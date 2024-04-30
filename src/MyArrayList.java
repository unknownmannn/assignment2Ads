public class MyArrayList<T> implements MyList<T> {
    private T[] items;
    private int size = 0;

    public MyArrayList() {
        items = (T[]) new Object[10];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) throw new IllegalArgumentException("notvalid capacity");
        items = (T[]) new Object[initialCapacity];
    }

    public int size() {
        return size;
    }

    private void ensureCapacity() {
        if (size == items.length) {
            T[] newItems = (T[]) new Object[items.length * 2 + 1];
            System.arraycopy(items, 0, newItems, 0, size);
            items = newItems;
        }
    }

    public void add(T item) {
        ensureCapacity();
        items[size++] = item;
    }

    public void addFirst(T item) {
        ensureCapacity();
        System.arraycopy(items, 0, items, 1, size);
        items[0] = item;
        size++;
    }

    public void addLast(T item) {
        ensureCapacity();
        items[size++] = item;
    }

    private void checkIndex(int index) {
        if (index >= size || index < 0) throw new IndexOutOfBoundsException("notvalid index");
    }

    private void checkNotEmpty() {
        if (size == 0) throw new NoSuchElementException("List is empty");
    }

    public void addAtIndex(int index, T item) {
        checkIndex(index);
        ensureCapacity();
        System.arraycopy(items, index, items, index + 1, size - index);
        items[index] = item;
        size++;
    }

    public void remove(int index) {
        checkIndex(index);
        System.arraycopy(items, index + 1, items, index, size - index - 1);
        items[size - 1] = null;
        size--;
    }

    public void removeFirst() {
        checkNotEmpty();
        System.arraycopy(items, 1, items, 0, size - 1);
        items[size - 1] = null;
        size--;
    }

    public void removeLast() {
        checkNotEmpty();
        items[size - 1] = null;
        size--;
    }

    public T get(int index) {
        checkIndex(index);
        return items[index];
    }

    public T getFirst() {
        checkNotEmpty();
        return items[0];
    }

    public T getLast() {
        checkNotEmpty();
        return items[size - 1];
    }

    public void clear() {
        for (int i = 0; i < size; i++) {
            items[i] = null;
        }
        size = 0;
    }

    public Object[] toArray() {
        Object[] result = new Object[size];
        System.arraycopy(items, 0, result, 0, size);
        return result;
    }

    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return items[index++];
            }
        };
    }

    public T set(int index, T item) {
        checkIndex(index);
        T oldItem = items[index];
        items[index] = item;
        return oldItem;
    }

    public boolean contains(T object) {
        for (int i = 0; i < size; i++) {
            if (items[i] == null && object == null) return true;
            else if (items[i] != null && items[i].equals(object)) return true;
        }
        return false;
    }
}
