package com.hs.common;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import java.util.Queue;

import org.junit.jupiter.api.Test;

public class PeekIteratorTest {

    @Test
    public void test_peek() {
        String source = "abcdefg";
        var it = new PeekIterator<Character>(source.chars().mapToObj(c -> (char) c));
        assertEquals('a', it.next());
        assertEquals('b', it.next());

        it.next();
        it.next();

        assertEquals('e', it.next());
        assertEquals('f', it.peek());
        assertEquals('f', it.peek());
        assertEquals('f', it.next());
        assertEquals('g', it.next());
    }

    @Test
    public void test_putback() {
        String source = "abcdefg";
        var it = new PeekIterator<Character>(source.chars().mapToObj(c -> (char) c));
        assertEquals('a', it.next());
        assertEquals('b', it.next());

        it.putBack();

        assertEquals('b', it.peek());
        assertEquals('b', it.peek());
        assertEquals('b', it.next());
        assertEquals('c', it.next());

    }

    @Test
    public void test_qu() {
        Queue<Character> queue = new LinkedList<>();
        queue.add('a');
        queue.add('b');

        while (queue.size() > 0) {
            Character character = queue.poll();
            System.out.println(character.charValue());
        }
    }
}