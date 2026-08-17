package kotlin.reflect.jvm.internal;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
class Util {
    public static Object getEnumConstantByName(Class<? extends Enum<?>> cls, String str) {
        return Enum.valueOf(cls, str);
    }
}
