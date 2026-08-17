package org.jetbrains.kotlin.codegen.optimization.temporaryVals;

import java.util.List;
import kotlin.Metadata;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\b\t\u0018\u00002\u00020\u0001B%\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\u0004\b\b\u0010\tR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0017\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/temporaryVals/TemporaryVal;", Argument.Delimiters.none, "index", Argument.Delimiters.none, "storeInsn", "Lorg/jetbrains/org/objectweb/asm/tree/VarInsnNode;", "loadInsns", Argument.Delimiters.none, "<init>", "(ILorg/jetbrains/org/objectweb/asm/tree/VarInsnNode;Ljava/util/List;)V", "getIndex", "()I", "getStoreInsn", "()Lorg/jetbrains/org/objectweb/asm/tree/VarInsnNode;", "getLoadInsns", "()Ljava/util/List;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class TemporaryVal {
    private final int index;
    private final List<VarInsnNode> loadInsns;
    private final VarInsnNode storeInsn;

    public TemporaryVal(int i, VarInsnNode varInsnNode, List<? extends VarInsnNode> list) {
        varInsnNode.getClass();
        list.getClass();
        this.index = i;
        this.storeInsn = varInsnNode;
        this.loadInsns = list;
    }

    public final int getIndex() {
        return this.index;
    }

    public final List<VarInsnNode> getLoadInsns() {
        return this.loadInsns;
    }

    public final VarInsnNode getStoreInsn() {
        return this.storeInsn;
    }
}
