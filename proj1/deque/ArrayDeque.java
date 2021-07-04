package deque;

import java.util.Iterator;

public class ArrayDeque<T> implements Deque<T> {

    /* Deque implementation using array */
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

    /* Check if the deque is full */
    public boolean isFull() {
        return size == capacity;
    }

    /* Resize method: should be very tricky */
    public void resize(double scalingFactor) {
        int newCapacity = (int) scalingFactor * capacity;
        //int newCapacity = (int) 100+ capacity;
        T[] newItems = (T[]) new Object[newCapacity];

        // Looping the number of size times
        for (int i = 0; i < size; i++) {
            newItems[(first + i) % newCapacity] = items[(first + i) % capacity];
        }

        last = (first + size - 1) % newCapacity;
        capacity = newCapacity;
        items = newItems;
    }

    /* Should take constant time */
    @Override
    public int size() {
        return size;
    }

    /** Add item at the first of deque
     * The add method should not use any looping or recursion
     * such operation should take constant amount of time, not depend on size
     */
    @Override
    public void addFirst(T item) {
        if (isFull()) {
            resize(2);
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

    /* Add item at the last of deque */
    @Override
    public void addLast(T item) {
        if (isFull()) {
            resize(2);
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

    /* Remove the item at the first of deque */
    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        } else if ((size-1) < 0.25*capacity && capacity > 8) {
            resize(0.5);
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

    /* Remove the item at the last of deque */
    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        } else if ((size-1) < 0.25*capacity && capacity > 8) {
            resize(0.5);
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

    /* Print all items in deque */
    @Override
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
    @Override
    public T get(int index) {
        if (index < 0 || index > size) {
            return null;
        }

        int currentPos = (first + index) % capacity;
        return items[currentPos];
    }

    private Iterator<T> iterator() {
        return new arrayDequeIterator();
    }

    private class arrayDequeIterator implements Iterator<T> {
        private int position;

        public arrayDequeIterator() {
            position = 0;
        }

        public boolean hasNext() {
            return position < size;
        }

        public T next() {
            T returnItem = items[(first+position) % capacity];
            position += 1;
            return returnItem;
        }
    }

    /* Comparison using class method */
    @Override
    public boolean equals(Object o) {
        if (o == null) {
            return false;
        } else if (!(o instanceof ArrayDeque)) {
            return false;
        } else if (o == this) {
            return true;
        }

        ArrayDeque<T> otherObject = (ArrayDeque<T>) o;
        if (otherObject.size() != this.size()) {
            return false;
        }

        Iterator<T> otherObjectIter = otherObject.iterator();
        Iterator<T> thisIter = iterator();
        while (otherObjectIter.hasNext()) {
            if (otherObjectIter.next() != thisIter.next()) {
                return false;
            }
        }

        // All tests passed, return true
        return true;
    }
}
