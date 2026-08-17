package org.jetbrains.kotlin.fir.resolve;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.SessionAndScopeSessionHolder;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\b\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0012HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/SessionHolderImpl;", "Lorg/jetbrains/kotlin/fir/SessionAndScopeSessionHolder;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class SessionHolderImpl implements SessionAndScopeSessionHolder {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final ScopeSession scopeSession;
    private final FirSession session;

    public SessionHolderImpl(FirSession firSession, ScopeSession scopeSession) {
        firSession.getClass();
        scopeSession.getClass();
        this.session = firSession;
        this.scopeSession = scopeSession;
    }

    public static /* synthetic */ SessionHolderImpl copy$default(SessionHolderImpl sessionHolderImpl, FirSession firSession, ScopeSession scopeSession, int i, Object obj) {
        if ((i & 1) != 0) {
            firSession = sessionHolderImpl.session;
        }
        if ((i & 2) != 0) {
            scopeSession = sessionHolderImpl.scopeSession;
        }
        return sessionHolderImpl.copy(firSession, scopeSession);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final FirSession getSession() {
        return this.session;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    public final SessionHolderImpl copy(FirSession session, ScopeSession scopeSession) {
        session.getClass();
        scopeSession.getClass();
        return new SessionHolderImpl(session, scopeSession);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SessionHolderImpl)) {
            return false;
        }
        SessionHolderImpl sessionHolderImpl = (SessionHolderImpl) other;
        return Intrinsics.areEqual(this.session, sessionHolderImpl.session) && Intrinsics.areEqual(this.scopeSession, sessionHolderImpl.scopeSession);
    }

    @Override // org.jetbrains.kotlin.fir.ScopeSessionHolder
    /* JADX INFO: renamed from: getScopeSession */
    public ScopeSession get$scopeSession() {
        return this.scopeSession;
    }

    @Override // org.jetbrains.kotlin.fir.SessionHolder
    /* JADX INFO: renamed from: getSession */
    public FirSession get$session() {
        return this.session;
    }

    public int hashCode() {
        return (this.session.hashCode() * 31) + this.scopeSession.hashCode();
    }

    public String toString() {
        return "SessionHolderImpl(session=" + this.session + ", scopeSession=" + this.scopeSession + ')';
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/fir/resolve/SessionHolderImpl$Companion;", Argument.Delimiters.none, "<init>", "()V", "createWithEmptyScopeSession", "Lorg/jetbrains/kotlin/fir/resolve/SessionHolderImpl;", "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "org.jetbrains.kotlin:resolve"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final SessionHolderImpl createWithEmptyScopeSession(FirSession session) {
            session.getClass();
            return new SessionHolderImpl(session, new ScopeSession());
        }

        private Companion() {
        }
    }
}
