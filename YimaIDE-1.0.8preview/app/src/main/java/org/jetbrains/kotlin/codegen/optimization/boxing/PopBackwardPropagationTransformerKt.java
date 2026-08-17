package org.jetbrains.kotlin.codegen.optimization.boxing;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0018\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\u001a\n\u0010\u0004\u001a\u00020\u0005*\u00020\u0002\u001a\n\u0010\u0006\u001a\u00020\u0005*\u00020\u0002\u001a\n\u0010\u0007\u001a\u00020\u0005*\u00020\u0002\u001a\n\u0010\b\u001a\u00020\u0005*\u00020\u0002*$\b\u0002\u0010\u0000\"\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u00012\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001¨\u0006\t"}, d2 = {"Transformation", "Lkotlin/Function1;", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", Argument.Delimiters.none, "isPurePush", Argument.Delimiters.none, "isPop", "isUnitInstance", "isPrimitiveTypeConversion", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class PopBackwardPropagationTransformerKt {
    public static final boolean isPop(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return abstractInsnNode.getOpcode() == 87 || abstractInsnNode.getOpcode() == 88;
    }

    public static final boolean isPrimitiveTypeConversion(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        int opcode = abstractInsnNode.getOpcode();
        return 133 <= opcode && opcode < 148;
    }

    public static final boolean isPurePush(AbstractInsnNode abstractInsnNode) {
        int opcode;
        abstractInsnNode.getClass();
        return UtilKt.isLoadOperation(abstractInsnNode) || (1 <= (opcode = abstractInsnNode.getOpcode()) && opcode < 21) || isUnitInstance(abstractInsnNode);
    }

    public static final boolean isUnitInstance(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        if (abstractInsnNode.getOpcode() != 178 || !(abstractInsnNode instanceof FieldInsnNode)) {
            return false;
        }
        FieldInsnNode fieldInsnNode = (FieldInsnNode) abstractInsnNode;
        return Intrinsics.areEqual(fieldInsnNode.owner, "kotlin/Unit") && Intrinsics.areEqual(fieldInsnNode.name, "INSTANCE");
    }
}
