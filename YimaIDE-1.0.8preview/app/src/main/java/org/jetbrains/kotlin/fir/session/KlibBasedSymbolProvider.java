package org.jetbrains.kotlin.fir.session;

import java.nio.file.Paths;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.AnalysisFlags;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirLanguageSettingsComponentKt;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.deserialization.FirTypeDeserializer;
import org.jetbrains.kotlin.fir.deserialization.ModuleDataProvider;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.fir.session.KlibBasedSymbolProvider;
import org.jetbrains.kotlin.library.KotlinLibrary;
import org.jetbrains.kotlin.library.components.KlibMetadataComponent;
import org.jetbrains.kotlin.library.metadata.KlibDeserializedContainerSource;
import org.jetbrains.kotlin.library.metadata.KlibMetadataDeserializationUtilsKt;
import org.jetbrains.kotlin.library.metadata.KlibMetadataProtoBuf;
import org.jetbrains.kotlin.library.metadata.UtilsKt;
import org.jetbrains.kotlin.metadata.deserialization.MetadataVersion;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.serialization.deserialization.IncompatibleVersionErrorData;
import org.jetbrains.kotlin.util.KlibMetadataHelpersKt;
import org.jetbrains.kotlin.utils.SmartList;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001BA\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\n\u0012\b\b\u0002\u0010\u000b\u001a\u00020\f\u0012\b\b\u0002\u0010\r\u001a\u00020\u000e¢\u0006\u0004\b\u000f\u0010\u0010J\u0012\u0010)\u001a\u0004\u0018\u00010*2\u0006\u0010+\u001a\u00020\u0002H\u0014J\u0018\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020\u00022\u0006\u0010/\u001a\u00020%H\u0014R\u0014\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00020\nX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0013\u001a\n\u0012\u0004\u0012\u00020\u0012\u0018\u00010\u0014*\u00020\u00028BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0016R'\u0010\u0017\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00190\u00188BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u001d\u001a\u0004\b\u001a\u0010\u001bR-\u0010\u001e\u001a\u0014\u0012\u0004\u0012\u00020\u001f\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020 0\u00188TX\u0094\u0084\u0002¢\u0006\f\n\u0004\b\"\u0010\u001d\u001a\u0004\b!\u0010\u001bR!\u0010#\u001a\b\u0012\u0004\u0012\u00020%0$8TX\u0094\u0084\u0002¢\u0006\f\n\u0004\b(\u0010\u001d\u001a\u0004\b&\u0010'¨\u00060"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/KlibBasedSymbolProvider;", "Lorg/jetbrains/kotlin/fir/session/MetadataLibraryBasedSymbolProvider;", "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "moduleDataProvider", "Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;", "kotlinScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "resolvedLibraries", Argument.Delimiters.none, "defaultDeserializationOrigin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "flexibleTypeFactory", "Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer$FlexibleTypeFactory;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/deserialization/ModuleDataProvider;Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;Ljava/util/Collection;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer$FlexibleTypeFactory;)V", "ownMetadataVersion", "Lorg/jetbrains/kotlin/metadata/deserialization/MetadataVersion;", "incompatibility", "Lorg/jetbrains/kotlin/serialization/deserialization/IncompatibleVersionErrorData;", "getIncompatibility", "(Lorg/jetbrains/kotlin/library/KotlinLibrary;)Lorg/jetbrains/kotlin/serialization/deserialization/IncompatibleVersionErrorData;", "moduleHeaders", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/library/metadata/KlibMetadataProtoBuf$Header;", "getModuleHeaders", "()Ljava/util/Map;", "moduleHeaders$delegate", "Lkotlin/Lazy;", "fragmentNamesInLibraries", Argument.Delimiters.none, Argument.Delimiters.none, "getFragmentNamesInLibraries", "fragmentNamesInLibraries$delegate", "knownPackagesInLibraries", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "getKnownPackagesInLibraries", "()Ljava/util/Set;", "knownPackagesInLibraries$delegate", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "library", "createDeserializedContainerSource", "Lorg/jetbrains/kotlin/library/metadata/KlibDeserializedContainerSource;", "resolvedLibrary", "packageFqName", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KlibBasedSymbolProvider extends MetadataLibraryBasedSymbolProvider<KotlinLibrary> {

    /* JADX INFO: renamed from: fragmentNamesInLibraries$delegate, reason: from kotlin metadata */
    private final Lazy fragmentNamesInLibraries;

    /* JADX INFO: renamed from: knownPackagesInLibraries$delegate, reason: from kotlin metadata */
    private final Lazy knownPackagesInLibraries;

    /* JADX INFO: renamed from: moduleHeaders$delegate, reason: from kotlin metadata */
    private final Lazy moduleHeaders;
    private final MetadataVersion ownMetadataVersion;
    private final Collection<KotlinLibrary> resolvedLibraries;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KlibBasedSymbolProvider(FirSession firSession, ModuleDataProvider moduleDataProvider, FirKotlinScopeProvider firKotlinScopeProvider, Collection<? extends KotlinLibrary> collection, FirDeclarationOrigin firDeclarationOrigin, FirTypeDeserializer.FlexibleTypeFactory flexibleTypeFactory) {
        super(firSession, moduleDataProvider, firKotlinScopeProvider, flexibleTypeFactory, firDeclarationOrigin, new Function1() { // from class: s78
            public final Object invoke(Object obj) {
                return KlibBasedSymbolProvider.g((KotlinLibrary) obj);
            }
        });
        firSession.getClass();
        moduleDataProvider.getClass();
        firKotlinScopeProvider.getClass();
        collection.getClass();
        firDeclarationOrigin.getClass();
        flexibleTypeFactory.getClass();
        this.resolvedLibraries = collection;
        this.ownMetadataVersion = KlibMetadataHelpersKt.toKlibMetadataVersion(FirLanguageSettingsComponentKt.getLanguageVersionSettings(firSession).getLanguageVersion());
        this.moduleHeaders = LazyKt.lazy(new Function0() { // from class: t78
            public final Object invoke() {
                return KlibBasedSymbolProvider.j(this.b);
            }
        });
        this.fragmentNamesInLibraries = LazyKt.lazy(new Function0() { // from class: u78
            public final Object invoke() {
                return KlibBasedSymbolProvider.i(this.b);
            }
        });
        this.knownPackagesInLibraries = LazyKt.lazy(new Function0() { // from class: v78
            public final Object invoke() {
                return KlibBasedSymbolProvider.h(this.b);
            }
        });
    }

    public static KlibMetadataComponent g(KotlinLibrary kotlinLibrary) {
        kotlinLibrary.getClass();
        KlibMetadataComponent component = kotlinLibrary.getComponent(KlibMetadataComponent.Kind);
        component.getClass();
        return component;
    }

    private final IncompatibleVersionErrorData<MetadataVersion> getIncompatibility(KotlinLibrary kotlinLibrary) {
        if (((Boolean) FirLanguageSettingsComponentKt.getLanguageVersionSettings(getSession()).getFlag(AnalysisFlags.getSkipMetadataVersionCheck())).booleanValue()) {
            return null;
        }
        return UtilsKt.getIncompatibility(kotlinLibrary, this.ownMetadataVersion);
    }

    private final Map<KotlinLibrary, KlibMetadataProtoBuf.Header> getModuleHeaders() {
        return (Map) this.moduleHeaders.getValue();
    }

    public static Set h(KlibBasedSymbolProvider klibBasedSymbolProvider) {
        Set setCreateSetBuilder = SetsKt.createSetBuilder();
        Iterator<Map.Entry<KotlinLibrary, KlibMetadataProtoBuf.Header>> it = klibBasedSymbolProvider.getModuleHeaders().entrySet().iterator();
        while (it.hasNext()) {
            for (String str : it.next().getValue().getPackageFragmentNameList()) {
                str.getClass();
                for (FqName fqName = new FqName(str); !fqName.isRoot(); fqName = fqName.parent()) {
                    setCreateSetBuilder.add(fqName);
                }
            }
        }
        return SetsKt.build(setCreateSetBuilder);
    }

    public static Map i(KlibBasedSymbolProvider klibBasedSymbolProvider) {
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        for (Map.Entry<KotlinLibrary, KlibMetadataProtoBuf.Header> entry : klibBasedSymbolProvider.getModuleHeaders().entrySet()) {
            KotlinLibrary key = entry.getKey();
            for (String str : entry.getValue().getPackageFragmentNameList()) {
                Object smartList = mapCreateMapBuilder.get(str);
                if (smartList == null) {
                    smartList = new SmartList();
                    mapCreateMapBuilder.put(str, smartList);
                }
                ((SmartList) smartList).add(key);
            }
        }
        return MapsKt.build(mapCreateMapBuilder);
    }

    public static Map j(KlibBasedSymbolProvider klibBasedSymbolProvider) {
        Collection<KotlinLibrary> collection = klibBasedSymbolProvider.resolvedLibraries;
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(collection, 10)), 16));
        for (Object obj : collection) {
            linkedHashMap.put(obj, KlibMetadataDeserializationUtilsKt.parseModuleHeader(((KlibMetadataComponent) klibBasedSymbolProvider.getMetadataProvider().invoke((KotlinLibrary) obj)).getModuleHeaderData()));
        }
        return linkedHashMap;
    }

    @Override // org.jetbrains.kotlin.fir.session.MetadataLibraryBasedSymbolProvider
    public KlibDeserializedContainerSource createDeserializedContainerSource(KotlinLibrary resolvedLibrary, FqName packageFqName) {
        resolvedLibrary.getClass();
        packageFqName.getClass();
        KlibMetadataProtoBuf.Header header = getModuleHeaders().get(resolvedLibrary);
        header.getClass();
        return new KlibDeserializedContainerSource(resolvedLibrary, header, getDeserializationConfiguration(), packageFqName, getIncompatibility(resolvedLibrary));
    }

    @Override // org.jetbrains.kotlin.fir.session.MetadataLibraryBasedSymbolProvider
    public Map<String, List<KotlinLibrary>> getFragmentNamesInLibraries() {
        return (Map) this.fragmentNamesInLibraries.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.session.MetadataLibraryBasedSymbolProvider
    public Set<FqName> getKnownPackagesInLibraries() {
        return (Set) this.knownPackagesInLibraries.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.session.MetadataLibraryBasedSymbolProvider
    public FirModuleData moduleData(KotlinLibrary library) {
        library.getClass();
        return getModuleDataProvider().getModuleData(Paths.get(library.getLibraryFile().getPath(), new String[0]));
    }

    public /* synthetic */ KlibBasedSymbolProvider(FirSession firSession, ModuleDataProvider moduleDataProvider, FirKotlinScopeProvider firKotlinScopeProvider, Collection collection, FirDeclarationOrigin firDeclarationOrigin, FirTypeDeserializer.FlexibleTypeFactory flexibleTypeFactory, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, moduleDataProvider, firKotlinScopeProvider, collection, (i & 16) != 0 ? FirDeclarationOrigin.Library.INSTANCE : firDeclarationOrigin, (i & 32) != 0 ? FirTypeDeserializer.FlexibleTypeFactory.Default.INSTANCE : flexibleTypeFactory);
    }
}
