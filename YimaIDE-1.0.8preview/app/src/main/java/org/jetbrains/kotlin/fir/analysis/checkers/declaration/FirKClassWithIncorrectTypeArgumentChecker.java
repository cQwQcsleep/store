package org.jetbrains.kotlin.fir.analysis.checkers.declaration;

import java.util.ArrayList;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeTypeParameterInQualifiedAccess;
import org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.InferenceUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ\u0014\u0010\u000e\u001a\u00020\u000f*\u00020\u00102\u0006\u0010\t\u001a\u00020\u0011H\u0002R\u001a\u0010\u0012\u001a\u0004\u0018\u00010\u0013*\u00020\u00108BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirKClassWithIncorrectTypeArgumentChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirDeclarationChecker;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/declaration/FirCallableDeclarationChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "declaration", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;)V", "isKClassTypeWithErrorOrNullableArgument", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/types/ConeInferenceContext;", "typeParameterFromError", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "getTypeParameterFromError", "(Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirKClassWithIncorrectTypeArgumentChecker extends FirDeclarationChecker<FirCallableDeclaration> {
    public static final FirKClassWithIncorrectTypeArgumentChecker INSTANCE = new FirKClassWithIncorrectTypeArgumentChecker();

    private FirKClassWithIncorrectTypeArgumentChecker() {
        super(MppCheckerKind.Common);
    }

    private final FirTypeParameterSymbol getTypeParameterFromError(ConeKotlinType coneKotlinType) {
        ConeErrorType coneErrorType = coneKotlinType instanceof ConeErrorType ? (ConeErrorType) coneKotlinType : null;
        ConeDiagnostic diagnostic = coneErrorType != null ? coneErrorType.getDiagnostic() : null;
        ConeTypeParameterInQualifiedAccess coneTypeParameterInQualifiedAccess = diagnostic instanceof ConeTypeParameterInQualifiedAccess ? (ConeTypeParameterInQualifiedAccess) diagnostic : null;
        if (coneTypeParameterInQualifiedAccess != null) {
            return coneTypeParameterInQualifiedAccess.getSymbol();
        }
        return null;
    }

    private final boolean isKClassTypeWithErrorOrNullableArgument(ConeKotlinType coneKotlinType, ConeInferenceContext coneInferenceContext) {
        ConeKotlinTypeProjection coneKotlinTypeProjection;
        ConeKotlinType type;
        if (InferenceUtilsKt.isKClassType(coneKotlinType) && (coneKotlinTypeProjection = (ConeTypeProjection) CollectionsKt.singleOrNull(ArraysKt.toList(coneKotlinType.getTypeArguments()))) != null) {
            if (coneKotlinTypeProjection instanceof ConeStarProjection) {
                type = null;
            } else if (coneKotlinTypeProjection instanceof ConeKotlinTypeProjection) {
                type = coneKotlinTypeProjection.getType();
            } else {
                bu8.a();
            }
            if (type != null) {
                FirTypeParameterSymbol typeParameterFromError = INSTANCE.getTypeParameterFromError(type);
                if (typeParameterFromError != null) {
                    return coneInferenceContext.isNullableType(FirNestedClassifierScopeKt.toConeType(typeParameterFromError));
                }
                return (type instanceof ConeErrorType) || coneInferenceContext.isNullableType(type);
            }
        }
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDeclarationChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableDeclaration firCallableDeclaration) {
        KtSourceElement source;
        CheckerContext checkerContext2;
        DiagnosticReporter diagnosticReporter2;
        ConeKotlinType type;
        FirTypeParameterSymbol typeParameterFromError;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firCallableDeclaration.getClass();
        if (checkerContext.getContainingDeclarations().size() > 1 || (source = firCallableDeclaration.getSource()) == null || (source.getKind() instanceof KtFakeSourceElementKind)) {
            return;
        }
        ArrayList arrayList = new ArrayList();
        ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firCallableDeclaration.getReturnTypeRef());
        if (isKClassTypeWithErrorOrNullableArgument(coneType, TypeComponentsKt.getTypeContext(checkerContext.getSession()))) {
            arrayList.add(coneType);
        }
        for (ConeTypeProjection coneTypeProjection : coneType.getTypeArguments()) {
            ConeKotlinType type2 = ConeTypeProjectionKt.getType(coneTypeProjection);
            if (type2 != null && INSTANCE.isKClassTypeWithErrorOrNullableArgument(type2, TypeComponentsKt.getTypeContext(checkerContext.getSession()))) {
                arrayList.add(type2);
            }
        }
        if (arrayList.isEmpty()) {
            return;
        }
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ConeKotlinTypeProjection coneKotlinTypeProjection = ((ConeKotlinType) it.next()).getTypeArguments()[0];
            ConeKotlinTypeProjection coneKotlinTypeProjection2 = coneKotlinTypeProjection instanceof ConeKotlinTypeProjection ? coneKotlinTypeProjection : null;
            if (coneKotlinTypeProjection2 == null || (type = coneKotlinTypeProjection2.getType()) == null || (typeParameterFromError = INSTANCE.getTypeParameterFromError(type)) == null) {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
            } else {
                checkerContext2 = checkerContext;
                diagnosticReporter2 = diagnosticReporter;
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirErrors.INSTANCE.getKCLASS_WITH_NULLABLE_TYPE_PARAMETER_IN_SIGNATURE(), (Object) typeParameterFromError, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            }
            checkerContext = checkerContext2;
            diagnosticReporter = diagnosticReporter2;
        }
    }
}
