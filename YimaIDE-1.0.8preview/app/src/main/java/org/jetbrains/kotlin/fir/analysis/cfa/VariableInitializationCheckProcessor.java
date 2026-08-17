package org.jetbrains.kotlin.fir.analysis.cfa;

import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.collections.immutable.ExtensionsKt;
import kotlinx.collections.immutable.PersistentMap;
import kotlinx.collections.immutable.PersistentSet;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.EventOccurrencesRangeKt;
import org.jetbrains.kotlin.contracts.description.MarkedEventOccurrencesRange;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.fir.analysis.cfa.util.CfgTraverserKt;
import org.jetbrains.kotlin.fir.analysis.cfa.util.EventOccurrencesRangeAtNode;
import org.jetbrains.kotlin.fir.analysis.cfa.util.VariableInitializationInfoData;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.diagnostics.DiagnosticKind;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirImplicitInvokeCall;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNodeKt;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CFGNodeWithSubgraphs;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.CapturedByValue;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.ControlFlowGraph;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.EdgeLabel;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FunctionCallArgumentsExitNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.FunctionCallEnterNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.QualifiedAccessNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.VariableAssignmentNode;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.VariableDeclarationExitNode;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000r\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\f\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J3\u0010\u0004\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rR\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u000eJ\u0087\u0001\u0010\u000f\u001a\u00020\u0005*\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\r2\u0018\u0010\u001a\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u001bH\u0002R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u001cJG\u0010\u001d\u001a\u00020\u0005*\u00020\u000b2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001f2\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u0013H\u0002R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010 JJ\u0010!\u001a\u00020\u0005*\u00020\u000b2\u0006\u0010\u001e\u001a\u00020\"2\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u00132\u0018\u0010\u001a\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u001bH\u0002Jg\u0010#\u001a\u00020\u0005*\u00020\u000b2\u0006\u0010\u001e\u001a\u00020$2\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0018\u0010\u001a\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u001bH\u0002R\u00020\bR\u00020\u0006j\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010%Jg\u0010&\u001a\u00020\u0005*\u00020\u000b2\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001f2\u0006\u0010'\u001a\u00020(2\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u00132\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\rH\u0002R\u00020\bR\u00020\u0006j\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u0010)J$\u0010*\u001a\u00020\r*\u0006\u0012\u0002\b\u00030\u00142\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030\u001f2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0093\u0001\u0010+\u001a\u00020\u0005*\u00020\u000b2\u0006\u0010\u0010\u001a\u00020\u00112\n\u0010\u001e\u001a\u0006\u0012\u0002\b\u00030,2\u0010\u0010\u0012\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\r2\u0006\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\r2\u0018\u0010\u001a\u001a\u0014\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0014\u0012\u0006\u0012\u0004\u0018\u00010\u00160\u001bH\u0002R\u00020\u0006R\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006j\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010-J=\u0010.\u001a\u00020\u0005*\u00020\u000b2\u0006\u0010\u001e\u001a\u00020$2\n\u0010/\u001a\u0006\u0012\u0002\b\u00030\u0014H$R\u00020\bR\u00020\u0006j\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u00100J9\u00101\u001a\u00020\u00052\u0006\u0010'\u001a\u00020(2\n\u0010/\u001a\u0006\u0012\u0002\b\u00030\u0014H$R\u00020\bR\u00020\u0006j\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u00102J9\u00103\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020$2\n\u0010/\u001a\u0006\u0012\u0002\b\u00030\u0014H$R\u00020\bR\u00020\u0006j\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u00104J9\u00105\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020$2\n\u0010/\u001a\u0006\u0012\u0002\b\u00030\u0014H$R\u00020\bR\u00020\u0006j\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0002\u00104J\"\u00106\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00140\u00132\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\rH$J\u0014\u00107\u001a\u00020\r*\u00020(2\u0006\u0010\n\u001a\u00020\u000bH$¨\u00068"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/cfa/VariableInitializationCheckProcessor;", Argument.Delimiters.none, "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "data", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;", "isForInitialization", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;Z)V", "runCheck", "graph", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;", "properties", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "scope", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "doNotReportUninitializedVariable", "doNotReportConstantUninitialized", "doNotReportStaticUninitialized", "scopes", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;Ljava/util/Set;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;ZZZZLjava/util/Map;)V", "processUnionNode", "node", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;Ljava/util/Set;)V", "processVariableDeclaration", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableDeclarationExitNode;", "processVariableAssignment", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;Ljava/util/Set;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Ljava/util/Map;)V", "processQualifiedAccess", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNode;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;Ljava/util/Set;ZZZ)V", "isInitializedAt", "processSubGraphs", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNodeWithSubgraphs;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/ControlFlowGraph;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/CFGNodeWithSubgraphs;Ljava/util/Set;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;ZZZZLjava/util/Map;)V", "reportCapturedInitialization", "symbol", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;)V", "reportUninitializedVariable", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;)V", "reportNonInlineMemberValInitialization", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;)V", "reportValReassignment", "filterProperties", "hasMatchingReceiver", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class VariableInitializationCheckProcessor {
    private final boolean isInitializedAt(FirVariableSymbol<?> firVariableSymbol, CFGNode<?> cFGNode, VariableInitializationInfoData variableInitializationInfoData) {
        MarkedEventOccurrencesRange<CFGNode<?>> range;
        PersistentMap<EdgeLabel, PersistentMap<FirVariableSymbol<?>, EventOccurrencesRangeAtNode>> value = variableInitializationInfoData.getValue(cFGNode);
        if (value.isEmpty()) {
            return true;
        }
        for (Map.Entry entry : value.entrySet()) {
            EdgeLabel edgeLabel = (EdgeLabel) entry.getKey();
            PersistentMap persistentMap = (PersistentMap) entry.getValue();
            if (!Intrinsics.areEqual(edgeLabel, CapturedByValue.INSTANCE) || VariableInitializationCheckProcessorKt.isCapturedByValue(firVariableSymbol)) {
                EventOccurrencesRangeAtNode eventOccurrencesRangeAtNode = (EventOccurrencesRangeAtNode) persistentMap.get(firVariableSymbol);
                if (eventOccurrencesRangeAtNode == null || (range = eventOccurrencesRangeAtNode.getRange()) == null || !EventOccurrencesRangeKt.isDefinitelyVisited(range)) {
                    return false;
                }
            }
        }
        return true;
    }

    private final void processQualifiedAccess(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, VariableInitializationInfoData variableInitializationInfoData, CFGNode<?> cFGNode, FirQualifiedAccessExpression firQualifiedAccessExpression, Set<? extends FirVariableSymbol<?>> set, boolean z, boolean z2, boolean z3) {
        if (z || (firQualifiedAccessExpression instanceof FirWhenSubjectExpression) || FirHelpersKt.hasDiagnosticKind(FirTypeUtilsKt.getResolvedType(firQualifiedAccessExpression), DiagnosticKind.RecursionInImplicitTypes)) {
            return;
        }
        FirVariableSymbol<?> resolvedVariableSymbol$default = FirReferenceUtilsKt.toResolvedVariableSymbol$default(firQualifiedAccessExpression.getCalleeReference(), false, 1, null);
        if (resolvedVariableSymbol$default == null) {
            return;
        }
        if (z2 && resolvedVariableSymbol$default.getRawStatus().isConst()) {
            return;
        }
        if (z3 && resolvedVariableSymbol$default.getRawStatus().isStatic() && !(resolvedVariableSymbol$default instanceof FirEnumEntrySymbol)) {
            return;
        }
        KtSourceElement source = resolvedVariableSymbol$default.getSource();
        if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtRealSourceElementKind.INSTANCE) && !resolvedVariableSymbol$default.getRawStatus().isLateInit() && !resolvedVariableSymbol$default.getRawStatus().isExternal() && hasMatchingReceiver(firQualifiedAccessExpression, variableInitializationInfoData) && set.contains(resolvedVariableSymbol$default) && !isInitializedAt(resolvedVariableSymbol$default, cFGNode, variableInitializationInfoData)) {
            reportUninitializedVariable(diagnosticReporter, checkerContext, firQualifiedAccessExpression, resolvedVariableSymbol$default);
        }
    }

    /* JADX WARN: Code duplicated, block: B:43:0x0081  */
    private final void processSubGraphs(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, VariableInitializationInfoData variableInitializationInfoData, ControlFlowGraph controlFlowGraph, CFGNodeWithSubgraphs<?> cFGNodeWithSubgraphs, Set<? extends FirVariableSymbol<?>> set, FirDeclaration firDeclaration, boolean z, boolean z2, boolean z3, boolean z4, Map<FirVariableSymbol<?>, FirDeclaration> map) {
        CFGNodeWithSubgraphs<?> cFGNodeWithSubgraphs2;
        FirDeclaration firDeclaration2;
        if (variableInitializationInfoData.getReceiver() != null) {
            cFGNodeWithSubgraphs2 = cFGNodeWithSubgraphs;
            if (cFGNodeWithSubgraphs2 == controlFlowGraph.getExitNode()) {
                return;
            }
        } else {
            cFGNodeWithSubgraphs2 = cFGNodeWithSubgraphs;
        }
        for (ControlFlowGraph controlFlowGraph2 : cFGNodeWithSubgraphs2.getSubGraphs()) {
            boolean z5 = z && VariableInitializationCheckProcessorKt.getDoNotReportUninitializedVariableForInitialization(controlFlowGraph2.getKind());
            FirDeclaration declaration = controlFlowGraph2.getDeclaration();
            FirProperty firProperty = declaration instanceof FirProperty ? (FirProperty) declaration : null;
            boolean z6 = firProperty != null && firProperty.getStatus().isConst();
            FirDeclaration declaration2 = controlFlowGraph2.getDeclaration();
            FirProperty firProperty2 = declaration2 instanceof FirProperty ? (FirProperty) declaration2 : null;
            boolean z7 = firProperty2 != null && firProperty2.getStatus().isStatic();
            FirDeclaration declaration3 = controlFlowGraph2.getDeclaration();
            if (declaration3 == null) {
                firDeclaration2 = firDeclaration;
            } else {
                FirDeclaration firDeclaration3 = FirPropertyInitializationAnalyzerKt.getEvaluatedInPlace(declaration3) ? null : declaration3;
                if (firDeclaration3 == null) {
                    firDeclaration2 = firDeclaration;
                } else {
                    firDeclaration2 = firDeclaration3;
                }
            }
            runCheck(checkerContext, diagnosticReporter, variableInitializationInfoData, controlFlowGraph2, set, firDeclaration2, z, z2 || z5, z3 && !z6, z4 && !z7, map);
        }
    }

    private final void processUnionNode(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, VariableInitializationInfoData variableInitializationInfoData, CFGNode<?> cFGNode, Set<? extends FirVariableSymbol<?>> set) {
        boolean z;
        EventOccurrencesRangeAtNode eventOccurrencesRangeAtNode;
        MarkedEventOccurrencesRange<CFGNode<?>> range;
        for (Map.Entry entry : variableInitializationInfoData.getValue(cFGNode).entrySet()) {
            EdgeLabel edgeLabel = (EdgeLabel) entry.getKey();
            PersistentMap persistentMap = (PersistentMap) entry.getValue();
            if (!Intrinsics.areEqual(edgeLabel, CapturedByValue.INSTANCE)) {
                for (Map.Entry entry2 : persistentMap.entrySet()) {
                    FirVariableSymbol firVariableSymbol = (FirVariableSymbol) entry2.getKey();
                    EventOccurrencesRangeAtNode eventOccurrencesRangeAtNode2 = (EventOccurrencesRangeAtNode) entry2.getValue();
                    if (firVariableSymbol.isVal() && EventOccurrencesRangeKt.canBeRevisited(eventOccurrencesRangeAtNode2.getRange())) {
                        if (set.contains(firVariableSymbol)) {
                            List<CFGNode<?>> previousCfgNodes = CfgTraverserKt.getPreviousCfgNodes(cFGNode);
                            if (!(previousCfgNodes instanceof Collection) || !previousCfgNodes.isEmpty()) {
                                Iterator<T> it = previousCfgNodes.iterator();
                                do {
                                    if (it.hasNext()) {
                                        PersistentMap persistentMap2 = (PersistentMap) variableInitializationInfoData.getValue((CFGNode) it.next()).get(edgeLabel);
                                        z = false;
                                        if (persistentMap2 != null && (eventOccurrencesRangeAtNode = (EventOccurrencesRangeAtNode) persistentMap2.get(firVariableSymbol)) != null && (range = eventOccurrencesRangeAtNode.getRange()) != null && EventOccurrencesRangeKt.canBeRevisited(range)) {
                                            z = true;
                                        }
                                    }
                                } while (!z);
                            }
                            processUnionNode$reportErrorsOnInitializationsInInputs(cFGNode, variableInitializationInfoData, this, diagnosticReporter, checkerContext, firVariableSymbol, edgeLabel, ExtensionsKt.persistentSetOf());
                            break;
                        }
                    }
                }
            }
        }
    }

    private static final void processUnionNode$reportErrorsOnInitializationsInInputs(CFGNode<?> cFGNode, VariableInitializationInfoData variableInitializationInfoData, VariableInitializationCheckProcessor variableInitializationCheckProcessor, DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirVariableSymbol<?> firVariableSymbol, EdgeLabel edgeLabel, PersistentSet<? extends CFGNode<?>> persistentSet) {
        EventOccurrencesRangeAtNode eventOccurrencesRangeAtNode;
        MarkedEventOccurrencesRange<CFGNode<?>> range;
        PersistentSet<? extends CFGNode<?>> persistentSetAdd = persistentSet.add(cFGNode);
        if (persistentSetAdd == persistentSet) {
            b6c.a(VariableInitializationCheckProcessorKt.buildRecursionErrorMessage(checkerContext, cFGNode, firVariableSymbol));
            return;
        }
        for (CFGNode<?> cFGNode2 : CfgTraverserKt.getPreviousCfgNodes(cFGNode)) {
            if (!cFGNode.edgeFrom(cFGNode2).getKind().getIsBack()) {
                PersistentMap persistentMap = (PersistentMap) variableInitializationInfoData.getValue(cFGNode2).get(edgeLabel);
                CFGNode<?> location = (persistentMap == null || (eventOccurrencesRangeAtNode = (EventOccurrencesRangeAtNode) persistentMap.get(firVariableSymbol)) == null || (range = eventOccurrencesRangeAtNode.getRange()) == null) ? null : range.getLocation();
                if (!(location instanceof VariableDeclarationExitNode)) {
                    if (location instanceof VariableAssignmentNode) {
                        variableInitializationCheckProcessor.reportCapturedInitialization(diagnosticReporter, checkerContext, variableInitializationInfoData, (VariableAssignmentNode) location, firVariableSymbol);
                    } else if (location != null) {
                        processUnionNode$reportErrorsOnInitializationsInInputs(location, variableInitializationInfoData, variableInitializationCheckProcessor, diagnosticReporter, checkerContext, firVariableSymbol, edgeLabel, persistentSetAdd);
                    }
                }
            }
        }
    }

    private final void processVariableAssignment(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, VariableInitializationInfoData variableInitializationInfoData, VariableAssignmentNode variableAssignmentNode, Set<? extends FirVariableSymbol<?>> set, FirDeclaration firDeclaration, Map<FirVariableSymbol<?>, FirDeclaration> map) {
        FirQualifiedAccessExpression firQualifiedAccessExpressionUnwrapLValue;
        MarkedEventOccurrencesRange<CFGNode<?>> range;
        FirReference calleeReference = ReferenceUtilsKt.getCalleeReference(variableAssignmentNode.getFir());
        if (calleeReference != null) {
            boolean z = false;
            FirVariableSymbol<?> resolvedVariableSymbol$default = FirReferenceUtilsKt.toResolvedVariableSymbol$default(calleeReference, false, 1, null);
            if (resolvedVariableSymbol$default != null && resolvedVariableSymbol$default.isVal() && (firQualifiedAccessExpressionUnwrapLValue = FirExpressionUtilKt.unwrapLValue(variableAssignmentNode.getFir())) != null && hasMatchingReceiver(firQualifiedAccessExpressionUnwrapLValue, variableInitializationInfoData) && set.contains(resolvedVariableSymbol$default)) {
                Collection collectionValues = variableInitializationInfoData.getValue(variableAssignmentNode).values();
                if (!(collectionValues instanceof Collection) || !collectionValues.isEmpty()) {
                    Iterator it = collectionValues.iterator();
                    while (it.hasNext()) {
                        EventOccurrencesRangeAtNode eventOccurrencesRangeAtNode = (EventOccurrencesRangeAtNode) ((PersistentMap) it.next()).get(resolvedVariableSymbol$default);
                        if (eventOccurrencesRangeAtNode != null && (range = eventOccurrencesRangeAtNode.getRange()) != null && EventOccurrencesRangeKt.canBeRevisited(range)) {
                            z = true;
                            break;
                        }
                    }
                }
                if (z && resolvedVariableSymbol$default.getResolvedInitializer() != null) {
                    reportValReassignment(diagnosticReporter, checkerContext, variableAssignmentNode, resolvedVariableSymbol$default);
                    return;
                }
                if (!Intrinsics.areEqual(firDeclaration, map.get(resolvedVariableSymbol$default))) {
                    reportCapturedInitialization(diagnosticReporter, checkerContext, variableInitializationInfoData, variableAssignmentNode, resolvedVariableSymbol$default);
                    return;
                }
                if ((resolvedVariableSymbol$default instanceof FirRegularPropertySymbol) && !VariableInitializationCheckProcessorKt.isInline(variableAssignmentNode.getOwner(), ContainingClassUtilsKt.getContainingSymbol(resolvedVariableSymbol$default, checkerContext.getSession()))) {
                    reportNonInlineMemberValInitialization(diagnosticReporter, checkerContext, variableAssignmentNode, resolvedVariableSymbol$default);
                } else if (z) {
                    reportValReassignment(diagnosticReporter, checkerContext, variableAssignmentNode, resolvedVariableSymbol$default);
                }
            }
        }
    }

    private final void processVariableDeclaration(VariableInitializationInfoData variableInitializationInfoData, VariableDeclarationExitNode variableDeclarationExitNode, FirDeclaration firDeclaration, Set<? extends FirVariableSymbol<?>> set, Map<FirVariableSymbol<?>, FirDeclaration> map) {
        FirPropertySymbol symbol = variableDeclarationExitNode.getFir().getSymbol();
        if (firDeclaration != null && variableInitializationInfoData.getReceiver() == null && variableDeclarationExitNode.getFir().getIsVal() && set.contains(symbol)) {
            map.put(symbol, firDeclaration);
        }
    }

    private final void runCheck(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, VariableInitializationInfoData variableInitializationInfoData, ControlFlowGraph controlFlowGraph, Set<? extends FirVariableSymbol<?>> set, FirDeclaration firDeclaration, boolean z, boolean z2, boolean z3, boolean z4, Map<FirVariableSymbol<?>, FirDeclaration> map) {
        for (CFGNode<?> cFGNode : controlFlowGraph.getNodes()) {
            if (cFGNode.isUnion()) {
                processUnionNode(checkerContext, diagnosticReporter, variableInitializationInfoData, cFGNode, set);
            }
            if (cFGNode instanceof VariableDeclarationExitNode) {
                processVariableDeclaration(variableInitializationInfoData, (VariableDeclarationExitNode) cFGNode, firDeclaration, set, map);
            } else if (cFGNode instanceof VariableAssignmentNode) {
                processVariableAssignment(diagnosticReporter, checkerContext, variableInitializationInfoData, (VariableAssignmentNode) cFGNode, set, firDeclaration, map);
            } else if (cFGNode instanceof QualifiedAccessNode) {
                processQualifiedAccess(diagnosticReporter, checkerContext, variableInitializationInfoData, cFGNode, ((QualifiedAccessNode) cFGNode).getFir(), set, z2, z3, z4);
            } else if (cFGNode instanceof FunctionCallEnterNode) {
                FirFunctionCall fir = ((FunctionCallEnterNode) cFGNode).getFir();
                if (fir instanceof FirImplicitInvokeCall) {
                    CFGNode<?> firstPreviousNode = CFGNodeKt.getFirstPreviousNode(cFGNode);
                    FunctionCallArgumentsExitNode functionCallArgumentsExitNode = firstPreviousNode instanceof FunctionCallArgumentsExitNode ? (FunctionCallArgumentsExitNode) firstPreviousNode : null;
                    CFGNode<?> explicitReceiverExitNode = functionCallArgumentsExitNode != null ? functionCallArgumentsExitNode.getExplicitReceiverExitNode() : null;
                    FirImplicitInvokeCall firImplicitInvokeCall = (FirImplicitInvokeCall) fir;
                    FirExpression dispatchReceiver = firImplicitInvokeCall.getDispatchReceiver();
                    if (dispatchReceiver == null) {
                        dispatchReceiver = firImplicitInvokeCall.getExplicitReceiver();
                    }
                    FirQualifiedAccessExpression firQualifiedAccessExpression = dispatchReceiver instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) dispatchReceiver : null;
                    if (explicitReceiverExitNode != null && firQualifiedAccessExpression != null) {
                        processQualifiedAccess(diagnosticReporter, checkerContext, variableInitializationInfoData, explicitReceiverExitNode, firQualifiedAccessExpression, set, z2, z3, z4);
                    }
                }
            } else if (cFGNode instanceof CFGNodeWithSubgraphs) {
                processSubGraphs(checkerContext, diagnosticReporter, variableInitializationInfoData, controlFlowGraph, (CFGNodeWithSubgraphs) cFGNode, set, firDeclaration, z, z2, z3, z4, map);
            }
        }
    }

    public final void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, VariableInitializationInfoData variableInitializationInfoData, boolean z) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        variableInitializationInfoData.getClass();
        Set<FirVariableSymbol<?>> setFilterProperties = filterProperties(variableInitializationInfoData, z);
        if (setFilterProperties.isEmpty()) {
            return;
        }
        runCheck(checkerContext, diagnosticReporter, variableInitializationInfoData, variableInitializationInfoData.getGraph(), setFilterProperties, null, z, false, true, true, new HashMap());
    }

    public abstract Set<FirVariableSymbol<?>> filterProperties(VariableInitializationInfoData data, boolean isForInitialization);

    public abstract boolean hasMatchingReceiver(FirQualifiedAccessExpression firQualifiedAccessExpression, VariableInitializationInfoData variableInitializationInfoData);

    public abstract void reportCapturedInitialization(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, VariableInitializationInfoData variableInitializationInfoData, VariableAssignmentNode variableAssignmentNode, FirVariableSymbol<?> firVariableSymbol);

    public abstract void reportNonInlineMemberValInitialization(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, VariableAssignmentNode variableAssignmentNode, FirVariableSymbol<?> firVariableSymbol);

    public abstract void reportUninitializedVariable(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirQualifiedAccessExpression firQualifiedAccessExpression, FirVariableSymbol<?> firVariableSymbol);

    public abstract void reportValReassignment(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, VariableAssignmentNode variableAssignmentNode, FirVariableSymbol<?> firVariableSymbol);
}
