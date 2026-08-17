package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import com.intellij.psi.tree.IElementType;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.SourceElementPositioningStrategies;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.analysis.FirSourceUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FE10LikeConeSubstitutor;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirUpperBoundViolatedHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirResolvedReifiedParameterReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassifierSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeStarProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeParameterType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.lexer.KtSingleValueToken;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.resolve.checkers.OptInNames;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0011\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\u000fj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010\u0012J%\u0010\u0013\u001a\u00020\u0007*\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0007H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0016J=\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020\u00182\u0006\u0010!\u001a\u00020\u00142\u0006\u0010\"\u001a\u00020#H\u0002R\u00020\bR\u00020\u000fj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010$J8\u0010%\u001a\u00020\u00072\b\u0010&\u001a\u0004\u0018\u00010'2\u0006\u0010(\u001a\u00020\u0007H\u0082\u0010R\u00020\bR\u00020\u000fj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u0010)J\u001d\u0010.\u001a\u00020\u0007*\u00020\u0014H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010/J\u001d\u00100\u001a\u00020\u0007*\u00020\u0014H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010/JA\u00101\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020'2\n\u00102\u001a\u0006\u0012\u0002\b\u00030,2\u0006\u0010!\u001a\u00020\u0014H\u0002R\u00020\bR\u00020\u000fj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0002\u00103R!\u0010\u0006\u001a\u00020\u00078BX\u0082\u0004b\u00020\b\u008a\u0001\u0006\u0010\t\u001a\u00020\b¢\u0006\u0006\u001a\u0004\b\u0006\u0010\nR!\u0010\u000b\u001a\u00020\u00078BX\u0082\u0004b\u00020\b\u008a\u0001\u0006\u0010\t\u001a\u00020\b¢\u0006\u0006\u001a\u0004\b\f\u0010\nR\u0018\u0010\u0017\u001a\u00020\u0007*\u00020\u00188BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u001aR\u001a\u0010\u001b\u001a\u0004\u0018\u00010\u001c*\u00020\u00188BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR)\u0010*\u001a\u00020\u0007*\u0006\u0012\u0002\b\u00030,8BX\u0082\u0004b\u00020\b\u008a\u0001\u0006\u0010+\u001a\u00020\b¢\u0006\u0006\u001a\u0004\b*\u0010-¨\u00064"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirClassLiteralChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirGetClassCallChecker;", "<init>", "()V", "isGenericArrayAllowed", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)Z", "areUselessTypeArgumentsForbidden", "getAreUselessTypeArgumentsForbidden", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;)V", "isNullableTypeParameter", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "isExpression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Z)Z", "canBeDoubleColonLHSAsType", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "getCanBeDoubleColonLHSAsType", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Z", "safeAsTypeParameterSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "getSafeAsTypeParameterSymbol", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeParameterSymbol;", "reportTypeArguments", "argument", "fullyExpandedType", "getClassSource", "Lorg/jetbrains/kotlin/KtSourceElement;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/KtSourceElement;)V", "reportWrongNumberOfTypeArguments", "qualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "deprecationCase", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;Z)Z", "isTypeAliasToNonGeneric", "checkerContext", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)Z", "isAllowedGenericArrayTypeInClassLiteral", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "isAllowedTypeArgumentInClassLiteral", "checkUpperBoundViolationsInTypeAlias", "symbol", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirClassLiteralChecker extends FirExpressionChecker<FirGetClassCall> {
    public static final FirClassLiteralChecker INSTANCE = new FirClassLiteralChecker();

    private FirClassLiteralChecker() {
        super(MppCheckerKind.Common);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final void checkUpperBoundViolationsInTypeAlias(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedQualifier firResolvedQualifier, FirClassLikeSymbol<?> firClassLikeSymbol, ConeKotlinType coneKotlinType) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirClassifierSymbol<?> symbol;
        List<FirTypeParameterSymbol> typeParameterSymbols;
        if (!(firClassLikeSymbol instanceof FirTypeAliasSymbol) || (symbol = ToSymbolUtilsKt.toSymbol(checkerContext, coneKotlinType)) == null || (typeParameterSymbols = FirHelpersKt.getTypeParameterSymbols(symbol)) == null) {
            return;
        }
        List list = ArraysKt.toList(coneKotlinType.getTypeArguments());
        FirUpperBoundViolatedHelpersKt.checkUpperBoundViolatedInLhsOfGetClass(checkerContext, diagnosticReporter, typeParameterSymbols, list, new FE10LikeConeSubstitutor(typeParameterSymbols, list, checkerContext.getSession()), firResolvedQualifier.getSource());
    }

    private final boolean getAreUselessTypeArgumentsForbidden(CheckerContext checkerContext) {
        return LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ForbidUselessTypeArgumentsIn25);
    }

    private final boolean getCanBeDoubleColonLHSAsType(FirExpression firExpression) {
        return (firExpression instanceof FirResolvedQualifier) || (firExpression instanceof FirResolvedReifiedParameterReference) || getSafeAsTypeParameterSymbol(firExpression) != null;
    }

    private final FirTypeParameterSymbol getSafeAsTypeParameterSymbol(FirExpression firExpression) {
        FirReference calleeReference;
        FirQualifiedAccessExpression firQualifiedAccessExpression = firExpression instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) firExpression : null;
        if (firQualifiedAccessExpression == null || (calleeReference = firQualifiedAccessExpression.getCalleeReference()) == null) {
            return null;
        }
        return FirReferenceUtilsKt.toResolvedTypeParameterSymbol$default(calleeReference, false, 1, null);
    }

    private final boolean isAllowedGenericArrayTypeInClassLiteral(CheckerContext checkerContext, ConeKotlinType coneKotlinType) {
        boolean zIsAllowedTypeArgumentInClassLiteral;
        if (!(coneKotlinType instanceof ConeClassLikeType) || !ConeBuiltinTypeUtilsKt.isNonPrimitiveArray(coneKotlinType) || !isGenericArrayAllowed(checkerContext)) {
            return false;
        }
        for (ConeKotlinTypeProjection coneKotlinTypeProjection : coneKotlinType.getTypeArguments()) {
            if (coneKotlinTypeProjection instanceof ConeStarProjection) {
                zIsAllowedTypeArgumentInClassLiteral = false;
            } else {
                if (!(coneKotlinTypeProjection instanceof ConeKotlinTypeProjection)) {
                    bu8.a();
                    return false;
                }
                zIsAllowedTypeArgumentInClassLiteral = INSTANCE.isAllowedTypeArgumentInClassLiteral(checkerContext, coneKotlinTypeProjection.getType());
            }
            if (!zIsAllowedTypeArgumentInClassLiteral) {
                return false;
            }
        }
        return true;
    }

    private final boolean isAllowedTypeArgumentInClassLiteral(CheckerContext checkerContext, ConeKotlinType coneKotlinType) {
        if ((coneKotlinType instanceof ConeClassLikeType) && coneKotlinType.getTypeArguments().length == 0) {
            return true;
        }
        return ((coneKotlinType instanceof ConeTypeParameterType) && ((ConeTypeParameterType) coneKotlinType).getLookupTag().getTypeParameterSymbol().isReified()) || isAllowedGenericArrayTypeInClassLiteral(checkerContext, coneKotlinType);
    }

    private final boolean isGenericArrayAllowed(CheckerContext checkerContext) {
        return FirClassLiteralCheckerKt.getFirGenericArrayClassLiteralSupport(checkerContext.getSession()).isEnabled();
    }

    private final boolean isNullableTypeParameter(CheckerContext checkerContext, ConeKotlinType coneKotlinType, boolean z) {
        if (!(coneKotlinType instanceof ConeTypeParameterType)) {
            return false;
        }
        FirTypeParameterSymbol typeParameterSymbol = ((ConeTypeParameterType) coneKotlinType).getLookupTag().getTypeParameterSymbol();
        if (TypeUtilsKt.canBeNull$default(coneKotlinType, checkerContext.getSession(), false, null, 6, null)) {
            if (!typeParameterSymbol.isReified()) {
                return true;
            }
            if (z && LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ForbidClassLiteralWithPotentiallyNullableReifiedLhs)) {
                return true;
            }
        }
        return false;
    }

    private final boolean isTypeAliasToNonGeneric(CheckerContext checkerContext, FirClassLikeSymbol<?> firClassLikeSymbol) {
        return (firClassLikeSymbol instanceof FirTypeAliasSymbol) && TypeExpansionUtilsKt.fullyExpandedType(checkerContext, ((FirTypeAliasSymbol) firClassLikeSymbol).getResolvedExpandedTypeRef().getConeType()).getTypeArguments().length == 0;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final void reportTypeArguments(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirExpression firExpression, ConeKotlinType coneKotlinType, KtSourceElement ktSourceElement) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirResolvedQualifier firResolvedQualifier;
        FirClassLikeSymbol<?> symbol;
        if (firExpression instanceof FirResolvedReifiedParameterReference) {
            IElementType iElementType = KtNodeTypes.TYPE_ARGUMENT_LIST;
            iElementType.getClass();
            KtSourceElement child$default = FirSourceUtilsKt.getChild$default(ktSourceElement, iElementType, 0, 0, false, 14, (Object) null);
            if (child$default == null) {
                return;
            }
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) child$default, (KtDiagnosticFactory1) (!getAreUselessTypeArgumentsForbidden(checkerContext) ? FirErrors.INSTANCE.getTYPE_ARGUMENTS_NOT_ALLOWED_WARNING() : FirErrors.INSTANCE.getTYPE_ARGUMENTS_NOT_ALLOWED()), (Object) "for type parameters", (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
            return;
        }
        if (!(firExpression instanceof FirResolvedQualifier) || (symbol = (firResolvedQualifier = (FirResolvedQualifier) firExpression).getSymbol()) == null) {
            return;
        }
        if (!firResolvedQualifier.getTypeArguments().isEmpty()) {
            boolean zIsAllowedGenericArrayTypeInClassLiteral = isAllowedGenericArrayTypeInClassLiteral(checkerContext, coneKotlinType);
            boolean z = (symbol instanceof FirTypeAliasSymbol) && zIsAllowedGenericArrayTypeInClassLiteral;
            boolean z2 = isTypeAliasToNonGeneric(checkerContext, symbol) || z;
            if (!reportWrongNumberOfTypeArguments(checkerContext, diagnosticReporter, firResolvedQualifier, z2) && (!zIsAllowedGenericArrayTypeInClassLiteral || z)) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) ktSourceElement, (!z2 || getAreUselessTypeArgumentsForbidden(checkerContext)) ? FirErrors.INSTANCE.getCLASS_LITERAL_LHS_NOT_A_CLASS() : FirErrors.INSTANCE.getCLASS_LITERAL_LHS_NOT_A_CLASS_WARNING(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                return;
            }
        }
        checkUpperBoundViolationsInTypeAlias(checkerContext, diagnosticReporter, firResolvedQualifier, symbol, coneKotlinType);
    }

    private final boolean reportWrongNumberOfTypeArguments(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirResolvedQualifier firResolvedQualifier, boolean z) {
        FirClassLikeSymbol<?> symbol;
        while (firResolvedQualifier != null && (symbol = firResolvedQualifier.getSymbol()) != null) {
            if (symbol.getOwnTypeParameterSymbols().size() != UtilsKt.getOwnTypeArguments(firResolvedQualifier).size()) {
                KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firResolvedQualifier.getSource(), (KtDiagnosticFactory2<Integer, FirClassLikeSymbol<?>>) ((!z || getAreUselessTypeArgumentsForbidden(checkerContext)) ? FirErrors.INSTANCE.getWRONG_NUMBER_OF_TYPE_ARGUMENTS() : FirErrors.INSTANCE.getWRONG_NUMBER_OF_TYPE_ARGUMENTS_IN_GET_CLASS_WARNING()), Integer.valueOf(symbol.getOwnTypeParameterSymbols().size()), symbol, (AbstractSourceElementPositioningStrategy) SourceElementPositioningStrategies.INSTANCE.getTYPE_ARGUMENT_LIST_OR_WITHOUT_RECEIVER());
                return true;
            }
            CheckerContext checkerContext2 = checkerContext;
            DiagnosticReporter diagnosticReporter2 = diagnosticReporter;
            if (!symbol.getRawStatus().isInner()) {
                return false;
            }
            firResolvedQualifier = firResolvedQualifier.getExplicitParent();
            checkerContext = checkerContext2;
            diagnosticReporter = diagnosticReporter2;
        }
        return false;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:15:0x0041  */
    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirGetClassCall firGetClassCall) throws KotlinIllegalArgumentExceptionWithAttachments {
        CheckerContext checkerContext2;
        FirTypeParameterSymbol typeParameterSymbol;
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firGetClassCall.getClass();
        KtSourceElement source = firGetClassCall.getSource();
        if (source == null || (source.getKind() instanceof KtFakeSourceElementKind)) {
            return;
        }
        FirExpression argument = firGetClassCall.getArgument();
        boolean z = argument instanceof FirResolvedQualifier;
        if (z) {
            FirResolvedQualifier firResolvedQualifier = (FirResolvedQualifier) argument;
            ClassId classId = firResolvedQualifier.getClassId();
            OptInNames optInNames = OptInNames.INSTANCE;
            if (Intrinsics.areEqual(classId, optInNames.getREQUIRES_OPT_IN_CLASS_ID()) || Intrinsics.areEqual(classId, optInNames.getOPT_IN_CLASS_ID())) {
                KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firResolvedQualifier.getSource(), FirErrors.INSTANCE.getOPT_IN_CAN_ONLY_BE_USED_AS_ANNOTATION(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
                checkerContext2 = checkerContext;
            } else {
                checkerContext2 = checkerContext;
            }
        } else {
            checkerContext2 = checkerContext;
        }
        KtSingleValueToken ktSingleValueToken = KtTokens.QUEST;
        ktSingleValueToken.getClass();
        boolean z2 = FirSourceUtilsKt.getChild$default(source, (IElementType) ktSingleValueToken, 0, 1, false, 10, (Object) null) != null;
        ConeKotlinType coneKotlinTypeFullyExpandedType = TypeExpansionUtilsKt.fullyExpandedType(checkerContext2, FirTypeUtilsKt.getResolvedType(argument));
        if (!z2) {
            FirResolvedQualifier firResolvedQualifier2 = z ? (FirResolvedQualifier) argument : null;
            if ((firResolvedQualifier2 == null || !firResolvedQualifier2.getIsNullableLHSForCallableReference()) && !ConeTypeUtilsKt.isMarkedNullable(coneKotlinTypeFullyExpandedType) && !isNullableTypeParameter(checkerContext2, coneKotlinTypeFullyExpandedType, !getCanBeDoubleColonLHSAsType(argument))) {
                if (!getCanBeDoubleColonLHSAsType(argument) && LanguageVersionUtilsKt.isDisabled(checkerContext2, LanguageFeature.ForbidClassLiteralWithPotentiallyNullableReifiedLhs) && (typeParameterSymbol = ToSymbolUtilsKt.toTypeParameterSymbol(checkerContext2, coneKotlinTypeFullyExpandedType)) != null && typeParameterSymbol.isReified() && !ConeTypeUtilsKt.isMarkedNullable(coneKotlinTypeFullyExpandedType) && TypeUtilsKt.canBeNull$default(coneKotlinTypeFullyExpandedType, checkerContext2.getSession(), false, null, 6, null)) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext2, diagnosticReporter, (AbstractKtSourceElement) argument.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getEXPRESSION_OF_NULLABLE_TYPE_IN_CLASS_LITERAL_LHS_WARNING(), (Object) FirTypeUtilsKt.getResolvedType(argument), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
                FirTypeParameterSymbol safeAsTypeParameterSymbol = getSafeAsTypeParameterSymbol(argument);
                if (safeAsTypeParameterSymbol != null && !safeAsTypeParameterSymbol.isReified()) {
                    KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, (KtDiagnosticFactory1) FirErrors.INSTANCE.getTYPE_PARAMETER_AS_REIFIED(), (Object) safeAsTypeParameterSymbol, (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
                }
                reportTypeArguments(checkerContext, diagnosticReporter, argument, coneKotlinTypeFullyExpandedType, source);
                return;
            }
        }
        if (getCanBeDoubleColonLHSAsType(argument)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) source, FirErrors.INSTANCE.getNULLABLE_TYPE_IN_CLASS_LITERAL_LHS(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        } else {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) argument.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getEXPRESSION_OF_NULLABLE_TYPE_IN_CLASS_LITERAL_LHS(), (Object) FirTypeUtilsKt.getResolvedType(argument), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        }
    }
}
