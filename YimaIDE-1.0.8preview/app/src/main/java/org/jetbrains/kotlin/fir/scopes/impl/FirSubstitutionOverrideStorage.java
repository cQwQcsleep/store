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
import org.jetbrains.kotlin.fir.resolve.ScopeSessionKey;
import org.jetbrains.kotlin.fir.scopes.impl.FirClassSubstitutionScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirSubstitutionOverrideStorage;
import org.jetbrains.kotlin.fir.symbols.impl.FirConstructorSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirNamedFunctionSymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirPropertySymbol;
import org.jetbrains.kotlin.fir.symbols.impl.FirVariableSymbol;
import org.jetbrains.kotlin.fir.utils.exceptions.FirExceptionUtilsKt;
import org.jetbrains.kotlin.utils.exceptions.ExceptionAttachmentBuilder;
import org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0004\u0018\u00002\u00020\u0001:\u0001\u0011B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R-\u0010\n\u001a\u001e\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\f\u0012\u0004\u0012\u00020\r\u0012\u0006\u0012\u0004\u0018\u00010\u000e0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirSubstitutionOverrideStorage;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "cachesFactory", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "substitutionOverrideCacheByScope", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSessionKey;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirSubstitutionOverrideStorage$SubstitutionOverrideCache;", Argument.Delimiters.none, "getSubstitutionOverrideCacheByScope", "()Lorg/jetbrains/kotlin/fir/caches/FirCache;", "SubstitutionOverrideCache", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirSubstitutionOverrideStorage implements FirSessionComponent {
    private final FirCachesFactory cachesFactory;
    private final FirSession session;
    private final FirCache substitutionOverrideCacheByScope;

    @Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R#\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR#\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\r\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000bR+\u0010\u000f\u001a\u001c\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0010\u0012\b\u0012\u0006\u0012\u0002\b\u00030\u0010\u0012\u0004\u0012\u00020\t0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u000b¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirSubstitutionOverrideStorage$SubstitutionOverrideCache;", Argument.Delimiters.none, "cachesFactory", "Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;", "<init>", "(Lorg/jetbrains/kotlin/fir/caches/FirCachesFactory;)V", "overridesForFunctions", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirNamedFunctionSymbol;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassSubstitutionScope;", "getOverridesForFunctions", "()Lorg/jetbrains/kotlin/fir/caches/FirCache;", "overridesForConstructors", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirConstructorSymbol;", "getOverridesForConstructors", "overridesForVariables", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirVariableSymbol;", "getOverridesForVariables", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class SubstitutionOverrideCache {
        private final FirCache<FirConstructorSymbol, FirConstructorSymbol, FirClassSubstitutionScope> overridesForConstructors;
        private final FirCache<FirNamedFunctionSymbol, FirNamedFunctionSymbol, FirClassSubstitutionScope> overridesForFunctions;
        private final FirCache<FirVariableSymbol<?>, FirVariableSymbol<?>, FirClassSubstitutionScope> overridesForVariables;

        public SubstitutionOverrideCache(FirCachesFactory firCachesFactory) {
            firCachesFactory.getClass();
            this.overridesForFunctions = firCachesFactory.createCache(new Function2() { // from class: me5
                public final Object invoke(Object obj, Object obj2) {
                    return FirSubstitutionOverrideStorage.SubstitutionOverrideCache.b((FirNamedFunctionSymbol) obj, (FirClassSubstitutionScope) obj2);
                }
            });
            this.overridesForConstructors = firCachesFactory.createCache(new Function2() { // from class: ne5
                public final Object invoke(Object obj, Object obj2) {
                    return FirSubstitutionOverrideStorage.SubstitutionOverrideCache.c((FirConstructorSymbol) obj, (FirClassSubstitutionScope) obj2);
                }
            });
            this.overridesForVariables = firCachesFactory.createCache(new Function2() { // from class: oe5
                public final Object invoke(Object obj, Object obj2) {
                    return FirSubstitutionOverrideStorage.SubstitutionOverrideCache.a((FirVariableSymbol) obj, (FirClassSubstitutionScope) obj2);
                }
            });
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.kotlin.utils.exceptions.KotlinIllegalArgumentExceptionWithAttachments */
        /* JADX WARN: Type inference failed for: r2v1, types: [org.jetbrains.kotlin.fir.FirElement, org.jetbrains.kotlin.fir.declarations.FirDeclaration] */
        public static FirVariableSymbol a(FirVariableSymbol firVariableSymbol, FirClassSubstitutionScope firClassSubstitutionScope) throws KotlinIllegalArgumentExceptionWithAttachments {
            firVariableSymbol.getClass();
            firClassSubstitutionScope.getClass();
            if (firVariableSymbol instanceof FirPropertySymbol) {
                return firClassSubstitutionScope.createSubstitutionOverrideProperty((FirPropertySymbol) firVariableSymbol);
            }
            if (firVariableSymbol instanceof FirFieldSymbol) {
                return firClassSubstitutionScope.createSubstitutionOverrideField((FirFieldSymbol) firVariableSymbol);
            }
            KotlinIllegalArgumentExceptionWithAttachments kotlinIllegalArgumentExceptionWithAttachments = new KotlinIllegalArgumentExceptionWithAttachments("symbol " + firVariableSymbol.getClass() + " is not overridable", (Throwable) null);
            ExceptionAttachmentBuilder exceptionAttachmentBuilder = new ExceptionAttachmentBuilder();
            FirExceptionUtilsKt.withFirEntry(exceptionAttachmentBuilder, "original", firVariableSymbol.getFir());
            kotlinIllegalArgumentExceptionWithAttachments.withAttachment("info.txt", exceptionAttachmentBuilder.buildString());
            throw kotlinIllegalArgumentExceptionWithAttachments;
        }

        public static FirNamedFunctionSymbol b(FirNamedFunctionSymbol firNamedFunctionSymbol, FirClassSubstitutionScope firClassSubstitutionScope) {
            firNamedFunctionSymbol.getClass();
            firClassSubstitutionScope.getClass();
            return firClassSubstitutionScope.createSubstitutionOverrideFunction(firNamedFunctionSymbol);
        }

        public static FirConstructorSymbol c(FirConstructorSymbol firConstructorSymbol, FirClassSubstitutionScope firClassSubstitutionScope) {
            firConstructorSymbol.getClass();
            firClassSubstitutionScope.getClass();
            return firClassSubstitutionScope.createSubstitutionOverrideConstructor(firConstructorSymbol);
        }

        public final FirCache<FirConstructorSymbol, FirConstructorSymbol, FirClassSubstitutionScope> getOverridesForConstructors() {
            return this.overridesForConstructors;
        }

        public final FirCache<FirNamedFunctionSymbol, FirNamedFunctionSymbol, FirClassSubstitutionScope> getOverridesForFunctions() {
            return this.overridesForFunctions;
        }

        public final FirCache<FirVariableSymbol<?>, FirVariableSymbol<?>, FirClassSubstitutionScope> getOverridesForVariables() {
            return this.overridesForVariables;
        }
    }

    public FirSubstitutionOverrideStorage(FirSession firSession) {
        firSession.getClass();
        this.session = firSession;
        FirCachesFactory firCachesFactory = FirCachesFactoryKt.getFirCachesFactory(firSession);
        this.cachesFactory = firCachesFactory;
        this.substitutionOverrideCacheByScope = firCachesFactory.createCache(new Function2() { // from class: org.jetbrains.kotlin.fir.scopes.impl.FirSubstitutionOverrideStorage$special$$inlined$createCache$1
            public final FirSubstitutionOverrideStorage.SubstitutionOverrideCache invoke(ScopeSessionKey<?, ?> scopeSessionKey, Void r2) {
                scopeSessionKey.getClass();
                return new FirSubstitutionOverrideStorage.SubstitutionOverrideCache(FirCachesFactoryKt.getFirCachesFactory(this.this$0.getSession()));
            }

            public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
                return invoke((ScopeSessionKey<?, ?>) obj, (Void) obj2);
            }
        });
    }

    public final FirSession getSession() {
        return this.session;
    }

    public final FirCache getSubstitutionOverrideCacheByScope() {
        return this.substitutionOverrideCacheByScope;
    }
}
