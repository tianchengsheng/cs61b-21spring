package deque;

public interface Deque<T> {
    default boolean isEmpty() {
        return size() == 0;
    }

    public void addFirst(T item);

    public void addLast(T item);

    public T removeFirst();

    public T removeLast();

    public int size();

    public void printDeque();

    public T get(int index);

    public boolean equals(Object o);
}
