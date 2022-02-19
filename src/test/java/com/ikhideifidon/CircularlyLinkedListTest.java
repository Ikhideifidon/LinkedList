package com.ikhideifidon;

import org.junit.jupiter.api.*;

import java.util.Iterator;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CircularlyLinkedListTest {

    CircularlyLinkedList<Integer> circularlyLinkedList;

    @BeforeAll
    public void setUpAll() {
        circularlyLinkedList = new CircularlyLinkedList<>();
        System.out.println("Should Initialize the circularlyLinkedList variable for all tests.");
    }

    @BeforeEach
    public void setUp() {
        for (int i = 1; i <= 12; i++)
            circularlyLinkedList.addLast(i);
    }

    @Test
    public void ShouldAddAnIntegerToTheHeadOfTheCircularlyLinkedList() {
        Assertions.assertFalse(circularlyLinkedList.isEmpty());
        Assertions.assertEquals(12, circularlyLinkedList.size());
        Assertions.assertNotNull(circularlyLinkedList);
        Assertions.assertEquals(1, circularlyLinkedList.first());
        Assertions.assertEquals(12, circularlyLinkedList.last());
    }

    @Test
    public void shouldIterateOverTheCircularlyLinkedList() {
        Iterator<Integer> iter = circularlyLinkedList.iterator();
        Assertions.assertTrue(iter.hasNext());
        Assertions.assertEquals(1, iter.next());
        Assertions.assertEquals(2, iter.next());

    }

    @Test
    public void shouldReturnNullWhenTargetIsNull() {
        for (var element : circularlyLinkedList)
            Assertions.assertNotNull(element);
    }

    @Test
    void testHashCode() {
    }

    @Test
    void size() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void first() {
    }

    @Test
    void last() {
    }

    @Test
    void rotate() {
        circularlyLinkedList.rotate();
        Assertions.assertEquals(1, circularlyLinkedList.last());
    }

    @Test
    void addLast() {
    }

    @Test
    void removeFirst() throws EmptyLinkedListException {
        Assertions.assertEquals(12, circularlyLinkedList.removeLast());
        Assertions.assertEquals(11, circularlyLinkedList.size());
        Assertions.assertFalse(circularlyLinkedList.isEmpty());
    }

    @Test
    void removeLast() {
//        Assertions.assertDoesNotThrow(EmptyLinkedListException.class, () -> circularlyLinkedList.removeLast());
    }

    /**
     * Testing for clone contracts:
     * x.clone != x;
     * x.getClass() == x.clone.getClass();
     * x.clone.equals(x)
     * @throws CloneNotSupportedException: Throw an exception.
     */
    @Test
    void testClone() throws CloneNotSupportedException {
        CircularlyLinkedList<Integer> circularlyLinkedList2 = circularlyLinkedList.clone();
        // x.clone != x;
        Assertions.assertNotSame(circularlyLinkedList2, circularlyLinkedList);
        // x.getClass() == x.clone.getClass();
        Assertions.assertSame(circularlyLinkedList2.getClass(), circularlyLinkedList.getClass());
        // x.clone.equals(x)
        Assertions.assertEquals(circularlyLinkedList2, circularlyLinkedList);
    }

    /**
     * Testing for equal contracts:
     * x.equals(x)--> True;
     * x.equals(y) --> True if y.equals(x) --> True
     * if x.equals(y) --> True, y.equals(z) --> True, then x.equals(z) --> True
     * For any non-null reference value x, x.equals(null) --> False
     */
    @Test
    void testEquals() {
    }

    @AfterEach
    public void tearDown() {
        System.out.println("Should execute after each test");
    }

    @AfterAll
    public void tearDownAll() {
        System.out.println("Should execute at the end of the test.");
    }
}