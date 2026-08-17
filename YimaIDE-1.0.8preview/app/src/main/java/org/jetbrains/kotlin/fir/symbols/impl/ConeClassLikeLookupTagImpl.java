package org.jetbrains.kotlin.fir.symbols.impl;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.fir.FirSession;
import org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag;
import org.jetbrains.kotlin.name.ClassId;
import org.jetbrains.kotlin.util.WeakPair;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016H\u0096\u0082\u0004J\n\u0010\u0017\u001a\u00020\u0018H\u0096\u0080\u0004R\u0014\u0010\u0002\u001a\u00020\u0003X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R<\u0010\b\u001a\u0016\u0012\u0004\u0012\u00020\n\u0012\n\u0012\b\u0012\u0002\b\u0003\u0018\u00010\u000b\u0018\u00010\t8\u0006@\u0006X\u0087\u000er\u0002\b\u0012¢\u0006\u0014\n\u0000\u0012\u0004\b\f\u0010\r\u001a\u0004\b\u000e\u0010\u000f\"\u0004\b\u0010\u0010\u0011¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/fir/symbols/impl/ConeClassLikeLookupTagImpl;", "Lorg/jetbrains/kotlin/fir/types/ConeClassLikeLookupTag;", "classId", "Lorg/jetbrains/kotlin/name/ClassId;", "<init>", "(Lorg/jetbrains/kotlin/name/ClassId;)V", "getClassId", "()Lorg/jetbrains/kotlin/name/ClassId;", "boundSymbol", "Lorg/jetbrains/kotlin/util/WeakPair;", "Lorg/jetbrains/kotlin/fir/FirSession;", "Lorg/jetbrains/kotlin/fir/symbols/impl/FirClassLikeSymbol;", "getBoundSymbol$annotations", "()V", "getBoundSymbol", "()Lorg/jetbrains/kotlin/util/WeakPair;", "setBoundSymbol", "(Lorg/jetbrains/kotlin/util/WeakPair;)V", "Lorg/jetbrains/kotlin/fir/symbols/impl/LookupTagInternals;", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", Argument.Delimiters.none, "org.jetbrains.kotlin:tree"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConeClassLikeLookupTagImpl extends ConeClassLikeLookupTag {
    private WeakPair<FirSession, FirClassLikeSymbol<?>> boundSymbol;
    private final ClassId classId;

    public ConeClassLikeLookupTagImpl(ClassId classId) {
        classId.getClass();
        this.classId = classId;
        getClassId().isLocal();
    }

    @LookupTagInternals
    public static /* synthetic */ void getBoundSymbol$annotations() {
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!Intrinsics.areEqual(ConeClassLikeLookupTagImpl.class, other != null ? other.getClass() : null)) {
            return false;
        }
        other.getClass();
        return Intrinsics.areEqual(getClassId(), ((ConeClassLikeLookupTagImpl) other).getClassId());
    }

    public final WeakPair<FirSession, FirClassLikeSymbol<?>> getBoundSymbol() {
        return this.boundSymbol;
    }

    @Override // org.jetbrains.kotlin.fir.types.ConeClassLikeLookupTag
    public ClassId getClassId() {
        return this.classId;
    }

    public int hashCode() {
        return getClassId().hashCode();
    }

    public final void setBoundSymbol(WeakPair<FirSession, FirClassLikeSymbol<?>> weakPair) {
        this.boundSymbol = weakPair;
    }
}
