package androidx.window.area.reflectionguard;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.extensions.area.ExtensionWindowAreaPresentation;
import androidx.window.reflection.ReflectionUtils;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J!\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\nJ!\u0010\u000b\u001a\u00020\u00052\n\u0010\f\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\rJ!\u0010\u000e\u001a\u00020\u00052\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\b\u001a\u00020\tH\u0000¢\u0006\u0002\b\u0010¨\u0006\u0011"}, d2 = {"Landroidx/window/area/reflectionguard/WindowAreaComponentValidator;", "", "<init>", "()V", "isWindowAreaComponentValid", "", "windowAreaComponent", "Ljava/lang/Class;", "apiLevel", "", "isWindowAreaComponentValid$window_release", "isExtensionWindowAreaStatusValid", "extensionWindowAreaStatus", "isExtensionWindowAreaStatusValid$window_release", "isExtensionWindowAreaPresentationValid", "extensionWindowAreaPresentation", "isExtensionWindowAreaPresentationValid$window_release", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class WindowAreaComponentValidator {
    public static final WindowAreaComponentValidator INSTANCE = new WindowAreaComponentValidator();

    private WindowAreaComponentValidator() {
    }

    public final boolean isExtensionWindowAreaPresentationValid$window_release(Class<?> extensionWindowAreaPresentation, int apiLevel) {
        extensionWindowAreaPresentation.getClass();
        if (apiLevel <= 2) {
            return false;
        }
        return ReflectionUtils.INSTANCE.validateImplementation$window_release(extensionWindowAreaPresentation, ExtensionWindowAreaPresentation.class);
    }

    public final boolean isExtensionWindowAreaStatusValid$window_release(Class<?> extensionWindowAreaStatus, int apiLevel) {
        extensionWindowAreaStatus.getClass();
        if (apiLevel <= 2) {
            return false;
        }
        return ReflectionUtils.INSTANCE.validateImplementation$window_release(extensionWindowAreaStatus, ExtensionWindowAreaStatusRequirements.class);
    }

    public final boolean isWindowAreaComponentValid$window_release(Class<?> windowAreaComponent, int apiLevel) {
        windowAreaComponent.getClass();
        if (apiLevel <= 2) {
            return false;
        }
        return ReflectionUtils.INSTANCE.validateImplementation$window_release(windowAreaComponent, WindowAreaComponentApi3Requirements.class);
    }
}
