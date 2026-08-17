package org.jetbrains.kotlin.codegen.coroutines;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.ranges.IntRange;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.InsnSequence;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.boxing.PopBackwardPropagationTransformerKt;
import org.jetbrains.kotlin.codegen.optimization.common.ControlFlowGraph;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.optimization.fixStack.StackTransformationUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.kotlin.utils.addToStdlib.AddToStdlibKt;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\r\n\u0002\u0010\"\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u001a\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0000\u001a\f\u0010\u0006\u001a\u00020\u0001*\u00020\u0005H\u0002\u001a\f\u0010\u0007\u001a\u00020\u0001*\u00020\u0005H\u0002\u001a\u000e\u0010\b\u001a\u0004\u0018\u00010\t*\u00020\tH\u0002\u001a\u001a\u0010\n\u001a\u00020\u000b*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004H\u0000\u001a\u0018\u0010\f\u001a\u0004\u0018\u00010\t*\u0004\u0018\u00010\t2\u0006\u0010\r\u001a\u00020\u0001H\u0002\u001a\u0016\u0010\u0016\u001a\u00020\u0001*\u0004\u0018\u00010\t2\u0006\u0010\u0017\u001a\u00020\u0002H\u0002\u001a\u0010\u0010\u001b\u001a\u0004\u0018\u00010\u001c*\u0004\u0018\u00010\u001cH\u0002\"\u001a\u0010\u000e\u001a\u0004\u0018\u00010\t*\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u0010\"\u001a\u0010\u0011\u001a\u0004\u0018\u00010\t*\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0010\"\u0018\u0010\u0013\u001a\u00020\u0001*\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u0014\"\u0018\u0010\u0015\u001a\u00020\u0001*\u00020\t8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0014\"\u0014\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u001a0\u0019X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"allSuspensionPointsAreTailCalls", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "suspensionPoints", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/coroutines/SuspensionPoint;", "isNoCall", "isUnitSuspendCall", "skipBeforeSuspendMarker", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "addCoroutineSuspendedChecks", Argument.Delimiters.none, "skipUntilMeaningful", "skipSuspendMarkers", "nextMeaningful", "getNextMeaningful", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "nextMeaningfulOrMarker", "getNextMeaningfulOrMarker", "isReturnUnit", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)Z", "isPopBeforeReturnUnit", "isInvisibleInDebugVarInsn", "methodNode", "SAFE_OPCODES", Argument.Delimiters.none, Argument.Delimiters.none, "toFromSuspensionPoint", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TailCallOptimizationKt {
    private static final Set<Integer> SAFE_OPCODES;

    static {
        Set setCreateSetBuilder = SetsKt.createSetBuilder();
        setCreateSetBuilder.add(0);
        Set set = setCreateSetBuilder;
        CollectionsKt.addAll(set, new IntRange(87, 95));
        CollectionsKt.addAll(set, new IntRange(153, 167));
        setCreateSetBuilder.add(192);
        SAFE_OPCODES = SetsKt.build(setCreateSetBuilder);
    }

    public static final void addCoroutineSuspendedChecks(MethodNode methodNode, List<SuspensionPoint> list) {
        methodNode.getClass();
        list.getClass();
        for (SuspensionPoint suspensionPoint : list) {
            AbstractInsnNode nextMeaningful = getNextMeaningful(suspensionPoint.getSuspensionCallEnd());
            if (nextMeaningful == null || nextMeaningful.getOpcode() != 176) {
                InsnList insnList = methodNode.instructions;
                AbstractInsnNode suspensionCallEnd = suspensionPoint.getSuspensionCallEnd();
                MethodNode methodNode2 = new MethodNode();
                InstructionAdapter instructionAdapter = new InstructionAdapter(methodNode2);
                Label label = new Label();
                instructionAdapter.dup();
                CoroutineCodegenUtilKt.loadCoroutineSuspendedMarker(instructionAdapter);
                instructionAdapter.ifacmpne(label);
                instructionAdapter.areturn(AsmTypes.OBJECT_TYPE);
                instructionAdapter.mark(label);
                Unit unit = Unit.INSTANCE;
                InsnList insnList2 = methodNode2.instructions;
                insnList2.getClass();
                insnList.insert(suspensionCallEnd, insnList2);
            }
        }
    }

    public static final boolean allSuspensionPointsAreTailCalls(MethodNode methodNode, List<SuspensionPoint> list) {
        methodNode.getClass();
        list.getClass();
        Frame[] frameArrAnalyze = MethodTransformer.analyze("fake", methodNode, new TcoInterpreter(list));
        frameArrAnalyze.getClass();
        ControlFlowGraph controlFlowGraphBuild$default = ControlFlowGraph.Companion.build$default(ControlFlowGraph.INSTANCE, methodNode, false, 2, null);
        List<SuspensionPoint> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return true;
        }
        for (SuspensionPoint suspensionPoint : list2) {
            int iIndexOf = methodNode.instructions.indexOf(suspensionPoint.getSuspensionCallBegin());
            List list3 = methodNode.tryCatchBlocks;
            list3.getClass();
            List<TryCatchBlockNode> list4 = list3;
            if (!(list4 instanceof Collection) || !list4.isEmpty()) {
                for (TryCatchBlockNode tryCatchBlockNode : list4) {
                    if (iIndexOf < methodNode.instructions.indexOf(tryCatchBlockNode.start) || methodNode.instructions.indexOf(tryCatchBlockNode.end) <= iIndexOf) {
                    }
                }
                if (!isNoCall(suspensionPoint) || allSuspensionPointsAreTailCalls$transitiveSuccessorsAreSafeOrReturns(suspensionPoint.getSuspensionCallEnd(), frameArrAnalyze, methodNode, controlFlowGraphBuild$default, isUnitSuspendCall(suspensionPoint))) {
                }
            } else if (!isNoCall(suspensionPoint)) {
            }
            return false;
        }
        return true;
    }

    private static final boolean allSuspensionPointsAreTailCalls$isPartOfSuspendInlineMarker(AbstractInsnNode abstractInsnNode) {
        AbstractInsnNode next;
        return InlineCodegenUtilsKt.isSuspendInlineMarker(abstractInsnNode) || ((next = abstractInsnNode.getNext()) != null && InlineCodegenUtilsKt.isSuspendInlineMarker(next));
    }

    private static final boolean allSuspensionPointsAreTailCalls$isSafe(AbstractInsnNode abstractInsnNode, MethodNode methodNode) {
        return !UtilKt.isMeaningful(abstractInsnNode) || SAFE_OPCODES.contains(Integer.valueOf(abstractInsnNode.getOpcode())) || isInvisibleInDebugVarInsn(abstractInsnNode, methodNode) || InlineCodegenUtilsKt.isInlineMarker(abstractInsnNode) || allSuspensionPointsAreTailCalls$isPartOfSuspendInlineMarker(abstractInsnNode);
    }

    private static final boolean allSuspensionPointsAreTailCalls$transitiveSuccessorsAreSafeOrReturns(AbstractInsnNode abstractInsnNode, Frame<BasicValue>[] frameArr, MethodNode methodNode, ControlFlowGraph controlFlowGraph, boolean z) {
        Set setMutableSetOf = SetsKt.mutableSetOf(new AbstractInsnNode[]{abstractInsnNode});
        List listMutableListOf = CollectionsKt.mutableListOf(new AbstractInsnNode[]{abstractInsnNode});
        while (true) {
            if (listMutableListOf.isEmpty()) {
                return true;
            }
            AbstractInsnNode abstractInsnNode2 = (AbstractInsnNode) AddToStdlibKt.popLast(listMutableListOf);
            if (abstractInsnNode2.getOpcode() == 176 || (z && isPopBeforeReturnUnit(abstractInsnNode2))) {
                Frame<BasicValue> frame = frameArr[methodNode.instructions.indexOf(abstractInsnNode2)];
                BasicValue basicValue = frame != null ? (BasicValue) StackTransformationUtilsKt.top(frame) : null;
                if (!(basicValue != null ? basicValue instanceof FromSuspensionPointValue : true)) {
                    return false;
                }
            } else {
                if (abstractInsnNode2 != abstractInsnNode && !allSuspensionPointsAreTailCalls$isSafe(abstractInsnNode2, methodNode)) {
                    return false;
                }
                Iterator<Integer> it = controlFlowGraph.getSuccessorsIndices(abstractInsnNode2).iterator();
                while (it.hasNext()) {
                    AbstractInsnNode abstractInsnNode3 = methodNode.instructions.get(it.next().intValue());
                    abstractInsnNode3.getClass();
                    if (setMutableSetOf.add(abstractInsnNode3)) {
                        listMutableListOf.add(abstractInsnNode3);
                    }
                }
            }
        }
    }

    private static final AbstractInsnNode getNextMeaningful(AbstractInsnNode abstractInsnNode) {
        return skipUntilMeaningful(abstractInsnNode.getNext(), true);
    }

    private static final AbstractInsnNode getNextMeaningfulOrMarker(AbstractInsnNode abstractInsnNode) {
        return skipUntilMeaningful(abstractInsnNode.getNext(), false);
    }

    private static final boolean isInvisibleInDebugVarInsn(AbstractInsnNode abstractInsnNode, MethodNode methodNode) {
        InsnList insnList = methodNode.instructions;
        int iIndexOf = insnList.indexOf(abstractInsnNode);
        if (!(abstractInsnNode instanceof VarInsnNode)) {
            return false;
        }
        List list = methodNode.localVariables;
        list.getClass();
        List<LocalVariableNode> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return true;
        }
        for (LocalVariableNode localVariableNode : list2) {
            if (localVariableNode.index == ((VarInsnNode) abstractInsnNode).var) {
                int iIndexOf2 = insnList.indexOf(localVariableNode.start);
                if (iIndexOf <= insnList.indexOf(localVariableNode.end) && iIndexOf2 <= iIndexOf) {
                    return false;
                }
            }
        }
        return true;
    }

    private static final boolean isNoCall(SuspensionPoint suspensionPoint) {
        AbstractInsnNode next = suspensionPoint.getSuspensionCallBegin().getNext();
        next.getClass();
        for (AbstractInsnNode abstractInsnNode : new InsnSequence(next, suspensionPoint.getSuspensionCallEnd())) {
            int opcode = abstractInsnNode.getOpcode();
            if (opcode == 182 || opcode == 183 || opcode == 185 || opcode == 186 || (opcode == 184 && !InlineCodegenUtilsKt.isInlineMarker(abstractInsnNode) && !InlineCodegenUtilsKt.isSuspendInlineMarker(abstractInsnNode))) {
                return false;
            }
        }
        return true;
    }

    private static final boolean isPopBeforeReturnUnit(AbstractInsnNode abstractInsnNode) {
        AbstractInsnNode nextMeaningful;
        return abstractInsnNode.getOpcode() == 87 && (nextMeaningful = getNextMeaningful(abstractInsnNode)) != null && isReturnUnit(nextMeaningful);
    }

    private static final boolean isReturnUnit(AbstractInsnNode abstractInsnNode) {
        AbstractInsnNode nextMeaningful;
        if (!PopBackwardPropagationTransformerKt.isUnitInstance(abstractInsnNode) || (nextMeaningful = getNextMeaningful(abstractInsnNode)) == null) {
            return false;
        }
        return nextMeaningful.getOpcode() == 176 || isPopBeforeReturnUnit(nextMeaningful);
    }

    private static final boolean isUnitSuspendCall(SuspensionPoint suspensionPoint) {
        AbstractInsnNode nextMeaningfulOrMarker;
        AbstractInsnNode abstractInsnNodeSkipBeforeSuspendMarker = skipBeforeSuspendMarker(suspensionPoint.getSuspensionCallBegin());
        if (abstractInsnNodeSkipBeforeSuspendMarker == null || (nextMeaningfulOrMarker = getNextMeaningfulOrMarker(abstractInsnNodeSkipBeforeSuspendMarker)) == null) {
            return false;
        }
        return InlineCodegenUtilsKt.isBeforeSuspendUnitCallMarker(nextMeaningfulOrMarker);
    }

    private static final AbstractInsnNode skipBeforeSuspendMarker(AbstractInsnNode abstractInsnNode) {
        if (abstractInsnNode.getNext() != null) {
            AbstractInsnNode next = abstractInsnNode.getNext();
            next.getClass();
            if (InlineCodegenUtilsKt.isBeforeSuspendMarker(next)) {
                return abstractInsnNode.getNext().getNext();
            }
        }
        w01.a("Expected BeforeSuspendMarker");
        return null;
    }

    private static final AbstractInsnNode skipUntilMeaningful(AbstractInsnNode abstractInsnNode, boolean z) {
        if (abstractInsnNode == null) {
            return null;
        }
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        while (abstractInsnNode != null && linkedHashSet.add(abstractInsnNode)) {
            if (abstractInsnNode.getOpcode() == 0 || !UtilKt.isMeaningful(abstractInsnNode)) {
                abstractInsnNode = abstractInsnNode.getNext();
            } else {
                if (z && abstractInsnNode.getNext() != null) {
                    AbstractInsnNode next = abstractInsnNode.getNext();
                    next.getClass();
                    if (InlineCodegenUtilsKt.isSuspendInlineMarker(next)) {
                        abstractInsnNode = abstractInsnNode.getNext().getNext();
                    }
                }
                if (abstractInsnNode.getOpcode() != 167) {
                    return abstractInsnNode;
                }
                abstractInsnNode = ((JumpInsnNode) abstractInsnNode).label;
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final BasicValue toFromSuspensionPoint(BasicValue basicValue) {
        Type type;
        return (basicValue == null || (type = basicValue.getType()) == null || type.getSort() != 10) ? basicValue : FromSuspensionPointValue.INSTANCE;
    }
}
