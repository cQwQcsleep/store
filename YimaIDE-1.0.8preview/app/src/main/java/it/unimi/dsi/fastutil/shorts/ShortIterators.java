package it.unimi.dsi.fastutil.shorts;

import defpackage.z0e;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class ShortIterators {
    public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();

    public static abstract class AbstractIndexBasedIterator extends AbstractShortIterator {
        protected int lastReturned;
        protected final int minPos;
        protected int pos;

        public AbstractIndexBasedIterator(int i, int i2) {
            this.minPos = i;
            this.pos = i2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // it.unimi.dsi.fastutil.shorts.ShortIterator, java.util.PrimitiveIterator
        public void forEachRemaining(ShortConsumer shortConsumer) {
            while (this.pos < getMaxPos()) {
                int i = this.pos;
                this.pos = i + 1;
                this.lastReturned = i;
                shortConsumer.accept(get(i));
            }
        }

        public abstract short get(int i);

        public abstract int getMaxPos();

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.pos < getMaxPos();
        }

        @Override // it.unimi.dsi.fastutil.shorts.ShortIterator
        public short nextShort() {
            if (!hasNext()) {
                z0e.a();
                return (short) 0;
            }
            int i = this.pos;
            this.pos = i + 1;
            this.lastReturned = i;
            return get(i);
        }

        @Override // java.util.Iterator, it.unimi.dsi.fastutil.shorts.ShortListIterator, java.util.ListIterator
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

    public static abstract class AbstractIndexBasedListIterator extends AbstractIndexBasedIterator implements ShortListIterator {
        public AbstractIndexBasedListIterator(int i, int i2) {
            super(i, i2);
        }

        public abstract void add(int i, short s);

        public void add(short s) {
            int i = this.pos;
            this.pos = i + 1;
            add(i, s);
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

        @Override // it.unimi.dsi.fastutil.shorts.ShortBidirectionalIterator
        public short previousShort() {
            if (!hasPrevious()) {
                z0e.a();
                return (short) 0;
            }
            int i = this.pos - 1;
            this.pos = i;
            this.lastReturned = i;
            return get(i);
        }

        public abstract void set(int i, short s);

        @Override // it.unimi.dsi.fastutil.shorts.ShortListIterator
        public void set(short s) {
            int i = this.lastReturned;
            if (i != -1) {
                set(i, s);
            } else {
                g33.a();
            }
        }
    }

    public static int unwrap(ShortIterator shortIterator, short[] sArr, int i, int i2) {
        int i3;
        if (i2 < 0) {
            ty8.a("The maximum number of elements (", i2, ") is negative");
            return 0;
        }
        if (i < 0 || i + i2 > sArr.length) {
            j2d.a();
            return 0;
        }
        int i4 = i2;
        while (true) {
            i3 = i4 - 1;
            if (i4 == 0 || !shortIterator.hasNext()) {
                break;
            }
            sArr[i] = shortIterator.nextShort();
            i++;
            i4 = i3;
        }
        return (i2 - i3) - 1;
    }

    public static class EmptyIterator implements ShortListIterator, Serializable, Cloneable {
        private static final long serialVersionUID = -7046029254386353129L;

        private Object readResolve() {
            return ShortIterators.EMPTY_ITERATOR;
        }

        public Object clone() {
            return ShortIterators.EMPTY_ITERATOR;
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

        @Override // it.unimi.dsi.fastutil.shorts.ShortIterator
        public short nextShort() {
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return -1;
        }

        @Override // it.unimi.dsi.fastutil.shorts.ShortBidirectionalIterator
        public short previousShort() {
            throw new NoSuchElementException();
        }

        @Override // java.util.Iterator, it.unimi.dsi.fastutil.shorts.ShortIterator
        @Deprecated
        public void forEachRemaining(Consumer<? super Short> consumer) {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // it.unimi.dsi.fastutil.shorts.ShortIterator, java.util.PrimitiveIterator
        public void forEachRemaining(ShortConsumer shortConsumer) {
        }
    }

    public static int unwrap(ShortIterator shortIterator, short[] sArr) {
        return unwrap(shortIterator, sArr, 0, sArr.length);
    }
}
