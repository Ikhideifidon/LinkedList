package com.ikhideifidon;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E extends Comparable<E>> implements Iterable<E>, Cloneable {

    // Interface Iterable Implementation
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> index = head.next;

            @Override
            public boolean hasNext() {
                return index != null;
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                E value = index.data;
                index = index.next;
                return value;
            }
        };
    }

    // Inner class for Node
    private static class Node<E> {
        public E data;
        public Node<E> prev, next;

        public Node(E data) { this(data, null); }
        public Node(E data, Node<E> prev) { this(data, prev, null); }
        public Node(E data, Node<E> prev, Node<E> next) {
            this.data = data; this.prev = prev; this.next = next;
        }
    }

    private Node<E>
            head = new Node<>(null),
            tail = new Node<>(null, head);
    int currentSize = 0;

    public DoublyLinkedList() {
        head.next = tail;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public int size() {
        return currentSize;
    }

    /** Retrieves, but does not remove, the head (first element) of this list.*/
    public E peek() {
        if (isEmpty())
            throw new NoSuchElementException();
        return head.next.data;
    }

    /** Retrieves, but does not remove, the first element of this list, or returns null if this list is empty.*/
    public E peekFirst() {
        if (isEmpty())
            return null;
        return head.next.data;
    }

    /** Retrieves, but does not remove, the last element of this list, or returns null if this list is empty. */
    public E peekLast() {
        if (isEmpty())
            return null;
        return tail.prev.data;
    }

    public void addFirst(E element) {
        addBetween(element, head, head.next);
    }

    public void addLast(E element) {
        addBetween(element, tail.prev, tail);
    }

    public E removeFirst() {
        if (isEmpty())
            throw new NoSuchElementException();
        return remove(head.next);
    }

    private E remove(Node<E> node) {
        Node<E> predecessor = node.prev;
        Node<E> successor = node.next;
        predecessor.next = successor;
        successor.prev = predecessor;
        currentSize--;
        return node.data;
    }

    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.next = newest;
        successor.prev = newest;
        currentSize++;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");

        E last = peekLast();

        for (E data: this) {
            sb.append(data);
            // This prevents the trailing delimiter.
            if (data != last)
                sb.append(" \u21c6 ");
        }
        sb.append("]");
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    public DoublyLinkedList<E> clone() throws CloneNotSupportedException {
        DoublyLinkedList<E> other = (DoublyLinkedList<E>) super.clone();

        if (currentSize > 0) {
            other.head = new Node<>(null);
            other.tail = new Node<>(null, other.head);
            other.head.next = other.tail;

            Node<E> otherWalk = other.head;

            for (E data: this) {
                Node<E> newest = new Node<>(data, otherWalk, otherWalk.next);
                otherWalk.next.prev = newest;
                otherWalk.next = newest;
                otherWalk = otherWalk.next;
            }
        }
        return other;
    }

    public DoublyLinkedList<E> cloned() {
        // always use inherited Object.clone() to create initial copy
        DoublyLinkedList<E> other = new DoublyLinkedList<>();
        if (currentSize > 0) {
            Node<E> walk = head.next;
            for(int i = 0; i < currentSize; i++) {
                other.addLast(walk.data);
                walk = walk.next;
            }
        }
        return other;
    }

    @Override
    public boolean equals(Object o) {
        // Returns true based on certain conditions.
        if (o == null) { return false; }
        if (getClass() != o.getClass()) { return false; }
        DoublyLinkedList<E> other = (DoublyLinkedList<E>) o;
        if (currentSize != other.currentSize) { return false; }

        Iterator<E> otherWalk = other.iterator();
        for (E data: this) {
            if (!data.equals(otherWalk.next()))
                return false;
        }
        return true;
    }
}
