package com.aston;

import java.util.Arrays;
import java.util.Comparator;

public class ArrayList<T> {
    private T[] array;
    private static final int DEFAULT_CAPACITY = 2;
    private static final int MULTIPLIER = 2;
    private int lastPosition = 0;

    public ArrayList() {
        this.array = (T[]) new Object[DEFAULT_CAPACITY];
    }

    public ArrayList(int capacity) {
        this.array = (T[]) new Object[capacity];
    }

    public void add(T element) {
        if (lastPosition >= array.length) {
            growArray(lastPosition);
        }
        array[lastPosition] = element;
        lastPosition++;
    }

    public void insert(int index, T element) {
        if (index > lastPosition) {
            throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, lastPosition));
        }
        if (lastPosition + 1 >= array.length) {
            growArray(lastPosition);
        }
        System.arraycopy(array, index, array, index + 1, lastPosition - index);
        set(index, element);
        lastPosition++;
    }

    public void set(int index, T element) {
        checkBounds(index);
        array[index] = element;
    }

    public T remove(int index) {
        checkBounds(index);
        T element = array[index];
        array[index] = null;
        if (index == lastPosition - 1) {
            lastPosition--;
        }
        return element;
    }

    public boolean remove(T element) {
        boolean result = false;
        for (int i = 0; i < lastPosition; i++) {
            if (element == null) {
                if (array[i] == null) {
                    result = true;
                    break;
                }
            } else {
                if (element.equals(array[i])) {
                    array[i] = null;
                    result = true;
                    if (i == lastPosition - 1) {
                        lastPosition--;
                    }
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
        lastPosition = 0;
    }

    public void sort(Comparator<? super T> comparator) {
        qsort(0, array.length, comparator);
    }

    public int size() {
        return lastPosition;
    }

    private void qsort(int low, int high, Comparator comparator) {
        int middle = low + (high - low) / 2;
        T opor = null;
        while (opor == null) {
            opor = array[middle];
            middle--;
        }
        int leftBound = low;
        int rightBound = high;
        while (leftBound <= rightBound) {
            while (comparator.compare(opor, array[leftBound]) < 0) {
                leftBound++;
            }
            while (comparator.compare(opor, array[rightBound]) > 0) {
                rightBound++;
            }
            if (leftBound <= rightBound) {
                T temp = array[leftBound];
                array[leftBound] = array[rightBound];
                array[rightBound] = temp;
                leftBound++;
                rightBound--;
            }
        }
        if (low < rightBound) {
            qsort(low, rightBound, comparator);
        }
        if (high < leftBound) {
            qsort(leftBound, high, comparator);
        }
    }

    public void rsort() {
        System.out.println("sort R");
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
        return Arrays.toString(Arrays.copyOfRange(array, 0, lastPosition));
    }
}
