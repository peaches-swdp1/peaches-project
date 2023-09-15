import React, { useEffect, useState } from "react";
import MessageListItem from "./MessageListItem";
import fetchMessages from "./fetchMessages";

export default function MessageList() {
  const [messages, setMessages] = useState([]);

  useEffect(() => {
    fetchMessages().then((fetchedMessages) => setMessages(fetchedMessages));
  }, []);

  return (
    <div>
      <h1>Messages</h1>

      <ul>
        {messages.map((message) => (
          <MessageListItem message={message} key={message.id} />
        ))}
      </ul>

      <a className="btn btn-primary" href="/messages/add">
        Add a message
      </a>
    </div>
  );
}
