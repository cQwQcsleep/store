package org.jetbrains.kotlin.descriptors;

import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\bf\u0018\u00002\u00020\u0001R\u0014\u0010\u0002\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005R\u0014\u0010\u0006\u001a\u0004\u0018\u00010\u0003X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\u0005R\u0012\u0010\b\u001a\u00020\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\b\u0010\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/descriptors/VariableDescriptorWithAccessors;", "Lorg/jetbrains/kotlin/descriptors/VariableDescriptor;", "getter", "Lorg/jetbrains/kotlin/descriptors/VariableAccessorDescriptor;", "getGetter", "()Lorg/jetbrains/kotlin/descriptors/VariableAccessorDescriptor;", "setter", "getSetter", "isDelegated", "", "()Z", "org.jetbrains.kotlin:descriptors"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface VariableDescriptorWithAccessors extends VariableDescriptor {
    VariableAccessorDescriptor getGetter();

    VariableAccessorDescriptor getSetter();

    boolean isDelegated();
}
