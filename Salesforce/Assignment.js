// 1. findMin using rest and spread operator
const findMin = (...args) => Math.min(...args);

// 2. Animal and Dog class using prototype-based inheritance
function Animal(name) {
    this.name = name;
}
Animal.prototype.getName = function() {
    return this.name;
}
function Dog(name) {
    Animal.call(this, name);
}
Dog.prototype = Object.create(Animal.prototype);
Dog.prototype.constructor = Dog;
Dog.prototype.bark = function() {
    console.log(this.name);
}

// 3. multiplyByEight using closures
function multiplyByEight() {
    return function(x) {
        return x * 8;
    }
}

// 4. waitAndReturn and usage with async/await
function waitAndReturn() {
    return new Promise(resolve => {
        setTimeout(() => {
            resolve("Resolved");
        }, 5000);
    });
}
async function callWaitAndReturn() {
    const result = await waitAndReturn();
    console.log(result);
}
callWaitAndReturn();

// 5. filteredArray to remove specified values
const filteredArray = (originalArray, removeValuesArray) => {
    return originalArray.filter(item => !removeValuesArray.includes(item));
}
