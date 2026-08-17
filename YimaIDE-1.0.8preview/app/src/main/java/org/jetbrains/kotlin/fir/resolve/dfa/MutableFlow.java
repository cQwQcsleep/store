package org.jetbrains.kotlin.fir.resolve.dfa;

import java.util.Collection;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlinx.collections.immutable.PersistentList;
import kotlinx.collections.immutable.PersistentMap;
import kotlinx.collections.immutable.PersistentSet;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\"\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0083\u0001\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0003\u0012\u0012\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005\u0012\u0018\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0005\u0012\u0012\u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u0005\u0012\u0012\u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u0005\u0012\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00100\u0005¢\u0006\u0004\b\u0011\u0010\u0012B\t\b\u0016¢\u0006\u0004\b\u0011\u0010\u0013J\u0010\u0010\u001e\u001a\u00020\f2\u0006\u0010\u001f\u001a\u00020\fH\u0016J\u0012\u0010 \u001a\u0004\u0018\u00010!2\u0006\u0010\u001f\u001a\u00020\u0006H\u0016J\u0018\u0010\"\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010#2\u0006\u0010\u001f\u001a\u00020\u0006H\u0016J\u0006\u0010$\u001a\u00020\u0003R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R&\u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015R \u0010\u000b\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0015R \u0010\u000e\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\f0\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0015R&\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\f0\u00100\u0005X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0015R\u001a\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00060\u001b8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/MutableFlow;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Flow;", "previousFlow", "Lorg/jetbrains/kotlin/fir/resolve/dfa/PersistentFlow;", "approvedTypeStatements", "Lkotlinx/collections/immutable/PersistentMap$Builder;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/PersistentTypeStatement;", "implications", "Lkotlinx/collections/immutable/PersistentList;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Implication;", "assignmentIndex", "Lorg/jetbrains/kotlin/fir/resolve/dfa/RealVariable;", Argument.Delimiters.none, "directAliasMap", "backwardsAliasMap", "Lkotlinx/collections/immutable/PersistentSet;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/PersistentFlow;Lkotlinx/collections/immutable/PersistentMap$Builder;Lkotlinx/collections/immutable/PersistentMap$Builder;Lkotlinx/collections/immutable/PersistentMap$Builder;Lkotlinx/collections/immutable/PersistentMap$Builder;Lkotlinx/collections/immutable/PersistentMap$Builder;)V", "()V", "getApprovedTypeStatements$org_jetbrains_kotlin_semantics", "()Lkotlinx/collections/immutable/PersistentMap$Builder;", "getImplications$org_jetbrains_kotlin_semantics", "getAssignmentIndex$org_jetbrains_kotlin_semantics", "getDirectAliasMap$org_jetbrains_kotlin_semantics", "getBackwardsAliasMap$org_jetbrains_kotlin_semantics", "knownVariables", Argument.Delimiters.none, "getKnownVariables", "()Ljava/util/Set;", "unwrapVariable", "variable", "getTypeStatement", "Lorg/jetbrains/kotlin/fir/resolve/dfa/TypeStatement;", "getImplications", Argument.Delimiters.none, "freeze", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MutableFlow extends Flow {
    private final PersistentMap.Builder<DataFlowVariable, PersistentTypeStatement> approvedTypeStatements;
    private final PersistentMap.Builder<RealVariable, Integer> assignmentIndex;
    private final PersistentMap.Builder<RealVariable, PersistentSet<RealVariable>> backwardsAliasMap;
    private final PersistentMap.Builder<RealVariable, RealVariable> directAliasMap;
    private final PersistentMap.Builder<DataFlowVariable, PersistentList<Implication>> implications;
    private final PersistentFlow previousFlow;

    public MutableFlow(PersistentFlow persistentFlow, PersistentMap.Builder<DataFlowVariable, PersistentTypeStatement> builder, PersistentMap.Builder<DataFlowVariable, PersistentList<Implication>> builder2, PersistentMap.Builder<RealVariable, Integer> builder3, PersistentMap.Builder<RealVariable, RealVariable> builder4, PersistentMap.Builder<RealVariable, PersistentSet<RealVariable>> builder5) {
        builder.getClass();
        builder2.getClass();
        builder3.getClass();
        builder4.getClass();
        builder5.getClass();
        this.previousFlow = persistentFlow;
        this.approvedTypeStatements = builder;
        this.implications = builder2;
        this.assignmentIndex = builder3;
        this.directAliasMap = builder4;
        this.backwardsAliasMap = builder5;
    }

    public final PersistentFlow freeze() {
        return new PersistentFlow(this.previousFlow, this.approvedTypeStatements.build(), this.implications.build(), this.assignmentIndex.build(), this.directAliasMap.build(), this.backwardsAliasMap.build());
    }

    public final PersistentMap.Builder<DataFlowVariable, PersistentTypeStatement> getApprovedTypeStatements$org_jetbrains_kotlin_semantics() {
        return this.approvedTypeStatements;
    }

    public final PersistentMap.Builder<RealVariable, Integer> getAssignmentIndex$org_jetbrains_kotlin_semantics() {
        return this.assignmentIndex;
    }

    public final PersistentMap.Builder<RealVariable, PersistentSet<RealVariable>> getBackwardsAliasMap$org_jetbrains_kotlin_semantics() {
        return this.backwardsAliasMap;
    }

    public final PersistentMap.Builder<RealVariable, RealVariable> getDirectAliasMap$org_jetbrains_kotlin_semantics() {
        return this.directAliasMap;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.Flow
    public Collection<Implication> getImplications(DataFlowVariable variable) {
        variable.getClass();
        return (Collection) this.implications.get(variable);
    }

    public final PersistentMap.Builder<DataFlowVariable, PersistentList<Implication>> getImplications$org_jetbrains_kotlin_semantics() {
        return this.implications;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.Flow
    public Set<DataFlowVariable> getKnownVariables() {
        return SetsKt.plus(this.approvedTypeStatements.keySet(), this.directAliasMap.keySet());
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.Flow
    public TypeStatement getTypeStatement(DataFlowVariable variable) {
        variable.getClass();
        PersistentTypeStatement persistentTypeStatement = (PersistentTypeStatement) this.approvedTypeStatements.get(unwrapVariable(variable));
        if (persistentTypeStatement != null) {
            return PersistentTypeStatement.copy$default(persistentTypeStatement, variable, null, null, 6, null);
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.Flow
    public RealVariable unwrapVariable(RealVariable variable) {
        variable.getClass();
        RealVariable realVariable = (RealVariable) this.directAliasMap.get(variable);
        return realVariable == null ? variable : realVariable;
    }

    public MutableFlow() {
        this(null, FlowKt.emptyPersistentHashMapBuilder(), FlowKt.emptyPersistentHashMapBuilder(), FlowKt.emptyPersistentHashMapBuilder(), FlowKt.emptyPersistentHashMapBuilder(), FlowKt.emptyPersistentHashMapBuilder());
    }
}
