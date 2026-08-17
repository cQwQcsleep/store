package org.jetbrains.kotlin.fir.backend.generators;

import com.intellij.psi.tree.TokenSet;
import defpackage.f2f;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtFakeSourceElementKind;
import org.jetbrains.kotlin.KtSourceElement;
import org.jetbrains.kotlin.KtSourceElementKt;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
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
import org.jetbrains.kotlin.fir.backend.FirProviderWithGeneratedFiles;
import org.jetbrains.kotlin.fir.backend.PropertySymbols;
import org.jetbrains.kotlin.fir.backend.utils.ConversionTypeOrigin;
import org.jetbrains.kotlin.fir.backend.utils.IrElementsCreationUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.OffsetUtilsKt;
import org.jetbrains.kotlin.fir.backend.utils.OriginUtilsKt;
import org.jetbrains.kotlin.fir.declarations.FirConstructor;
import org.jetbrains.kotlin.fir.declarations.FirField;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.declarations.FirNamedFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirReceiverParameter;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.declarations.FirValueParameter;
import org.jetbrains.kotlin.fir.declarations.utils.FirDeclarationUtilKt;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazyClass;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazyConstructor;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazyField;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazyProperty;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazyPropertyForPureField;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazySimpleFunction;
import org.jetbrains.kotlin.fir.lazy.Fir2IrLazyTypeAlias;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationOrigin;
import org.jetbrains.kotlin.ir.declarations.IrDeclarationParent;
import org.jetbrains.kotlin.ir.declarations.IrParameterKind;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.ir.symbols.IrClassSymbol;
import org.jetbrains.kotlin.ir.symbols.IrConstructorSymbol;
import org.jetbrains.kotlin.ir.symbols.IrFieldSymbol;
import org.jetbrains.kotlin.ir.symbols.IrPropertySymbol;
import org.jetbrains.kotlin.ir.symbols.IrSimpleFunctionSymbol;
import org.jetbrains.kotlin.ir.symbols.IrTypeAliasSymbol;
import org.jetbrains.kotlin.ir.types.IrType;
import org.jetbrains.kotlin.ir.util.AdditionalIrUtilsKt;
import org.jetbrains.kotlin.ir.util.IrUtilsKt;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.lexer.KtTokens;
import org.jetbrains.kotlin.name.StandardClassIds;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000â\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0001¢\u0006\u0004\b\u0003\u0010\u0004J5\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u0010H\u0000¢\u0006\u0002\b\u0011J-\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020\u00142\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\r\u001a\u00020\u000eH\u0000¢\u0006\u0002\b\u0017J&\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u0007\u001a\u00020\u001a2\u0006\u0010\t\u001a\u00020\u001b2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000b\u001a\u00020\fJ\u001e\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u001f2\u0006\u0010 \u001a\u00020\f2\u0006\u0010\t\u001a\u00020!J\u001e\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%2\u0006\u0010 \u001a\u00020\f2\u0006\u0010\t\u001a\u00020&J0\u0010'\u001a\u00020(2\u0006\u0010\u0007\u001a\u00020)2\u0006\u0010\t\u001a\u00020*2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\b\u0010+\u001a\u0004\u0018\u00010,J.\u0010-\u001a\u00020\u00132\u0006\u0010\u0007\u001a\u00020)2\u0006\u0010.\u001a\u00020*2\u0006\u0010+\u001a\u00020,2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000eR\u000e\u0010\u0002\u001a\u00020\u0001X\u0082\u0004¢\u0006\u0002\n\u0000R\u0012\u0010/\u001a\u000200X\u0096\u0005¢\u0006\u0006\u001a\u0004\b1\u00102R\u0012\u00103\u001a\u000204X\u0096\u0005¢\u0006\u0006\u001a\u0004\b5\u00106R\u0012\u00107\u001a\u000208X\u0096\u0005¢\u0006\u0006\u001a\u0004\b9\u0010:R\u0012\u0010;\u001a\u00020<X\u0096\u0005¢\u0006\u0006\u001a\u0004\b=\u0010>R\u0012\u0010?\u001a\u00020@X\u0096\u0005¢\u0006\u0006\u001a\u0004\bA\u0010BR\u0012\u0010C\u001a\u00020DX\u0096\u0005¢\u0006\u0006\u001a\u0004\bE\u0010FR\u0012\u0010G\u001a\u00020HX\u0096\u0005¢\u0006\u0006\u001a\u0004\bI\u0010JR\u0012\u0010K\u001a\u00020LX\u0096\u0005¢\u0006\u0006\u001a\u0004\bM\u0010NR\u0012\u0010O\u001a\u00020PX\u0096\u0005¢\u0006\u0006\u001a\u0004\bQ\u0010RR\u0012\u0010S\u001a\u00020TX\u0096\u0005¢\u0006\u0006\u001a\u0004\bU\u0010VR\u0012\u0010W\u001a\u00020XX\u0096\u0005¢\u0006\u0006\u001a\u0004\bY\u0010ZR\u0012\u0010[\u001a\u00020\\X\u0096\u0005¢\u0006\u0006\u001a\u0004\b]\u0010^R\u0012\u0010_\u001a\u00020`X\u0096\u0005¢\u0006\u0006\u001a\u0004\ba\u0010bR\u001a\u0010c\u001a\n\u0012\u0004\u0012\u00020e\u0018\u00010dX\u0096\u0005¢\u0006\u0006\u001a\u0004\bf\u0010gR\u0012\u0010h\u001a\u00020iX\u0096\u0005¢\u0006\u0006\u001a\u0004\bj\u0010kR\u0012\u0010l\u001a\u00020mX\u0096\u0005¢\u0006\u0006\u001a\u0004\bn\u0010oR\u0012\u0010p\u001a\u00020qX\u0096\u0005¢\u0006\u0006\u001a\u0004\br\u0010sR\u0018\u0010t\u001a\b\u0012\u0004\u0012\u00020v0uX\u0096\u0005¢\u0006\u0006\u001a\u0004\bw\u0010xR\u0012\u0010y\u001a\u00020\u0000X\u0096\u0005¢\u0006\u0006\u001a\u0004\bz\u0010{R\u0012\u0010|\u001a\u00020}X\u0096\u0005¢\u0006\u0006\u001a\u0004\b~\u0010\u007fR\u0016\u0010\u0080\u0001\u001a\u00030\u0081\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0082\u0001\u0010\u0083\u0001R\u0016\u0010\u0084\u0001\u001a\u00030\u0085\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0086\u0001\u0010\u0087\u0001R\u0016\u0010\u0088\u0001\u001a\u00030\u0089\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008a\u0001\u0010\u008b\u0001R\u0018\u0010\u008c\u0001\u001a\u0005\u0018\u00010\u008d\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u008e\u0001\u0010\u008f\u0001R\u0016\u0010\u0090\u0001\u001a\u00030\u0091\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0092\u0001\u0010\u0093\u0001R\u0016\u0010\u0094\u0001\u001a\u00030\u0095\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u0096\u0001\u0010\u0097\u0001R\u0016\u0010\u0098\u0001\u001a\u00030\u0099\u0001X\u0096\u0005¢\u0006\b\u001a\u0006\b\u009a\u0001\u0010\u009b\u0001¨\u0006\u009c\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "c", "<init>", "(Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;)V", "createIrLazyFunction", "Lorg/jetbrains/kotlin/fir/lazy/Fir2IrLazySimpleFunction;", "fir", "Lorg/jetbrains/kotlin/fir/declarations/FirNamedFunction;", "symbol", "Lorg/jetbrains/kotlin/ir/symbols/IrSimpleFunctionSymbol;", "lazyParent", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationParent;", "declarationOrigin", "Lorg/jetbrains/kotlin/ir/declarations/IrDeclarationOrigin;", "isSynthetic", Argument.Delimiters.none, "createIrLazyFunction$org_jetbrains_kotlin_fir2ir", "createIrLazyProperty", "Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "symbols", "Lorg/jetbrains/kotlin/fir/backend/PropertySymbols;", "createIrLazyProperty$org_jetbrains_kotlin_fir2ir", "createIrLazyConstructor", "Lorg/jetbrains/kotlin/fir/lazy/Fir2IrLazyConstructor;", "Lorg/jetbrains/kotlin/fir/declarations/FirConstructor;", "Lorg/jetbrains/kotlin/ir/symbols/IrConstructorSymbol;", "createIrLazyClass", "Lorg/jetbrains/kotlin/fir/lazy/Fir2IrLazyClass;", "firClass", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "irParent", "Lorg/jetbrains/kotlin/ir/symbols/IrClassSymbol;", "createIrLazyTypeAlias", "Lorg/jetbrains/kotlin/fir/lazy/Fir2IrLazyTypeAlias;", "firTypeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "Lorg/jetbrains/kotlin/ir/symbols/IrTypeAliasSymbol;", "createIrLazyField", "Lorg/jetbrains/kotlin/fir/lazy/Fir2IrLazyField;", "Lorg/jetbrains/kotlin/fir/declarations/FirField;", "Lorg/jetbrains/kotlin/ir/symbols/IrFieldSymbol;", "irPropertySymbol", "Lorg/jetbrains/kotlin/ir/symbols/IrPropertySymbol;", "createIrPropertyForPureField", "fieldSymbol", "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "filesBeingCompiled", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "getFilesBeingCompiled", "()Ljava/util/Set;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "irProviders", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "()Ljava/util/List;", "lazyDeclarationsGenerator", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrLazyDeclarationsGenerator implements Fir2IrComponents {
    private final Fir2IrComponents c;

    public Fir2IrLazyDeclarationsGenerator(Fir2IrComponents fir2IrComponents) {
        fir2IrComponents.getClass();
        this.c = fir2IrComponents;
    }

    /* JADX WARN: Code duplicated, block: B:30:0x006b  */
    public final Fir2IrLazyClass createIrLazyClass(FirRegularClass firClass, IrDeclarationParent irParent, IrClassSymbol symbol) {
        int i;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        firClass.getClass();
        irParent.getClass();
        symbol.getClass();
        IrDeclarationOrigin irDeclarationOriginIrOrigin = OriginUtilsKt.irOrigin(this, firClass);
        KtSourceElement source = firClass.getSource();
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
        Fir2IrLazyClass fir2IrLazyClass = new Fir2IrLazyClass(this.c, i, endOffset, irDeclarationOriginIrOrigin, firClass, symbol, irParent);
        fir2IrLazyClass.prepareTypeParameters();
        return fir2IrLazyClass;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:30:0x006e  */
    public final Fir2IrLazyConstructor createIrLazyConstructor(FirConstructor fir, IrConstructorSymbol symbol, IrDeclarationOrigin declarationOrigin, IrDeclarationParent lazyParent) throws KotlinIllegalArgumentExceptionWithAttachments {
        int i;
        IrDeclarationParent irDeclarationParent;
        Integer numStartOffsetSkippingComments;
        fir.getClass();
        symbol.getClass();
        declarationOrigin.getClass();
        lazyParent.getClass();
        TokenSet tokenSet = OffsetUtilsKt.CONSTRUCTOR_KEYWORD_TOKENS;
        KtSourceElement source = fir.getSource();
        int endOffset = -1;
        if (OffsetUtilsKt.isCompiledElement(KtSourceElementKt.getPsi(source))) {
            i = -1;
        } else if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.DataClassGeneratedMembers.INSTANCE)) {
            i = -1;
        } else if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitThisReceiverExpression.INSTANCE)) {
            i = -1;
        } else if (Intrinsics.areEqual(source != null ? source.getKind() : null, KtFakeSourceElementKind.ImplicitContextParameterArgument.INSTANCE)) {
            i = -1;
        } else {
            int startOffset = (source == null || (numStartOffsetSkippingComments = OffsetUtilsKt.startOffsetSkippingComments(source, tokenSet)) == null) ? source != null ? source.getStartOffset() : -1 : numStartOffsetSkippingComments.intValue();
            endOffset = source != null ? source.getEndOffset() : -1;
            i = startOffset;
        }
        IrDeclarationParent fir2IrLazyConstructor = new Fir2IrLazyConstructor(this.c, i, endOffset, declarationOrigin, fir, symbol, lazyParent);
        fir2IrLazyConstructor.prepareTypeParameters();
        getDeclarationStorage().enterScope(symbol);
        List<IrValueParameter> listCreateListBuilder = CollectionsKt.createListBuilder();
        IrClass irClass = lazyParent instanceof IrClass ? (IrClass) lazyParent : null;
        IrClass parentClassOrNull = irClass != null ? IrUtilsKt.getParentClassOrNull(irClass) : null;
        if (irClass == null || !irClass.isInner() || parentClassOrNull == null) {
            irDeclarationParent = fir2IrLazyConstructor;
        } else {
            IrValueParameter thisReceiver = parentClassOrNull.getThisReceiver();
            thisReceiver.getClass();
            irDeclarationParent = fir2IrLazyConstructor;
            listCreateListBuilder.add(IrElementsCreationUtilsKt.declareThisReceiverParameter$default(this, irDeclarationParent, thisReceiver.getType(), fir2IrLazyConstructor.getOrigin(), IrParameterKind.DispatchReceiver, 0, 0, null, null, false, 496, null));
        }
        getCallablesGenerator().addContextParametersTo(fir.getContextParameters(), irDeclarationParent, listCreateListBuilder);
        List<IrValueParameter> list = listCreateListBuilder;
        for (FirValueParameter firValueParameter : fir.getValueParameters()) {
            IrClass parent = irDeclarationParent.getParent();
            IrClass irClass2 = parent instanceof IrClass ? parent : null;
            IrValueParameter irValueParameterCreateIrParameter$org_jetbrains_kotlin_fir2ir$default = Fir2IrCallableDeclarationsGenerator.createIrParameter$org_jetbrains_kotlin_fir2ir$default(getCallablesGenerator(), firValueParameter, !Intrinsics.areEqual(irClass2 != null ? AdditionalIrUtilsKt.getClassId(irClass2) : null, StandardClassIds.INSTANCE.getEnum()), null, false, irClass2 != null && IrUtilsKt.isAnnotationClass(irClass2), null, 44, null);
            irValueParameterCreateIrParameter$org_jetbrains_kotlin_fir2ir$default.setParent(irDeclarationParent);
            list.add(irValueParameterCreateIrParameter$org_jetbrains_kotlin_fir2ir$default);
        }
        irDeclarationParent.setParameters(CollectionsKt.build(listCreateListBuilder));
        getDeclarationStorage().leaveScope(symbol);
        return irDeclarationParent;
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0073  */
    public final Fir2IrLazyField createIrLazyField(FirField fir, IrFieldSymbol symbol, IrDeclarationParent lazyParent, IrDeclarationOrigin declarationOrigin, IrPropertySymbol irPropertySymbol) {
        int i;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        fir.getClass();
        symbol.getClass();
        lazyParent.getClass();
        declarationOrigin.getClass();
        TokenSet tokenSet = fir != null ? KtTokens.VAL_VAR : null;
        KtSourceElement source = fir.getSource();
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
        Fir2IrComponents fir2IrComponents = this.c;
        Fir2IrLazyClass fir2IrLazyClass = lazyParent instanceof Fir2IrLazyClass ? (Fir2IrLazyClass) lazyParent : null;
        Fir2IrLazyField fir2IrLazyField = new Fir2IrLazyField(fir2IrComponents, i, endOffset, declarationOrigin, fir, fir2IrLazyClass != null ? fir2IrLazyClass.getFir() : null, symbol, irPropertySymbol);
        fir2IrLazyField.setParent(lazyParent);
        return fir2IrLazyField;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Code duplicated, block: B:30:0x0077  */
    public final Fir2IrLazySimpleFunction createIrLazyFunction$org_jetbrains_kotlin_fir2ir(FirNamedFunction fir, IrSimpleFunctionSymbol symbol, IrDeclarationParent lazyParent, IrDeclarationOrigin declarationOrigin, boolean isSynthetic) throws KotlinIllegalArgumentExceptionWithAttachments {
        int endOffset;
        int i;
        int i2;
        ConversionTypeOrigin conversionTypeOrigin;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        fir.getClass();
        symbol.getClass();
        lazyParent.getClass();
        declarationOrigin.getClass();
        TokenSet tokenSet = OffsetUtilsKt.FUNCTION_KEYWORD_TOKENS;
        KtSourceElement source = fir.getSource();
        int i3 = -1;
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
                        i3 = startOffset;
                        endOffset = source != null ? source.getEndOffset() : -1;
                    }
                }
            }
        }
        Fir2IrLazyClass fir2IrLazyClass = lazyParent instanceof Fir2IrLazyClass ? (Fir2IrLazyClass) lazyParent : null;
        FirRegularClass fir2 = fir2IrLazyClass != null ? fir2IrLazyClass.getFir() : null;
        boolean zIsFakeOverride = Fir2IrLazyDeclarationsGeneratorKt.isFakeOverride(fir, fir2);
        int i4 = endOffset;
        Fir2IrComponents fir2IrComponents = this.c;
        if (isSynthetic) {
            i3 = -2;
        }
        if (isSynthetic) {
            i2 = i3;
            i = -2;
        } else {
            int i5 = i3;
            i = i4;
            i2 = i5;
        }
        Fir2IrLazySimpleFunction fir2IrLazySimpleFunction = new Fir2IrLazySimpleFunction(fir2IrComponents, i2, i, declarationOrigin, fir, fir2, symbol, lazyParent, zIsFakeOverride);
        fir2IrLazySimpleFunction.prepareTypeParameters();
        getDeclarationStorage().enterScope(symbol);
        List<IrValueParameter> listCreateListBuilder = CollectionsKt.createListBuilder();
        IrClass irClass = lazyParent instanceof IrClass ? (IrClass) lazyParent : null;
        if (irClass == null || !fir2IrLazySimpleFunction.shouldHaveDispatchReceiver$org_jetbrains_kotlin_fir2ir(irClass)) {
            conversionTypeOrigin = null;
        } else {
            IrType irTypeComputeDispatchReceiverType$org_jetbrains_kotlin_fir2ir = Fir2IrCallableDeclarationsGenerator.INSTANCE.computeDispatchReceiverType$org_jetbrains_kotlin_fir2ir(this, fir2IrLazySimpleFunction, fir, irClass);
            List<IrValueParameter> list = listCreateListBuilder;
            if (irTypeComputeDispatchReceiverType$org_jetbrains_kotlin_fir2ir == null) {
                f2f.a("No dispatch receiver receiver for function: ", UtilsKt.render(fir));
                return null;
            }
            conversionTypeOrigin = null;
            list.add(IrElementsCreationUtilsKt.declareThisReceiverParameter$default(this, fir2IrLazySimpleFunction, irTypeComputeDispatchReceiverType$org_jetbrains_kotlin_fir2ir, fir2IrLazySimpleFunction.getOrigin(), IrParameterKind.DispatchReceiver, 0, 0, null, null, false, 496, null));
        }
        getCallablesGenerator().addContextParametersTo(FirDeclarationUtilKt.contextParametersForFunctionOrContainingProperty(fir), fir2IrLazySimpleFunction, listCreateListBuilder);
        FirReceiverParameter receiverParameter = fir.getReceiverParameter();
        if (receiverParameter != null) {
            ConversionTypeOrigin conversionTypeOrigin2 = conversionTypeOrigin;
            listCreateListBuilder.add(IrElementsCreationUtilsKt.declareThisReceiverParameter$default(this, fir2IrLazySimpleFunction, Fir2IrTypeConverterKt.toIrType$default(this, receiverParameter.getTypeRef(), conversionTypeOrigin2, 2, conversionTypeOrigin2), fir2IrLazySimpleFunction.getOrigin(), IrParameterKind.ExtensionReceiver, 0, 0, null, receiverParameter, false, 368, null));
        }
        List<IrValueParameter> list2 = listCreateListBuilder;
        Iterator<T> it = fir.getValueParameters().iterator();
        while (it.hasNext()) {
            IrValueParameter irValueParameterCreateIrParameter$org_jetbrains_kotlin_fir2ir$default = Fir2IrCallableDeclarationsGenerator.createIrParameter$org_jetbrains_kotlin_fir2ir$default(getCallablesGenerator(), (FirValueParameter) it.next(), false, null, fir2IrLazySimpleFunction.getIsFakeOverride(), false, null, 54, null);
            irValueParameterCreateIrParameter$org_jetbrains_kotlin_fir2ir$default.setParent(fir2IrLazySimpleFunction);
            list2.add(irValueParameterCreateIrParameter$org_jetbrains_kotlin_fir2ir$default);
        }
        fir2IrLazySimpleFunction.setParameters(CollectionsKt.build(listCreateListBuilder));
        getDeclarationStorage().leaveScope(symbol);
        return fir2IrLazySimpleFunction;
    }

    /* JADX WARN: Code duplicated, block: B:48:0x00a3  */
    public final IrProperty createIrLazyProperty$org_jetbrains_kotlin_fir2ir(FirProperty fir, IrDeclarationParent lazyParent, PropertySymbols symbols, IrDeclarationOrigin declarationOrigin) {
        int i;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        fir.getClass();
        lazyParent.getClass();
        symbols.getClass();
        declarationOrigin.getClass();
        boolean zAreEqual = Intrinsics.areEqual(Fir2IrDeclarationStorageKt.isStubPropertyForPureField(fir), Boolean.TRUE);
        Fir2IrLazyClass fir2IrLazyClass = lazyParent instanceof Fir2IrLazyClass ? (Fir2IrLazyClass) lazyParent : null;
        FirRegularClass fir2 = fir2IrLazyClass != null ? fir2IrLazyClass.getFir() : null;
        boolean z = !zAreEqual && Fir2IrLazyDeclarationsGeneratorKt.isFakeOverride(fir, fir2);
        IrDeclarationOrigin defined = zAreEqual ? IrDeclarationOrigin.Companion.getDEFINED() : declarationOrigin;
        TokenSet tokenSet = fir != null ? KtTokens.VAL_VAR : null;
        KtSourceElement source = fir.getSource();
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
        return new Fir2IrLazyProperty(this.c, i, endOffset, defined, fir, fir2, symbols, lazyParent, z);
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0068  */
    public final Fir2IrLazyTypeAlias createIrLazyTypeAlias(FirTypeAlias firTypeAlias, IrDeclarationParent irParent, IrTypeAliasSymbol symbol) {
        int i;
        int endOffset;
        int startOffset;
        Integer numStartOffsetSkippingComments;
        firTypeAlias.getClass();
        irParent.getClass();
        symbol.getClass();
        KtSourceElement source = firTypeAlias.getSource();
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
        Fir2IrLazyTypeAlias fir2IrLazyTypeAlias = new Fir2IrLazyTypeAlias(this.c, i, endOffset, IrDeclarationOrigin.Companion.getIR_EXTERNAL_DECLARATION_STUB(), firTypeAlias, symbol, irParent);
        fir2IrLazyTypeAlias.prepareTypeParameters();
        return fir2IrLazyTypeAlias;
    }

    public final IrProperty createIrPropertyForPureField(FirField fir, IrFieldSymbol fieldSymbol, IrPropertySymbol irPropertySymbol, IrDeclarationParent lazyParent, IrDeclarationOrigin declarationOrigin) {
        fir.getClass();
        fieldSymbol.getClass();
        irPropertySymbol.getClass();
        lazyParent.getClass();
        declarationOrigin.getClass();
        return new Fir2IrLazyPropertyForPureField(this.c, createIrLazyField(fir, fieldSymbol, lazyParent, declarationOrigin, irPropertySymbol), irPropertySymbol, lazyParent);
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
}
