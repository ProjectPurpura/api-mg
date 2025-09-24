import websocket
import threading
import json
import sys
import re
import os
import time


class ChatClient:
    def __init__(self, chat_id, sender_id, ws_url):
        self.chat_id = chat_id
        self.sender_id = sender_id
        self.ws_url = ws_url
        self.ws = None
        self.connected = threading.Event()
        self.running = True
        self.input_thread = None

    def parse_stomp_frame(self, message):
        # Minimal STOMP frame parser
        if not message:
            return None, {}, ''
        parts = message.split('\n\n', 1)
        header = parts[0]
        body = parts[1][:-1] if len(parts) > 1 and parts[1].endswith('\0') else (parts[1] if len(parts) > 1 else '')
        lines = header.split('\n')
        command = lines[0]
        headers = {}
        for line in lines[1:]:
            if ':' in line:
                k, v = line.split(':', 1)
                headers[k.strip()] = v.strip()
        return command, headers, body

    def on_message(self, ws, message):
        command, headers, body = self.parse_stomp_frame(message)
        if command == 'CONNECTED':
            print('🔗 STOMP CONNECTED')
            self.connected.set()
        elif command == 'MESSAGE':
            try:
                data = json.loads(body)
                print(f"\n💬 {json.dumps(data, indent=2, ensure_ascii=False)}\n> ", end="", flush=True)
            except Exception:
                print(f"\n💬 {body}\n> ", end="", flush=True)
        elif command == 'ERROR':
            print(f"\n❌ STOMP ERROR: {body}\n> ", end="", flush=True)
        else:
            print(f"\n[STOMP] {command}: {body}\n> ", end="", flush=True)

    def on_open(self, ws):
        try:
            ws.send("CONNECT\naccept-version:1.2\nhost:localhost\n\n\0")
        except Exception as e:
            print(f"❌ Failed to send CONNECT: {e}")

    def on_error(self, ws, error):
        print(f"\n❌ WebSocket error: {error}")
        self.running = False
        self.connected.set()

    def on_close(self, ws, close_status_code, close_msg):
        print(f"\n🔒 WebSocket closed: {close_status_code} {close_msg}")
        self.running = False
        self.connected.set()

    def input_loop(self):
        while self.running:
            try:
                msg = input("> ")
            except (EOFError, KeyboardInterrupt):
                print("\n👋 Exiting chat...")
                self.running = False
                if self.ws:
                    self.ws.close()
                break
            if not msg.strip():
                continue
            if msg.startswith("/read"):
                ids = re.findall(r"[\w-]+", msg[len("/read"):])
                if not ids:
                    print("⚠️  Usage: /read <id1> <id2> ...")
                    continue
                frame = (
                    "SEND\ndestination:/app/chat.markRead\ncontent-type:application/json\n\n" +
                    json.dumps({"messageIds": ids}) +
                    "\0"
                )
                try:
                    self.ws.send(frame)
                    print(f"🔖 Marked as read: {', '.join(ids)}")
                except Exception as e:
                    print(f"❌ Failed to send /read: {e}")
            else:
                frame = (
                    "SEND\ndestination:/app/chat\ncontent-type:application/json\n\n" +
                    json.dumps({
                        "chatId": self.chat_id,
                        "senderId": self.sender_id,
                        "content": msg
                    }) +
                    "\0"
                )
                try:
                    self.ws.send(frame)
                except Exception as e:
                    print(f"❌ Failed to send message: {e}")

    def run(self):
        self.ws = websocket.WebSocketApp(
            self.ws_url,
            on_message=self.on_message,
            on_open=self.on_open,
            on_error=self.on_error,
            on_close=self.on_close
        )
        wst = threading.Thread(target=self.ws.run_forever, daemon=True)
        wst.start()
        # Wait for CONNECTED
        if not self.connected.wait(timeout=10):
            print("❌ Failed to connect to STOMP server.")
            self.running = False
            return
        # Subscribe after CONNECTED
        try:
            self.ws.send(f"SUBSCRIBE\nid:sub-0\ndestination:/topic/chat.{self.chat_id}\n\n\0")
            print(f"✅ Connected to chat {self.chat_id} as {self.sender_id}")
            print("Type messages and press Enter to send. Use /read <id1> <id2> ... to mark messages as read. Ctrl+C to quit.\n")
        except Exception as e:
            print(f"❌ Failed to subscribe: {e}")
            self.running = False
            return
        self.input_thread = threading.Thread(target=self.input_loop, daemon=True)
        self.input_thread.start()
        # Wait for input thread to finish
        while self.running and self.input_thread.is_alive():
            time.sleep(0.1)
        if self.ws:
            self.ws.close()


if __name__ == "__main__":
    if len(sys.argv) < 3:
        print("Usage: python main.py <chatId> <senderId> [ws_url]")
        sys.exit(1)
    chat_id = sys.argv[1]
    sender_id = sys.argv[2]
    ws_url = sys.argv[3] if len(sys.argv) > 3 else os.environ.get("WS_URL", "ws://localhost:8081/ws-chat")
    client = ChatClient(chat_id, sender_id, ws_url)
    client.run()
