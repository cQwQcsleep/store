package it.unimi.dsi.fastutil.longs;

import defpackage.z0e;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class LongIterators {
    public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();

    public static abstract class AbstractIndexBasedIterator extends AbstractLongIterator {
        protected int lastReturned;
        protected final int minPos;
        protected int pos;

        public AbstractIndexBasedIterator(int i, int i2) {
            this.minPos = i;
            this.pos = i2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.PrimitiveIterator
        public void forEachRemaining(java.util.function.LongConsumer longConsumer) {
            while (this.pos < getMaxPos()) {
                int i = this.pos;
                this.pos = i + 1;
                this.lastReturned = i;
                longConsumer.accept(get(i));
            }
        }

        public abstract long get(int i);

        public abstract int getMaxPos();

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.pos < getMaxPos();
        }

        @Override // it.unimi.dsi.fastutil.longs.LongIterator, java.util.PrimitiveIterator.OfLong
        public long nextLong() {
            if (!hasNext()) {
                z0e.a();
                return 0L;
            }
            int i = this.pos;
            this.pos = i + 1;
            this.lastReturned = i;
            return get(i);
        }

        @Override // java.util.Iterator, it.unimi.dsi.fastutil.longs.LongListIterator, java.util.ListIterator
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

    public static abstract class AbstractIndexBasedListIterator extends AbstractIndexBasedIterator implements LongListIterator {
        public AbstractIndexBasedListIterator(int i, int i2) {
            super(i, i2);
        }

        public abstract void add(int i, long j);

        public void add(long j) {
            int i = this.pos;
            this.pos = i + 1;
            add(i, j);
            this.lastReturned = -1;
        }

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

        public long previousLong() {
            if (!hasPrevious()) {
                z0e.a();
                return 0L;
            }
            int i = this.pos - 1;
            this.pos = i;
            this.lastReturned = i;
            return get(i);
        }

        public abstract void set(int i, long j);

        @Override // it.unimi.dsi.fastutil.longs.LongListIterator
        public void set(long j) {
            int i = this.lastReturned;
            if (i != -1) {
                set(i, j);
            } else {
                g33.a();
            }
        }
    }

    public static LongIterator unmodifiable(LongIterator longIterator) {
        return new UnmodifiableIterator(longIterator);
    }

    public static int unwrap(LongIterator longIterator, long[] jArr, int i, int i2) {
        int i3;
        if (i2 < 0) {
            ty8.a("The maximum number of elements (", i2, ") is negative");
            return 0;
        }
        if (i < 0 || i + i2 > jArr.length) {
            j2d.a();
            return 0;
        }
        int i4 = i2;
        while (true) {
            i3 = i4 - 1;
            if (i4 == 0 || !longIterator.hasNext()) {
                break;
            }
            jArr[i] = longIterator.nextLong();
            i++;
            i4 = i3;
        }
        return (i2 - i3) - 1;
    }

    public static class EmptyIterator implements LongListIterator, Serializable, Cloneable {
        private static final long serialVersionUID = -7046029254386353129L;

        private Object readResolve() {
            return LongIterators.EMPTY_ITERATOR;
        }

        public Object clone() {
            return LongIterators.EMPTY_ITERATOR;
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

        @Override // it.unimi.dsi.fastutil.longs.LongIterator, java.util.PrimitiveIterator.OfLong
        public long nextLong() {
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return -1;
        }

        @Override // it.unimi.dsi.fastutil.longs.LongBidirectionalIterator
        public long previousLong() {
            throw new NoSuchElementException();
        }

        @Override // java.util.PrimitiveIterator
        public void forEachRemaining(java.util.function.LongConsumer longConsumer) {
        }

        @Override // it.unimi.dsi.fastutil.longs.LongIterator, java.util.PrimitiveIterator.OfLong, java.util.Iterator
        @Deprecated
        public void forEachRemaining(Consumer<? super Long> consumer) {
        }
    }

    public static class UnmodifiableIterator implements LongIterator {
        protected final LongIterator i;

        public UnmodifiableIterator(LongIterator longIterator) {
            this.i = longIterator;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.i.hasNext();
        }

        @Override // it.unimi.dsi.fastutil.longs.LongIterator, java.util.PrimitiveIterator.OfLong
        public long nextLong() {
            return this.i.nextLong();
        }

        @Override // java.util.PrimitiveIterator
        public void forEachRemaining(java.util.function.LongConsumer longConsumer) {
            this.i.forEachRemaining(longConsumer);
        }

        @Override // it.unimi.dsi.fastutil.longs.LongIterator, java.util.PrimitiveIterator.OfLong, java.util.Iterator
        @Deprecated
        public void forEachRemaining(Consumer<? super Long> consumer) {
            this.i.forEachRemaining(consumer);
        }
    }

    public static int unwrap(LongIterator longIterator, long[] jArr) {
        return unwrap(longIterator, jArr, 0, jArr.length);
    }
}
