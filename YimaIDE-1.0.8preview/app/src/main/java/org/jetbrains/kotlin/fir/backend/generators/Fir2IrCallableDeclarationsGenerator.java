package org.jetbrains.kotlin.fir.backend.generators;

import com.intellij.psi.tree.TokenSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKind;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor;
import org.jetbrains.kotlin.descriptors.ParameterDescriptor;
import org.jetbrains.kotlin.descriptors.PropertyDescriptor;
import org.jetbrains.kotlin.descriptors.VariableDescriptor;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirElement;
import org.jetbrains.kotlin.fir.FirEvaluatorResult;
import org.jetbrains.kotlin.fir.FirLabel;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.UtilsKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrBuiltinSymbolsContainer;
import org.jetbrains.kotlin.fir.backend.Fir2IrClassifierStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrConfiguration;
import org.jetbrains.kotlin.fir.backend.Fir2IrConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrDeclarationStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrDeclarationStorageKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrExtensions;
import org.jetbrains.kotlin.fir.backend.Fir2IrImplicitCastInserter;
import org.jetbrains.kotlin.fir.backend.Fir2IrIrGeneratedDeclarationsRegistrar;
import org.jetbrains.kotlin.fir.backend.Fir2IrSymbolsMappingForLazyClasses;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverterKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrVisibilityConverter;
import org.jetbrains.kotlin.fir.backend.FirMetadataSource;
import org.jetbrains.kotlin.fir.backend.FirProviderWithGeneratedFiles;
import org.jetbrains.kotlin.fir.backend.LocalDelegatedPropertySymbols;
import org.jetbrains.kotlin.fir.backend.PropertySymbols;
import org.jetbrains.kotlin.fir.backend.utils.ConstantUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.ConversionTypeOrigin;
import org.jetbrains.kotlin.fir.backend.utils.IrElementsCreationUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.OffsetUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.OriginUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.VariousUtilsKt;
import org.jetbrains.kotlin.fir.declarations.DeclarationUtilsKt;
import org.jetbrains.kotlin.fir.declarations.ExpectActualAttributesKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousFunction;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousInitializer;
import org.jetbrains.kotlin.fir.declarations.FirBackingField;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirPropertyAccessor;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirScript;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.FirValueParameterKind;
import org.jetbrains.kotlin.fir.declarations.FirVariable;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertyGetter;
import org.jetbrains.kotlin.fir.declarations.impl.FirDefaultPropertySetter;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.declarations.utils.FirStatusUtilsKt;
import org.jetbrains.kotlin.fir.descriptors.FirModuleDescriptor;
import org.jetbrains.kotlin.fir.descriptors.FirPackageFragmentDescriptor;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.expressions.FirLiteralExpression;
import org.jetbrains.kotlin.fir.expressions.FirQualifiedAccessExpression;
import org.jetbrains.kotlin.fir.expressions.impl.FirExpressionStub;
import org.jetbrains.kotlin.fir.java.declarations.FirJavaField;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazyClass;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazyField;
import org.jetbrains.kotlin.fir.references.FirReference;
import org.jetbrains.kotlin.fir.references.FirReferenceUtilsKt;
import org.jetbrains.kotlin.fir.resolve.CallableIdUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.calls.FirSimpleSyntheticPropertySymbol;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirProviderUtilsKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.fir.symbols.impl.FirFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirLocalPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirValueParameterSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;
import org.jetbrains.kotlin.fir.types.ConeRigidType;
import org.jetbrains.kotlin.fir.types.ConeSimpleKotlinType;
import org.jetbrains.kotlin.fir.types.FirResolvedTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.FirTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.FunctionalTypeUtilsKt;
import org.jetbrains.kotlin.fir.types.TypeComponentsKt;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.declarations.IrAnonymousInitializer;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrDeclaration;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationWithName;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationWithVisibility;
import org.jetbrains.kotlin.ir.declarations.IrExternalPackageFragment;
import org.jetbrains.kotlin.ir.declarations.IrFactory;
import org.jetbrains.kotlin.ir.declarations.IrFactoryHelpersKt;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.ir.declarations.IrFunction;
import org.jetbrains.kotlin.ir.declarations.IrLocalDelegatedProperty;
import org.jetbrains.kotlin.ir.declarations.IrMutableAnnotationContainer;
import org.jetbrains.kotlin.ir.declarations.IrOverridableMember;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrReplSnippet;
import org.jetbrains.kotlin.ir.declarations.IrScript;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.declarations.IrVariable;
import org.jetbrains.kotlin.ir.declarations.impl.BuildersKt;
import org.jetbrains.kotlin.ir.declarations.impl.IrExternalPackageFragmentImpl;
import org.jetbrains.kotlin.ir.declarations.impl.IrFactoryImpl;
import org.jetbrains.kotlin.ir.declarations.impl.IrReplSnippetImpl;
import org.jetbrains.kotlin.ir.declarations.impl.IrScriptImpl;
import org.jetbrains.kotlin.ir.declarations.impl.ScriptAndSnippetDetailsKt;
import org.jetbrains.kotlin.ir.expressions.IrConst;
import org.jetbrains.kotlin.ir.expressions.IrExpressionBody;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.symbols.IrFieldSymbol;
import org.jetbrains.kotlin.ir.symbols.IrLocalDelegatedPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrReplSnippetSymbol;
import org.jetbrains.kotlin.ir.symbols.IrScriptSymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrAnonymousInitializerSymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrExternalPackageFragmentSymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrPropertySymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrValueParameterSymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrVariableSymbolImpl;
import org.jetbrains.kotlin.ir.types.IrSimpleType;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.ir.util.IrFakeOverrideUtilsKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.mpp.DeclarationSymbolMarker;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.NameUtils;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;
import org.jetbrains.kotlin.types.AbstractTypeChecker;
import org.jetbrains.kotlin.types.model.RigidTypeMarker;
import org.jetbrains.kotlin.types.model.TypeCheckerProviderContext;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;
import org.jetbrains.kotlin.utils.exceptions.PlatformExceptionUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000¨\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010!\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 û\u00012\u00020\u0001:\u0002û\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u001d\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0000¢\u0006\u0002\b\u000bJ\u0015\u0010\u0005\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0000¢\u0006\u0002\b\u000bJD\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020\u00152\b\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u0019J2\u0010\u001f\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010\u0012\u001a\u00020#2\u0006\u0010\u0014\u001a\u00020$2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\u0006\u0010\u001c\u001a\u00020\u0019J@\u0010%\u001a\u00020&2\u0006\u0010'\u001a\u00020(2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010)\u001a\u00020*2\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u00172\n\b\u0002\u0010\u001a\u001a\u0004\u0018\u00010\u001b2\u0006\u0010\u001c\u001a\u00020\u0019J\u001a\u0010+\u001a\u0004\u0018\u00010,2\u0006\u0010'\u001a\u00020(2\u0006\u0010-\u001a\u00020\u0019H\u0002J\u0016\u0010.\u001a\u00020&2\u0006\u0010/\u001a\u0002002\u0006\u00101\u001a\u00020\u0013Jh\u00102\u001a\u00020\u000f2\b\u00103\u001a\u0004\u0018\u0001042\u0006\u0010'\u001a\u00020(2\u0006\u00105\u001a\u0002062\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u00107\u001a\u0002082\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u00109\u001a\u00020\u00192\u0006\u0010:\u001a\u00020\u00172\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<2\n\b\u0002\u0010>\u001a\u0004\u0018\u000104H\u0002J[\u0010?\u001a\u00020@2\u0006\u0010A\u001a\u00020&2\u0006\u0010B\u001a\u00020(2\u0006\u0010:\u001a\u00020\u00172\u0006\u0010\u0014\u001a\u00020C2\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020G2\u0006\u0010H\u001a\u00020\u00192\b\u0010I\u001a\u0004\u0018\u00010,2\n\b\u0002\u0010J\u001a\u0004\u0018\u000108H\u0000¢\u0006\u0002\bKJ;\u0010P\u001a\u00020@2\u0006\u0010Q\u001a\u00020R2\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0014\u001a\u00020C2\b\b\u0002\u0010J\u001a\u00020S2\b\b\u0002\u0010:\u001a\u00020\u0017H\u0000¢\u0006\u0002\bTJW\u0010U\u001a\u00020V2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<2\u0006\u0010J\u001a\u0002082\u0006\u00101\u001a\u00020W2\b\u0010X\u001a\u0004\u0018\u00010Y2\n\b\u0002\u0010F\u001a\u0004\u0018\u00010G2\b\b\u0002\u0010Z\u001a\u00020\u00192\b\b\u0002\u0010[\u001a\u00020\u0019H\u0000¢\u0006\u0002\b\\J*\u0010]\u001a\u00020^2\f\u0010_\u001a\b\u0012\u0004\u0012\u00020Y0`2\u0006\u00101\u001a\u00020W2\f\u0010a\u001a\b\u0012\u0004\u0012\u00020V0bJF\u0010c\u001a\u00020^*\u00020W2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u00132\b\u0010d\u001a\u0004\u0018\u0001082\u0006\u0010e\u001a\u00020\u00192\u0006\u0010f\u001a\u00020\u00192\n\b\u0002\u0010g\u001a\u0004\u0018\u00010(H\u0002JI\u0010h\u001a\u00020V2\u0006\u0010i\u001a\u00020Y2\b\b\u0002\u0010j\u001a\u00020\u00192\b\b\u0002\u0010k\u001a\u00020l2\b\b\u0002\u0010m\u001a\u00020\u00192\b\b\u0002\u0010n\u001a\u00020\u00192\n\b\u0002\u0010\u0016\u001a\u0004\u0018\u00010\u0017H\u0000¢\u0006\u0002\boJ\u001e\u0010p\u001a\u00020q2\u0006\u0010'\u001a\u00020(2\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010)\u001a\u00020rJ \u0010s\u001a\u00020t2\u0006\u0010u\u001a\u00020v2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010w\u001a\u0004\u0018\u00010\u0017JH\u0010x\u001a\u00020t2\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020<2\u0006\u0010:\u001a\u00020\u00172\u0006\u0010F\u001a\u00020G2\u0006\u0010J\u001a\u0002082\u0006\u0010y\u001a\u00020\u00192\u0006\u0010z\u001a\u00020\u00192\u0006\u0010{\u001a\u00020\u0019H\u0002J\u0016\u0010|\u001a\u00020}2\u0006\u0010~\u001a\u00020\u007f2\u0006\u0010\u0012\u001a\u00020#J\u001b\u0010\u0080\u0001\u001a\u00030\u0081\u00012\b\u0010\u0082\u0001\u001a\u00030\u0083\u00012\u0007\u0010\u0014\u001a\u00030\u0084\u0001J\u001b\u0010\u0085\u0001\u001a\u00030\u0086\u00012\b\u0010\u0087\u0001\u001a\u00030\u0088\u00012\u0007\u0010\u0014\u001a\u00030\u0089\u0001J \u0010\u008a\u0001\u001a\u00020^*\u00030\u008b\u00012\b\u0010\u008c\u0001\u001a\u00030\u008d\u00012\u0006\u0010:\u001a\u00020\u0017H\u0002J\u000e\u0010\u008e\u0001\u001a\u00020\u0019*\u00030\u008d\u0001H\u0002R\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010\u001d\u001a\u00020\u0019*\u0004\u0018\u00010\u00178BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u001eR\u0018\u0010L\u001a\u00020M*\u00020(8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\bN\u0010OR\u0016\u0010\u008f\u0001\u001a\u00030\u0090\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0091\u0001\u0010\u0092\u0001R\u0016\u0010\u0093\u0001\u001a\u00030\u0094\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0095\u0001\u0010\u0096\u0001R\u0016\u0010\u0097\u0001\u001a\u00030\u0098\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0099\u0001\u0010\u009a\u0001R\u0016\u0010\u009b\u0001\u001a\u00030\u009c\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009d\u0001\u0010\u009e\u0001R\u0016\u0010\u009f\u0001\u001a\u00030 \u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¡\u0001\u0010¢\u0001R\u0015\u0010£\u0001\u001a\u00020\u0000X\u0096\u0005¢\u0006\b\u001a\u0006\b¤\u0001\u0010¥\u0001R\u0016\u0010¦\u0001\u001a\u00030§\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¨\u0001\u0010©\u0001R\u0016\u0010ª\u0001\u001a\u00030«\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¬\u0001\u0010\u00ad\u0001R\u0016\u0010®\u0001\u001a\u00030¯\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b°\u0001\u0010±\u0001R\u0016\u0010²\u0001\u001a\u00030³\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b´\u0001\u0010µ\u0001R\u0016\u0010¶\u0001\u001a\u00030·\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¸\u0001\u0010¹\u0001R\u0016\u0010º\u0001\u001a\u00030»\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¼\u0001\u0010½\u0001R\u0016\u0010¾\u0001\u001a\u00030¿\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÀ\u0001\u0010Á\u0001R\u001f\u0010Â\u0001\u001a\f\u0012\u0005\u0012\u00030Ä\u0001\u0018\u00010Ã\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÅ\u0001\u0010Æ\u0001R\u0016\u0010Ç\u0001\u001a\u00030È\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÉ\u0001\u0010Ê\u0001R\u0016\u0010Ë\u0001\u001a\u00030Ì\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÍ\u0001\u0010Î\u0001R\u0016\u0010Ï\u0001\u001a\u00030Ð\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÑ\u0001\u0010Ò\u0001R\u001c\u0010Ó\u0001\u001a\t\u0012\u0005\u0012\u00030Ô\u00010`X\u0096\u0005¢\u0006\b\u001a\u0006\bÕ\u0001\u0010Ö\u0001R\u0016\u0010×\u0001\u001a\u00030Ø\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÙ\u0001\u0010Ú\u0001R\u0016\u0010Û\u0001\u001a\u00030Ü\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÝ\u0001\u0010Þ\u0001R\u0016\u0010ß\u0001\u001a\u00030à\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bá\u0001\u0010â\u0001R\u0016\u0010ã\u0001\u001a\u00030ä\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bå\u0001\u0010æ\u0001R\u0016\u0010ç\u0001\u001a\u00030è\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bé\u0001\u0010ê\u0001R\u0018\u0010ë\u0001\u001a\u0005\u0018\u00010ì\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bí\u0001\u0010î\u0001R\u0016\u0010ï\u0001\u001a\u00030ð\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bñ\u0001\u0010ò\u0001R\u0016\u0010ó\u0001\u001a\u00030ô\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bõ\u0001\u0010ö\u0001R\u0016\u0010÷\u0001\u001a\u00030ø\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bù\u0001\u0010ú\u0001¨\u0006ü\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;)V", "createExternalPackageFragment", "Lorg/jetbrains/kotlin/ir/declarations/IrExternalPackageFragment;", "fqName", "Lorg/jetbrains/kotlin/name/FqName;", "moduleDescriptor", "Lorg/jetbrains/kotlin/fir/descriptors/FirModuleDescriptor;", "createExternalPackageFragment$org_jetbrains_kotlin_fir2ir", "packageFragmentDescriptor", "Lorg/jetbrains/kotlin/descriptors/PackageFragmentDescriptor;", "createIrFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "function", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "irParent", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;", "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "predefinedOrigin", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "isLocal", Argument.Delimiters.none, "fakeOverrideOwnerLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "allowLazyDeclarationsCreation", "isExternal", "(Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;)Z", "createIrConstructor", "Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", "constructor", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "Lorg/jetbrains/kotlin/ir/symbols/IrConstructorSymbol;", "createIrProperty", "Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", "property", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "symbols", "Lorg/jetbrains/kotlin/fir/backend/PropertySymbols;", "getEffectivePropertyInitializer", "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "resolveIfNeeded", "generateIrPropertyForSyntheticPropertyReference", "propertySymbol", "Lorg/jetbrains/kotlin/fir/resolve/calls/FirSimpleSyntheticPropertySymbol;", "parent", "createIrPropertyAccessor", "propertyAccessor", "Lorg/jetbrains/kotlin/fir/declarations/FirPropertyAccessor;", "correspondingProperty", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationWithName;", "propertyType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "isSetter", "origin", "startOffset", Argument.Delimiters.none, "endOffset", "propertyAccessorForAnnotations", "createBackingField", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "irProperty", "firProperty", "Lorg/jetbrains/kotlin/ir/symbols/IrFieldSymbol;", "visibility", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "isFinal", "firInitializerExpression", ModuleXmlParser.TYPE, "createBackingField$org_jetbrains_kotlin_fir2ir", "fieldVisibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", "getFieldVisibility", "(Lorg/jetbrains/kotlin/fir/declarations/FirProperty;)Lorg/jetbrains/kotlin/descriptors/Visibility;", "createIrField", "field", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "createIrField$org_jetbrains_kotlin_fir2ir", "createDefaultSetterParameter", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "Lorg/jetbrains/kotlin/ir/declarations/IrFunction;", "firValueParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirValueParameter;", "isCrossinline", "isNoinline", "createDefaultSetterParameter$org_jetbrains_kotlin_fir2ir", "addContextParametersTo", Argument.Delimiters.none, "contextParameters", Argument.Delimiters.none, CoroutineCodegenUtilKt.CONTINUATION_RESULT_FIELD_NAME, Argument.Delimiters.none, "declareParameters", "dispatchReceiverType", "isStatic", "forSetter", "parentProperty", "createIrParameter", "valueParameter", "useStubForDefaultValueStub", "typeOrigin", "Lorg/jetbrains/kotlin/fir/backend/utils/ConversionTypeOrigin;", "skipDefaultParameter", "forcedDefaultValueConversion", "createIrParameter$org_jetbrains_kotlin_fir2ir", "createIrLocalDelegatedProperty", "Lorg/jetbrains/kotlin/ir/declarations/IrLocalDelegatedProperty;", "Lorg/jetbrains/kotlin/fir/backend/LocalDelegatedPropertySymbols;", "createIrVariable", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "variable", "Lorg/jetbrains/kotlin/fir/declarations/FirVariable;", "givenOrigin", "declareIrVariable", "isVar", "isConst", "isLateinit", "createIrAnonymousInitializer", "Lorg/jetbrains/kotlin/ir/declarations/IrAnonymousInitializer;", "anonymousInitializer", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousInitializer;", "createIrScript", "Lorg/jetbrains/kotlin/ir/declarations/IrScript;", "script", "Lorg/jetbrains/kotlin/fir/declarations/FirScript;", "Lorg/jetbrains/kotlin/ir/symbols/IrScriptSymbol;", "createIrReplSnippet", "Lorg/jetbrains/kotlin/ir/declarations/IrReplSnippet;", "snippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "Lorg/jetbrains/kotlin/ir/symbols/IrReplSnippetSymbol;", "convertAnnotationsForNonDeclaredMembers", "Lorg/jetbrains/kotlin/ir/declarations/IrMutableAnnotationContainer;", "firAnnotationContainer", "Lorg/jetbrains/kotlin/fir/FirAnnotationContainer;", "isDeclaredInFilesBeingCompiled", "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "()Ljava/util/List;", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "Companion", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrCallableDeclarationsGenerator implements Fir2IrComponents {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Fir2IrComponents c;

    public Fir2IrCallableDeclarationsGenerator(Fir2IrComponents fir2IrComponents) {
        fir2IrComponents.getClass();
        this.c = fir2IrComponents;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void convertAnnotationsForNonDeclaredMembers(IrMutableAnnotationContainer irMutableAnnotationContainer, FirAnnotationContainer firAnnotationContainer, IrDeclarationOrigin irDeclarationOrigin) {
        FirDeclaration firDeclaration = firAnnotationContainer instanceof FirDeclaration ? (FirDeclaration) firAnnotationContainer : null;
        if ((firDeclaration != null && (Intrinsics.areEqual(firDeclaration.getOrigin(), FirDeclarationOrigin.Library.INSTANCE) || Intrinsics.areEqual(firDeclaration.getOrigin(), FirDeclarationOrigin.Java.Library.INSTANCE) || Intrinsics.areEqual(firDeclaration.getOrigin(), FirDeclarationOrigin.Precompiled.INSTANCE))) || Intrinsics.areEqual(irDeclarationOrigin, IrDeclarationOrigin.Companion.getFAKE_OVERRIDE()) || isDeclaredInFilesBeingCompiled(firAnnotationContainer)) {
            getAnnotationGenerator().generate(irMutableAnnotationContainer, firAnnotationContainer);
        }
    }

    public static /* synthetic */ IrValueParameter createDefaultSetterParameter$org_jetbrains_kotlin_fir2ir$default(Fir2IrCallableDeclarationsGenerator fir2IrCallableDeclarationsGenerator, int i, int i2, IrType irType, IrFunction irFunction, FirValueParameter firValueParameter, Name name, boolean z, boolean z2, int i3, Object obj) {
        return fir2IrCallableDeclarationsGenerator.createDefaultSetterParameter$org_jetbrains_kotlin_fir2ir(i, i2, irType, irFunction, firValueParameter, (i3 & 32) != 0 ? null : name, (i3 & 64) != 0 ? false : z, (i3 & 128) != 0 ? false : z2);
    }

    public static /* synthetic */ IrConstructor createIrConstructor$default(Fir2IrCallableDeclarationsGenerator fir2IrCallableDeclarationsGenerator, FirConstructor firConstructor, IrClass irClass, IrConstructorSymbol irConstructorSymbol, IrDeclarationOrigin irDeclarationOrigin, boolean z, int i, Object obj) {
        if ((i & 8) != 0) {
            irDeclarationOrigin = null;
        }
        return fir2IrCallableDeclarationsGenerator.createIrConstructor(firConstructor, irClass, irConstructorSymbol, irDeclarationOrigin, z);
    }

    public static /* synthetic */ IrField createIrField$org_jetbrains_kotlin_fir2ir$default(Fir2IrCallableDeclarationsGenerator fir2IrCallableDeclarationsGenerator, FirField firField, IrDeclarationParent irDeclarationParent, IrFieldSymbol irFieldSymbol, ConeKotlinType coneKotlinType, IrDeclarationOrigin irDeclarationOrigin, int i, Object obj) {
        if ((i & 8) != 0) {
            coneKotlinType = FirTypeUtilsKt.getConeType(firField.getReturnTypeRef());
        }
        ConeKotlinType coneKotlinType2 = coneKotlinType;
        if ((i & 16) != 0) {
            irDeclarationOrigin = IrDeclarationOrigin.Companion.getIR_EXTERNAL_JAVA_DECLARATION_STUB();
        }
        return fir2IrCallableDeclarationsGenerator.createIrField$org_jetbrains_kotlin_fir2ir(firField, irDeclarationParent, irFieldSymbol, coneKotlinType2, irDeclarationOrigin);
    }

    public static /* synthetic */ IrValueParameter createIrParameter$org_jetbrains_kotlin_fir2ir$default(Fir2IrCallableDeclarationsGenerator fir2IrCallableDeclarationsGenerator, FirValueParameter firValueParameter, boolean z, ConversionTypeOrigin conversionTypeOrigin, boolean z2, boolean z3, IrDeclarationOrigin irDeclarationOrigin, int i, Object obj) {
        if ((i & 2) != 0) {
            z = true;
        }
        boolean z4 = z;
        if ((i & 4) != 0) {
            conversionTypeOrigin = ConversionTypeOrigin.DEFAULT;
        }
        ConversionTypeOrigin conversionTypeOrigin2 = conversionTypeOrigin;
        boolean z5 = (i & 8) != 0 ? false : z2;
        boolean z6 = (i & 16) != 0 ? false : z3;
        if ((i & 32) != 0) {
            irDeclarationOrigin = null;
        }
        return fir2IrCallableDeclarationsGenerator.createIrParameter$org_jetbrains_kotlin_fir2ir(firValueParameter, z4, conversionTypeOrigin2, z5, z6, irDeclarationOrigin);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public static /* synthetic */ IrProperty createIrProperty$default(Fir2IrCallableDeclarationsGenerator fir2IrCallableDeclarationsGenerator, FirProperty firProperty, IrDeclarationParent irDeclarationParent, PropertySymbols propertySymbols, IrDeclarationOrigin irDeclarationOrigin, ConeClassLikeLookupTag coneClassLikeLookupTag, boolean z, int i, Object obj) throws KotlinIllegalArgumentExceptionWithAttachments {
        if ((i & 8) != 0) {
            irDeclarationOrigin = null;
        }
        if ((i & 16) != 0) {
            coneClassLikeLookupTag = null;
        }
        return fir2IrCallableDeclarationsGenerator.createIrProperty(firProperty, irDeclarationParent, propertySymbols, irDeclarationOrigin, coneClassLikeLookupTag, z);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    public final IrSimpleFunction createIrPropertyAccessor(FirPropertyAccessor propertyAccessor, FirProperty property, IrDeclarationWithName correspondingProperty, IrSimpleFunctionSymbol symbol, IrType propertyType, IrDeclarationParent irParent, boolean isSetter, IrDeclarationOrigin origin, int startOffset, int endOffset, FirPropertyAccessor propertyAccessorForAnnotations) throws KotlinIllegalArgumentExceptionWithAttachments {
        Modality modality;
        Visibility visibility;
        Object obj = propertyAccessor != null ? propertyAccessor : property;
        String str = isSetter ? "set" : "get";
        try {
            IrProperty irProperty = correspondingProperty instanceof IrProperty ? (IrProperty) correspondingProperty : null;
            DeserializedContainerSource containerSource = irProperty != null ? irProperty.getContainerSource() : null;
            IrType unitType = isSetter ? getBuiltins().getUnitType() : propertyType;
            DescriptorVisibility descriptorVisibilityConvertToDescriptorVisibility = (propertyAccessor == null || (visibility = propertyAccessor.getStatus().getVisibility()) == null) ? null : this.c.getVisibilityConverter().convertToDescriptorVisibility(visibility);
            IrFactoryImpl irFactoryImpl = IrFactoryImpl.INSTANCE;
            Name nameSpecial = Name.special("<" + str + '-' + correspondingProperty.getName() + '>');
            nameSpecial.getClass();
            if (descriptorVisibilityConvertToDescriptorVisibility == null) {
                descriptorVisibilityConvertToDescriptorVisibility = ((IrDeclarationWithVisibility) correspondingProperty).getVisibility();
            }
            DescriptorVisibility descriptorVisibility = descriptorVisibilityConvertToDescriptorVisibility;
            boolean z = propertyAccessor != null && propertyAccessor.getStatus().isInline();
            IrOverridableMember irOverridableMember = correspondingProperty instanceof IrOverridableMember ? (IrOverridableMember) correspondingProperty : null;
            if (irOverridableMember == null || (modality = irOverridableMember.getModality()) == null) {
                modality = Modality.FINAL;
            }
            boolean z2 = true;
            IrSimpleFunction irSimpleFunctionCreateSimpleFunction$default = IrFactory.createSimpleFunction$default(irFactoryImpl, startOffset, endOffset, origin, nameSpecial, descriptorVisibility, z, false, unitType, modality, symbol, false, false, false, false, Fir2IrCallableDeclarationsGeneratorKt.isEffectivelyExternal(propertyAccessor, irParent), containerSource, false, 65536, (Object) null);
            IrProperty irProperty2 = correspondingProperty instanceof IrProperty ? (IrProperty) correspondingProperty : null;
            irSimpleFunctionCreateSimpleFunction$default.setCorrespondingPropertySymbol(irProperty2 != null ? irProperty2.getSymbol() : null);
            if (propertyAccessor != null) {
                irSimpleFunctionCreateSimpleFunction$default.setMetadata(new FirMetadataSource.Function(propertyAccessor));
                convertAnnotationsForNonDeclaredMembers(irSimpleFunctionCreateSimpleFunction$default, propertyAccessor, origin);
            }
            if (propertyAccessorForAnnotations != null) {
                convertAnnotationsForNonDeclaredMembers(irSimpleFunctionCreateSimpleFunction$default, propertyAccessorForAnnotations, origin);
            }
            getClassifiersGenerator().setTypeParameters$org_jetbrains_kotlin_fir2ir(irSimpleFunctionCreateSimpleFunction$default, property, isSetter ? ConversionTypeOrigin.SETTER : ConversionTypeOrigin.DEFAULT);
            IrType irTypeComputeDispatchReceiverType$org_jetbrains_kotlin_fir2ir = INSTANCE.computeDispatchReceiverType$org_jetbrains_kotlin_fir2ir(this, irSimpleFunctionCreateSimpleFunction$default, property, irParent);
            Fir2IrDeclarationStorage declarationStorage = getDeclarationStorage();
            declarationStorage.enterScope(symbol);
            Fir2IrCallableDeclarationsGeneratorKt.setParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
            if ((irParent instanceof IrClass) && (propertyAccessor == null || !propertyAccessor.getStatus().isStatic())) {
                z2 = false;
            }
            try {
                declareParameters(irSimpleFunctionCreateSimpleFunction$default, propertyAccessor, irParent, irTypeComputeDispatchReceiverType$org_jetbrains_kotlin_fir2ir, z2, isSetter, property);
                declarationStorage.leaveScope(symbol);
                return irSimpleFunctionCreateSimpleFunction$default;
            } catch (Throwable th) {
                th = th;
                String str2 = "Exception was thrown during transformation of " + obj.getClass();
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(th);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(str2, th);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", obj);
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        } catch (Throwable th2) {
            th = th2;
        }
    }

    public static /* synthetic */ IrSimpleFunction createIrPropertyAccessor$default(Fir2IrCallableDeclarationsGenerator fir2IrCallableDeclarationsGenerator, FirPropertyAccessor firPropertyAccessor, FirProperty firProperty, IrDeclarationWithName irDeclarationWithName, IrSimpleFunctionSymbol irSimpleFunctionSymbol, IrType irType, IrDeclarationParent irDeclarationParent, boolean z, IrDeclarationOrigin irDeclarationOrigin, int i, int i2, FirPropertyAccessor firPropertyAccessor2, int i3, Object obj) {
        if ((i3 & 1024) != 0) {
            firPropertyAccessor2 = firPropertyAccessor;
        }
        return fir2IrCallableDeclarationsGenerator.createIrPropertyAccessor(firPropertyAccessor, firProperty, irDeclarationWithName, irSimpleFunctionSymbol, irType, irDeclarationParent, z, irDeclarationOrigin, i, i2, firPropertyAccessor2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final IrVariable declareIrVariable(int startOffset, int endOffset, IrDeclarationOrigin origin, Name name, IrType type, boolean isVar, boolean isConst, boolean isLateinit) {
        return BuildersKt.IrVariableImpl(startOffset, endOffset, origin, new IrVariableSymbolImpl((VariableDescriptor) null, 1, (DefaultConstructorMarker) null), name, type, isVar, isConst, isLateinit);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:106:0x0188  */
    /* JADX WARN: Code duplicated, block: B:131:0x01f6  */
    /* JADX WARN: Code duplicated, block: B:147:0x0234  */
    /* JADX WARN: Code duplicated, block: B:149:0x0239  */
    /* JADX WARN: Code duplicated, block: B:152:0x0247  */
    /* JADX WARN: Code duplicated, block: B:154:0x024a  */
    /* JADX WARN: Code duplicated, block: B:156:0x0250  */
    /* JADX WARN: Code duplicated, block: B:22:0x0070  */
    /* JADX WARN: Code duplicated, block: B:42:0x00ce  */
    /* JADX WARN: Code duplicated, block: B:57:0x00f1  */
    /* JADX WARN: Code duplicated, block: B:59:0x0103 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:60:0x0105  */
    /* JADX WARN: Code duplicated, block: B:61:0x010a  */
    /* JADX WARN: Code duplicated, block: B:64:0x0113 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:65:0x0115  */
    /* JADX WARN: Code duplicated, block: B:66:0x011a  */
    /* JADX WARN: Code duplicated, block: B:69:0x0123 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:70:0x0125  */
    /* JADX WARN: Code duplicated, block: B:71:0x012a  */
    /* JADX WARN: Code duplicated, block: B:74:0x0133  */
    /* JADX WARN: Code duplicated, block: B:75:0x0135  */
    /* JADX WARN: Code duplicated, block: B:80:0x0143 A[DONT_INVERT] */
    /* JADX WARN: Code duplicated, block: B:81:0x0145  */
    /* JADX WARN: Code duplicated, block: B:82:0x014a  */
    /* JADX WARN: Code duplicated, block: B:84:0x014d  */
    /* JADX WARN: Code duplicated, block: B:89:0x015a  */
    /* JADX WARN: Code duplicated, block: B:90:0x015e  */
    /* JADX WARN: Instruction removed from duplicated block: B:57:0x00f1, please report this as an issue */
    public final void declareParameters(IrFunction irFunction, FirFunction firFunction, IrDeclarationParent irDeclarationParent, IrType irType, boolean z, boolean z2, FirProperty firProperty) {
        Fir2IrCallableDeclarationsGenerator fir2IrCallableDeclarationsGenerator;
        IrFunction irFunction2;
        boolean z3;
        FirReceiverParameter firReceiverParameter;
        FirReceiverParameter receiverParameter;
        KtSourceElement source;
        ClassId classId;
        int i;
        int endOffset;
        ClassId classId2;
        Name nameIdentifier;
        FirLabel label;
        ClassId name;
        KtSourceElementKind kind;
        KtSourceElementKind kind2;
        KtSourceElementKind kind3;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        FirDefaultPropertySetter firDefaultPropertySetter;
        ClassId classId3;
        FirTypeRef returnTypeRef;
        List<FirValueParameter> valueParameters;
        List<FirValueParameter> valueParameters2;
        boolean z4;
        boolean z5;
        List<FirValueParameter> listContextParametersForFunctionOrContainingProperty;
        IrClass irClassComputeContainingClass = INSTANCE.computeContainingClass(irDeclarationParent);
        boolean z6 = firFunction instanceof FirNamedFunction;
        if (z6 || (firFunction instanceof FirConstructor)) {
            Fir2IrClassifiersGenerator.setTypeParameters$org_jetbrains_kotlin_fir2ir$default(getClassifiersGenerator(), irFunction, firFunction, null, 4, null);
        }
        List<IrValueParameter> listCreateListBuilder = CollectionsKt.createListBuilder();
        if (firFunction instanceof FirConstructor) {
            IrClass parentClassOrNull = irClassComputeContainingClass != null ? IrUtilsKt.getParentClassOrNull(irClassComputeContainingClass) : null;
            if (irClassComputeContainingClass == null || !irClassComputeContainingClass.isInner() || parentClassOrNull == null) {
                fir2IrCallableDeclarationsGenerator = this;
                irFunction2 = irFunction;
            } else {
                IrValueParameter thisReceiver = parentClassOrNull.getThisReceiver();
                thisReceiver.getClass();
                fir2IrCallableDeclarationsGenerator = this;
                irFunction2 = irFunction;
                listCreateListBuilder.add(IrElementsCreationUtilsKt.declareThisReceiverParameter$default(fir2IrCallableDeclarationsGenerator, irFunction2, thisReceiver.getType(), IrDeclarationOrigin.Companion.getDEFINED(), IrParameterKind.DispatchReceiver, 0, 0, null, null, false, 496, null));
            }
        } else {
            boolean z7 = z6 && Intrinsics.areEqual(((FirNamedFunction) firFunction).getStatus().getVisibility(), Visibilities.Local.INSTANCE);
            if ((firFunction instanceof FirAnonymousFunction) || irType == null || z || z7) {
                fir2IrCallableDeclarationsGenerator = this;
                irFunction2 = irFunction;
            } else {
                irFunction2 = irFunction;
                fir2IrCallableDeclarationsGenerator = this;
                listCreateListBuilder.add(IrElementsCreationUtilsKt.declareThisReceiverParameter$default(fir2IrCallableDeclarationsGenerator, irFunction2, irType, IrDeclarationOrigin.Companion.getDEFINED(), IrParameterKind.DispatchReceiver, 0, 0, null, null, false, 496, null));
            }
        }
        if (firFunction != null && (listContextParametersForFunctionOrContainingProperty = FirDeclarationUtilKt.contextParametersForFunctionOrContainingProperty(firFunction)) != null) {
            fir2IrCallableDeclarationsGenerator.addContextParametersTo(listContextParametersForFunctionOrContainingProperty, irFunction2, listCreateListBuilder);
        }
        ConversionTypeOrigin conversionTypeOrigin = z2 ? ConversionTypeOrigin.SETTER : ConversionTypeOrigin.DEFAULT;
        if (firFunction != null) {
            z3 = true;
            if (FirStatusUtilsKt.isCompanionExtension(firFunction)) {
                classId = null;
            }
            if (!(firFunction instanceof FirDefaultPropertySetter) || (z2 && firFunction == null)) {
                firDefaultPropertySetter = (FirDefaultPropertySetter) firFunction;
                if (firDefaultPropertySetter != null || (valueParameters = firDefaultPropertySetter.getValueParameters()) == null) {
                    classId3 = classId;
                } else {
                    classId3 = (FirValueParameter) CollectionsKt.first(valueParameters);
                }
                if (classId3 != null || (returnTypeRef = classId3.getReturnTypeRef()) == null) {
                    firProperty.getClass();
                    returnTypeRef = firProperty.getReturnTypeRef();
                }
                irFunction2 = irFunction;
                listCreateListBuilder.add(createDefaultSetterParameter$org_jetbrains_kotlin_fir2ir$default(fir2IrCallableDeclarationsGenerator, irFunction.getStartOffset(), irFunction.getEndOffset(), Fir2IrTypeConverterKt.toIrType(fir2IrCallableDeclarationsGenerator, returnTypeRef, ConversionTypeOrigin.SETTER), irFunction, classId3, null, false, false, 224, null));
            } else if (firFunction != null && (valueParameters2 = firFunction.getValueParameters()) != null) {
                List<IrValueParameter> list = listCreateListBuilder;
                int i2 = 0;
                for (Object obj : valueParameters2) {
                    int i3 = i2 + 1;
                    if (i2 < 0) {
                        CollectionsKt.throwIndexOverflow();
                    }
                    FirValueParameter firValueParameter = (FirValueParameter) obj;
                    Fir2IrDeclarationStorage declarationStorage = fir2IrCallableDeclarationsGenerator.getDeclarationStorage();
                    if (!(firFunction instanceof FirConstructor)) {
                        z4 = true;
                    } else if (Intrinsics.areEqual(irClassComputeContainingClass != null ? AdditionalIrUtilsKt.getClassId(irClassComputeContainingClass) : classId, StandardClassIds.INSTANCE.getEnum())) {
                        z4 = false;
                    } else {
                        z4 = true;
                    }
                    boolean z8 = IrFakeOverrideUtilsKt.isFakeOverride(irFunction2) || Intrinsics.areEqual(irFunction2.getOrigin(), IrDeclarationOrigin.Companion.getDELEGATED_MEMBER());
                    if (irClassComputeContainingClass != null) {
                        z5 = true;
                        if (!IrUtilsKt.isAnnotationClass(irClassComputeContainingClass)) {
                            z5 = false;
                        }
                    } else {
                        z5 = false;
                    }
                    IrValueParameter irValueParameterCreateAndCacheParameter = declarationStorage.createAndCacheParameter(firValueParameter, z4, conversionTypeOrigin, z8, z5);
                    irValueParameterCreateAndCacheParameter.setParent(irFunction2);
                    list.add(irValueParameterCreateAndCacheParameter);
                    conversionTypeOrigin = conversionTypeOrigin;
                    i2 = i3;
                }
            }
            irFunction2.setParameters(CollectionsKt.build(listCreateListBuilder));
        }
        z3 = true;
        if (firProperty == null || FirStatusUtilsKt.isCompanionExtension(firProperty) != z3) {
            if ((firFunction instanceof FirPropertyAccessor) || firFunction == null) {
                if (firProperty != null) {
                    receiverParameter = firProperty.getReceiverParameter();
                } else {
                    firReceiverParameter = null;
                }
                if (firReceiverParameter != null) {
                    List<IrValueParameter> list2 = listCreateListBuilder;
                    source = firReceiverParameter.getSource();
                    if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
                        classId = null;
                        i = -1;
                        endOffset = -1;
                    } else {
                        if (source != null) {
                            kind = source.getKind();
                        } else {
                            kind = null;
                        }
                        if (Intrinsics.areEqual(kind, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                            classId = null;
                            i = -1;
                            endOffset = -1;
                        } else {
                            if (source != null) {
                                kind2 = source.getKind();
                            } else {
                                kind2 = null;
                            }
                            if (Intrinsics.areEqual(kind2, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                                classId = null;
                                i = -1;
                                endOffset = -1;
                            } else {
                                if (source != null) {
                                    kind3 = source.getKind();
                                } else {
                                    kind3 = null;
                                }
                                if (Intrinsics.areEqual(kind3, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                                    classId = null;
                                    i = -1;
                                    endOffset = -1;
                                } else {
                                    classId = null;
                                    if (source == null && (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) != null) {
                                        startOffset = numStartOffsetSkippingComments.intValue();
                                    } else if (source != null) {
                                        startOffset = source.getStartOffset();
                                    } else {
                                        startOffset = -1;
                                    }
                                    i = startOffset;
                                    endOffset = source != null ? source.getEndOffset() : -1;
                                }
                            }
                        }
                    }
                    if (firFunction instanceof FirAnonymousFunction) {
                        classId2 = (FirAnonymousFunction) firFunction;
                    } else {
                        classId2 = classId;
                    }
                    if (classId2 != null || (label = classId2.getLabel()) == null || (name = label.getName()) == null) {
                        nameIdentifier = SpecialNames.THIS;
                    } else {
                        if (!Name.isValidIdentifier(name)) {
                            name = classId;
                        }
                        if (name == null) {
                            name = "$receiver";
                        }
                        nameIdentifier = Name.identifier("$this$".concat(name));
                        if (nameIdentifier == null) {
                            nameIdentifier = SpecialNames.THIS;
                        }
                    }
                    list2.add(IrElementsCreationUtilsKt.declareThisReceiverParameter(fir2IrCallableDeclarationsGenerator, irFunction2, Fir2IrTypeConverterKt.toIrType(fir2IrCallableDeclarationsGenerator, firReceiverParameter.getTypeRef(), conversionTypeOrigin), IrDeclarationOrigin.Companion.getDEFINED(), IrParameterKind.ExtensionReceiver, i, endOffset, nameIdentifier, firReceiverParameter, Fir2IrCallableDeclarationsGeneratorKt.shouldParametersBeAssignable(firFunction, fir2IrCallableDeclarationsGenerator.c)));
                } else {
                    classId = null;
                }
            } else {
                receiverParameter = firFunction.getReceiverParameter();
            }
            firReceiverParameter = receiverParameter;
            if (firReceiverParameter != null) {
                List<IrValueParameter> list3 = listCreateListBuilder;
                source = firReceiverParameter.getSource();
                if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
                    classId = null;
                    i = -1;
                    endOffset = -1;
                } else {
                    if (source != null) {
                        kind = source.getKind();
                    } else {
                        kind = null;
                    }
                    if (Intrinsics.areEqual(kind, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                        classId = null;
                        i = -1;
                        endOffset = -1;
                    } else {
                        if (source != null) {
                            kind2 = source.getKind();
                        } else {
                            kind2 = null;
                        }
                        if (Intrinsics.areEqual(kind2, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                            classId = null;
                            i = -1;
                            endOffset = -1;
                        } else {
                            if (source != null) {
                                kind3 = source.getKind();
                            } else {
                                kind3 = null;
                            }
                            if (Intrinsics.areEqual(kind3, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                                classId = null;
                                i = -1;
                                endOffset = -1;
                            } else {
                                classId = null;
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
                                i = startOffset;
                                endOffset = source != null ? source.getEndOffset() : -1;
                            }
                        }
                    }
                }
                if (firFunction instanceof FirAnonymousFunction) {
                    classId2 = (FirAnonymousFunction) firFunction;
                } else {
                    classId2 = classId;
                }
                if (classId2 != null) {
                    nameIdentifier = SpecialNames.THIS;
                } else {
                    nameIdentifier = SpecialNames.THIS;
                }
                list3.add(IrElementsCreationUtilsKt.declareThisReceiverParameter(fir2IrCallableDeclarationsGenerator, irFunction2, Fir2IrTypeConverterKt.toIrType(fir2IrCallableDeclarationsGenerator, firReceiverParameter.getTypeRef(), conversionTypeOrigin), IrDeclarationOrigin.Companion.getDEFINED(), IrParameterKind.ExtensionReceiver, i, endOffset, nameIdentifier, firReceiverParameter, Fir2IrCallableDeclarationsGeneratorKt.shouldParametersBeAssignable(firFunction, fir2IrCallableDeclarationsGenerator.c)));
            } else {
                classId = null;
            }
        } else {
            classId = null;
        }
        if (firFunction instanceof FirDefaultPropertySetter) {
            firDefaultPropertySetter = (FirDefaultPropertySetter) firFunction;
            if (firDefaultPropertySetter != null) {
                classId3 = classId;
            } else {
                classId3 = classId;
            }
            if (classId3 != null) {
                firProperty.getClass();
                returnTypeRef = firProperty.getReturnTypeRef();
            } else {
                firProperty.getClass();
                returnTypeRef = firProperty.getReturnTypeRef();
            }
            irFunction2 = irFunction;
            listCreateListBuilder.add(createDefaultSetterParameter$org_jetbrains_kotlin_fir2ir$default(fir2IrCallableDeclarationsGenerator, irFunction.getStartOffset(), irFunction.getEndOffset(), Fir2IrTypeConverterKt.toIrType(fir2IrCallableDeclarationsGenerator, returnTypeRef, ConversionTypeOrigin.SETTER), irFunction, classId3, null, false, false, 224, null));
        } else {
            firDefaultPropertySetter = (FirDefaultPropertySetter) firFunction;
            if (firDefaultPropertySetter != null) {
                classId3 = classId;
            } else {
                classId3 = classId;
            }
            if (classId3 != null) {
                firProperty.getClass();
                returnTypeRef = firProperty.getReturnTypeRef();
            } else {
                firProperty.getClass();
                returnTypeRef = firProperty.getReturnTypeRef();
            }
            irFunction2 = irFunction;
            listCreateListBuilder.add(createDefaultSetterParameter$org_jetbrains_kotlin_fir2ir$default(fir2IrCallableDeclarationsGenerator, irFunction.getStartOffset(), irFunction.getEndOffset(), Fir2IrTypeConverterKt.toIrType(fir2IrCallableDeclarationsGenerator, returnTypeRef, ConversionTypeOrigin.SETTER), irFunction, classId3, null, false, false, 224, null));
        }
        irFunction2.setParameters(CollectionsKt.build(listCreateListBuilder));
    }

    public static /* synthetic */ void declareParameters$default(Fir2IrCallableDeclarationsGenerator fir2IrCallableDeclarationsGenerator, IrFunction irFunction, FirFunction firFunction, IrDeclarationParent irDeclarationParent, IrType irType, boolean z, boolean z2, FirProperty firProperty, int i, Object obj) {
        fir2IrCallableDeclarationsGenerator.declareParameters(irFunction, firFunction, irDeclarationParent, irType, z, z2, (i & 32) != 0 ? null : firProperty);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirExpression getEffectivePropertyInitializer(FirProperty property, boolean resolveIfNeeded) {
        FirExpression initializer;
        FirBackingField backingField = property.getBackingField();
        if (backingField == null || (initializer = backingField.getInitializer()) == null) {
            initializer = property.getInitializer();
        }
        if (!resolveIfNeeded || !(initializer instanceof FirLiteralExpression)) {
            return initializer;
        }
        FirLazyDeclarationResolverKt.lazyResolveToPhase(property, FirResolvePhase.BODY_RESOLVE);
        return getEffectivePropertyInitializer(property, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Visibility getFieldVisibility(FirProperty firProperty) {
        Visibility visibility;
        if (DeclarationAttributesKt.getHasExplicitBackingField(firProperty)) {
            FirBackingField backingField = firProperty.getBackingField();
            return (backingField == null || (visibility = backingField.getStatus().getVisibility()) == null) ? firProperty.getStatus().getVisibility() : visibility;
        }
        if (firProperty.getStatus().isConst()) {
            return firProperty.getStatus().getVisibility();
        }
        Visibility visibilitySpecialBackingFieldVisibility = getExtensions().specialBackingFieldVisibility(firProperty, getSession());
        if (visibilitySpecialBackingFieldVisibility != null) {
            return visibilitySpecialBackingFieldVisibility;
        }
        return Intrinsics.areEqual(firProperty.getOrigin(), FirDeclarationOrigin.ScriptCustomization.ResultProperty.INSTANCE) ? firProperty.getStatus().getVisibility() : Visibilities.Private.INSTANCE;
    }

    private final boolean isDeclaredInFilesBeingCompiled(FirAnnotationContainer firAnnotationContainer) {
        Set<FirFile> filesBeingCompiled = getFilesBeingCompiled();
        if (filesBeingCompiled == null || !(firAnnotationContainer instanceof FirDeclaration)) {
            return false;
        }
        FirDeclaration firDeclaration = (FirDeclaration) firAnnotationContainer;
        return !CollectionsKt.contains(filesBeingCompiled, FirProviderUtilsKt.getContainingFile(FirProviderKt.getFirProvider(firDeclaration.getModuleData().getSession()), firDeclaration.getSymbol()));
    }

    private final boolean isExternal(IrDeclarationOrigin irDeclarationOrigin) {
        IrDeclarationOrigin.Companion companion = IrDeclarationOrigin.Companion;
        return Intrinsics.areEqual(irDeclarationOrigin, companion.getIR_EXTERNAL_JAVA_DECLARATION_STUB()) || Intrinsics.areEqual(irDeclarationOrigin, companion.getIR_EXTERNAL_DECLARATION_STUB());
    }

    public final void addContextParametersTo(List<? extends FirValueParameter> contextParameters, IrFunction parent, List<IrValueParameter> result) {
        contextParameters.getClass();
        parent.getClass();
        result.getClass();
        List<IrValueParameter> list = result;
        int i = 0;
        for (Object obj : contextParameters) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            IrValueParameter irValueParameterCreateAndCacheParameter$default = Fir2IrDeclarationStorage.createAndCacheParameter$default(getDeclarationStorage(), (FirValueParameter) obj, false, null, false, false, 30, null);
            irValueParameterCreateAndCacheParameter$default.setParent(parent);
            list.add(irValueParameterCreateAndCacheParameter$default);
            i = i2;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:52:0x00ae  */
    public final IrField createBackingField$org_jetbrains_kotlin_fir2ir(IrProperty irProperty, FirProperty firProperty, IrDeclarationOrigin origin, IrFieldSymbol symbol, DescriptorVisibility visibility, Name name, boolean isFinal, FirExpression firInitializerExpression, IrType type) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrType irType$default;
        TokenSet tokenSet;
        int i;
        int i2;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        irProperty.getClass();
        firProperty.getClass();
        origin.getClass();
        symbol.getClass();
        visibility.getClass();
        name.getClass();
        if (type == null) {
            try {
                firInitializerExpression.getClass();
                irType$default = Fir2IrTypeConverterKt.toIrType$default(this, FirTypeUtilsKt.getResolvedType(firInitializerExpression), (ConversionTypeOrigin) null, 2, (Object) null);
            } catch (Throwable th) {
                String str = "Exception was thrown during transformation of " + firProperty.getClass();
                PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(th);
                KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(str, th);
                ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", firProperty);
                kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                throw kotlinIllegalArgumentExceptionWithAttachments;
            }
        } else {
            irType$default = type;
        }
        FirElement delegate = firProperty.getDelegate();
        if (delegate == null && (delegate = firProperty.getBackingField()) == null) {
            delegate = firProperty;
        }
        if (delegate instanceof FirNamedFunction) {
            tokenSet = OffsetUtilsKt.FUNCTION_KEYWORD_TOKENS;
        } else if (delegate instanceof FirConstructor) {
            tokenSet = OffsetUtilsKt.CONSTRUCTOR_KEYWORD_TOKENS;
        } else {
            tokenSet = delegate instanceof FirVariable ? KtTokens.VAL_VAR : null;
        }
        KtSourceElement source = delegate.getSource();
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
                        if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, tokenSet)) == null) {
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
        IrField irFieldCreateField = IrFactoryImpl.INSTANCE.createField(i, i2, origin, name, visibility, symbol, irType$default, isFinal && !Intrinsics.areEqual(DeclarationAttributesKt.isReplSnippetDeclaration(firProperty), Boolean.TRUE), firProperty.getStatus().isStatic() || !((irProperty.getParent() instanceof IrClass) || (irProperty.getParent() instanceof IrScript)), firProperty.getStatus().isExternal() || irProperty.isExternal());
        irFieldCreateField.setCorrespondingPropertySymbol(irProperty.getSymbol());
        irFieldCreateField.setMetadata(new FirMetadataSource.Property(firProperty));
        convertAnnotationsForNonDeclaredMembers(irFieldCreateField, firProperty, origin);
        return irFieldCreateField;
    }

    public final IrValueParameter createDefaultSetterParameter$org_jetbrains_kotlin_fir2ir(int startOffset, int endOffset, IrType type, IrFunction parent, FirValueParameter firValueParameter, Name name, boolean isCrossinline, boolean isNoinline) {
        type.getClass();
        parent.getClass();
        IrValueParameter irValueParameterCreateValueParameter = IrFactoryImpl.INSTANCE.createValueParameter(startOffset, endOffset, IrDeclarationOrigin.Companion.getDEFINED(), IrParameterKind.Regular, name == null ? SpecialNames.IMPLICIT_SET_PARAMETER : name, type, false, new IrValueParameterSymbolImpl((ParameterDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null), (IrType) null, isCrossinline, isNoinline, false);
        irValueParameterCreateValueParameter.setParent(parent);
        if (firValueParameter != null) {
            getAnnotationGenerator().generate(irValueParameterCreateValueParameter, firValueParameter);
        }
        return irValueParameterCreateValueParameter;
    }

    public final IrExternalPackageFragment createExternalPackageFragment$org_jetbrains_kotlin_fir2ir(PackageFragmentDescriptor packageFragmentDescriptor) {
        packageFragmentDescriptor.getClass();
        return new IrExternalPackageFragmentImpl(new IrExternalPackageFragmentSymbolImpl(packageFragmentDescriptor), packageFragmentDescriptor.getFqName());
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:32:0x0067  */
    public final IrAnonymousInitializer createIrAnonymousInitializer(FirAnonymousInitializer anonymousInitializer, IrClass irParent) throws KotlinIllegalArgumentExceptionWithAttachments {
        int startOffset;
        Integer numStartOffsetSkippingComments;
        anonymousInitializer.getClass();
        irParent.getClass();
        try {
            KtSourceElement source = anonymousInitializer.getSource();
            int endOffset = -1;
            if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
                startOffset = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                    startOffset = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                        startOffset = -1;
                    } else {
                        if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                            startOffset = -1;
                        } else {
                            if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, null)) == null) {
                                startOffset = source != null ? source.getStartOffset() : -1;
                            } else {
                                startOffset = numStartOffsetSkippingComments.intValue();
                            }
                            if (source != null) {
                                endOffset = source.getEndOffset();
                            }
                        }
                    }
                }
            }
            IrAnonymousInitializer irAnonymousInitializerCreateAnonymousInitializer$default = IrFactory.createAnonymousInitializer$default(IrFactoryImpl.INSTANCE, startOffset, endOffset, IrDeclarationOrigin.Companion.getDEFINED(), new IrAnonymousInitializerSymbolImpl((ClassDescriptor) null, 1, (DefaultConstructorMarker) null), false, 16, (Object) null);
            Fir2IrCallableDeclarationsGeneratorKt.setParent(irAnonymousInitializerCreateAnonymousInitializer$default, irParent);
            Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irAnonymousInitializerCreateAnonymousInitializer$default, irParent);
            return irAnonymousInitializerCreateAnonymousInitializer$default;
        } catch (Throwable th) {
            String str = "Exception was thrown during transformation of " + anonymousInitializer.getClass();
            PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(th);
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(str, th);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", anonymousInitializer);
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:46:0x00c4  */
    public final IrConstructor createIrConstructor(FirConstructor constructor, IrClass irParent, IrConstructorSymbol symbol, IrDeclarationOrigin predefinedOrigin, boolean allowLazyDeclarationsCreation) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirConstructor firConstructor;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        constructor.getClass();
        irParent.getClass();
        symbol.getClass();
        try {
            firConstructor = constructor;
            try {
                IrDeclarationOrigin irDeclarationOriginComputeIrOrigin$default = OriginUtilsKt.computeIrOrigin$default(firConstructor, predefinedOrigin, irParent.getOrigin(), null, 4, null);
                boolean zIsPrimary = firConstructor.getIsPrimary();
                if (irParent instanceof Fir2IrLazyClass) {
                    if (allowLazyDeclarationsCreation) {
                        return getLazyDeclarationsGenerator().createIrLazyConstructor(firConstructor, symbol, irDeclarationOriginComputeIrOrigin$default, irParent);
                    }
                    throw new IllegalStateException(("Lazy constructors should be processed in Fir2IrDeclarationStorage: " + UtilsKt.render(firConstructor)).toString());
                }
                Visibility visibility = IrUtilsKt.isAnonymousObject(irParent) ? Visibilities.Public.INSTANCE : firConstructor.getStatus().getVisibility();
                TokenSet tokenSet = OffsetUtilsKt.CONSTRUCTOR_KEYWORD_TOKENS;
                KtSourceElement source = firConstructor.getSource();
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
                getClassifierStorage().preCacheTypeParameters$org_jetbrains_kotlin_fir2ir(firConstructor);
                IrMutableAnnotationContainer irMutableAnnotationContainerCreateConstructor$default = IrFactory.createConstructor$default(IrFactoryImpl.INSTANCE, i, endOffset, irDeclarationOriginComputeIrOrigin$default, SpecialNames.INIT, this.c.getVisibilityConverter().convertToDescriptorVisibility(visibility), false, firConstructor.getStatus().isExpect(), Fir2IrTypeConverterKt.toIrType$default(this, firConstructor.getReturnTypeRef(), (ConversionTypeOrigin) null, 2, (Object) null), symbol, zIsPrimary, Fir2IrCallableDeclarationsGeneratorKt.isEffectivelyExternal(constructor, irParent), (DeserializedContainerSource) null, 2048, (Object) null);
                irMutableAnnotationContainerCreateConstructor$default.setMetadata(new FirMetadataSource.Function(firConstructor));
                getAnnotationGenerator().generate(irMutableAnnotationContainerCreateConstructor$default, firConstructor);
                Fir2IrDeclarationStorage declarationStorage = getDeclarationStorage();
                declarationStorage.enterScope(symbol);
                Fir2IrCallableDeclarationsGeneratorKt.setParent(irMutableAnnotationContainerCreateConstructor$default, irParent);
                Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irMutableAnnotationContainerCreateConstructor$default, irParent);
                try {
                    declareParameters$default(this, irMutableAnnotationContainerCreateConstructor$default, firConstructor, irParent, null, false, false, null, 32, null);
                    declarationStorage.leaveScope(symbol);
                    return irMutableAnnotationContainerCreateConstructor$default;
                } catch (Throwable th) {
                    th = th;
                    firConstructor = firConstructor;
                    String str = "Exception was thrown during transformation of " + firConstructor.getClass();
                    PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(th);
                    KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(str, th);
                    ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                    FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", firConstructor);
                    kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                    throw kotlinIllegalArgumentExceptionWithAttachments;
                }
            } catch (Throwable th2) {
                th = th2;
            }
        } catch (Throwable th3) {
            th = th3;
            firConstructor = constructor;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:46:0x00b3  */
    public final IrField createIrField$org_jetbrains_kotlin_fir2ir(FirField field, IrDeclarationParent irParent, IrFieldSymbol symbol, ConeKotlinType type, IrDeclarationOrigin origin) throws KotlinIllegalArgumentExceptionWithAttachments {
        int i;
        int i2;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        field.getClass();
        symbol.getClass();
        type.getClass();
        origin.getClass();
        try {
            IrType irType$default = Fir2IrTypeConverterKt.toIrType$default(this, type, (ConversionTypeOrigin) null, 2, (Object) null);
            boolean zIsExternalParent = Fir2IrCallableDeclarationsGeneratorKt.isExternalParent(irParent);
            if ((field instanceof FirJavaField) && field.getStatus().isStatic() && ((FirJavaField) field).getIsVal() && zIsExternalParent) {
                Fir2IrLazyField fir2IrLazyFieldCreateIrLazyField = getLazyDeclarationsGenerator().createIrLazyField(field, symbol, irParent, origin, null);
                Fir2IrCallableDeclarationsGeneratorKt.setParent(fir2IrLazyFieldCreateIrLazyField, irParent);
                Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(fir2IrLazyFieldCreateIrLazyField, irParent);
                return fir2IrLazyFieldCreateIrLazyField;
            }
            TokenSet tokenSet = field != null ? KtTokens.VAL_VAR : null;
            KtSourceElement source = field.getSource();
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
                            if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, tokenSet)) == null) {
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
            IrField irFieldCreateField = IrFactoryImpl.INSTANCE.createField(i, i2, origin, field.getName(), this.c.getVisibilityConverter().convertToDescriptorVisibility(field.getStatus().getVisibility()), symbol, irType$default, field.getStatus().getModality() == Modality.FINAL, field.getStatus().isStatic(), false);
            irFieldCreateField.setMetadata(new FirMetadataSource.Field(field));
            FirCallableDeclaration firCallableDeclaration = field;
            while (true) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                }
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                }
                firCallableDeclaration = originalForSubstitutionOverrideAttr;
            }
            FirExpression initializer = ((FirField) firCallableDeclaration).getInitializer();
            if (initializer instanceof FirLiteralExpression) {
                irFieldCreateField.setInitializer(IrFactoryHelpersKt.createExpressionBody(irFieldCreateField.getFactory(), ConstantUtilsKt.toIrConst((FirLiteralExpression) initializer, irType$default)));
            }
            Fir2IrCallableDeclarationsGeneratorKt.setParent(irFieldCreateField, irParent);
            if (Intrinsics.areEqual(origin, IrDeclarationOrigin.Companion.getDELEGATE())) {
                Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irFieldCreateField, irParent);
            }
            return irFieldCreateField;
        } catch (Throwable th) {
            String str = "Exception was thrown during transformation of " + field.getClass();
            PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(th);
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(str, th);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", field);
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:142:0x020c  */
    /* JADX WARN: Code duplicated, block: B:171:0x027c A[Catch: all -> 0x0019, TryCatch #0 {all -> 0x0019, blocks: (B:3:0x0010, B:5:0x0015, B:9:0x001d, B:11:0x0024, B:15:0x0031, B:17:0x003f, B:19:0x0045, B:21:0x004b, B:27:0x0058, B:59:0x00d3, B:61:0x00df, B:63:0x00e9, B:68:0x00f9, B:70:0x00ff, B:73:0x0105, B:75:0x010b, B:77:0x0112, B:79:0x0123, B:80:0x013c, B:81:0x013d, B:82:0x0144, B:85:0x014c, B:91:0x015b, B:95:0x0169, B:97:0x0174, B:100:0x017a, B:102:0x0180, B:107:0x0198, B:109:0x019c, B:114:0x01ab, B:117:0x01bc, B:119:0x01c2, B:122:0x01cc, B:124:0x01d2, B:127:0x01dc, B:129:0x01e2, B:133:0x01ed, B:135:0x01f3, B:140:0x0202, B:143:0x020d, B:148:0x021e, B:150:0x022a, B:155:0x023a, B:159:0x024a, B:161:0x0257, B:165:0x0265, B:171:0x027c, B:177:0x0293, B:182:0x02b0, B:184:0x02b6, B:186:0x02bd, B:188:0x02f0, B:191:0x02f9, B:193:0x0303, B:197:0x0312, B:200:0x0335, B:202:0x0343, B:204:0x0353, B:210:0x0365, B:212:0x036c, B:214:0x0372, B:217:0x0379, B:208:0x035f, B:190:0x02f6, B:163:0x0261, B:137:0x01fa, B:110:0x01a1, B:112:0x01a5, B:106:0x018f, B:93:0x0165, B:88:0x0154, B:89:0x0157, B:29:0x0062, B:30:0x006a, B:33:0x0072, B:35:0x007c, B:37:0x008c, B:38:0x0093, B:40:0x0097, B:43:0x009e, B:45:0x00a4, B:47:0x00b0, B:49:0x00b6, B:50:0x00bb, B:52:0x00bf, B:55:0x00c6, B:58:0x00cf), top: B:221:0x0010 }] */
    /* JADX WARN: Code duplicated, block: B:173:0x0289  */
    /* JADX WARN: Code duplicated, block: B:175:0x028e  */
    /* JADX WARN: Code duplicated, block: B:177:0x0293 A[Catch: all -> 0x0019, TryCatch #0 {all -> 0x0019, blocks: (B:3:0x0010, B:5:0x0015, B:9:0x001d, B:11:0x0024, B:15:0x0031, B:17:0x003f, B:19:0x0045, B:21:0x004b, B:27:0x0058, B:59:0x00d3, B:61:0x00df, B:63:0x00e9, B:68:0x00f9, B:70:0x00ff, B:73:0x0105, B:75:0x010b, B:77:0x0112, B:79:0x0123, B:80:0x013c, B:81:0x013d, B:82:0x0144, B:85:0x014c, B:91:0x015b, B:95:0x0169, B:97:0x0174, B:100:0x017a, B:102:0x0180, B:107:0x0198, B:109:0x019c, B:114:0x01ab, B:117:0x01bc, B:119:0x01c2, B:122:0x01cc, B:124:0x01d2, B:127:0x01dc, B:129:0x01e2, B:133:0x01ed, B:135:0x01f3, B:140:0x0202, B:143:0x020d, B:148:0x021e, B:150:0x022a, B:155:0x023a, B:159:0x024a, B:161:0x0257, B:165:0x0265, B:171:0x027c, B:177:0x0293, B:182:0x02b0, B:184:0x02b6, B:186:0x02bd, B:188:0x02f0, B:191:0x02f9, B:193:0x0303, B:197:0x0312, B:200:0x0335, B:202:0x0343, B:204:0x0353, B:210:0x0365, B:212:0x036c, B:214:0x0372, B:217:0x0379, B:208:0x035f, B:190:0x02f6, B:163:0x0261, B:137:0x01fa, B:110:0x01a1, B:112:0x01a5, B:106:0x018f, B:93:0x0165, B:88:0x0154, B:89:0x0157, B:29:0x0062, B:30:0x006a, B:33:0x0072, B:35:0x007c, B:37:0x008c, B:38:0x0093, B:40:0x0097, B:43:0x009e, B:45:0x00a4, B:47:0x00b0, B:49:0x00b6, B:50:0x00bb, B:52:0x00bf, B:55:0x00c6, B:58:0x00cf), top: B:221:0x0010 }] */
    /* JADX WARN: Code duplicated, block: B:179:0x02a2  */
    /* JADX WARN: Code duplicated, block: B:181:0x02aa  */
    /* JADX WARN: Code duplicated, block: B:184:0x02b6 A[Catch: all -> 0x0019, TryCatch #0 {all -> 0x0019, blocks: (B:3:0x0010, B:5:0x0015, B:9:0x001d, B:11:0x0024, B:15:0x0031, B:17:0x003f, B:19:0x0045, B:21:0x004b, B:27:0x0058, B:59:0x00d3, B:61:0x00df, B:63:0x00e9, B:68:0x00f9, B:70:0x00ff, B:73:0x0105, B:75:0x010b, B:77:0x0112, B:79:0x0123, B:80:0x013c, B:81:0x013d, B:82:0x0144, B:85:0x014c, B:91:0x015b, B:95:0x0169, B:97:0x0174, B:100:0x017a, B:102:0x0180, B:107:0x0198, B:109:0x019c, B:114:0x01ab, B:117:0x01bc, B:119:0x01c2, B:122:0x01cc, B:124:0x01d2, B:127:0x01dc, B:129:0x01e2, B:133:0x01ed, B:135:0x01f3, B:140:0x0202, B:143:0x020d, B:148:0x021e, B:150:0x022a, B:155:0x023a, B:159:0x024a, B:161:0x0257, B:165:0x0265, B:171:0x027c, B:177:0x0293, B:182:0x02b0, B:184:0x02b6, B:186:0x02bd, B:188:0x02f0, B:191:0x02f9, B:193:0x0303, B:197:0x0312, B:200:0x0335, B:202:0x0343, B:204:0x0353, B:210:0x0365, B:212:0x036c, B:214:0x0372, B:217:0x0379, B:208:0x035f, B:190:0x02f6, B:163:0x0261, B:137:0x01fa, B:110:0x01a1, B:112:0x01a5, B:106:0x018f, B:93:0x0165, B:88:0x0154, B:89:0x0157, B:29:0x0062, B:30:0x006a, B:33:0x0072, B:35:0x007c, B:37:0x008c, B:38:0x0093, B:40:0x0097, B:43:0x009e, B:45:0x00a4, B:47:0x00b0, B:49:0x00b6, B:50:0x00bb, B:52:0x00bf, B:55:0x00c6, B:58:0x00cf), top: B:221:0x0010 }] */
    /* JADX WARN: Code duplicated, block: B:185:0x02bb  */
    /* JADX WARN: Code duplicated, block: B:188:0x02f0 A[Catch: all -> 0x0019, TryCatch #0 {all -> 0x0019, blocks: (B:3:0x0010, B:5:0x0015, B:9:0x001d, B:11:0x0024, B:15:0x0031, B:17:0x003f, B:19:0x0045, B:21:0x004b, B:27:0x0058, B:59:0x00d3, B:61:0x00df, B:63:0x00e9, B:68:0x00f9, B:70:0x00ff, B:73:0x0105, B:75:0x010b, B:77:0x0112, B:79:0x0123, B:80:0x013c, B:81:0x013d, B:82:0x0144, B:85:0x014c, B:91:0x015b, B:95:0x0169, B:97:0x0174, B:100:0x017a, B:102:0x0180, B:107:0x0198, B:109:0x019c, B:114:0x01ab, B:117:0x01bc, B:119:0x01c2, B:122:0x01cc, B:124:0x01d2, B:127:0x01dc, B:129:0x01e2, B:133:0x01ed, B:135:0x01f3, B:140:0x0202, B:143:0x020d, B:148:0x021e, B:150:0x022a, B:155:0x023a, B:159:0x024a, B:161:0x0257, B:165:0x0265, B:171:0x027c, B:177:0x0293, B:182:0x02b0, B:184:0x02b6, B:186:0x02bd, B:188:0x02f0, B:191:0x02f9, B:193:0x0303, B:197:0x0312, B:200:0x0335, B:202:0x0343, B:204:0x0353, B:210:0x0365, B:212:0x036c, B:214:0x0372, B:217:0x0379, B:208:0x035f, B:190:0x02f6, B:163:0x0261, B:137:0x01fa, B:110:0x01a1, B:112:0x01a5, B:106:0x018f, B:93:0x0165, B:88:0x0154, B:89:0x0157, B:29:0x0062, B:30:0x006a, B:33:0x0072, B:35:0x007c, B:37:0x008c, B:38:0x0093, B:40:0x0097, B:43:0x009e, B:45:0x00a4, B:47:0x00b0, B:49:0x00b6, B:50:0x00bb, B:52:0x00bf, B:55:0x00c6, B:58:0x00cf), top: B:221:0x0010 }] */
    /* JADX WARN: Code duplicated, block: B:190:0x02f6 A[Catch: all -> 0x0019, TryCatch #0 {all -> 0x0019, blocks: (B:3:0x0010, B:5:0x0015, B:9:0x001d, B:11:0x0024, B:15:0x0031, B:17:0x003f, B:19:0x0045, B:21:0x004b, B:27:0x0058, B:59:0x00d3, B:61:0x00df, B:63:0x00e9, B:68:0x00f9, B:70:0x00ff, B:73:0x0105, B:75:0x010b, B:77:0x0112, B:79:0x0123, B:80:0x013c, B:81:0x013d, B:82:0x0144, B:85:0x014c, B:91:0x015b, B:95:0x0169, B:97:0x0174, B:100:0x017a, B:102:0x0180, B:107:0x0198, B:109:0x019c, B:114:0x01ab, B:117:0x01bc, B:119:0x01c2, B:122:0x01cc, B:124:0x01d2, B:127:0x01dc, B:129:0x01e2, B:133:0x01ed, B:135:0x01f3, B:140:0x0202, B:143:0x020d, B:148:0x021e, B:150:0x022a, B:155:0x023a, B:159:0x024a, B:161:0x0257, B:165:0x0265, B:171:0x027c, B:177:0x0293, B:182:0x02b0, B:184:0x02b6, B:186:0x02bd, B:188:0x02f0, B:191:0x02f9, B:193:0x0303, B:197:0x0312, B:200:0x0335, B:202:0x0343, B:204:0x0353, B:210:0x0365, B:212:0x036c, B:214:0x0372, B:217:0x0379, B:208:0x035f, B:190:0x02f6, B:163:0x0261, B:137:0x01fa, B:110:0x01a1, B:112:0x01a5, B:106:0x018f, B:93:0x0165, B:88:0x0154, B:89:0x0157, B:29:0x0062, B:30:0x006a, B:33:0x0072, B:35:0x007c, B:37:0x008c, B:38:0x0093, B:40:0x0097, B:43:0x009e, B:45:0x00a4, B:47:0x00b0, B:49:0x00b6, B:50:0x00bb, B:52:0x00bf, B:55:0x00c6, B:58:0x00cf), top: B:221:0x0010 }] */
    /* JADX WARN: Code duplicated, block: B:193:0x0303 A[Catch: all -> 0x0019, TryCatch #0 {all -> 0x0019, blocks: (B:3:0x0010, B:5:0x0015, B:9:0x001d, B:11:0x0024, B:15:0x0031, B:17:0x003f, B:19:0x0045, B:21:0x004b, B:27:0x0058, B:59:0x00d3, B:61:0x00df, B:63:0x00e9, B:68:0x00f9, B:70:0x00ff, B:73:0x0105, B:75:0x010b, B:77:0x0112, B:79:0x0123, B:80:0x013c, B:81:0x013d, B:82:0x0144, B:85:0x014c, B:91:0x015b, B:95:0x0169, B:97:0x0174, B:100:0x017a, B:102:0x0180, B:107:0x0198, B:109:0x019c, B:114:0x01ab, B:117:0x01bc, B:119:0x01c2, B:122:0x01cc, B:124:0x01d2, B:127:0x01dc, B:129:0x01e2, B:133:0x01ed, B:135:0x01f3, B:140:0x0202, B:143:0x020d, B:148:0x021e, B:150:0x022a, B:155:0x023a, B:159:0x024a, B:161:0x0257, B:165:0x0265, B:171:0x027c, B:177:0x0293, B:182:0x02b0, B:184:0x02b6, B:186:0x02bd, B:188:0x02f0, B:191:0x02f9, B:193:0x0303, B:197:0x0312, B:200:0x0335, B:202:0x0343, B:204:0x0353, B:210:0x0365, B:212:0x036c, B:214:0x0372, B:217:0x0379, B:208:0x035f, B:190:0x02f6, B:163:0x0261, B:137:0x01fa, B:110:0x01a1, B:112:0x01a5, B:106:0x018f, B:93:0x0165, B:88:0x0154, B:89:0x0157, B:29:0x0062, B:30:0x006a, B:33:0x0072, B:35:0x007c, B:37:0x008c, B:38:0x0093, B:40:0x0097, B:43:0x009e, B:45:0x00a4, B:47:0x00b0, B:49:0x00b6, B:50:0x00bb, B:52:0x00bf, B:55:0x00c6, B:58:0x00cf), top: B:221:0x0010 }] */
    /* JADX WARN: Code duplicated, block: B:196:0x0310  */
    /* JADX WARN: Code duplicated, block: B:199:0x0333 A[ADDED_TO_REGION] */
    /* JADX WARN: Code duplicated, block: B:200:0x0335 A[Catch: all -> 0x0019, TryCatch #0 {all -> 0x0019, blocks: (B:3:0x0010, B:5:0x0015, B:9:0x001d, B:11:0x0024, B:15:0x0031, B:17:0x003f, B:19:0x0045, B:21:0x004b, B:27:0x0058, B:59:0x00d3, B:61:0x00df, B:63:0x00e9, B:68:0x00f9, B:70:0x00ff, B:73:0x0105, B:75:0x010b, B:77:0x0112, B:79:0x0123, B:80:0x013c, B:81:0x013d, B:82:0x0144, B:85:0x014c, B:91:0x015b, B:95:0x0169, B:97:0x0174, B:100:0x017a, B:102:0x0180, B:107:0x0198, B:109:0x019c, B:114:0x01ab, B:117:0x01bc, B:119:0x01c2, B:122:0x01cc, B:124:0x01d2, B:127:0x01dc, B:129:0x01e2, B:133:0x01ed, B:135:0x01f3, B:140:0x0202, B:143:0x020d, B:148:0x021e, B:150:0x022a, B:155:0x023a, B:159:0x024a, B:161:0x0257, B:165:0x0265, B:171:0x027c, B:177:0x0293, B:182:0x02b0, B:184:0x02b6, B:186:0x02bd, B:188:0x02f0, B:191:0x02f9, B:193:0x0303, B:197:0x0312, B:200:0x0335, B:202:0x0343, B:204:0x0353, B:210:0x0365, B:212:0x036c, B:214:0x0372, B:217:0x0379, B:208:0x035f, B:190:0x02f6, B:163:0x0261, B:137:0x01fa, B:110:0x01a1, B:112:0x01a5, B:106:0x018f, B:93:0x0165, B:88:0x0154, B:89:0x0157, B:29:0x0062, B:30:0x006a, B:33:0x0072, B:35:0x007c, B:37:0x008c, B:38:0x0093, B:40:0x0097, B:43:0x009e, B:45:0x00a4, B:47:0x00b0, B:49:0x00b6, B:50:0x00bb, B:52:0x00bf, B:55:0x00c6, B:58:0x00cf), top: B:221:0x0010 }] */
    /* JADX WARN: Code duplicated, block: B:204:0x0353 A[Catch: all -> 0x0019, TryCatch #0 {all -> 0x0019, blocks: (B:3:0x0010, B:5:0x0015, B:9:0x001d, B:11:0x0024, B:15:0x0031, B:17:0x003f, B:19:0x0045, B:21:0x004b, B:27:0x0058, B:59:0x00d3, B:61:0x00df, B:63:0x00e9, B:68:0x00f9, B:70:0x00ff, B:73:0x0105, B:75:0x010b, B:77:0x0112, B:79:0x0123, B:80:0x013c, B:81:0x013d, B:82:0x0144, B:85:0x014c, B:91:0x015b, B:95:0x0169, B:97:0x0174, B:100:0x017a, B:102:0x0180, B:107:0x0198, B:109:0x019c, B:114:0x01ab, B:117:0x01bc, B:119:0x01c2, B:122:0x01cc, B:124:0x01d2, B:127:0x01dc, B:129:0x01e2, B:133:0x01ed, B:135:0x01f3, B:140:0x0202, B:143:0x020d, B:148:0x021e, B:150:0x022a, B:155:0x023a, B:159:0x024a, B:161:0x0257, B:165:0x0265, B:171:0x027c, B:177:0x0293, B:182:0x02b0, B:184:0x02b6, B:186:0x02bd, B:188:0x02f0, B:191:0x02f9, B:193:0x0303, B:197:0x0312, B:200:0x0335, B:202:0x0343, B:204:0x0353, B:210:0x0365, B:212:0x036c, B:214:0x0372, B:217:0x0379, B:208:0x035f, B:190:0x02f6, B:163:0x0261, B:137:0x01fa, B:110:0x01a1, B:112:0x01a5, B:106:0x018f, B:93:0x0165, B:88:0x0154, B:89:0x0157, B:29:0x0062, B:30:0x006a, B:33:0x0072, B:35:0x007c, B:37:0x008c, B:38:0x0093, B:40:0x0097, B:43:0x009e, B:45:0x00a4, B:47:0x00b0, B:49:0x00b6, B:50:0x00bb, B:52:0x00bf, B:55:0x00c6, B:58:0x00cf), top: B:221:0x0010 }] */
    /* JADX WARN: Code duplicated, block: B:208:0x035f A[Catch: all -> 0x0019, TryCatch #0 {all -> 0x0019, blocks: (B:3:0x0010, B:5:0x0015, B:9:0x001d, B:11:0x0024, B:15:0x0031, B:17:0x003f, B:19:0x0045, B:21:0x004b, B:27:0x0058, B:59:0x00d3, B:61:0x00df, B:63:0x00e9, B:68:0x00f9, B:70:0x00ff, B:73:0x0105, B:75:0x010b, B:77:0x0112, B:79:0x0123, B:80:0x013c, B:81:0x013d, B:82:0x0144, B:85:0x014c, B:91:0x015b, B:95:0x0169, B:97:0x0174, B:100:0x017a, B:102:0x0180, B:107:0x0198, B:109:0x019c, B:114:0x01ab, B:117:0x01bc, B:119:0x01c2, B:122:0x01cc, B:124:0x01d2, B:127:0x01dc, B:129:0x01e2, B:133:0x01ed, B:135:0x01f3, B:140:0x0202, B:143:0x020d, B:148:0x021e, B:150:0x022a, B:155:0x023a, B:159:0x024a, B:161:0x0257, B:165:0x0265, B:171:0x027c, B:177:0x0293, B:182:0x02b0, B:184:0x02b6, B:186:0x02bd, B:188:0x02f0, B:191:0x02f9, B:193:0x0303, B:197:0x0312, B:200:0x0335, B:202:0x0343, B:204:0x0353, B:210:0x0365, B:212:0x036c, B:214:0x0372, B:217:0x0379, B:208:0x035f, B:190:0x02f6, B:163:0x0261, B:137:0x01fa, B:110:0x01a1, B:112:0x01a5, B:106:0x018f, B:93:0x0165, B:88:0x0154, B:89:0x0157, B:29:0x0062, B:30:0x006a, B:33:0x0072, B:35:0x007c, B:37:0x008c, B:38:0x0093, B:40:0x0097, B:43:0x009e, B:45:0x00a4, B:47:0x00b0, B:49:0x00b6, B:50:0x00bb, B:52:0x00bf, B:55:0x00c6, B:58:0x00cf), top: B:221:0x0010 }] */
    /* JADX WARN: Code duplicated, block: B:210:0x0365 A[Catch: all -> 0x0019, TryCatch #0 {all -> 0x0019, blocks: (B:3:0x0010, B:5:0x0015, B:9:0x001d, B:11:0x0024, B:15:0x0031, B:17:0x003f, B:19:0x0045, B:21:0x004b, B:27:0x0058, B:59:0x00d3, B:61:0x00df, B:63:0x00e9, B:68:0x00f9, B:70:0x00ff, B:73:0x0105, B:75:0x010b, B:77:0x0112, B:79:0x0123, B:80:0x013c, B:81:0x013d, B:82:0x0144, B:85:0x014c, B:91:0x015b, B:95:0x0169, B:97:0x0174, B:100:0x017a, B:102:0x0180, B:107:0x0198, B:109:0x019c, B:114:0x01ab, B:117:0x01bc, B:119:0x01c2, B:122:0x01cc, B:124:0x01d2, B:127:0x01dc, B:129:0x01e2, B:133:0x01ed, B:135:0x01f3, B:140:0x0202, B:143:0x020d, B:148:0x021e, B:150:0x022a, B:155:0x023a, B:159:0x024a, B:161:0x0257, B:165:0x0265, B:171:0x027c, B:177:0x0293, B:182:0x02b0, B:184:0x02b6, B:186:0x02bd, B:188:0x02f0, B:191:0x02f9, B:193:0x0303, B:197:0x0312, B:200:0x0335, B:202:0x0343, B:204:0x0353, B:210:0x0365, B:212:0x036c, B:214:0x0372, B:217:0x0379, B:208:0x035f, B:190:0x02f6, B:163:0x0261, B:137:0x01fa, B:110:0x01a1, B:112:0x01a5, B:106:0x018f, B:93:0x0165, B:88:0x0154, B:89:0x0157, B:29:0x0062, B:30:0x006a, B:33:0x0072, B:35:0x007c, B:37:0x008c, B:38:0x0093, B:40:0x0097, B:43:0x009e, B:45:0x00a4, B:47:0x00b0, B:49:0x00b6, B:50:0x00bb, B:52:0x00bf, B:55:0x00c6, B:58:0x00cf), top: B:221:0x0010 }] */
    /* JADX WARN: Code duplicated, block: B:211:0x036a  */
    /* JADX WARN: Code duplicated, block: B:214:0x0372 A[Catch: all -> 0x0019, TryCatch #0 {all -> 0x0019, blocks: (B:3:0x0010, B:5:0x0015, B:9:0x001d, B:11:0x0024, B:15:0x0031, B:17:0x003f, B:19:0x0045, B:21:0x004b, B:27:0x0058, B:59:0x00d3, B:61:0x00df, B:63:0x00e9, B:68:0x00f9, B:70:0x00ff, B:73:0x0105, B:75:0x010b, B:77:0x0112, B:79:0x0123, B:80:0x013c, B:81:0x013d, B:82:0x0144, B:85:0x014c, B:91:0x015b, B:95:0x0169, B:97:0x0174, B:100:0x017a, B:102:0x0180, B:107:0x0198, B:109:0x019c, B:114:0x01ab, B:117:0x01bc, B:119:0x01c2, B:122:0x01cc, B:124:0x01d2, B:127:0x01dc, B:129:0x01e2, B:133:0x01ed, B:135:0x01f3, B:140:0x0202, B:143:0x020d, B:148:0x021e, B:150:0x022a, B:155:0x023a, B:159:0x024a, B:161:0x0257, B:165:0x0265, B:171:0x027c, B:177:0x0293, B:182:0x02b0, B:184:0x02b6, B:186:0x02bd, B:188:0x02f0, B:191:0x02f9, B:193:0x0303, B:197:0x0312, B:200:0x0335, B:202:0x0343, B:204:0x0353, B:210:0x0365, B:212:0x036c, B:214:0x0372, B:217:0x0379, B:208:0x035f, B:190:0x02f6, B:163:0x0261, B:137:0x01fa, B:110:0x01a1, B:112:0x01a5, B:106:0x018f, B:93:0x0165, B:88:0x0154, B:89:0x0157, B:29:0x0062, B:30:0x006a, B:33:0x0072, B:35:0x007c, B:37:0x008c, B:38:0x0093, B:40:0x0097, B:43:0x009e, B:45:0x00a4, B:47:0x00b0, B:49:0x00b6, B:50:0x00bb, B:52:0x00bf, B:55:0x00c6, B:58:0x00cf), top: B:221:0x0010 }] */
    /* JADX WARN: Code duplicated, block: B:215:0x0375  */
    /* JADX WARN: Code duplicated, block: B:217:0x0379 A[Catch: all -> 0x0019, TRY_LEAVE, TryCatch #0 {all -> 0x0019, blocks: (B:3:0x0010, B:5:0x0015, B:9:0x001d, B:11:0x0024, B:15:0x0031, B:17:0x003f, B:19:0x0045, B:21:0x004b, B:27:0x0058, B:59:0x00d3, B:61:0x00df, B:63:0x00e9, B:68:0x00f9, B:70:0x00ff, B:73:0x0105, B:75:0x010b, B:77:0x0112, B:79:0x0123, B:80:0x013c, B:81:0x013d, B:82:0x0144, B:85:0x014c, B:91:0x015b, B:95:0x0169, B:97:0x0174, B:100:0x017a, B:102:0x0180, B:107:0x0198, B:109:0x019c, B:114:0x01ab, B:117:0x01bc, B:119:0x01c2, B:122:0x01cc, B:124:0x01d2, B:127:0x01dc, B:129:0x01e2, B:133:0x01ed, B:135:0x01f3, B:140:0x0202, B:143:0x020d, B:148:0x021e, B:150:0x022a, B:155:0x023a, B:159:0x024a, B:161:0x0257, B:165:0x0265, B:171:0x027c, B:177:0x0293, B:182:0x02b0, B:184:0x02b6, B:186:0x02bd, B:188:0x02f0, B:191:0x02f9, B:193:0x0303, B:197:0x0312, B:200:0x0335, B:202:0x0343, B:204:0x0353, B:210:0x0365, B:212:0x036c, B:214:0x0372, B:217:0x0379, B:208:0x035f, B:190:0x02f6, B:163:0x0261, B:137:0x01fa, B:110:0x01a1, B:112:0x01a5, B:106:0x018f, B:93:0x0165, B:88:0x0154, B:89:0x0157, B:29:0x0062, B:30:0x006a, B:33:0x0072, B:35:0x007c, B:37:0x008c, B:38:0x0093, B:40:0x0097, B:43:0x009e, B:45:0x00a4, B:47:0x00b0, B:49:0x00b6, B:50:0x00bb, B:52:0x00bf, B:55:0x00c6, B:58:0x00cf), top: B:221:0x0010 }] */
    public final IrSimpleFunction createIrFunction(FirFunction function, IrDeclarationParent irParent, IrSimpleFunctionSymbol symbol, IrDeclarationOrigin predefinedOrigin, boolean isLocal, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag, boolean allowLazyDeclarationsCreation) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrDeclarationOrigin irDeclarationOriginComputeIrOrigin;
        Name name;
        Visibility visibility;
        boolean zIsSuspend;
        TokenSet tokenSet;
        int endOffset;
        Modality modality;
        IrType irType;
        boolean z;
        boolean z2;
        boolean z3;
        boolean z4;
        boolean z5;
        FirFunctionSymbol firFunctionSymbol;
        DeclarationSymbolMarker containerSource;
        FirNamedFunction firNamedFunction;
        FirFunctionSymbol firFunctionSymbol2;
        Visibility visibility2;
        IrSimpleFunction irSimpleFunctionCreateSimpleFunction$default;
        boolean z6;
        FirFunction fir;
        DeclarationSymbolMarker originalForSubstitutionOverrideAttr;
        DeclarationSymbolMarker symbol2;
        FirFunctionSymbol firFunctionSymbol3;
        FirFunctionSymbol firFunctionSymbol4;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        ConeKotlinType coneType;
        function.getClass();
        symbol.getClass();
        try {
            FirNamedFunction firNamedFunction2 = function instanceof FirNamedFunction ? (FirNamedFunction) function : null;
            boolean z7 = true;
            boolean z8 = (function instanceof FirAnonymousFunction) && ((FirAnonymousFunction) function).getIsLambda();
            CallableId callableId = function.getSymbol().getCallableId();
            boolean z9 = CallableIdUtilsKt.isFunctionInvoke(callableId) || CallableIdUtilsKt.isSuspendFunctionInvoke(callableId) || CallableIdUtilsKt.isKFunctionInvoke(callableId) || CallableIdUtilsKt.isKSuspendFunctionInvoke(callableId);
            if (z8) {
                irDeclarationOriginComputeIrOrigin = IrDeclarationOrigin.Companion.getLOCAL_FUNCTION_FOR_LAMBDA();
            } else if (z9) {
                irDeclarationOriginComputeIrOrigin = IrDeclarationOrigin.Companion.getFUNCTION_INTERFACE_MEMBER();
            } else if (isExternal(predefinedOrigin) || firNamedFunction2 == null || !firNamedFunction2.getStatus().isStatic() || !Fir2IrDeclarationStorage.INSTANCE.getENUM_SYNTHETIC_NAMES$org_jetbrains_kotlin_fir2ir().containsKey(firNamedFunction2.getName())) {
                IrClass irClass = irParent instanceof IrClass ? (IrClass) irParent : null;
                IrDeclarationOrigin origin = irClass != null ? irClass.getOrigin() : null;
                IrDeclarationOrigin.Companion companion = IrDeclarationOrigin.Companion;
                if (Intrinsics.areEqual(origin, companion.getIR_EXTERNAL_DECLARATION_STUB()) && DeclarationUtilsKt.isJavaOrEnhancement(function)) {
                    irDeclarationOriginComputeIrOrigin = companion.getIR_EXTERNAL_JAVA_DECLARATION_STUB();
                } else {
                    IrDeclaration irDeclaration = irParent instanceof IrDeclaration ? (IrDeclaration) irParent : null;
                    irDeclarationOriginComputeIrOrigin = OriginUtilsKt.computeIrOrigin(function, predefinedOrigin, irDeclaration != null ? irDeclaration.getOrigin() : null, fakeOverrideOwnerLookupTag);
                }
            } else {
                irDeclarationOriginComputeIrOrigin = IrDeclarationOrigin.Companion.getENUM_CLASS_SPECIAL_MEMBER();
            }
            IrDeclarationOrigin.Companion companion2 = IrDeclarationOrigin.Companion;
            if (!Intrinsics.areEqual(irDeclarationOriginComputeIrOrigin, companion2.getDELEGATED_MEMBER()) && !Intrinsics.areEqual(irDeclarationOriginComputeIrOrigin, companion2.getENUM_CLASS_SPECIAL_MEMBER()) && !Intrinsics.areEqual(irDeclarationOriginComputeIrOrigin, companion2.getFUNCTION_INTERFACE_MEMBER())) {
                z7 = false;
            }
            if (Fir2IrCallableDeclarationsGeneratorKt.isExternalParent(irParent)) {
                if (!(function instanceof FirNamedFunction)) {
                    throw new IllegalArgumentException("Failed requirement.");
                }
                if (allowLazyDeclarationsCreation) {
                    return symbol.isBound() ? symbol.getOwner() : getLazyDeclarationsGenerator().createIrLazyFunction$org_jetbrains_kotlin_fir2ir((FirNamedFunction) function, symbol, irParent, irDeclarationOriginComputeIrOrigin, z7);
                }
                throw new IllegalStateException(("Lazy functions should be processed in Fir2IrDeclarationStorage: " + UtilsKt.render(function)).toString());
            }
            IrDeclarationOrigin irDeclarationOrigin = irDeclarationOriginComputeIrOrigin;
            if (firNamedFunction2 == null || (name = firNamedFunction2.getName()) == null) {
                name = z8 ? SpecialNames.ANONYMOUS : SpecialNames.NO_NAME_PROVIDED;
            }
            if (firNamedFunction2 == null || (visibility = firNamedFunction2.getStatus().getVisibility()) == null) {
                visibility = Visibilities.Local.INSTANCE;
            }
            if (z8) {
                FirResolvedTypeRef typeRef = ((FirAnonymousFunction) function).getTypeRef();
                FirResolvedTypeRef firResolvedTypeRef = typeRef instanceof FirResolvedTypeRef ? typeRef : null;
                zIsSuspend = (firResolvedTypeRef == null || (coneType = firResolvedTypeRef.getConeType()) == null || FunctionalTypeUtilsKt.isSuspendOrKSuspendFunctionType(coneType, getSession()) != z7) ? false : true;
            } else {
                zIsSuspend = function.getStatus().isSuspend();
            }
            if (function instanceof FirNamedFunction) {
                tokenSet = OffsetUtilsKt.FUNCTION_KEYWORD_TOKENS;
            } else {
                tokenSet = function instanceof FirConstructor ? OffsetUtilsKt.CONSTRUCTOR_KEYWORD_TOKENS : null;
            }
            KtSourceElement source = function.getSource();
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
                            i = startOffset;
                            endOffset = source != null ? source.getEndOffset() : -1;
                        }
                    }
                }
            }
            getClassifierStorage().preCacheTypeParameters$org_jetbrains_kotlin_fir2ir(function);
            IrFactoryImpl irFactoryImpl = IrFactoryImpl.INSTANCE;
            if (z7) {
                i = -2;
            }
            if (z7) {
                endOffset = -2;
            }
            DescriptorVisibility descriptorVisibilityConvertToDescriptorVisibility = this.c.getVisibilityConverter().convertToDescriptorVisibility(visibility);
            boolean z10 = (firNamedFunction2 == null || firNamedFunction2.getStatus().isInline() != z7) ? false : z7;
            boolean z11 = (firNamedFunction2 == null || firNamedFunction2.getStatus().isExpect() != z7) ? false : z7;
            IrType irType$default = Fir2IrTypeConverterKt.toIrType$default(this, function.getReturnTypeRef(), (ConversionTypeOrigin) null, 2, (Object) null);
            if (firNamedFunction2 == null || (modality = firNamedFunction2.getStatus().getModality()) == null) {
                modality = Modality.FINAL;
            }
            if (firNamedFunction2 != null) {
                irType = irType$default;
                if (firNamedFunction2.getStatus().isTailRec()) {
                    z = true;
                }
                if (firNamedFunction2 != null) {
                    z2 = z;
                    if (firNamedFunction2.getStatus().isOperator()) {
                        z3 = true;
                    }
                    if (firNamedFunction2 != null) {
                        z4 = z3;
                        if (firNamedFunction2.getStatus().isInfix()) {
                            firFunctionSymbol = null;
                            z5 = true;
                        }
                        boolean zIsEffectivelyExternal = Fir2IrCallableDeclarationsGeneratorKt.isEffectivelyExternal(firNamedFunction2, irParent);
                        if (firNamedFunction2 != null) {
                            containerSource = firNamedFunction2.getContainerSource();
                        } else {
                            containerSource = firFunctionSymbol;
                        }
                        firNamedFunction = firNamedFunction2;
                        firFunctionSymbol2 = firFunctionSymbol;
                        visibility2 = visibility;
                        irSimpleFunctionCreateSimpleFunction$default = IrFactory.createSimpleFunction$default(irFactoryImpl, i, endOffset, irDeclarationOrigin, name, descriptorVisibilityConvertToDescriptorVisibility, z10, z11, irType, modality, symbol, z2, zIsSuspend, z4, z5, zIsEffectivelyExternal, containerSource, false, 65536, (Object) null);
                        irSimpleFunctionCreateSimpleFunction$default.setMetadata(new FirMetadataSource.Function(function));
                        Fir2IrDeclarationStorage declarationStorage = getDeclarationStorage();
                        declarationStorage.enterScope(symbol);
                        Fir2IrCallableDeclarationsGeneratorKt.setParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
                        if (isLocal || ClassMembersKt.containingClassLookupTag(function) != null) {
                            Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
                        }
                        IrType irTypeComputeDispatchReceiverType$org_jetbrains_kotlin_fir2ir = INSTANCE.computeDispatchReceiverType$org_jetbrains_kotlin_fir2ir(this, irSimpleFunctionCreateSimpleFunction$default, firNamedFunction, irParent);
                        if (firNamedFunction == null && firNamedFunction.getStatus().isStatic()) {
                            z6 = true;
                        } else {
                            z6 = false;
                        }
                        declareParameters$default(this, irSimpleFunctionCreateSimpleFunction$default, function, irParent, irTypeComputeDispatchReceiverType$org_jetbrains_kotlin_fir2ir, z6, false, null, 32, null);
                        convertAnnotationsForNonDeclaredMembers(irSimpleFunctionCreateSimpleFunction$default, function, irSimpleFunctionCreateSimpleFunction$default.getOrigin());
                        declarationStorage.leaveScope(symbol);
                        if (!Intrinsics.areEqual(visibility2, Visibilities.Local.INSTANCE) && CallableIdUtilsKt.isKFunctionInvoke(function.getSymbol().getCallableId())) {
                            fir = function.getSymbol().getFir();
                            if (!ClassMembersKt.isSubstitutionOverride(fir) || (fir.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) {
                                originalForSubstitutionOverrideAttr = ClassMembersKt.getOriginalForSubstitutionOverrideAttr(fir);
                            } else {
                                originalForSubstitutionOverrideAttr = firFunctionSymbol2;
                            }
                            if (originalForSubstitutionOverrideAttr != null) {
                                symbol2 = originalForSubstitutionOverrideAttr.getSymbol();
                            } else {
                                symbol2 = firFunctionSymbol2;
                            }
                            firFunctionSymbol3 = (FirFunctionSymbol) symbol2;
                            if (firFunctionSymbol3 instanceof FirNamedFunctionSymbol) {
                                firFunctionSymbol4 = (FirNamedFunctionSymbol) firFunctionSymbol3;
                            } else {
                                firFunctionSymbol4 = firFunctionSymbol2;
                            }
                            if (firFunctionSymbol4 != null) {
                                List overriddenSymbols = irSimpleFunctionCreateSimpleFunction$default.getOverriddenSymbols();
                                IrSimpleFunctionSymbol irFunctionSymbol$default = Fir2IrDeclarationStorage.getIrFunctionSymbol$default(getDeclarationStorage(), firFunctionSymbol4, null, false, 6, null);
                                irFunctionSymbol$default.getClass();
                                irSimpleFunctionCreateSimpleFunction$default.setOverriddenSymbols(CollectionsKt.plus(overriddenSymbols, irFunctionSymbol$default));
                                Unit unit = Unit.INSTANCE;
                            }
                        }
                        return irSimpleFunctionCreateSimpleFunction$default;
                    }
                    z4 = z3;
                    z5 = false;
                    firFunctionSymbol = null;
                    boolean zIsEffectivelyExternal2 = Fir2IrCallableDeclarationsGeneratorKt.isEffectivelyExternal(firNamedFunction2, irParent);
                    if (firNamedFunction2 != null) {
                        containerSource = firNamedFunction2.getContainerSource();
                    } else {
                        containerSource = firFunctionSymbol;
                    }
                    firNamedFunction = firNamedFunction2;
                    firFunctionSymbol2 = firFunctionSymbol;
                    visibility2 = visibility;
                    irSimpleFunctionCreateSimpleFunction$default = IrFactory.createSimpleFunction$default(irFactoryImpl, i, endOffset, irDeclarationOrigin, name, descriptorVisibilityConvertToDescriptorVisibility, z10, z11, irType, modality, symbol, z2, zIsSuspend, z4, z5, zIsEffectivelyExternal2, containerSource, false, 65536, (Object) null);
                    irSimpleFunctionCreateSimpleFunction$default.setMetadata(new FirMetadataSource.Function(function));
                    Fir2IrDeclarationStorage declarationStorage2 = getDeclarationStorage();
                    declarationStorage2.enterScope(symbol);
                    Fir2IrCallableDeclarationsGeneratorKt.setParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
                    if (isLocal) {
                        Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
                    } else {
                        Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
                    }
                    IrType irTypeComputeDispatchReceiverType$org_jetbrains_kotlin_fir2ir2 = INSTANCE.computeDispatchReceiverType$org_jetbrains_kotlin_fir2ir(this, irSimpleFunctionCreateSimpleFunction$default, firNamedFunction, irParent);
                    if (firNamedFunction == null) {
                        z6 = false;
                    } else {
                        z6 = false;
                    }
                    declareParameters$default(this, irSimpleFunctionCreateSimpleFunction$default, function, irParent, irTypeComputeDispatchReceiverType$org_jetbrains_kotlin_fir2ir2, z6, false, null, 32, null);
                    convertAnnotationsForNonDeclaredMembers(irSimpleFunctionCreateSimpleFunction$default, function, irSimpleFunctionCreateSimpleFunction$default.getOrigin());
                    declarationStorage2.leaveScope(symbol);
                    if (!Intrinsics.areEqual(visibility2, Visibilities.Local.INSTANCE)) {
                        fir = function.getSymbol().getFir();
                        if (ClassMembersKt.isSubstitutionOverride(fir)) {
                            originalForSubstitutionOverrideAttr = ClassMembersKt.getOriginalForSubstitutionOverrideAttr(fir);
                        } else {
                            originalForSubstitutionOverrideAttr = ClassMembersKt.getOriginalForSubstitutionOverrideAttr(fir);
                        }
                        if (originalForSubstitutionOverrideAttr != null) {
                            symbol2 = originalForSubstitutionOverrideAttr.getSymbol();
                        } else {
                            symbol2 = firFunctionSymbol2;
                        }
                        firFunctionSymbol3 = (FirFunctionSymbol) symbol2;
                        if (firFunctionSymbol3 instanceof FirNamedFunctionSymbol) {
                            firFunctionSymbol4 = (FirNamedFunctionSymbol) firFunctionSymbol3;
                        } else {
                            firFunctionSymbol4 = firFunctionSymbol2;
                        }
                        if (firFunctionSymbol4 != null) {
                            List overriddenSymbols2 = irSimpleFunctionCreateSimpleFunction$default.getOverriddenSymbols();
                            IrSimpleFunctionSymbol irFunctionSymbol$default2 = Fir2IrDeclarationStorage.getIrFunctionSymbol$default(getDeclarationStorage(), firFunctionSymbol4, null, false, 6, null);
                            irFunctionSymbol$default2.getClass();
                            irSimpleFunctionCreateSimpleFunction$default.setOverriddenSymbols(CollectionsKt.plus(overriddenSymbols2, irFunctionSymbol$default2));
                            Unit unit2 = Unit.INSTANCE;
                        }
                    }
                    return irSimpleFunctionCreateSimpleFunction$default;
                }
                z2 = z;
                z3 = false;
                if (firNamedFunction2 != null) {
                    z4 = z3;
                    if (firNamedFunction2.getStatus().isInfix()) {
                        firFunctionSymbol = null;
                        z5 = true;
                    }
                    boolean zIsEffectivelyExternal3 = Fir2IrCallableDeclarationsGeneratorKt.isEffectivelyExternal(firNamedFunction2, irParent);
                    if (firNamedFunction2 != null) {
                        containerSource = firNamedFunction2.getContainerSource();
                    } else {
                        containerSource = firFunctionSymbol;
                    }
                    firNamedFunction = firNamedFunction2;
                    firFunctionSymbol2 = firFunctionSymbol;
                    visibility2 = visibility;
                    irSimpleFunctionCreateSimpleFunction$default = IrFactory.createSimpleFunction$default(irFactoryImpl, i, endOffset, irDeclarationOrigin, name, descriptorVisibilityConvertToDescriptorVisibility, z10, z11, irType, modality, symbol, z2, zIsSuspend, z4, z5, zIsEffectivelyExternal3, containerSource, false, 65536, (Object) null);
                    irSimpleFunctionCreateSimpleFunction$default.setMetadata(new FirMetadataSource.Function(function));
                    Fir2IrDeclarationStorage declarationStorage3 = getDeclarationStorage();
                    declarationStorage3.enterScope(symbol);
                    Fir2IrCallableDeclarationsGeneratorKt.setParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
                    if (isLocal) {
                        Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
                    } else {
                        Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
                    }
                    IrType irTypeComputeDispatchReceiverType$org_jetbrains_kotlin_fir2ir3 = INSTANCE.computeDispatchReceiverType$org_jetbrains_kotlin_fir2ir(this, irSimpleFunctionCreateSimpleFunction$default, firNamedFunction, irParent);
                    if (firNamedFunction == null) {
                        z6 = false;
                    } else {
                        z6 = false;
                    }
                    declareParameters$default(this, irSimpleFunctionCreateSimpleFunction$default, function, irParent, irTypeComputeDispatchReceiverType$org_jetbrains_kotlin_fir2ir3, z6, false, null, 32, null);
                    convertAnnotationsForNonDeclaredMembers(irSimpleFunctionCreateSimpleFunction$default, function, irSimpleFunctionCreateSimpleFunction$default.getOrigin());
                    declarationStorage3.leaveScope(symbol);
                    if (!Intrinsics.areEqual(visibility2, Visibilities.Local.INSTANCE)) {
                        fir = function.getSymbol().getFir();
                        if (ClassMembersKt.isSubstitutionOverride(fir)) {
                            originalForSubstitutionOverrideAttr = ClassMembersKt.getOriginalForSubstitutionOverrideAttr(fir);
                        } else {
                            originalForSubstitutionOverrideAttr = ClassMembersKt.getOriginalForSubstitutionOverrideAttr(fir);
                        }
                        if (originalForSubstitutionOverrideAttr != null) {
                            symbol2 = originalForSubstitutionOverrideAttr.getSymbol();
                        } else {
                            symbol2 = firFunctionSymbol2;
                        }
                        firFunctionSymbol3 = (FirFunctionSymbol) symbol2;
                        if (firFunctionSymbol3 instanceof FirNamedFunctionSymbol) {
                            firFunctionSymbol4 = (FirNamedFunctionSymbol) firFunctionSymbol3;
                        } else {
                            firFunctionSymbol4 = firFunctionSymbol2;
                        }
                        if (firFunctionSymbol4 != null) {
                            List overriddenSymbols3 = irSimpleFunctionCreateSimpleFunction$default.getOverriddenSymbols();
                            IrSimpleFunctionSymbol irFunctionSymbol$default3 = Fir2IrDeclarationStorage.getIrFunctionSymbol$default(getDeclarationStorage(), firFunctionSymbol4, null, false, 6, null);
                            irFunctionSymbol$default3.getClass();
                            irSimpleFunctionCreateSimpleFunction$default.setOverriddenSymbols(CollectionsKt.plus(overriddenSymbols3, irFunctionSymbol$default3));
                            Unit unit3 = Unit.INSTANCE;
                        }
                    }
                    return irSimpleFunctionCreateSimpleFunction$default;
                }
                z4 = z3;
                z5 = false;
                firFunctionSymbol = null;
                boolean zIsEffectivelyExternal4 = Fir2IrCallableDeclarationsGeneratorKt.isEffectivelyExternal(firNamedFunction2, irParent);
                if (firNamedFunction2 != null) {
                    containerSource = firNamedFunction2.getContainerSource();
                } else {
                    containerSource = firFunctionSymbol;
                }
                firNamedFunction = firNamedFunction2;
                firFunctionSymbol2 = firFunctionSymbol;
                visibility2 = visibility;
                irSimpleFunctionCreateSimpleFunction$default = IrFactory.createSimpleFunction$default(irFactoryImpl, i, endOffset, irDeclarationOrigin, name, descriptorVisibilityConvertToDescriptorVisibility, z10, z11, irType, modality, symbol, z2, zIsSuspend, z4, z5, zIsEffectivelyExternal4, containerSource, false, 65536, (Object) null);
                irSimpleFunctionCreateSimpleFunction$default.setMetadata(new FirMetadataSource.Function(function));
                Fir2IrDeclarationStorage declarationStorage4 = getDeclarationStorage();
                declarationStorage4.enterScope(symbol);
                Fir2IrCallableDeclarationsGeneratorKt.setParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
                if (isLocal) {
                    Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
                } else {
                    Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
                }
                IrType irTypeComputeDispatchReceiverType$org_jetbrains_kotlin_fir2ir4 = INSTANCE.computeDispatchReceiverType$org_jetbrains_kotlin_fir2ir(this, irSimpleFunctionCreateSimpleFunction$default, firNamedFunction, irParent);
                if (firNamedFunction == null) {
                    z6 = false;
                } else {
                    z6 = false;
                }
                declareParameters$default(this, irSimpleFunctionCreateSimpleFunction$default, function, irParent, irTypeComputeDispatchReceiverType$org_jetbrains_kotlin_fir2ir4, z6, false, null, 32, null);
                convertAnnotationsForNonDeclaredMembers(irSimpleFunctionCreateSimpleFunction$default, function, irSimpleFunctionCreateSimpleFunction$default.getOrigin());
                declarationStorage4.leaveScope(symbol);
                if (!Intrinsics.areEqual(visibility2, Visibilities.Local.INSTANCE)) {
                    fir = function.getSymbol().getFir();
                    if (ClassMembersKt.isSubstitutionOverride(fir)) {
                        originalForSubstitutionOverrideAttr = ClassMembersKt.getOriginalForSubstitutionOverrideAttr(fir);
                    } else {
                        originalForSubstitutionOverrideAttr = ClassMembersKt.getOriginalForSubstitutionOverrideAttr(fir);
                    }
                    if (originalForSubstitutionOverrideAttr != null) {
                        symbol2 = originalForSubstitutionOverrideAttr.getSymbol();
                    } else {
                        symbol2 = firFunctionSymbol2;
                    }
                    firFunctionSymbol3 = (FirFunctionSymbol) symbol2;
                    if (firFunctionSymbol3 instanceof FirNamedFunctionSymbol) {
                        firFunctionSymbol4 = (FirNamedFunctionSymbol) firFunctionSymbol3;
                    } else {
                        firFunctionSymbol4 = firFunctionSymbol2;
                    }
                    if (firFunctionSymbol4 != null) {
                        List overriddenSymbols4 = irSimpleFunctionCreateSimpleFunction$default.getOverriddenSymbols();
                        IrSimpleFunctionSymbol irFunctionSymbol$default4 = Fir2IrDeclarationStorage.getIrFunctionSymbol$default(getDeclarationStorage(), firFunctionSymbol4, null, false, 6, null);
                        irFunctionSymbol$default4.getClass();
                        irSimpleFunctionCreateSimpleFunction$default.setOverriddenSymbols(CollectionsKt.plus(overriddenSymbols4, irFunctionSymbol$default4));
                        Unit unit4 = Unit.INSTANCE;
                    }
                }
                return irSimpleFunctionCreateSimpleFunction$default;
            }
            irType = irType$default;
            z = false;
            if (firNamedFunction2 != null) {
                z2 = z;
                if (firNamedFunction2.getStatus().isOperator()) {
                    z3 = true;
                }
                if (firNamedFunction2 != null) {
                    z4 = z3;
                    if (firNamedFunction2.getStatus().isInfix()) {
                        firFunctionSymbol = null;
                        z5 = true;
                    }
                    boolean zIsEffectivelyExternal5 = Fir2IrCallableDeclarationsGeneratorKt.isEffectivelyExternal(firNamedFunction2, irParent);
                    if (firNamedFunction2 != null) {
                        containerSource = firNamedFunction2.getContainerSource();
                    } else {
                        containerSource = firFunctionSymbol;
                    }
                    firNamedFunction = firNamedFunction2;
                    firFunctionSymbol2 = firFunctionSymbol;
                    visibility2 = visibility;
                    irSimpleFunctionCreateSimpleFunction$default = IrFactory.createSimpleFunction$default(irFactoryImpl, i, endOffset, irDeclarationOrigin, name, descriptorVisibilityConvertToDescriptorVisibility, z10, z11, irType, modality, symbol, z2, zIsSuspend, z4, z5, zIsEffectivelyExternal5, containerSource, false, 65536, (Object) null);
                    irSimpleFunctionCreateSimpleFunction$default.setMetadata(new FirMetadataSource.Function(function));
                    Fir2IrDeclarationStorage declarationStorage5 = getDeclarationStorage();
                    declarationStorage5.enterScope(symbol);
                    Fir2IrCallableDeclarationsGeneratorKt.setParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
                    if (isLocal) {
                        Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
                    } else {
                        Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
                    }
                    IrType irTypeComputeDispatchReceiverType$org_jetbrains_kotlin_fir2ir5 = INSTANCE.computeDispatchReceiverType$org_jetbrains_kotlin_fir2ir(this, irSimpleFunctionCreateSimpleFunction$default, firNamedFunction, irParent);
                    if (firNamedFunction == null) {
                        z6 = false;
                    } else {
                        z6 = false;
                    }
                    declareParameters$default(this, irSimpleFunctionCreateSimpleFunction$default, function, irParent, irTypeComputeDispatchReceiverType$org_jetbrains_kotlin_fir2ir5, z6, false, null, 32, null);
                    convertAnnotationsForNonDeclaredMembers(irSimpleFunctionCreateSimpleFunction$default, function, irSimpleFunctionCreateSimpleFunction$default.getOrigin());
                    declarationStorage5.leaveScope(symbol);
                    if (!Intrinsics.areEqual(visibility2, Visibilities.Local.INSTANCE)) {
                        fir = function.getSymbol().getFir();
                        if (ClassMembersKt.isSubstitutionOverride(fir)) {
                            originalForSubstitutionOverrideAttr = ClassMembersKt.getOriginalForSubstitutionOverrideAttr(fir);
                        } else {
                            originalForSubstitutionOverrideAttr = ClassMembersKt.getOriginalForSubstitutionOverrideAttr(fir);
                        }
                        if (originalForSubstitutionOverrideAttr != null) {
                            symbol2 = originalForSubstitutionOverrideAttr.getSymbol();
                        } else {
                            symbol2 = firFunctionSymbol2;
                        }
                        firFunctionSymbol3 = (FirFunctionSymbol) symbol2;
                        if (firFunctionSymbol3 instanceof FirNamedFunctionSymbol) {
                            firFunctionSymbol4 = (FirNamedFunctionSymbol) firFunctionSymbol3;
                        } else {
                            firFunctionSymbol4 = firFunctionSymbol2;
                        }
                        if (firFunctionSymbol4 != null) {
                            List overriddenSymbols5 = irSimpleFunctionCreateSimpleFunction$default.getOverriddenSymbols();
                            IrSimpleFunctionSymbol irFunctionSymbol$default5 = Fir2IrDeclarationStorage.getIrFunctionSymbol$default(getDeclarationStorage(), firFunctionSymbol4, null, false, 6, null);
                            irFunctionSymbol$default5.getClass();
                            irSimpleFunctionCreateSimpleFunction$default.setOverriddenSymbols(CollectionsKt.plus(overriddenSymbols5, irFunctionSymbol$default5));
                            Unit unit5 = Unit.INSTANCE;
                        }
                    }
                    return irSimpleFunctionCreateSimpleFunction$default;
                }
                z4 = z3;
                z5 = false;
                firFunctionSymbol = null;
                boolean zIsEffectivelyExternal6 = Fir2IrCallableDeclarationsGeneratorKt.isEffectivelyExternal(firNamedFunction2, irParent);
                if (firNamedFunction2 != null) {
                    containerSource = firNamedFunction2.getContainerSource();
                } else {
                    containerSource = firFunctionSymbol;
                }
                firNamedFunction = firNamedFunction2;
                firFunctionSymbol2 = firFunctionSymbol;
                visibility2 = visibility;
                irSimpleFunctionCreateSimpleFunction$default = IrFactory.createSimpleFunction$default(irFactoryImpl, i, endOffset, irDeclarationOrigin, name, descriptorVisibilityConvertToDescriptorVisibility, z10, z11, irType, modality, symbol, z2, zIsSuspend, z4, z5, zIsEffectivelyExternal6, containerSource, false, 65536, (Object) null);
                irSimpleFunctionCreateSimpleFunction$default.setMetadata(new FirMetadataSource.Function(function));
                Fir2IrDeclarationStorage declarationStorage6 = getDeclarationStorage();
                declarationStorage6.enterScope(symbol);
                Fir2IrCallableDeclarationsGeneratorKt.setParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
                if (isLocal) {
                    Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
                } else {
                    Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
                }
                IrType irTypeComputeDispatchReceiverType$org_jetbrains_kotlin_fir2ir6 = INSTANCE.computeDispatchReceiverType$org_jetbrains_kotlin_fir2ir(this, irSimpleFunctionCreateSimpleFunction$default, firNamedFunction, irParent);
                if (firNamedFunction == null) {
                    z6 = false;
                } else {
                    z6 = false;
                }
                declareParameters$default(this, irSimpleFunctionCreateSimpleFunction$default, function, irParent, irTypeComputeDispatchReceiverType$org_jetbrains_kotlin_fir2ir6, z6, false, null, 32, null);
                convertAnnotationsForNonDeclaredMembers(irSimpleFunctionCreateSimpleFunction$default, function, irSimpleFunctionCreateSimpleFunction$default.getOrigin());
                declarationStorage6.leaveScope(symbol);
                if (!Intrinsics.areEqual(visibility2, Visibilities.Local.INSTANCE)) {
                    fir = function.getSymbol().getFir();
                    if (ClassMembersKt.isSubstitutionOverride(fir)) {
                        originalForSubstitutionOverrideAttr = ClassMembersKt.getOriginalForSubstitutionOverrideAttr(fir);
                    } else {
                        originalForSubstitutionOverrideAttr = ClassMembersKt.getOriginalForSubstitutionOverrideAttr(fir);
                    }
                    if (originalForSubstitutionOverrideAttr != null) {
                        symbol2 = originalForSubstitutionOverrideAttr.getSymbol();
                    } else {
                        symbol2 = firFunctionSymbol2;
                    }
                    firFunctionSymbol3 = (FirFunctionSymbol) symbol2;
                    if (firFunctionSymbol3 instanceof FirNamedFunctionSymbol) {
                        firFunctionSymbol4 = (FirNamedFunctionSymbol) firFunctionSymbol3;
                    } else {
                        firFunctionSymbol4 = firFunctionSymbol2;
                    }
                    if (firFunctionSymbol4 != null) {
                        List overriddenSymbols6 = irSimpleFunctionCreateSimpleFunction$default.getOverriddenSymbols();
                        IrSimpleFunctionSymbol irFunctionSymbol$default6 = Fir2IrDeclarationStorage.getIrFunctionSymbol$default(getDeclarationStorage(), firFunctionSymbol4, null, false, 6, null);
                        irFunctionSymbol$default6.getClass();
                        irSimpleFunctionCreateSimpleFunction$default.setOverriddenSymbols(CollectionsKt.plus(overriddenSymbols6, irFunctionSymbol$default6));
                        Unit unit6 = Unit.INSTANCE;
                    }
                }
                return irSimpleFunctionCreateSimpleFunction$default;
            }
            z2 = z;
            z3 = false;
            if (firNamedFunction2 != null) {
                z4 = z3;
                if (firNamedFunction2.getStatus().isInfix()) {
                    firFunctionSymbol = null;
                    z5 = true;
                }
                boolean zIsEffectivelyExternal7 = Fir2IrCallableDeclarationsGeneratorKt.isEffectivelyExternal(firNamedFunction2, irParent);
                if (firNamedFunction2 != null) {
                    containerSource = firNamedFunction2.getContainerSource();
                } else {
                    containerSource = firFunctionSymbol;
                }
                firNamedFunction = firNamedFunction2;
                firFunctionSymbol2 = firFunctionSymbol;
                visibility2 = visibility;
                irSimpleFunctionCreateSimpleFunction$default = IrFactory.createSimpleFunction$default(irFactoryImpl, i, endOffset, irDeclarationOrigin, name, descriptorVisibilityConvertToDescriptorVisibility, z10, z11, irType, modality, symbol, z2, zIsSuspend, z4, z5, zIsEffectivelyExternal7, containerSource, false, 65536, (Object) null);
                irSimpleFunctionCreateSimpleFunction$default.setMetadata(new FirMetadataSource.Function(function));
                Fir2IrDeclarationStorage declarationStorage7 = getDeclarationStorage();
                declarationStorage7.enterScope(symbol);
                Fir2IrCallableDeclarationsGeneratorKt.setParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
                if (isLocal) {
                    Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
                } else {
                    Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
                }
                IrType irTypeComputeDispatchReceiverType$org_jetbrains_kotlin_fir2ir7 = INSTANCE.computeDispatchReceiverType$org_jetbrains_kotlin_fir2ir(this, irSimpleFunctionCreateSimpleFunction$default, firNamedFunction, irParent);
                if (firNamedFunction == null) {
                    z6 = false;
                } else {
                    z6 = false;
                }
                declareParameters$default(this, irSimpleFunctionCreateSimpleFunction$default, function, irParent, irTypeComputeDispatchReceiverType$org_jetbrains_kotlin_fir2ir7, z6, false, null, 32, null);
                convertAnnotationsForNonDeclaredMembers(irSimpleFunctionCreateSimpleFunction$default, function, irSimpleFunctionCreateSimpleFunction$default.getOrigin());
                declarationStorage7.leaveScope(symbol);
                if (!Intrinsics.areEqual(visibility2, Visibilities.Local.INSTANCE)) {
                    fir = function.getSymbol().getFir();
                    if (ClassMembersKt.isSubstitutionOverride(fir)) {
                        originalForSubstitutionOverrideAttr = ClassMembersKt.getOriginalForSubstitutionOverrideAttr(fir);
                    } else {
                        originalForSubstitutionOverrideAttr = ClassMembersKt.getOriginalForSubstitutionOverrideAttr(fir);
                    }
                    if (originalForSubstitutionOverrideAttr != null) {
                        symbol2 = originalForSubstitutionOverrideAttr.getSymbol();
                    } else {
                        symbol2 = firFunctionSymbol2;
                    }
                    firFunctionSymbol3 = (FirFunctionSymbol) symbol2;
                    if (firFunctionSymbol3 instanceof FirNamedFunctionSymbol) {
                        firFunctionSymbol4 = (FirNamedFunctionSymbol) firFunctionSymbol3;
                    } else {
                        firFunctionSymbol4 = firFunctionSymbol2;
                    }
                    if (firFunctionSymbol4 != null) {
                        List overriddenSymbols7 = irSimpleFunctionCreateSimpleFunction$default.getOverriddenSymbols();
                        IrSimpleFunctionSymbol irFunctionSymbol$default7 = Fir2IrDeclarationStorage.getIrFunctionSymbol$default(getDeclarationStorage(), firFunctionSymbol4, null, false, 6, null);
                        irFunctionSymbol$default7.getClass();
                        irSimpleFunctionCreateSimpleFunction$default.setOverriddenSymbols(CollectionsKt.plus(overriddenSymbols7, irFunctionSymbol$default7));
                        Unit unit7 = Unit.INSTANCE;
                    }
                }
                return irSimpleFunctionCreateSimpleFunction$default;
            }
            z4 = z3;
            z5 = false;
            firFunctionSymbol = null;
            boolean zIsEffectivelyExternal8 = Fir2IrCallableDeclarationsGeneratorKt.isEffectivelyExternal(firNamedFunction2, irParent);
            if (firNamedFunction2 != null) {
                containerSource = firNamedFunction2.getContainerSource();
            } else {
                containerSource = firFunctionSymbol;
            }
            firNamedFunction = firNamedFunction2;
            firFunctionSymbol2 = firFunctionSymbol;
            visibility2 = visibility;
            irSimpleFunctionCreateSimpleFunction$default = IrFactory.createSimpleFunction$default(irFactoryImpl, i, endOffset, irDeclarationOrigin, name, descriptorVisibilityConvertToDescriptorVisibility, z10, z11, irType, modality, symbol, z2, zIsSuspend, z4, z5, zIsEffectivelyExternal8, containerSource, false, 65536, (Object) null);
            irSimpleFunctionCreateSimpleFunction$default.setMetadata(new FirMetadataSource.Function(function));
            Fir2IrDeclarationStorage declarationStorage8 = getDeclarationStorage();
            declarationStorage8.enterScope(symbol);
            Fir2IrCallableDeclarationsGeneratorKt.setParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
            if (isLocal) {
                Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
            } else {
                Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irSimpleFunctionCreateSimpleFunction$default, irParent);
            }
            IrType irTypeComputeDispatchReceiverType$org_jetbrains_kotlin_fir2ir8 = INSTANCE.computeDispatchReceiverType$org_jetbrains_kotlin_fir2ir(this, irSimpleFunctionCreateSimpleFunction$default, firNamedFunction, irParent);
            if (firNamedFunction == null) {
                z6 = false;
            } else {
                z6 = false;
            }
            declareParameters$default(this, irSimpleFunctionCreateSimpleFunction$default, function, irParent, irTypeComputeDispatchReceiverType$org_jetbrains_kotlin_fir2ir8, z6, false, null, 32, null);
            convertAnnotationsForNonDeclaredMembers(irSimpleFunctionCreateSimpleFunction$default, function, irSimpleFunctionCreateSimpleFunction$default.getOrigin());
            declarationStorage8.leaveScope(symbol);
            if (!Intrinsics.areEqual(visibility2, Visibilities.Local.INSTANCE)) {
                fir = function.getSymbol().getFir();
                if (ClassMembersKt.isSubstitutionOverride(fir)) {
                    originalForSubstitutionOverrideAttr = ClassMembersKt.getOriginalForSubstitutionOverrideAttr(fir);
                } else {
                    originalForSubstitutionOverrideAttr = ClassMembersKt.getOriginalForSubstitutionOverrideAttr(fir);
                }
                if (originalForSubstitutionOverrideAttr != null) {
                    symbol2 = originalForSubstitutionOverrideAttr.getSymbol();
                } else {
                    symbol2 = firFunctionSymbol2;
                }
                firFunctionSymbol3 = (FirFunctionSymbol) symbol2;
                if (firFunctionSymbol3 instanceof FirNamedFunctionSymbol) {
                    firFunctionSymbol4 = (FirNamedFunctionSymbol) firFunctionSymbol3;
                } else {
                    firFunctionSymbol4 = firFunctionSymbol2;
                }
                if (firFunctionSymbol4 != null) {
                    List overriddenSymbols8 = irSimpleFunctionCreateSimpleFunction$default.getOverriddenSymbols();
                    IrSimpleFunctionSymbol irFunctionSymbol$default8 = Fir2IrDeclarationStorage.getIrFunctionSymbol$default(getDeclarationStorage(), firFunctionSymbol4, null, false, 6, null);
                    irFunctionSymbol$default8.getClass();
                    irSimpleFunctionCreateSimpleFunction$default.setOverriddenSymbols(CollectionsKt.plus(overriddenSymbols8, irFunctionSymbol$default8));
                    Unit unit8 = Unit.INSTANCE;
                }
            }
            return irSimpleFunctionCreateSimpleFunction$default;
        } catch (Throwable th) {
            String str = "Exception was thrown during transformation of " + function.getClass();
            PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(th);
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(str, th);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", function);
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:33:0x0083  */
    public final IrLocalDelegatedProperty createIrLocalDelegatedProperty(FirProperty property, IrDeclarationParent irParent, LocalDelegatedPropertySymbols symbols) throws KotlinIllegalArgumentExceptionWithAttachments {
        FirProperty firProperty;
        int i;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        property.getClass();
        irParent.getClass();
        symbols.getClass();
        try {
            IrType irType$default = Fir2IrTypeConverterKt.toIrType$default(this, property.getReturnTypeRef(), (ConversionTypeOrigin) null, 2, (Object) null);
            IrDeclarationOrigin.Companion companion = IrDeclarationOrigin.Companion;
            IrDeclarationOrigin defined = companion.getDEFINED();
            TokenSet tokenSet = KtTokens.VAL_VAR;
            KtSourceElement source = property.getSource();
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
            IrMutableAnnotationContainer irMutableAnnotationContainerCreateLocalDelegatedProperty = IrFactoryImpl.INSTANCE.createLocalDelegatedProperty(i, endOffset, defined, property.getName(), symbols.getPropertySymbol(), irType$default, property.getIsVar());
            irMutableAnnotationContainerCreateLocalDelegatedProperty.setParent(irParent);
            irMutableAnnotationContainerCreateLocalDelegatedProperty.setMetadata(new FirMetadataSource.Property(property));
            Fir2IrDeclarationStorage declarationStorage = getDeclarationStorage();
            IrLocalDelegatedPropertySymbol symbol = irMutableAnnotationContainerCreateLocalDelegatedProperty.getSymbol();
            declarationStorage.enterScope(symbol);
            int startOffset2 = irMutableAnnotationContainerCreateLocalDelegatedProperty.getStartOffset();
            int endOffset2 = irMutableAnnotationContainerCreateLocalDelegatedProperty.getEndOffset();
            IrDeclarationOrigin property_delegate = companion.getPROPERTY_DELEGATE();
            Name namePropertyDelegateName = NameUtils.propertyDelegateName(property.getName());
            FirExpression delegate = property.getDelegate();
            delegate.getClass();
            IrVariable irVariableDeclareIrVariable = declareIrVariable(startOffset2, endOffset2, property_delegate, namePropertyDelegateName, Fir2IrTypeConverterKt.toIrType$default(this, FirTypeUtilsKt.getResolvedType(delegate), (ConversionTypeOrigin) null, 2, (Object) null), false, false, false);
            irVariableDeclareIrVariable.setParent(irParent);
            irMutableAnnotationContainerCreateLocalDelegatedProperty.setDelegate(irVariableDeclareIrVariable);
            try {
                firProperty = property;
                try {
                    irMutableAnnotationContainerCreateLocalDelegatedProperty.setGetter(createIrPropertyAccessor$default(this, property.getGetter(), firProperty, irMutableAnnotationContainerCreateLocalDelegatedProperty, symbols.getGetterSymbol(), irType$default, irParent, false, companion.getDELEGATED_PROPERTY_ACCESSOR(), irMutableAnnotationContainerCreateLocalDelegatedProperty.getStartOffset(), irMutableAnnotationContainerCreateLocalDelegatedProperty.getEndOffset(), null, 1024, null));
                    if (property.getIsVar()) {
                        FirPropertyAccessor setter = property.getSetter();
                        IrSimpleFunctionSymbol setterSymbol = symbols.getSetterSymbol();
                        setterSymbol.getClass();
                        firProperty = property;
                        irMutableAnnotationContainerCreateLocalDelegatedProperty.setSetter(createIrPropertyAccessor$default(this, setter, firProperty, irMutableAnnotationContainerCreateLocalDelegatedProperty, setterSymbol, irType$default, irParent, true, companion.getDELEGATED_PROPERTY_ACCESSOR(), irMutableAnnotationContainerCreateLocalDelegatedProperty.getStartOffset(), irMutableAnnotationContainerCreateLocalDelegatedProperty.getEndOffset(), null, 1024, null));
                    } else {
                        firProperty = property;
                    }
                    getAnnotationGenerator().generate(irMutableAnnotationContainerCreateLocalDelegatedProperty, firProperty);
                    declarationStorage.leaveScope(symbol);
                    return irMutableAnnotationContainerCreateLocalDelegatedProperty;
                } catch (Throwable th) {
                    th = th;
                    String str = "Exception was thrown during transformation of " + firProperty.getClass();
                    PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(th);
                    KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(str, th);
                    ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                    FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", firProperty);
                    kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                    throw kotlinIllegalArgumentExceptionWithAttachments;
                }
            } catch (Throwable th2) {
                th = th2;
                firProperty = property;
            }
        } catch (Throwable th3) {
            th = th3;
            firProperty = property;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:34:0x008b  */
    /* JADX WARN: Code duplicated, block: B:96:0x0174  */
    /* JADX WARN: Code duplicated, block: B:97:0x0175  */
    /* JADX WARN: Multi-variable type inference failed */
    public final IrValueParameter createIrParameter$org_jetbrains_kotlin_fir2ir(FirValueParameter valueParameter, boolean useStubForDefaultValueStub, ConversionTypeOrigin typeOrigin, boolean skipDefaultParameter, boolean forcedDefaultValueConversion, IrDeclarationOrigin predefinedOrigin) throws KotlinIllegalArgumentExceptionWithAttachments {
        int i;
        int endOffset;
        FirExpression firExpression;
        FirFunctionSymbol<?> singleMatchedExpectForActualOrNull;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        valueParameter.getClass();
        typeOrigin.getClass();
        try {
            FirLazyDeclarationResolverKt.lazyResolveToPhase(valueParameter, FirResolvePhase.BODY_RESOLVE);
            IrDeclarationOrigin irDeclarationOriginComputeIrOrigin$default = OriginUtilsKt.computeIrOrigin$default(valueParameter, predefinedOrigin, null, null, 6, null);
            IrType irType = Fir2IrTypeConverterKt.toIrType(this, valueParameter.getReturnTypeRef(), typeOrigin);
            TokenSet tokenSet = KtTokens.VAL_VAR;
            KtSourceElement source = valueParameter.getSource();
            IrExpressionBody irExpressionBodyCreateExpressionBody = null;
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
            IrFactoryImpl irFactoryImpl = IrFactoryImpl.INSTANCE;
            Name generatedContextParameterName = ClassMembersKt.getGeneratedContextParameterName(valueParameter);
            if (generatedContextParameterName == null) {
                generatedContextParameterName = valueParameter.getName();
            }
            Name name = generatedContextParameterName;
            FirDeclaration fir = valueParameter.getContainingDeclarationSymbol().getFir();
            boolean z = (fir instanceof FirCallableDeclaration) && Fir2IrCallableDeclarationsGeneratorKt.shouldParametersBeAssignable((FirCallableDeclaration) fir, this.c);
            IrValueParameterSymbolImpl irValueParameterSymbolImpl = new IrValueParameterSymbolImpl((ParameterDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null);
            ConeKotlinType varargElementType = VariousUtilsKt.getVarargElementType(valueParameter);
            IrType irType2 = varargElementType != null ? Fir2IrTypeConverterKt.toIrType(this, varargElementType, typeOrigin) : null;
            boolean zIsCrossinline = valueParameter.getIsCrossinline();
            boolean zIsNoinline = valueParameter.getIsNoinline();
            FirValueParameterKind valueParameterKind = valueParameter.getValueParameterKind();
            FirValueParameterKind firValueParameterKind = FirValueParameterKind.Regular;
            IrValueParameter irValueParameterCreateValueParameter = irFactoryImpl.createValueParameter(i, endOffset, irDeclarationOriginComputeIrOrigin$default, valueParameterKind == firValueParameterKind ? IrParameterKind.Regular : IrParameterKind.Context, name, irType, z, irValueParameterSymbolImpl, irType2, zIsCrossinline, zIsNoinline, false);
            FirExpression defaultValue = valueParameter.getDefaultValue();
            if (defaultValue == null) {
                if (this.c.getConfiguration().getAllowNonCachedDeclarations() && valueParameter.getValueParameterKind() == firValueParameterKind) {
                    FirBasedSymbol<?> containingDeclarationSymbol = valueParameter.getContainingDeclarationSymbol();
                    FirFunctionSymbol firFunctionSymbol = containingDeclarationSymbol instanceof FirFunctionSymbol ? (FirFunctionSymbol) containingDeclarationSymbol : null;
                    if (firFunctionSymbol != null && firFunctionSymbol.getRawStatus().isActual() && (singleMatchedExpectForActualOrNull = ExpectActualAttributesKt.getSingleMatchedExpectForActualOrNull((FirFunctionSymbol<?>) firFunctionSymbol)) != null) {
                        FirValueParameterSymbol firValueParameterSymbol = (FirValueParameterSymbol) CollectionsKt.getOrNull(singleMatchedExpectForActualOrNull.getValueParameterSymbols(), firFunctionSymbol.getValueParameterSymbols().indexOf(valueParameter.getSymbol()));
                        if (firValueParameterSymbol != null) {
                            defaultValue = ((FirValueParameter) firValueParameterSymbol.getFir()).getDefaultValue();
                        }
                    }
                    defaultValue = null;
                } else {
                    defaultValue = null;
                }
            }
            if (!skipDefaultParameter && defaultValue != null) {
                if (forcedDefaultValueConversion && !(defaultValue instanceof FirExpressionStub)) {
                    FirEvaluatorResult evaluatedInitializer = DeclarationAttributesKt.getEvaluatedInitializer(valueParameter);
                    if (evaluatedInitializer != null) {
                        if (evaluatedInitializer instanceof FirEvaluatorResult.CompileTimeException) {
                        } else {
                            if (evaluatedInitializer instanceof FirEvaluatorResult.Evaluated) {
                                FirElement result = ((FirEvaluatorResult.Evaluated) evaluatedInitializer).getResult();
                                if (!(result instanceof FirExpression)) {
                                    result = null;
                                }
                                firExpression = (FirExpression) result;
                            }
                            if (firExpression == null) {
                                defaultValue = firExpression;
                            }
                        }
                        firExpression = null;
                        if (firExpression == null) {
                            defaultValue = firExpression;
                        }
                    }
                    irExpressionBodyCreateExpressionBody = ConstantUtilsKt.asCompileTimeIrInitializerForAnnotationParameter$default(this, defaultValue, null, 2, null);
                } else if (useStubForDefaultValueStub || !(defaultValue instanceof FirExpressionStub)) {
                    irExpressionBodyCreateExpressionBody = IrFactoryHelpersKt.createExpressionBody(irValueParameterCreateValueParameter.getFactory(), org.jetbrains.kotlin.ir.expressions.impl.BuildersKt.IrErrorExpressionImpl(-1, -1, irType, "Stub expression for default value of " + valueParameter.getName()));
                }
                irValueParameterCreateValueParameter.setDefaultValue(irExpressionBodyCreateExpressionBody);
            }
            getAnnotationGenerator().generate(irValueParameterCreateValueParameter, valueParameter);
            return irValueParameterCreateValueParameter;
        } catch (Throwable th) {
            String str = "Exception was thrown during transformation of " + valueParameter.getClass();
            PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(th);
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(str, th);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", valueParameter);
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:120:0x02bb A[Catch: all -> 0x02d6, TryCatch #0 {all -> 0x02d6, blocks: (B:150:0x0358, B:152:0x035e, B:153:0x0363, B:155:0x0369, B:157:0x038c, B:159:0x0395, B:161:0x039b, B:148:0x0353, B:118:0x02b7, B:120:0x02bb, B:123:0x02db, B:125:0x02e5, B:127:0x02eb, B:129:0x02ef, B:130:0x02f2, B:132:0x02f6, B:135:0x0301, B:138:0x0307, B:147:0x0348, B:139:0x0315, B:141:0x031c, B:143:0x0323, B:145:0x0329, B:146:0x032d), top: B:174:0x02b7 }] */
    /* JADX WARN: Code duplicated, block: B:123:0x02db A[Catch: all -> 0x02d6, TryCatch #0 {all -> 0x02d6, blocks: (B:150:0x0358, B:152:0x035e, B:153:0x0363, B:155:0x0369, B:157:0x038c, B:159:0x0395, B:161:0x039b, B:148:0x0353, B:118:0x02b7, B:120:0x02bb, B:123:0x02db, B:125:0x02e5, B:127:0x02eb, B:129:0x02ef, B:130:0x02f2, B:132:0x02f6, B:135:0x0301, B:138:0x0307, B:147:0x0348, B:139:0x0315, B:141:0x031c, B:143:0x0323, B:145:0x0329, B:146:0x032d), top: B:174:0x02b7 }] */
    /* JADX WARN: Code duplicated, block: B:125:0x02e5 A[Catch: all -> 0x02d6, TryCatch #0 {all -> 0x02d6, blocks: (B:150:0x0358, B:152:0x035e, B:153:0x0363, B:155:0x0369, B:157:0x038c, B:159:0x0395, B:161:0x039b, B:148:0x0353, B:118:0x02b7, B:120:0x02bb, B:123:0x02db, B:125:0x02e5, B:127:0x02eb, B:129:0x02ef, B:130:0x02f2, B:132:0x02f6, B:135:0x0301, B:138:0x0307, B:147:0x0348, B:139:0x0315, B:141:0x031c, B:143:0x0323, B:145:0x0329, B:146:0x032d), top: B:174:0x02b7 }] */
    /* JADX WARN: Code duplicated, block: B:127:0x02eb A[Catch: all -> 0x02d6, TryCatch #0 {all -> 0x02d6, blocks: (B:150:0x0358, B:152:0x035e, B:153:0x0363, B:155:0x0369, B:157:0x038c, B:159:0x0395, B:161:0x039b, B:148:0x0353, B:118:0x02b7, B:120:0x02bb, B:123:0x02db, B:125:0x02e5, B:127:0x02eb, B:129:0x02ef, B:130:0x02f2, B:132:0x02f6, B:135:0x0301, B:138:0x0307, B:147:0x0348, B:139:0x0315, B:141:0x031c, B:143:0x0323, B:145:0x0329, B:146:0x032d), top: B:174:0x02b7 }] */
    /* JADX WARN: Code duplicated, block: B:129:0x02ef A[Catch: all -> 0x02d6, TryCatch #0 {all -> 0x02d6, blocks: (B:150:0x0358, B:152:0x035e, B:153:0x0363, B:155:0x0369, B:157:0x038c, B:159:0x0395, B:161:0x039b, B:148:0x0353, B:118:0x02b7, B:120:0x02bb, B:123:0x02db, B:125:0x02e5, B:127:0x02eb, B:129:0x02ef, B:130:0x02f2, B:132:0x02f6, B:135:0x0301, B:138:0x0307, B:147:0x0348, B:139:0x0315, B:141:0x031c, B:143:0x0323, B:145:0x0329, B:146:0x032d), top: B:174:0x02b7 }] */
    /* JADX WARN: Code duplicated, block: B:130:0x02f2 A[Catch: all -> 0x02d6, TryCatch #0 {all -> 0x02d6, blocks: (B:150:0x0358, B:152:0x035e, B:153:0x0363, B:155:0x0369, B:157:0x038c, B:159:0x0395, B:161:0x039b, B:148:0x0353, B:118:0x02b7, B:120:0x02bb, B:123:0x02db, B:125:0x02e5, B:127:0x02eb, B:129:0x02ef, B:130:0x02f2, B:132:0x02f6, B:135:0x0301, B:138:0x0307, B:147:0x0348, B:139:0x0315, B:141:0x031c, B:143:0x0323, B:145:0x0329, B:146:0x032d), top: B:174:0x02b7 }] */
    /* JADX WARN: Code duplicated, block: B:132:0x02f6 A[Catch: all -> 0x02d6, TryCatch #0 {all -> 0x02d6, blocks: (B:150:0x0358, B:152:0x035e, B:153:0x0363, B:155:0x0369, B:157:0x038c, B:159:0x0395, B:161:0x039b, B:148:0x0353, B:118:0x02b7, B:120:0x02bb, B:123:0x02db, B:125:0x02e5, B:127:0x02eb, B:129:0x02ef, B:130:0x02f2, B:132:0x02f6, B:135:0x0301, B:138:0x0307, B:147:0x0348, B:139:0x0315, B:141:0x031c, B:143:0x0323, B:145:0x0329, B:146:0x032d), top: B:174:0x02b7 }] */
    /* JADX WARN: Code duplicated, block: B:134:0x0300  */
    /* JADX WARN: Code duplicated, block: B:136:0x0304  */
    /* JADX WARN: Code duplicated, block: B:138:0x0307 A[Catch: all -> 0x02d6, TryCatch #0 {all -> 0x02d6, blocks: (B:150:0x0358, B:152:0x035e, B:153:0x0363, B:155:0x0369, B:157:0x038c, B:159:0x0395, B:161:0x039b, B:148:0x0353, B:118:0x02b7, B:120:0x02bb, B:123:0x02db, B:125:0x02e5, B:127:0x02eb, B:129:0x02ef, B:130:0x02f2, B:132:0x02f6, B:135:0x0301, B:138:0x0307, B:147:0x0348, B:139:0x0315, B:141:0x031c, B:143:0x0323, B:145:0x0329, B:146:0x032d), top: B:174:0x02b7 }] */
    /* JADX WARN: Code duplicated, block: B:139:0x0315 A[Catch: all -> 0x02d6, TryCatch #0 {all -> 0x02d6, blocks: (B:150:0x0358, B:152:0x035e, B:153:0x0363, B:155:0x0369, B:157:0x038c, B:159:0x0395, B:161:0x039b, B:148:0x0353, B:118:0x02b7, B:120:0x02bb, B:123:0x02db, B:125:0x02e5, B:127:0x02eb, B:129:0x02ef, B:130:0x02f2, B:132:0x02f6, B:135:0x0301, B:138:0x0307, B:147:0x0348, B:139:0x0315, B:141:0x031c, B:143:0x0323, B:145:0x0329, B:146:0x032d), top: B:174:0x02b7 }] */
    /* JADX WARN: Code duplicated, block: B:141:0x031c A[Catch: all -> 0x02d6, TryCatch #0 {all -> 0x02d6, blocks: (B:150:0x0358, B:152:0x035e, B:153:0x0363, B:155:0x0369, B:157:0x038c, B:159:0x0395, B:161:0x039b, B:148:0x0353, B:118:0x02b7, B:120:0x02bb, B:123:0x02db, B:125:0x02e5, B:127:0x02eb, B:129:0x02ef, B:130:0x02f2, B:132:0x02f6, B:135:0x0301, B:138:0x0307, B:147:0x0348, B:139:0x0315, B:141:0x031c, B:143:0x0323, B:145:0x0329, B:146:0x032d), top: B:174:0x02b7 }] */
    /* JADX WARN: Code duplicated, block: B:142:0x0321  */
    /* JADX WARN: Code duplicated, block: B:145:0x0329 A[Catch: all -> 0x02d6, TryCatch #0 {all -> 0x02d6, blocks: (B:150:0x0358, B:152:0x035e, B:153:0x0363, B:155:0x0369, B:157:0x038c, B:159:0x0395, B:161:0x039b, B:148:0x0353, B:118:0x02b7, B:120:0x02bb, B:123:0x02db, B:125:0x02e5, B:127:0x02eb, B:129:0x02ef, B:130:0x02f2, B:132:0x02f6, B:135:0x0301, B:138:0x0307, B:147:0x0348, B:139:0x0315, B:141:0x031c, B:143:0x0323, B:145:0x0329, B:146:0x032d), top: B:174:0x02b7 }] */
    /* JADX WARN: Code duplicated, block: B:65:0x0102  */
    public final IrProperty createIrProperty(final FirProperty property, final IrDeclarationParent irParent, final PropertySymbols symbols, IrDeclarationOrigin predefinedOrigin, ConeClassLikeLookupTag fakeOverrideOwnerLookupTag, boolean allowLazyDeclarationsCreation) throws KotlinIllegalArgumentExceptionWithAttachments {
        IrDeclarationOrigin irDeclarationOriginComputeIrOrigin;
        int startOffset;
        int endOffset;
        int i;
        IrProperty irProperty;
        FirExpression firExpression;
        IrType irType;
        IrDeclarationOrigin irDeclarationOrigin;
        Fir2IrDeclarationStorage fir2IrDeclarationStorage;
        int i2;
        IrField irFieldCreateBackingField$org_jetbrains_kotlin_fir2ir;
        FirEvaluatorResult evaluatedInitializer;
        FirLiteralExpression firLiteralExpression;
        IrSimpleFunction irSimpleFunction;
        IrExpressionBody initializer;
        int startOffset2;
        IrConst irConstIrErrorExpressionImpl;
        FirElement result;
        FirTypeRef returnTypeRef;
        FirReference calleeReference;
        FirBasedSymbol resolvedBaseSymbol$default;
        final IrType irType2;
        final IrDeclarationOrigin irDeclarationOrigin2;
        IrSimpleFunction irSimpleFunction2;
        IrField backingField;
        Integer numStartOffsetSkippingComments;
        FirProperty firProperty = property;
        firProperty.getClass();
        symbols.getClass();
        try {
            if (!isExternal(predefinedOrigin) && firProperty.getStatus().isStatic() && Fir2IrDeclarationStorage.INSTANCE.getENUM_SYNTHETIC_NAMES$org_jetbrains_kotlin_fir2ir().containsKey(firProperty.getName())) {
                irDeclarationOriginComputeIrOrigin = IrDeclarationOrigin.Companion.getENUM_CLASS_SPECIAL_MEMBER();
            } else {
                IrDeclaration irDeclaration = irParent instanceof IrDeclaration ? (IrDeclaration) irParent : null;
                irDeclarationOriginComputeIrOrigin = OriginUtilsKt.computeIrOrigin(firProperty, predefinedOrigin, irDeclaration != null ? irDeclaration.getOrigin() : null, fakeOverrideOwnerLookupTag);
            }
            IrDeclarationOrigin irDeclarationOrigin3 = irDeclarationOriginComputeIrOrigin;
            if (Fir2IrCallableDeclarationsGeneratorKt.isExternalParent(irParent)) {
                if (allowLazyDeclarationsCreation) {
                    return symbols.getPropertySymbol().isBound() ? symbols.getPropertySymbol().getOwner() : getLazyDeclarationsGenerator().createIrLazyProperty$org_jetbrains_kotlin_fir2ir(firProperty, irParent, symbols, irDeclarationOrigin3);
                }
                throw new IllegalStateException(("Lazy properties should be processed in Fir2IrDeclarationStorage: " + UtilsKt.render(firProperty)).toString());
            }
            TokenSet tokenSet = firProperty != null ? KtTokens.VAL_VAR : null;
            KtSourceElement source = firProperty.getSource();
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
                            if (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, tokenSet)) == null) {
                                startOffset = source != null ? source.getStartOffset() : -1;
                            } else {
                                startOffset = numStartOffsetSkippingComments.intValue();
                            }
                            endOffset = source != null ? source.getEndOffset() : -1;
                        }
                    }
                }
            }
            getClassifierStorage().preCacheTypeParameters$org_jetbrains_kotlin_fir2ir(firProperty);
            IrFactoryImpl irFactoryImpl = IrFactoryImpl.INSTANCE;
            IrDeclarationOrigin.Companion companion = IrDeclarationOrigin.Companion;
            int i3 = Intrinsics.areEqual(irDeclarationOrigin3, companion.getENUM_CLASS_SPECIAL_MEMBER()) ? -2 : startOffset;
            int i4 = Intrinsics.areEqual(irDeclarationOrigin3, companion.getENUM_CLASS_SPECIAL_MEMBER()) ? -2 : endOffset;
            Name name = firProperty.getName();
            DescriptorVisibility descriptorVisibilityConvertToDescriptorVisibility = this.c.getVisibilityConverter().convertToDescriptorVisibility(firProperty.getStatus().getVisibility());
            Modality modality = firProperty.getStatus().getModality();
            modality.getClass();
            IrProperty irPropertyCreateProperty$default = IrFactory.createProperty$default(irFactoryImpl, i3, i4, irDeclarationOrigin3, name, descriptorVisibilityConvertToDescriptorVisibility, modality, symbols.getPropertySymbol(), firProperty.getIsVar(), firProperty.getStatus().isConst(), firProperty.getStatus().isLateInit(), firProperty.getDelegate() != null, Fir2IrCallableDeclarationsGeneratorKt.isEffectivelyExternal(property, irParent), firProperty.getContainerSource(), firProperty.getStatus().isExpect(), false, 16384, (Object) null);
            irPropertyCreateProperty$default.setMetadata(new FirMetadataSource.Property(firProperty));
            convertAnnotationsForNonDeclaredMembers(irPropertyCreateProperty$default, firProperty, irDeclarationOrigin3);
            Fir2IrDeclarationStorage declarationStorage = getDeclarationStorage();
            IrPropertySymbol symbol = irPropertyCreateProperty$default.getSymbol();
            declarationStorage.enterScope(symbol);
            Fir2IrCallableDeclarationsGeneratorKt.setParent(irPropertyCreateProperty$default, irParent);
            Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irPropertyCreateProperty$default, irParent);
            IrType irType$default = Fir2IrTypeConverterKt.toIrType$default(this, firProperty.getReturnTypeRef(), (ConversionTypeOrigin) null, 2, (Object) null);
            FirExpression delegate = firProperty.getDelegate();
            final FirPropertyAccessor getter = firProperty.getGetter();
            final FirPropertyAccessor setter = firProperty.getSetter();
            if (delegate != null || DeclarationAttributesKt.getHasBackingField(firProperty)) {
                if (delegate != null) {
                    FirQualifiedAccessExpression firQualifiedAccessExpression = delegate instanceof FirQualifiedAccessExpression ? (FirQualifiedAccessExpression) delegate : null;
                    FirAnnotationContainer fir = (firQualifiedAccessExpression == null || (calleeReference = firQualifiedAccessExpression.getCalleeReference()) == null || (resolvedBaseSymbol$default = FirReferenceUtilsKt.toResolvedBaseSymbol$default(calleeReference, false, 1, null)) == null) ? null : resolvedBaseSymbol$default.getFir();
                    FirTypeParameterRefsOwner firTypeParameterRefsOwner = fir instanceof FirTypeParameterRefsOwner ? (FirTypeParameterRefsOwner) fir : null;
                    if (firTypeParameterRefsOwner != null) {
                        getClassifierStorage().preCacheTypeParameters$org_jetbrains_kotlin_fir2ir(firTypeParameterRefsOwner);
                        Unit unit = Unit.INSTANCE;
                    }
                    int i5 = startOffset;
                    IrDeclarationOrigin property_delegate = companion.getPROPERTY_DELEGATE();
                    int i6 = endOffset;
                    IrFieldSymbol backingFieldSymbol = symbols.getBackingFieldSymbol();
                    backingFieldSymbol.getClass();
                    firExpression = delegate;
                    i = i5;
                    irProperty = irPropertyCreateProperty$default;
                    irFieldCreateBackingField$org_jetbrains_kotlin_fir2ir = createBackingField$org_jetbrains_kotlin_fir2ir(irProperty, firProperty, property_delegate, backingFieldSymbol, this.c.getVisibilityConverter().convertToDescriptorVisibility(getFieldVisibility(property)), NameUtils.propertyDelegateName(firProperty.getName()), true, !Intrinsics.areEqual(DeclarationAttributesKt.isReplSnippetDeclaration(firProperty), Boolean.TRUE) ? delegate : null, Fir2IrTypeConverterKt.toIrType$default(this, FirTypeUtilsKt.getResolvedType(delegate), (ConversionTypeOrigin) null, 2, (Object) null));
                    irDeclarationOrigin = irDeclarationOrigin3;
                    fir2IrDeclarationStorage = declarationStorage;
                    i2 = i6;
                } else {
                    i = startOffset;
                    irProperty = irPropertyCreateProperty$default;
                    firExpression = delegate;
                    FirExpression effectivePropertyInitializer = getEffectivePropertyInitializer(firProperty, true);
                    FirBackingField backingField2 = firProperty.getBackingField();
                    try {
                        if (backingField2 != null && (returnTypeRef = backingField2.getReturnTypeRef()) != null) {
                            IrType irType$default2 = Fir2IrTypeConverterKt.toIrType$default(this, returnTypeRef, (ConversionTypeOrigin) null, 2, (Object) null);
                            if (irType$default2 != null) {
                                irType = irType$default2;
                            }
                            IrDeclarationOrigin property_backing_field = companion.getPROPERTY_BACKING_FIELD();
                            int i7 = endOffset;
                            IrFieldSymbol backingFieldSymbol2 = symbols.getBackingFieldSymbol();
                            backingFieldSymbol2.getClass();
                            irDeclarationOrigin = irDeclarationOrigin3;
                            fir2IrDeclarationStorage = declarationStorage;
                            i2 = i7;
                            irFieldCreateBackingField$org_jetbrains_kotlin_fir2ir = createBackingField$org_jetbrains_kotlin_fir2ir(irProperty, firProperty, property_backing_field, backingFieldSymbol2, this.c.getVisibilityConverter().convertToDescriptorVisibility(getFieldVisibility(property)), firProperty.getName(), firProperty.getIsVal(), effectivePropertyInitializer, irType);
                            if (effectivePropertyInitializer instanceof FirLiteralExpression) {
                                irFieldCreateBackingField$org_jetbrains_kotlin_fir2ir.setInitializer(IrFactoryHelpersKt.createExpressionBody(irProperty.getFactory(), ConstantUtilsKt.toIrConst((FirLiteralExpression) effectivePropertyInitializer, Fir2IrTypeConverterKt.toIrType$default(this, FirTypeUtilsKt.getResolvedType(effectivePropertyInitializer), (ConversionTypeOrigin) null, 2, (Object) null))));
                            } else {
                                if (property.getStatus().isConst()) {
                                    evaluatedInitializer = DeclarationAttributesKt.getEvaluatedInitializer(property);
                                    if (evaluatedInitializer == null) {
                                        firLiteralExpression = null;
                                    } else {
                                        if (evaluatedInitializer instanceof FirEvaluatorResult.CompileTimeException) {
                                        } else if (evaluatedInitializer instanceof FirEvaluatorResult.Evaluated) {
                                            result = ((FirEvaluatorResult.Evaluated) evaluatedInitializer).getResult();
                                            if (!(result instanceof FirLiteralExpression)) {
                                                result = null;
                                            }
                                            firLiteralExpression = (FirLiteralExpression) result;
                                        }
                                        firLiteralExpression = null;
                                    }
                                    if (firLiteralExpression != null) {
                                        irSimpleFunction = null;
                                        irConstIrErrorExpressionImpl = ConstantUtilsKt.toIrConst(firLiteralExpression, Fir2IrTypeConverterKt.toIrType$default(this, FirTypeUtilsKt.getResolvedType(firLiteralExpression), (ConversionTypeOrigin) null, 2, (Object) null));
                                    } else {
                                        irSimpleFunction = null;
                                        initializer = irFieldCreateBackingField$org_jetbrains_kotlin_fir2ir.getInitializer();
                                        if (initializer != null) {
                                            startOffset2 = initializer.getStartOffset();
                                        } else {
                                            startOffset2 = -1;
                                        }
                                        IrExpressionBody initializer2 = irFieldCreateBackingField$org_jetbrains_kotlin_fir2ir.getInitializer();
                                        irConstIrErrorExpressionImpl = org.jetbrains.kotlin.ir.expressions.impl.BuildersKt.IrErrorExpressionImpl(startOffset2, initializer2 != null ? initializer2.getEndOffset() : -1, irType, "Initializer for const property " + property.getName() + " was not evaluated");
                                    }
                                    irFieldCreateBackingField$org_jetbrains_kotlin_fir2ir.setInitializer(IrFactoryHelpersKt.createExpressionBody(irProperty.getFactory(), irConstIrErrorExpressionImpl));
                                }
                                irProperty.setBackingField(irFieldCreateBackingField$org_jetbrains_kotlin_fir2ir);
                            }
                        }
                        if (effectivePropertyInitializer instanceof FirLiteralExpression) {
                            irFieldCreateBackingField$org_jetbrains_kotlin_fir2ir.setInitializer(IrFactoryHelpersKt.createExpressionBody(irProperty.getFactory(), ConstantUtilsKt.toIrConst((FirLiteralExpression) effectivePropertyInitializer, Fir2IrTypeConverterKt.toIrType$default(this, FirTypeUtilsKt.getResolvedType(effectivePropertyInitializer), (ConversionTypeOrigin) null, 2, (Object) null))));
                        } else {
                            if (property.getStatus().isConst()) {
                                evaluatedInitializer = DeclarationAttributesKt.getEvaluatedInitializer(property);
                                if (evaluatedInitializer == null) {
                                    firLiteralExpression = null;
                                } else {
                                    if (evaluatedInitializer instanceof FirEvaluatorResult.CompileTimeException) {
                                    } else if (evaluatedInitializer instanceof FirEvaluatorResult.Evaluated) {
                                        result = ((FirEvaluatorResult.Evaluated) evaluatedInitializer).getResult();
                                        if (!(result instanceof FirLiteralExpression)) {
                                            result = null;
                                        }
                                        firLiteralExpression = (FirLiteralExpression) result;
                                    }
                                    firLiteralExpression = null;
                                }
                                if (firLiteralExpression != null) {
                                    irSimpleFunction = null;
                                    irConstIrErrorExpressionImpl = ConstantUtilsKt.toIrConst(firLiteralExpression, Fir2IrTypeConverterKt.toIrType$default(this, FirTypeUtilsKt.getResolvedType(firLiteralExpression), (ConversionTypeOrigin) null, 2, (Object) null));
                                } else {
                                    irSimpleFunction = null;
                                    initializer = irFieldCreateBackingField$org_jetbrains_kotlin_fir2ir.getInitializer();
                                    if (initializer != null) {
                                        startOffset2 = initializer.getStartOffset();
                                    } else {
                                        startOffset2 = -1;
                                    }
                                    IrExpressionBody initializer3 = irFieldCreateBackingField$org_jetbrains_kotlin_fir2ir.getInitializer();
                                    if (initializer3 != null) {
                                    }
                                    irConstIrErrorExpressionImpl = org.jetbrains.kotlin.ir.expressions.impl.BuildersKt.IrErrorExpressionImpl(startOffset2, initializer3 != null ? initializer3.getEndOffset() : -1, irType, "Initializer for const property " + property.getName() + " was not evaluated");
                                }
                                irFieldCreateBackingField$org_jetbrains_kotlin_fir2ir.setInitializer(IrFactoryHelpersKt.createExpressionBody(irProperty.getFactory(), irConstIrErrorExpressionImpl));
                            }
                            irProperty.setBackingField(irFieldCreateBackingField$org_jetbrains_kotlin_fir2ir);
                        }
                    } catch (Throwable th) {
                        th = th;
                        firProperty = property;
                        String str = "Exception was thrown during transformation of " + firProperty.getClass();
                        PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(th);
                        KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(str, th);
                        ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
                        FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", firProperty);
                        kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
                        throw kotlinIllegalArgumentExceptionWithAttachments;
                    }
                    irType = irType$default;
                    IrDeclarationOrigin property_backing_field2 = companion.getPROPERTY_BACKING_FIELD();
                    int i8 = endOffset;
                    IrFieldSymbol backingFieldSymbol3 = symbols.getBackingFieldSymbol();
                    backingFieldSymbol3.getClass();
                    irDeclarationOrigin = irDeclarationOrigin3;
                    fir2IrDeclarationStorage = declarationStorage;
                    i2 = i8;
                    irFieldCreateBackingField$org_jetbrains_kotlin_fir2ir = createBackingField$org_jetbrains_kotlin_fir2ir(irProperty, firProperty, property_backing_field2, backingFieldSymbol3, this.c.getVisibilityConverter().convertToDescriptorVisibility(getFieldVisibility(property)), firProperty.getName(), firProperty.getIsVal(), effectivePropertyInitializer, irType);
                }
                irSimpleFunction = null;
                irProperty.setBackingField(irFieldCreateBackingField$org_jetbrains_kotlin_fir2ir);
            } else {
                irProperty = irPropertyCreateProperty$default;
                firExpression = delegate;
                irSimpleFunction = null;
                irDeclarationOrigin = irDeclarationOrigin3;
                fir2IrDeclarationStorage = declarationStorage;
                i = startOffset;
                i2 = endOffset;
            }
            if (irParent != null && (backingField = irProperty.getBackingField()) != null) {
                backingField.setParent(irParent);
                Unit unit2 = Unit.INSTANCE;
            }
            final IrSimpleFunctionSymbol getterSymbol = symbols.getGetterSymbol();
            if (getterSymbol != null) {
                final IrProperty irProperty2 = irProperty;
                irType2 = irType$default;
                final FirExpression firExpression2 = firExpression;
                final IrDeclarationOrigin irDeclarationOrigin4 = irDeclarationOrigin;
                Function2<Integer, Integer, IrSimpleFunction> function2 = new Function2<Integer, Integer, IrSimpleFunction>() { // from class: org.jetbrains.kotlin.fir.backend.generators.Fir2IrCallableDeclarationsGenerator$createIrProperty$1$1$1$1$1$1
                    public final IrSimpleFunction invoke(int i9, int i10) {
                        Fir2IrCallableDeclarationsGenerator fir2IrCallableDeclarationsGenerator = this.this$0;
                        FirPropertyAccessor firPropertyAccessor = getter;
                        FirProperty firProperty2 = property;
                        IrProperty irProperty3 = irProperty2;
                        IrSimpleFunctionSymbol irSimpleFunctionSymbol = getterSymbol;
                        IrType irType3 = irType2;
                        IrDeclarationParent irDeclarationParent = irParent;
                        IrDeclarationOrigin irDeclarationOrigin5 = irDeclarationOrigin4;
                        IrDeclarationOrigin.Companion companion2 = IrDeclarationOrigin.Companion;
                        boolean zAreEqual = Intrinsics.areEqual(irDeclarationOrigin5, companion2.getIR_EXTERNAL_DECLARATION_STUB());
                        IrDeclarationOrigin default_property_accessor = irDeclarationOrigin4;
                        if (!zAreEqual) {
                            if (Intrinsics.areEqual(default_property_accessor, companion2.getENUM_CLASS_SPECIAL_MEMBER())) {
                                default_property_accessor = irDeclarationOrigin4;
                            } else if (firExpression2 != null) {
                                default_property_accessor = companion2.getDELEGATED_PROPERTY_ACCESSOR();
                            } else {
                                boolean zAreEqual2 = Intrinsics.areEqual(irDeclarationOrigin4, companion2.getFAKE_OVERRIDE());
                                default_property_accessor = irDeclarationOrigin4;
                                if (!zAreEqual2) {
                                    if (Intrinsics.areEqual(default_property_accessor, companion2.getDELEGATED_MEMBER())) {
                                        default_property_accessor = irDeclarationOrigin4;
                                    } else {
                                        FirPropertyAccessor firPropertyAccessor2 = getter;
                                        default_property_accessor = (firPropertyAccessor2 == null || (firPropertyAccessor2 instanceof FirDefaultPropertyGetter)) ? companion2.getDEFAULT_PROPERTY_ACCESSOR() : irDeclarationOrigin4;
                                    }
                                }
                            }
                        }
                        if (Intrinsics.areEqual(irDeclarationOrigin4, companion2.getENUM_CLASS_SPECIAL_MEMBER())) {
                            i9 = -2;
                        }
                        int i11 = Intrinsics.areEqual(irDeclarationOrigin4, companion2.getENUM_CLASS_SPECIAL_MEMBER()) ? -2 : i10;
                        FirCallableDeclaration firCallableDeclaration = property;
                        while (true) {
                            FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration) : null;
                            if (originalForSubstitutionOverrideAttr == null) {
                                originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration) : null;
                            }
                            if (originalForSubstitutionOverrideAttr == null) {
                                return fir2IrCallableDeclarationsGenerator.createIrPropertyAccessor(firPropertyAccessor, firProperty2, irProperty3, irSimpleFunctionSymbol, irType3, irDeclarationParent, false, default_property_accessor, i9, i11, ((FirProperty) firCallableDeclaration).getGetter());
                            }
                            firCallableDeclaration = originalForSubstitutionOverrideAttr;
                        }
                    }

                    public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                        return invoke(((Number) obj).intValue(), ((Number) obj2).intValue());
                    }
                };
                irProperty = irProperty2;
                firExpression = firExpression2;
                irDeclarationOrigin2 = irDeclarationOrigin4;
                irSimpleFunction2 = (IrSimpleFunction) OffsetUtilsKt.convertWithOffsets(getter, i, i2, function2);
            } else {
                irType2 = irType$default;
                irDeclarationOrigin2 = irDeclarationOrigin;
                irSimpleFunction2 = irSimpleFunction;
            }
            irProperty.setGetter(irSimpleFunction2);
            if (property.getIsVar() && symbols.getSetterSymbol() != null) {
                final IrProperty irProperty3 = irProperty;
                final FirExpression firExpression3 = firExpression;
                try {
                    irProperty = irProperty3;
                    irProperty.setSetter(OffsetUtilsKt.convertWithOffsets(setter, i, i2, new Function2<Integer, Integer, IrSimpleFunction>() { // from class: org.jetbrains.kotlin.fir.backend.generators.Fir2IrCallableDeclarationsGenerator$createIrProperty$1$1$1$1$2
                        /* JADX WARN: Code duplicated, block: B:23:0x005f  */
                        /* JADX WARN: Code duplicated, block: B:25:0x0065  */
                        /* JADX WARN: Code duplicated, block: B:27:0x006b  */
                        /* JADX WARN: Code duplicated, block: B:28:0x0070  */
                        /* JADX WARN: Code duplicated, block: B:32:0x0081 A[LOOP:0: B:17:0x004d->B:32:0x0081, LOOP_END] */
                        /* JADX WARN: Code duplicated, block: B:33:0x0073 A[SYNTHETIC] */
                        public final IrSimpleFunction invoke(int i9, int i10) {
                            IrDeclarationOrigin default_property_accessor;
                            IrDeclarationOrigin irDeclarationOrigin5;
                            FirCallableDeclaration firCallableDeclaration;
                            FirCallableDeclaration originalForSubstitutionOverrideAttr;
                            Fir2IrCallableDeclarationsGenerator fir2IrCallableDeclarationsGenerator = this.this$0;
                            FirPropertyAccessor firPropertyAccessor = setter;
                            FirProperty firProperty2 = property;
                            IrProperty irProperty4 = irProperty3;
                            IrSimpleFunctionSymbol setterSymbol = symbols.getSetterSymbol();
                            IrType irType3 = irType2;
                            IrDeclarationParent irDeclarationParent = irParent;
                            if (firExpression3 != null) {
                                default_property_accessor = IrDeclarationOrigin.Companion.getDELEGATED_PROPERTY_ACCESSOR();
                            } else {
                                IrDeclarationOrigin irDeclarationOrigin6 = irDeclarationOrigin2;
                                IrDeclarationOrigin.Companion companion2 = IrDeclarationOrigin.Companion;
                                boolean zAreEqual = Intrinsics.areEqual(irDeclarationOrigin6, companion2.getFAKE_OVERRIDE());
                                IrDeclarationOrigin irDeclarationOrigin7 = irDeclarationOrigin2;
                                if (zAreEqual) {
                                    irDeclarationOrigin5 = irDeclarationOrigin7;
                                } else {
                                    default_property_accessor = (!Intrinsics.areEqual(irDeclarationOrigin7, companion2.getDELEGATED_MEMBER()) && (setter instanceof FirDefaultPropertySetter)) ? companion2.getDEFAULT_PROPERTY_ACCESSOR() : irDeclarationOrigin2;
                                }
                                firCallableDeclaration = property;
                                while (true) {
                                    if (!ClassMembersKt.isSubstitutionOverride(firCallableDeclaration) || (firCallableDeclaration.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) {
                                        originalForSubstitutionOverrideAttr = ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration);
                                    } else {
                                        originalForSubstitutionOverrideAttr = null;
                                    }
                                    if (originalForSubstitutionOverrideAttr == null) {
                                        if (ClassMembersKt.isIntersectionOverride(firCallableDeclaration)) {
                                            originalForSubstitutionOverrideAttr = ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration);
                                        } else {
                                            originalForSubstitutionOverrideAttr = null;
                                        }
                                    }
                                    if (originalForSubstitutionOverrideAttr == null) {
                                        return fir2IrCallableDeclarationsGenerator.createIrPropertyAccessor(firPropertyAccessor, firProperty2, irProperty4, setterSymbol, irType3, irDeclarationParent, true, irDeclarationOrigin5, i9, i10, ((FirProperty) firCallableDeclaration).getSetter());
                                    }
                                    firCallableDeclaration = originalForSubstitutionOverrideAttr;
                                }
                            }
                            irDeclarationOrigin5 = default_property_accessor;
                            firCallableDeclaration = property;
                            while (true) {
                                if (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration)) {
                                    originalForSubstitutionOverrideAttr = ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration);
                                } else {
                                    originalForSubstitutionOverrideAttr = ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration);
                                }
                                if (originalForSubstitutionOverrideAttr == null) {
                                    if (ClassMembersKt.isIntersectionOverride(firCallableDeclaration)) {
                                        originalForSubstitutionOverrideAttr = ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration);
                                    } else {
                                        originalForSubstitutionOverrideAttr = null;
                                    }
                                }
                                if (originalForSubstitutionOverrideAttr == null) {
                                    return fir2IrCallableDeclarationsGenerator.createIrPropertyAccessor(firPropertyAccessor, firProperty2, irProperty4, setterSymbol, irType3, irDeclarationParent, true, irDeclarationOrigin5, i9, i10, ((FirProperty) firCallableDeclaration).getSetter());
                                }
                                firCallableDeclaration = originalForSubstitutionOverrideAttr;
                            }
                        }

                        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                            return invoke(((Number) obj).intValue(), ((Number) obj2).intValue());
                        }
                    }));
                } catch (Throwable th2) {
                    th = th2;
                    firProperty = property;
                    String str2 = "Exception was thrown during transformation of " + firProperty.getClass();
                    PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(th);
                    KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments2 = new KotlinIllegalArgumentExceptionWithAttachments(str2, th);
                    ExceptionAttachmentBuilder exceptionAttachmentBuilder2 = new ExceptionAttachmentBuilder();
                    FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder2, "element", firProperty);
                    kotlinIllegalArgumentExceptionWithAttachments2.withAttachment("info.txt", exceptionAttachmentBuilder2.buildString());
                    throw kotlinIllegalArgumentExceptionWithAttachments2;
                }
            }
            fir2IrDeclarationStorage.leaveScope(symbol);
            return irProperty;
        } catch (Throwable th3) {
            th = th3;
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0065  */
    public final IrReplSnippet createIrReplSnippet(FirReplSnippet snippet, IrReplSnippetSymbol symbol) {
        int i;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        snippet.getClass();
        symbol.getClass();
        KtSourceElement source = snippet.getSource();
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
        IrReplSnippetImpl irReplSnippetImpl = new IrReplSnippetImpl(i, endOffset, IrFactoryImpl.INSTANCE, snippet.getSnippetClass().getName(), symbol);
        irReplSnippetImpl.setMetadata(new FirMetadataSource.ReplSnippet(snippet));
        return irReplSnippetImpl;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0065  */
    public final IrScript createIrScript(FirScript script, IrScriptSymbol symbol) {
        int i;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        script.getClass();
        symbol.getClass();
        KtSourceElement source = script.getSource();
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
        IrScriptImpl irScriptImpl = new IrScriptImpl(symbol, script.getName(), IrFactoryImpl.INSTANCE, i, endOffset);
        irScriptImpl.setOrigin(ScriptAndSnippetDetailsKt.getSCRIPT_K2_ORIGIN());
        irScriptImpl.setMetadata(new FirMetadataSource.Script(script));
        irScriptImpl.setImplicitReceiversParameters(CollectionsKt.emptyList());
        irScriptImpl.setProvidedProperties(CollectionsKt.emptyList());
        irScriptImpl.setProvidedPropertiesParameters(CollectionsKt.emptyList());
        return irScriptImpl;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:50:0x00b9  */
    public final IrVariable createIrVariable(FirVariable variable, IrDeclarationParent irParent, IrDeclarationOrigin givenOrigin) throws KotlinIllegalArgumentExceptionWithAttachments {
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        variable.getClass();
        irParent.getClass();
        try {
            IrType irTypeIrTypeForPotentiallyComponentCall$default = VariousUtilsKt.irTypeForPotentiallyComponentCall$default(this, variable, null, 2, null);
            if (givenOrigin == null) {
                if (Intrinsics.areEqual(variable.getName(), SpecialNames.ITERATOR)) {
                    givenOrigin = IrDeclarationOrigin.Companion.getFOR_LOOP_ITERATOR();
                } else if (variable.getName().isSpecial()) {
                    givenOrigin = IrDeclarationOrigin.Companion.getIR_TEMPORARY_VARIABLE();
                } else {
                    givenOrigin = FirDeclarationUtilKt.isDestructuredParameter(variable) ? IrDeclarationOrigin.Companion.getIR_DESTRUCTURED_PARAMETER_VARIABLE() : IrDeclarationOrigin.Companion.getDEFINED();
                }
            }
            IrDeclarationOrigin irDeclarationOrigin = givenOrigin;
            boolean zIsLateInit = variable instanceof FirProperty ? variable.getStatus().isLateInit() : false;
            TokenSet tokenSet = KtTokens.VAL_VAR;
            KtSourceElement source = variable.getSource();
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
            IrVariable irVariableDeclareIrVariable = declareIrVariable(i, endOffset, irDeclarationOrigin, variable.getName(), irTypeIrTypeForPotentiallyComponentCall$default, variable.getIsVar(), false, zIsLateInit);
            irVariableDeclareIrVariable.setParent(irParent);
            return irVariableDeclareIrVariable;
        } catch (Throwable th) {
            String str = "Exception was thrown during transformation of " + variable.getClass();
            PlatformExceptionUtilsKt.rethrowIntellijPlatformExceptionIfNeeded(th);
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments(str, th);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "element", variable);
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final IrProperty generateIrPropertyForSyntheticPropertyReference(FirSimpleSyntheticPropertySymbol propertySymbol, IrDeclarationParent parent) {
        propertySymbol.getClass();
        parent.getClass();
        FirProperty firProperty = (FirProperty) propertySymbol.getFir();
        IrFactoryImpl irFactoryImpl = IrFactoryImpl.INSTANCE;
        IrDeclarationOrigin synthetic_java_property_delegate = IrDeclarationOrigin.Companion.getSYNTHETIC_JAVA_PROPERTY_DELEGATE();
        Name name = firProperty.getName();
        DescriptorVisibility descriptorVisibilityConvertToDescriptorVisibility = getVisibilityConverter().convertToDescriptorVisibility(firProperty.getStatus().getVisibility());
        Modality modality = firProperty.getStatus().getModality();
        if (modality == null) {
            modality = Modality.FINAL;
        }
        IrProperty irPropertyCreateProperty$default = IrFactory.createProperty$default(irFactoryImpl, -1, -1, synthetic_java_property_delegate, name, descriptorVisibilityConvertToDescriptorVisibility, modality, new IrPropertySymbolImpl((PropertyDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null), firProperty.getIsVar(), false, firProperty.getStatus().isLateInit(), firProperty.getDelegate() != null, firProperty.getStatus().isExternal(), (DeserializedContainerSource) null, firProperty.getStatus().isExpect(), false, 20480, (Object) null);
        irPropertyCreateProperty$default.setParent(parent);
        return irPropertyCreateProperty$default;
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

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J9\u0010\u0004\u001a\u0004\u0018\u00010\u00052\u0006\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0000R\u00020\u0006j\u0006\u0010\u0007\u001a\u00020\u0006¢\u0006\u0004\b\u000e\u0010\u000fJ\u0014\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\u0010\f\u001a\u0004\u0018\u00010\rH\u0002¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator$Companion;", Argument.Delimiters.none, "<init>", "()V", "computeDispatchReceiverType", "Lorg/jetbrains/kotlin/ir/types/IrType;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "irFunction", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "firCallable", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "parent", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;", "computeDispatchReceiverType$org_jetbrains_kotlin_fir2ir", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;)Lorg/jetbrains/kotlin/ir/types/IrType;", "computeContainingClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final IrClass computeContainingClass(IrDeclarationParent parent) {
            if (!(parent instanceof IrClass)) {
                return null;
            }
            IrClass irClass = (IrClass) parent;
            if (Fir2IrDeclarationStorageKt.isNonCachedSourceFileFacade(irClass)) {
                return null;
            }
            return irClass;
        }

        public final IrType computeDispatchReceiverType$org_jetbrains_kotlin_fir2ir(Fir2IrComponents fir2IrComponents, IrSimpleFunction irSimpleFunction, FirCallableDeclaration firCallableDeclaration, IrDeclarationParent irDeclarationParent) {
            IrClass irClassComputeContainingClass;
            ConeSimpleKotlinType dispatchReceiverType;
            RigidTypeMarker rigidTypeMarker;
            fir2IrComponents.getClass();
            irSimpleFunction.getClass();
            if (((firCallableDeclaration instanceof FirProperty) && (((FirProperty) firCallableDeclaration).getSymbol() instanceof FirLocalPropertySymbol)) || (irClassComputeContainingClass = computeContainingClass(irDeclarationParent)) == null) {
                return null;
            }
            IrSimpleType defaultType = IrUtilsKt.getDefaultType(irClassComputeContainingClass);
            if (firCallableDeclaration == null || !irSimpleFunction.isFakeOverride()) {
                return defaultType;
            }
            FirCallableDeclaration firCallableDeclaration2 = firCallableDeclaration;
            while (true) {
                FirCallableDeclaration originalForSubstitutionOverrideAttr = (ClassMembersKt.isSubstitutionOverride(firCallableDeclaration2) || (firCallableDeclaration2.getOrigin() instanceof FirDeclarationOrigin.Synthetic)) ? ClassMembersKt.getOriginalForSubstitutionOverrideAttr(firCallableDeclaration2) : null;
                if (originalForSubstitutionOverrideAttr == null) {
                    originalForSubstitutionOverrideAttr = ClassMembersKt.isIntersectionOverride(firCallableDeclaration2) ? ClassMembersKt.getOriginalForIntersectionOverrideAttr(firCallableDeclaration2) : null;
                }
                if (originalForSubstitutionOverrideAttr == null) {
                    break;
                }
                firCallableDeclaration2 = originalForSubstitutionOverrideAttr;
            }
            ConeClassLikeLookupTag coneClassLikeLookupTagContainingClassLookupTag = ClassMembersKt.containingClassLookupTag(firCallableDeclaration2);
            return (coneClassLikeLookupTagContainingClassLookupTag == null || (dispatchReceiverType = firCallableDeclaration.getDispatchReceiverType()) == null || (rigidTypeMarker = (RigidTypeMarker) CollectionsKt.firstOrNull(AbstractTypeChecker.INSTANCE.findCorrespondingSupertypes(TypeCheckerProviderContext.newTypeCheckerState$default(TypeComponentsKt.getTypeContext(fir2IrComponents.getSession()), false, false, false, 4, (Object) null), dispatchReceiverType, coneClassLikeLookupTagContainingClassLookupTag))) == null) ? defaultType : Fir2IrTypeConverterKt.toIrType$default(fir2IrComponents, (ConeRigidType) rigidTypeMarker, (ConversionTypeOrigin) null, 2, (Object) null);
        }

        private Companion() {
        }
    }

    public final IrExternalPackageFragment createExternalPackageFragment$org_jetbrains_kotlin_fir2ir(FqName fqName, FirModuleDescriptor moduleDescriptor) {
        fqName.getClass();
        moduleDescriptor.getClass();
        return createExternalPackageFragment$org_jetbrains_kotlin_fir2ir(new FirPackageFragmentDescriptor(fqName, moduleDescriptor));
    }
}
