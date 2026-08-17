package org.jetbrains.kotlin.descriptors;

import java.util.List;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.TypeSubstitutor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public interface ConstructorDescriptor extends FunctionDescriptor {
    /* JADX INFO: renamed from: copy, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    ConstructorDescriptor m9copy(DeclarationDescriptor declarationDescriptor, Modality modality, DescriptorVisibility descriptorVisibility, CallableMemberDescriptor.Kind kind, boolean z);

    ClassDescriptor getConstructedClass();

    /* JADX INFO: renamed from: getContainingDeclaration, reason: merged with bridge method [inline-methods] */
    ClassifierDescriptorWithTypeParameters m10getContainingDeclaration();

    Name getName();

    /* JADX INFO: renamed from: getOriginal, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    ConstructorDescriptor m15getOriginal();

    KotlinType getReturnType();

    List<TypeParameterDescriptor> getTypeParameters();

    boolean isPrimary();

    /* JADX INFO: renamed from: substitute, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    ConstructorDescriptor m17substitute(TypeSubstitutor typeSubstitutor);
}
