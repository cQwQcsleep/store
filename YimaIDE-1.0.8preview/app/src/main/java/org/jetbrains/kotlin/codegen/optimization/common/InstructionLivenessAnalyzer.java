package org.jetbrains.kotlin.codegen.optimization.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.LookupSwitchInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TableSwitchInsnNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000h\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\u0019\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010 \u001a\u00020\u0012J\b\u0010!\u001a\u00020\"H\u0002J\b\u0010#\u001a\u00020\"H\u0002J\b\u0010$\u001a\u00020\"H\u0002J\u0010\u0010%\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u0010H\u0002J\u0010\u0010'\u001a\u00020\"2\u0006\u0010(\u001a\u00020)H\u0002J\u0010\u0010*\u001a\u00020\"2\u0006\u0010(\u001a\u00020+H\u0002J \u0010,\u001a\u00020\"2\u0006\u0010(\u001a\u00020-2\u0006\u0010&\u001a\u00020\u00102\u0006\u0010.\u001a\u00020\u0010H\u0002J\b\u0010/\u001a\u00020\"H\u0002J\u0010\u00100\u001a\u00020\"2\u0006\u00101\u001a\u00020\u0003H\u0002J\u0010\u00102\u001a\u00020\"2\u0006\u0010&\u001a\u00020\u0010H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0016\u0010\f\u001a\n \u000e*\u0004\u0018\u00010\r0\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0010X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0013\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0016\u0018\u00010\u00150\u0014X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0017R\u000e\u0010\u0018\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0019\u001a\u00020\u001aX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001b\u001a\u00020\u0010X\u0082\u000e¢\u0006\u0002\n\u0000R\u0018\u0010\u001c\u001a\u00020\u0010*\u00020\u001d8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001e\u0010\u001f¨\u00063"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/common/InstructionLivenessAnalyzer;", Argument.Delimiters.none, "method", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "visitExceptionHandlers", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;Z)V", "getMethod", "()Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "getVisitExceptionHandlers", "()Z", "instructions", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "kotlin.jvm.PlatformType", "nInsns", Argument.Delimiters.none, "isLive", Argument.Delimiters.none, "handlers", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/TryCatchBlockNode;", "[Ljava/util/List;", "queued", "queue", Argument.Delimiters.none, "top", "indexOf", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "getIndexOf", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)I", "analyze", "traverseCfg", Argument.Delimiters.none, "localVariableAndTryCatchBlockLabelsAreAlwaysLive", "checkAssertions", "visitOpInsn", "insn", "visitTableSwitchInsnNode", "insnNode", "Lorg/jetbrains/org/objectweb/asm/tree/TableSwitchInsnNode;", "visitLookupSwitchInsnNode", "Lorg/jetbrains/org/objectweb/asm/tree/LookupSwitchInsnNode;", "visitJumpInsnNode", "Lorg/jetbrains/org/objectweb/asm/tree/JumpInsnNode;", "insnOpcode", "initControlFlowAnalysis", "computeExceptionHandlersForEachInsn", "m", "visitControlFlowEdge", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class InstructionLivenessAnalyzer {
    private final List<TryCatchBlockNode>[] handlers;
    private final InsnList instructions;
    private final boolean[] isLive;
    private final MethodNode method;
    private final int nInsns;
    private final int[] queue;
    private final boolean[] queued;
    private int top;
    private final boolean visitExceptionHandlers;

    public InstructionLivenessAnalyzer(MethodNode methodNode, boolean z) {
        methodNode.getClass();
        this.method = methodNode;
        this.visitExceptionHandlers = z;
        InsnList insnList = methodNode.instructions;
        this.instructions = insnList;
        int size = insnList.size();
        this.nInsns = size;
        this.isLive = new boolean[size];
        this.handlers = new List[size];
        this.queued = new boolean[size];
        this.queue = new int[size];
    }

    private final void checkAssertions() {
        Collection<AbstractInsnNode> collection = this.instructions;
        collection.getClass();
        if ((collection instanceof Collection) && collection.isEmpty()) {
            return;
        }
        for (AbstractInsnNode abstractInsnNode : collection) {
            if (abstractInsnNode.getOpcode() == 168 || abstractInsnNode.getOpcode() == 169) {
                x01.a("Subroutines are deprecated since Java 6");
                return;
            }
        }
    }

    private final void computeExceptionHandlersForEachInsn(MethodNode m) {
        if (this.visitExceptionHandlers) {
            for (TryCatchBlockNode tryCatchBlockNode : m.tryCatchBlocks) {
                LabelNode labelNode = tryCatchBlockNode.start;
                labelNode.getClass();
                LabelNode labelNode2 = tryCatchBlockNode.end;
                labelNode2.getClass();
                int indexOf = getIndexOf(labelNode2);
                for (int indexOf2 = getIndexOf(labelNode); indexOf2 < indexOf; indexOf2++) {
                    AbstractInsnNode abstractInsnNode = this.instructions.get(indexOf2);
                    abstractInsnNode.getClass();
                    if (UtilKt.isMeaningful(abstractInsnNode)) {
                        List<TryCatchBlockNode> arrayList = this.handlers[indexOf2];
                        if (arrayList == null) {
                            arrayList = new ArrayList<>();
                            this.handlers[indexOf2] = arrayList;
                        }
                        arrayList.add(tryCatchBlockNode);
                    }
                }
            }
        }
    }

    private final int getIndexOf(AbstractInsnNode abstractInsnNode) {
        return this.instructions.indexOf(abstractInsnNode);
    }

    private final void initControlFlowAnalysis() {
        visitControlFlowEdge(0);
    }

    private final void localVariableAndTryCatchBlockLabelsAreAlwaysLive() {
        for (LocalVariableNode localVariableNode : this.method.localVariables) {
            boolean[] zArr = this.isLive;
            LabelNode labelNode = localVariableNode.start;
            labelNode.getClass();
            zArr[getIndexOf(labelNode)] = true;
            boolean[] zArr2 = this.isLive;
            LabelNode labelNode2 = localVariableNode.end;
            labelNode2.getClass();
            zArr2[getIndexOf(labelNode2)] = true;
        }
        for (TryCatchBlockNode tryCatchBlockNode : this.method.tryCatchBlocks) {
            boolean[] zArr3 = this.isLive;
            LabelNode labelNode3 = tryCatchBlockNode.start;
            labelNode3.getClass();
            zArr3[getIndexOf(labelNode3)] = true;
            boolean[] zArr4 = this.isLive;
            LabelNode labelNode4 = tryCatchBlockNode.end;
            labelNode4.getClass();
            zArr4[getIndexOf(labelNode4)] = true;
            boolean[] zArr5 = this.isLive;
            LabelNode labelNode5 = tryCatchBlockNode.handler;
            labelNode5.getClass();
            zArr5[getIndexOf(labelNode5)] = true;
        }
    }

    /* JADX WARN: Code duplicated, block: B:25:0x004f  */
    private final void traverseCfg() {
        List<TryCatchBlockNode> list;
        while (true) {
            int i = this.top;
            if (i <= 0) {
                return;
            }
            int[] iArr = this.queue;
            int i2 = i - 1;
            this.top = i2;
            int i3 = iArr[i2];
            AbstractInsnNode abstractInsnNode = this.method.instructions.get(i3);
            int opcode = abstractInsnNode.getOpcode();
            int nodeType = UtilKt.getNodeType(abstractInsnNode);
            if (nodeType == 7) {
                visitJumpInsnNode((JumpInsnNode) abstractInsnNode, i3, opcode);
            } else if (nodeType == 8) {
                visitOpInsn(i3);
            } else if (nodeType == 11) {
                visitTableSwitchInsnNode((TableSwitchInsnNode) abstractInsnNode);
            } else if (nodeType == 12) {
                visitLookupSwitchInsnNode((LookupSwitchInsnNode) abstractInsnNode);
            } else if (nodeType == 14 || nodeType == 15) {
                visitOpInsn(i3);
            } else if (opcode != 191 && (opcode < 172 || opcode > 177)) {
                visitOpInsn(i3);
            }
            if (this.visitExceptionHandlers && (list = this.handlers[i3]) != null) {
                Iterator<T> it = list.iterator();
                while (it.hasNext()) {
                    LabelNode labelNode = ((TryCatchBlockNode) it.next()).handler;
                    labelNode.getClass();
                    visitControlFlowEdge(getIndexOf(labelNode));
                }
            }
        }
    }

    private final void visitControlFlowEdge(int insn) {
        boolean[] zArr = this.isLive;
        boolean z = zArr[insn];
        zArr[insn] = true;
        if (z) {
            return;
        }
        boolean[] zArr2 = this.queued;
        if (zArr2[insn]) {
            return;
        }
        zArr2[insn] = true;
        int[] iArr = this.queue;
        int i = this.top;
        this.top = i + 1;
        iArr[i] = insn;
    }

    private final void visitJumpInsnNode(JumpInsnNode insnNode, int insn, int insnOpcode) {
        if (insnOpcode != 167 && insnOpcode != 168) {
            visitControlFlowEdge(insn + 1);
        }
        LabelNode labelNode = insnNode.label;
        labelNode.getClass();
        visitControlFlowEdge(getIndexOf(labelNode));
    }

    private final void visitLookupSwitchInsnNode(LookupSwitchInsnNode insnNode) {
        LabelNode labelNode = insnNode.dflt;
        labelNode.getClass();
        visitControlFlowEdge(getIndexOf(labelNode));
        for (LabelNode labelNode2 : insnNode.labels) {
            labelNode2.getClass();
            visitControlFlowEdge(getIndexOf(labelNode2));
        }
    }

    private final void visitOpInsn(int insn) {
        visitControlFlowEdge(insn + 1);
    }

    private final void visitTableSwitchInsnNode(TableSwitchInsnNode insnNode) {
        LabelNode labelNode = insnNode.dflt;
        labelNode.getClass();
        visitControlFlowEdge(getIndexOf(labelNode));
        for (LabelNode labelNode2 : insnNode.labels) {
            labelNode2.getClass();
            visitControlFlowEdge(getIndexOf(labelNode2));
        }
    }

    public final boolean[] analyze() {
        if (this.nInsns == 0) {
            return this.isLive;
        }
        checkAssertions();
        computeExceptionHandlersForEachInsn(this.method);
        initControlFlowAnalysis();
        traverseCfg();
        localVariableAndTryCatchBlockLabelsAreAlwaysLive();
        if (this.instructions.getLast() instanceof LabelNode) {
            boolean[] zArr = this.isLive;
            AbstractInsnNode last = this.instructions.getLast();
            last.getClass();
            zArr[getIndexOf(last)] = true;
        }
        return this.isLive;
    }

    public final MethodNode getMethod() {
        return this.method;
    }

    public final boolean getVisitExceptionHandlers() {
        return this.visitExceptionHandlers;
    }

    public /* synthetic */ InstructionLivenessAnalyzer(MethodNode methodNode, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this(methodNode, (i & 2) != 0 ? true : z);
    }
}
