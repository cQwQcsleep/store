package org.jetbrains.kotlin.descriptors;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public interface MemberDescriptor extends DeclarationDescriptorNonRoot, DeclarationDescriptorWithVisibility {
    Modality getModality();

    @Override // org.jetbrains.kotlin.descriptors.DeclarationDescriptorWithVisibility
    DescriptorVisibility getVisibility();

    boolean isActual();

    boolean isExpect();

    boolean isExternal();
}
