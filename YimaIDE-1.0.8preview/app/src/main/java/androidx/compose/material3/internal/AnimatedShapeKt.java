package androidx.compose.material3.internal;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.foundation.shape.RoundedCornerShape;
import androidx.compose.foundation.shape.RoundedCornerShapeKt;
import androidx.compose.material3.ShapeWithHorizontalCenterOptically;
import androidx.compose.material3.internal.AnimatedShapeKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.EffectsKt;
import androidx.compose.runtime.MutableState;
import androidx.compose.runtime.SnapshotStateKt__SnapshotStateKt;
import androidx.compose.ui.graphics.Outline;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.platform.CompositionLocalsKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.Density;
import androidx.compose.ui.unit.LayoutDirection;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.ranges.ClosedFloatingPointRange;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.channels.BufferOverflow;
import kotlinx.coroutines.channels.Channel;
import kotlinx.coroutines.channels.ChannelKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0002\b\u0002\u001a\u0015\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0003¢\u0006\u0002\u0010\u0004\u001a#\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u0001¢\u0006\u0002\u0010\n¨\u0006\u000b"}, d2 = {"rememberAnimatedShape", "Landroidx/compose/ui/graphics/Shape;", "state", "Landroidx/compose/material3/internal/AnimatedShapeState;", "(Landroidx/compose/material3/internal/AnimatedShapeState;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "currentShape", "Landroidx/compose/foundation/shape/RoundedCornerShape;", "animationSpec", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "", "(Landroidx/compose/foundation/shape/RoundedCornerShape;Landroidx/compose/animation/core/FiniteAnimationSpec;Landroidx/compose/runtime/Composer;I)Landroidx/compose/ui/graphics/Shape;", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AnimatedShapeKt {
    public static Unit a(Channel channel, RoundedCornerShape roundedCornerShape) {
        channel.trySend-JP2dKIU(roundedCornerShape);
        return Unit.INSTANCE;
    }

    public static final Shape rememberAnimatedShape(final RoundedCornerShape roundedCornerShape, FiniteAnimationSpec<Float> finiteAnimationSpec, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-288714613, i, -1, "androidx.compose.material3.internal.rememberAnimatedShape (AnimatedShape.kt:131)");
        }
        boolean zChanged = composer.changed(finiteAnimationSpec);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new AnimatedShapeState(roundedCornerShape, finiteAnimationSpec);
            composer.updateRememberedValue(objRememberedValue);
        }
        AnimatedShapeState animatedShapeState = (AnimatedShapeState) objRememberedValue;
        Object objRememberedValue2 = composer.rememberedValue();
        Composer.Companion companion = Composer.INSTANCE;
        if (objRememberedValue2 == companion.getEmpty()) {
            objRememberedValue2 = ChannelKt.Channel$default(-1, (BufferOverflow) null, (Function1) null, 6, (Object) null);
            composer.updateRememberedValue(objRememberedValue2);
        }
        final Channel channel = (Channel) objRememberedValue2;
        boolean zChangedInstance = ((((i & 14) ^ 6) > 4 && composer.changed(roundedCornerShape)) || (i & 6) == 4) | composer.changedInstance(channel);
        Object objRememberedValue3 = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue3 == companion.getEmpty()) {
            objRememberedValue3 = new Function0() { // from class: w70
                public final Object invoke() {
                    return AnimatedShapeKt.a(channel, roundedCornerShape);
                }
            };
            composer.updateRememberedValue(objRememberedValue3);
        }
        EffectsKt.SideEffect((Function0) objRememberedValue3, composer, 0);
        boolean zChangedInstance2 = composer.changedInstance(channel) | composer.changed(animatedShapeState);
        Object objRememberedValue4 = composer.rememberedValue();
        if (zChangedInstance2 || objRememberedValue4 == companion.getEmpty()) {
            objRememberedValue4 = new AnimatedShapeKt$rememberAnimatedShape$3$1(channel, animatedShapeState, null);
            composer.updateRememberedValue(objRememberedValue4);
        }
        EffectsKt.LaunchedEffect(animatedShapeState, channel, (Function2) objRememberedValue4, composer, 0);
        Shape shapeRememberAnimatedShape = rememberAnimatedShape(animatedShapeState, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return shapeRememberAnimatedShape;
    }

    private static final Shape rememberAnimatedShape(final AnimatedShapeState animatedShapeState, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1383367813, i, -1, "androidx.compose.material3.internal.rememberAnimatedShape (AnimatedShape.kt:89)");
        }
        Density density = (Density) composer.consume(CompositionLocalsKt.getLocalDensity());
        animatedShapeState.setDensity(density);
        boolean zChanged = ((((i & 14) ^ 6) > 4 && composer.changed(animatedShapeState)) || (i & 6) == 4) | composer.changed(density);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new ShapeWithHorizontalCenterOptically() { // from class: androidx.compose.material3.internal.AnimatedShapeKt$rememberAnimatedShape$1$1

                /* JADX INFO: renamed from: clampedRange$delegate, reason: from kotlin metadata */
                private final MutableState clampedRange = SnapshotStateKt__SnapshotStateKt.mutableStateOf$default(RangesKt.rangeTo(0.0f, 1.0f), null, 2, null);

                @Override // androidx.compose.ui.graphics.Shape
                /* JADX INFO: renamed from: createOutline-Pq9zytI */
                public Outline mo44createOutlinePq9zytI(long size, LayoutDirection layoutDirection, Density density2) {
                    animatedShapeState.m1376setSizeuvyYCjk(size);
                    setClampedRange(RangesKt.rangeTo(0.0f, Float.intBitsToFloat((int) (4294967295L & size)) / 2.0f));
                    return RoundedCornerShapeKt.RoundedCornerShape(((Number) RangesKt.coerceIn(Float.valueOf(AnimatedShapeState.m1372topStartTmRCtEA$default(animatedShapeState, 0L, null, 3, null)), getClampedRange())).floatValue(), ((Number) RangesKt.coerceIn(Float.valueOf(AnimatedShapeState.m1371topEndTmRCtEA$default(animatedShapeState, 0L, null, 3, null)), getClampedRange())).floatValue(), ((Number) RangesKt.coerceIn(Float.valueOf(AnimatedShapeState.m1369bottomEndTmRCtEA$default(animatedShapeState, 0L, null, 3, null)), getClampedRange())).floatValue(), ((Number) RangesKt.coerceIn(Float.valueOf(AnimatedShapeState.m1370bottomStartTmRCtEA$default(animatedShapeState, 0L, null, 3, null)), getClampedRange())).floatValue()).createOutline-Pq9zytI(size, layoutDirection, density2);
                }

                public final ClosedFloatingPointRange<Float> getClampedRange() {
                    return (ClosedFloatingPointRange) this.clampedRange.getValue();
                }

                @Override // androidx.compose.material3.ShapeWithHorizontalCenterOptically
                public float offset() {
                    float fFloatValue = ((Number) RangesKt.coerceIn(Float.valueOf(AnimatedShapeState.m1372topStartTmRCtEA$default(animatedShapeState, 0L, null, 3, null)), getClampedRange())).floatValue();
                    float fFloatValue2 = ((Number) RangesKt.coerceIn(Float.valueOf(AnimatedShapeState.m1371topEndTmRCtEA$default(animatedShapeState, 0L, null, 3, null)), getClampedRange())).floatValue();
                    return (((fFloatValue + ((Number) RangesKt.coerceIn(Float.valueOf(AnimatedShapeState.m1370bottomStartTmRCtEA$default(animatedShapeState, 0L, null, 3, null)), getClampedRange())).floatValue()) / 2.0f) - ((fFloatValue2 + ((Number) RangesKt.coerceIn(Float.valueOf(AnimatedShapeState.m1369bottomEndTmRCtEA$default(animatedShapeState, 0L, null, 3, null)), getClampedRange())).floatValue()) / 2.0f)) * 0.11f;
                }

                public final void setClampedRange(ClosedFloatingPointRange<Float> closedFloatingPointRange) {
                    this.clampedRange.setValue(closedFloatingPointRange);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        AnimatedShapeKt$rememberAnimatedShape$1$1 animatedShapeKt$rememberAnimatedShape$1$1 = (AnimatedShapeKt$rememberAnimatedShape$1$1) objRememberedValue;
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return animatedShapeKt$rememberAnimatedShape$1$1;
    }
}
