package Datastructures.Trees;

import java.util.LinkedList;
import java.util.Queue;

public class BST {
    private TNode root;

    // Default constructor
    public BST() {
        this.root = null;
    }

    // Overload constructor with a root value
    public BST(int val) {
        this.root = new TNode(val, 0, null, null, null);
    }

    // Overload constructor with a root node
    public BST(TNode obj) {
        this.root = obj;
    }

    // Getters and setters for root
    public TNode getRoot() {
        return this.root;
    }

    public void setRoot(TNode root) {
        this.root = root;
    }

    // Inserts a new node with data val to the tree
    public void insert(int val) {
        TNode newNode = new TNode(val, 0, null, null, null);

        if (this.root == null) {
            this.root = newNode;
        } else {
            TNode current = this.root;
            TNode parent;

            while (true) {
                parent = current;

                if (val < current.getData()) {
                    current = current.getLeft();

                    if (current == null) {
                        parent.setLeft(newNode);
                        newNode.setParent(parent);
                        break;
                    }
                } else {
                    current = current.getRight();

                    if (current == null) {
                        parent.setRight(newNode);
                        newNode.setParent(parent);
                        break;
                    }
                }
            }
        }
    }

    // Inserts the node passed as argument into the tree
    public void insert(TNode node) {
        if (this.root == null) {
            this.root = node;
        } else {
            TNode current = this.root;
            TNode parent;

            while (true) {
                parent = current;

                if (node.getData() < current.getData()) {
                    current = current.getLeft();

                    if (current == null) {
                        parent.setLeft(node);
                        node.setParent(parent);
                        break;
                    }
                } else {
                    current = current.getRight();

                    if (current == null) {
                        parent.setRight(node);
                        node.setParent(parent);
                        break;
                    }
                }
            }
        }
    }

    // Deletes the node with data val from the tree
    public void Delete(int val) {
        root = delete(root, val);
    }
    
    private TNode delete(TNode node, int val) {
        // If the node is null, return null (value not found)
        if (node == null) {
            System.out.println(val + " not found in the tree.");
            return null;
        }
    
        // If the value to be deleted is less than the node's value,
        // go left
        if (val < node.getData()) {
            node.setLeft(delete(node.getLeft(), val));
        }
        // If the value to be deleted is greater than the node's value,
        // go right
        else if (val > node.getData()) {
            node.setRight(delete(node.getRight(), val));
        }
        // If the value to be deleted is equal to the node's value,
        // delete the node
        else {
            // Case 1: Node has no children
            if (node.getLeft() == null && node.getRight() == null) {
                return null;
            }
            // Case 2: Node has one child
            else if (node.getLeft() == null || node.getRight() == null) {
                TNode temp = node.getLeft();
                if (temp == null) {
                    temp = node.getRight();
                }
                return temp;
            }
            // Case 3: Node has two children
            else {
                TNode temp = findMin(node.getRight());
                node.setData(temp.getData());
                node.setRight(delete(node.getRight(), temp.getData()));
            }
        }
        return node;
    }
    
    // Helper method to find the minimum value in a sub-tree
    private TNode findMin(TNode node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }

    public TNode Search(int val) {
        return search(root, val);
    }
    
    private TNode search(TNode node, int val) {
        // If the node is null or the value is found, return the node
        if (node == null || node.getData() == val) {
            return node;
        }
        
        // If the value to be searched is less than the node's value,
        // search in the left subtree
        if (val < node.getData()) {
            return search(node.getLeft(), val);
        }
        // If the value to be searched is greater than the node's value,
        // search in the right subtree
        else {
            return search(node.getRight(), val);
        }
    }
    public void printInOrder() {
        inOrderTraversal(root);
    }
    
    private void inOrderTraversal(TNode node) {
        if (node != null) {
            // Traverse the left subtree
            inOrderTraversal(node.getLeft());
    
            // Visit the node
            System.out.print(node.getData() + " ");
    
            // Traverse the right subtree
            inOrderTraversal(node.getRight());
        }
    }
    public void printBF() {
        if (root == null) {
            return;
        }
    
        Queue<TNode> queue = new LinkedList<>();
        queue.add(root);
    
        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            for (int i = 0; i < levelSize; i++) {
                TNode node = queue.poll();
                System.out.print(node.getData() + " ");
                if (node.getLeft() != null) {
                    queue.add(node.getLeft());
                }
                if (node.getRight() != null) {
                    queue.add(node.getRight());
                }
            }
            System.out.println();
        }
    }
}
