package org.jetbrains.kotlin.fir.session;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirModuleData;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProviderWithoutCallables;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderInternals;
import org.jetbrains.kotlin.fir.scopes.FirKotlinScopeProvider;
import org.jetbrains.kotlin.fir.session.NativeForwardDeclarationsSymbolProvider;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.library.KotlinLibrary;
import org.jetbrains.kotlin.library.KotlinLibraryKt;
import org.jetbrains.kotlin.library.metadata.UtilsKt;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.name.NativeForwardDeclarationKind;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0090\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u0000 82\u00020\u0001:\u00018B-\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\t¢\u0006\u0004\b\u000b\u0010\fJ\u0016\u0010\u001d\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020\u0011H\u0016J\u0016\u0010%\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020\u0011H\u0002J.\u0010&\u001a\u00020'2\u0010\u0010(\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030*0)2\u0006\u0010+\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u0019H\u0017b\u0002\b,J*\u0010-\u001a\u00020'2\f\u0010(\u001a\b\u0012\u0004\u0012\u00020.0)2\u0006\u0010+\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u0019H\u0017b\u0002\b,J*\u0010/\u001a\u00020'2\f\u0010(\u001a\b\u0012\u0004\u0012\u0002000)2\u0006\u0010+\u001a\u00020\u00182\u0006\u0010#\u001a\u00020\u0019H\u0017b\u0002\b,J\u0010\u00101\u001a\u0002022\u0006\u00103\u001a\u00020\u0018H\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004¢\u0006\u0002\n\u0000R!\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00108BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0012\u0010\u0013R-\u0010\u0016\u001a\u0014\u0012\u0004\u0012\u00020\u0018\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u00100\u00178BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u001c\u0010\u0015\u001a\u0004\b\u001a\u0010\u001bR7\u0010 \u001a+\u0012\u0013\u0012\u00110\u0011¢\u0006\f\b\"\u0012\b\b#\u0012\u0004\b\b(\u001f\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u001e\u0012\u0006\u0012\u0004\u0018\u00010$0!X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u00104\u001a\u000205X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b6\u00107¨\u00069"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/NativeForwardDeclarationsSymbolProvider;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "forwardDeclarationsModuleData", "Lorg/jetbrains/kotlin/fir/FirModuleData;", "kotlinScopeProvider", "Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;", "kotlinLibraries", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/library/KotlinLibrary;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/FirModuleData;Lorg/jetbrains/kotlin/fir/scopes/FirKotlinScopeProvider;Ljava/util/Collection;)V", "getForwardDeclarationsModuleData", "()Lorg/jetbrains/kotlin/fir/FirModuleData;", "includedForwardDeclarations", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/ClassId;", "getIncludedForwardDeclarations", "()Ljava/util/Set;", "includedForwardDeclarations$delegate", "Lkotlin/Lazy;", "includedForwardDeclarationsByPackage", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "Lorg/jetbrains/kotlin/name/Name;", "getIncludedForwardDeclarationsByPackage", "()Ljava/util/Map;", "includedForwardDeclarationsByPackage$delegate", "getClassLikeSymbolByClassId", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "classId", "syntheticForwardDeclarationClassCache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lkotlin/ParameterName;", ModuleXmlParser.NAME, Argument.Delimiters.none, "createSyntheticForwardDeclarationClass", "getTopLevelCallableSymbolsTo", Argument.Delimiters.none, "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "packageFqName", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProviderInternals;", "getTopLevelFunctionSymbolsTo", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "getTopLevelPropertySymbolsTo", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "hasPackage", Argument.Delimiters.none, "fqName", "symbolNamesProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "getSymbolNamesProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "Companion", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class NativeForwardDeclarationsSymbolProvider extends FirSymbolProvider {
    private static final Companion Companion = new Companion(null);
    private static final Set<FqName> validPackages = NativeForwardDeclarationKind.Companion.getPackageFqNameToKind().keySet();
    private final FirModuleData forwardDeclarationsModuleData;

    /* JADX INFO: renamed from: includedForwardDeclarations$delegate, reason: from kotlin metadata */
    private final Lazy includedForwardDeclarations;

    /* JADX INFO: renamed from: includedForwardDeclarationsByPackage$delegate, reason: from kotlin metadata */
    private final Lazy includedForwardDeclarationsByPackage;
    private final Collection<KotlinLibrary> kotlinLibraries;
    private final FirKotlinScopeProvider kotlinScopeProvider;
    private final FirSymbolNamesProvider symbolNamesProvider;
    private final FirCache syntheticForwardDeclarationClassCache;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public NativeForwardDeclarationsSymbolProvider(FirSession firSession, FirModuleData firModuleData, FirKotlinScopeProvider firKotlinScopeProvider, Collection<? extends KotlinLibrary> collection) {
        super(firSession);
        firSession.getClass();
        firModuleData.getClass();
        firKotlinScopeProvider.getClass();
        collection.getClass();
        this.forwardDeclarationsModuleData = firModuleData;
        this.kotlinScopeProvider = firKotlinScopeProvider;
        this.kotlinLibraries = collection;
        this.includedForwardDeclarations = LazyKt.lazy(new Function0() { // from class: bca
            public final Object invoke() {
                return NativeForwardDeclarationsSymbolProvider.b(this.b);
            }
        });
        this.includedForwardDeclarationsByPackage = LazyKt.lazy(new Function0() { // from class: cca
            public final Object invoke() {
                return NativeForwardDeclarationsSymbolProvider.a(this.b);
            }
        });
        this.syntheticForwardDeclarationClassCache = FirCachesFactoryKt.getFirCachesFactory(firSession).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.session.NativeForwardDeclarationsSymbolProvider$special$$inlined$createCache$1
            public final FirClassLikeSymbol<?> invoke(ClassId classId, Void r2) {
                classId.getClass();
                return this.$receiver$inlined.createSyntheticForwardDeclarationClass(classId);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((ClassId) obj, (Void) obj2);
            }
        });
        this.symbolNamesProvider = new FirSymbolNamesProviderWithoutCallables() { // from class: org.jetbrains.kotlin.fir.session.NativeForwardDeclarationsSymbolProvider$symbolNamesProvider$1
            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public boolean getHasSpecificClassifierPackageNamesComputation() {
                return true;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<String> getPackageNamesWithTopLevelClassifiers() {
                Set setKeySet = this.this$0.getIncludedForwardDeclarationsByPackage().keySet();
                if (setKeySet.isEmpty()) {
                    return SetsKt.emptySet();
                }
                LinkedHashSet linkedHashSet = new LinkedHashSet();
                Iterator it = setKeySet.iterator();
                while (it.hasNext()) {
                    linkedHashSet.add(((FqName) it.next()).asString());
                }
                return linkedHashSet;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<Name> getTopLevelClassifierNamesInPackage(FqName packageFqName) {
                packageFqName.getClass();
                Set<Name> set = (Set) this.this$0.getIncludedForwardDeclarationsByPackage().get(packageFqName);
                return set == null ? SetsKt.emptySet() : set;
            }
        };
    }

    public static Map a(NativeForwardDeclarationsSymbolProvider nativeForwardDeclarationsSymbolProvider) {
        Map mapCreateMapBuilder = MapsKt.createMapBuilder();
        for (ClassId classId : nativeForwardDeclarationsSymbolProvider.getIncludedForwardDeclarations()) {
            FqName packageFqName = classId.getPackageFqName();
            Object linkedHashSet = mapCreateMapBuilder.get(packageFqName);
            if (linkedHashSet == null) {
                linkedHashSet = new LinkedHashSet();
                mapCreateMapBuilder.put(packageFqName, linkedHashSet);
            }
            ((Set) linkedHashSet).add(classId.getShortClassName());
        }
        return MapsKt.build(mapCreateMapBuilder);
    }

    public static Set b(NativeForwardDeclarationsSymbolProvider nativeForwardDeclarationsSymbolProvider) {
        Set setCreateSetBuilder = SetsKt.createSetBuilder();
        for (KotlinLibrary kotlinLibrary : nativeForwardDeclarationsSymbolProvider.kotlinLibraries) {
            if (UtilsKt.isCInteropLibrary(kotlinLibrary) || UtilsKt.isCommonizedCInteropLibrary(kotlinLibrary)) {
                Iterator it = KotlinLibraryKt.getIncludedForwardDeclarations(kotlinLibrary).iterator();
                while (it.hasNext()) {
                    ClassId classId = ClassId.Companion.topLevel(new FqName((String) it.next()));
                    if (validPackages.contains(classId.getPackageFqName())) {
                        setCreateSetBuilder.add(classId);
                    }
                }
            }
        }
        return SetsKt.build(setCreateSetBuilder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirClassLikeSymbol<?> createSyntheticForwardDeclarationClass(ClassId classId) {
        return NativeForwardDeclarationsSymbolProviderKt.createSyntheticForwardDeclarationClass$default(classId, this.forwardDeclarationsModuleData, getSession(), this.kotlinScopeProvider, null, 16, null);
    }

    private final Set<ClassId> getIncludedForwardDeclarations() {
        return (Set) this.includedForwardDeclarations.getValue();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final Map<FqName, Set<Name>> getIncludedForwardDeclarationsByPackage() {
        return (Map) this.includedForwardDeclarationsByPackage.getValue();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public FirClassLikeSymbol<?> getClassLikeSymbolByClassId(ClassId classId) {
        classId.getClass();
        if (getIncludedForwardDeclarations().contains(classId) && !classId.isNestedClass()) {
            return (FirClassLikeSymbol) this.syntheticForwardDeclarationClassCache.getValue(classId, null);
        }
        return null;
    }

    public final FirModuleData getForwardDeclarationsModuleData() {
        return this.forwardDeclarationsModuleData;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public FirSymbolNamesProvider getSymbolNamesProvider() {
        return this.symbolNamesProvider;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelCallableSymbolsTo(List<FirCallableSymbol<?>> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelFunctionSymbolsTo(List<FirNamedFunctionSymbol> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelPropertySymbolsTo(List<FirPropertySymbol> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public boolean hasPackage(FqName fqName) {
        fqName.getClass();
        return getIncludedForwardDeclarationsByPackage().containsKey(fqName);
    }

    @Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0007"}, d2 = {"Lorg/jetbrains/kotlin/fir/session/NativeForwardDeclarationsSymbolProvider$Companion;", Argument.Delimiters.none, "<init>", "()V", "validPackages", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/FqName;", "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }
}
