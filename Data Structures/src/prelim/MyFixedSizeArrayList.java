package prelim;

import java.util.NoSuchElementException;

/**
 * MyFixedSizeArrayList - A fixed-size array-based list implementation
 * 
 * Perfect for limited capacity scenarios such as:
 * - Class Roster Manager (Maximum 5 students per section)
 * - Small group management systems
 * - Resource-constrained environments
 * 
 * Key Features:
 * - Fixed capacity of 5 elements
 * - Fast O(1) insertion at end when not full
 * - O(n) search and deletion operations
 * - Memory efficient with pre-allocated array
 * 
 * @param <E> the type of elements stored in this list
 */
public class MyFixedSizeArrayList<E> implements MyList<E> {
    private E[] array;
    private int size;
    private static final int CAPACITY = 5; // Maximum 5 students per section
    /**
     * Constructs an empty fixed-size array list with capacity of 5.
     */
    @SuppressWarnings("unchecked")
    public MyFixedSizeArrayList() {
        array = (E[]) new Object[CAPACITY];
        size = 0;
    }

    /**
     * Returns the maximum capacity of this list.
     * 
     * @return the maximum number of elements this list can hold
     */
    public int getCapacity() {
        return CAPACITY;
    }

    /**
     * Checks if the list is full.
     * 
     * @return true if the list has reached its maximum capacity
     */
    public boolean isFull() {
        return size >= CAPACITY;
    }

    /**
     * Checks if the list is empty.
     * 
     * @return true if the list contains no elements
     */
    public boolean isEmpty() {
        return size == 0;
    }
    /**
     * Returns the current number of elements in this list.
     * 
     * @return the number of elements currently stored
     */
    @Override
    public int getSize() {
        return size;
    }

    /**
     * Inserts an element at the end of the list.
     * Time Complexity: O(1)
     * 
     * @param data the element to be inserted
     * @throws ListOverflowException if the list is already full
     * @throws IllegalArgumentException if data is null
     */
    @Override
    public void insert(E data) throws ListOverflowException {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null element");
        }
        if (isFull()) {
            throw new ListOverflowException("Class roster is full! Maximum " + CAPACITY + 
                                          " students allowed per section.");
        }
        array[size] = data;
        size++;
        System.out.println("Successfully added element. Current size: " + size + "/" + CAPACITY);
    }
    /**
     * Retrieves an element from the list that matches the given data.
     * Time Complexity: O(n)
     * 
     * @param data the element to search for
     * @return the matching element from the list
     * @throws NoSuchElementException if the element is not found
     * @throws IllegalArgumentException if data is null
     */
    @Override
    public E getElement(E data) throws NoSuchElementException {
        if (data == null) {
            throw new IllegalArgumentException("Cannot search for null element");
        }
        for (int i = 0; i < size; i++) {
            if (array[i].equals(data)) {
                return array[i];
            }
        }
        throw new NoSuchElementException("Student not found in class roster: " + data);
    }
    /**
     * Removes the first occurrence of the specified element from the list.
     * Time Complexity: O(n)
     * 
     * @param data the element to be removed
     * @return true if the element was found and removed, false otherwise
     * @throws IllegalArgumentException if data is null
     */
    @Override
    public boolean delete(E data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot delete null element");
        }
        
        int index = search(data);
        if (index == -1) {
            System.out.println("Student not found in roster. Cannot delete.");
            return false;
        }
        
        // Shift elements to the left to fill the gap
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        array[size] = null; // Clear the last element to prevent memory leaks
        System.out.println("Successfully removed student. Current size: " + size + "/" + CAPACITY);
        return true;
    }
    /**
     * Searches for the first occurrence of the specified element.
     * Time Complexity: O(n)
     * 
     * @param data the element to search for
     * @return the index of the element if found, -1 otherwise
     * @throws IllegalArgumentException if data is null
     */
    @Override
    public int search(E data) {
        if (data == null) {
            throw new IllegalArgumentException("Cannot search for null element");
        }
        for (int i = 0; i < size; i++) {
            if (array[i].equals(data)) {
                return i;
            }
        }
        return -1; // Element not found
    }

    /**
     * Returns the element at the specified index.
     * 
     * @param index the index of the element to return
     * @return the element at the specified index
     * @throws IndexOutOfBoundsException if index is out of range
     */
    public E get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index " + index + " is out of bounds for size " + size);
        }
        return array[index];
    }

    /**
     * Helper method to display all elements in the roster.
     * Perfect for showing the current class roster status.
     */
    public void display() {
        System.out.println("\n=== CLASS ROSTER (Fixed Size = " + CAPACITY + ") ===");
        if (isEmpty()) {
            System.out.println("No students enrolled yet.");
        } else {
            System.out.println("Current enrollment: " + size + "/" + CAPACITY + " students");
            System.out.print("Students: [");
            for (int i = 0; i < size; i++) {
                System.out.print(array[i]);
                if (i < size - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
        System.out.println("Available spots: " + (CAPACITY - size));
        System.out.println("==========================================\n");
    }
}