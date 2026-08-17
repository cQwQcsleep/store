package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.state.InlineClassManglingUtilsKt;
import org.jetbrains.kotlin.config.LanguageVersionSettings;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedErrorReference;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.ConeTypeParameterLookupTag;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeCapturedType;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeTypesKt;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJA\u0010\u000e\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u00102\n\u0010\u0011\u001a\u0006\u0012\u0002\b\u00030\u0012H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\u0013J\f\u0010\u0014\u001a\u00020\u0015*\u00020\u0016H\u0002J\u0014\u0010\u0017\u001a\u00020\u0015*\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J_\u0010\u001b\u001a\u00020\u00072\u0006\u0010\u001c\u001a\u00020\u00182\u0006\u0010\u001d\u001a\u00020\u00162\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\u00152\u0006\u0010!\u001a\u00020\u00152\u0006\u0010\"\u001a\u00020\u00152\b\b\u0002\u0010#\u001a\u00020\u0018H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010$¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReifiedChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)V", "validateReturnTypeVisibility", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "callableSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;Lorg/jetbrains/kotlin/fir/references/FirReference;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)V", "isReifiedTypeParameterOrFromKotlinArray", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "cannotBeReified", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "languageVersionSettings", "Lorg/jetbrains/kotlin/config/LanguageVersionSettings;", "checkArgumentAndReport", "typeArgument", "typeParameter", "source", "Lorg/jetbrains/kotlin/KtSourceElement;", "isExplicit", "isArray", "isPlaceHolder", "fullyExpandedType", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;Lorg/jetbrains/kotlin/KtSourceElement;ZZZLorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirReifiedChecker extends FirExpressionChecker<FirQualifiedAccessExpression> {
    public static final FirReifiedChecker INSTANCE = new FirReifiedChecker();

    private FirReifiedChecker() {
        super(MppCheckerKind.Common);
    }

    private final boolean cannotBeReified(ConeKotlinType coneKotlinType, LanguageVersionSettings languageVersionSettings) {
        return (coneKotlinType instanceof ConeCapturedType) || (coneKotlinType instanceof ConeDynamicType) || FirArrayOfNothingQualifierCheckerKt.unsupportedKindOfNothingAsReifiedOrInArray(coneKotlinType, languageVersionSettings) != null;
    }

    private final void checkArgumentAndReport(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, ConeKotlinType coneKotlinType, FirTypeParameterSymbol firTypeParameterSymbol, KtSourceElement ktSourceElement, boolean z, boolean z2, boolean z3, ConeKotlinType coneKotlinType2) {
        if (Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(coneKotlinType2), StandardClassIds.INSTANCE.getArray())) {
            for (ConeKotlinType coneKotlinType3 : coneKotlinType2.getTypeArguments()) {
                if (coneKotlinType3 instanceof ConeKotlinType) {
                    checkArgumentAndReport$default(INSTANCE, checkerContext, diagnosticReporter, coneKotlinType3, firTypeParameterSymbol, ktSourceElement, z, true, z3, null, 256, null);
                }
            }
            return;
        }
        if (FirTypeVisibilityHelpersKt.isTypeVisibilityBroken(checkerContext, coneKotlinType2, false) && (!z || z3)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactoryForDeprecation2) FirErrors.INSTANCE.getINFERRED_INVISIBLE_REIFIED_TYPE_ARGUMENT(), (Object) firTypeParameterSymbol, (Object) coneKotlinType2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        }
        if (coneKotlinType instanceof ConeTypeParameterType) {
            FirTypeParameterSymbol typeParameterSymbol = ((ConeTypeParameterType) coneKotlinType).getLookupTag().getTypeParameterSymbol();
            if (typeParameterSymbol.isReified()) {
                return;
            }
            FirErrors firErrors = FirErrors.INSTANCE;
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) (z2 ? firErrors.getTYPE_PARAMETER_AS_REIFIED_ARRAY_ERROR() : firErrors.getTYPE_PARAMETER_AS_REIFIED()), (Object) typeParameterSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            return;
        }
        if ((coneKotlinType instanceof ConeDefinitelyNotNullType) && z) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, FirErrors.INSTANCE.getDEFINITELY_NON_NULLABLE_AS_REIFIED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        } else if (cannotBeReified(coneKotlinType, checkerContext.get$languageVersionSettings())) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactory1) FirErrors.INSTANCE.getREIFIED_TYPE_FORBIDDEN_SUBSTITUTION(), (Object) coneKotlinType, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        } else if (coneKotlinType instanceof ConeIntersectionType) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactoryForDeprecation2) FirErrors.INSTANCE.getTYPE_INTERSECTION_AS_REIFIED(), (Object) firTypeParameterSymbol, (Object) ((ConeIntersectionType) coneKotlinType).getIntersectedTypes(), (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        }
    }

    public static /* synthetic */ void checkArgumentAndReport$default(FirReifiedChecker firReifiedChecker, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, ConeKotlinType coneKotlinType, FirTypeParameterSymbol firTypeParameterSymbol, KtSourceElement ktSourceElement, boolean z, boolean z2, boolean z3, ConeKotlinType coneKotlinType2, int i, Object obj) {
        firReifiedChecker.checkArgumentAndReport(checkerContext, diagnosticReporter, coneKotlinType, firTypeParameterSymbol, ktSourceElement, z, z2, z3, (i & 256) != 0 ? TypeExpansionUtilsKt.fullyExpandedType(checkerContext, coneKotlinType) : coneKotlinType2);
    }

    private final boolean isReifiedTypeParameterOrFromKotlinArray(FirTypeParameterSymbol firTypeParameterSymbol) {
        FirBasedSymbol<?> containingDeclarationSymbol = firTypeParameterSymbol.getContainingDeclarationSymbol();
        if (firTypeParameterSymbol.isReified()) {
            return true;
        }
        return (containingDeclarationSymbol instanceof FirRegularClassSymbol) && Intrinsics.areEqual(((FirRegularClassSymbol) containingDeclarationSymbol).getClassId(), StandardClassIds.INSTANCE.getArray());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private final void validateReturnTypeVisibility(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression, FirReference firReference, FirCallableSymbol<?> firCallableSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firQualifiedAccessExpression);
        if (firReference instanceof FirResolvedErrorReference) {
            return;
        }
        List<FirTypeProjection> typeArguments = firQualifiedAccessExpression.getTypeArguments();
        if (!(typeArguments instanceof Collection) || !typeArguments.isEmpty()) {
            for (FirTypeProjection firTypeProjection : typeArguments) {
                if ((firTypeProjection instanceof FirTypeProjectionWithVariance) && (((FirTypeProjectionWithVariance) firTypeProjection).getTypeRef() instanceof FirErrorTypeRef)) {
                    return;
                }
            }
        }
        if (firQualifiedAccessExpression instanceof FirFunctionCall) {
            List<FirExpression> listPlus = CollectionsKt.plus(FirExpressionUtilKt.getAllReceiverExpressions(firQualifiedAccessExpression), ((FirCall) firQualifiedAccessExpression).getArgumentList().getArguments());
            if (!(listPlus instanceof Collection) || !listPlus.isEmpty()) {
                for (FirExpression firExpression : listPlus) {
                    if ((FirTypeUtilsKt.getResolvedType(firExpression) instanceof ConeErrorType) || FirTypeVisibilityHelpersKt.isTypeVisibilityBroken(checkerContext, FirTypeUtilsKt.getResolvedType(firExpression), true)) {
                        return;
                    }
                }
            }
            if (FirTypeVisibilityHelpersKt.isTypeVisibilityBroken(checkerContext, resolvedType, true)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirFunctionCall) firQualifiedAccessExpression).getSource(), (KtDiagnosticFactoryForDeprecation2) FirErrors.INSTANCE.getINFERRED_INVISIBLE_RETURN_TYPE(), (Object) firCallableSymbol, (Object) resolvedType, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression) throws KotlinIllegalArgumentExceptionWithAttachments {
        List<FirTypeParameterSymbol> typeParameterSymbols;
        FirValueParameterSymbol firValueParameterSymbol;
        ConeKotlinType type;
        ConeKotlinType coneKotlinTypeFullyExpandedType;
        List<FirTypeParameterSymbol> list;
        int i;
        FirTypeParameterSymbol firTypeParameterSymbol;
        int i2;
        FirValueParameterSymbol firValueParameterSymbol2;
        ConeTypeParameterLookupTag lookupTag;
        ConeKotlinType resolvedReturnType;
        List<FirValueParameterSymbol> valueParameterSymbols;
        CheckerContext checkerContext2 = checkerContext;
        checkerContext2.getClass();
        diagnosticReporter.getClass();
        firQualifiedAccessExpression.getClass();
        FirReference calleeReference = firQualifiedAccessExpression.getCalleeReference();
        List<FirTypeProjection> typeArguments = firQualifiedAccessExpression.getTypeArguments();
        KtSourceElementKind ktSourceElementKind = null;
        FirCallableSymbol<?> resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(calleeReference, false, 1, null);
        if (resolvedCallableSymbol$default == null || (typeParameterSymbols = resolvedCallableSymbol$default.getTypeParameterSymbols()) == null) {
            return;
        }
        int iMin = Math.min(typeArguments.size(), typeParameterSymbols.size());
        FirFunctionSymbol firFunctionSymbol = resolvedCallableSymbol$default instanceof FirFunctionSymbol ? (FirFunctionSymbol) resolvedCallableSymbol$default : null;
        if (firFunctionSymbol == null || (valueParameterSymbols = firFunctionSymbol.getValueParameterSymbols()) == null) {
            firValueParameterSymbol = null;
        } else {
            Iterator<T> it = valueParameterSymbols.iterator();
            boolean z = false;
            Object obj = null;
            while (true) {
                if (!it.hasNext()) {
                    if (!z) {
                        break;
                    } else {
                        break;
                    }
                } else {
                    Object next = it.next();
                    if (((FirValueParameterSymbol) next).isVararg()) {
                        if (!z) {
                            obj = next;
                            z = true;
                        }
                    }
                }
                obj = null;
                break;
            }
            firValueParameterSymbol = (FirValueParameterSymbol) obj;
        }
        ConeKotlinType coneKotlinTypeArrayElementType$default = (firValueParameterSymbol == null || (resolvedReturnType = firValueParameterSymbol.getResolvedReturnType()) == null) ? null : FirTypeUtilsKt.arrayElementType$default(resolvedReturnType, false, 1, null);
        ConeSimpleKotlinType coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound = coneKotlinTypeArrayElementType$default != null ? ConeTypesKt.unwrapToSimpleTypeUsingLowerBound(coneKotlinTypeArrayElementType$default) : null;
        ConeTypeParameterType coneTypeParameterType = coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound instanceof ConeTypeParameterType ? (ConeTypeParameterType) coneSimpleKotlinTypeUnwrapToSimpleTypeUsingLowerBound : null;
        FirTypeParameterSymbol typeParameterSymbol = (coneTypeParameterType == null || (lookupTag = coneTypeParameterType.getLookupTag()) == null) ? null : lookupTag.getTypeParameterSymbol();
        int i3 = 0;
        while (i3 < iMin) {
            FirTypeProjection firTypeProjection = typeArguments.get(i3);
            KtSourceElement source = firTypeProjection.getSource();
            if ((source == null && (source = calleeReference.getSource()) == null) || (type = ConeTypeProjectionKt.getType(FirTypeUtilsKt.toConeTypeProjection(firTypeProjection))) == null || (coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext2, type)) == null) {
                list = typeParameterSymbols;
                i = iMin;
                firTypeParameterSymbol = typeParameterSymbol;
                i2 = i3;
            } else {
                FirTypeParameterSymbol firTypeParameterSymbol2 = typeParameterSymbols.get(i3);
                KtSourceElement source2 = firTypeProjection.getSource();
                boolean zAreEqual = Intrinsics.areEqual(source2 != null ? source2.getKind() : ktSourceElementKind, KtRealSourceElementKind.INSTANCE);
                boolean z2 = zAreEqual && Intrinsics.areEqual(KtSourceElementKt.getText(firTypeProjection.getSource()), InlineClassManglingUtilsKt.NOT_INLINE_CLASS_PARAMETER_PLACEHOLDER);
                boolean z3 = !zAreEqual || z2;
                if (isReifiedTypeParameterOrFromKotlinArray(firTypeParameterSymbol2)) {
                    list = typeParameterSymbols;
                    i2 = i3;
                    firTypeParameterSymbol = typeParameterSymbol;
                    i = iMin;
                    checkArgumentAndReport(checkerContext2, diagnosticReporter, coneKotlinTypeFullyExpandedType, firTypeParameterSymbol2, source, zAreEqual, false, z2, coneKotlinTypeFullyExpandedType);
                } else {
                    list = typeParameterSymbols;
                    i = iMin;
                    firTypeParameterSymbol = typeParameterSymbol;
                    i2 = i3;
                    KtSourceElement ktSourceElement = source;
                    if (Intrinsics.areEqual(firTypeParameterSymbol, firTypeParameterSymbol2) && FirTypeVisibilityHelpersKt.isTypeVisibilityBroken(checkerContext2, coneKotlinTypeFullyExpandedType, false) && z3) {
                        firValueParameterSymbol2 = firValueParameterSymbol;
                        KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext2, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (KtDiagnosticFactoryForDeprecation3<FirTypeParameterSymbol, ConeKotlinType, FirValueParameterSymbol>) ((KtDiagnosticFactoryForDeprecation3<Object, Object, Object>) FirErrors.INSTANCE.getINFERRED_INVISIBLE_VARARG_TYPE_ARGUMENT()), firTypeParameterSymbol2, coneKotlinTypeFullyExpandedType, firValueParameterSymbol2, (64 & 64) != 0 ? null : null);
                    }
                    i3 = i2 + 1;
                    checkerContext2 = checkerContext;
                    firValueParameterSymbol = firValueParameterSymbol2;
                    typeParameterSymbols = list;
                    typeParameterSymbol = firTypeParameterSymbol;
                    iMin = i;
                    ktSourceElementKind = null;
                }
            }
            firValueParameterSymbol2 = firValueParameterSymbol;
            i3 = i2 + 1;
            checkerContext2 = checkerContext;
            firValueParameterSymbol = firValueParameterSymbol2;
            typeParameterSymbols = list;
            typeParameterSymbol = firTypeParameterSymbol;
            iMin = i;
            ktSourceElementKind = null;
        }
        validateReturnTypeVisibility(checkerContext2, diagnosticReporter, firQualifiedAccessExpression, calleeReference, resolvedCallableSymbol$default);
    }
}
