package org.jetbrains.kotlin.codegen.inline;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0010\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/DefaultProcessor;", "Lorg/jetbrains/kotlin/codegen/inline/CoveringTryCatchNodeProcessor;", "node", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "parameterSize", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;I)V", "getNode", "()Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "instructionIndex", "inst", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DefaultProcessor extends CoveringTryCatchNodeProcessor {
    private final MethodNode node;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public DefaultProcessor(MethodNode methodNode, int i) {
        super(i);
        methodNode.getClass();
        this.node = methodNode;
        List<TryCatchBlockNode> list = methodNode.tryCatchBlocks;
        list.getClass();
        for (TryCatchBlockNode tryCatchBlockNode : list) {
            IntervalMetaInfo<TryCatchBlockNodeInfo> tryBlocksMetaInfo = getTryBlocksMetaInfo();
            tryCatchBlockNode.getClass();
            tryBlocksMetaInfo.addNewInterval(new TryCatchBlockNodeInfo(tryCatchBlockNode, false));
        }
        List<LocalVariableNode> list2 = this.node.localVariables;
        list2.getClass();
        for (LocalVariableNode localVariableNode : list2) {
            IntervalMetaInfo<LocalVarNodeWrapper> localVarsMetaInfo = getLocalVarsMetaInfo();
            localVariableNode.getClass();
            localVarsMetaInfo.addNewInterval(new LocalVarNodeWrapper(localVariableNode));
        }
    }

    public final MethodNode getNode() {
        return this.node;
    }

    @Override // org.jetbrains.kotlin.codegen.inline.CoveringTryCatchNodeProcessor
    public int instructionIndex(AbstractInsnNode inst) {
        inst.getClass();
        return this.node.instructions.indexOf(inst);
    }
}
