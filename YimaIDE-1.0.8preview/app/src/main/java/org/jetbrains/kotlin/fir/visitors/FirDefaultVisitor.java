package org.jetbrains.kotlin.fir.visitors;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirTargetElement;
import org.jetbrains.kotlin.fir.contracts.FirEffectDeclaration;
import org.jetbrains.kotlin.fir.contracts.FirErrorContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirLazyContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirLegacyRawContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirRawContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirResolvedContractDescription;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirConstructedClassTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirOuterClassTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirResolvedImport;
import org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousObjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirAugmentedAssignment;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirBooleanOperatorExpression;
import org.jetbrains.kotlin.fir.expressions.FirBreakExpression;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCheckedSafeCallSubject;
import org.jetbrains.kotlin.fir.expressions.FirClassReferenceExpression;
import org.jetbrains.kotlin.fir.expressions.FirComparisonExpression;
import org.jetbrains.kotlin.fir.expressions.FirComponentCall;
import org.jetbrains.kotlin.fir.expressions.FirContinueExpression;
import org.jetbrains.kotlin.fir.expressions.FirDesugaredAssignmentValueReferenceExpression;
import org.jetbrains.kotlin.fir.expressions.FirDoWhileLoop;
import org.jetbrains.kotlin.fir.expressions.FirEnumEntryDeserializedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionTypeConversionExpression;
import org.jetbrains.kotlin.fir.expressions.FirImplicitInvokeCall;
import org.jetbrains.kotlin.fir.expressions.FirIncrementDecrementExpression;
import org.jetbrains.kotlin.fir.expressions.FirIndexedAccessAugmentedAssignment;
import org.jetbrains.kotlin.fir.expressions.FirIntegerLiteralOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirJump;
import org.jetbrains.kotlin.fir.expressions.FirLazyBlock;
import org.jetbrains.kotlin.fir.expressions.FirLazyExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirLoopJump;
import org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirNamedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
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
import org.jetbrains.kotlin.fir.expressions.FirSuperReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirThrowExpression;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhileLoop;
import org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirWrappedDelegateExpression;
import org.jetbrains.kotlin.fir.expressions.FirWrappedExpression;
import org.jetbrains.kotlin.fir.references.FirBackingFieldReference;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.references.FirDelegateFieldReference;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirNamedReferenceWithCandidateBase;
import org.jetbrains.kotlin.fir.references.FirPropertyWithExplicitBackingFieldResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.FirResolvedCallableReference;
import org.jetbrains.kotlin.fir.references.FirResolvedErrorReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.FirSuperReference;
import org.jetbrains.kotlin.fir.references.FirThisReference;
import org.jetbrains.kotlin.fir.types.FirDynamicTypeRef;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef;
import org.jetbrains.kotlin.fir.types.FirPlaceholderProjection;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirStarProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0086\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0001*\u0006\b\u0001\u0010\u0002 \u00002\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\nJ\u001d\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u000eJ\u001d\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0012J\u001d\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0016J\u001d\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001aJ\u001d\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001eJ\u001d\u0010\u001f\u001a\u00028\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\"J\u001d\u0010#\u001a\u00028\u00002\u0006\u0010$\u001a\u00020%2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010&J\u001d\u0010'\u001a\u00028\u00002\u0006\u0010(\u001a\u00020)2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010*J-\u0010+\u001a\u00028\u0000\"\b\b\u0002\u0010,*\u00020-2\f\u0010.\u001a\b\u0012\u0004\u0012\u0002H,0/2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u00100J\u001d\u00101\u001a\u00028\u00002\u0006\u00102\u001a\u0002032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u00104J\u001d\u00105\u001a\u00028\u00002\u0006\u00106\u001a\u0002072\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u00108J\u001d\u00109\u001a\u00028\u00002\u0006\u0010:\u001a\u00020;2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010<J\u001d\u0010=\u001a\u00028\u00002\u0006\u0010>\u001a\u00020?2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010@J\u001d\u0010A\u001a\u00028\u00002\u0006\u0010B\u001a\u00020C2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010DJ\u001d\u0010E\u001a\u00028\u00002\u0006\u0010F\u001a\u00020G2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010HJ\u001d\u0010I\u001a\u00028\u00002\u0006\u0010J\u001a\u00020K2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010LJ\u001d\u0010M\u001a\u00028\u00002\u0006\u0010N\u001a\u00020O2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010PJ\u001d\u0010Q\u001a\u00028\u00002\u0006\u0010R\u001a\u00020S2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010TJ\u001d\u0010U\u001a\u00028\u00002\u0006\u0010V\u001a\u00020W2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010XJ\u001d\u0010Y\u001a\u00028\u00002\u0006\u0010Z\u001a\u00020[2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\\J\u001d\u0010]\u001a\u00028\u00002\u0006\u0010^\u001a\u00020_2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010`J\u001d\u0010a\u001a\u00028\u00002\u0006\u0010b\u001a\u00020c2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010dJ\u001d\u0010e\u001a\u00028\u00002\u0006\u0010f\u001a\u00020g2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010hJ\u001d\u0010i\u001a\u00028\u00002\u0006\u0010j\u001a\u00020k2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010lJ\u001d\u0010m\u001a\u00028\u00002\u0006\u0010n\u001a\u00020o2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010pJ\u001d\u0010q\u001a\u00028\u00002\u0006\u0010r\u001a\u00020s2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010tJ\u001d\u0010u\u001a\u00028\u00002\u0006\u0010v\u001a\u00020w2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010xJ\u001d\u0010y\u001a\u00028\u00002\u0006\u0010z\u001a\u00020{2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010|J\u001e\u0010}\u001a\u00028\u00002\u0006\u0010~\u001a\u00020\u007f2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0080\u0001J!\u0010\u0081\u0001\u001a\u00028\u00002\b\u0010\u0082\u0001\u001a\u00030\u0083\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0084\u0001J!\u0010\u0085\u0001\u001a\u00028\u00002\b\u0010\u0086\u0001\u001a\u00030\u0087\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0088\u0001J!\u0010\u0089\u0001\u001a\u00028\u00002\b\u0010\u008a\u0001\u001a\u00030\u008b\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u008c\u0001J!\u0010\u008d\u0001\u001a\u00028\u00002\b\u0010\u008e\u0001\u001a\u00030\u008f\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0090\u0001J!\u0010\u0091\u0001\u001a\u00028\u00002\b\u0010\u0092\u0001\u001a\u00030\u0093\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0094\u0001J!\u0010\u0095\u0001\u001a\u00028\u00002\b\u0010\u0096\u0001\u001a\u00030\u0097\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0098\u0001J!\u0010\u0099\u0001\u001a\u00028\u00002\b\u0010\u009a\u0001\u001a\u00030\u009b\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u009c\u0001J!\u0010\u009d\u0001\u001a\u00028\u00002\b\u0010\u009e\u0001\u001a\u00030\u009f\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010 \u0001J!\u0010¡\u0001\u001a\u00028\u00002\b\u0010¢\u0001\u001a\u00030£\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¤\u0001J!\u0010¥\u0001\u001a\u00028\u00002\b\u0010¦\u0001\u001a\u00030§\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¨\u0001J!\u0010©\u0001\u001a\u00028\u00002\b\u0010ª\u0001\u001a\u00030«\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¬\u0001J!\u0010\u00ad\u0001\u001a\u00028\u00002\b\u0010®\u0001\u001a\u00030¯\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010°\u0001J!\u0010±\u0001\u001a\u00028\u00002\b\u0010²\u0001\u001a\u00030³\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010´\u0001J!\u0010µ\u0001\u001a\u00028\u00002\b\u0010¶\u0001\u001a\u00030·\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¸\u0001J!\u0010¹\u0001\u001a\u00028\u00002\b\u0010º\u0001\u001a\u00030»\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¼\u0001J!\u0010½\u0001\u001a\u00028\u00002\b\u0010¾\u0001\u001a\u00030¿\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010À\u0001J!\u0010Á\u0001\u001a\u00028\u00002\b\u0010Â\u0001\u001a\u00030Ã\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ä\u0001J!\u0010Å\u0001\u001a\u00028\u00002\b\u0010Æ\u0001\u001a\u00030Ç\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010È\u0001J!\u0010É\u0001\u001a\u00028\u00002\b\u0010Ê\u0001\u001a\u00030Ë\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ì\u0001J!\u0010Í\u0001\u001a\u00028\u00002\b\u0010Î\u0001\u001a\u00030Ï\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ð\u0001J!\u0010Ñ\u0001\u001a\u00028\u00002\b\u0010Ò\u0001\u001a\u00030Ó\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ô\u0001J!\u0010Õ\u0001\u001a\u00028\u00002\b\u0010Ö\u0001\u001a\u00030×\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ø\u0001J!\u0010Ù\u0001\u001a\u00028\u00002\b\u0010Ú\u0001\u001a\u00030Û\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ü\u0001J!\u0010Ý\u0001\u001a\u00028\u00002\b\u0010Þ\u0001\u001a\u00030ß\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010à\u0001J!\u0010á\u0001\u001a\u00028\u00002\b\u0010â\u0001\u001a\u00030ã\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ä\u0001J!\u0010å\u0001\u001a\u00028\u00002\b\u0010æ\u0001\u001a\u00030ç\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010è\u0001J!\u0010é\u0001\u001a\u00028\u00002\b\u0010ê\u0001\u001a\u00030ë\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ì\u0001J!\u0010í\u0001\u001a\u00028\u00002\b\u0010î\u0001\u001a\u00030ï\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ð\u0001J!\u0010ñ\u0001\u001a\u00028\u00002\b\u0010ò\u0001\u001a\u00030ó\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ô\u0001J!\u0010õ\u0001\u001a\u00028\u00002\b\u0010ö\u0001\u001a\u00030÷\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ø\u0001J!\u0010ù\u0001\u001a\u00028\u00002\b\u0010ú\u0001\u001a\u00030û\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ü\u0001J!\u0010ý\u0001\u001a\u00028\u00002\b\u0010þ\u0001\u001a\u00030ÿ\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0080\u0002J!\u0010\u0081\u0002\u001a\u00028\u00002\b\u0010\u0082\u0002\u001a\u00030\u0083\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0084\u0002J!\u0010\u0085\u0002\u001a\u00028\u00002\b\u0010\u0086\u0002\u001a\u00030\u0087\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0088\u0002J!\u0010\u0089\u0002\u001a\u00028\u00002\b\u0010\u008a\u0002\u001a\u00030\u008b\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u008c\u0002J!\u0010\u008d\u0002\u001a\u00028\u00002\b\u0010\u008e\u0002\u001a\u00030\u008f\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0090\u0002J!\u0010\u0091\u0002\u001a\u00028\u00002\b\u0010\u0092\u0002\u001a\u00030\u0093\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0094\u0002J!\u0010\u0095\u0002\u001a\u00028\u00002\b\u0010\u0096\u0002\u001a\u00030\u0097\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0098\u0002J!\u0010\u0099\u0002\u001a\u00028\u00002\b\u0010\u009a\u0002\u001a\u00030\u009b\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u009c\u0002J!\u0010\u009d\u0002\u001a\u00028\u00002\b\u0010\u009e\u0002\u001a\u00030\u009f\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010 \u0002J!\u0010¡\u0002\u001a\u00028\u00002\b\u0010¢\u0002\u001a\u00030£\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¤\u0002J!\u0010¥\u0002\u001a\u00028\u00002\b\u0010¦\u0002\u001a\u00030§\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¨\u0002J!\u0010©\u0002\u001a\u00028\u00002\b\u0010ª\u0002\u001a\u00030«\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¬\u0002J!\u0010\u00ad\u0002\u001a\u00028\u00002\b\u0010®\u0002\u001a\u00030¯\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010°\u0002J!\u0010±\u0002\u001a\u00028\u00002\b\u0010²\u0002\u001a\u00030³\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010´\u0002J!\u0010µ\u0002\u001a\u00028\u00002\b\u0010¶\u0002\u001a\u00030·\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¸\u0002J!\u0010¹\u0002\u001a\u00028\u00002\b\u0010º\u0002\u001a\u00030»\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¼\u0002J!\u0010½\u0002\u001a\u00028\u00002\b\u0010¾\u0002\u001a\u00030¿\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010À\u0002J!\u0010Á\u0002\u001a\u00028\u00002\b\u0010Â\u0002\u001a\u00030Ã\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ä\u0002J!\u0010Å\u0002\u001a\u00028\u00002\b\u0010Æ\u0002\u001a\u00030Ç\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010È\u0002J!\u0010É\u0002\u001a\u00028\u00002\b\u0010Ê\u0002\u001a\u00030Ë\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ì\u0002J!\u0010Í\u0002\u001a\u00028\u00002\b\u0010Î\u0002\u001a\u00030Ï\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ð\u0002J!\u0010Ñ\u0002\u001a\u00028\u00002\b\u0010Ò\u0002\u001a\u00030Ó\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ô\u0002J!\u0010Õ\u0002\u001a\u00028\u00002\b\u0010Ö\u0002\u001a\u00030×\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ø\u0002J!\u0010Ù\u0002\u001a\u00028\u00002\b\u0010Ú\u0002\u001a\u00030Û\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ü\u0002J!\u0010Ý\u0002\u001a\u00028\u00002\b\u0010Þ\u0002\u001a\u00030ß\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010à\u0002J!\u0010á\u0002\u001a\u00028\u00002\b\u0010â\u0002\u001a\u00030ã\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ä\u0002J!\u0010å\u0002\u001a\u00028\u00002\b\u0010æ\u0002\u001a\u00030ç\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010è\u0002J!\u0010é\u0002\u001a\u00028\u00002\b\u0010ê\u0002\u001a\u00030ë\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ì\u0002J!\u0010í\u0002\u001a\u00028\u00002\b\u0010î\u0002\u001a\u00030ï\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ð\u0002J!\u0010ñ\u0002\u001a\u00028\u00002\b\u0010ò\u0002\u001a\u00030ó\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ô\u0002J!\u0010õ\u0002\u001a\u00028\u00002\b\u0010ö\u0002\u001a\u00030÷\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ø\u0002J!\u0010ù\u0002\u001a\u00028\u00002\b\u0010ú\u0002\u001a\u00030û\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ü\u0002J!\u0010ý\u0002\u001a\u00028\u00002\b\u0010þ\u0002\u001a\u00030ÿ\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0080\u0003¨\u0006\u0081\u0003"}, d2 = {"Lorg/jetbrains/kotlin/fir/visitors/FirDefaultVisitor;", "R", "D", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "<init>", "()V", "visitTypeParametersOwner", "typeParametersOwner", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParametersOwner;", "data", "(Lorg/jetbrains/kotlin/fir/declarations/FirTypeParametersOwner;Ljava/lang/Object;)Ljava/lang/Object;", "visitCallableDeclaration", "callableDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitStatement", "statement", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "(Lorg/jetbrains/kotlin/fir/expressions/FirStatement;Ljava/lang/Object;)Ljava/lang/Object;", "visitExpression", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitLazyExpression", "lazyExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirLazyExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirLazyExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitCall", K2JsArgumentConstants.CALL, "Lorg/jetbrains/kotlin/fir/expressions/FirCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirCall;Ljava/lang/Object;)Ljava/lang/Object;", "visitBlock", "block", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "(Lorg/jetbrains/kotlin/fir/expressions/FirBlock;Ljava/lang/Object;)Ljava/lang/Object;", "visitLazyBlock", "lazyBlock", "Lorg/jetbrains/kotlin/fir/expressions/FirLazyBlock;", "(Lorg/jetbrains/kotlin/fir/expressions/FirLazyBlock;Ljava/lang/Object;)Ljava/lang/Object;", "visitBooleanOperatorExpression", "booleanOperatorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitJump", "E", "Lorg/jetbrains/kotlin/fir/FirTargetElement;", "jump", "Lorg/jetbrains/kotlin/fir/expressions/FirJump;", "(Lorg/jetbrains/kotlin/fir/expressions/FirJump;Ljava/lang/Object;)Ljava/lang/Object;", "visitLoopJump", "loopJump", "Lorg/jetbrains/kotlin/fir/expressions/FirLoopJump;", "(Lorg/jetbrains/kotlin/fir/expressions/FirLoopJump;Ljava/lang/Object;)Ljava/lang/Object;", "visitBreakExpression", "breakExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBreakExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirBreakExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitContinueExpression", "continueExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirContinueExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirContinueExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitReturnExpression", "returnExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitWhileLoop", "whileLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirWhileLoop;", "(Lorg/jetbrains/kotlin/fir/expressions/FirWhileLoop;Ljava/lang/Object;)Ljava/lang/Object;", "visitDoWhileLoop", "doWhileLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirDoWhileLoop;", "(Lorg/jetbrains/kotlin/fir/expressions/FirDoWhileLoop;Ljava/lang/Object;)Ljava/lang/Object;", "visitLiteralExpression", "literalExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitIntegerLiteralOperatorCall", "integerLiteralOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirIntegerLiteralOperatorCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirIntegerLiteralOperatorCall;Ljava/lang/Object;)Ljava/lang/Object;", "visitComparisonExpression", "comparisonExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirComparisonExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirComparisonExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitAugmentedAssignment", "augmentedAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirAugmentedAssignment;", "(Lorg/jetbrains/kotlin/fir/expressions/FirAugmentedAssignment;Ljava/lang/Object;)Ljava/lang/Object;", "visitIncrementDecrementExpression", "incrementDecrementExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirIncrementDecrementExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirIncrementDecrementExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitRegularClass", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnonymousObjectExpression", "anonymousObjectExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousObjectExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousObjectExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeAlias", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "(Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnonymousFunctionExpression", "anonymousFunctionExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitConstructedClassTypeParameterRef", "constructedClassTypeParameterRef", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructedClassTypeParameterRef;", "(Lorg/jetbrains/kotlin/fir/declarations/FirConstructedClassTypeParameterRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitOuterClassTypeParameterRef", "outerClassTypeParameterRef", "Lorg/jetbrains/kotlin/fir/declarations/FirOuterClassTypeParameterRef;", "(Lorg/jetbrains/kotlin/fir/declarations/FirOuterClassTypeParameterRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitResolvedDeclarationStatus", "resolvedDeclarationStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;Ljava/lang/Object;)Ljava/lang/Object;", "visitImplicitInvokeCall", "implicitInvokeCall", "Lorg/jetbrains/kotlin/fir/expressions/FirImplicitInvokeCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirImplicitInvokeCall;Ljava/lang/Object;)Ljava/lang/Object;", "visitMultiDelegatedConstructorCall", "multiDelegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirMultiDelegatedConstructorCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirMultiDelegatedConstructorCall;Ljava/lang/Object;)Ljava/lang/Object;", "visitReceiverParameter", "receiverParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "(Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;Ljava/lang/Object;)Ljava/lang/Object;", "visitScriptReceiverParameter", "scriptReceiverParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirScriptReceiverParameter;", "(Lorg/jetbrains/kotlin/fir/declarations/FirScriptReceiverParameter;Ljava/lang/Object;)Ljava/lang/Object;", "visitEnumEntry", "enumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "(Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;Ljava/lang/Object;)Ljava/lang/Object;", "visitCodeFragment", "codeFragment", "Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;", "(Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;Ljava/lang/Object;)Ljava/lang/Object;", "visitReplSnippet", "replSnippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "(Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;Ljava/lang/Object;)Ljava/lang/Object;", "visitReplDeclarationReference", "replDeclarationReference", "Lorg/jetbrains/kotlin/fir/expressions/FirReplDeclarationReference;", "(Lorg/jetbrains/kotlin/fir/expressions/FirReplDeclarationReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitReplExpressionReference", "replExpressionReference", "Lorg/jetbrains/kotlin/fir/expressions/FirReplExpressionReference;", "(Lorg/jetbrains/kotlin/fir/expressions/FirReplExpressionReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitReplPropertyInitializer", "replPropertyInitializer", "Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyInitializer;", "(Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyInitializer;Ljava/lang/Object;)Ljava/lang/Object;", "visitReplPropertyDelegate", "replPropertyDelegate", "Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyDelegate;", "(Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyDelegate;Ljava/lang/Object;)Ljava/lang/Object;", "visitResolvedImport", "resolvedImport", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedImport;", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvedImport;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnnotation", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;Ljava/lang/Object;)Ljava/lang/Object;", "visitIndexedAccessAugmentedAssignment", "indexedAccessAugmentedAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirIndexedAccessAugmentedAssignment;", "(Lorg/jetbrains/kotlin/fir/expressions/FirIndexedAccessAugmentedAssignment;Ljava/lang/Object;)Ljava/lang/Object;", "visitClassReferenceExpression", "classReferenceExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirClassReferenceExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirClassReferenceExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitComponentCall", "componentCall", "Lorg/jetbrains/kotlin/fir/expressions/FirComponentCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirComponentCall;Ljava/lang/Object;)Ljava/lang/Object;", "visitSmartCastExpression", "smartCastExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitSafeCallExpression", "safeCallExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitCheckedSafeCallSubject", "checkedSafeCallSubject", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckedSafeCallSubject;", "(Lorg/jetbrains/kotlin/fir/expressions/FirCheckedSafeCallSubject;Ljava/lang/Object;)Ljava/lang/Object;", "visitCallableReferenceAccess", "callableReferenceAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "(Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertyAccessExpression", "propertyAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitWrappedArgumentExpression", "wrappedArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWrappedArgumentExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirWrappedArgumentExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitSpreadArgumentExpression", "spreadArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSpreadArgumentExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirSpreadArgumentExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitNamedArgumentExpression", "namedArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirNamedArgumentExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirNamedArgumentExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitVarargArgumentsExpression", "varargArgumentsExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirVarargArgumentsExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirVarargArgumentsExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitFunctionTypeConversionExpression", "functionTypeConversionExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionTypeConversionExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirFunctionTypeConversionExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitResolvedQualifier", "resolvedQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "(Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;Ljava/lang/Object;)Ljava/lang/Object;", "visitResolvedReifiedParameterReference", "resolvedReifiedParameterReference", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedReifiedParameterReference;", "(Lorg/jetbrains/kotlin/fir/expressions/FirResolvedReifiedParameterReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitThrowExpression", "throwExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThrowExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirThrowExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitVariableAssignment", "variableAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "(Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;Ljava/lang/Object;)Ljava/lang/Object;", "visitWhenSubjectExpression", "whenSubjectExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenSubjectExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirWhenSubjectExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitDesugaredAssignmentValueReferenceExpression", "desugaredAssignmentValueReferenceExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirDesugaredAssignmentValueReferenceExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirDesugaredAssignmentValueReferenceExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitWrappedExpression", "wrappedExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWrappedExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirWrappedExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitWrappedDelegateExpression", "wrappedDelegateExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWrappedDelegateExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirWrappedDelegateExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitEnumEntryDeserializedAccessExpression", "enumEntryDeserializedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirEnumEntryDeserializedAccessExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirEnumEntryDeserializedAccessExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitNamedReference", "namedReference", "Lorg/jetbrains/kotlin/fir/references/FirNamedReference;", "(Lorg/jetbrains/kotlin/fir/references/FirNamedReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitNamedReferenceWithCandidateBase", "namedReferenceWithCandidateBase", "Lorg/jetbrains/kotlin/fir/references/FirNamedReferenceWithCandidateBase;", "(Lorg/jetbrains/kotlin/fir/references/FirNamedReferenceWithCandidateBase;Ljava/lang/Object;)Ljava/lang/Object;", "visitResolvedNamedReference", "resolvedNamedReference", "Lorg/jetbrains/kotlin/fir/references/FirResolvedNamedReference;", "(Lorg/jetbrains/kotlin/fir/references/FirResolvedNamedReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertyWithExplicitBackingFieldResolvedNamedReference", "propertyWithExplicitBackingFieldResolvedNamedReference", "Lorg/jetbrains/kotlin/fir/references/FirPropertyWithExplicitBackingFieldResolvedNamedReference;", "(Lorg/jetbrains/kotlin/fir/references/FirPropertyWithExplicitBackingFieldResolvedNamedReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitResolvedCallableReference", "resolvedCallableReference", "Lorg/jetbrains/kotlin/fir/references/FirResolvedCallableReference;", "(Lorg/jetbrains/kotlin/fir/references/FirResolvedCallableReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitDelegateFieldReference", "delegateFieldReference", "Lorg/jetbrains/kotlin/fir/references/FirDelegateFieldReference;", "(Lorg/jetbrains/kotlin/fir/references/FirDelegateFieldReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitBackingFieldReference", "backingFieldReference", "Lorg/jetbrains/kotlin/fir/references/FirBackingFieldReference;", "(Lorg/jetbrains/kotlin/fir/references/FirBackingFieldReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitSuperReference", "superReference", "Lorg/jetbrains/kotlin/fir/references/FirSuperReference;", "(Lorg/jetbrains/kotlin/fir/references/FirSuperReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitThisReference", "thisReference", "Lorg/jetbrains/kotlin/fir/references/FirThisReference;", "(Lorg/jetbrains/kotlin/fir/references/FirThisReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitControlFlowGraphReference", "controlFlowGraphReference", "Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "(Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeRef", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitResolvedTypeRef", "resolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitUnresolvedTypeRef", "unresolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirUnresolvedTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirUnresolvedTypeRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitUserTypeRef", "userTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirUserTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirUserTypeRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitFunctionTypeRef", "functionTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitDynamicTypeRef", "dynamicTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirDynamicTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirDynamicTypeRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitImplicitTypeRef", "implicitTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirImplicitTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirImplicitTypeRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitResolvedErrorReference", "resolvedErrorReference", "Lorg/jetbrains/kotlin/fir/references/FirResolvedErrorReference;", "(Lorg/jetbrains/kotlin/fir/references/FirResolvedErrorReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitIntersectionTypeRef", "intersectionTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirIntersectionTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirIntersectionTypeRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitThisReceiverExpression", "thisReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitSuperReceiverExpression", "superReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSuperReceiverExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirSuperReceiverExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeProjectionWithVariance", "typeProjectionWithVariance", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjectionWithVariance;", "(Lorg/jetbrains/kotlin/fir/types/FirTypeProjectionWithVariance;Ljava/lang/Object;)Ljava/lang/Object;", "visitStarProjection", "starProjection", "Lorg/jetbrains/kotlin/fir/types/FirStarProjection;", "(Lorg/jetbrains/kotlin/fir/types/FirStarProjection;Ljava/lang/Object;)Ljava/lang/Object;", "visitPlaceholderProjection", "placeholderProjection", "Lorg/jetbrains/kotlin/fir/types/FirPlaceholderProjection;", "(Lorg/jetbrains/kotlin/fir/types/FirPlaceholderProjection;Ljava/lang/Object;)Ljava/lang/Object;", "visitEffectDeclaration", "effectDeclaration", "Lorg/jetbrains/kotlin/fir/contracts/FirEffectDeclaration;", "(Lorg/jetbrains/kotlin/fir/contracts/FirEffectDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitRawContractDescription", "rawContractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirRawContractDescription;", "(Lorg/jetbrains/kotlin/fir/contracts/FirRawContractDescription;Ljava/lang/Object;)Ljava/lang/Object;", "visitResolvedContractDescription", "resolvedContractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirResolvedContractDescription;", "(Lorg/jetbrains/kotlin/fir/contracts/FirResolvedContractDescription;Ljava/lang/Object;)Ljava/lang/Object;", "visitLegacyRawContractDescription", "legacyRawContractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirLegacyRawContractDescription;", "(Lorg/jetbrains/kotlin/fir/contracts/FirLegacyRawContractDescription;Ljava/lang/Object;)Ljava/lang/Object;", "visitLazyContractDescription", "lazyContractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirLazyContractDescription;", "(Lorg/jetbrains/kotlin/fir/contracts/FirLazyContractDescription;Ljava/lang/Object;)Ljava/lang/Object;", "visitErrorContractDescription", "errorContractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirErrorContractDescription;", "(Lorg/jetbrains/kotlin/fir/contracts/FirErrorContractDescription;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirDefaultVisitor<R, D> extends FirVisitor<R, D> {
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitAnnotation(FirAnnotation annotation, D data) {
        annotation.getClass();
        return visitExpression(annotation, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitAnonymousFunctionExpression(FirAnonymousFunctionExpression anonymousFunctionExpression, D data) {
        anonymousFunctionExpression.getClass();
        return visitExpression(anonymousFunctionExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitAnonymousObject(FirAnonymousObject anonymousObject, D data) {
        anonymousObject.getClass();
        return visitClass(anonymousObject, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitAnonymousObjectExpression(FirAnonymousObjectExpression anonymousObjectExpression, D data) {
        anonymousObjectExpression.getClass();
        return visitExpression(anonymousObjectExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitAugmentedAssignment(FirAugmentedAssignment augmentedAssignment, D data) {
        augmentedAssignment.getClass();
        return visitStatement(augmentedAssignment, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitBackingFieldReference(FirBackingFieldReference backingFieldReference, D data) {
        backingFieldReference.getClass();
        return visitResolvedNamedReference(backingFieldReference, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitBlock(FirBlock block, D data) {
        block.getClass();
        return visitExpression(block, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitBooleanOperatorExpression(FirBooleanOperatorExpression booleanOperatorExpression, D data) {
        booleanOperatorExpression.getClass();
        return visitExpression(booleanOperatorExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitBreakExpression(FirBreakExpression breakExpression, D data) {
        breakExpression.getClass();
        return visitLoopJump(breakExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitCall(FirCall call, D data) {
        call.getClass();
        return visitStatement(call, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitCallableDeclaration(FirCallableDeclaration callableDeclaration, D data) {
        callableDeclaration.getClass();
        return visitMemberDeclaration(callableDeclaration, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitCallableReferenceAccess(FirCallableReferenceAccess callableReferenceAccess, D data) {
        callableReferenceAccess.getClass();
        return visitQualifiedAccessExpression(callableReferenceAccess, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitCheckedSafeCallSubject(FirCheckedSafeCallSubject checkedSafeCallSubject, D data) {
        checkedSafeCallSubject.getClass();
        return visitExpression(checkedSafeCallSubject, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitClassReferenceExpression(FirClassReferenceExpression classReferenceExpression, D data) {
        classReferenceExpression.getClass();
        return visitExpression(classReferenceExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitCodeFragment(FirCodeFragment codeFragment, D data) {
        codeFragment.getClass();
        return visitDeclaration(codeFragment, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitComparisonExpression(FirComparisonExpression comparisonExpression, D data) {
        comparisonExpression.getClass();
        return visitExpression(comparisonExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitComponentCall(FirComponentCall componentCall, D data) {
        componentCall.getClass();
        return visitFunctionCall(componentCall, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitConstructedClassTypeParameterRef(FirConstructedClassTypeParameterRef constructedClassTypeParameterRef, D data) {
        constructedClassTypeParameterRef.getClass();
        return visitTypeParameterRef(constructedClassTypeParameterRef, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitContinueExpression(FirContinueExpression continueExpression, D data) {
        continueExpression.getClass();
        return visitLoopJump(continueExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitControlFlowGraphReference(FirControlFlowGraphReference controlFlowGraphReference, D data) {
        controlFlowGraphReference.getClass();
        return visitReference(controlFlowGraphReference, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitDelegateFieldReference(FirDelegateFieldReference delegateFieldReference, D data) {
        delegateFieldReference.getClass();
        return visitResolvedNamedReference(delegateFieldReference, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitDesugaredAssignmentValueReferenceExpression(FirDesugaredAssignmentValueReferenceExpression desugaredAssignmentValueReferenceExpression, D data) {
        desugaredAssignmentValueReferenceExpression.getClass();
        return visitExpression(desugaredAssignmentValueReferenceExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitDoWhileLoop(FirDoWhileLoop doWhileLoop, D data) {
        doWhileLoop.getClass();
        return visitLoop(doWhileLoop, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitDynamicTypeRef(FirDynamicTypeRef dynamicTypeRef, D data) {
        dynamicTypeRef.getClass();
        return visitUnresolvedTypeRef(dynamicTypeRef, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitEffectDeclaration(FirEffectDeclaration effectDeclaration, D data) {
        effectDeclaration.getClass();
        return visitContractElementDeclaration(effectDeclaration, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitEnumEntry(FirEnumEntry enumEntry, D data) {
        enumEntry.getClass();
        return visitVariable(enumEntry, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitEnumEntryDeserializedAccessExpression(FirEnumEntryDeserializedAccessExpression enumEntryDeserializedAccessExpression, D data) {
        enumEntryDeserializedAccessExpression.getClass();
        return visitExpression(enumEntryDeserializedAccessExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitErrorContractDescription(FirErrorContractDescription errorContractDescription, D data) {
        errorContractDescription.getClass();
        return visitContractDescription(errorContractDescription, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitExpression(FirExpression expression, D data) {
        expression.getClass();
        return visitStatement(expression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitFunctionTypeConversionExpression(FirFunctionTypeConversionExpression functionTypeConversionExpression, D data) {
        functionTypeConversionExpression.getClass();
        return visitExpression(functionTypeConversionExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitFunctionTypeRef(FirFunctionTypeRef functionTypeRef, D data) {
        functionTypeRef.getClass();
        return visitUnresolvedTypeRef(functionTypeRef, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitImplicitInvokeCall(FirImplicitInvokeCall implicitInvokeCall, D data) {
        implicitInvokeCall.getClass();
        return visitFunctionCall(implicitInvokeCall, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitImplicitTypeRef(FirImplicitTypeRef implicitTypeRef, D data) {
        implicitTypeRef.getClass();
        return visitTypeRef(implicitTypeRef, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitIncrementDecrementExpression(FirIncrementDecrementExpression incrementDecrementExpression, D data) {
        incrementDecrementExpression.getClass();
        return visitExpression(incrementDecrementExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitIndexedAccessAugmentedAssignment(FirIndexedAccessAugmentedAssignment indexedAccessAugmentedAssignment, D data) {
        indexedAccessAugmentedAssignment.getClass();
        return visitStatement(indexedAccessAugmentedAssignment, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitIntegerLiteralOperatorCall(FirIntegerLiteralOperatorCall integerLiteralOperatorCall, D data) {
        integerLiteralOperatorCall.getClass();
        return visitFunctionCall(integerLiteralOperatorCall, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitIntersectionTypeRef(FirIntersectionTypeRef intersectionTypeRef, D data) {
        intersectionTypeRef.getClass();
        return visitUnresolvedTypeRef(intersectionTypeRef, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public <E extends FirTargetElement> R visitJump(FirJump<E> jump, D data) {
        jump.getClass();
        return visitExpression(jump, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitLazyBlock(FirLazyBlock lazyBlock, D data) {
        lazyBlock.getClass();
        return visitBlock(lazyBlock, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitLazyContractDescription(FirLazyContractDescription lazyContractDescription, D data) {
        lazyContractDescription.getClass();
        return visitLegacyRawContractDescription(lazyContractDescription, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitLazyExpression(FirLazyExpression lazyExpression, D data) {
        lazyExpression.getClass();
        return visitExpression(lazyExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitLegacyRawContractDescription(FirLegacyRawContractDescription legacyRawContractDescription, D data) {
        legacyRawContractDescription.getClass();
        return visitContractDescription(legacyRawContractDescription, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitLiteralExpression(FirLiteralExpression literalExpression, D data) {
        literalExpression.getClass();
        return visitExpression(literalExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitLoopJump(FirLoopJump loopJump, D data) {
        loopJump.getClass();
        return visitJump(loopJump, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitMultiDelegatedConstructorCall(FirMultiDelegatedConstructorCall multiDelegatedConstructorCall, D data) {
        multiDelegatedConstructorCall.getClass();
        return visitDelegatedConstructorCall(multiDelegatedConstructorCall, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitNamedArgumentExpression(FirNamedArgumentExpression namedArgumentExpression, D data) {
        namedArgumentExpression.getClass();
        return visitWrappedArgumentExpression(namedArgumentExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitNamedReference(FirNamedReference namedReference, D data) {
        namedReference.getClass();
        return visitReference(namedReference, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitNamedReferenceWithCandidateBase(FirNamedReferenceWithCandidateBase namedReferenceWithCandidateBase, D data) {
        namedReferenceWithCandidateBase.getClass();
        return visitNamedReference(namedReferenceWithCandidateBase, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitOuterClassTypeParameterRef(FirOuterClassTypeParameterRef outerClassTypeParameterRef, D data) {
        outerClassTypeParameterRef.getClass();
        return visitTypeParameterRef(outerClassTypeParameterRef, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitPlaceholderProjection(FirPlaceholderProjection placeholderProjection, D data) {
        placeholderProjection.getClass();
        return visitTypeProjection(placeholderProjection, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitPropertyAccessExpression(FirPropertyAccessExpression propertyAccessExpression, D data) {
        propertyAccessExpression.getClass();
        return visitQualifiedAccessExpression(propertyAccessExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitPropertyWithExplicitBackingFieldResolvedNamedReference(FirPropertyWithExplicitBackingFieldResolvedNamedReference propertyWithExplicitBackingFieldResolvedNamedReference, D data) {
        propertyWithExplicitBackingFieldResolvedNamedReference.getClass();
        return visitResolvedNamedReference(propertyWithExplicitBackingFieldResolvedNamedReference, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitRawContractDescription(FirRawContractDescription rawContractDescription, D data) {
        rawContractDescription.getClass();
        return visitContractDescription(rawContractDescription, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitReceiverParameter(FirReceiverParameter receiverParameter, D data) {
        receiverParameter.getClass();
        return visitDeclaration(receiverParameter, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitRegularClass(FirRegularClass regularClass, D data) {
        regularClass.getClass();
        return visitClass(regularClass, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitReplDeclarationReference(FirReplDeclarationReference replDeclarationReference, D data) {
        replDeclarationReference.getClass();
        return visitStatement(replDeclarationReference, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitReplExpressionReference(FirReplExpressionReference replExpressionReference, D data) {
        replExpressionReference.getClass();
        return visitExpression(replExpressionReference, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitReplPropertyDelegate(FirReplPropertyDelegate replPropertyDelegate, D data) {
        replPropertyDelegate.getClass();
        return visitStatement(replPropertyDelegate, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitReplPropertyInitializer(FirReplPropertyInitializer replPropertyInitializer, D data) {
        replPropertyInitializer.getClass();
        return visitStatement(replPropertyInitializer, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitReplSnippet(FirReplSnippet replSnippet, D data) {
        replSnippet.getClass();
        return visitDeclaration(replSnippet, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitResolvedCallableReference(FirResolvedCallableReference resolvedCallableReference, D data) {
        resolvedCallableReference.getClass();
        return visitResolvedNamedReference(resolvedCallableReference, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitResolvedContractDescription(FirResolvedContractDescription resolvedContractDescription, D data) {
        resolvedContractDescription.getClass();
        return visitContractDescription(resolvedContractDescription, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitResolvedDeclarationStatus(FirResolvedDeclarationStatus resolvedDeclarationStatus, D data) {
        resolvedDeclarationStatus.getClass();
        return visitDeclarationStatus(resolvedDeclarationStatus, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitResolvedErrorReference(FirResolvedErrorReference resolvedErrorReference, D data) {
        resolvedErrorReference.getClass();
        return visitResolvedNamedReference(resolvedErrorReference, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitResolvedImport(FirResolvedImport resolvedImport, D data) {
        resolvedImport.getClass();
        return visitImport(resolvedImport, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitResolvedNamedReference(FirResolvedNamedReference resolvedNamedReference, D data) {
        resolvedNamedReference.getClass();
        return visitNamedReference(resolvedNamedReference, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitResolvedQualifier(FirResolvedQualifier resolvedQualifier, D data) {
        resolvedQualifier.getClass();
        return visitExpression(resolvedQualifier, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitResolvedReifiedParameterReference(FirResolvedReifiedParameterReference resolvedReifiedParameterReference, D data) {
        resolvedReifiedParameterReference.getClass();
        return visitExpression(resolvedReifiedParameterReference, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitResolvedTypeRef(FirResolvedTypeRef resolvedTypeRef, D data) {
        resolvedTypeRef.getClass();
        return visitTypeRef(resolvedTypeRef, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitReturnExpression(FirReturnExpression returnExpression, D data) {
        returnExpression.getClass();
        return visitJump(returnExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitSafeCallExpression(FirSafeCallExpression safeCallExpression, D data) {
        safeCallExpression.getClass();
        return visitExpression(safeCallExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitScriptReceiverParameter(FirScriptReceiverParameter scriptReceiverParameter, D data) {
        scriptReceiverParameter.getClass();
        return visitReceiverParameter(scriptReceiverParameter, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitSmartCastExpression(FirSmartCastExpression smartCastExpression, D data) {
        smartCastExpression.getClass();
        return visitExpression(smartCastExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitSpreadArgumentExpression(FirSpreadArgumentExpression spreadArgumentExpression, D data) {
        spreadArgumentExpression.getClass();
        return visitWrappedArgumentExpression(spreadArgumentExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitStarProjection(FirStarProjection starProjection, D data) {
        starProjection.getClass();
        return visitTypeProjection(starProjection, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitStatement(FirStatement statement, D data) {
        statement.getClass();
        return visitAnnotationContainer(statement, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitSuperReceiverExpression(FirSuperReceiverExpression superReceiverExpression, D data) {
        superReceiverExpression.getClass();
        return visitQualifiedAccessExpression(superReceiverExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitSuperReference(FirSuperReference superReference, D data) {
        superReference.getClass();
        return visitReference(superReference, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitThisReceiverExpression(FirThisReceiverExpression thisReceiverExpression, D data) {
        thisReceiverExpression.getClass();
        return visitQualifiedAccessExpression(thisReceiverExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitThisReference(FirThisReference thisReference, D data) {
        thisReference.getClass();
        return visitReference(thisReference, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitThrowExpression(FirThrowExpression throwExpression, D data) {
        throwExpression.getClass();
        return visitExpression(throwExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitTypeAlias(FirTypeAlias typeAlias, D data) {
        typeAlias.getClass();
        return visitClassLikeDeclaration(typeAlias, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitTypeParametersOwner(FirTypeParametersOwner typeParametersOwner, D data) {
        typeParametersOwner.getClass();
        return visitTypeParameterRefsOwner(typeParametersOwner, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitTypeProjectionWithVariance(FirTypeProjectionWithVariance typeProjectionWithVariance, D data) {
        typeProjectionWithVariance.getClass();
        return visitTypeProjection(typeProjectionWithVariance, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitTypeRef(FirTypeRef typeRef, D data) {
        typeRef.getClass();
        return visitAnnotationContainer(typeRef, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitUnresolvedTypeRef(FirUnresolvedTypeRef unresolvedTypeRef, D data) {
        unresolvedTypeRef.getClass();
        return visitTypeRef(unresolvedTypeRef, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitUserTypeRef(FirUserTypeRef userTypeRef, D data) {
        userTypeRef.getClass();
        return visitUnresolvedTypeRef(userTypeRef, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitVarargArgumentsExpression(FirVarargArgumentsExpression varargArgumentsExpression, D data) {
        varargArgumentsExpression.getClass();
        return visitExpression(varargArgumentsExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitVariableAssignment(FirVariableAssignment variableAssignment, D data) {
        variableAssignment.getClass();
        return visitStatement(variableAssignment, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitWhenSubjectExpression(FirWhenSubjectExpression whenSubjectExpression, D data) {
        whenSubjectExpression.getClass();
        return visitPropertyAccessExpression(whenSubjectExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitWhileLoop(FirWhileLoop whileLoop, D data) {
        whileLoop.getClass();
        return visitLoop(whileLoop, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitWrappedArgumentExpression(FirWrappedArgumentExpression wrappedArgumentExpression, D data) {
        wrappedArgumentExpression.getClass();
        return visitWrappedExpression(wrappedArgumentExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitWrappedDelegateExpression(FirWrappedDelegateExpression wrappedDelegateExpression, D data) {
        wrappedDelegateExpression.getClass();
        return visitWrappedExpression(wrappedDelegateExpression, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public R visitWrappedExpression(FirWrappedExpression wrappedExpression, D data) {
        wrappedExpression.getClass();
        return visitExpression(wrappedExpression, data);
    }
}
