package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ-\u0010\u000f\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ6\u0010\u0010\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0082\u0010R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirOptInUsageQualifierChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirResolvedQualifierChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;)V", "checkNotAcceptedExperimentalities", "checkMarkerUsedAsQualifier", "checkContainingClasses", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;Lorg/jetbrains/kotlin/KtSourceElement;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirOptInUsageQualifierChecker extends FirExpressionChecker<FirResolvedQualifier> {
    public static final FirOptInUsageQualifierChecker INSTANCE = new FirOptInUsageQualifierChecker();

    private FirOptInUsageQualifierChecker() {
        super(MppCheckerKind.Common);
    }

    private final void checkContainingClasses(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClassLikeSymbol<?> firClassLikeSymbol, KtSourceElement ktSourceElement) {
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        KtSourceElement ktSourceElement2;
        while (true) {
            if (FirOptInUsageBaseChecker.INSTANCE.isExperimentalMarker(firClassLikeSymbol, checkerContext.getSession())) {
                List<FirBasedSymbol<?>> containingDeclarations = checkerContext.getContainingDeclarations();
                if (!(containingDeclarations instanceof Collection) || !containingDeclarations.isEmpty()) {
                    Iterator<T> it = containingDeclarations.iterator();
                    while (true) {
                        if (it.hasNext()) {
                            if (Intrinsics.areEqual((FirBasedSymbol) it.next(), firClassLikeSymbol)) {
                                checkerContext2 = checkerContext;
                                diagnosticReporter2 = diagnosticReporter;
                                ktSourceElement2 = ktSourceElement;
                            }
                        }
                    }
                }
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
                ktSourceElement2 = ktSourceElement;
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) ktSourceElement2, FirErrors.INSTANCE.getOPT_IN_MARKER_CAN_ONLY_BE_USED_AS_ANNOTATION_OR_ARGUMENT_IN_OPT_IN(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            } else {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
                ktSourceElement2 = ktSourceElement;
            }
            ConeClassLikeLookupTag containingClassLookupTag = ClassMembersKt.getContainingClassLookupTag(firClassLikeSymbol);
            if (containingClassLookupTag == null || (firClassLikeSymbol = ToSymbolUtilsKt.toSymbol((SessionHolder) checkerContext2, containingClassLookupTag)) == null) {
                return;
            }
            checkerContext = checkerContext2;
            diagnosticReporter = diagnosticReporter2;
            ktSourceElement = ktSourceElement2;
        }
    }

    private final void checkMarkerUsedAsQualifier(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedQualifier firResolvedQualifier) {
        FirElement firElementPrevious;
        KtSourceElement source;
        FirClassLikeSymbol<?> symbol;
        List<FirElement> containingElements = checkerContext.getContainingElements();
        ListIterator<FirElement> listIterator = containingElements.listIterator(containingElements.size());
        while (true) {
            if (!listIterator.hasPrevious()) {
                firElementPrevious = null;
                break;
            }
            firElementPrevious = listIterator.previous();
            FirElement firElement = firElementPrevious;
            if ((firElement instanceof FirQualifiedAccessExpression) && Intrinsics.areEqual(((FirQualifiedAccessExpression) firElement).getDispatchReceiver(), firResolvedQualifier)) {
                break;
            }
        }
        FirElement firElement2 = firElementPrevious;
        if (firElement2 == null || (source = firElement2.getSource()) == null || (symbol = firResolvedQualifier.getSymbol()) == null) {
            return;
        }
        checkContainingClasses(checkerContext, diagnosticReporter, symbol, source);
    }

    private final void checkNotAcceptedExperimentalities(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedQualifier firResolvedQualifier) {
        FirClassLikeSymbol<?> symbol = firResolvedQualifier.getSymbol();
        if (symbol == null) {
            return;
        }
        FirClassLikeSymbol<?> firClassLikeSymbolResolvedCompanionSymbol = FirHelpersKt.resolvedCompanionSymbol(checkerContext, firResolvedQualifier);
        FirOptInUsageBaseChecker firOptInUsageBaseChecker = FirOptInUsageBaseChecker.INSTANCE;
        Pair<Set<FirOptInUsageBaseChecker.Experimentality>, Set<FirOptInUsageBaseChecker.Experimentality>> pairLoadExperimentalitiesForQualifier = firOptInUsageBaseChecker.loadExperimentalitiesForQualifier(checkerContext, symbol, firClassLikeSymbolResolvedCompanionSymbol);
        Set set = (Set) pairLoadExperimentalitiesForQualifier.component1();
        Set set2 = (Set) pairLoadExperimentalitiesForQualifier.component2();
        FirOptInUsageBaseChecker.reportNotAcceptedExperimentalities$default(firOptInUsageBaseChecker, checkerContext, diagnosticReporter, set, firResolvedQualifier, null, false, 48, null);
        FirOptInUsageBaseChecker.reportNotAcceptedExperimentalities$default(firOptInUsageBaseChecker, checkerContext, diagnosticReporter, set2, firResolvedQualifier, null, LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.ReportOptInUsageOnCompanionObjectAccesses), 16, null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedQualifier firResolvedQualifier) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firResolvedQualifier.getClass();
        checkNotAcceptedExperimentalities(checkerContext, diagnosticReporter, firResolvedQualifier);
        checkMarkerUsedAsQualifier(checkerContext, diagnosticReporter, firResolvedQualifier);
    }
}
