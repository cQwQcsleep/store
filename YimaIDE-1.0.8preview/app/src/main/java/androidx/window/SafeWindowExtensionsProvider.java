package androidx.window;

import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.SafeWindowExtensionsProvider;
import androidx.window.extensions.WindowExtensions;
import androidx.window.extensions.WindowExtensionsProvider;
import androidx.window.reflection.ReflectionUtils;
import androidx.window.reflection.WindowExtensionsConstants;
import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\b\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\r\u0010\u000e\u001a\u00020\u000fH\u0000¢\u0006\u0002\b\u0010J\b\u0010\u0011\u001a\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u00078F¢\u0006\u0006\u001a\u0004\b\b\u0010\tR\u0018\u0010\n\u001a\u0006\u0012\u0002\b\u00030\u000b8@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u0012\u001a\u0006\u0012\u0002\b\u00030\u000b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\r¨\u0006\u0014"}, d2 = {"Landroidx/window/SafeWindowExtensionsProvider;", "", "loader", "Ljava/lang/ClassLoader;", "<init>", "(Ljava/lang/ClassLoader;)V", "windowExtensions", "Landroidx/window/extensions/WindowExtensions;", "getWindowExtensions", "()Landroidx/window/extensions/WindowExtensions;", "windowExtensionsClass", "Ljava/lang/Class;", "getWindowExtensionsClass$window_release", "()Ljava/lang/Class;", "isWindowExtensionsValid", "", "isWindowExtensionsValid$window_release", "isWindowExtensionsPresent", "windowExtensionsProviderClass", "getWindowExtensionsProviderClass", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SafeWindowExtensionsProvider {
    private final ClassLoader loader;

    public SafeWindowExtensionsProvider(ClassLoader classLoader) {
        classLoader.getClass();
        this.loader = classLoader;
    }

    public static Class a(SafeWindowExtensionsProvider safeWindowExtensionsProvider) throws ClassNotFoundException {
        Class<?> clsLoadClass = safeWindowExtensionsProvider.loader.loadClass(WindowExtensionsConstants.WINDOW_EXTENSIONS_PROVIDER_CLASS);
        clsLoadClass.getClass();
        return clsLoadClass;
    }

    public static boolean b(SafeWindowExtensionsProvider safeWindowExtensionsProvider) throws NoSuchMethodException, ClassNotFoundException {
        Method declaredMethod = safeWindowExtensionsProvider.getWindowExtensionsProviderClass().getDeclaredMethod("getWindowExtensions", null);
        Class<?> windowExtensionsClass$window_release = safeWindowExtensionsProvider.getWindowExtensionsClass$window_release();
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        declaredMethod.getClass();
        return reflectionUtils.doesReturn$window_release(declaredMethod, windowExtensionsClass$window_release) && reflectionUtils.isPublic$window_release(declaredMethod);
    }

    private final Class<?> getWindowExtensionsProviderClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.WINDOW_EXTENSIONS_PROVIDER_CLASS);
        clsLoadClass.getClass();
        return clsLoadClass;
    }

    private final boolean isWindowExtensionsPresent() {
        return ReflectionUtils.INSTANCE.checkIsPresent$window_release(new Function0() { // from class: brc
            public final Object invoke() {
                return SafeWindowExtensionsProvider.a(this.b);
            }
        });
    }

    public final WindowExtensions getWindowExtensions() {
        try {
            if (isWindowExtensionsPresent() && isWindowExtensionsValid$window_release()) {
                return WindowExtensionsProvider.getWindowExtensions();
            }
        } catch (Exception unused) {
        }
        return null;
    }

    public final Class<?> getWindowExtensionsClass$window_release() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.WINDOW_EXTENSIONS_CLASS);
        clsLoadClass.getClass();
        return clsLoadClass;
    }

    public final boolean isWindowExtensionsValid$window_release() {
        return isWindowExtensionsPresent() && ReflectionUtils.validateReflection$window_release("WindowExtensionsProvider#getWindowExtensions is not valid", new Function0() { // from class: crc
            public final Object invoke() {
                return Boolean.valueOf(SafeWindowExtensionsProvider.b(this.b));
            }
        });
    }
}
