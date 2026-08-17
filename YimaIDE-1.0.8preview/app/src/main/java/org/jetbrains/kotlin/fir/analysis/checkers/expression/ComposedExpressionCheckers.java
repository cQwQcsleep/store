package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.Set;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.analysis.CheckersComponentInternal;
import org.jetbrains.kotlin.fir.analysis.checkers.FirCheckerWithMppKind;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.ComposedExpressionCheckers;
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
@Metadata(d1 = {"\u0000\u0092\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\b'\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0012\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\u0004\b\u0006\u0010\u0007B\u0011\b\u0016\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\u0006\u0010\nJ\u0018\u0010Ô\u0001\u001a\u00030Õ\u00012\u0007\u0010Ö\u0001\u001a\u00020\u0001H\u0007b\u0003\b×\u0001R\u001d\u0010\u0002\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00050\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR$\u0010\r\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00100\u000fj\u0002`\u00110\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R$\u0010\u0014\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00150\u000fj\u0002`\u00160\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013R$\u0010\u0018\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00190\u000fj\u0002`\u001a0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0013R$\u0010\u001c\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u001d0\u000fj\u0002`\u001e0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0013R$\u0010 \u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020!0\u000fj\u0002`\"0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b#\u0010\u0013R$\u0010$\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020%0\u000fj\u0002`&0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b'\u0010\u0013R$\u0010(\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020)0\u000fj\u0002`*0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b+\u0010\u0013R$\u0010,\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020-0\u000fj\u0002`.0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b/\u0010\u0013R$\u00100\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002010\u000fj\u0002`20\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b3\u0010\u0013R$\u00104\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002050\u000fj\u0002`60\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b7\u0010\u0013R$\u00108\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002090\u000fj\u0002`:0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b;\u0010\u0013R$\u0010<\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020=0\u000fj\u0002`>0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b?\u0010\u0013R$\u0010@\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020A0\u000fj\u0002`B0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bC\u0010\u0013R$\u0010D\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020E0\u000fj\u0002`F0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bG\u0010\u0013R$\u0010H\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020I0\u000fj\u0002`J0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bK\u0010\u0013R$\u0010L\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020M0\u000fj\u0002`N0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bO\u0010\u0013R$\u0010P\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020Q0\u000fj\u0002`R0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bS\u0010\u0013R$\u0010T\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020U0\u000fj\u0002`V0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bW\u0010\u0013R$\u0010X\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020Y0\u000fj\u0002`Z0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b[\u0010\u0013R$\u0010\\\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020]0\u000fj\u0002`^0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b_\u0010\u0013R$\u0010`\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020a0\u000fj\u0002`b0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bc\u0010\u0013R$\u0010d\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020e0\u000fj\u0002`f0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bg\u0010\u0013R$\u0010h\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020i0\u000fj\u0002`j0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bk\u0010\u0013R$\u0010l\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020m0\u000fj\u0002`n0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bo\u0010\u0013R$\u0010p\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020q0\u000fj\u0002`r0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bs\u0010\u0013R$\u0010t\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020u0\u000fj\u0002`v0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\bw\u0010\u0013R$\u0010x\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020y0\u000fj\u0002`z0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b{\u0010\u0013R$\u0010|\u001a\u0012\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020}0\u000fj\u0002`~0\u000e8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u007f\u0010\u0013R(\u0010\u0080\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0081\u00010\u000fj\u0003`\u0082\u00010\u000e8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0083\u0001\u0010\u0013R(\u0010\u0084\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0085\u00010\u000fj\u0003`\u0086\u00010\u000e8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0087\u0001\u0010\u0013R(\u0010\u0088\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0089\u00010\u000fj\u0003`\u008a\u00010\u000e8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u008b\u0001\u0010\u0013R(\u0010\u008c\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u008d\u00010\u000fj\u0003`\u008e\u00010\u000e8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u008f\u0001\u0010\u0013R(\u0010\u0090\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0091\u00010\u000fj\u0003`\u0092\u00010\u000e8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0093\u0001\u0010\u0013R(\u0010\u0094\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0095\u00010\u000fj\u0003`\u0096\u00010\u000e8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u0097\u0001\u0010\u0013R(\u0010\u0098\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0099\u00010\u000fj\u0003`\u009a\u00010\u000e8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u009b\u0001\u0010\u0013R(\u0010\u009c\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u009d\u00010\u000fj\u0003`\u009e\u00010\u000e8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b\u009f\u0001\u0010\u0013R(\u0010 \u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030¡\u00010\u000fj\u0003`¢\u00010\u000e8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b£\u0001\u0010\u0013R(\u0010¤\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030¥\u00010\u000fj\u0003`¦\u00010\u000e8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b§\u0001\u0010\u0013R(\u0010¨\u0001\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030©\u00010\u000fj\u0003`ª\u00010\u000e8VX\u0096\u0004¢\u0006\u0007\u001a\u0005\b«\u0001\u0010\u0013R \u0010¬\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00100\u000fj\u0002`\u00110\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010®\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00150\u000fj\u0002`\u00160\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010¯\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u00190\u000fj\u0002`\u001a0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010°\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020\u001d0\u000fj\u0002`\u001e0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010±\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020!0\u000fj\u0002`\"0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010²\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020%0\u000fj\u0002`&0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010³\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020)0\u000fj\u0002`*0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010´\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020-0\u000fj\u0002`.0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010µ\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002010\u000fj\u0002`20\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010¶\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002050\u000fj\u0002`60\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010·\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u0002090\u000fj\u0002`:0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010¸\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020=0\u000fj\u0002`>0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010¹\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020A0\u000fj\u0002`B0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010º\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020E0\u000fj\u0002`F0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010»\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020I0\u000fj\u0002`J0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010¼\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020M0\u000fj\u0002`N0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010½\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020Q0\u000fj\u0002`R0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010¾\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020U0\u000fj\u0002`V0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010¿\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020Y0\u000fj\u0002`Z0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010À\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020]0\u000fj\u0002`^0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010Á\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020a0\u000fj\u0002`b0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010Â\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020e0\u000fj\u0002`f0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010Ã\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020i0\u000fj\u0002`j0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010Ä\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020m0\u000fj\u0002`n0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010Å\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020q0\u000fj\u0002`r0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010Æ\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020u0\u000fj\u0002`v0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010Ç\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020y0\u000fj\u0002`z0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010È\u0001\u001a\u0013\u0012\u000e\u0012\f\u0012\u0004\u0012\u00020}0\u000fj\u0002`~0\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010É\u0001\u001a\u0015\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0081\u00010\u000fj\u0003`\u0082\u00010\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010Ê\u0001\u001a\u0015\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0085\u00010\u000fj\u0003`\u0086\u00010\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010Ë\u0001\u001a\u0015\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0089\u00010\u000fj\u0003`\u008a\u00010\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010Ì\u0001\u001a\u0015\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u008d\u00010\u000fj\u0003`\u008e\u00010\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010Í\u0001\u001a\u0015\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0091\u00010\u000fj\u0003`\u0092\u00010\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010Î\u0001\u001a\u0015\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0095\u00010\u000fj\u0003`\u0096\u00010\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010Ï\u0001\u001a\u0015\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u0099\u00010\u000fj\u0003`\u009a\u00010\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010Ð\u0001\u001a\u0015\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030\u009d\u00010\u000fj\u0003`\u009e\u00010\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010Ñ\u0001\u001a\u0015\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030¡\u00010\u000fj\u0003`¢\u00010\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010Ò\u0001\u001a\u0015\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030¥\u00010\u000fj\u0003`¦\u00010\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\"\u0010Ó\u0001\u001a\u0015\u0012\u0010\u0012\u000e\u0012\u0005\u0012\u00030©\u00010\u000fj\u0003`ª\u00010\u00ad\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006Ø\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ComposedExpressionCheckers;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ExpressionCheckers;", "predicate", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/FirCheckerWithMppKind;", Argument.Delimiters.none, "<init>", "(Lkotlin/jvm/functions/Function1;)V", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;)V", "getPredicate", "()Lkotlin/jvm/functions/Function1;", "basicExpressionCheckers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBasicExpressionChecker;", "getBasicExpressionCheckers", "()Ljava/util/Set;", "qualifiedAccessExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirQualifiedAccessExpressionChecker;", "getQualifiedAccessExpressionCheckers", "callCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCallChecker;", "getCallCheckers", "functionCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirFunctionCallChecker;", "getFunctionCallCheckers", "propertyAccessExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirPropertyAccessExpressionChecker;", "getPropertyAccessExpressionCheckers", "superReceiverExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirSuperReceiverExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirSuperReceiverExpressionChecker;", "getSuperReceiverExpressionCheckers", "integerLiteralOperatorCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirIntegerLiteralOperatorCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirIntegerLiteralOperatorCallChecker;", "getIntegerLiteralOperatorCallCheckers", "variableAssignmentCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirVariableAssignmentChecker;", "getVariableAssignmentCheckers", "tryExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirTryExpressionChecker;", "getTryExpressionCheckers", "whenExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirWhenExpressionChecker;", "getWhenExpressionCheckers", "loopExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirLoop;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirLoopExpressionChecker;", "getLoopExpressionCheckers", "loopJumpCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirLoopJump;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirLoopJumpChecker;", "getLoopJumpCheckers", "booleanOperatorExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBooleanOperatorExpressionChecker;", "getBooleanOperatorExpressionCheckers", "returnExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReturnExpressionChecker;", "getReturnExpressionCheckers", "blockCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirBlockChecker;", "getBlockCheckers", "replDeclarationReferenceCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirReplDeclarationReference;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReplDeclarationReferenceChecker;", "getReplDeclarationReferenceCheckers", "replPropertyInitializerCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyInitializer;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReplPropertyInitializerChecker;", "getReplPropertyInitializerCheckers", "replPropertyDelegateCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyDelegate;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReplPropertyDelegateChecker;", "getReplPropertyDelegateCheckers", "replExpressionReferenceCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirReplExpressionReference;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirReplExpressionReferenceChecker;", "getReplExpressionReferenceCheckers", "annotationCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirAnnotationChecker;", "getAnnotationCheckers", "annotationCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirAnnotationCallChecker;", "getAnnotationCallCheckers", "checkNotNullCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckNotNullCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCheckNotNullCallChecker;", "getCheckNotNullCallCheckers", "elvisExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirElvisExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirElvisExpressionChecker;", "getElvisExpressionCheckers", "getClassCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirGetClassCallChecker;", "getGetClassCallCheckers", "safeCallExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirSafeCallExpressionChecker;", "getSafeCallExpressionCheckers", "smartCastExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirSmartCastExpressionChecker;", "getSmartCastExpressionCheckers", "equalityOperatorCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirEqualityOperatorCallChecker;", "getEqualityOperatorCallCheckers", "stringConcatenationCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirStringConcatenationCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirStringConcatenationCallChecker;", "getStringConcatenationCallCheckers", "typeOperatorCallCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirTypeOperatorCallChecker;", "getTypeOperatorCallCheckers", "resolvedQualifierCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirResolvedQualifierChecker;", "getResolvedQualifierCheckers", "literalExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirLiteralExpressionChecker;", "getLiteralExpressionCheckers", "callableReferenceAccessCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCallableReferenceAccessChecker;", "getCallableReferenceAccessCheckers", "thisReceiverExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirThisReceiverExpressionChecker;", "getThisReceiverExpressionCheckers", "whileLoopCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirWhileLoop;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirWhileLoopChecker;", "getWhileLoopCheckers", "throwExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirThrowExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirThrowExpressionChecker;", "getThrowExpressionCheckers", "doWhileLoopCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirDoWhileLoop;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirDoWhileLoopChecker;", "getDoWhileLoopCheckers", "collectionLiteralCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirCollectionLiteralChecker;", "getCollectionLiteralCheckers", "classReferenceExpressionCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirClassReferenceExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirClassReferenceExpressionChecker;", "getClassReferenceExpressionCheckers", "inaccessibleReceiverCheckers", "Lorg/jetbrains/kotlin/fir/expressions/FirInaccessibleReceiverExpression;", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirInaccessibleReceiverChecker;", "getInaccessibleReceiverCheckers", "_basicExpressionCheckers", Argument.Delimiters.none, "_qualifiedAccessExpressionCheckers", "_callCheckers", "_functionCallCheckers", "_propertyAccessExpressionCheckers", "_superReceiverExpressionCheckers", "_integerLiteralOperatorCallCheckers", "_variableAssignmentCheckers", "_tryExpressionCheckers", "_whenExpressionCheckers", "_loopExpressionCheckers", "_loopJumpCheckers", "_booleanOperatorExpressionCheckers", "_returnExpressionCheckers", "_blockCheckers", "_replDeclarationReferenceCheckers", "_replPropertyInitializerCheckers", "_replPropertyDelegateCheckers", "_replExpressionReferenceCheckers", "_annotationCheckers", "_annotationCallCheckers", "_checkNotNullCallCheckers", "_elvisExpressionCheckers", "_getClassCallCheckers", "_safeCallExpressionCheckers", "_smartCastExpressionCheckers", "_equalityOperatorCallCheckers", "_stringConcatenationCallCheckers", "_typeOperatorCallCheckers", "_resolvedQualifierCheckers", "_literalExpressionCheckers", "_callableReferenceAccessCheckers", "_thisReceiverExpressionCheckers", "_whileLoopCheckers", "_throwExpressionCheckers", "_doWhileLoopCheckers", "_collectionLiteralCheckers", "_classReferenceExpressionCheckers", "_inaccessibleReceiverCheckers", "register", Argument.Delimiters.none, "checkers", "Lorg/jetbrains/kotlin/fir/analysis/CheckersComponentInternal;", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ComposedExpressionCheckers extends ExpressionCheckers {
    private final Set<FirExpressionChecker<FirAnnotationCall>> _annotationCallCheckers;
    private final Set<FirExpressionChecker<FirAnnotation>> _annotationCheckers;
    private final Set<FirExpressionChecker<FirStatement>> _basicExpressionCheckers;
    private final Set<FirExpressionChecker<FirBlock>> _blockCheckers;
    private final Set<FirExpressionChecker<FirBooleanOperatorExpression>> _booleanOperatorExpressionCheckers;
    private final Set<FirExpressionChecker<FirCall>> _callCheckers;
    private final Set<FirExpressionChecker<FirCallableReferenceAccess>> _callableReferenceAccessCheckers;
    private final Set<FirExpressionChecker<FirCheckNotNullCall>> _checkNotNullCallCheckers;
    private final Set<FirExpressionChecker<FirClassReferenceExpression>> _classReferenceExpressionCheckers;
    private final Set<FirExpressionChecker<FirCollectionLiteral>> _collectionLiteralCheckers;
    private final Set<FirExpressionChecker<FirDoWhileLoop>> _doWhileLoopCheckers;
    private final Set<FirExpressionChecker<FirElvisExpression>> _elvisExpressionCheckers;
    private final Set<FirExpressionChecker<FirEqualityOperatorCall>> _equalityOperatorCallCheckers;
    private final Set<FirExpressionChecker<FirFunctionCall>> _functionCallCheckers;
    private final Set<FirExpressionChecker<FirGetClassCall>> _getClassCallCheckers;
    private final Set<FirExpressionChecker<FirInaccessibleReceiverExpression>> _inaccessibleReceiverCheckers;
    private final Set<FirExpressionChecker<FirIntegerLiteralOperatorCall>> _integerLiteralOperatorCallCheckers;
    private final Set<FirExpressionChecker<FirLiteralExpression>> _literalExpressionCheckers;
    private final Set<FirExpressionChecker<FirLoop>> _loopExpressionCheckers;
    private final Set<FirExpressionChecker<FirLoopJump>> _loopJumpCheckers;
    private final Set<FirExpressionChecker<FirPropertyAccessExpression>> _propertyAccessExpressionCheckers;
    private final Set<FirExpressionChecker<FirQualifiedAccessExpression>> _qualifiedAccessExpressionCheckers;
    private final Set<FirExpressionChecker<FirReplDeclarationReference>> _replDeclarationReferenceCheckers;
    private final Set<FirExpressionChecker<FirReplExpressionReference>> _replExpressionReferenceCheckers;
    private final Set<FirExpressionChecker<FirReplPropertyDelegate>> _replPropertyDelegateCheckers;
    private final Set<FirExpressionChecker<FirReplPropertyInitializer>> _replPropertyInitializerCheckers;
    private final Set<FirExpressionChecker<FirResolvedQualifier>> _resolvedQualifierCheckers;
    private final Set<FirExpressionChecker<FirReturnExpression>> _returnExpressionCheckers;
    private final Set<FirExpressionChecker<FirSafeCallExpression>> _safeCallExpressionCheckers;
    private final Set<FirExpressionChecker<FirSmartCastExpression>> _smartCastExpressionCheckers;
    private final Set<FirExpressionChecker<FirStringConcatenationCall>> _stringConcatenationCallCheckers;
    private final Set<FirExpressionChecker<FirSuperReceiverExpression>> _superReceiverExpressionCheckers;
    private final Set<FirExpressionChecker<FirThisReceiverExpression>> _thisReceiverExpressionCheckers;
    private final Set<FirExpressionChecker<FirThrowExpression>> _throwExpressionCheckers;
    private final Set<FirExpressionChecker<FirTryExpression>> _tryExpressionCheckers;
    private final Set<FirExpressionChecker<FirTypeOperatorCall>> _typeOperatorCallCheckers;
    private final Set<FirExpressionChecker<FirVariableAssignment>> _variableAssignmentCheckers;
    private final Set<FirExpressionChecker<FirWhenExpression>> _whenExpressionCheckers;
    private final Set<FirExpressionChecker<FirWhileLoop>> _whileLoopCheckers;
    private final Function1<FirCheckerWithMppKind, Boolean> predicate;

    /* JADX WARN: Multi-variable type inference failed */
    public ComposedExpressionCheckers(Function1<? super FirCheckerWithMppKind, Boolean> function1) {
        function1.getClass();
        this.predicate = function1;
        this._basicExpressionCheckers = new LinkedHashSet();
        this._qualifiedAccessExpressionCheckers = new LinkedHashSet();
        this._callCheckers = new LinkedHashSet();
        this._functionCallCheckers = new LinkedHashSet();
        this._propertyAccessExpressionCheckers = new LinkedHashSet();
        this._superReceiverExpressionCheckers = new LinkedHashSet();
        this._integerLiteralOperatorCallCheckers = new LinkedHashSet();
        this._variableAssignmentCheckers = new LinkedHashSet();
        this._tryExpressionCheckers = new LinkedHashSet();
        this._whenExpressionCheckers = new LinkedHashSet();
        this._loopExpressionCheckers = new LinkedHashSet();
        this._loopJumpCheckers = new LinkedHashSet();
        this._booleanOperatorExpressionCheckers = new LinkedHashSet();
        this._returnExpressionCheckers = new LinkedHashSet();
        this._blockCheckers = new LinkedHashSet();
        this._replDeclarationReferenceCheckers = new LinkedHashSet();
        this._replPropertyInitializerCheckers = new LinkedHashSet();
        this._replPropertyDelegateCheckers = new LinkedHashSet();
        this._replExpressionReferenceCheckers = new LinkedHashSet();
        this._annotationCheckers = new LinkedHashSet();
        this._annotationCallCheckers = new LinkedHashSet();
        this._checkNotNullCallCheckers = new LinkedHashSet();
        this._elvisExpressionCheckers = new LinkedHashSet();
        this._getClassCallCheckers = new LinkedHashSet();
        this._safeCallExpressionCheckers = new LinkedHashSet();
        this._smartCastExpressionCheckers = new LinkedHashSet();
        this._equalityOperatorCallCheckers = new LinkedHashSet();
        this._stringConcatenationCallCheckers = new LinkedHashSet();
        this._typeOperatorCallCheckers = new LinkedHashSet();
        this._resolvedQualifierCheckers = new LinkedHashSet();
        this._literalExpressionCheckers = new LinkedHashSet();
        this._callableReferenceAccessCheckers = new LinkedHashSet();
        this._thisReceiverExpressionCheckers = new LinkedHashSet();
        this._whileLoopCheckers = new LinkedHashSet();
        this._throwExpressionCheckers = new LinkedHashSet();
        this._doWhileLoopCheckers = new LinkedHashSet();
        this._collectionLiteralCheckers = new LinkedHashSet();
        this._classReferenceExpressionCheckers = new LinkedHashSet();
        this._inaccessibleReceiverCheckers = new LinkedHashSet();
    }

    public static boolean N(MppCheckerKind mppCheckerKind, FirCheckerWithMppKind firCheckerWithMppKind) {
        firCheckerWithMppKind.getClass();
        return firCheckerWithMppKind.getMppKind() == mppCheckerKind;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirAnnotationCall>> getAnnotationCallCheckers() {
        return this._annotationCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirAnnotation>> getAnnotationCheckers() {
        return this._annotationCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirStatement>> getBasicExpressionCheckers() {
        return this._basicExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirBlock>> getBlockCheckers() {
        return this._blockCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirBooleanOperatorExpression>> getBooleanOperatorExpressionCheckers() {
        return this._booleanOperatorExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirCall>> getCallCheckers() {
        return this._callCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirCallableReferenceAccess>> getCallableReferenceAccessCheckers() {
        return this._callableReferenceAccessCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirCheckNotNullCall>> getCheckNotNullCallCheckers() {
        return this._checkNotNullCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirClassReferenceExpression>> getClassReferenceExpressionCheckers() {
        return this._classReferenceExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirCollectionLiteral>> getCollectionLiteralCheckers() {
        return this._collectionLiteralCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirDoWhileLoop>> getDoWhileLoopCheckers() {
        return this._doWhileLoopCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirElvisExpression>> getElvisExpressionCheckers() {
        return this._elvisExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirEqualityOperatorCall>> getEqualityOperatorCallCheckers() {
        return this._equalityOperatorCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirFunctionCall>> getFunctionCallCheckers() {
        return this._functionCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirGetClassCall>> getGetClassCallCheckers() {
        return this._getClassCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirInaccessibleReceiverExpression>> getInaccessibleReceiverCheckers() {
        return this._inaccessibleReceiverCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirIntegerLiteralOperatorCall>> getIntegerLiteralOperatorCallCheckers() {
        return this._integerLiteralOperatorCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirLiteralExpression>> getLiteralExpressionCheckers() {
        return this._literalExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirLoop>> getLoopExpressionCheckers() {
        return this._loopExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirLoopJump>> getLoopJumpCheckers() {
        return this._loopJumpCheckers;
    }

    public final Function1<FirCheckerWithMppKind, Boolean> getPredicate() {
        return this.predicate;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirPropertyAccessExpression>> getPropertyAccessExpressionCheckers() {
        return this._propertyAccessExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirQualifiedAccessExpression>> getQualifiedAccessExpressionCheckers() {
        return this._qualifiedAccessExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirReplDeclarationReference>> getReplDeclarationReferenceCheckers() {
        return this._replDeclarationReferenceCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirReplExpressionReference>> getReplExpressionReferenceCheckers() {
        return this._replExpressionReferenceCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirReplPropertyDelegate>> getReplPropertyDelegateCheckers() {
        return this._replPropertyDelegateCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirReplPropertyInitializer>> getReplPropertyInitializerCheckers() {
        return this._replPropertyInitializerCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirResolvedQualifier>> getResolvedQualifierCheckers() {
        return this._resolvedQualifierCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirReturnExpression>> getReturnExpressionCheckers() {
        return this._returnExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirSafeCallExpression>> getSafeCallExpressionCheckers() {
        return this._safeCallExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirSmartCastExpression>> getSmartCastExpressionCheckers() {
        return this._smartCastExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirStringConcatenationCall>> getStringConcatenationCallCheckers() {
        return this._stringConcatenationCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirSuperReceiverExpression>> getSuperReceiverExpressionCheckers() {
        return this._superReceiverExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirThisReceiverExpression>> getThisReceiverExpressionCheckers() {
        return this._thisReceiverExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirThrowExpression>> getThrowExpressionCheckers() {
        return this._throwExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirTryExpression>> getTryExpressionCheckers() {
        return this._tryExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirTypeOperatorCall>> getTypeOperatorCallCheckers() {
        return this._typeOperatorCallCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirVariableAssignment>> getVariableAssignmentCheckers() {
        return this._variableAssignmentCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirWhenExpression>> getWhenExpressionCheckers() {
        return this._whenExpressionCheckers;
    }

    @Override // org.jetbrains.kotlin.fir.analysis.checkers.expression.ExpressionCheckers
    public Set<FirExpressionChecker<FirWhileLoop>> getWhileLoopCheckers() {
        return this._whileLoopCheckers;
    }

    @CheckersComponentInternal
    public final void register(ExpressionCheckers checkers) {
        checkers.getClass();
        Set<FirExpressionChecker<FirStatement>> basicExpressionCheckers = checkers.getBasicExpressionCheckers();
        Collection collection = this._basicExpressionCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function1 = this.predicate;
        for (Object obj : basicExpressionCheckers) {
            if (((Boolean) function1.invoke(obj)).booleanValue()) {
                collection.add(obj);
            }
        }
        Set<FirExpressionChecker<FirQualifiedAccessExpression>> qualifiedAccessExpressionCheckers = checkers.getQualifiedAccessExpressionCheckers();
        Collection collection2 = this._qualifiedAccessExpressionCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function2 = this.predicate;
        for (Object obj2 : qualifiedAccessExpressionCheckers) {
            if (((Boolean) function2.invoke(obj2)).booleanValue()) {
                collection2.add(obj2);
            }
        }
        Set<FirExpressionChecker<FirCall>> callCheckers = checkers.getCallCheckers();
        Collection collection3 = this._callCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function3 = this.predicate;
        for (Object obj3 : callCheckers) {
            if (((Boolean) function3.invoke(obj3)).booleanValue()) {
                collection3.add(obj3);
            }
        }
        Set<FirExpressionChecker<FirFunctionCall>> functionCallCheckers = checkers.getFunctionCallCheckers();
        Collection collection4 = this._functionCallCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function4 = this.predicate;
        for (Object obj4 : functionCallCheckers) {
            if (((Boolean) function4.invoke(obj4)).booleanValue()) {
                collection4.add(obj4);
            }
        }
        Set<FirExpressionChecker<FirPropertyAccessExpression>> propertyAccessExpressionCheckers = checkers.getPropertyAccessExpressionCheckers();
        Collection collection5 = this._propertyAccessExpressionCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function5 = this.predicate;
        for (Object obj5 : propertyAccessExpressionCheckers) {
            if (((Boolean) function5.invoke(obj5)).booleanValue()) {
                collection5.add(obj5);
            }
        }
        Set<FirExpressionChecker<FirSuperReceiverExpression>> superReceiverExpressionCheckers = checkers.getSuperReceiverExpressionCheckers();
        Collection collection6 = this._superReceiverExpressionCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function6 = this.predicate;
        for (Object obj6 : superReceiverExpressionCheckers) {
            if (((Boolean) function6.invoke(obj6)).booleanValue()) {
                collection6.add(obj6);
            }
        }
        Set<FirExpressionChecker<FirIntegerLiteralOperatorCall>> integerLiteralOperatorCallCheckers = checkers.getIntegerLiteralOperatorCallCheckers();
        Collection collection7 = this._integerLiteralOperatorCallCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function7 = this.predicate;
        for (Object obj7 : integerLiteralOperatorCallCheckers) {
            if (((Boolean) function7.invoke(obj7)).booleanValue()) {
                collection7.add(obj7);
            }
        }
        Set<FirExpressionChecker<FirVariableAssignment>> variableAssignmentCheckers = checkers.getVariableAssignmentCheckers();
        Collection collection8 = this._variableAssignmentCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function8 = this.predicate;
        for (Object obj8 : variableAssignmentCheckers) {
            if (((Boolean) function8.invoke(obj8)).booleanValue()) {
                collection8.add(obj8);
            }
        }
        Set<FirExpressionChecker<FirTryExpression>> tryExpressionCheckers = checkers.getTryExpressionCheckers();
        Collection collection9 = this._tryExpressionCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function9 = this.predicate;
        for (Object obj9 : tryExpressionCheckers) {
            if (((Boolean) function9.invoke(obj9)).booleanValue()) {
                collection9.add(obj9);
            }
        }
        Set<FirExpressionChecker<FirWhenExpression>> whenExpressionCheckers = checkers.getWhenExpressionCheckers();
        Collection collection10 = this._whenExpressionCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function10 = this.predicate;
        for (Object obj10 : whenExpressionCheckers) {
            if (((Boolean) function10.invoke(obj10)).booleanValue()) {
                collection10.add(obj10);
            }
        }
        Set<FirExpressionChecker<FirLoop>> loopExpressionCheckers = checkers.getLoopExpressionCheckers();
        Collection collection11 = this._loopExpressionCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function11 = this.predicate;
        for (Object obj11 : loopExpressionCheckers) {
            if (((Boolean) function11.invoke(obj11)).booleanValue()) {
                collection11.add(obj11);
            }
        }
        Set<FirExpressionChecker<FirLoopJump>> loopJumpCheckers = checkers.getLoopJumpCheckers();
        Collection collection12 = this._loopJumpCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function12 = this.predicate;
        for (Object obj12 : loopJumpCheckers) {
            if (((Boolean) function12.invoke(obj12)).booleanValue()) {
                collection12.add(obj12);
            }
        }
        Set<FirExpressionChecker<FirBooleanOperatorExpression>> booleanOperatorExpressionCheckers = checkers.getBooleanOperatorExpressionCheckers();
        Collection collection13 = this._booleanOperatorExpressionCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function13 = this.predicate;
        for (Object obj13 : booleanOperatorExpressionCheckers) {
            if (((Boolean) function13.invoke(obj13)).booleanValue()) {
                collection13.add(obj13);
            }
        }
        Set<FirExpressionChecker<FirReturnExpression>> returnExpressionCheckers = checkers.getReturnExpressionCheckers();
        Collection collection14 = this._returnExpressionCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function14 = this.predicate;
        for (Object obj14 : returnExpressionCheckers) {
            if (((Boolean) function14.invoke(obj14)).booleanValue()) {
                collection14.add(obj14);
            }
        }
        Set<FirExpressionChecker<FirBlock>> blockCheckers = checkers.getBlockCheckers();
        Collection collection15 = this._blockCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function15 = this.predicate;
        for (Object obj15 : blockCheckers) {
            if (((Boolean) function15.invoke(obj15)).booleanValue()) {
                collection15.add(obj15);
            }
        }
        Set<FirExpressionChecker<FirReplDeclarationReference>> replDeclarationReferenceCheckers = checkers.getReplDeclarationReferenceCheckers();
        Collection collection16 = this._replDeclarationReferenceCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function16 = this.predicate;
        for (Object obj16 : replDeclarationReferenceCheckers) {
            if (((Boolean) function16.invoke(obj16)).booleanValue()) {
                collection16.add(obj16);
            }
        }
        Set<FirExpressionChecker<FirReplPropertyInitializer>> replPropertyInitializerCheckers = checkers.getReplPropertyInitializerCheckers();
        Collection collection17 = this._replPropertyInitializerCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function17 = this.predicate;
        for (Object obj17 : replPropertyInitializerCheckers) {
            if (((Boolean) function17.invoke(obj17)).booleanValue()) {
                collection17.add(obj17);
            }
        }
        Set<FirExpressionChecker<FirReplPropertyDelegate>> replPropertyDelegateCheckers = checkers.getReplPropertyDelegateCheckers();
        Collection collection18 = this._replPropertyDelegateCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function18 = this.predicate;
        for (Object obj18 : replPropertyDelegateCheckers) {
            if (((Boolean) function18.invoke(obj18)).booleanValue()) {
                collection18.add(obj18);
            }
        }
        Set<FirExpressionChecker<FirReplExpressionReference>> replExpressionReferenceCheckers = checkers.getReplExpressionReferenceCheckers();
        Collection collection19 = this._replExpressionReferenceCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function19 = this.predicate;
        for (Object obj19 : replExpressionReferenceCheckers) {
            if (((Boolean) function19.invoke(obj19)).booleanValue()) {
                collection19.add(obj19);
            }
        }
        Set<FirExpressionChecker<FirAnnotation>> annotationCheckers = checkers.getAnnotationCheckers();
        Collection collection20 = this._annotationCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function20 = this.predicate;
        for (Object obj20 : annotationCheckers) {
            if (((Boolean) function20.invoke(obj20)).booleanValue()) {
                collection20.add(obj20);
            }
        }
        Set<FirExpressionChecker<FirAnnotationCall>> annotationCallCheckers = checkers.getAnnotationCallCheckers();
        Collection collection21 = this._annotationCallCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function21 = this.predicate;
        for (Object obj21 : annotationCallCheckers) {
            if (((Boolean) function21.invoke(obj21)).booleanValue()) {
                collection21.add(obj21);
            }
        }
        Set<FirExpressionChecker<FirCheckNotNullCall>> checkNotNullCallCheckers = checkers.getCheckNotNullCallCheckers();
        Collection collection22 = this._checkNotNullCallCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function22 = this.predicate;
        for (Object obj22 : checkNotNullCallCheckers) {
            if (((Boolean) function22.invoke(obj22)).booleanValue()) {
                collection22.add(obj22);
            }
        }
        Set<FirExpressionChecker<FirElvisExpression>> elvisExpressionCheckers = checkers.getElvisExpressionCheckers();
        Collection collection23 = this._elvisExpressionCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function23 = this.predicate;
        for (Object obj23 : elvisExpressionCheckers) {
            if (((Boolean) function23.invoke(obj23)).booleanValue()) {
                collection23.add(obj23);
            }
        }
        Set<FirExpressionChecker<FirGetClassCall>> getClassCallCheckers = checkers.getGetClassCallCheckers();
        Collection collection24 = this._getClassCallCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function24 = this.predicate;
        for (Object obj24 : getClassCallCheckers) {
            if (((Boolean) function24.invoke(obj24)).booleanValue()) {
                collection24.add(obj24);
            }
        }
        Set<FirExpressionChecker<FirSafeCallExpression>> safeCallExpressionCheckers = checkers.getSafeCallExpressionCheckers();
        Collection collection25 = this._safeCallExpressionCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function25 = this.predicate;
        for (Object obj25 : safeCallExpressionCheckers) {
            if (((Boolean) function25.invoke(obj25)).booleanValue()) {
                collection25.add(obj25);
            }
        }
        Set<FirExpressionChecker<FirSmartCastExpression>> smartCastExpressionCheckers = checkers.getSmartCastExpressionCheckers();
        Collection collection26 = this._smartCastExpressionCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function26 = this.predicate;
        for (Object obj26 : smartCastExpressionCheckers) {
            if (((Boolean) function26.invoke(obj26)).booleanValue()) {
                collection26.add(obj26);
            }
        }
        Set<FirExpressionChecker<FirEqualityOperatorCall>> equalityOperatorCallCheckers = checkers.getEqualityOperatorCallCheckers();
        Collection collection27 = this._equalityOperatorCallCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function27 = this.predicate;
        for (Object obj27 : equalityOperatorCallCheckers) {
            if (((Boolean) function27.invoke(obj27)).booleanValue()) {
                collection27.add(obj27);
            }
        }
        Set<FirExpressionChecker<FirStringConcatenationCall>> stringConcatenationCallCheckers = checkers.getStringConcatenationCallCheckers();
        Collection collection28 = this._stringConcatenationCallCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function28 = this.predicate;
        for (Object obj28 : stringConcatenationCallCheckers) {
            if (((Boolean) function28.invoke(obj28)).booleanValue()) {
                collection28.add(obj28);
            }
        }
        Set<FirExpressionChecker<FirTypeOperatorCall>> typeOperatorCallCheckers = checkers.getTypeOperatorCallCheckers();
        Collection collection29 = this._typeOperatorCallCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function29 = this.predicate;
        for (Object obj29 : typeOperatorCallCheckers) {
            if (((Boolean) function29.invoke(obj29)).booleanValue()) {
                collection29.add(obj29);
            }
        }
        Set<FirExpressionChecker<FirResolvedQualifier>> resolvedQualifierCheckers = checkers.getResolvedQualifierCheckers();
        Collection collection30 = this._resolvedQualifierCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function30 = this.predicate;
        for (Object obj30 : resolvedQualifierCheckers) {
            if (((Boolean) function30.invoke(obj30)).booleanValue()) {
                collection30.add(obj30);
            }
        }
        Set<FirExpressionChecker<FirLiteralExpression>> literalExpressionCheckers = checkers.getLiteralExpressionCheckers();
        Collection collection31 = this._literalExpressionCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function31 = this.predicate;
        for (Object obj31 : literalExpressionCheckers) {
            if (((Boolean) function31.invoke(obj31)).booleanValue()) {
                collection31.add(obj31);
            }
        }
        Set<FirExpressionChecker<FirCallableReferenceAccess>> callableReferenceAccessCheckers = checkers.getCallableReferenceAccessCheckers();
        Collection collection32 = this._callableReferenceAccessCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function32 = this.predicate;
        for (Object obj32 : callableReferenceAccessCheckers) {
            if (((Boolean) function32.invoke(obj32)).booleanValue()) {
                collection32.add(obj32);
            }
        }
        Set<FirExpressionChecker<FirThisReceiverExpression>> thisReceiverExpressionCheckers = checkers.getThisReceiverExpressionCheckers();
        Collection collection33 = this._thisReceiverExpressionCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function33 = this.predicate;
        for (Object obj33 : thisReceiverExpressionCheckers) {
            if (((Boolean) function33.invoke(obj33)).booleanValue()) {
                collection33.add(obj33);
            }
        }
        Set<FirExpressionChecker<FirWhileLoop>> whileLoopCheckers = checkers.getWhileLoopCheckers();
        Collection collection34 = this._whileLoopCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function34 = this.predicate;
        for (Object obj34 : whileLoopCheckers) {
            if (((Boolean) function34.invoke(obj34)).booleanValue()) {
                collection34.add(obj34);
            }
        }
        Set<FirExpressionChecker<FirThrowExpression>> throwExpressionCheckers = checkers.getThrowExpressionCheckers();
        Collection collection35 = this._throwExpressionCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function35 = this.predicate;
        for (Object obj35 : throwExpressionCheckers) {
            if (((Boolean) function35.invoke(obj35)).booleanValue()) {
                collection35.add(obj35);
            }
        }
        Set<FirExpressionChecker<FirDoWhileLoop>> doWhileLoopCheckers = checkers.getDoWhileLoopCheckers();
        Collection collection36 = this._doWhileLoopCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function36 = this.predicate;
        for (Object obj36 : doWhileLoopCheckers) {
            if (((Boolean) function36.invoke(obj36)).booleanValue()) {
                collection36.add(obj36);
            }
        }
        Set<FirExpressionChecker<FirCollectionLiteral>> collectionLiteralCheckers = checkers.getCollectionLiteralCheckers();
        Collection collection37 = this._collectionLiteralCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function37 = this.predicate;
        for (Object obj37 : collectionLiteralCheckers) {
            if (((Boolean) function37.invoke(obj37)).booleanValue()) {
                collection37.add(obj37);
            }
        }
        Set<FirExpressionChecker<FirClassReferenceExpression>> classReferenceExpressionCheckers = checkers.getClassReferenceExpressionCheckers();
        Collection collection38 = this._classReferenceExpressionCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function38 = this.predicate;
        for (Object obj38 : classReferenceExpressionCheckers) {
            if (((Boolean) function38.invoke(obj38)).booleanValue()) {
                collection38.add(obj38);
            }
        }
        Set<FirExpressionChecker<FirInaccessibleReceiverExpression>> inaccessibleReceiverCheckers = checkers.getInaccessibleReceiverCheckers();
        Collection collection39 = this._inaccessibleReceiverCheckers;
        Function1<FirCheckerWithMppKind, Boolean> function39 = this.predicate;
        for (Object obj39 : inaccessibleReceiverCheckers) {
            if (((Boolean) function39.invoke(obj39)).booleanValue()) {
                collection39.add(obj39);
            }
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ComposedExpressionCheckers(final MppCheckerKind mppCheckerKind) {
        this((Function1<? super FirCheckerWithMppKind, Boolean>) new Function1() { // from class: qm2
            public final Object invoke(Object obj) {
                return Boolean.valueOf(ComposedExpressionCheckers.N(mppCheckerKind, (FirCheckerWithMppKind) obj));
            }
        });
        mppCheckerKind.getClass();
    }
}
