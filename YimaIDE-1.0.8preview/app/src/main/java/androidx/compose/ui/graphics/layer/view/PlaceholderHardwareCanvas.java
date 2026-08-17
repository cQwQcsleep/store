package androidx.compose.ui.graphics.layer.view;

import android.graphics.Rect;
import android.view.HardwareCanvas;
import android.view.RenderNode;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\b\u0001\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005H\u0016J\b\u0010\u000b\u001a\u00020\fH\u0016¨\u0006\r"}, d2 = {"Landroidx/compose/ui/graphics/layer/view/PlaceholderHardwareCanvas;", "Landroid/view/HardwareCanvas;", "<init>", "()V", "drawRenderNode", "", "renderNode", "Landroid/view/RenderNode;", "dirty", "Landroid/graphics/Rect;", "flags", "isHardwareAccelerated", "", "ui-graphics"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class PlaceholderHardwareCanvas extends HardwareCanvas {
    public static final int $stable = 8;

    public int drawRenderNode(RenderNode renderNode, Rect dirty, int flags) {
        return 0;
    }

    public boolean isHardwareAccelerated() {
        return true;
    }
}
