package kotlin.internal;

import java.lang.reflect.Method;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.library.SearchPathResolverKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÂ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003R\u0018\u0010\u0004\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0084\b\u0092\u0002\u0002\b\u0006¢\u0006\u0002\n\u0000R\u0018\u0010\u0007\u001a\u0004\u0018\u00010\u00058\u0006X\u0087\u0084\b\u0092\u0002\u0002\b\u0006¢\u0006\u0002\n\u0000¨\u0006\b"}, d2 = {"Lkotlin/internal/PlatformImplementations$ReflectThrowable;", "", "<init>", "()V", "addSuppressed", "Ljava/lang/reflect/Method;", "Lkotlin/jvm/JvmField;", "getSuppressed", SearchPathResolverKt.KOTLIN_JKLIB_STDLIB_NAME}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class PlatformImplementations$ReflectThrowable {
    public static final PlatformImplementations$ReflectThrowable INSTANCE = new PlatformImplementations$ReflectThrowable();
    public static final Method addSuppressed;
    public static final Method getSuppressed;

    static {
        Method method;
        Method method2;
        Method[] methods = Throwable.class.getMethods();
        methods.getClass();
        int length = methods.length;
        int i = 0;
        while (true) {
            method = null;
            if (i >= length) {
                method2 = null;
                break;
            }
            method2 = methods[i];
            if (Intrinsics.areEqual(method2.getName(), "addSuppressed")) {
                Class<?>[] parameterTypes = method2.getParameterTypes();
                parameterTypes.getClass();
                if (Intrinsics.areEqual(ArraysKt.singleOrNull(parameterTypes), Throwable.class)) {
                    break;
                }
            }
            i++;
        }
        addSuppressed = method2;
        for (Method method3 : methods) {
            if (Intrinsics.areEqual(method3.getName(), "getSuppressed")) {
                method = method3;
                break;
            }
        }
        getSuppressed = method;
    }

    private PlatformImplementations$ReflectThrowable() {
    }
}
