package it.unimi.dsi.fastutil.objects;

import defpackage.z0e;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class ObjectIterators {
    public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();

    public static abstract class AbstractIndexBasedIterator<K> extends AbstractObjectIterator<K> {
        protected int lastReturned;
        protected final int minPos;
        protected int pos;

        public AbstractIndexBasedIterator(int i, int i2) {
            this.minPos = i;
            this.pos = i2;
        }

        @Override // java.util.Iterator
        public void forEachRemaining(Consumer<? super K> consumer) {
            while (this.pos < getMaxPos()) {
                int i = this.pos;
                this.pos = i + 1;
                this.lastReturned = i;
                consumer.accept(get(i));
            }
        }

        public abstract K get(int i);

        public abstract int getMaxPos();

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.pos < getMaxPos();
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public K next() {
            if (!hasNext()) {
                z0e.a();
                return null;
            }
            int i = this.pos;
            this.pos = i + 1;
            this.lastReturned = i;
            return get(i);
        }

        @Override // java.util.Iterator, it.unimi.dsi.fastutil.objects.ObjectListIterator, java.util.ListIterator
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

    public static abstract class AbstractIndexBasedListIterator<K> extends AbstractIndexBasedIterator<K> implements ObjectListIterator<K> {
        public AbstractIndexBasedListIterator(int i, int i2) {
            super(i, i2);
        }

        public abstract void add(int i, K k);

        public void add(K k) {
            int i = this.pos;
            this.pos = i + 1;
            add(i, k);
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

        public K previous() {
            if (!hasPrevious()) {
                z0e.a();
                return null;
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

        public abstract void set(int i, K k);

        @Override // it.unimi.dsi.fastutil.objects.ObjectListIterator, java.util.ListIterator
        public void set(K k) {
            int i = this.lastReturned;
            if (i != -1) {
                set(i, k);
            } else {
                g33.a();
            }
        }
    }

    public static class EmptyIterator<K> implements ObjectListIterator<K>, Serializable, Cloneable {
        private static final long serialVersionUID = -7046029254386353129L;

        private Object readResolve() {
            return ObjectIterators.EMPTY_ITERATOR;
        }

        public Object clone() {
            return ObjectIterators.EMPTY_ITERATOR;
        }

        @Override // java.util.Iterator
        public void forEachRemaining(Consumer<? super K> consumer) {
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public boolean hasNext() {
            return false;
        }

        @Override // it.unimi.dsi.fastutil.BidirectionalIterator, java.util.ListIterator
        public boolean hasPrevious() {
            return false;
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public K next() {
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return 0;
        }

        @Override // it.unimi.dsi.fastutil.BidirectionalIterator
        public K previous() {
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return -1;
        }
    }

    public static class UnmodifiableIterator<K> implements ObjectIterator<K> {
        protected final ObjectIterator<? extends K> i;

        public UnmodifiableIterator(ObjectIterator<? extends K> objectIterator) {
            this.i = objectIterator;
        }

        @Override // java.util.Iterator
        public void forEachRemaining(Consumer<? super K> consumer) {
            this.i.forEachRemaining(consumer);
        }

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.i.hasNext();
        }

        @Override // java.util.Iterator
        public K next() {
            return this.i.next();
        }
    }

    public static <K> ObjectIterator<K> unmodifiable(ObjectIterator<? extends K> objectIterator) {
        return new UnmodifiableIterator(objectIterator);
    }
}
