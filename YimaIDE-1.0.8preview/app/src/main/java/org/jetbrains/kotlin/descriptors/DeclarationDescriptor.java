package org.jetbrains.kotlin.descriptors;

import org.jetbrains.kotlin.descriptors.annotations.Annotated;
import org.jetbrains.kotlin.mpp.DeclarationSymbolMarker;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public interface DeclarationDescriptor extends Named, ValidateableDescriptor, Annotated, DeclarationSymbolMarker {
    <R, D> R accept(DeclarationDescriptorVisitor<R, D> declarationDescriptorVisitor, D d);

    void acceptVoid(DeclarationDescriptorVisitor<Void, Void> declarationDescriptorVisitor);

    DeclarationDescriptor getContainingDeclaration();

    /* JADX INFO: renamed from: getOriginal */
    DeclarationDescriptor mo118getOriginal();
}
