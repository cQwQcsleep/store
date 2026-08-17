package org.jetbrains.kotlin.fir.analysis.checkers;

import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.ArrayEqualityCanBeReplacedWithContentEquals;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirAbstractClassInstantiationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirAbstractSuperCallChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirAnnotationExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirArrayOfNothingQualifierChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirAssignmentOperatorCallChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirAssignmentTypeMismatchChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirBreakOrContinueJumpsAcrossFunctionBoundaryChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirCallableReferenceChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirCastOperatorsChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirCatchParameterChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirClassLiteralChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirCommonAtomicArrayToPrimitiveCallChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirCommonAtomicReferenceToPrimitiveCallChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirConflictsExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirConstructorCallChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirContextParameterInCalledSignatureChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirContextSensitiveResolutionAmbiguityCheckerForEqualities;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirContextSensitiveResolutionAmbiguityCheckerForTypeOperators;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirContractNotFirstStatementChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirConventionFunctionCallChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirCustomEnumEntriesMigrationAccessChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirCustomEnumEntriesMigrationQualifierChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirCustomEnumEntriesMigrationReferenceChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirDataClassCopyUsageWillBecomeInaccessibleChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirDeprecatedQualifierChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirDeprecatedSmartCastChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirDeprecationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirDivisionByZeroChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirDslMarkerUseSiteChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirEqualityCompatibilityChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExhaustiveWhenChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionAnnotationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirExpressionWithErrorTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirForLoopChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirForLoopStatementAssignmentChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirFunctionReturnTypeMismatchChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirGenericQualifierOnConstructorCallChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirImplicitPropertyTypeMakesBehaviorOrderDependantChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirIncompatibleClassExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirIncompatibleProjectionsOnTypeArgumentChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirInlineBodyResolvableExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirInlineBodyResolvedQualifierChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirInlineBodyVariableAssignmentChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirInlineExposedLessVisibleThisReceiverChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirInlineExposedLessVisibleTypeQualifiedAccessChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirKotlinActualAnnotationHasNoEffectInKotlinExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirLargeArityFunctionCallableReferenceChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirLateinitIntrinsicApplicabilityChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirLogicExpressionTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirLoopConditionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirMissingDependencyClassChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirMissingDependencySupertypeInQualifiedAccessExpressionsChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirMultiDollarInterpolationCheckerConcatenation;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirMultiDollarInterpolationCheckerLiteral;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirNamedVarargChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirNotNullAssertionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirOptInAnnotationCallChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirOptInUsageAccessChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirOptInUsageQualifierChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirOptionalExpectationExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirPackageOnLhsQualifierChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirParenthesizedLhsSetOperatorChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirParenthesizedLhsVariableAssignmentChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirPrivateToThisAccessChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirProjectionsOnNonClassTypeArgumentChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirPropertyAccessTypeArgumentsChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirProtectedConstructorNotInSuperCallChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirQualifierWithTypeArgumentsChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirReassignmentAndInvisibleSetterChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirReceiverAccessBeforeSuperCallChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirRecursiveProblemChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirReifiedChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirReturnSyntaxAndLabelChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirSealedClassConstructorCallChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirSingleNamedFunctionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirSpreadOfNullableChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirStandaloneQualifierChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirSuperReferenceChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirSuperclassNotAccessibleFromInterfaceChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirSuspendCallChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirThrowExpressionTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirTrimMarginBlankPrefixChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirTypeArgumentsNotAllowedExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirTypeArgumentsOfQualifierOfCallableReferenceChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirTypeParameterInQualifiedAccessChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirUnderscoreChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirUninitializedEnumChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirUnnecessarySafeCallChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirUnsupportedArrayLiteralChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirUpperBoundViolatedQualifiedAccessExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirUselessElvisChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirVarargWithNonTrivialUpperBoundInferredToNothingChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirVisibilityQualifierChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirWhenConditionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirWhenReturnTypeChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirWhenSubjectChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.PlatformClassMappedToKotlinConstructorCallChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.RedundantCallOfConversionMethodChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirAnnotatedBinaryExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirCommaInWhenConditionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirConfusingWhenBranchSyntaxChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirPrefixAndSuffixSyntaxChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirUnderscoredTypeArgumentSyntaxChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.syntax.FirWhenGuardChecker;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirBooleanOperatorExpression;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCheckNotNullCall;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirElvisExpression;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirInaccessibleReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirLoop;
import org.jetbrains.kotlin.fir.expressions.FirLoopJump;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirStringConcatenationCall;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirThrowExpression;
import org.jetbrains.kotlin.fir.expressions.FirTryExpression;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ü\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\r0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR$\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00100\u0006j\u0002`\u00110\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\nR$\u0010\u0013\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00140\u0006j\u0002`\u00150\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\nR$\u0010\u0017\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00180\u0006j\u0002`\u00190\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\nR$\u0010\u001b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u001c0\u0006j\u0002`\u001d0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\nR$\u0010\u001f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020 0\u0006j\u0002`!0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\nR$\u0010#\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020$0\u0006j\u0002`%0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\nR$\u0010'\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020(0\u0006j\u0002`)0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\nR$\u0010+\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020,0\u0006j\u0002`-0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\nR$\u0010/\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002000\u0006j\u0002`10\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\nR$\u00103\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002040\u0006j\u0002`50\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\nR$\u00107\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002080\u0006j\u0002`90\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\nR$\u0010;\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020<0\u0006j\u0002`=0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\nR$\u0010?\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020@0\u0006j\u0002`A0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\nR$\u0010C\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020D0\u0006j\u0002`E0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\nR$\u0010G\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020H0\u0006j\u0002`I0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010\nR$\u0010K\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020L0\u0006j\u0002`M0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bN\u0010\nR$\u0010O\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020P0\u0006j\u0002`Q0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bR\u0010\nR$\u0010S\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020T0\u0006j\u0002`U0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bV\u0010\nR$\u0010W\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020X0\u0006j\u0002`Y0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010\nR$\u0010[\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\\0\u0006j\u0002`]0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b^\u0010\nR$\u0010_\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020`0\u0006j\u0002`a0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bb\u0010\nR$\u0010c\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020d0\u0006j\u0002`e0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bf\u0010\nR$\u0010g\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020h0\u0006j\u0002`i0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bj\u0010\nR$\u0010k\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020l0\u0006j\u0002`m0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bn\u0010\nR$\u0010o\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020p0\u0006j\u0002`q0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\br\u0010\nR$\u0010s\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020t0\u0006j\u0002`u0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bv\u0010\nR$\u0010w\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020x0\u0006j\u0002`y0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bz\u0010\nR$\u0010{\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020|0\u0006j\u0002`}0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b~\u0010\n¨\u0006\u007f"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/CommonExpressionCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ExpressionCheckers;", "<init>", "()V", "annotationCallCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirAnnotationCallChecker;", "getAnnotationCallCheckers", "()Ljava/util/Set;", "annotationCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirAnnotationChecker;", "getAnnotationCheckers", "basicExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBasicExpressionChecker;", "getBasicExpressionCheckers", "throwExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirThrowExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirThrowExpressionChecker;", "getThrowExpressionCheckers", "qualifiedAccessExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "getQualifiedAccessExpressionCheckers", "callCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCallChecker;", "getCallCheckers", "functionCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "getFunctionCallCheckers", "propertyAccessExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirPropertyAccessExpressionChecker;", "getPropertyAccessExpressionCheckers", "tryExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirTryExpressionChecker;", "getTryExpressionCheckers", "variableAssignmentCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirVariableAssignmentChecker;", "getVariableAssignmentCheckers", "whenExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirWhenExpressionChecker;", "getWhenExpressionCheckers", "loopExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirLoop;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirLoopExpressionChecker;", "getLoopExpressionCheckers", "loopJumpCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirLoopJump;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirLoopJumpChecker;", "getLoopJumpCheckers", "booleanOperatorExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBooleanOperatorExpressionChecker;", "getBooleanOperatorExpressionCheckers", "returnExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReturnExpressionChecker;", "getReturnExpressionCheckers", "blockCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBlockChecker;", "getBlockCheckers", "checkNotNullCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckNotNullCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCheckNotNullCallChecker;", "getCheckNotNullCallCheckers", "elvisExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirElvisExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirElvisExpressionChecker;", "getElvisExpressionCheckers", "getClassCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirGetClassCallChecker;", "getGetClassCallCheckers", "safeCallExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirSafeCallExpressionChecker;", "getSafeCallExpressionCheckers", "smartCastExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirSmartCastExpressionChecker;", "getSmartCastExpressionCheckers", "typeOperatorCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirTypeOperatorCallChecker;", "getTypeOperatorCallCheckers", "resolvedQualifierCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirResolvedQualifierChecker;", "getResolvedQualifierCheckers", "equalityOperatorCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirEqualityOperatorCallChecker;", "getEqualityOperatorCallCheckers", "collectionLiteralCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCollectionLiteralChecker;", "getCollectionLiteralCheckers", "inaccessibleReceiverCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirInaccessibleReceiverExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirInaccessibleReceiverChecker;", "getInaccessibleReceiverCheckers", "callableReferenceAccessCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCallableReferenceAccessChecker;", "getCallableReferenceAccessCheckers", "stringConcatenationCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirStringConcatenationCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirStringConcatenationCallChecker;", "getStringConcatenationCallCheckers", "literalExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirLiteralExpressionChecker;", "getLiteralExpressionCheckers", "thisReceiverExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirThisReceiverExpressionChecker;", "getThisReceiverExpressionCheckers", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CommonExpressionCheckers extends ExpressionCheckers {
    public static final CommonExpressionCheckers INSTANCE = new CommonExpressionCheckers();
    private static final Set<FirExpressionChecker<FirAnnotationCall>> annotationCallCheckers = SetsKt.setOf(new FirExpressionChecker[]{FirAnnotationExpressionChecker.INSTANCE, FirOptInAnnotationCallChecker.INSTANCE});
    private static final Set<FirExpressionChecker<FirAnnotation>> annotationCheckers = SetsKt.setOf(FirDslMarkerUseSiteChecker.INSTANCE);
    private static final Set<FirExpressionChecker<FirStatement>> basicExpressionCheckers = SetsKt.setOf(new FirExpressionChecker[]{FirUnderscoreChecker.INSTANCE, FirExpressionAnnotationChecker.INSTANCE, FirDeprecationChecker.INSTANCE, FirRecursiveProblemChecker.INSTANCE, FirOptInUsageAccessChecker.INSTANCE, FirPrefixAndSuffixSyntaxChecker.INSTANCE, FirAnnotatedBinaryExpressionChecker.INSTANCE, FirExpressionWithErrorTypeChecker.INSTANCE, FirInlineBodyResolvableExpressionChecker.INSTANCE, ArrayEqualityCanBeReplacedWithContentEquals.INSTANCE});
    private static final Set<FirExpressionChecker<FirThrowExpression>> throwExpressionCheckers = SetsKt.setOf(FirThrowExpressionTypeChecker.INSTANCE);
    private static final Set<FirExpressionChecker<FirQualifiedAccessExpression>> qualifiedAccessExpressionCheckers = SetsKt.setOf(new FirExpressionChecker[]{FirCallableReferenceChecker.INSTANCE, FirSuperReferenceChecker.INSTANCE, FirSuperclassNotAccessibleFromInterfaceChecker.INSTANCE, FirAbstractSuperCallChecker.INSTANCE, FirProjectionsOnNonClassTypeArgumentChecker.INSTANCE, FirDataClassCopyUsageWillBecomeInaccessibleChecker.INSTANCE, FirIncompatibleProjectionsOnTypeArgumentChecker.INSTANCE, FirUpperBoundViolatedQualifiedAccessExpressionChecker.INSTANCE, FirTypeArgumentsNotAllowedExpressionChecker.INSTANCE, FirTypeParameterInQualifiedAccessChecker.INSTANCE, FirSealedClassConstructorCallChecker.INSTANCE, FirUninitializedEnumChecker.INSTANCE, FirReifiedChecker.INSTANCE, FirSuspendCallChecker.INSTANCE, FirLateinitIntrinsicApplicabilityChecker.INSTANCE, FirLargeArityFunctionCallableReferenceChecker.INSTANCE, FirAbstractClassInstantiationChecker.INSTANCE, FirIncompatibleClassExpressionChecker.INSTANCE, FirMissingDependencyClassChecker.INSTANCE, FirMissingDependencySupertypeInQualifiedAccessExpressionsChecker.INSTANCE, FirArrayOfNothingQualifierChecker.INSTANCE, FirPrivateToThisAccessChecker.INSTANCE, FirContextParameterInCalledSignatureChecker.INSTANCE, FirInlineExposedLessVisibleTypeQualifiedAccessChecker.INSTANCE});
    private static final Set<FirExpressionChecker<FirCall>> callCheckers = SetsKt.setOf(FirNamedVarargChecker.INSTANCE);
    private static final Set<FirExpressionChecker<FirFunctionCall>> functionCallCheckers = SetsKt.setOf(new FirExpressionChecker[]{FirKotlinActualAnnotationHasNoEffectInKotlinExpressionChecker.FunctionCall.INSTANCE, FirConventionFunctionCallChecker.INSTANCE, FirDivisionByZeroChecker.INSTANCE, FirTrimMarginBlankPrefixChecker.INSTANCE, FirConstructorCallChecker.INSTANCE, FirSpreadOfNullableChecker.INSTANCE, FirAssignmentOperatorCallChecker.INSTANCE, FirUnderscoredTypeArgumentSyntaxChecker.INSTANCE, FirContractNotFirstStatementChecker.INSTANCE, FirProtectedConstructorNotInSuperCallChecker.INSTANCE, FirOptionalExpectationExpressionChecker.INSTANCE, FirParenthesizedLhsSetOperatorChecker.INSTANCE, FirCommonAtomicReferenceToPrimitiveCallChecker.INSTANCE, FirCommonAtomicArrayToPrimitiveCallChecker.INSTANCE, FirGenericQualifierOnConstructorCallChecker.INSTANCE, FirVarargWithNonTrivialUpperBoundInferredToNothingChecker.INSTANCE, PlatformClassMappedToKotlinConstructorCallChecker.INSTANCE, RedundantCallOfConversionMethodChecker.INSTANCE, FirImplicitPropertyTypeMakesBehaviorOrderDependantChecker.INSTANCE});
    private static final Set<FirExpressionChecker<FirPropertyAccessExpression>> propertyAccessExpressionCheckers = SetsKt.setOf(new FirExpressionChecker[]{FirPropertyAccessTypeArgumentsChecker.INSTANCE, FirCustomEnumEntriesMigrationAccessChecker.INSTANCE});
    private static final Set<FirExpressionChecker<FirTryExpression>> tryExpressionCheckers = SetsKt.setOf(FirCatchParameterChecker.INSTANCE);
    private static final Set<FirExpressionChecker<FirVariableAssignment>> variableAssignmentCheckers = SetsKt.setOf(new FirExpressionChecker[]{FirReassignmentAndInvisibleSetterChecker.INSTANCE, FirAssignmentTypeMismatchChecker.INSTANCE, FirInlineBodyVariableAssignmentChecker.INSTANCE, FirParenthesizedLhsVariableAssignmentChecker.INSTANCE});
    private static final Set<FirExpressionChecker<FirWhenExpression>> whenExpressionCheckers = SetsKt.setOf(new FirExpressionChecker[]{FirExhaustiveWhenChecker.INSTANCE, FirWhenConditionChecker.INSTANCE, FirWhenSubjectChecker.INSTANCE, FirCommaInWhenConditionChecker.INSTANCE, FirConfusingWhenBranchSyntaxChecker.INSTANCE, FirWhenGuardChecker.INSTANCE, FirWhenReturnTypeChecker.INSTANCE});
    private static final Set<FirExpressionChecker<FirLoop>> loopExpressionCheckers = SetsKt.setOf(new FirExpressionChecker[]{FirLoopConditionChecker.INSTANCE, FirForLoopStatementAssignmentChecker.INSTANCE});
    private static final Set<FirExpressionChecker<FirLoopJump>> loopJumpCheckers = SetsKt.setOf(FirBreakOrContinueJumpsAcrossFunctionBoundaryChecker.INSTANCE);
    private static final Set<FirExpressionChecker<FirBooleanOperatorExpression>> booleanOperatorExpressionCheckers = SetsKt.setOf(FirLogicExpressionTypeChecker.INSTANCE);
    private static final Set<FirExpressionChecker<FirReturnExpression>> returnExpressionCheckers = SetsKt.setOf(new FirExpressionChecker[]{FirReturnSyntaxAndLabelChecker.INSTANCE, FirFunctionReturnTypeMismatchChecker.INSTANCE});
    private static final Set<FirExpressionChecker<FirBlock>> blockCheckers = SetsKt.setOf(new FirExpressionChecker[]{FirForLoopChecker.INSTANCE, FirConflictsExpressionChecker.INSTANCE, FirSingleNamedFunctionChecker.INSTANCE});
    private static final Set<FirExpressionChecker<FirCheckNotNullCall>> checkNotNullCallCheckers = SetsKt.setOf(FirNotNullAssertionChecker.INSTANCE);
    private static final Set<FirExpressionChecker<FirElvisExpression>> elvisExpressionCheckers = SetsKt.setOf(FirUselessElvisChecker.INSTANCE);
    private static final Set<FirExpressionChecker<FirGetClassCall>> getClassCallCheckers = SetsKt.setOf(FirClassLiteralChecker.INSTANCE);
    private static final Set<FirExpressionChecker<FirSafeCallExpression>> safeCallExpressionCheckers = SetsKt.setOf(FirUnnecessarySafeCallChecker.INSTANCE);
    private static final Set<FirExpressionChecker<FirSmartCastExpression>> smartCastExpressionCheckers = SetsKt.setOf(FirDeprecatedSmartCastChecker.INSTANCE);
    private static final Set<FirExpressionChecker<FirTypeOperatorCall>> typeOperatorCallCheckers = SetsKt.setOf(new FirExpressionChecker[]{FirCastOperatorsChecker.INSTANCE, FirContextSensitiveResolutionAmbiguityCheckerForTypeOperators.INSTANCE});
    private static final Set<FirExpressionChecker<FirResolvedQualifier>> resolvedQualifierCheckers = SetsKt.setOf(new FirExpressionChecker[]{FirKotlinActualAnnotationHasNoEffectInKotlinExpressionChecker.ResolvedQualifier.INSTANCE, FirStandaloneQualifierChecker.INSTANCE, FirPackageOnLhsQualifierChecker.INSTANCE, FirOptInUsageQualifierChecker.INSTANCE, FirDeprecatedQualifierChecker.INSTANCE, FirVisibilityQualifierChecker.INSTANCE, FirInlineBodyResolvedQualifierChecker.INSTANCE, FirCustomEnumEntriesMigrationQualifierChecker.INSTANCE, FirQualifierWithTypeArgumentsChecker.INSTANCE, FirRootIdePackageDeprecatedInCliQualifierChecker.INSTANCE});
    private static final Set<FirExpressionChecker<FirEqualityOperatorCall>> equalityOperatorCallCheckers = SetsKt.setOf(new FirExpressionChecker[]{FirEqualityCompatibilityChecker.INSTANCE, FirContextSensitiveResolutionAmbiguityCheckerForEqualities.INSTANCE});
    private static final Set<FirExpressionChecker<FirCollectionLiteral>> collectionLiteralCheckers = SetsKt.setOf(FirUnsupportedArrayLiteralChecker.INSTANCE);
    private static final Set<FirExpressionChecker<FirInaccessibleReceiverExpression>> inaccessibleReceiverCheckers = SetsKt.setOf(FirReceiverAccessBeforeSuperCallChecker.INSTANCE);
    private static final Set<FirExpressionChecker<FirCallableReferenceAccess>> callableReferenceAccessCheckers = SetsKt.setOf(new FirExpressionChecker[]{FirKotlinActualAnnotationHasNoEffectInKotlinExpressionChecker.CallableReference.INSTANCE, FirTypeArgumentsOfQualifierOfCallableReferenceChecker.INSTANCE, FirCustomEnumEntriesMigrationReferenceChecker.INSTANCE});
    private static final Set<FirExpressionChecker<FirStringConcatenationCall>> stringConcatenationCallCheckers = SetsKt.setOf(FirMultiDollarInterpolationCheckerConcatenation.INSTANCE);
    private static final Set<FirExpressionChecker<FirLiteralExpression>> literalExpressionCheckers = SetsKt.setOf(FirMultiDollarInterpolationCheckerLiteral.INSTANCE);
    private static final Set<FirExpressionChecker<FirThisReceiverExpression>> thisReceiverExpressionCheckers = SetsKt.setOf(FirInlineExposedLessVisibleThisReceiverChecker.INSTANCE);

    private CommonExpressionCheckers() {
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirAnnotationCall>> getAnnotationCallCheckers() {
        return annotationCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirAnnotation>> getAnnotationCheckers() {
        return annotationCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirStatement>> getBasicExpressionCheckers() {
        return basicExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirBlock>> getBlockCheckers() {
        return blockCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirBooleanOperatorExpression>> getBooleanOperatorExpressionCheckers() {
        return booleanOperatorExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirCall>> getCallCheckers() {
        return callCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirCallableReferenceAccess>> getCallableReferenceAccessCheckers() {
        return callableReferenceAccessCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirCheckNotNullCall>> getCheckNotNullCallCheckers() {
        return checkNotNullCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirCollectionLiteral>> getCollectionLiteralCheckers() {
        return collectionLiteralCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirElvisExpression>> getElvisExpressionCheckers() {
        return elvisExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirEqualityOperatorCall>> getEqualityOperatorCallCheckers() {
        return equalityOperatorCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirFunctionCall>> getFunctionCallCheckers() {
        return functionCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirGetClassCall>> getGetClassCallCheckers() {
        return getClassCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirInaccessibleReceiverExpression>> getInaccessibleReceiverCheckers() {
        return inaccessibleReceiverCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirLiteralExpression>> getLiteralExpressionCheckers() {
        return literalExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirLoop>> getLoopExpressionCheckers() {
        return loopExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirLoopJump>> getLoopJumpCheckers() {
        return loopJumpCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirPropertyAccessExpression>> getPropertyAccessExpressionCheckers() {
        return propertyAccessExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirQualifiedAccessExpression>> getQualifiedAccessExpressionCheckers() {
        return qualifiedAccessExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirResolvedQualifier>> getResolvedQualifierCheckers() {
        return resolvedQualifierCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirReturnExpression>> getReturnExpressionCheckers() {
        return returnExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirSafeCallExpression>> getSafeCallExpressionCheckers() {
        return safeCallExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirSmartCastExpression>> getSmartCastExpressionCheckers() {
        return smartCastExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirStringConcatenationCall>> getStringConcatenationCallCheckers() {
        return stringConcatenationCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirThisReceiverExpression>> getThisReceiverExpressionCheckers() {
        return thisReceiverExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirThrowExpression>> getThrowExpressionCheckers() {
        return throwExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirTryExpression>> getTryExpressionCheckers() {
        return tryExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirTypeOperatorCall>> getTypeOperatorCallCheckers() {
        return typeOperatorCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirVariableAssignment>> getVariableAssignmentCheckers() {
        return variableAssignmentCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirWhenExpression>> getWhenExpressionCheckers() {
        return whenExpressionCheckers;
    }
}
