package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ)\u0010\u000e\u001a\u00020\u000f*\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0010J)\u0010\u0014\u001a\u00020\u0007*\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rR%\u0010\u0011\u001a\u00020\u000f*\u00020\u00028BX\u0082\u0004b\u00020\b\u008a\u0001\u0006\u0010\t\u001a\u00020\b¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u0012R%\u0010\u0013\u001a\u00020\u000f*\u00020\u00028BX\u0082\u0004b\u00020\b\u008a\u0001\u0006\u0010\t\u001a\u00020\b¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0012¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirStandaloneQualifierChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirResolvedQualifierChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;)V", "reportPackageOrNoCompanion", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;)Z", "isNotResolvedToObject", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;)Z", "isTypeAliasToClassWithCompanion", "reportTypeArguments", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirStandaloneQualifierChecker extends FirExpressionChecker<FirResolvedQualifier> {
    public static final FirStandaloneQualifierChecker INSTANCE = new FirStandaloneQualifierChecker();

    private FirStandaloneQualifierChecker() {
        super(MppCheckerKind.Common);
    }

    private final boolean isNotResolvedToObject(CheckerContext checkerContext, FirResolvedQualifier firResolvedQualifier) {
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        if (!ConeBuiltinTypeUtilsKt.isUnit(FirTypeUtilsKt.getResolvedType(firResolvedQualifier))) {
            return false;
        }
        FirClassLikeSymbol<?> symbol = firResolvedQualifier.getSymbol();
        return ((symbol == null || (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(checkerContext, symbol)) == null) ? null : firRegularClassSymbolFullyExpandedClass.getClassKind()) != ClassKind.OBJECT;
    }

    private final boolean isTypeAliasToClassWithCompanion(CheckerContext checkerContext, FirResolvedQualifier firResolvedQualifier) {
        FirRegularClassSymbol firRegularClassSymbolFullyExpandedClass;
        FirClassLikeSymbol<?> symbol = firResolvedQualifier.getSymbol();
        return ((symbol == null || (firRegularClassSymbolFullyExpandedClass = DeclarationUtilsKt.fullyExpandedClass(checkerContext, symbol)) == null) ? null : firRegularClassSymbolFullyExpandedClass.getResolvedCompanionObjectSymbol()) != null;
    }

    private final boolean reportPackageOrNoCompanion(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedQualifier firResolvedQualifier) {
        FirClassLikeSymbol<?> symbol = firResolvedQualifier.getSymbol();
        if (symbol == null) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firResolvedQualifier.getSource(), FirErrors.INSTANCE.getEXPRESSION_EXPECTED_PACKAGE_FOUND(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            return true;
        }
        if (!isNotResolvedToObject(checkerContext, firResolvedQualifier) || isTypeAliasToClassWithCompanion(checkerContext, firResolvedQualifier)) {
            return false;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firResolvedQualifier.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getNO_COMPANION_OBJECT(), (Object) symbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        return true;
    }

    private final void reportTypeArguments(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedQualifier firResolvedQualifier) {
        if (reportTypeArguments$preForbidUselessTypeArgumentsIn25Implementation(firResolvedQualifier, checkerContext, diagnosticReporter)) {
            return;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firResolvedQualifier.getSource(), (KtDiagnosticFactory1) (LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ForbidUselessTypeArgumentsIn25) ? FirErrors.INSTANCE.getEXPLICIT_TYPE_ARGUMENTS_IN_PROPERTY_ACCESS() : FirErrors.INSTANCE.getEXPLICIT_TYPE_ARGUMENTS_IN_PROPERTY_ACCESS_WARNING()), (Object) "Object", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
    }

    private static final boolean reportTypeArguments$preForbidUselessTypeArgumentsIn25Implementation(FirResolvedQualifier firResolvedQualifier, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter) {
        if (ConeBuiltinTypeUtilsKt.isUnit(FirTypeUtilsKt.getResolvedType(firResolvedQualifier)) && !INSTANCE.isTypeAliasToClassWithCompanion(checkerContext, firResolvedQualifier)) {
            return false;
        }
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firResolvedQualifier.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getEXPLICIT_TYPE_ARGUMENTS_IN_PROPERTY_ACCESS(), (Object) "Object", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        return true;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedQualifier firResolvedQualifier) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firResolvedQualifier.getClass();
        if (FirHelpersKt.isStandalone(checkerContext, firResolvedQualifier) && !reportPackageOrNoCompanion(checkerContext, diagnosticReporter, firResolvedQualifier)) {
            List<FirTypeProjection> typeArguments = firResolvedQualifier.getTypeArguments();
            if ((typeArguments instanceof Collection) && typeArguments.isEmpty()) {
                return;
            }
            Iterator<T> it = typeArguments.iterator();
            while (it.hasNext()) {
                if (FirHelpersKt.isExplicit((FirTypeProjection) it.next())) {
                    reportTypeArguments(checkerContext, diagnosticReporter, firResolvedQualifier);
                    return;
                }
            }
        }
    }
}
