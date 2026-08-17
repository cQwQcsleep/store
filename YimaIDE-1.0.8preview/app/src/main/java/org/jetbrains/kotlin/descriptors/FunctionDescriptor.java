package org.jetbrains.kotlin.descriptors;

import java.util.Collection;
import org.jetbrains.kotlin.mpp.FunctionSymbolMarker;
import org.jetbrains.kotlin.types.TypeSubstitutor;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public interface FunctionDescriptor extends CallableMemberDescriptor, FunctionSymbolMarker {
    FunctionDescriptor copy(DeclarationDescriptor declarationDescriptor, Modality modality, DescriptorVisibility descriptorVisibility, CallableMemberDescriptor.Kind kind, boolean z);

    DeclarationDescriptor getContainingDeclaration();

    FunctionDescriptor getInitialSignatureDescriptor();

    /* JADX INFO: renamed from: getOriginal, reason: collision with other method in class and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    FunctionDescriptor m2174getOriginal();

    Collection<? extends FunctionDescriptor> getOverriddenDescriptors();

    boolean isHiddenForResolutionEverywhereBesideSupercalls();

    boolean isHiddenToOvercomeSignatureClash();

    boolean isInfix();

    boolean isInline();

    boolean isOperator();

    boolean isSuspend();

    boolean isTailrec();

    CopyBuilder<? extends FunctionDescriptor> newCopyBuilder();

    FunctionDescriptor substitute(TypeSubstitutor typeSubstitutor);
}
