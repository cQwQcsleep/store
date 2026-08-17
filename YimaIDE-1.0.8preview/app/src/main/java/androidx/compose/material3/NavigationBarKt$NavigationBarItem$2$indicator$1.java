package androidx.compose.material3;

import androidx.compose.foundation.BackgroundKt;
import androidx.compose.foundation.layout.BoxKt;
import androidx.compose.material3.tokens.NavigationBarTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import androidx.compose.ui.layout.LayoutIdKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class NavigationBarKt$NavigationBarItem$2$indicator$1 implements Function2<Composer, Integer, Unit> {
    final /* synthetic */ State<Float> $alphaAnimationProgress;
    final /* synthetic */ NavigationBarItemColors $colors;

    public NavigationBarKt$NavigationBarItem$2$indicator$1(State<Float> state, NavigationBarItemColors navigationBarItemColors) {
        this.$alphaAnimationProgress = state;
        this.$colors = navigationBarItemColors;
    }

    public static Unit a(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.setAlpha(((Number) state.getValue()).floatValue());
        return Unit.INSTANCE;
    }

    public final void invoke(Composer composer, int i) {
        if (!composer.shouldExecute((i & 3) != 2, i & 1)) {
            composer.skipToGroupEnd();
            return;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-799524251, i, -1, "androidx.compose.material3.NavigationBarItem.<anonymous>.<anonymous> (NavigationBar.kt:297)");
        }
        Modifier modifierLayoutId = LayoutIdKt.layoutId(Modifier.INSTANCE, "indicator");
        boolean zChanged = composer.changed(this.$alphaAnimationProgress);
        final State<Float> state = this.$alphaAnimationProgress;
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function1() { // from class: androidx.compose.material3.s2
                public final Object invoke(Object obj) {
                    return NavigationBarKt$NavigationBarItem$2$indicator$1.a(state, (GraphicsLayerScope) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        BoxKt.Box(BackgroundKt.background-bw27NRU(GraphicsLayerModifierKt.graphicsLayer(modifierLayoutId, (Function1) objRememberedValue), this.$colors.getSelectedIndicatorColor(), ShapesKt.getValue(NavigationBarTokens.INSTANCE.getItemActiveIndicatorShape(), composer, 6)), composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
    }

    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
        invoke((Composer) obj, ((Number) obj2).intValue());
        return Unit.INSTANCE;
    }
}
