package deque;

import java.util.Iterator;

public class ArrayDeque<T> {

    /** Deque implementation using array */
    private int capacity;
    private T[] items;
    private int first;
    private int last;
    private int size;

    /** Constructor, creates an empty linked list deque*/
    public ArrayDeque() {
        capacity = 8;
        items = (T[]) new Object[capacity];
        size = 0;
    }

    /** Check if the deque has items  */
    public boolean isEmpty() {
        return size == 0;
    }

    /** Check if the deque is full  */
    public boolean isFull() {
        return size == capacity;
    }

    /** Should take constant time  */
    public int size() {
        return size;
    }

    /** Add item at the first of deque
     * The add method should not use any looping or recursion
     * such operation should take constant amount of time, not depend on size
     */
    public void addFirst(T item) {
        if (isFull()) {
            resize();
        }

        if (isEmpty()) {
            first = 0;
            last = 0;
        } else if (first == 0) {
            first = capacity - 1;
        } else {
            first -= 1;
        }
        items[first] = item;
        size += 1;
    }

    /** Add item at the last of deque */
    public void addLast(T item) {
        if (isFull()) {
            resize();
        }

        if (isEmpty()) {
            first = 0;
            last = 0;
        } else if (last == capacity - 1) {
            last = 0;
        } else {
            last += 1;
        }
        items[last] = item;
        size += 1;
    }

    /** Remove the item at the first of deque  */
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        T delItem = items[first];
        items[first] = null;
        size -= 1;

        if (first == capacity - 1) {
            first = 0;
        } else {
            first += 1;
        }
        return delItem;
    }

    /** Remove the item at the last of deque  */
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        T delItem = items[last];
        items[last] = null;
        size -= 1;

        if (last == 0) {
            last = capacity - 1;
        } else {
            last -= 1;
        }
        return delItem;
    }

    /** Resize method: should be very tricky */
    public void resize() {
        int newCapacity = 2 * capacity;
        T[] newItems = (T[]) new Object[newCapacity];

        // Looping the number of size times
        for (int i = 0; i < size; i++) {
            newItems[(first + i) % newCapacity] = items[(first + i) % capacity];
        }

        last = (first + size - 1) % newCapacity;
        capacity = newCapacity;
        items = newItems;
    }


    /** Print all items in deque  */
    public void printDeque() {
        int firstPos = first;
        int lastPos = last;

        while (firstPos != lastPos) {
            System.out.print(items[firstPos] + " ");
            if (firstPos == capacity - 1) {
                firstPos = 0;
            } else {
                firstPos += 1;
            }
        }
        System.out.print(items[firstPos]);
        System.out.println();
    }

    /** Get the item at the specified index
     * Must use iteration, not recursion
     */
    public T get(int index) {
        if (index < 0 || index > size) {
            return null;
        }

        int currentPos = (first + index) % capacity;
        return items[currentPos];
    }

    /**   */
    // public Iterator<T> iterator() {}

    /**   */
    public boolean equals(Object o) {
        if (!(o instanceof ArrayDeque)) {
            return false;
        }

        ArrayDeque<T> otherObject = (ArrayDeque<T>) o;
        if (otherObject == null) {
            return false;
        }
        if (otherObject == this) {
            return true;
        }
        if (otherObject.size() != this.size()) {
            return false;
        }

        // TODO: Iterating all elements and compare
        return true;
    }
}