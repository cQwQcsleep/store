package org.jetbrains.kotlin.cli.common.repl;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u00040\u0003B-\u0012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b¢\u0006\u0004\b\t\u0010\nJ+\u0010\u0014\u001a\u0002H\u0015\"\f\b\u0002\u0010\u0015*\u0006\u0012\u0002\b\u00030\u00032\u000e\u0010\u0016\u001a\n\u0012\u0006\b\u0001\u0012\u0002H\u00150\u0017H\u0016¢\u0006\u0002\u0010\u0018J\b\u0010\u0019\u001a\u00020\u001aH\u0016J\b\u0010\u001e\u001a\u00020\u001fH\u0016R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00028\u00000\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00010\u0003¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\fR\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR&\u0010\u0010\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u00040\u0011X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0014\u0010\u001b\u001a\u00020\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006 "}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/AggregatedReplStageState;", "T1", "T2", "Lorg/jetbrains/kotlin/cli/common/repl/IReplStageState;", "Lkotlin/Pair;", "state1", "state2", "lock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "<init>", "(Lorg/jetbrains/kotlin/cli/common/repl/IReplStageState;Lorg/jetbrains/kotlin/cli/common/repl/IReplStageState;Ljava/util/concurrent/locks/ReentrantReadWriteLock;)V", "getState1", "()Lorg/jetbrains/kotlin/cli/common/repl/IReplStageState;", "getState2", "getLock", "()Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "history", "Lorg/jetbrains/kotlin/cli/common/repl/IReplStageHistory;", "getHistory", "()Lorg/jetbrains/kotlin/cli/common/repl/IReplStageHistory;", "asState", "StateT", "target", "Ljava/lang/Class;", "(Ljava/lang/Class;)Lorg/jetbrains/kotlin/cli/common/repl/IReplStageState;", "getNextLineNo", Argument.Delimiters.none, "currentGeneration", "getCurrentGeneration", "()I", "dispose", Argument.Delimiters.none, "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class AggregatedReplStageState<T1, T2> implements IReplStageState<Pair<? extends T1, ? extends T2>> {
    private final IReplStageHistory<Pair<T1, T2>> history;
    private final ReentrantReadWriteLock lock;
    private final IReplStageState<T1> state1;
    private final IReplStageState<T2> state2;

    public AggregatedReplStageState(IReplStageState<T1> iReplStageState, IReplStageState<T2> iReplStageState2, ReentrantReadWriteLock reentrantReadWriteLock) {
        iReplStageState.getClass();
        iReplStageState2.getClass();
        reentrantReadWriteLock.getClass();
        this.state1 = iReplStageState;
        this.state2 = iReplStageState2;
        this.lock = reentrantReadWriteLock;
        this.history = new AggregatedReplStateHistory(iReplStageState.getHistory(), iReplStageState2.getHistory(), reentrantReadWriteLock);
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.IReplStageState
    public <StateT extends IReplStageState<?>> StateT asState(Class<? extends StateT> target) {
        target.getClass();
        if (target.isAssignableFrom(this.state1.getClass())) {
            IReplStageState<T1> iReplStageState = this.state1;
            iReplStageState.getClass();
            return iReplStageState;
        }
        if (!target.isAssignableFrom(this.state2.getClass())) {
            return (StateT) super.asState(target);
        }
        IReplStageState<T2> iReplStageState2 = this.state2;
        iReplStageState2.getClass();
        return iReplStageState2;
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.IReplStageState
    public void dispose() {
        this.state2.dispose();
        this.state1.dispose();
        super.dispose();
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.IReplStageState
    public int getCurrentGeneration() {
        return this.state1.getCurrentGeneration();
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.IReplStageState
    public IReplStageHistory<Pair<T1, T2>> getHistory() {
        return this.history;
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.IReplStageState
    public final ReentrantReadWriteLock getLock() {
        return this.lock;
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.IReplStageState
    public int getNextLineNo() {
        return this.state1.getNextLineNo();
    }

    public final IReplStageState<T1> getState1() {
        return this.state1;
    }

    public final IReplStageState<T2> getState2() {
        return this.state2;
    }

    public /* synthetic */ AggregatedReplStageState(IReplStageState iReplStageState, IReplStageState iReplStageState2, ReentrantReadWriteLock reentrantReadWriteLock, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(iReplStageState, iReplStageState2, (i & 4) != 0 ? new ReentrantReadWriteLock() : reentrantReadWriteLock);
    }
}
