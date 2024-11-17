import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class RpgGameTest {

    // Test the initialisation of the character pool to ensure it contains the correct number of characters
    @Test
    public void testCharacterPoolInitialisation() {
        RpgGame game = new RpgGame();
        assertEquals(6, game.characterPool.size()); // Assuming you initialised with 6 characters
    }

    // Test the calculation of total initiative for a given team
    @Test
    public void testCalculateTeamInitiative() {
        RpgGame game = new RpgGame();
        Team team = new Team();
        team.addMember(new Character("Rebel", 10, 10, 25, 12));
        team.addMember(new Character("Student", 20, 8, 8, 20));
        
        int initiative = game.calculateTeamInitiative(team);
        assertEquals(32, initiative); // Adjust based on character initiative values
    }
}
