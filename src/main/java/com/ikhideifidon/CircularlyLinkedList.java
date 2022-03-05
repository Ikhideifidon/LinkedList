package com.ikhideifidon;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class CircularlyLinkedList<E extends Comparable<E>> implements Iterable<E>, Cloneable {
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> current = tail.next;
            int counter = 0;

            @Override
            public boolean hasNext() {
                return counter != size();
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                counter++;
                E value = current.data;
                current = current.next;
                return value;
            }
        };
    }

    private static class Node<E> {
        @SuppressWarnings("FieldMayBeFinal")
        private E data;
        private Node<E> next;

        public Node(E data) { this(data, null); }               // Delegation

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }
    }

    private Node<E> tail = null;
    private int currentSize = 0;

    public CircularlyLinkedList() {}                     // Default Constructor for CircularlyLinkedList.

    @SuppressWarnings("CopyConstructorMissesField")
    public CircularlyLinkedList(CircularlyLinkedList<E> that) {     // Copy Constructor
        if (that.tail == null) {
            tail = null;
            currentSize = 0;
        } else {
            // Make a deep copy of the subsequent nodes.
            for (E data : that) {
                // addLast method creates new node using the data at constant time.
                addFirst(data);
                tail = tail.next;
            }
            currentSize = that.currentSize;
        }

    }

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
        } else {
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
            throw new EmptyLinkedListException();
        Node<E> head = tail.next;
        if (head == tail)
            tail = null;
        else tail.next = head.next;
        currentSize--;
        return head.data;
    }

    public E removeLast() {
        if (isEmpty())
            throw new EmptyLinkedListException();
        Node<E> head = tail.next;
        if (head == tail)                               // A single element circular linked list.
            return removeFirst();
        else {
            Node<E> previous = null;
            Node<E> current = tail.next;
            while (current != tail) {
                previous = current;
                current = current.next;
            }
            previous.next = tail.next;                          // Self linking for circular list
            tail = previous;
            currentSize--;
            return current.data;
        }
    }

    public void reverse() {
        tail = tail.next;
        Node<E> current, previous, head;
        head = tail.next;
        current = head;
        previous = tail;
        while (current != tail) {
            Node<E> next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        tail.next = previous;
    }

    @SuppressWarnings("unchecked")
    public CircularlyLinkedList<E> clone() throws CloneNotSupportedException {
        CircularlyLinkedList<E> that = (CircularlyLinkedList<E>) super.clone();
        that.tail = new Node<>(tail.next.data, tail.next);      // The head node of the cloned is the same as the head node of the original

        for (E data : this) {
            if (data != first()) {
                Node<E> newest = new Node<>(data, that.tail.next);
                that.tail.next = newest;
                that.tail = newest;
            }
        }
        return that;
    }



    private int hashCode;

    /**
     * Object HashCode Override using caching.
     * @return int
     */
    @Override
    public int hashCode() {
        int result = hashCode;
        if (result == 0) {
            for (E element : this)
                result = 31 * result + (element == null ? 0 : element.hashCode());
            hashCode = result;
        }
        return result;
    }



    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CircularlyLinkedList<?> that))
            return false;
        if (that.size() != size())
            return false;
        Iterator<?> walkB = that.iterator();
        for (E element : this) {
            if (!element.equals(walkB.next()))                   // Mismatch either as incompatible types or same type with different values.
                return false;
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
       for (E data : this) {
            sb.append(data);
            if (data != last())
                sb.append("-->");
        }
        sb.append("]");
        return sb.toString();
    }
}
