package prelim;

import java.util.NoSuchElementException;

/**
 * MyGrowingArrayList - A dynamic array-based list implementation
 * 
 * Perfect for expanding collections such as:
 * - Academic Records System (Grows as more grades are added)
 * - Student grade tracking over multiple semesters
 * - Any scenario where the data size is unpredictable
 * 
 * Key Features:
 * - Dynamic capacity that doubles when full
 * - Fast O(1) amortized insertion at end
 * - O(n) search and deletion operations
 * - Automatic memory management with growth
 * 
 * @param <E> the type of elements stored in this list
 */
public class MyGrowingArrayList<E> implements MyList<E> {
    private E[] array;
    private int size;
    private int capacity;
    /**
     * Constructs an empty growing array list with initial capacity of 5.
     */
    @SuppressWarnings("unchecked")
    public MyGrowingArrayList() {
        capacity = 5; // Initial capacity for grade records
        array = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * Constructs an empty growing array list with specified initial capacity.
     * 
     * @param initialCapacity the initial capacity of the list
     * @throws IllegalArgumentException if initialCapacity is negative
     */
    @SuppressWarnings("unchecked")
    public MyGrowingArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Initial capacity cannot be negative: " + initialCapacity);
        }
        capacity = Math.max(initialCapacity, 1); // Ensure minimum capacity of 1
        array = (E[]) new Object[capacity];
        size = 0;
    }

    /**
     * Returns the current capacity of this list.
     * 
     * @return the current capacity
     */
    public int getCapacity() {
        return capacity;
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
     * Automatically grows the array if needed.
     * Time Complexity: O(1) amortized
     * 
     * @param data the element to be inserted
     * @throws IllegalArgumentException if data is null
     */
    @Override
    public void insert(E data) throws ListOverflowException {
        if (data == null) {
            throw new IllegalArgumentException("Cannot insert null element");
        }
        if (size >= capacity) {
            grow(); // Double the capacity automatically
        }
        array[size] = data;
        size++;
        System.out.println("Successfully added grade record. Current size: " + size);
    }
    /**
     * Doubles the capacity of the internal array.
     * This method is called automatically when the array becomes full.
     * Time Complexity: O(n) for copying elements
     */
    @SuppressWarnings("unchecked")
    private void grow() {
        int newCapacity = capacity * 2;
        E[] newArray = (E[]) new Object[newCapacity];
        
        // Copy all existing elements to new array
        for (int i = 0; i < size; i++) {
            newArray[i] = array[i];
        }
        
        array = newArray;
        capacity = newCapacity;
        System.out.println("Academic records storage expanded! New capacity: " + capacity);
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
        throw new NoSuchElementException("Grade record not found: " + data);
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
            System.out.println("Grade record not found. Cannot delete.");
            return false;
        }
        
        // Shift elements to the left to fill the gap
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
        array[size] = null; // Clear the last element to prevent memory leaks
        System.out.println("Successfully removed grade record. Current size: " + size);
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
     * Trims the capacity to the current size to save memory.
     * Useful when no more elements will be added.
     */
    @SuppressWarnings("unchecked")
    public void trimToSize() {
        if (size < capacity) {
            E[] newArray = (E[]) new Object[size];
            for (int i = 0; i < size; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
            capacity = size;
            System.out.println("Memory optimized! Capacity trimmed to: " + capacity);
        }
    }

    /**
     * Helper method to display all grade records.
     * Perfect for showing the academic records system status.
     */
    public void display() {
        System.out.println("\n=== ACADEMIC RECORDS SYSTEM (Dynamic Growth) ===");
        if (isEmpty()) {
            System.out.println("No grade records found.");
        } else {
            System.out.println("Total grade records: " + size);
            System.out.println("Current capacity: " + capacity);
            System.out.println("Memory efficiency: " + String.format("%.1f%%", (double)size/capacity * 100));
            System.out.print("Records: [");
            for (int i = 0; i < size; i++) {
                System.out.print(array[i]);
                if (i < size - 1) System.out.print(", ");
            }
            System.out.println("]");
        }
        System.out.println("==============================================\n");
    }
}