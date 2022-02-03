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
                return (index != null);
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
        private E data;
        private Node<E> next;

        public Node() { }
        public Node(E data) { this.data = data; }

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

        public void setData(E data) {
            this.data = data;
        }

        public void setNext(Node<E> next) {
            this.next = next;
        }

        public E getData() {
            return data;
        }

        public Node<E> getNext() {
            return next;
        }


    }

    private Node<E> tail;
    private int currentSize;

    public CircularlyLinkedList() {
        tail = null;
        currentSize = 0;
    }

    // Access Methods
    public int size() { return currentSize; }
    public boolean isEmpty() { return currentSize == 0; }
    public E first() {
        if (isEmpty())
            return null;
        return tail.getNext().getData();
    }

    public E last() {
        if (isEmpty())
            return null;
        return tail.getData();
    }

    // Update Methods
    public void rotate() {
        if (tail != null)
            tail = tail.getNext();
    }

    public void addFirst(E element) {
        if (isEmpty()) {
            tail = new Node<>(element, null);
            tail.setNext(tail);                         // Self linking for circular linked list.
        } else {
            Node<E> newest = new Node<>(element, tail.getNext());
            tail.setNext(newest);
        }
        currentSize++;

    }

    public void addLast(E element) {
        addFirst(element);
        tail = tail.getNext();
    }

    public E removeFirst() {
        if (isEmpty())
            return null;
        Node<E> head = tail.getNext();
        if (head == tail)
            tail = null;
        else tail.setNext(head.getNext());
        currentSize--;
        return head.getData();
    }

    @Override
    public String toString() {
        Node<E> head = tail.getNext();
        Node<E> current = head;
        StringBuilder sb = new StringBuilder("[");
        while (current != tail) {
            sb.append(current.getData());
            // This prevents the trailing delimiter.
            if (current.next != null)
                sb.append("-->");
            current = current.next;
        }
        sb.append(current.getData());
        sb.append("]");
        return sb.toString();
    }

}
