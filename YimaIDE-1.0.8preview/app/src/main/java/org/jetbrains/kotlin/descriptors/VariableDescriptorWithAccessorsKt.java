package org.jetbrains.kotlin.descriptors;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\"\u001b\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001*\u00020\u00038F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0005¨\u0006\u0006"}, d2 = {"accessors", "", "Lorg/jetbrains/kotlin/descriptors/VariableAccessorDescriptor;", "Lorg/jetbrains/kotlin/descriptors/VariableDescriptorWithAccessors;", "getAccessors", "(Lorg/jetbrains/kotlin/descriptors/VariableDescriptorWithAccessors;)Ljava/util/List;", "org.jetbrains.kotlin:descriptors"}, k = 2, mv = {2, 2, 0}, xi = 48)
public final class VariableDescriptorWithAccessorsKt {
    public static final List<VariableAccessorDescriptor> getAccessors(VariableDescriptorWithAccessors variableDescriptorWithAccessors) {
        variableDescriptorWithAccessors.getClass();
        return CollectionsKt.listOfNotNull(new VariableAccessorDescriptor[]{variableDescriptorWithAccessors.getGetter(), variableDescriptorWithAccessors.getSetter()});
    }
}
