package org.jetbrains.kotlin.codegen.inline;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineCodegenUtilKt;
import org.jetbrains.kotlin.codegen.inline.CoveringTryCatchNodeProcessor;
import org.jetbrains.kotlin.codegen.inline.TryCatchBlockNodeInfo;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.IincInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\b&\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\b0\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0014\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\b0\u00132\u0006\u0010\u0014\u001a\u00020\u0015J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0016J\u0010\u0010\u001d\u001a\u00020\u00032\u0006\u0010\u001e\u001a\u00020\u001aH&J\u001a\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\b0\u00132\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\b0\u0013J\u000e\u0010!\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020#J\u000e\u0010$\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020#R\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\b0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\u0007¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\nR\u001e\u0010\u000f\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u0003@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/CoveringTryCatchNodeProcessor;", Argument.Delimiters.none, "parameterSize", Argument.Delimiters.none, "<init>", "(I)V", "tryBlocksMetaInfo", "Lorg/jetbrains/kotlin/codegen/inline/IntervalMetaInfo;", "Lorg/jetbrains/kotlin/codegen/inline/TryCatchBlockNodeInfo;", "getTryBlocksMetaInfo", "()Lorg/jetbrains/kotlin/codegen/inline/IntervalMetaInfo;", "localVarsMetaInfo", "Lorg/jetbrains/kotlin/codegen/inline/LocalVarNodeWrapper;", "getLocalVarsMetaInfo", "value", "nextFreeLocalIndex", "getNextFreeLocalIndex", "()I", "getStartNodes", Argument.Delimiters.none, CoroutineCodegenUtilKt.COROUTINE_LABEL_FIELD_NAME, "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "getEndNodes", "processInstruction", Argument.Delimiters.none, "curInstr", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "directOrder", Argument.Delimiters.none, "instructionIndex", "inst", "sortTryCatchBlocks", "intervals", "substituteTryBlockNodes", "node", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "substituteLocalVarTable", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class CoveringTryCatchNodeProcessor {
    private int nextFreeLocalIndex;
    private final IntervalMetaInfo<TryCatchBlockNodeInfo> tryBlocksMetaInfo = new IntervalMetaInfo<>(this);
    private final IntervalMetaInfo<LocalVarNodeWrapper> localVarsMetaInfo = new IntervalMetaInfo<>(this);

    public CoveringTryCatchNodeProcessor(int i) {
        this.nextFreeLocalIndex = i;
    }

    public static int a(CoveringTryCatchNodeProcessor coveringTryCatchNodeProcessor, TryCatchBlockNodeInfo tryCatchBlockNodeInfo, TryCatchBlockNodeInfo tryCatchBlockNodeInfo2) {
        tryCatchBlockNodeInfo.getClass();
        tryCatchBlockNodeInfo2.getClass();
        int iInstructionIndex = coveringTryCatchNodeProcessor.instructionIndex(tryCatchBlockNodeInfo.getHandler()) - coveringTryCatchNodeProcessor.instructionIndex(tryCatchBlockNodeInfo2.getHandler());
        return (iInstructionIndex == 0 && (iInstructionIndex = coveringTryCatchNodeProcessor.instructionIndex(tryCatchBlockNodeInfo.getStartLabel()) - coveringTryCatchNodeProcessor.instructionIndex(tryCatchBlockNodeInfo2.getStartLabel())) == 0) ? coveringTryCatchNodeProcessor.instructionIndex(tryCatchBlockNodeInfo.getEndLabel()) - coveringTryCatchNodeProcessor.instructionIndex(tryCatchBlockNodeInfo2.getEndLabel()) : iInstructionIndex;
    }

    public final List<TryCatchBlockNodeInfo> getEndNodes(LabelNode label) {
        label.getClass();
        List<TryCatchBlockNodeInfo> list = this.tryBlocksMetaInfo.getIntervalEnds().get(label);
        list.getClass();
        return list;
    }

    public final IntervalMetaInfo<LocalVarNodeWrapper> getLocalVarsMetaInfo() {
        return this.localVarsMetaInfo;
    }

    public final int getNextFreeLocalIndex() {
        return this.nextFreeLocalIndex;
    }

    public final List<TryCatchBlockNodeInfo> getStartNodes(LabelNode label) {
        label.getClass();
        List<TryCatchBlockNodeInfo> list = this.tryBlocksMetaInfo.getIntervalStarts().get(label);
        list.getClass();
        return list;
    }

    public final IntervalMetaInfo<TryCatchBlockNodeInfo> getTryBlocksMetaInfo() {
        return this.tryBlocksMetaInfo;
    }

    public abstract int instructionIndex(AbstractInsnNode inst);

    public void processInstruction(AbstractInsnNode curInstr, boolean directOrder) {
        curInstr.getClass();
        if ((curInstr instanceof VarInsnNode) || (curInstr instanceof IincInsnNode)) {
            this.nextFreeLocalIndex = Math.max(this.nextFreeLocalIndex, (curInstr instanceof VarInsnNode ? ((VarInsnNode) curInstr).var : ((IincInsnNode) curInstr).var) + InlineCodegenUtilsKt.getLoadStoreArgSize(curInstr.getOpcode()));
        }
        if (curInstr instanceof LabelNode) {
            LabelNode labelNode = (LabelNode) curInstr;
            this.tryBlocksMetaInfo.processCurrent(labelNode, directOrder);
            this.localVarsMetaInfo.processCurrent(labelNode, directOrder);
        }
    }

    public final List<TryCatchBlockNodeInfo> sortTryCatchBlocks(List<TryCatchBlockNodeInfo> intervals) {
        intervals.getClass();
        Collections.sort(intervals, new Comparator() { // from class: u13
            @Override // java.util.Comparator
            public final int compare(Object obj, Object obj2) {
                return CoveringTryCatchNodeProcessor.a(this.b, (TryCatchBlockNodeInfo) obj, (TryCatchBlockNodeInfo) obj2);
            }
        });
        return intervals;
    }

    public final void substituteLocalVarTable(MethodNode node) {
        node.getClass();
        node.localVariables.clear();
        Iterator it = CoveringTryCatchNodeProcessorKt.getMeaningfulIntervals(this.localVarsMetaInfo).iterator();
        while (it.hasNext()) {
            node.localVariables.add(((LocalVarNodeWrapper) it.next()).getNode());
        }
    }

    public final void substituteTryBlockNodes(MethodNode node) {
        node.getClass();
        node.tryCatchBlocks.clear();
        sortTryCatchBlocks(this.tryBlocksMetaInfo.getAllIntervals());
        Iterator it = CoveringTryCatchNodeProcessorKt.getMeaningfulIntervals(this.tryBlocksMetaInfo).iterator();
        while (it.hasNext()) {
            node.tryCatchBlocks.add(((TryCatchBlockNodeInfo) it.next()).getNode());
        }
    }
}
