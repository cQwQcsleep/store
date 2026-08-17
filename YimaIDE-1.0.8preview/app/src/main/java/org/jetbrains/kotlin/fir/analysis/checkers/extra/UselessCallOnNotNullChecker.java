package org.jetbrains.kotlin.fir.analysis.checkers.extra;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.StandardClassIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u000e\u0010\u000e\u001a\u0004\u0018\u00010\u000f*\u00020\u0002H\u0002R\u0014\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0011X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/extra/UselessCallOnNotNullChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "getCallableId", "Lorg/jetbrains/kotlin/name/CallableId;", "triggerOn", Argument.Delimiters.none, "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class UselessCallOnNotNullChecker extends FirExpressionChecker<FirQualifiedAccessExpression> {
    public static final UselessCallOnNotNullChecker INSTANCE = new UselessCallOnNotNullChecker();
    private static final Set<CallableId> triggerOn;

    static {
        StandardClassIds standardClassIds = StandardClassIds.INSTANCE;
        FqName base_collections_package = standardClassIds.getBASE_COLLECTIONS_PACKAGE();
        Name nameIdentifier = Name.identifier("orEmpty");
        nameIdentifier.getClass();
        CallableId callableId = new CallableId(base_collections_package, nameIdentifier);
        FqName base_sequences_package = standardClassIds.getBASE_SEQUENCES_PACKAGE();
        Name nameIdentifier2 = Name.identifier("orEmpty");
        nameIdentifier2.getClass();
        CallableId callableId2 = new CallableId(base_sequences_package, nameIdentifier2);
        FqName base_text_package = standardClassIds.getBASE_TEXT_PACKAGE();
        Name nameIdentifier3 = Name.identifier("orEmpty");
        nameIdentifier3.getClass();
        CallableId callableId3 = new CallableId(base_text_package, nameIdentifier3);
        FqName base_kotlin_package = standardClassIds.getBASE_KOTLIN_PACKAGE();
        Name nameIdentifier4 = Name.identifier("orEmpty");
        nameIdentifier4.getClass();
        CallableId callableId4 = new CallableId(base_kotlin_package, nameIdentifier4);
        FqName base_text_package2 = standardClassIds.getBASE_TEXT_PACKAGE();
        Name nameIdentifier5 = Name.identifier("isNullOrBlank");
        nameIdentifier5.getClass();
        CallableId callableId5 = new CallableId(base_text_package2, nameIdentifier5);
        FqName base_text_package3 = standardClassIds.getBASE_TEXT_PACKAGE();
        Name nameIdentifier6 = Name.identifier("isNullOrEmpty");
        nameIdentifier6.getClass();
        CallableId callableId6 = new CallableId(base_text_package3, nameIdentifier6);
        FqName base_kotlin_package2 = standardClassIds.getBASE_KOTLIN_PACKAGE();
        Name nameIdentifier7 = Name.identifier("isNullOrBlank");
        nameIdentifier7.getClass();
        CallableId callableId7 = new CallableId(base_kotlin_package2, nameIdentifier7);
        FqName base_kotlin_package3 = standardClassIds.getBASE_KOTLIN_PACKAGE();
        Name nameIdentifier8 = Name.identifier("isNullOrEmpty");
        nameIdentifier8.getClass();
        triggerOn = SetsKt.setOf(new CallableId[]{callableId, callableId2, callableId3, callableId4, callableId5, callableId6, callableId7, new CallableId(base_kotlin_package3, nameIdentifier8)});
    }

    private UselessCallOnNotNullChecker() {
        super(MppCheckerKind.Common);
    }

    private final CallableId getCallableId(FirQualifiedAccessExpression firQualifiedAccessExpression) {
        FirNamedFunctionSymbol resolvedNamedFunctionSymbol$default = FirReferenceUtilsKt.toResolvedNamedFunctionSymbol$default(firQualifiedAccessExpression.getCalleeReference(), false, 1, null);
        if (resolvedNamedFunctionSymbol$default != null) {
            return resolvedNamedFunctionSymbol$default.getCallableId();
        }
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        FirExpression explicitReceiver;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firQualifiedAccessExpression.getClass();
        CallableId callableId = getCallableId(firQualifiedAccessExpression);
        if (callableId == null || !triggerOn.contains(callableId) || (explicitReceiver = firQualifiedAccessExpression.getExplicitReceiver()) == null || TypeUtilsKt.canBeNull$default(FirTypeUtilsKt.getResolvedType(explicitReceiver), checkerContext.getSession(), false, null, 6, null)) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firQualifiedAccessExpression.getSource(), FirErrors.INSTANCE.getUSELESS_CALL_ON_NOT_NULL(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
    }
}
