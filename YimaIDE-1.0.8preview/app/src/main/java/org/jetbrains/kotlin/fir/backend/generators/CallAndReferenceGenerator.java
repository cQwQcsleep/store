package org.jetbrains.kotlin.fir.backend.generators;

import com.intellij.psi.tree.TokenSet;
import defpackage.f2f;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.jvm.internal.Reflection;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.backend.common.CommonIrAttributesKt;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.arguments.K2JsArgumentConstants;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirEvaluatorResult;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.LanguageVersionUtilsKt;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrBuiltinSymbolsContainer;
import org.jetbrains.kotlin.fir.backend.Fir2IrClassifierStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrConfiguration;
import org.jetbrains.kotlin.fir.backend.Fir2IrConversionScope;
import org.jetbrains.kotlin.fir.backend.Fir2IrConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrDeclarationStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrExtensions;
import org.jetbrains.kotlin.fir.backend.Fir2IrImplicitCastInserter;
import org.jetbrains.kotlin.fir.backend.Fir2IrIrGeneratedDeclarationsRegistrar;
import org.jetbrains.kotlin.fir.backend.Fir2IrSymbolsMappingForLazyClasses;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverterKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrVisibilityConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrVisitor;
import org.jetbrains.kotlin.fir.backend.FirProviderWithGeneratedFiles;
import org.jetbrains.kotlin.fir.backend.generators.CallAndReferenceGenerator;
import org.jetbrains.kotlin.fir.backend.utils.ConstantUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.ImplicitConversionUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.InjectedValue;
import org.jetbrains.kotlin.fir.backend.utils.IrElementsCreationUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.OffsetUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.OriginUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.SymbolConversionUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.VariousUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirOuterClassTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirTypeParametersOwner;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKind;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticProperty;
import org.jetbrains.kotlin.fir.declarations.synthetic.FirSyntheticPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirSymbolStatusUtilsKt;
import org.jetbrains.kotlin.fir.diagnostics.ConeSimpleDiagnostic;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.expressions.FirAnnotationCall;
import org.jetbrains.kotlin.fir.expressions.FirArgumentList;
import org.jetbrains.kotlin.fir.expressions.FirArgumentUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirCall;
import org.jetbrains.kotlin.fir.expressions.FirCallableReferenceAccess;
import org.jetbrains.kotlin.fir.expressions.FirContextArgumentListOwner;
import org.jetbrains.kotlin.fir.expressions.FirDelegatedConstructorCall;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpressionEvaluator;
import org.jetbrains.kotlin.fir.expressions.FirExpressionUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCall;
import org.jetbrains.kotlin.fir.expressions.FirFunctionCallOrigin;
import org.jetbrains.kotlin.fir.expressions.FirImplicitInvokeCall;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirPropertyAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.FirResolvable;
import org.jetbrains.kotlin.fir.expressions.FirResolvedQualifier;
import org.jetbrains.kotlin.fir.expressions.FirSmartCastExpression;
import org.jetbrains.kotlin.fir.expressions.FirStatement;
import org.jetbrains.kotlin.fir.expressions.FirSuperReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirThisReceiverExpression;
import org.jetbrains.kotlin.fir.expressions.FirVarargArgumentsExpression;
import org.jetbrains.kotlin.fir.expressions.FirVariableAssignment;
import org.jetbrains.kotlin.fir.expressions.ReferenceUtilsKt;
import org.jetbrains.kotlin.fir.expressions.builder.FirAnnotationCallBuilder;
import org.jetbrains.kotlin.fir.expressions.impl.FirResolvedArgumentList;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaField;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.references.FirResolvedNamedReference;
import org.jetbrains.kotlin.fir.references.builder.FirResolvedNamedReferenceBuilder;
import org.jetbrains.kotlin.fir.resolve.ContainingClassUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ImplicitIntegerCoercionKt;
import org.jetbrains.kotlin.fir.resolve.ResolveUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.resolve.TypeExpansionUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.ArgumentUtilsKt;
import org.jetbrains.kotlin.fir.resolve.calls.FirSimpleSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.resolve.calls.FirSyntheticFunctionSymbol;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.resolve.transformers.body.resolve.DeclarationApproximationUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScopeKt;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorInfo;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorsSubstitutingScopeKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirBackingFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirDelegateFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirThisOwnerSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeBuiltinTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.ConeClassifierLookupTag;
import org.jetbrains.kotlin.fir.types.ConeErrorType;
import org.jetbrains.kotlin.fir.types.ConeInferenceContext;
import org.jetbrains.kotlin.fir.types.ConeIntersectionType;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeLookupTagBasedType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.ConeTypeConstructorMarker;
import org.jetbrains.kotlin.fir.types.ConeTypeProjection;
import org.jetbrains.kotlin.fir.types.ConeTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FirTypeProjection;
import org.jetbrains.kotlin.fir.types.FirTypeProjectionWithVariance;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.InferenceUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.types.TypeUtilsKt;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.ir.IrElement;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.declarations.IrAnonymousInitializer;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationsKt;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrSymbolOwner;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.kotlin.ir.expressions.IrAnnotation;
import org.jetbrains.kotlin.ir.expressions.IrCall;
import org.jetbrains.kotlin.ir.expressions.IrConst;
import org.jetbrains.kotlin.ir.expressions.IrConstructorCall;
import org.jetbrains.kotlin.ir.expressions.IrDynamicOperator;
import org.jetbrains.kotlin.ir.expressions.IrErrorCallExpression;
import org.jetbrains.kotlin.ir.expressions.IrErrorExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpression;
import org.jetbrains.kotlin.ir.expressions.IrExpressionsKt;
import org.jetbrains.kotlin.ir.expressions.IrFieldAccessExpression;
import org.jetbrains.kotlin.ir.expressions.IrGetClass;
import org.jetbrains.kotlin.ir.expressions.IrGetEnumValue;
import org.jetbrains.kotlin.ir.expressions.IrGetObjectValue;
import org.jetbrains.kotlin.ir.expressions.IrGetValue;
import org.jetbrains.kotlin.ir.expressions.IrMemberAccessExpression;
import org.jetbrains.kotlin.ir.expressions.IrSetValue;
import org.jetbrains.kotlin.ir.expressions.IrStatementOrigin;
import org.jetbrains.kotlin.ir.expressions.IrStatementOriginImpl;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperator;
import org.jetbrains.kotlin.ir.expressions.IrTypeOperatorCall;
import org.jetbrains.kotlin.ir.expressions.IrValueAccessExpression;
import org.jetbrains.kotlin.ir.expressions.IrVararg;
import org.jetbrains.kotlin.ir.expressions.IrVarargElement;
import org.jetbrains.kotlin.ir.expressions.impl.BuildersKt;
import org.jetbrains.kotlin.ir.expressions.impl.IrBlockImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrCallImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrDynamicOperatorExpressionImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrErrorCallExpressionImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrGetValueImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrSetFieldImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrTypeOperatorCallImpl;
import org.jetbrains.kotlin.ir.expressions.impl.IrVarargImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrClassifierSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.symbols.IrEnumEntrySymbol;
import org.jetbrains.kotlin.ir.symbols.IrFieldSymbol;
import org.jetbrains.kotlin.ir.symbols.IrFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrLocalDelegatedPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSymbol;
import org.jetbrains.kotlin.ir.symbols.IrValueSymbol;
import org.jetbrains.kotlin.ir.types.IrDynamicType;
import org.jetbrains.kotlin.ir.types.IrErrorType;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.types.IrTypesKt;
import org.jetbrains.kotlin.ir.util.DumpIrTreeOptions;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.ir.util.IdSignatureRenderer;
import org.jetbrains.kotlin.ir.util.IdSignatureRendererKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.ir.util.PatchDeclarationParentsKt;
import org.jetbrains.kotlin.ir.util.RenderIrElementKt;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.name.WebCommonStandardClassIds;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.ConstantValueKind;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;
import org.jetbrains.kotlin.utils.exceptions.PlatformExceptionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000ü\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u001e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002ù\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0001\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\f\u0010\t\u001a\u00020\n*\u00020\u000bH\u0002J\f\u0010\t\u001a\u00020\n*\u00020\fH\u0002J \u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0012\u001a\u00020\u0013J\u0016\u0010\u0014\u001a\u0004\u0018\u00010\u0015*\u00020\u00162\u0006\u0010\u0017\u001a\u00020\nH\u0002J\u000e\u0010\u0018\u001a\u0004\u0018\u00010\u0019*\u00020\u001aH\u0002J\u001c\u0010\u001b\u001a\u0004\u0018\u00010\u0019*\u00020\u001a2\f\u0010\u001c\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001dH\u0002JL\u0010&\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\u00162\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010(\u001a\u00020)2\n\u0010*\u001a\u0006\u0012\u0002\b\u00030\u001d2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010+\u001a\u00020\u0013H\u0002J\u0017\u0010,\u001a\u0004\u0018\u00010\u000e2\u0006\u0010-\u001a\u00020\u0016H\u0000¢\u0006\u0002\b.J\u001f\u0010/\u001a\u0004\u0018\u00010\u000e2\u0006\u00100\u001a\u0002012\u0006\u0010(\u001a\u00020)H\u0000¢\u0006\u0002\b2J-\u00103\u001a\u0002042\u0006\u00105\u001a\u0002062\u0006\u0010(\u001a\u00020)2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u000208H\u0000¢\u0006\u0002\b:J@\u0010;\u001a\u00020\u000e2\u0006\u0010'\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\f2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u001e\u001a\u0004\u0018\u00010\u001f2\b\b\u0002\u0010<\u001a\u00020\u00132\b\b\u0002\u0010+\u001a\u00020\u0013J\u0010\u0010=\u001a\u000208*\u0006\u0012\u0002\b\u00030>H\u0002J>\u0010?\u001a\u00020\u000e2\u0006\u0010@\u001a\u00020A2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010(\u001a\u00020)2\n\u0010*\u001a\u0006\u0012\u0002\b\u00030\u001d2\u0006\u0010B\u001a\u00020\u000eH\u0002J\u0016\u0010C\u001a\u00020\u000e*\u00020\u00162\b\u0010\u0011\u001a\u0004\u0018\u00010\u000eH\u0002J\"\u0010D\u001a\u0004\u0018\u00010\u000e2\u0006\u00100\u001a\u0002012\u0006\u0010(\u001a\u00020)2\u0006\u0010B\u001a\u00020\u000eH\u0002J\u0017\u0010E\u001a\u0004\u0018\u0001062\u0006\u0010(\u001a\u00020)H\u0000¢\u0006\u0002\bFJ\u0018\u0010G\u001a\u00020\u000e2\u0006\u0010@\u001a\u00020A2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000eJ\u0016\u0010G\u001a\u00020\u000e2\u0006\u0010H\u001a\u00020\u001a2\u0006\u0010I\u001a\u00020JJ\u0012\u0010K\u001a\u0004\u0018\u00010\u001a2\u0006\u0010@\u001a\u00020AH\u0002J\u000e\u0010L\u001a\u00020\u000e2\u0006\u0010M\u001a\u00020NJ\u000e\u0010O\u001a\u0004\u0018\u00010P*\u00020NH\u0002J\f\u0010Q\u001a\u00020\u000e*\u00020\u000eH\u0002J\u0015\u0010R\u001a\u00020\u000e2\u0006\u0010S\u001a\u00020TH\u0000¢\u0006\u0002\bUJ!\u0010R\u001a\u0004\u0018\u00010\u000e2\u0006\u0010S\u001a\u00020T2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0000¢\u0006\u0002\bUJ\"\u0010V\u001a\u00020\u000e2\u0006\u0010W\u001a\u00020\u001a2\b\u0010X\u001a\u0004\u0018\u00010Y2\u0006\u0010Z\u001a\u00020[H\u0002J\u001e\u0010\\\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010W\u001a\u00020\u001a2\b\u0010X\u001a\u0004\u0018\u00010YH\u0002J\u0018\u0010]\u001a\u0004\u0018\u00010\u000e*\u00020\u00162\b\u0010\u0011\u001a\u0004\u0018\u00010\u000eH\u0002J\u0018\u0010^\u001a\u0004\u0018\u00010\u000e*\u00020\u00162\b\u0010\u0011\u001a\u0004\u0018\u00010\u000eH\u0002J%\u0010_\u001a\u0004\u0018\u00010\u000e*\u00020\u00162\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\u0006\u0010`\u001a\u00020\u0013H\u0000¢\u0006\u0002\baJ\u0010\u0010b\u001a\u00020\u0013*\u0006\u0012\u0002\b\u00030>H\u0002J,\u0010c\u001a\u00020d2\u0006\u00107\u001a\u0002082\u0006\u00109\u001a\u0002082\u0006\u0010(\u001a\u00020)2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\nH\u0002J\u0014\u0010e\u001a\u00020f*\u00020\u000e2\u0006\u0010g\u001a\u00020hH\u0002J\u0019\u0010i\u001a\u00020\u000e*\u00020\u000e2\u0006\u0010j\u001a\u00020\u0016H\u0000¢\u0006\u0002\bkJ$\u0010l\u001a\u00020\u000e*\u00020\u000e2\b\u0010m\u001a\u0004\u0018\u00010n2\f\u0010o\u001a\b\u0012\u0004\u0012\u00020q0pH\u0002J\"\u0010r\u001a\b\u0012\u0004\u0012\u00020\f0p*\u0004\u0018\u00010n2\f\u0010o\u001a\b\u0012\u0004\u0012\u00020q0pH\u0002J,\u0010i\u001a\u00020\u000e*\u00020\u000e2\u000e\u0010s\u001a\n\u0012\u0004\u0012\u00020\f\u0018\u00010p2\u000e\u0010t\u001a\n\u0012\u0004\u0012\u00020u\u0018\u00010pH\u0002J\u0018\u0010v\u001a\u00020f2\u0006\u0010w\u001a\u00020x2\u0006\u0010y\u001a\u00020zH\u0002J8\u0010{\u001a\u00020\u000e*\u00020\u000e2\b\u0010|\u001a\u0004\u0018\u00010}2\f\u0010~\u001a\b\u0012\u0002\b\u0003\u0018\u00010>2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000e2\n\b\u0002\u0010\u007f\u001a\u0004\u0018\u00010\u000eJ.\u0010\u0080\u0001\u001a\u00030\u0081\u0001*\u00020\u000e2\u0006\u0010|\u001a\u00020}2\f\u0010~\u001a\b\u0012\u0002\b\u0003\u0018\u00010>2\b\u0010\u0011\u001a\u0004\u0018\u00010\u000eH\u0002J/\u0010\u0082\u0001\u001a\u00020\u000e*\u00020\u000e2\b\u0010|\u001a\u0004\u0018\u00010}2\f\u0010~\u001a\b\u0012\u0002\b\u0003\u0018\u00010>2\b\u0010\u0083\u0001\u001a\u00030\u0081\u0001H\u0002J(\u0010\u0084\u0001\u001a\u0004\u0018\u00010\u000e*\u0007\u0012\u0002\b\u00030\u0085\u00012\b\u0010\u0083\u0001\u001a\u00030\u0081\u00012\b\u0010\u0086\u0001\u001a\u00030\u0087\u0001H\u0002J(\u0010\u0088\u0001\u001a\u00020\u00132\u000e\u0010\u0089\u0001\u001a\t\u0012\u0004\u0012\u00020Y0\u008a\u00012\r\u0010\u008b\u0001\u001a\b\u0012\u0004\u0012\u00020Y0pH\u0002J&\u0010\u008c\u0001\u001a\u000208*\u0007\u0012\u0002\b\u00030\u0085\u00012\b\u0010|\u001a\u0004\u0018\u00010}2\b\u0010\u0083\u0001\u001a\u00030\u0081\u0001H\u0002R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001e\u001a\u0004\u0018\u00010\u001f*\u00020 8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u001a\u0010\u001e\u001a\u0004\u0018\u00010\u001f*\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b!\u0010#R\u0018\u0010$\u001a\u00020\u0013*\u00020\u00168BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b$\u0010%R\u0016\u0010\u008d\u0001\u001a\u00030\u008e\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001R\u0016\u0010\u0091\u0001\u001a\u00030\u0092\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0093\u0001\u0010\u0094\u0001R\u0016\u0010\u0095\u0001\u001a\u00030\u0096\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001R\u0016\u0010\u0099\u0001\u001a\u00030\u009a\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009b\u0001\u0010\u009c\u0001R\u0015\u0010\u009d\u0001\u001a\u00020\u0000X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009e\u0001\u0010\u009f\u0001R\u0016\u0010 \u0001\u001a\u00030¡\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¢\u0001\u0010£\u0001R\u0016\u0010¤\u0001\u001a\u00030¥\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¦\u0001\u0010§\u0001R\u0016\u0010¨\u0001\u001a\u00030©\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bª\u0001\u0010«\u0001R\u0016\u0010¬\u0001\u001a\u00030\u00ad\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b®\u0001\u0010¯\u0001R\u0016\u0010°\u0001\u001a\u00030±\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b²\u0001\u0010³\u0001R\u0016\u0010´\u0001\u001a\u00030µ\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¶\u0001\u0010·\u0001R\u0016\u0010¸\u0001\u001a\u00030¹\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bº\u0001\u0010»\u0001R\u0016\u0010¼\u0001\u001a\u00030½\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¾\u0001\u0010¿\u0001R\u001f\u0010À\u0001\u001a\f\u0012\u0005\u0012\u00030Â\u0001\u0018\u00010Á\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÃ\u0001\u0010Ä\u0001R\u0016\u0010Å\u0001\u001a\u00030Æ\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÇ\u0001\u0010È\u0001R\u0016\u0010É\u0001\u001a\u00030Ê\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bË\u0001\u0010Ì\u0001R\u0016\u0010Í\u0001\u001a\u00030Î\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÏ\u0001\u0010Ð\u0001R\u001c\u0010Ñ\u0001\u001a\t\u0012\u0005\u0012\u00030Ò\u00010pX\u0096\u0005¢\u0006\b\u001a\u0006\bÓ\u0001\u0010Ô\u0001R\u0016\u0010Õ\u0001\u001a\u00030Ö\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b×\u0001\u0010Ø\u0001R\u0016\u0010Ù\u0001\u001a\u00030Ú\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÛ\u0001\u0010Ü\u0001R\u0016\u0010Ý\u0001\u001a\u00030Þ\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bß\u0001\u0010à\u0001R\u0016\u0010á\u0001\u001a\u00030â\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bã\u0001\u0010ä\u0001R\u0016\u0010å\u0001\u001a\u00030æ\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bç\u0001\u0010è\u0001R\u0018\u0010é\u0001\u001a\u0005\u0018\u00010ê\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bë\u0001\u0010ì\u0001R\u0016\u0010í\u0001\u001a\u00030î\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bï\u0001\u0010ð\u0001R\u0016\u0010ñ\u0001\u001a\u00030ò\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bó\u0001\u0010ô\u0001R\u0016\u0010õ\u0001\u001a\u00030ö\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b÷\u0001\u0010ø\u0001¨\u0006ú\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "visitor", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisitor;", "conversionScope", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisitor;Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;)V", "toIrType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "convertToIrCallableReference", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "callableReferenceAccess", "Lorg/jetbrains/kotlin/fir/expressions/FirCallableReferenceAccess;", "explicitReceiverExpression", "isDelegate", Argument.Delimiters.none, "tryConvertToSamConstructorCall", "Lorg/jetbrains/kotlin/ir/expressions/IrTypeOperatorCall;", "Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;", ModuleXmlParser.TYPE, "superQualifierSymbolForFunctionAndPropertyAccess", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "superQualifierSymbolForFieldAccess", "firResolvedSymbol", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "dynamicOperator", "Lorg/jetbrains/kotlin/ir/expressions/IrDynamicOperator;", "Lorg/jetbrains/kotlin/name/Name;", "getDynamicOperator", "(Lorg/jetbrains/kotlin/name/Name;)Lorg/jetbrains/kotlin/ir/expressions/IrDynamicOperator;", "(Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)Lorg/jetbrains/kotlin/ir/expressions/IrDynamicOperator;", "isOperatorCall", "(Lorg/jetbrains/kotlin/fir/expressions/FirQualifiedAccessExpression;)Z", "convertToIrCallForDynamic", "qualifiedAccess", "calleeReference", "Lorg/jetbrains/kotlin/fir/references/FirReference;", "symbol", "noArguments", "convertSubstitutedInlineLambda", "expression", "convertSubstitutedInlineLambda$org_jetbrains_kotlin_fir2ir", "injectGetValueCall", "element", "Lorg/jetbrains/kotlin/fir/FirElement;", "injectGetValueCall$org_jetbrains_kotlin_fir2ir", "useInjectedValue", "Lorg/jetbrains/kotlin/ir/expressions/impl/IrGetValueImpl;", "injectedValue", "Lorg/jetbrains/kotlin/fir/backend/utils/InjectedValue;", "startOffset", Argument.Delimiters.none, "endOffset", "useInjectedValue$org_jetbrains_kotlin_fir2ir", "convertToIrCall", "variableAsFunctionMode", "valueParametersSize", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "convertToIrSetCallForDynamic", "variableAssignment", "Lorg/jetbrains/kotlin/fir/expressions/FirVariableAssignment;", "assignedValue", "findIrDynamicReceiver", "injectSetValueCall", "findInjectedValue", "findInjectedValue$org_jetbrains_kotlin_fir2ir", "convertToIrSetCall", "rValue", "property", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "extractDispatchReceiverOfAssignment", "convertToIrAnnotation", "annotation", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "toAnnotationCall", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotationCall;", "filterOutErrorArgsInAnnotation", "convertToGetObject", "qualifier", "Lorg/jetbrains/kotlin/fir/expressions/FirResolvedQualifier;", "convertToGetObject$org_jetbrains_kotlin_fir2ir", "convertArgument", "argument", "parameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "applyImplicitIntegerCoercionIfNeeded", "findIrDispatchReceiver", "findIrExtensionReceiver", "findIrReceiver", "isDispatch", "findIrReceiver$org_jetbrains_kotlin_fir2ir", "isFunctionFromAny", "generateErrorCallExpression", "Lorg/jetbrains/kotlin/ir/expressions/IrErrorCallExpression;", "updateStatementOrigin", Argument.Delimiters.none, "newOrigin", "Lorg/jetbrains/kotlin/ir/expressions/IrStatementOrigin;", "applyTypeArguments", "access", "applyTypeArguments$org_jetbrains_kotlin_fir2ir", "applyTypeArgumentsWithTypealiasConstructorRemapping", "callableFir", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "originalTypeArguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/types/FirTypeProjection;", "refineTypeArgumentsOfTypeAliasConstructor", "typeArguments", "typeParameters", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "evaluateAndApplyJsCallArg", "jsFirCall", "Lorg/jetbrains/kotlin/fir/expressions/FirFunctionCall;", "jsIrCall", "Lorg/jetbrains/kotlin/ir/expressions/IrCall;", "applyReceiversAndArguments", "statement", "Lorg/jetbrains/kotlin/fir/expressions/FirStatement;", "declarationSiteSymbol", "irAssignmentRhs", "putReceivers", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator$ReceiverInfo;", "applyCallArguments", "receiverInfo", "applyArgumentsWithReorderingIfNeeded", "Lorg/jetbrains/kotlin/ir/expressions/IrMemberAccessExpression;", K2JsArgumentConstants.CALL, "Lorg/jetbrains/kotlin/fir/expressions/FirCall;", "needArgumentReordering", "parametersInArgumentOrder", Argument.Delimiters.none, "contextAndValueParameters", "putContextArguments", "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "()Ljava/util/List;", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "ReceiverInfo", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CallAndReferenceGenerator implements Fir2IrComponents {
    private final Fir2IrComponents c;
    private final Fir2IrConversionScope conversionScope;
    private final Fir2IrVisitor visitor;

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bJ\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\b¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator$ReceiverInfo;", Argument.Delimiters.none, "hasDispatchReceiver", Argument.Delimiters.none, "hasExtensionReceiver", "<init>", "(ZZ)V", "getHasDispatchReceiver", "()Z", "getHasExtensionReceiver", "contextArgumentOffset", Argument.Delimiters.none, "valueArgumentOffset", "contextArgumentCount", "extensionReceiverOffset", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ReceiverInfo {
        private final boolean hasDispatchReceiver;
        private final boolean hasExtensionReceiver;

        public ReceiverInfo(boolean z, boolean z2) {
            this.hasDispatchReceiver = z;
            this.hasExtensionReceiver = z2;
        }

        public final int contextArgumentOffset() {
            return this.hasDispatchReceiver ? 1 : 0;
        }

        public final int extensionReceiverOffset(int contextArgumentCount) {
            return (this.hasDispatchReceiver ? 1 : 0) + contextArgumentCount;
        }

        public final boolean getHasDispatchReceiver() {
            return this.hasDispatchReceiver;
        }

        public final boolean getHasExtensionReceiver() {
            return this.hasExtensionReceiver;
        }

        public final int valueArgumentOffset(int contextArgumentCount) {
            return extensionReceiverOffset(contextArgumentCount) + (this.hasExtensionReceiver ? 1 : 0);
        }
    }

    @Metadata(d1 = {"\u0000-\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000*\u0001\u0000\b\u008a\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0011\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0007HÆ\u0003J,\u0010\u0013\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007HÆ\u0001¢\u0006\u0002\u0010\u0014J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0007HÖ\u0081\u0004J\n\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u001b"}, d2 = {"org/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator$applyArgumentsWithReorderingIfNeeded$ArgumentInfo", Argument.Delimiters.none, "parameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "expression", "Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "parameterIndex", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;Lorg/jetbrains/kotlin/ir/expressions/IrExpression;I)V", "getParameter", "()Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "getExpression", "()Lorg/jetbrains/kotlin/ir/expressions/IrExpression;", "getParameterIndex", "()I", "component1", "component2", "component3", "copy", "(Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;Lorg/jetbrains/kotlin/ir/expressions/IrExpression;I)Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator$applyArgumentsWithReorderingIfNeeded$ArgumentInfo;", "equals", Argument.Delimiters.none, "other", "hashCode", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class ArgumentInfo {
        private final IrExpression expression;
        private final FirValueParameter parameter;
        private final int parameterIndex;

        public ArgumentInfo(FirValueParameter firValueParameter, IrExpression irExpression, int i) {
            firValueParameter.getClass();
            irExpression.getClass();
            this.parameter = firValueParameter;
            this.expression = irExpression;
            this.parameterIndex = i;
        }

        public static /* synthetic */ ArgumentInfo copy$default(ArgumentInfo argumentInfo, FirValueParameter firValueParameter, IrExpression irExpression, int i, int i2, Object obj) {
            if ((i2 & 1) != 0) {
                firValueParameter = argumentInfo.parameter;
            }
            if ((i2 & 2) != 0) {
                irExpression = argumentInfo.expression;
            }
            if ((i2 & 4) != 0) {
                i = argumentInfo.parameterIndex;
            }
            return argumentInfo.copy(firValueParameter, irExpression, i);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final FirValueParameter getParameter() {
            return this.parameter;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final IrExpression getExpression() {
            return this.expression;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final int getParameterIndex() {
            return this.parameterIndex;
        }

        public final ArgumentInfo copy(FirValueParameter parameter, IrExpression expression, int parameterIndex) {
            parameter.getClass();
            expression.getClass();
            return new ArgumentInfo(parameter, expression, parameterIndex);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof ArgumentInfo)) {
                return false;
            }
            ArgumentInfo argumentInfo = (ArgumentInfo) other;
            return Intrinsics.areEqual(this.parameter, argumentInfo.parameter) && Intrinsics.areEqual(this.expression, argumentInfo.expression) && this.parameterIndex == argumentInfo.parameterIndex;
        }

        public final IrExpression getExpression() {
            return this.expression;
        }

        public final FirValueParameter getParameter() {
            return this.parameter;
        }

        public final int getParameterIndex() {
            return this.parameterIndex;
        }

        public int hashCode() {
            return (((this.parameter.hashCode() * 31) + this.expression.hashCode()) * 31) + Integer.hashCode(this.parameterIndex);
        }

        public String toString() {
            return "ArgumentInfo(parameter=" + this.parameter + ", expression=" + this.expression + ", parameterIndex=" + this.parameterIndex + ')';
        }
    }

    public CallAndReferenceGenerator(Fir2IrComponents fir2IrComponents, Fir2IrVisitor fir2IrVisitor, Fir2IrConversionScope fir2IrConversionScope) {
        fir2IrComponents.getClass();
        fir2IrVisitor.getClass();
        fir2IrConversionScope.getClass();
        this.c = fir2IrComponents;
        this.visitor = fir2IrVisitor;
        this.conversionScope = fir2IrConversionScope;
    }

    /* JADX WARN: Code duplicated, block: B:124:0x02c4  */
    /* JADX WARN: Code duplicated, block: B:125:0x02c7  */
    /* JADX WARN: Code duplicated, block: B:129:0x02d6  */
    /* JADX WARN: Code duplicated, block: B:140:0x031c  */
    /* JADX WARN: Multi-variable type inference failed */
    private final IrExpression applyArgumentsWithReorderingIfNeeded(IrMemberAccessExpression<?> irMemberAccessExpression, ReceiverInfo receiverInfo, FirCall firCall) {
        ConeSubstitutor coneSubstitutorBuildSubstitutorByCalledCallable;
        int i;
        FirCallableDeclaration firCallableDeclaration;
        FirFunction firFunction;
        IrVarargImpl IrVarargImpl;
        int iContextArgumentOffset;
        int iIndexOf;
        IrExpression irExpressionIrErrorExpressionImpl;
        List<FirExpression> contextArguments;
        FirReference calleeReference;
        FirFunctionSymbol resolvedFunctionSymbol$default;
        FirResolvable firResolvable = firCall instanceof FirResolvable ? (FirResolvable) firCall : null;
        FirFunction firFunction2 = (firResolvable == null || (calleeReference = firResolvable.getCalleeReference()) == null || (resolvedFunctionSymbol$default = FirReferenceUtilsKt.toResolvedFunctionSymbol$default(calleeReference, false, 1, null)) == null) ? null : (FirFunction) resolvedFunctionSymbol$default.getFir();
        FirArgumentList argumentList = firCall.getArgumentList();
        FirResolvedArgumentList firResolvedArgumentList = argumentList instanceof FirResolvedArgumentList ? (FirResolvedArgumentList) argumentList : null;
        if (firFunction2 == null || firResolvedArgumentList == null || (!this.visitor.get_annotationMode() && firResolvedArgumentList.getMappingIncludingContextArguments().isEmpty())) {
            putContextArguments(irMemberAccessExpression, firCall, receiverInfo);
            return null;
        }
        List<FirValueParameter> contextParameters = firFunction2.getContextParameters();
        List<FirValueParameter> valueParameters = firFunction2.getValueParameters();
        FirFunctionCall firFunctionCall = firCall instanceof FirFunctionCall ? (FirFunctionCall) firCall : null;
        if (firFunctionCall == null || (coneSubstitutorBuildSubstitutorByCalledCallable = VariousUtilsKt.buildSubstitutorByCalledCallable(this, firFunctionCall)) == null) {
            coneSubstitutorBuildSubstitutorByCalledCallable = ConeSubstitutor.Empty.INSTANCE;
        }
        int size = contextParameters.size();
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        FirContextArgumentListOwner firContextArgumentListOwner = firCall instanceof FirContextArgumentListOwner ? (FirContextArgumentListOwner) firCall : null;
        if (firContextArgumentListOwner != null && (contextArguments = firContextArgumentListOwner.getContextArguments()) != null) {
            int i2 = 0;
            for (Object obj : contextArguments) {
                int i3 = i2 + 1;
                if (i2 < 0) {
                    CollectionsKt.throwIndexOverflow();
                }
                FirExpression firExpression = (FirExpression) obj;
                if (!firResolvedArgumentList.getMappingIncludingContextArguments().containsKey(firExpression)) {
                    FirValueParameter firValueParameter = contextParameters.get(i2);
                    listCreateListBuilder.add(new ArgumentInfo(firValueParameter, convertArgument(firExpression, firValueParameter, coneSubstitutorBuildSubstitutorByCalledCallable), receiverInfo.contextArgumentOffset() + i2));
                }
                i2 = i3;
            }
        }
        Set<Map.Entry<FirExpression, FirValueParameter>> setEntrySet = firResolvedArgumentList.getMappingIncludingContextArguments().entrySet();
        setEntrySet.getClass();
        Iterator it = setEntrySet.iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            entry.getClass();
            Object key = entry.getKey();
            key.getClass();
            FirExpression firExpression2 = (FirExpression) key;
            Object value = entry.getValue();
            value.getClass();
            FirValueParameter firValueParameter2 = (FirValueParameter) value;
            if (this.visitor.isGetClassOfUnresolvedTypeInAnnotation$org_jetbrains_kotlin_fir2ir(firExpression2)) {
                valueParameters = valueParameters;
                it = it;
            } else {
                if (firValueParameter2.getValueParameterKind() == FirValueParameterKind.Regular) {
                    iContextArgumentOffset = receiverInfo.valueArgumentOffset(size);
                    iIndexOf = valueParameters.indexOf(firValueParameter2);
                } else {
                    iContextArgumentOffset = receiverInfo.contextArgumentOffset();
                    iIndexOf = contextParameters.indexOf(firValueParameter2);
                }
                int i4 = iContextArgumentOffset + iIndexOf;
                if (this.visitor.get_annotationMode() && (firCall instanceof FirAnnotation)) {
                    firExpression2 = ((FirAnnotation) firCall).getArgumentMapping().getMapping().get(firValueParameter2.getName());
                }
                if (firExpression2 == null || (irExpressionIrErrorExpressionImpl = convertArgument(firExpression2, firValueParameter2, coneSubstitutorBuildSubstitutorByCalledCallable)) == null) {
                    irExpressionIrErrorExpressionImpl = BuildersKt.IrErrorExpressionImpl(irMemberAccessExpression.getStartOffset(), irMemberAccessExpression.getEndOffset(), irMemberAccessExpression.getType(), "No evaluated argument found for parameter `" + firValueParameter2.getName() + "` in " + UtilsKt.render(firCall));
                }
                listCreateListBuilder.add(new ArgumentInfo(firValueParameter2, irExpressionIrErrorExpressionImpl, i4));
            }
            valueParameters = valueParameters;
            it = it;
        }
        List<FirValueParameter> list = valueParameters;
        List<ArgumentInfo> listBuild = CollectionsKt.build(listCreateListBuilder);
        if (!this.visitor.get_annotationMode()) {
            List list2 = listBuild;
            if (!(list2 instanceof Collection) || !list2.isEmpty()) {
                Iterator it2 = list2.iterator();
                while (it2.hasNext()) {
                    if (!IrExpressionsKt.hasNoSideEffects(((ArgumentInfo) it2.next()).getExpression())) {
                        Collection<FirValueParameter> collectionValues = firResolvedArgumentList.getMappingIncludingContextArguments().values();
                        collectionValues.getClass();
                        if (!needArgumentReordering(collectionValues, CollectionsKt.plus(contextParameters, list))) {
                            break;
                        }
                        IrBlockImpl IrBlockImpl = BuildersKt.IrBlockImpl(irMemberAccessExpression.getStartOffset(), irMemberAccessExpression.getEndOffset(), irMemberAccessExpression.getType(), IrStatementOrigin.Companion.getARGUMENTS_REORDERING_FOR_CALL());
                        if (receiverInfo.getHasDispatchReceiver()) {
                            IrMemberAccessExpression.ValueArgumentsList arguments = irMemberAccessExpression.getArguments();
                            IrExpression irExpression = (IrExpression) irMemberAccessExpression.getArguments().get(0);
                            arguments.set(0, irExpression != null ? applyArgumentsWithReorderingIfNeeded$lambda$2$freeze(irExpression, this, IrBlockImpl, "$this") : null);
                        }
                        if (receiverInfo.getHasExtensionReceiver()) {
                            int iExtensionReceiverOffset = receiverInfo.extensionReceiverOffset(size);
                            IrMemberAccessExpression.ValueArgumentsList arguments2 = irMemberAccessExpression.getArguments();
                            IrExpression irExpression2 = (IrExpression) irMemberAccessExpression.getArguments().get(iExtensionReceiverOffset);
                            arguments2.set(iExtensionReceiverOffset, irExpression2 != null ? applyArgumentsWithReorderingIfNeeded$lambda$2$freeze(irExpression2, this, IrBlockImpl, "$receiver") : null);
                        }
                        for (ArgumentInfo argumentInfo : listBuild) {
                            FirValueParameter parameter = argumentInfo.getParameter();
                            IrExpression expression = argumentInfo.getExpression();
                            int parameterIndex = argumentInfo.getParameterIndex();
                            IrMemberAccessExpression.ValueArgumentsList arguments3 = irMemberAccessExpression.getArguments();
                            String strAsString = parameter.getName().asString();
                            strAsString.getClass();
                            arguments3.set(parameterIndex, applyArgumentsWithReorderingIfNeeded$lambda$2$freeze(expression, this, IrBlockImpl, strAsString));
                        }
                        IrBlockImpl.getStatements().add(irMemberAccessExpression);
                        return IrBlockImpl;
                    }
                }
            }
        }
        for (ArgumentInfo argumentInfo2 : listBuild) {
            irMemberAccessExpression.getArguments().set(argumentInfo2.getParameterIndex(), argumentInfo2.getExpression());
        }
        if (this.visitor.get_annotationMode()) {
            FirReference reference = ReferenceUtilsKt.toReference(firCall, getSession());
            if (reference != null) {
                i = 0;
                FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(reference, false, 1, null);
                firCallableDeclaration = resolvedCallableSymbol$default != null ? (FirCallableDeclaration) resolvedCallableSymbol$default.getFir() : null;
                if (firCallableDeclaration instanceof FirFunction) {
                    firFunction = (FirFunction) firCallableDeclaration;
                } else {
                    firFunction = null;
                }
                for (FirValueParameter firValueParameter3 : list) {
                    int i5 = i + 1;
                    if (!firValueParameter3.getIsVararg() && !firResolvedArgumentList.getMapping().containsValue(firValueParameter3)) {
                        if (firFunction == null || !DeclarationUtilsKt.itOrExpectHasDefaultParameterValue(firFunction, i)) {
                            IrType irType = toIrType(firValueParameter3.getReturnTypeRef());
                            IrVarargImpl = BuildersKt.IrVarargImpl(-1, -1, irType, VariousUtilsKt.getArrayElementType(irType, getBuiltins()));
                        } else {
                            IrVarargImpl = null;
                        }
                        irMemberAccessExpression.getArguments().set(receiverInfo.valueArgumentOffset(size) + i, IrVarargImpl);
                    }
                    i = i5;
                }
            } else {
                i = 0;
            }
            if (firCallableDeclaration instanceof FirFunction) {
                firFunction = (FirFunction) firCallableDeclaration;
            } else {
                firFunction = null;
            }
            while (r3.hasNext()) {
                int i6 = i + 1;
                if (!firValueParameter3.getIsVararg()) {
                }
                i = i6;
            }
        }
        return irMemberAccessExpression;
    }

    private static final IrExpression applyArgumentsWithReorderingIfNeeded$lambda$2$freeze(IrExpression irExpression, CallAndReferenceGenerator callAndReferenceGenerator, IrBlockImpl irBlockImpl, String str) {
        if (IrExpressionsKt.isUnchanging(irExpression)) {
            return irExpression;
        }
        Pair<IrVariable, IrValueSymbol> pairCreateTemporaryVariable = IrElementsCreationUtilsKt.createTemporaryVariable(callAndReferenceGenerator.conversionScope, irExpression, str);
        IrVariable irVariable = (IrVariable) pairCreateTemporaryVariable.component1();
        IrValueSymbol irValueSymbol = (IrValueSymbol) pairCreateTemporaryVariable.component2();
        irBlockImpl.getStatements().add(irVariable);
        return BuildersKt.IrGetValueImpl(irExpression.getStartOffset(), irExpression.getEndOffset(), irValueSymbol, (IrStatementOrigin) null);
    }

    /* JADX WARN: Code duplicated, block: B:44:0x0077  */
    private final IrExpression applyCallArguments(IrExpression irExpression, FirStatement firStatement, FirCallableSymbol<?> firCallableSymbol, ReceiverInfo receiverInfo) {
        String strRender$default;
        IrSimpleFunction owner;
        IdSignature signature;
        FirCall firCall = firStatement instanceof FirCall ? (FirCall) firStatement : null;
        if (!(irExpression instanceof IrMemberAccessExpression)) {
            if (irExpression instanceof IrErrorCallExpressionImpl) {
                IrErrorCallExpressionImpl irErrorCallExpressionImpl = (IrErrorCallExpressionImpl) irExpression;
                List<FirExpression> arguments = firCall != null ? firCall.getArgumentList().getArguments() : null;
                if (arguments == null) {
                    arguments = CollectionsKt.emptyList();
                }
                Iterator<FirExpression> it = arguments.iterator();
                while (it.hasNext()) {
                    irErrorCallExpressionImpl.getArguments().add(Fir2IrVisitor.convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this.visitor, it.next(), false, null, 6, null));
                }
            }
            return irExpression;
        }
        if (firCall == null) {
            putContextArguments((IrMemberAccessExpression) irExpression, firStatement, receiverInfo);
            return irExpression;
        }
        int size = firCall.getArgumentList().getArguments().size();
        if (firCallableSymbol != null && size <= valueParametersSize(firCallableSymbol)) {
            IrExpression irExpressionApplyArgumentsWithReorderingIfNeeded = applyArgumentsWithReorderingIfNeeded((IrMemberAccessExpression) irExpression, receiverInfo, firCall);
            if (irExpressionApplyArgumentsWithReorderingIfNeeded != null) {
                return irExpressionApplyArgumentsWithReorderingIfNeeded;
            }
            if (size == 0) {
                return irExpression;
            }
            k2d.a("Non-empty unresolved argument list.");
            return null;
        }
        IrCallImpl irCallImpl = irExpression instanceof IrCallImpl ? (IrCallImpl) irExpression : null;
        IrSimpleFunctionSymbol symbol = irCallImpl != null ? irCallImpl.getSymbol() : null;
        if (symbol == null || (signature = symbol.getSignature()) == null || (strRender$default = IdSignatureRendererKt.render$default(signature, (IdSignatureRenderer) null, 1, (Object) null)) == null) {
            if (symbol == null) {
                strRender$default = "???";
            } else {
                if (!symbol.isBound()) {
                    symbol = null;
                }
                if (symbol == null || (owner = symbol.getOwner()) == null) {
                    strRender$default = "???";
                } else {
                    strRender$default = RenderIrElementKt.render$default(owner, (DumpIrTreeOptions) null, 1, (Object) null);
                }
            }
        }
        int startOffset = irExpression.getStartOffset();
        int endOffset = irExpression.getEndOffset();
        IrType type = irExpression.getType();
        StringBuilder sb = new StringBuilder("Cannot bind ");
        sb.append(size);
        sb.append(" arguments to '");
        sb.append(strRender$default);
        sb.append("' call with ");
        sb.append(firCallableSymbol != null ? Integer.valueOf(valueParametersSize(firCallableSymbol)) : null);
        sb.append(" parameters");
        IrErrorCallExpressionImpl IrErrorCallExpressionImpl = BuildersKt.IrErrorCallExpressionImpl(startOffset, endOffset, type, sb.toString());
        Iterator<FirExpression> it2 = firCall.getArgumentList().getArguments().iterator();
        while (it2.hasNext()) {
            IrErrorCallExpressionImpl.getArguments().add(Fir2IrVisitor.convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this.visitor, it2.next(), false, null, 6, null));
        }
        return IrErrorCallExpressionImpl;
    }

    private final IrExpression applyImplicitIntegerCoercionIfNeeded(IrExpression irExpression, FirExpression firExpression, FirValueParameter firValueParameter) {
        Pair<FirNamedFunctionSymbol, IrSimpleFunctionSymbol> pair;
        Pair<FirNamedFunctionSymbol, IrSimpleFunctionSymbol> pair2;
        if (LanguageVersionUtilsKt.isEnabled(this, LanguageFeature.ImplicitSignedToUnsignedIntegerConversion) && firValueParameter != null && ImplicitIntegerCoercionKt.isMarkedWithImplicitIntegerCoercion(firValueParameter) && ConeBuiltinTypeUtilsKt.isUnsignedTypeOrNullableUnsignedType(TypeExpansionUtilsKt.fullyExpandedType(this, ArgumentUtilsKt.getExpectedType(firExpression, getSession(), firValueParameter))) && ImplicitIntegerCoercionKt.isMarkedWithImplicitIntegerCoercion(firValueParameter)) {
            if ((irExpression instanceof IrVarargImpl) && (firExpression instanceof FirVarargArgumentsExpression)) {
                IrVarargImpl irVarargImpl = (IrVarargImpl) irExpression;
                FqName classFqName = IrTypesKt.getClassFqName(irVarargImpl.getVarargElementType());
                if (classFqName == null) {
                    return irExpression;
                }
                Fir2IrBuiltinSymbolsContainer builtins = getBuiltins();
                Name nameIdentifier = Name.identifier("to" + classFqName.shortName().asString());
                nameIdentifier.getClass();
                String strAsString = StandardNames.BUILT_INS_PACKAGE_NAME.asString();
                strAsString.getClass();
                Map<IrClassifierSymbol, Pair<FirNamedFunctionSymbol, IrSimpleFunctionSymbol>> nonBuiltInFunctionsWithFirCounterpartByExtensionReceiver = builtins.getNonBuiltInFunctionsWithFirCounterpartByExtensionReceiver(nameIdentifier, strAsString);
                if (!nonBuiltInFunctionsWithFirCounterpartByExtensionReceiver.isEmpty()) {
                    int i = 0;
                    for (Object obj : irVarargImpl.getElements()) {
                        int i2 = i + 1;
                        if (i < 0) {
                            CollectionsKt.throwIndexOverflow();
                        }
                        IrExpression irExpression2 = (IrVarargElement) obj;
                        if (irExpression2 instanceof IrExpression) {
                            FirVarargArgumentsExpression firVarargArgumentsExpression = (FirVarargArgumentsExpression) firExpression;
                            IrClassifierSymbol classifierOrNull = IrTypesKt.getClassifierOrNull(toIrType(FirTypeUtilsKt.getResolvedType(firVarargArgumentsExpression.getArguments().get(i))));
                            if (classifierOrNull != null && (pair2 = nonBuiltInFunctionsWithFirCounterpartByExtensionReceiver.get(classifierOrNull)) != null) {
                                irVarargImpl.getElements().set(i, applyImplicitIntegerCoercionIfNeeded$applyToElement(irExpression2, this, firVarargArgumentsExpression.getArguments().get(i), (FirNamedFunctionSymbol) pair2.component1(), (IrSimpleFunctionSymbol) pair2.component2()));
                            }
                        }
                        i = i2;
                    }
                }
            } else {
                FqName classFqName2 = IrTypesKt.getClassFqName(toIrType(firValueParameter.getReturnTypeRef()));
                if (classFqName2 != null) {
                    Fir2IrBuiltinSymbolsContainer builtins2 = getBuiltins();
                    Name nameIdentifier2 = Name.identifier("to" + classFqName2.shortName().asString());
                    nameIdentifier2.getClass();
                    String strAsString2 = StandardNames.BUILT_INS_PACKAGE_NAME.asString();
                    strAsString2.getClass();
                    Map<IrClassifierSymbol, Pair<FirNamedFunctionSymbol, IrSimpleFunctionSymbol>> nonBuiltInFunctionsWithFirCounterpartByExtensionReceiver2 = builtins2.getNonBuiltInFunctionsWithFirCounterpartByExtensionReceiver(nameIdentifier2, strAsString2);
                    IrClassifierSymbol classifierOrNull2 = IrTypesKt.getClassifierOrNull(toIrType(FirTypeUtilsKt.getResolvedType(firExpression)));
                    if (classifierOrNull2 != null && (pair = nonBuiltInFunctionsWithFirCounterpartByExtensionReceiver2.get(classifierOrNull2)) != null) {
                        return applyImplicitIntegerCoercionIfNeeded$applyToElement(irExpression, this, firExpression, (FirNamedFunctionSymbol) pair.component1(), (IrSimpleFunctionSymbol) pair.component2());
                    }
                }
            }
        }
        return irExpression;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final IrExpression applyImplicitIntegerCoercionIfNeeded$applyToElement(IrExpression irExpression, CallAndReferenceGenerator callAndReferenceGenerator, FirExpression firExpression, FirNamedFunctionSymbol firNamedFunctionSymbol, IrSimpleFunctionSymbol irSimpleFunctionSymbol) {
        FirReference reference;
        FirCallableSymbol resolvedCallableSymbol$default;
        if (!ResolveUtilsKt.isIntegerLiteralOrOperatorCall(firExpression) && ((reference = ReferenceUtilsKt.toReference(firExpression, callAndReferenceGenerator.getSession())) == null || (resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(reference, false, 1, null)) == null || !resolvedCallableSymbol$default.getResolvedStatus().isConst() || !ImplicitIntegerCoercionKt.isMarkedWithImplicitIntegerCoercion((FirCallableSymbol<?>) resolvedCallableSymbol$default))) {
            return irExpression;
        }
        IrCallImpl irCallImplIrCallImpl$default = BuildersKt.IrCallImpl$default(irExpression.getStartOffset(), irExpression.getEndOffset(), callAndReferenceGenerator.toIrType(((FirNamedFunction) firNamedFunctionSymbol.getFir()).getReturnTypeRef()), irSimpleFunctionSymbol, 0, (IrStatementOrigin) null, (IrClassSymbol) null, 96, (Object) null);
        irCallImplIrCallImpl$default.getArguments().set(0, irExpression);
        return irCallImplIrCallImpl$default;
    }

    public static /* synthetic */ IrExpression applyReceiversAndArguments$default(CallAndReferenceGenerator callAndReferenceGenerator, IrExpression irExpression, FirStatement firStatement, FirCallableSymbol firCallableSymbol, IrExpression irExpression2, IrExpression irExpression3, int i, Object obj) {
        if ((i & 8) != 0) {
            irExpression3 = null;
        }
        return callAndReferenceGenerator.applyReceiversAndArguments(irExpression, firStatement, firCallableSymbol, irExpression2, irExpression3);
    }

    private final IrExpression applyTypeArguments(IrExpression irExpression, List<? extends ConeKotlinType> list, List<? extends FirTypeParameter> list2) {
        CallAndReferenceGenerator callAndReferenceGenerator;
        IrType irType;
        if ((irExpression instanceof IrMemberAccessExpression) && list != null) {
            int size = list.size();
            IrMemberAccessExpression irMemberAccessExpression = (IrMemberAccessExpression) irExpression;
            if (size > irMemberAccessExpression.getTypeArguments().size()) {
                String strValueOf = irExpression instanceof IrCallImpl ? String.valueOf(((IrCallImpl) irExpression).getSymbol().getSignature()) : "???";
                return BuildersKt.IrErrorExpressionImpl(irExpression.getStartOffset(), irExpression.getEndOffset(), irExpression.getType(), "Cannot bind " + size + " type arguments to " + strValueOf + " call with " + list.size() + " type parameters");
            }
            int i = 0;
            for (ConeKotlinType coneKotlinType : list) {
                int i2 = i + 1;
                FirTypeParameter firTypeParameter = list2 != null ? list2.get(i) : null;
                if (firTypeParameter == null || !firTypeParameter.getIsReified()) {
                    callAndReferenceGenerator = this;
                    irType = callAndReferenceGenerator.toIrType(coneKotlinType);
                } else {
                    callAndReferenceGenerator = this;
                    irType = callAndReferenceGenerator.toIrType(DeclarationApproximationUtilsKt.approximateDeclarationType$default(callAndReferenceGenerator, coneKotlinType, null, false, false, false, 24, null));
                }
                irMemberAccessExpression.getTypeArguments().set(i, irType);
                i = i2;
                this = callAndReferenceGenerator;
            }
        }
        return irExpression;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final IrExpression applyTypeArgumentsWithTypealiasConstructorRemapping(IrExpression irExpression, FirCallableDeclaration firCallableDeclaration, List<? extends FirTypeProjection> list) {
        List<ConeKotlinType> listRefineTypeArgumentsOfTypeAliasConstructor = refineTypeArgumentsOfTypeAliasConstructor(firCallableDeclaration, list);
        FirTypeParametersOwner firTypeParametersOwner = firCallableDeclaration instanceof FirTypeParametersOwner ? (FirTypeParametersOwner) firCallableDeclaration : null;
        return applyTypeArguments(irExpression, listRefineTypeArgumentsOfTypeAliasConstructor, firTypeParametersOwner != null ? firTypeParametersOwner.getTypeParameters() : null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static IrExpression c(FirBasedSymbol firBasedSymbol, FirReference firReference, FirQualifiedAccessExpression firQualifiedAccessExpression, IrDynamicOperator irDynamicOperator, CallAndReferenceGenerator callAndReferenceGenerator, IrType irType, IrExpression irExpression, boolean z, int i, int i2) {
        Name name;
        Name name2;
        if (!(firBasedSymbol instanceof FirFunctionSymbol)) {
            if (!(firBasedSymbol instanceof FirPropertySymbol)) {
                IrErrorCallExpression irErrorCallExpressionGenerateErrorCallExpression = callAndReferenceGenerator.generateErrorCallExpression(i, i2, firReference, irType);
                return (!(firQualifiedAccessExpression instanceof FirCall) || z) ? irErrorCallExpressionGenerateErrorCallExpression : applyReceiversAndArguments$default(callAndReferenceGenerator, irErrorCallExpressionGenerateErrorCallExpression, firQualifiedAccessExpression, null, null, null, 8, null);
            }
            FirResolvedNamedReference resolved = FirReferenceUtilsKt.getResolved(firReference);
            if (resolved == null || (name = resolved.getName()) == null) {
                f2f.a("Property access must have a name: ", UtilsKt.render(firQualifiedAccessExpression));
                return null;
            }
            String identifier = name.getIdentifier();
            identifier.getClass();
            return BuildersKt.IrDynamicMemberExpressionImpl(i, i2, irType, identifier, irExpression);
        }
        FirResolvedNamedReference resolved2 = FirReferenceUtilsKt.getResolved(firReference);
        if (resolved2 == null || (name2 = resolved2.getName()) == null) {
            f2f.a("Callee reference must have a name: ", UtilsKt.render(firQualifiedAccessExpression));
            return null;
        }
        if (irDynamicOperator == null) {
            if (callAndReferenceGenerator.isOperatorCall(firQualifiedAccessExpression)) {
                IrDynamicOperator dynamicOperator = callAndReferenceGenerator.getDynamicOperator(name2);
                if (dynamicOperator == null) {
                    dynamicOperator = callAndReferenceGenerator.getDynamicOperator(firQualifiedAccessExpression);
                }
                irDynamicOperator = dynamicOperator;
            } else {
                irDynamicOperator = null;
            }
            if (irDynamicOperator == null) {
                irDynamicOperator = IrDynamicOperator.INVOKE;
            }
        }
        IrDynamicOperatorExpressionImpl IrDynamicOperatorExpressionImpl = BuildersKt.IrDynamicOperatorExpressionImpl(i, i2, Intrinsics.areEqual(name2, OperatorNameConventions.COMPARE_TO) ? callAndReferenceGenerator.getTypeConverter().getBuiltins().getBooleanType() : irType, irDynamicOperator);
        if (irDynamicOperator == IrDynamicOperator.INVOKE && !(firQualifiedAccessExpression instanceof FirImplicitInvokeCall)) {
            String identifier2 = name2.getIdentifier();
            identifier2.getClass();
            irExpression = BuildersKt.IrDynamicMemberExpressionImpl(i, i2, irType, identifier2, irExpression);
        }
        IrDynamicOperatorExpressionImpl.setReceiver(irExpression);
        if (!z && (firQualifiedAccessExpression instanceof FirCall)) {
            FirArgumentList argumentList = ((FirCall) firQualifiedAccessExpression).getArgumentList();
            LinkedHashMap<FirExpression, FirValueParameter> mapping = argumentList instanceof FirResolvedArgumentList ? ((FirResolvedArgumentList) argumentList).getMapping() : null;
            if (mapping != null && (callAndReferenceGenerator.visitor.get_annotationMode() || !mapping.isEmpty())) {
                Set<FirExpression> setKeySet = mapping.keySet();
                setKeySet.getClass();
                Object objFirstOrNull = CollectionsKt.firstOrNull(setKeySet);
                FirVarargArgumentsExpression firVarargArgumentsExpression = objFirstOrNull instanceof FirVarargArgumentsExpression ? (FirVarargArgumentsExpression) objFirstOrNull : null;
                if (firVarargArgumentsExpression == null) {
                    f2f.a("Dynamic call must have a single vararg argument: ", UtilsKt.render(firQualifiedAccessExpression));
                    return null;
                }
                Iterator<FirExpression> it = firVarargArgumentsExpression.getArguments().iterator();
                while (it.hasNext()) {
                    IrDynamicOperatorExpressionImpl.getArguments().add(callAndReferenceGenerator.convertArgument(it.next(), null, ConeSubstitutor.Empty.INSTANCE));
                }
            }
        }
        return IrDynamicOperatorExpressionImpl;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final IrExpression convertArgument(FirExpression argument, FirValueParameter parameter, ConeSubstitutor substitutor) throws KotlinIllegalArgumentExceptionWithAttachments {
        ConeKotlinType coneKotlinTypeArrayElementType$default;
        FirTypeRef returnTypeRef;
        ConeKotlinType coneType;
        ConeKotlinType coneKotlinTypeFullyExpandedType = (parameter == null || (returnTypeRef = parameter.getReturnTypeRef()) == null || (coneType = FirTypeUtilsKt.getConeType(returnTypeRef)) == null) ? null : TypeExpansionUtilsKt.fullyExpandedType(this, coneType);
        Fir2IrVisitor fir2IrVisitor = this.visitor;
        IrExpression irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default = Fir2IrVisitor.convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(fir2IrVisitor, argument, false, (fir2IrVisitor.get_annotationMode() && coneKotlinTypeFullyExpandedType != null && ConeBuiltinTypeUtilsKt.isArrayType(coneKotlinTypeFullyExpandedType)) ? coneKotlinTypeFullyExpandedType : null, 2, null);
        if (coneKotlinTypeFullyExpandedType != null) {
            if (parameter.getIsVararg()) {
                coneKotlinTypeArrayElementType$default = FirTypeUtilsKt.arrayElementType$default(coneKotlinTypeFullyExpandedType, false, 1, null);
                coneKotlinTypeArrayElementType$default.getClass();
            } else {
                coneKotlinTypeArrayElementType$default = coneKotlinTypeFullyExpandedType;
            }
            irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default = ImplicitConversionUtilsKt.prepareExpressionForGivenExpectedType$default(this, irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default, argument, null, coneKotlinTypeFullyExpandedType, substitutor.substituteOrSelf(coneKotlinTypeArrayElementType$default), false, 4, null);
        }
        return applyImplicitIntegerCoercionIfNeeded(irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default, argument, parameter);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final Unit convertToIrAnnotation$lambda$1$0(Ref.ObjectRef objectRef, FirConstructorSymbol firConstructorSymbol) {
        firConstructorSymbol.getClass();
        if (((FirConstructor) firConstructorSymbol.getFir()).getIsPrimary() && objectRef.element == null) {
            objectRef.element = firConstructorSymbol;
        }
        return Unit.INSTANCE;
    }

    public static /* synthetic */ IrExpression convertToIrCall$default(CallAndReferenceGenerator callAndReferenceGenerator, FirQualifiedAccessExpression firQualifiedAccessExpression, ConeKotlinType coneKotlinType, IrExpression irExpression, IrDynamicOperator irDynamicOperator, boolean z, boolean z2, int i, Object obj) {
        if ((i & 8) != 0) {
            irDynamicOperator = null;
        }
        return callAndReferenceGenerator.convertToIrCall(firQualifiedAccessExpression, coneKotlinType, irExpression, irDynamicOperator, (i & 16) != 0 ? false : z, (i & 32) != 0 ? false : z2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static final IrExpression convertToIrCall$lambda$0$2(CallAndReferenceGenerator callAndReferenceGenerator, FirExpression firExpression, int i, int i2) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrExpression irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default = Fir2IrVisitor.convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(callAndReferenceGenerator.visitor, firExpression, false, null, 6, null);
        irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default.setStartOffset(i);
        irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default.setEndOffset(i2);
        return irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default;
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Multi-variable type inference failed */
    public static final IrTypeOperatorCallImpl convertToIrCall$lambda$0$3(FirReference firReference, CallAndReferenceGenerator callAndReferenceGenerator, FirQualifiedAccessExpression firQualifiedAccessExpression, IrExpression irExpression, int i, int i2) {
        FirReceiverParameter receiverParameter;
        FirTypeRef typeRef;
        IrType irType;
        ConeSimpleKotlinType dispatchReceiverType;
        FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(firReference, false, 1, null);
        FirCallableDeclaration firCallableDeclaration = resolvedCallableSymbol$default != null ? (FirCallableDeclaration) resolvedCallableSymbol$default.getFir() : null;
        if (firCallableDeclaration == null || (dispatchReceiverType = firCallableDeclaration.getDispatchReceiverType()) == null || (irType = callAndReferenceGenerator.toIrType(dispatchReceiverType)) == null) {
            if (firCallableDeclaration == null || (receiverParameter = firCallableDeclaration.getReceiverParameter()) == null || (typeRef = receiverParameter.getTypeRef()) == null) {
                f2f.a("Couldn't get the proper receiver for call ", UtilsKt.render(firQualifiedAccessExpression));
                return null;
            }
            irType = callAndReferenceGenerator.toIrType(typeRef);
        }
        IrType irType2 = irType;
        return BuildersKt.IrTypeOperatorCallImpl(i, i2, irType2, IrTypeOperator.IMPLICIT_DYNAMIC_CAST, irType2, irExpression);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    public static final IrExpression convertToIrCall$lambda$0$4(FirCallableSymbol firCallableSymbol, CallAndReferenceGenerator callAndReferenceGenerator, FirExpression firExpression, FirQualifiedAccessExpression firQualifiedAccessExpression, IrType irType, FirReference firReference, IrExpression irExpression, boolean z, int i, int i2) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrStatementOriginImpl irStatementOriginImplStatementOrigin;
        IrType irType2;
        int i3;
        IrSymbol irSymbolForCall = firCallableSymbol != null ? SymbolConversionUtilsKt.toIrSymbolForCall(callAndReferenceGenerator, firCallableSymbol, firExpression, firQualifiedAccessExpression.getExplicitReceiver()) : null;
        if (irSymbolForCall instanceof IrConstructorSymbol) {
            if (!(firCallableSymbol instanceof FirConstructorSymbol)) {
                w01.a("Failed requirement.");
                return null;
            }
            D fir = ScopeUtilsKt.unwrapCallRepresentative$default(callAndReferenceGenerator, firCallableSymbol, null, 2, null).getFir();
            fir.getClass();
            FirConstructor firConstructor = (FirConstructor) fir;
            int size = firConstructor.getTypeParameters().size();
            List<FirTypeParameterRef> typeParameters = firConstructor.getTypeParameters();
            if ((typeParameters instanceof Collection) && typeParameters.isEmpty()) {
                i3 = 0;
            } else {
                Iterator<T> it = typeParameters.iterator();
                int i4 = 0;
                while (it.hasNext()) {
                    if ((((FirTypeParameterRef) it.next()) instanceof FirTypeParameter) && (i4 = i4 + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
                i3 = i4;
            }
            if (DeclarationUtilsKt.isAnnotationConstructor(firCallableSymbol, callAndReferenceGenerator.getSession())) {
                return BuildersKt.IrAnnotationImplWithShape$default(i, i2, irType, (IrConstructorSymbol) irSymbolForCall, size, i3, callAndReferenceGenerator.valueParametersSize(firCallableSymbol), firConstructor.getContextParameters().size(), ((FirConstructorSymbol) firCallableSymbol).getDispatchReceiverType() != null, FirSymbolStatusUtilsKt.isInstanceExtension(firCallableSymbol), (IrStatementOrigin) null, (SourceElement) null, 3072, (Object) null);
            }
            return BuildersKt.IrConstructorCallImplWithShape$default(i, i2, irType, (IrConstructorSymbol) irSymbolForCall, size, i3, callAndReferenceGenerator.valueParametersSize(firCallableSymbol), firConstructor.getContextParameters().size(), ((FirConstructorSymbol) firCallableSymbol).getDispatchReceiverType() != null, FirSymbolStatusUtilsKt.isInstanceExtension(firCallableSymbol), (IrStatementOrigin) null, (SourceElement) null, 3072, (Object) null);
        }
        IrSymbol irSymbol = irSymbolForCall;
        boolean z2 = false;
        if (irSymbol instanceof IrSimpleFunctionSymbol) {
            IrStatementOrigin irStatementOriginStatementOrigin = OriginUtilsKt.statementOrigin(firReference);
            if (irExpression != null) {
                KtSourceElement source = firReference.getSource();
                if (((source != null ? source.getKind() : null) instanceof KtFakeSourceElementKind.DesugaredAugmentedAssign) && irStatementOriginStatementOrigin != null && !Intrinsics.areEqual(firCallableSymbol.getName(), OperatorNameConventions.GET) && !Intrinsics.areEqual(firCallableSymbol.getName(), OperatorNameConventions.SET)) {
                    callAndReferenceGenerator.updateStatementOrigin(irExpression, irStatementOriginStatementOrigin);
                }
            }
            IrSimpleFunctionSymbol irSimpleFunctionSymbol = (IrSimpleFunctionSymbol) irSymbol;
            int size2 = firCallableSymbol.getTypeParameterSymbols().size();
            int iValueParametersSize = callAndReferenceGenerator.valueParametersSize(firCallableSymbol);
            int size3 = ((FirCallableDeclaration) firCallableSymbol.getFir()).getContextParameters().size();
            if (firCallableSymbol.getDispatchReceiverType() != null) {
                z2 = true;
            }
            IrCallImpl irCallImplIrCallImplWithShape = BuildersKt.IrCallImplWithShape(i, i2, irType, irSimpleFunctionSymbol, size2, iValueParametersSize, size3, z2, FirSymbolStatusUtilsKt.isInstanceExtension(firCallableSymbol), irStatementOriginStatementOrigin, firExpression != null ? callAndReferenceGenerator.superQualifierSymbolForFunctionAndPropertyAccess(firExpression) : null);
            if (firQualifiedAccessExpression instanceof FirImplicitInvokeCall) {
                CommonIrAttributesKt.setImplicitInvoke(irCallImplIrCallImplWithShape, true);
            }
            return irCallImplIrCallImplWithShape;
        }
        if (irSymbol instanceof IrLocalDelegatedPropertySymbol) {
            IrSimpleFunctionSymbol irSimpleFunctionSymbolFindGetterOfProperty = callAndReferenceGenerator.getDeclarationStorage().findGetterOfProperty((IrLocalDelegatedPropertySymbol) irSymbol);
            FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(firReference, false, 1, null);
            resolvedCallableSymbol$default.getClass();
            return BuildersKt.IrCallImpl(i, i2, irType, irSimpleFunctionSymbolFindGetterOfProperty, ((FirCallableDeclaration) resolvedCallableSymbol$default.getFir()).getTypeParameters().size(), IrStatementOrigin.Companion.getGET_LOCAL_PROPERTY(), firExpression != null ? callAndReferenceGenerator.superQualifierSymbolForFunctionAndPropertyAccess(firExpression) : null);
        }
        if (!(irSymbol instanceof IrPropertySymbol)) {
            if (irSymbol instanceof IrFieldSymbol) {
                return BuildersKt.IrGetFieldImpl$default(i, i2, (IrFieldSymbol) irSymbol, irType, (IrStatementOrigin) null, firExpression != null ? callAndReferenceGenerator.superQualifierSymbolForFieldAccess(firExpression, firCallableSymbol) : null, 16, (Object) null);
            }
            if (!(irSymbol instanceof IrValueSymbol)) {
                return irSymbol instanceof IrEnumEntrySymbol ? BuildersKt.IrGetEnumValueImpl(i, i2, irType, (IrEnumEntrySymbol) irSymbol) : callAndReferenceGenerator.generateErrorCallExpression(i, i2, firReference, irType);
            }
            FirVariableSymbol resolvedVariableSymbol$default = FirReferenceUtilsKt.toResolvedVariableSymbol$default(firReference, false, 1, null);
            resolvedVariableSymbol$default.getClass();
            IrType irTypeIrTypeForPotentiallyComponentCall = VariousUtilsKt.irTypeForPotentiallyComponentCall(callAndReferenceGenerator, (FirVariable) resolvedVariableSymbol$default.getFir(), irType);
            IrValueSymbol irValueSymbol = (IrValueSymbol) irSymbol;
            if (z) {
                irStatementOriginImplStatementOrigin = IrStatementOrigin.Companion.getVARIABLE_AS_FUNCTION();
            } else {
                Map<KtFakeSourceElementKind, IrStatementOrigin> incOrDecSourceKindToIrStatementOrigin = OriginUtilsKt.getIncOrDecSourceKindToIrStatementOrigin();
                KtSourceElement source2 = firQualifiedAccessExpression.getSource();
                irStatementOriginImplStatementOrigin = (IrStatementOrigin) incOrDecSourceKindToIrStatementOrigin.get(source2 != null ? source2.getKind() : null);
                if (irStatementOriginImplStatementOrigin == null) {
                    irStatementOriginImplStatementOrigin = OriginUtilsKt.statementOrigin(firReference);
                }
            }
            return BuildersKt.IrGetValueImpl(i, i2, irTypeIrTypeForPotentiallyComponentCall, irValueSymbol, irStatementOriginImplStatementOrigin);
        }
        FirPropertySymbol resolvedPropertySymbol$default = FirReferenceUtilsKt.toResolvedPropertySymbol$default(firReference, false, 1, null);
        resolvedPropertySymbol$default.getClass();
        FirProperty firProperty = (FirProperty) resolvedPropertySymbol$default.getFir();
        IrPropertySymbol irPropertySymbol = (IrPropertySymbol) irSymbol;
        IrSimpleFunctionSymbol irSimpleFunctionSymbolFindGetterOfProperty2 = callAndReferenceGenerator.getDeclarationStorage().findGetterOfProperty(irPropertySymbol);
        IrFieldSymbol irFieldSymbolFindBackingFieldOfProperty = callAndReferenceGenerator.getDeclarationStorage().findBackingFieldOfProperty(irPropertySymbol);
        if (irSimpleFunctionSymbolFindGetterOfProperty2 == null) {
            if (irFieldSymbolFindBackingFieldOfProperty != null) {
                return BuildersKt.IrGetFieldImpl$default(i, i2, irFieldSymbolFindBackingFieldOfProperty, irType, (IrStatementOrigin) null, firExpression != null ? callAndReferenceGenerator.superQualifierSymbolForFieldAccess(firExpression, firCallableSymbol) : null, 16, (Object) null);
            }
            return BuildersKt.IrErrorCallExpressionImpl(i, i2, irType, "No getter or backing field found for " + UtilsKt.render(firReference));
        }
        if (VariousUtilsKt.isInlineClassProperty(firCallableSymbol) && ClassMembersKt.isIntersectionOverride(firProperty) && (firProperty.getDispatchReceiverType() instanceof ConeIntersectionType)) {
            FirCallableDeclaration originalForIntersectionOverrideAttr = ClassMembersKt.isIntersectionOverride(firProperty) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firProperty) : null;
            originalForIntersectionOverrideAttr.getClass();
            irType2 = callAndReferenceGenerator.toIrType(((FirProperty) originalForIntersectionOverrideAttr).getReturnTypeRef());
        } else {
            irType2 = irType;
        }
        int size4 = firProperty.getTypeParameters().size();
        int size5 = firProperty.getContextParameters().size();
        int size6 = firProperty.getContextParameters().size();
        if (firProperty.getDispatchReceiverType() != null) {
            z2 = true;
        }
        boolean zIsInstanceExtension = FirDeclarationUtilKt.isInstanceExtension(firProperty);
        Map<KtFakeSourceElementKind, IrStatementOrigin> incOrDecSourceKindToIrStatementOrigin2 = OriginUtilsKt.getIncOrDecSourceKindToIrStatementOrigin();
        KtSourceElement source3 = firQualifiedAccessExpression.getSource();
        IrStatementOriginImpl get_property = (IrStatementOrigin) incOrDecSourceKindToIrStatementOrigin2.get(source3 != null ? source3.getKind() : null);
        if (get_property == null) {
            Map<KtFakeSourceElementKind.DesugaredAugmentedAssign, IrStatementOrigin> augmentedAssignSourceKindToIrStatementOrigin = OriginUtilsKt.getAugmentedAssignSourceKindToIrStatementOrigin();
            KtSourceElement source4 = firQualifiedAccessExpression.getSource();
            get_property = (IrStatementOrigin) augmentedAssignSourceKindToIrStatementOrigin.get(source4 != null ? source4.getKind() : null);
            if (get_property == null) {
                get_property = IrStatementOrigin.Companion.getGET_PROPERTY();
            }
        }
        return BuildersKt.IrCallImplWithShape(i, i2, irType2, irSimpleFunctionSymbolFindGetterOfProperty2, size4, size5, size6, z2, zIsInstanceExtension, get_property, firExpression != null ? callAndReferenceGenerator.superQualifierSymbolForFunctionAndPropertyAccess(firExpression) : null);
    }

    private final IrExpression convertToIrCallForDynamic(final FirQualifiedAccessExpression qualifiedAccess, IrExpression explicitReceiverExpression, final IrType type, final FirReference calleeReference, final FirBasedSymbol<?> symbol, final IrDynamicOperator dynamicOperator, final boolean noArguments) {
        final IrExpression irExpressionFindIrDynamicReceiver = findIrDynamicReceiver(qualifiedAccess, explicitReceiverExpression);
        return OffsetUtilsKt.convertWithOffsets(qualifiedAccess, new Function2() { // from class: ba1
            public final Object invoke(Object obj, Object obj2) {
                return CallAndReferenceGenerator.c(symbol, calleeReference, qualifiedAccess, dynamicOperator, this, type, irExpressionFindIrDynamicReceiver, noArguments, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
            }
        });
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final IrExpression convertToIrCallableReference$lambda$1$convertReferenceToField(CallAndReferenceGenerator callAndReferenceGenerator, int i, int i2, IrType irType, IrStatementOriginImpl irStatementOriginImpl, FirCallableReferenceAccess firCallableReferenceAccess, FirCallableSymbol<?> firCallableSymbol, IrExpression irExpression, boolean z, FirFieldSymbol firFieldSymbol) {
        FirField firField = (FirField) firFieldSymbol.getFir();
        IrSymbol irSymbolConvertToIrCallableReference$lambda$1$toSymbolForCall = convertToIrCallableReference$lambda$1$toSymbolForCall(firFieldSymbol, callAndReferenceGenerator, firCallableReferenceAccess, z);
        irSymbolConvertToIrCallableReference$lambda$1$toSymbolForCall.getClass();
        IrPropertySymbol irPropertySymbol = (IrPropertySymbol) irSymbolConvertToIrCallableReference$lambda$1$toSymbolForCall;
        IrFieldSymbol irFieldSymbolFindBackingFieldOfProperty = callAndReferenceGenerator.getDeclarationStorage().findBackingFieldOfProperty(irPropertySymbol);
        irFieldSymbolFindBackingFieldOfProperty.getClass();
        return applyReceiversAndArguments$default(callAndReferenceGenerator, BuildersKt.IrPropertyReferenceImpl(i, i2, irType, irPropertySymbol, 0, irFieldSymbolFindBackingFieldOfProperty, !firField.getStatus().isStatic() ? callAndReferenceGenerator.getDeclarationStorage().findGetterOfProperty(irPropertySymbol) : null, firField.getStatus().isStatic() ? null : callAndReferenceGenerator.getDeclarationStorage().findSetterOfProperty(irPropertySymbol), irStatementOriginImpl), firCallableReferenceAccess, firCallableSymbol, irExpression, null, 8, null);
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final IrExpression convertToIrCallableReference$lambda$1$convertReferenceToFunction(IrType irType, FirCallableReferenceAccess firCallableReferenceAccess, CallAndReferenceGenerator callAndReferenceGenerator, int i, int i2, FirCallableSymbol<?> firCallableSymbol, IrExpression irExpression, boolean z, FirFunctionSymbol<?> firFunctionSymbol) {
        TypeAliasConstructorInfo typeAliasConstructorInfo;
        FirConstructor firConstructor;
        IrSymbol irSymbolConvertToIrCallableReference$lambda$1$toSymbolForCall = convertToIrCallableReference$lambda$1$toSymbolForCall(firFunctionSymbol, callAndReferenceGenerator, firCallableReferenceAccess, z);
        IrFunctionSymbol irFunctionSymbol = irSymbolConvertToIrCallableReference$lambda$1$toSymbolForCall instanceof IrFunctionSymbol ? (IrFunctionSymbol) irSymbolConvertToIrCallableReference$lambda$1$toSymbolForCall : null;
        if (irFunctionSymbol == null) {
            return null;
        }
        if (!(irType instanceof IrSimpleType)) {
            w01.a("Failed requirement.");
            return null;
        }
        FirFunctionSymbol resolvedFunctionSymbol$default = FirReferenceUtilsKt.toResolvedFunctionSymbol$default(firCallableReferenceAccess.getCalleeReference(), false, 1, null);
        resolvedFunctionSymbol$default.getClass();
        FirFunction firFunction = (FirFunction) resolvedFunctionSymbol$default.getFir();
        if ((firFunction instanceof FirConstructor) && (typeAliasConstructorInfo = TypeAliasConstructorsSubstitutingScopeKt.getTypeAliasConstructorInfo(firFunction)) != null && (firConstructor = (FirConstructor) typeAliasConstructorInfo.getOriginalConstructor()) != null) {
            firFunction = firConstructor;
        }
        IrSimpleType irSimpleType = (IrSimpleType) irType;
        if (callAndReferenceGenerator.getAdapterGenerator().needToGenerateAdaptedCallableReference$org_jetbrains_kotlin_fir2ir(firCallableReferenceAccess, irSimpleType, firFunction)) {
            return callAndReferenceGenerator.getAdapterGenerator().generateAdaptedCallableReference$org_jetbrains_kotlin_fir2ir(firCallableReferenceAccess, irExpression, irFunctionSymbol, irSimpleType);
        }
        return applyReceiversAndArguments$default(callAndReferenceGenerator, callAndReferenceGenerator.applyTypeArguments$org_jetbrains_kotlin_fir2ir(BuildersKt.IrFunctionReferenceImplWithShape$default(i, i2, irType, irFunctionSymbol, firFunction.getTypeParameters().size(), firFunction.getValueParameters().size() + firFunction.getContextParameters().size(), firFunction.getContextParameters().size(), firFunction.getDispatchReceiverType() != null, FirDeclarationUtilKt.isInstanceExtension(firFunction), irFunctionSymbol, (IrStatementOrigin) null, 1024, (Object) null), firCallableReferenceAccess), firCallableReferenceAccess, firCallableSymbol, irExpression, null, 8, null);
    }

    private static final IrExpression convertToIrCallableReference$lambda$1$convertReferenceToLocalDelegatedProperty(int i, int i2, IrType irType, CallAndReferenceGenerator callAndReferenceGenerator, IrStatementOriginImpl irStatementOriginImpl, FirCallableReferenceAccess firCallableReferenceAccess, boolean z, FirPropertySymbol firPropertySymbol) {
        IrSymbol irSymbolConvertToIrCallableReference$lambda$1$toSymbolForCall = convertToIrCallableReference$lambda$1$toSymbolForCall(firPropertySymbol, callAndReferenceGenerator, firCallableReferenceAccess, z);
        IrLocalDelegatedPropertySymbol irLocalDelegatedPropertySymbol = irSymbolConvertToIrCallableReference$lambda$1$toSymbolForCall instanceof IrLocalDelegatedPropertySymbol ? (IrLocalDelegatedPropertySymbol) irSymbolConvertToIrCallableReference$lambda$1$toSymbolForCall : null;
        if (irLocalDelegatedPropertySymbol == null) {
            return null;
        }
        return BuildersKt.IrLocalDelegatedPropertyReferenceImpl(i, i2, irType, irLocalDelegatedPropertySymbol, callAndReferenceGenerator.getDeclarationStorage().findDelegateVariableOfProperty(irLocalDelegatedPropertySymbol), callAndReferenceGenerator.getDeclarationStorage().findGetterOfProperty(irLocalDelegatedPropertySymbol), callAndReferenceGenerator.getDeclarationStorage().findSetterOfProperty(irLocalDelegatedPropertySymbol), irStatementOriginImpl);
    }

    private static final IrExpression convertToIrCallableReference$lambda$1$convertReferenceToRegularProperty(CallAndReferenceGenerator callAndReferenceGenerator, FirCallableReferenceAccess firCallableReferenceAccess, int i, int i2, IrType irType, IrStatementOriginImpl irStatementOriginImpl, FirCallableSymbol<?> firCallableSymbol, IrExpression irExpression, boolean z, FirPropertySymbol firPropertySymbol) {
        FirCallableDeclaration firCallableDeclaration;
        List<FirTypeParameterRef> typeParameters;
        IrSymbol irSymbolConvertToIrCallableReference$lambda$1$toSymbolForCall = convertToIrCallableReference$lambda$1$toSymbolForCall(firPropertySymbol, callAndReferenceGenerator, firCallableReferenceAccess, z);
        IrPropertySymbol irPropertySymbol = irSymbolConvertToIrCallableReference$lambda$1$toSymbolForCall instanceof IrPropertySymbol ? (IrPropertySymbol) irSymbolConvertToIrCallableReference$lambda$1$toSymbolForCall : null;
        if (irPropertySymbol == null) {
            return null;
        }
        IrSimpleFunctionSymbol irSimpleFunctionSymbolFindGetterOfProperty = callAndReferenceGenerator.getDeclarationStorage().findGetterOfProperty(irPropertySymbol);
        IrSimpleFunctionSymbol irSimpleFunctionSymbolFindSetterOfProperty = InferenceUtilsKt.isKMutableProperty(FirTypeUtilsKt.getResolvedType(firCallableReferenceAccess), callAndReferenceGenerator.getSession()) ? callAndReferenceGenerator.getDeclarationStorage().findSetterOfProperty(irPropertySymbol) : null;
        IrFieldSymbol irFieldSymbolFindBackingFieldOfProperty = irSimpleFunctionSymbolFindGetterOfProperty == null ? callAndReferenceGenerator.getDeclarationStorage().findBackingFieldOfProperty(irPropertySymbol) : null;
        FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(firCallableReferenceAccess);
        return applyReceiversAndArguments$default(callAndReferenceGenerator, callAndReferenceGenerator.applyTypeArguments$org_jetbrains_kotlin_fir2ir(BuildersKt.IrPropertyReferenceImpl(i, i2, irType, irPropertySymbol, (resolvedCallableSymbol == null || (firCallableDeclaration = (FirCallableDeclaration) resolvedCallableSymbol.getFir()) == null || (typeParameters = firCallableDeclaration.getTypeParameters()) == null) ? 0 : typeParameters.size(), irFieldSymbolFindBackingFieldOfProperty, irSimpleFunctionSymbolFindGetterOfProperty, irSimpleFunctionSymbolFindSetterOfProperty, irStatementOriginImpl), firCallableReferenceAccess), firCallableReferenceAccess, firCallableSymbol, irExpression, null, 8, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private static final IrExpression convertToIrCallableReference$lambda$1$convertReferenceToSyntheticProperty(CallAndReferenceGenerator callAndReferenceGenerator, FirCallableReferenceAccess firCallableReferenceAccess, int i, int i2, IrType irType, IrStatementOriginImpl irStatementOriginImpl, FirCallableSymbol<?> firCallableSymbol, IrExpression irExpression, FirSimpleSyntheticPropertySymbol firSimpleSyntheticPropertySymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrSimpleFunctionSymbol irSimpleFunctionSymbol;
        FirCallableDeclaration firCallableDeclaration;
        List<FirTypeParameterRef> typeParameters;
        FirSyntheticPropertyAccessor setter;
        FirCallableDeclaration delegate;
        IrPropertySymbol symbol = callAndReferenceGenerator.getCallablesGenerator().generateIrPropertyForSyntheticPropertyReference(firSimpleSyntheticPropertySymbol, callAndReferenceGenerator.conversionScope.parentFromStack()).getSymbol();
        FirSyntheticProperty syntheticProperty = firSimpleSyntheticPropertySymbol.getSyntheticProperty();
        Fir2IrDeclarationStorage declarationStorage = callAndReferenceGenerator.getDeclarationStorage();
        FirCallableDeclaration delegate2 = syntheticProperty.getGetter().getDelegate();
        while (true) {
            irSimpleFunctionSymbol = null;
            if (!Intrinsics.areEqual(delegate2.getOrigin(), FirDeclarationOrigin.SubstitutionOverride.CallSite.INSTANCE)) {
                break;
            }
            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(delegate2) || (delegate2.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(delegate2) : null;
            if (originalForSubstitutionOverrideAttr == null) {
                break;
            }
            delegate2 = originalForSubstitutionOverrideAttr;
        }
        IrSimpleFunctionSymbol irFunctionSymbol$default = Fir2IrDeclarationStorage.getIrFunctionSymbol$default(declarationStorage, ((FirNamedFunction) delegate2).getSymbol(), null, false, 6, null);
        IrSimpleFunctionSymbol irSimpleFunctionSymbol2 = irFunctionSymbol$default instanceof IrSimpleFunctionSymbol ? irFunctionSymbol$default : null;
        if (irSimpleFunctionSymbol2 == null) {
            return null;
        }
        if (InferenceUtilsKt.isKMutableProperty(FirTypeUtilsKt.getResolvedType(firCallableReferenceAccess), callAndReferenceGenerator.getSession()) && (setter = syntheticProperty.getSetter()) != null && (delegate = setter.getDelegate()) != null) {
            while (Intrinsics.areEqual(delegate.getOrigin(), FirDeclarationOrigin.SubstitutionOverride.CallSite.INSTANCE)) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr2 = (ClassMembersKt.isSubstitutionOverride(delegate) || (delegate.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(delegate) : null;
                if (originalForSubstitutionOverrideAttr2 == null) {
                    break;
                }
                delegate = originalForSubstitutionOverrideAttr2;
            }
            FirNamedFunctionSymbol symbol2 = ((FirNamedFunction) delegate).getSymbol();
            if (symbol2 != null) {
                IrFunctionSymbol irFunctionSymbol$default2 = Fir2IrDeclarationStorage.getIrFunctionSymbol$default(callAndReferenceGenerator.getDeclarationStorage(), symbol2, null, false, 6, null);
                IrSimpleFunctionSymbol irSimpleFunctionSymbol3 = irFunctionSymbol$default2 instanceof IrSimpleFunctionSymbol ? (IrSimpleFunctionSymbol) irFunctionSymbol$default2 : null;
                if (irSimpleFunctionSymbol3 == null) {
                    return null;
                }
                irSimpleFunctionSymbol = irSimpleFunctionSymbol3;
            }
        }
        IrSimpleFunctionSymbol irSimpleFunctionSymbol4 = irSimpleFunctionSymbol;
        FirCallableSymbol<?> resolvedCallableSymbol = ReferenceUtilsKt.toResolvedCallableSymbol(firCallableReferenceAccess);
        return applyReceiversAndArguments$default(callAndReferenceGenerator, callAndReferenceGenerator.applyTypeArguments$org_jetbrains_kotlin_fir2ir(BuildersKt.IrPropertyReferenceImpl(i, i2, irType, symbol, (resolvedCallableSymbol == null || (firCallableDeclaration = (FirCallableDeclaration) resolvedCallableSymbol.getFir()) == null || (typeParameters = firCallableDeclaration.getTypeParameters()) == null) ? 0 : typeParameters.size(), (IrFieldSymbol) null, irSimpleFunctionSymbol2, irSimpleFunctionSymbol4, irStatementOriginImpl), firCallableReferenceAccess), firCallableReferenceAccess, firCallableSymbol, irExpression, null, 8, null);
    }

    private static final IrSymbol convertToIrCallableReference$lambda$1$toSymbolForCall(FirCallableSymbol<?> firCallableSymbol, CallAndReferenceGenerator callAndReferenceGenerator, FirCallableReferenceAccess firCallableReferenceAccess, boolean z) {
        return SymbolConversionUtilsKt.toIrSymbolForCallableReference(callAndReferenceGenerator, firCallableSymbol, firCallableReferenceAccess.getDispatchReceiver(), firCallableReferenceAccess.getExplicitReceiver(), z);
    }

    /* JADX WARN: Code duplicated, block: B:34:0x006b  */
    private final IrExpression convertToIrSetCallForDynamic(FirVariableAssignment variableAssignment, IrExpression explicitReceiverExpression, IrType type, FirReference calleeReference, FirBasedSymbol<?> symbol, IrExpression assignedValue) {
        IrExpression irExpressionFindIrDynamicReceiver;
        int i;
        int i2;
        Name name;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        FirQualifiedAccessExpression firQualifiedAccessExpressionUnwrapLValue = FirExpressionUtilKt.unwrapLValue(variableAssignment);
        if (firQualifiedAccessExpressionUnwrapLValue == null || (irExpressionFindIrDynamicReceiver = findIrDynamicReceiver(firQualifiedAccessExpressionUnwrapLValue, explicitReceiverExpression)) == null) {
            f2f.a("Assignment has no lValue: ", UtilsKt.render(variableAssignment));
            return null;
        }
        KtSourceElement source = variableAssignment.getSource();
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            i = -1;
            i2 = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                i = -1;
                i2 = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    i = -1;
                    i2 = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        i = -1;
                        i2 = -1;
                    } else {
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                            startOffset = source != null ? source.getStartOffset() : -1;
                        } else {
                            startOffset = numStartOffsetSkippingComments.intValue();
                        }
                        int endOffset = source != null ? source.getEndOffset() : -1;
                        i = startOffset;
                        i2 = endOffset;
                    }
                }
            }
        }
        if (!(symbol instanceof FirPropertySymbol)) {
            return generateErrorCallExpression$default(this, i, i2, calleeReference, null, 8, null);
        }
        FirResolvedNamedReference resolved = FirReferenceUtilsKt.getResolved(calleeReference);
        if (resolved == null || (name = resolved.getName()) == null) {
            f2f.a("There must be a name: ", UtilsKt.render(variableAssignment));
            return null;
        }
        IrDynamicOperatorExpressionImpl IrDynamicOperatorExpressionImpl = BuildersKt.IrDynamicOperatorExpressionImpl(i, i2, type, IrDynamicOperator.EQ);
        String identifier = name.getIdentifier();
        identifier.getClass();
        IrDynamicOperatorExpressionImpl.setReceiver(BuildersKt.IrDynamicMemberExpressionImpl(i, i2, type, identifier, irExpressionFindIrDynamicReceiver));
        IrDynamicOperatorExpressionImpl.getArguments().add(assignedValue);
        return IrDynamicOperatorExpressionImpl;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    private final void evaluateAndApplyJsCallArg(FirFunctionCall jsFirCall, IrCall jsIrCall) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrExpression irExpression;
        FirEvaluatorResult firEvaluatorResultEvaluateExpression;
        FirExpression firExpression = (FirExpression) CollectionsKt.singleOrNull(jsFirCall.getArgumentList().getArguments());
        if (firExpression == null || (irExpression = (IrExpression) CollectionsKt.singleOrNull(jsIrCall.getArguments())) == null || (firEvaluatorResultEvaluateExpression = FirExpressionEvaluator.INSTANCE.evaluateExpression(firExpression, getSession())) == null) {
            return;
        }
        if (firEvaluatorResultEvaluateExpression instanceof FirEvaluatorResult.CompileTimeException) {
            return;
        }
        FirLiteralExpression firLiteralExpression = null;
        if (firEvaluatorResultEvaluateExpression instanceof FirEvaluatorResult.Evaluated) {
            FirElement result = ((FirEvaluatorResult.Evaluated) firEvaluatorResultEvaluateExpression).getResult();
            firLiteralExpression = (FirLiteralExpression) (result instanceof FirLiteralExpression ? result : null);
        }
        if (firLiteralExpression != null && Intrinsics.areEqual(firLiteralExpression.getKind(), ConstantValueKind.String.INSTANCE)) {
            IrConst irConst = ConstantUtilsKt.toIrConst(firLiteralExpression, toIrType(FirTypeUtilsKt.getResolvedType(firLiteralExpression)));
            irConst.setStartOffset(irExpression.getStartOffset());
            irConst.setEndOffset(irExpression.getEndOffset());
            jsIrCall.getArguments().set(0, irConst);
        }
    }

    private final FirExpression extractDispatchReceiverOfAssignment(FirVariableAssignment variableAssignment) {
        FirReference calleeReference;
        FirPropertySymbol resolvedPropertySymbol$default;
        ConeSimpleKotlinType dispatchReceiverType;
        FirExpression dispatchReceiver = FirExpressionUtilKt.getDispatchReceiver(variableAssignment);
        if (dispatchReceiver == null) {
            return null;
        }
        if (dispatchReceiver instanceof FirSmartCastExpression) {
            FirExpression originalExpression = ((FirSmartCastExpression) dispatchReceiver).getOriginalExpression();
            FirThisReceiverExpression firThisReceiverExpression = originalExpression instanceof FirThisReceiverExpression ? (FirThisReceiverExpression) originalExpression : null;
            if (firThisReceiverExpression != null) {
                FirThisOwnerSymbol<?> boundSymbol = firThisReceiverExpression.getCalleeReference().getBoundSymbol();
                FirClassSymbol firClassSymbol = boundSymbol instanceof FirClassSymbol ? (FirClassSymbol) boundSymbol : null;
                if (firClassSymbol != null && (calleeReference = ReferenceUtilsKt.getCalleeReference(variableAssignment)) != null && (resolvedPropertySymbol$default = FirReferenceUtilsKt.toResolvedPropertySymbol$default(calleeReference, false, 1, null)) != null && (dispatchReceiverType = resolvedPropertySymbol$default.getDispatchReceiverType()) != null) {
                    boolean zIsSubtypeOf$default = TypeUtilsKt.isSubtypeOf$default(org.jetbrains.kotlin.fir.resolve.ScopeUtilsKt.defaultType(firClassSymbol), dispatchReceiverType, getSession(), false, 4, null);
                    if (zIsSubtypeOf$default) {
                        return firThisReceiverExpression;
                    }
                    if (zIsSubtypeOf$default) {
                        bu8.a();
                        return null;
                    }
                }
            }
        }
        return dispatchReceiver;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:10:0x0038  */
    public static IrExpression f(FirCallableSymbol firCallableSymbol, IrType irType, FirCallableReferenceAccess firCallableReferenceAccess, CallAndReferenceGenerator callAndReferenceGenerator, IrStatementOriginImpl irStatementOriginImpl, IrExpression irExpression, boolean z, int i, int i2) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrExpression irExpressionConvertToIrCallableReference$lambda$1$convertReferenceToField;
        if (firCallableSymbol instanceof FirSimpleSyntheticPropertySymbol) {
            irExpressionConvertToIrCallableReference$lambda$1$convertReferenceToField = convertToIrCallableReference$lambda$1$convertReferenceToSyntheticProperty(callAndReferenceGenerator, firCallableReferenceAccess, i, i2, irType, irStatementOriginImpl, firCallableSymbol, irExpression, (FirSimpleSyntheticPropertySymbol) firCallableSymbol);
        } else if (firCallableSymbol instanceof FirLocalPropertySymbol) {
            if (((FirLocalPropertySymbol) firCallableSymbol).getHasDelegate()) {
                irExpressionConvertToIrCallableReference$lambda$1$convertReferenceToField = convertToIrCallableReference$lambda$1$convertReferenceToLocalDelegatedProperty(i, i2, irType, callAndReferenceGenerator, irStatementOriginImpl, firCallableReferenceAccess, z, (FirPropertySymbol) firCallableSymbol);
            } else {
                irExpressionConvertToIrCallableReference$lambda$1$convertReferenceToField = null;
            }
        } else if (firCallableSymbol instanceof FirRegularPropertySymbol) {
            irExpressionConvertToIrCallableReference$lambda$1$convertReferenceToField = convertToIrCallableReference$lambda$1$convertReferenceToRegularProperty(callAndReferenceGenerator, firCallableReferenceAccess, i, i2, irType, irStatementOriginImpl, firCallableSymbol, irExpression, z, (FirPropertySymbol) firCallableSymbol);
        } else if (firCallableSymbol instanceof FirFunctionSymbol) {
            irExpressionConvertToIrCallableReference$lambda$1$convertReferenceToField = convertToIrCallableReference$lambda$1$convertReferenceToFunction(irType, firCallableReferenceAccess, callAndReferenceGenerator, i, i2, firCallableSymbol, irExpression, z, (FirFunctionSymbol) firCallableSymbol);
        } else if (firCallableSymbol instanceof FirFieldSymbol) {
            irExpressionConvertToIrCallableReference$lambda$1$convertReferenceToField = convertToIrCallableReference$lambda$1$convertReferenceToField(callAndReferenceGenerator, i, i2, irType, irStatementOriginImpl, firCallableReferenceAccess, firCallableSymbol, irExpression, z, (FirFieldSymbol) firCallableSymbol);
        } else {
            irExpressionConvertToIrCallableReference$lambda$1$convertReferenceToField = null;
        }
        if (irExpressionConvertToIrCallableReference$lambda$1$convertReferenceToField != null) {
            return irExpressionConvertToIrCallableReference$lambda$1$convertReferenceToField;
        }
        return BuildersKt.IrErrorCallExpressionImpl(i, i2, irType, "Unsupported callable reference: " + UtilsKt.render(firCallableReferenceAccess));
    }

    private final IrExpression filterOutErrorArgsInAnnotation(IrExpression irExpression) {
        if (getConfiguration().getSkipBodies() && (irExpression instanceof IrAnnotation)) {
            filterOutErrorArgsInAnnotation$cleanUp(irExpression);
        }
        return irExpression;
    }

    private static final boolean filterOutErrorArgsInAnnotation$cleanUp(IrElement irElement) {
        if (irElement instanceof IrErrorExpression) {
            return true;
        }
        if (irElement instanceof IrGetClass) {
            return ((IrGetClass) irElement).getArgument().getType() instanceof IrErrorType;
        }
        if (!(irElement instanceof IrConstructorCall)) {
            if (irElement instanceof IrVararg) {
                CollectionsKt.removeAll(((IrVararg) irElement).getElements(), new Function1() { // from class: v91
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(CallAndReferenceGenerator.g((IrVarargElement) obj));
                    }
                });
            }
            return false;
        }
        IrConstructorCall irConstructorCall = (IrConstructorCall) irElement;
        Iterator it = irConstructorCall.getArguments().iterator();
        int i = 0;
        while (it.hasNext()) {
            int i2 = i + 1;
            if (filterOutErrorArgsInAnnotation$cleanUp((IrExpression) it.next())) {
                irConstructorCall.getArguments().set(i, null);
            }
            i = i2;
        }
        return false;
    }

    private final IrExpression findIrDispatchReceiver(FirQualifiedAccessExpression firQualifiedAccessExpression, IrExpression irExpression) {
        return findIrReceiver$org_jetbrains_kotlin_fir2ir(firQualifiedAccessExpression, irExpression, true);
    }

    private final IrExpression findIrDynamicReceiver(FirQualifiedAccessExpression firQualifiedAccessExpression, IrExpression irExpression) {
        if (irExpression != null) {
            return irExpression;
        }
        FirExpression dispatchReceiver = firQualifiedAccessExpression.getDispatchReceiver();
        FirThisReceiverExpression firThisReceiverExpression = dispatchReceiver instanceof FirThisReceiverExpression ? (FirThisReceiverExpression) dispatchReceiver : null;
        IrExpression irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default = firThisReceiverExpression != null ? Fir2IrVisitor.convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this.visitor, firThisReceiverExpression, false, null, 6, null) : null;
        if (irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default != null) {
            return irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default;
        }
        f2f.a("No receiver for dynamic call: ", UtilsKt.render(firQualifiedAccessExpression));
        return null;
    }

    private final IrExpression findIrExtensionReceiver(FirQualifiedAccessExpression firQualifiedAccessExpression, IrExpression irExpression) {
        return findIrReceiver$org_jetbrains_kotlin_fir2ir(firQualifiedAccessExpression, irExpression, false);
    }

    public static boolean g(IrVarargElement irVarargElement) {
        irVarargElement.getClass();
        return filterOutErrorArgsInAnnotation$cleanUp(irVarargElement);
    }

    private final IrErrorCallExpression generateErrorCallExpression(int startOffset, int endOffset, FirReference calleeReference, IrType type) {
        if (type == null) {
            type = Fir2IrTypeConverterKt.createErrorType$default(null, false, 3, null);
        }
        return BuildersKt.IrErrorCallExpressionImpl(startOffset, endOffset, type, "Unresolved reference: " + UtilsKt.render(calleeReference));
    }

    public static /* synthetic */ IrErrorCallExpression generateErrorCallExpression$default(CallAndReferenceGenerator callAndReferenceGenerator, int i, int i2, FirReference firReference, IrType irType, int i3, Object obj) {
        if ((i3 & 8) != 0) {
            irType = null;
        }
        return callAndReferenceGenerator.generateErrorCallExpression(i, i2, firReference, irType);
    }

    private final IrDynamicOperator getDynamicOperator(FirQualifiedAccessExpression firQualifiedAccessExpression) {
        FirReference calleeReference;
        FirResolvedNamedReference resolved;
        KtSourceElement source = firQualifiedAccessExpression.getCalleeReference().getSource();
        KtSourceElementKind kind = source != null ? source.getKind() : null;
        FirExpression explicitReceiver = firQualifiedAccessExpression.getExplicitReceiver();
        FirQualifiedAccessExpression firQualifiedAccessExpression2 = explicitReceiver instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) explicitReceiver : null;
        boolean zAreEqual = Intrinsics.areEqual((firQualifiedAccessExpression2 == null || (calleeReference = firQualifiedAccessExpression2.getCalleeReference()) == null || (resolved = FirReferenceUtilsKt.getResolved(calleeReference)) == null) ? null : resolved.getName(), SpecialNames.ARRAY);
        if ((kind instanceof KtFakeSourceElementKind.ArrayAccessNameReference) || zAreEqual) {
            FirResolvedNamedReference resolved2 = FirReferenceUtilsKt.getResolved(firQualifiedAccessExpression.getCalleeReference());
            Name name = resolved2 != null ? resolved2.getName() : null;
            if (Intrinsics.areEqual(name, OperatorNameConventions.SET)) {
                return IrDynamicOperator.EQ;
            }
            if (Intrinsics.areEqual(name, OperatorNameConventions.GET)) {
                return IrDynamicOperator.ARRAY_ACCESS;
            }
            f2f.a("Unexpected name: ", UtilsKt.render(firQualifiedAccessExpression));
            return null;
        }
        if (kind instanceof KtFakeSourceElementKind.DesugaredPrefixInc) {
            return IrDynamicOperator.PREFIX_INCREMENT;
        }
        if (kind instanceof KtFakeSourceElementKind.DesugaredPrefixDec) {
            return IrDynamicOperator.PREFIX_DECREMENT;
        }
        if (kind instanceof KtFakeSourceElementKind.DesugaredPostfixInc) {
            return IrDynamicOperator.POSTFIX_INCREMENT;
        }
        if (kind instanceof KtFakeSourceElementKind.DesugaredPostfixDec) {
            return IrDynamicOperator.POSTFIX_DECREMENT;
        }
        if (kind instanceof KtFakeSourceElementKind.DesugaredAugmentedAssign) {
            FirResolvedNamedReference resolved3 = FirReferenceUtilsKt.getResolved(firQualifiedAccessExpression.getCalleeReference());
            Name name2 = resolved3 != null ? resolved3.getName() : null;
            if (Intrinsics.areEqual(name2, OperatorNameConventions.SET)) {
                return IrDynamicOperator.EQ;
            }
            if (Intrinsics.areEqual(name2, OperatorNameConventions.GET)) {
                return IrDynamicOperator.ARRAY_ACCESS;
            }
            f2f.a("Unexpected name: ", UtilsKt.render(firQualifiedAccessExpression));
        }
        return null;
    }

    public static IrTypeOperatorCallImpl h(IrType irType, IrExpression irExpression, int i, int i2) {
        return BuildersKt.IrTypeOperatorCallImpl(i, i2, irType, IrTypeOperator.SAM_CONVERSION, irType, irExpression);
    }

    /* JADX WARN: Code duplicated, block: B:41:0x007d  */
    private final IrExpression injectSetValueCall(FirElement element, FirReference calleeReference, IrExpression assignedValue) {
        TokenSet tokenSet;
        int i;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        InjectedValue injectedValueFindInjectedValue$org_jetbrains_kotlin_fir2ir = findInjectedValue$org_jetbrains_kotlin_fir2ir(calleeReference);
        if (injectedValueFindInjectedValue$org_jetbrains_kotlin_fir2ir == null) {
            return null;
        }
        if (element instanceof FirNamedFunction) {
            tokenSet = OffsetUtilsKt.FUNCTION_KEYWORD_TOKENS;
        } else if (element instanceof FirConstructor) {
            tokenSet = OffsetUtilsKt.CONSTRUCTOR_KEYWORD_TOKENS;
        } else {
            tokenSet = element instanceof FirVariable ? KtTokens.VAL_VAR : null;
        }
        KtSourceElement source = element.getSource();
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
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, tokenSet)) == null) {
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
        return BuildersKt.IrSetValueImpl(i, endOffset, getBuiltins().getUnitType(), injectedValueFindInjectedValue$org_jetbrains_kotlin_fir2ir.getIrParameterSymbol(), assignedValue, OriginUtilsKt.statementOrigin(calleeReference));
    }

    private final boolean isFunctionFromAny(FirCallableSymbol<?> firCallableSymbol) {
        if (firCallableSymbol instanceof FirNamedFunctionSymbol) {
            return FirDeclarationUtilKt.isMethodOfAny((FirNamedFunctionSymbol) firCallableSymbol);
        }
        return false;
    }

    private final boolean isOperatorCall(FirQualifiedAccessExpression firQualifiedAccessExpression) {
        return (firQualifiedAccessExpression instanceof FirFunctionCall) && ((FirFunctionCall) firQualifiedAccessExpression).getOrigin() == FirFunctionCallOrigin.Operator;
    }

    private final boolean needArgumentReordering(Collection<? extends FirValueParameter> parametersInArgumentOrder, List<? extends FirValueParameter> contextAndValueParameters) {
        Iterator<? extends FirValueParameter> it = parametersInArgumentOrder.iterator();
        int i = -1;
        while (it.hasNext()) {
            int iIndexOf = contextAndValueParameters.indexOf(it.next());
            if (iIndexOf < i) {
                return true;
            }
            i = iIndexOf;
        }
        return false;
    }

    private final int putContextArguments(IrMemberAccessExpression<?> irMemberAccessExpression, FirStatement firStatement, ReceiverInfo receiverInfo) {
        if (!(firStatement instanceof FirContextArgumentListOwner)) {
            return 0;
        }
        int iContextArgumentOffset = receiverInfo.contextArgumentOffset();
        FirContextArgumentListOwner firContextArgumentListOwner = (FirContextArgumentListOwner) firStatement;
        int size = firContextArgumentListOwner.getContextArguments().size();
        if (size > 0) {
            for (int i = 0; i < size; i++) {
                irMemberAccessExpression.getArguments().set(iContextArgumentOffset + i, Fir2IrVisitor.convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this.visitor, firContextArgumentListOwner.getContextArguments().get(i), false, null, 6, null));
            }
        }
        return size;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v15, types: [org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    private final ReceiverInfo putReceivers(IrExpression irExpression, FirStatement firStatement, FirCallableSymbol<?> firCallableSymbol, IrExpression irExpression2) {
        int i;
        IrErrorCallExpressionImpl irErrorCallExpressionImplFindIrDispatchReceiver;
        ConeKotlinType resolvedType;
        FirRegularClassSymbol regularClassSymbol;
        boolean z = irExpression instanceof IrMemberAccessExpression;
        boolean z2 = 1;
        z2 = 1;
        boolean z3 = false;
        z3 = false;
        z3 = false;
        z3 = false;
        z3 = false;
        z3 = false;
        if (z && (firStatement instanceof FirQualifiedAccessExpression)) {
            if ((firCallableSymbol != null ? firCallableSymbol.getDispatchReceiverType() : null) != null) {
                if (DeclarationAttributesKt.getOriginalReplSnippetSymbol(firCallableSymbol.getFir()) != null) {
                    irErrorCallExpressionImplFindIrDispatchReceiver = BuildersKt.IrErrorCallExpressionImpl(irExpression.getStartOffset(), irExpression.getEndOffset(), getBuiltins().getNothingType(), "No REPL snippet class instance.");
                } else if (VariousUtilsKt.shouldHaveReceiver(firCallableSymbol, getSession())) {
                    FirQualifiedAccessExpression firQualifiedAccessExpression = (FirQualifiedAccessExpression) firStatement;
                    irErrorCallExpressionImplFindIrDispatchReceiver = !VariousUtilsKt.isConstructorCallOnTypealiasWithInnerRhs(firQualifiedAccessExpression) ? findIrDispatchReceiver(firQualifiedAccessExpression, irExpression2) : findIrExtensionReceiver(firQualifiedAccessExpression, irExpression2);
                } else {
                    irErrorCallExpressionImplFindIrDispatchReceiver = IrUtilsKt.toIrConst$default((Object) null, getBuiltins().getNothingNType(), 0, 0, 6, (Object) null);
                }
                FirExpression dispatchReceiver = ((FirQualifiedAccessExpression) firStatement).getDispatchReceiver();
                if (dispatchReceiver instanceof FirSuperReceiverExpression) {
                    dispatchReceiver = ((FirSuperReceiverExpression) dispatchReceiver).getDispatchReceiver();
                }
                boolean zIsFunctionFromAny = isFunctionFromAny(firCallableSymbol);
                Object[] objArr = (dispatchReceiver == null || (resolvedType = FirTypeUtilsKt.getResolvedType(dispatchReceiver)) == null || (regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(this, resolvedType)) == null || regularClassSymbol.getClassKind() != ClassKind.INTERFACE) ? false : true;
                IrMemberAccessExpression.ValueArgumentsList arguments = ((IrMemberAccessExpression) irExpression).getArguments();
                if (zIsFunctionFromAny && objArr != false) {
                    if (irErrorCallExpressionImplFindIrDispatchReceiver == null) {
                        w01.a("Required value was null.");
                        return null;
                    }
                    irErrorCallExpressionImplFindIrDispatchReceiver = VariousUtilsKt.implicitCast(irErrorCallExpressionImplFindIrDispatchReceiver, getBuiltins().getAnyType(), IrTypeOperator.IMPLICIT_CAST);
                }
                arguments.set(0, irErrorCallExpressionImplFindIrDispatchReceiver);
                i = 1;
            } else {
                i = 0;
            }
            if (firCallableSymbol != null && FirSymbolStatusUtilsKt.isInstanceExtension(firCallableSymbol) && !(firCallableSymbol instanceof FirConstructorSymbol)) {
                List<FirExpression> contextArguments = ((FirContextArgumentListOwner) firStatement).getContextArguments();
                ((IrMemberAccessExpression) irExpression).getArguments().set((contextArguments != null ? contextArguments.size() : 0) + i, findIrExtensionReceiver((FirQualifiedAccessExpression) firStatement, irExpression2));
                z3 = true;
            }
            z2 = i;
        } else if (z && (firStatement instanceof FirDelegatedConstructorCall)) {
            FirExpression dispatchReceiver2 = ((FirDelegatedConstructorCall) firStatement).getDispatchReceiver();
            if (dispatchReceiver2 != null) {
                ((IrMemberAccessExpression) irExpression).getArguments().set(0, Fir2IrVisitor.convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this.visitor, dispatchReceiver2, false, null, 6, null));
            } else {
                z2 = 0;
            }
        } else {
            if (irExpression instanceof IrFieldAccessExpression) {
                if (!(firStatement instanceof FirQualifiedAccessExpression)) {
                    w01.a("Failed requirement.");
                    return null;
                }
                firCallableSymbol.getClass();
                FirCallableDeclaration propertyIfBackingField = ClassMembersKt.getPropertyIfBackingField((FirCallableDeclaration) firCallableSymbol.getFir());
                if (!propertyIfBackingField.getStatus().isStatic() && (!(propertyIfBackingField instanceof FirProperty) || !(((FirProperty) propertyIfBackingField).getSymbol() instanceof FirRegularPropertySymbol) || ClassMembersKt.containingClassLookupTag(propertyIfBackingField) != null)) {
                    ((IrFieldAccessExpression) irExpression).setReceiver(findIrDispatchReceiver((FirQualifiedAccessExpression) firStatement, irExpression2));
                }
            } else if ((!(irExpression instanceof IrConstructorCall) || !(firStatement instanceof FirAnnotationCall)) && !(irExpression instanceof IrGetValue) && !(irExpression instanceof IrSetValue) && !(irExpression instanceof IrGetEnumValue) && !(irExpression instanceof IrGetObjectValue) && !(irExpression instanceof IrErrorCallExpression)) {
                s0g.a("Can't apply receivers of ", firStatement.getClass().getSimpleName(), " to ", irExpression.getClass().getSimpleName());
                return null;
            }
            z2 = 0;
        }
        return new ReceiverInfo(z2, z3);
    }

    private final List<ConeKotlinType> refineTypeArgumentsOfTypeAliasConstructor(FirCallableDeclaration firCallableDeclaration, List<? extends FirTypeProjection> list) {
        TypeAliasConstructorInfo typeAliasConstructorInfo;
        Collection collectionEmptySet;
        FirConstructor firConstructor = firCallableDeclaration instanceof FirConstructor ? (FirConstructor) firCallableDeclaration : null;
        if (firConstructor == null || (typeAliasConstructorInfo = TypeAliasConstructorsSubstitutingScopeKt.getTypeAliasConstructorInfo(firConstructor)) == null) {
            List<? extends FirTypeProjection> list2 = list;
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
            for (FirTypeProjection firTypeProjection : list2) {
                firTypeProjection.getClass();
                arrayList.add(FirTypeUtilsKt.getConeType(((FirTypeProjectionWithVariance) firTypeProjection).getTypeRef()));
            }
            return arrayList;
        }
        FirTypeAliasSymbol typeAliasSymbol = typeAliasConstructorInfo.getTypeAliasSymbol();
        FirSession session = getSession();
        List<FirTypeParameterSymbol> typeParameterSymbols = typeAliasSymbol.getTypeParameterSymbols();
        List<? extends FirTypeProjection> list3 = list;
        Iterator<T> it = typeParameterSymbols.iterator();
        Iterator<T> it2 = list3.iterator();
        ArrayList arrayList2 = new ArrayList(Math.min(CollectionsKt.collectionSizeOrDefault(typeParameterSymbols, 10), CollectionsKt.collectionSizeOrDefault(list3, 10)));
        while (it.hasNext() && it2.hasNext()) {
            arrayList2.add(TuplesKt.to((FirTypeParameterSymbol) it.next(), FirTypeUtilsKt.toConeTypeProjection((FirTypeProjection) it2.next())));
        }
        ConeSubstitutor coneSubstitutorCreateParametersSubstitutor = TypeExpansionUtilsKt.createParametersSubstitutor(session, MapsKt.toMap(arrayList2));
        FirFunction originalConstructor = typeAliasConstructorInfo.getOriginalConstructor();
        if (!((FirConstructor) originalConstructor).getStatus().isInner()) {
            originalConstructor = null;
        }
        FirConstructor firConstructor2 = (FirConstructor) originalConstructor;
        FirRegularClass containingClass = firConstructor2 != null ? ResolveUtilsKt.getContainingClass(firConstructor2) : null;
        ConeSubstitutor substitutor = typeAliasConstructorInfo.getSubstitutor();
        if (substitutor == null || containingClass == null) {
            collectionEmptySet = SetsKt.emptySet();
        } else {
            List<FirTypeParameterRef> typeParameters = containingClass.getTypeParameters();
            ArrayList arrayList3 = new ArrayList();
            for (Object obj : typeParameters) {
                if (obj instanceof FirOuterClassTypeParameterRef) {
                    arrayList3.add(obj);
                }
            }
            collectionEmptySet = new LinkedHashSet();
            Iterator it3 = arrayList3.iterator();
            while (it3.hasNext()) {
                ConeKotlinType coneKotlinTypeSubstituteOrNull = substitutor.substituteOrNull(FirNestedClassifierScopeKt.toConeType((FirOuterClassTypeParameterRef) it3.next()));
                if (coneKotlinTypeSubstituteOrNull != null) {
                    collectionEmptySet.add(coneKotlinTypeSubstituteOrNull);
                }
            }
        }
        List listCreateListBuilder = CollectionsKt.createListBuilder();
        ConeTypeProjection[] typeArguments = typeAliasSymbol.getResolvedExpandedTypeRef().getConeType().getTypeArguments();
        int length = typeArguments.length;
        for (int i = 0; i < length; i++) {
            ConeTypeProjection coneTypeProjection = typeArguments[i];
            if (!CollectionsKt.contains(collectionEmptySet, coneTypeProjection)) {
                ConeTypeProjection coneTypeProjectionSubstituteArgument = coneSubstitutorCreateParametersSubstitutor.substituteArgument(coneTypeProjection, i);
                if (coneTypeProjectionSubstituteArgument != null) {
                    coneTypeProjection = coneTypeProjectionSubstituteArgument;
                }
                ConeKotlinType coneErrorType = coneTypeProjection instanceof ConeKotlinType ? (ConeKotlinType) coneTypeProjection : null;
                if (coneErrorType == null) {
                    coneErrorType = new ConeErrorType(new ConeSimpleDiagnostic("Expansion contains unexpected type " + coneTypeProjection.getClass(), null, 2, null), false, null, null, null, null, null, 126, null);
                }
                listCreateListBuilder.add(coneErrorType);
            }
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    /* JADX WARN: Code duplicated, block: B:15:0x0024  */
    private final IrClassSymbol superQualifierSymbolForFieldAccess(FirExpression firExpression, FirBasedSymbol<?> firBasedSymbol) {
        FirClassSymbol<?> classSymbol;
        if ((firBasedSymbol instanceof FirBackingFieldSymbol) || (firBasedSymbol instanceof FirDelegateFieldSymbol)) {
            return null;
        }
        if (firExpression instanceof FirResolvedQualifier) {
            FirResolvedQualifier firResolvedQualifier = (FirResolvedQualifier) firExpression;
            if (firResolvedQualifier.getResolvedToCompanionObject()) {
                return null;
            }
            FirClassLikeSymbol<?> symbol = firResolvedQualifier.getSymbol();
            if (symbol instanceof FirClassSymbol) {
                classSymbol = (FirClassSymbol) symbol;
            } else {
                classSymbol = null;
            }
        } else {
            ConeKotlinType coneKotlinTypeSuperQualifierSymbolForFieldAccess$findIntersectionComponent = superQualifierSymbolForFieldAccess$findIntersectionComponent(firBasedSymbol, this, ConeTypeUtilsKt.lowerBoundIfFlexible(TypeExpansionUtilsKt.fullyExpandedType(this, FirTypeUtilsKt.getResolvedType(firExpression))));
            if (coneKotlinTypeSuperQualifierSymbolForFieldAccess$findIntersectionComponent != null) {
                classSymbol = ToSymbolUtilsKt.toClassSymbol(this, coneKotlinTypeSuperQualifierSymbolForFieldAccess$findIntersectionComponent);
            } else {
                classSymbol = null;
            }
        }
        if (classSymbol == null || classSymbol.getRawStatus().isCompanion()) {
            return null;
        }
        IrClassSymbol irClassSymbol = getClassifierStorage().getIrClassSymbol(classSymbol);
        if (classSymbol.getClassKind() == ClassKind.ENUM_CLASS) {
            List<IrDeclarationParent> parentStack = this.conversionScope.getParentStack();
            if (!(parentStack instanceof Collection) || !parentStack.isEmpty()) {
                Iterator<T> it = parentStack.iterator();
                while (it.hasNext()) {
                    IrClass irClass = (IrDeclarationParent) it.next();
                    IrClass irClass2 = irClass instanceof IrClass ? irClass : null;
                    if (Intrinsics.areEqual(irClass2 != null ? irClass2.getSymbol() : null, irClassSymbol)) {
                        return null;
                    }
                }
            }
        }
        List<IrAnonymousInitializer> initBlocksStack$org_jetbrains_kotlin_fir2ir = this.conversionScope.getInitBlocksStack$org_jetbrains_kotlin_fir2ir();
        if ((initBlocksStack$org_jetbrains_kotlin_fir2ir instanceof Collection) && initBlocksStack$org_jetbrains_kotlin_fir2ir.isEmpty()) {
            return irClassSymbol;
        }
        Iterator<T> it2 = initBlocksStack$org_jetbrains_kotlin_fir2ir.iterator();
        while (it2.hasNext()) {
            if (Intrinsics.areEqual(IrUtilsKt.getParentAsClass((IrAnonymousInitializer) it2.next()).getSymbol(), irClassSymbol)) {
                if (firExpression instanceof FirThisReceiverExpression) {
                    if ((firBasedSymbol != null ? firBasedSymbol.getFir() : null) instanceof FirJavaField) {
                        return irClassSymbol;
                    }
                }
                return null;
            }
        }
        return irClassSymbol;
    }

    private static final ConeKotlinType superQualifierSymbolForFieldAccess$findIntersectionComponent(FirBasedSymbol<?> firBasedSymbol, CallAndReferenceGenerator callAndReferenceGenerator, ConeKotlinType coneKotlinType) {
        if (!(coneKotlinType instanceof ConeIntersectionType)) {
            return coneKotlinType;
        }
        if (!(firBasedSymbol instanceof FirCallableSymbol)) {
            return null;
        }
        ConeSimpleKotlinType dispatchReceiverType = ((FirCallableSymbol) firBasedSymbol).getDispatchReceiverType();
        ConeLookupTagBasedType coneLookupTagBasedType = dispatchReceiverType instanceof ConeLookupTagBasedType ? (ConeLookupTagBasedType) dispatchReceiverType : null;
        if (coneLookupTagBasedType == null) {
            return null;
        }
        ConeClassifierLookupTag lookupTag = coneLookupTagBasedType.getLookupTag();
        ConeInferenceContext typeContext = TypeComponentsKt.getTypeContext(callAndReferenceGenerator.getSession());
        for (ConeKotlinType coneKotlinType2 : ((ConeIntersectionType) coneKotlinType).getIntersectedTypes()) {
            if (AbstractTypeChecker.INSTANCE.isSubtypeOfClass(typeContext, (ConeTypeConstructorMarker) typeContext.m691typeConstructor(typeContext.lowerBoundIfFlexible(coneKotlinType2)), lookupTag)) {
                return coneKotlinType2;
            }
        }
        hb9.a("Collection contains no element matching the predicate.");
        return null;
    }

    private final IrClassSymbol superQualifierSymbolForFunctionAndPropertyAccess(FirExpression firExpression) {
        FirClassSymbol<?> classSymbol;
        if ((firExpression instanceof FirSuperReceiverExpression) && (classSymbol = ToSymbolUtilsKt.toClassSymbol(this, TypeExpansionUtilsKt.fullyExpandedType(this, FirTypeUtilsKt.getConeType(((FirSuperReceiverExpression) firExpression).getCalleeReference().getSuperTypeRef())))) != null) {
            return getClassifierStorage().getIrClassSymbol(classSymbol);
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final FirAnnotationCall toAnnotationCall(FirAnnotation firAnnotation) {
        FirConstructorSymbol firConstructorSymbol;
        if (firAnnotation instanceof FirAnnotationCall) {
            return (FirAnnotationCall) firAnnotation;
        }
        FirAnnotationCallBuilder firAnnotationCallBuilder = new FirAnnotationCallBuilder();
        firAnnotationCallBuilder.setUseSiteTarget(firAnnotation.getUseSiteTarget());
        firAnnotationCallBuilder.setAnnotationTypeRef(firAnnotation.getAnnotationTypeRef());
        FirRegularClassSymbol regularClassSymbol = ToSymbolUtilsKt.toRegularClassSymbol(this, TypeExpansionUtilsKt.fullyExpandedType(this, FirTypeUtilsKt.getConeType(firAnnotationCallBuilder.getAnnotationTypeRef())));
        if (regularClassSymbol == null || (firConstructorSymbol = (FirConstructorSymbol) CollectionsKt.firstOrNull(FirScopeKt.getDeclaredConstructors(ScopeUtilsKt.unsubstitutedScope(this, regularClassSymbol)))) == null) {
            return null;
        }
        List<FirValueParameterSymbol> valueParameterSymbols = firConstructorSymbol.getValueParameterSymbols();
        ArrayList arrayList = new ArrayList();
        Iterator<T> it = valueParameterSymbols.iterator();
        while (it.hasNext()) {
            FirValueParameter firValueParameter = (FirValueParameter) ((FirValueParameterSymbol) it.next()).getFir();
            FirExpression firExpression = firAnnotation.getArgumentMapping().getMapping().get(firValueParameter.getName());
            Pair pair = firExpression == null ? null : TuplesKt.to(firExpression, firValueParameter);
            if (pair != null) {
                arrayList.add(pair);
            }
        }
        firAnnotationCallBuilder.setArgumentList(FirArgumentUtilKt.buildResolvedArgumentList(null, (LinkedHashMap) MapsKt.toMap(arrayList, new LinkedHashMap())));
        FirResolvedNamedReferenceBuilder firResolvedNamedReferenceBuilder = new FirResolvedNamedReferenceBuilder();
        firResolvedNamedReferenceBuilder.setName(regularClassSymbol.getClassId().getShortClassName());
        firResolvedNamedReferenceBuilder.setResolvedSymbol(firConstructorSymbol);
        firAnnotationCallBuilder.setCalleeReference(firResolvedNamedReferenceBuilder.build());
        firAnnotationCallBuilder.setArgumentMapping(firAnnotation.getArgumentMapping());
        firAnnotationCallBuilder.setContainingDeclarationSymbol(firConstructorSymbol);
        return firAnnotationCallBuilder.mo288build();
    }

    private final IrType toIrType(FirTypeRef firTypeRef) {
        return Fir2IrTypeConverterKt.toIrType(this, firTypeRef, this.conversionScope.defaultConversionTypeOrigin());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    private final IrTypeOperatorCall tryConvertToSamConstructorCall(FirQualifiedAccessExpression firQualifiedAccessExpression, final IrType irType) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirReference calleeReference = firQualifiedAccessExpression.getCalleeReference();
        FirResolvedNamedReference firResolvedNamedReference = calleeReference instanceof FirResolvedNamedReference ? (FirResolvedNamedReference) calleeReference : null;
        if (firResolvedNamedReference == null) {
            return null;
        }
        FirDeclaration fir = firResolvedNamedReference.getResolvedSymbol().getFir();
        if ((firQualifiedAccessExpression instanceof FirFunctionCall) && (fir instanceof FirNamedFunction)) {
            FirNamedFunction firNamedFunction = (FirNamedFunction) fir;
            if (Intrinsics.areEqual(firNamedFunction.getOrigin(), FirDeclarationOrigin.SamConstructor.INSTANCE)) {
                final IrExpression irExpressionConvertArgument = convertArgument((FirExpression) CollectionsKt.first(((FirCall) firQualifiedAccessExpression).getArgumentList().getArguments()), (FirValueParameter) CollectionsKt.first(firNamedFunction.getValueParameters()), VariousUtilsKt.buildSubstitutorByCalledCallable(this, firQualifiedAccessExpression));
                return OffsetUtilsKt.convertWithOffsets(firQualifiedAccessExpression, new Function2() { // from class: ca1
                    public final Object invoke(Object obj, Object obj2) {
                        return CallAndReferenceGenerator.h(irType, irExpressionConvertArgument, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
                    }
                });
            }
        }
        return null;
    }

    private final void updateStatementOrigin(IrExpression irExpression, IrStatementOrigin irStatementOrigin) {
        if (irExpression instanceof IrFieldAccessExpression) {
            IrFieldAccessExpression irFieldAccessExpression = (IrFieldAccessExpression) irExpression;
            IrStatementOrigin origin = irFieldAccessExpression.getOrigin();
            if (origin != null) {
                irStatementOrigin = origin;
            }
            irFieldAccessExpression.setOrigin(irStatementOrigin);
            return;
        }
        if (irExpression instanceof IrMemberAccessExpression) {
            IrMemberAccessExpression irMemberAccessExpression = (IrMemberAccessExpression) irExpression;
            IrStatementOrigin origin2 = irMemberAccessExpression.getOrigin();
            if (origin2 != null) {
                irStatementOrigin = origin2;
            }
            irMemberAccessExpression.setOrigin(irStatementOrigin);
            return;
        }
        if (irExpression instanceof IrValueAccessExpression) {
            IrValueAccessExpression irValueAccessExpression = (IrValueAccessExpression) irExpression;
            IrStatementOrigin origin3 = irValueAccessExpression.getOrigin();
            if (origin3 != null) {
                irStatementOrigin = origin3;
            }
            irValueAccessExpression.setOrigin(irStatementOrigin);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    private final int valueParametersSize(FirCallableSymbol<?> firCallableSymbol) {
        if (firCallableSymbol instanceof FirSyntheticPropertySymbol) {
            return 0;
        }
        if (firCallableSymbol instanceof FirNamedFunctionSymbol) {
            FirNamedFunctionSymbol firNamedFunctionSymbol = (FirNamedFunctionSymbol) firCallableSymbol;
            return ((FirNamedFunction) firNamedFunctionSymbol.getFir()).getValueParameters().size() + ((FirNamedFunction) firNamedFunctionSymbol.getFir()).getContextParameters().size();
        }
        if (firCallableSymbol instanceof FirConstructorSymbol) {
            FirConstructorSymbol firConstructorSymbol = (FirConstructorSymbol) firCallableSymbol;
            return ((FirConstructor) firConstructorSymbol.getFir()).getValueParameters().size() + ((FirConstructor) firConstructorSymbol.getFir()).getContextParameters().size();
        }
        if (firCallableSymbol instanceof FirFunctionSymbol) {
            return ((FirFunction) ((FirFunctionSymbol) firCallableSymbol).getFir()).getValueParameters().size();
        }
        f2f.a("Illegal symbol: ", Reflection.getOrCreateKotlinClass(firCallableSymbol.getClass()));
        return 0;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final IrExpression applyReceiversAndArguments(IrExpression irExpression, FirStatement firStatement, FirCallableSymbol<?> firCallableSymbol, IrExpression irExpression2, IrExpression irExpression3) throws KotlinIllegalArgumentExceptionWithAttachments {
        irExpression.getClass();
        if (firStatement == null) {
            return irExpression;
        }
        ReceiverInfo receiverInfoPutReceivers = putReceivers(irExpression, firStatement, firCallableSymbol, irExpression2);
        if (irExpression3 == null || !(irExpression instanceof IrMemberAccessExpression)) {
            irExpression = applyCallArguments(irExpression, firStatement, firCallableSymbol, receiverInfoPutReceivers);
        } else {
            IrMemberAccessExpression<?> irMemberAccessExpression = (IrMemberAccessExpression) irExpression;
            irMemberAccessExpression.getArguments().set(receiverInfoPutReceivers.valueArgumentOffset(putContextArguments(irMemberAccessExpression, firStatement, receiverInfoPutReceivers)), irExpression3);
        }
        if ((irExpression instanceof IrCall) && (firStatement instanceof FirFunctionCall)) {
            if (Intrinsics.areEqual(firCallableSymbol != null ? firCallableSymbol.getCallableId() : null, WebCommonStandardClassIds.Callables.Js)) {
                evaluateAndApplyJsCallArg((FirFunctionCall) firStatement, (IrCall) irExpression);
            }
        }
        return irExpression;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final IrExpression applyTypeArguments$org_jetbrains_kotlin_fir2ir(IrExpression irExpression, FirQualifiedAccessExpression firQualifiedAccessExpression) {
        irExpression.getClass();
        firQualifiedAccessExpression.getClass();
        FirCallableSymbol resolvedCallableSymbol$default = FirReferenceUtilsKt.toResolvedCallableSymbol$default(firQualifiedAccessExpression.getCalleeReference(), false, 1, null);
        return applyTypeArgumentsWithTypealiasConstructorRemapping(irExpression, resolvedCallableSymbol$default != null ? (FirCallableDeclaration) resolvedCallableSymbol$default.getFir() : null, firQualifiedAccessExpression.getTypeArguments());
    }

    public final IrExpression convertSubstitutedInlineLambda$org_jetbrains_kotlin_fir2ir(FirQualifiedAccessExpression expression) {
        FirExpression firExpressionFindInjectedInlineLambdaArgument;
        expression.getClass();
        FirPropertyAccessExpression firPropertyAccessExpression = expression instanceof FirPropertyAccessExpression ? (FirPropertyAccessExpression) expression : null;
        FirCallableSymbol<?> resolvedCallableSymbol = firPropertyAccessExpression != null ? ReferenceUtilsKt.toResolvedCallableSymbol(firPropertyAccessExpression) : null;
        FirValueParameterSymbol firValueParameterSymbol = resolvedCallableSymbol instanceof FirValueParameterSymbol ? (FirValueParameterSymbol) resolvedCallableSymbol : null;
        if (firValueParameterSymbol == null || (firExpressionFindInjectedInlineLambdaArgument = getExtensions().findInjectedInlineLambdaArgument(firValueParameterSymbol)) == null) {
            return null;
        }
        return Fir2IrVisitor.convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this.visitor, firExpressionFindInjectedInlineLambdaArgument, false, null, 6, null);
    }

    /* JADX WARN: Code duplicated, block: B:35:0x007a  */
    public final IrExpression convertToGetObject$org_jetbrains_kotlin_fir2ir(FirResolvedQualifier qualifier, FirCallableReferenceAccess callableReferenceAccess) {
        int startOffset;
        int endOffset;
        Integer numStartOffsetSkippingComments;
        qualifier.getClass();
        FirClassLikeSymbol<?> classLikeSymbol = ToSymbolUtilsKt.toClassLikeSymbol(this, FirTypeUtilsKt.getResolvedType(qualifier));
        if (callableReferenceAccess != null && !FirExpressionUtilKt.isBound(callableReferenceAccess)) {
            return null;
        }
        IrType irType = toIrType(FirTypeUtilsKt.getResolvedType(qualifier));
        KtSourceElement source = qualifier.getSource();
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            startOffset = -1;
            endOffset = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                startOffset = -1;
                endOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    startOffset = -1;
                    endOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        startOffset = -1;
                        endOffset = -1;
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
        if (classLikeSymbol != null) {
            IrClassSymbol irSymbol$default = SymbolConversionUtilsKt.toIrSymbol$default(this, classLikeSymbol, null, null, 6, null);
            irSymbol$default.getClass();
            return BuildersKt.IrGetObjectValueImpl(startOffset, endOffset, irType, irSymbol$default);
        }
        return BuildersKt.IrErrorCallExpressionImpl(startOffset, endOffset, irType, "Resolved qualifier " + UtilsKt.render(qualifier) + " does not have correctly resolved type");
    }

    /* JADX WARN: Failed to calculate best type for var: r20v0 'this'  ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r20v0 'this'  ??, new type: org.jetbrains.kotlin.fir.backend.generators.CallAndReferenceGenerator
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r6v4 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r6v4 ??, new type: org.jetbrains.kotlin.fir.declarations.FirConstructor
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:159)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:136)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:241)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:224)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 6 more
     */
    /* JADX WARN: Failed to calculate best type for var: r6v4 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r6v4 ??, new type: org.jetbrains.kotlin.fir.declarations.FirConstructor
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r6v5 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r6v5 ??, new type: org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r6v7 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r6v7 ??, new type: org.jetbrains.kotlin.fir.declarations.FirConstructor
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to set immutable type for var: r20v0 'this'  ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r20v0 'this'  ??, new type: org.jetbrains.kotlin.fir.backend.generators.CallAndReferenceGenerator
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyWithWiderIgnSame(TypeUpdate.java:73)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setImmutableType(TypeInferenceVisitor.java:111)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$1(TypeInferenceVisitor.java:102)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:102)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 6 more
     */
    /*  JADX ERROR: Types fix failed
        jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r6v4 ??, new type: org.jetbrains.kotlin.fir.declarations.FirFunction
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.applyWithWiderAllow(TypeUpdate.java:66)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryWiderObjects(FixTypesVisitor.java:795)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:249)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:224)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
        Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        	... 5 more
        */
    public final org.jetbrains.kotlin.ir.expressions.IrExpression convertToIrAnnotation(org.jetbrains.kotlin.fir.expressions.FirAnnotation r21) {
        /*
            Method dump skipped, instruction units count: 620
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.kotlin.fir.backend.generators.CallAndReferenceGenerator.convertToIrAnnotation(org.jetbrains.kotlin.fir.expressions.FirAnnotation):org.jetbrains.kotlin.ir.expressions.IrExpression");
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final IrExpression convertToIrCall(final FirQualifiedAccessExpression qualifiedAccess, ConeKotlinType type, final IrExpression explicitReceiverExpression, IrDynamicOperator dynamicOperator, final boolean variableAsFunctionMode, boolean noArguments) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrFile irFileContainingFileIfAny;
        qualifiedAccess.getClass();
        type.getClass();
        Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
        try {
            IrExpression irExpressionInjectGetValueCall$org_jetbrains_kotlin_fir2ir = injectGetValueCall$org_jetbrains_kotlin_fir2ir(qualifiedAccess, qualifiedAccess.getCalleeReference());
            if (irExpressionInjectGetValueCall$org_jetbrains_kotlin_fir2ir != null) {
                return irExpressionInjectGetValueCall$org_jetbrains_kotlin_fir2ir;
            }
            IrExpression irExpressionConvertSubstitutedInlineLambda$org_jetbrains_kotlin_fir2ir = convertSubstitutedInlineLambda$org_jetbrains_kotlin_fir2ir(qualifiedAccess);
            if (irExpressionConvertSubstitutedInlineLambda$org_jetbrains_kotlin_fir2ir != null) {
                return PatchDeclarationParentsKt.patchDeclarationParents(irExpressionConvertSubstitutedInlineLambda$org_jetbrains_kotlin_fir2ir, this.conversionScope.parent());
            }
            final IrType irType = toIrType(type);
            IrTypeOperatorCall irTypeOperatorCallTryConvertToSamConstructorCall = tryConvertToSamConstructorCall(qualifiedAccess, irType);
            if (irTypeOperatorCallTryConvertToSamConstructorCall != null) {
                return irTypeOperatorCallTryConvertToSamConstructorCall;
            }
            final FirExpression dispatchReceiver = qualifiedAccess.getDispatchReceiver();
            final FirReference calleeReference = qualifiedAccess.getCalleeReference();
            if ((qualifiedAccess instanceof FirSuperReceiverExpression) && dispatchReceiver != null) {
                return OffsetUtilsKt.convertWithOffsets(qualifiedAccess, new Function2() { // from class: x91
                    public final Object invoke(Object obj, Object obj2) {
                        return CallAndReferenceGenerator.convertToIrCall$lambda$0$2(this.b, dispatchReceiver, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
                    }
                });
            }
            final FirCallableSymbol<?> firCallableSymbolExtractDeclarationSiteSymbol = SymbolConversionUtilsKt.extractDeclarationSiteSymbol(this, calleeReference);
            if (Intrinsics.areEqual(firCallableSymbolExtractDeclarationSiteSymbol != null ? firCallableSymbolExtractDeclarationSiteSymbol.getOrigin() : null, FirDeclarationOrigin.DynamicScope.INSTANCE)) {
                return convertToIrCallForDynamic(qualifiedAccess, explicitReceiverExpression, irType, calleeReference, firCallableSymbolExtractDeclarationSiteSymbol, dynamicOperator, noArguments);
            }
            return applyReceiversAndArguments$default(this, applyTypeArguments$org_jetbrains_kotlin_fir2ir(OffsetUtilsKt.convertWithOffsets(qualifiedAccess, new Function2() { // from class: z91
                public final Object invoke(Object obj, Object obj2) {
                    return CallAndReferenceGenerator.convertToIrCall$lambda$0$4(firCallableSymbolExtractDeclarationSiteSymbol, this, dispatchReceiver, qualifiedAccess, irType, calleeReference, explicitReceiverExpression, variableAsFunctionMode, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
                }
            }), qualifiedAccess), qualifiedAccess, firCallableSymbolExtractDeclarationSiteSymbol, (explicitReceiverExpression != null ? explicitReceiverExpression.getType() : null) instanceof IrDynamicType ? (IrExpression) OffsetUtilsKt.convertWithOffsets(qualifiedAccess, new Function2() { // from class: y91
                public final Object invoke(Object obj, Object obj2) {
                    return CallAndReferenceGenerator.convertToIrCall$lambda$0$3(calleeReference, this, qualifiedAccess, explicitReceiverExpression, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
                }
            }) : explicitReceiverExpression, null, 8, null);
        } catch (Throwable th) {
            String str = "Exception was thrown during transformation of " + qualifiedAccess.getClass();
            PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(th);
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(str, th);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", qualifiedAccess);
            if (fir2IrConversionScope != null && (irFileContainingFileIfAny = fir2IrConversionScope.containingFileIfAny()) != null) {
                exceptionAttachmentBuilder.withEntry("file", IrDeclarationsKt.getPath(irFileContainingFileIfAny));
            }
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final IrExpression convertToIrCallableReference(final FirCallableReferenceAccess callableReferenceAccess, final IrExpression explicitReceiverExpression, final boolean isDelegate) throws KotlinIllegalArgumentExceptionWithAttachments {
        callableReferenceAccess.getClass();
        final IrType irType = toIrType(Fir2IrTypeConverterKt.approximateFunctionTypeInputs(this, FirTypeUtilsKt.getResolvedType(callableReferenceAccess)));
        final FirCallableSymbol<?> firCallableSymbolExtractDeclarationSiteSymbol = SymbolConversionUtilsKt.extractDeclarationSiteSymbol(this, callableReferenceAccess.getCalleeReference());
        if (Intrinsics.areEqual(firCallableSymbolExtractDeclarationSiteSymbol != null ? firCallableSymbolExtractDeclarationSiteSymbol.getOrigin() : null, FirDeclarationOrigin.SamConstructor.INSTANCE)) {
            AdapterGenerator adapterGenerator = getAdapterGenerator();
            firCallableSymbolExtractDeclarationSiteSymbol.getClass();
            return adapterGenerator.generateFunInterfaceConstructorReference(callableReferenceAccess, (FirSyntheticFunctionSymbol) firCallableSymbolExtractDeclarationSiteSymbol, irType);
        }
        KtSourceElement source = callableReferenceAccess.getSource();
        final IrStatementOriginImpl property_reference_for_delegate = Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DelegatedPropertyAccessor.INSTANCE) ? IrStatementOrigin.Companion.getPROPERTY_REFERENCE_FOR_DELEGATE() : null;
        return OffsetUtilsKt.convertWithOffsets((FirQualifiedAccessExpression) callableReferenceAccess, new Function2() { // from class: w91
            public final Object invoke(Object obj, Object obj2) {
                return CallAndReferenceGenerator.f(firCallableSymbolExtractDeclarationSiteSymbol, irType, callableReferenceAccess, this, property_reference_for_delegate, explicitReceiverExpression, isDelegate, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
            }
        });
    }

    /* JADX WARN: Failed to calculate best type for var: r16v4 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r16v4 ??, new type: boolean
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r16v5 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r16v5 ??, new type: boolean
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r16v6 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r16v6 ??, new type: boolean
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v13 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v13 ??, new type: int
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.calculateFromBounds(FixTypesVisitor.java:159)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.setBestType(FixTypesVisitor.java:136)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:241)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:224)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 6 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v13 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v13 ??, new type: int
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /* JADX WARN: Failed to calculate best type for var: r4v14 ??
    jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v14 ??, new type: int
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.calculateFromBounds(TypeInferenceVisitor.java:147)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.setBestType(TypeInferenceVisitor.java:125)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.lambda$runTypePropagation$2(TypeInferenceVisitor.java:103)
    	at java.base/java.util.ArrayList.forEach(ArrayList.java:1604)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.runTypePropagation(TypeInferenceVisitor.java:103)
    	at jadx.core.dex.visitors.typeinference.TypeInferenceVisitor.visit(TypeInferenceVisitor.java:75)
    Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
    	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
    	... 7 more
     */
    /*  JADX ERROR: Types fix failed
        jadx.core.utils.exceptions.JadxRuntimeException: Type update failed for variable: r4v13 ??, new type: int
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:109)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:59)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryPossibleTypes(FixTypesVisitor.java:186)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.deduceType(FixTypesVisitor.java:245)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryDeduceTypes(FixTypesVisitor.java:224)
        	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
        Caused by: java.lang.NullPointerException: Cannot invoke "jadx.core.dex.instructions.args.InsnArg.getType()" because "arg" is null
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.verifyType(TypeUpdate.java:210)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.queueTypeUpdate(TypeUpdate.java:171)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.sameFirstArgListener(TypeUpdate.java:454)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.requestUpdate(TypeUpdate.java:310)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.runUpdate(TypeUpdate.java:124)
        	at jadx.core.dex.visitors.typeinference.TypeUpdate.apply(TypeUpdate.java:91)
        	... 5 more
        */
    public final org.jetbrains.kotlin.ir.expressions.IrExpression convertToIrSetCall(org.jetbrains.kotlin.fir.expressions.FirVariableAssignment r22, org.jetbrains.kotlin.ir.expressions.IrExpression r23) {
        /*
            Method dump skipped, instruction units count: 771
            To view this dump add '--comments-level debug' option
        */
        throw new UnsupportedOperationException("Method not decompiled: org.jetbrains.kotlin.fir.backend.generators.CallAndReferenceGenerator.convertToIrSetCall(org.jetbrains.kotlin.fir.expressions.FirVariableAssignment, org.jetbrains.kotlin.ir.expressions.IrExpression):org.jetbrains.kotlin.ir.expressions.IrExpression");
    }

    public final InjectedValue findInjectedValue$org_jetbrains_kotlin_fir2ir(FirReference calleeReference) {
        calleeReference.getClass();
        return getExtensions().mo248findInjectedValue(calleeReference, this.conversionScope);
    }

    public final IrExpression findIrReceiver$org_jetbrains_kotlin_fir2ir(FirQualifiedAccessExpression firQualifiedAccessExpression, IrExpression irExpression, boolean z) {
        IrExpression irExpressionConvertToIrReceiverExpression$org_jetbrains_kotlin_fir2ir;
        firQualifiedAccessExpression.getClass();
        FirExpression dispatchReceiver = z ? firQualifiedAccessExpression.getDispatchReceiver() : firQualifiedAccessExpression.getExtensionReceiver();
        return (Intrinsics.areEqual(dispatchReceiver, firQualifiedAccessExpression.getExplicitReceiver()) || dispatchReceiver == null || (irExpressionConvertToIrReceiverExpression$org_jetbrains_kotlin_fir2ir = this.visitor.convertToIrReceiverExpression$org_jetbrains_kotlin_fir2ir(dispatchReceiver, firQualifiedAccessExpression)) == null) ? irExpression : irExpressionConvertToIrReceiverExpression$org_jetbrains_kotlin_fir2ir;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AdapterGenerator getAdapterGenerator() {
        return this.c.getAdapterGenerator();
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AnnotationGenerator getAnnotationGenerator() {
        return this.c.getAnnotationGenerator();
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

    /* JADX WARN: Code duplicated, block: B:41:0x0083  */
    public final IrExpression injectGetValueCall$org_jetbrains_kotlin_fir2ir(FirElement element, FirReference calleeReference) {
        TokenSet tokenSet;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        element.getClass();
        calleeReference.getClass();
        InjectedValue injectedValueFindInjectedValue$org_jetbrains_kotlin_fir2ir = findInjectedValue$org_jetbrains_kotlin_fir2ir(calleeReference);
        if (injectedValueFindInjectedValue$org_jetbrains_kotlin_fir2ir == null) {
            return null;
        }
        if (element instanceof FirNamedFunction) {
            tokenSet = OffsetUtilsKt.FUNCTION_KEYWORD_TOKENS;
        } else if (element instanceof FirConstructor) {
            tokenSet = OffsetUtilsKt.CONSTRUCTOR_KEYWORD_TOKENS;
        } else {
            tokenSet = element instanceof FirVariable ? KtTokens.VAL_VAR : null;
        }
        KtSourceElement source = element.getSource();
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
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, tokenSet)) == null) {
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
        return useInjectedValue$org_jetbrains_kotlin_fir2ir(injectedValueFindInjectedValue$org_jetbrains_kotlin_fir2ir, calleeReference, i, endOffset);
    }

    public final IrGetValueImpl useInjectedValue$org_jetbrains_kotlin_fir2ir(InjectedValue injectedValue, FirReference calleeReference, int startOffset, int endOffset) {
        injectedValue.getClass();
        calleeReference.getClass();
        return BuildersKt.IrGetValueImpl(startOffset, endOffset, toIrType(injectedValue.getTypeRef()), injectedValue.getIrParameterSymbol(), OriginUtilsKt.statementOrigin(calleeReference));
    }

    private final IrType toIrType(ConeKotlinType coneKotlinType) {
        return Fir2IrTypeConverterKt.toIrType(this, coneKotlinType, this.conversionScope.defaultConversionTypeOrigin());
    }

    public final IrExpression convertToGetObject$org_jetbrains_kotlin_fir2ir(FirResolvedQualifier qualifier) {
        qualifier.getClass();
        IrExpression irExpressionConvertToGetObject$org_jetbrains_kotlin_fir2ir = convertToGetObject$org_jetbrains_kotlin_fir2ir(qualifier, null);
        irExpressionConvertToGetObject$org_jetbrains_kotlin_fir2ir.getClass();
        return irExpressionConvertToGetObject$org_jetbrains_kotlin_fir2ir;
    }

    private final IrDynamicOperator getDynamicOperator(Name name) {
        if (Intrinsics.areEqual(name, OperatorNameConventions.UNARY_PLUS)) {
            return IrDynamicOperator.UNARY_PLUS;
        }
        if (Intrinsics.areEqual(name, OperatorNameConventions.UNARY_MINUS)) {
            return IrDynamicOperator.UNARY_MINUS;
        }
        if (Intrinsics.areEqual(name, OperatorNameConventions.NOT)) {
            return IrDynamicOperator.EXCL;
        }
        if (Intrinsics.areEqual(name, OperatorNameConventions.PLUS)) {
            return IrDynamicOperator.BINARY_PLUS;
        }
        if (Intrinsics.areEqual(name, OperatorNameConventions.MINUS)) {
            return IrDynamicOperator.BINARY_MINUS;
        }
        if (Intrinsics.areEqual(name, OperatorNameConventions.TIMES)) {
            return IrDynamicOperator.MUL;
        }
        if (Intrinsics.areEqual(name, OperatorNameConventions.DIV)) {
            return IrDynamicOperator.DIV;
        }
        if (Intrinsics.areEqual(name, OperatorNameConventions.REM)) {
            return IrDynamicOperator.MOD;
        }
        if (Intrinsics.areEqual(name, OperatorNameConventions.AND)) {
            return IrDynamicOperator.ANDAND;
        }
        if (Intrinsics.areEqual(name, OperatorNameConventions.OR)) {
            return IrDynamicOperator.OROR;
        }
        if (Intrinsics.areEqual(name, OperatorNameConventions.EQUALS)) {
            return IrDynamicOperator.EQEQ;
        }
        if (Intrinsics.areEqual(name, OperatorNameConventions.PLUS_ASSIGN)) {
            return IrDynamicOperator.PLUSEQ;
        }
        if (Intrinsics.areEqual(name, OperatorNameConventions.MINUS_ASSIGN)) {
            return IrDynamicOperator.MINUSEQ;
        }
        if (Intrinsics.areEqual(name, OperatorNameConventions.TIMES_ASSIGN)) {
            return IrDynamicOperator.MULEQ;
        }
        if (Intrinsics.areEqual(name, OperatorNameConventions.DIV_ASSIGN)) {
            return IrDynamicOperator.DIVEQ;
        }
        if (Intrinsics.areEqual(name, OperatorNameConventions.REM_ASSIGN)) {
            return IrDynamicOperator.MODEQ;
        }
        return null;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:64:0x00ff  */
    public final IrExpression convertToIrSetCall(FirExpression rValue, FirPropertySymbol property) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirExpression firExpression;
        IrFile irFileContainingFileIfAny;
        IrClass irClass;
        int i;
        int i2;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        rValue.getClass();
        property.getClass();
        Fir2IrConversionScope fir2IrConversionScope = this.conversionScope;
        try {
            firExpression = rValue;
            try {
                IrExpression irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default = Fir2IrVisitor.convertToIrExpression$org_jetbrains_kotlin_fir2ir$default(this.visitor, firExpression, false, property.getResolvedReturnType(), 2, null);
                IrFieldSymbol irBackingFieldSymbol$org_jetbrains_kotlin_fir2ir = getDeclarationStorage().getIrBackingFieldSymbol$org_jetbrains_kotlin_fir2ir(property);
                FirClassLikeSymbol<?> containingClassSymbol = ContainingClassUtilsKt.getContainingClassSymbol(property);
                containingClassSymbol.getClass();
                IrClassSymbol irClassSymbol = getClassifierStorage().getIrClassSymbol((FirClassSymbol<?>) containingClassSymbol);
                Fir2IrConversionScope fir2IrConversionScope2 = this.conversionScope;
                if (!AbstractTypeChecker.RUN_SLOW_ASSERTIONS) {
                    IrSymbolOwner owner = irClassSymbol.getOwner();
                    if (owner == null) {
                        throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.ir.declarations.IrClass");
                    }
                    irClass = (IrClass) owner;
                } else {
                    Iterator it = CollectionsKt.asReversedMutable(fir2IrConversionScope2.get_parentStack()).iterator();
                    while (true) {
                        if (it.hasNext()) {
                            IrDeclaration irDeclaration = (IrDeclarationParent) it.next();
                            IrDeclaration irDeclaration2 = irDeclaration instanceof IrDeclaration ? irDeclaration : null;
                            if (Intrinsics.areEqual(irDeclaration2 != null ? irDeclaration2.getSymbol() : null, irClassSymbol)) {
                                if (irDeclaration == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.ir.declarations.IrClass");
                                }
                                irClass = (IrClass) irDeclaration;
                                break;
                            }
                        } else {
                            if (fir2IrConversionScope2.getConfiguration().getAllowNonCachedDeclarations()) {
                                IrClass owner2 = irClassSymbol.getOwner();
                                if (owner2 == null) {
                                    throw new NullPointerException("null cannot be cast to non-null type org.jetbrains.kotlin.ir.declarations.IrClass");
                                }
                                irClass = owner2;
                                break;
                            }
                            throw new IllegalStateException(("Declaration with symbol " + irClassSymbol + " is not found in parents stack").toString());
                        }
                    }
                }
                IrValueParameter irValueParameterDispatchReceiverParameter = this.conversionScope.dispatchReceiverParameter(irClass);
                irValueParameterDispatchReceiverParameter.getClass();
                KtSourceElement source = firExpression.getSource();
                if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
                    i = -1;
                    i2 = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                        i = -1;
                        i2 = -1;
                    } else {
                        if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                            i = -1;
                            i2 = -1;
                        } else {
                            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                                i = -1;
                                i2 = -1;
                            } else {
                                if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                                    startOffset = source != null ? source.getStartOffset() : -1;
                                } else {
                                    startOffset = numStartOffsetSkippingComments.intValue();
                                }
                                int endOffset = source != null ? source.getEndOffset() : -1;
                                i = startOffset;
                                i2 = endOffset;
                            }
                        }
                    }
                }
                if (irBackingFieldSymbol$org_jetbrains_kotlin_fir2ir instanceof IrFieldSymbol) {
                    IrType unitType = getBuiltins().getUnitType();
                    IrStatementOrigin.Companion companion = IrStatementOrigin.Companion;
                    IrSetFieldImpl irSetFieldImplIrSetFieldImpl$default = BuildersKt.IrSetFieldImpl$default(i, i2, irBackingFieldSymbol$org_jetbrains_kotlin_fir2ir, unitType, companion.getEQ(), (IrClassSymbol) null, 32, (Object) null);
                    irSetFieldImplIrSetFieldImpl$default.setValue(irExpressionConvertToIrExpression$org_jetbrains_kotlin_fir2ir$default);
                    irSetFieldImplIrSetFieldImpl$default.setReceiver(BuildersKt.IrGetValueImpl(i, i2, irValueParameterDispatchReceiverParameter.getType(), irValueParameterDispatchReceiverParameter.getSymbol(), companion.getIMPLICIT_ARGUMENT()));
                    return irSetFieldImplIrSetFieldImpl$default;
                }
                return BuildersKt.IrErrorCallExpressionImpl(i, i2, Fir2IrTypeConverterKt.createErrorType$default(null, false, 3, null), "Unresolved reference: " + property.getName());
            } catch (Throwable th) {
                th = th;
                Throwable th2 = th;
                String str = "Exception was thrown during transformation of " + firExpression.getClass();
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(th2);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(str, th2);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", firExpression);
                if (fir2IrConversionScope != null && (irFileContainingFileIfAny = fir2IrConversionScope.containingFileIfAny()) != null) {
                    exceptionAttachmentBuilder.withEntry("file", IrDeclarationsKt.getPath(irFileContainingFileIfAny));
                }
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        } catch (Throwable th3) {
            th = th3;
            firExpression = rValue;
        }
    }
}
