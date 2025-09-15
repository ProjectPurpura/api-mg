import websocket
import threading
import json
import sys


def on_message(ws, message):
    print(f"\n💬 {message}\n> ", end="", flush=True)


def on_open(ws, chat_id, sender_id):
    # Send STOMP CONNECT
    ws.send("CONNECT\naccept-version:1.2\nhost:localhost\n\n\0")

    # Subscribe to chat room
    ws.send(f"SUBSCRIBE\nid:sub-0\ndestination:/topic/chat.{chat_id}\n\n\0")

    print(f"✅ Connected to chat {chat_id} as {sender_id}")
    print("Type messages and press Enter to send. Ctrl+C to quit.\n")

    def run():
        while True:
            msg = input("> ")
            if not msg.strip():
                continue
            frame = (
                "SEND\ndestination:/app/chat\n"
                "content-type:application/json\n\n" +
                json.dumps({
                    "chatId": chat_id,
                    "senderId": sender_id,
                    "content": msg
                }) +
                "\0"
            )
            ws.send(frame)

    threading.Thread(target=run, daemon=True).start()


if __name__ == "__main__":
    if len(sys.argv) < 3:
        print("Usage: python chat.py <chatId> <senderId>")
        sys.exit(1)

    chat_id = sys.argv[1]
    sender_id = sys.argv[2]

    ws = websocket.WebSocketApp(
        "ws://localhost:8081/ws-chat",
        on_message=on_message,
        on_open=lambda ws: on_open(ws, chat_id, sender_id),
    )

    ws.run_forever()
