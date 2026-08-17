package org.jetbrains.kotlin.cli.common;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B\u001d\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\r\u001a\u00020\u0004HÆ\u0003J\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006HÆ\u0003J)\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006HÆ\u0001J\u0014\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u0010\u0013\u001a\u00020\u0014HÖ\u0081\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/SessionWithSources;", "F", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "files", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Ljava/util/List;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getFiles", "()Ljava/util/List;", "component1", "component2", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class SessionWithSources<F> {
    private final List<F> files;
    private final FirSession session;

    /* JADX WARN: Multi-variable type inference failed */
    public SessionWithSources(FirSession firSession, List<? extends F> list) {
        firSession.getClass();
        list.getClass();
        this.session = firSession;
        this.files = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SessionWithSources copy$default(SessionWithSources sessionWithSources, FirSession firSession, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            firSession = sessionWithSources.session;
        }
        if ((i & 2) != 0) {
            list = sessionWithSources.files;
        }
        return sessionWithSources.copy(firSession, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final FirSession getSession() {
        return this.session;
    }

    public final List<F> component2() {
        return this.files;
    }

    public final SessionWithSources<F> copy(FirSession session, List<? extends F> files) {
        session.getClass();
        files.getClass();
        return new SessionWithSources<>(session, files);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SessionWithSources)) {
            return false;
        }
        SessionWithSources sessionWithSources = (SessionWithSources) other;
        return Intrinsics.areEqual(this.session, sessionWithSources.session) && Intrinsics.areEqual(this.files, sessionWithSources.files);
    }

    public final List<F> getFiles() {
        return this.files;
    }

    public final FirSession getSession() {
        return this.session;
    }

    public int hashCode() {
        return (this.session.hashCode() * 31) + this.files.hashCode();
    }

    public String toString() {
        return "SessionWithSources(session=" + this.session + ", files=" + this.files + ')';
    }
}
