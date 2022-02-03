package com.ikhideifidon;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyLinkedList<E extends Comparable<E>> implements Iterable<E>, Cloneable {

    // Interface Iterable Implementation
    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {
            Node<E> index = header.getNext();

            @Override
            public boolean hasNext() {
                return (index != null);
            }

            @Override
            public E next() {
                if (!hasNext())
                    throw new NoSuchElementException();
                E value = index.getData();
                index = index.next;
                return value;
            }
        };
    }

    // Inner class for Node
    private static class Node<E> {
        private E data;
        private Node<E> prev;
        private Node<E> next;

        public Node() {};
        public Node(E data) { this.data = data; }
        public Node(E data, Node<E> prev) { this.prev = prev; }
        public Node(E data, Node<E> prev, Node<E> next) { this.data = data; this.prev = prev; this.next = next; }

        public void setData(E data) { this.data = data; }
        public void setPrev(Node<E> prev) { this.prev = prev; }
        public void setNext(Node<E> next) { this.next = next; }

        public E getData() { return data; }
        public Node<E> getPrev() { return prev; }
        public Node<E> getNext() { return next; }
    }

    private Node<E> header;
    private Node<E> trailer;
    int currentSize;

    public DoublyLinkedList() {
        header = new Node<>(null, null, null);
        trailer = new Node<>(null, header, null);
        header.setNext(trailer);
        this.currentSize = 0;
    }

    public boolean isEmpty() {
        return currentSize == 0;
    }

    public int size() {
        return currentSize;
    }

    public E peek() {
        /** Retrieves, but does not remove, the head (first element) of this list.*/
        if (isEmpty())
            throw new NullPointerException();
        return header.getNext().getData();
    }

    public E peekFirst() {
        /** Retrieves, but does not remove, the first element of this list, or returns null if this list is empty.*/
        if (isEmpty())
            return null;
        return header.getNext().getData();
    }

    public E peekLast() {
        /** Retrieves, but does not remove, the last element of this list, or returns null if this list is empty. */
        if (isEmpty())
            return null;
        return trailer.getPrev().getData();
    }

    public void addFirst(E element) {
        addBetween(element, header, header.getNext());
    }

    public void addLast(E element) {
        addBetween(element, trailer.getPrev(), trailer);
    }

    public E removeFirst() {
        if (isEmpty())
            throw new NullPointerException();
        return remove(header.getNext());
    }

    private E remove(Node<E> node) {
        Node<E> predecessor = node.getPrev();
        Node<E> successor = node.getNext();
        predecessor.setNext(successor);
        successor.setPrev(predecessor);
        currentSize--;
        return node.getData();
    }


    private void addBetween(E e, Node<E> predecessor, Node<E> successor) {
        Node<E> newest = new Node<>(e, predecessor, successor);
        predecessor.setNext(newest);
        successor.setPrev(newest);
        currentSize++;
    }



    @Override
    public String toString() {
        Node<E> current = header.getNext();
        StringBuilder sb = new StringBuilder("[");
        while (current != trailer) {
            sb.append(current.getData());
            // This prevents the trailing delimiter.
            if (current.getNext() != trailer)
                sb.append(" \u21c6 ");
            current = current.getNext();
        }
        sb.append("]");
        return sb.toString();
    }

    @SuppressWarnings("unchecked")
    public DoublyLinkedList<E> clone() throws CloneNotSupportedException {
        DoublyLinkedList<E> other = (DoublyLinkedList<E>) super.clone();
        if (currentSize > 0) {
            other.header = new Node<>(null, null, null);
            other.trailer = new Node<>(null, other.header, null);
            other.header.setNext(other.trailer);


            Node<E> walk = header.getNext();
            Node<E> otherWalk = other.header;

            while (walk.getNext() != null) {
                Node<E> newest = new Node<>(walk.getData(), otherWalk, otherWalk.getNext());
                otherWalk.getNext().setPrev(newest);
                otherWalk.setNext(newest);
                otherWalk = otherWalk.getNext();
                walk = walk.getNext();
            }
        }
        return other;
    }

    public DoublyLinkedList<E> cloned() {
        // always use inherited Object.clone() to create initial copy
        DoublyLinkedList<E> other = new DoublyLinkedList<>();
        if (currentSize > 0) {
            Node<E> walk = header.getNext();
            for(int i = 0; i < currentSize; i++) {
                other.addLast(walk.getData());
                walk = walk.getNext();
            }
        }
        return other;
    }

    @Override
    @SuppressWarnings({"rawtypes"})
    public boolean equals(Object o) {
        // Returns true based on certain conditions.
        if (o == null) { return false; }
        if (getClass() != o.getClass()) { return false; }
        DoublyLinkedList other = (DoublyLinkedList) o;
        if (currentSize != other.currentSize) { return false; }
        Node walk = header.getNext();
        Node otherWalk = other.header.getNext();
        while (walk != trailer) {
            if (!walk.getData().equals(otherWalk.getData()))
                return false;
            walk = walk.getNext();
            otherWalk = otherWalk.getNext();
        }
        return true;
    }

}
