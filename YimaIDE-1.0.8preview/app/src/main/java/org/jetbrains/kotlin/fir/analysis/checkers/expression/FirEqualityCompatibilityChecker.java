package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
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
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.ArgumentInfo;
import org.jetbrains.kotlin.fir.analysis.checkers.FirPlatformSpecificEqualityCheckerKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirTypeCompatibilityHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.TypeInfo;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.FirErrors;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000e\bÆ\u0002\u0018\u00002\f\u0012\u0004\u0012\u00020\u00020\u0001j\u0002`\u0003:\u00015B\t\b\u0002¢\u0006\u0004\b\u0004\u0010\u0005J-\u0010\u0006\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u0002H\u0016R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u0010\rJ/\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0015J)\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0017J)\u0010\u0018\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010\u0017J\u0018\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013H\u0002J$\u0010\u001a\u001a\u00020\u0007*\u00020\u000f2\u0012\u0010\u001b\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00070\u001cH\u0086\bø\u0001\u0000J\"\u0010\u001d\u001a\u0014\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020 0\u001e2\u0006\u0010!\u001a\u00020\"H\u0002J=\u0010#\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020 0$2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010!\u001a\u00020\"H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010%J \u0010&\u001a\u00020\"2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010'\u001a\u00020(H\u0002J \u0010)\u001a\u00020\"2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010'\u001a\u00020(H\u0002J\u001c\u0010*\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020 0$2\u0006\u0010!\u001a\u00020\"H\u0002J=\u0010+\u001a\u000e\u0012\u0004\u0012\u00020 \u0012\u0004\u0012\u00020 0$2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010!\u001a\u00020\"H\u0002R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u0010%J[\u0010,\u001a\u00020\u0007*\u00020\n2\u0006\u0010\f\u001a\u00020\u00022\u0006\u0010-\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\u0006\u0010.\u001a\u00020 2\u0006\u0010/\u001a\u00020 R\u00020\bj\u0006\u0010\t\u001a\u00020\b¢\u0006\u0002\u00100J=\u00101\u001a\u00020\u00072\u0006\u00102\u001a\u00020 2\u0006\u00103\u001a\u00020 2\u0006\u0010\f\u001a\u00020\u0002H\u0002R\u00020\bR\u00020\nj\u0006\u0010\t\u001a\u00020\bj\u0006\u0010\u000b\u001a\u00020\n¢\u0006\u0002\u00104\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00066"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirEqualityCompatibilityChecker;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirEqualityOperatorCallChecker;", "<init>", "()V", "check", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;)V", "checkApplicability", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirEqualityCompatibilityChecker$Applicability;", "operation", "Lorg/jetbrains/kotlin/fir/expressions/FirOperation;", "l", "Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;", "r", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirOperation;Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;)Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirEqualityCompatibilityChecker$Applicability;", "checkEqualityApplicability", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;)Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirEqualityCompatibilityChecker$Applicability;", "checkIdentityApplicability", "getInapplicabilityFor", "ifInapplicable", "block", "Lkotlin/Function1;", "getGeneralInapplicabilityDiagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "forceWarning", Argument.Delimiters.none, "getIdentityLessInapplicabilityDiagnostic", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;Z)Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory2;", "isIdentityComparedWithImplicitBoxing", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "arePrimitiveAndNonPrimitiveSupertypeRespectively", "getSourceLessInapplicabilityDiagnostic", "getEnumInapplicabilityDiagnostic", "reportInapplicabilityDiagnostic", "applicability", "lUserType", "rUserType", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirEqualityCompatibilityChecker$Applicability;Lorg/jetbrains/kotlin/fir/expressions/FirOperation;ZLorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;Lorg/jetbrains/kotlin/fir/analysis/checkers/TypeInfo;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)V", "checkSenselessness", "lType", "rType", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;)V", "Applicability", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirEqualityCompatibilityChecker extends FirExpressionChecker<FirEqualityOperatorCall> {
    public static final FirEqualityCompatibilityChecker INSTANCE = new FirEqualityCompatibilityChecker();

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0007\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirEqualityCompatibilityChecker$Applicability;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;I)V", "APPLICABLE", "GENERALLY_INAPPLICABLE", "INAPPLICABLE_AS_ENUMS", "INAPPLICABLE_AS_IDENTITY_LESS", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public enum Applicability {
        APPLICABLE,
        GENERALLY_INAPPLICABLE,
        INAPPLICABLE_AS_ENUMS,
        INAPPLICABLE_AS_IDENTITY_LESS;

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
            int[] iArr = new int[FirOperation.values().length];
            try {
                iArr[FirOperation.EQ.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FirOperation.NOT_EQ.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FirOperation.IDENTITY.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FirOperation.NOT_IDENTITY.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[Applicability.values().length];
            try {
                iArr2[Applicability.APPLICABLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    private FirEqualityCompatibilityChecker() {
        super(MppCheckerKind.Common);
    }

    private final boolean arePrimitiveAndNonPrimitiveSupertypeRespectively(TypeInfo l, TypeInfo r, FirSession session) {
        return FirTypeCompatibilityHelpersKt.isNotNullPrimitive(l) && !FirTypeCompatibilityHelpersKt.isNotNullPrimitive(r) && TypeUtilsKt.isSubtypeOf$default(l.getType(), r.getType(), session, false, 4, null);
    }

    private final Applicability checkEqualityApplicability(CheckerContext checkerContext, TypeInfo typeInfo, TypeInfo typeInfo2) {
        boolean z = true;
        boolean z2 = typeInfo.getIsBuiltin() || typeInfo2.getIsBuiltin();
        if (!FirTypeCompatibilityHelpersKt.isIdentityLess(typeInfo, checkerContext.getSession()) && !FirTypeCompatibilityHelpersKt.isIdentityLess(typeInfo2, checkerContext.getSession())) {
            z = false;
        }
        return ((z2 || z) && FirTypeCompatibilityHelpersKt.shouldReportAsPerRules1(checkerContext, typeInfo, typeInfo2)) ? getInapplicabilityFor(typeInfo, typeInfo2) : Applicability.APPLICABLE;
    }

    private final Applicability checkIdentityApplicability(CheckerContext checkerContext, TypeInfo typeInfo, TypeInfo typeInfo2) {
        boolean z = (ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(typeInfo.getType()) && ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(typeInfo2.getType())) ? false : true;
        if (ConeBuiltinTypeUtilsKt.isNullableNothing(typeInfo.getType()) || ConeBuiltinTypeUtilsKt.isNullableNothing(typeInfo2.getType())) {
            return Applicability.APPLICABLE;
        }
        if (FirTypeCompatibilityHelpersKt.isIdentityLess(typeInfo, checkerContext.getSession()) || FirTypeCompatibilityHelpersKt.isIdentityLess(typeInfo2, checkerContext.getSession())) {
            return Applicability.INAPPLICABLE_AS_IDENTITY_LESS;
        }
        return (z && FirTypeCompatibilityHelpersKt.shouldReportAsPerRules1(checkerContext, typeInfo, typeInfo2)) ? getInapplicabilityFor(typeInfo, typeInfo2) : Applicability.APPLICABLE;
    }

    private final void checkSenselessness(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2, FirEqualityOperatorCall firEqualityOperatorCall) {
        ConeKotlinType coneKotlinType3;
        if (ConeBuiltinTypeUtilsKt.isNullableNothing(coneKotlinType2)) {
            coneKotlinType3 = coneKotlinType;
        } else if (!ConeBuiltinTypeUtilsKt.isNullableNothing(coneKotlinType)) {
            return;
        } else {
            coneKotlinType3 = coneKotlinType2;
        }
        if (coneKotlinType3 instanceof ConeErrorType) {
            return;
        }
        boolean z = true;
        boolean z2 = firEqualityOperatorCall.getOperation() == FirOperation.EQ || firEqualityOperatorCall.getOperation() == FirOperation.IDENTITY;
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(checkerContext.getSession());
        if (ConeBuiltinTypeUtilsKt.isNullableNothing(coneKotlinType3)) {
            z = z2;
        } else {
            if (typeContext.isNullableType(coneKotlinType3)) {
                return;
            }
            if (z2) {
                z = false;
            }
        }
        KtSourceElement source = firEqualityOperatorCall.getSource();
        if (Intrinsics.areEqual(source != null ? source.getElementType() : null, KtNodeTypes.BINARY_EXPRESSION) || coneKotlinType3 != coneKotlinType || z) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firEqualityOperatorCall.getSource(), (KtDiagnosticFactory1) FirErrors.INSTANCE.getSENSELESS_COMPARISON(), (Object) Boolean.valueOf(z), (AbstractSourceElementPositioningStrategy) null, 16, (Object) null);
        } else {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firEqualityOperatorCall.getSource(), FirErrors.INSTANCE.getSENSELESS_NULL_IN_WHEN(), (AbstractSourceElementPositioningStrategy) null, 8, (Object) null);
        }
    }

    private final KtDiagnosticFactory2<ConeKotlinType, ConeKotlinType> getEnumInapplicabilityDiagnostic(CheckerContext checkerContext, TypeInfo typeInfo, TypeInfo typeInfo2, boolean z) {
        boolean z2 = false;
        boolean z3 = FirTypeCompatibilityHelpersKt.isNullableEnum(typeInfo) && FirTypeCompatibilityHelpersKt.isNullableEnum(typeInfo2);
        boolean z4 = (typeInfo.getType() instanceof ConeIntersectionType) || (typeInfo2.getType() instanceof ConeIntersectionType);
        boolean zIsEnabled = LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ReportErrorsForComparisonOperators);
        if ((z3 || z4) && !zIsEnabled) {
            z2 = true;
        }
        return (z || z2) ? FirErrors.INSTANCE.getINCOMPATIBLE_ENUM_COMPARISON() : FirErrors.INSTANCE.getINCOMPATIBLE_ENUM_COMPARISON_ERROR();
    }

    private final KtDiagnosticFactory3<String, ConeKotlinType, ConeKotlinType> getGeneralInapplicabilityDiagnostic(boolean forceWarning) {
        return forceWarning ? FirErrors.INSTANCE.getEQUALITY_NOT_APPLICABLE_WARNING() : FirErrors.INSTANCE.getEQUALITY_NOT_APPLICABLE();
    }

    private final KtDiagnosticFactory2<ConeKotlinType, ConeKotlinType> getIdentityLessInapplicabilityDiagnostic(CheckerContext checkerContext, TypeInfo typeInfo, TypeInfo typeInfo2, boolean z) {
        boolean z2 = false;
        boolean z3 = FirTypeCompatibilityHelpersKt.isNotNullPrimitive(typeInfo) && FirTypeCompatibilityHelpersKt.isNotNullPrimitive(typeInfo2);
        boolean zAreEqual = Intrinsics.areEqual(ConeTypeUtilsKt.getClassId(typeInfo.getType()), ConeTypeUtilsKt.getClassId(typeInfo2.getType()));
        boolean zIsEnabled = LanguageVersionUtilsKt.isEnabled(checkerContext, LanguageFeature.ReportErrorsForComparisonOperators);
        if ((typeInfo.getIsPrimitive() || typeInfo2.getIsPrimitive()) && FirTypeCompatibilityHelpersKt.areRelated(checkerContext, typeInfo, typeInfo2) && !zIsEnabled) {
            z2 = true;
        }
        if (zAreEqual && z3) {
            return FirErrors.INSTANCE.getDEPRECATED_IDENTITY_EQUALS();
        }
        if (isIdentityComparedWithImplicitBoxing(typeInfo, typeInfo2, checkerContext.getSession())) {
            return FirErrors.INSTANCE.getIMPLICIT_BOXING_IN_IDENTITY_EQUALS();
        }
        return (z || z2) ? FirErrors.INSTANCE.getFORBIDDEN_IDENTITY_EQUALS_WARNING() : FirErrors.INSTANCE.getFORBIDDEN_IDENTITY_EQUALS();
    }

    private final Applicability getInapplicabilityFor(TypeInfo l, TypeInfo r) {
        return (FirEqualityCompatibilityCheckerKt.isCaseMissedByK1Intersector(l, r) && (l.getIsEnumClass() || r.getIsEnumClass())) ? Applicability.INAPPLICABLE_AS_ENUMS : Applicability.GENERALLY_INAPPLICABLE;
    }

    private final KtDiagnosticFactory2<ConeKotlinType, ConeKotlinType> getSourceLessInapplicabilityDiagnostic(boolean forceWarning) {
        return forceWarning ? FirErrors.INSTANCE.getINCOMPATIBLE_TYPES_WARNING() : FirErrors.INSTANCE.getINCOMPATIBLE_TYPES();
    }

    private final boolean isIdentityComparedWithImplicitBoxing(TypeInfo l, TypeInfo r, FirSession session) {
        return arePrimitiveAndNonPrimitiveSupertypeRespectively(l, r, session) || arePrimitiveAndNonPrimitiveSupertypeRespectively(r, l, session);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker
    public void check(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirEqualityOperatorCall firEqualityOperatorCall) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firEqualityOperatorCall.getClass();
        List<FirExpression> arguments = firEqualityOperatorCall.getArgumentList().getArguments();
        if (arguments.size() != 2) {
            w01.a("Equality operator call with non-2 arguments");
            return;
        }
        boolean z = false;
        ArgumentInfo argumentInfo = FirTypeCompatibilityHelpersKt.toArgumentInfo(checkerContext, arguments.get(0));
        ArgumentInfo argumentInfo2 = FirTypeCompatibilityHelpersKt.toArgumentInfo(checkerContext, arguments.get(1));
        checkSenselessness(checkerContext, diagnosticReporter, argumentInfo.getSmartCastType(), argumentInfo2.getSmartCastType(), firEqualityOperatorCall);
        Applicability applicabilityRunApplicabilityCheck = FirPlatformSpecificEqualityCheckerKt.getFirPlatformSpecificEqualityChecker(checkerContext.getSession()).runApplicabilityCheck(checkerContext, firEqualityOperatorCall.getOperation(), argumentInfo.getOriginalType(), argumentInfo2.getOriginalType(), this);
        int[] iArr = WhenMappings.$EnumSwitchMapping$1;
        if (iArr[applicabilityRunApplicabilityCheck.ordinal()] == 1) {
            if ((argumentInfo.getArgument() instanceof FirSmartCastExpression) || (argumentInfo2.getArgument() instanceof FirSmartCastExpression)) {
                Applicability applicabilityRunApplicabilityCheck2 = FirPlatformSpecificEqualityCheckerKt.getFirPlatformSpecificEqualityChecker(checkerContext.getSession()).runApplicabilityCheck(checkerContext, firEqualityOperatorCall.getOperation(), argumentInfo.getSmartCastType(), argumentInfo2.getSmartCastType(), this);
                if (iArr[applicabilityRunApplicabilityCheck2.ordinal()] != 1) {
                    INSTANCE.reportInapplicabilityDiagnostic(checkerContext, diagnosticReporter, firEqualityOperatorCall, applicabilityRunApplicabilityCheck2, firEqualityOperatorCall.getOperation(), true, argumentInfo.getSmartCastTypeInfo(), argumentInfo2.getSmartCastTypeInfo(), argumentInfo.getUserType(), argumentInfo2.getUserType());
                    return;
                }
                return;
            }
            return;
        }
        boolean z2 = FirEqualityCompatibilityCheckerKt.isCaseMissedByK1Intersector(argumentInfo.getOriginalTypeInfo(), argumentInfo2.getOriginalTypeInfo()) && FirEqualityCompatibilityCheckerKt.isCaseMissedByAdditionalK1IncompatibleEnumsCheck(argumentInfo.getOriginalType(), argumentInfo2.getOriginalType(), checkerContext.getSession());
        boolean zIsDisabled = LanguageVersionUtilsKt.isDisabled(checkerContext, LanguageFeature.ReportErrorsForComparisonOperators);
        boolean z3 = z2;
        FirEqualityCompatibilityChecker firEqualityCompatibilityChecker = INSTANCE;
        FirOperation operation = firEqualityOperatorCall.getOperation();
        if (z3 && zIsDisabled) {
            z = true;
        }
        firEqualityCompatibilityChecker.reportInapplicabilityDiagnostic(checkerContext, diagnosticReporter, firEqualityOperatorCall, applicabilityRunApplicabilityCheck, operation, z, argumentInfo.getOriginalTypeInfo(), argumentInfo2.getOriginalTypeInfo(), argumentInfo.getUserType(), argumentInfo2.getUserType());
    }

    public final Applicability checkApplicability(CheckerContext checkerContext, FirOperation firOperation, TypeInfo typeInfo, TypeInfo typeInfo2) {
        checkerContext.getClass();
        firOperation.getClass();
        typeInfo.getClass();
        typeInfo2.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[firOperation.ordinal()];
        if (i == 1 || i == 2) {
            return checkEqualityApplicability(checkerContext, typeInfo, typeInfo2);
        }
        if (i == 3 || i == 4) {
            return checkIdentityApplicability(checkerContext, typeInfo, typeInfo2);
        }
        k2d.a("Invalid operator of FirEqualityOperatorCall");
        return null;
    }

    public final void ifInapplicable(Applicability applicability, Function1<? super Applicability, Unit> function1) {
        applicability.getClass();
        function1.getClass();
        if (WhenMappings.$EnumSwitchMapping$1[applicability.ordinal()] != 1) {
            function1.invoke(applicability);
        }
    }

    public final void reportInapplicabilityDiagnostic(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirEqualityOperatorCall firEqualityOperatorCall, Applicability applicability, FirOperation firOperation, boolean z, TypeInfo typeInfo, TypeInfo typeInfo2, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        checkerContext.getClass();
        diagnosticReporter.getClass();
        firEqualityOperatorCall.getClass();
        applicability.getClass();
        firOperation.getClass();
        typeInfo.getClass();
        typeInfo2.getClass();
        coneKotlinType.getClass();
        coneKotlinType2.getClass();
        if (applicability == Applicability.INAPPLICABLE_AS_IDENTITY_LESS) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firEqualityOperatorCall.getSource(), (KtDiagnosticFactory2) getIdentityLessInapplicabilityDiagnostic(checkerContext, typeInfo, typeInfo2, z), (Object) coneKotlinType, (Object) coneKotlinType2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            return;
        }
        if (applicability == Applicability.INAPPLICABLE_AS_ENUMS) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firEqualityOperatorCall.getSource(), (KtDiagnosticFactory2) getEnumInapplicabilityDiagnostic(checkerContext, typeInfo, typeInfo2, z), (Object) coneKotlinType, (Object) coneKotlinType2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
            return;
        }
        KtSourceElement source = firEqualityOperatorCall.getSource();
        if (!((source != null ? source.getKind() : null) instanceof KtRealSourceElementKind)) {
            KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firEqualityOperatorCall.getSource(), (KtDiagnosticFactory2) getSourceLessInapplicabilityDiagnostic(z), (Object) coneKotlinType, (Object) coneKotlinType2, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
        } else if (applicability == Applicability.GENERALLY_INAPPLICABLE) {
            KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firEqualityOperatorCall.getSource(), (KtDiagnosticFactory3<String, ConeKotlinType, ConeKotlinType>) ((KtDiagnosticFactory3<Object, Object, Object>) getGeneralInapplicabilityDiagnostic(z)), firOperation.getOperator(), coneKotlinType, coneKotlinType2, (64 & 64) != 0 ? null : null);
        } else {
            k2d.a("Shouldn't be here");
        }
    }
}
