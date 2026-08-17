package androidx.window.embedding;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.IBinder;
import android.view.WindowMetrics;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.SafeWindowExtensionsProvider;
import androidx.window.WindowSdkExtensions;
import androidx.window.core.ConsumerAdapter;
import androidx.window.embedding.SafeActivityEmbeddingComponentProvider;
import androidx.window.extensions.WindowExtensions;
import androidx.window.extensions.core.util.function.Consumer;
import androidx.window.extensions.core.util.function.Function;
import androidx.window.extensions.core.util.function.Predicate;
import androidx.window.extensions.embedding.ActivityEmbeddingComponent;
import androidx.window.extensions.embedding.ActivityStackAttributes;
import androidx.window.extensions.embedding.ActivityStackAttributesCalculatorParams;
import androidx.window.extensions.embedding.AnimationBackground;
import androidx.window.extensions.embedding.AnimationParams;
import androidx.window.extensions.embedding.WindowAttributes;
import androidx.window.extensions.layout.WindowLayoutInfo;
import androidx.window.reflection.ReflectionUtils;
import androidx.window.reflection.WindowExtensionsConstants;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Set;
import java.util.concurrent.Executor;
import kotlin.Metadata;
import kotlin.jvm.functions.Function0;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0012\n\u0002\u0018\u0002\n\u0002\bB\b\u0000\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\b\u0010\u0010\u001a\u00020\u0011H\u0002J\r\u0010\u0012\u001a\u00020\u0011H\u0001¢\u0006\u0002\b\u0013J\r\u0010\u0014\u001a\u00020\u0011H\u0001¢\u0006\u0002\b\u0015J\r\u0010\u0016\u001a\u00020\u0011H\u0001¢\u0006\u0002\b\u0017J\r\u0010\u0018\u001a\u00020\u0011H\u0001¢\u0006\u0002\b\u0019J\r\u0010\u001a\u001a\u00020\u0011H\u0001¢\u0006\u0002\b\u001bJ\r\u0010\u001c\u001a\u00020\u0011H\u0001¢\u0006\u0002\b\u001dJ\r\u0010\u001e\u001a\u00020\u0011H\u0001¢\u0006\u0002\b\u001fJ\r\u0010 \u001a\u00020\u0011H\u0001¢\u0006\u0002\b!J\b\u0010\"\u001a\u00020\u0011H\u0002J\b\u0010'\u001a\u00020\u0011H\u0002J\b\u0010(\u001a\u00020\u0011H\u0002J\b\u0010)\u001a\u00020\u0011H\u0002J\b\u0010*\u001a\u00020\u0011H\u0002J\b\u0010+\u001a\u00020\u0011H\u0002J\b\u0010,\u001a\u00020\u0011H\u0002J\b\u0010-\u001a\u00020\u0011H\u0002J\b\u0010.\u001a\u00020\u0011H\u0002J\b\u0010/\u001a\u00020\u0011H\u0002J\b\u00100\u001a\u00020\u0011H\u0002J\b\u00101\u001a\u00020\u0011H\u0002J\b\u00102\u001a\u00020\u0011H\u0002J\b\u00103\u001a\u00020\u0011H\u0002J\b\u00104\u001a\u00020\u0011H\u0002J\b\u00105\u001a\u00020\u0011H\u0002J\b\u00106\u001a\u00020\u0011H\u0002J\b\u00107\u001a\u00020\u0011H\u0002J\b\u00108\u001a\u00020\u0011H\u0002J\b\u00109\u001a\u00020\u0011H\u0002J\b\u0010:\u001a\u00020\u0011H\u0002J\b\u0010;\u001a\u00020\u0011H\u0002J\b\u0010<\u001a\u00020\u0011H\u0002J\b\u0010=\u001a\u00020\u0011H\u0002J\b\u0010>\u001a\u00020\u0011H\u0002J\b\u0010?\u001a\u00020\u0011H\u0002J\b\u0010@\u001a\u00020\u0011H\u0002J\b\u0010A\u001a\u00020\u0011H\u0002J\b\u0010B\u001a\u00020\u0011H\u0002J\b\u0010C\u001a\u00020\u0011H\u0002J\b\u0010D\u001a\u00020\u0011H\u0002J\b\u0010E\u001a\u00020\u0011H\u0002J\b\u0010F\u001a\u00020\u0011H\u0002J\b\u0010G\u001a\u00020\u0011H\u0002J\b\u0010H\u001a\u00020\u0011H\u0002J\b\u0010I\u001a\u00020\u0011H\u0002J\b\u0010J\u001a\u00020\u0011H\u0002J\b\u0010K\u001a\u00020\u0011H\u0002J\b\u0010L\u001a\u00020\u0011H\u0002J\b\u0010M\u001a\u00020\u0011H\u0002J\b\u0010N\u001a\u00020\u0011H\u0002J\b\u0010O\u001a\u00020\u0011H\u0002J\b\u0010P\u001a\u00020\u0011H\u0002J\b\u0010Q\u001a\u00020\u0011H\u0002J\b\u0010R\u001a\u00020\u0011H\u0002J\b\u0010S\u001a\u00020\u0011H\u0002J\b\u0010T\u001a\u00020\u0011H\u0002J\b\u0010U\u001a\u00020\u0011H\u0002J\b\u0010V\u001a\u00020\u0011H\u0002J\b\u0010W\u001a\u00020\u0011H\u0002J\b\u0010X\u001a\u00020\u0011H\u0002J\b\u0010Y\u001a\u00020\u0011H\u0002J\b\u0010Z\u001a\u00020\u0011H\u0002J\b\u0010[\u001a\u00020\u0011H\u0002J\b\u0010\\\u001a\u00020\u0011H\u0002J\b\u0010]\u001a\u00020\u0011H\u0002J\b\u0010^\u001a\u00020\u0011H\u0002J\b\u0010_\u001a\u00020\u0011H\u0002J\b\u0010`\u001a\u00020\u0011H\u0002J\b\u0010a\u001a\u00020\u0011H\u0002J\b\u0010b\u001a\u00020\u0011H\u0002J\b\u0010c\u001a\u00020\u0011H\u0002J\b\u0010d\u001a\u00020\u0011H\u0002J\b\u0010e\u001a\u00020\u0011H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0013\u0010\f\u001a\u0004\u0018\u00010\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0018\u0010#\u001a\u0006\u0012\u0002\b\u00030$8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b%\u0010&¨\u0006f"}, d2 = {"Landroidx/window/embedding/SafeActivityEmbeddingComponentProvider;", "", "loader", "Ljava/lang/ClassLoader;", "consumerAdapter", "Landroidx/window/core/ConsumerAdapter;", "windowExtensions", "Landroidx/window/extensions/WindowExtensions;", "<init>", "(Ljava/lang/ClassLoader;Landroidx/window/core/ConsumerAdapter;Landroidx/window/extensions/WindowExtensions;)V", "safeWindowExtensionsProvider", "Landroidx/window/SafeWindowExtensionsProvider;", "activityEmbeddingComponent", "Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;", "getActivityEmbeddingComponent", "()Landroidx/window/extensions/embedding/ActivityEmbeddingComponent;", "canUseActivityEmbeddingComponent", "", "isActivityEmbeddingComponentAccessible", "isActivityEmbeddingComponentAccessible$window_release", "hasValidVendorApiLevel1", "hasValidVendorApiLevel1$window_release", "hasValidVendorApiLevel2", "hasValidVendorApiLevel2$window_release", "hasValidVendorApiLevel3", "hasValidVendorApiLevel3$window_release", "hasValidVendorApiLevel5", "hasValidVendorApiLevel5$window_release", "hasValidVendorApiLevel6", "hasValidVendorApiLevel6$window_release", "hasValidVendorApiLevel7", "hasValidVendorApiLevel7$window_release", "hasValidVendorApiLevel8", "hasValidVendorApiLevel8$window_release", "isOverlayFeatureValid", "activityEmbeddingComponentClass", "Ljava/lang/Class;", "getActivityEmbeddingComponentClass", "()Ljava/lang/Class;", "isActivityEmbeddingComponentValid", "isMethodSetEmbeddingRulesValid", "isMethodIsActivityEmbeddedValid", "isMethodSetSplitInfoCallbackJavaConsumerValid", "isMethodGetSplitRatioValid", "isMethodGetLayoutDirectionValid", "isClassActivityRuleValid", "isClassActivityRuleBuilderLevel1Valid", "isClassSplitInfoValid", "isClassSplitPairRuleValid", "isClassSplitPairRuleBuilderLevel1Valid", "isClassSplitPlaceholderRuleValid", "isClassSplitPlaceholderRuleBuilderLevel1Valid", "isMethodSetSplitInfoCallbackWindowConsumerValid", "isMethodClearSplitInfoCallbackValid", "isMethodSplitAttributesCalculatorValid", "isMethodGetSplitAttributesValid", "isMethodGetFinishPrimaryWithPlaceholderValid", "isMethodGetDefaultSplitAttributesValid", "isClassActivityRuleBuilderLevel2Valid", "isClassEmbeddingRuleValid", "isClassSplitAttributesValid", "isClassSplitAttributesCalculatorParamsValid", "isClassSplitTypeValid", "isClassSplitPairRuleBuilderLevel2Valid", "isClassSplitPlaceholderRuleBuilderLevel2Valid", "isMethodInvalidateTopVisibleSplitAttributesValid", "isMethodUpdateSplitAttributesValid", "isMethodSplitInfoGetTokenValid", "isActivityStackGetActivityStackTokenValid", "isMethodRegisterActivityStackCallbackValid", "isMethodUnregisterActivityStackCallbackValid", "isMethodPinUnpinTopActivityStackValid", "isMethodUpdateSplitAttributesWithTokenValid", "isMethodGetSplitInfoTokenValid", "isClassAnimationBackgroundValid", "isClassActivityStackTokenValid", "isClassWindowAttributesValid", "isClassSplitInfoTokenValid", "isMethodGetEmbeddedActivityWindowInfoValid", "isMethodSetEmbeddedActivityWindowInfoCallbackValid", "isMethodClearEmbeddedActivityWindowInfoCallbackValid", "isMethodGetDividerAttributesValid", "isMethodSetDividerAttributesValid", "isClassEmbeddedActivityWindowInfoValid", "isClassDividerAttributesValid", "isClassDividerAttributesBuilderValid", "isMethodGetAnimationParamsValid", "isMethodSetAnimationParamsValid", "isMethodIsDraggingToFullscreenAllowedValid", "isMethodSetDraggingToFullscreenAllowedValid", "isClassAnimationParamsValid", "isClassAnimationParamsBuilderValid", "isActivityStackGetTagValid", "isMethodGetActivityStackTokenValid", "isClassParentContainerInfoValid", "isMethodGetParentContainerInfoValid", "isMethodSetActivityStackAttributesCalculatorValid", "isMethodClearActivityStackAttributesCalculatorValid", "isMethodUpdateActivityStackAttributesValid", "isClassActivityStackAttributesValid", "isClassActivityStackAttributesBuilderValid", "isClassActivityStackAttributesCalculatorParamsValid", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class SafeActivityEmbeddingComponentProvider {
    private final ConsumerAdapter consumerAdapter;
    private final ClassLoader loader;
    private final SafeWindowExtensionsProvider safeWindowExtensionsProvider;
    private final WindowExtensions windowExtensions;

    public SafeActivityEmbeddingComponentProvider(ClassLoader classLoader, ConsumerAdapter consumerAdapter, WindowExtensions windowExtensions) {
        classLoader.getClass();
        consumerAdapter.getClass();
        windowExtensions.getClass();
        this.loader = classLoader;
        this.consumerAdapter = consumerAdapter;
        this.windowExtensions = windowExtensions;
        this.safeWindowExtensionsProvider = new SafeWindowExtensionsProvider(classLoader);
    }

    public static boolean F(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) throws NoSuchMethodException {
        Class<?> clsConsumerClassOrNull$window_release = safeActivityEmbeddingComponentProvider.consumerAdapter.consumerClassOrNull$window_release();
        if (clsConsumerClassOrNull$window_release == null) {
            return false;
        }
        Method method = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass().getMethod("setSplitInfoCallback", clsConsumerClassOrNull$window_release);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method);
    }

    public static boolean L(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) throws NoSuchMethodException {
        Method method = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass().getMethod("setEmbeddingRules", Set.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method);
    }

    public static boolean Q(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) throws NoSuchMethodException {
        Method method = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass().getMethod("clearActivityStackAttributesCalculator", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method);
    }

    private final boolean canUseActivityEmbeddingComponent() {
        if (!isActivityEmbeddingComponentAccessible$window_release()) {
            return false;
        }
        int extensionVersion = WindowSdkExtensions.INSTANCE.getInstance().getExtensionVersion();
        if (extensionVersion == 1) {
            return hasValidVendorApiLevel1$window_release();
        }
        if (extensionVersion == 2) {
            return hasValidVendorApiLevel2$window_release();
        }
        if (3 <= extensionVersion && extensionVersion < 5) {
            return hasValidVendorApiLevel3$window_release();
        }
        if (extensionVersion == 5) {
            return hasValidVendorApiLevel5$window_release();
        }
        if (extensionVersion == 6) {
            return hasValidVendorApiLevel6$window_release();
        }
        if (extensionVersion == 7) {
            return hasValidVendorApiLevel7$window_release();
        }
        if (8 > extensionVersion || extensionVersion > Integer.MAX_VALUE) {
            return false;
        }
        return hasValidVendorApiLevel8$window_release();
    }

    private final Class<?> getActivityEmbeddingComponentClass() throws ClassNotFoundException {
        Class<?> clsLoadClass = this.loader.loadClass(WindowExtensionsConstants.ACTIVITY_EMBEDDING_COMPONENT_CLASS);
        clsLoadClass.getClass();
        return clsLoadClass;
    }

    private final boolean isActivityEmbeddingComponentValid() {
        return ReflectionUtils.validateReflection$window_release("WindowExtensions#getActivityEmbeddingComponent is not valid", new Function0() { // from class: pqc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.o(this.b));
            }
        });
    }

    private final boolean isActivityStackGetActivityStackTokenValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityStack#getActivityToken is not valid", new Function0() { // from class: aqc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isActivityStackGetActivityStackTokenValid$lambda$29());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isActivityStackGetActivityStackTokenValid$lambda$29() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.ActivityStack.class.getMethod("getActivityStackToken", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, androidx.window.extensions.embedding.ActivityStack.Token.class);
    }

    private final boolean isActivityStackGetTagValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityStack#getTag is not valid", new Function0() { // from class: mpc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isActivityStackGetTagValid$lambda$53());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isActivityStackGetTagValid$lambda$53() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.ActivityStack.class.getMethod("getTag", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, String.class);
    }

    private final boolean isClassActivityRuleBuilderLevel1Valid() {
        return ReflectionUtils.validateReflection$window_release("Class ActivityRule.Builder is not valid", new Function0() { // from class: kpc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassActivityRuleBuilderLevel1Valid$lambda$7());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassActivityRuleBuilderLevel1Valid$lambda$7() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.ActivityRule.Builder.class.getMethod("setShouldAlwaysExpand", Boolean.TYPE);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, androidx.window.extensions.embedding.ActivityRule.Builder.class);
    }

    private final boolean isClassActivityRuleBuilderLevel2Valid() {
        return ReflectionUtils.validateReflection$window_release("Class ActivityRule.Builder is not valid", new Function0() { // from class: rpc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassActivityRuleBuilderLevel2Valid$lambda$19());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassActivityRuleBuilderLevel2Valid$lambda$19() throws NoSuchMethodException {
        Constructor<?> declaredConstructor = androidx.window.extensions.embedding.ActivityRule.Builder.class.getDeclaredConstructor(Predicate.class, Predicate.class);
        Method method = androidx.window.extensions.embedding.ActivityRule.Builder.class.getMethod("setTag", String.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        declaredConstructor.getClass();
        if (!reflectionUtils.isPublic$window_release(declaredConstructor)) {
            return false;
        }
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, androidx.window.extensions.embedding.ActivityRule.Builder.class);
    }

    private final boolean isClassActivityRuleValid() {
        return ReflectionUtils.validateReflection$window_release("Class ActivityRule is not valid", new Function0() { // from class: voc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassActivityRuleValid$lambda$6());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassActivityRuleValid$lambda$6() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.ActivityRule.class.getMethod("shouldAlwaysExpand", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, Boolean.TYPE);
    }

    private final boolean isClassActivityStackAttributesBuilderValid() {
        return ReflectionUtils.validateReflection$window_release("Class ActivityStackAttributes.Builder is not valid", new Function0() { // from class: ppc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassActivityStackAttributesBuilderValid$lambda$61());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassActivityStackAttributesBuilderValid$lambda$61() throws NoSuchMethodException {
        Constructor<?> declaredConstructor = ActivityStackAttributes.Builder.class.getDeclaredConstructor(null);
        Method method = ActivityStackAttributes.Builder.class.getMethod("setRelativeBounds", Rect.class);
        Method method2 = ActivityStackAttributes.Builder.class.getMethod("setWindowAttributes", WindowAttributes.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        declaredConstructor.getClass();
        if (!reflectionUtils.isPublic$window_release(declaredConstructor)) {
            return false;
        }
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method) || !reflectionUtils.doesReturn$window_release(method, ActivityStackAttributes.Builder.class)) {
            return false;
        }
        method2.getClass();
        return reflectionUtils.isPublic$window_release(method2) && reflectionUtils.doesReturn$window_release(method2, ActivityStackAttributes.Builder.class);
    }

    private final boolean isClassActivityStackAttributesCalculatorParamsValid() {
        return ReflectionUtils.validateReflection$window_release("Class ActivityStackAttributesCalculatorParams is not valid", new Function0() { // from class: xoc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassActivityStackAttributesCalculatorParamsValid$lambda$62());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassActivityStackAttributesCalculatorParamsValid$lambda$62() throws NoSuchMethodException {
        Method method = ActivityStackAttributesCalculatorParams.class.getMethod("getParentContainerInfo", null);
        Method method2 = ActivityStackAttributesCalculatorParams.class.getMethod("getActivityStackTag", null);
        Method method3 = ActivityStackAttributesCalculatorParams.class.getMethod("getLaunchOptions", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method) || !reflectionUtils.doesReturn$window_release(method, androidx.window.extensions.embedding.ParentContainerInfo.class)) {
            return false;
        }
        method2.getClass();
        if (!reflectionUtils.isPublic$window_release(method2) || !reflectionUtils.doesReturn$window_release(method2, String.class)) {
            return false;
        }
        method3.getClass();
        return reflectionUtils.isPublic$window_release(method3) && reflectionUtils.doesReturn$window_release(method3, Bundle.class);
    }

    private final boolean isClassActivityStackAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("Class ActivityStackAttributes is not valid", new Function0() { // from class: loc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassActivityStackAttributesValid$lambda$60());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassActivityStackAttributesValid$lambda$60() throws NoSuchMethodException {
        Method method = ActivityStackAttributes.class.getMethod("getRelativeBounds", null);
        Method method2 = ActivityStackAttributes.class.getMethod("getWindowAttributes", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method) || !reflectionUtils.doesReturn$window_release(method, Rect.class)) {
            return false;
        }
        method2.getClass();
        return reflectionUtils.isPublic$window_release(method2) && reflectionUtils.doesReturn$window_release(method2, WindowAttributes.class);
    }

    private final boolean isClassActivityStackTokenValid() {
        return ReflectionUtils.validateReflection$window_release("Class ActivityStack.Token is not valid", new Function0() { // from class: uqc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassActivityStackTokenValid$lambda$36());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassActivityStackTokenValid$lambda$36() throws NoSuchFieldException, NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.ActivityStack.Token.class.getMethod("toBundle", null);
        Method method2 = androidx.window.extensions.embedding.ActivityStack.Token.class.getMethod("readFromBundle", Bundle.class);
        Method method3 = androidx.window.extensions.embedding.ActivityStack.Token.class.getMethod("createFromBinder", IBinder.class);
        Field declaredField = androidx.window.extensions.embedding.ActivityStack.Token.class.getDeclaredField("INVALID_ACTIVITY_STACK_TOKEN");
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method) || !reflectionUtils.doesReturn$window_release(method, Bundle.class)) {
            return false;
        }
        method2.getClass();
        if (!reflectionUtils.isPublic$window_release(method2) || !reflectionUtils.doesReturn$window_release(method2, androidx.window.extensions.embedding.ActivityStack.Token.class)) {
            return false;
        }
        method3.getClass();
        if (!reflectionUtils.isPublic$window_release(method3) || !reflectionUtils.doesReturn$window_release(method3, androidx.window.extensions.embedding.ActivityStack.Token.class)) {
            return false;
        }
        declaredField.getClass();
        return reflectionUtils.isPublic$window_release(declaredField);
    }

    private final boolean isClassAnimationBackgroundValid() {
        return ReflectionUtils.validateReflection$window_release("Class AnimationBackground is not valid", new Function0() { // from class: qpc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassAnimationBackgroundValid$lambda$35());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassAnimationBackgroundValid$lambda$35() throws NoSuchFieldException, NoSuchMethodException {
        Class<?> cls = Integer.TYPE;
        Method method = AnimationBackground.class.getMethod("createColorBackground", cls);
        Field declaredField = AnimationBackground.class.getDeclaredField("ANIMATION_BACKGROUND_DEFAULT");
        Method method2 = AnimationBackground.ColorBackground.class.getMethod("getColor", null);
        Method method3 = androidx.window.extensions.embedding.SplitAttributes.class.getMethod("getAnimationBackground", null);
        Method method4 = androidx.window.extensions.embedding.SplitAttributes.Builder.class.getMethod("setAnimationBackground", AnimationBackground.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method) || !reflectionUtils.doesReturn$window_release(method, AnimationBackground.ColorBackground.class)) {
            return false;
        }
        declaredField.getClass();
        if (!reflectionUtils.isPublic$window_release(declaredField)) {
            return false;
        }
        method2.getClass();
        if (!reflectionUtils.isPublic$window_release(method2) || !reflectionUtils.doesReturn$window_release(method2, cls)) {
            return false;
        }
        method3.getClass();
        if (!reflectionUtils.isPublic$window_release(method3) || !reflectionUtils.doesReturn$window_release(method3, AnimationBackground.class)) {
            return false;
        }
        method4.getClass();
        return reflectionUtils.isPublic$window_release(method4) && reflectionUtils.doesReturn$window_release(method4, androidx.window.extensions.embedding.SplitAttributes.Builder.class);
    }

    private final boolean isClassAnimationParamsBuilderValid() {
        return ReflectionUtils.validateReflection$window_release("Class AnimationParams.Builder is not valid", new Function0() { // from class: opc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassAnimationParamsBuilderValid$lambda$52());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassAnimationParamsBuilderValid$lambda$52() throws NoSuchMethodException {
        Method method = AnimationParams.Builder.class.getMethod("setAnimationBackground", AnimationBackground.class);
        Class cls = Integer.TYPE;
        Method method2 = AnimationParams.Builder.class.getMethod("setOpenAnimationResId", cls);
        Method method3 = AnimationParams.Builder.class.getMethod("setCloseAnimationResId", cls);
        Method method4 = AnimationParams.Builder.class.getMethod("setChangeAnimationResId", cls);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method) || !reflectionUtils.doesReturn$window_release(method, AnimationParams.Builder.class)) {
            return false;
        }
        method2.getClass();
        if (!reflectionUtils.isPublic$window_release(method2) || !reflectionUtils.doesReturn$window_release(method2, AnimationParams.Builder.class)) {
            return false;
        }
        method3.getClass();
        if (!reflectionUtils.isPublic$window_release(method3) || !reflectionUtils.doesReturn$window_release(method3, AnimationParams.Builder.class)) {
            return false;
        }
        method4.getClass();
        return reflectionUtils.isPublic$window_release(method4) && reflectionUtils.doesReturn$window_release(method4, AnimationParams.Builder.class);
    }

    private final boolean isClassAnimationParamsValid() {
        return ReflectionUtils.validateReflection$window_release("Class AnimationParams is not valid", new Function0() { // from class: spc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassAnimationParamsValid$lambda$51());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassAnimationParamsValid$lambda$51() throws NoSuchFieldException, NoSuchMethodException {
        Field declaredField = AnimationParams.class.getDeclaredField("DEFAULT_ANIMATION_RESOURCES_ID");
        Method method = AnimationParams.class.getMethod("getAnimationBackground", null);
        Method method2 = AnimationParams.class.getMethod("getOpenAnimationResId", null);
        Method method3 = AnimationParams.class.getMethod("getCloseAnimationResId", null);
        Method method4 = AnimationParams.class.getMethod("getChangeAnimationResId", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        declaredField.getClass();
        if (!reflectionUtils.isPublic$window_release(declaredField)) {
            return false;
        }
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method) || !reflectionUtils.doesReturn$window_release(method, AnimationBackground.class)) {
            return false;
        }
        method2.getClass();
        if (!reflectionUtils.isPublic$window_release(method2)) {
            return false;
        }
        Class<?> cls = Integer.TYPE;
        if (!reflectionUtils.doesReturn$window_release(method2, cls)) {
            return false;
        }
        method3.getClass();
        if (!reflectionUtils.isPublic$window_release(method3) || !reflectionUtils.doesReturn$window_release(method3, cls)) {
            return false;
        }
        method4.getClass();
        return reflectionUtils.isPublic$window_release(method4) && reflectionUtils.doesReturn$window_release(method4, cls);
    }

    private final boolean isClassDividerAttributesBuilderValid() {
        return ReflectionUtils.validateReflection$window_release("Class DividerAttributes.Builder is not valid", new Function0() { // from class: lpc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassDividerAttributesBuilderValid$lambda$46());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassDividerAttributesBuilderValid$lambda$46() throws NoSuchMethodException {
        Class cls = Integer.TYPE;
        Constructor<?> declaredConstructor = androidx.window.extensions.embedding.DividerAttributes.Builder.class.getDeclaredConstructor(cls);
        Constructor<?> declaredConstructor2 = androidx.window.extensions.embedding.DividerAttributes.Builder.class.getDeclaredConstructor(androidx.window.extensions.embedding.DividerAttributes.class);
        Method method = androidx.window.extensions.embedding.DividerAttributes.Builder.class.getMethod("setWidthDp", cls);
        Class cls2 = Float.TYPE;
        Method method2 = androidx.window.extensions.embedding.DividerAttributes.Builder.class.getMethod("setPrimaryMinRatio", cls2);
        Method method3 = androidx.window.extensions.embedding.DividerAttributes.Builder.class.getMethod("setPrimaryMaxRatio", cls2);
        Method method4 = androidx.window.extensions.embedding.DividerAttributes.Builder.class.getMethod("setDividerColor", cls);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        declaredConstructor.getClass();
        if (!reflectionUtils.isPublic$window_release(declaredConstructor)) {
            return false;
        }
        declaredConstructor2.getClass();
        if (!reflectionUtils.isPublic$window_release(declaredConstructor2)) {
            return false;
        }
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method) || !reflectionUtils.doesReturn$window_release(method, androidx.window.extensions.embedding.DividerAttributes.Builder.class)) {
            return false;
        }
        method2.getClass();
        if (!reflectionUtils.isPublic$window_release(method2) || !reflectionUtils.doesReturn$window_release(method2, androidx.window.extensions.embedding.DividerAttributes.Builder.class)) {
            return false;
        }
        method3.getClass();
        if (!reflectionUtils.isPublic$window_release(method3) || !reflectionUtils.doesReturn$window_release(method3, androidx.window.extensions.embedding.DividerAttributes.Builder.class)) {
            return false;
        }
        method4.getClass();
        return reflectionUtils.isPublic$window_release(method4) && reflectionUtils.doesReturn$window_release(method4, androidx.window.extensions.embedding.DividerAttributes.Builder.class);
    }

    private final boolean isClassDividerAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("Class DividerAttributes is not valid", new Function0() { // from class: jqc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassDividerAttributesValid$lambda$45());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassDividerAttributesValid$lambda$45() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.DividerAttributes.class.getMethod("getDividerType", null);
        Method method2 = androidx.window.extensions.embedding.DividerAttributes.class.getMethod("getWidthDp", null);
        Method method3 = androidx.window.extensions.embedding.DividerAttributes.class.getMethod("getPrimaryMinRatio", null);
        Method method4 = androidx.window.extensions.embedding.DividerAttributes.class.getMethod("getPrimaryMaxRatio", null);
        Method method5 = androidx.window.extensions.embedding.DividerAttributes.class.getMethod("getDividerColor", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method)) {
            return false;
        }
        Class<?> cls = Integer.TYPE;
        if (!reflectionUtils.doesReturn$window_release(method, cls)) {
            return false;
        }
        method2.getClass();
        if (!reflectionUtils.isPublic$window_release(method2) || !reflectionUtils.doesReturn$window_release(method2, cls)) {
            return false;
        }
        method3.getClass();
        if (!reflectionUtils.isPublic$window_release(method3)) {
            return false;
        }
        Class<?> cls2 = Float.TYPE;
        if (!reflectionUtils.doesReturn$window_release(method3, cls2)) {
            return false;
        }
        method4.getClass();
        if (!reflectionUtils.isPublic$window_release(method4) || !reflectionUtils.doesReturn$window_release(method4, cls2)) {
            return false;
        }
        method5.getClass();
        return reflectionUtils.isPublic$window_release(method5) && reflectionUtils.doesReturn$window_release(method5, cls);
    }

    private final boolean isClassEmbeddedActivityWindowInfoValid() {
        return ReflectionUtils.validateReflection$window_release("Class EmbeddedActivityWindowInfo is not valid", new Function0() { // from class: dqc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassEmbeddedActivityWindowInfoValid$lambda$44());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassEmbeddedActivityWindowInfoValid$lambda$44() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.EmbeddedActivityWindowInfo.class.getMethod("getActivity", null);
        Method method2 = androidx.window.extensions.embedding.EmbeddedActivityWindowInfo.class.getMethod("isEmbedded", null);
        Method method3 = androidx.window.extensions.embedding.EmbeddedActivityWindowInfo.class.getMethod("getTaskBounds", null);
        Method method4 = androidx.window.extensions.embedding.EmbeddedActivityWindowInfo.class.getMethod("getActivityStackBounds", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method) || !reflectionUtils.doesReturn$window_release(method, Activity.class)) {
            return false;
        }
        method2.getClass();
        if (!reflectionUtils.isPublic$window_release(method2) || !reflectionUtils.doesReturn$window_release(method2, Boolean.TYPE)) {
            return false;
        }
        method3.getClass();
        if (!reflectionUtils.isPublic$window_release(method3) || !reflectionUtils.doesReturn$window_release(method3, Rect.class)) {
            return false;
        }
        method4.getClass();
        return reflectionUtils.isPublic$window_release(method4) && reflectionUtils.doesReturn$window_release(method4, Rect.class);
    }

    private final boolean isClassEmbeddingRuleValid() {
        return ReflectionUtils.validateReflection$window_release("Class EmbeddingRule is not valid", new Function0() { // from class: soc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.k());
            }
        });
    }

    private final boolean isClassParentContainerInfoValid() {
        return ReflectionUtils.validateReflection$window_release("ParentContainerInfo is not valid", new Function0() { // from class: fqc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassParentContainerInfoValid$lambda$55());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassParentContainerInfoValid$lambda$55() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.ParentContainerInfo.class.getMethod("getWindowMetrics", null);
        Method method2 = androidx.window.extensions.embedding.ParentContainerInfo.class.getMethod("getConfiguration", null);
        Method method3 = androidx.window.extensions.embedding.ParentContainerInfo.class.getMethod("getWindowLayoutInfo", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method) || !reflectionUtils.doesReturn$window_release(method, WindowMetrics.class)) {
            return false;
        }
        method2.getClass();
        if (!reflectionUtils.isPublic$window_release(method2) || !reflectionUtils.doesReturn$window_release(method2, Configuration.class)) {
            return false;
        }
        method3.getClass();
        return reflectionUtils.isPublic$window_release(method3) && reflectionUtils.doesReturn$window_release(method3, WindowLayoutInfo.class);
    }

    private final boolean isClassSplitAttributesCalculatorParamsValid() {
        return ReflectionUtils.validateReflection$window_release("Class SplitAttributesCalculatorParams is not valid", new Function0() { // from class: epc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitAttributesCalculatorParamsValid$lambda$22());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassSplitAttributesCalculatorParamsValid$lambda$22() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.SplitAttributesCalculatorParams.class.getMethod("getParentWindowMetrics", null);
        Method method2 = androidx.window.extensions.embedding.SplitAttributesCalculatorParams.class.getMethod("getParentConfiguration", null);
        Method method3 = androidx.window.extensions.embedding.SplitAttributesCalculatorParams.class.getMethod("getDefaultSplitAttributes", null);
        Method method4 = androidx.window.extensions.embedding.SplitAttributesCalculatorParams.class.getMethod("areDefaultConstraintsSatisfied", null);
        Method method5 = androidx.window.extensions.embedding.SplitAttributesCalculatorParams.class.getMethod("getParentWindowLayoutInfo", null);
        Method method6 = androidx.window.extensions.embedding.SplitAttributesCalculatorParams.class.getMethod("getSplitRuleTag", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method) || !reflectionUtils.doesReturn$window_release(method, WindowMetrics.class)) {
            return false;
        }
        method2.getClass();
        if (!reflectionUtils.isPublic$window_release(method2) || !reflectionUtils.doesReturn$window_release(method2, Configuration.class)) {
            return false;
        }
        method3.getClass();
        if (!reflectionUtils.isPublic$window_release(method3) || !reflectionUtils.doesReturn$window_release(method3, androidx.window.extensions.embedding.SplitAttributes.class)) {
            return false;
        }
        method4.getClass();
        if (!reflectionUtils.isPublic$window_release(method4) || !reflectionUtils.doesReturn$window_release(method4, Boolean.TYPE)) {
            return false;
        }
        method5.getClass();
        if (!reflectionUtils.isPublic$window_release(method5) || !reflectionUtils.doesReturn$window_release(method5, WindowLayoutInfo.class)) {
            return false;
        }
        method6.getClass();
        return reflectionUtils.isPublic$window_release(method6) && reflectionUtils.doesReturn$window_release(method6, String.class);
    }

    private final boolean isClassSplitAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("Class SplitAttributes is not valid", new Function0() { // from class: tpc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitAttributesValid$lambda$21());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassSplitAttributesValid$lambda$21() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.SplitAttributes.class.getMethod("getLayoutDirection", null);
        Method method2 = androidx.window.extensions.embedding.SplitAttributes.class.getMethod("getSplitType", null);
        Method method3 = androidx.window.extensions.embedding.SplitAttributes.Builder.class.getMethod("setSplitType", androidx.window.extensions.embedding.SplitAttributes.SplitType.class);
        Class<?> cls = Integer.TYPE;
        Method method4 = androidx.window.extensions.embedding.SplitAttributes.Builder.class.getMethod("setLayoutDirection", cls);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method) || !reflectionUtils.doesReturn$window_release(method, cls)) {
            return false;
        }
        method2.getClass();
        if (!reflectionUtils.isPublic$window_release(method2) || !reflectionUtils.doesReturn$window_release(method2, androidx.window.extensions.embedding.SplitAttributes.SplitType.class)) {
            return false;
        }
        method3.getClass();
        if (!reflectionUtils.isPublic$window_release(method3)) {
            return false;
        }
        method4.getClass();
        return reflectionUtils.isPublic$window_release(method4);
    }

    private final boolean isClassSplitInfoTokenValid() {
        return ReflectionUtils.validateReflection$window_release("SplitInfo.Token is not valid", new Function0() { // from class: ipc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitInfoTokenValid$lambda$38());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassSplitInfoTokenValid$lambda$38() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.SplitInfo.Token.class.getMethod("createFromBinder", IBinder.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, androidx.window.extensions.embedding.SplitInfo.Token.class);
    }

    private final boolean isClassSplitInfoValid() {
        return ReflectionUtils.validateReflection$window_release("Class SplitInfo is not valid", new Function0() { // from class: cpc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitInfoValid$lambda$8());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassSplitInfoValid$lambda$8() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.SplitInfo.class.getMethod("getPrimaryActivityStack", null);
        Method method2 = androidx.window.extensions.embedding.SplitInfo.class.getMethod("getSecondaryActivityStack", null);
        Method method3 = androidx.window.extensions.embedding.SplitInfo.class.getMethod("getSplitRatio", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method) || !reflectionUtils.doesReturn$window_release(method, androidx.window.extensions.embedding.ActivityStack.class)) {
            return false;
        }
        method2.getClass();
        if (!reflectionUtils.isPublic$window_release(method2) || !reflectionUtils.doesReturn$window_release(method2, androidx.window.extensions.embedding.ActivityStack.class)) {
            return false;
        }
        method3.getClass();
        return reflectionUtils.isPublic$window_release(method3) && reflectionUtils.doesReturn$window_release(method3, Float.TYPE);
    }

    private final boolean isClassSplitPairRuleBuilderLevel1Valid() {
        return ReflectionUtils.validateReflection$window_release("Class SplitPairRule.Builder is not valid", new Function0() { // from class: moc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitPairRuleBuilderLevel1Valid$lambda$10());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassSplitPairRuleBuilderLevel1Valid$lambda$10() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.SplitPairRule.Builder.class.getMethod("setSplitRatio", Float.TYPE);
        Method method2 = androidx.window.extensions.embedding.SplitPairRule.Builder.class.getMethod("setLayoutDirection", Integer.TYPE);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method) || !reflectionUtils.doesReturn$window_release(method, androidx.window.extensions.embedding.SplitPairRule.Builder.class)) {
            return false;
        }
        method2.getClass();
        return reflectionUtils.isPublic$window_release(method2) && reflectionUtils.doesReturn$window_release(method2, androidx.window.extensions.embedding.SplitPairRule.Builder.class);
    }

    private final boolean isClassSplitPairRuleBuilderLevel2Valid() {
        return ReflectionUtils.validateReflection$window_release("Class SplitPairRule.Builder is not valid", new Function0() { // from class: koc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitPairRuleBuilderLevel2Valid$lambda$24());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassSplitPairRuleBuilderLevel2Valid$lambda$24() throws NoSuchMethodException {
        Constructor<?> declaredConstructor = androidx.window.extensions.embedding.SplitPairRule.Builder.class.getDeclaredConstructor(Predicate.class, Predicate.class, Predicate.class);
        Method method = androidx.window.extensions.embedding.SplitPairRule.Builder.class.getMethod("setDefaultSplitAttributes", androidx.window.extensions.embedding.SplitAttributes.class);
        Method method2 = androidx.window.extensions.embedding.SplitPairRule.Builder.class.getMethod("setTag", String.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        declaredConstructor.getClass();
        if (!reflectionUtils.isPublic$window_release(declaredConstructor)) {
            return false;
        }
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method) || !reflectionUtils.doesReturn$window_release(method, androidx.window.extensions.embedding.SplitPairRule.Builder.class)) {
            return false;
        }
        method2.getClass();
        return reflectionUtils.isPublic$window_release(method2) && reflectionUtils.doesReturn$window_release(method2, androidx.window.extensions.embedding.SplitPairRule.Builder.class);
    }

    private final boolean isClassSplitPairRuleValid() {
        return ReflectionUtils.validateReflection$window_release("Class SplitPairRule is not valid", new Function0() { // from class: rqc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitPairRuleValid$lambda$9());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassSplitPairRuleValid$lambda$9() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.SplitPairRule.class.getMethod("getFinishPrimaryWithSecondary", null);
        Method method2 = androidx.window.extensions.embedding.SplitPairRule.class.getMethod("getFinishSecondaryWithPrimary", null);
        Method method3 = androidx.window.extensions.embedding.SplitPairRule.class.getMethod("shouldClearTop", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method)) {
            return false;
        }
        Class<?> cls = Integer.TYPE;
        if (!reflectionUtils.doesReturn$window_release(method, cls)) {
            return false;
        }
        method2.getClass();
        if (!reflectionUtils.isPublic$window_release(method2) || !reflectionUtils.doesReturn$window_release(method2, cls)) {
            return false;
        }
        method3.getClass();
        return reflectionUtils.isPublic$window_release(method3) && reflectionUtils.doesReturn$window_release(method3, Boolean.TYPE);
    }

    private final boolean isClassSplitPlaceholderRuleBuilderLevel1Valid() {
        return ReflectionUtils.validateReflection$window_release("Class SplitPlaceholderRule.Builder is not valid", new Function0() { // from class: yoc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitPlaceholderRuleBuilderLevel1Valid$lambda$12());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassSplitPlaceholderRuleBuilderLevel1Valid$lambda$12() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.SplitPlaceholderRule.Builder.class.getMethod("setSplitRatio", Float.TYPE);
        Class cls = Integer.TYPE;
        Method method2 = androidx.window.extensions.embedding.SplitPlaceholderRule.Builder.class.getMethod("setLayoutDirection", cls);
        Method method3 = androidx.window.extensions.embedding.SplitPlaceholderRule.Builder.class.getMethod("setSticky", Boolean.TYPE);
        Method method4 = androidx.window.extensions.embedding.SplitPlaceholderRule.Builder.class.getMethod("setFinishPrimaryWithSecondary", cls);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method) || !reflectionUtils.doesReturn$window_release(method, androidx.window.extensions.embedding.SplitPlaceholderRule.Builder.class)) {
            return false;
        }
        method2.getClass();
        if (!reflectionUtils.isPublic$window_release(method2) || !reflectionUtils.doesReturn$window_release(method2, androidx.window.extensions.embedding.SplitPlaceholderRule.Builder.class)) {
            return false;
        }
        method3.getClass();
        if (!reflectionUtils.isPublic$window_release(method3) || !reflectionUtils.doesReturn$window_release(method3, androidx.window.extensions.embedding.SplitPlaceholderRule.Builder.class)) {
            return false;
        }
        method4.getClass();
        return reflectionUtils.isPublic$window_release(method4) && reflectionUtils.doesReturn$window_release(method4, androidx.window.extensions.embedding.SplitPlaceholderRule.Builder.class);
    }

    private final boolean isClassSplitPlaceholderRuleBuilderLevel2Valid() {
        return ReflectionUtils.validateReflection$window_release("Class SplitPlaceholderRule.Builder is not valid", new Function0() { // from class: eqc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitPlaceholderRuleBuilderLevel2Valid$lambda$25());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassSplitPlaceholderRuleBuilderLevel2Valid$lambda$25() throws NoSuchMethodException {
        Constructor<?> declaredConstructor = androidx.window.extensions.embedding.SplitPlaceholderRule.Builder.class.getDeclaredConstructor(Intent.class, Predicate.class, Predicate.class, Predicate.class);
        Method method = androidx.window.extensions.embedding.SplitPlaceholderRule.Builder.class.getMethod("setDefaultSplitAttributes", androidx.window.extensions.embedding.SplitAttributes.class);
        Method method2 = androidx.window.extensions.embedding.SplitPlaceholderRule.Builder.class.getMethod("setFinishPrimaryWithPlaceholder", Integer.TYPE);
        Method method3 = androidx.window.extensions.embedding.SplitPlaceholderRule.Builder.class.getMethod("setTag", String.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        declaredConstructor.getClass();
        if (!reflectionUtils.isPublic$window_release(declaredConstructor)) {
            return false;
        }
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method) || !reflectionUtils.doesReturn$window_release(method, androidx.window.extensions.embedding.SplitPlaceholderRule.Builder.class)) {
            return false;
        }
        method2.getClass();
        if (!reflectionUtils.isPublic$window_release(method2) || !reflectionUtils.doesReturn$window_release(method2, androidx.window.extensions.embedding.SplitPlaceholderRule.Builder.class)) {
            return false;
        }
        method3.getClass();
        return reflectionUtils.isPublic$window_release(method3) && reflectionUtils.doesReturn$window_release(method3, androidx.window.extensions.embedding.SplitPlaceholderRule.Builder.class);
    }

    private final boolean isClassSplitPlaceholderRuleValid() {
        return ReflectionUtils.validateReflection$window_release("Class SplitPlaceholderRule is not valid", new Function0() { // from class: cqc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitPlaceholderRuleValid$lambda$11());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassSplitPlaceholderRuleValid$lambda$11() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.SplitPlaceholderRule.class.getMethod("getPlaceholderIntent", null);
        Method method2 = androidx.window.extensions.embedding.SplitPlaceholderRule.class.getMethod("isSticky", null);
        Method method3 = androidx.window.extensions.embedding.SplitPlaceholderRule.class.getMethod("getFinishPrimaryWithSecondary", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method) || !reflectionUtils.doesReturn$window_release(method, Intent.class)) {
            return false;
        }
        method2.getClass();
        if (!reflectionUtils.isPublic$window_release(method2) || !reflectionUtils.doesReturn$window_release(method2, Boolean.TYPE)) {
            return false;
        }
        method3.getClass();
        return reflectionUtils.isPublic$window_release(method3) && reflectionUtils.doesReturn$window_release(method3, Integer.TYPE);
    }

    private final boolean isClassSplitTypeValid() {
        return ReflectionUtils.validateReflection$window_release("Class SplitAttributes.SplitType is not valid", new Function0() { // from class: upc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassSplitTypeValid$lambda$23());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassSplitTypeValid$lambda$23() throws NoSuchMethodException {
        Class<?> cls = Float.TYPE;
        Constructor<?> declaredConstructor = androidx.window.extensions.embedding.SplitAttributes.SplitType.RatioSplitType.class.getDeclaredConstructor(cls);
        Method method = androidx.window.extensions.embedding.SplitAttributes.SplitType.RatioSplitType.class.getMethod("getRatio", null);
        Method method2 = androidx.window.extensions.embedding.SplitAttributes.SplitType.RatioSplitType.class.getMethod("splitEqually", null);
        Constructor<?> declaredConstructor2 = androidx.window.extensions.embedding.SplitAttributes.SplitType.HingeSplitType.class.getDeclaredConstructor(androidx.window.extensions.embedding.SplitAttributes.SplitType.class);
        Method method3 = androidx.window.extensions.embedding.SplitAttributes.SplitType.HingeSplitType.class.getMethod("getFallbackSplitType", null);
        Constructor<?> declaredConstructor3 = androidx.window.extensions.embedding.SplitAttributes.SplitType.ExpandContainersSplitType.class.getDeclaredConstructor(null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        declaredConstructor.getClass();
        if (!reflectionUtils.isPublic$window_release(declaredConstructor)) {
            return false;
        }
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method) || !reflectionUtils.doesReturn$window_release(method, cls)) {
            return false;
        }
        declaredConstructor2.getClass();
        if (!reflectionUtils.isPublic$window_release(declaredConstructor2)) {
            return false;
        }
        method2.getClass();
        if (!reflectionUtils.isPublic$window_release(method2) || !reflectionUtils.doesReturn$window_release(method2, androidx.window.extensions.embedding.SplitAttributes.SplitType.RatioSplitType.class)) {
            return false;
        }
        method3.getClass();
        if (!reflectionUtils.isPublic$window_release(method3) || !reflectionUtils.doesReturn$window_release(method3, androidx.window.extensions.embedding.SplitAttributes.SplitType.class)) {
            return false;
        }
        declaredConstructor3.getClass();
        return reflectionUtils.isPublic$window_release(declaredConstructor3);
    }

    private final boolean isClassWindowAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("Class WindowAttributes is not valid", new Function0() { // from class: hpc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isClassWindowAttributesValid$lambda$37());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isClassWindowAttributesValid$lambda$37() throws NoSuchMethodException {
        Method method = WindowAttributes.class.getMethod("getDimAreaBehavior", null);
        Method method2 = androidx.window.extensions.embedding.SplitAttributes.class.getMethod("getWindowAttributes", null);
        Method method3 = androidx.window.extensions.embedding.SplitAttributes.Builder.class.getMethod("setWindowAttributes", WindowAttributes.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method) || !reflectionUtils.doesReturn$window_release(method, Integer.TYPE)) {
            return false;
        }
        method2.getClass();
        if (!reflectionUtils.isPublic$window_release(method2) || !reflectionUtils.doesReturn$window_release(method2, WindowAttributes.class)) {
            return false;
        }
        method3.getClass();
        return reflectionUtils.isPublic$window_release(method3) && reflectionUtils.doesReturn$window_release(method3, androidx.window.extensions.embedding.SplitAttributes.Builder.class);
    }

    private final boolean isMethodClearActivityStackAttributesCalculatorValid() {
        return ReflectionUtils.validateReflection$window_release("clearActivityStackAttributesCalculator is not valid", new Function0() { // from class: qoc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.Q(this.b));
            }
        });
    }

    private final boolean isMethodClearEmbeddedActivityWindowInfoCallbackValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#clearEmbeddedActivityWindowInfoCallback is not valid", new Function0() { // from class: roc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.n(this.b));
            }
        });
    }

    private final boolean isMethodClearSplitInfoCallbackValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#clearSplitInfoCallback is not valid", new Function0() { // from class: fpc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.s(this.b));
            }
        });
    }

    private final boolean isMethodGetActivityStackTokenValid() {
        return ReflectionUtils.validateReflection$window_release("getActivityStackToken is not valid", new Function0() { // from class: lqc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetActivityStackTokenValid$lambda$54(this.b));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodGetActivityStackTokenValid$lambda$54(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) throws NoSuchMethodException {
        Method method = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass().getMethod("getActivityStackToken", String.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, androidx.window.extensions.embedding.ActivityStack.Token.class);
    }

    private final boolean isMethodGetAnimationParamsValid() {
        return ReflectionUtils.validateReflection$window_release("SplitAttributes#getAnimationParams is not valid", new Function0() { // from class: zoc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetAnimationParamsValid$lambda$47());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodGetAnimationParamsValid$lambda$47() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.SplitAttributes.class.getMethod("getAnimationParams", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, AnimationParams.class);
    }

    private final boolean isMethodGetDefaultSplitAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("SplitRule#getDefaultSplitAttributes is not valid", new Function0() { // from class: xpc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetDefaultSplitAttributesValid$lambda$18());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodGetDefaultSplitAttributesValid$lambda$18() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.SplitRule.class.getMethod("getDefaultSplitAttributes", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, androidx.window.extensions.embedding.SplitAttributes.class);
    }

    private final boolean isMethodGetDividerAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("SplitAttributes#getDividerAttributes is not valid", new Function0() { // from class: gqc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetDividerAttributesValid$lambda$42());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodGetDividerAttributesValid$lambda$42() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.SplitAttributes.class.getMethod("getDividerAttributes", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, androidx.window.extensions.embedding.DividerAttributes.class);
    }

    private final boolean isMethodGetEmbeddedActivityWindowInfoValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#getEmbeddedActivityWindowInfo is not valid", new Function0() { // from class: dpc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetEmbeddedActivityWindowInfoValid$lambda$39(this.b));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodGetEmbeddedActivityWindowInfoValid$lambda$39(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) throws NoSuchMethodException {
        Method method = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass().getMethod("getEmbeddedActivityWindowInfo", Activity.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, androidx.window.extensions.embedding.EmbeddedActivityWindowInfo.class);
    }

    private final boolean isMethodGetFinishPrimaryWithPlaceholderValid() {
        return ReflectionUtils.validateReflection$window_release("SplitPlaceholderRule#getFinishPrimaryWithPlaceholder is not valid", new Function0() { // from class: woc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetFinishPrimaryWithPlaceholderValid$lambda$17());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodGetFinishPrimaryWithPlaceholderValid$lambda$17() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.SplitPlaceholderRule.class.getMethod("getFinishPrimaryWithPlaceholder", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, Integer.TYPE);
    }

    private final boolean isMethodGetLayoutDirectionValid() {
        return ReflectionUtils.validateReflection$window_release("SplitRule#getLayoutDirection is not valid", new Function0() { // from class: iqc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetLayoutDirectionValid$lambda$5());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodGetLayoutDirectionValid$lambda$5() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.SplitRule.class.getMethod("getLayoutDirection", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, Integer.TYPE);
    }

    private final boolean isMethodGetParentContainerInfoValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#getParentContainerInfo is not valid", new Function0() { // from class: hqc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetParentContainerInfoValid$lambda$56(this.b));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodGetParentContainerInfoValid$lambda$56(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) throws NoSuchMethodException {
        Method method = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass().getMethod("getParentContainerInfo", androidx.window.extensions.embedding.ActivityStack.Token.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, androidx.window.extensions.embedding.ParentContainerInfo.class);
    }

    private final boolean isMethodGetSplitAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("SplitInfo#getSplitAttributes is not valid", new Function0() { // from class: sqc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetSplitAttributesValid$lambda$16());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodGetSplitAttributesValid$lambda$16() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.SplitInfo.class.getMethod("getSplitAttributes", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, androidx.window.extensions.embedding.SplitAttributes.class);
    }

    private final boolean isMethodGetSplitInfoTokenValid() {
        return ReflectionUtils.validateReflection$window_release("SplitInfo#getSplitInfoToken is not valid", new Function0() { // from class: poc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetSplitInfoTokenValid$lambda$34());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodGetSplitInfoTokenValid$lambda$34() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.SplitInfo.class.getMethod("getSplitInfoToken", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, androidx.window.extensions.embedding.SplitInfo.Token.class);
    }

    private final boolean isMethodGetSplitRatioValid() {
        return ReflectionUtils.validateReflection$window_release("SplitRule#getSplitRatio is not valid", new Function0() { // from class: vpc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodGetSplitRatioValid$lambda$4());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodGetSplitRatioValid$lambda$4() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.SplitRule.class.getMethod("getSplitRatio", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, Float.TYPE);
    }

    private final boolean isMethodInvalidateTopVisibleSplitAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("#invalidateTopVisibleSplitAttributes is not valid", new Function0() { // from class: jpc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.k0(this.b));
            }
        });
    }

    private final boolean isMethodIsActivityEmbeddedValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#isActivityEmbedded is not valid", new Function0() { // from class: wpc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.l(this.b));
            }
        });
    }

    private final boolean isMethodIsDraggingToFullscreenAllowedValid() {
        return ReflectionUtils.validateReflection$window_release("DividerAttributes#isDraggingToFullscreenAllowed is not valid", new Function0() { // from class: bqc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodIsDraggingToFullscreenAllowedValid$lambda$49());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodIsDraggingToFullscreenAllowedValid$lambda$49() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.DividerAttributes.class.getMethod("isDraggingToFullscreenAllowed", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, Boolean.TYPE);
    }

    private final boolean isMethodPinUnpinTopActivityStackValid() {
        return ReflectionUtils.validateReflection$window_release("#pin(unPin)TopActivityStack is not valid", new Function0() { // from class: ooc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodPinUnpinTopActivityStackValid$lambda$32(this.b));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodPinUnpinTopActivityStackValid$lambda$32(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) throws NoSuchMethodException, ClassNotFoundException {
        Method method = androidx.window.extensions.embedding.SplitPinRule.class.getMethod("isSticky", null);
        Class<?> activityEmbeddingComponentClass = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass();
        Class cls = Integer.TYPE;
        Method method2 = activityEmbeddingComponentClass.getMethod("pinTopActivityStack", cls, androidx.window.extensions.embedding.SplitPinRule.class);
        Method method3 = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass().getMethod("unpinTopActivityStack", cls);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method)) {
            return false;
        }
        Class<?> cls2 = Boolean.TYPE;
        if (!reflectionUtils.doesReturn$window_release(method, cls2)) {
            return false;
        }
        method2.getClass();
        if (!reflectionUtils.isPublic$window_release(method2) || !reflectionUtils.doesReturn$window_release(method2, cls2)) {
            return false;
        }
        method3.getClass();
        return reflectionUtils.isPublic$window_release(method3);
    }

    private final boolean isMethodRegisterActivityStackCallbackValid() {
        return ReflectionUtils.validateReflection$window_release("registerActivityStackCallback is not valid", new Function0() { // from class: nqc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodRegisterActivityStackCallbackValid$lambda$30(this.b));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodRegisterActivityStackCallbackValid$lambda$30(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) throws NoSuchMethodException {
        Method method = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass().getMethod("registerActivityStackCallback", Executor.class, Consumer.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method);
    }

    private final boolean isMethodSetActivityStackAttributesCalculatorValid() {
        return ReflectionUtils.validateReflection$window_release("setActivityStackAttributesCalculator is not valid", new Function0() { // from class: bpc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodSetActivityStackAttributesCalculatorValid$lambda$57(this.b));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodSetActivityStackAttributesCalculatorValid$lambda$57(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) throws NoSuchMethodException {
        Method method = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass().getMethod("setActivityStackAttributesCalculator", Function.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method);
    }

    private final boolean isMethodSetAnimationParamsValid() {
        return ReflectionUtils.validateReflection$window_release("SplitAttributes#setAnimationParams is not valid", new Function0() { // from class: qqc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodSetAnimationParamsValid$lambda$48());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodSetAnimationParamsValid$lambda$48() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.SplitAttributes.Builder.class.getMethod("setAnimationParams", AnimationParams.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, androidx.window.extensions.embedding.SplitAttributes.Builder.class);
    }

    private final boolean isMethodSetDividerAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("SplitAttributes#setDividerAttributes is not valid", new Function0() { // from class: npc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodSetDividerAttributesValid$lambda$43());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodSetDividerAttributesValid$lambda$43() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.SplitAttributes.Builder.class.getMethod("setDividerAttributes", androidx.window.extensions.embedding.DividerAttributes.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, androidx.window.extensions.embedding.SplitAttributes.Builder.class);
    }

    private final boolean isMethodSetDraggingToFullscreenAllowedValid() {
        return ReflectionUtils.validateReflection$window_release("DividerAttributes.Builder#setDraggingToFullscreenAllowed is not valid", new Function0() { // from class: apc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodSetDraggingToFullscreenAllowedValid$lambda$50());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodSetDraggingToFullscreenAllowedValid$lambda$50() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.DividerAttributes.Builder.class.getMethod("setDraggingToFullscreenAllowed", Boolean.TYPE);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, androidx.window.extensions.embedding.DividerAttributes.Builder.class);
    }

    private final boolean isMethodSetEmbeddedActivityWindowInfoCallbackValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#setEmbeddedActivityWindowInfoCallback is not valid", new Function0() { // from class: noc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodSetEmbeddedActivityWindowInfoCallbackValid$lambda$40(this.b));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodSetEmbeddedActivityWindowInfoCallbackValid$lambda$40(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) throws NoSuchMethodException {
        Method method = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass().getMethod("setEmbeddedActivityWindowInfoCallback", Executor.class, Consumer.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method);
    }

    private final boolean isMethodSetEmbeddingRulesValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#setEmbeddingRules is not valid", new Function0() { // from class: kqc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.L(this.b));
            }
        });
    }

    private final boolean isMethodSetSplitInfoCallbackJavaConsumerValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#setSplitInfoCallback is not valid", new Function0() { // from class: ypc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.F(this.b));
            }
        });
    }

    private final boolean isMethodSetSplitInfoCallbackWindowConsumerValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#setSplitInfoCallback is not valid", new Function0() { // from class: zpc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodSetSplitInfoCallbackWindowConsumerValid$lambda$13(this.b));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodSetSplitInfoCallbackWindowConsumerValid$lambda$13(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) throws NoSuchMethodException {
        Method method = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass().getMethod("setSplitInfoCallback", Consumer.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method);
    }

    private final boolean isMethodSplitAttributesCalculatorValid() {
        return ReflectionUtils.validateReflection$window_release("ActivityEmbeddingComponent#setSplitAttributesCalculator is not valid", new Function0() { // from class: mqc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodSplitAttributesCalculatorValid$lambda$15(this.b));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodSplitAttributesCalculatorValid$lambda$15(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) throws NoSuchMethodException {
        Method method = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass().getMethod("setSplitAttributesCalculator", Function.class);
        Method method2 = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass().getMethod("clearSplitAttributesCalculator", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        if (!reflectionUtils.isPublic$window_release(method)) {
            return false;
        }
        method2.getClass();
        return reflectionUtils.isPublic$window_release(method2);
    }

    private final boolean isMethodSplitInfoGetTokenValid() {
        return ReflectionUtils.validateReflection$window_release("SplitInfo#getToken is not valid", new Function0() { // from class: uoc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodSplitInfoGetTokenValid$lambda$28());
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodSplitInfoGetTokenValid$lambda$28() throws NoSuchMethodException {
        Method method = androidx.window.extensions.embedding.SplitInfo.class.getMethod("getToken", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, IBinder.class);
    }

    private final boolean isMethodUnregisterActivityStackCallbackValid() {
        return ReflectionUtils.validateReflection$window_release("unregisterActivityStackCallback is not valid", new Function0() { // from class: tqc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodUnregisterActivityStackCallbackValid$lambda$31(this.b));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodUnregisterActivityStackCallbackValid$lambda$31(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) throws NoSuchMethodException {
        Method method = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass().getMethod("unregisterActivityStackCallback", Consumer.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method);
    }

    private final boolean isMethodUpdateActivityStackAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("updateActivityStackAttributes is not valid", new Function0() { // from class: toc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodUpdateActivityStackAttributesValid$lambda$59(this.b));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodUpdateActivityStackAttributesValid$lambda$59(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) throws NoSuchMethodException {
        Method method = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass().getMethod("updateActivityStackAttributes", androidx.window.extensions.embedding.ActivityStack.Token.class, ActivityStackAttributes.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method);
    }

    private final boolean isMethodUpdateSplitAttributesValid() {
        return ReflectionUtils.validateReflection$window_release("#updateSplitAttributes is not valid", new Function0() { // from class: gpc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodUpdateSplitAttributesValid$lambda$27(this.b));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodUpdateSplitAttributesValid$lambda$27(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) throws NoSuchMethodException {
        Method method = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass().getMethod("updateSplitAttributes", IBinder.class, androidx.window.extensions.embedding.SplitAttributes.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method);
    }

    private final boolean isMethodUpdateSplitAttributesWithTokenValid() {
        return ReflectionUtils.validateReflection$window_release("updateSplitAttributes is not valid", new Function0() { // from class: oqc
            public final Object invoke() {
                return Boolean.valueOf(SafeActivityEmbeddingComponentProvider.isMethodUpdateSplitAttributesWithTokenValid$lambda$33(this.b));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isMethodUpdateSplitAttributesWithTokenValid$lambda$33(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) throws NoSuchMethodException {
        Method method = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass().getMethod("updateSplitAttributes", androidx.window.extensions.embedding.SplitInfo.Token.class, androidx.window.extensions.embedding.SplitAttributes.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method);
    }

    private final boolean isOverlayFeatureValid() {
        return isActivityStackGetTagValid() && isMethodGetActivityStackTokenValid() && isClassParentContainerInfoValid() && isMethodGetParentContainerInfoValid() && isMethodSetActivityStackAttributesCalculatorValid() && isMethodClearActivityStackAttributesCalculatorValid() && isMethodUpdateActivityStackAttributesValid() && isClassActivityStackAttributesValid() && isClassActivityStackAttributesBuilderValid() && isClassActivityStackAttributesCalculatorParamsValid();
    }

    public static boolean k() throws NoSuchMethodException {
        Method method = EmbeddingRule.class.getMethod("getTag", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, String.class);
    }

    public static boolean k0(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) throws NoSuchMethodException {
        Method method = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass().getMethod("invalidateTopVisibleSplitAttributes", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method);
    }

    public static boolean l(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) throws NoSuchMethodException {
        Method method = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass().getMethod("isActivityEmbedded", Activity.class);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, Boolean.TYPE);
    }

    public static boolean n(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) throws NoSuchMethodException {
        Method method = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass().getMethod("clearEmbeddedActivityWindowInfoCallback", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method);
    }

    public static boolean o(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) throws NoSuchMethodException, ClassNotFoundException {
        Method method = safeActivityEmbeddingComponentProvider.safeWindowExtensionsProvider.getWindowExtensionsClass$window_release().getMethod("getActivityEmbeddingComponent", null);
        Class<?> activityEmbeddingComponentClass = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass();
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method) && reflectionUtils.doesReturn$window_release(method, activityEmbeddingComponentClass);
    }

    public static boolean s(SafeActivityEmbeddingComponentProvider safeActivityEmbeddingComponentProvider) throws NoSuchMethodException {
        Method method = safeActivityEmbeddingComponentProvider.getActivityEmbeddingComponentClass().getMethod("clearSplitInfoCallback", null);
        ReflectionUtils reflectionUtils = ReflectionUtils.INSTANCE;
        method.getClass();
        return reflectionUtils.isPublic$window_release(method);
    }

    public final ActivityEmbeddingComponent getActivityEmbeddingComponent() {
        if (!canUseActivityEmbeddingComponent()) {
            return null;
        }
        try {
            return this.windowExtensions.getActivityEmbeddingComponent();
        } catch (UnsupportedOperationException unused) {
            return null;
        }
    }

    public final boolean hasValidVendorApiLevel1$window_release() {
        return isMethodSetEmbeddingRulesValid() && isMethodIsActivityEmbeddedValid() && isMethodSetSplitInfoCallbackJavaConsumerValid() && isMethodGetSplitRatioValid() && isMethodGetLayoutDirectionValid() && isClassActivityRuleValid() && isClassActivityRuleBuilderLevel1Valid() && isClassSplitInfoValid() && isClassSplitPairRuleValid() && isClassSplitPairRuleBuilderLevel1Valid() && isClassSplitPlaceholderRuleValid() && isClassSplitPlaceholderRuleBuilderLevel1Valid();
    }

    public final boolean hasValidVendorApiLevel2$window_release() {
        return hasValidVendorApiLevel1$window_release() && isMethodSetSplitInfoCallbackWindowConsumerValid() && isMethodClearSplitInfoCallbackValid() && isMethodSplitAttributesCalculatorValid() && isMethodGetSplitAttributesValid() && isMethodGetFinishPrimaryWithPlaceholderValid() && isMethodGetDefaultSplitAttributesValid() && isClassActivityRuleBuilderLevel2Valid() && isClassEmbeddingRuleValid() && isClassSplitAttributesValid() && isClassSplitAttributesCalculatorParamsValid() && isClassSplitTypeValid() && isClassSplitPairRuleBuilderLevel2Valid() && isClassSplitPlaceholderRuleBuilderLevel2Valid();
    }

    public final boolean hasValidVendorApiLevel3$window_release() {
        return hasValidVendorApiLevel2$window_release() && isMethodInvalidateTopVisibleSplitAttributesValid() && isMethodUpdateSplitAttributesValid() && isMethodSplitInfoGetTokenValid();
    }

    public final boolean hasValidVendorApiLevel5$window_release() {
        return hasValidVendorApiLevel3$window_release() && isActivityStackGetActivityStackTokenValid() && isMethodRegisterActivityStackCallbackValid() && isMethodUnregisterActivityStackCallbackValid() && isMethodPinUnpinTopActivityStackValid() && isMethodUpdateSplitAttributesWithTokenValid() && isMethodGetSplitInfoTokenValid() && isClassAnimationBackgroundValid() && isClassActivityStackTokenValid() && isClassWindowAttributesValid() && isClassSplitInfoTokenValid();
    }

    public final boolean hasValidVendorApiLevel6$window_release() {
        return hasValidVendorApiLevel5$window_release() && isMethodGetEmbeddedActivityWindowInfoValid() && isMethodSetEmbeddedActivityWindowInfoCallbackValid() && isMethodClearEmbeddedActivityWindowInfoCallbackValid() && isMethodGetDividerAttributesValid() && isMethodSetDividerAttributesValid() && isClassEmbeddedActivityWindowInfoValid() && isClassDividerAttributesValid() && isClassDividerAttributesBuilderValid();
    }

    public final boolean hasValidVendorApiLevel7$window_release() {
        return hasValidVendorApiLevel6$window_release() && isMethodGetAnimationParamsValid() && isMethodSetAnimationParamsValid() && isMethodIsDraggingToFullscreenAllowedValid() && isMethodSetDraggingToFullscreenAllowedValid() && isClassAnimationParamsValid() && isClassAnimationParamsBuilderValid();
    }

    public final boolean hasValidVendorApiLevel8$window_release() {
        return hasValidVendorApiLevel7$window_release();
    }

    public final boolean isActivityEmbeddingComponentAccessible$window_release() {
        return this.safeWindowExtensionsProvider.isWindowExtensionsValid$window_release() && isActivityEmbeddingComponentValid();
    }
}
