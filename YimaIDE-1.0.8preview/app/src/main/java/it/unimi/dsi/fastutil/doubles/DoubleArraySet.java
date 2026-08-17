package it.unimi.dsi.fastutil.doubles;

import defpackage.z0e;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class DoubleArraySet extends AbstractDoubleSet implements Serializable, Cloneable {
    private static final long serialVersionUID = 1;
    protected transient double[] a;
    protected int size;

    public DoubleArraySet(double[] dArr) {
        this.a = dArr;
        this.size = dArr.length;
    }

    private int findKey(double d) {
        double[] dArr = this.a;
        int i = this.size;
        while (true) {
            int i2 = i - 1;
            if (i == 0) {
                return -1;
            }
            if (Double.doubleToLongBits(dArr[i2]) == Double.doubleToLongBits(d)) {
                return i2;
            }
            i = i2;
        }
    }

    private void readObject(ObjectInputStream objectInputStream) throws ClassNotFoundException, IOException {
        objectInputStream.defaultReadObject();
        double[] dArr = new double[this.size];
        this.a = dArr;
        for (int i = 0; i < this.size; i++) {
            dArr[i] = objectInputStream.readDouble();
        }
    }

    private void writeObject(ObjectOutputStream objectOutputStream) throws IOException {
        objectOutputStream.defaultWriteObject();
        double[] dArr = this.a;
        for (int i = 0; i < this.size; i++) {
            objectOutputStream.writeDouble(dArr[i]);
        }
    }

    @Override // it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection, it.unimi.dsi.fastutil.doubles.DoubleCollection
    public boolean add(double d) {
        if (findKey(d) != -1) {
            return false;
        }
        int i = this.size;
        if (i == this.a.length) {
            double[] dArr = new double[i == 0 ? 2 : i * 2];
            while (true) {
                int i2 = i - 1;
                if (i == 0) {
                    break;
                }
                dArr[i2] = this.a[i2];
                i = i2;
            }
            this.a = dArr;
        }
        double[] dArr2 = this.a;
        int i3 = this.size;
        this.size = i3 + 1;
        dArr2[i3] = d;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public void clear() {
        this.size = 0;
    }

    /* JADX INFO: renamed from: clone, reason: merged with bridge method [inline-methods] */
    public DoubleArraySet m6clone() {
        try {
            DoubleArraySet doubleArraySet = (DoubleArraySet) super.clone();
            doubleArraySet.a = (double[]) this.a.clone();
            return doubleArraySet;
        } catch (CloneNotSupportedException unused) {
            uj0.a();
            return null;
        }
    }

    @Override // it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection, it.unimi.dsi.fastutil.doubles.DoubleCollection
    public boolean contains(double d) {
        return findKey(d) != -1;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public boolean isEmpty() {
        return this.size == 0;
    }

    @Override // it.unimi.dsi.fastutil.doubles.AbstractDoubleSet, it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection, java.util.AbstractCollection, java.util.Collection, java.lang.Iterable, it.unimi.dsi.fastutil.doubles.DoubleCollection, it.unimi.dsi.fastutil.doubles.DoubleIterable, it.unimi.dsi.fastutil.doubles.DoubleSet, java.util.Set
    public DoubleIterator iterator() {
        return new DoubleIterator() { // from class: it.unimi.dsi.fastutil.doubles.DoubleArraySet.1
            int curr = -1;
            int next = 0;

            @Override // java.util.PrimitiveIterator
            public void forEachRemaining(java.util.function.DoubleConsumer doubleConsumer) {
                double[] dArr = DoubleArraySet.this.a;
                while (true) {
                    int i = this.next;
                    if (i >= DoubleArraySet.this.size) {
                        return;
                    }
                    this.next = i + 1;
                    doubleConsumer.accept(dArr[i]);
                }
            }

            @Override // java.util.Iterator
            public boolean hasNext() {
                return this.next < DoubleArraySet.this.size;
            }

            @Override // it.unimi.dsi.fastutil.doubles.DoubleIterator, java.util.PrimitiveIterator.OfDouble
            public double nextDouble() {
                if (!hasNext()) {
                    z0e.a();
                    return 0.0d;
                }
                double[] dArr = DoubleArraySet.this.a;
                int i = this.next;
                this.next = i + 1;
                this.curr = i;
                return dArr[i];
            }

            @Override // java.util.Iterator
            public void remove() {
                if (this.curr == -1) {
                    g33.a();
                    return;
                }
                this.curr = -1;
                DoubleArraySet doubleArraySet = DoubleArraySet.this;
                int i = doubleArraySet.size;
                doubleArraySet.size = i - 1;
                int i2 = this.next;
                int i3 = i2 - 1;
                this.next = i3;
                double[] dArr = doubleArraySet.a;
                System.arraycopy(dArr, i2, dArr, i3, i - i2);
            }
        };
    }

    @Override // it.unimi.dsi.fastutil.doubles.AbstractDoubleSet, it.unimi.dsi.fastutil.doubles.DoubleSet
    public boolean remove(double d) {
        int iFindKey = findKey(d);
        if (iFindKey == -1) {
            return false;
        }
        int i = (this.size - iFindKey) - 1;
        for (int i2 = 0; i2 < i; i2++) {
            double[] dArr = this.a;
            int i3 = iFindKey + i2;
            dArr[i3] = dArr[i3 + 1];
        }
        this.size--;
        return true;
    }

    @Override // java.util.AbstractCollection, java.util.Collection, java.util.Set
    public int size() {
        return this.size;
    }

    @Override // java.util.Collection, java.lang.Iterable, it.unimi.dsi.fastutil.doubles.DoubleCollection, it.unimi.dsi.fastutil.doubles.DoubleIterable, it.unimi.dsi.fastutil.doubles.DoubleSet, java.util.Set
    public DoubleSpliterator spliterator() {
        return new Spliterator(this);
    }

    @Override // it.unimi.dsi.fastutil.doubles.AbstractDoubleCollection, it.unimi.dsi.fastutil.doubles.DoubleCollection
    public double[] toDoubleArray() {
        int i = this.size;
        return i == 0 ? DoubleArrays.EMPTY_ARRAY : Arrays.copyOf(this.a, i);
    }

    public DoubleArraySet() {
        this.a = DoubleArrays.EMPTY_ARRAY;
    }

    public final class Spliterator implements DoubleSpliterator {
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
            return this.hasSplit ? this.max : DoubleArraySet.this.size;
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
        public void forEachRemaining(java.util.function.DoubleConsumer doubleConsumer) {
            double[] dArr = DoubleArraySet.this.a;
            int workingMax = getWorkingMax();
            while (true) {
                int i = this.pos;
                if (i >= workingMax) {
                    return;
                }
                doubleConsumer.accept(dArr[i]);
                this.pos++;
            }
        }

        @Override // java.util.Spliterator.OfPrimitive
        public boolean tryAdvance(java.util.function.DoubleConsumer doubleConsumer) {
            if (this.pos >= getWorkingMax()) {
                return false;
            }
            double[] dArr = DoubleArraySet.this.a;
            int i = this.pos;
            this.pos = i + 1;
            doubleConsumer.accept(dArr[i]);
            return true;
        }

        @Override // it.unimi.dsi.fastutil.doubles.DoubleSpliterator, java.util.Spliterator.OfDouble, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public DoubleSpliterator trySplit() {
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
            return DoubleArraySet.this.new Spliterator(i, i3, true);
        }

        public Spliterator(DoubleArraySet doubleArraySet) {
            this(0, doubleArraySet.size, false);
        }
    }
}
