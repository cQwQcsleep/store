package org.jetbrains.kotlin.fir.resolve.providers.impl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.resolve.providers.FirCompositeCachedSymbolNamesProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider;
import org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProviderInternals;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.name.CallableId;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u000b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ!\u0010#\u001a\u00020$2\b\u0010%\u001a\u0004\u0018\u00010&2\f\u0010'\u001a\b\u0012\u0004\u0012\u00020)0(H\u0082\bJ\u0006\u0010*\u001a\u00020\u0000J\"\u0010+\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\u00052\u0006\u0010,\u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020-H\u0016J.\u0010.\u001a\u00020$2\u0010\u0010/\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0017002\u0006\u0010,\u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020-H\u0017b\u0002\b1J*\u00102\u001a\u00020$2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u0019002\u0006\u0010,\u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020-H\u0017b\u0002\b1J*\u00103\u001a\u00020$2\f\u0010/\u001a\b\u0012\u0004\u0012\u00020\u001b002\u0006\u0010,\u001a\u00020\u001d2\u0006\u0010\u0010\u001a\u00020-H\u0017b\u0002\b1J\u0010\u00104\u001a\u00020\u00072\u0006\u00105\u001a\u00020\u001dH\u0016J\u0016\u00106\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00122\u0006\u0010\u0011\u001a\u00020\u000eH\u0016J\u001a\u00107\u001a\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\u00052\u0006\u0010\u0016\u001a\u00020\u0015H\u0002J\u0016\u00108\u001a\b\u0012\u0004\u0012\u00020\u00190\u00052\u0006\u0010\u0016\u001a\u00020\u0015H\u0002J\u0016\u00109\u001a\b\u0012\u0004\u0012\u00020\u001b0\u00052\u0006\u0010\u0016\u001a\u00020\u0015H\u0002J\u0010\u0010:\u001a\u00020\u00072\u0006\u0010\u001e\u001a\u00020\u001dH\u0002J\u0016\u0010;\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u00122\u0006\u0010\u0011\u001a\u00020\u000eH\u0002R\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R7\u0010\f\u001a+\u0012\u0013\u0012\u00110\u000e¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0011\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u0012\u0012\u0006\u0012\u0004\u0018\u00010\u00130\rX\u0082\u0004¢\u0006\u0002\n\u0000R;\u0010\u0014\u001a/\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0016\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u00170\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00130\rX\u0082\u0004¢\u0006\u0002\n\u0000R7\u0010\u0018\u001a+\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00190\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00130\rX\u0082\u0004¢\u0006\u0002\n\u0000R7\u0010\u001a\u001a+\u0012\u0013\u0012\u00110\u0015¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u0016\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001b0\u0005\u0012\u0006\u0012\u0004\u0018\u00010\u00130\rX\u0082\u0004¢\u0006\u0002\n\u0000R1\u0010\u001c\u001a%\u0012\u0013\u0012\u00110\u001d¢\u0006\f\b\u000f\u0012\b\b\u0010\u0012\u0004\b\b(\u001e\u0012\u0004\u0012\u00020\u0007\u0012\u0006\u0012\u0004\u0018\u00010\u00130\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u001f\u001a\u00020 X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b!\u0010\"¨\u0006<"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/impl/FirCachingCompositeSymbolProvider;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "providers", Argument.Delimiters.none, "expectedCachesToBeCleanedOnce", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Ljava/util/List;Z)V", "getProviders", "()Ljava/util/List;", "classLikeCache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/name/ClassId;", "Lkotlin/ParameterName;", ModuleXmlParser.NAME, "classId", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", Argument.Delimiters.none, "topLevelCallableCache", "Lorg/jetbrains/kotlin/name/CallableId;", "callableId", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "topLevelFunctionCache", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "topLevelPropertyCache", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "packageCache", "Lorg/jetbrains/kotlin/name/FqName;", "it", "symbolNamesProvider", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "getSymbolNamesProvider", "()Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "ensureNotNull", Argument.Delimiters.none, "v", Argument.Delimiters.none, "representation", "Lkotlin/Function0;", Argument.Delimiters.none, "createCopyWithCleanCaches", "getTopLevelCallableSymbols", "packageFqName", "Lorg/jetbrains/kotlin/name/Name;", "getTopLevelCallableSymbolsTo", "destination", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolProviderInternals;", "getTopLevelFunctionSymbolsTo", "getTopLevelPropertySymbolsTo", "hasPackage", "fqName", "getClassLikeSymbolByClassId", "computeTopLevelCallables", "computeTopLevelFunctions", "computeTopLevelProperties", "computePackage", "computeClass", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirCachingCompositeSymbolProvider extends FirSymbolProvider {
    private final FirCache classLikeCache;
    private final boolean expectedCachesToBeCleanedOnce;
    private final FirCache packageCache;
    private final List<FirSymbolProvider> providers;
    private final FirSymbolNamesProvider symbolNamesProvider;
    private final FirCache topLevelCallableCache;
    private final FirCache topLevelFunctionCache;
    private final FirCache topLevelPropertyCache;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public FirCachingCompositeSymbolProvider(final FirSession firSession, List<? extends FirSymbolProvider> list, boolean z) {
        super(firSession);
        firSession.getClass();
        list.getClass();
        this.providers = list;
        this.expectedCachesToBeCleanedOnce = z;
        this.classLikeCache = FirCachesFactoryKt.getFirCachesFactory(firSession).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.resolve.providers.impl.FirCachingCompositeSymbolProvider$special$$inlined$createCache$1
            public final FirClassLikeSymbol<?> invoke(ClassId classId, Void r2) {
                classId.getClass();
                return this.$receiver$inlined.computeClass(classId);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((ClassId) obj, (Void) obj2);
            }
        });
        this.topLevelCallableCache = FirCachesFactoryKt.getFirCachesFactory(firSession).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.resolve.providers.impl.FirCachingCompositeSymbolProvider$special$$inlined$createCache$2
            public final List<? extends FirCallableSymbol<?>> invoke(CallableId callableId, Void r2) {
                callableId.getClass();
                return this.$receiver$inlined.computeTopLevelCallables(callableId);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((CallableId) obj, (Void) obj2);
            }
        });
        this.topLevelFunctionCache = FirCachesFactoryKt.getFirCachesFactory(firSession).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.resolve.providers.impl.FirCachingCompositeSymbolProvider$special$$inlined$createCache$3
            public final List<? extends FirNamedFunctionSymbol> invoke(CallableId callableId, Void r2) {
                callableId.getClass();
                return this.$receiver$inlined.computeTopLevelFunctions(callableId);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((CallableId) obj, (Void) obj2);
            }
        });
        this.topLevelPropertyCache = FirCachesFactoryKt.getFirCachesFactory(firSession).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.resolve.providers.impl.FirCachingCompositeSymbolProvider$special$$inlined$createCache$4
            public final List<? extends FirPropertySymbol> invoke(CallableId callableId, Void r2) {
                callableId.getClass();
                return this.$receiver$inlined.computeTopLevelProperties(callableId);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((CallableId) obj, (Void) obj2);
            }
        });
        this.packageCache = FirCachesFactoryKt.getFirCachesFactory(firSession).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.resolve.providers.impl.FirCachingCompositeSymbolProvider$special$$inlined$createCache$5
            public final Boolean invoke(FqName fqName, Void r2) {
                fqName.getClass();
                return Boolean.valueOf(this.$receiver$inlined.computePackage(fqName));
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((FqName) obj, (Void) obj2);
            }
        });
        List<? extends FirSymbolProvider> list2 = list;
        final ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(((FirSymbolProvider) it.next()).getSymbolNamesProvider());
        }
        this.symbolNamesProvider = new FirCompositeCachedSymbolNamesProvider(this, arrayList) { // from class: org.jetbrains.kotlin.fir.resolve.providers.impl.FirCachingCompositeSymbolProvider$symbolNamesProvider$1
            final /* synthetic */ FirCachingCompositeSymbolProvider this$0;

            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(this.$session, arrayList);
                this.this$0 = this;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCompositeCachedSymbolNamesProvider, org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider
            public Set<String> computePackageNames() {
                return null;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCompositeCachedSymbolNamesProvider, org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider
            public Set<String> computePackageNamesWithTopLevelCallables() {
                Set<String> setComputePackageNamesWithTopLevelCallables = super.computePackageNamesWithTopLevelCallables();
                FirCachingCompositeSymbolProvider firCachingCompositeSymbolProvider = this.this$0;
                if (setComputePackageNamesWithTopLevelCallables != null || firCachingCompositeSymbolProvider.expectedCachesToBeCleanedOnce) {
                    return setComputePackageNamesWithTopLevelCallables;
                }
                b6c.a("package names with top-level callables is expected to be not null in CLI");
                return null;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCompositeCachedSymbolNamesProvider, org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider
            public Set<String> computePackageNamesWithTopLevelClassifiers() {
                return null;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCompositeCachedSymbolNamesProvider, org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider
            public Set<Name> computeTopLevelCallableNames(FqName packageFqName) {
                packageFqName.getClass();
                Set<Name> setComputeTopLevelCallableNames = super.computeTopLevelCallableNames(packageFqName);
                FirCachingCompositeSymbolProvider firCachingCompositeSymbolProvider = this.this$0;
                if (setComputeTopLevelCallableNames != null || firCachingCompositeSymbolProvider.expectedCachesToBeCleanedOnce) {
                    return setComputeTopLevelCallableNames;
                }
                b6c.a(("callable names in package " + packageFqName).concat(" is expected to be not null in CLI"));
                return null;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCompositeCachedSymbolNamesProvider, org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider
            public Set<Name> computeTopLevelClassifierNames(FqName packageFqName) {
                packageFqName.getClass();
                Set<Name> setComputeTopLevelClassifierNames = super.computeTopLevelClassifierNames(packageFqName);
                FirCachingCompositeSymbolProvider firCachingCompositeSymbolProvider = this.this$0;
                if (setComputeTopLevelClassifierNames != null || firCachingCompositeSymbolProvider.expectedCachesToBeCleanedOnce) {
                    return setComputeTopLevelClassifierNames;
                }
                b6c.a(("classifier names in package " + packageFqName).concat(" is expected to be not null in CLI"));
                return null;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCompositeCachedSymbolNamesProvider, org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public boolean getHasSpecificCallablePackageNamesComputation() {
                return true;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCompositeCachedSymbolNamesProvider, org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public boolean getHasSpecificClassifierPackageNamesComputation() {
                return false;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider, org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<String> getPackageNames() {
                return null;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider, org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<String> getPackageNamesWithTopLevelClassifiers() {
                return null;
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider, org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public Set<Name> getTopLevelClassifierNamesInPackage(FqName packageFqName) {
                packageFqName.getClass();
                return super.getTopLevelClassifierNamesInPackageSkippingPackageCheck(packageFqName);
            }

            @Override // org.jetbrains.kotlin.fir.resolve.providers.FirCompositeCachedSymbolNamesProvider, org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
            public boolean mayHaveSyntheticFunctionType(ClassId classId) {
                classId.getClass();
                return FirSyntheticFunctionInterfaceProviderBase.INSTANCE.isNameForFunctionClass(classId, this.$session);
            }
        };
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirClassLikeSymbol<?> computeClass(ClassId classId) {
        Iterator<T> it = this.providers.iterator();
        while (it.hasNext()) {
            FirClassLikeSymbol<?> classLikeSymbolByClassId = ((FirSymbolProvider) it.next()).getClassLikeSymbolByClassId(classId);
            if (classLikeSymbolByClassId != null) {
                return classLikeSymbolByClassId;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean computePackage(FqName it) {
        List<FirSymbolProvider> list = this.providers;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        Iterator<T> it2 = list.iterator();
        while (it2.hasNext()) {
            if (((FirSymbolProvider) it2.next()).hasPackage(it)) {
                return true;
            }
        }
        return false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<FirCallableSymbol<?>> computeTopLevelCallables(CallableId callableId) {
        List<FirCallableSymbol<?>> listCreateListBuilder = CollectionsKt.createListBuilder();
        Iterator<T> it = this.providers.iterator();
        while (it.hasNext()) {
            ((FirSymbolProvider) it.next()).getTopLevelCallableSymbolsTo(listCreateListBuilder, callableId.getPackageName(), callableId.getCallableName());
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<FirNamedFunctionSymbol> computeTopLevelFunctions(CallableId callableId) {
        List<FirNamedFunctionSymbol> listCreateListBuilder = CollectionsKt.createListBuilder();
        Iterator<T> it = this.providers.iterator();
        while (it.hasNext()) {
            ((FirSymbolProvider) it.next()).getTopLevelFunctionSymbolsTo(listCreateListBuilder, callableId.getPackageName(), callableId.getCallableName());
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<FirPropertySymbol> computeTopLevelProperties(CallableId callableId) {
        List<FirPropertySymbol> listCreateListBuilder = CollectionsKt.createListBuilder();
        Iterator<T> it = this.providers.iterator();
        while (it.hasNext()) {
            ((FirSymbolProvider) it.next()).getTopLevelPropertySymbolsTo(listCreateListBuilder, callableId.getPackageName(), callableId.getCallableName());
        }
        return CollectionsKt.build(listCreateListBuilder);
    }

    public final FirCachingCompositeSymbolProvider createCopyWithCleanCaches() {
        if (this.expectedCachesToBeCleanedOnce) {
            return new FirCachingCompositeSymbolProvider(getSession(), this.providers, false);
        }
        w01.a("Unexpected caches clearing");
        return null;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public FirClassLikeSymbol<?> getClassLikeSymbolByClassId(ClassId classId) {
        classId.getClass();
        if (getSymbolNamesProvider().mayHaveTopLevelClassifier(classId)) {
            return (FirClassLikeSymbol) this.classLikeCache.getValue(classId, null);
        }
        return null;
    }

    public final List<FirSymbolProvider> getProviders() {
        return this.providers;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public FirSymbolNamesProvider getSymbolNamesProvider() {
        return this.symbolNamesProvider;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public List<FirCallableSymbol<?>> getTopLevelCallableSymbols(FqName packageFqName, Name name) {
        packageFqName.getClass();
        name.getClass();
        return !getSymbolNamesProvider().mayHaveTopLevelCallable(packageFqName, name) ? CollectionsKt.emptyList() : (List) this.topLevelCallableCache.getValue(new CallableId(packageFqName, name), null);
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelCallableSymbolsTo(List<FirCallableSymbol<?>> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
        CollectionsKt.addAll(destination, getTopLevelCallableSymbols(packageFqName, name));
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelFunctionSymbolsTo(List<FirNamedFunctionSymbol> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
        if (getSymbolNamesProvider().mayHaveTopLevelCallable(packageFqName, name)) {
            CollectionsKt.addAll(destination, (Iterable) this.topLevelFunctionCache.getValue(new CallableId(packageFqName, name), null));
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    @FirSymbolProviderInternals
    public void getTopLevelPropertySymbolsTo(List<FirPropertySymbol> destination, FqName packageFqName, Name name) {
        destination.getClass();
        packageFqName.getClass();
        name.getClass();
        if (getSymbolNamesProvider().mayHaveTopLevelCallable(packageFqName, name)) {
            CollectionsKt.addAll(destination, (Iterable) this.topLevelPropertyCache.getValue(new CallableId(packageFqName, name), null));
        }
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolProvider
    public boolean hasPackage(FqName fqName) {
        fqName.getClass();
        return ((Boolean) this.packageCache.getValue(fqName, null)).booleanValue();
    }

    public /* synthetic */ FirCachingCompositeSymbolProvider(FirSession firSession, List list, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(firSession, list, (i & 4) != 0 ? false : z);
    }
}
