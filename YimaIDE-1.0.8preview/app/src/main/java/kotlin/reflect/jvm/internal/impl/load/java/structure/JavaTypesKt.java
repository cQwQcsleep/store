package kotlin.reflect.jvm.internal.impl.load.java.structure;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class JavaTypesKt {
    public static final boolean isSuperWildcard(JavaType javaType) {
        JavaWildcardType javaWildcardType = javaType instanceof JavaWildcardType ? (JavaWildcardType) javaType : null;
        return (javaWildcardType == null || javaWildcardType.getBound() == null || javaWildcardType.isExtends()) ? false : true;
    }
}
