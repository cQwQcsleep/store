package org.jetbrains.kotlin.fir.resolve.transformers.body.resolve;

import defpackage.ia5;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirTargetElement;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDanglingModifierList;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirErrorPrimaryConstructor;
import org.jetbrains.kotlin.fir.declarations.FirErrorProperty;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousObjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirAugmentedAssignment;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirBooleanOperatorExpression;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCatch;
import org.jetbrains.kotlin.fir.expressions.FirCheckNotNullCall;
import org.jetbrains.kotlin.fir.expressions.FirCheckedSafeCallSubject;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirComparisonExpression;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirDoWhileLoop;
import org.jetbrains.kotlin.fir.expressions.FirElvisExpression;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirErrorAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirIncrementDecrementExpression;
import org.jetbrains.kotlin.fir.expressions.FirIndexedAccessAugmentedAssignment;
import org.jetbrains.kotlin.fir.expressions.FirJump;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedErrorAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirReplDeclarationReference;
import org.jetbrains.kotlin.fir.expressions.FirReplExpressionReference;
import org.jetbrains.kotlin.fir.expressions.FirReplPropertyDelegate;
import org.jetbrains.kotlin.fir.expressions.FirReplPropertyInitializer;
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
import org.jetbrains.kotlin.fir.expressions.FirWhenBranch;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhileLoop;
import org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirWrappedDelegateExpression;
import org.jetbrains.kotlin.fir.resolve.BodyResolveComponentsKt;
import org.jetbrains.kotlin.fir.resolve.ResolutionMode;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.TypeResolutionConfiguration;
import org.jetbrains.kotlin.fir.resolve.calls.ResolutionContext;
import org.jetbrains.kotlin.fir.resolve.dfa.DataFlowAnalyzerContext;
import org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculator;
import org.jetbrains.kotlin.fir.resolve.transformers.ReturnTypeCalculatorForFullBodyResolve;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.types.ConeDefinitelyNotNullType;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeKotlinTypeProjection;
import org.jetbrains.kotlin.fir.types.CustomAnnotationTypeAttributeKt;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirTransformer;
import org.jetbrains.kotlin.fir.visitors.FirTransformerUtilKt;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000à\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0014\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001BE\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r\u0012\u0006\u0010\u000e\u001a\u00020\u0007¢\u0006\u0004\b\u000f\u0010\u0010J\u0018\u00103\u001a\u0002042\u0006\u00105\u001a\u0002042\u0006\u00106\u001a\u000207H\u0016J\u0018\u00108\u001a\u0002092\u0006\u0010:\u001a\u0002092\u0006\u00106\u001a\u000207H\u0016J\u0018\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<2\u0006\u00106\u001a\u000207H\u0016J\u0018\u0010>\u001a\u00020?2\u0006\u0010@\u001a\u00020?2\u0006\u00106\u001a\u000207H\u0016J'\u0010A\u001a\u0002HB\"\b\b\u0000\u0010B*\u00020C2\u0006\u0010D\u001a\u0002HB2\u0006\u00106\u001a\u000207H\u0016¢\u0006\u0002\u0010EJ\u0018\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020I2\u0006\u00106\u001a\u000207H\u0016J\u0018\u0010J\u001a\u00020I2\u0006\u0010K\u001a\u00020L2\u0006\u00106\u001a\u000207H\u0016JS\u0010M\u001a\u0002HN\"\b\b\u0000\u0010O*\u0002HN\"\u0004\b\u0001\u0010N2\u0006\u0010P\u001a\u0002HO2\u0006\u00106\u001a\u0002072#\u0010Q\u001a\u001f\u0012\u0004\u0012\u00020*\u0012\u0004\u0012\u0002HO\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u0002HN0R¢\u0006\u0002\bSH\u0082\b¢\u0006\u0002\u0010TJ\u0018\u0010U\u001a\u00020V2\u0006\u0010P\u001a\u00020W2\u0006\u00106\u001a\u000207H\u0016J\u0018\u0010X\u001a\u00020V2\u0006\u0010Y\u001a\u00020Z2\u0006\u00106\u001a\u000207H\u0016J\u0018\u0010[\u001a\u00020V2\u0006\u0010\\\u001a\u00020]2\u0006\u00106\u001a\u000207H\u0016J\u0018\u0010^\u001a\u00020V2\u0006\u0010_\u001a\u00020`2\u0006\u00106\u001a\u000207H\u0016J\u0018\u0010a\u001a\u00020V2\u0006\u0010b\u001a\u00020c2\u0006\u00106\u001a\u000207H\u0016J\u0018\u0010d\u001a\u00020V2\u0006\u0010e\u001a\u00020f2\u0006\u00106\u001a\u000207H\u0016J\u0018\u0010g\u001a\u00020V2\u0006\u0010h\u001a\u00020i2\u0006\u00106\u001a\u000207H\u0016J\u0018\u0010j\u001a\u00020V2\u0006\u0010k\u001a\u00020l2\u0006\u00106\u001a\u000207H\u0016J\u0018\u0010m\u001a\u00020V2\u0006\u0010n\u001a\u00020o2\u0006\u00106\u001a\u000207H\u0016J\u0018\u0010p\u001a\u00020V2\u0006\u0010q\u001a\u00020r2\u0006\u00106\u001a\u000207H\u0016J\u0018\u0010s\u001a\u00020V2\u0006\u0010t\u001a\u00020u2\u0006\u00106\u001a\u000207H\u0016J\u0018\u0010v\u001a\u00020V2\u0006\u0010w\u001a\u00020x2\u0006\u00106\u001a\u000207H\u0016J\u0018\u0010y\u001a\u00020V2\u0006\u0010z\u001a\u00020{2\u0006\u00106\u001a\u000207H\u0016J\u0018\u0010|\u001a\u00020V2\u0006\u0010}\u001a\u00020~2\u0006\u00106\u001a\u000207H\u0016J\u001a\u0010\u007f\u001a\u00020V2\b\u0010\u0080\u0001\u001a\u00030\u0081\u00012\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010\u0082\u0001\u001a\u00020V2\b\u0010\u0083\u0001\u001a\u00030\u0084\u00012\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010\u0085\u0001\u001a\u00020V2\b\u0010\u0086\u0001\u001a\u00030\u0087\u00012\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010\u0088\u0001\u001a\u00020V2\b\u0010\u0089\u0001\u001a\u00030\u008a\u00012\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010\u008b\u0001\u001a\u00020V2\b\u0010\u008c\u0001\u001a\u00030\u008d\u00012\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010\u008e\u0001\u001a\u00020V2\b\u0010\u008f\u0001\u001a\u00030\u0090\u00012\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010\u0091\u0001\u001a\u00020V2\b\u0010\u0092\u0001\u001a\u00030\u0093\u00012\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010\u0094\u0001\u001a\u00020V2\b\u0010\u0095\u0001\u001a\u00030\u0096\u00012\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010\u0097\u0001\u001a\u00020V2\b\u0010\u0098\u0001\u001a\u00030\u0099\u00012\u0006\u00106\u001a\u000207H\u0016J\"\u0010\u009a\u0001\u001a\u00030\u009b\u00012\f\u0010\u009c\u0001\u001a\u0007\u0012\u0002\b\u00030\u009d\u00012\b\u0010\u009e\u0001\u001a\u00030\u009b\u0001H\u0016J\u001b\u0010\u009f\u0001\u001a\u00020V2\b\u0010\u009e\u0001\u001a\u00030\u009b\u00012\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010 \u0001\u001a\u00020V2\b\u0010¡\u0001\u001a\u00030¢\u00012\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010£\u0001\u001a\u00020V2\b\u0010¤\u0001\u001a\u00030¥\u00012\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010¦\u0001\u001a\u00020V2\b\u0010§\u0001\u001a\u00030¨\u00012\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010©\u0001\u001a\u00020V2\b\u0010ª\u0001\u001a\u00030«\u00012\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010¬\u0001\u001a\u00020V2\b\u0010\u00ad\u0001\u001a\u00030®\u00012\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010¯\u0001\u001a\u00020V2\b\u0010°\u0001\u001a\u00030±\u00012\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010²\u0001\u001a\u00020V2\b\u0010³\u0001\u001a\u00030´\u00012\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010µ\u0001\u001a\u00020V2\b\u0010¶\u0001\u001a\u00030·\u00012\u0006\u00106\u001a\u000207H\u0016JZ\u0010¸\u0001\u001a\u0002HN\"\b\b\u0000\u0010O*\u0002HN\"\b\b\u0001\u0010N*\u00020C2\u0007\u0010¹\u0001\u001a\u0002HO2\u0006\u00106\u001a\u0002072#\u0010Q\u001a\u001f\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u0002HO\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u0002HN0R¢\u0006\u0002\bSH\u0082\b¢\u0006\u0003\u0010º\u0001J\u001c\u0010»\u0001\u001a\u00030¼\u00012\b\u0010¹\u0001\u001a\u00030¼\u00012\u0006\u00106\u001a\u000207H\u0016J\u001c\u0010½\u0001\u001a\u00030¼\u00012\b\u0010¹\u0001\u001a\u00030¼\u00012\u0006\u00106\u001a\u000207H\u0016J\u001c\u0010¾\u0001\u001a\u00030¿\u00012\b\u0010À\u0001\u001a\u00030¿\u00012\u0006\u00106\u001a\u000207H\u0016J\u001c\u0010Á\u0001\u001a\u00030Â\u00012\b\u0010Ã\u0001\u001a\u00030Â\u00012\u0006\u00106\u001a\u000207H\u0016J\u001c\u0010Ä\u0001\u001a\u00030Å\u00012\b\u0010Æ\u0001\u001a\u00030Å\u00012\u0006\u00106\u001a\u000207H\u0016J\u001c\u0010Ç\u0001\u001a\u00030È\u00012\b\u0010É\u0001\u001a\u00030È\u00012\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010Ê\u0001\u001a\u00020V2\b\u0010Ë\u0001\u001a\u00030Ì\u00012\u0006\u00106\u001a\u000207H\u0016J\u001c\u0010Í\u0001\u001a\u00030Î\u00012\b\u0010Ï\u0001\u001a\u00030Î\u00012\u0006\u00106\u001a\u000207H\u0016J\u001c\u0010Ð\u0001\u001a\u00030Ñ\u00012\b\u0010Ò\u0001\u001a\u00030Ñ\u00012\u0006\u00106\u001a\u000207H\u0016J\u001c\u0010Ó\u0001\u001a\u00030Ô\u00012\b\u0010Õ\u0001\u001a\u00030Ô\u00012\u0006\u00106\u001a\u000207H\u0016J\u001c\u0010Ö\u0001\u001a\u00030×\u00012\b\u0010Ø\u0001\u001a\u00030×\u00012\u0006\u00106\u001a\u000207H\u0016J\u001c\u0010Ù\u0001\u001a\u00030Ú\u00012\b\u0010Û\u0001\u001a\u00030Ú\u00012\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010Ü\u0001\u001a\u00020V2\b\u0010Ý\u0001\u001a\u00030Þ\u00012\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010ß\u0001\u001a\u00020V2\b\u0010à\u0001\u001a\u00030á\u00012\u0006\u00106\u001a\u000207H\u0016J\u001c\u0010â\u0001\u001a\u00030ã\u00012\b\u0010ä\u0001\u001a\u00030ã\u00012\u0006\u00106\u001a\u000207H\u0016J\u001c\u0010å\u0001\u001a\u00030æ\u00012\b\u0010ç\u0001\u001a\u00030æ\u00012\u0006\u00106\u001a\u000207H\u0016J\u001c\u0010è\u0001\u001a\u00030é\u00012\b\u0010ê\u0001\u001a\u00030é\u00012\u0006\u00106\u001a\u000207H\u0016J\u001c\u0010ë\u0001\u001a\u00030ì\u00012\b\u0010í\u0001\u001a\u00030ì\u00012\u0006\u00106\u001a\u000207H\u0016J\u001c\u0010î\u0001\u001a\u00030ï\u00012\b\u0010ð\u0001\u001a\u00030ï\u00012\u0006\u00106\u001a\u000207H\u0016J\u001c\u0010ñ\u0001\u001a\u00030ò\u00012\b\u0010ó\u0001\u001a\u00030ò\u00012\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010ô\u0001\u001a\u00020V2\b\u0010õ\u0001\u001a\u00030ö\u00012\u0006\u00106\u001a\u000207H\u0016J\u001c\u0010÷\u0001\u001a\u00030ø\u00012\b\u0010ù\u0001\u001a\u00030ø\u00012\u0006\u00106\u001a\u000207H\u0016J\u001c\u0010ú\u0001\u001a\u00030û\u00012\b\u0010ü\u0001\u001a\u00030û\u00012\u0006\u00106\u001a\u000207H\u0016JQ\u0010ý\u0001\u001a\u0002HN\"\u0004\b\u0000\u0010O\"\u0004\b\u0001\u0010N2\u0007\u0010¹\u0001\u001a\u0002HO2\u0006\u00106\u001a\u0002072#\u0010Q\u001a\u001f\u0012\u0004\u0012\u000202\u0012\u0004\u0012\u0002HO\u0012\u0004\u0012\u000207\u0012\u0004\u0012\u0002HN0R¢\u0006\u0002\bSH\u0082\b¢\u0006\u0002\u0010TJ\u001b\u0010þ\u0001\u001a\u00020V2\b\u0010ÿ\u0001\u001a\u00030\u0080\u00022\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010\u0081\u0002\u001a\u00020V2\b\u0010\u0082\u0002\u001a\u00030\u0083\u00022\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010\u0084\u0002\u001a\u00020V2\b\u0010\u0085\u0002\u001a\u00030\u0086\u00022\u0006\u00106\u001a\u000207H\u0016J\u001c\u0010\u0087\u0002\u001a\u00030\u0088\u00022\b\u0010\u0089\u0002\u001a\u00030\u0088\u00022\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010\u008a\u0002\u001a\u00020V2\b\u0010\u008b\u0002\u001a\u00030\u008c\u00022\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010\u008d\u0002\u001a\u00020V2\b\u0010\u008e\u0002\u001a\u00030\u008f\u00022\u0006\u00106\u001a\u000207H\u0016J\u001c\u0010\u0090\u0002\u001a\u00030\u0091\u00022\b\u0010\u0092\u0002\u001a\u00030\u0091\u00022\u0006\u00106\u001a\u000207H\u0016J,\u0010\u0093\u0002\u001a\u00020V\"\t\b\u0000\u0010B*\u00030\u0094\u00022\u000e\u0010\u0095\u0002\u001a\t\u0012\u0004\u0012\u0002HB0\u0096\u00022\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010\u0097\u0002\u001a\u00020V2\b\u0010\u0098\u0002\u001a\u00030\u0099\u00022\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010\u009a\u0002\u001a\u00020V2\b\u0010\u009b\u0002\u001a\u00030\u009c\u00022\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010\u009d\u0002\u001a\u00020V2\b\u0010\u009e\u0002\u001a\u00030\u009f\u00022\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010 \u0002\u001a\u00020V2\b\u0010¡\u0002\u001a\u00030¢\u00022\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010£\u0002\u001a\u00020V2\b\u0010¤\u0002\u001a\u00030¥\u00022\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010¦\u0002\u001a\u00020V2\b\u0010§\u0002\u001a\u00030¨\u00022\u0006\u00106\u001a\u000207H\u0016J\u001b\u0010©\u0002\u001a\u00020V2\b\u0010ª\u0002\u001a\u00030«\u00022\u0006\u00106\u001a\u000207H\u0016J3\u0010¬\u0002\u001a\u00030\u00ad\u0002\"\u0005\b\u0000\u0010®\u0002*\u00020C2\u000f\u0010¯\u0002\u001a\n\u0012\u0005\u0012\u0003H®\u00020°\u00022\u0007\u00106\u001a\u0003H®\u0002¢\u0006\u0003\u0010±\u0002R$\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007@PX\u0096\u000e¢\u0006\u000e\n\u0000\u001a\u0004\b\u0012\u0010\u0013\"\u0004\b\u0014\u0010\u0015R\u0011\u0010\n\u001a\u00020\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0018\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0013R\u0014\u0010\u001a\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0013R\u0014\u0010\u001c\u001a\u00020\u00078VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0013R\u0011\u0010\u001e\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u001f\u0010 R\u0011\u0010!\u001a\u00020\"¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0011\u0010%\u001a\u00020&¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0014\u0010)\u001a\u0004\u0018\u00010*X¦\u0004¢\u0006\u0006\u001a\u0004\b+\u0010,R\u0014\u0010-\u001a\u0004\u0018\u00010.X¦\u0004¢\u0006\u0006\u001a\u0004\b/\u00100R\u000e\u00101\u001a\u000202X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006²\u0002"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformerDispatcher;", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "phase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "implicitTypeOnly", Argument.Delimiters.none, "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "returnTypeCalculator", "Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "outerBodyResolveContext", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "expandTypeAliases", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;ZLorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;Z)V", "value", "getImplicitTypeOnly", "()Z", "setImplicitTypeOnly$org_jetbrains_kotlin_resolve", "(Z)V", "getReturnTypeCalculator", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/ReturnTypeCalculator;", "preserveCFGForClasses", "getPreserveCFGForClasses", "buildCfgForScripts", "getBuildCfgForScripts", "buildCfgForFiles", "getBuildCfgForFiles", "context", "getContext", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/BodyResolveContext;", "components", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;", "getComponents", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirAbstractBodyResolveTransformer$BodyResolveTransformerComponents;", "resolutionContext", "Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "getResolutionContext", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ResolutionContext;", "expressionsTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirExpressionsResolveTransformer;", "getExpressionsTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirExpressionsResolveTransformer;", "declarationsTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirDeclarationsResolveTransformer;", "getDeclarationsTransformer", "()Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirDeclarationsResolveTransformer;", "controlFlowStatementsTransformer", "Lorg/jetbrains/kotlin/fir/resolve/transformers/body/resolve/FirControlFlowStatementsResolveTransformer;", "transformFile", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "file", "data", "Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;", "transformScript", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "script", "transformCodeFragment", "Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;", "codeFragment", "transformReplSnippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "replSnippet", "transformElement", "E", "Lorg/jetbrains/kotlin/fir/FirElement;", "element", "(Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;)Lorg/jetbrains/kotlin/fir/FirElement;", "transformTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "transformImplicitTypeRef", "implicitTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirImplicitTypeRef;", "expressionTransformation", "R", "T", "expression", "transformation", "Lkotlin/Function3;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/Object;Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;Lkotlin/jvm/functions/Function3;)Ljava/lang/Object;", "transformExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "transformWrappedArgumentExpression", "wrappedArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWrappedArgumentExpression;", "transformQualifiedAccessExpression", "qualifiedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "transformQualifiedErrorAccessExpression", "qualifiedErrorAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedErrorAccessExpression;", "transformPropertyAccessExpression", "propertyAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "transformSuperReceiverExpression", "superReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSuperReceiverExpression;", "transformFunctionCall", "functionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "transformStringConcatenationCall", "stringConcatenationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirStringConcatenationCall;", "transformCallableReferenceAccess", "callableReferenceAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "transformBlock", "block", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "transformThisReceiverExpression", "thisReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "transformComparisonExpression", "comparisonExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirComparisonExpression;", "transformTypeOperatorCall", "typeOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "transformAugmentedAssignment", "augmentedAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirAugmentedAssignment;", "transformIncrementDecrementExpression", "incrementDecrementExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirIncrementDecrementExpression;", "transformEqualityOperatorCall", "equalityOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "transformCheckNotNullCall", "checkNotNullCall", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckNotNullCall;", "transformBooleanOperatorExpression", "booleanOperatorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "transformVariableAssignment", "variableAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "transformGetClassCall", "getClassCall", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "transformWrappedDelegateExpression", "wrappedDelegateExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWrappedDelegateExpression;", "transformLiteralExpression", "literalExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "transformAnnotation", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "transformForeignAnnotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "annotationCall", "transformAnnotationCall", "transformErrorAnnotationCall", "errorAnnotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorAnnotationCall;", "transformDelegatedConstructorCall", "delegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "transformMultiDelegatedConstructorCall", "multiDelegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirMultiDelegatedConstructorCall;", "transformIndexedAccessAugmentedAssignment", "indexedAccessAugmentedAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirIndexedAccessAugmentedAssignment;", "transformSafeCallExpression", "safeCallExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "transformCheckedSafeCallSubject", "checkedSafeCallSubject", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckedSafeCallSubject;", "transformCollectionLiteral", "collectionLiteral", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "transformSmartCastExpression", "smartCastExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "declarationTransformation", "declaration", "(Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/fir/resolve/ResolutionMode;Lkotlin/jvm/functions/Function3;)Lorg/jetbrains/kotlin/fir/FirElement;", "transformDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "transformDeclarationContent", "transformDeclarationStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "declarationStatus", "transformEnumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "enumEntry", "transformDanglingModifierList", "Lorg/jetbrains/kotlin/fir/declarations/FirDanglingModifierList;", "danglingModifierList", "transformProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "property", "transformErrorProperty", "errorProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorProperty;", "transformPropertyAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "propertyAccessor", "transformBackingField", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "backingField", "transformReceiverParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "receiverParameter", "transformField", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "field", "transformRegularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "regularClass", "transformAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "transformAnonymousObjectExpression", "anonymousObjectExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousObjectExpression;", "transformNamedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "namedFunction", "transformFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "function", "transformConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "constructor", "transformErrorPrimaryConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorPrimaryConstructor;", "errorPrimaryConstructor", "transformAnonymousInitializer", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "anonymousInitializer", "transformAnonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "anonymousFunction", "transformAnonymousFunctionExpression", "anonymousFunctionExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "transformValueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "valueParameter", "transformTypeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "typeAlias", "controlFlowStatementsTransformation", "transformWhileLoop", "whileLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirWhileLoop;", "transformDoWhileLoop", "doWhileLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirDoWhileLoop;", "transformWhenExpression", "whenExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "transformWhenBranch", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenBranch;", "whenBranch", "transformWhenSubjectExpression", "whenSubjectExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenSubjectExpression;", "transformTryExpression", "tryExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;", "transformCatch", "Lorg/jetbrains/kotlin/fir/expressions/FirCatch;", "catch", "transformJump", "Lorg/jetbrains/kotlin/fir/FirTargetElement;", "jump", "Lorg/jetbrains/kotlin/fir/expressions/FirJump;", "transformReturnExpression", "returnExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "transformThrowExpression", "throwExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThrowExpression;", "transformElvisExpression", "elvisExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirElvisExpression;", "transformReplDeclarationReference", "replDeclarationReference", "Lorg/jetbrains/kotlin/fir/expressions/FirReplDeclarationReference;", "transformReplExpressionReference", "replExpressionReference", "Lorg/jetbrains/kotlin/fir/expressions/FirReplExpressionReference;", "transformReplPropertyInitializer", "replPropertyInitializer", "Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyInitializer;", "transformReplPropertyDelegate", "replPropertyDelegate", "Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyDelegate;", "visitNoTransform", Argument.Delimiters.none, "D", "transformer", "Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;", "(Lorg/jetbrains/kotlin/fir/FirElement;Lorg/jetbrains/kotlin/fir/visitors/FirTransformer;Ljava/lang/Object;)V", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirAbstractBodyResolveTransformerDispatcher extends FirAbstractBodyResolveTransformer {
    private final FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents components;
    private final BodyResolveContext context;
    private final FirControlFlowStatementsResolveTransformer controlFlowStatementsTransformer;
    private boolean implicitTypeOnly;
    private final ResolutionContext resolutionContext;
    private final ReturnTypeCalculator returnTypeCalculator;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public FirAbstractBodyResolveTransformerDispatcher(FirSession firSession, FirResolvePhase firResolvePhase, boolean z, ScopeSession scopeSession, ReturnTypeCalculator returnTypeCalculator, BodyResolveContext bodyResolveContext, boolean z2) {
        super(firResolvePhase);
        firSession.getClass();
        firResolvePhase.getClass();
        scopeSession.getClass();
        returnTypeCalculator.getClass();
        this.implicitTypeOnly = z;
        this.returnTypeCalculator = returnTypeCalculator;
        BodyResolveContext bodyResolveContext2 = bodyResolveContext == null ? new BodyResolveContext(returnTypeCalculator, new DataFlowAnalyzerContext(firSession), false) : bodyResolveContext;
        this.context = bodyResolveContext2;
        FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents bodyResolveTransformerComponents = new FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents(firSession, scopeSession, this, bodyResolveContext2, z2);
        this.components = bodyResolveTransformerComponents;
        this.resolutionContext = new ResolutionContext(firSession, bodyResolveTransformerComponents, bodyResolveContext2);
        this.controlFlowStatementsTransformer = new FirControlFlowStatementsResolveTransformer(this);
    }

    public boolean getBuildCfgForFiles() {
        return !getImplicitTypeOnly();
    }

    public boolean getBuildCfgForScripts() {
        return !getImplicitTypeOnly();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformer
    public final FirAbstractBodyResolveTransformer.BodyResolveTransformerComponents getComponents() {
        return this.components;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformer
    public final BodyResolveContext getContext() {
        return this.context;
    }

    public abstract FirDeclarationsResolveTransformer getDeclarationsTransformer();

    public abstract FirExpressionsResolveTransformer getExpressionsTransformer();

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformer
    public boolean getImplicitTypeOnly() {
        return this.implicitTypeOnly;
    }

    public boolean getPreserveCFGForClasses() {
        return !getImplicitTypeOnly();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformer
    public final ResolutionContext getResolutionContext() {
        return this.resolutionContext;
    }

    public final ReturnTypeCalculator getReturnTypeCalculator() {
        return this.returnTypeCalculator;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.FirAbstractBodyResolveTransformer
    public void setImplicitTypeOnly$org_jetbrains_kotlin_resolve(boolean z) {
        this.implicitTypeOnly = z;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnnotation(FirAnnotation annotation, ResolutionMode data) {
        FirStatement firStatementTransformAnnotation;
        annotation.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformAnnotation = expressionsTransformer.transformAnnotation(annotation, data)) == null) ? annotation : firStatementTransformAnnotation;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnnotationCall(FirAnnotationCall annotationCall, ResolutionMode data) {
        FirStatement firStatementTransformAnnotationCall;
        annotationCall.getClass();
        data.getClass();
        FirBasedSymbol<?> containingDeclarationSymbol = annotationCall.getContainingDeclarationSymbol();
        if (!CollectionsKt.asReversedMutable(this.context.getContainers()).contains(containingDeclarationSymbol.getFir())) {
            return transformForeignAnnotationCall(containingDeclarationSymbol, annotationCall);
        }
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformAnnotationCall = expressionsTransformer.transformAnnotationCall(annotationCall, data)) == null) ? annotationCall : firStatementTransformAnnotationCall;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirAnonymousFunction transformAnonymousFunction(FirAnonymousFunction anonymousFunction, ResolutionMode data) {
        FirAnonymousFunction firAnonymousFunctionTransformAnonymousFunction;
        anonymousFunction.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firAnonymousFunctionTransformAnonymousFunction = declarationsTransformer.transformAnonymousFunction(anonymousFunction, data)) == null) ? anonymousFunction : firAnonymousFunctionTransformAnonymousFunction;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnonymousFunctionExpression(FirAnonymousFunctionExpression anonymousFunctionExpression, ResolutionMode data) {
        FirStatement firStatementTransformAnonymousFunctionExpression;
        anonymousFunctionExpression.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firStatementTransformAnonymousFunctionExpression = declarationsTransformer.transformAnonymousFunctionExpression(anonymousFunctionExpression, data)) == null) ? anonymousFunctionExpression : firStatementTransformAnonymousFunctionExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirAnonymousInitializer transformAnonymousInitializer(FirAnonymousInitializer anonymousInitializer, ResolutionMode data) {
        FirAnonymousInitializer firAnonymousInitializerTransformAnonymousInitializer;
        anonymousInitializer.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firAnonymousInitializerTransformAnonymousInitializer = declarationsTransformer.transformAnonymousInitializer(anonymousInitializer, data)) == null) ? anonymousInitializer : firAnonymousInitializerTransformAnonymousInitializer;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnonymousObject(FirAnonymousObject anonymousObject, ResolutionMode data) {
        FirAnonymousObject firAnonymousObjectTransformAnonymousObject;
        anonymousObject.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firAnonymousObjectTransformAnonymousObject = declarationsTransformer.transformAnonymousObject(anonymousObject, data)) == null) ? anonymousObject : firAnonymousObjectTransformAnonymousObject;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAnonymousObjectExpression(FirAnonymousObjectExpression anonymousObjectExpression, ResolutionMode data) {
        FirStatement firStatementTransformAnonymousObjectExpression;
        anonymousObjectExpression.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformAnonymousObjectExpression = expressionsTransformer.transformAnonymousObjectExpression(anonymousObjectExpression, data)) == null) ? anonymousObjectExpression : firStatementTransformAnonymousObjectExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformAugmentedAssignment(FirAugmentedAssignment augmentedAssignment, ResolutionMode data) {
        FirStatement firStatementTransformAugmentedAssignment;
        augmentedAssignment.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformAugmentedAssignment = expressionsTransformer.transformAugmentedAssignment(augmentedAssignment, data)) == null) ? augmentedAssignment : firStatementTransformAugmentedAssignment;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirBackingField transformBackingField(FirBackingField backingField, ResolutionMode data) {
        FirBackingField firBackingFieldTransformBackingField;
        backingField.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firBackingFieldTransformBackingField = declarationsTransformer.transformBackingField(backingField, data)) == null) ? backingField : firBackingFieldTransformBackingField;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformBlock(FirBlock block, ResolutionMode data) {
        FirStatement firStatementTransformBlock;
        block.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformBlock = expressionsTransformer.transformBlock(block, data)) == null) ? block : firStatementTransformBlock;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformBooleanOperatorExpression(FirBooleanOperatorExpression booleanOperatorExpression, ResolutionMode data) {
        FirStatement firStatementTransformBooleanOperatorExpression;
        booleanOperatorExpression.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformBooleanOperatorExpression = expressionsTransformer.transformBooleanOperatorExpression(booleanOperatorExpression, data)) == null) ? booleanOperatorExpression : firStatementTransformBooleanOperatorExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformCallableReferenceAccess(FirCallableReferenceAccess callableReferenceAccess, ResolutionMode data) {
        FirStatement firStatementTransformCallableReferenceAccess;
        callableReferenceAccess.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformCallableReferenceAccess = expressionsTransformer.transformCallableReferenceAccess(callableReferenceAccess, data)) == null) ? callableReferenceAccess : firStatementTransformCallableReferenceAccess;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirCatch transformCatch(FirCatch firCatch, ResolutionMode data) {
        firCatch.getClass();
        data.getClass();
        return this.controlFlowStatementsTransformer.transformCatch(firCatch, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformCheckNotNullCall(FirCheckNotNullCall checkNotNullCall, ResolutionMode data) {
        FirStatement firStatementTransformCheckNotNullCall;
        checkNotNullCall.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformCheckNotNullCall = expressionsTransformer.transformCheckNotNullCall(checkNotNullCall, data)) == null) ? checkNotNullCall : firStatementTransformCheckNotNullCall;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformCheckedSafeCallSubject(FirCheckedSafeCallSubject checkedSafeCallSubject, ResolutionMode data) {
        FirStatement firStatementTransformCheckedSafeCallSubject;
        checkedSafeCallSubject.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformCheckedSafeCallSubject = expressionsTransformer.transformCheckedSafeCallSubject(checkedSafeCallSubject, data)) == null) ? checkedSafeCallSubject : firStatementTransformCheckedSafeCallSubject;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirCodeFragment transformCodeFragment(FirCodeFragment codeFragment, ResolutionMode data) {
        FirCodeFragment firCodeFragmentTransformCodeFragment;
        codeFragment.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firCodeFragmentTransformCodeFragment = declarationsTransformer.transformCodeFragment(codeFragment, data)) == null) ? codeFragment : firCodeFragmentTransformCodeFragment;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformCollectionLiteral(FirCollectionLiteral collectionLiteral, ResolutionMode data) {
        FirStatement firStatementTransformCollectionLiteral;
        collectionLiteral.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformCollectionLiteral = expressionsTransformer.transformCollectionLiteral(collectionLiteral, data)) == null) ? collectionLiteral : firStatementTransformCollectionLiteral;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformComparisonExpression(FirComparisonExpression comparisonExpression, ResolutionMode data) {
        FirStatement firStatementTransformComparisonExpression;
        comparisonExpression.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformComparisonExpression = expressionsTransformer.transformComparisonExpression(comparisonExpression, data)) == null) ? comparisonExpression : firStatementTransformComparisonExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirConstructor transformConstructor(FirConstructor constructor, ResolutionMode data) {
        FirConstructor firConstructorTransformConstructor;
        constructor.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firConstructorTransformConstructor = declarationsTransformer.transformConstructor(constructor, data)) == null) ? constructor : firConstructorTransformConstructor;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirDanglingModifierList transformDanglingModifierList(FirDanglingModifierList danglingModifierList, ResolutionMode data) {
        FirDanglingModifierList firDanglingModifierListTransformDanglingModifierList;
        danglingModifierList.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firDanglingModifierListTransformDanglingModifierList = declarationsTransformer.transformDanglingModifierList(danglingModifierList, data)) == null) ? danglingModifierList : firDanglingModifierListTransformDanglingModifierList;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirDeclaration transformDeclaration(FirDeclaration declaration, ResolutionMode data) {
        FirDeclaration firDeclarationTransformDeclaration;
        declaration.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firDeclarationTransformDeclaration = declarationsTransformer.transformDeclaration(declaration, data)) == null) ? declaration : firDeclarationTransformDeclaration;
    }

    public FirDeclaration transformDeclarationContent(FirDeclaration declaration, ResolutionMode data) {
        declaration.getClass();
        data.getClass();
        return (FirDeclaration) transformElement(declaration, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirDeclarationStatus transformDeclarationStatus(FirDeclarationStatus declarationStatus, ResolutionMode data) {
        FirDeclarationStatus firDeclarationStatusTransformDeclarationStatus;
        declarationStatus.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firDeclarationStatusTransformDeclarationStatus = declarationsTransformer.transformDeclarationStatus(declarationStatus, data)) == null) ? declarationStatus : firDeclarationStatusTransformDeclarationStatus;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformDelegatedConstructorCall(FirDelegatedConstructorCall delegatedConstructorCall, ResolutionMode data) {
        FirStatement firStatementTransformDelegatedConstructorCall;
        delegatedConstructorCall.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformDelegatedConstructorCall = expressionsTransformer.transformDelegatedConstructorCall(delegatedConstructorCall, data)) == null) ? delegatedConstructorCall : firStatementTransformDelegatedConstructorCall;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformDoWhileLoop(FirDoWhileLoop doWhileLoop, ResolutionMode data) {
        doWhileLoop.getClass();
        data.getClass();
        return this.controlFlowStatementsTransformer.transformDoWhileLoop(doWhileLoop, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public <E extends FirElement> E transformElement(E element, ResolutionMode data) {
        element.getClass();
        data.getClass();
        E e = (E) element.transformChildren(this, data);
        e.getClass();
        return e;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformElvisExpression(FirElvisExpression elvisExpression, ResolutionMode data) {
        elvisExpression.getClass();
        data.getClass();
        return this.controlFlowStatementsTransformer.transformElvisExpression(elvisExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirEnumEntry transformEnumEntry(FirEnumEntry enumEntry, ResolutionMode data) {
        FirEnumEntry firEnumEntryTransformEnumEntry;
        enumEntry.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firEnumEntryTransformEnumEntry = declarationsTransformer.transformEnumEntry(enumEntry, data)) == null) ? enumEntry : firEnumEntryTransformEnumEntry;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformEqualityOperatorCall(FirEqualityOperatorCall equalityOperatorCall, ResolutionMode data) {
        FirStatement firStatementTransformEqualityOperatorCall;
        equalityOperatorCall.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformEqualityOperatorCall = expressionsTransformer.transformEqualityOperatorCall(equalityOperatorCall, data)) == null) ? equalityOperatorCall : firStatementTransformEqualityOperatorCall;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformErrorAnnotationCall(FirErrorAnnotationCall errorAnnotationCall, ResolutionMode data) {
        FirStatement firStatementTransformErrorAnnotationCall;
        errorAnnotationCall.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformErrorAnnotationCall = expressionsTransformer.transformErrorAnnotationCall(errorAnnotationCall, data)) == null) ? errorAnnotationCall : firStatementTransformErrorAnnotationCall;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirErrorPrimaryConstructor transformErrorPrimaryConstructor(FirErrorPrimaryConstructor errorPrimaryConstructor, ResolutionMode data) {
        FirErrorPrimaryConstructor firErrorPrimaryConstructorTransformErrorPrimaryConstructor;
        errorPrimaryConstructor.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firErrorPrimaryConstructorTransformErrorPrimaryConstructor = declarationsTransformer.transformErrorPrimaryConstructor(errorPrimaryConstructor, data)) == null) ? errorPrimaryConstructor : firErrorPrimaryConstructorTransformErrorPrimaryConstructor;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformErrorProperty(FirErrorProperty errorProperty, ResolutionMode data) {
        errorProperty.getClass();
        data.getClass();
        return transformProperty((FirProperty) errorProperty, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformExpression(FirExpression expression, ResolutionMode data) {
        FirStatement firStatementTransformExpression;
        expression.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformExpression = expressionsTransformer.transformExpression(expression, data)) == null) ? expression : firStatementTransformExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirField transformField(FirField field, ResolutionMode data) {
        FirField firFieldTransformField;
        field.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firFieldTransformField = declarationsTransformer.transformField(field, data)) == null) ? field : firFieldTransformField;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.transformers.FirAbstractPhaseTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirFile transformFile(FirFile file, ResolutionMode data) {
        FirFile firFileTransformFile;
        file.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firFileTransformFile = declarationsTransformer.transformFile(file, data)) == null) ? file : firFileTransformFile;
    }

    public FirAnnotationCall transformForeignAnnotationCall(FirBasedSymbol<?> symbol, FirAnnotationCall annotationCall) {
        symbol.getClass();
        annotationCall.getClass();
        return annotationCall;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirFunction transformFunction(FirFunction function, ResolutionMode data) {
        FirFunction firFunctionTransformFunction;
        function.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firFunctionTransformFunction = declarationsTransformer.transformFunction(function, data)) == null) ? function : firFunctionTransformFunction;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformFunctionCall(FirFunctionCall functionCall, ResolutionMode data) {
        FirStatement firStatementTransformFunctionCall;
        functionCall.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformFunctionCall = expressionsTransformer.transformFunctionCall(functionCall, data)) == null) ? functionCall : firStatementTransformFunctionCall;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformGetClassCall(FirGetClassCall getClassCall, ResolutionMode data) {
        FirStatement firStatementTransformGetClassCall;
        getClassCall.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformGetClassCall = expressionsTransformer.transformGetClassCall(getClassCall, data)) == null) ? getClassCall : firStatementTransformGetClassCall;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirTypeRef transformImplicitTypeRef(FirImplicitTypeRef implicitTypeRef, ResolutionMode data) {
        implicitTypeRef.getClass();
        data.getClass();
        return !(data instanceof ResolutionMode.UpdateImplicitTypeRef) ? implicitTypeRef : (FirTypeRef) FirTransformerUtilKt.transformSingle(((ResolutionMode.UpdateImplicitTypeRef) data).getNewTypeRef(), this, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformIncrementDecrementExpression(FirIncrementDecrementExpression incrementDecrementExpression, ResolutionMode data) {
        FirStatement firStatementTransformIncrementDecrementExpression;
        incrementDecrementExpression.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformIncrementDecrementExpression = expressionsTransformer.transformIncrementDecrementExpression(incrementDecrementExpression, data)) == null) ? incrementDecrementExpression : firStatementTransformIncrementDecrementExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformIndexedAccessAugmentedAssignment(FirIndexedAccessAugmentedAssignment indexedAccessAugmentedAssignment, ResolutionMode data) {
        FirStatement firStatementTransformIndexedAccessAugmentedAssignment;
        indexedAccessAugmentedAssignment.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformIndexedAccessAugmentedAssignment = expressionsTransformer.transformIndexedAccessAugmentedAssignment(indexedAccessAugmentedAssignment, data)) == null) ? indexedAccessAugmentedAssignment : firStatementTransformIndexedAccessAugmentedAssignment;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public <E extends FirTargetElement> FirStatement transformJump(FirJump<E> jump, ResolutionMode data) {
        jump.getClass();
        data.getClass();
        return this.controlFlowStatementsTransformer.transformJump((FirJump) jump, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformLiteralExpression(FirLiteralExpression literalExpression, ResolutionMode data) {
        FirStatement firStatementTransformLiteralExpression;
        literalExpression.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformLiteralExpression = expressionsTransformer.transformLiteralExpression(literalExpression, data)) == null) ? literalExpression : firStatementTransformLiteralExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformMultiDelegatedConstructorCall(FirMultiDelegatedConstructorCall multiDelegatedConstructorCall, ResolutionMode data) {
        FirStatement firStatementTransformMultiDelegatedConstructorCall;
        multiDelegatedConstructorCall.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformMultiDelegatedConstructorCall = expressionsTransformer.transformMultiDelegatedConstructorCall(multiDelegatedConstructorCall, data)) == null) ? multiDelegatedConstructorCall : firStatementTransformMultiDelegatedConstructorCall;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirNamedFunction transformNamedFunction(FirNamedFunction namedFunction, ResolutionMode data) {
        FirNamedFunction firNamedFunctionTransformNamedFunction;
        namedFunction.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firNamedFunctionTransformNamedFunction = declarationsTransformer.transformNamedFunction(namedFunction, data)) == null) ? namedFunction : firNamedFunctionTransformNamedFunction;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirProperty transformProperty(FirProperty property, ResolutionMode data) {
        FirProperty firPropertyTransformProperty;
        property.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firPropertyTransformProperty = declarationsTransformer.transformProperty(property, data)) == null) ? property : firPropertyTransformProperty;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformPropertyAccessExpression(FirPropertyAccessExpression propertyAccessExpression, ResolutionMode data) {
        FirExpression firExpressionTransformQualifiedAccessExpression;
        propertyAccessExpression.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firExpressionTransformQualifiedAccessExpression = expressionsTransformer.transformQualifiedAccessExpression((FirQualifiedAccessExpression) propertyAccessExpression, data)) == null) ? propertyAccessExpression : firExpressionTransformQualifiedAccessExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirPropertyAccessor transformPropertyAccessor(FirPropertyAccessor propertyAccessor, ResolutionMode data) {
        FirPropertyAccessor firPropertyAccessorTransformPropertyAccessor;
        propertyAccessor.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firPropertyAccessorTransformPropertyAccessor = declarationsTransformer.transformPropertyAccessor(propertyAccessor, data)) == null) ? propertyAccessor : firPropertyAccessorTransformPropertyAccessor;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformQualifiedAccessExpression(FirQualifiedAccessExpression qualifiedAccessExpression, ResolutionMode data) {
        FirExpression firExpressionTransformQualifiedAccessExpression;
        qualifiedAccessExpression.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firExpressionTransformQualifiedAccessExpression = expressionsTransformer.transformQualifiedAccessExpression(qualifiedAccessExpression, data)) == null) ? qualifiedAccessExpression : firExpressionTransformQualifiedAccessExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformQualifiedErrorAccessExpression(FirQualifiedErrorAccessExpression qualifiedErrorAccessExpression, ResolutionMode data) {
        FirStatement firStatementTransformQualifiedErrorAccessExpression;
        qualifiedErrorAccessExpression.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformQualifiedErrorAccessExpression = expressionsTransformer.transformQualifiedErrorAccessExpression(qualifiedErrorAccessExpression, data)) == null) ? qualifiedErrorAccessExpression : firStatementTransformQualifiedErrorAccessExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirReceiverParameter transformReceiverParameter(FirReceiverParameter receiverParameter, ResolutionMode data) {
        FirReceiverParameter firReceiverParameterTransformReceiverParameter;
        receiverParameter.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firReceiverParameterTransformReceiverParameter = declarationsTransformer.transformReceiverParameter(receiverParameter, data)) == null) ? receiverParameter : firReceiverParameterTransformReceiverParameter;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirRegularClass transformRegularClass(FirRegularClass regularClass, ResolutionMode data) {
        FirRegularClass firRegularClassTransformRegularClass;
        regularClass.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firRegularClassTransformRegularClass = declarationsTransformer.transformRegularClass(regularClass, data)) == null) ? regularClass : firRegularClassTransformRegularClass;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformReplDeclarationReference(FirReplDeclarationReference replDeclarationReference, ResolutionMode data) {
        FirStatement firStatementTransformReplDeclarationReference;
        replDeclarationReference.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformReplDeclarationReference = expressionsTransformer.transformReplDeclarationReference(replDeclarationReference, data)) == null) ? replDeclarationReference : firStatementTransformReplDeclarationReference;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformReplExpressionReference(FirReplExpressionReference replExpressionReference, ResolutionMode data) {
        FirStatement firStatementTransformReplExpressionReference;
        replExpressionReference.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformReplExpressionReference = expressionsTransformer.transformReplExpressionReference(replExpressionReference, data)) == null) ? replExpressionReference : firStatementTransformReplExpressionReference;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformReplPropertyDelegate(FirReplPropertyDelegate replPropertyDelegate, ResolutionMode data) {
        FirStatement firStatementTransformReplPropertyDelegate;
        replPropertyDelegate.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformReplPropertyDelegate = expressionsTransformer.transformReplPropertyDelegate(replPropertyDelegate, data)) == null) ? replPropertyDelegate : firStatementTransformReplPropertyDelegate;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformReplPropertyInitializer(FirReplPropertyInitializer replPropertyInitializer, ResolutionMode data) {
        FirStatement firStatementTransformReplPropertyInitializer;
        replPropertyInitializer.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformReplPropertyInitializer = expressionsTransformer.transformReplPropertyInitializer(replPropertyInitializer, data)) == null) ? replPropertyInitializer : firStatementTransformReplPropertyInitializer;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirReplSnippet transformReplSnippet(FirReplSnippet replSnippet, ResolutionMode data) {
        FirReplSnippet firReplSnippetTransformReplSnippet;
        replSnippet.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firReplSnippetTransformReplSnippet = declarationsTransformer.transformReplSnippet(replSnippet, data)) == null) ? replSnippet : firReplSnippetTransformReplSnippet;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultTransformer, org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformReturnExpression(FirReturnExpression returnExpression, ResolutionMode data) {
        returnExpression.getClass();
        data.getClass();
        return this.controlFlowStatementsTransformer.transformReturnExpression(returnExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformSafeCallExpression(FirSafeCallExpression safeCallExpression, ResolutionMode data) {
        FirStatement firStatementTransformSafeCallExpression;
        safeCallExpression.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformSafeCallExpression = expressionsTransformer.transformSafeCallExpression(safeCallExpression, data)) == null) ? safeCallExpression : firStatementTransformSafeCallExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirScript transformScript(FirScript script, ResolutionMode data) {
        FirScript firScriptTransformScript;
        script.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firScriptTransformScript = declarationsTransformer.transformScript(script, data)) == null) ? script : firScriptTransformScript;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformSmartCastExpression(FirSmartCastExpression smartCastExpression, ResolutionMode data) {
        FirStatement firStatementTransformSmartCastExpression;
        smartCastExpression.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformSmartCastExpression = expressionsTransformer.transformSmartCastExpression(smartCastExpression, data)) == null) ? smartCastExpression : firStatementTransformSmartCastExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformStringConcatenationCall(FirStringConcatenationCall stringConcatenationCall, ResolutionMode data) {
        FirStatement firStatementTransformStringConcatenationCall;
        stringConcatenationCall.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformStringConcatenationCall = expressionsTransformer.transformStringConcatenationCall(stringConcatenationCall, data)) == null) ? stringConcatenationCall : firStatementTransformStringConcatenationCall;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformSuperReceiverExpression(FirSuperReceiverExpression superReceiverExpression, ResolutionMode data) {
        FirStatement firStatementTransformSuperReceiverExpression;
        superReceiverExpression.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformSuperReceiverExpression = expressionsTransformer.transformSuperReceiverExpression(superReceiverExpression, data)) == null) ? superReceiverExpression : firStatementTransformSuperReceiverExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformThisReceiverExpression(FirThisReceiverExpression thisReceiverExpression, ResolutionMode data) {
        FirStatement firStatementTransformThisReceiverExpression;
        thisReceiverExpression.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformThisReceiverExpression = expressionsTransformer.transformThisReceiverExpression(thisReceiverExpression, data)) == null) ? thisReceiverExpression : firStatementTransformThisReceiverExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformThrowExpression(FirThrowExpression throwExpression, ResolutionMode data) {
        throwExpression.getClass();
        data.getClass();
        return this.controlFlowStatementsTransformer.transformThrowExpression(throwExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformTryExpression(FirTryExpression tryExpression, ResolutionMode data) {
        tryExpression.getClass();
        data.getClass();
        return this.controlFlowStatementsTransformer.transformTryExpression(tryExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirTypeAlias transformTypeAlias(FirTypeAlias typeAlias, ResolutionMode data) {
        FirTypeAlias firTypeAliasTransformTypeAlias;
        typeAlias.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firTypeAliasTransformTypeAlias = declarationsTransformer.transformTypeAlias(typeAlias, data)) == null) ? typeAlias : firTypeAliasTransformTypeAlias;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformTypeOperatorCall(FirTypeOperatorCall typeOperatorCall, ResolutionMode data) {
        FirStatement firStatementTransformTypeOperatorCall;
        typeOperatorCall.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformTypeOperatorCall = expressionsTransformer.transformTypeOperatorCall(typeOperatorCall, data)) == null) ? typeOperatorCall : firStatementTransformTypeOperatorCall;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    /* JADX INFO: renamed from: transformTypeRef, reason: merged with bridge method [inline-methods] */
    public FirResolvedTypeRef mo600transformTypeRef(FirTypeRef typeRef, ResolutionMode data) {
        FirResolvedTypeRef firResolvedTypeRefMo600transformTypeRef;
        typeRef.getClass();
        data.getClass();
        if (typeRef instanceof FirResolvedTypeRef) {
            if (typeRef instanceof FirErrorTypeRef) {
                ((FirErrorTypeRef) typeRef).transformPartiallyResolvedTypeRef(this, data);
            }
            firResolvedTypeRefMo600transformTypeRef = (FirResolvedTypeRef) typeRef;
        } else {
            firResolvedTypeRefMo600transformTypeRef = getComponents().getTypeResolverTransformer().mo600transformTypeRef(typeRef, new TypeResolutionConfiguration(BodyResolveComponentsKt.createCurrentScopeList(this.components), this.context.getContainingClassDeclarations(), this.context.getFile(), this.context.getTopContainerForTypeResolution()));
        }
        List listMutableListOf = CollectionsKt.mutableListOf(new ConeKotlinType[]{firResolvedTypeRefMo600transformTypeRef.getConeType()});
        while (!listMutableListOf.isEmpty()) {
            ConeKotlinType coneKotlinType = (ConeKotlinType) AddToStdlibKt.popLast(listMutableListOf);
            Iterator<T> it = CustomAnnotationTypeAttributeKt.getTypeAnnotations(coneKotlinType).iterator();
            while (it.hasNext()) {
                ((FirAnnotation) it.next()).accept(this, data);
            }
            if (coneKotlinType instanceof ConeFlexibleType) {
                ConeFlexibleType coneFlexibleType = (ConeFlexibleType) coneKotlinType;
                listMutableListOf.add(coneFlexibleType.getLowerBound());
                if (!coneFlexibleType.getIsTrivial()) {
                    listMutableListOf.add(coneFlexibleType.getUpperBound());
                }
            } else if (coneKotlinType instanceof ConeDefinitelyNotNullType) {
                listMutableListOf.add(((ConeDefinitelyNotNullType) coneKotlinType).getOriginal());
            } else if (coneKotlinType instanceof ConeIntersectionType) {
                listMutableListOf.addAll(((ConeIntersectionType) coneKotlinType).getIntersectedTypes());
            } else {
                for (ConeKotlinTypeProjection coneKotlinTypeProjection : coneKotlinType.getTypeArguments()) {
                    if (coneKotlinTypeProjection instanceof ConeKotlinTypeProjection) {
                        listMutableListOf.add(coneKotlinTypeProjection.getType());
                    }
                }
            }
        }
        return firResolvedTypeRefMo600transformTypeRef.transformAnnotations(this, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirValueParameter transformValueParameter(FirValueParameter valueParameter, ResolutionMode data) {
        FirValueParameter firValueParameterTransformValueParameter;
        valueParameter.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firValueParameterTransformValueParameter = declarationsTransformer.transformValueParameter(valueParameter, data)) == null) ? valueParameter : firValueParameterTransformValueParameter;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformVariableAssignment(FirVariableAssignment variableAssignment, ResolutionMode data) {
        FirStatement firStatementTransformVariableAssignment;
        variableAssignment.getClass();
        data.getClass();
        FirExpressionsResolveTransformer expressionsTransformer = getExpressionsTransformer();
        return (expressionsTransformer == null || (firStatementTransformVariableAssignment = expressionsTransformer.transformVariableAssignment(variableAssignment, data)) == null) ? variableAssignment : firStatementTransformVariableAssignment;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirWhenBranch transformWhenBranch(FirWhenBranch whenBranch, ResolutionMode data) {
        whenBranch.getClass();
        data.getClass();
        return this.controlFlowStatementsTransformer.transformWhenBranch(whenBranch, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformWhenExpression(FirWhenExpression whenExpression, ResolutionMode data) {
        whenExpression.getClass();
        data.getClass();
        return this.controlFlowStatementsTransformer.transformWhenExpression(whenExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformWhenSubjectExpression(FirWhenSubjectExpression whenSubjectExpression, ResolutionMode data) {
        whenSubjectExpression.getClass();
        data.getClass();
        FirStatement firStatementTransformPropertyAccessExpression = transformPropertyAccessExpression((FirPropertyAccessExpression) whenSubjectExpression, data);
        getComponents().getDataFlowAnalyzer().exitWhenSubjectExpression(whenSubjectExpression);
        return firStatementTransformPropertyAccessExpression;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformWhileLoop(FirWhileLoop whileLoop, ResolutionMode data) {
        whileLoop.getClass();
        data.getClass();
        return this.controlFlowStatementsTransformer.transformWhileLoop(whileLoop, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformWrappedArgumentExpression(FirWrappedArgumentExpression wrappedArgumentExpression, ResolutionMode data) {
        wrappedArgumentExpression.getClass();
        data.getClass();
        return (FirStatement) transformElement(wrappedArgumentExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirTransformer
    public FirStatement transformWrappedDelegateExpression(FirWrappedDelegateExpression wrappedDelegateExpression, ResolutionMode data) {
        FirStatement firStatementTransformWrappedDelegateExpression;
        wrappedDelegateExpression.getClass();
        data.getClass();
        FirDeclarationsResolveTransformer declarationsTransformer = getDeclarationsTransformer();
        return (declarationsTransformer == null || (firStatementTransformWrappedDelegateExpression = declarationsTransformer.transformWrappedDelegateExpression(wrappedDelegateExpression, data)) == null) ? wrappedDelegateExpression : firStatementTransformWrappedDelegateExpression;
    }

    public final <D> void visitNoTransform(FirElement firElement, FirTransformer<? super D> firTransformer, D d) {
        firElement.getClass();
        firTransformer.getClass();
        FirElement firElementTransform = firElement.transform(firTransformer, d);
        if (firElementTransform == firElement) {
            return;
        }
        StringBuilder sb = new StringBuilder("become ");
        sb.append(firElementTransform);
        sb.append(": `");
        sb.append(UtilsKt.render(firElementTransform));
        sb.append("`, was ");
        sb.append(firElement);
        ia5.a(sb, ": `", UtilsKt.render(firElement), 96);
    }

    public /* synthetic */ FirAbstractBodyResolveTransformerDispatcher(FirSession firSession, FirResolvePhase firResolvePhase, boolean z, ScopeSession scopeSession, ReturnTypeCalculator returnTypeCalculator, BodyResolveContext bodyResolveContext, boolean z2, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, firResolvePhase, z, scopeSession, (i & 16) != 0 ? ReturnTypeCalculatorForFullBodyResolve.INSTANCE.getDefault() : returnTypeCalculator, (i & 32) != 0 ? null : bodyResolveContext, z2);
    }
}
