package androidx.compose.animation;

import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotMutationPolicy;
import androidx.compose.runtime.SnapshotStateKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.layout.ContentScale;
import androidx.compose.ui.layout.IntrinsicMeasurable;
import androidx.compose.ui.layout.IntrinsicMeasureScope;
import androidx.compose.ui.layout.Measurable;
import androidx.compose.ui.layout.MeasureResult;
import androidx.compose.ui.layout.MeasureScope;
import androidx.compose.ui.layout.Placeable;
import androidx.compose.ui.layout.ScaleFactor;
import androidx.compose.ui.node.LayoutModifierNode;
import androidx.compose.ui.unit.Constraints;
import androidx.compose.ui.unit.ConstraintsKt;
import androidx.compose.ui.unit.IntOffset;
import androidx.compose.ui.unit.IntSize;
import androidx.compose.ui.unit.IntSizeKt;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.math.MathKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\b\u0001\u0018\u00002\u00020\u00012\u00020\u0002B\u001f\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006¢\u0006\u0004\b\b\u0010\tJ#\u0010\u001a\u001a\u00020\u001b*\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0016H\u0016¢\u0006\u0004\b \u0010!J\u001c\u0010\"\u001a\u00020#*\u00020$2\u0006\u0010\u001d\u001a\u00020%2\u0006\u0010&\u001a\u00020#H\u0016J\u001c\u0010'\u001a\u00020#*\u00020$2\u0006\u0010\u001d\u001a\u00020%2\u0006\u0010&\u001a\u00020#H\u0016J\u001c\u0010(\u001a\u00020#*\u00020$2\u0006\u0010\u001d\u001a\u00020%2\u0006\u0010)\u001a\u00020#H\u0016J\u001c\u0010*\u001a\u00020#*\u00020$2\u0006\u0010\u001d\u001a\u00020%2\u0006\u0010)\u001a\u00020#H\u0016R/\u0010\u0003\u001a\u0004\u0018\u00010\u00042\b\u0010\n\u001a\u0004\u0018\u00010\u00048F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u000b\u0010\f\"\u0004\b\r\u0010\u000eR7\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u00062\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00070\u00068F@FX\u0086\u008e\u0002¢\u0006\u0012\n\u0004\b\u0014\u0010\u0010\u001a\u0004\b\u0005\u0010\u0011\"\u0004\b\u0012\u0010\u0013R\u0010\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0017\u001a\u00020\u0018X\u0082\u000e¢\u0006\u0004\n\u0002\u0010\u0019¨\u0006+"}, d2 = {"Landroidx/compose/animation/SkipToLookaheadSizeNode;", "Landroidx/compose/ui/node/LayoutModifierNode;", "Landroidx/compose/ui/Modifier$Node;", "scaleToBounds", "Landroidx/compose/animation/ScaleToBoundsImpl;", "isEnabled", "Lkotlin/Function0;", "", "<init>", "(Landroidx/compose/animation/ScaleToBoundsImpl;Lkotlin/jvm/functions/Function0;)V", "<set-?>", "getScaleToBounds", "()Landroidx/compose/animation/ScaleToBoundsImpl;", "setScaleToBounds", "(Landroidx/compose/animation/ScaleToBoundsImpl;)V", "scaleToBounds$delegate", "Landroidx/compose/runtime/MutableState;", "()Lkotlin/jvm/functions/Function0;", "setEnabled", "(Lkotlin/jvm/functions/Function0;)V", "isEnabled$delegate", "lookaheadConstraints", "Landroidx/compose/ui/unit/Constraints;", "lookaheadSize", "Landroidx/compose/ui/unit/IntSize;", "J", "measure", "Landroidx/compose/ui/layout/MeasureResult;", "Landroidx/compose/ui/layout/MeasureScope;", "measurable", "Landroidx/compose/ui/layout/Measurable;", "constraints", "measure-3p2s80s", "(Landroidx/compose/ui/layout/MeasureScope;Landroidx/compose/ui/layout/Measurable;J)Landroidx/compose/ui/layout/MeasureResult;", "maxIntrinsicWidth", "", "Landroidx/compose/ui/layout/IntrinsicMeasureScope;", "Landroidx/compose/ui/layout/IntrinsicMeasurable;", "height", "minIntrinsicWidth", "maxIntrinsicHeight", "width", "minIntrinsicHeight", "animation"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class SkipToLookaheadSizeNode extends Modifier.Node implements LayoutModifierNode {
    public static final int $stable = 8;

    /* JADX INFO: renamed from: isEnabled$delegate, reason: from kotlin metadata */
    private final MutableState isEnabled;
    private Constraints lookaheadConstraints;
    private long lookaheadSize = AnimationModifierKt.getInvalidSize();

    /* JADX INFO: renamed from: scaleToBounds$delegate, reason: from kotlin metadata */
    private final MutableState scaleToBounds;

    public SkipToLookaheadSizeNode(ScaleToBoundsImpl scaleToBoundsImpl, Function0<Boolean> function0) {
        this.scaleToBounds = SnapshotStateKt.mutableStateOf$default(scaleToBoundsImpl, (SnapshotMutationPolicy) null, 2, (Object) null);
        this.isEnabled = SnapshotStateKt.mutableStateOf$default(function0, (SnapshotMutationPolicy) null, 2, (Object) null);
    }

    public final ScaleToBoundsImpl getScaleToBounds() {
        return (ScaleToBoundsImpl) this.scaleToBounds.getValue();
    }

    public final Function0<Boolean> isEnabled() {
        return (Function0) this.isEnabled.getValue();
    }

    public int maxIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return (intrinsicMeasureScope.isLookingAhead() || !AnimationModifierKt.m83isValidozmzZPI(this.lookaheadSize)) ? intrinsicMeasurable.maxIntrinsicHeight(i) : (int) (this.lookaheadSize & 4294967295L);
    }

    public int maxIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return (intrinsicMeasureScope.isLookingAhead() || !AnimationModifierKt.m83isValidozmzZPI(this.lookaheadSize)) ? intrinsicMeasurable.maxIntrinsicWidth(i) : (int) (this.lookaheadSize >> 32);
    }

    /* JADX INFO: renamed from: measure-3p2s80s, reason: not valid java name */
    public MeasureResult m179measure3p2s80s(final MeasureScope measureScope, Measurable measurable, long j) {
        Placeable placeable;
        if (measureScope.isLookingAhead()) {
            this.lookaheadConstraints = Constraints.box-impl(j);
        }
        if (!((Boolean) isEnabled().invoke()).booleanValue()) {
            final Placeable placeable2 = measurable.measure-BRTryo0(j);
            return MeasureScope.layout$default(measureScope, placeable2.getWidth(), placeable2.getHeight(), (Map) null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.SkipToLookaheadSizeNode$measure$1$1
                /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                {
                    super(1);
                }

                public final void invoke(Placeable.PlacementScope placementScope) {
                    Placeable.PlacementScope.place$default(placementScope, placeable2, 0, 0, 0.0f, 4, (Object) null);
                }

                public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                    invoke((Placeable.PlacementScope) obj);
                    return Unit.INSTANCE;
                }
            }, 4, (Object) null);
        }
        if (measureScope.isLookingAhead()) {
            placeable = measurable.measure-BRTryo0(j);
            this.lookaheadSize = IntSize.constructor-impl((((long) placeable.getWidth()) << 32) | (((long) placeable.getHeight()) & 4294967295L));
        } else {
            Constraints constraints = this.lookaheadConstraints;
            constraints.getClass();
            placeable = measurable.measure-BRTryo0(constraints.unbox-impl());
        }
        final long j2 = ConstraintsKt.constrain-4WqzIAM(j, this.lookaheadSize);
        final Placeable placeable3 = placeable;
        return MeasureScope.layout$default(measureScope, (int) (j2 >> 32), (int) (4294967295L & j2), (Map) null, new Function1<Placeable.PlacementScope, Unit>() { // from class: androidx.compose.animation.SkipToLookaheadSizeNode$measure$2
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(1);
            }

            public final void invoke(Placeable.PlacementScope placementScope) {
                final long j3;
                ScaleToBoundsImpl scaleToBounds = this.this$0.getScaleToBounds();
                if (scaleToBounds == null) {
                    Placeable.PlacementScope.place$default(placementScope, placeable3, 0, 0, 0.0f, 4, (Object) null);
                    return;
                }
                ContentScale contentScale = scaleToBounds.getContentScale();
                if (((int) (this.this$0.lookaheadSize >> 32)) == 0 || ((int) (this.this$0.lookaheadSize & 4294967295L)) == 0) {
                    j3 = ScaleFactor.constructor-impl((((long) Float.floatToRawIntBits(1.0f)) << 32) | (((long) Float.floatToRawIntBits(1.0f)) & 4294967295L));
                } else {
                    j3 = contentScale.computeScaleFactor-H7hwNQA(IntSizeKt.toSize-ozmzZPI(this.this$0.lookaheadSize), IntSizeKt.toSize-ozmzZPI(j2));
                }
                long j4 = scaleToBounds.getAlignment().align-KFBX0sM(IntSize.constructor-impl((((long) MathKt.roundToInt(((int) (this.this$0.lookaheadSize & 4294967295L)) * Float.intBitsToFloat((int) (j3 & 4294967295L)))) & 4294967295L) | (((long) MathKt.roundToInt(((int) (this.this$0.lookaheadSize >> 32)) * Float.intBitsToFloat((int) (j3 >> 32)))) << 32)), j2, measureScope.getLayoutDirection());
                Placeable.PlacementScope.placeWithLayer$default(placementScope, placeable3, IntOffset.getX-impl(j4), IntOffset.getY-impl(j4), 0.0f, new Function1<GraphicsLayerScope, Unit>() { // from class: androidx.compose.animation.SkipToLookaheadSizeNode$measure$2.1
                    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
                    {
                        super(1);
                    }

                    public final void invoke(GraphicsLayerScope graphicsLayerScope) {
                        graphicsLayerScope.setScaleX(Float.intBitsToFloat((int) (j3 >> 32)));
                        graphicsLayerScope.setScaleY(Float.intBitsToFloat((int) (j3 & 4294967295L)));
                        graphicsLayerScope.setTransformOrigin-__ExYCQ(TransformOriginKt.TransformOrigin(0.0f, 0.0f));
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                        invoke((GraphicsLayerScope) obj);
                        return Unit.INSTANCE;
                    }
                }, 4, (Object) null);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj) {
                invoke((Placeable.PlacementScope) obj);
                return Unit.INSTANCE;
            }
        }, 4, (Object) null);
    }

    public int minIntrinsicHeight(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return (intrinsicMeasureScope.isLookingAhead() || !AnimationModifierKt.m83isValidozmzZPI(this.lookaheadSize)) ? intrinsicMeasurable.minIntrinsicHeight(i) : (int) (this.lookaheadSize & 4294967295L);
    }

    public int minIntrinsicWidth(IntrinsicMeasureScope intrinsicMeasureScope, IntrinsicMeasurable intrinsicMeasurable, int i) {
        return (intrinsicMeasureScope.isLookingAhead() || !AnimationModifierKt.m83isValidozmzZPI(this.lookaheadSize)) ? intrinsicMeasurable.minIntrinsicWidth(i) : (int) (this.lookaheadSize >> 32);
    }

    public final void setEnabled(Function0<Boolean> function0) {
        this.isEnabled.setValue(function0);
    }

    public final void setScaleToBounds(ScaleToBoundsImpl scaleToBoundsImpl) {
        this.scaleToBounds.setValue(scaleToBoundsImpl);
    }
}
