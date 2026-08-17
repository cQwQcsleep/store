package androidx.compose.animation;

import androidx.compose.runtime.MutableFloatState;
import androidx.compose.runtime.PrimitiveSnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.DrawModifierNodeKt;
import androidx.compose.ui.node.LayoutModifierNode;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000p\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u0004:\u0001>B%\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\f\u0010\rJ#\u0010*\u001a\u00020+*\u00020,2\u0006\u0010-\u001a\u00020.2\u0006\u0010/\u001a\u000200H\u0016¢\u0006\u0004\b1\u00102J\f\u00103\u001a\u000204*\u000205H\u0016J\b\u0010<\u001a\u000204H\u0016J\b\u0010=\u001a\u000204H\u0016R\u001a\u0010\u0005\u001a\u00020\u0006X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011R \u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R+\u0010\n\u001a\u00020\u000b2\u0006\u0010\u0016\u001a\u00020\u000b8F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u001b\u0010\u001c\u001a\u0004\b\u0017\u0010\u0018\"\u0004\b\u0019\u0010\u001aR\u0013\u0010\u001d\u001a\u0004\u0018\u00010\u001e8F¢\u0006\u0006\u001a\u0004\b\u001f\u0010 R\u001e\u0010\"\u001a\u00020\t2\u0006\u0010!\u001a\u00020\t@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b#\u0010$R \u0010&\u001a\u00020%2\u0006\u0010!\u001a\u00020%@BX\u0082\u000e¢\u0006\n\n\u0002\u0010)\"\u0004\b'\u0010(R\u0013\u00106\u001a\u0004\u0018\u0001078F¢\u0006\u0006\u001a\u0004\b8\u00109R\u0014\u0010:\u001a\b\u0018\u00010;R\u00020\u0000X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006?"}, d2 = {"Landroidx/compose/animation/RenderInTransitionOverlayNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/modifier/ModifierLocalModifierNode;", "sharedScope", "Landroidx/compose/animation/SharedTransitionScopeImpl;", "renderInOverlay", "Lkotlin/Function0;", "", "zIndexInOverlay", "", "<init>", "(Landroidx/compose/animation/SharedTransitionScopeImpl;Lkotlin/jvm/functions/Function0;F)V", "getSharedScope", "()Landroidx/compose/animation/SharedTransitionScopeImpl;", "setSharedScope", "(Landroidx/compose/animation/SharedTransitionScopeImpl;)V", "getRenderInOverlay", "()Lkotlin/jvm/functions/Function0;", "setRenderInOverlay", "(Lkotlin/jvm/functions/Function0;)V", "<set-?>", "getZIndexInOverlay", "()F", "setZIndexInOverlay", "(F)V", "zIndexInOverlay$delegate", "Landroidx/compose/runtime/MutableFloatState;", "parentState", "Landroidx/compose/animation/SharedElementEntry;", "getParentState", "()Landroidx/compose/animation/SharedElementEntry;", "value", "enabled", "setEnabled", "(Z)V", "Landroidx/compose/ui/geometry/Offset;", "positionInOverlay", "setPositionInOverlay-k-4lQ0M", "(J)V", "J", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "draw", "", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "layer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "getLayer", "()Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "layerWithRenderer", "Landroidx/compose/animation/RenderInTransitionOverlayNode$LayerWithRenderer;", "onAttach", "onDetach", "LayerWithRenderer", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class RenderInTransitionOverlayNode extends Modifier.Node implements LayoutModifierNode, DrawModifierNode, ModifierLocalModifierNode {
    public static final int $stable = 8;
    private boolean enabled;
    private LayerWithRenderer layerWithRenderer;
    private long positionInOverlay = Offset.Companion.getZero-F1C5BW0();
    private Function0<Boolean> renderInOverlay;
    private SharedTransitionScopeImpl sharedScope;

    /* JADX INFO: renamed from: zIndexInOverlay$delegate, reason: from kotlin metadata */
    private final MutableFloatState zIndexInOverlay;

    @Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0082\u0004\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0016\u0010\b\u001a\u0004\u0018\u00010\t8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0014\u0010\f\u001a\u00020\r8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0014"}, d2 = {"Landroidx/compose/animation/RenderInTransitionOverlayNode$LayerWithRenderer;", "Landroidx/compose/animation/LayerRenderer;", "layer", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "<init>", "(Landroidx/compose/animation/RenderInTransitionOverlayNode;Landroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "getLayer", "()Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "parentState", "Landroidx/compose/animation/SharedElementEntry;", "getParentState", "()Landroidx/compose/animation/SharedElementEntry;", "zIndex", "", "getZIndex", "()F", "drawInOverlay", "", "drawScope", "Landroidx/compose/ui/graphics/drawscope/DrawScope;", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
    public final class LayerWithRenderer implements LayerRenderer {
        private final GraphicsLayer layer;

        public LayerWithRenderer(GraphicsLayer graphicsLayer) {
            this.layer = graphicsLayer;
        }

        @Override // androidx.compose.animation.LayerRenderer
        public void drawInOverlay(DrawScope drawScope) {
            if (RenderInTransitionOverlayNode.this.enabled) {
                RenderInTransitionOverlayNode renderInTransitionOverlayNode = RenderInTransitionOverlayNode.this;
                float fIntBitsToFloat = Float.intBitsToFloat((int) (renderInTransitionOverlayNode.positionInOverlay >> 32));
                float fIntBitsToFloat2 = Float.intBitsToFloat((int) (renderInTransitionOverlayNode.positionInOverlay & 4294967295L));
                drawScope.getDrawContext().getTransform().translate(fIntBitsToFloat, fIntBitsToFloat2);
                try {
                    GraphicsLayerKt.drawLayer(drawScope, this.layer);
                } finally {
                    drawScope.getDrawContext().getTransform().translate(-fIntBitsToFloat, -fIntBitsToFloat2);
                }
            }
        }

        public final GraphicsLayer getLayer() {
            return this.layer;
        }

        @Override // androidx.compose.animation.LayerRenderer
        public SharedElementEntry getParentState() {
            return RenderInTransitionOverlayNode.this.getParentState();
        }

        @Override // androidx.compose.animation.LayerRenderer
        public float getZIndex() {
            return RenderInTransitionOverlayNode.this.getZIndexInOverlay();
        }
    }

    public RenderInTransitionOverlayNode(SharedTransitionScopeImpl sharedTransitionScopeImpl, Function0<Boolean> function0, float f) {
        this.sharedScope = sharedTransitionScopeImpl;
        this.renderInOverlay = function0;
        this.zIndexInOverlay = PrimitiveSnapshotStateKt.mutableFloatStateOf(f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void setEnabled(boolean z) {
        if (z != this.enabled) {
            Function0<Unit> invalidateOverlay = this.sharedScope.getInvalidateOverlay();
            if (invalidateOverlay != null) {
                invalidateOverlay.invoke();
            }
            DrawModifierNodeKt.invalidateDraw(this);
            this.enabled = z;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: setPositionInOverlay-k-4lQ0M, reason: not valid java name */
    public final void m130setPositionInOverlayk4lQ0M(long j) {
        if (Offset.equals-impl0(j, this.positionInOverlay)) {
            return;
        }
        Function0<Unit> invalidateOverlay = this.sharedScope.getInvalidateOverlay();
        if (invalidateOverlay != null) {
            invalidateOverlay.invoke();
        }
        DrawModifierNodeKt.invalidateDraw(this);
        this.positionInOverlay = j;
    }

    public void draw(final ContentDrawScope contentDrawScope) {
        GraphicsLayer layer = getLayer();
        if (layer == null) {
            w01.a("Error: layer never initialized");
            return;
        }
        DrawScope.record-JVtK1S4$default(contentDrawScope, layer, 0L, new Function1<DrawScope, Unit>() { // from class: androidx.compose.animation.RenderInTransitionOverlayNode.draw.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((DrawScope) obj);
                return Unit.INSTANCE;
            }

            public final void invoke(DrawScope drawScope) {
                contentDrawScope.drawContent();
            }
        }, 1, (Object) null);
        if (this.enabled) {
            return;
        }
        GraphicsLayerKt.drawLayer(contentDrawScope, layer);
    }

    public final GraphicsLayer getLayer() {
        LayerWithRenderer layerWithRenderer = this.layerWithRenderer;
        if (layerWithRenderer != null) {
            return layerWithRenderer.getLayer();
        }
        return null;
    }

    public final SharedElementEntry getParentState() {
        return (SharedElementEntry) getCurrent(SharedContentNodeKt.getModifierLocalSharedElementInternalState());
    }

    public final Function0<Boolean> getRenderInOverlay() {
        return this.renderInOverlay;
    }

    public final SharedTransitionScopeImpl getSharedScope() {
        return this.sharedScope;
    }

    public final float getZIndexInOverlay() {
        return this.zIndexInOverlay.getFloatValue();
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult m131measure3p2s80s(final MeasureScope measureScope, Measurable measurable, long j) {
        final Placeable placeable = measurable.measure-BRTryo0(j);
        return MeasureScope.layout$default(measureScope, placeable.getWidth(), placeable.getHeight(), (Map) null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.RenderInTransitionOverlayNode$measure$1$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(Placeable.PlacementScope placementScope) {
                if (!measureScope.isLookingAhead()) {
                    if (!((Boolean) this.getRenderInOverlay().invoke()).booleanValue()) {
                        this.setEnabled(false);
                    } else if (placementScope.getCoordinates() != null) {
                        RenderInTransitionOverlayNode renderInTransitionOverlayNode = this;
                        renderInTransitionOverlayNode.setEnabled(true);
                        renderInTransitionOverlayNode.m130setPositionInOverlayk4lQ0M(renderInTransitionOverlayNode.getSharedScope().getRoot$animation().localPositionOf-R5De75A(DelegatableNodeKt.requireLayoutCoordinates(renderInTransitionOverlayNode), Offset.Companion.getZero-F1C5BW0()));
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
        LayerWithRenderer layerWithRenderer = new LayerWithRenderer(DelegatableNodeKt.requireGraphicsContext(this).createGraphicsLayer());
        this.sharedScope.onLayerRendererCreated$animation(layerWithRenderer);
        this.layerWithRenderer = layerWithRenderer;
    }

    public void onDetach() {
        LayerWithRenderer layerWithRenderer = this.layerWithRenderer;
        if (layerWithRenderer != null) {
            this.sharedScope.onLayerRendererRemoved$animation(layerWithRenderer);
            DelegatableNodeKt.requireGraphicsContext(this).releaseGraphicsLayer(layerWithRenderer.getLayer());
        }
    }

    public final void setRenderInOverlay(Function0<Boolean> function0) {
        this.renderInOverlay = function0;
    }

    public final void setSharedScope(SharedTransitionScopeImpl sharedTransitionScopeImpl) {
        this.sharedScope = sharedTransitionScopeImpl;
    }

    public final void setZIndexInOverlay(float f) {
        this.zIndexInOverlay.setFloatValue(f);
    }
}
