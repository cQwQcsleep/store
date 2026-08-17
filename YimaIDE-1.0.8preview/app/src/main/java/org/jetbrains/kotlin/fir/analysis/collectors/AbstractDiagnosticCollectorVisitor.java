package org.jetbrains.kotlin.fir.analysis.collectors;

import java.util.List;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirTargetElement;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.analysis.checkers.context.CheckerContextForProvider;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirDestructuringDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirInlineDeclarationChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.declaration.FirInlineDeclarationCheckerKt;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirInlineBodyResolvableExpressionChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.expression.FirInlineBodyResolvableExpressionCheckerKt;
import org.jetbrains.kotlin.fir.analysis.checkers.extra.FirAnonymousUnusedParamChecker;
import org.jetbrains.kotlin.fir.analysis.checkers.extra.FirAnonymousUnusedParamCheckerKt;
import org.jetbrains.kotlin.fir.contracts.FirContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirLazyContractDescription;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDanglingModifierList;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirErrorPrimaryConstructor;
import org.jetbrains.kotlin.fir.declarations.FirErrorProperty;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousObjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirBreakExpression;
import org.jetbrains.kotlin.fir.expressions.FirContinueExpression;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirErrorLoop;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirLazyBlock;
import org.jetbrains.kotlin.fir.expressions.FirLazyExpression;
import org.jetbrains.kotlin.fir.expressions.FirLoop;
import org.jetbrains.kotlin.fir.expressions.FirLoopJump;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.impl.FirContractCallBlock;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.impl.FirImplicitBuiltinTypeRef;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor;
import org.jetbrains.kotlin.util.PrivateForInline;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ü\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0017\b&\u0018\u00002\u0010\u0012\u0004\u0012\u00020\u0002\u0012\u0006\u0012\u0004\u0018\u00010\u00030\u0001B\u000f\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\u0010\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000fH\u0014J\u0010\u0010\u0011\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0014J\u0010\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H$J\b\u0010\u0015\u001a\u00020\u0002H\u0016J\u001a\u0010\u0016\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010\u0018\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001a2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u0010\u0010\u001b\u001a\u00020\u00022\u0006\u0010\u001c\u001a\u00020\u001dH\u0002J\u001a\u0010\u001e\u001a\u00020\u00022\u0006\u0010\u001f\u001a\u00020 2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010!\u001a\u00020\u00022\u0006\u0010\"\u001a\u00020#2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010$\u001a\u00020\u00022\u0006\u0010%\u001a\u00020&2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010'\u001a\u00020\u00022\u0006\u0010(\u001a\u00020)2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010*\u001a\u00020\u00022\u0006\u0010+\u001a\u00020,2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010-\u001a\u00020\u00022\u0006\u0010.\u001a\u00020/2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u00100\u001a\u00020\u00022\u0006\u00101\u001a\u0002022\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u00103\u001a\u00020\u00022\u0006\u00104\u001a\u0002052\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u00106\u001a\u00020\u00022\u0006\u00107\u001a\u0002082\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u00109\u001a\u00020\u00022\u0006\u0010:\u001a\u00020;2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010<\u001a\u00020\u00022\u0006\u0010=\u001a\u00020>2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010?\u001a\u00020\u00022\u0006\u0010@\u001a\u00020A2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010B\u001a\u00020\u00022\u0006\u0010C\u001a\u00020D2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010E\u001a\u00020\u00022\u0006\u0010F\u001a\u00020G2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010H\u001a\u00020\u00022\u0006\u0010I\u001a\u00020J2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010K\u001a\u00020\u00022\u0006\u0010L\u001a\u00020M2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010N\u001a\u00020\u00022\u0006\u0010O\u001a\u00020P2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010Q\u001a\u00020\u00022\u0006\u0010R\u001a\u00020S2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010T\u001a\u00020\u00022\u0006\u0010U\u001a\u00020V2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010W\u001a\u00020\u00022\u0006\u0010X\u001a\u00020Y2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010Z\u001a\u00020\u00022\u0006\u0010[\u001a\u00020\\2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010]\u001a\u00020\u00022\u0006\u0010^\u001a\u00020_2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010`\u001a\u00020\u00022\u0006\u0010a\u001a\u00020b2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010c\u001a\u00020\u00022\u0006\u0010d\u001a\u00020e2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u0010\u0010f\u001a\u00020\u00022\u0006\u0010\u0012\u001a\u00020\u0013H\u0002J\u001a\u0010g\u001a\u00020\u00022\u0006\u0010h\u001a\u00020i2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010j\u001a\u00020\u00022\u0006\u0010k\u001a\u00020l2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010m\u001a\u00020\u00022\u0006\u0010n\u001a\u00020o2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010p\u001a\u00020\u00022\u0006\u0010q\u001a\u00020r2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010s\u001a\u00020\u00022\u0006\u0010t\u001a\u00020u2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010v\u001a\u00020\u00022\u0006\u0010w\u001a\u00020x2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010y\u001a\u00020\u00022\u0006\u0010z\u001a\u00020{2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001a\u0010|\u001a\u00020\u00022\u0006\u0010}\u001a\u00020~2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001c\u0010\u007f\u001a\u00020\u00022\b\u0010\u0080\u0001\u001a\u00030\u0081\u00012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001d\u0010\u0082\u0001\u001a\u00020\u00022\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001d\u0010\u0085\u0001\u001a\u00020\u00022\b\u0010\u0086\u0001\u001a\u00030\u0087\u00012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J\u001d\u0010\u0088\u0001\u001a\u00020\u00022\b\u0010\u0089\u0001\u001a\u00030\u008a\u00012\b\u0010\u0017\u001a\u0004\u0018\u00010\u0003H\u0016J&\u0010\u008b\u0001\u001a\u00020\u00022\u0006\u0010\u000e\u001a\u00020\u000f2\u000f\b\u0002\u0010[\u001a\t\u0012\u0004\u0012\u00020\u00020\u008c\u0001H\u0084\bø\u0001\u0000J&\u0010\u008d\u0001\u001a\u00020\u00022\u0006\u0010U\u001a\u00020V2\u000f\b\u0002\u0010[\u001a\t\u0012\u0004\u0012\u00020\u00020\u008c\u0001H\u0084\bø\u0001\u0000J;\u0010\u008e\u0001\u001a\u0003H\u008f\u0001\"\u0005\b\u0000\u0010\u008f\u00012\b\u0010\u0090\u0001\u001a\u00030\u0091\u00012\u0007\u0010\u0092\u0001\u001a\u00020\r2\u000e\u0010[\u001a\n\u0012\u0005\u0012\u0003H\u008f\u00010\u008c\u0001H\u0082\b¢\u0006\u0003\u0010\u0093\u0001J1\u0010\u0094\u0001\u001a\u0003H\u008f\u0001\"\u0005\b\u0000\u0010\u008f\u00012\u0007\u0010\u0090\u0001\u001a\u00020>2\u000e\u0010[\u001a\n\u0012\u0005\u0012\u0003H\u008f\u00010\u008c\u0001H\u0082\b¢\u0006\u0003\u0010\u0095\u0001J\u0013\u0010\u0096\u0001\u001a\u00020\u00022\b\u0010\u0097\u0001\u001a\u00030\u0098\u0001H\u0002J\u0013\u0010\u0099\u0001\u001a\u00020\u00022\b\u0010\u0086\u0001\u001a\u00030\u0087\u0001H\u0002J5\u0010\u009a\u0001\u001a\u0003H\u009b\u0001\"\u0005\b\u0000\u0010\u009b\u00012\b\u0010\u0097\u0001\u001a\u00030\u0098\u00012\u000e\u0010[\u001a\n\u0012\u0005\u0012\u0003H\u009b\u00010\u008c\u0001H\u0086\bø\u0001\u0000¢\u0006\u0003\u0010\u009c\u0001J5\u0010\u009d\u0001\u001a\u0003H\u009b\u0001\"\u0005\b\u0000\u0010\u009b\u00012\b\u0010\u0086\u0001\u001a\u00030\u0087\u00012\u000e\u0010[\u001a\n\u0012\u0005\u0012\u0003H\u009b\u00010\u008c\u0001H\u0086\bø\u0001\u0000¢\u0006\u0003\u0010\u009e\u0001J3\u0010\u009f\u0001\u001a\u0003H\u009b\u0001\"\u0005\b\u0000\u0010\u009b\u00012\u0006\u0010\u000e\u001a\u00020\u000f2\u000e\u0010[\u001a\n\u0012\u0005\u0012\u0003H\u009b\u00010\u008c\u0001H\u0086\bø\u0001\u0000¢\u0006\u0003\u0010 \u0001J3\u0010¡\u0001\u001a\u0003H\u009b\u0001\"\u0005\b\u0000\u0010\u009b\u00012\u0006\u0010U\u001a\u00020V2\u000e\u0010[\u001a\n\u0012\u0005\u0012\u0003H\u009b\u00010\u008c\u0001H\u0086\bø\u0001\u0000¢\u0006\u0003\u0010¢\u0001J3\u0010£\u0001\u001a\u0003H\u008f\u0001\"\u0005\b\u0000\u0010\u008f\u00012\u0006\u0010\u0012\u001a\u00020\u00132\u000e\u0010[\u001a\n\u0012\u0005\u0012\u0003H\u008f\u00010\u008c\u0001H\u0086\bø\u0001\u0000¢\u0006\u0003\u0010¤\u0001J3\u0010¥\u0001\u001a\u0003H\u009b\u0001\"\u0005\b\u0000\u0010\u009b\u00012\u0006\u0010\u0019\u001a\u00020\u001a2\u000e\u0010[\u001a\n\u0012\u0005\u0012\u0003H\u009b\u00010\u008c\u0001H\u0086\bø\u0001\u0000¢\u0006\u0003\u0010¦\u0001J3\u0010§\u0001\u001a\u0003H\u009b\u0001\"\u0005\b\u0000\u0010\u009b\u00012\u0006\u0010@\u001a\u00020A2\u000e\u0010[\u001a\n\u0012\u0005\u0012\u0003H\u009b\u00010\u008c\u0001H\u0086\bø\u0001\u0000¢\u0006\u0003\u0010¨\u0001J(\u0010©\u0001\u001a\u0003H\u009b\u0001\"\u0005\b\u0000\u0010\u009b\u00012\u000e\u0010[\u001a\n\u0012\u0005\u0012\u0003H\u009b\u00010\u008c\u0001H\u0082\b¢\u0006\u0003\u0010ª\u0001J(\u0010«\u0001\u001a\u0003H\u009b\u0001\"\u0005\b\u0000\u0010\u009b\u00012\u000e\u0010[\u001a\n\u0012\u0005\u0012\u0003H\u009b\u00010\u008c\u0001H\u0082\b¢\u0006\u0003\u0010ª\u0001J0\u0010¬\u0001\u001a\u0003H\u009b\u0001\"\u0005\b\u0000\u0010\u009b\u00012\u0006\u0010\u0019\u001a\u00020l2\u000e\u0010[\u001a\n\u0012\u0005\u0012\u0003H\u009b\u00010\u008c\u0001H\u0082\b¢\u0006\u0003\u0010\u00ad\u0001J\u000f\u0010®\u0001\u001a\u00020\u00022\u0006\u0010\u0019\u001a\u00020\u001aR!\u0010\u0004\u001a\u00020\u0005@\u0007X\u0086\u000e\u0082\u0001\u0002\b\u000b¢\u0006\u000e\n\u0000\u001a\u0004\b\b\u0010\t\"\u0004\b\n\u0010\u0007\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006¯\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/analysis/collectors/AbstractDiagnosticCollectorVisitor;", "Lorg/jetbrains/kotlin/fir/visitors/FirDefaultVisitor;", Argument.Delimiters.none, Argument.Delimiters.none, "context", "Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContextForProvider;", "<init>", "(Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContextForProvider;)V", "getContext", "()Lorg/jetbrains/kotlin/fir/analysis/checkers/context/CheckerContextForProvider;", "setContext", "Lorg/jetbrains/kotlin/util/PrivateForInline;", "shouldVisitDeclaration", Argument.Delimiters.none, "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "onDeclarationExit", "visitNestedElements", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "checkElement", "checkSettings", "visitElement", "data", "visitAnnotationContainer", "annotationContainer", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "visitJump", "loopJump", "Lorg/jetbrains/kotlin/fir/expressions/FirLoopJump;", "visitBreakExpression", "breakExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBreakExpression;", "visitContinueExpression", "continueExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirContinueExpression;", "visitRegularClass", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "visitAnonymousObjectExpression", "anonymousObjectExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousObjectExpression;", "visitAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "visitScript", "script", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "visitNamedFunction", "namedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "visitConstructor", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "visitErrorPrimaryConstructor", "errorPrimaryConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorPrimaryConstructor;", "visitAnonymousFunctionExpression", "anonymousFunctionExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "visitAnonymousFunction", "anonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "visitProperty", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "visitErrorProperty", "errorProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorProperty;", "visitTypeAlias", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "visitPropertyAccessor", "propertyAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "visitReceiverParameter", "receiverParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "visitValueParameter", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "visitEnumEntry", "enumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "visitFile", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "visitAnonymousInitializer", "anonymousInitializer", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "visitBlock", "block", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "visitLazyBlock", "lazyBlock", "Lorg/jetbrains/kotlin/fir/expressions/FirLazyBlock;", "visitLazyExpression", "lazyExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirLazyExpression;", "visitLazyContractDescription", "lazyContractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirLazyContractDescription;", "suppressOrThrowError", "visitContractDescription", "contractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;", "visitTypeRef", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "visitErrorTypeRef", "errorTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirErrorTypeRef;", "visitResolvedTypeRef", "resolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "visitFunctionCall", "functionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "visitQualifiedAccessExpression", "qualifiedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "visitPropertyAccessExpression", "propertyAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "visitAnnotationCall", "annotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "visitVariableAssignment", "variableAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "visitDelegatedConstructorCall", "delegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "visitGetClassCall", "getClassCall", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "visitDanglingModifierList", "danglingModifierList", "Lorg/jetbrains/kotlin/fir/declarations/FirDanglingModifierList;", "visitWithDeclaration", "Lkotlin/Function0;", "visitWithFile", "withInlineFunctionBodyIfApplicable", "T", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "isInline", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;ZLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withLambdaBodyIfApplicable", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "visitWithCallOrAssignment", "callOrAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "visitWithGetClassCall", "withCallOrAssignment", "R", "(Lorg/jetbrains/kotlin/fir/expressions/FirStatement;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withGetClassCall", "(Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withDeclaration", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withFile", "(Lorg/jetbrains/kotlin/fir/declarations/FirFile;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withElement", "(Lorg/jetbrains/kotlin/fir/FirElement;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withAnnotationContainer", "(Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "withAdditionalSuppresses", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "suppressInlineFunctionBodyContext", "(Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "insideContractBody", "withTypeRefAnnotationContainer", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "addSuppressedDiagnosticsToContext", "org.jetbrains.kotlin:checkers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class AbstractDiagnosticCollectorVisitor extends FirDefaultVisitor {
    private CheckerContextForProvider context;

    public AbstractDiagnosticCollectorVisitor(CheckerContextForProvider checkerContextForProvider) {
        checkerContextForProvider.getClass();
        this.context = checkerContextForProvider;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final void suppressOrThrowError(FirElement element) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (Boolean.parseBoolean(System.getProperty("kotlin.suppress.lazy.expression.access"))) {
            return;
        }
        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(Reflection.getOrCreateKotlinClass(element.getClass()).getSimpleName() + " should be calculated before accessing", (Throwable) null);
        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "firElement", element);
        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
        throw kotlinIllegalArgumentExceptionWithAttachments;
    }

    private final void visitJump(FirLoopJump loopJump) {
        CheckerContextForProvider context = getContext();
        setContext(getContext().addElement(loopJump));
        try {
            FirSession session = getContext().getSession();
            try {
                context = getContext();
                addSuppressedDiagnosticsToContext(loopJump);
                boolean zIsEmpty = loopJump.getAnnotations().isEmpty();
                if (!zIsEmpty) {
                    setContext(getContext().addAnnotationContainer(loopJump));
                }
                try {
                    checkElement(loopJump);
                    FirTargetElement labeledElement = loopJump.getTarget().getLabeledElement();
                    if (!(((FirLoop) labeledElement) instanceof FirErrorLoop)) {
                        labeledElement = null;
                    }
                    FirLoop firLoop = (FirLoop) labeledElement;
                    if (firLoop != null) {
                        firLoop.accept(this, null);
                        Unit unit = Unit.INSTANCE;
                    }
                    if (!zIsEmpty) {
                        context.dropAnnotationContainer();
                    }
                    setContext(context);
                    context.dropElement();
                } finally {
                    if (!zIsEmpty) {
                        context.dropAnnotationContainer();
                    }
                    setContext(context);
                }
            } catch (Throwable th) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(loopJump, th);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th2) {
            context.dropElement();
            setContext(context);
            throw th2;
        }
    }

    private final void visitWithCallOrAssignment(FirStatement callOrAssignment) {
        CheckerContextForProvider context = getContext();
        setContext(getContext().addCallOrAssignment(callOrAssignment));
        try {
            FirSession session = getContext().getSession();
            try {
                visitElement((FirElement) callOrAssignment, (Void) null);
                Unit unit = Unit.INSTANCE;
                context.dropCallOrAssignment();
                setContext(context);
            } catch (Throwable th) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(callOrAssignment, th);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th2) {
            context.dropCallOrAssignment();
            setContext(context);
            throw th2;
        }
    }

    public static /* synthetic */ void visitWithDeclaration$default(AbstractDiagnosticCollectorVisitor abstractDiagnosticCollectorVisitor, final FirDeclaration firDeclaration, Function0 function0, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: visitWithDeclaration");
            return;
        }
        if ((i & 2) != 0) {
            function0 = new Function0<Unit>() { // from class: org.jetbrains.kotlin.fir.analysis.collectors.AbstractDiagnosticCollectorVisitor.visitWithDeclaration.1
                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m245invoke() {
                    AbstractDiagnosticCollectorVisitor.this.visitNestedElements(firDeclaration);
                }

                public /* bridge */ /* synthetic */ Object invoke() {
                    m245invoke();
                    return Unit.INSTANCE;
                }
            };
        }
        firDeclaration.getClass();
        function0.getClass();
        if (abstractDiagnosticCollectorVisitor.shouldVisitDeclaration(firDeclaration)) {
            abstractDiagnosticCollectorVisitor.checkElement(firDeclaration);
            CheckerContextForProvider context = abstractDiagnosticCollectorVisitor.getContext();
            abstractDiagnosticCollectorVisitor.setContext(abstractDiagnosticCollectorVisitor.getContext().addDeclaration(firDeclaration));
            try {
                FirSession session = abstractDiagnosticCollectorVisitor.getContext().getSession();
                try {
                    function0.invoke();
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    context.dropDeclaration();
                    abstractDiagnosticCollectorVisitor.setContext(context);
                    InlineMarker.finallyEnd(1);
                    abstractDiagnosticCollectorVisitor.onDeclarationExit(firDeclaration);
                } catch (Throwable th) {
                    UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(firDeclaration, th);
                    throw new KotlinNothingValueException();
                }
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                context.dropDeclaration();
                abstractDiagnosticCollectorVisitor.setContext(context);
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        }
    }

    public static /* synthetic */ void visitWithFile$default(AbstractDiagnosticCollectorVisitor abstractDiagnosticCollectorVisitor, final FirFile firFile, Function0 function0, int i, Object obj) {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: visitWithFile");
            return;
        }
        if ((i & 2) != 0) {
            function0 = new Function0<Unit>() { // from class: org.jetbrains.kotlin.fir.analysis.collectors.AbstractDiagnosticCollectorVisitor.visitWithFile.1
                /* JADX INFO: renamed from: invoke, reason: collision with other method in class */
                public final void m246invoke() {
                    AbstractDiagnosticCollectorVisitor.this.visitNestedElements(firFile);
                }

                public /* bridge */ /* synthetic */ Object invoke() {
                    m246invoke();
                    return Unit.INSTANCE;
                }
            };
        }
        firFile.getClass();
        function0.getClass();
        CheckerContextForProvider context = abstractDiagnosticCollectorVisitor.getContext();
        abstractDiagnosticCollectorVisitor.setContext(abstractDiagnosticCollectorVisitor.getContext().enterFile(firFile));
        try {
            if (abstractDiagnosticCollectorVisitor.shouldVisitDeclaration(firFile)) {
                abstractDiagnosticCollectorVisitor.checkElement(firFile);
                CheckerContextForProvider context2 = abstractDiagnosticCollectorVisitor.getContext();
                abstractDiagnosticCollectorVisitor.setContext(abstractDiagnosticCollectorVisitor.getContext().addDeclaration(firFile));
                try {
                    FirSession session = abstractDiagnosticCollectorVisitor.getContext().getSession();
                    try {
                        function0.invoke();
                        Unit unit = Unit.INSTANCE;
                        InlineMarker.finallyStart(1);
                        context2.dropDeclaration();
                        abstractDiagnosticCollectorVisitor.setContext(context2);
                        InlineMarker.finallyEnd(1);
                        abstractDiagnosticCollectorVisitor.onDeclarationExit(firFile);
                    } catch (Throwable th) {
                        UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(firFile, th);
                        throw new KotlinNothingValueException();
                    }
                } catch (Throwable th2) {
                    InlineMarker.finallyStart(1);
                    context2.dropDeclaration();
                    abstractDiagnosticCollectorVisitor.setContext(context2);
                    InlineMarker.finallyEnd(1);
                    throw th2;
                }
            }
            Unit unit2 = Unit.INSTANCE;
            InlineMarker.finallyStart(1);
            context.exitFile(firFile);
            abstractDiagnosticCollectorVisitor.setContext(context);
            InlineMarker.finallyEnd(1);
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            context.exitFile(firFile);
            abstractDiagnosticCollectorVisitor.setContext(context);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    private final void visitWithGetClassCall(FirGetClassCall getClassCall) {
        CheckerContextForProvider context = getContext();
        setContext(getContext().addGetClassCall(getClassCall));
        try {
            FirSession session = getContext().getSession();
            try {
                visitElement((FirElement) getClassCall, (Void) null);
                Unit unit = Unit.INSTANCE;
                context.dropGetClassCall();
                setContext(context);
            } catch (Throwable th) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(getClassCall, th);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th2) {
            context.dropGetClassCall();
            setContext(context);
            throw th2;
        }
    }

    public final void addSuppressedDiagnosticsToContext(FirAnnotationContainer annotationContainer) {
        annotationContainer.getClass();
        List<String> diagnosticsSuppressedForContainer = AbstractDiagnosticCollector.INSTANCE.getDiagnosticsSuppressedForContainer(annotationContainer);
        if (diagnosticsSuppressedForContainer == null) {
            return;
        }
        this.context = this.context.addSuppressedDiagnostics(diagnosticsSuppressedForContainer, diagnosticsSuppressedForContainer.contains(AbstractDiagnosticCollector.SUPPRESS_ALL_INFOS), diagnosticsSuppressedForContainer.contains(AbstractDiagnosticCollector.SUPPRESS_ALL_WARNINGS), diagnosticsSuppressedForContainer.contains(AbstractDiagnosticCollector.SUPPRESS_ALL_ERRORS));
    }

    public abstract void checkElement(FirElement element);

    public void checkSettings() {
    }

    public final CheckerContextForProvider getContext() {
        return this.context;
    }

    public void onDeclarationExit(FirDeclaration declaration) {
        declaration.getClass();
    }

    @PrivateForInline
    public final void setContext(CheckerContextForProvider checkerContextForProvider) {
        checkerContextForProvider.getClass();
        this.context = checkerContextForProvider;
    }

    public boolean shouldVisitDeclaration(FirDeclaration declaration) {
        declaration.getClass();
        return true;
    }

    public void visitAnnotationCall(FirAnnotationCall annotationCall, Void data) {
        annotationCall.getClass();
        FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext = this.context.getInlineFunctionBodyContext();
        if (inlineFunctionBodyContext != null) {
            this.context = this.context.setInlineFunctionBodyContext(null);
        } else {
            inlineFunctionBodyContext = null;
        }
        try {
            visitWithCallOrAssignment(annotationCall);
            Unit unit = Unit.INSTANCE;
        } finally {
            if (inlineFunctionBodyContext != null) {
                this.context = this.context.setInlineFunctionBodyContext(inlineFunctionBodyContext);
            }
        }
    }

    public void visitAnnotationContainer(FirAnnotationContainer annotationContainer, Void data) {
        annotationContainer.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().addElement(annotationContainer));
        try {
            FirSession session = getContext().getSession();
            try {
                context = getContext();
                addSuppressedDiagnosticsToContext(annotationContainer);
                boolean zIsEmpty = annotationContainer.getAnnotations().isEmpty();
                if (!zIsEmpty) {
                    setContext(getContext().addAnnotationContainer(annotationContainer));
                }
                try {
                    checkElement(annotationContainer);
                    visitNestedElements(annotationContainer);
                    Unit unit = Unit.INSTANCE;
                    if (!zIsEmpty) {
                        context.dropAnnotationContainer();
                    }
                    setContext(context);
                    context.dropElement();
                } finally {
                    if (!zIsEmpty) {
                        context.dropAnnotationContainer();
                    }
                    setContext(context);
                }
            } catch (Throwable th) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(annotationContainer, th);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th2) {
            context.dropElement();
            setContext(context);
            throw th2;
        }
    }

    public void visitAnonymousFunction(FirAnonymousFunction anonymousFunction, Void data) {
        anonymousFunction.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().addElement(anonymousFunction));
        try {
            FirSession session = getContext().getSession();
            try {
                CheckerContextForProvider context2 = getContext();
                addSuppressedDiagnosticsToContext(anonymousFunction);
                boolean zIsEmpty = anonymousFunction.getAnnotations().isEmpty();
                if (!zIsEmpty) {
                    setContext(getContext().addAnnotationContainer(anonymousFunction));
                }
                try {
                    FirAnonymousUnusedParamChecker.LambdaBodyContext lambdaBodyContext = this.context.getLambdaBodyContext();
                    try {
                        if (anonymousFunction.getIsLambda()) {
                            CheckerContextForProvider checkerContextForProvider = this.context;
                            this.context = checkerContextForProvider.setLambdaBodyContext(FirAnonymousUnusedParamCheckerKt.createLambdaBodyContext(anonymousFunction, checkerContextForProvider));
                        }
                        if (shouldVisitDeclaration(anonymousFunction)) {
                            checkElement(anonymousFunction);
                            CheckerContextForProvider context3 = getContext();
                            setContext(getContext().addDeclaration(anonymousFunction));
                            try {
                                FirSession session2 = getContext().getSession();
                                try {
                                    visitNestedElements(anonymousFunction);
                                    Unit unit = Unit.INSTANCE;
                                    context3.dropDeclaration();
                                    setContext(context3);
                                    onDeclarationExit(anonymousFunction);
                                } catch (Throwable th) {
                                    UtilsKt.getExceptionHandler(session2).handleExceptionOnElementAnalysis(anonymousFunction, th);
                                    throw new KotlinNothingValueException();
                                }
                            } catch (Throwable th2) {
                                context3.dropDeclaration();
                                setContext(context3);
                                throw th2;
                            }
                        }
                        Unit unit2 = Unit.INSTANCE;
                        if (anonymousFunction.getIsLambda()) {
                            this.context = this.context.setLambdaBodyContext(lambdaBodyContext);
                        }
                        if (!zIsEmpty) {
                            context2.dropAnnotationContainer();
                        }
                        setContext(context2);
                        context.dropElement();
                        setContext(context);
                    } catch (Throwable th3) {
                        if (anonymousFunction.getIsLambda()) {
                            this.context = this.context.setLambdaBodyContext(lambdaBodyContext);
                        }
                        throw th3;
                    }
                } catch (Throwable th4) {
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    throw th4;
                }
            } catch (Throwable th5) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(anonymousFunction, th5);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th6) {
            context.dropElement();
            setContext(context);
            throw th6;
        }
    }

    public void visitAnonymousFunctionExpression(FirAnonymousFunctionExpression anonymousFunctionExpression, Void data) {
        anonymousFunctionExpression.getClass();
        if (!UtilsKt.shouldSuppressInlineContextAt(anonymousFunctionExpression, (FirBasedSymbol) CollectionsKt.lastOrNull(this.context.getContainingDeclarations()))) {
            visitAnonymousFunction(anonymousFunctionExpression.getAnonymousFunction(), data);
            return;
        }
        FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext = this.context.getInlineFunctionBodyContext();
        if (inlineFunctionBodyContext != null) {
            this.context = this.context.setInlineFunctionBodyContext(null);
        } else {
            inlineFunctionBodyContext = null;
        }
        try {
            visitAnonymousFunction(anonymousFunctionExpression.getAnonymousFunction(), data);
            Unit unit = Unit.INSTANCE;
        } finally {
            if (inlineFunctionBodyContext != null) {
                this.context = this.context.setInlineFunctionBodyContext(inlineFunctionBodyContext);
            }
        }
    }

    public void visitAnonymousInitializer(FirAnonymousInitializer anonymousInitializer, Void data) {
        anonymousInitializer.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().addElement(anonymousInitializer));
        try {
            FirSession session = getContext().getSession();
            try {
                if (shouldVisitDeclaration(anonymousInitializer)) {
                    checkElement(anonymousInitializer);
                    CheckerContextForProvider context2 = getContext();
                    setContext(getContext().addDeclaration(anonymousInitializer));
                    try {
                        FirSession session2 = getContext().getSession();
                        try {
                            visitNestedElements(anonymousInitializer);
                            Unit unit = Unit.INSTANCE;
                            context2.dropDeclaration();
                            setContext(context2);
                            onDeclarationExit(anonymousInitializer);
                        } catch (Throwable th) {
                            UtilsKt.getExceptionHandler(session2).handleExceptionOnElementAnalysis(anonymousInitializer, th);
                            throw new KotlinNothingValueException();
                        }
                    } catch (Throwable th2) {
                        context2.dropDeclaration();
                        setContext(context2);
                        throw th2;
                    }
                }
                Unit unit2 = Unit.INSTANCE;
                context.dropElement();
                setContext(context);
            } catch (Throwable th3) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(anonymousInitializer, th3);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th4) {
            context.dropElement();
            setContext(context);
            throw th4;
        }
    }

    public void visitAnonymousObject(FirAnonymousObject anonymousObject, Void data) {
        anonymousObject.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().addElement(anonymousObject));
        try {
            FirSession session = getContext().getSession();
            try {
                CheckerContextForProvider context2 = getContext();
                addSuppressedDiagnosticsToContext(anonymousObject);
                boolean zIsEmpty = anonymousObject.getAnnotations().isEmpty();
                if (!zIsEmpty) {
                    setContext(getContext().addAnnotationContainer(anonymousObject));
                }
                try {
                    if (shouldVisitDeclaration(anonymousObject)) {
                        checkElement(anonymousObject);
                        CheckerContextForProvider context3 = getContext();
                        setContext(getContext().addDeclaration(anonymousObject));
                        try {
                            FirSession session2 = getContext().getSession();
                            try {
                                visitNestedElements(anonymousObject);
                                Unit unit = Unit.INSTANCE;
                                context3.dropDeclaration();
                                setContext(context3);
                                onDeclarationExit(anonymousObject);
                            } catch (Throwable th) {
                                UtilsKt.getExceptionHandler(session2).handleExceptionOnElementAnalysis(anonymousObject, th);
                                throw new KotlinNothingValueException();
                            }
                        } catch (Throwable th2) {
                            context3.dropDeclaration();
                            setContext(context3);
                            throw th2;
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    context.dropElement();
                    setContext(context);
                } catch (Throwable th3) {
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    throw th3;
                }
            } catch (Throwable th4) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(anonymousObject, th4);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th5) {
            context.dropElement();
            setContext(context);
            throw th5;
        }
    }

    public void visitAnonymousObjectExpression(FirAnonymousObjectExpression anonymousObjectExpression, Void data) {
        anonymousObjectExpression.getClass();
        anonymousObjectExpression.getAnonymousObject().accept(this, data);
    }

    public void visitBlock(FirBlock block, Void data) {
        block.getClass();
        if (!(block instanceof FirContractCallBlock)) {
            visitExpression(block, data);
            return;
        }
        this.context = this.context.enterContractBody();
        try {
            visitExpression(block, data);
            Unit unit = Unit.INSTANCE;
        } finally {
            this.context = this.context.exitContractBody();
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitBreakExpression(FirBreakExpression firBreakExpression, Object obj) {
        visitBreakExpression(firBreakExpression, (Void) obj);
        return Unit.INSTANCE;
    }

    public void visitConstructor(FirConstructor constructor, Void data) {
        constructor.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().addElement(constructor));
        try {
            FirSession session = getContext().getSession();
            try {
                CheckerContextForProvider context2 = getContext();
                addSuppressedDiagnosticsToContext(constructor);
                boolean zIsEmpty = constructor.getAnnotations().isEmpty();
                if (!zIsEmpty) {
                    setContext(getContext().addAnnotationContainer(constructor));
                }
                try {
                    if (shouldVisitDeclaration(constructor)) {
                        checkElement(constructor);
                        CheckerContextForProvider context3 = getContext();
                        setContext(getContext().addDeclaration(constructor));
                        try {
                            FirSession session2 = getContext().getSession();
                            try {
                                visitNestedElements(constructor);
                                Unit unit = Unit.INSTANCE;
                                context3.dropDeclaration();
                                setContext(context3);
                                onDeclarationExit(constructor);
                            } catch (Throwable th) {
                                UtilsKt.getExceptionHandler(session2).handleExceptionOnElementAnalysis(constructor, th);
                                throw new KotlinNothingValueException();
                            }
                        } catch (Throwable th2) {
                            context3.dropDeclaration();
                            setContext(context3);
                            throw th2;
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    context.dropElement();
                    setContext(context);
                } catch (Throwable th3) {
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    throw th3;
                }
            } catch (Throwable th4) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(constructor, th4);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th5) {
            context.dropElement();
            setContext(context);
            throw th5;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitContinueExpression(FirContinueExpression firContinueExpression, Object obj) {
        visitContinueExpression(firContinueExpression, (Void) obj);
        return Unit.INSTANCE;
    }

    public void visitContractDescription(FirContractDescription contractDescription, Void data) {
        contractDescription.getClass();
        FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext = this.context.getInlineFunctionBodyContext();
        if (inlineFunctionBodyContext != null) {
            this.context = this.context.setInlineFunctionBodyContext(null);
        } else {
            inlineFunctionBodyContext = null;
        }
        try {
            visitElement((FirElement) contractDescription, data);
            Unit unit = Unit.INSTANCE;
        } finally {
            if (inlineFunctionBodyContext != null) {
                this.context = this.context.setInlineFunctionBodyContext(inlineFunctionBodyContext);
            }
        }
    }

    public void visitDanglingModifierList(FirDanglingModifierList danglingModifierList, Void data) {
        danglingModifierList.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().addElement(danglingModifierList));
        try {
            FirSession session = getContext().getSession();
            try {
                CheckerContextForProvider context2 = getContext();
                addSuppressedDiagnosticsToContext(danglingModifierList);
                boolean zIsEmpty = danglingModifierList.getAnnotations().isEmpty();
                if (!zIsEmpty) {
                    setContext(getContext().addAnnotationContainer(danglingModifierList));
                }
                try {
                    if (shouldVisitDeclaration(danglingModifierList)) {
                        checkElement(danglingModifierList);
                        CheckerContextForProvider context3 = getContext();
                        setContext(getContext().addDeclaration(danglingModifierList));
                        try {
                            FirSession session2 = getContext().getSession();
                            try {
                                visitNestedElements(danglingModifierList);
                                Unit unit = Unit.INSTANCE;
                                context3.dropDeclaration();
                                setContext(context3);
                                onDeclarationExit(danglingModifierList);
                            } catch (Throwable th) {
                                UtilsKt.getExceptionHandler(session2).handleExceptionOnElementAnalysis(danglingModifierList, th);
                                throw new KotlinNothingValueException();
                            }
                        } catch (Throwable th2) {
                            context3.dropDeclaration();
                            setContext(context3);
                            throw th2;
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    context.dropElement();
                    setContext(context);
                } catch (Throwable th3) {
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    throw th3;
                }
            } catch (Throwable th4) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(danglingModifierList, th4);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th5) {
            context.dropElement();
            setContext(context);
            throw th5;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitDelegatedConstructorCall(FirDelegatedConstructorCall firDelegatedConstructorCall, Object obj) {
        visitDelegatedConstructorCall(firDelegatedConstructorCall, (Void) obj);
        return Unit.INSTANCE;
    }

    public void visitElement(FirElement element, Void data) {
        element.getClass();
        if (!(element instanceof FirAnnotationContainer)) {
            CheckerContextForProvider context = getContext();
            setContext(getContext().addElement(element));
            try {
                FirSession session = getContext().getSession();
                try {
                    checkElement(element);
                    visitNestedElements(element);
                    Unit unit = Unit.INSTANCE;
                    context.dropElement();
                    setContext(context);
                    return;
                } catch (Throwable th) {
                    UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(element, th);
                    throw new KotlinNothingValueException();
                }
            } catch (Throwable th2) {
                context.dropElement();
                setContext(context);
                throw th2;
            }
        }
        FirAnnotationContainer firAnnotationContainer = (FirAnnotationContainer) element;
        CheckerContextForProvider context2 = getContext();
        setContext(getContext().addElement(firAnnotationContainer));
        try {
            FirSession session2 = getContext().getSession();
            try {
                context2 = getContext();
                addSuppressedDiagnosticsToContext(firAnnotationContainer);
                boolean zIsEmpty = firAnnotationContainer.getAnnotations().isEmpty();
                if (!zIsEmpty) {
                    setContext(getContext().addAnnotationContainer(firAnnotationContainer));
                }
                try {
                    checkElement(element);
                    visitNestedElements(element);
                    Unit unit2 = Unit.INSTANCE;
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    context2.dropElement();
                } finally {
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                }
            } catch (Throwable th3) {
                UtilsKt.getExceptionHandler(session2).handleExceptionOnElementAnalysis(firAnnotationContainer, th3);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th4) {
            context2.dropElement();
            setContext(context2);
            throw th4;
        }
    }

    public void visitEnumEntry(FirEnumEntry enumEntry, Void data) {
        enumEntry.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().addElement(enumEntry));
        try {
            FirSession session = getContext().getSession();
            try {
                CheckerContextForProvider context2 = getContext();
                addSuppressedDiagnosticsToContext(enumEntry);
                boolean zIsEmpty = enumEntry.getAnnotations().isEmpty();
                if (!zIsEmpty) {
                    setContext(getContext().addAnnotationContainer(enumEntry));
                }
                try {
                    if (shouldVisitDeclaration(enumEntry)) {
                        checkElement(enumEntry);
                        CheckerContextForProvider context3 = getContext();
                        setContext(getContext().addDeclaration(enumEntry));
                        try {
                            FirSession session2 = getContext().getSession();
                            try {
                                visitNestedElements(enumEntry);
                                Unit unit = Unit.INSTANCE;
                                context3.dropDeclaration();
                                setContext(context3);
                                onDeclarationExit(enumEntry);
                            } catch (Throwable th) {
                                UtilsKt.getExceptionHandler(session2).handleExceptionOnElementAnalysis(enumEntry, th);
                                throw new KotlinNothingValueException();
                            }
                        } catch (Throwable th2) {
                            context3.dropDeclaration();
                            setContext(context3);
                            throw th2;
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    context.dropElement();
                    setContext(context);
                } catch (Throwable th3) {
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    throw th3;
                }
            } catch (Throwable th4) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(enumEntry, th4);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th5) {
            context.dropElement();
            setContext(context);
            throw th5;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitErrorPrimaryConstructor(FirErrorPrimaryConstructor firErrorPrimaryConstructor, Object obj) {
        visitErrorPrimaryConstructor(firErrorPrimaryConstructor, (Void) obj);
        return Unit.INSTANCE;
    }

    public void visitErrorProperty(FirErrorProperty errorProperty, Void data) {
        errorProperty.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().addElement(errorProperty));
        try {
            FirSession session = getContext().getSession();
            try {
                CheckerContextForProvider context2 = getContext();
                addSuppressedDiagnosticsToContext(errorProperty);
                boolean zIsEmpty = errorProperty.getAnnotations().isEmpty();
                if (!zIsEmpty) {
                    setContext(getContext().addAnnotationContainer(errorProperty));
                }
                try {
                    if (shouldVisitDeclaration(errorProperty)) {
                        checkElement(errorProperty);
                        CheckerContextForProvider context3 = getContext();
                        setContext(getContext().addDeclaration(errorProperty));
                        try {
                            FirSession session2 = getContext().getSession();
                            try {
                                visitNestedElements(errorProperty);
                                Unit unit = Unit.INSTANCE;
                                context3.dropDeclaration();
                                setContext(context3);
                                onDeclarationExit(errorProperty);
                            } catch (Throwable th) {
                                UtilsKt.getExceptionHandler(session2).handleExceptionOnElementAnalysis(errorProperty, th);
                                throw new KotlinNothingValueException();
                            }
                        } catch (Throwable th2) {
                            context3.dropDeclaration();
                            setContext(context3);
                            throw th2;
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    context.dropElement();
                    setContext(context);
                } catch (Throwable th3) {
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    throw th3;
                }
            } catch (Throwable th4) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(errorProperty, th4);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th5) {
            context.dropElement();
            setContext(context);
            throw th5;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitErrorTypeRef(FirErrorTypeRef firErrorTypeRef, Object obj) {
        visitErrorTypeRef(firErrorTypeRef, (Void) obj);
        return Unit.INSTANCE;
    }

    public void visitFile(FirFile file, Void data) {
        file.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().addElement(file));
        try {
            FirSession session = getContext().getSession();
            try {
                CheckerContextForProvider context2 = getContext();
                addSuppressedDiagnosticsToContext(file);
                boolean zIsEmpty = file.getAnnotations().isEmpty();
                if (!zIsEmpty) {
                    setContext(getContext().addAnnotationContainer(file));
                }
                try {
                    CheckerContextForProvider context3 = getContext();
                    setContext(getContext().enterFile(file));
                    try {
                        if (shouldVisitDeclaration(file)) {
                            checkElement(file);
                            CheckerContextForProvider context4 = getContext();
                            setContext(getContext().addDeclaration(file));
                            try {
                                FirSession session2 = getContext().getSession();
                                try {
                                    visitNestedElements(file);
                                    Unit unit = Unit.INSTANCE;
                                    context4.dropDeclaration();
                                    setContext(context4);
                                    onDeclarationExit(file);
                                } catch (Throwable th) {
                                    UtilsKt.getExceptionHandler(session2).handleExceptionOnElementAnalysis(file, th);
                                    throw new KotlinNothingValueException();
                                }
                            } catch (Throwable th2) {
                                context4.dropDeclaration();
                                setContext(context4);
                                throw th2;
                            }
                        }
                        Unit unit2 = Unit.INSTANCE;
                        context3.exitFile(file);
                        setContext(context3);
                        if (!zIsEmpty) {
                            context2.dropAnnotationContainer();
                        }
                        setContext(context2);
                        context.dropElement();
                        setContext(context);
                    } catch (Throwable th3) {
                        context3.exitFile(file);
                        setContext(context3);
                        throw th3;
                    }
                } catch (Throwable th4) {
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    throw th4;
                }
            } catch (Throwable th5) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(file, th5);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th6) {
            context.dropElement();
            setContext(context);
            throw th6;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitFunctionCall(FirFunctionCall firFunctionCall, Object obj) {
        visitFunctionCall(firFunctionCall, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitGetClassCall(FirGetClassCall firGetClassCall, Object obj) {
        visitGetClassCall(firGetClassCall, (Void) obj);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public void visitLazyBlock(FirLazyBlock lazyBlock, Void data) throws KotlinIllegalArgumentExceptionWithAttachments {
        lazyBlock.getClass();
        suppressOrThrowError(lazyBlock);
        super.visitLazyBlock(lazyBlock, data);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public void visitLazyContractDescription(FirLazyContractDescription lazyContractDescription, Void data) throws KotlinIllegalArgumentExceptionWithAttachments {
        lazyContractDescription.getClass();
        suppressOrThrowError(lazyContractDescription);
        super.visitLazyContractDescription(lazyContractDescription, data);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public void visitLazyExpression(FirLazyExpression lazyExpression, Void data) throws KotlinIllegalArgumentExceptionWithAttachments {
        lazyExpression.getClass();
        suppressOrThrowError(lazyExpression);
        super.visitLazyExpression(lazyExpression, data);
    }

    public void visitNamedFunction(FirNamedFunction namedFunction, Void data) {
        namedFunction.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().addElement(namedFunction));
        try {
            FirSession session = getContext().getSession();
            try {
                CheckerContextForProvider context2 = getContext();
                addSuppressedDiagnosticsToContext(namedFunction);
                boolean zIsEmpty = namedFunction.getAnnotations().isEmpty();
                if (!zIsEmpty) {
                    setContext(getContext().addAnnotationContainer(namedFunction));
                }
                try {
                    boolean zIsInline = namedFunction.getStatus().isInline();
                    FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext = this.context.getInlineFunctionBodyContext();
                    FirInlineBodyResolvableExpressionChecker.InlinableParameterContext inlinableParameterContext = this.context.getInlinableParameterContext();
                    if (zIsInline) {
                        try {
                            FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContextCreateInlineFunctionBodyContext = FirInlineDeclarationCheckerKt.createInlineFunctionBodyContext(namedFunction, this.context.getSession(), inlineFunctionBodyContext);
                            this.context = this.context.setInlineFunctionBodyContext(inlineFunctionBodyContextCreateInlineFunctionBodyContext).setInlinableParameterContext(FirInlineBodyResolvableExpressionCheckerKt.createInlinableParameterContext(namedFunction, this.context.getSession()));
                        } catch (Throwable th) {
                            if (zIsInline) {
                                this.context = this.context.setInlinableParameterContext(inlinableParameterContext).setInlineFunctionBodyContext(inlineFunctionBodyContext);
                            }
                            throw th;
                        }
                    }
                    if (shouldVisitDeclaration(namedFunction)) {
                        checkElement(namedFunction);
                        CheckerContextForProvider context3 = getContext();
                        setContext(getContext().addDeclaration(namedFunction));
                        try {
                            FirSession session2 = getContext().getSession();
                            try {
                                visitNestedElements(namedFunction);
                                Unit unit = Unit.INSTANCE;
                                context3.dropDeclaration();
                                setContext(context3);
                                onDeclarationExit(namedFunction);
                            } catch (Throwable th2) {
                                UtilsKt.getExceptionHandler(session2).handleExceptionOnElementAnalysis(namedFunction, th2);
                                throw new KotlinNothingValueException();
                            }
                        } catch (Throwable th3) {
                            context3.dropDeclaration();
                            setContext(context3);
                            throw th3;
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    if (zIsInline) {
                        this.context = this.context.setInlinableParameterContext(inlinableParameterContext).setInlineFunctionBodyContext(inlineFunctionBodyContext);
                    }
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    context.dropElement();
                    setContext(context);
                } catch (Throwable th4) {
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    throw th4;
                }
            } catch (Throwable th5) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(namedFunction, th5);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th6) {
            context.dropElement();
            setContext(context);
            throw th6;
        }
    }

    public void visitNestedElements(FirElement element) {
        element.getClass();
        element.acceptChildren(this, null);
    }

    public void visitProperty(FirProperty property, Void data) {
        property.getClass();
        CheckerContextForProvider context = getContext();
        FirValueParameterSymbol correspondingValueParameterFromPrimaryConstructor = DeclarationAttributesKt.getCorrespondingValueParameterFromPrimaryConstructor(property);
        if (correspondingValueParameterFromPrimaryConstructor != null) {
            FirLazyDeclarationResolverKt.lazyResolveToPhase(correspondingValueParameterFromPrimaryConstructor, FirResolvePhase.ANNOTATION_ARGUMENTS);
            addSuppressedDiagnosticsToContext(correspondingValueParameterFromPrimaryConstructor.getFir());
        }
        FirVariableSymbol<?> destructuringVariableIfEntry = FirDestructuringDeclarationChecker.INSTANCE.getDestructuringVariableIfEntry(property);
        if (destructuringVariableIfEntry != null) {
            FirLazyDeclarationResolverKt.lazyResolveToPhase(destructuringVariableIfEntry, FirResolvePhase.ANNOTATION_ARGUMENTS);
            addSuppressedDiagnosticsToContext(destructuringVariableIfEntry.getFir());
        }
        try {
            CheckerContextForProvider context2 = getContext();
            setContext(getContext().addElement(property));
            try {
                FirSession session = getContext().getSession();
                try {
                    CheckerContextForProvider context3 = getContext();
                    addSuppressedDiagnosticsToContext(property);
                    boolean zIsEmpty = property.getAnnotations().isEmpty();
                    if (!zIsEmpty) {
                        setContext(getContext().addAnnotationContainer(property));
                    }
                    try {
                        if (shouldVisitDeclaration(property)) {
                            checkElement(property);
                            CheckerContextForProvider context4 = getContext();
                            setContext(getContext().addDeclaration(property));
                            try {
                                FirSession session2 = getContext().getSession();
                                try {
                                    visitNestedElements(property);
                                    Unit unit = Unit.INSTANCE;
                                    context4.dropDeclaration();
                                    setContext(context4);
                                    onDeclarationExit(property);
                                } catch (Throwable th) {
                                    UtilsKt.getExceptionHandler(session2).handleExceptionOnElementAnalysis(property, th);
                                    throw new KotlinNothingValueException();
                                }
                            } catch (Throwable th2) {
                                context4.dropDeclaration();
                                setContext(context4);
                                throw th2;
                            }
                        }
                        Unit unit2 = Unit.INSTANCE;
                        if (!zIsEmpty) {
                            context3.dropAnnotationContainer();
                        }
                        setContext(context3);
                        context2.dropElement();
                        setContext(context2);
                        setContext(context);
                    } catch (Throwable th3) {
                        if (!zIsEmpty) {
                            context3.dropAnnotationContainer();
                        }
                        setContext(context3);
                        throw th3;
                    }
                } catch (Throwable th4) {
                    UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(property, th4);
                    throw new KotlinNothingValueException();
                }
            } catch (Throwable th5) {
                context2.dropElement();
                setContext(context2);
                throw th5;
            }
        } catch (Throwable th6) {
            setContext(context);
            throw th6;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitPropertyAccessExpression(FirPropertyAccessExpression firPropertyAccessExpression, Object obj) {
        visitPropertyAccessExpression(firPropertyAccessExpression, (Void) obj);
        return Unit.INSTANCE;
    }

    public void visitPropertyAccessor(FirPropertyAccessor propertyAccessor, Void data) {
        propertyAccessor.getClass();
        Object objLast = CollectionsKt.last(this.context.getContainingDeclarations());
        objLast.getClass();
        FirPropertySymbol firPropertySymbol = (FirPropertySymbol) objLast;
        CheckerContextForProvider context = getContext();
        setContext(getContext().addElement(propertyAccessor));
        try {
            FirSession session = getContext().getSession();
            try {
                CheckerContextForProvider context2 = getContext();
                addSuppressedDiagnosticsToContext(propertyAccessor);
                boolean zIsEmpty = propertyAccessor.getAnnotations().isEmpty();
                if (!zIsEmpty) {
                    setContext(getContext().addAnnotationContainer(propertyAccessor));
                }
                try {
                    boolean z = propertyAccessor.getStatus().isInline() || firPropertySymbol.getRawStatus().isInline();
                    FirInlineDeclarationChecker.InlineFunctionBodyContext inlineFunctionBodyContext = this.context.getInlineFunctionBodyContext();
                    FirInlineBodyResolvableExpressionChecker.InlinableParameterContext inlinableParameterContext = this.context.getInlinableParameterContext();
                    if (z) {
                        try {
                            this.context = this.context.setInlineFunctionBodyContext(FirInlineDeclarationCheckerKt.createInlineFunctionBodyContext(propertyAccessor, this.context.getSession(), inlineFunctionBodyContext)).setInlinableParameterContext(FirInlineBodyResolvableExpressionCheckerKt.createInlinableParameterContext(propertyAccessor, this.context.getSession()));
                        } catch (Throwable th) {
                            if (z) {
                                this.context = this.context.setInlinableParameterContext(inlinableParameterContext).setInlineFunctionBodyContext(inlineFunctionBodyContext);
                            }
                            throw th;
                        }
                    }
                    if (shouldVisitDeclaration(propertyAccessor)) {
                        checkElement(propertyAccessor);
                        CheckerContextForProvider context3 = getContext();
                        setContext(getContext().addDeclaration(propertyAccessor));
                        try {
                            FirSession session2 = getContext().getSession();
                            try {
                                visitNestedElements(propertyAccessor);
                                Unit unit = Unit.INSTANCE;
                                context3.dropDeclaration();
                                setContext(context3);
                                onDeclarationExit(propertyAccessor);
                            } catch (Throwable th2) {
                                UtilsKt.getExceptionHandler(session2).handleExceptionOnElementAnalysis(propertyAccessor, th2);
                                throw new KotlinNothingValueException();
                            }
                        } catch (Throwable th3) {
                            context3.dropDeclaration();
                            setContext(context3);
                            throw th3;
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    if (z) {
                        this.context = this.context.setInlinableParameterContext(inlinableParameterContext).setInlineFunctionBodyContext(inlineFunctionBodyContext);
                    }
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    context.dropElement();
                    setContext(context);
                } catch (Throwable th4) {
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    throw th4;
                }
            } catch (Throwable th5) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(propertyAccessor, th5);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th6) {
            context.dropElement();
            setContext(context);
            throw th6;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitQualifiedAccessExpression(FirQualifiedAccessExpression firQualifiedAccessExpression, Object obj) {
        visitQualifiedAccessExpression(firQualifiedAccessExpression, (Void) obj);
        return Unit.INSTANCE;
    }

    public void visitReceiverParameter(FirReceiverParameter receiverParameter, Void data) {
        receiverParameter.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().addElement(receiverParameter));
        try {
            FirSession session = getContext().getSession();
            try {
                context = getContext();
                addSuppressedDiagnosticsToContext(receiverParameter);
                boolean zIsEmpty = receiverParameter.getAnnotations().isEmpty();
                if (!zIsEmpty) {
                    setContext(getContext().addAnnotationContainer(receiverParameter));
                }
                try {
                    visitNestedElements(receiverParameter);
                    Unit unit = Unit.INSTANCE;
                    if (!zIsEmpty) {
                        context.dropAnnotationContainer();
                    }
                    setContext(context);
                    context.dropElement();
                } finally {
                    if (!zIsEmpty) {
                        context.dropAnnotationContainer();
                    }
                    setContext(context);
                }
            } catch (Throwable th) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(receiverParameter, th);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th2) {
            context.dropElement();
            setContext(context);
            throw th2;
        }
    }

    public void visitRegularClass(FirRegularClass regularClass, Void data) {
        regularClass.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().addElement(regularClass));
        try {
            FirSession session = getContext().getSession();
            try {
                CheckerContextForProvider context2 = getContext();
                addSuppressedDiagnosticsToContext(regularClass);
                boolean zIsEmpty = regularClass.getAnnotations().isEmpty();
                if (!zIsEmpty) {
                    setContext(getContext().addAnnotationContainer(regularClass));
                }
                try {
                    if (shouldVisitDeclaration(regularClass)) {
                        checkElement(regularClass);
                        CheckerContextForProvider context3 = getContext();
                        setContext(getContext().addDeclaration(regularClass));
                        try {
                            FirSession session2 = getContext().getSession();
                            try {
                                visitNestedElements(regularClass);
                                Unit unit = Unit.INSTANCE;
                                context3.dropDeclaration();
                                setContext(context3);
                                onDeclarationExit(regularClass);
                            } catch (Throwable th) {
                                UtilsKt.getExceptionHandler(session2).handleExceptionOnElementAnalysis(regularClass, th);
                                throw new KotlinNothingValueException();
                            }
                        } catch (Throwable th2) {
                            context3.dropDeclaration();
                            setContext(context3);
                            throw th2;
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    context.dropElement();
                    setContext(context);
                } catch (Throwable th3) {
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    throw th3;
                }
            } catch (Throwable th4) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(regularClass, th4);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th5) {
            context.dropElement();
            setContext(context);
            throw th5;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:66:0x003a, code lost:
    
        r2 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void visitResolvedTypeRef(FirResolvedTypeRef resolvedTypeRef, Void data) {
        KtSourceElementKind kind;
        resolvedTypeRef.getClass();
        ConeKotlinType coneType = resolvedTypeRef.getConeType();
        boolean z = coneType instanceof ConeErrorType;
        if (z) {
            visitTypeRef((FirTypeRef) resolvedTypeRef, data);
        }
        KtSourceElement source = resolvedTypeRef.getSource();
        if ((source != null && (kind = source.getKind()) != null && kind.getShouldSkipErrorTypeReporting()) || (resolvedTypeRef instanceof FirImplicitBuiltinTypeRef)) {
            return;
        }
        Object objLastOrNull = CollectionsKt.lastOrNull(this.context.getAnnotationContainers());
        if (objLastOrNull instanceof FirResolvedTypeRef) {
            FirResolvedTypeRef firResolvedTypeRef = (FirResolvedTypeRef) objLastOrNull;
            while (firResolvedTypeRef != null && !Intrinsics.areEqual(firResolvedTypeRef.getDelegatedTypeRef(), resolvedTypeRef)) {
                FirTypeRef delegatedTypeRef = firResolvedTypeRef.getDelegatedTypeRef();
                firResolvedTypeRef = delegatedTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) delegatedTypeRef : null;
            }
            if (firResolvedTypeRef != null) {
                if (!z) {
                    checkElement(resolvedTypeRef);
                }
                FirTypeRef delegatedTypeRef2 = resolvedTypeRef.getDelegatedTypeRef();
                if (delegatedTypeRef2 != null) {
                    delegatedTypeRef2.accept(this, data);
                    return;
                }
                return;
            }
            CheckerContextForProvider context = getContext();
            setContext(getContext().addElement(resolvedTypeRef));
            try {
                FirSession session = getContext().getSession();
                try {
                    context = getContext();
                    addSuppressedDiagnosticsToContext(resolvedTypeRef);
                    boolean zIsEmpty = resolvedTypeRef.getAnnotations().isEmpty();
                    if (!zIsEmpty) {
                        setContext(getContext().addAnnotationContainer(resolvedTypeRef));
                    }
                    try {
                        if (!(coneType instanceof ConeErrorType)) {
                            checkElement(resolvedTypeRef);
                        }
                        FirTypeRef delegatedTypeRef3 = resolvedTypeRef.getDelegatedTypeRef();
                        if (delegatedTypeRef3 != null) {
                            delegatedTypeRef3.accept(this, data);
                            Unit unit = Unit.INSTANCE;
                        }
                        if (!zIsEmpty) {
                            context.dropAnnotationContainer();
                        }
                        setContext(context);
                        context.dropElement();
                    } finally {
                        if (!zIsEmpty) {
                            context.dropAnnotationContainer();
                        }
                        setContext(context);
                    }
                } catch (Throwable th) {
                    UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(resolvedTypeRef, th);
                    throw new KotlinNothingValueException();
                }
            } catch (Throwable th2) {
                context.dropElement();
                setContext(context);
                throw th2;
            }
        }
    }

    public void visitScript(FirScript script, Void data) {
        script.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().addElement(script));
        try {
            FirSession session = getContext().getSession();
            try {
                CheckerContextForProvider context2 = getContext();
                addSuppressedDiagnosticsToContext(script);
                boolean zIsEmpty = script.getAnnotations().isEmpty();
                if (!zIsEmpty) {
                    setContext(getContext().addAnnotationContainer(script));
                }
                try {
                    if (shouldVisitDeclaration(script)) {
                        checkElement(script);
                        CheckerContextForProvider context3 = getContext();
                        setContext(getContext().addDeclaration(script));
                        try {
                            FirSession session2 = getContext().getSession();
                            try {
                                visitNestedElements(script);
                                Unit unit = Unit.INSTANCE;
                                context3.dropDeclaration();
                                setContext(context3);
                                onDeclarationExit(script);
                            } catch (Throwable th) {
                                UtilsKt.getExceptionHandler(session2).handleExceptionOnElementAnalysis(script, th);
                                throw new KotlinNothingValueException();
                            }
                        } catch (Throwable th2) {
                            context3.dropDeclaration();
                            setContext(context3);
                            throw th2;
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    context.dropElement();
                    setContext(context);
                } catch (Throwable th3) {
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    throw th3;
                }
            } catch (Throwable th4) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(script, th4);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th5) {
            context.dropElement();
            setContext(context);
            throw th5;
        }
    }

    public void visitTypeAlias(FirTypeAlias typeAlias, Void data) {
        typeAlias.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().addElement(typeAlias));
        try {
            FirSession session = getContext().getSession();
            try {
                CheckerContextForProvider context2 = getContext();
                addSuppressedDiagnosticsToContext(typeAlias);
                boolean zIsEmpty = typeAlias.getAnnotations().isEmpty();
                if (!zIsEmpty) {
                    setContext(getContext().addAnnotationContainer(typeAlias));
                }
                try {
                    if (shouldVisitDeclaration(typeAlias)) {
                        checkElement(typeAlias);
                        CheckerContextForProvider context3 = getContext();
                        setContext(getContext().addDeclaration(typeAlias));
                        try {
                            FirSession session2 = getContext().getSession();
                            try {
                                visitNestedElements(typeAlias);
                                Unit unit = Unit.INSTANCE;
                                context3.dropDeclaration();
                                setContext(context3);
                                onDeclarationExit(typeAlias);
                            } catch (Throwable th) {
                                UtilsKt.getExceptionHandler(session2).handleExceptionOnElementAnalysis(typeAlias, th);
                                throw new KotlinNothingValueException();
                            }
                        } catch (Throwable th2) {
                            context3.dropDeclaration();
                            setContext(context3);
                            throw th2;
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    context.dropElement();
                    setContext(context);
                } catch (Throwable th3) {
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    throw th3;
                }
            } catch (Throwable th4) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(typeAlias, th4);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th5) {
            context.dropElement();
            setContext(context);
            throw th5;
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:50:0x0028, code lost:
    
        r6 = null;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void visitTypeRef(FirTypeRef typeRef, Void data) {
        KtSourceElementKind kind;
        typeRef.getClass();
        KtSourceElement source = typeRef.getSource();
        if (source == null || (kind = source.getKind()) == null || kind.getShouldSkipErrorTypeReporting()) {
            return;
        }
        Object objLastOrNull = CollectionsKt.lastOrNull(this.context.getAnnotationContainers());
        if (objLastOrNull instanceof FirResolvedTypeRef) {
            FirResolvedTypeRef firResolvedTypeRef = (FirResolvedTypeRef) objLastOrNull;
            while (firResolvedTypeRef != null && !Intrinsics.areEqual(firResolvedTypeRef.getDelegatedTypeRef(), typeRef)) {
                FirTypeRef delegatedTypeRef = firResolvedTypeRef.getDelegatedTypeRef();
                firResolvedTypeRef = delegatedTypeRef instanceof FirResolvedTypeRef ? (FirResolvedTypeRef) delegatedTypeRef : null;
            }
            if (firResolvedTypeRef != null) {
                checkElement(typeRef);
                visitNestedElements(typeRef);
                return;
            }
            CheckerContextForProvider context = getContext();
            setContext(getContext().addElement(typeRef));
            try {
                FirSession session = getContext().getSession();
                try {
                    context = getContext();
                    addSuppressedDiagnosticsToContext(typeRef);
                    boolean zIsEmpty = typeRef.getAnnotations().isEmpty();
                    if (!zIsEmpty) {
                        setContext(getContext().addAnnotationContainer(typeRef));
                    }
                    try {
                        checkElement(typeRef);
                        visitNestedElements(typeRef);
                        Unit unit = Unit.INSTANCE;
                        if (!zIsEmpty) {
                            context.dropAnnotationContainer();
                        }
                        setContext(context);
                        context.dropElement();
                    } finally {
                        if (!zIsEmpty) {
                            context.dropAnnotationContainer();
                        }
                        setContext(context);
                    }
                } catch (Throwable th) {
                    UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(typeRef, th);
                    throw new KotlinNothingValueException();
                }
            } catch (Throwable th2) {
                context.dropElement();
                setContext(context);
                throw th2;
            }
        }
    }

    public void visitValueParameter(FirValueParameter valueParameter, Void data) {
        valueParameter.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().addElement(valueParameter));
        try {
            FirSession session = getContext().getSession();
            try {
                CheckerContextForProvider context2 = getContext();
                addSuppressedDiagnosticsToContext(valueParameter);
                boolean zIsEmpty = valueParameter.getAnnotations().isEmpty();
                if (!zIsEmpty) {
                    setContext(getContext().addAnnotationContainer(valueParameter));
                }
                try {
                    if (shouldVisitDeclaration(valueParameter)) {
                        checkElement(valueParameter);
                        CheckerContextForProvider context3 = getContext();
                        setContext(getContext().addDeclaration(valueParameter));
                        try {
                            FirSession session2 = getContext().getSession();
                            try {
                                visitNestedElements(valueParameter);
                                Unit unit = Unit.INSTANCE;
                                context3.dropDeclaration();
                                setContext(context3);
                                onDeclarationExit(valueParameter);
                            } catch (Throwable th) {
                                UtilsKt.getExceptionHandler(session2).handleExceptionOnElementAnalysis(valueParameter, th);
                                throw new KotlinNothingValueException();
                            }
                        } catch (Throwable th2) {
                            context3.dropDeclaration();
                            setContext(context3);
                            throw th2;
                        }
                    }
                    Unit unit2 = Unit.INSTANCE;
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    context.dropElement();
                    setContext(context);
                } catch (Throwable th3) {
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    throw th3;
                }
            } catch (Throwable th4) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(valueParameter, th4);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th5) {
            context.dropElement();
            setContext(context);
            throw th5;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitVariableAssignment(FirVariableAssignment firVariableAssignment, Object obj) {
        visitVariableAssignment(firVariableAssignment, (Void) obj);
        return Unit.INSTANCE;
    }

    public final void visitWithDeclaration(FirDeclaration declaration, Function0<Unit> block) {
        declaration.getClass();
        block.getClass();
        if (shouldVisitDeclaration(declaration)) {
            checkElement(declaration);
            CheckerContextForProvider context = getContext();
            setContext(getContext().addDeclaration(declaration));
            try {
                FirSession session = getContext().getSession();
                try {
                    block.invoke();
                    Unit unit = Unit.INSTANCE;
                    InlineMarker.finallyStart(1);
                    context.dropDeclaration();
                    setContext(context);
                    InlineMarker.finallyEnd(1);
                    onDeclarationExit(declaration);
                } catch (Throwable th) {
                    UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(declaration, th);
                    throw new KotlinNothingValueException();
                }
            } catch (Throwable th2) {
                InlineMarker.finallyStart(1);
                context.dropDeclaration();
                setContext(context);
                InlineMarker.finallyEnd(1);
                throw th2;
            }
        }
    }

    public final void visitWithFile(FirFile file, Function0<Unit> block) {
        file.getClass();
        block.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().enterFile(file));
        try {
            if (shouldVisitDeclaration(file)) {
                checkElement(file);
                CheckerContextForProvider context2 = getContext();
                setContext(getContext().addDeclaration(file));
                try {
                    FirSession session = getContext().getSession();
                    try {
                        block.invoke();
                        Unit unit = Unit.INSTANCE;
                        InlineMarker.finallyStart(1);
                        context2.dropDeclaration();
                        setContext(context2);
                        InlineMarker.finallyEnd(1);
                        onDeclarationExit(file);
                    } catch (Throwable th) {
                        UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(file, th);
                        throw new KotlinNothingValueException();
                    }
                } catch (Throwable th2) {
                    InlineMarker.finallyStart(1);
                    context2.dropDeclaration();
                    setContext(context2);
                    InlineMarker.finallyEnd(1);
                    throw th2;
                }
            }
            Unit unit2 = Unit.INSTANCE;
            InlineMarker.finallyStart(1);
            context.exitFile(file);
            setContext(context);
            InlineMarker.finallyEnd(1);
        } catch (Throwable th3) {
            InlineMarker.finallyStart(1);
            context.exitFile(file);
            setContext(context);
            InlineMarker.finallyEnd(1);
            throw th3;
        }
    }

    public final <R> R withAdditionalSuppresses(FirProperty property, Function0<? extends R> block) {
        property.getClass();
        block.getClass();
        CheckerContextForProvider context = getContext();
        FirValueParameterSymbol correspondingValueParameterFromPrimaryConstructor = DeclarationAttributesKt.getCorrespondingValueParameterFromPrimaryConstructor(property);
        if (correspondingValueParameterFromPrimaryConstructor != null) {
            FirLazyDeclarationResolverKt.lazyResolveToPhase(correspondingValueParameterFromPrimaryConstructor, FirResolvePhase.ANNOTATION_ARGUMENTS);
            addSuppressedDiagnosticsToContext(correspondingValueParameterFromPrimaryConstructor.getFir());
        }
        FirVariableSymbol<?> destructuringVariableIfEntry = FirDestructuringDeclarationChecker.INSTANCE.getDestructuringVariableIfEntry(property);
        if (destructuringVariableIfEntry != null) {
            FirLazyDeclarationResolverKt.lazyResolveToPhase(destructuringVariableIfEntry, FirResolvePhase.ANNOTATION_ARGUMENTS);
            addSuppressedDiagnosticsToContext(destructuringVariableIfEntry.getFir());
        }
        try {
            return (R) block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            setContext(context);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <R> R withAnnotationContainer(FirAnnotationContainer annotationContainer, Function0<? extends R> block) {
        annotationContainer.getClass();
        block.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().addElement(annotationContainer));
        try {
            FirSession session = getContext().getSession();
            try {
                CheckerContextForProvider context2 = getContext();
                addSuppressedDiagnosticsToContext(annotationContainer);
                boolean zIsEmpty = annotationContainer.getAnnotations().isEmpty();
                if (!zIsEmpty) {
                    setContext(getContext().addAnnotationContainer(annotationContainer));
                }
                try {
                    R r = (R) block.invoke();
                    InlineMarker.finallyStart(1);
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    InlineMarker.finallyEnd(1);
                    InlineMarker.finallyStart(1);
                    context.dropElement();
                    return r;
                } finally {
                    InlineMarker.finallyStart(1);
                    if (!zIsEmpty) {
                        context2.dropAnnotationContainer();
                    }
                    setContext(context2);
                    InlineMarker.finallyEnd(1);
                }
            } catch (Throwable th) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(annotationContainer, th);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            context.dropElement();
            setContext(context);
            InlineMarker.finallyEnd(1);
            throw th2;
        }
    }

    public final <R> R withCallOrAssignment(FirStatement callOrAssignment, Function0<? extends R> block) {
        callOrAssignment.getClass();
        block.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().addCallOrAssignment(callOrAssignment));
        try {
            FirSession session = getContext().getSession();
            try {
                R r = (R) block.invoke();
                InlineMarker.finallyStart(1);
                context.dropCallOrAssignment();
                setContext(context);
                InlineMarker.finallyEnd(1);
                return r;
            } catch (Throwable th) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(callOrAssignment, th);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            context.dropCallOrAssignment();
            setContext(context);
            InlineMarker.finallyEnd(1);
            throw th2;
        }
    }

    public final <R> R withDeclaration(FirDeclaration declaration, Function0<? extends R> block) {
        declaration.getClass();
        block.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().addDeclaration(declaration));
        try {
            FirSession session = getContext().getSession();
            try {
                R r = (R) block.invoke();
                InlineMarker.finallyStart(1);
                context.dropDeclaration();
                setContext(context);
                InlineMarker.finallyEnd(1);
                return r;
            } catch (Throwable th) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(declaration, th);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            context.dropDeclaration();
            setContext(context);
            InlineMarker.finallyEnd(1);
            throw th2;
        }
    }

    public final <T> T withElement(FirElement element, Function0<? extends T> block) {
        element.getClass();
        block.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().addElement(element));
        try {
            FirSession session = getContext().getSession();
            try {
                T t = (T) block.invoke();
                InlineMarker.finallyStart(1);
                context.dropElement();
                setContext(context);
                InlineMarker.finallyEnd(1);
                return t;
            } catch (Throwable th) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(element, th);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            context.dropElement();
            setContext(context);
            InlineMarker.finallyEnd(1);
            throw th2;
        }
    }

    public final <R> R withFile(FirFile file, Function0<? extends R> block) {
        file.getClass();
        block.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().enterFile(file));
        try {
            return (R) block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            context.exitFile(file);
            setContext(context);
            InlineMarker.finallyEnd(1);
        }
    }

    public final <R> R withGetClassCall(FirGetClassCall getClassCall, Function0<? extends R> block) {
        getClassCall.getClass();
        block.getClass();
        CheckerContextForProvider context = getContext();
        setContext(getContext().addGetClassCall(getClassCall));
        try {
            FirSession session = getContext().getSession();
            try {
                R r = (R) block.invoke();
                InlineMarker.finallyStart(1);
                context.dropGetClassCall();
                setContext(context);
                InlineMarker.finallyEnd(1);
                return r;
            } catch (Throwable th) {
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(getClassCall, th);
                throw new KotlinNothingValueException();
            }
        } catch (Throwable th2) {
            InlineMarker.finallyStart(1);
            context.dropGetClassCall();
            setContext(context);
            InlineMarker.finallyEnd(1);
            throw th2;
        }
    }

    public void visitBreakExpression(FirBreakExpression breakExpression, Void data) {
        breakExpression.getClass();
        visitJump(breakExpression);
    }

    public void visitContinueExpression(FirContinueExpression continueExpression, Void data) {
        continueExpression.getClass();
        visitJump(continueExpression);
    }

    public void visitDelegatedConstructorCall(FirDelegatedConstructorCall delegatedConstructorCall, Void data) {
        delegatedConstructorCall.getClass();
        visitWithCallOrAssignment(delegatedConstructorCall);
    }

    public void visitErrorPrimaryConstructor(FirErrorPrimaryConstructor errorPrimaryConstructor, Void data) {
        errorPrimaryConstructor.getClass();
        visitConstructor((FirConstructor) errorPrimaryConstructor, data);
    }

    public void visitErrorTypeRef(FirErrorTypeRef errorTypeRef, Void data) {
        errorTypeRef.getClass();
        visitResolvedTypeRef((FirResolvedTypeRef) errorTypeRef, data);
    }

    public void visitFunctionCall(FirFunctionCall functionCall, Void data) {
        functionCall.getClass();
        visitWithCallOrAssignment(functionCall);
    }

    public void visitGetClassCall(FirGetClassCall getClassCall, Void data) {
        getClassCall.getClass();
        visitWithGetClassCall(getClassCall);
    }

    public void visitPropertyAccessExpression(FirPropertyAccessExpression propertyAccessExpression, Void data) {
        propertyAccessExpression.getClass();
        visitWithCallOrAssignment(propertyAccessExpression);
    }

    public void visitQualifiedAccessExpression(FirQualifiedAccessExpression qualifiedAccessExpression, Void data) {
        qualifiedAccessExpression.getClass();
        visitWithCallOrAssignment(qualifiedAccessExpression);
    }

    public void visitVariableAssignment(FirVariableAssignment variableAssignment, Void data) {
        variableAssignment.getClass();
        visitWithCallOrAssignment(variableAssignment);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitLazyBlock(FirLazyBlock firLazyBlock, Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitLazyBlock(firLazyBlock, (Void) obj);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitLazyContractDescription(FirLazyContractDescription firLazyContractDescription, Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitLazyContractDescription(firLazyContractDescription, (Void) obj);
        return Unit.INSTANCE;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitLazyExpression(FirLazyExpression firLazyExpression, Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
        visitLazyExpression(firLazyExpression, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitAnonymousObjectExpression(FirAnonymousObjectExpression firAnonymousObjectExpression, Object obj) {
        visitAnonymousObjectExpression(firAnonymousObjectExpression, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitBlock(FirBlock firBlock, Object obj) {
        visitBlock(firBlock, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitAnnotationCall(FirAnnotationCall firAnnotationCall, Object obj) {
        visitAnnotationCall(firAnnotationCall, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitContractDescription(FirContractDescription firContractDescription, Object obj) {
        visitContractDescription(firContractDescription, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitAnonymousFunctionExpression(FirAnonymousFunctionExpression firAnonymousFunctionExpression, Object obj) {
        visitAnonymousFunctionExpression(firAnonymousFunctionExpression, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitReceiverParameter(FirReceiverParameter firReceiverParameter, Object obj) {
        visitReceiverParameter(firReceiverParameter, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitAnnotationContainer(FirAnnotationContainer firAnnotationContainer, Object obj) {
        visitAnnotationContainer(firAnnotationContainer, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitAnonymousInitializer(FirAnonymousInitializer firAnonymousInitializer, Object obj) {
        visitAnonymousInitializer(firAnonymousInitializer, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitAnonymousObject(FirAnonymousObject firAnonymousObject, Object obj) {
        visitAnonymousObject(firAnonymousObject, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitConstructor(FirConstructor firConstructor, Object obj) {
        visitConstructor(firConstructor, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitDanglingModifierList(FirDanglingModifierList firDanglingModifierList, Object obj) {
        visitDanglingModifierList(firDanglingModifierList, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitEnumEntry(FirEnumEntry firEnumEntry, Object obj) {
        visitEnumEntry(firEnumEntry, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitErrorProperty(FirErrorProperty firErrorProperty, Object obj) {
        visitErrorProperty(firErrorProperty, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitRegularClass(FirRegularClass firRegularClass, Object obj) {
        visitRegularClass(firRegularClass, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitScript(FirScript firScript, Object obj) {
        visitScript(firScript, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitTypeAlias(FirTypeAlias firTypeAlias, Object obj) {
        visitTypeAlias(firTypeAlias, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitValueParameter(FirValueParameter firValueParameter, Object obj) {
        visitValueParameter(firValueParameter, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitElement(FirElement firElement, Object obj) {
        visitElement(firElement, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitTypeRef(FirTypeRef firTypeRef, Object obj) {
        visitTypeRef(firTypeRef, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitFile(FirFile firFile, Object obj) {
        visitFile(firFile, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitResolvedTypeRef(FirResolvedTypeRef firResolvedTypeRef, Object obj) {
        visitResolvedTypeRef(firResolvedTypeRef, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitProperty(FirProperty firProperty, Object obj) {
        visitProperty(firProperty, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitAnonymousFunction(FirAnonymousFunction firAnonymousFunction, Object obj) {
        visitAnonymousFunction(firAnonymousFunction, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitNamedFunction(FirNamedFunction firNamedFunction, Object obj) {
        visitNamedFunction(firNamedFunction, (Void) obj);
        return Unit.INSTANCE;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public /* bridge */ /* synthetic */ Object visitPropertyAccessor(FirPropertyAccessor firPropertyAccessor, Object obj) {
        visitPropertyAccessor(firPropertyAccessor, (Void) obj);
        return Unit.INSTANCE;
    }
}
