package org.jetbrains.kotlin.cli.common.repl;

import java.io.Serializable;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0007\u0018\u0000 +*\u0004\b\u0000\u0010\u00012\u00020\u0002:\u0001+B7\u0012.\b\u0002\u0010\u0003\u001a(\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000\u0005j\b\u0012\u0004\u0012\u00028\u0000`\u00070\u0004j\b\u0012\u0004\u0012\u00028\u0000`\b¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\u000e\u001a\u00020\u000fJ\u0006\u0010\u0010\u001a\u00020\u000fJ\u001b\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00028\u0000¢\u0006\u0002\u0010\u0015J\u000e\u0010\u0016\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0006J,\u0010\u0017\u001a(\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00028\u00000\u0005j\b\u0012\u0004\u0012\u00028\u0000`\u00190\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u001aJ4\u0010\u001b\u001a(\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00028\u00000\u0005j\b\u0012\u0004\u0012\u00028\u0000`\u00190\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u001a2\u0006\u0010\u001c\u001a\u00020\u001dJ4\u0010\u001b\u001a(\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0018\u0012\u0004\u0012\u00028\u00000\u0005j\b\u0012\u0004\u0012\u00028\u0000`\u00190\u0004j\b\u0012\u0004\u0012\u00028\u0000`\u001a2\u0006\u0010\u0013\u001a\u00020\u0018J4\u0010\u001b\u001a(\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000\u0005j\b\u0012\u0004\u0012\u00028\u0000`\u00070\u0004j\b\u0012\u0004\u0012\u00028\u0000`\b2\u0006\u0010\u0013\u001a\u00020\u0006J\u000e\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0018J\u000e\u0010\u001e\u001a\u00020\u000f2\u0006\u0010\u0013\u001a\u00020\u0006J \u0010\u001f\u001a\u001c\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u0005j\n\u0012\u0004\u0012\u00028\u0000\u0018\u0001`\u0007J\b\u0010 \u001a\u0004\u0018\u00010\u0006J\r\u0010!\u001a\u0004\u0018\u00018\u0000¢\u0006\u0002\u0010\"J\u001c\u0010#\u001a\u00020\u000f2\u0014\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0004j\u0004\u0018\u0001`%J#\u0010&\u001a\u0004\u0018\u00010\u001d2\u0014\u0010$\u001a\u0010\u0012\u0004\u0012\u00020\u0018\u0018\u00010\u0004j\u0004\u0018\u0001`%¢\u0006\u0002\u0010'J\u0010\u0010(\u001a\f\u0012\u0004\u0012\u00020\u00180\u0004j\u0002`%J\f\u0010)\u001a\b\u0012\u0004\u0012\u00028\u00000\u0004J,\u0010*\u001a(\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000\u0005j\b\u0012\u0004\u0012\u00028\u0000`\u00070\u0004j\b\u0012\u0004\u0012\u00028\u0000`\bR4\u0010\u000b\u001a(\u0012\u001a\u0012\u0018\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00028\u00000\u0005j\b\u0012\u0004\u0012\u00028\u0000`\u00070\fj\b\u0012\u0004\u0012\u00028\u0000`\rX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006,"}, d2 = {"Lorg/jetbrains/kotlin/cli/common/repl/ReplHistory;", "T", "Ljava/io/Serializable;", "startingHistory", Argument.Delimiters.none, "Lkotlin/Pair;", "Lorg/jetbrains/kotlin/cli/common/repl/CompiledReplCodeLine;", "Lorg/jetbrains/kotlin/cli/common/repl/CompiledHistoryItem;", "Lorg/jetbrains/kotlin/cli/common/repl/CompiledHistoryList;", "<init>", "(Ljava/util/List;)V", "history", "Ljava/util/ArrayDeque;", "Lorg/jetbrains/kotlin/cli/common/repl/CompiledHistoryStorage;", "isEmpty", Argument.Delimiters.none, "isNotEmpty", "add", Argument.Delimiters.none, "line", "value", "(Lorg/jetbrains/kotlin/cli/common/repl/CompiledReplCodeLine;Ljava/lang/Object;)V", "removeLast", "reset", "Lorg/jetbrains/kotlin/cli/common/repl/ReplCodeLine;", "Lorg/jetbrains/kotlin/cli/common/repl/SourceHistoryItem;", "Lorg/jetbrains/kotlin/cli/common/repl/SourceHistoryList;", "resetToLine", "lineNumber", Argument.Delimiters.none, "contains", "lastItem", "lastCodeLine", "lastValue", "()Ljava/lang/Object;", "checkHistoryIsInSync", "compareHistory", "Lorg/jetbrains/kotlin/cli/common/repl/SourceList;", "firstMismatchingHistory", "(Ljava/util/List;)Ljava/lang/Integer;", "copySources", "copyValues", "copyAll", "Companion", "org.jetbrains.kotlin:cli-base"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.LIST_ITEM, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ReplHistory<T> implements Serializable {
    private static final long serialVersionUID = 8328353000L;
    private final ArrayDeque<Pair<CompiledReplCodeLine, T>> history;

    public ReplHistory(List<? extends Pair<CompiledReplCodeLine, ? extends T>> list) {
        list.getClass();
        this.history = new ArrayDeque<>(list);
    }

    public final void add(CompiledReplCodeLine line, T value) {
        line.getClass();
        this.history.add(TuplesKt.to(line, value));
    }

    public final boolean checkHistoryIsInSync(List<ReplCodeLine> compareHistory) {
        return firstMismatchingHistory(compareHistory) == null;
    }

    public final boolean contains(ReplCodeLine line) {
        line.getClass();
        ArrayDeque<Pair<CompiledReplCodeLine, T>> arrayDeque = this.history;
        if (arrayDeque != null && arrayDeque.isEmpty()) {
            return false;
        }
        Iterator<T> it = arrayDeque.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(((CompiledReplCodeLine) ((Pair) it.next()).getFirst()).getSource(), line)) {
                return true;
            }
        }
        return false;
    }

    public final List<Pair<CompiledReplCodeLine, T>> copyAll() {
        return CollectionsKt.toList(this.history);
    }

    public final List<ReplCodeLine> copySources() {
        ArrayDeque<Pair<CompiledReplCodeLine, T>> arrayDeque = this.history;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayDeque, 10));
        Iterator<T> it = arrayDeque.iterator();
        while (it.hasNext()) {
            arrayList.add(((CompiledReplCodeLine) ((Pair) it.next()).getFirst()).getSource());
        }
        return arrayList;
    }

    public final List<T> copyValues() {
        ArrayDeque<Pair<CompiledReplCodeLine, T>> arrayDeque = this.history;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayDeque, 10));
        Iterator<T> it = arrayDeque.iterator();
        while (it.hasNext()) {
            arrayList.add(((Pair) it.next()).getSecond());
        }
        return arrayList;
    }

    public final Integer firstMismatchingHistory(List<ReplCodeLine> compareHistory) {
        T next;
        ReplCodeLine replCodeLine;
        Pair pair;
        if (compareHistory == null) {
            return null;
        }
        if (compareHistory.size() != this.history.size()) {
            int size = compareHistory.size();
            int size2 = this.history.size();
            ArrayDeque<Pair<CompiledReplCodeLine, T>> arrayDeque = this.history;
            return size > size2 ? Integer.valueOf(compareHistory.get(arrayDeque.size()).getNo()) : Integer.valueOf(((CompiledReplCodeLine) ((Pair) CollectionsKt.toList(arrayDeque).get(compareHistory.size())).getFirst()).getSource().getNo());
        }
        Iterator<T> it = CollectionsKt.zip(this.history, compareHistory).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            pair = (Pair) next;
        } while (Intrinsics.areEqual(((CompiledReplCodeLine) ((Pair) pair.getFirst()).getFirst()).getSource(), pair.getSecond()));
        Pair pair2 = (Pair) next;
        if (pair2 == null || (replCodeLine = (ReplCodeLine) pair2.getSecond()) == null) {
            return null;
        }
        return Integer.valueOf(replCodeLine.getNo());
    }

    public final boolean isEmpty() {
        return this.history.isEmpty();
    }

    public final boolean isNotEmpty() {
        return !this.history.isEmpty();
    }

    public final CompiledReplCodeLine lastCodeLine() {
        Pair<CompiledReplCodeLine, T> pairLastItem = lastItem();
        if (pairLastItem != null) {
            return (CompiledReplCodeLine) pairLastItem.getFirst();
        }
        return null;
    }

    public final Pair<CompiledReplCodeLine, T> lastItem() {
        return this.history.peekLast();
    }

    public final T lastValue() {
        Pair<CompiledReplCodeLine, T> pairLastItem = lastItem();
        if (pairLastItem != null) {
            return (T) pairLastItem.getSecond();
        }
        return null;
    }

    public final boolean removeLast(CompiledReplCodeLine line) {
        line.getClass();
        if (!Intrinsics.areEqual(this.history.peekLast().getFirst(), line)) {
            return false;
        }
        this.history.removeLast();
        return true;
    }

    public final List<Pair<ReplCodeLine, T>> reset() {
        ArrayDeque<Pair<CompiledReplCodeLine, T>> arrayDeque = this.history;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(arrayDeque, 10));
        Iterator<T> it = arrayDeque.iterator();
        while (it.hasNext()) {
            Pair pair = (Pair) it.next();
            arrayList.add(new Pair(((CompiledReplCodeLine) pair.getFirst()).getSource(), pair.getSecond()));
        }
        this.history.clear();
        return arrayList;
    }

    public final List<Pair<ReplCodeLine, T>> resetToLine(int lineNumber) {
        CompiledReplCodeLine compiledReplCodeLine;
        ReplCodeLine source;
        ArrayList arrayList = new ArrayList();
        while (true) {
            Pair<CompiledReplCodeLine, T> pairPeekLast = this.history.peekLast();
            if (((pairPeekLast == null || (compiledReplCodeLine = (CompiledReplCodeLine) pairPeekLast.getFirst()) == null || (source = compiledReplCodeLine.getSource()) == null) ? -1 : source.getNo()) <= lineNumber) {
                return CollectionsKt.reversed(arrayList);
            }
            Pair<CompiledReplCodeLine, T> pairRemoveLast = this.history.removeLast();
            arrayList.add(new Pair(((CompiledReplCodeLine) pairRemoveLast.getFirst()).getSource(), pairRemoveLast.getSecond()));
        }
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public ReplHistory() {
        List list = null;
        this(list, 1, list);
    }

    public /* synthetic */ ReplHistory(List list, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? CollectionsKt.emptyList() : list);
    }

    public final boolean contains(CompiledReplCodeLine line) {
        line.getClass();
        ArrayDeque<Pair<CompiledReplCodeLine, T>> arrayDeque = this.history;
        if (arrayDeque != null && arrayDeque.isEmpty()) {
            return false;
        }
        Iterator<T> it = arrayDeque.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(((Pair) it.next()).getFirst(), line)) {
                return true;
            }
        }
        return false;
    }

    public final List<Pair<ReplCodeLine, T>> resetToLine(ReplCodeLine line) {
        line.getClass();
        return resetToLine(line.getNo());
    }

    public final List<Pair<CompiledReplCodeLine, T>> resetToLine(CompiledReplCodeLine line) {
        CompiledReplCodeLine compiledReplCodeLine;
        ReplCodeLine source;
        line.getClass();
        ArrayList arrayList = new ArrayList();
        while (true) {
            Pair<CompiledReplCodeLine, T> pairPeekLast = this.history.peekLast();
            if (((pairPeekLast == null || (compiledReplCodeLine = (CompiledReplCodeLine) pairPeekLast.getFirst()) == null || (source = compiledReplCodeLine.getSource()) == null) ? -1 : source.getNo()) > line.getSource().getNo()) {
                arrayList.add(this.history.removeLast());
            } else {
                return CollectionsKt.reversed(arrayList);
            }
        }
    }
}
