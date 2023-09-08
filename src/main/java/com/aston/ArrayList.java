package com.aston;

import java.util.Arrays;

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
        set(index, element);
        currentPosition = index;
    }

    public void set(int index, T element) {
        checkBounds(index);
        array[index] = element;
    }

    public T remove(int index) {
        checkBounds(index);
        T element = array[index];
        array[index] = null;
        return element;
    }

    public boolean remove(T element) {
        boolean result = false;
        for (int i = 0; i < array.length; i++) {
            if (element == null) {
                if (array[i] == null) {
                    result = true;
                    break;
                }
            } else {
                if (element.equals(array[i])) {
                    array[i] = null;
                    result = true;
                    break;
                }
            }
        }

        return result;
    }

    public T get(int index) {
        checkBounds(index);
        return array[index];
    }

    public void clear() {
        Arrays.fill(array, null);
        currentPosition = 0;
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

    @Override
    public String toString() {
        return "{" + Arrays.toString(array) + '}';
    }
}
