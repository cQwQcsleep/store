package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import androidx.compose.compiler.plugins.kotlin.lower.ComposableFunctionBodyTransformerKt;
import com.intellij.openapi.util.Condition;
import com.intellij.openapi.util.Conditions;
import com.intellij.util.Function;
import com.intellij.util.Functions;
import com.intellij.util.containers.FilteredTraverserBase;
import com.intellij.util.containers.JBIterable;
import com.intellij.util.containers.TreeTraversal;
import defpackage.ns4;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class FilteredTraverserBase<T, Self extends FilteredTraverserBase<T, Self>> implements Iterable<T> {
    final Meta<T> myMeta;

    public static final class Cond<T> {
        final Condition<? super T> impl;
        final int length;
        final Cond<T> next;
        static final Cond<Object> TRUE = new Cond<>(Conditions.alwaysTrue(), null);
        static final Cond<Object> FALSE = new Cond<>(Conditions.alwaysFalse(), null);

        public Cond(Condition<? super T> condition, Cond<T> cond) {
            this.impl = condition;
            this.next = cond;
            this.length = cond != null ? 1 + cond.length : 1;
        }

        public Condition<? super T> and() {
            Boolean bool = Boolean.TRUE;
            for (Cond<T> cond = this; cond != null; cond = cond.next) {
                if (cond.impl == Conditions.alwaysFalse()) {
                    return Conditions.alwaysFalse();
                }
                if (bool != null && cond.impl != Conditions.alwaysTrue()) {
                    bool = null;
                }
            }
            return bool == null ? new Condition() { // from class: com.intellij.util.containers.e
                public final boolean value(Object obj) {
                    return this.b.valueAnd(obj);
                }
            } : Conditions.alwaysTrue();
        }

        public Cond<T> append(Condition<? super T> condition) {
            Cond<T> cond = new Cond<>(condition, null);
            Cond<T>[] array = toArray(true);
            int length = array.length;
            int i = 0;
            while (i < length) {
                Cond<T> cond2 = new Cond<>(array[i].impl, cond);
                i++;
                cond = cond2;
            }
            return cond;
        }

        public Condition<? super T> or() {
            Boolean bool = Boolean.FALSE;
            for (Cond<T> cond = this; cond != null; cond = cond.next) {
                if (cond.impl == Conditions.alwaysTrue()) {
                    return Conditions.alwaysTrue();
                }
                if (bool != null && cond.impl != Conditions.alwaysFalse()) {
                    bool = null;
                }
            }
            return bool == null ? new Condition() { // from class: com.intellij.util.containers.d
                public final boolean value(Object obj) {
                    return this.b.valueOr(obj);
                }
            } : Conditions.alwaysFalse();
        }

        public Cond<T>[] toArray(boolean z) {
            Cond<T>[] condArr = new Cond[this.length];
            int i = 0;
            Cond<T> cond = this;
            while (true) {
                int i2 = this.length;
                if (i >= i2) {
                    return condArr;
                }
                condArr[z ? (i2 - i) - 1 : i] = cond;
                i++;
                cond = cond.next;
            }
        }

        public String toString() {
            if (this == TRUE) {
                return "Cond.TRUE";
            }
            if (this == FALSE) {
                return "Cond.FALSE";
            }
            StringBuilder sb = new StringBuilder("Cond{");
            while (this != null) {
                sb.append(JBIterator.toShortString(this.impl));
                if (this.next != null) {
                    sb.append(", ");
                }
                this = this.next;
            }
            sb.append("}");
            return sb.toString();
        }

        public boolean valueAnd(T t) {
            while (this != null) {
                if (!this.impl.value(t)) {
                    return false;
                }
                this = this.next;
            }
            return true;
        }

        public boolean valueOr(T t) {
            while (this != null) {
                if (this.impl.value(t)) {
                    return true;
                }
                this = this.next;
            }
            return false;
        }
    }

    public static abstract class EdgeFilter<T> extends JBIterable.SCond<T> {
        protected T edgeSource;
    }

    public static final class MappedTraversal<T, S> extends TreeTraversal {
        final Function<? super T, ? extends S> map;
        final Meta<T> meta;
        final TreeTraversal original;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 5 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 5 ? 3 : 2];
            if (i == 1) {
                objArr[0] = "meta";
            } else if (i == 2) {
                objArr[0] = "map";
            } else if (i == 3) {
                objArr[0] = "ignore1";
            } else if (i == 4) {
                objArr[0] = "ignore2";
            } else if (i != 5) {
                objArr[0] = "original";
            } else {
                objArr[0] = "com/intellij/util/containers/FilteredTraverserBase$MappedTraversal";
            }
            if (i != 5) {
                objArr[1] = "com/intellij/util/containers/FilteredTraverserBase$MappedTraversal";
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
        public MappedTraversal(TreeTraversal treeTraversal, Meta<T> meta, Function<? super T, ? extends S> function) {
            super(treeTraversal + " (MAPPED by " + function + ")");
            if (treeTraversal == null) {
                $$$reportNull$$$0(0);
            }
            if (meta == null) {
                $$$reportNull$$$0(1);
            }
            if (function == null) {
                $$$reportNull$$$0(2);
            }
            this.original = treeTraversal;
            this.meta = meta;
            this.map = function;
        }

        @Override // com.intellij.util.containers.TreeTraversal
        public <SS> TreeTraversal.It<SS> createIterator(Iterable<? extends SS> iterable, Function<? super SS, ? extends Iterable<? extends SS>> function) {
            if (iterable == null) {
                $$$reportNull$$$0(3);
            }
            if (function == null) {
                $$$reportNull$$$0(4);
            }
            List listReverse = ContainerUtil.reverse(JBIterable.generate(this.meta, new Function() { // from class: com.intellij.util.containers.f
                @Override // com.intellij.util.Function
                public final Object fun(Object obj) {
                    return ((FilteredTraverserBase.Meta) obj).original;
                }
            }).toList());
            Meta meta = (Meta) listReverse.get(0);
            Iterable map = meta.roots;
            Function ns4Var = new ns4(meta);
            final Condition<? super T> conditionAnd = meta.filter.and();
            int size = listReverse.size();
            int i = 1;
            while (i <= size) {
                Meta meta2 = i < size ? (Meta) listReverse.get(i) : null;
                final MappedTree mappedTree = new MappedTree(ns4Var, ((MappedTraversal) (meta2 == null ? this : meta2.interceptor.fun(this.original))).map, meta2);
                map = JBIterable.from(map).map(new g(mappedTree));
                conditionAnd = Conditions.and(conditionAnd == Conditions.alwaysTrue() ? Conditions.alwaysTrue() : new Condition() { // from class: com.intellij.util.containers.h
                    public final boolean value(Object obj) {
                        return conditionAnd.value(((FilteredTraverserBase.MappedTree) mappedTree).reverse(obj));
                    }
                }, meta2 == null ? Conditions.alwaysTrue() : meta2.filter.and());
                i++;
                ns4Var = mappedTree;
            }
            TreeTraversal.It<SS> it = (TreeTraversal.It) this.original.createIterator(JBIterable.from(map), (MappedTree) ns4Var).filter(conditionAnd);
            if (it == null) {
                $$$reportNull$$$0(5);
            }
            return it;
        }
    }

    public static final class MappedTree<T, S> implements Function<S, Iterable<? extends S>> {
        final Function<? super T, ? extends S> mapInner;
        final Meta<S> meta;
        Map<S, T> reverse;
        final Function<? super T, ? extends Iterable<? extends T>> tree;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            throw new IllegalStateException(String.format("@NotNull method %s.%s must not return null", "com/intellij/util/containers/FilteredTraverserBase$MappedTree", "reverse"));
        }

        public MappedTree(Function<? super T, ? extends Iterable<? extends T>> function, Function<? super T, ? extends S> function2, Meta<S> meta) {
            this.tree = function;
            this.mapInner = function2;
            this.meta = meta;
        }

        @Override // com.intellij.util.Function
        public Iterable<? extends S> fun(S s) {
            Meta<S> meta = this.meta;
            return meta == null ? JBIterable.from(this.tree.fun(reverse(s))).map(new g(this)) : meta.childrenImpl(s, new Function() { // from class: com.intellij.util.containers.i
                @Override // com.intellij.util.Function
                public final Object fun(Object obj) {
                    FilteredTraverserBase.MappedTree mappedTree = this.b;
                    return JBIterable.from(mappedTree.tree.fun((Object) mappedTree.reverse(obj))).map(new g(mappedTree));
                }
            });
        }

        public S map(T t) {
            if (this.reverse == null) {
                this.reverse = new java.util.WeakHashMap();
            }
            S sFun = this.mapInner.fun(t);
            if (sFun != null && t != null) {
                this.reverse.put(sFun, t);
            }
            return sFun;
        }

        public T reverse(S s) {
            T t = s == null ? null : this.reverse.get(s);
            if (t != null) {
                return t;
            }
            qu7.a("unable to reverse map for: ", s);
            return null;
        }
    }

    public static final class Meta<T> {
        final Cond<T> expand;
        final Cond<T> filter;
        final Cond<T> forceDisregard;
        final Cond<T> forceExpand;
        final Cond<T> forceIgnore;
        final Function<? super TreeTraversal, ? extends TreeTraversal> interceptor;
        final Meta<?> original;
        final Cond<T> regard;
        final Iterable<? extends T> roots;
        final TreeTraversal traversal;
        final Function<? super T, ? extends Iterable<? extends T>> tree;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            switch (i) {
                case 1:
                    objArr[0] = "traversal";
                    break;
                case 2:
                case 14:
                    objArr[0] = "tree";
                    break;
                case 3:
                    objArr[0] = "expand";
                    break;
                case 4:
                    objArr[0] = "regard";
                    break;
                case 5:
                    objArr[0] = "filter";
                    break;
                case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                    objArr[0] = "forceExpand";
                    break;
                case ScatterMapKt.ClonedMetadataCount /* 7 */:
                    objArr[0] = "forceIgnore";
                    break;
                case 8:
                    objArr[0] = "forceDisregard";
                    break;
                case 9:
                    objArr[0] = "interceptor";
                    break;
                case 10:
                default:
                    objArr[0] = "roots";
                    break;
                case 11:
                case 12:
                case 13:
                    objArr[0] = "c";
                    break;
            }
            objArr[1] = "com/intellij/util/containers/FilteredTraverserBase$Meta";
            switch (i) {
                case 10:
                    objArr[2] = "withRoots";
                    break;
                case 11:
                    objArr[2] = "expand";
                    break;
                case 12:
                    objArr[2] = "regard";
                    break;
                case 13:
                    objArr[2] = "filter";
                    break;
                case 14:
                    objArr[2] = "childrenImpl";
                    break;
                default:
                    objArr[2] = "<init>";
                    break;
            }
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        /* JADX WARN: Multi-variable type inference failed */
        public Meta(Iterable<? extends T> iterable, TreeTraversal treeTraversal, Function<? super T, ? extends Iterable<? extends T>> function, Cond<? super T> cond, Cond<? super T> cond2, Cond<? super T> cond3, Cond<? super T> cond4, Cond<? super T> cond5, Cond<? super T> cond6, Function<? super TreeTraversal, ? extends TreeTraversal> function2, Meta<?> meta) {
            if (iterable == null) {
                $$$reportNull$$$0(0);
            }
            if (treeTraversal == null) {
                $$$reportNull$$$0(1);
            }
            if (function == null) {
                $$$reportNull$$$0(2);
            }
            if (cond == 0) {
                $$$reportNull$$$0(3);
            }
            if (cond2 == 0) {
                $$$reportNull$$$0(4);
            }
            if (cond3 == 0) {
                $$$reportNull$$$0(5);
            }
            if (cond4 == 0) {
                $$$reportNull$$$0(6);
            }
            if (cond5 == 0) {
                $$$reportNull$$$0(7);
            }
            if (cond6 == 0) {
                $$$reportNull$$$0(8);
            }
            if (function2 == null) {
                $$$reportNull$$$0(9);
            }
            this.roots = iterable;
            this.traversal = treeTraversal;
            this.tree = function;
            this.expand = cond;
            this.regard = cond2;
            this.filter = cond3;
            this.forceExpand = cond4;
            this.forceIgnore = cond5;
            this.forceDisregard = cond6;
            this.interceptor = function2;
            this.original = meta;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public Condition<? super T> buildExpandConditionForChildren(T t) {
            Cond cond;
            int i = this.regard.length;
            Cond<T> cond2 = this.forceDisregard;
            Condition[] conditionArr = new Condition[i + cond2.length];
            int i2 = 0;
            int i3 = 0;
            boolean z = false;
            while (true) {
                cond = null;
                if (cond2 == null) {
                    break;
                }
                if (cond2.impl != (z ? Conditions.alwaysTrue() : Conditions.alwaysFalse())) {
                    Condition conditionNot = (Condition) JBIterable.Stateful.copy(cond2.impl);
                    if (conditionNot instanceof EdgeFilter) {
                        ((EdgeFilter) conditionNot).edgeSource = t;
                    }
                    int i4 = i3 + 1;
                    if (z) {
                        conditionNot = Conditions.not(conditionNot);
                    }
                    conditionArr[i3] = conditionNot;
                    i3 = i4;
                }
                cond2 = cond2.next;
                if (cond2 == null) {
                    cond2 = z ? null : this.regard;
                    z = true;
                }
            }
            if (i3 <= 0) {
                return Conditions.alwaysFalse();
            }
            while (i2 < i3) {
                Cond cond3 = new Cond(conditionArr[(i3 - i2) - 1], cond);
                i2++;
                cond = cond3;
            }
            return cond.or();
        }

        public static <T> Meta<T> create(Function<? super T, ? extends Iterable<? extends T>> function) {
            JBIterable jBIterableEmpty = JBIterable.empty();
            TreeTraversal treeTraversal = TreeTraversal.PRE_ORDER_DFS;
            Cond<Object> cond = Cond.TRUE;
            Cond<Object> cond2 = Cond.FALSE;
            return new Meta<>(jBIterableEmpty, treeTraversal, function, cond, cond, cond, cond2, cond2, cond2, Functions.id(), null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void doPerformChildrenGuidance(TreeTraversal.GuidedIt<T> guidedIt, Condition<? super T> condition) {
            T t = guidedIt.curChild;
            if (t == null) {
                return;
            }
            Cond<T> cond = this.forceIgnore;
            if (cond == Cond.FALSE || !cond.valueOr(t)) {
                if (guidedIt.curParent == null || condition == Conditions.alwaysTrue() || condition.value(guidedIt.curChild)) {
                    guidedIt.queueNext(guidedIt.curChild);
                } else {
                    guidedIt.result(guidedIt.curChild);
                }
            }
        }

        public JBIterable<T> children(T t) {
            return childrenImpl(t, this.tree);
        }

        public JBIterable<T> childrenImpl(T t, Function<? super T, ? extends Iterable<? extends T>> function) {
            Cond<T> cond;
            if (function == null) {
                $$$reportNull$$$0(14);
            }
            if (t == null) {
                return JBIterable.empty();
            }
            Cond<T> cond2 = this.expand;
            Cond<Object> cond3 = Cond.TRUE;
            if (cond2 == cond3 || cond2.valueAnd(t) || ((cond = this.forceExpand) != Cond.FALSE && cond.valueOr(t))) {
                return (this.regard == cond3 && this.forceDisregard == Cond.FALSE) ? JBIterable.from(function.fun(t)).filter(Conditions.not(this.forceIgnore.or())) : TreeTraversal.GUIDED_TRAVERSAL(createChildrenGuide(t)).traversal(t, function);
            }
            return JBIterable.empty();
        }

        public TreeTraversal.GuidedIt.Guide<T> createChildrenGuide(T t) {
            return new TreeTraversal.GuidedIt.Guide<T>(t) { // from class: com.intellij.util.containers.FilteredTraverserBase.Meta.1
                final Condition<? super T> expand;
                final /* synthetic */ Object val$parent;

                private static /* synthetic */ void $$$reportNull$$$0(int i) {
                    throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "guidedIt", "com/intellij/util/containers/FilteredTraverserBase$Meta$1", "guide"));
                }

                {
                    this.val$parent = t;
                    this.expand = Meta.this.buildExpandConditionForChildren(t);
                }

                @Override // com.intellij.util.containers.TreeTraversal.GuidedIt.Guide
                public void guide(TreeTraversal.GuidedIt<T> guidedIt) {
                    if (guidedIt == null) {
                        $$$reportNull$$$0(0);
                    }
                    Meta.this.doPerformChildrenGuidance(guidedIt, this.expand);
                }
            };
        }

        public Meta<T> expand(Condition<? super T> condition) {
            if (condition == null) {
                $$$reportNull$$$0(11);
            }
            return new Meta<>(this.roots, this.traversal, this.tree, this.expand.append(condition), this.regard, this.filter, this.forceExpand, this.forceIgnore, this.forceDisregard, this.interceptor, this.original);
        }

        public Meta<T> filter(Condition<? super T> condition) {
            if (condition == null) {
                $$$reportNull$$$0(13);
            }
            return new Meta<>(this.roots, this.traversal, this.tree, this.expand, this.regard, this.filter.append(condition), this.forceExpand, this.forceIgnore, this.forceDisregard, this.interceptor, this.original);
        }

        public Meta<T> forceDisregard(Condition<? super T> condition) {
            return new Meta<>(this.roots, this.traversal, this.tree, this.expand, this.regard, this.filter, this.forceExpand, this.forceIgnore, this.forceDisregard.append(condition), this.interceptor, this.original);
        }

        public Meta<T> forceExpand(Condition<? super T> condition) {
            return new Meta<>(this.roots, this.traversal, this.tree, this.expand, this.regard, this.filter, this.forceExpand.append(condition), this.forceIgnore, this.forceDisregard, this.interceptor, this.original);
        }

        public Meta<T> forceIgnore(Condition<? super T> condition) {
            return new Meta<>(this.roots, this.traversal, this.tree, this.expand, this.regard, this.filter, this.forceExpand, this.forceIgnore.append(condition), this.forceDisregard, this.interceptor, this.original);
        }

        public Meta<T> interceptTraversal(Function<? super TreeTraversal, ? extends TreeTraversal> function) {
            return function == Functions.identity() ? this : new Meta<>(this.roots, this.traversal, this.tree, this.expand, this.regard, this.filter, this.forceExpand, this.forceIgnore, this.forceDisregard, Functions.compose(this.interceptor, function), this.original);
        }

        public Meta<T> regard(Condition<? super T> condition) {
            if (condition == null) {
                $$$reportNull$$$0(12);
            }
            return new Meta<>(this.roots, this.traversal, this.tree, this.expand, this.regard.append(condition), this.filter, this.forceExpand, this.forceIgnore, this.forceDisregard, this.interceptor, this.original);
        }

        public Meta<T> reset() {
            Iterable<? extends T> iterable = this.roots;
            TreeTraversal treeTraversal = TreeTraversal.PRE_ORDER_DFS;
            Function<? super T, ? extends Iterable<? extends T>> function = this.tree;
            Cond<Object> cond = Cond.TRUE;
            return new Meta<>(iterable, treeTraversal, function, cond, cond, cond, this.forceExpand, this.forceIgnore, this.forceDisregard, this.interceptor, this.original);
        }

        public Meta<T> withRoots(Iterable<? extends T> iterable) {
            if (iterable == null) {
                $$$reportNull$$$0(10);
            }
            return new Meta<>(iterable, this.traversal, this.tree, this.expand, this.regard, this.filter, this.forceExpand, this.forceIgnore, this.forceDisregard, this.interceptor, this.original);
        }

        public Meta<T> withTraversal(TreeTraversal treeTraversal) {
            return new Meta<>(this.roots, treeTraversal, this.tree, this.expand, this.regard, this.filter, this.forceExpand, this.forceIgnore, this.forceDisregard, this.interceptor, this.original);
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 16:
            case 18:
            case 20:
            case 24:
            case 26:
            case 28:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 33:
            case 34:
            case 35:
            case 36:
                str = "@NotNull method %s.%s must not return null";
                break;
            case 4:
            case 11:
            case 13:
            case 17:
            case 19:
            case 21:
            case 22:
            case 23:
            case 25:
            case 27:
            case 29:
            case 30:
            case 32:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 16:
            case 18:
            case 20:
            case 24:
            case 26:
            case 28:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 33:
            case 34:
            case 35:
            case 36:
                i2 = 2;
                break;
            case 4:
            case 11:
            case 13:
            case 17:
            case 19:
            case 21:
            case 22:
            case 23:
            case 25:
            case 27:
            case 29:
            case 30:
            case 32:
            default:
                i2 = 3;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 16:
            case 18:
            case 20:
            case 24:
            case 26:
            case 28:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 33:
            case 34:
            case 35:
            case 36:
                objArr[0] = "com/intellij/util/containers/FilteredTraverserBase";
                break;
            case 4:
                objArr[0] = "traversal";
                break;
            case 11:
            case 13:
            case 17:
            case 23:
            case 25:
                objArr[0] = "c";
                break;
            case 19:
                objArr[0] = "type";
                break;
            case 21:
                objArr[0] = "identity";
                break;
            case 22:
                objArr[0] = "rangeCondition";
                break;
            case 27:
                objArr[0] = "transform";
                break;
            case 29:
            case 32:
                objArr[0] = "function";
                break;
            case 30:
                objArr[0] = "reverse";
                break;
            default:
                objArr[0] = "meta";
                break;
        }
        switch (i) {
            case 1:
                objArr[1] = "getTree";
                break;
            case 2:
                objArr[1] = "getRoot";
                break;
            case 3:
                objArr[1] = "getRoots";
                break;
            case 4:
            case 11:
            case 13:
            case 17:
            case 19:
            case 21:
            case 22:
            case 23:
            case 25:
            case 27:
            case 29:
            case 30:
            case 32:
            default:
                objArr[1] = "com/intellij/util/containers/FilteredTraverserBase";
                break;
            case 5:
                objArr[1] = "traverse";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[1] = "reset";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[1] = "withRoot";
                break;
            case 8:
            case 9:
                objArr[1] = "withRoots";
                break;
            case 10:
                objArr[1] = "withTraversal";
                break;
            case 12:
                objArr[1] = "expand";
                break;
            case 14:
                objArr[1] = "regard";
                break;
            case 15:
                objArr[1] = "expandAndFilter";
                break;
            case 16:
                objArr[1] = "expandAndSkip";
                break;
            case 18:
            case 20:
                objArr[1] = "filter";
                break;
            case 24:
                objArr[1] = "forceIgnore";
                break;
            case 26:
                objArr[1] = "forceDisregard";
                break;
            case 28:
                objArr[1] = "intercept";
                break;
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 33:
                objArr[1] = "mapImpl";
                break;
            case 34:
                objArr[1] = "children";
                break;
            case 35:
                objArr[1] = "toList";
                break;
            case 36:
                objArr[1] = "toSet";
                break;
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 16:
            case 18:
            case 20:
            case 24:
            case 26:
            case 28:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 33:
            case 34:
            case 35:
            case 36:
                break;
            case 4:
                objArr[2] = "traverse";
                break;
            case 11:
                objArr[2] = "expand";
                break;
            case 13:
                objArr[2] = "regard";
                break;
            case 17:
            case 19:
                objArr[2] = "filter";
                break;
            case 21:
                objArr[2] = "unique";
                break;
            case 22:
                objArr[2] = "onRange";
                break;
            case 23:
                objArr[2] = "forceIgnore";
                break;
            case 25:
                objArr[2] = "forceDisregard";
                break;
            case 27:
                objArr[2] = "intercept";
                break;
            case 29:
            case 30:
            case 32:
                objArr[2] = "mapImpl";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 12:
            case 14:
            case 15:
            case 16:
            case 18:
            case 20:
            case 24:
            case 26:
            case 28:
            case ComposableFunctionBodyTransformerKt.BITS_PER_INT /* 31 */:
            case 33:
            case 34:
            case 35:
            case 36:
                throw new IllegalStateException(str2);
            case 4:
            case 11:
            case 13:
            case 17:
            case 19:
            case 21:
            case 22:
            case 23:
            case 25:
            case 27:
            case 29:
            case 30:
            case 32:
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    public FilteredTraverserBase(Meta<T> meta) {
        if (meta == null) {
            $$$reportNull$$$0(0);
        }
        this.myMeta = meta;
    }

    public static /* synthetic */ TreeTraversal d(FilteredTraverserBase filteredTraverserBase, Function function, TreeTraversal treeTraversal) {
        filteredTraverserBase.getClass();
        return new MappedTraversal(treeTraversal, filteredTraverserBase.myMeta, (Function) JBIterable.Stateful.copy(function));
    }

    public final JBIterable<T> bfsTraversal() {
        return traverse(TreeTraversal.PLAIN_BFS);
    }

    public final JBIterable<T> biOrderDfsTraversal() {
        return traverse(TreeTraversal.BI_ORDER_DFS);
    }

    public final Self cached() {
        final IdentityHashMap identityHashMap = new IdentityHashMap();
        return (Self) intercept(new Function() { // from class: ss4
            @Override // com.intellij.util.Function
            public final Object fun(Object obj) {
                return ((TreeTraversal) obj).cached(identityHashMap);
            }
        });
    }

    public final JBIterable<T> children(T t) {
        JBIterable<T> jBIterableChildren = this.myMeta.children(t);
        if (jBIterableChildren == null) {
            $$$reportNull$$$0(34);
        }
        return jBIterableChildren;
    }

    public final Self expand(Condition<? super T> condition) {
        if (condition == null) {
            $$$reportNull$$$0(11);
        }
        Self self = (Self) newInstance(this.myMeta.expand(condition));
        if (self == null) {
            $$$reportNull$$$0(12);
        }
        return self;
    }

    public final Self expandAndFilter(Condition<? super T> condition) {
        Self self = (Self) newInstance(this.myMeta.expand(condition).filter(condition));
        if (self == null) {
            $$$reportNull$$$0(15);
        }
        return self;
    }

    public final Self expandAndSkip(Condition<? super T> condition) {
        Self self = (Self) newInstance(this.myMeta.expand(condition).filter(Conditions.not(condition)));
        if (self == null) {
            $$$reportNull$$$0(16);
        }
        return self;
    }

    public final Self filter(Condition<? super T> condition) {
        if (condition == null) {
            $$$reportNull$$$0(17);
        }
        Self self = (Self) newInstance(this.myMeta.filter(condition));
        if (self == null) {
            $$$reportNull$$$0(18);
        }
        return self;
    }

    public final Self forceDisregard(Condition<? super T> condition) {
        if (condition == null) {
            $$$reportNull$$$0(25);
        }
        Self self = (Self) newInstance(this.myMeta.forceDisregard(condition));
        if (self == null) {
            $$$reportNull$$$0(26);
        }
        return self;
    }

    public final Self forceIgnore(Condition<? super T> condition) {
        if (condition == null) {
            $$$reportNull$$$0(23);
        }
        Self self = (Self) newInstance(this.myMeta.forceIgnore(condition));
        if (self == null) {
            $$$reportNull$$$0(24);
        }
        return self;
    }

    public final T getRoot() {
        T next = this.myMeta.roots.iterator().next();
        if (next == null) {
            $$$reportNull$$$0(2);
        }
        return next;
    }

    public final Iterable<? extends T> getRoots() {
        Iterable<? extends T> iterable = this.myMeta.roots;
        if (iterable == null) {
            $$$reportNull$$$0(3);
        }
        return iterable;
    }

    public Function<? super T, ? extends Iterable<? extends T>> getTree() {
        Function<? super T, ? extends Iterable<? extends T>> function = this.myMeta.tree;
        if (function == null) {
            $$$reportNull$$$0(1);
        }
        return function;
    }

    public final Self intercept(Function<? super TreeTraversal, ? extends TreeTraversal> function) {
        if (function == null) {
            $$$reportNull$$$0(27);
        }
        Self self = (Self) newInstance(this.myMeta.interceptTraversal(function));
        if (self == null) {
            $$$reportNull$$$0(28);
        }
        return self;
    }

    @Override // java.lang.Iterable
    public final Iterator<T> iterator() {
        return traverse().iterator();
    }

    public <S, SelfS extends FilteredTraverserBase<S, ?>> SelfS mapImpl(final Function<? super T, ? extends S> function, final Function<? super S, ? extends T> function2) {
        if (function == null) {
            $$$reportNull$$$0(29);
        }
        if (function2 == null) {
            $$$reportNull$$$0(30);
        }
        Meta<T> meta = this.myMeta;
        Objects.requireNonNull(meta);
        final ns4 ns4Var = new ns4(meta);
        final Condition<? super T> conditionAnd = this.myMeta.filter.and();
        SelfS selfs = (SelfS) newInstance(Meta.create(new Function() { // from class: os4
            @Override // com.intellij.util.Function
            public final Object fun(Object obj) {
                return ((JBIterable) ns4Var.fun(function2.fun(obj))).map(function);
            }
        }).withRoots(JBIterable.from(getRoots()).map(function)).filter(conditionAnd == Conditions.alwaysTrue() ? Conditions.alwaysTrue() : new Condition() { // from class: ps4
            public final boolean value(Object obj) {
                return conditionAnd.value(function2.fun(obj));
            }
        }));
        if (selfs == null) {
            $$$reportNull$$$0(31);
        }
        return selfs;
    }

    public abstract Self newInstance(Meta<T> meta);

    public Self onRange(final Condition<? super T> condition) {
        if (condition == null) {
            $$$reportNull$$$0(22);
        }
        return (Self) intercept(new Function() { // from class: rs4
            @Override // com.intellij.util.Function
            public final Object fun(Object obj) {
                return ((TreeTraversal) obj).onRange(condition);
            }
        });
    }

    public final JBIterable<T> postOrderDfsTraversal() {
        return traverse(TreeTraversal.POST_ORDER_DFS);
    }

    public final JBIterable<T> preOrderDfsTraversal() {
        return traverse(TreeTraversal.PRE_ORDER_DFS);
    }

    public final Self regard(Condition<? super T> condition) {
        if (condition == null) {
            $$$reportNull$$$0(13);
        }
        Self self = (Self) newInstance(this.myMeta.regard(condition));
        if (self == null) {
            $$$reportNull$$$0(14);
        }
        return self;
    }

    public final Self reset() {
        Self self = (Self) newInstance(this.myMeta.reset());
        if (self == null) {
            $$$reportNull$$$0(6);
        }
        return self;
    }

    public final List<T> toList() {
        List<T> list = traverse().toList();
        if (list == null) {
            $$$reportNull$$$0(35);
        }
        return list;
    }

    public final Set<T> toSet() {
        Set<T> set = traverse().toSet();
        if (set == null) {
            $$$reportNull$$$0(36);
        }
        return set;
    }

    public String toString() {
        return getClass().getSimpleName() + "{traversal=" + this.myMeta.traversal + '}';
    }

    public final JBIterable<T> tracingBfsTraversal() {
        return traverse(TreeTraversal.TRACING_BFS);
    }

    public final JBIterable<T> traverse(TreeTraversal treeTraversal) {
        if (treeTraversal == null) {
            $$$reportNull$$$0(4);
        }
        JBIterable<T> jBIterableFilter = this.myMeta.interceptor.fun(treeTraversal).traversal((Iterable) getRoots(), (Function) new Function() { // from class: ts4
            @Override // com.intellij.util.Function
            public final Object fun(Object obj) {
                return this.b.children(obj);
            }
        }).filter(this.myMeta.filter.and());
        if (jBIterableFilter == null) {
            $$$reportNull$$$0(5);
        }
        return jBIterableFilter;
    }

    public final Self unique(final Function<? super T, Object> function) {
        if (function == null) {
            $$$reportNull$$$0(21);
        }
        return (Self) intercept(new Function() { // from class: qs4
            @Override // com.intellij.util.Function
            public final Object fun(Object obj) {
                return ((TreeTraversal) obj).unique(function);
            }
        });
    }

    public final Self withRoot(T t) {
        Self self = (Self) newInstance(this.myMeta.withRoots(JBIterable.of(t)));
        if (self == null) {
            $$$reportNull$$$0(7);
        }
        return self;
    }

    public final Self withRoots(Iterable<? extends T> iterable) {
        Meta<T> meta = this.myMeta;
        if (iterable == null) {
            iterable = JBIterable.empty();
        }
        Self self = (Self) newInstance(meta.withRoots(iterable));
        if (self == null) {
            $$$reportNull$$$0(9);
        }
        return self;
    }

    public final Self withTraversal(TreeTraversal treeTraversal) {
        Self self = (Self) newInstance(this.myMeta.withTraversal(treeTraversal));
        if (self == null) {
            $$$reportNull$$$0(10);
        }
        return self;
    }

    public final Self unique() {
        return (Self) unique(Functions.identity());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final <C> JBIterable<C> filter(Class<C> cls) {
        if (cls == 0) {
            $$$reportNull$$$0(19);
        }
        JBIterable<T> jBIterableFilter = traverse().filter(cls);
        if (jBIterableFilter == null) {
            $$$reportNull$$$0(20);
        }
        return jBIterableFilter;
    }

    public final Self withRoots(T... tArr) {
        Self self = (Self) newInstance(this.myMeta.withRoots(JBIterable.of((Object[]) tArr)));
        if (self == null) {
            $$$reportNull$$$0(8);
        }
        return self;
    }

    public final JBIterable<T> traverse() {
        return traverse(this.myMeta.traversal);
    }

    public <S, SelfS extends FilteredTraverserBase<S, ?>> SelfS mapImpl(final Function<? super T, ? extends S> function) {
        if (function == null) {
            $$$reportNull$$$0(32);
        }
        JBIterable jBIterableEmpty = JBIterable.empty();
        TreeTraversal treeTraversal = this.myMeta.traversal;
        Function functionConstant = Functions.constant(JBIterable.empty());
        Cond<Object> cond = Cond.TRUE;
        Cond<Object> cond2 = Cond.FALSE;
        SelfS selfs = (SelfS) newInstance(new Meta<>(jBIterableEmpty, treeTraversal, functionConstant, cond, cond, cond, cond2, cond2, cond2, new Function() { // from class: ms4
            @Override // com.intellij.util.Function
            public final Object fun(Object obj) {
                return FilteredTraverserBase.d(this.b, function, (TreeTraversal) obj);
            }
        }, this.myMeta));
        if (selfs == null) {
            $$$reportNull$$$0(33);
        }
        return selfs;
    }
}
