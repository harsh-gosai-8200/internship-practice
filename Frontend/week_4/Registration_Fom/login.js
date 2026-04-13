document.getElementById("loginForm").addEventListener("Submit", function (e) {
    e.preventDefault();

    const email = document.querySelector('input[type="email"]').value.trim();
    const password = document.querySelector('input[type="password"]').value;

    const storedUser = localStorage.getItem("userData");

    if (!storedUser) {
        alert("No user found. Please register first.");
        return;
    }

    const user = JSON.parse(storedUser);

    if (email === user.email && password === user.password) {
        alert("Login successful");
        
    } else {
        alert("Invalid email or password");
    }
});
