package org.jetbrains.kotlin.cli.common.repl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.repl.BasicReplStageHistory;
import org.jetbrains.kotlin.cli.common.repl.ReplHistoryRecord;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001c\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u00022\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00010\u00040\u0003B\u0011\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0006¢\u0006\u0004\b\u0007\u0010\bJ\u001d\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010\u0016J\u0010\u0010\u0017\u001a\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0004H\u0016J\u000e\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00140\u0019H\u0016J\u0016\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00140\u00192\u0006\u0010\u0013\u001a\u00020\u0014H\u0016J\u0018\u0010\u001b\u001a\n\u0012\u0004\u0012\u00020\u0014\u0018\u00010\u001c2\u0006\u0010\u0013\u001a\u00020\u0014H\u0004R\u0014\u0010\u0005\u001a\u00020\u0006X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000eR\u0011\u0010\u000f\u001a\u00020\f¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u000e¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/BasicReplStageHistory;", "T", "Lorg/jetbrains/kotlin/cli/common/repl/IReplStageHistory;", "Ljava/util/ArrayList;", "Lorg/jetbrains/kotlin/cli/common/repl/ReplHistoryRecord;", "lock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "<init>", "(Ljava/util/concurrent/locks/ReentrantReadWriteLock;)V", "getLock", "()Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "currentLineNumber", "Ljava/util/concurrent/atomic/AtomicInteger;", "getCurrentLineNumber", "()Ljava/util/concurrent/atomic/AtomicInteger;", "currentGeneration", "getCurrentGeneration", "push", Argument.Delimiters.none, "id", "Lorg/jetbrains/kotlin/cli/common/repl/ILineId;", "item", "(Lorg/jetbrains/kotlin/cli/common/repl/ILineId;Ljava/lang/Object;)V", "pop", "reset", Argument.Delimiters.none, "resetTo", "tryResetTo", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class BasicReplStageHistory<T> extends ArrayList<ReplHistoryRecord<? extends T>> implements IReplStageHistory<T> {
    private final AtomicInteger currentGeneration;
    private final AtomicInteger currentLineNumber;
    private final ReentrantReadWriteLock lock;

    public BasicReplStageHistory(ReentrantReadWriteLock reentrantReadWriteLock) {
        reentrantReadWriteLock.getClass();
        this.lock = reentrantReadWriteLock;
        this.currentLineNumber = new AtomicInteger(0);
        this.currentGeneration = new AtomicInteger(1);
    }

    public static ILineId b(ReplHistoryRecord replHistoryRecord) {
        replHistoryRecord.getClass();
        return replHistoryRecord.getId();
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean contains(Object obj) {
        if (obj instanceof ReplHistoryRecord) {
            return contains((ReplHistoryRecord<? extends Object>) obj);
        }
        return false;
    }

    public final AtomicInteger getCurrentGeneration() {
        return this.currentGeneration;
    }

    public final AtomicInteger getCurrentLineNumber() {
        return this.currentLineNumber;
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.IReplStageHistory
    public ReentrantReadWriteLock getLock() {
        return this.lock;
    }

    public /* bridge */ int getSize() {
        return super.size();
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int indexOf(Object obj) {
        if (obj instanceof ReplHistoryRecord) {
            return indexOf((ReplHistoryRecord<? extends Object>) obj);
        }
        return -1;
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ int lastIndexOf(Object obj) {
        if (obj instanceof ReplHistoryRecord) {
            return lastIndexOf((ReplHistoryRecord<? extends Object>) obj);
        }
        return -1;
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.IReplStageHistory
    public ReplHistoryRecord<T> pop() {
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
            ReplHistoryRecord<? extends T> replHistoryRecordRemove = isEmpty() ? null : remove(CollectionsKt.getLastIndex(this));
            while (i < readHoldCount) {
                lock2.lock();
                i++;
            }
            return replHistoryRecordRemove;
        } finally {
            while (i < readHoldCount) {
                lock2.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.IReplStageHistory
    public void push(ILineId id, T item) {
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
            add(new ReplHistoryRecord(id, item));
            while (i < readHoldCount) {
                lock2.lock();
                i++;
            }
        } finally {
            while (i < readHoldCount) {
                lock2.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ boolean remove(Object obj) {
        if (obj instanceof ReplHistoryRecord) {
            return remove((ReplHistoryRecord<? extends Object>) obj);
        }
        return false;
    }

    public /* bridge */ ReplHistoryRecord<Object> removeAt(int i) {
        return (ReplHistoryRecord) super.remove(i);
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.IReplStageHistory
    public Iterable<ILineId> reset() {
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
            ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(this, 10));
            Iterator<T> it = iterator();
            while (it.hasNext()) {
                arrayList.add(((ReplHistoryRecord) it.next()).getId());
            }
            clear();
            this.currentGeneration.incrementAndGet();
            this.currentLineNumber.set(0);
            return arrayList;
        } finally {
            while (i < readHoldCount) {
                lock2.lock();
                i++;
            }
            writeLock.unlock();
        }
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.IReplStageHistory
    public Iterable<ILineId> resetTo(ILineId id) {
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
            List<ILineId> listTryResetTo = tryResetTo(id);
            if (listTryResetTo == null) {
                throw new NoSuchElementException("Cannot reset to non-existent line " + id.getNo());
            }
            List<ILineId> list = listTryResetTo;
            while (i < readHoldCount) {
                lock2.lock();
                i++;
            }
            writeLock.unlock();
            return list;
        } catch (Throwable th) {
            while (i < readHoldCount) {
                lock2.lock();
                i++;
            }
            writeLock.unlock();
            throw th;
        }
    }

    @Override // java.util.ArrayList, java.util.AbstractCollection, java.util.Collection, java.util.List
    public final /* bridge */ int size() {
        return getSize();
    }

    public final List<ILineId> tryResetTo(ILineId id) {
        id.getClass();
        Iterator<ReplHistoryRecord<? extends T>> it = iterator();
        int i = 0;
        while (true) {
            if (!it.hasNext()) {
                i = -1;
                break;
            }
            if (Intrinsics.areEqual(it.next().getId(), id)) {
                break;
            }
            i++;
        }
        if (i < 0) {
            return null;
        }
        if (i >= CollectionsKt.getLastIndex(this)) {
            this.currentGeneration.incrementAndGet();
            this.currentLineNumber.set(id.getNo() + 1);
            return CollectionsKt.emptyList();
        }
        int i2 = i + 1;
        List<ILineId> list = SequencesKt.toList(SequencesKt.map(SequencesKt.drop(CollectionsKt.asSequence(this), i2), new Function1() { // from class: yp0
            public final Object invoke(Object obj) {
                return BasicReplStageHistory.b((ReplHistoryRecord) obj);
            }
        }));
        removeRange(i2, size());
        this.currentGeneration.incrementAndGet();
        ILineId iLineId = (ILineId) CollectionsKt.lastOrNull(list);
        if (iLineId != null) {
            this.currentLineNumber.set(iLineId.getNo());
        }
        return list;
    }

    public /* bridge */ boolean contains(ReplHistoryRecord<? extends Object> replHistoryRecord) {
        return super.contains((Object) replHistoryRecord);
    }

    public /* bridge */ int indexOf(ReplHistoryRecord<? extends Object> replHistoryRecord) {
        return super.indexOf((Object) replHistoryRecord);
    }

    public /* bridge */ int lastIndexOf(ReplHistoryRecord<? extends Object> replHistoryRecord) {
        return super.lastIndexOf((Object) replHistoryRecord);
    }

    @Override // java.util.ArrayList, java.util.AbstractList, java.util.List
    public final /* bridge */ ReplHistoryRecord<T> remove(int i) {
        return (ReplHistoryRecord<T>) removeAt(i);
    }

    public /* bridge */ boolean remove(ReplHistoryRecord<? extends Object> replHistoryRecord) {
        return super.remove((Object) replHistoryRecord);
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public BasicReplStageHistory() {
        ReentrantReadWriteLock reentrantReadWriteLock = null;
        this(reentrantReadWriteLock, 1, reentrantReadWriteLock);
    }

    public /* synthetic */ BasicReplStageHistory(ReentrantReadWriteLock reentrantReadWriteLock, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ReentrantReadWriteLock() : reentrantReadWriteLock);
    }
}
