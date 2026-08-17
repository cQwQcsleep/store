package androidx.compose.foundation.lazy;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.lazy.LazyListMeasureKt;
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
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010!\n\u0002\b\f\u001a\u0092\u0002\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00032\u0006\u0010\u0007\u001a\u00020\u00032\u0006\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u00032\u0006\u0010\u000b\u001a\u00020\u00032\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001a2\u0006\u0010\u001c\u001a\u00020\u00032\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u001e2\u0006\u0010\u001f\u001a\u00020\u00112\u0006\u0010 \u001a\u00020\u00112\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2\u0006\u0010%\u001a\u00020&2\b\u0010'\u001a\u0004\u0018\u00010(2/\u0010)\u001a+\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020,\u0012\u0004\u0012\u00020-0+¢\u0006\u0002\b.\u0012\u0004\u0012\u00020/0*H\u0000¢\u0006\u0004\b0\u00101\u001aB\u00102\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001e2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u001b042\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u001c\u001a\u00020\u00032\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u001eH\u0002\u001a4\u00105\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001e2\u0006\u00106\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\u00032\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00030\u001eH\u0002\u001a\u008c\u0001\u00107\u001a\b\u0012\u0004\u0012\u00020\u001b042\f\u00108\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001e2\f\u00109\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001e2\f\u0010:\u001a\b\u0012\u0004\u0012\u00020\u001b0\u001e2\u0006\u0010;\u001a\u00020\u00032\u0006\u0010<\u001a\u00020\u00032\u0006\u0010=\u001a\u00020\u00032\u0006\u0010>\u001a\u00020\u00032\u0006\u0010?\u001a\u00020\u00032\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0017\u001a\u00020\u0018H\u0002¨\u0006@"}, d2 = {"measureLazyList", "Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "itemsCount", "", "measuredItemProvider", "Landroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenItems", "firstVisibleItemIndex", "firstVisibleItemScrollOffset", "scrollToBeConsumed", "", "constraints", "Landroidx/compose/ui/unit/Constraints;", "isVertical", "", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "reverseLayout", "density", "Landroidx/compose/ui/unit/Density;", "itemAnimator", "Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;", "Landroidx/compose/foundation/lazy/LazyListMeasuredItem;", "beyondBoundsItemCount", "pinnedItems", "", "hasLookaheadOccurred", "isLookingAhead", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "placementScopeInvalidator", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "graphicsContext", "Landroidx/compose/ui/graphics/GraphicsContext;", "stickyItemsPlacement", "Landroidx/compose/foundation/lazy/layout/StickyItemsPlacement;", "layout", "Lkotlin/Function3;", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/layout/MeasureResult;", "measureLazyList-_s_dbAc", "(ILandroidx/compose/foundation/lazy/LazyListMeasuredItemProvider;IIIIIIFJZLandroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/foundation/layout/Arrangement$Horizontal;ZLandroidx/compose/ui/unit/Density;Landroidx/compose/foundation/lazy/layout/LazyLayoutItemAnimator;ILjava/util/List;ZZLkotlinx/coroutines/CoroutineScope;Landroidx/compose/runtime/MutableState;Landroidx/compose/ui/graphics/GraphicsContext;Landroidx/compose/foundation/lazy/layout/StickyItemsPlacement;Lkotlin/jvm/functions/Function3;)Landroidx/compose/foundation/lazy/LazyListMeasureResult;", "createItemsAfterList", "visibleItems", "", "createItemsBeforeList", "currentFirstItemIndex", "calculateItemsOffsets", "items", "extraItemsBefore", "extraItemsAfter", "layoutWidth", "layoutHeight", "finalMainAxisOffset", "maxOffset", "itemsScrollOffset", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class LazyListMeasureKt {
    public static Unit a(Placeable.PlacementScope placementScope) {
        return Unit.INSTANCE;
    }

    public static LazyListMeasuredItem b(LazyListMeasuredItemProvider lazyListMeasuredItemProvider, int i) {
        return LazyListMeasuredItemProvider.m1064getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, i, 0L, 2, null);
    }

    public static Unit c(MutableState mutableState, final List list, final List list2, final boolean z, Placeable.PlacementScope placementScope) {
        placementScope.withMotionFrameOfReferencePlacement(new Function1() { // from class: ev8
            public final Object invoke(Object obj) {
                return LazyListMeasureKt.measureLazyList__s_dbAc$lambda$8$0(list, list2, z, (Placeable.PlacementScope) obj);
            }
        });
        ObservableScopeInvalidator.m1154attachToScopeimpl(mutableState);
        return Unit.INSTANCE;
    }

    private static final List<LazyListMeasuredItem> calculateItemsOffsets(List<LazyListMeasuredItem> list, List<LazyListMeasuredItem> list2, List<LazyListMeasuredItem> list3, int i, int i2, int i3, int i4, int i5, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density) {
        int i6 = z ? i2 : i;
        int i7 = 0;
        boolean z3 = i3 < Math.min(i6, i4);
        if (z3) {
            if (!(i5 == 0)) {
                InlineClassHelperKt.throwIllegalStateException("non-zero itemsScrollOffset");
            }
        }
        ArrayList arrayList = new ArrayList(list.size() + list2.size() + list3.size());
        if (z3) {
            if (!(list2.isEmpty() && list3.isEmpty())) {
                InlineClassHelperKt.throwIllegalArgumentException("no extra items");
            }
            int size = list.size();
            int[] iArr = new int[size];
            while (i7 < size) {
                iArr[i7] = list.get(calculateItemsOffsets$reverseAware(i7, z2, size)).getSize();
                i7++;
            }
            int[] iArr2 = new int[size];
            if (z) {
                if (vertical == null) {
                    InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null verticalArrangement when isVertical == true");
                    wq6.a();
                    return null;
                }
                vertical.arrange(density, i6, iArr, iArr2);
            } else {
                if (horizontal == null) {
                    InlineClassHelperKt.throwIllegalArgumentExceptionForNullCheck("null horizontalArrangement when isVertical == false");
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
                    int size2 = iArr2[first];
                    LazyListMeasuredItem lazyListMeasuredItem = list.get(calculateItemsOffsets$reverseAware(first, z2, size));
                    if (z2) {
                        size2 = (i6 - size2) - lazyListMeasuredItem.getSize();
                    }
                    lazyListMeasuredItem.position(size2, i, i2);
                    arrayList.add(lazyListMeasuredItem);
                    if (first == last) {
                        break;
                    }
                    first += step;
                }
            }
        } else {
            int size3 = list2.size();
            int mainAxisSizeWithSpacings = i5;
            for (int i8 = 0; i8 < size3; i8++) {
                LazyListMeasuredItem lazyListMeasuredItem2 = list2.get(i8);
                mainAxisSizeWithSpacings -= lazyListMeasuredItem2.getMainAxisSizeWithSpacings();
                lazyListMeasuredItem2.position(mainAxisSizeWithSpacings, i, i2);
                arrayList.add(lazyListMeasuredItem2);
            }
            int size4 = list.size();
            int mainAxisSizeWithSpacings2 = i5;
            for (int i9 = 0; i9 < size4; i9++) {
                LazyListMeasuredItem lazyListMeasuredItem3 = list.get(i9);
                lazyListMeasuredItem3.position(mainAxisSizeWithSpacings2, i, i2);
                arrayList.add(lazyListMeasuredItem3);
                mainAxisSizeWithSpacings2 += lazyListMeasuredItem3.getMainAxisSizeWithSpacings();
            }
            int size5 = list3.size();
            while (i7 < size5) {
                LazyListMeasuredItem lazyListMeasuredItem4 = list3.get(i7);
                lazyListMeasuredItem4.position(mainAxisSizeWithSpacings2, i, i2);
                arrayList.add(lazyListMeasuredItem4);
                mainAxisSizeWithSpacings2 += lazyListMeasuredItem4.getMainAxisSizeWithSpacings();
                i7++;
            }
        }
        return arrayList;
    }

    private static final int calculateItemsOffsets$reverseAware(int i, boolean z, int i2) {
        return !z ? i : (i2 - i) - 1;
    }

    private static final List<LazyListMeasuredItem> createItemsAfterList(List<LazyListMeasuredItem> list, LazyListMeasuredItemProvider lazyListMeasuredItemProvider, int i, int i2, List<Integer> list2) {
        LazyListMeasuredItemProvider lazyListMeasuredItemProvider2;
        LazyListMeasuredItemProvider lazyListMeasuredItemProvider3;
        int iMin = Math.min(((LazyListMeasuredItem) CollectionsKt.last(list)).getIndex() + i2, i - 1);
        int index = ((LazyListMeasuredItem) CollectionsKt.last(list)).getIndex() + 1;
        ArrayList arrayList = null;
        if (index <= iMin) {
            int i3 = index;
            while (true) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                lazyListMeasuredItemProvider2 = lazyListMeasuredItemProvider;
                arrayList.add(LazyListMeasuredItemProvider.m1064getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider2, i3, 0L, 2, null));
                if (i3 == iMin) {
                    break;
                }
                i3++;
                lazyListMeasuredItemProvider = lazyListMeasuredItemProvider2;
            }
        } else {
            lazyListMeasuredItemProvider2 = lazyListMeasuredItemProvider;
        }
        if (arrayList != null && ((LazyListMeasuredItem) CollectionsKt.last(arrayList)).getIndex() > iMin) {
            iMin = ((LazyListMeasuredItem) CollectionsKt.last(arrayList)).getIndex();
        }
        int size = list2.size();
        int i4 = 0;
        while (i4 < size) {
            int iIntValue = list2.get(i4).intValue();
            if (iIntValue > iMin) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                lazyListMeasuredItemProvider3 = lazyListMeasuredItemProvider2;
                arrayList.add(LazyListMeasuredItemProvider.m1064getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider3, iIntValue, 0L, 2, null));
            } else {
                lazyListMeasuredItemProvider3 = lazyListMeasuredItemProvider2;
            }
            i4++;
            lazyListMeasuredItemProvider2 = lazyListMeasuredItemProvider3;
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List<LazyListMeasuredItem> createItemsBeforeList(int i, LazyListMeasuredItemProvider lazyListMeasuredItemProvider, int i2, List<Integer> list) {
        LazyListMeasuredItemProvider lazyListMeasuredItemProvider2;
        int iMax = Math.max(0, i - i2);
        int i3 = i - 1;
        ArrayList arrayList = null;
        if (iMax <= i3) {
            int i4 = i3;
            while (true) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                lazyListMeasuredItemProvider2 = lazyListMeasuredItemProvider;
                arrayList.add(LazyListMeasuredItemProvider.m1064getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider2, i4, 0L, 2, null));
                if (i4 == iMax) {
                    break;
                }
                i4--;
                lazyListMeasuredItemProvider = lazyListMeasuredItemProvider2;
            }
        } else {
            lazyListMeasuredItemProvider2 = lazyListMeasuredItemProvider;
        }
        int size = list.size() - 1;
        if (size >= 0) {
            while (true) {
                int i5 = size - 1;
                int iIntValue = list.get(size).intValue();
                if (iIntValue < iMax) {
                    if (arrayList == null) {
                        arrayList = new ArrayList();
                    }
                    arrayList.add(LazyListMeasuredItemProvider.m1064getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider2, iIntValue, 0L, 2, null));
                }
                if (i5 < 0) {
                    break;
                }
                size = i5;
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    /* JADX WARN: Code duplicated, block: B:150:0x03bb  */
    /* JADX WARN: Code duplicated, block: B:165:0x0407  */
    /* JADX INFO: renamed from: measureLazyList-_s_dbAc, reason: not valid java name */
    public static final LazyListMeasureResult m1058measureLazyList_s_dbAc(int i, final LazyListMeasuredItemProvider lazyListMeasuredItemProvider, int i2, int i3, int i4, int i5, int i6, int i7, float f, long j, boolean z, Arrangement.Vertical vertical, Arrangement.Horizontal horizontal, boolean z2, Density density, LazyLayoutItemAnimator<LazyListMeasuredItem> lazyLayoutItemAnimator, int i8, List<Integer> list, boolean z3, final boolean z4, CoroutineScope coroutineScope, final MutableState<Unit> mutableState, GraphicsContext graphicsContext, StickyItemsPlacement stickyItemsPlacement, Function3<? super Integer, ? super Integer, ? super Function1<? super Placeable.PlacementScope, Unit>, ? extends MeasureResult> function3) {
        int i9;
        int i10;
        int i11;
        int i12;
        int i13;
        LazyListMeasuredItemProvider lazyListMeasuredItemProvider2;
        int i14;
        int iMax;
        int i15;
        int i16;
        int i17;
        int i18;
        int i19;
        int i20;
        Integer numValueOf;
        if (!(i3 >= 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("invalid beforeContentPadding");
        }
        if (!(i4 >= 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("invalid afterContentPadding");
        }
        if (i <= 0) {
            int i21 = Constraints.getMinWidth-impl(j);
            int i22 = Constraints.getMinHeight-impl(j);
            lazyLayoutItemAnimator.onMeasured(0, i21, i22, new ArrayList(), lazyListMeasuredItemProvider.getKeyIndexMap(), lazyListMeasuredItemProvider, z, z4, 1, z3, 0, 0, coroutineScope, graphicsContext);
            if (!z4) {
                long jM1123getMinSizeToFitDisappearingItemsYbymL2g = lazyLayoutItemAnimator.m1123getMinSizeToFitDisappearingItemsYbymL2g();
                if (!IntSize.equals-impl0(jM1123getMinSizeToFitDisappearingItemsYbymL2g, IntSize.Companion.getZero-YbymL2g())) {
                    i21 = ConstraintsKt.constrainWidth-K40F9xA(j, (int) (jM1123getMinSizeToFitDisappearingItemsYbymL2g >> 32));
                    i22 = ConstraintsKt.constrainHeight-K40F9xA(j, (int) (jM1123getMinSizeToFitDisappearingItemsYbymL2g & 4294967295L));
                }
            }
            return new LazyListMeasureResult(null, 0, false, 0.0f, (MeasureResult) function3.invoke(Integer.valueOf(i21), Integer.valueOf(i22), new Function1() { // from class: fv8
                public final Object invoke(Object obj) {
                    return LazyListMeasureKt.a((Placeable.PlacementScope) obj);
                }
            }), 0.0f, false, coroutineScope, density, lazyListMeasuredItemProvider.getChildConstraints(), CollectionsKt.emptyList(), -i3, i2 + i4, 0, z2, z ? Orientation.Vertical : Orientation.Horizontal, i4, i5, null);
        }
        int i23 = i6;
        if (i23 >= i) {
            i23 = i - 1;
            i9 = 0;
        } else {
            i9 = i7;
        }
        int iRound = Math.round(f);
        int i24 = i9 - iRound;
        if (i23 == 0 && i24 < 0) {
            iRound += i24;
            i24 = 0;
        }
        int i25 = iRound;
        ArrayDeque arrayDeque = new ArrayDeque();
        int i26 = -i3;
        int i27 = (i5 < 0 ? i5 : 0) + i26;
        int mainAxisSizeWithSpacings = i24 + i27;
        int iMax2 = 0;
        while (mainAxisSizeWithSpacings < 0 && i23 > 0) {
            int i28 = i23 - 1;
            ArrayDeque arrayDeque2 = arrayDeque;
            LazyListMeasuredItem lazyListMeasuredItemM1064getAndMeasure0kLqBqw$default = LazyListMeasuredItemProvider.m1064getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, i28, 0L, 2, null);
            arrayDeque2.add(0, lazyListMeasuredItemM1064getAndMeasure0kLqBqw$default);
            iMax2 = Math.max(iMax2, lazyListMeasuredItemM1064getAndMeasure0kLqBqw$default.getCrossAxisSize());
            mainAxisSizeWithSpacings = lazyListMeasuredItemM1064getAndMeasure0kLqBqw$default.getMainAxisSizeWithSpacings() + mainAxisSizeWithSpacings;
            i23 = i28;
            arrayDeque = arrayDeque2;
            i27 = i27;
            i26 = i26;
            i25 = i25;
        }
        int i29 = mainAxisSizeWithSpacings;
        ArrayDeque arrayDeque3 = arrayDeque;
        int i30 = i26;
        int i31 = iMax2;
        int i32 = i25;
        int i33 = i27;
        if (i29 < i33) {
            i11 = i32 - (i33 - i29);
            i10 = i33;
        } else {
            i10 = i29;
            i11 = i32;
        }
        int i34 = i10 - i33;
        int i35 = i2 + i4;
        int iCoerceAtLeast = RangesKt.coerceAtLeast(i35, 0);
        int mainAxisSizeWithSpacings2 = -i34;
        int i36 = i23;
        int i37 = 0;
        boolean z5 = false;
        while (i37 < arrayDeque3.size()) {
            if (mainAxisSizeWithSpacings2 >= iCoerceAtLeast) {
                arrayDeque3.remove(i37);
                Unit unit = Unit.INSTANCE;
                z5 = true;
            } else {
                i36++;
                mainAxisSizeWithSpacings2 += ((LazyListMeasuredItem) arrayDeque3.get(i37)).getMainAxisSizeWithSpacings();
                i37++;
            }
        }
        int i38 = i23;
        int i39 = i35;
        int i40 = i31;
        int i41 = i36;
        int mainAxisSizeWithSpacings3 = mainAxisSizeWithSpacings2;
        int mainAxisSizeWithSpacings4 = i34;
        while (i41 < i && (mainAxisSizeWithSpacings3 < iCoerceAtLeast || mainAxisSizeWithSpacings3 <= 0 || arrayDeque3.isEmpty())) {
            int i42 = i40;
            int i43 = iCoerceAtLeast;
            int i44 = i38;
            int i45 = i39;
            LazyListMeasuredItem lazyListMeasuredItemM1064getAndMeasure0kLqBqw$default2 = LazyListMeasuredItemProvider.m1064getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, i41, 0L, 2, null);
            int i46 = i41;
            mainAxisSizeWithSpacings3 += lazyListMeasuredItemM1064getAndMeasure0kLqBqw$default2.getMainAxisSizeWithSpacings();
            if (mainAxisSizeWithSpacings3 > i33 || i46 == i - 1) {
                int iMax3 = Math.max(i42, lazyListMeasuredItemM1064getAndMeasure0kLqBqw$default2.getCrossAxisSize());
                arrayDeque3.add(lazyListMeasuredItemM1064getAndMeasure0kLqBqw$default2);
                i38 = i44;
                i40 = iMax3;
            } else {
                mainAxisSizeWithSpacings4 -= lazyListMeasuredItemM1064getAndMeasure0kLqBqw$default2.getMainAxisSizeWithSpacings();
                Unit unit2 = Unit.INSTANCE;
                i40 = i42;
                z5 = true;
                i38 = i46 + 1;
            }
            i41 = i46 + 1;
            i39 = i45;
            iCoerceAtLeast = i43;
        }
        int i47 = i39;
        int i48 = i41;
        int i49 = i38;
        int iMax4 = i40;
        if (mainAxisSizeWithSpacings3 < i2) {
            int i50 = i2 - mainAxisSizeWithSpacings3;
            int i51 = mainAxisSizeWithSpacings3 + i50;
            int i52 = i49;
            int mainAxisSizeWithSpacings5 = mainAxisSizeWithSpacings4 - i50;
            while (mainAxisSizeWithSpacings5 < i3 && i52 > 0) {
                i52--;
                int i53 = mainAxisSizeWithSpacings5;
                LazyListMeasuredItem lazyListMeasuredItemM1064getAndMeasure0kLqBqw$default3 = LazyListMeasuredItemProvider.m1064getAndMeasure0kLqBqw$default(lazyListMeasuredItemProvider, i52, 0L, 2, null);
                arrayDeque3.add(0, lazyListMeasuredItemM1064getAndMeasure0kLqBqw$default3);
                iMax4 = Math.max(iMax4, lazyListMeasuredItemM1064getAndMeasure0kLqBqw$default3.getCrossAxisSize());
                mainAxisSizeWithSpacings5 = i53 + lazyListMeasuredItemM1064getAndMeasure0kLqBqw$default3.getMainAxisSizeWithSpacings();
                i48 = i48;
                i51 = i51;
            }
            int i54 = mainAxisSizeWithSpacings5;
            int i55 = i51;
            i13 = i48;
            lazyListMeasuredItemProvider2 = lazyListMeasuredItemProvider;
            i14 = i50 + i11;
            if (i54 < 0) {
                i14 += i54;
                iMax = iMax4;
                i16 = i52;
                i12 = i55 + i54;
                i15 = 0;
            } else {
                iMax = iMax4;
                i15 = i54;
                i16 = i52;
                i12 = i55;
            }
        } else {
            i12 = mainAxisSizeWithSpacings3;
            i13 = i48;
            lazyListMeasuredItemProvider2 = lazyListMeasuredItemProvider;
            i14 = i11;
            iMax = iMax4;
            i15 = mainAxisSizeWithSpacings4;
            i16 = i49;
        }
        float f2 = (MathKt.getSign(Math.round(f)) != MathKt.getSign(i14) || Math.abs(Math.round(f)) < Math.abs(i14)) ? f : i14;
        float f3 = f - f2;
        float f4 = 0.0f;
        if (z4 && i14 > i11 && f3 <= 0.0f) {
            f4 = (i14 - i11) + f3;
        }
        if (!(i15 >= 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("negative currentFirstItemScrollOffset");
        }
        int i56 = -i15;
        LazyListMeasuredItem lazyListMeasuredItem = (LazyListMeasuredItem) arrayDeque3.first();
        if (i3 > 0 || i5 < 0) {
            int size = arrayDeque3.size();
            int i57 = 0;
            while (true) {
                if (i57 >= size) {
                    i17 = i15;
                    i18 = i56;
                    break;
                }
                i18 = i56;
                int mainAxisSizeWithSpacings6 = ((LazyListMeasuredItem) arrayDeque3.get(i57)).getMainAxisSizeWithSpacings();
                if (i15 == 0 || mainAxisSizeWithSpacings6 > i15) {
                    i17 = i15;
                    break;
                }
                i17 = i15;
                if (i57 == CollectionsKt.getLastIndex(arrayDeque3)) {
                    break;
                }
                i15 = i17 - mainAxisSizeWithSpacings6;
                i57++;
                lazyListMeasuredItem = (LazyListMeasuredItem) arrayDeque3.get(i57);
                i56 = i18;
            }
            i19 = i17;
        } else {
            i19 = i15;
            i18 = i56;
        }
        LazyListMeasuredItem lazyListMeasuredItem2 = lazyListMeasuredItem;
        List<LazyListMeasuredItem> listCreateItemsBeforeList = createItemsBeforeList(i16, lazyListMeasuredItemProvider2, i8, list);
        int size2 = listCreateItemsBeforeList.size();
        for (int i58 = 0; i58 < size2; i58++) {
            iMax = Math.max(iMax, listCreateItemsBeforeList.get(i58).getCrossAxisSize());
        }
        List<LazyListMeasuredItem> listCreateItemsAfterList = createItemsAfterList(arrayDeque3, lazyListMeasuredItemProvider2, i, i8, list);
        int size3 = listCreateItemsAfterList.size();
        for (int i59 = 0; i59 < size3; i59++) {
            iMax = Math.max(iMax, listCreateItemsAfterList.get(i59).getCrossAxisSize());
        }
        boolean z6 = Intrinsics.areEqual(lazyListMeasuredItem2, arrayDeque3.first()) && listCreateItemsBeforeList.isEmpty() && listCreateItemsAfterList.isEmpty();
        int i60 = ConstraintsKt.constrainWidth-K40F9xA(j, z ? iMax : i12);
        if (z) {
            iMax = i12;
        }
        int i61 = ConstraintsKt.constrainHeight-K40F9xA(j, iMax);
        float f5 = f2;
        int i62 = i12;
        final List<LazyListMeasuredItem> listCalculateItemsOffsets = calculateItemsOffsets(arrayDeque3, listCreateItemsBeforeList, listCreateItemsAfterList, i60, i61, i62, i2, i18, z, vertical, horizontal, z2, density);
        int i63 = i60;
        int i64 = i19;
        lazyLayoutItemAnimator.onMeasured((int) f5, i63, i61, listCalculateItemsOffsets, lazyListMeasuredItemProvider.getKeyIndexMap(), lazyListMeasuredItemProvider, z, z4, 1, z3, i64, i62, coroutineScope, graphicsContext);
        if (z4) {
            i20 = i61;
        } else {
            long jM1123getMinSizeToFitDisappearingItemsYbymL2g2 = lazyLayoutItemAnimator.m1123getMinSizeToFitDisappearingItemsYbymL2g();
            if (IntSize.equals-impl0(jM1123getMinSizeToFitDisappearingItemsYbymL2g2, IntSize.Companion.getZero-YbymL2g())) {
                i20 = i61;
            } else {
                int i65 = z ? i61 : i63;
                i63 = ConstraintsKt.constrainWidth-K40F9xA(j, Math.max(i63, (int) (jM1123getMinSizeToFitDisappearingItemsYbymL2g2 >> 32)));
                int i66 = ConstraintsKt.constrainHeight-K40F9xA(j, Math.max(i61, (int) (jM1123getMinSizeToFitDisappearingItemsYbymL2g2 & 4294967295L)));
                int i67 = z ? i66 : i63;
                if (i67 != i65) {
                    int size4 = listCalculateItemsOffsets.size();
                    for (int i68 = 0; i68 < size4; i68++) {
                        listCalculateItemsOffsets.get(i68).updateMainAxisLayoutSize(i67);
                    }
                }
                i20 = i66;
            }
        }
        int i69 = i63;
        LazyListMeasuredItem lazyListMeasuredItem3 = (LazyListMeasuredItem) arrayDeque3.firstOrNull();
        int index = lazyListMeasuredItem3 != null ? lazyListMeasuredItem3.getIndex() : 0;
        LazyListMeasuredItem lazyListMeasuredItem4 = (LazyListMeasuredItem) arrayDeque3.lastOrNull();
        final List listApplyStickyItems = LazyLayoutStickyItemsKt.applyStickyItems(stickyItemsPlacement, index, lazyListMeasuredItem4 != null ? lazyListMeasuredItem4.getIndex() : 0, listCalculateItemsOffsets, lazyListMeasuredItemProvider.getHeaderIndexes(), i3, i4, i69, i20, new Function1() { // from class: gv8
            public final Object invoke(Object obj) {
                return LazyListMeasureKt.b(lazyListMeasuredItemProvider, ((Integer) obj).intValue());
            }
        });
        Integer numValueOf2 = null;
        if (z6) {
            LazyListMeasuredItem lazyListMeasuredItem5 = (LazyListMeasuredItem) CollectionsKt.firstOrNull(listCalculateItemsOffsets);
            if (lazyListMeasuredItem5 != null) {
                numValueOf = Integer.valueOf(lazyListMeasuredItem5.getIndex());
            } else {
                numValueOf = null;
            }
        } else {
            LazyListMeasuredItem lazyListMeasuredItem6 = (LazyListMeasuredItem) arrayDeque3.firstOrNull();
            if (lazyListMeasuredItem6 != null) {
                numValueOf = Integer.valueOf(lazyListMeasuredItem6.getIndex());
            } else {
                numValueOf = null;
            }
        }
        if (z6) {
            LazyListMeasuredItem lazyListMeasuredItem7 = (LazyListMeasuredItem) CollectionsKt.lastOrNull(listCalculateItemsOffsets);
            if (lazyListMeasuredItem7 != null) {
                numValueOf2 = Integer.valueOf(lazyListMeasuredItem7.getIndex());
            }
        } else {
            LazyListMeasuredItem lazyListMeasuredItem8 = (LazyListMeasuredItem) arrayDeque3.lastOrNull();
            if (lazyListMeasuredItem8 != null) {
                numValueOf2 = Integer.valueOf(lazyListMeasuredItem8.getIndex());
            }
        }
        return new LazyListMeasureResult(lazyListMeasuredItem2, i64, i13 < i || i62 > i2, f5, (MeasureResult) function3.invoke(Integer.valueOf(i69), Integer.valueOf(i20), new Function1() { // from class: hv8
            public final Object invoke(Object obj) {
                return LazyListMeasureKt.c(mutableState, listCalculateItemsOffsets, listApplyStickyItems, z4, (Placeable.PlacementScope) obj);
            }
        }), f4, z5, coroutineScope, density, lazyListMeasuredItemProvider.getChildConstraints(), LazyLayoutMeasuredItemKt.updatedVisibleItems(numValueOf != null ? numValueOf.intValue() : 0, numValueOf2 != null ? numValueOf2.intValue() : 0, listCalculateItemsOffsets, listApplyStickyItems), i30, i47, i, z2, z ? Orientation.Vertical : Orientation.Horizontal, i4, i5, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measureLazyList__s_dbAc$lambda$8$0(List list, List list2, boolean z, Placeable.PlacementScope placementScope) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ((LazyListMeasuredItem) list.get(i)).place(placementScope, z);
        }
        int size2 = list2.size();
        for (int i2 = 0; i2 < size2; i2++) {
            ((LazyListMeasuredItem) list2.get(i2)).place(placementScope, z);
        }
        return Unit.INSTANCE;
    }
}
