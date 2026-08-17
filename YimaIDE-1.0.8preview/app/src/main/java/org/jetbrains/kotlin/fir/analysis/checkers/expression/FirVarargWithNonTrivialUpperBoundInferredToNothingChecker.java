package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
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
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.SubstitutionUtilsKt;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScopeKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirVarargWithNonTrivialUpperBoundInferredToNothingChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirVarargWithNonTrivialUpperBoundInferredToNothingChecker extends FirExpressionChecker<FirFunctionCall> {
    public static final FirVarargWithNonTrivialUpperBoundInferredToNothingChecker INSTANCE = new FirVarargWithNonTrivialUpperBoundInferredToNothingChecker();

    private FirVarargWithNonTrivialUpperBoundInferredToNothingChecker() {
        super(MppCheckerKind.Common);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) {
        FirFunctionSymbol resolvedFunctionSymbol$default;
        ConeSubstitutor coneSubstitutorCreateConeSubstitutorFromTypeArguments$default;
        FirTypeParameterSymbol typeParameterSymbol;
        ConeKotlinType type;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        if (firFunctionCall.getTypeArguments().isEmpty() || (resolvedFunctionSymbol$default = FirReferenceUtilsKt.toResolvedFunctionSymbol$default(firFunctionCall.getCalleeReference(), false, 1, null)) == null) {
            return;
        }
        List<FirValueParameterSymbol> valueParameterSymbols = resolvedFunctionSymbol$default.getValueParameterSymbols();
        ArrayList arrayList = new ArrayList();
        for (Object obj : valueParameterSymbols) {
            if (((FirValueParameterSymbol) obj).isVararg()) {
                arrayList.add(obj);
            }
        }
        ArrayList<FirTypeParameterSymbol> arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ConeTypeProjection coneTypeProjectionArrayElementTypeArgument$default = FirTypeUtilsKt.arrayElementTypeArgument$default(((FirValueParameterSymbol) it.next()).getResolvedReturnTypeRef().getConeType(), false, 1, null);
            if (coneTypeProjectionArrayElementTypeArgument$default == null || (type = ConeTypeProjectionKt.getType(coneTypeProjectionArrayElementTypeArgument$default)) == null || (typeParameterSymbol = ToSymbolUtilsKt.toTypeParameterSymbol(checkerContext, type)) == null || typeParameterSymbol.isReified()) {
                typeParameterSymbol = null;
            }
            if (typeParameterSymbol != null) {
                arrayList2.add(typeParameterSymbol);
            }
        }
        if (arrayList2.isEmpty()) {
            arrayList2 = null;
        }
        if (arrayList2 == null || (coneSubstitutorCreateConeSubstitutorFromTypeArguments$default = SubstitutionUtilsKt.createConeSubstitutorFromTypeArguments$default(firFunctionCall, checkerContext.getSession(), false, 2, null)) == null) {
            return;
        }
        for (FirTypeParameterSymbol firTypeParameterSymbol : arrayList2) {
            ConeKotlinType coneKotlinTypeSubstituteOrSelf = coneSubstitutorCreateConeSubstitutorFromTypeArguments$default.substituteOrSelf(FirNestedClassifierScopeKt.toConeType(firTypeParameterSymbol));
            if (!ConeBuiltinTypeUtilsKt.isNothing(coneKotlinTypeSubstituteOrSelf)) {
                if (ConeBuiltinTypeUtilsKt.isNullableNothing(coneKotlinTypeSubstituteOrSelf)) {
                    List<FirResolvedTypeRef> resolvedBounds = firTypeParameterSymbol.getResolvedBounds();
                    if (!(resolvedBounds instanceof Collection) || !resolvedBounds.isEmpty()) {
                        Iterator<T> it2 = resolvedBounds.iterator();
                        while (true) {
                            if (it2.hasNext()) {
                                if (!ConeBuiltinTypeUtilsKt.isAnyOrNullableAny(((FirResolvedTypeRef) it2.next()).getConeType())) {
                                }
                            }
                        }
                    }
                }
            }
            FirTypeProjection firTypeProjection = (FirTypeProjection) CollectionsKt.getOrNull(firFunctionCall.getTypeArguments(), resolvedFunctionSymbol$default.getTypeParameterSymbols().indexOf(firTypeParameterSymbol));
            KtSourceElement source = firTypeProjection != null ? firTypeProjection.getSource() : null;
            if (source == null) {
                source = firFunctionCall.getSource();
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirErrors.INSTANCE.getILLEGAL_TYPE_ARGUMENT_FOR_VARARG_PARAMETER_WARNING(), (Object) coneKotlinTypeSubstituteOrSelf, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }
}
