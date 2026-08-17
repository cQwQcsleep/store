package org.jetbrains.kotlin.fir.declarations;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValueForScriptOrSnippet;
import org.jetbrains.kotlin.fir.scopes.FirScope;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u001f\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\u0007\u0010\bR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0013\u0010\u0005\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/TowerElementsForScript;", Argument.Delimiters.none, "implicitReceivers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValueForScriptOrSnippet;", "staticScope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "<init>", "(Ljava/util/List;Lorg/jetbrains/kotlin/fir/scopes/FirScope;)V", "getImplicitReceivers", "()Ljava/util/List;", "getStaticScope", "()Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TowerElementsForScript {
    private final List<ImplicitReceiverValueForScriptOrSnippet> implicitReceivers;
    private final FirScope staticScope;

    public TowerElementsForScript(List<ImplicitReceiverValueForScriptOrSnippet> list, FirScope firScope) {
        list.getClass();
        this.implicitReceivers = list;
        this.staticScope = firScope;
    }

    public final List<ImplicitReceiverValueForScriptOrSnippet> getImplicitReceivers() {
        return this.implicitReceivers;
    }

    public final FirScope getStaticScope() {
        return this.staticScope;
    }
}
