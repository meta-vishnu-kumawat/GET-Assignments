
function Dog(name) {
  Animal.call(this, name); // Call the parent constructor
}

// Inherit prototype
Dog.prototype = Object.create(Animal.prototype);
Dog.prototype.constructor = Dog;

Dog.prototype.bark = function() {
  console.log(this.name + " says Woof!");
};

// Example:
const dog1 = new Dog("Buddy");
dog1.bark(); // Output: Buddy says Woof!