package org.jetbrains.kotlin.codegen.optimization.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.InsnSequence;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.inline.MaxStackFrameSizeAndLocalsCalculator;
import org.jetbrains.kotlin.codegen.optimization.LabelNormalizationMethodTransformerKt;
import org.jetbrains.kotlin.codegen.pseudoInsns.PseudoInsn;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.FrameNode;
import org.jetbrains.org.objectweb.asm.tree.IincInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.IntInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LdcInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LineNumberNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\\\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0000\u001a\n\u0010\u000b\u001a\u00020\f*\u00020\r\u001a\n\u0010\u000e\u001a\u00020\f*\u00020\r\u001a\n\u0010\u000f\u001a\u00020\f*\u00020\r\u001a\u0010\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0011\u001a\u00020\u0002H\u0002\u001a\n\u0010\u0012\u001a\u00020\f*\u00020\r\u001a\n\u0010\u0013\u001a\u00020\f*\u00020\r\u001a\f\u0010\u0014\u001a\u00020\u0001*\u00020\u0015H\u0002\u001a\u0012\u0010\u0016\u001a\u00020\f*\u00020\r2\u0006\u0010\u0017\u001a\u00020\u0006\u001a&\u0010\u0018\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u001aH\u0086\bø\u0001\u0000\u001a&\u0010\u001b\u001a\u0004\u0018\u00010\u0002*\u00020\u00022\u0012\u0010\u0019\u001a\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00010\u001aH\u0086\bø\u0001\u0000\u001a\n\u0010\u001c\u001a\u00020\u0001*\u00020\u0002\u001a\u001f\u0010 \u001a\u00020!2\u0012\u0010\"\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020#\"\u00020\u0002¢\u0006\u0002\u0010$\u001a\n\u0010%\u001a\u00020\u0001*\u00020\u0002\u001a\n\u0010&\u001a\u00020\u0001*\u00020\u0002\u001a=\u0010+\u001a\u00020\u0001\"\n\b\u0000\u0010,\u0018\u0001*\u00020\u0002*\u00020\u00022\u0006\u0010-\u001a\u00020\b2\u0017\u0010.\u001a\u0013\u0012\u0004\u0012\u0002H,\u0012\u0004\u0012\u00020\u00010\u001a¢\u0006\u0002\b/H\u0080\bø\u0001\u0000\u001aD\u00100\u001a\u0004\u0018\u0001H,\"\n\b\u0000\u0010,\u0018\u0001*\u00020\u0002*\u00020\u00022\u0006\u0010-\u001a\u00020\b2\u0017\u0010.\u001a\u0013\u0012\u0004\u0012\u0002H,\u0012\u0004\u0012\u00020\u00010\u001a¢\u0006\u0002\b/H\u0080\bø\u0001\u0000¢\u0006\u0002\u00101\u001a\u0018\u00102\u001a\u00020\f*\u00020!2\f\u00103\u001a\b\u0012\u0004\u0012\u00020\u000204\"\u0015\u0010\u0000\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0000\u0010\u0003\"\u0015\u0010\u0004\u001a\u00020\u0001*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u0004\u0010\u0003\"\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0015\u0010\u0007\u001a\u00020\b*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\t\u0010\n\"\u0017\u0010\u001d\u001a\u0004\u0018\u00010\b*\u00020\u00028F¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f\"\u0017\u0010'\u001a\u00020(*\u0004\u0018\u00010\u00028F¢\u0006\u0006\u001a\u0004\b)\u0010*\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u00065"}, d2 = {"isMeaningful", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)Z", "isBranchOrCall", "opcodeToNodeType", Argument.Delimiters.none, "nodeType", Argument.Delimiters.none, "getNodeType", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)I", "prepareForEmitting", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "updateMaxStack", "stripOptimizationMarkers", "isOptimizationMarker", "insn", "removeEmptyCatchBlocks", "removeUnusedLocalVariables", "isSize2LoadStoreOperation", "Lorg/jetbrains/org/objectweb/asm/tree/VarInsnNode;", "remapLocalVariables", "remapping", "findNextOrNull", "predicate", "Lkotlin/Function1;", "findPreviousOrNull", "hasOpcode", "intConstant", "getIntConstant", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)Ljava/lang/Integer;", "insnListOf", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "insns", Argument.Delimiters.none, "([Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "isStoreOperation", "isLoadOperation", "debugText", Argument.Delimiters.none, "getDebugText", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)Ljava/lang/String;", "isInsn", "T", "opcode", "condition", "Lkotlin/ExtensionFunctionType;", "takeInsnIf", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;ILkotlin/jvm/functions/Function1;)Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "removeAll", "nodes", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class UtilKt {
    private static final int[] opcodeToNodeType;

    static {
        int i;
        int[] iArr = new int[200];
        int i2 = 0;
        while (i2 < 200) {
            if ((i2 >= 0 && i2 < 16) || ((46 <= i2 && i2 < 54) || ((79 <= i2 && i2 < 132) || ((133 <= i2 && i2 < 153) || ((172 <= i2 && i2 < 178) || i2 == 190 || i2 == 191 || i2 == 194 || i2 == 195))))) {
                i = 0;
            } else if ((16 <= i2 && i2 < 18) || i2 == 188) {
                i = 1;
            } else if ((21 <= i2 && i2 < 26) || ((54 <= i2 && i2 < 59) || i2 == 169)) {
                i = 2;
            } else if (i2 == 187 || i2 == 189 || i2 == 192 || i2 == 193) {
                i = 3;
            } else if (178 <= i2 && i2 < 182) {
                i = 4;
            } else if (182 <= i2 && i2 < 186) {
                i = 5;
            } else if (i2 == 186) {
                i = 6;
            } else if ((153 <= i2 && i2 < 169) || i2 == 198 || i2 == 199) {
                i = 7;
            } else if (i2 == 18) {
                i = 9;
            } else if (i2 == 132) {
                i = 10;
            } else if (i2 == 170) {
                i = 11;
            } else if (i2 == 171) {
                i = 12;
            } else {
                i = i2 == 197 ? 13 : -1;
            }
            iArr[i2] = i;
            i2++;
        }
        opcodeToNodeType = iArr;
    }

    public static final AbstractInsnNode findNextOrNull(AbstractInsnNode abstractInsnNode, Function1<? super AbstractInsnNode, Boolean> function1) {
        abstractInsnNode.getClass();
        function1.getClass();
        AbstractInsnNode next = abstractInsnNode.getNext();
        while (next != null && !((Boolean) function1.invoke(next)).booleanValue()) {
            next = next.getNext();
        }
        return next;
    }

    public static final AbstractInsnNode findPreviousOrNull(AbstractInsnNode abstractInsnNode, Function1<? super AbstractInsnNode, Boolean> function1) {
        abstractInsnNode.getClass();
        function1.getClass();
        AbstractInsnNode previous = abstractInsnNode.getPrevious();
        while (previous != null && !((Boolean) function1.invoke(previous)).booleanValue()) {
            previous = previous.getPrevious();
        }
        return previous;
    }

    public static final String getDebugText(AbstractInsnNode abstractInsnNode) {
        if (abstractInsnNode == null) {
            return "<null>";
        }
        return abstractInsnNode.getClass().getSimpleName() + ": " + InlineCodegenUtilsKt.getInsnText(abstractInsnNode);
    }

    public static final Integer getIntConstant(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        int opcode = abstractInsnNode.getOpcode();
        if (2 <= opcode && opcode < 9) {
            return Integer.valueOf(abstractInsnNode.getOpcode() - 3);
        }
        if (opcode == 16 || opcode == 17) {
            return Integer.valueOf(((IntInsnNode) abstractInsnNode).operand);
        }
        if (opcode == 18) {
            Object obj = ((LdcInsnNode) abstractInsnNode).cst;
            if (obj instanceof Integer) {
                return (Integer) obj;
            }
        }
        return null;
    }

    public static final int getNodeType(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        int opcode = abstractInsnNode.getOpcode();
        if (opcode == -1) {
            if (abstractInsnNode instanceof LabelNode) {
                return 8;
            }
            if (abstractInsnNode instanceof FrameNode) {
                return 14;
            }
            return abstractInsnNode instanceof LineNumberNode ? 15 : -1;
        }
        int[] iArr = opcodeToNodeType;
        if (opcode < 0 || opcode >= iArr.length) {
            return -1;
        }
        return iArr[opcode];
    }

    public static final boolean hasOpcode(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return abstractInsnNode.getOpcode() >= 0;
    }

    public static final InsnList insnListOf(AbstractInsnNode... abstractInsnNodeArr) {
        abstractInsnNodeArr.getClass();
        InsnList insnList = new InsnList();
        for (AbstractInsnNode abstractInsnNode : abstractInsnNodeArr) {
            insnList.add(abstractInsnNode);
        }
        return insnList;
    }

    public static final boolean isBranchOrCall(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        int nodeType = getNodeType(abstractInsnNode);
        return nodeType == 5 || nodeType == 7 || nodeType == 11 || nodeType == 12;
    }

    public static final /* synthetic */ <T extends AbstractInsnNode> boolean isInsn(AbstractInsnNode abstractInsnNode, int i, Function1<? super T, Boolean> function1) {
        abstractInsnNode.getClass();
        function1.getClass();
        AbstractInsnNode abstractInsnNode2 = null;
        if (abstractInsnNode.getOpcode() != i) {
            abstractInsnNode = null;
        }
        Intrinsics.reifiedOperationMarker(2, "T");
        if (abstractInsnNode != null && ((Boolean) function1.invoke(abstractInsnNode)).booleanValue()) {
            abstractInsnNode2 = abstractInsnNode;
        }
        return abstractInsnNode2 != null;
    }

    public static final boolean isLoadOperation(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        int opcode = abstractInsnNode.getOpcode();
        return 21 <= opcode && opcode < 26;
    }

    public static final boolean isMeaningful(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        int nodeType = getNodeType(abstractInsnNode);
        return (nodeType == 8 || nodeType == 14 || nodeType == 15) ? false : true;
    }

    private static final boolean isOptimizationMarker(AbstractInsnNode abstractInsnNode) {
        return PseudoInsn.STORE_NOT_NULL.isa(abstractInsnNode);
    }

    private static final boolean isSize2LoadStoreOperation(VarInsnNode varInsnNode) {
        return varInsnNode.getOpcode() == 22 || varInsnNode.getOpcode() == 24 || varInsnNode.getOpcode() == 55 || varInsnNode.getOpcode() == 57;
    }

    public static final boolean isStoreOperation(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        int opcode = abstractInsnNode.getOpcode();
        return 54 <= opcode && opcode < 59;
    }

    public static final void prepareForEmitting(MethodNode methodNode) {
        methodNode.getClass();
        stripOptimizationMarkers(methodNode);
        removeEmptyCatchBlocks(methodNode);
        List list = methodNode.localVariables;
        list.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            LocalVariableNode localVariableNode = (LocalVariableNode) obj;
            LabelNode labelNode = localVariableNode.start;
            labelNode.getClass();
            Iterator it = new InsnSequence(labelNode, localVariableNode.end).iterator();
            while (it.hasNext()) {
                if (isMeaningful((AbstractInsnNode) it.next())) {
                    arrayList.add(obj);
                    break;
                }
            }
        }
        methodNode.localVariables = arrayList;
        AbstractInsnNode last = methodNode.instructions.getLast();
        while (true) {
            last.getClass();
            if (isMeaningful(last)) {
                updateMaxStack(methodNode);
                return;
            }
            AbstractInsnNode previous = last.getPrevious();
            if (getNodeType(last) == 15) {
                methodNode.instructions.remove(last);
            }
            last = previous;
        }
    }

    public static final void remapLocalVariables(MethodNode methodNode, int[] iArr) {
        methodNode.getClass();
        iArr.getClass();
        ListIterator it = methodNode.instructions.iterator();
        it.getClass();
        while (it.hasNext()) {
            IincInsnNode iincInsnNode = (AbstractInsnNode) it.next();
            if (iincInsnNode instanceof VarInsnNode) {
                VarInsnNode varInsnNode = (VarInsnNode) iincInsnNode;
                varInsnNode.var = iArr[varInsnNode.var];
            } else if (iincInsnNode instanceof IincInsnNode) {
                IincInsnNode iincInsnNode2 = iincInsnNode;
                iincInsnNode2.var = iArr[iincInsnNode2.var];
            }
        }
        for (LocalVariableNode localVariableNode : methodNode.localVariables) {
            localVariableNode.index = iArr[localVariableNode.index];
        }
    }

    public static final void removeAll(InsnList insnList, Collection<? extends AbstractInsnNode> collection) {
        insnList.getClass();
        collection.getClass();
        Iterator<? extends AbstractInsnNode> it = collection.iterator();
        while (it.hasNext()) {
            insnList.remove(it.next());
        }
    }

    public static final void removeEmptyCatchBlocks(MethodNode methodNode) {
        methodNode.getClass();
        List list = methodNode.tryCatchBlocks;
        list.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj : list) {
            TryCatchBlockNode tryCatchBlockNode = (TryCatchBlockNode) obj;
            LabelNode labelNode = tryCatchBlockNode.start;
            labelNode.getClass();
            Iterator it = new InsnSequence(labelNode, tryCatchBlockNode.end).iterator();
            while (it.hasNext()) {
                if (isMeaningful((AbstractInsnNode) it.next())) {
                    arrayList.add(obj);
                    break;
                }
            }
        }
        methodNode.tryCatchBlocks = arrayList;
    }

    public static final void removeUnusedLocalVariables(MethodNode methodNode) {
        int i;
        methodNode.getClass();
        int i2 = methodNode.maxLocals;
        boolean[] zArr = new boolean[i2];
        for (int i3 = 0; i3 < i2; i3++) {
            zArr[i3] = false;
        }
        if ((methodNode.access & 8) != 0) {
            i = 0;
        } else {
            zArr[0] = true;
            i = 1;
        }
        Type[] argumentTypes = Type.getArgumentTypes(methodNode.desc);
        argumentTypes.getClass();
        for (Type type : argumentTypes) {
            int size = type.getSize();
            int i4 = 0;
            while (i4 < size) {
                zArr[i] = true;
                i4++;
                i++;
            }
        }
        ListIterator it = methodNode.instructions.iterator();
        it.getClass();
        while (it.hasNext()) {
            IincInsnNode iincInsnNode = (AbstractInsnNode) it.next();
            if (iincInsnNode instanceof VarInsnNode) {
                VarInsnNode varInsnNode = (VarInsnNode) iincInsnNode;
                int i5 = varInsnNode.var;
                zArr[i5] = true;
                if (isSize2LoadStoreOperation(varInsnNode)) {
                    zArr[i5 + 1] = true;
                }
            } else if (iincInsnNode instanceof IincInsnNode) {
                zArr[iincInsnNode.var] = true;
            }
        }
        for (LocalVariableNode localVariableNode : methodNode.localVariables) {
            int i6 = localVariableNode.index;
            zArr[i6] = true;
            if (Type.getType(localVariableNode.desc).getSize() == 2) {
                zArr[i6 + 1] = true;
            }
        }
        for (int i7 = 0; i7 < i2; i7++) {
            if (!zArr[i7]) {
                int i8 = methodNode.maxLocals;
                int[] iArr = new int[i8];
                for (int i9 = 0; i9 < i8; i9++) {
                    iArr[i9] = 0;
                }
                int i10 = 0;
                for (int i11 = 0; i11 < i8; i11++) {
                    iArr[i11] = i10;
                    if (zArr[i11]) {
                        i10++;
                    }
                }
                remapLocalVariables(methodNode, iArr);
                return;
            }
        }
    }

    public static final void stripOptimizationMarkers(MethodNode methodNode) {
        methodNode.getClass();
        AbstractInsnNode first = methodNode.instructions.getFirst();
        while (first != null) {
            if (isOptimizationMarker(first)) {
                InsnList insnList = methodNode.instructions;
                insnList.getClass();
                first = LabelNormalizationMethodTransformerKt.removeNodeGetNext(insnList, first);
            } else {
                first = first.getNext();
            }
        }
    }

    public static final /* synthetic */ <T extends AbstractInsnNode> T takeInsnIf(AbstractInsnNode abstractInsnNode, int i, Function1<? super T, Boolean> function1) {
        abstractInsnNode.getClass();
        function1.getClass();
        if (abstractInsnNode.getOpcode() != i) {
            abstractInsnNode = (T) null;
        }
        Intrinsics.reifiedOperationMarker(2, "T");
        if (abstractInsnNode == null || !((Boolean) function1.invoke(abstractInsnNode)).booleanValue()) {
            return null;
        }
        return (T) abstractInsnNode;
    }

    public static final void updateMaxStack(final MethodNode methodNode) {
        methodNode.getClass();
        methodNode.maxStack = -1;
        methodNode.accept(new MaxStackFrameSizeAndLocalsCalculator(589824, methodNode.access, methodNode.desc, new MethodVisitor() { // from class: org.jetbrains.kotlin.codegen.optimization.common.UtilKt.updateMaxStack.1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(589824);
            }

            public void visitMaxs(int maxStack, int maxLocals) {
                methodNode.maxStack = maxStack;
            }
        }));
    }
}
