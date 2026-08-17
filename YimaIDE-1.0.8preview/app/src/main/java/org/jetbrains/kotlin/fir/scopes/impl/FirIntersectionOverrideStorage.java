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
import org.jetbrains.kotlin.fir.scopes.MemberWithBaseScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;
import org.jetbrains.kotlin.fir.types.ConeKotlinType;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000RM\u0010\n\u001a>\u0012\u0004\u0012\u00020\f\u0012,\u0012*\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r\u0012\u000e\u0012\f\u0012\b\u0012\u0006\u0012\u0002\b\u00030\r0\u000e\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u000f0\u000bj\u0002`\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirIntersectionOverrideStorage;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "cachesFactory", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "cacheByScope", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/types/ConeKotlinType;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirTypeIntersectionScopeContext$ResultOfIntersection$NonTrivial;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirIntersectionOverrideCache;", Argument.Delimiters.none, "getCacheByScope", "()Lorg/jetbrains/kotlin/fir/caches/FirCache;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirIntersectionOverrideStorage implements FirSessionComponent {
    private final FirCache cacheByScope;
    private final FirCachesFactory cachesFactory;
    private final FirSession session;

    public FirIntersectionOverrideStorage(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        FirCachesFactory firCachesFactory = FirCachesFactoryKt.getFirCachesFactory(firSession);
        this.cachesFactory = firCachesFactory;
        this.cacheByScope = firCachesFactory.createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.scopes.impl.FirIntersectionOverrideStorage$special$$inlined$createCache$1
            public final FirCache<? super FirCallableSymbol<?>, ? extends MemberWithBaseScope<? extends FirCallableSymbol<?>>, ? super FirTypeIntersectionScopeContext.ResultOfIntersection.NonTrivial<?>> invoke(ConeKotlinType coneKotlinType, Void r2) {
                coneKotlinType.getClass();
                return this.this$0.cachesFactory.createCache(new Function2<FirCallableSymbol<?>, FirTypeIntersectionScopeContext.ResultOfIntersection.NonTrivial<?>, MemberWithBaseScope<? extends FirCallableSymbol<?>>>() { // from class: org.jetbrains.kotlin.fir.scopes.impl.FirIntersectionOverrideStorage$cacheByScope$1$1
                    public final MemberWithBaseScope<FirCallableSymbol<?>> invoke(FirCallableSymbol<?> firCallableSymbol, FirTypeIntersectionScopeContext.ResultOfIntersection.NonTrivial<?> nonTrivial) {
                        firCallableSymbol.getClass();
                        nonTrivial.getClass();
                        return nonTrivial.getContext().createIntersectionOverride(nonTrivial.getMostSpecific(), nonTrivial.getOverriddenMembers(), nonTrivial.getContainsMultipleNonSubsumed());
                    }
                });
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((ConeKotlinType) obj, (Void) obj2);
            }
        });
    }

    public final FirCache getCacheByScope() {
        return this.cacheByScope;
    }

    public final FirSession getSession() {
        return this.session;
    }
}
