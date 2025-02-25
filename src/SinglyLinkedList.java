import java.io.*;
import java.util.ArrayList;
import java.util.NoSuchElementException;

public class SinglyLinkedList<T> implements Serializable {

    // The head node of the list
    transient private Node<T> head;

    // Node inner class representing each element in the list
    private static class Node<T> {
        T data;       // Data stored in the node
        Node<T> next; // Reference to the next node in the list

        public Node(T data) {
            this.data = data;
            this.next = null;
        }
    }


    // Adds an element at the beginning of the list
    public void addFirst(T data) {
        Node<T> newNode = new Node<>(data);
        newNode.next = head;
        head = newNode;
    }

    // Adds an element at the end of the list
    public void addLast(T data) {
        Node<T> newNode = new Node<>(data);
        if (head == null) { // If the list is empty
            head = newNode;
            return;
        }
        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Removes the first element of the list and returns its data
    public T removeFirst() {
        if (head == null) {
            throw new NoSuchElementException("List is empty.");
        }
        T removedData = head.data;
        head = head.next;
        return removedData;
    }

    // Prints the linked list in a readable format
    public void printList() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    private void writeObject(ObjectOutputStream oos)
            throws IOException{
       // oos.defaultWriteObject();
        oos.writeInt(this.size());
        Node<T> current = head;
        while (current != null) {
            oos.writeObject(current.data);
            current = current.next;
        }
    }

    private int size() {
        int size = 0;
        Node<T> current = head;
        while (current != null) {
            size++;
            current = current.next;
        }
        return size;
    }

    private void readObject(ObjectInputStream ois)
            throws IOException, ClassNotFoundException{
        ois.defaultReadObject();
        int size = ois.readInt();
        int counter = 0;
        while (counter++ < size) {
            T data = (T) ois.readObject();
            addLast(data);
        }
    }
    // Main method for testing the SinglyLinkedList
    public static void main(String[] args) {
        SinglyLinkedList<Integer> list = new SinglyLinkedList<>();

        // Adding elements to the list
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        System.out.println("Initial list:");
        list.printList();

        // Adding an element at the beginning
        list.addFirst(5);
        System.out.println("After adding 5 at the beginning:");
        list.printList();

        // Removing the first element
        list.removeFirst();
        System.out.println("After removing the first element:");
        list.printList();

        String filePath = "intList.dat";

        try (
                FileOutputStream fileOut = new FileOutputStream(filePath);
                BufferedOutputStream bufferOut = new BufferedOutputStream(fileOut);
                ObjectOutputStream objectOut = new ObjectOutputStream(bufferOut)
        ) {
            objectOut.writeObject(list); // Writing object
            System.out.println("Object written to file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // read

        try ( // try with resources
              FileInputStream fileIn = new FileInputStream(filePath);
              BufferedInputStream bufferIn = new BufferedInputStream(fileIn);
              ObjectInputStream objectIn = new ObjectInputStream(bufferIn)
        ) {
            SinglyLinkedList<Integer> sll = (SinglyLinkedList<Integer>) objectIn.readObject(); // Reading object
            sll.printList();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
