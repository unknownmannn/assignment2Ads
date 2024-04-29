import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyArrayList<T> implements MyList<T> {
    private int size = 0;
    private static final int DEFAULT_CAPACITY = 10;
    private T[] array;

    public MyArrayList() {
        array = (T[]) new Object[DEFAULT_CAPACITY];
    }


    public MyArrayList(int capacity) {
        if (capacity < 0)
            throw new IllegalArgumentException("Illegal Capacity: " + capacity);
        array = (T[]) new Object[capacity];
        size = 0;
    }


    @Override
    public int size() {
        return size;
    }



    public void checkForCapacity(){
        if(size == array.length){
            T[] newArray = (T[]) new Object[size * 2 + 1]; //  + 1 to handle zero initial capacity
            for(int i = 0; i<size; i++){
                newArray[i] = (T) array[i];
            }
            array = newArray;
        }
    }


    @Override

    public void add(T item){
        checkForCapacity();
        array[size] = item;
        size++;
    }


    @Override
    public void addFirst(T item){
        checkForCapacity();
        for( int i = size; i > 0; i--){
            array[i] = array[i-1];
        }
        array[0] = item;
        size++;

    }

    @Override
    public void addLast(T item){
        checkForCapacity();
        array[size] = item;
        size++;

    }

    private void throwException(int index){
        if (index > size || index < 0)
            throw new IndexOutOfBoundsException("Invalid index: " + index);
    }

    private void checkEmpty() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty.");
        }
    }



    @Override

    public void add(int index, T item){
        throwException(index);
        checkForCapacity();
        for(int i = size; i>index; i--){
            array[i] = array[i-1];
        }
        array[index] = item;
        size++;
    }


    @Override
    public void remove(int index){
        throwException(index);
        for( int i = index; i<size-1; i++){
            array[i] = array[i+1];
        }
        array[size - 1] = null; // prevents the memory leak
        size--;
    }

    @Override
    public void removeFirst() {
        checkEmpty();
        for (int i = 1; i < size; i++) {
            array[i - 1] = array[i];
        }
        array[--size] = null;
    }

    @Override
    public void removeLast() {
        checkEmpty();
        array[--size] = null;
    }



    @Override
    public T get(int index){
        throwException(index);
        return (T) array[index];
    }

    @Override
    public T getFirst(){
        checkEmpty();
        return (T) array[0];
    }

    @Override
    public T getLast(){
        checkEmpty();
        return (T) array[size-1];
    }

    @Override
    public void clear(){
        for( int i = 0; i < size; i++){
            array[i] = null;
        }
        size = 0;
    }


    @Override
    public Object[] toArray(){
        Object[] result = new Object[size];
        for( int i = 0; i< size; i++){
            result[i] = array[i];
        }
        return result;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int indx = 0;

            @Override
            public boolean hasNext() {
                return indx < size;
            }

            @Override
            public T next() {
                if (!hasNext()) throw new NoSuchElementException();
                return (T) array[indx++];
            }
        };
    }

    @Override
    public Object set(int index, T item){
        throwException(index);
        return array[index] = item;
    }


    @Override
    public boolean exists(Object object) {
        for (int i = 0; i < size; i++) {
            if (array[i] == null && object == null) {
                return true;  // Both are null
            } else if (array[i] != null && array[i].equals(object)) {
                return true;  // not null and equal
            }
        }
        return false;
    }


    @Override
    public void sort() {
        T temp;
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size - 1; j++) {
                if (((Comparable<T>) array[j]).compareTo(array[j + 1]) > 0) {
                    temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                }
            }
        }
    }



    @Override
    public int indexOf(Object object){
        if( object == null){
            for (int i = 0; i < size; i++)
            {
                if (array[i] == null)
                    return i;
            }
        }else
        {
            for (int i = 0; i < size; i++)
            {
                if(object.equals(array[i]))
                    return i;
            }
        }
        return -1;
    }


    @Override
    public int lastIndexOf(Object object) {
        if (object == null) {
            for (int i = size - 1; i >= 0; i--) {
                if (array[i] == null) {
                    return i;
                }
            }
        } else {
            for (int i = size - 1; i >= 0; i--) {
                if (object.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }






}
