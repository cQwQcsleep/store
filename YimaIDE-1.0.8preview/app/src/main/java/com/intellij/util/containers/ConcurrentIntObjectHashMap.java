package com.intellij.util.containers;

import androidx.collection.ScatterMapKt;
import androidx.collection.SieveCacheKt;
import com.intellij.util.io.IOUtil;
import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.Set;
import java.util.Spliterator;
import java.util.concurrent.locks.LockSupport;
import java.util.function.Consumer;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Deprecated
final class ConcurrentIntObjectHashMap<V> implements ConcurrentIntObjectMap<V> {
    private static final long ABASE;
    private static final int ASHIFT;
    private static final long BASECOUNT;
    private static final long CELLSBUSY;
    private static final long CELLVALUE;
    static final int NCPU = Runtime.getRuntime().availableProcessors();
    private static final long SIZECTL;
    private static final long TRANSFERINDEX;
    private volatile transient long baseCount;
    private volatile transient int cellsBusy;
    private volatile transient CounterCell[] counterCells;
    private transient EntrySetView<V> entrySet;
    private volatile transient Node<V>[] nextTable;
    private volatile transient int sizeCtl;
    volatile transient Node<V>[] table;
    private volatile transient int transferIndex;
    private transient ValuesView<V> values;

    public static class BaseIterator<V> extends Traverser<V> {
        Node<V> lastReturned;
        final ConcurrentIntObjectHashMap<V> map;

        public BaseIterator(Node<V>[] nodeArr, int i, int i2, int i3, ConcurrentIntObjectHashMap<V> concurrentIntObjectHashMap) {
            super(nodeArr, i, i2, i3);
            this.map = concurrentIntObjectHashMap;
            advance();
        }

        public final boolean hasMoreElements() {
            return this.next != null;
        }

        public final boolean hasNext() {
            return this.next != null;
        }

        public final void remove() {
            Node<V> node = this.lastReturned;
            if (node == null) {
                g33.a();
            } else {
                this.lastReturned = null;
                this.map.replaceNode(node.key, null, null);
            }
        }
    }

    public static final class CounterCell {
        volatile long value;

        public CounterCell(long j) {
            this.value = j;
        }
    }

    public static final class EntryIterator<V> extends BaseIterator<V> implements Iterator<IntObjectMap.Entry<V>> {
        public EntryIterator(Node<V>[] nodeArr, int i, int i2, int i3, ConcurrentIntObjectHashMap<V> concurrentIntObjectHashMap) {
            super(nodeArr, i, i2, i3, concurrentIntObjectHashMap);
        }

        @Override // java.util.Iterator
        public final IntObjectMap.Entry<V> next() {
            Node<V> node = this.next;
            if (node == null) {
                z0e.a();
                return null;
            }
            int i = node.key;
            V v = node.val;
            this.lastReturned = node;
            advance();
            return new SimpleEntry(i, v);
        }
    }

    public static final class EntrySetView<V> extends CollectionView<V, IntObjectMap.Entry<V>> implements Set<IntObjectMap.Entry<V>> {
        public EntrySetView(ConcurrentIntObjectHashMap<V> concurrentIntObjectHashMap) {
            super(concurrentIntObjectHashMap);
        }

        @Override // java.util.Collection, java.util.Set
        public boolean add(IntObjectMap.Entry<V> entry) {
            return this.map.putVal(entry.getKey(), entry.getValue(), false) == null;
        }

        @Override // java.util.Collection, java.util.Set
        public boolean addAll(Collection<? extends IntObjectMap.Entry<V>> collection) {
            Iterator<? extends IntObjectMap.Entry<V>> it = collection.iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (add((IntObjectMap.Entry) it.next())) {
                    z = true;
                }
            }
            return z;
        }

        @Override // com.intellij.util.containers.ConcurrentIntObjectHashMap.CollectionView, java.util.Collection
        public boolean contains(Object obj) {
            if (!(obj instanceof IntObjectMap.Entry)) {
                return false;
            }
            IntObjectMap.Entry entry = (IntObjectMap.Entry) obj;
            V v = this.map.get(entry.getKey());
            if (v == null) {
                return false;
            }
            Object value = entry.getValue();
            return value == v || value.equals(v);
        }

        @Override // java.util.Collection, java.util.Set
        public final boolean equals(Object obj) {
            if (!(obj instanceof Set)) {
                return false;
            }
            Set set = (Set) obj;
            if (set != this) {
                return containsAll(set) && set.containsAll(this);
            }
            return true;
        }

        @Override // java.lang.Iterable
        public void forEach(Consumer<? super IntObjectMap.Entry<V>> consumer) {
            consumer.getClass();
            Node<V>[] nodeArr = this.map.table;
            if (nodeArr == null) {
                return;
            }
            Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
            while (true) {
                Node<V> nodeAdvance = traverser.advance();
                if (nodeAdvance == null) {
                    return;
                } else {
                    consumer.accept(new SimpleEntry(nodeAdvance.key, nodeAdvance.val));
                }
            }
        }

        @Override // java.util.Collection, java.util.Set
        public int hashCode() {
            Node<V>[] nodeArr = this.map.table;
            int iHashCode = 0;
            if (nodeArr != null) {
                Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
                while (true) {
                    Node<V> nodeAdvance = traverser.advance();
                    if (nodeAdvance == null) {
                        break;
                    }
                    iHashCode += nodeAdvance.hashCode();
                }
            }
            return iHashCode;
        }

        @Override // com.intellij.util.containers.ConcurrentIntObjectHashMap.CollectionView, java.util.Collection, java.lang.Iterable
        public Iterator<IntObjectMap.Entry<V>> iterator() {
            ConcurrentIntObjectHashMap<V> concurrentIntObjectHashMap = this.map;
            Node<V>[] nodeArr = concurrentIntObjectHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            return new EntryIterator(nodeArr, length, 0, length, concurrentIntObjectHashMap);
        }

        @Override // com.intellij.util.containers.ConcurrentIntObjectHashMap.CollectionView, java.util.Collection
        public boolean remove(Object obj) {
            if (!(obj instanceof IntObjectMap.Entry)) {
                return false;
            }
            IntObjectMap.Entry entry = (IntObjectMap.Entry) obj;
            return this.map.remove(entry.getKey(), entry.getValue());
        }

        @Override // java.util.Collection, java.lang.Iterable, java.util.Set
        public Spliterator<IntObjectMap.Entry<V>> spliterator() {
            ConcurrentIntObjectHashMap<V> concurrentIntObjectHashMap = this.map;
            long jSumCount = concurrentIntObjectHashMap.sumCount();
            Node<V>[] nodeArr = concurrentIntObjectHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            return new EntrySpliterator(nodeArr, length, 0, length, jSumCount < 0 ? 0L : jSumCount, concurrentIntObjectHashMap);
        }
    }

    public static final class EntrySpliterator<V> extends Traverser<V> implements Spliterator<IntObjectMap.Entry<V>> {
        long est;
        final ConcurrentIntObjectHashMap<V> map;

        public EntrySpliterator(Node<V>[] nodeArr, int i, int i2, int i3, long j, ConcurrentIntObjectHashMap<V> concurrentIntObjectHashMap) {
            super(nodeArr, i, i2, i3);
            this.map = concurrentIntObjectHashMap;
            this.est = j;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 4353;
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.est;
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super IntObjectMap.Entry<V>> consumer) {
            consumer.getClass();
            while (true) {
                Node<V> nodeAdvance = advance();
                if (nodeAdvance == null) {
                    return;
                } else {
                    consumer.accept(new SimpleEntry(nodeAdvance.key, nodeAdvance.val));
                }
            }
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super IntObjectMap.Entry<V>> consumer) {
            consumer.getClass();
            Node<V> nodeAdvance = advance();
            if (nodeAdvance == null) {
                return false;
            }
            consumer.accept(new SimpleEntry(nodeAdvance.key, nodeAdvance.val));
            return true;
        }

        @Override // java.util.Spliterator
        public EntrySpliterator<V> trySplit() {
            int i = this.baseIndex;
            int i2 = this.baseLimit;
            int i3 = (i + i2) >>> 1;
            if (i3 <= i) {
                return null;
            }
            Node<V>[] nodeArr = this.tab;
            int i4 = this.baseSize;
            this.baseLimit = i3;
            long j = this.est >>> 1;
            this.est = j;
            return new EntrySpliterator<>(nodeArr, i4, i3, i2, j, this.map);
        }
    }

    public static final class ForwardingNode<V> extends Node<V> {
        final Node<V>[] nextTable;

        public ForwardingNode(Node<V>[] nodeArr) {
            super(-1, 0, null, null);
            this.nextTable = nodeArr;
        }

        @Override // com.intellij.util.containers.ConcurrentIntObjectHashMap.Node
        public Node<V> find(int i, int i2) {
            int length;
            Node<V> nodeTabAt;
            loop0: for (Node<V>[] nodeArr = this.nextTable; nodeArr != null && (length = nodeArr.length) != 0 && (nodeTabAt = ConcurrentIntObjectHashMap.tabAt(nodeArr, (length - 1) & i)) != null; nodeArr = ((ForwardingNode) nodeTabAt).nextTable) {
                while (nodeTabAt.key != i2) {
                    if (nodeTabAt.hash >= 0) {
                        nodeTabAt = nodeTabAt.next;
                        if (nodeTabAt == null) {
                            break loop0;
                        }
                    } else {
                        if (!(nodeTabAt instanceof ForwardingNode)) {
                            return nodeTabAt.find(i, i2);
                        }
                    }
                }
                return nodeTabAt;
            }
            return null;
        }
    }

    public static class Node<V> implements IntObjectMap.Entry<V> {
        final int hash;
        final int key;
        volatile Node<V> next;
        volatile V val;

        public Node(int i, int i2, V v, Node<V> node) {
            this.hash = i;
            this.key = i2;
            this.val = v;
            this.next = node;
        }

        public final boolean equals(Object obj) {
            if (!(obj instanceof IntObjectMap.Entry)) {
                return false;
            }
            IntObjectMap.Entry entry = (IntObjectMap.Entry) obj;
            if (entry.getKey() != this.key) {
                return false;
            }
            Object value = entry.getValue();
            V v = this.val;
            return value == v || value.equals(v);
        }

        public Node<V> find(int i, int i2) {
            while (this.key != i2) {
                this = this.next;
                if (this == null) {
                    return null;
                }
            }
            return this;
        }

        @Override // com.intellij.util.containers.IntObjectMap.Entry
        public final int getKey() {
            return this.key;
        }

        @Override // com.intellij.util.containers.IntObjectMap.Entry
        public final V getValue() {
            return this.val;
        }

        public final int hashCode() {
            return this.val.hashCode() ^ ConcurrentIntObjectHashMap.spread(this.key);
        }

        public final String toString() {
            return this.key + "=" + this.val;
        }
    }

    public static final class ReservationNode<V> extends Node<V> {
        public ReservationNode() {
            super(-3, 0, null, null);
        }
    }

    public static class SimpleEntry<V> implements IntObjectMap.Entry<V> {
        private final int key;
        private final V value;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 1 ? 3 : 2];
            if (i != 1) {
                objArr[0] = "value";
            } else {
                objArr[0] = "com/intellij/util/containers/ConcurrentIntObjectHashMap$SimpleEntry";
            }
            if (i != 1) {
                objArr[1] = "com/intellij/util/containers/ConcurrentIntObjectHashMap$SimpleEntry";
            } else {
                objArr[1] = "getValue";
            }
            if (i != 1) {
                objArr[2] = "<init>";
            }
            String str2 = String.format(str, objArr);
            if (i == 1) {
                throw new IllegalStateException(str2);
            }
        }

        private SimpleEntry(int i, V v) {
            if (v == null) {
                $$$reportNull$$$0(0);
            }
            this.key = i;
            this.value = v;
        }

        @Override // com.intellij.util.containers.IntObjectMap.Entry
        public int getKey() {
            return this.key;
        }

        @Override // com.intellij.util.containers.IntObjectMap.Entry
        public V getValue() {
            V v = this.value;
            if (v == null) {
                $$$reportNull$$$0(1);
            }
            return v;
        }
    }

    public static final class TableStack<V> {
        int index;
        int length;
        TableStack<V> next;
        Node<V>[] tab;
    }

    public static class Traverser<V> {
        int baseIndex;
        int baseLimit;
        final int baseSize;
        int index;
        Node<V> next = null;
        TableStack<V> spare;
        TableStack<V> stack;
        Node<V>[] tab;

        public Traverser(Node<V>[] nodeArr, int i, int i2, int i3) {
            this.tab = nodeArr;
            this.baseSize = i;
            this.index = i2;
            this.baseIndex = i2;
            this.baseLimit = i3;
        }

        private void pushState(Node<V>[] nodeArr, int i, int i2) {
            TableStack<V> tableStack = this.spare;
            if (tableStack != null) {
                this.spare = tableStack.next;
            } else {
                tableStack = new TableStack<>();
            }
            tableStack.tab = nodeArr;
            tableStack.length = i2;
            tableStack.index = i;
            tableStack.next = this.stack;
            this.stack = tableStack;
        }

        private void recoverState(int i) {
            TableStack<V> tableStack;
            while (true) {
                tableStack = this.stack;
                if (tableStack == null) {
                    break;
                }
                int i2 = this.index;
                int i3 = tableStack.length;
                int i4 = i2 + i3;
                this.index = i4;
                if (i4 < i) {
                    break;
                }
                this.index = tableStack.index;
                this.tab = tableStack.tab;
                tableStack.tab = null;
                TableStack<V> tableStack2 = tableStack.next;
                tableStack.next = this.spare;
                this.stack = tableStack2;
                this.spare = tableStack;
                i = i3;
            }
            if (tableStack == null) {
                int i5 = this.index + this.baseSize;
                this.index = i5;
                if (i5 >= i) {
                    int i6 = this.baseIndex + 1;
                    this.baseIndex = i6;
                    this.index = i6;
                }
            }
        }

        /* JADX WARN: Code duplicated, block: B:45:0x004b A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:46:0x0047 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:47:0x0052 A[SYNTHETIC] */
        /* JADX WARN: Code duplicated, block: B:49:0x0006 A[SYNTHETIC] */
        public final Node<V> advance() {
            Node<V>[] nodeArr;
            int length;
            int i;
            int i2;
            Node<V> node = this.next;
            if (node != null) {
                node = node.next;
            }
            while (node == null) {
                if (this.baseIndex >= this.baseLimit || (nodeArr = this.tab) == null || (length = nodeArr.length) <= (i = this.index) || i < 0) {
                    this.next = null;
                    return null;
                }
                Node<V> nodeTabAt = ConcurrentIntObjectHashMap.tabAt(nodeArr, i);
                if (nodeTabAt == null || nodeTabAt.hash >= 0) {
                    node = nodeTabAt;
                    if (this.stack != null) {
                        recoverState(length);
                    } else {
                        i2 = i + this.baseSize;
                        this.index = i2;
                        if (i2 >= length) {
                            int i3 = this.baseIndex + 1;
                            this.baseIndex = i3;
                            this.index = i3;
                        }
                    }
                } else if (nodeTabAt instanceof ForwardingNode) {
                    this.tab = ((ForwardingNode) nodeTabAt).nextTable;
                    pushState(nodeArr, i, length);
                    node = null;
                } else {
                    node = nodeTabAt instanceof TreeBin ? ((TreeBin) nodeTabAt).first : null;
                    if (this.stack != null) {
                        recoverState(length);
                    } else {
                        i2 = i + this.baseSize;
                        this.index = i2;
                        if (i2 >= length) {
                            int i4 = this.baseIndex + 1;
                            this.baseIndex = i4;
                            this.index = i4;
                        }
                    }
                }
            }
            this.next = node;
            return node;
        }
    }

    public static final class TreeBin<V> extends Node<V> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private static final long LOCKSTATE;
        volatile TreeNode<V> first;
        volatile int lockState;
        TreeNode<V> root;
        volatile Thread waiter;

        static {
            try {
                LOCKSTATE = Unsafe.objectFieldOffset(TreeBin.class.getDeclaredField("lockState"));
            } catch (Throwable th) {
                throw new Error(th);
            }
        }

        public TreeBin(TreeNode<V> treeNode) {
            byte b;
            super(-2, 0, null, null);
            this.first = treeNode;
            TreeNode<V> treeNode2 = null;
            while (treeNode != null) {
                TreeNode<V> treeNode3 = (TreeNode) treeNode.next;
                treeNode.right = null;
                treeNode.left = null;
                if (treeNode2 == null) {
                    treeNode.parent = null;
                    treeNode.red = false;
                } else {
                    int i = treeNode.hash;
                    TreeNode<V> treeNode4 = treeNode2;
                    while (true) {
                        int i2 = treeNode4.hash;
                        b = i2 > i ? (byte) -1 : i2 < i ? (byte) 1 : (byte) 0;
                        TreeNode<V> treeNode5 = b <= 0 ? treeNode4.left : treeNode4.right;
                        if (treeNode5 == null) {
                            break;
                        } else {
                            treeNode4 = treeNode5;
                        }
                    }
                    treeNode.parent = treeNode4;
                    if (b <= 0) {
                        treeNode4.left = treeNode;
                    } else {
                        treeNode4.right = treeNode;
                    }
                    treeNode = balanceInsertion(treeNode2, treeNode);
                }
                treeNode2 = treeNode;
                treeNode = treeNode3;
            }
            this.root = treeNode2;
        }

        public static <V> TreeNode<V> balanceDeletion(TreeNode<V> treeNode, TreeNode<V> treeNode2) {
            while (treeNode2 != null && treeNode2 != treeNode) {
                TreeNode<V> treeNode3 = treeNode2.parent;
                if (treeNode3 == null) {
                    treeNode2.red = false;
                    return treeNode2;
                }
                if (treeNode2.red) {
                    treeNode2.red = false;
                    return treeNode;
                }
                TreeNode<V> treeNode4 = treeNode3.left;
                if (treeNode4 == treeNode2) {
                    TreeNode<V> treeNode5 = treeNode3.right;
                    if (treeNode5 != null && treeNode5.red) {
                        treeNode5.red = false;
                        treeNode3.red = true;
                        treeNode = rotateLeft(treeNode, treeNode3);
                        treeNode3 = treeNode2.parent;
                        treeNode5 = treeNode3 == null ? null : treeNode3.right;
                    }
                    if (treeNode5 != null) {
                        TreeNode<V> treeNode6 = treeNode5.left;
                        TreeNode<V> treeNode7 = treeNode5.right;
                        if ((treeNode7 == null || !treeNode7.red) && (treeNode6 == null || !treeNode6.red)) {
                            treeNode5.red = true;
                        } else {
                            if (treeNode7 == null || !treeNode7.red) {
                                if (treeNode6 != null) {
                                    treeNode6.red = false;
                                }
                                treeNode5.red = true;
                                treeNode = rotateRight(treeNode, treeNode5);
                                treeNode3 = treeNode2.parent;
                                treeNode5 = treeNode3 != null ? treeNode3.right : null;
                            }
                            if (treeNode5 != null) {
                                treeNode5.red = treeNode3 == null ? false : treeNode3.red;
                                TreeNode<V> treeNode8 = treeNode5.right;
                                if (treeNode8 != null) {
                                    treeNode8.red = false;
                                }
                            }
                            if (treeNode3 != null) {
                                treeNode3.red = false;
                                treeNode = rotateLeft(treeNode, treeNode3);
                            }
                            treeNode2 = treeNode;
                        }
                    }
                    treeNode2 = treeNode3;
                } else {
                    if (treeNode4 != null && treeNode4.red) {
                        treeNode4.red = false;
                        treeNode3.red = true;
                        treeNode = rotateRight(treeNode, treeNode3);
                        treeNode3 = treeNode2.parent;
                        treeNode4 = treeNode3 == null ? null : treeNode3.left;
                    }
                    if (treeNode4 != null) {
                        TreeNode<V> treeNode9 = treeNode4.left;
                        TreeNode<V> treeNode10 = treeNode4.right;
                        if ((treeNode9 == null || !treeNode9.red) && (treeNode10 == null || !treeNode10.red)) {
                            treeNode4.red = true;
                        } else {
                            if (treeNode9 == null || !treeNode9.red) {
                                if (treeNode10 != null) {
                                    treeNode10.red = false;
                                }
                                treeNode4.red = true;
                                treeNode = rotateLeft(treeNode, treeNode4);
                                treeNode3 = treeNode2.parent;
                                treeNode4 = treeNode3 != null ? treeNode3.left : null;
                            }
                            if (treeNode4 != null) {
                                treeNode4.red = treeNode3 == null ? false : treeNode3.red;
                                TreeNode<V> treeNode11 = treeNode4.left;
                                if (treeNode11 != null) {
                                    treeNode11.red = false;
                                }
                            }
                            if (treeNode3 != null) {
                                treeNode3.red = false;
                                treeNode = rotateRight(treeNode, treeNode3);
                            }
                            treeNode2 = treeNode;
                        }
                    }
                    treeNode2 = treeNode3;
                }
            }
            return treeNode;
        }

        public static <V> TreeNode<V> balanceInsertion(TreeNode<V> treeNode, TreeNode<V> treeNode2) {
            TreeNode<V> treeNode3;
            treeNode2.red = true;
            while (true) {
                TreeNode<V> treeNode4 = treeNode2.parent;
                if (treeNode4 == null) {
                    treeNode2.red = false;
                    return treeNode2;
                }
                if (!treeNode4.red || (treeNode3 = treeNode4.parent) == null) {
                    return treeNode;
                }
                TreeNode<V> treeNode5 = treeNode3.left;
                if (treeNode4 == treeNode5) {
                    TreeNode<V> treeNode6 = treeNode3.right;
                    if (treeNode6 == null || !treeNode6.red) {
                        if (treeNode2 == treeNode4.right) {
                            treeNode = rotateLeft(treeNode, treeNode4);
                            TreeNode<V> treeNode7 = treeNode4.parent;
                            treeNode3 = treeNode7 == null ? null : treeNode7.parent;
                            treeNode4 = treeNode7;
                            treeNode2 = treeNode4;
                        }
                        if (treeNode4 != null) {
                            treeNode4.red = false;
                            if (treeNode3 != null) {
                                treeNode3.red = true;
                                treeNode = rotateRight(treeNode, treeNode3);
                            }
                        }
                    } else {
                        treeNode6.red = false;
                        treeNode4.red = false;
                        treeNode3.red = true;
                        treeNode2 = treeNode3;
                    }
                } else if (treeNode5 == null || !treeNode5.red) {
                    if (treeNode2 == treeNode4.left) {
                        treeNode = rotateRight(treeNode, treeNode4);
                        TreeNode<V> treeNode8 = treeNode4.parent;
                        treeNode3 = treeNode8 == null ? null : treeNode8.parent;
                        treeNode4 = treeNode8;
                        treeNode2 = treeNode4;
                    }
                    if (treeNode4 != null) {
                        treeNode4.red = false;
                        if (treeNode3 != null) {
                            treeNode3.red = true;
                            treeNode = rotateLeft(treeNode, treeNode3);
                        }
                    }
                } else {
                    treeNode5.red = false;
                    treeNode4.red = false;
                    treeNode3.red = true;
                    treeNode2 = treeNode3;
                }
            }
        }

        public static <V> boolean checkInvariants(TreeNode<V> treeNode) {
            TreeNode<V> treeNode2 = treeNode.parent;
            TreeNode<V> treeNode3 = treeNode.left;
            TreeNode<V> treeNode4 = treeNode.right;
            TreeNode<V> treeNode5 = treeNode.prev;
            TreeNode treeNode6 = (TreeNode) treeNode.next;
            if (treeNode5 != null && treeNode5.next != treeNode) {
                return false;
            }
            if (treeNode6 != null && treeNode6.prev != treeNode) {
                return false;
            }
            if (treeNode2 != null && treeNode != treeNode2.left && treeNode != treeNode2.right) {
                return false;
            }
            if (treeNode3 != null && (treeNode3.parent != treeNode || treeNode3.hash > treeNode.hash)) {
                return false;
            }
            if (treeNode4 != null && (treeNode4.parent != treeNode || treeNode4.hash < treeNode.hash)) {
                return false;
            }
            if (treeNode.red && treeNode3 != null && treeNode3.red && treeNode4 != null && treeNode4.red) {
                return false;
            }
            if (treeNode3 == null || checkInvariants(treeNode3)) {
                return treeNode4 == null || checkInvariants(treeNode4);
            }
            return false;
        }

        private final void contendedLock() {
            boolean z = false;
            while (true) {
                int i = this.lockState;
                if ((i & (-3)) == 0) {
                    if (Unsafe.compareAndSwapInt(this, LOCKSTATE, i, 1)) {
                        break;
                    }
                } else if ((i & 2) == 0) {
                    if (Unsafe.compareAndSwapInt(this, LOCKSTATE, i, i | 2)) {
                        this.waiter = Thread.currentThread();
                        z = true;
                    }
                } else if (z) {
                    LockSupport.park(this);
                }
            }
            if (z) {
                this.waiter = null;
            }
        }

        private static int getAndAddInt(Object obj, long j, int i) {
            try {
                return Unsafe.getAndAddInt(obj, j, i);
            } catch (Throwable th) {
                rc6.a(th);
                return 0;
            }
        }

        private final void lockRoot() {
            if (Unsafe.compareAndSwapInt(this, LOCKSTATE, 0, 1)) {
                return;
            }
            contendedLock();
        }

        public static <V> TreeNode<V> rotateLeft(TreeNode<V> treeNode, TreeNode<V> treeNode2) {
            TreeNode<V> treeNode3;
            if (treeNode2 != null && (treeNode3 = treeNode2.right) != null) {
                TreeNode<V> treeNode4 = treeNode3.left;
                treeNode2.right = treeNode4;
                if (treeNode4 != null) {
                    treeNode4.parent = treeNode2;
                }
                TreeNode<V> treeNode5 = treeNode2.parent;
                treeNode3.parent = treeNode5;
                if (treeNode5 == null) {
                    treeNode3.red = false;
                    treeNode = treeNode3;
                } else if (treeNode5.left == treeNode2) {
                    treeNode5.left = treeNode3;
                } else {
                    treeNode5.right = treeNode3;
                }
                treeNode3.left = treeNode2;
                treeNode2.parent = treeNode3;
            }
            return treeNode;
        }

        public static <V> TreeNode<V> rotateRight(TreeNode<V> treeNode, TreeNode<V> treeNode2) {
            TreeNode<V> treeNode3;
            if (treeNode2 != null && (treeNode3 = treeNode2.left) != null) {
                TreeNode<V> treeNode4 = treeNode3.right;
                treeNode2.left = treeNode4;
                if (treeNode4 != null) {
                    treeNode4.parent = treeNode2;
                }
                TreeNode<V> treeNode5 = treeNode2.parent;
                treeNode3.parent = treeNode5;
                if (treeNode5 == null) {
                    treeNode3.red = false;
                    treeNode = treeNode3;
                } else if (treeNode5.right == treeNode2) {
                    treeNode5.right = treeNode3;
                } else {
                    treeNode5.left = treeNode3;
                }
                treeNode3.right = treeNode2;
                treeNode2.parent = treeNode3;
            }
            return treeNode;
        }

        private final void unlockRoot() {
            this.lockState = 0;
        }

        @Override // com.intellij.util.containers.ConcurrentIntObjectHashMap.Node
        public Node<V> find(int i, int i2) {
            Thread thread;
            Node<V> node = this.first;
            while (true) {
                TreeNode<V> treeNodeFindTreeNode = null;
                if (node == null) {
                    return null;
                }
                int i3 = this.lockState;
                if ((i3 & 3) != 0) {
                    if (node.key == i2) {
                        return node;
                    }
                    node = node.next;
                } else if (Unsafe.compareAndSwapInt(this, LOCKSTATE, i3, i3 + 4)) {
                    try {
                        TreeNode<V> treeNode = this.root;
                        if (treeNode != null) {
                            treeNodeFindTreeNode = treeNode.findTreeNode(i, i2);
                        }
                        return treeNodeFindTreeNode;
                    } finally {
                        if (getAndAddInt(this, LOCKSTATE, -4) == 6 && (thread = this.waiter) != null) {
                            LockSupport.unpark(thread);
                        }
                    }
                }
            }
        }

        public TreeNode<V> putTreeVal(int i, int i2, V v) {
            byte b;
            TreeNode<V> treeNodeFindTreeNode;
            TreeNode<V> treeNodeFindTreeNode2;
            TreeNode<V> treeNode = this.root;
            boolean z = false;
            while (treeNode != null) {
                int i3 = treeNode.hash;
                if (i3 > i) {
                    b = -1;
                } else if (i3 < i) {
                    b = 1;
                } else {
                    if (treeNode.key == i2) {
                        return treeNode;
                    }
                    if (!z) {
                        TreeNode<V> treeNode2 = treeNode.left;
                        if (treeNode2 != null && (treeNodeFindTreeNode2 = treeNode2.findTreeNode(i, i2)) != null) {
                            return treeNodeFindTreeNode2;
                        }
                        TreeNode<V> treeNode3 = treeNode.right;
                        if (treeNode3 != null && (treeNodeFindTreeNode = treeNode3.findTreeNode(i, i2)) != null) {
                            return treeNodeFindTreeNode;
                        }
                        z = true;
                    }
                    b = 0;
                }
                TreeNode<V> treeNode4 = b <= 0 ? treeNode.left : treeNode.right;
                if (treeNode4 == null) {
                    TreeNode<V> treeNode5 = this.first;
                    TreeNode<V> treeNode6 = new TreeNode<>(i, i2, v, treeNode5, treeNode);
                    this.first = treeNode6;
                    if (treeNode5 != null) {
                        treeNode5.prev = treeNode6;
                    }
                    if (b <= 0) {
                        treeNode.left = treeNode6;
                    } else {
                        treeNode.right = treeNode6;
                    }
                    if (!treeNode.red) {
                        treeNode6.red = true;
                        return null;
                    }
                    lockRoot();
                    try {
                        this.root = balanceInsertion(this.root, treeNode6);
                        return null;
                    } finally {
                        unlockRoot();
                    }
                }
                treeNode = treeNode4;
            }
            TreeNode<V> treeNode7 = new TreeNode<>(i, i2, v, null, null);
            this.root = treeNode7;
            this.first = treeNode7;
            return null;
        }

        /* JADX WARN: Code duplicated, block: B:57:0x008d A[PHI: r0
          0x008d: PHI (r0v4 com.intellij.util.containers.ConcurrentIntObjectHashMap$TreeNode<V>) = 
          (r0v3 com.intellij.util.containers.ConcurrentIntObjectHashMap$TreeNode<V>)
          (r0v12 com.intellij.util.containers.ConcurrentIntObjectHashMap$TreeNode<V>)
         binds: [B:55:0x0089, B:51:0x0082] A[DONT_GENERATE, DONT_INLINE]] */
        public final boolean removeTreeNode(TreeNode<V> treeNode) {
            TreeNode<V> treeNode2;
            TreeNode<V> treeNode3;
            TreeNode<V> treeNode4 = (TreeNode) treeNode.next;
            TreeNode<V> treeNode5 = treeNode.prev;
            if (treeNode5 == null) {
                this.first = treeNode4;
            } else {
                treeNode5.next = treeNode4;
            }
            if (treeNode4 != null) {
                treeNode4.prev = treeNode5;
            }
            if (this.first == null) {
                this.root = null;
                return true;
            }
            TreeNode<V> treeNodeBalanceDeletion = this.root;
            if (treeNodeBalanceDeletion == null || treeNodeBalanceDeletion.right == null || (treeNode2 = treeNodeBalanceDeletion.left) == null || treeNode2.left == null) {
                return true;
            }
            lockRoot();
            try {
                TreeNode<V> treeNode6 = treeNode.left;
                TreeNode<V> treeNode7 = treeNode.right;
                if (treeNode6 != null && treeNode7 != null) {
                    TreeNode<V> treeNode8 = treeNode7;
                    while (true) {
                        TreeNode<V> treeNode9 = treeNode8.left;
                        if (treeNode9 == null) {
                            break;
                        }
                        treeNode8 = treeNode9;
                    }
                    boolean z = treeNode8.red;
                    treeNode8.red = treeNode.red;
                    treeNode.red = z;
                    TreeNode<V> treeNode10 = treeNode8.right;
                    TreeNode<V> treeNode11 = treeNode.parent;
                    if (treeNode8 == treeNode7) {
                        treeNode.parent = treeNode8;
                        treeNode8.right = treeNode;
                    } else {
                        TreeNode<V> treeNode12 = treeNode8.parent;
                        treeNode.parent = treeNode12;
                        if (treeNode12 != null) {
                            if (treeNode8 == treeNode12.left) {
                                treeNode12.left = treeNode;
                            } else {
                                treeNode12.right = treeNode;
                            }
                        }
                        treeNode8.right = treeNode7;
                        treeNode7.parent = treeNode8;
                    }
                    treeNode.left = null;
                    treeNode.right = treeNode10;
                    if (treeNode10 != null) {
                        treeNode10.parent = treeNode;
                    }
                    treeNode8.left = treeNode6;
                    treeNode6.parent = treeNode8;
                    treeNode8.parent = treeNode11;
                    if (treeNode11 == null) {
                        treeNodeBalanceDeletion = treeNode8;
                    } else if (treeNode == treeNode11.left) {
                        treeNode11.left = treeNode8;
                    } else {
                        treeNode11.right = treeNode8;
                    }
                    if (treeNode10 != null) {
                        treeNode6 = treeNode10;
                    } else {
                        treeNode6 = treeNode;
                    }
                } else if (treeNode6 == null) {
                    if (treeNode7 != null) {
                        treeNode6 = treeNode7;
                    } else {
                        treeNode6 = treeNode;
                    }
                }
                if (treeNode6 != treeNode) {
                    TreeNode<V> treeNode13 = treeNode.parent;
                    treeNode6.parent = treeNode13;
                    if (treeNode13 == null) {
                        treeNodeBalanceDeletion = treeNode6;
                    } else if (treeNode == treeNode13.left) {
                        treeNode13.left = treeNode6;
                    } else {
                        treeNode13.right = treeNode6;
                    }
                    treeNode.parent = null;
                    treeNode.right = null;
                    treeNode.left = null;
                }
                if (!treeNode.red) {
                    treeNodeBalanceDeletion = balanceDeletion(treeNodeBalanceDeletion, treeNode6);
                }
                this.root = treeNodeBalanceDeletion;
                if (treeNode == treeNode6 && (treeNode3 = treeNode.parent) != null) {
                    if (treeNode == treeNode3.left) {
                        treeNode3.left = null;
                    } else if (treeNode == treeNode3.right) {
                        treeNode3.right = null;
                    }
                    treeNode.parent = null;
                }
                return false;
            } finally {
                unlockRoot();
            }
        }
    }

    public static final class TreeNode<V> extends Node<V> {
        TreeNode<V> left;
        TreeNode<V> parent;
        TreeNode<V> prev;
        boolean red;
        TreeNode<V> right;

        public TreeNode(int i, int i2, V v, Node<V> node, TreeNode<V> treeNode) {
            super(i, i2, v, node);
            this.parent = treeNode;
        }

        @Override // com.intellij.util.containers.ConcurrentIntObjectHashMap.Node
        public Node<V> find(int i, int i2) {
            return findTreeNode(i, i2);
        }

        public final TreeNode<V> findTreeNode(int i, int i2) {
            TreeNode<V> treeNodeFindTreeNode;
            do {
                TreeNode<V> treeNode = this.left;
                TreeNode<V> treeNode2 = this.right;
                int i3 = this.hash;
                if (i3 <= i) {
                    if (i3 >= i) {
                        if (this.key == i2) {
                            return this;
                        }
                        if (treeNode != null) {
                            if (treeNode2 != null && (treeNodeFindTreeNode = treeNode2.findTreeNode(i, i2)) != null) {
                                return treeNodeFindTreeNode;
                            }
                            this = treeNode;
                        }
                    }
                    this = treeNode2;
                } else {
                    this = treeNode;
                }
            } while (this != null);
            return null;
        }
    }

    public static final class ValueIterator<V> extends BaseIterator<V> implements Enumeration<V>, Iterator<V> {
        public ValueIterator(Node<V>[] nodeArr, int i, int i2, int i3, ConcurrentIntObjectHashMap<V> concurrentIntObjectHashMap) {
            super(nodeArr, i, i2, i3, concurrentIntObjectHashMap);
        }

        @Override // java.util.Iterator
        public final V next() {
            Node<V> node = this.next;
            if (node == null) {
                z0e.a();
                return null;
            }
            V v = node.val;
            this.lastReturned = node;
            advance();
            return v;
        }

        @Override // java.util.Enumeration
        public final V nextElement() {
            return next();
        }
    }

    public static final class ValueSpliterator<V> extends Traverser<V> implements Spliterator<V> {
        long est;

        public ValueSpliterator(Node<V>[] nodeArr, int i, int i2, int i3, long j) {
            super(nodeArr, i, i2, i3);
            this.est = j;
        }

        @Override // java.util.Spliterator
        public int characteristics() {
            return 4352;
        }

        @Override // java.util.Spliterator
        public long estimateSize() {
            return this.est;
        }

        @Override // java.util.Spliterator
        public void forEachRemaining(Consumer<? super V> consumer) {
            consumer.getClass();
            while (true) {
                Node<V> nodeAdvance = advance();
                if (nodeAdvance == null) {
                    return;
                } else {
                    consumer.accept(nodeAdvance.val);
                }
            }
        }

        @Override // java.util.Spliterator
        public boolean tryAdvance(Consumer<? super V> consumer) {
            consumer.getClass();
            Node<V> nodeAdvance = advance();
            if (nodeAdvance == null) {
                return false;
            }
            consumer.accept(nodeAdvance.val);
            return true;
        }

        @Override // java.util.Spliterator
        public ValueSpliterator<V> trySplit() {
            int i = this.baseIndex;
            int i2 = this.baseLimit;
            int i3 = (i + i2) >>> 1;
            if (i3 <= i) {
                return null;
            }
            Node<V>[] nodeArr = this.tab;
            int i4 = this.baseSize;
            this.baseLimit = i3;
            long j = this.est >>> 1;
            this.est = j;
            return new ValueSpliterator<>(nodeArr, i4, i3, i2, j);
        }
    }

    public static final class ValuesView<V> extends CollectionView<V, V> implements Collection<V> {
        public ValuesView(ConcurrentIntObjectHashMap<V> concurrentIntObjectHashMap) {
            super(concurrentIntObjectHashMap);
        }

        @Override // java.util.Collection
        public final boolean add(V v) {
            throw new UnsupportedOperationException();
        }

        @Override // java.util.Collection
        public final boolean addAll(Collection<? extends V> collection) {
            throw new UnsupportedOperationException();
        }

        @Override // com.intellij.util.containers.ConcurrentIntObjectHashMap.CollectionView, java.util.Collection
        public final boolean contains(Object obj) {
            return this.map.containsValue(obj);
        }

        @Override // java.lang.Iterable
        public void forEach(Consumer<? super V> consumer) {
            consumer.getClass();
            Node<V>[] nodeArr = this.map.table;
            if (nodeArr == null) {
                return;
            }
            Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
            while (true) {
                Node<V> nodeAdvance = traverser.advance();
                if (nodeAdvance == null) {
                    return;
                } else {
                    consumer.accept(nodeAdvance.val);
                }
            }
        }

        @Override // com.intellij.util.containers.ConcurrentIntObjectHashMap.CollectionView, java.util.Collection, java.lang.Iterable
        public final Iterator<V> iterator() {
            ConcurrentIntObjectHashMap<V> concurrentIntObjectHashMap = this.map;
            Node<V>[] nodeArr = concurrentIntObjectHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            return new ValueIterator(nodeArr, length, 0, length, concurrentIntObjectHashMap);
        }

        @Override // com.intellij.util.containers.ConcurrentIntObjectHashMap.CollectionView, java.util.Collection
        public final boolean remove(Object obj) {
            if (obj == null) {
                return false;
            }
            Iterator<V> it = iterator();
            while (it.hasNext()) {
                if (obj.equals(it.next())) {
                    it.remove();
                    return true;
                }
            }
            return false;
        }

        @Override // com.intellij.util.containers.ConcurrentIntObjectHashMap.CollectionView, java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            collection.getClass();
            Iterator<V> it = iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (collection.contains(it.next())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        @Override // java.util.Collection, java.lang.Iterable
        public Spliterator<V> spliterator() {
            ConcurrentIntObjectHashMap<V> concurrentIntObjectHashMap = this.map;
            long jSumCount = concurrentIntObjectHashMap.sumCount();
            Node<V>[] nodeArr = concurrentIntObjectHashMap.table;
            int length = nodeArr == null ? 0 : nodeArr.length;
            return new ValueSpliterator(nodeArr, length, 0, length, jSumCount < 0 ? 0L : jSumCount);
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 7 || i == 9 || i == 10) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 7 || i == 9 || i == 10) ? 2 : 3];
        switch (i) {
            case 4:
                objArr[0] = "oldValue";
                break;
            case 5:
                objArr[0] = "newValue";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            default:
                objArr[0] = "value";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 9:
            case 10:
                objArr[0] = "com/intellij/util/containers/ConcurrentIntObjectHashMap";
                break;
            case 8:
                objArr[0] = "defaultValue";
                break;
        }
        if (i == 7) {
            objArr[1] = "keys";
        } else if (i == 9 || i == 10) {
            objArr[1] = "cacheOrGet";
        } else {
            objArr[1] = "com/intellij/util/containers/ConcurrentIntObjectHashMap";
        }
        switch (i) {
            case 1:
                objArr[2] = "put";
                break;
            case 2:
                objArr[2] = "putIfAbsent";
                break;
            case 3:
                objArr[2] = "remove";
                break;
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "replace";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 9:
            case 10:
                break;
            case 8:
                objArr[2] = "cacheOrGet";
                break;
            default:
                objArr[2] = "containsValue";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 7 && i != 9 && i != 10) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    static {
        try {
            SIZECTL = Unsafe.objectFieldOffset(ConcurrentIntObjectHashMap.class.getDeclaredField("sizeCtl"));
            TRANSFERINDEX = Unsafe.objectFieldOffset(ConcurrentIntObjectHashMap.class.getDeclaredField("transferIndex"));
            BASECOUNT = Unsafe.objectFieldOffset(ConcurrentIntObjectHashMap.class.getDeclaredField("baseCount"));
            CELLSBUSY = Unsafe.objectFieldOffset(ConcurrentIntObjectHashMap.class.getDeclaredField("cellsBusy"));
            CELLVALUE = Unsafe.objectFieldOffset(CounterCell.class.getDeclaredField("value"));
            ABASE = Unsafe.arrayBaseOffset(Node[].class);
            int iArrayIndexScale = Unsafe.arrayIndexScale(Node[].class);
            if (((iArrayIndexScale - 1) & iArrayIndexScale) != 0) {
                throw new Error("data type scale not a power of two");
            }
            ASHIFT = 31 - Integer.numberOfLeadingZeros(iArrayIndexScale);
        } catch (Throwable th) {
            throw new Error(th);
        }
    }

    /* JADX WARN: Code duplicated, block: B:21:0x003a A[LOOP:0: B:21:0x003a->B:44:0x0081, LOOP_START, PHI: r6
      0x003a: PHI (r6v3 long) = (r6v2 long), (r6v4 long) binds: [B:20:0x0038, B:44:0x0081] A[DONT_GENERATE, DONT_INLINE]] */
    /* JADX WARN: Code duplicated, block: B:45:0x0086 A[ORIG_RETURN, RETURN] */
    private final void addCount(long j, int i) {
        ConcurrentIntObjectHashMap<V> concurrentIntObjectHashMap;
        int length;
        CounterCell counterCell;
        long jSumCount;
        int i2;
        Node<V>[] nodeArr;
        int length2;
        Node<V>[] nodeArr2;
        CounterCell[] counterCellArr = this.counterCells;
        if (counterCellArr == null) {
            long j2 = BASECOUNT;
            long j3 = this.baseCount;
            jSumCount = j3 + j;
            concurrentIntObjectHashMap = this;
            if (!Unsafe.compareAndSwapLong(concurrentIntObjectHashMap, j2, j3, jSumCount)) {
            }
            if (i >= 0) {
                return;
            }
            while (true) {
                i2 = concurrentIntObjectHashMap.sizeCtl;
                if (jSumCount >= i2 || (nodeArr = concurrentIntObjectHashMap.table) == null || (length2 = nodeArr.length) >= 1073741824) {
                    return;
                }
                int iResizeStamp = resizeStamp(length2) << 16;
                if (i2 < 0) {
                    if (i2 == 65535 + iResizeStamp || i2 == iResizeStamp + 1 || (nodeArr2 = concurrentIntObjectHashMap.nextTable) == null || concurrentIntObjectHashMap.transferIndex <= 0) {
                        return;
                    }
                    if (Unsafe.compareAndSwapInt(concurrentIntObjectHashMap, SIZECTL, i2, i2 + 1)) {
                        concurrentIntObjectHashMap.transfer(nodeArr, nodeArr2);
                    }
                } else if (Unsafe.compareAndSwapInt(concurrentIntObjectHashMap, SIZECTL, i2, iResizeStamp + 2)) {
                    concurrentIntObjectHashMap.transfer(nodeArr, null);
                }
                jSumCount = concurrentIntObjectHashMap.sumCount();
            }
        } else {
            concurrentIntObjectHashMap = this;
        }
        boolean z = true;
        if (counterCellArr != null && (length = counterCellArr.length - 1) >= 0 && (counterCell = counterCellArr[length & ThreadLocalRandom.getProbe()]) != null) {
            long j4 = CELLVALUE;
            long j5 = counterCell.value;
            boolean zCompareAndSwapLong = Unsafe.compareAndSwapLong(counterCell, j4, j5, j5 + j);
            if (!zCompareAndSwapLong) {
                z = zCompareAndSwapLong;
            } else {
                if (i <= 1) {
                    return;
                }
                jSumCount = concurrentIntObjectHashMap.sumCount();
                if (i >= 0) {
                    return;
                }
                while (true) {
                    i2 = concurrentIntObjectHashMap.sizeCtl;
                    if (jSumCount >= i2) {
                        return;
                    } else {
                        return;
                    }
                    jSumCount = concurrentIntObjectHashMap.sumCount();
                }
            }
        }
        concurrentIntObjectHashMap.fullAddCount(j, z);
    }

    public static <V> boolean casTabAt(Node<V>[] nodeArr, int i, Node<V> node) {
        try {
            return Unsafe.compareAndSwapObject(nodeArr, (((long) i) << ASHIFT) + ABASE, null, node);
        } catch (Throwable th) {
            rc6.a(th);
            return false;
        }
    }

    /* JADX WARN: Code duplicated, block: B:30:0x0052  */
    private final void fullAddCount(long j, boolean z) {
        int probe;
        boolean z2;
        CounterCell[] counterCellArr;
        boolean z3;
        int length;
        boolean z4;
        int length2;
        ConcurrentIntObjectHashMap<V> concurrentIntObjectHashMap = this;
        int probe2 = ThreadLocalRandom.getProbe();
        if (probe2 == 0) {
            ThreadLocalRandom.localInit();
            probe = ThreadLocalRandom.getProbe();
            z2 = true;
        } else {
            probe = probe2;
            z2 = z;
        }
        int iAdvanceProbe = probe;
        while (true) {
            boolean z5 = false;
            while (true) {
                counterCellArr = concurrentIntObjectHashMap.counterCells;
                if (counterCellArr == null || (length = counterCellArr.length) <= 0) {
                    if (concurrentIntObjectHashMap.cellsBusy == 0 && concurrentIntObjectHashMap.counterCells == counterCellArr && Unsafe.compareAndSwapInt(concurrentIntObjectHashMap, CELLSBUSY, 0, 1)) {
                        try {
                            if (concurrentIntObjectHashMap.counterCells == counterCellArr) {
                                CounterCell[] counterCellArr2 = new CounterCell[2];
                                counterCellArr2[iAdvanceProbe & 1] = new CounterCell(j);
                                concurrentIntObjectHashMap.counterCells = counterCellArr2;
                                z3 = true;
                            } else {
                                z3 = false;
                            }
                            concurrentIntObjectHashMap.cellsBusy = 0;
                            if (z3) {
                                return;
                            }
                        } catch (Throwable th) {
                            concurrentIntObjectHashMap.cellsBusy = 0;
                            throw th;
                        }
                    } else {
                        long j2 = BASECOUNT;
                        long j3 = concurrentIntObjectHashMap.baseCount;
                        if (Unsafe.compareAndSwapLong(concurrentIntObjectHashMap, j2, j3, j3 + j)) {
                            return;
                        }
                    }
                    concurrentIntObjectHashMap = this;
                } else {
                    CounterCell counterCell = counterCellArr[(length - 1) & iAdvanceProbe];
                    if (counterCell != null) {
                        if (z2) {
                            long j4 = CELLVALUE;
                            long j5 = counterCell.value;
                            if (!Unsafe.compareAndSwapLong(counterCell, j4, j5, j5 + j)) {
                                if (concurrentIntObjectHashMap.counterCells == counterCellArr && length < NCPU) {
                                    if (!z5) {
                                        z5 = true;
                                    } else if (concurrentIntObjectHashMap.cellsBusy == 0 && Unsafe.compareAndSwapInt(concurrentIntObjectHashMap, CELLSBUSY, 0, 1)) {
                                        break;
                                    }
                                }
                            } else {
                                return;
                            }
                        } else {
                            z2 = true;
                        }
                        iAdvanceProbe = ThreadLocalRandom.advanceProbe(iAdvanceProbe);
                        concurrentIntObjectHashMap = this;
                    } else if (concurrentIntObjectHashMap.cellsBusy == 0) {
                        CounterCell counterCell2 = new CounterCell(j);
                        if (concurrentIntObjectHashMap.cellsBusy == 0 && Unsafe.compareAndSwapInt(concurrentIntObjectHashMap, CELLSBUSY, 0, 1)) {
                            try {
                                CounterCell[] counterCellArr3 = concurrentIntObjectHashMap.counterCells;
                                if (counterCellArr3 == null || (length2 = counterCellArr3.length) <= 0) {
                                    z4 = false;
                                } else {
                                    int i = (length2 - 1) & iAdvanceProbe;
                                    if (counterCellArr3[i] == null) {
                                        counterCellArr3[i] = counterCell2;
                                        z4 = true;
                                    } else {
                                        z4 = false;
                                    }
                                }
                                concurrentIntObjectHashMap.cellsBusy = 0;
                                if (z4) {
                                    return;
                                }
                            } catch (Throwable th2) {
                                concurrentIntObjectHashMap.cellsBusy = 0;
                                throw th2;
                            }
                        }
                    }
                    z5 = false;
                    iAdvanceProbe = ThreadLocalRandom.advanceProbe(iAdvanceProbe);
                    concurrentIntObjectHashMap = this;
                }
            }
            try {
                if (concurrentIntObjectHashMap.counterCells == counterCellArr) {
                    concurrentIntObjectHashMap.counterCells = (CounterCell[]) Arrays.copyOf(counterCellArr, length << 1);
                }
                concurrentIntObjectHashMap.cellsBusy = 0;
            } catch (Throwable th3) {
                concurrentIntObjectHashMap.cellsBusy = 0;
                throw th3;
            }
        }
    }

    private final Node<V>[] initTable() {
        while (true) {
            Node<V>[] nodeArr = this.table;
            if (nodeArr != null && nodeArr.length != 0) {
                return nodeArr;
            }
            int i = this.sizeCtl;
            if (i < 0) {
                Thread.yield();
            } else if (Unsafe.compareAndSwapInt(this, SIZECTL, i, -1)) {
                try {
                    Node<V>[] nodeArr2 = this.table;
                    if (nodeArr2 == null || nodeArr2.length == 0) {
                        int i2 = i > 0 ? i : 16;
                        Node<V>[] nodeArr3 = new Node[i2];
                        this.table = nodeArr3;
                        i = i2 - (i2 >>> 2);
                        nodeArr2 = nodeArr3;
                    }
                    return nodeArr2;
                } finally {
                    this.sizeCtl = i;
                }
            }
        }
    }

    private static boolean isEqual(int i, int i2) {
        return i == i2;
    }

    public static final int resizeStamp(int i) {
        return Integer.numberOfLeadingZeros(i) | 32768;
    }

    public static <V> void setTabAt(Node<V>[] nodeArr, int i, Node<V> node) {
        try {
            Unsafe.putObjectVolatile(nodeArr, (((long) i) << ASHIFT) + ABASE, node);
        } catch (Throwable th) {
            rc6.a(th);
        }
    }

    public static final int spread(int i) {
        return (i ^ (i >>> 16)) & Integer.MAX_VALUE;
    }

    public static <V> Node<V> tabAt(Node<V>[] nodeArr, int i) {
        try {
            return (Node) Unsafe.getObjectVolatile(nodeArr, (((long) i) << ASHIFT) + ABASE);
        } catch (Throwable th) {
            rc6.a(th);
            return null;
        }
    }

    private static final int tableSizeFor(int i) {
        int iNumberOfLeadingZeros = (-1) >>> Integer.numberOfLeadingZeros(i - 1);
        if (iNumberOfLeadingZeros < 0) {
            return 1;
        }
        return iNumberOfLeadingZeros >= 1073741824 ? IOUtil.GiB : iNumberOfLeadingZeros + 1;
    }

    private final void transfer(Node<V>[] nodeArr, Node<V>[] nodeArr2) {
        Node<V>[] nodeArr3;
        char c;
        boolean z;
        int i;
        int i2;
        int i3;
        int i4;
        int i5;
        Node<V> node;
        Node<V> node2;
        int length = nodeArr.length;
        int i6 = NCPU;
        boolean z2 = true;
        int i7 = i6 > 1 ? (length >>> 3) / i6 : length;
        char c2 = 16;
        if (i7 < 16) {
            i7 = 16;
        }
        if (nodeArr2 == null) {
            try {
                nodeArr3 = new Node[length << 1];
                this.nextTable = nodeArr3;
                this.transferIndex = length;
            } catch (Throwable unused) {
                this.sizeCtl = Integer.MAX_VALUE;
                return;
            }
        } else {
            nodeArr3 = nodeArr2;
        }
        int length2 = nodeArr3.length;
        ForwardingNode forwardingNode = new ForwardingNode(nodeArr3);
        boolean zCasTabAt = true;
        int i8 = 0;
        int i9 = 0;
        boolean z3 = false;
        while (true) {
            if (zCasTabAt) {
                i8--;
                if (i8 >= i9 || z3) {
                    c = c2;
                    z = z2;
                } else {
                    int i10 = this.transferIndex;
                    if (i10 <= 0) {
                        c = c2;
                        z = z2;
                        i8 = -1;
                    } else {
                        c = c2;
                        z = z2;
                        long j = TRANSFERINDEX;
                        int i11 = i10 > i7 ? i10 - i7 : 0;
                        if (Unsafe.compareAndSwapInt(this, j, i10, i11)) {
                            i9 = i11;
                            i8 = i10 - 1;
                        }
                        z2 = z;
                        c2 = c;
                    }
                }
                zCasTabAt = false;
                z2 = z;
                c2 = c;
            } else {
                char c3 = c2;
                boolean z4 = z2;
                if (i8 < 0 || i8 >= length || (i5 = i8 + length) >= length2) {
                    i = length;
                    i2 = i7;
                    i3 = length2;
                    i4 = i9;
                    if (z3) {
                        this.nextTable = null;
                        this.table = nodeArr3;
                        this.sizeCtl = (i << 1) - (i >>> 1);
                        return;
                    } else {
                        int i12 = this.sizeCtl;
                        if (Unsafe.compareAndSwapInt(this, SIZECTL, i12, i12 - 1)) {
                            if (i12 - 2 != (resizeStamp(i) << 16)) {
                                return;
                            }
                            zCasTabAt = z4;
                            z3 = zCasTabAt;
                            i8 = i;
                        }
                    }
                } else {
                    Node<V> nodeTabAt = tabAt(nodeArr, i8);
                    if (nodeTabAt == null) {
                        i = length;
                        zCasTabAt = casTabAt(nodeArr, i8, forwardingNode);
                        i2 = i7;
                        i3 = length2;
                        i4 = i9;
                    } else {
                        int i13 = nodeTabAt.hash;
                        if (i13 == -1) {
                            i = length;
                            i2 = i7;
                            i3 = length2;
                            i4 = i9;
                            zCasTabAt = z4;
                        } else {
                            synchronized (nodeTabAt) {
                                try {
                                    if (tabAt(nodeArr, i8) == nodeTabAt) {
                                        if (i13 >= 0) {
                                            int i14 = i13 & length;
                                            Node<V> node3 = nodeTabAt;
                                            for (Node<V> node4 = nodeTabAt.next; node4 != null; node4 = node4.next) {
                                                int i15 = node4.hash & length;
                                                if (i15 != i14) {
                                                    i14 = i15;
                                                    node3 = node4;
                                                }
                                            }
                                            if (i14 == 0) {
                                                node2 = node3;
                                                node = null;
                                            } else {
                                                node = node3;
                                                node2 = null;
                                            }
                                            Node<V> node5 = nodeTabAt;
                                            Node<V> node6 = node;
                                            while (node5 != node3) {
                                                int i16 = length;
                                                int i17 = node5.hash;
                                                int i18 = i7;
                                                int i19 = node5.key;
                                                int i20 = length2;
                                                V v = node5.val;
                                                if ((i17 & i16) == 0) {
                                                    node2 = new Node<>(i17, i19, v, node2);
                                                } else {
                                                    node6 = new Node<>(i17, i19, v, node6);
                                                }
                                                node5 = node5.next;
                                                length = i16;
                                                i7 = i18;
                                                length2 = i20;
                                                i9 = i9;
                                            }
                                            i = length;
                                            i2 = i7;
                                            i3 = length2;
                                            i4 = i9;
                                            setTabAt(nodeArr3, i8, node2);
                                            setTabAt(nodeArr3, i5, node6);
                                            setTabAt(nodeArr, i8, forwardingNode);
                                        } else {
                                            i = length;
                                            i2 = i7;
                                            i3 = length2;
                                            i4 = i9;
                                            if (nodeTabAt instanceof TreeBin) {
                                                TreeBin treeBin = (TreeBin) nodeTabAt;
                                                Node node7 = treeBin.first;
                                                TreeNode<V> treeNode = null;
                                                TreeNode<V> treeNode2 = null;
                                                TreeNode<V> treeNode3 = null;
                                                TreeNode<V> treeNode4 = null;
                                                int i21 = 0;
                                                int i22 = 0;
                                                while (node7 != null) {
                                                    TreeBin treeBin2 = treeBin;
                                                    int i23 = node7.hash;
                                                    TreeNode<V> treeNode5 = new TreeNode<>(i23, node7.key, node7.val, null, null);
                                                    if ((i23 & i) == 0) {
                                                        treeNode5.prev = treeNode4;
                                                        if (treeNode4 == null) {
                                                            treeNode = treeNode5;
                                                        } else {
                                                            treeNode4.next = treeNode5;
                                                        }
                                                        i21++;
                                                        treeNode4 = treeNode5;
                                                    } else {
                                                        treeNode5.prev = treeNode3;
                                                        if (treeNode3 == null) {
                                                            treeNode2 = treeNode5;
                                                        } else {
                                                            treeNode3.next = treeNode5;
                                                        }
                                                        i22++;
                                                        treeNode3 = treeNode5;
                                                    }
                                                    node7 = node7.next;
                                                    treeBin = treeBin2;
                                                }
                                                Node<V> node8 = treeBin;
                                                Node<V> nodeUntreeify = i21 <= 6 ? untreeify(treeNode) : i22 != 0 ? new TreeBin<>(treeNode) : node8;
                                                Node<V> nodeUntreeify2 = i22 <= 6 ? untreeify(treeNode2) : i21 != 0 ? new TreeBin<>(treeNode2) : node8;
                                                setTabAt(nodeArr3, i8, nodeUntreeify);
                                                setTabAt(nodeArr3, i5, nodeUntreeify2);
                                                setTabAt(nodeArr, i8, forwardingNode);
                                            } else if (nodeTabAt instanceof ReservationNode) {
                                                throw new IllegalStateException("Recursive update");
                                            }
                                        }
                                        zCasTabAt = z4;
                                    } else {
                                        i = length;
                                        i2 = i7;
                                        i3 = length2;
                                        i4 = i9;
                                    }
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                        }
                    }
                }
                z2 = z4;
                c2 = c3;
                length = i;
                i7 = i2;
                length2 = i3;
                i9 = i4;
            }
        }
    }

    private final void treeifyBin(Node<V>[] nodeArr, int i) {
        if (nodeArr != null) {
            int length = nodeArr.length;
            if (length < 64) {
                tryPresize(length << 1);
                return;
            }
            Node<V> nodeTabAt = tabAt(nodeArr, i);
            if (nodeTabAt == null || nodeTabAt.hash < 0) {
                return;
            }
            synchronized (nodeTabAt) {
                try {
                    if (tabAt(nodeArr, i) == nodeTabAt) {
                        TreeNode<V> treeNode = null;
                        Node<V> node = nodeTabAt;
                        TreeNode<V> treeNode2 = null;
                        while (node != null) {
                            TreeNode<V> treeNode3 = new TreeNode<>(node.hash, node.key, node.val, null, null);
                            treeNode3.prev = treeNode2;
                            if (treeNode2 == null) {
                                treeNode = treeNode3;
                            } else {
                                treeNode2.next = treeNode3;
                            }
                            node = node.next;
                            treeNode2 = treeNode3;
                        }
                        setTabAt(nodeArr, i, new TreeBin(treeNode));
                    }
                } catch (Throwable th) {
                    throw th;
                }
            }
        }
    }

    private final void tryPresize(int i) {
        int length;
        int iTableSizeFor = i >= 536870912 ? 1073741824 : tableSizeFor(i + (i >>> 1) + 1);
        while (true) {
            int i2 = this.sizeCtl;
            if (i2 < 0) {
                return;
            }
            Node<V>[] nodeArr = this.table;
            if (nodeArr == null || (length = nodeArr.length) == 0) {
                int i3 = i2 > iTableSizeFor ? i2 : iTableSizeFor;
                if (Unsafe.compareAndSwapInt(this, SIZECTL, i2, -1)) {
                    try {
                        if (this.table == nodeArr) {
                            this.table = new Node[i3];
                            i2 = i3 - (i3 >>> 2);
                        }
                        this.sizeCtl = i2;
                    } catch (Throwable th) {
                        this.sizeCtl = i2;
                        throw th;
                    }
                } else {
                    continue;
                }
            } else {
                if (iTableSizeFor <= i2 || length >= 1073741824) {
                    return;
                }
                if (nodeArr == this.table) {
                    if (Unsafe.compareAndSwapInt(this, SIZECTL, i2, (resizeStamp(length) << 16) + 2)) {
                        transfer(nodeArr, null);
                    }
                }
            }
        }
    }

    @Override // com.intellij.util.containers.ConcurrentIntObjectMap
    public V cacheOrGet(int i, V v) {
        if (v == null) {
            $$$reportNull$$$0(8);
        }
        V v2 = get(i);
        if (v2 != null) {
            return v2;
        }
        V vPutIfAbsent = putIfAbsent(i, v);
        if (vPutIfAbsent != null) {
            v = vPutIfAbsent;
        }
        if (v == null) {
            $$$reportNull$$$0(10);
        }
        return v;
    }

    public void clear() {
        Node<V> nodeTabAt;
        Node<V> node;
        Node<V>[] nodeArrHelpTransfer = this.table;
        long j = 0;
        loop0: while (true) {
            int i = 0;
            while (true) {
                if (nodeArrHelpTransfer == null || i >= nodeArrHelpTransfer.length) {
                    break loop0;
                }
                nodeTabAt = tabAt(nodeArrHelpTransfer, i);
                if (nodeTabAt == null) {
                    i++;
                } else {
                    int i2 = nodeTabAt.hash;
                    if (i2 == -1) {
                        break;
                    }
                    synchronized (nodeTabAt) {
                        try {
                            if (tabAt(nodeArrHelpTransfer, i) == nodeTabAt) {
                                if (i2 >= 0) {
                                    node = nodeTabAt;
                                } else {
                                    node = nodeTabAt instanceof TreeBin ? ((TreeBin) nodeTabAt).first : null;
                                }
                                while (node != null) {
                                    j--;
                                    node = node.next;
                                }
                                setTabAt(nodeArrHelpTransfer, i, null);
                                i++;
                            }
                        } catch (Throwable th) {
                            throw th;
                        }
                    }
                }
            }
            nodeArrHelpTransfer = helpTransfer(nodeArrHelpTransfer, nodeTabAt);
        }
        if (j != 0) {
            addCount(j, -1);
        }
    }

    public boolean containsValue(Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(0);
        }
        Node<V>[] nodeArr = this.table;
        if (nodeArr != null) {
            Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
            while (true) {
                Node<V> nodeAdvance = traverser.advance();
                if (nodeAdvance == null) {
                    break;
                }
                V v = nodeAdvance.val;
                if (v == obj) {
                    return true;
                }
                if (v != null && obj.equals(v)) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override // com.intellij.util.containers.ConcurrentIntObjectMap
    public Enumeration<V> elements() {
        Node<V>[] nodeArr = this.table;
        int length = nodeArr == null ? 0 : nodeArr.length;
        return new ValueIterator(nodeArr, length, 0, length, this);
    }

    @Override // com.intellij.util.containers.IntObjectMap
    public Set<IntObjectMap.Entry<V>> entrySet() {
        EntrySetView<V> entrySetView = this.entrySet;
        if (entrySetView != null) {
            return entrySetView;
        }
        EntrySetView<V> entrySetView2 = new EntrySetView<>(this);
        this.entrySet = entrySetView2;
        return entrySetView2;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ConcurrentIntObjectMap)) {
            return false;
        }
        ConcurrentIntObjectMap concurrentIntObjectMap = (ConcurrentIntObjectMap) obj;
        Node<V>[] nodeArr = this.table;
        int length = nodeArr == null ? 0 : nodeArr.length;
        Traverser traverser = new Traverser(nodeArr, length, 0, length);
        while (true) {
            Node<V> nodeAdvance = traverser.advance();
            if (nodeAdvance == null) {
                for (IntObjectMap.Entry<V> entry : concurrentIntObjectMap.entrySet()) {
                    int key = entry.getKey();
                    V value = entry.getValue();
                    V v = get(key);
                    if (v == null || (value != v && !value.equals(v))) {
                        return false;
                    }
                }
                return true;
            }
            V v2 = nodeAdvance.val;
            V v3 = concurrentIntObjectMap.get(nodeAdvance.key);
            if (v3 == null || (v3 != v2 && !v3.equals(v2))) {
                break;
            }
        }
        return false;
    }

    @Override // com.intellij.util.containers.IntObjectMap
    public V get(int i) {
        int length;
        Node<V> nodeTabAt;
        int i2;
        int iSpread = spread(i);
        Node<V>[] nodeArr = this.table;
        if (nodeArr != null && (length = nodeArr.length) > 0 && (nodeTabAt = tabAt(nodeArr, (length - 1) & iSpread)) != null) {
            int i3 = nodeTabAt.hash;
            if (i3 == iSpread) {
                int i4 = nodeTabAt.key;
                if (i4 == i || isEqual(i, i4)) {
                    return nodeTabAt.val;
                }
            } else if (i3 < 0) {
                Node<V> nodeFind = nodeTabAt.find(iSpread, i);
                if (nodeFind != null) {
                    return nodeFind.val;
                }
                return null;
            }
            while (true) {
                nodeTabAt = nodeTabAt.next;
                if (nodeTabAt == null) {
                    break;
                }
                if (nodeTabAt.hash == iSpread && ((i2 = nodeTabAt.key) == i || isEqual(i, i2))) {
                    return nodeTabAt.val;
                }
            }
        }
        return null;
    }

    @Override // com.intellij.util.containers.ConcurrentIntObjectMap
    public V getOrDefault(int i, V v) {
        V v2 = get(i);
        return v2 == null ? v : v2;
    }

    public int hashCode() {
        Node<V>[] nodeArr = this.table;
        int iHashCode = 0;
        if (nodeArr != null) {
            Traverser traverser = new Traverser(nodeArr, nodeArr.length, 0, nodeArr.length);
            while (true) {
                Node<V> nodeAdvance = traverser.advance();
                if (nodeAdvance == null) {
                    break;
                }
                iHashCode += nodeAdvance.val.hashCode() ^ spread(nodeAdvance.key);
            }
        }
        return iHashCode;
    }

    public final Node<V>[] helpTransfer(Node<V>[] nodeArr, Node<V> node) {
        Node<V>[] nodeArr2;
        int i;
        if (nodeArr == null || !(node instanceof ForwardingNode) || (nodeArr2 = ((ForwardingNode) node).nextTable) == null) {
            return this.table;
        }
        int iResizeStamp = resizeStamp(nodeArr.length) << 16;
        while (nodeArr2 == this.nextTable && this.table == nodeArr && (i = this.sizeCtl) < 0 && i != 65535 + iResizeStamp && i != iResizeStamp + 1 && this.transferIndex > 0) {
            if (Unsafe.compareAndSwapInt(this, SIZECTL, i, i + 1)) {
                transfer(nodeArr, nodeArr2);
                break;
            }
        }
        return nodeArr2;
    }

    public boolean isEmpty() {
        return sumCount() <= 0;
    }

    public long mappingCount() {
        long jSumCount = sumCount();
        if (jSumCount < 0) {
            return 0L;
        }
        return jSumCount;
    }

    @Override // com.intellij.util.containers.IntObjectMap
    public V put(int i, V v) {
        if (v == null) {
            $$$reportNull$$$0(1);
        }
        return putVal(i, v, false);
    }

    @Override // com.intellij.util.containers.ConcurrentIntObjectMap
    public V putIfAbsent(int i, V v) {
        if (v == null) {
            $$$reportNull$$$0(2);
        }
        return putVal(i, v, true);
    }

    public final V putVal(int i, V v, boolean z) {
        V v2;
        V v3;
        int iSpread = spread(i);
        Node<V>[] nodeArrInitTable = this.table;
        int i2 = 0;
        while (true) {
            if (nodeArrInitTable != null) {
                int length = nodeArrInitTable.length;
                if (length != 0) {
                    int i3 = (length - 1) & iSpread;
                    Node<V> nodeTabAt = tabAt(nodeArrInitTable, i3);
                    if (nodeTabAt != null) {
                        int i4 = nodeTabAt.hash;
                        if (i4 == -1) {
                            nodeArrInitTable = helpTransfer(nodeArrInitTable, nodeTabAt);
                        } else {
                            if (z && i4 == iSpread && nodeTabAt.key == i && (v3 = nodeTabAt.val) != null) {
                                return v3;
                            }
                            synchronized (nodeTabAt) {
                                try {
                                    if (tabAt(nodeArrInitTable, i3) != nodeTabAt) {
                                        v2 = null;
                                        break;
                                    }
                                    if (i4 >= 0) {
                                        i2 = 1;
                                        Node<V> node = nodeTabAt;
                                        while (true) {
                                            if (node.hash == iSpread && node.key == i) {
                                                v2 = node.val;
                                                if (!z) {
                                                    node.val = v;
                                                    break;
                                                }
                                                break;
                                            }
                                            Node<V> node2 = node.next;
                                            if (node2 == null) {
                                                node.next = new Node<>(iSpread, i, v, null);
                                                v2 = null;
                                                break;
                                            }
                                            i2++;
                                            node = node2;
                                        }
                                    } else {
                                        if (!(nodeTabAt instanceof TreeBin)) {
                                            if (!(nodeTabAt instanceof ReservationNode)) {
                                                v2 = null;
                                                break;
                                            }
                                            throw new IllegalStateException("Recursive update");
                                        }
                                        TreeNode<V> treeNodePutTreeVal = ((TreeBin) nodeTabAt).putTreeVal(iSpread, i, v);
                                        if (treeNodePutTreeVal != null) {
                                            V v4 = treeNodePutTreeVal.val;
                                            if (!z) {
                                                treeNodePutTreeVal.val = v;
                                            }
                                            v2 = v4;
                                        } else {
                                            v2 = null;
                                        }
                                        i2 = 2;
                                    }
                                } catch (Throwable th) {
                                    throw th;
                                }
                            }
                            if (i2 != 0) {
                                if (i2 >= 8) {
                                    treeifyBin(nodeArrInitTable, i3);
                                }
                                if (v2 == null) {
                                    break;
                                }
                                return v2;
                            }
                        }
                    } else if (casTabAt(nodeArrInitTable, i3, new Node(iSpread, i, v, null))) {
                        break;
                    }
                }
            }
            nodeArrInitTable = initTable();
        }
        addCount(1L, i2);
        return null;
    }

    @Override // com.intellij.util.containers.ConcurrentIntObjectMap
    public boolean remove(int i, Object obj) {
        if (obj == null) {
            $$$reportNull$$$0(3);
        }
        return replaceNode(i, null, obj) != null;
    }

    @Override // com.intellij.util.containers.ConcurrentIntObjectMap
    public boolean replace(int i, V v, V v2) {
        if (v == null) {
            $$$reportNull$$$0(4);
        }
        if (v2 == null) {
            $$$reportNull$$$0(5);
        }
        return replaceNode(i, v2, v) != null;
    }

    /* JADX WARN: Code duplicated, block: B:64:0x009e A[EDGE_INSN: B:64:0x009e->B:65:0x009f BREAK  A[LOOP:1: B:19:0x002d->B:40:0x0058], PHI: r7
      0x009e: PHI (r7v3 boolean) = 
      (r7v1 boolean)
      (r7v4 boolean)
      (r7v4 boolean)
      (r7v4 boolean)
      (r7v4 boolean)
      (r7v4 boolean)
      (r7v4 boolean)
      (r7v4 boolean)
     binds: [B:63:0x009d, B:44:0x0065, B:46:0x006b, B:50:0x0073, B:52:0x0079, B:24:0x0037, B:26:0x003d, B:39:0x0057] A[DONT_GENERATE, DONT_INLINE]] */
    public final V replaceNode(int i, V v, Object obj) {
        int length;
        int i2;
        Node<V> nodeTabAt;
        boolean z;
        V v2;
        TreeNode<V> treeNodeFindTreeNode;
        int iSpread = spread(i);
        Node<V>[] nodeArrHelpTransfer = this.table;
        while (nodeArrHelpTransfer != null && (length = nodeArrHelpTransfer.length) != 0 && (nodeTabAt = tabAt(nodeArrHelpTransfer, (i2 = (length - 1) & iSpread))) != null) {
            int i3 = nodeTabAt.hash;
            if (i3 == -1) {
                nodeArrHelpTransfer = helpTransfer(nodeArrHelpTransfer, nodeTabAt);
            } else {
                synchronized (nodeTabAt) {
                    try {
                        if (tabAt(nodeArrHelpTransfer, i2) != nodeTabAt) {
                            z = false;
                            v2 = null;
                            break;
                        }
                        z = true;
                        if (i3 >= 0) {
                            Node<V> node = null;
                            Node<V> node2 = nodeTabAt;
                            while (true) {
                                if (node2.key == i) {
                                    v2 = node2.val;
                                    if (obj == null || obj == v2 || (v2 != null && obj.equals(v2))) {
                                        if (v == null) {
                                            Node<V> node3 = node2.next;
                                            if (node == null) {
                                                setTabAt(nodeArrHelpTransfer, i2, node3);
                                                break;
                                            }
                                            node.next = node3;
                                            break;
                                        }
                                        node2.val = v;
                                        break;
                                    }
                                } else {
                                    Node<V> node4 = node2.next;
                                    if (node4 != null) {
                                        node = node2;
                                        node2 = node4;
                                    }
                                }
                                v2 = null;
                                break;
                            }
                        }
                        if (!(nodeTabAt instanceof TreeBin)) {
                            if (!(nodeTabAt instanceof ReservationNode)) {
                                z = false;
                                v2 = null;
                                break;
                            }
                            throw new IllegalStateException("Recursive update");
                        }
                        TreeBin treeBin = (TreeBin) nodeTabAt;
                        TreeNode<V> treeNode = treeBin.root;
                        if (treeNode != null && (treeNodeFindTreeNode = treeNode.findTreeNode(iSpread, i)) != null) {
                            v2 = treeNodeFindTreeNode.val;
                            if (obj != null && obj != v2 && (v2 == null || !obj.equals(v2))) {
                                v2 = null;
                                break;
                            }
                            if (v != null) {
                                treeNodeFindTreeNode.val = v;
                            } else if (treeBin.removeTreeNode(treeNodeFindTreeNode)) {
                                setTabAt(nodeArrHelpTransfer, i2, untreeify(treeBin.first));
                            }
                        } else {
                            v2 = null;
                            break;
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                if (z) {
                    if (v2 == null) {
                        break;
                    }
                    if (v == null) {
                        addCount(-1L, -1);
                    }
                    return v2;
                }
            }
        }
        return null;
    }

    @Override // com.intellij.util.containers.ConcurrentIntObjectMap
    public int size() {
        long jSumCount = sumCount();
        if (jSumCount < 0) {
            return 0;
        }
        if (jSumCount > SieveCacheKt.NodeLinkMask) {
            return Integer.MAX_VALUE;
        }
        return (int) jSumCount;
    }

    public final long sumCount() {
        CounterCell[] counterCellArr = this.counterCells;
        long j = this.baseCount;
        if (counterCellArr != null) {
            for (CounterCell counterCell : counterCellArr) {
                if (counterCell != null) {
                    j += counterCell.value;
                }
            }
        }
        return j;
    }

    public String toString() {
        Node<V>[] nodeArr = this.table;
        int length = nodeArr == null ? 0 : nodeArr.length;
        Traverser traverser = new Traverser(nodeArr, length, 0, length);
        StringBuilder sb = new StringBuilder("{");
        Node<V> nodeAdvance = traverser.advance();
        if (nodeAdvance != null) {
            while (true) {
                int i = nodeAdvance.key;
                Object obj = nodeAdvance.val;
                sb.append(i);
                sb.append('=');
                if (obj == this) {
                    obj = "(this Map)";
                }
                sb.append(obj);
                nodeAdvance = traverser.advance();
                if (nodeAdvance == null) {
                    break;
                }
                sb.append(", ");
            }
        }
        sb.append('}');
        return sb.toString();
    }

    public Node<V> untreeify(Node<V> node) {
        Node<V> node2 = null;
        Node<V> node3 = null;
        while (node != null) {
            Node<V> node4 = new Node<>(node.hash, node.key, node.val, null);
            if (node3 == null) {
                node2 = node4;
            } else {
                node3.next = node4;
            }
            node = node.next;
            node3 = node4;
        }
        return node2;
    }

    @Override // com.intellij.util.containers.IntObjectMap
    public Collection<V> values() {
        ValuesView<V> valuesView = this.values;
        if (valuesView != null) {
            return valuesView;
        }
        ValuesView<V> valuesView2 = new ValuesView<>(this);
        this.values = valuesView2;
        return valuesView2;
    }

    @Override // com.intellij.util.containers.ConcurrentIntObjectMap
    public V replace(int i, V v) {
        if (v == null) {
            $$$reportNull$$$0(6);
        }
        return replaceNode(i, v, null);
    }

    public static abstract class CollectionView<V, E> implements Collection<E> {
        final ConcurrentIntObjectHashMap<V> map;

        public CollectionView(ConcurrentIntObjectHashMap<V> concurrentIntObjectHashMap) {
            this.map = concurrentIntObjectHashMap;
        }

        @Override // java.util.Collection
        public final void clear() {
            this.map.clear();
        }

        @Override // java.util.Collection
        public abstract boolean contains(Object obj);

        @Override // java.util.Collection
        public final boolean containsAll(Collection<?> collection) {
            if (collection == this) {
                return true;
            }
            for (Object obj : collection) {
                if (obj == null || !contains(obj)) {
                    return false;
                }
            }
            return true;
        }

        @Override // java.util.Collection
        public final boolean isEmpty() {
            return this.map.isEmpty();
        }

        @Override // java.util.Collection, java.lang.Iterable
        public abstract Iterator<E> iterator();

        @Override // java.util.Collection
        public abstract boolean remove(Object obj);

        @Override // java.util.Collection
        public boolean removeAll(Collection<?> collection) {
            collection.getClass();
            Node<V>[] nodeArr = this.map.table;
            boolean zRemove = false;
            if (nodeArr == null) {
                return false;
            }
            if (!(collection instanceof Set) || collection.size() <= nodeArr.length) {
                Iterator<?> it = collection.iterator();
                while (it.hasNext()) {
                    zRemove |= remove(it.next());
                }
                return zRemove;
            }
            Iterator<E> it2 = iterator();
            while (it2.hasNext()) {
                if (collection.contains(it2.next())) {
                    it2.remove();
                    zRemove = true;
                }
            }
            return zRemove;
        }

        @Override // java.util.Collection
        public final boolean retainAll(Collection<?> collection) {
            collection.getClass();
            Iterator<E> it = iterator();
            boolean z = false;
            while (it.hasNext()) {
                if (!collection.contains(it.next())) {
                    it.remove();
                    z = true;
                }
            }
            return z;
        }

        @Override // java.util.Collection
        public final int size() {
            return this.map.size();
        }

        @Override // java.util.Collection
        public final <T> T[] toArray(T[] tArr) {
            long jMappingCount = this.map.mappingCount();
            if (jMappingCount > 2147483639) {
                throw new OutOfMemoryError("Required array size too large");
            }
            int i = (int) jMappingCount;
            Object[] objArr = tArr.length >= i ? tArr : (Object[]) Array.newInstance(tArr.getClass().getComponentType(), i);
            int length = objArr.length;
            int i2 = 0;
            for (E e : this) {
                if (i2 == length) {
                    if (length >= 2147483639) {
                        throw new OutOfMemoryError("Required array size too large");
                    }
                    int i3 = length < 1073741819 ? (length >>> 1) + 1 + length : 2147483639;
                    objArr = (T[]) Arrays.copyOf(objArr, i3);
                    length = i3;
                }
                objArr[i2] = e;
                i2++;
            }
            if (tArr != objArr || i2 >= length) {
                return i2 == length ? (T[]) objArr : (T[]) Arrays.copyOf(objArr, i2);
            }
            objArr[i2] = null;
            return (T[]) objArr;
        }

        public final String toString() {
            StringBuilder sb = new StringBuilder("[");
            Iterator<E> it = iterator();
            if (it.hasNext()) {
                while (true) {
                    Object next = it.next();
                    if (next == this) {
                        next = "(this Collection)";
                    }
                    sb.append(next);
                    if (!it.hasNext()) {
                        break;
                    }
                    sb.append(", ");
                }
            }
            sb.append(']');
            return sb.toString();
        }

        @Override // java.util.Collection
        public final Object[] toArray() {
            long jMappingCount = this.map.mappingCount();
            if (jMappingCount <= 2147483639) {
                int i = (int) jMappingCount;
                Object[] objArrCopyOf = new Object[i];
                int i2 = 0;
                for (E e : this) {
                    if (i2 == i) {
                        if (i < 2147483639) {
                            int i3 = i < 1073741819 ? (i >>> 1) + 1 + i : 2147483639;
                            objArrCopyOf = Arrays.copyOf(objArrCopyOf, i3);
                            i = i3;
                        } else {
                            throw new OutOfMemoryError("Required array size too large");
                        }
                    }
                    objArrCopyOf[i2] = e;
                    i2++;
                }
                return i2 == i ? objArrCopyOf : Arrays.copyOf(objArrCopyOf, i2);
            }
            throw new OutOfMemoryError("Required array size too large");
        }
    }
}
