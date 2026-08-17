package org.jetbrains.kotlin.fir.renderer;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.UninitializedPropertyAccessException;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKind;
import org.jetbrains.kotlin.builtins.functions.FunctionTypeKindExtractor;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.cli.common.arguments.PreprocessCommandLineArgumentsKt;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirFunctionTypeParameter;
import org.jetbrains.kotlin.fir.FirLabel;
import org.jetbrains.kotlin.fir.FirPackageDirective;
import org.jetbrains.kotlin.fir.FirTarget;
import org.jetbrains.kotlin.fir.contracts.FirContractDescription;
import org.jetbrains.kotlin.fir.contracts.FirEffectDeclaration;
import org.jetbrains.kotlin.fir.contracts.description.ConeContractRenderer;
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
import org.jetbrains.kotlin.fir.declarations.FirDanglingModifierList;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
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
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
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
import org.jetbrains.kotlin.fir.expressions.FirContinueExpression;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirDesugaredAssignmentValueReferenceExpression;
import org.jetbrains.kotlin.fir.expressions.FirDoWhileLoop;
import org.jetbrains.kotlin.fir.expressions.FirElvisExpression;
import org.jetbrains.kotlin.fir.expressions.FirEnumEntryDeserializedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirEqualityOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirErrorExpression;
import org.jetbrains.kotlin.fir.expressions.FirErrorResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionConversionKind;
import org.jetbrains.kotlin.fir.expressions.FirFunctionTypeConversionExpression;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirImplicitInvokeCall;
import org.jetbrains.kotlin.fir.expressions.FirInaccessibleReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirIncrementDecrementExpression;
import org.jetbrains.kotlin.fir.expressions.FirIndexedAccessAugmentedAssignment;
import org.jetbrains.kotlin.fir.expressions.FirIntegerLiteralOperatorCall;
import org.jetbrains.kotlin.fir.expressions.FirLazyExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirLoop;
import org.jetbrains.kotlin.fir.expressions.FirLoopJump;
import org.jetbrains.kotlin.fir.expressions.FirMultiDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirNamedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
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
import org.jetbrains.kotlin.fir.expressions.FirWhenBranch;
import org.jetbrains.kotlin.fir.expressions.FirWhenExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhenSubjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirWhileLoop;
import org.jetbrains.kotlin.fir.expressions.FirWrappedDelegateExpression;
import org.jetbrains.kotlin.fir.expressions.impl.FirElseIfTrueCondition;
import org.jetbrains.kotlin.fir.expressions.impl.FirExpressionStub;
import org.jetbrains.kotlin.fir.expressions.impl.FirLazyDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.impl.FirUnitExpression;
import org.jetbrains.kotlin.fir.references.FirBackingFieldReference;
import org.jetbrains.kotlin.fir.references.FirDelegateFieldReference;
import org.jetbrains.kotlin.fir.references.FirErrorNamedReference;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirNamedReferenceWithCandidateBase;
import org.jetbrains.kotlin.fir.references.FirPropertyWithExplicitBackingFieldResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.FirResolvedCallableReference;
import org.jetbrains.kotlin.fir.references.FirResolvedErrorReference;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.FirSuperReference;
import org.jetbrains.kotlin.fir.references.FirThisReference;
import org.jetbrains.kotlin.fir.renderer.FirRenderer;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirThisOwnerSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassifierLookupTag;
import org.jetbrains.kotlin.fir.types.ConeFlexibleType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirDynamicTypeRef;
import org.jetbrains.kotlin.fir.types.FirErrorTypeRef;
import org.jetbrains.kotlin.fir.types.FirFunctionTypeRef;
import org.jetbrains.kotlin.fir.types.FirImplicitTypeRef;
import org.jetbrains.kotlin.fir.types.FirQualifierPart;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirStarProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirUnresolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirUserTypeRef;
import org.jetbrains.kotlin.fir.visitors.FirVisitorVoid;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.types.ConstantValueKind;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0086\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b-\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0000\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u0000 \u0089\u00012\u00020\u0001:\u0004\u0089\u0001\u008a\u0001B\u0097\u0002\u0012\f\b\u0002\u0010\u0002\u001a\u00060\u0003j\u0002`\u0004\u0012\n\b\u0002\u0010\u0005\u001a\u0004\u0018\u00010\u0006\u0012\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\b\u0012\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u0012\n\b\u0002\u0010\r\u001a\u0004\u0018\u00010\u000e\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\u0010\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\u0012\u0012\b\b\u0002\u0010\u0013\u001a\u00020\u0014\u0012\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016\u0012\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018\u0012\n\b\u0002\u0010\u0019\u001a\u0004\u0018\u00010\u001a\u0012\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u001c\u0012\b\b\u0002\u0010\u001d\u001a\u00020\u001e\u0012\b\b\u0002\u0010\u001f\u001a\u00020 \u0012\n\b\u0002\u0010!\u001a\u0004\u0018\u00010\"\u0012\n\b\u0002\u0010#\u001a\u0004\u0018\u00010$\u0012\b\b\u0002\u0010%\u001a\u00020&\u0012\b\b\u0002\u0010'\u001a\u00020(\u0012\b\b\u0002\u0010)\u001a\u00020*\u0012\n\b\u0002\u0010+\u001a\u0004\u0018\u00010,\u0012\b\b\u0002\u0010-\u001a\u00020.\u0012\b\b\u0002\u0010/\u001a\u00020.\u0012\b\b\u0002\u00100\u001a\u00020.¢\u0006\u0004\b1\u00102J\u0018\u0010c\u001a\u00020d2\u0006\u0010e\u001a\u00020f2\b\b\u0002\u0010g\u001a\u00020.J\u000e\u0010h\u001a\u00020d2\u0006\u0010e\u001a\u00020fJ\u000e\u0010i\u001a\u00020d2\u0006\u0010j\u001a\u00020kJ\u000e\u0010l\u001a\u00020m2\u0006\u0010n\u001a\u00020oJ\u000e\u0010p\u001a\u00020m2\u0006\u0010q\u001a\u00020rJ\f\u0010s\u001a\u00020m*\u00020tH\u0002J \u0010u\u001a\u00020m2\f\u0010v\u001a\b\u0012\u0004\u0012\u00020x0w2\b\b\u0002\u0010y\u001a\u00020.H\u0002J\u0012\u0010z\u001a\u00020m*\b\u0012\u0004\u0012\u00020{0wH\u0002J\u0012\u0010|\u001a\u00020m*\b\u0012\u0004\u0012\u00020}0wH\u0002J\u0011\u0010~\u001a\u00020m2\u0007\u0010\u007f\u001a\u00030\u0080\u0001H\u0002J$\u0010\u0081\u0001\u001a\u00020m2\r\u0010\u0082\u0001\u001a\b\u0012\u0004\u0012\u00020f0w2\n\u0010[\u001a\u00060\\R\u00020\u0000H\u0002J\u0015\u0010\u0083\u0001\u001a\u00020m2\n\u0010\u0084\u0001\u001a\u0005\u0018\u00010\u0085\u0001H\u0002J\u0013\u0010\u0086\u0001\u001a\u00020m2\b\u0010\u0087\u0001\u001a\u00030\u0088\u0001H\u0002R\u0016\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0016\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b5\u00106R\u0016\u0010\t\u001a\u0004\u0018\u00010\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u0016\u0010\u000b\u001a\u0004\u0018\u00010\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b9\u0010:R\u0016\u0010\r\u001a\u0004\u0018\u00010\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u0016\u0010\u000f\u001a\u0004\u0018\u00010\u0010X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b=\u0010>R\u0016\u0010\u0011\u001a\u0004\u0018\u00010\u0012X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b?\u0010@R\u0014\u0010\u0013\u001a\u00020\u0014X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bA\u0010BR\u0016\u0010\u0015\u001a\u0004\u0018\u00010\u0016X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bC\u0010DR\u0016\u0010\u0017\u001a\u0004\u0018\u00010\u0018X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bE\u0010FR\u0016\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bG\u0010HR\u0016\u0010\u001b\u001a\u0004\u0018\u00010\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bI\u0010JR\u0014\u0010\u001d\u001a\u00020\u001eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bK\u0010LR\u0014\u0010\u001f\u001a\u00020 X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bM\u0010NR\u0016\u0010!\u001a\u0004\u0018\u00010\"X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bO\u0010PR\u0016\u0010#\u001a\u0004\u0018\u00010$X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010RR\u0014\u0010%\u001a\u00020&X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bS\u0010TR\u0014\u0010'\u001a\u00020(X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bU\u0010VR\u0014\u0010)\u001a\u00020*X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bW\u0010XR\u0016\u0010+\u001a\u0004\u0018\u00010,X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bY\u0010ZR\u000e\u0010-\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010/\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u00100\u001a\u00020.X\u0082\u0004¢\u0006\u0002\n\u0000R\u0018\u0010[\u001a\u00060\\R\u00020\u0000X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b]\u0010^R\u0014\u0010_\u001a\u00020`X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\ba\u0010b¨\u0006\u008b\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirRenderer;", "Lorg/jetbrains/kotlin/fir/renderer/FirRendererComponents;", "builder", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "annotationRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirAnnotationRenderer;", "bodyRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirBodyRenderer;", "callArgumentsRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirCallArgumentsRenderer;", "contextArgumentRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirContextArgumentRenderer;", "classMemberRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirClassMemberRenderer;", "contractRenderer", "Lorg/jetbrains/kotlin/fir/contracts/description/ConeContractRenderer;", "declarationRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirDeclarationRenderer;", "idRenderer", "Lorg/jetbrains/kotlin/fir/renderer/ConeIdRenderer;", "modifierRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirModifierRenderer;", "packageDirectiveRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirPackageDirectiveRenderer;", "propertyAccessorRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirPropertyAccessorRenderer;", "resolvePhaseRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirResolvePhaseRenderer;", "typeRenderer", "Lorg/jetbrains/kotlin/fir/renderer/ConeTypeRenderer;", "referencedSymbolRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirSymbolRenderer;", "callableSignatureRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirCallableSignatureRenderer;", "errorExpressionRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirErrorExpressionRenderer;", "resolvedNamedReferenceRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirResolvedNamedReferenceRenderer;", "resolvedQualifierRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirResolvedQualifierRenderer;", "getClassCallRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirGetClassCallRenderer;", "supertypeRenderer", "Lorg/jetbrains/kotlin/fir/renderer/FirSupertypeRenderer;", "lineBreakAfterContextParameters", Argument.Delimiters.none, "renderFieldAnnotationSeparately", "renderVarargTypes", "<init>", "(Ljava/lang/StringBuilder;Lorg/jetbrains/kotlin/fir/renderer/FirAnnotationRenderer;Lorg/jetbrains/kotlin/fir/renderer/FirBodyRenderer;Lorg/jetbrains/kotlin/fir/renderer/FirCallArgumentsRenderer;Lorg/jetbrains/kotlin/fir/renderer/FirContextArgumentRenderer;Lorg/jetbrains/kotlin/fir/renderer/FirClassMemberRenderer;Lorg/jetbrains/kotlin/fir/contracts/description/ConeContractRenderer;Lorg/jetbrains/kotlin/fir/renderer/FirDeclarationRenderer;Lorg/jetbrains/kotlin/fir/renderer/ConeIdRenderer;Lorg/jetbrains/kotlin/fir/renderer/FirModifierRenderer;Lorg/jetbrains/kotlin/fir/renderer/FirPackageDirectiveRenderer;Lorg/jetbrains/kotlin/fir/renderer/FirPropertyAccessorRenderer;Lorg/jetbrains/kotlin/fir/renderer/FirResolvePhaseRenderer;Lorg/jetbrains/kotlin/fir/renderer/ConeTypeRenderer;Lorg/jetbrains/kotlin/fir/renderer/FirSymbolRenderer;Lorg/jetbrains/kotlin/fir/renderer/FirCallableSignatureRenderer;Lorg/jetbrains/kotlin/fir/renderer/FirErrorExpressionRenderer;Lorg/jetbrains/kotlin/fir/renderer/FirResolvedNamedReferenceRenderer;Lorg/jetbrains/kotlin/fir/renderer/FirResolvedQualifierRenderer;Lorg/jetbrains/kotlin/fir/renderer/FirGetClassCallRenderer;Lorg/jetbrains/kotlin/fir/renderer/FirSupertypeRenderer;ZZZ)V", "getAnnotationRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirAnnotationRenderer;", "getBodyRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirBodyRenderer;", "getCallArgumentsRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirCallArgumentsRenderer;", "getContextArgumentRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirContextArgumentRenderer;", "getClassMemberRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirClassMemberRenderer;", "getContractRenderer", "()Lorg/jetbrains/kotlin/fir/contracts/description/ConeContractRenderer;", "getDeclarationRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirDeclarationRenderer;", "getIdRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/ConeIdRenderer;", "getModifierRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirModifierRenderer;", "getPackageDirectiveRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirPackageDirectiveRenderer;", "getPropertyAccessorRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirPropertyAccessorRenderer;", "getResolvePhaseRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirResolvePhaseRenderer;", "getTypeRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/ConeTypeRenderer;", "getReferencedSymbolRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirSymbolRenderer;", "getCallableSignatureRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirCallableSignatureRenderer;", "getErrorExpressionRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirErrorExpressionRenderer;", "getResolvedNamedReferenceRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirResolvedNamedReferenceRenderer;", "getResolvedQualifierRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirResolvedQualifierRenderer;", "getGetClassCallRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirGetClassCallRenderer;", "getSupertypeRenderer", "()Lorg/jetbrains/kotlin/fir/renderer/FirSupertypeRenderer;", "visitor", "Lorg/jetbrains/kotlin/fir/renderer/FirRenderer$Visitor;", "getVisitor", "()Lorg/jetbrains/kotlin/fir/renderer/FirRenderer$Visitor;", "printer", "Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "getPrinter", "()Lorg/jetbrains/kotlin/fir/renderer/FirPrinter;", "renderElementAsString", Argument.Delimiters.none, "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "trim", "renderElementWithTypeAsString", "renderAsCallableDeclarationString", "callableDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "renderMemberDeclarationClass", Argument.Delimiters.none, "firClass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "renderAnnotations", "annotationContainer", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "renderVariance", "Lorg/jetbrains/kotlin/types/Variance;", "renderContexts", "contextParameters", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "lineBreakAfter", "renderTypeParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "renderTypeArguments", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "print", "s", Argument.Delimiters.none, "renderSeparated", "elements", "renderType", ModuleXmlParser.TYPE, "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "renderPhaseAndAttributes", "declaration", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "Companion", "Visitor", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirRenderer implements FirRendererComponents {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final FirAnnotationRenderer annotationRenderer;
    private final FirBodyRenderer bodyRenderer;
    private final FirCallArgumentsRenderer callArgumentsRenderer;
    private final FirCallableSignatureRenderer callableSignatureRenderer;
    private final FirClassMemberRenderer classMemberRenderer;
    private final FirContextArgumentRenderer contextArgumentRenderer;
    private final ConeContractRenderer contractRenderer;
    private final FirDeclarationRenderer declarationRenderer;
    private final FirErrorExpressionRenderer errorExpressionRenderer;
    private final FirGetClassCallRenderer getClassCallRenderer;
    private final ConeIdRenderer idRenderer;
    private final boolean lineBreakAfterContextParameters;
    private final FirModifierRenderer modifierRenderer;
    private final FirPackageDirectiveRenderer packageDirectiveRenderer;
    private final FirPrinter printer;
    private final FirPropertyAccessorRenderer propertyAccessorRenderer;
    private final FirSymbolRenderer referencedSymbolRenderer;
    private final boolean renderFieldAnnotationSeparately;
    private final boolean renderVarargTypes;
    private final FirResolvePhaseRenderer resolvePhaseRenderer;
    private final FirResolvedNamedReferenceRenderer resolvedNamedReferenceRenderer;
    private final FirResolvedQualifierRenderer resolvedQualifierRenderer;
    private final FirSupertypeRenderer supertypeRenderer;
    private final ConeTypeRenderer typeRenderer;
    private final Visitor visitor;

    @Metadata(d1 = {"\u0000ú\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\b\u0086\u0004\u0018\u00002\u00020\u0001B\t\b\u0000¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0010\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\nH\u0016J\u0010\u0010\u000b\u001a\u00020\u00052\u0006\u0010\f\u001a\u00020\rH\u0016J\u0010\u0010\u000e\u001a\u00020\u00052\u0006\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\u00052\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\u00052\u0006\u0010\u0015\u001a\u00020\u0016H\u0016J\u0010\u0010\u0017\u001a\u00020\u00052\u0006\u0010\u0018\u001a\u00020\u0019H\u0016J\u0010\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001e\u001a\u00020\u001fH\u0016J\u0010\u0010 \u001a\u00020\u00052\u0006\u0010!\u001a\u00020\"H\u0016J\u0010\u0010#\u001a\u00020\u00052\u0006\u0010$\u001a\u00020%H\u0016J\u0010\u0010&\u001a\u00020\u00052\u0006\u0010'\u001a\u00020(H\u0016J\u0010\u0010)\u001a\u00020\u00052\u0006\u0010*\u001a\u00020+H\u0016J\u0010\u0010,\u001a\u00020\u00052\u0006\u0010-\u001a\u00020.H\u0016J\u0010\u0010/\u001a\u00020\u00052\u0006\u00100\u001a\u000201H\u0016J\u0010\u00102\u001a\u00020\u00052\u0006\u00103\u001a\u000204H\u0016J\u0010\u00105\u001a\u00020\u00052\u0006\u00106\u001a\u000207H\u0016J\u0010\u00108\u001a\u00020\u00052\u0006\u00109\u001a\u00020:H\u0016J\u0010\u0010;\u001a\u00020\u00052\u0006\u0010<\u001a\u00020=H\u0016J\u0010\u0010>\u001a\u00020\u00052\u0006\u0010?\u001a\u00020@H\u0016J\u0010\u0010A\u001a\u00020\u00052\u0006\u0010B\u001a\u00020CH\u0016J\u0010\u0010D\u001a\u00020\u00052\u0006\u0010E\u001a\u00020FH\u0016J\u0010\u0010G\u001a\u00020\u00052\u0006\u0010H\u001a\u00020IH\u0016J\u0010\u0010J\u001a\u00020\u00052\u0006\u0010K\u001a\u00020LH\u0016J\u0010\u0010M\u001a\u00020\u00052\u0006\u0010N\u001a\u00020OH\u0016J\u0010\u0010P\u001a\u00020\u00052\u0006\u0010Q\u001a\u00020RH\u0016J\u0010\u0010S\u001a\u00020\u00052\u0006\u0010T\u001a\u00020UH\u0016J\u0010\u0010V\u001a\u00020\u00052\u0006\u0010W\u001a\u00020XH\u0016J\u0016\u0010Y\u001a\u00020\u00052\u0006\u0010W\u001a\u00020X2\u0006\u0010Z\u001a\u00020[J\u0010\u0010\\\u001a\u00020\u00052\u0006\u0010]\u001a\u00020^H\u0016J\u0010\u0010_\u001a\u00020\u00052\u0006\u0010`\u001a\u00020aH\u0016J\u0010\u0010b\u001a\u00020\u00052\u0006\u0010c\u001a\u00020dH\u0016J\u0010\u0010e\u001a\u00020\u00052\u0006\u0010f\u001a\u00020gH\u0016J\u0010\u0010h\u001a\u00020\u00052\u0006\u0010i\u001a\u00020jH\u0016J\u0010\u0010k\u001a\u00020\u00052\u0006\u0010l\u001a\u00020mH\u0016J\u0010\u0010n\u001a\u00020\u00052\u0006\u0010o\u001a\u00020pH\u0016J\u0010\u0010q\u001a\u00020\u00052\u0006\u0010r\u001a\u00020sH\u0016J\u0010\u0010t\u001a\u00020\u00052\u0006\u0010u\u001a\u00020vH\u0016J\u0010\u0010w\u001a\u00020\u00052\u0006\u0010x\u001a\u00020yH\u0016J\u0010\u0010z\u001a\u00020\u00052\u0006\u0010{\u001a\u00020|H\u0016J\u0010\u0010}\u001a\u00020\u00052\u0006\u0010~\u001a\u00020\u007fH\u0016J\u001c\u0010\u0080\u0001\u001a\u00020\u00052\u0006\u0010~\u001a\u00020\u007f2\t\b\u0002\u0010\u0081\u0001\u001a\u00020[H\u0002J\u0013\u0010\u0082\u0001\u001a\u00020\u00052\b\u0010\u0083\u0001\u001a\u00030\u0084\u0001H\u0016J\u0013\u0010\u0085\u0001\u001a\u00020\u00052\b\u0010\u0086\u0001\u001a\u00030\u0087\u0001H\u0016J\u0013\u0010\u0088\u0001\u001a\u00020\u00052\b\u0010\u0089\u0001\u001a\u00030\u008a\u0001H\u0016J\u0013\u0010\u008b\u0001\u001a\u00020\u00052\b\u0010\u008c\u0001\u001a\u00030\u008d\u0001H\u0016J\u0013\u0010\u008e\u0001\u001a\u00020\u00052\b\u0010\u008f\u0001\u001a\u00030\u0090\u0001H\u0016J\u0013\u0010\u0091\u0001\u001a\u00020\u00052\b\u0010\u0092\u0001\u001a\u00030\u0093\u0001H\u0016J\u0013\u0010\u0094\u0001\u001a\u00020\u00052\b\u0010\u0095\u0001\u001a\u00030\u0096\u0001H\u0016J\u0013\u0010\u0097\u0001\u001a\u00020\u00052\b\u0010\u0098\u0001\u001a\u00030\u0099\u0001H\u0016J\u0013\u0010\u009a\u0001\u001a\u00020\u00052\b\u0010\u009b\u0001\u001a\u00030\u009c\u0001H\u0016J\u0013\u0010\u009d\u0001\u001a\u00020\u00052\b\u0010\u009e\u0001\u001a\u00030\u009f\u0001H\u0016J\u0013\u0010 \u0001\u001a\u00020\u00052\b\u0010¡\u0001\u001a\u00030¢\u0001H\u0016J\u0013\u0010£\u0001\u001a\u00020\u00052\b\u0010¤\u0001\u001a\u00030¥\u0001H\u0016J\u0013\u0010©\u0001\u001a\u00020\u00052\b\u0010ª\u0001\u001a\u00030¨\u0001H\u0016J\u0013\u0010«\u0001\u001a\u00020\u00052\b\u0010¬\u0001\u001a\u00030\u00ad\u0001H\u0016J\u0013\u0010®\u0001\u001a\u00020\u00052\b\u0010¯\u0001\u001a\u00030°\u0001H\u0016J\u0013\u0010±\u0001\u001a\u00020\u00052\b\u0010²\u0001\u001a\u00030³\u0001H\u0016J\u0013\u0010´\u0001\u001a\u00020\u00052\b\u0010µ\u0001\u001a\u00030¶\u0001H\u0016J\u0013\u0010·\u0001\u001a\u00020\u00052\b\u0010¸\u0001\u001a\u00030¹\u0001H\u0016J\u0013\u0010º\u0001\u001a\u00020\u00052\b\u0010»\u0001\u001a\u00030¼\u0001H\u0016J\u0013\u0010½\u0001\u001a\u00020\u00052\b\u0010¾\u0001\u001a\u00030¿\u0001H\u0016J\u0013\u0010À\u0001\u001a\u00020\u00052\b\u0010Á\u0001\u001a\u00030Â\u0001H\u0016J\u0013\u0010Ã\u0001\u001a\u00020\u00052\b\u0010Ä\u0001\u001a\u00030Å\u0001H\u0016J\u0013\u0010Æ\u0001\u001a\u00020\u00052\b\u0010Ç\u0001\u001a\u00030È\u0001H\u0016J\u0013\u0010É\u0001\u001a\u00020\u00052\b\u0010Ê\u0001\u001a\u00030Ë\u0001H\u0016J\u0013\u0010Ì\u0001\u001a\u00020\u00052\b\u0010Í\u0001\u001a\u00030Î\u0001H\u0016J\u0013\u0010Ï\u0001\u001a\u00020\u00052\b\u0010Ð\u0001\u001a\u00030Ñ\u0001H\u0016J\u0013\u0010Ò\u0001\u001a\u00020\u00052\b\u0010Ó\u0001\u001a\u00030Ô\u0001H\u0016J\u0013\u0010Õ\u0001\u001a\u00020\u00052\b\u0010Ö\u0001\u001a\u00030×\u0001H\u0016J\u0013\u0010Ø\u0001\u001a\u00020\u00052\b\u0010Ù\u0001\u001a\u00030Ú\u0001H\u0016J\u0013\u0010Û\u0001\u001a\u00020\u00052\b\u0010Ü\u0001\u001a\u00030Ý\u0001H\u0016J\u0013\u0010Þ\u0001\u001a\u00020\u00052\b\u0010ß\u0001\u001a\u00030à\u0001H\u0016J\u0013\u0010á\u0001\u001a\u00020\u00052\b\u0010â\u0001\u001a\u00030ã\u0001H\u0016J\u0013\u0010ä\u0001\u001a\u00020\u00052\b\u0010å\u0001\u001a\u00030æ\u0001H\u0016J\u0013\u0010ç\u0001\u001a\u00020\u00052\b\u0010è\u0001\u001a\u00030é\u0001H\u0016J\u0013\u0010ê\u0001\u001a\u00020\u00052\b\u0010ë\u0001\u001a\u00030ì\u0001H\u0016J\u0013\u0010í\u0001\u001a\u00020\u00052\b\u0010î\u0001\u001a\u00030ï\u0001H\u0016J\u0013\u0010ð\u0001\u001a\u00020\u00052\b\u0010ñ\u0001\u001a\u00030ò\u0001H\u0016J\u0013\u0010ó\u0001\u001a\u00020\u00052\b\u0010ô\u0001\u001a\u00030õ\u0001H\u0016J\u0013\u0010ö\u0001\u001a\u00020\u00052\b\u0010÷\u0001\u001a\u00030ø\u0001H\u0016J\u0013\u0010ù\u0001\u001a\u00020\u00052\b\u0010ú\u0001\u001a\u00030û\u0001H\u0016J\u0013\u0010ü\u0001\u001a\u00020\u00052\b\u0010ý\u0001\u001a\u00030þ\u0001H\u0016J\u0013\u0010ÿ\u0001\u001a\u00020\u00052\b\u0010\u0080\u0002\u001a\u00030\u0081\u0002H\u0016J\u0013\u0010\u0082\u0002\u001a\u00020\u00052\b\u0010\u0083\u0002\u001a\u00030\u0084\u0002H\u0016J\u0013\u0010\u0085\u0002\u001a\u00020\u00052\b\u0010\u0086\u0002\u001a\u00030\u0087\u0002H\u0016J\u0013\u0010\u0088\u0002\u001a\u00020\u00052\b\u0010\u0089\u0002\u001a\u00030\u008a\u0002H\u0016J\u0013\u0010\u008b\u0002\u001a\u00020\u00052\b\u0010\u008c\u0002\u001a\u00030\u008d\u0002H\u0016J\u0013\u0010\u008e\u0002\u001a\u00020\u00052\b\u0010\u008f\u0002\u001a\u00030\u0090\u0002H\u0016J\u0013\u0010\u0091\u0002\u001a\u00020\u00052\b\u0010\u0092\u0002\u001a\u00030\u0093\u0002H\u0016J\u0013\u0010\u0094\u0002\u001a\u00020\u00052\b\u0010\u0095\u0002\u001a\u00030\u0096\u0002H\u0016J\u0013\u0010\u0097\u0002\u001a\u00020\u00052\b\u0010\u0098\u0002\u001a\u00030\u0099\u0002H\u0016J\u0013\u0010\u009a\u0002\u001a\u00020\u00052\b\u0010\u009b\u0002\u001a\u00030\u009c\u0002H\u0016J\u0013\u0010\u009d\u0002\u001a\u00020\u00052\b\u0010\u009e\u0002\u001a\u00030\u009f\u0002H\u0002J\u0013\u0010 \u0002\u001a\u00020\u00052\b\u0010¡\u0002\u001a\u00030¢\u0002H\u0016J\u0013\u0010£\u0002\u001a\u00020\u00052\b\u0010¤\u0002\u001a\u00030¥\u0002H\u0016J\u0013\u0010¦\u0002\u001a\u00020\u00052\b\u0010§\u0002\u001a\u00030¨\u0002H\u0016J\u0013\u0010©\u0002\u001a\u00020\u00052\b\u0010ª\u0002\u001a\u00030\u009f\u0002H\u0016J\u0013\u0010«\u0002\u001a\u00020\u00052\b\u0010¬\u0002\u001a\u00030\u00ad\u0002H\u0016J\u0013\u0010®\u0002\u001a\u00020\u00052\b\u0010¯\u0002\u001a\u00030°\u0002H\u0016J\u0013\u0010±\u0002\u001a\u00020\u00052\b\u0010²\u0002\u001a\u00030³\u0002H\u0016J\u0013\u0010´\u0002\u001a\u00020\u00052\b\u0010µ\u0002\u001a\u00030¶\u0002H\u0016J\u0013\u0010·\u0002\u001a\u00020\u00052\b\u0010¸\u0002\u001a\u00030¹\u0002H\u0016J\u0013\u0010º\u0002\u001a\u00020\u00052\b\u0010»\u0002\u001a\u00030¼\u0002H\u0016J\u0013\u0010½\u0002\u001a\u00020\u00052\b\u0010¾\u0002\u001a\u00030¿\u0002H\u0016J\u0013\u0010À\u0002\u001a\u00020\u00052\b\u0010Á\u0002\u001a\u00030Â\u0002H\u0016J\u0013\u0010Ã\u0002\u001a\u00020\u00052\b\u0010Ä\u0002\u001a\u00030Å\u0002H\u0016J\u0013\u0010Æ\u0002\u001a\u00020\u00052\b\u0010Ç\u0002\u001a\u00030È\u0002H\u0016J\u0013\u0010É\u0002\u001a\u00020\u00052\b\u0010Ê\u0002\u001a\u00030Ë\u0002H\u0016J\u0013\u0010Ì\u0002\u001a\u00020\u00052\b\u0010Í\u0002\u001a\u00030Î\u0002H\u0016J\u0013\u0010Ï\u0002\u001a\u00020\u00052\b\u0010Ð\u0002\u001a\u00030Ñ\u0002H\u0016J\u0013\u0010Ò\u0002\u001a\u00020\u00052\b\u0010Ó\u0002\u001a\u00030Ô\u0002H\u0016J\u0013\u0010Õ\u0002\u001a\u00020\u00052\b\u0010Ö\u0002\u001a\u00030×\u0002H\u0016J\u0013\u0010Ø\u0002\u001a\u00020\u00052\b\u0010Ù\u0002\u001a\u00030Ú\u0002H\u0016J\u0013\u0010Û\u0002\u001a\u00020\u00052\b\u0010Ü\u0002\u001a\u00030Ý\u0002H\u0016J\u0013\u0010Þ\u0002\u001a\u00020\u00052\b\u0010ß\u0002\u001a\u00030à\u0002H\u0016J\u0013\u0010á\u0002\u001a\u00020\u00052\b\u0010â\u0002\u001a\u00030ã\u0002H\u0016J\u0013\u0010ä\u0002\u001a\u00020\u00052\b\u0010å\u0002\u001a\u00030æ\u0002H\u0016J\u0013\u0010ç\u0002\u001a\u00020\u00052\b\u0010è\u0002\u001a\u00030é\u0002H\u0016J\u0013\u0010ê\u0002\u001a\u00020\u00052\b\u0010ë\u0002\u001a\u00030ì\u0002H\u0016J\u0013\u0010í\u0002\u001a\u00020\u00052\b\u0010î\u0002\u001a\u00030ï\u0002H\u0016J\u0013\u0010ð\u0002\u001a\u00020\u00052\b\u0010ñ\u0002\u001a\u00030ò\u0002H\u0016J\u0013\u0010ó\u0002\u001a\u00020\u00052\b\u0010ô\u0002\u001a\u00030õ\u0002H\u0016J\u0013\u0010ö\u0002\u001a\u00020\u00052\b\u0010÷\u0002\u001a\u00030ø\u0002H\u0016J\u0013\u0010ù\u0002\u001a\u00020\u00052\b\u0010ú\u0002\u001a\u00030û\u0002H\u0016J\u0013\u0010ü\u0002\u001a\u00020\u00052\b\u0010ý\u0002\u001a\u00030þ\u0002H\u0016J\u0013\u0010ÿ\u0002\u001a\u00020\u00052\b\u0010\u0080\u0003\u001a\u00030\u0081\u0003H\u0016R\u0017\u0010¦\u0001\u001a\n\u0012\u0005\u0012\u00030¨\u00010§\u0001X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0082\u0003"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirRenderer$Visitor;", "Lorg/jetbrains/kotlin/fir/visitors/FirVisitorVoid;", "<init>", "(Lorg/jetbrains/kotlin/fir/renderer/FirRenderer;)V", "visitElement", Argument.Delimiters.none, "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "visitFile", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "visitScript", "script", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "visitReplSnippet", "replSnippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "visitScriptReceiverParameter", "scriptReceiverParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirScriptReceiverParameter;", "visitReplDeclarationReference", "replDeclarationReference", "Lorg/jetbrains/kotlin/fir/expressions/FirReplDeclarationReference;", "visitReplPropertyInitializer", "replPropertyInitializer", "Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyInitializer;", "visitReplPropertyDelegate", "replPropertyDelegate", "Lorg/jetbrains/kotlin/fir/expressions/FirReplPropertyDelegate;", "visitReplExpressionReference", "replExpressionReference", "Lorg/jetbrains/kotlin/fir/expressions/FirReplExpressionReference;", "visitCodeFragment", "codeFragment", "Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;", "visitAnnotation", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "visitAnnotationCall", "annotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "visitCallableDeclaration", "callableDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "visitTypeParameterRef", "typeParameterRef", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRef;", "visitOuterClassTypeParameterRef", "outerClassTypeParameterRef", "Lorg/jetbrains/kotlin/fir/declarations/FirOuterClassTypeParameterRef;", "visitConstructedClassTypeParameterRef", "constructedClassTypeParameterRef", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructedClassTypeParameterRef;", "visitMemberDeclaration", "memberDeclaration", "Lorg/jetbrains/kotlin/fir/declarations/FirMemberDeclaration;", "visitRegularClass", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "visitEnumEntry", "enumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "visitAnonymousObjectExpression", "anonymousObjectExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousObjectExpression;", "visitAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "visitVariable", "variable", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "visitField", "field", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "visitProperty", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "visitErrorProperty", "errorProperty", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorProperty;", "visitErrorFunction", "errorFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorFunction;", "visitBackingField", "backingField", "Lorg/jetbrains/kotlin/fir/declarations/FirBackingField;", "visitReceiverParameter", "receiverParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirReceiverParameter;", "renderReceiverParameter", "isExplicit", Argument.Delimiters.none, "visitNamedFunction", "namedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "visitConstructor", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "visitErrorPrimaryConstructor", "errorPrimaryConstructor", "Lorg/jetbrains/kotlin/fir/declarations/FirErrorPrimaryConstructor;", "visitPropertyAccessor", "propertyAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "visitAnonymousFunctionExpression", "anonymousFunctionExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "visitAnonymousFunction", "anonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "visitFunction", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "visitAnonymousInitializer", "anonymousInitializer", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "visitDanglingModifierList", "danglingModifierList", "Lorg/jetbrains/kotlin/fir/declarations/FirDanglingModifierList;", "visitBlock", "block", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "visitTypeAlias", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "visitTypeParameter", "typeParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "renderTypeParameter", "forOuterTypeRef", "visitSafeCallExpression", "safeCallExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "visitCheckedSafeCallSubject", "checkedSafeCallSubject", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckedSafeCallSubject;", "visitValueParameter", "valueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "visitImport", "import", "Lorg/jetbrains/kotlin/fir/declarations/FirImport;", "visitStatement", "statement", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "visitReturnExpression", "returnExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "visitWhenBranch", "whenBranch", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenBranch;", "visitWhenExpression", "whenExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "visitWhenSubjectExpression", "whenSubjectExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenSubjectExpression;", "visitTryExpression", "tryExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;", "visitDoWhileLoop", "doWhileLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirDoWhileLoop;", "visitWhileLoop", "whileLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirWhileLoop;", "loopJumpStack", "Ljava/util/Stack;", "Lorg/jetbrains/kotlin/fir/expressions/FirLoopJump;", "visitLoopJump", "loopJump", "visitBreakExpression", "breakExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBreakExpression;", "visitContinueExpression", "continueExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirContinueExpression;", "visitExpression", "expression", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "visitLazyExpression", "lazyExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirLazyExpression;", "visitLiteralExpression", "literalExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "visitWrappedDelegateExpression", "wrappedDelegateExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWrappedDelegateExpression;", "visitEnumEntryDeserializedAccessExpression", "enumEntryDeserializedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirEnumEntryDeserializedAccessExpression;", "visitNamedArgumentExpression", "namedArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirNamedArgumentExpression;", "visitSpreadArgumentExpression", "spreadArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSpreadArgumentExpression;", "visitVarargArgumentsExpression", "varargArgumentsExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirVarargArgumentsExpression;", "visitFunctionTypeConversionExpression", "functionTypeConversionExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionTypeConversionExpression;", "visitCall", K2JsArgumentConstants.CALL, "Lorg/jetbrains/kotlin/fir/expressions/FirCall;", "visitStringConcatenationCall", "stringConcatenationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirStringConcatenationCall;", "visitTypeOperatorCall", "typeOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "visitDelegatedConstructorCall", "delegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirDelegatedConstructorCall;", "visitMultiDelegatedConstructorCall", "multiDelegatedConstructorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirMultiDelegatedConstructorCall;", "visitTypeRef", "typeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "visitErrorTypeRef", "errorTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirErrorTypeRef;", "visitImplicitTypeRef", "implicitTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirImplicitTypeRef;", "visitUnresolvedTypeRef", "unresolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirUnresolvedTypeRef;", "visitDynamicTypeRef", "dynamicTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirDynamicTypeRef;", "visitFunctionTypeRef", "functionTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirFunctionTypeRef;", "visitResolvedTypeRef", "resolvedTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirResolvedTypeRef;", "visitUserTypeRef", "userTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirUserTypeRef;", "visitTypeProjection", "typeProjection", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "visitTypeProjectionWithVariance", "typeProjectionWithVariance", "Lorg/jetbrains/kotlin/fir/types/FirTypeProjectionWithVariance;", "visitStarProjection", "starProjection", "Lorg/jetbrains/kotlin/fir/types/FirStarProjection;", "visitNamedReference", "namedReference", "Lorg/jetbrains/kotlin/fir/references/FirNamedReference;", "visitNamedReferenceWithCandidateBase", "namedReferenceWithCandidateBase", "Lorg/jetbrains/kotlin/fir/references/FirNamedReferenceWithCandidateBase;", "visitErrorNamedReference", "errorNamedReference", "Lorg/jetbrains/kotlin/fir/references/FirErrorNamedReference;", "visitBackingFieldReference", "backingFieldReference", "Lorg/jetbrains/kotlin/fir/references/FirBackingFieldReference;", "visitDelegateFieldReference", "delegateFieldReference", "Lorg/jetbrains/kotlin/fir/references/FirDelegateFieldReference;", "visitResolvedNamedReference", "resolvedNamedReference", "Lorg/jetbrains/kotlin/fir/references/FirResolvedNamedReference;", "visitResolvedErrorReference", "resolvedErrorReference", "Lorg/jetbrains/kotlin/fir/references/FirResolvedErrorReference;", "visitResolvedCallableReference", "resolvedCallableReference", "Lorg/jetbrains/kotlin/fir/references/FirResolvedCallableReference;", "visitPropertyWithExplicitBackingFieldResolvedNamedReference", "propertyWithExplicitBackingFieldResolvedNamedReference", "Lorg/jetbrains/kotlin/fir/references/FirPropertyWithExplicitBackingFieldResolvedNamedReference;", "visitThisReference", "thisReference", "Lorg/jetbrains/kotlin/fir/references/FirThisReference;", "visitSuperReference", "superReference", "Lorg/jetbrains/kotlin/fir/references/FirSuperReference;", "visitQualifiedAccessExpressionReceivers", "qualifiedAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "visitCheckNotNullCall", "checkNotNullCall", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckNotNullCall;", "visitElvisExpression", "elvisExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirElvisExpression;", "visitCallableReferenceAccess", "callableReferenceAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "visitQualifiedAccessExpression", "qualifiedAccessExpression", "visitQualifiedErrorAccessExpression", "qualifiedErrorAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedErrorAccessExpression;", "visitSuperReceiverExpression", "superReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSuperReceiverExpression;", "visitPropertyAccessExpression", "propertyAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "visitThisReceiverExpression", "thisReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "visitSmartCastExpression", "smartCastExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "visitDesugaredAssignmentValueReferenceExpression", "desugaredAssignmentValueReferenceExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirDesugaredAssignmentValueReferenceExpression;", "visitVariableAssignment", "variableAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "visitIndexedAccessAugmentedAssignment", "indexedAccessAugmentedAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirIndexedAccessAugmentedAssignment;", "visitFunctionCall", "functionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "visitIntegerLiteralOperatorCall", "integerLiteralOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirIntegerLiteralOperatorCall;", "visitImplicitInvokeCall", "implicitInvokeCall", "Lorg/jetbrains/kotlin/fir/expressions/FirImplicitInvokeCall;", "visitComparisonExpression", "comparisonExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirComparisonExpression;", "visitAugmentedAssignment", "augmentedAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirAugmentedAssignment;", "visitIncrementDecrementExpression", "incrementDecrementExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirIncrementDecrementExpression;", "visitEqualityOperatorCall", "equalityOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "visitComponentCall", "componentCall", "Lorg/jetbrains/kotlin/fir/expressions/FirComponentCall;", "visitGetClassCall", "getClassCall", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "visitClassReferenceExpression", "classReferenceExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirClassReferenceExpression;", "visitCollectionLiteral", "collectionLiteral", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "visitThrowExpression", "throwExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThrowExpression;", "visitErrorExpression", "errorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorExpression;", "visitResolvedQualifier", "resolvedQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "visitErrorResolvedQualifier", "errorResolvedQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorResolvedQualifier;", "visitBooleanOperatorExpression", "booleanOperatorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "visitEffectDeclaration", "effectDeclaration", "Lorg/jetbrains/kotlin/fir/contracts/FirEffectDeclaration;", "visitContractDescription", "contractDescription", "Lorg/jetbrains/kotlin/fir/contracts/FirContractDescription;", "visitPackageDirective", "packageDirective", "Lorg/jetbrains/kotlin/fir/FirPackageDirective;", "visitResolvedReifiedParameterReference", "resolvedReifiedParameterReference", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedReifiedParameterReference;", "visitInaccessibleReceiverExpression", "inaccessibleReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirInaccessibleReceiverExpression;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class Visitor extends FirVisitorVoid {
        private final Stack<FirLoopJump> loopJumpStack = new Stack<>();

        public Visitor() {
        }

        public static FunctionTypeKind b(ConeKotlinType coneKotlinType) {
            ClassId classId;
            coneKotlinType.getClass();
            if ((coneKotlinType instanceof ConeFlexibleType) || (classId = ConeTypeUtilsKt.getClassId(coneKotlinType)) == null) {
                return null;
            }
            FunctionTypeKindExtractor functionTypeKindExtractor = FunctionTypeKindExtractor.Companion.getDefault();
            FqName packageFqName = classId.getPackageFqName();
            String strAsString = classId.getShortClassName().asString();
            strAsString.getClass();
            return functionTypeKindExtractor.getFunctionalClassKind(packageFqName, strAsString);
        }

        private final void renderTypeParameter(FirTypeParameter typeParameter, boolean forOuterTypeRef) {
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, typeParameter, null, 2, null);
            }
            FirModifierRenderer modifierRenderer = FirRenderer.this.getModifierRenderer();
            if (modifierRenderer != null) {
                modifierRenderer.renderModifiers(typeParameter);
            }
            FirRenderer.this.renderPhaseAndAttributes(typeParameter);
            FirRenderer.this.renderVariance(typeParameter.getVariance());
            FirRenderer firRenderer = FirRenderer.this;
            if (forOuterTypeRef) {
                firRenderer.print("Outer(" + typeParameter.getName() + ')');
            } else {
                firRenderer.print(typeParameter.getName());
            }
            List<FirTypeRef> bounds = typeParameter.getBounds();
            ArrayList arrayList = new ArrayList();
            for (Object obj : bounds) {
                FirResolvedTypeRef firResolvedTypeRef = (FirTypeRef) obj;
                if (firResolvedTypeRef instanceof FirResolvedTypeRef) {
                    FirResolvedTypeRef firResolvedTypeRef2 = firResolvedTypeRef;
                    if (ConeTypeUtilsKt.isMarkedOrFlexiblyNullable(firResolvedTypeRef2.getConeType())) {
                        ConeKotlinType coneType = firResolvedTypeRef2.getConeType();
                        ConeLookupTagBasedType coneLookupTagBasedType = coneType instanceof ConeLookupTagBasedType ? (ConeLookupTagBasedType) coneType : null;
                        if (coneLookupTagBasedType != null) {
                            ConeClassifierLookupTag lookupTag = coneLookupTagBasedType.getLookupTag();
                            ConeClassLikeLookupTag coneClassLikeLookupTag = lookupTag instanceof ConeClassLikeLookupTag ? (ConeClassLikeLookupTag) lookupTag : null;
                            if (!Intrinsics.areEqual(coneClassLikeLookupTag != null ? coneClassLikeLookupTag.getClassId() : null, StandardClassIds.INSTANCE.getAny())) {
                            }
                        }
                    }
                }
                arrayList.add(obj);
            }
            if (arrayList.isEmpty()) {
                return;
            }
            FirRenderer.this.print(" : ");
            FirRenderer firRenderer2 = FirRenderer.this;
            firRenderer2.renderSeparated(arrayList, firRenderer2.getVisitor());
        }

        public static /* synthetic */ void renderTypeParameter$default(Visitor visitor, FirTypeParameter firTypeParameter, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = false;
            }
            visitor.renderTypeParameter(firTypeParameter, z);
        }

        private final void visitQualifiedAccessExpressionReceivers(FirQualifiedAccessExpression qualifiedAccess) {
            FirContextArgumentRenderer contextArgumentRenderer = FirRenderer.this.getContextArgumentRenderer();
            if (contextArgumentRenderer != null) {
                contextArgumentRenderer.renderContextArguments(qualifiedAccess);
            }
            FirExpression explicitReceiver = qualifiedAccess.getExplicitReceiver();
            FirExpression dispatchReceiver = qualifiedAccess.getDispatchReceiver();
            FirExpression extensionReceiver = qualifiedAccess.getExtensionReceiver();
            if (dispatchReceiver != null && extensionReceiver != null) {
                FirRenderer.this.print("(");
                dispatchReceiver.accept(this);
                FirRenderer.this.print(", ");
                extensionReceiver.accept(this);
                FirRenderer.this.print(")");
            } else if (dispatchReceiver != null) {
                dispatchReceiver.accept(this);
            } else if (extensionReceiver != null) {
                extensionReceiver.accept(this);
            } else if (explicitReceiver == null) {
                return;
            } else {
                explicitReceiver.accept(this);
            }
            FirRenderer.this.print(".");
        }

        public final void renderReceiverParameter(FirReceiverParameter receiverParameter, boolean isExplicit) {
            receiverParameter.getClass();
            FirRenderer.this.renderPhaseAndAttributes(receiverParameter);
            FirRenderer firRenderer = FirRenderer.this;
            if (isExplicit) {
                FirAnnotationRenderer annotationRenderer = firRenderer.getAnnotationRenderer();
                if (annotationRenderer != null) {
                    FirAnnotationRenderer.render$default(annotationRenderer, receiverParameter, null, 2, null);
                }
                FirRenderer.this.print("<explicit receiver parameter>: ");
            } else {
                FirAnnotationRenderer annotationRenderer2 = firRenderer.getAnnotationRenderer();
                if (annotationRenderer2 != null) {
                    annotationRenderer2.render(receiverParameter, AnnotationUseSiteTarget.RECEIVER);
                }
            }
            receiverParameter.getTypeRef().accept(this);
        }

        public void visitAnnotation(FirAnnotation annotation) {
            annotation.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.renderAnnotation$org_jetbrains_kotlin_tree$default(annotationRenderer, annotation, null, 2, null);
            }
        }

        public void visitAnnotationCall(FirAnnotationCall annotationCall) {
            annotationCall.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.renderAnnotation$org_jetbrains_kotlin_tree$default(annotationRenderer, annotationCall, null, 2, null);
            }
        }

        public void visitAnonymousFunction(FirAnonymousFunction anonymousFunction) {
            anonymousFunction.getClass();
            FirRenderer.this.renderContexts(anonymousFunction.getContextParameters(), false);
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, anonymousFunction, null, 2, null);
            }
            FirModifierRenderer modifierRenderer = FirRenderer.this.getModifierRenderer();
            if (modifierRenderer != null) {
                modifierRenderer.renderModifiers(anonymousFunction);
            }
            FirDeclarationRenderer declarationRenderer = FirRenderer.this.getDeclarationRenderer();
            if (declarationRenderer != null) {
                declarationRenderer.render(anonymousFunction);
            }
            FirRenderer.this.print(Argument.Delimiters.space);
            FirReceiverParameter receiverParameter = anonymousFunction.getReceiverParameter();
            if (receiverParameter != null) {
                renderReceiverParameter(receiverParameter, false);
                FirRenderer.this.print(".");
            }
            FirRenderer.this.print(SpecialNames.ANONYMOUS_STRING);
            if (anonymousFunction.getValueParameters().isEmpty() && anonymousFunction.getHasExplicitParameterList() && (anonymousFunction.getReturnTypeRef() instanceof FirImplicitTypeRef)) {
                FirRenderer.this.print("(<no-parameters>)");
            }
            FirCallableSignatureRenderer callableSignatureRenderer = FirRenderer.this.getCallableSignatureRenderer();
            if (callableSignatureRenderer != null) {
                callableSignatureRenderer.renderParameters(anonymousFunction.getValueParameters());
            }
            FirRenderer.this.print(": ");
            anonymousFunction.getReturnTypeRef().accept(this);
            FirRenderer.this.print(" <inline=" + anonymousFunction.getInlineStatus());
            if (anonymousFunction.getInvocationKind() != null) {
                FirRenderer.this.print(", kind=" + anonymousFunction.getInvocationKind());
            }
            FirRenderer.this.print("> ");
            FirBodyRenderer bodyRenderer = FirRenderer.this.getBodyRenderer();
            if (bodyRenderer != null) {
                bodyRenderer.render(anonymousFunction);
            }
        }

        public void visitAnonymousFunctionExpression(FirAnonymousFunctionExpression anonymousFunctionExpression) {
            anonymousFunctionExpression.getClass();
            if (anonymousFunctionExpression.getIsTrailingLambda()) {
                FirRenderer.this.print("<L> = ");
            }
            visitAnonymousFunction(anonymousFunctionExpression.getAnonymousFunction());
        }

        public void visitAnonymousInitializer(FirAnonymousInitializer anonymousInitializer) {
            anonymousInitializer.getClass();
            FirRenderer.this.renderPhaseAndAttributes(anonymousInitializer);
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, anonymousInitializer, null, 2, null);
            }
            FirRenderer.this.print("init");
            FirBodyRenderer bodyRenderer = FirRenderer.this.getBodyRenderer();
            if (bodyRenderer != null) {
                FirBodyRenderer.renderBody$default(bodyRenderer, anonymousInitializer.getBody(), null, 2, null);
            }
        }

        public void visitAnonymousObject(FirAnonymousObject anonymousObject) {
            anonymousObject.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, anonymousObject, null, 2, null);
            }
            FirRenderer.this.print("object : ");
            FirRenderer.this.renderSeparated(anonymousObject.getSuperTypeRefs(), FirRenderer.this.getVisitor());
            FirClassMemberRenderer classMemberRenderer = FirRenderer.this.getClassMemberRenderer();
            if (classMemberRenderer != null) {
                classMemberRenderer.render(anonymousObject.getDeclarations());
            }
        }

        public void visitAnonymousObjectExpression(FirAnonymousObjectExpression anonymousObjectExpression) {
            anonymousObjectExpression.getClass();
            anonymousObjectExpression.getAnonymousObject().accept(this);
        }

        public void visitAugmentedAssignment(FirAugmentedAssignment augmentedAssignment) {
            augmentedAssignment.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, augmentedAssignment, null, 2, null);
            }
            FirRenderer.this.print(augmentedAssignment.getOperation().getOperator());
            FirRenderer.this.print("(");
            augmentedAssignment.getLeftArgument().accept(FirRenderer.this.getVisitor());
            FirRenderer.this.print(", ");
            augmentedAssignment.getRightArgument().accept(FirRenderer.this.getVisitor());
            FirRenderer.this.print(")");
        }

        public void visitBackingField(FirBackingField backingField) {
            backingField.getClass();
            visitVariable(backingField);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void visitBackingFieldReference(FirBackingFieldReference backingFieldReference) {
            backingFieldReference.getClass();
            FirRenderer.this.print("F|");
            FirRenderer.this.print(((FirBackingField) backingFieldReference.getResolvedSymbol().getFir()).getPropertySymbol().getCallableIdForRendering());
            FirRenderer.this.print("|");
        }

        public void visitBlock(FirBlock block) {
            block.getClass();
            FirBodyRenderer bodyRenderer = FirRenderer.this.getBodyRenderer();
            if (bodyRenderer != null) {
                FirBodyRenderer.renderBody$default(bodyRenderer, block, null, 2, null);
            }
        }

        public void visitBooleanOperatorExpression(FirBooleanOperatorExpression booleanOperatorExpression) {
            booleanOperatorExpression.getClass();
            booleanOperatorExpression.getLeftOperand().accept(this);
            FirRenderer.this.print(Argument.Delimiters.space + booleanOperatorExpression.getKind().getToken() + ' ');
            booleanOperatorExpression.getRightOperand().accept(this);
        }

        public void visitBreakExpression(FirBreakExpression breakExpression) {
            breakExpression.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, breakExpression, null, 2, null);
            }
            FirRenderer.this.print("break");
            visitLoopJump(breakExpression);
        }

        public void visitCall(FirCall call) {
            call.getClass();
            FirCallArgumentsRenderer callArgumentsRenderer = FirRenderer.this.getCallArgumentsRenderer();
            if (callArgumentsRenderer != null) {
                callArgumentsRenderer.renderArguments(call.getArgumentList().getArguments());
            }
        }

        public void visitCallableDeclaration(FirCallableDeclaration callableDeclaration) {
            FirCallableSignatureRenderer callableSignatureRenderer;
            FirBackingField backingField;
            List<FirAnnotation> annotations;
            callableDeclaration.getClass();
            FirRenderer.renderContexts$default(FirRenderer.this, callableDeclaration.getContextParameters(), false, 2, null);
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, callableDeclaration, null, 2, null);
            }
            boolean z = callableDeclaration instanceof FirProperty;
            if (z && (backingField = ((FirProperty) callableDeclaration).getBackingField()) != null && (annotations = backingField.getAnnotations()) != null && (!annotations.isEmpty())) {
                if (FirRenderer.this.renderFieldAnnotationSeparately) {
                    FirRenderer.this.print("field:");
                }
                FirAnnotationRenderer annotationRenderer2 = FirRenderer.this.getAnnotationRenderer();
                if (annotationRenderer2 != null) {
                    FirAnnotationRenderer.render$default(annotationRenderer2, backingField, null, 2, null);
                }
            }
            visitMemberDeclaration(callableDeclaration);
            if (!z || !Intrinsics.areEqual(ClassMembersKt.isCatchParameter((FirProperty) callableDeclaration), Boolean.TRUE)) {
                FirRenderer.this.print(Argument.Delimiters.space);
            }
            FirReceiverParameter receiverParameter = callableDeclaration.getReceiverParameter();
            if (receiverParameter != null) {
                renderReceiverParameter(receiverParameter, false);
                FirRenderer.this.print(".");
            }
            if (callableDeclaration instanceof FirNamedFunction) {
                FirRenderer.this.getIdRenderer().renderCallableId(((FirNamedFunction) callableDeclaration).getSymbol().getCallableId());
            } else if (callableDeclaration instanceof FirVariable) {
                FirRenderer.this.getIdRenderer().renderCallableId(((FirVariable) callableDeclaration).getSymbol().getCallableIdForRendering());
            }
            if ((callableDeclaration instanceof FirFunction) && (callableSignatureRenderer = FirRenderer.this.getCallableSignatureRenderer()) != null) {
                callableSignatureRenderer.renderParameters(((FirFunction) callableDeclaration).getValueParameters());
            }
            FirCallableSignatureRenderer callableSignatureRenderer2 = FirRenderer.this.getCallableSignatureRenderer();
            if (callableSignatureRenderer2 != null) {
                callableSignatureRenderer2.renderReturnTypePrefix();
            }
            FirCallableSignatureRenderer callableSignatureRenderer3 = FirRenderer.this.getCallableSignatureRenderer();
            if (callableSignatureRenderer3 != null) {
                callableSignatureRenderer3.renderCallableType(callableDeclaration);
            }
            ConeContractRenderer contractRenderer = FirRenderer.this.getContractRenderer();
            if (contractRenderer != null) {
                contractRenderer.render(callableDeclaration);
            }
        }

        public void visitCallableReferenceAccess(FirCallableReferenceAccess callableReferenceAccess) {
            FirCallArgumentsRenderer callArgumentsRenderer;
            callableReferenceAccess.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, callableReferenceAccess, null, 2, null);
            }
            FirContextArgumentRenderer contextArgumentRenderer = FirRenderer.this.getContextArgumentRenderer();
            if (contextArgumentRenderer != null) {
                contextArgumentRenderer.renderContextArguments(callableReferenceAccess);
            }
            FirExpression explicitReceiver = callableReferenceAccess.getExplicitReceiver();
            if (explicitReceiver != null) {
                explicitReceiver.accept(this);
            }
            if (callableReferenceAccess.getHasQuestionMarkAtLHS() && !(callableReferenceAccess.getExplicitReceiver() instanceof FirResolvedQualifier)) {
                FirRenderer.this.print("?");
            }
            FirRenderer.this.print("::");
            callableReferenceAccess.getCalleeReference().accept(this);
            FirArgumentList errorArgumentList = callableReferenceAccess.getErrorArgumentList();
            if (errorArgumentList == null || (callArgumentsRenderer = FirRenderer.this.getCallArgumentsRenderer()) == null) {
                return;
            }
            callArgumentsRenderer.renderArguments(errorArgumentList.getArguments());
        }

        public void visitCheckNotNullCall(FirCheckNotNullCall checkNotNullCall) {
            checkNotNullCall.getClass();
            ((FirExpression) CollectionsKt.first(checkNotNullCall.getArgumentList().getArguments())).accept(this);
            FirRenderer.this.print("!!");
        }

        public void visitCheckedSafeCallSubject(FirCheckedSafeCallSubject checkedSafeCallSubject) {
            checkedSafeCallSubject.getClass();
            FirRenderer.this.print("$subj$");
        }

        public void visitClassReferenceExpression(FirClassReferenceExpression classReferenceExpression) {
            classReferenceExpression.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, classReferenceExpression, null, 2, null);
            }
            FirRenderer.this.print("<getClass>");
            FirRenderer.this.print("(");
            classReferenceExpression.getClassTypeRef().accept(this);
            FirRenderer.this.print(")");
        }

        public void visitCodeFragment(FirCodeFragment codeFragment) {
            codeFragment.getClass();
            FirRenderer.this.getPrinter().print("CODE FRAGMENT:");
            FirBodyRenderer bodyRenderer = FirRenderer.this.getBodyRenderer();
            if (bodyRenderer != null) {
                FirBodyRenderer.renderBody$default(bodyRenderer, codeFragment.getBlock(), null, 2, null);
            }
        }

        public void visitCollectionLiteral(FirCollectionLiteral collectionLiteral) {
            collectionLiteral.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, collectionLiteral, null, 2, null);
            }
            FirRenderer.this.print("<collectionLiteralCall>");
            visitCall(collectionLiteral);
        }

        public void visitComparisonExpression(FirComparisonExpression comparisonExpression) {
            comparisonExpression.getClass();
            FirRenderer.this.print("CMP(" + comparisonExpression.getOperation().getOperator() + ", ");
            comparisonExpression.getCompareToCall().accept(this);
            FirRenderer.this.print(")");
        }

        public void visitComponentCall(FirComponentCall componentCall) {
            componentCall.getClass();
            visitFunctionCall(componentCall);
        }

        public void visitConstructedClassTypeParameterRef(FirConstructedClassTypeParameterRef constructedClassTypeParameterRef) {
            constructedClassTypeParameterRef.getClass();
            visitTypeParameterRef(constructedClassTypeParameterRef);
        }

        public void visitConstructor(FirConstructor constructor) {
            FirBodyRenderer bodyRenderer;
            constructor.getClass();
            FirRenderer.renderContexts$default(FirRenderer.this, constructor.getContextParameters(), false, 2, null);
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, constructor, null, 2, null);
            }
            FirModifierRenderer modifierRenderer = FirRenderer.this.getModifierRenderer();
            if (modifierRenderer != null) {
                modifierRenderer.renderModifiers(constructor);
            }
            FirDeclarationRenderer declarationRenderer = FirRenderer.this.getDeclarationRenderer();
            if (declarationRenderer != null) {
                declarationRenderer.render(constructor);
            }
            FirRenderer.this.renderTypeParameters(constructor.getTypeParameters());
            FirCallableSignatureRenderer callableSignatureRenderer = FirRenderer.this.getCallableSignatureRenderer();
            if (callableSignatureRenderer != null) {
                callableSignatureRenderer.renderParameters(constructor.getValueParameters());
            }
            FirRenderer.this.print(": ");
            constructor.getReturnTypeRef().accept(this);
            FirBlock body = constructor.getBody();
            FirDelegatedConstructorCall delegatedConstructor = constructor.getDelegatedConstructor();
            if (body == null && (bodyRenderer = FirRenderer.this.getBodyRenderer()) != null) {
                bodyRenderer.renderDelegatedConstructor(delegatedConstructor);
            }
            ConeContractRenderer contractRenderer = FirRenderer.this.getContractRenderer();
            if (contractRenderer != null) {
                contractRenderer.render(constructor);
            }
            FirBodyRenderer bodyRenderer2 = FirRenderer.this.getBodyRenderer();
            if (bodyRenderer2 != null) {
                bodyRenderer2.renderBody(body, CollectionsKt.listOfNotNull(delegatedConstructor));
            }
        }

        public void visitContinueExpression(FirContinueExpression continueExpression) {
            continueExpression.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, continueExpression, null, 2, null);
            }
            FirRenderer.this.print("continue");
            visitLoopJump(continueExpression);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
        public void visitContractDescription(FirContractDescription contractDescription) throws KotlinNothingValueException {
            contractDescription.getClass();
            AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
            throw new KotlinNothingValueException();
        }

        public void visitDanglingModifierList(FirDanglingModifierList danglingModifierList) {
            danglingModifierList.getClass();
            FirRenderer.this.renderPhaseAndAttributes(danglingModifierList);
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, danglingModifierList, null, 2, null);
            }
            FirRenderer.renderContexts$default(FirRenderer.this, danglingModifierList.getContextParameters(), false, 2, null);
            FirRenderer firRenderer = FirRenderer.this;
            firRenderer.print(firRenderer.getTypeRenderer().renderDiagnostic(danglingModifierList.getDiagnostic(), "<DANGLING MODIFIER: ", ">"));
        }

        public void visitDelegateFieldReference(FirDelegateFieldReference delegateFieldReference) {
            delegateFieldReference.getClass();
            FirRenderer.this.print("D|");
            FirRenderer.this.print(delegateFieldReference.getResolvedSymbol().getCallableIdForRendering());
            FirRenderer.this.print("|");
        }

        public void visitDelegatedConstructorCall(FirDelegatedConstructorCall delegatedConstructorCall) {
            delegatedConstructorCall.getClass();
            boolean z = delegatedConstructorCall instanceof FirLazyDelegatedConstructorCall;
            if (!z) {
                FirContextArgumentRenderer contextArgumentRenderer = FirRenderer.this.getContextArgumentRenderer();
                if (contextArgumentRenderer != null) {
                    contextArgumentRenderer.renderContextArguments(delegatedConstructorCall);
                }
                FirExpression dispatchReceiver = delegatedConstructorCall.getDispatchReceiver();
                if (dispatchReceiver != null) {
                    dispatchReceiver.accept(this);
                    FirRenderer.this.print(".");
                }
            }
            if (z) {
                FirRenderer.this.print("LAZY_");
            }
            if (delegatedConstructorCall.isSuper()) {
                FirRenderer.this.print("super<");
            } else if (delegatedConstructorCall.getIsThis()) {
                FirRenderer.this.print("this<");
            }
            delegatedConstructorCall.getConstructedTypeRef().accept(this);
            FirRenderer.this.print(">");
            if (delegatedConstructorCall instanceof FirLazyDelegatedConstructorCall) {
                return;
            }
            visitCall(delegatedConstructorCall);
        }

        public void visitDesugaredAssignmentValueReferenceExpression(FirDesugaredAssignmentValueReferenceExpression desugaredAssignmentValueReferenceExpression) {
            desugaredAssignmentValueReferenceExpression.getClass();
            desugaredAssignmentValueReferenceExpression.getExpressionRef().getValue().accept(this);
        }

        public void visitDoWhileLoop(FirDoWhileLoop doWhileLoop) {
            doWhileLoop.getClass();
            FirLabel label = doWhileLoop.getLabel();
            if (label != null) {
                FirRenderer.this.print(label.getName() + '@');
            }
            FirRenderer.this.print("do");
            doWhileLoop.getBlock().accept(this);
            FirRenderer.this.print("while(");
            doWhileLoop.getCondition().accept(this);
            FirRenderer.this.print(")");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void visitDynamicTypeRef(FirDynamicTypeRef dynamicTypeRef) {
            dynamicTypeRef.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, dynamicTypeRef, null, 2, null);
            }
            FirRenderer.this.print("<dynamic>");
            visitUnresolvedTypeRef(dynamicTypeRef);
        }

        public void visitEffectDeclaration(FirEffectDeclaration effectDeclaration) {
            effectDeclaration.getClass();
            ConeContractRenderer contractRenderer = FirRenderer.this.getContractRenderer();
            if (contractRenderer != null) {
                contractRenderer.render(effectDeclaration);
            }
        }

        public void visitElement(FirElement element) {
            element.getClass();
            element.acceptChildren(this);
        }

        public void visitElvisExpression(FirElvisExpression elvisExpression) {
            elvisExpression.getClass();
            elvisExpression.getLhs().accept(this);
            FirRenderer.this.print(" ?: ");
            elvisExpression.getRhs().accept(this);
        }

        public void visitEnumEntry(FirEnumEntry enumEntry) {
            enumEntry.getClass();
            visitVariable(enumEntry);
        }

        public void visitEnumEntryDeserializedAccessExpression(FirEnumEntryDeserializedAccessExpression enumEntryDeserializedAccessExpression) {
            enumEntryDeserializedAccessExpression.getClass();
            FirRenderer firRenderer = FirRenderer.this;
            StringBuilder sb = new StringBuilder();
            sb.append(enumEntryDeserializedAccessExpression.getEnumClassId());
            sb.append('.');
            sb.append(enumEntryDeserializedAccessExpression.getEnumEntryName());
            firRenderer.print(sb.toString());
        }

        public void visitEqualityOperatorCall(FirEqualityOperatorCall equalityOperatorCall) {
            equalityOperatorCall.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, equalityOperatorCall, null, 2, null);
            }
            FirRenderer.this.print(equalityOperatorCall.getOperation().getOperator());
            visitCall(equalityOperatorCall);
        }

        public void visitErrorExpression(FirErrorExpression errorExpression) {
            errorExpression.getClass();
            FirErrorExpressionRenderer errorExpressionRenderer = FirRenderer.this.getErrorExpressionRenderer();
            if (errorExpressionRenderer != null) {
                errorExpressionRenderer.renderErrorExpression(errorExpression);
            }
        }

        public void visitErrorFunction(FirErrorFunction errorFunction) {
            errorFunction.getClass();
            visitFunction(errorFunction);
        }

        public void visitErrorNamedReference(FirErrorNamedReference errorNamedReference) {
            errorNamedReference.getClass();
            FirRenderer firRenderer = FirRenderer.this;
            firRenderer.print(firRenderer.getTypeRenderer().renderDiagnostic(errorNamedReference.getDiagnostic(), "<", ">#"));
        }

        public void visitErrorPrimaryConstructor(FirErrorPrimaryConstructor errorPrimaryConstructor) {
            errorPrimaryConstructor.getClass();
            visitConstructor(errorPrimaryConstructor);
        }

        public void visitErrorProperty(FirErrorProperty errorProperty) {
            errorProperty.getClass();
            visitProperty(errorProperty);
        }

        public void visitErrorResolvedQualifier(FirErrorResolvedQualifier errorResolvedQualifier) {
            errorResolvedQualifier.getClass();
            visitResolvedQualifier(errorResolvedQualifier);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void visitErrorTypeRef(FirErrorTypeRef errorTypeRef) {
            errorTypeRef.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, errorTypeRef, null, 2, null);
            }
            FirRenderer firRenderer = FirRenderer.this;
            firRenderer.print(firRenderer.getTypeRenderer().renderDiagnostic(errorTypeRef.getDiagnostic(), "<ERROR TYPE REF: ", ">"));
        }

        public void visitExpression(FirExpression expression) {
            String str;
            expression.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, expression, null, 2, null);
            }
            FirRenderer firRenderer = FirRenderer.this;
            if (expression instanceof FirExpressionStub) {
                str = "STUB";
            } else if (expression instanceof FirUnitExpression) {
                str = "Unit";
            } else if (expression instanceof FirElseIfTrueCondition) {
                str = "else";
            } else {
                str = "??? " + expression.getClass();
            }
            firRenderer.print(str);
        }

        public void visitField(FirField field) {
            field.getClass();
            visitVariable(field);
            FirRenderer.this.getPrinter().newLine();
        }

        public void visitFile(FirFile file) {
            file.getClass();
            FirRenderer.this.getPrinter().print("FILE: ");
            FirRenderer.this.renderPhaseAndAttributes(file);
            FirRenderer.this.getPrinter().println(file.getName());
            FirRenderer.this.getPrinter().pushIndent$org_jetbrains_kotlin_tree();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, file, null, 2, null);
            }
            visitPackageDirective(file.getPackageDirective());
            Iterator<T> it = file.getImports().iterator();
            while (it.hasNext()) {
                ((FirImport) it.next()).accept(this);
            }
            Iterator<T> it2 = file.getDeclarations().iterator();
            while (it2.hasNext()) {
                ((FirDeclaration) it2.next()).accept(this);
            }
            FirRenderer.this.getPrinter().popIndent$org_jetbrains_kotlin_tree();
        }

        public void visitFunction(FirFunction function) {
            function.getClass();
            visitCallableDeclaration(function);
            FirBodyRenderer bodyRenderer = FirRenderer.this.getBodyRenderer();
            if (bodyRenderer != null) {
                bodyRenderer.render(function);
            }
            if (function.getBody() == null) {
                FirRenderer.this.getPrinter().newLine();
            }
        }

        public void visitFunctionCall(FirFunctionCall functionCall) {
            functionCall.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, functionCall, null, 2, null);
            }
            visitQualifiedAccessExpressionReceivers(functionCall);
            functionCall.getCalleeReference().accept(this);
            FirRenderer.this.renderTypeArguments(functionCall.getTypeArguments());
            visitCall(functionCall);
        }

        public void visitFunctionTypeConversionExpression(FirFunctionTypeConversionExpression functionTypeConversionExpression) {
            String str;
            functionTypeConversionExpression.getClass();
            FirExpression expression = functionTypeConversionExpression.getExpression();
            FirFunctionConversionKind kind = functionTypeConversionExpression.getKind();
            if (Intrinsics.areEqual(kind, FirFunctionConversionKind.Sam.INSTANCE)) {
                str = "SAM";
            } else {
                if (!(kind instanceof FirFunctionConversionKind.BetweenFunctionTypes)) {
                    bu8.a();
                    return;
                }
                str = "FConversion";
            }
            if (expression instanceof FirAnonymousFunctionExpression) {
                FirAnonymousFunctionExpression firAnonymousFunctionExpression = (FirAnonymousFunctionExpression) expression;
                if (firAnonymousFunctionExpression.getIsTrailingLambda()) {
                    FirRenderer.this.print("<L> = " + str + '(');
                    firAnonymousFunctionExpression.getAnonymousFunction().accept(this);
                    FirRenderer.this.print(")");
                    return;
                }
            }
            FirRenderer.this.print(str.concat("("));
            expression.accept(this);
            FirRenderer.this.print(")");
        }

        public void visitFunctionTypeRef(FirFunctionTypeRef functionTypeRef) {
            functionTypeRef.getClass();
            if (!functionTypeRef.getContextParameterTypeRefs().isEmpty()) {
                FirRenderer.this.print("context(");
                FirRenderer.this.renderSeparated(functionTypeRef.getContextParameterTypeRefs(), FirRenderer.this.getVisitor());
                FirRenderer.this.print(")");
            }
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.renderAnnotations$org_jetbrains_kotlin_tree$default(annotationRenderer, FirTypeUtilsKt.dropExtensionFunctionAnnotation(functionTypeRef.getAnnotations()), null, 2, null);
            }
            FirRenderer.this.print("( ");
            FirModifierRenderer modifierRenderer = FirRenderer.this.getModifierRenderer();
            if (modifierRenderer != null) {
                modifierRenderer.renderModifiers(functionTypeRef);
            }
            FirTypeRef receiverTypeRef = functionTypeRef.getReceiverTypeRef();
            if (receiverTypeRef != null) {
                FirRenderer firRenderer = FirRenderer.this;
                receiverTypeRef.accept(this);
                firRenderer.print(".");
            }
            FirRenderer.this.getPrinter().print("(");
            int i = 0;
            for (FirFunctionTypeParameter firFunctionTypeParameter : functionTypeRef.getParameters()) {
                int i2 = i + 1;
                if (i > 0) {
                    FirRenderer.this.getPrinter().print(", ");
                }
                Name name = firFunctionTypeParameter.getName();
                if (name != null) {
                    FirRenderer firRenderer2 = FirRenderer.this;
                    FirPrinter printer = firRenderer2.getPrinter();
                    String strAsString = name.asString();
                    strAsString.getClass();
                    printer.print(strAsString);
                    firRenderer2.getPrinter().print(": ");
                }
                firFunctionTypeParameter.getReturnTypeRef().accept(FirRenderer.this.getVisitor());
                i = i2;
            }
            FirRenderer.this.getPrinter().print(")");
            FirRenderer.this.print(" -> ");
            functionTypeRef.getReturnTypeRef().accept(this);
            FirRenderer.this.print(" )");
            visitUnresolvedTypeRef(functionTypeRef);
        }

        public void visitGetClassCall(FirGetClassCall getClassCall) {
            getClassCall.getClass();
            FirRenderer.this.getGetClassCallRenderer().render$org_jetbrains_kotlin_tree(getClassCall);
        }

        public void visitImplicitInvokeCall(FirImplicitInvokeCall implicitInvokeCall) {
            implicitInvokeCall.getClass();
            visitFunctionCall(implicitInvokeCall);
        }

        public void visitImplicitTypeRef(FirImplicitTypeRef implicitTypeRef) {
            implicitTypeRef.getClass();
            FirRenderer.this.print("<implicit>");
        }

        public void visitImport(FirImport firImport) {
            firImport.getClass();
            visitElement(firImport);
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        public void visitInaccessibleReceiverExpression(FirInaccessibleReceiverExpression inaccessibleReceiverExpression) throws UninitializedPropertyAccessException {
            inaccessibleReceiverExpression.getClass();
            FirRenderer.this.renderType(inaccessibleReceiverExpression.getConeTypeOrNull());
            visitElement(inaccessibleReceiverExpression);
        }

        public void visitIncrementDecrementExpression(FirIncrementDecrementExpression incrementDecrementExpression) {
            incrementDecrementExpression.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, incrementDecrementExpression, null, 2, null);
            }
            String str = Intrinsics.areEqual(incrementDecrementExpression.getOperationName(), OperatorNameConventions.INC) ? "++" : "--";
            if (incrementDecrementExpression.getIsPrefix()) {
                FirRenderer.this.print(str);
            }
            incrementDecrementExpression.getExpression().accept(FirRenderer.this.getVisitor());
            if (incrementDecrementExpression.getIsPrefix()) {
                return;
            }
            FirRenderer.this.print(str);
        }

        public void visitIndexedAccessAugmentedAssignment(FirIndexedAccessAugmentedAssignment indexedAccessAugmentedAssignment) {
            indexedAccessAugmentedAssignment.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, indexedAccessAugmentedAssignment, null, 2, null);
            }
            FirRenderer.this.print("ArraySet:[");
            indexedAccessAugmentedAssignment.getLhsGetCall().accept(this);
            FirRenderer.this.print(Argument.Delimiters.space);
            FirRenderer.this.print(indexedAccessAugmentedAssignment.getOperation().getOperator());
            FirRenderer.this.print(Argument.Delimiters.space);
            indexedAccessAugmentedAssignment.getRhs().accept(this);
            FirRenderer.this.print("]");
        }

        public void visitIntegerLiteralOperatorCall(FirIntegerLiteralOperatorCall integerLiteralOperatorCall) {
            integerLiteralOperatorCall.getClass();
            visitFunctionCall(integerLiteralOperatorCall);
        }

        public void visitLazyExpression(FirLazyExpression lazyExpression) {
            lazyExpression.getClass();
            FirRenderer.this.print("LAZY_EXPRESSION");
        }

        public void visitLiteralExpression(FirLiteralExpression literalExpression) {
            literalExpression.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, literalExpression, null, 2, null);
            }
            ConstantValueKind kind = literalExpression.getKind();
            Object value = literalExpression.getValue();
            FirRenderer firRenderer = FirRenderer.this;
            StringBuilder sb = new StringBuilder();
            sb.append(kind);
            sb.append('(');
            firRenderer.print(sb.toString());
            if (value instanceof Character) {
                Character ch = (Character) value;
                char cCharValue = ch.charValue();
                if (' ' > cCharValue || cCharValue >= 128) {
                    FirRenderer.this.print(Integer.valueOf(ch.charValue()));
                } else {
                    FirRenderer.this.print(value);
                }
            } else {
                FirRenderer.this.print(String.valueOf(value));
            }
            FirRenderer.this.print(")");
        }

        public void visitLoopJump(FirLoopJump loopJump) {
            loopJump.getClass();
            if (this.loopJumpStack.contains(loopJump)) {
                return;
            }
            this.loopJumpStack.push(loopJump);
            FirLoop firLoop = (FirLoop) loopJump.getTarget().getLabeledElement();
            FirRenderer.this.print("@@@[");
            firLoop.getCondition().accept(this);
            FirRenderer.this.print("] ");
            this.loopJumpStack.pop();
        }

        public void visitMemberDeclaration(FirMemberDeclaration memberDeclaration) {
            memberDeclaration.getClass();
            FirModifierRenderer modifierRenderer = FirRenderer.this.getModifierRenderer();
            if (modifierRenderer != null) {
                modifierRenderer.renderModifiers(memberDeclaration);
            }
            FirDeclarationRenderer declarationRenderer = FirRenderer.this.getDeclarationRenderer();
            if (declarationRenderer != null) {
                declarationRenderer.render(memberDeclaration);
            }
            if (!(memberDeclaration instanceof FirClassLikeDeclaration)) {
                if (!(memberDeclaration instanceof FirCallableDeclaration)) {
                    bu8.a();
                    return;
                }
                FirCallableDeclaration firCallableDeclaration = (FirCallableDeclaration) memberDeclaration;
                if (firCallableDeclaration.getTypeParameters().isEmpty()) {
                    return;
                }
                FirRenderer.this.print(Argument.Delimiters.space);
                FirRenderer.this.renderTypeParameters(firCallableDeclaration.getTypeParameters());
                return;
            }
            if (memberDeclaration instanceof FirRegularClass) {
                FirRenderer.this.print(Argument.Delimiters.space + ((FirRegularClass) memberDeclaration).getName());
            }
            if (memberDeclaration instanceof FirTypeAlias) {
                FirRenderer.this.print(Argument.Delimiters.space + ((FirTypeAlias) memberDeclaration).getName());
            }
            FirRenderer.this.renderTypeParameters(((FirClassLikeDeclaration) memberDeclaration).getTypeParameters());
        }

        public void visitMultiDelegatedConstructorCall(FirMultiDelegatedConstructorCall multiDelegatedConstructorCall) {
            multiDelegatedConstructorCall.getClass();
            boolean z = true;
            for (FirDelegatedConstructorCall firDelegatedConstructorCall : multiDelegatedConstructorCall.getDelegatedConstructorCalls()) {
                if (z) {
                    z = false;
                } else {
                    FirRenderer.this.getPrinter().println(new Object[0]);
                }
                visitDelegatedConstructorCall(firDelegatedConstructorCall);
            }
        }

        public void visitNamedArgumentExpression(FirNamedArgumentExpression namedArgumentExpression) {
            namedArgumentExpression.getClass();
            FirRenderer.this.print(namedArgumentExpression.getName());
            FirRenderer.this.print(" = ");
            if (namedArgumentExpression.getIsSpread()) {
                FirRenderer.this.print("*");
            }
            namedArgumentExpression.getExpression().accept(this);
        }

        public void visitNamedFunction(FirNamedFunction namedFunction) {
            namedFunction.getClass();
            visitFunction(namedFunction);
        }

        public void visitNamedReference(FirNamedReference namedReference) {
            namedReference.getClass();
            FirRenderer firRenderer = FirRenderer.this;
            StringBuilder sb = new StringBuilder();
            sb.append(namedReference.getName());
            sb.append('#');
            firRenderer.print(sb.toString());
        }

        public void visitNamedReferenceWithCandidateBase(FirNamedReferenceWithCandidateBase namedReferenceWithCandidateBase) {
            namedReferenceWithCandidateBase.getClass();
            FirRenderer.this.print("R?C|");
            FirRenderer.this.getReferencedSymbolRenderer().printReference(namedReferenceWithCandidateBase.getCandidateSymbol());
            FirRenderer.this.print("|");
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void visitOuterClassTypeParameterRef(FirOuterClassTypeParameterRef outerClassTypeParameterRef) {
            outerClassTypeParameterRef.getClass();
            renderTypeParameter((FirTypeParameter) outerClassTypeParameterRef.getSymbol().getFir(), true);
        }

        public void visitPackageDirective(FirPackageDirective packageDirective) {
            packageDirective.getClass();
            FirPackageDirectiveRenderer packageDirectiveRenderer = FirRenderer.this.getPackageDirectiveRenderer();
            if (packageDirectiveRenderer != null) {
                packageDirectiveRenderer.render(packageDirective);
            }
        }

        public void visitProperty(FirProperty property) {
            FirPropertyAccessorRenderer propertyAccessorRenderer;
            property.getClass();
            visitVariable(property);
            if ((!(property.getSymbol() instanceof FirLocalPropertySymbol) || DeclarationAttributesKt.isDelegatedProperty(property)) && (propertyAccessorRenderer = FirRenderer.this.getPropertyAccessorRenderer()) != null) {
                propertyAccessorRenderer.render(property);
            }
        }

        public void visitPropertyAccessExpression(FirPropertyAccessExpression propertyAccessExpression) {
            propertyAccessExpression.getClass();
            visitQualifiedAccessExpression(propertyAccessExpression);
        }

        public void visitPropertyAccessor(FirPropertyAccessor propertyAccessor) {
            propertyAccessor.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, propertyAccessor, null, 2, null);
            }
            FirModifierRenderer modifierRenderer = FirRenderer.this.getModifierRenderer();
            if (modifierRenderer != null) {
                modifierRenderer.renderModifiers(propertyAccessor);
            }
            FirDeclarationRenderer declarationRenderer = FirRenderer.this.getDeclarationRenderer();
            if (declarationRenderer != null) {
                declarationRenderer.render(propertyAccessor);
            }
            FirCallableSignatureRenderer callableSignatureRenderer = FirRenderer.this.getCallableSignatureRenderer();
            if (callableSignatureRenderer != null) {
                callableSignatureRenderer.renderParameters(propertyAccessor.getValueParameters());
            }
            FirRenderer.this.print(": ");
            propertyAccessor.getReturnTypeRef().accept(this);
            ConeContractRenderer contractRenderer = FirRenderer.this.getContractRenderer();
            if (contractRenderer != null) {
                contractRenderer.render(propertyAccessor);
            }
            FirBodyRenderer bodyRenderer = FirRenderer.this.getBodyRenderer();
            if (bodyRenderer != null) {
                bodyRenderer.render(propertyAccessor);
            }
        }

        public void visitPropertyWithExplicitBackingFieldResolvedNamedReference(FirPropertyWithExplicitBackingFieldResolvedNamedReference propertyWithExplicitBackingFieldResolvedNamedReference) {
            propertyWithExplicitBackingFieldResolvedNamedReference.getClass();
            visitResolvedNamedReference(propertyWithExplicitBackingFieldResolvedNamedReference);
        }

        public void visitQualifiedAccessExpression(FirQualifiedAccessExpression qualifiedAccessExpression) {
            qualifiedAccessExpression.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, qualifiedAccessExpression, null, 2, null);
            }
            visitQualifiedAccessExpressionReceivers(qualifiedAccessExpression);
            qualifiedAccessExpression.getCalleeReference().accept(this);
            FirRenderer.this.renderTypeArguments(qualifiedAccessExpression.getTypeArguments());
        }

        public void visitQualifiedErrorAccessExpression(FirQualifiedErrorAccessExpression qualifiedErrorAccessExpression) {
            qualifiedErrorAccessExpression.getClass();
            FirErrorExpressionRenderer errorExpressionRenderer = FirRenderer.this.getErrorExpressionRenderer();
            if (errorExpressionRenderer != null) {
                errorExpressionRenderer.renderDiagnostic(qualifiedErrorAccessExpression.getDiagnostic());
            }
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, qualifiedErrorAccessExpression, null, 2, null);
            }
            qualifiedErrorAccessExpression.getReceiver().accept(this);
            FirRenderer.this.print('.');
            qualifiedErrorAccessExpression.getSelector().accept(this);
        }

        public void visitReceiverParameter(FirReceiverParameter receiverParameter) {
            receiverParameter.getClass();
            renderReceiverParameter(receiverParameter, true);
        }

        public void visitRegularClass(FirRegularClass regularClass) {
            regularClass.getClass();
            FirRenderer.renderContexts$default(FirRenderer.this, regularClass.getContextParameters(), false, 2, null);
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, regularClass, null, 2, null);
            }
            visitMemberDeclaration(regularClass);
            FirSupertypeRenderer supertypeRenderer = FirRenderer.this.getSupertypeRenderer();
            if (supertypeRenderer != null) {
                supertypeRenderer.renderSupertypes(regularClass);
            }
            FirClassMemberRenderer classMemberRenderer = FirRenderer.this.getClassMemberRenderer();
            if (classMemberRenderer != null) {
                classMemberRenderer.render(regularClass);
            }
        }

        public void visitReplDeclarationReference(FirReplDeclarationReference replDeclarationReference) {
            replDeclarationReference.getClass();
            FirRenderer.this.print("<repl declaration reference>: ");
            FirRenderer.this.getReferencedSymbolRenderer().printReference(replDeclarationReference.getSymbol());
        }

        public void visitReplExpressionReference(FirReplExpressionReference replExpressionReference) {
            replExpressionReference.getClass();
            FirRenderer.this.print("REPL_EXPRESSION_REF");
        }

        public void visitReplPropertyDelegate(FirReplPropertyDelegate replPropertyDelegate) {
            replPropertyDelegate.getClass();
            FirRenderer.this.print("<repl property delegate: ");
            FirRenderer.this.getReferencedSymbolRenderer().printReference(replPropertyDelegate.getPropertySymbol());
            FirRenderer.this.print("> = ");
            replPropertyDelegate.getDelegate().accept(this);
        }

        public void visitReplPropertyInitializer(FirReplPropertyInitializer replPropertyInitializer) {
            replPropertyInitializer.getClass();
            FirRenderer.this.print("<repl property initializer: ");
            FirRenderer.this.getReferencedSymbolRenderer().printReference(replPropertyInitializer.getPropertySymbol());
            FirRenderer.this.print("> = ");
            replPropertyInitializer.getInitializer().accept(this);
        }

        public void visitReplSnippet(FirReplSnippet replSnippet) {
            replSnippet.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, replSnippet, null, 2, null);
            }
            FirRenderer.this.getPrinter().print("REPL_SNIPPET: ");
            FirRenderer.this.renderPhaseAndAttributes(replSnippet);
            FirRenderer.this.getPrinter().println(replSnippet.getName());
            FirRenderer.this.getPrinter().pushIndent$org_jetbrains_kotlin_tree();
            List<FirScriptReceiverParameter> receivers = replSnippet.getReceivers();
            FirRenderer firRenderer = FirRenderer.this;
            Iterator<T> it = receivers.iterator();
            while (it.hasNext()) {
                ((FirScriptReceiverParameter) it.next()).accept(this);
                firRenderer.getPrinter().newLine();
            }
            replSnippet.getSnippetClass().accept(this);
            FirRenderer.this.getPrinter().popIndent$org_jetbrains_kotlin_tree();
        }

        public void visitResolvedCallableReference(FirResolvedCallableReference resolvedCallableReference) {
            resolvedCallableReference.getClass();
            visitResolvedNamedReference(resolvedCallableReference);
        }

        public void visitResolvedErrorReference(FirResolvedErrorReference resolvedErrorReference) {
            resolvedErrorReference.getClass();
            visitResolvedNamedReference(resolvedErrorReference);
        }

        public void visitResolvedNamedReference(FirResolvedNamedReference resolvedNamedReference) {
            resolvedNamedReference.getClass();
            FirRenderer.this.getResolvedNamedReferenceRenderer().render$org_jetbrains_kotlin_tree(resolvedNamedReference);
        }

        public void visitResolvedQualifier(FirResolvedQualifier resolvedQualifier) {
            resolvedQualifier.getClass();
            FirRenderer.this.getResolvedQualifierRenderer().render$org_jetbrains_kotlin_tree(resolvedQualifier);
            FirRenderer.this.renderTypeArguments(resolvedQualifier.getTypeArguments());
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        public void visitResolvedReifiedParameterReference(FirResolvedReifiedParameterReference resolvedReifiedParameterReference) throws UninitializedPropertyAccessException {
            resolvedReifiedParameterReference.getClass();
            FirRenderer.this.renderType(resolvedReifiedParameterReference.getConeTypeOrNull());
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        public void visitResolvedTypeRef(FirResolvedTypeRef resolvedTypeRef) throws UninitializedPropertyAccessException {
            resolvedTypeRef.getClass();
            ConeTypeRenderer.renderAsPossibleFunctionType$default(FirRenderer.this.getTypeRenderer(), resolvedTypeRef.getConeType(), new Function1() { // from class: kc5
                public final Object invoke(Object obj) {
                    return FirRenderer.Visitor.b((ConeKotlinType) obj);
                }
            }, null, 4, null);
        }

        public void visitReturnExpression(FirReturnExpression returnExpression) {
            returnExpression.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, returnExpression, null, 2, null);
            }
            FirRenderer.this.print("^");
            FirTarget<FirFunction> target = returnExpression.getTarget();
            FirFunction firFunction = (FirFunction) target.getLabeledElement();
            if (firFunction instanceof FirNamedFunction) {
                FirRenderer.this.print(String.valueOf(((FirNamedFunction) firFunction).getName()));
            } else {
                String labelName = target.getLabelName();
                if (labelName != null) {
                    FirRenderer.this.print(PreprocessCommandLineArgumentsKt.ARGFILE_ARGUMENT.concat(labelName));
                }
            }
            FirRenderer.this.print(Argument.Delimiters.space);
            returnExpression.getResult().accept(this);
        }

        public void visitSafeCallExpression(FirSafeCallExpression safeCallExpression) {
            safeCallExpression.getClass();
            safeCallExpression.getReceiver().accept(this);
            FirRenderer.this.print("?.{ ");
            safeCallExpression.getSelector().accept(this);
            FirRenderer.this.print(" }");
        }

        public void visitScript(FirScript script) {
            script.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, script, null, 2, null);
            }
            FirRenderer.this.getPrinter().print("SCRIPT: ");
            FirRenderer.this.renderPhaseAndAttributes(script);
            FirRenderer.this.getPrinter().println(script.getName());
            FirRenderer.this.getPrinter().pushIndent$org_jetbrains_kotlin_tree();
            List<FirScriptReceiverParameter> receivers = script.getReceivers();
            FirRenderer firRenderer = FirRenderer.this;
            Iterator<T> it = receivers.iterator();
            while (it.hasNext()) {
                ((FirScriptReceiverParameter) it.next()).accept(this);
                firRenderer.getPrinter().newLine();
            }
            List<FirProperty> parameters = script.getParameters();
            FirRenderer firRenderer2 = FirRenderer.this;
            Iterator<T> it2 = parameters.iterator();
            while (it2.hasNext()) {
                ((FirProperty) it2.next()).accept(this);
                firRenderer2.getPrinter().newLine();
            }
            FirRenderer.this.getPrinter().newLine();
            List<FirDeclaration> declarations = script.getDeclarations();
            FirRenderer firRenderer3 = FirRenderer.this;
            Iterator<T> it3 = declarations.iterator();
            while (it3.hasNext()) {
                ((FirDeclaration) it3.next()).accept(this);
                firRenderer3.getPrinter().newLine();
            }
            FirRenderer.this.getPrinter().popIndent$org_jetbrains_kotlin_tree();
        }

        public void visitScriptReceiverParameter(FirScriptReceiverParameter scriptReceiverParameter) {
            scriptReceiverParameter.getClass();
            FirRenderer.this.renderPhaseAndAttributes(scriptReceiverParameter);
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, scriptReceiverParameter, null, 2, null);
            }
            FirRenderer.this.print("<script receiver parameter>: ");
            scriptReceiverParameter.getTypeRef().accept(this);
        }

        public void visitSmartCastExpression(FirSmartCastExpression smartCastExpression) {
            smartCastExpression.getClass();
            smartCastExpression.getOriginalExpression().accept(this);
        }

        public void visitSpreadArgumentExpression(FirSpreadArgumentExpression spreadArgumentExpression) {
            spreadArgumentExpression.getClass();
            if (spreadArgumentExpression.getIsSpread()) {
                FirRenderer.this.print("*");
            }
            spreadArgumentExpression.getExpression().accept(this);
        }

        public void visitStarProjection(FirStarProjection starProjection) {
            starProjection.getClass();
            FirRenderer.this.print("*");
        }

        public void visitStatement(FirStatement statement) {
            statement.getClass();
            visitElement(statement);
        }

        public void visitStringConcatenationCall(FirStringConcatenationCall stringConcatenationCall) {
            stringConcatenationCall.getClass();
            FirRenderer.this.print("<strcat>");
            visitCall(stringConcatenationCall);
        }

        public void visitSuperReceiverExpression(FirSuperReceiverExpression superReceiverExpression) {
            superReceiverExpression.getClass();
            visitQualifiedAccessExpression(superReceiverExpression);
        }

        public void visitSuperReference(FirSuperReference superReference) {
            superReference.getClass();
            FirRenderer.this.print("super<");
            superReference.getSuperTypeRef().accept(this);
            FirRenderer.this.print(">");
            String labelName = superReference.getLabelName();
            if (labelName != null) {
                FirRenderer.this.print(PreprocessCommandLineArgumentsKt.ARGFILE_ARGUMENT + labelName + '#');
            }
        }

        public void visitThisReceiverExpression(FirThisReceiverExpression thisReceiverExpression) {
            thisReceiverExpression.getClass();
            visitQualifiedAccessExpression(thisReceiverExpression);
        }

        public void visitThisReference(FirThisReference thisReference) {
            thisReference.getClass();
            FirRenderer.this.print("this");
            String labelName = thisReference.getLabelName();
            FirThisOwnerSymbol<?> boundSymbol = thisReference.getBoundSymbol();
            if (boundSymbol != null) {
                FirRenderer.this.print("@R|");
                FirRenderer.this.getReferencedSymbolRenderer().printReference(boundSymbol);
                FirRenderer.this.print("|");
                return;
            }
            FirRenderer firRenderer = FirRenderer.this;
            if (labelName == null) {
                firRenderer.print("#");
                return;
            }
            firRenderer.print(PreprocessCommandLineArgumentsKt.ARGFILE_ARGUMENT + labelName + '#');
        }

        public void visitThrowExpression(FirThrowExpression throwExpression) {
            throwExpression.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, throwExpression, null, 2, null);
            }
            FirRenderer.this.print("throw ");
            throwExpression.getException().accept(this);
        }

        public void visitTryExpression(FirTryExpression tryExpression) {
            tryExpression.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, tryExpression, null, 2, null);
            }
            FirRenderer.this.print("try");
            tryExpression.getTryBlock().accept(this);
            for (FirCatch firCatch : tryExpression.getCatches()) {
                FirRenderer.this.print("catch (");
                firCatch.getParameter().accept(this);
                FirRenderer.this.print(")");
                firCatch.getBlock().accept(this);
            }
            FirBlock finallyBlock = tryExpression.getFinallyBlock();
            if (finallyBlock == null) {
                return;
            }
            FirRenderer.this.print("finally");
            finallyBlock.accept(this);
        }

        public void visitTypeAlias(FirTypeAlias typeAlias) {
            typeAlias.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, typeAlias, null, 2, null);
            }
            visitMemberDeclaration(typeAlias);
            FirSupertypeRenderer supertypeRenderer = FirRenderer.this.getSupertypeRenderer();
            if (supertypeRenderer != null) {
                supertypeRenderer.renderTypeAliasExpansion(typeAlias);
            }
            FirRenderer.this.getPrinter().newLine();
        }

        public void visitTypeOperatorCall(FirTypeOperatorCall typeOperatorCall) {
            typeOperatorCall.getClass();
            FirRenderer.this.print("(");
            ((FirExpression) CollectionsKt.first(typeOperatorCall.getArgumentList().getArguments())).accept(this);
            FirRenderer.this.print(Argument.Delimiters.space);
            FirRenderer.this.print(typeOperatorCall.getOperation().getOperator());
            FirRenderer.this.print(Argument.Delimiters.space);
            typeOperatorCall.getConversionTypeRef().accept(this);
            FirRenderer.this.print(")");
        }

        public void visitTypeParameter(FirTypeParameter typeParameter) {
            typeParameter.getClass();
            renderTypeParameter$default(this, typeParameter, false, 2, null);
        }

        /* JADX WARN: Multi-variable type inference failed */
        public void visitTypeParameterRef(FirTypeParameterRef typeParameterRef) {
            typeParameterRef.getClass();
            ((FirTypeParameter) typeParameterRef.getSymbol().getFir()).accept(this);
        }

        public void visitTypeProjection(FirTypeProjection typeProjection) {
            typeProjection.getClass();
            visitElement(typeProjection);
        }

        public void visitTypeProjectionWithVariance(FirTypeProjectionWithVariance typeProjectionWithVariance) {
            typeProjectionWithVariance.getClass();
            FirRenderer.this.renderVariance(typeProjectionWithVariance.getVariance());
            typeProjectionWithVariance.getTypeRef().accept(this);
        }

        public void visitTypeRef(FirTypeRef typeRef) {
            typeRef.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, typeRef, null, 2, null);
            }
            if (typeRef.getCustomRenderer()) {
                FirRenderer.this.print(typeRef.toString());
            } else {
                visitElement(typeRef);
            }
        }

        public void visitUnresolvedTypeRef(FirUnresolvedTypeRef unresolvedTypeRef) {
            unresolvedTypeRef.getClass();
            if (unresolvedTypeRef.isMarkedNullable()) {
                FirRenderer.this.print("?");
            }
        }

        public void visitUserTypeRef(FirUserTypeRef userTypeRef) {
            userTypeRef.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, userTypeRef, null, 2, null);
            }
            int i = 0;
            for (FirQualifierPart firQualifierPart : userTypeRef.getQualifier()) {
                int i2 = i + 1;
                if (i != 0) {
                    FirRenderer.this.print(".");
                }
                FirRenderer.this.print(firQualifierPart.getName());
                if (!firQualifierPart.getTypeArgumentList().getTypeArguments().isEmpty()) {
                    FirRenderer.this.print("<");
                    FirRenderer.this.renderSeparated(firQualifierPart.getTypeArgumentList().getTypeArguments(), FirRenderer.this.getVisitor());
                    FirRenderer.this.print(">");
                }
                i = i2;
            }
            visitUnresolvedTypeRef(userTypeRef);
        }

        public void visitValueParameter(FirValueParameter valueParameter) {
            valueParameter.getClass();
            FirCallableSignatureRenderer callableSignatureRenderer = FirRenderer.this.getCallableSignatureRenderer();
            if (callableSignatureRenderer != null) {
                callableSignatureRenderer.renderParameter(valueParameter);
            }
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
        public void visitVarargArgumentsExpression(FirVarargArgumentsExpression varargArgumentsExpression) throws UninitializedPropertyAccessException {
            varargArgumentsExpression.getClass();
            FirRenderer.this.print("vararg(");
            FirRenderer.this.renderSeparated(varargArgumentsExpression.getArguments(), FirRenderer.this.getVisitor());
            if (FirRenderer.this.renderVarargTypes) {
                if (!varargArgumentsExpression.getArguments().isEmpty()) {
                    FirRenderer.this.print("; ");
                }
                FirRenderer.this.print("type = ");
                ConeTypeRenderer.render$default(FirRenderer.this.getTypeRenderer(), FirTypeUtilsKt.getResolvedType(varargArgumentsExpression), null, 2, null);
                FirRenderer.this.print(", elementType = ");
                ConeTypeRenderer typeRenderer = FirRenderer.this.getTypeRenderer();
                ConeKotlinType coneElementTypeOrNull = varargArgumentsExpression.getConeElementTypeOrNull();
                coneElementTypeOrNull.getClass();
                ConeTypeRenderer.render$default(typeRenderer, coneElementTypeOrNull, null, 2, null);
            }
            FirRenderer.this.print(")");
        }

        public void visitVariable(FirVariable variable) {
            variable.getClass();
            visitCallableDeclaration(variable);
            FirBodyRenderer bodyRenderer = FirRenderer.this.getBodyRenderer();
            if (bodyRenderer != null) {
                bodyRenderer.render(variable);
            }
        }

        public void visitVariableAssignment(FirVariableAssignment variableAssignment) {
            variableAssignment.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, variableAssignment, null, 2, null);
            }
            variableAssignment.getLValue().accept(this);
            FirRenderer.this.print(Argument.Delimiters.space);
            FirRenderer.this.print(FirOperation.ASSIGN.getOperator());
            FirRenderer.this.print(Argument.Delimiters.space);
            variableAssignment.getRValue().accept(FirRenderer.this.getVisitor());
        }

        public void visitWhenBranch(FirWhenBranch whenBranch) {
            whenBranch.getClass();
            FirExpression condition = whenBranch.getCondition();
            if (condition instanceof FirElseIfTrueCondition) {
                FirRenderer.this.print("else");
            } else {
                condition.accept(this);
            }
            FirRenderer.this.print(" -> ");
            whenBranch.getResult().accept(this);
        }

        public void visitWhenExpression(FirWhenExpression whenExpression) {
            FirExpression initializer;
            whenExpression.getClass();
            FirAnnotationRenderer annotationRenderer = FirRenderer.this.getAnnotationRenderer();
            if (annotationRenderer != null) {
                FirAnnotationRenderer.render$default(annotationRenderer, whenExpression, null, 2, null);
            }
            FirRenderer.this.print("when (");
            FirVariable subjectVariable = whenExpression.getSubjectVariable();
            if (subjectVariable != null) {
                subjectVariable.accept(this);
            } else {
                FirVariable subjectVariable2 = whenExpression.getSubjectVariable();
                if (subjectVariable2 != null && (initializer = subjectVariable2.getInitializer()) != null) {
                    initializer.accept(this);
                }
            }
            FirRenderer.this.getPrinter().println(") {");
            FirRenderer.this.getPrinter().pushIndent$org_jetbrains_kotlin_tree();
            Iterator<FirWhenBranch> it = whenExpression.getBranches().iterator();
            while (it.hasNext()) {
                it.next().accept(this);
            }
            FirRenderer.this.getPrinter().popIndent$org_jetbrains_kotlin_tree();
            FirRenderer.this.getPrinter().println("}");
        }

        public void visitWhenSubjectExpression(FirWhenSubjectExpression whenSubjectExpression) {
            whenSubjectExpression.getClass();
            FirRenderer.this.print("$subj$");
        }

        public void visitWhileLoop(FirWhileLoop whileLoop) {
            whileLoop.getClass();
            FirLabel label = whileLoop.getLabel();
            if (label != null) {
                FirRenderer.this.print(label.getName() + '@');
            }
            FirRenderer.this.print("while(");
            whileLoop.getCondition().accept(this);
            FirRenderer.this.print(")");
            whileLoop.getBlock().accept(this);
        }

        public void visitWrappedDelegateExpression(FirWrappedDelegateExpression wrappedDelegateExpression) {
            wrappedDelegateExpression.getClass();
            wrappedDelegateExpression.getExpression().accept(this);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ FirRenderer(StringBuilder sb, FirAnnotationRenderer firAnnotationRenderer, FirBodyRenderer firBodyRenderer, FirCallArgumentsRenderer firCallArgumentsRenderer, FirContextArgumentRenderer firContextArgumentRenderer, FirClassMemberRenderer firClassMemberRenderer, ConeContractRenderer coneContractRenderer, FirDeclarationRenderer firDeclarationRenderer, ConeIdRenderer coneIdRenderer, FirModifierRenderer firModifierRenderer, FirPackageDirectiveRenderer firPackageDirectiveRenderer, FirPropertyAccessorRenderer firPropertyAccessorRenderer, FirResolvePhaseRenderer firResolvePhaseRenderer, ConeTypeRenderer coneTypeRenderer, FirSymbolRenderer firSymbolRenderer, FirCallableSignatureRenderer firCallableSignatureRenderer, FirErrorExpressionRenderer firErrorExpressionRenderer, FirResolvedNamedReferenceRenderer firResolvedNamedReferenceRenderer, FirResolvedQualifierRenderer firResolvedQualifierRenderer, FirGetClassCallRenderer firGetClassCallRenderer, FirSupertypeRenderer firSupertypeRenderer, boolean z, boolean z2, boolean z3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new StringBuilder() : sb, (i & 2) != 0 ? new FirAnnotationRenderer() : firAnnotationRenderer, (i & 4) != 0 ? new FirBodyRenderer() : firBodyRenderer, (i & 8) != 0 ? new FirCallArgumentsRenderer() : firCallArgumentsRenderer, (i & 16) != 0 ? new FirContextArgumentRenderer() : firContextArgumentRenderer, (i & 32) != 0 ? new FirClassMemberRenderer() : firClassMemberRenderer, (i & 64) != 0 ? new ConeContractRenderer() : coneContractRenderer, (i & 128) != 0 ? new FirDeclarationRenderer(0 == true ? 1 : 0, false, 3, 0 == true ? 1 : 0) : firDeclarationRenderer, (i & 256) != 0 ? new ConeIdRendererForDebugging() : coneIdRenderer, (i & 512) != 0 ? new FirAllModifierRenderer(FirModifierRenderer.StaticPolicy.Default.INSTANCE) : firModifierRenderer, (i & 1024) != 0 ? null : firPackageDirectiveRenderer, (i & 2048) != 0 ? new FirPropertyAccessorRenderer() : firPropertyAccessorRenderer, (i & 4096) == 0 ? firResolvePhaseRenderer : null, (i & 8192) != 0 ? new ConeTypeRendererForDebugging() : coneTypeRenderer, (i & 16384) != 0 ? new FirSymbolRenderer() : firSymbolRenderer, (i & 32768) != 0 ? new FirCallableSignatureRenderer() : firCallableSignatureRenderer, (i & 65536) != 0 ? new FirErrorExpressionOnlyErrorRenderer() : firErrorExpressionRenderer, (i & 131072) != 0 ? new FirResolvedNamedReferenceRendererWithLabel() : firResolvedNamedReferenceRenderer, (i & 262144) != 0 ? new FirResolvedQualifierRendererWithLabel() : firResolvedQualifierRenderer, (i & 524288) != 0 ? new FirGetClassCallRendererForDebugging() : firGetClassCallRenderer, (i & 1048576) != 0 ? new FirSupertypeRenderer() : firSupertypeRenderer, (i & 2097152) != 0 ? true : z, (i & 4194304) == 0 ? z2 : true, (i & 8388608) != 0 ? false : z3);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void print(Object s) {
        getPrinter().print(s);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderContexts(List<? extends FirValueParameter> contextParameters, boolean lineBreakAfter) {
        if (contextParameters.isEmpty()) {
            return;
        }
        print("context(");
        renderSeparated(contextParameters, getVisitor());
        print(")");
        if (lineBreakAfter) {
            getPrinter().newLine();
        } else {
            print(Argument.Delimiters.space);
        }
    }

    public static /* synthetic */ void renderContexts$default(FirRenderer firRenderer, List list, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = firRenderer.lineBreakAfterContextParameters;
        }
        firRenderer.renderContexts(list, z);
    }

    public static /* synthetic */ String renderElementAsString$default(FirRenderer firRenderer, FirElement firElement, boolean z, int i, Object obj) {
        if ((i & 2) != 0) {
            z = false;
        }
        return firRenderer.renderElementAsString(firElement, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderPhaseAndAttributes(FirDeclaration declaration) {
        FirDeclarationRenderer declarationRenderer = getDeclarationRenderer();
        if (declarationRenderer != null) {
            declarationRenderer.renderPhaseAndAttributes$org_jetbrains_kotlin_tree(declaration);
            return;
        }
        FirResolvePhaseRenderer resolvePhaseRenderer = getResolvePhaseRenderer();
        if (resolvePhaseRenderer != null) {
            resolvePhaseRenderer.render(declaration);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderSeparated(List<? extends FirElement> elements, Visitor visitor) {
        getPrinter().renderSeparated$org_jetbrains_kotlin_tree(elements, visitor);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.UninitializedPropertyAccessException */
    public final void renderType(ConeKotlinType type) throws UninitializedPropertyAccessException {
        if (type == null) {
            return;
        }
        print("R|");
        ConeTypeRenderer.render$default(getTypeRenderer(), type, null, 2, null);
        print("|");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderTypeArguments(List<? extends FirTypeProjection> list) {
        if (list.isEmpty()) {
            return;
        }
        print("<");
        renderSeparated(list, getVisitor());
        print(">");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderTypeParameters(List<? extends FirTypeParameterRef> list) {
        if (list.isEmpty()) {
            return;
        }
        print("<");
        renderSeparated(list, getVisitor());
        print(">");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void renderVariance(Variance variance) {
        String label = variance.getLabel();
        print(label);
        if (label.length() > 0) {
            print(Argument.Delimiters.space);
        }
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public FirAnnotationRenderer getAnnotationRenderer() {
        return this.annotationRenderer;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public FirBodyRenderer getBodyRenderer() {
        return this.bodyRenderer;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public FirCallArgumentsRenderer getCallArgumentsRenderer() {
        return this.callArgumentsRenderer;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public FirCallableSignatureRenderer getCallableSignatureRenderer() {
        return this.callableSignatureRenderer;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public FirClassMemberRenderer getClassMemberRenderer() {
        return this.classMemberRenderer;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public FirContextArgumentRenderer getContextArgumentRenderer() {
        return this.contextArgumentRenderer;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public ConeContractRenderer getContractRenderer() {
        return this.contractRenderer;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public FirDeclarationRenderer getDeclarationRenderer() {
        return this.declarationRenderer;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public FirErrorExpressionRenderer getErrorExpressionRenderer() {
        return this.errorExpressionRenderer;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public FirGetClassCallRenderer getGetClassCallRenderer() {
        return this.getClassCallRenderer;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public ConeIdRenderer getIdRenderer() {
        return this.idRenderer;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public FirModifierRenderer getModifierRenderer() {
        return this.modifierRenderer;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public FirPackageDirectiveRenderer getPackageDirectiveRenderer() {
        return this.packageDirectiveRenderer;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public FirPrinter getPrinter() {
        return this.printer;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public FirPropertyAccessorRenderer getPropertyAccessorRenderer() {
        return this.propertyAccessorRenderer;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public FirSymbolRenderer getReferencedSymbolRenderer() {
        return this.referencedSymbolRenderer;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public FirResolvePhaseRenderer getResolvePhaseRenderer() {
        return this.resolvePhaseRenderer;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public FirResolvedNamedReferenceRenderer getResolvedNamedReferenceRenderer() {
        return this.resolvedNamedReferenceRenderer;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public FirResolvedQualifierRenderer getResolvedQualifierRenderer() {
        return this.resolvedQualifierRenderer;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public FirSupertypeRenderer getSupertypeRenderer() {
        return this.supertypeRenderer;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public ConeTypeRenderer getTypeRenderer() {
        return this.typeRenderer;
    }

    @Override // org.jetbrains.kotlin.fir.renderer.FirRendererComponents
    public Visitor getVisitor() {
        return this.visitor;
    }

    public final void renderAnnotations(FirAnnotationContainer annotationContainer) {
        annotationContainer.getClass();
        FirAnnotationRenderer annotationRenderer = getAnnotationRenderer();
        if (annotationRenderer != null) {
            FirAnnotationRenderer.render$default(annotationRenderer, annotationContainer, null, 2, null);
        }
    }

    public final String renderAsCallableDeclarationString(FirCallableDeclaration callableDeclaration) {
        callableDeclaration.getClass();
        getVisitor().visitCallableDeclaration(callableDeclaration);
        return getPrinter().toString();
    }

    public final String renderElementAsString(FirElement element, boolean trim) {
        element.getClass();
        element.accept(getVisitor());
        String string = getPrinter().toString();
        return trim ? StringsKt.trim(string).toString() : string;
    }

    public final String renderElementWithTypeAsString(FirElement element) {
        element.getClass();
        print(element);
        print(": ");
        return renderElementAsString$default(this, element, false, 2, null);
    }

    public final void renderMemberDeclarationClass(FirClass firClass) {
        firClass.getClass();
        getVisitor().visitMemberDeclaration(firClass);
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0006\u0010\u0004\u001a\u00020\u0005J\u0006\u0010\u0006\u001a\u00020\u0005J\u0006\u0010\u0007\u001a\u00020\u0005J\u0006\u0010\b\u001a\u00020\u0005¨\u0006\t"}, d2 = {"Lorg/jetbrains/kotlin/fir/renderer/FirRenderer$Companion;", Argument.Delimiters.none, "<init>", "()V", "noAnnotationBodiesAccessorAndArguments", "Lorg/jetbrains/kotlin/fir/renderer/FirRenderer;", "withResolvePhase", "withDeclarationAttributes", "forReadability", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Multi-variable type inference failed */
        public final FirRenderer forReadability() {
            boolean z = false;
            ConeTypeRenderer coneTypeRenderer = new ConeTypeRenderer(null, z, 3, 0 == true ? 1 : 0);
            ConeIdShortRenderer coneIdShortRenderer = new ConeIdShortRenderer();
            FirNoClassMemberRenderer firNoClassMemberRenderer = new FirNoClassMemberRenderer();
            FirCallNoArgumentsRenderer firCallNoArgumentsRenderer = new FirCallNoArgumentsRenderer();
            FirPartialModifierRenderer firPartialModifierRenderer = new FirPartialModifierRenderer(FirModifierRenderer.StaticPolicy.Default.INSTANCE);
            FirCallableSignatureRendererForReadability firCallableSignatureRendererForReadability = new FirCallableSignatureRendererForReadability();
            FirAnnotationRenderer firAnnotationRenderer = null;
            FirBodyRenderer firBodyRenderer = null;
            FirContextArgumentRenderer firContextArgumentRenderer = null;
            ConeContractRenderer coneContractRenderer = null;
            FirPackageDirectiveRenderer firPackageDirectiveRenderer = null;
            FirPropertyAccessorRenderer firPropertyAccessorRenderer = null;
            FirResolvePhaseRenderer firResolvePhaseRenderer = null;
            FirSymbolRenderer firSymbolRenderer = null;
            FirErrorExpressionRenderer firErrorExpressionRenderer = null;
            FirResolvedNamedReferenceRenderer firResolvedNamedReferenceRenderer = null;
            FirResolvedQualifierRenderer firResolvedQualifierRenderer = null;
            FirGetClassCallRenderer firGetClassCallRenderer = null;
            FirSupertypeRenderer firSupertypeRenderer = null;
            boolean z2 = false;
            boolean z3 = false;
            boolean z4 = false;
            return new FirRenderer(0 == true ? 1 : 0, firAnnotationRenderer, firBodyRenderer, firCallNoArgumentsRenderer, firContextArgumentRenderer, firNoClassMemberRenderer, coneContractRenderer, new FirDeclarationRenderer("local ", z, 2, 0 == true ? 1 : 0), coneIdShortRenderer, firPartialModifierRenderer, firPackageDirectiveRenderer, firPropertyAccessorRenderer, firResolvePhaseRenderer, coneTypeRenderer, firSymbolRenderer, firCallableSignatureRendererForReadability, firErrorExpressionRenderer, firResolvedNamedReferenceRenderer, firResolvedQualifierRenderer, firGetClassCallRenderer, firSupertypeRenderer, z2, z3, z4, 16733267, null);
        }

        public final FirRenderer noAnnotationBodiesAccessorAndArguments() {
            return new FirRenderer(null, null, null, new FirCallNoArgumentsRenderer(), null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, false, 14678001, null);
        }

        public final FirRenderer withDeclarationAttributes() {
            return new FirRenderer(null, null, null, null, null, null, null, new FirDeclarationRendererWithAttributes(), null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, false, 16777087, null);
        }

        public final FirRenderer withResolvePhase() {
            return new FirRenderer(null, null, null, null, null, null, null, null, null, null, null, null, new FirResolvePhaseRenderer(), null, null, null, null, null, null, null, null, false, false, false, 16773119, null);
        }

        private Companion() {
        }
    }

    public FirRenderer(StringBuilder sb, FirAnnotationRenderer firAnnotationRenderer, FirBodyRenderer firBodyRenderer, FirCallArgumentsRenderer firCallArgumentsRenderer, FirContextArgumentRenderer firContextArgumentRenderer, FirClassMemberRenderer firClassMemberRenderer, ConeContractRenderer coneContractRenderer, FirDeclarationRenderer firDeclarationRenderer, ConeIdRenderer coneIdRenderer, FirModifierRenderer firModifierRenderer, FirPackageDirectiveRenderer firPackageDirectiveRenderer, FirPropertyAccessorRenderer firPropertyAccessorRenderer, FirResolvePhaseRenderer firResolvePhaseRenderer, ConeTypeRenderer coneTypeRenderer, FirSymbolRenderer firSymbolRenderer, FirCallableSignatureRenderer firCallableSignatureRenderer, FirErrorExpressionRenderer firErrorExpressionRenderer, FirResolvedNamedReferenceRenderer firResolvedNamedReferenceRenderer, FirResolvedQualifierRenderer firResolvedQualifierRenderer, FirGetClassCallRenderer firGetClassCallRenderer, FirSupertypeRenderer firSupertypeRenderer, boolean z, boolean z2, boolean z3) {
        sb.getClass();
        coneIdRenderer.getClass();
        coneTypeRenderer.getClass();
        firSymbolRenderer.getClass();
        firResolvedNamedReferenceRenderer.getClass();
        firResolvedQualifierRenderer.getClass();
        firGetClassCallRenderer.getClass();
        this.annotationRenderer = firAnnotationRenderer;
        this.bodyRenderer = firBodyRenderer;
        this.callArgumentsRenderer = firCallArgumentsRenderer;
        this.contextArgumentRenderer = firContextArgumentRenderer;
        this.classMemberRenderer = firClassMemberRenderer;
        this.contractRenderer = coneContractRenderer;
        this.declarationRenderer = firDeclarationRenderer;
        this.idRenderer = coneIdRenderer;
        this.modifierRenderer = firModifierRenderer;
        this.packageDirectiveRenderer = firPackageDirectiveRenderer;
        this.propertyAccessorRenderer = firPropertyAccessorRenderer;
        this.resolvePhaseRenderer = firResolvePhaseRenderer;
        this.typeRenderer = coneTypeRenderer;
        this.referencedSymbolRenderer = firSymbolRenderer;
        this.callableSignatureRenderer = firCallableSignatureRenderer;
        this.errorExpressionRenderer = firErrorExpressionRenderer;
        this.resolvedNamedReferenceRenderer = firResolvedNamedReferenceRenderer;
        this.resolvedQualifierRenderer = firResolvedQualifierRenderer;
        this.getClassCallRenderer = firGetClassCallRenderer;
        this.supertypeRenderer = firSupertypeRenderer;
        this.lineBreakAfterContextParameters = z;
        this.renderFieldAnnotationSeparately = z2;
        this.renderVarargTypes = z3;
        this.visitor = new Visitor();
        this.printer = new FirPrinter(sb);
        FirAnnotationRenderer annotationRenderer = getAnnotationRenderer();
        if (annotationRenderer != null) {
            annotationRenderer.setComponents$org_jetbrains_kotlin_tree(this);
        }
        FirBodyRenderer bodyRenderer = getBodyRenderer();
        if (bodyRenderer != null) {
            bodyRenderer.setComponents$org_jetbrains_kotlin_tree(this);
        }
        FirCallArgumentsRenderer callArgumentsRenderer = getCallArgumentsRenderer();
        if (callArgumentsRenderer != null) {
            callArgumentsRenderer.setComponents$org_jetbrains_kotlin_tree(this);
        }
        FirContextArgumentRenderer contextArgumentRenderer = getContextArgumentRenderer();
        if (contextArgumentRenderer != null) {
            contextArgumentRenderer.setComponents$org_jetbrains_kotlin_tree(this);
        }
        FirClassMemberRenderer classMemberRenderer = getClassMemberRenderer();
        if (classMemberRenderer != null) {
            classMemberRenderer.setComponents$org_jetbrains_kotlin_tree(this);
        }
        ConeContractRenderer contractRenderer = getContractRenderer();
        if (contractRenderer != null) {
            contractRenderer.setComponents$org_jetbrains_kotlin_tree(this);
        }
        FirDeclarationRenderer declarationRenderer = getDeclarationRenderer();
        if (declarationRenderer != null) {
            declarationRenderer.setComponents$org_jetbrains_kotlin_tree(this);
        }
        getIdRenderer().setBuilder(sb);
        FirModifierRenderer modifierRenderer = getModifierRenderer();
        if (modifierRenderer != null) {
            modifierRenderer.setComponents$org_jetbrains_kotlin_tree(this);
        }
        FirPackageDirectiveRenderer packageDirectiveRenderer = getPackageDirectiveRenderer();
        if (packageDirectiveRenderer != null) {
            packageDirectiveRenderer.setComponents$org_jetbrains_kotlin_tree(this);
        }
        FirPropertyAccessorRenderer propertyAccessorRenderer = getPropertyAccessorRenderer();
        if (propertyAccessorRenderer != null) {
            propertyAccessorRenderer.setComponents$org_jetbrains_kotlin_tree(this);
        }
        FirResolvePhaseRenderer resolvePhaseRenderer = getResolvePhaseRenderer();
        if (resolvePhaseRenderer != null) {
            resolvePhaseRenderer.setComponents$org_jetbrains_kotlin_tree(this);
        }
        getTypeRenderer().setBuilder(sb);
        getTypeRenderer().setIdRenderer(getIdRenderer());
        getReferencedSymbolRenderer().setComponents$org_jetbrains_kotlin_tree(this);
        FirCallableSignatureRenderer callableSignatureRenderer = getCallableSignatureRenderer();
        if (callableSignatureRenderer != null) {
            callableSignatureRenderer.setComponents$org_jetbrains_kotlin_tree(this);
        }
        FirErrorExpressionRenderer errorExpressionRenderer = getErrorExpressionRenderer();
        if (errorExpressionRenderer != null) {
            errorExpressionRenderer.setComponents$org_jetbrains_kotlin_tree(this);
        }
        getResolvedNamedReferenceRenderer().setComponents$org_jetbrains_kotlin_tree(this);
        getResolvedQualifierRenderer().setComponents$org_jetbrains_kotlin_tree(this);
        getGetClassCallRenderer().setComponents$org_jetbrains_kotlin_tree(this);
        FirSupertypeRenderer supertypeRenderer = getSupertypeRenderer();
        if (supertypeRenderer != null) {
            supertypeRenderer.setComponents$org_jetbrains_kotlin_tree(this);
        }
    }

    public FirRenderer() {
        this(null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, null, false, false, false, 16777215, null);
    }
}
