package org.jetbrains.kotlin.fir.session;

import java.util.Iterator;
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
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.deserialization.FirTypeDeserializer;
import org.jetbrains.kotlin.fir.deserialization.SingleModuleDataProvider;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.fir.session.KlibIcCacheBasedSymbolProvider;
import org.jetbrains.kotlin.fir.session.KlibIcData;
import org.jetbrains.kotlin.library.components.KlibMetadataComponent;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.serialization.deserialization.descriptors.DeserializedContainerSource;
import org.jetbrains.kotlin.utils.SmartList;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0010 \n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B;\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\u0002\u0012\b\b\u0002\u0010\n\u001a\u00020\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0004\b\u000e\u0010\u000fJ\u0010\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0002H\u0014J\u0018\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u00022\u0006\u0010$\u001a\u00020\u001dH\u0014R\u000e\u0010\t\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000R-\u0010\u0013\u001a\u0014\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00020\u00160\u00148TX\u0094\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u0017\u0010\u0018R!\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001d0\u001c8TX\u0094\u0084\u0002¢\u0006\f\n\u0004\b \u0010\u001a\u001a\u0004\b\u001e\u0010\u001f¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/KlibIcCacheBasedSymbolProvider;", "Lorg/jetbrains/kotlin/fir/session/MetadataLibraryBasedSymbolProvider;", "Lorg/jetbrains/kotlin/fir/session/KlibIcData;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "moduleDataProvider", "Lorg/jetbrains/kotlin/fir/deserialization/SingleModuleDataProvider;", "kotlinScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "icData", "defaultDeserializationOrigin", "Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;", "flexibleTypeFactory", "Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer$FlexibleTypeFactory;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/deserialization/SingleModuleDataProvider;Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;Lorg/jetbrains/kotlin/fir/session/KlibIcData;Lorg/jetbrains/kotlin/fir/declarations/FirDeclarationOrigin;Lorg/jetbrains/kotlin/fir/deserialization/FirTypeDeserializer$FlexibleTypeFactory;)V", "moduleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "library", "fragmentNamesInLibraries", Argument.Delimiters.none, Argument.Delimiters.none, Argument.Delimiters.none, "getFragmentNamesInLibraries", "()Ljava/util/Map;", "fragmentNamesInLibraries$delegate", "Lkotlin/Lazy;", "knownPackagesInLibraries", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "getKnownPackagesInLibraries", "()Ljava/util/Set;", "knownPackagesInLibraries$delegate", "createDeserializedContainerSource", "Lorg/jetbrains/kotlin/serialization/deserialization/descriptors/DeserializedContainerSource;", "resolvedLibrary", "packageFqName", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class KlibIcCacheBasedSymbolProvider extends MetadataLibraryBasedSymbolProvider<KlibIcData> {

    /* JADX INFO: renamed from: fragmentNamesInLibraries$delegate, reason: from kotlin metadata */
    private final Lazy fragmentNamesInLibraries;
    private final KlibIcData icData;

    /* JADX INFO: renamed from: knownPackagesInLibraries$delegate, reason: from kotlin metadata */
    private final Lazy knownPackagesInLibraries;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public KlibIcCacheBasedSymbolProvider(FirSession firSession, SingleModuleDataProvider singleModuleDataProvider, FirKotlinScopeProvider firKotlinScopeProvider, KlibIcData klibIcData, FirDeclarationOrigin firDeclarationOrigin, FirTypeDeserializer.FlexibleTypeFactory flexibleTypeFactory) {
        super(firSession, singleModuleDataProvider, firKotlinScopeProvider, flexibleTypeFactory, firDeclarationOrigin, new Function1() { // from class: w78
            public final Object invoke(Object obj) {
                return KlibIcCacheBasedSymbolProvider.i((KlibIcData) obj);
            }
        });
        firSession.getClass();
        singleModuleDataProvider.getClass();
        firKotlinScopeProvider.getClass();
        klibIcData.getClass();
        firDeclarationOrigin.getClass();
        flexibleTypeFactory.getClass();
        this.icData = klibIcData;
        this.fragmentNamesInLibraries = LazyKt.lazy(new Function0() { // from class: x78
            public final Object invoke() {
                return KlibIcCacheBasedSymbolProvider.g(this.b);
            }
        });
        this.knownPackagesInLibraries = LazyKt.lazy(new Function0() { // from class: y78
            public final Object invoke() {
                return KlibIcCacheBasedSymbolProvider.h(this.b);
            }
        });
    }

    public static Map g(KlibIcCacheBasedSymbolProvider klibIcCacheBasedSymbolProvider) {
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        for (String str : klibIcCacheBasedSymbolProvider.icData.getPackageFragmentNameList()) {
            Object smartList = mapCreateMapBuilder.get(str);
            if (smartList == null) {
                smartList = new SmartList();
                mapCreateMapBuilder.put(str, smartList);
            }
            ((SmartList) smartList).add(klibIcCacheBasedSymbolProvider.icData);
        }
        return MapsKt.build(mapCreateMapBuilder);
    }

    public static Set h(KlibIcCacheBasedSymbolProvider klibIcCacheBasedSymbolProvider) {
        Set setCreateSetBuilder = SetsKt.createSetBuilder();
        Iterator it = klibIcCacheBasedSymbolProvider.icData.getPackageFragmentNameList().iterator();
        while (it.hasNext()) {
            for (FqName fqName = new FqName((String) it.next()); !fqName.isRoot(); fqName = fqName.parent()) {
                setCreateSetBuilder.add(fqName);
            }
        }
        return SetsKt.build(setCreateSetBuilder);
    }

    public static KlibMetadataComponent i(KlibIcData klibIcData) {
        klibIcData.getClass();
        return klibIcData;
    }

    @Override // org.jetbrains.kotlin.fir.session.MetadataLibraryBasedSymbolProvider
    public DeserializedContainerSource createDeserializedContainerSource(KlibIcData resolvedLibrary, FqName packageFqName) {
        resolvedLibrary.getClass();
        packageFqName.getClass();
        return new KlibIcDeserializedContainerSource(packageFqName);
    }

    @Override // org.jetbrains.kotlin.fir.session.MetadataLibraryBasedSymbolProvider
    public Map<String, List<KlibIcData>> getFragmentNamesInLibraries() {
        return (Map) this.fragmentNamesInLibraries.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.session.MetadataLibraryBasedSymbolProvider
    public Set<FqName> getKnownPackagesInLibraries() {
        return (Set) this.knownPackagesInLibraries.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.session.MetadataLibraryBasedSymbolProvider
    public FirModuleData moduleData(KlibIcData library) {
        library.getClass();
        return (FirModuleData) CollectionsKt.single(getModuleDataProvider().getAllModuleData());
    }

    public /* synthetic */ KlibIcCacheBasedSymbolProvider(FirSession firSession, SingleModuleDataProvider singleModuleDataProvider, FirKotlinScopeProvider firKotlinScopeProvider, KlibIcData klibIcData, FirDeclarationOrigin firDeclarationOrigin, FirTypeDeserializer.FlexibleTypeFactory flexibleTypeFactory, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, singleModuleDataProvider, firKotlinScopeProvider, klibIcData, (i & 16) != 0 ? FirDeclarationOrigin.Precompiled.INSTANCE : firDeclarationOrigin, (i & 32) != 0 ? FirTypeDeserializer.FlexibleTypeFactory.Default.INSTANCE : flexibleTypeFactory);
    }
}
