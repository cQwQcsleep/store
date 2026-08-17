package androidx.compose.animation;

import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004B\u000f\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\b\u0010\t\u001a\u00020\nH\u0016J\b\u0010\u000b\u001a\u00020\nH\u0016J#\u0010\u0010\u001a\u00020\u0011*\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0016¢\u0006\u0004\b\u0017\u0010\u0018J\b\u0010\u0019\u001a\u00020\nH\u0016J\f\u0010\u001a\u001a\u00020\n*\u00020\u001bH\u0016R$\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u0006@FX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\r\u0010\u000e\"\u0004\b\u000f\u0010\b¨\u0006\u001c"}, d2 = {"Landroidx/compose/animation/SharedTransitionScopeRootModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "sharedScope", "Landroidx/compose/animation/SharedTransitionScopeImpl;", "<init>", "(Landroidx/compose/animation/SharedTransitionScopeImpl;)V", "onAttach", "", "onDetach", "newScope", "getSharedScope", "()Landroidx/compose/animation/SharedTransitionScopeImpl;", "setSharedScope", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "onObservedReadsChanged", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
final class SharedTransitionScopeRootModifierNode extends Modifier.Node implements LayoutModifierNode, ObserverModifierNode, DrawModifierNode {
    private SharedTransitionScopeImpl sharedScope;

    public SharedTransitionScopeRootModifierNode(SharedTransitionScopeImpl sharedTransitionScopeImpl) {
        this.sharedScope = sharedTransitionScopeImpl;
    }

    public void draw(ContentDrawScope contentDrawScope) {
        contentDrawScope.drawContent();
        this.sharedScope.drawInOverlay$animation(contentDrawScope);
    }

    public final SharedTransitionScopeImpl getSharedScope() {
        return this.sharedScope;
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult m159measure3p2s80s(final MeasureScope measureScope, Measurable measurable, long j) {
        final Placeable placeable = measurable.measure-BRTryo0(j);
        return MeasureScope.layout$default(measureScope, placeable.getWidth(), placeable.getHeight(), (Map) null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.SharedTransitionScopeRootModifierNode$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(Placeable.PlacementScope placementScope) {
                LayoutCoordinates coordinates = placementScope.getCoordinates();
                if (coordinates != null) {
                    boolean zIsLookingAhead = measureScope.isLookingAhead();
                    SharedTransitionScopeRootModifierNode sharedTransitionScopeRootModifierNode = this;
                    if (zIsLookingAhead) {
                        sharedTransitionScopeRootModifierNode.getSharedScope().setLookaheadRoot$animation(coordinates);
                    } else {
                        sharedTransitionScopeRootModifierNode.getSharedScope().setRoot$animation(coordinates);
                    }
                }
                Placeable.PlacementScope.place$default(placementScope, placeable, 0, 0, 0.0f, 4, (Object) null);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Placeable.PlacementScope) obj);
                return Unit.INSTANCE;
            }
        }, 4, (Object) null);
    }

    public void onAttach() {
        super.onAttach();
        ObserverModifierNodeKt.observeReads(this, this.sharedScope.getObserveAnimatingBlock$animation());
        this.sharedScope.setInvalidateOverlay(new Function0<Unit>() { // from class: androidx.compose.animation.SharedTransitionScopeRootModifierNode.onAttach.1
            {
                super(0);
            }

            public /* bridge */ /* synthetic */ Object invoke() {
                m160invoke();
                return Unit.INSTANCE;
            }

            /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
            public final void m160invoke() {
                DrawModifierNodeKt.invalidateDraw(SharedTransitionScopeRootModifierNode.this);
            }
        });
    }

    public void onDetach() {
        this.sharedScope.setInvalidateOverlay(null);
    }

    public void onObservedReadsChanged() {
        this.sharedScope.updateTransitionActiveness$animation();
        ObserverModifierNodeKt.observeReads(this, this.sharedScope.getObserveAnimatingBlock$animation());
    }

    public final void setSharedScope(SharedTransitionScopeImpl sharedTransitionScopeImpl) {
        if (!Intrinsics.areEqual(sharedTransitionScopeImpl, this.sharedScope)) {
            ObserverModifierNodeKt.observeReads(this, sharedTransitionScopeImpl.getObserveAnimatingBlock$animation());
        }
        this.sharedScope = sharedTransitionScopeImpl;
    }
}
