package org.jetbrains.kotlin.util;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\n\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\f\u001a\u00020\u0003HÆ\u0003J\t\u0010\r\u001a\u00020\u0005HÆ\u0003J\u001d\u0010\u000e\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004J\n\u0010\u0014\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006\u0015"}, d2 = {"Lorg/jetbrains/kotlin/util/KlibElementStats;", "", "path", "", "size", "", "<init>", "(Ljava/lang/String;J)V", "getPath", "()Ljava/lang/String;", "getSize", "()J", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final /* data */ class KlibElementStats {
    private final String path;
    private final long size;

    public KlibElementStats(String str, long j) {
        str.getClass();
        this.path = str;
        this.size = j;
    }

    public static /* synthetic */ KlibElementStats copy$default(KlibElementStats klibElementStats, String str, long j, int i, Object obj) {
        if ((i & 1) != 0) {
            str = klibElementStats.path;
        }
        if ((i & 2) != 0) {
            j = klibElementStats.size;
        }
        return klibElementStats.copy(str, j);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getPath() {
        return this.path;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getSize() {
        return this.size;
    }

    public final KlibElementStats copy(String path, long size) {
        path.getClass();
        return new KlibElementStats(path, size);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof KlibElementStats)) {
            return false;
        }
        KlibElementStats klibElementStats = (KlibElementStats) other;
        return Intrinsics.areEqual(this.path, klibElementStats.path) && this.size == klibElementStats.size;
    }

    public final String getPath() {
        return this.path;
    }

    public final long getSize() {
        return this.size;
    }

    public int hashCode() {
        return (this.path.hashCode() * 31) + Long.hashCode(this.size);
    }

    public String toString() {
        return "KlibElementStats(path=" + this.path + ", size=" + this.size + ')';
    }
}
