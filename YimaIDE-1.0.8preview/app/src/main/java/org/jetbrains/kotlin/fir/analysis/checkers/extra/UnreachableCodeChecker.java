package org.jetbrains.kotlin.fir.analysis.checkers.extra;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.cfa.FirControlFlowChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.BooleanOperatorEnterRightOperandNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.BooleanOperatorExitLeftOperandNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ElvisLhsExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ElvisLhsIsNotNullNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.EnterNodeMarker;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ExitNodeMarker;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ExitSafeCallNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FunctionCallExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.LoopBlockEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.LoopEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.MergePostponedLambdaExitsNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.PostponedLambdaExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.SplitPostponedLambdasNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.StubNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.TryExpressionEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.WhenBranchResultEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.WhenBranchResultExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.WhenSyntheticElseBranchNode;
import org.jetbrains.kotlin.fir.visitors.FirVisitorVoid;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\u001bB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\fJ*\u0010\r\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\u000e*\u00020\u000b2\u0012\b\u0002\u0010\u0010\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\u0011H\u0002J\u0010\u0010\u0015\u001a\u00020\u0016*\u0006\u0012\u0002\b\u00030\u000fH\u0002J\u001a\u0010\u0017\u001a\u00020\u0005*\u00020\u00182\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u001aH\u0002R\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00140\u0013X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/UnreachableCodeChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/cfa/FirControlFlowChecker;", "<init>", "()V", "analyze", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "graph", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;)V", "allNodes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "acc", Argument.Delimiters.none, "sourceKindsToSkip", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/KtFakeSourceElementKind;", "skipNode", Argument.Delimiters.none, "collectInnerNodes", "Lorg/jetbrains/kotlin/fir/FirElement;", "nodes", Argument.Delimiters.none, "CollectNodesVisitor", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class UnreachableCodeChecker extends FirControlFlowChecker {
    public static final UnreachableCodeChecker INSTANCE = new UnreachableCodeChecker();
    private static final Set<KtFakeSourceElementKind> sourceKindsToSkip = SetsKt.setOf(new KtFakeSourceElementKind[]{KtFakeSourceElementKind.ImplicitReturn.FromExpressionBody.INSTANCE, KtFakeSourceElementKind.ImplicitReturn.FromLastStatement.INSTANCE, KtFakeSourceElementKind.DesugaredForLoop.INSTANCE});

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0010\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\u0004H\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/UnreachableCodeChecker$CollectNodesVisitor;", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitorVoid;", "nodes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/FirElement;", "<init>", "(Ljava/util/Set;)V", "visitElement", Argument.Delimiters.none, "element", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class CollectNodesVisitor extends FirVisitorVoid {
        private final Set<FirElement> nodes;

        public CollectNodesVisitor(Set<FirElement> set) {
            set.getClass();
            this.nodes = set;
        }

        public void visitElement(FirElement element) {
            element.getClass();
            this.nodes.add(element);
            element.acceptChildren(this);
        }
    }

    private UnreachableCodeChecker() {
        super(MppCheckerKind.Common);
    }

    private final List<CFGNode<?>> allNodes(ControlFlowGraph controlFlowGraph, List<CFGNode<?>> list) {
        list.addAll(controlFlowGraph.getNodes());
        Iterator<T> it = controlFlowGraph.getSubGraphs().iterator();
        while (it.hasNext()) {
            INSTANCE.allNodes((ControlFlowGraph) it.next(), list);
        }
        return list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ List allNodes$default(UnreachableCodeChecker unreachableCodeChecker, ControlFlowGraph controlFlowGraph, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            list = new ArrayList();
        }
        return unreachableCodeChecker.allNodes(controlFlowGraph, list);
    }

    private final void collectInnerNodes(FirElement firElement, Set<FirElement> set) {
        firElement.acceptChildren(new CollectNodesVisitor(set));
    }

    private final boolean skipNode(CFGNode<?> cFGNode) {
        boolean z = (cFGNode instanceof ExitNodeMarker) || (cFGNode instanceof EnterNodeMarker) || (cFGNode instanceof StubNode) || (cFGNode instanceof SplitPostponedLambdasNode) || (cFGNode instanceof PostponedLambdaExitNode) || (cFGNode instanceof MergePostponedLambdaExitsNode) || (cFGNode instanceof FunctionCallExitNode) || (cFGNode instanceof BooleanOperatorExitLeftOperandNode) || (cFGNode instanceof BooleanOperatorEnterRightOperandNode) || (cFGNode instanceof WhenSyntheticElseBranchNode) || (cFGNode instanceof WhenBranchResultEnterNode) || (cFGNode instanceof WhenBranchResultExitNode) || (cFGNode instanceof ExitSafeCallNode) || (cFGNode instanceof ElvisLhsExitNode) || (cFGNode instanceof ElvisLhsIsNotNullNode);
        if (!(cFGNode instanceof LoopEnterNode) && !(cFGNode instanceof LoopBlockEnterNode) && !(cFGNode instanceof TryExpressionEnterNode)) {
            if (!z) {
                Set<KtFakeSourceElementKind> set = sourceKindsToSkip;
                KtSourceElement source = cFGNode.getFir().getSource();
                if (CollectionsKt.contains(set, source != null ? source.getKind() : null)) {
                }
            }
            return true;
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.cfa.FirControlFlowChecker
    public void analyze(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, ControlFlowGraph controlFlowGraph) {
        DiagnosticReporter diagnosticReporter2;
        CheckerContext checkerContext2;
        diagnosticReporter.getClass();
        checkerContext.getClass();
        controlFlowGraph.getClass();
        List listAllNodes$default = allNodes$default(this, controlFlowGraph, null, 1, null);
        ArrayList arrayList = new ArrayList();
        for (Object obj : listAllNodes$default) {
            if (!INSTANCE.skipNode((CFGNode) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (Object obj2 : arrayList) {
            if (((CFGNode) obj2).getIsDead()) {
                arrayList2.add(obj2);
            } else {
                arrayList3.add(obj2);
            }
        }
        Pair pair = new Pair(arrayList2, arrayList3);
        List list = (List) pair.component1();
        List list2 = (List) pair.component2();
        if (list.isEmpty()) {
            return;
        }
        List list3 = list;
        ArrayList arrayList4 = new ArrayList();
        Iterator it = list3.iterator();
        while (it.hasNext()) {
            KtSourceElement source = ((CFGNode) it.next()).getFir().getSource();
            if (source != null) {
                arrayList4.add(source);
            }
        }
        Set set = CollectionsKt.toSet(arrayList4);
        ArrayList arrayList5 = new ArrayList();
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            KtSourceElement source2 = ((CFGNode) it2.next()).getFir().getSource();
            if (source2 != null) {
                arrayList5.add(source2);
            }
        }
        Set set2 = CollectionsKt.toSet(arrayList5);
        ArrayList arrayList6 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
        Iterator it3 = list3.iterator();
        while (it3.hasNext()) {
            arrayList6.add(((CFGNode) it3.next()).getFir());
        }
        Set<FirElement> linkedHashSet = new LinkedHashSet<>();
        Iterator it4 = arrayList6.iterator();
        while (it4.hasNext()) {
            INSTANCE.collectInnerNodes((FirElement) it4.next(), linkedHashSet);
        }
        HashSet hashSet = new HashSet();
        ArrayList<FirElement> arrayList7 = new ArrayList();
        for (Object obj3 : arrayList6) {
            if (hashSet.add(((FirElement) obj3).getSource())) {
                arrayList7.add(obj3);
            }
        }
        for (FirElement firElement : arrayList7) {
            if (linkedHashSet.contains(firElement)) {
                diagnosticReporter2 = diagnosticReporter;
                checkerContext2 = checkerContext;
            } else {
                diagnosticReporter2 = diagnosticReporter;
                checkerContext2 = checkerContext;
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) firElement.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getUNREACHABLE_CODE(), (Object) set2, (Object) set, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }
            checkerContext = checkerContext2;
            diagnosticReporter = diagnosticReporter2;
        }
    }
}
