package bstmap;

import java.util.*;

public class RedBlackBSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {
    private Node root;

    public static final boolean RED = true;
    public static final boolean BLACK = false;

    private class Node {
        private K key;
        private V val;
        private Node left, right;
        private int N;
        private boolean color;

        public Node(K key, V val, int N, boolean color) {
            this.key = key;
            this.val = val;
            this.N = N;
            this.color = color;
        }
    }

    @Override
    /** Removes all of the mappings from this map. */
    public void clear() {
        root = null;
    }

    @Override
    /* Returns true if this map contains a mapping for the specified key. */
    public boolean containsKey(K key) {
        return getNode(root, key) != null;
    }

    @Override
    /* Returns the value to which the specified key is mapped, or null if this
     * map contains no mapping for the key.
     */
    public V get(K key) {
        Node targetNode = getNode(root, key);
        if (targetNode == null)
            return null;
        return targetNode.val;
    }

    private Node getNode(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp > 0) {
            return getNode(x.right, key);  // Remember to add the return statement!
        } else if (cmp < 0) {
            return getNode(x.left, key);
        } else {
            return x;
        }
    }

    @Override
    /* Returns the number of key-value mappings in this map. */
    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null) {
            return 0;
        }
        return x.N;
    }

    @Override
    /* Associates the specified value with the specified key in this map. */
    public void put(K key, V value) {
        root = put(root, key, value);
        root.color = BLACK;
    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    private Node rotateLeft(Node x) {
        Node h = x.right;
        x.right = h.left;
        h.left = x;
        h.color = x.color;
        x.color = RED;
        x.N = size(x.left) + size(x.right) + 1;
        return h;
    }

    private Node rotateRight(Node x) {
        Node h = x.left;
        x.left = h.right;
        h.right = x;
        h.color = x.color;
        x.color = RED;
        x.N = size(x.left) + size(x.right) + 1;
        return h;
    }

    private void flipColors(Node x) {
        x.color = !x.color;
        x.left.color = !x.left.color;
        x.right.color = !x.right.color;  // Will these be possible?
    }

    private Node put(Node x, K key, V val) {
        if (x == null) {
            return new Node(key, val, 1, RED);  // Should we start as a red node?
        }

        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            x.val = val;
        } else if (cmp < 0) {
            x.left  = put(x.left, key, val);
        } else {
            x.right = put(x.right, key, val);
        }

        // Test is the B-tree still satisfies all conditions
        if (isRed(x.right) && (!isRed(x.left))) {
            x = rotateLeft(x);
        }
        if (isRed(x.left) && isRed(x.left.left)) {
            x = rotateRight(x);
        }
        if (isRed(x.right) && isRed(x.left)) {
            flipColors(x);
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet() {
        Set<K> keySet = new HashSet<K>();
        findKey(keySet, root);
        return keySet;
    }

    private void findKey(Set<K> keySet, Node x) {
        if (x == null) {
            return;
        }
        if (x.left != null) {
            findKey(keySet, x.left);
        }
        keySet.add(x.key);
        if (x.right != null) {
            findKey(keySet, x.right);
        }
    }

    public void printInOrder() {
        printKeyVal(root);
    }

    private void printKeyVal(Node x) {
        if (x == null) {
            return;
        }
        if (x.left != null) {
            printKeyVal(x.left);
        }
        System.out.println(x.key + " : " + x.val);
        if (x.right != null) {
            printKeyVal(x.right);
        }
    }

    @Override
    /* Removes the mapping for the specified key from this map if present.
     * Not required for Lab 7. If you don't implement this, throw an
     * UnsupportedOperationException. */
    public V remove(K key) {
        throw new UnsupportedOperationException();
    }

    @Override
    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    private Node removeMin(Node x) {
        throw new UnsupportedOperationException();
    }

    private Node remove(Node x, K key, V value) {
        // TODO: This is just too complicated!!!
        // Hold it for the moment...
        /*if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            remove(x.left, key, value);
        } else if (cmp > 0) {
            remove(x.right, key, value);
        } else {
            // x now is the node to be removed
            if (x.left == null) {
                return x.right;
            }
            if (x.right == null) {
                return x.left;
            }

            // Find minimum value of right tree of x
            Node successor = x.right;
            while (successor.left != null) {
                successor = successor.left;
            }

            Node h = x;
            x = successor;
            x.left = h.left;
            x.right = removeMin(h.right);
            x.N = size(x.left) + size(x.right) + 1;
        }*/
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }
}
