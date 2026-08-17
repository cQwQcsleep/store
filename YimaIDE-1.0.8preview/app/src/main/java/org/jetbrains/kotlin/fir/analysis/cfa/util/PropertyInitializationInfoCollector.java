package org.jetbrains.kotlin.fir.analysis.cfa.util;

import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.MarkedEventOccurrencesRange;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirThisReference;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNodeKt;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNodeWithSubgraphs;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ClassExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.Edge;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.EdgeLabel;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.LoopBlockEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.LoopConditionEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.LoopEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.PostponedLambdaExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.PropertyInitializerEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.PropertyInitializerExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.QualifiedAccessNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.VariableAssignmentNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.VariableDeclarationExitNode;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirThisOwnerSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.util.SetMultimap;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u0010\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u00030\u0001BA\u0012\u0010\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0005\u0012\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0007\u0012\u0016\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\t¢\u0006\u0004\b\u000b\u0010\fJ\u001c\u0010\r\u001a\u00020\u000e2\n\u0010\u000f\u001a\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0090\u0001\u0010\u0013\u001a>\u0012\u0004\u0012\u00020\u0015\u00120\u0012.\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u00160\u0014j\u0016\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u0016`\u00170\u0014j\u0002`\u00182\u0006\u0010\u000f\u001a\u00020\u00192B\u0010\u001a\u001a>\u0012\u0004\u0012\u00020\u0015\u00120\u0012.\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u00160\u0014j\u0016\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u0016`\u00170\u0014j\u0002`\u0018H\u0016J¸\u0001\u0010\u001b\u001aR\u0012\u0004\u0012\u00020\u0015\u00120\u0012.\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u00160\u0014j\u0016\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u0016`\u00170\u0014j\u0016\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u0016`\u001c2\u0006\u0010\u000f\u001a\u00020\u001d2V\u0010\u001a\u001aR\u0012\u0004\u0012\u00020\u0015\u00120\u0012.\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u00160\u0014j\u0016\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u0016`\u00170\u0014j\u0016\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u0016`\u001cH\u0016J\u001e\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\u0010 \u001a\u0004\u0018\u00010!2\b\u0010\"\u001a\u0004\u0018\u00010#H\u0002J\u0090\u0001\u0010$\u001a>\u0012\u0004\u0012\u00020\u0015\u00120\u0012.\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u00160\u0014j\u0016\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u0016`\u00170\u0014j\u0002`\u00182\u0006\u0010\u000f\u001a\u00020%2B\u0010\u001a\u001a>\u0012\u0004\u0012\u00020\u0015\u00120\u0012.\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u00160\u0014j\u0016\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u0016`\u00170\u0014j\u0002`\u0018H\u0016J\u0090\u0001\u0010&\u001a>\u0012\u0004\u0012\u00020\u0015\u00120\u0012.\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u00160\u0014j\u0016\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u0016`\u00170\u0014j\u0002`\u00182\u0006\u0010\u000f\u001a\u00020'2B\u0010\u001a\u001a>\u0012\u0004\u0012\u00020\u0015\u00120\u0012.\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u00160\u0014j\u0016\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u0016`\u00170\u0014j\u0002`\u0018H\u0016J¨\u0001\u0010(\u001a>\u0012\u0004\u0012\u00020\u0015\u00120\u0012.\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u00160\u0014j\u0016\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u0016`\u00170\u0014j\u0002`\u00182\n\u0010)\u001a\u0006\u0012\u0002\b\u00030*2\n\u0010+\u001a\u0006\u0012\u0002\b\u00030*2\u0006\u0010,\u001a\u00020-2B\u0010\u001a\u001a>\u0012\u0004\u0012\u00020\u0015\u00120\u0012.\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u00160\u0014j\u0016\u0012\f\u0012\n\u0012\u0002\b\u00030\u0002j\u0002`\u0003\u0012\u0004\u0012\u00020\u0016`\u00170\u0014j\u0002`\u0018H\u0016R\u0018\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0006\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\n\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006."}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PropertyInitializationInfoCollector;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/EventCollectingControlFlowGraphVisitor;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationEvent;", "localProperties", Argument.Delimiters.none, "expectedReceiver", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "declaredVariablesInLoop", "Lorg/jetbrains/kotlin/fir/util/SetMultimap;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "<init>", "(Ljava/util/Set;Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;Lorg/jetbrains/kotlin/fir/util/SetMultimap;)V", "visitSubGraph", Argument.Delimiters.none, "node", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNodeWithSubgraphs;", "graph", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "visitVariableAssignmentNode", "Lkotlinx/collections/immutable/PersistentMap;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/EventOccurrencesRangeAtNode;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/ControlFlowInfo;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PathAwarePropertyInitializationInfo;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;", "data", "visitQualifiedAccessNode", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/PathAwareControlFlowInfo;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/QualifiedAccessNode;", "toSymbolIfOurs", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "dispatchReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "visitVariableDeclarationExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationExitNode;", "visitPropertyInitializerExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PropertyInitializerExitNode;", "visitEdge", "from", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "to", "metadata", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/Edge;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PropertyInitializationInfoCollector extends EventCollectingControlFlowGraphVisitor<FirVariableSymbol<?>> {
    private final SetMultimap<FirStatement, FirVariableSymbol<?>> declaredVariablesInLoop;
    private final FirBasedSymbol<?> expectedReceiver;
    private final Set<FirVariableSymbol<?>> localProperties;

    /* JADX WARN: Multi-variable type inference failed */
    public PropertyInitializationInfoCollector(Set<? extends FirVariableSymbol<?>> set, FirBasedSymbol<?> firBasedSymbol, SetMultimap<FirStatement, FirVariableSymbol<?>> setMultimap) {
        set.getClass();
        setMultimap.getClass();
        this.localProperties = set;
        this.expectedReceiver = firBasedSymbol;
        this.declaredVariablesInLoop = setMultimap;
    }

    private final FirPropertySymbol toSymbolIfOurs(FirExpression dispatchReceiver, FirReference calleeReference) {
        FirPropertySymbol resolvedPropertySymbol$default;
        FirThisReference calleeReference2;
        FirExpression firExpressionUnwrapSmartcastExpression = dispatchReceiver != null ? FirExpressionUtilKt.unwrapSmartcastExpression(dispatchReceiver) : null;
        FirThisReceiverExpression firThisReceiverExpression = firExpressionUnwrapSmartcastExpression instanceof FirThisReceiverExpression ? (FirThisReceiverExpression) firExpressionUnwrapSmartcastExpression : null;
        FirThisOwnerSymbol<?> boundSymbol = (firThisReceiverExpression == null || (calleeReference2 = firThisReceiverExpression.getCalleeReference()) == null) ? null : calleeReference2.getBoundSymbol();
        if (calleeReference == null || (resolvedPropertySymbol$default = FirReferenceUtilsKt.toResolvedPropertySymbol$default(calleeReference, false, 1, null)) == null || !Intrinsics.areEqual(boundSymbol, this.expectedReceiver) || !this.localProperties.contains(resolvedPropertySymbol$default)) {
            return null;
        }
        return resolvedPropertySymbol$default;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.cfa.util.PathAwareControlFlowGraphVisitor
    public PersistentMap<EdgeLabel, PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode>> visitEdge(CFGNode<?> from, CFGNode<?> to, Edge metadata, PersistentMap<EdgeLabel, ? extends PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode>> data) {
        Set set;
        from.getClass();
        to.getClass();
        metadata.getClass();
        data.getClass();
        PersistentMap<EdgeLabel, PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode>> persistentMapVisitEdge = super.visitEdge(from, to, metadata, data);
        if (metadata.getKind().getIsBack()) {
            if (from instanceof PostponedLambdaExitNode) {
                set = (Set) this.declaredVariablesInLoop.get(((PostponedLambdaExitNode) from).getFir().getAnonymousFunction());
            } else if (to instanceof LoopEnterNode) {
                set = (Set) this.declaredVariablesInLoop.get(((LoopEnterNode) to).getFir());
            } else if (to instanceof LoopBlockEnterNode) {
                set = (Set) this.declaredVariablesInLoop.get(((LoopBlockEnterNode) to).getFir());
            } else if (to instanceof LoopConditionEnterNode) {
                set = (Set) this.declaredVariablesInLoop.get(((LoopConditionEnterNode) to).getLoop());
            }
            Iterator it = set.iterator();
            while (it.hasNext()) {
                data = EventCollectingControlFlowGraphVisitorKt.removeRange(data, (FirVariableSymbol) it.next());
            }
            return data;
        }
        return persistentMapVisitEdge;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public PersistentMap<EdgeLabel, PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode>> visitPropertyInitializerExitNode(PropertyInitializerExitNode node, PersistentMap<EdgeLabel, ? extends PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode>> data) {
        node.getClass();
        data.getClass();
        PersistentMap<EdgeLabel, PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode>> persistentMapVisitNode = visitNode((CFGNode<?>) node, (PersistentMap) data);
        return CFGNodeKt.getFirstPreviousNode(node) instanceof PropertyInitializerEnterNode ? persistentMapVisitNode : EventCollectingControlFlowGraphVisitorKt.overwriteRange(persistentMapVisitNode, node.getFir().getSymbol(), new EventOccurrencesRangeAtNode(new MarkedEventOccurrencesRange.ExactlyOnce(node), false));
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public PersistentMap<EdgeLabel, PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode>> visitQualifiedAccessNode(QualifiedAccessNode node, PersistentMap<EdgeLabel, ? extends PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode>> data) {
        node.getClass();
        data.getClass();
        PersistentMap<EdgeLabel, PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode>> persistentMapVisitNode = visitNode((CFGNode<?>) node, (PersistentMap) data);
        FirPropertySymbol symbolIfOurs = toSymbolIfOurs(node.getFir().getDispatchReceiver(), node.getFir().getCalleeReference());
        return symbolIfOurs == null ? persistentMapVisitNode : EventCollectingControlFlowGraphVisitorKt.addRangeIfEmpty(persistentMapVisitNode, symbolIfOurs, new EventOccurrencesRangeAtNode(MarkedEventOccurrencesRange.Zero.INSTANCE, true));
    }

    @Override // org.jetbrains.kotlin.fir.analysis.cfa.util.PathAwareControlFlowGraphVisitor
    public boolean visitSubGraph(CFGNodeWithSubgraphs<?> node, ControlFlowGraph graph) {
        node.getClass();
        graph.getClass();
        return (this.expectedReceiver != null && (node instanceof ClassExitNode) && node == ((ClassExitNode) node).getOwner().getExitNode()) ? false : true;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public PersistentMap<EdgeLabel, PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode>> visitVariableAssignmentNode(VariableAssignmentNode node, PersistentMap<EdgeLabel, ? extends PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode>> data) {
        node.getClass();
        data.getClass();
        PersistentMap<EdgeLabel, PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode>> persistentMapVisitNode = visitNode((CFGNode<?>) node, (PersistentMap) data);
        FirPropertySymbol symbolIfOurs = toSymbolIfOurs(FirExpressionUtilKt.getDispatchReceiver(node.getFir()), ReferenceUtilsKt.getCalleeReference(node.getFir()));
        return symbolIfOurs == null ? persistentMapVisitNode : EventCollectingControlFlowGraphVisitorKt.addRange(persistentMapVisitNode, symbolIfOurs, new EventOccurrencesRangeAtNode(new MarkedEventOccurrencesRange.ExactlyOnce(node), false));
    }

    @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitor
    public PersistentMap<EdgeLabel, PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode>> visitVariableDeclarationExitNode(VariableDeclarationExitNode node, PersistentMap<EdgeLabel, ? extends PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode>> data) {
        node.getClass();
        data.getClass();
        PersistentMap<EdgeLabel, PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode>> persistentMapVisitNode = visitNode((CFGNode<?>) node, (PersistentMap) data);
        if (this.expectedReceiver != null) {
            return persistentMapVisitNode;
        }
        return (node.getFir().getInitializer() == null && node.getFir().getDelegate() == null) ? EventCollectingControlFlowGraphVisitorKt.removeRange(persistentMapVisitNode, node.getFir().getSymbol()) : EventCollectingControlFlowGraphVisitorKt.overwriteRange(persistentMapVisitNode, node.getFir().getSymbol(), new EventOccurrencesRangeAtNode(new MarkedEventOccurrencesRange.ExactlyOnce(node), false));
    }

    public /* synthetic */ PropertyInitializationInfoCollector(Set set, FirBasedSymbol firBasedSymbol, SetMultimap setMultimap, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(set, (i & 2) != 0 ? null : firBasedSymbol, setMultimap);
    }
}
