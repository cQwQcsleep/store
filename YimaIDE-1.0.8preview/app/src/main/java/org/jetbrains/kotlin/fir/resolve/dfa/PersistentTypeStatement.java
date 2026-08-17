package org.jetbrains.kotlin.fir.resolve.dfa;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentSet;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.DfaType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B+\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u0005HÆ\u0003J3\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005HÆ\u0001J\u0014\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u0017HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u001a\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000e¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/PersistentTypeStatement;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/TypeStatement;", "variable", "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;", "upperTypes", "Lkotlinx/collections/immutable/PersistentSet;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "lowerTypes", "Lorg/jetbrains/kotlin/fir/DfaType;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;Lkotlinx/collections/immutable/PersistentSet;Lkotlinx/collections/immutable/PersistentSet;)V", "getVariable", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;", "getUpperTypes", "()Lkotlinx/collections/immutable/PersistentSet;", "getLowerTypes", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class PersistentTypeStatement extends TypeStatement {
    private final PersistentSet<DfaType> lowerTypes;
    private final PersistentSet<ConeKotlinType> upperTypes;
    private final DataFlowVariable variable;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PersistentTypeStatement(DataFlowVariable dataFlowVariable, PersistentSet<? extends ConeKotlinType> persistentSet, PersistentSet<? extends DfaType> persistentSet2) {
        super(null);
        dataFlowVariable.getClass();
        persistentSet.getClass();
        persistentSet2.getClass();
        this.variable = dataFlowVariable;
        this.upperTypes = persistentSet;
        this.lowerTypes = persistentSet2;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ PersistentTypeStatement copy$default(PersistentTypeStatement persistentTypeStatement, DataFlowVariable dataFlowVariable, PersistentSet persistentSet, PersistentSet persistentSet2, int i, Object obj) {
        if ((i & 1) != 0) {
            dataFlowVariable = persistentTypeStatement.variable;
        }
        if ((i & 2) != 0) {
            persistentSet = persistentTypeStatement.upperTypes;
        }
        if ((i & 4) != 0) {
            persistentSet2 = persistentTypeStatement.lowerTypes;
        }
        return persistentTypeStatement.copy(dataFlowVariable, persistentSet, persistentSet2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final DataFlowVariable getVariable() {
        return this.variable;
    }

    public final PersistentSet<ConeKotlinType> component2() {
        return this.upperTypes;
    }

    public final PersistentSet<DfaType> component3() {
        return this.lowerTypes;
    }

    public final PersistentTypeStatement copy(DataFlowVariable variable, PersistentSet<? extends ConeKotlinType> upperTypes, PersistentSet<? extends DfaType> lowerTypes) {
        variable.getClass();
        upperTypes.getClass();
        lowerTypes.getClass();
        return new PersistentTypeStatement(variable, upperTypes, lowerTypes);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PersistentTypeStatement)) {
            return false;
        }
        PersistentTypeStatement persistentTypeStatement = (PersistentTypeStatement) other;
        return Intrinsics.areEqual(this.variable, persistentTypeStatement.variable) && Intrinsics.areEqual(this.upperTypes, persistentTypeStatement.upperTypes) && Intrinsics.areEqual(this.lowerTypes, persistentTypeStatement.lowerTypes);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.TypeStatement, org.jetbrains.kotlin.fir.resolve.dfa.Statement
    public DataFlowVariable getVariable() {
        return this.variable;
    }

    public int hashCode() {
        return (((this.variable.hashCode() * 31) + this.upperTypes.hashCode()) * 31) + this.lowerTypes.hashCode();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.TypeStatement
    public PersistentSet<DfaType> getLowerTypes() {
        return this.lowerTypes;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.TypeStatement
    public PersistentSet<ConeKotlinType> getUpperTypes() {
        return this.upperTypes;
    }
}
