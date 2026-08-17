package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.CheckersComponentInternal;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers;
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
@Metadata(d1 = {"\u0000ù\u0003\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0003\b\u009a\u0001\b&\u0018\u0000 Ã\u00022\u00020\u0001:\u0002Ã\u0002B\u0007¢\u0006\u0004\b\u0002\u0010\u0003R$\u0010\u0004\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR$\u0010\u000b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\r0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\nR$\u0010\u000f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00100\u0006j\u0002`\u00110\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\nR$\u0010\u0013\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00140\u0006j\u0002`\u00150\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\nR$\u0010\u0017\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00180\u0006j\u0002`\u00190\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\nR$\u0010\u001b\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u001c0\u0006j\u0002`\u001d0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\nR$\u0010\u001f\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020 0\u0006j\u0002`!0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\"\u0010\nR$\u0010#\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020$0\u0006j\u0002`%0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b&\u0010\nR$\u0010'\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020(0\u0006j\u0002`)0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b*\u0010\nR$\u0010+\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020,0\u0006j\u0002`-0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b.\u0010\nR$\u0010/\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002000\u0006j\u0002`10\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b2\u0010\nR$\u00103\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002040\u0006j\u0002`50\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u0010\nR$\u00107\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002080\u0006j\u0002`90\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b:\u0010\nR$\u0010;\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020<0\u0006j\u0002`=0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b>\u0010\nR$\u0010?\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020@0\u0006j\u0002`A0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bB\u0010\nR$\u0010C\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020D0\u0006j\u0002`E0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bF\u0010\nR$\u0010G\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020H0\u0006j\u0002`I0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bJ\u0010\nR$\u0010K\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020L0\u0006j\u0002`M0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bN\u0010\nR$\u0010O\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020P0\u0006j\u0002`Q0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bR\u0010\nR$\u0010S\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020T0\u0006j\u0002`U0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bV\u0010\nR$\u0010W\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020X0\u0006j\u0002`Y0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bZ\u0010\nR$\u0010[\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\\0\u0006j\u0002`]0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b^\u0010\nR$\u0010_\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020`0\u0006j\u0002`a0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bb\u0010\nR$\u0010c\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020d0\u0006j\u0002`e0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bf\u0010\nR$\u0010g\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020h0\u0006j\u0002`i0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bj\u0010\nR$\u0010k\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020l0\u0006j\u0002`m0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bn\u0010\nR$\u0010o\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020p0\u0006j\u0002`q0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\br\u0010\nR$\u0010s\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020t0\u0006j\u0002`u0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bv\u0010\nR$\u0010w\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020x0\u0006j\u0002`y0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bz\u0010\nR$\u0010{\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020|0\u0006j\u0002`}0\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b~\u0010\nR'\u0010\u007f\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0080\u00010\u0006j\u0003`\u0081\u00010\u0005X\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0082\u0001\u0010\nR(\u0010\u0083\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0084\u00010\u0006j\u0003`\u0085\u00010\u0005X\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0086\u0001\u0010\nR(\u0010\u0087\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0088\u00010\u0006j\u0003`\u0089\u00010\u0005X\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u008a\u0001\u0010\nR(\u0010\u008b\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u008c\u00010\u0006j\u0003`\u008d\u00010\u0005X\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u008e\u0001\u0010\nR(\u0010\u008f\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0090\u00010\u0006j\u0003`\u0091\u00010\u0005X\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0092\u0001\u0010\nR(\u0010\u0093\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0094\u00010\u0006j\u0003`\u0095\u00010\u0005X\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u0096\u0001\u0010\nR(\u0010\u0097\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0098\u00010\u0006j\u0003`\u0099\u00010\u0005X\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u009a\u0001\u0010\nR(\u0010\u009b\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u009c\u00010\u0006j\u0003`\u009d\u00010\u0005X\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b\u009e\u0001\u0010\nR(\u0010\u009f\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030 \u00010\u0006j\u0003`¡\u00010\u0005X\u0096\u0004¢\u0006\t\n\u0000\u001a\u0005\b¢\u0001\u0010\nR=\u0010£\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00070\u0006j\u0002`\b0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\b¨\u0001\u0010©\u0001\u0012\u0005\b¥\u0001\u0010\u0003\u001a\u0006\b¦\u0001\u0010§\u0001R=\u0010«\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\f0\u0006j\u0002`\r0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\b®\u0001\u0010©\u0001\u0012\u0005\b¬\u0001\u0010\u0003\u001a\u0006\b\u00ad\u0001\u0010§\u0001R=\u0010¯\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00100\u0006j\u0002`\u00110¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\b²\u0001\u0010©\u0001\u0012\u0005\b°\u0001\u0010\u0003\u001a\u0006\b±\u0001\u0010§\u0001R=\u0010³\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00140\u0006j\u0002`\u00150¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\b¶\u0001\u0010©\u0001\u0012\u0005\b´\u0001\u0010\u0003\u001a\u0006\bµ\u0001\u0010§\u0001R=\u0010·\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00180\u0006j\u0002`\u00190¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\bº\u0001\u0010©\u0001\u0012\u0005\b¸\u0001\u0010\u0003\u001a\u0006\b¹\u0001\u0010§\u0001R=\u0010»\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u001c0\u0006j\u0002`\u001d0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\b¾\u0001\u0010©\u0001\u0012\u0005\b¼\u0001\u0010\u0003\u001a\u0006\b½\u0001\u0010§\u0001R=\u0010¿\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020 0\u0006j\u0002`!0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\bÂ\u0001\u0010©\u0001\u0012\u0005\bÀ\u0001\u0010\u0003\u001a\u0006\bÁ\u0001\u0010§\u0001R=\u0010Ã\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020$0\u0006j\u0002`%0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\bÆ\u0001\u0010©\u0001\u0012\u0005\bÄ\u0001\u0010\u0003\u001a\u0006\bÅ\u0001\u0010§\u0001R=\u0010Ç\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020(0\u0006j\u0002`)0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\bÊ\u0001\u0010©\u0001\u0012\u0005\bÈ\u0001\u0010\u0003\u001a\u0006\bÉ\u0001\u0010§\u0001R=\u0010Ë\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020,0\u0006j\u0002`-0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\bÎ\u0001\u0010©\u0001\u0012\u0005\bÌ\u0001\u0010\u0003\u001a\u0006\bÍ\u0001\u0010§\u0001R=\u0010Ï\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002000\u0006j\u0002`10¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\bÒ\u0001\u0010©\u0001\u0012\u0005\bÐ\u0001\u0010\u0003\u001a\u0006\bÑ\u0001\u0010§\u0001R=\u0010Ó\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002040\u0006j\u0002`50¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\bÖ\u0001\u0010©\u0001\u0012\u0005\bÔ\u0001\u0010\u0003\u001a\u0006\bÕ\u0001\u0010§\u0001R=\u0010×\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002080\u0006j\u0002`90¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\bÚ\u0001\u0010©\u0001\u0012\u0005\bØ\u0001\u0010\u0003\u001a\u0006\bÙ\u0001\u0010§\u0001R=\u0010Û\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020<0\u0006j\u0002`=0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\bÞ\u0001\u0010©\u0001\u0012\u0005\bÜ\u0001\u0010\u0003\u001a\u0006\bÝ\u0001\u0010§\u0001R=\u0010ß\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020@0\u0006j\u0002`A0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\bâ\u0001\u0010©\u0001\u0012\u0005\bà\u0001\u0010\u0003\u001a\u0006\bá\u0001\u0010§\u0001R=\u0010ã\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020D0\u0006j\u0002`E0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\bæ\u0001\u0010©\u0001\u0012\u0005\bä\u0001\u0010\u0003\u001a\u0006\bå\u0001\u0010§\u0001R=\u0010ç\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020H0\u0006j\u0002`I0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\bê\u0001\u0010©\u0001\u0012\u0005\bè\u0001\u0010\u0003\u001a\u0006\bé\u0001\u0010§\u0001R=\u0010ë\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020L0\u0006j\u0002`M0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\bî\u0001\u0010©\u0001\u0012\u0005\bì\u0001\u0010\u0003\u001a\u0006\bí\u0001\u0010§\u0001R=\u0010ï\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020P0\u0006j\u0002`Q0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\bò\u0001\u0010©\u0001\u0012\u0005\bð\u0001\u0010\u0003\u001a\u0006\bñ\u0001\u0010§\u0001R=\u0010ó\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020T0\u0006j\u0002`U0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\bö\u0001\u0010©\u0001\u0012\u0005\bô\u0001\u0010\u0003\u001a\u0006\bõ\u0001\u0010§\u0001R=\u0010÷\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020X0\u0006j\u0002`Y0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\bú\u0001\u0010©\u0001\u0012\u0005\bø\u0001\u0010\u0003\u001a\u0006\bù\u0001\u0010§\u0001R=\u0010û\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\\0\u0006j\u0002`]0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\bþ\u0001\u0010©\u0001\u0012\u0005\bü\u0001\u0010\u0003\u001a\u0006\bý\u0001\u0010§\u0001R=\u0010ÿ\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020`0\u0006j\u0002`a0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\b\u0082\u0002\u0010©\u0001\u0012\u0005\b\u0080\u0002\u0010\u0003\u001a\u0006\b\u0081\u0002\u0010§\u0001R=\u0010\u0083\u0002\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020d0\u0006j\u0002`e0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\b\u0086\u0002\u0010©\u0001\u0012\u0005\b\u0084\u0002\u0010\u0003\u001a\u0006\b\u0085\u0002\u0010§\u0001R=\u0010\u0087\u0002\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020h0\u0006j\u0002`i0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\b\u008a\u0002\u0010©\u0001\u0012\u0005\b\u0088\u0002\u0010\u0003\u001a\u0006\b\u0089\u0002\u0010§\u0001R=\u0010\u008b\u0002\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020l0\u0006j\u0002`m0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\b\u008e\u0002\u0010©\u0001\u0012\u0005\b\u008c\u0002\u0010\u0003\u001a\u0006\b\u008d\u0002\u0010§\u0001R=\u0010\u008f\u0002\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020p0\u0006j\u0002`q0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\b\u0092\u0002\u0010©\u0001\u0012\u0005\b\u0090\u0002\u0010\u0003\u001a\u0006\b\u0091\u0002\u0010§\u0001R=\u0010\u0093\u0002\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020t0\u0006j\u0002`u0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\b\u0096\u0002\u0010©\u0001\u0012\u0005\b\u0094\u0002\u0010\u0003\u001a\u0006\b\u0095\u0002\u0010§\u0001R=\u0010\u0097\u0002\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020x0\u0006j\u0002`y0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\b\u009a\u0002\u0010©\u0001\u0012\u0005\b\u0098\u0002\u0010\u0003\u001a\u0006\b\u0099\u0002\u0010§\u0001R=\u0010\u009b\u0002\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020|0\u0006j\u0002`}0¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\b\u009e\u0002\u0010©\u0001\u0012\u0005\b\u009c\u0002\u0010\u0003\u001a\u0006\b\u009d\u0002\u0010§\u0001R?\u0010\u009f\u0002\u001a\u0015\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0080\u00010\u0006j\u0003`\u0081\u00010¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\b¢\u0002\u0010©\u0001\u0012\u0005\b \u0002\u0010\u0003\u001a\u0006\b¡\u0002\u0010§\u0001R?\u0010£\u0002\u001a\u0015\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0084\u00010\u0006j\u0003`\u0085\u00010¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\b¦\u0002\u0010©\u0001\u0012\u0005\b¤\u0002\u0010\u0003\u001a\u0006\b¥\u0002\u0010§\u0001R?\u0010§\u0002\u001a\u0015\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0088\u00010\u0006j\u0003`\u0089\u00010¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\bª\u0002\u0010©\u0001\u0012\u0005\b¨\u0002\u0010\u0003\u001a\u0006\b©\u0002\u0010§\u0001R?\u0010«\u0002\u001a\u0015\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u008c\u00010\u0006j\u0003`\u008d\u00010¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\b®\u0002\u0010©\u0001\u0012\u0005\b¬\u0002\u0010\u0003\u001a\u0006\b\u00ad\u0002\u0010§\u0001R?\u0010¯\u0002\u001a\u0015\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0090\u00010\u0006j\u0003`\u0091\u00010¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\b²\u0002\u0010©\u0001\u0012\u0005\b°\u0002\u0010\u0003\u001a\u0006\b±\u0002\u0010§\u0001R?\u0010³\u0002\u001a\u0015\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0094\u00010\u0006j\u0003`\u0095\u00010¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\b¶\u0002\u0010©\u0001\u0012\u0005\b´\u0002\u0010\u0003\u001a\u0006\bµ\u0002\u0010§\u0001R?\u0010·\u0002\u001a\u0015\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0098\u00010\u0006j\u0003`\u0099\u00010¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\bº\u0002\u0010©\u0001\u0012\u0005\b¸\u0002\u0010\u0003\u001a\u0006\b¹\u0002\u0010§\u0001R?\u0010»\u0002\u001a\u0015\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u009c\u00010\u0006j\u0003`\u009d\u00010¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\b¾\u0002\u0010©\u0001\u0012\u0005\b¼\u0002\u0010\u0003\u001a\u0006\b½\u0002\u0010§\u0001R?\u0010¿\u0002\u001a\u0015\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030 \u00010\u0006j\u0003`¡\u00010¤\u00018@X\u0081\u0084\u0002r\u0003\bª\u0001¢\u0006\u0017\n\u0006\bÂ\u0002\u0010©\u0001\u0012\u0005\bÀ\u0002\u0010\u0003\u001a\u0006\bÁ\u0002\u0010§\u0001¨\u0006Ä\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ExpressionCheckers;", Argument.Delimiters.none, "<init>", "()V", "basicExpressionCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBasicExpressionChecker;", "getBasicExpressionCheckers", "()Ljava/util/Set;", "qualifiedAccessExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "getQualifiedAccessExpressionCheckers", "callCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCallChecker;", "getCallCheckers", "functionCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "getFunctionCallCheckers", "propertyAccessExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirPropertyAccessExpressionChecker;", "getPropertyAccessExpressionCheckers", "superReceiverExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirSuperReceiverExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirSuperReceiverExpressionChecker;", "getSuperReceiverExpressionCheckers", "integerLiteralOperatorCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirIntegerLiteralOperatorCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirIntegerLiteralOperatorCallChecker;", "getIntegerLiteralOperatorCallCheckers", "variableAssignmentCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirVariableAssignmentChecker;", "getVariableAssignmentCheckers", "tryExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirTryExpressionChecker;", "getTryExpressionCheckers", "whenExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirWhenExpressionChecker;", "getWhenExpressionCheckers", "loopExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirLoop;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirLoopExpressionChecker;", "getLoopExpressionCheckers", "loopJumpCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirLoopJump;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirLoopJumpChecker;", "getLoopJumpCheckers", "booleanOperatorExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBooleanOperatorExpressionChecker;", "getBooleanOperatorExpressionCheckers", "returnExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReturnExpressionChecker;", "getReturnExpressionCheckers", "blockCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBlockChecker;", "getBlockCheckers", "replDeclarationReferenceCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirReplDeclarationReference;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReplDeclarationReferenceChecker;", "getReplDeclarationReferenceCheckers", "replPropertyInitializerCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyInitializer;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReplPropertyInitializerChecker;", "getReplPropertyInitializerCheckers", "replPropertyDelegateCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyDelegate;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReplPropertyDelegateChecker;", "getReplPropertyDelegateCheckers", "replExpressionReferenceCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirReplExpressionReference;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReplExpressionReferenceChecker;", "getReplExpressionReferenceCheckers", "annotationCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirAnnotationChecker;", "getAnnotationCheckers", "annotationCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirAnnotationCallChecker;", "getAnnotationCallCheckers", "checkNotNullCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckNotNullCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCheckNotNullCallChecker;", "getCheckNotNullCallCheckers", "elvisExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirElvisExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirElvisExpressionChecker;", "getElvisExpressionCheckers", "getClassCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirGetClassCallChecker;", "getGetClassCallCheckers", "safeCallExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirSafeCallExpressionChecker;", "getSafeCallExpressionCheckers", "smartCastExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirSmartCastExpressionChecker;", "getSmartCastExpressionCheckers", "equalityOperatorCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirEqualityOperatorCallChecker;", "getEqualityOperatorCallCheckers", "stringConcatenationCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirStringConcatenationCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirStringConcatenationCallChecker;", "getStringConcatenationCallCheckers", "typeOperatorCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirTypeOperatorCallChecker;", "getTypeOperatorCallCheckers", "resolvedQualifierCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirResolvedQualifierChecker;", "getResolvedQualifierCheckers", "literalExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirLiteralExpressionChecker;", "getLiteralExpressionCheckers", "callableReferenceAccessCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCallableReferenceAccessChecker;", "getCallableReferenceAccessCheckers", "thisReceiverExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirThisReceiverExpressionChecker;", "getThisReceiverExpressionCheckers", "whileLoopCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirWhileLoop;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirWhileLoopChecker;", "getWhileLoopCheckers", "throwExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirThrowExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirThrowExpressionChecker;", "getThrowExpressionCheckers", "doWhileLoopCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirDoWhileLoop;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirDoWhileLoopChecker;", "getDoWhileLoopCheckers", "collectionLiteralCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCollectionLiteralChecker;", "getCollectionLiteralCheckers", "classReferenceExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirClassReferenceExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirClassReferenceExpressionChecker;", "getClassReferenceExpressionCheckers", "inaccessibleReceiverCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirInaccessibleReceiverExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirInaccessibleReceiverChecker;", "getInaccessibleReceiverCheckers", "allBasicExpressionCheckers", Argument.Delimiters.none, "getAllBasicExpressionCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllBasicExpressionCheckers$org_jetbrains_kotlin_checkers", "()[Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "allBasicExpressionCheckers$delegate", "Lkotlin/Lazy;", "Lorg/jetbrains/kotlin/fir/analysis/CheckersComponentInternal;", "allQualifiedAccessExpressionCheckers", "getAllQualifiedAccessExpressionCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllQualifiedAccessExpressionCheckers$org_jetbrains_kotlin_checkers", "allQualifiedAccessExpressionCheckers$delegate", "allCallCheckers", "getAllCallCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllCallCheckers$org_jetbrains_kotlin_checkers", "allCallCheckers$delegate", "allFunctionCallCheckers", "getAllFunctionCallCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllFunctionCallCheckers$org_jetbrains_kotlin_checkers", "allFunctionCallCheckers$delegate", "allPropertyAccessExpressionCheckers", "getAllPropertyAccessExpressionCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllPropertyAccessExpressionCheckers$org_jetbrains_kotlin_checkers", "allPropertyAccessExpressionCheckers$delegate", "allSuperReceiverExpressionCheckers", "getAllSuperReceiverExpressionCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllSuperReceiverExpressionCheckers$org_jetbrains_kotlin_checkers", "allSuperReceiverExpressionCheckers$delegate", "allIntegerLiteralOperatorCallCheckers", "getAllIntegerLiteralOperatorCallCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllIntegerLiteralOperatorCallCheckers$org_jetbrains_kotlin_checkers", "allIntegerLiteralOperatorCallCheckers$delegate", "allVariableAssignmentCheckers", "getAllVariableAssignmentCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllVariableAssignmentCheckers$org_jetbrains_kotlin_checkers", "allVariableAssignmentCheckers$delegate", "allTryExpressionCheckers", "getAllTryExpressionCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllTryExpressionCheckers$org_jetbrains_kotlin_checkers", "allTryExpressionCheckers$delegate", "allWhenExpressionCheckers", "getAllWhenExpressionCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllWhenExpressionCheckers$org_jetbrains_kotlin_checkers", "allWhenExpressionCheckers$delegate", "allLoopExpressionCheckers", "getAllLoopExpressionCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllLoopExpressionCheckers$org_jetbrains_kotlin_checkers", "allLoopExpressionCheckers$delegate", "allLoopJumpCheckers", "getAllLoopJumpCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllLoopJumpCheckers$org_jetbrains_kotlin_checkers", "allLoopJumpCheckers$delegate", "allBooleanOperatorExpressionCheckers", "getAllBooleanOperatorExpressionCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllBooleanOperatorExpressionCheckers$org_jetbrains_kotlin_checkers", "allBooleanOperatorExpressionCheckers$delegate", "allReturnExpressionCheckers", "getAllReturnExpressionCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllReturnExpressionCheckers$org_jetbrains_kotlin_checkers", "allReturnExpressionCheckers$delegate", "allBlockCheckers", "getAllBlockCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllBlockCheckers$org_jetbrains_kotlin_checkers", "allBlockCheckers$delegate", "allReplDeclarationReferenceCheckers", "getAllReplDeclarationReferenceCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllReplDeclarationReferenceCheckers$org_jetbrains_kotlin_checkers", "allReplDeclarationReferenceCheckers$delegate", "allReplPropertyInitializerCheckers", "getAllReplPropertyInitializerCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllReplPropertyInitializerCheckers$org_jetbrains_kotlin_checkers", "allReplPropertyInitializerCheckers$delegate", "allReplPropertyDelegateCheckers", "getAllReplPropertyDelegateCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllReplPropertyDelegateCheckers$org_jetbrains_kotlin_checkers", "allReplPropertyDelegateCheckers$delegate", "allReplExpressionReferenceCheckers", "getAllReplExpressionReferenceCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllReplExpressionReferenceCheckers$org_jetbrains_kotlin_checkers", "allReplExpressionReferenceCheckers$delegate", "allAnnotationCheckers", "getAllAnnotationCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllAnnotationCheckers$org_jetbrains_kotlin_checkers", "allAnnotationCheckers$delegate", "allAnnotationCallCheckers", "getAllAnnotationCallCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllAnnotationCallCheckers$org_jetbrains_kotlin_checkers", "allAnnotationCallCheckers$delegate", "allCheckNotNullCallCheckers", "getAllCheckNotNullCallCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllCheckNotNullCallCheckers$org_jetbrains_kotlin_checkers", "allCheckNotNullCallCheckers$delegate", "allElvisExpressionCheckers", "getAllElvisExpressionCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllElvisExpressionCheckers$org_jetbrains_kotlin_checkers", "allElvisExpressionCheckers$delegate", "allGetClassCallCheckers", "getAllGetClassCallCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllGetClassCallCheckers$org_jetbrains_kotlin_checkers", "allGetClassCallCheckers$delegate", "allSafeCallExpressionCheckers", "getAllSafeCallExpressionCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllSafeCallExpressionCheckers$org_jetbrains_kotlin_checkers", "allSafeCallExpressionCheckers$delegate", "allSmartCastExpressionCheckers", "getAllSmartCastExpressionCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllSmartCastExpressionCheckers$org_jetbrains_kotlin_checkers", "allSmartCastExpressionCheckers$delegate", "allEqualityOperatorCallCheckers", "getAllEqualityOperatorCallCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllEqualityOperatorCallCheckers$org_jetbrains_kotlin_checkers", "allEqualityOperatorCallCheckers$delegate", "allStringConcatenationCallCheckers", "getAllStringConcatenationCallCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllStringConcatenationCallCheckers$org_jetbrains_kotlin_checkers", "allStringConcatenationCallCheckers$delegate", "allTypeOperatorCallCheckers", "getAllTypeOperatorCallCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllTypeOperatorCallCheckers$org_jetbrains_kotlin_checkers", "allTypeOperatorCallCheckers$delegate", "allResolvedQualifierCheckers", "getAllResolvedQualifierCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllResolvedQualifierCheckers$org_jetbrains_kotlin_checkers", "allResolvedQualifierCheckers$delegate", "allLiteralExpressionCheckers", "getAllLiteralExpressionCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllLiteralExpressionCheckers$org_jetbrains_kotlin_checkers", "allLiteralExpressionCheckers$delegate", "allCallableReferenceAccessCheckers", "getAllCallableReferenceAccessCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllCallableReferenceAccessCheckers$org_jetbrains_kotlin_checkers", "allCallableReferenceAccessCheckers$delegate", "allThisReceiverExpressionCheckers", "getAllThisReceiverExpressionCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllThisReceiverExpressionCheckers$org_jetbrains_kotlin_checkers", "allThisReceiverExpressionCheckers$delegate", "allWhileLoopCheckers", "getAllWhileLoopCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllWhileLoopCheckers$org_jetbrains_kotlin_checkers", "allWhileLoopCheckers$delegate", "allThrowExpressionCheckers", "getAllThrowExpressionCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllThrowExpressionCheckers$org_jetbrains_kotlin_checkers", "allThrowExpressionCheckers$delegate", "allDoWhileLoopCheckers", "getAllDoWhileLoopCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllDoWhileLoopCheckers$org_jetbrains_kotlin_checkers", "allDoWhileLoopCheckers$delegate", "allCollectionLiteralCheckers", "getAllCollectionLiteralCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllCollectionLiteralCheckers$org_jetbrains_kotlin_checkers", "allCollectionLiteralCheckers$delegate", "allClassReferenceExpressionCheckers", "getAllClassReferenceExpressionCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllClassReferenceExpressionCheckers$org_jetbrains_kotlin_checkers", "allClassReferenceExpressionCheckers$delegate", "allInaccessibleReceiverCheckers", "getAllInaccessibleReceiverCheckers$org_jetbrains_kotlin_checkers$annotations", "getAllInaccessibleReceiverCheckers$org_jetbrains_kotlin_checkers", "allInaccessibleReceiverCheckers$delegate", "Companion", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ExpressionCheckers {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ExpressionCheckers EMPTY = new ExpressionCheckers() { // from class: org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers$Companion$EMPTY$1
    };
    private final Set<FirExpressionChecker<FirStatement>> basicExpressionCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirQualifiedAccessExpression>> qualifiedAccessExpressionCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirCall>> callCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirFunctionCall>> functionCallCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirPropertyAccessExpression>> propertyAccessExpressionCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirSuperReceiverExpression>> superReceiverExpressionCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirIntegerLiteralOperatorCall>> integerLiteralOperatorCallCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirVariableAssignment>> variableAssignmentCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirTryExpression>> tryExpressionCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirWhenExpression>> whenExpressionCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirLoop>> loopExpressionCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirLoopJump>> loopJumpCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirBooleanOperatorExpression>> booleanOperatorExpressionCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirReturnExpression>> returnExpressionCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirBlock>> blockCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirReplDeclarationReference>> replDeclarationReferenceCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirReplPropertyInitializer>> replPropertyInitializerCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirReplPropertyDelegate>> replPropertyDelegateCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirReplExpressionReference>> replExpressionReferenceCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirAnnotation>> annotationCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirAnnotationCall>> annotationCallCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirCheckNotNullCall>> checkNotNullCallCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirElvisExpression>> elvisExpressionCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirGetClassCall>> getClassCallCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirSafeCallExpression>> safeCallExpressionCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirSmartCastExpression>> smartCastExpressionCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirEqualityOperatorCall>> equalityOperatorCallCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirStringConcatenationCall>> stringConcatenationCallCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirTypeOperatorCall>> typeOperatorCallCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirResolvedQualifier>> resolvedQualifierCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirLiteralExpression>> literalExpressionCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirCallableReferenceAccess>> callableReferenceAccessCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirThisReceiverExpression>> thisReceiverExpressionCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirWhileLoop>> whileLoopCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirThrowExpression>> throwExpressionCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirDoWhileLoop>> doWhileLoopCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirCollectionLiteral>> collectionLiteralCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirClassReferenceExpression>> classReferenceExpressionCheckers = SetsKt.emptySet();
    private final Set<FirExpressionChecker<FirInaccessibleReceiverExpression>> inaccessibleReceiverCheckers = SetsKt.emptySet();

    /* JADX INFO: renamed from: allBasicExpressionCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allBasicExpressionCheckers = LazyKt.lazy(new Function0() { // from class: fh4
        public final Object invoke() {
            return ExpressionCheckers.y(this.b);
        }
    });

    /* JADX INFO: renamed from: allQualifiedAccessExpressionCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allQualifiedAccessExpressionCheckers = LazyKt.lazy(new Function0() { // from class: hh4
        public final Object invoke() {
            return ExpressionCheckers.x(this.b);
        }
    });

    /* JADX INFO: renamed from: allCallCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allCallCheckers = LazyKt.lazy(new Function0() { // from class: th4
        public final Object invoke() {
            return ExpressionCheckers.z(this.b);
        }
    });

    /* JADX INFO: renamed from: allFunctionCallCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allFunctionCallCheckers = LazyKt.lazy(new Function0() { // from class: ei4
        public final Object invoke() {
            return ExpressionCheckers.g(this.b);
        }
    });

    /* JADX INFO: renamed from: allPropertyAccessExpressionCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allPropertyAccessExpressionCheckers = LazyKt.lazy(new Function0() { // from class: fi4
        public final Object invoke() {
            return ExpressionCheckers.F(this.b);
        }
    });

    /* JADX INFO: renamed from: allSuperReceiverExpressionCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allSuperReceiverExpressionCheckers = LazyKt.lazy(new Function0() { // from class: gi4
        public final Object invoke() {
            return ExpressionCheckers.K(this.b);
        }
    });

    /* JADX INFO: renamed from: allIntegerLiteralOperatorCallCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allIntegerLiteralOperatorCallCheckers = LazyKt.lazy(new Function0() { // from class: hi4
        public final Object invoke() {
            return ExpressionCheckers.M(this.b);
        }
    });

    /* JADX INFO: renamed from: allVariableAssignmentCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allVariableAssignmentCheckers = LazyKt.lazy(new Function0() { // from class: ii4
        public final Object invoke() {
            return ExpressionCheckers.q(this.b);
        }
    });

    /* JADX INFO: renamed from: allTryExpressionCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allTryExpressionCheckers = LazyKt.lazy(new Function0() { // from class: ji4
        public final Object invoke() {
            return ExpressionCheckers.l(this.b);
        }
    });

    /* JADX INFO: renamed from: allWhenExpressionCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allWhenExpressionCheckers = LazyKt.lazy(new Function0() { // from class: ki4
        public final Object invoke() {
            return ExpressionCheckers.p(this.b);
        }
    });

    /* JADX INFO: renamed from: allLoopExpressionCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allLoopExpressionCheckers = LazyKt.lazy(new Function0() { // from class: qh4
        public final Object invoke() {
            return ExpressionCheckers.v(this.b);
        }
    });

    /* JADX INFO: renamed from: allLoopJumpCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allLoopJumpCheckers = LazyKt.lazy(new Function0() { // from class: bi4
        public final Object invoke() {
            return ExpressionCheckers.h(this.b);
        }
    });

    /* JADX INFO: renamed from: allBooleanOperatorExpressionCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allBooleanOperatorExpressionCheckers = LazyKt.lazy(new Function0() { // from class: li4
        public final Object invoke() {
            return ExpressionCheckers.t(this.b);
        }
    });

    /* JADX INFO: renamed from: allReturnExpressionCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allReturnExpressionCheckers = LazyKt.lazy(new Function0() { // from class: mi4
        public final Object invoke() {
            return ExpressionCheckers.A(this.b);
        }
    });

    /* JADX INFO: renamed from: allBlockCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allBlockCheckers = LazyKt.lazy(new Function0() { // from class: ni4
        public final Object invoke() {
            return ExpressionCheckers.D(this.b);
        }
    });

    /* JADX INFO: renamed from: allReplDeclarationReferenceCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allReplDeclarationReferenceCheckers = LazyKt.lazy(new Function0() { // from class: oi4
        public final Object invoke() {
            return ExpressionCheckers.o(this.b);
        }
    });

    /* JADX INFO: renamed from: allReplPropertyInitializerCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allReplPropertyInitializerCheckers = LazyKt.lazy(new Function0() { // from class: pi4
        public final Object invoke() {
            return ExpressionCheckers.w(this.b);
        }
    });

    /* JADX INFO: renamed from: allReplPropertyDelegateCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allReplPropertyDelegateCheckers = LazyKt.lazy(new Function0() { // from class: qi4
        public final Object invoke() {
            return ExpressionCheckers.n(this.b);
        }
    });

    /* JADX INFO: renamed from: allReplExpressionReferenceCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allReplExpressionReferenceCheckers = LazyKt.lazy(new Function0() { // from class: ri4
        public final Object invoke() {
            return ExpressionCheckers.j(this.b);
        }
    });

    /* JADX INFO: renamed from: allAnnotationCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allAnnotationCheckers = LazyKt.lazy(new Function0() { // from class: gh4
        public final Object invoke() {
            return ExpressionCheckers.a(this.b);
        }
    });

    /* JADX INFO: renamed from: allAnnotationCallCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allAnnotationCallCheckers = LazyKt.lazy(new Function0() { // from class: ih4
        public final Object invoke() {
            return ExpressionCheckers.c(this.b);
        }
    });

    /* JADX INFO: renamed from: allCheckNotNullCallCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allCheckNotNullCallCheckers = LazyKt.lazy(new Function0() { // from class: jh4
        public final Object invoke() {
            return ExpressionCheckers.i(this.b);
        }
    });

    /* JADX INFO: renamed from: allElvisExpressionCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allElvisExpressionCheckers = LazyKt.lazy(new Function0() { // from class: kh4
        public final Object invoke() {
            return ExpressionCheckers.s(this.b);
        }
    });

    /* JADX INFO: renamed from: allGetClassCallCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allGetClassCallCheckers = LazyKt.lazy(new Function0() { // from class: lh4
        public final Object invoke() {
            return ExpressionCheckers.L(this.b);
        }
    });

    /* JADX INFO: renamed from: allSafeCallExpressionCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allSafeCallExpressionCheckers = LazyKt.lazy(new Function0() { // from class: mh4
        public final Object invoke() {
            return ExpressionCheckers.B(this.b);
        }
    });

    /* JADX INFO: renamed from: allSmartCastExpressionCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allSmartCastExpressionCheckers = LazyKt.lazy(new Function0() { // from class: nh4
        public final Object invoke() {
            return ExpressionCheckers.H(this.b);
        }
    });

    /* JADX INFO: renamed from: allEqualityOperatorCallCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allEqualityOperatorCallCheckers = LazyKt.lazy(new Function0() { // from class: oh4
        public final Object invoke() {
            return ExpressionCheckers.I(this.b);
        }
    });

    /* JADX INFO: renamed from: allStringConcatenationCallCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allStringConcatenationCallCheckers = LazyKt.lazy(new Function0() { // from class: ph4
        public final Object invoke() {
            return ExpressionCheckers.E(this.b);
        }
    });

    /* JADX INFO: renamed from: allTypeOperatorCallCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allTypeOperatorCallCheckers = LazyKt.lazy(new Function0() { // from class: rh4
        public final Object invoke() {
            return ExpressionCheckers.J(this.b);
        }
    });

    /* JADX INFO: renamed from: allResolvedQualifierCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allResolvedQualifierCheckers = LazyKt.lazy(new Function0() { // from class: sh4
        public final Object invoke() {
            return ExpressionCheckers.e(this.b);
        }
    });

    /* JADX INFO: renamed from: allLiteralExpressionCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allLiteralExpressionCheckers = LazyKt.lazy(new Function0() { // from class: uh4
        public final Object invoke() {
            return ExpressionCheckers.f(this.b);
        }
    });

    /* JADX INFO: renamed from: allCallableReferenceAccessCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allCallableReferenceAccessCheckers = LazyKt.lazy(new Function0() { // from class: vh4
        public final Object invoke() {
            return ExpressionCheckers.d(this.b);
        }
    });

    /* JADX INFO: renamed from: allThisReceiverExpressionCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allThisReceiverExpressionCheckers = LazyKt.lazy(new Function0() { // from class: wh4
        public final Object invoke() {
            return ExpressionCheckers.k(this.b);
        }
    });

    /* JADX INFO: renamed from: allWhileLoopCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allWhileLoopCheckers = LazyKt.lazy(new Function0() { // from class: xh4
        public final Object invoke() {
            return ExpressionCheckers.u(this.b);
        }
    });

    /* JADX INFO: renamed from: allThrowExpressionCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allThrowExpressionCheckers = LazyKt.lazy(new Function0() { // from class: yh4
        public final Object invoke() {
            return ExpressionCheckers.r(this.b);
        }
    });

    /* JADX INFO: renamed from: allDoWhileLoopCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allDoWhileLoopCheckers = LazyKt.lazy(new Function0() { // from class: zh4
        public final Object invoke() {
            return ExpressionCheckers.b(this.b);
        }
    });

    /* JADX INFO: renamed from: allCollectionLiteralCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allCollectionLiteralCheckers = LazyKt.lazy(new Function0() { // from class: ai4
        public final Object invoke() {
            return ExpressionCheckers.G(this.b);
        }
    });

    /* JADX INFO: renamed from: allClassReferenceExpressionCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allClassReferenceExpressionCheckers = LazyKt.lazy(new Function0() { // from class: ci4
        public final Object invoke() {
            return ExpressionCheckers.m(this.b);
        }
    });

    /* JADX INFO: renamed from: allInaccessibleReceiverCheckers$delegate, reason: from kotlin metadata */
    private final Lazy allInaccessibleReceiverCheckers = LazyKt.lazy(new Function0() { // from class: di4
        public final Object invoke() {
            return ExpressionCheckers.C(this.b);
        }
    });

    public static FirExpressionChecker[] A(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getReturnExpressionCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] B(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getSafeCallExpressionCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] C(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getInaccessibleReceiverCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] D(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getBlockCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] E(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(SetsKt.plus(expressionCheckers.getStringConcatenationCallCheckers(), expressionCheckers.getBasicExpressionCheckers()), expressionCheckers.getCallCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] F(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(SetsKt.plus(expressionCheckers.getPropertyAccessExpressionCheckers(), expressionCheckers.getQualifiedAccessExpressionCheckers()), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] G(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(SetsKt.plus(expressionCheckers.getCollectionLiteralCheckers(), expressionCheckers.getBasicExpressionCheckers()), expressionCheckers.getCallCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] H(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getSmartCastExpressionCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] I(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(SetsKt.plus(expressionCheckers.getEqualityOperatorCallCheckers(), expressionCheckers.getBasicExpressionCheckers()), expressionCheckers.getCallCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] J(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(SetsKt.plus(expressionCheckers.getTypeOperatorCallCheckers(), expressionCheckers.getBasicExpressionCheckers()), expressionCheckers.getCallCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] K(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(SetsKt.plus(expressionCheckers.getSuperReceiverExpressionCheckers(), expressionCheckers.getQualifiedAccessExpressionCheckers()), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] L(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(SetsKt.plus(expressionCheckers.getGetClassCallCheckers(), expressionCheckers.getBasicExpressionCheckers()), expressionCheckers.getCallCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] M(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(SetsKt.plus(SetsKt.plus(SetsKt.plus(expressionCheckers.getIntegerLiteralOperatorCallCheckers(), expressionCheckers.getFunctionCallCheckers()), expressionCheckers.getQualifiedAccessExpressionCheckers()), expressionCheckers.getBasicExpressionCheckers()), expressionCheckers.getCallCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] a(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getAnnotationCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] b(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(SetsKt.plus(expressionCheckers.getDoWhileLoopCheckers(), expressionCheckers.getLoopExpressionCheckers()), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] c(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(SetsKt.plus(SetsKt.plus(expressionCheckers.getAnnotationCallCheckers(), expressionCheckers.getAnnotationCheckers()), expressionCheckers.getBasicExpressionCheckers()), expressionCheckers.getCallCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] d(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(SetsKt.plus(expressionCheckers.getCallableReferenceAccessCheckers(), expressionCheckers.getQualifiedAccessExpressionCheckers()), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] e(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getResolvedQualifierCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] f(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getLiteralExpressionCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] g(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(SetsKt.plus(SetsKt.plus(expressionCheckers.getFunctionCallCheckers(), expressionCheckers.getQualifiedAccessExpressionCheckers()), expressionCheckers.getBasicExpressionCheckers()), expressionCheckers.getCallCheckers()).toArray(new FirExpressionChecker[0]);
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllAnnotationCallCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllAnnotationCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllBasicExpressionCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllBlockCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllBooleanOperatorExpressionCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllCallCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllCallableReferenceAccessCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllCheckNotNullCallCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllClassReferenceExpressionCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllCollectionLiteralCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllDoWhileLoopCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllElvisExpressionCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllEqualityOperatorCallCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllFunctionCallCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllGetClassCallCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllInaccessibleReceiverCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllIntegerLiteralOperatorCallCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllLiteralExpressionCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllLoopExpressionCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllLoopJumpCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllPropertyAccessExpressionCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllQualifiedAccessExpressionCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllReplDeclarationReferenceCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllReplExpressionReferenceCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllReplPropertyDelegateCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllReplPropertyInitializerCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllResolvedQualifierCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllReturnExpressionCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllSafeCallExpressionCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllSmartCastExpressionCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllStringConcatenationCallCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllSuperReceiverExpressionCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllThisReceiverExpressionCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllThrowExpressionCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllTryExpressionCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllTypeOperatorCallCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllVariableAssignmentCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllWhenExpressionCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    @CheckersComponentInternal
    public static /* synthetic */ void getAllWhileLoopCheckers$org_jetbrains_kotlin_checkers$annotations() {
    }

    public static FirExpressionChecker[] h(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getLoopJumpCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] i(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(SetsKt.plus(expressionCheckers.getCheckNotNullCallCheckers(), expressionCheckers.getBasicExpressionCheckers()), expressionCheckers.getCallCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] j(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getReplExpressionReferenceCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] k(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(SetsKt.plus(expressionCheckers.getThisReceiverExpressionCheckers(), expressionCheckers.getQualifiedAccessExpressionCheckers()), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] l(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getTryExpressionCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] m(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getClassReferenceExpressionCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] n(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getReplPropertyDelegateCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] o(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getReplDeclarationReferenceCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] p(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getWhenExpressionCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] q(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getVariableAssignmentCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] r(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getThrowExpressionCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] s(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getElvisExpressionCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] t(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getBooleanOperatorExpressionCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] u(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(SetsKt.plus(expressionCheckers.getWhileLoopCheckers(), expressionCheckers.getLoopExpressionCheckers()), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] v(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getLoopExpressionCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] w(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getReplPropertyInitializerCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] x(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getQualifiedAccessExpressionCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] y(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) expressionCheckers.getBasicExpressionCheckers().toArray(new FirExpressionChecker[0]);
    }

    public static FirExpressionChecker[] z(ExpressionCheckers expressionCheckers) {
        return (FirExpressionChecker[]) SetsKt.plus(expressionCheckers.getCallCheckers(), expressionCheckers.getBasicExpressionCheckers()).toArray(new FirExpressionChecker[0]);
    }

    public final FirExpressionChecker<FirAnnotationCall>[] getAllAnnotationCallCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allAnnotationCallCheckers.getValue();
    }

    public final FirExpressionChecker<FirAnnotation>[] getAllAnnotationCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allAnnotationCheckers.getValue();
    }

    public final FirExpressionChecker<FirStatement>[] getAllBasicExpressionCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allBasicExpressionCheckers.getValue();
    }

    public final FirExpressionChecker<FirBlock>[] getAllBlockCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allBlockCheckers.getValue();
    }

    public final FirExpressionChecker<FirBooleanOperatorExpression>[] getAllBooleanOperatorExpressionCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allBooleanOperatorExpressionCheckers.getValue();
    }

    public final FirExpressionChecker<FirCall>[] getAllCallCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allCallCheckers.getValue();
    }

    public final FirExpressionChecker<FirCallableReferenceAccess>[] getAllCallableReferenceAccessCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allCallableReferenceAccessCheckers.getValue();
    }

    public final FirExpressionChecker<FirCheckNotNullCall>[] getAllCheckNotNullCallCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allCheckNotNullCallCheckers.getValue();
    }

    public final FirExpressionChecker<FirClassReferenceExpression>[] getAllClassReferenceExpressionCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allClassReferenceExpressionCheckers.getValue();
    }

    public final FirExpressionChecker<FirCollectionLiteral>[] getAllCollectionLiteralCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allCollectionLiteralCheckers.getValue();
    }

    public final FirExpressionChecker<FirDoWhileLoop>[] getAllDoWhileLoopCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allDoWhileLoopCheckers.getValue();
    }

    public final FirExpressionChecker<FirElvisExpression>[] getAllElvisExpressionCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allElvisExpressionCheckers.getValue();
    }

    public final FirExpressionChecker<FirEqualityOperatorCall>[] getAllEqualityOperatorCallCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allEqualityOperatorCallCheckers.getValue();
    }

    public final FirExpressionChecker<FirFunctionCall>[] getAllFunctionCallCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allFunctionCallCheckers.getValue();
    }

    public final FirExpressionChecker<FirGetClassCall>[] getAllGetClassCallCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allGetClassCallCheckers.getValue();
    }

    public final FirExpressionChecker<FirInaccessibleReceiverExpression>[] getAllInaccessibleReceiverCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allInaccessibleReceiverCheckers.getValue();
    }

    public final FirExpressionChecker<FirIntegerLiteralOperatorCall>[] getAllIntegerLiteralOperatorCallCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allIntegerLiteralOperatorCallCheckers.getValue();
    }

    public final FirExpressionChecker<FirLiteralExpression>[] getAllLiteralExpressionCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allLiteralExpressionCheckers.getValue();
    }

    public final FirExpressionChecker<FirLoop>[] getAllLoopExpressionCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allLoopExpressionCheckers.getValue();
    }

    public final FirExpressionChecker<FirLoopJump>[] getAllLoopJumpCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allLoopJumpCheckers.getValue();
    }

    public final FirExpressionChecker<FirPropertyAccessExpression>[] getAllPropertyAccessExpressionCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allPropertyAccessExpressionCheckers.getValue();
    }

    public final FirExpressionChecker<FirQualifiedAccessExpression>[] getAllQualifiedAccessExpressionCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allQualifiedAccessExpressionCheckers.getValue();
    }

    public final FirExpressionChecker<FirReplDeclarationReference>[] getAllReplDeclarationReferenceCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allReplDeclarationReferenceCheckers.getValue();
    }

    public final FirExpressionChecker<FirReplExpressionReference>[] getAllReplExpressionReferenceCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allReplExpressionReferenceCheckers.getValue();
    }

    public final FirExpressionChecker<FirReplPropertyDelegate>[] getAllReplPropertyDelegateCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allReplPropertyDelegateCheckers.getValue();
    }

    public final FirExpressionChecker<FirReplPropertyInitializer>[] getAllReplPropertyInitializerCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allReplPropertyInitializerCheckers.getValue();
    }

    public final FirExpressionChecker<FirResolvedQualifier>[] getAllResolvedQualifierCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allResolvedQualifierCheckers.getValue();
    }

    public final FirExpressionChecker<FirReturnExpression>[] getAllReturnExpressionCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allReturnExpressionCheckers.getValue();
    }

    public final FirExpressionChecker<FirSafeCallExpression>[] getAllSafeCallExpressionCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allSafeCallExpressionCheckers.getValue();
    }

    public final FirExpressionChecker<FirSmartCastExpression>[] getAllSmartCastExpressionCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allSmartCastExpressionCheckers.getValue();
    }

    public final FirExpressionChecker<FirStringConcatenationCall>[] getAllStringConcatenationCallCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allStringConcatenationCallCheckers.getValue();
    }

    public final FirExpressionChecker<FirSuperReceiverExpression>[] getAllSuperReceiverExpressionCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allSuperReceiverExpressionCheckers.getValue();
    }

    public final FirExpressionChecker<FirThisReceiverExpression>[] getAllThisReceiverExpressionCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allThisReceiverExpressionCheckers.getValue();
    }

    public final FirExpressionChecker<FirThrowExpression>[] getAllThrowExpressionCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allThrowExpressionCheckers.getValue();
    }

    public final FirExpressionChecker<FirTryExpression>[] getAllTryExpressionCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allTryExpressionCheckers.getValue();
    }

    public final FirExpressionChecker<FirTypeOperatorCall>[] getAllTypeOperatorCallCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allTypeOperatorCallCheckers.getValue();
    }

    public final FirExpressionChecker<FirVariableAssignment>[] getAllVariableAssignmentCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allVariableAssignmentCheckers.getValue();
    }

    public final FirExpressionChecker<FirWhenExpression>[] getAllWhenExpressionCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allWhenExpressionCheckers.getValue();
    }

    public final FirExpressionChecker<FirWhileLoop>[] getAllWhileLoopCheckers$org_jetbrains_kotlin_checkers() {
        return (FirExpressionChecker[]) this.allWhileLoopCheckers.getValue();
    }

    public Set<FirExpressionChecker<FirAnnotationCall>> getAnnotationCallCheckers() {
        return this.annotationCallCheckers;
    }

    public Set<FirExpressionChecker<FirAnnotation>> getAnnotationCheckers() {
        return this.annotationCheckers;
    }

    public Set<FirExpressionChecker<FirStatement>> getBasicExpressionCheckers() {
        return this.basicExpressionCheckers;
    }

    public Set<FirExpressionChecker<FirBlock>> getBlockCheckers() {
        return this.blockCheckers;
    }

    public Set<FirExpressionChecker<FirBooleanOperatorExpression>> getBooleanOperatorExpressionCheckers() {
        return this.booleanOperatorExpressionCheckers;
    }

    public Set<FirExpressionChecker<FirCall>> getCallCheckers() {
        return this.callCheckers;
    }

    public Set<FirExpressionChecker<FirCallableReferenceAccess>> getCallableReferenceAccessCheckers() {
        return this.callableReferenceAccessCheckers;
    }

    public Set<FirExpressionChecker<FirCheckNotNullCall>> getCheckNotNullCallCheckers() {
        return this.checkNotNullCallCheckers;
    }

    public Set<FirExpressionChecker<FirClassReferenceExpression>> getClassReferenceExpressionCheckers() {
        return this.classReferenceExpressionCheckers;
    }

    public Set<FirExpressionChecker<FirCollectionLiteral>> getCollectionLiteralCheckers() {
        return this.collectionLiteralCheckers;
    }

    public Set<FirExpressionChecker<FirDoWhileLoop>> getDoWhileLoopCheckers() {
        return this.doWhileLoopCheckers;
    }

    public Set<FirExpressionChecker<FirElvisExpression>> getElvisExpressionCheckers() {
        return this.elvisExpressionCheckers;
    }

    public Set<FirExpressionChecker<FirEqualityOperatorCall>> getEqualityOperatorCallCheckers() {
        return this.equalityOperatorCallCheckers;
    }

    public Set<FirExpressionChecker<FirFunctionCall>> getFunctionCallCheckers() {
        return this.functionCallCheckers;
    }

    public Set<FirExpressionChecker<FirGetClassCall>> getGetClassCallCheckers() {
        return this.getClassCallCheckers;
    }

    public Set<FirExpressionChecker<FirInaccessibleReceiverExpression>> getInaccessibleReceiverCheckers() {
        return this.inaccessibleReceiverCheckers;
    }

    public Set<FirExpressionChecker<FirIntegerLiteralOperatorCall>> getIntegerLiteralOperatorCallCheckers() {
        return this.integerLiteralOperatorCallCheckers;
    }

    public Set<FirExpressionChecker<FirLiteralExpression>> getLiteralExpressionCheckers() {
        return this.literalExpressionCheckers;
    }

    public Set<FirExpressionChecker<FirLoop>> getLoopExpressionCheckers() {
        return this.loopExpressionCheckers;
    }

    public Set<FirExpressionChecker<FirLoopJump>> getLoopJumpCheckers() {
        return this.loopJumpCheckers;
    }

    public Set<FirExpressionChecker<FirPropertyAccessExpression>> getPropertyAccessExpressionCheckers() {
        return this.propertyAccessExpressionCheckers;
    }

    public Set<FirExpressionChecker<FirQualifiedAccessExpression>> getQualifiedAccessExpressionCheckers() {
        return this.qualifiedAccessExpressionCheckers;
    }

    public Set<FirExpressionChecker<FirReplDeclarationReference>> getReplDeclarationReferenceCheckers() {
        return this.replDeclarationReferenceCheckers;
    }

    public Set<FirExpressionChecker<FirReplExpressionReference>> getReplExpressionReferenceCheckers() {
        return this.replExpressionReferenceCheckers;
    }

    public Set<FirExpressionChecker<FirReplPropertyDelegate>> getReplPropertyDelegateCheckers() {
        return this.replPropertyDelegateCheckers;
    }

    public Set<FirExpressionChecker<FirReplPropertyInitializer>> getReplPropertyInitializerCheckers() {
        return this.replPropertyInitializerCheckers;
    }

    public Set<FirExpressionChecker<FirResolvedQualifier>> getResolvedQualifierCheckers() {
        return this.resolvedQualifierCheckers;
    }

    public Set<FirExpressionChecker<FirReturnExpression>> getReturnExpressionCheckers() {
        return this.returnExpressionCheckers;
    }

    public Set<FirExpressionChecker<FirSafeCallExpression>> getSafeCallExpressionCheckers() {
        return this.safeCallExpressionCheckers;
    }

    public Set<FirExpressionChecker<FirSmartCastExpression>> getSmartCastExpressionCheckers() {
        return this.smartCastExpressionCheckers;
    }

    public Set<FirExpressionChecker<FirStringConcatenationCall>> getStringConcatenationCallCheckers() {
        return this.stringConcatenationCallCheckers;
    }

    public Set<FirExpressionChecker<FirSuperReceiverExpression>> getSuperReceiverExpressionCheckers() {
        return this.superReceiverExpressionCheckers;
    }

    public Set<FirExpressionChecker<FirThisReceiverExpression>> getThisReceiverExpressionCheckers() {
        return this.thisReceiverExpressionCheckers;
    }

    public Set<FirExpressionChecker<FirThrowExpression>> getThrowExpressionCheckers() {
        return this.throwExpressionCheckers;
    }

    public Set<FirExpressionChecker<FirTryExpression>> getTryExpressionCheckers() {
        return this.tryExpressionCheckers;
    }

    public Set<FirExpressionChecker<FirTypeOperatorCall>> getTypeOperatorCallCheckers() {
        return this.typeOperatorCallCheckers;
    }

    public Set<FirExpressionChecker<FirVariableAssignment>> getVariableAssignmentCheckers() {
        return this.variableAssignmentCheckers;
    }

    public Set<FirExpressionChecker<FirWhenExpression>> getWhenExpressionCheckers() {
        return this.whenExpressionCheckers;
    }

    public Set<FirExpressionChecker<FirWhileLoop>> getWhileLoopCheckers() {
        return this.whileLoopCheckers;
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ExpressionCheckers$Companion;", Argument.Delimiters.none, "<init>", "()V", "EMPTY", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ExpressionCheckers;", "getEMPTY", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ExpressionCheckers;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final ExpressionCheckers getEMPTY() {
            return ExpressionCheckers.EMPTY;
        }

        private Companion() {
        }
    }
}
