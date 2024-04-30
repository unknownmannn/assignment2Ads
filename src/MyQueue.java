public class MyQueue<T> {
    private MyLinkedList<T> list = new MyLinkedList<>();


    public T queue1(T item) {
        list.addLast(item);
        return item;
    }



    public T queue2(){
        T removingItem = check();
        list.removeFirst();
        return removingItem;
    }




    public T check() {
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