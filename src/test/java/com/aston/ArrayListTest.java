package com.aston;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.StringJoiner;

class ArrayListTest {
    private ArrayList<String> testStringList;
    private ArrayList<Integer> testIntegerList;

    @BeforeEach
    void setUp() {
        testStringList = new ArrayList<>();
        testIntegerList = new ArrayList<>();
    }

    @AfterEach
    void tearDown() {
        testStringList = null;
        testIntegerList = null;
    }

    @DisplayName("Add elements to List")
    @ParameterizedTest
    @CsvSource(value = {
            "3; '[1, 2, 3]'; '1,2,3'",
            "4; '[4, 3, 2, 1]'; '4,3,2,1'"
    }, delimiter = ';'
    )
    void add(int expectedCount, String expectedToString, String values) {
        for (String value : values.split(",")) {
            testStringList.add(value);
            testIntegerList.add(Integer.parseInt(value));
        }
        Assertions.assertEquals(expectedCount, testStringList.size());
        Assertions.assertEquals(expectedToString, testStringList.toString());

        Assertions.assertEquals(expectedCount, testIntegerList.size());
        Assertions.assertEquals(expectedToString, testIntegerList.toString());
    }

    @DisplayName("Add a lot of elements to List in Loop")
    @ParameterizedTest
    @CsvSource(value = {
            "300",
            "100500"
    })
    void addInLoop(int expectedCount) {
        StringJoiner sjExpected = new StringJoiner(", ", "[", "]");
        for (int i = 0; i < expectedCount; i++) {
            testStringList.add(Integer.toString(i));
            testIntegerList.add(i);
            sjExpected.add(Integer.toString(i));
        }
        Assertions.assertEquals(expectedCount, testStringList.size());
        Assertions.assertEquals(sjExpected.toString(), testStringList.toString());

        Assertions.assertEquals(expectedCount, testIntegerList.size());
        Assertions.assertEquals(sjExpected.toString(), testIntegerList.toString());
    }


    @DisplayName("Insert elements to List")
    @ParameterizedTest
    @CsvSource(value = {
            "4; '[0, 1, 2, 3]';    0; 0; '1,2,3'", // at start
            "4; '[1, 2, 0, 3]';    2; 0; '1,2,3'", // into middle
            "5; '[4, 3, 2, 1, 0]'; 4; 0; '4,3,2,1'" // into end
    }, delimiter = ';'
    )
    void insert(int expectedCount, String expectedToString, int index, String element, String values) {
        for (String value : values.split(",")) {
            testStringList.add(value);
            testIntegerList.add(Integer.parseInt(value));
        }
        testStringList.insert(index, element);
        testIntegerList.insert(index, Integer.parseInt(element));

        Assertions.assertEquals(expectedCount, testStringList.size());
        Assertions.assertEquals(expectedToString, testStringList.toString());

        Assertions.assertEquals(expectedCount, testIntegerList.size());
        Assertions.assertEquals(expectedToString, testIntegerList.toString());
    }

    @DisplayName("Insert elements to List in Loop")
    @ParameterizedTest
    @CsvSource(value = {
            "300",
            "100500"
    })
    void insertInLoop(int expectedCount) {
        StringJoiner sjExpected = new StringJoiner(", ", "[", "]");
        testStringList.add("a");
        testStringList.add("b");
        sjExpected.add("a");
        for (int i = 0; i < expectedCount - 2; i++) {
            testStringList.insert(1, "x");
            sjExpected.add("x");
        }
        sjExpected.add("b");

        Assertions.assertEquals(expectedCount, testStringList.size());
        Assertions.assertEquals(sjExpected.toString(), testStringList.toString());
    }


    @Test
    void set() {
    }

    @Test
    void remove() {
    }

    @Test
    void testRemove() {
    }

    @Test
    void get() {
    }

    @Test
    void clear() {
    }

    @Test
    void size() {
    }

    @Test
    void sort() {
    }
}