package org.jetbrains.kotlin.synthetic;

import kotlin.Metadata;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.synthetic.FunctionInterfaceAdapterExtensionFunctionDescriptor;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0010\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\bf\u0018\u00002\u00020\u00012\u00020\u0002R\u0012\u0010\u0003\u001a\u00020\u0001X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/synthetic/SamAdapterExtensionFunctionDescriptor;", "Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "Lorg/jetbrains/kotlin/descriptors/synthetic/FunctionInterfaceAdapterExtensionFunctionDescriptor;", "baseDescriptorForSynthetic", "getBaseDescriptorForSynthetic", "()Lorg/jetbrains/kotlin/descriptors/FunctionDescriptor;", "org.jetbrains.kotlin:frontend.java"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public interface SamAdapterExtensionFunctionDescriptor extends FunctionDescriptor, FunctionInterfaceAdapterExtensionFunctionDescriptor {
    @Override // 
    FunctionDescriptor getBaseDescriptorForSynthetic();
}
