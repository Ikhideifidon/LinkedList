package com.ikhideifidon;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E extends Comparable<E>> implements Iterable<E>, Cloneable {
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> index = head;

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

    private Node<E> head = null, tail = null;
    private int currentSize = 0;

    public int size() {
        return currentSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public E first() {
        if (isEmpty())
            return null;
        return head.data;
    }

    public E last() {
        if (isEmpty()) return null;
        return tail.data;
    }

    // update methods
    public void addFirst(E e) {
        head = new Node<>(e, head);
        if (currentSize == 0)
            tail = head;
        currentSize++;
    }


    public void addLast(E e) {
        Node<E> newest = new Node<>(e);
        if (isEmpty())
            head = newest;
        else
            tail.next = newest;
        tail = newest;
        currentSize++;
    }

    public E removeFirst() {
        if (isEmpty())
            return null;
        E answer = head.data;
        head = head.next;
        currentSize--;
        if (currentSize == 0)
            tail = null;
        return answer;
    }

    public E removeLast() {
        if (isEmpty())
            return null;
        if (head == tail)
            return removeFirst();
        Node<E> current = head;
        Node<E> previous = null;
        while (current != tail) {
            previous = current;
            current = current.next;
        }
        previous.next = null;
        tail = previous;
        currentSize--;
        return current.data;
    }


    public E remove(E obj, Comparator<? super E> cmp) {            // Unnatural Ordering
        Node<E> current = head;
        Node<E> previous = null;
        while (current != null) {
            if (cmp.compare(current.data, obj) == 0) {
                if (current == head)
                    return removeFirst();
                if (current == tail)
                    return removeLast();
                currentSize--;
                previous.next = current.next;
                return current.data;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    public E remove(E obj) {                                        // Natural Ordering
        Node<E> current = head;
        Node<E> previous = null;
        while (current != null) {
            if (obj.compareTo(current.data) == 0) {
                // (obj.equals(current.data)) will not always yield the same result.
                if (current == head)
                    return removeFirst();
                if (current == tail)
                    return removeLast();
                currentSize--;
                previous.next = current.next;
                return current.data;
            }
            previous = current;
            current = current.next;
        }
        return null;
    }

    public void reverse() {
        Node<E> current = head;
        Node<E> previous = null;
        while (current != null) {
            Node<E> next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        head = previous;
    }

    /**
     * This gives a reversed sample of the LinkedList
     */
    public SinglyLinkedList<E> reversed() throws CloneNotSupportedException {
        SinglyLinkedList<E> copied = clone();
        copied.reverse();
        return copied;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        E last = last();

        for (E data: this) {
            sb.append(data);
            // This prevents the trailing delimiter.
            if (data != last)
                sb.append("-->");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
        SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone();

        if (currentSize > 0) {
            other.head = new Node<>(head.data);

            Node<E> otherTail = other.head;
            for (E data: this) {
                Node<E> newest = new Node<>(data);
                otherTail.next = newest;
                otherTail = newest;
            }
        }

        return other;
    }

    public boolean equals(Object o) {
        if (getClass() != o.getClass())
            return false;

        SinglyLinkedList<E> other = (SinglyLinkedList<E>) o;
        if (this.currentSize != other.currentSize)
            return false;

        Iterator<E> walkB = other.iterator();
        for (E dataA : this) {
            if (!dataA.equals(walkB.next()))           //mismatch
                return false;
        }
        return true;
    }

    public void addAll(Iterable<E> listCollection) {
        for (E data: listCollection)
            addLast(data);
    }
}
