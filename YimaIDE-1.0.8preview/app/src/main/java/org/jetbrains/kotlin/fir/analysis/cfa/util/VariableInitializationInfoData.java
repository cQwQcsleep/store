package org.jetbrains.kotlin.fir.analysis.cfa.util;

import java.util.Set;
import kotlin.Metadata;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.EdgeLabel;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003JP\u0010\u0013\u001a>\u0012\u0004\u0012\u00020\u0015\u00120\u0012.\u0012\f\u0012\n\u0012\u0002\b\u00030\u0006j\u0002`\u0016\u0012\u0004\u0012\u00020\u00170\u0014j\u0016\u0012\f\u0012\n\u0012\u0002\b\u00030\u0006j\u0002`\u0016\u0012\u0004\u0012\u00020\u0017`\u00180\u0014j\u0002`\u00192\n\u0010\u001a\u001a\u0006\u0012\u0002\b\u00030\u001bH&R\u001c\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0007\u0010\bR\u001c\u0010\t\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\bR\u0018\u0010\u000b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eR\u0012\u0010\u000f\u001a\u00020\u0010X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;", Argument.Delimiters.none, "<init>", "()V", "properties", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "getProperties", "()Ljava/util/Set;", "conditionallyInitializedProperties", "getConditionallyInitializedProperties", "receiver", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getReceiver", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "graph", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "getGraph", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "getValue", "Lkotlinx/collections/immutable/PersistentMap;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationEvent;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/EventOccurrencesRangeAtNode;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/ControlFlowInfo;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PathAwarePropertyInitializationInfo;", "node", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class VariableInitializationInfoData {
    public abstract Set<FirVariableSymbol<?>> getConditionallyInitializedProperties();

    public abstract ControlFlowGraph getGraph();

    public abstract Set<FirVariableSymbol<?>> getProperties();

    public abstract FirBasedSymbol<?> getReceiver();

    public abstract PersistentMap<EdgeLabel, PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode>> getValue(CFGNode<?> node);
}
