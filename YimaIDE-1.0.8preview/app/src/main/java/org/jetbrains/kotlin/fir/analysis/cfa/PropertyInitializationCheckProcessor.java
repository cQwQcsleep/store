package org.jetbrains.kotlin.fir.analysis.cfa;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.cfa.util.VariableInitializationInfoData;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.references.FirThisReference;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.VariableAssignmentNode;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0014J=\u0010\u000b\u001a\u00020\f*\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0014R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\u00020\n*\u00020\u00162\u0006\u0010\u0007\u001a\u00020\bH\u0014J9\u0010\u0017\u001a\u00020\f2\u0006\u0010\u0018\u001a\u00020\u00162\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0014R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0019J9\u0010\u001a\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0014R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u001bJ9\u0010\u001c\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0014R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u001b¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/cfa/PropertyInitializationCheckProcessor;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/VariableInitializationCheckProcessor;", "<init>", "()V", "filterProperties", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "data", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;", "isForInitialization", Argument.Delimiters.none, "reportCapturedInitialization", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "node", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;", "symbol", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;)V", "hasMatchingReceiver", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "reportUninitializedVariable", "expression", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;)V", "reportNonInlineMemberValInitialization", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;)V", "reportValReassignment", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PropertyInitializationCheckProcessor extends VariableInitializationCheckProcessor {
    public static final PropertyInitializationCheckProcessor INSTANCE = new PropertyInitializationCheckProcessor();

    private PropertyInitializationCheckProcessor() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.cfa.VariableInitializationCheckProcessor
    public Set<FirVariableSymbol<?>> filterProperties(VariableInitializationInfoData data, boolean isForInitialization) {
        data.getClass();
        Set<FirVariableSymbol<?>> properties = data.getProperties();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object obj : properties) {
            FirVariableSymbol firVariableSymbol = (FirVariableSymbol) obj;
            if (!(firVariableSymbol instanceof FirPropertySymbol)) {
                w01.a("Failed requirement.");
                return null;
            }
            if (FirPropertyInitializationAnalyzerKt.requiresInitialization((FirPropertySymbol) firVariableSymbol, isForInitialization) || data.getConditionallyInitializedProperties().contains(firVariableSymbol)) {
                linkedHashSet.add(obj);
            }
        }
        return linkedHashSet;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.cfa.VariableInitializationCheckProcessor
    public boolean hasMatchingReceiver(FirQualifiedAccessExpression firQualifiedAccessExpression, VariableInitializationInfoData variableInitializationInfoData) {
        FirThisReference calleeReference;
        firQualifiedAccessExpression.getClass();
        variableInitializationInfoData.getClass();
        FirExpression dispatchReceiver = firQualifiedAccessExpression.getDispatchReceiver();
        FirExpression firExpressionUnwrapSmartcastExpression = dispatchReceiver != null ? FirExpressionUtilKt.unwrapSmartcastExpression(dispatchReceiver) : null;
        FirThisReceiverExpression firThisReceiverExpression = firExpressionUnwrapSmartcastExpression instanceof FirThisReceiverExpression ? (FirThisReceiverExpression) firExpressionUnwrapSmartcastExpression : null;
        if (Intrinsics.areEqual((firThisReceiverExpression == null || (calleeReference = firThisReceiverExpression.getCalleeReference()) == null) ? null : calleeReference.getBoundSymbol(), variableInitializationInfoData.getReceiver())) {
            return true;
        }
        FirResolvedQualifier firResolvedQualifier = firExpressionUnwrapSmartcastExpression instanceof FirResolvedQualifier ? (FirResolvedQualifier) firExpressionUnwrapSmartcastExpression : null;
        return Intrinsics.areEqual(firResolvedQualifier != null ? firResolvedQualifier.getSymbol() : null, variableInitializationInfoData.getReceiver());
    }

    @Override // org.jetbrains.kotlin.fir.analysis.cfa.VariableInitializationCheckProcessor
    public void reportCapturedInitialization(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, VariableInitializationInfoData variableInitializationInfoData, VariableAssignmentNode variableAssignmentNode, FirVariableSymbol<?> firVariableSymbol) {
        diagnosticReporter.getClass();
        checkerContext.getClass();
        variableInitializationInfoData.getClass();
        variableAssignmentNode.getClass();
        firVariableSymbol.getClass();
        if (firVariableSymbol instanceof FirPropertySymbol) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) variableAssignmentNode.getFir().getLValue().getSource(), (KtDiagnosticFactory1) (variableInitializationInfoData.getReceiver() != null ? FirErrors.INSTANCE.getCAPTURED_MEMBER_VAL_INITIALIZATION() : FirErrors.INSTANCE.getCAPTURED_VAL_INITIALIZATION()), (Object) firVariableSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        } else {
            w01.a("Failed requirement.");
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.cfa.VariableInitializationCheckProcessor
    public void reportNonInlineMemberValInitialization(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, VariableAssignmentNode variableAssignmentNode, FirVariableSymbol<?> firVariableSymbol) {
        diagnosticReporter.getClass();
        checkerContext.getClass();
        variableAssignmentNode.getClass();
        firVariableSymbol.getClass();
        if (firVariableSymbol instanceof FirPropertySymbol) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) variableAssignmentNode.getFir().getLValue().getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getNON_INLINE_MEMBER_VAL_INITIALIZATION(), (Object) firVariableSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        } else {
            w01.a("Failed requirement.");
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.cfa.VariableInitializationCheckProcessor
    public void reportUninitializedVariable(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirQualifiedAccessExpression firQualifiedAccessExpression, FirVariableSymbol<?> firVariableSymbol) {
        diagnosticReporter.getClass();
        checkerContext.getClass();
        firQualifiedAccessExpression.getClass();
        firVariableSymbol.getClass();
        if (firVariableSymbol instanceof FirPropertySymbol) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNINITIALIZED_VARIABLE(), (Object) firVariableSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        } else {
            w01.a("Failed requirement.");
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.cfa.VariableInitializationCheckProcessor
    public void reportValReassignment(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, VariableAssignmentNode variableAssignmentNode, FirVariableSymbol<?> firVariableSymbol) {
        diagnosticReporter.getClass();
        checkerContext.getClass();
        variableAssignmentNode.getClass();
        firVariableSymbol.getClass();
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) variableAssignmentNode.getFir().getLValue().getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getVAL_REASSIGNMENT(), (Object) firVariableSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }
}
