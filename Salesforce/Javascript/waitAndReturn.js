function waitAndReturn() {
  return new Promise(resolve => {
    setTimeout(() => {
      resolve("Resolved");
    }, 5000);
  });
}

async function runAsync() {
  const result = await waitAndReturn();
  console.log(result); // Output after 5 seconds: Resolved
}

runAsync();
