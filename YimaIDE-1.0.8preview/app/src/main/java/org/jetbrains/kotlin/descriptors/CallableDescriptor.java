package org.jetbrains.kotlin.descriptors;

import java.util.Collection;
import java.util.List;
import org.jetbrains.kotlin.mpp.CallableSymbolMarker;
import org.jetbrains.kotlin.types.KotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public interface CallableDescriptor extends DeclarationDescriptorNonRoot, DeclarationDescriptorWithVisibility, Substitutable<CallableDescriptor>, CallableSymbolMarker {

    public interface UserDataKey<V> {
    }

    List<ReceiverParameterDescriptor> getContextReceiverParameters();

    ReceiverParameterDescriptor getDispatchReceiverParameter();

    ReceiverParameterDescriptor getExtensionReceiverParameter();

    @Override // org.jetbrains.kotlin.descriptors.DeclarationDescriptor
    /* JADX INFO: renamed from: getOriginal, reason: merged with bridge method [inline-methods] */
    CallableDescriptor m84getOriginal();

    Collection<? extends CallableDescriptor> getOverriddenDescriptors();

    KotlinType getReturnType();

    List<TypeParameterDescriptor> getTypeParameters();

    <V> V getUserData(UserDataKey<V> userDataKey);

    List<ValueParameterDescriptor> getValueParameters();

    boolean hasStableParameterNames();

    boolean hasSynthesizedParameterNames();
}
