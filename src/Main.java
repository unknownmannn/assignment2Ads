public class Main {
    public static void main(String[] args) {
        MyLinkedList<String> list = new MyLinkedList<>();
        list.add("apple");
        list.add("banana");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}