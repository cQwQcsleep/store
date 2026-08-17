package it.unimi.dsi.fastutil.longs;

import defpackage.z0e;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class LongArraySet extends AbstractLongSet implements Serializable, Cloneable {
    private static final long serialVersionUID = 1;
    protected transient long[] a;
    protected int size;

    public LongArraySet(long[] jArr) {
        this.a = jArr;
        this.size = jArr.length;
    }

    private int findKey(long j) {
        long[] jArr = this.a;
        int i = this.size;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return -1;
            }
            if (jArr[i2] == j) {
                return i2;
            }
            i = i2;
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        long[] jArr = new long[this.size];
        this.a = jArr;
        for (int i = 0; i < this.size; i++) {
            jArr[i] = objectInputStream.readLong();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        long[] jArr = this.a;
        for (int i = 0; i < this.size; i++) {
            objectOutputStream.writeLong(jArr[i]);
        }
    }

    @Override // it.unimi.dsi.fastutil.longs.AbstractLongCollection, it.unimi.dsi.fastutil.longs.LongCollection
    public boolean add(long j) {
        if (findKey(j) != -1) {
            return false;
        }
        int i = this.size;
        if (i == this.a.length) {
            long[] jArr = new long[i == 0 ? 2 : i * 2];
            while (true) {
                int i2 = i - 1;
                if (i == 0) {
                    break;
                }
                jArr[i2] = this.a[i2];
                i = i2;
            }
            this.a = jArr;
        }
        long[] jArr2 = this.a;
        int i3 = this.size;
        this.size = i3 + 1;
        jArr2[i3] = j;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.size = 0;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public LongArraySet m15clone() {
        try {
            LongArraySet longArraySet = (LongArraySet) super.clone();
            longArraySet.a = (long[]) this.a.clone();
            return longArraySet;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // it.unimi.dsi.fastutil.longs.AbstractLongCollection, it.unimi.dsi.fastutil.longs.LongCollection
    public boolean contains(long j) {
        return findKey(j) != -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override // it.unimi.dsi.fastutil.longs.AbstractLongSet, it.unimi.dsi.fastutil.longs.AbstractLongCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, it.unimi.dsi.fastutil.longs.LongCollection, it.unimi.dsi.fastutil.longs.LongIterable, it.unimi.dsi.fastutil.longs.LongSet, java.util.Set
    public LongIterator iterator() {
        return new LongIterator() { // from class: it.unimi.dsi.fastutil.longs.LongArraySet.1
            int curr = -1;
            int next = 0;

            @Override // java.util.PrimitiveIterator
            public void forEachRemaining(java.util.function.LongConsumer longConsumer) {
                long[] jArr = LongArraySet.this.a;
                while (true) {
                    int i = this.next;
                    if (i >= LongArraySet.this.size) {
                        return;
                    }
                    this.next = i + 1;
                    longConsumer.accept(jArr[i]);
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.next < LongArraySet.this.size;
            }

            @Override // it.unimi.dsi.fastutil.longs.LongIterator, java.util.PrimitiveIterator.OfLong
            public long nextLong() {
                if (!hasNext()) {
                    z0e.a();
                    return 0L;
                }
                long[] jArr = LongArraySet.this.a;
                int i = this.next;
                this.next = i + 1;
                this.curr = i;
                return jArr[i];
            }

            @Override // java.util.Iterator
            public void remove() {
                if (this.curr == -1) {
                    g33.a();
                    return;
                }
                this.curr = -1;
                LongArraySet longArraySet = LongArraySet.this;
                int i = longArraySet.size;
                longArraySet.size = i - 1;
                int i2 = this.next;
                int i3 = i2 - 1;
                this.next = i3;
                long[] jArr = longArraySet.a;
                System.arraycopy(jArr, i2, jArr, i3, i - i2);
            }
        };
    }

    @Override // it.unimi.dsi.fastutil.longs.AbstractLongSet, it.unimi.dsi.fastutil.longs.LongSet
    public boolean remove(long j) {
        int iFindKey = findKey(j);
        if (iFindKey == -1) {
            return false;
        }
        int i = (this.size - iFindKey) - 1;
        for (int i2 = 0; i2 < i; i2++) {
            long[] jArr = this.a;
            int i3 = iFindKey + i2;
            jArr[i3] = jArr[i3 + 1];
        }
        this.size--;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.size;
    }

    @Override // java.util.Collection, java.lang.Iterable, it.unimi.dsi.fastutil.longs.LongCollection, it.unimi.dsi.fastutil.longs.LongIterable, it.unimi.dsi.fastutil.longs.LongSet, java.util.Set
    public LongSpliterator spliterator() {
        return new Spliterator(this);
    }

    @Override // it.unimi.dsi.fastutil.longs.AbstractLongCollection, it.unimi.dsi.fastutil.longs.LongCollection
    public long[] toLongArray() {
        int i = this.size;
        return i == 0 ? LongArrays.EMPTY_ARRAY : Arrays.copyOf(this.a, i);
    }

    public LongArraySet() {
        this.a = LongArrays.EMPTY_ARRAY;
    }

    public final class Spliterator implements LongSpliterator {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        boolean hasSplit;
        int max;
        int pos;

        private Spliterator(int i, int i2, boolean z) {
            this.pos = i;
            this.max = i2;
            this.hasSplit = z;
        }

        private int getWorkingMax() {
            return this.hasSplit ? this.max : LongArraySet.this.size;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 16721;
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return getWorkingMax() - this.pos;
        }

        @Override // java.util.Spliterator.OfPrimitive
        public void forEachRemaining(java.util.function.LongConsumer longConsumer) {
            long[] jArr = LongArraySet.this.a;
            int workingMax = getWorkingMax();
            while (true) {
                int i = this.pos;
                if (i >= workingMax) {
                    return;
                }
                longConsumer.accept(jArr[i]);
                this.pos++;
            }
        }

        @Override // java.util.Spliterator.OfPrimitive
        public boolean tryAdvance(java.util.function.LongConsumer longConsumer) {
            if (this.pos >= getWorkingMax()) {
                return false;
            }
            long[] jArr = LongArraySet.this.a;
            int i = this.pos;
            this.pos = i + 1;
            longConsumer.accept(jArr[i]);
            return true;
        }

        @Override // it.unimi.dsi.fastutil.longs.LongSpliterator, java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public LongSpliterator trySplit() {
            int workingMax = getWorkingMax();
            int i = this.pos;
            int i2 = (workingMax - i) >> 1;
            if (i2 <= 1) {
                return null;
            }
            this.max = workingMax;
            int i3 = i2 + i;
            this.pos = i3;
            this.hasSplit = true;
            return LongArraySet.this.new Spliterator(i, i3, true);
        }

        public Spliterator(LongArraySet longArraySet) {
            this(0, longArraySet.size, false);
        }
    }
}
