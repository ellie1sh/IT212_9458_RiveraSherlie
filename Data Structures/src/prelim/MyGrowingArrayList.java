package prelim;
import java.util.NoSuchElementException;
/**
 * Student Grade Tracker
 * Application: Academic Records System – Grows as more grades are added
 *
 * Notes:
 * - This list grows dynamically as items are inserted, similar to adding
 *   grades across terms. Logic remains generic, but comments and messages use
 *   the grading metaphor for readability.
 */
public class MyGrowingArrayList<E> implements MyList<E> {
    private E[] array;
    private int size;
    private int capacity;
    @SuppressWarnings("unchecked")
    public MyGrowingArrayList() {
        capacity = 5;
        array = (E[]) new Object[capacity];
        size = 0;
    }
    @Override
    public int getSize() {
        return size;
    }
    @Override
    public void insert(E data) throws ListOverflowException {
        if (size >= capacity) {
// Double the capacity to accommodate more grade records
            grow();
        }
        array[size] = data;
        size++;
    }
    @SuppressWarnings("unchecked")
    private void grow() {
        int newCapacity = capacity * 2;
        E[] newArray = (E[]) new Object[newCapacity];
// Copy existing grade entries to the larger transcript array
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        array = newArray;
        capacity = newCapacity;
        System.out.println("Capacity increased to: " + capacity);
    }
    @Override

    public E getElement(E data) throws NoSuchElementException {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(data)) {
                return array[i];
            }
        }
        throw new NoSuchElementException("Element not found: " + data);
    }
    @Override
    public boolean delete(E data) {
        int index = search(data);
        if (index == -1) {
            return false;
        }
// Shift elements to the left
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        array[size] = null;
        return true;
    }
    @Override
    public int search(E data) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }
    public void display() {
        System.out.print("Grades: [");
        for (int i = 0; i < size; i++) {
            System.out.print(array[i]);
            if (i < size - 1) System.out.print(", ");
        }
        System.out.println("] (Capacity: " + capacity + ")");
    }
}