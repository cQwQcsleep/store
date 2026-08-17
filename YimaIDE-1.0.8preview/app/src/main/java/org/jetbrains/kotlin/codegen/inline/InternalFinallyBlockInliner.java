package org.jetbrains.kotlin.codegen.inline;

import com.google.common.collect.Lists;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import org.jetbrains.kotlin.codegen.CodegenUtilKt;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.inline.InternalFinallyBlockInliner;
import org.jetbrains.kotlin.codegen.inline.LocalVarNodeWrapper;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LineNumberNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;
import org.jetbrains.org.objectweb.asm.util.Textifier;
import org.jetbrains.org.objectweb.asm.util.TraceMethodVisitor;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class InternalFinallyBlockInliner extends CoveringTryCatchNodeProcessor {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final MethodNode inlineFun;
    private final boolean properFinallySplit;

    public static class FinallyBlockInfo {
        final LabelNode closestLabelAfterEnd;
        final LabelNode closestLabelBeforeStart;
        final AbstractInsnNode endInsExclusive;
        final AbstractInsnNode startIns;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            Object[] objArr = new Object[3];
            if (i == 1) {
                objArr[0] = "exclusiveEnd";
            } else if (i == 2) {
                objArr[0] = "closestLabelBeforeStart";
            } else if (i != 3) {
                objArr[0] = "inclusiveStart";
            } else {
                objArr[0] = "closestLabelAfterEnd";
            }
            objArr[1] = "org/jetbrains/kotlin/codegen/inline/InternalFinallyBlockInliner$FinallyBlockInfo";
            objArr[2] = "<init>";
            throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
        }

        private FinallyBlockInfo(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2, LabelNode labelNode, LabelNode labelNode2) {
            if (abstractInsnNode == null) {
                $$$reportNull$$$0(0);
            }
            if (abstractInsnNode2 == null) {
                $$$reportNull$$$0(1);
            }
            if (labelNode == null) {
                $$$reportNull$$$0(2);
            }
            if (labelNode2 == null) {
                $$$reportNull$$$0(3);
            }
            this.startIns = abstractInsnNode;
            this.endInsExclusive = abstractInsnNode2;
            this.closestLabelBeforeStart = labelNode;
            this.closestLabelAfterEnd = labelNode2;
        }

        public boolean isEmpty() {
            AbstractInsnNode abstractInsnNode;
            if (!(this.startIns instanceof LabelNode)) {
                return false;
            }
            AbstractInsnNode previous = this.endInsExclusive;
            while (true) {
                abstractInsnNode = this.startIns;
                if (previous == abstractInsnNode || !(previous instanceof LabelNode)) {
                    break;
                }
                previous = previous.getPrevious();
            }
            return abstractInsnNode == previous;
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 8 || i == 20) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 8 || i == 20) ? 2 : 3];
        switch (i) {
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
                objArr[0] = "inlineFunTryBlockInfo";
                break;
            case 3:
                objArr[0] = "localVariableInfo";
                break;
            case 4:
                objArr[0] = "finallyBlockCopy";
                break;
            case 5:
                objArr[0] = "currentIns";
                break;
            case 6:
                objArr[0] = "currentCoveringNodesFromOuterMost";
                break;
            case 7:
            case 19:
                objArr[0] = "finallyInfo";
                break;
            case 8:
            case 20:
                objArr[0] = "org/jetbrains/kotlin/codegen/inline/InternalFinallyBlockInliner";
                break;
            case 9:
                objArr[0] = "updatingClusterBlocks";
                break;
            case 10:
                objArr[0] = "newFinallyStart";
                break;
            case 11:
                objArr[0] = "newFinallyEnd";
                break;
            case 12:
                objArr[0] = "tryCatchBlockPresentInFinally";
                break;
            case 13:
            case 15:
                objArr[0] = "labelsInsideFinallyOldToNew";
                break;
            case 14:
                objArr[0] = "insertedBlockEnd";
                break;
            case 16:
                objArr[0] = "oldToNew";
                break;
            case 17:
                objArr[0] = "tryCatchBlock";
                break;
            case 18:
                objArr[0] = "tryCatchBlocks";
                break;
            case 21:
                objArr[0] = "inst";
                break;
            case 22:
                objArr[0] = "curNonLocal";
                break;
            default:
                objArr[0] = "inlineFun";
                break;
        }
        if (i == 8) {
            objArr[1] = "rememberOriginalLabelNodes";
        } else if (i != 20) {
            objArr[1] = "org/jetbrains/kotlin/codegen/inline/InternalFinallyBlockInliner";
        } else {
            objArr[1] = "findTryCatchBlocksInlinedInFinally";
        }
        switch (i) {
            case 1:
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 3:
                objArr[2] = "<init>";
                break;
            case 4:
            case 5:
                objArr[2] = "copyInstruction";
                break;
            case 6:
                objArr[2] = "checkCoveringBlocksInvariant";
                break;
            case 7:
                objArr[2] = "rememberOriginalLabelNodes";
                break;
            case 8:
            case 20:
                break;
            case 9:
            case 10:
            case 11:
            case 12:
            case 13:
            case 14:
                objArr[2] = "updateExceptionTable";
                break;
            case 15:
                objArr[2] = "getNewOrOldLabel";
                break;
            case 16:
                objArr[2] = "getNewLabel";
                break;
            case 17:
            case 18:
                objArr[2] = "findFinallyBlockBody";
                break;
            case 19:
                objArr[2] = "findTryCatchBlocksInlinedInFinally";
                break;
            case 21:
                objArr[2] = "instructionIndex";
                break;
            case 22:
                objArr[2] = "flushCurrentState";
                break;
            default:
                objArr[2] = "processInlineFunFinallyBlocks";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 8 && i != 20) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    private InternalFinallyBlockInliner(MethodNode methodNode, List<TryCatchBlockNodeInfo> list, List<LocalVarNodeWrapper> list2, int i, boolean z) {
        super(i);
        if (methodNode == null) {
            $$$reportNull$$$0(1);
        }
        if (list == null) {
            $$$reportNull$$$0(2);
        }
        if (list2 == null) {
            $$$reportNull$$$0(3);
        }
        this.inlineFun = methodNode;
        this.properFinallySplit = z;
        Iterator<TryCatchBlockNodeInfo> it = list.iterator();
        while (it.hasNext()) {
            getTryBlocksMetaInfo().addNewInterval(it.next());
        }
        Iterator<LocalVarNodeWrapper> it2 = list2.iterator();
        while (it2.hasNext()) {
            getLocalVarsMetaInfo().addNewInterval(it2.next());
        }
    }

    public static /* synthetic */ LabelNode c(LabelNode labelNode) {
        return labelNode;
    }

    private static void checkClusterInvariant(List<TryBlockCluster<TryCatchBlockNodeInfo>> list) {
        Iterator it = Lists.reverse(list).iterator();
        while (it.hasNext()) {
            ((TryCatchBlockNodeInfo) ((TryBlockCluster) it.next()).getBlocks().get(0)).getOnlyCopyNotProcess();
        }
    }

    private static void checkCoveringBlocksInvariant(List<TryCatchBlockNodeInfo> list) {
        if (list == null) {
            $$$reportNull$$$0(6);
        }
        Iterator<TryCatchBlockNodeInfo> it = list.iterator();
        while (it.hasNext()) {
            it.next().getOnlyCopyNotProcess();
        }
    }

    private void checkFinally(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
        if (this.inlineFun.instructions.indexOf(abstractInsnNode) < this.inlineFun.instructions.indexOf(abstractInsnNode2)) {
            return;
        }
        pe1.a("Inconsistent finally: block end occurs before start ", traceInterval(abstractInsnNode2, abstractInsnNode));
    }

    private static void copyInstruction(MethodNode methodNode, AbstractInsnNode abstractInsnNode, boolean z, int i) {
        if (methodNode == null) {
            $$$reportNull$$$0(4);
        }
        if (abstractInsnNode == null) {
            $$$reportNull$$$0(5);
        }
        if (!z) {
            methodNode.instructions.add(new JumpInsnNode(abstractInsnNode.getOpcode(), ((JumpInsnNode) abstractInsnNode).label));
        } else if (InlineCodegenUtilsKt.isFinallyMarker(abstractInsnNode.getNext())) {
            methodNode.visitLdcInsn(Integer.valueOf(InlineCodegenUtilsKt.getConstant(abstractInsnNode) + i));
        } else {
            abstractInsnNode.accept(methodNode);
        }
    }

    public static /* synthetic */ void d(InternalFinallyBlockInliner internalFinallyBlockInliner, final LocalVarNodeWrapper localVarNodeWrapper) {
        internalFinallyBlockInliner.getClass();
        final LabelNode labelNodeFirstLabelInChain = InlineCodegenUtilsKt.firstLabelInChain(localVarNodeWrapper.getStartLabel());
        Optional optionalFindFirst = internalFinallyBlockInliner.getLocalVarsMetaInfo().getCurrentIntervals().stream().filter(new Predicate() { // from class: ou6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return ((LocalVarNodeWrapper) obj).getNode().name.equals(localVarNodeWrapper.getNode().name);
            }
        }).filter(new Predicate() { // from class: pu6
            @Override // java.util.function.Predicate
            public final boolean test(Object obj) {
                return InlineCodegenUtilsKt.firstLabelInChain(((LocalVarNodeWrapper) obj).getEndLabel()).equals(labelNodeFirstLabelInChain);
            }
        }).findFirst();
        if (!optionalFindFirst.isPresent()) {
            internalFinallyBlockInliner.getLocalVarsMetaInfo().addNewInterval(localVarNodeWrapper);
        } else {
            ((LocalVarNodeWrapper) optionalFindFirst.get()).getNode().end = localVarNodeWrapper.getEndLabel();
        }
    }

    public static /* synthetic */ void f(FinallyBlockInfo finallyBlockInfo, LabelNode labelNode, Map map, LabelNode labelNode2, final LocalVarNodeWrapper localVarNodeWrapper) {
        localVarNodeWrapper.remapLabel(finallyBlockInfo.closestLabelBeforeStart, labelNode);
        map.forEach(new BiConsumer() { // from class: nu6
            @Override // java.util.function.BiConsumer
            public final void accept(Object obj, Object obj2) {
                localVarNodeWrapper.remapLabel((LabelNode) obj, (LabelNode) obj2);
            }
        });
        localVarNodeWrapper.remapLabel(finallyBlockInfo.closestLabelAfterEnd, labelNode2);
    }

    private static LabelNode findClosestLabel(AbstractInsnNode abstractInsnNode) {
        while (abstractInsnNode != null) {
            if (abstractInsnNode instanceof LabelNode) {
                return (LabelNode) abstractInsnNode;
            }
            abstractInsnNode = abstractInsnNode.getNext();
        }
        return null;
    }

    private FinallyBlockInfo findFinallyBlockBody(TryCatchBlockNodeInfo tryCatchBlockNodeInfo, List<TryCatchBlockNodeInfo> list) {
        if (tryCatchBlockNodeInfo == null) {
            $$$reportNull$$$0(17);
        }
        if (list == null) {
            $$$reportNull$$$0(18);
        }
        ArrayList arrayList = new ArrayList();
        boolean z = false;
        LabelNode labelNodeFirstLabelInChain = null;
        for (TryCatchBlockNodeInfo tryCatchBlockNodeInfo2 : list) {
            if (tryCatchBlockNodeInfo == tryCatchBlockNodeInfo2) {
                z = true;
            }
            if (z && tryCatchBlockNodeInfo2.getNode().type == null && ((InlineCodegenUtilsKt.firstLabelInChain(tryCatchBlockNodeInfo.getNode().start) == InlineCodegenUtilsKt.firstLabelInChain(tryCatchBlockNodeInfo2.getNode().start) && InlineCodegenUtilsKt.firstLabelInChain(tryCatchBlockNodeInfo.getNode().end) == InlineCodegenUtilsKt.firstLabelInChain(tryCatchBlockNodeInfo2.getNode().end)) || labelNodeFirstLabelInChain == InlineCodegenUtilsKt.firstLabelInChain(tryCatchBlockNodeInfo2.getNode().handler))) {
                arrayList.add(tryCatchBlockNodeInfo2);
                if (labelNodeFirstLabelInChain == null) {
                    labelNodeFirstLabelInChain = InlineCodegenUtilsKt.firstLabelInChain(tryCatchBlockNodeInfo2.getNode().handler);
                }
            }
        }
        if (arrayList.isEmpty()) {
            return null;
        }
        TryCatchBlockNodeInfo tryCatchBlockNodeInfo3 = (TryCatchBlockNodeInfo) arrayList.get(1);
        LabelNode labelNode = tryCatchBlockNodeInfo.getNode().end;
        AbstractInsnNode nextMeaningful = MethodInlinerUtilKt.getNextMeaningful(labelNode);
        Integer numValueOf = Integer.valueOf(InlineCodegenUtilsKt.getConstant(nextMeaningful));
        AbstractInsnNode next = tryCatchBlockNodeInfo3.getNode().start;
        AbstractInsnNode next2 = nextMeaningful.getNext();
        while (next != next2) {
            next2 = next2.getNext();
            if (InlineCodegenUtilsKt.isFinallyEnd(next2) && Integer.valueOf(InlineCodegenUtilsKt.getConstant(next2.getPrevious())).equals(numValueOf)) {
                next = next2.getNext();
                break;
            }
        }
        AbstractInsnNode abstractInsnNode = next;
        LabelNode labelNodeFindClosestLabel = findClosestLabel(abstractInsnNode);
        Objects.requireNonNull(labelNodeFindClosestLabel);
        FinallyBlockInfo finallyBlockInfo = new FinallyBlockInfo(labelNode.getNext(), abstractInsnNode, labelNode, labelNodeFindClosestLabel);
        checkFinally(finallyBlockInfo);
        return finallyBlockInfo;
    }

    private List<TryCatchBlockNodePosition> findTryCatchBlocksInlinedInFinally(FinallyBlockInfo finallyBlockInfo) {
        if (finallyBlockInfo == null) {
            $$$reportNull$$$0(19);
        }
        ArrayList arrayList = new ArrayList();
        HashMap map = new HashMap();
        for (AbstractInsnNode next = finallyBlockInfo.startIns; next != finallyBlockInfo.endInsExclusive; next = next.getNext()) {
            if (next instanceof LabelNode) {
                LabelNode labelNode = (LabelNode) next;
                for (TryCatchBlockNodeInfo tryCatchBlockNodeInfo : getStartNodes(labelNode)) {
                    TryCatchBlockNodePosition tryCatchBlockNodePosition = new TryCatchBlockNodePosition(tryCatchBlockNodeInfo, TryCatchPosition.START);
                    map.put(tryCatchBlockNodeInfo, tryCatchBlockNodePosition);
                    arrayList.add(tryCatchBlockNodePosition);
                }
                for (TryCatchBlockNodeInfo tryCatchBlockNodeInfo2 : getEndNodes(labelNode)) {
                    TryCatchBlockNodePosition tryCatchBlockNodePosition2 = (TryCatchBlockNodePosition) map.get(tryCatchBlockNodeInfo2);
                    if (tryCatchBlockNodePosition2 != null) {
                        tryCatchBlockNodePosition2.setPosition(TryCatchPosition.INNER);
                    } else {
                        TryCatchBlockNodePosition tryCatchBlockNodePosition3 = new TryCatchBlockNodePosition(tryCatchBlockNodeInfo2, TryCatchPosition.END);
                        map.put(tryCatchBlockNodeInfo2, tryCatchBlockNodePosition3);
                        arrayList.add(tryCatchBlockNodePosition3);
                    }
                }
            }
        }
        return arrayList;
    }

    public static /* synthetic */ LabelNode g(LabelNode labelNode) {
        return (LabelNode) labelNode.getLabel().info;
    }

    private static LabelNode getNewLabel(LabelNode labelNode, Map<LabelNode, LabelNode> map) {
        if (map == null) {
            $$$reportNull$$$0(16);
        }
        return map.get(labelNode);
    }

    private static LabelNode getNewOrOldLabel(LabelNode labelNode, Map<LabelNode, LabelNode> map) {
        if (map == null) {
            $$$reportNull$$$0(15);
        }
        return map.getOrDefault(labelNode, labelNode);
    }

    private static boolean hasFinallyBlocks(List<TryCatchBlockNodeInfo> list) {
        for (TryCatchBlockNodeInfo tryCatchBlockNodeInfo : list) {
            if (!tryCatchBlockNodeInfo.getOnlyCopyNotProcess() && tryCatchBlockNodeInfo.getNode().type == null) {
                return true;
            }
        }
        return false;
    }

    private int initAndGetVarIndexForNonLocalReturnValue() {
        MethodNode methodNode = this.inlineFun;
        MaxLocalsCalculator maxLocalsCalculator = new MaxLocalsCalculator(589824, methodNode.access, methodNode.desc, null);
        this.inlineFun.accept(maxLocalsCalculator);
        return maxLocalsCalculator.getMaxLocals();
    }

    private void processInlineFunFinallyBlocks() {
        List<TryCatchBlockNodeInfo> listSortTryCatchBlocks;
        boolean z;
        AbstractInsnNode abstractInsnNode;
        int iInitAndGetVarIndexForNonLocalReturnValue = initAndGetVarIndexForNonLocalReturnValue();
        InsnList insnList = this.inlineFun.instructions;
        AbstractInsnNode last = insnList.getLast();
        while (true) {
            AbstractInsnNode previous = last;
            while (true) {
                if (previous == null) {
                    substituteTryBlockNodes(this.inlineFun);
                    substituteLocalVarTable(this.inlineFun);
                    return;
                }
                processInstruction(previous, false);
                if (InlineCodegenUtilsKt.isReturnOpcode(previous.getOpcode()) && InlineCodegenUtilsKt.isMarkedReturn(previous)) {
                    listSortTryCatchBlocks = sortTryCatchBlocks(new ArrayList<>(getTryBlocksMetaInfo().getCurrentIntervals()));
                    checkCoveringBlocksInvariant(Lists.reverse(listSortTryCatchBlocks));
                    if (!listSortTryCatchBlocks.isEmpty()) {
                        z = true;
                        if (!listSortTryCatchBlocks.get(listSortTryCatchBlocks.size() - 1).getOnlyCopyNotProcess()) {
                            break;
                        }
                    }
                    previous = previous.getPrevious();
                } else {
                    previous = previous.getPrevious();
                }
            }
            AbstractInsnNode previous2 = previous.getPrevious();
            AbstractInsnNode previous3 = previous2.getPrevious();
            LabelNode next = previous.getNext();
            AbstractInsnNode next2 = next.getNext();
            Integer lineNumberOrNull = InlineCodegenUtilsKt.getLineNumberOrNull(next2);
            Type returnType = InlineCodegenUtilsKt.getReturnType(previous.getOpcode());
            List<TryBlockCluster> listDoClustering = TryBlockClusteringKt.doClustering(listSortTryCatchBlocks);
            checkClusterInvariant(listDoClustering);
            List<TryCatchBlockNodeInfo> arrayList = new ArrayList<>();
            ArrayList arrayList2 = new ArrayList();
            int i = 0;
            for (TryBlockCluster tryBlockCluster : listDoClustering) {
                z = z;
                List<TryCatchBlockNodeInfo> blocks = tryBlockCluster.getBlocks();
                TryCatchBlockNodeInfo tryCatchBlockNodeInfo = blocks.get(blocks.size() - 1);
                LabelNode labelNode = next;
                final FinallyBlockInfo finallyBlockInfoFindFinallyBlockBody = findFinallyBlockBody(tryCatchBlockNodeInfo, getTryBlocksMetaInfo().getAllIntervals());
                if (finallyBlockInfoFindFinallyBlockBody == null) {
                    arrayList.addAll(tryBlockCluster.getBlocks());
                    next = labelNode;
                } else {
                    if (tryCatchBlockNodeInfo.getOnlyCopyNotProcess()) {
                        f63.a("Lambda try blocks should be skipped");
                        return;
                    }
                    int i2 = i + 1;
                    insnList.resetLabels();
                    List<TryCatchBlockNodePosition> listFindTryCatchBlocksInlinedInFinally = findTryCatchBlocksInlinedInFinally(finallyBlockInfoFindFinallyBlockBody);
                    MethodNode methodNodeCreateEmptyMethodNode = InlineCodegenUtilsKt.createEmptyMethodNode();
                    final LabelNode labelNodeLinkWithLabel = CodegenUtilKt.linkWithLabel(new LabelNode());
                    AbstractInsnNode abstractInsnNode2 = previous;
                    final LabelNode labelNodeLinkWithLabel2 = CodegenUtilKt.linkWithLabel(new LabelNode());
                    AbstractInsnNode abstractInsnNode3 = previous3;
                    boolean z2 = (returnType == Type.VOID_TYPE || finallyBlockInfoFindFinallyBlockBody.isEmpty()) ? false : z;
                    boolean z3 = z2;
                    if (z2) {
                        methodNodeCreateEmptyMethodNode.visitVarInsn(returnType.getOpcode(54), iInitAndGetVarIndexForNonLocalReturnValue);
                    }
                    methodNodeCreateEmptyMethodNode.visitLabel(labelNodeLinkWithLabel.getLabel());
                    Set<LabelNode> setRememberOriginalLabelNodes = rememberOriginalLabelNodes(finallyBlockInfoFindFinallyBlockBody);
                    AbstractInsnNode abstractInsnNode4 = next2;
                    Integer num = lineNumberOrNull;
                    List<TryCatchBlockNodeInfo> list = arrayList;
                    Collection collectionCopyIntervalsForRange = getLocalVarsMetaInfo().copyIntervalsForRange(insnList, finallyBlockInfoFindFinallyBlockBody.closestLabelBeforeStart, finallyBlockInfoFindFinallyBlockBody.closestLabelAfterEnd);
                    for (JumpInsnNode next3 = finallyBlockInfoFindFinallyBlockBody.startIns; next3 != finallyBlockInfoFindFinallyBlockBody.endInsExclusive; next3 = next3.getNext()) {
                        copyInstruction(methodNodeCreateEmptyMethodNode, next3, (!(next3 instanceof JumpInsnNode) || setRememberOriginalLabelNodes.contains(next3.label)) ? z : false, i2);
                    }
                    final Map<LabelNode, LabelNode> map = (Map) setRememberOriginalLabelNodes.stream().collect(Collectors.toMap(new Function() { // from class: qu6
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return InternalFinallyBlockInliner.c((LabelNode) obj);
                        }
                    }, new Function() { // from class: ru6
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return InternalFinallyBlockInliner.g((LabelNode) obj);
                        }
                    }));
                    if (z3) {
                        methodNodeCreateEmptyMethodNode.visitVarInsn(returnType.getOpcode(21), iInitAndGetVarIndexForNonLocalReturnValue);
                        iInitAndGetVarIndexForNonLocalReturnValue += returnType.getSize();
                    }
                    int i3 = iInitAndGetVarIndexForNonLocalReturnValue;
                    methodNodeCreateEmptyMethodNode.visitLabel(labelNodeLinkWithLabel2.getLabel());
                    collectionCopyIntervalsForRange.forEach(new Consumer() { // from class: org.jetbrains.kotlin.codegen.inline.a
                        @Override // java.util.function.Consumer
                        public final void accept(Object obj) {
                            InternalFinallyBlockInliner.f(finallyBlockInfoFindFinallyBlockBody, labelNodeLinkWithLabel, map, labelNodeLinkWithLabel2, (LocalVarNodeWrapper) obj);
                        }
                    });
                    arrayList2.addAll(collectionCopyIntervalsForRange);
                    InlineCodegenUtilsKt.insertNodeBefore(methodNodeCreateEmptyMethodNode, this.inlineFun, previous2);
                    insnList.resetLabels();
                    AbstractInsnNode first = methodNodeCreateEmptyMethodNode.instructions.getFirst();
                    Integer firstFinallyOperationLineNumberOrNull = InlineCodegenUtilsKt.getFirstFinallyOperationLineNumberOrNull(finallyBlockInfoFindFinallyBlockBody.startIns, finallyBlockInfoFindFinallyBlockBody.endInsExclusive);
                    Integer firstFinallyOperationLineNumberOrNull2 = InlineCodegenUtilsKt.getFirstFinallyOperationLineNumberOrNull(first, previous2);
                    if (firstFinallyOperationLineNumberOrNull != null && !firstFinallyOperationLineNumberOrNull.equals(firstFinallyOperationLineNumberOrNull2)) {
                        LabelNode labelNode2 = new LabelNode();
                        this.inlineFun.instructions.insertBefore(first, labelNode2);
                        this.inlineFun.instructions.insertBefore(first, new LineNumberNode(firstFinallyOperationLineNumberOrNull.intValue(), labelNode2));
                    }
                    List<TryCatchBlockNodeInfo> list2 = list;
                    list2.addAll(blocks);
                    if (!this.properFinallySplit) {
                        list2 = blocks;
                    }
                    next = labelNode;
                    ArrayList arrayList3 = arrayList2;
                    updateExceptionTable(list2, labelNodeLinkWithLabel, next, listFindTryCatchBlocksInlinedInFinally, map, labelNodeLinkWithLabel2);
                    list2.clear();
                    arrayList2 = arrayList3;
                    i = i2;
                    iInitAndGetVarIndexForNonLocalReturnValue = i3;
                    previous3 = abstractInsnNode3;
                    previous = abstractInsnNode2;
                    next2 = abstractInsnNode4;
                    lineNumberOrNull = num;
                    arrayList = list2;
                }
            }
            AbstractInsnNode abstractInsnNode5 = previous;
            boolean z4 = z;
            AbstractInsnNode abstractInsnNode6 = previous3;
            AbstractInsnNode abstractInsnNode7 = next2;
            Integer num2 = lineNumberOrNull;
            ArrayList arrayList4 = arrayList2;
            if (num2 != null && !num2.equals(InlineCodegenUtilsKt.getLineNumberOrNull(abstractInsnNode7))) {
                LabelNode labelNode3 = new LabelNode();
                this.inlineFun.instructions.insertBefore(abstractInsnNode7, labelNode3);
                this.inlineFun.instructions.insertBefore(abstractInsnNode7, new LineNumberNode(num2.intValue(), labelNode3));
            }
            last = abstractInsnNode5.getPrevious();
            while (true) {
                abstractInsnNode = abstractInsnNode6;
                if (last == null || last == abstractInsnNode) {
                    break;
                }
                processInstruction(last, false);
                last = last.getPrevious();
                abstractInsnNode6 = abstractInsnNode;
            }
            if (previous2.getPrevious() != abstractInsnNode && last != null) {
                LabelNode labelNode4 = new LabelNode();
                LabelNode labelNode5 = new LabelNode();
                insnList.insert(last, labelNode4);
                insnList.insert(abstractInsnNode5, labelNode5);
                getLocalVarsMetaInfo().splitCurrentIntervals(new SimpleInterval(labelNode4, labelNode5), z4);
                arrayList4.forEach(new Consumer() { // from class: su6
                    @Override // java.util.function.Consumer
                    public final void accept(Object obj) {
                        InternalFinallyBlockInliner.d(this.b, (LocalVarNodeWrapper) obj);
                    }
                });
            }
        }
    }

    private static Set<LabelNode> rememberOriginalLabelNodes(FinallyBlockInfo finallyBlockInfo) {
        if (finallyBlockInfo == null) {
            $$$reportNull$$$0(7);
        }
        HashSet hashSet = new HashSet();
        for (LabelNode next = finallyBlockInfo.startIns; next != finallyBlockInfo.endInsExclusive; next = next.getNext()) {
            if (next instanceof LabelNode) {
                hashSet.add(next);
            }
        }
        return hashSet;
    }

    private static String traceInterval(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
        Textifier textifier = new Textifier();
        TraceMethodVisitor traceMethodVisitor = new TraceMethodVisitor(textifier);
        while (abstractInsnNode != abstractInsnNode2) {
            abstractInsnNode.accept(traceMethodVisitor);
            abstractInsnNode = abstractInsnNode.getNext();
        }
        abstractInsnNode.accept(traceMethodVisitor);
        StringWriter stringWriter = new StringWriter();
        textifier.print(new PrintWriter(stringWriter));
        return stringWriter.toString();
    }

    private void updateExceptionTable(List<TryCatchBlockNodeInfo> list, LabelNode labelNode, LabelNode labelNode2, List<TryCatchBlockNodePosition> list2, Map<LabelNode, LabelNode> map, LabelNode labelNode3) {
        if (list == null) {
            $$$reportNull$$$0(9);
        }
        if (labelNode == null) {
            $$$reportNull$$$0(10);
        }
        if (labelNode2 == null) {
            $$$reportNull$$$0(11);
        }
        if (list2 == null) {
            $$$reportNull$$$0(12);
        }
        if (map == null) {
            $$$reportNull$$$0(13);
        }
        if (labelNode3 == null) {
            $$$reportNull$$$0(14);
        }
        List<TryBlockCluster> listDoClustering = TryBlockClusteringKt.doClustering(list2);
        HashMap map2 = new HashMap();
        IntervalMetaInfo<TryCatchBlockNodeInfo> tryBlocksMetaInfo = getTryBlocksMetaInfo();
        for (TryBlockCluster tryBlockCluster : listDoClustering) {
            List blocks = tryBlockCluster.getBlocks();
            TryCatchPosition position = ((TryCatchBlockNodePosition) blocks.get(0)).getPosition();
            if (position == TryCatchPosition.INNER) {
                Iterator it = blocks.iterator();
                while (it.hasNext()) {
                    TryCatchBlockNode node = ((TryCatchBlockNodePosition) it.next()).getNodeInfo().getNode();
                    tryBlocksMetaInfo.addNewInterval(new TryCatchBlockNodeInfo(new TryCatchBlockNode(getNewLabel(node.start, map), getNewLabel(node.end, map), getNewOrOldLabel(node.handler, map), node.type), true));
                }
            } else if (position == TryCatchPosition.END) {
                map2.put(((TryCatchBlockNodePosition) tryBlockCluster.getDefaultHandler()).getHandler(), tryBlockCluster);
            } else {
                TryBlockCluster tryBlockCluster2 = (TryBlockCluster) map2.remove(((TryCatchBlockNodePosition) tryBlockCluster.getDefaultHandler()).getHandler());
                Iterator it2 = blocks.iterator();
                for (TryCatchBlockNodePosition tryCatchBlockNodePosition : tryBlockCluster2.getBlocks()) {
                    TryCatchBlockNodeInfo nodeInfo = ((TryCatchBlockNodePosition) it2.next()).getNodeInfo();
                    TryCatchBlockNodeInfo nodeInfo2 = tryCatchBlockNodePosition.getNodeInfo();
                    getTryBlocksMetaInfo().split(nodeInfo2, new SimpleInterval(getNewLabel(nodeInfo2.getNode().end, map), getNewLabel(nodeInfo.getStartLabel(), map)), false);
                }
            }
        }
        if (map2.size() == 1) {
            TryBlockCluster tryBlockCluster3 = (TryBlockCluster) map2.values().iterator().next();
            if (((TryCatchBlockNodePosition) tryBlockCluster3.getBlocks().get(0)).getPosition() == TryCatchPosition.END) {
                Iterator it3 = tryBlockCluster3.getBlocks().iterator();
                while (it3.hasNext()) {
                    TryCatchBlockNodeInfo nodeInfo3 = ((TryCatchBlockNodePosition) it3.next()).getNodeInfo();
                    getTryBlocksMetaInfo().split(nodeInfo3, new SimpleInterval(getNewLabel(nodeInfo3.getNode().end, map), labelNode3), false);
                }
                map2.clear();
            }
        }
        SimpleInterval simpleInterval = new SimpleInterval(labelNode, labelNode2);
        Iterator<TryCatchBlockNodeInfo> it4 = list.iterator();
        while (it4.hasNext()) {
            SplitPair<T> splitPairSplitAndRemoveIntervalFromCurrents = tryBlocksMetaInfo.splitAndRemoveIntervalFromCurrents(it4.next(), simpleInterval, false);
            checkFinally((IntervalWithHandler) splitPairSplitAndRemoveIntervalFromCurrents.getNewPart());
            checkFinally((IntervalWithHandler) splitPairSplitAndRemoveIntervalFromCurrents.getPatchedPart());
        }
        sortTryCatchBlocks(tryBlocksMetaInfo.getAllIntervals());
    }

    @Override // org.jetbrains.kotlin.codegen.inline.CoveringTryCatchNodeProcessor
    public int instructionIndex(AbstractInsnNode abstractInsnNode) {
        if (abstractInsnNode == null) {
            $$$reportNull$$$0(21);
        }
        return this.inlineFun.instructions.indexOf(abstractInsnNode);
    }

    private void checkFinally(IntervalWithHandler intervalWithHandler) {
        checkFinally(intervalWithHandler.getStartLabel(), intervalWithHandler.getEndLabel());
    }

    private void checkFinally(FinallyBlockInfo finallyBlockInfo) {
        checkFinally(finallyBlockInfo.startIns, finallyBlockInfo.endInsExclusive);
    }

    public static void processInlineFunFinallyBlocks(MethodNode methodNode, int i, int i2, boolean z) {
        if (methodNode == null) {
            $$$reportNull$$$0(0);
        }
        ArrayList arrayList = new ArrayList();
        Iterator it = methodNode.tryCatchBlocks.iterator();
        int i3 = 0;
        while (it.hasNext()) {
            int i4 = i3 + 1;
            arrayList.add(new TryCatchBlockNodeInfo((TryCatchBlockNode) it.next(), i3 < i));
            i3 = i4;
        }
        ArrayList arrayList2 = new ArrayList();
        Iterator it2 = methodNode.localVariables.iterator();
        while (it2.hasNext()) {
            arrayList2.add(new LocalVarNodeWrapper((LocalVariableNode) it2.next()));
        }
        if (hasFinallyBlocks(arrayList)) {
            new InternalFinallyBlockInliner(methodNode, arrayList, arrayList2, i2, z).processInlineFunFinallyBlocks();
        }
    }
}
