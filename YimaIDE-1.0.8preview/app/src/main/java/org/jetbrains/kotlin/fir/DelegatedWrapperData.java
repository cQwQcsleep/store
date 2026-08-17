package org.jetbrains.kotlin.fir;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirCallableDeclaration;
import org.jetbrains.kotlin.fir.symbols.impl.FirFieldSymbol;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000e\n\u0000\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B\u001f\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ\n\u0010\u0012\u001a\u00020\u0013H\u0096\u0080\u0004R\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/fir/DelegatedWrapperData;", "D", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", Argument.Delimiters.none, "wrapped", "containingClass", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "delegateFieldSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;)V", "getWrapped", "()Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "Lorg/jetbrains/kotlin/fir/declarations/FirCallableDeclaration;", "getContainingClass", "()Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "getDelegateFieldSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirFieldSymbol;", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DelegatedWrapperData<D extends FirCallableDeclaration> {
    private final ConeClassLikeLookupTag containingClass;
    private final FirFieldSymbol delegateFieldSymbol;
    private final D wrapped;

    public DelegatedWrapperData(D d, ConeClassLikeLookupTag coneClassLikeLookupTag, FirFieldSymbol firFieldSymbol) {
        d.getClass();
        coneClassLikeLookupTag.getClass();
        firFieldSymbol.getClass();
        this.wrapped = d;
        this.containingClass = coneClassLikeLookupTag;
        this.delegateFieldSymbol = firFieldSymbol;
    }

    public final ConeClassLikeLookupTag getContainingClass() {
        return this.containingClass;
    }

    public final FirFieldSymbol getDelegateFieldSymbol() {
        return this.delegateFieldSymbol;
    }

    public final D getWrapped() {
        return this.wrapped;
    }

    public String toString() {
        return "[wrapped=" + this.wrapped.getSymbol() + ", containingClass=" + this.containingClass + ", delegateFieldSymbol=" + this.delegateFieldSymbol;
    }
}
