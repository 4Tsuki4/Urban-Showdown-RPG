**Urban Showdown RPG**


**Problem Description**
Urban Showdown RPG is a simple turn-based role-playing game where two teams of characters battle against each other. The game simulates tactical combat, allowing players to select characters with different stats like health points, strength, defense, and initiative. Each turn, characters take actions such as attacking or defending, and the game continues until one team is entirely defeated. Additionally, all game events, including attacks, defenses, and outcomes, are logged and written to a file, providing a detailed record of each battle session.



**Program Structure**
The program consists of the following classes:

Main Class: Initialises the game and facilitates interaction between the different components. It manages the game loop, prompts players for input, and controls the overall flow of the game.

Character Class: Represents a game character with attributes like name, health, strength, defense, and initiative. The character can perform actions such as attacking and defending, and the class keeps track of the character's status (alive or defeated).

CharacterNode Class: A wrapper around the Character object for data structure flexibility.

GameLog Class: Handles logging of game events to an external file. The log tracks the actions performed by each character and other important game events such as round announcements and game outcomes.

RPGGame Class: The core class that drives the game logic. It handles team setup, character selection, round-based combat, and determining the winner. This class also interfaces with the GameLog class to ensure the game events are properly documented.

Team Class: Represents a team of characters, manages the members of the team, and checks for the team's status (alive or defeated).



**Game Guide**
1. Character Attributes
Each character has the following attributes:
Health Points (HP): Determines how much damage a character can take before being defeated.
Strength: Determines how much damage a character can deal in an attack.
Defense: Reduces the amount of damage taken from an opponent's attack.
Initiative: Determines who takes the first turn in the battle.


2. Game Mechanics
2.1. Turn-Based Combat
The game alternates between Player 1 and Player 2 taking turns.
On a player's turn, they can choose to either:
Attack: Inflict damage on the opponent.
Defend: Boost their defense temporarily, reducing the damage from the next incoming attack.

2.2. Actions
Attack:
The attacking player’s strength is compared to the defending player’s defense. The damage dealt is the attacker’s strength minus the defender’s defense (but not below zero).
If the opponent is defending, their defense is temporarily boosted.
Defend:
When defending, the character's defense is increased for the next round.
The attacker's damage is reduced for that turn, and after the attack, the defense boost is reset.

2.3. Initiative and Turn Order
The character with the higher initiative takes the first turn.
If both characters have equal initiative, the game randomly determines who goes first.
Team initiative is calculated by adding each character on teams initiative stat.

2.4. Health and Defeat
A character's health is reduced when they take damage.
When a character’s health reaches zero, they are defeated, and can no longer be used in battle.
When all characters on a player's team are defeated the opponent wins. 


3. Game Events
Logging: All key events in the game are recorded in a log file. This allows players to review the course of the battle after the game ends.