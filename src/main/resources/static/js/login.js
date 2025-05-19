document.addEventListener("DOMContentLoaded", function () {
    let username = prompt("What is your name?");
    if (username && username.trim() !== "") {
        sessionStorage.setItem("username", username.trim());

        fetch("/login", {
            method: "POST",
            headers: {
                "Content-Type": "application/x-www-form-urlencoded"
            },
            body: `username=${encodeURIComponent(username)}`
        })
        .then(() => {
            window.location.href = "/channels"; // or "/channels/general"
        })
        .catch(err => {
            alert("Login failed. Please try again.");
            console.error(err);
        });
    } else {
        alert("You must enter a name to continue.");
        window.location.reload();
    }
});

