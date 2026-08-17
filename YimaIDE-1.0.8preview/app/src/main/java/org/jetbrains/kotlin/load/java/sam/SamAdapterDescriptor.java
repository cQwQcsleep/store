package org.jetbrains.kotlin.load.java.sam;

import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.synthetic.FunctionInterfaceAdapterDescriptor;
import org.jetbrains.kotlin.load.java.descriptors.JavaCallableMemberDescriptor;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
public interface SamAdapterDescriptor<D extends FunctionDescriptor> extends FunctionDescriptor, FunctionInterfaceAdapterDescriptor<D>, JavaCallableMemberDescriptor {
}
