package org.jetbrains.kotlin.fir.extensions;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.scopes.FirContainingNamesAwareScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirClassDeclaredMemberScope;
import org.jetbrains.kotlin.fir.scopes.impl.FirNestedClassifierScope;
import org.jetbrains.kotlin.fir.symbols.impl.FirClassSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\b6\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003:\u0002\u0016\u0017B\u001f\b\u0004\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005\u0012\b\u0010\u0006\u001a\u0004\u0018\u00018\u0000¢\u0006\u0004\b\u0007\u0010\bJ\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0003H\u0096\u0082\u0004J\n\u0010\u0015\u001a\u00020\u000fH\u0096\u0080\u0004R\u0015\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0015\u0010\u0006\u001a\u0004\u0018\u00018\u0000¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0012\u0010\u000e\u001a\u00020\u000fX¤\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\u0082\u0001\u0002\u0018\u0019¨\u0006\u001a"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/DeclarationGenerationContext;", "T", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", Argument.Delimiters.none, "owner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "declaredScope", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;)V", "getOwner", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "getDeclaredScope", "()Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "Lorg/jetbrains/kotlin/fir/scopes/FirContainingNamesAwareScope;", "kind", Argument.Delimiters.none, "getKind", "()I", "equals", Argument.Delimiters.none, "other", "hashCode", "Member", "Nested", "Lorg/jetbrains/kotlin/fir/extensions/DeclarationGenerationContext$Member;", "Lorg/jetbrains/kotlin/fir/extensions/DeclarationGenerationContext$Nested;", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class DeclarationGenerationContext<T extends FirContainingNamesAwareScope> {
    private final T declaredScope;
    private final FirClassSymbol<?> owner;

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/DeclarationGenerationContext$Member;", "Lorg/jetbrains/kotlin/fir/extensions/DeclarationGenerationContext;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassDeclaredMemberScope;", "owner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "declaredScope", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;Lorg/jetbrains/kotlin/fir/scopes/impl/FirClassDeclaredMemberScope;)V", "kind", Argument.Delimiters.none, "getKind", "()I", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Member extends DeclarationGenerationContext<FirClassDeclaredMemberScope> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Member(FirClassSymbol<?> firClassSymbol, FirClassDeclaredMemberScope firClassDeclaredMemberScope) {
            super(firClassSymbol, firClassDeclaredMemberScope, null);
            firClassSymbol.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.extensions.DeclarationGenerationContext
        public int getKind() {
            return 1;
        }
    }

    @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0003\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u001d\u0012\n\u0010\u0003\u001a\u0006\u0012\u0002\b\u00030\u0004\u0012\b\u0010\u0005\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\u0006\u0010\u0007R\u0014\u0010\b\u001a\u00020\t8TX\u0094\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lorg/jetbrains/kotlin/fir/extensions/DeclarationGenerationContext$Nested;", "Lorg/jetbrains/kotlin/fir/extensions/DeclarationGenerationContext;", "Lorg/jetbrains/kotlin/fir/scopes/impl/FirNestedClassifierScope;", "owner", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;", "declaredScope", "<init>", "(Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassSymbol;Lorg/jetbrains/kotlin/fir/scopes/impl/FirNestedClassifierScope;)V", "kind", Argument.Delimiters.none, "getKind", "()I", "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Nested extends DeclarationGenerationContext<FirNestedClassifierScope> {
        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public Nested(FirClassSymbol<?> firClassSymbol, FirNestedClassifierScope firNestedClassifierScope) {
            super(firClassSymbol, firNestedClassifierScope, null);
            firClassSymbol.getClass();
        }

        @Override // org.jetbrains.kotlin.fir.extensions.DeclarationGenerationContext
        public int getKind() {
            return 2;
        }
    }

    private DeclarationGenerationContext(FirClassSymbol<?> firClassSymbol, T t) {
        this.owner = firClassSymbol;
        this.declaredScope = t;
    }

    public boolean equals(Object other) {
        if (getClass() != (other != null ? other.getClass() : null)) {
            return false;
        }
        if (other instanceof DeclarationGenerationContext) {
            return Intrinsics.areEqual(this.owner, ((DeclarationGenerationContext) other).owner);
        }
        w01.a("Failed requirement.");
        return false;
    }

    public final T getDeclaredScope() {
        return this.declaredScope;
    }

    public abstract int getKind();

    public final FirClassSymbol<?> getOwner() {
        return this.owner;
    }

    public int hashCode() {
        return this.owner.hashCode() + getKind();
    }

    public /* synthetic */ DeclarationGenerationContext(FirClassSymbol firClassSymbol, FirContainingNamesAwareScope firContainingNamesAwareScope, DefaultConstructorMarker defaultConstructorMarker) {
        this(firClassSymbol, firContainingNamesAwareScope);
    }
}
