package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Set;
import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.cfa.VariableInitializationCheckProcessor;
import org.jetbrains.kotlin.fir.analysis.cfa.util.VariableInitializationInfoData;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.resolve.dfa.cfg.VariableAssignmentNode;
import org.jetbrains.kotlin.fir.symbols.impl.FirEnumEntrySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\bÂ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\"\u0010\u0004\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00060\u00052\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0014J=\u0010\u000b\u001a\u00020\f*\u00020\b2\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0014R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0014J9\u0010\u0015\u001a\u00020\f2\u0006\u0010\u0016\u001a\u00020\u00172\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0014R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0018J9\u0010\u0019\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0014R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u001aJ9\u0010\u001b\u001a\u00020\f2\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u0006H\u0014R\u00020\rR\u00020\u000fj\u0006\u0010\u000e\u001a\u00020\rj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u001aJ\u0014\u0010\u001c\u001a\u00020\n*\u00020\u00172\u0006\u0010\u0007\u001a\u00020\bH\u0014¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/EnumEntryInitializationCheckProcessor;", "Lorg/jetbrains/kotlin/fir/analysis/cfa/VariableInitializationCheckProcessor;", "<init>", "()V", "filterProperties", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "data", "Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;", "isForInitialization", Argument.Delimiters.none, "reportCapturedInitialization", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "node", "Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;", "symbol", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/analysis/cfa/util/VariableInitializationInfoData;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;)V", "reportUninitializedVariable", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;)V", "reportNonInlineMemberValInitialization", "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/resolve/dfa/cfg/VariableAssignmentNode;Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;)V", "reportValReassignment", "hasMatchingReceiver", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class EnumEntryInitializationCheckProcessor extends VariableInitializationCheckProcessor {
    public static final EnumEntryInitializationCheckProcessor INSTANCE = new EnumEntryInitializationCheckProcessor();

    private EnumEntryInitializationCheckProcessor() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.cfa.VariableInitializationCheckProcessor
    public Set<FirVariableSymbol<?>> filterProperties(VariableInitializationInfoData data, boolean isForInitialization) {
        data.getClass();
        return data.getProperties();
    }

    @Override // org.jetbrains.kotlin.fir.analysis.cfa.VariableInitializationCheckProcessor
    public boolean hasMatchingReceiver(FirQualifiedAccessExpression firQualifiedAccessExpression, VariableInitializationInfoData variableInitializationInfoData) {
        firQualifiedAccessExpression.getClass();
        variableInitializationInfoData.getClass();
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.cfa.VariableInitializationCheckProcessor
    public void reportCapturedInitialization(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, VariableInitializationInfoData variableInitializationInfoData, VariableAssignmentNode variableAssignmentNode, FirVariableSymbol<?> firVariableSymbol) {
        diagnosticReporter.getClass();
        checkerContext.getClass();
        variableInitializationInfoData.getClass();
        variableAssignmentNode.getClass();
        firVariableSymbol.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.analysis.cfa.VariableInitializationCheckProcessor
    public void reportNonInlineMemberValInitialization(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, VariableAssignmentNode variableAssignmentNode, FirVariableSymbol<?> firVariableSymbol) {
        diagnosticReporter.getClass();
        checkerContext.getClass();
        variableAssignmentNode.getClass();
        firVariableSymbol.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.analysis.cfa.VariableInitializationCheckProcessor
    public void reportUninitializedVariable(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirQualifiedAccessExpression firQualifiedAccessExpression, FirVariableSymbol<?> firVariableSymbol) {
        diagnosticReporter.getClass();
        checkerContext.getClass();
        firQualifiedAccessExpression.getClass();
        firVariableSymbol.getClass();
        if (firVariableSymbol instanceof FirEnumEntrySymbol) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getUNINITIALIZED_ENUM_ENTRY(), (Object) firVariableSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
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
    }
}
