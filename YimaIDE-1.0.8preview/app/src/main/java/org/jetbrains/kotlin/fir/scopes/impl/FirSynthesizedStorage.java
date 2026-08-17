package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactory;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.scopes.impl.FirClassAnySynthesizedMemberScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirSynthesizedStorage;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0011B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R%\u0010\n\u001a\u0016\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirSynthesizedStorage;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "cachesFactory", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "synthesizedCacheByScope", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirSynthesizedStorage$SynthesizedCache;", Argument.Delimiters.none, "getSynthesizedCacheByScope", "()Lorg/jetbrains/kotlin/fir/caches/FirCache;", "SynthesizedCache", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSynthesizedStorage implements FirSessionComponent {
    private final FirCachesFactory cachesFactory;
    private final FirSession session;
    private final FirCache synthesizedCacheByScope;

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R#\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\n0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirSynthesizedStorage$SynthesizedCache;", Argument.Delimiters.none, "cachesFactory", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "<init>", "(Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;)V", "synthesizedFunction", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/name/Name;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassAnySynthesizedMemberScope;", "getSynthesizedFunction", "()Lorg/jetbrains/kotlin/fir/caches/FirCache;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class SynthesizedCache {
        private final FirCache<Name, FirNamedFunctionSymbol, FirClassAnySynthesizedMemberScope> synthesizedFunction;

        public SynthesizedCache(FirCachesFactory firCachesFactory) {
            firCachesFactory.getClass();
            this.synthesizedFunction = firCachesFactory.createCache(new Function2() { // from class: bf5
                public final Object invoke(Object obj, Object obj2) {
                    return FirSynthesizedStorage.SynthesizedCache.a((Name) obj, (FirClassAnySynthesizedMemberScope) obj2);
                }
            });
        }

        public static FirNamedFunctionSymbol a(Name name, FirClassAnySynthesizedMemberScope firClassAnySynthesizedMemberScope) {
            name.getClass();
            firClassAnySynthesizedMemberScope.getClass();
            return firClassAnySynthesizedMemberScope.generateSyntheticFunctionByName$org_jetbrains_kotlin_providers(name);
        }

        public final FirCache<Name, FirNamedFunctionSymbol, FirClassAnySynthesizedMemberScope> getSynthesizedFunction() {
            return this.synthesizedFunction;
        }
    }

    public FirSynthesizedStorage(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        FirCachesFactory firCachesFactory = FirCachesFactoryKt.getFirCachesFactory(firSession);
        this.cachesFactory = firCachesFactory;
        this.synthesizedCacheByScope = firCachesFactory.createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.scopes.impl.FirSynthesizedStorage$special$$inlined$createCache$1
            public final FirSynthesizedStorage.SynthesizedCache invoke(ConeClassLikeLookupTag coneClassLikeLookupTag, Void r2) {
                coneClassLikeLookupTag.getClass();
                return new FirSynthesizedStorage.SynthesizedCache(FirCachesFactoryKt.getFirCachesFactory(this.this$0.getSession()));
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((ConeClassLikeLookupTag) obj, (Void) obj2);
            }
        });
    }

    public final FirSession getSession() {
        return this.session;
    }

    public final FirCache getSynthesizedCacheByScope() {
        return this.synthesizedCacheByScope;
    }
}
