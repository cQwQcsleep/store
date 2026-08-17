package org.jetbrains.kotlin.codegen.optimization.boxing;

import kotlin.Metadata;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.optimization.common.FastAnalyzer;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.InsnNode;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LdcInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\n\u001a\u00020\u000b2\u0006\u0010\b\u001a\u00020\tH\u0002J\f\u0010\f\u001a\u00020\u000b*\u00020\rH\u0002J\f\u0010\u000e\u001a\u00020\u000b*\u00020\rH\u0002J\f\u0010\u000f\u001a\u00020\u000b*\u00020\rH\u0002J\f\u0010\u0010\u001a\u00020\u000b*\u00020\rH\u0002J\f\u0010\u0011\u001a\u00020\u000b*\u00020\rH\u0002J\f\u0010\u0012\u001a\u00020\u000b*\u00020\rH\u0002¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/boxing/StackPeepholeOptimizationsTransformer;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;", "<init>", "()V", "transform", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "transformOnce", Argument.Delimiters.none, "isEliminatedByPop", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "isPurePushOfSize1", "isEliminatedByPop2", "isPurePushOfSize2", "isLdcOfSize2", "isKotlinJvmInternalIntrinsicsCompareInt", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class StackPeepholeOptimizationsTransformer extends MethodTransformer {
    private final boolean isEliminatedByPop(AbstractInsnNode abstractInsnNode) {
        return isPurePushOfSize1(abstractInsnNode) || abstractInsnNode.getOpcode() == 89;
    }

    private final boolean isEliminatedByPop2(AbstractInsnNode abstractInsnNode) {
        return isPurePushOfSize2(abstractInsnNode) || abstractInsnNode.getOpcode() == 92;
    }

    private final boolean isKotlinJvmInternalIntrinsicsCompareInt(AbstractInsnNode abstractInsnNode) {
        if (abstractInsnNode.getOpcode() != 184 || !(abstractInsnNode instanceof MethodInsnNode)) {
            return false;
        }
        MethodInsnNode methodInsnNode = (MethodInsnNode) abstractInsnNode;
        return Intrinsics.areEqual(methodInsnNode.name, "compare") && Intrinsics.areEqual(methodInsnNode.owner, "kotlin/jvm/internal/Intrinsics") && Intrinsics.areEqual(methodInsnNode.desc, "(II)I");
    }

    private final boolean isLdcOfSize2(AbstractInsnNode abstractInsnNode) {
        if (abstractInsnNode.getOpcode() != 18 || !(abstractInsnNode instanceof LdcInsnNode)) {
            return false;
        }
        Object obj = ((LdcInsnNode) abstractInsnNode).cst;
        return (obj instanceof Double) || (obj instanceof Long);
    }

    private final boolean isPurePushOfSize1(AbstractInsnNode abstractInsnNode) {
        int opcode;
        if (isLdcOfSize2(abstractInsnNode)) {
            return false;
        }
        int opcode2 = abstractInsnNode.getOpcode();
        return (1 <= opcode2 && opcode2 < 14) || (16 <= (opcode = abstractInsnNode.getOpcode()) && opcode < 22) || abstractInsnNode.getOpcode() == 23 || abstractInsnNode.getOpcode() == 25 || PopBackwardPropagationTransformerKt.isUnitInstance(abstractInsnNode);
    }

    private final boolean isPurePushOfSize2(AbstractInsnNode abstractInsnNode) {
        return isLdcOfSize2(abstractInsnNode) || abstractInsnNode.getOpcode() == 9 || abstractInsnNode.getOpcode() == 10 || abstractInsnNode.getOpcode() == 14 || abstractInsnNode.getOpcode() == 15 || abstractInsnNode.getOpcode() == 22 || abstractInsnNode.getOpcode() == 24;
    }

    private final boolean transformOnce(MethodNode methodNode) {
        AbstractInsnNode abstractInsnNodeTransformOnce$previousMeaningful;
        InsnList insnList = methodNode.instructions;
        boolean[] zArrFindMergeNodes = FastAnalyzer.INSTANCE.findMergeNodes(methodNode);
        JumpInsnNode first = insnList.getFirst();
        boolean z = false;
        while (first != null) {
            first = first.getNext();
            AbstractInsnNode abstractInsnNodeTransformOnce$previousMeaningful2 = transformOnce$previousMeaningful(first, zArrFindMergeNodes, insnList);
            if (abstractInsnNodeTransformOnce$previousMeaningful2 != null) {
                int opcode = first.getOpcode();
                if (opcode == 87) {
                    if (isEliminatedByPop(abstractInsnNodeTransformOnce$previousMeaningful2)) {
                        insnList.set(first, new InsnNode(0));
                        insnList.set(abstractInsnNodeTransformOnce$previousMeaningful2, new InsnNode(0));
                    } else if (abstractInsnNodeTransformOnce$previousMeaningful2.getOpcode() == 90) {
                        insnList.set(first, new InsnNode(0));
                        insnList.set(abstractInsnNodeTransformOnce$previousMeaningful2, new InsnNode(95));
                    }
                    z = true;
                } else if (opcode == 95) {
                    AbstractInsnNode abstractInsnNodeTransformOnce$previousMeaningful3 = transformOnce$previousMeaningful(abstractInsnNodeTransformOnce$previousMeaningful2, zArrFindMergeNodes, insnList);
                    if (abstractInsnNodeTransformOnce$previousMeaningful3 != null && isPurePushOfSize1(abstractInsnNodeTransformOnce$previousMeaningful2) && isPurePushOfSize1(abstractInsnNodeTransformOnce$previousMeaningful3)) {
                        insnList.set(first, new InsnNode(0));
                        insnList.set(abstractInsnNodeTransformOnce$previousMeaningful2, abstractInsnNodeTransformOnce$previousMeaningful3.clone(MapsKt.emptyMap()));
                        insnList.set(abstractInsnNodeTransformOnce$previousMeaningful3, abstractInsnNodeTransformOnce$previousMeaningful2.clone(MapsKt.emptyMap()));
                        z = true;
                    }
                } else if (opcode == 133) {
                    int opcode2 = abstractInsnNodeTransformOnce$previousMeaningful2.getOpcode();
                    if (opcode2 == 3) {
                        insnList.set(first, new InsnNode(0));
                        insnList.set(abstractInsnNodeTransformOnce$previousMeaningful2, new InsnNode(9));
                        z = true;
                    } else if (opcode2 == 4) {
                        insnList.set(first, new InsnNode(0));
                        insnList.set(abstractInsnNodeTransformOnce$previousMeaningful2, new InsnNode(10));
                        z = true;
                    }
                } else if (opcode == 88) {
                    if (isEliminatedByPop2(abstractInsnNodeTransformOnce$previousMeaningful2)) {
                        insnList.set(first, new InsnNode(0));
                        insnList.set(abstractInsnNodeTransformOnce$previousMeaningful2, new InsnNode(0));
                    } else {
                        AbstractInsnNode abstractInsnNodeTransformOnce$previousMeaningful4 = transformOnce$previousMeaningful(abstractInsnNodeTransformOnce$previousMeaningful2, zArrFindMergeNodes, insnList);
                        if (abstractInsnNodeTransformOnce$previousMeaningful4 != null && isEliminatedByPop(abstractInsnNodeTransformOnce$previousMeaningful2) && isEliminatedByPop(abstractInsnNodeTransformOnce$previousMeaningful4)) {
                            insnList.set(first, new InsnNode(0));
                            insnList.set(abstractInsnNodeTransformOnce$previousMeaningful2, new InsnNode(0));
                            insnList.set(abstractInsnNodeTransformOnce$previousMeaningful4, new InsnNode(0));
                        }
                    }
                    z = true;
                } else if (153 > opcode || opcode >= 159) {
                    if (159 <= opcode && opcode < 165 && (abstractInsnNodeTransformOnce$previousMeaningful = transformOnce$previousMeaningful(abstractInsnNodeTransformOnce$previousMeaningful2, zArrFindMergeNodes, insnList)) != null && abstractInsnNodeTransformOnce$previousMeaningful2.getOpcode() == 3 && isKotlinJvmInternalIntrinsicsCompareInt(abstractInsnNodeTransformOnce$previousMeaningful)) {
                        insnList.set(abstractInsnNodeTransformOnce$previousMeaningful2, new InsnNode(0));
                        insnList.set(abstractInsnNodeTransformOnce$previousMeaningful, new InsnNode(0));
                        z = true;
                    }
                } else if (isKotlinJvmInternalIntrinsicsCompareInt(abstractInsnNodeTransformOnce$previousMeaningful2)) {
                    JumpInsnNode jumpInsnNode = first;
                    LabelNode labelNode = jumpInsnNode.label;
                    int opcode3 = jumpInsnNode.getOpcode() + 6;
                    insnList.set(first, new InsnNode(0));
                    insnList.set(abstractInsnNodeTransformOnce$previousMeaningful2, new JumpInsnNode(opcode3, labelNode));
                    z = true;
                }
            }
        }
        return z;
    }

    private static final AbstractInsnNode transformOnce$previousMeaningful(AbstractInsnNode abstractInsnNode, boolean[] zArr, InsnList insnList) {
        AbstractInsnNode previous = abstractInsnNode.getPrevious();
        while (previous != null) {
            if (previous.getOpcode() != 0 && UtilKt.getNodeType(previous) != 15 && (UtilKt.getNodeType(previous) != 8 || zArr[insnList.indexOf(previous)])) {
                return previous;
            }
            previous = previous.getPrevious();
        }
        return previous;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
    public void transform(String internalClassName, MethodNode methodNode) {
        internalClassName.getClass();
        methodNode.getClass();
        while (transformOnce(methodNode)) {
        }
    }
}
