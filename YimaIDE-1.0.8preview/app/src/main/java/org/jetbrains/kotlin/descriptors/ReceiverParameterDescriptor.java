package org.jetbrains.kotlin.descriptors;

import org.jetbrains.kotlin.resolve.scopes.receivers.ReceiverValue;
import org.jetbrains.kotlin.types.TypeSubstitutor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public interface ReceiverParameterDescriptor extends ParameterDescriptor {
    ReceiverParameterDescriptor copy(DeclarationDescriptor declarationDescriptor);

    ReceiverValue getValue();

    @Override // 
    ReceiverParameterDescriptor substitute(TypeSubstitutor typeSubstitutor);
}
