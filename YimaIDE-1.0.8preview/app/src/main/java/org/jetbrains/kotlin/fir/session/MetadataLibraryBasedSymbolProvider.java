package org.jetbrains.kotlin.fir.session;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.annotations.AnnotationUseSiteTarget;
import org.jetbrains.kotlin.fir.ClassMembersKt;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.declarations.FirProperty;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.declarations.utils.DeclarationAttributesKt;
import org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider;
import org.jetbrains.kotlin.fir.deserialization.AnnotationDeserializationUtilKt;
import org.jetbrains.kotlin.fir.deserialization.ClassDeserializationKt;
import org.jetbrains.kotlin.fir.deserialization.FirConstDeserializer;
import org.jetbrains.kotlin.fir.deserialization.FirDeserializationContext;
import org.jetbrains.kotlin.fir.deserialization.FirTypeDeserializer;
import org.jetbrains.kotlin.fir.deserialization.ModuleDataProvider;
import org.jetbrains.kotlin.fir.deserialization.PackagePartsCacheData;
import org.jetbrains.kotlin.fir.expressions.FirAnnotation;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.fir.session.MetadataLibraryBasedSymbolProvider;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.library.KotlinLibrary;
import org.jetbrains.kotlin.library.components.KlibMetadataComponent;
import org.jetbrains.kotlin.library.metadata.DeserializedSourceFile;
import org.jetbrains.kotlin.library.metadata.KlibDeserializedContainerSource;
import org.jetbrains.kotlin.library.metadata.KlibMetadataClassDataFinder;
import org.jetbrains.kotlin.library.metadata.KlibMetadataDeserializationUtilsKt;
import org.jetbrains.kotlin.library.metadata.KlibMetadataProtoBuf;
import org.jetbrains.kotlin.library.metadata.KlibMetadataSerializerProtocol;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.metadata.deserialization.NameResolverImpl;
import org.jetbrains.kotlin.metadata.deserialization.ProtoBufUtilKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.protobuf.GeneratedMessageLite;
import org.jetbrains.kotlin.resolve.KlibCompilerDeserializationConfiguration;
import org.jetbrains.kotlin.serialization.deserialization.ClassData;
import org.jetbrains.kotlin.serialization.deserialization.NameResolverUtilKt;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0086\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b&\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001lBE\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000f0\u000e¢\u0006\u0004\b\u0010\u0010\u0011J\u0017\u0010\u0014\u001a\u0004\u0018\u00010\u00152\u0006\u0010\u0016\u001a\u00028\u0000H$¢\u0006\u0002\u0010\u0017J\u001e\u00106\u001a\b\u0012\u0004\u0012\u0002050\u001b2\u0006\u00107\u001a\u00020.2\u0006\u00108\u001a\u000201H\u0002J%\u00109\u001a\u00020.2\u0006\u0010:\u001a\u00028\u00002\u0006\u0010;\u001a\u00020\u001a2\u0006\u0010<\u001a\u00020\u001aH\u0002¢\u0006\u0002\u0010=J\u0010\u0010>\u001a\u0002012\u0006\u00107\u001a\u00020.H\u0002J\u0018\u0010?\u001a\u0002032\u0006\u00107\u001a\u00020.2\u0006\u0010@\u001a\u000201H\u0002J\u0016\u0010A\u001a\b\u0012\u0004\u0012\u00020B0\u001b2\u0006\u0010C\u001a\u00020 H\u0014J\u000e\u0010D\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001fH\u0014J\u0016\u0010E\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001f2\u0006\u0010C\u001a\u00020 H\u0014J\u001c\u0010F\u001a\u0004\u0018\u00010G2\u0006\u0010H\u001a\u00020I2\b\u0010J\u001a\u0004\u0018\u00010KH\u0014J1\u0010L\u001a\u00020M2\u0006\u0010C\u001a\u00020 2\u001e\u0010N\u001a\u001a\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u000201\u0012\u0004\u0012\u00020M0OH\u0082\bJ \u0010P\u001a\u00020M2\u0006\u0010Q\u001a\u00020B2\u0006\u0010R\u001a\u00020S2\u0006\u0010T\u001a\u00020UH\u0016J \u0010V\u001a\u00020M2\u0006\u0010Q\u001a\u00020B2\u0006\u0010R\u001a\u00020W2\u0006\u0010T\u001a\u00020XH\u0016JC\u0010Y\u001a\u0004\u0018\u00010Z\"\u000e\b\u0001\u0010[*\b\u0012\u0004\u0012\u0002H[0\\2\u0006\u0010Q\u001a\u00020B2\u0006\u0010R\u001a\u0002H[2\u0012\u0010]\u001a\u000e\u0012\u0004\u0012\u0002H[\u0012\u0004\u0012\u00020_0^H\u0002¢\u0006\u0002\u0010`JK\u0010Y\u001a\u0004\u0018\u00010Z\"\u000e\b\u0001\u0010[*\b\u0012\u0004\u0012\u0002H[0\\2\u0006\u0010\u0016\u001a\u00020a2\u0006\u00108\u001a\u0002012\u0006\u0010R\u001a\u0002H[2\u0012\u0010]\u001a\u000e\u0012\u0004\u0012\u0002H[\u0012\u0004\u0012\u00020_0^H\u0002¢\u0006\u0002\u0010bJ\u001f\u0010c\u001a\u0004\u0018\u00010d2\u0006\u0010:\u001a\u00028\u00002\u0006\u0010C\u001a\u00020 H$¢\u0006\u0002\u0010eJ\u0010\u0010f\u001a\u00020g2\u0006\u0010h\u001a\u00020iH\u0014J\u0010\u0010j\u001a\u00020g2\u0006\u0010k\u001a\u00020 H\u0016R\u000e\u0010\t\u001a\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\r\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00020\u000f0\u000eX\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R$\u0010\u0018\u001a\u0014\u0012\u0004\u0012\u00020\u001a\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u001b0\u0019X¤\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001dR\u0018\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020 0\u001fX¤\u0004¢\u0006\u0006\u001a\u0004\b!\u0010\"R\u000e\u0010#\u001a\u00020$X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010%\u001a\u00020&X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010'\u001a\u00020(X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b)\u0010*R2\u0010+\u001a&\u0012\u0004\u0012\u00028\u0000\u0012\u001c\u0012\u001a\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u001a\u0012\u0004\u0012\u00020\u001a0-\u0012\u0004\u0012\u00020.0,0,X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u0010/\u001a\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020100X\u0082\u0004¢\u0006\u0002\n\u0000R\u001a\u00102\u001a\u000e\u0012\u0004\u0012\u00020.\u0012\u0004\u0012\u00020300X\u0082\u0004¢\u0006\u0002\n\u0000R \u00104\u001a\u0014\u0012\u0004\u0012\u00020.\u0012\n\u0012\b\u0012\u0004\u0012\u0002050\u001b00X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006m"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/MetadataLibraryBasedSymbolProvider;", "L", "Lorg/jetbrains/kotlin/fir/deserialization/AbstractFirDeserializedSymbolProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "moduleDataProvider", "Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;", "kotlinScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "flexibleTypeFactory", "Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer$FlexibleTypeFactory;", "defaultDeserializationOrigin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "metadataProvider", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/library/components/KlibMetadataComponent;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer$FlexibleTypeFactory;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;Lkotlin/jvm/functions/Function1;)V", "getMetadataProvider", "()Lkotlin/jvm/functions/Function1;", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "library", "(Ljava/lang/Object;)Lorg/jetbrains/kotlin/fir/FirModuleData;", "fragmentNamesInLibraries", Argument.Delimiters.none, Argument.Delimiters.none, Argument.Delimiters.none, "getFragmentNamesInLibraries", "()Ljava/util/Map;", "knownPackagesInLibraries", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "getKnownPackagesInLibraries", "()Ljava/util/Set;", "annotationDeserializer", "Lorg/jetbrains/kotlin/fir/session/KlibBasedAnnotationDeserializer;", "constDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/FirConstDeserializer;", "deserializationConfiguration", "Lorg/jetbrains/kotlin/resolve/KlibCompilerDeserializationConfiguration;", "getDeserializationConfiguration", "()Lorg/jetbrains/kotlin/resolve/KlibCompilerDeserializationConfiguration;", "cachedFragments", Argument.Delimiters.none, "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$PackageFragment;", "fragmentToNameResolver", "Ljava/util/IdentityHashMap;", "Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;", "fragmentToKlibMetadataClassDataFinder", "Lorg/jetbrains/kotlin/library/metadata/KlibMetadataClassDataFinder;", "fragmentToFileAnnotations", "Lorg/jetbrains/kotlin/fir/expressions/FirAnnotation;", "getFileAnnotations", "fragment", "nameResolver", "getPackageFragment", "resolvedLibrary", "packageStringName", "packageMetadataPart", "(Ljava/lang/Object;Ljava/lang/String;Ljava/lang/String;)Lorg/jetbrains/kotlin/metadata/ProtoBuf$PackageFragment;", "getNameResolver", "getFinder", "resolver", "computePackagePartsInfos", "Lorg/jetbrains/kotlin/fir/deserialization/PackagePartsCacheData;", "packageFqName", "computePackageSetWithNonClassDeclarations", "knownTopLevelClassesInPackage", "extractClassMetadata", "Lorg/jetbrains/kotlin/fir/deserialization/AbstractFirDeserializedSymbolProvider$ClassMetadataFindResult;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "parentContext", "Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationContext;", "forEachFragmentInPackage", Argument.Delimiters.none, "f", "Lkotlin/Function3;", "loadFunctionExtensions", "packagePart", "proto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Function;", "fir", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "loadPropertyExtensions", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Property;", "Lorg/jetbrains/kotlin/fir/declarations/FirProperty;", "loadKlibSourceFileExtensionOrNull", "Lorg/jetbrains/kotlin/library/metadata/DeserializedSourceFile;", "T", "Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$ExtendableMessage;", "sourceFileExtension", "Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$GeneratedExtension;", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/fir/deserialization/PackagePartsCacheData;Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$ExtendableMessage;Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$GeneratedExtension;)Lorg/jetbrains/kotlin/library/metadata/DeserializedSourceFile;", "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "(Lorg/jetbrains/kotlin/library/KotlinLibrary;Lorg/jetbrains/kotlin/metadata/deserialization/NameResolver;Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$ExtendableMessage;Lorg/jetbrains/kotlin/protobuf/GeneratedMessageLite$GeneratedExtension;)Lorg/jetbrains/kotlin/library/metadata/DeserializedSourceFile;", "createDeserializedContainerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "(Ljava/lang/Object;Lorg/jetbrains/kotlin/name/FqName;)Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "isNewPlaceForBodyGeneration", Argument.Delimiters.none, "classProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "hasPackage", "fqName", "MetadataLibraryPackagePartCacheDataExtra", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class MetadataLibraryBasedSymbolProvider<L> extends AbstractFirDeserializedSymbolProvider {
    private final KlibBasedAnnotationDeserializer annotationDeserializer;
    private final Map<L, Map<Pair<String, String>, ProtoBuf.PackageFragment>> cachedFragments;
    private final FirConstDeserializer constDeserializer;
    private final KlibCompilerDeserializationConfiguration deserializationConfiguration;
    private final FirTypeDeserializer.FlexibleTypeFactory flexibleTypeFactory;
    private final IdentityHashMap<ProtoBuf.PackageFragment, List<FirAnnotation>> fragmentToFileAnnotations;
    private final IdentityHashMap<ProtoBuf.PackageFragment, KlibMetadataClassDataFinder> fragmentToKlibMetadataClassDataFinder;
    private final IdentityHashMap<ProtoBuf.PackageFragment, NameResolver> fragmentToNameResolver;
    private final Function1<L, KlibMetadataComponent> metadataProvider;

    @Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/MetadataLibraryBasedSymbolProvider$MetadataLibraryPackagePartCacheDataExtra;", "Lorg/jetbrains/kotlin/fir/deserialization/PackagePartsCacheData$Extra;", "library", "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "<init>", "(Lorg/jetbrains/kotlin/library/KotlinLibrary;)V", "getLibrary", "()Lorg/jetbrains/kotlin/library/KotlinLibrary;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class MetadataLibraryPackagePartCacheDataExtra implements PackagePartsCacheData.Extra {
        private final KotlinLibrary library;

        public MetadataLibraryPackagePartCacheDataExtra(KotlinLibrary kotlinLibrary) {
            kotlinLibrary.getClass();
            this.library = kotlinLibrary;
        }

        public final KotlinLibrary getLibrary() {
            return this.library;
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    /* JADX WARN: Multi-variable type inference failed */
    public MetadataLibraryBasedSymbolProvider(FirSession firSession, ModuleDataProvider moduleDataProvider, FirKotlinScopeProvider firKotlinScopeProvider, FirTypeDeserializer.FlexibleTypeFactory flexibleTypeFactory, FirDeclarationOrigin firDeclarationOrigin, Function1<? super L, ? extends KlibMetadataComponent> function1) {
        firSession.getClass();
        moduleDataProvider.getClass();
        firKotlinScopeProvider.getClass();
        flexibleTypeFactory.getClass();
        firDeclarationOrigin.getClass();
        function1.getClass();
        KlibMetadataSerializerProtocol klibMetadataSerializerProtocol = KlibMetadataSerializerProtocol.INSTANCE;
        super(firSession, moduleDataProvider, firKotlinScopeProvider, firDeclarationOrigin, klibMetadataSerializerProtocol);
        this.flexibleTypeFactory = flexibleTypeFactory;
        this.metadataProvider = function1;
        this.annotationDeserializer = new KlibBasedAnnotationDeserializer(firSession);
        this.constDeserializer = new FirConstDeserializer(klibMetadataSerializerProtocol);
        this.deserializationConfiguration = new KlibCompilerDeserializationConfiguration(FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession));
        this.cachedFragments = new LinkedHashMap();
        this.fragmentToNameResolver = new IdentityHashMap<>();
        this.fragmentToKlibMetadataClassDataFinder = new IdentityHashMap<>();
        this.fragmentToFileAnnotations = new IdentityHashMap<>();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v7, types: [org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    /* JADX WARN: Type inference failed for: r2v6, types: [org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
    public static final Unit extractClassMetadata$lambda$0$0(MetadataLibraryBasedSymbolProvider metadataLibraryBasedSymbolProvider, Object obj, ClassId classId, ProtoBuf.Class r21, NameResolver nameResolver, FirModuleData firModuleData, FirDeserializationContext firDeserializationContext, ProtoBuf.PackageFragment packageFragment, FirRegularClassSymbol firRegularClassSymbol) throws KotlinIllegalArgumentExceptionWithAttachments {
        firRegularClassSymbol.getClass();
        ClassDeserializationKt.deserializeClassToSymbol(classId, r21, firRegularClassSymbol, nameResolver, metadataLibraryBasedSymbolProvider.getSession(), firModuleData, metadataLibraryBasedSymbolProvider.annotationDeserializer, metadataLibraryBasedSymbolProvider.getKdocDeserializer(), metadataLibraryBasedSymbolProvider.flexibleTypeFactory, metadataLibraryBasedSymbolProvider.getKotlinScopeProvider(), KlibMetadataSerializerProtocol.INSTANCE, firDeserializationContext, metadataLibraryBasedSymbolProvider.createDeserializedContainerSource(obj, classId.getPackageFqName()), metadataLibraryBasedSymbolProvider.getDefaultDeserializationOrigin(), new MetadataLibraryBasedSymbolProvider$extractClassMetadata$1$1$1(metadataLibraryBasedSymbolProvider), new MetadataLibraryBasedSymbolProvider$extractClassMetadata$1$1$2(metadataLibraryBasedSymbolProvider));
        if (obj instanceof KotlinLibrary) {
            ?? fir = firRegularClassSymbol.getFir();
            GeneratedMessageLite.GeneratedExtension generatedExtension = KlibMetadataProtoBuf.classFile;
            generatedExtension.getClass();
            DeclarationAttributesKt.setKlibSourceFile(fir, metadataLibraryBasedSymbolProvider.loadKlibSourceFileExtensionOrNull((KotlinLibrary) obj, nameResolver, r21, generatedExtension));
        }
        ClassMembersKt.setNewPlaceForBodyGeneration((FirRegularClass) firRegularClassSymbol.getFir(), Boolean.valueOf(metadataLibraryBasedSymbolProvider.isNewPlaceForBodyGeneration(r21)));
        List<FirAnnotation> fileAnnotations = metadataLibraryBasedSymbolProvider.getFileAnnotations(packageFragment, nameResolver);
        if (!fileAnnotations.isEmpty()) {
            DeclarationAttributesKt.setKlibFileAnnotations(firRegularClassSymbol.getFir(), fileAnnotations);
        }
        return Unit.INSTANCE;
    }

    private final List<FirAnnotation> getFileAnnotations(ProtoBuf.PackageFragment fragment, NameResolver nameResolver) {
        IdentityHashMap<ProtoBuf.PackageFragment, List<FirAnnotation>> identityHashMap = this.fragmentToFileAnnotations;
        List<FirAnnotation> listLoadAnnotationsFromMetadata = identityHashMap.get(fragment);
        if (listLoadAnnotationsFromMetadata == null) {
            FirSession session = getSession();
            List fileAnnotationList = fragment.getFileAnnotationList();
            fileAnnotationList.getClass();
            listLoadAnnotationsFromMetadata = AnnotationDeserializationUtilKt.loadAnnotationsFromMetadata(session, fileAnnotationList, nameResolver, AnnotationUseSiteTarget.FILE);
            identityHashMap.put(fragment, listLoadAnnotationsFromMetadata);
        }
        listLoadAnnotationsFromMetadata.getClass();
        return listLoadAnnotationsFromMetadata;
    }

    private final KlibMetadataClassDataFinder getFinder(ProtoBuf.PackageFragment fragment, NameResolver resolver) {
        IdentityHashMap<ProtoBuf.PackageFragment, KlibMetadataClassDataFinder> identityHashMap = this.fragmentToKlibMetadataClassDataFinder;
        KlibMetadataClassDataFinder klibMetadataClassDataFinder = identityHashMap.get(fragment);
        if (klibMetadataClassDataFinder == null) {
            KlibMetadataClassDataFinder klibMetadataClassDataFinder2 = new KlibMetadataClassDataFinder(fragment, resolver, (KlibDeserializedContainerSource) null, 4, (DefaultConstructorMarker) null);
            identityHashMap.put(fragment, klibMetadataClassDataFinder2);
            klibMetadataClassDataFinder = klibMetadataClassDataFinder2;
        }
        return klibMetadataClassDataFinder;
    }

    private final NameResolver getNameResolver(ProtoBuf.PackageFragment fragment) {
        IdentityHashMap<ProtoBuf.PackageFragment, NameResolver> identityHashMap = this.fragmentToNameResolver;
        NameResolverImpl nameResolverImpl = identityHashMap.get(fragment);
        if (nameResolverImpl == null) {
            ProtoBuf.StringTable strings = fragment.getStrings();
            strings.getClass();
            ProtoBuf.QualifiedNameTable qualifiedNames = fragment.getQualifiedNames();
            qualifiedNames.getClass();
            nameResolverImpl = new NameResolverImpl(strings, qualifiedNames);
            identityHashMap.put(fragment, nameResolverImpl);
        }
        return (NameResolver) nameResolverImpl;
    }

    private final ProtoBuf.PackageFragment getPackageFragment(L resolvedLibrary, String packageStringName, String packageMetadataPart) {
        Map<L, Map<Pair<String, String>, ProtoBuf.PackageFragment>> map = this.cachedFragments;
        Map<Pair<String, String>, ProtoBuf.PackageFragment> linkedHashMap = map.get(resolvedLibrary);
        if (linkedHashMap == null) {
            linkedHashMap = new LinkedHashMap<>();
            map.put(resolvedLibrary, linkedHashMap);
        }
        Map<Pair<String, String>, ProtoBuf.PackageFragment> map2 = linkedHashMap;
        Pair<String, String> pair = TuplesKt.to(packageStringName, packageMetadataPart);
        ProtoBuf.PackageFragment packageFragment = map2.get(pair);
        if (packageFragment == null) {
            packageFragment = KlibMetadataDeserializationUtilsKt.parsePackageFragment(((KlibMetadataComponent) this.metadataProvider.invoke(resolvedLibrary)).getPackageFragment(packageStringName, packageMetadataPart));
            map2.put(pair, packageFragment);
        }
        return packageFragment;
    }

    private final <T extends GeneratedMessageLite.ExtendableMessage<T>> DeserializedSourceFile loadKlibSourceFileExtensionOrNull(PackagePartsCacheData packagePart, T proto, GeneratedMessageLite.GeneratedExtension<T, Integer> sourceFileExtension) {
        KotlinLibrary library;
        PackagePartsCacheData.Extra extra = packagePart.getExtra();
        MetadataLibraryPackagePartCacheDataExtra metadataLibraryPackagePartCacheDataExtra = extra instanceof MetadataLibraryPackagePartCacheDataExtra ? (MetadataLibraryPackagePartCacheDataExtra) extra : null;
        if (metadataLibraryPackagePartCacheDataExtra == null || (library = metadataLibraryPackagePartCacheDataExtra.getLibrary()) == null) {
            return null;
        }
        return loadKlibSourceFileExtensionOrNull(library, packagePart.getContext().getNameResolver(), proto, sourceFileExtension);
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider
    public List<PackagePartsCacheData> computePackagePartsInfos(FqName packageFqName) {
        List listEmptyList;
        packageFqName.getClass();
        String strAsString = packageFqName.isRoot() ? Argument.Delimiters.none : packageFqName.asString();
        List list = (List) getFragmentNamesInLibraries().get(strAsString);
        if (list == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            FirModuleData firModuleDataModuleData = moduleData(obj);
            if (firModuleDataModuleData == null) {
                listEmptyList = CollectionsKt.emptyList();
            } else {
                Set packageFragmentNames = ((KlibMetadataComponent) this.metadataProvider.invoke(obj)).getPackageFragmentNames(strAsString);
                ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(packageFragmentNames, 10));
                Iterator it = packageFragmentNames.iterator();
                while (it.hasNext()) {
                    ProtoBuf.PackageFragment packageFragment = getPackageFragment(obj, strAsString, (String) it.next());
                    ProtoBuf.Package r7 = packageFragment.getPackage();
                    NameResolver nameResolver = getNameResolver(packageFragment);
                    r7.getClass();
                    String str = strAsString;
                    FirDeserializationContext firDeserializationContextCreateForPackage = FirDeserializationContext.INSTANCE.createForPackage(packageFqName, r7, nameResolver, firModuleDataModuleData, this.annotationDeserializer, this.flexibleTypeFactory, this.constDeserializer, getKdocDeserializer(), createDeserializedContainerSource(obj, packageFqName));
                    MetadataLibraryPackagePartCacheDataExtra metadataLibraryPackagePartCacheDataExtra = null;
                    KotlinLibrary kotlinLibrary = obj instanceof KotlinLibrary ? (KotlinLibrary) obj : null;
                    if (kotlinLibrary != null) {
                        metadataLibraryPackagePartCacheDataExtra = new MetadataLibraryPackagePartCacheDataExtra(kotlinLibrary);
                    }
                    arrayList2.add(new PackagePartsCacheData(r7, firDeserializationContextCreateForPackage, metadataLibraryPackagePartCacheDataExtra, getFileAnnotations(packageFragment, nameResolver)));
                    strAsString = str;
                }
                listEmptyList = arrayList2;
            }
            String str2 = strAsString;
            CollectionsKt.addAll(arrayList, listEmptyList);
            strAsString = str2;
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider
    public Set<String> computePackageSetWithNonClassDeclarations() {
        return getFragmentNamesInLibraries().keySet();
    }

    public abstract DeserializedContainerSource createDeserializedContainerSource(L resolvedLibrary, FqName packageFqName);

    @Override // org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider
    public AbstractFirDeserializedSymbolProvider.ClassMetadataFindResult extractClassMetadata(ClassId classId, FirDeserializationContext parentContext) {
        final ProtoBuf.Class classProto;
        classId.getClass();
        String strAsString = classId.getPackageFqName().asString();
        List<L> list = getFragmentNamesInLibraries().get(strAsString);
        if (list != null) {
            for (final L l : list) {
                Iterator it = ((KlibMetadataComponent) this.metadataProvider.invoke(l)).getPackageFragmentNames(strAsString).iterator();
                while (it.hasNext()) {
                    final ProtoBuf.PackageFragment packageFragment = this.getPackageFragment(l, strAsString, (String) it.next());
                    final NameResolver nameResolver = this.getNameResolver(packageFragment);
                    ClassData classDataFindClassData = this.getFinder(packageFragment, nameResolver).findClassData(classId);
                    if (classDataFindClassData != null && (classProto = classDataFindClassData.getClassProto()) != null) {
                        final FirModuleData firModuleDataModuleData = this.moduleData(l);
                        if (firModuleDataModuleData == null) {
                            return null;
                        }
                        final MetadataLibraryBasedSymbolProvider<L> metadataLibraryBasedSymbolProvider = this;
                        final ClassId classId2 = classId;
                        final FirDeserializationContext firDeserializationContext = parentContext;
                        return new AbstractFirDeserializedSymbolProvider.ClassMetadataFindResult.NoMetadata(new Function1() { // from class: f0a
                            public final Object invoke(Object obj) {
                                return MetadataLibraryBasedSymbolProvider.extractClassMetadata$lambda$0$0(this.b, l, classId2, classProto, nameResolver, firModuleDataModuleData, firDeserializationContext, packageFragment, (FirRegularClassSymbol) obj);
                            }
                        });
                    }
                    this = this;
                    classId = classId;
                    parentContext = parentContext;
                }
            }
        }
        return null;
    }

    public final KlibCompilerDeserializationConfiguration getDeserializationConfiguration() {
        return this.deserializationConfiguration;
    }

    public abstract Map<String, List<L>> getFragmentNamesInLibraries();

    public abstract Set<FqName> getKnownPackagesInLibraries();

    public final Function1<L, KlibMetadataComponent> getMetadataProvider() {
        return this.metadataProvider;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public boolean hasPackage(FqName fqName) {
        fqName.getClass();
        return getKnownPackagesInLibraries().contains(fqName);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider
    public boolean isNewPlaceForBodyGeneration(ProtoBuf.Class classProto) {
        classProto.getClass();
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider
    public Set<String> knownTopLevelClassesInPackage(FqName packageFqName) {
        packageFqName.getClass();
        Set setCreateSetBuilder = SetsKt.createSetBuilder();
        String strAsString = packageFqName.asString();
        List<L> list = getFragmentNamesInLibraries().get(strAsString);
        if (list != null) {
            for (L l : list) {
                Iterator it = ((KlibMetadataComponent) this.metadataProvider.invoke(l)).getPackageFragmentNames(strAsString).iterator();
                while (it.hasNext()) {
                    ProtoBuf.PackageFragment packageFragment = getPackageFragment(l, strAsString, (String) it.next());
                    NameResolver nameResolver = getNameResolver(packageFragment);
                    List<Integer> listEmptyList = (List) packageFragment.getExtension(KlibMetadataProtoBuf.className);
                    if (listEmptyList == null) {
                        listEmptyList = CollectionsKt.emptyList();
                    }
                    for (Integer num : listEmptyList) {
                        num.getClass();
                        String strAsString2 = NameResolverUtilKt.getClassId(nameResolver, num.intValue()).getShortClassName().asString();
                        strAsString2.getClass();
                        setCreateSetBuilder.add(strAsString2);
                    }
                }
            }
        }
        return SetsKt.build(setCreateSetBuilder);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider
    public void loadFunctionExtensions(PackagePartsCacheData packagePart, ProtoBuf.Function proto, FirFunction fir) {
        packagePart.getClass();
        proto.getClass();
        fir.getClass();
        GeneratedMessageLite.GeneratedExtension<T, Integer> generatedExtension = KlibMetadataProtoBuf.functionFile;
        generatedExtension.getClass();
        DeserializedSourceFile deserializedSourceFileLoadKlibSourceFileExtensionOrNull = loadKlibSourceFileExtensionOrNull(packagePart, proto, generatedExtension);
        if (deserializedSourceFileLoadKlibSourceFileExtensionOrNull != null) {
            DeclarationAttributesKt.setKlibSourceFile(fir, deserializedSourceFileLoadKlibSourceFileExtensionOrNull);
        }
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider
    public void loadPropertyExtensions(PackagePartsCacheData packagePart, ProtoBuf.Property proto, FirProperty fir) {
        packagePart.getClass();
        proto.getClass();
        fir.getClass();
        GeneratedMessageLite.GeneratedExtension<T, Integer> generatedExtension = KlibMetadataProtoBuf.propertyFile;
        generatedExtension.getClass();
        DeserializedSourceFile deserializedSourceFileLoadKlibSourceFileExtensionOrNull = loadKlibSourceFileExtensionOrNull(packagePart, proto, generatedExtension);
        if (deserializedSourceFileLoadKlibSourceFileExtensionOrNull != null) {
            DeclarationAttributesKt.setKlibSourceFile(fir, deserializedSourceFileLoadKlibSourceFileExtensionOrNull);
        }
    }

    public abstract FirModuleData moduleData(L library);

    private final <T extends GeneratedMessageLite.ExtendableMessage<T>> DeserializedSourceFile loadKlibSourceFileExtensionOrNull(KotlinLibrary library, NameResolver nameResolver, T proto, GeneratedMessageLite.GeneratedExtension<T, Integer> sourceFileExtension) {
        String string;
        Integer num = (Integer) ProtoBufUtilKt.getExtensionOrNull(proto, sourceFileExtension);
        if (num == null || (string = nameResolver.getString(num.intValue())) == null) {
            return null;
        }
        return new DeserializedSourceFile(string, library);
    }

    public /* synthetic */ MetadataLibraryBasedSymbolProvider(FirSession firSession, ModuleDataProvider moduleDataProvider, FirKotlinScopeProvider firKotlinScopeProvider, FirTypeDeserializer.FlexibleTypeFactory flexibleTypeFactory, FirDeclarationOrigin firDeclarationOrigin, Function1 function1, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, moduleDataProvider, firKotlinScopeProvider, flexibleTypeFactory, (i & 16) != 0 ? FirDeclarationOrigin.Library.INSTANCE : firDeclarationOrigin, function1);
    }
}
