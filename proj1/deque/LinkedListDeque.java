package deque;

import org.hamcrest.core.IsEqual;

import java.util.Iterator;

public class LinkedListDeque<T> {

    private Node<T> sentinel;
    private int size;

    /** Deque implementation using linked list
     * Make it circular
     */
    public class Node<T> {
        public T data;
        public Node<T> next;
        public Node<T> prev;

        public Node(T x) {
            data = x;
        }
    }

    /** Constructor, creates an empty linked list deque*/
    public LinkedListDeque() {
        sentinel = new Node<T>(null);  // Not sure for now...
        sentinel.next = sentinel;
        sentinel.prev = sentinel;
        size = 0;
    }

    /** Check if the deque has items  */
    public boolean isEmpty() {
        return size > 0;
    }

    /** Should take constant time  */
    public int size() {
        return size;
    }

    /** Add item at the first of deque
     * The add method should not use any looping or recursion: how ?
     * such operation should take constant amount of time, not depend on size
     */
    public void addFirst(T data) {
        Node<T> newNode = new Node<T>(data);
        newNode.next = sentinel.next;
        newNode.prev = sentinel;
        sentinel.next.prev = newNode;
        sentinel.next = newNode;
        size += 1;
    }

    /** Add item at the last of deque */
    public void addLast(T data) {
        Node<T> newNode = new Node<T>(data);
        newNode.prev = sentinel.prev;
        newNode.next = sentinel;
        sentinel.prev.next = newNode;
        sentinel.prev = newNode;
        size += 1;
    }

    /** Remove the item at the first of deque  */
    public T removeFirst() {
        // If the deque is empty, return null
        if (isEmpty()) {
            return null;
        }

        Node<T> del = sentinel.next;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return del.data;
    }

    /** Remove the item at the last of deque  */
    public T removeLast() {
        // If the deque is empty, return null
        if (isEmpty()) {
            return null;
        }

        Node<T> del = sentinel.prev;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        return del.data;
    }

    /** Print all items in deque  */
    public void printDeque() {
        if (size == 0) {
            System.out.println();
            return;
        }

        // currentNode points at sentinel
        Node<T> currentNode = sentinel;

        // Iterate over the queue
        while (currentNode.next != sentinel) {
            currentNode = currentNode.next;
            System.out.print(currentNode.data + " ");
        }
        System.out.print(currentNode.data);
        System.out.println();
    }

    /** Get the item at the specified index
     * Must use iteration, not recursion
     */
    public T get(int index) {
        if (index <= 0) {
            throw new IllegalArgumentException("Invalid index number");
        } else if (index > size) {
            throw new IllegalArgumentException("Index out of range");
        }

        Node<T> currentNode = sentinel;
        for (int i = 0; i < index; i++) {
            currentNode = currentNode.next;
        }
        return currentNode.data;
    }

    /** Same as get(), but use recursion */
    public T getRecursive(int index) {
        // TODO: Not sure how to do the recursion thing!
        if (index <= 0) {
            throw new IllegalArgumentException("Invalid index number");
        } else if (index > size) {
            throw new IllegalArgumentException("Index out of range");
        }

        Node<T> currentNode = sentinel.next;
        if (index == 1) {
            return currentNode.data;
        }
        currentNode = currentNode.next;
        return getRecursive(index - 1);
    }

    /** Build the iterator class for linked list deque
     * Class method: hasNext(), next()
     */
    private class LinkedListDequeIterator implements Iterator<T>{
        private int position;
        private Node<T> currentNode;

        public LinkedListDequeIterator() {
            position = 0;
            currentNode = sentinel;
        }

        public boolean hasNext() {
            return position < size;
        }

        public T next() {
            position += 1;
            currentNode = currentNode.next;
            return currentNode.data;
        }
    }

    public Iterator<T> iterator() {
        return new LinkedListDequeIterator();  // Why need a new keyword?
    }

    @Override
    /** Comparison using class method */
    public boolean equals(Object o) {
        if (!(o instanceof LinkedListDeque)) {
            return false;
        }

        // Casting
        LinkedListDeque<T> otherObject = (LinkedListDeque<T>) o;
        if (otherObject == this) {
            return true;
        }
        if (otherObject == null) {
            return false;
        }
        if (otherObject.size() != this.size()) {
            return false;
        }

        Iterator<T> iteratorOther = otherObject.iterator();
        Iterator<T> iteratorThis = iterator();
        while(iteratorOther.hasNext() && iteratorThis.hasNext()) {
            if (iteratorOther.next() != iteratorThis.next()) {
                return false;
            }
        }

        // All tests passed, return true
        return true;
    }
}