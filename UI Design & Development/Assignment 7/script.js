// Custom alert wrapper for easy future updates
const showAlert = message => alert(message);

// Steps and tab management
const steps = [...document.querySelectorAll(".step")];
const tabs = document.querySelectorAll(".tabs li");

let currentStep = 0;

const showStep = idx => {
  steps.forEach((s, i) => s.classList.toggle("hidden", i !== idx));
  updateTabs();
};


// Validate and advance employee
const validateAndNext = step => {
  const ids = ["fullName", "gender", "email", "password", "confirmPassword", "contact"];
  const fid = ids[step - 1];

  let valid = false;

  if (fid === "gender") {
    valid = !!document.querySelector('input[name="gender"]:checked');
    if (!valid) showAlert("Select your gender.");
  } else if (fid === "confirmPassword") {
    const pw = document.getElementById("password").value;
    const cpw = document.getElementById("confirmPassword").value;
    if (cpw !== pw) showAlert("Passwords must match.");
    else valid = patterns.password.test(cpw);
  } else {
    const inp = document.getElementById(fid);
    const key = fid === "fullName" ? "name" : fid;
    if (!patterns[key]) {
      console.error(`Validation pattern for ${key} is missing.`);
      return;
    }
    valid = patterns[key].test(inp.value.trim());
    if (!valid) showAlert(`Invalid ${fid}.`);
  }

  if (valid) {
    if (step === 1) {
      document.getElementById("greeting").textContent = `Hi ${document.getElementById("fullName").value.trim()}! Can I know your gender?`;
    }
    currentStep = step;
    showStep(currentStep);
  }
};
const updateTabs = () => {
  tabs.forEach(t => t.classList.remove("active"));
  const id = steps[currentStep].id;
  const section = id.startsWith("step-v")
    ? "vehicle"
    : id.startsWith("step-pass")
    ? "pass"
    : "employee";
  
  document.querySelector(`.tabs li[data-step="${section}"]`).classList.add("active");
};

showStep(currentStep);

// Validation regex
const patterns = {
  name: /^[A-Za-z]{2,}(?: [A-Za-z]+)*$/,
  email: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\.[A-Za-z]{2,}$/,
  password: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^\w\s]).{8,}$/,
  contact: /^\d{10,}$/,
  vehicleNum: /^[A-Z]{2}\d{2}-[A-Z]{2}-\d{4}$/
};
console.log(patterns.email.test("test@example.com")); // true
console.log(patterns.email.test("invalid-email")); // false

// Employee navigation
for (let i = 1; i <= 6; i++) {
  document.getElementById(`next-${i}`)?.addEventListener("click", () => validateAndNext(i));
  document.getElementById(`prev-${i}`)?.addEventListener("click", () => {
    currentStep = i - 1;
    showStep(currentStep);
  });
}
// Submit employee, generate ID
const validateAndSubmitEmployee = () => {
  const contactVal = document.getElementById("contact").value.trim();
  if (!patterns.contact.test(contactVal)) {
    showAlert("Contact number must be at least 10 digits.");
    return;
  }
document.getElementById("submit-employee").addEventListener("click", validateAndSubmitEmployee);





  const id = `EMP-${Math.floor(Math.random() * 90000 + 10000)}`;
  document.getElementById("regId").textContent = id;
  document.getElementById("employeeId").value = id;

  const empData = {
    fullName: document.getElementById("fullName").value.trim(),
    gender: document.querySelector('input[name="gender"]:checked').value,
    email: document.getElementById("email").value.trim(),
    contact: contactVal,
    regId: id
  };

  localStorage.setItem("employeeData", JSON.stringify(empData));

  currentStep = steps.findIndex(s => s.id === "employee-complete");
  showStep(currentStep);

  document.getElementById("next-empComplete").addEventListener("click", () => {
    currentStep++;
    showStep(currentStep);
  });
};