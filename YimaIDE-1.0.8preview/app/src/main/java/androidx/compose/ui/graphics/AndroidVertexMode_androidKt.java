package androidx.compose.ui.graphics;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0011\u0010\u0000\u001a\u00020\u0001*\u00020\u0002¢\u0006\u0004\b\u0003\u0010\u0004¨\u0006\u0005"}, d2 = {"toAndroidVertexMode", "Landroid/graphics/Canvas$VertexMode;", "Landroidx/compose/ui/graphics/VertexMode;", "toAndroidVertexMode-JOOmi9M", "(I)Landroid/graphics/Canvas$VertexMode;", "ui-graphics"}, k = 2, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class AndroidVertexMode_androidKt {
    /* JADX INFO: renamed from: toAndroidVertexMode-JOOmi9M, reason: not valid java name */
    public static final android.graphics.Canvas.VertexMode m3038toAndroidVertexModeJOOmi9M(int i) {
        VertexMode.Companion companion = VertexMode.INSTANCE;
        if (VertexMode.m3552equalsimpl0(i, companion.m3558getTrianglesc2xauaI())) {
            return android.graphics.Canvas.VertexMode.TRIANGLES;
        }
        if (VertexMode.m3552equalsimpl0(i, companion.m3557getTriangleStripc2xauaI())) {
            return android.graphics.Canvas.VertexMode.TRIANGLE_STRIP;
        }
        return VertexMode.m3552equalsimpl0(i, companion.m3556getTriangleFanc2xauaI()) ? android.graphics.Canvas.VertexMode.TRIANGLE_FAN : android.graphics.Canvas.VertexMode.TRIANGLES;
    }
}
