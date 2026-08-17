package org.jetbrains.kotlin.fir.backend.generators;

import com.intellij.psi.tree.TokenSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.descriptors.ClassKind;
import org.jetbrains.kotlin.descriptors.DescriptorVisibilities;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.EffectiveVisibility;
import org.jetbrains.kotlin.descriptors.FunctionDescriptor;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.descriptors.PackageFragmentDescriptor;
import org.jetbrains.kotlin.descriptors.SourceElement;
import org.jetbrains.kotlin.descriptors.Visibilities;
import org.jetbrains.kotlin.descriptors.Visibility;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirAnnotationContainer;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirModuleDataKt;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.Fir2IrBuiltinSymbolsContainer;
import org.jetbrains.kotlin.fir.backend.Fir2IrClassifierStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrComponents;
import org.jetbrains.kotlin.fir.backend.Fir2IrConfiguration;
import org.jetbrains.kotlin.fir.backend.Fir2IrConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrDeclarationStorage;
import org.jetbrains.kotlin.fir.backend.Fir2IrExtensions;
import org.jetbrains.kotlin.fir.backend.Fir2IrImplicitCastInserter;
import org.jetbrains.kotlin.fir.backend.Fir2IrIrGeneratedDeclarationsRegistrar;
import org.jetbrains.kotlin.fir.backend.Fir2IrSymbolsMappingForLazyClasses;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverter;
import org.jetbrains.kotlin.fir.backend.Fir2IrTypeConverterKt;
import org.jetbrains.kotlin.fir.backend.Fir2IrVisibilityConverter;
import org.jetbrains.kotlin.fir.backend.FirMetadataSource;
import org.jetbrains.kotlin.fir.backend.FirProviderWithGeneratedFiles;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrClassifiersGenerator;
import org.jetbrains.kotlin.fir.backend.utils.CodeFragmentConversionData;
import org.jetbrains.kotlin.fir.backend.utils.ConversionTypeOrigin;
import org.jetbrains.kotlin.fir.backend.utils.IrElementsCreationUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.OffsetUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.OriginUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.ScopeUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirAnonymousObject;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirCodeFragment;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirEnumEntry;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirMemberDeclaration;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirReplSnippet;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameter;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRef;
import org.jetbrains.kotlin.fir.declarations.FirTypeParameterRefsOwner;
import org.jetbrains.kotlin.fir.declarations.builder.FirRegularClassBuilder;
import org.jetbrains.kotlin.fir.declarations.impl.FirResolvedDeclarationStatusImpl;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.expressions.FirAnonymousObjectExpression;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazyClass;
import org.jetbrains.kotlin.fir.resolve.LookupTagUtilsKt;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.fir.resolve.ToSymbolUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProviderKt;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.symbols.impl.ConeClassLikeLookupTagImpl;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.fir.types.FirTypeRef;
import org.jetbrains.kotlin.fir.types.TypeConstructionUtilsKt;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrEnumEntry;
import org.jetbrains.kotlin.ir.declarations.IrFactory;
import org.jetbrains.kotlin.ir.declarations.IrFile;
import org.jetbrains.kotlin.ir.declarations.IrMutableAnnotationContainer;
import org.jetbrains.kotlin.ir.declarations.IrPackageFragment;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.ir.declarations.IrTypeAlias;
import org.jetbrains.kotlin.ir.declarations.IrTypeParameter;
import org.jetbrains.kotlin.ir.declarations.IrTypeParametersContainer;
import org.jetbrains.kotlin.ir.declarations.impl.IrExternalPackageFragmentImpl;
import org.jetbrains.kotlin.ir.declarations.impl.IrFactoryImpl;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrEnumEntrySymbol;
import org.jetbrains.kotlin.ir.symbols.IrTypeAliasSymbol;
import org.jetbrains.kotlin.ir.symbols.IrTypeParameterSymbol;
import org.jetbrains.kotlin.ir.symbols.impl.IrClassSymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrExternalPackageFragmentSymbolImpl;
import org.jetbrains.kotlin.ir.symbols.impl.IrSimpleFunctionSymbolImpl;
import org.jetbrains.kotlin.ir.util.IdSignature;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.SpecialNames;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000²\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0002Â\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fJ\u0016\u0010\r\u001a\u00020\u000e2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0006J*\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u00020\u00162\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\u0018\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001b2\b\b\u0002\u0010\u001c\u001a\u00020\u0011J\u0014\u0010\u001d\u001a\u00020\u000e*\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0014\u0010\u001e\u001a\u00020\u000e*\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001bH\u0002J\u0014\u0010\u001f\u001a\u00020\u000e*\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u0013H\u0002J\f\u0010 \u001a\u00020!*\u00020\u0013H\u0002J\f\u0010\"\u001a\u00020#*\u00020\u0013H\u0002J\u0016\u0010$\u001a\u00020%2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010&\u001a\u00020#J.\u0010-\u001a\u00020\u00112\u0006\u0010.\u001a\u00020/2\b\b\u0002\u00100\u001a\u0002012\b\b\u0002\u00102\u001a\u0002032\n\b\u0002\u00104\u001a\u0004\u0018\u00010\u0015J\u001e\u00105\u001a\u0002062\u0006\u00107\u001a\u0002082\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u000b\u001a\u000209J\u001e\u0010:\u001a\u00020\u00112\u0006\u0010;\u001a\u00020<2\u0006\u0010=\u001a\u00020>2\u0006\u0010\u000b\u001a\u00020\u0016J\u001e\u0010?\u001a\u00020\u00112\u0006\u0010@\u001a\u00020A2\u0006\u0010B\u001a\u00020C2\u0006\u0010\u000b\u001a\u00020\u0016J*\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020G2\u0006\u00104\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020H2\n\b\u0002\u0010\u0017\u001a\u0004\u0018\u00010\u0018J\u0010\u0010I\u001a\u00020#2\u0006\u0010F\u001a\u00020GH\u0002J'\u0010J\u001a\u00020\u000e2\u0006\u0010K\u001a\u00020L2\u0006\u0010M\u001a\u00020N2\b\b\u0002\u0010O\u001a\u00020PH\u0000¢\u0006\u0002\bQJ\u000e\u0010R\u001a\u00020\u00112\u0006\u0010S\u001a\u00020TR\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u001b\u0010'\u001a\u00020(8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b+\u0010,\u001a\u0004\b)\u0010*R\u0012\u0010U\u001a\u00020VX\u0096\u0005¢\u0006\u0006\u001a\u0004\bW\u0010XR\u0012\u0010Y\u001a\u00020ZX\u0096\u0005¢\u0006\u0006\u001a\u0004\b[\u0010\\R\u0012\u0010]\u001a\u00020^X\u0096\u0005¢\u0006\u0006\u001a\u0004\b_\u0010`R\u0012\u0010a\u001a\u00020bX\u0096\u0005¢\u0006\u0006\u001a\u0004\bc\u0010dR\u0012\u0010e\u001a\u00020fX\u0096\u0005¢\u0006\u0006\u001a\u0004\bg\u0010hR\u0012\u0010i\u001a\u00020jX\u0096\u0005¢\u0006\u0006\u001a\u0004\bk\u0010lR\u0012\u0010m\u001a\u00020nX\u0096\u0005¢\u0006\u0006\u001a\u0004\bo\u0010pR\u0012\u0010q\u001a\u00020\u0000X\u0096\u0005¢\u0006\u0006\u001a\u0004\br\u0010sR\u0012\u0010t\u001a\u00020uX\u0096\u0005¢\u0006\u0006\u001a\u0004\bv\u0010wR\u0012\u0010x\u001a\u00020yX\u0096\u0005¢\u0006\u0006\u001a\u0004\bz\u0010{R\u0012\u0010|\u001a\u00020}X\u0096\u0005¢\u0006\u0006\u001a\u0004\b~\u0010\u007fR\u0016\u0010\u0080\u0001\u001a\u00030\u0081\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0016\u0010\u0084\u0001\u001a\u00030\u0085\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0086\u0001\u0010\u0087\u0001R\u001f\u0010\u0088\u0001\u001a\f\u0012\u0005\u0012\u00030\u008a\u0001\u0018\u00010\u0089\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008b\u0001\u0010\u008c\u0001R\u0016\u0010\u008d\u0001\u001a\u00030\u008e\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008f\u0001\u0010\u0090\u0001R\u0016\u0010\u0091\u0001\u001a\u00030\u0092\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0093\u0001\u0010\u0094\u0001R\u0016\u0010\u0095\u0001\u001a\u00030\u0096\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0097\u0001\u0010\u0098\u0001R\u001d\u0010\u0099\u0001\u001a\n\u0012\u0005\u0012\u00030\u009b\u00010\u009a\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009c\u0001\u0010\u009d\u0001R\u0016\u0010\u009e\u0001\u001a\u00030\u009f\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b \u0001\u0010¡\u0001R\u0016\u0010¢\u0001\u001a\u00030£\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¤\u0001\u0010¥\u0001R\u0016\u0010¦\u0001\u001a\u00030§\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¨\u0001\u0010©\u0001R\u0016\u0010ª\u0001\u001a\u00030«\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¬\u0001\u0010\u00ad\u0001R\u0016\u0010®\u0001\u001a\u00030¯\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b°\u0001\u0010±\u0001R\u0018\u0010²\u0001\u001a\u0005\u0018\u00010³\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b´\u0001\u0010µ\u0001R\u0016\u0010¶\u0001\u001a\u00030·\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¸\u0001\u0010¹\u0001R\u0016\u0010º\u0001\u001a\u00030»\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b¼\u0001\u0010½\u0001R\u0016\u0010¾\u0001\u001a\u00030¿\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\bÀ\u0001\u0010Á\u0001¨\u0006Ã\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;)V", "createIrTypeParameterWithoutBounds", "Lorg/jetbrains/kotlin/ir/declarations/IrTypeParameter;", "typeParameter", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameter;", "index", Argument.Delimiters.none, "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrTypeParameterSymbol;", "initializeTypeParameterBounds", Argument.Delimiters.none, "irTypeParameter", "createIrClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "regularClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "parent", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "predefinedOrigin", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "processClassHeader", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "irClass", "declareTypeParameters", "declareSupertypes", "declareValueClassRepresentation", "enumClassModality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "hasAbstractMembersInScope", Argument.Delimiters.none, "createLocalIrClassOnTheFly", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator$LocalIrClassInfo;", "processMembersOfClassesOnTheFlyImmediately", "temporaryParent", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "getTemporaryParent", "()Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", "temporaryParent$delegate", "Lkotlin/Lazy;", "createAnonymousObject", "anonymousObject", "Lorg/jetbrains/kotlin/fir/declarations/FirAnonymousObject;", "visibility", "Lorg/jetbrains/kotlin/descriptors/Visibility;", ModuleXmlParser.NAME, "Lorg/jetbrains/kotlin/name/Name;", "irParent", "createIrTypeAlias", "Lorg/jetbrains/kotlin/ir/declarations/IrTypeAlias;", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "Lorg/jetbrains/kotlin/ir/symbols/IrTypeAliasSymbol;", "createCodeFragmentClass", "codeFragment", "Lorg/jetbrains/kotlin/fir/declarations/FirCodeFragment;", "containingFile", "Lorg/jetbrains/kotlin/ir/declarations/IrFile;", "createEarlierSnippetClass", "snippet", "Lorg/jetbrains/kotlin/fir/declarations/FirReplSnippet;", "containingPackageFragment", "Lorg/jetbrains/kotlin/ir/declarations/IrPackageFragment;", "createIrEnumEntry", "Lorg/jetbrains/kotlin/ir/declarations/IrEnumEntry;", "enumEntry", "Lorg/jetbrains/kotlin/fir/declarations/FirEnumEntry;", "Lorg/jetbrains/kotlin/ir/symbols/IrEnumEntrySymbol;", "isEnumEntryWhichRequiresSubclass", "setTypeParameters", "irOwner", "Lorg/jetbrains/kotlin/ir/declarations/IrTypeParametersContainer;", "owner", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeParameterRefsOwner;", "typeOrigin", "Lorg/jetbrains/kotlin/fir/backend/utils/ConversionTypeOrigin;", "setTypeParameters$org_jetbrains_kotlin_fir2ir", "createIrClassForNotFoundClass", "classLikeLookupTag", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "()Ljava/util/List;", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "LocalIrClassInfo", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrClassifiersGenerator implements Fir2IrComponents {
    private final Fir2IrComponents c;

    /* JADX INFO: renamed from: temporaryParent$delegate, reason: from kotlin metadata */
    private final Lazy temporaryParent;

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0003¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0003HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\n¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator$LocalIrClassInfo;", Argument.Delimiters.none, "irClass", "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "firClassOrLocalParent", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "irClassOrLocalParent", "<init>", "(Lorg/jetbrains/kotlin/ir/declarations/IrClass;Lorg/jetbrains/kotlin/fir/declarations/FirClass;Lorg/jetbrains/kotlin/ir/declarations/IrClass;)V", "getIrClass", "()Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "getFirClassOrLocalParent", "()Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "getIrClassOrLocalParent", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class LocalIrClassInfo {
        private final FirClass firClassOrLocalParent;
        private final IrClass irClass;
        private final IrClass irClassOrLocalParent;

        public LocalIrClassInfo(IrClass irClass, FirClass firClass, IrClass irClass2) {
            irClass.getClass();
            firClass.getClass();
            irClass2.getClass();
            this.irClass = irClass;
            this.firClassOrLocalParent = firClass;
            this.irClassOrLocalParent = irClass2;
        }

        public static /* synthetic */ LocalIrClassInfo copy$default(LocalIrClassInfo localIrClassInfo, IrClass irClass, FirClass firClass, IrClass irClass2, int i, Object obj) {
            if ((i & 1) != 0) {
                irClass = localIrClassInfo.irClass;
            }
            if ((i & 2) != 0) {
                firClass = localIrClassInfo.firClassOrLocalParent;
            }
            if ((i & 4) != 0) {
                irClass2 = localIrClassInfo.irClassOrLocalParent;
            }
            return localIrClassInfo.copy(irClass, firClass, irClass2);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final IrClass getIrClass() {
            return this.irClass;
        }

        /* JADX INFO: renamed from: component2, reason: from getter */
        public final FirClass getFirClassOrLocalParent() {
            return this.firClassOrLocalParent;
        }

        /* JADX INFO: renamed from: component3, reason: from getter */
        public final IrClass getIrClassOrLocalParent() {
            return this.irClassOrLocalParent;
        }

        public final LocalIrClassInfo copy(IrClass irClass, FirClass firClassOrLocalParent, IrClass irClassOrLocalParent) {
            irClass.getClass();
            firClassOrLocalParent.getClass();
            irClassOrLocalParent.getClass();
            return new LocalIrClassInfo(irClass, firClassOrLocalParent, irClassOrLocalParent);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof LocalIrClassInfo)) {
                return false;
            }
            LocalIrClassInfo localIrClassInfo = (LocalIrClassInfo) other;
            return Intrinsics.areEqual(this.irClass, localIrClassInfo.irClass) && Intrinsics.areEqual(this.firClassOrLocalParent, localIrClassInfo.firClassOrLocalParent) && Intrinsics.areEqual(this.irClassOrLocalParent, localIrClassInfo.irClassOrLocalParent);
        }

        public final FirClass getFirClassOrLocalParent() {
            return this.firClassOrLocalParent;
        }

        public final IrClass getIrClass() {
            return this.irClass;
        }

        public final IrClass getIrClassOrLocalParent() {
            return this.irClassOrLocalParent;
        }

        public int hashCode() {
            return (((this.irClass.hashCode() * 31) + this.firClassOrLocalParent.hashCode()) * 31) + this.irClassOrLocalParent.hashCode();
        }

        public String toString() {
            return "LocalIrClassInfo(irClass=" + this.irClass + ", firClassOrLocalParent=" + this.firClassOrLocalParent + ", irClassOrLocalParent=" + this.irClassOrLocalParent + ')';
        }
    }

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class WhenMappings {
        public static final /* synthetic */ int[] $EnumSwitchMapping$0;

        static {
            int[] iArr = new int[ClassKind.values().length];
            try {
                iArr[ClassKind.ENUM_CLASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                iArr[ClassKind.ANNOTATION_CLASS.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            $EnumSwitchMapping$0 = iArr;
        }
    }

    public Fir2IrClassifiersGenerator(Fir2IrComponents fir2IrComponents) {
        fir2IrComponents.getClass();
        this.c = fir2IrComponents;
        this.temporaryParent = LazyKt.lazy(new Function0() { // from class: av4
            public final Object invoke() {
                return Fir2IrClassifiersGenerator.c(this.b);
            }
        });
    }

    public static Unit b(Ref.BooleanRef booleanRef, FirVariableSymbol firVariableSymbol) {
        firVariableSymbol.getClass();
        if (firVariableSymbol.getResolvedStatus().getModality() == Modality.ABSTRACT) {
            booleanRef.element = true;
        }
        return Unit.INSTANCE;
    }

    public static IrSimpleFunction c(Fir2IrClassifiersGenerator fir2IrClassifiersGenerator) {
        IrFactoryImpl irFactoryImpl = IrFactoryImpl.INSTANCE;
        IrDeclarationOrigin defined = IrDeclarationOrigin.Companion.getDEFINED();
        Name nameSpecial = Name.special("<stub>");
        nameSpecial.getClass();
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.PRIVATE;
        descriptorVisibility.getClass();
        IrSimpleFunction irSimpleFunctionCreateSimpleFunction$default = IrFactory.createSimpleFunction$default(irFactoryImpl, -1, -1, defined, nameSpecial, descriptorVisibility, false, false, fir2IrClassifiersGenerator.getBuiltins().getUnitType(), Modality.FINAL, new IrSimpleFunctionSymbolImpl((FunctionDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null), false, false, false, false, false, (DeserializedContainerSource) null, false, 98304, (Object) null);
        irSimpleFunctionCreateSimpleFunction$default.setParent(new IrExternalPackageFragmentImpl(new IrExternalPackageFragmentSymbolImpl((PackageFragmentDescriptor) null, 1, (DefaultConstructorMarker) null), FqName.ROOT));
        return irSimpleFunctionCreateSimpleFunction$default;
    }

    public static /* synthetic */ IrClass createAnonymousObject$default(Fir2IrClassifiersGenerator fir2IrClassifiersGenerator, FirAnonymousObject firAnonymousObject, Visibility visibility, Name name, IrDeclarationParent irDeclarationParent, int i, Object obj) {
        if ((i & 2) != 0) {
            visibility = Visibilities.Local.INSTANCE;
        }
        if ((i & 4) != 0) {
            name = SpecialNames.NO_NAME_PROVIDED;
        }
        if ((i & 8) != 0) {
            irDeclarationParent = null;
        }
        return fir2IrClassifiersGenerator.createAnonymousObject(firAnonymousObject, visibility, name, irDeclarationParent);
    }

    public static /* synthetic */ IrClass createIrClass$default(Fir2IrClassifiersGenerator fir2IrClassifiersGenerator, FirRegularClass firRegularClass, IrDeclarationParent irDeclarationParent, IrClassSymbol irClassSymbol, IrDeclarationOrigin irDeclarationOrigin, int i, Object obj) {
        if ((i & 8) != 0) {
            irDeclarationOrigin = null;
        }
        return fir2IrClassifiersGenerator.createIrClass(firRegularClass, irDeclarationParent, irClassSymbol, irDeclarationOrigin);
    }

    public static /* synthetic */ IrEnumEntry createIrEnumEntry$default(Fir2IrClassifiersGenerator fir2IrClassifiersGenerator, FirEnumEntry firEnumEntry, IrClass irClass, IrEnumEntrySymbol irEnumEntrySymbol, IrDeclarationOrigin irDeclarationOrigin, int i, Object obj) {
        if ((i & 8) != 0) {
            irDeclarationOrigin = null;
        }
        return fir2IrClassifiersGenerator.createIrEnumEntry(firEnumEntry, irClass, irEnumEntrySymbol, irDeclarationOrigin);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static FirClass d(Fir2IrClassifiersGenerator fir2IrClassifiersGenerator, FirClass firClass) {
        FirClassSymbol<?> classSymbol;
        FirClass firClass2;
        firClass.getClass();
        ConeClassLikeLookupTag containingClassForLocalAttr = ClassMembersKt.getContainingClassForLocalAttr(firClass);
        if (containingClassForLocalAttr == null || (classSymbol = ToSymbolUtilsKt.toClassSymbol(fir2IrClassifiersGenerator, containingClassForLocalAttr)) == null || (firClass2 = (FirClass) classSymbol.getFir()) == null || !firClass2.getDeclarations().contains(firClass)) {
            return null;
        }
        return firClass2;
    }

    private final void declareSupertypes(IrClass irClass, FirClass firClass) {
        List<FirTypeRef> superTypeRefs = firClass.getSuperTypeRefs();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(superTypeRefs, 10));
        Iterator<T> it = superTypeRefs.iterator();
        while (it.hasNext()) {
            arrayList.add(Fir2IrTypeConverterKt.toIrType$default(this, (FirTypeRef) it.next(), (ConversionTypeOrigin) null, 2, (Object) null));
        }
        irClass.setSuperTypes(arrayList);
    }

    private final void declareTypeParameters(IrClass irClass, FirClass firClass) {
        getClassifierStorage().preCacheTypeParameters$org_jetbrains_kotlin_fir2ir(firClass);
        setTypeParameters$org_jetbrains_kotlin_fir2ir$default(this, irClass, firClass, null, 4, null);
    }

    private final void declareValueClassRepresentation(IrClass irClass, FirRegularClass firRegularClass) {
        if (irClass instanceof Fir2IrLazyClass) {
            return;
        }
        irClass.setValueClassRepresentation(IrElementsCreationUtilsKt.computeValueClassRepresentation(this, firRegularClass));
    }

    public static Unit e(Ref.BooleanRef booleanRef, FirNamedFunctionSymbol firNamedFunctionSymbol) {
        firNamedFunctionSymbol.getClass();
        if (firNamedFunctionSymbol.getResolvedStatus().getModality() == Modality.ABSTRACT) {
            booleanRef.element = true;
        }
        return Unit.INSTANCE;
    }

    private final Modality enumClassModality(FirRegularClass firRegularClass) {
        List<FirDeclaration> declarations = firRegularClass.getDeclarations();
        if (!(declarations instanceof Collection) || !declarations.isEmpty()) {
            for (FirDeclaration firDeclaration : declarations) {
                if (firDeclaration instanceof FirCallableDeclaration) {
                    Modality modality = ((FirMemberDeclaration) firDeclaration).getStatus().getModality();
                    Modality modality2 = Modality.ABSTRACT;
                    if (modality == modality2) {
                        return modality2;
                    }
                }
            }
        }
        List<FirDeclaration> declarations2 = firRegularClass.getDeclarations();
        if (!(declarations2 instanceof Collection) || !declarations2.isEmpty()) {
            for (FirDeclaration firDeclaration2 : declarations2) {
                if ((firDeclaration2 instanceof FirEnumEntry) && isEnumEntryWhichRequiresSubclass((FirEnumEntry) firDeclaration2)) {
                    return hasAbstractMembersInScope(firRegularClass) ? Modality.ABSTRACT : Modality.OPEN;
                }
            }
        }
        return Modality.FINAL;
    }

    private final IrSimpleFunction getTemporaryParent() {
        return (IrSimpleFunction) this.temporaryParent.getValue();
    }

    private final boolean hasAbstractMembersInScope(FirRegularClass firRegularClass) {
        FirTypeScope firTypeScopeUnsubstitutedScope = ScopeUtilsKt.unsubstitutedScope(this, firRegularClass);
        Set<Name> callableNames = firTypeScopeUnsubstitutedScope.getCallableNames();
        final Ref.BooleanRef booleanRef = new Ref.BooleanRef();
        for (Name name : callableNames) {
            firTypeScopeUnsubstitutedScope.processFunctionsByName(name, new Function1() { // from class: yu4
                public final Object invoke(Object obj) {
                    return Fir2IrClassifiersGenerator.e(booleanRef, (FirNamedFunctionSymbol) obj);
                }
            });
            if (booleanRef.element) {
                return true;
            }
            firTypeScopeUnsubstitutedScope.processPropertiesByName(name, new Function1() { // from class: zu4
                public final Object invoke(Object obj) {
                    return Fir2IrClassifiersGenerator.b(booleanRef, (FirVariableSymbol) obj);
                }
            });
            if (booleanRef.element) {
                return true;
            }
        }
        return false;
    }

    private final boolean isEnumEntryWhichRequiresSubclass(FirEnumEntry enumEntry) {
        FirExpression initializer = enumEntry.getInitializer();
        if (!(initializer instanceof FirAnonymousObjectExpression)) {
            return false;
        }
        List<FirDeclaration> declarations = ((FirAnonymousObjectExpression) initializer).getAnonymousObject().getDeclarations();
        if ((declarations instanceof Collection) && declarations.isEmpty()) {
            return false;
        }
        Iterator<T> it = declarations.iterator();
        while (it.hasNext()) {
            if (!(((FirDeclaration) it.next()) instanceof FirConstructor)) {
                return true;
            }
        }
        return false;
    }

    public static /* synthetic */ IrClass processClassHeader$default(Fir2IrClassifiersGenerator fir2IrClassifiersGenerator, FirClass firClass, IrClass irClass, int i, Object obj) {
        if ((i & 2) != 0) {
            irClass = fir2IrClassifiersGenerator.getClassifierStorage().getIrClass(firClass);
        }
        return fir2IrClassifiersGenerator.processClassHeader(firClass, irClass);
    }

    public static /* synthetic */ void setTypeParameters$org_jetbrains_kotlin_fir2ir$default(Fir2IrClassifiersGenerator fir2IrClassifiersGenerator, IrTypeParametersContainer irTypeParametersContainer, FirTypeParameterRefsOwner firTypeParameterRefsOwner, ConversionTypeOrigin conversionTypeOrigin, int i, Object obj) {
        if ((i & 4) != 0) {
            conversionTypeOrigin = ConversionTypeOrigin.DEFAULT;
        }
        fir2IrClassifiersGenerator.setTypeParameters$org_jetbrains_kotlin_fir2ir(irTypeParametersContainer, firTypeParameterRefsOwner, conversionTypeOrigin);
    }

    /* JADX WARN: Code duplicated, block: B:29:0x0070  */
    public final IrClass createAnonymousObject(FirAnonymousObject anonymousObject, Visibility visibility, Name name, IrDeclarationParent irParent) {
        int startOffset;
        Integer numStartOffsetSkippingComments;
        anonymousObject.getClass();
        visibility.getClass();
        name.getClass();
        IrDeclarationOrigin defined = IrDeclarationOrigin.Companion.getDEFINED();
        Modality modality = Modality.FINAL;
        KtSourceElement source = anonymousObject.getSource();
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
        IrClass irClassCreateClass$default = IrFactory.createClass$default(IrFactoryImpl.INSTANCE, startOffset, endOffset, defined, name, this.c.getVisibilityConverter().convertToDescriptorVisibility(visibility), new IrClassSymbolImpl((ClassDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null), anonymousObject.getClassKind(), modality, false, false, false, false, false, false, false, false, (SourceElement) null, 130816, (Object) null);
        irClassCreateClass$default.setMetadata(new FirMetadataSource.Class(anonymousObject));
        if (irParent != null) {
            irClassCreateClass$default.setParent(irParent);
        }
        return irClassCreateClass$default;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0074  */
    public final IrClass createCodeFragmentClass(FirCodeFragment codeFragment, IrFile containingFile, IrClassSymbol symbol) {
        int i;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        codeFragment.getClass();
        containingFile.getClass();
        symbol.getClass();
        CodeFragmentConversionData codeFragmentConversionData = getExtensions().codeFragmentConversionData(codeFragment);
        KtSourceElement source = codeFragment.getSource();
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
        IrFactoryImpl irFactoryImpl = IrFactoryImpl.INSTANCE;
        IrDeclarationOrigin defined = IrDeclarationOrigin.Companion.getDEFINED();
        Name shortClassName = codeFragmentConversionData.getClassId().getShortClassName();
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.PUBLIC;
        descriptorVisibility.getClass();
        IrClass irClassCreateClass$default = IrFactory.createClass$default(irFactoryImpl, i, endOffset, defined, shortClassName, descriptorVisibility, symbol, ClassKind.CLASS, Modality.FINAL, false, false, false, false, false, false, false, false, (SourceElement) null, 65536, (Object) null);
        irClassCreateClass$default.setMetadata(new FirMetadataSource.CodeFragment(codeFragment));
        Fir2IrCallableDeclarationsGeneratorKt.setParent(irClassCreateClass$default, containingFile);
        Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irClassCreateClass$default, containingFile);
        irClassCreateClass$default.setTypeParameters(CollectionsKt.emptyList());
        IrElementsCreationUtilsKt.setThisReceiver(this, irClassCreateClass$default, CollectionsKt.emptyList());
        irClassCreateClass$default.setSuperTypes(CollectionsKt.listOf(getBuiltins().getAnyType()));
        return irClassCreateClass$default;
    }

    public final IrClass createEarlierSnippetClass(FirReplSnippet snippet, IrPackageFragment containingPackageFragment, IrClassSymbol symbol) {
        snippet.getClass();
        containingPackageFragment.getClass();
        symbol.getClass();
        Name name = snippet.getSnippetClass().getName();
        FirRegularClassSymbol firRegularClassSymbol = new FirRegularClassSymbol(new ClassId(containingPackageFragment.getPackageFqName(), name));
        FirRegularClassBuilder firRegularClassBuilder = new FirRegularClassBuilder();
        firRegularClassBuilder.setModuleData(snippet.getModuleData());
        firRegularClassBuilder.setOrigin(FirDeclarationOrigin.FromOtherReplSnippet.INSTANCE);
        firRegularClassBuilder.setName(name);
        firRegularClassBuilder.setStatus(new FirResolvedDeclarationStatusImpl(Visibilities.Public.INSTANCE, Modality.FINAL, EffectiveVisibility.Public.INSTANCE));
        firRegularClassBuilder.setClassKind(ClassKind.CLASS);
        firRegularClassBuilder.setSymbol(firRegularClassSymbol);
        firRegularClassBuilder.getSuperTypeRefs().add(getSession().getBuiltinTypes().getAnyType());
        firRegularClassBuilder.setResolvePhase(FirResolvePhase.INSTANCE.getANALYZED_DEPENDENCIES());
        firRegularClassBuilder.setScopeProvider(FirKotlinScopeProviderKt.getKotlinScopeProvider(getSession()));
        FirRegularClass firRegularClassBuild = firRegularClassBuilder.mo288build();
        ConeClassLikeLookupTag lookupTag = firRegularClassBuild.getSymbol().getLookupTag();
        ConeClassLikeLookupTagImpl coneClassLikeLookupTagImpl = lookupTag instanceof ConeClassLikeLookupTagImpl ? (ConeClassLikeLookupTagImpl) lookupTag : null;
        if (coneClassLikeLookupTagImpl != null) {
            LookupTagUtilsKt.bindSymbolToLookupTag(coneClassLikeLookupTagImpl, getSession(), firRegularClassSymbol);
        }
        return getLazyDeclarationsGenerator().createIrLazyClass(firRegularClassBuild, containingPackageFragment, symbol);
    }

    /* JADX WARN: Code duplicated, block: B:40:0x0099  */
    public final IrClass createIrClass(FirRegularClass regularClass, IrDeclarationParent parent, IrClassSymbol symbol, IrDeclarationOrigin predefinedOrigin) {
        Modality modalityEnumClassModality;
        int i;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        regularClass.getClass();
        parent.getClass();
        symbol.getClass();
        Visibility visibility = regularClass.getStatus().getVisibility();
        int i2 = WhenMappings.$EnumSwitchMapping$0[regularClass.getClassKind().ordinal()];
        boolean z = true;
        if (i2 == 1) {
            modalityEnumClassModality = enumClassModality(regularClass);
        } else if (i2 != 2) {
            modalityEnumClassModality = regularClass.getStatus().getModality();
            if (modalityEnumClassModality == null) {
                modalityEnumClassModality = Modality.FINAL;
            }
        } else {
            modalityEnumClassModality = Modality.OPEN;
        }
        Modality modality = modalityEnumClassModality;
        KtSourceElement source = regularClass.getSource();
        int endOffset = -1;
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            i = -1;
        } else {
            if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
                i = -1;
            } else {
                if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
                    i = -1;
                } else {
                    if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
                        i = -1;
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
        IrFactoryImpl irFactoryImpl = IrFactoryImpl.INSTANCE;
        IrDeclarationOrigin irDeclarationOriginComputeIrOrigin$default = OriginUtilsKt.computeIrOrigin$default(regularClass, predefinedOrigin, null, null, 6, null);
        Name name = regularClass.getName();
        DescriptorVisibility descriptorVisibilityConvertToDescriptorVisibility = this.c.getVisibilityConverter().convertToDescriptorVisibility(visibility);
        ClassKind classKind = regularClass.getClassKind();
        boolean zIsEffectivelyExternal = Fir2IrCallableDeclarationsGeneratorKt.isEffectivelyExternal(regularClass, parent);
        boolean zIsCompanion = regularClass.getStatus().isCompanion();
        boolean zIsInner = regularClass.getStatus().isInner();
        boolean zIsData = regularClass.getStatus().isData();
        if (!regularClass.getStatus().isInline() && !regularClass.getStatus().isValue()) {
            z = false;
        }
        IrClass irClassCreateClass$default = IrFactory.createClass$default(irFactoryImpl, i, endOffset, irDeclarationOriginComputeIrOrigin$default, name, descriptorVisibilityConvertToDescriptorVisibility, symbol, classKind, modality, zIsEffectivelyExternal, zIsCompanion, zIsInner, zIsData, z, regularClass.getStatus().isExpect(), regularClass.getStatus().isFun(), ClassMembersKt.getHasEnumEntries(regularClass), (SourceElement) null, 65536, (Object) null);
        irClassCreateClass$default.setMetadata(new FirMetadataSource.Class(regularClass));
        Fir2IrCallableDeclarationsGeneratorKt.setParent(irClassCreateClass$default, parent);
        if (regularClass.getIsLocal() && FirDeclarationUtilKt.getClassId(regularClass).getOuterClassId() == null) {
            return irClassCreateClass$default;
        }
        Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irClassCreateClass$default, parent);
        return irClassCreateClass$default;
    }

    public final IrClass createIrClassForNotFoundClass(ConeClassLikeLookupTag classLikeLookupTag) {
        classLikeLookupTag.getClass();
        ClassId classId = classLikeLookupTag.getClassId();
        ClassId outerClassId = classId.getOuterClassId();
        IrClass irClassForNotFoundClass = outerClassId != null ? getClassifierStorage().getIrClassForNotFoundClass(TypeConstructionUtilsKt.toLookupTag(outerClassId)) : null;
        if (irClassForNotFoundClass == null) {
            irClassForNotFoundClass = Fir2IrDeclarationStorage.getIrExternalPackageFragment$default(getDeclarationStorage(), classId.getPackageFqName(), (FirModuleData) CollectionsKt.first(FirModuleDataKt.getModuleData(getSession()).getDependencies()), null, 4, null);
        }
        IrFactoryImpl irFactoryImpl = IrFactoryImpl.INSTANCE;
        IrDeclarationOrigin ir_external_declaration_stub = IrDeclarationOrigin.Companion.getIR_EXTERNAL_DECLARATION_STUB();
        Name shortClassName = classId.getShortClassName();
        DescriptorVisibility descriptorVisibility = DescriptorVisibilities.DEFAULT_VISIBILITY;
        descriptorVisibility.getClass();
        IrClass irClassCreateClass$default = IrFactory.createClass$default(irFactoryImpl, -1, -1, ir_external_declaration_stub, shortClassName, descriptorVisibility, new IrClassSymbolImpl((ClassDescriptor) null, (IdSignature) null, 3, (DefaultConstructorMarker) null), ClassKind.CLASS, Modality.FINAL, false, false, false, false, false, false, false, false, (SourceElement) null, 130816, (Object) null);
        Fir2IrCallableDeclarationsGeneratorKt.setParent(irClassCreateClass$default, irClassForNotFoundClass);
        Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irClassCreateClass$default, irClassForNotFoundClass);
        irClassCreateClass$default.setTypeParameters(CollectionsKt.emptyList());
        IrElementsCreationUtilsKt.setThisReceiver(this, irClassCreateClass$default, CollectionsKt.emptyList());
        irClassCreateClass$default.setSuperTypes(CollectionsKt.listOf(getBuiltins().getAnyType()));
        return irClassCreateClass$default;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:33:0x006c  */
    public final IrEnumEntry createIrEnumEntry(FirEnumEntry enumEntry, IrClass irParent, IrEnumEntrySymbol symbol, IrDeclarationOrigin predefinedOrigin) throws KotlinIllegalArgumentExceptionWithAttachments {
        int i;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        enumEntry.getClass();
        irParent.getClass();
        symbol.getClass();
        TokenSet tokenSet = enumEntry != null ? KtTokens.VAL_VAR : null;
        KtSourceElement source = enumEntry.getSource();
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
        IrDeclarationOrigin irDeclarationOriginComputeIrOrigin$default = OriginUtilsKt.computeIrOrigin$default(enumEntry, predefinedOrigin, null, null, 6, null);
        IrEnumEntry irEnumEntryCreateEnumEntry = IrFactoryImpl.INSTANCE.createEnumEntry(i, endOffset, irDeclarationOriginComputeIrOrigin$default, enumEntry.getName(), symbol);
        getDeclarationStorage().enterScope(irEnumEntryCreateEnumEntry.getSymbol());
        Fir2IrCallableDeclarationsGeneratorKt.setParent(irEnumEntryCreateEnumEntry, irParent);
        Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irEnumEntryCreateEnumEntry, irParent);
        if (isEnumEntryWhichRequiresSubclass(enumEntry)) {
            Fir2IrClassifierStorage classifierStorage = getClassifierStorage();
            FirExpression initializer = enumEntry.getInitializer();
            initializer.getClass();
            irEnumEntryCreateEnumEntry.setCorrespondingClass(classifierStorage.getIrAnonymousObjectForEnumEntry(((FirAnonymousObjectExpression) initializer).getAnonymousObject(), enumEntry.getName(), irParent));
        }
        IrDeclarationOrigin.Companion companion = IrDeclarationOrigin.Companion;
        if (Intrinsics.areEqual(irDeclarationOriginComputeIrOrigin$default, companion.getIR_EXTERNAL_DECLARATION_STUB()) || Intrinsics.areEqual(irDeclarationOriginComputeIrOrigin$default, companion.getIR_EXTERNAL_JAVA_DECLARATION_STUB())) {
            getAnnotationGenerator().generate((IrMutableAnnotationContainer) irEnumEntryCreateEnumEntry, (FirAnnotationContainer) enumEntry);
        }
        getDeclarationStorage().leaveScope(irEnumEntryCreateEnumEntry.getSymbol());
        return irEnumEntryCreateEnumEntry;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x006e  */
    public final IrTypeAlias createIrTypeAlias(FirTypeAlias typeAlias, IrDeclarationParent parent, IrTypeAliasSymbol symbol) {
        int i;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        typeAlias.getClass();
        parent.getClass();
        symbol.getClass();
        KtSourceElement source = typeAlias.getSource();
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
        getClassifierStorage().preCacheTypeParameters$org_jetbrains_kotlin_fir2ir(typeAlias);
        IrTypeAlias irTypeAliasCreateTypeAlias = IrFactoryImpl.INSTANCE.createTypeAlias(i, endOffset, IrDeclarationOrigin.Companion.getDEFINED(), typeAlias.getName(), this.c.getVisibilityConverter().convertToDescriptorVisibility(typeAlias.getStatus().getVisibility()), symbol, typeAlias.getStatus().isActual(), Fir2IrTypeConverterKt.toIrType$default(this, typeAlias.getExpandedTypeRef(), (ConversionTypeOrigin) null, 2, (Object) null));
        irTypeAliasCreateTypeAlias.setParent(parent);
        irTypeAliasCreateTypeAlias.setMetadata(new FirMetadataSource.TypeAlias(typeAlias));
        setTypeParameters$org_jetbrains_kotlin_fir2ir$default(this, irTypeAliasCreateTypeAlias, typeAlias, null, 4, null);
        Fir2IrCallableDeclarationsGeneratorKt.setParent(irTypeAliasCreateTypeAlias, parent);
        Fir2IrCallableDeclarationsGeneratorKt.addDeclarationToParent(irTypeAliasCreateTypeAlias, parent);
        return irTypeAliasCreateTypeAlias;
    }

    /* JADX WARN: Code duplicated, block: B:32:0x0072  */
    public final IrTypeParameter createIrTypeParameterWithoutBounds(FirTypeParameter typeParameter, int index, IrTypeParameterSymbol symbol) {
        int i;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        typeParameter.getClass();
        symbol.getClass();
        if (index < 0) {
            w01.a("Failed requirement.");
            return null;
        }
        IrDeclarationOrigin irDeclarationOriginComputeIrOrigin$default = OriginUtilsKt.computeIrOrigin$default(typeParameter, null, null, null, 7, null);
        KtSourceElement source = typeParameter.getSource();
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
        IrTypeParameter irTypeParameterCreateTypeParameter = IrFactoryImpl.INSTANCE.createTypeParameter(i, endOffset, irDeclarationOriginComputeIrOrigin$default, typeParameter.getName(), symbol, typeParameter.getVariance(), index, typeParameter.getIsReified());
        getAnnotationGenerator().generate((IrMutableAnnotationContainer) irTypeParameterCreateTypeParameter, (FirAnnotationContainer) typeParameter);
        return irTypeParameterCreateTypeParameter;
    }

    public final LocalIrClassInfo createLocalIrClassOnTheFly(FirClass klass, boolean processMembersOfClassesOnTheFlyImmediately) {
        klass.getClass();
        FirClass firClass = (FirClass) SequencesKt.last(SequencesKt.generateSequence(klass, new Function1() { // from class: bv4
            public final Object invoke(Object obj) {
                return Fir2IrClassifiersGenerator.d(this.b, (FirClass) obj);
            }
        }));
        IrClass irClassProcessLocalClassAndNestedClassesOnTheFly = getConverter().processLocalClassAndNestedClassesOnTheFly(firClass, getTemporaryParent());
        if (processMembersOfClassesOnTheFlyImmediately) {
            getConverter().processClassMembers$org_jetbrains_kotlin_fir2ir(firClass, irClassProcessLocalClassAndNestedClassesOnTheFly);
        }
        return new LocalIrClassInfo(firClass == klass ? irClassProcessLocalClassAndNestedClassesOnTheFly : getClassifierStorage().getIrClass(klass), firClass, irClassProcessLocalClassAndNestedClassesOnTheFly);
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

    public final void initializeTypeParameterBounds(FirTypeParameter typeParameter, IrTypeParameter irTypeParameter) {
        typeParameter.getClass();
        irTypeParameter.getClass();
        List<FirTypeRef> bounds = typeParameter.getBounds();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(bounds, 10));
        Iterator<T> it = bounds.iterator();
        while (it.hasNext()) {
            arrayList.add(Fir2IrTypeConverterKt.toIrType$default(this, (FirTypeRef) it.next(), (ConversionTypeOrigin) null, 2, (Object) null));
        }
        irTypeParameter.setSuperTypes(arrayList);
    }

    public final IrClass processClassHeader(FirClass klass, IrClass irClass) {
        klass.getClass();
        irClass.getClass();
        declareTypeParameters(irClass, klass);
        IrElementsCreationUtilsKt.setThisReceiver(this, irClass, klass.getTypeParameters());
        declareSupertypes(irClass, klass);
        if (klass instanceof FirRegularClass) {
            declareValueClassRepresentation(irClass, (FirRegularClass) klass);
        }
        return irClass;
    }

    public final void setTypeParameters$org_jetbrains_kotlin_fir2ir(IrTypeParametersContainer irOwner, FirTypeParameterRefsOwner owner, ConversionTypeOrigin typeOrigin) {
        IrTypeParameter irTypeParameter$org_jetbrains_kotlin_fir2ir;
        irOwner.getClass();
        owner.getClass();
        typeOrigin.getClass();
        List<FirTypeParameterRef> typeParameters = owner.getTypeParameters();
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Object obj : typeParameters) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            FirTypeParameterRef firTypeParameterRef = (FirTypeParameterRef) obj;
            if (firTypeParameterRef instanceof FirTypeParameter) {
                FirTypeParameter firTypeParameter = (FirTypeParameter) firTypeParameterRef;
                irTypeParameter$org_jetbrains_kotlin_fir2ir = getClassifierStorage().getIrTypeParameter$org_jetbrains_kotlin_fir2ir(firTypeParameter, i, typeOrigin);
                irTypeParameter$org_jetbrains_kotlin_fir2ir.setParent(irOwner);
                if (irTypeParameter$org_jetbrains_kotlin_fir2ir.getSuperTypes().isEmpty()) {
                    List<FirTypeRef> bounds = firTypeParameter.getBounds();
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(bounds, 10));
                    Iterator<T> it = bounds.iterator();
                    while (it.hasNext()) {
                        arrayList2.add(Fir2IrTypeConverterKt.toIrType(this, (FirTypeRef) it.next(), typeOrigin));
                    }
                    irTypeParameter$org_jetbrains_kotlin_fir2ir.setSuperTypes(arrayList2);
                }
            } else {
                irTypeParameter$org_jetbrains_kotlin_fir2ir = null;
            }
            if (irTypeParameter$org_jetbrains_kotlin_fir2ir != null) {
                arrayList.add(irTypeParameter$org_jetbrains_kotlin_fir2ir);
            }
            i = i2;
        }
        irOwner.setTypeParameters(arrayList);
    }
}
