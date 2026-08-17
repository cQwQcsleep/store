package org.jetbrains.kotlin.fir.declarations;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.symbols.FirBasedSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;
import org.jetbrains.kotlin.resolve.multiplatform.ExpectActualMatchingCompatibility;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007RU\u0010\b\u001aF\u0012\u0004\u0012\u00020\n\u00124\u00122\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0004\u0012\u00020\n0\f\u0012\u0014\u0012\u0012\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u0004\u0012\u00020\u000e0\u000b0\u000bj\u0002`\u000f\u0012\u0006\u0012\u0004\u0018\u00010\u00100\t¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/FirExpectActualMappingStorage;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "cache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "Ljava/util/concurrent/ConcurrentMap;", "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/fir/symbols/FirBasedSymbol;", "Lorg/jetbrains/kotlin/resolve/multiplatform/ExpectActualMatchingCompatibility;", "Lorg/jetbrains/kotlin/fir/declarations/MemberExpectForActualData;", Argument.Delimiters.none, "getCache", "()Lorg/jetbrains/kotlin/fir/caches/FirCache;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirExpectActualMappingStorage implements FirSessionComponent {
    private final FirCache cache;
    private final FirSession session;

    public FirExpectActualMappingStorage(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        this.cache = FirCachesFactoryKt.getFirCachesFactory(firSession).createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.declarations.FirExpectActualMappingStorage$cache$1
            public final ConcurrentHashMap<Pair<FirBasedSymbol<?>, FirRegularClassSymbol>, ConcurrentMap<FirBasedSymbol<?>, ExpectActualMatchingCompatibility>> invoke(FirRegularClassSymbol firRegularClassSymbol, Void r2) {
                firRegularClassSymbol.getClass();
                return new ConcurrentHashMap<>();
            }
        });
    }

    public final FirCache getCache() {
        return this.cache;
    }

    public final FirSession getSession() {
        return this.session;
    }
}
