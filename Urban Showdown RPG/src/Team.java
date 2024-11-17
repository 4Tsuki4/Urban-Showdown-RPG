import java.util.ArrayList;
import java.util.List;

public class Team {
    private CharacterNode head;  // Head node of the linked list for team members
    private int memberCount;      // To keep track of the number of members

    /*
     * Constructor to initialise a new team
     * Creates an empty linked list of team members
     */
    public Team() {
        this.head = null;  // Initialise head to null
        this.memberCount = 0; // Initialise member count to 0
    }

    /*
     * Method to add a member to the team
     */
    public void addMember(Character character) {
        CharacterNode newNode = new CharacterNode(character);
        if (head == null) {
            head = newNode; // If list is empty, set head to new node
        } else {
            CharacterNode current = head;
            while (current.next != null) { // Traverse to the end of the list
                current = current.next;
            }
            current.next = newNode; // Link new node at the end
        }
        memberCount++;
    }

    /*
     * Method to retrieve a list of alive team members
     */
    public List<Character> getAliveMembers() {
        List<Character> aliveMembers = new ArrayList<>();
        CharacterNode current = head; // Start from head
        while (current != null) { // Traverse the linked list
            if (current.character.alive) {
                aliveMembers.add(current.character); // Add alive characters to the list
            }
            current = current.next; // Move to the next node
        }
        return aliveMembers;
    }

    /*
     * Method to get the count of team members
     */
    public int membersCount() {
        return memberCount; // Return total number of members
    }

    /*
     * Method to check if the team is defeated (all members dead)
     */
    public boolean Defeated() {
        CharacterNode current = head;
        while (current != null) {
            if (current.character.alive) {
                return false; // Team is not defeated if there's at least one alive member
            }
            current = current.next; // Move to the next node
        }
        return true; // All members are dead
    }

    /*
     * Recursive method to display team members
     */
    public void displayMembers() {
        displayMembers(head); // Start recursion from the head
    }

    private void displayMembers(CharacterNode node) {
        if (node == null) {
            return; // Base case: end of list
        }
        System.out.println(node.character.name + " (HP: " + node.character.healthPoints + ", Alive: " + node.character.alive + ")");
        displayMembers(node.next); // Recursive call
    }
}
