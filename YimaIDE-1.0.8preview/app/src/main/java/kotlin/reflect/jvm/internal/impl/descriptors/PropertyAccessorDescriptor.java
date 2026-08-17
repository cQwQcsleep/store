package kotlin.reflect.jvm.internal.impl.descriptors;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface PropertyAccessorDescriptor extends VariableAccessorDescriptor {
    PropertyDescriptor getCorrespondingProperty();

    boolean isDefault();
}
