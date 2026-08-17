package org.jetbrains.kotlin.fir.analysis.checkers.declaration.crv;

import java.util.Iterator;
import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.config.ReturnValueCheckerMode;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.resolve.ReturnValueStatus;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/crv/FirReturnValueOverrideChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirCallableDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirReturnValueOverrideChecker extends FirDeclarationChecker<FirCallableDeclaration> {
    public static final FirReturnValueOverrideChecker INSTANCE = new FirReturnValueOverrideChecker();

    private FirReturnValueOverrideChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableDeclaration firCallableDeclaration) {
        Object next;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firCallableDeclaration.getClass();
        if (checkerContext.get$languageVersionSettings().getFlag(AnalysisFlags.getReturnValueCheckerMode()) != ReturnValueCheckerMode.DISABLED && firCallableDeclaration.getStatus().isOverride() && firCallableDeclaration.getStatus().getReturnValueStatus() == ReturnValueStatus.MustUse && !HelpersKt.isIgnorable(checkerContext, FirTypeUtilsKt.getConeType(firCallableDeclaration.getReturnTypeRef()))) {
            FirCallableSymbol<FirCallableDeclaration> symbol = firCallableDeclaration.getSymbol();
            Iterator<T> it = FirHelpersKt.directOverriddenSymbolsSafe(checkerContext, symbol).iterator();
            do {
                if (!it.hasNext()) {
                    next = null;
                    break;
                }
                next = it.next();
            } while (((FirCallableSymbol) next).getResolvedStatus().getReturnValueStatus() != ReturnValueStatus.ExplicitlyIgnorable);
            FirCallableSymbol firCallableSymbol = (FirCallableSymbol) next;
            if (firCallableSymbol == null) {
                return;
            }
            FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(firCallableSymbol);
            if (containingClassSymbol != null) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firCallableDeclaration.getSource(), (KtDiagnosticFactory2) FirErrors.INSTANCE.getOVERRIDING_IGNORABLE_WITH_MUST_USE(), (Object) symbol, (Object) containingClassSymbol, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            } else {
                b88.a("Overridden symbol ", firCallableSymbol.getCallableId(), " does not have containing class symbol");
            }
        }
    }
}
