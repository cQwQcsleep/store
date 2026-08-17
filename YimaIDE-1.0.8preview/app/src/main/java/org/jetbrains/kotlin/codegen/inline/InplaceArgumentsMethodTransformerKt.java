package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\f\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002¨\u0006\u0003"}, d2 = {"fixupLVT", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InplaceArgumentsMethodTransformerKt {
    /* JADX INFO: Access modifiers changed from: private */
    public static final void fixupLVT(MethodNode methodNode) {
        for (LocalVariableNode localVariableNode : methodNode.localVariables) {
            if (methodNode.instructions.indexOf(localVariableNode.end) < methodNode.instructions.indexOf(localVariableNode.start)) {
                LabelNode labelNode = localVariableNode.start;
                labelNode.getClass();
                AbstractInsnNode next = labelNode.getNext();
                while (next != null && !(next instanceof LabelNode)) {
                    next = next.getNext();
                }
                LabelNode labelNode2 = next instanceof LabelNode ? (LabelNode) next : null;
                if (labelNode2 == null) {
                    labelNode2 = localVariableNode.start;
                }
                localVariableNode.end = labelNode2;
            }
        }
    }
}
