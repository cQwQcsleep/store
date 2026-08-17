package androidx.compose.material3;

import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.material3.internal.FloatProducer;
import androidx.compose.ui.Alignment;
import androidx.compose.ui.layout.AlignmentLineKt;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasurePolicy;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.LayoutDirection;
import androidx.compose.ui.util.ListUtilsKt;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.math.MathKt;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0002\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ)\u0010\u0019\u001a\u00020\u001a*\u00020\u001b2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001e0\u001d2\u0006\u0010\u001f\u001a\u00020 H\u0016¢\u0006\u0004\b!\u0010\"J\"\u0010#\u001a\u00020\t*\u00020$2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020%0\u001d2\u0006\u0010\n\u001a\u00020\tH\u0016J\"\u0010&\u001a\u00020\t*\u00020$2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020%0\u001d2\u0006\u0010'\u001a\u00020\tH\u0016J\"\u0010(\u001a\u00020\t*\u00020$2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020%0\u001d2\u0006\u0010\n\u001a\u00020\tH\u0016J\"\u0010)\u001a\u00020\t*\u00020$2\f\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020%0\u001d2\u0006\u0010'\u001a\u00020\tH\u0016JK\u0010*\u001a\u00020\u001a*\u00020\u001b2\u0006\u0010\u001f\u001a\u00020 2\u0006\u0010+\u001a\u00020\t2\u0006\u0010,\u001a\u00020\t2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u00020.2\u0006\u00100\u001a\u00020.2\u0006\u00101\u001a\u00020\tH\u0002¢\u0006\u0004\b2\u00103R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0013\u0010\n\u001a\u00020\u000b¢\u0006\n\n\u0002\u0010\u0018\u001a\u0004\b\u0016\u0010\u0017¨\u00064"}, d2 = {"Landroidx/compose/material3/TopAppBarMeasurePolicy;", "Landroidx/compose/ui/layout/MeasurePolicy;", "scrolledOffset", "Landroidx/compose/material3/internal/FloatProducer;", "titleVerticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "titleHorizontalAlignment", "Landroidx/compose/ui/Alignment$Horizontal;", "titleBottomPadding", "", "height", "Landroidx/compose/ui/unit/Dp;", "<init>", "(Landroidx/compose/material3/internal/FloatProducer;Landroidx/compose/foundation/layout/Arrangement$Vertical;Landroidx/compose/ui/Alignment$Horizontal;IFLkotlin/jvm/internal/DefaultConstructorMarker;)V", "getScrolledOffset", "()Landroidx/compose/material3/internal/FloatProducer;", "getTitleVerticalArrangement", "()Landroidx/compose/foundation/layout/Arrangement$Vertical;", "getTitleHorizontalAlignment", "()Landroidx/compose/ui/Alignment$Horizontal;", "getTitleBottomPadding", "()I", "getHeight-D9Ej5fM", "()F", "F", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicWidth", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "minIntrinsicHeight", "width", "maxIntrinsicWidth", "maxIntrinsicHeight", "placeTopAppBar", "layoutHeight", "maxLayoutHeight", "navigationIconPlaceable", "Landroidx/compose/ui/layout/Placeable;", "titlePlaceable", "actionIconsPlaceable", "titleBaseline", "placeTopAppBar-mpW86Vk", "(Landroidx/compose/ui/layout/MeasureScope;JIILandroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;Landroidx/compose/ui/layout/Placeable;I)Landroidx/compose/ui/layout/MeasureResult;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class TopAppBarMeasurePolicy implements MeasurePolicy {
    private final float height;
    private final FloatProducer scrolledOffset;
    private final int titleBottomPadding;
    private final Alignment.Horizontal titleHorizontalAlignment;
    private final Arrangement.Vertical titleVerticalArrangement;

    private TopAppBarMeasurePolicy(FloatProducer floatProducer, Arrangement.Vertical vertical, Alignment.Horizontal horizontal, int i, float f) {
        this.scrolledOffset = floatProducer;
        this.titleVerticalArrangement = vertical;
        this.titleHorizontalAlignment = horizontal;
        this.titleBottomPadding = i;
        this.height = f;
    }

    /* JADX WARN: Code duplicated, block: B:12:0x0068  */
    /* JADX WARN: Code duplicated, block: B:14:0x0072  */
    /* JADX WARN: Code duplicated, block: B:16:0x007d  */
    /* JADX WARN: Code duplicated, block: B:18:0x0081  */
    /* JADX WARN: Code duplicated, block: B:19:0x0088  */
    /* JADX WARN: Code duplicated, block: B:21:0x0096  */
    public static Unit a(Placeable placeable, int i, Placeable placeable2, Placeable placeable3, long j, MeasureScope measureScope, TopAppBarMeasurePolicy topAppBarMeasurePolicy, int i2, int i3, Placeable.PlacementScope placementScope) {
        int iM5975getMaxWidthimpl;
        Arrangement.Vertical vertical;
        Arrangement arrangement;
        boolean zAreEqual;
        int i4;
        int i5;
        int height;
        int height2;
        int height3;
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable, 0, (i - placeable.getHeight()) / 2, 0.0f, 4, null);
        int iMax = Math.max(measureScope.mo4551roundToPx0680j_4(AppBarKt.TopAppBarTitleInset), placeable.getWidth());
        int width = placeable3.getWidth();
        int iAlign = topAppBarMeasurePolicy.titleHorizontalAlignment.align(placeable2.getWidth(), Constraints.m5975getMaxWidthimpl(j), LayoutDirection.Ltr);
        if (iAlign >= iMax) {
            if (placeable2.getWidth() + iAlign > Constraints.m5975getMaxWidthimpl(j) - width) {
                iM5975getMaxWidthimpl = (Constraints.m5975getMaxWidthimpl(j) - width) - (placeable2.getWidth() + iAlign);
            }
            int i6 = iAlign;
            vertical = topAppBarMeasurePolicy.titleVerticalArrangement;
            arrangement = Arrangement.INSTANCE;
            if (Intrinsics.areEqual(vertical, arrangement.getCenter())) {
                zAreEqual = Intrinsics.areEqual(vertical, arrangement.getBottom());
                i4 = 0;
                if (zAreEqual) {
                    i5 = topAppBarMeasurePolicy.titleBottomPadding;
                    if (i5 == 0) {
                        height3 = i - placeable2.getHeight();
                    } else {
                        height = i5 - (placeable2.getHeight() - i2);
                        height2 = placeable2.getHeight() + height;
                        if (height2 > i3) {
                            height -= height2 - i3;
                        }
                        height3 = (i - placeable2.getHeight()) - Math.max(0, height);
                    }
                }
                Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i6, i4, 0.0f, 4, null);
                Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, Constraints.m5975getMaxWidthimpl(j) - placeable3.getWidth(), (i - placeable3.getHeight()) / 2, 0.0f, 4, null);
                return Unit.INSTANCE;
            }
            height3 = (i - placeable2.getHeight()) / 2;
            i4 = height3;
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i6, i4, 0.0f, 4, null);
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, Constraints.m5975getMaxWidthimpl(j) - placeable3.getWidth(), (i - placeable3.getHeight()) / 2, 0.0f, 4, null);
            return Unit.INSTANCE;
        }
        iM5975getMaxWidthimpl = iMax - iAlign;
        iAlign += iM5975getMaxWidthimpl;
        int i7 = iAlign;
        vertical = topAppBarMeasurePolicy.titleVerticalArrangement;
        arrangement = Arrangement.INSTANCE;
        if (Intrinsics.areEqual(vertical, arrangement.getCenter())) {
            zAreEqual = Intrinsics.areEqual(vertical, arrangement.getBottom());
            i4 = 0;
            if (zAreEqual) {
                i5 = topAppBarMeasurePolicy.titleBottomPadding;
                if (i5 == 0) {
                    height3 = i - placeable2.getHeight();
                } else {
                    height = i5 - (placeable2.getHeight() - i2);
                    height2 = placeable2.getHeight() + height;
                    if (height2 > i3) {
                        height -= height2 - i3;
                    }
                    height3 = (i - placeable2.getHeight()) - Math.max(0, height);
                }
            }
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i7, i4, 0.0f, 4, null);
            Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, Constraints.m5975getMaxWidthimpl(j) - placeable3.getWidth(), (i - placeable3.getHeight()) / 2, 0.0f, 4, null);
            return Unit.INSTANCE;
        }
        height3 = (i - placeable2.getHeight()) / 2;
        i4 = height3;
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable2, i7, i4, 0.0f, 4, null);
        Placeable.PlacementScope.placeRelative$default(placementScope, placeable3, Constraints.m5975getMaxWidthimpl(j) - placeable3.getWidth(), (i - placeable3.getHeight()) / 2, 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: placeTopAppBar-mpW86Vk, reason: not valid java name */
    private final MeasureResult m1314placeTopAppBarmpW86Vk(final MeasureScope measureScope, final long j, final int i, final int i2, final Placeable placeable, final Placeable placeable2, final Placeable placeable3, final int i3) {
        return MeasureScope.layout$default(measureScope, Constraints.m5975getMaxWidthimpl(j), i, null, new Function1() { // from class: androidx.compose.material3.n5
            public final Object invoke(Object obj) {
                return TopAppBarMeasurePolicy.a(placeable, i, placeable2, placeable3, j, measureScope, this, i3, i2, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    /* JADX INFO: renamed from: getHeight-D9Ej5fM, reason: not valid java name and from getter */
    public final float getHeight() {
        return this.height;
    }

    public final FloatProducer getScrolledOffset() {
        return this.scrolledOffset;
    }

    public final int getTitleBottomPadding() {
        return this.titleBottomPadding;
    }

    public final Alignment.Horizontal getTitleHorizontalAlignment() {
        return this.titleHorizontalAlignment;
    }

    public final Arrangement.Vertical getTitleVerticalArrangement() {
        return this.titleVerticalArrangement;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        Integer numValueOf;
        int iMo4551roundToPx0680j_4 = intrinsicMeasureScope.mo4551roundToPx0680j_4(this.height);
        if (!list.isEmpty()) {
            numValueOf = Integer.valueOf(list.get(0).maxIntrinsicHeight(i));
            int lastIndex = CollectionsKt.getLastIndex(list);
            int i2 = 1;
            if (1 <= lastIndex) {
                while (true) {
                    Integer numValueOf2 = Integer.valueOf(list.get(i2).maxIntrinsicHeight(i));
                    if (numValueOf2.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        } else {
            numValueOf = null;
        }
        return Math.max(iMo4551roundToPx0680j_4, numValueOf != null ? numValueOf.intValue() : 0);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        int size = list.size();
        int iMaxIntrinsicWidth = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iMaxIntrinsicWidth += list.get(i2).maxIntrinsicWidth(i);
        }
        return iMaxIntrinsicWidth;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo14measure3p2s80s(MeasureScope measureScope, List<? extends Measurable> list, long j) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            Measurable measurable = list.get(i);
            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable), "navigationIcon")) {
                Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(j, 0, 0, 0, 0, 14, null));
                List<? extends Measurable> list2 = list;
                int size2 = list2.size();
                int i2 = 0;
                while (i2 < size2) {
                    Measurable measurable2 = list.get(i2);
                    if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable2), "actionIcons")) {
                        Placeable placeableMo4605measureBRTryo1 = measurable2.mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(j, 0, 0, 0, 0, 14, null));
                        int iM5975getMaxWidthimpl = Constraints.m5975getMaxWidthimpl(j) == Integer.MAX_VALUE ? Constraints.m5975getMaxWidthimpl(j) : RangesKt.coerceAtLeast((Constraints.m5975getMaxWidthimpl(j) - placeableMo4605measureBRTryo0.getWidth()) - placeableMo4605measureBRTryo1.getWidth(), 0);
                        int size3 = list2.size();
                        int i3 = 0;
                        while (i3 < size3) {
                            Measurable measurable3 = list.get(i3);
                            if (Intrinsics.areEqual(LayoutIdKt.getLayoutId(measurable3), "title")) {
                                Placeable placeableMo4605measureBRTryo2 = measurable3.mo4605measureBRTryo0(Constraints.m5965copyZbe2FdA$default(j, 0, iM5975getMaxWidthimpl, 0, 0, 12, null));
                                int i4 = placeableMo4605measureBRTryo2.get(AlignmentLineKt.getLastBaseline()) != Integer.MIN_VALUE ? placeableMo4605measureBRTryo2.get(AlignmentLineKt.getLastBaseline()) : 0;
                                float fInvoke = this.scrolledOffset.invoke();
                                int iRoundToInt = Float.isNaN(fInvoke) ? 0 : MathKt.roundToInt(fInvoke);
                                int iMax = Math.max(measureScope.mo4551roundToPx0680j_4(this.height), placeableMo4605measureBRTryo2.getHeight());
                                return this.m1314placeTopAppBarmpW86Vk(measureScope, j, Constraints.m5974getMaxHeightimpl(j) == Integer.MAX_VALUE ? iMax : RangesKt.coerceAtLeast(iRoundToInt + iMax, 0), iMax, placeableMo4605measureBRTryo0, placeableMo4605measureBRTryo2, placeableMo4605measureBRTryo1, i4);
                            }
                            i3++;
                            this = this;
                        }
                        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                        wq6.a();
                        return null;
                    }
                    i2++;
                    this = this;
                }
                ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
                wq6.a();
                return null;
            }
        }
        ListUtilsKt.throwNoSuchElementException("Collection contains no element matching the predicate.");
        wq6.a();
        return null;
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        Integer numValueOf;
        int iMo4551roundToPx0680j_4 = intrinsicMeasureScope.mo4551roundToPx0680j_4(this.height);
        if (!list.isEmpty()) {
            numValueOf = Integer.valueOf(list.get(0).minIntrinsicHeight(i));
            int lastIndex = CollectionsKt.getLastIndex(list);
            int i2 = 1;
            if (1 <= lastIndex) {
                while (true) {
                    Integer numValueOf2 = Integer.valueOf(list.get(i2).minIntrinsicHeight(i));
                    if (numValueOf2.compareTo(numValueOf) > 0) {
                        numValueOf = numValueOf2;
                    }
                    if (i2 == lastIndex) {
                        break;
                    }
                    i2++;
                }
            }
        } else {
            numValueOf = null;
        }
        return Math.max(iMo4551roundToPx0680j_4, numValueOf != null ? numValueOf.intValue() : 0);
    }

    @Override // androidx.compose.ui.layout.MeasurePolicy
    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends IntrinsicMeasurable> list, int i) {
        int size = list.size();
        int iMinIntrinsicWidth = 0;
        for (int i2 = 0; i2 < size; i2++) {
            iMinIntrinsicWidth += list.get(i2).minIntrinsicWidth(i);
        }
        return iMinIntrinsicWidth;
    }

    public /* synthetic */ TopAppBarMeasurePolicy(FloatProducer floatProducer, Arrangement.Vertical vertical, Alignment.Horizontal horizontal, int i, float f, DefaultConstructorMarker defaultConstructorMarker) {
        this(floatProducer, vertical, horizontal, i, f);
    }
}
