package org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import org.jetbrains.kotlin.AbstractKtSourceElement;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.AbstractSourceElementPositioningStrategy;
import org.jetbrains.kotlin.diagnostics.DiagnosticContext;
import org.jetbrains.kotlin.diagnostics.DiagnosticReporter;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory2;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticFactory3;
import org.jetbrains.kotlin.diagnostics.KtDiagnosticReportHelpersKt;
import org.jetbrains.kotlin.diagnostics.rendering.RenderingUtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.FirHelpersKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.diagnostics.jvm.FirJvmErrors;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirExpressionJavaNullabilityWarningCheckersKt;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.expressions.ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttribute;
import org.jetbrains.kotlin.fir.expressions.ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttributeKt;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.java.enhancement.EnhancedForWarningConeSubstitutor;
import org.jetbrains.kotlin.fir.java.enhancement.EnhancedTypeForWarningAttributeKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScopeKt;
import org.jetbrains.kotlin.fir.scopes.ProcessorAction;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReceiverParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.types.model.KotlinTypeMarker;
import org.jetbrains.kotlin.types.model.TypeCheckerProviderContext;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a9\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\tH\u0002R\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\n\u001a\u001d\u0010\u000b\u001a\u00020\f*\u00020\rH\u0002R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u000e\u001a-\u0010\u000f\u001a\u00020\f2\n\u0010\b\u001a\u0006\u0012\u0002\b\u00030\t2\u0006\u0010\u0010\u001a\u00020\rH\u0002R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u0011\u001a)\u0010\u0012\u001a\u00020\u0001*\u00020\u0013H\u0002R\u00020\u0002R\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0002\u0010\u0014\u001ar\u0010\u0015\u001a\u00020\u0001*\u00020\u00132\b\u0010\u0016\u001a\u0004\u0018\u00010\r2\u0018\u0010\u0017\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u00190\u00182#\b\u0002\u0010\u001a\u001a\u001d\u0012\u0013\u0012\u00110\r¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u0010\u0012\u0004\u0012\u00020\f0\u001bH\u0000R\u00020\u0004R\u00020\u0002j\u0006\u0010\u0005\u001a\u00020\u0004j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010\u001e\u001a\u0018\u0010\u001f\u001a\u00020\u00192\u0006\u0010 \u001a\u00020\r2\u0006\u0010\u0016\u001a\u00020\rH\u0002\u001a\f\u0010!\u001a\u00020\f*\u00020\rH\u0002\u001a;\u0010\"\u001a\u0010\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0018\u00010#2\b\u0010 \u001a\u0004\u0018\u00010\r2\b\u0010\u0016\u001a\u0004\u0018\u00010\rH\u0002R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010$\u001a\u0019\u0010%\u001a\u00020&H\u0002R\u00020\u0002j\u0006\u0010\u0003\u001a\u00020\u0002¢\u0006\u0002\u0010'¨\u0006("}, d2 = {"checkDispatchReceiver", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "context", "Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;", "reporter", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;)V", "canLowerBoundBeNull", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "shouldSuppressWarningForExtensionReceiver", "actualTypeForComparison", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Z", "checkConditionForEnhancedTypeMismatch", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;)V", "checkExpressionForEnhancedTypeMismatch", "expectedType", "factory", "Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;", Argument.Delimiters.none, "suppressWarnings", "Lkotlin/Function1;", "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "(Lorg/jetbrains/kotlin/diagnostics/DiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/diagnostics/KtDiagnosticFactory3;Lkotlin/jvm/functions/Function1;)V", "buildSuffix", "actualType", "isExplicitTypeArgumentMadeFlexibleSynthetically", "getEnhancedTypesForComparison", "Lkotlin/Pair;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;)Lkotlin/Pair;", "enhancedForWarningSubstitutor", "Lorg/jetbrains/kotlin/fir/java/enhancement/EnhancedForWarningConeSubstitutor;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)Lorg/jetbrains/kotlin/fir/java/enhancement/EnhancedForWarningConeSubstitutor;", "org.jetbrains.kotlin:checkers.jvm"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExpressionJavaNullabilityWarningCheckersKt {
    public static Unit a(FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        return Unit.INSTANCE;
    }

    public static boolean b(ConeKotlinType coneKotlinType) {
        coneKotlinType.getClass();
        return false;
    }

    private static final String buildSuffix(ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        StringBuilder sb = new StringBuilder();
        if (EnhancedTypeForWarningAttributeKt.isEnhancedTypeForWarningDeprecation(coneKotlinType) || EnhancedTypeForWarningAttributeKt.isEnhancedTypeForWarningDeprecation(coneKotlinType2)) {
            RenderingUtilsKt.appendDeprecationWarningSuffix(sb, LanguageFeature.SupportJavaErrorEnhancementOfArgumentsOfWarningLevelEnhanced);
        } else if (isExplicitTypeArgumentMadeFlexibleSynthetically(coneKotlinType) || isExplicitTypeArgumentMadeFlexibleSynthetically(coneKotlinType2)) {
            RenderingUtilsKt.appendDeprecationWarningSuffix(sb, LanguageFeature.DontMakeExplicitNullableJavaTypeArgumentsFlexible);
        }
        return sb.toString();
    }

    public static Unit c(FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        return Unit.INSTANCE;
    }

    private static final boolean canLowerBoundBeNull(CheckerContext checkerContext, ConeKotlinType coneKotlinType) {
        return TypeUtilsKt.canBeNull$default(ConeTypeUtilsKt.lowerBoundIfFlexible(coneKotlinType), checkerContext.getSession(), false, null, 6, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkConditionForEnhancedTypeMismatch(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirExpression firExpression) {
        checkExpressionForEnhancedTypeMismatch$default(diagnosticReporter, checkerContext, firExpression, checkerContext.getSession().getBuiltinTypes().getBooleanType().getConeType(), FirJvmErrors.INSTANCE.getTYPE_MISMATCH_BASED_ON_JAVA_ANNOTATIONS(), null, 16, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void checkDispatchReceiver(CheckerContext checkerContext, DiagnosticReporter diagnosticReporter, FirQualifiedAccessExpression firQualifiedAccessExpression, FirCallableSymbol<?> firCallableSymbol) {
        EnhancedForWarningConeSubstitutor enhancedForWarningConeSubstitutorEnhancedForWarningSubstitutor;
        ConeKotlinType coneKotlinTypeSubstituteOrNull;
        FirExpression dispatchReceiver = firQualifiedAccessExpression.getDispatchReceiver();
        ConeKotlinType resolvedType = dispatchReceiver != null ? FirTypeUtilsKt.getResolvedType(dispatchReceiver) : null;
        ConeSimpleKotlinType dispatchReceiverType = firCallableSymbol.getDispatchReceiverType();
        if (resolvedType == null || dispatchReceiverType == null || (coneKotlinTypeSubstituteOrNull = (enhancedForWarningConeSubstitutorEnhancedForWarningSubstitutor = enhancedForWarningSubstitutor(checkerContext)).substituteOrNull(resolvedType)) == null) {
            return;
        }
        if (!canLowerBoundBeNull(checkerContext, resolvedType) && canLowerBoundBeNull(checkerContext, coneKotlinTypeSubstituteOrNull)) {
            KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, String> nullability_mismatch_based_on_explicit_type_arguments_for_java = isExplicitTypeArgumentMadeFlexibleSynthetically(resolvedType) ? FirJvmErrors.INSTANCE.getNULLABILITY_MISMATCH_BASED_ON_EXPLICIT_TYPE_ARGUMENTS_FOR_JAVA() : FirJvmErrors.INSTANCE.getRECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS();
            FirExpression dispatchReceiver2 = firQualifiedAccessExpression.getDispatchReceiver();
            KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, dispatchReceiver2 != null ? dispatchReceiver2.getSource() : null, (KtDiagnosticFactory3<ConeKotlinType, ConeSimpleKotlinType, String>) ((KtDiagnosticFactory3<Object, Object, Object>) nullability_mismatch_based_on_explicit_type_arguments_for_java), coneKotlinTypeSubstituteOrNull, dispatchReceiverType, buildSuffix(resolvedType, dispatchReceiverType), (64 & 64) != 0 ? null : null);
        }
        ConeKotlinType coneKotlinTypeFinalApproximationOrSelf = FirHelpersKt.finalApproximationOrSelf(checkerContext, resolvedType);
        ClassId classId = ConeTypeUtilsKt.getClassId(coneKotlinTypeFinalApproximationOrSelf);
        ConeKotlinType coneKotlinTypeSubstituteOrSelf = enhancedForWarningConeSubstitutorEnhancedForWarningSubstitutor.substituteOrSelf(coneKotlinTypeFinalApproximationOrSelf);
        final ClassId classId2 = ConeTypeUtilsKt.getClassId(coneKotlinTypeSubstituteOrSelf);
        if (classId == null || Intrinsics.areEqual(classId2, classId)) {
            return;
        }
        ConeSimpleKotlinType coneSimpleKotlinTypeReplaceArgumentsWithStarProjectionsOrNull = ConeTypeUtilsKt.replaceArgumentsWithStarProjectionsOrNull(dispatchReceiverType);
        if (TypeUtilsKt.isSubtypeOf$default(coneKotlinTypeSubstituteOrSelf, coneSimpleKotlinTypeReplaceArgumentsWithStarProjectionsOrNull == null ? dispatchReceiverType : coneSimpleKotlinTypeReplaceArgumentsWithStarProjectionsOrNull, checkerContext.getSession(), false, 4, null)) {
            return;
        }
        FirTypeScope firTypeScopeDispatchReceiverScope = DeclarationUtilsKt.dispatchReceiverScope(checkerContext, firCallableSymbol);
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        Function1 function1 = new Function1() { // from class: n65
            public final Object invoke(Object obj) {
                return FirExpressionJavaNullabilityWarningCheckersKt.d(classId2, booleanRef, (FirCallableSymbol) obj);
            }
        };
        if (firCallableSymbol instanceof FirPropertySymbol) {
            FirPropertySymbol firPropertySymbol = (FirPropertySymbol) firCallableSymbol;
            firTypeScopeDispatchReceiverScope.processPropertiesByName(firPropertySymbol.getName(), new Function1() { // from class: o65
                public final Object invoke(Object obj) {
                    return FirExpressionJavaNullabilityWarningCheckersKt.a((FirVariableSymbol) obj);
                }
            });
            FirTypeScopeKt.processOverriddenProperties(firTypeScopeDispatchReceiverScope, firPropertySymbol, (Function1<? super FirPropertySymbol, ? extends ProcessorAction>) function1);
        } else if (firCallableSymbol instanceof FirNamedFunctionSymbol) {
            FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) firCallableSymbol;
            firTypeScopeDispatchReceiverScope.processFunctionsByName(firNamedFunctionSymbol.getName(), new Function1() { // from class: p65
                public final Object invoke(Object obj) {
                    return FirExpressionJavaNullabilityWarningCheckersKt.c((FirNamedFunctionSymbol) obj);
                }
            });
            FirTypeScopeKt.processOverriddenFunctions(firTypeScopeDispatchReceiverScope, firNamedFunctionSymbol, (Function1<? super FirNamedFunctionSymbol, ? extends ProcessorAction>) function1);
        }
        if (booleanRef.element) {
            return;
        }
        FirExpression dispatchReceiver3 = firQualifiedAccessExpression.getDispatchReceiver();
        KtDiagnosticReportHelpersKt.reportOn$default((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) (dispatchReceiver3 != null ? dispatchReceiver3.getSource() : null), (KtDiagnosticFactory2) FirJvmErrors.INSTANCE.getRECEIVER_MUTABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS(), (Object) coneKotlinTypeSubstituteOrNull, (Object) classId, (AbstractSourceElementPositioningStrategy) null, 32, (Object) null);
    }

    public static final void checkExpressionForEnhancedTypeMismatch(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirExpression firExpression, ConeKotlinType coneKotlinType, KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, String> ktDiagnosticFactory3, Function1<? super ConeKotlinType, Boolean> function1) {
        ConeKotlinType resolvedType;
        Pair<ConeKotlinType, ConeKotlinType> enhancedTypesForComparison;
        KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, String> nullability_mismatch_based_on_explicit_type_arguments_for_java;
        diagnosticReporter.getClass();
        checkerContext.getClass();
        firExpression.getClass();
        ktDiagnosticFactory3.getClass();
        function1.getClass();
        if (coneKotlinType == null || (enhancedTypesForComparison = getEnhancedTypesForComparison(checkerContext, (resolvedType = FirTypeUtilsKt.getResolvedType(firExpression)), coneKotlinType)) == null) {
            return;
        }
        ConeKotlinType coneKotlinType2 = (ConeKotlinType) enhancedTypesForComparison.component1();
        ConeKotlinType coneKotlinType3 = (ConeKotlinType) enhancedTypesForComparison.component2();
        if (TypeUtilsKt.isSubtypeOf((KotlinTypeMarker) coneKotlinType2, (TypeCheckerProviderContext) TypeComponentsKt.getTypeContext(checkerContext.getSession()), (KotlinTypeMarker) coneKotlinType3) || !TypeUtilsKt.isSubtypeOf((KotlinTypeMarker) resolvedType, (TypeCheckerProviderContext) TypeComponentsKt.getTypeContext(checkerContext.getSession()), (KotlinTypeMarker) coneKotlinType) || ((Boolean) function1.invoke(coneKotlinType2)).booleanValue()) {
            return;
        }
        if (isExplicitTypeArgumentMadeFlexibleSynthetically(resolvedType) || isExplicitTypeArgumentMadeFlexibleSynthetically(coneKotlinType)) {
            nullability_mismatch_based_on_explicit_type_arguments_for_java = FirJvmErrors.INSTANCE.getNULLABILITY_MISMATCH_BASED_ON_EXPLICIT_TYPE_ARGUMENTS_FOR_JAVA();
        } else {
            FirJvmErrors firJvmErrors = FirJvmErrors.INSTANCE;
            nullability_mismatch_based_on_explicit_type_arguments_for_java = (!Intrinsics.areEqual(ktDiagnosticFactory3, firJvmErrors.getRECEIVER_NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS()) || canLowerBoundBeNull(checkerContext, coneKotlinType2)) ? ktDiagnosticFactory3 : firJvmErrors.getTYPE_MISMATCH_BASED_ON_JAVA_ANNOTATIONS();
        }
        KtDiagnosticReportHelpersKt.reportOn((DiagnosticContext) checkerContext, diagnosticReporter, (AbstractKtSourceElement) firExpression.getSource(), (KtDiagnosticFactory3<ConeKotlinType, ConeKotlinType, String>) ((KtDiagnosticFactory3<Object, Object, Object>) nullability_mismatch_based_on_explicit_type_arguments_for_java), coneKotlinType2, coneKotlinType3, buildSuffix(resolvedType, coneKotlinType), (64 & 64) != 0 ? null : null);
    }

    public static /* synthetic */ void checkExpressionForEnhancedTypeMismatch$default(DiagnosticReporter diagnosticReporter, CheckerContext checkerContext, FirExpression firExpression, ConeKotlinType coneKotlinType, KtDiagnosticFactory3 ktDiagnosticFactory3, Function1 function1, int i, Object obj) {
        if ((i & 16) != 0) {
            function1 = new Function1() { // from class: q65
                public final Object invoke(Object obj2) {
                    return Boolean.valueOf(FirExpressionJavaNullabilityWarningCheckersKt.b((ConeKotlinType) obj2));
                }
            };
        }
        checkExpressionForEnhancedTypeMismatch(diagnosticReporter, checkerContext, firExpression, coneKotlinType, ktDiagnosticFactory3, function1);
    }

    public static ProcessorAction d(ClassId classId, Ref.BooleanRef booleanRef, FirCallableSymbol firCallableSymbol) {
        firCallableSymbol.getClass();
        ConeSimpleKotlinType dispatchReceiverType = firCallableSymbol.getDispatchReceiverType();
        if (!Intrinsics.areEqual(dispatchReceiverType != null ? ConeTypeUtilsKt.getClassId(dispatchReceiverType) : null, classId)) {
            return ProcessorAction.NEXT;
        }
        booleanRef.element = true;
        return ProcessorAction.STOP;
    }

    private static final EnhancedForWarningConeSubstitutor enhancedForWarningSubstitutor(CheckerContext checkerContext) {
        return new EnhancedForWarningConeSubstitutor(TypeComponentsKt.getTypeContext(checkerContext.getSession()), LanguageFeature.DontMakeExplicitNullableJavaTypeArgumentsFlexible);
    }

    private static final Pair<ConeKotlinType, ConeKotlinType> getEnhancedTypesForComparison(CheckerContext checkerContext, ConeKotlinType coneKotlinType, ConeKotlinType coneKotlinType2) {
        if (coneKotlinType == null || coneKotlinType2 == null || (coneKotlinType instanceof ConeErrorType) || (coneKotlinType2 instanceof ConeErrorType)) {
            return null;
        }
        EnhancedForWarningConeSubstitutor enhancedForWarningConeSubstitutorEnhancedForWarningSubstitutor = enhancedForWarningSubstitutor(checkerContext);
        ConeKotlinType coneKotlinTypeSubstituteOrNull = enhancedForWarningConeSubstitutorEnhancedForWarningSubstitutor.substituteOrNull(coneKotlinType);
        ConeKotlinType coneKotlinTypeSubstituteOrNull2 = enhancedForWarningConeSubstitutorEnhancedForWarningSubstitutor.substituteOrNull(coneKotlinType2);
        if (coneKotlinTypeSubstituteOrNull == null && coneKotlinTypeSubstituteOrNull2 == null) {
            return null;
        }
        if (coneKotlinTypeSubstituteOrNull != null) {
            coneKotlinType = coneKotlinTypeSubstituteOrNull;
        }
        if (coneKotlinTypeSubstituteOrNull2 != null) {
            coneKotlinType2 = coneKotlinTypeSubstituteOrNull2;
        }
        return TuplesKt.to(coneKotlinType, coneKotlinType2);
    }

    private static final boolean isExplicitTypeArgumentMadeFlexibleSynthetically(ConeKotlinType coneKotlinType) {
        ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttribute explicitTypeArgumentIfMadeFlexibleSynthetically = ExplicitTypeArgumentIfMadeFlexibleSyntheticallyTypeAttributeKt.getExplicitTypeArgumentIfMadeFlexibleSynthetically(coneKotlinType.getAttributes());
        return (explicitTypeArgumentIfMadeFlexibleSynthetically != null ? explicitTypeArgumentIfMadeFlexibleSynthetically.getRelevantFeature() : null) == LanguageFeature.DontMakeExplicitNullableJavaTypeArgumentsFlexible;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean shouldSuppressWarningForExtensionReceiver(CheckerContext checkerContext, FirCallableSymbol<?> firCallableSymbol, ConeKotlinType coneKotlinType) {
        ConeKotlinType coneKotlinType2;
        boolean zIsSubtypeOf$default;
        ConeSimpleKotlinType coneSimpleKotlinTypeReplaceArgumentsWithStarProjectionsOrNull;
        CallableId callableId = firCallableSymbol.getCallableId();
        FqName packageName = callableId != null ? callableId.getPackageName() : null;
        FqName fqName = StandardNames.COLLECTIONS_PACKAGE_FQ_NAME;
        if (!Intrinsics.areEqual(packageName, fqName)) {
            return false;
        }
        List<FirCallableSymbol<?>> topLevelCallableSymbols = FirSymbolProviderKt.getSymbolProvider(checkerContext.getSession()).getTopLevelCallableSymbols(fqName, callableId.getCallableName());
        if ((topLevelCallableSymbols instanceof Collection) && topLevelCallableSymbols.isEmpty()) {
            return false;
        }
        Iterator<T> it = topLevelCallableSymbols.iterator();
        while (it.hasNext()) {
            FirReceiverParameterSymbol receiverParameterSymbol = ((FirCallableSymbol) it.next()).getReceiverParameterSymbol();
            ConeKotlinType resolvedType = receiverParameterSymbol != null ? receiverParameterSymbol.getResolvedType() : null;
            ConeSimpleKotlinType coneSimpleKotlinType = resolvedType instanceof ConeSimpleKotlinType ? (ConeSimpleKotlinType) resolvedType : null;
            if (coneSimpleKotlinType == null || (coneSimpleKotlinTypeReplaceArgumentsWithStarProjectionsOrNull = ConeTypeUtilsKt.replaceArgumentsWithStarProjectionsOrNull(coneSimpleKotlinType)) == null) {
                coneKotlinType2 = coneKotlinType;
                zIsSubtypeOf$default = false;
            } else {
                coneKotlinType2 = coneKotlinType;
                zIsSubtypeOf$default = TypeUtilsKt.isSubtypeOf$default(coneKotlinType2, coneSimpleKotlinTypeReplaceArgumentsWithStarProjectionsOrNull, checkerContext.getSession(), false, 4, null);
            }
            if (zIsSubtypeOf$default) {
                return true;
            }
            coneKotlinType = coneKotlinType2;
        }
        return false;
    }
}
