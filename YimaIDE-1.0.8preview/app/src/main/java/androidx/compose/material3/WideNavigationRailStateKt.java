package androidx.compose.material3;

import androidx.compose.animation.core.AnimationSpec;
import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.material3.WideNavigationRailStateKt;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.runtime.saveable.RememberSaveableKt;
import androidx.compose.runtime.saveable.Saver;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\u001a\u0017\u0010\u0000\u001a\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u0003H\u0007¢\u0006\u0002\u0010\u0004\u001a\r\u0010\b\u001a\u00020\u0003*\u00020\u0003H\u0080\u0002\"\u0018\u0010\u0005\u001a\u00020\u0006*\u00020\u00038@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0007¨\u0006\t"}, d2 = {"rememberWideNavigationRailState", "Landroidx/compose/material3/WideNavigationRailState;", "initialValue", "Landroidx/compose/material3/WideNavigationRailValue;", "(Landroidx/compose/material3/WideNavigationRailValue;Landroidx/compose/runtime/Composer;II)Landroidx/compose/material3/WideNavigationRailState;", "isExpanded", "", "(Landroidx/compose/material3/WideNavigationRailValue;)Z", "not", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WideNavigationRailStateKt {
    public static WideNavigationRailState a(WideNavigationRailValue wideNavigationRailValue, FiniteAnimationSpec finiteAnimationSpec) {
        return new WideNavigationRailStateImpl(wideNavigationRailValue, finiteAnimationSpec);
    }

    public static final boolean isExpanded(WideNavigationRailValue wideNavigationRailValue) {
        return wideNavigationRailValue == WideNavigationRailValue.Expanded;
    }

    public static final WideNavigationRailValue not(WideNavigationRailValue wideNavigationRailValue) {
        WideNavigationRailValue wideNavigationRailValue2 = WideNavigationRailValue.Collapsed;
        return wideNavigationRailValue == wideNavigationRailValue2 ? WideNavigationRailValue.Expanded : wideNavigationRailValue2;
    }

    public static final WideNavigationRailState rememberWideNavigationRailState(final WideNavigationRailValue wideNavigationRailValue, Composer composer, int i, int i2) {
        boolean z = true;
        if ((i2 & 1) != 0) {
            wideNavigationRailValue = WideNavigationRailValue.Collapsed;
        }
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(1595273472, i, -1, "androidx.compose.material3.rememberWideNavigationRailState (WideNavigationRailState.kt:86)");
        }
        final AnimationSpec<Float> animationSpecValue = MotionSchemeKt.value(MotionSchemeKeyTokens.DefaultSpatial, composer, 6);
        Object[] objArr = new Object[0];
        Saver<WideNavigationRailState, WideNavigationRailValue> Saver = WideNavigationRailStateImpl.INSTANCE.Saver(animationSpecValue);
        if ((((i & 14) ^ 6) <= 4 || !composer.changed(wideNavigationRailValue.ordinal())) && (i & 6) != 4) {
            z = false;
        }
        boolean zChangedInstance = composer.changedInstance(animationSpecValue) | z;
        Object objRememberedValue = composer.rememberedValue();
        if (zChangedInstance || objRememberedValue == Composer.INSTANCE.getEmpty()) {
            objRememberedValue = new Function0() { // from class: cnf
                public final Object invoke() {
                    return WideNavigationRailStateKt.a(wideNavigationRailValue, animationSpecValue);
                }
            };
            composer.updateRememberedValue(objRememberedValue);
        }
        WideNavigationRailState wideNavigationRailState = (WideNavigationRailState) RememberSaveableKt.m2564rememberSaveable(objArr, (Saver) Saver, (Function0) objRememberedValue, composer, 0);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return wideNavigationRailState;
    }
}
