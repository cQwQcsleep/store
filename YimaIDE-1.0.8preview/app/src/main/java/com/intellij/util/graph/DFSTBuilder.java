package com.intellij.util.graph;

import androidx.collection.ScatterMapKt;
import com.intellij.util.ArrayUtil;
import com.intellij.util.ArrayUtilRt;
import it.unimi.dsi.fastutil.ints.IntArrayList;
import it.unimi.dsi.fastutil.ints.IntList;
import it.unimi.dsi.fastutil.ints.IntStack;
import it.unimi.dsi.fastutil.objects.Object2IntMap;
import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
import it.unimi.dsi.fastutil.objects.Reference2IntOpenHashMap;
import java.util.AbstractCollection;
import java.util.AbstractMap;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.ObjIntConsumer;
import java.util.function.ToIntFunction;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class DFSTBuilder<Node> {
    private final Node[] allNodes;
    private Map.Entry<Node, Node> myBackEdge;
    private final OutboundSemiGraph<Node> myGraph;
    private final Node[] myInvN;
    private final Node[] myInvT;
    private Comparator<Node> myNComparator;
    private final ToIntFunction<Node> myNodeToNNumber;
    private final ToIntFunction<Node> myNodeToTNumber;
    private final IntList mySCCs;
    private Comparator<Node> myTComparator;

    /* JADX INFO: renamed from: com.intellij.util.graph.DFSTBuilder$1, reason: invalid class name */
    public class AnonymousClass1 extends MyCollection<Collection<Node>> {
        final /* synthetic */ DFSTBuilder this$0;
        final /* synthetic */ IntList val$componentSizes;

        /* JADX INFO: renamed from: com.intellij.util.graph.DFSTBuilder$1$1, reason: invalid class name and collision with other inner class name */
        public class C00451 extends MyIterator<Collection<Node>> {
            private int offset;

            public C00451(int i) {
                super(i);
            }

            @Override // com.intellij.util.graph.DFSTBuilder.MyIterator
            public Collection<Node> get(int i) {
                final int i2 = AnonymousClass1.this.val$componentSizes.getInt(i);
                final int i3 = this.offset;
                if (i2 == 0) {
                    return Collections.EMPTY_LIST;
                }
                this.offset = i3 + i2;
                return new MyCollection<Node>(i2) { // from class: com.intellij.util.graph.DFSTBuilder.1.1.1
                    @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
                    public Iterator<Node> iterator() {
                        return new MyIterator<Node>(i2) { // from class: com.intellij.util.graph.DFSTBuilder.1.1.1.1
                            @Override // com.intellij.util.graph.DFSTBuilder.MyIterator
                            public Node get(int i4) {
                                C00461 c00461 = C00461.this;
                                return (Node) AnonymousClass1.this.this$0.getNodeByTNumber(i3 + i4);
                            }
                        };
                    }
                };
            }
        }

        @Override // java.util.AbstractCollection, java.util.Collection, java.lang.Iterable
        public Iterator<Collection<Node>> iterator() {
            return new C00451(this.val$componentSizes.size());
        }
    }

    public static abstract class MyCollection<T> extends AbstractCollection<T> {
        private final int size;

        public MyCollection(int i) {
            this.size = i;
        }

        @Override // java.util.AbstractCollection, java.util.Collection
        public int size() {
            return this.size;
        }
    }

    public static abstract class MyIterator<T> implements Iterator<T> {
        private int i;
        private final int size;

        public MyIterator(int i) {
            this.size = i;
        }

        public abstract T get(int i);

        @Override // java.util.Iterator
        public boolean hasNext() {
            return this.i < this.size;
        }

        @Override // java.util.Iterator
        public T next() {
            int i = this.i;
            if (i != this.size) {
                this.i = i + 1;
                return get(i);
            }
            z0e.a();
            return null;
        }

        @Override // java.util.Iterator
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }

    public static final class TarjanFrame<Node> {
        private final Node[] allNodes;
        int nextUnexploredIndex;
        private final int nodeI;
        private final int[] out;

        public TarjanFrame(int i, Node[] nodeArr, int[] iArr) {
            this.nodeI = i;
            this.allNodes = nodeArr;
            this.out = iArr;
        }

        public String toString() {
            StringBuilder sb = new StringBuilder();
            sb.append(this.allNodes[this.nodeI]);
            sb.append(" -> [");
            for (int i : this.out) {
                sb.append(this.allNodes[i]);
                sb.append(", ");
            }
            sb.append(']');
            return sb.toString();
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
                str = "@NotNull method %s.%s must not return null";
                break;
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i) {
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
                i2 = 2;
                break;
            default:
                i2 = 3;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
                objArr[0] = "com/intellij/util/graph/DFSTBuilder";
                break;
            default:
                objArr[0] = "graph";
                break;
        }
        switch (i) {
            case 3:
            case 4:
            case 5:
                objArr[1] = "comparator";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[1] = "getNodeByNNumber";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[1] = "getNodeByTNumber";
                break;
            case 8:
                objArr[1] = "getSCCs";
                break;
            case 9:
                objArr[1] = "getComponents";
                break;
            case 10:
                objArr[1] = "getSortedNodes";
                break;
            default:
                objArr[1] = "com/intellij/util/graph/DFSTBuilder";
                break;
        }
        switch (i) {
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case 3:
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
                throw new IllegalStateException(str2);
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    public DFSTBuilder(OutboundSemiGraph<Node> outboundSemiGraph, Node node, boolean z) {
        if (outboundSemiGraph == null) {
            $$$reportNull$$$0(2);
        }
        this.mySCCs = new IntArrayList();
        this.myGraph = outboundSemiGraph;
        Node[] nodeArr = (Node[]) outboundSemiGraph.getNodes().toArray();
        this.allNodes = nodeArr;
        if (node != null) {
            int iIndexOfIdentity = z ? ArrayUtil.indexOfIdentity(nodeArr, node) : ArrayUtil.indexOf(nodeArr, node);
            if (iIndexOfIdentity != -1) {
                ArrayUtil.swap(nodeArr, 0, iIndexOfIdentity);
            }
        }
        int length = nodeArr.length;
        this.myInvN = (Node[]) new Object[length];
        this.myInvT = (Node[]) new Object[length];
        if (z) {
            final Reference2IntOpenHashMap reference2IntOpenHashMap = new Reference2IntOpenHashMap(length * 2, 0.5f);
            final Reference2IntOpenHashMap reference2IntOpenHashMap2 = new Reference2IntOpenHashMap();
            this.myNodeToNNumber = reference2IntOpenHashMap;
            this.myNodeToTNumber = reference2IntOpenHashMap2;
            new Tarjan(this, new ObjIntConsumer() { // from class: o73
                @Override // java.util.function.ObjIntConsumer
                public final void accept(Object obj, int i) {
                    reference2IntOpenHashMap2.put(obj, i);
                }
            }, new ObjIntConsumer() { // from class: o73
                @Override // java.util.function.ObjIntConsumer
                public final void accept(Object obj, int i) {
                    reference2IntOpenHashMap.put(obj, i);
                }
            }, nodeArr, true, null);
            return;
        }
        final Object2IntOpenHashMap object2IntOpenHashMap = new Object2IntOpenHashMap(length * 2, 0.5f);
        final Object2IntOpenHashMap object2IntOpenHashMap2 = new Object2IntOpenHashMap();
        this.myNodeToNNumber = object2IntOpenHashMap;
        this.myNodeToTNumber = object2IntOpenHashMap2;
        new Tarjan(this, new ObjIntConsumer() { // from class: p73
            @Override // java.util.function.ObjIntConsumer
            public final void accept(Object obj, int i) {
                object2IntOpenHashMap2.put(obj, i);
            }
        }, new ObjIntConsumer() { // from class: p73
            @Override // java.util.function.ObjIntConsumer
            public final void accept(Object obj, int i) {
                object2IntOpenHashMap.put(obj, i);
            }
        }, nodeArr, false, null);
    }

    public Comparator<Node> comparator(boolean z) {
        if (z) {
            if (this.myNComparator == null) {
                this.myNComparator = Comparator.comparingInt(this.myNodeToNNumber);
            }
            Comparator<Node> comparator = this.myNComparator;
            if (comparator == null) {
                $$$reportNull$$$0(4);
            }
            return comparator;
        }
        if (this.myTComparator == null) {
            this.myTComparator = Comparator.comparingInt(this.myNodeToTNumber);
        }
        Comparator<Node> comparator2 = this.myTComparator;
        if (comparator2 == null) {
            $$$reportNull$$$0(5);
        }
        return comparator2;
    }

    public Map.Entry<Node, Node> getCircularDependency() {
        return this.myBackEdge;
    }

    public Node getNodeByTNumber(int i) {
        Node node = this.myInvT[i];
        if (node == null) {
            $$$reportNull$$$0(7);
        }
        return node;
    }

    public boolean isAcyclic() {
        return getCircularDependency() == null;
    }

    public Comparator<Node> comparator() {
        Comparator<Node> comparator = comparator(isAcyclic());
        if (comparator == null) {
            $$$reportNull$$$0(3);
        }
        return comparator;
    }

    public final class Tarjan {
        private int dfsIndex;
        private final Deque<TarjanFrame<Node>> frames;
        private final int[] index;
        private final boolean[] isOnStack;
        private final int[] lowLink;
        private final ToIntFunction<? super Node> myNodeIndex;
        private final IntStack nodesOnStack;
        private int sccsSizeCombined;
        private final IntList topo;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = (i == 2 || i == 3) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
            Object[] objArr = new Object[(i == 2 || i == 3) ? 2 : 3];
            if (i == 1) {
                objArr[0] = "node";
            } else if (i == 2 || i == 3) {
                objArr[0] = "com/intellij/util/graph/DFSTBuilder$Tarjan";
            } else {
                objArr[0] = "sccs";
            }
            if (i == 2) {
                objArr[1] = "createObject2IntMap";
            } else if (i != 3) {
                objArr[1] = "com/intellij/util/graph/DFSTBuilder$Tarjan";
            } else {
                objArr[1] = "createReference2IntMap";
            }
            if (i == 1) {
                objArr[2] = "buildOuts";
            } else if (i != 2 && i != 3) {
                objArr[2] = "strongConnect";
            }
            String str2 = String.format(str, objArr);
            if (i != 2 && i != 3) {
                throw new IllegalArgumentException(str2);
            }
            throw new IllegalStateException(str2);
        }

        private Tarjan(ObjIntConsumer<Node> objIntConsumer, ObjIntConsumer<Node> objIntConsumer2, Node[] nodeArr, boolean z) {
            this.lowLink = new int[DFSTBuilder.this.myInvN.length];
            int[] iArr = new int[DFSTBuilder.this.myInvN.length];
            this.index = iArr;
            this.nodesOnStack = new IntArrayList();
            this.isOnStack = new boolean[iArr.length];
            this.frames = new ArrayDeque();
            this.topo = new IntArrayList(iArr.length);
            this.myNodeIndex = z ? createReference2IntMap(nodeArr) : createObject2IntMap(nodeArr);
            Arrays.fill(iArr, -1);
            build(objIntConsumer, objIntConsumer2, nodeArr);
        }

        private void build(ObjIntConsumer<? super Node> objIntConsumer, ObjIntConsumer<? super Node> objIntConsumer2, Node[] nodeArr) {
            int i = 0;
            int i2 = 0;
            while (true) {
                int[] iArr = this.index;
                if (i2 >= iArr.length) {
                    break;
                }
                if (iArr[i2] == -1) {
                    this.frames.addLast(new TarjanFrame<>(i2, nodeArr, buildOuts(nodeArr[i2])));
                    ArrayList arrayList = new ArrayList();
                    strongConnect(arrayList, nodeArr);
                    for (List<Node> list : arrayList) {
                        int size = list.size();
                        DFSTBuilder.this.mySCCs.add(size);
                        int length = (this.index.length - this.sccsSizeCombined) - size;
                        int iIndexOf = list.indexOf(nodeArr[i2]);
                        if (iIndexOf != -1) {
                            Node node = list.get(iIndexOf);
                            list.set(iIndexOf, list.get(0));
                            list.set(0, node);
                        }
                        for (int i3 = 0; i3 < list.size(); i3++) {
                            Object obj = list.get(i3);
                            int i4 = length + i3;
                            DFSTBuilder.this.myInvT[i4] = obj;
                            objIntConsumer.accept(obj, i4);
                        }
                        this.sccsSizeCombined += size;
                    }
                }
                i2++;
            }
            for (int i5 = 0; i5 < this.topo.size(); i5++) {
                Node node2 = nodeArr[this.topo.getInt(i5)];
                objIntConsumer2.accept(node2, (this.index.length - 1) - i5);
                DFSTBuilder.this.myInvN[(this.index.length - 1) - i5] = node2;
            }
            for (int size2 = DFSTBuilder.this.mySCCs.size() - 1; i < size2; size2--) {
                int i6 = DFSTBuilder.this.mySCCs.getInt(i);
                DFSTBuilder.this.mySCCs.set(i, DFSTBuilder.this.mySCCs.getInt(size2));
                DFSTBuilder.this.mySCCs.set(size2, i6);
                i++;
            }
        }

        private int[] buildOuts(Node node) {
            if (node == null) {
                $$$reportNull$$$0(1);
            }
            IntArrayList intArrayList = new IntArrayList();
            Iterator<Node> out = DFSTBuilder.this.myGraph.getOut(node);
            while (out.hasNext()) {
                intArrayList.add(this.myNodeIndex.applyAsInt(out.next()));
            }
            return intArrayList.isEmpty() ? ArrayUtilRt.EMPTY_INT_ARRAY : intArrayList.toIntArray();
        }

        private Object2IntMap<Node> createObject2IntMap(Node[] nodeArr) {
            Object2IntOpenHashMap object2IntOpenHashMap = new Object2IntOpenHashMap(nodeArr.length);
            for (int i = 0; i < nodeArr.length; i++) {
                object2IntOpenHashMap.put(nodeArr[i], i);
            }
            return object2IntOpenHashMap;
        }

        private Reference2IntOpenHashMap<Node> createReference2IntMap(Node[] nodeArr) {
            Reference2IntOpenHashMap<Node> reference2IntOpenHashMap = new Reference2IntOpenHashMap<>(nodeArr.length);
            for (int i = 0; i < nodeArr.length; i++) {
                reference2IntOpenHashMap.put(nodeArr[i], i);
            }
            return reference2IntOpenHashMap;
        }

        private void strongConnect(List<? super List<Node>> list, Node[] nodeArr) {
            int iPopInt;
            if (list == null) {
                $$$reportNull$$$0(0);
            }
            int i = -1;
            while (!this.frames.isEmpty()) {
                TarjanFrame<Node> tarjanFramePeekLast = this.frames.peekLast();
                int i2 = ((TarjanFrame) tarjanFramePeekLast).nodeI;
                int[] iArr = this.index;
                if (iArr[i2] == -1) {
                    int i3 = this.dfsIndex;
                    iArr[i2] = i3;
                    this.lowLink[i2] = i3;
                    this.dfsIndex = i3 + 1;
                    this.nodesOnStack.push(i2);
                    this.isOnStack[i2] = true;
                }
                if (ArrayUtil.indexOf(((TarjanFrame) tarjanFramePeekLast).out, i) != -1) {
                    int[] iArr2 = this.lowLink;
                    iArr2[i2] = Math.min(iArr2[i2], iArr2[i]);
                }
                while (true) {
                    if (tarjanFramePeekLast.nextUnexploredIndex >= ((TarjanFrame) tarjanFramePeekLast).out.length) {
                        this.frames.removeLast();
                        this.topo.add(i2);
                        if (this.lowLink[i2] != this.index[i2]) {
                            break;
                        }
                        ArrayList arrayList = new ArrayList();
                        do {
                            iPopInt = this.nodesOnStack.popInt();
                            Node node = nodeArr[iPopInt];
                            this.isOnStack[iPopInt] = false;
                            arrayList.add(node);
                        } while (iPopInt != i2);
                        list.add(arrayList);
                        break;
                    }
                    int[] iArr3 = ((TarjanFrame) tarjanFramePeekLast).out;
                    int i4 = tarjanFramePeekLast.nextUnexploredIndex;
                    tarjanFramePeekLast.nextUnexploredIndex = i4 + 1;
                    int i5 = iArr3[i4];
                    int i6 = this.index[i5];
                    if (i6 == -1) {
                        this.frames.addLast(new TarjanFrame<>(i5, nodeArr, buildOuts(nodeArr[i5])));
                        break;
                    } else if (this.isOnStack[i5]) {
                        int[] iArr4 = this.lowLink;
                        iArr4[i2] = Math.min(iArr4[i2], i6);
                        if (DFSTBuilder.this.myBackEdge == null) {
                            DFSTBuilder.this.myBackEdge = new AbstractMap.SimpleImmutableEntry(nodeArr[i5], nodeArr[i2]);
                        }
                    }
                }
                i = i2;
            }
        }

        public /* synthetic */ Tarjan(DFSTBuilder dFSTBuilder, ObjIntConsumer objIntConsumer, ObjIntConsumer objIntConsumer2, Object[] objArr, boolean z, AnonymousClass1 anonymousClass1) {
            this(objIntConsumer, objIntConsumer2, objArr, z);
        }
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public DFSTBuilder(OutboundSemiGraph<Node> outboundSemiGraph) {
        this(outboundSemiGraph, null, false);
        if (outboundSemiGraph == null) {
            $$$reportNull$$$0(0);
        }
    }
}
