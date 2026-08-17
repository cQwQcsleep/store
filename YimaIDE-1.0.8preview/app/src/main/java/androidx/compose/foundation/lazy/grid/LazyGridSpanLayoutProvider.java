package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.lazy.layout.IntervalList;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0010!\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u0001:\u0003)*+B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00160\u00152\u0006\u0010\u0018\u001a\u00020\u000bH\u0002J\u000e\u0010 \u001a\u00020!2\u0006\u0010\"\u001a\u00020\u000bJ\u000e\u0010#\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u000bJ\u0016\u0010%\u001a\u00020\u000b2\u0006\u0010$\u001a\u00020\u000b2\u0006\u0010&\u001a\u00020\u000bJ\b\u0010'\u001a\u00020(H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0006\u001a\u0012\u0012\u0004\u0012\u00020\b0\u0007j\b\u0012\u0004\u0012\u00020\b`\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\u00020\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u000e\u0010\u000e\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u000bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00160\u0015X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0019\u001a\u00020\u000b8F¢\u0006\u0006\u001a\u0004\b\u001a\u0010\rR$\u0010\u001c\u001a\u00020\u000b2\u0006\u0010\u001b\u001a\u00020\u000b@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001d\u0010\r\"\u0004\b\u001e\u0010\u001f¨\u0006,"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider;", "", "gridContent", "Landroidx/compose/foundation/lazy/grid/LazyGridIntervalContent;", "<init>", "(Landroidx/compose/foundation/lazy/grid/LazyGridIntervalContent;)V", "buckets", "Ljava/util/ArrayList;", "Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider$Bucket;", "Lkotlin/collections/ArrayList;", "bucketSize", "", "getBucketSize", "()I", "lastLineIndex", "lastLineStartItemIndex", "lastLineStartKnownSpan", "cachedBucketIndex", "cachedBucket", "", "previousDefaultSpans", "", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", "getDefaultSpans", "currentSlotsPerLine", "totalSize", "getTotalSize", "value", "slotsPerLine", "getSlotsPerLine", "setSlotsPerLine", "(I)V", "getLineConfiguration", "Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider$LineConfiguration;", "lineIndex", "getLineIndexOfItem", "itemIndex", "spanOf", "maxSpan", "invalidateCache", "", "LineConfiguration", "Bucket", "LazyGridItemSpanScopeImpl", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LazyGridSpanLayoutProvider {
    public static final int $stable = 8;
    private final ArrayList<Bucket> buckets;
    private final List<Integer> cachedBucket;
    private int cachedBucketIndex;
    private final LazyGridIntervalContent gridContent;
    private int lastLineIndex;
    private int lastLineStartItemIndex;
    private int lastLineStartKnownSpan;
    private List<GridItemSpan> previousDefaultSpans;
    private int slotsPerLine;

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\b\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u001a\u0010\u0004\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0006\u0010\u0007\"\u0004\b\b\u0010\tR\u001a\u0010\n\u001a\u00020\u0005X\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000b\u0010\u0007\"\u0004\b\f\u0010\t¨\u0006\r"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider$LazyGridItemSpanScopeImpl;", "Landroidx/compose/foundation/lazy/grid/LazyGridItemSpanScope;", "<init>", "()V", "maxCurrentLineSpan", "", "getMaxCurrentLineSpan", "()I", "setMaxCurrentLineSpan", "(I)V", "maxLineSpan", "getMaxLineSpan", "setMaxLineSpan", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class LazyGridItemSpanScopeImpl implements LazyGridItemSpanScope {
        public static final LazyGridItemSpanScopeImpl INSTANCE = new LazyGridItemSpanScopeImpl();
        private static int maxCurrentLineSpan;
        private static int maxLineSpan;

        private LazyGridItemSpanScopeImpl() {
        }

        @Override // androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
        public int getMaxCurrentLineSpan() {
            return maxCurrentLineSpan;
        }

        @Override // androidx.compose.foundation.lazy.grid.LazyGridItemSpanScope
        public int getMaxLineSpan() {
            return maxLineSpan;
        }

        public void setMaxCurrentLineSpan(int i) {
            maxCurrentLineSpan = i;
        }

        public void setMaxLineSpan(int i) {
            maxLineSpan = i;
        }
    }

    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0007\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider$LineConfiguration;", "", "firstItemIndex", "", "spans", "", "Landroidx/compose/foundation/lazy/grid/GridItemSpan;", "<init>", "(ILjava/util/List;)V", "getFirstItemIndex", "()I", "getSpans", "()Ljava/util/List;", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class LineConfiguration {
        public static final int $stable = 8;
        private final int firstItemIndex;
        private final List<GridItemSpan> spans;

        public LineConfiguration(int i, List<GridItemSpan> list) {
            this.firstItemIndex = i;
            this.spans = list;
        }

        public final int getFirstItemIndex() {
            return this.firstItemIndex;
        }

        public final List<GridItemSpan> getSpans() {
            return this.spans;
        }
    }

    public LazyGridSpanLayoutProvider(LazyGridIntervalContent lazyGridIntervalContent) {
        this.gridContent = lazyGridIntervalContent;
        ArrayList<Bucket> arrayList = new ArrayList<>();
        int i = 0;
        arrayList.add(new Bucket(i, i, 2, null));
        this.buckets = arrayList;
        this.cachedBucketIndex = -1;
        this.cachedBucket = new ArrayList();
        this.previousDefaultSpans = CollectionsKt.emptyList();
    }

    public static int a(int i, Bucket bucket) {
        return bucket.getFirstItemIndex() - i;
    }

    private final int getBucketSize() {
        return ((int) Math.sqrt((((double) getTotalSize()) * 1.0d) / ((double) this.slotsPerLine))) + 1;
    }

    private final List<GridItemSpan> getDefaultSpans(int currentSlotsPerLine) {
        if (currentSlotsPerLine == this.previousDefaultSpans.size()) {
            return this.previousDefaultSpans;
        }
        ArrayList arrayList = new ArrayList(currentSlotsPerLine);
        for (int i = 0; i < currentSlotsPerLine; i++) {
            arrayList.add(GridItemSpan.m1072boximpl(LazyGridSpanKt.GridItemSpan(1)));
        }
        this.previousDefaultSpans = arrayList;
        return arrayList;
    }

    private final void invalidateCache() {
        this.buckets.clear();
        int i = 0;
        this.buckets.add(new Bucket(i, i, 2, null));
        this.lastLineIndex = 0;
        this.lastLineStartItemIndex = 0;
        this.lastLineStartKnownSpan = 0;
        this.cachedBucketIndex = -1;
        this.cachedBucket.clear();
    }

    /* JADX WARN: Code duplicated, block: B:21:0x0091  */
    public final LineConfiguration getLineConfiguration(int lineIndex) {
        int i;
        boolean z;
        int i2;
        int i3;
        if (!this.gridContent.getHasCustomSpans()) {
            int i4 = lineIndex * this.slotsPerLine;
            return new LineConfiguration(i4, getDefaultSpans(RangesKt.coerceAtLeast(RangesKt.coerceAtMost(this.slotsPerLine, getTotalSize() - i4), 0)));
        }
        int iMin = Math.min(lineIndex / getBucketSize(), this.buckets.size() - 1);
        int bucketSize = getBucketSize() * iMin;
        int firstItemIndex = this.buckets.get(iMin).getFirstItemIndex();
        int firstItemKnownSpan = this.buckets.get(iMin).getFirstItemKnownSpan();
        int i5 = this.lastLineIndex;
        if (bucketSize <= i5 && i5 <= lineIndex) {
            firstItemIndex = this.lastLineStartItemIndex;
            firstItemKnownSpan = this.lastLineStartKnownSpan;
            bucketSize = i5;
        } else if (iMin == this.cachedBucketIndex && (i = lineIndex - bucketSize) < this.cachedBucket.size()) {
            firstItemIndex = this.cachedBucket.get(i).intValue();
            bucketSize = lineIndex;
            firstItemKnownSpan = 0;
        }
        if (bucketSize % getBucketSize() == 0) {
            int bucketSize2 = getBucketSize();
            int i6 = lineIndex - bucketSize;
            if (2 > i6 || i6 >= bucketSize2) {
                z = false;
            } else {
                z = true;
            }
        } else {
            z = false;
        }
        if (z) {
            this.cachedBucketIndex = iMin;
            this.cachedBucket.clear();
        }
        if (!(bucketSize <= lineIndex)) {
            InlineClassHelperKt.throwIllegalStateException("currentLine (" + bucketSize + ") > lineIndex (" + lineIndex + ')');
        }
        while (bucketSize < lineIndex && firstItemIndex < getTotalSize()) {
            if (z) {
                this.cachedBucket.add(Integer.valueOf(firstItemIndex));
            }
            int i7 = 0;
            while (i7 < this.slotsPerLine && firstItemIndex < getTotalSize()) {
                if (firstItemKnownSpan == 0) {
                    i3 = firstItemKnownSpan;
                    firstItemKnownSpan = spanOf(firstItemIndex, this.slotsPerLine - i7);
                } else {
                    i3 = 0;
                }
                i7 += firstItemKnownSpan;
                if (i7 > this.slotsPerLine) {
                    break;
                }
                firstItemIndex++;
                firstItemKnownSpan = i3;
            }
            bucketSize++;
            if (bucketSize % getBucketSize() == 0 && firstItemIndex < getTotalSize()) {
                if (!(this.buckets.size() == bucketSize / getBucketSize())) {
                    InlineClassHelperKt.throwIllegalStateException("invalid starting point");
                }
                this.buckets.add(new Bucket(firstItemIndex, firstItemKnownSpan));
            }
        }
        this.lastLineIndex = lineIndex;
        this.lastLineStartItemIndex = firstItemIndex;
        this.lastLineStartKnownSpan = firstItemKnownSpan;
        ArrayList arrayList = new ArrayList();
        int i8 = 0;
        int i9 = firstItemIndex;
        while (i8 < this.slotsPerLine && i9 < getTotalSize()) {
            if (firstItemKnownSpan == 0) {
                int i10 = firstItemKnownSpan;
                firstItemKnownSpan = spanOf(i9, this.slotsPerLine - i8);
                i2 = i10;
            } else {
                i2 = 0;
            }
            i8 += firstItemKnownSpan;
            if (i8 > this.slotsPerLine) {
                break;
            }
            i9++;
            arrayList.add(GridItemSpan.m1072boximpl(LazyGridSpanKt.GridItemSpan(firstItemKnownSpan)));
            firstItemKnownSpan = i2;
        }
        return new LineConfiguration(firstItemIndex, arrayList);
    }

    public final int getLineIndexOfItem(final int itemIndex) {
        int i;
        int i2 = 0;
        if (getTotalSize() <= 0) {
            return 0;
        }
        if (!(itemIndex < getTotalSize())) {
            InlineClassHelperKt.throwIllegalArgumentException("ItemIndex > total count");
        }
        if (!this.gridContent.getHasCustomSpans()) {
            return itemIndex / this.slotsPerLine;
        }
        int iBinarySearch$default = CollectionsKt.binarySearch$default(this.buckets, 0, 0, new Function1() { // from class: androidx.compose.foundation.lazy.grid.g
            public final Object invoke(Object obj) {
                return Integer.valueOf(LazyGridSpanLayoutProvider.a(itemIndex, (LazyGridSpanLayoutProvider.Bucket) obj));
            }
        }, 3, (Object) null);
        int i3 = 2;
        if (iBinarySearch$default < 0) {
            iBinarySearch$default = (-iBinarySearch$default) - 2;
        }
        int bucketSize = getBucketSize() * iBinarySearch$default;
        int firstItemIndex = this.buckets.get(iBinarySearch$default).getFirstItemIndex();
        if (!(firstItemIndex <= itemIndex)) {
            InlineClassHelperKt.throwIllegalArgumentException("currentItemIndex > itemIndex");
        }
        int i4 = 0;
        while (true) {
            i = this.slotsPerLine;
            if (firstItemIndex >= itemIndex) {
                break;
            }
            int i5 = firstItemIndex + 1;
            int iSpanOf = spanOf(firstItemIndex, i - i4);
            i4 += iSpanOf;
            int i6 = this.slotsPerLine;
            if (i4 >= i6) {
                if (i4 == i6) {
                    bucketSize++;
                    i4 = 0;
                } else {
                    bucketSize++;
                    i4 = iSpanOf;
                }
            }
            if (bucketSize % getBucketSize() == 0 && bucketSize / getBucketSize() >= this.buckets.size()) {
                this.buckets.add(new Bucket(i5 - (i4 > 0 ? 1 : 0), i2, i3, null));
            }
            firstItemIndex = i5;
        }
        return i4 + spanOf(itemIndex, i - i4) > this.slotsPerLine ? bucketSize + 1 : bucketSize;
    }

    public final int getSlotsPerLine() {
        return this.slotsPerLine;
    }

    public final int getTotalSize() {
        return this.gridContent.getIntervals().getSize();
    }

    public final void setSlotsPerLine(int i) {
        if (i != this.slotsPerLine) {
            this.slotsPerLine = i;
            invalidateCache();
        }
    }

    public final int spanOf(int itemIndex, int maxSpan) {
        LazyGridItemSpanScopeImpl lazyGridItemSpanScopeImpl = LazyGridItemSpanScopeImpl.INSTANCE;
        lazyGridItemSpanScopeImpl.setMaxCurrentLineSpan(maxSpan);
        lazyGridItemSpanScopeImpl.setMaxLineSpan(this.slotsPerLine);
        IntervalList.Interval<LazyGridInterval> interval = this.gridContent.getIntervals().get(itemIndex);
        return GridItemSpan.m1076getCurrentLineSpanimpl(((GridItemSpan) interval.getValue().getSpan().invoke(lazyGridItemSpanScopeImpl, Integer.valueOf(itemIndex - interval.getStartIndex()))).getPackedValue());
    }

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0007\b\u0002\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\n"}, d2 = {"Landroidx/compose/foundation/lazy/grid/LazyGridSpanLayoutProvider$Bucket;", "", "firstItemIndex", "", "firstItemKnownSpan", "<init>", "(II)V", "getFirstItemIndex", "()I", "getFirstItemKnownSpan", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public static final class Bucket {
        private final int firstItemIndex;
        private final int firstItemKnownSpan;

        public /* synthetic */ Bucket(int i, int i2, int i3, DefaultConstructorMarker defaultConstructorMarker) {
            this(i, (i3 & 2) != 0 ? 0 : i2);
        }

        public final int getFirstItemIndex() {
            return this.firstItemIndex;
        }

        public final int getFirstItemKnownSpan() {
            return this.firstItemKnownSpan;
        }

        public Bucket(int i, int i2) {
            this.firstItemIndex = i;
            this.firstItemKnownSpan = i2;
        }
    }
}
