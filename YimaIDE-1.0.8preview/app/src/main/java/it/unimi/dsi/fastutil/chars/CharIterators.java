package it.unimi.dsi.fastutil.chars;

import defpackage.z0e;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class CharIterators {
    public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();

    public static abstract class AbstractIndexBasedIterator extends AbstractCharIterator {
        protected int lastReturned;
        protected final int minPos;
        protected int pos;

        public AbstractIndexBasedIterator(int i, int i2) {
            this.minPos = i;
            this.pos = i2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // it.unimi.dsi.fastutil.chars.CharIterator, java.util.PrimitiveIterator
        public void forEachRemaining(CharConsumer charConsumer) {
            while (this.pos < getMaxPos()) {
                int i = this.pos;
                this.pos = i + 1;
                this.lastReturned = i;
                charConsumer.accept(get(i));
            }
        }

        public abstract char get(int i);

        public abstract int getMaxPos();

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.pos < getMaxPos();
        }

        @Override // it.unimi.dsi.fastutil.chars.CharIterator
        public char nextChar() {
            if (!hasNext()) {
                z0e.a();
                return (char) 0;
            }
            int i = this.pos;
            this.pos = i + 1;
            this.lastReturned = i;
            return get(i);
        }

        @Override // java.util.Iterator, it.unimi.dsi.fastutil.chars.CharListIterator, java.util.ListIterator
        public void remove() {
            int i = this.lastReturned;
            if (i == -1) {
                g33.a();
                return;
            }
            remove(i);
            int i2 = this.lastReturned;
            int i3 = this.pos;
            if (i2 < i3) {
                this.pos = i3 - 1;
            }
            this.lastReturned = -1;
        }

        public abstract void remove(int i);
    }

    public static abstract class AbstractIndexBasedListIterator extends AbstractIndexBasedIterator implements CharListIterator {
        public AbstractIndexBasedListIterator(int i, int i2) {
            super(i, i2);
        }

        public void add(char c) {
            int i = this.pos;
            this.pos = i + 1;
            add(i, c);
            this.lastReturned = -1;
        }

        public abstract void add(int i, char c);

        @Override // it.unimi.dsi.fastutil.BidirectionalIterator, java.util.ListIterator
        public boolean hasPrevious() {
            return this.pos > this.minPos;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.pos;
        }

        @Override // it.unimi.dsi.fastutil.chars.CharBidirectionalIterator
        public char previousChar() {
            if (!hasPrevious()) {
                z0e.a();
                return (char) 0;
            }
            int i = this.pos - 1;
            this.pos = i;
            this.lastReturned = i;
            return get(i);
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.pos - 1;
        }

        @Override // it.unimi.dsi.fastutil.chars.CharListIterator
        public void set(char c) {
            int i = this.lastReturned;
            if (i != -1) {
                set(i, c);
            } else {
                g33.a();
            }
        }

        public abstract void set(int i, char c);
    }

    public static int unwrap(CharIterator charIterator, char[] cArr, int i, int i2) {
        int i3;
        if (i2 < 0) {
            ty8.a("The maximum number of elements (", i2, ") is negative");
            return 0;
        }
        if (i < 0 || i + i2 > cArr.length) {
            j2d.a();
            return 0;
        }
        int i4 = i2;
        while (true) {
            i3 = i4 - 1;
            if (i4 == 0 || !charIterator.hasNext()) {
                break;
            }
            cArr[i] = charIterator.nextChar();
            i++;
            i4 = i3;
        }
        return (i2 - i3) - 1;
    }

    public static class EmptyIterator implements CharListIterator, Serializable, Cloneable {
        private static final long serialVersionUID = -7046029254386353129L;

        private Object readResolve() {
            return CharIterators.EMPTY_ITERATOR;
        }

        public Object clone() {
            return CharIterators.EMPTY_ITERATOR;
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public boolean hasNext() {
            return false;
        }

        @Override // it.unimi.dsi.fastutil.BidirectionalIterator, java.util.ListIterator
        public boolean hasPrevious() {
            return false;
        }

        @Override // it.unimi.dsi.fastutil.chars.CharIterator
        public char nextChar() {
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return 0;
        }

        @Override // it.unimi.dsi.fastutil.chars.CharBidirectionalIterator
        public char previousChar() {
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return -1;
        }

        @Override // it.unimi.dsi.fastutil.chars.CharIterator, java.util.Iterator
        @Deprecated
        public void forEachRemaining(Consumer<? super Character> consumer) {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // it.unimi.dsi.fastutil.chars.CharIterator, java.util.PrimitiveIterator
        public void forEachRemaining(CharConsumer charConsumer) {
        }
    }

    public static int unwrap(CharIterator charIterator, char[] cArr) {
        return unwrap(charIterator, cArr, 0, cArr.length);
    }
}
