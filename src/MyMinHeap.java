import java.util.Comparator;

public class MyMinHeap<T extends Comparable<T>> {

    private MyArrayList<T> elements;
    private Comparator<T> comparator;

    public MyMinHeap() {
        this(null);
    }

    public MyMinHeap(Comparator<T> comparator) {
        this.elements = new MyArrayList<>();
        this.comparator = comparator;
    }

    public boolean isEmpty() {
        return elements.size() == 0;
    }

    public int size() {
        return elements.size();
    }

    public T check() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        return elements.get(0);
    }

    public T izvlcat() {
        if (isEmpty()) {
            throw new IllegalStateException("Heap is empty");
        }
        T min = check();
        elements.set(0, elements.get(elements.size() - 1));
        elements.remove(elements.size() - 1);
        heapifyDown(0);
        return min;
    }

    public void add(T element) {
        elements.add(element);
        bubbleUp(elements.size() - 1);
    }

    private void heap(int index) {
        int leftChildIndex = leftChild(index);
        int rightChildIndex = rightChild(index);
        int smallestIndex = index;

        if (leftChildIndex < elements.size() && compare(elements.get(leftChildIndex), elements.get(smallestIndex)) < 0) {
            smallestIndex = leftChildIndex;
        }

        if (rightChildIndex < elements.size() && compare(elements.get(rightChildIndex), elements.get(smallestIndex)) < 0) {
            smallestIndex = rightChildIndex;
        }

        if (smallestIndex != index) {
            swap(index, smallestIndex);
            heap(smallestIndex);
        }
    }

    private void uping(int index) {
        while (index > 0 && compare(elements.get(index), elements.get(parent(index))) < 0) {
            swap(index, parent(index));
            index = parent(index);
        }
    }

    private int leftChild(int index) {
        return 2 * index + 1;
    }

    private int rightChild(int index) {
        return 2 * index + 2;
    }

    private int parent(int index) {
        return (index - 1) / 2;
    }

    private void swap(int index1, int index2) {
        T temp = elements.get(index1);
        elements.set(index1, elements.get(index2));
        elements.set(index2, temp);
    }

    private int compare(T element1, T element2) {
        if (comparator != null) {
            return comparator.compare(element1, element2);
        } else {
            return element1.compareTo(element2);
        }
    }
}
