package androidx.compose.ui.graphics;

import android.graphics.ComposePathEffect;
import android.graphics.CornerPathEffect;
import android.graphics.DashPathEffect;
import android.graphics.PathDashPathEffect;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\u0002\n\u0002\u0010\u0014\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\n\u0010\u0000\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010\u0003\u001a\u00020\u0002*\u00020\u0001\u001a\u0010\u0010\u0004\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0006H\u0000\u001a\u0018\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0006H\u0000\u001a\u0018\u0010\u000b\u001a\u00020\u00022\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0002H\u0000\u001a/\u0010\u000e\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u00062\u0006\u0010\u0012\u001a\u00020\u0013H\u0000¢\u0006\u0004\b\u0014\u0010\u0015\u001a\u0013\u0010\u0016\u001a\u00020\u0017*\u00020\u0013H\u0000¢\u0006\u0004\b\u0018\u0010\u0019¨\u0006\u001a"}, d2 = {"asAndroidPathEffect", "Landroid/graphics/PathEffect;", "Landroidx/compose/ui/graphics/PathEffect;", "toComposePathEffect", "actualCornerPathEffect", "radius", "", "actualDashPathEffect", "intervals", "", "phase", "actualChainPathEffect", "outer", "inner", "actualStampedPathEffect", "shape", "Landroidx/compose/ui/graphics/Path;", "advance", "style", "Landroidx/compose/ui/graphics/StampedPathEffectStyle;", "actualStampedPathEffect-7aD1DOk", "(Landroidx/compose/ui/graphics/Path;FFI)Landroidx/compose/ui/graphics/PathEffect;", "toAndroidPathDashPathEffectStyle", "Landroid/graphics/PathDashPathEffect$Style;", "toAndroidPathDashPathEffectStyle-oQv6xUo", "(I)Landroid/graphics/PathDashPathEffect$Style;", "ui-graphics"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AndroidPathEffect_androidKt {
    public static final PathEffect actualChainPathEffect(PathEffect pathEffect, PathEffect pathEffect2) {
        pathEffect.getClass();
        android.graphics.PathEffect nativePathEffect = ((AndroidPathEffect) pathEffect).getNativePathEffect();
        pathEffect2.getClass();
        return new AndroidPathEffect(new ComposePathEffect(nativePathEffect, ((AndroidPathEffect) pathEffect2).getNativePathEffect()));
    }

    public static final PathEffect actualCornerPathEffect(float f) {
        return new AndroidPathEffect(new CornerPathEffect(f));
    }

    public static final PathEffect actualDashPathEffect(float[] fArr, float f) {
        return new AndroidPathEffect(new DashPathEffect(fArr, f));
    }

    /* JADX INFO: renamed from: actualStampedPathEffect-7aD1DOk, reason: not valid java name */
    public static final PathEffect m3027actualStampedPathEffect7aD1DOk(Path path, float f, float f2, int i) {
        if (path instanceof AndroidPath) {
            return new AndroidPathEffect(new PathDashPathEffect(((AndroidPath) path).getInternalPath(), f, f2, m3028toAndroidPathDashPathEffectStyleoQv6xUo(i)));
        }
        c41.a("Unable to obtain android.graphics.Path");
        return null;
    }

    public static final android.graphics.PathEffect asAndroidPathEffect(PathEffect pathEffect) {
        pathEffect.getClass();
        return ((AndroidPathEffect) pathEffect).getNativePathEffect();
    }

    /* JADX INFO: renamed from: toAndroidPathDashPathEffectStyle-oQv6xUo, reason: not valid java name */
    public static final PathDashPathEffect.Style m3028toAndroidPathDashPathEffectStyleoQv6xUo(int i) {
        StampedPathEffectStyle.Companion companion = StampedPathEffectStyle.INSTANCE;
        if (StampedPathEffectStyle.m3494equalsimpl0(i, companion.m3498getMorphYpspkwk())) {
            return PathDashPathEffect.Style.MORPH;
        }
        if (StampedPathEffectStyle.m3494equalsimpl0(i, companion.m3499getRotateYpspkwk())) {
            return PathDashPathEffect.Style.ROTATE;
        }
        return StampedPathEffectStyle.m3494equalsimpl0(i, companion.m3500getTranslateYpspkwk()) ? PathDashPathEffect.Style.TRANSLATE : PathDashPathEffect.Style.TRANSLATE;
    }

    public static final PathEffect toComposePathEffect(android.graphics.PathEffect pathEffect) {
        return new AndroidPathEffect(pathEffect);
    }
}
