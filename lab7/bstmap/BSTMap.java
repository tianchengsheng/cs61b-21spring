package bstmap;

import java.util.*;

public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private Node root;

    private class Node {
        private K key;
        private V val;
        private Node left, right;
        private int N;

        public Node(K key, V val, int N) {
            this.key = key;
            this.val = val;
            this.N = N;
        }
    }

    @Override
    /* Removes all of the mappings from this map. */
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
        Node h = getNode(root, key);
        if (h == null) {
            return null;
        }
        return h.val;
    }

    private Node getNode(Node x, K key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return getNode(x.left, key);
        } else if (cmp > 0) {
            return getNode(x.right, key);
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
    }

    private Node put(Node x, K key, V val) {
        if (x == null) {
            return new Node(key, val, 1);
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            x.val = val;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    /* Returns a Set view of the keys contained in this map. Not required for Lab 7.
     * If you don't implement this, throw an UnsupportedOperationException. */
    public Set<K> keySet() {
        Queue<K> keyQueue = new LinkedList<>();
        enqueueKey(keyQueue, root);
        return new HashSet<>(keyQueue);
    }

    public void enqueueKey(Queue<K> keyQueue, Node x) {
        if (x.left != null) {
            enqueueKey(keyQueue, x.left);
        }
        keyQueue.offer(x.key);
        if (x.right != null) {
            enqueueKey(keyQueue, x.right);
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
        Node targetKey = getNode(root, key);
        if (targetKey == null) {
            return null;
        }
        root = remove(root, key);
        return targetKey.val;
    }

    @Override
    /* Removes the entry for the specified key only if it is currently mapped to
     * the specified value. Not required for Lab 7. If you don't implement this,
     * throw an UnsupportedOperationException.*/
    public V remove(K key, V value) {
        Node targetKey = getNode(root, key);
        if (targetKey == null || targetKey.val != value) {
            return null;
        }
        root = remove(root, key);
        return targetKey.val;
    }

    private Node remove(Node x, K key) {
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = remove(x.left, key);
        } else if (cmp > 0) {
            x.right = remove(x.right, key);
        } else {
            // x now is the node to be deleted
            if (x.left == null) {
                return x.right;
            }
            if (x.right == null) {
                return x.left;
            }

            Node successor = x.right;  // get the successor
            while (successor.left != null) {
                successor = successor.left;
            }

            Node h = x;
            x = successor;
            x.right = removeMin(h.right);
            x.left = h.left;
        }
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    private Node removeMin(Node x) {
        if (x.left == null)
            return x.right;
        x = removeMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    @Override
    public Iterator<K> iterator() {
        return new BSTMapIterator();
    }

    private class BSTMapIterator implements Iterator<K> {
        private Queue<K> keyQueue;

        public BSTMapIterator() {
            keyQueue = new LinkedList<>();
            enqueueKey(keyQueue, root);
        }

        public boolean hasNext() {
            return keyQueue.size() > 0;
        }

        public K next() {
            return keyQueue.poll();
        }
    }
}
