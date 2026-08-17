package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.Map;
import kotlin.Metadata;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.DelegateFieldsMapKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.symbols.SymbolInternals;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J1\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0017R\u00020\bR\u00020\nb\u0002\b\u000ej\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDelegateFieldTypeMismatchChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirClassChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirClass;)V", "Lorg/jetbrains/kotlin/fir/symbols/SymbolInternals;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDelegateFieldTypeMismatchChecker extends FirDeclarationChecker<FirClass> {
    public static final FirDelegateFieldTypeMismatchChecker INSTANCE = new FirDelegateFieldTypeMismatchChecker();

    private FirDelegateFieldTypeMismatchChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    @SymbolInternals
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirClass firClass) {
        FirFieldSymbol firFieldSymbol;
        FirField firField;
        FirExpression initializer;
        FirReference reference;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firClass.getClass();
        int size = firClass.getSuperTypeRefs().size();
        for (int i = 0; i < size; i++) {
            FirTypeRef firTypeRef = firClass.getSuperTypeRefs().get(i);
            Map<Integer, FirFieldSymbol> delegateFieldsMap = DelegateFieldsMapKt.getDelegateFieldsMap(firClass);
            if (delegateFieldsMap != null && (firFieldSymbol = delegateFieldsMap.get(Integer.valueOf(i))) != null && (firField = (FirField) firFieldSymbol.getFir()) != null && (initializer = firField.getInitializer()) != null && ((!(initializer instanceof FirCall) || (reference = ReferenceUtilsKt.toReference(initializer, checkerContext.getSession())) == null || !FirReferenceUtilsKt.isError(reference)) && !TypeUtilsKt.isSubtypeOf(FirTypeUtilsKt.getResolvedType(initializer), FirTypeUtilsKt.getConeType(firTypeRef), checkerContext.getSession(), true))) {
                KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) initializer.getSource(), (KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, Boolean>) ((KtDiagnosticFactory3<Object, Object, Object>) FirErrors.INSTANCE.getTYPE_MISMATCH()), FirTypeUtilsKt.getConeType(firField.getReturnTypeRef()), FirTypeUtilsKt.getResolvedType(initializer), Boolean.FALSE, (64 & 64) != 0 ? null : null);
            }
        }
    }
}
