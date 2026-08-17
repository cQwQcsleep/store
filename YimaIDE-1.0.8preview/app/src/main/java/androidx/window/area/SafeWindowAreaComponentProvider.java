package androidx.window.area;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.SafeWindowExtensionsProvider;
import androidx.window.area.SafeWindowAreaComponentProvider;
import androidx.window.area.reflectionguard.WindowAreaComponentValidator;
import androidx.window.core.ExtensionsUtil;
import androidx.window.extensions.WindowExtensions;
import androidx.window.extensions.area.WindowAreaComponent;
import androidx.window.reflection.ReflectionUtils;
import androidx.window.reflection.WindowExtensionsConstants;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u0006\u001a\u00020\u0001H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0006\u001a\u0004\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\b\u001a\u0004\u0018\u00010\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0018\u0010\u000e\u001a\u0006\u0012\u0002\b\u00030\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011R\u0018\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0011R\u0018\u0010\u0014\u001a\u0006\u0012\u0002\b\u00030\u000f8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0011¨\u0006\u0016"}, d2 = {"Landroidx/window/area/SafeWindowAreaComponentProvider;", "", "loader", "Ljava/lang/ClassLoader;", "<init>", "(Ljava/lang/ClassLoader;)V", "windowExtensions", "Landroidx/window/extensions/WindowExtensions;", "windowAreaComponent", "Landroidx/window/extensions/area/WindowAreaComponent;", "getWindowAreaComponent", "()Landroidx/window/extensions/area/WindowAreaComponent;", "isWindowAreaProviderValid", "", "windowAreaComponentClass", "Ljava/lang/Class;", "getWindowAreaComponentClass", "()Ljava/lang/Class;", "extensionWindowAreaStatusClass", "getExtensionWindowAreaStatusClass", "extensionWindowAreaPresentationClass", "getExtensionWindowAreaPresentationClass", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SafeWindowAreaComponentProvider {
    private final ClassLoader loader;
    private final WindowExtensions windowExtensions;

    public SafeWindowAreaComponentProvider(ClassLoader classLoader) {
        classLoader.getClass();
        this.loader = classLoader;
        this.windowExtensions = new SafeWindowExtensionsProvider(classLoader).getWindowExtensions();
    }

    public static boolean a(Object obj, SafeWindowAreaComponentProvider safeWindowAreaComponentProvider) throws NoSuchMethodException {
        Method method = obj.getClass().getMethod("getWindowAreaComponent", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, safeWindowAreaComponentProvider.getWindowAreaComponentClass());
    }

    private final Class<?> getExtensionWindowAreaPresentationClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.EXTENSION_WINDOW_AREA_PRESENTATION_CLASS);
        clsLoadClass.getClass();
        return clsLoadClass;
    }

    private final Class<?> getExtensionWindowAreaStatusClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.EXTENSION_WINDOW_AREA_STATUS_CLASS);
        clsLoadClass.getClass();
        return clsLoadClass;
    }

    private final Class<?> getWindowAreaComponentClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.WINDOW_AREA_COMPONENT_CLASS);
        clsLoadClass.getClass();
        return clsLoadClass;
    }

    private final boolean isWindowAreaProviderValid(final Object windowExtensions) {
        return ReflectionUtils.validateReflection$window_release("WindowExtensions#getWindowAreaComponent is not valid", new Function0() { // from class: arc
            public final Object invoke() {
                return Boolean.valueOf(SafeWindowAreaComponentProvider.a(windowExtensions, this));
            }
        });
    }

    public final WindowAreaComponent getWindowAreaComponent() {
        try {
            WindowExtensions windowExtensions = this.windowExtensions;
            if (windowExtensions != null && isWindowAreaProviderValid(windowExtensions)) {
                WindowAreaComponentValidator windowAreaComponentValidator = WindowAreaComponentValidator.INSTANCE;
                Class<?> windowAreaComponentClass = getWindowAreaComponentClass();
                ExtensionsUtil extensionsUtil = ExtensionsUtil.INSTANCE;
                if (windowAreaComponentValidator.isWindowAreaComponentValid$window_release(windowAreaComponentClass, extensionsUtil.getSafeVendorApiLevel()) && windowAreaComponentValidator.isExtensionWindowAreaStatusValid$window_release(getExtensionWindowAreaStatusClass(), extensionsUtil.getSafeVendorApiLevel()) && windowAreaComponentValidator.isExtensionWindowAreaPresentationValid$window_release(getExtensionWindowAreaPresentationClass(), extensionsUtil.getSafeVendorApiLevel())) {
                    return this.windowExtensions.getWindowAreaComponent();
                }
            }
        } catch (Throwable unused) {
        }
        return null;
    }
}
