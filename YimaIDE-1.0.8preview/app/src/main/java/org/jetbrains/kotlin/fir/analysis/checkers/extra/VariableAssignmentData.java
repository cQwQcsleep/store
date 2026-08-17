package org.jetbrains.kotlin.fir.analysis.checkers.extra;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.EdgeLabel;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bRb\u0010\t\u001aJ\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000b\u0012<\u0012:\u0012\u0004\u0012\u00020\r\u0012 \u0012\u001e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\fj\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f`\u00100\fj\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f`\u00110\nX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\bR\u001d\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001c0\u001b¢\u0006\b\n\u0000\u001a\u0004\b\u001d\u0010\u0013R\u001d\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u001f0\u001b¢\u0006\b\n\u0000\u001a\u0004\b \u0010\u0013¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/VariableAssignmentData;", Argument.Delimiters.none, "localProperties", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "<init>", "(Ljava/util/Set;)V", "getLocalProperties", "()Ljava/util/Set;", "writesByNode", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "Lkotlinx/collections/immutable/PersistentMap;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/PropertyAccessType;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/VariableWriteData;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/ControlFlowInfo;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PathAwareControlFlowInfo;", "getWritesByNode", "()Ljava/util/Map;", "setWritesByNode", "(Ljava/util/Map;)V", "unreadWrites", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "getUnreadWrites", "variableScopes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "getVariableScopes", "variablesWithoutReads", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "getVariablesWithoutReads", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class VariableAssignmentData {
    private final Set<FirPropertySymbol> localProperties;
    private final Set<FirStatement> unreadWrites;
    private final Map<FirPropertySymbol, ControlFlowGraph> variableScopes;
    private final Map<FirPropertySymbol, FirProperty> variablesWithoutReads;
    private Map<CFGNode<?>, ? extends PersistentMap<EdgeLabel, ? extends PersistentMap<PropertyAccessType, VariableWriteData>>> writesByNode;

    /* JADX WARN: Multi-variable type inference failed */
    public VariableAssignmentData(Set<? extends FirPropertySymbol> set) {
        set.getClass();
        this.localProperties = set;
        this.writesByNode = MapsKt.emptyMap();
        this.unreadWrites = new LinkedHashSet();
        this.variableScopes = new HashMap();
        this.variablesWithoutReads = new LinkedHashMap();
    }

    public final Set<FirPropertySymbol> getLocalProperties() {
        return this.localProperties;
    }

    public final Set<FirStatement> getUnreadWrites() {
        return this.unreadWrites;
    }

    public final Map<FirPropertySymbol, ControlFlowGraph> getVariableScopes() {
        return this.variableScopes;
    }

    public final Map<FirPropertySymbol, FirProperty> getVariablesWithoutReads() {
        return this.variablesWithoutReads;
    }

    public final Map<CFGNode<?>, PersistentMap<EdgeLabel, PersistentMap<PropertyAccessType, VariableWriteData>>> getWritesByNode() {
        return this.writesByNode;
    }

    public final void setWritesByNode(Map<CFGNode<?>, ? extends PersistentMap<EdgeLabel, ? extends PersistentMap<PropertyAccessType, VariableWriteData>>> map) {
        map.getClass();
        this.writesByNode = map;
    }
}
