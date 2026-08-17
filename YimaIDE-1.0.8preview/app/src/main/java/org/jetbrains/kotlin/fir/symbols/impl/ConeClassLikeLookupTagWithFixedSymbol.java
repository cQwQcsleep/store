package org.jetbrains.kotlin.fir.symbols.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.name.ClassId;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\n\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0014\u0010\f\u001a\u00020\r2\b\u0010\u000e\u001a\u0004\u0018\u00010\u000fH\u0096\u0082\u0004J\n\u0010\u0010\u001a\u00020\u0011H\u0096\u0080\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0015\u0010\u0004\u001a\u0006\u0012\u0002\b\u00030\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/ConeClassLikeLookupTagWithFixedSymbol;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "symbol", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;)V", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "getSymbol", "()Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeClassLikeLookupTagWithFixedSymbol extends ConeClassLikeLookupTag {
    private final ClassId classId;
    private final FirClassLikeSymbol<?> symbol;

    public ConeClassLikeLookupTagWithFixedSymbol(ClassId classId, FirClassLikeSymbol<?> firClassLikeSymbol) {
        classId.getClass();
        firClassLikeSymbol.getClass();
        this.classId = classId;
        this.symbol = firClassLikeSymbol;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(ConeClassLikeLookupTagWithFixedSymbol.class, other != null ? other.getClass() : null)) {
            return false;
        }
        other.getClass();
        return Intrinsics.areEqual(this.symbol, ((ConeClassLikeLookupTagWithFixedSymbol) other).symbol);
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag
    public ClassId getClassId() {
        return this.classId;
    }

    public final FirClassLikeSymbol<?> getSymbol() {
        return this.symbol;
    }

    public int hashCode() {
        return this.symbol.hashCode();
    }
}
