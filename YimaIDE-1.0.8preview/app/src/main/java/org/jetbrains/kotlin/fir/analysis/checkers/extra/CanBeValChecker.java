package org.jetbrains.kotlin.fir.analysis.checkers.extra;

import com.intellij.lang.LighterASTNode;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.collections.immutable.PersistentMap;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRangeKt;
import org.jetbrains.kotlin.contracts.description.MarkedEventOccurrencesRange;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory0;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.cfa.AbstractFirPropertyInitializationChecker;
import org.jetbrains.kotlin.fir.analysis.cfa.FirPropertyInitializationAnalyzerKt;
import org.jetbrains.kotlin.fir.analysis.cfa.util.EventOccurrencesRangeAtNode;
import org.jetbrains.kotlin.fir.analysis.cfa.util.VariableInitializationInfoData;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitorVoid;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.VariableAssignmentNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.VariableDeclarationExitNode;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.util.LightTreeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\u00020\u0001:\u0001\rB\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J-\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\f¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/CanBeValChecker;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/AbstractFirPropertyInitializationChecker;", "<init>", "()V", "analyze", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "data", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;)V", "ReassignedVariableCollector", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CanBeValChecker extends AbstractFirPropertyInitializationChecker {
    public static final CanBeValChecker INSTANCE = new CanBeValChecker();

    @Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010#\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\u000f\u001a\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0016J\u0010\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0014H\u0016J\u0010\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0016H\u0016J\u0012\u0010\u0017\u001a\u00020\u00052\n\u0010\u0018\u001a\u0006\u0012\u0002\b\u00030\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R*\u0010\b\u001a\u001e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b0\tj\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b`\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\r\u001a\b\u0012\u0004\u0012\u00020\n0\u000eX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/CanBeValChecker$ReassignedVariableCollector;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraphVisitorVoid;", "data", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;", "isForInitialization", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;Z)V", "declaredIn", "Ljava/util/HashMap;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "Lkotlin/collections/HashMap;", "reassigned", Argument.Delimiters.none, "visitNode", Argument.Delimiters.none, "node", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "visitVariableDeclarationExitNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationExitNode;", "visitVariableAssignmentNode", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;", "canBeVal", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ReassignedVariableCollector extends ControlFlowGraphVisitorVoid {
        private final VariableInitializationInfoData data;
        private final HashMap<FirPropertySymbol, ControlFlowGraph> declaredIn;
        private final boolean isForInitialization;
        private final Set<FirPropertySymbol> reassigned;

        public ReassignedVariableCollector(VariableInitializationInfoData variableInitializationInfoData, boolean z) {
            variableInitializationInfoData.getClass();
            this.data = variableInitializationInfoData;
            this.isForInitialization = z;
            this.declaredIn = new HashMap<>();
            this.reassigned = new LinkedHashSet();
        }

        public final boolean canBeVal(FirVariableSymbol<?> symbol) {
            symbol.getClass();
            if (!(symbol instanceof FirPropertySymbol)) {
                w01.a("Failed requirement.");
                return false;
            }
            FirPropertySymbol firPropertySymbol = (FirPropertySymbol) symbol;
            if (firPropertySymbol.isVar() && !firPropertySymbol.getHasDelegate()) {
                KtSourceElement source = firPropertySymbol.getSource();
                KtSourceElementKind kind = source != null ? source.getKind() : null;
                if (!(kind == null ? true : kind instanceof KtFakeSourceElementKind) && !this.reassigned.contains(symbol)) {
                    return true;
                }
            }
            return false;
        }

        @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitorVoid
        public void visitNode(CFGNode<?> node) {
            node.getClass();
        }

        /* JADX WARN: Code duplicated, block: B:36:0x0094 A[RETURN] */
        @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitorVoid
        public void visitVariableAssignmentNode(VariableAssignmentNode node) {
            MarkedEventOccurrencesRange<CFGNode<?>> range;
            node.getClass();
            FirReference calleeReference = ReferenceUtilsKt.getCalleeReference(node.getFir());
            if (calleeReference != null) {
                FirPropertySymbol resolvedPropertySymbol$default = FirReferenceUtilsKt.toResolvedPropertySymbol$default(calleeReference, false, 1, null);
                if (resolvedPropertySymbol$default != null && resolvedPropertySymbol$default.isVar()) {
                    KtSourceElement source = resolvedPropertySymbol$default.getSource();
                    if (((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind) || !this.data.getProperties().contains(resolvedPropertySymbol$default)) {
                        return;
                    }
                    if (FirPropertyInitializationAnalyzerKt.requiresInitialization(resolvedPropertySymbol$default, this.isForInitialization)) {
                        Collection collectionValues = this.data.getValue(node).values();
                        if (!(collectionValues instanceof Collection) || !collectionValues.isEmpty()) {
                            Iterator it = collectionValues.iterator();
                            while (it.hasNext()) {
                                EventOccurrencesRangeAtNode eventOccurrencesRangeAtNode = (EventOccurrencesRangeAtNode) ((PersistentMap) it.next()).get(resolvedPropertySymbol$default);
                                if (eventOccurrencesRangeAtNode == null || (range = eventOccurrencesRangeAtNode.getRange()) == null || !EventOccurrencesRangeKt.canBeRevisited(range)) {
                                }
                            }
                            if (Intrinsics.areEqual(this.declaredIn.get(resolvedPropertySymbol$default), FirPropertyInitializationAnalyzerKt.nearestNonInPlaceGraph(node.getOwner()))) {
                                return;
                            }
                        } else if (Intrinsics.areEqual(this.declaredIn.get(resolvedPropertySymbol$default), FirPropertyInitializationAnalyzerKt.nearestNonInPlaceGraph(node.getOwner()))) {
                            return;
                        }
                    }
                    this.reassigned.add(resolvedPropertySymbol$default);
                }
            }
        }

        @Override // org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraphVisitorVoid
        public void visitVariableDeclarationExitNode(VariableDeclarationExitNode node) {
            node.getClass();
            this.declaredIn.put(node.getFir().getSymbol(), FirPropertyInitializationAnalyzerKt.nearestNonInPlaceGraph(node.getOwner()));
        }
    }

    private CanBeValChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.cfa.AbstractFirPropertyInitializationChecker
    public void analyze(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, VariableInitializationInfoData variableInitializationInfoData) {
        boolean zCanBeVal;
        KtDiagnosticFactory0 can_be_val_lateinit;
        int i;
        diagnosticReporter.getClass();
        checkerContext.getClass();
        variableInitializationInfoData.getClass();
        boolean z = variableInitializationInfoData.getGraph().getKind() == ControlFlowGraph.Kind.Class || variableInitializationInfoData.getGraph().getKind() == ControlFlowGraph.Kind.File;
        ReassignedVariableCollector reassignedVariableCollector = new ReassignedVariableCollector(variableInitializationInfoData, z);
        variableInitializationInfoData.getGraph().traverse(reassignedVariableCollector);
        Iterator<FirVariableSymbol<?>> it = variableInitializationInfoData.getProperties().iterator();
        while (it.hasNext()) {
            FirVariableSymbol<?> next = it.next();
            KtSourceElement source = next.getSource();
            if (source != null) {
                if (Intrinsics.areEqual(source.getElementType(), KtNodeTypes.DESTRUCTURING_DECLARATION)) {
                    List children = LightTreeUtilsKt.getChildren(source.getLighterASTNode(), source.getTreeStructure());
                    if ((children instanceof Collection) && children.isEmpty()) {
                        i = 0;
                    } else {
                        Iterator it2 = children.iterator();
                        i = 0;
                        while (it2.hasNext()) {
                            if (Intrinsics.areEqual(((LighterASTNode) it2.next()).getTokenType(), KtNodeTypes.DESTRUCTURING_DECLARATION_ENTRY) && (i = i + 1) < 0) {
                                CollectionsKt.throwCountOverflow();
                            }
                        }
                    }
                    IntIterator it3 = RangesKt.until(0, i).iterator();
                    while (true) {
                        zCanBeVal = true;
                        while (true) {
                            if (!it3.hasNext()) {
                                break;
                            }
                            it3.nextInt();
                            if (!it.hasNext() || !reassignedVariableCollector.canBeVal(it.next()) || !zCanBeVal) {
                                zCanBeVal = false;
                            }
                        }
                    }
                } else {
                    zCanBeVal = reassignedVariableCollector.canBeVal(next);
                }
                if (zCanBeVal) {
                    if (!(next instanceof FirPropertySymbol) || FirPropertyInitializationAnalyzerKt.requiresInitialization((FirPropertySymbol) next, z)) {
                        can_be_val_lateinit = next.getRawStatus().isLateInit() ? FirErrors.INSTANCE.getCAN_BE_VAL_LATEINIT() : FirErrors.INSTANCE.getCAN_BE_VAL_DELAYED_INITIALIZATION();
                    } else {
                        can_be_val_lateinit = FirErrors.INSTANCE.getCAN_BE_VAL();
                    }
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, can_be_val_lateinit, (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                }
            }
        }
    }
}
