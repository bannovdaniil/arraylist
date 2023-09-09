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
        checkBounds(index);
        if (lastPosition + 1 >= array.length) {
            growArray(lastPosition);
        }
        System.arraycopy(array, index, array, index + 1, lastPosition - index);
        array[index] = element;
        lastPosition++;
    }

    public void set(int index, T element) {
        checkBounds(index);
        array[index] = element;
    }

    public T remove(int index) {
        checkBounds(index);
        T element = array[index];
        System.arraycopy(array, index + 1, array, index, lastPosition - index - 1);
        lastPosition--;
        array[lastPosition] = null;
        return element;
    }

    public boolean remove(T findElement) {
        boolean result = false;

        if (findElement == null) {
            for (int i = 0; i < lastPosition; i++) {
                if (array[i] == null) {
                    remove(i);
                    result = true;
                    break;
                }
            }
        } else {
            for (int i = 0; i < lastPosition; i++) {
                if (findElement.equals(array[i])) {
                    remove(i);
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
        lastPosition = 0;
    }

    public int size() {
        return lastPosition;
    }

    public void sort(Comparator<? super T> comparator) {
        quickSort(0, lastPosition - 1, comparator);
    }

    private void quickSort(int low, int high, Comparator comparator) {
        if (low >= high) {
            return;
        }
        int middle = low + (high - low) / 2;
        T opor = array[middle];

        int leftBound = low;
        int rightBound = high;
        while (leftBound <= rightBound) {
            while (comparator.compare(array[leftBound], opor) < 0) {
                leftBound++;
            }
            while (comparator.compare(array[rightBound], opor) > 0) {
                rightBound--;
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
            quickSort(low, rightBound, comparator);
        }
        if (high > leftBound) {
            quickSort(leftBound, high, comparator);
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
        if (index < 0 || index >= lastPosition) {
            throw new IndexOutOfBoundsException(String.format("Index: %d, Size: %d", index, lastPosition));
        }
    }

    @Override
    public String toString() {
        return Arrays.toString(Arrays.copyOfRange(array, 0, lastPosition));
    }
}
