package org.jetbrains.kotlin.contracts;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.model.structure.ESVariable;
import org.jetbrains.kotlin.descriptors.ValueDescriptor;
import org.jetbrains.kotlin.resolve.calls.smartcasts.DataFlowValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u0014\u0010\u000b\u001a\u00020\f2\b\u0010\r\u001a\u0004\u0018\u00010\u000eH\u0096\u0082\u0004J\n\u0010\u000f\u001a\u00020\u0010H\u0096\u0080\u0004R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\n¨\u0006\u0011"}, d2 = {"Lorg/jetbrains/kotlin/contracts/ESVariableWithDataFlowValue;", "Lorg/jetbrains/kotlin/contracts/model/structure/ESVariable;", "Lorg/jetbrains/kotlin/contracts/ESDataFlowValue;", "descriptor", "Lorg/jetbrains/kotlin/descriptors/ValueDescriptor;", "dataFlowValue", "Lorg/jetbrains/kotlin/resolve/calls/smartcasts/DataFlowValue;", "<init>", "(Lorg/jetbrains/kotlin/descriptors/ValueDescriptor;Lorg/jetbrains/kotlin/resolve/calls/smartcasts/DataFlowValue;)V", "getDataFlowValue", "()Lorg/jetbrains/kotlin/resolve/calls/smartcasts/DataFlowValue;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:frontend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ESVariableWithDataFlowValue extends ESVariable implements ESDataFlowValue {
    private final DataFlowValue dataFlowValue;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ESVariableWithDataFlowValue(ValueDescriptor valueDescriptor, DataFlowValue dataFlowValue) {
        super(valueDescriptor);
        valueDescriptor.getClass();
        dataFlowValue.getClass();
        this.dataFlowValue = dataFlowValue;
    }

    @Override // org.jetbrains.kotlin.contracts.model.structure.ESVariable
    public boolean equals(Object other) {
        return dataFlowEquals(other);
    }

    @Override // org.jetbrains.kotlin.contracts.ESDataFlowValue
    public DataFlowValue getDataFlowValue() {
        return this.dataFlowValue;
    }

    @Override // org.jetbrains.kotlin.contracts.model.structure.ESVariable
    public int hashCode() {
        return getDataFlowValue().hashCode();
    }
}
