package org.jetbrains.kotlin.descriptors;

import java.util.List;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.annotations.Annotations;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.TypeSubstitution;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public interface FunctionDescriptor$CopyBuilder<D extends FunctionDescriptor> extends CallableMemberDescriptor.CopyBuilder<D> {
    @Override // 
    D build();

    <V> FunctionDescriptor$CopyBuilder<D> putUserData(CallableDescriptor.UserDataKey<V> userDataKey, V v);

    FunctionDescriptor$CopyBuilder<D> setAdditionalAnnotations(Annotations annotations);

    FunctionDescriptor$CopyBuilder<D> setContextReceiverParameters(List<ReceiverParameterDescriptor> list);

    @Override // 
    FunctionDescriptor$CopyBuilder<D> setCopyOverrides(boolean z);

    @Override // 
    FunctionDescriptor$CopyBuilder<D> setDispatchReceiverParameter(ReceiverParameterDescriptor receiverParameterDescriptor);

    FunctionDescriptor$CopyBuilder<D> setDropOriginalInContainingParts();

    FunctionDescriptor$CopyBuilder<D> setExtensionReceiverParameter(ReceiverParameterDescriptor receiverParameterDescriptor);

    FunctionDescriptor$CopyBuilder<D> setHiddenForResolutionEverywhereBesideSupercalls();

    FunctionDescriptor$CopyBuilder<D> setHiddenToOvercomeSignatureClash();

    @Override // 
    FunctionDescriptor$CopyBuilder<D> setKind(CallableMemberDescriptor.Kind kind);

    @Override // 
    FunctionDescriptor$CopyBuilder<D> setModality(Modality modality);

    @Override // 
    FunctionDescriptor$CopyBuilder<D> setName(Name name);

    @Override // 
    FunctionDescriptor$CopyBuilder<D> setOriginal(CallableMemberDescriptor callableMemberDescriptor);

    @Override // 
    FunctionDescriptor$CopyBuilder<D> setOwner(DeclarationDescriptor declarationDescriptor);

    @Override // 
    FunctionDescriptor$CopyBuilder<D> setPreserveSourceElement();

    @Override // 
    FunctionDescriptor$CopyBuilder<D> setReturnType(KotlinType kotlinType);

    FunctionDescriptor$CopyBuilder<D> setSignatureChange();

    @Override // 
    FunctionDescriptor$CopyBuilder<D> setSubstitution(TypeSubstitution typeSubstitution);

    /* bridge */ /* synthetic */ default CallableMemberDescriptor.CopyBuilder setTypeParameters(List list) {
        return mo98setTypeParameters((List<TypeParameterDescriptor>) list);
    }

    /* JADX INFO: renamed from: setTypeParameters, reason: collision with other method in class */
    FunctionDescriptor$CopyBuilder<D> mo98setTypeParameters(List<TypeParameterDescriptor> list);

    FunctionDescriptor$CopyBuilder<D> setValueParameters(List<ValueParameterDescriptor> list);

    @Override // 
    FunctionDescriptor$CopyBuilder<D> setVisibility(DescriptorVisibility descriptorVisibility);
}
