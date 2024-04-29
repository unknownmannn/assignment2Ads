public class MyStack<T>{

    private MyArrayList<T> list = new MyArrayList<>();


    public T push(T item) {
        list.add(item);
        return item;
    }


    public T peek(){
        if (empty()) {
            return null;
        }
        return list.get(0);
    }


    public T pop(){
        if (empty()) {
            return null;
        }
        T removingItem = peek();
        list.removeFirst();
        return removingItem;
    }


    public boolean empty(){
        return list.size() == 0;
    }

    public int size() {
        return list.size();
    }
}