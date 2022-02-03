package com.ikhideifidon;

import org.jetbrains.annotations.NotNull;

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

    private Node<E> head;
    private Node<E> tail;
    private int currentSize;

    public SinglyLinkedList() {                    // constructs an initially empty linkedList.
        head = null;
        tail = null;
        currentSize = 0;
    }

    public int size() {
        return currentSize;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public E first() {
        if (isEmpty())
            return null;
        return head.getData();
    }

    public E last() {
        if (isEmpty()) return null;
        return tail.getData();
    }

    // update methods
    public void addFirst(E e) {
        head = new Node<>(e, head);
        if (currentSize == 0)
            tail = head;
        currentSize++;
    }


    public void addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty())
            head = newest;
        else
            tail.setNext(newest);
        tail = newest;
        currentSize++;
    }

    public E removeFirst() {
        if (isEmpty())
            return null;
        E answer = head.getData();
        head = head.getNext();
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
        Node<E> temp = head;
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

    public SinglyLinkedList<E> reversed() throws CloneNotSupportedException {
        /**
         * This gives a reversed sample of the LinkedList
         */
        SinglyLinkedList<E> copied = this.clone();
        copied.reverse();
        return copied;

    }

    @Override
    public String toString() {
        Node<E> current = head;
        StringBuilder sb = new StringBuilder("[");
        while (current != null) {
            sb.append(current.data);
            // This prevents the trailing delimiter.
            if (current.next != null)
                sb.append("-->");
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    @Override
    public SinglyLinkedList<E> clone() throws CloneNotSupportedException {
        SinglyLinkedList<E> other = (SinglyLinkedList<E>) super.clone();
        if (currentSize > 0) {
            other.head = new Node<>(head.getData(), null);
            Node<E> walk = head.getNext();
            Node<E> otherTail = other.head;
            while (walk != null) {
                Node<E> newest = new Node<>(walk.getData(), null);
                otherTail.setNext(newest);
                otherTail = newest;
                walk = walk.getNext();
            }

        }
        return other;
    }

    @SuppressWarnings({ "rawtypes" })
    public boolean equals(Object o) {
        if (o instanceof SinglyLinkedList other) {
            other = (SinglyLinkedList) o;
            if (this.currentSize != other.currentSize)
                return false;
            Node walkA = head;
            Node walkB = other.head;
            while (walkA != null) {
                if (!walkA.getData().equals(walkB.getData()))           //mismatch
                    return false;
                walkA = walkA.getNext();
                walkB = walkB.getNext();
            }
            return true;
        }
        return false;
    }

    public SinglyLinkedList addTwoNumber(@NotNull SinglyLinkedList<Integer> other) {
        Node<Integer> current = (Node<Integer>) head;
        SinglyLinkedList<Integer> result = new SinglyLinkedList<>();
        int size = 0;
        int carryOver = 0;
        while (current != null || other.head != null || carryOver != 0) {
            if (current != null) {
                carryOver +=current.getData();
                current = current.getNext();
            }
            if (other.head != null) {
                carryOver += other.head.getData();
                other.head = other.head.getNext();
            }
            Node<Integer> newest = new Node<>(carryOver % 10);
            if (size == 0)
                result.head = newest;
            else
                result.tail.setNext(newest);
            result.tail = newest;
            carryOver /= 10;
            size++;
        }
        return result;
    }

    public boolean addAll(SinglyLinkedList<E> listCollection) {
        if (listCollection.isEmpty())
            return false;
        Node<E> current = listCollection.head;
        while (current != null) {
            Node<E> newest = new Node<>(current.getData());
            tail.setNext(newest);
            tail = newest;
            currentSize++;
            current = current.getNext();
        }
        return true;
    }
}