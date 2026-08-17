package org.jetbrains.kotlin.descriptors;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
public interface DeclarationDescriptorWithSource extends DeclarationDescriptor {
    @Override // 
    DeclarationDescriptorWithSource getOriginal();

    SourceElement getSource();
}
