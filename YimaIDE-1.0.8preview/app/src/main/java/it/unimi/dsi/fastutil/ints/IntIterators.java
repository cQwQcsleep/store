package it.unimi.dsi.fastutil.ints;

import defpackage.z0e;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class IntIterators {
    public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();

    public static abstract class AbstractIndexBasedIterator extends AbstractIntIterator {
        protected int lastReturned;
        protected final int minPos;
        protected int pos;

        public AbstractIndexBasedIterator(int i, int i2) {
            this.minPos = i;
            this.pos = i2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.PrimitiveIterator
        public void forEachRemaining(java.util.function.IntConsumer intConsumer) {
            while (this.pos < getMaxPos()) {
                int i = this.pos;
                this.pos = i + 1;
                this.lastReturned = i;
                intConsumer.accept(get(i));
            }
        }

        public abstract int get(int i);

        public abstract int getMaxPos();

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.pos < getMaxPos();
        }

        @Override // it.unimi.dsi.fastutil.ints.IntIterator, java.util.PrimitiveIterator.OfInt
        public int nextInt() {
            if (!hasNext()) {
                z0e.a();
                return 0;
            }
            int i = this.pos;
            this.pos = i + 1;
            this.lastReturned = i;
            return get(i);
        }

        @Override // java.util.Iterator, it.unimi.dsi.fastutil.ints.IntListIterator, java.util.ListIterator
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

    public static abstract class AbstractIndexBasedListIterator extends AbstractIndexBasedIterator implements IntListIterator {
        public AbstractIndexBasedListIterator(int i, int i2) {
            super(i, i2);
        }

        public void add(int i) {
            int i2 = this.pos;
            this.pos = i2 + 1;
            add(i2, i);
            this.lastReturned = -1;
        }

        public abstract void add(int i, int i2);

        @Override // it.unimi.dsi.fastutil.BidirectionalIterator, java.util.ListIterator
        public boolean hasPrevious() {
            return this.pos > this.minPos;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.pos;
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return this.pos - 1;
        }

        public int previousInt() {
            if (!hasPrevious()) {
                z0e.a();
                return 0;
            }
            int i = this.pos - 1;
            this.pos = i;
            this.lastReturned = i;
            return get(i);
        }

        @Override // it.unimi.dsi.fastutil.ints.IntListIterator
        public void set(int i) {
            int i2 = this.lastReturned;
            if (i2 != -1) {
                set(i2, i);
            } else {
                g33.a();
            }
        }

        public abstract void set(int i, int i2);
    }

    public static IntIterator unmodifiable(IntIterator intIterator) {
        return new UnmodifiableIterator(intIterator);
    }

    public static int unwrap(IntIterator intIterator, int[] iArr, int i, int i2) {
        int i3;
        if (i2 < 0) {
            ty8.a("The maximum number of elements (", i2, ") is negative");
            return 0;
        }
        if (i < 0 || i + i2 > iArr.length) {
            j2d.a();
            return 0;
        }
        int i4 = i2;
        while (true) {
            i3 = i4 - 1;
            if (i4 == 0 || !intIterator.hasNext()) {
                break;
            }
            iArr[i] = intIterator.nextInt();
            i++;
            i4 = i3;
        }
        return (i2 - i3) - 1;
    }

    public static class EmptyIterator implements IntListIterator, Serializable, Cloneable {
        private static final long serialVersionUID = -7046029254386353129L;

        private Object readResolve() {
            return IntIterators.EMPTY_ITERATOR;
        }

        public Object clone() {
            return IntIterators.EMPTY_ITERATOR;
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public boolean hasNext() {
            return false;
        }

        @Override // it.unimi.dsi.fastutil.BidirectionalIterator, java.util.ListIterator
        public boolean hasPrevious() {
            return false;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return 0;
        }

        @Override // it.unimi.dsi.fastutil.ints.IntIterator, java.util.PrimitiveIterator.OfInt
        public int nextInt() {
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return -1;
        }

        @Override // it.unimi.dsi.fastutil.ints.IntBidirectionalIterator
        public int previousInt() {
            throw new NoSuchElementException();
        }

        @Override // java.util.PrimitiveIterator
        public void forEachRemaining(java.util.function.IntConsumer intConsumer) {
        }

        @Override // it.unimi.dsi.fastutil.ints.IntIterator, java.util.PrimitiveIterator.OfInt, java.util.Iterator
        @Deprecated
        public void forEachRemaining(Consumer<? super Integer> consumer) {
        }
    }

    public static class UnmodifiableIterator implements IntIterator {
        protected final IntIterator i;

        public UnmodifiableIterator(IntIterator intIterator) {
            this.i = intIterator;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.i.hasNext();
        }

        @Override // it.unimi.dsi.fastutil.ints.IntIterator, java.util.PrimitiveIterator.OfInt
        public int nextInt() {
            return this.i.nextInt();
        }

        @Override // java.util.PrimitiveIterator
        public void forEachRemaining(java.util.function.IntConsumer intConsumer) {
            this.i.forEachRemaining(intConsumer);
        }

        @Override // it.unimi.dsi.fastutil.ints.IntIterator, java.util.PrimitiveIterator.OfInt, java.util.Iterator
        @Deprecated
        public void forEachRemaining(Consumer<? super Integer> consumer) {
            this.i.forEachRemaining(consumer);
        }
    }

    public static int unwrap(IntIterator intIterator, int[] iArr) {
        return unwrap(intIterator, iArr, 0, iArr.length);
    }
}
