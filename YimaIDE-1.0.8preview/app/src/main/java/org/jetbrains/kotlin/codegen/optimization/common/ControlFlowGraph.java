package org.jetbrains.kotlin.codegen.optimization.common;

import it.unimi.dsi.fastutil.ints.AbstractIntCollection;
import it.unimi.dsi.fastutil.ints.IntOpenHashSet;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LookupSwitchInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TableSwitchInsnNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0010!\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u0000 \u00132\u00020\u0001:\u0002\u0012\u0013B\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0014\u0010\f\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u0010\u001a\u00020\tJ\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u000e\u001a\u00020\u000fJ\u0014\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\t0\r2\u0006\u0010\u0010\u001a\u00020\tR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\nR\u001c\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\u0007X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\n¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/common/ControlFlowGraph;", Argument.Delimiters.none, "insns", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/InsnList;)V", "successors", Argument.Delimiters.none, Argument.Delimiters.none, Argument.Delimiters.none, "[Ljava/util/List;", "predecessors", "getSuccessorsIndices", Argument.Delimiters.none, "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "index", "getPredecessorsIndices", "Builder", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ControlFlowGraph {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final InsnList insns;
    private final List<Integer>[] predecessors;
    private final List<Integer>[] successors;

    @Metadata(d1 = {"\u0000v\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\u0015\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\u001e\u001a\u00020\u001fJ\b\u0010 \u001a\u00020!H\u0002J\b\u0010\"\u001a\u00020!H\u0002J\u0010\u0010#\u001a\u00020!2\u0006\u0010$\u001a\u00020\fH\u0002J\u0018\u0010%\u001a\u00020!2\u0006\u0010$\u001a\u00020\f2\u0006\u0010&\u001a\u00020'H\u0002J\u0018\u0010(\u001a\u00020!2\u0006\u0010$\u001a\u00020\f2\u0006\u0010&\u001a\u00020)H\u0002J \u0010*\u001a\u00020!2\u0006\u0010&\u001a\u00020+2\u0006\u0010$\u001a\u00020\f2\u0006\u0010,\u001a\u00020\fH\u0002J\b\u0010-\u001a\u00020!H\u0002J\b\u0010.\u001a\u00020!H\u0002J\u0018\u0010/\u001a\u00020!2\u0006\u00100\u001a\u00020\f2\u0006\u00101\u001a\u00020\fH\u0002J\u0018\u00102\u001a\u00020!2\u0006\u00100\u001a\u00020\f2\u0006\u00101\u001a\u00020\fH\u0002J\u0010\u00103\u001a\u00020!2\u0006\u0010$\u001a\u00020\fH\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0016\u0010\b\u001a\n \n*\u0004\u0018\u00010\t0\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\r\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0010\u0018\u00010\u000f0\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0011R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0014\u001a\u00020\u0015X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0016\u001a\u00020\fX\u0082\u000e¢\u0006\u0002\n\u0000R\u0016\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00180\u000eX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0019R\u0018\u0010\u001a\u001a\u00020\f*\u00020\u001b8BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u001c\u0010\u001d¨\u00064"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/common/ControlFlowGraph$Builder;", Argument.Delimiters.none, "method", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "followExceptions", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;Z)V", "instructions", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "kotlin.jvm.PlatformType", "nInsns", Argument.Delimiters.none, "handlers", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/TryCatchBlockNode;", "[Ljava/util/List;", "queued", Argument.Delimiters.none, "queue", Argument.Delimiters.none, "top", "predecessors", "Lit/unimi/dsi/fastutil/ints/IntOpenHashSet;", "[Lit/unimi/dsi/fastutil/ints/IntOpenHashSet;", "indexOf", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "getIndexOf", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)I", "build", "Lorg/jetbrains/kotlin/codegen/optimization/common/ControlFlowGraph;", "traverseCfg", Argument.Delimiters.none, "checkAssertions", "visitOpInsn", "insn", "visitTableSwitchInsnNode", "insnNode", "Lorg/jetbrains/org/objectweb/asm/tree/TableSwitchInsnNode;", "visitLookupSwitchInsnNode", "Lorg/jetbrains/org/objectweb/asm/tree/LookupSwitchInsnNode;", "visitJumpInsnNode", "Lorg/jetbrains/org/objectweb/asm/tree/JumpInsnNode;", "insnOpcode", "initControlFlowAnalysis", "computeExceptionHandlersForEachInsn", "visitExceptionEdge", "from", "to", "visitEdge", "enqueue", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Builder {
        private final boolean followExceptions;
        private final List<TryCatchBlockNode>[] handlers;
        private final InsnList instructions;
        private final MethodNode method;
        private final int nInsns;
        private final IntOpenHashSet[] predecessors;
        private final int[] queue;
        private final boolean[] queued;
        private int top;

        public Builder(MethodNode methodNode, boolean z) {
            methodNode.getClass();
            this.method = methodNode;
            this.followExceptions = z;
            InsnList insnList = methodNode.instructions;
            this.instructions = insnList;
            int size = insnList.size();
            this.nInsns = size;
            this.handlers = new List[size];
            this.queued = new boolean[size];
            this.queue = new int[size];
            IntOpenHashSet[] intOpenHashSetArr = new IntOpenHashSet[size];
            for (int i = 0; i < size; i++) {
                intOpenHashSetArr[i] = new IntOpenHashSet();
            }
            this.predecessors = intOpenHashSetArr;
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

        private final void computeExceptionHandlersForEachInsn() {
            for (TryCatchBlockNode tryCatchBlockNode : this.method.tryCatchBlocks) {
                LabelNode labelNode = tryCatchBlockNode.start;
                labelNode.getClass();
                LabelNode labelNode2 = tryCatchBlockNode.end;
                labelNode2.getClass();
                int indexOf = getIndexOf(labelNode2);
                for (int indexOf2 = getIndexOf(labelNode); indexOf2 < indexOf; indexOf2++) {
                    List<TryCatchBlockNode> arrayList = this.handlers[indexOf2];
                    if (arrayList == null) {
                        arrayList = new ArrayList<>();
                        this.handlers[indexOf2] = arrayList;
                    }
                    arrayList.add(tryCatchBlockNode);
                }
            }
        }

        private final void enqueue(int insn) {
            boolean[] zArr = this.queued;
            if (zArr[insn]) {
                return;
            }
            zArr[insn] = true;
            int[] iArr = this.queue;
            int i = this.top;
            this.top = i + 1;
            iArr[i] = insn;
        }

        private final int getIndexOf(AbstractInsnNode abstractInsnNode) {
            return this.instructions.indexOf(abstractInsnNode);
        }

        private final void initControlFlowAnalysis() {
            this.queued[0] = true;
            int[] iArr = this.queue;
            int i = this.top;
            this.top = i + 1;
            iArr[i] = 0;
        }

        /* JADX WARN: Code duplicated, block: B:25:0x004f  */
        private final void traverseCfg() {
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
                    visitTableSwitchInsnNode(i3, (TableSwitchInsnNode) abstractInsnNode);
                } else if (nodeType == 12) {
                    visitLookupSwitchInsnNode(i3, (LookupSwitchInsnNode) abstractInsnNode);
                } else if (nodeType == 14 || nodeType == 15) {
                    visitOpInsn(i3);
                } else if (opcode != 191 && (opcode < 172 || opcode > 177)) {
                    visitOpInsn(i3);
                }
                List<TryCatchBlockNode> list = this.handlers[i3];
                if (list != null) {
                    Iterator<T> it = list.iterator();
                    while (it.hasNext()) {
                        LabelNode labelNode = ((TryCatchBlockNode) it.next()).handler;
                        labelNode.getClass();
                        visitExceptionEdge(i3, getIndexOf(labelNode));
                    }
                }
            }
        }

        private final void visitEdge(int from, int to) {
            this.predecessors[to].add(from);
            enqueue(to);
        }

        private final void visitExceptionEdge(int from, int to) {
            if (this.followExceptions) {
                this.predecessors[to].add(from);
            }
            enqueue(to);
        }

        private final void visitJumpInsnNode(JumpInsnNode insnNode, int insn, int insnOpcode) {
            if (insnOpcode != 167 && insnOpcode != 168) {
                visitEdge(insn, insn + 1);
            }
            LabelNode labelNode = insnNode.label;
            labelNode.getClass();
            visitEdge(insn, getIndexOf(labelNode));
        }

        private final void visitLookupSwitchInsnNode(int insn, LookupSwitchInsnNode insnNode) {
            LabelNode labelNode = insnNode.dflt;
            labelNode.getClass();
            visitEdge(insn, getIndexOf(labelNode));
            for (LabelNode labelNode2 : insnNode.labels) {
                labelNode2.getClass();
                visitEdge(insn, getIndexOf(labelNode2));
            }
        }

        private final void visitOpInsn(int insn) {
            visitEdge(insn, insn + 1);
        }

        private final void visitTableSwitchInsnNode(int insn, TableSwitchInsnNode insnNode) {
            LabelNode labelNode = insnNode.dflt;
            labelNode.getClass();
            visitEdge(insn, getIndexOf(labelNode));
            for (LabelNode labelNode2 : insnNode.labels) {
                labelNode2.getClass();
                visitEdge(insn, getIndexOf(labelNode2));
            }
        }

        public final ControlFlowGraph build() {
            InsnList insnList = this.method.instructions;
            insnList.getClass();
            ControlFlowGraph controlFlowGraph = new ControlFlowGraph(insnList, null);
            if (this.nInsns != 0) {
                checkAssertions();
                computeExceptionHandlersForEachInsn();
                initControlFlowAnalysis();
                traverseCfg();
                AbstractIntCollection[] abstractIntCollectionArr = this.predecessors;
                int length = abstractIntCollectionArr.length;
                for (int i = 0; i < length; i++) {
                    int[] intArray = abstractIntCollectionArr[i].toIntArray();
                    intArray.getClass();
                    for (int i2 : intArray) {
                        controlFlowGraph.predecessors[i].add(Integer.valueOf(i2));
                        controlFlowGraph.successors[i2].add(Integer.valueOf(i));
                    }
                }
            }
            return controlFlowGraph;
        }
    }

    private ControlFlowGraph(InsnList insnList) {
        this.insns = insnList;
        int size = insnList.size();
        List<Integer>[] listArr = new List[size];
        for (int i = 0; i < size; i++) {
            listArr[i] = new ArrayList(2);
        }
        this.successors = listArr;
        int size2 = this.insns.size();
        List<Integer>[] listArr2 = new List[size2];
        for (int i2 = 0; i2 < size2; i2++) {
            listArr2[i2] = new ArrayList(2);
        }
        this.predecessors = listArr2;
    }

    @JvmStatic
    public static final ControlFlowGraph build(MethodNode methodNode, boolean z) {
        return INSTANCE.build(methodNode, z);
    }

    public final List<Integer> getPredecessorsIndices(AbstractInsnNode insn) {
        insn.getClass();
        return getPredecessorsIndices(this.insns.indexOf(insn));
    }

    public final List<Integer> getSuccessorsIndices(AbstractInsnNode insn) {
        insn.getClass();
        return getSuccessorsIndices(this.insns.indexOf(insn));
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\b\b\u0002\u0010\b\u001a\u00020\tH\u0007b\u0002\b\n¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/common/ControlFlowGraph$Companion;", Argument.Delimiters.none, "<init>", "()V", "build", "Lorg/jetbrains/kotlin/codegen/optimization/common/ControlFlowGraph;", "node", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "followExceptions", Argument.Delimiters.none, "Lkotlin/jvm/JvmStatic;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public static /* synthetic */ ControlFlowGraph build$default(Companion companion, MethodNode methodNode, boolean z, int i, Object obj) {
            if ((i & 2) != 0) {
                z = true;
            }
            return companion.build(methodNode, z);
        }

        @JvmStatic
        public final ControlFlowGraph build(MethodNode node, boolean followExceptions) {
            node.getClass();
            return new Builder(node, followExceptions).build();
        }

        private Companion() {
        }
    }

    public final List<Integer> getPredecessorsIndices(int index) {
        return this.predecessors[index];
    }

    public final List<Integer> getSuccessorsIndices(int index) {
        return this.successors[index];
    }

    public /* synthetic */ ControlFlowGraph(InsnList insnList, DefaultConstructorMarker defaultConstructorMarker) {
        this(insnList);
    }
}
