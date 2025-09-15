import stomp
import sys
import threading
import time


class ChatListener(stomp.ConnectionListener):
    def on_error(self, frame):
        print("❌ Error:", frame.body)

    def on_message(self, frame):
        print(f"\n💬 {frame.body}\n> ", end="", flush=True)


def main():
    if len(sys.argv) < 3:
        print("Usage: python chat_client.py <chatId> <senderId>")
        sys.exit(1)

    chat_id = sys.argv[1]
    sender_id = sys.argv[2]

    # Connect to localhost:8081 (where Spring Boot runs)
    conn = stomp.Connection([("localhost", 8081)], auto_content_length=False)
    conn.set_listener("", ChatListener())
    conn.connect(wait=True)

    # Subscribe to the chat room
    destination = f"/topic/chat.{chat_id}"
    conn.subscribe(destination=destination, id=1, ack="auto")
    print(f"✅ Connected to chat room {chat_id} as {sender_id}")
    print("Type messages and press Enter to send. Ctrl+C to quit.\n")

    try:
        while True:
            msg = input("> ")
            if msg.strip() == "":
                continue

            payload = (
                f'{{"chatId":"{chat_id}","senderId":"{sender_id}","content":"{msg}"}}'
            )
            conn.send(destination="/app/chat.sendMessage", body=payload)
    except KeyboardInterrupt:
        print("\n👋 Disconnecting...")
    finally:
        conn.disconnect()


if __name__ == "__main__":
    main()
