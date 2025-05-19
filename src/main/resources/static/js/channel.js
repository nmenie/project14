document.addEventListener("DOMContentLoaded", function () {
    const chatBox = document.getElementById("chat-box");
    const messageInput = document.getElementById("message-input");

    const username = sessionStorage.getItem("username") || "Guest";
    const channelId = document.body.getAttribute("data-channel");

    setInterval(loadMessages, 500);

    messageInput.addEventListener("keydown", function (e) {
        if (e.key === "Enter") {
            sendMessage();
        }
    });

    function loadMessages() {
        fetch(`/channels/${channelId}/messages`)
            .then(response => response.json())
            .then(messages => {
                chatBox.innerHTML = '';
                messages.forEach(msg => {
                    const timestamp = msg.formattedTimestamp || "unknown time";
                    const sender = msg.sender || "unknown";
                    const content = msg.content || "";

                    const messageDiv = document.createElement("div");
                    messageDiv.textContent = `${timestamp} ${sender}: ${content}`;
                    chatBox.appendChild(messageDiv);
                });
                chatBox.scrollTop = chatBox.scrollHeight;
            })
            .catch(err => console.error("Failed to load messages:", err));
    }

    window.sendMessage = function () {
        const content = messageInput.value.trim();
        if (content === "") return;

        const formData = new URLSearchParams();
        formData.append("sender", username);
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
            loadMessages();
        })
        .catch(err => console.error("Failed to send message:", err));
    };
});

