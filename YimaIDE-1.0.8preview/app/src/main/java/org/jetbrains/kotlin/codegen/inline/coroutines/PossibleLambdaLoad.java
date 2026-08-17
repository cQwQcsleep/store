package org.jetbrains.kotlin.codegen.inline.coroutines;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0002\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/coroutines/PossibleLambdaLoad;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)V", "getInsn", "()Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class PossibleLambdaLoad extends BasicValue {
    private final AbstractInsnNode insn;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public PossibleLambdaLoad(AbstractInsnNode abstractInsnNode) {
        super(AsmTypes.OBJECT_TYPE);
        abstractInsnNode.getClass();
        this.insn = abstractInsnNode;
    }

    public final AbstractInsnNode getInsn() {
        return this.insn;
    }
}
