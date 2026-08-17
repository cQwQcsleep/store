package org.jetbrains.kotlin.fir.lightTree.fir;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.KtLightSourceElement;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.expressions.FirExpression;
import org.jetbrains.kotlin.fir.types.FirTypeRef;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\t\u0010\u0011\u001a\u00020\u0003HÆ\u0003J\u000f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005HÆ\u0003J\u000b\u0010\u0013\u001a\u0004\u0018\u00010\bHÆ\u0003J/\u0010\u0014\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\u000e\b\u0002\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u00052\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001J\u0014\u0010\u0015\u001a\u00020\u00162\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010¨\u0006\u001c"}, d2 = {"Lorg/jetbrains/kotlin/fir/lightTree/fir/DelegatedConstructorWrapper;", Argument.Delimiters.none, "delegatedSuperTypeRef", "Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "arguments", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/fir/expressions/FirExpression;", "source", "Lorg/jetbrains/kotlin/KtLightSourceElement;", "<init>", "(Lorg/jetbrains/kotlin/fir/types/FirTypeRef;Ljava/util/List;Lorg/jetbrains/kotlin/KtLightSourceElement;)V", "getDelegatedSuperTypeRef", "()Lorg/jetbrains/kotlin/fir/types/FirTypeRef;", "getArguments", "()Ljava/util/List;", "getSource", "()Lorg/jetbrains/kotlin/KtLightSourceElement;", "component1", "component2", "component3", "copy", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin.fir:light-tree2fir"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class DelegatedConstructorWrapper {
    private final List<FirExpression> arguments;
    private final FirTypeRef delegatedSuperTypeRef;
    private final KtLightSourceElement source;

    /* JADX WARN: Multi-variable type inference failed */
    public DelegatedConstructorWrapper(FirTypeRef firTypeRef, List<? extends FirExpression> list, KtLightSourceElement ktLightSourceElement) {
        firTypeRef.getClass();
        list.getClass();
        this.delegatedSuperTypeRef = firTypeRef;
        this.arguments = list;
        this.source = ktLightSourceElement;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DelegatedConstructorWrapper copy$default(DelegatedConstructorWrapper delegatedConstructorWrapper, FirTypeRef firTypeRef, List list, KtLightSourceElement ktLightSourceElement, int i, Object obj) {
        if ((i & 1) != 0) {
            firTypeRef = delegatedConstructorWrapper.delegatedSuperTypeRef;
        }
        if ((i & 2) != 0) {
            list = delegatedConstructorWrapper.arguments;
        }
        if ((i & 4) != 0) {
            ktLightSourceElement = delegatedConstructorWrapper.source;
        }
        return delegatedConstructorWrapper.copy(firTypeRef, list, ktLightSourceElement);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final FirTypeRef getDelegatedSuperTypeRef() {
        return this.delegatedSuperTypeRef;
    }

    public final List<FirExpression> component2() {
        return this.arguments;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final KtLightSourceElement getSource() {
        return this.source;
    }

    public final DelegatedConstructorWrapper copy(FirTypeRef delegatedSuperTypeRef, List<? extends FirExpression> arguments, KtLightSourceElement source) {
        delegatedSuperTypeRef.getClass();
        arguments.getClass();
        return new DelegatedConstructorWrapper(delegatedSuperTypeRef, arguments, source);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DelegatedConstructorWrapper)) {
            return false;
        }
        DelegatedConstructorWrapper delegatedConstructorWrapper = (DelegatedConstructorWrapper) other;
        return Intrinsics.areEqual(this.delegatedSuperTypeRef, delegatedConstructorWrapper.delegatedSuperTypeRef) && Intrinsics.areEqual(this.arguments, delegatedConstructorWrapper.arguments) && Intrinsics.areEqual(this.source, delegatedConstructorWrapper.source);
    }

    public final List<FirExpression> getArguments() {
        return this.arguments;
    }

    public final FirTypeRef getDelegatedSuperTypeRef() {
        return this.delegatedSuperTypeRef;
    }

    public final KtLightSourceElement getSource() {
        return this.source;
    }

    public int hashCode() {
        int iHashCode = ((this.delegatedSuperTypeRef.hashCode() * 31) + this.arguments.hashCode()) * 31;
        KtLightSourceElement ktLightSourceElement = this.source;
        return iHashCode + (ktLightSourceElement == null ? 0 : ktLightSourceElement.hashCode());
    }

    public String toString() {
        return "DelegatedConstructorWrapper(delegatedSuperTypeRef=" + this.delegatedSuperTypeRef + ", arguments=" + this.arguments + ", source=" + this.source + ')';
    }
}
