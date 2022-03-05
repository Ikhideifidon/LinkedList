package com.ikhideifidon;


import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class SinglyLinkedList<E extends Comparable<E>> implements Iterable<E>, Cloneable {
    @Override
    public Iterator<E> iterator() {
        return new Iterator<>() {
            Node<E> current = head;

            @Override
            public boolean hasNext() {
                return current != null;
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

    private static class Node<E> {
        @SuppressWarnings("FieldMayBeFinal")
        private E data;
        private Node<E> next;

        public Node(E data) { this(data, null); }

        public Node(E data, Node<E> next) {
            this.data = data;
            this.next = next;
        }

    }

    private Node<E> head = null, tail = null;
    private int currentSize = 0;

    public SinglyLinkedList() { }                   // constructs an initially empty linkedList.

    /**
     * A deep copy constructor
     * @param that : A SinglyLinkedList to copy.
     */
    @SuppressWarnings("CopyConstructorMissesField")
    public SinglyLinkedList(SinglyLinkedList<E> that) {
        if (that.head == null) {
            tail = null;
            head = null;
            currentSize = 0;
        } else {
            // Make a deep copy of the subsequent nodes.
            for (E data : that)
                addLast(data);
            currentSize = that.currentSize;
        }
    }


    public int size() {
        return currentSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public E first() {
        if (isEmpty())
            throw new EmptyLinkedListException();
        return head.data;
    }

    public E last() {
        if (isEmpty())
            throw new EmptyLinkedListException();
        return tail.data;
    }

    // update methods
    public void addFirst(E e) {
        if (e != null) {
            head = new Node<>(e, head);
            if (currentSize == 0)
                tail = head;
            currentSize++;
        }
    }


    public void addLast(E e) {
        if (e != null) {
            Node<E> newest = new Node<>(e, null);
            if (isEmpty())
                head = newest;
            else
                tail.next = newest;
            tail = newest;
            currentSize++;
        }
    }

    public E removeFirst() {
        if (isEmpty())
            throw new EmptyLinkedListException();
        E answer = head.data;
        head = head.next;
        currentSize--;
        if (currentSize == 0)
            tail = null;
        return answer;
    }

    public E removeLast() {
        if (isEmpty())
            throw new EmptyLinkedListException();
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

    /**
     * This method attempts to remove and return a given data if present in the SinglyLinkedList.
     * A null value is returned if the given data is not found in the SinglyLinkedList.
     * @param obj : The data to be removed
     * @return : The removed data.
     * @throws EmptyLinkedListException : An EmptyLinkedList RuntimeException
     */
    public E tryRemove(E obj) {                                        // Natural Ordering
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

    /**
     * The remove method removes and return the given data if present, otherwise a
     * NoSuchElementException is thrown
     * @param obj : The data to be removed
     * @param cmp : The Comparator to be applied.
     * @return : The removed data.
     * @throws EmptyLinkedListException : An EmptyLinkedList RuntimeException
     */

    @SuppressWarnings("unused")
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
        throw new NoSuchElementException();
    }

    /**
     * The remove method removes and return the given data if present, relying on the
     * natural ordering, otherwise a NoSuchElementException is thrown.
     * @param obj : The data to be removed
     * @return : The removed data.
     * @throws EmptyLinkedListException : An EmptyLinkedList RuntimeException.
     */
    @SuppressWarnings("unused")
    public E remove(E obj) {                                        // Natural Ordering
        E result = tryRemove(obj);
        if (result == null)
            throw new NoSuchElementException();
        return result;
    }

    /**
     * The reverse method reverses the linked list in-place
     */
    public void reverse() {
        Node<E> current, previous;
        current = head;
        previous = null;
        while (current != null) {
            Node<E> next = current.next;
            current.next = previous;
            previous = current;
            current = next;
        }
        // Reset both the head and the tail pointers.
        tail = head;
        head = previous;
    }

    /**
     * This gives a reversed sample of the LinkedList
     */
    public SinglyLinkedList<E> reversed() throws CloneNotSupportedException {
        // Make a copy of the linked list and then reverse in-place.
        SinglyLinkedList<E> cloned = this.clone();
        cloned.reverse();
        return cloned;

    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("[");
        for (E data : this) {
            sb.append(data);
            // This prevents the trailing delimiter.
            if (data != last())
                sb.append("-->");
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    @SuppressWarnings("unchecked")
    public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
        SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone();
        if (currentSize > 0) {
            other.head = new Node<>(head.data);
            Node<E> otherTail = other.head;
            for (E data : this) {
                if (data != first()) {
                    Node<E> newest = new Node<>(data);
                    otherTail.next = newest;
                    otherTail = newest;
                }
            }
            other.tail = otherTail;
        }
        return other;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (o instanceof SinglyLinkedList<?> other) {
            if (this.currentSize != other.currentSize)
                return false;
            Iterator<?> walkB = other.iterator();
            for (E dataA : this) {
                if (!dataA.equals(walkB.next()))           //mismatch
                    return false;
            }
            return true;
        }
        return false;
    }

    private int hashCode;
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


    /**
     * Remove the nth node from the end of the Singly Linked List and return its head.
     * @param n : nth node from the end of the Singly linked list.
     */
    public void removeNthNodeFromTheEnd(int n) throws EmptyLinkedListException {
        if (isEmpty())
            throw new EmptyLinkedListException("Linked List is empty");
        // n must be within the bounds of the list. The list is assumed to be 0-indexed.
        if (0 > n || n > size())
            throw new IndexOutOfBoundsException(n + " is out of bounds for this Linked List");
        if (n == size())
            removeFirst();
        else if (n == 1)
            removeLast();
        else {
            Node<E> fast = head;            // Using head.data is my choice
            Node<E> slow = fast;
            int linkCount = 0;
            while(fast.next != null) {
                fast = fast.next;
                linkCount++;
                if (linkCount > n )
                    slow = slow.next;
            }
            assert slow.next != null;
            slow.next = slow.next.next;
            currentSize--;
        }
    }

    /**
     * The addAll mutating method concatenate a given iterable to the end of the singlyLinkedList.
     * @param listCollection --> A collection of Iterables to be added
     */
    public void addAll(Iterable<? extends E> listCollection) {
        for (E data: listCollection)
            addLast(data);
    }
}