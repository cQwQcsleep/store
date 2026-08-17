package it.unimi.dsi.fastutil.objects;

import defpackage.we3;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.function.Consumer;
import kotlin.jvm.internal.LongCompanionObject;
import org.bouncycastle.pqc.crypto.newhope.NewHope;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class ObjectSpliterators {
    public static final EmptySpliterator EMPTY_SPLITERATOR = new EmptySpliterator();

    public static abstract class AbstractIndexBasedSpliterator<K> extends AbstractObjectSpliterator<K> {
        protected int pos;

        public AbstractIndexBasedSpliterator(int i) {
            this.pos = i;
        }

        private void splitPointCheck(int i, int i2) {
            if (i < this.pos || i > i2) {
                we3.a("splitPoint ", i, " outside of range of current position ", this.pos, " and range end ", i2);
            }
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 16464;
        }

        public int computeSplitPoint() {
            return this.pos + ((getMaxPos() - this.pos) / 2);
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return ((long) getMaxPos()) - ((long) this.pos);
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super K> consumer) {
            int maxPos = getMaxPos();
            while (true) {
                int i = this.pos;
                if (i >= maxPos) {
                    return;
                }
                consumer.accept(get(i));
                this.pos++;
            }
        }

        public abstract K get(int i);

        public abstract int getMaxPos();

        public abstract ObjectSpliterator<K> makeForSplit(int i, int i2);

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super K> consumer) {
            if (this.pos >= getMaxPos()) {
                return false;
            }
            int i = this.pos;
            this.pos = i + 1;
            consumer.accept(get(i));
            return true;
        }

        @Override // it.unimi.dsi.fastutil.objects.ObjectSpliterator, java.util.Spliterator
        public ObjectSpliterator<K> trySplit() {
            int maxPos = getMaxPos();
            int iComputeSplitPoint = computeSplitPoint();
            if (iComputeSplitPoint == this.pos || iComputeSplitPoint == maxPos) {
                return null;
            }
            splitPointCheck(iComputeSplitPoint, maxPos);
            ObjectSpliterator<K> objectSpliteratorMakeForSplit = makeForSplit(this.pos, iComputeSplitPoint);
            if (objectSpliteratorMakeForSplit != null) {
                this.pos = iComputeSplitPoint;
            }
            return objectSpliteratorMakeForSplit;
        }
    }

    public static class ArraySpliterator<K> implements ObjectSpliterator<K> {
        final K[] array;
        final int characteristics;
        private int curr;
        private int length;
        private final int offset;

        public ArraySpliterator(K[] kArr, int i, int i2, int i3) {
            this.array = kArr;
            this.offset = i;
            this.length = i2;
            this.characteristics = i3 | 16464;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.length - this.curr;
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super K> consumer) {
            Objects.requireNonNull(consumer);
            K[] kArr = this.array;
            while (true) {
                int i = this.curr;
                if (i >= this.length) {
                    return;
                }
                consumer.accept(kArr[this.offset + i]);
                this.curr++;
            }
        }

        public ArraySpliterator<K> makeForSplit(int i, int i2) {
            return new ArraySpliterator<>(this.array, i, i2, this.characteristics);
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super K> consumer) {
            if (this.curr >= this.length) {
                return false;
            }
            Objects.requireNonNull(consumer);
            K[] kArr = this.array;
            int i = this.offset;
            int i2 = this.curr;
            this.curr = i2 + 1;
            consumer.accept(kArr[i + i2]);
            return true;
        }

        @Override // it.unimi.dsi.fastutil.objects.ObjectSpliterator, java.util.Spliterator
        public ObjectSpliterator<K> trySplit() {
            int i = this.length;
            int i2 = this.curr;
            int i3 = (i - i2) >> 1;
            if (i3 <= 1) {
                return null;
            }
            int i4 = this.offset + i2;
            this.curr = i2 + i3;
            return makeForSplit(i4, i3);
        }
    }

    public static class ArraySpliteratorWithComparator<K> extends ArraySpliterator<K> {
        private final Comparator<? super K> comparator;

        public ArraySpliteratorWithComparator(K[] kArr, int i, int i2, int i3, Comparator<? super K> comparator) {
            super(kArr, i, i2, i3 | 20);
            this.comparator = comparator;
        }

        @Override // java.util.Spliterator
        public Comparator<? super K> getComparator() {
            return this.comparator;
        }

        @Override // it.unimi.dsi.fastutil.objects.ObjectSpliterators.ArraySpliterator
        public ArraySpliteratorWithComparator<K> makeForSplit(int i, int i2) {
            return new ArraySpliteratorWithComparator<>(this.array, i, i2, this.characteristics, this.comparator);
        }
    }

    public static class SpliteratorFromIteratorWithComparator<K> extends SpliteratorFromIterator<K> {
        private final Comparator<? super K> comparator;

        public SpliteratorFromIteratorWithComparator(ObjectIterator<? extends K> objectIterator, long j, int i, Comparator<? super K> comparator) {
            super(objectIterator, j, i | 20);
            this.comparator = comparator;
        }

        @Override // java.util.Spliterator
        public Comparator<? super K> getComparator() {
            return this.comparator;
        }

        @Override // it.unimi.dsi.fastutil.objects.ObjectSpliterators.SpliteratorFromIterator
        public ObjectSpliterator<K> makeForSplit(K[] kArr, int i) {
            return ObjectSpliterators.wrapPreSorted(kArr, 0, i, this.characteristics, this.comparator);
        }
    }

    public static <K> ObjectSpliterator<K> asSpliterator(ObjectIterator<? extends K> objectIterator, long j, int i) {
        return new SpliteratorFromIterator(objectIterator, j, i);
    }

    public static <K> ObjectSpliterator<K> asSpliteratorFromSorted(ObjectIterator<? extends K> objectIterator, long j, int i, Comparator<? super K> comparator) {
        return new SpliteratorFromIteratorWithComparator(objectIterator, j, i, comparator);
    }

    public static <K> ObjectSpliterator<K> asSpliteratorUnknownSize(ObjectIterator<? extends K> objectIterator, int i) {
        return new SpliteratorFromIterator(objectIterator, i);
    }

    public static <K> ObjectSpliterator<K> wrap(K[] kArr, int i, int i2, int i3) {
        ObjectArrays.ensureOffsetLength(kArr, i, i2);
        return new ArraySpliterator(kArr, i, i2, i3);
    }

    public static <K> ObjectSpliterator<K> wrapPreSorted(K[] kArr, int i, int i2, int i3, Comparator<? super K> comparator) {
        ObjectArrays.ensureOffsetLength(kArr, i, i2);
        return new ArraySpliteratorWithComparator(kArr, i, i2, i3, comparator);
    }

    public static class EmptySpliterator<K> implements ObjectSpliterator<K>, Serializable, Cloneable {
        private static final long serialVersionUID = 8379247926738230492L;

        private Object readResolve() {
            return ObjectSpliterators.EMPTY_SPLITERATOR;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 16448;
        }

        public Object clone() {
            return ObjectSpliterators.EMPTY_SPLITERATOR;
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return 0L;
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super K> consumer) {
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super K> consumer) {
            return false;
        }

        @Override // it.unimi.dsi.fastutil.objects.ObjectSpliterator, java.util.Spliterator
        public ObjectSpliterator<K> trySplit() {
            return null;
        }
    }

    public static abstract class LateBindingSizeIndexBasedSpliterator<K> extends AbstractIndexBasedSpliterator<K> {
        protected int maxPos;
        private boolean maxPosFixed;

        public LateBindingSizeIndexBasedSpliterator(int i) {
            super(i);
            this.maxPos = -1;
            this.maxPosFixed = false;
        }

        @Override // it.unimi.dsi.fastutil.objects.ObjectSpliterators.AbstractIndexBasedSpliterator
        public final int getMaxPos() {
            return this.maxPosFixed ? this.maxPos : getMaxPosFromBackingStore();
        }

        public abstract int getMaxPosFromBackingStore();

        @Override // it.unimi.dsi.fastutil.objects.ObjectSpliterators.AbstractIndexBasedSpliterator, it.unimi.dsi.fastutil.objects.ObjectSpliterator, java.util.Spliterator
        public ObjectSpliterator<K> trySplit() {
            ObjectSpliterator<K> objectSpliteratorTrySplit = super.trySplit();
            if (!this.maxPosFixed && objectSpliteratorTrySplit != null) {
                this.maxPos = getMaxPosFromBackingStore();
                this.maxPosFixed = true;
            }
            return objectSpliteratorTrySplit;
        }

        public LateBindingSizeIndexBasedSpliterator(int i, int i2) {
            super(i);
            this.maxPos = i2;
            this.maxPosFixed = true;
        }
    }

    public static class SpliteratorFromIterator<K> implements ObjectSpliterator<K> {
        final int characteristics;
        private ObjectSpliterator<K> delegate;
        private final ObjectIterator<? extends K> iter;
        private final boolean knownSize;
        private int nextBatchSize;
        private long size;

        public SpliteratorFromIterator(ObjectIterator<? extends K> objectIterator, long j, int i) {
            this.nextBatchSize = NewHope.POLY_SIZE;
            this.delegate = null;
            this.iter = objectIterator;
            this.knownSize = true;
            this.size = j;
            if ((i & 4096) != 0) {
                this.characteristics = i;
            } else {
                this.characteristics = i | 16448;
            }
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            ObjectSpliterator<K> objectSpliterator = this.delegate;
            if (objectSpliterator != null) {
                return objectSpliterator.estimateSize();
            }
            if (!this.iter.hasNext()) {
                return 0L;
            }
            if (!this.knownSize) {
                return LongCompanionObject.MAX_VALUE;
            }
            long j = this.size;
            return j >= 0 ? j : LongCompanionObject.MAX_VALUE;
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super K> consumer) {
            ObjectSpliterator<K> objectSpliterator = this.delegate;
            if (objectSpliterator != null) {
                objectSpliterator.forEachRemaining(consumer);
                this.delegate = null;
            }
            this.iter.forEachRemaining(consumer);
            this.size = 0L;
        }

        public ObjectSpliterator<K> makeForSplit(K[] kArr, int i) {
            return ObjectSpliterators.wrap(kArr, 0, i, this.characteristics);
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super K> consumer) {
            ObjectSpliterator<K> objectSpliterator = this.delegate;
            if (objectSpliterator != null) {
                boolean zTryAdvance = objectSpliterator.tryAdvance(consumer);
                if (!zTryAdvance) {
                    this.delegate = null;
                }
                return zTryAdvance;
            }
            if (!this.iter.hasNext()) {
                return false;
            }
            this.size--;
            consumer.accept(this.iter.next());
            return true;
        }

        /* JADX WARN: Code duplicated, block: B:11:0x001f  */
        /* JADX WARN: Multi-variable type inference failed */
        @Override // it.unimi.dsi.fastutil.objects.ObjectSpliterator, java.util.Spliterator
        public ObjectSpliterator<K> trySplit() {
            int iMin;
            if (!this.iter.hasNext()) {
                return null;
            }
            if (this.knownSize) {
                long j = this.size;
                if (j > 0) {
                    iMin = (int) Math.min(this.nextBatchSize, j);
                } else {
                    iMin = this.nextBatchSize;
                }
            } else {
                iMin = this.nextBatchSize;
            }
            Object[] objArrCopyOf = new Object[iMin];
            int i = 0;
            while (i < iMin && this.iter.hasNext()) {
                objArrCopyOf[i] = this.iter.next();
                this.size--;
                i++;
            }
            if (iMin < this.nextBatchSize && this.iter.hasNext()) {
                objArrCopyOf = Arrays.copyOf(objArrCopyOf, this.nextBatchSize);
                while (this.iter.hasNext() && i < this.nextBatchSize) {
                    objArrCopyOf[i] = this.iter.next();
                    this.size--;
                    i++;
                }
            }
            this.nextBatchSize = Math.min(33554432, this.nextBatchSize + NewHope.POLY_SIZE);
            ObjectSpliterator<K> objectSpliteratorMakeForSplit = makeForSplit(objArrCopyOf, i);
            if (this.iter.hasNext()) {
                return objectSpliteratorMakeForSplit;
            }
            this.delegate = objectSpliteratorMakeForSplit;
            return objectSpliteratorMakeForSplit.trySplit();
        }

        public SpliteratorFromIterator(ObjectIterator<? extends K> objectIterator, int i) {
            this.size = LongCompanionObject.MAX_VALUE;
            this.nextBatchSize = NewHope.POLY_SIZE;
            this.delegate = null;
            this.iter = objectIterator;
            this.characteristics = i;
            this.knownSize = false;
        }
    }
}
