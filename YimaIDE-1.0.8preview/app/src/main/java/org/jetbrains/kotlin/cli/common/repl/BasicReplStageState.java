package org.jetbrains.kotlin.cli.common.repl;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u00012\b\u0012\u0004\u0012\u0002H\u00010\u0002B\u0011\u0012\b\b\u0002\u0010\u0003\u001a\u00020\u0004¢\u0006\u0004\b\u0005\u0010\u0006J\b\u0010\r\u001a\u00020\nH\u0016R\u0011\u0010\u0003\u001a\u00020\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0014\u0010\t\u001a\u00020\n8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u000b\u0010\fR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00028\u00000\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/BasicReplStageState;", "HistoryItemT", "Lorg/jetbrains/kotlin/cli/common/repl/IReplStageState;", "lock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "<init>", "(Ljava/util/concurrent/locks/ReentrantReadWriteLock;)V", "getLock", "()Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "currentGeneration", Argument.Delimiters.none, "getCurrentGeneration", "()I", "getNextLineNo", "history", "Lorg/jetbrains/kotlin/cli/common/repl/BasicReplStageHistory;", "getHistory", "()Lorg/jetbrains/kotlin/cli/common/repl/BasicReplStageHistory;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class BasicReplStageState<HistoryItemT> implements IReplStageState<HistoryItemT> {
    private final BasicReplStageHistory<HistoryItemT> history;
    private final ReentrantReadWriteLock lock;

    public BasicReplStageState(ReentrantReadWriteLock reentrantReadWriteLock) {
        reentrantReadWriteLock.getClass();
        this.lock = reentrantReadWriteLock;
        this.history = new BasicReplStageHistory<>(reentrantReadWriteLock);
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.IReplStageState
    public int getCurrentGeneration() {
        return getHistory().getCurrentGeneration().get();
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.IReplStageState
    public final ReentrantReadWriteLock getLock() {
        return this.lock;
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.IReplStageState
    public int getNextLineNo() {
        return getHistory().getCurrentLineNumber().getAndIncrement();
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.IReplStageState
    public BasicReplStageHistory<HistoryItemT> getHistory() {
        return this.history;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public BasicReplStageState() {
        ReentrantReadWriteLock reentrantReadWriteLock = null;
        this(reentrantReadWriteLock, 1, reentrantReadWriteLock);
    }

    public /* synthetic */ BasicReplStageState(ReentrantReadWriteLock reentrantReadWriteLock, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new ReentrantReadWriteLock() : reentrantReadWriteLock);
    }
}
