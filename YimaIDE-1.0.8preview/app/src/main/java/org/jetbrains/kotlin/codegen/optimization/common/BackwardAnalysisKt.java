package org.jetbrains.kotlin.codegen.optimization.common;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.repl.BasicReplStageHistory;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a2\u0010\u0000\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0001\"\u000e\b\u0000\u0010\u0002*\b\u0012\u0004\u0012\u0002H\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u0002H\u00020\u0007¨\u0006\b"}, d2 = {"analyze", Argument.Delimiters.none, "F", "Lorg/jetbrains/kotlin/codegen/optimization/common/VarFrame;", "node", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "interpreter", "Lorg/jetbrains/kotlin/codegen/optimization/common/BackwardAnalysisInterpreter;", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class BackwardAnalysisKt {
    /* JADX WARN: Multi-variable type inference failed */
    public static final <F extends VarFrame<F>> List<F> analyze(MethodNode methodNode, BackwardAnalysisInterpreter<F> backwardAnalysisInterpreter) {
        boolean z;
        methodNode.getClass();
        backwardAnalysisInterpreter.getClass();
        ControlFlowGraph controlFlowGraphBuild$default = ControlFlowGraph.Companion.build$default(ControlFlowGraph.INSTANCE, methodNode, false, 2, null);
        InsnList insnList = methodNode.instructions;
        IntRange intRange = new IntRange(1, insnList.size());
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(intRange, 10));
        IntIterator it = intRange.iterator();
        while (it.hasNext()) {
            it.nextInt();
            arrayList.add(backwardAnalysisInterpreter.newFrame(methodNode.maxLocals));
        }
        BasicReplStageHistory basicReplStageHistory = (List<F>) CollectionsKt.toMutableList(arrayList);
        AbstractInsnNode[] array = insnList.toArray();
        do {
            int length = array.length - 1;
            z = false;
            if (length >= 0) {
                while (true) {
                    int i = length - 1;
                    AbstractInsnNode abstractInsnNode = array[length];
                    VarFrame varFrameNewFrame = backwardAnalysisInterpreter.newFrame(methodNode.maxLocals);
                    abstractInsnNode.getClass();
                    Iterator<Integer> it2 = controlFlowGraphBuild$default.getSuccessorsIndices(abstractInsnNode).iterator();
                    while (it2.hasNext()) {
                        varFrameNewFrame.mergeFrom((VarFrame) basicReplStageHistory.get(it2.next().intValue()));
                    }
                    if (controlFlowGraphBuild$default.getPredecessorsIndices(abstractInsnNode).size() > 1) {
                        varFrameNewFrame.markControlFlowMerge();
                    }
                    backwardAnalysisInterpreter.def(varFrameNewFrame, abstractInsnNode);
                    backwardAnalysisInterpreter.use(varFrameNewFrame, abstractInsnNode);
                    if (!Intrinsics.areEqual(basicReplStageHistory.get(length), varFrameNewFrame)) {
                        basicReplStageHistory.set(length, varFrameNewFrame);
                        z = true;
                    }
                    if (i < 0) {
                        break;
                    }
                    length = i;
                }
            }
        } while (z);
        return basicReplStageHistory;
    }
}
