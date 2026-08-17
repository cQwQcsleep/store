package org.jetbrains.kotlin.fir.scopes.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.declarations.FirFunction;
import org.jetbrains.kotlin.fir.resolve.substitution.ConeSubstitutor;
import org.jetbrains.kotlin.fir.symbols.impl.FirTypeAliasSymbol;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\b\b\u0000\u0010\u0001*\u00020\u00022\u00020\u0003B!\u0012\u0006\u0010\u0004\u001a\u00028\u0000\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\b\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\u0004\b\t\u0010\nJ\u000e\u0010\u0012\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\fJ\t\u0010\u0013\u001a\u00020\u0006HÆ\u0003J\u000b\u0010\u0014\u001a\u0004\u0018\u00010\bHÆ\u0003J4\u0010\u0015\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0004\u001a\u00028\u00002\b\b\u0002\u0010\u0005\u001a\u00020\u00062\n\b\u0002\u0010\u0007\u001a\u0004\u0018\u00010\bHÆ\u0001¢\u0006\u0002\u0010\u0016J\u0014\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u0003HÖ\u0083\u0004J\n\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004J\n\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004R\u0013\u0010\u0004\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\r\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0013\u0010\u0007\u001a\u0004\u0018\u00010\b¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/fir/scopes/impl/TypeAliasConstructorInfo;", "T", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", Argument.Delimiters.none, "originalConstructor", "typeAliasSymbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;", "substitutor", "Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "<init>", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;)V", "getOriginalConstructor", "()Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "Lorg/jetbrains/kotlin/fir/declarations/FirFunction;", "getTypeAliasSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;", "getSubstitutor", "()Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;", "component1", "component2", "component3", "copy", "(Lorg/jetbrains/kotlin/fir/declarations/FirFunction;Lorg/jetbrains/kotlin/fir/symbols/impl/FirTypeAliasSymbol;Lorg/jetbrains/kotlin/fir/resolve/substitution/ConeSubstitutor;)Lorg/jetbrains/kotlin/fir/scopes/impl/TypeAliasConstructorInfo;", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:providers"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class TypeAliasConstructorInfo<T extends FirFunction> {
    private final T originalConstructor;
    private final ConeSubstitutor substitutor;
    private final FirTypeAliasSymbol typeAliasSymbol;

    public TypeAliasConstructorInfo(T t, FirTypeAliasSymbol firTypeAliasSymbol, ConeSubstitutor coneSubstitutor) {
        t.getClass();
        firTypeAliasSymbol.getClass();
        this.originalConstructor = t;
        this.typeAliasSymbol = firTypeAliasSymbol;
        this.substitutor = coneSubstitutor;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ TypeAliasConstructorInfo copy$default(TypeAliasConstructorInfo typeAliasConstructorInfo, FirFunction firFunction, FirTypeAliasSymbol firTypeAliasSymbol, ConeSubstitutor coneSubstitutor, int i, Object obj) {
        if ((i & 1) != 0) {
            firFunction = typeAliasConstructorInfo.originalConstructor;
        }
        if ((i & 2) != 0) {
            firTypeAliasSymbol = typeAliasConstructorInfo.typeAliasSymbol;
        }
        if ((i & 4) != 0) {
            coneSubstitutor = typeAliasConstructorInfo.substitutor;
        }
        return typeAliasConstructorInfo.copy(firFunction, firTypeAliasSymbol, coneSubstitutor);
    }

    public final T component1() {
        return this.originalConstructor;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final FirTypeAliasSymbol getTypeAliasSymbol() {
        return this.typeAliasSymbol;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final ConeSubstitutor getSubstitutor() {
        return this.substitutor;
    }

    public final TypeAliasConstructorInfo<T> copy(T originalConstructor, FirTypeAliasSymbol typeAliasSymbol, ConeSubstitutor substitutor) {
        originalConstructor.getClass();
        typeAliasSymbol.getClass();
        return new TypeAliasConstructorInfo<>(originalConstructor, typeAliasSymbol, substitutor);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof TypeAliasConstructorInfo)) {
            return false;
        }
        TypeAliasConstructorInfo typeAliasConstructorInfo = (TypeAliasConstructorInfo) other;
        return Intrinsics.areEqual(this.originalConstructor, typeAliasConstructorInfo.originalConstructor) && Intrinsics.areEqual(this.typeAliasSymbol, typeAliasConstructorInfo.typeAliasSymbol) && Intrinsics.areEqual(this.substitutor, typeAliasConstructorInfo.substitutor);
    }

    public final T getOriginalConstructor() {
        return this.originalConstructor;
    }

    public final ConeSubstitutor getSubstitutor() {
        return this.substitutor;
    }

    public final FirTypeAliasSymbol getTypeAliasSymbol() {
        return this.typeAliasSymbol;
    }

    public int hashCode() {
        int iHashCode = ((this.originalConstructor.hashCode() * 31) + this.typeAliasSymbol.hashCode()) * 31;
        ConeSubstitutor coneSubstitutor = this.substitutor;
        return iHashCode + (coneSubstitutor == null ? 0 : coneSubstitutor.hashCode());
    }

    public String toString() {
        return "TypeAliasConstructorInfo(originalConstructor=" + this.originalConstructor + ", typeAliasSymbol=" + this.typeAliasSymbol + ", substitutor=" + this.substitutor + ')';
    }
}
