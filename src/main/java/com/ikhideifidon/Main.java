package com.ikhideifidon;

import java.util.Random;

public class Main {

    public static void main(String[] args) throws CloneNotSupportedException {
        SinglyLinkedList<Integer> singlyLinkedList = new SinglyLinkedList<>();
        Random rand = new Random();
        rand.setSeed(System.currentTimeMillis());
        for (int i = 0; i <= 20; i++)
            singlyLinkedList.addFirst(rand.nextInt(100));
        System.out.println(singlyLinkedList);

        SinglyLinkedList<Integer> clonedLinkedList = singlyLinkedList.clone();
        assert clonedLinkedList.equals(singlyLinkedList) : "Not Equals";

        SinglyLinkedList<Integer> l1 = new SinglyLinkedList<>();
        SinglyLinkedList<Integer> l2 = new SinglyLinkedList<>();
        for (int i = 0; i < 10; i++) {
            l1.addLast(rand.nextInt(10));
        }

        for (int i = 0; i < 10; i++)
            l2.addLast(rand.nextInt(10));
        l2.addLast(9);
        l2.addLast(9);
        l2.addLast(9);
        l2.addLast(9);

        SinglyLinkedList<Integer> l3 = l2.clone();
        System.out.println();
        System.out.println("Linked List 1: " + l1);
        System.out.println("Linked List 2: " + l2);
        System.out.println(SinglyLinkedListExercises.addTwoNumber(l1, l2));
        System.out.println("Linked List 1: " + l1);

        l1.addAll(l3);
        System.out.println(l3);
        System.out.println(l1);
        System.out.println(l1.size());


        // DoublyLinkedList
        System.out.println("DoublyLinkedList Test starts here");
        DoublyLinkedList<Integer> doublyLinkedList = new DoublyLinkedList<>();
        doublyLinkedList.addLast(1);
        doublyLinkedList.addLast(2);
        doublyLinkedList.addLast(3);
        doublyLinkedList.addLast(4);
        doublyLinkedList.addLast(5);
        doublyLinkedList.addLast(6);
        doublyLinkedList.addLast(7);
        doublyLinkedList.addLast(8);


        System.out.println(doublyLinkedList);

        System.out.println(doublyLinkedList);
        System.out.println(doublyLinkedList.size());
        System.out.println(doublyLinkedList.peekFirst());
        DoublyLinkedList<Integer> other = doublyLinkedList.clone();
        System.out.println(other);
        System.out.println(doublyLinkedList);
        System.out.println(doublyLinkedList.equals(other));


        // CircularlyLinked List
        System.out.println("CircularlyLinked List Test starts here");
        CircularlyLinkedList<Integer> circularlyLinkedList = new CircularlyLinkedList<>();
        circularlyLinkedList.addLast(1);
        circularlyLinkedList.addLast(2);
        circularlyLinkedList.addLast(3);
        circularlyLinkedList.addLast(4);
        circularlyLinkedList.addLast(5);
        circularlyLinkedList.addLast(6);
        circularlyLinkedList.addLast(7);
        circularlyLinkedList.addLast(8);
        System.out.println(circularlyLinkedList);

    }
}
