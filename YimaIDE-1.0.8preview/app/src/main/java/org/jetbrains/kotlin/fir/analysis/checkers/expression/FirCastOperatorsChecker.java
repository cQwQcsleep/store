package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnostic;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactoryForDeprecation1;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticWithSource;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.PrimitivesKt;
import org.jetbrains.kotlin.fir.analysis.checkers.ArgumentInfo;
import org.jetbrains.kotlin.fir.analysis.checkers.FirCastDiagnosticsHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformSpecificCastCheckerKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirTypeCompatibilityHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.TypeInfo;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.ConeDiagnosticToFirDiagnosticKt;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.ExhaustivenessStatusKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirWhenBranch;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000`\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u00016B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ/\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0013J1\u0010\u0014\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0015J1\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0015J)\u0010\u0017\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0011H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0018JA\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00112\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010\u001a\u001a\u00020\u000f2\u0006\u0010\u001b\u001a\u00020\u000fH\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u001cJ$\u0010\u001d\u001a\u00020\u0007*\u00020\u000f2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\u001fH\u0086\bø\u0001\u0000J\u001b\u0010 \u001a\u00020\u000f*\u00020\u000f2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u000f0\"H\u0082\bJO\u0010#\u001a\u00020\u0007*\u00020\n2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020%2\u0006\u0010\u0012\u001a\u00020&2\u0006\u0010'\u001a\u00020&2\b\b\u0002\u0010(\u001a\u00020)H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010*J!\u0010+\u001a\u00020)2\u0006\u0010\u0010\u001a\u00020%H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010,J;\u0010-\u001a\u0004\u0018\u00010.2\u0006\u0010\u0010\u001a\u00020%2\u0006\u0010/\u001a\u00020&2\u0006\u00100\u001a\u00020)2\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u00101J3\u00102\u001a\u0004\u0018\u00010.2\u0006\u0010\u0010\u001a\u00020%2\u0006\u0010/\u001a\u00020&2\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u00103J3\u00104\u001a\u0004\u0018\u00010.2\u0006\u0010(\u001a\u00020)2\u0006\u00100\u001a\u00020)2\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u00105\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00067"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirTypeOperatorCallChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;)V", "checkGeneralApplicability", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker$Applicability;", "l", "Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;", "r", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;)Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker$Applicability;", "checkIsApplicability", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;)Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker$Applicability;", "checkAsApplicability", "checkCastErased", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;)Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker$Applicability;", "checkAnyApplicability", "impossible", "useless", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker$Applicability;Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker$Applicability;)Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker$Applicability;", "ifInapplicable", "block", "Lkotlin/Function1;", "orIfApplicable", "other", "Lkotlin/Function0;", "reportInapplicabilityDiagnostic", "applicability", "Lorg/jetbrains/kotlin/fir/analysis/checkers/ArgumentInfo;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "rUserType", "forceWarning", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker$Applicability;Lorg/jetbrains/kotlin/fir/analysis/checkers/ArgumentInfo;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Z)V", "isLastBranchOfExhaustiveWhen", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/analysis/checkers/ArgumentInfo;)Z", "getImpossibilityDiagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithSource;", "rType", "areBothTypesNullable", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/analysis/checkers/ArgumentInfo;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;ZLorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;)Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithSource;", "reportUselessCastDiagnosticIfNeeded", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/analysis/checkers/ArgumentInfo;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;)Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithSource;", "getImpossibleIsCheckDiagnostic", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;ZZLorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;)Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticWithSource;", "Applicability", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCastOperatorsChecker extends FirExpressionChecker<FirTypeOperatorCall> {
    public static final FirCastOperatorsChecker INSTANCE = new FirCastOperatorsChecker();

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\t\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\t¨\u0006\n"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCastOperatorsChecker$Applicability;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "APPLICABLE", "IMPOSSIBLE_CAST", "IMPOSSIBLE_IS_CHECK", "USELESS_CAST", "USELESS_IS_CHECK", "CAST_ERASED", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum Applicability {
        APPLICABLE,
        IMPOSSIBLE_CAST,
        IMPOSSIBLE_IS_CHECK,
        USELESS_CAST,
        USELESS_IS_CHECK,
        CAST_ERASED;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<Applicability> getEntries() {
            return $ENTRIES;
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 176)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[Applicability.values().length];
            try {
                iArr[Applicability.APPLICABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[Applicability.CAST_ERASED.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[Applicability.USELESS_CAST.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[Applicability.IMPOSSIBLE_CAST.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                iArr[Applicability.IMPOSSIBLE_IS_CHECK.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr[Applicability.USELESS_IS_CHECK.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[FirOperation.values().length];
            try {
                iArr2[FirOperation.IS.ordinal()] = 1;
            } catch (NoSuchFieldError unused7) {
            }
            try {
                iArr2[FirOperation.NOT_IS.ordinal()] = 2;
            } catch (NoSuchFieldError unused8) {
            }
            try {
                iArr2[FirOperation.AS.ordinal()] = 3;
            } catch (NoSuchFieldError unused9) {
            }
            try {
                iArr2[FirOperation.SAFE_AS.ordinal()] = 4;
            } catch (NoSuchFieldError unused10) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private FirCastOperatorsChecker() {
        super(MppCheckerKind.Common);
    }

    private final Applicability checkAnyApplicability(CheckerContext checkerContext, TypeInfo typeInfo, TypeInfo typeInfo2, FirTypeOperatorCall firTypeOperatorCall, Applicability applicability, Applicability applicability2) {
        if (FirCastDiagnosticsHelpersKt.isRefinementUseless(checkerContext, ConeTypeUtilsKt.upperBoundIfFlexible(typeInfo.getDirectType()), typeInfo2.getDirectType(), firTypeOperatorCall)) {
            return applicability2;
        }
        return FirTypeCompatibilityHelpersKt.shouldReportAsPerRules1(checkerContext, typeInfo, typeInfo2) ? applicability : Applicability.APPLICABLE;
    }

    private final Applicability checkAsApplicability(CheckerContext checkerContext, TypeInfo typeInfo, TypeInfo typeInfo2, FirTypeOperatorCall firTypeOperatorCall) {
        boolean z = (!ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(typeInfo.getType()) && ConeBuiltinTypeUtilsKt.isNullableNothing(typeInfo2.getType())) || (ConeBuiltinTypeUtilsKt.isNullableNothing(typeInfo.getType()) && !ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(typeInfo2.getType()));
        if (ConeBuiltinTypeUtilsKt.isNothing(typeInfo.getType())) {
            return Applicability.APPLICABLE;
        }
        if (ConeBuiltinTypeUtilsKt.isNothing(typeInfo2.getType())) {
            return Applicability.IMPOSSIBLE_CAST;
        }
        if (z) {
            return WhenMappings.$EnumSwitchMapping$1[firTypeOperatorCall.getOperation().ordinal()] == 4 ? Applicability.USELESS_CAST : Applicability.IMPOSSIBLE_CAST;
        }
        Applicability applicabilityCheckAnyApplicability = checkAnyApplicability(checkerContext, typeInfo, typeInfo2, firTypeOperatorCall, Applicability.IMPOSSIBLE_CAST, Applicability.USELESS_CAST);
        return applicabilityCheckAnyApplicability == Applicability.APPLICABLE ? INSTANCE.checkCastErased(checkerContext, typeInfo, typeInfo2) : applicabilityCheckAnyApplicability;
    }

    private final Applicability checkCastErased(CheckerContext checkerContext, TypeInfo typeInfo, TypeInfo typeInfo2) {
        return (!(checkerContext.getIsContractBody() && LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.AllowCheckForErasedTypesInContracts)) && FirCastDiagnosticsHelpersKt.isCastErased(checkerContext, typeInfo.getDirectType(), typeInfo2.getDirectType())) ? Applicability.CAST_ERASED : Applicability.APPLICABLE;
    }

    private final Applicability checkIsApplicability(CheckerContext checkerContext, TypeInfo typeInfo, TypeInfo typeInfo2, FirTypeOperatorCall firTypeOperatorCall) {
        Applicability applicabilityCheckCastErased = checkCastErased(checkerContext, typeInfo, typeInfo2);
        return applicabilityCheckCastErased == Applicability.APPLICABLE ? INSTANCE.checkAnyApplicability(checkerContext, typeInfo, typeInfo2, firTypeOperatorCall, Applicability.IMPOSSIBLE_IS_CHECK, Applicability.USELESS_IS_CHECK) : applicabilityCheckCastErased;
    }

    private final KtDiagnosticWithSource getImpossibilityDiagnostic(CheckerContext checkerContext, ArgumentInfo argumentInfo, ConeKotlinType coneKotlinType, boolean z, FirTypeOperatorCall firTypeOperatorCall) {
        if (LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.EnableDfaWarningsInK2)) {
            return null;
        }
        if (z) {
            return WhenMappings.$EnumSwitchMapping$1[firTypeOperatorCall.getOperation().ordinal()] == 4 ? ConeDiagnosticToFirDiagnosticKt.createOn$default(FirErrors.INSTANCE.getSAFE_CAST_RELYING_ON_NULL(), firTypeOperatorCall.getSource(), checkerContext.getSession(), null, 4, null) : ConeDiagnosticToFirDiagnosticKt.createOn$default(FirErrors.INSTANCE.getUNSAFE_CAST_RELYING_ON_NULL(), firTypeOperatorCall.getSource(), checkerContext.getSession(), null, 4, null);
        }
        ConeKotlinType userType = argumentInfo.getUserType();
        ConeClassLikeType coneClassLikeType = userType instanceof ConeClassLikeType ? (ConeClassLikeType) userType : null;
        if (coneClassLikeType != null && PrimitivesKt.isPrimitiveNumberOrUnsignedNumberType(coneClassLikeType)) {
            ConeClassLikeType coneClassLikeType2 = coneKotlinType instanceof ConeClassLikeType ? (ConeClassLikeType) coneKotlinType : null;
            if (coneClassLikeType2 != null && PrimitivesKt.isPrimitiveNumberOrUnsignedNumberType(coneClassLikeType2)) {
                return ConeDiagnosticToFirDiagnosticKt.createOn$default(FirErrors.INSTANCE.getNUMERIC_CAST_NEVER_SUCCEEDS_BUT_CAN_BE_REPLACED_WITH_TO_CALL(), firTypeOperatorCall.getSource(), coneKotlinType, checkerContext.getSession(), null, 8, null);
            }
        }
        return ConeDiagnosticToFirDiagnosticKt.createOn$default(FirErrors.INSTANCE.getCAST_NEVER_SUCCEEDS(), firTypeOperatorCall.getSource(), checkerContext.getSession(), null, 4, null);
    }

    private final KtDiagnosticWithSource getImpossibleIsCheckDiagnostic(CheckerContext checkerContext, boolean z, boolean z2, FirTypeOperatorCall firTypeOperatorCall) {
        boolean z3 = firTypeOperatorCall.getOperation() != FirOperation.IS;
        Pair pair = z2 ? TuplesKt.to(FirErrors.INSTANCE.getIMPOSSIBLE_IS_CHECK_RELYING_ON_NULL(), Boolean.valueOf(!z3)) : TuplesKt.to(FirErrors.INSTANCE.getIMPOSSIBLE_IS_CHECK(), Boolean.valueOf(z3));
        KtDiagnosticFactoryForDeprecation1 ktDiagnosticFactoryForDeprecation1 = (KtDiagnosticFactoryForDeprecation1) pair.component1();
        Boolean bool = (Boolean) pair.component2();
        bool.booleanValue();
        return ConeDiagnosticToFirDiagnosticKt.createOn$default(z ? ktDiagnosticFactoryForDeprecation1.getWarningFactory() : (KtDiagnosticFactory1) KtDiagnosticReportHelpersKt.chooseFactory(checkerContext, ktDiagnosticFactoryForDeprecation1), firTypeOperatorCall.getSource(), bool, checkerContext.getSession(), null, 8, null);
    }

    private final boolean isLastBranchOfExhaustiveWhen(CheckerContext checkerContext, ArgumentInfo argumentInfo) {
        if (checkerContext.getContainingElements().size() < 2) {
            return false;
        }
        List listTakeLast = CollectionsKt.takeLast(CollectionsKt.dropLast(checkerContext.getContainingElements(), 1), 2);
        FirElement firElement = (FirElement) listTakeLast.get(0);
        FirElement firElement2 = (FirElement) listTakeLast.get(1);
        if ((firElement instanceof FirWhenExpression) && (firElement2 instanceof FirWhenBranch)) {
            FirWhenExpression firWhenExpression = (FirWhenExpression) firElement;
            if (ExhaustivenessStatusKt.isExhaustive(firWhenExpression) && Intrinsics.areEqual(firElement2, CollectionsKt.lastOrNull(firWhenExpression.getBranches())) && !ConeBuiltinTypeUtilsKt.isNothing(FirTypeUtilsKt.getResolvedType(argumentInfo.getArgument())) && firWhenExpression.getBranches().size() > 1) {
                return true;
            }
        }
        return false;
    }

    private final void reportInapplicabilityDiagnostic(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeOperatorCall firTypeOperatorCall, Applicability applicability, ArgumentInfo argumentInfo, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, boolean z) {
        KtDiagnostic ktDiagnosticCreateOn$default;
        boolean z2 = TypeUtilsKt.canBeNull$default(argumentInfo.getSmartCastType(), checkerContext.getSession(), false, null, 6, null) && TypeUtilsKt.canBeNull$default(coneKotlinType, checkerContext.getSession(), false, null, 6, null);
        int i = WhenMappings.$EnumSwitchMapping$0[applicability.ordinal()];
        if (i == 2) {
            ktDiagnosticCreateOn$default = (firTypeOperatorCall.getOperation() == FirOperation.AS || firTypeOperatorCall.getOperation() == FirOperation.SAFE_AS) ? ConeDiagnosticToFirDiagnosticKt.createOn$default(FirErrors.INSTANCE.getUNCHECKED_CAST(), firTypeOperatorCall.getSource(), argumentInfo.getUserType(), coneKotlinType2, checkerContext.getSession(), null, 16, null) : ConeDiagnosticToFirDiagnosticKt.createOn$default(FirErrors.INSTANCE.getCANNOT_CHECK_FOR_ERASED(), firTypeOperatorCall.getConversionTypeRef().getSource(), coneKotlinType2, checkerContext.getSession(), null, 8, null);
        } else if (i == 3) {
            ktDiagnosticCreateOn$default = reportUselessCastDiagnosticIfNeeded(checkerContext, argumentInfo, coneKotlinType, firTypeOperatorCall);
        } else if (i == 4) {
            ktDiagnosticCreateOn$default = getImpossibilityDiagnostic(checkerContext, argumentInfo, coneKotlinType, z2, firTypeOperatorCall);
        } else if (i == 5) {
            ktDiagnosticCreateOn$default = getImpossibleIsCheckDiagnostic(checkerContext, z, z2, firTypeOperatorCall);
        } else if (i != 6) {
            k2d.a("Shouldn't be here");
            return;
        } else if (isLastBranchOfExhaustiveWhen(checkerContext, argumentInfo)) {
            ktDiagnosticCreateOn$default = null;
        } else {
            ktDiagnosticCreateOn$default = ConeDiagnosticToFirDiagnosticKt.createOn$default(FirErrors.INSTANCE.getUSELESS_IS_CHECK(), firTypeOperatorCall.getSource(), Boolean.valueOf(firTypeOperatorCall.getOperation() == FirOperation.IS), checkerContext.getSession(), null, 8, null);
        }
        diagnosticReporter.report(ktDiagnosticCreateOn$default, checkerContext);
    }

    public static /* synthetic */ void reportInapplicabilityDiagnostic$default(FirCastOperatorsChecker firCastOperatorsChecker, CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeOperatorCall firTypeOperatorCall, Applicability applicability, ArgumentInfo argumentInfo, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, boolean z, int i, Object obj) {
        firCastOperatorsChecker.reportInapplicabilityDiagnostic(checkerContext, diagnosticReporter, firTypeOperatorCall, applicability, argumentInfo, coneKotlinType, coneKotlinType2, (i & 64) != 0 ? false : z);
    }

    private final KtDiagnosticWithSource reportUselessCastDiagnosticIfNeeded(CheckerContext checkerContext, ArgumentInfo argumentInfo, ConeKotlinType coneKotlinType, FirTypeOperatorCall firTypeOperatorCall) {
        if (LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.EnableDfaWarningsInK2)) {
            return null;
        }
        return FirHelpersKt.hasIntegerLiteralTypeAmbiguity(argumentInfo.getArgument()) ? ConeDiagnosticToFirDiagnosticKt.createOn$default(FirErrors.INSTANCE.getINTEGER_LITERAL_CAST_INSTEAD_OF_TO_CALL(), firTypeOperatorCall.getSource(), coneKotlinType, checkerContext.getSession(), null, 8, null) : ConeDiagnosticToFirDiagnosticKt.createOn$default(FirErrors.INSTANCE.getUSELESS_CAST(), firTypeOperatorCall.getSource(), checkerContext.getSession(), null, 4, null);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirTypeOperatorCall firTypeOperatorCall) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firTypeOperatorCall.getClass();
        List<FirExpression> arguments = firTypeOperatorCall.getArgumentList().getArguments();
        if (arguments.size() != 1) {
            w01.a("Type operator call with non-1 arguments");
            return;
        }
        ArgumentInfo argumentInfo = FirTypeCompatibilityHelpersKt.toArgumentInfo(checkerContext, arguments.get(0));
        ConeKotlinType coneKotlinTypeFinalApproximationOrSelf = FirHelpersKt.finalApproximationOrSelf(checkerContext, TypeExpansionUtilsKt.fullyExpandedType(checkerContext, FirTypeUtilsKt.getConeType(firTypeOperatorCall.getConversionTypeRef())));
        if (FirOperation.INSTANCE.getTYPES().contains(firTypeOperatorCall.getOperation()) && (coneKotlinTypeFinalApproximationOrSelf instanceof ConeDynamicType)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firTypeOperatorCall.getConversionTypeRef().getSource(), FirErrors.INSTANCE.getDYNAMIC_NOT_ALLOWED(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
        ConeKotlinType coneKotlinTypeFinalApproximationOrSelf2 = FirHelpersKt.finalApproximationOrSelf(checkerContext, FirTypeUtilsKt.getConeType(firTypeOperatorCall.getConversionTypeRef()));
        Applicability applicabilityRunApplicabilityCheck = FirPlatformSpecificCastCheckerKt.getFirPlatformSpecificCastChecker(checkerContext.getSession()).runApplicabilityCheck(checkerContext, firTypeOperatorCall, argumentInfo.getOriginalType(), coneKotlinTypeFinalApproximationOrSelf, this);
        int[] iArr = WhenMappings.$EnumSwitchMapping$0;
        int i = iArr[applicabilityRunApplicabilityCheck.ordinal()];
        if (i != 1 && ((i != 2 || !(argumentInfo.getArgument() instanceof FirSmartCastExpression)) && (i != 3 || !(argumentInfo.getArgument() instanceof FirSmartCastExpression)))) {
            reportInapplicabilityDiagnostic$default(this, checkerContext, diagnosticReporter, firTypeOperatorCall, applicabilityRunApplicabilityCheck, argumentInfo, coneKotlinTypeFinalApproximationOrSelf, coneKotlinTypeFinalApproximationOrSelf2, false, 64, null);
        } else if (argumentInfo.getArgument() instanceof FirSmartCastExpression) {
            Applicability applicabilityRunApplicabilityCheck2 = FirPlatformSpecificCastCheckerKt.getFirPlatformSpecificCastChecker(checkerContext.getSession()).runApplicabilityCheck(checkerContext, firTypeOperatorCall, argumentInfo.getSmartCastType(), coneKotlinTypeFinalApproximationOrSelf, this);
            if (iArr[applicabilityRunApplicabilityCheck2.ordinal()] != 1) {
                INSTANCE.reportInapplicabilityDiagnostic(checkerContext, diagnosticReporter, firTypeOperatorCall, applicabilityRunApplicabilityCheck2, argumentInfo, coneKotlinTypeFinalApproximationOrSelf, coneKotlinTypeFinalApproximationOrSelf2, true);
            }
        }
    }

    public final Applicability checkGeneralApplicability(CheckerContext checkerContext, FirTypeOperatorCall firTypeOperatorCall, TypeInfo typeInfo, TypeInfo typeInfo2) {
        checkerContext.getClass();
        firTypeOperatorCall.getClass();
        typeInfo.getClass();
        typeInfo2.getClass();
        int i = WhenMappings.$EnumSwitchMapping$1[firTypeOperatorCall.getOperation().ordinal()];
        if (i == 1 || i == 2) {
            return checkIsApplicability(checkerContext, typeInfo, typeInfo2, firTypeOperatorCall);
        }
        if (i == 3 || i == 4) {
            return checkAsApplicability(checkerContext, typeInfo, typeInfo2, firTypeOperatorCall);
        }
        k2d.a("Invalid operator of FirTypeOperatorCall");
        return null;
    }

    public final void ifInapplicable(Applicability applicability, Function1<? super Applicability, Unit> function1) {
        applicability.getClass();
        function1.getClass();
        if (WhenMappings.$EnumSwitchMapping$0[applicability.ordinal()] != 1) {
            function1.invoke(applicability);
        }
    }
}
