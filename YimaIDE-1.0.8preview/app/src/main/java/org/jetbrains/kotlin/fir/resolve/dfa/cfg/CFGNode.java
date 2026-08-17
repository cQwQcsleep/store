package org.jetbrains.kotlin.fir.resolve.dfa.cfg;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.resolve.dfa.FlowPath;
import org.jetbrains.kotlin.fir.resolve.dfa.PersistentFlow;
import org.jetbrains.kotlin.utils.SmartList;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¶\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000b\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0006\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000 S*\n\b\u0000\u0010\u0001 \u0001*\u00020\u00022\u00020\u0003:\u0001SB\u0019\b\u0004\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u001c\u0010\u001f\u001a\u00020 2\n\u0010!\u001a\u0006\u0012\u0002\b\u00030\u00002\u0006\u0010\"\u001a\u00020\u001eH\u0002J\u0012\u0010#\u001a\u00020\u001e2\n\u0010$\u001a\u0006\u0012\u0002\b\u00030\u0000J\u0012\u0010%\u001a\u00020\u001e2\n\u0010$\u001a\u0006\u0012\u0002\b\u00030\u0000J\u0012\u0010=\u001a\u0004\u0018\u00010.2\u0006\u0010>\u001a\u000208H\u0016J\u001c\u0010?\u001a\u00020 2\u0006\u0010>\u001a\u0002082\u0006\u00101\u001a\u00020.H\u0017b\u0002\b6J\f\u0010@\u001a\u00020 H\u0007b\u0002\b6J \u0010A\u001a\u00020 2\n\u0010!\u001a\u0006\u0012\u0002\b\u00030\u00002\u0006\u0010B\u001a\u00020CH\u0017b\u0002\b6JD\u0010D\u001a\u0002HE\"\u0004\b\u0001\u0010E2\u0006\u0010\u0004\u001a\u0002HE2\u0006\u0010F\u001a\u00020G2\u0006\u0010B\u001a\u00020C2\u0012\u0010H\u001a\u000e\u0012\u0004\u0012\u00020G\u0012\u0004\u0012\u0002HE0IH\u0083\bb\u0002\b6¢\u0006\u0002\u0010JJ5\u0010K\u001a\u0002HL\"\u0004\b\u0001\u0010L\"\u0004\b\u0002\u0010M2\u0012\u0010N\u001a\u000e\u0012\u0004\u0012\u0002HL\u0012\u0004\u0012\u0002HM0O2\u0006\u0010P\u001a\u0002HMH&¢\u0006\u0002\u0010QJ\u000e\u0010K\u001a\u00020 2\u0006\u0010N\u001a\u00020RR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000e\u001a\u00020\u0007¢\u0006\u000e\n\u0000\u0012\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\rR\u0014\u0010\u0012\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0014R\u001d\u0010\u0015\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00000\u0016¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u0017\u0010\u0018R\u001d\u0010\u001a\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00000\u0016¢\u0006\n\n\u0002\u0010\u0019\u001a\u0004\b\u001b\u0010\u0018R \u0010\u001c\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0000\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u0012\u0010&\u001a\u00028\u0000X¦\u0004¢\u0006\u0006\u001a\u0004\b'\u0010(R$\u0010*\u001a\u00020\u00132\u0006\u0010)\u001a\u00020\u0013@DX\u0086\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b*\u0010\u0014\"\u0004\b+\u0010,R\u0010\u0010-\u001a\u0004\u0018\u00010.X\u0082\u000e¢\u0006\u0002\n\u0000R\u0014\u0010/\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b0\u0010\u0014R)\u00101\u001a\u00020.2\u0006\u0010)\u001a\u00020.8V@WX\u0096\u000e\u0082\u0001\u0002\b6¢\u0006\f\u001a\u0004\b2\u00103\"\u0004\b4\u00105R\u001c\u00107\u001a\u0010\u0012\u0004\u0012\u000208\u0012\u0004\u0012\u00020.\u0018\u00010\u001dX\u0082\u000e¢\u0006\u0002\n\u0000R\u001a\u00109\u001a\b\u0012\u0004\u0012\u0002080:8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u0010<\u0082\u0001nTUVWXYZ[\\]^_`abcdefghijklmnopqrstuvwxyz{|}~\u007f\u0080\u0001\u0081\u0001\u0082\u0001\u0083\u0001\u0084\u0001\u0085\u0001\u0086\u0001\u0087\u0001\u0088\u0001\u0089\u0001\u008a\u0001\u008b\u0001\u008c\u0001\u008d\u0001\u008e\u0001\u008f\u0001\u0090\u0001\u0091\u0001\u0092\u0001\u0093\u0001\u0094\u0001\u0095\u0001\u0096\u0001\u0097\u0001\u0098\u0001\u0099\u0001\u009a\u0001\u009b\u0001\u009c\u0001\u009d\u0001\u009e\u0001\u009f\u0001 \u0001¨\u0006¡\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", Argument.Delimiters.none, "owner", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "level", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;I)V", "getOwner", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "getLevel", "()I", "id", "getId$annotations", "()V", "getId", "isUnion", Argument.Delimiters.none, "()Z", "previousNodes", Argument.Delimiters.none, "getPreviousNodes", "()Ljava/util/List;", "Lorg/jetbrains/kotlin/utils/SmartList;", "followingNodes", "getFollowingNodes", "_incomingEdges", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/Edge;", "insertIncomingEdge", Argument.Delimiters.none, "from", "edge", "edgeFrom", "other", "edgeTo", "fir", "getFir", "()Lorg/jetbrains/kotlin/fir/FirElement;", "value", "isDead", "setDead", "(Z)V", "_flow", "Lorg/jetbrains/kotlin/fir/resolve/dfa/PersistentFlow;", "flowInitialized", "getFlowInitialized", "flow", "getFlow", "()Lorg/jetbrains/kotlin/fir/resolve/dfa/PersistentFlow;", "setFlow", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/PersistentFlow;)V", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CfgInternals;", "_alternateFlows", "Lorg/jetbrains/kotlin/fir/resolve/dfa/FlowPath;", "alternateFlowPaths", Argument.Delimiters.none, "getAlternateFlowPaths", "()Ljava/util/Set;", "getAlternateFlow", ModuleXmlParser.PATH, "addAlternateFlow", "updateDeadStatus", "copyData", "mapper", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowNodeMapper;", "mapLabelOwner", "T", CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", "factory", "Lkotlin/Function1;", "(Ljava/lang/Object;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowNodeMapper;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "accept", "R", "D", "visitor", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitor;", "data", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitor;Ljava/lang/Object;)Ljava/lang/Object;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitorVoid;", "Companion", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousFunctionCaptureNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/AnonymousObjectExpressionExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BlockEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BlockExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorEnterRightOperandNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorExitLeftOperandNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/BooleanOperatorExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNodeWithSubgraphs;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CallableReferenceNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CatchClauseEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CatchClauseExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CheckNotNullCallNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CodeFragmentEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CodeFragmentExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ComparisonExpressionNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/DelegateExpressionExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/DelegatedConstructorCallNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisLhsExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisLhsIsNotNullNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisRhsEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterDefaultArgumentsNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EnterSafeCallNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EqualityOperatorCallNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitDefaultArgumentsNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitSafeCallNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ExitValueParameterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FakeExpressionEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FieldInitializerEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FieldInitializerExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FileExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FinallyBlockEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FinallyBlockExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallArgumentsEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallArgumentsExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionCallExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/FunctionExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/GetClassCallNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/InitBlockEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/InitBlockExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/JumpNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LiteralExpressionNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopBlockEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopBlockExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopConditionEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopConditionExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/LoopExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/MergePostponedLambdaExitsNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PostponedLambdaExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PropertyInitializerEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/PropertyInitializerExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/QualifiedAccessNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ResolvedQualifierNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ScriptExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/SmartCastExpressionExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/StringConcatenationCallNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/StubNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ThrowExceptionNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryExpressionEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryExpressionExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryMainBlockEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TryMainBlockExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/TypeOperatorCallNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchConditionEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchConditionExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchResultEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenBranchResultExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenEnterNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenSubjectExpressionExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/WhenSyntheticElseBranchNode;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class CFGNode<E extends FirElement> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private Map<FlowPath, PersistentFlow> _alternateFlows;
    private PersistentFlow _flow;
    private Map<CFGNode<?>, Edge> _incomingEdges;
    private final SmartList<CFGNode<?>> followingNodes;
    private final int id;
    private boolean isDead;
    private final int level;
    private final ControlFlowGraph owner;
    private final SmartList<CFGNode<?>> previousNodes;

    private CFGNode(ControlFlowGraph controlFlowGraph, int i) {
        this.owner = controlFlowGraph;
        this.level = i;
        int nodeCount = controlFlowGraph.getNodeCount();
        controlFlowGraph.setNodeCount(nodeCount + 1);
        this.id = nodeCount;
        this.previousNodes = new SmartList<>();
        this.followingNodes = new SmartList<>();
    }

    public static /* synthetic */ void getId$annotations() {
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void insertIncomingEdge(CFGNode<?> from, Edge edge) {
        Map<CFGNode<?>, Edge> map = this._incomingEdges;
        if (map != null) {
            map.put(from, edge);
        } else {
            this._incomingEdges = MapsKt.mutableMapOf(new Pair[]{TuplesKt.to(from, edge)});
        }
    }

    public abstract <R, D> R accept(ControlFlowGraphVisitor<? extends R, ? super D> visitor, D data);

    public final void accept(ControlFlowGraphVisitorVoid visitor) {
        visitor.getClass();
        accept(visitor, null);
    }

    @CfgInternals
    public void addAlternateFlow(FlowPath path, PersistentFlow flow) {
        path.getClass();
        flow.getClass();
        FlowPath.Default r0 = FlowPath.Default.INSTANCE;
        Map<FlowPath, PersistentFlow> map = this._alternateFlows;
        if (map != null) {
            map.get(path);
        }
        Map linkedHashMap = this._alternateFlows;
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap();
            this._alternateFlows = linkedHashMap;
        }
        linkedHashMap.put(path, flow);
    }

    @CfgInternals
    public void copyData(CFGNode<?> from, ControlFlowNodeMapper mapper) {
        from.getClass();
        mapper.getClass();
        Iterator it = from.previousNodes.iterator();
        while (it.hasNext()) {
            CFGNode cFGNode = (CFGNode) it.next();
            SmartList<CFGNode<?>> smartList = this.previousNodes;
            cFGNode.getClass();
            smartList.add(mapper.get(cFGNode));
        }
        Iterator it2 = from.followingNodes.iterator();
        while (it2.hasNext()) {
            CFGNode cFGNode2 = (CFGNode) it2.next();
            SmartList<CFGNode<?>> smartList2 = this.followingNodes;
            cFGNode2.getClass();
            smartList2.add(mapper.get(cFGNode2));
        }
        Map<CFGNode<?>, Edge> map = from._incomingEdges;
        if (map != null) {
            for (Map.Entry<CFGNode<?>, Edge> entry : map.entrySet()) {
                CFGNode<?> key = entry.getKey();
                Edge value = entry.getValue();
                Object label = value.getLabel();
                if (label instanceof CFGNode) {
                    value = new Edge((EdgeLabel) mapper.get((CFGNode) label), value.getKind());
                }
                insertIncomingEdge(mapper.get(key), value);
            }
        }
        if (!(getFir() instanceof FirStub)) {
            this._flow = from._flow;
        }
        this.isDead = from.isDead;
        Map<FlowPath, PersistentFlow> map2 = from._alternateFlows;
        if (map2 != null) {
            for (Map.Entry<FlowPath, PersistentFlow> entry2 : map2.entrySet()) {
                FlowPath key2 = entry2.getKey();
                PersistentFlow value2 = entry2.getValue();
                if (key2 instanceof FlowPath.CfgEdge) {
                    FlowPath.CfgEdge cfgEdge = (FlowPath.CfgEdge) key2;
                    Object label2 = cfgEdge.getLabel();
                    if (label2 instanceof CFGNode) {
                        key2 = new FlowPath.CfgEdge((EdgeLabel) mapper.get((CFGNode) label2), cfgEdge.getFir());
                    }
                } else if (!Intrinsics.areEqual(key2, FlowPath.Default.INSTANCE)) {
                    bu8.a();
                    return;
                }
                addAlternateFlow(key2, value2);
            }
        }
    }

    public final Edge edgeFrom(CFGNode<?> other) {
        Edge edge;
        other.getClass();
        Map<CFGNode<?>, Edge> map = this._incomingEdges;
        return (map == null || (edge = map.get(other)) == null) ? Edge.INSTANCE.getNormal_Forward() : edge;
    }

    public final Edge edgeTo(CFGNode<?> other) {
        other.getClass();
        return other.edgeFrom(this);
    }

    public PersistentFlow getAlternateFlow(FlowPath path) {
        path.getClass();
        Map<FlowPath, PersistentFlow> map = this._alternateFlows;
        if (map != null) {
            return map.get(path);
        }
        return null;
    }

    public Set<FlowPath> getAlternateFlowPaths() {
        Set<FlowPath> setKeySet;
        Map<FlowPath, PersistentFlow> map = this._alternateFlows;
        return (map == null || (setKeySet = map.keySet()) == null) ? SetsKt.emptySet() : setKeySet;
    }

    public abstract E getFir();

    public PersistentFlow getFlow() {
        PersistentFlow persistentFlow = this._flow;
        if (persistentFlow != null) {
            return persistentFlow;
        }
        rc9.a("flow for ", this, " not initialized - traversing nodes in wrong order?");
        return null;
    }

    public boolean getFlowInitialized() {
        return this._flow != null;
    }

    public final List<CFGNode<?>> getFollowingNodes() {
        return this.followingNodes;
    }

    public final int getId() {
        return this.id;
    }

    public final int getLevel() {
        return this.level;
    }

    public final ControlFlowGraph getOwner() {
        return this.owner;
    }

    public final List<CFGNode<?>> getPreviousNodes() {
        return this.previousNodes;
    }

    /* JADX INFO: renamed from: isDead, reason: from getter */
    public final boolean getIsDead() {
        return this.isDead;
    }

    public boolean isUnion() {
        return false;
    }

    public final void setDead(boolean z) {
        this.isDead = z;
    }

    @CfgInternals
    public void setFlow(PersistentFlow persistentFlow) {
        persistentFlow.getClass();
        this._flow = persistentFlow;
    }

    /* JADX WARN: Code duplicated, block: B:16:0x003b  */
    @CfgInternals
    public final void updateDeadStatus() {
        boolean zIsUnion = isUnion();
        Map<CFGNode<?>, Edge> map = this._incomingEdges;
        boolean z = true;
        if (zIsUnion) {
            if (map == null) {
                z = false;
                break;
            }
            Collection<Edge> collectionValues = map.values();
            if (!(collectionValues instanceof Collection) || !collectionValues.isEmpty()) {
                Iterator<T> it = collectionValues.iterator();
                while (it.hasNext()) {
                    if (((Edge) it.next()).getKind().getIsDead()) {
                    }
                }
                z = false;
                break;
            }
            z = false;
            break;
        }
        if (map == null || map.size() != this.previousNodes.size()) {
            z = false;
            break;
        }
        Collection<Edge> collectionValues2 = map.values();
        if (!(collectionValues2 instanceof Collection) || !collectionValues2.isEmpty()) {
            for (Edge edge : collectionValues2) {
                if (!edge.getKind().getIsDead() && edge.getKind().getUsedInCfa()) {
                    z = false;
                    break;
                }
            }
        }
        this.isDead = z;
    }

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J>\u0010\u0004\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0007b\u0002\b\u000fJ,\u0010\u0010\u001a\u00020\f2\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u00072\u0006\u0010\u000b\u001a\u00020\fH\u0007b\u0002\b\u000fJ\u0018\u0010\u0011\u001a\u00020\u00052\n\u0010\u0006\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0007b\u0002\b\u000fJ\u0018\u0010\u0012\u001a\u00020\u00052\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\u0007H\u0007b\u0002\b\u000f¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode$Companion;", Argument.Delimiters.none, "<init>", "()V", "addEdge", Argument.Delimiters.none, "from", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "to", "kind", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeKind;", "propagateDeadness", Argument.Delimiters.none, CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/EdgeLabel;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CfgInternals;", "killEdge", "removeAllOutgoingEdges", "removeAllIncomingEdges", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ void addEdge$default(Companion companion, CFGNode cFGNode, CFGNode cFGNode2, EdgeKind edgeKind, boolean z, EdgeLabel edgeLabel, int i, Object obj) {
            if ((i & 16) != 0) {
                edgeLabel = NormalPath.INSTANCE;
            }
            companion.addEdge(cFGNode, cFGNode2, edgeKind, z, edgeLabel);
        }

        @CfgInternals
        public final void addEdge(CFGNode<?> from, CFGNode<?> to, EdgeKind kind, boolean propagateDeadness, EdgeLabel label) {
            from.getClass();
            to.getClass();
            kind.getClass();
            label.getClass();
            from.getFollowingNodes().add(to);
            to.getPreviousNodes().add(from);
            if (kind != EdgeKind.Forward || !Intrinsics.areEqual(label, NormalPath.INSTANCE)) {
                to.insertIncomingEdge(from, Edge.INSTANCE.create(label, kind));
            }
            if (propagateDeadness && kind.getIsDead() && !kind.getIsBack()) {
                to.setDead(true);
            }
        }

        @CfgInternals
        public final boolean killEdge(CFGNode<?> from, CFGNode<?> to, boolean propagateDeadness) {
            from.getClass();
            to.getClass();
            Edge edgeEdgeFrom = to.edgeFrom(from);
            if (edgeEdgeFrom.getKind().getIsDead()) {
                return false;
            }
            to.insertIncomingEdge(from, Edge.INSTANCE.create(edgeEdgeFrom.getLabel(), edgeEdgeFrom.getKind().toDead()));
            if (propagateDeadness) {
                to.setDead(true);
            }
            return true;
        }

        @CfgInternals
        public final void removeAllIncomingEdges(CFGNode<?> to) {
            to.getClass();
            Iterator it = to.getPreviousNodes().iterator();
            it.getClass();
            while (it.hasNext()) {
                ((CFGNode) it.next()).getFollowingNodes().remove(to);
            }
            to.getPreviousNodes().clear();
            Map map = ((CFGNode) to)._incomingEdges;
            if (map != null) {
                map.clear();
            }
        }

        @CfgInternals
        public final void removeAllOutgoingEdges(CFGNode<?> from) {
            from.getClass();
            Iterator it = from.getFollowingNodes().iterator();
            it.getClass();
            while (it.hasNext()) {
                CFGNode cFGNode = (CFGNode) it.next();
                cFGNode.getPreviousNodes().remove(from);
                Map map = cFGNode._incomingEdges;
                if (map != null) {
                }
            }
            from.getFollowingNodes().clear();
        }

        private Companion() {
        }
    }

    public /* synthetic */ CFGNode(ControlFlowGraph controlFlowGraph, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(controlFlowGraph, i);
    }
}
