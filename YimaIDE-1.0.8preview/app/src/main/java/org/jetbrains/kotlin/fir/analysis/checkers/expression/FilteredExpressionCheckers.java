package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirBooleanOperatorExpression;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCheckNotNullCall;
import org.jetbrains.kotlin.fir.expressions.FirClassReferenceExpression;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirDoWhileLoop;
import org.jetbrains.kotlin.fir.expressions.FirElvisExpression;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirInaccessibleReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirIntegerLiteralOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirLoop;
import org.jetbrains.kotlin.fir.expressions.FirLoopJump;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirReplDeclarationReference;
import org.jetbrains.kotlin.fir.expressions.FirReplExpressionReference;
import org.jetbrains.kotlin.fir.expressions.FirReplPropertyDelegate;
import org.jetbrains.kotlin.fir.expressions.FirReplPropertyInitializer;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirStringConcatenationCall;
import org.jetbrains.kotlin.fir.expressions.FirSuperReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirThrowExpression;
import org.jetbrains.kotlin.fir.expressions.FirTryExpression;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhileLoop;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ô\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0016\u0010\u0003\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0001¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR!\u0010\u0003\u001a\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0005\u0012\u0004\u0012\u00020\u00060\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR$\u0010\r\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u000f0\u0005j\u0002`\u00100\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R$\u0010\u0013\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00140\u0005j\u0002`\u00150\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R$\u0010\u0017\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00180\u0005j\u0002`\u00190\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0012R$\u0010\u001b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u001c0\u0005j\u0002`\u001d0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u0012R$\u0010\u001f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020 0\u0005j\u0002`!0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\u0012R$\u0010#\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020$0\u0005j\u0002`%0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\u0012R$\u0010'\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020(0\u0005j\u0002`)0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\u0012R$\u0010+\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020,0\u0005j\u0002`-0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\u0012R$\u0010/\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002000\u0005j\u0002`10\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\u0012R$\u00103\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002040\u0005j\u0002`50\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\u0012R$\u00107\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002080\u0005j\u0002`90\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\u0012R$\u0010;\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020<0\u0005j\u0002`=0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\u0012R$\u0010?\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020@0\u0005j\u0002`A0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\u0012R$\u0010C\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020D0\u0005j\u0002`E0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\u0012R$\u0010G\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020H0\u0005j\u0002`I0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010\u0012R$\u0010K\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020L0\u0005j\u0002`M0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bN\u0010\u0012R$\u0010O\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020P0\u0005j\u0002`Q0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bR\u0010\u0012R$\u0010S\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020T0\u0005j\u0002`U0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bV\u0010\u0012R$\u0010W\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020X0\u0005j\u0002`Y0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010\u0012R$\u0010[\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\\0\u0005j\u0002`]0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b^\u0010\u0012R$\u0010_\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020`0\u0005j\u0002`a0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bb\u0010\u0012R$\u0010c\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020d0\u0005j\u0002`e0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bf\u0010\u0012R$\u0010g\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020h0\u0005j\u0002`i0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bj\u0010\u0012R$\u0010k\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020l0\u0005j\u0002`m0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bn\u0010\u0012R$\u0010o\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020p0\u0005j\u0002`q0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\br\u0010\u0012R$\u0010s\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020t0\u0005j\u0002`u0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bv\u0010\u0012R$\u0010w\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020x0\u0005j\u0002`y0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bz\u0010\u0012R$\u0010{\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020|0\u0005j\u0002`}0\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b~\u0010\u0012R'\u0010\u007f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0080\u00010\u0005j\u0003`\u0081\u00010\u000eX\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0082\u0001\u0010\u0012R(\u0010\u0083\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0084\u00010\u0005j\u0003`\u0085\u00010\u000eX\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0086\u0001\u0010\u0012R(\u0010\u0087\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0088\u00010\u0005j\u0003`\u0089\u00010\u000eX\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u008a\u0001\u0010\u0012R(\u0010\u008b\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u008c\u00010\u0005j\u0003`\u008d\u00010\u000eX\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u008e\u0001\u0010\u0012R(\u0010\u008f\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0090\u00010\u0005j\u0003`\u0091\u00010\u000eX\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0092\u0001\u0010\u0012R(\u0010\u0093\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0094\u00010\u0005j\u0003`\u0095\u00010\u000eX\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0096\u0001\u0010\u0012R(\u0010\u0097\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0098\u00010\u0005j\u0003`\u0099\u00010\u000eX\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u009a\u0001\u0010\u0012R(\u0010\u009b\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u009c\u00010\u0005j\u0003`\u009d\u00010\u000eX\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u009e\u0001\u0010\u0012R(\u0010\u009f\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030 \u00010\u0005j\u0003`¡\u00010\u000eX\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b¢\u0001\u0010\u0012R(\u0010£\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030¤\u00010\u0005j\u0003`¥\u00010\u000eX\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b¦\u0001\u0010\u0012R(\u0010§\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030¨\u00010\u0005j\u0003`©\u00010\u000eX\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\bª\u0001\u0010\u0012¨\u0006«\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FilteredExpressionCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ExpressionCheckers;", "delegate", "predicate", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ExpressionCheckers;Lkotlin/jvm/functions/Function1;)V", "getDelegate", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ExpressionCheckers;", "getPredicate", "()Lkotlin/jvm/functions/Function1;", "basicExpressionCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBasicExpressionChecker;", "getBasicExpressionCheckers", "()Ljava/util/Set;", "qualifiedAccessExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "getQualifiedAccessExpressionCheckers", "callCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCallChecker;", "getCallCheckers", "functionCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "getFunctionCallCheckers", "propertyAccessExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirPropertyAccessExpressionChecker;", "getPropertyAccessExpressionCheckers", "superReceiverExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirSuperReceiverExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirSuperReceiverExpressionChecker;", "getSuperReceiverExpressionCheckers", "integerLiteralOperatorCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirIntegerLiteralOperatorCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirIntegerLiteralOperatorCallChecker;", "getIntegerLiteralOperatorCallCheckers", "variableAssignmentCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirVariableAssignmentChecker;", "getVariableAssignmentCheckers", "tryExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirTryExpressionChecker;", "getTryExpressionCheckers", "whenExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirWhenExpressionChecker;", "getWhenExpressionCheckers", "loopExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirLoop;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirLoopExpressionChecker;", "getLoopExpressionCheckers", "loopJumpCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirLoopJump;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirLoopJumpChecker;", "getLoopJumpCheckers", "booleanOperatorExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBooleanOperatorExpressionChecker;", "getBooleanOperatorExpressionCheckers", "returnExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReturnExpressionChecker;", "getReturnExpressionCheckers", "blockCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBlockChecker;", "getBlockCheckers", "replDeclarationReferenceCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirReplDeclarationReference;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReplDeclarationReferenceChecker;", "getReplDeclarationReferenceCheckers", "replPropertyInitializerCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyInitializer;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReplPropertyInitializerChecker;", "getReplPropertyInitializerCheckers", "replPropertyDelegateCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyDelegate;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReplPropertyDelegateChecker;", "getReplPropertyDelegateCheckers", "replExpressionReferenceCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirReplExpressionReference;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReplExpressionReferenceChecker;", "getReplExpressionReferenceCheckers", "annotationCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirAnnotationChecker;", "getAnnotationCheckers", "annotationCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirAnnotationCallChecker;", "getAnnotationCallCheckers", "checkNotNullCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckNotNullCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCheckNotNullCallChecker;", "getCheckNotNullCallCheckers", "elvisExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirElvisExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirElvisExpressionChecker;", "getElvisExpressionCheckers", "getClassCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirGetClassCallChecker;", "getGetClassCallCheckers", "safeCallExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirSafeCallExpressionChecker;", "getSafeCallExpressionCheckers", "smartCastExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirSmartCastExpressionChecker;", "getSmartCastExpressionCheckers", "equalityOperatorCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirEqualityOperatorCallChecker;", "getEqualityOperatorCallCheckers", "stringConcatenationCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirStringConcatenationCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirStringConcatenationCallChecker;", "getStringConcatenationCallCheckers", "typeOperatorCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirTypeOperatorCallChecker;", "getTypeOperatorCallCheckers", "resolvedQualifierCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirResolvedQualifierChecker;", "getResolvedQualifierCheckers", "literalExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirLiteralExpressionChecker;", "getLiteralExpressionCheckers", "callableReferenceAccessCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCallableReferenceAccessChecker;", "getCallableReferenceAccessCheckers", "thisReceiverExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirThisReceiverExpressionChecker;", "getThisReceiverExpressionCheckers", "whileLoopCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirWhileLoop;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirWhileLoopChecker;", "getWhileLoopCheckers", "throwExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirThrowExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirThrowExpressionChecker;", "getThrowExpressionCheckers", "doWhileLoopCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirDoWhileLoop;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirDoWhileLoopChecker;", "getDoWhileLoopCheckers", "collectionLiteralCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCollectionLiteralChecker;", "getCollectionLiteralCheckers", "classReferenceExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirClassReferenceExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirClassReferenceExpressionChecker;", "getClassReferenceExpressionCheckers", "inaccessibleReceiverCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirInaccessibleReceiverExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirInaccessibleReceiverChecker;", "getInaccessibleReceiverCheckers", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FilteredExpressionCheckers extends ExpressionCheckers {
    private final Set<FirExpressionChecker<FirAnnotationCall>> annotationCallCheckers;
    private final Set<FirExpressionChecker<FirAnnotation>> annotationCheckers;
    private final Set<FirExpressionChecker<FirStatement>> basicExpressionCheckers;
    private final Set<FirExpressionChecker<FirBlock>> blockCheckers;
    private final Set<FirExpressionChecker<FirBooleanOperatorExpression>> booleanOperatorExpressionCheckers;
    private final Set<FirExpressionChecker<FirCall>> callCheckers;
    private final Set<FirExpressionChecker<FirCallableReferenceAccess>> callableReferenceAccessCheckers;
    private final Set<FirExpressionChecker<FirCheckNotNullCall>> checkNotNullCallCheckers;
    private final Set<FirExpressionChecker<FirClassReferenceExpression>> classReferenceExpressionCheckers;
    private final Set<FirExpressionChecker<FirCollectionLiteral>> collectionLiteralCheckers;
    private final ExpressionCheckers delegate;
    private final Set<FirExpressionChecker<FirDoWhileLoop>> doWhileLoopCheckers;
    private final Set<FirExpressionChecker<FirElvisExpression>> elvisExpressionCheckers;
    private final Set<FirExpressionChecker<FirEqualityOperatorCall>> equalityOperatorCallCheckers;
    private final Set<FirExpressionChecker<FirFunctionCall>> functionCallCheckers;
    private final Set<FirExpressionChecker<FirGetClassCall>> getClassCallCheckers;
    private final Set<FirExpressionChecker<FirInaccessibleReceiverExpression>> inaccessibleReceiverCheckers;
    private final Set<FirExpressionChecker<FirIntegerLiteralOperatorCall>> integerLiteralOperatorCallCheckers;
    private final Set<FirExpressionChecker<FirLiteralExpression>> literalExpressionCheckers;
    private final Set<FirExpressionChecker<FirLoop>> loopExpressionCheckers;
    private final Set<FirExpressionChecker<FirLoopJump>> loopJumpCheckers;
    private final Function1<FirExpressionChecker<?>, Boolean> predicate;
    private final Set<FirExpressionChecker<FirPropertyAccessExpression>> propertyAccessExpressionCheckers;
    private final Set<FirExpressionChecker<FirQualifiedAccessExpression>> qualifiedAccessExpressionCheckers;
    private final Set<FirExpressionChecker<FirReplDeclarationReference>> replDeclarationReferenceCheckers;
    private final Set<FirExpressionChecker<FirReplExpressionReference>> replExpressionReferenceCheckers;
    private final Set<FirExpressionChecker<FirReplPropertyDelegate>> replPropertyDelegateCheckers;
    private final Set<FirExpressionChecker<FirReplPropertyInitializer>> replPropertyInitializerCheckers;
    private final Set<FirExpressionChecker<FirResolvedQualifier>> resolvedQualifierCheckers;
    private final Set<FirExpressionChecker<FirReturnExpression>> returnExpressionCheckers;
    private final Set<FirExpressionChecker<FirSafeCallExpression>> safeCallExpressionCheckers;
    private final Set<FirExpressionChecker<FirSmartCastExpression>> smartCastExpressionCheckers;
    private final Set<FirExpressionChecker<FirStringConcatenationCall>> stringConcatenationCallCheckers;
    private final Set<FirExpressionChecker<FirSuperReceiverExpression>> superReceiverExpressionCheckers;
    private final Set<FirExpressionChecker<FirThisReceiverExpression>> thisReceiverExpressionCheckers;
    private final Set<FirExpressionChecker<FirThrowExpression>> throwExpressionCheckers;
    private final Set<FirExpressionChecker<FirTryExpression>> tryExpressionCheckers;
    private final Set<FirExpressionChecker<FirTypeOperatorCall>> typeOperatorCallCheckers;
    private final Set<FirExpressionChecker<FirVariableAssignment>> variableAssignmentCheckers;
    private final Set<FirExpressionChecker<FirWhenExpression>> whenExpressionCheckers;
    private final Set<FirExpressionChecker<FirWhileLoop>> whileLoopCheckers;

    /* JADX WARN: Multi-variable type inference failed */
    public FilteredExpressionCheckers(ExpressionCheckers expressionCheckers, Function1<? super FirExpressionChecker<?>, Boolean> function1) {
        expressionCheckers.getClass();
        function1.getClass();
        this.delegate = expressionCheckers;
        this.predicate = function1;
        Set<FirExpressionChecker<FirStatement>> basicExpressionCheckers = expressionCheckers.getBasicExpressionCheckers();
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        for (Object obj : basicExpressionCheckers) {
            if (((Boolean) function1.invoke(obj)).booleanValue()) {
                linkedHashSet.add(obj);
            }
        }
        this.basicExpressionCheckers = linkedHashSet;
        Set<FirExpressionChecker<FirQualifiedAccessExpression>> qualifiedAccessExpressionCheckers = this.delegate.getQualifiedAccessExpressionCheckers();
        LinkedHashSet linkedHashSet2 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function2 = this.predicate;
        for (Object obj2 : qualifiedAccessExpressionCheckers) {
            if (((Boolean) function2.invoke(obj2)).booleanValue()) {
                linkedHashSet2.add(obj2);
            }
        }
        this.qualifiedAccessExpressionCheckers = linkedHashSet2;
        Set<FirExpressionChecker<FirCall>> callCheckers = this.delegate.getCallCheckers();
        LinkedHashSet linkedHashSet3 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function3 = this.predicate;
        for (Object obj3 : callCheckers) {
            if (((Boolean) function3.invoke(obj3)).booleanValue()) {
                linkedHashSet3.add(obj3);
            }
        }
        this.callCheckers = linkedHashSet3;
        Set<FirExpressionChecker<FirFunctionCall>> functionCallCheckers = this.delegate.getFunctionCallCheckers();
        LinkedHashSet linkedHashSet4 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function4 = this.predicate;
        for (Object obj4 : functionCallCheckers) {
            if (((Boolean) function4.invoke(obj4)).booleanValue()) {
                linkedHashSet4.add(obj4);
            }
        }
        this.functionCallCheckers = linkedHashSet4;
        Set<FirExpressionChecker<FirPropertyAccessExpression>> propertyAccessExpressionCheckers = this.delegate.getPropertyAccessExpressionCheckers();
        LinkedHashSet linkedHashSet5 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function5 = this.predicate;
        for (Object obj5 : propertyAccessExpressionCheckers) {
            if (((Boolean) function5.invoke(obj5)).booleanValue()) {
                linkedHashSet5.add(obj5);
            }
        }
        this.propertyAccessExpressionCheckers = linkedHashSet5;
        Set<FirExpressionChecker<FirSuperReceiverExpression>> superReceiverExpressionCheckers = this.delegate.getSuperReceiverExpressionCheckers();
        LinkedHashSet linkedHashSet6 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function6 = this.predicate;
        for (Object obj6 : superReceiverExpressionCheckers) {
            if (((Boolean) function6.invoke(obj6)).booleanValue()) {
                linkedHashSet6.add(obj6);
            }
        }
        this.superReceiverExpressionCheckers = linkedHashSet6;
        Set<FirExpressionChecker<FirIntegerLiteralOperatorCall>> integerLiteralOperatorCallCheckers = this.delegate.getIntegerLiteralOperatorCallCheckers();
        LinkedHashSet linkedHashSet7 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function7 = this.predicate;
        for (Object obj7 : integerLiteralOperatorCallCheckers) {
            if (((Boolean) function7.invoke(obj7)).booleanValue()) {
                linkedHashSet7.add(obj7);
            }
        }
        this.integerLiteralOperatorCallCheckers = linkedHashSet7;
        Set<FirExpressionChecker<FirVariableAssignment>> variableAssignmentCheckers = this.delegate.getVariableAssignmentCheckers();
        LinkedHashSet linkedHashSet8 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function8 = this.predicate;
        for (Object obj8 : variableAssignmentCheckers) {
            if (((Boolean) function8.invoke(obj8)).booleanValue()) {
                linkedHashSet8.add(obj8);
            }
        }
        this.variableAssignmentCheckers = linkedHashSet8;
        Set<FirExpressionChecker<FirTryExpression>> tryExpressionCheckers = this.delegate.getTryExpressionCheckers();
        LinkedHashSet linkedHashSet9 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function9 = this.predicate;
        for (Object obj9 : tryExpressionCheckers) {
            if (((Boolean) function9.invoke(obj9)).booleanValue()) {
                linkedHashSet9.add(obj9);
            }
        }
        this.tryExpressionCheckers = linkedHashSet9;
        Set<FirExpressionChecker<FirWhenExpression>> whenExpressionCheckers = this.delegate.getWhenExpressionCheckers();
        LinkedHashSet linkedHashSet10 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function10 = this.predicate;
        for (Object obj10 : whenExpressionCheckers) {
            if (((Boolean) function10.invoke(obj10)).booleanValue()) {
                linkedHashSet10.add(obj10);
            }
        }
        this.whenExpressionCheckers = linkedHashSet10;
        Set<FirExpressionChecker<FirLoop>> loopExpressionCheckers = this.delegate.getLoopExpressionCheckers();
        LinkedHashSet linkedHashSet11 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function11 = this.predicate;
        for (Object obj11 : loopExpressionCheckers) {
            if (((Boolean) function11.invoke(obj11)).booleanValue()) {
                linkedHashSet11.add(obj11);
            }
        }
        this.loopExpressionCheckers = linkedHashSet11;
        Set<FirExpressionChecker<FirLoopJump>> loopJumpCheckers = this.delegate.getLoopJumpCheckers();
        LinkedHashSet linkedHashSet12 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function12 = this.predicate;
        for (Object obj12 : loopJumpCheckers) {
            if (((Boolean) function12.invoke(obj12)).booleanValue()) {
                linkedHashSet12.add(obj12);
            }
        }
        this.loopJumpCheckers = linkedHashSet12;
        Set<FirExpressionChecker<FirBooleanOperatorExpression>> booleanOperatorExpressionCheckers = this.delegate.getBooleanOperatorExpressionCheckers();
        LinkedHashSet linkedHashSet13 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function13 = this.predicate;
        for (Object obj13 : booleanOperatorExpressionCheckers) {
            if (((Boolean) function13.invoke(obj13)).booleanValue()) {
                linkedHashSet13.add(obj13);
            }
        }
        this.booleanOperatorExpressionCheckers = linkedHashSet13;
        Set<FirExpressionChecker<FirReturnExpression>> returnExpressionCheckers = this.delegate.getReturnExpressionCheckers();
        LinkedHashSet linkedHashSet14 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function14 = this.predicate;
        for (Object obj14 : returnExpressionCheckers) {
            if (((Boolean) function14.invoke(obj14)).booleanValue()) {
                linkedHashSet14.add(obj14);
            }
        }
        this.returnExpressionCheckers = linkedHashSet14;
        Set<FirExpressionChecker<FirBlock>> blockCheckers = this.delegate.getBlockCheckers();
        LinkedHashSet linkedHashSet15 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function15 = this.predicate;
        for (Object obj15 : blockCheckers) {
            if (((Boolean) function15.invoke(obj15)).booleanValue()) {
                linkedHashSet15.add(obj15);
            }
        }
        this.blockCheckers = linkedHashSet15;
        Set<FirExpressionChecker<FirReplDeclarationReference>> replDeclarationReferenceCheckers = this.delegate.getReplDeclarationReferenceCheckers();
        LinkedHashSet linkedHashSet16 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function16 = this.predicate;
        for (Object obj16 : replDeclarationReferenceCheckers) {
            if (((Boolean) function16.invoke(obj16)).booleanValue()) {
                linkedHashSet16.add(obj16);
            }
        }
        this.replDeclarationReferenceCheckers = linkedHashSet16;
        Set<FirExpressionChecker<FirReplPropertyInitializer>> replPropertyInitializerCheckers = this.delegate.getReplPropertyInitializerCheckers();
        LinkedHashSet linkedHashSet17 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function17 = this.predicate;
        for (Object obj17 : replPropertyInitializerCheckers) {
            if (((Boolean) function17.invoke(obj17)).booleanValue()) {
                linkedHashSet17.add(obj17);
            }
        }
        this.replPropertyInitializerCheckers = linkedHashSet17;
        Set<FirExpressionChecker<FirReplPropertyDelegate>> replPropertyDelegateCheckers = this.delegate.getReplPropertyDelegateCheckers();
        LinkedHashSet linkedHashSet18 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function18 = this.predicate;
        for (Object obj18 : replPropertyDelegateCheckers) {
            if (((Boolean) function18.invoke(obj18)).booleanValue()) {
                linkedHashSet18.add(obj18);
            }
        }
        this.replPropertyDelegateCheckers = linkedHashSet18;
        Set<FirExpressionChecker<FirReplExpressionReference>> replExpressionReferenceCheckers = this.delegate.getReplExpressionReferenceCheckers();
        LinkedHashSet linkedHashSet19 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function19 = this.predicate;
        for (Object obj19 : replExpressionReferenceCheckers) {
            if (((Boolean) function19.invoke(obj19)).booleanValue()) {
                linkedHashSet19.add(obj19);
            }
        }
        this.replExpressionReferenceCheckers = linkedHashSet19;
        Set<FirExpressionChecker<FirAnnotation>> annotationCheckers = this.delegate.getAnnotationCheckers();
        LinkedHashSet linkedHashSet20 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function20 = this.predicate;
        for (Object obj20 : annotationCheckers) {
            if (((Boolean) function20.invoke(obj20)).booleanValue()) {
                linkedHashSet20.add(obj20);
            }
        }
        this.annotationCheckers = linkedHashSet20;
        Set<FirExpressionChecker<FirAnnotationCall>> annotationCallCheckers = this.delegate.getAnnotationCallCheckers();
        LinkedHashSet linkedHashSet21 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function21 = this.predicate;
        for (Object obj21 : annotationCallCheckers) {
            if (((Boolean) function21.invoke(obj21)).booleanValue()) {
                linkedHashSet21.add(obj21);
            }
        }
        this.annotationCallCheckers = linkedHashSet21;
        Set<FirExpressionChecker<FirCheckNotNullCall>> checkNotNullCallCheckers = this.delegate.getCheckNotNullCallCheckers();
        LinkedHashSet linkedHashSet22 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function22 = this.predicate;
        for (Object obj22 : checkNotNullCallCheckers) {
            if (((Boolean) function22.invoke(obj22)).booleanValue()) {
                linkedHashSet22.add(obj22);
            }
        }
        this.checkNotNullCallCheckers = linkedHashSet22;
        Set<FirExpressionChecker<FirElvisExpression>> elvisExpressionCheckers = this.delegate.getElvisExpressionCheckers();
        LinkedHashSet linkedHashSet23 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function23 = this.predicate;
        for (Object obj23 : elvisExpressionCheckers) {
            if (((Boolean) function23.invoke(obj23)).booleanValue()) {
                linkedHashSet23.add(obj23);
            }
        }
        this.elvisExpressionCheckers = linkedHashSet23;
        Set<FirExpressionChecker<FirGetClassCall>> getClassCallCheckers = this.delegate.getGetClassCallCheckers();
        LinkedHashSet linkedHashSet24 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function24 = this.predicate;
        for (Object obj24 : getClassCallCheckers) {
            if (((Boolean) function24.invoke(obj24)).booleanValue()) {
                linkedHashSet24.add(obj24);
            }
        }
        this.getClassCallCheckers = linkedHashSet24;
        Set<FirExpressionChecker<FirSafeCallExpression>> safeCallExpressionCheckers = this.delegate.getSafeCallExpressionCheckers();
        LinkedHashSet linkedHashSet25 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function25 = this.predicate;
        for (Object obj25 : safeCallExpressionCheckers) {
            if (((Boolean) function25.invoke(obj25)).booleanValue()) {
                linkedHashSet25.add(obj25);
            }
        }
        this.safeCallExpressionCheckers = linkedHashSet25;
        Set<FirExpressionChecker<FirSmartCastExpression>> smartCastExpressionCheckers = this.delegate.getSmartCastExpressionCheckers();
        LinkedHashSet linkedHashSet26 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function26 = this.predicate;
        for (Object obj26 : smartCastExpressionCheckers) {
            if (((Boolean) function26.invoke(obj26)).booleanValue()) {
                linkedHashSet26.add(obj26);
            }
        }
        this.smartCastExpressionCheckers = linkedHashSet26;
        Set<FirExpressionChecker<FirEqualityOperatorCall>> equalityOperatorCallCheckers = this.delegate.getEqualityOperatorCallCheckers();
        LinkedHashSet linkedHashSet27 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function27 = this.predicate;
        for (Object obj27 : equalityOperatorCallCheckers) {
            if (((Boolean) function27.invoke(obj27)).booleanValue()) {
                linkedHashSet27.add(obj27);
            }
        }
        this.equalityOperatorCallCheckers = linkedHashSet27;
        Set<FirExpressionChecker<FirStringConcatenationCall>> stringConcatenationCallCheckers = this.delegate.getStringConcatenationCallCheckers();
        LinkedHashSet linkedHashSet28 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function28 = this.predicate;
        for (Object obj28 : stringConcatenationCallCheckers) {
            if (((Boolean) function28.invoke(obj28)).booleanValue()) {
                linkedHashSet28.add(obj28);
            }
        }
        this.stringConcatenationCallCheckers = linkedHashSet28;
        Set<FirExpressionChecker<FirTypeOperatorCall>> typeOperatorCallCheckers = this.delegate.getTypeOperatorCallCheckers();
        LinkedHashSet linkedHashSet29 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function29 = this.predicate;
        for (Object obj29 : typeOperatorCallCheckers) {
            if (((Boolean) function29.invoke(obj29)).booleanValue()) {
                linkedHashSet29.add(obj29);
            }
        }
        this.typeOperatorCallCheckers = linkedHashSet29;
        Set<FirExpressionChecker<FirResolvedQualifier>> resolvedQualifierCheckers = this.delegate.getResolvedQualifierCheckers();
        LinkedHashSet linkedHashSet30 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function30 = this.predicate;
        for (Object obj30 : resolvedQualifierCheckers) {
            if (((Boolean) function30.invoke(obj30)).booleanValue()) {
                linkedHashSet30.add(obj30);
            }
        }
        this.resolvedQualifierCheckers = linkedHashSet30;
        Set<FirExpressionChecker<FirLiteralExpression>> literalExpressionCheckers = this.delegate.getLiteralExpressionCheckers();
        LinkedHashSet linkedHashSet31 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function31 = this.predicate;
        for (Object obj31 : literalExpressionCheckers) {
            if (((Boolean) function31.invoke(obj31)).booleanValue()) {
                linkedHashSet31.add(obj31);
            }
        }
        this.literalExpressionCheckers = linkedHashSet31;
        Set<FirExpressionChecker<FirCallableReferenceAccess>> callableReferenceAccessCheckers = this.delegate.getCallableReferenceAccessCheckers();
        LinkedHashSet linkedHashSet32 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function32 = this.predicate;
        for (Object obj32 : callableReferenceAccessCheckers) {
            if (((Boolean) function32.invoke(obj32)).booleanValue()) {
                linkedHashSet32.add(obj32);
            }
        }
        this.callableReferenceAccessCheckers = linkedHashSet32;
        Set<FirExpressionChecker<FirThisReceiverExpression>> thisReceiverExpressionCheckers = this.delegate.getThisReceiverExpressionCheckers();
        LinkedHashSet linkedHashSet33 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function33 = this.predicate;
        for (Object obj33 : thisReceiverExpressionCheckers) {
            if (((Boolean) function33.invoke(obj33)).booleanValue()) {
                linkedHashSet33.add(obj33);
            }
        }
        this.thisReceiverExpressionCheckers = linkedHashSet33;
        Set<FirExpressionChecker<FirWhileLoop>> whileLoopCheckers = this.delegate.getWhileLoopCheckers();
        LinkedHashSet linkedHashSet34 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function34 = this.predicate;
        for (Object obj34 : whileLoopCheckers) {
            if (((Boolean) function34.invoke(obj34)).booleanValue()) {
                linkedHashSet34.add(obj34);
            }
        }
        this.whileLoopCheckers = linkedHashSet34;
        Set<FirExpressionChecker<FirThrowExpression>> throwExpressionCheckers = this.delegate.getThrowExpressionCheckers();
        LinkedHashSet linkedHashSet35 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function35 = this.predicate;
        for (Object obj35 : throwExpressionCheckers) {
            if (((Boolean) function35.invoke(obj35)).booleanValue()) {
                linkedHashSet35.add(obj35);
            }
        }
        this.throwExpressionCheckers = linkedHashSet35;
        Set<FirExpressionChecker<FirDoWhileLoop>> doWhileLoopCheckers = this.delegate.getDoWhileLoopCheckers();
        LinkedHashSet linkedHashSet36 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function36 = this.predicate;
        for (Object obj36 : doWhileLoopCheckers) {
            if (((Boolean) function36.invoke(obj36)).booleanValue()) {
                linkedHashSet36.add(obj36);
            }
        }
        this.doWhileLoopCheckers = linkedHashSet36;
        Set<FirExpressionChecker<FirCollectionLiteral>> collectionLiteralCheckers = this.delegate.getCollectionLiteralCheckers();
        LinkedHashSet linkedHashSet37 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function37 = this.predicate;
        for (Object obj37 : collectionLiteralCheckers) {
            if (((Boolean) function37.invoke(obj37)).booleanValue()) {
                linkedHashSet37.add(obj37);
            }
        }
        this.collectionLiteralCheckers = linkedHashSet37;
        Set<FirExpressionChecker<FirClassReferenceExpression>> classReferenceExpressionCheckers = this.delegate.getClassReferenceExpressionCheckers();
        LinkedHashSet linkedHashSet38 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function38 = this.predicate;
        for (Object obj38 : classReferenceExpressionCheckers) {
            if (((Boolean) function38.invoke(obj38)).booleanValue()) {
                linkedHashSet38.add(obj38);
            }
        }
        this.classReferenceExpressionCheckers = linkedHashSet38;
        Set<FirExpressionChecker<FirInaccessibleReceiverExpression>> inaccessibleReceiverCheckers = this.delegate.getInaccessibleReceiverCheckers();
        LinkedHashSet linkedHashSet39 = new LinkedHashSet();
        Function1<FirExpressionChecker<?>, Boolean> function39 = this.predicate;
        for (Object obj39 : inaccessibleReceiverCheckers) {
            if (((Boolean) function39.invoke(obj39)).booleanValue()) {
                linkedHashSet39.add(obj39);
            }
        }
        this.inaccessibleReceiverCheckers = linkedHashSet39;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirAnnotationCall>> getAnnotationCallCheckers() {
        return this.annotationCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirAnnotation>> getAnnotationCheckers() {
        return this.annotationCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirStatement>> getBasicExpressionCheckers() {
        return this.basicExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirBlock>> getBlockCheckers() {
        return this.blockCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirBooleanOperatorExpression>> getBooleanOperatorExpressionCheckers() {
        return this.booleanOperatorExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirCall>> getCallCheckers() {
        return this.callCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirCallableReferenceAccess>> getCallableReferenceAccessCheckers() {
        return this.callableReferenceAccessCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirCheckNotNullCall>> getCheckNotNullCallCheckers() {
        return this.checkNotNullCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirClassReferenceExpression>> getClassReferenceExpressionCheckers() {
        return this.classReferenceExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirCollectionLiteral>> getCollectionLiteralCheckers() {
        return this.collectionLiteralCheckers;
    }

    public final ExpressionCheckers getDelegate() {
        return this.delegate;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirDoWhileLoop>> getDoWhileLoopCheckers() {
        return this.doWhileLoopCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirElvisExpression>> getElvisExpressionCheckers() {
        return this.elvisExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirEqualityOperatorCall>> getEqualityOperatorCallCheckers() {
        return this.equalityOperatorCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirFunctionCall>> getFunctionCallCheckers() {
        return this.functionCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirGetClassCall>> getGetClassCallCheckers() {
        return this.getClassCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirInaccessibleReceiverExpression>> getInaccessibleReceiverCheckers() {
        return this.inaccessibleReceiverCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirIntegerLiteralOperatorCall>> getIntegerLiteralOperatorCallCheckers() {
        return this.integerLiteralOperatorCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirLiteralExpression>> getLiteralExpressionCheckers() {
        return this.literalExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirLoop>> getLoopExpressionCheckers() {
        return this.loopExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirLoopJump>> getLoopJumpCheckers() {
        return this.loopJumpCheckers;
    }

    public final Function1<FirExpressionChecker<?>, Boolean> getPredicate() {
        return this.predicate;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirPropertyAccessExpression>> getPropertyAccessExpressionCheckers() {
        return this.propertyAccessExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirQualifiedAccessExpression>> getQualifiedAccessExpressionCheckers() {
        return this.qualifiedAccessExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirReplDeclarationReference>> getReplDeclarationReferenceCheckers() {
        return this.replDeclarationReferenceCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirReplExpressionReference>> getReplExpressionReferenceCheckers() {
        return this.replExpressionReferenceCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirReplPropertyDelegate>> getReplPropertyDelegateCheckers() {
        return this.replPropertyDelegateCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirReplPropertyInitializer>> getReplPropertyInitializerCheckers() {
        return this.replPropertyInitializerCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirResolvedQualifier>> getResolvedQualifierCheckers() {
        return this.resolvedQualifierCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirReturnExpression>> getReturnExpressionCheckers() {
        return this.returnExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirSafeCallExpression>> getSafeCallExpressionCheckers() {
        return this.safeCallExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirSmartCastExpression>> getSmartCastExpressionCheckers() {
        return this.smartCastExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirStringConcatenationCall>> getStringConcatenationCallCheckers() {
        return this.stringConcatenationCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirSuperReceiverExpression>> getSuperReceiverExpressionCheckers() {
        return this.superReceiverExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirThisReceiverExpression>> getThisReceiverExpressionCheckers() {
        return this.thisReceiverExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirThrowExpression>> getThrowExpressionCheckers() {
        return this.throwExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirTryExpression>> getTryExpressionCheckers() {
        return this.tryExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirTypeOperatorCall>> getTypeOperatorCallCheckers() {
        return this.typeOperatorCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirVariableAssignment>> getVariableAssignmentCheckers() {
        return this.variableAssignmentCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirWhenExpression>> getWhenExpressionCheckers() {
        return this.whenExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirWhileLoop>> getWhileLoopCheckers() {
        return this.whileLoopCheckers;
    }
}
