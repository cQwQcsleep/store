package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDelegationSuperCallInEnumConstructorChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirRegularClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDelegationSuperCallInEnumConstructorChecker extends FirDeclarationChecker<FirRegularClass> {
    public static final FirDelegationSuperCallInEnumConstructorChecker INSTANCE = new FirDelegationSuperCallInEnumConstructorChecker();

    private FirDelegationSuperCallInEnumConstructorChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Code duplicated, block: B:25:0x006f  */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirRegularClass firRegularClass) {
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        FirDelegatedConstructorCall resolvedDelegatedConstructorCall;
        KtSourceElement source;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firRegularClass.getClass();
        if (firRegularClass.getClassKind() == ClassKind.ENUM_CLASS) {
            for (FirConstructorSymbol firConstructorSymbol : org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt.constructors(firRegularClass, checkerContext.getSession())) {
                if (firConstructorSymbol.isPrimary() || (resolvedDelegatedConstructorCall = firConstructorSymbol.getResolvedDelegatedConstructorCall()) == null || resolvedDelegatedConstructorCall.getIsThis()) {
                    checkerContext2 = checkerContext;
                    diagnosticReporter2 = diagnosticReporter;
                } else {
                    FirDelegatedConstructorCall resolvedDelegatedConstructorCall2 = firConstructorSymbol.getResolvedDelegatedConstructorCall();
                    if (((resolvedDelegatedConstructorCall2 == null || (source = resolvedDelegatedConstructorCall2.getSource()) == null) ? null : source.getKind()) instanceof KtFakeSourceElementKind) {
                        checkerContext2 = checkerContext;
                        diagnosticReporter2 = diagnosticReporter;
                    } else {
                        FirDelegatedConstructorCall resolvedDelegatedConstructorCall3 = firConstructorSymbol.getResolvedDelegatedConstructorCall();
                        checkerContext2 = checkerContext;
                        diagnosticReporter2 = diagnosticReporter;
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) (resolvedDelegatedConstructorCall3 != null ? resolvedDelegatedConstructorCall3.getSource() : null), FirErrors.INSTANCE.getDELEGATION_SUPER_CALL_IN_ENUM_CONSTRUCTOR(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                    }
                }
                checkerContext = checkerContext2;
                diagnosticReporter = diagnosticReporter2;
            }
        }
    }
}
