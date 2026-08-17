package com.sun.tools.javac.util;

import java.util.Collections;
import java.util.Iterator;
import java.util.function.Function;
import java.util.function.Predicate;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class Iterators {
    private static final Iterator<?> EMPTY = Collections.emptyIterator();

    public static class CompoundIterator<I, O> implements Iterator<O> {
        private final Function<I, Iterator<O>> converter;
        private Iterator<O> currentIterator = Iterators.emptyIterator();
        private final Iterator<I> inputs;

        public CompoundIterator(Iterable<I> iterable, Function<I, Iterator<O>> function) {
            this.inputs = iterable.iterator();
            this.converter = function;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            while (!this.currentIterator.hasNext()) {
                if (!this.inputs.hasNext()) {
                    return false;
                }
                this.currentIterator = (Iterator) this.converter.apply(this.inputs.next());
            }
            return true;
        }

        @Override // java.util.Iterator
        public O next() {
            while (!this.currentIterator.hasNext() && this.inputs.hasNext()) {
                this.currentIterator = (Iterator) this.converter.apply(this.inputs.next());
            }
            return this.currentIterator.next();
        }
    }

    public static <I, O> Iterator<O> createCompoundIterator(Iterable<I> iterable, Function<I, Iterator<O>> function) {
        return new CompoundIterator(iterable, function);
    }

    public static <E> Iterator<E> createFilterIterator(final Iterator<E> it, final Predicate<E> predicate) {
        return new Iterator<E>() { // from class: com.sun.tools.javac.util.Iterators.1
            private E current = update();

            private E update() {
                while (it.hasNext()) {
                    E e = (E) it.next();
                    if (predicate.test(e)) {
                        return e;
                    }
                }
                return null;
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.current != null;
            }

            @Override // java.util.Iterator
            public E next() {
                E e = this.current;
                if (e != null) {
                    this.current = update();
                    return e;
                }
                z0e.a();
                return null;
            }
        };
    }

    public static <T> Iterator<T> emptyIterator() {
        return (Iterator<T>) EMPTY;
    }
}
