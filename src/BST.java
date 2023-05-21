import java.util.ArrayList;
import java.util.List;

public class BST<K extends Comparable<K>, V> {
    private Node root;
    private int size;

    private class Node {
        private K key;
        private V value;
        private Node left;
        private Node right;

        public Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public void put(K key, V value) {
        root = putRecursive(root, key, value);
        size++;
    }

    private Node putRecursive(Node node, K key, V value) {
        if (node == null) {
            return new Node(key, value);
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = putRecursive(node.left, key, value);
        } else if (cmp > 0) {
            node.right = putRecursive(node.right, key, value);
        } else {
            node.value = value;
        }

        return node;
    }

    public V get(K key) {
        Node node = getRecursive(root, key);
        return node != null ? node.value : null;
    }

    private Node getRecursive(Node node, K key) {
        if (node == null || key.equals(node.key)) {
            return node;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            return getRecursive(node.left, key);
        } else {
            return getRecursive(node.right, key);
        }
    }

    public void delete(K key) {
        root = deleteRecursive(root, key);
        size--;
    }

    private Node deleteRecursive(Node node, K key) {
        if (node == null) {
            return null;
        }

        int cmp = key.compareTo(node.key);
        if (cmp < 0) {
            node.left = deleteRecursive(node.left, key);
        } else if (cmp > 0) {
            node.right = deleteRecursive(node.right, key);
        } else {
            if (node.left == null) {
                return node.right;
            } else if (node.right == null) {
                return node.left;
            }

            Node minNode = findMin(node.right);
            node.key = minNode.key;
            node.value = minNode.value;
            node.right = deleteRecursive(node.right, minNode.key);
        }

        return node;
    }

    private Node findMin(Node node) {
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    public Iterable<K> iterator() {
        List<K> keys = new ArrayList<>();
        inorderTraversal(root, keys);
        return keys;
    }

    private void inorderTraversal(Node node, List<K> keys) {
        if (node != null) {
            inorderTraversal(node.left, keys);
            keys.add(node.key);
            inorderTraversal(node.right, keys);
        }
    }

    public int size() {
        return size;
    }
}
