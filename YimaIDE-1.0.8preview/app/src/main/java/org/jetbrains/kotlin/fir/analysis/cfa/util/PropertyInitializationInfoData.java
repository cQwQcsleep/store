package org.jetbrains.kotlin.fir.analysis.cfa.util;

import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.cfa.util.PropertyInitializationInfoData;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.EdgeLabel;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.util.MultimapKt;
import org.jetbrains.kotlin.fir.util.SetMultimap;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001BA\u0012\u0010\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003\u0012\u0010\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJP\u0010 \u001a>\u0012\u0004\u0012\u00020\u0017\u00120\u0012.\u0012\f\u0012\n\u0012\u0002\b\u00030\u0004j\u0002`\u0018\u0012\u0004\u0012\u00020\u00190\u0016j\u0016\u0012\f\u0012\n\u0012\u0002\b\u00030\u0004j\u0002`\u0018\u0012\u0004\u0012\u00020\u0019`\u001a0\u0016j\u0002`!2\n\u0010\"\u001a\u0006\u0012\u0002\b\u00030\u0015H\u0016R\u001e\u0010\u0002\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001e\u0010\u0005\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00040\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u001a\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R{\u0010\u0013\u001ab\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0015\u0012T\u0012R\u0012\u0004\u0012\u00020\u0017\u00120\u0012.\u0012\f\u0012\n\u0012\u0002\b\u00030\u0004j\u0002`\u0018\u0012\u0004\u0012\u00020\u00190\u0016j\u0016\u0012\f\u0012\n\u0012\u0002\b\u00030\u0004j\u0002`\u0018\u0012\u0004\u0012\u00020\u0019`\u001a0\u0016j\u0016\u0012\f\u0012\n\u0012\u0002\b\u00030\u0004j\u0002`\u0018\u0012\u0004\u0012\u00020\u0019`\u001b0\u00148BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001e\u0010\u001f\u001a\u0004\b\u001c\u0010\u001d¨\u0006#"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PropertyInitializationInfoData;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;", "properties", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "conditionallyInitializedProperties", "receiver", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "graph", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "<init>", "(Ljava/util/Set;Ljava/util/Set;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;)V", "getProperties", "()Ljava/util/Set;", "getConditionallyInitializedProperties", "getReceiver", "()Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "getGraph", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "data", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "Lkotlinx/collections/immutable/PersistentMap;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationEvent;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/EventOccurrencesRangeAtNode;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/ControlFlowInfo;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PathAwareControlFlowInfo;", "getData", "()Ljava/util/Map;", "data$delegate", "Lkotlin/Lazy;", "getValue", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PathAwarePropertyInitializationInfo;", "node", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PropertyInitializationInfoData extends VariableInitializationInfoData {
    private final Set<FirVariableSymbol<?>> conditionallyInitializedProperties;

    /* JADX INFO: renamed from: data$delegate, reason: from kotlin metadata */
    private final Lazy data;
    private final ControlFlowGraph graph;
    private final Set<FirVariableSymbol<?>> properties;
    private final FirBasedSymbol<?> receiver;

    /* JADX WARN: Multi-variable type inference failed */
    public PropertyInitializationInfoData(Set<? extends FirVariableSymbol<?>> set, Set<? extends FirVariableSymbol<?>> set2, FirBasedSymbol<?> firBasedSymbol, ControlFlowGraph controlFlowGraph) {
        set.getClass();
        set2.getClass();
        controlFlowGraph.getClass();
        this.properties = set;
        this.conditionallyInitializedProperties = set2;
        this.receiver = firBasedSymbol;
        this.graph = controlFlowGraph;
        this.data = LazyKt.lazy(LazyThreadSafetyMode.NONE, new Function0() { // from class: mgb
            public final Object invoke() {
                return PropertyInitializationInfoData.a(this.b);
            }
        });
    }

    public static Map a(PropertyInitializationInfoData propertyInitializationInfoData) {
        SetMultimap multimapOf = MultimapKt.setMultimapOf();
        FirDeclaration declaration = propertyInitializationInfoData.getGraph().getDeclaration();
        if (declaration != null) {
            declaration.accept(new PropertyDeclarationCollector(multimapOf), null);
        }
        return CfgTraverserKt.traverseToFixedPoint(propertyInitializationInfoData.getGraph(), new PropertyInitializationInfoCollector(propertyInitializationInfoData.getProperties(), propertyInitializationInfoData.getReceiver(), multimapOf));
    }

    private final Map<CFGNode<?>, PersistentMap<EdgeLabel, PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode>>> getData() {
        return (Map) this.data.getValue();
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
    public Set<FirVariableSymbol<?>> getProperties() {
        return this.properties;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.cfa.util.VariableInitializationInfoData
    public FirBasedSymbol<?> getReceiver() {
        return this.receiver;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.cfa.util.VariableInitializationInfoData
    public PersistentMap<EdgeLabel, PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode>> getValue(CFGNode<?> node) {
        node.getClass();
        return (PersistentMap) MapsKt.getValue(getData(), node);
    }
}
