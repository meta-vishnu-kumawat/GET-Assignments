function multiplyByEight() {
  const multiplier = 8;
  return function(x) {
    return x * multiplier;
  };
}

// Example:
const mult8 = multiplyByEight();
console.log(mult8(5)); // Output: 40
