package org.jetbrains.kotlin.descriptors;

import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public interface ValueDescriptor extends CallableDescriptor {
    @Override // org.jetbrains.kotlin.descriptors.DeclarationDescriptorNonRoot
    DeclarationDescriptor getContainingDeclaration();

    KotlinType getType();
}
