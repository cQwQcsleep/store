package androidx.window.reflection;

import android.util.Log;
import androidx.compose.ui.tooling.preview.AndroidUiModes;
import androidx.window.reflection.ReflectionUtils;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmClassMappingKt;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.functions.Function0;
import kotlin.reflect.KClass;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001f\u0010\u0004\u001a\u00020\u00052\u0010\u0010\u0006\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b0\u0007H\u0000¢\u0006\u0002\b\tJ#\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007H\u0001¢\u0006\u0002\b\u000eJ\u001d\u0010\u0017\u001a\u00020\u0005*\u00020\u00132\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019H\u0000¢\u0006\u0002\b\u001aJ\u001d\u0010\u0017\u001a\u00020\u0005*\u00020\u00132\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\bH\u0000¢\u0006\u0002\b\u001aJ%\u0010\u001b\u001a\u00020\u00052\n\u0010\u001c\u001a\u0006\u0012\u0002\b\u00030\b2\n\u0010\u001d\u001a\u0006\u0012\u0002\b\u00030\bH\u0000¢\u0006\u0002\b\u001eR\u001c\u0010\u000f\u001a\u00020\u0005*\u0006\u0012\u0002\b\u00030\u00108@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R\u0018\u0010\u000f\u001a\u00020\u0005*\u00020\u00138@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0014R\u0018\u0010\u000f\u001a\u00020\u0005*\u00020\u00158@X\u0080\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0016¨\u0006\u001f"}, d2 = {"Landroidx/window/reflection/ReflectionUtils;", "", "<init>", "()V", "checkIsPresent", "", "classLoader", "Lkotlin/Function0;", "Ljava/lang/Class;", "checkIsPresent$window_release", "validateReflection", "errorMessage", "", "block", "validateReflection$window_release", "isPublic", "Ljava/lang/reflect/Constructor;", "isPublic$window_release", "(Ljava/lang/reflect/Constructor;)Z", "Ljava/lang/reflect/Method;", "(Ljava/lang/reflect/Method;)Z", "Ljava/lang/reflect/Field;", "(Ljava/lang/reflect/Field;)Z", "doesReturn", "clazz", "Lkotlin/reflect/KClass;", "doesReturn$window_release", "validateImplementation", "implementation", "requirements", "validateImplementation$window_release", "window_release"}, k = 1, mv = {2, 0, 0}, xi = AndroidUiModes.UI_MODE_NIGHT_MASK)
public final class ReflectionUtils {
    public static final ReflectionUtils INSTANCE = new ReflectionUtils();

    private ReflectionUtils() {
    }

    public static boolean a(Class cls, Method method) throws NoSuchMethodException {
        String name = method.getName();
        Class<?>[] parameterTypes = method.getParameterTypes();
        Method method2 = cls.getMethod(name, (Class[]) Arrays.copyOf(parameterTypes, parameterTypes.length));
        ReflectionUtils reflectionUtils = INSTANCE;
        method2.getClass();
        if (!reflectionUtils.isPublic$window_release(method2)) {
            return false;
        }
        Class<?> returnType = method.getReturnType();
        returnType.getClass();
        return reflectionUtils.doesReturn$window_release(method2, returnType);
    }

    @JvmStatic
    public static final boolean validateReflection$window_release(String errorMessage, Function0<Boolean> block) {
        errorMessage.getClass();
        block.getClass();
        try {
            boolean zBooleanValue = ((Boolean) block.invoke()).booleanValue();
            if (!zBooleanValue) {
                Log.e("ReflectionGuard", errorMessage);
            }
            return zBooleanValue;
        } catch (ClassNotFoundException unused) {
            Log.e("ReflectionGuard", "ClassNotFound: " + errorMessage);
            return false;
        } catch (NoSuchFieldException unused2) {
            Log.e("ReflectionGuard", "NoSuchField: " + errorMessage);
            return false;
        } catch (NoSuchMethodException unused3) {
            Log.e("ReflectionGuard", "NoSuchMethod: " + errorMessage);
            return false;
        }
    }

    public final boolean checkIsPresent$window_release(Function0<? extends Class<?>> classLoader) {
        classLoader.getClass();
        try {
            classLoader.invoke();
            return true;
        } catch (ClassNotFoundException | NoClassDefFoundError unused) {
            return false;
        }
    }

    public final boolean doesReturn$window_release(Method method, KClass<?> kClass) {
        method.getClass();
        kClass.getClass();
        return doesReturn$window_release(method, JvmClassMappingKt.getJavaClass(kClass));
    }

    public final boolean isPublic$window_release(Constructor<?> constructor) {
        constructor.getClass();
        return Modifier.isPublic(constructor.getModifiers());
    }

    public final boolean validateImplementation$window_release(final Class<?> implementation, Class<?> requirements) {
        implementation.getClass();
        requirements.getClass();
        Method[] methods = requirements.getMethods();
        methods.getClass();
        for (final Method method : methods) {
            if (!validateReflection$window_release(implementation.getName() + '#' + method.getName() + " is not valid", new Function0() { // from class: jac
                public final Object invoke() {
                    return Boolean.valueOf(ReflectionUtils.a(implementation, method));
                }
            })) {
                return false;
            }
        }
        return true;
    }

    public final boolean isPublic$window_release(Method method) {
        method.getClass();
        return Modifier.isPublic(method.getModifiers());
    }

    public final boolean isPublic$window_release(Field field) {
        field.getClass();
        return Modifier.isPublic(field.getModifiers());
    }

    public final boolean doesReturn$window_release(Method method, Class<?> cls) {
        method.getClass();
        cls.getClass();
        return method.getReturnType().equals(cls);
    }
}
