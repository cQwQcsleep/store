package org.jetbrains.kotlin.codegen.inline;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;

/* JADX INFO: Access modifiers changed from: package-private */
/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u000e\n\u0002\u0010\u000e\n\u0000\b\u0082\b\u0018\u00002\u00020\u0001B1\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\b\u0012\b\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\u0004\b\n\u0010\u000bJ\t\u0010\u001a\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001b\u001a\u00020\u0003HÆ\u0003J\t\u0010\u001c\u001a\u00020\u0006HÆ\u0003J\t\u0010\u001d\u001a\u00020\bHÆ\u0003J\u000b\u0010\u001e\u001a\u0004\u0018\u00010\u0006HÆ\u0003J=\u0010\u001f\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00062\b\b\u0002\u0010\u0007\u001a\u00020\b2\n\b\u0002\u0010\t\u001a\u0004\u0018\u00010\u0006HÆ\u0001J\u0014\u0010 \u001a\u00020\u00152\b\u0010!\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004J\n\u0010\"\u001a\u00020\u0003HÖ\u0081\u0004J\n\u0010#\u001a\u00020$HÖ\u0081\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0004\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\rR\u0011\u0010\u0005\u001a\u00020\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010R\u0011\u0010\u0007\u001a\u00020\b¢\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0013\u0010\t\u001a\u0004\u0018\u00010\u0006¢\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0010R\u0011\u0010\u0014\u001a\u00020\u0015¢\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0011\u0010\u0018\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\r¨\u0006%"}, d2 = {"Lorg/jetbrains/kotlin/codegen/inline/Condition;", Argument.Delimiters.none, "mask", Argument.Delimiters.none, "constant", "maskInstruction", "Lorg/jetbrains/org/objectweb/asm/tree/VarInsnNode;", "jumpInstruction", "Lorg/jetbrains/org/objectweb/asm/tree/JumpInsnNode;", "varInsNode", "<init>", "(IILorg/jetbrains/org/objectweb/asm/tree/VarInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/JumpInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/VarInsnNode;)V", "getMask", "()I", "getConstant", "getMaskInstruction", "()Lorg/jetbrains/org/objectweb/asm/tree/VarInsnNode;", "getJumpInstruction", "()Lorg/jetbrains/org/objectweb/asm/tree/JumpInsnNode;", "getVarInsNode", "expandNotDelete", Argument.Delimiters.none, "getExpandNotDelete", "()Z", "varIndex", "getVarIndex", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final /* data */ class Condition {
    private final int constant;
    private final boolean expandNotDelete;
    private final JumpInsnNode jumpInstruction;
    private final int mask;
    private final VarInsnNode maskInstruction;
    private final int varIndex;
    private final VarInsnNode varInsNode;

    public Condition(int i, int i2, VarInsnNode varInsnNode, JumpInsnNode jumpInsnNode, VarInsnNode varInsnNode2) {
        varInsnNode.getClass();
        jumpInsnNode.getClass();
        this.mask = i;
        this.constant = i2;
        this.maskInstruction = varInsnNode;
        this.jumpInstruction = jumpInsnNode;
        this.varInsNode = varInsnNode2;
        this.expandNotDelete = (i & i2) != 0;
        this.varIndex = varInsnNode2 != null ? varInsnNode2.var : 0;
    }

    public static /* synthetic */ Condition copy$default(Condition condition, int i, int i2, VarInsnNode varInsnNode, JumpInsnNode jumpInsnNode, VarInsnNode varInsnNode2, int i3, Object obj) {
        if ((i3 & 1) != 0) {
            i = condition.mask;
        }
        if ((i3 & 2) != 0) {
            i2 = condition.constant;
        }
        if ((i3 & 4) != 0) {
            varInsnNode = condition.maskInstruction;
        }
        if ((i3 & 8) != 0) {
            jumpInsnNode = condition.jumpInstruction;
        }
        if ((i3 & 16) != 0) {
            varInsnNode2 = condition.varInsNode;
        }
        VarInsnNode varInsnNode3 = varInsnNode2;
        VarInsnNode varInsnNode4 = varInsnNode;
        return condition.copy(i, i2, varInsnNode4, jumpInsnNode, varInsnNode3);
    }

    /* JADX INFO: renamed from: component1, reason: from getter */
    public final int getMask() {
        return this.mask;
    }

    /* JADX INFO: renamed from: component2, reason: from getter */
    public final int getConstant() {
        return this.constant;
    }

    /* JADX INFO: renamed from: component3, reason: from getter */
    public final VarInsnNode getMaskInstruction() {
        return this.maskInstruction;
    }

    /* JADX INFO: renamed from: component4, reason: from getter */
    public final JumpInsnNode getJumpInstruction() {
        return this.jumpInstruction;
    }

    /* JADX INFO: renamed from: component5, reason: from getter */
    public final VarInsnNode getVarInsNode() {
        return this.varInsNode;
    }

    public final Condition copy(int mask, int constant, VarInsnNode maskInstruction, JumpInsnNode jumpInstruction, VarInsnNode varInsNode) {
        maskInstruction.getClass();
        jumpInstruction.getClass();
        return new Condition(mask, constant, maskInstruction, jumpInstruction, varInsNode);
    }

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof Condition)) {
            return false;
        }
        Condition condition = (Condition) other;
        return this.mask == condition.mask && this.constant == condition.constant && Intrinsics.areEqual(this.maskInstruction, condition.maskInstruction) && Intrinsics.areEqual(this.jumpInstruction, condition.jumpInstruction) && Intrinsics.areEqual(this.varInsNode, condition.varInsNode);
    }

    public final int getConstant() {
        return this.constant;
    }

    public final boolean getExpandNotDelete() {
        return this.expandNotDelete;
    }

    public final JumpInsnNode getJumpInstruction() {
        return this.jumpInstruction;
    }

    public final int getMask() {
        return this.mask;
    }

    public final VarInsnNode getMaskInstruction() {
        return this.maskInstruction;
    }

    public final int getVarIndex() {
        return this.varIndex;
    }

    public final VarInsnNode getVarInsNode() {
        return this.varInsNode;
    }

    public int hashCode() {
        int iHashCode = ((((((Integer.hashCode(this.mask) * 31) + Integer.hashCode(this.constant)) * 31) + this.maskInstruction.hashCode()) * 31) + this.jumpInstruction.hashCode()) * 31;
        VarInsnNode varInsnNode = this.varInsNode;
        return iHashCode + (varInsnNode == null ? 0 : varInsnNode.hashCode());
    }

    public String toString() {
        return "Condition(mask=" + this.mask + ", constant=" + this.constant + ", maskInstruction=" + this.maskInstruction + ", jumpInstruction=" + this.jumpInstruction + ", varInsNode=" + this.varInsNode + ')';
    }
}
