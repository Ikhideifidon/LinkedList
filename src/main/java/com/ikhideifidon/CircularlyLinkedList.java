package com.ikhideifidon;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularlyLinkedList<E extends Comparable<E>> implements Iterable<E>, Cloneable {
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> index = tail.next;

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

    private static class Node<E> {
        public E data;
        public Node<E> next;

        public Node(E data) { this(data, null); }

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<E> tail = null;
    private int currentSize = 0;

    // Access Methods
    public int size() { return currentSize; }

    public boolean isEmpty() { return currentSize == 0; }

    public E first() {
        if (isEmpty())
            return null;
        return tail.next.data;
    }

    public E last() {
        if (isEmpty())
            return null;
        return tail.data;
    }

    // Update Methods
    public void rotate() {
        if (tail != null)
            tail = tail.next;
    }

    public void addFirst(E element) {
        if (isEmpty()) {
            tail = new Node<>(element);
            tail.next = tail;                         // Self linking for circular linked list.
        }
        else {
            tail.next = new Node<>(element, tail.next);
        }
        currentSize++;
    }

    public void addLast(E element) {
        addFirst(element);
        tail = tail.next;
    }

    public E removeFirst() {
        if (isEmpty())
            return null;
        Node<E> head = tail.next;
        if (head == tail)
            tail = null;
        else tail.next = head.next;
        currentSize--;
        return head.data;
    }

    public E removeLast() {
        throw new UnsupportedOperationException();
    }

    @Override
    public String toString() {
        Node<E> current = tail.next;
        StringBuilder sb = new StringBuilder("[");
        while (current != tail) {
            sb.append(current.data);
            // This prevents the trailing delimiter.
            if (current.next != null)
                sb.append("-->");
            current = current.next;
        }
        sb.append(current.data);
        sb.append("]");
        return sb.toString();
    }

    public CircularlyLinkedList<E> clone() {
        throw new UnsupportedOperationException();
    }
}
