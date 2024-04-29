import java.util.Iterator;
import java.util.NoSuchElementException;
public class MyLinkedList<T> implements MyList<T> {

    static class Node<T> {
        T item;
        Node<T> next;

        Node(T element, Node<T> next) {
            this.item = element;
            this.next = next;
        }
    }
    private Node<T> head;
    private Node<T> tail;
    private int size = 0;

    private void checkElementIndex(int index) {
        if (index < 0 || index >= size)
            throw new IndexOutOfBoundsException("Invalid Index: " + index + "or " +" Size: " + size);
    }

    private void isEmpty(){
        if(head==null){
            System.out.println("this list is empty");
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private Node<T> current = head;


            @Override
            public boolean hasNext() {
                return current != null;
            }


            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException("No more elements in the list");
                T item = current.item;
                current = current.next;
                return item;
            }
        };
    }



    @Override
    public Object set(int index, T item){
        checkElementIndex(index);
        Node<T> current = head;
        for(int i = 0; i< index; i++){
            current = current.next;
        }
        T res = current.item;
        current.item = item;
        return res;
    }


    @Override
    public void add(T item) {
        Node<T> newNode = new Node<>(item, null);
        if (head == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            tail = newNode;
        }
        size++;
    }



    @Override
    public void add(int index, T item) {
        checkElementIndex(index);

        if (index == 0) {
            addFirst(item);
        } else if (index == size) {
            addLast(item);
        } else {
            Node<T> current = head;
            for (int i = 0; i < index - 1; i++) {
                current = current.next;
            }

            Node<T> newNode = new Node<>(item, current.next);

            current.next = newNode;
            size++;
        }
    }



    @Override
    public void addFirst(T item){
        Node<T> newNode = new Node<>(item, null);
        if( head == null){
            head = newNode;
            return;
        }
        newNode.next = head;
        head = newNode;
    }




    @Override
    public void addLast(T item){
        Node<T> newNode = new Node<>(item, null);
        if( head == null){
            head = newNode;
            return;
        }
        Node<T> current = head;
        while(current.next != null){
            current = current.next;
        }
        current.next = newNode;
    }




    @Override
    public T get(int index){
        checkElementIndex(index);
        Node<T> current = head;
        for(int i = 0; i < index; i++){
            current = current.next;
        }
        return current.item;
    }



    @Override
    public T getFirst(){
        isEmpty();
        return head.item;
    }


    @Override
    public T getLast(){
        isEmpty();
        return tail.item;

    }





    @Override
    public void remove(int index){
        checkElementIndex(index);
        if (index == 0) {
            removeFirst();
            return;
        }
        if (index == size - 1) {
            removeLast();
            return;
        }
        Node<T> current = head;
        for (int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        size--;
    }

    @Override
    public void removeFirst(){
        isEmpty();
        head=head.next;
    }



    @Override
    public void removeLast(){
        isEmpty();
        if (size == 1) {
            head = tail = null;
            size--;
            return;
        }
        Node<T> current = head;
        while(current.next != tail) {
            current = current.next;
        }
        current.next = null;
        tail = current;
        size--;

    }



    @Override
    public void sort() {
        if (head == null || head.next == null) {
            return;
        }

        for (int i = 0; i < size; i++) {
            Node<T> current = head;
            for (int j = 0; j < size - 1; j++) {
                Comparable<T> currentComparable = (Comparable<T>) current.item;
                if (currentComparable.compareTo(current.next.item) > 0) {
                    T temp = current.item;
                    current.item = current.next.item;
                    current.next.item = temp;
                }
                current = current.next;
            }
        }
    }



    @Override
    public int indexOf(Object object) {
        Node<T> current = head;
        for (int i = 0; current != null; i++, current = current.next) {
            if (object == current.item) {
                return i;
            }
        }
        return -1;
    }



    @Override
    public int lastIndexOf(Object object) {
        Node<T> current = head;
        int lastIndex = -1;
        for (int i = 0; current != null; current = current.next, i++) {
            if (object == current.item) {
                lastIndex = i;
            }
        }
        return lastIndex;
    }



    @Override
    public boolean exists(Object object) {
        Node<T> current = head;
        while (current != null) {
            if (object == null && current.item == null) {
                return true;
            } else if (object != null && object.equals(current.item)) {
                return true;
            }
            current = current.next;
        }
        return false;
    }


    @Override
    public Object[] toArray() {
        Object[] array = new Object[size];
        int i = 0;
        Node<T> current = head;

        while (current != null) {
            array[i++] = current.item;
            current = current.next;
        }
        return array;
    }


    @Override
    public void clear(){
        head = tail = null;
        size = 0;
    }


    @Override
    public int size(){
        return size;
    }


}