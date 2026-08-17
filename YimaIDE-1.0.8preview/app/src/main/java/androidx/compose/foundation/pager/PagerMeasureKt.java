package androidx.compose.foundation.pager;

import androidx.collection.MutableIntObjectMap;
import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.gestures.snapping.SnapPosition;
import androidx.compose.foundation.gestures.snapping.SnapPositionKt;
import androidx.compose.foundation.internal.InlineClassHelperKt;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.lazy.layout.LazyLayoutMeasureScope;
import androidx.compose.foundation.lazy.layout.ObservableScopeInvalidator;
import androidx.compose.foundation.pager.PagerMeasureKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArrayDeque;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntProgression;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.CoroutineScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000®\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\t\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\u001a\u008a\u0002\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00042\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\u00042\u0006\u0010\f\u001a\u00020\u00042\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$2/\u0010%\u001a+\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u0004\u0012\u0015\u0012\u0013\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020)0'¢\u0006\u0002\b*\u0012\u0004\u0012\u00020+0&2\u0012\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020.0\u001c0-H\u0000¢\u0006\u0004\b/\u00100\u001aH\u00101\u001a\b\u0012\u0004\u0012\u0002020\u001c2\u0006\u00103\u001a\u00020\u00042\u0006\u00104\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\u001c2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002020'H\u0002\u001a@\u00106\u001a\b\u0012\u0004\u0012\u0002020\u001c2\u0006\u00107\u001a\u00020\u00042\u0006\u0010\u001a\u001a\u00020\u00042\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00040\u001c2\u0012\u00105\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u0002020'H\u0002\u001aH\u00108\u001a\u0004\u0018\u0001022\u0006\u00109\u001a\u00020\u00042\f\u0010:\u001a\b\u0012\u0004\u0012\u0002020\u001c2\u0006\u0010\b\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u00042\u0006\u0010;\u001a\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0003\u001a\u00020\u0004H\u0002\u001a{\u00105\u001a\u000202*\u00020\u00022\u0006\u0010<\u001a\u00020\u00042\u0006\u0010=\u001a\u00020\u000e2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0013\u001a\u0004\u0018\u00010\u00142\b\u0010\u0011\u001a\u0004\u0018\u00010\u00122\u0006\u0010>\u001a\u00020?2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0019\u001a\u00020\u00042\u0012\u0010,\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020.0\u001c0-H\u0002¢\u0006\u0004\b@\u0010A\u001a\u008c\u0001\u0010B\u001a\b\u0012\u0004\u0012\u0002020C*\u00020\u00022\f\u0010D\u001a\b\u0012\u0004\u0012\u0002020\u001c2\f\u0010E\u001a\b\u0012\u0004\u0012\u0002020\u001c2\f\u0010F\u001a\b\u0012\u0004\u0012\u0002020\u001c2\u0006\u0010G\u001a\u00020\u00042\u0006\u0010H\u001a\u00020\u00042\u0006\u0010I\u001a\u00020\u00042\u0006\u0010J\u001a\u00020\u00042\u0006\u0010K\u001a\u00020\u00042\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010#\u001a\u00020$2\u0006\u0010\n\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0004H\u0002\u001a\u0017\u0010O\u001a\u00020)2\f\u0010P\u001a\b\u0012\u0004\u0012\u00020R0QH\u0082\b\"\u000e\u0010L\u001a\u00020MX\u0080T¢\u0006\u0002\n\u0000\"\u000e\u0010N\u001a\u00020MX\u0080T¢\u0006\u0002\n\u0000¨\u0006S"}, d2 = {"measurePager", "Landroidx/compose/foundation/pager/PagerMeasureResult;", "Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;", "pageCount", "", "pagerItemProvider", "Landroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;", "mainAxisAvailableSize", "beforeContentPadding", "afterContentPadding", "spaceBetweenPages", "currentPage", "currentPageOffset", "constraints", "Landroidx/compose/ui/unit/Constraints;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "verticalAlignment", "Landroidx/compose/ui/Alignment$Vertical;", "horizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "reverseLayout", "", "visualPageOffset", "Landroidx/compose/ui/unit/IntOffset;", "pageAvailableSize", "beyondViewportPageCount", "pinnedPages", "", "snapPosition", "Landroidx/compose/foundation/gestures/snapping/SnapPosition;", "placementScopeInvalidator", "Landroidx/compose/foundation/lazy/layout/ObservableScopeInvalidator;", "coroutineScope", "Lkotlinx/coroutines/CoroutineScope;", "density", "Landroidx/compose/ui/unit/Density;", "layout", "Lkotlin/Function3;", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "", "Lkotlin/ExtensionFunctionType;", "Landroidx/compose/ui/layout/MeasureResult;", "placeablesCache", "Landroidx/collection/MutableIntObjectMap;", "Landroidx/compose/ui/layout/Placeable;", "measurePager-7L1iB3k", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;ILandroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;IIIIIIJLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/ui/Alignment$Horizontal;ZJIILjava/util/List;Landroidx/compose/foundation/gestures/snapping/SnapPosition;Landroidx/compose/runtime/MutableState;Lkotlinx/coroutines/CoroutineScope;Landroidx/compose/ui/unit/Density;Lkotlin/jvm/functions/Function3;Landroidx/collection/MutableIntObjectMap;)Landroidx/compose/foundation/pager/PagerMeasureResult;", "createPagesAfterList", "Landroidx/compose/foundation/pager/MeasuredPage;", "currentLastPage", "pagesCount", "getAndMeasure", "createPagesBeforeList", "currentFirstPage", "calculateNewCurrentPage", "viewportSize", "visiblePagesInfo", "itemSize", "index", "childConstraints", "layoutDirection", "Landroidx/compose/ui/unit/LayoutDirection;", "getAndMeasure-G5IdpRk", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutMeasureScope;IJLandroidx/compose/foundation/pager/PagerLazyLayoutItemProvider;JLandroidx/compose/foundation/gestures/Orientation;Landroidx/compose/ui/Alignment$Horizontal;Landroidx/compose/ui/Alignment$Vertical;Landroidx/compose/ui/unit/LayoutDirection;ZILandroidx/collection/MutableIntObjectMap;)Landroidx/compose/foundation/pager/MeasuredPage;", "calculatePagesOffsets", "", "pages", "extraPagesBefore", "extraPagesAfter", "layoutWidth", "layoutHeight", "finalMainAxisOffset", "maxOffset", "pagesScrollOffset", "MinPageOffset", "", "MaxPageOffset", "debugLog", "generateMsg", "Lkotlin/Function0;", "", "foundation"}, k = 2, mv = {2, 0, 0}, xi = 48)
public final class PagerMeasureKt {
    public static final float MaxPageOffset = 0.5f;
    public static final float MinPageOffset = -0.5f;

    public static Unit a(Placeable.PlacementScope placementScope) {
        return Unit.INSTANCE;
    }

    public static MeasuredPage b(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j, PagerLazyLayoutItemProvider pagerLazyLayoutItemProvider, long j2, Orientation orientation, Alignment.Horizontal horizontal, Alignment.Vertical vertical, boolean z, int i, MutableIntObjectMap mutableIntObjectMap, int i2) {
        return m1231getAndMeasureG5IdpRk(lazyLayoutMeasureScope, i2, j, pagerLazyLayoutItemProvider, j2, orientation, horizontal, vertical, lazyLayoutMeasureScope.getLayoutDirection(), z, i, mutableIntObjectMap);
    }

    public static Unit c(MutableState mutableState, final List list, Placeable.PlacementScope placementScope) {
        placementScope.withMotionFrameOfReferencePlacement(new Function1() { // from class: rxa
            public final Object invoke(Object obj) {
                return PagerMeasureKt.measurePager_7L1iB3k$lambda$18$0(list, (Placeable.PlacementScope) obj);
            }
        });
        ObservableScopeInvalidator.m1154attachToScopeimpl(mutableState);
        return Unit.INSTANCE;
    }

    private static final MeasuredPage calculateNewCurrentPage(int i, List<MeasuredPage> list, int i2, int i3, int i4, SnapPosition snapPosition, int i5) {
        MeasuredPage measuredPage;
        if (list.isEmpty()) {
            measuredPage = null;
        } else {
            MeasuredPage measuredPage2 = list.get(0);
            MeasuredPage measuredPage3 = measuredPage2;
            float f = -Math.abs(SnapPositionKt.calculateDistanceToDesiredSnapPosition(i, i2, i3, i4, measuredPage3.getOffset(), measuredPage3.getIndex(), snapPosition, i5));
            int lastIndex = CollectionsKt.getLastIndex(list);
            if (1 <= lastIndex) {
                int i6 = 1;
                while (true) {
                    MeasuredPage measuredPage4 = list.get(i6);
                    MeasuredPage measuredPage5 = measuredPage4;
                    float f2 = -Math.abs(SnapPositionKt.calculateDistanceToDesiredSnapPosition(i, i2, i3, i4, measuredPage5.getOffset(), measuredPage5.getIndex(), snapPosition, i5));
                    if (Float.compare(f, f2) < 0) {
                        f = f2;
                        measuredPage2 = measuredPage4;
                    }
                    if (i6 == lastIndex) {
                        break;
                    }
                    i6++;
                }
            }
            measuredPage = measuredPage2;
        }
        return measuredPage;
    }

    private static final List<MeasuredPage> calculatePagesOffsets(LazyLayoutMeasureScope lazyLayoutMeasureScope, List<MeasuredPage> list, List<MeasuredPage> list2, List<MeasuredPage> list3, int i, int i2, int i3, int i4, int i5, Orientation orientation, boolean z, Density density, int i6, int i7) {
        ArrayList arrayList;
        int i8 = i5;
        int i9 = i7 + i6;
        int i10 = orientation == Orientation.Vertical ? i2 : i;
        int i11 = 0;
        boolean z2 = i3 < Math.min(i10, i4);
        if (z2) {
            if (!(i8 == 0)) {
                InlineClassHelperKt.throwIllegalStateException("non-zero pagesScrollOffset=" + i8);
            }
        }
        ArrayList arrayList2 = new ArrayList(list.size() + list2.size() + list3.size());
        if (z2) {
            if (!(list2.isEmpty() && list3.isEmpty())) {
                InlineClassHelperKt.throwIllegalArgumentException("No extra pages");
            }
            int size = list.size();
            int[] iArr = new int[size];
            while (i11 < size) {
                iArr[i11] = i7;
                i11++;
            }
            int[] iArr2 = new int[size];
            Arrangement.HorizontalOrVertical horizontalOrVerticalM785spacedBy0680j_4 = Arrangement.Absolute.INSTANCE.m785spacedBy0680j_4(lazyLayoutMeasureScope.toDp-u2uoSUM(i6));
            if (orientation == Orientation.Vertical) {
                horizontalOrVerticalM785spacedBy0680j_4.arrange(density, i10, iArr, iArr2);
                arrayList = arrayList2;
            } else {
                arrayList = arrayList2;
                horizontalOrVerticalM785spacedBy0680j_4.arrange(density, i10, iArr, LayoutDirection.Ltr, iArr2);
            }
            IntProgression indices = ArraysKt.getIndices(iArr2);
            if (z) {
                indices = RangesKt.reversed(indices);
            }
            int first = indices.getFirst();
            int last = indices.getLast();
            int step = indices.getStep();
            if ((step > 0 && first <= last) || (step < 0 && last <= first)) {
                while (true) {
                    int size2 = iArr2[first];
                    MeasuredPage measuredPage = list.get(calculatePagesOffsets$reverseAware(first, z, size));
                    if (z) {
                        size2 = (i10 - size2) - measuredPage.getSize();
                    }
                    measuredPage.position(size2, i, i2);
                    arrayList.add(measuredPage);
                    if (first == last) {
                        break;
                    }
                    first += step;
                }
            }
        } else {
            arrayList = arrayList2;
            int size3 = list2.size();
            int i12 = i8;
            for (int i13 = 0; i13 < size3; i13++) {
                MeasuredPage measuredPage2 = list2.get(i13);
                i12 -= i9;
                measuredPage2.position(i12, i, i2);
                arrayList.add(measuredPage2);
            }
            int size4 = list.size();
            for (int i14 = 0; i14 < size4; i14++) {
                MeasuredPage measuredPage3 = list.get(i14);
                measuredPage3.position(i8, i, i2);
                arrayList.add(measuredPage3);
                i8 += i9;
            }
            int size5 = list3.size();
            while (i11 < size5) {
                MeasuredPage measuredPage4 = list3.get(i11);
                measuredPage4.position(i8, i, i2);
                arrayList.add(measuredPage4);
                i8 += i9;
                i11++;
            }
        }
        return arrayList;
    }

    private static final int calculatePagesOffsets$reverseAware(int i, boolean z, int i2) {
        return !z ? i : (i2 - i) - 1;
    }

    private static final List<MeasuredPage> createPagesAfterList(int i, int i2, int i3, List<Integer> list, Function1<? super Integer, MeasuredPage> function1) {
        int iMin = Math.min(i3, (i2 - i) - 1) + i;
        int i4 = i + 1;
        ArrayList arrayList = null;
        if (i4 <= iMin) {
            while (true) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(function1.invoke(Integer.valueOf(i4)));
                if (i4 == iMin) {
                    break;
                }
                i4++;
            }
        }
        int size = list.size();
        for (int i5 = 0; i5 < size; i5++) {
            int iIntValue = list.get(i5).intValue();
            if (iMin + 1 <= iIntValue && iIntValue < i2) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(function1.invoke(Integer.valueOf(iIntValue)));
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    private static final List<MeasuredPage> createPagesBeforeList(int i, int i2, List<Integer> list, Function1<? super Integer, MeasuredPage> function1) {
        int iMax = Math.max(0, i - i2);
        int i3 = i - 1;
        ArrayList arrayList = null;
        if (iMax <= i3) {
            while (true) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(function1.invoke(Integer.valueOf(i3)));
                if (i3 == iMax) {
                    break;
                }
                i3--;
            }
        }
        int size = list.size();
        for (int i4 = 0; i4 < size; i4++) {
            int iIntValue = list.get(i4).intValue();
            if (iIntValue < iMax) {
                if (arrayList == null) {
                    arrayList = new ArrayList();
                }
                arrayList.add(function1.invoke(Integer.valueOf(iIntValue)));
            }
        }
        return arrayList == null ? CollectionsKt.emptyList() : arrayList;
    }

    public static MeasuredPage d(LazyLayoutMeasureScope lazyLayoutMeasureScope, long j, PagerLazyLayoutItemProvider pagerLazyLayoutItemProvider, long j2, Orientation orientation, Alignment.Horizontal horizontal, Alignment.Vertical vertical, boolean z, int i, MutableIntObjectMap mutableIntObjectMap, int i2) {
        return m1231getAndMeasureG5IdpRk(lazyLayoutMeasureScope, i2, j, pagerLazyLayoutItemProvider, j2, orientation, horizontal, vertical, lazyLayoutMeasureScope.getLayoutDirection(), z, i, mutableIntObjectMap);
    }

    private static final void debugLog(Function0<String> function0) {
    }

    /* JADX INFO: renamed from: getAndMeasure-G5IdpRk, reason: not valid java name */
    private static final MeasuredPage m1231getAndMeasureG5IdpRk(LazyLayoutMeasureScope lazyLayoutMeasureScope, int i, long j, PagerLazyLayoutItemProvider pagerLazyLayoutItemProvider, long j2, Orientation orientation, Alignment.Horizontal horizontal, Alignment.Vertical vertical, LayoutDirection layoutDirection, boolean z, int i2, MutableIntObjectMap<List<Placeable>> mutableIntObjectMap) {
        List<Placeable> list;
        Object key = pagerLazyLayoutItemProvider.getKey(i);
        List<Placeable> list2 = mutableIntObjectMap.get(i);
        if (list2 != null) {
            list = list2;
        } else {
            List<Measurable> listCompose = lazyLayoutMeasureScope.compose(i);
            int size = listCompose.size();
            ArrayList arrayList = new ArrayList(size);
            for (int i3 = 0; i3 < size; i3++) {
                arrayList.add(listCompose.get(i3).measure-BRTryo0(j));
            }
            mutableIntObjectMap.set(i, arrayList);
            list = arrayList;
        }
        return new MeasuredPage(i, i2, list, j2, key, orientation, horizontal, vertical, layoutDirection, z, null);
    }

    /* JADX INFO: renamed from: measurePager-7L1iB3k, reason: not valid java name */
    public static final PagerMeasureResult m1232measurePager7L1iB3k(final LazyLayoutMeasureScope lazyLayoutMeasureScope, int i, final PagerLazyLayoutItemProvider pagerLazyLayoutItemProvider, int i2, int i3, int i4, int i5, int i6, int i7, long j, final Orientation orientation, final Alignment.Vertical vertical, final Alignment.Horizontal horizontal, final boolean z, final long j2, final int i8, int i9, List<Integer> list, SnapPosition snapPosition, final MutableState<Unit> mutableState, CoroutineScope coroutineScope, Density density, Function3<? super Integer, ? super Integer, ? super Function1<? super Placeable.PlacementScope, Unit>, ? extends MeasureResult> function3, final MutableIntObjectMap<List<Placeable>> mutableIntObjectMap) {
        int i10;
        boolean z2;
        int iMax;
        int i11;
        int i12;
        int i13;
        MeasuredPage measuredPage;
        List<MeasuredPage> list2;
        List arrayList;
        List arrayList2;
        int i14;
        if (!(i3 >= 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("negative beforeContentPadding");
        }
        if (!(i4 >= 0)) {
            InlineClassHelperKt.throwIllegalArgumentException("negative afterContentPadding");
        }
        int iCoerceAtLeast = RangesKt.coerceAtLeast(i8 + i5, 0);
        int iCoerceAtMost = RangesKt.coerceAtMost(i9, i);
        Orientation orientation2 = Orientation.Vertical;
        final long jConstraints$default = ConstraintsKt.Constraints$default(0, orientation == orientation2 ? Constraints.getMaxWidth-impl(j) : i8, 0, orientation != orientation2 ? Constraints.getMaxHeight-impl(j) : i8, 5, (Object) null);
        if (i <= 0) {
            return new PagerMeasureResult(CollectionsKt.emptyList(), i8, i5, i4, orientation, -i3, i2 + i4, false, iCoerceAtMost, null, null, 0.0f, 0, false, snapPosition, (MeasureResult) function3.invoke(Integer.valueOf(Constraints.getMinWidth-impl(j)), Integer.valueOf(Constraints.getMinHeight-impl(j)), new Function1() { // from class: nxa
                public final Object invoke(Object obj) {
                    return PagerMeasureKt.a((Placeable.PlacementScope) obj);
                }
            }), false, null, null, coroutineScope, density, jConstraints$default, 393216, null);
        }
        int i15 = iCoerceAtMost;
        int i16 = i6;
        int i17 = i7;
        while (i16 > 0 && i17 > 0) {
            i16--;
            i17 -= iCoerceAtLeast;
        }
        int i18 = i17 * (-1);
        if (i16 >= i) {
            i16 = i - 1;
            i18 = 0;
        }
        ArrayDeque arrayDeque = new ArrayDeque();
        int i19 = -i3;
        int i20 = (i5 < 0 ? i5 : 0) + i19;
        int i21 = i18 + i20;
        int iMax2 = 0;
        while (i21 < 0 && i16 > 0) {
            int i22 = i16 - 1;
            MeasuredPage measuredPageM1231getAndMeasureG5IdpRk = m1231getAndMeasureG5IdpRk(lazyLayoutMeasureScope, i22, jConstraints$default, pagerLazyLayoutItemProvider, j2, orientation, horizontal, vertical, lazyLayoutMeasureScope.getLayoutDirection(), z, i8, mutableIntObjectMap);
            arrayDeque.add(0, measuredPageM1231getAndMeasureG5IdpRk);
            iMax2 = Math.max(iMax2, measuredPageM1231getAndMeasureG5IdpRk.getCrossAxisSize());
            i21 += iCoerceAtLeast;
            i16 = i22;
        }
        if (i21 < i20) {
            i21 = i20;
        }
        int i23 = i21 - i20;
        int i24 = i2 + i4;
        int i25 = i16;
        int iCoerceAtLeast2 = RangesKt.coerceAtLeast(i24, 0);
        int i26 = -i23;
        int i27 = i25;
        int i28 = 0;
        boolean z3 = false;
        while (i28 < arrayDeque.size()) {
            if (i26 >= iCoerceAtLeast2) {
                arrayDeque.remove(i28);
                Unit unit = Unit.INSTANCE;
                z3 = true;
            } else {
                i27++;
                i26 += iCoerceAtLeast;
                i28++;
            }
        }
        int i29 = i23;
        int i30 = i27;
        boolean z4 = z3;
        while (i30 < i && (i26 < iCoerceAtLeast2 || i26 <= 0 || arrayDeque.isEmpty())) {
            int i31 = iCoerceAtLeast2;
            int i32 = i30;
            int iMax3 = iMax2;
            MeasuredPage measuredPageM1231getAndMeasureG5IdpRk2 = m1231getAndMeasureG5IdpRk(lazyLayoutMeasureScope, i32, jConstraints$default, pagerLazyLayoutItemProvider, j2, orientation, horizontal, vertical, lazyLayoutMeasureScope.getLayoutDirection(), z, i8, mutableIntObjectMap);
            int i33 = i29;
            int i34 = i - 1;
            i26 += i32 == i34 ? i8 : iCoerceAtLeast;
            if (i26 > i20 || i32 == i34) {
                iMax3 = Math.max(iMax3, measuredPageM1231getAndMeasureG5IdpRk2.getCrossAxisSize());
                arrayDeque.add(measuredPageM1231getAndMeasureG5IdpRk2);
                i14 = i25;
                i29 = i33;
            } else {
                i14 = i32 + 1;
                i29 = i33 - iCoerceAtLeast;
                Unit unit2 = Unit.INSTANCE;
                z4 = true;
            }
            iMax2 = iMax3;
            i30 = i32 + 1;
            i25 = i14;
            iCoerceAtLeast2 = i31;
        }
        int i35 = iMax2;
        int i36 = i30;
        int i37 = i29;
        if (i26 < i2) {
            int i38 = i2 - i26;
            i11 = i37 - i38;
            i26 += i38;
            iMax = i35;
            i12 = i25;
            while (i11 < i3 && i12 > 0) {
                int i39 = i12 - 1;
                MeasuredPage measuredPageM1231getAndMeasureG5IdpRk3 = m1231getAndMeasureG5IdpRk(lazyLayoutMeasureScope, i39, jConstraints$default, pagerLazyLayoutItemProvider, j2, orientation, horizontal, vertical, lazyLayoutMeasureScope.getLayoutDirection(), z, i8, mutableIntObjectMap);
                arrayDeque.add(0, measuredPageM1231getAndMeasureG5IdpRk3);
                iMax = Math.max(iMax, measuredPageM1231getAndMeasureG5IdpRk3.getCrossAxisSize());
                i11 += iCoerceAtLeast;
                i36 = i36;
                i12 = i39;
            }
            i10 = i36;
            z2 = false;
            if (i11 < 0) {
                i26 += i11;
                i11 = 0;
            }
        } else {
            i10 = i36;
            z2 = false;
            iMax = i35;
            i11 = i37;
            i12 = i25;
        }
        if (!(i11 >= 0 ? true : z2)) {
            InlineClassHelperKt.throwIllegalArgumentException("invalid currentFirstPageScrollOffset");
        }
        int i40 = iMax;
        int i41 = -i11;
        MeasuredPage measuredPage2 = (MeasuredPage) arrayDeque.first();
        if (i3 > 0 || i5 < 0) {
            int size = arrayDeque.size();
            i13 = i41;
            int i42 = 0;
            while (i42 < size && i11 != 0 && iCoerceAtLeast <= i11 && i42 != CollectionsKt.getLastIndex(arrayDeque)) {
                i11 -= iCoerceAtLeast;
                i42++;
                measuredPage2 = (MeasuredPage) arrayDeque.get(i42);
            }
        } else {
            i13 = i41;
        }
        int i43 = i11;
        MeasuredPage measuredPage3 = measuredPage2;
        List<MeasuredPage> listCreatePagesBeforeList = createPagesBeforeList(i12, i15, list, new Function1() { // from class: oxa
            public final Object invoke(Object obj) {
                return PagerMeasureKt.b(lazyLayoutMeasureScope, jConstraints$default, pagerLazyLayoutItemProvider, j2, orientation, horizontal, vertical, z, i8, mutableIntObjectMap, ((Integer) obj).intValue());
            }
        });
        int size2 = listCreatePagesBeforeList.size();
        int iMax4 = i40;
        int i44 = 0;
        while (i44 < size2) {
            iMax4 = Math.max(iMax4, listCreatePagesBeforeList.get(i44).getCrossAxisSize());
            i44++;
            listCreatePagesBeforeList = listCreatePagesBeforeList;
        }
        List<MeasuredPage> list3 = listCreatePagesBeforeList;
        List<MeasuredPage> listCreatePagesAfterList = createPagesAfterList(((MeasuredPage) arrayDeque.last()).getIndex(), i, i15, list, new Function1() { // from class: pxa
            public final Object invoke(Object obj) {
                return PagerMeasureKt.d(lazyLayoutMeasureScope, jConstraints$default, pagerLazyLayoutItemProvider, j2, orientation, horizontal, vertical, z, i8, mutableIntObjectMap, ((Integer) obj).intValue());
            }
        });
        int size3 = listCreatePagesAfterList.size();
        int i45 = 0;
        while (i45 < size3) {
            iMax4 = Math.max(iMax4, listCreatePagesAfterList.get(i45).getCrossAxisSize());
            i45++;
            i15 = i15;
        }
        int i46 = i15;
        boolean z5 = Intrinsics.areEqual(measuredPage3, arrayDeque.first()) && list3.isEmpty() && listCreatePagesAfterList.isEmpty();
        Orientation orientation3 = Orientation.Vertical;
        int i47 = ConstraintsKt.constrainWidth-K40F9xA(j, orientation == orientation3 ? iMax4 : i26);
        if (orientation == orientation3) {
            iMax4 = i26;
        }
        int i48 = ConstraintsKt.constrainHeight-K40F9xA(j, iMax4);
        int i49 = iCoerceAtLeast;
        int i50 = i10;
        int i51 = i26;
        final List<MeasuredPage> listCalculatePagesOffsets = calculatePagesOffsets(lazyLayoutMeasureScope, arrayDeque, list3, listCreatePagesAfterList, i47, i48, i51, i2, i13, orientation, z, lazyLayoutMeasureScope, i5, i8);
        if (z5) {
            measuredPage = measuredPage3;
            list2 = listCalculatePagesOffsets;
        } else {
            ArrayList arrayList3 = new ArrayList(listCalculatePagesOffsets.size());
            int size4 = listCalculatePagesOffsets.size();
            int i52 = 0;
            while (i52 < size4) {
                MeasuredPage measuredPage4 = listCalculatePagesOffsets.get(i52);
                MeasuredPage measuredPage5 = measuredPage4;
                MeasuredPage measuredPage6 = measuredPage3;
                int i53 = i49;
                if (measuredPage5.getIndex() >= ((MeasuredPage) arrayDeque.first()).getIndex() && measuredPage5.getIndex() <= ((MeasuredPage) arrayDeque.last()).getIndex()) {
                    arrayList3.add(measuredPage4);
                }
                i52++;
                i49 = i53;
                measuredPage3 = measuredPage6;
            }
            measuredPage = measuredPage3;
            list2 = arrayList3;
        }
        int i54 = i49;
        if (list3.isEmpty()) {
            arrayList = CollectionsKt.emptyList();
        } else {
            arrayList = new ArrayList(listCalculatePagesOffsets.size());
            int size5 = listCalculatePagesOffsets.size();
            for (int i55 = 0; i55 < size5; i55++) {
                MeasuredPage measuredPage7 = listCalculatePagesOffsets.get(i55);
                if (measuredPage7.getIndex() < ((MeasuredPage) arrayDeque.first()).getIndex()) {
                    arrayList.add(measuredPage7);
                }
            }
        }
        List list4 = arrayList;
        if (listCreatePagesAfterList.isEmpty()) {
            arrayList2 = CollectionsKt.emptyList();
        } else {
            arrayList2 = new ArrayList(listCalculatePagesOffsets.size());
            int size6 = listCalculatePagesOffsets.size();
            for (int i56 = 0; i56 < size6; i56++) {
                MeasuredPage measuredPage8 = listCalculatePagesOffsets.get(i56);
                if (measuredPage8.getIndex() > ((MeasuredPage) arrayDeque.last()).getIndex()) {
                    arrayList2.add(measuredPage8);
                }
            }
        }
        List list5 = arrayList2;
        int i57 = i2 + i3 + i4;
        MeasuredPage measuredPageCalculateNewCurrentPage = calculateNewCurrentPage(i57, list2, i3, i4, i8, snapPosition, i);
        return new PagerMeasureResult(list2, i8, i5, i4, orientation, i19, i24, z, i46, measuredPage, measuredPageCalculateNewCurrentPage, i54 == 0 ? 0.0f : RangesKt.coerceIn((snapPosition.position(i57, i8, i3, i4, measuredPageCalculateNewCurrentPage != null ? measuredPageCalculateNewCurrentPage.getIndex() : 0, i) - (measuredPageCalculateNewCurrentPage != null ? measuredPageCalculateNewCurrentPage.getOffset() : 0)) / i54, -0.5f, 0.5f), i43, i50 < i || i51 > i2, snapPosition, (MeasureResult) function3.invoke(Integer.valueOf(i47), Integer.valueOf(i48), new Function1() { // from class: qxa
            public final Object invoke(Object obj) {
                return PagerMeasureKt.c(mutableState, listCalculatePagesOffsets, (Placeable.PlacementScope) obj);
            }
        }), z4, list4, list5, coroutineScope, density, jConstraints$default, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit measurePager_7L1iB3k$lambda$18$0(List list, Placeable.PlacementScope placementScope) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            ((MeasuredPage) list.get(i)).place(placementScope);
        }
        return Unit.INSTANCE;
    }
}
