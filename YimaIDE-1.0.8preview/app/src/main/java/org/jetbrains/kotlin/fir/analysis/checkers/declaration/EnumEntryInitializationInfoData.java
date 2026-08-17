package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.cfa.util.EventOccurrencesRangeAtNode;
import org.jetbrains.kotlin.fir.analysis.cfa.util.PathAwareControlFlowGraphVisitorKt;
import org.jetbrains.kotlin.fir.analysis.cfa.util.VariableInitializationInfoData;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.EdgeLabel;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B)\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\n\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJP\u0010\u0014\u001a>\u0012\u0004\u0012\u00020\u0016\u00120\u0012.\u0012\f\u0012\n\u0012\u0002\b\u00030\u0012j\u0002`\u0017\u0012\u0004\u0012\u00020\u00180\u0015j\u0016\u0012\f\u0012\n\u0012\u0002\b\u00030\u0012j\u0002`\u0017\u0012\u0004\u0012\u00020\u0018`\u00190\u0015j\u0002`\u001a2\n\u0010\u001b\u001a\u0006\u0012\u0002\b\u00030\u001cH\u0016R\u001a\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0018\u0010\u0005\u001a\u0006\u0012\u0002\b\u00030\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0014\u0010\u0007\u001a\u00020\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u001e\u0010\u0011\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00120\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\f¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/EnumEntryInitializationInfoData;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;", "properties", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirEnumEntrySymbol;", "receiver", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "graph", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "<init>", "(Ljava/util/Set;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;)V", "getProperties", "()Ljava/util/Set;", "getReceiver", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getGraph", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "conditionallyInitializedProperties", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "getConditionallyInitializedProperties", "getValue", "Lkotlinx/collections/immutable/PersistentMap;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationEvent;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/EventOccurrencesRangeAtNode;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/ControlFlowInfo;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PathAwarePropertyInitializationInfo;", "node", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class EnumEntryInitializationInfoData extends VariableInitializationInfoData {
    private final Set<FirVariableSymbol<?>> conditionallyInitializedProperties;
    private final ControlFlowGraph graph;
    private final Set<FirEnumEntrySymbol> properties;
    private final FirBasedSymbol<?> receiver;

    public EnumEntryInitializationInfoData(Set<FirEnumEntrySymbol> set, FirBasedSymbol<?> firBasedSymbol, ControlFlowGraph controlFlowGraph) {
        set.getClass();
        firBasedSymbol.getClass();
        controlFlowGraph.getClass();
        this.properties = set;
        this.receiver = firBasedSymbol;
        this.graph = controlFlowGraph;
        this.conditionallyInitializedProperties = SetsKt.emptySet();
    }

    @Override // org.jetbrains.kotlin.fir.analysis.cfa.util.VariableInitializationInfoData
    public Set<FirVariableSymbol<?>> getConditionallyInitializedProperties() {
        return this.conditionallyInitializedProperties;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.cfa.util.VariableInitializationInfoData
    public ControlFlowGraph getGraph() {
        return this.graph;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.cfa.util.VariableInitializationInfoData
    public Set<FirEnumEntrySymbol> getProperties() {
        return this.properties;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.cfa.util.VariableInitializationInfoData
    public FirBasedSymbol<?> getReceiver() {
        return this.receiver;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.cfa.util.VariableInitializationInfoData
    public PersistentMap<EdgeLabel, PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode>> getValue(CFGNode<?> node) {
        node.getClass();
        return PathAwareControlFlowGraphVisitorKt.emptyNormalPathInfo();
    }
}
