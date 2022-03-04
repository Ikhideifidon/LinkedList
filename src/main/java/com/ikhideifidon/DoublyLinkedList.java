package com.ikhideifidon;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E extends Comparable<E>> implements Iterable<E>, Cloneable {

    // Interface Iterable Implementation
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> current = head.next;

            @Override
            public boolean hasNext() {
                return current != tail;
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                E value = current.data;
                current = current.next;
                return value;
            }
        };
    }

    // Inner class for Node
    private static class Node<E> {
        @SuppressWarnings("FieldMayBeFinal")
        private E data;
        private Node<E> prev, next;

        public Node(E data) { this(data, null, null); }             // Not encouraging data to be null.
        public Node(E data, Node<E> prev) { this(data, prev, null); }
        public Node(E data, Node<E> prev, Node<E> next) { this.data = data; this.prev = prev; this.next = next; }
    }

    private Node<E>
            head = new Node<>(null),
            tail = new Node<>(null, head);
    int currentSize = 0;

    public DoublyLinkedList() {
        head.next = tail;
    }

    @SuppressWarnings("CopyConstructorMissesField")
    public DoublyLinkedList(DoublyLinkedList<E> that) {
        if (that.head.next == null) {
            head.next = null;
            tail.prev = null;
            currentSize = 0;
        } else {
            // Make a deep copy of the subsequent nodes.
            for (E data : that)
                // private method addBetween creates new node using the data at constant time.
                addBetween(data, tail.prev, tail);
            currentSize = that.currentSize;
        }
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public int size() {
        return currentSize;
    }

    /**
     * Retrieves, but does not remove, the head (first element) of this list.
     * */
    public E peek() {
        if (isEmpty())
            throw new NoSuchElementException();
        return head.next.data;
    }

    /**
     * Retrieves, but does not remove, the first element of this list,
     * or returns null if this list is empty.
     * @return First element in the list if any.
     */
    public E peekFirst() {
        if (isEmpty())
            return null;
        return head.next.data;
    }

    /**
     * Retrieves, but does not remove, the last element of this list,
     * or returns null if this list is empty.
     *  @return last element in the list if any.
     * */
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
            throw new EmptyLinkedListException("List is empty");
        return remove(head.next);
    }

    public E removeLast() {
        if (isEmpty())
            throw new EmptyLinkedListException("List is empty");
        return remove(tail.prev);
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

    public void reverse() {
        if (head.next != tail) {
            Node<E> current, previous;
            current = head.next;
            previous = tail;
            while (current != tail) {
                Node<E> next = current.next;
                current.next = previous;
                previous.prev = current;
                previous = current;
                current = next;
            }
            // head ⇆ 1 ⇆ 2 ⇆ 3 ⇆ 4 ⇆ 5 ⇆ tail--> Ø

            //        5 ⇆ 4 ⇆ 3 ⇆ 2 ⇆ 1 ⇆ tail --> Ø
            //        ^
            //        |
            //      previous
            previous.prev = head;
            head.next = previous;
        }

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (E data : this) {
            sb.append(data);
            // This prevents the trailing delimiter.
            if (data != peekLast())
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

           for (E data : this) {
                Node<E> newest = new Node<>(data, otherWalk, otherWalk.next);
                otherWalk.next.prev = newest;
                otherWalk.next = newest;
                otherWalk = otherWalk.next;
            }
        }
        return other;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)              // Check if they both point to the same memory address.
             return true;
        // Returns true based on certain conditions.
        if (o == null) { return false; }
        // Note that at runtime, I cannot detect if Object o has a matching type.
        // This check is made within the loop via equals on the elements and
        // relying on that as well as the outer type check.
        // A compile-time exception is thrown for incompatible types
        if (!(o instanceof DoublyLinkedList<?> other))
            return false;
        if (currentSize != other.currentSize)
            return false;
        Iterator<?> otherWalk = other.iterator();
        for (E data : this) {
            // Mismatch results if they both have incompatible types Or same type with different values.
            if (!data.equals(otherWalk.next()))
                return false;
        }
        return true;
    }

}
