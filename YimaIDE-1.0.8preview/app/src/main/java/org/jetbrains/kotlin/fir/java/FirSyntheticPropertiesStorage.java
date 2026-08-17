package org.jetbrains.kotlin.fir.java;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactory;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.declarations.FirRegularClass;
import org.jetbrains.kotlin.fir.java.scopes.JavaClassUseSiteMemberScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirTypeIntersectionScopeContext;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirSyntheticPropertySymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000RO\u0010\b\u001a@\u0012\u0004\u0012\u00020\n\u0012.\u0012,\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\f\u0012\u0016\u0012\u0014\u0012\u0004\u0012\u00020\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00100\u000f0\r0\tj\u0002`\u0011\u0012\u0006\u0012\u0004\u0018\u00010\u00120\t¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/fir/java/FirSyntheticPropertiesStorage;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "cachesFactory", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "cacheByOwner", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/declarations/FirRegularClass;", "Lorg/jetbrains/kotlin/fir/java/SyntheticPropertiesCacheKey;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirSyntheticPropertySymbol;", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/java/scopes/JavaClassUseSiteMemberScope;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext$ResultOfIntersection;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirPropertySymbol;", "Lorg/jetbrains/kotlin/fir/java/SyntheticPropertiesCache;", Argument.Delimiters.none, "getCacheByOwner", "()Lorg/jetbrains/kotlin/fir/caches/FirCache;", "org.jetbrains.kotlin:fir-jvm"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSyntheticPropertiesStorage implements FirSessionComponent {
    private final FirCache cacheByOwner;
    private final FirCachesFactory cachesFactory;

    public FirSyntheticPropertiesStorage(FirSession firSession) {
        firSession.getClass();
        FirCachesFactory firCachesFactory = FirCachesFactoryKt.getFirCachesFactory(firSession);
        this.cachesFactory = firCachesFactory;
        this.cacheByOwner = firCachesFactory.createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.java.FirSyntheticPropertiesStorage$special$$inlined$createCache$1
            public final FirCache<? super SyntheticPropertiesCacheKey, ? extends FirSyntheticPropertySymbol, ? super Pair<? extends JavaClassUseSiteMemberScope, ? extends FirTypeIntersectionScopeContext.ResultOfIntersection<FirPropertySymbol>>> invoke(FirRegularClass firRegularClass, Void r2) {
                firRegularClass.getClass();
                return this.this$0.cachesFactory.createCache(new Function2<SyntheticPropertiesCacheKey, Pair<? extends JavaClassUseSiteMemberScope, ? extends FirTypeIntersectionScopeContext.ResultOfIntersection<FirPropertySymbol>>, FirSyntheticPropertySymbol>() { // from class: org.jetbrains.kotlin.fir.java.FirSyntheticPropertiesStorage$cacheByOwner$1$1
                    public final FirSyntheticPropertySymbol invoke(SyntheticPropertiesCacheKey syntheticPropertiesCacheKey, Pair<JavaClassUseSiteMemberScope, ? extends FirTypeIntersectionScopeContext.ResultOfIntersection<FirPropertySymbol>> pair) {
                        syntheticPropertiesCacheKey.getClass();
                        pair.getClass();
                        return ((JavaClassUseSiteMemberScope) pair.component1()).syntheticPropertyFromOverride$org_jetbrains_kotlin_fir_jvm((FirTypeIntersectionScopeContext.ResultOfIntersection) pair.component2());
                    }
                });
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((FirRegularClass) obj, (Void) obj2);
            }
        });
    }

    public final FirCache getCacheByOwner() {
        return this.cacheByOwner;
    }
}
