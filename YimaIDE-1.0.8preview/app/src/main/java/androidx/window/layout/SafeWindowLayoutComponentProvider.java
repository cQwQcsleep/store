package androidx.window.layout;

import android.app.Activity;
import android.content.Context;
import android.graphics.Rect;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.SafeWindowExtensionsProvider;
import androidx.window.core.ConsumerAdapter;
import androidx.window.core.ExtensionsUtil;
import androidx.window.extensions.WindowExtensionsProvider;
import androidx.window.extensions.core.util.function.Consumer;
import androidx.window.extensions.layout.WindowLayoutComponent;
import androidx.window.layout.SafeWindowLayoutComponentProvider;
import androidx.window.reflection.ReflectionUtils;
import androidx.window.reflection.WindowExtensionsConstants;
import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0010\n\u0002\u0018\u0002\n\u0002\b\t\b\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\b\u0010\u000e\u001a\u00020\u000fH\u0002J\r\u0010\u0010\u001a\u00020\u000fH\u0001¢\u0006\u0002\b\u0011J\r\u0010\u0012\u001a\u00020\u000fH\u0001¢\u0006\u0002\b\u0013J\r\u0010\u0014\u001a\u00020\u000fH\u0001¢\u0006\u0002\b\u0015J\r\u0010\u0016\u001a\u00020\u000fH\u0001¢\u0006\u0002\b\u0017J\b\u0010\u0018\u001a\u00020\u000fH\u0002J\b\u0010\u0019\u001a\u00020\u000fH\u0002J\b\u0010\u001a\u001a\u00020\u000fH\u0002J\b\u0010\u001b\u001a\u00020\u000fH\u0002J\b\u0010\u001c\u001a\u00020\u000fH\u0002J\b\u0010\u001d\u001a\u00020\u000fH\u0002J\b\u0010\u001e\u001a\u00020\u000fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\rR\u0018\u0010\u001f\u001a\u0006\u0012\u0002\b\u00030 8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u0018\u0010#\u001a\u0006\u0012\u0002\b\u00030 8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010\"R\u0018\u0010%\u001a\u0006\u0012\u0002\b\u00030 8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\"R\u0018\u0010'\u001a\u0006\u0012\u0002\b\u00030 8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b(\u0010\"¨\u0006)"}, d2 = {"Landroidx/window/layout/SafeWindowLayoutComponentProvider;", "", "loader", "Ljava/lang/ClassLoader;", "consumerAdapter", "Landroidx/window/core/ConsumerAdapter;", "<init>", "(Ljava/lang/ClassLoader;Landroidx/window/core/ConsumerAdapter;)V", "safeWindowExtensionsProvider", "Landroidx/window/SafeWindowExtensionsProvider;", "windowLayoutComponent", "Landroidx/window/extensions/layout/WindowLayoutComponent;", "getWindowLayoutComponent", "()Landroidx/window/extensions/layout/WindowLayoutComponent;", "canUseWindowLayoutComponent", "", "isWindowLayoutComponentAccessible", "isWindowLayoutComponentAccessible$window_release", "hasValidVendorApiLevel1", "hasValidVendorApiLevel1$window_release", "hasValidVendorApiLevel2", "hasValidVendorApiLevel2$window_release", "hasValidVendorApiLevel6", "hasValidVendorApiLevel6$window_release", "isWindowLayoutProviderValid", "isFoldingFeatureValid", "isMethodWindowLayoutInfoListenerJavaConsumerValid", "isMethodWindowLayoutInfoListenerWindowConsumerValid", "isDisplayFoldFeatureValid", "isSupportedWindowFeaturesValid", "isGetSupportedWindowFeaturesValid", "displayFoldFeatureClass", "Ljava/lang/Class;", "getDisplayFoldFeatureClass", "()Ljava/lang/Class;", "supportedWindowFeaturesClass", "getSupportedWindowFeaturesClass", "foldingFeatureClass", "getFoldingFeatureClass", "windowLayoutComponentClass", "getWindowLayoutComponentClass", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SafeWindowLayoutComponentProvider {
    private final ConsumerAdapter consumerAdapter;
    private final ClassLoader loader;
    private final SafeWindowExtensionsProvider safeWindowExtensionsProvider;

    public SafeWindowLayoutComponentProvider(ClassLoader classLoader, ConsumerAdapter consumerAdapter) {
        classLoader.getClass();
        consumerAdapter.getClass();
        this.loader = classLoader;
        this.consumerAdapter = consumerAdapter;
        this.safeWindowExtensionsProvider = new SafeWindowExtensionsProvider(classLoader);
    }

    public static boolean a(SafeWindowLayoutComponentProvider safeWindowLayoutComponentProvider) throws NoSuchMethodException {
        Method method = safeWindowLayoutComponentProvider.getWindowLayoutComponentClass().getMethod("getSupportedWindowFeatures", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, safeWindowLayoutComponentProvider.getSupportedWindowFeaturesClass());
    }

    public static boolean b(SafeWindowLayoutComponentProvider safeWindowLayoutComponentProvider) throws NoSuchMethodException, ClassNotFoundException {
        Class<?> clsConsumerClassOrNull$window_release = safeWindowLayoutComponentProvider.consumerAdapter.consumerClassOrNull$window_release();
        if (clsConsumerClassOrNull$window_release == null) {
            return false;
        }
        Class<?> windowLayoutComponentClass = safeWindowLayoutComponentProvider.getWindowLayoutComponentClass();
        Method method = windowLayoutComponentClass.getMethod("addWindowLayoutInfoListener", Activity.class, clsConsumerClassOrNull$window_release);
        Method method2 = windowLayoutComponentClass.getMethod("removeWindowLayoutInfoListener", clsConsumerClassOrNull$window_release);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method)) {
            return false;
        }
        method2.getClass();
        return reflectionUtils.isPublic$window_release(method2);
    }

    public static boolean c(SafeWindowLayoutComponentProvider safeWindowLayoutComponentProvider) throws NoSuchMethodException, ClassNotFoundException {
        Method method = safeWindowLayoutComponentProvider.safeWindowExtensionsProvider.getWindowExtensionsClass$window_release().getMethod("getWindowLayoutComponent", null);
        Class<?> windowLayoutComponentClass = safeWindowLayoutComponentProvider.getWindowLayoutComponentClass();
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, windowLayoutComponentClass);
    }

    private final boolean canUseWindowLayoutComponent() {
        int safeVendorApiLevel;
        if (!isWindowLayoutComponentAccessible$window_release() || (safeVendorApiLevel = ExtensionsUtil.INSTANCE.getSafeVendorApiLevel()) < 1) {
            return false;
        }
        if (safeVendorApiLevel == 1) {
            return hasValidVendorApiLevel1$window_release();
        }
        return safeVendorApiLevel < 5 ? hasValidVendorApiLevel2$window_release() : hasValidVendorApiLevel6$window_release();
    }

    public static boolean d(SafeWindowLayoutComponentProvider safeWindowLayoutComponentProvider) throws NoSuchMethodException, ClassNotFoundException {
        Class<?> foldingFeatureClass = safeWindowLayoutComponentProvider.getFoldingFeatureClass();
        Method method = foldingFeatureClass.getMethod("getBounds", null);
        Method method2 = foldingFeatureClass.getMethod("getType", null);
        Method method3 = foldingFeatureClass.getMethod("getState", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.doesReturn$window_release(method, Reflection.getOrCreateKotlinClass(Rect.class)) || !reflectionUtils.isPublic$window_release(method)) {
            return false;
        }
        method2.getClass();
        Class cls = Integer.TYPE;
        if (!reflectionUtils.doesReturn$window_release(method2, Reflection.getOrCreateKotlinClass(cls)) || !reflectionUtils.isPublic$window_release(method2)) {
            return false;
        }
        method3.getClass();
        return reflectionUtils.doesReturn$window_release(method3, Reflection.getOrCreateKotlinClass(cls)) && reflectionUtils.isPublic$window_release(method3);
    }

    public static boolean e(SafeWindowLayoutComponentProvider safeWindowLayoutComponentProvider) throws NoSuchMethodException, ClassNotFoundException {
        Class<?> displayFoldFeatureClass = safeWindowLayoutComponentProvider.getDisplayFoldFeatureClass();
        Method method = displayFoldFeatureClass.getMethod("getType", null);
        Class<?> cls = Integer.TYPE;
        Method method2 = displayFoldFeatureClass.getMethod("hasProperty", cls);
        Method method3 = displayFoldFeatureClass.getMethod("hasProperties", int[].class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method) || !reflectionUtils.doesReturn$window_release(method, cls)) {
            return false;
        }
        method2.getClass();
        if (!reflectionUtils.isPublic$window_release(method2)) {
            return false;
        }
        Class<?> cls2 = Boolean.TYPE;
        if (!reflectionUtils.doesReturn$window_release(method2, cls2)) {
            return false;
        }
        method3.getClass();
        return reflectionUtils.isPublic$window_release(method3) && reflectionUtils.doesReturn$window_release(method3, cls2);
    }

    public static boolean g(SafeWindowLayoutComponentProvider safeWindowLayoutComponentProvider) throws NoSuchMethodException {
        Method method = safeWindowLayoutComponentProvider.getSupportedWindowFeaturesClass().getMethod("getDisplayFoldFeatures", null);
        Type genericReturnType = method.getGenericReturnType();
        genericReturnType.getClass();
        Type type = ((ParameterizedType) genericReturnType).getActualTypeArguments()[0];
        type.getClass();
        Class cls = (Class) type;
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, List.class) && Intrinsics.areEqual(cls, safeWindowLayoutComponentProvider.getDisplayFoldFeatureClass());
    }

    private final Class<?> getDisplayFoldFeatureClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.DISPLAY_FOLD_FEATURE_CLASS);
        clsLoadClass.getClass();
        return clsLoadClass;
    }

    private final Class<?> getFoldingFeatureClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.FOLDING_FEATURE_CLASS);
        clsLoadClass.getClass();
        return clsLoadClass;
    }

    private final Class<?> getSupportedWindowFeaturesClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.SUPPORTED_WINDOW_FEATURES_CLASS);
        clsLoadClass.getClass();
        return clsLoadClass;
    }

    private final Class<?> getWindowLayoutComponentClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.WINDOW_LAYOUT_COMPONENT_CLASS);
        clsLoadClass.getClass();
        return clsLoadClass;
    }

    private final boolean isDisplayFoldFeatureValid() {
        return ReflectionUtils.validateReflection$window_release("DisplayFoldFeature is not valid", new Function0() { // from class: grc
            public final Object invoke() {
                return Boolean.valueOf(SafeWindowLayoutComponentProvider.e(this.b));
            }
        });
    }

    private final boolean isFoldingFeatureValid() {
        return ReflectionUtils.validateReflection$window_release("FoldingFeature class is not valid", new Function0() { // from class: erc
            public final Object invoke() {
                return Boolean.valueOf(SafeWindowLayoutComponentProvider.d(this.b));
            }
        });
    }

    private final boolean isGetSupportedWindowFeaturesValid() {
        return ReflectionUtils.validateReflection$window_release("WindowLayoutComponent#getSupportedWindowFeatures is not valid", new Function0() { // from class: hrc
            public final Object invoke() {
                return Boolean.valueOf(SafeWindowLayoutComponentProvider.a(this.b));
            }
        });
    }

    private final boolean isMethodWindowLayoutInfoListenerJavaConsumerValid() {
        return ReflectionUtils.validateReflection$window_release("WindowLayoutComponent#addWindowLayoutInfoListener(" + Activity.class.getName() + ", java.util.function.Consumer) is not valid", new Function0() { // from class: irc
            public final Object invoke() {
                return Boolean.valueOf(SafeWindowLayoutComponentProvider.b(this.b));
            }
        });
    }

    private final boolean isMethodWindowLayoutInfoListenerWindowConsumerValid() {
        return ReflectionUtils.validateReflection$window_release("WindowLayoutComponent#addWindowLayoutInfoListener(" + Context.class.getName() + ", androidx.window.extensions.core.util.function.Consumer) is not valid", new Function0() { // from class: jrc
            public final Object invoke() {
                return Boolean.valueOf(SafeWindowLayoutComponentProvider.isMethodWindowLayoutInfoListenerWindowConsumerValid$lambda$3(this.b));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodWindowLayoutInfoListenerWindowConsumerValid$lambda$3(SafeWindowLayoutComponentProvider safeWindowLayoutComponentProvider) throws NoSuchMethodException, ClassNotFoundException {
        Class<?> windowLayoutComponentClass = safeWindowLayoutComponentProvider.getWindowLayoutComponentClass();
        Method method = windowLayoutComponentClass.getMethod("addWindowLayoutInfoListener", Context.class, Consumer.class);
        Method method2 = windowLayoutComponentClass.getMethod("removeWindowLayoutInfoListener", Consumer.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method)) {
            return false;
        }
        method2.getClass();
        return reflectionUtils.isPublic$window_release(method2);
    }

    private final boolean isSupportedWindowFeaturesValid() {
        return ReflectionUtils.validateReflection$window_release("SupportedWindowFeatures is not valid", new Function0() { // from class: frc
            public final Object invoke() {
                return Boolean.valueOf(SafeWindowLayoutComponentProvider.g(this.b));
            }
        });
    }

    private final boolean isWindowLayoutProviderValid() {
        return ReflectionUtils.validateReflection$window_release("WindowExtensions#getWindowLayoutComponent is not valid", new Function0() { // from class: drc
            public final Object invoke() {
                return Boolean.valueOf(SafeWindowLayoutComponentProvider.c(this.b));
            }
        });
    }

    public final WindowLayoutComponent getWindowLayoutComponent() {
        if (!canUseWindowLayoutComponent()) {
            return null;
        }
        try {
            return WindowExtensionsProvider.getWindowExtensions().getWindowLayoutComponent();
        } catch (UnsupportedOperationException unused) {
            return null;
        }
    }

    public final boolean hasValidVendorApiLevel1$window_release() {
        return isMethodWindowLayoutInfoListenerJavaConsumerValid();
    }

    public final boolean hasValidVendorApiLevel2$window_release() {
        return hasValidVendorApiLevel1$window_release() && isMethodWindowLayoutInfoListenerWindowConsumerValid();
    }

    public final boolean hasValidVendorApiLevel6$window_release() {
        return hasValidVendorApiLevel2$window_release() && isDisplayFoldFeatureValid() && isSupportedWindowFeaturesValid() && isGetSupportedWindowFeaturesValid();
    }

    public final boolean isWindowLayoutComponentAccessible$window_release() {
        return this.safeWindowExtensionsProvider.isWindowExtensionsValid$window_release() && isWindowLayoutProviderValid() && isFoldingFeatureValid();
    }
}
