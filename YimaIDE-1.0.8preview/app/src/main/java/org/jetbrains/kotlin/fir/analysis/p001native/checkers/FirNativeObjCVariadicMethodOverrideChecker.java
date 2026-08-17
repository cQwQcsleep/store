package org.jetbrains.kotlin.fir.analysis.p001native.checkers;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.p000native.FirNativeErrors;
import org.jetbrains.kotlin.fir.backend.p002native.interop.FirObjCInteropKt;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÀ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/native/checkers/FirNativeObjCVariadicMethodOverrideChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirFunctionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirFunction;)V", "org.jetbrains.kotlin:checkers.native"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirNativeObjCVariadicMethodOverrideChecker extends FirDeclarationChecker<FirFunction> {
    public static final FirNativeObjCVariadicMethodOverrideChecker INSTANCE = new FirNativeObjCVariadicMethodOverrideChecker();

    private FirNativeObjCVariadicMethodOverrideChecker() {
        super(MppCheckerKind.Platform);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunction firFunction) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunction.getClass();
        if (firFunction.getStatus().isOverride()) {
            FirFunctionSymbol<FirFunction> symbol = firFunction.getSymbol();
            List<FirValueParameterSymbol> valueParameterSymbols = symbol.getValueParameterSymbols();
            if ((valueParameterSymbols instanceof Collection) && valueParameterSymbols.isEmpty()) {
                return;
            }
            Iterator<T> it = valueParameterSymbols.iterator();
            while (it.hasNext()) {
                if (((FirValueParameterSymbol) it.next()).isVararg()) {
                    if (FirObjCInteropKt.getObjCMethodInfoFromOverriddenFunctions(symbol, checkerContext.getSession(), checkerContext.getScopeSession()) != null) {
                        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firFunction.getSource(), (KtDiagnosticFactory1) FirNativeErrors.INSTANCE.getOVERRIDING_VARIADIC_OBJECTIVE_C_METHODS_IS_NOT_SUPPORTED(), (Object) symbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                        return;
                    }
                    return;
                }
            }
        }
    }
}
