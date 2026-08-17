package org.jetbrains.kotlin.backend.wasm.utils;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.sequences.Sequence;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010#\n\u0000\n\u0002\u0010!\n\u0000\n\u0002\u0010%\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\u0018\u0000*\u0004\b\u0000\u0010\u00012\u00020\u0002B!\u0012\u0018\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u0013\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u0000¢\u0006\u0002\u0010\u0013J\u0012\u0010\u0014\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\r0\rJ#\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00028\u00002\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00028\u00000\rH\u0002¢\u0006\u0002\u0010\u0017R#\u0010\u0003\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u00050\u0004¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00028\u00000\rX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00028\u0000\u0012\n\u0012\b\u0012\u0004\u0012\u00028\u00000\u000b0\u000fX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0018"}, d2 = {"Lorg/jetbrains/kotlin/backend/wasm/utils/StronglyConnectedComponents;", "T", "", "enumerateOutgoingEdges", "Lkotlin/Function1;", "Lkotlin/sequences/Sequence;", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Lkotlin/jvm/functions/Function1;)V", "getEnumerateOutgoingEdges", "()Lkotlin/jvm/functions/Function1;", "visited", "", "stack", "", "reversedGraph", "", "visit", "", "edge", "(Ljava/lang/Object;)V", "findComponents", "visitTransposedEdge", "component", "(Ljava/lang/Object;Ljava/util/List;)V", "org.jetbrains.kotlin:backend.wasm"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class StronglyConnectedComponents<T> {
    private final Function1<T, Sequence<T>> enumerateOutgoingEdges;
    private final Map<T, Set<T>> reversedGraph;
    private final List<T> stack;
    private final Set<T> visited;

    /* JADX WARN: Multi-variable type inference failed */
    public StronglyConnectedComponents(Function1<? super T, ? extends Sequence<? extends T>> function1) {
        function1.getClass();
        this.enumerateOutgoingEdges = function1;
        this.visited = new LinkedHashSet();
        this.stack = new ArrayList();
        this.reversedGraph = new LinkedHashMap();
    }

    private final void visitTransposedEdge(T edge, List<T> component) {
        component.add(edge);
        Set<T> set = this.reversedGraph.get(edge);
        if (set != null) {
            for (T t : set) {
                if (this.visited.add(t)) {
                    visitTransposedEdge(t, component);
                }
            }
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<List<T>> findComponents() {
        this.visited.clear();
        ArrayList arrayList = new ArrayList();
        while (!this.stack.isEmpty()) {
            Object objPop = org.jetbrains.kotlin.backend.common.UtilsKt.pop(this.stack);
            if (this.visited.add((T) objPop)) {
                ArrayList arrayList2 = new ArrayList();
                visitTransposedEdge(objPop, arrayList2);
                arrayList.add(arrayList2);
            }
        }
        CollectionsKt.reverse(arrayList);
        return arrayList;
    }

    public final Function1<T, Sequence<T>> getEnumerateOutgoingEdges() {
        return this.enumerateOutgoingEdges;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void visit(T edge) {
        if (this.visited.add(edge)) {
            for (Object obj : (Sequence) this.enumerateOutgoingEdges.invoke(edge)) {
                Map<T, Set<T>> map = this.reversedGraph;
                Set<T> linkedHashSet = map.get(obj);
                if (linkedHashSet == null) {
                    linkedHashSet = new LinkedHashSet<>();
                    map.put((T) obj, linkedHashSet);
                }
                linkedHashSet.add(edge);
                visit(obj);
            }
            org.jetbrains.kotlin.backend.common.UtilsKt.push(this.stack, edge);
        }
    }
}
