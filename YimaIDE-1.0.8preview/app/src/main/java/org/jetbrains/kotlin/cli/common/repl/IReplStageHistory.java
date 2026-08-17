package org.jetbrains.kotlin.cli.common.repl;

import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00030\u0002J\u0010\u0010\u0004\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003H\u0016J\u001d\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00028\u0000H&¢\u0006\u0002\u0010\nJ\u0010\u0010\u000b\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0003H&J\u0018\u0010\f\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00032\u0006\u0010\u0007\u001a\u00020\bH\u0016J\u000e\u0010\r\u001a\b\u0012\u0004\u0012\u00020\b0\u000eH&J\u0016\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\b0\u000e2\u0006\u0010\u0007\u001a\u00020\bH&R\u0012\u0010\u0010\u001a\u00020\u0011X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0014À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/IReplStageHistory;", "T", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/cli/common/repl/ReplHistoryRecord;", "peek", "push", Argument.Delimiters.none, "id", "Lorg/jetbrains/kotlin/cli/common/repl/ILineId;", "item", "(Lorg/jetbrains/kotlin/cli/common/repl/ILineId;Ljava/lang/Object;)V", "pop", "verifiedPop", "reset", Argument.Delimiters.none, "resetTo", "lock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "getLock", "()Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface IReplStageHistory<T> extends List<ReplHistoryRecord<? extends T>>, KMappedMarker {
    ReentrantReadWriteLock getLock();

    default ReplHistoryRecord<T> peek() {
        ReentrantReadWriteLock.ReadLock lock = getLock().readLock();
        lock.lock();
        try {
            return (ReplHistoryRecord) CollectionsKt.lastOrNull(this);
        } finally {
            lock.unlock();
        }
    }

    ReplHistoryRecord<T> pop();

    void push(ILineId id, T item);

    Iterable<ILineId> reset();

    Iterable<ILineId> resetTo(ILineId id);

    default ReplHistoryRecord<T> verifiedPop(ILineId id) {
        id.getClass();
        ReentrantReadWriteLock lock = getLock();
        ReentrantReadWriteLock.ReadLock lock2 = lock.readLock();
        int i = 0;
        int readHoldCount = lock.getWriteHoldCount() == 0 ? lock.getReadHoldCount() : 0;
        for (int i2 = 0; i2 < readHoldCount; i2++) {
            lock2.unlock();
        }
        ReentrantReadWriteLock.WriteLock writeLock = lock.writeLock();
        writeLock.lock();
        try {
            ReplHistoryRecord replHistoryRecord = (ReplHistoryRecord) CollectionsKt.lastOrNull(this);
            return Intrinsics.areEqual(replHistoryRecord != null ? replHistoryRecord.getId() : null, id) ? pop() : null;
        } finally {
            while (i < readHoldCount) {
                lock2.lock();
                i++;
            }
            writeLock.unlock();
        }
    }
}
