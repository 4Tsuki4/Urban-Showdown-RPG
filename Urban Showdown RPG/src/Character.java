public class Character {
    // Attributes for each character in game
    public String name;
    public int healthPoints;
    public int maxHealthPoints;
    public int strength;
    public int defense;
    public int initiative;
    public boolean alive;
    public boolean isDefending; 
    private int defendTurnsRemaining; // Tracks remaining turns for defense boost

    /*
     * Constructor to initialise character with stats
     */
    public Character(String name, int healthPoints, int strength, int defense, int initiative) {
        this.name = name;
        this.healthPoints = healthPoints;
        this.maxHealthPoints = healthPoints;
        this.strength = strength;
        this.defense = defense;
        this.initiative = initiative;
        this.alive = true;
        this.isDefending = false;
        this.defendTurnsRemaining = 0;
    }

    /*
     * Method to attack opponent. Takes into account whether opponent 
     * is defending and applies damage accordingly
     */
    public void attack(Character opponent) {
        if (this.alive) {
            // Damage reduced if opponent is defending
            int damage = this.strength - (opponent.isDefending ? opponent.defense / 2 : opponent.defense);
            damage += (int)(Math.random() * 5.0); // Add random variation to damage

            if (damage > 0) {
                opponent.takeDamage(damage); 
                System.out.println(this.name + " attacked " + opponent.name + " for " + damage + " damage!");
            } else {
                System.out.println(opponent.name + " blocked the attack!"); // No damage if defense is higher
            }

            // Check if defense boost updated after attack
            if (this.isDefending) {
                this.updateDefense();
            }
        } else {
            System.out.println(this.name + " is unable to attack, they are dead.");
        }
    }

    /*
     * Method to reduce health of a character when they take damage
     * If health falls to 0 or below, the character is defeated
     */
    public void takeDamage(int damage) {
        this.healthPoints -= damage;
        if (this.healthPoints <= 0) {
            this.alive = false; // Character defeated
            this.healthPoints = 0;
            System.out.println(this.name + " has been defeated!");
        }
    }

    /*
     * Method for character to defend, which temporarily increases their defense
     * Defense boost lasts 1 turn
     */
    public void defend() {
        this.isDefending = true;
        this.defense += 2; // Increase defense temporarily
        this.defendTurnsRemaining = 1; // Defense lasts 1 turn
        System.out.println(this.name + " is defending!");
    }

    /*
     * Method to update defense status at end of each turn
     * Removes defense boost if defending period is over
     */
    public void updateDefense() {
        if (this.defendTurnsRemaining > 0) {
            this.defendTurnsRemaining--; // Decrease number of turns remaining
            if (this.defendTurnsRemaining == 0) {
                this.isDefending = false; // Reset defense boost after defending period
                this.defense -= 2;
                System.out.println(this.name + " has stopped defending.");
            }
        }
    }

    // Returns whether character is still alive
    public boolean Alive() {
        return this.alive;
    }
}