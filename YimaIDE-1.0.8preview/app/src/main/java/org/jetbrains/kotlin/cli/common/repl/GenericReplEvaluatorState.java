package org.jetbrains.kotlin.cli.common.repl;

import java.io.File;
import java.util.List;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001c\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\b\u0016\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B)\u0012\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u0012\b\u0010\u0006\u001a\u0004\u0018\u00010\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bR\u0014\u0010\b\u001a\u00020\tX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u001a\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00020\u000fX\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0014\u0010\u0012\u001a\u00020\u00138VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0016\u001a\u00020\u0017¢\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0019R\u0017\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u00050\u001b8F¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/GenericReplEvaluatorState;", "Lorg/jetbrains/kotlin/cli/common/repl/IReplStageState;", "Lorg/jetbrains/kotlin/cli/common/repl/EvalClassWithInstanceAndLoader;", "baseClasspath", Argument.Delimiters.none, "Ljava/io/File;", "baseClassloader", "Ljava/lang/ClassLoader;", "lock", "Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "<init>", "(Ljava/lang/Iterable;Ljava/lang/ClassLoader;Ljava/util/concurrent/locks/ReentrantReadWriteLock;)V", "getLock", "()Ljava/util/concurrent/locks/ReentrantReadWriteLock;", "history", "Lorg/jetbrains/kotlin/cli/common/repl/IReplStageHistory;", "getHistory", "()Lorg/jetbrains/kotlin/cli/common/repl/IReplStageHistory;", "currentGeneration", Argument.Delimiters.none, "getCurrentGeneration", "()I", "topClassLoader", "Lorg/jetbrains/kotlin/cli/common/repl/ReplClassLoader;", "getTopClassLoader", "()Lorg/jetbrains/kotlin/cli/common/repl/ReplClassLoader;", "currentClasspath", Argument.Delimiters.none, "getCurrentClasspath", "()Ljava/util/List;", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public class GenericReplEvaluatorState implements IReplStageState<EvalClassWithInstanceAndLoader> {
    private final IReplStageHistory<EvalClassWithInstanceAndLoader> history;
    private final ReentrantReadWriteLock lock;
    private final ReplClassLoader topClassLoader;

    public GenericReplEvaluatorState(Iterable<? extends File> iterable, ClassLoader classLoader, ReentrantReadWriteLock reentrantReadWriteLock) {
        iterable.getClass();
        reentrantReadWriteLock.getClass();
        this.lock = reentrantReadWriteLock;
        this.history = new BasicReplStageHistory(getLock());
        this.topClassLoader = GenericEvaluatorStateKt.makeReplClassLoader(classLoader, iterable);
    }

    public final List<File> getCurrentClasspath() {
        List<File> listListAllUrlsAsFiles;
        EvalClassWithInstanceAndLoader item;
        ClassLoader classLoader;
        ReentrantReadWriteLock.ReadLock lock = getLock().readLock();
        lock.lock();
        try {
            ReplHistoryRecord<EvalClassWithInstanceAndLoader> replHistoryRecordPeek = getHistory().peek();
            if (replHistoryRecordPeek == null || (item = replHistoryRecordPeek.getItem()) == null || (classLoader = item.getClassLoader()) == null || (listListAllUrlsAsFiles = ReplUtilKt.listAllUrlsAsFiles(classLoader)) == null) {
                listListAllUrlsAsFiles = ReplUtilKt.listAllUrlsAsFiles(this.topClassLoader);
            }
            return listListAllUrlsAsFiles;
        } finally {
            lock.unlock();
        }
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.IReplStageState
    public int getCurrentGeneration() {
        IReplStageHistory<EvalClassWithInstanceAndLoader> history = getHistory();
        history.getClass();
        return ((BasicReplStageHistory) history).getCurrentGeneration().get();
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.IReplStageState
    public IReplStageHistory<EvalClassWithInstanceAndLoader> getHistory() {
        return this.history;
    }

    @Override // org.jetbrains.kotlin.cli.common.repl.IReplStageState
    public ReentrantReadWriteLock getLock() {
        return this.lock;
    }

    public final ReplClassLoader getTopClassLoader() {
        return this.topClassLoader;
    }

    public /* synthetic */ GenericReplEvaluatorState(Iterable iterable, ClassLoader classLoader, ReentrantReadWriteLock reentrantReadWriteLock, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(iterable, classLoader, (i & 4) != 0 ? new ReentrantReadWriteLock() : reentrantReadWriteLock);
    }
}
