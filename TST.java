class TernarySearchTree {
    private TSTNode root; // Root of the TST

    // Node class for Ternary Search Tree
    private static class TSTNode {
        char data; // Character stored in the node
        boolean isEndOfWord; // Flag to mark the end of a word
        TSTNode left, middle, right; // Pointers to left, middle, and right children

        TSTNode(char data) {
            this.data = data;
            this.isEndOfWord = false;
            this.left = this.middle = this.right = null;
        }
    }

    // Method to search for a word in the TST
    public boolean search(String word) {
        return search(root, word, 0);
    }

    // Helper method for searching
    private boolean search(TSTNode node, String word, int index) {
        // If the node is null, the word doesn't exist in the TST
        if (node == null) return false;

        char currentChar = word.charAt(index); // Get the current character

        // If the current character is less than the node's character, search in the left subtree
        if (currentChar < node.data) {
            return search(node.left, word, index);
        }
        // If the current character is greater than the node's character, search in the right subtree
        else if (currentChar > node.data) {
            return search(node.right, word, index);
        }
        // If the current character matches the node's character
        else {
            // If this is the last character, check if it's the end of a word
            if (index + 1 == word.length()) {
                return node.isEndOfWord;
            }
            // If there are more characters, move to the middle child and go to the next letter
            else {
                return search(node.middle, word, index + 1);
            }
        }
    }

    // Method to insert a word into the TST
    public void insert(String word) {
        root = insert(root, word, 0);
    }

    // Helper method for insertion
    private TSTNode insert(TSTNode node, String word, int index) {
        char currentChar = word.charAt(index); // Get the current character

        // If the node is null, create a new node with the current character
        if (node == null) {
            node = new TSTNode(currentChar);
        }

        // If the current character is less than the node's character, go left
        if (currentChar < node.data) {
            node.left = insert(node.left, word, index);
        }
        // If the current character is greater than the node's character, go right
        else if (currentChar > node.data) {
            node.right = insert(node.right, word, index);
        }
        // If the current character matches the node's character
        else {
            // If there are more characters, move to the middle child and go to the next letter
            if (index + 1 < word.length()) {
                node.middle = insert(node.middle, word, index + 1);
            }
            // If this is the last character, mark the end of the word
            else {
                node.isEndOfWord = true;
            }
        }

        return node; // Return the updated node
    }

    // Method to remove a word from the TST
    public void remove(String word) {
        root = remove(root, word, 0);
    }

    // Helper method for removal
    private TSTNode remove(TSTNode node, String word, int index) {
        // If the node is null, the word doesn't exist in the TST
        if (node == null) return null;

        char currentChar = word.charAt(index); // Get the current character

        // If the current character is less than the node's character, go left
        if (currentChar < node.data) {
            node.left = remove(node.left, word, index);
        }
        // If the current character is greater than the node's character, go right
        else if (currentChar > node.data) {
            node.right = remove(node.right, word, index);
        }
        // If the current character matches the node's character
        else {
            // If this is the last character, unmark the end of the word
            if (index + 1 == word.length()) {
                node.isEndOfWord = false;
            }
            // If there are more characters, move to the middle child and go to the next letter
            else {
                node.middle = remove(node.middle, word, index + 1);
            }

            // If the node has no children and is not the end of a word, delete it
            if (node.left == null && node.middle == null && node.right == null && !node.isEndOfWord) {
                node = null;
            }
        }

        return node; // Return the updated node
    }

    // Main method to test the TST
    public static void main(String[] args) {
        TernarySearchTree tst = new TernarySearchTree();

        // Insert words into the TST
        tst.insert("cat");
        tst.insert("car");
        tst.insert("bat");
        tst.insert("ball");
        tst.insert("apple");

        // Search for words before removal
        System.out.println("Search 'cat' before removal: " + tst.search("cat")); // true
        System.out.println("Search 'car' before removal: " + tst.search("car")); // true
        System.out.println("Search 'bat' before removal: " + tst.search("bat")); // true
        System.out.println("Search 'ball' before removal: " + tst.search("ball")); // true
        System.out.println("Search 'apple' before removal: " + tst.search("apple")); // true

        // Remove words
        tst.remove("cat");
        tst.remove("bat");
        tst.remove("apple");

        // Search for words after removal
        System.out.println("Search 'cat' after removal: " + tst.search("cat")); // false
        System.out.println("Search 'car' after removal: " + tst.search("car")); // false
        System.out.println("Search 'apple' after removal: " + tst.search("apple")); // false
        System.out.println("Search 'bat' after removal: " + tst.search("bat")); // true
        System.out.println("Search 'ball' after removal: " + tst.search("ball")); // true
    }
}