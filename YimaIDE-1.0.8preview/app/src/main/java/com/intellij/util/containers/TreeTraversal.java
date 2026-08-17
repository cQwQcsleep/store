package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.Comparing;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Conditions;
import com.intellij.util.Function;
import com.intellij.util.NotNullizer;
import defpackage.kpe;
import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class TreeTraversal {
    private final String debugName;
    private static final NotNullizer ourNotNullizer = new NotNullizer("TreeTraversal.NotNull");
    public static final TreeTraversal BI_ORDER_DFS = new TreeTraversal("BI_ORDER_DFS") { // from class: com.intellij.util.containers.TreeTraversal.6
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "roots";
            } else {
                objArr[0] = "tree";
            }
            objArr[1] = "com/intellij/util/containers/TreeTraversal$6";
            objArr[2] = "createIterator";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // com.intellij.util.containers.TreeTraversal
        public <T> It<T> createIterator(Iterable<? extends T> iterable, Function<? super T, ? extends Iterable<? extends T>> function) {
            if (iterable == null) {
                $$$reportNull$$$0(0);
            }
            if (function == null) {
                $$$reportNull$$$0(1);
            }
            return new BiOrderIt(iterable, function, BiOrderIt.Order.BOTH);
        }
    };
    public static final TreeTraversal PRE_ORDER_DFS = new TreeTraversal("PRE_ORDER_DFS") { // from class: com.intellij.util.containers.TreeTraversal.7
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "roots";
            } else {
                objArr[0] = "tree";
            }
            objArr[1] = "com/intellij/util/containers/TreeTraversal$7";
            objArr[2] = "createIterator";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // com.intellij.util.containers.TreeTraversal
        public <T> It<T> createIterator(Iterable<? extends T> iterable, Function<? super T, ? extends Iterable<? extends T>> function) {
            if (iterable == null) {
                $$$reportNull$$$0(0);
            }
            if (function == null) {
                $$$reportNull$$$0(1);
            }
            return new BiOrderIt(iterable, function, BiOrderIt.Order.PRE);
        }
    };
    public static final TreeTraversal POST_ORDER_DFS = new TreeTraversal("POST_ORDER_DFS") { // from class: com.intellij.util.containers.TreeTraversal.8
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "roots";
            } else {
                objArr[0] = "tree";
            }
            objArr[1] = "com/intellij/util/containers/TreeTraversal$8";
            objArr[2] = "createIterator";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // com.intellij.util.containers.TreeTraversal
        public <T> It<T> createIterator(Iterable<? extends T> iterable, Function<? super T, ? extends Iterable<? extends T>> function) {
            if (iterable == null) {
                $$$reportNull$$$0(0);
            }
            if (function == null) {
                $$$reportNull$$$0(1);
            }
            return new BiOrderIt(iterable, function, BiOrderIt.Order.POST);
        }
    };
    public static final TreeTraversal LEAVES_DFS = new TreeTraversal("LEAVES_DFS") { // from class: com.intellij.util.containers.TreeTraversal.9
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "roots";
            } else {
                objArr[0] = "tree";
            }
            objArr[1] = "com/intellij/util/containers/TreeTraversal$9";
            objArr[2] = "createIterator";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // com.intellij.util.containers.TreeTraversal
        public <T> It<T> createIterator(Iterable<? extends T> iterable, Function<? super T, ? extends Iterable<? extends T>> function) {
            if (iterable == null) {
                $$$reportNull$$$0(0);
            }
            if (function == null) {
                $$$reportNull$$$0(1);
            }
            return new LeavesDfsIt(iterable, function);
        }
    };
    public static final TreeTraversal INTERLEAVED_DFS = new TreeTraversal("INTERLEAVED_DFS") { // from class: com.intellij.util.containers.TreeTraversal.10
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "roots";
            } else {
                objArr[0] = "tree";
            }
            objArr[1] = "com/intellij/util/containers/TreeTraversal$10";
            objArr[2] = "createIterator";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // com.intellij.util.containers.TreeTraversal
        public <T> It<T> createIterator(Iterable<? extends T> iterable, Function<? super T, ? extends Iterable<? extends T>> function) {
            if (iterable == null) {
                $$$reportNull$$$0(0);
            }
            if (function == null) {
                $$$reportNull$$$0(1);
            }
            return new InterleavedIt(iterable, function);
        }
    };
    public static final TreeTraversal PLAIN_BFS = new TreeTraversal("PLAIN_BFS") { // from class: com.intellij.util.containers.TreeTraversal.11
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "roots";
            } else {
                objArr[0] = "tree";
            }
            objArr[1] = "com/intellij/util/containers/TreeTraversal$11";
            objArr[2] = "createIterator";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // com.intellij.util.containers.TreeTraversal
        public <T> It<T> createIterator(Iterable<? extends T> iterable, Function<? super T, ? extends Iterable<? extends T>> function) {
            if (iterable == null) {
                $$$reportNull$$$0(0);
            }
            if (function == null) {
                $$$reportNull$$$0(1);
            }
            return new PlainBfsIt(iterable, function);
        }
    };
    public static final TreeTraversal TRACING_BFS = new TreeTraversal("TRACING_BFS") { // from class: com.intellij.util.containers.TreeTraversal.12
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "roots";
            } else {
                objArr[0] = "tree";
            }
            objArr[1] = "com/intellij/util/containers/TreeTraversal$12";
            objArr[2] = "createIterator";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // com.intellij.util.containers.TreeTraversal
        public <T> It<T> createIterator(Iterable<? extends T> iterable, Function<? super T, ? extends Iterable<? extends T>> function) {
            if (iterable == null) {
                $$$reportNull$$$0(0);
            }
            if (function == null) {
                $$$reportNull$$$0(1);
            }
            return new TracingBfsIt(iterable, function);
        }
    };
    public static final TreeTraversal LEAVES_BFS = new TreeTraversal("LEAVES_BFS") { // from class: com.intellij.util.containers.TreeTraversal.13
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "roots";
            } else {
                objArr[0] = "tree";
            }
            objArr[1] = "com/intellij/util/containers/TreeTraversal$13";
            objArr[2] = "createIterator";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        @Override // com.intellij.util.containers.TreeTraversal
        public <T> It<T> createIterator(Iterable<? extends T> iterable, Function<? super T, ? extends Iterable<? extends T>> function) {
            if (iterable == null) {
                $$$reportNull$$$0(0);
            }
            if (function == null) {
                $$$reportNull$$$0(1);
            }
            return new LeavesBfsIt(iterable, function);
        }
    };

    public static final class BiOrderIt<T> extends DfsIt<T, P1<T>> {
        private boolean curDescending;
        private boolean descending;
        private final Order order;

        public enum Order {
            PRE,
            POST,
            BOTH
        }

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 3 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 3 ? 3 : 2];
            if (i == 1) {
                objArr[0] = "tree";
            } else if (i == 2) {
                objArr[0] = "order";
            } else if (i != 3) {
                objArr[0] = "roots";
            } else {
                objArr[0] = "com/intellij/util/containers/TreeTraversal$BiOrderIt";
            }
            if (i != 3) {
                objArr[1] = "com/intellij/util/containers/TreeTraversal$BiOrderIt";
            } else {
                objArr[1] = "backtrace";
            }
            if (i != 3) {
                objArr[2] = "<init>";
            }
            String str2 = String.format(str, objArr);
            if (i == 3) {
                throw new IllegalStateException(str2);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public BiOrderIt(Iterable<? extends T> iterable, Function<? super T, ? extends Iterable<? extends T>> function, Order order) {
            super(function);
            if (iterable == null) {
                $$$reportNull$$$0(0);
            }
            if (function == null) {
                $$$reportNull$$$0(1);
            }
            if (order == null) {
                $$$reportNull$$$0(2);
            }
            this.descending = true;
            this.order = order;
            this.last = P1.create((Iterable) iterable);
        }

        @Override // com.intellij.util.containers.TreeTraversal.DfsIt, com.intellij.util.containers.JBIterator
        public void currentChanged() {
            super.currentChanged();
            this.curDescending = this.descending;
        }

        @Override // com.intellij.util.containers.JBIterator
        public T nextImpl() {
            while (true) {
                H h = this.last;
                if (h == 0) {
                    this.descending = true;
                    return stop();
                }
                Iterator<? extends T> it = ((P1) h).iterator(this.tree);
                if (it.hasNext()) {
                    T next = it.next();
                    this.last = ((P1) this.last).add(P1.create(next));
                    this.descending = true;
                    if (this.order != Order.POST) {
                        return next;
                    }
                } else {
                    H h2 = this.last;
                    T t = ((P1) h2).node;
                    P1<T> p1Remove = ((P1) h2).remove();
                    this.last = p1Remove;
                    this.descending = false;
                    if (this.order != Order.PRE && p1Remove != null) {
                        return t;
                    }
                }
            }
        }
    }

    public static abstract class DfsIt<T, H extends P<T, H>> extends TracingIt<T> {
        H cur;
        H last;

        public DfsIt(Function<? super T, ? extends Iterable<? extends T>> function) {
            super(function);
        }

        @Override // com.intellij.util.containers.JBIterator
        public void currentChanged() {
            this.cur = this.last;
        }
    }

    public static abstract class GuidedIt<T> extends It<T> {
        public T curChild;
        public Iterable<? extends T> curChildren;
        public boolean curNoChildren;
        public T curParent;

        public interface Guide<T> {
            void guide(GuidedIt<T> guidedIt);
        }

        public GuidedIt(Function<? super T, ? extends Iterable<? extends T>> function) {
            super(function);
        }

        public abstract GuidedIt<T> queueNext(T t);

        public abstract GuidedIt<T> result(T t);
    }

    public static final class GuidedItImpl<T> extends GuidedIt<T> {
        T curResult;
        P1<T> first;
        final GuidedIt.Guide<T> guide;
        P1<T> last;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i == 1) {
                objArr[0] = "tree";
            } else if (i != 2) {
                objArr[0] = "roots";
            } else {
                objArr[0] = "guide";
            }
            objArr[1] = "com/intellij/util/containers/TreeTraversal$GuidedItImpl";
            objArr[2] = "<init>";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public GuidedItImpl(Iterable<? extends T> iterable, Function<? super T, ? extends Iterable<? extends T>> function, GuidedIt.Guide<T> guide) {
            super(function);
            if (iterable == null) {
                $$$reportNull$$$0(0);
            }
            if (function == null) {
                $$$reportNull$$$0(1);
            }
            if (guide == null) {
                $$$reportNull$$$0(2);
            }
            P1<T> p1Create = P1.create((Iterable) iterable);
            this.last = p1Create;
            this.first = p1Create;
            this.guide = guide;
        }

        @Override // com.intellij.util.containers.JBIterator
        public T nextImpl() {
            T t;
            if (this.guide == null) {
                return stop();
            }
            do {
                P1<T> p1 = this.last;
                if (p1 == null) {
                    return stop();
                }
                Iterator<? extends T> it = p1.iterator(this.tree);
                boolean zHasNext = it.hasNext();
                this.curResult = null;
                if (p1.node != null || zHasNext) {
                    this.curChild = zHasNext ? it.next() : null;
                    this.curParent = p1.node;
                    this.curChildren = p1.itle;
                    this.curNoChildren = p1.empty;
                    this.guide.guide(this);
                }
                if (!zHasNext) {
                    this.last = this.last.remove();
                }
                t = this.curResult;
            } while (t == null);
            return t;
        }

        @Override // com.intellij.util.containers.TreeTraversal.GuidedIt
        public GuidedIt<T> queueNext(T t) {
            if (t != null) {
                this.last = this.last.add(P1.create(t));
            }
            return this;
        }

        @Override // com.intellij.util.containers.TreeTraversal.GuidedIt
        public GuidedIt<T> result(T t) {
            this.curResult = t;
            return this;
        }
    }

    public static final class Intercepted extends TreeTraversal {
        final TraversalInterceptor interceptor;
        final TreeTraversal original;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 5 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 5 ? 3 : 2];
            if (i == 1) {
                objArr[0] = "original";
            } else if (i == 2) {
                objArr[0] = "interceptor";
            } else if (i == 3) {
                objArr[0] = "roots";
            } else if (i == 4) {
                objArr[0] = "tree";
            } else if (i != 5) {
                objArr[0] = "debugName";
            } else {
                objArr[0] = "com/intellij/util/containers/TreeTraversal$Intercepted";
            }
            if (i != 5) {
                objArr[1] = "com/intellij/util/containers/TreeTraversal$Intercepted";
            } else {
                objArr[1] = "createIterator";
            }
            if (i == 3 || i == 4) {
                objArr[2] = "createIterator";
            } else if (i != 5) {
                objArr[2] = "<init>";
            }
            String str2 = String.format(str, objArr);
            if (i == 5) {
                throw new IllegalStateException(str2);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        private Intercepted(String str, TreeTraversal treeTraversal, TraversalInterceptor traversalInterceptor) {
            super(str);
            if (str == null) {
                $$$reportNull$$$0(0);
            }
            if (treeTraversal == null) {
                $$$reportNull$$$0(1);
            }
            if (traversalInterceptor == null) {
                $$$reportNull$$$0(2);
            }
            this.original = treeTraversal;
            this.interceptor = traversalInterceptor;
        }

        @Override // com.intellij.util.containers.TreeTraversal
        public <T> It<T> createIterator(Iterable<? extends T> iterable, Function<? super T, ? extends Iterable<? extends T>> function) {
            if (iterable == null) {
                $$$reportNull$$$0(3);
            }
            if (function == null) {
                $$$reportNull$$$0(4);
            }
            TraversalArgs<T> traversalArgsIntercept = this.interceptor.intercept(new TraversalArgs<>(iterable, function));
            It<T> itCreateIterator = this.original.createIterator(traversalArgsIntercept.roots, traversalArgsIntercept.tree);
            if (itCreateIterator == null) {
                $$$reportNull$$$0(5);
            }
            return itCreateIterator;
        }
    }

    public static final class InterleavedIt<T> extends DfsIt<T, P2<T>> {
        P2<T> cur;
        P2<T> max;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "roots", "com/intellij/util/containers/TreeTraversal$InterleavedIt", "<init>"));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public InterleavedIt(Iterable<? extends T> iterable, Function<? super T, ? extends Iterable<? extends T>> function) {
            super(function);
            if (iterable == null) {
                $$$reportNull$$$0(0);
            }
            P2<T> p2Create = P2.create((Iterable) iterable);
            this.last = p2Create;
            this.max = p2Create;
            this.cur = p2Create;
        }

        @Override // com.intellij.util.containers.JBIterator
        public T nextImpl() {
            while (this.last != 0) {
                if (this.cur == null) {
                    P2<T> p2 = this.max;
                    this.cur = p2;
                    this.max = p2.next;
                }
                Iterator<? extends T> it = this.cur.iterator(this.tree);
                if (it.hasNext()) {
                    T next = it.next();
                    P2<T> p2Add = ((P2) this.last).add(P2.create(next));
                    this.last = p2Add;
                    P2<T> p3 = this.cur;
                    p2Add.parent = p3;
                    this.cur = p3.prev;
                    if (this.max == null) {
                        this.max = p2Add;
                    }
                    return next;
                }
                P2<T> p4 = this.cur;
                if (p4 == this.last) {
                    this.last = p4.prev;
                }
                this.cur = p4.remove();
            }
            return stop();
        }
    }

    public static abstract class It<T> extends JBIterator<T> {
        protected final Function<? super T, ? extends Iterable<? extends T>> tree;

        public It(Function<? super T, ? extends Iterable<? extends T>> function) {
            this.tree = function;
        }
    }

    public static final class LeavesBfsIt<T> extends TracingIt<T> {
        final ArrayDeque<T> queue;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "roots", "com/intellij/util/containers/TreeTraversal$LeavesBfsIt", "<init>"));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LeavesBfsIt(Iterable<? extends T> iterable, Function<? super T, ? extends Iterable<? extends T>> function) {
            super(function);
            if (iterable == null) {
                $$$reportNull$$$0(0);
            }
            ArrayDeque<T> arrayDeque = new ArrayDeque<>();
            this.queue = arrayDeque;
            JBIterable jBIterableFrom = JBIterable.from(iterable);
            NotNullizer notNullizer = TreeTraversal.ourNotNullizer;
            Objects.requireNonNull(notNullizer);
            jBIterableFrom.map(new kpe(notNullizer)).addAllTo(arrayDeque);
        }

        @Override // com.intellij.util.containers.JBIterator
        public T nextImpl() {
            while (!this.queue.isEmpty()) {
                T t = (Object) TreeTraversal.ourNotNullizer.nullize(this.queue.remove());
                Iterable<? extends T> iterableFun = this.tree.fun(t);
                Iterator<? extends T> it = iterableFun == null ? null : iterableFun.iterator();
                if (it == null || !it.hasNext()) {
                    return t;
                }
                while (it.hasNext()) {
                    this.queue.add((T) TreeTraversal.ourNotNullizer.notNullize(it.next()));
                }
            }
            return stop();
        }
    }

    public static final class LeavesDfsIt<T> extends DfsIt<T, P1<T>> {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "roots", "com/intellij/util/containers/TreeTraversal$LeavesDfsIt", "<init>"));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public LeavesDfsIt(Iterable<? extends T> iterable, Function<? super T, ? extends Iterable<? extends T>> function) {
            super(function);
            if (iterable == null) {
                $$$reportNull$$$0(0);
            }
            this.last = P1.create((Iterable) iterable);
        }

        @Override // com.intellij.util.containers.JBIterator
        public T nextImpl() {
            while (true) {
                H h = this.last;
                if (h == 0) {
                    return stop();
                }
                P1 p1 = (P1) h;
                if (!p1.iterator(this.tree).hasNext() || p1.empty) {
                    P1<T> p1Remove = ((P1) this.last).remove();
                    this.last = p1Remove;
                    if (p1.empty) {
                        return p1Remove == null ? stop() : p1.node;
                    }
                } else {
                    this.last = ((P1) this.last).add(P1.create(p1.iterator(this.tree).next()));
                }
            }
        }
    }

    public static final class PlainBfsIt<T> extends It<T> {
        final ArrayDeque<T> queue;
        P1<T> top;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "roots", "com/intellij/util/containers/TreeTraversal$PlainBfsIt", "<init>"));
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public PlainBfsIt(Iterable<? extends T> iterable, Function<? super T, ? extends Iterable<? extends T>> function) {
            super(function);
            if (iterable == null) {
                $$$reportNull$$$0(0);
            }
            ArrayDeque<T> arrayDeque = new ArrayDeque<>();
            this.queue = arrayDeque;
            JBIterable jBIterableFrom = JBIterable.from(iterable);
            NotNullizer notNullizer = TreeTraversal.ourNotNullizer;
            Objects.requireNonNull(notNullizer);
            jBIterableFrom.map(new kpe(notNullizer)).addAllTo(arrayDeque);
        }

        @Override // com.intellij.util.containers.JBIterator
        public T nextImpl() {
            P1<T> p1 = this.top;
            if (p1 != null) {
                JBIterable jBIterableFrom = JBIterable.from(p1.iterable(this.tree));
                NotNullizer notNullizer = TreeTraversal.ourNotNullizer;
                Objects.requireNonNull(notNullizer);
                jBIterableFrom.map(new kpe(notNullizer)).addAllTo(this.queue);
                this.top = null;
            }
            if (this.queue.isEmpty()) {
                return stop();
            }
            P1<T> p1Create = P1.create(TreeTraversal.ourNotNullizer.nullize(this.queue.remove()));
            this.top = p1Create;
            return p1Create.node;
        }
    }

    public static final class TracingBfsIt<T> extends TracingIt<T> {
        P1<T> cur;
        final Map<T, T> paths;
        final ArrayDeque<T> queue;
        P1<T> top;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 1 ? 3 : 2];
            if (i != 1) {
                objArr[0] = "roots";
            } else {
                objArr[0] = "com/intellij/util/containers/TreeTraversal$TracingBfsIt";
            }
            if (i != 1) {
                objArr[1] = "com/intellij/util/containers/TreeTraversal$TracingBfsIt";
            } else {
                objArr[1] = "backtrace";
            }
            if (i != 1) {
                objArr[2] = "<init>";
            }
            String str2 = String.format(str, objArr);
            if (i == 1) {
                throw new IllegalStateException(str2);
            }
        }

        /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
        public TracingBfsIt(Iterable<? extends T> iterable, Function<? super T, ? extends Iterable<? extends T>> function) {
            super(function);
            if (iterable == null) {
                $$$reportNull$$$0(0);
            }
            ArrayDeque<T> arrayDeque = new ArrayDeque<>();
            this.queue = arrayDeque;
            this.paths = new IdentityHashMap();
            JBIterable jBIterableFrom = JBIterable.from(iterable);
            NotNullizer notNullizer = TreeTraversal.ourNotNullizer;
            Objects.requireNonNull(notNullizer);
            jBIterableFrom.map(new kpe(notNullizer)).addAllTo(arrayDeque);
        }

        @Override // com.intellij.util.containers.JBIterator
        public void currentChanged() {
            this.cur = this.top;
        }

        @Override // com.intellij.util.containers.JBIterator
        public T nextImpl() {
            P1<T> p1 = this.top;
            if (p1 != null) {
                for (T t : p1.iterable(this.tree)) {
                    if (!this.paths.containsKey(t)) {
                        this.queue.add((T) TreeTraversal.ourNotNullizer.notNullize(t));
                        this.paths.put(t, this.top.node);
                    }
                }
                this.top = null;
            }
            if (this.queue.isEmpty()) {
                return stop();
            }
            P1<T> p1Create = P1.create(TreeTraversal.ourNotNullizer.nullize(this.queue.remove()));
            this.top = p1Create;
            return p1Create.node;
        }
    }

    public static abstract class TracingIt<T> extends It<T> {
        public TracingIt(Function<? super T, ? extends Iterable<? extends T>> function) {
            super(function);
        }
    }

    public static final class TraversalArgs<T> {
        public final Iterable<? extends T> roots;
        public final Function<? super T, ? extends Iterable<? extends T>> tree;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "roots";
            } else {
                objArr[0] = "tree";
            }
            objArr[1] = "com/intellij/util/containers/TreeTraversal$TraversalArgs";
            objArr[2] = "<init>";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        public TraversalArgs(Iterable<? extends T> iterable, Function<? super T, ? extends Iterable<? extends T>> function) {
            if (iterable == null) {
                $$$reportNull$$$0(0);
            }
            if (function == null) {
                $$$reportNull$$$0(1);
            }
            this.roots = iterable;
            this.tree = function;
        }
    }

    public interface TraversalInterceptor {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i != 1) {
                objArr[0] = "interceptor1";
            } else {
                objArr[0] = "interceptor2";
            }
            objArr[1] = "com/intellij/util/containers/TreeTraversal$TraversalInterceptor";
            objArr[2] = "compose";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        static TraversalInterceptor compose(final TraversalInterceptor traversalInterceptor, TraversalInterceptor traversalInterceptor2) {
            if (traversalInterceptor == null) {
                $$$reportNull$$$0(0);
            }
            if (traversalInterceptor2 == null) {
                $$$reportNull$$$0(1);
            }
            return new TraversalInterceptor() { // from class: com.intellij.util.containers.TreeTraversal.TraversalInterceptor.1
                private static /* synthetic */ void $$$reportNull$$$0(int i) {
                    String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
                    Object[] objArr = new Object[i != 1 ? 3 : 2];
                    if (i != 1) {
                        objArr[0] = "args";
                    } else {
                        objArr[0] = "com/intellij/util/containers/TreeTraversal$TraversalInterceptor$1";
                    }
                    if (i != 1) {
                        objArr[1] = "com/intellij/util/containers/TreeTraversal$TraversalInterceptor$1";
                    } else {
                        objArr[1] = "intercept";
                    }
                    if (i != 1) {
                        objArr[2] = "intercept";
                    }
                    String str2 = String.format(str, objArr);
                    if (i == 1) {
                        throw new IllegalStateException(str2);
                    }
                }

                @Override // com.intellij.util.containers.TreeTraversal.TraversalInterceptor
                public <TT> TraversalArgs<TT> intercept(TraversalArgs<TT> traversalArgs) {
                    if (traversalArgs == null) {
                        $$$reportNull$$$0(0);
                    }
                    TraversalArgs<TT> traversalArgsIntercept = TraversalInterceptor.this.intercept(traversalInterceptor.intercept(traversalArgs));
                    if (traversalArgsIntercept == null) {
                        $$$reportNull$$$0(1);
                    }
                    return traversalArgsIntercept;
                }
            };
        }

        <T> TraversalArgs<T> intercept(TraversalArgs<T> traversalArgs);
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 5 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 5 ? 3 : 2];
        switch (i) {
            case 1:
                objArr[0] = "roots";
                break;
            case 2:
            case 3:
            case 4:
                objArr[0] = "tree";
                break;
            case 5:
                objArr[0] = "com/intellij/util/containers/TreeTraversal";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "identity";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "rangeCondition";
                break;
            case 8:
            default:
                objArr[0] = "debugName";
                break;
            case 9:
                objArr[0] = "interceptor";
                break;
            case 10:
                objArr[0] = "guide";
                break;
        }
        if (i != 5) {
            objArr[1] = "com/intellij/util/containers/TreeTraversal";
        } else {
            objArr[1] = "traversal";
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
                objArr[2] = "traversal";
                break;
            case 5:
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "unique";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "onRange";
                break;
            case 8:
            case 9:
                objArr[2] = "intercept";
                break;
            case 10:
                objArr[2] = "GUIDED_TRAVERSAL";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i == 5) {
            throw new IllegalStateException(str2);
        }
    }

    public TreeTraversal(String str) {
        if (str == null) {
            $$$reportNull$$$0(0);
        }
        this.debugName = str;
    }

    public static TreeTraversal GUIDED_TRAVERSAL(final GuidedIt.Guide<?> guide) {
        if (guide == null) {
            $$$reportNull$$$0(10);
        }
        return new TreeTraversal("GUIDED_TRAVERSAL") { // from class: com.intellij.util.containers.TreeTraversal.5
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                Object[] objArr = new Object[3];
                if (i != 1) {
                    objArr[0] = "roots";
                } else {
                    objArr[0] = "tree";
                }
                objArr[1] = "com/intellij/util/containers/TreeTraversal$5";
                objArr[2] = "createIterator";
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
            }

            @Override // com.intellij.util.containers.TreeTraversal
            public <T> It<T> createIterator(Iterable<? extends T> iterable, Function<? super T, ? extends Iterable<? extends T>> function) {
                if (iterable == null) {
                    $$$reportNull$$$0(0);
                }
                if (function == null) {
                    $$$reportNull$$$0(1);
                }
                return new GuidedItImpl(iterable, function, guide);
            }
        };
    }

    public final TreeTraversal cached(final Map<Object, Object> map) {
        return intercept("CACHED", new TraversalInterceptor() { // from class: com.intellij.util.containers.TreeTraversal.3

            /* JADX INFO: Add missing generic type declarations: [TT] */
            /* JADX INFO: renamed from: com.intellij.util.containers.TreeTraversal$3$1WrappedTree, reason: invalid class name */
            public final class C1WrappedTree<TT> implements Function<TT, Iterable<? extends TT>> {
                final /* synthetic */ Function val$tree;

                public C1WrappedTree(Function function) {
                    this.val$tree = function;
                }

                @Override // com.intellij.util.Function
                public Iterable<? extends TT> fun(TT tt) {
                    Map map = map;
                    final Function function = this.val$tree;
                    return (Iterable) map.computeIfAbsent(tt, new java.util.function.Function() { // from class: com.intellij.util.containers.w
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return JBIterable.from((Iterable) function.fun(obj)).collect();
                        }
                    });
                }
            }

            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
                Object[] objArr = new Object[i != 1 ? 3 : 2];
                if (i != 1) {
                    objArr[0] = "args";
                } else {
                    objArr[0] = "com/intellij/util/containers/TreeTraversal$3";
                }
                if (i != 1) {
                    objArr[1] = "com/intellij/util/containers/TreeTraversal$3";
                } else {
                    objArr[1] = "intercept";
                }
                if (i != 1) {
                    objArr[2] = "intercept";
                }
                String str2 = String.format(str, objArr);
                if (i == 1) {
                    throw new IllegalStateException(str2);
                }
            }

            @Override // com.intellij.util.containers.TreeTraversal.TraversalInterceptor
            public <TT> TraversalArgs<TT> intercept(TraversalArgs<TT> traversalArgs) {
                if (traversalArgs == null) {
                    $$$reportNull$$$0(0);
                }
                Function<? super TT, ? extends Iterable<? extends TT>> function = traversalArgs.tree;
                if (function instanceof C1WrappedTree) {
                    return traversalArgs;
                }
                return new TraversalArgs<>(traversalArgs.roots, new C1WrappedTree(function));
            }
        });
    }

    public abstract <T> It<T> createIterator(Iterable<? extends T> iterable, Function<? super T, ? extends Iterable<? extends T>> function);

    public final TreeTraversal intercept(String str, TraversalInterceptor traversalInterceptor) {
        if (str == null) {
            $$$reportNull$$$0(8);
        }
        if (traversalInterceptor == null) {
            $$$reportNull$$$0(9);
        }
        String str2 = str + "." + this.debugName;
        if (!(this instanceof Intercepted)) {
            return new Intercepted(str2, this, traversalInterceptor);
        }
        Intercepted intercepted = (Intercepted) this;
        return new Intercepted(str2, intercepted.original, TraversalInterceptor.compose(intercepted.interceptor, traversalInterceptor));
    }

    public final <T> TreeTraversal onRange(final Condition<? super T> condition) {
        if (condition == null) {
            $$$reportNull$$$0(7);
        }
        return intercept("ON_RANGE", new TraversalInterceptor() { // from class: com.intellij.util.containers.TreeTraversal.4

            /* JADX INFO: Add missing generic type declarations: [TT] */
            /* JADX INFO: renamed from: com.intellij.util.containers.TreeTraversal$4$1WrappedTree, reason: invalid class name */
            public final class C1WrappedTree<TT> implements Function<TT, Iterable<? extends TT>> {
                final Condition<? super T> inner;
                final /* synthetic */ Condition val$inRangeCondition;
                final /* synthetic */ Condition val$notInRangeCondition;
                final /* synthetic */ Function val$tree;

                public C1WrappedTree(Function function, Condition condition, Condition condition2) {
                    this.val$tree = function;
                    this.val$notInRangeCondition = condition;
                    this.val$inRangeCondition = condition2;
                    this.inner = condition;
                }

                @Override // com.intellij.util.Function
                public Iterable<? extends TT> fun(TT tt) {
                    return JBIterable.from((Iterable) this.val$tree.fun(tt)).skipWhile(this.val$notInRangeCondition).takeWhile(this.val$inRangeCondition);
                }
            }

            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
                Object[] objArr = new Object[i != 1 ? 3 : 2];
                if (i != 1) {
                    objArr[0] = "args";
                } else {
                    objArr[0] = "com/intellij/util/containers/TreeTraversal$4";
                }
                if (i != 1) {
                    objArr[1] = "com/intellij/util/containers/TreeTraversal$4";
                } else {
                    objArr[1] = "intercept";
                }
                if (i != 1) {
                    objArr[2] = "intercept";
                }
                String str2 = String.format(str, objArr);
                if (i == 1) {
                    throw new IllegalStateException(str2);
                }
            }

            @Override // com.intellij.util.containers.TreeTraversal.TraversalInterceptor
            public <TT> TraversalArgs<TT> intercept(TraversalArgs<TT> traversalArgs) {
                if (traversalArgs == null) {
                    $$$reportNull$$$0(0);
                }
                Function<? super TT, ? extends Iterable<? extends TT>> function = traversalArgs.tree;
                Condition condition2 = condition;
                Condition conditionNot = Conditions.not(condition2);
                if ((function instanceof C1WrappedTree) && Comparing.equal(condition, ((C1WrappedTree) function).inner)) {
                    return traversalArgs;
                }
                return new TraversalArgs<>(JBIterable.from(traversalArgs.roots).filter(condition2), new C1WrappedTree(function, conditionNot, condition2));
            }
        });
    }

    public final String toString() {
        return this.debugName;
    }

    public final <T> JBIterable<T> traversal(final Iterable<? extends T> iterable, final Function<? super T, ? extends Iterable<? extends T>> function) {
        if (iterable == null) {
            $$$reportNull$$$0(1);
        }
        if (function == null) {
            $$$reportNull$$$0(2);
        }
        return new JBIterable<T>() { // from class: com.intellij.util.containers.TreeTraversal.1
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/intellij/util/containers/TreeTraversal$1", "iterator"));
            }

            @Override // java.lang.Iterable
            public Iterator<T> iterator() {
                It<T> itCreateIterator = TreeTraversal.this.createIterator(iterable, function);
                if (itCreateIterator == null) {
                    $$$reportNull$$$0(0);
                }
                return itCreateIterator;
            }
        };
    }

    public final TreeTraversal unique(final Function<?, ?> function) {
        if (function == null) {
            $$$reportNull$$$0(6);
        }
        return intercept("UNIQUE", new TraversalInterceptor() { // from class: com.intellij.util.containers.TreeTraversal.2

            /* JADX INFO: Add missing generic type declarations: [TT] */
            /* JADX INFO: renamed from: com.intellij.util.containers.TreeTraversal$2$1WrappedTree, reason: invalid class name */
            public final class C1WrappedTree<TT> implements Condition<TT>, Function<TT, Iterable<? extends TT>> {
                final Function<?, ?> inner;
                final /* synthetic */ Function val$tree;
                HashSet<Object> visited;

                public C1WrappedTree(Function function) {
                    this.val$tree = function;
                    this.inner = function;
                }

                @Override // com.intellij.util.Function
                public Iterable<? extends TT> fun(TT tt) {
                    return JBIterable.from((Iterable) this.val$tree.fun(tt)).filter(this);
                }

                public boolean value(TT tt) {
                    if (this.visited == null) {
                        this.visited = new HashSet<>();
                    }
                    return this.visited.add(this.inner.fun(tt));
                }
            }

            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
                Object[] objArr = new Object[i != 1 ? 3 : 2];
                if (i != 1) {
                    objArr[0] = "args";
                } else {
                    objArr[0] = "com/intellij/util/containers/TreeTraversal$2";
                }
                if (i != 1) {
                    objArr[1] = "com/intellij/util/containers/TreeTraversal$2";
                } else {
                    objArr[1] = "intercept";
                }
                if (i != 1) {
                    objArr[2] = "intercept";
                }
                String str2 = String.format(str, objArr);
                if (i == 1) {
                    throw new IllegalStateException(str2);
                }
            }

            @Override // com.intellij.util.containers.TreeTraversal.TraversalInterceptor
            public <TT> TraversalArgs<TT> intercept(TraversalArgs<TT> traversalArgs) {
                if (traversalArgs == null) {
                    $$$reportNull$$$0(0);
                }
                Function<? super TT, ? extends Iterable<? extends TT>> function2 = traversalArgs.tree;
                if ((function2 instanceof C1WrappedTree) && Comparing.equal(function, ((C1WrappedTree) function2).inner)) {
                    return traversalArgs;
                }
                C1WrappedTree c1WrappedTree = new C1WrappedTree(function2);
                return new TraversalArgs<>(JBIterable.from(traversalArgs.roots).filter(c1WrappedTree), c1WrappedTree);
            }
        });
    }

    public static class P<T, Self extends P<T, Self>> {
        static final Function TO_NODE = new Function() { // from class: com.intellij.util.containers.x
            @Override // com.intellij.util.Function
            public final Object fun(Object obj) {
                return ((TreeTraversal.P) obj).node;
            }
        };
        static final Function TO_PREV = new Function.Mono<P<?, ?>>() { // from class: com.intellij.util.containers.TreeTraversal.P.1
            @Override // com.intellij.util.Function
            public P<?, ?> fun(P<?, ?> p) {
                return p.parent;
            }
        };
        boolean empty;
        Iterator<? extends T> it;
        Iterable<? extends T> itle;
        T node;
        Self parent;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            objArr[0] = "tree";
            objArr[1] = "com/intellij/util/containers/TreeTraversal$P";
            if (i != 1) {
                objArr[2] = "iterator";
            } else {
                objArr[2] = "iterable";
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        private P() {
        }

        public static <T, Self extends P<T, Self>> Self create(Self self, T t) {
            self.node = t;
            return self;
        }

        public final Iterable<? extends T> iterable(Function<? super T, ? extends Iterable<? extends T>> function) {
            if (function == null) {
                $$$reportNull$$$0(1);
            }
            Iterable<? extends T> iterable = this.itle;
            if (iterable != null) {
                return iterable;
            }
            Iterable<? extends T> iterableFun = function.fun(this.node);
            this.itle = iterableFun;
            return iterableFun != null ? iterableFun : JBIterable.empty();
        }

        public final Iterator<? extends T> iterator(Function<? super T, ? extends Iterable<? extends T>> function) {
            if (function == null) {
                $$$reportNull$$$0(0);
            }
            Iterator<? extends T> it = this.it;
            if (it != null) {
                return it;
            }
            Iterator<? extends T> it2 = iterable(function).iterator();
            this.it = it2;
            this.empty = this.itle == null || !it2.hasNext();
            return this.it;
        }

        public static <T, Self extends P<T, Self>> Self create(Self self, Iterable<? extends T> iterable) {
            self.itle = iterable;
            return self;
        }
    }

    public static final class P1<T> extends P<T, P1<T>> {
        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            objArr[0] = "next";
            objArr[1] = "com/intellij/util/containers/TreeTraversal$P1";
            if (i != 1) {
                objArr[2] = "add";
            } else {
                objArr[2] = "addBefore";
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        private P1() {
            super();
        }

        public static <T> P1<T> create(T t) {
            return (P1) P.create(new P1(), t);
        }

        public P1<T> add(P1<T> p1) {
            if (p1 == null) {
                $$$reportNull$$$0(0);
            }
            p1.parent = this;
            return p1;
        }

        public P1<T> remove() {
            return (P1) this.parent;
        }

        public String toString() {
            int i = 0;
            for (P1 p1 = (P1) this.parent; p1 != null; p1 = (P1) p1.parent) {
                i++;
            }
            return i + ": " + this.node;
        }

        public static <T> P1<T> create(Iterable<? extends T> iterable) {
            return (P1) P.create(new P1(), (Iterable) iterable);
        }
    }

    public static final class P2<T> extends P<T, P2<T>> {
        P2<T> next;
        P2<T> prev;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "next", "com/intellij/util/containers/TreeTraversal$P2", "add"));
        }

        private P2() {
            super();
        }

        public static <T> P2<T> create(T t) {
            return (P2) P.create(new P2(), t);
        }

        public P2<T> add(P2<T> p2) {
            if (p2 == null) {
                $$$reportNull$$$0(0);
            }
            p2.next = this.next;
            p2.prev = this;
            this.next = p2;
            return p2;
        }

        public P2<T> remove() {
            P2<T> p2 = this.prev;
            P2<T> p3 = this.next;
            this.next = null;
            this.prev = null;
            if (p2 != null) {
                p2.next = p3;
            }
            if (p3 != null) {
                p3.prev = p2;
            }
            return p2;
        }

        public String toString() {
            int i = 0;
            int i2 = 0;
            for (P2<T> p2 = this.prev; p2 != null; p2 = p2.prev) {
                i2++;
            }
            for (P2<T> p3 = this.next; p3 != null; p3 = p3.next) {
                i++;
            }
            return i2 + " of " + (i2 + i + 1) + ": " + this.node;
        }

        public static <T> P2<T> create(Iterable<? extends T> iterable) {
            return (P2) P.create(new P2(), (Iterable) iterable);
        }
    }

    public final <T> JBIterable<T> traversal(T t, Function<? super T, ? extends Iterable<? extends T>> function) {
        if (function == null) {
            $$$reportNull$$$0(3);
        }
        return traversal((Iterable) JBIterable.of(t), (Function) function);
    }
}
