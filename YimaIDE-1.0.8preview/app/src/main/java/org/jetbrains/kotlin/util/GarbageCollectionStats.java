package org.jetbrains.kotlin.util;

import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\t\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0003\b\u0086\b\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0005¢\u0006\u0004\b\u0007\u0010\bJ\t\u0010\u000e\u001a\u00020\u0003HÆ\u0003J\t\u0010\u000f\u001a\u00020\u0005HÆ\u0003J\t\u0010\u0010\u001a\u00020\u0005HÆ\u0003J'\u0010\u0011\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0005HÆ\u0001J\u0014\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\u0015\u001a\u00020\u0016HÖ\u0081\u0004J\n\u0010\u0017\u001a\u00020\u0003HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0006\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\f¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/util/GarbageCollectionStats;", "", "kind", "", "millis", "", "count", "<init>", "(Ljava/lang/String;JJ)V", "getKind", "()Ljava/lang/String;", "getMillis", "()J", "getCount", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "", "toString", "Companion", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
public final /* data */ class GarbageCollectionStats {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final GarbageCollectionStats EMPTY = new GarbageCollectionStats("", 0, 0);
    private final long count;
    private final String kind;
    private final long millis;

    public GarbageCollectionStats(String str, long j, long j2) {
        str.getClass();
        this.kind = str;
        this.millis = j;
        this.count = j2;
    }

    public static /* synthetic */ GarbageCollectionStats copy$default(GarbageCollectionStats garbageCollectionStats, String str, long j, long j2, int i, Object obj) {
        if ((i & 1) != 0) {
            str = garbageCollectionStats.kind;
        }
        if ((i & 2) != 0) {
            j = garbageCollectionStats.millis;
        }
        if ((i & 4) != 0) {
            j2 = garbageCollectionStats.count;
        }
        return garbageCollectionStats.copy(str, j, j2);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final String getKind() {
        return this.kind;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final long getMillis() {
        return this.millis;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final long getCount() {
        return this.count;
    }

    public final GarbageCollectionStats copy(String kind, long millis, long count) {
        kind.getClass();
        return new GarbageCollectionStats(kind, millis, count);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof GarbageCollectionStats)) {
            return false;
        }
        GarbageCollectionStats garbageCollectionStats = (GarbageCollectionStats) other;
        return Intrinsics.areEqual(this.kind, garbageCollectionStats.kind) && this.millis == garbageCollectionStats.millis && this.count == garbageCollectionStats.count;
    }

    public final long getCount() {
        return this.count;
    }

    public final String getKind() {
        return this.kind;
    }

    public final long getMillis() {
        return this.millis;
    }

    public int hashCode() {
        return (((this.kind.hashCode() * 31) + Long.hashCode(this.millis)) * 31) + Long.hashCode(this.count);
    }

    public String toString() {
        return "GarbageCollectionStats(kind=" + this.kind + ", millis=" + this.millis + ", count=" + this.count + ')';
    }

    @Metadata(d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/util/GarbageCollectionStats$Companion;", "", "<init>", "()V", "EMPTY", "Lorg/jetbrains/kotlin/util/GarbageCollectionStats;", "getEMPTY", "()Lorg/jetbrains/kotlin/util/GarbageCollectionStats;", "org.jetbrains.kotlin:compiler.common"}, k = 1, mv = {2, 2, 0}, xi = OPCode.BACKREFN)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final GarbageCollectionStats getEMPTY() {
            return GarbageCollectionStats.EMPTY;
        }

        private Companion() {
        }
    }
}
