import java.util.Scanner;
public class BinarySearchTree {
    // Node class with the data and left/right child nodes
    // This represents 1 element within the tree
    class Node {
        int data;
        Node leftNode;
        Node rightNode;

        // Constructor for Node
        Node(int data) {
            this.data = data;
            leftNode = null;
            rightNode = null;
        }
    }

    // Establishing the root node
    Node root;

    // The Constructor method for the class
    public BinarySearchTree() {
        root = null;
    }

    // Method to insert values into the search tree
    // @param key = the value / node to insert into the tree
    public void insertNode(int key) {
        // If no current root, set target value to the top/root node
        if (root == null) {
            root = new Node(key);
            return;
        }

        // Set starting (currentNode) to point to root
        Node currentNode = root;

        // Setting to while true will keep code running until a break is hit
        // This will allow traversal of the tree until the proper insertion point is found
            // Each time the While statement runs, it will compare the value to each child node
            // It will move in the appropriate direction by comparing values and resetting currentNode
            // Until null node is found, This will be the place to enter the new node
        boolean endWhileLoop = false;
        while (!endWhileLoop) {
            // If Key is less than currentNode, check its leftNode, if it is null create the new node here
            if (key < currentNode.data) {
                if (currentNode.leftNode == null) {
                    // Assignment of node
                    currentNode.leftNode = new Node(key);
                    endWhileLoop = true;
                }
                // If leftNode is not null, make the currentNode the leftNode
                // While statement will run again until leftNode or rightNode is found null, in such properly placing the node to insert
                currentNode = currentNode.leftNode;

            // Else If Key is greater than currentNode, check its rightNode, if it is null create the new node here
            } else if (key > currentNode.data) {
                if (currentNode.rightNode == null) {
                    // Assignment of node
                    currentNode.rightNode = new Node(key);
                    endWhileLoop = true;
                }
                // If rightNode is not null, make the currentNode the rightNode
                // While statement will run again until leftNode or rightNode is found null, in such properly placing the node to insert
                currentNode = currentNode.rightNode;
            } else {
                // If key already exists, do nothing (break out of while loop)
                endWhileLoop = true;
            }
        }
    }


    // Delete a node
    // Helper Method for deleting a node.
    // Allows switch statement to easily call functionality with 1 single argument
    // @param key = the value / node to delete
    // Since Node is a object, it is a reference, this is needed
    public void delete(int key) {
        //call actual function to delete a node
        root = deleteNodeRecursive(root, key);  // Pass the root node into the recursive function
    }

    // Method to delete a Node from the tree
    // @param node = current node in the tree
    // @param key = target node to delete
    public Node deleteNodeRecursive(Node node, int key) {
        if (node == null) return node;  // Base case: node not found

        // Move left / right until reaching the targeted node
        // Recursively references and replaces the 'root' node with appropriate value
        if (key < node.data) {
            node.leftNode = deleteNodeRecursive(node.leftNode, key);  // Recur left
        } else if (key > node.data) {
            node.rightNode = deleteNodeRecursive(node.rightNode, key);  // Recur right
        } else {
            // Once at the target, see how many/which child nodes it has
            // If node has no child nodes, remove the node
            if (node.rightNode == null && node.leftNode == null) {
                return null;
            // If Node has only left node, directly replace node with left node
            } else if (node.leftNode != null && node.rightNode == null) {
                return node.leftNode;
            // If Node has only right nox
            } else if (node.leftNode == null) {
                return node.rightNode;
            } else {
                // If Node has two children
                // Set current node to in-order successor (smallest value in the right node's subtree)
                node.data = getInOrderSuccessor(node.rightNode);
                // Call deleteNodeRecursive once more to delete the in-order successor
                node.rightNode = deleteNodeRecursive(node.rightNode, node.data);
            }
        }
        return node;
    }

    // MinValue function
    // Uses a while loop to access the leftNode until it reaches a node where the left child
    // is null. This represents the lowest value since it has no left child. This value is returned
    // @param node = node to get in order successor of
    public int getInOrderSuccessor(Node node) {
        int inOrderSuccessor = node.data;
        if (node.leftNode == null) {
            return inOrderSuccessor;
        }
        // Traverse until no leftNode is present, representing the in order successor value
        while (node.leftNode != null) {
            node = node.leftNode;
            // Upon arriving at the final node, this will attach the in order successor value
            inOrderSuccessor = node.data;
        }
        return inOrderSuccessor;

    }
        // If node is null, return inOrderSuccessor from top
    // TRAVERSALS

    // The positioning of the print function and the recursive calls governs the order
    // This is due to how the recursive function executes ("unfolds")

    // Recursive function to print in order
    // @param node = node to start the printing on, usually the root
    public void printInOrder(Node node) {
        if (node != null) {
            // Recusively call left before the print line
                // This will print the entire left subtree in order
            printInOrder(node.leftNode);

            // Print the current node
            System.out.print("[" + node.data + "] ");

            // Recusively call right after the print line
                // This will print the entire right subtree in order
            printInOrder(node.rightNode);
        }
    }

    // NOTE: To show my understanding of the concepts, I have decided to expand the program
    // I have added a "Reverse Order" Traversal
    // Recursive function to print in order
    // @param node = node to start the printing on, usually the root
    public void printReverseOrder(Node node) {
        if (node != null) {
            // Recursively call right subtree before the print line
            // This will print the entire right subtree first
            printReverseOrder(node.rightNode);

            // Print the current node
            System.out.print("[" + node.data + "] ");

            // Recursively call left subtree after the print line
            // This will print the entire left subtree
            printReverseOrder(node.leftNode);
        }
    }

    // Recursive function to print the pre order
    // @param node = node to start the printing on, usually the root
    public void printPreOrder(Node node) {
        if (node != null) {
            System.out.print("[" + node.data + "] ");
            // Recusively call on left & right after the print line
            // This will print the root, follow by left subtree in order, then right subtree in order directly after
            printPreOrder(node.leftNode);
            printPreOrder(node.rightNode);
        }
    }

    // Recursive function to print the post order
    // @param node = node to start the printing on, usually the root
    public void printPostOrder(Node node) {
        if (node != null) {
            // Recusively call on left & right before the print line
            // This will print the entire left subtree, followed by right subtree, followed by the root
            printPostOrder(node.leftNode);
            printPostOrder(node.rightNode);
            System.out.print("[" + node.data + "] ");
        }
    }

    // Actual method responsible for creating the Balanced Search Tree
    // @param start = starting index, not the actual value
    // @param end = ending index, not the value
    private Node createBinarySearchTree(int[] data, int start, int end) {
        if (start > end) return null;

        int mid = (start + end) / 2;
        Node node = new Node(data[mid]);
        //Calls child nodes with recursion
        //Switches mid / end for lower half and upper half respectively
        // mid - 1 becomes the end for the left, mid + 1 becomes the start for the right
        //This way splitting the range at the actual mid. Performed recusively it constructs the tree.
        node.leftNode = createBinarySearchTree(data, start, mid - 1);
        node.rightNode = createBinarySearchTree(data, mid + 1, end);
        return node;
    }

    //MAIN METHOD
    public static void main(String[] args) {
        // Initialize Binary Search Tree
        BinarySearchTree binaryTree = new BinarySearchTree();
        // Initialize Scanner
        Scanner scanner = new Scanner(System.in);

        // NAVIGATION MENU
        // Set exitProgram to false, set to true when exiting program
        // While loop will run while exitProgram is false, allowing continuation
        boolean exitProgram = false;
        while (!exitProgram) {
            System.out.println("\nMenu:");
            System.out.println("1) Create a binary search tree");
            System.out.println("2) Add a node");
            System.out.println("3) Delete a node");
            System.out.println("4) Print tree In Order");
            System.out.println("5) Print tree in Pre Order");
            System.out.println("6) Print tree in Post Order");
            System.out.println("7) Print tree in Reverse Order\n");
            System.out.println("8) Exit program");
            System.out.print("Enter your choice:\n");
            int choice = scanner.nextInt();

            //SWITCH CASE, For NAVIGATION MENU
            //For Program Choices. Calls upon respective function based on selection.
            switch (choice) {
                //Case to initiate the creation of the Binary Search Tree
                //Data is already pre-set in the array
                case 1:
                    int[] data = {1, 2, 3, 4, 5, 6, 7};
                    binaryTree.root = binaryTree.createBinarySearchTree(data, 0, data.length - 1);
                    System.out.println("\nBalanced binary search tree created.");
                    break;

                //Case to Add a Mode to the tree
                //Prompts user to enter what value to add
                case 2:
                    System.out.print("\nEnter the value to add: ");
                    int value = scanner.nextInt();
                    binaryTree.insertNode(value);
                    System.out.println("\nNode added with value "+ value);
                    break;

                //Case to Delete a Value
                //Prompts user to enter which value to delete
                case 3:
                    System.out.print("\nEnter the value to delete: ");
                    int deleteValue = scanner.nextInt();
                    binaryTree.delete(deleteValue);
                    System.out.println("\nNode with value " + deleteValue + " deleted.");
                    break;

                //Case to print In Order traversal
                case 4:
                    System.out.println("\nIn Order traversal: ");
                    binaryTree.printInOrder(binaryTree.root);
                    System.out.println();
                    break;

                //Case to print Pre Order traversal
                case 5:
                    System.out.println("\nPre Order traversal: ");
                    binaryTree.printPreOrder(binaryTree.root);
                    System.out.println();
                    break;

                //Case to print Post Order traversal
                case 6:
                    System.out.println("\nPost Order traversal: ");
                    binaryTree.printPostOrder(binaryTree.root);
                    System.out.println();
                    break;

                //Case to print Post Order traversal
                case 7:
                    System.out.println("\nReverse Order traversal: ");
                    binaryTree.printReverseOrder(binaryTree.root);
                    System.out.println();
                    break;

                //Case to Exit and close out of the program
                case 8:
                    System.out.println("\nExiting program. Thanks for using Binary Search Tree Program");
                    exitProgram = true;
                    break;

                //Default case to be printed for invalid selections.
                default:
                    System.out.println("\nInvalid selection. Please choose from options 1-6. Use 7 to exit program.");
            }
        }
    }
}