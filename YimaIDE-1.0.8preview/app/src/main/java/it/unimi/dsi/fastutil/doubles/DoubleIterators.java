package it.unimi.dsi.fastutil.doubles;

import defpackage.z0e;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class DoubleIterators {
    public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();

    public static abstract class AbstractIndexBasedIterator extends AbstractDoubleIterator {
        protected int lastReturned;
        protected final int minPos;
        protected int pos;

        public AbstractIndexBasedIterator(int i, int i2) {
            this.minPos = i;
            this.pos = i2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.PrimitiveIterator
        public void forEachRemaining(java.util.function.DoubleConsumer doubleConsumer) {
            while (this.pos < getMaxPos()) {
                int i = this.pos;
                this.pos = i + 1;
                this.lastReturned = i;
                doubleConsumer.accept(get(i));
            }
        }

        public abstract double get(int i);

        public abstract int getMaxPos();

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.pos < getMaxPos();
        }

        @Override // it.unimi.dsi.fastutil.doubles.DoubleIterator, java.util.PrimitiveIterator.OfDouble
        public double nextDouble() {
            if (!hasNext()) {
                z0e.a();
                return 0.0d;
            }
            int i = this.pos;
            this.pos = i + 1;
            this.lastReturned = i;
            return get(i);
        }

        @Override // java.util.Iterator, it.unimi.dsi.fastutil.doubles.DoubleListIterator, java.util.ListIterator
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

    public static abstract class AbstractIndexBasedListIterator extends AbstractIndexBasedIterator implements DoubleListIterator {
        public AbstractIndexBasedListIterator(int i, int i2) {
            super(i, i2);
        }

        public void add(double d) {
            int i = this.pos;
            this.pos = i + 1;
            add(i, d);
            this.lastReturned = -1;
        }

        public abstract void add(int i, double d);

        @Override // it.unimi.dsi.fastutil.BidirectionalIterator, java.util.ListIterator
        public boolean hasPrevious() {
            return this.pos > this.minPos;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.pos;
        }

        public double previousDouble() {
            if (!hasPrevious()) {
                z0e.a();
                return 0.0d;
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

        @Override // it.unimi.dsi.fastutil.doubles.DoubleListIterator
        public void set(double d) {
            int i = this.lastReturned;
            if (i != -1) {
                set(i, d);
            } else {
                g33.a();
            }
        }

        public abstract void set(int i, double d);
    }

    public static DoubleIterator unmodifiable(DoubleIterator doubleIterator) {
        return new UnmodifiableIterator(doubleIterator);
    }

    public static int unwrap(DoubleIterator doubleIterator, double[] dArr, int i, int i2) {
        int i3;
        if (i2 < 0) {
            ty8.a("The maximum number of elements (", i2, ") is negative");
            return 0;
        }
        if (i < 0 || i + i2 > dArr.length) {
            j2d.a();
            return 0;
        }
        int i4 = i2;
        while (true) {
            i3 = i4 - 1;
            if (i4 == 0 || !doubleIterator.hasNext()) {
                break;
            }
            dArr[i] = doubleIterator.nextDouble();
            i++;
            i4 = i3;
        }
        return (i2 - i3) - 1;
    }

    public static class EmptyIterator implements DoubleListIterator, Serializable, Cloneable {
        private static final long serialVersionUID = -7046029254386353129L;

        private Object readResolve() {
            return DoubleIterators.EMPTY_ITERATOR;
        }

        public Object clone() {
            return DoubleIterators.EMPTY_ITERATOR;
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public boolean hasNext() {
            return false;
        }

        @Override // it.unimi.dsi.fastutil.BidirectionalIterator, java.util.ListIterator
        public boolean hasPrevious() {
            return false;
        }

        @Override // it.unimi.dsi.fastutil.doubles.DoubleIterator, java.util.PrimitiveIterator.OfDouble
        public double nextDouble() {
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return 0;
        }

        @Override // it.unimi.dsi.fastutil.doubles.DoubleBidirectionalIterator
        public double previousDouble() {
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return -1;
        }

        @Override // java.util.PrimitiveIterator
        public void forEachRemaining(java.util.function.DoubleConsumer doubleConsumer) {
        }

        @Override // it.unimi.dsi.fastutil.doubles.DoubleIterator, java.util.PrimitiveIterator.OfDouble, java.util.Iterator
        @Deprecated
        public void forEachRemaining(Consumer<? super Double> consumer) {
        }
    }

    public static class UnmodifiableIterator implements DoubleIterator {
        protected final DoubleIterator i;

        public UnmodifiableIterator(DoubleIterator doubleIterator) {
            this.i = doubleIterator;
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.i.hasNext();
        }

        @Override // it.unimi.dsi.fastutil.doubles.DoubleIterator, java.util.PrimitiveIterator.OfDouble
        public double nextDouble() {
            return this.i.nextDouble();
        }

        @Override // java.util.PrimitiveIterator
        public void forEachRemaining(java.util.function.DoubleConsumer doubleConsumer) {
            this.i.forEachRemaining(doubleConsumer);
        }

        @Override // it.unimi.dsi.fastutil.doubles.DoubleIterator, java.util.PrimitiveIterator.OfDouble, java.util.Iterator
        @Deprecated
        public void forEachRemaining(Consumer<? super Double> consumer) {
            this.i.forEachRemaining(consumer);
        }
    }

    public static int unwrap(DoubleIterator doubleIterator, double[] dArr) {
        return unwrap(doubleIterator, dArr, 0, dArr.length);
    }
}
