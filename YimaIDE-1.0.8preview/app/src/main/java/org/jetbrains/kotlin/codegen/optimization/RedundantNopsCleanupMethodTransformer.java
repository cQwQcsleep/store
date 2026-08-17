package org.jetbrains.kotlin.codegen.optimization;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LineNumberNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u001e\u0010\n\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002J\u001e\u0010\u000e\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\t2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fH\u0002¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/RedundantNopsCleanupMethodTransformer;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;", "<init>", "()V", "transform", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "recordNopsRequiredForDebugger", "requiredNops", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "recordNopsRequiredForTryCatchBlocks", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RedundantNopsCleanupMethodTransformer extends MethodTransformer {
    private final void recordNopsRequiredForDebugger(MethodNode methodNode, Set<AbstractInsnNode> requiredNops) {
        HashSet hashSet = new HashSet();
        for (LocalVariableNode localVariableNode : methodNode.localVariables) {
            hashSet.add(localVariableNode.start);
            hashSet.add(localVariableNode.end);
        }
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : insnList) {
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode) obj;
            if (CollectionsKt.contains(hashSet, abstractInsnNode) || (abstractInsnNode instanceof LineNumberNode)) {
                arrayList.add(obj);
            }
        }
        int size = arrayList.size() - 2;
        if (size < 0) {
            return;
        }
        int i = 0;
        while (true) {
            AbstractInsnNode abstractInsnNode2 = (AbstractInsnNode) arrayList.get(i);
            int i2 = i + 1;
            AbstractInsnNode abstractInsnNode3 = (AbstractInsnNode) arrayList.get(i2);
            if (abstractInsnNode2 instanceof LineNumberNode) {
                org.jetbrains.kotlin.utils.CollectionsKt.addIfNotNull(requiredNops, RedundantNopsCleanupMethodTransformerKt.getRequiredNopInRange(abstractInsnNode2, abstractInsnNode3));
            }
            if (i == size) {
                return;
            } else {
                i = i2;
            }
        }
    }

    private final void recordNopsRequiredForTryCatchBlocks(MethodNode methodNode, Set<AbstractInsnNode> requiredNops) {
        Iterator it = methodNode.tryCatchBlocks.iterator();
        while (it.hasNext()) {
            LabelNode labelNode = ((TryCatchBlockNode) it.next()).start;
            labelNode.getClass();
            AbstractInsnNode next = labelNode.getNext();
            while (next != null && !UtilKt.isMeaningful(next)) {
                next = next.getNext();
            }
            if (next != null && next.getOpcode() == 0) {
                requiredNops.add(next);
            }
        }
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
    public void transform(String internalClassName, MethodNode methodNode) {
        internalClassName.getClass();
        methodNode.getClass();
        new LabelNormalizationMethodTransformer().transform(internalClassName, methodNode);
        HashSet hashSet = new HashSet();
        recordNopsRequiredForTryCatchBlocks(methodNode, hashSet);
        recordNopsRequiredForDebugger(methodNode, hashSet);
        AbstractInsnNode first = methodNode.instructions.getFirst();
        while (first != null) {
            if (first.getOpcode() != 0 || hashSet.contains(first)) {
                first = first.getNext();
            } else {
                AbstractInsnNode next = first.getNext();
                methodNode.instructions.remove(first);
                first = next;
            }
        }
    }
}
