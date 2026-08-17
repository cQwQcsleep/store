package org.jetbrains.kotlin.codegen.optimization;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.ArraysKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnNode;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LineNumberNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010!\n\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J$\u0010\n\u001a\u00020\u00052\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\f0\u000eH\u0002J4\u0010\u0010\u001a\u0004\u0018\u00010\f2\u0006\u0010\u000b\u001a\u00020\f2\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\f0\u000e2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u0012H\u0002¨\u0006\u0013"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/RedundantGotoMethodTransformer;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;", "<init>", "()V", "transform", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "rewriteLabelIfNeeded", "jumpInsn", "Lorg/jetbrains/org/objectweb/asm/tree/JumpInsnNode;", "labelsToReplace", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "getLastTargetJumpInsn", "alreadyVisited", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RedundantGotoMethodTransformer extends MethodTransformer {
    private final JumpInsnNode getLastTargetJumpInsn(JumpInsnNode jumpInsn, Map<LabelNode, ? extends JumpInsnNode> labelsToReplace, List<JumpInsnNode> alreadyVisited) {
        JumpInsnNode jumpInsnNode = labelsToReplace.get(jumpInsn.label);
        if (jumpInsnNode == null) {
            return jumpInsn;
        }
        if (alreadyVisited.contains(jumpInsnNode)) {
            return null;
        }
        alreadyVisited.add(jumpInsnNode);
        return getLastTargetJumpInsn(jumpInsnNode, labelsToReplace, alreadyVisited);
    }

    private final void rewriteLabelIfNeeded(JumpInsnNode jumpInsn, Map<LabelNode, ? extends JumpInsnNode> labelsToReplace) {
        JumpInsnNode lastTargetJumpInsn = getLastTargetJumpInsn(jumpInsn, labelsToReplace, new ArrayList());
        if (lastTargetJumpInsn == null || Intrinsics.areEqual(lastTargetJumpInsn, jumpInsn)) {
            return;
        }
        jumpInsn.label = lastTargetJumpInsn.label;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
    public void transform(String internalClassName, MethodNode methodNode) {
        internalClassName.getClass();
        methodNode.getClass();
        AbstractInsnNode[] array = methodNode.instructions.toArray();
        array.getClass();
        ArraysKt.reverse(array);
        ArrayList arrayList = new ArrayList();
        HashSet hashSet = new HashSet();
        HashMap map = new HashMap();
        array.getClass();
        JumpInsnNode jumpInsnNode = null;
        for (AbstractInsnNode abstractInsnNode : array) {
            if (abstractInsnNode instanceof LabelNode) {
                hashSet.add(abstractInsnNode);
                if (jumpInsnNode != null) {
                    map.put(abstractInsnNode, jumpInsnNode);
                }
            } else if (abstractInsnNode.getOpcode() == 167) {
                jumpInsnNode = (JumpInsnNode) abstractInsnNode;
                if (hashSet.contains(jumpInsnNode.label)) {
                    arrayList.add(abstractInsnNode);
                } else {
                    hashSet.clear();
                }
            } else if ((abstractInsnNode instanceof LineNumberNode) || (UtilKt.isMeaningful(abstractInsnNode) && abstractInsnNode.getOpcode() != 0)) {
                hashSet.clear();
                jumpInsnNode = null;
            }
        }
        if (!map.isEmpty()) {
            ArrayList arrayList2 = new ArrayList();
            for (AbstractInsnNode abstractInsnNode2 : array) {
                if (abstractInsnNode2 instanceof JumpInsnNode) {
                    arrayList2.add(abstractInsnNode2);
                }
            }
            Iterator it = arrayList2.iterator();
            while (it.hasNext()) {
                rewriteLabelIfNeeded((JumpInsnNode) it.next(), map);
            }
        }
        Iterator it2 = arrayList.iterator();
        it2.getClass();
        while (it2.hasNext()) {
            Object next = it2.next();
            next.getClass();
            AbstractInsnNode abstractInsnNode3 = (AbstractInsnNode) next;
            methodNode.instructions.insertBefore(abstractInsnNode3, new InsnNode(0));
            methodNode.instructions.remove(abstractInsnNode3);
        }
    }
}
