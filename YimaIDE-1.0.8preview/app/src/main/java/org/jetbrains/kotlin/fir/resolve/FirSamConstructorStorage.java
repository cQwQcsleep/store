package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.resolve.FirSamConstructorStorage;
import org.jetbrains.kotlin.fir.resolve.FirSamResolver;
import org.jetbrains.kotlin.fir.symbols.impl.FirAnonymousObjectSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassLikeSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R)\u0010\u0006\u001a\u001a\u0012\b\u0012\u0006\u0012\u0002\b\u00030\b\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\n0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/FirSamConstructorStorage;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "samConstructors", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "Lorg/jetbrains/kotlin/fir/resolve/FirSamResolver;", "getSamConstructors", "()Lorg/jetbrains/kotlin/fir/caches/FirCache;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSamConstructorStorage implements FirSessionComponent {
    private final FirCache<FirClassLikeSymbol<?>, FirNamedFunctionSymbol, FirSamResolver> samConstructors;

    public FirSamConstructorStorage(FirSession firSession) {
        firSession.getClass();
        this.samConstructors = FirCachesFactoryKt.getFirCachesFactory(firSession).createCache(new Function2() { // from class: lc5
            public final Object invoke(Object obj, Object obj2) {
                return FirSamConstructorStorage.a((FirClassLikeSymbol) obj, (FirSamResolver) obj2);
            }
        });
    }

    public static FirNamedFunctionSymbol a(FirClassLikeSymbol firClassLikeSymbol, FirSamResolver firSamResolver) {
        firClassLikeSymbol.getClass();
        firSamResolver.getClass();
        if (firClassLikeSymbol instanceof FirRegularClassSymbol) {
            return firSamResolver.buildSamConstructorForRegularClass((FirRegularClassSymbol) firClassLikeSymbol);
        }
        if (firClassLikeSymbol instanceof FirTypeAliasSymbol) {
            return firSamResolver.buildSamConstructorForTypeAlias((FirTypeAliasSymbol) firClassLikeSymbol);
        }
        if (firClassLikeSymbol instanceof FirAnonymousObjectSymbol) {
            return null;
        }
        bu8.a();
        return null;
    }

    public final FirCache<FirClassLikeSymbol<?>, FirNamedFunctionSymbol, FirSamResolver> getSamConstructors() {
        return this.samConstructors;
    }
}
