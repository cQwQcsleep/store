package it.unimi.dsi.fastutil.bytes;

import defpackage.z0e;
import java.io.Serializable;
import java.util.NoSuchElementException;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class ByteIterators {
    public static final EmptyIterator EMPTY_ITERATOR = new EmptyIterator();

    public static abstract class AbstractIndexBasedIterator extends AbstractByteIterator {
        protected int lastReturned;
        protected final int minPos;
        protected int pos;

        public AbstractIndexBasedIterator(int i, int i2) {
            this.minPos = i;
            this.pos = i2;
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // it.unimi.dsi.fastutil.bytes.ByteIterator, java.util.PrimitiveIterator
        public void forEachRemaining(ByteConsumer byteConsumer) {
            while (this.pos < getMaxPos()) {
                int i = this.pos;
                this.pos = i + 1;
                this.lastReturned = i;
                byteConsumer.accept(get(i));
            }
        }

        public abstract byte get(int i);

        public abstract int getMaxPos();

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.pos < getMaxPos();
        }

        @Override // it.unimi.dsi.fastutil.bytes.ByteIterator
        public byte nextByte() {
            if (!hasNext()) {
                z0e.a();
                return (byte) 0;
            }
            int i = this.pos;
            this.pos = i + 1;
            this.lastReturned = i;
            return get(i);
        }

        @Override // java.util.Iterator, it.unimi.dsi.fastutil.bytes.ByteListIterator, java.util.ListIterator
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

    public static abstract class AbstractIndexBasedListIterator extends AbstractIndexBasedIterator implements ByteListIterator {
        public AbstractIndexBasedListIterator(int i, int i2) {
            super(i, i2);
        }

        public void add(byte b) {
            int i = this.pos;
            this.pos = i + 1;
            add(i, b);
            this.lastReturned = -1;
        }

        public abstract void add(int i, byte b);

        @Override // it.unimi.dsi.fastutil.BidirectionalIterator, java.util.ListIterator
        public boolean hasPrevious() {
            return this.pos > this.minPos;
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return this.pos;
        }

        public byte previousByte() {
            if (!hasPrevious()) {
                z0e.a();
                return (byte) 0;
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

        @Override // it.unimi.dsi.fastutil.bytes.ByteListIterator
        public void set(byte b) {
            int i = this.lastReturned;
            if (i != -1) {
                set(i, b);
            } else {
                g33.a();
            }
        }

        public abstract void set(int i, byte b);
    }

    public static int unwrap(ByteIterator byteIterator, byte[] bArr, int i, int i2) {
        int i3;
        if (i2 < 0) {
            ty8.a("The maximum number of elements (", i2, ") is negative");
            return 0;
        }
        if (i < 0 || i + i2 > bArr.length) {
            j2d.a();
            return 0;
        }
        int i4 = i2;
        while (true) {
            i3 = i4 - 1;
            if (i4 == 0 || !byteIterator.hasNext()) {
                break;
            }
            bArr[i] = byteIterator.nextByte();
            i++;
            i4 = i3;
        }
        return (i2 - i3) - 1;
    }

    public static class EmptyIterator implements ByteListIterator, Serializable, Cloneable {
        private static final long serialVersionUID = -7046029254386353129L;

        private Object readResolve() {
            return ByteIterators.EMPTY_ITERATOR;
        }

        public Object clone() {
            return ByteIterators.EMPTY_ITERATOR;
        }

        @Override // java.util.Iterator, java.util.ListIterator
        public boolean hasNext() {
            return false;
        }

        @Override // it.unimi.dsi.fastutil.BidirectionalIterator, java.util.ListIterator
        public boolean hasPrevious() {
            return false;
        }

        @Override // it.unimi.dsi.fastutil.bytes.ByteIterator
        public byte nextByte() {
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int nextIndex() {
            return 0;
        }

        @Override // it.unimi.dsi.fastutil.bytes.ByteBidirectionalIterator
        public byte previousByte() {
            throw new NoSuchElementException();
        }

        @Override // java.util.ListIterator
        public int previousIndex() {
            return -1;
        }

        @Override // it.unimi.dsi.fastutil.bytes.ByteIterator, java.util.Iterator
        @Deprecated
        public void forEachRemaining(Consumer<? super Byte> consumer) {
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // it.unimi.dsi.fastutil.bytes.ByteIterator, java.util.PrimitiveIterator
        public void forEachRemaining(ByteConsumer byteConsumer) {
        }
    }

    public static int unwrap(ByteIterator byteIterator, byte[] bArr) {
        return unwrap(byteIterator, bArr, 0, bArr.length);
    }
}
