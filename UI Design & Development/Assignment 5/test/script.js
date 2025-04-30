
window.onload = () => {
    const employeeForm = document.querySelector("#employee-form");
    console.log("Employee Form Visibility:", employeeForm.style.display);
};
document.querySelector("#employee-form").style.display = "block";
document.querySelector("#register-btn").addEventListener("click", (event) => {
    event.preventDefault(); // Stop default form behavior

    let name = document.querySelector("#full-name").value;
    let email = document.querySelector("#email").value;
    let password = document.querySelector("#password").value;
    let contact = document.querySelector("#contact").value;

    // Validate Required Fields
    if (!name || !email || !password || !contact) {
        alert("Please complete all fields before registering.");
        return;
    }

    // Validate Individual Fields
    if (!/^[a-zA-Z ]{2,}$/.test(name)) {
        alert("Invalid Name: It must have at least 2 letters and no numbers.");
        return;
    }
    if (!/^\S+@\S+\.\S+$/.test(email)) {
        alert("Invalid Email: Please enter a valid email format.");
        return;
    }
    if (!/(?=.*[A-Z])(?=.*[a-z])(?=.*\d)(?=.*\W).{8,}/.test(password)) {
        alert("Invalid Password: It must contain uppercase, lowercase, numbers, and special characters.");
        return;
    }
    if (!/^\d{8,}$/.test(contact)) {
        alert("Invalid Contact Number: Must be numeric with at least 8 digits.");
        return;
    }

    // Success Message and Proceeding to Next Section
    alert(`Welcome ${name}, your registration is complete!`);
    document.querySelector("#employee-section").style.display = "none";
    document.querySelector("#vehicle-section").classList.remove("hidden");
});

document.querySelector("#employee-collapse>img").addEventListener("click", () => {
    const getEmpComp = document.querySelector(".employee-full");
    const rightArr = document.querySelector("#employee-collapse>img");

    if (getEmpComp.style.display === "none" || getEmpComp.style.display === "") {
        getEmpComp.style.display = "flex";
        rightArr.classList.toggle("rotate");
    } else {
        getEmpComp.style.display = "none";
    }
});
document.getElementById("name-field").style.display = "none";
