package org.jetbrains.kotlin.fir.declarations;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.resolve.calls.ImplicitReceiverValue;
import org.jetbrains.kotlin.fir.scopes.FirScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirRegularClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0003\u0018\u00002\u00020\u0001BM\u0012\n\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003\u0012\b\u0010\u0004\u001a\u0004\u0018\u00010\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\f\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0005\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\u0004\b\r\u0010\u000eR\u0015\u0010\u0002\u001a\u0006\u0012\u0002\b\u00030\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0013\u0010\u0004\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\u0006\u001a\u0004\u0018\u00010\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\b\u001a\b\u0012\u0002\b\u0003\u0018\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0010R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0012R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\f0\u000b¢\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0019\u001a\u00020\u001a8F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/fir/declarations/TowerElementsForClass;", Argument.Delimiters.none, "thisReceiver", "Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;", "staticScope", "Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "staticScopeOwnerSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "companionReceiver", "companionStaticScope", "superClassesStaticsAndCompanionReceivers", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/declarations/FirTowerDataElement;", "<init>", "(Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;Lorg/jetbrains/kotlin/fir/scopes/FirScope;Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;Lorg/jetbrains/kotlin/fir/scopes/FirScope;Ljava/util/List;)V", "getThisReceiver", "()Lorg/jetbrains/kotlin/fir/resolve/calls/ImplicitReceiverValue;", "getStaticScope", "()Lorg/jetbrains/kotlin/fir/scopes/FirScope;", "getStaticScopeOwnerSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirRegularClassSymbol;", "getCompanionReceiver", "getCompanionStaticScope", "getSuperClassesStaticsAndCompanionReceivers", "()Ljava/util/List;", "hasStaticScopeOrOwnerSymbol", Argument.Delimiters.none, "getHasStaticScopeOrOwnerSymbol", "()Z", "org.jetbrains.kotlin:semantics"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TowerElementsForClass {
    private final ImplicitReceiverValue<?> companionReceiver;
    private final FirScope companionStaticScope;
    private final FirScope staticScope;
    private final FirRegularClassSymbol staticScopeOwnerSymbol;
    private final List<FirTowerDataElement> superClassesStaticsAndCompanionReceivers;
    private final ImplicitReceiverValue<?> thisReceiver;

    public TowerElementsForClass(ImplicitReceiverValue<?> implicitReceiverValue, FirScope firScope, FirRegularClassSymbol firRegularClassSymbol, ImplicitReceiverValue<?> implicitReceiverValue2, FirScope firScope2, List<FirTowerDataElement> list) {
        implicitReceiverValue.getClass();
        list.getClass();
        this.thisReceiver = implicitReceiverValue;
        this.staticScope = firScope;
        this.staticScopeOwnerSymbol = firRegularClassSymbol;
        this.companionReceiver = implicitReceiverValue2;
        this.companionStaticScope = firScope2;
        this.superClassesStaticsAndCompanionReceivers = list;
    }

    public final ImplicitReceiverValue<?> getCompanionReceiver() {
        return this.companionReceiver;
    }

    public final FirScope getCompanionStaticScope() {
        return this.companionStaticScope;
    }

    public final boolean getHasStaticScopeOrOwnerSymbol() {
        return (this.staticScope == null && this.staticScopeOwnerSymbol == null) ? false : true;
    }

    public final FirScope getStaticScope() {
        return this.staticScope;
    }

    public final FirRegularClassSymbol getStaticScopeOwnerSymbol() {
        return this.staticScopeOwnerSymbol;
    }

    public final List<FirTowerDataElement> getSuperClassesStaticsAndCompanionReceivers() {
        return this.superClassesStaticsAndCompanionReceivers;
    }

    public final ImplicitReceiverValue<?> getThisReceiver() {
        return this.thisReceiver;
    }
}
