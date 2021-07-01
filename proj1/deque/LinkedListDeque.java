package deque;

import java.util.Iterator;

public class LinkedListDeque<T> {
    public class Node<T> {
        public T item;
        public Node next;
        public Node last;

        public Node(T x, Node nextNode, Node lastNode) {
            item = x;
            next = nextNode;
            last = lastNode;
        }
    }

    private Node first;
    private int size;

    /** Constructor, creates an empty linked list deque*/
    public LinkedListDeque() {
        first = new Node();  // Not sure for now...
        size = 0;
    }

    /** Add item at the first of deque
     * The add method should not use any looping or recursion: how ?
     * such operation should take constant amount of time, not depend on size
     */
    public void addFirst(T item) {}

    /** Add item at the last of deque */
    public void addLast(T item) {}

    /** Remove the item at the first of deque  */
    public T removeFirst() {}

    /** Remove the item at the last of deque  */
    public T removeLast() {}

    /** Check if the deque has items  */
    public boolean isEmpty() {
        return size > 0;
    }

    /** Should take constant time  */
    public int size() {
        return size;
    }

    /** Print all items in deque  */
    public void printDeque() {}

    /** Get the item at the specified index
     * Must use iteration, not recursion
     */
    public T get(int index) {}

    /** Same as get(), but use recursion */
    public T getRecursive(int index) {}

    /**   */
    public Iterator<T> iterator() {}

    /**   */
    public boolean equals(Object o) {
        return true;
    }
}
