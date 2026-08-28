package com.shadow.kotlin.text;

import com.shadow.kotlin.Pair;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.Lambda;
import com.shadow.kotlin.ranges.RangesKt;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntRange;
import kotlin.sequences.Sequence;

/* loaded from: /workspace/unpacked/classes2.dex */
final class DelimitedRangesSequence implements Sequence<IntRange> {
    private final Lambda getNextMatch;
    private final CharSequence input;
    private final int limit;
    private final int startIndex;

    /* renamed from: com.shadow.kotlin.text.DelimitedRangesSequence$iterator$1, reason: invalid class name */
    public final class AnonymousClass1 implements Iterator<IntRange>, KMappedMarker {
        private int counter;
        private int currentStartIndex;
        private com.shadow.kotlin.ranges.IntRange nextItem;
        private int nextSearchIndex;
        private int nextState = -1;

        public AnonymousClass1() {
            int i = DelimitedRangesSequence.this.startIndex;
            int length = DelimitedRangesSequence.this.input.length();
            if (length < 0) {
                throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + length + " is less than minimum 0.");
            }
            if (i < 0) {
                i = 0;
            } else if (i > length) {
                i = length;
            }
            this.currentStartIndex = i;
            this.nextSearchIndex = i;
        }

        /* JADX WARN: Removed duplicated region for block: B:9:0x0021  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        private final void calcNext() {
            Pair pair;
            if (this.nextSearchIndex < 0) {
                this.nextState = 0;
                this.nextItem = null;
                return;
            }
            DelimitedRangesSequence delimitedRangesSequence = DelimitedRangesSequence.this;
            if (delimitedRangesSequence.limit > 0) {
                int i = this.counter + 1;
                this.counter = i;
                if (i >= delimitedRangesSequence.limit) {
                    this.nextItem = new com.shadow.kotlin.ranges.IntRange(this.currentStartIndex, StringsKt.getLastIndex(delimitedRangesSequence.input), 1);
                    this.nextSearchIndex = -1;
                } else if (this.nextSearchIndex <= delimitedRangesSequence.input.length() && (pair = (Pair) delimitedRangesSequence.getNextMatch.invoke(delimitedRangesSequence.input, Integer.valueOf(this.nextSearchIndex))) != null) {
                    int iIntValue = ((Number) pair.component1()).intValue();
                    int iIntValue2 = ((Number) pair.component2()).intValue();
                    this.nextItem = RangesKt.b(this.currentStartIndex, iIntValue);
                    int i2 = iIntValue + iIntValue2;
                    this.currentStartIndex = i2;
                    this.nextSearchIndex = i2 + (iIntValue2 == 0 ? 1 : 0);
                } else {
                    this.nextItem = new com.shadow.kotlin.ranges.IntRange(this.currentStartIndex, StringsKt.getLastIndex(delimitedRangesSequence.input), 1);
                    this.nextSearchIndex = -1;
                }
            }
            this.nextState = 1;
        }

        @Override // java.util.Iterator
        public final boolean hasNext() {
            if (this.nextState == -1) {
                calcNext();
            }
            return this.nextState == 1;
        }

        @Override // java.util.Iterator
        public final IntRange next() {
            if (this.nextState == -1) {
                calcNext();
            }
            if (this.nextState == 0) {
                throw new NoSuchElementException();
            }
            com.shadow.kotlin.ranges.IntRange intRange = this.nextItem;
            CloseableKt.checkNotNull(intRange, "null cannot be cast to non-null type kotlin.ranges.IntRange");
            this.nextItem = null;
            this.nextState = -1;
            return intRange;
        }

        @Override // java.util.Iterator
        public final void remove() {
            throw new UnsupportedOperationException("Operation is not supported for read-only collection");
        }
    }

    public DelimitedRangesSequence(CharSequence charSequence, int i, int i2, Function2<? super CharSequence, ? super Integer, kotlin.Pair<Integer, Integer>> function2) {
        CloseableKt.checkNotNullParameter(charSequence, "input");
        this.input = charSequence;
        this.startIndex = i;
        this.limit = i2;
        this.getNextMatch = (Lambda) function2;
    }

    public final Iterator<IntRange> iterator() {
        return new AnonymousClass1();
    }
}
