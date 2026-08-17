package it.unimi.dsi.fastutil.ints;

import defpackage.we3;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;
import kotlin.jvm.internal.LongCompanionObject;
import org.bouncycastle.pqc.crypto.newhope.NewHope;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class IntSpliterators {
    public static final EmptySpliterator EMPTY_SPLITERATOR = new EmptySpliterator();

    public static abstract class AbstractIndexBasedSpliterator extends AbstractIntSpliterator {
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
            return 16720;
        }

        public int computeSplitPoint() {
            return this.pos + ((getMaxPos() - this.pos) / 2);
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return ((long) getMaxPos()) - ((long) this.pos);
        }

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Spliterator.OfPrimitive
        public void forEachRemaining(java.util.function.IntConsumer intConsumer) {
            int maxPos = getMaxPos();
            while (true) {
                int i = this.pos;
                if (i >= maxPos) {
                    return;
                }
                intConsumer.accept(get(i));
                this.pos++;
            }
        }

        public abstract int get(int i);

        public abstract int getMaxPos();

        public abstract IntSpliterator makeForSplit(int i, int i2);

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Spliterator.OfPrimitive
        public boolean tryAdvance(java.util.function.IntConsumer intConsumer) {
            if (this.pos >= getMaxPos()) {
                return false;
            }
            int i = this.pos;
            this.pos = i + 1;
            intConsumer.accept(get(i));
            return true;
        }

        @Override // it.unimi.dsi.fastutil.ints.IntSpliterator, java.util.Spliterator.OfInt, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public IntSpliterator trySplit() {
            int maxPos = getMaxPos();
            int iComputeSplitPoint = computeSplitPoint();
            if (iComputeSplitPoint == this.pos || iComputeSplitPoint == maxPos) {
                return null;
            }
            splitPointCheck(iComputeSplitPoint, maxPos);
            IntSpliterator intSpliteratorMakeForSplit = makeForSplit(this.pos, iComputeSplitPoint);
            if (intSpliteratorMakeForSplit != null) {
                this.pos = iComputeSplitPoint;
            }
            return intSpliteratorMakeForSplit;
        }
    }

    public static class ArraySpliterator implements IntSpliterator {
        final int[] array;
        final int characteristics;
        private int curr;
        private int length;
        private final int offset;

        public ArraySpliterator(int[] iArr, int i, int i2, int i3) {
            this.array = iArr;
            this.offset = i;
            this.length = i2;
            this.characteristics = i3 | 16720;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.length - this.curr;
        }

        @Override // java.util.Spliterator.OfPrimitive
        public void forEachRemaining(java.util.function.IntConsumer intConsumer) {
            Objects.requireNonNull(intConsumer);
            int[] iArr = this.array;
            while (true) {
                int i = this.curr;
                if (i >= this.length) {
                    return;
                }
                intConsumer.accept(iArr[this.offset + i]);
                this.curr++;
            }
        }

        public ArraySpliterator makeForSplit(int i, int i2) {
            return new ArraySpliterator(this.array, i, i2, this.characteristics);
        }

        @Override // java.util.Spliterator.OfPrimitive
        public boolean tryAdvance(java.util.function.IntConsumer intConsumer) {
            if (this.curr >= this.length) {
                return false;
            }
            Objects.requireNonNull(intConsumer);
            int[] iArr = this.array;
            int i = this.offset;
            int i2 = this.curr;
            this.curr = i2 + 1;
            intConsumer.accept(iArr[i + i2]);
            return true;
        }

        @Override // it.unimi.dsi.fastutil.ints.IntSpliterator, java.util.Spliterator.OfInt, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public IntSpliterator trySplit() {
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

    public static IntSpliterator asSpliterator(IntIterator intIterator, long j, int i) {
        return new SpliteratorFromIterator(intIterator, j, i);
    }

    public static IntSpliterator asSpliteratorFromSorted(IntIterator intIterator, long j, int i, IntComparator intComparator) {
        return new SpliteratorFromIteratorWithComparator(intIterator, j, i, intComparator);
    }

    public static IntSpliterator asSpliteratorUnknownSize(IntIterator intIterator, int i) {
        return new SpliteratorFromIterator(intIterator, i);
    }

    public static IntSpliterator wrap(int[] iArr, int i, int i2, int i3) {
        IntArrays.ensureOffsetLength(iArr, i, i2);
        return new ArraySpliterator(iArr, i, i2, i3);
    }

    public static IntSpliterator wrapPreSorted(int[] iArr, int i, int i2, int i3, IntComparator intComparator) {
        IntArrays.ensureOffsetLength(iArr, i, i2);
        return new ArraySpliteratorWithComparator(iArr, i, i2, i3, intComparator);
    }

    public static class ArraySpliteratorWithComparator extends ArraySpliterator {
        private final IntComparator comparator;

        public ArraySpliteratorWithComparator(int[] iArr, int i, int i2, int i3, IntComparator intComparator) {
            super(iArr, i, i2, i3 | 20);
            this.comparator = intComparator;
        }

        @Override // it.unimi.dsi.fastutil.ints.IntSpliterators.ArraySpliterator
        public ArraySpliteratorWithComparator makeForSplit(int i, int i2) {
            return new ArraySpliteratorWithComparator(this.array, i, i2, this.characteristics, this.comparator);
        }

        @Override // it.unimi.dsi.fastutil.ints.IntSpliterator, java.util.Spliterator
        public IntComparator getComparator() {
            return this.comparator;
        }
    }

    public static class EmptySpliterator implements IntSpliterator, Serializable, Cloneable {
        private static final long serialVersionUID = 8379247926738230492L;

        private Object readResolve() {
            return IntSpliterators.EMPTY_SPLITERATOR;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 16448;
        }

        public Object clone() {
            return IntSpliterators.EMPTY_SPLITERATOR;
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return 0L;
        }

        @Override // it.unimi.dsi.fastutil.ints.IntSpliterator, java.util.Spliterator.OfInt, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public IntSpliterator trySplit() {
            return null;
        }

        @Override // java.util.Spliterator.OfPrimitive
        public void forEachRemaining(java.util.function.IntConsumer intConsumer) {
        }

        @Override // it.unimi.dsi.fastutil.ints.IntSpliterator, java.util.Spliterator.OfInt, java.util.Spliterator
        @Deprecated
        public void forEachRemaining(Consumer<? super Integer> consumer) {
        }

        @Override // java.util.Spliterator.OfPrimitive
        public boolean tryAdvance(java.util.function.IntConsumer intConsumer) {
            return false;
        }

        @Override // it.unimi.dsi.fastutil.ints.IntSpliterator, java.util.Spliterator.OfInt, java.util.Spliterator
        @Deprecated
        public boolean tryAdvance(Consumer<? super Integer> consumer) {
            return false;
        }
    }

    public static class SpliteratorFromIteratorWithComparator extends SpliteratorFromIterator {
        private final IntComparator comparator;

        public SpliteratorFromIteratorWithComparator(IntIterator intIterator, long j, int i, IntComparator intComparator) {
            super(intIterator, j, i | 20);
            this.comparator = intComparator;
        }

        @Override // it.unimi.dsi.fastutil.ints.IntSpliterators.SpliteratorFromIterator
        public IntSpliterator makeForSplit(int[] iArr, int i) {
            return IntSpliterators.wrapPreSorted(iArr, 0, i, this.characteristics, this.comparator);
        }

        @Override // it.unimi.dsi.fastutil.ints.IntSpliterator, java.util.Spliterator
        public IntComparator getComparator() {
            return this.comparator;
        }
    }

    public static abstract class LateBindingSizeIndexBasedSpliterator extends AbstractIndexBasedSpliterator {
        protected int maxPos;
        private boolean maxPosFixed;

        public LateBindingSizeIndexBasedSpliterator(int i) {
            super(i);
            this.maxPos = -1;
            this.maxPosFixed = false;
        }

        @Override // it.unimi.dsi.fastutil.ints.IntSpliterators.AbstractIndexBasedSpliterator
        public final int getMaxPos() {
            return this.maxPosFixed ? this.maxPos : getMaxPosFromBackingStore();
        }

        public abstract int getMaxPosFromBackingStore();

        @Override // it.unimi.dsi.fastutil.ints.IntSpliterators.AbstractIndexBasedSpliterator, it.unimi.dsi.fastutil.ints.IntSpliterator, java.util.Spliterator.OfInt, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public IntSpliterator trySplit() {
            IntSpliterator intSpliteratorTrySplit = super.trySplit();
            if (!this.maxPosFixed && intSpliteratorTrySplit != null) {
                this.maxPos = getMaxPosFromBackingStore();
                this.maxPosFixed = true;
            }
            return intSpliteratorTrySplit;
        }

        public LateBindingSizeIndexBasedSpliterator(int i, int i2) {
            super(i);
            this.maxPos = i2;
            this.maxPosFixed = true;
        }
    }

    public static class SpliteratorFromIterator implements IntSpliterator {
        final int characteristics;
        private IntSpliterator delegate;
        private final IntIterator iter;
        private final boolean knownSize;
        private int nextBatchSize;
        private long size;

        public SpliteratorFromIterator(IntIterator intIterator, long j, int i) {
            this.nextBatchSize = NewHope.POLY_SIZE;
            this.delegate = null;
            this.iter = intIterator;
            this.knownSize = true;
            this.size = j;
            if ((i & 4096) != 0) {
                this.characteristics = i | 256;
            } else {
                this.characteristics = i | 16704;
            }
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return this.characteristics;
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            IntSpliterator intSpliterator = this.delegate;
            if (intSpliterator != null) {
                return intSpliterator.estimateSize();
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

        @Override // java.util.Spliterator.OfPrimitive
        public void forEachRemaining(java.util.function.IntConsumer intConsumer) {
            IntSpliterator intSpliterator = this.delegate;
            if (intSpliterator != null) {
                intSpliterator.forEachRemaining(intConsumer);
                this.delegate = null;
            }
            this.iter.forEachRemaining(intConsumer);
            this.size = 0L;
        }

        public IntSpliterator makeForSplit(int[] iArr, int i) {
            return IntSpliterators.wrap(iArr, 0, i, this.characteristics);
        }

        @Override // java.util.Spliterator.OfPrimitive
        public boolean tryAdvance(java.util.function.IntConsumer intConsumer) {
            IntSpliterator intSpliterator = this.delegate;
            if (intSpliterator != null) {
                boolean zTryAdvance = intSpliterator.tryAdvance(intConsumer);
                if (!zTryAdvance) {
                    this.delegate = null;
                }
                return zTryAdvance;
            }
            if (!this.iter.hasNext()) {
                return false;
            }
            this.size--;
            intConsumer.accept(this.iter.nextInt());
            return true;
        }

        /* JADX WARN: Code duplicated, block: B:11:0x001f  */
        @Override // it.unimi.dsi.fastutil.ints.IntSpliterator, java.util.Spliterator.OfInt, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public IntSpliterator trySplit() {
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
            int[] iArrCopyOf = new int[iMin];
            int i = 0;
            while (i < iMin && this.iter.hasNext()) {
                iArrCopyOf[i] = this.iter.nextInt();
                this.size--;
                i++;
            }
            if (iMin < this.nextBatchSize && this.iter.hasNext()) {
                iArrCopyOf = Arrays.copyOf(iArrCopyOf, this.nextBatchSize);
                while (this.iter.hasNext() && i < this.nextBatchSize) {
                    iArrCopyOf[i] = this.iter.nextInt();
                    this.size--;
                    i++;
                }
            }
            this.nextBatchSize = Math.min(33554432, this.nextBatchSize + NewHope.POLY_SIZE);
            IntSpliterator intSpliteratorMakeForSplit = makeForSplit(iArrCopyOf, i);
            if (this.iter.hasNext()) {
                return intSpliteratorMakeForSplit;
            }
            this.delegate = intSpliteratorMakeForSplit;
            return intSpliteratorMakeForSplit.trySplit();
        }

        public SpliteratorFromIterator(IntIterator intIterator, int i) {
            this.size = LongCompanionObject.MAX_VALUE;
            this.nextBatchSize = NewHope.POLY_SIZE;
            this.delegate = null;
            this.iter = intIterator;
            this.characteristics = i | 256;
            this.knownSize = false;
        }
    }
}
