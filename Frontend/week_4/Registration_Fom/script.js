document.getElementById("registerForm").addEventListener("submit", function (e) {
    e.preventDefault();

    const name = document.querySelector('input[name="name"]').value.trim();
    const email = document.querySelector('input[name="email"]').value.trim();
    const password = document.querySelector('input[name="password"]').value;
    const cpassword = document.querySelector('input[name="cpassword"]').value;

    // validations
    if (name === "" || email === "" || password === "" || cpassword === "") {
        alert("All fields are required");
        return;
    }

    if (!/^[^\s@]+@[^\s@]+\.[^\s@]+$/.test(email)) {
        alert("Invalid email format");
        return;
    }

    if (password.length < 6) {
        alert("Password must be at least 6 characters");
        return;
    }

    if (password !== cpassword) {
        alert("Passwords do not match");
        return;
    }

    // store user data
    const user = {
        name: name,
        email: email,
        password: password
    };

    localStorage.setItem("userData", JSON.stringify(user));

    alert("Registration successful! Redirecting to login...");
    window.location.href = "login.html";
});
