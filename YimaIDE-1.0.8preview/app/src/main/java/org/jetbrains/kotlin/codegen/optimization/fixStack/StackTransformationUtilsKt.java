package org.jetbrains.kotlin.codegen.optimization.fixStack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.InsnNode;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;
import org.jetbrains.org.objectweb.asm.tree.analysis.Value;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000V\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010!\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\u001a!\u0010\u0000\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u0003¢\u0006\u0002\u0010\u0004\u001a)\u0010\u0005\u001a\u0004\u0018\u0001H\u0001\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0002\u0010\b\u001a<\u0010\t\u001a\u00020\u0007\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\f\u0010\n\u001a\b\u0012\u0004\u0012\u0002H\u00010\u000b2\u0006\u0010\f\u001a\u00020\u00072\b\b\u0002\u0010\r\u001a\u00020\u0007H\u0002\u001a*\u0010\u000e\u001a\n\u0012\u0004\u0012\u0002H\u0001\u0018\u00010\u000f\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\u0006\u0010\f\u001a\u00020\u0007\u001a2\u0010\u000e\u001a\n\u0012\u0004\u0012\u0002H\u0001\u0018\u00010\u000f\"\b\b\u0000\u0010\u0001*\u00020\u0002*\b\u0012\u0004\u0012\u0002H\u00010\u00032\u0006\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u0011\u001a\u00020\u0007\u001a\u001e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019\u001a\u001e\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019\u001a.\u0010\u001c\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0007\u001a\u001e\u0010 \u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019\u001a\u001e\u0010!\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u001b\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019\u001a\u000e\u0010\"\u001a\u00020#2\u0006\u0010\u0000\u001a\u00020$\u001a\u0016\u0010%\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010&\u001a\u00020\u0017\u001a\u0016\u0010'\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010&\u001a\u00020\u0017\u001a,\u0010(\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010&\u001a\u00020\u00172\u0006\u0010)\u001a\u00020\u00072\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u001e0\u000f¨\u0006+"}, d2 = {"top", "V", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "(Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;)Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;", "peek", "offset", Argument.Delimiters.none, "(Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;I)Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;", "peekWordsTo", "dest", Argument.Delimiters.none, "size", "offset0", "peekWords", Argument.Delimiters.none, "size1", "size2", "saveStack", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "nodeToReplace", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "savedStackDescriptor", "Lorg/jetbrains/kotlin/codegen/optimization/fixStack/SavedStackDescriptor;", "restoreStack", "location", "restoreStackWithReturnValue", "returnValue", "Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackValue;", "returnValueLocalVarIndex", "generateLoadInstructions", "generateStoreInstructions", "getPopInstruction", "Lorg/jetbrains/org/objectweb/asm/tree/InsnNode;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "removeAlwaysFalseIfeq", "node", "replaceAlwaysTrueIfeqWithGoto", "replaceMarkerWithPops", "expectedStackSize", "stackContent", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class StackTransformationUtilsKt {
    public static final void generateLoadInstructions(MethodNode methodNode, AbstractInsnNode abstractInsnNode, SavedStackDescriptor savedStackDescriptor) {
        methodNode.getClass();
        abstractInsnNode.getClass();
        savedStackDescriptor.getClass();
        int firstLocalVarIndex = savedStackDescriptor.getFirstLocalVarIndex();
        for (FixStackValue fixStackValue : savedStackDescriptor.getSavedValues()) {
            methodNode.instructions.insertBefore(abstractInsnNode, new VarInsnNode(fixStackValue.getLoadOpcode(), firstLocalVarIndex));
            firstLocalVarIndex += fixStackValue.get_size();
        }
    }

    public static final void generateStoreInstructions(MethodNode methodNode, AbstractInsnNode abstractInsnNode, SavedStackDescriptor savedStackDescriptor) {
        methodNode.getClass();
        abstractInsnNode.getClass();
        savedStackDescriptor.getClass();
        int firstUnusedLocalVarIndex = savedStackDescriptor.getFirstUnusedLocalVarIndex();
        for (FixStackValue fixStackValue : CollectionsKt.asReversed(savedStackDescriptor.getSavedValues())) {
            firstUnusedLocalVarIndex -= fixStackValue.get_size();
            methodNode.instructions.insertBefore(abstractInsnNode, new VarInsnNode(fixStackValue.getStoreOpcode(), firstUnusedLocalVarIndex));
        }
    }

    public static final InsnNode getPopInstruction(BasicValue basicValue) {
        int i;
        basicValue.getClass();
        int size = basicValue.getSize();
        if (size == 1) {
            i = 87;
        } else {
            if (size != 2) {
                x01.a("Unexpected value type size");
                return null;
            }
            i = 88;
        }
        return new InsnNode(i);
    }

    public static final <V extends Value> V peek(Frame<V> frame, int i) {
        frame.getClass();
        if (frame.getStackSize() > i) {
            return (V) frame.getStack((frame.getStackSize() - i) - 1);
        }
        return null;
    }

    public static final <V extends Value> List<V> peekWords(Frame<V> frame, int i, int i2) {
        frame.getClass();
        ArrayList arrayList = new ArrayList(i + i2);
        int iPeekWordsTo$default = peekWordsTo$default(frame, arrayList, i, 0, 4, null);
        if (iPeekWordsTo$default >= 0 && peekWordsTo(frame, arrayList, i2, iPeekWordsTo$default) >= 0) {
            return arrayList;
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    private static final <V extends Value> int peekWordsTo(Frame<V> frame, List<V> list, int i, int i2) {
        int size = 0;
        while (size < i) {
            int i3 = i2 + 1;
            Value valuePeek = peek(frame, i2);
            if (valuePeek == null) {
                return -1;
            }
            list.add(valuePeek);
            size += valuePeek.getSize();
            i2 = i3;
        }
        if (size > i) {
            return -1;
        }
        return i2;
    }

    public static /* synthetic */ int peekWordsTo$default(Frame frame, List list, int i, int i2, int i3, Object obj) {
        if ((i3 & 4) != 0) {
            i2 = 0;
        }
        return peekWordsTo(frame, list, i, i2);
    }

    public static final void removeAlwaysFalseIfeq(MethodNode methodNode, AbstractInsnNode abstractInsnNode) {
        methodNode.getClass();
        abstractInsnNode.getClass();
        InsnList insnList = methodNode.instructions;
        insnList.remove(abstractInsnNode.getNext());
        insnList.remove(abstractInsnNode);
    }

    public static final void replaceAlwaysTrueIfeqWithGoto(MethodNode methodNode, AbstractInsnNode abstractInsnNode) {
        methodNode.getClass();
        abstractInsnNode.getClass();
        InsnList insnList = methodNode.instructions;
        JumpInsnNode next = abstractInsnNode.getNext();
        next.getClass();
        JumpInsnNode jumpInsnNode = next;
        insnList.insertBefore(abstractInsnNode, new JumpInsnNode(167, jumpInsnNode.label));
        insnList.remove(abstractInsnNode);
        insnList.remove(jumpInsnNode);
    }

    public static final void replaceMarkerWithPops(MethodNode methodNode, AbstractInsnNode abstractInsnNode, int i, List<? extends FixStackValue> list) {
        methodNode.getClass();
        abstractInsnNode.getClass();
        list.getClass();
        InsnList insnList = methodNode.instructions;
        Iterator<? extends FixStackValue> it = list.subList(i, list.size()).iterator();
        while (it.hasNext()) {
            insnList.insert(abstractInsnNode, new InsnNode(it.next().getPopOpcode()));
        }
        insnList.remove(abstractInsnNode);
    }

    public static final void restoreStack(MethodNode methodNode, AbstractInsnNode abstractInsnNode, SavedStackDescriptor savedStackDescriptor) {
        methodNode.getClass();
        abstractInsnNode.getClass();
        savedStackDescriptor.getClass();
        InsnList insnList = methodNode.instructions;
        generateLoadInstructions(methodNode, abstractInsnNode, savedStackDescriptor);
        insnList.remove(abstractInsnNode);
    }

    public static final void restoreStackWithReturnValue(MethodNode methodNode, AbstractInsnNode abstractInsnNode, SavedStackDescriptor savedStackDescriptor, FixStackValue fixStackValue, int i) {
        methodNode.getClass();
        abstractInsnNode.getClass();
        savedStackDescriptor.getClass();
        fixStackValue.getClass();
        InsnList insnList = methodNode.instructions;
        insnList.insertBefore(abstractInsnNode, new VarInsnNode(fixStackValue.getStoreOpcode(), i));
        generateLoadInstructions(methodNode, abstractInsnNode, savedStackDescriptor);
        insnList.insertBefore(abstractInsnNode, new VarInsnNode(fixStackValue.getLoadOpcode(), i));
        insnList.remove(abstractInsnNode);
    }

    public static final void saveStack(MethodNode methodNode, AbstractInsnNode abstractInsnNode, SavedStackDescriptor savedStackDescriptor) {
        methodNode.getClass();
        abstractInsnNode.getClass();
        savedStackDescriptor.getClass();
        InsnList insnList = methodNode.instructions;
        generateStoreInstructions(methodNode, abstractInsnNode, savedStackDescriptor);
        insnList.remove(abstractInsnNode);
    }

    public static final <V extends Value> V top(Frame<V> frame) {
        frame.getClass();
        return (V) peek(frame, 0);
    }

    public static final <V extends Value> List<V> peekWords(Frame<V> frame, int i) {
        frame.getClass();
        ArrayList arrayList = new ArrayList(i);
        if (peekWordsTo$default(frame, arrayList, i, 0, 4, null) < 0) {
            return null;
        }
        return arrayList;
    }
}
