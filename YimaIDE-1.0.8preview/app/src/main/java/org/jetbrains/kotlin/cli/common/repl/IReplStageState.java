package org.jetbrains.kotlin.cli.common.repl;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\bf\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002J\b\u0010\u000f\u001a\u00020\fH\u0016J+\u0010\u0010\u001a\u0002H\u0011\"\f\b\u0001\u0010\u0011*\u0006\u0012\u0002\b\u00030\u00002\u000e\u0010\u0012\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00110\u0013H\u0016¢\u0006\u0002\u0010\u0014J\b\u0010\u0015\u001a\u00020\u0016H\u0016R\u0018\u0010\u0003\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0005\u0010\u0006R\u0012\u0010\u0007\u001a\u00020\bX¦\u0004¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0012\u0010\u000b\u001a\u00020\fX¦\u0004¢\u0006\u0006\u001a\u0004\b\r\u0010\u000eø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0017À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/IReplStageState;", "T", Argument.Delimiters.none, "history", "Lorg/jetbrains/kotlin/cli/common/repl/IReplStageHistory;", "getHistory", "()Lorg/jetbrains/kotlin/cli/common/repl/IReplStageHistory;", "lock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "getLock", "()Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "currentGeneration", Argument.Delimiters.none, "getCurrentGeneration", "()I", "getNextLineNo", "asState", "StateT", "target", "Ljava/lang/Class;", "(Ljava/lang/Class;)Lorg/jetbrains/kotlin/cli/common/repl/IReplStageState;", "dispose", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public interface IReplStageState<T> {
    /* JADX WARN: Multi-variable type inference failed */
    default <StateT extends IReplStageState<?>> StateT asState(Class<? extends StateT> target) {
        target.getClass();
        if (target.isAssignableFrom(getClass())) {
            return this;
        }
        e4b.a(this, " is not an expected instance of IReplStageState");
        return null;
    }

    default void dispose() {
    }

    int getCurrentGeneration();

    IReplStageHistory<T> getHistory();

    ReentrantReadWriteLock getLock();

    default int getNextLineNo() {
        ILineId id;
        ReplHistoryRecord<T> replHistoryRecordPeek = getHistory().peek();
        if (replHistoryRecordPeek == null || (id = replHistoryRecordPeek.getId()) == null) {
            return 0;
        }
        return id.getNo() + 1;
    }
}
