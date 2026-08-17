package org.jetbrains.kotlin.fir.scopes;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.symbols.impl.FirCallableSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u000e\b\u0000\u0010\u0001 \u0001*\u0006\u0012\u0002\b\u00030\u00022\u00020\u0003B\u0017\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u000e\u0010\u000e\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\nJ\t\u0010\u000f\u001a\u00020\u0006HÆ\u0003J(\u0010\u0010\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u0006HÆ\u0001¢\u0006\u0002\u0010\u0011J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\u000b\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;", "D", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", Argument.Delimiters.none, "member", "baseScope", "Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;)V", "getMember", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;", "getBaseScope", "()Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;", "component1", "component2", "copy", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirCallableSymbol;Lorg/jetbrains/kotlin/fir/scopes/FirTypeScope;)Lorg/jetbrains/kotlin/fir/scopes/MemberWithBaseScope;", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class MemberWithBaseScope<D extends FirCallableSymbol<?>> {
    private final FirTypeScope baseScope;
    private final D member;

    public MemberWithBaseScope(D d, FirTypeScope firTypeScope) {
        d.getClass();
        firTypeScope.getClass();
        this.member = d;
        this.baseScope = firTypeScope;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ MemberWithBaseScope copy$default(MemberWithBaseScope memberWithBaseScope, FirCallableSymbol firCallableSymbol, FirTypeScope firTypeScope, int i, Object obj) {
        if ((i & 1) != 0) {
            firCallableSymbol = memberWithBaseScope.member;
        }
        if ((i & 2) != 0) {
            firTypeScope = memberWithBaseScope.baseScope;
        }
        return memberWithBaseScope.copy(firCallableSymbol, firTypeScope);
    }

    public final D component1() {
        return this.member;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final FirTypeScope getBaseScope() {
        return this.baseScope;
    }

    public final MemberWithBaseScope<D> copy(D member, FirTypeScope baseScope) {
        member.getClass();
        baseScope.getClass();
        return new MemberWithBaseScope<>(member, baseScope);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof MemberWithBaseScope)) {
            return false;
        }
        MemberWithBaseScope memberWithBaseScope = (MemberWithBaseScope) other;
        return Intrinsics.areEqual(this.member, memberWithBaseScope.member) && Intrinsics.areEqual(this.baseScope, memberWithBaseScope.baseScope);
    }

    public final FirTypeScope getBaseScope() {
        return this.baseScope;
    }

    public final D getMember() {
        return this.member;
    }

    public int hashCode() {
        return (this.member.hashCode() * 31) + this.baseScope.hashCode();
    }

    public String toString() {
        return "MemberWithBaseScope(member=" + this.member + ", baseScope=" + this.baseScope + ')';
    }
}
