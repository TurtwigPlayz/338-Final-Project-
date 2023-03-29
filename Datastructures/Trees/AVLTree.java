package Datastructures.Trees;

public class AVLTree extends BST {
    public AVLTree() {
        super();
    }

    public AVLTree(int val) {
        super(val);
    }

    public AVLTree(TNode obj) {
        super(obj);
        balanceTree(root);
    }

    private int height(TNode node) {
        if (node == null) {
            return 0;
        }
        return Math.max(height(node.left), height(node.right)) + 1;
    }

    private int getBalance(TNode node) {
        if (node == null) {
            return 0;
        }
        return height(node.left) - height(node.right);
    }

    private TNode rotateRight(TNode y) {
        TNode x = y.left;
        TNode z = x.right;

        x.right = y;
        y.left = z;

        y.balance = getBalance(y);
        x.balance = getBalance(x);

        return x;
    }

    private TNode rotateLeft(TNode x) {
        TNode y = x.right;
        TNode z = y.left;

        y.left = x;
        x.right = z;

        x.balance = getBalance(x);
        y.balance = getBalance(y);

        return y;
    }

    private TNode balanceNode(TNode node) {
        if (node == null) {
            return null;
        }

        node.balance = getBalance(node);

        if (node.balance > 1) {
            if (getBalance(node.left) < 0) {
                node.left = rotateLeft(node.left);
            }
            return rotateRight(node);
        } else if (node.balance < -1) {
            if (getBalance(node.right) > 0) {
                node.right = rotateRight(node.right);
            }
            return rotateLeft(node);
        }

        return node;
    }

    private void balanceTree(TNode node) {
        if (node != null) {
            node.left = balanceTree(node.left);
            node.right = balanceTree(node.right);
            root = balanceNode(node);
        }
    }

    @Override
    public void setRoot(TNode root) {
        super.setRoot(root);
        balanceTree(root);
    }

    @Override
    public void insert(int val) {
        super.insert(val);
        balanceTree(root);
    }

    @Override
    public void insert(TNode node) {
        super.insert(node);
        balanceTree(root);
    }
}