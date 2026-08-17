package org.jetbrains.kotlin.fir.session;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider;
import org.jetbrains.kotlin.fir.deserialization.FirConstDeserializer;
import org.jetbrains.kotlin.fir.deserialization.FirDeserializationContext;
import org.jetbrains.kotlin.fir.deserialization.FirTypeDeserializer;
import org.jetbrains.kotlin.fir.deserialization.MetadataBasedAnnotationDeserializer;
import org.jetbrains.kotlin.fir.deserialization.ModuleDataProvider;
import org.jetbrains.kotlin.fir.deserialization.PackagePartsCacheData;
import org.jetbrains.kotlin.fir.java.deserialization.ProviderUtilsKt;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.load.kotlin.PackageAndMetadataPartProvider;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.NameResolver;
import org.jetbrains.kotlin.metadata.deserialization.NameResolverImpl;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.serialization.deserialization.ClassData;
import org.jetbrains.kotlin.serialization.deserialization.KotlinMetadataFinder;
import org.jetbrains.kotlin.serialization.deserialization.MetadataClassDataFinder;
import org.jetbrains.kotlin.serialization.deserialization.MetadataUtilKt;
import org.jetbrains.kotlin.serialization.deserialization.builtins.BuiltInSerializerProtocol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\u0010\u0001\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0016\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020!0 2\u0006\u0010\u001b\u001a\u00020\u0018H\u0014J\u0010\u0010\"\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001cH\u0014J\u0018\u0010#\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c2\u0006\u0010\u001b\u001a\u00020\u0018H\u0014J\u001c\u0010$\u001a\u0004\u0018\u00010%2\u0006\u0010&\u001a\u00020'2\b\u0010(\u001a\u0004\u0018\u00010)H\u0014J\u0010\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020-H\u0014J\u0010\u0010.\u001a\u00020+2\u0006\u0010/\u001a\u00020\u0018H\u0016J\u0018\u00100\u001a\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c2\u0006\u0010\u001b\u001a\u00020\u0018H\u0002R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R9\u0010\u0016\u001a-\u0012\u0013\u0012\u00110\u0018¢\u0006\f\b\u0019\u0012\b\b\u001a\u0012\u0004\b\b(\u001b\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001d\u0018\u00010\u001c\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u0017X\u0082\u0004¢\u0006\u0002\n\u0000¨\u00061"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/MetadataSymbolProvider;", "Lorg/jetbrains/kotlin/fir/deserialization/AbstractFirDeserializedSymbolProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "moduleDataProvider", "Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;", "kotlinScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "packageAndMetadataPartProvider", "Lorg/jetbrains/kotlin/load/kotlin/PackageAndMetadataPartProvider;", "kotlinClassFinder", "Lorg/jetbrains/kotlin/serialization/deserialization/KotlinMetadataFinder;", "defaultDeserializationOrigin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;Lorg/jetbrains/kotlin/load/kotlin/PackageAndMetadataPartProvider;Lorg/jetbrains/kotlin/serialization/deserialization/KotlinMetadataFinder;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;)V", "classDataFinder", "Lorg/jetbrains/kotlin/serialization/deserialization/MetadataClassDataFinder;", "annotationDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/MetadataBasedAnnotationDeserializer;", "constDeserializer", "Lorg/jetbrains/kotlin/fir/deserialization/FirConstDeserializer;", "metadataTopLevelClassesInPackageCache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/name/FqName;", "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "packageFqName", Argument.Delimiters.none, Argument.Delimiters.none, Argument.Delimiters.none, "computePackagePartsInfos", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/deserialization/PackagePartsCacheData;", "computePackageSetWithNonClassDeclarations", "knownTopLevelClassesInPackage", "extractClassMetadata", "Lorg/jetbrains/kotlin/fir/deserialization/AbstractFirDeserializedSymbolProvider$ClassMetadataFindResult;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "parentContext", "Lorg/jetbrains/kotlin/fir/deserialization/FirDeserializationContext;", "isNewPlaceForBodyGeneration", Argument.Delimiters.none, "classProto", "Lorg/jetbrains/kotlin/metadata/ProtoBuf$Class;", "hasPackage", "fqName", "findMetadataTopLevelClassesInPackage", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MetadataSymbolProvider extends AbstractFirDeserializedSymbolProvider {
    private final MetadataBasedAnnotationDeserializer annotationDeserializer;
    private final MetadataClassDataFinder classDataFinder;
    private final FirConstDeserializer constDeserializer;
    private final KotlinMetadataFinder kotlinClassFinder;
    private final FirCache metadataTopLevelClassesInPackageCache;
    private final PackageAndMetadataPartProvider packageAndMetadataPartProvider;

    /* JADX WARN: Illegal instructions before constructor call */
    public MetadataSymbolProvider(FirSession firSession, ModuleDataProvider moduleDataProvider, FirKotlinScopeProvider firKotlinScopeProvider, PackageAndMetadataPartProvider packageAndMetadataPartProvider, KotlinMetadataFinder kotlinMetadataFinder, FirDeclarationOrigin firDeclarationOrigin) {
        firSession.getClass();
        moduleDataProvider.getClass();
        firKotlinScopeProvider.getClass();
        packageAndMetadataPartProvider.getClass();
        kotlinMetadataFinder.getClass();
        firDeclarationOrigin.getClass();
        BuiltInSerializerProtocol builtInSerializerProtocol = BuiltInSerializerProtocol.INSTANCE;
        super(firSession, moduleDataProvider, firKotlinScopeProvider, firDeclarationOrigin, builtInSerializerProtocol);
        this.packageAndMetadataPartProvider = packageAndMetadataPartProvider;
        this.kotlinClassFinder = kotlinMetadataFinder;
        this.classDataFinder = new MetadataClassDataFinder(kotlinMetadataFinder);
        this.annotationDeserializer = new MetadataBasedAnnotationDeserializer(firSession);
        this.constDeserializer = new FirConstDeserializer(builtInSerializerProtocol);
        this.metadataTopLevelClassesInPackageCache = FirCachesFactoryKt.getFirCachesFactory(firSession).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.session.MetadataSymbolProvider$special$$inlined$createCache$1
            public final Set<? extends String> invoke(FqName fqName, Void r2) {
                fqName.getClass();
                return this.$receiver$inlined.findMetadataTopLevelClassesInPackage(fqName);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((FqName) obj, (Void) obj2);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Set<String> findMetadataTopLevelClassesInPackage(FqName packageFqName) {
        return this.kotlinClassFinder.findMetadataTopLevelClassesInPackage(packageFqName);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider
    public List<PackagePartsCacheData> computePackagePartsInfos(FqName packageFqName) {
        packageFqName.getClass();
        List<String> listFindMetadataPackageParts = this.packageAndMetadataPartProvider.findMetadataPackageParts(packageFqName.asString());
        ArrayList arrayList = new ArrayList();
        for (String str : listFindMetadataPackageParts) {
            PackagePartsCacheData packagePartsCacheData = null;
            if (!ProviderUtilsKt.getKotlinBuiltins().contains(str)) {
                Name nameIdentifier = Name.identifier(str);
                nameIdentifier.getClass();
                InputStream inputStreamFindMetadata = this.kotlinClassFinder.findMetadata(new ClassId(packageFqName, nameIdentifier));
                if (inputStreamFindMetadata != null) {
                    Triple proto = MetadataUtilKt.readProto(inputStreamFindMetadata);
                    ProtoBuf.PackageFragment packageFragment = (ProtoBuf.PackageFragment) proto.component1();
                    NameResolver nameResolver = (NameResolverImpl) proto.component2();
                    FirDeserializationContext.Companion companion = FirDeserializationContext.INSTANCE;
                    ProtoBuf.Package r8 = packageFragment.getPackage();
                    r8.getClass();
                    FirDeserializationContext firDeserializationContextCreateForPackage = companion.createForPackage(packageFqName, r8, nameResolver, (FirModuleData) CollectionsKt.last(getModuleDataProvider().getAllModuleData()), this.annotationDeserializer, FirTypeDeserializer.FlexibleTypeFactory.Default.INSTANCE, this.constDeserializer, getKdocDeserializer(), null);
                    ProtoBuf.Package r17 = packageFragment.getPackage();
                    r17.getClass();
                    packagePartsCacheData = new PackagePartsCacheData(r17, firDeserializationContextCreateForPackage, null, null, 12, null);
                }
            }
            if (packagePartsCacheData != null) {
                arrayList.add(packagePartsCacheData);
            }
        }
        return arrayList;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider
    public Set<String> computePackageSetWithNonClassDeclarations() {
        return this.packageAndMetadataPartProvider.computePackageSetWithNonClassDeclarations();
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider
    public AbstractFirDeserializedSymbolProvider.ClassMetadataFindResult extractClassMetadata(ClassId classId, FirDeserializationContext parentContext) {
        classId.getClass();
        ClassData classDataFindClassData = this.classDataFinder.findClassData(classId);
        if (classDataFindClassData == null) {
            return null;
        }
        return new AbstractFirDeserializedSymbolProvider.ClassMetadataFindResult.Metadata(classDataFindClassData.getNameResolver(), classDataFindClassData.getClassProto(), this.annotationDeserializer, (FirModuleData) CollectionsKt.last(getModuleDataProvider().getAllModuleData()), null, FirTypeDeserializer.FlexibleTypeFactory.Default.INSTANCE);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public boolean hasPackage(FqName fqName) {
        fqName.getClass();
        Set set = (Set) this.metadataTopLevelClassesInPackageCache.getValue(fqName, null);
        return set != null && (set.isEmpty() ^ true);
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider
    public boolean isNewPlaceForBodyGeneration(ProtoBuf.Class classProto) {
        classProto.getClass();
        return false;
    }

    @Override // org.jetbrains.kotlin.fir.deserialization.AbstractFirDeserializedSymbolProvider
    public Set<String> knownTopLevelClassesInPackage(FqName packageFqName) {
        packageFqName.getClass();
        return (Set) this.metadataTopLevelClassesInPackageCache.getValue(packageFqName, null);
    }

    public /* synthetic */ MetadataSymbolProvider(FirSession firSession, ModuleDataProvider moduleDataProvider, FirKotlinScopeProvider firKotlinScopeProvider, PackageAndMetadataPartProvider packageAndMetadataPartProvider, KotlinMetadataFinder kotlinMetadataFinder, FirDeclarationOrigin firDeclarationOrigin, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, moduleDataProvider, firKotlinScopeProvider, packageAndMetadataPartProvider, kotlinMetadataFinder, (i & 32) != 0 ? FirDeclarationOrigin.Library.INSTANCE : firDeclarationOrigin);
    }
}
