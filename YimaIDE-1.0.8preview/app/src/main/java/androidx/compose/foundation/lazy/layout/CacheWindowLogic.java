package androidx.compose.foundation.lazy.layout;

import androidx.collection.IntObjectMapKt;
import androidx.collection.IntSetKt;
import androidx.collection.MutableIntObjectMap;
import androidx.collection.MutableIntSet;
import androidx.compose.foundation.lazy.layout.CacheWindowLogic;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.util.AndroidTrace_androidKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b#\b!\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0012\u0010\u001f\u001a\u00020 *\u00020!2\u0006\u0010\"\u001a\u00020\u0011J\b\u0010#\u001a\u00020 H\u0002J\n\u0010$\u001a\u00020 *\u00020!J\u0006\u0010%\u001a\u00020\u0005J\u0014\u0010&\u001a\u00020 *\u00020!2\u0006\u0010\"\u001a\u00020\u0011H\u0002J\u0014\u0010'\u001a\u00020 *\u00020!2\u0006\u0010\"\u001a\u00020\u0011H\u0002J\u0014\u0010(\u001a\u00020 *\u00020!2\u0006\u0010)\u001a\u00020\u0005H\u0002J\f\u0010*\u001a\u00020 *\u00020!H\u0002J\u0006\u0010+\u001a\u00020 JD\u0010,\u001a\u00020 *\u00020!2\u0006\u0010-\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u00132\u0006\u0010/\u001a\u00020\u00132\u0006\u00100\u001a\u00020\u00132\u0006\u00101\u001a\u00020\u00132\u0006\u00102\u001a\u00020\u00112\u0006\u00103\u001a\u00020\u0005H\u0002J@\u00104\u001a\u00020 2\u0006\u0010-\u001a\u00020\u00132\u0006\u0010.\u001a\u00020\u00132\u0006\u00100\u001a\u00020\u00132\u0006\u00101\u001a\u00020\u00132\u0006\u00105\u001a\u00020\u00132\u0006\u00102\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00020\u0013H\u0002J\u001c\u00106\u001a\u00020\u0013*\u00020!2\u0006\u00107\u001a\u00020\u00132\u0006\u00108\u001a\u00020\u0005H\u0002J\u0018\u00109\u001a\u00020 2\u0006\u00107\u001a\u00020\u00132\u0006\u0010:\u001a\u00020\u0013H\u0002J \u0010;\u001a\u00020\u000f2\u0006\u00107\u001a\u00020\u00132\u0006\u0010:\u001a\u00020\u00132\u0006\u0010<\u001a\u00020\u0001H\u0002J \u0010=\u001a\u00020 2\u0006\u00107\u001a\u00020\u00132\u0006\u0010<\u001a\u00020\u00012\u0006\u0010:\u001a\u00020\u0013H\u0002J\u0018\u0010>\u001a\u00020 2\u0006\u0010?\u001a\u00020\u00132\u0006\u0010@\u001a\u00020\u0013H\u0002J\u001c\u0010A\u001a\u00020 *\u00020!2\u0006\u00107\u001a\u00020\u00132\u0006\u0010B\u001a\u00020\u0013H\u0002J\f\u0010C\u001a\u00020 *\u00020!H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0013@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u001e\u0010\u0019\u001a\u00020\u00132\u0006\u0010\u0015\u001a\u00020\u0013@BX\u0080\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0018R\u000e\u0010\u001b\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001c\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001d\u001a\u00020\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u001e\u001a\u00020\u0013X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006D"}, d2 = {"Landroidx/compose/foundation/lazy/layout/CacheWindowLogic;", "", "cacheWindow", "Landroidx/compose/foundation/lazy/layout/LazyLayoutCacheWindow;", "enableInitialPrefetch", "", "<init>", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutCacheWindow;Z)V", "prefetchWindowHandles", "Landroidx/collection/MutableIntObjectMap;", "", "Landroidx/compose/foundation/lazy/layout/LazyLayoutPrefetchState$PrefetchHandle;", "indicesToRemove", "Landroidx/collection/MutableIntSet;", "windowCache", "Landroidx/compose/foundation/lazy/layout/CachedItem;", "previousPassDelta", "", "previousPassItemCount", "", "hasUpdatedVisibleItemsOnce", "value", "prefetchWindowStartLine", "getPrefetchWindowStartLine$foundation", "()I", "prefetchWindowEndLine", "getPrefetchWindowEndLine$foundation", "prefetchWindowStartExtraSpace", "prefetchWindowEndExtraSpace", "shouldRefillWindow", "itemsCount", "onScroll", "", "Landroidx/compose/foundation/lazy/layout/CacheWindowScope;", "delta", "traceWindowInfo", "onVisibleItemsUpdated", "hasValidBounds", "fillCacheWindowBackward", "fillCacheWindowForward", "refillWindow", "refillForward", "onDatasetChangedSize", "resetStrategy", "onPrefetchForward", "visibleWindowStart", "visibleWindowEnd", "prefetchForwardWindow", "mainAxisExtraSpaceEnd", "mainAxisExtraSpaceStart", "scrollDelta", "applyForwardPrefetch", "onKeepAround", "keepAroundWindow", "getItemSizeOrPrefetch", "index", "isUrgent", "cachePrefetchedItem", "size", "updateOrCreateCachedItem", "key", "cacheVisibleItemsInfo", "removeOutOfBoundsItems", "startLine", "endLine", "onItemPrefetched", "itemSize", "scheduleNextItemIfNeeded", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public abstract class CacheWindowLogic {
    public static final int $stable = 8;
    private final LazyLayoutCacheWindow cacheWindow;
    private final boolean enableInitialPrefetch;
    private boolean hasUpdatedVisibleItemsOnce;
    private final MutableIntSet indicesToRemove;
    private int itemsCount;
    private int prefetchWindowEndExtraSpace;
    private int prefetchWindowEndLine;
    private final MutableIntObjectMap<List<LazyLayoutPrefetchState.PrefetchHandle>> prefetchWindowHandles;
    private int prefetchWindowStartExtraSpace;
    private int prefetchWindowStartLine;
    private float previousPassDelta;
    private int previousPassItemCount;
    private boolean shouldRefillWindow;
    private final MutableIntObjectMap<CachedItem> windowCache;

    public CacheWindowLogic(LazyLayoutCacheWindow lazyLayoutCacheWindow, boolean z) {
        this.cacheWindow = lazyLayoutCacheWindow;
        this.enableInitialPrefetch = z;
        this.prefetchWindowHandles = IntObjectMapKt.mutableIntObjectMapOf();
        this.indicesToRemove = IntSetKt.mutableIntSetOf();
        this.windowCache = IntObjectMapKt.mutableIntObjectMapOf();
        this.previousPassItemCount = -1;
        this.prefetchWindowStartLine = Integer.MAX_VALUE;
        this.prefetchWindowEndLine = Integer.MIN_VALUE;
    }

    public static Unit a(CacheWindowLogic cacheWindowLogic, CacheWindowScope cacheWindowScope, int i, int i2) {
        cacheWindowLogic.onItemPrefetched(cacheWindowScope, i, i2);
        return Unit.INSTANCE;
    }

    public static Unit b(CacheWindowLogic cacheWindowLogic, CacheWindowScope cacheWindowScope, int i, int i2) {
        cacheWindowLogic.onItemPrefetched(cacheWindowScope, i, i2);
        return Unit.INSTANCE;
    }

    private final void cachePrefetchedItem(int index, int size) {
        this.windowCache.set(index, updateOrCreateCachedItem(index, size, CachedItem.INSTANCE));
        if (index > this.prefetchWindowEndLine) {
            this.prefetchWindowEndLine = index;
            this.prefetchWindowEndExtraSpace -= size;
        } else if (index < this.prefetchWindowStartLine) {
            this.prefetchWindowStartLine = index;
            this.prefetchWindowStartExtraSpace -= size;
        }
    }

    private final void cacheVisibleItemsInfo(int index, Object key, int size) {
        if (this.windowCache.containsKey(index)) {
            CachedItem cachedItem = this.windowCache.get(index);
            cachedItem.getClass();
            int mainAxisSize = cachedItem.getMainAxisSize();
            CachedItem cachedItem2 = this.windowCache.get(index);
            cachedItem2.getClass();
            Object key2 = cachedItem2.getKey();
            if (mainAxisSize != size || !Intrinsics.areEqual(key2, key)) {
                this.shouldRefillWindow = true;
            }
        }
        this.windowCache.set(index, updateOrCreateCachedItem(index, size, key));
        this.prefetchWindowStartLine = Math.min(this.prefetchWindowStartLine, index);
        this.prefetchWindowEndLine = Math.max(this.prefetchWindowEndLine, index);
        List<LazyLayoutPrefetchState.PrefetchHandle> listRemove = this.prefetchWindowHandles.remove(index);
        if (listRemove != null) {
            int size2 = listRemove.size();
            for (int i = 0; i < size2; i++) {
                listRemove.get(i).cancel();
            }
        }
    }

    private final void fillCacheWindowBackward(CacheWindowScope cacheWindowScope, float f) {
        if (cacheWindowScope.getHasVisibleItems()) {
            int mainAxisViewportSize = cacheWindowScope.getMainAxisViewportSize();
            LazyLayoutCacheWindow lazyLayoutCacheWindow = this.cacheWindow;
            Density density = cacheWindowScope.getDensity();
            int iCalculateBehindWindow = density != null ? lazyLayoutCacheWindow.calculateBehindWindow(density, mainAxisViewportSize) : 0;
            this.itemsCount = cacheWindowScope.getTotalItemsCount();
            onKeepAround(cacheWindowScope.getFirstVisibleLineIndex(), cacheWindowScope.getLastVisibleLineIndex(), cacheWindowScope.getMainAxisExtraSpaceEnd(), cacheWindowScope.getMainAxisExtraSpaceStart(), iCalculateBehindWindow, f, cacheWindowScope.getTotalItemsCount());
        }
    }

    private final void fillCacheWindowForward(CacheWindowScope cacheWindowScope, float f) {
        if (cacheWindowScope.getHasVisibleItems()) {
            int mainAxisViewportSize = cacheWindowScope.getMainAxisViewportSize();
            LazyLayoutCacheWindow lazyLayoutCacheWindow = this.cacheWindow;
            Density density = cacheWindowScope.getDensity();
            int iCalculateAheadWindow = density != null ? lazyLayoutCacheWindow.calculateAheadWindow(density, mainAxisViewportSize) : 0;
            onPrefetchForward(cacheWindowScope, cacheWindowScope.getFirstVisibleLineIndex(), cacheWindowScope.getLastVisibleLineIndex(), iCalculateAheadWindow, cacheWindowScope.getMainAxisExtraSpaceEnd(), cacheWindowScope.getMainAxisExtraSpaceStart(), f, f <= 0.0f);
        }
    }

    private final int getItemSizeOrPrefetch(final CacheWindowScope cacheWindowScope, int i, boolean z) {
        List<LazyLayoutPrefetchState.PrefetchHandle> list;
        List<LazyLayoutPrefetchState.PrefetchHandle> list2;
        if (this.windowCache.containsKey(i)) {
            CachedItem cachedItem = this.windowCache.get(i);
            cachedItem.getClass();
            return cachedItem.getMainAxisSize();
        }
        int i2 = 0;
        if (this.prefetchWindowHandles.containsKey(i)) {
            if (z && (list2 = this.prefetchWindowHandles.get(i)) != null) {
                int size = list2.size();
                while (i2 < size) {
                    list2.get(i2).markAsUrgent();
                    i2++;
                }
            }
            return -1;
        }
        this.prefetchWindowHandles.set(i, cacheWindowScope.schedulePrefetch(i, new Function2() { // from class: h91
            public final Object invoke(Object obj, Object obj2) {
                return CacheWindowLogic.b(this.b, cacheWindowScope, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
            }
        }));
        if (z && (list = this.prefetchWindowHandles.get(i)) != null) {
            int size2 = list.size();
            while (i2 < size2) {
                list.get(i2).markAsUrgent();
                i2++;
            }
        }
        return -1;
    }

    private final void onDatasetChangedSize(CacheWindowScope cacheWindowScope) {
        this.shouldRefillWindow = true;
        this.prefetchWindowStartLine = RangesKt.coerceAtLeast(this.prefetchWindowStartLine, 0);
        int lastLineIndex = cacheWindowScope.getLastLineIndex();
        if (lastLineIndex != -1) {
            this.prefetchWindowEndLine = RangesKt.coerceAtMost(this.prefetchWindowEndLine, lastLineIndex);
        }
        if (this.previousPassDelta <= 0.0f) {
            removeOutOfBoundsItems(cacheWindowScope.getLastVisibleLineIndex(), this.itemsCount - 1);
        } else {
            removeOutOfBoundsItems(0, cacheWindowScope.getFirstVisibleLineIndex());
        }
    }

    private final void onItemPrefetched(CacheWindowScope cacheWindowScope, int i, int i2) {
        cachePrefetchedItem(i, i2);
        scheduleNextItemIfNeeded(cacheWindowScope);
        traceWindowInfo();
    }

    private final void onKeepAround(int visibleWindowStart, int visibleWindowEnd, int mainAxisExtraSpaceEnd, int mainAxisExtraSpaceStart, int keepAroundWindow, float scrollDelta, int itemsCount) {
        int i;
        int i2;
        if (scrollDelta <= 0.0f) {
            this.prefetchWindowStartExtraSpace = keepAroundWindow - mainAxisExtraSpaceStart;
            this.prefetchWindowStartLine = visibleWindowStart;
            while (this.prefetchWindowStartExtraSpace > 0 && (i2 = this.prefetchWindowStartLine) > 0 && this.windowCache.containsKey(i2 - 1)) {
                CachedItem cachedItem = this.windowCache.get(this.prefetchWindowStartLine - 1);
                cachedItem.getClass();
                int mainAxisSize = cachedItem.getMainAxisSize();
                this.prefetchWindowStartLine--;
                this.prefetchWindowStartExtraSpace -= mainAxisSize;
            }
            removeOutOfBoundsItems(0, this.prefetchWindowStartLine - 1);
            return;
        }
        this.prefetchWindowEndExtraSpace = keepAroundWindow - mainAxisExtraSpaceEnd;
        this.prefetchWindowEndLine = visibleWindowEnd;
        while (this.prefetchWindowEndExtraSpace > 0 && (i = this.prefetchWindowEndLine) < itemsCount - 1 && this.windowCache.containsKey(i + 1)) {
            CachedItem cachedItem2 = this.windowCache.get(this.prefetchWindowEndLine + 1);
            cachedItem2.getClass();
            int mainAxisSize2 = cachedItem2.getMainAxisSize();
            this.prefetchWindowEndLine++;
            this.prefetchWindowEndExtraSpace -= mainAxisSize2;
        }
        removeOutOfBoundsItems(this.prefetchWindowEndLine + 1, itemsCount - 1);
    }

    private final void onPrefetchForward(CacheWindowScope cacheWindowScope, int i, int i2, int i3, int i4, int i5, float f, boolean z) {
        int i6;
        boolean z2 = Math.signum(f) == Math.signum(this.previousPassDelta);
        if (!z) {
            if (!z2 || this.shouldRefillWindow) {
                this.prefetchWindowStartExtraSpace = i3 - i5;
                this.prefetchWindowStartLine = i;
            } else {
                this.prefetchWindowStartExtraSpace = RangesKt.coerceAtMost(this.prefetchWindowStartExtraSpace + MathKt.roundToInt(Math.abs(f)), i3 - i5);
            }
            while (this.prefetchWindowStartExtraSpace > 0 && (i6 = this.prefetchWindowStartLine) > 0) {
                int itemSizeOrPrefetch = getItemSizeOrPrefetch(cacheWindowScope, this.prefetchWindowStartLine - 1, i6 + (-1) == i + (-1) && f != 0.0f && Math.abs(f) >= ((float) i5));
                if (itemSizeOrPrefetch == -1) {
                    return;
                }
                this.prefetchWindowStartLine--;
                this.prefetchWindowStartExtraSpace -= itemSizeOrPrefetch;
            }
            return;
        }
        if (!z2 || this.shouldRefillWindow) {
            this.prefetchWindowEndExtraSpace = i3 - i4;
            this.prefetchWindowEndLine = i2;
        } else {
            this.prefetchWindowEndExtraSpace = RangesKt.coerceAtMost(this.prefetchWindowEndExtraSpace + MathKt.roundToInt(Math.abs(f)), i3 - i4);
        }
        while (this.prefetchWindowEndExtraSpace > 0 && cacheWindowScope.getLastIndexInLine(this.prefetchWindowEndLine) != -1 && cacheWindowScope.getLastIndexInLine(this.prefetchWindowEndLine) < this.itemsCount - 1) {
            int itemSizeOrPrefetch2 = getItemSizeOrPrefetch(cacheWindowScope, this.prefetchWindowEndLine + 1, this.prefetchWindowEndLine + 1 == i2 + 1 && f != 0.0f && Math.abs(f) >= ((float) i4));
            if (itemSizeOrPrefetch2 == -1) {
                return;
            }
            this.prefetchWindowEndLine++;
            this.prefetchWindowEndExtraSpace -= itemSizeOrPrefetch2;
        }
    }

    private final void refillWindow(CacheWindowScope cacheWindowScope, boolean z) {
        if (cacheWindowScope.getHasVisibleItems()) {
            int mainAxisViewportSize = cacheWindowScope.getMainAxisViewportSize();
            LazyLayoutCacheWindow lazyLayoutCacheWindow = this.cacheWindow;
            Density density = cacheWindowScope.getDensity();
            onPrefetchForward(cacheWindowScope, cacheWindowScope.getFirstVisibleLineIndex(), cacheWindowScope.getLastVisibleLineIndex(), density != null ? lazyLayoutCacheWindow.calculateAheadWindow(density, mainAxisViewportSize) : 0, cacheWindowScope.getMainAxisExtraSpaceEnd(), cacheWindowScope.getMainAxisExtraSpaceStart(), 0.0f, z);
        }
    }

    /* JADX WARN: Code duplicated, block: B:40:0x00b6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:41:0x00b8 A[LOOP:2: B:28:0x0086->B:41:0x00b8, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:68:0x00bb A[EDGE_INSN: B:68:0x00bb->B:42:0x00bb BREAK  A[LOOP:2: B:28:0x0086->B:41:0x00b8], SYNTHETIC] */
    private final void removeOutOfBoundsItems(int startLine, int endLine) {
        char c;
        long j;
        long j2;
        long j3;
        int i;
        char c2;
        this.indicesToRemove.clear();
        MutableIntObjectMap<List<LazyLayoutPrefetchState.PrefetchHandle>> mutableIntObjectMap = this.prefetchWindowHandles;
        int[] iArr = mutableIntObjectMap.keys;
        long[] jArr = mutableIntObjectMap.metadata;
        int length = jArr.length - 2;
        char c3 = 7;
        long j4 = -9187201950435737472L;
        if (length >= 0) {
            int i2 = 0;
            j2 = 128;
            while (true) {
                long j5 = jArr[i2];
                j3 = 255;
                if ((((~j5) << c3) & j5 & j4) != j4) {
                    int i3 = 8 - ((~(i2 - length)) >>> 31);
                    int i4 = 0;
                    while (i4 < i3) {
                        if ((j5 & 255) < 128) {
                            c2 = c3;
                            int i5 = iArr[(i2 << 3) + i4];
                            if (startLine <= i5 && i5 <= endLine) {
                                this.indicesToRemove.add(i5);
                            }
                            j5 >>= 8;
                            i4++;
                            c3 = c2;
                            j4 = j4;
                        } else {
                            c2 = c3;
                        }
                        j5 >>= 8;
                        i4++;
                        c3 = c2;
                        j4 = j4;
                    }
                    c = c3;
                    j = j4;
                    if (i3 != 8) {
                        break;
                    }
                } else {
                    c = c3;
                    j = j4;
                }
                if (i2 == length) {
                    break;
                }
                i2++;
                c3 = c;
                j4 = j;
            }
        } else {
            c = 7;
            j = -9187201950435737472L;
            j2 = 128;
            j3 = 255;
        }
        MutableIntObjectMap<CachedItem> mutableIntObjectMap2 = this.windowCache;
        int[] iArr2 = mutableIntObjectMap2.keys;
        long[] jArr2 = mutableIntObjectMap2.metadata;
        int length2 = jArr2.length - 2;
        if (length2 >= 0) {
            int i6 = 0;
            while (true) {
                long j6 = jArr2[i6];
                if ((((~j6) << c) & j6 & j) == j) {
                    if (i6 != length2) {
                        break;
                        break;
                    }
                    i6++;
                } else {
                    int i7 = 8 - ((~(i6 - length2)) >>> 31);
                    for (int i8 = 0; i8 < i7; i8++) {
                        if ((j6 & j3) < j2 && startLine <= (i = iArr2[(i6 << 3) + i8]) && i <= endLine) {
                            this.indicesToRemove.add(i);
                        }
                        j6 >>= 8;
                    }
                    if (i7 != 8) {
                        break;
                    } else if (i6 != length2) {
                        break;
                    } else {
                        i6++;
                    }
                }
            }
        }
        MutableIntSet mutableIntSet = this.indicesToRemove;
        int[] iArr3 = mutableIntSet.elements;
        long[] jArr3 = mutableIntSet.metadata;
        int length3 = jArr3.length - 2;
        if (length3 < 0) {
            return;
        }
        int i9 = 0;
        while (true) {
            long j7 = jArr3[i9];
            if ((((~j7) << c) & j7 & j) != j) {
                int i10 = 8 - ((~(i9 - length3)) >>> 31);
                for (int i11 = 0; i11 < i10; i11++) {
                    if ((j7 & j3) < j2) {
                        int i12 = iArr3[(i9 << 3) + i11];
                        List<LazyLayoutPrefetchState.PrefetchHandle> listRemove = this.prefetchWindowHandles.remove(i12);
                        if (listRemove != null) {
                            int size = listRemove.size();
                            for (int i13 = 0; i13 < size; i13++) {
                                listRemove.get(i13).cancel();
                            }
                        }
                        this.windowCache.remove(i12);
                    }
                    j7 >>= 8;
                }
                if (i10 != 8) {
                    return;
                }
            }
            if (i9 == length3) {
                return;
            } else {
                i9++;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0028  */
    private final void scheduleNextItemIfNeeded(final CacheWindowScope cacheWindowScope) {
        int i;
        if (Math.signum(this.previousPassDelta) <= 0.0f) {
            if (this.prefetchWindowEndExtraSpace > 0) {
                i = this.prefetchWindowEndLine + 1;
            } else {
                i = -1;
            }
        } else if (Math.signum(this.previousPassDelta) <= 0.0f || this.prefetchWindowStartExtraSpace <= 0) {
            i = -1;
        } else {
            i = this.prefetchWindowStartLine - 1;
        }
        if (i <= 0 || cacheWindowScope.getLastIndexInLine(i) == -1 || cacheWindowScope.getLastIndexInLine(i) >= this.itemsCount) {
            return;
        }
        this.prefetchWindowHandles.set(i, cacheWindowScope.schedulePrefetch(i, new Function2() { // from class: i91
            public final Object invoke(Object obj, Object obj2) {
                return CacheWindowLogic.a(this.b, cacheWindowScope, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
            }
        }));
    }

    private final void traceWindowInfo() {
        AndroidTrace_androidKt.traceValue("prefetchWindowStartExtraSpace", this.prefetchWindowStartExtraSpace);
        AndroidTrace_androidKt.traceValue("prefetchWindowEndExtraSpace", this.prefetchWindowEndExtraSpace);
        AndroidTrace_androidKt.traceValue("prefetchWindowStartIndex", this.prefetchWindowStartLine);
        AndroidTrace_androidKt.traceValue("prefetchWindowEndIndex", this.prefetchWindowEndLine);
    }

    private final CachedItem updateOrCreateCachedItem(int index, int size, Object key) {
        CachedItem cachedItem = this.windowCache.get(index);
        if (cachedItem == null) {
            return new CachedItem(CachedItem.INSTANCE, size);
        }
        cachedItem.setMainAxisSize(size);
        cachedItem.setKey(key);
        return cachedItem;
    }

    /* JADX INFO: renamed from: getPrefetchWindowEndLine$foundation, reason: from getter */
    public final int getPrefetchWindowEndLine() {
        return this.prefetchWindowEndLine;
    }

    /* JADX INFO: renamed from: getPrefetchWindowStartLine$foundation, reason: from getter */
    public final int getPrefetchWindowStartLine() {
        return this.prefetchWindowStartLine;
    }

    public final boolean hasValidBounds() {
        return (this.prefetchWindowStartLine == Integer.MAX_VALUE || this.prefetchWindowEndLine == Integer.MIN_VALUE) ? false : true;
    }

    public final void onScroll(CacheWindowScope cacheWindowScope, float f) {
        traceWindowInfo();
        fillCacheWindowBackward(cacheWindowScope, f);
        fillCacheWindowForward(cacheWindowScope, f);
        this.previousPassDelta = f;
        traceWindowInfo();
    }

    public final void onVisibleItemsUpdated(CacheWindowScope cacheWindowScope) {
        if (!this.hasUpdatedVisibleItemsOnce && this.enableInitialPrefetch) {
            LazyLayoutCacheWindow lazyLayoutCacheWindow = this.cacheWindow;
            Density density = cacheWindowScope.getDensity();
            if ((density != null ? lazyLayoutCacheWindow.calculateAheadWindow(density, cacheWindowScope.getMainAxisViewportSize()) : 0) != 0) {
                this.shouldRefillWindow = true;
            }
            this.hasUpdatedVisibleItemsOnce = true;
        }
        int i = this.previousPassItemCount;
        if (i != -1 && i != cacheWindowScope.getTotalItemsCount()) {
            onDatasetChangedSize(cacheWindowScope);
        }
        this.itemsCount = cacheWindowScope.getTotalItemsCount();
        if (cacheWindowScope.getHasVisibleItems()) {
            int visibleLineCount = cacheWindowScope.getVisibleLineCount();
            for (int i2 = 0; i2 < visibleLineCount; i2++) {
                int visibleItemLine = cacheWindowScope.getVisibleItemLine(i2);
                Object visibleLineKey = cacheWindowScope.getVisibleLineKey(i2);
                int visibleItemSize = cacheWindowScope.getVisibleItemSize(i2);
                if (visibleItemLine != -1) {
                    cacheVisibleItemsInfo(visibleItemLine, visibleLineKey, visibleItemSize);
                }
            }
            if (this.shouldRefillWindow) {
                refillWindow(cacheWindowScope, this.previousPassDelta <= 0.0f);
                this.shouldRefillWindow = false;
            }
        } else {
            resetStrategy();
        }
        this.previousPassItemCount = cacheWindowScope.getTotalItemsCount();
    }

    public final void resetStrategy() {
        this.prefetchWindowStartLine = Integer.MAX_VALUE;
        this.prefetchWindowEndLine = Integer.MIN_VALUE;
        this.prefetchWindowStartExtraSpace = 0;
        this.prefetchWindowEndExtraSpace = 0;
        this.shouldRefillWindow = false;
        this.windowCache.clear();
        MutableIntObjectMap<List<LazyLayoutPrefetchState.PrefetchHandle>> mutableIntObjectMap = this.prefetchWindowHandles;
        long[] jArr = mutableIntObjectMap.metadata;
        int length = jArr.length - 2;
        if (length < 0) {
            return;
        }
        int i = 0;
        while (true) {
            long j = jArr[i];
            if ((((~j) << 7) & j & (-9187201950435737472L)) != -9187201950435737472L) {
                int i2 = 8 - ((~(i - length)) >>> 31);
                for (int i3 = 0; i3 < i2; i3++) {
                    if ((255 & j) < 128) {
                        int i4 = (i << 3) + i3;
                        int i5 = mutableIntObjectMap.keys[i4];
                        List list = (List) mutableIntObjectMap.values[i4];
                        int size = list.size();
                        for (int i6 = 0; i6 < size; i6++) {
                            ((LazyLayoutPrefetchState.PrefetchHandle) list.get(i6)).cancel();
                        }
                        mutableIntObjectMap.removeValueAt(i4);
                    }
                    j >>= 8;
                }
                if (i2 != 8) {
                    return;
                }
            }
            if (i == length) {
                return;
            } else {
                i++;
            }
        }
    }

    public /* synthetic */ CacheWindowLogic(LazyLayoutCacheWindow lazyLayoutCacheWindow, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(lazyLayoutCacheWindow, (i & 2) != 0 ? true : z);
    }
}
