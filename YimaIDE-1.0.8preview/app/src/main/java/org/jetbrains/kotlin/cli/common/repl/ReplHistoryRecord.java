package org.jetbrains.kotlin.cli.common.repl;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0086\b\u0018\u0000*\u0006\b\u0000\u0010\u0001 \u00012\u00020\u0002B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00028\u0000¢\u0006\u0004\b\u0006\u0010\u0007J\t\u0010\r\u001a\u00020\u0004HÆ\u0003J\u000e\u0010\u000e\u001a\u00028\u0000HÆ\u0003¢\u0006\u0002\u0010\u000bJ(\u0010\u000f\u001a\b\u0012\u0004\u0012\u00028\u00000\u00002\b\b\u0002\u0010\u0003\u001a\u00020\u00042\b\b\u0002\u0010\u0005\u001a\u00028\u0000HÆ\u0001¢\u0006\u0002\u0010\u0010J\u0014\u0010\u0011\u001a\u00020\u00122\b\u0010\u0013\u001a\u0004\u0018\u00010\u0002HÖ\u0083\u0004J\n\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004J\n\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0013\u0010\u0005\u001a\u00028\u0000¢\u0006\n\n\u0002\u0010\f\u001a\u0004\b\n\u0010\u000b¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/ReplHistoryRecord;", "T", Argument.Delimiters.none, "id", "Lorg/jetbrains/kotlin/cli/common/repl/ILineId;", "item", "<init>", "(Lorg/jetbrains/kotlin/cli/common/repl/ILineId;Ljava/lang/Object;)V", "getId", "()Lorg/jetbrains/kotlin/cli/common/repl/ILineId;", "getItem", "()Ljava/lang/Object;", "Ljava/lang/Object;", "component1", "component2", "copy", "(Lorg/jetbrains/kotlin/cli/common/repl/ILineId;Ljava/lang/Object;)Lorg/jetbrains/kotlin/cli/common/repl/ReplHistoryRecord;", "equals", Argument.Delimiters.none, "other", "hashCode", Argument.Delimiters.none, "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class ReplHistoryRecord<T> {
    private final ILineId id;
    private final T item;

    public ReplHistoryRecord(ILineId iLineId, T t) {
        iLineId.getClass();
        this.id = iLineId;
        this.item = t;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public static /* synthetic */ ReplHistoryRecord copy$default(ReplHistoryRecord replHistoryRecord, ILineId iLineId, Object obj, int i, Object obj2) {
        if ((i & 1) != 0) {
            iLineId = replHistoryRecord.id;
        }
        if ((i & 2) != 0) {
            obj = replHistoryRecord.item;
        }
        return replHistoryRecord.copy(iLineId, obj);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final ILineId getId() {
        return this.id;
    }

    public final T component2() {
        return this.item;
    }

    public final ReplHistoryRecord<T> copy(ILineId id, T item) {
        id.getClass();
        return new ReplHistoryRecord<>(id, item);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ReplHistoryRecord)) {
            return false;
        }
        ReplHistoryRecord replHistoryRecord = (ReplHistoryRecord) other;
        return Intrinsics.areEqual(this.id, replHistoryRecord.id) && Intrinsics.areEqual(this.item, replHistoryRecord.item);
    }

    public final ILineId getId() {
        return this.id;
    }

    public final T getItem() {
        return this.item;
    }

    public int hashCode() {
        int iHashCode = this.id.hashCode() * 31;
        T t = this.item;
        return iHashCode + (t == null ? 0 : t.hashCode());
    }

    public String toString() {
        return "ReplHistoryRecord(id=" + this.id + ", item=" + this.item + ')';
    }
}
