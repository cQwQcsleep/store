package androidx.window.area.utils;

import android.view.Window;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.extensions.area.ExtensionWindowAreaPresentation;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0012\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0007J\u0012\u0010\b\u001a\u0004\u0018\u00010\t2\u0006\u0010\u0006\u001a\u00020\u0007H\u0002¨\u0006\n"}, d2 = {"Landroidx/window/area/utils/PresentationWindowCompatUtils;", "", "<init>", "()V", "getWindowBeforeVendorApiLevel4", "Landroid/view/Window;", "extensionPresentation", "Landroidx/window/extensions/area/ExtensionWindowAreaPresentation;", "getWindowMethod", "Ljava/lang/reflect/Method;", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class PresentationWindowCompatUtils {
    public static final PresentationWindowCompatUtils INSTANCE = new PresentationWindowCompatUtils();

    private PresentationWindowCompatUtils() {
    }

    private final Method getWindowMethod(ExtensionWindowAreaPresentation extensionPresentation) {
        Method[] methods = extensionPresentation.getClass().getMethods();
        methods.getClass();
        int length = methods.length;
        int i = 0;
        while (true) {
            if (i >= length) {
                return null;
            }
            Method method = methods[i];
            if (Intrinsics.areEqual(method != null ? method.getName() : null, "getWindow") && Intrinsics.areEqual(method.getReturnType(), Window.class)) {
                return method;
            }
            i++;
        }
    }

    public final Window getWindowBeforeVendorApiLevel4(ExtensionWindowAreaPresentation extensionPresentation) {
        extensionPresentation.getClass();
        Method windowMethod = getWindowMethod(extensionPresentation);
        if (windowMethod == null) {
            return null;
        }
        return (Window) windowMethod.invoke(extensionPresentation, null);
    }
}
