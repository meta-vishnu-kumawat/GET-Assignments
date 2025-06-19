const filteredArray = (originalArray, removeValuesArray) => {
  return originalArray.filter(item => !removeValuesArray.includes(item));
};

// Example:
const original = [1, 2, 3, 4, 5, 6];
const remove = [2, 4];
const result = filteredArray(original, remove);
console.log(result); // Output: [1, 3, 5, 6]
