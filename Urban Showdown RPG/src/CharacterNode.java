public class CharacterNode {
    Character character; // Character stored in this node
    CharacterNode next; 

    // Constructor to initialise node with a character
    public CharacterNode(Character character) {
        this.character = character;
        this.next = null; 
    }
}