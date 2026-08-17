package org.jetbrains.kotlin.load.kotlin;

import java.util.Collection;
import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\bf\u0018\u0000*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0002J\u001b\u0010\u0003\u001a\u00020\u00042\u0011\u0010\u0005\u001a\r\u0012\t\u0012\u00070\u0004¢\u0006\u0002\b\u00070\u0006H&J\u0017\u0010\b\u001a\u0004\u0018\u00018\u00002\u0006\u0010\t\u001a\u00020\nH&¢\u0006\u0002\u0010\u000bJ\u0012\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\t\u001a\u00020\nH&J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\t\u001a\u00020\nH\u0016J\u0018\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\nH&J\u0012\u0010\u0013\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0011\u001a\u00020\u0004H\u0016ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/load/kotlin/TypeMappingConfiguration;", "T", "", "commonSupertype", "Lorg/jetbrains/kotlin/types/KotlinType;", "types", "", "Lkotlin/jvm/JvmSuppressWildcards;", "getPredefinedTypeForClass", "classDescriptor", "Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;", "(Lorg/jetbrains/kotlin/descriptors/ClassDescriptor;)Ljava/lang/Object;", "getPredefinedInternalNameForClass", "", "getPredefinedFullInternalNameForClass", "processErrorType", "", "kotlinType", "descriptor", "preprocessType", "org.jetbrains.kotlin:descriptors.jvm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public interface TypeMappingConfiguration<T> {
    KotlinType commonSupertype(Collection<KotlinType> types);

    default String getPredefinedFullInternalNameForClass(ClassDescriptor classDescriptor) {
        classDescriptor.getClass();
        return null;
    }

    String getPredefinedInternalNameForClass(ClassDescriptor classDescriptor);

    T getPredefinedTypeForClass(ClassDescriptor classDescriptor);

    default KotlinType preprocessType(KotlinType kotlinType) {
        kotlinType.getClass();
        return null;
    }

    void processErrorType(KotlinType kotlinType, ClassDescriptor descriptor);
}
