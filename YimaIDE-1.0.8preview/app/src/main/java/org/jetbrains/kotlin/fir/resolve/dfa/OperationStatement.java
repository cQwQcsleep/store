package org.jetbrains.kotlin.fir.resolve.dfa;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\n\u0010\f\u001a\u00020\rH\u0096\u0080\u0004J\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0014HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/OperationStatement;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Statement;", "variable", "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;", "operation", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Operation;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;Lorg/jetbrains/kotlin/fir/resolve/dfa/Operation;)V", "getVariable", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;", "getOperation", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/Operation;", "toString", Argument.Delimiters.none, "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class OperationStatement extends Statement {
    private final Operation operation;
    private final DataFlowVariable variable;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public OperationStatement(DataFlowVariable dataFlowVariable, Operation operation) {
        super(null);
        dataFlowVariable.getClass();
        operation.getClass();
        this.variable = dataFlowVariable;
        this.operation = operation;
    }

    public static /* synthetic */ OperationStatement copy$default(OperationStatement operationStatement, DataFlowVariable dataFlowVariable, Operation operation, int i, Object obj) {
        if ((i & 1) != 0) {
            dataFlowVariable = operationStatement.variable;
        }
        if ((i & 2) != 0) {
            operation = operationStatement.operation;
        }
        return operationStatement.copy(dataFlowVariable, operation);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final DataFlowVariable getVariable() {
        return this.variable;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final Operation getOperation() {
        return this.operation;
    }

    public final OperationStatement copy(DataFlowVariable variable, Operation operation) {
        variable.getClass();
        operation.getClass();
        return new OperationStatement(variable, operation);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof OperationStatement)) {
            return false;
        }
        OperationStatement operationStatement = (OperationStatement) other;
        return Intrinsics.areEqual(this.variable, operationStatement.variable) && this.operation == operationStatement.operation;
    }

    public final Operation getOperation() {
        return this.operation;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.Statement
    public DataFlowVariable getVariable() {
        return this.variable;
    }

    public int hashCode() {
        return (this.variable.hashCode() * 31) + this.operation.hashCode();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getVariable());
        sb.append(' ');
        sb.append(this.operation);
        return sb.toString();
    }
}
