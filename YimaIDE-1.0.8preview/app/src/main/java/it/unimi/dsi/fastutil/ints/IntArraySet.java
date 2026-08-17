package it.unimi.dsi.fastutil.ints;

import defpackage.z0e;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class IntArraySet extends AbstractIntSet implements Serializable, Cloneable {
    private static final long serialVersionUID = 1;
    protected transient int[] a;
    protected int size;

    public IntArraySet(int[] iArr) {
        this.a = iArr;
        this.size = iArr.length;
    }

    private int findKey(int i) {
        int[] iArr = this.a;
        int i2 = this.size;
        while (true) {
            int i3 = i2 - 1;
            if (i2 == 0) {
                return -1;
            }
            if (iArr[i3] == i) {
                return i3;
            }
            i2 = i3;
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        int[] iArr = new int[this.size];
        this.a = iArr;
        for (int i = 0; i < this.size; i++) {
            iArr[i] = objectInputStream.readInt();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        int[] iArr = this.a;
        for (int i = 0; i < this.size; i++) {
            objectOutputStream.writeInt(iArr[i]);
        }
    }

    @Override // it.unimi.dsi.fastutil.ints.AbstractIntCollection, it.unimi.dsi.fastutil.ints.IntCollection
    public boolean add(int i) {
        if (findKey(i) != -1) {
            return false;
        }
        int i2 = this.size;
        if (i2 == this.a.length) {
            int[] iArr = new int[i2 == 0 ? 2 : i2 * 2];
            while (true) {
                int i3 = i2 - 1;
                if (i2 == 0) {
                    break;
                }
                iArr[i3] = this.a[i3];
                i2 = i3;
            }
            this.a = iArr;
        }
        int[] iArr2 = this.a;
        int i4 = this.size;
        this.size = i4 + 1;
        iArr2[i4] = i;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.size = 0;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public IntArraySet m10clone() {
        try {
            IntArraySet intArraySet = (IntArraySet) super.clone();
            intArraySet.a = (int[]) this.a.clone();
            return intArraySet;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // it.unimi.dsi.fastutil.ints.AbstractIntCollection, it.unimi.dsi.fastutil.ints.IntCollection
    public boolean contains(int i) {
        return findKey(i) != -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override // it.unimi.dsi.fastutil.ints.AbstractIntSet, it.unimi.dsi.fastutil.ints.AbstractIntCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, it.unimi.dsi.fastutil.ints.IntCollection, it.unimi.dsi.fastutil.ints.IntIterable, it.unimi.dsi.fastutil.ints.IntSet, java.util.Set
    public IntIterator iterator() {
        return new IntIterator() { // from class: it.unimi.dsi.fastutil.ints.IntArraySet.1
            int curr = -1;
            int next = 0;

            @Override // java.util.PrimitiveIterator
            public void forEachRemaining(java.util.function.IntConsumer intConsumer) {
                int[] iArr = IntArraySet.this.a;
                while (true) {
                    int i = this.next;
                    if (i >= IntArraySet.this.size) {
                        return;
                    }
                    this.next = i + 1;
                    intConsumer.accept(iArr[i]);
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.next < IntArraySet.this.size;
            }

            @Override // it.unimi.dsi.fastutil.ints.IntIterator, java.util.PrimitiveIterator.OfInt
            public int nextInt() {
                if (!hasNext()) {
                    z0e.a();
                    return 0;
                }
                int[] iArr = IntArraySet.this.a;
                int i = this.next;
                this.next = i + 1;
                this.curr = i;
                return iArr[i];
            }

            @Override // java.util.Iterator
            public void remove() {
                if (this.curr == -1) {
                    g33.a();
                    return;
                }
                this.curr = -1;
                IntArraySet intArraySet = IntArraySet.this;
                int i = intArraySet.size;
                intArraySet.size = i - 1;
                int i2 = this.next;
                int i3 = i2 - 1;
                this.next = i3;
                int[] iArr = intArraySet.a;
                System.arraycopy(iArr, i2, iArr, i3, i - i2);
            }
        };
    }

    @Override // it.unimi.dsi.fastutil.ints.AbstractIntSet, it.unimi.dsi.fastutil.ints.IntSet
    public boolean remove(int i) {
        int iFindKey = findKey(i);
        if (iFindKey == -1) {
            return false;
        }
        int i2 = (this.size - iFindKey) - 1;
        for (int i3 = 0; i3 < i2; i3++) {
            int[] iArr = this.a;
            int i4 = iFindKey + i3;
            iArr[i4] = iArr[i4 + 1];
        }
        this.size--;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.size;
    }

    @Override // java.util.Collection, java.lang.Iterable, it.unimi.dsi.fastutil.ints.IntCollection, it.unimi.dsi.fastutil.ints.IntIterable, it.unimi.dsi.fastutil.ints.IntSet, java.util.Set
    public IntSpliterator spliterator() {
        return new Spliterator(this);
    }

    @Override // it.unimi.dsi.fastutil.ints.AbstractIntCollection, it.unimi.dsi.fastutil.ints.IntCollection
    public int[] toIntArray() {
        int i = this.size;
        return i == 0 ? IntArrays.EMPTY_ARRAY : Arrays.copyOf(this.a, i);
    }

    public IntArraySet() {
        this.a = IntArrays.EMPTY_ARRAY;
    }

    public final class Spliterator implements IntSpliterator {
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
            return this.hasSplit ? this.max : IntArraySet.this.size;
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
        public void forEachRemaining(java.util.function.IntConsumer intConsumer) {
            int[] iArr = IntArraySet.this.a;
            int workingMax = getWorkingMax();
            while (true) {
                int i = this.pos;
                if (i >= workingMax) {
                    return;
                }
                intConsumer.accept(iArr[i]);
                this.pos++;
            }
        }

        @Override // java.util.Spliterator.OfPrimitive
        public boolean tryAdvance(java.util.function.IntConsumer intConsumer) {
            if (this.pos >= getWorkingMax()) {
                return false;
            }
            int[] iArr = IntArraySet.this.a;
            int i = this.pos;
            this.pos = i + 1;
            intConsumer.accept(iArr[i]);
            return true;
        }

        @Override // it.unimi.dsi.fastutil.ints.IntSpliterator, java.util.Spliterator.OfInt, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public IntSpliterator trySplit() {
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
            return IntArraySet.this.new Spliterator(i, i3, true);
        }

        public Spliterator(IntArraySet intArraySet) {
            this(0, intArraySet.size, false);
        }
    }
}
