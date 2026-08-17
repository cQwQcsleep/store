package org.jetbrains.kotlin.fir.pipeline;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.declarations.FirFile;
import org.jetbrains.kotlin.fir.resolve.ScopeSession;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0012\u001a\u00020\u0005HÆ\u0003J\u000f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0003J-\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\u000e\b\u0002\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007HÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/pipeline/SingleModuleFrontendOutput;", Argument.Delimiters.none, "session", "Lorg/jetbrains/kotlin/fir/FirSession;", "scopeSession", "Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "fir", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirFile;", "<init>", "(Lorg/jetbrains/kotlin/fir/FirSession;Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;Ljava/util/List;)V", "getSession", "()Lorg/jetbrains/kotlin/fir/FirSession;", "getScopeSession", "()Lorg/jetbrains/kotlin/fir/resolve/ScopeSession;", "getFir", "()Ljava/util/List;", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:entrypoint"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class SingleModuleFrontendOutput {
    private final List<FirFile> fir;
    private final ScopeSession scopeSession;
    private final FirSession session;

    /* JADX WARN: Multi-variable type inference failed */
    public SingleModuleFrontendOutput(FirSession firSession, ScopeSession scopeSession, List<? extends FirFile> list) {
        firSession.getClass();
        scopeSession.getClass();
        list.getClass();
        this.session = firSession;
        this.scopeSession = scopeSession;
        this.fir = list;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ SingleModuleFrontendOutput copy$default(SingleModuleFrontendOutput singleModuleFrontendOutput, FirSession firSession, ScopeSession scopeSession, List list, int i, Object obj) {
        if ((i & 1) != 0) {
            firSession = singleModuleFrontendOutput.session;
        }
        if ((i & 2) != 0) {
            scopeSession = singleModuleFrontendOutput.scopeSession;
        }
        if ((i & 4) != 0) {
            list = singleModuleFrontendOutput.fir;
        }
        return singleModuleFrontendOutput.copy(firSession, scopeSession, list);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final FirSession getSession() {
        return this.session;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    public final List<FirFile> component3() {
        return this.fir;
    }

    public final SingleModuleFrontendOutput copy(FirSession session, ScopeSession scopeSession, List<? extends FirFile> fir) {
        session.getClass();
        scopeSession.getClass();
        fir.getClass();
        return new SingleModuleFrontendOutput(session, scopeSession, fir);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof SingleModuleFrontendOutput)) {
            return false;
        }
        SingleModuleFrontendOutput singleModuleFrontendOutput = (SingleModuleFrontendOutput) other;
        return Intrinsics.areEqual(this.session, singleModuleFrontendOutput.session) && Intrinsics.areEqual(this.scopeSession, singleModuleFrontendOutput.scopeSession) && Intrinsics.areEqual(this.fir, singleModuleFrontendOutput.fir);
    }

    public final List<FirFile> getFir() {
        return this.fir;
    }

    public final ScopeSession getScopeSession() {
        return this.scopeSession;
    }

    public final FirSession getSession() {
        return this.session;
    }

    public int hashCode() {
        return (((this.session.hashCode() * 31) + this.scopeSession.hashCode()) * 31) + this.fir.hashCode();
    }

    public String toString() {
        return "SingleModuleFrontendOutput(session=" + this.session + ", scopeSession=" + this.scopeSession + ", fir=" + this.fir + ')';
    }
}
