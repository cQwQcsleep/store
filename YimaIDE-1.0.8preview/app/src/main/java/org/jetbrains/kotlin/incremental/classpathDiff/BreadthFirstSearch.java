package org.jetbrains.kotlin.incremental.classpathDiff;

import java.util.ArrayDeque;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0010\u001c\n\u0000\n\u0002\u0018\u0002\n\u0000\bÀ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J:\u0010\u0004\u001a\b\u0012\u0004\u0012\u0002H\u00060\u0005\"\u0004\b\u0000\u0010\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u0002H\u00060\b2\u0018\u0010\t\u001a\u0014\u0012\u0004\u0012\u0002H\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u0002H\u00060\b0\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/incremental/classpathDiff/BreadthFirstSearch;", "", "<init>", "()V", "findReachableNodes", "", "T", "nodes", "", "edgesProvider", "Lkotlin/Function1;", "org.jetbrains.kotlin:incremental-compilation-impl"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class BreadthFirstSearch {
    public static final BreadthFirstSearch INSTANCE = new BreadthFirstSearch();

    private BreadthFirstSearch() {
    }

    public final <T> Set<T> findReachableNodes(Iterable<? extends T> nodes, Function1<? super T, ? extends Iterable<? extends T>> edgesProvider) {
        nodes.getClass();
        edgesProvider.getClass();
        Set<T> mutableSet = CollectionsKt.toMutableSet(nodes);
        ArrayDeque arrayDeque = new ArrayDeque(CollectionsKt.toSet(nodes));
        while (!arrayDeque.isEmpty()) {
            List listMinus = CollectionsKt.minus((Iterable) edgesProvider.invoke(arrayDeque.removeFirst()), mutableSet);
            mutableSet.addAll(listMinus);
            arrayDeque.addAll(listMinus);
        }
        return mutableSet;
    }
}
