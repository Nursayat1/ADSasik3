import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class BST<K extends Comparable<K>, V> implements Iterable<BST.KeyValuePair<K, V>> {
    private Node root;
    private int size;

    private class Node {
        K key;
        V val;
        Node left, right;

        Node(K key, V val) {
            this.key = key;
            this.val = val;
        }
    }

    // Record to access both key and value during iteration
    public record KeyValuePair<K, V>(K key, V value) {}

    public void put(K key, V val) {
        root = put(root, key, val);
    }

    private Node put(Node x, K key, V val) {
        if (x == null) {
            size++;
            return new Node(key, val);
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = put(x.left, key, val);
        else if (cmp > 0) x.right = put(x.right, key, val);
        else x.val = val;

        return x;
    }

    public V get(K key) {
        Node x = root;
        while (x != null) {
            int cmp = key.compareTo(x.key);
            if (cmp < 0) x = x.left;
            else if (cmp > 0) x = x.right;
            else return x.val;
        }
        return null;
    }

    public void delete(K key) {
        root = delete(root, key);
    }

    private Node delete(Node x, K key) {
        if (x == null) return null;

        int cmp = key.compareTo(x.key);
        if (cmp < 0) x.left = delete(x.left, key);
        else if (cmp > 0) x.right = delete(x.right, key);
        else {
            // Case 1 & 2: Node has 0 or 1 child
            if (x.left == null) {
                size--;
                return x.right;
            }
            if (x.right == null) {
                size--;
                return x.left;
            }

            // Case 3: Node has 2 children
            Node minNode = findMin(x.right);
            x.key = minNode.key;
            x.val = minNode.val;
            x.right = delete(x.right, minNode.key);
        }
        return x;
    }

    private Node findMin(Node x) {
        while (x.left != null) {
            x = x.left;
        }
        return x;
    }

    public int size() {
        return size;
    }

    // Returns an iterator that traverses the BST in-order
    @Override
    public Iterator<KeyValuePair<K, V>> iterator() {
        List<KeyValuePair<K, V>> inOrderList = new ArrayList<>();
        inOrderTraversal(root, inOrderList);
        return inOrderList.iterator();
    }

    // In-order traversal: left subtree, node, right subtree
    private void inOrderTraversal(Node x, List<KeyValuePair<K, V>> list) {
        if (x == null) return;
        inOrderTraversal(x.left, list);
        list.add(new KeyValuePair<>(x.key, x.val));
        inOrderTraversal(x.right, list);
    }
}