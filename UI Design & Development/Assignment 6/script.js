// Custom alert wrapper for easy future updates
function showAlert(message) {
    alert(message);
  }
  
  // Steps and tab management
  const steps = Array.from(document.querySelectorAll(".step"));
  const tabs = document.querySelectorAll(".tabs li");
  
  let currentStep = 0;
  
  function showStep(idx) {
    steps.forEach((s, i) => s.classList.toggle("hidden", i !== idx));
    updateTabs();
  }
  
  function updateTabs() {
    tabs.forEach((t) => t.classList.remove("active"));
    const id = steps[currentStep].id;
    let sec = "employee";
    if (id.startsWith("step-v")) {
      sec = "vehicle";
    } else if (id.startsWith("step-pass")) {
      sec = "pass";
    }
    document
      .querySelector(`.tabs li[data-step="${sec}"]`)
      .classList.add("active");
  }
  showStep(currentStep);
  
  // Validation regex
  // const patterns = {
  //   name: /^[A-Za-z ]{2,}$/,
  //   email: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9-]+\.[a-zA-Z]{2,}$/,
  //   password: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^\w]).{8,}$/,
  //   contact: /^\d{10,}$/,
  //   vehicleNum: /^[A-Z]{2}\d{2}-[A-Z]{2}-\d{4}$/,
  // };
  
  const patterns = {
    name: /^[A-Za-z]{2,}(?: [A-Za-z]+)*$/,
    email: /^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9](?:[a-zA-Z0-9-]*[a-zA-Z0-9])?\.[a-zA-Z]{2,}$/,
    password: /^(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[^\w\s]).{8,}$/,
    contact: /^\d{10,}$/,
    vehicleNum: /^[A-Z]{2}\d{2}-[A-Z]{2}-\d{4}$/
  };
  
  
  
  
  // Employee navigation
  for (let i = 1; i <= 6; i++) {
    document
      .getElementById(`next-${i}`)
      ?.addEventListener("click", () => validateAndNext(i));
    document.getElementById(`prev-${i}`)?.addEventListener("click", () => {
      currentStep = i - 2;
      showStep(currentStep);
    });
  }
  document
    .getElementById("submit-employee")
    .addEventListener("click", validateAndSubmitEmployee);
  
  // Validate and advance employee
  function validateAndNext(step) {
    const ids = {
      1: "fullName",
      2: "gender",
      3: "email",
      4: "password",
      5: "confirmPassword",
      6: "contact",
    };
    console.log(step);
    const fid = ids[step];
    console.log("\n",fid);
  
    let ok = false;
    // if(step === 6){
    //   console.log("for contact num");
    // }
    if (fid === "gender") {
      ok = !!document.querySelector('input[name="gender"]:checked');
      if (!ok) {showAlert("Select your gender1.")};
    } else if (fid === "confirmPassword") {
      const pw = document.getElementById("password").value;
      const cpw = document.getElementById("confirmPassword").value;
      if (cpw !== pw) showAlert("Passwords must match.");
      else ok = patterns.password.test(cpw);
    }
    // else if (fid === "contact") {
    //   const contactNum = document.getElementById("contact").value;
    //   console.log(contactNum);
    //   ok = patterns.contact.test(contactNum);
    //   if(!ok){
    //     showAlert("phone number must be atleast 10 digits and only numbers without space")
    //   }
    // }
     else {
      const inp = document.getElementById(fid);
      const key = fid === "fullName" ? "name" : fid;
      if (!patterns[key].test(inp.value.trim())) showAlert(`Invalid ${fid}.`);
      else ok = true;
    }
    if (ok) {
      if (step === 1) {
        document.getElementById("greeting").textContent = `Hi ${document
          .getElementById("fullName")
          .value.trim()}! Can I know your gender?`;
      }
      currentStep = step;
      showStep(currentStep);
    }
  }
  
  // Submit employee, generate ID
  function validateAndSubmitEmployee() {
    const contactVal = document.getElementById('contact').value.trim();
    // Validate contact length and digits
    if (!patterns.contact.test(contactVal)) {
      showAlert('Contact number must be at least 10 digits.');
      return;
    }
    // All good: generate Registration ID
    const id = 'EMP-' + Math.floor(Math.random() * 90000 + 10000);
    document.getElementById('regId').textContent = id;
    document.getElementById('employeeId').value = id;
  
    // **Store employee data** in localStorage for summary page
    const empData = {
      fullName: document.getElementById('fullName').value.trim(),
      gender: document.querySelector('input[name="gender"]:checked').value,
      email: document.getElementById('email').value.trim(),
      contact: contactVal,
      regId: id
    };
    localStorage.setItem('employeeData', JSON.stringify(empData));
  
    // Move to completion
    currentStep = steps.findIndex(s => s.id === 'employee-complete');
    showStep(currentStep);
    document.getElementById('next-empComplete').addEventListener('click', () => {
      currentStep++;
      showStep(currentStep);
    });
  }
  
  // function validateAndSubmitEmployee() {
  //   const id = "EMP-" + Math.floor(Math.random() * 90000 + 10000);
  //   document.getElementById("regId").textContent = id;
  //   document.getElementById("employeeId").value = id;
  //   currentStep = steps.findIndex((s) => s.id === "employee-complete");
  //   showStep(currentStep);
  //   document.getElementById("next-empComplete").addEventListener("click", () => {
  //     currentStep++;
  //     showStep(currentStep);
  //   });
  // }
  
  // Password strength meter: count categories
  const pwIn = document.getElementById("password"),
    msg = document.getElementById("strength-msg");
  pwIn.addEventListener("input", () => {
    const v = pwIn.value;
    let categories = 0;
    if (/[a-z]/.test(v)) categories++;
    if (/[A-Z]/.test(v)) categories++;
    if (/\d/.test(v)) categories++;
    if (/\W/.test(v)) categories++;
    let cls = "";
    msg.textContent = "";
    if (v.length < 8 || categories < 2) {
      cls = "strength-weak";
      msg.textContent = "Weak";
    } else if (categories < 4) {
      cls = "strength-normal";
      msg.textContent = "Normal";
    } else {
      cls = "strength-strong";
      msg.textContent = "Strong";
    }
    pwIn.className = cls;
  });
  
  // Vehicle navigation
  for (let i = 1; i <= 5; i++) {
    document.getElementById(`next-v${i}`)?.addEventListener("click", () => {
      const el = document.getElementById(
        i === 3
          ? "vehicleNumber"
          : i === 5
          ? "identification"
          : i === 4
          ? "employeeId"
          : i === 2
          ? "vehicleType"
          : "vehicleName"
      );
      if (
        !el.value.trim() ||
        (i === 3 && !patterns.vehicleNum.test(el.value.trim()))
      ) {
        showAlert(i === 3 ? "Format: RJ14-MN-3435" : "Field required.");
        return;
      }
      currentStep++;
      showStep(currentStep);
    });
    document.getElementById(`prev-v${i}`)?.addEventListener("click", () => {
      currentStep--;
      showStep(currentStep);
    });
  }
  document.getElementById("submit-vehicle").addEventListener("click", () => {
    if (!document.getElementById("identification").value.trim()) {
      showAlert("Identification required.");
      return;
    }
    currentStep++;
    showStep(currentStep);
  });
  
  // Pass & currency logic
  const base = {
    Cycle: { daily: 5, monthly: 100, yearly: 500 },
    MotorCycle: { daily: 10, monthly: 200, yearly: 1000 },
    "Four Wheelers": { daily: 20, monthly: 500, yearly: 3500 },
  };
  const rates = { INR: 1, USD: 0.012, YEN: 1.7 };
  
  // Update plan labels
  function updatePlanLabels(currency) {
    ["daily", "monthly", "yearly"].forEach((plan) => {
      const inr = base[document.getElementById("vehicleType").value][plan];
      const conv = (inr * rates[currency]).toFixed(2);
      const text =
        currency === "INR" ? `${inr} ₹` : `${conv} ${currency} (${inr} ₹)`;
      document.getElementById(`label-${plan}`).textContent =
        plan.charAt(0).toUpperCase() + plan.slice(1) + ` (${text})`;
    });
  }
  
  // On vehicle submission, show pass step
  document.getElementById("submit-vehicle").addEventListener("click", () => {
    currentStep = steps.findIndex((s) => s.id === "step-pass");
    showStep(currentStep);
    updatePlanLabels("INR");
  });
  
  // Currency change updates labels
  document
    .getElementById("currency")
    .addEventListener("change", (e) => updatePlanLabels(e.target.value));
  
  // Back from pass
  document.getElementById("prev-pass").addEventListener("click", () => {
    currentStep--;
    showStep(currentStep);
  });
  
  // Final price
  document.getElementById("get-pass").addEventListener("click", () => {
    const plan = document.querySelector('input[name="plan"]:checked');
    if (!plan) {
      showAlert("Select a plan.");
      return;
    }
    updatePlanLabels(document.getElementById("currency").value);
    // Display selected radio label text
    const priceText = document.querySelector(
      `label > input[value="${plan.value}"] + span`
    ).textContent;
    document.getElementById("finalPrice").textContent = priceText;
    currentStep++;
    showStep(currentStep);
  });