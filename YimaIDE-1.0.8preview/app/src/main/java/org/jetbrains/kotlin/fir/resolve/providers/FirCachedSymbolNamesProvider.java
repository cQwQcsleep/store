package org.jetbrains.kotlin.fir.resolve.providers;

import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.LazyThreadSafetyMode;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.PropertyReference1Impl;
import kotlin.reflect.KProperty;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCacheWithPostComputeKt;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.caches.FirLazyValue;
import org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\"\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0001\n\u0002\b\u0011\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0010\u0010\b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tH&J\u0010\u0010\u000b\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tH&J\u0018\u0010\f\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\t2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010\u0010\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tH&J\u0018\u0010\u0011\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\t2\u0006\u0010\u000e\u001a\u00020\u000fH&J\u0010\u0010)\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tH\u0016J\u0010\u0010*\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tH\u0016J\u0018\u0010+\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016J\u0018\u0010,\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0004J\u0010\u0010-\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\tH\u0016J\u0018\u0010.\u001a\n\u0012\u0004\u0012\u00020\r\u0018\u00010\t2\u0006\u0010\u000e\u001a\u00020\u000fH\u0016R\u0014\u0010\u0002\u001a\u00020\u0003X\u0084\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R#\u0010\u0012\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0015\u0010\u0016\u001a\u0004\b\u0013\u0010\u0014R#\u0010\u0017\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0019\u0010\u0016\u001a\u0004\b\u0018\u0010\u0014RF\u0010\u001a\u001a-\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u000e\u0012\f\u0012\n\u0012\u0004\u0012\u00020\r\u0018\u00010\t\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b!\u0010\"\u001a\u0004\b\u001f\u0010 R#\u0010#\u001a\n\u0012\u0004\u0012\u00020\n\u0018\u00010\t8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b%\u0010\u0016\u001a\u0004\b$\u0010\u0014RF\u0010&\u001a-\u0012\u0013\u0012\u00110\u000f¢\u0006\f\b\u001c\u0012\b\b\u001d\u0012\u0004\b\b(\u000e\u0012\f\u0012\n\u0012\u0004\u0012\u00020\r\u0018\u00010\t\u0012\u0006\u0012\u0004\u0018\u00010\u001e0\u001b8BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b(\u0010\"\u001a\u0004\b'\u0010 ¨\u0006/"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/providers/FirCachedSymbolNamesProvider;", "Lorg/jetbrains/kotlin/fir/resolve/providers/FirSymbolNamesProvider;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "computePackageNames", Argument.Delimiters.none, Argument.Delimiters.none, "computePackageNamesWithTopLevelClassifiers", "computeTopLevelClassifierNames", "Lorg/jetbrains/kotlin/name/Name;", "packageFqName", "Lorg/jetbrains/kotlin/name/FqName;", "computePackageNamesWithTopLevelCallables", "computeTopLevelCallableNames", "cachedPackageNames", "getCachedPackageNames", "()Ljava/util/Set;", "cachedPackageNames$delegate", "Lkotlin/Lazy;", "topLevelClassifierPackageNames", "getTopLevelClassifierPackageNames", "topLevelClassifierPackageNames$delegate", "topLevelClassifierNamesByPackage", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lkotlin/ParameterName;", ModuleXmlParser.NAME, Argument.Delimiters.none, "getTopLevelClassifierNamesByPackage", "()Lorg/jetbrains/kotlin/fir/caches/FirCache;", "topLevelClassifierNamesByPackage$delegate", "Lorg/jetbrains/kotlin/fir/caches/FirLazyValue;", "topLevelCallablePackageNames", "getTopLevelCallablePackageNames", "topLevelCallablePackageNames$delegate", "topLevelCallableNamesByPackage", "getTopLevelCallableNamesByPackage", "topLevelCallableNamesByPackage$delegate", "getPackageNames", "getPackageNamesWithTopLevelClassifiers", "getTopLevelClassifierNamesInPackage", "getTopLevelClassifierNamesInPackageSkippingPackageCheck", "getPackageNamesWithTopLevelCallables", "getTopLevelCallableNamesInPackage", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirCachedSymbolNamesProvider extends FirSymbolNamesProvider {
    static final /* synthetic */ KProperty<Object>[] $$delegatedProperties = {new PropertyReference1Impl<>(FirCachedSymbolNamesProvider.class, "topLevelClassifierNamesByPackage", "getTopLevelClassifierNamesByPackage()Lorg/jetbrains/kotlin/fir/caches/FirCache;", 0), new PropertyReference1Impl<>(FirCachedSymbolNamesProvider.class, "topLevelCallableNamesByPackage", "getTopLevelCallableNamesByPackage()Lorg/jetbrains/kotlin/fir/caches/FirCache;", 0)};

    /* JADX INFO: renamed from: cachedPackageNames$delegate, reason: from kotlin metadata */
    private final Lazy cachedPackageNames;
    private final FirSession session;

    /* JADX INFO: renamed from: topLevelCallableNamesByPackage$delegate, reason: from kotlin metadata */
    private final FirLazyValue topLevelCallableNamesByPackage;

    /* JADX INFO: renamed from: topLevelCallablePackageNames$delegate, reason: from kotlin metadata */
    private final Lazy topLevelCallablePackageNames;

    /* JADX INFO: renamed from: topLevelClassifierNamesByPackage$delegate, reason: from kotlin metadata */
    private final FirLazyValue topLevelClassifierNamesByPackage;

    /* JADX INFO: renamed from: topLevelClassifierPackageNames$delegate, reason: from kotlin metadata */
    private final Lazy topLevelClassifierPackageNames;

    public FirCachedSymbolNamesProvider(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        LazyThreadSafetyMode lazyThreadSafetyMode = LazyThreadSafetyMode.PUBLICATION;
        this.cachedPackageNames = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: oy4
            public final Object invoke() {
                return FirCachedSymbolNamesProvider.b(this.b);
            }
        });
        this.topLevelClassifierPackageNames = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: py4
            public final Object invoke() {
                return FirCachedSymbolNamesProvider.e(this.b);
            }
        });
        this.topLevelClassifierNamesByPackage = FirCachesFactoryKt.getFirCachesFactory(firSession).createPossiblySoftLazyValue(new Function0() { // from class: qy4
            public final Object invoke() {
                return FirCachedSymbolNamesProvider.a(this.b);
            }
        });
        this.topLevelCallablePackageNames = LazyKt.lazy(lazyThreadSafetyMode, new Function0() { // from class: ry4
            public final Object invoke() {
                return FirCachedSymbolNamesProvider.d(this.b);
            }
        });
        this.topLevelCallableNamesByPackage = FirCachesFactoryKt.getFirCachesFactory(firSession).createPossiblySoftLazyValue(new Function0() { // from class: sy4
            public final Object invoke() {
                return FirCachedSymbolNamesProvider.c(this.b);
            }
        });
    }

    public static FirCache a(final FirCachedSymbolNamesProvider firCachedSymbolNamesProvider) {
        return FirCachesFactoryKt.getFirCachesFactory(firCachedSymbolNamesProvider.session).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider$topLevelClassifierNamesByPackage_delegate$lambda$0$$inlined$createCache$1
            public final Set<? extends Name> invoke(FqName fqName, Void r2) {
                fqName.getClass();
                return this.$receiver$inlined.computeTopLevelClassifierNames(fqName);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((FqName) obj, (Void) obj2);
            }
        });
    }

    public static Set b(FirCachedSymbolNamesProvider firCachedSymbolNamesProvider) {
        return firCachedSymbolNamesProvider.computePackageNames();
    }

    public static FirCache c(final FirCachedSymbolNamesProvider firCachedSymbolNamesProvider) {
        return FirCachesFactoryKt.getFirCachesFactory(firCachedSymbolNamesProvider.session).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.resolve.providers.FirCachedSymbolNamesProvider$topLevelCallableNamesByPackage_delegate$lambda$0$$inlined$createCache$1
            public final Set<? extends Name> invoke(FqName fqName, Void r2) {
                fqName.getClass();
                return this.$receiver$inlined.computeTopLevelCallableNames(fqName);
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((FqName) obj, (Void) obj2);
            }
        });
    }

    public static Set d(FirCachedSymbolNamesProvider firCachedSymbolNamesProvider) {
        Set<String> setComputePackageNamesWithTopLevelCallables;
        return (!firCachedSymbolNamesProvider.getHasSpecificCallablePackageNamesComputation() || (setComputePackageNamesWithTopLevelCallables = firCachedSymbolNamesProvider.computePackageNamesWithTopLevelCallables()) == null) ? firCachedSymbolNamesProvider.getCachedPackageNames() : setComputePackageNamesWithTopLevelCallables;
    }

    public static Set e(FirCachedSymbolNamesProvider firCachedSymbolNamesProvider) {
        Set<String> setComputePackageNamesWithTopLevelClassifiers;
        return (!firCachedSymbolNamesProvider.getHasSpecificClassifierPackageNamesComputation() || (setComputePackageNamesWithTopLevelClassifiers = firCachedSymbolNamesProvider.computePackageNamesWithTopLevelClassifiers()) == null) ? firCachedSymbolNamesProvider.getCachedPackageNames() : setComputePackageNamesWithTopLevelClassifiers;
    }

    private final Set<String> getCachedPackageNames() {
        return (Set) this.cachedPackageNames.getValue();
    }

    private final FirCache getTopLevelCallableNamesByPackage() {
        return (FirCache) FirCacheWithPostComputeKt.getValue(this.topLevelCallableNamesByPackage, this, $$delegatedProperties[1]);
    }

    private final Set<String> getTopLevelCallablePackageNames() {
        return (Set) this.topLevelCallablePackageNames.getValue();
    }

    private final FirCache getTopLevelClassifierNamesByPackage() {
        return (FirCache) FirCacheWithPostComputeKt.getValue(this.topLevelClassifierNamesByPackage, this, $$delegatedProperties[0]);
    }

    private final Set<String> getTopLevelClassifierPackageNames() {
        return (Set) this.topLevelClassifierPackageNames.getValue();
    }

    public abstract Set<String> computePackageNames();

    public abstract Set<String> computePackageNamesWithTopLevelCallables();

    public abstract Set<String> computePackageNamesWithTopLevelClassifiers();

    public abstract Set<Name> computeTopLevelCallableNames(FqName packageFqName);

    public abstract Set<Name> computeTopLevelClassifierNames(FqName packageFqName);

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
    public Set<String> getPackageNames() {
        return getCachedPackageNames();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
    public Set<String> getPackageNamesWithTopLevelCallables() {
        return getTopLevelCallablePackageNames();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
    public Set<String> getPackageNamesWithTopLevelClassifiers() {
        return getTopLevelClassifierPackageNames();
    }

    public final FirSession getSession() {
        return this.session;
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
    public Set<Name> getTopLevelCallableNamesInPackage(FqName packageFqName) {
        packageFqName.getClass();
        Set<String> packageNamesWithTopLevelCallables = getPackageNamesWithTopLevelCallables();
        return (packageNamesWithTopLevelCallables == null || packageNamesWithTopLevelCallables.contains(packageFqName.asString())) ? (Set) getTopLevelCallableNamesByPackage().getValue(packageFqName, null) : SetsKt.emptySet();
    }

    @Override // org.jetbrains.kotlin.fir.resolve.providers.FirSymbolNamesProvider
    public Set<Name> getTopLevelClassifierNamesInPackage(FqName packageFqName) {
        packageFqName.getClass();
        Set<String> packageNamesWithTopLevelClassifiers = getPackageNamesWithTopLevelClassifiers();
        return (packageNamesWithTopLevelClassifiers == null || packageNamesWithTopLevelClassifiers.contains(packageFqName.asString())) ? getTopLevelClassifierNamesInPackageSkippingPackageCheck(packageFqName) : SetsKt.emptySet();
    }

    public final Set<Name> getTopLevelClassifierNamesInPackageSkippingPackageCheck(FqName packageFqName) {
        packageFqName.getClass();
        return (Set) getTopLevelClassifierNamesByPackage().getValue(packageFqName, null);
    }
}
