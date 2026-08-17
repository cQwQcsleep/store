package org.jetbrains.kotlin.codegen.optimization;

import kotlin.Metadata;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u001a\u001c\u0010\u0000\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0001\u001a\u0014\u0010\u0005\u001a\u0004\u0018\u00010\u0001*\u00020\u00022\u0006\u0010\u0003\u001a\u00020\u0001¨\u0006\u0006"}, d2 = {"replaceNodeGetNext", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "oldNode", "newNode", "removeNodeGetNext", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class LabelNormalizationMethodTransformerKt {
    public static final AbstractInsnNode removeNodeGetNext(InsnList insnList, AbstractInsnNode abstractInsnNode) {
        insnList.getClass();
        abstractInsnNode.getClass();
        AbstractInsnNode next = abstractInsnNode.getNext();
        insnList.remove(abstractInsnNode);
        return next;
    }

    public static final AbstractInsnNode replaceNodeGetNext(InsnList insnList, AbstractInsnNode abstractInsnNode, AbstractInsnNode abstractInsnNode2) {
        insnList.getClass();
        abstractInsnNode.getClass();
        abstractInsnNode2.getClass();
        insnList.insertBefore(abstractInsnNode, abstractInsnNode2);
        insnList.remove(abstractInsnNode);
        return abstractInsnNode2.getNext();
    }
}
