import java.io.FileWriter;
import java.io.IOException;

public class GameLog {
    public FileWriter fileWriter;

    /*
     * Constructor initialises the FileWriter
     * Logs actions to file with given filename
     */
    public GameLog(String filename) throws IOException {
        this.fileWriter = new FileWriter(filename, true); // Append to log file
    }

    /*
     * Method to log action by writing the message to log file
     */
    public void logAction(String action) throws IOException {
        fileWriter.write(action + "\n"); // Log action to file
        fileWriter.flush(); // Ensure data written to disk immediately
    }

    // Closes FileWriter once game ends
    public void close() throws IOException {
        if (fileWriter != null) {
            fileWriter.close();
        }
    }
}