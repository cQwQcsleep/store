package org.jetbrains.kotlin.descriptors;

import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public interface PropertySetterDescriptor extends PropertyAccessorDescriptor {
    @Override // org.jetbrains.kotlin.descriptors.PropertyAccessorDescriptor
    PropertySetterDescriptor getOriginal();

    @Override // org.jetbrains.kotlin.descriptors.PropertyAccessorDescriptor
    Collection<? extends PropertySetterDescriptor> getOverriddenDescriptors();
}
