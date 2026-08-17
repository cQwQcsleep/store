package org.jetbrains.kotlin.codegen.optimization.common;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.IincInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u0004\u001a\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0002\u001a\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\tH\u0002¨\u0006\u000b"}, d2 = {"analyzeLiveness", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/optimization/common/VariableLivenessFrame;", "method", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "defVar", Argument.Delimiters.none, "frame", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "useVar", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class VariableLivenessKt {
    public static final List<VariableLivenessFrame> analyzeLiveness(MethodNode methodNode) {
        methodNode.getClass();
        return BackwardAnalysisKt.analyze(methodNode, new BackwardAnalysisInterpreter<VariableLivenessFrame>() { // from class: org.jetbrains.kotlin.codegen.optimization.common.VariableLivenessKt.analyzeLiveness.1
            @Override // org.jetbrains.kotlin.codegen.optimization.common.BackwardAnalysisInterpreter
            public void def(VariableLivenessFrame frame, AbstractInsnNode insn) {
                frame.getClass();
                insn.getClass();
                VariableLivenessKt.defVar(frame, insn);
            }

            @Override // org.jetbrains.kotlin.codegen.optimization.common.BackwardAnalysisInterpreter
            public VariableLivenessFrame newFrame(int maxLocals) {
                return new VariableLivenessFrame(maxLocals);
            }

            @Override // org.jetbrains.kotlin.codegen.optimization.common.BackwardAnalysisInterpreter
            public void use(VariableLivenessFrame frame, AbstractInsnNode insn) {
                frame.getClass();
                insn.getClass();
                VariableLivenessKt.useVar(frame, insn);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void defVar(VariableLivenessFrame variableLivenessFrame, AbstractInsnNode abstractInsnNode) {
        if ((abstractInsnNode instanceof VarInsnNode) && UtilKt.isStoreOperation(abstractInsnNode)) {
            variableLivenessFrame.markDead(((VarInsnNode) abstractInsnNode).var);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void useVar(VariableLivenessFrame variableLivenessFrame, AbstractInsnNode abstractInsnNode) {
        if ((abstractInsnNode instanceof VarInsnNode) && UtilKt.isLoadOperation(abstractInsnNode)) {
            variableLivenessFrame.markAlive(((VarInsnNode) abstractInsnNode).var);
        } else if (abstractInsnNode instanceof IincInsnNode) {
            variableLivenessFrame.markAlive(((IincInsnNode) abstractInsnNode).var);
        }
    }
}
