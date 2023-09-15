export default function fetchMessages() {
  return fetch("/api/messages").then((response) => response.json());
}
