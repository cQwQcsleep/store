package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.SubstitutionUtilsKt;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.scopes.impl.FirAbstractImportingScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.ImportedFromObjectOrStaticData;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeProjectionKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/expression/FirJavaSamConstructorNullabilityChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;)V", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirJavaSamConstructorNullabilityChecker extends FirExpressionChecker<FirFunctionCall> {
    public static final FirJavaSamConstructorNullabilityChecker INSTANCE = new FirJavaSamConstructorNullabilityChecker();

    private FirJavaSamConstructorNullabilityChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirFunctionCall firFunctionCall) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirRegularClassSymbol regularClassSymbol;
        Set<Map.Entry<FirExpression, FirValueParameter>> setEntrySet;
        Map.Entry entry;
        ConeKotlinType type;
        ConeKotlinType coneKotlinTypeSubstituteOrSelf;
        ImportedFromObjectOrStaticData importedFromObjectOrStaticData;
        FirCallableDeclaration original;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firFunctionCall.getClass();
        boolean zIsEnabled = LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ProhibitReturningIncorrectNullabilityValuesFromSamConstructorLambdaOfJdkInterfaces);
        FirNamedReference calleeReference = firFunctionCall.getCalleeReference();
        if (FirReferenceUtilsKt.isError(calleeReference)) {
            return;
        }
        FirFunctionSymbol resolvedFunctionSymbol$default = FirReferenceUtilsKt.toResolvedFunctionSymbol$default(calleeReference, false, 1, null);
        if (resolvedFunctionSymbol$default == null || !Intrinsics.areEqual(resolvedFunctionSymbol$default.getOrigin(), FirDeclarationOrigin.SamConstructor.INSTANCE) || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(checkerContext, resolvedFunctionSymbol$default.getResolvedReturnType())) == null) {
            return;
        }
        FirDeclarationOrigin origin = regularClassSymbol.getOrigin();
        if (!(origin instanceof FirDeclarationOrigin.Java) && !Intrinsics.areEqual(origin, FirDeclarationOrigin.Enhancement.INSTANCE)) {
            E fir = regularClassSymbol.getFir();
            FirCallableDeclaration firCallableDeclaration = fir instanceof FirCallableDeclaration ? (FirCallableDeclaration) fir : null;
            if (firCallableDeclaration == null || (importedFromObjectOrStaticData = FirAbstractImportingScopeKt.getImportedFromObjectOrStaticData(firCallableDeclaration)) == null || (original = importedFromObjectOrStaticData.getOriginal()) == null || !DeclarationUtilsKt.isJavaOrEnhancement(original)) {
                return;
            }
        }
        FirArgumentList argumentList = firFunctionCall.getArgumentList();
        LinkedHashMap<FirExpression, FirValueParameter> mapping = argumentList instanceof FirResolvedArgumentList ? ((FirResolvedArgumentList) argumentList).getMapping() : null;
        if (mapping == null || (setEntrySet = mapping.entrySet()) == null || (entry = (Map.Entry) CollectionsKt.singleOrNull(setEntrySet)) == null) {
            return;
        }
        Object key = entry.getKey();
        key.getClass();
        FirExpression firExpression = (FirExpression) key;
        Object value = entry.getValue();
        value.getClass();
        FirValueParameter firValueParameter = (FirValueParameter) value;
        if (firExpression instanceof FirAnonymousFunctionExpression) {
            ConeKotlinType coneType = FirTypeUtilsKt.getConeType(firValueParameter.getReturnTypeRef());
            ConeSubstitutor coneSubstitutorCreateConeSubstitutorFromTypeArguments$default = SubstitutionUtilsKt.createConeSubstitutorFromTypeArguments$default(firFunctionCall, resolvedFunctionSymbol$default, checkerContext.getSession(), false, true, 4, null);
            ConeTypeProjection coneTypeProjection = (ConeTypeProjection) ArraysKt.lastOrNull(coneType.getTypeArguments());
            if (coneTypeProjection == null || (type = ConeTypeProjectionKt.getType(coneTypeProjection)) == null || (coneKotlinTypeSubstituteOrSelf = coneSubstitutorCreateConeSubstitutorFromTypeArguments$default.substituteOrSelf(type)) == null) {
                return;
            }
            for (FirExpression firExpression2 : FirHelpersKt.getReturnedExpressions(((FirAnonymousFunctionExpression) firExpression).getAnonymousFunction().getSymbol())) {
                ConeKotlinType resolvedType = FirTypeUtilsKt.getResolvedType(firExpression2);
                ConeKotlinType coneKotlinType = coneKotlinTypeSubstituteOrSelf;
                if (AbstractTypeChecker.isSubtypeOf$default(AbstractTypeChecker.INSTANCE, TypeComponentsKt.getTypeContext(checkerContext.getSession()), resolvedType, coneKotlinType, false, 8, (Object) null)) {
                    coneKotlinTypeSubstituteOrSelf = coneKotlinType;
                } else if (zIsEnabled) {
                    coneKotlinTypeSubstituteOrSelf = coneKotlinType;
                    KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firExpression2.getSource(), (KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, Boolean>) ((KtDiagnosticFactory3<Object, Object, Object>) FirErrors.INSTANCE.getARGUMENT_TYPE_MISMATCH()), resolvedType, coneKotlinTypeSubstituteOrSelf, Boolean.TRUE, (64 & 64) != 0 ? null : null);
                } else {
                    CheckerContext checkerContext2 = checkerContext;
                    DiagnosticReporter diagnosticReporter2 = diagnosticReporter;
                    coneKotlinTypeSubstituteOrSelf = coneKotlinType;
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter2, (AbstractKtSourceElement) firExpression2.getSource(), (KtDiagnosticFactory2) FirJvmErrors.INSTANCE.getTYPE_MISMATCH_WHEN_FLEXIBILITY_CHANGES(), (Object) resolvedType, (Object) coneKotlinTypeSubstituteOrSelf, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
                    checkerContext = checkerContext2;
                    diagnosticReporter = diagnosticReporter2;
                }
            }
        }
    }
}
