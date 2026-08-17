package androidx.compose.animation;

import androidx.compose.animation.SharedBoundsNode;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.animation.core.VisibilityThresholdsKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.geometry.Offset;
import androidx.compose.ui.geometry.Rect;
import androidx.compose.ui.geometry.RectKt;
import androidx.compose.ui.graphics.drawscope.ContentDrawScope;
import androidx.compose.ui.graphics.drawscope.DrawScope;
import androidx.compose.ui.graphics.layer.GraphicsLayer;
import androidx.compose.ui.graphics.layer.GraphicsLayerKt;
import androidx.compose.ui.layout.ApproachLayoutModifierNode;
import androidx.compose.ui.layout.ApproachMeasureScope;
import androidx.compose.ui.layout.LayoutCoordinates;
import androidx.compose.ui.layout.LayoutCoordinatesKt;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.modifier.ModifierLocalMap;
import androidx.compose.ui.modifier.ModifierLocalModifierNode;
import androidx.compose.ui.modifier.ModifierLocalModifierNodeKt;
import androidx.compose.ui.node.DelegatableNodeKt;
import androidx.compose.ui.node.DrawModifierNode;
import androidx.compose.ui.node.ObserverModifierNode;
import androidx.compose.ui.node.ObserverModifierNodeKt;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntOffsetKt;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000¬\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0001\u0018\u00002\u00020\u00012\u00020\u00022\u00020\u00032\u00020\u00042\u00020\u00052\u00020\u0006B\u000f\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\f2\u0006\u0010\u0011\u001a\u00020\fH\u0016J\b\u0010\u001f\u001a\u00020\u0013H\u0002J\b\u00100\u001a\u000201H\u0002J\b\u00102\u001a\u000201H\u0016J\b\u00103\u001a\u000201H\u0016J\b\u00104\u001a\u000201H\u0016J#\u00105\u001a\u000206*\u0002072\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;H\u0016¢\u0006\u0004\b<\u0010=J\u001c\u0010>\u001a\u000201*\u00020?2\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020\fH\u0002J)\u0010C\u001a\u000201*\u00020?2\u0006\u0010@\u001a\u00020A2\u0006\u0010D\u001a\u00020E2\u0006\u0010B\u001a\u00020\fH\u0000¢\u0006\u0002\bFJ\u0014\u0010G\u001a\u000206*\u0002072\u0006\u0010@\u001a\u00020AH\u0002J\u0017\u0010H\u001a\u00020\u00172\u0006\u0010I\u001a\u00020JH\u0016¢\u0006\u0004\bK\u0010LJ#\u0010M\u001a\u000206*\u00020N2\u0006\u00108\u001a\u0002092\u0006\u0010:\u001a\u00020;H\u0016¢\u0006\u0004\bO\u0010PJ\f\u0010Q\u001a\u000201*\u00020RH\u0016J\b\u0010S\u001a\u000201H\u0016R\u0010\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\r\u001a\u0004\u0018\u00010\f8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0014\u0010\u0012\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u000e\u0010\u0016\u001a\u00020\u0017X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010\u0018\u001a\u00020\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0015R$\u0010\u001b\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\b@@X\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u001c\u0010\u001d\"\u0004\b\u001e\u0010\nR\u0014\u0010 \u001a\u00020!8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010#R\"\u0010%\u001a\u0004\u0018\u00010$2\b\u0010\u001a\u001a\u0004\u0018\u00010$@BX\u0082\u000e¢\u0006\b\n\u0000\"\u0004\b&\u0010'R\u0014\u0010(\u001a\u00020)8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b*\u0010+R\u0014\u0010,\u001a\u00020-X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010/¨\u0006T"}, d2 = {"Landroidx/compose/animation/SharedBoundsNode;", "Landroidx/compose/ui/layout/ApproachLayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "Landroidx/compose/ui/node/DrawModifierNode;", "Landroidx/compose/ui/modifier/ModifierLocalModifierNode;", "Landroidx/compose/ui/node/ObserverModifierNode;", "Landroidx/compose/animation/BoundsProvider;", "state", "Landroidx/compose/animation/SharedElementEntry;", "<init>", "(Landroidx/compose/animation/SharedElementEntry;)V", "boundsBeforeDetached", "Landroidx/compose/ui/geometry/Rect;", "lastBoundsInSharedTransitionScope", "getLastBoundsInSharedTransitionScope", "()Landroidx/compose/ui/geometry/Rect;", "calculateAlternativeTargetBounds", "targetBoundsBeforeDisposed", "approachCoordinates", "Landroidx/compose/ui/layout/LayoutCoordinates;", "getApproachCoordinates", "()Landroidx/compose/ui/layout/LayoutCoordinates;", "isPlaced", "", "rootCoords", "getRootCoords", "value", "sharedElementEntry", "getSharedElementEntry", "()Landroidx/compose/animation/SharedElementEntry;", "setSharedElementEntry$animation", "requireLookaheadLayoutCoordinates", "boundsAnimation", "Landroidx/compose/animation/BoundsAnimation;", "getBoundsAnimation", "()Landroidx/compose/animation/BoundsAnimation;", "Landroidx/compose/ui/graphics/layer/GraphicsLayer;", "layer", "setLayer", "(Landroidx/compose/ui/graphics/layer/GraphicsLayer;)V", "sharedElement", "Landroidx/compose/animation/SharedElement;", "getSharedElement", "()Landroidx/compose/animation/SharedElement;", "providedValues", "Landroidx/compose/ui/modifier/ModifierLocalMap;", "getProvidedValues", "()Landroidx/compose/ui/modifier/ModifierLocalMap;", "setup", "", "onAttach", "onDetach", "onReset", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "Landroidx/compose/ui/unit/Constraints;", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "approachPlaceMatchBeyondTransition", "Landroidx/compose/ui/layout/Placeable$PlacementScope;", "placeable", "Landroidx/compose/ui/layout/Placeable;", "currentBounds", "approachPlaceMatchInTransition", "targetData", "Landroidx/compose/animation/TargetData;", "approachPlaceMatchInTransition$animation", "approachPlace", "isMeasurementApproachInProgress", "lookaheadSize", "Landroidx/compose/ui/unit/IntSize;", "isMeasurementApproachInProgress-ozmzZPI", "(J)Z", "approachMeasure", "Landroidx/compose/ui/layout/ApproachMeasureScope;", "approachMeasure-3p2s80s", "(Landroidx/compose/ui/layout/ApproachMeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "draw", "Landroidx/compose/ui/graphics/drawscope/ContentDrawScope;", "onObservedReadsChanged", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SharedBoundsNode extends Modifier.Node implements ApproachLayoutModifierNode, DrawModifierNode, ModifierLocalModifierNode, ObserverModifierNode, BoundsProvider {
    public static final int $stable = 8;
    private Rect boundsBeforeDetached;
    private boolean isPlaced;
    private GraphicsLayer layer;
    private final ModifierLocalMap providedValues;
    private SharedElementEntry sharedElementEntry;

    public SharedBoundsNode(SharedElementEntry sharedElementEntry) {
        this.sharedElementEntry = sharedElementEntry;
        this.layer = sharedElementEntry.getLayer();
        this.providedValues = ModifierLocalModifierNodeKt.modifierLocalMapOf(TuplesKt.to(SharedContentNodeKt.getModifierLocalSharedElementInternalState(), sharedElementEntry));
    }

    public static FiniteAnimationSpec a(Rect rect, Rect rect2) {
        return AnimationSpecKt.spring$default(0.0f, 0.0f, VisibilityThresholdsKt.getVisibilityThreshold(Rect.Companion), 3, null);
    }

    private final MeasureResult approachPlace(MeasureScope measureScope, final Placeable placeable) {
        long jMo146calculateSizeJyjRU_E;
        if (getSharedElement().getState$animation().getMatchIsOrHasBeenConfigured()) {
            jMo146calculateSizeJyjRU_E = this.sharedElementEntry.getPlaceholderSize().mo146calculateSizeJyjRU_E(requireLookaheadLayoutCoordinates().getSize-YbymL2g(), IntSize.constructor-impl((((long) placeable.getHeight()) & 4294967295L) | (((long) placeable.getWidth()) << 32)));
        } else {
            jMo146calculateSizeJyjRU_E = IntSize.constructor-impl((((long) placeable.getWidth()) << 32) | (((long) placeable.getHeight()) & 4294967295L));
        }
        return MeasureScope.layout$default(measureScope, (int) (jMo146calculateSizeJyjRU_E >> 32), (int) (jMo146calculateSizeJyjRU_E & 4294967295L), (Map) null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.SharedBoundsNode.approachPlace.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(Placeable.PlacementScope placementScope) {
                SharedBoundsNode.this.isPlaced = true;
                SharedBoundsNode.this.boundsBeforeDetached = null;
                SharedTransitionStateMachine.State state$animation = SharedBoundsNode.this.getSharedElement().getState$animation();
                if (!SharedBoundsNode.this.getSharedElementEntry().isEnabled()) {
                    Placeable.PlacementScope.place$default(placementScope, placeable, 0, 0, 0.0f, 4, (Object) null);
                    return;
                }
                if (!state$animation.getMatchIsOrHasBeenConfigured()) {
                    Placeable.PlacementScope.place$default(placementScope, placeable, 0, 0, 0.0f, 4, (Object) null);
                    return;
                }
                TargetData targetData = state$animation.getTargetData();
                if (targetData == null) {
                    dt1.a("Match State is configured, but target data is null. State = ", state$animation);
                    return;
                }
                Rect currentBounds = state$animation.getCurrentBounds();
                if (currentBounds == null) {
                    dt1.a("Match State is configured, but current bounds is null. State = ", state$animation);
                    return;
                }
                boolean zIsTransitionActive = SharedBoundsNode.this.getSharedElement().getScope().isTransitionActive();
                SharedBoundsNode sharedBoundsNode = SharedBoundsNode.this;
                if (zIsTransitionActive) {
                    sharedBoundsNode.approachPlaceMatchInTransition$animation(placementScope, placeable, targetData, currentBounds);
                } else {
                    sharedBoundsNode.approachPlaceMatchBeyondTransition(placementScope, placeable, currentBounds);
                }
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Placeable.PlacementScope) obj);
                return Unit.INSTANCE;
            }
        }, 4, (Object) null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void approachPlaceMatchBeyondTransition(Placeable.PlacementScope placementScope, Placeable placeable, Rect rect) {
        long j;
        if (getBoundsAnimation().getTarget()) {
            Placeable.PlacementScope.place$default(placementScope, placeable, 0, 0, 0.0f, 4, (Object) null);
            return;
        }
        LayoutCoordinates coordinates = placementScope.getCoordinates();
        if (coordinates != null) {
            j = IntOffsetKt.round-k-4lQ0M(Offset.minus-MK-Hz9U(rect.getTopLeft-F1C5BW0(), getRootCoords().localPositionOf-R5De75A(coordinates, Offset.Companion.getZero-F1C5BW0())));
        } else {
            j = IntOffset.Companion.getZero-nOcc-ac();
        }
        Placeable.PlacementScope.place$default(placementScope, placeable, IntOffset.getX-impl(j), IntOffset.getY-impl(j), 0.0f, 4, (Object) null);
    }

    private final LayoutCoordinates getApproachCoordinates() {
        return DelegatableNodeKt.requireLayoutCoordinates(this);
    }

    private final BoundsAnimation getBoundsAnimation() {
        return this.sharedElementEntry.getBoundsAnimation();
    }

    private final LayoutCoordinates getRootCoords() {
        return getSharedElement().getScope().getRoot$animation();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final SharedElement getSharedElement() {
        return this.sharedElementEntry.getSharedElement();
    }

    private final LayoutCoordinates requireLookaheadLayoutCoordinates() {
        return this.sharedElementEntry.getSharedElement().getScope().toLookaheadCoordinates(DelegatableNodeKt.requireLayoutCoordinates(this));
    }

    private final void setLayer(GraphicsLayer graphicsLayer) {
        if (graphicsLayer == null) {
            GraphicsLayer graphicsLayer2 = this.layer;
            if (graphicsLayer2 != null) {
                DelegatableNodeKt.requireGraphicsContext(this).releaseGraphicsLayer(graphicsLayer2);
            }
        } else {
            this.sharedElementEntry.setLayer(graphicsLayer);
        }
        this.layer = graphicsLayer;
    }

    private final void setup() {
        provide(SharedContentNodeKt.getModifierLocalSharedElementInternalState(), this.sharedElementEntry);
        this.sharedElementEntry.setParentState((SharedElementEntry) getCurrent(SharedContentNodeKt.getModifierLocalSharedElementInternalState()));
        setLayer(DelegatableNodeKt.requireGraphicsContext(this).createGraphicsLayer());
        this.isPlaced = false;
        this.sharedElementEntry.setBoundsProvider(this);
    }

    /* JADX INFO: renamed from: approachMeasure-3p2s80s, reason: not valid java name */
    public MeasureResult m137approachMeasure3p2s80s(ApproachMeasureScope approachMeasureScope, Measurable measurable, long j) {
        Rect value = getBoundsAnimation().getValue();
        if (value == null) {
            value = getSharedElement().tryInitializingCurrentBounds();
        }
        if (value != null) {
            long j2 = IntSizeKt.roundToIntSize-uvyYCjk(value.getSize-NH-jbRc());
            int i = (int) (j2 >> 32);
            int i2 = (int) (j2 & 4294967295L);
            if (i == Integer.MAX_VALUE || i2 == Integer.MAX_VALUE) {
                StringBuilder sb = new StringBuilder("Error: Infinite width/height is invalid. animated bounds: ");
                sb.append(getBoundsAnimation().getValue());
                ywd.a(sb, ", current bounds: ", getSharedElement().getState$animation().getCurrentBounds());
                return null;
            }
            j = Constraints.Companion.fixed-JhjzzOo(RangesKt.coerceAtLeast(i, 0), RangesKt.coerceAtLeast(i2, 0));
        }
        return approachPlace(approachMeasureScope, measurable.measure-BRTryo0(j));
    }

    public final void approachPlaceMatchInTransition$animation(Placeable.PlacementScope placementScope, Placeable placeable, TargetData targetData, Rect rect) {
        long j;
        LayoutCoordinates coordinates = placementScope.getCoordinates();
        if (coordinates == null) {
            Placeable.PlacementScope.place$default(placementScope, placeable, 0, 0, 0.0f, 4, (Object) null);
            return;
        }
        boolean activeMatchFound = getSharedElement().getState$animation().getActiveMatchFound();
        long j2 = getRootCoords().localPositionOf-R5De75A(coordinates, Offset.Companion.getZero-F1C5BW0());
        if (activeMatchFound) {
            BoundsAnimation.animate$default(getBoundsAnimation(), rect, SharedTransitionStateMachineKt.getTargetBounds(targetData), null, 4, null);
        } else {
            getBoundsAnimation().animate(rect, SharedTransitionStateMachineKt.getTargetBounds(targetData), new BoundsTransform() { // from class: v8d
                @Override // androidx.compose.animation.BoundsTransform
                public final FiniteAnimationSpec createAnimationSpec(Rect rect2, Rect rect3) {
                    return SharedBoundsNode.a(rect2, rect3);
                }
            });
        }
        Rect value = getBoundsAnimation().getValue();
        Offset offset = value != null ? Offset.box-impl(SharedTransitionStateMachineKt.calculateOffsetFromDirectManipulation(targetData, value)) : null;
        if (getBoundsAnimation().getTarget() || !activeMatchFound) {
            long j3 = offset != null ? offset.unbox-impl() : j2;
            getSharedElement().getState$animation().updateBounds(offset == null ? RectKt.Rect-tz77jQw(j2, IntSizeKt.toSize-ozmzZPI(coordinates.getSize-YbymL2g())) : RectKt.Rect-tz77jQw(offset.unbox-impl(), value.getSize-NH-jbRc()));
            j = j3;
        } else {
            j = offset != null ? offset.unbox-impl() : rect.getTopLeft-F1C5BW0();
        }
        long j4 = Offset.minus-MK-Hz9U(j, j2);
        Placeable.PlacementScope.place$default(placementScope, placeable, Math.round(Float.intBitsToFloat((int) (j4 >> 32))), Math.round(Float.intBitsToFloat((int) (j4 & 4294967295L))), 0.0f, 4, (Object) null);
    }

    @Override // androidx.compose.animation.BoundsProvider
    public Rect calculateAlternativeTargetBounds(Rect targetBoundsBeforeDisposed) {
        return this.sharedElementEntry.calculateTargetBounds(targetBoundsBeforeDisposed);
    }

    public void draw(final ContentDrawScope contentDrawScope) {
        final Rect currentBounds = getSharedElement().getState$animation().getCurrentBounds();
        SharedElementEntry sharedElementEntry = this.sharedElementEntry;
        sharedElementEntry.setClipPathInOverlay$animation((!sharedElementEntry.getShouldRenderInOverlay$animation() || currentBounds == null) ? null : this.sharedElementEntry.getOverlayClip().getClipPath(this.sharedElementEntry.getUserState(), currentBounds, contentDrawScope.getLayoutDirection(), DelegatableNodeKt.requireDensity(this)));
        GraphicsLayer layer = this.sharedElementEntry.getLayer();
        if (layer != null) {
            DrawScope.record-JVtK1S4$default(contentDrawScope, layer, 0L, new Function1<DrawScope, Unit>() { // from class: androidx.compose.animation.SharedBoundsNode.draw.1
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
            if (this.sharedElementEntry.getShouldRenderInPlace()) {
                GraphicsLayerKt.drawLayer(contentDrawScope, layer);
                return;
            }
            return;
        }
        StringBuilder sb = new StringBuilder("Error: Layer is null when accessed for shared bounds/element : ");
        sb.append(getSharedElement().getKey());
        boolean target = this.sharedElementEntry.getBoundsAnimation().getTarget();
        boolean zIsAttached = isAttached();
        sb.append(",target: ");
        sb.append(target);
        sb.append(", is attached: ");
        sb.append(zIsAttached);
        throw new IllegalArgumentException(sb.toString().toString());
    }

    @Override // androidx.compose.animation.BoundsProvider
    public Rect getLastBoundsInSharedTransitionScope() {
        if (isAttached()) {
            return !this.isPlaced ? this.boundsBeforeDetached : RectKt.Rect-tz77jQw(LayoutCoordinates.localPositionOf-S_NoaFU$default(getRootCoords(), getApproachCoordinates(), 0L, false, 6, (Object) null), IntSizeKt.toSize-ozmzZPI(getApproachCoordinates().getSize-YbymL2g()));
        }
        return null;
    }

    public ModifierLocalMap getProvidedValues() {
        return this.providedValues;
    }

    public final SharedElementEntry getSharedElementEntry() {
        return this.sharedElementEntry;
    }

    /* JADX INFO: renamed from: isMeasurementApproachInProgress-ozmzZPI, reason: not valid java name */
    public boolean m138isMeasurementApproachInProgressozmzZPI(long lookaheadSize) {
        return this.sharedElementEntry.isEnabled() && getSharedElement().getFoundMatch() && getSharedElement().getScope().isTransitionActive();
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult m139measure3p2s80s(MeasureScope measureScope, Measurable measurable, long j) {
        final Placeable placeable = measurable.measure-BRTryo0(j);
        return MeasureScope.layout$default(measureScope, placeable.getWidth(), placeable.getHeight(), (Map) null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.SharedBoundsNode$measure$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(Placeable.PlacementScope placementScope) {
                Placeable.PlacementScope.place$default(placementScope, placeable, 0, 0, 0.0f, 4, (Object) null);
                this.getSharedElement().onLookaheadPlaced(placementScope, this.getSharedElementEntry());
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Placeable.PlacementScope) obj);
                return Unit.INSTANCE;
            }
        }, 4, (Object) null);
    }

    public void onAttach() {
        super.onAttach();
        ObserverModifierNodeKt.observeReads(this, getSharedElement().getObservingVisibilityChange$animation());
        setup();
        this.sharedElementEntry.setAttached(true);
    }

    public void onDetach() {
        super.onDetach();
        LayoutCoordinates nullableRoot = getSharedElement().getScope().getNullableRoot();
        if (nullableRoot != null) {
            this.boundsBeforeDetached = (nullableRoot.isAttached() && this.isPlaced) ? RectKt.Rect-tz77jQw(Offset.minus-MK-Hz9U(LayoutCoordinatesKt.positionInRoot(getApproachCoordinates()), LayoutCoordinatesKt.positionInRoot(nullableRoot)), IntSizeKt.toSize-ozmzZPI(getApproachCoordinates().getSize-YbymL2g())) : null;
        }
        setLayer(null);
        this.sharedElementEntry.setParentState(null);
        this.sharedElementEntry.setBoundsProvider(null);
        this.sharedElementEntry.setAttached(false);
        this.isPlaced = false;
    }

    public void onObservedReadsChanged() {
        getSharedElement().updateMatch$animation();
        ObserverModifierNodeKt.observeReads(this, getSharedElement().getObservingVisibilityChange$animation());
    }

    public void onReset() {
        super.onReset();
        this.boundsBeforeDetached = null;
        GraphicsLayer graphicsLayer = this.layer;
        if (graphicsLayer != null) {
            DelegatableNodeKt.requireGraphicsContext(this).releaseGraphicsLayer(graphicsLayer);
        }
        setLayer(DelegatableNodeKt.requireGraphicsContext(this).createGraphicsLayer());
    }

    public final void setSharedElementEntry$animation(SharedElementEntry sharedElementEntry) {
        if (Intrinsics.areEqual(sharedElementEntry, this.sharedElementEntry)) {
            return;
        }
        this.sharedElementEntry.setAttached(false);
        this.sharedElementEntry = sharedElementEntry;
        sharedElementEntry.setAttached(isAttached());
        if (isAttached()) {
            setup();
        }
    }
}
