package kotlin.reflect.jvm.internal.impl.types;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface CustomTypeParameter {
    boolean isTypeParameter();

    KotlinType substitutionResult(KotlinType kotlinType);
}
