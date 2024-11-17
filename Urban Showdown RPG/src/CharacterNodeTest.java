import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class CharacterNodeTest {

    // Test the initialisation of a CharacterNode with a Character object
    @Test
    public void testCharacterNodeInitialization() {
        Character character = new Character("Rebel", 10, 10, 25, 12);
        CharacterNode node = new CharacterNode(character);
        assertEquals(character, node.character, "The character in the node should match the initialised character."); // // Assert that the character in the node matches the initialised character
        assertNull(node.next, "The next reference in the CharacterNode should be null upon initialisation.");
    }
}
