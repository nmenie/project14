// channel.js

document.addEventListener("DOMContentLoaded", function () {
    const chatBox = document.getElementById("chat-box");
    const messageInput = document.getElementById("message-input");

    const username = document.body.getAttribute("data-username");
    const channelId = document.body.getAttribute("data-channel");

    // Poll messages every 500ms
    setInterval(loadMessages, 500);

    // Send message on Enter key
    messageInput.addEventListener("keydown", function (e) {
        if (e.key === "Enter") {
            sendMessage();
        }
    });

    // Load messages from the server
    function loadMessages() {
        fetch(`/channels/${channelId}/messages`)
            .then(response => response.json())
            .then(messages => {
                chatBox.innerHTML = '';
                messages.forEach(msg => {
                    const messageDiv = document.createElement("div");
                    messageDiv.textContent = `[${msg.timestamp}] ${msg.sender}: ${msg.content}`;
                    chatBox.appendChild(messageDiv);
                });
                chatBox.scrollTop = chatBox.scrollHeight;
            })
            .catch(err => console.error("Failed to load messages:", err));
    }

    // Make sendMessage globally available
    window.sendMessage = function () {
        const content = messageInput.value.trim();
        if (content === "") return;

        const formData = new URLSearchParams();
        formData.append("content", content);

        fetch(`/channels/${channelId}/messages`, {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: formData
        })
        .then(response => response.json())
        .then(() => {
            messageInput.value = "";
            loadMessages(); // Refresh messages
        })
        .catch(err => console.error("Failed to send message:", err));
    };
});