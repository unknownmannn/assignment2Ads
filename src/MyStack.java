public class MyStack<T> {

    private MyLinkedList<T> elements;

    public MyStack() {
        this.elements = new MyLinkedList<>();
    }

    public boolean empty() {
        return elements.size() == 0;
    }

    public int size() {
        return elements.size();
    }

    public T check() {
        if (empty()) {
            throw new IndexOutOfBoundsException("Stack is empty");
        }
        return elements.getLast();
    }

    public void push(T item) {
        elements.addLast(item);
    }

    public T pop() {
        if (empty()) {
            throw new IllegalStateException("Stack is empty");
        }
        return elements.removeLast();
    }
}
