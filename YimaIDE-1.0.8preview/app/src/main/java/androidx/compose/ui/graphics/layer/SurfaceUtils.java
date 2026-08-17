package androidx.compose.ui.graphics.layer;

import android.graphics.Canvas;
import android.view.Surface;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÁ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0007J\u0006\u0010\f\u001a\u00020\u0007J\n\u0010\r\u001a\u0004\u0018\u00010\u0005H\u0003J\u0010\u0010\u000e\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0003R\u0010\u0010\u0004\u001a\u0004\u0018\u00010\u0005X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Landroidx/compose/ui/graphics/layer/SurfaceUtils;", "", "<init>", "()V", "lockHardwareCanvasMethod", "Ljava/lang/reflect/Method;", "hasRetrievedMethod", "", "lockCanvas", "Landroid/graphics/Canvas;", "surface", "Landroid/view/Surface;", "isLockHardwareCanvasAvailable", "resolveLockHardwareCanvasMethod", "lockCanvasFallback", "ui-graphics"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SurfaceUtils {
    private static boolean hasRetrievedMethod;
    private static Method lockHardwareCanvasMethod;
    public static final SurfaceUtils INSTANCE = new SurfaceUtils();
    public static final int $stable = 8;

    private SurfaceUtils() {
    }

    private final Canvas lockCanvasFallback(Surface surface) throws IllegalAccessException, InvocationTargetException {
        Method methodResolveLockHardwareCanvasMethod = resolveLockHardwareCanvasMethod();
        if (methodResolveLockHardwareCanvasMethod == null) {
            return surface.lockCanvas(null);
        }
        Object objInvoke = methodResolveLockHardwareCanvasMethod.invoke(surface, null);
        objInvoke.getClass();
        return (Canvas) objInvoke;
    }

    private final Method resolveLockHardwareCanvasMethod() {
        Method method;
        synchronized (this) {
            method = null;
            try {
                Method method2 = lockHardwareCanvasMethod;
                if (hasRetrievedMethod) {
                    method = method2;
                } else {
                    hasRetrievedMethod = true;
                    Method declaredMethod = Surface.class.getDeclaredMethod("lockHardwareCanvas", null);
                    declaredMethod.setAccessible(true);
                    lockHardwareCanvasMethod = declaredMethod;
                    method = declaredMethod;
                }
            } catch (Throwable unused) {
                lockHardwareCanvasMethod = null;
            }
        }
        return method;
    }

    public final boolean isLockHardwareCanvasAvailable() {
        return true;
    }

    public final Canvas lockCanvas(Surface surface) {
        return SurfaceVerificationHelper.INSTANCE.lockHardwareCanvas(surface);
    }
}
