# Urban Showdown RPG

**Urban Showdown RPG** is a simple turn-based role-playing game (RPG) written in Java. The game features core object-oriented programming (OOP) principles, utilising concepts such as classes, objects, inheritance, polymorphism, and encapsulation. 

The game allows two players to engage in battle using a team of characters, where each character has specific attributes like health, defense, and attack power. Players take turns to attack, defend, and select their characters, while the game logs key events and battle outcomes.

---

## Java Techniques Used

### 1. **Object-Oriented Programming (OOP)**

- **Classes and Objects:** Used to represent **characters**, **teams**, and **game actions**.
- **Inheritance:** Enables the extension of basic character attributes and functionality. This allows future additions of character types with ease.
- **Polymorphism:** The game handles different character types and actions through method overriding, allowing behavior to vary based on the character’s role in the game.
- **Encapsulation:** Data is encapsulated within classes, protecting character stats and actions. Access is granted only through the appropriate methods, ensuring data integrity.

### 2. **Instance Methods**

These methods handle the core game actions, **attacking** and **defending**. Examples include:
- `defend()`: Temporarily boosts a character's defense.
- `attack()`: Calculates the damage dealt during a turn, considering the character’s attributes and whether the opponent is defending.

### 3. **Constructors**

Constructors are used to **initialise objects** like characters and teams with either default values or based on user input, making the game setup flexible and customisable.

### 4. **File I/O**

The game uses **file handling** techniques to log **battle events**, such as character actions and outcomes, to a text file. This feature allows users to review previous sessions.

### 5. **Arrays and Collections**

Arrays are used to manage **teams of characters**, making it easy to access and modify player attributes and game statuses during battles.

### 6. **Recursion**

Recursion is used to manage turn-based gameplay, allowing the game to repeatedly execute player turns until a win or loss condition is met.

### 7. **Test Cases with JUnit**

JUnit is used to implement unit tests for key game mechanics, ensuring that methods perform as expected. 



