package com.ikhideifidon;

import org.junit.jupiter.api.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CircularlyLinkedListTest {

    CircularlyLinkedList<Integer> circularlyLinkedList;

    @BeforeAll
    public void setUpAll() {
        System.out.println("Should print before all tests.");
    }

    @BeforeEach
    public void setUp() {
        circularlyLinkedList = new CircularlyLinkedList<>();
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
    }

    @Test
    void addLast() {
    }

    @Test
    void removeFirst() throws EmptyLinkedListException {
        Assertions.assertEquals(12, circularlyLinkedList.removeLast());
    }

    @Test
    void removeLast() {
        Assertions.assertThrows(EmptyLinkedListException.class, () -> circularlyLinkedList.removeLast());
    }

    @Test
    void testClone() {
    }

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