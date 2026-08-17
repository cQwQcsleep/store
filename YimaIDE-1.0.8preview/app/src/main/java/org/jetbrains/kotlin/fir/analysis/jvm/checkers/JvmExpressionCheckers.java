package org.jetbrains.kotlin.fir.analysis.jvm.checkers;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirSuperCallWithDefaultsChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirArrayOfNullableNothingExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirAssignmentJavaNullabilityWarningChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirFieldAccessShadowedByInvisibleKotlinProperty;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirFieldReferenceShadowedByInvisibleKotlinProperty;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirInterfaceDefaultMethodCallChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJavaAnnotationsChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJavaClassInheritsKtPrivateClassExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJavaClassOnCompanionChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJavaGenericVarianceViolationTypeChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJavaSamConstructorNullabilityChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJavaSamInterfaceConstructorReferenceChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJavaUnnecessaryNotNullChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJavaUnnecessarySafeCallChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJavaWhenExhaustivenessWarningChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJvmAtomicReferenceArrayToPrimitiveCallChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJvmAtomicReferenceToPrimitiveCallChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJvmIdentityEqualsOnJavaValueBasedClass;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJvmIdentitySensitiveCallWithValueTypeObjectChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJvmInconsistentOperatorFromJavaCallChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJvmInlineTargetQualifiedAccessChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJvmMissingBuiltInDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJvmModuleAccessibilityQualifiedAccessChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJvmModuleAccessibilityResolvedQualifierChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJvmPackageNameAnnotationsChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJvmPolymorphicSignatureCallChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJvmProtectedInSuperClassCompanionCallChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJvmReflectionApiCallChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJvmSerializableLambdaChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirJvmSuspensionPointInsideMutexLockChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirLogicExpressionTypeJavaNullabilityWarningChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirLoopConditionJavaNullabilityWarningChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirQualifiedAccessJavaNullabilityWarningChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirReturnJavaNullabilityWarningChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirSyntheticPropertyWithoutJavaOriginChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirThrowJavaNullabilityWarningChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirUnsupportedSyntheticCallableReferenceChecker;
import org.jetbrains.kotlin.fir.analysis.jvm.checkers.expression.FirWhenConditionJavaNullabilityWarningChecker;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirBooleanOperatorExpression;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCheckNotNullCall;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirLoop;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirThrowExpression;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ô\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\r0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000e\u0010\nR$\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00100\u0006j\u0002`\u00110\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\nR$\u0010\u0013\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00140\u0006j\u0002`\u00150\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0016\u0010\nR$\u0010\u0017\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00180\u0006j\u0002`\u00190\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001a\u0010\nR$\u0010\u001b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u001c0\u0006j\u0002`\u001d0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\nR$\u0010\u001f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020 0\u0006j\u0002`!0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\"\u0010\nR$\u0010#\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020$0\u0006j\u0002`%0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b&\u0010\nR$\u0010'\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020(0\u0006j\u0002`)0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b*\u0010\nR$\u0010+\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020,0\u0006j\u0002`-0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b.\u0010\nR$\u0010/\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002000\u0006j\u0002`10\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b2\u0010\nR$\u00103\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002040\u0006j\u0002`50\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b6\u0010\nR$\u00107\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002080\u0006j\u0002`90\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b:\u0010\nR$\u0010;\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020<0\u0006j\u0002`=0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b>\u0010\nR$\u0010?\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020@0\u0006j\u0002`A0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bB\u0010\nR$\u0010C\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020D0\u0006j\u0002`E0\u00058VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bF\u0010\n¨\u0006G"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/jvm/checkers/JvmExpressionCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ExpressionCheckers;", "<init>", "()V", "basicExpressionCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBasicExpressionChecker;", "getBasicExpressionCheckers", "()Ljava/util/Set;", "qualifiedAccessExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "getQualifiedAccessExpressionCheckers", "propertyAccessExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirPropertyAccessExpressionChecker;", "getPropertyAccessExpressionCheckers", "callableReferenceAccessCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCallableReferenceAccessChecker;", "getCallableReferenceAccessCheckers", "functionCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "getFunctionCallCheckers", "annotationCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirAnnotationChecker;", "getAnnotationCheckers", "loopExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirLoop;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirLoopExpressionChecker;", "getLoopExpressionCheckers", "whenExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirWhenExpressionChecker;", "getWhenExpressionCheckers", "booleanOperatorExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBooleanOperatorExpressionChecker;", "getBooleanOperatorExpressionCheckers", "throwExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirThrowExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirThrowExpressionChecker;", "getThrowExpressionCheckers", "variableAssignmentCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirVariableAssignmentChecker;", "getVariableAssignmentCheckers", "safeCallExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirSafeCallExpressionChecker;", "getSafeCallExpressionCheckers", "checkNotNullCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckNotNullCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCheckNotNullCallChecker;", "getCheckNotNullCallCheckers", "resolvedQualifierCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirResolvedQualifierChecker;", "getResolvedQualifierCheckers", "returnExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReturnExpressionChecker;", "getReturnExpressionCheckers", "equalityOperatorCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirEqualityOperatorCallChecker;", "getEqualityOperatorCallCheckers", "org.jetbrains.kotlin:checkers.jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class JvmExpressionCheckers extends ExpressionCheckers {
    public static final JvmExpressionCheckers INSTANCE = new JvmExpressionCheckers();

    private JvmExpressionCheckers() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirAnnotation>> getAnnotationCheckers() {
        return SetsKt.setOf(new FirExpressionChecker[]{FirJavaAnnotationsChecker.INSTANCE, FirJvmPackageNameAnnotationsChecker.INSTANCE, FirJvmSerializableLambdaChecker.INSTANCE});
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirStatement>> getBasicExpressionCheckers() {
        return SetsKt.setOf(new FirExpressionChecker[]{FirJvmProtectedInSuperClassCompanionCallChecker.INSTANCE, FirJvmReflectionApiCallChecker.INSTANCE, FirJvmMissingBuiltInDeclarationChecker.INSTANCE});
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirBooleanOperatorExpression>> getBooleanOperatorExpressionCheckers() {
        return SetsKt.setOf(FirLogicExpressionTypeJavaNullabilityWarningChecker.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirCallableReferenceAccess>> getCallableReferenceAccessCheckers() {
        return SetsKt.setOf(new FirExpressionChecker[]{FirUnsupportedSyntheticCallableReferenceChecker.INSTANCE, FirFieldReferenceShadowedByInvisibleKotlinProperty.INSTANCE, FirJavaSamInterfaceConstructorReferenceChecker.INSTANCE});
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirCheckNotNullCall>> getCheckNotNullCallCheckers() {
        return SetsKt.setOf(FirJavaUnnecessaryNotNullChecker.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirEqualityOperatorCall>> getEqualityOperatorCallCheckers() {
        return SetsKt.setOf(FirJvmIdentityEqualsOnJavaValueBasedClass.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirFunctionCall>> getFunctionCallCheckers() {
        return SetsKt.setOf(new FirExpressionChecker[]{FirJavaGenericVarianceViolationTypeChecker.INSTANCE, FirSuperCallWithDefaultsChecker.INSTANCE, FirJvmSuspensionPointInsideMutexLockChecker.INSTANCE, FirJvmIdentitySensitiveCallWithValueTypeObjectChecker.INSTANCE, FirJvmInconsistentOperatorFromJavaCallChecker.INSTANCE, FirJvmPolymorphicSignatureCallChecker.INSTANCE, FirJvmAtomicReferenceToPrimitiveCallChecker.INSTANCE, FirJvmAtomicReferenceArrayToPrimitiveCallChecker.INSTANCE, FirJavaSamConstructorNullabilityChecker.INSTANCE});
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirLoop>> getLoopExpressionCheckers() {
        return SetsKt.setOf(FirLoopConditionJavaNullabilityWarningChecker.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirPropertyAccessExpression>> getPropertyAccessExpressionCheckers() {
        return SetsKt.setOf(new FirExpressionChecker[]{FirSyntheticPropertyWithoutJavaOriginChecker.INSTANCE, FirFieldAccessShadowedByInvisibleKotlinProperty.INSTANCE, FirJavaClassOnCompanionChecker.INSTANCE});
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirQualifiedAccessExpression>> getQualifiedAccessExpressionCheckers() {
        return SetsKt.setOf(new FirExpressionChecker[]{FirInterfaceDefaultMethodCallChecker.INSTANCE, FirQualifiedAccessJavaNullabilityWarningChecker.INSTANCE, FirJvmModuleAccessibilityQualifiedAccessChecker.INSTANCE, FirJvmInlineTargetQualifiedAccessChecker.INSTANCE, FirJavaClassInheritsKtPrivateClassExpressionChecker.INSTANCE, FirArrayOfNullableNothingExpressionChecker.INSTANCE});
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirResolvedQualifier>> getResolvedQualifierCheckers() {
        return SetsKt.setOf(FirJvmModuleAccessibilityResolvedQualifierChecker.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirReturnExpression>> getReturnExpressionCheckers() {
        return SetsKt.setOf(FirReturnJavaNullabilityWarningChecker.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirSafeCallExpression>> getSafeCallExpressionCheckers() {
        return SetsKt.setOf(FirJavaUnnecessarySafeCallChecker.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirThrowExpression>> getThrowExpressionCheckers() {
        return SetsKt.setOf(FirThrowJavaNullabilityWarningChecker.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirVariableAssignment>> getVariableAssignmentCheckers() {
        return SetsKt.setOf(FirAssignmentJavaNullabilityWarningChecker.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirWhenExpression>> getWhenExpressionCheckers() {
        return SetsKt.setOf(new FirExpressionChecker[]{FirWhenConditionJavaNullabilityWarningChecker.INSTANCE, FirJavaWhenExhaustivenessWarningChecker.INSTANCE});
    }
}
