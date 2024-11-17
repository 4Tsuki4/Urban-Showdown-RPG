import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CharacterTest {

    // Test the initialisation of a Character object with specified attributes
    @Test
    public void testCharacterInitialization() {
        Character character = new Character("Rebel", 10, 10, 25, 12);
        assertEquals("Rebel", character.name);
        assertEquals(10, character.healthPoints);
        assertEquals(10, character.strength);
        assertEquals(25, character.defense);
        assertEquals(12, character.initiative);
        assertTrue(character.alive);
        assertFalse(character.isDefending);
    }

    // Test the takeDamage method to ensure it correctly reduces health points
    @Test
    public void testTakeDamage() {
        Character character = new Character("Student", 20, 8, 8, 20);
        character.takeDamage(10);
        assertEquals(10, character.healthPoints);
        character.takeDamage(15);
        assertFalse(character.alive);
        assertEquals(0, character.healthPoints);
    }

    // Test the attack method to ensure it correctly reduces the opponent's health
    @Test
    public void testAttackReducesOpponentHealth() {
        Character attacker = new Character("Athlete", 20, 25, 10, 10);
        Character defender = new Character("Rebel", 10, 10, 25, 12);
        attacker.attack(defender);
        assertTrue(defender.healthPoints < 10);
    }

    // Test the defend method to ensure it increases the character's defense
    @Test
    public void testDefendIncreasesDefense() {
        Character character = new Character("Motivational Speaker", 25, 5, 5, 25);
        character.defend();
        assertTrue(character.isDefending);
        assertEquals(7, character.defense); // Defense increased by 2
    }

    // Test the updateDefense method to ensure it correctly manages the defending state
    @Test
    public void testUpdateDefense() {
        Character character = new Character("Police Officer", 15, 16, 15, 6);
        character.defend();
        character.updateDefense();
        assertEquals(1, character.defendTurnsRemaining); // Should still have 1 turn left
        character.updateDefense();
        assertFalse(character.isDefending); // Should no longer be defending
        assertEquals(15, character.defense); // Defense should reset
    }
}
