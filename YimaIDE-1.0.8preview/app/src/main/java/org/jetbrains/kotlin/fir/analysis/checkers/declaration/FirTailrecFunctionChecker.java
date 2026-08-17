package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.dfa.FirControlFlowGraphReferenceImplKt;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CatchClauseEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CatchClauseExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitorVoid;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.Edge;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ElvisExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ElvisLhsExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ElvisLhsIsNotNullNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FinallyBlockEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FinallyBlockExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FunctionCallExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FunctionExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.TailrecExitNodeMarker;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.TryMainBlockEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.TryMainBlockExitNode;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ \u0010\u000e\u001a\u00020\u000f*\u0006\u0012\u0002\b\u00030\u00102\u0006\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u0015*\u00020\u00168F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirTailrecFunctionChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunctionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)V", "hasMoreFollowingInstructions", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "tailrecFunction", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "correspondingElvisExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisExitNode;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisLhsExitNode;", "getCorrespondingElvisExitNode", "(Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisLhsExitNode;)Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ElvisExitNode;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTailrecFunctionChecker extends FirDeclarationChecker<FirFunction> {
    public static final FirTailrecFunctionChecker INSTANCE = new FirTailrecFunctionChecker();

    private FirTailrecFunctionChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean hasMoreFollowingInstructions(CFGNode<?> cFGNode, FirFunction firFunction, FirSession firSession) {
        boolean zHasMoreFollowingInstructions;
        boolean zCanBeNull$default = TypeUtilsKt.canBeNull$default(FirTypeUtilsKt.getConeType(firFunction.getReturnTypeRef()), firSession, false, null, 6, null);
        for (CFGNode<?> cFGNode2 : cFGNode.getFollowingNodes()) {
            Edge edgeEdgeTo = cFGNode.edgeTo(cFGNode2);
            if (edgeEdgeTo.getKind().getUsedInCfa() && !edgeEdgeTo.getKind().getIsDead()) {
                if (edgeEdgeTo.getKind().getIsBack()) {
                    return true;
                }
                if (cFGNode2 instanceof FunctionExitNode) {
                    return !Intrinsics.areEqual(((FunctionExitNode) cFGNode2).getFir(), firFunction);
                }
                if (!(cFGNode2 instanceof ElvisLhsExitNode) || zCanBeNull$default) {
                    if (cFGNode2 instanceof TailrecExitNodeMarker) {
                        zHasMoreFollowingInstructions = hasMoreFollowingInstructions(cFGNode2, firFunction, firSession);
                    }
                    return true;
                }
                ElvisExitNode correspondingElvisExitNode = getCorrespondingElvisExitNode((ElvisLhsExitNode) cFGNode2);
                if (correspondingElvisExitNode == null) {
                    return true;
                }
                zHasMoreFollowingInstructions = hasMoreFollowingInstructions(correspondingElvisExitNode, firFunction, firSession);
                if (zHasMoreFollowingInstructions) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(final CheckerContext checkerContext, final DiagnosticReporter diagnosticReporter, final FirFunction firFunction) {
        ControlFlowGraph controlFlowGraph;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunction.getClass();
        if (firFunction.getStatus().isTailRec()) {
            if (!DeclarationUtilsKt.isEffectivelyFinal(firFunction) && !Intrinsics.areEqual(firFunction.getStatus().getVisibility(), Visibilities.Private.INSTANCE)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunction.getSource(), FirErrors.INSTANCE.getTAILREC_ON_VIRTUAL_MEMBER_ERROR(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
            FirControlFlowGraphReference controlFlowGraphReference = firFunction.getControlFlowGraphReference();
            if (controlFlowGraphReference == null || (controlFlowGraph = FirControlFlowGraphReferenceImplKt.getControlFlowGraph(controlFlowGraphReference)) == null) {
                return;
            }
            final Ref.IntRef intRef = new Ref.IntRef();
            final Ref.IntRef intRef2 = new Ref.IntRef();
            final Ref.IntRef intRef3 = new Ref.IntRef();
            final Ref.IntRef intRef4 = new Ref.IntRef();
            controlFlowGraph.traverse(new ControlFlowGraphVisitorVoid() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirTailrecFunctionChecker.check.1
                @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitorVoid
                public void visitCatchClauseEnterNode(CatchClauseEnterNode node) {
                    node.getClass();
                    intRef2.element++;
                }

                @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitorVoid
                public void visitCatchClauseExitNode(CatchClauseExitNode node) {
                    node.getClass();
                    intRef2.element--;
                }

                @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitorVoid
                public void visitFinallyBlockEnterNode(FinallyBlockEnterNode node) {
                    node.getClass();
                    intRef3.element++;
                }

                @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitorVoid
                public void visitFinallyBlockExitNode(FinallyBlockExitNode node) {
                    node.getClass();
                    intRef3.element--;
                }

                @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitorVoid
                public void visitFunctionCallExitNode(FunctionCallExitNode node) {
                    ClassKind classKind;
                    node.getClass();
                    FirFunctionCall fir = node.getFir();
                    FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(fir.getCalleeReference(), false, 1, null);
                    FirNamedFunctionSymbol firNamedFunctionSymbol = resolvedCallableSymbol$default instanceof FirNamedFunctionSymbol ? (FirNamedFunctionSymbol) resolvedCallableSymbol$default : null;
                    if (firNamedFunctionSymbol != null && Intrinsics.areEqual(firNamedFunctionSymbol, firFunction.getSymbol())) {
                        if (fir.getArgumentList().getArguments().size() != firNamedFunctionSymbol.getValueParameterSymbols().size() && firNamedFunctionSymbol.getResolvedStatus().isOverride()) {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) fir.getSource(), FirErrors.INSTANCE.getNON_TAIL_RECURSIVE_CALL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                            return;
                        }
                        FirExpression dispatchReceiver = fir.getDispatchReceiver();
                        ConeSimpleKotlinType dispatchReceiverType = firFunction.getDispatchReceiverType();
                        FirClassSymbol<?> classSymbol = dispatchReceiverType != null ? ToSymbolUtilsKt.toClassSymbol(checkerContext, dispatchReceiverType) : null;
                        if (dispatchReceiver != null && ((!(dispatchReceiver instanceof FirThisReceiverExpression) || !Intrinsics.areEqual(((FirThisReceiverExpression) dispatchReceiver).getCalleeReference().getBoundSymbol(), classSymbol)) && (classSymbol == null || (classKind = classSymbol.getClassKind()) == null || !classKind.isSingleton()))) {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) fir.getSource(), FirErrors.INSTANCE.getNON_TAIL_RECURSIVE_CALL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                            return;
                        }
                        if (intRef.element > 0 || intRef2.element > 0 || intRef3.element > 0) {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) fir.getSource(), FirErrors.INSTANCE.getTAIL_RECURSION_IN_TRY_IS_NOT_SUPPORTED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                            return;
                        }
                        if (FirTailrecFunctionChecker.INSTANCE.hasMoreFollowingInstructions(node, firFunction, checkerContext.getSession())) {
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) fir.getSource(), FirErrors.INSTANCE.getNON_TAIL_RECURSIVE_CALL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                        } else {
                            if (node.getIsDead()) {
                                return;
                            }
                            intRef4.element++;
                        }
                    }
                }

                @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitorVoid
                public void visitNode(CFGNode<?> node) {
                    node.getClass();
                }

                @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitorVoid
                public void visitTryMainBlockEnterNode(TryMainBlockEnterNode node) {
                    node.getClass();
                    intRef.element++;
                }

                @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitorVoid
                public void visitTryMainBlockExitNode(TryMainBlockExitNode node) {
                    node.getClass();
                    intRef.element--;
                }
            });
            if (intRef4.element == 0) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunction.getSource(), FirErrors.INSTANCE.getNO_TAIL_CALLS_FOUND(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
    }

    public final ElvisExitNode getCorrespondingElvisExitNode(ElvisLhsExitNode elvisLhsExitNode) {
        Object obj;
        Object next;
        List<CFGNode<?>> followingNodes;
        elvisLhsExitNode.getClass();
        Iterator<T> it = elvisLhsExitNode.getFollowingNodes().iterator();
        do {
            obj = null;
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (!(next instanceof ElvisLhsIsNotNullNode));
        ElvisLhsIsNotNullNode elvisLhsIsNotNullNode = (ElvisLhsIsNotNullNode) next;
        if (elvisLhsIsNotNullNode == null || (followingNodes = elvisLhsIsNotNullNode.getFollowingNodes()) == null) {
            return null;
        }
        for (Object obj2 : followingNodes) {
            if (obj2 instanceof ElvisExitNode) {
                obj = obj2;
                break;
            }
        }
        return (ElvisExitNode) obj;
    }
}
