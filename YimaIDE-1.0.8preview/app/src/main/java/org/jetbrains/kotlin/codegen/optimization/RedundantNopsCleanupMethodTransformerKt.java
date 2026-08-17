package org.jetbrains.kotlin.codegen.optimization;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\n\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u00012\u0006\u0010\u0002\u001a\u00020\u00012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0001H\u0000¨\u0006\u0004"}, d2 = {"getRequiredNopInRange", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "firstInclusive", "lastExclusive", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RedundantNopsCleanupMethodTransformerKt {
    public static final AbstractInsnNode getRequiredNopInRange(AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
        abstractInsnNode.getClass();
        AbstractInsnNode abstractInsnNode3 = null;
        while (abstractInsnNode != null && !Intrinsics.areEqual(abstractInsnNode, abstractInsnNode2)) {
            if (UtilKt.isMeaningful(abstractInsnNode) && abstractInsnNode.getOpcode() != 0) {
                return null;
            }
            if (abstractInsnNode.getOpcode() == 0 && abstractInsnNode3 == null) {
                abstractInsnNode3 = abstractInsnNode;
            }
            abstractInsnNode = abstractInsnNode.getNext();
        }
        return abstractInsnNode3;
    }
}
