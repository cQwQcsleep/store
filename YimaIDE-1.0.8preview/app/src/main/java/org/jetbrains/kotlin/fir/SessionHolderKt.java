package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001aB\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032!\u0010\u0004\u001a\u001d\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u00010\u0005¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\u0003\u0010\u0002H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\t\u001aJ\u0010\u0000\u001a\u0002H\u0001\"\u0004\b\u0000\u0010\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\n\u001a\u00020\u000b2!\u0010\u0004\u001a\u001d\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u0002H\u00010\u0005¢\u0006\f\b\u0007\u0012\b\b\b\u0012\u0004\b\u0003\u0010\u0002H\u0086\bø\u0001\u0000¢\u0006\u0002\u0010\f\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\r"}, d2 = {"withSession", "R", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "block", "Lkotlin/Function1;", "Lorg/jetbrains/kotlin/fir/SessionHolder;", "Lkotlin/ContextFunctionTypeParams;", "count", "(Lorg/jetbrains/kotlin/fir/FirSession;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "org.jetbrains.kotlin:tree"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class SessionHolderKt {
    public static final <R> R withSession(final FirSession firSession, final ScopeSession scopeSession, Function1<? super SessionHolder, ? extends R> function1) {
        firSession.getClass();
        scopeSession.getClass();
        function1.getClass();
        return (R) function1.invoke(new SessionAndScopeSessionHolder() { // from class: org.jetbrains.kotlin.fir.SessionHolderKt$withSession$holder$2
            @Override // org.jetbrains.kotlin.fir.ScopeSessionHolder
            /* JADX INFO: renamed from: getScopeSession, reason: from getter */
            public ScopeSession get$scopeSession() {
                return scopeSession;
            }

            @Override // org.jetbrains.kotlin.fir.SessionHolder
            /* JADX INFO: renamed from: getSession, reason: from getter */
            public FirSession get$session() {
                return firSession;
            }
        });
    }

    public static final <R> R withSession(final FirSession firSession, Function1<? super SessionHolder, ? extends R> function1) {
        firSession.getClass();
        function1.getClass();
        return (R) function1.invoke(new SessionHolder() { // from class: org.jetbrains.kotlin.fir.SessionHolderKt$withSession$holder$1
            @Override // org.jetbrains.kotlin.fir.SessionHolder
            /* JADX INFO: renamed from: getSession, reason: from getter */
            public FirSession get$session() {
                return firSession;
            }
        });
    }
}
