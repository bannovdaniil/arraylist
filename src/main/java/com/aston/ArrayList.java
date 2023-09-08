package com.aston;

public class ArrayList<T> {
    private T[] array;
    private static final int DEFAULT_CAPACITY = 2;
    private static final int MULTIPLIER = 2;
    private int currentPosition = 0;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        this.array = (T[]) new Object[capacity];
    }

    public void add(T element) {
        if (currentPosition >= array.length) {
            growArray(currentPosition);
        }
        array[currentPosition] = element;
        currentPosition++;
    }

    public void add(int index, T element) {
        if (index >= array.length) {
            growArray(index);
        }
        checkBounds(index);
        array[index] = element;
        currentPosition = index;
    }

    public T get(int index) {
        checkBounds(index);
        return array[index];
    }

    private void growArray(int index) {
        long newCapacity = array.length * MULTIPLIER;
        if (newCapacity < index) {
            newCapacity = index + DEFAULT_CAPACITY;
        }
        if (newCapacity > Integer.MAX_VALUE) {
            newCapacity = Integer.MAX_VALUE;
        }

        T[] newArray = (T[]) new Object[(int) newCapacity];
        System.arraycopy(this.array, 0, newArray, 0, array.length);
        this.array = newArray;
        System.out.println("new size: " + array.length);
    }


    private void checkBounds(int index) {
        if (index < 0 || index >= array.length) {
            throw new IndexOutOfBoundsException();
        }
    }

}
