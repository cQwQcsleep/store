package androidx.compose.material3.internal;

import androidx.compose.foundation.gestures.Orientation;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.math.MathKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0002\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u00022\u00020\u0003Bg\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005\u0012H\u0010\u0006\u001aD\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f\u0012\u0004\u0012\u00028\u00000\u000e0\u0007\u0012\u0006\u0010\u0010\u001a\u00020\u0011¢\u0006\u0004\b\u0012\u0010\u0013J\b\u0010\"\u001a\u00020#H\u0016J#\u0010&\u001a\u00020'*\u00020(2\u0006\u0010)\u001a\u00020*2\u0006\u0010\r\u001a\u00020\fH\u0016¢\u0006\u0004\b+\u0010,R \u0010\u0004\u001a\b\u0012\u0004\u0012\u00028\u00000\u0005X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0014\u0010\u0015\"\u0004\b\u0016\u0010\u0017R\\\u0010\u0006\u001aD\u0012\u0013\u0012\u00110\b¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\u000b\u0012\u0013\u0012\u00110\f¢\u0006\f\b\t\u0012\b\b\n\u0012\u0004\b\b(\r\u0012\u0016\u0012\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000f\u0012\u0004\u0012\u00028\u00000\u000e0\u0007X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0018\u0010\u0019\"\u0004\b\u001a\u0010\u001bR\u001a\u0010\u0010\u001a\u00020\u0011X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\u001fR\u000e\u0010 \u001a\u00020!X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010$\u001a\u00020!8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%¨\u0006-"}, d2 = {"Landroidx/compose/material3/internal/DraggableAnchorsNodeV2;", "T", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/LayoutModifierNode;", "state", "Landroidx/compose/foundation/gestures/AnchoredDraggableState;", "anchors", "Lkotlin/Function2;", "Landroidx/compose/ui/unit/IntSize;", "Lkotlin/ParameterName;", "name", "size", "Landroidx/compose/ui/unit/Constraints;", "constraints", "Lkotlin/Pair;", "Landroidx/compose/foundation/gestures/DraggableAnchors;", "orientation", "Landroidx/compose/foundation/gestures/Orientation;", "<init>", "(Landroidx/compose/foundation/gestures/AnchoredDraggableState;Lkotlin/jvm/functions/Function2;Landroidx/compose/foundation/gestures/Orientation;)V", "getState", "()Landroidx/compose/foundation/gestures/AnchoredDraggableState;", "setState", "(Landroidx/compose/foundation/gestures/AnchoredDraggableState;)V", "getAnchors", "()Lkotlin/jvm/functions/Function2;", "setAnchors", "(Lkotlin/jvm/functions/Function2;)V", "getOrientation", "()Landroidx/compose/foundation/gestures/Orientation;", "setOrientation", "(Landroidx/compose/foundation/gestures/Orientation;)V", "didLookahead", "", "onDetach", "", "isReverseDirection", "()Z", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "material3"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class DraggableAnchorsNodeV2<T> extends Modifier.Node implements LayoutModifierNode {
    private Function2<? super IntSize, ? super Constraints, ? extends Pair<? extends androidx.compose.foundation.gestures.DraggableAnchors<T>, ? extends T>> anchors;
    private boolean didLookahead;
    private Orientation orientation;
    private androidx.compose.foundation.gestures.AnchoredDraggableState<T> state;

    public DraggableAnchorsNodeV2(androidx.compose.foundation.gestures.AnchoredDraggableState<T> anchoredDraggableState, Function2<? super IntSize, ? super Constraints, ? extends Pair<? extends androidx.compose.foundation.gestures.DraggableAnchors<T>, ? extends T>> function2, Orientation orientation) {
        this.state = anchoredDraggableState;
        this.anchors = function2;
        this.orientation = orientation;
    }

    public static Unit a(Placeable placeable, float f, float f2, Placeable.PlacementScope placementScope) {
        Placeable.PlacementScope.place$default(placementScope, placeable, MathKt.roundToInt(f), MathKt.roundToInt(f2), 0.0f, 4, null);
        return Unit.INSTANCE;
    }

    public static Unit b(MeasureScope measureScope, DraggableAnchorsNodeV2 draggableAnchorsNodeV2, final Placeable placeable, Placeable.PlacementScope placementScope) {
        final float fPositionOf = measureScope.isLookingAhead() ? draggableAnchorsNodeV2.state.getAnchors().positionOf(draggableAnchorsNodeV2.state.getTargetValue()) : draggableAnchorsNodeV2.state.requireOffset();
        float f = draggableAnchorsNodeV2.isReverseDirection() ? -1.0f : 1.0f;
        Orientation orientation = draggableAnchorsNodeV2.orientation;
        final float f2 = orientation == Orientation.Horizontal ? f * fPositionOf : 0.0f;
        if (orientation != Orientation.Vertical) {
            fPositionOf = 0.0f;
        }
        placementScope.withMotionFrameOfReferencePlacement(new Function1() { // from class: androidx.compose.material3.internal.n
            public final Object invoke(Object obj) {
                return DraggableAnchorsNodeV2.a(placeable, f2, fPositionOf, (Placeable.PlacementScope) obj);
            }
        });
        return Unit.INSTANCE;
    }

    private final boolean isReverseDirection() {
        return DelegatableNodeKt.requireLayoutDirection(this) == LayoutDirection.Rtl && this.orientation == Orientation.Horizontal;
    }

    public final Function2<IntSize, Constraints, Pair<androidx.compose.foundation.gestures.DraggableAnchors<T>, T>> getAnchors() {
        return this.anchors;
    }

    public final Orientation getOrientation() {
        return this.orientation;
    }

    public final androidx.compose.foundation.gestures.AnchoredDraggableState<T> getState() {
        return this.state;
    }

    @Override // androidx.compose.ui.node.LayoutModifierNode
    /* JADX INFO: renamed from: measure-3p2s80s */
    public MeasureResult mo628measure3p2s80s(final MeasureScope measureScope, Measurable measurable, long j) {
        final Placeable placeableMo4605measureBRTryo0 = measurable.mo4605measureBRTryo0(j);
        if (!measureScope.isLookingAhead() || !this.didLookahead) {
            Pair pair = (Pair) this.anchors.invoke(IntSize.m6185boximpl(IntSize.m6188constructorimpl((((long) placeableMo4605measureBRTryo0.getHeight()) & 4294967295L) | (((long) placeableMo4605measureBRTryo0.getWidth()) << 32))), Constraints.m5962boximpl(j));
            this.state.updateAnchors((androidx.compose.foundation.gestures.DraggableAnchors) pair.getFirst(), pair.getSecond());
        }
        this.didLookahead = measureScope.isLookingAhead() || this.didLookahead;
        return MeasureScope.layout$default(measureScope, placeableMo4605measureBRTryo0.getWidth(), placeableMo4605measureBRTryo0.getHeight(), null, new Function1() { // from class: androidx.compose.material3.internal.m
            public final Object invoke(Object obj) {
                return DraggableAnchorsNodeV2.b(measureScope, this, placeableMo4605measureBRTryo0, (Placeable.PlacementScope) obj);
            }
        }, 4, null);
    }

    @Override // androidx.compose.ui.Modifier.Node
    public void onDetach() {
        this.didLookahead = false;
    }

    public final void setAnchors(Function2<? super IntSize, ? super Constraints, ? extends Pair<? extends androidx.compose.foundation.gestures.DraggableAnchors<T>, ? extends T>> function2) {
        this.anchors = function2;
    }

    public final void setOrientation(Orientation orientation) {
        this.orientation = orientation;
    }

    public final void setState(androidx.compose.foundation.gestures.AnchoredDraggableState<T> anchoredDraggableState) {
        this.state = anchoredDraggableState;
    }
}
