package androidx.compose.foundation.lazy.staggeredgrid;

import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.foundation.lazy.staggeredgrid.LazyStaggeredGridMeasureKt;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntSize;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000¬\u0001\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0015\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0016\u001a5\u0010\u0002\u001a\u0002H\u0003\"\u0004\b\u0000\u0010\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0017\u0010\u0006\u001a\u0013\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u0002H\u00030\u0007¢\u0006\u0002\b\bH\u0082\b¢\u0006\u0002\u0010\t\u001a\u001d\u0010\n\u001a\u00020\u000b*\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\fH\u0002¢\u0006\u0002\u0010\u000f\u001a\u0017\u0010\u0010\u001a\u00020\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u000b0\u0013H\u0082\b\u001a£\u0001\u0010\u0014\u001a\u00020\u0015*\u00020\u00052\u0006\u0010\u0016\u001a\u00020\u00172\f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00192\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\u00012\u0006\u0010\"\u001a\u00020\u00012\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020\u001a2\u0006\u0010&\u001a\u00020\u001a2\u0006\u0010'\u001a\u00020\u001a2\u0006\u0010(\u001a\u00020\u001a2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020\u00012\u0006\u0010,\u001a\u00020\u00012\b\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010/\u001a\u000200H\u0000¢\u0006\u0004\b1\u00102\u001a,\u00103\u001a\u00020\u0015*\u0002042\u0006\u00105\u001a\u00020\u001a2\u0006\u00106\u001a\u0002072\u0006\u00108\u001a\u0002072\u0006\u00109\u001a\u00020\u0001H\u0002\u001aK\u0010:\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0019*\u0002042\u0012\u0010;\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000e0\r0\f2\u0006\u0010<\u001a\u0002072\u0006\u0010=\u001a\u00020\u001a2\u0006\u0010>\u001a\u00020\u001a2\u0006\u0010?\u001a\u00020\u001aH\u0002¢\u0006\u0002\u0010@\u001aG\u0010A\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\u0019*\u0002042\u0006\u0010B\u001a\u00020\u001a2\u0006\u0010C\u001a\u00020\u001a2\u0006\u0010,\u001a\u00020\u00012\u0018\u0010D\u001a\u0014\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00110EH\u0082\b\u001aR\u0010F\u001a\b\u0012\u0004\u0012\u00020\u000e0\u0019*\u0002042\u0012\u0010D\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00110\u00072!\u0010G\u001a\u001d\u0012\u0013\u0012\u00110\u001a¢\u0006\f\bH\u0012\b\bI\u0012\u0004\b\b(J\u0012\u0004\u0012\u00020\u00010\u00072\u0006\u0010K\u001a\u00020\u0001H\u0082\b\u001a7\u0010L\u001a\u00020\u0011\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\u00192\b\b\u0002\u0010M\u001a\u00020\u00012\u0012\u0010N\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u00110\u0007H\u0082\b\u001a(\u0010O\u001a\u00020\u0011*\u00020P2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u00110\u0007H\u0082\b¢\u0006\u0004\bQ\u0010R\u001a\u0014\u0010S\u001a\u00020\u0011*\u0002072\u0006\u0010T\u001a\u00020\u001aH\u0002\u001a\u001b\u0010U\u001a\u00020\u001a*\u0002072\u0006\u0010V\u001a\u00020PH\u0002¢\u0006\u0004\bW\u0010X\u001a\u0016\u0010Y\u001a\u00020\u001a*\u0002072\b\b\u0002\u0010Z\u001a\u00020\u001aH\u0000\u001a2\u0010[\u001a\u00020\u001a\"\u0004\b\u0000\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00030\f2\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u0002H\u0003\u0012\u0004\u0012\u00020\u001a0\u0007H\u0082\b¢\u0006\u0002\u0010\\\u001a\f\u0010]\u001a\u00020\u001a*\u000207H\u0002\u001a!\u0010^\u001a\u000207*\u0002072\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a0\u0007H\u0082\b\u001a\u001c\u0010_\u001a\u00020\u0011*\u0002042\u0006\u0010`\u001a\u0002072\u0006\u0010a\u001a\u00020\u001aH\u0002\u001a\u001c\u0010b\u001a\u00020\u001a*\u0002042\u0006\u0010c\u001a\u00020\u001a2\u0006\u0010d\u001a\u00020\u001aH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010e\u001a\u00020\u001aX\u0082T¢\u0006\u0002\n\u0000¨\u0006f"}, d2 = {"DebugLoggingEnabled", "", "withDebugLogging", "T", "scope", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "debugRender", "", "", "Lkotlin/collections/ArrayDeque;", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasuredItem;", "([Lkotlin/collections/ArrayDeque;)Ljava/lang/String;", "debugLog", "", "message", "Lkotlin/Function0;", "measureStaggeredGrid", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "state", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;", "pinnedItems", "", "", "itemProvider", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;", "resolvedSlots", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "isVertical", "reverseLayout", "contentOffset", "Landroidx/compose/ui/unit/IntOffset;", "mainAxisAvailableSize", "mainAxisSpacing", "beforeContentPadding", "afterContentPadding", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "isInLookaheadScope", "isLookingAhead", "approachLayoutInfo", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLayoutInfo;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "measureStaggeredGrid-C6celF4", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridState;Ljava/util/List;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridItemProvider;Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridSlots;JZZJIIIILkotlinx/coroutines/CoroutineScope;ZZLandroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridLayoutInfo;Landroidx/compose/ui/graphics/GraphicsContext;)Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureResult;", "measure", "Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;", "initialScrollDelta", "initialItemIndices", "", "initialItemOffsets", "canRestartMeasure", "calculateVisibleItems", "measuredItems", "itemScrollOffsets", "mainAxisLayoutSize", "minOffset", "maxOffset", "(Landroidx/compose/foundation/lazy/staggeredgrid/LazyStaggeredGridMeasureContext;[Lkotlin/collections/ArrayDeque;[IIII)Ljava/util/List;", "itemsRetainedForLookahead", "lastVisibleItemIndex", "itemsCount", "position", "Lkotlin/Function2;", "calculateExtraItems", "filter", "Lkotlin/ParameterName;", "name", "itemIndex", "beforeVisibleBounds", "fastForEach", "reverse", "action", "forEach", "Landroidx/compose/foundation/lazy/staggeredgrid/SpanRange;", "forEach-nIS5qE8", "(JLkotlin/jvm/functions/Function1;)V", "offsetBy", "delta", "maxInRange", "indexRange", "maxInRange-jy6DScQ", "([IJ)I", "indexOfMinValue", "minBound", "indexOfMinBy", "([Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)I", "indexOfMaxValue", "transform", "ensureIndicesInRange", "indices", "itemCount", "findPreviousItemIndex", "item", "lane", "Unset", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LazyStaggeredGridMeasureKt {
    private static final boolean DebugLoggingEnabled = false;
    private static final int Unset = Integer.MIN_VALUE;

    private static final List<LazyStaggeredGridMeasuredItem> calculateExtraItems(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, Function1<? super LazyStaggeredGridMeasuredItem, Unit> function1, Function1<? super Integer, Boolean> function2, boolean z) {
        List<Integer> pinnedItems = lazyStaggeredGridMeasureContext.getPinnedItems();
        ArrayList arrayList = null;
        if (z) {
            int size = pinnedItems.size() - 1;
            if (size >= 0) {
                while (true) {
                    int i = size - 1;
                    int iIntValue = pinnedItems.get(size).intValue();
                    if (((Boolean) function2.invoke(Integer.valueOf(iIntValue))).booleanValue()) {
                        long jM1192getSpanRangelOCCd4c = lazyStaggeredGridMeasureContext.m1192getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext.getItemProvider(), iIntValue, 0);
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ = lazyStaggeredGridMeasureContext.getMeasuredItemProvider().m1200getAndMeasurejy6DScQ(iIntValue, jM1192getSpanRangelOCCd4c);
                        function1.invoke(lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ);
                        arrayList.add(lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ);
                    }
                    if (i < 0) {
                        break;
                    }
                    size = i;
                }
            }
        } else {
            int size2 = pinnedItems.size();
            for (int i2 = 0; i2 < size2; i2++) {
                int iIntValue2 = pinnedItems.get(i2).intValue();
                if (((Boolean) function2.invoke(Integer.valueOf(iIntValue2))).booleanValue()) {
                    long jM1192getSpanRangelOCCd4c2 = lazyStaggeredGridMeasureContext.m1192getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext.getItemProvider(), iIntValue2, 0);
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ2 = lazyStaggeredGridMeasureContext.getMeasuredItemProvider().m1200getAndMeasurejy6DScQ(iIntValue2, jM1192getSpanRangelOCCd4c2);
                    function1.invoke(lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ2);
                    arrayList.add(lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ2);
                }
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List<LazyStaggeredGridMeasuredItem> calculateVisibleItems(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, ArrayDeque<LazyStaggeredGridMeasuredItem>[] arrayDequeArr, int[] iArr, int i, int i2, int i3) {
        int size = 0;
        for (ArrayDeque<LazyStaggeredGridMeasuredItem> arrayDeque : arrayDequeArr) {
            size += arrayDeque.size();
        }
        ArrayList arrayList = new ArrayList(size);
        while (true) {
            for (ArrayDeque<LazyStaggeredGridMeasuredItem> arrayDeque2 : arrayDequeArr) {
                if (!arrayDeque2.isEmpty()) {
                    int length = arrayDequeArr.length;
                    int i4 = -1;
                    int i5 = Integer.MAX_VALUE;
                    for (int i6 = 0; i6 < length; i6++) {
                        LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem = (LazyStaggeredGridMeasuredItem) arrayDequeArr[i6].firstOrNull();
                        int index = lazyStaggeredGridMeasuredItem != null ? lazyStaggeredGridMeasuredItem.getIndex() : Integer.MAX_VALUE;
                        if (i5 > index) {
                            i4 = i6;
                            i5 = index;
                        }
                    }
                    LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem2 = (LazyStaggeredGridMeasuredItem) arrayDequeArr[i4].removeFirst();
                    if (lazyStaggeredGridMeasuredItem2.getLane() == i4) {
                        long jM1206constructorimpl = SpanRange.m1206constructorimpl(lazyStaggeredGridMeasuredItem2.getLane(), lazyStaggeredGridMeasuredItem2.getSpan());
                        int iM1196maxInRangejy6DScQ = m1196maxInRangejy6DScQ(iArr, jM1206constructorimpl);
                        int i7 = lazyStaggeredGridMeasureContext.getResolvedSlots().getPositions()[i4];
                        if (lazyStaggeredGridMeasuredItem2.getMainAxisSize() + iM1196maxInRangejy6DScQ >= i2 && iM1196maxInRangejy6DScQ <= i3) {
                            lazyStaggeredGridMeasuredItem2.position(iM1196maxInRangejy6DScQ, i7, i);
                            arrayList.add(lazyStaggeredGridMeasuredItem2);
                        }
                        int i8 = (int) (jM1206constructorimpl & 4294967295L);
                        for (int i9 = (int) (jM1206constructorimpl >> 32); i9 < i8; i9++) {
                            iArr[i9] = lazyStaggeredGridMeasuredItem2.getMainAxisSizeWithSpacings() + iM1196maxInRangejy6DScQ;
                        }
                    }
                }
            }
            return arrayList;
        }
    }

    private static final void debugLog(Function0<String> function0) {
    }

    private static final String debugRender(ArrayDeque<LazyStaggeredGridMeasuredItem>[] arrayDequeArr) {
        return "";
    }

    private static final void ensureIndicesInRange(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, int[] iArr, int i) {
        int length = iArr.length - 1;
        if (length < 0) {
            return;
        }
        while (true) {
            int i2 = length - 1;
            while (true) {
                if (iArr[length] < i && lazyStaggeredGridMeasureContext.getLaneInfo().assignedToLane(iArr[length], length)) {
                    break;
                } else {
                    iArr[length] = findPreviousItemIndex(lazyStaggeredGridMeasureContext, iArr[length], length);
                }
            }
            int i3 = iArr[length];
            if (i3 >= 0 && !lazyStaggeredGridMeasureContext.isFullSpan(lazyStaggeredGridMeasureContext.getItemProvider(), i3)) {
                if (lazyStaggeredGridMeasureContext.getLaneInfo().getLane(i3) == -2) {
                    int length2 = iArr.length;
                    int i4 = 0;
                    while (true) {
                        if (i4 >= length2) {
                            i4 = -1;
                            break;
                        } else if (iArr[i4] == i3) {
                            break;
                        } else {
                            i4++;
                        }
                    }
                    int i5 = i4 + 1;
                    if (i5 <= length) {
                        while (true) {
                            if (iArr[i5] == i3) {
                                iArr[i5] = findPreviousItemIndex(lazyStaggeredGridMeasureContext, i3, i5);
                            }
                            if (i5 == length) {
                                break;
                            } else {
                                i5++;
                            }
                        }
                    }
                    length = i4;
                }
                lazyStaggeredGridMeasureContext.getLaneInfo().setLane(i3, length);
            }
            if (i2 < 0) {
                return;
            } else {
                length = i2;
            }
        }
    }

    private static final <T> void fastForEach(List<? extends T> list, boolean z, Function1<? super T, Unit> function1) {
        if (!z) {
            int size = list.size();
            for (int i = 0; i < size; i++) {
                function1.invoke(list.get(i));
            }
            return;
        }
        int size2 = list.size() - 1;
        if (size2 < 0) {
            return;
        }
        while (true) {
            int i2 = size2 - 1;
            function1.invoke(list.get(size2));
            if (i2 < 0) {
                return;
            } else {
                size2 = i2;
            }
        }
    }

    public static /* synthetic */ void fastForEach$default(List list, boolean z, Function1 function1, int i, Object obj) {
        if ((i & 1) != 0) {
            z = false;
        }
        if (!z) {
            int size = list.size();
            for (int i2 = 0; i2 < size; i2++) {
                function1.invoke(list.get(i2));
            }
            return;
        }
        int size2 = list.size() - 1;
        if (size2 < 0) {
            return;
        }
        while (true) {
            int i3 = size2 - 1;
            function1.invoke(list.get(size2));
            if (i3 < 0) {
                return;
            } else {
                size2 = i3;
            }
        }
    }

    private static final int findPreviousItemIndex(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, int i, int i2) {
        return lazyStaggeredGridMeasureContext.getLaneInfo().findPreviousItemIndex(i, i2);
    }

    /* JADX INFO: renamed from: forEach-nIS5qE8, reason: not valid java name */
    private static final void m1195forEachnIS5qE8(long j, Function1<? super Integer, Unit> function1) {
        int i = (int) (j & 4294967295L);
        for (int i2 = (int) (j >> 32); i2 < i; i2++) {
            function1.invoke(Integer.valueOf(i2));
        }
    }

    private static final int indexOfMaxValue(int[] iArr) {
        int length = iArr.length;
        int i = -1;
        int i2 = Unset;
        for (int i3 = 0; i3 < length; i3++) {
            int i4 = iArr[i3];
            if (i2 < i4) {
                i = i3;
                i2 = i4;
            }
        }
        return i;
    }

    private static final <T> int indexOfMinBy(T[] tArr, Function1<? super T, Integer> function1) {
        int length = tArr.length;
        int i = -1;
        int i2 = Integer.MAX_VALUE;
        for (int i3 = 0; i3 < length; i3++) {
            int iIntValue = ((Number) function1.invoke(tArr[i3])).intValue();
            if (i2 > iIntValue) {
                i = i3;
                i2 = iIntValue;
            }
        }
        return i;
    }

    public static final int indexOfMinValue(int[] iArr, int i) {
        int length = iArr.length;
        int i2 = -1;
        int i3 = Integer.MAX_VALUE;
        for (int i4 = 0; i4 < length; i4++) {
            int i5 = i + 1;
            int i6 = iArr[i4];
            if (i5 <= i6 && i6 < i3) {
                i2 = i4;
                i3 = i6;
            }
        }
        return i2;
    }

    public static /* synthetic */ int indexOfMinValue$default(int[] iArr, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = Unset;
        }
        return indexOfMinValue(iArr, i);
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0091  */
    /* JADX WARN: Code duplicated, block: B:36:0x00a8  */
    /* JADX WARN: Code duplicated, block: B:39:0x00b6 A[LOOP:3: B:35:0x00a6->B:39:0x00b6, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:43:0x00be  */
    /* JADX WARN: Code duplicated, block: B:44:0x00c3  */
    /* JADX WARN: Code duplicated, block: B:47:0x00e2  */
    /* JADX WARN: Code duplicated, block: B:61:0x00b9 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:62:0x00ba A[EDGE_INSN: B:62:0x00ba->B:41:0x00ba BREAK  A[LOOP:3: B:35:0x00a6->B:39:0x00b6], SYNTHETIC] */
    private static final List<LazyStaggeredGridMeasuredItem> itemsRetainedForLookahead(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, int i, int i2, boolean z, Function2<? super LazyStaggeredGridMeasuredItem, ? super Integer, Unit> function2) {
        LazyStaggeredGridItemInfo lazyStaggeredGridItemInfo;
        int index;
        int iMin;
        List<LazyStaggeredGridItemInfo> visibleItemsInfo;
        int size;
        int i3;
        LazyStaggeredGridItemInfo lazyStaggeredGridItemInfo2;
        LazyStaggeredGridItemInfo lazyStaggeredGridItemInfo3;
        int lane;
        if (z && lazyStaggeredGridMeasureContext.getApproachLayoutInfo() != null && !lazyStaggeredGridMeasureContext.getApproachLayoutInfo().getVisibleItemsInfo().isEmpty()) {
            List<LazyStaggeredGridItemInfo> visibleItemsInfo2 = lazyStaggeredGridMeasureContext.getApproachLayoutInfo().getVisibleItemsInfo();
            int size2 = visibleItemsInfo2.size();
            while (true) {
                size2--;
                if (-1 >= size2) {
                    lazyStaggeredGridItemInfo = null;
                    break;
                }
                if (visibleItemsInfo2.get(size2).getIndex() > i && (size2 == 0 || visibleItemsInfo2.get(size2 - 1).getIndex() <= i)) {
                    lazyStaggeredGridItemInfo = visibleItemsInfo2.get(size2);
                    break;
                }
            }
            LazyStaggeredGridItemInfo lazyStaggeredGridItemInfo4 = (LazyStaggeredGridItemInfo) CollectionsKt.last(lazyStaggeredGridMeasureContext.getApproachLayoutInfo().getVisibleItemsInfo());
            if (lazyStaggeredGridItemInfo != null && (index = lazyStaggeredGridItemInfo.getIndex()) <= (iMin = Math.min(lazyStaggeredGridItemInfo4.getIndex(), i2 - 1))) {
                ArrayList arrayList = null;
                while (true) {
                    if (arrayList != null) {
                        int size3 = arrayList.size();
                        int i4 = 0;
                        while (true) {
                            if (i4 >= size3) {
                                if (arrayList == null) {
                                    arrayList = new ArrayList();
                                }
                                visibleItemsInfo = lazyStaggeredGridMeasureContext.getApproachLayoutInfo().getVisibleItemsInfo();
                                size = visibleItemsInfo.size();
                                i3 = 0;
                                while (true) {
                                    if (i3 < size) {
                                        lazyStaggeredGridItemInfo2 = null;
                                        break;
                                    }
                                    lazyStaggeredGridItemInfo2 = visibleItemsInfo.get(i3);
                                    if (lazyStaggeredGridItemInfo2.getIndex() == index) {
                                        break;
                                    }
                                    i3++;
                                }
                                lazyStaggeredGridItemInfo3 = lazyStaggeredGridItemInfo2;
                                if (lazyStaggeredGridItemInfo3 != null) {
                                    lane = lazyStaggeredGridItemInfo3.getLane();
                                } else {
                                    lane = 0;
                                }
                                LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ = lazyStaggeredGridMeasureContext.getMeasuredItemProvider().m1200getAndMeasurejy6DScQ(index, lazyStaggeredGridMeasureContext.m1192getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext.getItemProvider(), index, lane));
                                arrayList.add(lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ);
                                int[] positions = lazyStaggeredGridMeasureContext.getResolvedSlots().getPositions();
                                function2.invoke(lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ, Integer.valueOf(positions.length > lane ? positions[lane] : 0));
                            } else if (arrayList.get(i4).getIndex() != index) {
                                i4++;
                            }
                        }
                    } else {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        visibleItemsInfo = lazyStaggeredGridMeasureContext.getApproachLayoutInfo().getVisibleItemsInfo();
                        size = visibleItemsInfo.size();
                        i3 = 0;
                        while (true) {
                            if (i3 < size) {
                                lazyStaggeredGridItemInfo2 = null;
                                break;
                            }
                            lazyStaggeredGridItemInfo2 = visibleItemsInfo.get(i3);
                            if (lazyStaggeredGridItemInfo2.getIndex() == index) {
                                break;
                                break;
                            }
                            i3++;
                        }
                        lazyStaggeredGridItemInfo3 = lazyStaggeredGridItemInfo2;
                        if (lazyStaggeredGridItemInfo3 != null) {
                            lane = lazyStaggeredGridItemInfo3.getLane();
                        } else {
                            lane = 0;
                        }
                        LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ2 = lazyStaggeredGridMeasureContext.getMeasuredItemProvider().m1200getAndMeasurejy6DScQ(index, lazyStaggeredGridMeasureContext.m1192getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext.getItemProvider(), index, lane));
                        arrayList.add(lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ2);
                        int[] positions2 = lazyStaggeredGridMeasureContext.getResolvedSlots().getPositions();
                        function2.invoke(lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ2, Integer.valueOf(positions2.length > lane ? positions2[lane] : 0));
                    }
                    if (index == iMin) {
                        return arrayList;
                    }
                    index++;
                }
            }
        }
        return null;
    }

    /* JADX INFO: renamed from: maxInRange-jy6DScQ, reason: not valid java name */
    private static final int m1196maxInRangejy6DScQ(int[] iArr, long j) {
        int i = (int) (j & 4294967295L);
        int iMax = Unset;
        for (int i2 = (int) (j >> 32); i2 < i; i2++) {
            iMax = Math.max(iMax, iArr[i2]);
        }
        return iMax;
    }

    /* JADX WARN: Code duplicated, block: B:266:0x0568  */
    /* JADX WARN: Code duplicated, block: B:270:0x0587 A[LOOP:19: B:245:0x050d->B:270:0x0587, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:328:0x06d1 A[LOOP:21: B:299:0x0632->B:328:0x06d1, LOOP_END] */
    /* JADX WARN: Code duplicated, block: B:329:0x06dc  */
    /* JADX WARN: Code duplicated, block: B:476:0x0585 A[EDGE_INSN: B:476:0x0585->B:269:0x0585 BREAK  A[LOOP:19: B:245:0x050d->B:270:0x0587], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:482:0x06e5 A[EDGE_INSN: B:482:0x06e5->B:330:0x06e5 BREAK  A[LOOP:21: B:299:0x0632->B:328:0x06d1], SYNTHETIC] */
    private static final LazyStaggeredGridMeasureResult measure(LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, int i, int[] iArr, int[] iArr2, boolean z) {
        char c;
        long j;
        int i2;
        int iIndexOf;
        int i3;
        int i4;
        int[] iArr3;
        int[] iArr4;
        int i5;
        int i6;
        int[] iArr5;
        int i7;
        int i8;
        List listEmptyList;
        int i9;
        int mainAxisSizeWithSpacings;
        ArrayList arrayList;
        boolean z2;
        List<Integer> list;
        int[] iArr6;
        int i10;
        LazyStaggeredGridItemInfo lazyStaggeredGridItemInfo;
        int index;
        int iMin;
        LazyStaggeredGridItemInfo lazyStaggeredGridItemInfo2;
        int index2;
        int i11;
        int[] gaps;
        final LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext2 = lazyStaggeredGridMeasureContext;
        LazyLayoutMeasureScope measureScope = lazyStaggeredGridMeasureContext2.getMeasureScope();
        int itemCount = lazyStaggeredGridMeasureContext2.getItemProvider().getItemCount();
        if (itemCount <= 0 || lazyStaggeredGridMeasureContext2.getLaneCount() == 0) {
            int i12 = Constraints.getMinWidth-impl(lazyStaggeredGridMeasureContext.getConstraints());
            int i13 = Constraints.getMinHeight-impl(lazyStaggeredGridMeasureContext.getConstraints());
            lazyStaggeredGridMeasureContext.getState().getItemAnimator$foundation().onMeasured(0, i12, i13, new ArrayList(), lazyStaggeredGridMeasureContext.getMeasuredItemProvider().getKeyIndexMap(), lazyStaggeredGridMeasureContext.getMeasuredItemProvider(), lazyStaggeredGridMeasureContext.getIsVertical(), measureScope.isLookingAhead(), lazyStaggeredGridMeasureContext.getLaneCount(), lazyStaggeredGridMeasureContext.getIsInLookaheadScope(), 0, 0, lazyStaggeredGridMeasureContext.getCoroutineScope(), lazyStaggeredGridMeasureContext.getGraphicsContext());
            if (!measureScope.isLookingAhead()) {
                long jM1123getMinSizeToFitDisappearingItemsYbymL2g = lazyStaggeredGridMeasureContext.getState().getItemAnimator$foundation().m1123getMinSizeToFitDisappearingItemsYbymL2g();
                if (!IntSize.equals-impl0(jM1123getMinSizeToFitDisappearingItemsYbymL2g, IntSize.Companion.getZero-YbymL2g())) {
                    i12 = ConstraintsKt.constrainWidth-K40F9xA(lazyStaggeredGridMeasureContext.getConstraints(), (int) (jM1123getMinSizeToFitDisappearingItemsYbymL2g >> 32));
                    i13 = ConstraintsKt.constrainHeight-K40F9xA(lazyStaggeredGridMeasureContext.getConstraints(), (int) (jM1123getMinSizeToFitDisappearingItemsYbymL2g & 4294967295L));
                }
            }
            return new LazyStaggeredGridMeasureResult(iArr, iArr2, 0.0f, MeasureScope.layout$default(measureScope, i12, i13, (Map) null, new Function1() { // from class: ax8
                public final Object invoke(Object obj) {
                    return LazyStaggeredGridMeasureKt.measure$lambda$0$0((Placeable.PlacementScope) obj);
                }
            }, 4, (Object) null), 0.0f, false, lazyStaggeredGridMeasureContext.getIsVertical(), false, lazyStaggeredGridMeasureContext.getResolvedSlots(), lazyStaggeredGridMeasureContext.getItemProvider().getSpanProvider(), measureScope, itemCount, CollectionsKt.emptyList(), IntSize.constructor-impl((((long) Constraints.getMinWidth-impl(lazyStaggeredGridMeasureContext.getConstraints())) << 32) | (((long) Constraints.getMinHeight-impl(lazyStaggeredGridMeasureContext.getConstraints())) & 4294967295L)), -lazyStaggeredGridMeasureContext.getBeforeContentPadding(), lazyStaggeredGridMeasureContext.getMainAxisAvailableSize() + lazyStaggeredGridMeasureContext.getAfterContentPadding(), lazyStaggeredGridMeasureContext.getBeforeContentPadding(), lazyStaggeredGridMeasureContext.getAfterContentPadding(), lazyStaggeredGridMeasureContext.getMainAxisSpacing(), lazyStaggeredGridMeasureContext.getCoroutineScope(), null);
        }
        int[] iArrCopyOf = Arrays.copyOf(iArr, iArr.length);
        int[] iArrCopyOf2 = Arrays.copyOf(iArr2, iArr2.length);
        ensureIndicesInRange(lazyStaggeredGridMeasureContext2, iArrCopyOf, itemCount);
        offsetBy(iArrCopyOf2, -i);
        int laneCount = lazyStaggeredGridMeasureContext2.getLaneCount();
        ArrayDeque[] arrayDequeArr = new ArrayDeque[laneCount];
        for (int i14 = 0; i14 < laneCount; i14++) {
            arrayDequeArr[i14] = new ArrayDeque(16);
        }
        offsetBy(iArrCopyOf2, -lazyStaggeredGridMeasureContext2.getBeforeContentPadding());
        boolean z3 = false;
        while (true) {
            c = ' ';
            if (!measure$lambda$0$hasSpaceBeforeFirst(iArrCopyOf, iArrCopyOf2, lazyStaggeredGridMeasureContext2)) {
                j = 4294967295L;
                i2 = 0;
                iIndexOf = -1;
                break;
            }
            iIndexOf = indexOfMaxValue(iArrCopyOf);
            j = 4294967295L;
            int i15 = iArrCopyOf[iIndexOf];
            int length = iArrCopyOf2.length;
            for (int i16 = 0; i16 < length; i16++) {
                if (iArrCopyOf[i16] != iArrCopyOf[iIndexOf]) {
                    int i17 = iArrCopyOf2[i16];
                    int i18 = iArrCopyOf2[iIndexOf];
                    if (i17 < i18) {
                        iArrCopyOf2[i16] = i18;
                    }
                }
            }
            i2 = 0;
            int iFindPreviousItemIndex = findPreviousItemIndex(lazyStaggeredGridMeasureContext2, i15, iIndexOf);
            if (iFindPreviousItemIndex < 0) {
                break;
            }
            long jM1192getSpanRangelOCCd4c = lazyStaggeredGridMeasureContext2.m1192getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext2.getItemProvider(), iFindPreviousItemIndex, iIndexOf);
            int i19 = (int) (jM1192getSpanRangelOCCd4c & 4294967295L);
            int i20 = (int) (jM1192getSpanRangelOCCd4c >> 32);
            int i21 = i19 - i20;
            lazyStaggeredGridMeasureContext2.getLaneInfo().setLane(iFindPreviousItemIndex, i21 != 1 ? -2 : i20);
            LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ = lazyStaggeredGridMeasureContext2.getMeasuredItemProvider().m1200getAndMeasurejy6DScQ(iFindPreviousItemIndex, jM1192getSpanRangelOCCd4c);
            int iM1196maxInRangejy6DScQ = m1196maxInRangejy6DScQ(iArrCopyOf2, jM1192getSpanRangelOCCd4c);
            int[] gaps2 = i21 != 1 ? lazyStaggeredGridMeasureContext2.getLaneInfo().getGaps(iFindPreviousItemIndex) : null;
            while (i20 < i19) {
                iArrCopyOf[i20] = iFindPreviousItemIndex;
                int mainAxisSizeWithSpacings2 = lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ.getMainAxisSizeWithSpacings() + iM1196maxInRangejy6DScQ + (gaps2 == null ? 0 : gaps2[i20]);
                iArrCopyOf2[i20] = mainAxisSizeWithSpacings2;
                if (lazyStaggeredGridMeasureContext2.getMainAxisAvailableSize() + mainAxisSizeWithSpacings2 <= 0) {
                    z3 = true;
                }
                i20++;
            }
        }
        int i22 = -lazyStaggeredGridMeasureContext2.getBeforeContentPadding();
        int i23 = iArrCopyOf2[i2];
        if (i23 < i22) {
            offsetBy(iArrCopyOf2, i22 - i23);
            i3 = i - (i22 - i23);
        } else {
            i3 = i;
        }
        offsetBy(iArrCopyOf2, lazyStaggeredGridMeasureContext2.getBeforeContentPadding());
        if (iIndexOf == -1) {
            iIndexOf = ArraysKt.indexOf(iArrCopyOf, i2);
        }
        if (iIndexOf != -1 && measure$lambda$0$misalignedStart(iArrCopyOf, lazyStaggeredGridMeasureContext2, iArrCopyOf2, iIndexOf) && z) {
            lazyStaggeredGridMeasureContext2.getLaneInfo().reset();
            int length2 = iArrCopyOf.length;
            int[] iArr7 = new int[length2];
            for (int i24 = 0; i24 < length2; i24++) {
                iArr7[i24] = -1;
            }
            int length3 = iArrCopyOf2.length;
            int[] iArr8 = new int[length3];
            for (int i25 = 0; i25 < length3; i25++) {
                iArr8[i25] = iArrCopyOf2[iIndexOf];
            }
            return measure(lazyStaggeredGridMeasureContext2, i3, iArr7, iArr8, false);
        }
        int[] iArrCopyOf3 = Arrays.copyOf(iArrCopyOf, iArrCopyOf.length);
        int length4 = iArrCopyOf2.length;
        int[] iArr9 = new int[length4];
        for (int i26 = 0; i26 < length4; i26++) {
            iArr9[i26] = -iArrCopyOf2[i26];
        }
        int mainAxisSpacing = i22 + lazyStaggeredGridMeasureContext2.getMainAxisSpacing();
        int iCoerceAtLeast = RangesKt.coerceAtLeast(lazyStaggeredGridMeasureContext2.getMainAxisAvailableSize() + lazyStaggeredGridMeasureContext2.getAfterContentPadding(), 0);
        ArrayDeque[] arrayDequeArr2 = arrayDequeArr;
        boolean z4 = z3;
        int iIndexOfMinValue$default = indexOfMinValue$default(iArrCopyOf3, 0, 1, null);
        int laneCount2 = 0;
        boolean z5 = z4;
        for (int i27 = -1; iIndexOfMinValue$default != i27 && laneCount2 < lazyStaggeredGridMeasureContext2.getLaneCount(); i27 = -1) {
            int i28 = iArrCopyOf3[iIndexOfMinValue$default];
            iIndexOfMinValue$default = indexOfMinValue(iArrCopyOf3, i28);
            laneCount2++;
            if (i28 >= 0) {
                long jM1192getSpanRangelOCCd4c2 = lazyStaggeredGridMeasureContext2.m1192getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext2.getItemProvider(), i28, iIndexOfMinValue$default);
                int i29 = i22;
                LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ2 = lazyStaggeredGridMeasureContext2.getMeasuredItemProvider().m1200getAndMeasurejy6DScQ(i28, jM1192getSpanRangelOCCd4c2);
                LazyLayoutMeasureScope lazyLayoutMeasureScope = measureScope;
                int[] iArr10 = iArrCopyOf;
                int[] iArr11 = iArrCopyOf2;
                int i30 = (int) (jM1192getSpanRangelOCCd4c2 & j);
                int i31 = i3;
                int i32 = (int) (jM1192getSpanRangelOCCd4c2 >> 32);
                int i33 = i30 - i32;
                lazyStaggeredGridMeasureContext2.getLaneInfo().setLane(i28, i33 != 1 ? -2 : i32);
                int iM1196maxInRangejy6DScQ2 = m1196maxInRangejy6DScQ(iArr9, jM1192getSpanRangelOCCd4c2);
                for (int i34 = i32; i34 < i30; i34++) {
                    iArr9[i34] = lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ2.getMainAxisSizeWithSpacings() + iM1196maxInRangejy6DScQ2;
                    iArrCopyOf3[i34] = i28;
                    arrayDequeArr2[i34].addLast(lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ2);
                }
                if (iM1196maxInRangejy6DScQ2 < mainAxisSpacing && iArr9[i32] <= mainAxisSpacing) {
                    lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ2.setVisible(false);
                    z5 = true;
                }
                laneCount2 = i33 != 1 ? lazyStaggeredGridMeasureContext2.getLaneCount() : laneCount2;
                i22 = i29;
                measureScope = lazyLayoutMeasureScope;
                iArrCopyOf = iArr10;
                iArrCopyOf2 = iArr11;
                i3 = i31;
            }
        }
        int i35 = i22;
        int[] iArr12 = iArrCopyOf;
        int[] iArr13 = iArrCopyOf2;
        int i36 = i3;
        final LazyLayoutMeasureScope lazyLayoutMeasureScope2 = measureScope;
        loop9: while (true) {
            int i37 = 0;
            while (true) {
                if (i37 >= length4) {
                    for (int i38 = 0; i38 < laneCount; i38++) {
                        if (!arrayDequeArr2[i38].isEmpty()) {
                            i4 = 1;
                            break loop9;
                        }
                    }
                    break;
                }
                int i39 = iArr9[i37];
                if (i39 < iCoerceAtLeast || i39 <= 0) {
                    break;
                }
                i37++;
            }
            i4 = 1;
            int iIndexOfMinValue$default2 = indexOfMinValue$default(iArr9, 0, 1, null);
            int iMaxOrThrow = ArraysKt.maxOrThrow(iArrCopyOf3) + 1;
            if (iMaxOrThrow >= itemCount) {
                break;
            }
            int i40 = iCoerceAtLeast;
            int[] iArr14 = iArrCopyOf3;
            ArrayDeque[] arrayDequeArr3 = arrayDequeArr2;
            boolean z6 = z5;
            char c2 = c;
            int i41 = i35;
            long jM1192getSpanRangelOCCd4c3 = lazyStaggeredGridMeasureContext2.m1192getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext2.getItemProvider(), iMaxOrThrow, iIndexOfMinValue$default2);
            int i42 = (int) (jM1192getSpanRangelOCCd4c3 & j);
            int i43 = (int) (jM1192getSpanRangelOCCd4c3 >> c2);
            int i44 = i42 - i43;
            lazyStaggeredGridMeasureContext2.getLaneInfo().setLane(iMaxOrThrow, i44 != 1 ? -2 : i43);
            LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ3 = lazyStaggeredGridMeasureContext.getMeasuredItemProvider().m1200getAndMeasurejy6DScQ(iMaxOrThrow, jM1192getSpanRangelOCCd4c3);
            int iM1196maxInRangejy6DScQ3 = m1196maxInRangejy6DScQ(iArr9, jM1192getSpanRangelOCCd4c3);
            if (i44 != 1) {
                gaps = lazyStaggeredGridMeasureContext.getLaneInfo().getGaps(iMaxOrThrow);
                if (gaps == null) {
                    gaps = new int[lazyStaggeredGridMeasureContext.getLaneCount()];
                }
            } else {
                gaps = null;
            }
            for (int i45 = i43; i45 < i42; i45++) {
                if (gaps != null) {
                    gaps[i45] = iM1196maxInRangejy6DScQ3 - iArr9[i45];
                }
                iArr14[i45] = iMaxOrThrow;
                iArr9[i45] = iM1196maxInRangejy6DScQ3 + lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ3.getMainAxisSizeWithSpacings();
                arrayDequeArr3[i45].addLast(lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ3);
            }
            lazyStaggeredGridMeasureContext.getLaneInfo().setGaps(iMaxOrThrow, gaps);
            if (iM1196maxInRangejy6DScQ3 < mainAxisSpacing && iArr9[i43] <= mainAxisSpacing) {
                lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ3.setVisible(false);
            }
            lazyStaggeredGridMeasureContext2 = lazyStaggeredGridMeasureContext;
            iCoerceAtLeast = i40;
            z5 = z6;
            i35 = i41;
            c = c2;
            iArrCopyOf3 = iArr14;
            arrayDequeArr2 = arrayDequeArr3;
        }
        int i46 = 0;
        while (i46 < laneCount) {
            ArrayDeque arrayDeque = arrayDequeArr2[i46];
            while (arrayDeque.size() > i4 && !((LazyStaggeredGridMeasuredItem) arrayDeque.first()).getIsVisible()) {
                LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem = (LazyStaggeredGridMeasuredItem) arrayDeque.removeFirst();
                int[] gaps3 = lazyStaggeredGridMeasuredItem.getSpan() != i4 ? lazyStaggeredGridMeasureContext2.getLaneInfo().getGaps(lazyStaggeredGridMeasuredItem.getIndex()) : null;
                iArr13[i46] = iArr13[i46] - (lazyStaggeredGridMeasuredItem.getMainAxisSizeWithSpacings() + (gaps3 == null ? 0 : gaps3[i46]));
                i4 = 1;
            }
            LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem2 = (LazyStaggeredGridMeasuredItem) arrayDeque.firstOrNull();
            iArr12[i46] = lazyStaggeredGridMeasuredItem2 != null ? lazyStaggeredGridMeasuredItem2.getIndex() : -1;
            i46++;
            i4 = 1;
        }
        for (int i47 : iArrCopyOf3) {
            if (i47 == itemCount - 1) {
                offsetBy(iArr9, -lazyStaggeredGridMeasureContext2.getMainAxisSpacing());
                break;
            }
        }
        int i48 = 0;
        while (true) {
            if (i48 >= length4) {
                int mainAxisAvailableSize = lazyStaggeredGridMeasureContext2.getMainAxisAvailableSize() - iArr9[indexOfMaxValue(iArr9)];
                iArr3 = iArr13;
                offsetBy(iArr3, -mainAxisAvailableSize);
                offsetBy(iArr9, mainAxisAvailableSize);
                boolean z7 = false;
                loop31: while (true) {
                    int length5 = iArr3.length;
                    int i49 = 0;
                    while (true) {
                        if (i49 >= length5) {
                            iArr4 = iArr12;
                            break loop31;
                        }
                        if (iArr3[i49] < lazyStaggeredGridMeasureContext2.getBeforeContentPadding()) {
                            break;
                        }
                        i49++;
                        iArr12 = iArr12;
                    }
                    int iIndexOfMinValue$default3 = indexOfMinValue$default(iArr3, 0, 1, null);
                    int iIndexOfMaxValue = indexOfMaxValue(iArr12);
                    if (iIndexOfMinValue$default3 != iIndexOfMaxValue) {
                        if (iArr3[iIndexOfMinValue$default3] == iArr3[iIndexOfMaxValue]) {
                            iIndexOfMinValue$default3 = iIndexOfMaxValue;
                        } else {
                            z7 = true;
                        }
                    }
                    int i50 = iArr12[iIndexOfMinValue$default3];
                    if (i50 == -1) {
                        i50 = itemCount;
                    }
                    int iFindPreviousItemIndex2 = findPreviousItemIndex(lazyStaggeredGridMeasureContext2, i50, iIndexOfMinValue$default3);
                    if (iFindPreviousItemIndex2 < 0) {
                        iArr4 = iArr12;
                        if ((!z7 && !measure$lambda$0$misalignedStart(iArr4, lazyStaggeredGridMeasureContext2, iArr3, iIndexOfMinValue$default3)) || !z) {
                            break;
                            break;
                        }
                        lazyStaggeredGridMeasureContext2.getLaneInfo().reset();
                        int length6 = iArr4.length;
                        int[] iArr15 = new int[length6];
                        for (int i51 = 0; i51 < length6; i51++) {
                            iArr15[i51] = -1;
                        }
                        int length7 = iArr3.length;
                        int[] iArr16 = new int[length7];
                        for (int i52 = 0; i52 < length7; i52++) {
                            iArr16[i52] = iArr3[iIndexOfMinValue$default3];
                        }
                        return measure(lazyStaggeredGridMeasureContext2, i36, iArr15, iArr16, false);
                    }
                    int[] iArr17 = iArr12;
                    int i53 = i36;
                    boolean z8 = z7;
                    int i54 = mainAxisAvailableSize;
                    long jM1192getSpanRangelOCCd4c4 = lazyStaggeredGridMeasureContext2.m1192getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext2.getItemProvider(), iFindPreviousItemIndex2, iIndexOfMinValue$default3);
                    int[] iArr18 = iArrCopyOf3;
                    int i55 = (int) (jM1192getSpanRangelOCCd4c4 & j);
                    int i56 = itemCount;
                    int i57 = (int) (jM1192getSpanRangelOCCd4c4 >> c);
                    int i58 = i55 - i57;
                    int i59 = iCoerceAtLeast;
                    lazyStaggeredGridMeasureContext2.getLaneInfo().setLane(iFindPreviousItemIndex2, i58 != 1 ? -2 : i57);
                    LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ4 = lazyStaggeredGridMeasureContext2.getMeasuredItemProvider().m1200getAndMeasurejy6DScQ(iFindPreviousItemIndex2, jM1192getSpanRangelOCCd4c4);
                    int iM1196maxInRangejy6DScQ4 = m1196maxInRangejy6DScQ(iArr3, jM1192getSpanRangelOCCd4c4);
                    int[] gaps4 = i58 != 1 ? lazyStaggeredGridMeasureContext2.getLaneInfo().getGaps(iFindPreviousItemIndex2) : null;
                    while (i57 < i55) {
                        if (iArr3[i57] != iM1196maxInRangejy6DScQ4) {
                            z8 = true;
                        }
                        arrayDequeArr2[i57].addFirst(lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ4);
                        iArr17[i57] = iFindPreviousItemIndex2;
                        iArr3[i57] = lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ4.getMainAxisSizeWithSpacings() + iM1196maxInRangejy6DScQ4 + (gaps4 == null ? 0 : gaps4[i57]);
                        i57++;
                    }
                    mainAxisAvailableSize = i54;
                    i36 = i53;
                    z7 = z8;
                    iArrCopyOf3 = iArr18;
                    itemCount = i56;
                    iCoerceAtLeast = i59;
                    iArr12 = iArr17;
                }
                i5 = i36;
                int i60 = mainAxisAvailableSize;
                i6 = iCoerceAtLeast;
                iArr5 = iArrCopyOf3;
                i7 = itemCount;
                if (!z7 || !z) {
                    i8 = i5 + i60;
                    int i61 = iArr3[indexOfMinValue$default(iArr3, 0, 1, null)];
                    if (i61 >= 0) {
                        break;
                    }
                    i8 += i61;
                    offsetBy(iArr9, i61);
                    offsetBy(iArr3, -i61);
                    break;
                }
                lazyStaggeredGridMeasureContext2.getLaneInfo().reset();
                return measure(lazyStaggeredGridMeasureContext2, i5, iArr4, iArr3, false);
            }
            if (iArr9[i48] >= lazyStaggeredGridMeasureContext2.getMainAxisAvailableSize()) {
                iArr5 = iArrCopyOf3;
                iArr4 = iArr12;
                iArr3 = iArr13;
                i8 = i36;
                i5 = i8;
                i6 = iCoerceAtLeast;
                i7 = itemCount;
                break;
            }
            i48++;
        }
        float fScrollToBeConsumed$foundation = lazyStaggeredGridMeasureContext2.getState().scrollToBeConsumed$foundation(lazyLayoutMeasureScope2.isLookingAhead());
        float f = (MathKt.getSign(Math.round(fScrollToBeConsumed$foundation)) != MathKt.getSign(i8) || Math.abs(Math.round(fScrollToBeConsumed$foundation)) < Math.abs(i8)) ? fScrollToBeConsumed$foundation : i8;
        float f2 = fScrollToBeConsumed$foundation - f;
        float f3 = 0.0f;
        if (lazyLayoutMeasureScope2.isLookingAhead() && i8 > i5 && f2 <= 0.0f) {
            f3 = (i8 - i5) + f2;
        }
        float f4 = f3;
        int[] iArrCopyOf4 = Arrays.copyOf(iArr3, iArr3.length);
        int length8 = iArrCopyOf4.length;
        for (int i62 = 0; i62 < length8; i62++) {
            iArrCopyOf4[i62] = -iArrCopyOf4[i62];
        }
        if (lazyStaggeredGridMeasureContext2.getBeforeContentPadding() > lazyStaggeredGridMeasureContext2.getMainAxisSpacing()) {
            for (int i63 = 0; i63 < laneCount; i63++) {
                ArrayDeque arrayDeque2 = arrayDequeArr2[i63];
                int size = arrayDeque2.size();
                int i64 = 0;
                while (i64 < size) {
                    LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem3 = (LazyStaggeredGridMeasuredItem) arrayDeque2.get(i64);
                    int[] gaps5 = lazyStaggeredGridMeasureContext2.getLaneInfo().getGaps(lazyStaggeredGridMeasuredItem3.getIndex());
                    int mainAxisSizeWithSpacings3 = lazyStaggeredGridMeasuredItem3.getMainAxisSizeWithSpacings() + (gaps5 == null ? 0 : gaps5[i63]);
                    if (i64 == CollectionsKt.getLastIndex(arrayDeque2) || (i11 = iArr3[i63]) == 0 || i11 < mainAxisSizeWithSpacings3) {
                        break;
                    }
                    iArr3[i63] = i11 - mainAxisSizeWithSpacings3;
                    i64++;
                    iArr4[i63] = ((LazyStaggeredGridMeasuredItem) arrayDeque2.get(i64)).getIndex();
                }
            }
        }
        int beforeContentPadding = lazyStaggeredGridMeasureContext2.getBeforeContentPadding() + lazyStaggeredGridMeasureContext2.getAfterContentPadding();
        int i65 = lazyStaggeredGridMeasureContext2.getIsVertical() ? Constraints.getMaxWidth-impl(lazyStaggeredGridMeasureContext2.getConstraints()) : ConstraintsKt.constrainWidth-K40F9xA(lazyStaggeredGridMeasureContext2.getConstraints(), ArraysKt.maxOrThrow(iArr9) + beforeContentPadding);
        int i66 = lazyStaggeredGridMeasureContext2.getIsVertical() ? ConstraintsKt.constrainHeight-K40F9xA(lazyStaggeredGridMeasureContext2.getConstraints(), ArraysKt.maxOrThrow(iArr9) + beforeContentPadding) : Constraints.getMaxHeight-impl(lazyStaggeredGridMeasureContext2.getConstraints());
        int iMin2 = (Math.min(lazyStaggeredGridMeasureContext2.getIsVertical() ? i66 : i65, lazyStaggeredGridMeasureContext2.getMainAxisAvailableSize()) - lazyStaggeredGridMeasureContext2.getBeforeContentPadding()) + lazyStaggeredGridMeasureContext2.getAfterContentPadding();
        int i67 = iArrCopyOf4[0];
        List<Integer> pinnedItems = lazyStaggeredGridMeasureContext2.getPinnedItems();
        int size2 = pinnedItems.size() - 1;
        if (size2 >= 0) {
            int i68 = size2;
            ArrayList arrayList2 = null;
            while (true) {
                int i69 = i68 - 1;
                int iIntValue = pinnedItems.get(i68).intValue();
                int lane = lazyStaggeredGridMeasureContext2.getLaneInfo().getLane(iIntValue);
                int i70 = i67;
                if (lane != -2) {
                    index2 = -1;
                    if (lane != -1) {
                        LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem4 = (LazyStaggeredGridMeasuredItem) arrayDequeArr2[lane].firstOrNull();
                        if ((lazyStaggeredGridMeasuredItem4 != null ? lazyStaggeredGridMeasuredItem4.getIndex() : -1) > iIntValue) {
                            long jM1192getSpanRangelOCCd4c5 = lazyStaggeredGridMeasureContext2.m1192getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext2.getItemProvider(), iIntValue, 0);
                            if (arrayList2 == null) {
                                arrayList2 = new ArrayList();
                            }
                            LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ5 = lazyStaggeredGridMeasureContext2.getMeasuredItemProvider().m1200getAndMeasurejy6DScQ(iIntValue, jM1192getSpanRangelOCCd4c5);
                            int mainAxisSizeWithSpacings4 = i70 - lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ5.getMainAxisSizeWithSpacings();
                            lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ5.position(mainAxisSizeWithSpacings4, 0, iMin2);
                            arrayList2.add(lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ5);
                            i67 = mainAxisSizeWithSpacings4;
                        } else {
                            i67 = i70;
                        }
                    }
                    if (i69 < 0) {
                        break;
                    }
                    i68 = i69;
                    pinnedItems = pinnedItems;
                } else {
                    index2 = -1;
                }
                int i71 = 0;
                while (true) {
                    if (i71 >= laneCount) {
                        long jM1192getSpanRangelOCCd4c6 = lazyStaggeredGridMeasureContext2.m1192getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext2.getItemProvider(), iIntValue, 0);
                        if (arrayList2 == null) {
                            arrayList2 = new ArrayList();
                        }
                        LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ6 = lazyStaggeredGridMeasureContext2.getMeasuredItemProvider().m1200getAndMeasurejy6DScQ(iIntValue, jM1192getSpanRangelOCCd4c6);
                        int mainAxisSizeWithSpacings5 = i70 - lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ6.getMainAxisSizeWithSpacings();
                        lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ6.position(mainAxisSizeWithSpacings5, 0, iMin2);
                        arrayList2.add(lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ6);
                        i67 = mainAxisSizeWithSpacings5;
                        if (i69 < 0) {
                            break;
                            break;
                        }
                        i68 = i69;
                        pinnedItems = pinnedItems;
                    } else {
                        LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem5 = (LazyStaggeredGridMeasuredItem) arrayDequeArr2[i71].firstOrNull();
                        if (lazyStaggeredGridMeasuredItem5 != null) {
                            index2 = lazyStaggeredGridMeasuredItem5.getIndex();
                        }
                        if (index2 > iIntValue) {
                            i71++;
                            index2 = -1;
                        } else {
                            i67 = i70;
                            if (i69 < 0) {
                                break;
                                break;
                            }
                            i68 = i69;
                            pinnedItems = pinnedItems;
                        }
                    }
                }
            }
            listEmptyList = arrayList2;
        } else {
            listEmptyList = null;
        }
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        int i72 = i35;
        List list2 = listEmptyList;
        int i73 = i6;
        int i74 = -1;
        List<LazyStaggeredGridMeasuredItem> listCalculateVisibleItems = calculateVisibleItems(lazyStaggeredGridMeasureContext2, arrayDequeArr2, iArrCopyOf4, iMin2, i72, i73);
        char c3 = c;
        int i75 = iArrCopyOf4[0];
        LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItem6 = (LazyStaggeredGridMeasuredItem) CollectionsKt.lastOrNull(listCalculateVisibleItems);
        int index3 = lazyStaggeredGridMeasuredItem6 != null ? lazyStaggeredGridMeasuredItem6.getIndex() : -1;
        if (!lazyLayoutMeasureScope2.isLookingAhead() || lazyStaggeredGridMeasureContext2.getApproachLayoutInfo() == null || lazyStaggeredGridMeasureContext2.getApproachLayoutInfo().getVisibleItemsInfo().isEmpty()) {
            i9 = beforeContentPadding;
            iArr3 = iArr3;
            iArr4 = iArr4;
            f4 = f4;
            mainAxisSizeWithSpacings = i75;
            arrayList = null;
        } else {
            List<LazyStaggeredGridItemInfo> visibleItemsInfo = lazyStaggeredGridMeasureContext2.getApproachLayoutInfo().getVisibleItemsInfo();
            int size3 = visibleItemsInfo.size() - 1;
            while (true) {
                if (i74 >= size3) {
                    lazyStaggeredGridItemInfo = null;
                    break;
                }
                if (visibleItemsInfo.get(size3).getIndex() > index3 && (size3 == 0 || visibleItemsInfo.get(size3 - 1).getIndex() <= index3)) {
                    lazyStaggeredGridItemInfo = visibleItemsInfo.get(size3);
                    break;
                }
                size3--;
                i74 = -1;
            }
            LazyStaggeredGridItemInfo lazyStaggeredGridItemInfo3 = (LazyStaggeredGridItemInfo) CollectionsKt.last(lazyStaggeredGridMeasureContext2.getApproachLayoutInfo().getVisibleItemsInfo());
            if (lazyStaggeredGridItemInfo != null && (index = lazyStaggeredGridItemInfo.getIndex()) <= (iMin = Math.min(lazyStaggeredGridItemInfo3.getIndex(), i7 - 1))) {
                int i76 = index;
                mainAxisSizeWithSpacings = i75;
                arrayList = null;
                while (true) {
                    if (arrayList != null) {
                        int size4 = arrayList.size();
                        i9 = beforeContentPadding;
                        int i77 = 0;
                        while (true) {
                            if (i77 < size4) {
                                ArrayList arrayList3 = arrayList;
                                if (((LazyStaggeredGridMeasuredItem) arrayList.get(i77)).getIndex() == i76) {
                                    arrayList = arrayList3;
                                } else {
                                    i77++;
                                    arrayList = arrayList3;
                                }
                            }
                            if (i76 != iMin) {
                                break;
                            }
                            i76++;
                            beforeContentPadding = i9;
                            iArr3 = iArr3;
                            f4 = f4;
                            iArr4 = iArr4;
                        }
                    } else {
                        i9 = beforeContentPadding;
                    }
                    ArrayList arrayList4 = arrayList;
                    arrayList = arrayList4 == null ? new ArrayList() : arrayList4;
                    List<LazyStaggeredGridItemInfo> visibleItemsInfo2 = lazyStaggeredGridMeasureContext2.getApproachLayoutInfo().getVisibleItemsInfo();
                    int size5 = visibleItemsInfo2.size();
                    int i78 = 0;
                    while (true) {
                        if (i78 >= size5) {
                            lazyStaggeredGridItemInfo2 = null;
                            break;
                        }
                        lazyStaggeredGridItemInfo2 = visibleItemsInfo2.get(i78);
                        List<LazyStaggeredGridItemInfo> list3 = visibleItemsInfo2;
                        if (lazyStaggeredGridItemInfo2.getIndex() == i76) {
                            break;
                        }
                        i78++;
                        visibleItemsInfo2 = list3;
                    }
                    LazyStaggeredGridItemInfo lazyStaggeredGridItemInfo4 = lazyStaggeredGridItemInfo2;
                    int lane2 = lazyStaggeredGridItemInfo4 != null ? lazyStaggeredGridItemInfo4.getLane() : 0;
                    LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ7 = lazyStaggeredGridMeasureContext2.getMeasuredItemProvider().m1200getAndMeasurejy6DScQ(i76, lazyStaggeredGridMeasureContext2.m1192getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext2.getItemProvider(), i76, lane2));
                    arrayList.add(lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ7);
                    int[] positions = lazyStaggeredGridMeasureContext2.getResolvedSlots().getPositions();
                    lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ7.position(mainAxisSizeWithSpacings, positions.length > lane2 ? positions[lane2] : 0, iMin2);
                    mainAxisSizeWithSpacings += lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ7.getMainAxisSizeWithSpacings();
                    if (i76 != iMin) {
                        break;
                        break;
                    }
                    i76++;
                    beforeContentPadding = i9;
                    iArr3 = iArr3;
                    f4 = f4;
                    iArr4 = iArr4;
                }
            } else {
                i9 = beforeContentPadding;
                iArr3 = iArr3;
                iArr4 = iArr4;
                f4 = f4;
                mainAxisSizeWithSpacings = i75;
                arrayList = null;
            }
        }
        List<Integer> pinnedItems2 = lazyStaggeredGridMeasureContext2.getPinnedItems();
        int size6 = pinnedItems2.size();
        int i79 = 0;
        List listEmptyList2 = null;
        while (i79 < size6) {
            int iIntValue2 = pinnedItems2.get(i79).intValue();
            int i80 = i7;
            if (iIntValue2 >= i80) {
                list = pinnedItems2;
            } else {
                if (arrayList != null) {
                    int size7 = arrayList.size();
                    list = pinnedItems2;
                    int i81 = 0;
                    while (true) {
                        if (i81 < size7) {
                            int i82 = i81;
                            if (((LazyStaggeredGridMeasuredItem) arrayList.get(i81)).getIndex() != iIntValue2) {
                                i81 = i82 + 1;
                            }
                        }
                    }
                } else {
                    list = pinnedItems2;
                }
                int lane3 = lazyStaggeredGridMeasureContext2.getLaneInfo().getLane(iIntValue2);
                if (lane3 == -2 || lane3 == -1) {
                    iArr6 = iArr5;
                    int length9 = iArr6.length;
                    i10 = size6;
                    int i83 = 0;
                    while (true) {
                        if (i83 < length9) {
                            int i84 = i83;
                            if (iArr6[i84] < iIntValue2) {
                                i83 = i84 + 1;
                            } else {
                                i79++;
                                size6 = i10;
                                i7 = i80;
                                iArr5 = iArr6;
                                pinnedItems2 = list;
                            }
                        }
                    }
                } else if (iArr5[lane3] < iIntValue2) {
                    iArr6 = iArr5;
                    i10 = size6;
                }
                long jM1192getSpanRangelOCCd4c7 = lazyStaggeredGridMeasureContext2.m1192getSpanRangelOCCd4c(lazyStaggeredGridMeasureContext2.getItemProvider(), iIntValue2, 0);
                if (listEmptyList2 == null) {
                    listEmptyList2 = new ArrayList();
                }
                List list4 = listEmptyList2;
                LazyStaggeredGridMeasuredItem lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ8 = lazyStaggeredGridMeasureContext2.getMeasuredItemProvider().m1200getAndMeasurejy6DScQ(iIntValue2, jM1192getSpanRangelOCCd4c7);
                lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ8.position(mainAxisSizeWithSpacings, 0, iMin2);
                mainAxisSizeWithSpacings += lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ8.getMainAxisSizeWithSpacings();
                list4.add(lazyStaggeredGridMeasuredItemM1200getAndMeasurejy6DScQ8);
                listEmptyList2 = list4;
                i79++;
                size6 = i10;
                i7 = i80;
                iArr5 = iArr6;
                pinnedItems2 = list;
            }
            iArr6 = iArr5;
            i10 = size6;
            i79++;
            size6 = i10;
            i7 = i80;
            iArr5 = iArr6;
            pinnedItems2 = list;
        }
        int[] iArr19 = iArr5;
        int i85 = i7;
        if (listEmptyList2 == null) {
            listEmptyList2 = CollectionsKt.emptyList();
        }
        final ArrayList arrayList5 = new ArrayList();
        arrayList5.addAll(list2);
        arrayList5.addAll(listCalculateVisibleItems);
        if (arrayList != null) {
            arrayList5.addAll(arrayList);
        }
        arrayList5.addAll(listEmptyList2);
        lazyStaggeredGridMeasureContext2.getState().getItemAnimator$foundation().onMeasured((int) f, i65, i66, arrayList5, lazyStaggeredGridMeasureContext2.getMeasuredItemProvider().getKeyIndexMap(), lazyStaggeredGridMeasureContext2.getMeasuredItemProvider(), lazyStaggeredGridMeasureContext2.getIsVertical(), lazyLayoutMeasureScope2.isLookingAhead(), lazyStaggeredGridMeasureContext2.getLaneCount(), lazyStaggeredGridMeasureContext2.getIsInLookaheadScope(), ArraysKt.minOrThrow(iArr3), ArraysKt.maxOrThrow(iArr9) + i9, lazyStaggeredGridMeasureContext2.getCoroutineScope(), lazyStaggeredGridMeasureContext2.getGraphicsContext());
        int i86 = i65;
        int i87 = i66;
        if (!lazyLayoutMeasureScope2.isLookingAhead()) {
            long jM1123getMinSizeToFitDisappearingItemsYbymL2g2 = lazyStaggeredGridMeasureContext2.getState().getItemAnimator$foundation().m1123getMinSizeToFitDisappearingItemsYbymL2g();
            if (!IntSize.equals-impl0(jM1123getMinSizeToFitDisappearingItemsYbymL2g2, IntSize.Companion.getZero-YbymL2g())) {
                int i88 = lazyStaggeredGridMeasureContext2.getIsVertical() ? i87 : i86;
                int i89 = ConstraintsKt.constrainWidth-K40F9xA(lazyStaggeredGridMeasureContext2.getConstraints(), Math.max(i86, (int) (jM1123getMinSizeToFitDisappearingItemsYbymL2g2 >> c3)));
                int i90 = ConstraintsKt.constrainHeight-K40F9xA(lazyStaggeredGridMeasureContext2.getConstraints(), Math.max(i87, (int) (jM1123getMinSizeToFitDisappearingItemsYbymL2g2 & j)));
                int i91 = lazyStaggeredGridMeasureContext2.getIsVertical() ? i90 : i89;
                if (i91 != i88) {
                    int size8 = arrayList5.size();
                    for (int i92 = 0; i92 < size8; i92++) {
                        ((LazyStaggeredGridMeasuredItem) arrayList5.get(i92)).updateMainAxisLayoutSize(i91);
                    }
                }
                i86 = i89;
                i87 = i90;
            }
        }
        int i93 = 0;
        while (true) {
            if (i93 >= length4) {
                for (int i94 : iArr19) {
                    if (i94 >= i85 - 1) {
                        z2 = false;
                        return new LazyStaggeredGridMeasureResult(iArr4, iArr3, f, MeasureScope.layout$default(lazyLayoutMeasureScope2, i86, i87, (Map) null, new Function1() { // from class: bx8
                            public final Object invoke(Object obj) {
                                return LazyStaggeredGridMeasureKt.measure$lambda$0$37(lazyStaggeredGridMeasureContext2, arrayList5, lazyLayoutMeasureScope2, (Placeable.PlacementScope) obj);
                            }
                        }, 4, (Object) null), f4, z2, lazyStaggeredGridMeasureContext.getIsVertical(), z5, lazyStaggeredGridMeasureContext.getResolvedSlots(), lazyStaggeredGridMeasureContext.getItemProvider().getSpanProvider(), lazyLayoutMeasureScope2, i85, listCalculateVisibleItems, IntSize.constructor-impl((((long) i87) & j) | (((long) i86) << c3)), i72, i73, lazyStaggeredGridMeasureContext.getBeforeContentPadding(), lazyStaggeredGridMeasureContext.getAfterContentPadding(), lazyStaggeredGridMeasureContext.getMainAxisSpacing(), lazyStaggeredGridMeasureContext.getCoroutineScope(), null);
                    }
                }
                break;
            }
            if (iArr9[i93] > lazyStaggeredGridMeasureContext2.getMainAxisAvailableSize()) {
                break;
            }
            i93++;
        }
        z2 = true;
        return new LazyStaggeredGridMeasureResult(iArr4, iArr3, f, MeasureScope.layout$default(lazyLayoutMeasureScope2, i86, i87, (Map) null, new Function1() { // from class: bx8
            public final Object invoke(Object obj) {
                return LazyStaggeredGridMeasureKt.measure$lambda$0$37(lazyStaggeredGridMeasureContext2, arrayList5, lazyLayoutMeasureScope2, (Placeable.PlacementScope) obj);
            }
        }, 4, (Object) null), f4, z2, lazyStaggeredGridMeasureContext.getIsVertical(), z5, lazyStaggeredGridMeasureContext.getResolvedSlots(), lazyStaggeredGridMeasureContext.getItemProvider().getSpanProvider(), lazyLayoutMeasureScope2, i85, listCalculateVisibleItems, IntSize.constructor-impl((((long) i87) & j) | (((long) i86) << c3)), i72, i73, lazyStaggeredGridMeasureContext.getBeforeContentPadding(), lazyStaggeredGridMeasureContext.getAfterContentPadding(), lazyStaggeredGridMeasureContext.getMainAxisSpacing(), lazyStaggeredGridMeasureContext.getCoroutineScope(), null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measure$lambda$0$0(Placeable.PlacementScope placementScope) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measure$lambda$0$37(final LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, final List list, final LazyLayoutMeasureScope lazyLayoutMeasureScope, Placeable.PlacementScope placementScope) {
        placementScope.withMotionFrameOfReferencePlacement(new Function1() { // from class: zw8
            public final Object invoke(Object obj) {
                return LazyStaggeredGridMeasureKt.measure$lambda$0$37$0(list, lazyStaggeredGridMeasureContext, lazyLayoutMeasureScope, (Placeable.PlacementScope) obj);
            }
        });
        ObservableScopeInvalidator.m1154attachToScopeimpl(lazyStaggeredGridMeasureContext.getState().m1204getPlacementScopeInvalidatorzYiylxw$foundation());
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measure$lambda$0$37$0(List list, LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, LazyLayoutMeasureScope lazyLayoutMeasureScope, Placeable.PlacementScope placementScope) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ((LazyStaggeredGridMeasuredItem) list.get(i)).place(placementScope, lazyStaggeredGridMeasureContext, lazyLayoutMeasureScope.isLookingAhead());
        }
        return Unit.INSTANCE;
    }

    private static final boolean measure$lambda$0$hasSpaceBeforeFirst(int[] iArr, int[] iArr2, LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext) {
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            int i2 = iArr[i];
            if (iArr2[i] < Math.max(-lazyStaggeredGridMeasureContext.getMainAxisSpacing(), 0) && i2 > 0) {
                return true;
            }
        }
        return false;
    }

    private static final boolean measure$lambda$0$misalignedStart(int[] iArr, LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext, int[] iArr2, int i) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            if (findPreviousItemIndex(lazyStaggeredGridMeasureContext, iArr[i2], i2) == -1 && iArr2[i2] != iArr2[i]) {
                return true;
            }
        }
        int length2 = iArr.length;
        for (int i3 = 0; i3 < length2; i3++) {
            if (findPreviousItemIndex(lazyStaggeredGridMeasureContext, iArr[i3], i3) != -1 && iArr2[i3] >= iArr2[i]) {
                return true;
            }
        }
        int lane = lazyStaggeredGridMeasureContext.getLaneInfo().getLane(0);
        return (lane == 0 || lane == -1 || lane == -2) ? false : true;
    }

    /* JADX INFO: renamed from: measureStaggeredGrid-C6celF4, reason: not valid java name */
    public static final LazyStaggeredGridMeasureResult m1197measureStaggeredGridC6celF4(LazyLayoutMeasureScope lazyLayoutMeasureScope, LazyStaggeredGridState lazyStaggeredGridState, List<Integer> list, LazyStaggeredGridItemProvider lazyStaggeredGridItemProvider, LazyStaggeredGridSlots lazyStaggeredGridSlots, long j, boolean z, boolean z2, long j2, int i, int i2, int i3, int i4, CoroutineScope coroutineScope, boolean z3, boolean z4, LazyStaggeredGridLayoutInfo lazyStaggeredGridLayoutInfo, GraphicsContext graphicsContext) {
        int i5;
        int iM1196maxInRangejy6DScQ;
        LazyStaggeredGridMeasureContext lazyStaggeredGridMeasureContext = new LazyStaggeredGridMeasureContext(lazyStaggeredGridState, list, lazyStaggeredGridItemProvider, lazyStaggeredGridSlots, j, z, lazyLayoutMeasureScope, i, j2, i3, i4, z2, i2, coroutineScope, z3, z4, lazyStaggeredGridLayoutInfo, graphicsContext, null);
        int[] iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation = lazyStaggeredGridState.updateScrollPositionIfTheFirstItemWasMoved$foundation(lazyStaggeredGridItemProvider, lazyStaggeredGridState.getScrollPosition().getIndices());
        int[] scrollOffsets = lazyStaggeredGridState.getScrollPosition().getScrollOffsets();
        if (iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation.length != lazyStaggeredGridMeasureContext.getLaneCount()) {
            lazyStaggeredGridMeasureContext.getLaneInfo().reset();
            int laneCount = lazyStaggeredGridMeasureContext.getLaneCount();
            int[] iArr = new int[laneCount];
            int i6 = 0;
            while (i6 < laneCount) {
                if (i6 >= iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation.length || (iM1196maxInRangejy6DScQ = iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation[i6]) == -1) {
                    iM1196maxInRangejy6DScQ = i6 == 0 ? 0 : m1196maxInRangejy6DScQ(iArr, SpanRange.m1206constructorimpl(0, i6)) + 1;
                }
                iArr[i6] = iM1196maxInRangejy6DScQ;
                lazyStaggeredGridMeasureContext.getLaneInfo().setLane(iArr[i6], i6);
                i6++;
            }
            iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation = iArr;
        }
        if (scrollOffsets.length != lazyStaggeredGridMeasureContext.getLaneCount()) {
            int laneCount2 = lazyStaggeredGridMeasureContext.getLaneCount();
            int[] iArr2 = new int[laneCount2];
            int i7 = 0;
            while (i7 < laneCount2) {
                if (i7 < scrollOffsets.length) {
                    i5 = scrollOffsets[i7];
                } else {
                    i5 = i7 == 0 ? 0 : iArr2[i7 - 1];
                }
                iArr2[i7] = i5;
                i7++;
            }
            scrollOffsets = iArr2;
        }
        return measure(lazyStaggeredGridMeasureContext, Math.round(lazyStaggeredGridState.scrollToBeConsumed$foundation(z4)), iArrUpdateScrollPositionIfTheFirstItemWasMoved$foundation, scrollOffsets, true);
    }

    private static final void offsetBy(int[] iArr, int i) {
        int length = iArr.length;
        for (int i2 = 0; i2 < length; i2++) {
            iArr[i2] = iArr[i2] + i;
        }
    }

    private static final int[] transform(int[] iArr, Function1<? super Integer, Integer> function1) {
        int length = iArr.length;
        for (int i = 0; i < length; i++) {
            iArr[i] = ((Number) function1.invoke(Integer.valueOf(iArr[i]))).intValue();
        }
        return iArr;
    }

    private static final <T> T withDebugLogging(LazyLayoutMeasureScope lazyLayoutMeasureScope, Function1<? super LazyLayoutMeasureScope, ? extends T> function1) {
        return (T) function1.invoke(lazyLayoutMeasureScope);
    }
}
