package com.hs.common;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.stream.Stream;

public class PeekIterator<T> implements Iterator<T> {

    private final Iterator<T> it;
    private LinkedList<T> queueCache = new LinkedList<>();
    private LinkedList<T> putBackStack = new LinkedList<>();
    private static final int CACHE_SIZE = 10;

    private T endToken;

    public PeekIterator(Stream<T> stream) {
        this.it = stream.iterator();
        this.endToken = null;
    }

    public PeekIterator(Stream<T> stream, T endToken) {
        this.it = stream.iterator();
        this.endToken = endToken;
    }

    public T peek() {
        if (this.putBackStack.size() > 0) {
            return this.putBackStack.getFirst();
        }
        if (!it.hasNext()) {
            return endToken;
        }
        T val = next();
        this.putBack();
        return val;
    }

    //A->B->C->D
    //d c b a
    public void putBack() {
        if (this.queueCache.size() > 0) {
            this.putBackStack.push(this.queueCache.pollLast());
        }
    }

    @Override
    public boolean hasNext() {
        return endToken != null || this.putBackStack.size() > 0 || it.hasNext();
    }

    @Override
    public T next() {
        T val;
        if (this.putBackStack.size() > 0) {
            val = this.putBackStack.pop();
        } else {
            if (!it.hasNext()) {
                T tmp = endToken;
                endToken = null;
                return tmp;
            }
            val = it.next();
        }
        while (queueCache.size() > CACHE_SIZE - 1) {
            queueCache.poll();
        }
        queueCache.add(val);
        return val;
    }
}
