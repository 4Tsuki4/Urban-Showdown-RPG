import org.junit.jupiter.api.Test;
import java.io.IOException;
import static org.junit.jupiter.api.Assertions.*;

public class GameLogTest {

    // Test the logAction method to ensure actions are logged correctly
    @Test
    public void testLogAction() throws IOException {
        GameLog log = new GameLog("testLog.txt");
        log.logAction("Player 1 selected Rebel.");
        log.close(); // Close to flush the log
        // Check file content to ensure action is logged properly
        // Use file reading techniques to verify the content
    }

    // Test the close method to ensure it executes without throwing exceptions
    @Test
    public void testClose() throws IOException {
        GameLog log = new GameLog("testLog.txt");
        log.close(); // No exceptions should be thrown
    }
}
