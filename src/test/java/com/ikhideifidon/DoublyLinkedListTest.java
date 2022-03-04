package com.ikhideifidon;

import org.junit.jupiter.api.*;

import static java.lang.System.out;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class DoublyLinkedListTest {

    DoublyLinkedList<Integer> doublyLinkedList;

    @BeforeAll
    public static void setUpAll() {
        out.println("Should initialize doublyLinkedList variable for all test cases.");
    }

    @BeforeEach
    public void setUp() {
        doublyLinkedList = new DoublyLinkedList<>();
        for (int i = 1; i <= 12; i++)
            doublyLinkedList.addLast(i);
    }

    @Test
    void iterator() {
    }

    @Test
    void isEmpty() {
    }

    @Test
    void size() {
    }

    @Test
    void peek() {
    }

    @Test
    void peekFirst() {
    }

    @Test
    void peekLast() {
    }

    @Test
    void addFirst() {
    }

    @Test
    void addLast() {
    }

    @Test
    public void shouldReverseDoublyLinkedList() {
        doublyLinkedList.reverse();
        Assertions.assertEquals(1, doublyLinkedList.peekLast());
        Assertions.assertEquals(12, doublyLinkedList.peekFirst());
        Assertions.assertNotSame("[12 ⇆ 11 ⇆ 10 ⇆ 9 ⇆ 8 ⇆ 7 ⇆ 6 ⇆ 5 ⇆ 4 ⇆ 3 ⇆ 2 ⇆ 1]", doublyLinkedList.toString());        // They do not point to the same memory location.
        doublyLinkedList.reverse();
        out.println("After the second reverse operation :" + doublyLinkedList);
        Assertions.assertEquals("[1 ⇆ 2 ⇆ 3 ⇆ 4 ⇆ 5 ⇆ 6 ⇆ 7 ⇆ 8 ⇆ 9 ⇆ 10 ⇆ 11 ⇆ 12]", doublyLinkedList.toString());

        out.println(doublyLinkedList);
        out.println(doublyLinkedList.size());
        doublyLinkedList.removeLast();
        out.println(doublyLinkedList);
        doublyLinkedList.removeLast();
        out.println(doublyLinkedList);
        out.println(doublyLinkedList.size());
    }
    @Test
    public void performOperationsOnTheReversedDoublyLinkedList() {
        doublyLinkedList.reverse();
        Assertions.assertEquals(12, doublyLinkedList.peekFirst());
        Assertions.assertEquals(1, doublyLinkedList.peekLast());
        Assertions.assertEquals(1, doublyLinkedList.removeLast());
        Assertions.assertEquals(12, doublyLinkedList.removeFirst());

    }

    @Test
    void removeFirst() {
        doublyLinkedList.removeFirst();
        Assertions.assertEquals(11, doublyLinkedList.size());
    }

    @Test
    void removeLast() {
        Assertions.assertEquals(12, doublyLinkedList.removeLast());

    }

    @Test
    void testToString() {
    }

    @Test
    public void testClone() throws CloneNotSupportedException {
        DoublyLinkedList<Integer> d1 = new DoublyLinkedList<>(doublyLinkedList);
        DoublyLinkedList<Integer> d2 = doublyLinkedList.clone();
        Assertions.assertNotSame(d2, doublyLinkedList);
        Assertions.assertEquals(1, d2.removeFirst());
        Assertions.assertEquals(12, d2.removeLast());
        out.println(doublyLinkedList);
        out.println(d2);
        d2.removeLast();
        d2.removeFirst();
        out.println(d2);
//        doublyLinkedList.removeLast();
        out.println(doublyLinkedList);
        d1.removeFirst();
        out.println(doublyLinkedList.size());
        out.println(d1.size());
    }

    @Test
    void cloned() {
    }

    @Test
    void testEquals() {
    }

    @AfterEach
    void tearDown() {
    }

    @AfterAll
    void tearDownAll() {
    }
}