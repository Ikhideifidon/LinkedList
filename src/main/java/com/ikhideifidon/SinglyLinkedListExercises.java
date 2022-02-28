package com.ikhideifidon;

import org.jetbrains.annotations.NotNull;

import java.util.Iterator;

public class SinglyLinkedListExercises {
    public static SinglyLinkedList<Integer> addTwoNumber(
        @NotNull Iterable<Integer> us,
        @NotNull Iterable<Integer> other
    ) {
        SinglyLinkedList<Integer> result = new SinglyLinkedList<>();
        Iterator<Integer> usWalk = us.iterator(),
                otherWalk = other.iterator();

        int carryOver = 0;

        do {
            if (usWalk.hasNext())
                carryOver += usWalk.next();
            if (otherWalk.hasNext())
                carryOver += otherWalk.next();

            result.addLast(carryOver % 10);
            carryOver /= 10;
        } while (carryOver != 0);

        return result;
    }
}
