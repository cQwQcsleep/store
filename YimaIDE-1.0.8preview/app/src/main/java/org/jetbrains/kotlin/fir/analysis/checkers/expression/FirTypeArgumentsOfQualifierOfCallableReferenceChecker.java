package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.Severity;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategies;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategy;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.SessionHolder;
import org.jetbrains.kotlin.fir.analysis.checkers.FE10LikeConeSubstitutor;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirUpperBoundViolatedHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.ProjectionRelationCheckerImpl;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.diagnostics.ConeDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.resolve.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeOuterClassArgumentsRequired;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConePlaceholderProjectionInQualifierResolution;
import org.jetbrains.kotlin.fir.resolve.diagnostics.ConeWrongNumberOfTypeArgumentsError;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeAttributes;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005JK\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u0011\u001a\u00020\u00122\n\u0010\u0013\u001a\u0006\u0012\u0002\b\u00030\u00142\u0006\u0010\u0015\u001a\u00020\u0016H\u0002R\u00020\bR\u00020\u000ej\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u0017J-\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0010\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\u000ej\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0002\u0010\u001aR!\u0010\u0006\u001a\u00020\u00078BX\u0082\u0004b\u00020\b\u008a\u0001\u0006\u0010\t\u001a\u00020\b¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirTypeArgumentsOfQualifierOfCallableReferenceChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCallableReferenceAccessChecker;", "<init>", "()V", "innerClassesProperlySupported", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "getInnerClassesProperlySupported", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)Z", "checkNonFatalDiagnostics", "Lorg/jetbrains/kotlin/diagnostics/Severity;", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "lhs", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "classSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "lhsType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lorg/jetbrains/kotlin/diagnostics/Severity;", "check", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTypeArgumentsOfQualifierOfCallableReferenceChecker extends FirExpressionChecker<FirCallableReferenceAccess> {
    public static final FirTypeArgumentsOfQualifierOfCallableReferenceChecker INSTANCE = new FirTypeArgumentsOfQualifierOfCallableReferenceChecker();

    private FirTypeArgumentsOfQualifierOfCallableReferenceChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final Severity checkNonFatalDiagnostics(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableReferenceAccess firCallableReferenceAccess, FirResolvedQualifier firResolvedQualifier, FirClassLikeSymbol<?> firClassLikeSymbol, ConeKotlinType coneKotlinType) {
        boolean z = false;
        for (ConeDiagnostic coneDiagnostic : firCallableReferenceAccess.getNonFatalDiagnostics()) {
            if (coneDiagnostic instanceof ConeWrongNumberOfTypeArgumentsError) {
                ConeWrongNumberOfTypeArgumentsError coneWrongNumberOfTypeArgumentsError = (ConeWrongNumberOfTypeArgumentsError) coneDiagnostic;
                SourceElementPositioningStrategy type_argument_list_or_without_receiver = coneWrongNumberOfTypeArgumentsError.getIsDeprecationErrorForCallableReferenceLHS() ? SourceElementPositioningStrategies.INSTANCE.getTYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER() : SourceElementPositioningStrategies.INSTANCE.getDEFAULT();
                if (!coneWrongNumberOfTypeArgumentsError.getIsDeprecationErrorForCallableReferenceLHS() || getInnerClassesProperlySupported(checkerContext)) {
                    KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) coneWrongNumberOfTypeArgumentsError.getSource(), (KtDiagnosticFactory2<Integer, FirClassLikeSymbol<?>>) FirErrors.INSTANCE.getWRONG_NUMBER_OF_TYPE_ARGUMENTS(), Integer.valueOf(coneWrongNumberOfTypeArgumentsError.getDesiredCount()), coneWrongNumberOfTypeArgumentsError.getSymbol(), (AbstractSourceElementPositioningStrategy) type_argument_list_or_without_receiver);
                    return Severity.ERROR;
                }
                if (((FirClassLikeDeclaration) firClassLikeSymbol.getFir()).getIsLocal()) {
                    KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) coneWrongNumberOfTypeArgumentsError.getSource(), (KtDiagnosticFactory2<Integer, FirClassLikeSymbol<?>>) FirErrors.INSTANCE.getWRONG_NUMBER_OF_TYPE_ARGUMENTS_IN_LOCAL_CLASS_IN_LHS_WARNING(), Integer.valueOf(coneWrongNumberOfTypeArgumentsError.getDesiredCount()), coneWrongNumberOfTypeArgumentsError.getSymbol(), (AbstractSourceElementPositioningStrategy) type_argument_list_or_without_receiver);
                } else {
                    KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) coneWrongNumberOfTypeArgumentsError.getSource(), (KtDiagnosticFactory3<Integer, FirClassLikeSymbol<?>, ConeKotlinType>) FirErrors.INSTANCE.getWRONG_NUMBER_OF_TYPE_ARGUMENTS_WARNING(), Integer.valueOf(coneWrongNumberOfTypeArgumentsError.getDesiredCount()), coneWrongNumberOfTypeArgumentsError.getSymbol(), coneKotlinType, (AbstractSourceElementPositioningStrategy) type_argument_list_or_without_receiver);
                }
                z = true;
            } else if (coneDiagnostic instanceof ConeOuterClassArgumentsRequired) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firResolvedQualifier.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getOUTER_CLASS_ARGUMENTS_REQUIRED(), (Object) ((ConeOuterClassArgumentsRequired) coneDiagnostic).getSymbol(), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                return Severity.ERROR;
            }
        }
        Severity severity = Severity.WARNING;
        if (z) {
            return severity;
        }
        return null;
    }

    private final boolean getInnerClassesProperlySupported(CheckerContext checkerContext) {
        return LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ProperSupportOfInnerClassesInCallableReferenceLHS);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r23v0, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r5v1 */
    /* JADX WARN: Type inference failed for: r5v2 */
    /* JADX WARN: Type inference failed for: r5v3, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r5v5, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r5v6 */
    /* JADX WARN: Type inference failed for: r5v7 */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirCallableReferenceAccess firCallableReferenceAccess) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirClassLikeSymbol<?> symbol;
        ConeKotlinType resolvedLHSTypeForCallableReferenceOrNull;
        ?? arrayList;
        CheckerContext checkerContext2;
        ?? r5;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firCallableReferenceAccess.getClass();
        FirExpression explicitReceiver = firCallableReferenceAccess.getExplicitReceiver();
        FirExpression firExpressionUnwrapSmartcastExpression = explicitReceiver != null ? FirExpressionUtilKt.unwrapSmartcastExpression(explicitReceiver) : null;
        FirResolvedQualifier firResolvedQualifier = firExpressionUnwrapSmartcastExpression instanceof FirResolvedQualifier ? (FirResolvedQualifier) firExpressionUnwrapSmartcastExpression : null;
        if (firResolvedQualifier == null || (symbol = firResolvedQualifier.getSymbol()) == null || (resolvedLHSTypeForCallableReferenceOrNull = firResolvedQualifier.getResolvedLHSTypeForCallableReferenceOrNull()) == null) {
            return;
        }
        for (FirTypeProjection firTypeProjection : firResolvedQualifier.getTypeArguments()) {
            FirTypeProjectionWithVariance firTypeProjectionWithVariance = firTypeProjection instanceof FirTypeProjectionWithVariance ? (FirTypeProjectionWithVariance) firTypeProjection : null;
            Object typeRef = firTypeProjectionWithVariance != null ? firTypeProjectionWithVariance.getTypeRef() : null;
            FirErrorTypeRef firErrorTypeRef = typeRef instanceof FirErrorTypeRef ? (FirErrorTypeRef) typeRef : null;
            if (firErrorTypeRef != null && (firErrorTypeRef.getDiagnostic() instanceof ConePlaceholderProjectionInQualifierResolution)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ((FirTypeProjectionWithVariance) firTypeProjection).getSource(), FirErrors.INSTANCE.getPLACEHOLDER_PROJECTION_IN_QUALIFIER(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
            }
        }
        Severity severityCheckNonFatalDiagnostics = checkNonFatalDiagnostics(checkerContext, diagnosticReporter, firCallableReferenceAccess, firResolvedQualifier, symbol, resolvedLHSTypeForCallableReferenceOrNull);
        if (severityCheckNonFatalDiagnostics == Severity.ERROR) {
            return;
        }
        List<ConeTypeProjection> typeArgumentsWithSourceInfo = FirUpperBoundViolatedHelpersKt.toTypeArgumentsWithSourceInfo(firResolvedQualifier.getTypeArguments());
        if (getInnerClassesProperlySupported(checkerContext)) {
            int length = resolvedLHSTypeForCallableReferenceOrNull.getTypeArguments().length;
            arrayList = new ArrayList(length);
            int i = 0;
            while (i < length) {
                arrayList.add(i < typeArgumentsWithSourceInfo.size() ? typeArgumentsWithSourceInfo.get(i) : resolvedLHSTypeForCallableReferenceOrNull.getTypeArguments()[i]);
                i++;
            }
        } else {
            arrayList = typeArgumentsWithSourceInfo;
        }
        List<FirTypeParameterSymbol> typeParameterSymbols = getInnerClassesProperlySupported(checkerContext) ? symbol.getTypeParameterSymbols() : DeclarationUtilsKt.getClassTypeParameterSymbols(symbol);
        boolean z = symbol instanceof FirTypeAliasSymbol;
        if (z) {
            checkerContext2 = checkerContext;
            List list = ArraysKt.toList(TypeExpansionUtilsKt.fullyExpandedType((SessionHolder) checkerContext2, TypeConstructionUtilsKt.constructType$default((FirClassLikeSymbol) symbol, (ConeTypeProjection[]) typeArgumentsWithSourceInfo.toArray(new ConeTypeProjection[0]), false, (ConeAttributes) null, 6, (Object) null)).getTypeArguments());
            FirRegularClassSymbol regularClassSymbol = TypeUtilsKt.toRegularClassSymbol(((FirTypeAliasSymbol) symbol).getResolvedExpandedTypeRef(), checkerContext2.getSession());
            if (regularClassSymbol == null) {
                return;
            }
            typeParameterSymbols = regularClassSymbol.getTypeParameterSymbols();
            r5 = list;
        } else {
            checkerContext2 = checkerContext;
            r5 = arrayList;
        }
        FirUpperBoundViolatedHelpersKt.checkUpperBoundViolated$default(checkerContext2, diagnosticReporter, typeParameterSymbols, r5, new FE10LikeConeSubstitutor(typeParameterSymbols, r5, checkerContext2.getSession()), false, false, firResolvedQualifier.getSource(), false, false, false, z, 1888, null);
        if (severityCheckNonFatalDiagnostics == null) {
            ProjectionRelationCheckerImpl.INSTANCE.doCheck(checkerContext, diagnosticReporter, FirHelpersKt.extractArgumentsTypeRefAndSource(firResolvedQualifier), resolvedLHSTypeForCallableReferenceOrNull, new ProjectionRelationCheckerImpl.Deprecation(LanguageFeature.ProperSupportOfInnerClassesInCallableReferenceLHS, FirErrors.INSTANCE.getCONFLICTING_PROJECTION_IN_CALLABLE_REFERENCE_WARNING()));
        }
    }
}
