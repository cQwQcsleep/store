package androidx.compose.ui.text.android;

import android.graphics.Canvas;
import android.graphics.Path;
import android.graphics.RectF;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import com.reandroid.archive.PathTree;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0004\bÃ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000bJ.\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\r¨\u0006\u0011"}, d2 = {"Landroidx/compose/ui/text/android/CanvasCompatR;", "", "<init>", "()V", "quickReject", "", "canvas", "Landroid/graphics/Canvas;", "rect", "Landroid/graphics/RectF;", PathTree.NAME_path, "Landroid/graphics/Path;", "left", "", "top", "right", "bottom", "ui-text"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
final class CanvasCompatR {
    public static final CanvasCompatR INSTANCE = new CanvasCompatR();

    private CanvasCompatR() {
    }

    public final boolean quickReject(Canvas canvas, RectF rect) {
        return canvas.quickReject(rect);
    }

    public final boolean quickReject(Canvas canvas, Path path) {
        return canvas.quickReject(path);
    }

    public final boolean quickReject(Canvas canvas, float left, float top, float right, float bottom) {
        return canvas.quickReject(left, top, right, bottom);
    }
}
