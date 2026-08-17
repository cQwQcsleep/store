package androidx.compose.foundation.lazy.grid;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.lazy.grid.LazyGridMeasureKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutItemAnimator;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasuredItemKt;
import androidx.compose.foundation.lazy.layout.LazyLayoutStickyItemsKt;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.foundation.lazy.layout.StickyItemsPlacement;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.graphics.GraphicsContext;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.math.MathKt;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000¸\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\u000b\n\u0002\u0010\u0011\n\u0002\b\u0002\u001aü\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\u00032\u0006\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c2\u0006\u0010\u001e\u001a\u00020\u00032\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030 2\u0006\u0010!\u001a\u00020\u00132\u0006\u0010\"\u001a\u00020\u00132\b\u0010#\u001a\u0004\u0018\u00010$2\u0006\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\u0006\u0010)\u001a\u00020*23\u0010+\u001a/\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(/\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0011000 0,2!\u00101\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b-\u0012\b\b.\u0012\u0004\b\b(2\u0012\u0004\u0012\u00020\u00030,2\b\u00103\u001a\u0004\u0018\u0001042/\u00105\u001a+\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u0002080,¢\u0006\u0002\b9\u0012\u0004\u0012\u00020:06H\u0000¢\u0006\u0004\b;\u0010<\u001aA\u0010=\u001a\b\u0012\u0004\u0012\u00020\u001d0 2\f\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00030 2\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0004\u001a\u00020\u00052\u0012\u0010>\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00130,H\u0082\b\u001aF\u0010?\u001a\b\u0012\u0004\u0012\u00020@0 2\u0006\u0010A\u001a\u00020\u00032\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\"\u001a\u00020\u00132\f\u0010B\u001a\b\u0012\u0004\u0012\u00020@0 2\b\u0010C\u001a\u0004\u0018\u00010$H\u0002\u001a\u008c\u0001\u0010D\u001a\b\u0012\u0004\u0012\u00020\u001d0E2\f\u0010F\u001a\b\u0012\u0004\u0012\u00020@0 2\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\u001d0 2\f\u0010H\u001a\b\u0012\u0004\u0012\u00020\u001d0 2\u0006\u0010I\u001a\u00020\u00032\u0006\u0010J\u001a\u00020\u00032\u0006\u0010K\u001a\u00020\u00032\u0006\u0010L\u001a\u00020\u00032\u0006\u0010M\u001a\u00020\u00032\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u001aH\u0002\u001a+\u0010N\u001a\u000208\"\u0004\b\u0000\u0010O*\b\u0012\u0004\u0012\u0002HO0E2\f\u0010P\u001a\b\u0012\u0004\u0012\u0002HO0QH\u0002¢\u0006\u0002\u0010R¨\u0006S"}, d2 = {"measureLazyGrid", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "itemsCount", "", "measuredLineProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLineProvider;", "measuredItemProvider", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItemProvider;", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenLines", "firstVisibleLineIndex", "firstVisibleLineScrollOffset", "scrollToBeConsumed", "", "constraints", "Landroidx/compose/ui/unit/Constraints;", "isVertical", "", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "reverseLayout", "density", "Landroidx/compose/ui/unit/Density;", "itemAnimator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItem;", "slotsPerLine", "pinnedItems", "", "isInLookaheadScope", "isLookingAhead", "approachLayoutInfo", "Landroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "placementScopeInvalidator", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "prefetchInfoRetriever", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "line", "Lkotlin/Pair;", "lineIndexProvider", "itemIndex", "stickyItemsScrollBehavior", "Landroidx/compose/foundation/lazy/layout/StickyItemsPlacement;", "layout", "Lkotlin/Function3;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/layout/MeasureResult;", "measureLazyGrid-t1x4au0", "(ILandroidx/compose/foundation/lazy/grid/LazyGridMeasuredLineProvider;Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredItemProvider;IIIIIIFJZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;ILjava/util/List;ZZLandroidx/compose/foundation/lazy/grid/LazyGridLayoutInfo;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/graphics/GraphicsContext;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;Landroidx/compose/foundation/lazy/layout/StickyItemsPlacement;Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/lazy/grid/LazyGridMeasureResult;", "calculateExtraItems", "filter", "linesRetainedForLookahead", "Landroidx/compose/foundation/lazy/grid/LazyGridMeasuredLine;", "lastVisibleItemIndex", "visibleLines", "lastApproachLayoutInfo", "calculateItemsOffsets", "", "lines", "itemsBefore", "itemsAfter", "layoutWidth", "layoutHeight", "finalMainAxisOffset", "maxOffset", "firstLineScrollOffset", "addAllFromArray", "T", "arr", "", "(Ljava/util/List;[Ljava/lang/Object;)V", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LazyGridMeasureKt {
    public static Unit a(MutableState mutableState, final List list, final List list2, final boolean z, Placeable.PlacementScope placementScope) {
        placementScope.withMotionFrameOfReferencePlacement(new Function1() { // from class: kr8
            public final Object invoke(Object obj) {
                return LazyGridMeasureKt.measureLazyGrid_t1x4au0$lambda$8$0(list, list2, z, (Placeable.PlacementScope) obj);
            }
        });
        ObservableScopeInvalidator.m1154attachToScopeimpl(mutableState);
        return Unit.INSTANCE;
    }

    private static final <T> void addAllFromArray(List<T> list, T[] tArr) {
        for (T t : tArr) {
            list.add(t);
        }
    }

    public static Unit c(Placeable.PlacementScope placementScope) {
        return Unit.INSTANCE;
    }

    private static final List<LazyGridMeasuredItem> calculateExtraItems(List<Integer> list, LazyGridMeasuredItemProvider lazyGridMeasuredItemProvider, LazyGridMeasuredLineProvider lazyGridMeasuredLineProvider, Function1<? super Integer, Boolean> function1) {
        LazyGridMeasuredItemProvider lazyGridMeasuredItemProvider2;
        int size = list.size();
        ArrayList arrayList = null;
        int i = 0;
        while (i < size) {
            int iIntValue = list.get(i).intValue();
            if (((Boolean) function1.invoke(Integer.valueOf(iIntValue))).booleanValue()) {
                int iSpanOf = lazyGridMeasuredLineProvider.spanOf(iIntValue);
                lazyGridMeasuredItemProvider2 = lazyGridMeasuredItemProvider;
                LazyGridMeasuredItem lazyGridMeasuredItemMo1065getAndMeasurehBUhpc = lazyGridMeasuredItemProvider2.mo1065getAndMeasurehBUhpc(iIntValue, 0, iSpanOf, lazyGridMeasuredLineProvider.m1095childConstraintsJhjzzOo$foundation(0, iSpanOf));
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(lazyGridMeasuredItemMo1065getAndMeasurehBUhpc);
            } else {
                lazyGridMeasuredItemProvider2 = lazyGridMeasuredItemProvider;
            }
            i++;
            lazyGridMeasuredItemProvider = lazyGridMeasuredItemProvider2;
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List<LazyGridMeasuredItem> calculateItemsOffsets(List<LazyGridMeasuredLine> list, List<LazyGridMeasuredItem> list2, List<LazyGridMeasuredItem> list3, int i, int i2, int i3, int i4, int i5, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density) {
        int i6 = z ? i2 : i;
        boolean z3 = i3 < Math.min(i6, i4);
        if (z3) {
            if (!(i5 == 0)) {
                InlineClassHelperKt.throwIllegalStateException("non-zero firstLineScrollOffset");
            }
        }
        List<LazyGridMeasuredLine> list4 = list;
        int size = list4.size();
        int length = 0;
        for (int i7 = 0; i7 < size; i7++) {
            length += list.get(i7).getItems().length;
        }
        ArrayList arrayList = new ArrayList(length);
        if (z3) {
            if (!(list2.isEmpty() && list3.isEmpty())) {
                InlineClassHelperKt.throwIllegalArgumentException("no items");
            }
            int size2 = list.size();
            int[] iArr = new int[size2];
            for (int i8 = 0; i8 < size2; i8++) {
                iArr[i8] = list.get(calculateItemsOffsets$reverseAware(i8, z2, size2)).getMainAxisSize();
            }
            int[] iArr2 = new int[size2];
            if (z) {
                if (vertical == null) {
                    InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null verticalArrangement");
                    wq6.a();
                    return null;
                }
                vertical.arrange(density, i6, iArr, iArr2);
            } else {
                if (horizontal == null) {
                    InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null horizontalArrangement");
                    wq6.a();
                    return null;
                }
                horizontal.arrange(density, i6, iArr, LayoutDirection.Ltr, iArr2);
            }
            IntProgression indices = ArraysKt.getIndices(iArr2);
            if (z2) {
                indices = RangesKt.reversed(indices);
            }
            int first = indices.getFirst();
            int last = indices.getLast();
            int step = indices.getStep();
            if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
                while (true) {
                    int mainAxisSize = iArr2[first];
                    LazyGridMeasuredLine lazyGridMeasuredLine = list.get(calculateItemsOffsets$reverseAware(first, z2, size2));
                    if (z2) {
                        mainAxisSize = (i6 - mainAxisSize) - lazyGridMeasuredLine.getMainAxisSize();
                    }
                    addAllFromArray(arrayList, lazyGridMeasuredLine.position(mainAxisSize, i, i2));
                    if (first == last) {
                        break;
                    }
                    first += step;
                }
            }
        } else {
            int size3 = list2.size() - 1;
            if (size3 >= 0) {
                int mainAxisSizeWithSpacings = i5;
                while (true) {
                    int i9 = size3 - 1;
                    LazyGridMeasuredItem lazyGridMeasuredItem = list2.get(size3);
                    mainAxisSizeWithSpacings -= lazyGridMeasuredItem.getMainAxisSizeWithSpacings();
                    lazyGridMeasuredItem.position(mainAxisSizeWithSpacings, 0, i, i2);
                    arrayList.add(lazyGridMeasuredItem);
                    if (i9 < 0) {
                        break;
                    }
                    size3 = i9;
                }
            }
            int size4 = list4.size();
            int mainAxisSizeWithSpacings2 = i5;
            for (int i10 = 0; i10 < size4; i10++) {
                LazyGridMeasuredLine lazyGridMeasuredLine2 = list.get(i10);
                addAllFromArray(arrayList, lazyGridMeasuredLine2.position(mainAxisSizeWithSpacings2, i, i2));
                mainAxisSizeWithSpacings2 += lazyGridMeasuredLine2.getMainAxisSizeWithSpacings();
            }
            int size5 = list3.size();
            for (int i11 = 0; i11 < size5; i11++) {
                LazyGridMeasuredItem lazyGridMeasuredItem2 = list3.get(i11);
                lazyGridMeasuredItem2.position(mainAxisSizeWithSpacings2, 0, i, i2);
                arrayList.add(lazyGridMeasuredItem2);
                mainAxisSizeWithSpacings2 += lazyGridMeasuredItem2.getMainAxisSizeWithSpacings();
            }
        }
        return arrayList;
    }

    private static final int calculateItemsOffsets$reverseAware(int i, boolean z, int i2) {
        return !z ? i : (i2 - i) - 1;
    }

    public static LazyGridMeasuredItem d(LazyGridMeasuredLineProvider lazyGridMeasuredLineProvider, LazyGridMeasuredItemProvider lazyGridMeasuredItemProvider, int i) {
        int iSpanOf = lazyGridMeasuredLineProvider.spanOf(i);
        return lazyGridMeasuredItemProvider.mo1065getAndMeasurehBUhpc(i, 0, iSpanOf, lazyGridMeasuredLineProvider.m1095childConstraintsJhjzzOo$foundation(0, iSpanOf));
    }

    /* JADX WARN: Code duplicated, block: B:36:0x009d  */
    private static final List<LazyGridMeasuredLine> linesRetainedForLookahead(int i, int i2, LazyGridMeasuredLineProvider lazyGridMeasuredLineProvider, boolean z, List<LazyGridMeasuredLine> list, LazyGridLayoutInfo lazyGridLayoutInfo) {
        LazyGridItemInfo lazyGridItemInfo;
        int index;
        int iMin;
        ArrayList arrayList = null;
        if (z && lazyGridLayoutInfo != null && !lazyGridLayoutInfo.getVisibleItemsInfo().isEmpty()) {
            List<LazyGridItemInfo> visibleItemsInfo = lazyGridLayoutInfo.getVisibleItemsInfo();
            int size = visibleItemsInfo.size();
            while (true) {
                size--;
                if (-1 >= size) {
                    lazyGridItemInfo = null;
                    break;
                }
                if (visibleItemsInfo.get(size).getIndex() > i && (size == 0 || visibleItemsInfo.get(size - 1).getIndex() <= i)) {
                    lazyGridItemInfo = visibleItemsInfo.get(size);
                    break;
                }
            }
            LazyGridItemInfo lazyGridItemInfo2 = (LazyGridItemInfo) CollectionsKt.last(lazyGridLayoutInfo.getVisibleItemsInfo());
            LazyGridMeasuredLine lazyGridMeasuredLine = (LazyGridMeasuredLine) CollectionsKt.lastOrNull(list);
            int index2 = lazyGridMeasuredLine != null ? lazyGridMeasuredLine.getIndex() + 1 : 0;
            if (lazyGridItemInfo != null && (index = lazyGridItemInfo.getIndex()) <= (iMin = Math.min(lazyGridItemInfo2.getIndex(), i2 - 1))) {
                while (true) {
                    if (arrayList != null) {
                        int size2 = arrayList.size();
                        int i3 = 0;
                        while (true) {
                            if (i3 < size2) {
                                LazyGridMeasuredItem[] items = arrayList.get(i3).getItems();
                                int length = items.length;
                                int i4 = 0;
                                while (true) {
                                    if (i4 >= length) {
                                        i3++;
                                    } else if (items[i4].getIndex() != index) {
                                        i4++;
                                    }
                                }
                            } else {
                                if (arrayList == null) {
                                    arrayList = new ArrayList();
                                }
                                LazyGridMeasuredLine andMeasure = lazyGridMeasuredLineProvider.getAndMeasure(index2);
                                index2++;
                                arrayList.add(andMeasure);
                            }
                        }
                    } else {
                        if (arrayList == null) {
                            arrayList = new ArrayList();
                        }
                        LazyGridMeasuredLine andMeasure2 = lazyGridMeasuredLineProvider.getAndMeasure(index2);
                        index2++;
                        arrayList.add(andMeasure2);
                    }
                    if (index == iMin) {
                        break;
                    }
                    index++;
                }
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:189:0x0422  */
    /* JADX INFO: renamed from: measureLazyGrid-t1x4au0, reason: not valid java name */
    public static final LazyGridMeasureResult m1091measureLazyGridt1x4au0(int i, final LazyGridMeasuredLineProvider lazyGridMeasuredLineProvider, final LazyGridMeasuredItemProvider lazyGridMeasuredItemProvider, int i2, int i3, int i4, int i5, int i6, int i7, float f, long j, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density, LazyLayoutItemAnimator<LazyGridMeasuredItem> lazyLayoutItemAnimator, int i8, List<Integer> list, boolean z3, final boolean z4, LazyGridLayoutInfo lazyGridLayoutInfo, CoroutineScope coroutineScope, final MutableState<Unit> mutableState, GraphicsContext graphicsContext, Function1<? super Integer, ? extends List<Pair<Integer, Constraints>>> function1, Function1<? super Integer, Integer> function2, StickyItemsPlacement stickyItemsPlacement, Function3<? super Integer, ? super Integer, ? super Function1<? super Placeable.PlacementScope, Unit>, ? extends MeasureResult> function3) {
        int i9;
        ArrayDeque arrayDequePlus;
        int i10;
        LazyGridMeasuredItem[] items;
        LazyGridMeasuredItem lazyGridMeasuredItem;
        LazyGridMeasuredItem[] items2;
        LazyGridMeasuredItem lazyGridMeasuredItem2;
        int i11;
        int i12;
        int i13 = i;
        boolean z5 = true;
        if (!(i3 >= 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("negative beforeContentPadding");
        }
        if (!(i4 >= 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("negative afterContentPadding");
        }
        if (i13 <= 0) {
            int i14 = Constraints.getMinWidth-impl(j);
            int i15 = Constraints.getMinHeight-impl(j);
            lazyLayoutItemAnimator.onMeasured(0, i14, i15, new ArrayList(), lazyGridMeasuredItemProvider.getKeyIndexMap(), lazyGridMeasuredItemProvider, z, z4, i8, z3, 0, 0, coroutineScope, graphicsContext);
            if (!z4) {
                long jM1123getMinSizeToFitDisappearingItemsYbymL2g = lazyLayoutItemAnimator.m1123getMinSizeToFitDisappearingItemsYbymL2g();
                if (!IntSize.equals-impl0(jM1123getMinSizeToFitDisappearingItemsYbymL2g, IntSize.Companion.getZero-YbymL2g())) {
                    i14 = ConstraintsKt.constrainWidth-K40F9xA(j, (int) (jM1123getMinSizeToFitDisappearingItemsYbymL2g >> 32));
                    i15 = ConstraintsKt.constrainHeight-K40F9xA(j, (int) (jM1123getMinSizeToFitDisappearingItemsYbymL2g & 4294967295L));
                }
            }
            return new LazyGridMeasureResult(null, 0, false, 0.0f, (MeasureResult) function3.invoke(Integer.valueOf(i14), Integer.valueOf(i15), new Function1() { // from class: lr8
                public final Object invoke(Object obj) {
                    return LazyGridMeasureKt.c((Placeable.PlacementScope) obj);
                }
            }), 0.0f, false, coroutineScope, density, i8, function1, function2, CollectionsKt.emptyList(), -i3, i2 + i4, 0, z2, z ? Orientation.Vertical : Orientation.Horizontal, i4, i5);
        }
        int iRound = Math.round(f);
        int i16 = i7 - iRound;
        if (i6 == 0 && i16 < 0) {
            iRound += i16;
            i16 = 0;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        int i17 = -i3;
        int i18 = (i5 < 0 ? i5 : 0) + i17;
        int mainAxisSizeWithSpacings = i16 + i18;
        int i19 = i6;
        while (mainAxisSizeWithSpacings < 0 && i19 > 0) {
            i19--;
            LazyGridMeasuredLine andMeasure = lazyGridMeasuredLineProvider.getAndMeasure(i19);
            arrayDeque.add(0, andMeasure);
            mainAxisSizeWithSpacings += andMeasure.getMainAxisSizeWithSpacings();
        }
        if (mainAxisSizeWithSpacings < i18) {
            iRound -= i18 - mainAxisSizeWithSpacings;
            mainAxisSizeWithSpacings = i18;
        }
        int mainAxisSizeWithSpacings2 = mainAxisSizeWithSpacings - i18;
        int i20 = i2 + i4;
        int i21 = i19;
        int iCoerceAtLeast = RangesKt.coerceAtLeast(i20, 0);
        int mainAxisSizeWithSpacings3 = -mainAxisSizeWithSpacings2;
        int i22 = i21;
        int i23 = 0;
        boolean z6 = false;
        while (i23 < arrayDeque.size()) {
            if (mainAxisSizeWithSpacings3 >= iCoerceAtLeast) {
                arrayDeque.remove(i23);
                Unit unit = Unit.INSTANCE;
                z6 = true;
            } else {
                i22++;
                mainAxisSizeWithSpacings3 += ((LazyGridMeasuredLine) arrayDeque.get(i23)).getMainAxisSizeWithSpacings();
                i23++;
            }
        }
        int i24 = i21;
        boolean z7 = z6;
        int i25 = i22;
        while (i25 < i13 && (mainAxisSizeWithSpacings3 < iCoerceAtLeast || mainAxisSizeWithSpacings3 <= 0 || arrayDeque.isEmpty())) {
            LazyGridMeasuredLine andMeasure2 = lazyGridMeasuredLineProvider.getAndMeasure(i25);
            if (andMeasure2.isEmpty()) {
                break;
            }
            mainAxisSizeWithSpacings3 += andMeasure2.getMainAxisSizeWithSpacings();
            if (mainAxisSizeWithSpacings3 <= i18) {
                i11 = iCoerceAtLeast;
                i12 = i18;
                if (((LazyGridMeasuredItem) ArraysKt.last(andMeasure2.getItems())).getIndex() != i - 1) {
                    mainAxisSizeWithSpacings2 -= andMeasure2.getMainAxisSizeWithSpacings();
                    Unit unit2 = Unit.INSTANCE;
                    i24 = i25 + 1;
                    z7 = true;
                }
                i25++;
                i13 = i;
                iCoerceAtLeast = i11;
                i18 = i12;
            } else {
                i11 = iCoerceAtLeast;
                i12 = i18;
            }
            arrayDeque.add(andMeasure2);
            i25++;
            i13 = i;
            iCoerceAtLeast = i11;
            i18 = i12;
        }
        if (mainAxisSizeWithSpacings3 < i2) {
            int i26 = i2 - mainAxisSizeWithSpacings3;
            mainAxisSizeWithSpacings2 -= i26;
            mainAxisSizeWithSpacings3 += i26;
            while (mainAxisSizeWithSpacings2 < i3 && i24 > 0) {
                i24--;
                LazyGridMeasuredLine andMeasure3 = lazyGridMeasuredLineProvider.getAndMeasure(i24);
                arrayDeque.add(0, andMeasure3);
                mainAxisSizeWithSpacings2 += andMeasure3.getMainAxisSizeWithSpacings();
            }
            i9 = i26 + iRound;
            if (mainAxisSizeWithSpacings2 < 0) {
                i9 += mainAxisSizeWithSpacings2;
                mainAxisSizeWithSpacings3 += mainAxisSizeWithSpacings2;
                mainAxisSizeWithSpacings2 = 0;
            }
        } else {
            i9 = iRound;
        }
        float f2 = (MathKt.getSign(Math.round(f)) != MathKt.getSign(i9) || Math.abs(Math.round(f)) < Math.abs(i9)) ? f : i9;
        float f3 = f - f2;
        float f4 = 0.0f;
        if (z4 && i9 > iRound && f3 <= 0.0f) {
            f4 = (i9 - iRound) + f3;
        }
        float f5 = f4;
        if (!(mainAxisSizeWithSpacings2 >= 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("negative initial offset");
        }
        int i27 = -mainAxisSizeWithSpacings2;
        LazyGridMeasuredLine lazyGridMeasuredLine = (LazyGridMeasuredLine) arrayDeque.firstOrNull();
        int index = (lazyGridMeasuredLine == null || (items2 = lazyGridMeasuredLine.getItems()) == null || (lazyGridMeasuredItem2 = (LazyGridMeasuredItem) ArraysKt.firstOrNull(items2)) == null) ? 0 : lazyGridMeasuredItem2.getIndex();
        LazyGridMeasuredLine lazyGridMeasuredLine2 = (LazyGridMeasuredLine) arrayDeque.lastOrNull();
        int index2 = (lazyGridMeasuredLine2 == null || (items = lazyGridMeasuredLine2.getItems()) == null || (lazyGridMeasuredItem = (LazyGridMeasuredItem) ArraysKt.lastOrNull(items)) == null) ? 0 : lazyGridMeasuredItem.getIndex();
        List<Integer> list2 = list;
        int size = list2.size();
        List listEmptyList = null;
        List listEmptyList2 = null;
        int i28 = 0;
        while (i28 < size) {
            int i29 = size;
            int iIntValue = list.get(i28).intValue();
            if (iIntValue >= 0 && iIntValue < index) {
                int iSpanOf = lazyGridMeasuredLineProvider.spanOf(iIntValue);
                LazyGridMeasuredItem lazyGridMeasuredItemMo1065getAndMeasurehBUhpc = lazyGridMeasuredItemProvider.mo1065getAndMeasurehBUhpc(iIntValue, 0, iSpanOf, lazyGridMeasuredLineProvider.m1095childConstraintsJhjzzOo$foundation(0, iSpanOf));
                List arrayList = listEmptyList2 == null ? new ArrayList() : listEmptyList2;
                arrayList.add(lazyGridMeasuredItemMo1065getAndMeasurehBUhpc);
                listEmptyList2 = arrayList;
            }
            i28++;
            size = i29;
            index = index;
        }
        int i30 = index;
        if (listEmptyList2 == null) {
            listEmptyList2 = CollectionsKt.emptyList();
        }
        int i31 = index2;
        List<LazyGridMeasuredLine> listLinesRetainedForLookahead = linesRetainedForLookahead(i31, i, lazyGridMeasuredLineProvider, z4, arrayDeque, lazyGridLayoutInfo);
        int i32 = i;
        LazyGridMeasuredLineProvider lazyGridMeasuredLineProvider2 = lazyGridMeasuredLineProvider;
        int size2 = list2.size();
        int i33 = 0;
        while (i33 < size2) {
            int i34 = size2;
            int iIntValue2 = list.get(i33).intValue();
            int i35 = i33;
            if (i31 + 1 <= iIntValue2 && iIntValue2 < i32) {
                if (z4) {
                    int size3 = listLinesRetainedForLookahead.size();
                    int i36 = 0;
                    while (true) {
                        if (i36 < size3) {
                            int i37 = i36;
                            LazyGridMeasuredItem[] items3 = listLinesRetainedForLookahead.get(i36).getItems();
                            int i38 = size3;
                            int length = items3.length;
                            int i39 = 0;
                            while (true) {
                                if (i39 < length) {
                                    int i40 = i39;
                                    if (items3[i39].getIndex() != iIntValue2) {
                                        i39 = i40 + 1;
                                    }
                                } else {
                                    i36 = i37 + 1;
                                    size3 = i38;
                                }
                            }
                        }
                    }
                }
                int iSpanOf2 = lazyGridMeasuredLineProvider2.spanOf(iIntValue2);
                LazyGridMeasuredItem lazyGridMeasuredItemMo1065getAndMeasurehBUhpc2 = lazyGridMeasuredItemProvider.mo1065getAndMeasurehBUhpc(iIntValue2, 0, iSpanOf2, lazyGridMeasuredLineProvider2.m1095childConstraintsJhjzzOo$foundation(0, iSpanOf2));
                if (listEmptyList == null) {
                    listEmptyList = new ArrayList();
                }
                List list3 = listEmptyList;
                list3.add(lazyGridMeasuredItemMo1065getAndMeasurehBUhpc2);
                listEmptyList = list3;
            }
            i33 = i35 + 1;
            i32 = i;
            lazyGridMeasuredLineProvider2 = lazyGridMeasuredLineProvider;
            size2 = i34;
            arrayDeque = arrayDeque;
            listLinesRetainedForLookahead = listLinesRetainedForLookahead;
        }
        ArrayDeque arrayDeque2 = arrayDeque;
        List<LazyGridMeasuredLine> list4 = listLinesRetainedForLookahead;
        if (listEmptyList == null) {
            listEmptyList = CollectionsKt.emptyList();
        }
        List list5 = listEmptyList;
        if (i3 > 0 || i5 < 0) {
            int size4 = arrayDeque2.size();
            int i41 = 0;
            while (true) {
                arrayDequePlus = arrayDeque2;
                if (i41 >= size4) {
                    break;
                }
                int mainAxisSizeWithSpacings4 = ((LazyGridMeasuredLine) arrayDequePlus.get(i41)).getMainAxisSizeWithSpacings();
                if (mainAxisSizeWithSpacings2 == 0 || mainAxisSizeWithSpacings4 > mainAxisSizeWithSpacings2 || i41 == CollectionsKt.getLastIndex(arrayDequePlus)) {
                    break;
                }
                mainAxisSizeWithSpacings2 -= mainAxisSizeWithSpacings4;
                i41++;
                lazyGridMeasuredLine = (LazyGridMeasuredLine) arrayDequePlus.get(i41);
                arrayDeque2 = arrayDequePlus;
            }
        } else {
            arrayDequePlus = arrayDeque2;
        }
        int i42 = mainAxisSizeWithSpacings2;
        LazyGridMeasuredLine lazyGridMeasuredLine3 = lazyGridMeasuredLine;
        int i43 = z ? Constraints.getMaxWidth-impl(j) : ConstraintsKt.constrainWidth-K40F9xA(j, mainAxisSizeWithSpacings3);
        int i44 = z ? ConstraintsKt.constrainHeight-K40F9xA(j, mainAxisSizeWithSpacings3) : Constraints.getMaxHeight-impl(j);
        if (!list4.isEmpty()) {
            arrayDequePlus = CollectionsKt.plus(arrayDequePlus, list4);
        }
        ArrayDeque arrayDeque3 = arrayDequePlus;
        int i45 = i44;
        float f6 = f2;
        int i46 = mainAxisSizeWithSpacings3;
        final List<LazyGridMeasuredItem> listCalculateItemsOffsets = calculateItemsOffsets(arrayDeque3, listEmptyList2, list5, i43, i45, i46, i2, i27, z, vertical, horizontal, z2, density);
        lazyLayoutItemAnimator.onMeasured((int) f6, i43, i45, listCalculateItemsOffsets, lazyGridMeasuredItemProvider.getKeyIndexMap(), lazyGridMeasuredItemProvider, z, z4, i8, z3, i42, i46, coroutineScope, graphicsContext);
        if (z4) {
            i10 = i45;
        } else {
            long jM1123getMinSizeToFitDisappearingItemsYbymL2g2 = lazyLayoutItemAnimator.m1123getMinSizeToFitDisappearingItemsYbymL2g();
            if (IntSize.equals-impl0(jM1123getMinSizeToFitDisappearingItemsYbymL2g2, IntSize.Companion.getZero-YbymL2g())) {
                i10 = i45;
            } else {
                int i47 = z ? i45 : i43;
                i43 = ConstraintsKt.constrainWidth-K40F9xA(j, Math.max(i43, (int) (jM1123getMinSizeToFitDisappearingItemsYbymL2g2 >> 32)));
                int i48 = ConstraintsKt.constrainHeight-K40F9xA(j, Math.max(i45, (int) (jM1123getMinSizeToFitDisappearingItemsYbymL2g2 & 4294967295L)));
                int i49 = z ? i48 : i43;
                if (i49 != i47) {
                    int size5 = listCalculateItemsOffsets.size();
                    for (int i50 = 0; i50 < size5; i50++) {
                        listCalculateItemsOffsets.get(i50).updateMainAxisLayoutSize(i49);
                    }
                }
                i10 = i48;
            }
        }
        int i51 = i43;
        final List listApplyStickyItems = LazyLayoutStickyItemsKt.applyStickyItems(stickyItemsPlacement, i30, i31, listCalculateItemsOffsets, lazyGridMeasuredItemProvider.getHeaderIndices(), i3, i4, i51, i10, new Function1() { // from class: mr8
            public final Object invoke(Object obj) {
                return LazyGridMeasureKt.d(lazyGridMeasuredLineProvider, lazyGridMeasuredItemProvider, ((Integer) obj).intValue());
            }
        });
        if (i31 == i - 1 && i46 <= i2) {
            z5 = false;
        }
        return new LazyGridMeasureResult(lazyGridMeasuredLine3, i42, z5, f6, (MeasureResult) function3.invoke(Integer.valueOf(i51), Integer.valueOf(i10), new Function1() { // from class: nr8
            public final Object invoke(Object obj) {
                return LazyGridMeasureKt.a(mutableState, listCalculateItemsOffsets, listApplyStickyItems, z4, (Placeable.PlacementScope) obj);
            }
        }), f5, z7, coroutineScope, density, i8, function1, function2, LazyLayoutMeasuredItemKt.updatedVisibleItems(i30, i31, listCalculateItemsOffsets, listApplyStickyItems), i17, i20, i, z2, z ? Orientation.Vertical : Orientation.Horizontal, i4, i5);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measureLazyGrid_t1x4au0$lambda$8$0(List list, List list2, boolean z, Placeable.PlacementScope placementScope) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ((LazyGridMeasuredItem) list.get(i)).place(placementScope, z);
        }
        int size2 = list2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            ((LazyGridMeasuredItem) list2.get(i2)).place(placementScope, z);
        }
        return Unit.INSTANCE;
    }
}
