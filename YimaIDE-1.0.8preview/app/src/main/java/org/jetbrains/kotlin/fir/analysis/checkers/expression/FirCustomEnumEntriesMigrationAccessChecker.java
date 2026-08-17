package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeResolutionResultOverridesOtherToPreserveCompatibility;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCustomEnumEntriesMigrationAccessChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirPropertyAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCustomEnumEntriesMigrationAccessChecker extends FirExpressionChecker<FirPropertyAccessExpression> {
    public static final FirCustomEnumEntriesMigrationAccessChecker INSTANCE = new FirCustomEnumEntriesMigrationAccessChecker();

    private FirCustomEnumEntriesMigrationAccessChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirPropertyAccessExpression firPropertyAccessExpression) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firPropertyAccessExpression.getClass();
        if (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.PrioritizedEnumEntries)) {
            return;
        }
        FirPropertySymbol resolvedPropertySymbol$default = FirReferenceUtilsKt.toResolvedPropertySymbol$default(firPropertyAccessExpression.getCalleeReference(), false, 1, null);
        if (resolvedPropertySymbol$default != null && Intrinsics.areEqual(resolvedPropertySymbol$default.getName(), StandardNames.ENUM_ENTRIES)) {
            List<ConeDiagnostic> nonFatalDiagnostics = firPropertyAccessExpression.getNonFatalDiagnostics();
            if ((nonFatalDiagnostics instanceof Collection) && nonFatalDiagnostics.isEmpty()) {
                return;
            }
            Iterator<T> it = nonFatalDiagnostics.iterator();
            while (it.hasNext()) {
                if (((ConeDiagnostic) it.next()) instanceof ConeResolutionResultOverridesOtherToPreserveCompatibility) {
                    FirExpression dispatchReceiver = firPropertyAccessExpression.getDispatchReceiver();
                    if (!((dispatchReceiver != null ? FirExpressionUtilKt.unwrapSmartcastExpression(dispatchReceiver) : null) instanceof FirResolvedQualifier)) {
                        FirExpression extensionReceiver = firPropertyAccessExpression.getExtensionReceiver();
                        if (!((extensionReceiver != null ? FirExpressionUtilKt.unwrapSmartcastExpression(extensionReceiver) : null) instanceof FirResolvedQualifier)) {
                            List<FirBasedSymbol<?>> containingDeclarations = checkerContext.getContainingDeclarations();
                            if (!(containingDeclarations instanceof Collection) || !containingDeclarations.isEmpty()) {
                                Iterator<T> it2 = containingDeclarations.iterator();
                                while (it2.hasNext()) {
                                    FirBasedSymbol firBasedSymbol = (FirBasedSymbol) it2.next();
                                    if ((firBasedSymbol instanceof FirClassSymbol) && ((FirClassSymbol) firBasedSymbol).getClassKind() == ClassKind.ENUM_CLASS) {
                                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firPropertyAccessExpression.getSource(), FirErrors.INSTANCE.getDEPRECATED_ACCESS_TO_ENTRY_PROPERTY_FROM_ENUM(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                                        return;
                                    }
                                }
                            }
                            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firPropertyAccessExpression.getSource(), FirErrors.INSTANCE.getDEPRECATED_ACCESS_TO_ENTRIES_PROPERTY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                            return;
                        }
                    }
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firPropertyAccessExpression.getSource(), FirErrors.INSTANCE.getDEPRECATED_ACCESS_TO_ENUM_ENTRY_COMPANION_PROPERTY(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    return;
                }
            }
        }
    }
}
