package kotlin.reflect.jvm.internal.impl.descriptors.runtime.components;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class ReflectJavaClassFinderKt {
    public static final Class<?> tryLoadClass(ClassLoader classLoader, String str) {
        classLoader.getClass();
        str.getClass();
        try {
            return Class.forName(str, false, classLoader);
        } catch (ClassNotFoundException unused) {
            return null;
        }
    }
}
