package org.jetbrains.kotlin.fir.scopes.impl;

import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.time.Duration;
import kotlin.time.DurationKt;
import kotlin.time.DurationUnit;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.FirSessionComponent;
import org.jetbrains.kotlin.fir.caches.FirCache;
import org.jetbrains.kotlin.fir.caches.FirCachesFactory;
import org.jetbrains.kotlin.fir.caches.FirCachesFactoryKt;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirDeclarationOrigin;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.FirNameAwareCompositeScope;
import org.jetbrains.kotlin.fir.scopes.FirTypeScope;
import org.jetbrains.kotlin.fir.symbols.FirLazyDeclarationResolverKt;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0001\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001:\u0001\u001dB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J0\u0010\u0010\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\n2\u0006\u0010\u0012\u001a\u00020\u00132\u000e\u0010\u0014\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00152\b\u0010\u0017\u001a\u0004\u0018\u00010\u0018J \u0010\u0019\u001a\u00020\u000b2\u0006\u0010\u0011\u001a\u00020\n2\u000e\u0010\u001a\u001a\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u0015H\u0002J\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\nJ\u0012\u0010\u001c\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\nH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R \u0010\b\u001a\u0014\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\f0\tX\u0082\u0004¢\u0006\u0002\n\u0000R$\u0010\r\u001a\u0018\u0012\u0004\u0012\u00020\n\u0012\u0006\u0012\u0004\u0018\u00010\u000e\u0012\u0006\u0012\u0004\u0018\u00010\u000f0\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirDeclaredMemberScopeProvider;", "Lorg/jetbrains/kotlin/fir/FirSessionComponent;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;)V", "getUseSiteSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "declaredMemberCache", "Lorg/jetbrains/kotlin/fir/caches/FirCache;", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirDeclaredMemberScopeProvider$DeclaredMemberScopeContext;", "nestedClassifierCache", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirNestedClassifierScope;", Argument.Delimiters.none, "declaredMemberScope", "klass", "useLazyNestedClassifierScope", Argument.Delimiters.none, "existingNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "memberRequiredPhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "createDeclaredMemberScope", "existingNamesForLazyNestedClassifierScope", "nestedClassifierScope", "createNestedClassifierScope", "DeclaredMemberScopeContext", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FirDeclaredMemberScopeProvider implements FirSessionComponent {
    private final FirCache<FirClass, FirContainingNamesAwareScope, DeclaredMemberScopeContext> declaredMemberCache;
    private final FirCache nestedClassifierCache;
    private final FirSession useSiteSession;

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u000e\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0003HÆ\u0003J\u0011\u0010\u000e\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0003J%\u0010\u000f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u0010\b\u0002\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00032\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0019\u0010\u0004\u001a\n\u0012\u0004\u0012\u00020\u0006\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/FirDeclaredMemberScopeProvider$DeclaredMemberScopeContext;", Argument.Delimiters.none, "useLazyNestedClassifierScope", Argument.Delimiters.none, "existingNames", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/name/Name;", "<init>", "(ZLjava/util/List;)V", "getUseLazyNestedClassifierScope", "()Z", "getExistingNames", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* data */ class DeclaredMemberScopeContext {
        private final List<Name> existingNames;
        private final boolean useLazyNestedClassifierScope;

        public DeclaredMemberScopeContext(boolean z, List<Name> list) {
            this.useLazyNestedClassifierScope = z;
            this.existingNames = list;
        }

        /* JADX WARN: Multi-variable type inference failed */
        public static /* synthetic */ DeclaredMemberScopeContext copy$default(DeclaredMemberScopeContext declaredMemberScopeContext, boolean z, List list, int i, Object obj) {
            if ((i & 1) != 0) {
                z = declaredMemberScopeContext.useLazyNestedClassifierScope;
            }
            if ((i & 2) != 0) {
                list = declaredMemberScopeContext.existingNames;
            }
            return declaredMemberScopeContext.copy(z, list);
        }

        /* JADX INFO: renamed from: component1, reason: from getter */
        public final boolean getUseLazyNestedClassifierScope() {
            return this.useLazyNestedClassifierScope;
        }

        public final List<Name> component2() {
            return this.existingNames;
        }

        public final DeclaredMemberScopeContext copy(boolean useLazyNestedClassifierScope, List<Name> existingNames) {
            return new DeclaredMemberScopeContext(useLazyNestedClassifierScope, existingNames);
        }

        public boolean equals(Object other) {
            if (this == other) {
                return true;
            }
            if (!(other instanceof DeclaredMemberScopeContext)) {
                return false;
            }
            DeclaredMemberScopeContext declaredMemberScopeContext = (DeclaredMemberScopeContext) other;
            return this.useLazyNestedClassifierScope == declaredMemberScopeContext.useLazyNestedClassifierScope && Intrinsics.areEqual(this.existingNames, declaredMemberScopeContext.existingNames);
        }

        public final List<Name> getExistingNames() {
            return this.existingNames;
        }

        public final boolean getUseLazyNestedClassifierScope() {
            return this.useLazyNestedClassifierScope;
        }

        public int hashCode() {
            int iHashCode = Boolean.hashCode(this.useLazyNestedClassifierScope) * 31;
            List<Name> list = this.existingNames;
            return iHashCode + (list == null ? 0 : list.hashCode());
        }

        public String toString() {
            return "DeclaredMemberScopeContext(useLazyNestedClassifierScope=" + this.useLazyNestedClassifierScope + ", existingNames=" + this.existingNames + ')';
        }
    }

    public FirDeclaredMemberScopeProvider(FirSession firSession) {
        firSession.getClass();
        this.useSiteSession = firSession;
        FirCachesFactory firCachesFactory = FirCachesFactoryKt.getFirCachesFactory(firSession);
        Duration.Companion companion = Duration.Companion;
        DurationUnit durationUnit = DurationUnit.SECONDS;
        Duration duration = Duration.box-impl(DurationKt.toDuration(5, durationUnit));
        FirCachesFactory.ValueReferenceStrength valueReferenceStrength = FirCachesFactory.ValueReferenceStrength.SOFT;
        this.declaredMemberCache = FirCachesFactory.m262createCacheWithSuggestedLimitsn8is24I$default(firCachesFactory, duration, null, null, valueReferenceStrength, new Function2() { // from class: org.jetbrains.kotlin.fir.scopes.impl.b
            public final Object invoke(Object obj, Object obj2) {
                return FirDeclaredMemberScopeProvider.a(this.b, (FirClass) obj, (FirDeclaredMemberScopeProvider.DeclaredMemberScopeContext) obj2);
            }
        }, 6, null);
        this.nestedClassifierCache = FirCachesFactory.m262createCacheWithSuggestedLimitsn8is24I$default(FirCachesFactoryKt.getFirCachesFactory(firSession), Duration.box-impl(DurationKt.toDuration(5, durationUnit)), null, null, valueReferenceStrength, new Function2() { // from class: org.jetbrains.kotlin.fir.scopes.impl.FirDeclaredMemberScopeProvider$nestedClassifierCache$1
            public final FirNestedClassifierScope invoke(FirClass firClass, Void r2) {
                firClass.getClass();
                return this.this$0.createNestedClassifierScope(firClass);
            }
        }, 6, null);
    }

    public static FirContainingNamesAwareScope a(FirDeclaredMemberScopeProvider firDeclaredMemberScopeProvider, FirClass firClass, DeclaredMemberScopeContext declaredMemberScopeContext) {
        firClass.getClass();
        declaredMemberScopeContext.getClass();
        return firDeclaredMemberScopeProvider.createDeclaredMemberScope(firClass, declaredMemberScopeContext.getExistingNames());
    }

    private final FirContainingNamesAwareScope createDeclaredMemberScope(FirClass klass, List<Name> existingNamesForLazyNestedClassifierScope) {
        FirDeclarationOrigin origin = klass.getOrigin();
        if (origin.getGenerated()) {
            FirGeneratedClassDeclaredMemberScope firGeneratedClassDeclaredMemberScopeCreate = FirGeneratedClassDeclaredMemberScope.INSTANCE.create(this.useSiteSession, klass.getSymbol(), null, true);
            return firGeneratedClassDeclaredMemberScopeCreate != null ? firGeneratedClassDeclaredMemberScopeCreate : FirTypeScope.Empty.INSTANCE;
        }
        FirClassDeclaredMemberScopeImpl firClassDeclaredMemberScopeImpl = new FirClassDeclaredMemberScopeImpl(this.useSiteSession, klass, existingNamesForLazyNestedClassifierScope);
        FirGeneratedClassDeclaredMemberScope firGeneratedClassDeclaredMemberScopeCreate2 = origin.getFromSource() ? FirGeneratedClassDeclaredMemberScope.INSTANCE.create(this.useSiteSession, klass.getSymbol(), firClassDeclaredMemberScopeImpl, false) : null;
        return firGeneratedClassDeclaredMemberScopeCreate2 != null ? new FirNameAwareCompositeScope(CollectionsKt.listOf(new FirClassDeclaredMemberScope[]{firClassDeclaredMemberScopeImpl, firGeneratedClassDeclaredMemberScopeCreate2})) : firClassDeclaredMemberScopeImpl;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final FirNestedClassifierScope createNestedClassifierScope(FirClass klass) {
        FirNestedClassifierScope firCompositeNestedClassifierScope;
        FirDeclarationOrigin origin = klass.getOrigin();
        if (origin.getGenerated()) {
            firCompositeNestedClassifierScope = FirGeneratedClassNestedClassifierScope.INSTANCE.create(this.useSiteSession, klass.getSymbol(), null);
        } else {
            FirNestedClassifierScopeImpl firNestedClassifierScopeImpl = new FirNestedClassifierScopeImpl(klass, this.useSiteSession);
            FirGeneratedClassNestedClassifierScope firGeneratedClassNestedClassifierScopeCreate = origin.getFromSource() ? FirGeneratedClassNestedClassifierScope.INSTANCE.create(this.useSiteSession, klass.getSymbol(), firNestedClassifierScopeImpl) : null;
            firCompositeNestedClassifierScope = firGeneratedClassNestedClassifierScopeCreate != null ? new FirCompositeNestedClassifierScope(CollectionsKt.listOf(new FirNestedClassifierScope[]{firNestedClassifierScopeImpl, firGeneratedClassNestedClassifierScopeCreate}), klass, this.useSiteSession) : firNestedClassifierScopeImpl;
        }
        if (firCompositeNestedClassifierScope == null) {
            return null;
        }
        if (Intrinsics.areEqual(origin, FirDeclarationOrigin.Java.Source.INSTANCE) || !firCompositeNestedClassifierScope.isEmpty()) {
            return firCompositeNestedClassifierScope;
        }
        return null;
    }

    public final FirContainingNamesAwareScope declaredMemberScope(FirClass klass, boolean useLazyNestedClassifierScope, List<Name> existingNames, FirResolvePhase memberRequiredPhase) {
        klass.getClass();
        if (memberRequiredPhase != null) {
            FirLazyDeclarationResolverKt.lazyResolveToPhaseWithCallableMembers(klass, memberRequiredPhase);
        }
        return this.declaredMemberCache.getValue(klass, new DeclaredMemberScopeContext(useLazyNestedClassifierScope, existingNames));
    }

    public final FirSession getUseSiteSession() {
        return this.useSiteSession;
    }

    public final FirNestedClassifierScope nestedClassifierScope(FirClass klass) {
        klass.getClass();
        return (FirNestedClassifierScope) this.nestedClassifierCache.getValue(klass, null);
    }
}
