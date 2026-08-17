package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactory;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirTypealiasConstructorStorage;
import org.jetbrains.kotlin.fir.scopes.impl.TypeAliasConstructorsSubstitutingScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R/\u0010\n\u001a \u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\u000e0\f\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypealiasConstructorStorage;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "cachesFactory", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "cachedConstructors", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "Lorg/jetbrains/kotlin/fir/scopes/impl/TypeAliasConstructorsSubstitutingScope;", "getCachedConstructors", "()Lorg/jetbrains/kotlin/fir/caches/FirCache;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirTypealiasConstructorStorage implements FirSessionComponent {
    private final FirCache<Pair<FirTypeAliasSymbol, FirConstructorSymbol>, FirConstructorSymbol, TypeAliasConstructorsSubstitutingScope> cachedConstructors;
    private final FirCachesFactory cachesFactory;
    private final FirSession session;

    public FirTypealiasConstructorStorage(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        FirCachesFactory firCachesFactory = FirCachesFactoryKt.getFirCachesFactory(firSession);
        this.cachesFactory = firCachesFactory;
        this.cachedConstructors = firCachesFactory.createCache(new Function2() { // from class: fg5
            public final Object invoke(Object obj, Object obj2) {
                return FirTypealiasConstructorStorage.a((Pair) obj, (TypeAliasConstructorsSubstitutingScope) obj2);
            }
        });
    }

    public static FirConstructorSymbol a(Pair pair, TypeAliasConstructorsSubstitutingScope typeAliasConstructorsSubstitutingScope) {
        pair.getClass();
        typeAliasConstructorsSubstitutingScope.getClass();
        return typeAliasConstructorsSubstitutingScope.createTypealiasConstructor((FirConstructorSymbol) pair.getSecond());
    }

    public final FirCache<Pair<FirTypeAliasSymbol, FirConstructorSymbol>, FirConstructorSymbol, TypeAliasConstructorsSubstitutingScope> getCachedConstructors() {
        return this.cachedConstructors;
    }

    public final FirSession getSession() {
        return this.session;
    }
}
