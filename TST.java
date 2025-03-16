// Node class for Ternary Search Tree
class TSTNode {
    char data; // Character stored in the node
    boolean isEndOfWord; // Flag to mark the end of a word
    TSTNode left, middle, right; // Pointers to left, middle, and right children

    // Constructor to initialize a node
    TSTNode(char data) {
        this.data = data; // Set the character
        this.isEndOfWord = false; // By default, it's not the end of a word
        this.left = this.middle = this.right = null; // Initialize all children to null
    }
}

// Ternary Search Tree class
class TernarySearchTree {
    private TSTNode root; // Root of the TST

    // Method to insert a word into the TST
    void insert(String word) {
        root = insert(root, word, 0); // Start insertion from the root
    }

    // Helper method for insertion
    private TSTNode insert(TSTNode node, String word, int index) {
        // If the current node is null, create a new node with the current character
        if (node == null) node = new TSTNode(word.charAt(index));

        char currentChar = word.charAt(index); // Get the current character from the word

        // If the current character is less than the node's character, go left
        if (currentChar < node.data) node.left = insert(node.left, word, index);

            // If the current character is greater than the node's character, go right
        else if (currentChar > node.data) node.right = insert(node.right, word, index);

            // If the current character matches the node's character
        else {
            // If there are more characters in the word, move to the middle child
            if (index + 1 < word.length()) node.middle = insert(node.middle, word, index + 1);

                // If this is the last character, mark the end of the word
            else node.isEndOfWord = true;
        }

        return node; // Return the updated node
    }

    // Method to search for a word in the TST
    boolean search(String word) {
        return search(root, word, 0); // Start searching from the root
    }

    // Helper method for searching
    private boolean search(TSTNode node, String word, int index) {
        // If the node is null, the word doesn't exist in the TST
        if (node == null) return false;

        char currentChar = word.charAt(index); // Get the current character from the word

        // If the current character is less than the node's character, search in the left subtree
        if (currentChar < node.data) return search(node.left, word, index);

            // If the current character is greater than the node's character, search in the right subtree
        else if (currentChar > node.data) return search(node.right, word, index);

            // If the current character matches the node's character
        else {
            // If this is the last character, check if it's the end of a word
            if (index + 1 == word.length()) return node.isEndOfWord;

                // If there are more characters, move to the middle child
            else return search(node.middle, word, index + 1);
        }
    }

    // Main method to test the TST
    public static void main(String[] args) {
        TernarySearchTree tst = new TernarySearchTree(); // Create a new TST

        // Insert words into the TST
        tst.insert("cat");
        tst.insert("car");
        tst.insert("bat");
        tst.insert("ball");
        tst.insert("apple");

        // Search for words and print the results
        System.out.println("Search 'cat': " + tst.search("cat")); // true
        System.out.println("Search 'car': " + tst.search("car")); // true
        System.out.println("Search 'bat': " + tst.search("bat")); // true
        System.out.println("Search 'ball': " + tst.search("ball")); // true
        System.out.println("Search 'apple': " + tst.search("apple")); // true
        System.out.println("Search 'dog': " + tst.search("dog")); // false
    }
}