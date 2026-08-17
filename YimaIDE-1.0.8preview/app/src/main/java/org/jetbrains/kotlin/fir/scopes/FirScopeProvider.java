package org.jetbrains.kotlin.fir.scopes;

import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirClass;
import org.jetbrains.kotlin.fir.declarations.FirResolvePhase;
import org.jetbrains.kotlin.fir.declarations.FirTypeAlias;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J*\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\b\u0010\f\u001a\u0004\u0018\u00010\rH&J \u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\"\u0010\u0012\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\"\u0010\u0014\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J\"\u0010\u0015\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH&J \u0010\u0016\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ \u0010\u0017\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJE\u0010\u0018\u001a\u0004\u0018\u00010\u00132\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2 \u0010\u0019\u001a\u001c\u0012\u0004\u0012\u00020\u0007\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u000b\u0012\u0006\u0012\u0004\u0018\u00010\u00130\u001aH\u0082\b¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/FirScopeProvider;", Argument.Delimiters.none, "<init>", "()V", "getUseSiteMemberScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "klass", "Lorg/jetbrains/kotlin/fir/declarations/FirClass;", "useSiteSession", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "memberRequiredPhase", "Lorg/jetbrains/kotlin/fir/declarations/FirResolvePhase;", "getTypealiasConstructorScope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "typeAlias", "Lorg/jetbrains/kotlin/fir/declarations/FirTypeAlias;", "getStaticCallableMemberScope", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "getStaticCallableMemberScopeForBackend", "getNestedClassifierScope", "getStaticScope", "getStaticScopeForBackend", "getStaticScopeImpl", "callableMemberScope", "Lkotlin/Function3;", "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FirScopeProvider {
    public abstract FirContainingNamesAwareScope getNestedClassifierScope(FirClass klass, FirSession useSiteSession, ScopeSession scopeSession);

    public abstract FirContainingNamesAwareScope getStaticCallableMemberScope(FirClass klass, FirSession useSiteSession, ScopeSession scopeSession);

    public abstract FirContainingNamesAwareScope getStaticCallableMemberScopeForBackend(FirClass klass, FirSession useSiteSession, ScopeSession scopeSession);

    public final FirContainingNamesAwareScope getStaticScope(FirClass klass, FirSession useSiteSession, ScopeSession scopeSession) {
        klass.getClass();
        useSiteSession.getClass();
        scopeSession.getClass();
        FirContainingNamesAwareScope nestedClassifierScope = getNestedClassifierScope(klass, useSiteSession, scopeSession);
        FirContainingNamesAwareScope staticCallableMemberScope = getStaticCallableMemberScope(klass, useSiteSession, scopeSession);
        if (nestedClassifierScope == null || staticCallableMemberScope == null) {
            return nestedClassifierScope == null ? staticCallableMemberScope : nestedClassifierScope;
        }
        return new FirNameAwareCompositeScope(CollectionsKt.listOf(new FirContainingNamesAwareScope[]{nestedClassifierScope, staticCallableMemberScope}));
    }

    public final FirContainingNamesAwareScope getStaticScopeForBackend(FirClass klass, FirSession useSiteSession, ScopeSession scopeSession) {
        klass.getClass();
        useSiteSession.getClass();
        scopeSession.getClass();
        FirContainingNamesAwareScope nestedClassifierScope = getNestedClassifierScope(klass, useSiteSession, scopeSession);
        FirContainingNamesAwareScope staticCallableMemberScopeForBackend = getStaticCallableMemberScopeForBackend(klass, useSiteSession, scopeSession);
        if (nestedClassifierScope == null || staticCallableMemberScopeForBackend == null) {
            return nestedClassifierScope == null ? staticCallableMemberScopeForBackend : nestedClassifierScope;
        }
        return new FirNameAwareCompositeScope(CollectionsKt.listOf(new FirContainingNamesAwareScope[]{nestedClassifierScope, staticCallableMemberScopeForBackend}));
    }

    public abstract FirScope getTypealiasConstructorScope(FirTypeAlias typeAlias, FirSession useSiteSession, ScopeSession scopeSession);

    public abstract FirTypeScope getUseSiteMemberScope(FirClass klass, FirSession useSiteSession, ScopeSession scopeSession, FirResolvePhase memberRequiredPhase);
}
