package androidx.compose.ui.graphics.drawscope;

import androidx.compose.ui.graphics.DegreesKt;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\u001a!\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00020\u0004H\u0086\b\u001a\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0000\u001a\u00020\u0004H\u0086\b\u001a&\u0010\u0006\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\u0007\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\tH\u0086\b¢\u0006\u0004\b\n\u0010\u000b\u001a&\u0010\f\u001a\u00020\u0001*\u00020\u00022\u0006\u0010\f\u001a\u00020\u00042\b\b\u0002\u0010\b\u001a\u00020\tH\u0086\b¢\u0006\u0004\b\r\u0010\u000b¨\u0006\u000e"}, d2 = {"inset", "", "Landroidx/compose/ui/graphics/drawscope/DrawTransform;", "horizontal", "", "vertical", "rotateRad", "radians", "pivot", "Landroidx/compose/ui/geometry/Offset;", "rotateRad-0AR0LA0", "(Landroidx/compose/ui/graphics/drawscope/DrawTransform;FJ)V", "scale", "scale-0AR0LA0", "ui-graphics"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DrawTransformKt {
    public static final void inset(DrawTransform drawTransform, float f, float f2) {
        drawTransform.inset(f, f2, f, f2);
    }

    public static /* synthetic */ void inset$default(DrawTransform drawTransform, float f, float f2, int i, Object obj) {
        if ((i & 1) != 0) {
            f = 0.0f;
        }
        if ((i & 2) != 0) {
            f2 = 0.0f;
        }
        drawTransform.inset(f, f2, f, f2);
    }

    /* JADX INFO: renamed from: rotateRad-0AR0LA0, reason: not valid java name */
    public static final void m3773rotateRad0AR0LA0(DrawTransform drawTransform, float f, long j) {
        drawTransform.mo3635rotateUv8p0NA(DegreesKt.degrees(f), j);
    }

    /* JADX INFO: renamed from: rotateRad-0AR0LA0$default, reason: not valid java name */
    public static /* synthetic */ void m3774rotateRad0AR0LA0$default(DrawTransform drawTransform, float f, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = drawTransform.mo3633getCenterF1C5BW0();
        }
        drawTransform.mo3635rotateUv8p0NA(DegreesKt.degrees(f), j);
    }

    /* JADX INFO: renamed from: scale-0AR0LA0, reason: not valid java name */
    public static final void m3775scale0AR0LA0(DrawTransform drawTransform, float f, long j) {
        drawTransform.mo3636scale0AR0LA0(f, f, j);
    }

    /* JADX INFO: renamed from: scale-0AR0LA0$default, reason: not valid java name */
    public static /* synthetic */ void m3776scale0AR0LA0$default(DrawTransform drawTransform, float f, long j, int i, Object obj) {
        if ((i & 2) != 0) {
            j = drawTransform.mo3633getCenterF1C5BW0();
        }
        drawTransform.mo3636scale0AR0LA0(f, f, j);
    }

    public static final void inset(DrawTransform drawTransform, float f) {
        drawTransform.inset(f, f, f, f);
    }
}
