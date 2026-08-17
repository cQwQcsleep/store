package org.jetbrains.kotlin.descriptors;

import java.util.Collection;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public interface PropertyGetterDescriptor extends PropertyAccessorDescriptor {
    @Override // org.jetbrains.kotlin.descriptors.PropertyAccessorDescriptor
    PropertyGetterDescriptor getOriginal();

    @Override // org.jetbrains.kotlin.descriptors.PropertyAccessorDescriptor
    Collection<? extends PropertyGetterDescriptor> getOverriddenDescriptors();
}
