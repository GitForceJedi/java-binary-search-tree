import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

class BinarySearchTreeTest {
    private BinarySearchTree bst;
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;

    @BeforeEach
    void setUp() {
        bst = new BinarySearchTree();
        System.setOut(new PrintStream(outputStream));  // Redirect System.out to capture output

        // Create the balanced BST with {1, 2, 3, 4, 5, 6, 7}
        int[] data = {1, 2, 3, 4, 5, 6, 7};
        bst.root = bst.createBinarySearchTree(data, 0, data.length - 1);
    }

    @Test
    void testPrintInOrder() {
        bst.printInOrder(bst.root);
        assertEquals("[1] [2] [3] [4] [5] [6] [7]", getOutput());
    }

    @Test
    void testPrintPreOrder() {
        bst.printPreOrder(bst.root);
        assertEquals("[4] [2] [1] [3] [6] [5] [7]", getOutput());
    }

    @Test
    void testPrintPostOrder() {
        bst.printPostOrder(bst.root);
        assertEquals("[1] [3] [2] [5] [7] [6] [4]", getOutput());
    }

    @Test
    void testPrintReverseOrder() {
        bst.printReverseOrder(bst.root);
        assertEquals("[7] [6] [5] [4] [3] [2] [1]", getOutput());
    }

    // Utility method to capture printed output and normalize spacing
    private String getOutput() {
        System.out.flush();
        String output = outputStream.toString().trim();
        outputStream.reset();  // Clear the stream for the next test
        return output.replaceAll("\\s+", " ");  // Normalize spaces
    }
}
