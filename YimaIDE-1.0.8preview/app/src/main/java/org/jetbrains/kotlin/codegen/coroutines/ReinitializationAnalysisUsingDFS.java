package org.jetbrains.kotlin.codegen.coroutines;

import java.util.ArrayList;
import java.util.BitSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.ArrayDeque;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.ranges.RangesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.optimization.common.ControlFlowGraph;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.IincInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010!\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B9\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\u0012\u0010\t\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u00030\u0003¢\u0006\u0004\b\u000b\u0010\fJ\u0019\u0010\r\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\u000f0\u000eH\u0016¢\u0006\u0002\u0010\u0010J<\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00120\u00032\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00120\u00032\u0006\u0010\u0017\u001a\u00020\u00122\u0006\u0010\u0018\u001a\u00020\u0012H\u0002¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysisUsingDFS;", "Lorg/jetbrains/kotlin/codegen/coroutines/ReinitializationAnalysis;", "suspensionPoints", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/coroutines/SuspensionPoint;", "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "containingClassName", Argument.Delimiters.none, "variablesToSpillBySuspensionPointIndex", "Lorg/jetbrains/kotlin/codegen/coroutines/SpillableVariable;", "<init>", "(Ljava/util/List;Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;Ljava/lang/String;Ljava/util/List;)V", "calculate", Argument.Delimiters.none, Argument.Delimiters.none, "()[Ljava/util/List;", "doDFS", Argument.Delimiters.none, "startInsnIndex", "cfg", "Lorg/jetbrains/kotlin/codegen/optimization/common/ControlFlowGraph;", "spEnds", "currentSPEnd", "slot", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class ReinitializationAnalysisUsingDFS extends ReinitializationAnalysis {
    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public ReinitializationAnalysisUsingDFS(List<SuspensionPoint> list, MethodNode methodNode, String str, List<? extends List<SpillableVariable>> list2) {
        super(list, methodNode, str, list2);
        list.getClass();
        methodNode.getClass();
        str.getClass();
        list2.getClass();
    }

    private final List<Integer> doDFS(int startInsnIndex, ControlFlowGraph cfg, List<Integer> spEnds, int currentSPEnd, int slot) {
        ArrayList arrayList = new ArrayList();
        ArrayDeque arrayDeque = new ArrayDeque();
        BitSet bitSet = new BitSet(getMethodNode().instructions.size());
        bitSet.set(startInsnIndex);
        arrayDeque.add(Integer.valueOf(startInsnIndex));
        while (!arrayDeque.isEmpty()) {
            int iIntValue = ((Number) arrayDeque.removeLast()).intValue();
            ReinitializationAnalysisUsingDFS reinitializationAnalysisUsingDFS = this;
            List<Integer> list = spEnds;
            int i = currentSPEnd;
            int i2 = slot;
            if (doDFS$handleBeforeChildren(list, i, arrayList, reinitializationAnalysisUsingDFS, i2, iIntValue)) {
                Iterator<T> it = cfg.getPredecessorsIndices(iIntValue).iterator();
                while (it.hasNext()) {
                    int iIntValue2 = ((Number) it.next()).intValue();
                    if (!bitSet.get(iIntValue2)) {
                        bitSet.set(iIntValue2);
                        arrayDeque.add(Integer.valueOf(iIntValue2));
                    }
                }
            }
            spEnds = list;
            currentSPEnd = i;
            this = reinitializationAnalysisUsingDFS;
            slot = i2;
        }
        return arrayList;
    }

    private static final boolean doDFS$handleBeforeChildren(List<Integer> list, int i, List<Integer> list2, ReinitializationAnalysisUsingDFS reinitializationAnalysisUsingDFS, int i2, int i3) {
        Integer numValueOf;
        if (list.contains(Integer.valueOf(i3))) {
            if (i3 != i) {
                list2.add(Integer.valueOf(i3));
            }
            return false;
        }
        VarInsnNode varInsnNode = reinitializationAnalysisUsingDFS.getMethodNode().instructions.get(i3);
        int opcode = varInsnNode.getOpcode();
        if (54 > opcode || opcode >= 59) {
            numValueOf = opcode == 132 ? Integer.valueOf(((IincInsnNode) varInsnNode).var) : null;
        } else {
            numValueOf = Integer.valueOf(varInsnNode.var);
        }
        return numValueOf == null || numValueOf.intValue() != i2;
    }

    @Override // org.jetbrains.kotlin.codegen.coroutines.ReinitializationAnalysis
    public List<SpillableVariable>[] calculate() {
        int size = getSuspensionPoints().size();
        List<SpillableVariable>[] listArr = new List[size];
        int i = 0;
        for (int i2 = 0; i2 < size; i2++) {
            listArr[i2] = new ArrayList();
        }
        ControlFlowGraph controlFlowGraphBuild$default = ControlFlowGraph.Companion.build$default(ControlFlowGraph.INSTANCE, getMethodNode(), false, 2, null);
        List<SuspensionPoint> suspensionPoints = getSuspensionPoints();
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(suspensionPoints, 10));
        Iterator<T> it = suspensionPoints.iterator();
        while (it.hasNext()) {
            arrayList.add(Integer.valueOf(getMethodNode().instructions.indexOf(((SuspensionPoint) it.next()).getSuspensionCallEnd())));
        }
        Iterable<IndexedValue> iterableWithIndex = CollectionsKt.withIndex(arrayList);
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterableWithIndex, 10)), 16));
        for (IndexedValue indexedValue : iterableWithIndex) {
            linkedHashMap.put(Integer.valueOf(((Number) indexedValue.getValue()).intValue()), Integer.valueOf(indexedValue.getIndex()));
        }
        for (SuspensionPoint suspensionPoint : getSuspensionPoints()) {
            int i3 = i + 1;
            for (SpillableVariable spillableVariable : this.getVariablesToSpillBySuspensionPointIndex().get(i)) {
                int slot = spillableVariable.getSlot();
                int iIndexOf = this.getMethodNode().instructions.indexOf(suspensionPoint.getSuspensionCallBegin());
                int iIndexOf2 = this.getMethodNode().instructions.indexOf(suspensionPoint.getSuspensionCallEnd());
                ReinitializationAnalysisUsingDFS reinitializationAnalysisUsingDFS = this;
                List<Integer> listDoDFS = reinitializationAnalysisUsingDFS.doDFS(iIndexOf, controlFlowGraphBuild$default, arrayList, iIndexOf2, slot);
                ArrayList arrayList2 = new ArrayList();
                for (Object obj : listDoDFS) {
                    if (((Number) obj).intValue() != iIndexOf2) {
                        arrayList2.add(obj);
                    }
                }
                Iterator it2 = arrayList2.iterator();
                while (it2.hasNext()) {
                    Object obj2 = linkedHashMap.get(Integer.valueOf(((Number) it2.next()).intValue()));
                    obj2.getClass();
                    int iIntValue = ((Number) obj2).intValue();
                    List<SpillableVariable> list = reinitializationAnalysisUsingDFS.getVariablesToSpillBySuspensionPointIndex().get(iIntValue);
                    if (!(list instanceof Collection) || !list.isEmpty()) {
                        Iterator<T> it3 = list.iterator();
                        do {
                            if (it3.hasNext()) {
                            }
                        } while (((SpillableVariable) it3.next()).getSlot() != spillableVariable.getSlot());
                    }
                    listArr[iIntValue].add(spillableVariable);
                }
                this = reinitializationAnalysisUsingDFS;
            }
            i = i3;
        }
        return listArr;
    }
}
