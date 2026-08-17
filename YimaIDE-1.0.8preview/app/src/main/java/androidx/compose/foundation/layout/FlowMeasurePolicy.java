package androidx.compose.foundation.layout;

import androidx.collection.IntIntPair;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.MultiContentMeasurePolicy;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.Dp;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000t\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b!\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u00012\u00020\u0002BO\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\n\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u000f\u0012\u0006\u0010\u0011\u001a\u00020\u0012¢\u0006\u0004\b\u0013\u0010\u0014J/\u0010\u001d\u001a\u00020\u001e*\u00020\u001f2\u0012\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\"0!0!2\u0006\u0010#\u001a\u00020$H\u0016¢\u0006\u0004\b%\u0010&J(\u0010'\u001a\u00020\u000f*\u00020(2\u0012\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0!0!2\u0006\u0010*\u001a\u00020\u000fH\u0016J(\u0010+\u001a\u00020\u000f*\u00020(2\u0012\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0!0!2\u0006\u0010,\u001a\u00020\u000fH\u0016J(\u0010-\u001a\u00020\u000f*\u00020(2\u0012\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0!0!2\u0006\u0010,\u001a\u00020\u000fH\u0016J(\u0010.\u001a\u00020\u000f*\u00020(2\u0012\u0010 \u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020)0!0!2\u0006\u0010*\u001a\u00020\u000fH\u0016JD\u0010/\u001a\u00020\u000f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020)0!2\u0006\u00100\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u000f2\u0006\u00101\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012J$\u00102\u001a\u00020\u000f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020)0!2\u0006\u0010*\u001a\u00020\u000f2\u0006\u00103\u001a\u00020\u000fJD\u00104\u001a\u00020\u000f2\f\u0010 \u001a\b\u0012\u0004\u0012\u00020)0!2\u0006\u00105\u001a\u00020\u000f2\u0006\u0010\t\u001a\u00020\u000f2\u0006\u00101\u001a\u00020\u000f2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u0012J\u0012\u00106\u001a\u00020\u000f*\u00020)2\u0006\u00107\u001a\u00020\u000fJ\u0012\u00108\u001a\u00020\u000f*\u00020)2\u0006\u00107\u001a\u00020\u000fJ\u0012\u00109\u001a\u00020\u000f*\u00020)2\u0006\u00107\u001a\u00020\u000fJ\t\u0010:\u001a\u00020\u0004HÆ\u0003J\t\u0010;\u001a\u00020\u0006HÆ\u0003J\t\u0010<\u001a\u00020\bHÆ\u0003J\u0010\u0010=\u001a\u00020\nHÂ\u0003¢\u0006\u0004\b>\u0010?J\t\u0010@\u001a\u00020\fHÆ\u0003J\u0010\u0010A\u001a\u00020\nHÂ\u0003¢\u0006\u0004\bB\u0010?J\t\u0010C\u001a\u00020\u000fHÂ\u0003J\t\u0010D\u001a\u00020\u000fHÂ\u0003J\t\u0010E\u001a\u00020\u0012HÂ\u0003Jj\u0010F\u001a\u00020\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\b\b\u0002\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\n2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\b\b\u0002\u0010\u0011\u001a\u00020\u0012HÆ\u0001¢\u0006\u0004\bG\u0010HJ\u0013\u0010I\u001a\u00020\u00042\b\u0010J\u001a\u0004\u0018\u00010KHÖ\u0003J\t\u0010L\u001a\u00020\u000fHÖ\u0001J\t\u0010M\u001a\u00020NHÖ\u0001R\u0014\u0010\u0003\u001a\u00020\u0004X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0003\u0010\u0015R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0010\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001cR\u0010\u0010\r\u001a\u00020\nX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001aR\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006O"}, d2 = {"Landroidx/compose/foundation/layout/FlowMeasurePolicy;", "Landroidx/compose/ui/layout/MultiContentMeasurePolicy;", "Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;", "isHorizontal", "", "horizontalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "verticalArrangement", "Landroidx/compose/foundation/layout/Arrangement$Vertical;", "mainAxisSpacing", "Landroidx/compose/ui/unit/Dp;", "crossAxisAlignment", "Landroidx/compose/foundation/layout/CrossAxisAlignment;", "crossAxisArrangementSpacing", "maxItemsInMainAxis", "", "maxLines", "overflow", "Landroidx/compose/foundation/layout/FlowLayoutOverflowState;", "<init>", "(ZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;FLandroidx/compose/foundation/layout/CrossAxisAlignment;FIILandroidx/compose/foundation/layout/FlowLayoutOverflowState;Lkotlin/jvm/internal/DefaultConstructorMarker;)V", "()Z", "getHorizontalArrangement", "()Landroidx/compose/foundation/layout/Arrangement$Horizontal;", "getVerticalArrangement", "()Landroidx/compose/foundation/layout/Arrangement$Vertical;", "F", "getCrossAxisAlignment", "()Landroidx/compose/foundation/layout/CrossAxisAlignment;", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurables", "", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Ljava/util/List;J)Landroidx/compose/ui/layout/MeasureResult;", "minIntrinsicWidth", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "minIntrinsicHeight", "width", "maxIntrinsicHeight", "maxIntrinsicWidth", "minIntrinsicMainAxisSize", "crossAxisAvailable", "crossAxisSpacing", "maxIntrinsicMainAxisSize", "arrangementSpacing", "intrinsicCrossAxisSize", "mainAxisAvailable", "maxMainAxisIntrinsicItemSize", "size", "minCrossAxisIntrinsicItemSize", "minMainAxisIntrinsicItemSize", "component1", "component2", "component3", "component4", "component4-D9Ej5fM", "()F", "component5", "component6", "component6-D9Ej5fM", "component7", "component8", "component9", "copy", "copy-QuyCDyQ", "(ZLandroidx/compose/foundation/layout/Arrangement$Horizontal;Landroidx/compose/foundation/layout/Arrangement$Vertical;FLandroidx/compose/foundation/layout/CrossAxisAlignment;FIILandroidx/compose/foundation/layout/FlowLayoutOverflowState;)Landroidx/compose/foundation/layout/FlowMeasurePolicy;", "equals", "other", "", "hashCode", "toString", "", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
final /* data */ class FlowMeasurePolicy implements MultiContentMeasurePolicy, FlowLineMeasurePolicy {
    private final CrossAxisAlignment crossAxisAlignment;
    private final float crossAxisArrangementSpacing;
    private final Arrangement.Horizontal horizontalArrangement;
    private final boolean isHorizontal;
    private final float mainAxisSpacing;
    private final int maxItemsInMainAxis;
    private final int maxLines;
    private final FlowLayoutOverflowState overflow;
    private final Arrangement.Vertical verticalArrangement;

    private FlowMeasurePolicy(boolean z, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, float f, CrossAxisAlignment crossAxisAlignment, float f2, int i, int i2, FlowLayoutOverflowState flowLayoutOverflowState) {
        this.isHorizontal = z;
        this.horizontalArrangement = horizontal;
        this.verticalArrangement = vertical;
        this.mainAxisSpacing = f;
        this.crossAxisAlignment = crossAxisAlignment;
        this.crossAxisArrangementSpacing = f2;
        this.maxItemsInMainAxis = i;
        this.maxLines = i2;
        this.overflow = flowLayoutOverflowState;
    }

    public static Unit b(Placeable.PlacementScope placementScope) {
        return Unit.INSTANCE;
    }

    public static Unit c(Placeable.PlacementScope placementScope) {
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: component4-D9Ej5fM, reason: not valid java name and from getter */
    private final float getMainAxisSpacing() {
        return this.mainAxisSpacing;
    }

    /* JADX INFO: renamed from: component6-D9Ej5fM, reason: not valid java name and from getter */
    private final float getCrossAxisArrangementSpacing() {
        return this.crossAxisArrangementSpacing;
    }

    /* JADX INFO: renamed from: component7, reason: from getter */
    private final int getMaxItemsInMainAxis() {
        return this.maxItemsInMainAxis;
    }

    /* JADX INFO: renamed from: component8, reason: from getter */
    private final int getMaxLines() {
        return this.maxLines;
    }

    /* JADX INFO: renamed from: component9, reason: from getter */
    private final FlowLayoutOverflowState getOverflow() {
        return this.overflow;
    }

    /* JADX INFO: renamed from: copy-QuyCDyQ$default, reason: not valid java name */
    public static /* synthetic */ FlowMeasurePolicy m860copyQuyCDyQ$default(FlowMeasurePolicy flowMeasurePolicy, boolean z, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, float f, CrossAxisAlignment crossAxisAlignment, float f2, int i, int i2, FlowLayoutOverflowState flowLayoutOverflowState, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            z = flowMeasurePolicy.isHorizontal;
        }
        if ((i3 & 2) != 0) {
            horizontal = flowMeasurePolicy.horizontalArrangement;
        }
        if ((i3 & 4) != 0) {
            vertical = flowMeasurePolicy.verticalArrangement;
        }
        if ((i3 & 8) != 0) {
            f = flowMeasurePolicy.mainAxisSpacing;
        }
        if ((i3 & 16) != 0) {
            crossAxisAlignment = flowMeasurePolicy.crossAxisAlignment;
        }
        if ((i3 & 32) != 0) {
            f2 = flowMeasurePolicy.crossAxisArrangementSpacing;
        }
        if ((i3 & 64) != 0) {
            i = flowMeasurePolicy.maxItemsInMainAxis;
        }
        if ((i3 & 128) != 0) {
            i2 = flowMeasurePolicy.maxLines;
        }
        if ((i3 & 256) != 0) {
            flowLayoutOverflowState = flowMeasurePolicy.overflow;
        }
        int i4 = i2;
        FlowLayoutOverflowState flowLayoutOverflowState2 = flowLayoutOverflowState;
        float f3 = f2;
        int i5 = i;
        CrossAxisAlignment crossAxisAlignment2 = crossAxisAlignment;
        Arrangement.Vertical vertical2 = vertical;
        return flowMeasurePolicy.m861copyQuyCDyQ(z, horizontal, vertical2, f, crossAxisAlignment2, f3, i5, i4, flowLayoutOverflowState2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final boolean getIsHorizontal() {
        return this.isHorizontal;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Arrangement.Horizontal getHorizontalArrangement() {
        return this.horizontalArrangement;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final Arrangement.Vertical getVerticalArrangement() {
        return this.verticalArrangement;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final CrossAxisAlignment getCrossAxisAlignment() {
        return this.crossAxisAlignment;
    }

    /* JADX INFO: renamed from: copy-QuyCDyQ, reason: not valid java name */
    public final FlowMeasurePolicy m861copyQuyCDyQ(boolean isHorizontal, Arrangement.Horizontal horizontalArrangement, Arrangement.Vertical verticalArrangement, float mainAxisSpacing, CrossAxisAlignment crossAxisAlignment, float crossAxisArrangementSpacing, int maxItemsInMainAxis, int maxLines, FlowLayoutOverflowState overflow) {
        return new FlowMeasurePolicy(isHorizontal, horizontalArrangement, verticalArrangement, mainAxisSpacing, crossAxisAlignment, crossAxisArrangementSpacing, maxItemsInMainAxis, maxLines, overflow, null);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FlowMeasurePolicy)) {
            return false;
        }
        FlowMeasurePolicy flowMeasurePolicy = (FlowMeasurePolicy) other;
        return this.isHorizontal == flowMeasurePolicy.isHorizontal && Intrinsics.areEqual(this.horizontalArrangement, flowMeasurePolicy.horizontalArrangement) && Intrinsics.areEqual(this.verticalArrangement, flowMeasurePolicy.verticalArrangement) && Dp.equals-impl0(this.mainAxisSpacing, flowMeasurePolicy.mainAxisSpacing) && Intrinsics.areEqual(this.crossAxisAlignment, flowMeasurePolicy.crossAxisAlignment) && Dp.equals-impl0(this.crossAxisArrangementSpacing, flowMeasurePolicy.crossAxisArrangementSpacing) && this.maxItemsInMainAxis == flowMeasurePolicy.maxItemsInMainAxis && this.maxLines == flowMeasurePolicy.maxLines && Intrinsics.areEqual(this.overflow, flowMeasurePolicy.overflow);
    }

    @Override // androidx.compose.foundation.layout.FlowLineMeasurePolicy
    public CrossAxisAlignment getCrossAxisAlignment() {
        return this.crossAxisAlignment;
    }

    @Override // androidx.compose.foundation.layout.FlowLineMeasurePolicy
    public Arrangement.Horizontal getHorizontalArrangement() {
        return this.horizontalArrangement;
    }

    @Override // androidx.compose.foundation.layout.FlowLineMeasurePolicy
    public Arrangement.Vertical getVerticalArrangement() {
        return this.verticalArrangement;
    }

    public int hashCode() {
        return (((((((((((((((Boolean.hashCode(this.isHorizontal) * 31) + this.horizontalArrangement.hashCode()) * 31) + this.verticalArrangement.hashCode()) * 31) + Dp.hashCode-impl(this.mainAxisSpacing)) * 31) + this.crossAxisAlignment.hashCode()) * 31) + Dp.hashCode-impl(this.crossAxisArrangementSpacing)) * 31) + Integer.hashCode(this.maxItemsInMainAxis)) * 31) + Integer.hashCode(this.maxLines)) * 31) + this.overflow.hashCode();
    }

    public final int intrinsicCrossAxisSize(List<? extends IntrinsicMeasurable> measurables, int mainAxisAvailable, int mainAxisSpacing, int crossAxisSpacing, int maxItemsInMainAxis, int maxLines, FlowLayoutOverflowState overflow) {
        long jM24constructorimpl;
        int i = 0;
        if (measurables.isEmpty()) {
            jM24constructorimpl = IntIntPair.m24constructorimpl(0, 0);
        } else {
            FlowLayoutBuildingBlocks flowLayoutBuildingBlocks = new FlowLayoutBuildingBlocks(maxItemsInMainAxis, overflow, OrientationIndependentConstraints.m896constructorimpl(0, mainAxisAvailable, 0, Integer.MAX_VALUE), maxLines, mainAxisSpacing, crossAxisSpacing, null);
            IntrinsicMeasurable intrinsicMeasurable = (IntrinsicMeasurable) CollectionsKt.getOrNull(measurables, 0);
            int iMinCrossAxisIntrinsicItemSize = intrinsicMeasurable != null ? minCrossAxisIntrinsicItemSize(intrinsicMeasurable, mainAxisAvailable) : 0;
            int iMinMainAxisIntrinsicItemSize = intrinsicMeasurable != null ? minMainAxisIntrinsicItemSize(intrinsicMeasurable, iMinCrossAxisIntrinsicItemSize) : 0;
            int i2 = 0;
            if (flowLayoutBuildingBlocks.m837getWrapInfoOpUlnko(measurables.size() > 1, 0, IntIntPair.m24constructorimpl(mainAxisAvailable, Integer.MAX_VALUE), intrinsicMeasurable == null ? null : IntIntPair.m21boximpl(IntIntPair.m24constructorimpl(iMinMainAxisIntrinsicItemSize, iMinCrossAxisIntrinsicItemSize)), 0, 0, 0, false, false).getIsLastItemInContainer()) {
                IntIntPair intIntPairM844ellipsisSizeF35zmw$foundation_layout = overflow.m844ellipsisSizeF35zmw$foundation_layout(intrinsicMeasurable != null, 0, 0);
                jM24constructorimpl = IntIntPair.m24constructorimpl(intIntPairM844ellipsisSizeF35zmw$foundation_layout != null ? IntIntPair.m29getSecondimpl(intIntPairM844ellipsisSizeF35zmw$foundation_layout.getPackedValue()) : 0, 0);
            } else {
                int size = measurables.size();
                int i3 = mainAxisAvailable;
                int i4 = 0;
                int i5 = 0;
                int i6 = 0;
                int i7 = 0;
                int i8 = 0;
                while (i4 < size) {
                    int i9 = i3 - iMinMainAxisIntrinsicItemSize;
                    int i10 = i4 + 1;
                    int iMax = Math.max(i8, iMinCrossAxisIntrinsicItemSize);
                    IntrinsicMeasurable intrinsicMeasurable2 = (IntrinsicMeasurable) CollectionsKt.getOrNull(measurables, i10);
                    int iMinCrossAxisIntrinsicItemSize2 = intrinsicMeasurable2 != null ? minCrossAxisIntrinsicItemSize(intrinsicMeasurable2, mainAxisAvailable) : i;
                    int iMinMainAxisIntrinsicItemSize2 = intrinsicMeasurable2 != null ? minMainAxisIntrinsicItemSize(intrinsicMeasurable2, iMinCrossAxisIntrinsicItemSize2) + mainAxisSpacing : i;
                    boolean z = i4 + 2 < measurables.size();
                    int i11 = i10 - i6;
                    int i12 = i7;
                    int i13 = iMinMainAxisIntrinsicItemSize2;
                    int i14 = iMinCrossAxisIntrinsicItemSize2;
                    FlowLayoutBuildingBlocks.WrapInfo wrapInfoM837getWrapInfoOpUlnko = flowLayoutBuildingBlocks.m837getWrapInfoOpUlnko(z, i11, IntIntPair.m24constructorimpl(i9, Integer.MAX_VALUE), intrinsicMeasurable2 == null ? null : IntIntPair.m21boximpl(IntIntPair.m24constructorimpl(iMinMainAxisIntrinsicItemSize2, iMinCrossAxisIntrinsicItemSize2)), i12, i2, iMax, false, false);
                    if (wrapInfoM837getWrapInfoOpUlnko.getIsLastItemInLine()) {
                        int iM29getSecondimpl = i2 + iMax + crossAxisSpacing;
                        FlowLayoutBuildingBlocks.WrapEllipsisInfo wrapEllipsisInfo = flowLayoutBuildingBlocks.getWrapEllipsisInfo(wrapInfoM837getWrapInfoOpUlnko, intrinsicMeasurable2 != null, i12, iM29getSecondimpl, i9, i11);
                        int i15 = i13 - mainAxisSpacing;
                        i7 = i12 + 1;
                        if (wrapInfoM837getWrapInfoOpUlnko.getIsLastItemInContainer()) {
                            if (wrapEllipsisInfo != null) {
                                long ellipsisSize = wrapEllipsisInfo.getEllipsisSize();
                                if (!wrapEllipsisInfo.getPlaceEllipsisOnLastContentLine()) {
                                    iM29getSecondimpl += IntIntPair.m29getSecondimpl(ellipsisSize) + crossAxisSpacing;
                                }
                            }
                            i2 = iM29getSecondimpl;
                            i5 = i10;
                            break;
                        }
                        i2 = iM29getSecondimpl;
                        iMinMainAxisIntrinsicItemSize = i15;
                        i6 = i10;
                        i8 = 0;
                        i3 = mainAxisAvailable;
                    } else {
                        i3 = i9;
                        i7 = i12;
                        i8 = iMax;
                        iMinMainAxisIntrinsicItemSize = i13;
                    }
                    iMinCrossAxisIntrinsicItemSize = i14;
                    i4 = i10;
                    i5 = i4;
                    i = 0;
                }
                jM24constructorimpl = IntIntPair.m24constructorimpl(i2 - crossAxisSpacing, i5);
            }
        }
        return IntIntPair.m28getFirstimpl(jM24constructorimpl);
    }

    @Override // androidx.compose.foundation.layout.FlowLineMeasurePolicy
    public boolean isHorizontal() {
        return this.isHorizontal;
    }

    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends List<? extends IntrinsicMeasurable>> list, int i) {
        FlowLayoutOverflowState flowLayoutOverflowState = this.overflow;
        List list2 = (List) CollectionsKt.getOrNull(list, 1);
        IntrinsicMeasurable intrinsicMeasurable = list2 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list2) : null;
        List list3 = (List) CollectionsKt.getOrNull(list, 2);
        flowLayoutOverflowState.m846setOverflowMeasurableshBUhpc$foundation_layout(intrinsicMeasurable, list3 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list3) : null, isHorizontal(), ConstraintsKt.Constraints$default(0, i, 0, 0, 13, (Object) null));
        if (isHorizontal()) {
            List<? extends IntrinsicMeasurable> listEmptyList = (List) CollectionsKt.firstOrNull(list);
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            return intrinsicCrossAxisSize(listEmptyList, i, intrinsicMeasureScope.roundToPx-0680j_4(this.mainAxisSpacing), intrinsicMeasureScope.roundToPx-0680j_4(this.crossAxisArrangementSpacing), this.maxItemsInMainAxis, this.maxLines, this.overflow);
        }
        List<? extends IntrinsicMeasurable> listEmptyList2 = (List) CollectionsKt.firstOrNull(list);
        if (listEmptyList2 == null) {
            listEmptyList2 = CollectionsKt.emptyList();
        }
        return maxIntrinsicMainAxisSize(listEmptyList2, i, intrinsicMeasureScope.roundToPx-0680j_4(this.mainAxisSpacing));
    }

    public final int maxIntrinsicMainAxisSize(List<? extends IntrinsicMeasurable> measurables, int height, int arrangementSpacing) {
        int i = this.maxItemsInMainAxis;
        int size = measurables.size();
        int i2 = 0;
        int iMax = 0;
        int i3 = 0;
        int i4 = 0;
        while (i2 < size) {
            int iMaxMainAxisIntrinsicItemSize = maxMainAxisIntrinsicItemSize(measurables.get(i2), height) + arrangementSpacing;
            int i5 = i2 + 1;
            if (i5 - i3 == i || i5 == measurables.size()) {
                iMax = Math.max(iMax, (i4 + iMaxMainAxisIntrinsicItemSize) - arrangementSpacing);
                i4 = 0;
                i3 = i2;
            } else {
                i4 += iMaxMainAxisIntrinsicItemSize;
            }
            i2 = i5;
        }
        return iMax;
    }

    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends List<? extends IntrinsicMeasurable>> list, int i) {
        FlowLayoutOverflowState flowLayoutOverflowState = this.overflow;
        List list2 = (List) CollectionsKt.getOrNull(list, 1);
        IntrinsicMeasurable intrinsicMeasurable = list2 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list2) : null;
        List list3 = (List) CollectionsKt.getOrNull(list, 2);
        flowLayoutOverflowState.m846setOverflowMeasurableshBUhpc$foundation_layout(intrinsicMeasurable, list3 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list3) : null, isHorizontal(), ConstraintsKt.Constraints$default(0, 0, 0, i, 7, (Object) null));
        if (isHorizontal()) {
            List<? extends IntrinsicMeasurable> listEmptyList = (List) CollectionsKt.firstOrNull(list);
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            return maxIntrinsicMainAxisSize(listEmptyList, i, intrinsicMeasureScope.roundToPx-0680j_4(this.mainAxisSpacing));
        }
        List<? extends IntrinsicMeasurable> listEmptyList2 = (List) CollectionsKt.firstOrNull(list);
        if (listEmptyList2 == null) {
            listEmptyList2 = CollectionsKt.emptyList();
        }
        return intrinsicCrossAxisSize(listEmptyList2, i, intrinsicMeasureScope.roundToPx-0680j_4(this.mainAxisSpacing), intrinsicMeasureScope.roundToPx-0680j_4(this.crossAxisArrangementSpacing), this.maxItemsInMainAxis, this.maxLines, this.overflow);
    }

    public final int maxMainAxisIntrinsicItemSize(IntrinsicMeasurable intrinsicMeasurable, int i) {
        return isHorizontal() ? intrinsicMeasurable.maxIntrinsicWidth(i) : intrinsicMeasurable.maxIntrinsicHeight(i);
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult m862measure3p2s80s(MeasureScope measureScope, List<? extends List<? extends Measurable>> list, long j) {
        if (this.maxLines == 0 || this.maxItemsInMainAxis == 0 || list.isEmpty() || (Constraints.getMaxHeight-impl(j) == 0 && this.overflow.getType$foundation_layout() != FlowLayoutOverflow.OverflowType.Visible)) {
            return MeasureScope.layout$default(measureScope, 0, 0, (Map) null, new Function1() { // from class: androidx.compose.foundation.layout.q
                public final Object invoke(Object obj) {
                    return FlowMeasurePolicy.c((Placeable.PlacementScope) obj);
                }
            }, 4, (Object) null);
        }
        List list2 = (List) CollectionsKt.first(list);
        if (list2.isEmpty()) {
            return MeasureScope.layout$default(measureScope, 0, 0, (Map) null, new Function1() { // from class: androidx.compose.foundation.layout.s
                public final Object invoke(Object obj) {
                    return FlowMeasurePolicy.b((Placeable.PlacementScope) obj);
                }
            }, 4, (Object) null);
        }
        List list3 = (List) CollectionsKt.getOrNull(list, 1);
        Measurable measurable = list3 != null ? (Measurable) CollectionsKt.firstOrNull(list3) : null;
        List list4 = (List) CollectionsKt.getOrNull(list, 2);
        Measurable measurable2 = list4 != null ? (Measurable) CollectionsKt.firstOrNull(list4) : null;
        this.overflow.setItemCount$foundation_layout(list2.size());
        this.overflow.m845setOverflowMeasurableshBUhpc$foundation_layout(this, measurable, measurable2, j);
        return FlowLayoutKt.m839breakDownItemsdi9J0FM(measureScope, this, list2.iterator(), this.mainAxisSpacing, this.crossAxisArrangementSpacing, OrientationIndependentConstraints.m898constructorimpl(j, isHorizontal() ? LayoutOrientation.Horizontal : LayoutOrientation.Vertical), this.maxItemsInMainAxis, this.maxLines, this.overflow);
    }

    public final int minCrossAxisIntrinsicItemSize(IntrinsicMeasurable intrinsicMeasurable, int i) {
        return isHorizontal() ? intrinsicMeasurable.minIntrinsicHeight(i) : intrinsicMeasurable.minIntrinsicWidth(i);
    }

    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends List<? extends IntrinsicMeasurable>> list, int i) {
        FlowLayoutOverflowState flowLayoutOverflowState = this.overflow;
        List list2 = (List) CollectionsKt.getOrNull(list, 1);
        IntrinsicMeasurable intrinsicMeasurable = list2 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list2) : null;
        List list3 = (List) CollectionsKt.getOrNull(list, 2);
        flowLayoutOverflowState.m846setOverflowMeasurableshBUhpc$foundation_layout(intrinsicMeasurable, list3 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list3) : null, isHorizontal(), ConstraintsKt.Constraints$default(0, i, 0, 0, 13, (Object) null));
        if (isHorizontal()) {
            List<? extends IntrinsicMeasurable> listEmptyList = (List) CollectionsKt.firstOrNull(list);
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            return intrinsicCrossAxisSize(listEmptyList, i, intrinsicMeasureScope.roundToPx-0680j_4(this.mainAxisSpacing), intrinsicMeasureScope.roundToPx-0680j_4(this.crossAxisArrangementSpacing), this.maxItemsInMainAxis, this.maxLines, this.overflow);
        }
        List<? extends IntrinsicMeasurable> listEmptyList2 = (List) CollectionsKt.firstOrNull(list);
        if (listEmptyList2 == null) {
            listEmptyList2 = CollectionsKt.emptyList();
        }
        return minIntrinsicMainAxisSize(listEmptyList2, i, intrinsicMeasureScope.roundToPx-0680j_4(this.mainAxisSpacing), intrinsicMeasureScope.roundToPx-0680j_4(this.crossAxisArrangementSpacing), this.maxItemsInMainAxis, this.maxLines, this.overflow);
    }

    public final int minIntrinsicMainAxisSize(List<? extends IntrinsicMeasurable> measurables, int crossAxisAvailable, int mainAxisSpacing, int crossAxisSpacing, int maxItemsInMainAxis, int maxLines, FlowLayoutOverflowState overflow) {
        int i = maxItemsInMainAxis;
        int i2 = maxLines;
        if (measurables.isEmpty()) {
            return 0;
        }
        int size = measurables.size();
        int[] iArr = new int[size];
        int size2 = measurables.size();
        int[] iArr2 = new int[size2];
        int size3 = measurables.size();
        for (int i3 = 0; i3 < size3; i3++) {
            IntrinsicMeasurable intrinsicMeasurable = measurables.get(i3);
            int iMinMainAxisIntrinsicItemSize = minMainAxisIntrinsicItemSize(intrinsicMeasurable, crossAxisAvailable);
            iArr[i3] = iMinMainAxisIntrinsicItemSize;
            iArr2[i3] = minCrossAxisIntrinsicItemSize(intrinsicMeasurable, iMinMainAxisIntrinsicItemSize);
        }
        int i4 = Integer.MAX_VALUE;
        if (i2 != Integer.MAX_VALUE && i != Integer.MAX_VALUE) {
            i4 = i * i2;
        }
        int i5 = 1;
        int iMin = Math.min(i4 - (((i4 >= measurables.size() || !(overflow.getType$foundation_layout() == FlowLayoutOverflow.OverflowType.ExpandIndicator || overflow.getType$foundation_layout() == FlowLayoutOverflow.OverflowType.ExpandOrCollapseIndicator)) && (i4 < measurables.size() || i2 < overflow.getMinLinesToShowCollapse$foundation_layout() || overflow.getType$foundation_layout() != FlowLayoutOverflow.OverflowType.ExpandOrCollapseIndicator)) ? 0 : 1), measurables.size());
        int iSum = ArraysKt.sum(iArr) + ((measurables.size() - 1) * mainAxisSpacing);
        if (size2 == 0) {
            z0e.a();
            return 0;
        }
        int iM28getFirstimpl = iArr2[0];
        int lastIndex = ArraysKt.getLastIndex(iArr2);
        if (1 <= lastIndex) {
            int i6 = 1;
            while (true) {
                int i7 = iArr2[i6];
                if (iM28getFirstimpl < i7) {
                    iM28getFirstimpl = i7;
                }
                if (i6 == lastIndex) {
                    break;
                }
                i6++;
            }
        }
        if (size == 0) {
            z0e.a();
            return 0;
        }
        int i8 = iArr[0];
        int lastIndex2 = ArraysKt.getLastIndex(iArr);
        if (1 <= lastIndex2) {
            while (true) {
                int i9 = iArr[i5];
                if (i8 < i9) {
                    i8 = i9;
                }
                if (i5 == lastIndex2) {
                    break;
                }
                i5++;
            }
        }
        int i10 = i8;
        int i11 = iSum;
        while (i10 <= i11 && iM28getFirstimpl != crossAxisAvailable) {
            int i12 = (i10 + i11) / 2;
            long jIntrinsicCrossAxisSize = FlowLayoutKt.intrinsicCrossAxisSize(measurables, iArr, iArr2, i12, mainAxisSpacing, crossAxisSpacing, i, i2, overflow);
            iM28getFirstimpl = IntIntPair.m28getFirstimpl(jIntrinsicCrossAxisSize);
            int iM29getSecondimpl = IntIntPair.m29getSecondimpl(jIntrinsicCrossAxisSize);
            if (iM28getFirstimpl > crossAxisAvailable || iM29getSecondimpl < iMin) {
                i10 = i12 + 1;
                if (i10 > i11) {
                    return i10;
                }
            } else {
                if (iM28getFirstimpl >= crossAxisAvailable) {
                    return i12;
                }
                i11 = i12 - 1;
            }
            i = maxItemsInMainAxis;
            i2 = maxLines;
            iSum = i12;
        }
        return iSum;
    }

    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, List<? extends List<? extends IntrinsicMeasurable>> list, int i) {
        FlowLayoutOverflowState flowLayoutOverflowState = this.overflow;
        List list2 = (List) CollectionsKt.getOrNull(list, 1);
        IntrinsicMeasurable intrinsicMeasurable = list2 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list2) : null;
        List list3 = (List) CollectionsKt.getOrNull(list, 2);
        flowLayoutOverflowState.m846setOverflowMeasurableshBUhpc$foundation_layout(intrinsicMeasurable, list3 != null ? (IntrinsicMeasurable) CollectionsKt.firstOrNull(list3) : null, isHorizontal(), ConstraintsKt.Constraints$default(0, 0, 0, i, 7, (Object) null));
        if (isHorizontal()) {
            List<? extends IntrinsicMeasurable> listEmptyList = (List) CollectionsKt.firstOrNull(list);
            if (listEmptyList == null) {
                listEmptyList = CollectionsKt.emptyList();
            }
            return minIntrinsicMainAxisSize(listEmptyList, i, intrinsicMeasureScope.roundToPx-0680j_4(this.mainAxisSpacing), intrinsicMeasureScope.roundToPx-0680j_4(this.crossAxisArrangementSpacing), this.maxItemsInMainAxis, this.maxLines, this.overflow);
        }
        List<? extends IntrinsicMeasurable> listEmptyList2 = (List) CollectionsKt.firstOrNull(list);
        if (listEmptyList2 == null) {
            listEmptyList2 = CollectionsKt.emptyList();
        }
        return intrinsicCrossAxisSize(listEmptyList2, i, intrinsicMeasureScope.roundToPx-0680j_4(this.mainAxisSpacing), intrinsicMeasureScope.roundToPx-0680j_4(this.crossAxisArrangementSpacing), this.maxItemsInMainAxis, this.maxLines, this.overflow);
    }

    public final int minMainAxisIntrinsicItemSize(IntrinsicMeasurable intrinsicMeasurable, int i) {
        return isHorizontal() ? intrinsicMeasurable.minIntrinsicWidth(i) : intrinsicMeasurable.minIntrinsicHeight(i);
    }

    public String toString() {
        return "FlowMeasurePolicy(isHorizontal=" + this.isHorizontal + ", horizontalArrangement=" + this.horizontalArrangement + ", verticalArrangement=" + this.verticalArrangement + ", mainAxisSpacing=" + ((Object) Dp.toString-impl(this.mainAxisSpacing)) + ", crossAxisAlignment=" + this.crossAxisAlignment + ", crossAxisArrangementSpacing=" + ((Object) Dp.toString-impl(this.crossAxisArrangementSpacing)) + ", maxItemsInMainAxis=" + this.maxItemsInMainAxis + ", maxLines=" + this.maxLines + ", overflow=" + this.overflow + ')';
    }

    public /* synthetic */ FlowMeasurePolicy(boolean z, Arrangement.Horizontal horizontal, Arrangement.Vertical vertical, float f, CrossAxisAlignment crossAxisAlignment, float f2, int i, int i2, FlowLayoutOverflowState flowLayoutOverflowState, DefaultConstructorMarker defaultConstructorMarker) {
        this(z, horizontal, vertical, f, crossAxisAlignment, f2, i, i2, flowLayoutOverflowState);
    }
}
