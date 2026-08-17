package androidx.transition;

import android.graphics.Canvas;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
class CanvasUtils {
    private static Method sInorderBarrierMethod;
    private static boolean sOrderMethodsFetched;
    private static Method sReorderBarrierMethod;

    public static class Api29Impl {
        private Api29Impl() {
        }

        public static void disableZ(Canvas canvas) {
            canvas.disableZ();
        }

        public static void enableZ(Canvas canvas) {
            canvas.enableZ();
        }
    }

    private CanvasUtils() {
    }

    public static void enableZ(Canvas canvas, boolean z) {
        if (z) {
            Api29Impl.enableZ(canvas);
        } else {
            Api29Impl.disableZ(canvas);
        }
    }
}
