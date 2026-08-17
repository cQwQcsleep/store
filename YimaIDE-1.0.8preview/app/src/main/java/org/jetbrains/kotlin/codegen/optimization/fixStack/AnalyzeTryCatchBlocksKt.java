package org.jetbrains.kotlin.codegen.optimization.fixStack;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.pseudoInsns.PseudoInsn;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.utils.SmartSet;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000B\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010%\n\u0002\b\u0002\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\u0014\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0002H\u0002\u001a\u001c\u0010\u0007\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b2\u0006\u0010\n\u001a\u00020\u0005H\u0000\u001a$\u0010\u000b\u001a\u00020\f2\u0006\u0010\n\u001a\u00020\u00052\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u000eH\u0002\u001aD\u0010\u0010\u001a\u000e\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\t0\b2\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00120\b2\u0006\u0010\n\u001a\u00020\u00052\u0012\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u000f0\u0013H\u0002\u001a\u001c\u0010\u0014\u001a\u000e\u0012\u0004\u0012\u00020\u000f\u0012\u0004\u0012\u00020\u00120\b2\u0006\u0010\n\u001a\u00020\u0005H\u0002¨\u0006\u0015"}, d2 = {"isDefaultHandlerNode", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/TryCatchBlockNode;", "debugString", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "tcb", "insertTryCatchBlocksMarkers", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "methodNode", "transformTryCatchBlocks", Argument.Delimiters.none, "newTryStartLabels", "Ljava/util/HashMap;", "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "insertSaveRestoreStackMarkers", "decompiledTryDescriptorForStart", "Lorg/jetbrains/kotlin/codegen/optimization/fixStack/DecompiledTryDescriptor;", Argument.Delimiters.none, "collectDecompiledTryDescriptors", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class AnalyzeTryCatchBlocksKt {
    private static final Map<LabelNode, DecompiledTryDescriptor> collectDecompiledTryDescriptors(MethodNode methodNode) {
        HashMap map = new HashMap();
        HashMap map2 = new HashMap();
        List<TryCatchBlockNode> list = methodNode.tryCatchBlocks;
        list.getClass();
        SmartSet smartSetCreate = SmartSet.Companion.create();
        for (TryCatchBlockNode tryCatchBlockNode : list) {
            tryCatchBlockNode.getClass();
            LabelNode labelNode = isDefaultHandlerNode(tryCatchBlockNode) ? tryCatchBlockNode.handler : null;
            if (labelNode != null) {
                smartSetCreate.add(labelNode);
            }
        }
        for (TryCatchBlockNode tryCatchBlockNode2 : methodNode.tryCatchBlocks) {
            tryCatchBlockNode2.getClass();
            if (isDefaultHandlerNode(tryCatchBlockNode2)) {
                map2.containsKey(tryCatchBlockNode2.start);
            }
            LabelNode labelNode2 = tryCatchBlockNode2.handler;
            Object obj = map2.get(labelNode2);
            if (obj == null) {
                LabelNode labelNode3 = tryCatchBlockNode2.start;
                Object decompiledTryDescriptor = map.get(labelNode3);
                if (decompiledTryDescriptor == null) {
                    LabelNode labelNode4 = tryCatchBlockNode2.start;
                    labelNode4.getClass();
                    decompiledTryDescriptor = new DecompiledTryDescriptor(labelNode4);
                    map.put(labelNode3, decompiledTryDescriptor);
                }
                obj = (DecompiledTryDescriptor) decompiledTryDescriptor;
                map2.put(labelNode2, obj);
            }
            DecompiledTryDescriptor decompiledTryDescriptor2 = (DecompiledTryDescriptor) obj;
            if (isDefaultHandlerNode(tryCatchBlockNode2)) {
                decompiledTryDescriptor2.getDefaultHandlerTcb();
                decompiledTryDescriptor2.setDefaultHandlerTcb(tryCatchBlockNode2);
            }
            if (!smartSetCreate.contains(tryCatchBlockNode2.handler)) {
                decompiledTryDescriptor2.getHandlerStartLabels().add(tryCatchBlockNode2.handler);
            }
        }
        return map;
    }

    private static final String debugString(MethodNode methodNode, TryCatchBlockNode tryCatchBlockNode) {
        return "TCB<" + methodNode.instructions.indexOf(tryCatchBlockNode.start) + ", " + methodNode.instructions.indexOf(tryCatchBlockNode.end) + ", " + methodNode.instructions.indexOf(tryCatchBlockNode.handler) + '>';
    }

    private static final Map<AbstractInsnNode, AbstractInsnNode> insertSaveRestoreStackMarkers(Map<LabelNode, DecompiledTryDescriptor> map, MethodNode methodNode, Map<LabelNode, LabelNode> map2) {
        MethodInsnNode methodInsnNodeCreateInsnNode;
        HashMap map3 = new HashMap();
        HashMap map4 = new HashMap();
        HashSet hashSet = new HashSet();
        for (DecompiledTryDescriptor decompiledTryDescriptor : map.values()) {
            if (map4.containsKey(decompiledTryDescriptor.getTryStartLabel())) {
                Object obj = map4.get(decompiledTryDescriptor.getTryStartLabel());
                obj.getClass();
                methodInsnNodeCreateInsnNode = (AbstractInsnNode) obj;
            } else {
                AbstractInsnNode next = decompiledTryDescriptor.getTryStartLabel().getNext();
                while (next != null && !UtilKt.hasOpcode(next)) {
                    next = next.getNext();
                }
                next.getClass();
                next.getOpcode();
                LabelNode labelNode = new LabelNode(new Label());
                map2.put(decompiledTryDescriptor.getTryStartLabel(), labelNode);
                methodInsnNodeCreateInsnNode = PseudoInsn.SAVE_STACK_BEFORE_TRY.createInsnNode();
                MethodInsnNode methodInsnNodeCreateInsnNode2 = PseudoInsn.RESTORE_STACK_IN_TRY_CATCH.createInsnNode();
                map4.put(decompiledTryDescriptor.getTryStartLabel(), methodInsnNodeCreateInsnNode);
                map3.put(methodInsnNodeCreateInsnNode2, methodInsnNodeCreateInsnNode);
                methodNode.instructions.insertBefore(next, methodInsnNodeCreateInsnNode);
                methodNode.instructions.insertBefore(next, labelNode);
                methodNode.instructions.insert(next, methodInsnNodeCreateInsnNode2);
            }
            Iterator<LabelNode> it = decompiledTryDescriptor.getHandlerStartLabels().iterator();
            it.getClass();
            while (it.hasNext()) {
                LabelNode next2 = it.next();
                next2.getClass();
                LabelNode labelNode2 = next2;
                if (!hashSet.contains(labelNode2)) {
                    hashSet.add(labelNode2);
                    AbstractInsnNode next3 = labelNode2.getNext();
                    while (next3 != null && !UtilKt.hasOpcode(next3)) {
                        next3 = next3.getNext();
                    }
                    next3.getClass();
                    InlineCodegenUtilsKt.isCatchStoreInstruction(next3);
                    MethodInsnNode methodInsnNodeCreateInsnNode3 = PseudoInsn.RESTORE_STACK_IN_TRY_CATCH.createInsnNode();
                    map3.put(methodInsnNodeCreateInsnNode3, methodInsnNodeCreateInsnNode);
                    methodNode.instructions.insert(next3, methodInsnNodeCreateInsnNode3);
                }
            }
        }
        return map3;
    }

    public static final Map<AbstractInsnNode, AbstractInsnNode> insertTryCatchBlocksMarkers(MethodNode methodNode) {
        methodNode.getClass();
        if (methodNode.tryCatchBlocks.isEmpty()) {
            return MapsKt.emptyMap();
        }
        Map<LabelNode, DecompiledTryDescriptor> mapCollectDecompiledTryDescriptors = collectDecompiledTryDescriptors(methodNode);
        HashMap map = new HashMap();
        Map<AbstractInsnNode, AbstractInsnNode> mapInsertSaveRestoreStackMarkers = insertSaveRestoreStackMarkers(mapCollectDecompiledTryDescriptors, methodNode, map);
        transformTryCatchBlocks(methodNode, map);
        return mapInsertSaveRestoreStackMarkers;
    }

    private static final boolean isDefaultHandlerNode(TryCatchBlockNode tryCatchBlockNode) {
        return Intrinsics.areEqual(tryCatchBlockNode.start, tryCatchBlockNode.handler);
    }

    private static final void transformTryCatchBlocks(MethodNode methodNode, HashMap<LabelNode, LabelNode> map) {
        List list = methodNode.tryCatchBlocks;
        list.getClass();
        List<TryCatchBlockNode> list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        for (TryCatchBlockNode tryCatchBlockNode : list2) {
            LabelNode labelNode = map.get(tryCatchBlockNode.start);
            if (labelNode != null) {
                tryCatchBlockNode = new TryCatchBlockNode(labelNode, tryCatchBlockNode.end, tryCatchBlockNode.handler, tryCatchBlockNode.type);
            }
            arrayList.add(tryCatchBlockNode);
        }
        methodNode.tryCatchBlocks = arrayList;
    }
}
