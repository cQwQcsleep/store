package androidx.compose.ui.graphics.shadow;

import androidx.compose.ui.graphics.Brush;
import androidx.compose.ui.graphics.ColorKt;
import androidx.compose.ui.graphics.Interpolatable;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.compose.ui.unit.DpKt;
import androidx.compose.ui.util.MathHelpersKt;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0000\u001a$\u0010\u0006\u001a\u0004\u0018\u00010\u00012\b\u0010\u0002\u001a\u0004\u0018\u00010\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0004\u001a\u00020\u0005¨\u0006\u0007"}, d2 = {"lerpNonNull", "Landroidx/compose/ui/graphics/shadow/Shadow;", "a", "b", "t", "", "lerp", "ui-graphics"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ShadowKt {
    public static final Shadow lerp(Shadow shadow, Shadow shadow2, float f) {
        if (shadow == null && shadow2 == null) {
            return null;
        }
        if (shadow != null) {
            return shadow2 == null ? lerpNonNull(shadow, shadow.transparentCopy$ui_graphics(), f) : lerpNonNull(shadow, shadow2, f);
        }
        shadow2.getClass();
        return lerpNonNull(shadow2.transparentCopy$ui_graphics(), shadow2, f);
    }

    public static final Shadow lerpNonNull(Shadow shadow, Shadow shadow2, float f) {
        float fM6065lerpMdfbLM = DpKt.m6065lerpMdfbLM(shadow.getRadius(), shadow2.getRadius(), f);
        float fM6065lerpMdfbLM2 = DpKt.m6065lerpMdfbLM(shadow.getSpread(), shadow2.getSpread(), f);
        long jM6066lerpxhh869w = DpKt.m6066lerpxhh869w(shadow.getOffset(), shadow2.getOffset(), f);
        long jM3185lerpjxsXWHM = ColorKt.m3185lerpjxsXWHM(shadow.getColor(), shadow2.getColor(), f);
        Object objLerp = Interpolatable.INSTANCE.lerp(shadow.getBrush(), shadow2.getBrush(), f);
        return new Shadow(fM6065lerpMdfbLM, fM6065lerpMdfbLM2, jM6066lerpxhh869w, jM3185lerpjxsXWHM, objLerp instanceof Brush ? (Brush) objLerp : null, MathHelpersKt.lerp(shadow.getAlpha(), shadow2.getAlpha(), f), f < 0.5f ? shadow.getBlendMode() : shadow2.getBlendMode(), (DefaultConstructorMarker) null);
    }
}
