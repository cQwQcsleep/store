package androidx.compose.material3;

import androidx.compose.animation.core.Animatable;
import androidx.compose.animation.core.AnimationVector1D;
import androidx.compose.foundation.layout.Arrangement;
import androidx.compose.foundation.layout.WindowInsets;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.graphics.Shape;
import androidx.compose.ui.graphics.TransformOriginKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WideNavigationRailKt$ModalWideNavigationRailContent$5 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ Arrangement.Vertical $arrangement;
    final /* synthetic */ WideNavigationRailColors $colors;
    final /* synthetic */ Function2<Composer, Integer, Unit> $content;
    final /* synthetic */ boolean $expanded;
    final /* synthetic */ Function2<Composer, Integer, Unit> $header;
    final /* synthetic */ boolean $isRtl;
    final /* synthetic */ Animatable<Float, AnimationVector1D> $predictiveBackProgress;
    final /* synthetic */ RailPredictiveBackState $predictiveBackState;
    final /* synthetic */ Shape $shape;
    final /* synthetic */ WindowInsets $windowInsets;

    /* JADX WARN: Multi-variable type inference failed */
    public WideNavigationRailKt$ModalWideNavigationRailContent$5(Animatable<Float, AnimationVector1D> animatable, RailPredictiveBackState railPredictiveBackState, boolean z, boolean z2, WideNavigationRailColors wideNavigationRailColors, Shape shape, Function2<? super Composer, ? super Integer, Unit> function2, WindowInsets windowInsets, Arrangement.Vertical vertical, Function2<? super Composer, ? super Integer, Unit> function3) {
        this.$predictiveBackProgress = animatable;
        this.$predictiveBackState = railPredictiveBackState;
        this.$isRtl = z;
        this.$expanded = z2;
        this.$colors = wideNavigationRailColors;
        this.$shape = shape;
        this.$header = function2;
        this.$windowInsets = windowInsets;
        this.$arrangement = vertical;
        this.$content = function3;
    }

    public static Unit a(Animatable animatable, RailPredictiveBackState railPredictiveBackState, boolean z, GraphicsLayerScope graphicsLayerScope) {
        float fFloatValue = ((Number) animatable.getValue()).floatValue();
        if (fFloatValue <= 0.0f) {
            return Unit.INSTANCE;
        }
        float fCalculatePredictiveBackScaleX = WideNavigationRailKt.calculatePredictiveBackScaleX(graphicsLayerScope, fFloatValue, railPredictiveBackState.getSwipeEdgeMatchesRail());
        graphicsLayerScope.setScaleX(fCalculatePredictiveBackScaleX == 0.0f ? 1.0f : WideNavigationRailKt.calculatePredictiveBackScaleY(graphicsLayerScope, fFloatValue) / fCalculatePredictiveBackScaleX);
        graphicsLayerScope.mo3335setTransformOrigin__ExYCQ(TransformOriginKt.TransformOrigin(z ? 0.0f : 1.0f, 0.5f));
        return Unit.INSTANCE;
    }

    public final void invoke(Composer composer, int i) {
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-1043835354, i, -1, "androidx.compose.material3.ModalWideNavigationRailContent.<anonymous> (WideNavigationRail.kt:1044)");
        }
        Modifier.Companion companion = Modifier.INSTANCE;
        boolean zChangedInstance = composer.changedInstance(this.$predictiveBackProgress) | composer.changed(this.$predictiveBackState) | composer.changed(this.$isRtl);
        final Animatable<Float, AnimationVector1D> animatable = this.$predictiveBackProgress;
        final RailPredictiveBackState railPredictiveBackState = this.$predictiveBackState;
        final boolean z = this.$isRtl;
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function1() { // from class: androidx.compose.material3.q5
                public final Object invoke(Object obj) {
                    return WideNavigationRailKt$ModalWideNavigationRailContent$5.a(animatable, railPredictiveBackState, z, (GraphicsLayerScope) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        WideNavigationRailKt.WideNavigationRailLayout(GraphicsLayerModifierKt.graphicsLayer(companion, (Function1) objRememberedValue), true, this.$expanded, this.$colors, this.$shape, this.$header, this.$windowInsets, this.$arrangement, this.$content, composer, 48);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }
}
