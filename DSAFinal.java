package treenode;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.PriorityQueue;

// Binary Tree Class
class BinaryTree {
    // Inner Node class for Binary Tree
    static class TreeNode {
        int value;
        TreeNode left, right;

        public TreeNode(int value) {
            this.value = value;
            left = right = null;
        }
    }

    TreeNode root;

    public void add(int value) {
        root = addRecursive(root, value);
    }

    private TreeNode addRecursive(TreeNode node, int value) {
        if (node == null) {
            return new TreeNode(value);
        }
        if (value < node.value) {
            node.left = addRecursive(node.left, value);
        } else if (value > node.value) {
            node.right = addRecursive(node.right, value);
        }
        return node;
    }

    // In-Order Traversal
    public java.util.List<Integer> inOrderTraversal() {
        ArrayList<Integer> result = new ArrayList<>();
        inOrderRecursive(root, result);
        return result;
    }

    private void inOrderRecursive(TreeNode node, ArrayList<Integer> result) {
        if (node != null) {
            inOrderRecursive(node.left, result);
            result.add(node.value);
            inOrderRecursive(node.right, result);
        }
    }

    // Pre-Order Traversal
    public java.util.List<Integer> preOrderTraversal() {
        ArrayList<Integer> result = new ArrayList<>();
        preOrderRecursive(root, result);
        return result;
    }

    private void preOrderRecursive(TreeNode node, ArrayList<Integer> result) {
        if (node != null) {
            result.add(node.value);
            preOrderRecursive(node.left, result);
            preOrderRecursive(node.right, result);
        }
    }

    // Post-Order Traversal
    public java.util.List<Integer> postOrderTraversal() {
        ArrayList<Integer> result = new ArrayList<>();
        postOrderRecursive(root, result);
        return result;
    }

    private void postOrderRecursive(TreeNode node, ArrayList<Integer> result) {
        if (node != null) {
            postOrderRecursive(node.left, result);
            postOrderRecursive(node.right, result);
            result.add(node.value);
        }
    }
}

// Min-Heap Class
class MinHeap {
    private final PriorityQueue<Integer> heap;

    public MinHeap() {
        heap = new PriorityQueue<>();
    }

    public void add(int value) {
        heap.add(value);
    }

    public int remove() {
        return heap.poll();
    }

    public java.util.List<Integer> getHeapElements() {
        ArrayList<Integer> sortedList = new ArrayList<>(heap);
        sortedList.sort(Integer::compareTo);
        return sortedList;
    }
}

// GUI Class
public class TreeGUI extends JFrame {
    private final BinaryTree binaryTree;
    private final MinHeap minHeap;
    private final JTextArea outputArea;

    public TreeGUI() {
        binaryTree = new BinaryTree();
        minHeap = new MinHeap();

        // GUI Setup
        setTitle("Binary Tree, BST, and Heap Interactive System");
        setSize(600, 500);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Input Panel
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new FlowLayout());
        JTextField inputField = new JTextField(10);
        JButton addTreeButton = new JButton("Add to Binary Tree");
        JButton addHeapButton = new JButton("Add to Heap");
        JButton showOrderButton = new JButton("Show Orders");
        inputPanel.add(new JLabel("Enter Value: "));
        inputPanel.add(inputField);
        inputPanel.add(addTreeButton);
        inputPanel.add(addHeapButton);
        inputPanel.add(showOrderButton);

        // Output Area
        outputArea = new JTextArea(20, 50);
        outputArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(outputArea);

        // Adding Components
        add(inputPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Action Listeners
        addTreeButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(inputField.getText());
                binaryTree.add(value);
                outputArea.append("Added " + value + " to Binary Tree\n");
                inputField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer.");
            }
        });

        addHeapButton.addActionListener(e -> {
            try {
                int value = Integer.parseInt(inputField.getText());
                minHeap.add(value);
                outputArea.append("Added " + value + " to Min-Heap\n");
                inputField.setText("");
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Please enter a valid integer.");
            }
        });

        showOrderButton.addActionListener(e -> {
            outputArea.append("\nBinary Tree (In-Order Traversal): " + binaryTree.inOrderTraversal() + "\n");
            outputArea.append("Binary Tree (Pre-Order Traversal): " + binaryTree.preOrderTraversal() + "\n");
            outputArea.append("Binary Tree (Post-Order Traversal): " + binaryTree.postOrderTraversal() + "\n");
            outputArea.append("Min-Heap (Sorted Order): " + minHeap.getHeapElements() + "\n");
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            TreeGUI gui = new TreeGUI();
            gui.setVisible(true);
        });
    }
}
