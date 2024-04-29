public class MyQueue<T> {
    private MyLinkedList<T> list = new MyLinkedList<>();


    public T enqueue(T item) {
        list.addLast(item);
        return item;
    }



    public T dequeue(){
        T removingItem = peek();
        list.removeFirst();
        return removingItem;
    }




    public T peek() {
        if (isEmpty()) {
            return null;
        }
        return list.getFirst();
    }

    public boolean isEmpty() {
        return list.size() == 0;
    }
    public int size() {
        return list.size();
    }
}