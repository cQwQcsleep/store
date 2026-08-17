package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u001f\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u0010R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0011R\u001c\u0010\u0012\u001a\u00020\u000f*\u0006\u0012\u0002\b\u00030\u00138BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirInlineBodySimpleFunctionChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirSimpleFunctionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;)V", "isInsideInlineContext", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;)Z", "isObject", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "(Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;)Z", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirInlineBodySimpleFunctionChecker extends FirDeclarationChecker<FirNamedFunction> {
    public static final FirInlineBodySimpleFunctionChecker INSTANCE = new FirInlineBodySimpleFunctionChecker();

    private FirInlineBodySimpleFunctionChecker() {
        super(MppCheckerKind.Common);
    }

    private final boolean isObject(FirBasedSymbol<?> firBasedSymbol) {
        if (firBasedSymbol instanceof FirAnonymousObjectSymbol) {
            return true;
        }
        return (firBasedSymbol instanceof FirRegularClassSymbol) && ((FirRegularClassSymbol) firBasedSymbol).getClassKind() == ClassKind.OBJECT;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirNamedFunction firNamedFunction) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firNamedFunction.getClass();
        if (isInsideInlineContext(checkerContext, firNamedFunction)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firNamedFunction.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getNOT_YET_SUPPORTED_IN_INLINE(), (Object) "Local functions", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }

    public final boolean isInsideInlineContext(CheckerContext checkerContext, FirDeclaration firDeclaration) {
        FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext;
        FirFunction inlineFunction;
        checkerContext.getClass();
        firDeclaration.getClass();
        FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext2 = checkerContext.getInlineFunctionBodyContext();
        if (Intrinsics.areEqual(firDeclaration, inlineFunctionBodyContext2 != null ? inlineFunctionBodyContext2.getInlineFunction() : null)) {
            FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext3 = checkerContext.getInlineFunctionBodyContext();
            inlineFunctionBodyContext = inlineFunctionBodyContext3 != null ? inlineFunctionBodyContext3.getParentInlineContext() : null;
        } else {
            inlineFunctionBodyContext = checkerContext.getInlineFunctionBodyContext();
        }
        for (FirBasedSymbol<?> firBasedSymbol : CollectionsKt.asReversed(checkerContext.getContainingDeclarations())) {
            if (!Intrinsics.areEqual(firBasedSymbol, (inlineFunctionBodyContext == null || (inlineFunction = inlineFunctionBodyContext.getInlineFunction()) == null) ? null : inlineFunction.getSymbol())) {
                if (isObject(firBasedSymbol)) {
                    break;
                }
            } else {
                if (!Intrinsics.areEqual(inlineFunctionBodyContext.getInlineFunction().getSymbol().getRawStatus().getVisibility(), Visibilities.Local.INSTANCE)) {
                    return true;
                }
                inlineFunctionBodyContext = inlineFunctionBodyContext.getParentInlineContext();
            }
        }
        return false;
    }
}
