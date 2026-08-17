package org.jetbrains.kotlin.descriptors;

import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public interface PropertyAccessorDescriptor extends VariableAccessorDescriptor {
    @Override // 
    PropertyAccessorDescriptor mo100copy(DeclarationDescriptor declarationDescriptor, Modality modality, DescriptorVisibility descriptorVisibility, CallableMemberDescriptor.Kind kind, boolean z);

    PropertyDescriptor getCorrespondingProperty();

    @Override // 
    PropertyAccessorDescriptor mo105getOriginal();

    Collection<? extends PropertyAccessorDescriptor> getOverriddenDescriptors();

    boolean isDefault();
}
