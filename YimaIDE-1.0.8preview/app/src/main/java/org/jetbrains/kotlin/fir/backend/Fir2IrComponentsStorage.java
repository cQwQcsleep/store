package org.jetbrains.kotlin.fir.backend;

import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.backend.common.IrSpecialAnnotationsProvider;
import org.jetbrains.kotlin.builtins.KotlinBuiltIns;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.backend.generators.AdapterGenerator;
import org.jetbrains.kotlin.fir.backend.generators.AnnotationGenerator;
import org.jetbrains.kotlin.fir.backend.generators.CallAndReferenceGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrCallableDeclarationsGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrClassifiersGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrDataClassMembersGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyDeclarationsGenerator;
import org.jetbrains.kotlin.fir.backend.generators.Fir2IrLazyFakeOverrideGenerator;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.descriptors.FirModuleDescriptor;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;
import org.jetbrains.kotlin.ir.IrLock;
import org.jetbrains.kotlin.ir.IrProvider;
import org.jetbrains.kotlin.ir.declarations.IrClass;
import org.jetbrains.kotlin.ir.util.KotlinMangler;
import org.jetbrains.kotlin.ir.util.SymbolRemapper;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u009a\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u008b\u0001\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007\u0012\u0006\u0010\t\u001a\u00020\n\u0012\u0006\u0010\u000b\u001a\u00020\f\u0012\u0006\u0010\r\u001a\u00020\u000e\u0012\u0006\u0010\u000f\u001a\u00020\u0010\u0012\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00140\u0012\u0012\u0006\u0010\u0015\u001a\u00020\u0016\u0012\u0006\u0010\u0017\u001a\u00020\u0018\u0012\b\u0010\u0019\u001a\u0004\u0018\u00010\u001a\u0012\u0006\u0010\u001b\u001a\u00020\u001c\u0012\u0006\u0010\u001d\u001a\u00020\u001e\u0012\u0006\u0010\u001f\u001a\u00020 ¢\u0006\u0004\b!\u0010\"R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b%\u0010&R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b'\u0010(R\u0014\u0010\t\u001a\u00020\nX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R\u0014\u0010\u000b\u001a\u00020\fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b+\u0010,R\u0014\u0010\r\u001a\u00020\u000eX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b-\u0010.R\u0014\u0010\u0015\u001a\u00020\u0016X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b/\u00100R\u0016\u0010\u0019\u001a\u0004\u0018\u00010\u001aX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b1\u00102R\u0014\u0010\u001b\u001a\u00020\u001cX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b3\u00104R\u0014\u00105\u001a\u000206X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b7\u00108R\u001c\u00109\u001a\n\u0012\u0004\u0012\u00020\b\u0018\u00010:X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b;\u0010<R\u0011\u0010=\u001a\u00020>¢\u0006\b\n\u0000\u001a\u0004\b?\u0010@R\u000e\u0010A\u001a\u00020BX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010C\u001a\u00020DX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bE\u0010FR\u0014\u0010G\u001a\u00020HX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bI\u0010JR\u0014\u0010K\u001a\u00020LX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bM\u0010NR\u0014\u0010O\u001a\u00020PX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bQ\u0010RR\u0014\u0010S\u001a\u00020TX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bU\u0010VR\u0014\u0010W\u001a\u00020XX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bY\u0010ZR\u0014\u0010[\u001a\u00020\\X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b]\u0010^R\u0014\u0010_\u001a\u00020`X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\ba\u0010bR\u001a\u0010c\u001a\b\u0012\u0004\u0012\u00020d0\u0007X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\be\u0010(R\u0014\u0010f\u001a\u00020gX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bh\u0010iR\u0011\u0010j\u001a\u00020k¢\u0006\b\n\u0000\u001a\u0004\bl\u0010mR\u0014\u0010n\u001a\u00020oX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bp\u0010qR\u0014\u0010r\u001a\u00020sX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bt\u0010uR\u0014\u0010v\u001a\u00020wX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\bx\u0010yR\u0014\u0010z\u001a\u00020{X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b|\u0010}R\u0016\u0010~\u001a\u00020\u007fX\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0080\u0001\u0010\u0081\u0001R\u0018\u0010\u0082\u0001\u001a\u00030\u0083\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0084\u0001\u0010\u0085\u0001R\u0018\u0010\u0086\u0001\u001a\u00030\u0087\u0001X\u0096\u0004¢\u0006\n\n\u0000\u001a\u0006\b\u0088\u0001\u0010\u0089\u0001¨\u0006\u008a\u0001"}, d2 = {"Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponentsStorage;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrComponents;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "fir", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "extensions", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "configuration", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "visibilityConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "commonMemberStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrCommonMemberStorage;", "generatedDataValueClassSyntheticFunctionsStorage", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/ir/declarations/IrClass;", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrCommonMemberStorage$DataValueClassGeneratedMembersInfo;", "irMangler", "Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "kotlinBuiltIns", "Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;", "specialAnnotationsProvider", "Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "firProvider", "Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "syntheticIrBuiltinsSymbolsContainer", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSyntheticIrBuiltinsSymbolsContainer;", "fakeOverrideResolver", "Lorg/jetbrains/kotlin/ir/util/SymbolRemapper;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Ljava/util/List;Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;Lorg/jetbrains/kotlin/fir/backend/Fir2IrCommonMemberStorage;Ljava/util/Map;Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;Lorg/jetbrains/kotlin/builtins/KotlinBuiltIns;Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;Lorg/jetbrains/kotlin/fir/backend/Fir2IrSyntheticIrBuiltinsSymbolsContainer;Lorg/jetbrains/kotlin/ir/util/SymbolRemapper;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getFir", "()Ljava/util/List;", "getExtensions", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrExtensions;", "getConfiguration", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConfiguration;", "getVisibilityConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisibilityConverter;", "getIrMangler", "()Lorg/jetbrains/kotlin/ir/util/KotlinMangler$IrMangler;", "getSpecialAnnotationsProvider", "()Lorg/jetbrains/kotlin/backend/common/IrSpecialAnnotationsProvider;", "getFirProvider", "()Lorg/jetbrains/kotlin/fir/backend/FirProviderWithGeneratedFiles;", "lock", "Lorg/jetbrains/kotlin/ir/IrLock;", "getLock", "()Lorg/jetbrains/kotlin/ir/IrLock;", "filesBeingCompiled", Argument.Delimiters.none, "getFilesBeingCompiled", "()Ljava/util/Set;", "moduleDescriptor", "Lorg/jetbrains/kotlin/fir/descriptors/FirModuleDescriptor;", "getModuleDescriptor", "()Lorg/jetbrains/kotlin/fir/descriptors/FirModuleDescriptor;", "conversionScope", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConversionScope;", "converter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "getConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrConverter;", "classifierStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "getClassifierStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrClassifierStorage;", "declarationStorage", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "getDeclarationStorage", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrDeclarationStorage;", "callablesGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "getCallablesGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrCallableDeclarationsGenerator;", "classifiersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "getClassifiersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrClassifiersGenerator;", "lazyDeclarationsGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "getLazyDeclarationsGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyDeclarationsGenerator;", "dataClassMembersGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "getDataClassMembersGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrDataClassMembersGenerator;", "builtins", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "getBuiltins", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrBuiltinSymbolsContainer;", "irProviders", "Lorg/jetbrains/kotlin/ir/IrProvider;", "getIrProviders", "typeConverter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "getTypeConverter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrTypeConverter;", "fir2IrVisitor", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisitor;", "getFir2IrVisitor", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrVisitor;", "annotationGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "getAnnotationGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AnnotationGenerator;", "callGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "getCallGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/CallAndReferenceGenerator;", "lazyFakeOverrideGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "getLazyFakeOverrideGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/Fir2IrLazyFakeOverrideGenerator;", "symbolsMappingForLazyClasses", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "getSymbolsMappingForLazyClasses", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrSymbolsMappingForLazyClasses;", "annotationsFromPluginRegistrar", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "getAnnotationsFromPluginRegistrar", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrIrGeneratedDeclarationsRegistrar;", "adapterGenerator", "Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "getAdapterGenerator", "()Lorg/jetbrains/kotlin/fir/backend/generators/AdapterGenerator;", "implicitCastInserter", "Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "getImplicitCastInserter", "()Lorg/jetbrains/kotlin/fir/backend/Fir2IrImplicitCastInserter;", "org.jetbrains.kotlin:fir2ir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class Fir2IrComponentsStorage implements Fir2IrComponents {
    private final AdapterGenerator adapterGenerator;
    private final AnnotationGenerator annotationGenerator;
    private final Fir2IrIrGeneratedDeclarationsRegistrar annotationsFromPluginRegistrar;
    private final Fir2IrBuiltinSymbolsContainer builtins;
    private final CallAndReferenceGenerator callGenerator;
    private final Fir2IrCallableDeclarationsGenerator callablesGenerator;
    private final Fir2IrClassifierStorage classifierStorage;
    private final Fir2IrClassifiersGenerator classifiersGenerator;
    private final Fir2IrConfiguration configuration;
    private final Fir2IrConversionScope conversionScope;
    private final Fir2IrConverter converter;
    private final Fir2IrDataClassMembersGenerator dataClassMembersGenerator;
    private final Fir2IrDeclarationStorage declarationStorage;
    private final Fir2IrExtensions extensions;
    private final Set<FirFile> filesBeingCompiled;
    private final List<FirFile> fir;
    private final Fir2IrVisitor fir2IrVisitor;
    private final FirProviderWithGeneratedFiles firProvider;
    private final Fir2IrImplicitCastInserter implicitCastInserter;
    private final KotlinMangler.IrMangler irMangler;
    private final List<IrProvider> irProviders;
    private final Fir2IrLazyDeclarationsGenerator lazyDeclarationsGenerator;
    private final Fir2IrLazyFakeOverrideGenerator lazyFakeOverrideGenerator;
    private final IrLock lock;
    private final FirModuleDescriptor moduleDescriptor;
    private final ScopeSession scopeSession;
    private final FirSession session;
    private final IrSpecialAnnotationsProvider specialAnnotationsProvider;
    private final Fir2IrSymbolsMappingForLazyClasses symbolsMappingForLazyClasses;
    private final Fir2IrTypeConverter typeConverter;
    private final Fir2IrVisibilityConverter visibilityConverter;

    /* JADX WARN: Multi-variable type inference failed */
    public Fir2IrComponentsStorage(FirSession firSession, ScopeSession scopeSession, List<? extends FirFile> list, Fir2IrExtensions fir2IrExtensions, Fir2IrConfiguration fir2IrConfiguration, Fir2IrVisibilityConverter fir2IrVisibilityConverter, Fir2IrCommonMemberStorage fir2IrCommonMemberStorage, Map<IrClass, Fir2IrCommonMemberStorage.DataValueClassGeneratedMembersInfo> map, KotlinMangler.IrMangler irMangler, KotlinBuiltIns kotlinBuiltIns, IrSpecialAnnotationsProvider irSpecialAnnotationsProvider, FirProviderWithGeneratedFiles firProviderWithGeneratedFiles, Fir2IrSyntheticIrBuiltinsSymbolsContainer fir2IrSyntheticIrBuiltinsSymbolsContainer, SymbolRemapper symbolRemapper) {
        firSession.getClass();
        scopeSession.getClass();
        list.getClass();
        fir2IrExtensions.getClass();
        fir2IrConfiguration.getClass();
        fir2IrVisibilityConverter.getClass();
        fir2IrCommonMemberStorage.getClass();
        map.getClass();
        irMangler.getClass();
        kotlinBuiltIns.getClass();
        firProviderWithGeneratedFiles.getClass();
        fir2IrSyntheticIrBuiltinsSymbolsContainer.getClass();
        symbolRemapper.getClass();
        this.session = firSession;
        this.scopeSession = scopeSession;
        this.fir = list;
        this.extensions = fir2IrExtensions;
        this.configuration = fir2IrConfiguration;
        this.visibilityConverter = fir2IrVisibilityConverter;
        this.irMangler = irMangler;
        this.specialAnnotationsProvider = irSpecialAnnotationsProvider;
        this.firProvider = firProviderWithGeneratedFiles;
        this.lock = fir2IrCommonMemberStorage.getLock();
        this.filesBeingCompiled = getConfiguration().getAllowNonCachedDeclarations() ? CollectionsKt.toSet(list) : null;
        FirModuleDescriptor firModuleDescriptorCreateSourceModuleDescriptor = FirModuleDescriptor.INSTANCE.createSourceModuleDescriptor(getSession(), kotlinBuiltIns);
        this.moduleDescriptor = firModuleDescriptorCreateSourceModuleDescriptor;
        Fir2IrConversionScope fir2IrConversionScope = new Fir2IrConversionScope(getConfiguration());
        this.conversionScope = fir2IrConversionScope;
        this.converter = new Fir2IrConverter(firModuleDescriptorCreateSourceModuleDescriptor, this, fir2IrConversionScope);
        this.classifierStorage = new Fir2IrClassifierStorage(this, fir2IrCommonMemberStorage, fir2IrConversionScope);
        this.declarationStorage = new Fir2IrDeclarationStorage(this, firModuleDescriptorCreateSourceModuleDescriptor, fir2IrCommonMemberStorage);
        this.callablesGenerator = new Fir2IrCallableDeclarationsGenerator(this);
        this.classifiersGenerator = new Fir2IrClassifiersGenerator(this);
        this.lazyDeclarationsGenerator = new Fir2IrLazyDeclarationsGenerator(this);
        this.dataClassMembersGenerator = new Fir2IrDataClassMembersGenerator(this, map);
        this.builtins = new Fir2IrBuiltinSymbolsContainer(this, fir2IrSyntheticIrBuiltinsSymbolsContainer);
        this.irProviders = CollectionsKt.emptyList();
        this.typeConverter = new Fir2IrTypeConverter(this, fir2IrConversionScope);
        Fir2IrVisitor fir2IrVisitor = new Fir2IrVisitor(this, fir2IrConversionScope);
        this.fir2IrVisitor = fir2IrVisitor;
        this.annotationGenerator = new AnnotationGenerator(this);
        this.callGenerator = new CallAndReferenceGenerator(this, fir2IrVisitor, fir2IrConversionScope);
        this.lazyFakeOverrideGenerator = new Fir2IrLazyFakeOverrideGenerator(this);
        this.symbolsMappingForLazyClasses = new Fir2IrSymbolsMappingForLazyClasses(symbolRemapper);
        this.annotationsFromPluginRegistrar = new Fir2IrIrGeneratedDeclarationsRegistrar(this);
        this.adapterGenerator = new AdapterGenerator(this, fir2IrConversionScope);
        this.implicitCastInserter = new Fir2IrImplicitCastInserter(this);
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AdapterGenerator getAdapterGenerator() {
        return this.adapterGenerator;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public AnnotationGenerator getAnnotationGenerator() {
        return this.annotationGenerator;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrIrGeneratedDeclarationsRegistrar getAnnotationsFromPluginRegistrar() {
        return this.annotationsFromPluginRegistrar;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrBuiltinSymbolsContainer getBuiltins() {
        return this.builtins;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public CallAndReferenceGenerator getCallGenerator() {
        return this.callGenerator;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrCallableDeclarationsGenerator getCallablesGenerator() {
        return this.callablesGenerator;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrClassifierStorage getClassifierStorage() {
        return this.classifierStorage;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrClassifiersGenerator getClassifiersGenerator() {
        return this.classifiersGenerator;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrConfiguration getConfiguration() {
        return this.configuration;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrConverter getConverter() {
        return this.converter;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrDataClassMembersGenerator getDataClassMembersGenerator() {
        return this.dataClassMembersGenerator;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrDeclarationStorage getDeclarationStorage() {
        return this.declarationStorage;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrExtensions getExtensions() {
        return this.extensions;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Set<FirFile> getFilesBeingCompiled() {
        return this.filesBeingCompiled;
    }

    public final List<FirFile> getFir() {
        return this.fir;
    }

    public final Fir2IrVisitor getFir2IrVisitor() {
        return this.fir2IrVisitor;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public FirProviderWithGeneratedFiles getFirProvider() {
        return this.firProvider;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrImplicitCastInserter getImplicitCastInserter() {
        return this.implicitCastInserter;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public KotlinMangler.IrMangler getIrMangler() {
        return this.irMangler;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public List<IrProvider> getIrProviders() {
        return this.irProviders;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrLazyDeclarationsGenerator getLazyDeclarationsGenerator() {
        return this.lazyDeclarationsGenerator;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrLazyFakeOverrideGenerator getLazyFakeOverrideGenerator() {
        return this.lazyFakeOverrideGenerator;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrLock getLock() {
        return this.lock;
    }

    public final FirModuleDescriptor getModuleDescriptor() {
        return this.moduleDescriptor;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.ScopeSessionHolder
    public ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents, org.jetbrains.kotlin.fir.SessionHolder
    public FirSession getSession() {
        return this.session;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public IrSpecialAnnotationsProvider getSpecialAnnotationsProvider() {
        return this.specialAnnotationsProvider;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrSymbolsMappingForLazyClasses getSymbolsMappingForLazyClasses() {
        return this.symbolsMappingForLazyClasses;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrTypeConverter getTypeConverter() {
        return this.typeConverter;
    }

    @Override // org.jetbrains.kotlin.fir.backend.Fir2IrComponents
    public Fir2IrVisibilityConverter getVisibilityConverter() {
        return this.visibilityConverter;
    }
}
