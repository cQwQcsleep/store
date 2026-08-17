package androidx.compose.ui.graphics;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.lang.reflect.Method;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\bH\u0007R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\bX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Landroidx/compose/ui/graphics/CanvasUtils;", "", "<init>", "()V", "reorderBarrierMethod", "Ljava/lang/reflect/Method;", "inorderBarrierMethod", "orderMethodsFetched", "", "enableZ", "", "canvas", "Landroid/graphics/Canvas;", "enable", "ui-graphics"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class CanvasUtils {
    private static Method inorderBarrierMethod;
    private static boolean orderMethodsFetched;
    private static Method reorderBarrierMethod;
    public static final CanvasUtils INSTANCE = new CanvasUtils();
    public static final int $stable = 8;

    private CanvasUtils() {
    }

    public final void enableZ(android.graphics.Canvas canvas, boolean enable) {
        CanvasZHelper.INSTANCE.enableZ(canvas, enable);
    }
}
