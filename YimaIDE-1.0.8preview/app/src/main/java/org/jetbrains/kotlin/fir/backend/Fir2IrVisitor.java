package org.jetbrains.kotlin.fir.backend;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.tree.TokenSet;
import defpackage.e2f;
import defpackage.f2f;
import defpackage.uw4;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.KotlinNothingValueException;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Reflection;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtNodeTypes;
import org.jetbrains.kotlin.KtRealSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.contracts.description.LogicOperationKind;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.ParameterDescriptor;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirExpressionRef;
import org.jetbrains.kotlin.fir.FirLabel;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrVisitor;
import org.jetbrains.kotlin.fir.backend.generators.AdapterGenerator;
import org.jetbrains.kotlin.fir.backend.generators.AnnotationGenerator;
import org.jetbrains.kotlin.fir.backend.generators.CallAndReferenceGenerator;
import org.jetbrains.kotlin.fir.backend.generators.ClassMemberGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrCallableDeclarationsGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrClassifiersGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrDataClassMembersGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyDeclarationsGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyFakeOverrideGenerator;
import org.jetbrains.kotlin.fir.backend.generators.OperatorExpressionGenerator;
import org.jetbrains.kotlin.fir.backend.utils.ConstantUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.ConversionTypeOrigin;
import org.jetbrains.kotlin.fir.backend.utils.ImplicitConversionUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.InjectedValue;
import org.jetbrains.kotlin.fir.backend.utils.IrElementsCreationUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.OffsetUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.OriginUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.VariousUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DestructuringDeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirScriptReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.deserialization.FirEnumEntryDeserializerAccessUtilKt;
import org.jetbrains.kotlin.fir.diagnostics.FirDiagnosticHolder;
import org.jetbrains.kotlin.fir.expressions.ExhaustivenessStatusKt;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousFunctionExpression;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousObjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirBlock;
import org.jetbrains.kotlin.fir.expressions.FirBooleanOperatorExpression;
import org.jetbrains.kotlin.fir.expressions.FirBreakExpression;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirCatch;
import org.jetbrains.kotlin.fir.expressions.FirCheckNotNullCall;
import org.jetbrains.kotlin.fir.expressions.FirCheckedSafeCallSubject;
import org.jetbrains.kotlin.fir.expressions.FirClassReferenceExpression;
import org.jetbrains.kotlin.fir.expressions.FirCollectionLiteral;
import org.jetbrains.kotlin.fir.expressions.FirComparisonExpression;
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
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionTypeConversionExpression;
import org.jetbrains.kotlin.fir.expressions.FirGetClassCall;
import org.jetbrains.kotlin.fir.expressions.FirInaccessibleReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirIndexedAccessAugmentedAssignment;
import org.jetbrains.kotlin.fir.expressions.FirJump;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirLoop;
import org.jetbrains.kotlin.fir.expressions.FirOperation;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirReplDeclarationReference;
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
import org.jetbrains.kotlin.fir.expressions.FirWrappedArgumentExpression;
import org.jetbrains.kotlin.fir.expressions.impl.FirContractCallBlock;
import org.jetbrains.kotlin.fir.expressions.impl.FirElseIfTrueCondition;
import org.jetbrains.kotlin.fir.expressions.impl.FirEmptyExpressionBlock;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.expressions.impl.FirSingleExpressionBlock;
import org.jetbrains.kotlin.fir.expressions.impl.FirUnitExpression;
import org.jetbrains.kotlin.fir.extensions.FirExtensionServiceKt;
import org.jetbrains.kotlin.fir.extensions.FirScriptResolutionHacksComponent;
import org.jetbrains.kotlin.fir.extensions.FirScriptResolutionHacksComponentKt;
import org.jetbrains.kotlin.fir.references.FirNamedReference;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.FirThisReference;
import org.jetbrains.kotlin.fir.resolve.CallableIdUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirReplSnippetSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirScriptSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirThisOwnerSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeClassLikeType;
import org.jetbrains.kotlin.fir.types.ConeDynamicType;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.IrStatement;
import org.jetbrains.kotlin.ir.builders.Scope;
import org.jetbrains.kotlin.ir.declarations.IrAnonymousInitializer;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrEnumEntry;
import org.jetbrains.kotlin.ir.declarations.IrFactoryHelpersKt;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrLocalDelegatedProperty;
import org.jetbrains.kotlin.ir.declarations.IrMutableAnnotationContainer;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrReplSnippet;
import org.jetbrains.kotlin.ir.declarations.IrScript;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrSymbolOwner;
import org.jetbrains.kotlin.ir.declarations.IrTypeAlias;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.kotlin.ir.declarations.impl.IrFactoryImpl;
import org.jetbrains.kotlin.ir.expressions.IrBlock;
import org.jetbrains.kotlin.ir.expressions.IrBlockBody;
import org.jetbrains.kotlin.ir.expressions.IrBranch;
import org.jetbrains.kotlin.ir.expressions.IrBreakContinue;
import org.jetbrains.kotlin.ir.expressions.IrCatch;
import org.jetbrains.kotlin.ir.expressions.IrComposite;
import org.jetbrains.kotlin.ir.expressions.IrConst;
import org.jetbrains.kotlin.ir.expressions.IrConstKind;
import org.jetbrains.kotlin.ir.expressions.IrDynamicOperator;
import org.jetbrains.kotlin.ir.expressions.IrDynamicOperatorExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody;
import org.jetbrains.kotlin.ir.expressions.IrGetValue;
import org.jetbrains.kotlin.ir.expressions.IrLoop;
import org.jetbrains.kotlin.ir.expressions.IrMemberAccessExpression;
import org.jetbrains.kotlin.ir.expressions.IrSpreadElement;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.IrStatementOriginImpl;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperator;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperatorCall;
import org.jetbrains.kotlin.ir.expressions.IrValueAccessExpression;
import org.jetbrains.kotlin.ir.expressions.IrVararg;
import org.jetbrains.kotlin.ir.expressions.IrVarargElement;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrBlockImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrBreakImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrCompositeImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrConstImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrConstructorCallImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrContinueImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrDynamicOperatorExpressionImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrFunctionExpressionImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrGetValueImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrReturnImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrSetFieldImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrWhenImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrClassifierSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.symbols.IrFieldSymbol;
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrLocalDelegatedPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrScriptSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrTypeParameterSymbol;
import org.jetbrains.kotlin.ir.symbols.IrValueSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrValueParameterSymbolImpl;
import org.jetbrains.kotlin.ir.types.IrDynamicType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypePredicatesKt;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.types.impl.IrErrorClassImplKt;
import org.jetbrains.kotlin.ir.types.impl.IrErrorTypeImpl;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.KotlinType;
import org.jetbrains.kotlin.types.TypeApproximatorConfiguration;
import org.jetbrains.kotlin.types.Variance;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000Ø\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u00012\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00040\u0002B\u0017\u0012\u0006\u0010\u0005\u001a\u00020\u0001\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ1\u0010\u0017\u001a\u0002H\u0018\"\u0004\b\u0000\u0010\u00182\b\b\u0002\u0010\u0019\u001a\u00020\u00112\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u0002H\u00180\u001bH\u0080\bø\u0001\u0000¢\u0006\u0004\b\u001c\u0010\u001dJ\u001a\u0010\u001e\u001a\u00020\u00032\u0006\u0010\u001f\u001a\u00020 2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010&\u001a\u00020'2\u0006\u0010(\u001a\u00020)2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\f\u0010*\u001a\u00020+*\u00020,H\u0002J\u001a\u0010-\u001a\u00020\u00032\u0006\u0010.\u001a\u00020/2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u00100\u001a\u00020\u00032\u0006\u00101\u001a\u0002022\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u00103\u001a\u0002042\u0006\u00105\u001a\u0002062\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u00107\u001a\u00020\u00032\u0006\u00108\u001a\u0002092\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010:\u001a\u00020\u00032\u0006\u0010;\u001a\u00020<2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010=\u001a\u00020\u00032\u0006\u0010>\u001a\u00020?2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010@\u001a\u00020\u00032\u0006\u0010A\u001a\u00020B2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010C\u001a\u00020\u00032\u0006\u0010D\u001a\u00020E2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010F\u001a\u00020\u00032\u0006\u0010G\u001a\u00020H2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010I\u001a\u00020\u00032\u0006\u0010J\u001a\u00020K2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010L\u001a\u00020\u00032\u0006\u0010M\u001a\u00020N2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010O\u001a\u00020\u00032\u0006\u0010P\u001a\u00020Q2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010R\u001a\u00020\u00032\u0006\u0010S\u001a\u00020T2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u0010\u0010U\u001a\u00020\u00032\u0006\u0010V\u001a\u00020WH\u0002J\u001a\u0010X\u001a\u00020\u00032\u0006\u0010Y\u001a\u00020W2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010Z\u001a\u00020\u00032\u0006\u0010[\u001a\u00020\\2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010]\u001a\u00020\u00032\u0006\u0010^\u001a\u00020_2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010`\u001a\u00020\u00032\u0006\u0010a\u001a\u00020b2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010c\u001a\u00020\u00032\u0006\u0010d\u001a\u00020e2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\f\u0010f\u001a\u00020g*\u00020hH\u0002J\u0010\u0010i\u001a\u00020j2\u0006\u0010k\u001a\u00020lH\u0002J\u001a\u0010i\u001a\u00020j2\u0006\u0010k\u001a\u00020l2\b\u0010m\u001a\u0004\u0018\u00010nH\u0002J\u0010\u0010o\u001a\u00020j2\u0006\u0010k\u001a\u00020lH\u0002J\u001a\u0010p\u001a\u00020j2\u0006\u0010k\u001a\u00020l2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010q\u001a\u00020\u00032\u0006\u0010r\u001a\u00020s2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010t\u001a\u00020\u00032\u0006\u0010u\u001a\u00020v2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010w\u001a\u00020\u00032\u0006\u0010x\u001a\u00020y2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010z\u001a\u00020\u00032\u0006\u0010{\u001a\u00020|2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001a\u0010}\u001a\u00020\u00032\u0006\u0010~\u001a\u00020\u007f2\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001d\u0010\u0080\u0001\u001a\u00020\u00032\b\u0010\u0081\u0001\u001a\u00030\u0082\u00012\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u0011\u0010\u0083\u0001\u001a\u00020j2\u0006\u0010~\u001a\u00020\u007fH\u0002J\u0013\u0010\u0084\u0001\u001a\u00020\u00112\b\u0010\u0085\u0001\u001a\u00030\u0086\u0001H\u0002J\u001d\u0010\u0087\u0001\u001a\u00020\u00032\b\u0010\u0088\u0001\u001a\u00030\u0089\u00012\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J#\u0010\u008a\u0001\u001a\u0004\u0018\u00010\u00032\b\u0010\u0088\u0001\u001a\u00030\u0089\u00012\f\u0010\u008b\u0001\u001a\u0007\u0012\u0002\b\u00030\u008c\u0001H\u0002J\u001d\u0010\u008d\u0001\u001a\u00020\u00032\b\u0010\u0088\u0001\u001a\u00030\u0089\u00012\b\u0010\u008e\u0001\u001a\u00030\u008f\u0001H\u0002J\u001d\u0010\u0090\u0001\u001a\u00020\u00032\b\u0010\u0088\u0001\u001a\u00030\u0089\u00012\b\u0010\u0091\u0001\u001a\u00030\u0092\u0001H\u0002J#\u0010\u0093\u0001\u001a\u0004\u0018\u00010\u00032\b\u0010\u0088\u0001\u001a\u00030\u0089\u00012\f\u0010\u0094\u0001\u001a\u0007\u0012\u0002\b\u00030\u0095\u0001H\u0002J\u001d\u0010\u0096\u0001\u001a\u00020\u00032\b\u0010\u0097\u0001\u001a\u00030\u0098\u00012\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001d\u0010\u0099\u0001\u001a\u00020\u00032\b\u0010\u009a\u0001\u001a\u00030\u009b\u00012\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001d\u0010\u009c\u0001\u001a\u00020\u00032\b\u0010\u009d\u0001\u001a\u00030\u009e\u00012\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001c\u0010\u009f\u0001\u001a\u00020j2\b\u0010\u009d\u0001\u001a\u00030\u009e\u00012\u0007\u0010 \u0001\u001a\u00020\u0011H\u0002J\u001d\u0010¡\u0001\u001a\u00020j2\b\u0010¢\u0001\u001a\u00030£\u00012\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001d\u0010¤\u0001\u001a\u00020\u00032\b\u0010¥\u0001\u001a\u00030¦\u00012\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001d\u0010§\u0001\u001a\u00020\u00032\b\u0010¨\u0001\u001a\u00030©\u00012\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u0011\u0010ª\u0001\u001a\u0005\u0018\u00010«\u0001*\u00030¬\u0001H\u0002J1\u0010¯\u0001\u001a\u00020j2\u0007\u0010°\u0001\u001a\u00020h2\t\b\u0002\u0010 \u0001\u001a\u00020\u00112\f\b\u0002\u0010±\u0001\u001a\u0005\u0018\u00010²\u0001H\u0000¢\u0006\u0003\b³\u0001J%\u0010´\u0001\u001a\u0004\u0018\u00010j2\t\u0010µ\u0001\u001a\u0004\u0018\u00010h2\u0007\u0010¶\u0001\u001a\u00020\u007fH\u0000¢\u0006\u0003\b·\u0001J\u0019\u0010¸\u0001\u001a\u0005\u0018\u00010²\u0001*\u00020\u007f2\u0007\u0010µ\u0001\u001a\u00020hH\u0002J\u0019\u0010¹\u0001\u001a\u00030º\u00012\u0007\u0010\u001a\u001a\u00030»\u0001H\u0000¢\u0006\u0003\b¼\u0001J\u0013\u0010½\u0001\u001a\u0004\u0018\u00010l2\u0006\u0010k\u001a\u00020lH\u0002J\u000f\u0010¾\u0001\u001a\u00030¬\u0001*\u00030¬\u0001H\u0002J\u0010\u0010¿\u0001\u001a\u0004\u0018\u00010j*\u00030»\u0001H\u0002J&\u0010À\u0001\u001a\u00020j*\u00030»\u00012\n\u0010Á\u0001\u001a\u0005\u0018\u00010Â\u00012\n\u0010±\u0001\u001a\u0005\u0018\u00010²\u0001H\u0002J&\u0010Ã\u0001\u001a\u00020j*\u00030»\u00012\n\u0010Á\u0001\u001a\u0005\u0018\u00010Â\u00012\n\u0010±\u0001\u001a\u0005\u0018\u00010²\u0001H\u0002J\u001d\u0010Ä\u0001\u001a\u00020\u00032\b\u0010Å\u0001\u001a\u00030Æ\u00012\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001d\u0010Ç\u0001\u001a\u00020\u00032\b\u0010È\u0001\u001a\u00030É\u00012\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001d\u0010Ê\u0001\u001a\u00020\u00032\b\u0010Ë\u0001\u001a\u00030Ì\u00012\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001d\u0010Í\u0001\u001a\u00020\u00032\b\u0010Î\u0001\u001a\u00030Ï\u00012\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u000e\u0010Ð\u0001\u001a\u00020\u0011*\u00030Ï\u0001H\u0002J:\u0010Ñ\u0001\u001a\n\u0012\u0005\u0012\u00030Ó\u00010Ò\u0001*\u00030Ï\u00012\u000f\u0010Ô\u0001\u001a\n\u0012\u0005\u0012\u00030Ó\u00010Ò\u00012\b\u0010Õ\u0001\u001a\u00030²\u00012\u0007\u0010Ö\u0001\u001a\u00020\u0011H\u0002J\u0011\u0010×\u0001\u001a\u0005\u0018\u00010Ï\u0001*\u00030Ø\u0001H\u0002JP\u0010Ù\u0001\u001a\u00020j2\b\u0010Ú\u0001\u001a\u00030Û\u00012\b\u0010Ü\u0001\u001a\u00030Û\u00012\n\u0010Á\u0001\u001a\u0005\u0018\u00010Â\u00012\n\u0010Ý\u0001\u001a\u0005\u0018\u00010Þ\u00012\u000f\u0010ß\u0001\u001a\n\u0012\u0005\u0012\u00030Ó\u00010à\u00012\b\u0010á\u0001\u001a\u00030â\u0001H\u0002J\u0016\u0010ã\u0001\u001a\u0005\u0018\u00010Þ\u00012\b\u0010Î\u0001\u001a\u00030Ï\u0001H\u0002J\u0019\u0010ä\u0001\u001a\u00030Ó\u0001*\u00030Ø\u00012\b\u0010Õ\u0001\u001a\u00030²\u0001H\u0002J\u001d\u0010å\u0001\u001a\u00020\u00032\b\u0010æ\u0001\u001a\u00030ç\u00012\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001d\u0010ì\u0001\u001a\u00020\u00032\b\u0010í\u0001\u001a\u00030î\u00012\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001d\u0010ï\u0001\u001a\u00020\u00032\b\u0010ð\u0001\u001a\u00030ñ\u00012\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u008c\u0001\u0010ò\u0001\u001a\u00020j*\n\u0012\u0005\u0012\u00030ê\u00010ó\u00012u\u0010ô\u0001\u001ap\u0012\u0017\u0012\u00150Û\u0001¢\u0006\u000f\bö\u0001\u0012\n\b÷\u0001\u0012\u0005\b\b(Ú\u0001\u0012\u0017\u0012\u00150Û\u0001¢\u0006\u000f\bö\u0001\u0012\n\b÷\u0001\u0012\u0005\b\b(Ü\u0001\u0012\u0017\u0012\u00150ë\u0001¢\u0006\u000f\bö\u0001\u0012\n\b÷\u0001\u0012\u0005\b\b(ø\u0001\u0012\u0019\u0012\u0017\u0018\u00010ù\u0001¢\u0006\u000f\bö\u0001\u0012\n\b÷\u0001\u0012\u0005\b\b(ú\u0001\u0012\u0005\u0012\u00030û\u00010õ\u0001H\u0002J\u001d\u0010ü\u0001\u001a\u00020\u00032\b\u0010ý\u0001\u001a\u00030þ\u00012\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001d\u0010ÿ\u0001\u001a\u00020\u00032\b\u0010\u0080\u0002\u001a\u00030\u0081\u00022\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001d\u0010\u0082\u0002\u001a\u00020\u00032\b\u0010\u0083\u0002\u001a\u00030\u0084\u00022\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001d\u0010\u0085\u0002\u001a\u00020\u00032\b\u0010\u0086\u0002\u001a\u00030\u0087\u00022\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001e\u0010\u0088\u0002\u001a\u00030\u0089\u00022\b\u0010\u008a\u0002\u001a\u00030\u008b\u00022\b\u0010±\u0001\u001a\u00030²\u0001H\u0002J\u001d\u0010\u008c\u0002\u001a\u00020\u00032\b\u0010\u008d\u0002\u001a\u00030\u008b\u00022\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001d\u0010\u008e\u0002\u001a\u00020\u00032\b\u0010\u008f\u0002\u001a\u00030\u0090\u00022\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001d\u0010\u0091\u0002\u001a\u00020\u00032\b\u0010\u0092\u0002\u001a\u00030\u0093\u00022\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001d\u0010\u0094\u0002\u001a\u00020\u00032\b\u0010\u0095\u0002\u001a\u00030\u0096\u00022\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001d\u0010\u0097\u0002\u001a\u00020\u00032\b\u0010\u0098\u0002\u001a\u00030\u0099\u00022\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001d\u0010\u009a\u0002\u001a\u00020\u00032\b\u0010\u009b\u0002\u001a\u00030\u009c\u00022\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001d\u0010\u009d\u0002\u001a\u00020\u00032\b\u0010\u009e\u0002\u001a\u00030\u009f\u00022\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u0012\u0010 \u0002\u001a\u0005\u0018\u00010\u0086\u0001*\u0004\u0018\u00010\u0016H\u0002J \u0010¡\u0002\u001a\u00030¢\u00022\b\u0010£\u0002\u001a\u00030¤\u00022\n\u0010±\u0001\u001a\u0005\u0018\u00010²\u0001H\u0002J\u001d\u0010¥\u0002\u001a\u00020\u00032\b\u0010¦\u0002\u001a\u00030§\u00022\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001d\u0010¨\u0002\u001a\u00020\u00032\b\u0010©\u0002\u001a\u00030ª\u00022\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u001d\u0010«\u0002\u001a\u00020\u00032\b\u0010¬\u0002\u001a\u00030\u00ad\u00022\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u000e\u0010®\u0002\u001a\u00020n*\u00030¯\u0002H\u0002J\u001d\u0010°\u0002\u001a\u00020\u00032\b\u0010±\u0002\u001a\u00030²\u00022\b\u0010!\u001a\u0004\u0018\u00010\u0004H\u0016J\u0018\u0010³\u0002\u001a\u00020\u00112\u0007\u0010°\u0001\u001a\u00020hH\u0000¢\u0006\u0003\b´\u0002R\u000e\u0010\u0005\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000R\u0011\u0010\u0012\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014R\u000e\u0010\u0015\u001a\u00020\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010\u00ad\u0001\u001a\u00020\u0011*\u00020W8BX\u0082\u0004¢\u0006\b\u001a\u0006\b\u00ad\u0001\u0010®\u0001R\u001e\u0010è\u0001\u001a\u0011\u0012\u0005\u0012\u00030ê\u0001\u0012\u0005\u0012\u00030ë\u00010é\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010µ\u0002\u001a\u00030¶\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b·\u0002\u0010¸\u0002R\u0016\u0010¹\u0002\u001a\u00030º\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b»\u0002\u0010¼\u0002R\u0016\u0010½\u0002\u001a\u00030¾\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b¿\u0002\u0010À\u0002R\u0016\u0010Á\u0002\u001a\u00030Â\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bÃ\u0002\u0010Ä\u0002R\u0016\u0010Å\u0002\u001a\u00030Æ\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bÇ\u0002\u0010È\u0002R\u0016\u0010É\u0002\u001a\u00030Ê\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bË\u0002\u0010Ì\u0002R\u0016\u0010Í\u0002\u001a\u00030Î\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bÏ\u0002\u0010Ð\u0002R\u0016\u0010Ñ\u0002\u001a\u00030Ò\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bÓ\u0002\u0010Ô\u0002R\u0016\u0010Õ\u0002\u001a\u00030Ö\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b×\u0002\u0010Ø\u0002R\u0016\u0010Ù\u0002\u001a\u00030Ú\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bÛ\u0002\u0010Ü\u0002R\u0016\u0010Ý\u0002\u001a\u00030Þ\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bß\u0002\u0010à\u0002R\u0016\u0010á\u0002\u001a\u00030â\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bã\u0002\u0010ä\u0002R\u0016\u0010å\u0002\u001a\u00030æ\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bç\u0002\u0010è\u0002R\u001e\u0010é\u0002\u001a\u000b\u0012\u0004\u0012\u00020)\u0018\u00010ê\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bë\u0002\u0010ì\u0002R\u0016\u0010í\u0002\u001a\u00030î\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bï\u0002\u0010ð\u0002R\u0016\u0010ñ\u0002\u001a\u00030ò\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bó\u0002\u0010ô\u0002R\u0016\u0010õ\u0002\u001a\u00030ö\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\b÷\u0002\u0010ø\u0002R\u001d\u0010ù\u0002\u001a\n\u0012\u0005\u0012\u00030ú\u00020à\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bû\u0002\u0010ü\u0002R\u0016\u0010ý\u0002\u001a\u00030þ\u0002X\u0096\u0005¢\u0006\b\u001a\u0006\bÿ\u0002\u0010\u0080\u0003R\u0016\u0010\u0081\u0003\u001a\u00030\u0082\u0003X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0083\u0003\u0010\u0084\u0003R\u0016\u0010\u0085\u0003\u001a\u00030\u0086\u0003X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0087\u0003\u0010\u0088\u0003R\u0016\u0010\u0089\u0003\u001a\u00030\u008a\u0003X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008b\u0003\u0010\u008c\u0003R\u0016\u0010\u008d\u0003\u001a\u00030\u008e\u0003X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008f\u0003\u0010\u0090\u0003R\u0018\u0010\u0091\u0003\u001a\u0005\u0018\u00010\u0092\u0003X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0093\u0003\u0010\u0094\u0003R\u0016\u0010\u0095\u0003\u001a\u00030\u0096\u0003X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0097\u0003\u0010\u0098\u0003R\u0016\u0010\u0099\u0003\u001a\u00030\u009a\u0003X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009b\u0003\u0010\u009c\u0003R\u0016\u0010\u009d\u0003\u001a\u00030\u009e\u0003X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009f\u0003\u0010 \u0003\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006¡\u0003"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisitor;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "Lorg/jetbrains/kotlin/fir/visitors/FirDefaultVisitor;", "Lorg/jetbrains/kotlin/ir/IrElement;", Argument.Delimiters.none, "c", "conversionScope", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;)V", "cleaner", "Lorg/jetbrains/kotlin/fir/backend/FirDeclarationsContentCleaner;", "memberGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/ClassMemberGenerator;", "operatorGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/OperatorExpressionGenerator;", "_annotationMode", Argument.Delimiters.none, "annotationMode", "getAnnotationMode", "()Z", "unitType", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeType;", "withAnnotationMode", "T", "enableAnnotationMode", "block", "Lkotlin/Function0;", "withAnnotationMode$org_jetbrains_kotlin_fir2ir", "(ZLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "visitElement", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "data", "visitField", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "field", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "visitFile", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "file", "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "toIrDeclaration", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclaration;", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclaration;", "visitTypeAlias", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "visitEnumEntry", "enumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "visitRegularClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "visitScript", "script", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "visitCodeFragment", "codeFragment", "Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;", "visitReplSnippet", "replSnippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "visitAnonymousObjectExpression", "anonymousObjectExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousObjectExpression;", "visitAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "visitConstructor", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "visitAnonymousInitializer", "anonymousInitializer", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "visitNamedFunction", "namedFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "visitAnonymousFunctionExpression", "anonymousFunctionExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirAnonymousFunctionExpression;", "visitAnonymousFunction", "anonymousFunction", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousFunction;", "visitLocalVariable", "variable", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "visitProperty", "property", "visitReturnExpression", "returnExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirReturnExpression;", "visitWrappedArgumentExpression", "wrappedArgumentExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWrappedArgumentExpression;", "visitFunctionTypeConversionExpression", "functionTypeConversionExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionTypeConversionExpression;", "visitVarargArgumentsExpression", "varargArgumentsExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirVarargArgumentsExpression;", "convertToIrVarargElement", "Lorg/jetbrains/kotlin/ir/expressions/IrVarargElement;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "convertToIrCall", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "functionCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "dynamicOperator", "Lorg/jetbrains/kotlin/ir/expressions/IrDynamicOperator;", "convertToIrArraySetDynamicCall", "visitFunctionCall", "visitSafeCallExpression", "safeCallExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSafeCallExpression;", "visitCheckedSafeCallSubject", "checkedSafeCallSubject", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckedSafeCallSubject;", "visitAnnotation", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "visitAnnotationCall", "annotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "visitQualifiedAccessExpression", "qualifiedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", "visitPropertyAccessExpression", "propertyAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirPropertyAccessExpression;", "convertQualifiedAccessExpression", "shouldGenerateReceiverAsSingletonReference", "irClassSymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "visitThisReceiverExpression", "thisReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThisReceiverExpression;", "generateThisReceiverAccessForClass", "firClassSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "generateThisReceiverAccessForScript", "firScriptSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirScriptSymbol;", "generateThisReceiverAccessForReplSnippet", "firSnippetSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirReplSnippetSymbol;", "generateThisReceiverAccessForCallable", "firCallableSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "visitInaccessibleReceiverExpression", "inaccessibleReceiverExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirInaccessibleReceiverExpression;", "visitSmartCastExpression", "smartCastExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirSmartCastExpression;", "visitCallableReferenceAccess", "callableReferenceAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "convertCallableReferenceAccess", "isDelegate", "visitVariableAssignment", "variableAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "visitDesugaredAssignmentValueReferenceExpression", "desugaredAssignmentValueReferenceExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirDesugaredAssignmentValueReferenceExpression;", "visitLiteralExpression", "literalExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirLiteralExpression;", "toIrStatement", "Lorg/jetbrains/kotlin/ir/IrStatement;", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "isUnnamedLocalVariable", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Z", "convertToIrExpression", "expression", "expectedType", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "convertToIrExpression$org_jetbrains_kotlin_fir2ir", "convertToIrReceiverExpression", "receiver", "selector", "convertToIrReceiverExpression$org_jetbrains_kotlin_fir2ir", "expectedReceiverType", "convertToIrBlockBody", "Lorg/jetbrains/kotlin/ir/expressions/IrBlockBody;", "Lorg/jetbrains/kotlin/fir/expressions/FirBlock;", "convertToIrBlockBody$org_jetbrains_kotlin_fir2ir", "extractOperationFromDynamicSetCall", "unwrapDesugaredAssignmentValueReference", "tryConvertDynamicIncrementOrDecrementToIr", "convertToIrExpressionOrBlock", "origin", "Lorg/jetbrains/kotlin/ir/expressions/IrStatementOrigin;", "convertToIrBlock", "visitErrorExpression", "errorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorExpression;", "visitEnumEntryDeserializedAccessExpression", "enumEntryDeserializedAccessExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirEnumEntryDeserializedAccessExpression;", "visitElvisExpression", "elvisExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirElvisExpression;", "visitWhenExpression", "whenExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenExpression;", "isDeeplyProperlyExhaustive", "convertWhenBranchesTo", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/expressions/IrBranch;", CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, "whenExpressionType", "flattenElse", "nestedElseIfOrNull", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenBranch;", "generateWhen", "startOffset", Argument.Delimiters.none, "endOffset", "subjectVariable", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "branches", Argument.Delimiters.none, "resultType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "generateWhenSubjectVariable", "toIrWhenBranch", "visitWhenSubjectExpression", "whenSubjectExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirWhenSubjectExpression;", "loopMap", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirLoop;", "Lorg/jetbrains/kotlin/ir/expressions/IrLoop;", "visitDoWhileLoop", "doWhileLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirDoWhileLoop;", "visitWhileLoop", "whileLoop", "Lorg/jetbrains/kotlin/fir/expressions/FirWhileLoop;", "convertJumpWithOffsets", "Lorg/jetbrains/kotlin/fir/expressions/FirJump;", "f", "Lkotlin/Function4;", "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "irLoop", Argument.Delimiters.none, CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "Lorg/jetbrains/kotlin/ir/expressions/IrBreakContinue;", "visitBreakExpression", "breakExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBreakExpression;", "visitContinueExpression", "continueExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirContinueExpression;", "visitThrowExpression", "throwExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirThrowExpression;", "visitTryExpression", "tryExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirTryExpression;", "convertCatch", "Lorg/jetbrains/kotlin/ir/expressions/IrCatch;", "firCatch", "Lorg/jetbrains/kotlin/fir/expressions/FirCatch;", "visitCatch", "catch", "visitComparisonExpression", "comparisonExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirComparisonExpression;", "visitStringConcatenationCall", "stringConcatenationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirStringConcatenationCall;", "visitTypeOperatorCall", "typeOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirTypeOperatorCall;", "visitEqualityOperatorCall", "equalityOperatorCall", "Lorg/jetbrains/kotlin/fir/expressions/FirEqualityOperatorCall;", "visitCheckNotNullCall", "checkNotNullCall", "Lorg/jetbrains/kotlin/fir/expressions/FirCheckNotNullCall;", "visitGetClassCall", "getClassCall", "Lorg/jetbrains/kotlin/fir/expressions/FirGetClassCall;", "toIrClassSymbol", "convertToArrayLiteral", "Lorg/jetbrains/kotlin/ir/expressions/IrVararg;", "arrayLiteral", "Lorg/jetbrains/kotlin/fir/expressions/FirCollectionLiteral;", "visitIndexedAccessAugmentedAssignment", "indexedAccessAugmentedAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirIndexedAccessAugmentedAssignment;", "visitResolvedQualifier", "resolvedQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "visitErrorResolvedQualifier", "errorResolvedQualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirErrorResolvedQualifier;", "toIrDynamicOperator", "Lorg/jetbrains/kotlin/contracts/description/LogicOperationKind;", "visitBooleanOperatorExpression", "booleanOperatorExpression", "Lorg/jetbrains/kotlin/fir/expressions/FirBooleanOperatorExpression;", "isGetClassOfUnresolvedTypeInAnnotation", "isGetClassOfUnresolvedTypeInAnnotation$org_jetbrains_kotlin_fir2ir", "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", Argument.Delimiters.none, "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "()Ljava/util/List;", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrVisitor extends FirDefaultVisitor<IrElement, Object> implements Fir2IrComponents {
    private boolean _annotationMode;
    private final Fir2IrComponents c;
    private final FirDeclarationsContentCleaner cleaner;
    private final Fir2IrConversionScope conversionScope;
    private final Map<FirLoop, IrLoop> loopMap;
    private final ClassMemberGenerator memberGenerator;
    private final OperatorExpressionGenerator operatorGenerator;
    private final ConeClassLikeType unitType;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;
        public static final /* synthetic */ int[] $EnumSwitchMapping$1;

        static {
            int[] iArr = new int[FirOperation.values().length];
            try {
                iArr[FirOperation.IS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[FirOperation.NOT_IS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                iArr[FirOperation.AS.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                iArr[FirOperation.SAFE_AS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            $EnumSwitchMapping$0 = iArr;
            int[] iArr2 = new int[LogicOperationKind.values().length];
            try {
                iArr2[LogicOperationKind.AND.ordinal()] = 1;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                iArr2[LogicOperationKind.OR.ordinal()] = 2;
            } catch (NoSuchFieldError unused6) {
            }
            $EnumSwitchMapping$1 = iArr2;
        }
    }

    public Fir2IrVisitor(Fir2IrComponents fir2IrComponents, Fir2IrConversionScope fir2IrConversionScope) {
        fir2IrComponents.getClass();
        fir2IrConversionScope.getClass();
        this.c = fir2IrComponents;
        this.conversionScope = fir2IrConversionScope;
        FirDeclarationsContentCleaner firDeclarationsContentCleanerCreate = FirDeclarationsContentCleaner.INSTANCE.create(this);
        this.cleaner = firDeclarationsContentCleanerCreate;
        this.memberGenerator = new ClassMemberGenerator(fir2IrComponents, this, fir2IrConversionScope, firDeclarationsContentCleanerCreate);
        this.operatorGenerator = new OperatorExpressionGenerator(fir2IrComponents, this, fir2IrConversionScope);
        this.unitType = getSession().getBuiltinTypes().getUnitType().getConeType();
        this.loopMap = new LinkedHashMap();
    }

    public static final /* synthetic */ boolean access$get_annotationMode$p(Fir2IrVisitor fir2IrVisitor) {
        return fir2IrVisitor._annotationMode;
    }

    public static final /* synthetic */ void access$set_annotationMode$p(Fir2IrVisitor fir2IrVisitor, boolean z) {
        fir2IrVisitor._annotationMode = z;
    }

    public static IrGetValueImpl b(IrVariable irVariable, int i, int i2) {
        return BuildersKt.IrGetValueImpl$default(i, i2, irVariable.getType(), irVariable.getSymbol(), (IrStatementOrigin) null, 16, (Object) null);
    }

    public static IrBreakContinue c(Fir2IrVisitor fir2IrVisitor, int i, int i2, IrLoop irLoop, String str) {
        irLoop.getClass();
        IrContinueImpl IrContinueImpl = BuildersKt.IrContinueImpl(i, i2, fir2IrVisitor.getBuiltins().getNothingType(), irLoop);
        IrContinueImpl.setLabel(str);
        return IrContinueImpl;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final IrExpression convertCallableReferenceAccess(FirCallableReferenceAccess callableReferenceAccess, boolean isDelegate) throws KotlinIllegalArgumentExceptionWithAttachments {
        return getCallGenerator().convertToIrCallableReference(callableReferenceAccess, convertToIrReceiverExpression$org_jetbrains_kotlin_fir2ir(callableReferenceAccess.getExplicitReceiver(), callableReferenceAccess), isDelegate);
    }

    /* JADX WARN: Code duplicated, block: B:30:0x005f  */
    private final IrCatch convertCatch(FirCatch firCatch, ConeKotlinType expectedType) {
        int i;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        KtSourceElement source = firCatch.getSource();
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            i = -1;
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                i = -1;
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    i = -1;
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        i = -1;
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        i = startOffset;
                        endOffset = source != null ? source.getEndOffset() : -1;
                    }
                }
            }
        }
        return BuildersKt.IrCatchImpl$default(i, endOffset, getDeclarationStorage().createAndCacheIrVariable(firCatch.getParameter(), this.conversionScope.parentFromStack(), IrDeclarationOrigin.Companion.getCATCH_PARAMETER()), ImplicitConversionUtilsKt.prepareExpressionForGivenExpectedType$default(this, convertToIrBlock(firCatch.getBlock(), null, FirTypeUtilsKt.getResolvedType(firCatch.getBlock())), firCatch.getBlock(), null, expectedType, null, false, 20, null), (IrStatementOrigin) null, 16, (Object) null);
    }

    /* JADX WARN: Code duplicated, block: B:30:0x005f  */
    private final IrExpression convertJumpWithOffsets(FirJump<FirLoop> firJump, Function4<? super Integer, ? super Integer, ? super IrLoop, ? super String, ? extends IrBreakContinue> function4) {
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        KtSourceElement source = firJump.getSource();
        int i = -1;
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        endOffset = source != null ? source.getEndOffset() : -1;
                        i = startOffset;
                    }
                }
            }
        }
        IrLoop irLoop = this.loopMap.get((FirLoop) firJump.getTarget().getLabeledElement());
        if (irLoop != null) {
            return (IrExpression) function4.invoke(Integer.valueOf(i), Integer.valueOf(endOffset), irLoop, firJump.getTarget().getLabelName() != null ? irLoop.getLabel() : null);
        }
        return BuildersKt.IrErrorExpressionImpl(i, endOffset, getBuiltins().getNothingType(), "Unbound loop: " + UtilsKt.render(firJump));
    }

    private final IrExpression convertQualifiedAccessExpression(FirQualifiedAccessExpression qualifiedAccessExpression) {
        FirQualifiedAccessExpression firQualifiedAccessExpression;
        FirSession session = getSession();
        try {
            firQualifiedAccessExpression = qualifiedAccessExpression;
            try {
                return CallAndReferenceGenerator.convertToIrCall$default(getCallGenerator(), firQualifiedAccessExpression, FirTypeUtilsKt.getResolvedType(qualifiedAccessExpression), convertToIrReceiverExpression$org_jetbrains_kotlin_fir2ir(qualifiedAccessExpression.getExplicitReceiver(), qualifiedAccessExpression), null, false, false, 56, null);
            } catch (Throwable th) {
                th = th;
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(firQualifiedAccessExpression, th);
                wq6.a();
                return null;
            }
        } catch (Throwable th2) {
            th = th2;
            firQualifiedAccessExpression = qualifiedAccessExpression;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:30:0x005f  */
    private final IrVararg convertToArrayLiteral(FirCollectionLiteral arrayLiteral, ConeKotlinType expectedType) throws KotlinIllegalArgumentExceptionWithAttachments {
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        KtSourceElement source = arrayLiteral.getSource();
        int i = -1;
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        endOffset = source != null ? source.getEndOffset() : -1;
                        i = startOffset;
                    }
                }
            }
        }
        if (expectedType == null) {
            expectedType = FirTypeUtilsKt.getResolvedType(arrayLiteral);
        }
        IrType irType$default = Fir2IrTypeConverterKt.toIrType$default(this, expectedType, (ConversionTypeOrigin) null, 2, (Object) null);
        IrType arrayElementType = VariousUtilsKt.getArrayElementType(irType$default, getBuiltins());
        List<FirExpression> arguments = arrayLiteral.getArgumentList().getArguments();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arguments, 10));
        Iterator<T> it = arguments.iterator();
        while (it.hasNext()) {
            arrayList.add(convertToIrVarargElement((FirExpression) it.next()));
        }
        return BuildersKt.IrVarargImpl(i, endOffset, irType$default, arrayElementType, arrayList);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final IrExpression convertToIrArraySetDynamicCall(FirFunctionCall functionCall) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrDynamicOperatorExpression irDynamicOperatorExpressionConvertToIrCall = convertToIrCall(functionCall, IrDynamicOperator.ARRAY_ACCESS);
        IrDynamicOperatorExpression irDynamicOperatorExpression = irDynamicOperatorExpressionConvertToIrCall instanceof IrDynamicOperatorExpression ? irDynamicOperatorExpressionConvertToIrCall : null;
        if (irDynamicOperatorExpression == null) {
            f2f.a("Converting dynamic array access should have resulted in IrDynamicOperatorExpression: ", UtilsKt.render(functionCall));
            return null;
        }
        IrExpression irExpression = (IrExpression) CollectionsKt.removeLast(irDynamicOperatorExpression.getArguments());
        IrDynamicOperatorExpressionImpl IrDynamicOperatorExpressionImpl = BuildersKt.IrDynamicOperatorExpressionImpl(irDynamicOperatorExpression.getStartOffset(), irDynamicOperatorExpression.getEndOffset(), irDynamicOperatorExpression.getType(), IrDynamicOperator.EQ);
        IrDynamicOperatorExpressionImpl.setReceiver(irDynamicOperatorExpression);
        IrDynamicOperatorExpressionImpl.getArguments().add(irExpression);
        return IrDynamicOperatorExpressionImpl;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:20:0x0033  */
    /* JADX WARN: Code duplicated, block: B:49:0x009a  */
    private final IrExpression convertToIrBlock(FirBlock firBlock, IrStatementOrigin irStatementOrigin, ConeKotlinType coneKotlinType) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrType unitType;
        ConeKotlinType resolvedType;
        int i;
        int endOffset;
        IrCompositeImpl irCompositeImplIrBlockImpl;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        if (coneKotlinType == null || (unitType = Fir2IrTypeConverterKt.toIrType$default(this, coneKotlinType, (ConversionTypeOrigin) null, 2, (Object) null)) == null) {
            Object objLastOrNull = CollectionsKt.lastOrNull(firBlock.getStatements());
            FirExpression firExpression = objLastOrNull instanceof FirExpression ? (FirExpression) objLastOrNull : null;
            if (firExpression == null || (resolvedType = FirTypeUtilsKt.getResolvedType(firExpression)) == null) {
                unitType = getBuiltins().getUnitType();
            } else {
                if (ConeBuiltinTypeUtilsKt.isNothing(resolvedType)) {
                    resolvedType = null;
                }
                if (resolvedType != null) {
                    unitType = Fir2IrTypeConverterKt.toIrType$default(this, resolvedType, (ConversionTypeOrigin) null, 2, (Object) null);
                } else {
                    unitType = getBuiltins().getUnitType();
                }
            }
        }
        IrType irType = unitType;
        KtSourceElement source = firBlock.getSource();
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            i = -1;
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                i = -1;
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    i = -1;
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        i = -1;
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        i = startOffset;
                        endOffset = source != null ? source.getEndOffset() : -1;
                    }
                }
            }
        }
        if (Intrinsics.areEqual(irStatementOrigin, IrStatementOrigin.Companion.getDO_WHILE_LOOP())) {
            List<FirStatement> statements = firBlock.getStatements();
            ArrayList arrayList = new ArrayList();
            Iterator<T> it = statements.iterator();
            while (it.hasNext()) {
                IrStatement irStatement = toIrStatement((FirStatement) it.next());
                if (irStatement != null) {
                    arrayList.add(irStatement);
                }
            }
            irCompositeImplIrBlockImpl = new IrCompositeImpl(i, endOffset, irType, (IrStatementOrigin) null, arrayList);
        } else {
            List<FirStatement> statements2 = firBlock.getStatements();
            ArrayList arrayList2 = new ArrayList();
            Iterator<T> it2 = statements2.iterator();
            while (it2.hasNext()) {
                IrStatement irStatement2 = toIrStatement((FirStatement) it2.next());
                if (irStatement2 != null) {
                    arrayList2.add(irStatement2);
                }
            }
            IrCompositeImpl irCompositeImpl = (IrStatement) CollectionsKt.singleOrNull(arrayList2);
            IrCompositeImpl irCompositeImpl2 = irCompositeImpl instanceof IrBlock ? (IrBlock) irCompositeImpl : null;
            irCompositeImplIrBlockImpl = irCompositeImpl2 == null ? BuildersKt.IrBlockImpl(i, endOffset, irType, irStatementOrigin, arrayList2) : irCompositeImpl2;
        }
        ImplicitConversionUtilsKt.coerceStatementsToUnit(this, irCompositeImplIrBlockImpl, false);
        return IrTypePredicatesKt.isUnit(irType) ? ImplicitConversionUtilsKt.coerceToUnitHandlingSpecialBlocks(this, irCompositeImplIrBlockImpl) : irCompositeImplIrBlockImpl;
    }

    private final IrExpression convertToIrCall(FirFunctionCall functionCall) {
        FirNamedFunctionSymbol resolvedNamedFunctionSymbol$default = FirReferenceUtilsKt.toResolvedNamedFunctionSymbol$default(functionCall.getCalleeReference(), false, 1, null);
        if (Intrinsics.areEqual(resolvedNamedFunctionSymbol$default != null ? resolvedNamedFunctionSymbol$default.getOrigin() : null, FirDeclarationOrigin.DynamicScope.INSTANCE) && Intrinsics.areEqual(functionCall.getCalleeReference().getName(), OperatorNameConventions.SET)) {
            KtSourceElement source = functionCall.getCalleeReference().getSource();
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ArrayAccessNameReference.INSTANCE)) {
                return convertToIrArraySetDynamicCall(functionCall);
            }
        }
        return convertToIrCall(functionCall, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static /* synthetic */ IrExpression convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(Fir2IrVisitor fir2IrVisitor, FirExpression firExpression, boolean z, ConeKotlinType coneKotlinType, int i, Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
        if ((i & 2) != 0) {
            z = false;
        }
        if ((i & 4) != 0) {
            coneKotlinType = null;
        }
        return fir2IrVisitor.convertToIrExpression$org_jetbrains_kotlin_fir2ir(firExpression, z, coneKotlinType);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final IrExpression convertToIrExpressionOrBlock(FirBlock firBlock, IrStatementOrigin irStatementOrigin, ConeKotlinType coneKotlinType) throws KotlinIllegalArgumentExceptionWithAttachments {
        Fir2IrVisitor fir2IrVisitor;
        IrExpression irExpressionTryConvertDynamicIncrementOrDecrementToIr;
        boolean z = false;
        if (coneKotlinType != null && ConeBuiltinTypeUtilsKt.isUnit(coneKotlinType)) {
            z = true;
        }
        KtSourceElement source = firBlock.getSource();
        if (((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind.DesugaredIncrementOrDecrement) && (irExpressionTryConvertDynamicIncrementOrDecrementToIr = tryConvertDynamicIncrementOrDecrementToIr(firBlock)) != null) {
            return z ? ImplicitConversionUtilsKt.coerceToUnitHandlingSpecialBlocks(this, irExpressionTryConvertDynamicIncrementOrDecrementToIr) : irExpressionTryConvertDynamicIncrementOrDecrementToIr;
        }
        if (firBlock instanceof FirSingleExpressionBlock) {
            FirStatement statement = ((FirSingleExpressionBlock) firBlock).getStatement();
            if (statement instanceof FirExpression) {
                IrExpression irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default = convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, (FirExpression) statement, false, null, 6, null);
                return z ? ImplicitConversionUtilsKt.coerceToUnitHandlingSpecialBlocks(this, irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default) : irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default;
            }
            fir2IrVisitor = this;
            if (!(statement instanceof FirDeclaration)) {
                Object objAccept = statement.accept(fir2IrVisitor, null);
                objAccept.getClass();
                IrExpression irExpression = (IrExpression) objAccept;
                return z ? ImplicitConversionUtilsKt.coerceToUnitHandlingSpecialBlocks(fir2IrVisitor, irExpression) : irExpression;
            }
        } else {
            fir2IrVisitor = this;
        }
        KtSourceElement source2 = firBlock.getSource();
        if (!((source2 != null ? source2.getKind() : null) instanceof KtRealSourceElementKind)) {
            FirStatement firStatement = (FirStatement) CollectionsKt.singleOrNull(firBlock.getStatements());
            if ((firStatement instanceof FirExpression) && !(firStatement instanceof FirBlock)) {
                Fir2IrVisitor fir2IrVisitor2 = fir2IrVisitor;
                IrExpression irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default2 = convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(fir2IrVisitor2, (FirExpression) firStatement, false, null, 6, null);
                return z ? ImplicitConversionUtilsKt.coerceToUnitHandlingSpecialBlocks(fir2IrVisitor2, irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default2) : irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default2;
            }
        }
        return fir2IrVisitor.convertToIrBlock(firBlock, irStatementOrigin, coneKotlinType);
    }

    private static final IrExpression convertToIrReceiverExpression$unwrapTypeOperators(IrExpression irExpression) {
        return irExpression instanceof IrTypeOperatorCall ? convertToIrReceiverExpression$unwrapTypeOperators(((IrTypeOperatorCall) irExpression).getArgument()) : irExpression;
    }

    private final IrVarargElement convertToIrVarargElement(FirExpression firExpression) {
        if (!(firExpression instanceof FirSpreadArgumentExpression)) {
            return convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, firExpression, false, null, 6, null);
        }
        FirSpreadArgumentExpression firSpreadArgumentExpression = (FirSpreadArgumentExpression) firExpression;
        KtSourceElement source = firSpreadArgumentExpression.getSource();
        int startOffset = source != null ? source.getStartOffset() : -1;
        KtSourceElement source2 = firSpreadArgumentExpression.getSource();
        return BuildersKt.IrSpreadElementImpl(startOffset, source2 != null ? source2.getEndOffset() : -1, convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, firExpression, false, null, 6, null));
    }

    private final List<IrBranch> convertWhenBranchesTo(FirWhenExpression firWhenExpression, List<IrBranch> list, ConeKotlinType coneKotlinType, boolean z) {
        FirWhenExpression firWhenExpressionNestedElseIfOrNull;
        for (FirWhenBranch firWhenBranch : firWhenExpression.getBranches()) {
            if (z && (firWhenExpressionNestedElseIfOrNull = nestedElseIfOrNull(firWhenBranch)) != null) {
                convertWhenBranchesTo(firWhenExpressionNestedElseIfOrNull, list, coneKotlinType, true);
                return list;
            }
            list.add(toIrWhenBranch(firWhenBranch, coneKotlinType));
        }
        return list;
    }

    public static IrBreakContinue e(Fir2IrVisitor fir2IrVisitor, int i, int i2, IrLoop irLoop, String str) {
        irLoop.getClass();
        IrBreakImpl IrBreakImpl = BuildersKt.IrBreakImpl(i, i2, fir2IrVisitor.getBuiltins().getNothingType(), irLoop);
        IrBreakImpl.setLabel(str);
        return IrBreakImpl;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private final ConeKotlinType expectedReceiverType(FirQualifiedAccessExpression firQualifiedAccessExpression, FirExpression firExpression) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirReceiverParameter receiverParameter;
        FirTypeRef typeRef;
        ConeKotlinType coneType;
        ConeSimpleKotlinType dispatchReceiverType;
        FirCallableSymbol firCallableSymbolUnwrapCallRepresentative$default;
        FirReference calleeReference = firQualifiedAccessExpression.getCalleeReference();
        if (FirReferenceUtilsKt.isError(calleeReference)) {
            return new ConeErrorType(((FirDiagnosticHolder) calleeReference).getDiagnostic(), false, null, null, null, null, null, 126, null);
        }
        FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(calleeReference, false, 1, null);
        FirCallableDeclaration firCallableDeclaration = (resolvedCallableSymbol$default == null || (firCallableSymbolUnwrapCallRepresentative$default = ScopeUtilsKt.unwrapCallRepresentative$default(this, resolvedCallableSymbol$default, null, 2, null)) == null) ? null : (FirCallableDeclaration) firCallableSymbolUnwrapCallRepresentative$default.getFir();
        if (Intrinsics.areEqual(firCallableDeclaration != null ? firCallableDeclaration.getOrigin() : null, FirDeclarationOrigin.DynamicScope.INSTANCE)) {
            return TypeUtilsKt.create$default(ConeDynamicType.Companion, getSession(), null, 2, null);
        }
        if (Intrinsics.areEqual(firExpression, VariousUtilsKt.isConstructorCallOnTypealiasWithInnerRhs(firQualifiedAccessExpression) ? firQualifiedAccessExpression.getExtensionReceiver() : firQualifiedAccessExpression.getDispatchReceiver())) {
            if (firCallableDeclaration == null || (dispatchReceiverType = firCallableDeclaration.getDispatchReceiverType()) == null) {
                return null;
            }
            return ConeTypeUtilsKt.replaceArgumentsWithStarProjectionsOrNull(dispatchReceiverType);
        }
        if (!Intrinsics.areEqual(firExpression, firQualifiedAccessExpression.getExtensionReceiver()) || firCallableDeclaration == null || (receiverParameter = firCallableDeclaration.getReceiverParameter()) == null || (typeRef = receiverParameter.getTypeRef()) == null || (coneType = FirTypeUtilsKt.getConeType(typeRef)) == null) {
            return null;
        }
        ConeKotlinType coneKotlinTypeSubstituteOrSelf = VariousUtilsKt.buildSubstitutorByCalledCallable(this, firQualifiedAccessExpression).substituteOrSelf(coneType);
        ConeKotlinType coneKotlinTypeApproximateToSuperType = TypeComponentsKt.getTypeApproximator(this.c.getSession()).approximateToSuperType(coneKotlinTypeSubstituteOrSelf, TypeApproximatorConfiguration.InternalTypesApproximation.INSTANCE);
        return coneKotlinTypeApproximateToSuperType == null ? coneKotlinTypeSubstituteOrSelf : coneKotlinTypeApproximateToSuperType;
    }

    private final FirFunctionCall extractOperationFromDynamicSetCall(FirFunctionCall functionCall) {
        Object objFirstOrNull = CollectionsKt.firstOrNull(functionCall.getArgumentList().getArguments());
        FirVarargArgumentsExpression firVarargArgumentsExpression = objFirstOrNull instanceof FirVarargArgumentsExpression ? (FirVarargArgumentsExpression) objFirstOrNull : null;
        List<FirExpression> arguments = firVarargArgumentsExpression != null ? firVarargArgumentsExpression.getArguments() : null;
        FirExpression firExpression = arguments != null ? (FirExpression) CollectionsKt.lastOrNull(arguments) : null;
        if (firExpression instanceof FirFunctionCall) {
            return (FirFunctionCall) firExpression;
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:103:0x0189  */
    /* JADX WARN: Code duplicated, block: B:58:0x00f2  */
    /* JADX WARN: Type inference failed for: r11v3, types: [org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    private final IrElement generateThisReceiverAccessForCallable(FirThisReceiverExpression thisReceiverExpression, FirCallableSymbol<?> firCallableSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrSimpleFunction irSimpleFunctionParentAccessorOfDelegatedPropertyFromStack;
        FirScriptResolutionHacksComponent scriptResolutionHacksComponent;
        Object next;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        FirThisReference calleeReference = thisReceiverExpression.getCalleeReference();
        IrStatementOriginImpl implicit_argument = thisReceiverExpression.getIsImplicit() ? IrStatementOrigin.Companion.getIMPLICIT_ARGUMENT() : null;
        IrExpression irExpressionInjectGetValueCall$org_jetbrains_kotlin_fir2ir = getCallGenerator().injectGetValueCall$org_jetbrains_kotlin_fir2ir(thisReceiverExpression, calleeReference);
        if (irExpressionInjectGetValueCall$org_jetbrains_kotlin_fir2ir != null) {
            return irExpressionInjectGetValueCall$org_jetbrains_kotlin_fir2ir;
        }
        if (firCallableSymbol instanceof FirFunctionSymbol) {
            IrFunctionSymbol irFunctionSymbol$default = Fir2IrDeclarationStorage.getIrFunctionSymbol$default(getDeclarationStorage(), (FirFunctionSymbol) firCallableSymbol, null, false, 6, null);
            Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
            if (AbstractTypeChecker.RUN_SLOW_ASSERTIONS) {
                Iterator it = CollectionsKt.asReversedMutable(fir2IrConversionScope.get_parentStack()).iterator();
                while (true) {
                    if (!it.hasNext()) {
                        if (!fir2IrConversionScope.getConfiguration().getAllowNonCachedDeclarations()) {
                            x04.a("Declaration with symbol ", irFunctionSymbol$default, " is not found in parents stack");
                            return null;
                        }
                        IrSimpleFunction owner = irFunctionSymbol$default.getOwner();
                        if (owner != null) {
                            irSimpleFunctionParentAccessorOfDelegatedPropertyFromStack = owner;
                            break;
                        }
                        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.ir.declarations.IrSimpleFunction");
                        return null;
                    }
                    IrDeclaration irDeclaration = (IrDeclarationParent) it.next();
                    IrDeclaration irDeclaration2 = irDeclaration instanceof IrDeclaration ? irDeclaration : null;
                    if (Intrinsics.areEqual(irDeclaration2 != null ? irDeclaration2.getSymbol() : null, irFunctionSymbol$default)) {
                        if (irDeclaration != null) {
                            irSimpleFunctionParentAccessorOfDelegatedPropertyFromStack = (IrSimpleFunction) irDeclaration;
                            break;
                        }
                        x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.ir.declarations.IrSimpleFunction");
                        return null;
                    }
                }
            } else {
                IrSymbolOwner owner2 = irFunctionSymbol$default.getOwner();
                if (owner2 == null) {
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.ir.declarations.IrSimpleFunction");
                    return null;
                }
                irSimpleFunctionParentAccessorOfDelegatedPropertyFromStack = (IrSimpleFunction) owner2;
            }
        } else if (firCallableSymbol instanceof FirPropertySymbol) {
            FirPropertySymbol firPropertySymbol = (FirPropertySymbol) firCallableSymbol;
            IrPropertySymbol irPropertySymbol$default = Fir2IrDeclarationStorage.getIrPropertySymbol$default(getDeclarationStorage(), firPropertySymbol, null, 2, null);
            if (irPropertySymbol$default instanceof IrPropertySymbol) {
                IrPropertySymbol irPropertySymbol = irPropertySymbol$default;
                IrSimpleFunction irSimpleFunctionParentAccessorOfPropertyFromStack = this.conversionScope.parentAccessorOfPropertyFromStack(irPropertySymbol);
                if (irSimpleFunctionParentAccessorOfPropertyFromStack == null) {
                    if (!Intrinsics.areEqual(DeclarationAttributesKt.isScriptTopLevelDeclaration(firPropertySymbol.getFir()), Boolean.TRUE) && (scriptResolutionHacksComponent = FirScriptResolutionHacksComponentKt.getScriptResolutionHacksComponent(getSession())) != null && scriptResolutionHacksComponent.getSkipTowerDataCleanupForTopLevelInitializers()) {
                        b88.a("Accessor of property ", RenderIrElementKt.render$default(irPropertySymbol.getOwner(), (DumpIrTreeOptions) null, 1, (Object) null), " not found on parent stack");
                        return null;
                    }
                    irSimpleFunctionParentAccessorOfDelegatedPropertyFromStack = null;
                } else {
                    irSimpleFunctionParentAccessorOfDelegatedPropertyFromStack = irSimpleFunctionParentAccessorOfPropertyFromStack;
                }
            } else if (irPropertySymbol$default instanceof IrLocalDelegatedPropertySymbol) {
                irSimpleFunctionParentAccessorOfDelegatedPropertyFromStack = this.conversionScope.parentAccessorOfDelegatedPropertyFromStack((IrLocalDelegatedPropertySymbol) irPropertySymbol$default);
            } else {
                irSimpleFunctionParentAccessorOfDelegatedPropertyFromStack = null;
            }
        } else {
            irSimpleFunctionParentAccessorOfDelegatedPropertyFromStack = null;
        }
        if (irSimpleFunctionParentAccessorOfDelegatedPropertyFromStack == null) {
            return null;
        }
        Iterator it2 = irSimpleFunctionParentAccessorOfDelegatedPropertyFromStack.getParameters().iterator();
        do {
            if (!it2.hasNext()) {
                next = null;
                break;
            }
            next = it2.next();
        } while (((IrValueParameter) next).getKind() != IrParameterKind.ExtensionReceiver);
        IrValueParameter irValueParameter = (IrValueParameter) next;
        if (irValueParameter == null) {
            return null;
        }
        KtSourceElement source = thisReceiverExpression.getSource();
        int i = -1;
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        endOffset = source != null ? source.getEndOffset() : -1;
                        i = startOffset;
                    }
                }
            }
        }
        return BuildersKt.IrGetValueImpl(i, endOffset, irValueParameter.getType(), irValueParameter.getSymbol(), implicit_argument);
    }

    /* JADX WARN: Code duplicated, block: B:107:0x0184  */
    /* JADX WARN: Code duplicated, block: B:41:0x00a1  */
    /* JADX WARN: Multi-variable type inference failed */
    private final IrElement generateThisReceiverAccessForClass(FirThisReceiverExpression thisReceiverExpression, FirClassSymbol<?> firClassSymbol) {
        IrClass irClass;
        int endOffset;
        IrGetValueImpl irGetValueImplUseInjectedValue$org_jetbrains_kotlin_fir2ir;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        int endOffset2;
        int startOffset2;
        Integer numStartOffsetSkippingComments2;
        FirThisReference calleeReference = thisReceiverExpression.getCalleeReference();
        FirClass firClass = (FirClass) firClassSymbol.getFir();
        IrClassSymbol symbol = (firClass.getOrigin().getFromSource() || firClass.getOrigin().getGenerated()) ? getClassifierStorage().getIrClass(firClass).getSymbol() : getClassifierStorage().getIrClassSymbol(firClassSymbol);
        int i = -1;
        if (firClass.getClassKind() == ClassKind.OBJECT && shouldGenerateReceiverAsSingletonReference(symbol)) {
            KtSourceElement source = thisReceiverExpression.getSource();
            if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
                endOffset2 = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                    endOffset2 = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                        endOffset2 = -1;
                    } else {
                        if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                            endOffset2 = -1;
                        } else {
                            if (source == null || (numStartOffsetSkippingComments2 = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                                startOffset2 = source != null ? source.getStartOffset() : -1;
                            } else {
                                startOffset2 = numStartOffsetSkippingComments2.intValue();
                            }
                            endOffset2 = source != null ? source.getEndOffset() : -1;
                            i = startOffset2;
                        }
                    }
                }
            }
            return BuildersKt.IrGetObjectValueImpl(i, endOffset2, Fir2IrTypeConverterKt.toIrType$default(this, org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt.defaultType(firClassSymbol), (ConversionTypeOrigin) null, 2, (Object) null), symbol);
        }
        Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
        if (AbstractTypeChecker.RUN_SLOW_ASSERTIONS) {
            Iterator it = CollectionsKt.asReversedMutable(fir2IrConversionScope.get_parentStack()).iterator();
            while (true) {
                if (!it.hasNext()) {
                    if (!fir2IrConversionScope.getConfiguration().getAllowNonCachedDeclarations()) {
                        x04.a("Declaration with symbol ", symbol, " is not found in parents stack");
                        return null;
                    }
                    IrClass owner = symbol.getOwner();
                    if (owner != null) {
                        irClass = owner;
                        break;
                    }
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.ir.declarations.IrClass");
                    return null;
                }
                IrDeclaration irDeclaration = (IrDeclarationParent) it.next();
                IrDeclaration irDeclaration2 = irDeclaration instanceof IrDeclaration ? irDeclaration : null;
                if (Intrinsics.areEqual(irDeclaration2 != null ? irDeclaration2.getSymbol() : null, symbol)) {
                    if (irDeclaration != null) {
                        irClass = (IrClass) irDeclaration;
                        break;
                    }
                    x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.ir.declarations.IrClass");
                    return null;
                }
            }
        } else {
            IrSymbolOwner owner2 = symbol.getOwner();
            if (owner2 == null) {
                x0e.a("null cannot be cast to non-null type org.jetbrains.kotlin.ir.declarations.IrClass");
                return null;
            }
            irClass = (IrClass) owner2;
        }
        IrValueParameter irValueParameterDispatchReceiverParameter = this.conversionScope.dispatchReceiverParameter(irClass);
        if (irValueParameterDispatchReceiverParameter == null) {
            return null;
        }
        IrStatementOriginImpl implicit_argument = thisReceiverExpression.getIsImplicit() ? IrStatementOrigin.Companion.getIMPLICIT_ARGUMENT() : null;
        KtSourceElement source2 = thisReceiverExpression.getSource();
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source2))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source2 == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source2, null)) == null) {
                            startOffset = source2 != null ? source2.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        endOffset = source2 != null ? source2.getEndOffset() : -1;
                        i = startOffset;
                    }
                }
            }
        }
        InjectedValue injectedValueFindInjectedValue$org_jetbrains_kotlin_fir2ir = getCallGenerator().findInjectedValue$org_jetbrains_kotlin_fir2ir(calleeReference);
        return (injectedValueFindInjectedValue$org_jetbrains_kotlin_fir2ir == null || (irGetValueImplUseInjectedValue$org_jetbrains_kotlin_fir2ir = getCallGenerator().useInjectedValue$org_jetbrains_kotlin_fir2ir(injectedValueFindInjectedValue$org_jetbrains_kotlin_fir2ir, calleeReference, i, endOffset)) == null) ? BuildersKt.IrGetValueImpl(i, endOffset, irValueParameterDispatchReceiverParameter.getType(), irValueParameterDispatchReceiverParameter.getSymbol(), implicit_argument) : irGetValueImplUseInjectedValue$org_jetbrains_kotlin_fir2ir;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:49:0x00be  */
    private final IrElement generateThisReceiverAccessForReplSnippet(FirThisReceiverExpression thisReceiverExpression, FirReplSnippetSymbol firSnippetSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        Object next;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        FirThisReference calleeReference = thisReceiverExpression.getCalleeReference();
        FirReplSnippet fir = firSnippetSymbol.getFir();
        IrStatementOriginImpl implicit_argument = thisReceiverExpression.getIsImplicit() ? IrStatementOrigin.Companion.getIMPLICIT_ARGUMENT() : null;
        IrReplSnippet cachedIrReplSnippet = getDeclarationStorage().getCachedIrReplSnippet(fir);
        if (cachedIrReplSnippet == null) {
            b88.a("IrReplSnippet for ", fir.getSnippetClass().getName(), " not found");
            return null;
        }
        List<FirScriptReceiverParameter> receivers = firSnippetSymbol.getFir().getReceivers();
        FirThisOwnerSymbol<?> boundSymbol = calleeReference.getBoundSymbol();
        int iIndexOf = CollectionsKt.indexOf(receivers, boundSymbol != null ? boundSymbol.getFir() : null);
        Iterator it = cachedIrReplSnippet.getReceiverParameters().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (((IrValueParameter) next).getIndexInParameters() != iIndexOf);
        IrValueParameter irValueParameter = (IrValueParameter) next;
        if (irValueParameter == null) {
            k2d.a("Unexpected REPL snippet receiver");
            return null;
        }
        KtSourceElement source = thisReceiverExpression.getSource();
        int i = -1;
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        endOffset = source != null ? source.getEndOffset() : -1;
                        i = startOffset;
                    }
                }
            }
        }
        return BuildersKt.IrGetValueImpl(i, endOffset, irValueParameter.getType(), irValueParameter.getSymbol(), implicit_argument);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:51:0x00c4  */
    private final IrElement generateThisReceiverAccessForScript(FirThisReceiverExpression thisReceiverExpression, FirScriptSymbol firScriptSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        Object next;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        FirThisReference calleeReference = thisReceiverExpression.getCalleeReference();
        FirScript fir = firScriptSymbol.getFir();
        IrStatementOriginImpl implicit_argument = thisReceiverExpression.getIsImplicit() ? IrStatementOrigin.Companion.getIMPLICIT_ARGUMENT() : null;
        IrScript cachedIrScript = getDeclarationStorage().getCachedIrScript(fir);
        if (cachedIrScript == null) {
            b88.a("IrScript for ", fir.getName(), " not found");
            return null;
        }
        List<FirScriptReceiverParameter> receivers = firScriptSymbol.getFir().getReceivers();
        FirThisOwnerSymbol<?> boundSymbol = calleeReference.getBoundSymbol();
        int iIndexOf = CollectionsKt.indexOf(receivers, boundSymbol != null ? boundSymbol.getFir() : null);
        Iterator it = cachedIrScript.getImplicitReceiversParameters().iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
        } while (((IrValueParameter) next).getIndexInParameters() != iIndexOf);
        IrValueParameter thisReceiver = (IrValueParameter) next;
        if (thisReceiver == null) {
            thisReceiver = cachedIrScript.getThisReceiver();
        }
        if (thisReceiver == null) {
            k2d.a("No script receiver found");
            return null;
        }
        KtSourceElement source = thisReceiverExpression.getSource();
        int i = -1;
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        endOffset = source != null ? source.getEndOffset() : -1;
                        i = startOffset;
                    }
                }
            }
        }
        return BuildersKt.IrGetValueImpl(i, endOffset, thisReceiver.getType(), thisReceiver.getSymbol(), implicit_argument);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final IrExpression generateWhen(int startOffset, int endOffset, IrStatementOrigin origin, IrVariable subjectVariable, List<? extends IrBranch> branches, IrType resultType) {
        IrWhenImpl IrWhenImpl = BuildersKt.IrWhenImpl(startOffset, endOffset, resultType, !Intrinsics.areEqual(origin, IrStatementOrigin.Companion.getELVIS()) ? origin : null, branches);
        return subjectVariable == null ? IrWhenImpl : BuildersKt.IrBlockImpl(startOffset, endOffset, IrWhenImpl.getType(), origin, CollectionsKt.listOf(new IrElement[]{subjectVariable, IrWhenImpl}));
    }

    private final IrVariable generateWhenSubjectVariable(FirWhenExpression whenExpression) {
        FirVariable subjectVariable = whenExpression.getSubjectVariable();
        FirExpression initializer = subjectVariable != null ? subjectVariable.getInitializer() : null;
        if (subjectVariable != null && !FirExpressionUtilKt.isImplicitWhenSubjectVariable(subjectVariable)) {
            Object objAccept = subjectVariable.accept(this, null);
            objAccept.getClass();
            return (IrVariable) objAccept;
        }
        if (initializer == null) {
            return null;
        }
        FirExpression firExpression = initializer;
        return Scope.createTemporaryVariable$default(this.conversionScope.scope(), getImplicitCastInserter().insertCastForIntersectionTypeOrSelf$org_jetbrains_kotlin_fir2ir(convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, firExpression, false, null, 6, null), FirTypeUtilsKt.getResolvedType(firExpression), FirTypeUtilsKt.getConeType(subjectVariable.getReturnTypeRef())), "subject", false, (IrDeclarationOrigin) null, (IrType) null, 0, 0, false, 252, (Object) null);
    }

    private final boolean isDeeplyProperlyExhaustive(FirWhenExpression firWhenExpression) {
        FirWhenExpression firWhenExpressionNestedElseIfOrNull;
        if (!ExhaustivenessStatusKt.isProperlyExhaustive(firWhenExpression)) {
            return false;
        }
        FirWhenBranch firWhenBranch = (FirWhenBranch) CollectionsKt.lastOrNull(firWhenExpression.getBranches());
        if (firWhenBranch == null || (firWhenExpressionNestedElseIfOrNull = nestedElseIfOrNull(firWhenBranch)) == null) {
            return true;
        }
        return isDeeplyProperlyExhaustive(firWhenExpressionNestedElseIfOrNull);
    }

    private final boolean isUnnamedLocalVariable(FirProperty firProperty) {
        FirExpression initializer = firProperty.getInitializer();
        if (initializer == null) {
            return false;
        }
        KtSourceElement source = initializer.getSource();
        return !((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind.DesugaredComponentFunctionCall);
    }

    private final FirWhenExpression nestedElseIfOrNull(FirWhenBranch firWhenBranch) {
        if (firWhenBranch.getCondition() instanceof FirElseIfTrueCondition) {
            FirBlock result = firWhenBranch.getResult();
            FirSingleExpressionBlock firSingleExpressionBlock = result instanceof FirSingleExpressionBlock ? (FirSingleExpressionBlock) result : null;
            FirStatement statement = firSingleExpressionBlock != null ? firSingleExpressionBlock.getStatement() : null;
            FirWhenExpression firWhenExpression = statement instanceof FirWhenExpression ? (FirWhenExpression) statement : null;
            if (firWhenExpression != null) {
                KtSourceElement source = firWhenExpression.getSource();
                if (Intrinsics.areEqual(source != null ? source.getElementType() : null, KtNodeTypes.IF)) {
                    return firWhenExpression;
                }
            }
        }
        return null;
    }

    private final boolean shouldGenerateReceiverAsSingletonReference(IrClassSymbol irClassSymbol) {
        IrDeclaration irDeclarationParent = this.conversionScope.parent();
        IrDeclaration irDeclaration = irDeclarationParent instanceof IrDeclaration ? irDeclarationParent : null;
        if (Intrinsics.areEqual(irDeclaration != null ? irDeclaration.getSymbol() : null, irClassSymbol)) {
            return false;
        }
        if (!(irDeclarationParent instanceof IrFunction) && !(irDeclarationParent instanceof IrProperty) && !(irDeclarationParent instanceof IrField)) {
            return true;
        }
        irDeclarationParent.getClass();
        IrDeclaration parent = irDeclarationParent.getParent();
        IrDeclaration irDeclaration2 = parent instanceof IrDeclaration ? parent : null;
        return !Intrinsics.areEqual(irDeclaration2 != null ? irDeclaration2.getSymbol() : null, irClassSymbol);
    }

    private final IrClassSymbol toIrClassSymbol(ConeClassLikeType coneClassLikeType) {
        ConeClassLikeLookupTag lookupTag;
        FirClassSymbol<?> classSymbol;
        if (coneClassLikeType == null || (lookupTag = coneClassLikeType.getLookupTag()) == null || (classSymbol = ToSymbolUtilsKt.toClassSymbol(this, lookupTag)) == null) {
            return null;
        }
        return getClassifierStorage().getIrClassSymbol(classSymbol);
    }

    private final IrDeclaration toIrDeclaration(FirDeclaration firDeclaration) {
        Object objAccept = firDeclaration.accept(this, null);
        objAccept.getClass();
        return (IrDeclaration) objAccept;
    }

    private final IrDynamicOperator toIrDynamicOperator(LogicOperationKind logicOperationKind) {
        int i = WhenMappings.$EnumSwitchMapping$1[logicOperationKind.ordinal()];
        if (i == 1) {
            return IrDynamicOperator.ANDAND;
        }
        if (i == 2) {
            return IrDynamicOperator.OROR;
        }
        bu8.a();
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final IrStatement toIrStatement(FirStatement firStatement) throws KotlinIllegalArgumentExceptionWithAttachments {
        if (firStatement instanceof FirTypeAlias) {
            return null;
        }
        if (firStatement instanceof FirUnitExpression) {
            KtSourceElement source = ((FirUnitExpression) firStatement).getSource();
            if ((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind.ImplicitUnit.IndexedAssignmentCoercion) {
                return null;
            }
            return convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, (FirExpression) firStatement, false, null, 6, null);
        }
        if (firStatement instanceof FirContractCallBlock) {
            return null;
        }
        if (firStatement instanceof FirBlock) {
            return convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, (FirExpression) firStatement, false, null, 6, null);
        }
        if (firStatement instanceof FirProperty) {
            FirProperty firProperty = (FirProperty) firStatement;
            if (Intrinsics.areEqual(firProperty.getName(), SpecialNames.UNDERSCORE_FOR_UNUSED_VAR)) {
                if (!isUnnamedLocalVariable(firProperty)) {
                    return null;
                }
                FirExpression initializer = firProperty.getInitializer();
                IrElement irElement = initializer != null ? (IrElement) initializer.accept(this, null) : null;
                irElement.getClass();
                return (IrStatement) irElement;
            }
        }
        if (firStatement instanceof FirReplPropertyInitializer) {
            FirReplPropertyInitializer firReplPropertyInitializer = (FirReplPropertyInitializer) firStatement;
            return getCallGenerator().convertToIrSetCall(firReplPropertyInitializer.getInitializer(), firReplPropertyInitializer.getPropertySymbol());
        }
        if (firStatement instanceof FirReplPropertyDelegate) {
            FirReplPropertyDelegate firReplPropertyDelegate = (FirReplPropertyDelegate) firStatement;
            return getCallGenerator().convertToIrSetCall(firReplPropertyDelegate.getDelegate(), firReplPropertyDelegate.getPropertySymbol());
        }
        if (firStatement instanceof FirReplDeclarationReference) {
            return null;
        }
        Object objAccept = firStatement.accept(this, null);
        objAccept.getClass();
        return (IrStatement) objAccept;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:30:0x0060  */
    private final IrBranch toIrWhenBranch(FirWhenBranch firWhenBranch, ConeKotlinType coneKotlinType) throws KotlinIllegalArgumentExceptionWithAttachments {
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        KtSourceElement source = firWhenBranch.getSource();
        int i = -1;
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        i = startOffset;
                        endOffset = source != null ? source.getEndOffset() : -1;
                    }
                }
            }
        }
        FirExpression condition = firWhenBranch.getCondition();
        IrExpression irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default = convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, firWhenBranch.getResult(), false, coneKotlinType, 2, null);
        if (condition instanceof FirElseIfTrueCondition) {
            return BuildersKt.IrElseBranchImpl(IrConstImpl.Companion.boolean(irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default.getStartOffset(), irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default.getEndOffset(), getBuiltins().getBooleanType(), true), irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default);
        }
        if (irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default.getEndOffset() >= 0) {
            endOffset = irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default.getEndOffset();
        }
        return BuildersKt.IrBranchImpl(i, endOffset, convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, condition, false, null, 6, null), irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private final IrExpression tryConvertDynamicIncrementOrDecrementToIr(FirBlock firBlock) throws KotlinIllegalArgumentExceptionWithAttachments {
        Object next;
        FirStatement initializer;
        FirFunctionCall firFunctionCallExtractOperationFromDynamicSetCall;
        Object next2;
        FirExpression explicitReceiver;
        Object next3;
        FirVarargArgumentsExpression firVarargArgumentsExpression;
        List<FirExpression> arguments;
        FirExpression initializer2;
        FirNamedReference calleeReference;
        FirPropertySymbol resolvedPropertySymbol$default;
        Set<FirExpression> setKeySet;
        Fir2IrVisitor fir2IrVisitor = this;
        Iterator<T> it = firBlock.getStatements().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if ((next instanceof FirProperty) && Intrinsics.areEqual(((FirProperty) next).getName(), SpecialNames.UNARY)) {
                break;
            }
        }
        FirProperty firProperty = (FirProperty) next;
        if (firProperty == null || (initializer = firProperty.getInitializer()) == null) {
            initializer = (FirStatement) CollectionsKt.lastOrNull(firBlock.getStatements());
        }
        FirStatement firStatementUnwrapDesugaredAssignmentValueReference = initializer != null ? fir2IrVisitor.unwrapDesugaredAssignmentValueReference(initializer) : null;
        FirQualifiedAccessExpression firQualifiedAccessExpression = firStatementUnwrapDesugaredAssignmentValueReference instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) firStatementUnwrapDesugaredAssignmentValueReference : null;
        if (firQualifiedAccessExpression == null || !(FirTypeUtilsKt.getResolvedType(firQualifiedAccessExpression) instanceof ConeDynamicType)) {
            return null;
        }
        int i = 1;
        FirStatement firStatement = firBlock.getStatements().get(CollectionsKt.getLastIndex(firBlock.getStatements()) - 1);
        if (firStatement instanceof FirVariableAssignment) {
            FirExpression rValue = ((FirVariableAssignment) firStatement).getRValue();
            firFunctionCallExtractOperationFromDynamicSetCall = rValue instanceof FirFunctionCall ? (FirFunctionCall) rValue : null;
            if (firFunctionCallExtractOperationFromDynamicSetCall == null) {
                return null;
            }
        } else if (!(firStatement instanceof FirFunctionCall) || (firFunctionCallExtractOperationFromDynamicSetCall = fir2IrVisitor.extractOperationFromDynamicSetCall((FirFunctionCall) firStatement)) == null) {
            return null;
        }
        FirFunctionCall firFunctionCall = firFunctionCallExtractOperationFromDynamicSetCall;
        Iterator<T> it2 = firBlock.getStatements().iterator();
        while (true) {
            if (!it2.hasNext()) {
                next2 = null;
                break;
            }
            next2 = it2.next();
            if (next2 instanceof FirProperty) {
                FirProperty firProperty2 = (FirProperty) next2;
                if (Intrinsics.areEqual(firProperty2.getName(), SpecialNames.RECEIVER) || Intrinsics.areEqual(firProperty2.getName(), SpecialNames.ARRAY)) {
                    break;
                }
            }
        }
        FirProperty firProperty3 = (FirProperty) next2;
        if (firProperty3 == null || (explicitReceiver = firProperty3.getInitializer()) == null) {
            explicitReceiver = firQualifiedAccessExpression.getExplicitReceiver();
        }
        Iterator<T> it3 = firBlock.getStatements().iterator();
        while (true) {
            if (!it3.hasNext()) {
                next3 = null;
                break;
            }
            next3 = it3.next();
            FirStatement firStatement2 = (FirStatement) next3;
            if ((firStatement2 instanceof FirProperty) && Intrinsics.areEqual(((FirProperty) firStatement2).getName(), SpecialNames.ARRAY)) {
                break;
            }
        }
        boolean z = false;
        boolean z2 = next3 != null;
        IrDynamicOperatorExpression irDynamicOperatorExpressionConvertToIrCall$default = CallAndReferenceGenerator.convertToIrCall$default(fir2IrVisitor.getCallGenerator(), firQualifiedAccessExpression, FirTypeUtilsKt.getResolvedType(firQualifiedAccessExpression), fir2IrVisitor.convertToIrReceiverExpression$org_jetbrains_kotlin_fir2ir(explicitReceiver, firQualifiedAccessExpression), null, false, z2, 24, null);
        if (z2) {
            if (!(irDynamicOperatorExpressionConvertToIrCall$default instanceof IrDynamicOperatorExpression)) {
                w01.a("Failed requirement.");
                return null;
            }
            FirFunctionCall firFunctionCall2 = firQualifiedAccessExpression instanceof FirFunctionCall ? (FirFunctionCall) firQualifiedAccessExpression : null;
            if (firFunctionCall2 == null) {
                return null;
            }
            FirArgumentList argumentList = firFunctionCall2.getArgumentList();
            LinkedHashMap<FirExpression, FirValueParameter> mapping = argumentList instanceof FirResolvedArgumentList ? ((FirResolvedArgumentList) argumentList).getMapping() : null;
            if (mapping == null || (setKeySet = mapping.keySet()) == null) {
                firVarargArgumentsExpression = null;
            } else {
                ArrayList arrayList = new ArrayList();
                for (Object obj : setKeySet) {
                    if (obj instanceof FirVarargArgumentsExpression) {
                        arrayList.add(obj);
                    }
                }
                firVarargArgumentsExpression = (FirVarargArgumentsExpression) CollectionsKt.firstOrNull(arrayList);
            }
            if (firVarargArgumentsExpression != null && (arguments = firVarargArgumentsExpression.getArguments()) != null) {
                for (FirExpression firExpression : arguments) {
                    FirPropertyAccessExpression firPropertyAccessExpression = firExpression instanceof FirPropertyAccessExpression ? (FirPropertyAccessExpression) firExpression : null;
                    FirProperty firProperty4 = (firPropertyAccessExpression == null || (calleeReference = firPropertyAccessExpression.getCalleeReference()) == null || (resolvedPropertySymbol$default = FirReferenceUtilsKt.toResolvedPropertySymbol$default(calleeReference, z, i, null)) == null) ? null : (FirProperty) resolvedPropertySymbol$default.getFir();
                    if (firProperty4 != null && (initializer2 = firProperty4.getInitializer()) != null) {
                        irDynamicOperatorExpressionConvertToIrCall$default.getArguments().add(convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(fir2IrVisitor, initializer2, false, null, 6, null));
                    }
                    fir2IrVisitor = this;
                    z = z;
                    i = i;
                }
            }
        }
        return CallAndReferenceGenerator.convertToIrCall$default(getCallGenerator(), firFunctionCall, FirTypeUtilsKt.getResolvedType(firFunctionCall), irDynamicOperatorExpressionConvertToIrCall$default, null, false, false, 56, null);
    }

    private final FirStatement unwrapDesugaredAssignmentValueReference(FirStatement firStatement) {
        FirExpressionRef<FirExpression> expressionRef;
        FirExpression value;
        FirDesugaredAssignmentValueReferenceExpression firDesugaredAssignmentValueReferenceExpression = firStatement instanceof FirDesugaredAssignmentValueReferenceExpression ? (FirDesugaredAssignmentValueReferenceExpression) firStatement : null;
        return (firDesugaredAssignmentValueReferenceExpression == null || (expressionRef = firDesugaredAssignmentValueReferenceExpression.getExpressionRef()) == null || (value = expressionRef.getValue()) == null) ? firStatement : value;
    }

    private static final IrGetValue visitElvisExpression$lambda$0$irGetLhsValue(int i, int i2, IrVariable irVariable) {
        return BuildersKt.IrGetValueImpl$default(i, i2, irVariable.getType(), irVariable.getSymbol(), (IrStatementOrigin) null, 16, (Object) null);
    }

    private final IrElement visitLocalVariable(FirProperty variable) {
        IrDeclarationOrigin ir_temporary_variable;
        FirNamedFunctionSymbol resolvedNamedFunctionSymbol$default;
        CallableId callableId;
        KtSourceElement source;
        FirSession session = getSession();
        try {
            variable.getSymbol();
            FirExpression delegate = variable.getDelegate();
            int i = 1;
            if (delegate == null) {
                FirExpression initializer = variable.getInitializer();
                if (!(initializer instanceof FirFunctionCall) || (resolvedNamedFunctionSymbol$default = FirReferenceUtilsKt.toResolvedNamedFunctionSymbol$default(((FirFunctionCall) initializer).getCalleeReference(), false, 1, null)) == null || (callableId = resolvedNamedFunctionSymbol$default.getCallableId()) == null || !CallableIdUtilsKt.isIteratorNext(callableId) || (source = variable.getSource()) == null || !Fir2IrVisitorKt.isChildOfForLoop(source)) {
                    i = 0;
                }
                Fir2IrDeclarationStorage declarationStorage = getDeclarationStorage();
                IrDeclarationParent irDeclarationParentParentFromStack = this.conversionScope.parentFromStack();
                if (i != 0) {
                    ir_temporary_variable = (variable.getName().isSpecial() && Intrinsics.areEqual(variable.getName(), SpecialNames.DESTRUCT)) ? IrDeclarationOrigin.Companion.getIR_TEMPORARY_VARIABLE() : IrDeclarationOrigin.Companion.getFOR_LOOP_VARIABLE();
                } else {
                    ir_temporary_variable = null;
                }
                IrVariable irVariableCreateAndCacheIrVariable = declarationStorage.createAndCacheIrVariable(variable, irDeclarationParentParentFromStack, ir_temporary_variable);
                if (initializer != null) {
                    IrExpression irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default = convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, initializer, false, FirTypeUtilsKt.getConeType(variable.getReturnTypeRef()), 2, null);
                    if (Intrinsics.areEqual(irVariableCreateAndCacheIrVariable.getName(), SpecialNames.WHEN_SUBJECT)) {
                        irVariableCreateAndCacheIrVariable.setType(irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default.getType());
                    }
                    irVariableCreateAndCacheIrVariable.setInitializer(irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default);
                }
                getAnnotationGenerator().generate((IrMutableAnnotationContainer) irVariableCreateAndCacheIrVariable, (FirAnnotationContainer) variable);
                return irVariableCreateAndCacheIrVariable;
            }
            IrLocalDelegatedProperty irLocalDelegatedPropertyCreateAndCacheIrLocalDelegatedProperty = getDeclarationStorage().createAndCacheIrLocalDelegatedProperty(variable, this.conversionScope.parentFromStack());
            IrVariable delegate2 = irLocalDelegatedPropertyCreateAndCacheIrLocalDelegatedProperty.getDelegate();
            if (delegate2 == null) {
                throw new IllegalArgumentException(("Local delegated property " + RenderIrElementKt.render$default(irLocalDelegatedPropertyCreateAndCacheIrLocalDelegatedProperty, (DumpIrTreeOptions) null, 1, (Object) null) + " has no delegate").toString());
            }
            delegate2.setInitializer(convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, delegate, true, null, 4, null));
            Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
            fir2IrConversionScope.getFunctionStack().add(irLocalDelegatedPropertyCreateAndCacheIrLocalDelegatedProperty.getGetter());
            try {
                this.memberGenerator.convertFunctionContent(irLocalDelegatedPropertyCreateAndCacheIrLocalDelegatedProperty.getGetter(), variable.getGetter(), null);
                fir2IrConversionScope.getFunctionStack().remove(fir2IrConversionScope.getFunctionStack().size() - 1);
                IrFunction setter = irLocalDelegatedPropertyCreateAndCacheIrLocalDelegatedProperty.getSetter();
                if (setter == null) {
                    return irLocalDelegatedPropertyCreateAndCacheIrLocalDelegatedProperty;
                }
                Fir2IrConversionScope fir2IrConversionScope2 = this.conversionScope;
                fir2IrConversionScope2.getFunctionStack().add(setter);
                try {
                    this.memberGenerator.convertFunctionContent(setter, variable.getSetter(), null);
                    return irLocalDelegatedPropertyCreateAndCacheIrLocalDelegatedProperty;
                } finally {
                    fir2IrConversionScope2.getFunctionStack().remove(fir2IrConversionScope2.getFunctionStack().size() - 1);
                }
            } catch (Throwable th) {
                fir2IrConversionScope.getFunctionStack().remove(fir2IrConversionScope.getFunctionStack().size() - 1);
                throw th;
            }
        } catch (Throwable th2) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(variable, th2);
            wq6.a();
            return null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final IrScriptSymbol visitScript$lambda$0$3$0(Fir2IrVisitor fir2IrVisitor, FirScriptSymbol firScriptSymbol) {
        firScriptSymbol.getClass();
        IrScript cachedIrScript = fir2IrVisitor.getDeclarationStorage().getCachedIrScript(firScriptSymbol.getFir());
        if (cachedIrScript != null) {
            return cachedIrScript.getSymbol();
        }
        return null;
    }

    public static /* synthetic */ Object withAnnotationMode$org_jetbrains_kotlin_fir2ir$default(Fir2IrVisitor fir2IrVisitor, boolean z, Function0 function0, int i, Object obj) {
        if ((i & 1) != 0) {
            z = true;
        }
        function0.getClass();
        boolean z2 = fir2IrVisitor._annotationMode;
        fir2IrVisitor._annotationMode = z;
        try {
            return function0.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            fir2IrVisitor._annotationMode = z2;
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:30:0x0063  */
    public final IrBlockBody convertToIrBlockBody$org_jetbrains_kotlin_fir2ir(FirBlock block) throws KotlinIllegalArgumentExceptionWithAttachments {
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        block.getClass();
        KtSourceElement source = block.getSource();
        int i = -1;
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        i = startOffset;
                        endOffset = source != null ? source.getEndOffset() : -1;
                    }
                }
            }
        }
        IrFactoryImpl irFactoryImpl = IrFactoryImpl.INSTANCE;
        List<FirStatement> statements = block.getStatements();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = statements.iterator();
        while (it.hasNext()) {
            IrStatement irStatement = toIrStatement((FirStatement) it.next());
            if (irStatement != null) {
                arrayList.add(irStatement);
            }
        }
        IrBlockBody irBlockBodyCreateBlockBody = IrFactoryHelpersKt.createBlockBody(irFactoryImpl, i, endOffset, arrayList);
        ImplicitConversionUtilsKt.coerceStatementsToUnit(this, irBlockBodyCreateBlockBody, true);
        return irBlockBodyCreateBlockBody;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final IrExpression convertToIrExpression$org_jetbrains_kotlin_fir2ir(FirExpression expression, boolean isDelegate, ConeKotlinType expectedType) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrExpression irExpressionConvertToArrayLiteral;
        IrStatementOriginImpl for_loop;
        expression.getClass();
        ConeKotlinType coneKotlinType = null;
        if (expression instanceof FirBlock) {
            FirBlock firBlock = (FirBlock) expression;
            KtSourceElement source = firBlock.getSource();
            KtSourceElementKind kind = source != null ? source.getKind() : null;
            if (kind instanceof KtFakeSourceElementKind.DesugaredForLoop) {
                for_loop = IrStatementOrigin.Companion.getFOR_LOOP();
            } else if (kind instanceof KtFakeSourceElementKind.DesugaredAugmentedAssign) {
                Map<KtFakeSourceElementKind.DesugaredAugmentedAssign, IrStatementOrigin> augmentedAssignSourceKindToIrStatementOrigin = OriginUtilsKt.getAugmentedAssignSourceKindToIrStatementOrigin();
                KtSourceElement source2 = firBlock.getSource();
                for_loop = (IrStatementOrigin) augmentedAssignSourceKindToIrStatementOrigin.get(source2 != null ? source2.getKind() : null);
            } else if (kind instanceof KtFakeSourceElementKind.DesugaredIncrementOrDecrement) {
                Map<KtFakeSourceElementKind, IrStatementOrigin> incOrDecSourceKindToIrStatementOrigin = OriginUtilsKt.getIncOrDecSourceKindToIrStatementOrigin();
                KtSourceElement source3 = firBlock.getSource();
                for_loop = (IrStatementOrigin) incOrDecSourceKindToIrStatementOrigin.get(source3 != null ? source3.getKind() : null);
            } else {
                for_loop = null;
            }
            if (Intrinsics.areEqual(for_loop, IrStatementOrigin.Companion.getFOR_LOOP()) || (expectedType != null && ConeBuiltinTypeUtilsKt.isUnit(expectedType))) {
                coneKotlinType = this.unitType;
            } else if (expectedType != null && ConeBuiltinTypeUtilsKt.isNothing(expectedType)) {
                coneKotlinType = expectedType;
            }
            irExpressionConvertToArrayLiteral = convertToIrExpressionOrBlock(firBlock, for_loop, coneKotlinType);
        } else if (expression instanceof FirUnitExpression) {
            KtSourceElement source4 = expression.getSource();
            int endOffset = -1;
            if (!OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source4))) {
                if (!Intrinsics.areEqual(source4 != null ? source4.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                    if (!Intrinsics.areEqual(source4 != null ? source4.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                        if (!Intrinsics.areEqual(source4 != null ? source4.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                            if ((source4 == null || OffsetUtilsKt.startOffsetSkippingComments(source4, null) == null) && source4 != null) {
                                source4.getStartOffset();
                            }
                            if (source4 != null) {
                                endOffset = source4.getEndOffset();
                            }
                        }
                    }
                }
            }
            irExpressionConvertToArrayLiteral = BuildersKt.IrGetObjectValueImpl(endOffset, endOffset, getBuiltins().getUnitType(), getBuiltins().getUnitClass());
        } else {
            FirExpression firExpressionUnwrapArgument = FirExpressionUtilKt.unwrapArgument(expression);
            if (firExpressionUnwrapArgument instanceof FirCallableReferenceAccess) {
                irExpressionConvertToArrayLiteral = convertCallableReferenceAccess((FirCallableReferenceAccess) firExpressionUnwrapArgument, isDelegate);
            } else if (firExpressionUnwrapArgument instanceof FirCollectionLiteral) {
                irExpressionConvertToArrayLiteral = convertToArrayLiteral((FirCollectionLiteral) firExpressionUnwrapArgument, expectedType);
            } else {
                Object objAccept = expression.accept(this, null);
                objAccept.getClass();
                irExpressionConvertToArrayLiteral = (IrExpression) objAccept;
            }
        }
        IrExpression irExpression = irExpressionConvertToArrayLiteral;
        return expectedType != null ? ImplicitConversionUtilsKt.prepareExpressionForGivenExpectedType$default(this, irExpression, expression, null, expectedType, null, false, 20, null) : irExpression;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:43:0x007d  */
    /* JADX WARN: Code duplicated, block: B:49:0x00b2 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:50:0x00b3  */
    public final IrExpression convertToIrReceiverExpression$org_jetbrains_kotlin_fir2ir(FirExpression receiver, FirQualifiedAccessExpression selector) throws KotlinIllegalArgumentExceptionWithAttachments {
        Fir2IrVisitor fir2IrVisitor;
        FirExpression firExpression;
        IrExpression irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default;
        boolean z;
        CallableId callableId;
        IrValueAccessExpression irValueAccessExpressionConvertToIrReceiverExpression$unwrapTypeOperators;
        selector.getClass();
        FirReference calleeReference = selector.getCalleeReference();
        if (receiver == null) {
            return null;
        }
        boolean z2 = receiver instanceof FirResolvedQualifier;
        if (!z2) {
            if ((receiver instanceof FirFunctionCall) || (receiver instanceof FirThisReceiverExpression) || (receiver instanceof FirCallableReferenceAccess) || (receiver instanceof FirSmartCastExpression)) {
                fir2IrVisitor = this;
                firExpression = receiver;
                irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default = convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(fir2IrVisitor, firExpression, false, null, 6, null);
            } else if (receiver instanceof FirQualifiedAccessExpression) {
                FirQualifiedAccessExpression firQualifiedAccessExpression = (FirQualifiedAccessExpression) receiver;
                if (firQualifiedAccessExpression.getExplicitReceiver() == null) {
                    if (calleeReference instanceof FirResolvedNamedReference) {
                        FirResolvedNamedReference firResolvedNamedReference = (FirResolvedNamedReference) calleeReference;
                        Name name = firResolvedNamedReference.getName();
                        Name name2 = OperatorNameConventions.INVOKE;
                        if (Intrinsics.areEqual(name, name2)) {
                            z = false;
                        } else {
                            FirBasedSymbol<?> resolvedSymbol = firResolvedNamedReference.getResolvedSymbol();
                            FirCallableSymbol firCallableSymbol = resolvedSymbol instanceof FirCallableSymbol ? (FirCallableSymbol) resolvedSymbol : null;
                            if (Intrinsics.areEqual((firCallableSymbol == null || (callableId = firCallableSymbol.getCallableId()) == null) ? null : callableId.getCallableName(), name2)) {
                                z = true;
                            } else {
                                z = false;
                            }
                        }
                    } else {
                        z = false;
                    }
                    irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default = CallAndReferenceGenerator.convertToIrCall$default(getCallGenerator(), firQualifiedAccessExpression, FirTypeUtilsKt.getResolvedType(receiver), null, null, z, false, 40, null);
                } else {
                    fir2IrVisitor = this;
                    firExpression = receiver;
                    irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default = convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(fir2IrVisitor, firExpression, false, null, 6, null);
                }
            } else {
                fir2IrVisitor = this;
                firExpression = receiver;
                irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default = convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(fir2IrVisitor, firExpression, false, null, 6, null);
            }
            if (irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default == null) {
                return null;
            }
            irValueAccessExpressionConvertToIrReceiverExpression$unwrapTypeOperators = convertToIrReceiverExpression$unwrapTypeOperators(irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default);
            if ((irValueAccessExpressionConvertToIrReceiverExpression$unwrapTypeOperators instanceof IrValueAccessExpression) && !Intrinsics.areEqual(firExpression, selector.getExplicitReceiver())) {
                irValueAccessExpressionConvertToIrReceiverExpression$unwrapTypeOperators.setOrigin(IrStatementOrigin.Companion.getIMPLICIT_ARGUMENT());
            }
            if ((firExpression instanceof FirSuperReceiverExpression) && !z2) {
                ConeKotlinType coneKotlinTypeExpectedReceiverType = fir2IrVisitor.expectedReceiverType(selector, firExpression);
                if (coneKotlinTypeExpectedReceiverType != null) {
                    return ImplicitConversionUtilsKt.prepareExpressionForGivenExpectedType$default(fir2IrVisitor, irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default, firExpression, null, coneKotlinTypeExpectedReceiverType, null, true, 20, null);
                }
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("Cannot determine expected receiver type", (Throwable) null);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "selector", selector);
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "receiver", firExpression);
                Unit unit = Unit.INSTANCE;
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        }
        irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default = getCallGenerator().convertToGetObject$org_jetbrains_kotlin_fir2ir((FirResolvedQualifier) receiver, selector instanceof FirCallableReferenceAccess ? (FirCallableReferenceAccess) selector : null);
        fir2IrVisitor = this;
        firExpression = receiver;
        if (irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default == null) {
            return null;
        }
        irValueAccessExpressionConvertToIrReceiverExpression$unwrapTypeOperators = convertToIrReceiverExpression$unwrapTypeOperators(irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default);
        if (irValueAccessExpressionConvertToIrReceiverExpression$unwrapTypeOperators instanceof IrValueAccessExpression) {
            irValueAccessExpressionConvertToIrReceiverExpression$unwrapTypeOperators.setOrigin(IrStatementOrigin.Companion.getIMPLICIT_ARGUMENT());
        }
        return firExpression instanceof FirSuperReceiverExpression ? irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default : irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AdapterGenerator getAdapterGenerator() {
        return this.c.getAdapterGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AnnotationGenerator getAnnotationGenerator() {
        return this.c.getAnnotationGenerator();
    }

    /* JADX INFO: renamed from: getAnnotationMode, reason: from getter */
    public final boolean get_annotationMode() {
        return this._annotationMode;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrIrGeneratedDeclarationsRegistrar getAnnotationsFromPluginRegistrar() {
        return this.c.getAnnotationsFromPluginRegistrar();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrBuiltinSymbolsContainer getBuiltins() {
        return this.c.getBuiltins();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public CallAndReferenceGenerator getCallGenerator() {
        return this.c.getCallGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrCallableDeclarationsGenerator getCallablesGenerator() {
        return this.c.getCallablesGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrClassifierStorage getClassifierStorage() {
        return this.c.getClassifierStorage();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrClassifiersGenerator getClassifiersGenerator() {
        return this.c.getClassifiersGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrConfiguration getConfiguration() {
        return this.c.getConfiguration();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrConverter getConverter() {
        return this.c.getConverter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrDataClassMembersGenerator getDataClassMembersGenerator() {
        return this.c.getDataClassMembersGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrDeclarationStorage getDeclarationStorage() {
        return this.c.getDeclarationStorage();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrExtensions getExtensions() {
        return this.c.getExtensions();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Set<FirFile> getFilesBeingCompiled() {
        return this.c.getFilesBeingCompiled();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public FirProviderWithGeneratedFiles getFirProvider() {
        return this.c.getFirProvider();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrImplicitCastInserter getImplicitCastInserter() {
        return this.c.getImplicitCastInserter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public KotlinMangler.IrMangler getIrMangler() {
        return this.c.getIrMangler();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public List<IrProvider> getIrProviders() {
        return this.c.getIrProviders();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrLazyDeclarationsGenerator getLazyDeclarationsGenerator() {
        return this.c.getLazyDeclarationsGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrLazyFakeOverrideGenerator getLazyFakeOverrideGenerator() {
        return this.c.getLazyFakeOverrideGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrLock getLock() {
        return this.c.getLock();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.c.getScopeSession();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.c.getSession();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrSpecialAnnotationsProvider getSpecialAnnotationsProvider() {
        return this.c.getSpecialAnnotationsProvider();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrSymbolsMappingForLazyClasses getSymbolsMappingForLazyClasses() {
        return this.c.getSymbolsMappingForLazyClasses();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrTypeConverter getTypeConverter() {
        return this.c.getTypeConverter();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrVisibilityConverter getVisibilityConverter() {
        return this.c.getVisibilityConverter();
    }

    public final boolean isGetClassOfUnresolvedTypeInAnnotation$org_jetbrains_kotlin_fir2ir(FirExpression expression) {
        expression.getClass();
        return getConfiguration().getSkipBodies() && get_annotationMode() && (expression instanceof FirGetClassCall) && (FirTypeUtilsKt.getResolvedType(((FirGetClassCall) expression).getArgument()) instanceof ConeErrorType);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitAnnotation(FirAnnotation annotation, Object data) {
        annotation.getClass();
        return getCallGenerator().convertToIrAnnotation(annotation);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitAnnotationCall(FirAnnotationCall annotationCall, Object data) {
        annotationCall.getClass();
        FirSession session = getSession();
        try {
            return getCallGenerator().convertToIrAnnotation(annotationCall);
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(annotationCall, th);
            wq6.a();
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:34:0x006b  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitAnonymousFunction(FirAnonymousFunction anonymousFunction, Object data) {
        FirAnonymousFunction firAnonymousFunction;
        Throwable th;
        int endOffset;
        KtSourceElementKind kind;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        anonymousFunction.getClass();
        FirSession session = getSession();
        try {
            KtSourceElement source = anonymousFunction.getSource();
            int i = -1;
            if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
                endOffset = -1;
            } else {
                if (source != null) {
                    try {
                        kind = source.getKind();
                    } catch (Throwable th2) {
                        th = th2;
                        firAnonymousFunction = anonymousFunction;
                        UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(firAnonymousFunction, th);
                        wq6.a();
                        return null;
                    }
                } else {
                    kind = null;
                }
                if (Intrinsics.areEqual(kind, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                            endOffset = -1;
                        } else {
                            if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                                startOffset = source != null ? source.getStartOffset() : -1;
                            } else {
                                startOffset = numStartOffsetSkippingComments.intValue();
                            }
                            endOffset = source != null ? source.getEndOffset() : -1;
                            i = startOffset;
                        }
                    }
                }
            }
            Fir2IrDeclarationStorage declarationStorage = getDeclarationStorage();
            IrDeclarationParent irDeclarationParentParent = this.conversionScope.parent();
            IrDeclarationOrigin.Companion companion = IrDeclarationOrigin.Companion;
            firAnonymousFunction = anonymousFunction;
            try {
                IrFunction irFunctionCreateAndCacheIrFunction$default = Fir2IrDeclarationStorage.createAndCacheIrFunction$default(declarationStorage, firAnonymousFunction, irDeclarationParentParent, companion.getLOCAL_FUNCTION(), true, null, false, 48, null);
                Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
                fir2IrConversionScope.getFunctionStack().add(irFunctionCreateAndCacheIrFunction$default);
                try {
                    this.memberGenerator.convertFunctionContent(irFunctionCreateAndCacheIrFunction$default, firAnonymousFunction, null);
                    fir2IrConversionScope.getFunctionStack().remove(fir2IrConversionScope.getFunctionStack().size() - 1);
                    IrFunctionExpressionImpl IrFunctionExpressionImpl = BuildersKt.IrFunctionExpressionImpl(i, endOffset, Fir2IrTypeConverterKt.toIrType$default(this, Fir2IrTypeConverterKt.approximateFunctionTypeInputs(this, FirTypeUtilsKt.getConeType(firAnonymousFunction.getTypeRef())), (ConversionTypeOrigin) null, 2, (Object) null), irFunctionCreateAndCacheIrFunction$default, Intrinsics.areEqual(irFunctionCreateAndCacheIrFunction$default.getOrigin(), companion.getLOCAL_FUNCTION_FOR_LAMBDA()) ? IrStatementOrigin.Companion.getLAMBDA() : IrStatementOrigin.Companion.getANONYMOUS_FUNCTION());
                    this.cleaner.cleanAnonymousFunction(firAnonymousFunction);
                    return IrFunctionExpressionImpl;
                } catch (Throwable th3) {
                    fir2IrConversionScope.getFunctionStack().remove(fir2IrConversionScope.getFunctionStack().size() - 1);
                    throw th3;
                }
            } catch (Throwable th4) {
                th = th4;
                th = th;
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(firAnonymousFunction, th);
                wq6.a();
                return null;
            }
        } catch (Throwable th5) {
            th = th5;
            firAnonymousFunction = anonymousFunction;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitAnonymousFunctionExpression(FirAnonymousFunctionExpression anonymousFunctionExpression, Object data) {
        anonymousFunctionExpression.getClass();
        return visitAnonymousFunction(anonymousFunctionExpression.getAnonymousFunction(), data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitAnonymousInitializer(FirAnonymousInitializer anonymousInitializer, Object data) {
        IrBlockBody irBlockBodyConvertToIrBlockBody$org_jetbrains_kotlin_fir2ir;
        anonymousInitializer.getClass();
        FirSession session = getSession();
        try {
            IrAnonymousInitializer irAnonymousInitializer = getDeclarationStorage().getIrAnonymousInitializer(anonymousInitializer);
            getDeclarationStorage().enterScope(irAnonymousInitializer.getSymbol());
            Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
            fir2IrConversionScope.get_initBlocksStack().add(irAnonymousInitializer);
            try {
                if (getConfiguration().getSkipBodies()) {
                    irBlockBodyConvertToIrBlockBody$org_jetbrains_kotlin_fir2ir = IrFactoryImpl.INSTANCE.createBlockBody(-1, -1);
                } else {
                    FirBlock body = anonymousInitializer.getBody();
                    body.getClass();
                    irBlockBodyConvertToIrBlockBody$org_jetbrains_kotlin_fir2ir = convertToIrBlockBody$org_jetbrains_kotlin_fir2ir(body);
                }
                irAnonymousInitializer.setBody(irBlockBodyConvertToIrBlockBody$org_jetbrains_kotlin_fir2ir);
                Unit unit = Unit.INSTANCE;
                fir2IrConversionScope.get_initBlocksStack().remove(fir2IrConversionScope.get_initBlocksStack().size() - 1);
                getDeclarationStorage().leaveScope(irAnonymousInitializer.getSymbol());
                this.cleaner.cleanAnonymousInitializer(anonymousInitializer);
                return irAnonymousInitializer;
            } catch (Throwable th) {
                fir2IrConversionScope.get_initBlocksStack().remove(fir2IrConversionScope.get_initBlocksStack().size() - 1);
                throw th;
            }
        } catch (Throwable th2) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(anonymousInitializer, th2);
            wq6.a();
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:44:0x00da  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitAnonymousObject(FirAnonymousObject anonymousObject, Object data) {
        int i;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        anonymousObject.getClass();
        FirSession session = getSession();
        try {
            IrDeclarationParent irDeclarationParentParentFromStack = this.conversionScope.parentFromStack();
            IrElement cachedIrLocalClass = getClassifierStorage().getCachedIrLocalClass(anonymousObject);
            if (cachedIrLocalClass != null) {
                cachedIrLocalClass.setParent(irDeclarationParentParentFromStack);
            } else {
                cachedIrLocalClass = getConverter().processLocalClassAndNestedClasses(anonymousObject, irDeclarationParentParentFromStack);
            }
            Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
            fir2IrConversionScope.get_parentStack().add(cachedIrLocalClass);
            if (cachedIrLocalClass != null) {
                fir2IrConversionScope.getScopeStack().add(new Scope(cachedIrLocalClass.getSymbol()));
            }
            try {
                this.memberGenerator.convertClassContent(cachedIrLocalClass, anonymousObject);
                Unit unit = Unit.INSTANCE;
                if (cachedIrLocalClass != null) {
                    fir2IrConversionScope.getScopeStack().remove(fir2IrConversionScope.getScopeStack().size() - 1);
                }
                fir2IrConversionScope.get_parentStack().remove(fir2IrConversionScope.get_parentStack().size() - 1);
                IrValueParameter thisReceiver = cachedIrLocalClass.getThisReceiver();
                thisReceiver.getClass();
                IrType type = thisReceiver.getType();
                KtSourceElement source = anonymousObject.getSource();
                if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
                    i = -1;
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                        i = -1;
                        endOffset = -1;
                    } else {
                        if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                            i = -1;
                            endOffset = -1;
                        } else {
                            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                                i = -1;
                                endOffset = -1;
                            } else {
                                if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                                    startOffset = source != null ? source.getStartOffset() : -1;
                                } else {
                                    startOffset = numStartOffsetSkippingComments.intValue();
                                }
                                i = startOffset;
                                endOffset = source != null ? source.getEndOffset() : -1;
                            }
                        }
                    }
                }
                IrStatementOrigin.Companion companion = IrStatementOrigin.Companion;
                IrBlockImpl IrBlockImpl = BuildersKt.IrBlockImpl(i, endOffset, type, companion.getOBJECT_LITERAL(), CollectionsKt.listOf(new IrElement[]{cachedIrLocalClass, BuildersKt.fromSymbolOwner(IrConstructorCallImpl.Companion, i, endOffset, type, ((IrConstructor) SequencesKt.first(IrUtilsKt.getConstructors(cachedIrLocalClass))).getSymbol(), cachedIrLocalClass.getTypeParameters().size(), companion.getOBJECT_LITERAL())}));
                this.cleaner.cleanAnonymousObject(anonymousObject);
                return IrBlockImpl;
            } catch (Throwable th) {
                if (cachedIrLocalClass != null) {
                    fir2IrConversionScope.getScopeStack().remove(fir2IrConversionScope.getScopeStack().size() - 1);
                }
                fir2IrConversionScope.get_parentStack().remove(fir2IrConversionScope.get_parentStack().size() - 1);
                throw th;
            }
        } catch (Throwable th2) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(anonymousObject, th2);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitAnonymousObjectExpression(FirAnonymousObjectExpression anonymousObjectExpression, Object data) {
        anonymousObjectExpression.getClass();
        return visitAnonymousObject(anonymousObjectExpression.getAnonymousObject(), data);
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0062  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitBooleanOperatorExpression(FirBooleanOperatorExpression booleanOperatorExpression, Object data) {
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        booleanOperatorExpression.getClass();
        KtSourceElement source = booleanOperatorExpression.getSource();
        int i = -1;
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        endOffset = source != null ? source.getEndOffset() : -1;
                        i = startOffset;
                    }
                }
            }
        }
        Object objAccept = booleanOperatorExpression.getLeftOperand().accept(this, data);
        objAccept.getClass();
        IrExpression irExpression = (IrExpression) objAccept;
        Object objAccept2 = booleanOperatorExpression.getRightOperand().accept(this, data);
        objAccept2.getClass();
        IrExpression irExpression2 = (IrExpression) objAccept2;
        if (irExpression.getType() instanceof IrDynamicType) {
            IrDynamicOperatorExpressionImpl IrDynamicOperatorExpressionImpl = BuildersKt.IrDynamicOperatorExpressionImpl(i, endOffset, getBuiltins().getBooleanType(), toIrDynamicOperator(booleanOperatorExpression.getKind()));
            IrDynamicOperatorExpressionImpl.setReceiver(irExpression);
            IrDynamicOperatorExpressionImpl.getArguments().add(irExpression2);
            return IrDynamicOperatorExpressionImpl;
        }
        int i2 = WhenMappings.$EnumSwitchMapping$1[booleanOperatorExpression.getKind().ordinal()];
        if (i2 == 1) {
            IrWhenImpl IrWhenImpl = BuildersKt.IrWhenImpl(i, endOffset, getBuiltins().getBooleanType(), IrStatementOrigin.Companion.getANDAND());
            IrWhenImpl.getBranches().add(BuildersKt.IrBranchImpl(irExpression, irExpression2));
            IrWhenImpl.getBranches().add(IrElementsCreationUtilsKt.elseBranch(this, IrElementsCreationUtilsKt.constFalse(this, irExpression2.getStartOffset(), irExpression2.getEndOffset())));
            return IrWhenImpl;
        }
        if (i2 != 2) {
            bu8.a();
            return null;
        }
        IrWhenImpl IrWhenImpl2 = BuildersKt.IrWhenImpl(i, endOffset, getBuiltins().getBooleanType(), IrStatementOrigin.Companion.getOROR());
        IrWhenImpl2.getBranches().add(BuildersKt.IrBranchImpl(irExpression, IrElementsCreationUtilsKt.constTrue(this, irExpression.getStartOffset(), irExpression.getEndOffset())));
        IrWhenImpl2.getBranches().add(IrElementsCreationUtilsKt.elseBranch(this, irExpression2));
        return IrWhenImpl2;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitBreakExpression(FirBreakExpression breakExpression, Object data) {
        breakExpression.getClass();
        return convertJumpWithOffsets(breakExpression, new Function4() { // from class: vw4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return Fir2IrVisitor.e(this.b, ((Integer) obj).intValue(), ((Integer) obj2).intValue(), (IrLoop) obj3, (String) obj4);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitCallableReferenceAccess(FirCallableReferenceAccess callableReferenceAccess, Object data) {
        callableReferenceAccess.getClass();
        FirSession session = getSession();
        try {
            return convertCallableReferenceAccess(callableReferenceAccess, false);
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(callableReferenceAccess, th);
            wq6.a();
            return null;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.KotlinNothingValueException */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitCatch(FirCatch firCatch, Object data) throws KotlinNothingValueException {
        firCatch.getClass();
        AddToStdlibKt.shouldNotBeCalled$default((String) null, 1, (Object) null);
        throw new KotlinNothingValueException();
    }

    /* JADX WARN: Code duplicated, block: B:33:0x006b  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitCheckNotNullCall(FirCheckNotNullCall checkNotNullCall, Object data) {
        int i;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        checkNotNullCall.getClass();
        FirSession session = getSession();
        try {
            KtSourceElement source = checkNotNullCall.getSource();
            if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
                i = -1;
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                    i = -1;
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                        i = -1;
                        endOffset = -1;
                    } else {
                        if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                            i = -1;
                            endOffset = -1;
                        } else {
                            if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                                startOffset = source != null ? source.getStartOffset() : -1;
                            } else {
                                startOffset = numStartOffsetSkippingComments.intValue();
                            }
                            i = startOffset;
                            endOffset = source != null ? source.getEndOffset() : -1;
                        }
                    }
                }
            }
            IrCallImpl irCallImplIrCallImplWithShape$default = BuildersKt.IrCallImplWithShape$default(i, endOffset, Fir2IrTypeConverterKt.toIrType$default(this, FirTypeUtilsKt.getResolvedType(checkNotNullCall), (ConversionTypeOrigin) null, 2, (Object) null), getBuiltins().getCheckNotNullSymbol(), 1, 1, 0, false, false, IrStatementOrigin.Companion.getEXCLEXCL(), (IrClassSymbol) null, 1024, (Object) null);
            irCallImplIrCallImplWithShape$default.getTypeArguments().set(0, IrTypesKt.makeNotNull(Fir2IrTypeConverterKt.toIrType$default(this, FirTypeUtilsKt.getResolvedType((FirExpression) CollectionsKt.first(checkNotNullCall.getArgumentList().getArguments())), (ConversionTypeOrigin) null, 2, (Object) null)));
            irCallImplIrCallImplWithShape$default.getArguments().set(0, convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, (FirExpression) CollectionsKt.first(checkNotNullCall.getArgumentList().getArguments()), false, null, 6, null));
            return irCallImplIrCallImplWithShape$default;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(checkNotNullCall, th);
            wq6.a();
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0068  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitCheckedSafeCallSubject(FirCheckedSafeCallSubject checkedSafeCallSubject, Object data) {
        int i;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        checkedSafeCallSubject.getClass();
        IrVariable irVariableLastSafeCallSubject = this.conversionScope.lastSafeCallSubject();
        KtSourceElement source = checkedSafeCallSubject.getSource();
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            i = -1;
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                i = -1;
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    i = -1;
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        i = -1;
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        i = startOffset;
                        endOffset = source != null ? source.getEndOffset() : -1;
                    }
                }
            }
        }
        IrExpression irExpressionIrGetValueImpl$default = BuildersKt.IrGetValueImpl$default(i, endOffset, irVariableLastSafeCallSubject.getType(), irVariableLastSafeCallSubject.getSymbol(), (IrStatementOrigin) null, 16, (Object) null);
        return Fir2IrImplicitCastInserter.INSTANCE.implicitCastOrExpression(irExpressionIrGetValueImpl$default, IrTypesKt.makeNotNull(irExpressionIrGetValueImpl$default.getType()));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitCodeFragment(FirCodeFragment codeFragment, Object data) throws KotlinIllegalArgumentExceptionWithAttachments {
        codeFragment.getClass();
        IrClass cachedIrCodeFragment = getClassifierStorage().getCachedIrCodeFragment(codeFragment);
        cachedIrCodeFragment.getClass();
        for (Object obj : cachedIrCodeFragment.getDeclarations()) {
            if (obj instanceof IrSimpleFunction) {
                IrDeclarationParent irDeclarationParent = (IrSimpleFunction) obj;
                getDeclarationStorage().enterScope(irDeclarationParent.getSymbol());
                Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
                fir2IrConversionScope.get_parentStack().add(irDeclarationParent);
                fir2IrConversionScope.getScopeStack().add(new Scope(irDeclarationParent.getSymbol()));
                try {
                    irDeclarationParent.setBody(getConfiguration().getSkipBodies() ? IrFactoryHelpersKt.createExpressionBody(IrFactoryImpl.INSTANCE, IrUtilsKt.defaultValueForType(IrConstImpl.Companion, -1, -1, irDeclarationParent.getReturnType())) : IrFactoryHelpersKt.createExpressionBody(IrFactoryImpl.INSTANCE, convertToIrBlock(codeFragment.getBlock(), null, null)));
                    Unit unit = Unit.INSTANCE;
                    fir2IrConversionScope.getScopeStack().remove(fir2IrConversionScope.getScopeStack().size() - 1);
                    fir2IrConversionScope.get_parentStack().remove(fir2IrConversionScope.get_parentStack().size() - 1);
                    getDeclarationStorage().leaveScope(irDeclarationParent.getSymbol());
                    return irDeclarationParent;
                } catch (Throwable th) {
                    fir2IrConversionScope.getScopeStack().remove(fir2IrConversionScope.getScopeStack().size() - 1);
                    fir2IrConversionScope.get_parentStack().remove(fir2IrConversionScope.get_parentStack().size() - 1);
                    throw th;
                }
            }
        }
        hb9.a("No element of given type found");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitComparisonExpression(FirComparisonExpression comparisonExpression, Object data) {
        comparisonExpression.getClass();
        return this.operatorGenerator.convertComparisonExpression(comparisonExpression);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitConstructor(FirConstructor constructor, Object data) {
        constructor.getClass();
        FirSession session = getSession();
        try {
            IrConstructorSymbol cachedIrConstructorSymbol = getDeclarationStorage().getCachedIrConstructorSymbol(constructor);
            cachedIrConstructorSymbol.getClass();
            IrFunction irFunction = (IrConstructor) cachedIrConstructorSymbol.getOwner();
            Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
            fir2IrConversionScope.getFunctionStack().add(irFunction);
            try {
                IrConstructor irConstructorConvertFunctionContent = this.memberGenerator.convertFunctionContent(irFunction, constructor, this.conversionScope.containerFirClass());
                fir2IrConversionScope.getFunctionStack().remove(fir2IrConversionScope.getFunctionStack().size() - 1);
                this.cleaner.cleanConstructor(constructor);
                return irConstructorConvertFunctionContent;
            } catch (Throwable th) {
                fir2IrConversionScope.getFunctionStack().remove(fir2IrConversionScope.getFunctionStack().size() - 1);
                throw th;
            }
        } catch (Throwable th2) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(constructor, th2);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitContinueExpression(FirContinueExpression continueExpression, Object data) {
        continueExpression.getClass();
        return convertJumpWithOffsets(continueExpression, new Function4() { // from class: ww4
            public final Object invoke(Object obj, Object obj2, Object obj3, Object obj4) {
                return Fir2IrVisitor.c(this.b, ((Integer) obj).intValue(), ((Integer) obj2).intValue(), (IrLoop) obj3, (String) obj4);
            }
        });
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitDesugaredAssignmentValueReferenceExpression(FirDesugaredAssignmentValueReferenceExpression desugaredAssignmentValueReferenceExpression, Object data) {
        desugaredAssignmentValueReferenceExpression.getClass();
        return (IrElement) desugaredAssignmentValueReferenceExpression.getExpressionRef().getValue().accept(this, null);
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0062  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitDoWhileLoop(FirDoWhileLoop doWhileLoop, Object data) {
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        doWhileLoop.getClass();
        KtSourceElement source = doWhileLoop.getSource();
        int i = -1;
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        endOffset = source != null ? source.getEndOffset() : -1;
                        i = startOffset;
                    }
                }
            }
        }
        IrLoop irLoopIrDoWhileLoopImpl = BuildersKt.IrDoWhileLoopImpl(i, endOffset, getBuiltins().getUnitType(), IrStatementOrigin.Companion.getDO_WHILE_LOOP());
        this.loopMap.put(doWhileLoop, irLoopIrDoWhileLoopImpl);
        FirLabel label = doWhileLoop.getLabel();
        irLoopIrDoWhileLoopImpl.setLabel(label != null ? label.getName() : null);
        irLoopIrDoWhileLoopImpl.setBody(doWhileLoop.getBlock() instanceof FirEmptyExpressionBlock ? null : convertToIrExpressionOrBlock(doWhileLoop.getBlock(), irLoopIrDoWhileLoopImpl.getOrigin(), this.unitType));
        irLoopIrDoWhileLoopImpl.setCondition(convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, doWhileLoop.getCondition(), false, null, 6, null));
        this.loopMap.remove(doWhileLoop);
        IrBlockImpl irBlockImplIrBlockImpl$default = BuildersKt.IrBlockImpl$default(irLoopIrDoWhileLoopImpl.getStartOffset(), irLoopIrDoWhileLoopImpl.getEndOffset(), getBuiltins().getUnitType(), (IrStatementOrigin) null, 8, (Object) null);
        irBlockImplIrBlockImpl$default.getStatements().add(irLoopIrDoWhileLoopImpl);
        return irBlockImplIrBlockImpl$default;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitElement(FirElement element, Object data) {
        element.getClass();
        throw new IllegalStateException(("Should not be here: " + Reflection.getOrCreateKotlinClass(element.getClass()) + ' ' + UtilsKt.render(element)).toString());
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0064  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitElvisExpression(FirElvisExpression elvisExpression, Object data) {
        int i;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        elvisExpression.getClass();
        KtSourceElement source = elvisExpression.getSource();
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            i = -1;
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                i = -1;
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    i = -1;
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        i = -1;
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        i = startOffset;
                        endOffset = source != null ? source.getEndOffset() : -1;
                    }
                }
            }
        }
        Scope scope = this.conversionScope.scope();
        Object objAccept = elvisExpression.getLhs().accept(this, null);
        objAccept.getClass();
        IrVariable irVariableCreateTemporaryVariable$default = Scope.createTemporaryVariable$default(scope, (IrExpression) objAccept, "elvis_lhs", false, (IrDeclarationOrigin) null, (IrType) null, i, endOffset, false, 156, (Object) null);
        IrType booleanType = getBuiltins().getBooleanType();
        IrSimpleFunctionSymbol eqeqSymbol = getBuiltins().getEqeqSymbol();
        IrStatementOrigin.Companion companion = IrStatementOrigin.Companion;
        int i2 = i;
        int i3 = endOffset;
        IrCallImpl irCallImplIrCallImplWithShape$default = BuildersKt.IrCallImplWithShape$default(i2, i3, booleanType, eqeqSymbol, 0, 2, 0, false, false, companion.getEQEQ(), (IrClassSymbol) null, 1024, (Object) null);
        irCallImplIrCallImplWithShape$default.getArguments().set(0, visitElvisExpression$lambda$0$irGetLhsValue(i2, i3, irVariableCreateTemporaryVariable$default));
        IrMemberAccessExpression.ValueArgumentsList arguments = irCallImplIrCallImplWithShape$default.getArguments();
        IrConstImpl.Companion companion2 = IrConstImpl.Companion;
        arguments.set(1, companion2.constNull(i2, i3, getBuiltins().getNothingNType()));
        Unit unit = Unit.INSTANCE;
        return generateWhen(i2, i3, companion.getELVIS(), irVariableCreateTemporaryVariable$default, CollectionsKt.listOf(new IrBranch[]{BuildersKt.IrBranchImpl(i2, i3, irCallImplIrCallImplWithShape$default, convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, elvisExpression.getRhs(), false, FirTypeUtilsKt.getResolvedType(elvisExpression), 2, null)), BuildersKt.IrElseBranchImpl(companion2.boolean(i2, i3, getBuiltins().getBooleanType(), true), visitElvisExpression$lambda$0$irGetLhsValue(i2, i3, irVariableCreateTemporaryVariable$default))}), Fir2IrTypeConverterKt.toIrType$default(this, FirTypeUtilsKt.getResolvedType(elvisExpression), (ConversionTypeOrigin) null, 2, (Object) null));
    }

    /* JADX WARN: Code duplicated, block: B:56:0x01aa  */
    /* JADX WARN: Code duplicated, block: B:92:0x0227  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitEnumEntry(FirEnumEntry enumEntry, Object data) {
        int i;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        int endOffset2;
        int startOffset2;
        Integer numStartOffsetSkippingComments2;
        FirConstructor firConstructor;
        enumEntry.getClass();
        FirSession session = getSession();
        try {
            IrEnumEntry owner = getClassifierStorage().getIrEnumEntrySymbol(enumEntry).getOwner();
            getAnnotationGenerator().generate((IrMutableAnnotationContainer) owner, (FirAnnotationContainer) enumEntry);
            if (getConfiguration().getSkipBodies()) {
                return owner;
            }
            IrClass correspondingClass = owner.getCorrespondingClass();
            FirExpression initializer = enumEntry.getInitializer();
            IrType irType$default = Fir2IrTypeConverterKt.toIrType$default(this, enumEntry.getReturnTypeRef(), (ConversionTypeOrigin) null, 2, (Object) null);
            IrClass parent = owner.getParent();
            IrClass irClass = parent instanceof IrClass ? parent : null;
            if (correspondingClass != null) {
                getDeclarationStorage().enterScope(owner.getSymbol());
                getClassifierStorage().putEnumEntryClassInScope(enumEntry, correspondingClass);
                FirExpression initializer2 = enumEntry.getInitializer();
                initializer2.getClass();
                FirAnonymousObject anonymousObject = ((FirAnonymousObjectExpression) initializer2).getAnonymousObject();
                getConverter().processAnonymousObjectHeaders(anonymousObject, correspondingClass);
                getConverter().processClassMembers$org_jetbrains_kotlin_fir2ir(anonymousObject, correspondingClass);
                Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
                fir2IrConversionScope.get_parentStack().add(correspondingClass);
                fir2IrConversionScope.getScopeStack().add(new Scope(correspondingClass.getSymbol()));
                try {
                    this.memberGenerator.convertClassContent(correspondingClass, anonymousObject);
                    IrConstructor irConstructor = (IrConstructor) SequencesKt.first(IrUtilsKt.getConstructors(correspondingClass));
                    owner.setInitializerExpression(IrFactoryHelpersKt.createExpressionBody(IrFactoryImpl.INSTANCE, BuildersKt.IrEnumConstructorCallImpl$default(correspondingClass.getStartOffset(), correspondingClass.getEndOffset(), irType$default, irConstructor.getSymbol(), irConstructor.getTypeParameters().size(), (IrStatementOrigin) null, 32, (Object) null)));
                    Unit unit = Unit.INSTANCE;
                    fir2IrConversionScope.getScopeStack().remove(fir2IrConversionScope.getScopeStack().size() - 1);
                    fir2IrConversionScope.get_parentStack().remove(fir2IrConversionScope.get_parentStack().size() - 1);
                    getDeclarationStorage().leaveScope(owner.getSymbol());
                } catch (Throwable th) {
                    fir2IrConversionScope.getScopeStack().remove(fir2IrConversionScope.getScopeStack().size() - 1);
                    fir2IrConversionScope.get_parentStack().remove(fir2IrConversionScope.get_parentStack().size() - 1);
                    throw th;
                }
            } else {
                int i2 = -1;
                if (initializer instanceof FirAnonymousObjectExpression) {
                    FirConstructorSymbol firConstructorSymbolPrimaryConstructorIfAny = DeclarationUtilsKt.primaryConstructorIfAny(((FirAnonymousObjectExpression) initializer).getAnonymousObject(), getSession());
                    FirDelegatedConstructorCall delegatedConstructor = (firConstructorSymbolPrimaryConstructorIfAny == null || (firConstructor = (FirConstructor) firConstructorSymbolPrimaryConstructorIfAny.getFir()) == null) ? null : firConstructor.getDelegatedConstructor();
                    if (delegatedConstructor != null) {
                        ClassMemberGenerator classMemberGenerator = this.memberGenerator;
                        IrFactoryImpl irFactoryImpl = IrFactoryImpl.INSTANCE;
                        KtSourceElement source = delegatedConstructor.getSource();
                        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
                            endOffset2 = -1;
                        } else {
                            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                                endOffset2 = -1;
                            } else {
                                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                                    endOffset2 = -1;
                                } else {
                                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                                        endOffset2 = -1;
                                    } else {
                                        if (source == null || (numStartOffsetSkippingComments2 = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                                            startOffset2 = source != null ? source.getStartOffset() : -1;
                                        } else {
                                            startOffset2 = numStartOffsetSkippingComments2.intValue();
                                        }
                                        endOffset2 = source != null ? source.getEndOffset() : -1;
                                        i2 = startOffset2;
                                    }
                                }
                            }
                        }
                        owner.setInitializerExpression(IrFactoryHelpersKt.createExpressionBody(irFactoryImpl, classMemberGenerator.toIrDelegatingConstructorCall$org_jetbrains_kotlin_fir2ir(delegatedConstructor, i2, endOffset2)));
                    }
                } else if (irClass != null && !irClass.isExpect() && initializer == null) {
                    IrConstructor defaultConstructor = IrUtilsKt.getDefaultConstructor(irClass);
                    if (defaultConstructor == null) {
                        throw new IllegalStateException(("Assuming that default constructor should exist and be converted at this point: " + UtilsKt.render(enumEntry)).toString());
                    }
                    TokenSet tokenSet = KtTokens.VAL_VAR;
                    KtSourceElement source2 = enumEntry.getSource();
                    if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source2))) {
                        i = -1;
                        endOffset = -1;
                    } else {
                        if (Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                            i = -1;
                            endOffset = -1;
                        } else {
                            if (Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                                i = -1;
                                endOffset = -1;
                            } else {
                                if (Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                                    i = -1;
                                    endOffset = -1;
                                } else {
                                    if (source2 == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source2, tokenSet)) == null) {
                                        startOffset = source2 != null ? source2.getStartOffset() : -1;
                                    } else {
                                        startOffset = numStartOffsetSkippingComments.intValue();
                                    }
                                    i = startOffset;
                                    endOffset = source2 != null ? source2.getEndOffset() : -1;
                                }
                            }
                        }
                    }
                    owner.setInitializerExpression(IrFactoryHelpersKt.createExpressionBody(IrFactoryImpl.INSTANCE, BuildersKt.IrEnumConstructorCallImpl$default(i, endOffset, irType$default, defaultConstructor.getSymbol(), defaultConstructor.getTypeParameters().size(), (IrStatementOrigin) null, 32, (Object) null)));
                }
            }
            this.cleaner.cleanEnumEntry(enumEntry);
            return owner;
        } catch (Throwable th2) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(enumEntry, th2);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitEnumEntryDeserializedAccessExpression(FirEnumEntryDeserializedAccessExpression enumEntryDeserializedAccessExpression, Object data) {
        enumEntryDeserializedAccessExpression.getClass();
        return visitPropertyAccessExpression(FirEnumEntryDeserializerAccessUtilKt.toQualifiedPropertyAccessExpression(enumEntryDeserializedAccessExpression, getSession()), data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitEqualityOperatorCall(FirEqualityOperatorCall equalityOperatorCall, Object data) {
        equalityOperatorCall.getClass();
        FirSession session = getSession();
        try {
            return this.operatorGenerator.convertEqualityOperatorCall(equalityOperatorCall);
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(equalityOperatorCall, th);
            wq6.a();
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0062  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitErrorExpression(FirErrorExpression errorExpression, Object data) {
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        errorExpression.getClass();
        KtSourceElement source = errorExpression.getSource();
        int i = -1;
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        endOffset = source != null ? source.getEndOffset() : -1;
                        i = startOffset;
                    }
                }
            }
        }
        return BuildersKt.IrErrorExpressionImpl(i, endOffset, Fir2IrTypeConverterKt.toIrType$default(this, FirTypeUtilsKt.getResolvedType(errorExpression), (ConversionTypeOrigin) null, 2, (Object) null), errorExpression.getDiagnostic().getReason());
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitErrorResolvedQualifier(FirErrorResolvedQualifier errorResolvedQualifier, Object data) {
        errorResolvedQualifier.getClass();
        return visitResolvedQualifier((FirResolvedQualifier) errorResolvedQualifier, data);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrField visitField(FirField field, Object data) {
        field.getClass();
        FirSession session = getSession();
        try {
            if (!(field.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) {
                throw new IllegalArgumentException(("Non-synthetic field found during traversal of FIR tree: " + UtilsKt.render(field)).toString());
            }
            IrFieldSymbol cachedIrFieldSymbolForSupertypeDelegateField = getDeclarationStorage().getCachedIrFieldSymbolForSupertypeDelegateField(field);
            cachedIrFieldSymbolForSupertypeDelegateField.getClass();
            IrField owner = cachedIrFieldSymbolForSupertypeDelegateField.getOwner();
            IrField irField = owner;
            if (irField.getCorrespondingPropertySymbol() == null) {
                this.memberGenerator.convertFieldContent(irField, field);
            }
            return owner;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(field, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrFile visitFile(FirFile file, Object data) {
        file.getClass();
        IrDeclarationParent irFile = getDeclarationStorage().getIrFile(file);
        Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
        fir2IrConversionScope.get_parentStack().add(irFile);
        boolean z = irFile instanceof IrDeclaration;
        if (z) {
            fir2IrConversionScope.getScopeStack().add(new Scope(irFile.getSymbol()));
        }
        try {
            Iterator<T> it = file.getDeclarations().iterator();
            while (it.hasNext()) {
                toIrDeclaration((FirDeclaration) it.next());
            }
            getAnnotationGenerator().generate((IrMutableAnnotationContainer) irFile, (FirAnnotationContainer) file);
            irFile.setMetadata(new FirMetadataSource.File(file));
            Unit unit = Unit.INSTANCE;
            if (z) {
                fir2IrConversionScope.getScopeStack().remove(fir2IrConversionScope.getScopeStack().size() - 1);
            }
            fir2IrConversionScope.get_parentStack().remove(fir2IrConversionScope.get_parentStack().size() - 1);
            this.cleaner.cleanFile(file);
            return irFile;
        } catch (Throwable th) {
            if (z) {
                fir2IrConversionScope.getScopeStack().remove(fir2IrConversionScope.getScopeStack().size() - 1);
            }
            fir2IrConversionScope.get_parentStack().remove(fir2IrConversionScope.get_parentStack().size() - 1);
            throw th;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrExpression visitFunctionCall(FirFunctionCall functionCall, Object data) {
        functionCall.getClass();
        FirSession session = getSession();
        try {
            return convertToIrCall(functionCall);
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(functionCall, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitFunctionTypeConversionExpression(FirFunctionTypeConversionExpression functionTypeConversionExpression, Object data) {
        functionTypeConversionExpression.getClass();
        return this.c.getAdapterGenerator().applyFunctionTypeConversion$org_jetbrains_kotlin_fir2ir(convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, functionTypeConversionExpression.getExpression(), false, null, 6, null), functionTypeConversionExpression);
    }

    /* JADX WARN: Code duplicated, block: B:48:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:90:0x0182  */
    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitGetClassCall(FirGetClassCall getClassCall, Object data) {
        IrTypeParameterSymbol symbol;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        int i;
        int endOffset2;
        int startOffset2;
        Integer numStartOffsetSkippingComments2;
        getClassCall.getClass();
        FirSession session = getSession();
        try {
            FirExpression argument = getClassCall.getArgument();
            IrType irType$default = Fir2IrTypeConverterKt.toIrType$default(this, FirTypeUtilsKt.getResolvedType(getClassCall), (ConversionTypeOrigin) null, 2, (Object) null);
            IrType irType$default2 = argument instanceof FirClassReferenceExpression ? Fir2IrTypeConverterKt.toIrType$default(this, ((FirClassReferenceExpression) argument).getClassTypeRef(), (ConversionTypeOrigin) null, 2, (Object) null) : Fir2IrTypeConverterKt.toIrType$default(this, FirTypeUtilsKt.getResolvedType(argument), (ConversionTypeOrigin) null, 2, (Object) null);
            int i2 = -1;
            if (argument instanceof FirResolvedReifiedParameterReference) {
                symbol = getClassifierStorage().getIrTypeParameterSymbol(((FirResolvedReifiedParameterReference) argument).getSymbol(), ConversionTypeOrigin.DEFAULT);
            } else if (argument instanceof FirResolvedQualifier) {
                FirClassLikeSymbol<?> symbol2 = ((FirResolvedQualifier) argument).getSymbol();
                if (symbol2 instanceof FirClassSymbol) {
                    symbol = getClassifierStorage().getIrClassSymbol((FirClassSymbol<?>) symbol2);
                } else {
                    if (!(symbol2 instanceof FirTypeAliasSymbol)) {
                        KtSourceElement source = getClassCall.getSource();
                        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
                            endOffset = -1;
                        } else {
                            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                                endOffset = -1;
                            } else {
                                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                                    endOffset = -1;
                                } else {
                                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                                        endOffset = -1;
                                    } else {
                                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                                            startOffset = source != null ? source.getStartOffset() : -1;
                                        } else {
                                            startOffset = numStartOffsetSkippingComments.intValue();
                                        }
                                        endOffset = source != null ? source.getEndOffset() : -1;
                                        i2 = startOffset;
                                    }
                                }
                            }
                        }
                        return BuildersKt.IrErrorCallExpressionImpl(i2, endOffset, irType$default, "Resolved qualifier " + UtilsKt.render(argument) + " does not have correct symbol");
                    }
                    symbol = toIrClassSymbol(TypeExpansionUtilsKt.fullyExpandedConeType((FirTypeAlias) ((FirTypeAliasSymbol) symbol2).getFir(), getSession()));
                }
            } else if (argument instanceof FirClassReferenceExpression) {
                ConeRigidType coneRigidTypeLowerBoundIfFlexible = ConeTypeUtilsKt.lowerBoundIfFlexible(FirTypeUtilsKt.getConeType(((FirClassReferenceExpression) argument).getClassTypeRef()));
                ConeClassLikeType coneClassLikeType = coneRigidTypeLowerBoundIfFlexible instanceof ConeClassLikeType ? (ConeClassLikeType) coneRigidTypeLowerBoundIfFlexible : null;
                if (coneClassLikeType == null || (symbol = toIrClassSymbol(coneClassLikeType)) == null) {
                    symbol = IrErrorClassImplKt.getIrErrorClassImpl().getSymbol();
                }
            } else {
                symbol = null;
            }
            KtSourceElement source2 = getClassCall.getSource();
            if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source2))) {
                i = -1;
                endOffset2 = -1;
            } else {
                if (Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                    i = -1;
                    endOffset2 = -1;
                } else {
                    if (Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                        i = -1;
                        endOffset2 = -1;
                    } else {
                        if (Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                            i = -1;
                            endOffset2 = -1;
                        } else {
                            if (source2 == null || (numStartOffsetSkippingComments2 = OffsetUtilsKt.startOffsetSkippingComments(source2, null)) == null) {
                                startOffset2 = source2 != null ? source2.getStartOffset() : -1;
                            } else {
                                startOffset2 = numStartOffsetSkippingComments2.intValue();
                            }
                            endOffset2 = source2 != null ? source2.getEndOffset() : -1;
                            i = startOffset2;
                        }
                    }
                }
            }
            return symbol != null ? BuildersKt.IrClassReferenceImpl(i, endOffset2, irType$default, (IrClassifierSymbol) symbol, irType$default2) : BuildersKt.IrGetClassImpl(i, endOffset2, irType$default, convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, argument, false, null, 6, null));
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(getClassCall, th);
            wq6.a();
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0062  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitInaccessibleReceiverExpression(FirInaccessibleReceiverExpression inaccessibleReceiverExpression, Object data) {
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        inaccessibleReceiverExpression.getClass();
        KtSourceElement source = inaccessibleReceiverExpression.getSource();
        int i = -1;
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        endOffset = source != null ? source.getEndOffset() : -1;
                        i = startOffset;
                    }
                }
            }
        }
        return BuildersKt.IrErrorExpressionImpl(i, endOffset, Fir2IrTypeConverterKt.toIrType$default(this, FirTypeUtilsKt.getResolvedType(inaccessibleReceiverExpression), (ConversionTypeOrigin) null, 2, (Object) null), "Receiver is inaccessible");
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0068  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitIndexedAccessAugmentedAssignment(FirIndexedAccessAugmentedAssignment indexedAccessAugmentedAssignment, Object data) {
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        indexedAccessAugmentedAssignment.getClass();
        FirSession session = getSession();
        try {
            KtSourceElement source = indexedAccessAugmentedAssignment.getSource();
            int i = -1;
            if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                            endOffset = -1;
                        } else {
                            if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                                startOffset = source != null ? source.getStartOffset() : -1;
                            } else {
                                startOffset = numStartOffsetSkippingComments.intValue();
                            }
                            endOffset = source != null ? source.getEndOffset() : -1;
                            i = startOffset;
                        }
                    }
                }
            }
            return BuildersKt.IrErrorCallExpressionImpl(i, endOffset, getBuiltins().getUnitType(), "FirIndexedAccessAugmentedAssignment (resolve isn't supported yet)");
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(indexedAccessAugmentedAssignment, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitLiteralExpression(FirLiteralExpression literalExpression, Object data) {
        literalExpression.getClass();
        return ConstantUtilsKt.toIrConst(literalExpression, Fir2IrTypeConverterKt.toIrType$default(this, FirTypeUtilsKt.getResolvedType(literalExpression), (ConversionTypeOrigin) null, 2, (Object) null));
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitNamedFunction(FirNamedFunction namedFunction, Object data) {
        FirNamedFunction firNamedFunction;
        IrSimpleFunction owner;
        namedFunction.getClass();
        FirSession session = getSession();
        try {
            try {
                if (Intrinsics.areEqual(namedFunction.getStatus().getVisibility(), Visibilities.Local.INSTANCE)) {
                    firNamedFunction = namedFunction;
                    owner = Fir2IrDeclarationStorage.createAndCacheIrFunction$default(getDeclarationStorage(), firNamedFunction, this.conversionScope.parent(), IrDeclarationOrigin.Companion.getLOCAL_FUNCTION(), true, null, false, 48, null);
                } else {
                    firNamedFunction = namedFunction;
                    IrSimpleFunctionSymbol cachedIrFunctionSymbol$default = Fir2IrDeclarationStorage.getCachedIrFunctionSymbol$default(getDeclarationStorage(), firNamedFunction, (ConeClassLikeLookupTag) null, 2, (Object) null);
                    cachedIrFunctionSymbol$default.getClass();
                    owner = cachedIrFunctionSymbol$default.getOwner();
                }
                Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
                fir2IrConversionScope.getFunctionStack().add(owner);
                try {
                    IrSimpleFunction irSimpleFunctionConvertFunctionContent = this.memberGenerator.convertFunctionContent(owner, firNamedFunction, this.conversionScope.containerFirClass());
                    fir2IrConversionScope.getFunctionStack().remove(fir2IrConversionScope.getFunctionStack().size() - 1);
                    this.cleaner.cleanNamedFunction(firNamedFunction);
                    return irSimpleFunctionConvertFunctionContent;
                } catch (Throwable th) {
                    fir2IrConversionScope.getFunctionStack().remove(fir2IrConversionScope.getFunctionStack().size() - 1);
                    throw th;
                }
            } catch (Throwable th2) {
                th = th2;
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(firNamedFunction, th);
                wq6.a();
                return null;
            }
        } catch (Throwable th3) {
            th = th3;
            firNamedFunction = namedFunction;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitProperty(FirProperty property, Object data) {
        IrProperty owner;
        property.getClass();
        FirSession session = getSession();
        try {
            if (property.getSymbol() instanceof FirLocalPropertySymbol) {
                return visitLocalVariable(property);
            }
            IrPropertySymbol cachedIrPropertySymbol = getDeclarationStorage().getCachedIrPropertySymbol(property, null);
            if (cachedIrPropertySymbol != null && (owner = cachedIrPropertySymbol.getOwner()) != null) {
                Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
                fir2IrConversionScope.getPropertyStack().add(TuplesKt.to(owner, property));
                try {
                    IrProperty irPropertyConvertPropertyContent = this.memberGenerator.convertPropertyContent(owner, property);
                    fir2IrConversionScope.getPropertyStack().remove(fir2IrConversionScope.getPropertyStack().size() - 1);
                    this.cleaner.cleanProperty(property);
                    return irPropertyConvertPropertyContent;
                } catch (Throwable th) {
                    fir2IrConversionScope.getPropertyStack().remove(fir2IrConversionScope.getPropertyStack().size() - 1);
                    throw th;
                }
            }
            return BuildersKt.IrErrorExpressionImpl(-1, -1, new IrErrorTypeImpl((KotlinType) null, CollectionsKt.emptyList(), Variance.INVARIANT, false, 8, (DefaultConstructorMarker) null), "Stub for Enum.entries");
        } catch (Throwable th2) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(property, th2);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitPropertyAccessExpression(FirPropertyAccessExpression propertyAccessExpression, Object data) {
        propertyAccessExpression.getClass();
        return convertQualifiedAccessExpression(propertyAccessExpression);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitQualifiedAccessExpression(FirQualifiedAccessExpression qualifiedAccessExpression, Object data) {
        qualifiedAccessExpression.getClass();
        return convertQualifiedAccessExpression(qualifiedAccessExpression);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrClass visitRegularClass(FirRegularClass regularClass, Object data) {
        regularClass.getClass();
        FirSession session = getSession();
        try {
            if (Intrinsics.areEqual(regularClass.getStatus().getVisibility(), Visibilities.Local.INSTANCE)) {
                IrDeclarationParent irDeclarationParentParentFromStack = this.conversionScope.parentFromStack();
                IrClass cachedIrLocalClass = getClassifierStorage().getCachedIrLocalClass(regularClass);
                if (cachedIrLocalClass != null) {
                    cachedIrLocalClass.setParent(irDeclarationParentParentFromStack);
                } else {
                    cachedIrLocalClass = null;
                }
                if (cachedIrLocalClass != null) {
                    Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
                    fir2IrConversionScope.get_parentStack().add(cachedIrLocalClass);
                    fir2IrConversionScope.getScopeStack().add(new Scope(cachedIrLocalClass.getSymbol()));
                    try {
                        this.memberGenerator.convertClassContent(cachedIrLocalClass, regularClass);
                        Unit unit = Unit.INSTANCE;
                        return cachedIrLocalClass;
                    } finally {
                        fir2IrConversionScope.getScopeStack().remove(fir2IrConversionScope.getScopeStack().size() - 1);
                        fir2IrConversionScope.get_parentStack().remove(fir2IrConversionScope.get_parentStack().size() - 1);
                    }
                }
                getConverter().processLocalClassAndNestedClasses(regularClass, irDeclarationParentParentFromStack);
                UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(regularClass, th);
                wq6.a();
                return null;
            }
            IrClass irClass = getClassifierStorage().getIrClass(regularClass);
            if (regularClass.getStatus().getModality() == Modality.SEALED) {
                irClass.setSealedSubclasses(VariousUtilsKt.getIrSymbolsForSealedSubclasses(this, regularClass));
            }
            Fir2IrConversionScope fir2IrConversionScope2 = this.conversionScope;
            fir2IrConversionScope2.get_parentStack().add(irClass);
            if (irClass != null) {
                fir2IrConversionScope2.getScopeStack().add(new Scope(irClass.getSymbol()));
            }
            try {
                this.memberGenerator.convertClassContent(irClass, regularClass);
                Unit unit2 = Unit.INSTANCE;
                if (irClass != null) {
                    fir2IrConversionScope2.getScopeStack().remove(fir2IrConversionScope2.getScopeStack().size() - 1);
                }
                fir2IrConversionScope2.get_parentStack().remove(fir2IrConversionScope2.get_parentStack().size() - 1);
                this.cleaner.cleanClass(regularClass);
                return irClass;
            } catch (Throwable th) {
                if (irClass != null) {
                    fir2IrConversionScope2.getScopeStack().remove(fir2IrConversionScope2.getScopeStack().size() - 1);
                }
                fir2IrConversionScope2.get_parentStack().remove(fir2IrConversionScope2.get_parentStack().size() - 1);
                throw th;
            }
        } catch (Throwable th2) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(regularClass, th2);
            wq6.a();
            return null;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:39:0x00e7  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitReplSnippet(FirReplSnippet replSnippet, Object data) throws KotlinIllegalArgumentExceptionWithAttachments {
        int startOffset;
        Integer numStartOffsetSkippingComments;
        replSnippet.getClass();
        IrReplSnippet cachedIrReplSnippet = getDeclarationStorage().getCachedIrReplSnippet(replSnippet);
        cachedIrReplSnippet.getClass();
        cachedIrReplSnippet.setParent(this.conversionScope.parentFromStack());
        getDeclarationStorage().enterScope(cachedIrReplSnippet.getSymbol());
        Iterator<Fir2IrReplSnippetConfiguratorExtension> it = Fir2IrReplSnippetConfiguratorExtensionKt.getFir2IrReplSnippetConfigurators(FirExtensionServiceKt.getExtensionService(getSession())).iterator();
        while (it.hasNext()) {
            it.next().prepareSnippet(this, this, replSnippet, cachedIrReplSnippet);
        }
        List<FirScriptReceiverParameter> receivers = replSnippet.getReceivers();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(receivers, 10));
        int i = 0;
        for (Object obj : receivers) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            FirScriptReceiverParameter firScriptReceiverParameter = (FirScriptReceiverParameter) obj;
            Name nameIdentifier = Name.identifier("$script_receiver_" + i);
            nameIdentifier.getClass();
            IrDeclarationOrigin script_implicit_receiver = IrDeclarationOrigin.Companion.getSCRIPT_IMPLICIT_RECEIVER();
            KtSourceElement source = firScriptReceiverParameter.getSource();
            int endOffset = -1;
            if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
                startOffset = -1;
            } else if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                startOffset = -1;
            } else if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                startOffset = -1;
            } else if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                startOffset = -1;
            } else {
                startOffset = (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) ? source != null ? source.getStartOffset() : -1 : numStartOffsetSkippingComments.intValue();
                if (source != null) {
                    endOffset = source.getEndOffset();
                }
            }
            IrValueParameter irValueParameterCreateValueParameter = IrFactoryImpl.INSTANCE.createValueParameter(startOffset, endOffset, script_implicit_receiver, IrParameterKind.Context, nameIdentifier, Fir2IrTypeConverterKt.toIrType$default(this, firScriptReceiverParameter.getTypeRef(), (ConversionTypeOrigin) null, 2, (Object) null), false, new IrValueParameterSymbolImpl((ParameterDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null), (IrType) null, false, false, false);
            irValueParameterCreateValueParameter.setParent(cachedIrReplSnippet);
            irValueParameterCreateValueParameter.setIndexInParameters(i);
            arrayList.add(irValueParameterCreateValueParameter);
            i = i2;
        }
        cachedIrReplSnippet.setReceiverParameters(arrayList);
        Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
        fir2IrConversionScope.get_parentStack().add(cachedIrReplSnippet);
        fir2IrConversionScope.getScopeStack().add(new Scope(cachedIrReplSnippet.getSymbol()));
        try {
            cachedIrReplSnippet.setTargetClass(visitRegularClass(replSnippet.getSnippetClass(), data).getSymbol());
            Unit unit = Unit.INSTANCE;
            fir2IrConversionScope.getScopeStack().remove(fir2IrConversionScope.getScopeStack().size() - 1);
            fir2IrConversionScope.get_parentStack().remove(fir2IrConversionScope.get_parentStack().size() - 1);
            getDeclarationStorage().leaveScope(cachedIrReplSnippet.getSymbol());
            return cachedIrReplSnippet;
        } catch (Throwable th) {
            fir2IrConversionScope.getScopeStack().remove(fir2IrConversionScope.getScopeStack().size() - 1);
            fir2IrConversionScope.get_parentStack().remove(fir2IrConversionScope.get_parentStack().size() - 1);
            throw th;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitResolvedQualifier(FirResolvedQualifier resolvedQualifier, Object data) {
        resolvedQualifier.getClass();
        return getCallGenerator().convertToGetObject$org_jetbrains_kotlin_fir2ir(resolvedQualifier);
    }

    /* JADX WARN: Code duplicated, block: B:34:0x007f  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitReturnExpression(FirReturnExpression returnExpression, Object data) {
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        returnExpression.getClass();
        FirExpression result = returnExpression.getResult();
        if (result instanceof FirThrowExpression) {
            return convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, result, false, null, 6, null);
        }
        IrFunctionSymbol irFunctionSymbolReturnTarget = this.conversionScope.returnTarget(returnExpression, getDeclarationStorage());
        KtSourceElement source = returnExpression.getSource();
        int i = -1;
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        endOffset = source != null ? source.getEndOffset() : -1;
                        i = startOffset;
                    }
                }
            }
        }
        KtSourceElement source2 = returnExpression.getSource();
        return new IrReturnImpl((source2 != null ? source2.getKind() : null) instanceof KtFakeSourceElementKind.ImplicitReturn ? endOffset : i, endOffset, getBuiltins().getNothingType(), irFunctionSymbolReturnTarget, convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, result, false, FirTypeUtilsKt.getConeType(((FirFunction) returnExpression.getTarget().getLabeledElement()).getReturnTypeRef()), 2, null));
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitSafeCallExpression(FirSafeCallExpression safeCallExpression, Object data) {
        IrExpression irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default;
        safeCallExpression.getClass();
        FirSession session = getSession();
        try {
            Pair<IrVariable, IrValueSymbol> pairCreateTemporaryVariableForSafeCallConstruction = IrElementsCreationUtilsKt.createTemporaryVariableForSafeCallConstruction(this.conversionScope, convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, safeCallExpression.getReceiver(), false, null, 6, null));
            IrVariable irVariable = (IrVariable) pairCreateTemporaryVariableForSafeCallConstruction.component1();
            IrValueSymbol irValueSymbol = (IrValueSymbol) pairCreateTemporaryVariableForSafeCallConstruction.component2();
            Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
            if (irVariable != null) {
                fir2IrConversionScope.getSafeCallSubjectVariableStack().add(irVariable);
            }
            try {
                FirStatement selector = safeCallExpression.getSelector();
                FirExpression firExpression = selector instanceof FirExpression ? (FirExpression) selector : null;
                if (firExpression == null || (irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default = convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, firExpression, false, null, 6, null)) == null) {
                    Object objAccept = safeCallExpression.getSelector().accept(this, data);
                    objAccept.getClass();
                    irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default = (IrExpression) objAccept;
                }
                return IrElementsCreationUtilsKt.createSafeCallConstruction(this.c, irVariable, irValueSymbol, irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default);
            } finally {
                if (irVariable != null) {
                    fir2IrConversionScope.getSafeCallSubjectVariableStack().remove(fir2IrConversionScope.getSafeCallSubjectVariableStack().size() - 1);
                }
            }
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(safeCallExpression, th);
            wq6.a();
            return null;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:155:0x02e0  */
    /* JADX WARN: Code duplicated, block: B:50:0x010e  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitScript(FirScript script, Object data) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrStatement irStatementIrCompositeImpl;
        int startOffset;
        int endOffset;
        Integer numStartOffsetSkippingComments;
        FirExpression initializer;
        List<FirStatement> statements;
        Name nameIdentifier;
        int i;
        int endOffset2;
        Integer numStartOffsetSkippingComments2;
        script.getClass();
        IrScript cachedIrScript = getDeclarationStorage().getCachedIrScript(script);
        cachedIrScript.getClass();
        cachedIrScript.setParent(this.conversionScope.parentFromStack());
        getDeclarationStorage().enterScope(cachedIrScript.getSymbol());
        List<FirProperty> parameters = script.getParameters();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(parameters, 10));
        Iterator<T> it = parameters.iterator();
        while (it.hasNext()) {
            arrayList.add(getDeclarationStorage().createAndCacheIrVariable((FirProperty) it.next(), cachedIrScript, IrDeclarationOrigin.Companion.getSCRIPT_CALL_PARAMETER()));
        }
        cachedIrScript.setExplicitCallParameters(arrayList);
        List<FirScriptReceiverParameter> receivers = script.getReceivers();
        ArrayList arrayList2 = new ArrayList();
        Iterator<T> it2 = receivers.iterator();
        int i2 = 0;
        while (true) {
            IrValueParameter irValueParameter = null;
            if (!it2.hasNext()) {
                break;
            }
            Object next = it2.next();
            int i3 = i2 + 1;
            if (i2 < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            FirScriptReceiverParameter firScriptReceiverParameter = (FirScriptReceiverParameter) next;
            boolean zIsBaseClassReceiver = firScriptReceiverParameter.getIsBaseClassReceiver();
            if (zIsBaseClassReceiver) {
                nameIdentifier = SpecialNames.THIS;
            } else {
                nameIdentifier = Name.identifier("$script_receiver_" + i2);
                nameIdentifier.getClass();
            }
            Name name = nameIdentifier;
            IrDeclarationOrigin.Companion companion = IrDeclarationOrigin.Companion;
            IrDeclarationOrigin instance_receiver = zIsBaseClassReceiver ? companion.getINSTANCE_RECEIVER() : companion.getSCRIPT_IMPLICIT_RECEIVER();
            KtSourceElement source = firScriptReceiverParameter.getSource();
            if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
                i = -1;
                endOffset2 = -1;
            } else if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                i = -1;
                endOffset2 = -1;
            } else if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                i = -1;
                endOffset2 = -1;
            } else if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                i = -1;
                endOffset2 = -1;
            } else {
                int startOffset2 = (source == null || (numStartOffsetSkippingComments2 = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) ? source != null ? source.getStartOffset() : -1 : numStartOffsetSkippingComments2.intValue();
                endOffset2 = source != null ? source.getEndOffset() : -1;
                i = startOffset2;
            }
            IrValueParameter irValueParameterCreateValueParameter = IrFactoryImpl.INSTANCE.createValueParameter(i, endOffset2, instance_receiver, IrParameterKind.Regular, name, Fir2IrTypeConverterKt.toIrType$default(this, firScriptReceiverParameter.getTypeRef(), (ConversionTypeOrigin) null, 2, (Object) null), false, new IrValueParameterSymbolImpl((ParameterDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null), (IrType) null, false, false, false);
            irValueParameterCreateValueParameter.setParent(cachedIrScript);
            if (!zIsBaseClassReceiver) {
                irValueParameterCreateValueParameter.setIndexInParameters(i2);
            }
            if (zIsBaseClassReceiver) {
                irValueParameterCreateValueParameter.setKind(IrParameterKind.DispatchReceiver);
                cachedIrScript.setThisReceiver(irValueParameterCreateValueParameter);
                cachedIrScript.setBaseClass(irValueParameterCreateValueParameter.getType());
            } else {
                irValueParameter = irValueParameterCreateValueParameter;
            }
            if (irValueParameter != null) {
                arrayList2.add(irValueParameter);
            }
            i2 = i3;
        }
        cachedIrScript.setImplicitReceiversParameters(arrayList2);
        Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
        fir2IrConversionScope.get_parentStack().add(cachedIrScript);
        fir2IrConversionScope.getScopeStack().add(new Scope(cachedIrScript.getSymbol()));
        try {
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            for (FirDeclaration firDeclaration : script.getDeclarations()) {
                if (firDeclaration instanceof FirAnonymousInitializer) {
                    FirBlock body = ((FirAnonymousInitializer) firDeclaration).getBody();
                    if (body != null && (statements = body.getStatements()) != null) {
                        ArrayList arrayList3 = new ArrayList();
                        Iterator<T> it3 = statements.iterator();
                        while (it3.hasNext()) {
                            IrStatement irStatement = toIrStatement((FirStatement) it3.next());
                            if (irStatement != null) {
                                arrayList3.add(irStatement);
                            }
                        }
                        cachedIrScript.getStatements().addAll(arrayList3);
                    }
                } else if ((firDeclaration instanceof FirProperty) && Intrinsics.areEqual(((FirProperty) firDeclaration).getName(), SpecialNames.UNDERSCORE_FOR_UNUSED_VAR)) {
                    IrElement irElement = (!isUnnamedLocalVariable((FirProperty) firDeclaration) || (initializer = ((FirProperty) firDeclaration).getInitializer()) == null) ? null : (IrElement) initializer.accept(this, null);
                    if (!(irElement instanceof IrStatement) || DestructuringDeclarationAttributesKt.getDestructuringDeclarationContainerVariable((FirProperty) firDeclaration) == null) {
                        irStatementIrCompositeImpl = irElement instanceof IrStatement ? (IrStatement) irElement : null;
                        if (irStatementIrCompositeImpl != null) {
                            List statements2 = cachedIrScript.getStatements();
                            irStatementIrCompositeImpl.getClass();
                            statements2.add(irStatementIrCompositeImpl);
                        }
                    } else {
                        FirVariableSymbol<?> destructuringDeclarationContainerVariable = DestructuringDeclarationAttributesKt.getDestructuringDeclarationContainerVariable((FirProperty) firDeclaration);
                        destructuringDeclarationContainerVariable.getClass();
                        Object obj = linkedHashMap.get(destructuringDeclarationContainerVariable);
                        obj.getClass();
                        ((IrComposite) obj).getStatements().add(irElement);
                    }
                } else {
                    if ((firDeclaration instanceof FirProperty) && Intrinsics.areEqual(((FirProperty) firDeclaration).getOrigin(), FirDeclarationOrigin.ScriptCustomization.ResultProperty.INSTANCE)) {
                        FirTypeRef returnTypeRef = ((FirProperty) firDeclaration).getReturnTypeRef();
                        if (FirTypeUtilsKt.isUnit(returnTypeRef) || FirTypeUtilsKt.isNothing(returnTypeRef) || FirTypeUtilsKt.isNullableNothing(returnTypeRef)) {
                            FirExpression initializer2 = ((FirProperty) firDeclaration).getInitializer();
                            initializer2.getClass();
                            irStatementIrCompositeImpl = toIrStatement(initializer2);
                        } else {
                            Object objAccept = ((FirProperty) firDeclaration).accept(this, null);
                            IrDeclaration irDeclaration = objAccept instanceof IrDeclaration ? (IrDeclaration) objAccept : null;
                            if (irDeclaration != null) {
                                IrProperty irProperty = irDeclaration instanceof IrProperty ? (IrProperty) irDeclaration : null;
                                cachedIrScript.setResultProperty(irProperty != null ? irProperty.getSymbol() : null);
                            } else {
                                irDeclaration = null;
                            }
                            irStatementIrCompositeImpl = (IrStatement) irDeclaration;
                        }
                    } else if ((firDeclaration instanceof FirVariable) && Intrinsics.areEqual(DestructuringDeclarationAttributesKt.isDestructuringDeclarationContainerVariable((FirVariable) firDeclaration), Boolean.TRUE)) {
                        TokenSet tokenSet = firDeclaration instanceof FirNamedFunction ? OffsetUtilsKt.FUNCTION_KEYWORD_TOKENS : firDeclaration instanceof FirConstructor ? OffsetUtilsKt.CONSTRUCTOR_KEYWORD_TOKENS : KtTokens.VAL_VAR;
                        KtSourceElement source2 = firDeclaration.getSource();
                        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source2))) {
                            startOffset = -1;
                            endOffset = -1;
                        } else if (Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                            startOffset = -1;
                            endOffset = -1;
                        } else if (Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                            startOffset = -1;
                            endOffset = -1;
                        } else if (Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                            startOffset = -1;
                            endOffset = -1;
                        } else {
                            startOffset = (source2 == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source2, tokenSet)) == null) ? source2 != null ? source2.getStartOffset() : -1 : numStartOffsetSkippingComments.intValue();
                            endOffset = source2 != null ? source2.getEndOffset() : -1;
                        }
                        irStatementIrCompositeImpl = BuildersKt.IrCompositeImpl(startOffset, endOffset, getBuiltins().getUnitType(), IrStatementOrigin.Companion.getDESTRUCTURING_DECLARATION());
                        List statements3 = irStatementIrCompositeImpl.getStatements();
                        IrVariable irVariableCreateAndCacheIrVariable$default = Fir2IrDeclarationStorage.createAndCacheIrVariable$default(getDeclarationStorage(), (FirVariable) firDeclaration, this.conversionScope.parentFromStack(), null, 4, null);
                        FirExpression initializer3 = ((FirVariable) firDeclaration).getInitializer();
                        IrStatement irStatement2 = initializer3 != null ? toIrStatement(initializer3) : null;
                        irVariableCreateAndCacheIrVariable$default.setInitializer(irStatement2 instanceof IrExpression ? (IrExpression) irStatement2 : null);
                        statements3.add(irVariableCreateAndCacheIrVariable$default);
                        linkedHashMap.put(((FirVariable) firDeclaration).getSymbol(), irStatementIrCompositeImpl);
                    } else if ((firDeclaration instanceof FirProperty) && DestructuringDeclarationAttributesKt.getDestructuringDeclarationContainerVariable((FirProperty) firDeclaration) != null) {
                        Object objAccept2 = ((FirProperty) firDeclaration).accept(this, null);
                        objAccept2.getClass();
                        irStatementIrCompositeImpl = (IrProperty) objAccept2;
                        int startOffset3 = irStatementIrCompositeImpl.getStartOffset();
                        int endOffset3 = irStatementIrCompositeImpl.getEndOffset();
                        IrField backingField = irStatementIrCompositeImpl.getBackingField();
                        backingField.getClass();
                        IrSetFieldImpl IrSetFieldImpl = BuildersKt.IrSetFieldImpl(startOffset3, endOffset3, backingField.getSymbol(), getBuiltins().getUnitType(), (IrStatementOrigin) null, (IrClassSymbol) null);
                        IrField backingField2 = irStatementIrCompositeImpl.getBackingField();
                        backingField2.getClass();
                        IrExpressionBody initializer4 = backingField2.getInitializer();
                        initializer4.getClass();
                        IrSetFieldImpl.setValue(initializer4.getExpression());
                        IrSetFieldImpl.setReceiver((IrExpression) null);
                        FirVariableSymbol<?> destructuringDeclarationContainerVariable2 = DestructuringDeclarationAttributesKt.getDestructuringDeclarationContainerVariable((FirProperty) firDeclaration);
                        destructuringDeclarationContainerVariable2.getClass();
                        Object obj2 = linkedHashMap.get(destructuringDeclarationContainerVariable2);
                        obj2.getClass();
                        ((IrComposite) obj2).getStatements().add(IrSetFieldImpl);
                        IrField backingField3 = irStatementIrCompositeImpl.getBackingField();
                        backingField3.getClass();
                        backingField3.setInitializer((IrExpressionBody) null);
                    } else if (firDeclaration instanceof FirClass) {
                        Object objAccept3 = ((FirClass) firDeclaration).accept(this, null);
                        objAccept3.getClass();
                        irStatementIrCompositeImpl = (IrClass) objAccept3;
                    } else {
                        Object objAccept4 = firDeclaration.accept(this, null);
                        irStatementIrCompositeImpl = objAccept4 instanceof IrDeclaration ? (IrDeclaration) objAccept4 : null;
                    }
                    List statements4 = cachedIrScript.getStatements();
                    irStatementIrCompositeImpl.getClass();
                    statements4.add(irStatementIrCompositeImpl);
                }
            }
            Unit unit = Unit.INSTANCE;
            fir2IrConversionScope.getScopeStack().remove(fir2IrConversionScope.getScopeStack().size() - 1);
            fir2IrConversionScope.get_parentStack().remove(fir2IrConversionScope.get_parentStack().size() - 1);
            Iterator<Fir2IrScriptConfiguratorExtension> it4 = Fir2IrScriptConfiguratorExtensionKt.getFir2IrScriptConfigurators(FirExtensionServiceKt.getExtensionService(getSession())).iterator();
            while (it4.hasNext()) {
                it4.next().configure(cachedIrScript, script, new Function1() { // from class: xw4
                    public final Object invoke(Object obj3) {
                        return Fir2IrVisitor.visitScript$lambda$0$3$0(this.b, (FirScriptSymbol) obj3);
                    }
                });
                Unit unit2 = Unit.INSTANCE;
            }
            getDeclarationStorage().leaveScope(cachedIrScript.getSymbol());
            return cachedIrScript;
        } catch (Throwable th) {
            fir2IrConversionScope.getScopeStack().remove(fir2IrConversionScope.getScopeStack().size() - 1);
            fir2IrConversionScope.get_parentStack().remove(fir2IrConversionScope.get_parentStack().size() - 1);
            throw th;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitSmartCastExpression(FirSmartCastExpression smartCastExpression, Object data) throws KotlinIllegalArgumentExceptionWithAttachments {
        smartCastExpression.getClass();
        return getImplicitCastInserter().handleSmartCastExpression(smartCastExpression, convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, smartCastExpression.getOriginalExpression(), false, null, 6, null));
    }

    /* JADX WARN: Code duplicated, block: B:33:0x0069  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitStringConcatenationCall(FirStringConcatenationCall stringConcatenationCall, Object data) {
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        stringConcatenationCall.getClass();
        FirSession session = getSession();
        try {
            KtSourceElement source = stringConcatenationCall.getSource();
            int startOffset2 = -1;
            if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
                endOffset = -1;
                startOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                    endOffset = -1;
                    startOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                        endOffset = -1;
                        startOffset = -1;
                    } else {
                        if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                            endOffset = -1;
                            startOffset = -1;
                        } else {
                            if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                                startOffset = source != null ? source.getStartOffset() : -1;
                            } else {
                                startOffset = numStartOffsetSkippingComments.intValue();
                            }
                            endOffset = source != null ? source.getEndOffset() : -1;
                        }
                    }
                }
            }
            ArrayList arrayList = new ArrayList();
            StringBuilder sb = new StringBuilder();
            Iterator<FirExpression> it = stringConcatenationCall.getArgumentList().getArguments().iterator();
            int endOffset2 = -1;
            while (it.hasNext()) {
                IrConst irConstConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default = convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, it.next(), false, null, 6, null);
                if ((irConstConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default instanceof IrConst) && Intrinsics.areEqual(irConstConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default.getKind(), IrConstKind.String.INSTANCE)) {
                    if (sb.length() == 0) {
                        startOffset2 = irConstConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default.getStartOffset();
                    }
                    sb.append(irConstConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default.getValue());
                    endOffset2 = irConstConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default.getEndOffset();
                } else {
                    if (sb.length() > 0) {
                        arrayList.add(IrConstImpl.Companion.string(startOffset2, endOffset2, getBuiltins().getStringType(), sb.toString()));
                        StringsKt.clear(sb);
                    }
                    arrayList.add(irConstConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default);
                }
            }
            if (sb.length() > 0) {
                arrayList.add(IrConstImpl.Companion.string(startOffset2, endOffset2, getBuiltins().getStringType(), sb.toString()));
            }
            return BuildersKt.IrStringConcatenationImpl(startOffset, endOffset, getBuiltins().getStringType(), arrayList);
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(stringConcatenationCall, th);
            wq6.a();
            return null;
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitThisReceiverExpression(FirThisReceiverExpression thisReceiverExpression, Object data) {
        IrElement irElementGenerateThisReceiverAccessForCallable;
        thisReceiverExpression.getClass();
        FirSession session = getSession();
        try {
            FirThisReference calleeReference = thisReceiverExpression.getCalleeReference();
            IrExpression irExpressionInjectGetValueCall$org_jetbrains_kotlin_fir2ir = getCallGenerator().injectGetValueCall$org_jetbrains_kotlin_fir2ir(thisReceiverExpression, calleeReference);
            if (irExpressionInjectGetValueCall$org_jetbrains_kotlin_fir2ir != null) {
                return irExpressionInjectGetValueCall$org_jetbrains_kotlin_fir2ir;
            }
            FirBasedSymbol<?> referencedMemberSymbol = ResolveUtilsKt.getReferencedMemberSymbol(calleeReference);
            if (referencedMemberSymbol instanceof FirClassSymbol) {
                irElementGenerateThisReceiverAccessForCallable = generateThisReceiverAccessForClass(thisReceiverExpression, (FirClassSymbol) referencedMemberSymbol);
            } else if (referencedMemberSymbol instanceof FirScriptSymbol) {
                irElementGenerateThisReceiverAccessForCallable = generateThisReceiverAccessForScript(thisReceiverExpression, (FirScriptSymbol) referencedMemberSymbol);
            } else if (referencedMemberSymbol instanceof FirReplSnippetSymbol) {
                irElementGenerateThisReceiverAccessForCallable = generateThisReceiverAccessForReplSnippet(thisReceiverExpression, (FirReplSnippetSymbol) referencedMemberSymbol);
            } else {
                irElementGenerateThisReceiverAccessForCallable = referencedMemberSymbol instanceof FirCallableSymbol ? generateThisReceiverAccessForCallable(thisReceiverExpression, (FirCallableSymbol) referencedMemberSymbol) : null;
            }
            return irElementGenerateThisReceiverAccessForCallable == null ? visitQualifiedAccessExpression((FirQualifiedAccessExpression) thisReceiverExpression, data) : irElementGenerateThisReceiverAccessForCallable;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(thisReceiverExpression, th);
            wq6.a();
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0063  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitThrowExpression(FirThrowExpression throwExpression, Object data) {
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        throwExpression.getClass();
        KtSourceElement source = throwExpression.getSource();
        int i = -1;
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        i = startOffset;
                        endOffset = source != null ? source.getEndOffset() : -1;
                    }
                }
            }
        }
        return BuildersKt.IrThrowImpl(i, endOffset, getBuiltins().getNothingType(), convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, throwExpression.getException(), false, getSession().getBuiltinTypes().getThrowableType().getConeType(), 2, null));
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0064  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitTryExpression(FirTryExpression tryExpression, Object data) {
        int i;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        tryExpression.getClass();
        KtSourceElement source = tryExpression.getSource();
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            i = -1;
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                i = -1;
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    i = -1;
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        i = -1;
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        i = startOffset;
                        endOffset = source != null ? source.getEndOffset() : -1;
                    }
                }
            }
        }
        IrType irType$default = Fir2IrTypeConverterKt.toIrType$default(this, FirTypeUtilsKt.getResolvedType(tryExpression), (ConversionTypeOrigin) null, 2, (Object) null);
        IrExpression irExpressionPrepareExpressionForGivenExpectedType$default = ImplicitConversionUtilsKt.prepareExpressionForGivenExpectedType$default(this, convertToIrBlock(tryExpression.getTryBlock(), null, FirTypeUtilsKt.getResolvedType(tryExpression.getTryBlock())), tryExpression.getTryBlock(), null, FirTypeUtilsKt.getResolvedType(tryExpression), null, false, 20, null);
        List<FirCatch> catches = tryExpression.getCatches();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(catches, 10));
        Iterator<T> it = catches.iterator();
        while (it.hasNext()) {
            arrayList.add(convertCatch((FirCatch) it.next(), FirTypeUtilsKt.getResolvedType(tryExpression)));
        }
        FirBlock finallyBlock = tryExpression.getFinallyBlock();
        return BuildersKt.IrTryImpl(i, endOffset, irType$default, irExpressionPrepareExpressionForGivenExpectedType$default, arrayList, finallyBlock != null ? convertToIrBlock(finallyBlock, null, this.unitType) : null);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitTypeAlias(FirTypeAlias typeAlias, Object data) {
        typeAlias.getClass();
        FirSession session = getSession();
        try {
            IrTypeAlias cachedTypeAlias$org_jetbrains_kotlin_fir2ir = getClassifierStorage().getCachedTypeAlias$org_jetbrains_kotlin_fir2ir(typeAlias);
            cachedTypeAlias$org_jetbrains_kotlin_fir2ir.getClass();
            getAnnotationGenerator().generate((IrMutableAnnotationContainer) cachedTypeAlias$org_jetbrains_kotlin_fir2ir, (FirAnnotationContainer) typeAlias);
            return cachedTypeAlias$org_jetbrains_kotlin_fir2ir;
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(typeAlias, th);
            wq6.a();
            return null;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: kotlin.NotImplementedError */
    /* JADX WARN: Code duplicated, block: B:30:0x0062  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitTypeOperatorCall(FirTypeOperatorCall typeOperatorCall, Object data) throws NotImplementedError {
        int i;
        int endOffset;
        Pair pair;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        typeOperatorCall.getClass();
        KtSourceElement source = typeOperatorCall.getSource();
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            i = -1;
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                i = -1;
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    i = -1;
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        i = -1;
                        endOffset = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        i = startOffset;
                        endOffset = source != null ? source.getEndOffset() : -1;
                    }
                }
            }
        }
        IrType irType$default = Fir2IrTypeConverterKt.toIrType$default(this, typeOperatorCall.getConversionTypeRef(), (ConversionTypeOrigin) null, 2, (Object) null);
        int i2 = WhenMappings.$EnumSwitchMapping$0[typeOperatorCall.getOperation().ordinal()];
        if (i2 == 1) {
            pair = TuplesKt.to(getBuiltins().getBooleanType(), IrTypeOperator.INSTANCEOF);
        } else if (i2 == 2) {
            pair = TuplesKt.to(getBuiltins().getBooleanType(), IrTypeOperator.NOT_INSTANCEOF);
        } else if (i2 == 3) {
            pair = TuplesKt.to(irType$default, IrTypeOperator.CAST);
        } else {
            if (i2 != 4) {
                e2f.a("An operation is not implemented: ".concat("Should not be here: " + typeOperatorCall.getOperation() + " in type operator call"));
                return null;
            }
            pair = TuplesKt.to(IrTypesKt.makeNullable(irType$default), IrTypeOperator.SAFE_CAST);
        }
        return BuildersKt.IrTypeOperatorCallImpl(i, endOffset, (IrType) pair.component1(), (IrTypeOperator) pair.component2(), irType$default, convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, (FirExpression) CollectionsKt.first(typeOperatorCall.getArgumentList().getArguments()), false, null, 6, null));
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0062  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitVarargArgumentsExpression(FirVarargArgumentsExpression varargArgumentsExpression, Object data) {
        int endOffset;
        IrType irType$default;
        List elements;
        IrVarargElement expression;
        Integer numStartOffsetSkippingComments;
        varargArgumentsExpression.getClass();
        KtSourceElement source = varargArgumentsExpression.getSource();
        int i = -1;
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            endOffset = -1;
        } else if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
            endOffset = -1;
        } else if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
            endOffset = -1;
        } else if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
            endOffset = -1;
        } else {
            int startOffset = (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) ? source != null ? source.getStartOffset() : -1 : numStartOffsetSkippingComments.intValue();
            endOffset = source != null ? source.getEndOffset() : -1;
            i = startOffset;
        }
        IrType irType$default2 = Fir2IrTypeConverterKt.toIrType$default(this, FirTypeUtilsKt.getResolvedType(varargArgumentsExpression), (ConversionTypeOrigin) null, 2, (Object) null);
        ConeKotlinType coneElementTypeOrNull = varargArgumentsExpression.getConeElementTypeOrNull();
        if (coneElementTypeOrNull == null || (irType$default = Fir2IrTypeConverterKt.toIrType$default(this, coneElementTypeOrNull, (ConversionTypeOrigin) null, 2, (Object) null)) == null) {
            f2f.a("Vararg expression has incorrect type: ", UtilsKt.render(varargArgumentsExpression));
            return null;
        }
        List<FirExpression> arguments = varargArgumentsExpression.getArguments();
        ArrayList arrayList = new ArrayList();
        for (Object obj : arguments) {
            if (!isGetClassOfUnresolvedTypeInAnnotation$org_jetbrains_kotlin_fir2ir((FirExpression) obj)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            IrVarargElement irVarargElementConvertToIrVarargElement = convertToIrVarargElement((FirExpression) it.next());
            if (get_annotationMode()) {
                IrSpreadElement irSpreadElement = irVarargElementConvertToIrVarargElement instanceof IrSpreadElement ? (IrSpreadElement) irVarargElementConvertToIrVarargElement : null;
                if (irSpreadElement != null && (expression = irSpreadElement.getExpression()) != null) {
                    irVarargElementConvertToIrVarargElement = expression;
                }
                elements = irVarargElementConvertToIrVarargElement instanceof IrVararg ? ((IrVararg) irVarargElementConvertToIrVarargElement).getElements() : CollectionsKt.listOf(irVarargElementConvertToIrVarargElement);
            } else {
                elements = CollectionsKt.listOf(irVarargElementConvertToIrVarargElement);
            }
            CollectionsKt.addAll(arrayList2, elements);
        }
        return BuildersKt.IrVarargImpl(i, endOffset, irType$default2, irType$default, arrayList2);
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrExpression visitVariableAssignment(FirVariableAssignment variableAssignment, Object data) {
        IrExpression irExpressionConvertToIrReceiverExpression$org_jetbrains_kotlin_fir2ir;
        variableAssignment.getClass();
        FirSession session = getSession();
        try {
            FirExpression explicitReceiver = FirExpressionUtilKt.getExplicitReceiver(variableAssignment);
            if (explicitReceiver != null) {
                FirQualifiedAccessExpression firQualifiedAccessExpressionUnwrapLValue = FirExpressionUtilKt.unwrapLValue(variableAssignment);
                firQualifiedAccessExpressionUnwrapLValue.getClass();
                irExpressionConvertToIrReceiverExpression$org_jetbrains_kotlin_fir2ir = convertToIrReceiverExpression$org_jetbrains_kotlin_fir2ir(explicitReceiver, firQualifiedAccessExpressionUnwrapLValue);
            } else {
                irExpressionConvertToIrReceiverExpression$org_jetbrains_kotlin_fir2ir = null;
            }
            return getCallGenerator().convertToIrSetCall(variableAssignment, irExpressionConvertToIrReceiverExpression$org_jetbrains_kotlin_fir2ir);
        } catch (Throwable th) {
            UtilsKt.getExceptionHandler(session).handleExceptionOnElementAnalysis(variableAssignment, th);
            wq6.a();
            return null;
        }
    }

    /* JADX WARN: Code duplicated, block: B:25:0x0067  */
    /* JADX WARN: Code duplicated, block: B:31:0x007c  */
    /* JADX WARN: Code duplicated, block: B:35:0x0095 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:36:0x0097 A[Catch: all -> 0x009c, TryCatch #0 {all -> 0x009c, blocks: (B:33:0x0086, B:36:0x0097, B:40:0x00a0, B:43:0x00aa, B:45:0x00b0, B:48:0x00ba, B:50:0x00c0, B:54:0x00cb, B:56:0x00d1, B:61:0x00e0, B:64:0x00e9, B:66:0x00f3, B:67:0x0105, B:69:0x010b, B:71:0x0112, B:73:0x0129, B:75:0x0133, B:84:0x0157, B:85:0x0194, B:78:0x013d, B:79:0x0141, B:81:0x0147, B:70:0x0110, B:58:0x00d8), top: B:92:0x0086 }] */
    /* JADX WARN: Code duplicated, block: B:39:0x009f  */
    /* JADX WARN: Code duplicated, block: B:42:0x00a8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:43:0x00aa A[Catch: all -> 0x009c, TryCatch #0 {all -> 0x009c, blocks: (B:33:0x0086, B:36:0x0097, B:40:0x00a0, B:43:0x00aa, B:45:0x00b0, B:48:0x00ba, B:50:0x00c0, B:54:0x00cb, B:56:0x00d1, B:61:0x00e0, B:64:0x00e9, B:66:0x00f3, B:67:0x0105, B:69:0x010b, B:71:0x0112, B:73:0x0129, B:75:0x0133, B:84:0x0157, B:85:0x0194, B:78:0x013d, B:79:0x0141, B:81:0x0147, B:70:0x0110, B:58:0x00d8), top: B:92:0x0086 }] */
    /* JADX WARN: Code duplicated, block: B:44:0x00af  */
    /* JADX WARN: Code duplicated, block: B:47:0x00b8 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:48:0x00ba A[Catch: all -> 0x009c, TryCatch #0 {all -> 0x009c, blocks: (B:33:0x0086, B:36:0x0097, B:40:0x00a0, B:43:0x00aa, B:45:0x00b0, B:48:0x00ba, B:50:0x00c0, B:54:0x00cb, B:56:0x00d1, B:61:0x00e0, B:64:0x00e9, B:66:0x00f3, B:67:0x0105, B:69:0x010b, B:71:0x0112, B:73:0x0129, B:75:0x0133, B:84:0x0157, B:85:0x0194, B:78:0x013d, B:79:0x0141, B:81:0x0147, B:70:0x0110, B:58:0x00d8), top: B:92:0x0086 }] */
    /* JADX WARN: Code duplicated, block: B:49:0x00bf  */
    /* JADX WARN: Code duplicated, block: B:52:0x00c8  */
    /* JADX WARN: Code duplicated, block: B:53:0x00c9  */
    /* JADX WARN: Code duplicated, block: B:57:0x00d6 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:58:0x00d8 A[Catch: all -> 0x009c, TryCatch #0 {all -> 0x009c, blocks: (B:33:0x0086, B:36:0x0097, B:40:0x00a0, B:43:0x00aa, B:45:0x00b0, B:48:0x00ba, B:50:0x00c0, B:54:0x00cb, B:56:0x00d1, B:61:0x00e0, B:64:0x00e9, B:66:0x00f3, B:67:0x0105, B:69:0x010b, B:71:0x0112, B:73:0x0129, B:75:0x0133, B:84:0x0157, B:85:0x0194, B:78:0x013d, B:79:0x0141, B:81:0x0147, B:70:0x0110, B:58:0x00d8), top: B:92:0x0086 }] */
    /* JADX WARN: Code duplicated, block: B:59:0x00dd  */
    /* JADX WARN: Code duplicated, block: B:61:0x00e0 A[Catch: all -> 0x009c, TryCatch #0 {all -> 0x009c, blocks: (B:33:0x0086, B:36:0x0097, B:40:0x00a0, B:43:0x00aa, B:45:0x00b0, B:48:0x00ba, B:50:0x00c0, B:54:0x00cb, B:56:0x00d1, B:61:0x00e0, B:64:0x00e9, B:66:0x00f3, B:67:0x0105, B:69:0x010b, B:71:0x0112, B:73:0x0129, B:75:0x0133, B:84:0x0157, B:85:0x0194, B:78:0x013d, B:79:0x0141, B:81:0x0147, B:70:0x0110, B:58:0x00d8), top: B:92:0x0086 }] */
    /* JADX WARN: Code duplicated, block: B:63:0x00e7  */
    /* JADX WARN: Code duplicated, block: B:66:0x00f3 A[Catch: all -> 0x009c, TryCatch #0 {all -> 0x009c, blocks: (B:33:0x0086, B:36:0x0097, B:40:0x00a0, B:43:0x00aa, B:45:0x00b0, B:48:0x00ba, B:50:0x00c0, B:54:0x00cb, B:56:0x00d1, B:61:0x00e0, B:64:0x00e9, B:66:0x00f3, B:67:0x0105, B:69:0x010b, B:71:0x0112, B:73:0x0129, B:75:0x0133, B:84:0x0157, B:85:0x0194, B:78:0x013d, B:79:0x0141, B:81:0x0147, B:70:0x0110, B:58:0x00d8), top: B:92:0x0086 }] */
    /* JADX WARN: Code duplicated, block: B:67:0x0105 A[Catch: all -> 0x009c, TryCatch #0 {all -> 0x009c, blocks: (B:33:0x0086, B:36:0x0097, B:40:0x00a0, B:43:0x00aa, B:45:0x00b0, B:48:0x00ba, B:50:0x00c0, B:54:0x00cb, B:56:0x00d1, B:61:0x00e0, B:64:0x00e9, B:66:0x00f3, B:67:0x0105, B:69:0x010b, B:71:0x0112, B:73:0x0129, B:75:0x0133, B:84:0x0157, B:85:0x0194, B:78:0x013d, B:79:0x0141, B:81:0x0147, B:70:0x0110, B:58:0x00d8), top: B:92:0x0086 }] */
    /* JADX WARN: Code duplicated, block: B:69:0x010b A[Catch: all -> 0x009c, TryCatch #0 {all -> 0x009c, blocks: (B:33:0x0086, B:36:0x0097, B:40:0x00a0, B:43:0x00aa, B:45:0x00b0, B:48:0x00ba, B:50:0x00c0, B:54:0x00cb, B:56:0x00d1, B:61:0x00e0, B:64:0x00e9, B:66:0x00f3, B:67:0x0105, B:69:0x010b, B:71:0x0112, B:73:0x0129, B:75:0x0133, B:84:0x0157, B:85:0x0194, B:78:0x013d, B:79:0x0141, B:81:0x0147, B:70:0x0110, B:58:0x00d8), top: B:92:0x0086 }] */
    /* JADX WARN: Code duplicated, block: B:70:0x0110 A[Catch: all -> 0x009c, TryCatch #0 {all -> 0x009c, blocks: (B:33:0x0086, B:36:0x0097, B:40:0x00a0, B:43:0x00aa, B:45:0x00b0, B:48:0x00ba, B:50:0x00c0, B:54:0x00cb, B:56:0x00d1, B:61:0x00e0, B:64:0x00e9, B:66:0x00f3, B:67:0x0105, B:69:0x010b, B:71:0x0112, B:73:0x0129, B:75:0x0133, B:84:0x0157, B:85:0x0194, B:78:0x013d, B:79:0x0141, B:81:0x0147, B:70:0x0110, B:58:0x00d8), top: B:92:0x0086 }] */
    /* JADX WARN: Code duplicated, block: B:73:0x0129 A[Catch: all -> 0x009c, TryCatch #0 {all -> 0x009c, blocks: (B:33:0x0086, B:36:0x0097, B:40:0x00a0, B:43:0x00aa, B:45:0x00b0, B:48:0x00ba, B:50:0x00c0, B:54:0x00cb, B:56:0x00d1, B:61:0x00e0, B:64:0x00e9, B:66:0x00f3, B:67:0x0105, B:69:0x010b, B:71:0x0112, B:73:0x0129, B:75:0x0133, B:84:0x0157, B:85:0x0194, B:78:0x013d, B:79:0x0141, B:81:0x0147, B:70:0x0110, B:58:0x00d8), top: B:92:0x0086 }] */
    /* JADX WARN: Code duplicated, block: B:78:0x013d A[Catch: all -> 0x009c, TryCatch #0 {all -> 0x009c, blocks: (B:33:0x0086, B:36:0x0097, B:40:0x00a0, B:43:0x00aa, B:45:0x00b0, B:48:0x00ba, B:50:0x00c0, B:54:0x00cb, B:56:0x00d1, B:61:0x00e0, B:64:0x00e9, B:66:0x00f3, B:67:0x0105, B:69:0x010b, B:71:0x0112, B:73:0x0129, B:75:0x0133, B:84:0x0157, B:85:0x0194, B:78:0x013d, B:79:0x0141, B:81:0x0147, B:70:0x0110, B:58:0x00d8), top: B:92:0x0086 }] */
    /* JADX WARN: Code duplicated, block: B:81:0x0147 A[Catch: all -> 0x009c, TryCatch #0 {all -> 0x009c, blocks: (B:33:0x0086, B:36:0x0097, B:40:0x00a0, B:43:0x00aa, B:45:0x00b0, B:48:0x00ba, B:50:0x00c0, B:54:0x00cb, B:56:0x00d1, B:61:0x00e0, B:64:0x00e9, B:66:0x00f3, B:67:0x0105, B:69:0x010b, B:71:0x0112, B:73:0x0129, B:75:0x0133, B:84:0x0157, B:85:0x0194, B:78:0x013d, B:79:0x0141, B:81:0x0147, B:70:0x0110, B:58:0x00d8), top: B:92:0x0086 }] */
    /* JADX WARN: Code duplicated, block: B:83:0x0155  */
    /* JADX WARN: Code duplicated, block: B:87:0x01a3  */
    /* JADX WARN: Code duplicated, block: B:93:0x0157 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:94:0x0155 A[SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:95:? A[LOOP:0: B:79:0x0141->B:95:?, LOOP_END, SYNTHETIC] */
    @Override // org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitWhenExpression(FirWhenExpression whenExpression, Object data) {
        IrStatementOriginImpl exclexcl;
        IrStatementOriginImpl irStatementOriginImpl;
        Fir2IrConversionScope fir2IrConversionScope;
        KtSourceElement source;
        int i;
        int i2;
        boolean zIsDeeplyProperlyExhaustive;
        ConeKotlinType resolvedType;
        List<IrBranch> listConvertWhenBranchesTo;
        int i3;
        IrBlockImpl irBlockImplGenerateWhen;
        List<FirWhenBranch> branches;
        Iterator<T> it;
        KtSourceElementKind kind;
        KtSourceElementKind kind2;
        KtSourceElementKind kind3;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        whenExpression.getClass();
        IrVariable irVariableGenerateWhenSubjectVariable = generateWhenSubjectVariable(whenExpression);
        KtSourceElement source2 = whenExpression.getSource();
        IElementType elementType = source2 != null ? source2.getElementType() : null;
        try {
            if (Intrinsics.areEqual(elementType, KtNodeTypes.WHEN)) {
                exclexcl = IrStatementOrigin.Companion.getWHEN();
            } else {
                if (!Intrinsics.areEqual(elementType, KtNodeTypes.IF)) {
                    if (Intrinsics.areEqual(elementType, KtNodeTypes.BINARY_EXPRESSION)) {
                        KtSourceElement source3 = whenExpression.getSource();
                        IElementType operationToken = source3 != null ? Fir2IrVisitorKt.getOperationToken(source3) : null;
                        if (Intrinsics.areEqual(operationToken, KtTokens.OROR)) {
                            exclexcl = IrStatementOrigin.Companion.getOROR();
                        } else if (Intrinsics.areEqual(operationToken, KtTokens.ANDAND)) {
                            exclexcl = IrStatementOrigin.Companion.getANDAND();
                        } else {
                            irStatementOriginImpl = null;
                        }
                    } else if (Intrinsics.areEqual(elementType, KtNodeTypes.POSTFIX_EXPRESSION)) {
                        exclexcl = IrStatementOrigin.Companion.getEXCLEXCL();
                    } else {
                        irStatementOriginImpl = null;
                    }
                    fir2IrConversionScope = this.conversionScope;
                    if (irVariableGenerateWhenSubjectVariable != null) {
                        fir2IrConversionScope.getWhenSubjectVariableStack().add(irVariableGenerateWhenSubjectVariable);
                    }
                    source = whenExpression.getSource();
                    if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
                        i = -1;
                        i2 = -1;
                    } else {
                        if (source != null) {
                            kind = source.getKind();
                        } else {
                            kind = null;
                        }
                        if (Intrinsics.areEqual(kind, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                            i = -1;
                            i2 = -1;
                        } else {
                            if (source != null) {
                                kind2 = source.getKind();
                            } else {
                                kind2 = null;
                            }
                            if (Intrinsics.areEqual(kind2, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                                i = -1;
                                i2 = -1;
                            } else {
                                if (source != null) {
                                    kind3 = source.getKind();
                                } else {
                                    kind3 = null;
                                }
                                if (Intrinsics.areEqual(kind3, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                                    i = -1;
                                    i2 = -1;
                                } else {
                                    if (source == null && (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) != null) {
                                        startOffset = numStartOffsetSkippingComments.intValue();
                                    } else if (source != null) {
                                        startOffset = source.getStartOffset();
                                    } else {
                                        startOffset = -1;
                                    }
                                    int endOffset = source != null ? source.getEndOffset() : -1;
                                    i = startOffset;
                                    i2 = endOffset;
                                }
                            }
                        }
                    }
                    if (whenExpression.getBranches().isEmpty()) {
                        irBlockImplGenerateWhen = BuildersKt.IrBlockImpl(i, i2, getBuiltins().getUnitType(), irStatementOriginImpl, CollectionsKt.listOfNotNull(irVariableGenerateWhenSubjectVariable));
                    } else {
                        zIsDeeplyProperlyExhaustive = isDeeplyProperlyExhaustive(whenExpression);
                        if (zIsDeeplyProperlyExhaustive) {
                            resolvedType = FirTypeUtilsKt.getResolvedType(whenExpression);
                        } else {
                            resolvedType = this.unitType;
                        }
                        listConvertWhenBranchesTo = convertWhenBranchesTo(whenExpression, new ArrayList(), resolvedType, Intrinsics.areEqual(irStatementOriginImpl, IrStatementOrigin.Companion.getIF()));
                        if (zIsDeeplyProperlyExhaustive) {
                            branches = whenExpression.getBranches();
                            if ((branches instanceof Collection) || !branches.isEmpty()) {
                                it = branches.iterator();
                                while (true) {
                                    if (it.hasNext()) {
                                        if (((FirWhenBranch) it.next()).getCondition() instanceof FirElseIfTrueCondition) {
                                            i3 = i;
                                        }
                                    }
                                }
                            }
                            i3 = i;
                            listConvertWhenBranchesTo.add(BuildersKt.IrElseBranchImpl(IrConstImpl.Companion.boolean(i3, i2, getBuiltins().getBooleanType(), true), BuildersKt.IrCallImplWithShape$default(i3, i2, getBuiltins().getNothingType(), getBuiltins().getNoWhenBranchMatchedExceptionSymbol(), 0, 0, 0, false, false, (IrStatementOrigin) null, (IrClassSymbol) null, 1536, (Object) null)));
                        } else {
                            i3 = i;
                        }
                        irBlockImplGenerateWhen = generateWhen(i3, i2, irStatementOriginImpl, irVariableGenerateWhenSubjectVariable, listConvertWhenBranchesTo, Fir2IrTypeConverterKt.toIrType$default(this, resolvedType, (ConversionTypeOrigin) null, 2, (Object) null));
                    }
                    if (irVariableGenerateWhenSubjectVariable != null) {
                        fir2IrConversionScope.getWhenSubjectVariableStack().remove(fir2IrConversionScope.getWhenSubjectVariableStack().size() - 1);
                    }
                    return irBlockImplGenerateWhen;
                }
                exclexcl = IrStatementOrigin.Companion.getIF();
            }
            source = whenExpression.getSource();
            if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
                i = -1;
                i2 = -1;
            } else {
                if (source != null) {
                    kind = source.getKind();
                } else {
                    kind = null;
                }
                if (Intrinsics.areEqual(kind, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                    i = -1;
                    i2 = -1;
                } else {
                    if (source != null) {
                        kind2 = source.getKind();
                    } else {
                        kind2 = null;
                    }
                    if (Intrinsics.areEqual(kind2, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                        i = -1;
                        i2 = -1;
                    } else {
                        if (source != null) {
                            kind3 = source.getKind();
                        } else {
                            kind3 = null;
                        }
                        if (Intrinsics.areEqual(kind3, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                            i = -1;
                            i2 = -1;
                        } else {
                            if (source == null) {
                                if (source != null) {
                                    startOffset = source.getStartOffset();
                                } else {
                                    startOffset = -1;
                                }
                            } else if (source != null) {
                                startOffset = source.getStartOffset();
                            } else {
                                startOffset = -1;
                            }
                            if (source != null) {
                            }
                            i = startOffset;
                            i2 = endOffset;
                        }
                    }
                }
            }
            if (whenExpression.getBranches().isEmpty()) {
                irBlockImplGenerateWhen = BuildersKt.IrBlockImpl(i, i2, getBuiltins().getUnitType(), irStatementOriginImpl, CollectionsKt.listOfNotNull(irVariableGenerateWhenSubjectVariable));
            } else {
                zIsDeeplyProperlyExhaustive = isDeeplyProperlyExhaustive(whenExpression);
                if (zIsDeeplyProperlyExhaustive) {
                    resolvedType = FirTypeUtilsKt.getResolvedType(whenExpression);
                } else {
                    resolvedType = this.unitType;
                }
                listConvertWhenBranchesTo = convertWhenBranchesTo(whenExpression, new ArrayList(), resolvedType, Intrinsics.areEqual(irStatementOriginImpl, IrStatementOrigin.Companion.getIF()));
                if (zIsDeeplyProperlyExhaustive) {
                    branches = whenExpression.getBranches();
                    if (branches instanceof Collection) {
                        it = branches.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (((FirWhenBranch) it.next()).getCondition() instanceof FirElseIfTrueCondition) {
                                    i3 = i;
                                }
                            }
                        }
                    } else {
                        it = branches.iterator();
                        while (true) {
                            if (it.hasNext()) {
                                if (((FirWhenBranch) it.next()).getCondition() instanceof FirElseIfTrueCondition) {
                                    i3 = i;
                                }
                            }
                        }
                    }
                    i3 = i;
                    listConvertWhenBranchesTo.add(BuildersKt.IrElseBranchImpl(IrConstImpl.Companion.boolean(i3, i2, getBuiltins().getBooleanType(), true), BuildersKt.IrCallImplWithShape$default(i3, i2, getBuiltins().getNothingType(), getBuiltins().getNoWhenBranchMatchedExceptionSymbol(), 0, 0, 0, false, false, (IrStatementOrigin) null, (IrClassSymbol) null, 1536, (Object) null)));
                } else {
                    i3 = i;
                }
                irBlockImplGenerateWhen = generateWhen(i3, i2, irStatementOriginImpl, irVariableGenerateWhenSubjectVariable, listConvertWhenBranchesTo, Fir2IrTypeConverterKt.toIrType$default(this, resolvedType, (ConversionTypeOrigin) null, 2, (Object) null));
            }
            if (irVariableGenerateWhenSubjectVariable != null) {
                fir2IrConversionScope.getWhenSubjectVariableStack().remove(fir2IrConversionScope.getWhenSubjectVariableStack().size() - 1);
            }
            return irBlockImplGenerateWhen;
        } catch (Throwable th) {
            if (irVariableGenerateWhenSubjectVariable != null) {
                fir2IrConversionScope.getWhenSubjectVariableStack().remove(fir2IrConversionScope.getWhenSubjectVariableStack().size() - 1);
            }
            throw th;
        }
        irStatementOriginImpl = exclexcl;
        fir2IrConversionScope = this.conversionScope;
        if (irVariableGenerateWhenSubjectVariable != null) {
            fir2IrConversionScope.getWhenSubjectVariableStack().add(irVariableGenerateWhenSubjectVariable);
        }
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitWhenSubjectExpression(FirWhenSubjectExpression whenSubjectExpression, Object data) {
        whenSubjectExpression.getClass();
        final IrVariable irVariableLastWhenSubject = this.conversionScope.lastWhenSubject();
        return OffsetUtilsKt.convertWithOffsets((FirQualifiedAccessExpression) whenSubjectExpression, new Function2() { // from class: yw4
            public final Object invoke(Object obj, Object obj2) {
                return Fir2IrVisitor.b(irVariableLastWhenSubject, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
            }
        });
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:30:0x0066  */
    /* JADX WARN: Code duplicated, block: B:76:0x0121  */
    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitWhileLoop(FirWhileLoop whileLoop, Object data) throws KotlinIllegalArgumentExceptionWithAttachments {
        int endOffset;
        int startOffset;
        IrBlockImpl irBlockImplConvertToIrExpressionOrBlock;
        int endOffset2;
        IrVariable irStatement;
        KtSourceElement source;
        Integer numStartOffsetSkippingComments;
        Integer numStartOffsetSkippingComments2;
        whileLoop.getClass();
        IrBlockImpl irBlockImpl = null;
        KtSourceElement source2 = whileLoop.getSource();
        int i = -1;
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source2))) {
            endOffset = -1;
            startOffset = -1;
        } else if (Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
            endOffset = -1;
            startOffset = -1;
        } else if (Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
            endOffset = -1;
            startOffset = -1;
        } else if (Intrinsics.areEqual(source2 != null ? source2.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
            endOffset = -1;
            startOffset = -1;
        } else {
            startOffset = (source2 == null || (numStartOffsetSkippingComments2 = OffsetUtilsKt.startOffsetSkippingComments(source2, null)) == null) ? source2 != null ? source2.getStartOffset() : -1 : numStartOffsetSkippingComments2.intValue();
            endOffset = source2 != null ? source2.getEndOffset() : -1;
        }
        KtSourceElement source3 = whileLoop.getSource();
        boolean zAreEqual = Intrinsics.areEqual(source3 != null ? source3.getElementType() : null, KtNodeTypes.FOR);
        IrStatementOrigin.Companion companion = IrStatementOrigin.Companion;
        IrStatementOriginImpl for_loop_inner_while = zAreEqual ? companion.getFOR_LOOP_INNER_WHILE() : companion.getWHILE_LOOP();
        FirBlock block = whileLoop.getBlock();
        IrLoop irLoopIrWhileLoopImpl = BuildersKt.IrWhileLoopImpl(startOffset, endOffset, getBuiltins().getUnitType(), for_loop_inner_while);
        this.loopMap.put(whileLoop, irLoopIrWhileLoopImpl);
        FirLabel label = whileLoop.getLabel();
        irLoopIrWhileLoopImpl.setLabel(label != null ? label.getName() : null);
        irLoopIrWhileLoopImpl.setCondition(convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, whileLoop.getCondition(), false, null, 6, null));
        if (!(block instanceof FirEmptyExpressionBlock)) {
            if (zAreEqual) {
                KtSourceElement source4 = block.getSource();
                if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source4))) {
                    endOffset2 = -1;
                } else if (Intrinsics.areEqual(source4 != null ? source4.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                    endOffset2 = -1;
                } else if (Intrinsics.areEqual(source4 != null ? source4.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    endOffset2 = -1;
                } else if (Intrinsics.areEqual(source4 != null ? source4.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                    endOffset2 = -1;
                } else {
                    int startOffset2 = (source4 == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source4, null)) == null) ? source4 != null ? source4.getStartOffset() : -1 : numStartOffsetSkippingComments.intValue();
                    endOffset2 = source4 != null ? source4.getEndOffset() : -1;
                    i = startOffset2;
                }
                List<FirStatement> statements = block.getStatements();
                FirStatement firStatement = (FirStatement) CollectionsKt.firstOrNull(statements);
                if (firStatement == null) {
                    f2f.a("Unexpected shape of for loop body: missing body statements: ", UtilsKt.render(whileLoop));
                    return null;
                }
                List listDrop = CollectionsKt.drop(statements, 1);
                ArrayList arrayList = new ArrayList();
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : listDrop) {
                    FirStatement firStatement2 = (FirStatement) obj;
                    if (firStatement2 instanceof FirProperty) {
                        FirExpression initializer = ((FirProperty) firStatement2).getInitializer();
                        if (((initializer == null || (source = initializer.getSource()) == null) ? null : source.getKind()) instanceof KtFakeSourceElementKind.DestructuringInitializer) {
                            arrayList.add(obj);
                        }
                    }
                    arrayList2.add(obj);
                }
                Pair pair = new Pair(arrayList, arrayList2);
                List list = (List) pair.component1();
                List list2 = (List) pair.component2();
                Object objSingleOrNull = CollectionsKt.singleOrNull(list2);
                FirBlock firBlock = objSingleOrNull instanceof FirBlock ? (FirBlock) objSingleOrNull : null;
                if (firBlock == null) {
                    uw4.a("Unexpected shape of for loop body: must be single real loop statement, but got ", list2.size(), ". Loop: ", UtilsKt.render(whileLoop));
                    return null;
                }
                List listCreateListBuilder = CollectionsKt.createListBuilder();
                if ((firStatement instanceof FirProperty) && Intrinsics.areEqual(((FirProperty) firStatement).getName(), SpecialNames.UNDERSCORE_FOR_UNUSED_VAR)) {
                    Scope scope = this.conversionScope.scope();
                    FirExpression initializer2 = ((FirProperty) firStatement).getInitializer();
                    initializer2.getClass();
                    irStatement = Scope.createTemporaryVariable$default(scope, convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, initializer2, false, null, 6, null), "forLoopVariable", false, (IrDeclarationOrigin) null, (IrType) null, 0, 0, false, 252, (Object) null);
                } else {
                    irStatement = toIrStatement(firStatement);
                }
                List list3 = listCreateListBuilder;
                org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(list3, irStatement);
                Iterator it = list.iterator();
                while (it.hasNext()) {
                    org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(list3, toIrStatement((FirStatement) it.next()));
                }
                if (!(firBlock instanceof FirEmptyExpressionBlock)) {
                    listCreateListBuilder.add(convertToIrExpressionOrBlock(firBlock, null, this.unitType));
                }
                irBlockImplConvertToIrExpressionOrBlock = BuildersKt.IrBlockImpl(i, endOffset2, getBuiltins().getUnitType(), for_loop_inner_while, CollectionsKt.build(listCreateListBuilder));
            } else {
                irBlockImplConvertToIrExpressionOrBlock = convertToIrExpressionOrBlock(block, null, this.unitType);
            }
            irBlockImpl = irBlockImplConvertToIrExpressionOrBlock;
        }
        irLoopIrWhileLoopImpl.setBody(irBlockImpl);
        this.loopMap.remove(whileLoop);
        return irLoopIrWhileLoopImpl;
    }

    @Override // org.jetbrains.kotlin.fir.visitors.FirDefaultVisitor, org.jetbrains.kotlin.fir.visitors.FirVisitor
    public IrElement visitWrappedArgumentExpression(FirWrappedArgumentExpression wrappedArgumentExpression, Object data) {
        wrappedArgumentExpression.getClass();
        return convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this, wrappedArgumentExpression.getExpression(), false, null, 6, null);
    }

    public final <T> T withAnnotationMode$org_jetbrains_kotlin_fir2ir(boolean enableAnnotationMode, Function0<? extends T> block) {
        block.getClass();
        boolean z = this._annotationMode;
        this._annotationMode = enableAnnotationMode;
        try {
            return (T) block.invoke();
        } finally {
            InlineMarker.finallyStart(1);
            this._annotationMode = z;
            InlineMarker.finallyEnd(1);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final IrExpression convertToIrCall(FirFunctionCall functionCall, IrDynamicOperator dynamicOperator) throws KotlinIllegalArgumentExceptionWithAttachments {
        return CallAndReferenceGenerator.convertToIrCall$default(getCallGenerator(), functionCall, FirTypeUtilsKt.getResolvedType(functionCall), convertToIrReceiverExpression$org_jetbrains_kotlin_fir2ir(functionCall.getExplicitReceiver(), functionCall), dynamicOperator, false, false, 48, null);
    }
}
