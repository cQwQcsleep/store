package org.jetbrains.kotlin.fir.analysis.checkers.expression;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.diagnostics.PendingDiagnosticReporter;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.analysis.CheckersComponentKt;
import org.jetbrains.kotlin.fir.analysis.checkers.MppCheckerKind;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContext;
import org.jetbrains.kotlin.fir.analysis.collectors.components.AbstractDiagnosticCollectorComponent;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirBooleanOperatorExpression;
import org.jetbrains.kotlin.fir.expressions.FirBreakExpression;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCheckNotNullCall;
import org.jetbrains.kotlin.fir.expressions.FirCheckedSafeCallSubject;
import org.jetbrains.kotlin.fir.expressions.FirClassReferenceExpression;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirComparisonExpression;
import org.jetbrains.kotlin.fir.expressions.FirComponentCall;
import org.jetbrains.kotlin.fir.expressions.FirContinueExpression;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirDesugaredAssignmentValueReferenceExpression;
import org.jetbrains.kotlin.fir.expressions.FirDoWhileLoop;
import org.jetbrains.kotlin.fir.expressions.FirElvisExpression;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirErrorAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirErrorExpression;
import org.jetbrains.kotlin.fir.expressions.FirErrorLoop;
import org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionTypeConversionExpression;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirImplicitInvokeCall;
import org.jetbrains.kotlin.fir.expressions.FirInaccessibleReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirIntegerLiteralOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirLoop;
import org.jetbrains.kotlin.fir.expressions.FirLoopJump;
import org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirNamedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedErrorAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirReplDeclarationReference;
import org.jetbrains.kotlin.fir.expressions.FirReplExpressionReference;
import org.jetbrains.kotlin.fir.expressions.FirReplPropertyDelegate;
import org.jetbrains.kotlin.fir.expressions.FirReplPropertyInitializer;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirResolvedReifiedParameterReference;
import org.jetbrains.kotlin.fir.expressions.FirReturnExpression;
import org.jetbrains.kotlin.fir.expressions.FirSafeCallExpression;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirSpreadArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirStringConcatenationCall;
import org.jetbrains.kotlin.fir.expressions.FirSuperReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirThrowExpression;
import org.jetbrains.kotlin.fir.expressions.FirTryExpression;
import org.jetbrains.kotlin.fir.expressions.FirTypeOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhileLoop;
import org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirWrappedExpression;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;
import org.jetbrains.kotlin.utils.exceptions.PlatformExceptionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009a\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tB!\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\n\u001a\u00020\u000b¢\u0006\u0004\b\b\u0010\fJ\u0018\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0013\u001a\u00020\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0016\u001a\u00020\u000e2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u0019\u001a\u00020\u000e2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u001c\u001a\u00020\u000e2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\u001f\u001a\u00020\u000e2\u0006\u0010 \u001a\u00020!2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010\"\u001a\u00020\u000e2\u0006\u0010#\u001a\u00020$2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010%\u001a\u00020\u000e2\u0006\u0010&\u001a\u00020'2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010(\u001a\u00020\u000e2\u0006\u0010)\u001a\u00020*2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010+\u001a\u00020\u000e2\u0006\u0010,\u001a\u00020-2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010.\u001a\u00020\u000e2\u0006\u0010/\u001a\u0002002\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u00101\u001a\u00020\u000e2\u0006\u00102\u001a\u0002032\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u00104\u001a\u00020\u000e2\u0006\u00105\u001a\u0002062\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u00107\u001a\u00020\u000e2\u0006\u00108\u001a\u0002092\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010:\u001a\u00020\u000e2\u0006\u0010;\u001a\u00020<2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010=\u001a\u00020\u000e2\u0006\u0010>\u001a\u00020?2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010@\u001a\u00020\u000e2\u0006\u0010A\u001a\u00020B2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010C\u001a\u00020\u000e2\u0006\u0010D\u001a\u00020E2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010F\u001a\u00020\u000e2\u0006\u0010G\u001a\u00020H2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010I\u001a\u00020\u000e2\u0006\u0010J\u001a\u00020K2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010L\u001a\u00020\u000e2\u0006\u0010M\u001a\u00020N2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010O\u001a\u00020\u000e2\u0006\u0010P\u001a\u00020Q2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010R\u001a\u00020\u000e2\u0006\u0010S\u001a\u00020T2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010U\u001a\u00020\u000e2\u0006\u0010V\u001a\u00020W2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010X\u001a\u00020\u000e2\u0006\u0010Y\u001a\u00020Z2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010[\u001a\u00020\u000e2\u0006\u0010\\\u001a\u00020]2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010^\u001a\u00020\u000e2\u0006\u0010_\u001a\u00020`2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010a\u001a\u00020\u000e2\u0006\u0010b\u001a\u00020c2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010d\u001a\u00020\u000e2\u0006\u0010e\u001a\u00020f2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010g\u001a\u00020\u000e2\u0006\u0010h\u001a\u00020i2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010j\u001a\u00020\u000e2\u0006\u0010k\u001a\u00020l2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010m\u001a\u00020\u000e2\u0006\u0010n\u001a\u00020o2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010p\u001a\u00020\u000e2\u0006\u0010q\u001a\u00020r2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010s\u001a\u00020\u000e2\u0006\u0010t\u001a\u00020u2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010v\u001a\u00020\u000e2\u0006\u0010w\u001a\u00020x2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010y\u001a\u00020\u000e2\u0006\u0010z\u001a\u00020{2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u0018\u0010|\u001a\u00020\u000e2\u0006\u0010}\u001a\u00020~2\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001a\u0010\u007f\u001a\u00020\u000e2\b\u0010\u0080\u0001\u001a\u00030\u0081\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001b\u0010\u0082\u0001\u001a\u00020\u000e2\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001b\u0010\u0085\u0001\u001a\u00020\u000e2\b\u0010\u0086\u0001\u001a\u00030\u0087\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001b\u0010\u0088\u0001\u001a\u00020\u000e2\b\u0010\u0089\u0001\u001a\u00030\u008a\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001b\u0010\u008b\u0001\u001a\u00020\u000e2\b\u0010\u008c\u0001\u001a\u00030\u008d\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001b\u0010\u008e\u0001\u001a\u00020\u000e2\b\u0010\u008f\u0001\u001a\u00030\u0090\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001b\u0010\u0091\u0001\u001a\u00020\u000e2\b\u0010\u0092\u0001\u001a\u00030\u0093\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001b\u0010\u0094\u0001\u001a\u00020\u000e2\b\u0010\u0095\u0001\u001a\u00030\u0096\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001b\u0010\u0097\u0001\u001a\u00020\u000e2\b\u0010\u0098\u0001\u001a\u00030\u0099\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001b\u0010\u009a\u0001\u001a\u00020\u000e2\b\u0010\u009b\u0001\u001a\u00030\u009c\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001b\u0010\u009d\u0001\u001a\u00020\u000e2\b\u0010\u009e\u0001\u001a\u00030\u009f\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001b\u0010 \u0001\u001a\u00020\u000e2\b\u0010¡\u0001\u001a\u00030¢\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001b\u0010£\u0001\u001a\u00020\u000e2\b\u0010¤\u0001\u001a\u00030¥\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001b\u0010¦\u0001\u001a\u00020\u000e2\b\u0010§\u0001\u001a\u00030¨\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001b\u0010©\u0001\u001a\u00020\u000e2\b\u0010ª\u0001\u001a\u00030«\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001b\u0010¬\u0001\u001a\u00020\u000e2\b\u0010\u00ad\u0001\u001a\u00030®\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001b\u0010¯\u0001\u001a\u00020\u000e2\b\u0010°\u0001\u001a\u00030±\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001b\u0010²\u0001\u001a\u00020\u000e2\b\u0010³\u0001\u001a\u00030´\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001b\u0010µ\u0001\u001a\u00020\u000e2\b\u0010¶\u0001\u001a\u00030·\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001b\u0010¸\u0001\u001a\u00020\u000e2\b\u0010¹\u0001\u001a\u00030º\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001b\u0010»\u0001\u001a\u00020\u000e2\b\u0010¼\u0001\u001a\u00030½\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016J\u001b\u0010¾\u0001\u001a\u00020\u000e2\b\u0010¿\u0001\u001a\u00030À\u00012\u0006\u0010\u0011\u001a\u00020\u0012H\u0016JC\u0010Á\u0001\u001a\u00020\u000e\"\f\b\u0000\u0010Â\u0001\u0018\u0001*\u00030Ã\u0001*\u0011\u0012\f\u0012\n\u0012\u0005\u0012\u0003HÂ\u00010Å\u00010Ä\u00012\u0007\u0010\u000f\u001a\u0003HÂ\u00012\u0007\u0010Æ\u0001\u001a\u00020\u0012H\u0082\b¢\u0006\u0003\u0010Ç\u0001R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006È\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ExpressionCheckersDiagnosticComponent;", "Lorg/jetbrains/kotlin/fir/analysis/collectors/components/AbstractDiagnosticCollectorComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "reporter", "Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;", "checkers", "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ExpressionCheckers;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/ExpressionCheckers;)V", "mppKind", "Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/diagnostics/PendingDiagnosticReporter;Lorg/jetbrains/kotlin/fir/analysis/checkers/MppCheckerKind;)V", "visitElement", Argument.Delimiters.none, "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "data", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;", "visitQualifiedAccessExpression", "qualifiedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "visitFunctionCall", "functionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "visitPropertyAccessExpression", "propertyAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "visitSuperReceiverExpression", "superReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSuperReceiverExpression;", "visitIntegerLiteralOperatorCall", "integerLiteralOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirIntegerLiteralOperatorCall;", "visitVariableAssignment", "variableAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "visitTryExpression", "tryExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;", "visitWhenExpression", "whenExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "visitBooleanOperatorExpression", "booleanOperatorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "visitReturnExpression", "returnExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "visitBlock", "block", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "visitReplDeclarationReference", "replDeclarationReference", "Lorg/jetbrains/kotlin/fir/expressions/FirReplDeclarationReference;", "visitReplPropertyInitializer", "replPropertyInitializer", "Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyInitializer;", "visitReplPropertyDelegate", "replPropertyDelegate", "Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyDelegate;", "visitReplExpressionReference", "replExpressionReference", "Lorg/jetbrains/kotlin/fir/expressions/FirReplExpressionReference;", "visitAnnotation", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "visitAnnotationCall", "annotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "visitCheckNotNullCall", "checkNotNullCall", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckNotNullCall;", "visitElvisExpression", "elvisExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirElvisExpression;", "visitGetClassCall", "getClassCall", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "visitSafeCallExpression", "safeCallExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "visitSmartCastExpression", "smartCastExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "visitEqualityOperatorCall", "equalityOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "visitStringConcatenationCall", "stringConcatenationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirStringConcatenationCall;", "visitTypeOperatorCall", "typeOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "visitResolvedQualifier", "resolvedQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "visitLiteralExpression", "literalExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "visitCallableReferenceAccess", "callableReferenceAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "visitThisReceiverExpression", "thisReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "visitWhileLoop", "whileLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirWhileLoop;", "visitThrowExpression", "throwExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThrowExpression;", "visitDoWhileLoop", "doWhileLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirDoWhileLoop;", "visitCollectionLiteral", "collectionLiteral", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "visitClassReferenceExpression", "classReferenceExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirClassReferenceExpression;", "visitInaccessibleReceiverExpression", "inaccessibleReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirInaccessibleReceiverExpression;", "visitExpression", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "visitVarargArgumentsExpression", "varargArgumentsExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirVarargArgumentsExpression;", "visitFunctionTypeConversionExpression", "functionTypeConversionExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionTypeConversionExpression;", "visitWrappedExpression", "wrappedExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWrappedExpression;", "visitWrappedArgumentExpression", "wrappedArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWrappedArgumentExpression;", "visitSpreadArgumentExpression", "spreadArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSpreadArgumentExpression;", "visitNamedArgumentExpression", "namedArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirNamedArgumentExpression;", "visitWhenSubjectExpression", "whenSubjectExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenSubjectExpression;", "visitResolvedReifiedParameterReference", "resolvedReifiedParameterReference", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedReifiedParameterReference;", "visitComparisonExpression", "comparisonExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirComparisonExpression;", "visitDesugaredAssignmentValueReferenceExpression", "desugaredAssignmentValueReferenceExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirDesugaredAssignmentValueReferenceExpression;", "visitCheckedSafeCallSubject", "checkedSafeCallSubject", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckedSafeCallSubject;", "visitErrorExpression", "errorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorExpression;", "visitQualifiedErrorAccessExpression", "qualifiedErrorAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedErrorAccessExpression;", "visitDelegatedConstructorCall", "delegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "visitMultiDelegatedConstructorCall", "multiDelegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirMultiDelegatedConstructorCall;", "visitComponentCall", "componentCall", "Lorg/jetbrains/kotlin/fir/expressions/FirComponentCall;", "visitImplicitInvokeCall", "implicitInvokeCall", "Lorg/jetbrains/kotlin/fir/expressions/FirImplicitInvokeCall;", "visitErrorLoop", "errorLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorLoop;", "visitBreakExpression", "breakExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBreakExpression;", "visitContinueExpression", "continueExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirContinueExpression;", "visitErrorAnnotationCall", "errorAnnotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorAnnotationCall;", "visitErrorResolvedQualifier", "errorResolvedQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorResolvedQualifier;", "check", "E", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;", "context", "([Lorg/jetbrains/kotlin/fir/analysis/checkers/expression/FirExpressionChecker;Lorg/jetbrains/kotlin/fir/expressions/FirStatement;Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContext;)V", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ExpressionCheckersDiagnosticComponent extends AbstractDiagnosticCollectorComponent {
    private final ExpressionCheckers checkers;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[MppCheckerKind.values().length];
            try {
                iArr[MppCheckerKind.Common.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[MppCheckerKind.Platform.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ExpressionCheckersDiagnosticComponent(FirSession firSession, PendingDiagnosticReporter pendingDiagnosticReporter, MppCheckerKind mppCheckerKind) {
        ExpressionCheckers commonExpressionCheckers;
        firSession.getClass();
        pendingDiagnosticReporter.getClass();
        mppCheckerKind.getClass();
        int i = WhenMappings.$EnumSwitchMapping$0[mppCheckerKind.ordinal()];
        if (i == 1) {
            commonExpressionCheckers = CheckersComponentKt.getCheckersComponent(firSession).getCommonExpressionCheckers();
        } else {
            if (i != 2) {
                bu8.a();
                throw null;
            }
            commonExpressionCheckers = CheckersComponentKt.getCheckersComponent(firSession).getPlatformExpressionCheckers();
        }
        this(firSession, pendingDiagnosticReporter, commonExpressionCheckers);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitAnnotation, reason: avoid collision after fix types in other method */
    public void visitAnnotation2(FirAnnotation annotation, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        annotation.getClass();
        data.getClass();
        for (FirExpressionChecker<FirAnnotation> firExpressionChecker : this.checkers.getAllAnnotationCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), annotation);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", annotation);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitAnnotationCall, reason: avoid collision after fix types in other method */
    public void visitAnnotationCall2(FirAnnotationCall annotationCall, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        annotationCall.getClass();
        data.getClass();
        for (FirExpressionChecker<FirAnnotationCall> firExpressionChecker : this.checkers.getAllAnnotationCallCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), annotationCall);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", annotationCall);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitBlock, reason: avoid collision after fix types in other method */
    public void visitBlock2(FirBlock block, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        block.getClass();
        data.getClass();
        for (FirExpressionChecker<FirBlock> firExpressionChecker : this.checkers.getAllBlockCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), block);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", block);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitBooleanOperatorExpression, reason: avoid collision after fix types in other method */
    public void visitBooleanOperatorExpression2(FirBooleanOperatorExpression booleanOperatorExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        booleanOperatorExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirBooleanOperatorExpression> firExpressionChecker : this.checkers.getAllBooleanOperatorExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), booleanOperatorExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", booleanOperatorExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitBreakExpression, reason: avoid collision after fix types in other method */
    public void visitBreakExpression2(FirBreakExpression breakExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        breakExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirLoopJump> firExpressionChecker : this.checkers.getAllLoopJumpCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), breakExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", breakExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitCallableReferenceAccess, reason: avoid collision after fix types in other method */
    public void visitCallableReferenceAccess2(FirCallableReferenceAccess callableReferenceAccess, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        callableReferenceAccess.getClass();
        data.getClass();
        for (FirExpressionChecker<FirCallableReferenceAccess> firExpressionChecker : this.checkers.getAllCallableReferenceAccessCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), callableReferenceAccess);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", callableReferenceAccess);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitCheckNotNullCall, reason: avoid collision after fix types in other method */
    public void visitCheckNotNullCall2(FirCheckNotNullCall checkNotNullCall, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        checkNotNullCall.getClass();
        data.getClass();
        for (FirExpressionChecker<FirCheckNotNullCall> firExpressionChecker : this.checkers.getAllCheckNotNullCallCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), checkNotNullCall);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", checkNotNullCall);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitCheckedSafeCallSubject, reason: avoid collision after fix types in other method */
    public void visitCheckedSafeCallSubject2(FirCheckedSafeCallSubject checkedSafeCallSubject, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        checkedSafeCallSubject.getClass();
        data.getClass();
        for (FirExpressionChecker<FirStatement> firExpressionChecker : this.checkers.getAllBasicExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), checkedSafeCallSubject);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", checkedSafeCallSubject);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitClassReferenceExpression, reason: avoid collision after fix types in other method */
    public void visitClassReferenceExpression2(FirClassReferenceExpression classReferenceExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        classReferenceExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirClassReferenceExpression> firExpressionChecker : this.checkers.getAllClassReferenceExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), classReferenceExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", classReferenceExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitCollectionLiteral, reason: avoid collision after fix types in other method */
    public void visitCollectionLiteral2(FirCollectionLiteral collectionLiteral, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        collectionLiteral.getClass();
        data.getClass();
        for (FirExpressionChecker<FirCollectionLiteral> firExpressionChecker : this.checkers.getAllCollectionLiteralCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), collectionLiteral);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", collectionLiteral);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitComparisonExpression, reason: avoid collision after fix types in other method */
    public void visitComparisonExpression2(FirComparisonExpression comparisonExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        comparisonExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirStatement> firExpressionChecker : this.checkers.getAllBasicExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), comparisonExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", comparisonExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitComponentCall, reason: avoid collision after fix types in other method */
    public void visitComponentCall2(FirComponentCall componentCall, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        componentCall.getClass();
        data.getClass();
        for (FirExpressionChecker<FirFunctionCall> firExpressionChecker : this.checkers.getAllFunctionCallCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), componentCall);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", componentCall);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitContinueExpression, reason: avoid collision after fix types in other method */
    public void visitContinueExpression2(FirContinueExpression continueExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        continueExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirLoopJump> firExpressionChecker : this.checkers.getAllLoopJumpCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), continueExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", continueExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitDelegatedConstructorCall, reason: avoid collision after fix types in other method */
    public void visitDelegatedConstructorCall2(FirDelegatedConstructorCall delegatedConstructorCall, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        delegatedConstructorCall.getClass();
        data.getClass();
        for (FirExpressionChecker<FirCall> firExpressionChecker : this.checkers.getAllCallCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), delegatedConstructorCall);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", delegatedConstructorCall);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitDesugaredAssignmentValueReferenceExpression, reason: avoid collision after fix types in other method */
    public void visitDesugaredAssignmentValueReferenceExpression2(FirDesugaredAssignmentValueReferenceExpression desugaredAssignmentValueReferenceExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        desugaredAssignmentValueReferenceExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirStatement> firExpressionChecker : this.checkers.getAllBasicExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), desugaredAssignmentValueReferenceExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", desugaredAssignmentValueReferenceExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitDoWhileLoop, reason: avoid collision after fix types in other method */
    public void visitDoWhileLoop2(FirDoWhileLoop doWhileLoop, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        doWhileLoop.getClass();
        data.getClass();
        for (FirExpressionChecker<FirDoWhileLoop> firExpressionChecker : this.checkers.getAllDoWhileLoopCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), doWhileLoop);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", doWhileLoop);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.collectors.components.AbstractDiagnosticCollectorComponent
    /* JADX INFO: renamed from: visitElement, reason: avoid collision after fix types in other method */
    public void visitElement2(FirElement element, CheckerContext data) {
        element.getClass();
        data.getClass();
        if (element instanceof FirExpression) {
            i37.a(Reflection.getOrCreateKotlinClass(element.getClass()).getSimpleName(), " should call parent checkers inside ", Reflection.getOrCreateKotlinClass(ExpressionCheckersDiagnosticComponent.class).getSimpleName());
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitElvisExpression, reason: avoid collision after fix types in other method */
    public void visitElvisExpression2(FirElvisExpression elvisExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        elvisExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirElvisExpression> firExpressionChecker : this.checkers.getAllElvisExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), elvisExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", elvisExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitEqualityOperatorCall, reason: avoid collision after fix types in other method */
    public void visitEqualityOperatorCall2(FirEqualityOperatorCall equalityOperatorCall, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        equalityOperatorCall.getClass();
        data.getClass();
        for (FirExpressionChecker<FirEqualityOperatorCall> firExpressionChecker : this.checkers.getAllEqualityOperatorCallCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), equalityOperatorCall);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", equalityOperatorCall);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitErrorAnnotationCall, reason: avoid collision after fix types in other method */
    public void visitErrorAnnotationCall2(FirErrorAnnotationCall errorAnnotationCall, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        errorAnnotationCall.getClass();
        data.getClass();
        for (FirExpressionChecker<FirAnnotationCall> firExpressionChecker : this.checkers.getAllAnnotationCallCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), errorAnnotationCall);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", errorAnnotationCall);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitErrorExpression, reason: avoid collision after fix types in other method */
    public void visitErrorExpression2(FirErrorExpression errorExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        errorExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirStatement> firExpressionChecker : this.checkers.getAllBasicExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), errorExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", errorExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitErrorLoop, reason: avoid collision after fix types in other method */
    public void visitErrorLoop2(FirErrorLoop errorLoop, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        errorLoop.getClass();
        data.getClass();
        for (FirExpressionChecker<FirLoop> firExpressionChecker : this.checkers.getAllLoopExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), errorLoop);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", errorLoop);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitErrorResolvedQualifier, reason: avoid collision after fix types in other method */
    public void visitErrorResolvedQualifier2(FirErrorResolvedQualifier errorResolvedQualifier, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        errorResolvedQualifier.getClass();
        data.getClass();
        for (FirExpressionChecker<FirResolvedQualifier> firExpressionChecker : this.checkers.getAllResolvedQualifierCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), errorResolvedQualifier);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", errorResolvedQualifier);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitExpression, reason: avoid collision after fix types in other method */
    public void visitExpression2(FirExpression expression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        expression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirStatement> firExpressionChecker : this.checkers.getAllBasicExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), expression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", expression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitFunctionCall, reason: avoid collision after fix types in other method */
    public void visitFunctionCall2(FirFunctionCall functionCall, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        functionCall.getClass();
        data.getClass();
        for (FirExpressionChecker<FirFunctionCall> firExpressionChecker : this.checkers.getAllFunctionCallCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), functionCall);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", functionCall);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitFunctionTypeConversionExpression, reason: avoid collision after fix types in other method */
    public void visitFunctionTypeConversionExpression2(FirFunctionTypeConversionExpression functionTypeConversionExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        functionTypeConversionExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirStatement> firExpressionChecker : this.checkers.getAllBasicExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), functionTypeConversionExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", functionTypeConversionExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitGetClassCall, reason: avoid collision after fix types in other method */
    public void visitGetClassCall2(FirGetClassCall getClassCall, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        getClassCall.getClass();
        data.getClass();
        for (FirExpressionChecker<FirGetClassCall> firExpressionChecker : this.checkers.getAllGetClassCallCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), getClassCall);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", getClassCall);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitImplicitInvokeCall, reason: avoid collision after fix types in other method */
    public void visitImplicitInvokeCall2(FirImplicitInvokeCall implicitInvokeCall, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        implicitInvokeCall.getClass();
        data.getClass();
        for (FirExpressionChecker<FirFunctionCall> firExpressionChecker : this.checkers.getAllFunctionCallCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), implicitInvokeCall);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", implicitInvokeCall);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitInaccessibleReceiverExpression, reason: avoid collision after fix types in other method */
    public void visitInaccessibleReceiverExpression2(FirInaccessibleReceiverExpression inaccessibleReceiverExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        inaccessibleReceiverExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirInaccessibleReceiverExpression> firExpressionChecker : this.checkers.getAllInaccessibleReceiverCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), inaccessibleReceiverExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", inaccessibleReceiverExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitIntegerLiteralOperatorCall, reason: avoid collision after fix types in other method */
    public void visitIntegerLiteralOperatorCall2(FirIntegerLiteralOperatorCall integerLiteralOperatorCall, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        integerLiteralOperatorCall.getClass();
        data.getClass();
        for (FirExpressionChecker<FirIntegerLiteralOperatorCall> firExpressionChecker : this.checkers.getAllIntegerLiteralOperatorCallCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), integerLiteralOperatorCall);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", integerLiteralOperatorCall);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitLiteralExpression, reason: avoid collision after fix types in other method */
    public void visitLiteralExpression2(FirLiteralExpression literalExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        literalExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirLiteralExpression> firExpressionChecker : this.checkers.getAllLiteralExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), literalExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", literalExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitMultiDelegatedConstructorCall, reason: avoid collision after fix types in other method */
    public void visitMultiDelegatedConstructorCall2(FirMultiDelegatedConstructorCall multiDelegatedConstructorCall, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        multiDelegatedConstructorCall.getClass();
        data.getClass();
        for (FirExpressionChecker<FirCall> firExpressionChecker : this.checkers.getAllCallCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), multiDelegatedConstructorCall);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", multiDelegatedConstructorCall);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitNamedArgumentExpression, reason: avoid collision after fix types in other method */
    public void visitNamedArgumentExpression2(FirNamedArgumentExpression namedArgumentExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        namedArgumentExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirStatement> firExpressionChecker : this.checkers.getAllBasicExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), namedArgumentExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", namedArgumentExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitPropertyAccessExpression, reason: avoid collision after fix types in other method */
    public void visitPropertyAccessExpression2(FirPropertyAccessExpression propertyAccessExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        propertyAccessExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirPropertyAccessExpression> firExpressionChecker : this.checkers.getAllPropertyAccessExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), propertyAccessExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", propertyAccessExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitQualifiedAccessExpression, reason: avoid collision after fix types in other method */
    public void visitQualifiedAccessExpression2(FirQualifiedAccessExpression qualifiedAccessExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        qualifiedAccessExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirQualifiedAccessExpression> firExpressionChecker : this.checkers.getAllQualifiedAccessExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), qualifiedAccessExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", qualifiedAccessExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitQualifiedErrorAccessExpression, reason: avoid collision after fix types in other method */
    public void visitQualifiedErrorAccessExpression2(FirQualifiedErrorAccessExpression qualifiedErrorAccessExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        qualifiedErrorAccessExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirStatement> firExpressionChecker : this.checkers.getAllBasicExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), qualifiedErrorAccessExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", qualifiedErrorAccessExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitReplDeclarationReference, reason: avoid collision after fix types in other method */
    public void visitReplDeclarationReference2(FirReplDeclarationReference replDeclarationReference, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        replDeclarationReference.getClass();
        data.getClass();
        for (FirExpressionChecker<FirReplDeclarationReference> firExpressionChecker : this.checkers.getAllReplDeclarationReferenceCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), replDeclarationReference);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", replDeclarationReference);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitReplExpressionReference, reason: avoid collision after fix types in other method */
    public void visitReplExpressionReference2(FirReplExpressionReference replExpressionReference, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        replExpressionReference.getClass();
        data.getClass();
        for (FirExpressionChecker<FirReplExpressionReference> firExpressionChecker : this.checkers.getAllReplExpressionReferenceCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), replExpressionReference);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", replExpressionReference);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitReplPropertyDelegate, reason: avoid collision after fix types in other method */
    public void visitReplPropertyDelegate2(FirReplPropertyDelegate replPropertyDelegate, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        replPropertyDelegate.getClass();
        data.getClass();
        for (FirExpressionChecker<FirReplPropertyDelegate> firExpressionChecker : this.checkers.getAllReplPropertyDelegateCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), replPropertyDelegate);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", replPropertyDelegate);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitReplPropertyInitializer, reason: avoid collision after fix types in other method */
    public void visitReplPropertyInitializer2(FirReplPropertyInitializer replPropertyInitializer, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        replPropertyInitializer.getClass();
        data.getClass();
        for (FirExpressionChecker<FirReplPropertyInitializer> firExpressionChecker : this.checkers.getAllReplPropertyInitializerCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), replPropertyInitializer);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", replPropertyInitializer);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitResolvedQualifier, reason: avoid collision after fix types in other method */
    public void visitResolvedQualifier2(FirResolvedQualifier resolvedQualifier, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        resolvedQualifier.getClass();
        data.getClass();
        for (FirExpressionChecker<FirResolvedQualifier> firExpressionChecker : this.checkers.getAllResolvedQualifierCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), resolvedQualifier);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", resolvedQualifier);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitResolvedReifiedParameterReference, reason: avoid collision after fix types in other method */
    public void visitResolvedReifiedParameterReference2(FirResolvedReifiedParameterReference resolvedReifiedParameterReference, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        resolvedReifiedParameterReference.getClass();
        data.getClass();
        for (FirExpressionChecker<FirStatement> firExpressionChecker : this.checkers.getAllBasicExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), resolvedReifiedParameterReference);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", resolvedReifiedParameterReference);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitReturnExpression, reason: avoid collision after fix types in other method */
    public void visitReturnExpression2(FirReturnExpression returnExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        returnExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirReturnExpression> firExpressionChecker : this.checkers.getAllReturnExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), returnExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", returnExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitSafeCallExpression, reason: avoid collision after fix types in other method */
    public void visitSafeCallExpression2(FirSafeCallExpression safeCallExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        safeCallExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirSafeCallExpression> firExpressionChecker : this.checkers.getAllSafeCallExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), safeCallExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", safeCallExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitSmartCastExpression, reason: avoid collision after fix types in other method */
    public void visitSmartCastExpression2(FirSmartCastExpression smartCastExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        smartCastExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirSmartCastExpression> firExpressionChecker : this.checkers.getAllSmartCastExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), smartCastExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", smartCastExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitSpreadArgumentExpression, reason: avoid collision after fix types in other method */
    public void visitSpreadArgumentExpression2(FirSpreadArgumentExpression spreadArgumentExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        spreadArgumentExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirStatement> firExpressionChecker : this.checkers.getAllBasicExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), spreadArgumentExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", spreadArgumentExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitStringConcatenationCall, reason: avoid collision after fix types in other method */
    public void visitStringConcatenationCall2(FirStringConcatenationCall stringConcatenationCall, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        stringConcatenationCall.getClass();
        data.getClass();
        for (FirExpressionChecker<FirStringConcatenationCall> firExpressionChecker : this.checkers.getAllStringConcatenationCallCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), stringConcatenationCall);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", stringConcatenationCall);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitSuperReceiverExpression, reason: avoid collision after fix types in other method */
    public void visitSuperReceiverExpression2(FirSuperReceiverExpression superReceiverExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        superReceiverExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirSuperReceiverExpression> firExpressionChecker : this.checkers.getAllSuperReceiverExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), superReceiverExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", superReceiverExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitThisReceiverExpression, reason: avoid collision after fix types in other method */
    public void visitThisReceiverExpression2(FirThisReceiverExpression thisReceiverExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        thisReceiverExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirThisReceiverExpression> firExpressionChecker : this.checkers.getAllThisReceiverExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), thisReceiverExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", thisReceiverExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitThrowExpression, reason: avoid collision after fix types in other method */
    public void visitThrowExpression2(FirThrowExpression throwExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        throwExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirThrowExpression> firExpressionChecker : this.checkers.getAllThrowExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), throwExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", throwExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitTryExpression, reason: avoid collision after fix types in other method */
    public void visitTryExpression2(FirTryExpression tryExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        tryExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirTryExpression> firExpressionChecker : this.checkers.getAllTryExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), tryExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", tryExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitTypeOperatorCall, reason: avoid collision after fix types in other method */
    public void visitTypeOperatorCall2(FirTypeOperatorCall typeOperatorCall, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        typeOperatorCall.getClass();
        data.getClass();
        for (FirExpressionChecker<FirTypeOperatorCall> firExpressionChecker : this.checkers.getAllTypeOperatorCallCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), typeOperatorCall);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", typeOperatorCall);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitVarargArgumentsExpression, reason: avoid collision after fix types in other method */
    public void visitVarargArgumentsExpression2(FirVarargArgumentsExpression varargArgumentsExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        varargArgumentsExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirStatement> firExpressionChecker : this.checkers.getAllBasicExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), varargArgumentsExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", varargArgumentsExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitVariableAssignment, reason: avoid collision after fix types in other method */
    public void visitVariableAssignment2(FirVariableAssignment variableAssignment, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        variableAssignment.getClass();
        data.getClass();
        for (FirExpressionChecker<FirVariableAssignment> firExpressionChecker : this.checkers.getAllVariableAssignmentCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), variableAssignment);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", variableAssignment);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitWhenExpression, reason: avoid collision after fix types in other method */
    public void visitWhenExpression2(FirWhenExpression whenExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        whenExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirWhenExpression> firExpressionChecker : this.checkers.getAllWhenExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), whenExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", whenExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitWhenSubjectExpression, reason: avoid collision after fix types in other method */
    public void visitWhenSubjectExpression2(FirWhenSubjectExpression whenSubjectExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        whenSubjectExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirStatement> firExpressionChecker : this.checkers.getAllBasicExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), whenSubjectExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", whenSubjectExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitWhileLoop, reason: avoid collision after fix types in other method */
    public void visitWhileLoop2(FirWhileLoop whileLoop, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        whileLoop.getClass();
        data.getClass();
        for (FirExpressionChecker<FirWhileLoop> firExpressionChecker : this.checkers.getAllWhileLoopCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), whileLoop);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", whileLoop);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitWrappedArgumentExpression, reason: avoid collision after fix types in other method */
    public void visitWrappedArgumentExpression2(FirWrappedArgumentExpression wrappedArgumentExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        wrappedArgumentExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirStatement> firExpressionChecker : this.checkers.getAllBasicExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), wrappedArgumentExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", wrappedArgumentExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX INFO: renamed from: visitWrappedExpression, reason: avoid collision after fix types in other method */
    public void visitWrappedExpression2(FirWrappedExpression wrappedExpression, CheckerContext data) throws KotlinIllegalArgumentExceptionWithAttachments {
        wrappedExpression.getClass();
        data.getClass();
        for (FirExpressionChecker<FirStatement> firExpressionChecker : this.checkers.getAllBasicExpressionCheckers$org_jetbrains_kotlin_checkers()) {
            try {
                firExpressionChecker.check(data, getReporter(), wrappedExpression);
            } catch (Exception e) {
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(e);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Exception in expression checkers", e);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", wrappedExpression);
                String containingFilePath = data.getContainingFilePath();
                if (containingFilePath != null) {
                    exceptionAttachmentBuilder.withEntry("file", containingFilePath);
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
    }

    @Override // org.jetbrains.kotlin.fir.analysis.collectors.components.AbstractDiagnosticCollectorComponent, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitElement(FirElement firElement, CheckerContext checkerContext) {
        visitElement2(firElement, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ExpressionCheckersDiagnosticComponent(FirSession firSession, PendingDiagnosticReporter pendingDiagnosticReporter, ExpressionCheckers expressionCheckers) {
        super(firSession, pendingDiagnosticReporter);
        firSession.getClass();
        pendingDiagnosticReporter.getClass();
        expressionCheckers.getClass();
        this.checkers = expressionCheckers;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitAnnotation(FirAnnotation firAnnotation, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitAnnotation2(firAnnotation, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitAnnotationCall(FirAnnotationCall firAnnotationCall, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitAnnotationCall2(firAnnotationCall, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitBlock(FirBlock firBlock, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitBlock2(firBlock, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitBooleanOperatorExpression(FirBooleanOperatorExpression firBooleanOperatorExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitBooleanOperatorExpression2(firBooleanOperatorExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitBreakExpression(FirBreakExpression firBreakExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitBreakExpression2(firBreakExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitCallableReferenceAccess(FirCallableReferenceAccess firCallableReferenceAccess, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitCallableReferenceAccess2(firCallableReferenceAccess, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitCheckNotNullCall(FirCheckNotNullCall firCheckNotNullCall, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitCheckNotNullCall2(firCheckNotNullCall, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitCheckedSafeCallSubject(FirCheckedSafeCallSubject firCheckedSafeCallSubject, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitCheckedSafeCallSubject2(firCheckedSafeCallSubject, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitClassReferenceExpression(FirClassReferenceExpression firClassReferenceExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitClassReferenceExpression2(firClassReferenceExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitCollectionLiteral(FirCollectionLiteral firCollectionLiteral, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitCollectionLiteral2(firCollectionLiteral, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitComparisonExpression(FirComparisonExpression firComparisonExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitComparisonExpression2(firComparisonExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitComponentCall(FirComponentCall firComponentCall, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitComponentCall2(firComponentCall, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitContinueExpression(FirContinueExpression firContinueExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitContinueExpression2(firContinueExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitDelegatedConstructorCall(FirDelegatedConstructorCall firDelegatedConstructorCall, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitDelegatedConstructorCall2(firDelegatedConstructorCall, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitDesugaredAssignmentValueReferenceExpression(FirDesugaredAssignmentValueReferenceExpression firDesugaredAssignmentValueReferenceExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitDesugaredAssignmentValueReferenceExpression2(firDesugaredAssignmentValueReferenceExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitDoWhileLoop(FirDoWhileLoop firDoWhileLoop, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitDoWhileLoop2(firDoWhileLoop, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitElvisExpression(FirElvisExpression firElvisExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitElvisExpression2(firElvisExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitEqualityOperatorCall(FirEqualityOperatorCall firEqualityOperatorCall, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitEqualityOperatorCall2(firEqualityOperatorCall, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitErrorAnnotationCall(FirErrorAnnotationCall firErrorAnnotationCall, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitErrorAnnotationCall2(firErrorAnnotationCall, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitErrorExpression(FirErrorExpression firErrorExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitErrorExpression2(firErrorExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitErrorLoop(FirErrorLoop firErrorLoop, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitErrorLoop2(firErrorLoop, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitErrorResolvedQualifier(FirErrorResolvedQualifier firErrorResolvedQualifier, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitErrorResolvedQualifier2(firErrorResolvedQualifier, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitExpression(FirExpression firExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitExpression2(firExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitFunctionCall(FirFunctionCall firFunctionCall, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitFunctionCall2(firFunctionCall, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitFunctionTypeConversionExpression(FirFunctionTypeConversionExpression firFunctionTypeConversionExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitFunctionTypeConversionExpression2(firFunctionTypeConversionExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitGetClassCall(FirGetClassCall firGetClassCall, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitGetClassCall2(firGetClassCall, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitImplicitInvokeCall(FirImplicitInvokeCall firImplicitInvokeCall, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitImplicitInvokeCall2(firImplicitInvokeCall, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitInaccessibleReceiverExpression(FirInaccessibleReceiverExpression firInaccessibleReceiverExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitInaccessibleReceiverExpression2(firInaccessibleReceiverExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitIntegerLiteralOperatorCall(FirIntegerLiteralOperatorCall firIntegerLiteralOperatorCall, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitIntegerLiteralOperatorCall2(firIntegerLiteralOperatorCall, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitLiteralExpression(FirLiteralExpression firLiteralExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitLiteralExpression2(firLiteralExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitMultiDelegatedConstructorCall(FirMultiDelegatedConstructorCall firMultiDelegatedConstructorCall, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitMultiDelegatedConstructorCall2(firMultiDelegatedConstructorCall, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitNamedArgumentExpression(FirNamedArgumentExpression firNamedArgumentExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitNamedArgumentExpression2(firNamedArgumentExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitPropertyAccessExpression(FirPropertyAccessExpression firPropertyAccessExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitPropertyAccessExpression2(firPropertyAccessExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitQualifiedAccessExpression(FirQualifiedAccessExpression firQualifiedAccessExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitQualifiedAccessExpression2(firQualifiedAccessExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitQualifiedErrorAccessExpression(FirQualifiedErrorAccessExpression firQualifiedErrorAccessExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitQualifiedErrorAccessExpression2(firQualifiedErrorAccessExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitReplDeclarationReference(FirReplDeclarationReference firReplDeclarationReference, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitReplDeclarationReference2(firReplDeclarationReference, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitReplExpressionReference(FirReplExpressionReference firReplExpressionReference, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitReplExpressionReference2(firReplExpressionReference, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitReplPropertyDelegate(FirReplPropertyDelegate firReplPropertyDelegate, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitReplPropertyDelegate2(firReplPropertyDelegate, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitReplPropertyInitializer(FirReplPropertyInitializer firReplPropertyInitializer, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitReplPropertyInitializer2(firReplPropertyInitializer, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitResolvedQualifier(FirResolvedQualifier firResolvedQualifier, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitResolvedQualifier2(firResolvedQualifier, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitResolvedReifiedParameterReference(FirResolvedReifiedParameterReference firResolvedReifiedParameterReference, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitResolvedReifiedParameterReference2(firResolvedReifiedParameterReference, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitReturnExpression(FirReturnExpression firReturnExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitReturnExpression2(firReturnExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitSafeCallExpression(FirSafeCallExpression firSafeCallExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitSafeCallExpression2(firSafeCallExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitSmartCastExpression(FirSmartCastExpression firSmartCastExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitSmartCastExpression2(firSmartCastExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitSpreadArgumentExpression(FirSpreadArgumentExpression firSpreadArgumentExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitSpreadArgumentExpression2(firSpreadArgumentExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitStringConcatenationCall(FirStringConcatenationCall firStringConcatenationCall, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitStringConcatenationCall2(firStringConcatenationCall, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitSuperReceiverExpression(FirSuperReceiverExpression firSuperReceiverExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitSuperReceiverExpression2(firSuperReceiverExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitThisReceiverExpression(FirThisReceiverExpression firThisReceiverExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitThisReceiverExpression2(firThisReceiverExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitThrowExpression(FirThrowExpression firThrowExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitThrowExpression2(firThrowExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitTryExpression(FirTryExpression firTryExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitTryExpression2(firTryExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitTypeOperatorCall(FirTypeOperatorCall firTypeOperatorCall, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitTypeOperatorCall2(firTypeOperatorCall, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitVarargArgumentsExpression(FirVarargArgumentsExpression firVarargArgumentsExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitVarargArgumentsExpression2(firVarargArgumentsExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitVariableAssignment(FirVariableAssignment firVariableAssignment, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitVariableAssignment2(firVariableAssignment, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitWhenExpression(FirWhenExpression firWhenExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitWhenExpression2(firWhenExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitWhenSubjectExpression(FirWhenSubjectExpression firWhenSubjectExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitWhenSubjectExpression2(firWhenSubjectExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitWhileLoop(FirWhileLoop firWhileLoop, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitWhileLoop2(firWhileLoop, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitWrappedArgumentExpression(FirWrappedArgumentExpression firWrappedArgumentExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitWrappedArgumentExpression2(firWrappedArgumentExpression, checkerContext);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Unit visitWrappedExpression(FirWrappedExpression firWrappedExpression, CheckerContext checkerContext) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitWrappedExpression2(firWrappedExpression, checkerContext);
        return Unit.INSTANCE;
    }
}
