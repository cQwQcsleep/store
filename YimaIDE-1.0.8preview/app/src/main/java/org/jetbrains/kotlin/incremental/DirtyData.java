package org.jetbrains.kotlin.incremental;

import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.name.FqName;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u00002\u00020\u0001B7\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\u0004\b\b\u0010\tJ\u000f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003HÆ\u0003J\u000f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J\u000f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0003J9\u0010\u0011\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0018HÖ\u0081\u0004R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\u000bR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000b¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/incremental/DirtyData;", "", "dirtyLookupSymbols", "", "Lorg/jetbrains/kotlin/incremental/LookupSymbol;", "dirtyClassesFqNames", "Lorg/jetbrains/kotlin/name/FqName;", "dirtyClassesFqNamesForceRecompile", "<init>", "(Ljava/util/Collection;Ljava/util/Collection;Ljava/util/Collection;)V", "getDirtyLookupSymbols", "()Ljava/util/Collection;", "getDirtyClassesFqNames", "getDirtyClassesFqNamesForceRecompile", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public final /* data */ class DirtyData {
    private final Collection<FqName> dirtyClassesFqNames;
    private final Collection<FqName> dirtyClassesFqNamesForceRecompile;
    private final Collection<LookupSymbol> dirtyLookupSymbols;

    public /* synthetic */ DirtyData(List list, List list2, List list3, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list, (i & 2) != 0 ? CollectionsKt.emptyList() : list2, (i & 4) != 0 ? CollectionsKt.emptyList() : list3);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ DirtyData copy$default(DirtyData dirtyData, Collection collection, Collection collection2, Collection collection3, int i, Object obj) {
        if ((i & 1) != 0) {
            collection = dirtyData.dirtyLookupSymbols;
        }
        if ((i & 2) != 0) {
            collection2 = dirtyData.dirtyClassesFqNames;
        }
        if ((i & 4) != 0) {
            collection3 = dirtyData.dirtyClassesFqNamesForceRecompile;
        }
        return dirtyData.copy(collection, collection2, collection3);
    }

    public final Collection<LookupSymbol> component1() {
        return this.dirtyLookupSymbols;
    }

    public final Collection<FqName> component2() {
        return this.dirtyClassesFqNames;
    }

    public final Collection<FqName> component3() {
        return this.dirtyClassesFqNamesForceRecompile;
    }

    public final DirtyData copy(Collection<LookupSymbol> dirtyLookupSymbols, Collection<FqName> dirtyClassesFqNames, Collection<FqName> dirtyClassesFqNamesForceRecompile) {
        dirtyLookupSymbols.getClass();
        dirtyClassesFqNames.getClass();
        dirtyClassesFqNamesForceRecompile.getClass();
        return new DirtyData(dirtyLookupSymbols, dirtyClassesFqNames, dirtyClassesFqNamesForceRecompile);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof DirtyData)) {
            return false;
        }
        DirtyData dirtyData = (DirtyData) other;
        return Intrinsics.areEqual(this.dirtyLookupSymbols, dirtyData.dirtyLookupSymbols) && Intrinsics.areEqual(this.dirtyClassesFqNames, dirtyData.dirtyClassesFqNames) && Intrinsics.areEqual(this.dirtyClassesFqNamesForceRecompile, dirtyData.dirtyClassesFqNamesForceRecompile);
    }

    public final Collection<FqName> getDirtyClassesFqNames() {
        return this.dirtyClassesFqNames;
    }

    public final Collection<FqName> getDirtyClassesFqNamesForceRecompile() {
        return this.dirtyClassesFqNamesForceRecompile;
    }

    public final Collection<LookupSymbol> getDirtyLookupSymbols() {
        return this.dirtyLookupSymbols;
    }

    public int hashCode() {
        return (((this.dirtyLookupSymbols.hashCode() * 31) + this.dirtyClassesFqNames.hashCode()) * 31) + this.dirtyClassesFqNamesForceRecompile.hashCode();
    }

    public String toString() {
        return "DirtyData(dirtyLookupSymbols=" + this.dirtyLookupSymbols + ", dirtyClassesFqNames=" + this.dirtyClassesFqNames + ", dirtyClassesFqNamesForceRecompile=" + this.dirtyClassesFqNamesForceRecompile + ')';
    }

    public DirtyData(Collection<LookupSymbol> collection, Collection<FqName> collection2, Collection<FqName> collection3) {
        collection.getClass();
        collection2.getClass();
        collection3.getClass();
        this.dirtyLookupSymbols = collection;
        this.dirtyClassesFqNames = collection2;
        this.dirtyClassesFqNamesForceRecompile = collection3;
    }

    public DirtyData() {
        this(null, null, null, 7, null);
    }
}
