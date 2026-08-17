package org.jetbrains.kotlin.codegen.optimization;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u0010\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\rH\u0002R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/NegatedJumpsMethodTransformer;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;", "<init>", "()V", "transform", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "negatedConditionalJumpOpcode", Argument.Delimiters.none, "negateConditionalJumpOpcode", Argument.Delimiters.none, "opcode", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class NegatedJumpsMethodTransformer extends MethodTransformer {
    private final int[] negatedConditionalJumpOpcode;

    public NegatedJumpsMethodTransformer() {
        int[] iArr = new int[255];
        negatedConditionalJumpOpcode$lambda$0$negated(iArr, 198, 199);
        negatedConditionalJumpOpcode$lambda$0$negated(iArr, 153, 154);
        negatedConditionalJumpOpcode$lambda$0$negated(iArr, 155, 156);
        negatedConditionalJumpOpcode$lambda$0$negated(iArr, 158, 157);
        negatedConditionalJumpOpcode$lambda$0$negated(iArr, 159, 160);
        negatedConditionalJumpOpcode$lambda$0$negated(iArr, 161, 162);
        negatedConditionalJumpOpcode$lambda$0$negated(iArr, 164, 163);
        negatedConditionalJumpOpcode$lambda$0$negated(iArr, 165, 166);
        this.negatedConditionalJumpOpcode = iArr;
    }

    private final int negateConditionalJumpOpcode(int opcode) {
        return this.negatedConditionalJumpOpcode[opcode];
    }

    private static final void negatedConditionalJumpOpcode$lambda$0$negated(int[] iArr, int i, int i2) {
        iArr[i] = i2;
        iArr[i2] = i;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
    public void transform(String internalClassName, MethodNode methodNode) {
        JumpInsnNode next;
        AbstractInsnNode next2;
        internalClassName.getClass();
        methodNode.getClass();
        InsnList insnList = methodNode.instructions;
        JumpInsnNode[] array = insnList.toArray();
        array.getClass();
        for (JumpInsnNode jumpInsnNode : array) {
            jumpInsnNode.getClass();
            if (UtilKt.getNodeType(jumpInsnNode) == 7 && jumpInsnNode.getOpcode() != 167 && (next = jumpInsnNode.getNext()) != null && next.getOpcode() == 167 && (next2 = next.getNext()) != null) {
                JumpInsnNode jumpInsnNode2 = jumpInsnNode;
                if (Intrinsics.areEqual(next2, jumpInsnNode2.label)) {
                    insnList.insertBefore(jumpInsnNode, new JumpInsnNode(negateConditionalJumpOpcode(jumpInsnNode2.getOpcode()), next.label));
                    insnList.remove(jumpInsnNode);
                    insnList.remove(next);
                }
            }
        }
    }
}
