package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategy;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.java.symbols.FirJavaOverriddenSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertySymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0003\u0010\u0004J-\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000b\u001a\u00020\u0002H\u0016R\u00020\u0007R\u00020\tj\u0006\u0010\b\u001a\u00020\u0007j\u0006\u0010\n\u001a\u00020\t¢\u0006\u0002\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/expression/FirUnsupportedSyntheticCallableReferenceChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirUnsupportedSyntheticCallableReferenceChecker extends FirExpressionChecker<FirCallableReferenceAccess> {
    public static final FirUnsupportedSyntheticCallableReferenceChecker INSTANCE = new FirUnsupportedSyntheticCallableReferenceChecker();

    private FirUnsupportedSyntheticCallableReferenceChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableReferenceAccess firCallableReferenceAccess) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firCallableReferenceAccess.getClass();
        List<FirElement> containingElements = checkerContext.getContainingElements();
        if (CollectionsKt.last(containingElements) != firCallableReferenceAccess) {
            k2d.a("Check failed.");
            return;
        }
        FirElement firElement = containingElements.get(CollectionsKt.getLastIndex(containingElements) - 1);
        if ((firElement instanceof FirProperty) && ((FirProperty) firElement).getDelegate() == firCallableReferenceAccess) {
            return;
        }
        FirResolvedNamedReference resolvedCallableReference = ReferenceUtilsKt.toResolvedCallableReference(firCallableReferenceAccess);
        FirBasedSymbol<?> resolvedSymbol = resolvedCallableReference != null ? resolvedCallableReference.getResolvedSymbol() : null;
        if (!(resolvedSymbol instanceof FirSyntheticPropertySymbol) || (resolvedSymbol instanceof FirJavaOverriddenSyntheticPropertySymbol)) {
            return;
        }
        FirHelpersKt.requireFeatureSupport$default(checkerContext, diagnosticReporter, firCallableReferenceAccess.getCalleeReference(), LanguageFeature.ReferencesToSyntheticJavaProperties, (SourceElementPositioningStrategy) null, 8, (Object) null);
    }
}
