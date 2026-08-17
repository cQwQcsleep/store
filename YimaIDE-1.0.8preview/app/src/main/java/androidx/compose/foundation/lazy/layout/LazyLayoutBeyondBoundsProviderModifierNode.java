package androidx.compose.foundation.lazy.layout;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsProviderModifierNode;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.BeyondBoundsLayout;
import androidx.compose.ui.layout.BeyondBoundsLayoutProviderModifierNode;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.LayoutModifierNodeKt;
import androidx.compose.ui.unit.LayoutDirection;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000n\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0001\u0018\u0000 42\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u00014B'\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f¢\u0006\u0004\b\r\u0010\u000eJ#\u0010\u0012\u001a\u00020\u0013*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u0018H\u0016¢\u0006\u0004\b\u0019\u0010\u001aJ:\u0010\u001b\u001a\u0004\u0018\u0001H\u001c\"\u0004\b\u0000\u0010\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0019\u0010\u001f\u001a\u0015\u0012\u0004\u0012\u00020!\u0012\u0006\u0012\u0004\u0018\u0001H\u001c0 ¢\u0006\u0002\b\"H\u0016¢\u0006\u0004\b#\u0010$J\u0013\u0010%\u001a\u00020\n*\u00020\u001eH\u0002¢\u0006\u0004\b&\u0010'J\u001f\u0010(\u001a\u00020)2\u0006\u0010*\u001a\u00020)2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002¢\u0006\u0004\b+\u0010,J\u001b\u0010-\u001a\u00020\n*\u00020)2\u0006\u0010\u001d\u001a\u00020\u001eH\u0002¢\u0006\u0004\b.\u0010/J\u0013\u00100\u001a\u00020\n*\u00020\u001eH\u0002¢\u0006\u0004\b1\u0010'J&\u00102\u001a\u0002032\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u000f\u001a\u00020\u00048VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u00065"}, d2 = {"Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsProviderModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/layout/BeyondBoundsLayoutProviderModifierNode;", "Landroidx/compose/ui/layout/BeyondBoundsLayout;", "state", "Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsState;", "beyondBoundsInfo", "Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsInfo;", "reverseLayout", "", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "<init>", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsState;Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsInfo;ZLandroidx/compose/foundation/gestures/Orientation;)V", "beyondBoundsLayout", "getBeyondBoundsLayout", "()Landroidx/compose/ui/layout/BeyondBoundsLayout;", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "layout", "T", "direction", "Landroidx/compose/ui/layout/BeyondBoundsLayout$LayoutDirection;", "block", "Lkotlin/Function1;", "Landroidx/compose/ui/layout/BeyondBoundsLayout$BeyondBoundsScope;", "Lkotlin/ExtensionFunctionType;", "layout-o7g1Pn8", "(ILkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "isForward", "isForward-4vf7U8o", "(I)Z", "addNextInterval", "Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsInfo$Interval;", "currentInterval", "addNextInterval-FR3nfPY", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsInfo$Interval;I)Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsInfo$Interval;", "hasMoreContent", "hasMoreContent-FR3nfPY", "(Landroidx/compose/foundation/lazy/layout/LazyLayoutBeyondBoundsInfo$Interval;I)Z", "isOppositeToOrientation", "isOppositeToOrientation-4vf7U8o", "update", "", "Companion", "foundation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class LazyLayoutBeyondBoundsProviderModifierNode extends Modifier.Node implements LayoutModifierNode, BeyondBoundsLayoutProviderModifierNode, BeyondBoundsLayout {
    private LazyLayoutBeyondBoundsInfo beyondBoundsInfo;
    private Orientation orientation;
    private boolean reverseLayout;
    private LazyLayoutBeyondBoundsState state;
    public static final int $stable = 8;
    private static final LazyLayoutBeyondBoundsProviderModifierNode$Companion$emptyBeyondBoundsScope$1 emptyBeyondBoundsScope = new BeyondBoundsLayout.BeyondBoundsScope() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsProviderModifierNode$Companion$emptyBeyondBoundsScope$1
        private final boolean hasMoreContent;

        public boolean getHasMoreContent() {
            return this.hasMoreContent;
        }
    };

    @Metadata(k = 3, mv = {2, 0, 0}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[LayoutDirection.values().length];
            try {
                iArr[LayoutDirection.Ltr.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[LayoutDirection.Rtl.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public LazyLayoutBeyondBoundsProviderModifierNode(LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsState, LazyLayoutBeyondBoundsInfo lazyLayoutBeyondBoundsInfo, boolean z, Orientation orientation) {
        this.state = lazyLayoutBeyondBoundsState;
        this.beyondBoundsInfo = lazyLayoutBeyondBoundsInfo;
        this.reverseLayout = z;
        this.orientation = orientation;
    }

    public static Unit a(Placeable placeable, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.place$default(placementScope, placeable, 0, 0, 0.0f, 4, (Object) null);
        return Unit.INSTANCE;
    }

    /* JADX INFO: renamed from: addNextInterval-FR3nfPY, reason: not valid java name */
    private final LazyLayoutBeyondBoundsInfo.Interval m1104addNextIntervalFR3nfPY(LazyLayoutBeyondBoundsInfo.Interval currentInterval, int direction) {
        int start = currentInterval.getStart();
        int end = currentInterval.getEnd();
        if (m1106isForward4vf7U8o(direction)) {
            end++;
        } else {
            start--;
        }
        return this.beyondBoundsInfo.addInterval(start, end);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: hasMoreContent-FR3nfPY, reason: not valid java name */
    public final boolean m1105hasMoreContentFR3nfPY(LazyLayoutBeyondBoundsInfo.Interval interval, int i) {
        if (m1107isOppositeToOrientation4vf7U8o(i)) {
            return false;
        }
        if (m1106isForward4vf7U8o(i)) {
            return interval.getEnd() < this.state.getItemCount() - 1;
        }
        return interval.getStart() > 0;
    }

    /* JADX INFO: renamed from: isForward-4vf7U8o, reason: not valid java name */
    private final boolean m1106isForward4vf7U8o(int i) {
        BeyondBoundsLayout.LayoutDirection.Companion companion = BeyondBoundsLayout.LayoutDirection.Companion;
        if (BeyondBoundsLayout.LayoutDirection.equals-impl0(i, companion.getBefore-hoxUOeE())) {
            return false;
        }
        if (BeyondBoundsLayout.LayoutDirection.equals-impl0(i, companion.getAfter-hoxUOeE())) {
            return true;
        }
        if (BeyondBoundsLayout.LayoutDirection.equals-impl0(i, companion.getAbove-hoxUOeE())) {
            return this.reverseLayout;
        }
        if (BeyondBoundsLayout.LayoutDirection.equals-impl0(i, companion.getBelow-hoxUOeE())) {
            return !this.reverseLayout;
        }
        if (BeyondBoundsLayout.LayoutDirection.equals-impl0(i, companion.getLeft-hoxUOeE())) {
            int i2 = WhenMappings.$EnumSwitchMapping$0[DelegatableNodeKt.requireLayoutDirection(this).ordinal()];
            if (i2 == 1) {
                return this.reverseLayout;
            }
            if (i2 == 2) {
                return !this.reverseLayout;
            }
            bu8.a();
            return false;
        }
        if (!BeyondBoundsLayout.LayoutDirection.equals-impl0(i, companion.getRight-hoxUOeE())) {
            LazyLayoutBeyondBoundsModifierLocalKt.unsupportedDirection();
            wq6.a();
            return false;
        }
        int i3 = WhenMappings.$EnumSwitchMapping$0[DelegatableNodeKt.requireLayoutDirection(this).ordinal()];
        if (i3 == 1) {
            return !this.reverseLayout;
        }
        if (i3 == 2) {
            return this.reverseLayout;
        }
        bu8.a();
        return false;
    }

    /* JADX INFO: renamed from: isOppositeToOrientation-4vf7U8o, reason: not valid java name */
    private final boolean m1107isOppositeToOrientation4vf7U8o(int i) {
        BeyondBoundsLayout.LayoutDirection.Companion companion = BeyondBoundsLayout.LayoutDirection.Companion;
        if (BeyondBoundsLayout.LayoutDirection.equals-impl0(i, companion.getAbove-hoxUOeE()) || BeyondBoundsLayout.LayoutDirection.equals-impl0(i, companion.getBelow-hoxUOeE())) {
            return this.orientation == Orientation.Horizontal;
        }
        if (BeyondBoundsLayout.LayoutDirection.equals-impl0(i, companion.getLeft-hoxUOeE()) || BeyondBoundsLayout.LayoutDirection.equals-impl0(i, companion.getRight-hoxUOeE())) {
            return this.orientation == Orientation.Vertical;
        }
        if (BeyondBoundsLayout.LayoutDirection.equals-impl0(i, companion.getBefore-hoxUOeE()) || BeyondBoundsLayout.LayoutDirection.equals-impl0(i, companion.getAfter-hoxUOeE())) {
            return false;
        }
        LazyLayoutBeyondBoundsModifierLocalKt.unsupportedDirection();
        wq6.a();
        return false;
    }

    public BeyondBoundsLayout getBeyondBoundsLayout() {
        return this;
    }

    /* JADX INFO: renamed from: layout-o7g1Pn8, reason: not valid java name */
    public <T> T m1108layouto7g1Pn8(final int direction, Function1<? super BeyondBoundsLayout.BeyondBoundsScope, ? extends T> block) {
        if (this.state.getItemCount() <= 0 || !this.state.getHasVisibleItems() || !isAttached()) {
            return (T) block.invoke(emptyBeyondBoundsScope);
        }
        boolean zM1106isForward4vf7U8o = m1106isForward4vf7U8o(direction);
        LazyLayoutBeyondBoundsState lazyLayoutBeyondBoundsState = this.state;
        int lastPlacedIndex = zM1106isForward4vf7U8o ? lazyLayoutBeyondBoundsState.getLastPlacedIndex() : lazyLayoutBeyondBoundsState.getFirstPlacedIndex();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        objectRef.element = this.beyondBoundsInfo.addInterval(lastPlacedIndex, lastPlacedIndex);
        int iCoerceAtMost = RangesKt.coerceAtMost(this.state.itemsPerViewport() * 2, this.state.getItemCount());
        T t = null;
        int i = 0;
        while (t == null && m1105hasMoreContentFR3nfPY((LazyLayoutBeyondBoundsInfo.Interval) objectRef.element, direction) && i < iCoerceAtMost) {
            LazyLayoutBeyondBoundsInfo.Interval intervalM1104addNextIntervalFR3nfPY = m1104addNextIntervalFR3nfPY((LazyLayoutBeyondBoundsInfo.Interval) objectRef.element, direction);
            this.beyondBoundsInfo.removeInterval((LazyLayoutBeyondBoundsInfo.Interval) objectRef.element);
            objectRef.element = intervalM1104addNextIntervalFR3nfPY;
            i++;
            LayoutModifierNodeKt.remeasureSync(this);
            t = (T) block.invoke(new BeyondBoundsLayout.BeyondBoundsScope() { // from class: androidx.compose.foundation.lazy.layout.LazyLayoutBeyondBoundsProviderModifierNode$layout$2
                public boolean getHasMoreContent() {
                    return this.this$0.m1105hasMoreContentFR3nfPY((LazyLayoutBeyondBoundsInfo.Interval) objectRef.element, direction);
                }
            });
        }
        this.beyondBoundsInfo.removeInterval((LazyLayoutBeyondBoundsInfo.Interval) objectRef.element);
        LayoutModifierNodeKt.remeasureSync(this);
        return t;
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult m1109measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        final Placeable placeable = measurable.measure-BRTryo0(j);
        return MeasureScope.layout$default(measureScope, placeable.getWidth(), placeable.getHeight(), (Map) null, new Function1() { // from class: cu8
            public final Object invoke(Object obj) {
                return LazyLayoutBeyondBoundsProviderModifierNode.a(placeable, (Placeable.PlacementScope) obj);
            }
        }, 4, (Object) null);
    }

    public final void update(LazyLayoutBeyondBoundsState state, LazyLayoutBeyondBoundsInfo beyondBoundsInfo, boolean reverseLayout, Orientation orientation) {
        this.state = state;
        this.beyondBoundsInfo = beyondBoundsInfo;
        this.reverseLayout = reverseLayout;
        this.orientation = orientation;
    }
}
