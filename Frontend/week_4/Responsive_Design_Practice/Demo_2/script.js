document.getElementById("Registration-form").addEventListener("submit",function(e){
    e.preventDefault();

    let name = document.getElementById("name").value.trim();
    let email = document.getElementById("email").value.trim();
    let password = document.getElementById("password").value.trim();

    console.log(name,email,password);
    if(name === ""){
        //alert("name is require !!");
        document.getElementById("nameError").innerText = "Name is require !!"
        return;
    }
    if(!email.includes("@")){
        //alert("email is not standerd formate !!");
        document.getElementById("emailError").innerText = "email is require proper formate !!"
        return;
    }
    if(password.length<6){
        //alert("Password must be greater than 6 eliment !!");
        document.getElementById("passwordError").innerText = "password must be greater than 6 element is require !!"
        return;
    }
    alert("Form submitted sucessfully");
})