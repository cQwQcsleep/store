package androidx.compose.material3;

import androidx.compose.animation.core.FiniteAnimationSpec;
import androidx.compose.material3.tokens.MotionSchemeKeyTokens;
import androidx.compose.runtime.Composer;
import androidx.compose.runtime.ComposerKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u0005H\u0001\u001a\u001d\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u0004\b\u0000\u0010\u0002*\u00020\u0005H\u0001¢\u0006\u0002\u0010\u0006¨\u0006\u0007"}, d2 = {"fromToken", "Landroidx/compose/animation/core/FiniteAnimationSpec;", "T", "Landroidx/compose/material3/MotionScheme;", "value", "Landroidx/compose/material3/tokens/MotionSchemeKeyTokens;", "(Landroidx/compose/material3/tokens/MotionSchemeKeyTokens;Landroidx/compose/runtime/Composer;I)Landroidx/compose/animation/core/FiniteAnimationSpec;", "material3"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class MotionSchemeKt {

    @Metadata(k = 3, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
    public /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MotionSchemeKeyTokens.values().length];
            try {
                iArr[MotionSchemeKeyTokens.DefaultSpatial.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MotionSchemeKeyTokens.FastSpatial.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[MotionSchemeKeyTokens.SlowSpatial.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[MotionSchemeKeyTokens.DefaultEffects.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[MotionSchemeKeyTokens.FastEffects.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[MotionSchemeKeyTokens.SlowEffects.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public static final <T> FiniteAnimationSpec<T> fromToken(MotionScheme motionScheme, MotionSchemeKeyTokens motionSchemeKeyTokens) {
        switch (WhenMappings.$EnumSwitchMapping$0[motionSchemeKeyTokens.ordinal()]) {
            case 1:
                return motionScheme.defaultSpatialSpec();
            case 2:
                return motionScheme.fastSpatialSpec();
            case 3:
                return motionScheme.slowSpatialSpec();
            case 4:
                return motionScheme.defaultEffectsSpec();
            case 5:
                return motionScheme.fastEffectsSpec();
            case 6:
                return motionScheme.slowEffectsSpec();
            default:
                bu8.a();
                return null;
        }
    }

    public static final <T> FiniteAnimationSpec<T> value(MotionSchemeKeyTokens motionSchemeKeyTokens, Composer composer, int i) {
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventStart(-19828261, i, -1, "androidx.compose.material3.value (MotionScheme.kt:288)");
        }
        FiniteAnimationSpec<T> finiteAnimationSpecFromToken = fromToken(MaterialTheme.INSTANCE.getMotionScheme(composer, 6), motionSchemeKeyTokens);
        if (ComposerKt.isTraceInProgress()) {
            ComposerKt.traceEventEnd();
        }
        return finiteAnimationSpecFromToken;
    }
}
