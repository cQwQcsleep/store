package androidx.compose.foundation.layout;

import androidx.collection.IntIntPair;
import androidx.compose.foundation.layout.FlowLayoutOverflowState;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.unit.Constraints;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000j\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0010\b\u0081\b\u0018\u00002\u00020\u0001B!\b\u0000\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ'\u0010+\u001a\u0004\u0018\u00010\"2\u0006\u0010,\u001a\u00020&2\u0006\u0010-\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u0005H\u0000¢\u0006\u0002\b/J'\u00100\u001a\u0004\u0018\u0001012\u0006\u0010,\u001a\u00020&2\u0006\u0010-\u001a\u00020\u00052\u0006\u0010.\u001a\u00020\u0005H\u0000¢\u0006\u0002\b2J3\u00103\u001a\u0002042\b\u0010\u001b\u001a\u0004\u0018\u0001052\b\u0010\u001f\u001a\u0004\u0018\u0001052\u0006\u00106\u001a\u00020&2\u0006\u00107\u001a\u000208H\u0000¢\u0006\u0004\b9\u0010:J3\u00103\u001a\u0002042\u0006\u0010;\u001a\u00020<2\b\u0010\u001b\u001a\u0004\u0018\u00010\u001c2\b\u0010\u001f\u001a\u0004\u0018\u00010\u001c2\u0006\u00107\u001a\u000208H\u0000¢\u0006\u0004\b9\u0010=JY\u00103\u001a\u0002042\u0006\u0010;\u001a\u00020<2\u0006\u00107\u001a\u00020828\u0010$\u001a4\u0012\u0013\u0012\u00110&¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b()\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b(>\u0012\u0006\u0012\u0004\u0018\u00010\u001c0%H\u0000¢\u0006\u0004\b?\u0010@J\u000e\u0010A\u001a\u00020\u0003HÀ\u0003¢\u0006\u0002\bBJ\u000e\u0010C\u001a\u00020\u0005HÀ\u0003¢\u0006\u0002\bDJ\u000e\u0010E\u001a\u00020\u0005HÀ\u0003¢\u0006\u0002\bFJ'\u0010G\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0013\u0010H\u001a\u00020&2\b\u0010I\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010J\u001a\u00020\u0005HÖ\u0001J\t\u0010K\u001a\u00020\u0011HÖ\u0001R\u0014\u0010\u0002\u001a\u00020\u0003X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0014\u0010\u0004\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0014\u0010\u0006\u001a\u00020\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0014\u0010\u000e\u001a\u00020\u00058@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\fR\u0014\u0010\u0010\u001a\u00020\u0011X\u0080D¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u001a\u0010\u0014\u001a\u00020\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0015\u0010\f\"\u0004\b\u0016\u0010\u0017R\u001a\u0010\u0018\u001a\u00020\u0005X\u0080\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0019\u0010\f\"\u0004\b\u001a\u0010\u0017R\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001d\u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u001f\u001a\u0004\u0018\u00010\u001cX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010 \u001a\u0004\u0018\u00010\u001eX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010!\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010#\u001a\u0004\u0018\u00010\"X\u0082\u000e¢\u0006\u0002\n\u0000RB\u0010$\u001a6\u0012\u0013\u0012\u00110&¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b()\u0012\u0013\u0012\u00110\u0005¢\u0006\f\b'\u0012\b\b(\u0012\u0004\b\b(*\u0012\u0006\u0012\u0004\u0018\u00010\u001c\u0018\u00010%X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006L"}, d2 = {"Landroidx/compose/foundation/layout/FlowLayoutOverflowState;", "", "type", "Landroidx/compose/foundation/layout/FlowLayoutOverflow$OverflowType;", "minLinesToShowCollapse", "", "minCrossAxisSizeToShowCollapse", "<init>", "(Landroidx/compose/foundation/layout/FlowLayoutOverflow$OverflowType;II)V", "getType$foundation_layout", "()Landroidx/compose/foundation/layout/FlowLayoutOverflow$OverflowType;", "getMinLinesToShowCollapse$foundation_layout", "()I", "getMinCrossAxisSizeToShowCollapse$foundation_layout", "shownItemCount", "getShownItemCount$foundation_layout", "shownItemLazyErrorMessage", "", "getShownItemLazyErrorMessage$foundation_layout", "()Ljava/lang/String;", "itemShown", "getItemShown$foundation_layout", "setItemShown$foundation_layout", "(I)V", "itemCount", "getItemCount$foundation_layout", "setItemCount$foundation_layout", "seeMoreMeasurable", "Landroidx/compose/ui/layout/Measurable;", "seeMorePlaceable", "Landroidx/compose/ui/layout/Placeable;", "collapseMeasurable", "collapsePlaceable", "seeMoreSize", "Landroidx/collection/IntIntPair;", "collapseSize", "getOverflowMeasurable", "Lkotlin/Function2;", "", "Lkotlin/ParameterName;", "name", "isExpandable", "noOfItemsShown", "ellipsisSize", "hasNext", "lineIndex", "totalCrossAxisSize", "ellipsisSize-F35zm-w$foundation_layout", "ellipsisInfo", "Landroidx/compose/foundation/layout/FlowLayoutBuildingBlocks$WrapEllipsisInfo;", "ellipsisInfo$foundation_layout", "setOverflowMeasurables", "", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "isHorizontal", "constraints", "Landroidx/compose/ui/unit/Constraints;", "setOverflowMeasurables--hBUhpc$foundation_layout", "(Landroidx/compose/ui/layout/IntrinsicMeasurable;Landroidx/compose/ui/layout/IntrinsicMeasurable;ZJ)V", "measurePolicy", "Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;", "(Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;Landroidx/compose/ui/layout/Measurable;Landroidx/compose/ui/layout/Measurable;J)V", "numberOfItemsShown", "setOverflowMeasurables-VKLhPVY$foundation_layout", "(Landroidx/compose/foundation/layout/FlowLineMeasurePolicy;JLkotlin/jvm/functions/Function2;)V", "component1", "component1$foundation_layout", "component2", "component2$foundation_layout", "component3", "component3$foundation_layout", "copy", "equals", "other", "hashCode", "toString", "foundation-layout"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class FlowLayoutOverflowState {
    public static final int $stable = 8;
    private Measurable collapseMeasurable;
    private Placeable collapsePlaceable;
    private IntIntPair collapseSize;
    private Function2<? super Boolean, ? super Integer, ? extends Measurable> getOverflowMeasurable;
    private int itemCount;
    private final int minCrossAxisSizeToShowCollapse;
    private final int minLinesToShowCollapse;
    private Measurable seeMoreMeasurable;
    private Placeable seeMorePlaceable;
    private IntIntPair seeMoreSize;
    private final FlowLayoutOverflow.OverflowType type;
    private final String shownItemLazyErrorMessage = "Accessing shownItemCount before it is set. Are you calling this in the Composition phase, rather than in the draw phase? Consider our samples on how to use it during the draw phase or consider using ContextualFlowRow/ContextualFlowColumn which initializes this method in the composition phase.";
    private int itemShown = -1;

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[FlowLayoutOverflow.OverflowType.values().length];
            try {
                iArr[FlowLayoutOverflow.OverflowType.Visible.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FlowLayoutOverflow.OverflowType.Clip.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FlowLayoutOverflow.OverflowType.ExpandIndicator.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FlowLayoutOverflow.OverflowType.ExpandOrCollapseIndicator.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public FlowLayoutOverflowState(FlowLayoutOverflow.OverflowType overflowType, int i, int i2) {
        this.type = overflowType;
        this.minLinesToShowCollapse = i;
        this.minCrossAxisSizeToShowCollapse = i2;
    }

    public static /* synthetic */ FlowLayoutOverflowState copy$default(FlowLayoutOverflowState flowLayoutOverflowState, FlowLayoutOverflow.OverflowType overflowType, int i, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            overflowType = flowLayoutOverflowState.type;
        }
        if ((i3 & 2) != 0) {
            i = flowLayoutOverflowState.minLinesToShowCollapse;
        }
        if ((i3 & 4) != 0) {
            i2 = flowLayoutOverflowState.minCrossAxisSizeToShowCollapse;
        }
        return flowLayoutOverflowState.copy(overflowType, i, i2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setOverflowMeasurables__hBUhpc$lambda$2$0(FlowLayoutOverflowState flowLayoutOverflowState, FlowLineMeasurePolicy flowLineMeasurePolicy, Placeable placeable) {
        int iMainAxisSize;
        int iCrossAxisSize;
        if (placeable != null) {
            iMainAxisSize = flowLineMeasurePolicy.mainAxisSize(placeable);
            iCrossAxisSize = flowLineMeasurePolicy.crossAxisSize(placeable);
        } else {
            iMainAxisSize = 0;
            iCrossAxisSize = 0;
        }
        flowLayoutOverflowState.seeMoreSize = IntIntPair.m21boximpl(IntIntPair.m24constructorimpl(iMainAxisSize, iCrossAxisSize));
        flowLayoutOverflowState.seeMorePlaceable = placeable;
        return Unit.INSTANCE;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final Unit setOverflowMeasurables__hBUhpc$lambda$3$0(FlowLayoutOverflowState flowLayoutOverflowState, FlowLineMeasurePolicy flowLineMeasurePolicy, Placeable placeable) {
        int iMainAxisSize;
        int iCrossAxisSize;
        if (placeable != null) {
            iMainAxisSize = flowLineMeasurePolicy.mainAxisSize(placeable);
            iCrossAxisSize = flowLineMeasurePolicy.crossAxisSize(placeable);
        } else {
            iMainAxisSize = 0;
            iCrossAxisSize = 0;
        }
        flowLayoutOverflowState.collapseSize = IntIntPair.m21boximpl(IntIntPair.m24constructorimpl(iMainAxisSize, iCrossAxisSize));
        flowLayoutOverflowState.collapsePlaceable = placeable;
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: component1$foundation_layout, reason: from getter */
    public final FlowLayoutOverflow.OverflowType getType() {
        return this.type;
    }

    /* JADX INFO: renamed from: component2$foundation_layout, reason: from getter */
    public final int getMinLinesToShowCollapse() {
        return this.minLinesToShowCollapse;
    }

    /* JADX INFO: renamed from: component3$foundation_layout, reason: from getter */
    public final int getMinCrossAxisSizeToShowCollapse() {
        return this.minCrossAxisSizeToShowCollapse;
    }

    public final FlowLayoutOverflowState copy(FlowLayoutOverflow.OverflowType type, int minLinesToShowCollapse, int minCrossAxisSizeToShowCollapse) {
        return new FlowLayoutOverflowState(type, minLinesToShowCollapse, minCrossAxisSizeToShowCollapse);
    }

    /* JADX WARN: Code duplicated, block: B:23:0x0041 A[PHI: r11 r12
      0x0041: PHI (r11v10 androidx.compose.ui.layout.Measurable) = (r11v4 androidx.compose.ui.layout.Measurable), (r11v14 androidx.compose.ui.layout.Measurable) binds: [B:35:0x006b, B:20:0x003a] A[DONT_GENERATE, DONT_INLINE]
      0x0041: PHI (r12v3 androidx.collection.IntIntPair) = (r12v1 androidx.collection.IntIntPair), (r12v6 androidx.collection.IntIntPair) binds: [B:35:0x006b, B:20:0x003a] A[DONT_GENERATE, DONT_INLINE]] */
    public final FlowLayoutBuildingBlocks.WrapEllipsisInfo ellipsisInfo$foundation_layout(boolean hasNext, int lineIndex, int totalCrossAxisSize) {
        Measurable measurable;
        IntIntPair intIntPair;
        Placeable placeable;
        Measurable measurable2;
        Placeable placeable2;
        int i = WhenMappings.$EnumSwitchMapping$0[this.type.ordinal()];
        if (i == 1 || i == 2) {
            return null;
        }
        if (i != 3 && i != 4) {
            bu8.a();
            return null;
        }
        if (hasNext) {
            Function2<? super Boolean, ? super Integer, ? extends Measurable> function2 = this.getOverflowMeasurable;
            if (function2 == null || (measurable = (Measurable) function2.invoke(Boolean.TRUE, Integer.valueOf(getShownItemCount$foundation_layout()))) == null) {
                measurable = this.seeMoreMeasurable;
            }
            intIntPair = this.seeMoreSize;
            if (this.getOverflowMeasurable == null) {
                placeable = this.seeMorePlaceable;
                placeable2 = placeable;
                measurable2 = measurable;
            } else {
                measurable2 = measurable;
                placeable2 = null;
            }
        } else {
            if (lineIndex < this.minLinesToShowCollapse - 1 || totalCrossAxisSize < this.minCrossAxisSizeToShowCollapse) {
                measurable = null;
            } else {
                Function2<? super Boolean, ? super Integer, ? extends Measurable> function3 = this.getOverflowMeasurable;
                if (function3 == null || (measurable = (Measurable) function3.invoke(Boolean.FALSE, Integer.valueOf(getShownItemCount$foundation_layout()))) == null) {
                    measurable = this.collapseMeasurable;
                }
            }
            intIntPair = this.collapseSize;
            if (this.getOverflowMeasurable == null) {
                placeable = this.collapsePlaceable;
                placeable2 = placeable;
                measurable2 = measurable;
            } else {
                measurable2 = measurable;
                placeable2 = null;
            }
        }
        if (measurable2 == null) {
            return null;
        }
        intIntPair.getClass();
        return new FlowLayoutBuildingBlocks.WrapEllipsisInfo(measurable2, placeable2, intIntPair.getPackedValue(), false, 8, null);
    }

    /* JADX INFO: renamed from: ellipsisSize-F35zm-w$foundation_layout, reason: not valid java name */
    public final IntIntPair m844ellipsisSizeF35zmw$foundation_layout(boolean hasNext, int lineIndex, int totalCrossAxisSize) {
        int i = WhenMappings.$EnumSwitchMapping$0[this.type.ordinal()];
        if (i != 1 && i != 2) {
            if (i != 3) {
                if (i != 4) {
                    bu8.a();
                    return null;
                }
                if (hasNext) {
                    return this.seeMoreSize;
                }
                if (lineIndex + 1 < this.minLinesToShowCollapse || totalCrossAxisSize < this.minCrossAxisSizeToShowCollapse) {
                    return null;
                }
                return this.collapseSize;
            }
            if (hasNext) {
                return this.seeMoreSize;
            }
        }
        return null;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof FlowLayoutOverflowState)) {
            return false;
        }
        FlowLayoutOverflowState flowLayoutOverflowState = (FlowLayoutOverflowState) other;
        return this.type == flowLayoutOverflowState.type && this.minLinesToShowCollapse == flowLayoutOverflowState.minLinesToShowCollapse && this.minCrossAxisSizeToShowCollapse == flowLayoutOverflowState.minCrossAxisSizeToShowCollapse;
    }

    /* JADX INFO: renamed from: getItemCount$foundation_layout, reason: from getter */
    public final int getItemCount() {
        return this.itemCount;
    }

    /* JADX INFO: renamed from: getItemShown$foundation_layout, reason: from getter */
    public final int getItemShown() {
        return this.itemShown;
    }

    public final int getMinCrossAxisSizeToShowCollapse$foundation_layout() {
        return this.minCrossAxisSizeToShowCollapse;
    }

    public final int getMinLinesToShowCollapse$foundation_layout() {
        return this.minLinesToShowCollapse;
    }

    public final int getShownItemCount$foundation_layout() {
        int i = this.itemShown;
        if (i != -1) {
            return i;
        }
        k2d.a(this.shownItemLazyErrorMessage);
        return 0;
    }

    /* JADX INFO: renamed from: getShownItemLazyErrorMessage$foundation_layout, reason: from getter */
    public final String getShownItemLazyErrorMessage() {
        return this.shownItemLazyErrorMessage;
    }

    public final FlowLayoutOverflow.OverflowType getType$foundation_layout() {
        return this.type;
    }

    public int hashCode() {
        return (((this.type.hashCode() * 31) + Integer.hashCode(this.minLinesToShowCollapse)) * 31) + Integer.hashCode(this.minCrossAxisSizeToShowCollapse);
    }

    public final void setItemCount$foundation_layout(int i) {
        this.itemCount = i;
    }

    public final void setItemShown$foundation_layout(int i) {
        this.itemShown = i;
    }

    /* JADX INFO: renamed from: setOverflowMeasurables--hBUhpc$foundation_layout, reason: not valid java name */
    public final void m846setOverflowMeasurableshBUhpc$foundation_layout(IntrinsicMeasurable seeMoreMeasurable, IntrinsicMeasurable collapseMeasurable, boolean isHorizontal, long constraints) {
        long jM898constructorimpl = OrientationIndependentConstraints.m898constructorimpl(constraints, isHorizontal ? LayoutOrientation.Horizontal : LayoutOrientation.Vertical);
        if (seeMoreMeasurable != null) {
            int iMainAxisMin = FlowLayoutKt.mainAxisMin(seeMoreMeasurable, isHorizontal, Constraints.getMaxHeight-impl(jM898constructorimpl));
            this.seeMoreSize = IntIntPair.m21boximpl(IntIntPair.m24constructorimpl(iMainAxisMin, FlowLayoutKt.crossAxisMin(seeMoreMeasurable, isHorizontal, iMainAxisMin)));
            this.seeMoreMeasurable = seeMoreMeasurable instanceof Measurable ? (Measurable) seeMoreMeasurable : null;
            this.seeMorePlaceable = null;
        }
        if (collapseMeasurable != null) {
            int iMainAxisMin2 = FlowLayoutKt.mainAxisMin(collapseMeasurable, isHorizontal, Constraints.getMaxHeight-impl(jM898constructorimpl));
            this.collapseSize = IntIntPair.m21boximpl(IntIntPair.m24constructorimpl(iMainAxisMin2, FlowLayoutKt.crossAxisMin(collapseMeasurable, isHorizontal, iMainAxisMin2)));
            this.collapseMeasurable = collapseMeasurable instanceof Measurable ? (Measurable) collapseMeasurable : null;
            this.collapsePlaceable = null;
        }
    }

    /* JADX INFO: renamed from: setOverflowMeasurables-VKLhPVY$foundation_layout, reason: not valid java name */
    public final void m847setOverflowMeasurablesVKLhPVY$foundation_layout(FlowLineMeasurePolicy measurePolicy, long constraints, Function2<? super Boolean, ? super Integer, ? extends Measurable> getOverflowMeasurable) {
        this.itemShown = 0;
        this.getOverflowMeasurable = getOverflowMeasurable;
        m845setOverflowMeasurableshBUhpc$foundation_layout(measurePolicy, (Measurable) getOverflowMeasurable.invoke(Boolean.TRUE, 0), (Measurable) getOverflowMeasurable.invoke(Boolean.FALSE, 0), constraints);
    }

    public String toString() {
        return "FlowLayoutOverflowState(type=" + this.type + ", minLinesToShowCollapse=" + this.minLinesToShowCollapse + ", minCrossAxisSizeToShowCollapse=" + this.minCrossAxisSizeToShowCollapse + ')';
    }

    /* JADX INFO: renamed from: setOverflowMeasurables--hBUhpc$foundation_layout, reason: not valid java name */
    public final void m845setOverflowMeasurableshBUhpc$foundation_layout(final FlowLineMeasurePolicy measurePolicy, Measurable seeMoreMeasurable, Measurable collapseMeasurable, long constraints) {
        LayoutOrientation layoutOrientation = measurePolicy.isHorizontal() ? LayoutOrientation.Horizontal : LayoutOrientation.Vertical;
        long jM911toBoxConstraintsOenEA2s = OrientationIndependentConstraints.m911toBoxConstraintsOenEA2s(OrientationIndependentConstraints.m900copyyUG9Ft0$default(OrientationIndependentConstraints.m898constructorimpl(constraints, layoutOrientation), 0, 0, 0, 0, 10, null), layoutOrientation);
        if (seeMoreMeasurable != null) {
            FlowLayoutKt.m840measureAndCacherqJ1uqs(seeMoreMeasurable, measurePolicy, jM911toBoxConstraintsOenEA2s, new Function1() { // from class: jj5
                public final Object invoke(Object obj) {
                    return FlowLayoutOverflowState.setOverflowMeasurables__hBUhpc$lambda$2$0(this.b, measurePolicy, (Placeable) obj);
                }
            });
            this.seeMoreMeasurable = seeMoreMeasurable;
        }
        if (collapseMeasurable != null) {
            FlowLayoutKt.m840measureAndCacherqJ1uqs(collapseMeasurable, measurePolicy, jM911toBoxConstraintsOenEA2s, new Function1() { // from class: kj5
                public final Object invoke(Object obj) {
                    return FlowLayoutOverflowState.setOverflowMeasurables__hBUhpc$lambda$3$0(this.b, measurePolicy, (Placeable) obj);
                }
            });
            this.collapseMeasurable = collapseMeasurable;
        }
    }
}
