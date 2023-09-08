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
            growArray();
        }
        array[currentPosition] = element;
        currentPosition++;
    }

    private void growArray() {
        T[] newArray = (T[]) new Object[array.length * MULTIPLIER];
        System.arraycopy(newArray, 0, newArray, 0, array.length);
        this.array = newArray;
    }

}
