package org.jetbrains.kotlin.fir.visitors;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirElementWithResolveState;
import org.jetbrains.kotlin.fir.FirFunctionTypeParameter;
import org.jetbrains.kotlin.fir.FirLabel;
import org.jetbrains.kotlin.fir.FirPackageDirective;
import org.jetbrains.kotlin.fir.FirTargetElement;
import org.jetbrains.kotlin.fir.contracts.FirContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirContractElementDeclaration;
import org.jetbrains.kotlin.fir.contracts.FirEffectDeclaration;
import org.jetbrains.kotlin.fir.contracts.FirErrorContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirLazyContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirLegacyRawContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirRawContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirResolvedContractDescription;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirClassLikeDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirConstructedClassTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirContractDescriptionOwner;
import org.jetbrains.kotlin.fir.declarations.FirControlFlowGraphOwner;
import org.jetbrains.kotlin.fir.declarations.FirDanglingModifierList;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirErrorFunction;
import org.jetbrains.kotlin.fir.declarations.FirErrorPrimaryConstructor;
import org.jetbrains.kotlin.fir.declarations.FirErrorProperty;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirImport;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirOuterClassTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirResolvedDeclarationStatus;
import org.jetbrains.kotlin.fir.declarations.FirResolvedImport;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationArgumentMapping;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousObjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirAugmentedAssignment;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirBooleanOperatorExpression;
import org.jetbrains.kotlin.fir.expressions.FirBreakExpression;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCatch;
import org.jetbrains.kotlin.fir.expressions.FirCheckNotNullCall;
import org.jetbrains.kotlin.fir.expressions.FirCheckedSafeCallSubject;
import org.jetbrains.kotlin.fir.expressions.FirClassReferenceExpression;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirComparisonExpression;
import org.jetbrains.kotlin.fir.expressions.FirComponentCall;
import org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner;
import org.jetbrains.kotlin.fir.expressions.FirContinueExpression;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirDesugaredAssignmentValueReferenceExpression;
import org.jetbrains.kotlin.fir.expressions.FirDoWhileLoop;
import org.jetbrains.kotlin.fir.expressions.FirElvisExpression;
import org.jetbrains.kotlin.fir.expressions.FirEnumEntryDeserializedAccessExpression;
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
import org.jetbrains.kotlin.fir.expressions.FirIncrementDecrementExpression;
import org.jetbrains.kotlin.fir.expressions.FirIndexedAccessAugmentedAssignment;
import org.jetbrains.kotlin.fir.expressions.FirIntegerLiteralOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirJump;
import org.jetbrains.kotlin.fir.expressions.FirLazyBlock;
import org.jetbrains.kotlin.fir.expressions.FirLazyExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirLoop;
import org.jetbrains.kotlin.fir.expressions.FirLoopJump;
import org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirNamedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedErrorAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifierWithContextSensitiveAlternative;
import org.jetbrains.kotlin.fir.expressions.FirReplDeclarationReference;
import org.jetbrains.kotlin.fir.expressions.FirReplExpressionReference;
import org.jetbrains.kotlin.fir.expressions.FirReplPropertyDelegate;
import org.jetbrains.kotlin.fir.expressions.FirReplPropertyInitializer;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
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
import org.jetbrains.kotlin.fir.expressions.FirWhenBranch;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhileLoop;
import org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirWrappedDelegateExpression;
import org.jetbrains.kotlin.fir.expressions.FirWrappedExpression;
import org.jetbrains.kotlin.fir.references.FirBackingFieldReference;
import org.jetbrains.kotlin.fir.references.FirControlFlowGraphReference;
import org.jetbrains.kotlin.fir.references.FirDelegateFieldReference;
import org.jetbrains.kotlin.fir.references.FirErrorNamedReference;
import org.jetbrains.kotlin.fir.references.FirErrorSuperReference;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirNamedReferenceWithCandidateBase;
import org.jetbrains.kotlin.fir.references.FirPropertyWithExplicitBackingFieldResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirResolvedCallableReference;
import org.jetbrains.kotlin.fir.references.FirResolvedErrorReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.FirSuperReference;
import org.jetbrains.kotlin.fir.references.FirThisReference;
import org.jetbrains.kotlin.fir.types.FirDynamicTypeRef;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirIntersectionTypeRef;
import org.jetbrains.kotlin.fir.types.FirPlaceholderProjection;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirStarProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000°\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u0001*\u0006\b\u0001\u0010\u0002 \u00002\u00020\u0003B\u0007¢\u0006\u0004\b\u0004\u0010\u0005J\u001d\u0010\u0006\u001a\u00028\u00002\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0001H&¢\u0006\u0002\u0010\nJ\u001d\u0010\u000b\u001a\u00028\u00002\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u000eJ\u001d\u0010\u000f\u001a\u00028\u00002\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0012J\u001d\u0010\u0013\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u0016J\u001d\u0010\u0017\u001a\u00028\u00002\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001aJ\u001d\u0010\u001b\u001a\u00028\u00002\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u001eJ\u001d\u0010\u001f\u001a\u00028\u00002\u0006\u0010 \u001a\u00020!2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\"J\u001d\u0010#\u001a\u00028\u00002\u0006\u0010$\u001a\u00020%2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010&J\u001d\u0010'\u001a\u00028\u00002\u0006\u0010(\u001a\u00020)2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010*J\u001d\u0010+\u001a\u00028\u00002\u0006\u0010,\u001a\u00020-2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010.J\u001d\u0010/\u001a\u00028\u00002\u0006\u00100\u001a\u0002012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u00102J\u001d\u00103\u001a\u00028\u00002\u0006\u00104\u001a\u0002052\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u00106J\u001d\u00107\u001a\u00028\u00002\u0006\u00108\u001a\u0002092\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010:J\u001d\u0010;\u001a\u00028\u00002\u0006\u0010<\u001a\u00020=2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010>J\u001d\u0010?\u001a\u00028\u00002\u0006\u0010@\u001a\u00020A2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010BJ\u001d\u0010C\u001a\u00028\u00002\u0006\u0010D\u001a\u00020E2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010FJ\u001d\u0010G\u001a\u00028\u00002\u0006\u0010H\u001a\u00020I2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010JJ\u001d\u0010K\u001a\u00028\u00002\u0006\u0010L\u001a\u00020M2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010NJ\u001d\u0010O\u001a\u00028\u00002\u0006\u0010P\u001a\u00020Q2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010RJ\u001d\u0010S\u001a\u00028\u00002\u0006\u0010T\u001a\u00020U2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010VJ\u001d\u0010W\u001a\u00028\u00002\u0006\u0010X\u001a\u00020Y2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010ZJ\u001d\u0010[\u001a\u00028\u00002\u0006\u0010\\\u001a\u00020]2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010^J\u001d\u0010_\u001a\u00028\u00002\u0006\u0010`\u001a\u00020a2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010bJ\u001d\u0010c\u001a\u00028\u00002\u0006\u0010d\u001a\u00020e2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010fJ-\u0010g\u001a\u00028\u0000\"\b\b\u0002\u0010h*\u00020e2\f\u0010i\u001a\b\u0012\u0004\u0012\u0002Hh0j2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010kJ\u001d\u0010l\u001a\u00028\u00002\u0006\u0010m\u001a\u00020n2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010oJ\u001d\u0010p\u001a\u00028\u00002\u0006\u0010q\u001a\u00020r2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010sJ\u001d\u0010t\u001a\u00028\u00002\u0006\u0010u\u001a\u00020v2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010wJ\u001d\u0010x\u001a\u00028\u00002\u0006\u0010y\u001a\u00020z2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010{J\u001d\u0010|\u001a\u00028\u00002\u0006\u0010}\u001a\u00020~2\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0002\u0010\u007fJ!\u0010\u0080\u0001\u001a\u00028\u00002\b\u0010\u0081\u0001\u001a\u00030\u0082\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0083\u0001J!\u0010\u0084\u0001\u001a\u00028\u00002\b\u0010\u0085\u0001\u001a\u00030\u0086\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0087\u0001J!\u0010\u0088\u0001\u001a\u00028\u00002\b\u0010\u0089\u0001\u001a\u00030\u008a\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u008b\u0001J!\u0010\u008c\u0001\u001a\u00028\u00002\b\u0010\u008d\u0001\u001a\u00030\u008e\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u008f\u0001J!\u0010\u0090\u0001\u001a\u00028\u00002\b\u0010\u0091\u0001\u001a\u00030\u0092\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0093\u0001J!\u0010\u0094\u0001\u001a\u00028\u00002\b\u0010\u0095\u0001\u001a\u00030\u0096\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0097\u0001J!\u0010\u0098\u0001\u001a\u00028\u00002\b\u0010\u0099\u0001\u001a\u00030\u009a\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u009b\u0001J!\u0010\u009c\u0001\u001a\u00028\u00002\b\u0010\u009d\u0001\u001a\u00030\u009e\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u009f\u0001J!\u0010 \u0001\u001a\u00028\u00002\b\u0010¡\u0001\u001a\u00030¢\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010£\u0001J!\u0010¤\u0001\u001a\u00028\u00002\b\u0010¥\u0001\u001a\u00030¦\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010§\u0001J!\u0010¨\u0001\u001a\u00028\u00002\b\u0010©\u0001\u001a\u00030ª\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010«\u0001J!\u0010¬\u0001\u001a\u00028\u00002\b\u0010\u00ad\u0001\u001a\u00030®\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¯\u0001J!\u0010°\u0001\u001a\u00028\u00002\b\u0010±\u0001\u001a\u00030²\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010³\u0001J!\u0010´\u0001\u001a\u00028\u00002\b\u0010µ\u0001\u001a\u00030¶\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010·\u0001J!\u0010¸\u0001\u001a\u00028\u00002\b\u0010¹\u0001\u001a\u00030º\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010»\u0001J!\u0010¼\u0001\u001a\u00028\u00002\b\u0010½\u0001\u001a\u00030¾\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¿\u0001J!\u0010À\u0001\u001a\u00028\u00002\b\u0010Á\u0001\u001a\u00030Â\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ã\u0001J!\u0010Ä\u0001\u001a\u00028\u00002\b\u0010Å\u0001\u001a\u00030Æ\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ç\u0001J!\u0010È\u0001\u001a\u00028\u00002\b\u0010É\u0001\u001a\u00030Ê\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ë\u0001J!\u0010Ì\u0001\u001a\u00028\u00002\b\u0010Í\u0001\u001a\u00030Î\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ï\u0001J!\u0010Ð\u0001\u001a\u00028\u00002\b\u0010Ñ\u0001\u001a\u00030Ò\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ó\u0001J!\u0010Ô\u0001\u001a\u00028\u00002\b\u0010Õ\u0001\u001a\u00030Ö\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010×\u0001J!\u0010Ø\u0001\u001a\u00028\u00002\b\u0010Ù\u0001\u001a\u00030Ú\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Û\u0001J!\u0010Ü\u0001\u001a\u00028\u00002\b\u0010Ý\u0001\u001a\u00030Þ\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ß\u0001J!\u0010à\u0001\u001a\u00028\u00002\b\u0010á\u0001\u001a\u00030â\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ã\u0001J!\u0010ä\u0001\u001a\u00028\u00002\b\u0010å\u0001\u001a\u00030æ\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ç\u0001J!\u0010è\u0001\u001a\u00028\u00002\b\u0010é\u0001\u001a\u00030ê\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ë\u0001J!\u0010ì\u0001\u001a\u00028\u00002\b\u0010í\u0001\u001a\u00030î\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ï\u0001J!\u0010ð\u0001\u001a\u00028\u00002\b\u0010ñ\u0001\u001a\u00030ò\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ó\u0001J!\u0010ô\u0001\u001a\u00028\u00002\b\u0010õ\u0001\u001a\u00030ö\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010÷\u0001J!\u0010ø\u0001\u001a\u00028\u00002\b\u0010ù\u0001\u001a\u00030ú\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010û\u0001J!\u0010ü\u0001\u001a\u00028\u00002\b\u0010ý\u0001\u001a\u00030þ\u00012\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ÿ\u0001J!\u0010\u0080\u0002\u001a\u00028\u00002\b\u0010\u0081\u0002\u001a\u00030\u0082\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0083\u0002J!\u0010\u0084\u0002\u001a\u00028\u00002\b\u0010\u0085\u0002\u001a\u00030\u0086\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0087\u0002J!\u0010\u0088\u0002\u001a\u00028\u00002\b\u0010\u0089\u0002\u001a\u00030\u008a\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u008b\u0002J!\u0010\u008c\u0002\u001a\u00028\u00002\b\u0010\u008d\u0002\u001a\u00030\u008e\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u008f\u0002J!\u0010\u0090\u0002\u001a\u00028\u00002\b\u0010\u0091\u0002\u001a\u00030\u0092\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0093\u0002J!\u0010\u0094\u0002\u001a\u00028\u00002\b\u0010\u0095\u0002\u001a\u00030\u0096\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0097\u0002J!\u0010\u0098\u0002\u001a\u00028\u00002\b\u0010\u0099\u0002\u001a\u00030\u009a\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u009b\u0002J!\u0010\u009c\u0002\u001a\u00028\u00002\b\u0010\u009d\u0002\u001a\u00030\u009e\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u009f\u0002J!\u0010 \u0002\u001a\u00028\u00002\b\u0010¡\u0002\u001a\u00030¢\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010£\u0002J!\u0010¤\u0002\u001a\u00028\u00002\b\u0010¥\u0002\u001a\u00030¦\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010§\u0002J!\u0010¨\u0002\u001a\u00028\u00002\b\u0010©\u0002\u001a\u00030ª\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010«\u0002J!\u0010¬\u0002\u001a\u00028\u00002\b\u0010\u00ad\u0002\u001a\u00030®\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¯\u0002J!\u0010°\u0002\u001a\u00028\u00002\b\u0010±\u0002\u001a\u00030²\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010³\u0002J!\u0010´\u0002\u001a\u00028\u00002\b\u0010µ\u0002\u001a\u00030¶\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010·\u0002J!\u0010¸\u0002\u001a\u00028\u00002\b\u0010¹\u0002\u001a\u00030º\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010»\u0002J!\u0010¼\u0002\u001a\u00028\u00002\b\u0010½\u0002\u001a\u00030¾\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¿\u0002J!\u0010À\u0002\u001a\u00028\u00002\b\u0010Á\u0002\u001a\u00030Â\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ã\u0002J!\u0010Ä\u0002\u001a\u00028\u00002\b\u0010Å\u0002\u001a\u00030Æ\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ç\u0002J!\u0010È\u0002\u001a\u00028\u00002\b\u0010É\u0002\u001a\u00030Ê\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ë\u0002J!\u0010Ì\u0002\u001a\u00028\u00002\b\u0010Í\u0002\u001a\u00030Î\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ï\u0002J!\u0010Ð\u0002\u001a\u00028\u00002\b\u0010Ñ\u0002\u001a\u00030Ò\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ó\u0002J!\u0010Ô\u0002\u001a\u00028\u00002\b\u0010Õ\u0002\u001a\u00030Ö\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010×\u0002J!\u0010Ø\u0002\u001a\u00028\u00002\b\u0010Ù\u0002\u001a\u00030Ú\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Û\u0002J!\u0010Ü\u0002\u001a\u00028\u00002\b\u0010Ý\u0002\u001a\u00030Þ\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ß\u0002J!\u0010à\u0002\u001a\u00028\u00002\b\u0010á\u0002\u001a\u00030â\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ã\u0002J!\u0010ä\u0002\u001a\u00028\u00002\b\u0010å\u0002\u001a\u00030æ\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ç\u0002J!\u0010è\u0002\u001a\u00028\u00002\b\u0010é\u0002\u001a\u00030ê\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ë\u0002J!\u0010ì\u0002\u001a\u00028\u00002\b\u0010í\u0002\u001a\u00030î\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ï\u0002J!\u0010ð\u0002\u001a\u00028\u00002\b\u0010ñ\u0002\u001a\u00030ò\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ó\u0002J!\u0010ô\u0002\u001a\u00028\u00002\b\u0010õ\u0002\u001a\u00030ö\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010÷\u0002J!\u0010ø\u0002\u001a\u00028\u00002\b\u0010ù\u0002\u001a\u00030ú\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010û\u0002J!\u0010ü\u0002\u001a\u00028\u00002\b\u0010ý\u0002\u001a\u00030þ\u00022\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ÿ\u0002J!\u0010\u0080\u0003\u001a\u00028\u00002\b\u0010\u0081\u0003\u001a\u00030\u0082\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0083\u0003J!\u0010\u0084\u0003\u001a\u00028\u00002\b\u0010\u0085\u0003\u001a\u00030\u0086\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0087\u0003J!\u0010\u0088\u0003\u001a\u00028\u00002\b\u0010\u0089\u0003\u001a\u00030\u008a\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u008b\u0003J!\u0010\u008c\u0003\u001a\u00028\u00002\b\u0010\u008d\u0003\u001a\u00030\u008e\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u008f\u0003J!\u0010\u0090\u0003\u001a\u00028\u00002\b\u0010\u0091\u0003\u001a\u00030\u0092\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0093\u0003J!\u0010\u0094\u0003\u001a\u00028\u00002\b\u0010\u0095\u0003\u001a\u00030\u0096\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0097\u0003J!\u0010\u0098\u0003\u001a\u00028\u00002\b\u0010\u0099\u0003\u001a\u00030\u009a\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u009b\u0003J!\u0010\u009c\u0003\u001a\u00028\u00002\b\u0010\u009d\u0003\u001a\u00030\u009e\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u009f\u0003J!\u0010 \u0003\u001a\u00028\u00002\b\u0010¡\u0003\u001a\u00030¢\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010£\u0003J!\u0010¤\u0003\u001a\u00028\u00002\b\u0010¥\u0003\u001a\u00030¦\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010§\u0003J!\u0010¨\u0003\u001a\u00028\u00002\b\u0010©\u0003\u001a\u00030ª\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010«\u0003J!\u0010¬\u0003\u001a\u00028\u00002\b\u0010\u00ad\u0003\u001a\u00030®\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¯\u0003J!\u0010°\u0003\u001a\u00028\u00002\b\u0010±\u0003\u001a\u00030²\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010³\u0003J!\u0010´\u0003\u001a\u00028\u00002\b\u0010µ\u0003\u001a\u00030¶\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010·\u0003J!\u0010¸\u0003\u001a\u00028\u00002\b\u0010¹\u0003\u001a\u00030º\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010»\u0003J!\u0010¼\u0003\u001a\u00028\u00002\b\u0010½\u0003\u001a\u00030¾\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¿\u0003J!\u0010À\u0003\u001a\u00028\u00002\b\u0010Á\u0003\u001a\u00030Â\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ã\u0003J!\u0010Ä\u0003\u001a\u00028\u00002\b\u0010Å\u0003\u001a\u00030Æ\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ç\u0003J!\u0010È\u0003\u001a\u00028\u00002\b\u0010É\u0003\u001a\u00030Ê\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ë\u0003J!\u0010Ì\u0003\u001a\u00028\u00002\b\u0010Í\u0003\u001a\u00030Î\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ï\u0003J!\u0010Ð\u0003\u001a\u00028\u00002\b\u0010Ñ\u0003\u001a\u00030Ò\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ó\u0003J!\u0010Ô\u0003\u001a\u00028\u00002\b\u0010Õ\u0003\u001a\u00030Ö\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010×\u0003J!\u0010Ø\u0003\u001a\u00028\u00002\b\u0010Ù\u0003\u001a\u00030Ú\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Û\u0003J!\u0010Ü\u0003\u001a\u00028\u00002\b\u0010Ý\u0003\u001a\u00030Þ\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ß\u0003J!\u0010à\u0003\u001a\u00028\u00002\b\u0010á\u0003\u001a\u00030â\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ã\u0003J!\u0010ä\u0003\u001a\u00028\u00002\b\u0010å\u0003\u001a\u00030æ\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ç\u0003J!\u0010è\u0003\u001a\u00028\u00002\b\u0010é\u0003\u001a\u00030ê\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ë\u0003J!\u0010ì\u0003\u001a\u00028\u00002\b\u0010í\u0003\u001a\u00030î\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ï\u0003J!\u0010ð\u0003\u001a\u00028\u00002\b\u0010ñ\u0003\u001a\u00030ò\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ó\u0003J!\u0010ô\u0003\u001a\u00028\u00002\b\u0010õ\u0003\u001a\u00030ö\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010÷\u0003J!\u0010ø\u0003\u001a\u00028\u00002\b\u0010ù\u0003\u001a\u00030ú\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010û\u0003J!\u0010ü\u0003\u001a\u00028\u00002\b\u0010ý\u0003\u001a\u00030þ\u00032\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ÿ\u0003J!\u0010\u0080\u0004\u001a\u00028\u00002\b\u0010\u0081\u0004\u001a\u00030\u0082\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0083\u0004J!\u0010\u0084\u0004\u001a\u00028\u00002\b\u0010\u0085\u0004\u001a\u00030\u0086\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0087\u0004J!\u0010\u0088\u0004\u001a\u00028\u00002\b\u0010\u0089\u0004\u001a\u00030\u008a\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u008b\u0004J!\u0010\u008c\u0004\u001a\u00028\u00002\b\u0010\u008d\u0004\u001a\u00030\u008e\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u008f\u0004J!\u0010\u0090\u0004\u001a\u00028\u00002\b\u0010\u0091\u0004\u001a\u00030\u0092\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0093\u0004J!\u0010\u0094\u0004\u001a\u00028\u00002\b\u0010\u0095\u0004\u001a\u00030\u0096\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0097\u0004J!\u0010\u0098\u0004\u001a\u00028\u00002\b\u0010\u0099\u0004\u001a\u00030\u009a\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u009b\u0004J!\u0010\u009c\u0004\u001a\u00028\u00002\b\u0010\u009d\u0004\u001a\u00030\u009e\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u009f\u0004J!\u0010 \u0004\u001a\u00028\u00002\b\u0010¡\u0004\u001a\u00030¢\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010£\u0004J!\u0010¤\u0004\u001a\u00028\u00002\b\u0010¥\u0004\u001a\u00030¦\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010§\u0004J!\u0010¨\u0004\u001a\u00028\u00002\b\u0010©\u0004\u001a\u00030ª\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010«\u0004J!\u0010¬\u0004\u001a\u00028\u00002\b\u0010\u00ad\u0004\u001a\u00030®\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¯\u0004J!\u0010°\u0004\u001a\u00028\u00002\b\u0010±\u0004\u001a\u00030²\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010³\u0004J!\u0010´\u0004\u001a\u00028\u00002\b\u0010µ\u0004\u001a\u00030¶\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010·\u0004J!\u0010¸\u0004\u001a\u00028\u00002\b\u0010¹\u0004\u001a\u00030º\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010»\u0004J!\u0010¼\u0004\u001a\u00028\u00002\b\u0010½\u0004\u001a\u00030¾\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010¿\u0004J!\u0010À\u0004\u001a\u00028\u00002\b\u0010Á\u0004\u001a\u00030Â\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ã\u0004J!\u0010Ä\u0004\u001a\u00028\u00002\b\u0010Å\u0004\u001a\u00030Æ\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ç\u0004J!\u0010È\u0004\u001a\u00028\u00002\b\u0010É\u0004\u001a\u00030Ê\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ë\u0004J!\u0010Ì\u0004\u001a\u00028\u00002\b\u0010Í\u0004\u001a\u00030Î\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ï\u0004J!\u0010Ð\u0004\u001a\u00028\u00002\b\u0010Ñ\u0004\u001a\u00030Ò\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Ó\u0004J!\u0010Ô\u0004\u001a\u00028\u00002\b\u0010Õ\u0004\u001a\u00030Ö\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010×\u0004J!\u0010Ø\u0004\u001a\u00028\u00002\b\u0010Ù\u0004\u001a\u00030Ú\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010Û\u0004J!\u0010Ü\u0004\u001a\u00028\u00002\b\u0010Ý\u0004\u001a\u00030Þ\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ß\u0004J!\u0010à\u0004\u001a\u00028\u00002\b\u0010á\u0004\u001a\u00030â\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ã\u0004J!\u0010ä\u0004\u001a\u00028\u00002\b\u0010å\u0004\u001a\u00030æ\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ç\u0004J!\u0010è\u0004\u001a\u00028\u00002\b\u0010é\u0004\u001a\u00030ê\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ë\u0004J!\u0010ì\u0004\u001a\u00028\u00002\b\u0010í\u0004\u001a\u00030î\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ï\u0004J!\u0010ð\u0004\u001a\u00028\u00002\b\u0010ñ\u0004\u001a\u00030ò\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ó\u0004J!\u0010ô\u0004\u001a\u00028\u00002\b\u0010õ\u0004\u001a\u00030ö\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010÷\u0004J!\u0010ø\u0004\u001a\u00028\u00002\b\u0010ù\u0004\u001a\u00030ú\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010û\u0004J!\u0010ü\u0004\u001a\u00028\u00002\b\u0010ý\u0004\u001a\u00030þ\u00042\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010ÿ\u0004J!\u0010\u0080\u0005\u001a\u00028\u00002\b\u0010\u0081\u0005\u001a\u00030\u0082\u00052\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0083\u0005J!\u0010\u0084\u0005\u001a\u00028\u00002\b\u0010\u0085\u0005\u001a\u00030\u0086\u00052\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0087\u0005J!\u0010\u0088\u0005\u001a\u00028\u00002\b\u0010\u0089\u0005\u001a\u00030\u008a\u00052\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u008b\u0005J!\u0010\u008c\u0005\u001a\u00028\u00002\b\u0010\u008d\u0005\u001a\u00030\u008e\u00052\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u008f\u0005J!\u0010\u0090\u0005\u001a\u00028\u00002\b\u0010\u0091\u0005\u001a\u00030\u0092\u00052\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0093\u0005J!\u0010\u0094\u0005\u001a\u00028\u00002\b\u0010\u0095\u0005\u001a\u00030\u0096\u00052\u0006\u0010\t\u001a\u00028\u0001H\u0016¢\u0006\u0003\u0010\u0097\u0005¨\u0006\u0098\u0005"}, d2 = {"Lorg/jetbrains/kotlin/fir/visitors/FirVisitor;", "R", "D", Argument.Delimiters.none, "<init>", "()V", "visitElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "data", "(Lorg/jetbrains/kotlin/fir/FirElement;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnnotationContainer", "annotationContainer", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "(Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeParameterRef", "typeParameterRef", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "(Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeParametersOwner", "typeParametersOwner", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParametersOwner;", "(Lorg/jetbrains/kotlin/fir/declarations/FirTypeParametersOwner;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeParameterRefsOwner", "typeParameterRefsOwner", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRefsOwner;", "(Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRefsOwner;Ljava/lang/Object;)Ljava/lang/Object;", "visitResolvable", "resolvable", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvable;", "(Lorg/jetbrains/kotlin/fir/expressions/FirResolvable;Ljava/lang/Object;)Ljava/lang/Object;", "visitDiagnosticHolder", "diagnosticHolder", "Lorg/jetbrains/kotlin/fir/diagnostics/FirDiagnosticHolder;", "(Lorg/jetbrains/kotlin/fir/diagnostics/FirDiagnosticHolder;Ljava/lang/Object;)Ljava/lang/Object;", "visitControlFlowGraphOwner", "controlFlowGraphOwner", "Lorg/jetbrains/kotlin/fir/declarations/FirControlFlowGraphOwner;", "(Lorg/jetbrains/kotlin/fir/declarations/FirControlFlowGraphOwner;Ljava/lang/Object;)Ljava/lang/Object;", "visitElementWithResolveState", "elementWithResolveState", "Lorg/jetbrains/kotlin/fir/FirElementWithResolveState;", "(Lorg/jetbrains/kotlin/fir/FirElementWithResolveState;Ljava/lang/Object;)Ljava/lang/Object;", "visitDeclaration", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitCallableDeclaration", "callableDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitFunction", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;Ljava/lang/Object;)Ljava/lang/Object;", "visitErrorExpression", "errorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirErrorExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitErrorFunction", "errorFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorFunction;", "(Lorg/jetbrains/kotlin/fir/declarations/FirErrorFunction;Ljava/lang/Object;)Ljava/lang/Object;", "visitMemberDeclaration", "memberDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "(Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitStatement", "statement", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "(Lorg/jetbrains/kotlin/fir/expressions/FirStatement;Ljava/lang/Object;)Ljava/lang/Object;", "visitExpression", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitLazyExpression", "lazyExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirLazyExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirLazyExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitArgumentList", "argumentList", "Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;", "(Lorg/jetbrains/kotlin/fir/expressions/FirArgumentList;Ljava/lang/Object;)Ljava/lang/Object;", "visitCall", K2JsArgumentConstants.CALL, "Lorg/jetbrains/kotlin/fir/expressions/FirCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirCall;Ljava/lang/Object;)Ljava/lang/Object;", "visitBlock", "block", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "(Lorg/jetbrains/kotlin/fir/expressions/FirBlock;Ljava/lang/Object;)Ljava/lang/Object;", "visitLazyBlock", "lazyBlock", "Lorg/jetbrains/kotlin/fir/expressions/FirLazyBlock;", "(Lorg/jetbrains/kotlin/fir/expressions/FirLazyBlock;Ljava/lang/Object;)Ljava/lang/Object;", "visitBooleanOperatorExpression", "booleanOperatorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitTargetElement", "targetElement", "Lorg/jetbrains/kotlin/fir/FirTargetElement;", "(Lorg/jetbrains/kotlin/fir/FirTargetElement;Ljava/lang/Object;)Ljava/lang/Object;", "visitJump", "E", "jump", "Lorg/jetbrains/kotlin/fir/expressions/FirJump;", "(Lorg/jetbrains/kotlin/fir/expressions/FirJump;Ljava/lang/Object;)Ljava/lang/Object;", "visitLoopJump", "loopJump", "Lorg/jetbrains/kotlin/fir/expressions/FirLoopJump;", "(Lorg/jetbrains/kotlin/fir/expressions/FirLoopJump;Ljava/lang/Object;)Ljava/lang/Object;", "visitBreakExpression", "breakExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBreakExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirBreakExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitContinueExpression", "continueExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirContinueExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirContinueExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitReturnExpression", "returnExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitLabel", CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "Lorg/jetbrains/kotlin/fir/FirLabel;", "(Lorg/jetbrains/kotlin/fir/FirLabel;Ljava/lang/Object;)Ljava/lang/Object;", "visitLoop", "loop", "Lorg/jetbrains/kotlin/fir/expressions/FirLoop;", "(Lorg/jetbrains/kotlin/fir/expressions/FirLoop;Ljava/lang/Object;)Ljava/lang/Object;", "visitWhileLoop", "whileLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirWhileLoop;", "(Lorg/jetbrains/kotlin/fir/expressions/FirWhileLoop;Ljava/lang/Object;)Ljava/lang/Object;", "visitDoWhileLoop", "doWhileLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirDoWhileLoop;", "(Lorg/jetbrains/kotlin/fir/expressions/FirDoWhileLoop;Ljava/lang/Object;)Ljava/lang/Object;", "visitErrorLoop", "errorLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorLoop;", "(Lorg/jetbrains/kotlin/fir/expressions/FirErrorLoop;Ljava/lang/Object;)Ljava/lang/Object;", "visitCatch", "catch", "Lorg/jetbrains/kotlin/fir/expressions/FirCatch;", "(Lorg/jetbrains/kotlin/fir/expressions/FirCatch;Ljava/lang/Object;)Ljava/lang/Object;", "visitTryExpression", "tryExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitElvisExpression", "elvisExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirElvisExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirElvisExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitContextArgumentListOwner", "contextArgumentListOwner", "Lorg/jetbrains/kotlin/fir/expressions/FirContextArgumentListOwner;", "(Lorg/jetbrains/kotlin/fir/expressions/FirContextArgumentListOwner;Ljava/lang/Object;)Ljava/lang/Object;", "visitQualifiedAccessExpression", "qualifiedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitQualifiedErrorAccessExpression", "qualifiedErrorAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedErrorAccessExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedErrorAccessExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitLiteralExpression", "literalExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitFunctionCall", "functionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;Ljava/lang/Object;)Ljava/lang/Object;", "visitIntegerLiteralOperatorCall", "integerLiteralOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirIntegerLiteralOperatorCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirIntegerLiteralOperatorCall;Ljava/lang/Object;)Ljava/lang/Object;", "visitCollectionLiteral", "collectionLiteral", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "(Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;Ljava/lang/Object;)Ljava/lang/Object;", "visitCheckNotNullCall", "checkNotNullCall", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckNotNullCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirCheckNotNullCall;Ljava/lang/Object;)Ljava/lang/Object;", "visitComparisonExpression", "comparisonExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirComparisonExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirComparisonExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeOperatorCall", "typeOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;Ljava/lang/Object;)Ljava/lang/Object;", "visitAugmentedAssignment", "augmentedAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirAugmentedAssignment;", "(Lorg/jetbrains/kotlin/fir/expressions/FirAugmentedAssignment;Ljava/lang/Object;)Ljava/lang/Object;", "visitIncrementDecrementExpression", "incrementDecrementExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirIncrementDecrementExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirIncrementDecrementExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitEqualityOperatorCall", "equalityOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;Ljava/lang/Object;)Ljava/lang/Object;", "visitWhenBranch", "whenBranch", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenBranch;", "(Lorg/jetbrains/kotlin/fir/expressions/FirWhenBranch;Ljava/lang/Object;)Ljava/lang/Object;", "visitClassLikeDeclaration", "classLikeDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;", "(Lorg/jetbrains/kotlin/fir/declarations/FirClassLikeDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitClass", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "(Lorg/jetbrains/kotlin/fir/declarations/FirClass;Ljava/lang/Object;)Ljava/lang/Object;", "visitRegularClass", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "(Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnonymousObjectExpression", "anonymousObjectExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousObjectExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousObjectExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeAlias", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "(Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnonymousFunction", "anonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnonymousFunctionExpression", "anonymousFunctionExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeParameter", "typeParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "(Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;Ljava/lang/Object;)Ljava/lang/Object;", "visitConstructedClassTypeParameterRef", "constructedClassTypeParameterRef", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructedClassTypeParameterRef;", "(Lorg/jetbrains/kotlin/fir/declarations/FirConstructedClassTypeParameterRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitOuterClassTypeParameterRef", "outerClassTypeParameterRef", "Lorg/jetbrains/kotlin/fir/declarations/FirOuterClassTypeParameterRef;", "(Lorg/jetbrains/kotlin/fir/declarations/FirOuterClassTypeParameterRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitNamedFunction", "namedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "(Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;Ljava/lang/Object;)Ljava/lang/Object;", "visitContractDescriptionOwner", "contractDescriptionOwner", "Lorg/jetbrains/kotlin/fir/declarations/FirContractDescriptionOwner;", "(Lorg/jetbrains/kotlin/fir/declarations/FirContractDescriptionOwner;Ljava/lang/Object;)Ljava/lang/Object;", "visitProperty", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertyAccessor", "propertyAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "(Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;Ljava/lang/Object;)Ljava/lang/Object;", "visitBackingField", "backingField", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "(Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;Ljava/lang/Object;)Ljava/lang/Object;", "visitDeclarationStatus", "declarationStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;", "(Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationStatus;Ljava/lang/Object;)Ljava/lang/Object;", "visitResolvedDeclarationStatus", "resolvedDeclarationStatus", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvedDeclarationStatus;Ljava/lang/Object;)Ljava/lang/Object;", "visitImplicitInvokeCall", "implicitInvokeCall", "Lorg/jetbrains/kotlin/fir/expressions/FirImplicitInvokeCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirImplicitInvokeCall;Ljava/lang/Object;)Ljava/lang/Object;", "visitConstructor", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "(Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;Ljava/lang/Object;)Ljava/lang/Object;", "visitErrorPrimaryConstructor", "errorPrimaryConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorPrimaryConstructor;", "(Lorg/jetbrains/kotlin/fir/declarations/FirErrorPrimaryConstructor;Ljava/lang/Object;)Ljava/lang/Object;", "visitDelegatedConstructorCall", "delegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;Ljava/lang/Object;)Ljava/lang/Object;", "visitMultiDelegatedConstructorCall", "multiDelegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirMultiDelegatedConstructorCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirMultiDelegatedConstructorCall;Ljava/lang/Object;)Ljava/lang/Object;", "visitValueParameter", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "(Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;Ljava/lang/Object;)Ljava/lang/Object;", "visitReceiverParameter", "receiverParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "(Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;Ljava/lang/Object;)Ljava/lang/Object;", "visitScriptReceiverParameter", "scriptReceiverParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirScriptReceiverParameter;", "(Lorg/jetbrains/kotlin/fir/declarations/FirScriptReceiverParameter;Ljava/lang/Object;)Ljava/lang/Object;", "visitVariable", "variable", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "(Lorg/jetbrains/kotlin/fir/declarations/FirVariable;Ljava/lang/Object;)Ljava/lang/Object;", "visitFunctionTypeParameter", "functionTypeParameter", "Lorg/jetbrains/kotlin/fir/FirFunctionTypeParameter;", "(Lorg/jetbrains/kotlin/fir/FirFunctionTypeParameter;Ljava/lang/Object;)Ljava/lang/Object;", "visitErrorProperty", "errorProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorProperty;", "(Lorg/jetbrains/kotlin/fir/declarations/FirErrorProperty;Ljava/lang/Object;)Ljava/lang/Object;", "visitEnumEntry", "enumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "(Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;Ljava/lang/Object;)Ljava/lang/Object;", "visitField", "field", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "(Lorg/jetbrains/kotlin/fir/declarations/FirField;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnonymousInitializer", "anonymousInitializer", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "(Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;Ljava/lang/Object;)Ljava/lang/Object;", "visitDanglingModifierList", "danglingModifierList", "Lorg/jetbrains/kotlin/fir/declarations/FirDanglingModifierList;", "(Lorg/jetbrains/kotlin/fir/declarations/FirDanglingModifierList;Ljava/lang/Object;)Ljava/lang/Object;", "visitFile", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "(Lorg/jetbrains/kotlin/fir/declarations/FirFile;Ljava/lang/Object;)Ljava/lang/Object;", "visitScript", "script", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "(Lorg/jetbrains/kotlin/fir/declarations/FirScript;Ljava/lang/Object;)Ljava/lang/Object;", "visitCodeFragment", "codeFragment", "Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;", "(Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;Ljava/lang/Object;)Ljava/lang/Object;", "visitReplSnippet", "replSnippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "(Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;Ljava/lang/Object;)Ljava/lang/Object;", "visitReplDeclarationReference", "replDeclarationReference", "Lorg/jetbrains/kotlin/fir/expressions/FirReplDeclarationReference;", "(Lorg/jetbrains/kotlin/fir/expressions/FirReplDeclarationReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitReplExpressionReference", "replExpressionReference", "Lorg/jetbrains/kotlin/fir/expressions/FirReplExpressionReference;", "(Lorg/jetbrains/kotlin/fir/expressions/FirReplExpressionReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitReplPropertyInitializer", "replPropertyInitializer", "Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyInitializer;", "(Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyInitializer;Ljava/lang/Object;)Ljava/lang/Object;", "visitReplPropertyDelegate", "replPropertyDelegate", "Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyDelegate;", "(Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyDelegate;Ljava/lang/Object;)Ljava/lang/Object;", "visitPackageDirective", "packageDirective", "Lorg/jetbrains/kotlin/fir/FirPackageDirective;", "(Lorg/jetbrains/kotlin/fir/FirPackageDirective;Ljava/lang/Object;)Ljava/lang/Object;", "visitImport", "import", "Lorg/jetbrains/kotlin/fir/declarations/FirImport;", "(Lorg/jetbrains/kotlin/fir/declarations/FirImport;Ljava/lang/Object;)Ljava/lang/Object;", "visitResolvedImport", "resolvedImport", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvedImport;", "(Lorg/jetbrains/kotlin/fir/declarations/FirResolvedImport;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnnotation", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnnotationCall", "annotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;Ljava/lang/Object;)Ljava/lang/Object;", "visitErrorAnnotationCall", "errorAnnotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorAnnotationCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirErrorAnnotationCall;Ljava/lang/Object;)Ljava/lang/Object;", "visitAnnotationArgumentMapping", "annotationArgumentMapping", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;", "(Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationArgumentMapping;Ljava/lang/Object;)Ljava/lang/Object;", "visitIndexedAccessAugmentedAssignment", "indexedAccessAugmentedAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirIndexedAccessAugmentedAssignment;", "(Lorg/jetbrains/kotlin/fir/expressions/FirIndexedAccessAugmentedAssignment;Ljava/lang/Object;)Ljava/lang/Object;", "visitClassReferenceExpression", "classReferenceExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirClassReferenceExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirClassReferenceExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitComponentCall", "componentCall", "Lorg/jetbrains/kotlin/fir/expressions/FirComponentCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirComponentCall;Ljava/lang/Object;)Ljava/lang/Object;", "visitSmartCastExpression", "smartCastExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitSafeCallExpression", "safeCallExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitCheckedSafeCallSubject", "checkedSafeCallSubject", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckedSafeCallSubject;", "(Lorg/jetbrains/kotlin/fir/expressions/FirCheckedSafeCallSubject;Ljava/lang/Object;)Ljava/lang/Object;", "visitCallableReferenceAccess", "callableReferenceAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "(Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;Ljava/lang/Object;)Ljava/lang/Object;", "visitQualifierWithContextSensitiveAlternative", "qualifierWithContextSensitiveAlternative", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifierWithContextSensitiveAlternative;", "(Lorg/jetbrains/kotlin/fir/expressions/FirQualifierWithContextSensitiveAlternative;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertyAccessExpression", "propertyAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitGetClassCall", "getClassCall", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;Ljava/lang/Object;)Ljava/lang/Object;", "visitWrappedArgumentExpression", "wrappedArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWrappedArgumentExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirWrappedArgumentExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitSpreadArgumentExpression", "spreadArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSpreadArgumentExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirSpreadArgumentExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitNamedArgumentExpression", "namedArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirNamedArgumentExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirNamedArgumentExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitVarargArgumentsExpression", "varargArgumentsExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirVarargArgumentsExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirVarargArgumentsExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitFunctionTypeConversionExpression", "functionTypeConversionExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionTypeConversionExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirFunctionTypeConversionExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitResolvedQualifier", "resolvedQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "(Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;Ljava/lang/Object;)Ljava/lang/Object;", "visitErrorResolvedQualifier", "errorResolvedQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorResolvedQualifier;", "(Lorg/jetbrains/kotlin/fir/expressions/FirErrorResolvedQualifier;Ljava/lang/Object;)Ljava/lang/Object;", "visitResolvedReifiedParameterReference", "resolvedReifiedParameterReference", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedReifiedParameterReference;", "(Lorg/jetbrains/kotlin/fir/expressions/FirResolvedReifiedParameterReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitStringConcatenationCall", "stringConcatenationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirStringConcatenationCall;", "(Lorg/jetbrains/kotlin/fir/expressions/FirStringConcatenationCall;Ljava/lang/Object;)Ljava/lang/Object;", "visitThrowExpression", "throwExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThrowExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirThrowExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitVariableAssignment", "variableAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "(Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;Ljava/lang/Object;)Ljava/lang/Object;", "visitWhenSubjectExpression", "whenSubjectExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenSubjectExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirWhenSubjectExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitDesugaredAssignmentValueReferenceExpression", "desugaredAssignmentValueReferenceExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirDesugaredAssignmentValueReferenceExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirDesugaredAssignmentValueReferenceExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitWrappedExpression", "wrappedExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWrappedExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirWrappedExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitWrappedDelegateExpression", "wrappedDelegateExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWrappedDelegateExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirWrappedDelegateExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitEnumEntryDeserializedAccessExpression", "enumEntryDeserializedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirEnumEntryDeserializedAccessExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirEnumEntryDeserializedAccessExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitReference", "reference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "(Lorg/jetbrains/kotlin/fir/references/FirReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitNamedReference", "namedReference", "Lorg/jetbrains/kotlin/fir/references/FirNamedReference;", "(Lorg/jetbrains/kotlin/fir/references/FirNamedReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitNamedReferenceWithCandidateBase", "namedReferenceWithCandidateBase", "Lorg/jetbrains/kotlin/fir/references/FirNamedReferenceWithCandidateBase;", "(Lorg/jetbrains/kotlin/fir/references/FirNamedReferenceWithCandidateBase;Ljava/lang/Object;)Ljava/lang/Object;", "visitResolvedNamedReference", "resolvedNamedReference", "Lorg/jetbrains/kotlin/fir/references/FirResolvedNamedReference;", "(Lorg/jetbrains/kotlin/fir/references/FirResolvedNamedReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitPropertyWithExplicitBackingFieldResolvedNamedReference", "propertyWithExplicitBackingFieldResolvedNamedReference", "Lorg/jetbrains/kotlin/fir/references/FirPropertyWithExplicitBackingFieldResolvedNamedReference;", "(Lorg/jetbrains/kotlin/fir/references/FirPropertyWithExplicitBackingFieldResolvedNamedReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitResolvedCallableReference", "resolvedCallableReference", "Lorg/jetbrains/kotlin/fir/references/FirResolvedCallableReference;", "(Lorg/jetbrains/kotlin/fir/references/FirResolvedCallableReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitDelegateFieldReference", "delegateFieldReference", "Lorg/jetbrains/kotlin/fir/references/FirDelegateFieldReference;", "(Lorg/jetbrains/kotlin/fir/references/FirDelegateFieldReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitBackingFieldReference", "backingFieldReference", "Lorg/jetbrains/kotlin/fir/references/FirBackingFieldReference;", "(Lorg/jetbrains/kotlin/fir/references/FirBackingFieldReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitSuperReference", "superReference", "Lorg/jetbrains/kotlin/fir/references/FirSuperReference;", "(Lorg/jetbrains/kotlin/fir/references/FirSuperReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitThisReference", "thisReference", "Lorg/jetbrains/kotlin/fir/references/FirThisReference;", "(Lorg/jetbrains/kotlin/fir/references/FirThisReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitControlFlowGraphReference", "controlFlowGraphReference", "Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;", "(Lorg/jetbrains/kotlin/fir/references/FirControlFlowGraphReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeRef", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitResolvedTypeRef", "resolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitUnresolvedTypeRef", "unresolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirUnresolvedTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirUnresolvedTypeRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitUserTypeRef", "userTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirUserTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirUserTypeRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitFunctionTypeRef", "functionTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitDynamicTypeRef", "dynamicTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirDynamicTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirDynamicTypeRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitImplicitTypeRef", "implicitTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirImplicitTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirImplicitTypeRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitErrorTypeRef", "errorTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirErrorTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirErrorTypeRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitResolvedErrorReference", "resolvedErrorReference", "Lorg/jetbrains/kotlin/fir/references/FirResolvedErrorReference;", "(Lorg/jetbrains/kotlin/fir/references/FirResolvedErrorReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitErrorNamedReference", "errorNamedReference", "Lorg/jetbrains/kotlin/fir/references/FirErrorNamedReference;", "(Lorg/jetbrains/kotlin/fir/references/FirErrorNamedReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitErrorSuperReference", "errorSuperReference", "Lorg/jetbrains/kotlin/fir/references/FirErrorSuperReference;", "(Lorg/jetbrains/kotlin/fir/references/FirErrorSuperReference;Ljava/lang/Object;)Ljava/lang/Object;", "visitIntersectionTypeRef", "intersectionTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirIntersectionTypeRef;", "(Lorg/jetbrains/kotlin/fir/types/FirIntersectionTypeRef;Ljava/lang/Object;)Ljava/lang/Object;", "visitThisReceiverExpression", "thisReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitSuperReceiverExpression", "superReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSuperReceiverExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirSuperReceiverExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitInaccessibleReceiverExpression", "inaccessibleReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirInaccessibleReceiverExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirInaccessibleReceiverExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitWhenExpression", "whenExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "(Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeProjection", "typeProjection", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "(Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;Ljava/lang/Object;)Ljava/lang/Object;", "visitTypeProjectionWithVariance", "typeProjectionWithVariance", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjectionWithVariance;", "(Lorg/jetbrains/kotlin/fir/types/FirTypeProjectionWithVariance;Ljava/lang/Object;)Ljava/lang/Object;", "visitStarProjection", "starProjection", "Lorg/jetbrains/kotlin/fir/types/FirStarProjection;", "(Lorg/jetbrains/kotlin/fir/types/FirStarProjection;Ljava/lang/Object;)Ljava/lang/Object;", "visitPlaceholderProjection", "placeholderProjection", "Lorg/jetbrains/kotlin/fir/types/FirPlaceholderProjection;", "(Lorg/jetbrains/kotlin/fir/types/FirPlaceholderProjection;Ljava/lang/Object;)Ljava/lang/Object;", "visitContractElementDeclaration", "contractElementDeclaration", "Lorg/jetbrains/kotlin/fir/contracts/FirContractElementDeclaration;", "(Lorg/jetbrains/kotlin/fir/contracts/FirContractElementDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitEffectDeclaration", "effectDeclaration", "Lorg/jetbrains/kotlin/fir/contracts/FirEffectDeclaration;", "(Lorg/jetbrains/kotlin/fir/contracts/FirEffectDeclaration;Ljava/lang/Object;)Ljava/lang/Object;", "visitContractDescription", "contractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;", "(Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;Ljava/lang/Object;)Ljava/lang/Object;", "visitRawContractDescription", "rawContractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirRawContractDescription;", "(Lorg/jetbrains/kotlin/fir/contracts/FirRawContractDescription;Ljava/lang/Object;)Ljava/lang/Object;", "visitResolvedContractDescription", "resolvedContractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirResolvedContractDescription;", "(Lorg/jetbrains/kotlin/fir/contracts/FirResolvedContractDescription;Ljava/lang/Object;)Ljava/lang/Object;", "visitLegacyRawContractDescription", "legacyRawContractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirLegacyRawContractDescription;", "(Lorg/jetbrains/kotlin/fir/contracts/FirLegacyRawContractDescription;Ljava/lang/Object;)Ljava/lang/Object;", "visitLazyContractDescription", "lazyContractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirLazyContractDescription;", "(Lorg/jetbrains/kotlin/fir/contracts/FirLazyContractDescription;Ljava/lang/Object;)Ljava/lang/Object;", "visitErrorContractDescription", "errorContractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirErrorContractDescription;", "(Lorg/jetbrains/kotlin/fir/contracts/FirErrorContractDescription;Ljava/lang/Object;)Ljava/lang/Object;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirVisitor<R, D> {
    public R visitAnnotation(FirAnnotation annotation, D data) {
        annotation.getClass();
        return visitElement(annotation, data);
    }

    public R visitAnnotationArgumentMapping(FirAnnotationArgumentMapping annotationArgumentMapping, D data) {
        annotationArgumentMapping.getClass();
        return visitElement(annotationArgumentMapping, data);
    }

    public R visitAnnotationCall(FirAnnotationCall annotationCall, D data) {
        annotationCall.getClass();
        return visitElement(annotationCall, data);
    }

    public R visitAnnotationContainer(FirAnnotationContainer annotationContainer, D data) {
        annotationContainer.getClass();
        return visitElement(annotationContainer, data);
    }

    public R visitAnonymousFunction(FirAnonymousFunction anonymousFunction, D data) {
        anonymousFunction.getClass();
        return visitElement(anonymousFunction, data);
    }

    public R visitAnonymousFunctionExpression(FirAnonymousFunctionExpression anonymousFunctionExpression, D data) {
        anonymousFunctionExpression.getClass();
        return visitElement(anonymousFunctionExpression, data);
    }

    public R visitAnonymousInitializer(FirAnonymousInitializer anonymousInitializer, D data) {
        anonymousInitializer.getClass();
        return visitElement(anonymousInitializer, data);
    }

    public R visitAnonymousObject(FirAnonymousObject anonymousObject, D data) {
        anonymousObject.getClass();
        return visitElement(anonymousObject, data);
    }

    public R visitAnonymousObjectExpression(FirAnonymousObjectExpression anonymousObjectExpression, D data) {
        anonymousObjectExpression.getClass();
        return visitElement(anonymousObjectExpression, data);
    }

    public R visitArgumentList(FirArgumentList argumentList, D data) {
        argumentList.getClass();
        return visitElement(argumentList, data);
    }

    public R visitAugmentedAssignment(FirAugmentedAssignment augmentedAssignment, D data) {
        augmentedAssignment.getClass();
        return visitElement(augmentedAssignment, data);
    }

    public R visitBackingField(FirBackingField backingField, D data) {
        backingField.getClass();
        return visitElement(backingField, data);
    }

    public R visitBackingFieldReference(FirBackingFieldReference backingFieldReference, D data) {
        backingFieldReference.getClass();
        return visitElement(backingFieldReference, data);
    }

    public R visitBlock(FirBlock block, D data) {
        block.getClass();
        return visitElement(block, data);
    }

    public R visitBooleanOperatorExpression(FirBooleanOperatorExpression booleanOperatorExpression, D data) {
        booleanOperatorExpression.getClass();
        return visitElement(booleanOperatorExpression, data);
    }

    public R visitBreakExpression(FirBreakExpression breakExpression, D data) {
        breakExpression.getClass();
        return visitElement(breakExpression, data);
    }

    public R visitCall(FirCall call, D data) {
        call.getClass();
        return visitElement(call, data);
    }

    public R visitCallableDeclaration(FirCallableDeclaration callableDeclaration, D data) {
        callableDeclaration.getClass();
        return visitElement(callableDeclaration, data);
    }

    public R visitCallableReferenceAccess(FirCallableReferenceAccess callableReferenceAccess, D data) {
        callableReferenceAccess.getClass();
        return visitElement(callableReferenceAccess, data);
    }

    public R visitCatch(FirCatch firCatch, D data) {
        firCatch.getClass();
        return visitElement(firCatch, data);
    }

    public R visitCheckNotNullCall(FirCheckNotNullCall checkNotNullCall, D data) {
        checkNotNullCall.getClass();
        return visitElement(checkNotNullCall, data);
    }

    public R visitCheckedSafeCallSubject(FirCheckedSafeCallSubject checkedSafeCallSubject, D data) {
        checkedSafeCallSubject.getClass();
        return visitElement(checkedSafeCallSubject, data);
    }

    public R visitClass(FirClass klass, D data) {
        klass.getClass();
        return visitElement(klass, data);
    }

    public R visitClassLikeDeclaration(FirClassLikeDeclaration classLikeDeclaration, D data) {
        classLikeDeclaration.getClass();
        return visitElement(classLikeDeclaration, data);
    }

    public R visitClassReferenceExpression(FirClassReferenceExpression classReferenceExpression, D data) {
        classReferenceExpression.getClass();
        return visitElement(classReferenceExpression, data);
    }

    public R visitCodeFragment(FirCodeFragment codeFragment, D data) {
        codeFragment.getClass();
        return visitElement(codeFragment, data);
    }

    public R visitCollectionLiteral(FirCollectionLiteral collectionLiteral, D data) {
        collectionLiteral.getClass();
        return visitElement(collectionLiteral, data);
    }

    public R visitComparisonExpression(FirComparisonExpression comparisonExpression, D data) {
        comparisonExpression.getClass();
        return visitElement(comparisonExpression, data);
    }

    public R visitComponentCall(FirComponentCall componentCall, D data) {
        componentCall.getClass();
        return visitElement(componentCall, data);
    }

    public R visitConstructedClassTypeParameterRef(FirConstructedClassTypeParameterRef constructedClassTypeParameterRef, D data) {
        constructedClassTypeParameterRef.getClass();
        return visitElement(constructedClassTypeParameterRef, data);
    }

    public R visitConstructor(FirConstructor constructor, D data) {
        constructor.getClass();
        return visitElement(constructor, data);
    }

    public R visitContextArgumentListOwner(FirContextArgumentListOwner contextArgumentListOwner, D data) {
        contextArgumentListOwner.getClass();
        return visitElement(contextArgumentListOwner, data);
    }

    public R visitContinueExpression(FirContinueExpression continueExpression, D data) {
        continueExpression.getClass();
        return visitElement(continueExpression, data);
    }

    public R visitContractDescription(FirContractDescription contractDescription, D data) {
        contractDescription.getClass();
        return visitElement(contractDescription, data);
    }

    public R visitContractDescriptionOwner(FirContractDescriptionOwner contractDescriptionOwner, D data) {
        contractDescriptionOwner.getClass();
        return visitElement(contractDescriptionOwner, data);
    }

    public R visitContractElementDeclaration(FirContractElementDeclaration contractElementDeclaration, D data) {
        contractElementDeclaration.getClass();
        return visitElement(contractElementDeclaration, data);
    }

    public R visitControlFlowGraphOwner(FirControlFlowGraphOwner controlFlowGraphOwner, D data) {
        controlFlowGraphOwner.getClass();
        return visitElement(controlFlowGraphOwner, data);
    }

    public R visitControlFlowGraphReference(FirControlFlowGraphReference controlFlowGraphReference, D data) {
        controlFlowGraphReference.getClass();
        return visitElement(controlFlowGraphReference, data);
    }

    public R visitDanglingModifierList(FirDanglingModifierList danglingModifierList, D data) {
        danglingModifierList.getClass();
        return visitElement(danglingModifierList, data);
    }

    public R visitDeclaration(FirDeclaration declaration, D data) {
        declaration.getClass();
        return visitElement(declaration, data);
    }

    public R visitDeclarationStatus(FirDeclarationStatus declarationStatus, D data) {
        declarationStatus.getClass();
        return visitElement(declarationStatus, data);
    }

    public R visitDelegateFieldReference(FirDelegateFieldReference delegateFieldReference, D data) {
        delegateFieldReference.getClass();
        return visitElement(delegateFieldReference, data);
    }

    public R visitDelegatedConstructorCall(FirDelegatedConstructorCall delegatedConstructorCall, D data) {
        delegatedConstructorCall.getClass();
        return visitElement(delegatedConstructorCall, data);
    }

    public R visitDesugaredAssignmentValueReferenceExpression(FirDesugaredAssignmentValueReferenceExpression desugaredAssignmentValueReferenceExpression, D data) {
        desugaredAssignmentValueReferenceExpression.getClass();
        return visitElement(desugaredAssignmentValueReferenceExpression, data);
    }

    public R visitDiagnosticHolder(FirDiagnosticHolder diagnosticHolder, D data) {
        diagnosticHolder.getClass();
        return visitElement(diagnosticHolder, data);
    }

    public R visitDoWhileLoop(FirDoWhileLoop doWhileLoop, D data) {
        doWhileLoop.getClass();
        return visitElement(doWhileLoop, data);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public R visitDynamicTypeRef(FirDynamicTypeRef dynamicTypeRef, D data) {
        dynamicTypeRef.getClass();
        return visitElement(dynamicTypeRef, data);
    }

    public R visitEffectDeclaration(FirEffectDeclaration effectDeclaration, D data) {
        effectDeclaration.getClass();
        return visitElement(effectDeclaration, data);
    }

    public abstract R visitElement(FirElement element, D data);

    public R visitElementWithResolveState(FirElementWithResolveState elementWithResolveState, D data) {
        elementWithResolveState.getClass();
        return visitElement(elementWithResolveState, data);
    }

    public R visitElvisExpression(FirElvisExpression elvisExpression, D data) {
        elvisExpression.getClass();
        return visitElement(elvisExpression, data);
    }

    public R visitEnumEntry(FirEnumEntry enumEntry, D data) {
        enumEntry.getClass();
        return visitElement(enumEntry, data);
    }

    public R visitEnumEntryDeserializedAccessExpression(FirEnumEntryDeserializedAccessExpression enumEntryDeserializedAccessExpression, D data) {
        enumEntryDeserializedAccessExpression.getClass();
        return visitElement(enumEntryDeserializedAccessExpression, data);
    }

    public R visitEqualityOperatorCall(FirEqualityOperatorCall equalityOperatorCall, D data) {
        equalityOperatorCall.getClass();
        return visitElement(equalityOperatorCall, data);
    }

    public R visitErrorAnnotationCall(FirErrorAnnotationCall errorAnnotationCall, D data) {
        errorAnnotationCall.getClass();
        return visitElement(errorAnnotationCall, data);
    }

    public R visitErrorContractDescription(FirErrorContractDescription errorContractDescription, D data) {
        errorContractDescription.getClass();
        return visitElement(errorContractDescription, data);
    }

    public R visitErrorExpression(FirErrorExpression errorExpression, D data) {
        errorExpression.getClass();
        return visitElement(errorExpression, data);
    }

    public R visitErrorFunction(FirErrorFunction errorFunction, D data) {
        errorFunction.getClass();
        return visitElement(errorFunction, data);
    }

    public R visitErrorLoop(FirErrorLoop errorLoop, D data) {
        errorLoop.getClass();
        return visitElement(errorLoop, data);
    }

    public R visitErrorNamedReference(FirErrorNamedReference errorNamedReference, D data) {
        errorNamedReference.getClass();
        return visitElement(errorNamedReference, data);
    }

    public R visitErrorPrimaryConstructor(FirErrorPrimaryConstructor errorPrimaryConstructor, D data) {
        errorPrimaryConstructor.getClass();
        return visitElement(errorPrimaryConstructor, data);
    }

    public R visitErrorProperty(FirErrorProperty errorProperty, D data) {
        errorProperty.getClass();
        return visitElement(errorProperty, data);
    }

    public R visitErrorResolvedQualifier(FirErrorResolvedQualifier errorResolvedQualifier, D data) {
        errorResolvedQualifier.getClass();
        return visitElement(errorResolvedQualifier, data);
    }

    public R visitErrorSuperReference(FirErrorSuperReference errorSuperReference, D data) {
        errorSuperReference.getClass();
        return visitElement(errorSuperReference, data);
    }

    public R visitErrorTypeRef(FirErrorTypeRef errorTypeRef, D data) {
        errorTypeRef.getClass();
        return visitElement(errorTypeRef, data);
    }

    public R visitExpression(FirExpression expression, D data) {
        expression.getClass();
        return visitElement(expression, data);
    }

    public R visitField(FirField field, D data) {
        field.getClass();
        return visitElement(field, data);
    }

    public R visitFile(FirFile file, D data) {
        file.getClass();
        return visitElement(file, data);
    }

    public R visitFunction(FirFunction function, D data) {
        function.getClass();
        return visitElement(function, data);
    }

    public R visitFunctionCall(FirFunctionCall functionCall, D data) {
        functionCall.getClass();
        return visitElement(functionCall, data);
    }

    public R visitFunctionTypeConversionExpression(FirFunctionTypeConversionExpression functionTypeConversionExpression, D data) {
        functionTypeConversionExpression.getClass();
        return visitElement(functionTypeConversionExpression, data);
    }

    public R visitFunctionTypeParameter(FirFunctionTypeParameter functionTypeParameter, D data) {
        functionTypeParameter.getClass();
        return visitElement(functionTypeParameter, data);
    }

    public R visitFunctionTypeRef(FirFunctionTypeRef functionTypeRef, D data) {
        functionTypeRef.getClass();
        return visitElement(functionTypeRef, data);
    }

    public R visitGetClassCall(FirGetClassCall getClassCall, D data) {
        getClassCall.getClass();
        return visitElement(getClassCall, data);
    }

    public R visitImplicitInvokeCall(FirImplicitInvokeCall implicitInvokeCall, D data) {
        implicitInvokeCall.getClass();
        return visitElement(implicitInvokeCall, data);
    }

    public R visitImplicitTypeRef(FirImplicitTypeRef implicitTypeRef, D data) {
        implicitTypeRef.getClass();
        return visitElement(implicitTypeRef, data);
    }

    public R visitImport(FirImport firImport, D data) {
        firImport.getClass();
        return visitElement(firImport, data);
    }

    public R visitInaccessibleReceiverExpression(FirInaccessibleReceiverExpression inaccessibleReceiverExpression, D data) {
        inaccessibleReceiverExpression.getClass();
        return visitElement(inaccessibleReceiverExpression, data);
    }

    public R visitIncrementDecrementExpression(FirIncrementDecrementExpression incrementDecrementExpression, D data) {
        incrementDecrementExpression.getClass();
        return visitElement(incrementDecrementExpression, data);
    }

    public R visitIndexedAccessAugmentedAssignment(FirIndexedAccessAugmentedAssignment indexedAccessAugmentedAssignment, D data) {
        indexedAccessAugmentedAssignment.getClass();
        return visitElement(indexedAccessAugmentedAssignment, data);
    }

    public R visitIntegerLiteralOperatorCall(FirIntegerLiteralOperatorCall integerLiteralOperatorCall, D data) {
        integerLiteralOperatorCall.getClass();
        return visitElement(integerLiteralOperatorCall, data);
    }

    public R visitIntersectionTypeRef(FirIntersectionTypeRef intersectionTypeRef, D data) {
        intersectionTypeRef.getClass();
        return visitElement(intersectionTypeRef, data);
    }

    public <E extends FirTargetElement> R visitJump(FirJump<E> jump, D data) {
        jump.getClass();
        return visitElement(jump, data);
    }

    public R visitLabel(FirLabel label, D data) {
        label.getClass();
        return visitElement(label, data);
    }

    public R visitLazyBlock(FirLazyBlock lazyBlock, D data) {
        lazyBlock.getClass();
        return visitElement(lazyBlock, data);
    }

    public R visitLazyContractDescription(FirLazyContractDescription lazyContractDescription, D data) {
        lazyContractDescription.getClass();
        return visitElement(lazyContractDescription, data);
    }

    public R visitLazyExpression(FirLazyExpression lazyExpression, D data) {
        lazyExpression.getClass();
        return visitElement(lazyExpression, data);
    }

    public R visitLegacyRawContractDescription(FirLegacyRawContractDescription legacyRawContractDescription, D data) {
        legacyRawContractDescription.getClass();
        return visitElement(legacyRawContractDescription, data);
    }

    public R visitLiteralExpression(FirLiteralExpression literalExpression, D data) {
        literalExpression.getClass();
        return visitElement(literalExpression, data);
    }

    public R visitLoop(FirLoop loop, D data) {
        loop.getClass();
        return visitElement(loop, data);
    }

    public R visitLoopJump(FirLoopJump loopJump, D data) {
        loopJump.getClass();
        return visitElement(loopJump, data);
    }

    public R visitMemberDeclaration(FirMemberDeclaration memberDeclaration, D data) {
        memberDeclaration.getClass();
        return visitElement(memberDeclaration, data);
    }

    public R visitMultiDelegatedConstructorCall(FirMultiDelegatedConstructorCall multiDelegatedConstructorCall, D data) {
        multiDelegatedConstructorCall.getClass();
        return visitElement(multiDelegatedConstructorCall, data);
    }

    public R visitNamedArgumentExpression(FirNamedArgumentExpression namedArgumentExpression, D data) {
        namedArgumentExpression.getClass();
        return visitElement(namedArgumentExpression, data);
    }

    public R visitNamedFunction(FirNamedFunction namedFunction, D data) {
        namedFunction.getClass();
        return visitElement(namedFunction, data);
    }

    public R visitNamedReference(FirNamedReference namedReference, D data) {
        namedReference.getClass();
        return visitElement(namedReference, data);
    }

    public R visitNamedReferenceWithCandidateBase(FirNamedReferenceWithCandidateBase namedReferenceWithCandidateBase, D data) {
        namedReferenceWithCandidateBase.getClass();
        return visitElement(namedReferenceWithCandidateBase, data);
    }

    public R visitOuterClassTypeParameterRef(FirOuterClassTypeParameterRef outerClassTypeParameterRef, D data) {
        outerClassTypeParameterRef.getClass();
        return visitElement(outerClassTypeParameterRef, data);
    }

    public R visitPackageDirective(FirPackageDirective packageDirective, D data) {
        packageDirective.getClass();
        return visitElement(packageDirective, data);
    }

    public R visitPlaceholderProjection(FirPlaceholderProjection placeholderProjection, D data) {
        placeholderProjection.getClass();
        return visitElement(placeholderProjection, data);
    }

    public R visitProperty(FirProperty property, D data) {
        property.getClass();
        return visitElement(property, data);
    }

    public R visitPropertyAccessExpression(FirPropertyAccessExpression propertyAccessExpression, D data) {
        propertyAccessExpression.getClass();
        return visitElement(propertyAccessExpression, data);
    }

    public R visitPropertyAccessor(FirPropertyAccessor propertyAccessor, D data) {
        propertyAccessor.getClass();
        return visitElement(propertyAccessor, data);
    }

    public R visitPropertyWithExplicitBackingFieldResolvedNamedReference(FirPropertyWithExplicitBackingFieldResolvedNamedReference propertyWithExplicitBackingFieldResolvedNamedReference, D data) {
        propertyWithExplicitBackingFieldResolvedNamedReference.getClass();
        return visitElement(propertyWithExplicitBackingFieldResolvedNamedReference, data);
    }

    public R visitQualifiedAccessExpression(FirQualifiedAccessExpression qualifiedAccessExpression, D data) {
        qualifiedAccessExpression.getClass();
        return visitElement(qualifiedAccessExpression, data);
    }

    public R visitQualifiedErrorAccessExpression(FirQualifiedErrorAccessExpression qualifiedErrorAccessExpression, D data) {
        qualifiedErrorAccessExpression.getClass();
        return visitElement(qualifiedErrorAccessExpression, data);
    }

    public R visitQualifierWithContextSensitiveAlternative(FirQualifierWithContextSensitiveAlternative qualifierWithContextSensitiveAlternative, D data) {
        qualifierWithContextSensitiveAlternative.getClass();
        return visitElement(qualifierWithContextSensitiveAlternative, data);
    }

    public R visitRawContractDescription(FirRawContractDescription rawContractDescription, D data) {
        rawContractDescription.getClass();
        return visitElement(rawContractDescription, data);
    }

    public R visitReceiverParameter(FirReceiverParameter receiverParameter, D data) {
        receiverParameter.getClass();
        return visitElement(receiverParameter, data);
    }

    public R visitReference(FirReference reference, D data) {
        reference.getClass();
        return visitElement(reference, data);
    }

    public R visitRegularClass(FirRegularClass regularClass, D data) {
        regularClass.getClass();
        return visitElement(regularClass, data);
    }

    public R visitReplDeclarationReference(FirReplDeclarationReference replDeclarationReference, D data) {
        replDeclarationReference.getClass();
        return visitElement(replDeclarationReference, data);
    }

    public R visitReplExpressionReference(FirReplExpressionReference replExpressionReference, D data) {
        replExpressionReference.getClass();
        return visitElement(replExpressionReference, data);
    }

    public R visitReplPropertyDelegate(FirReplPropertyDelegate replPropertyDelegate, D data) {
        replPropertyDelegate.getClass();
        return visitElement(replPropertyDelegate, data);
    }

    public R visitReplPropertyInitializer(FirReplPropertyInitializer replPropertyInitializer, D data) {
        replPropertyInitializer.getClass();
        return visitElement(replPropertyInitializer, data);
    }

    public R visitReplSnippet(FirReplSnippet replSnippet, D data) {
        replSnippet.getClass();
        return visitElement(replSnippet, data);
    }

    public R visitResolvable(FirResolvable resolvable, D data) {
        resolvable.getClass();
        return visitElement(resolvable, data);
    }

    public R visitResolvedCallableReference(FirResolvedCallableReference resolvedCallableReference, D data) {
        resolvedCallableReference.getClass();
        return visitElement(resolvedCallableReference, data);
    }

    public R visitResolvedContractDescription(FirResolvedContractDescription resolvedContractDescription, D data) {
        resolvedContractDescription.getClass();
        return visitElement(resolvedContractDescription, data);
    }

    public R visitResolvedDeclarationStatus(FirResolvedDeclarationStatus resolvedDeclarationStatus, D data) {
        resolvedDeclarationStatus.getClass();
        return visitElement(resolvedDeclarationStatus, data);
    }

    public R visitResolvedErrorReference(FirResolvedErrorReference resolvedErrorReference, D data) {
        resolvedErrorReference.getClass();
        return visitElement(resolvedErrorReference, data);
    }

    public R visitResolvedImport(FirResolvedImport resolvedImport, D data) {
        resolvedImport.getClass();
        return visitElement(resolvedImport, data);
    }

    public R visitResolvedNamedReference(FirResolvedNamedReference resolvedNamedReference, D data) {
        resolvedNamedReference.getClass();
        return visitElement(resolvedNamedReference, data);
    }

    public R visitResolvedQualifier(FirResolvedQualifier resolvedQualifier, D data) {
        resolvedQualifier.getClass();
        return visitElement(resolvedQualifier, data);
    }

    public R visitResolvedReifiedParameterReference(FirResolvedReifiedParameterReference resolvedReifiedParameterReference, D data) {
        resolvedReifiedParameterReference.getClass();
        return visitElement(resolvedReifiedParameterReference, data);
    }

    public R visitResolvedTypeRef(FirResolvedTypeRef resolvedTypeRef, D data) {
        resolvedTypeRef.getClass();
        return visitElement(resolvedTypeRef, data);
    }

    public R visitReturnExpression(FirReturnExpression returnExpression, D data) {
        returnExpression.getClass();
        return visitElement(returnExpression, data);
    }

    public R visitSafeCallExpression(FirSafeCallExpression safeCallExpression, D data) {
        safeCallExpression.getClass();
        return visitElement(safeCallExpression, data);
    }

    public R visitScript(FirScript script, D data) {
        script.getClass();
        return visitElement(script, data);
    }

    public R visitScriptReceiverParameter(FirScriptReceiverParameter scriptReceiverParameter, D data) {
        scriptReceiverParameter.getClass();
        return visitElement(scriptReceiverParameter, data);
    }

    public R visitSmartCastExpression(FirSmartCastExpression smartCastExpression, D data) {
        smartCastExpression.getClass();
        return visitElement(smartCastExpression, data);
    }

    public R visitSpreadArgumentExpression(FirSpreadArgumentExpression spreadArgumentExpression, D data) {
        spreadArgumentExpression.getClass();
        return visitElement(spreadArgumentExpression, data);
    }

    public R visitStarProjection(FirStarProjection starProjection, D data) {
        starProjection.getClass();
        return visitElement(starProjection, data);
    }

    public R visitStatement(FirStatement statement, D data) {
        statement.getClass();
        return visitElement(statement, data);
    }

    public R visitStringConcatenationCall(FirStringConcatenationCall stringConcatenationCall, D data) {
        stringConcatenationCall.getClass();
        return visitElement(stringConcatenationCall, data);
    }

    public R visitSuperReceiverExpression(FirSuperReceiverExpression superReceiverExpression, D data) {
        superReceiverExpression.getClass();
        return visitElement(superReceiverExpression, data);
    }

    public R visitSuperReference(FirSuperReference superReference, D data) {
        superReference.getClass();
        return visitElement(superReference, data);
    }

    public R visitTargetElement(FirTargetElement targetElement, D data) {
        targetElement.getClass();
        return visitElement(targetElement, data);
    }

    public R visitThisReceiverExpression(FirThisReceiverExpression thisReceiverExpression, D data) {
        thisReceiverExpression.getClass();
        return visitElement(thisReceiverExpression, data);
    }

    public R visitThisReference(FirThisReference thisReference, D data) {
        thisReference.getClass();
        return visitElement(thisReference, data);
    }

    public R visitThrowExpression(FirThrowExpression throwExpression, D data) {
        throwExpression.getClass();
        return visitElement(throwExpression, data);
    }

    public R visitTryExpression(FirTryExpression tryExpression, D data) {
        tryExpression.getClass();
        return visitElement(tryExpression, data);
    }

    public R visitTypeAlias(FirTypeAlias typeAlias, D data) {
        typeAlias.getClass();
        return visitElement(typeAlias, data);
    }

    public R visitTypeOperatorCall(FirTypeOperatorCall typeOperatorCall, D data) {
        typeOperatorCall.getClass();
        return visitElement(typeOperatorCall, data);
    }

    public R visitTypeParameter(FirTypeParameter typeParameter, D data) {
        typeParameter.getClass();
        return visitElement(typeParameter, data);
    }

    public R visitTypeParameterRef(FirTypeParameterRef typeParameterRef, D data) {
        typeParameterRef.getClass();
        return visitElement(typeParameterRef, data);
    }

    public R visitTypeParameterRefsOwner(FirTypeParameterRefsOwner typeParameterRefsOwner, D data) {
        typeParameterRefsOwner.getClass();
        return visitElement(typeParameterRefsOwner, data);
    }

    public R visitTypeParametersOwner(FirTypeParametersOwner typeParametersOwner, D data) {
        typeParametersOwner.getClass();
        return visitElement(typeParametersOwner, data);
    }

    public R visitTypeProjection(FirTypeProjection typeProjection, D data) {
        typeProjection.getClass();
        return visitElement(typeProjection, data);
    }

    public R visitTypeProjectionWithVariance(FirTypeProjectionWithVariance typeProjectionWithVariance, D data) {
        typeProjectionWithVariance.getClass();
        return visitElement(typeProjectionWithVariance, data);
    }

    public R visitTypeRef(FirTypeRef typeRef, D data) {
        typeRef.getClass();
        return visitElement(typeRef, data);
    }

    public R visitUnresolvedTypeRef(FirUnresolvedTypeRef unresolvedTypeRef, D data) {
        unresolvedTypeRef.getClass();
        return visitElement(unresolvedTypeRef, data);
    }

    public R visitUserTypeRef(FirUserTypeRef userTypeRef, D data) {
        userTypeRef.getClass();
        return visitElement(userTypeRef, data);
    }

    public R visitValueParameter(FirValueParameter valueParameter, D data) {
        valueParameter.getClass();
        return visitElement(valueParameter, data);
    }

    public R visitVarargArgumentsExpression(FirVarargArgumentsExpression varargArgumentsExpression, D data) {
        varargArgumentsExpression.getClass();
        return visitElement(varargArgumentsExpression, data);
    }

    public R visitVariable(FirVariable variable, D data) {
        variable.getClass();
        return visitElement(variable, data);
    }

    public R visitVariableAssignment(FirVariableAssignment variableAssignment, D data) {
        variableAssignment.getClass();
        return visitElement(variableAssignment, data);
    }

    public R visitWhenBranch(FirWhenBranch whenBranch, D data) {
        whenBranch.getClass();
        return visitElement(whenBranch, data);
    }

    public R visitWhenExpression(FirWhenExpression whenExpression, D data) {
        whenExpression.getClass();
        return visitElement(whenExpression, data);
    }

    public R visitWhenSubjectExpression(FirWhenSubjectExpression whenSubjectExpression, D data) {
        whenSubjectExpression.getClass();
        return visitElement(whenSubjectExpression, data);
    }

    public R visitWhileLoop(FirWhileLoop whileLoop, D data) {
        whileLoop.getClass();
        return visitElement(whileLoop, data);
    }

    public R visitWrappedArgumentExpression(FirWrappedArgumentExpression wrappedArgumentExpression, D data) {
        wrappedArgumentExpression.getClass();
        return visitElement(wrappedArgumentExpression, data);
    }

    public R visitWrappedDelegateExpression(FirWrappedDelegateExpression wrappedDelegateExpression, D data) {
        wrappedDelegateExpression.getClass();
        return visitElement(wrappedDelegateExpression, data);
    }

    public R visitWrappedExpression(FirWrappedExpression wrappedExpression, D data) {
        wrappedExpression.getClass();
        return visitElement(wrappedExpression, data);
    }
}
