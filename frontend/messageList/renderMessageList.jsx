import React from "react";
import { createRoot } from "react-dom/client";
import MessageList from "./MessageList";

const root = createRoot(document.getElementById("messageListRoot"));
root.render(<MessageList />);
