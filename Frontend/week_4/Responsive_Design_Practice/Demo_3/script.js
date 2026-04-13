const emailInput = document.getElementById("email");
const passwordInput = document.getElementById("password");

const emailError = document.getElementById("emailError");
const passwordError = document.getElementById("passwordError");

console.log(emailInput, passwordInput);

emailInput.addEventListener("input", validateEmail);
passwordInput.addEventListener("input", validatePassword);

document.getElementById("loginForm").addEventListener("submit", function(e){
    e.preventDefault();

    const isEmailValid = validateEmail();
    const isPasswordValid = validatePassword();

    console.log(emailInput, passwordInput);

    if(isEmailValid && isPasswordValid){
        alert("Login Successful !!!")
    }
});

function validateEmail() {
    const email = emailInput.value.trim();
    const emailRegex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;

    if (email === "") {
        emailError.innerText = "Email is required";
        return false;
    } else if (!emailRegex.test(email)) {
        emailError.innerText = "Enter a valid email";
        return false;
    } else {
        emailError.innerText = "";
        return true;
    }
}

function validatePassword() {
    const password = passwordInput.value.trim();

    if (password === "") {
        passwordError.innerText = "Password is required";
        return false;
    } else if (password.length < 6) {
        passwordError.innerText = "Password must be at least 6 characters";
        return false;
    } else {
        passwordError.innerText = "";
        return true;
    }
}