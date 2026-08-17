package it.unimi.dsi.fastutil.longs;

import defpackage.we3;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Objects;
import java.util.function.Consumer;
import kotlin.jvm.internal.LongCompanionObject;
import org.bouncycastle.pqc.crypto.newhope.NewHope;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class LongSpliterators {
    public static final EmptySpliterator EMPTY_SPLITERATOR = new EmptySpliterator();

    public static abstract class AbstractIndexBasedSpliterator extends AbstractLongSpliterator {
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
        public void forEachRemaining(java.util.function.LongConsumer longConsumer) {
            int maxPos = getMaxPos();
            while (true) {
                int i = this.pos;
                if (i >= maxPos) {
                    return;
                }
                longConsumer.accept(get(i));
                this.pos++;
            }
        }

        public abstract long get(int i);

        public abstract int getMaxPos();

        public abstract LongSpliterator makeForSplit(int i, int i2);

        /* JADX WARN: Can't rename method to resolve collision */
        @Override // java.util.Spliterator.OfPrimitive
        public boolean tryAdvance(java.util.function.LongConsumer longConsumer) {
            if (this.pos >= getMaxPos()) {
                return false;
            }
            int i = this.pos;
            this.pos = i + 1;
            longConsumer.accept(get(i));
            return true;
        }

        @Override // it.unimi.dsi.fastutil.longs.LongSpliterator, java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public LongSpliterator trySplit() {
            int maxPos = getMaxPos();
            int iComputeSplitPoint = computeSplitPoint();
            if (iComputeSplitPoint == this.pos || iComputeSplitPoint == maxPos) {
                return null;
            }
            splitPointCheck(iComputeSplitPoint, maxPos);
            LongSpliterator longSpliteratorMakeForSplit = makeForSplit(this.pos, iComputeSplitPoint);
            if (longSpliteratorMakeForSplit != null) {
                this.pos = iComputeSplitPoint;
            }
            return longSpliteratorMakeForSplit;
        }
    }

    public static class ArraySpliterator implements LongSpliterator {
        final long[] array;
        final int characteristics;
        private int curr;
        private int length;
        private final int offset;

        public ArraySpliterator(long[] jArr, int i, int i2, int i3) {
            this.array = jArr;
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
        public void forEachRemaining(java.util.function.LongConsumer longConsumer) {
            Objects.requireNonNull(longConsumer);
            long[] jArr = this.array;
            while (true) {
                int i = this.curr;
                if (i >= this.length) {
                    return;
                }
                longConsumer.accept(jArr[this.offset + i]);
                this.curr++;
            }
        }

        public ArraySpliterator makeForSplit(int i, int i2) {
            return new ArraySpliterator(this.array, i, i2, this.characteristics);
        }

        @Override // java.util.Spliterator.OfPrimitive
        public boolean tryAdvance(java.util.function.LongConsumer longConsumer) {
            if (this.curr >= this.length) {
                return false;
            }
            Objects.requireNonNull(longConsumer);
            long[] jArr = this.array;
            int i = this.offset;
            int i2 = this.curr;
            this.curr = i2 + 1;
            longConsumer.accept(jArr[i + i2]);
            return true;
        }

        @Override // it.unimi.dsi.fastutil.longs.LongSpliterator, java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public LongSpliterator trySplit() {
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

    public static LongSpliterator asSpliterator(LongIterator longIterator, long j, int i) {
        return new SpliteratorFromIterator(longIterator, j, i);
    }

    public static LongSpliterator asSpliteratorFromSorted(LongIterator longIterator, long j, int i, LongComparator longComparator) {
        return new SpliteratorFromIteratorWithComparator(longIterator, j, i, longComparator);
    }

    public static LongSpliterator asSpliteratorUnknownSize(LongIterator longIterator, int i) {
        return new SpliteratorFromIterator(longIterator, i);
    }

    public static LongSpliterator wrap(long[] jArr, int i, int i2, int i3) {
        LongArrays.ensureOffsetLength(jArr, i, i2);
        return new ArraySpliterator(jArr, i, i2, i3);
    }

    public static LongSpliterator wrapPreSorted(long[] jArr, int i, int i2, int i3, LongComparator longComparator) {
        LongArrays.ensureOffsetLength(jArr, i, i2);
        return new ArraySpliteratorWithComparator(jArr, i, i2, i3, longComparator);
    }

    public static class ArraySpliteratorWithComparator extends ArraySpliterator {
        private final LongComparator comparator;

        public ArraySpliteratorWithComparator(long[] jArr, int i, int i2, int i3, LongComparator longComparator) {
            super(jArr, i, i2, i3 | 20);
            this.comparator = longComparator;
        }

        @Override // it.unimi.dsi.fastutil.longs.LongSpliterators.ArraySpliterator
        public ArraySpliteratorWithComparator makeForSplit(int i, int i2) {
            return new ArraySpliteratorWithComparator(this.array, i, i2, this.characteristics, this.comparator);
        }

        @Override // it.unimi.dsi.fastutil.longs.LongSpliterator, java.util.Spliterator
        public LongComparator getComparator() {
            return this.comparator;
        }
    }

    public static class EmptySpliterator implements LongSpliterator, Serializable, Cloneable {
        private static final long serialVersionUID = 8379247926738230492L;

        private Object readResolve() {
            return LongSpliterators.EMPTY_SPLITERATOR;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 16448;
        }

        public Object clone() {
            return LongSpliterators.EMPTY_SPLITERATOR;
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return 0L;
        }

        @Override // it.unimi.dsi.fastutil.longs.LongSpliterator, java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public LongSpliterator trySplit() {
            return null;
        }

        @Override // java.util.Spliterator.OfPrimitive
        public void forEachRemaining(java.util.function.LongConsumer longConsumer) {
        }

        @Override // it.unimi.dsi.fastutil.longs.LongSpliterator, java.util.Spliterator.OfLong, java.util.Spliterator
        @Deprecated
        public void forEachRemaining(Consumer<? super Long> consumer) {
        }

        @Override // java.util.Spliterator.OfPrimitive
        public boolean tryAdvance(java.util.function.LongConsumer longConsumer) {
            return false;
        }

        @Override // it.unimi.dsi.fastutil.longs.LongSpliterator, java.util.Spliterator.OfLong, java.util.Spliterator
        @Deprecated
        public boolean tryAdvance(Consumer<? super Long> consumer) {
            return false;
        }
    }

    public static class SpliteratorFromIteratorWithComparator extends SpliteratorFromIterator {
        private final LongComparator comparator;

        public SpliteratorFromIteratorWithComparator(LongIterator longIterator, long j, int i, LongComparator longComparator) {
            super(longIterator, j, i | 20);
            this.comparator = longComparator;
        }

        @Override // it.unimi.dsi.fastutil.longs.LongSpliterators.SpliteratorFromIterator
        public LongSpliterator makeForSplit(long[] jArr, int i) {
            return LongSpliterators.wrapPreSorted(jArr, 0, i, this.characteristics, this.comparator);
        }

        @Override // it.unimi.dsi.fastutil.longs.LongSpliterator, java.util.Spliterator
        public LongComparator getComparator() {
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

        @Override // it.unimi.dsi.fastutil.longs.LongSpliterators.AbstractIndexBasedSpliterator
        public final int getMaxPos() {
            return this.maxPosFixed ? this.maxPos : getMaxPosFromBackingStore();
        }

        public abstract int getMaxPosFromBackingStore();

        @Override // it.unimi.dsi.fastutil.longs.LongSpliterators.AbstractIndexBasedSpliterator, it.unimi.dsi.fastutil.longs.LongSpliterator, java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public LongSpliterator trySplit() {
            LongSpliterator longSpliteratorTrySplit = super.trySplit();
            if (!this.maxPosFixed && longSpliteratorTrySplit != null) {
                this.maxPos = getMaxPosFromBackingStore();
                this.maxPosFixed = true;
            }
            return longSpliteratorTrySplit;
        }

        public LateBindingSizeIndexBasedSpliterator(int i, int i2) {
            super(i);
            this.maxPos = i2;
            this.maxPosFixed = true;
        }
    }

    public static class SpliteratorFromIterator implements LongSpliterator {
        final int characteristics;
        private LongSpliterator delegate;
        private final LongIterator iter;
        private final boolean knownSize;
        private int nextBatchSize;
        private long size;

        public SpliteratorFromIterator(LongIterator longIterator, long j, int i) {
            this.nextBatchSize = NewHope.POLY_SIZE;
            this.delegate = null;
            this.iter = longIterator;
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
            LongSpliterator longSpliterator = this.delegate;
            if (longSpliterator != null) {
                return longSpliterator.estimateSize();
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
        public void forEachRemaining(java.util.function.LongConsumer longConsumer) {
            LongSpliterator longSpliterator = this.delegate;
            if (longSpliterator != null) {
                longSpliterator.forEachRemaining(longConsumer);
                this.delegate = null;
            }
            this.iter.forEachRemaining(longConsumer);
            this.size = 0L;
        }

        public LongSpliterator makeForSplit(long[] jArr, int i) {
            return LongSpliterators.wrap(jArr, 0, i, this.characteristics);
        }

        @Override // java.util.Spliterator.OfPrimitive
        public boolean tryAdvance(java.util.function.LongConsumer longConsumer) {
            LongSpliterator longSpliterator = this.delegate;
            if (longSpliterator != null) {
                boolean zTryAdvance = longSpliterator.tryAdvance(longConsumer);
                if (!zTryAdvance) {
                    this.delegate = null;
                }
                return zTryAdvance;
            }
            if (!this.iter.hasNext()) {
                return false;
            }
            this.size--;
            longConsumer.accept(this.iter.nextLong());
            return true;
        }

        /* JADX WARN: Code duplicated, block: B:11:0x001f  */
        @Override // it.unimi.dsi.fastutil.longs.LongSpliterator, java.util.Spliterator.OfLong, java.util.Spliterator.OfPrimitive, java.util.Spliterator
        public LongSpliterator trySplit() {
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
            long[] jArrCopyOf = new long[iMin];
            int i = 0;
            while (i < iMin && this.iter.hasNext()) {
                jArrCopyOf[i] = this.iter.nextLong();
                this.size--;
                i++;
            }
            if (iMin < this.nextBatchSize && this.iter.hasNext()) {
                jArrCopyOf = Arrays.copyOf(jArrCopyOf, this.nextBatchSize);
                while (this.iter.hasNext() && i < this.nextBatchSize) {
                    jArrCopyOf[i] = this.iter.nextLong();
                    this.size--;
                    i++;
                }
            }
            this.nextBatchSize = Math.min(33554432, this.nextBatchSize + NewHope.POLY_SIZE);
            LongSpliterator longSpliteratorMakeForSplit = makeForSplit(jArrCopyOf, i);
            if (this.iter.hasNext()) {
                return longSpliteratorMakeForSplit;
            }
            this.delegate = longSpliteratorMakeForSplit;
            return longSpliteratorMakeForSplit.trySplit();
        }

        public SpliteratorFromIterator(LongIterator longIterator, int i) {
            this.size = LongCompanionObject.MAX_VALUE;
            this.nextBatchSize = NewHope.POLY_SIZE;
            this.delegate = null;
            this.iter = longIterator;
            this.characteristics = i | 256;
            this.knownSize = false;
        }
    }
}
