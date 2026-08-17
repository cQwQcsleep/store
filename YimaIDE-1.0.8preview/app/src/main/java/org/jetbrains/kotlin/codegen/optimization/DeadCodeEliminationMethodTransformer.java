package org.jetbrains.kotlin.codegen.optimization;

import java.util.ArrayList;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.MethodInlinerUtilKt;
import org.jetbrains.kotlin.codegen.optimization.common.InstructionLivenessAnalyzer;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LineNumberNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0018\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J \u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011H\u0002¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/DeadCodeEliminationMethodTransformer;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;", "<init>", "()V", "transform", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "shouldRemove", Argument.Delimiters.none, "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "index", Argument.Delimiters.none, "liveness", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DeadCodeEliminationMethodTransformer extends MethodTransformer {
    private final boolean shouldRemove(AbstractInsnNode insn, int index, boolean[] liveness) {
        if (!(insn instanceof LineNumberNode)) {
            return !liveness[index];
        }
        boolean z = false;
        AbstractInsnNode next = insn;
        while (true) {
            next = next.getNext();
            if (next == null) {
                return true;
            }
            index++;
            if (!(next instanceof LabelNode)) {
                if (next instanceof LineNumberNode) {
                    if (((LineNumberNode) next).line != ((LineNumberNode) insn).line) {
                        return z;
                    }
                } else {
                    if (liveness[index]) {
                        return false;
                    }
                    z = true;
                }
            }
        }
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
    public void transform(String internalClassName, MethodNode methodNode) {
        internalClassName.getClass();
        methodNode.getClass();
        boolean[] zArrAnalyze = new InstructionLivenessAnalyzer(methodNode, false, 2, null).analyze();
        ArrayList arrayList = new ArrayList();
        AbstractInsnNode[] array = methodNode.instructions.toArray();
        int length = array.length;
        for (int i = 0; i < length; i++) {
            AbstractInsnNode abstractInsnNode = array[i];
            abstractInsnNode.getClass();
            if (shouldRemove(abstractInsnNode, i, zArrAnalyze)) {
                arrayList.add(abstractInsnNode);
            }
        }
        MethodInlinerUtilKt.remove(methodNode, arrayList);
        UtilKt.removeEmptyCatchBlocks(methodNode);
        UtilKt.removeUnusedLocalVariables(methodNode);
    }
}
