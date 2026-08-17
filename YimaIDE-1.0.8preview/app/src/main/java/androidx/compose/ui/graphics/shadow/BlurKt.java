package androidx.compose.ui.graphics.shadow;

import android.graphics.BlurMaskFilter;
import androidx.compose.ui.graphics.BlendMode;
import androidx.compose.ui.graphics.Color;
import androidx.compose.ui.graphics.Paint;
import androidx.compose.ui.graphics.PaintingStyle;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001aC\u0010\u0000\u001a\u00020\u0001*\u00020\u00012\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u0010\b\u0002\u0010\u0006\u001a\n\u0018\u00010\u0007j\u0004\u0018\u0001`\b2\b\b\u0002\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"configureShadow", "Landroidx/compose/ui/graphics/Paint;", "color", "Landroidx/compose/ui/graphics/Color;", "blendMode", "Landroidx/compose/ui/graphics/BlendMode;", "blurFilter", "Landroid/graphics/BlurMaskFilter;", "Landroidx/compose/ui/graphics/shadow/BlurFilter;", "style", "Landroidx/compose/ui/graphics/PaintingStyle;", "configureShadow-FoewPVk", "(Landroidx/compose/ui/graphics/Paint;JILandroid/graphics/BlurMaskFilter;I)Landroidx/compose/ui/graphics/Paint;", "ui-graphics"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class BlurKt {
    /* JADX INFO: renamed from: configureShadow-FoewPVk, reason: not valid java name */
    public static final Paint m3842configureShadowFoewPVk(Paint paint, long j, int i, BlurMaskFilter blurMaskFilter, int i2) {
        paint.mo3010setColor8_81llA(j);
        paint.mo3009setBlendModes9anfk8(i);
        paint.mo3014setStylek9PVt8s(i2);
        Blur_androidKt.setBlurFilter(paint, blurMaskFilter);
        return paint;
    }

    /* JADX INFO: renamed from: configureShadow-FoewPVk$default, reason: not valid java name */
    public static /* synthetic */ Paint m3843configureShadowFoewPVk$default(Paint paint, long j, int i, BlurMaskFilter blurMaskFilter, int i2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            j = Color.INSTANCE.m3160getBlack0d7_KjU();
        }
        long j2 = j;
        if ((i3 & 2) != 0) {
            i = BlendMode.INSTANCE.m3074getSrcOver0nO6VwU();
        }
        int i4 = i;
        if ((i3 & 4) != 0) {
            blurMaskFilter = null;
        }
        BlurMaskFilter blurMaskFilter2 = blurMaskFilter;
        if ((i3 & 8) != 0) {
            i2 = PaintingStyle.INSTANCE.m3415getFillTiuSbCo();
        }
        return m3842configureShadowFoewPVk(paint, j2, i4, blurMaskFilter2, i2);
    }
}
