import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RpgGame {
    // Color codes for console output
    public static final String RESET = "\033[0m"; // Reset terminal color
    public static final String GREEN = "\033[0;32m";  
    public static final String RED = "\033[0;31m";  
    public static final String YELLOW = "\033[0;33m";  

    private Team player1Team;
    private Team player2Team;
    private ArrayList<Character> characterPool; // Pool of available characters
    private Scanner scanner; // Scanner for user input
    private GameLog gameLog;
   
    /*
     * Constructor to initialise game
     * Sets up player teams, character pool, and game log file
     */
    public RpgGame() {
        this.player1Team = new Team();
        this.player2Team = new Team();
        this.characterPool = new ArrayList<>();
        this.scanner = new Scanner(System.in);

        // Set up log file with timestamp
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
        String timestamp = LocalDateTime.now().format(formatter);
        String logFileName = "GameLog_" + timestamp + ".txt";

         // Try to create a game log file
        try {
            this.gameLog = new GameLog(logFileName);
        } catch (IOException e) {
            System.err.println("Failed to create log file: " + e.getMessage());
            System.exit(1); // Exit if log file creation fails
        }

        initialiseCharacterPool(); // Initialise the character pool with available characters
    }

    /*
     * Method to close game log file safely
     */
    public void closeGameLog() {
        try {
            if (gameLog != null) {
                gameLog.close();
            }
        } catch (IOException e) {
            System.err.println("Error closing log file: " + e.getMessage());
        }
    }

    /*
     * Method to display starting screen and handle user selection
     */
    public void showStartScreen() {
        System.out.println(YELLOW + "=== Welcome to the Urban Showdown RPG! ===" + RESET);
        System.out.println(GREEN + "1. Start Game" + RESET);
        System.out.println(RED + "2. Quit" + RESET);
        System.out.print("Select an option: ");
        
        String choice = scanner.nextLine().trim(); // Get user input
        
        switch (choice) {
            case "1":
                startCharacterSelection(); // Start character selection
                break;
            case "2":
                System.out.println("Cowards always leave a good fight!");
                closeGameLog(); // Close log file before quitting
                System.exit(0);
            default:
                System.out.println(RED + "Invalid option. Select again." + RESET);
                showStartScreen(); // Re-display start screen on invalid input
        }
    }

    /*
     * Method to manage character selection for both players
     */
    public void startCharacterSelection() {
        System.out.println(YELLOW + "\n=== Player 1 Character Selection ===" + RESET);
        selectTeamCharacters(player1Team); // Allow Player 1 to select characters

        System.out.println(YELLOW + "\n=== Player 2 Character Selection ===" + RESET);
        selectTeamCharacters(player2Team); // Allow Player 2 to select characters

        System.out.println("\nBoth teams are ready!");
        displayTeams(); // Display both teams

        printStoryline();
        start();
    }

    /*
     * Method to allow player to select characters for their team
     */
    public void selectTeamCharacters(Team team) {
        System.out.println("Select 3 characters for your team.");
    
        System.out.println("\nAvailable characters:");
        for (int j = 0; j < characterPool.size(); j++) {
            Character character = characterPool.get(j);
            // Display character details in formatted manner
            System.out.println(String.format("%-30s %15s %15s %15s %15s", 
            (j + 1) + ". " + character.name, 
            "HP: " + String.format("%-3d", character.healthPoints), 
            "STR: " + String.format("%-3d", character.strength), 
            "DEF: " + String.format("%-3d", character.defense), 
            "INIT: " + String.format("%-3d", character.initiative)));
        }
    
        int charactersToSelect = 3;
        while (team.membersCount() < charactersToSelect) { // Loop until team is full
            System.out.print("\nSelect character " + (team.membersCount() + 1) + ": ");
            int choice = Integer.parseInt(scanner.nextLine().trim()) - 1; // Get user selection
    
            if (choice >= 0 && choice < characterPool.size()) {
                Character selectedCharacter = characterPool.get(choice);
                // Create a new instance of selected character
                Character newCharacter = new Character(selectedCharacter.name, 
                                                        selectedCharacter.healthPoints, 
                                                        selectedCharacter.strength, 
                                                        selectedCharacter.defense, 
                                                        selectedCharacter.initiative);
                team.addMember(newCharacter); // Add new character to team
                System.out.println(GREEN + newCharacter.name + " added to your team." + RESET);
    
                // Log the character selection
                try {
                    gameLog.logAction("Player " + (team == player1Team ? "1" : "2") + " selected " + newCharacter.name + ".");
                } catch (IOException e) {
                    System.err.println("Failed to log selection: " + e.getMessage());
                }
            } else {
                System.out.println(RED + "Invalid selection. Try again." + RESET);
            }
    
            System.out.println("\nYour current team:");
            team.displayMembers(); // Display current team members
        }
    }    

    /*
     * Method to display both players' teams
     */
    public void displayTeams() {
        System.out.println("\n" + GREEN + "Player 1 Team:" + RESET);
        player1Team.displayMembers();

        System.out.println("\n" + RED + "Player 2 Team:" + RESET);
        player2Team.displayMembers();
    }

    /*
     * Method to initialise character pool with predefined characters
     */
    public void initialiseCharacterPool() {
        characterPool.add(new Character("Student", 20, 8, 8, 20));
        characterPool.add(new Character("Police Officer", 15, 16, 15, 6));
        characterPool.add(new Character("Rebel", 10, 10, 25, 12));
        characterPool.add(new Character("Trader", 12, 11, 12, 11));
        characterPool.add(new Character("Motivational Speaker", 25, 5, 5, 25));
        characterPool.add(new Character("Athlete", 20, 25, 10, 10));
    }

    /*
     * Method to print storyline before game starts
     */
    public void printStoryline() {
        System.out.println(YELLOW + "=== The Storyline Begins ===" + RESET);
        System.out.println("You and your rival are about to battle for glory!");
        System.out.println("Prepare your team and fight to become the champion of the Urban Showdown RPG!");
    }

    /*
     * Method to start the battle between the two teams
     */
    public void start() {
        System.out.println("\nThe battle begins!\n");
    
        // Calculate initiative for both teams
        int player1Initiative = calculateTeamInitiative(player1Team);
        int player2Initiative = calculateTeamInitiative(player2Team);
    
        Team firstTeam, secondTeam;
        String firstPlayer, secondPlayer;
    
        // Determine which player goes first based on initiative
        if (player1Initiative > player2Initiative) {
            firstTeam = player1Team;
            secondTeam = player2Team;
            firstPlayer = "Player 1";
            secondPlayer = "Player 2";
        } else {
            firstTeam = player2Team;
            secondTeam = player1Team;
            firstPlayer = "Player 2";
            secondPlayer = "Player 1";
        }
    
        int round = 1; // Initialise round counter
        // Continue battle until one team is defeated
        while (!player1Team.Defeated() && !player2Team.Defeated()) {
            printRoundAnnouncement(round); // Announce current round
            displayTeams();
    
            // Player 1 turn
            for (Character character : firstTeam.getAliveMembers()) {
                if (character.alive) {
                    selectedAction(character, firstPlayer);
                    if (player2Team.Defeated()) { // Check if Player 2 team defeated
                        declareWinner(firstPlayer);
                        returnToStartScreen(); 
                        return; // Exit the method after returning to start screen
                    }
                }
            }
    
            // Player 2 turn
            for (Character character : secondTeam.getAliveMembers()) {
                if (character.alive) {
                    selectedAction(character, secondPlayer);
                    if (player1Team.Defeated()) { // Check if Player 1 team defeated
                        declareWinner(secondPlayer);
                        returnToStartScreen();
                        return; // Exit the method after returning to start screen
                    }
                }
            }
    
            round++;
        }
    }

   /*
    * Method to declare winner of game
    * Displays victory message and logs result to game log
    */
    private void declareWinner(String winner) {
        System.out.println(GREEN + "\n" + winner + " wins!" + RESET);
        try {
            gameLog.logAction(winner + " wins the game!");
        } catch (IOException e) {
            System.err.println("Failed to log the game result: " + e.getMessage()); // Handle logging error
        }
    }
    
   /*
    * Method resets both teams and shows start screen again
    */
    private void returnToStartScreen() {
        System.out.println(YELLOW + "\nReturning to the home screen..." + RESET);
        
        // Reset teams for both players
        this.player1Team = new Team();
        this.player2Team = new Team();
    
        // Re-display start screen after resetting teams
        showStartScreen(); 
    }  

   /*
    * Method to print round announcement and current round number
    */
    private void printRoundAnnouncement(int round) {
        System.out.println(YELLOW + "\n=== ROUND " + round + " ===" + RESET);
        System.out.println("-----------------------------");
    }

   /*
    * Method to calculate total initiative for a team
    */
    public int calculateTeamInitiative(Team team) {
        int totalInitiative = 0;
        for (Character character : team.getAliveMembers()) {
            totalInitiative += character.initiative; // Accumulate initiative points
        }
        return totalInitiative;
    }

  /*
   * Method allows player to choose action and handles it
   */
    public void selectedAction(Character character, String player) {
        System.out.println(YELLOW + "\nIt's " + player + "'s turn with " + character.name + "." + RESET);
        System.out.println("Choose an action (attack/defend): ");
        String action = scanner.nextLine().trim().toLowerCase(); // Get user input

        if (action.equals("attack")) {
            System.out.println("Choose an opponent to attack:");

            List<Character> aliveOpponents = (player.equals("Player 1")) ? player2Team.getAliveMembers() : player1Team.getAliveMembers();

            // Display available opponents
            for (int i = 0; i < aliveOpponents.size(); i++) {
                Character opponent = aliveOpponents.get(i);
                System.out.println((i + 1) + ". " + opponent.name + " (HP: " + opponent.healthPoints + ")");
            }

            int opponentChoice = Integer.parseInt(scanner.nextLine().trim()) - 1;
            if (opponentChoice >= 0 && opponentChoice < aliveOpponents.size()) {
                Character opponent = aliveOpponents.get(opponentChoice); // Select opponent
                character.attack(opponent);

                // Log attack action
                try {
                    gameLog.logAction(character.name + " attacked " + opponent.name + ".");
                } catch (IOException e) {
                    System.err.println("Failed to log action: " + e.getMessage()); // Handle logging error
                }
            } else {
                System.out.println(RED + "Invalid selection." + RESET); // Handle invalid target selection
            }
        } else if (action.equals("defend")) {
            character.defend(); // Activate defense
            try {
                gameLog.logAction(character.name + " is defending this turn.");
            } catch (IOException e) {
                System.err.println("Failed to log action: " + e.getMessage()); // Handle logging error
            }
        } else {
            System.out.println(RED + "Invalid action. Choose attack or defend." + RESET); // Handle invalid action
        }
    }

   /*
    * Main method to run the RPG game
    */
    public static void main(String[] args) {
        RpgGame game = new RpgGame(); // Create new game instance
        game.showStartScreen();
    }
}