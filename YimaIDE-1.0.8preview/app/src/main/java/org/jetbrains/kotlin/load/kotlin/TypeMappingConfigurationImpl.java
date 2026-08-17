package org.jetbrains.kotlin.load.kotlin;

import java.util.Collection;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J\u0016\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\bH\u0016J\u0012\u0010\t\u001a\u0004\u0018\u00010\u00022\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u000bH\u0016¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/TypeMappingConfigurationImpl;", "Lorg/jetbrains/kotlin/load/kotlin/TypeMappingConfiguration;", "Lorg/jetbrains/kotlin/load/kotlin/JvmType;", "<init>", "()V", "commonSupertype", "Lorg/jetbrains/kotlin/types/KotlinType;", "types", "", "getPredefinedTypeForClass", "classDescriptor", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "getPredefinedInternalNameForClass", "", "processErrorType", "", "kotlinType", "descriptor", "org.jetbrains.kotlin:descriptors.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class TypeMappingConfigurationImpl implements TypeMappingConfiguration<JvmType> {
    public static final TypeMappingConfigurationImpl INSTANCE = new TypeMappingConfigurationImpl();

    private TypeMappingConfigurationImpl() {
    }

    @Override // org.jetbrains.kotlin.load.kotlin.TypeMappingConfiguration
    public KotlinType commonSupertype(Collection<? extends KotlinType> types) {
        types.getClass();
        throw new AssertionError("There should be no intersection type in existing descriptors, but found: " + CollectionsKt.joinToString$default(types, (CharSequence) null, (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 63, (Object) null));
    }

    @Override // org.jetbrains.kotlin.load.kotlin.TypeMappingConfiguration
    public String getPredefinedInternalNameForClass(ClassDescriptor classDescriptor) {
        classDescriptor.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.load.kotlin.TypeMappingConfiguration
    public JvmType getPredefinedTypeForClass(ClassDescriptor classDescriptor) {
        classDescriptor.getClass();
        return null;
    }

    @Override // org.jetbrains.kotlin.load.kotlin.TypeMappingConfiguration
    public void processErrorType(KotlinType kotlinType, ClassDescriptor descriptor) {
        kotlinType.getClass();
        descriptor.getClass();
    }
}
