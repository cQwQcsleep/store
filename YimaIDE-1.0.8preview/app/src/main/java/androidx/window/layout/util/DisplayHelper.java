package androidx.window.layout.util;

import android.graphics.Point;
import android.view.Display;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Landroidx/window/layout/util/DisplayHelper;", "", "<init>", "()V", "getRealSizeForDisplay", "Landroid/graphics/Point;", "display", "Landroid/view/Display;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class DisplayHelper {
    public static final DisplayHelper INSTANCE = new DisplayHelper();

    private DisplayHelper() {
    }

    public final Point getRealSizeForDisplay(Display display) {
        display.getClass();
        Point point = new Point();
        display.getRealSize(point);
        return point;
    }
}
