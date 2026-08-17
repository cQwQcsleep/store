package com.intellij.util.io.stats;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0012\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B/\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0006\u0010\t\u001a\u00020\b¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u0014\u001a\u00020\u0003HÆ\u0003J\t\u0010\u0015\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0016\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0017\u001a\u00020\bHÆ\u0003J\t\u0010\u0018\u001a\u00020\bHÆ\u0003J;\u0010\u0019\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u00052\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\bHÆ\u0001J\u0013\u0010\u001a\u001a\u00020\u001b2\b\u0010\u001c\u001a\u0004\u0018\u00010\u0001HÖ\u0003J\t\u0010\u001d\u001a\u00020\u0005HÖ\u0001J\t\u0010\u001e\u001a\u00020\u001fHÖ\u0001R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\t\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0012¨\u0006 "}, d2 = {"Lcom/intellij/util/io/stats/PersistentEnumeratorStatistics;", "", "bTreeStatistics", "Lcom/intellij/util/io/stats/BTreeStatistics;", "collisions", "", "values", "dataFileSizeInBytes", "", "storageSizeInBytes", "<init>", "(Lcom/intellij/util/io/stats/BTreeStatistics;IIJJ)V", "getBTreeStatistics", "()Lcom/intellij/util/io/stats/BTreeStatistics;", "getCollisions", "()I", "getValues", "getDataFileSizeInBytes", "()J", "getStorageSizeInBytes", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "", "other", "hashCode", "toString", "", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final /* data */ class PersistentEnumeratorStatistics {
    private final BTreeStatistics bTreeStatistics;
    private final int collisions;
    private final long dataFileSizeInBytes;
    private final long storageSizeInBytes;
    private final int values;

    public PersistentEnumeratorStatistics(BTreeStatistics bTreeStatistics, int i, int i2, long j, long j2) {
        bTreeStatistics.getClass();
        this.bTreeStatistics = bTreeStatistics;
        this.collisions = i;
        this.values = i2;
        this.dataFileSizeInBytes = j;
        this.storageSizeInBytes = j2;
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof PersistentEnumeratorStatistics)) {
            return false;
        }
        PersistentEnumeratorStatistics persistentEnumeratorStatistics = (PersistentEnumeratorStatistics) other;
        return Intrinsics.areEqual(this.bTreeStatistics, persistentEnumeratorStatistics.bTreeStatistics) && this.collisions == persistentEnumeratorStatistics.collisions && this.values == persistentEnumeratorStatistics.values && this.dataFileSizeInBytes == persistentEnumeratorStatistics.dataFileSizeInBytes && this.storageSizeInBytes == persistentEnumeratorStatistics.storageSizeInBytes;
    }

    public int hashCode() {
        return (((((((this.bTreeStatistics.hashCode() * 31) + Integer.hashCode(this.collisions)) * 31) + Integer.hashCode(this.values)) * 31) + Long.hashCode(this.dataFileSizeInBytes)) * 31) + Long.hashCode(this.storageSizeInBytes);
    }

    public String toString() {
        return "PersistentEnumeratorStatistics(bTreeStatistics=" + this.bTreeStatistics + ", collisions=" + this.collisions + ", values=" + this.values + ", dataFileSizeInBytes=" + this.dataFileSizeInBytes + ", storageSizeInBytes=" + this.storageSizeInBytes + ')';
    }
}
