package org.jetbrains.kotlin.fir.resolve.dfa;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentList;
import kotlinx.collections.immutable.PersistentMap;
import kotlinx.collections.immutable.PersistentSet;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\"\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0083\u0001\b\u0000\u0012\b\u0010\u0002\u001a\u0004\u0018\u00010\u0000\u0012\u0012\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004\u0012\u0018\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0004\u0012\u0012\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u0004\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\u0004\u0012\u0018\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u000f0\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u0010\u0010\u001d\u001a\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\u000bH\u0016J\u0012\u0010\u001f\u001a\u0004\u0018\u00010 2\u0006\u0010\u001e\u001a\u00020\u0005H\u0016J\u0018\u0010!\u001a\n\u0012\u0004\u0012\u00020\t\u0018\u00010\"2\u0006\u0010\u001e\u001a\u00020\u0005H\u0016J\u0010\u0010#\u001a\u0004\u0018\u00010\u00002\u0006\u0010$\u001a\u00020\u0000J\u0006\u0010%\u001a\u00020&R\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0000X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0003\u001a\u000e\u0012\u0004\u0012\u00020\u0005\u0012\u0004\u0012\u00020\u00060\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R&\u0010\u0007\u001a\u0014\u0012\u0004\u0012\u00020\u0005\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R \u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0013R \u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u000b0\u0004X\u0080\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0013R \u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u000b\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u000b0\u000f0\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00050\u00188VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u0017\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00050\u00188F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001a¨\u0006'"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/PersistentFlow;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Flow;", "previousFlow", "approvedTypeStatements", "Lkotlinx/collections/immutable/PersistentMap;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/DataFlowVariable;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/PersistentTypeStatement;", "implications", "Lkotlinx/collections/immutable/PersistentList;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/Implication;", "assignmentIndex", "Lorg/jetbrains/kotlin/fir/resolve/dfa/RealVariable;", Argument.Delimiters.none, "directAliasMap", "backwardsAliasMap", "Lkotlinx/collections/immutable/PersistentSet;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/PersistentFlow;Lkotlinx/collections/immutable/PersistentMap;Lkotlinx/collections/immutable/PersistentMap;Lkotlinx/collections/immutable/PersistentMap;Lkotlinx/collections/immutable/PersistentMap;Lkotlinx/collections/immutable/PersistentMap;)V", "getImplications$org_jetbrains_kotlin_semantics", "()Lkotlinx/collections/immutable/PersistentMap;", "getAssignmentIndex$org_jetbrains_kotlin_semantics", "getDirectAliasMap$org_jetbrains_kotlin_semantics", "level", "knownVariables", Argument.Delimiters.none, "getKnownVariables", "()Ljava/util/Set;", "allVariablesForDebug", "getAllVariablesForDebug", "unwrapVariable", "variable", "getTypeStatement", "Lorg/jetbrains/kotlin/fir/resolve/dfa/TypeStatement;", "getImplications", Argument.Delimiters.none, "lowestCommonAncestor", "other", "fork", "Lorg/jetbrains/kotlin/fir/resolve/dfa/MutableFlow;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PersistentFlow extends Flow {
    private final PersistentMap<DataFlowVariable, PersistentTypeStatement> approvedTypeStatements;
    private final PersistentMap<RealVariable, Integer> assignmentIndex;
    private final PersistentMap<RealVariable, PersistentSet<RealVariable>> backwardsAliasMap;
    private final PersistentMap<RealVariable, RealVariable> directAliasMap;
    private final PersistentMap<DataFlowVariable, PersistentList<Implication>> implications;
    private final int level;
    private final PersistentFlow previousFlow;

    public PersistentFlow(PersistentFlow persistentFlow, PersistentMap<DataFlowVariable, PersistentTypeStatement> persistentMap, PersistentMap<DataFlowVariable, ? extends PersistentList<Implication>> persistentMap2, PersistentMap<RealVariable, Integer> persistentMap3, PersistentMap<RealVariable, RealVariable> persistentMap4, PersistentMap<RealVariable, ? extends PersistentSet<RealVariable>> persistentMap5) {
        persistentMap.getClass();
        persistentMap2.getClass();
        persistentMap3.getClass();
        persistentMap4.getClass();
        persistentMap5.getClass();
        this.previousFlow = persistentFlow;
        this.approvedTypeStatements = persistentMap;
        this.implications = persistentMap2;
        this.assignmentIndex = persistentMap3;
        this.directAliasMap = persistentMap4;
        this.backwardsAliasMap = persistentMap5;
        this.level = persistentFlow != null ? persistentFlow.level + 1 : 0;
    }

    public final MutableFlow fork() {
        return new MutableFlow(this, this.approvedTypeStatements.builder(), this.implications.builder(), this.assignmentIndex.builder(), this.directAliasMap.builder(), this.backwardsAliasMap.builder());
    }

    public final Set<DataFlowVariable> getAllVariablesForDebug() {
        Set setPlus = SetsKt.plus(getKnownVariables(), this.implications.keySet());
        List listFlatten = CollectionsKt.flatten(this.implications.values());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(listFlatten, 10));
        Iterator it = listFlatten.iterator();
        while (it.hasNext()) {
            arrayList.add(((Implication) it.next()).getEffect().getVariable());
        }
        return SetsKt.plus(setPlus, arrayList);
    }

    public final PersistentMap<RealVariable, Integer> getAssignmentIndex$org_jetbrains_kotlin_semantics() {
        return this.assignmentIndex;
    }

    public final PersistentMap<RealVariable, RealVariable> getDirectAliasMap$org_jetbrains_kotlin_semantics() {
        return this.directAliasMap;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.Flow
    public Collection<Implication> getImplications(DataFlowVariable variable) {
        variable.getClass();
        return (Collection) this.implications.get(variable);
    }

    public final PersistentMap<DataFlowVariable, PersistentList<Implication>> getImplications$org_jetbrains_kotlin_semantics() {
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

    public final PersistentFlow lowestCommonAncestor(PersistentFlow other) {
        other.getClass();
        while (this.level > other.level) {
            this = this.previousFlow;
            if (this == null) {
                return null;
            }
        }
        while (other.level > this.level) {
            other = other.previousFlow;
            if (other == null) {
                return null;
            }
        }
        while (!Intrinsics.areEqual(this, other)) {
            this = this.previousFlow;
            if (this == null || (other = other.previousFlow) == null) {
                return null;
            }
        }
        return this;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.Flow
    public RealVariable unwrapVariable(RealVariable variable) {
        variable.getClass();
        RealVariable realVariable = (RealVariable) this.directAliasMap.get(variable);
        return realVariable == null ? variable : realVariable;
    }
}
