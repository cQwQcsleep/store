package defpackage;

import androidx.compose.animation.core.AnimateAsStateKt;
import androidx.compose.animation.core.AnimationSpecKt;
import androidx.compose.animation.core.Easing;
import androidx.compose.foundation.interaction.InteractionSource;
import androidx.compose.foundation.interaction.PressInteractionKt;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.State;
import androidx.compose.ui.ComposedModifierKt;
import androidx.compose.ui.Modifier;
import androidx.compose.ui.graphics.GraphicsLayerModifierKt;
import androidx.compose.ui.graphics.GraphicsLayerScope;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function3;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class m7b {
    public static Unit a(State state, GraphicsLayerScope graphicsLayerScope) {
        graphicsLayerScope.getClass();
        graphicsLayerScope.setScaleX(f(state));
        graphicsLayerScope.setScaleY(f(state));
        return Unit.INSTANCE;
    }

    public static Modifier b(InteractionSource interactionSource, float f, int i, Modifier modifier, Composer composer, int i2) {
        modifier.getClass();
        composer.startReplaceGroup(118033903);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(118033903, i2, -1, "com.yimaide.app.ui.modifier.pressScale.<anonymous> (PressScale.kt:39)");
        }
        if (!e(PressInteractionKt.collectIsPressedAsState(interactionSource, composer, 0))) {
            f = 1.0f;
        }
        final State stateAnimateFloatAsState = AnimateAsStateKt.animateFloatAsState(f, AnimationSpecKt.tween$default(i, 0, (Easing) null, 6, (Object) null), 0.0f, "pressScale", (Function1) null, composer, 3072, 20);
        boolean zChanged = composer.changed(stateAnimateFloatAsState);
        Object objRememberedValue = composer.rememberedValue();
        if (zChanged || objRememberedValue == Composer.Companion.getEmpty()) {
            objRememberedValue = new Function1() { // from class: l7b
                @Override // kotlin.jvm.functions.Function1
                public final Object invoke(Object obj) {
                    return m7b.a(stateAnimateFloatAsState, (GraphicsLayerScope) obj);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        Modifier modifierGraphicsLayer = GraphicsLayerModifierKt.graphicsLayer(modifier, (Function1) objRememberedValue);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        composer.endReplaceGroup();
        return modifierGraphicsLayer;
    }

    public static final Modifier c(Modifier modifier, final InteractionSource interactionSource, final float f, final int i) {
        modifier.getClass();
        interactionSource.getClass();
        return ComposedModifierKt.composed$default(modifier, (Function1) null, new Function3() { // from class: k7b
            public final Object invoke(Object obj, Object obj2, Object obj3) {
                return m7b.b(interactionSource, f, i, (Modifier) obj, (Composer) obj2, ((Integer) obj3).intValue());
            }
        }, 1, (Object) null);
    }

    public static /* synthetic */ Modifier d(Modifier modifier, InteractionSource interactionSource, float f, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            f = 0.93f;
        }
        if ((i2 & 4) != 0) {
            i = 120;
        }
        return c(modifier, interactionSource, f, i);
    }

    public static final boolean e(State state) {
        return ((Boolean) state.getValue()).booleanValue();
    }

    public static final float f(State state) {
        return ((Number) state.getValue()).floatValue();
    }
}
