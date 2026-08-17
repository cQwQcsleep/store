package org.jetbrains.kotlin.codegen.optimization.common;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.temporaryVals.StoreLoadFrame;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.utils.SmartList;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LookupSwitchInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TableSwitchInsnNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;
import org.jetbrains.org.objectweb.asm.tree.analysis.Interpreter;
import org.jetbrains.org.objectweb.asm.tree.analysis.Value;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u0088\u0001\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0018\n\u0002\b\u0003\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0002\b&\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\b&\u0018\u0000 \\*\b\b\u0000\u0010\u0001*\u00020\u0002*\u000e\b\u0001\u0010\u0003*\b\u0012\u0004\u0012\u0002H\u00010\u00042\u00020\u0005:\u0001\\BW\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\f\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\r\u0012\u0006\u0010\u000f\u001a\u00020\r\u0012\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00028\u00010\u0011¢\u0006\u0004\b\u0013\u0010\u0014J\u0015\u0010$\u001a\u0004\u0018\u00018\u00012\u0006\u0010%\u001a\u00020&¢\u0006\u0002\u0010'J\u0019\u0010(\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00040\u0017¢\u0006\u0002\u0010)J\b\u0010*\u001a\u00020+H\u0002J5\u0010,\u001a\u00020+2\u0006\u0010-\u001a\u00020&2\u0006\u0010.\u001a\u00020\u00122\u0006\u0010/\u001a\u00028\u00012\u0006\u00100\u001a\u00028\u00012\u0006\u00101\u001a\u00028\u0001H\u0002¢\u0006\u0002\u00102J\b\u00103\u001a\u00020+H\u0002J\u0010\u00104\u001a\u00020+2\u0006\u00105\u001a\u00020\tH\u0002J\u0010\u00106\u001a\u00020+2\u0006\u00107\u001a\u00020\u001bH\u0002J\u0010\u00108\u001a\u00020+2\u0006\u00107\u001a\u00020\u001bH\u0002J\b\u00109\u001a\u00020+H\u0014J\u0015\u0010:\u001a\u00020+2\u0006\u00100\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010;J\u0018\u0010<\u001a\u00020\r2\u0006\u0010-\u001a\u00020&2\u0006\u0010=\u001a\u00020\u0012H\u0014J/\u0010>\u001a\u00020+2\u0006\u00100\u001a\u00028\u00012\u0006\u0010-\u001a\u00020&2\u0006\u0010?\u001a\u00020\u00122\b\b\u0002\u0010@\u001a\u00020\rH\u0002¢\u0006\u0002\u0010AJ'\u0010B\u001a\u00020+2\u0006\u0010C\u001a\u00020\u00122\u0006\u0010D\u001a\u00028\u00012\b\b\u0002\u0010@\u001a\u00020\rH\u0002¢\u0006\u0002\u0010EJ'\u0010F\u001a\u00020+2\u0006\u0010C\u001a\u00020\u00122\u0006\u0010D\u001a\u00028\u00012\b\b\u0002\u0010@\u001a\u00020\rH\u0002¢\u0006\u0002\u0010EJ%\u0010G\u001a\u00020+2\u0006\u0010C\u001a\u00020\u00122\u0006\u0010D\u001a\u00028\u00012\u0006\u0010@\u001a\u00020\rH\u0002¢\u0006\u0002\u0010EJ\u0018\u0010H\u001a\u00020+2\u0006\u0010I\u001a\u00020\r2\u0006\u0010C\u001a\u00020\u0012H\u0002J5\u0010J\u001a\u00020+2\u0006\u0010-\u001a\u00020&2\u0006\u0010K\u001a\u00020\u00122\u0006\u0010L\u001a\u00020\u00122\u0006\u00100\u001a\u00028\u00012\u0006\u0010%\u001a\u00020\u0012H\u0002¢\u0006\u0002\u0010MJ%\u0010N\u001a\u00020+2\u0006\u0010-\u001a\u00020&2\u0006\u00100\u001a\u00028\u00012\u0006\u0010%\u001a\u00020\u0012H\u0002¢\u0006\u0002\u0010OJ%\u0010P\u001a\u00020+2\u0006\u0010-\u001a\u00020&2\u0006\u00100\u001a\u00028\u00012\u0006\u0010%\u001a\u00020\u0012H\u0002¢\u0006\u0002\u0010OJ\u001d\u0010Q\u001a\u00020+2\u0006\u0010-\u001a\u00020R2\u0006\u00100\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010SJ\u001d\u0010T\u001a\u00020+2\u0006\u0010-\u001a\u00020U2\u0006\u00100\u001a\u00028\u0001H\u0002¢\u0006\u0002\u0010VJ-\u0010W\u001a\u00020+2\u0006\u0010-\u001a\u00020X2\u0006\u00100\u001a\u00028\u00012\u0006\u0010%\u001a\u00020\u00122\u0006\u0010L\u001a\u00020\u0012H\u0002¢\u0006\u0002\u0010YJ\f\u0010Z\u001a\u00020\u0012*\u00020&H\u0004J\u0012\u0010[\u001a\u00020\u0007*\b\u0012\u0004\u0012\u00028\u00000\u0004H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\n\u001a\b\u0012\u0004\u0012\u00028\u00000\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R \u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00020\u0012\u0012\u0004\u0012\u00028\u00010\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0012X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0016\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00028\u0000\u0018\u00010\u00040\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0018R\u001e\u0010\u0019\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001b\u0018\u00010\u001a0\u0017X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u001cR\u000e\u0010\u001d\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u001f\u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010 \u001a\u00020\u001eX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010!\u001a\u00020\"X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010#\u001a\u00020\u0012X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006]"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/common/FastAnalyzer;", "V", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Value;", "F", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", Argument.Delimiters.none, "owner", Argument.Delimiters.none, "method", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "interpreter", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Interpreter;", "pruneExceptionEdges", Argument.Delimiters.none, "useFastComputeExceptionHandlers", "useFastMergeControlFlowEdge", "newFrame", "Lkotlin/Function2;", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;Lorg/jetbrains/org/objectweb/asm/tree/analysis/Interpreter;ZZZLkotlin/jvm/functions/Function2;)V", "nInsns", "frames", Argument.Delimiters.none, "[Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "handlers", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/TryCatchBlockNode;", "[Ljava/util/List;", "isTcbStart", Argument.Delimiters.none, "isMergeNode", "queued", "queue", Argument.Delimiters.none, "top", "getFrame", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "analyze", "()[Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "analyzeMainLoop", Argument.Delimiters.none, "analyzeInstruction", "insnNode", "insnIndex", "currentlyAnalyzing", "current", "handler", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;ILorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;)V", "checkAssertions", "computeExceptionHandlers", "m", "computeExceptionHandlersForEachInsn", "tcb", "computeExceptionHandlerFast", "beforeAnalyze", "initLocals", "(Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;)V", "visitControlFlowEdge", "successor", "processControlFlowEdge", "jump", "canReuse", "(Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;IZ)V", "mergeControlFlowEdge", "dest", "frame", "(ILorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;Z)V", "fullMergeControlFlowEdge", "fastMergeControlFlowEdge", "updateQueue", "changes", "visitMeaningfulInstruction", "insnType", "insnOpcode", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;IILorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;I)V", "visitNopInsn", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;I)V", "visitOpInsn", "visitTableSwitchInsnNode", "Lorg/jetbrains/org/objectweb/asm/tree/TableSwitchInsnNode;", "(Lorg/jetbrains/org/objectweb/asm/tree/TableSwitchInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;)V", "visitLookupSwitchInsnNode", "Lorg/jetbrains/org/objectweb/asm/tree/LookupSwitchInsnNode;", "(Lorg/jetbrains/org/objectweb/asm/tree/LookupSwitchInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;)V", "visitJumpInsnNode", "Lorg/jetbrains/org/objectweb/asm/tree/JumpInsnNode;", "(Lorg/jetbrains/org/objectweb/asm/tree/JumpInsnNode;Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;II)V", "indexOf", "dump", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class FastAnalyzer<V extends Value, F extends Frame<V>> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final Frame<V>[] frames;
    private final List<TryCatchBlockNode>[] handlers;
    private final Interpreter<V> interpreter;
    private final boolean[] isMergeNode;
    private final boolean[] isTcbStart;
    private final MethodNode method;
    private final int nInsns;
    private final Function2<Integer, Integer, F> newFrame;
    private final String owner;
    private final boolean pruneExceptionEdges;
    private final int[] queue;
    private final boolean[] queued;
    private int top;
    private final boolean useFastComputeExceptionHandlers;
    private final boolean useFastMergeControlFlowEdge;

    /* JADX WARN: Multi-variable type inference failed */
    public FastAnalyzer(String str, MethodNode methodNode, Interpreter<V> interpreter, boolean z, boolean z2, boolean z3, Function2<? super Integer, ? super Integer, ? extends F> function2) {
        str.getClass();
        methodNode.getClass();
        interpreter.getClass();
        function2.getClass();
        this.owner = str;
        this.method = methodNode;
        this.interpreter = interpreter;
        this.pruneExceptionEdges = z;
        this.useFastComputeExceptionHandlers = z2;
        this.useFastMergeControlFlowEdge = z3;
        this.newFrame = function2;
        int size = methodNode.instructions.size();
        this.nInsns = size;
        this.frames = new Frame[size];
        this.handlers = new List[size];
        this.isTcbStart = new boolean[size];
        this.isMergeNode = INSTANCE.findMergeNodes(methodNode);
        this.queued = new boolean[size];
        this.queue = new int[size];
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    private final void analyzeInstruction(AbstractInsnNode insnNode, int insnIndex, F currentlyAnalyzing, F current, F handler) throws AnalyzerException {
        FastAnalyzer<V, F> fastAnalyzer;
        int i;
        List<TryCatchBlockNode> list;
        int opcode = insnNode.getOpcode();
        int nodeType = UtilKt.getNodeType(insnNode);
        if (nodeType == 8 || nodeType == 15 || nodeType == 14 || opcode == 0) {
            fastAnalyzer = this;
            i = insnIndex;
            fastAnalyzer.visitNopInsn(insnNode, currentlyAnalyzing, i);
        } else {
            current.init(currentlyAnalyzing);
            if (opcode != 177) {
                current.execute(insnNode, this.interpreter);
            }
            fastAnalyzer = this;
            i = insnIndex;
            fastAnalyzer.visitMeaningfulInstruction(insnNode, nodeType, opcode, current, i);
        }
        if ((!fastAnalyzer.pruneExceptionEdges || ((54 <= opcode && opcode < 59) || opcode == 132 || fastAnalyzer.isTcbStart[i])) && (list = fastAnalyzer.handlers[i]) != null) {
            for (TryCatchBlockNode tryCatchBlockNode : list) {
                String str = tryCatchBlockNode.type;
                if (str == null) {
                    str = "java/lang/Throwable";
                }
                Type objectType = Type.getObjectType(str);
                LabelNode labelNode = tryCatchBlockNode.handler;
                labelNode.getClass();
                int iIndexOf = fastAnalyzer.indexOf(labelNode);
                handler.init(currentlyAnalyzing);
                if (handler.getMaxStackSize() > 0) {
                    handler.clearStack();
                    handler.push(fastAnalyzer.interpreter.newExceptionValue(tryCatchBlockNode, handler, objectType));
                }
                mergeControlFlowEdge$default(fastAnalyzer, iIndexOf, handler, false, 4, null);
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    private final void analyzeMainLoop() throws AnalyzerException {
        Frame<V> frame = (Frame) this.newFrame.invoke(Integer.valueOf(this.method.maxLocals), Integer.valueOf(this.method.maxStack));
        Frame frame2 = (Frame) this.newFrame.invoke(Integer.valueOf(this.method.maxLocals), Integer.valueOf(this.method.maxStack));
        initLocals(frame);
        mergeControlFlowEdge$default(this, 0, frame, false, 4, null);
        while (true) {
            int i = this.top;
            if (i <= 0) {
                return;
            }
            int[] iArr = this.queue;
            int i2 = i - 1;
            this.top = i2;
            int i3 = iArr[i2];
            Frame<V> frame3 = this.frames[i3];
            frame3.getClass();
            this.queued[i3] = false;
            AbstractInsnNode abstractInsnNode = this.method.instructions.get(i3);
            try {
                abstractInsnNode.getClass();
                Frame frame4 = frame2;
                Frame<V> frame5 = frame;
                try {
                    analyzeInstruction(abstractInsnNode, i3, frame3, frame5, frame4);
                    frame = frame5;
                    frame2 = frame4;
                } catch (AnalyzerException e) {
                    e = e;
                    i3 = i3;
                    frame = frame5;
                    AbstractInsnNode abstractInsnNode2 = e.node;
                    InsnList insnList = this.method.instructions;
                    insnList.getClass();
                    throw new AnalyzerException(abstractInsnNode2, "Error at instruction #" + i3 + ' ' + InlineCodegenUtilsKt.insnText(abstractInsnNode, insnList) + ": " + e.getMessage() + "\ncurrent: " + dump(frame), e);
                } catch (Exception e2) {
                    e = e2;
                    i3 = i3;
                    frame = frame5;
                    InsnList insnList2 = this.method.instructions;
                    insnList2.getClass();
                    throw new AnalyzerException(abstractInsnNode, "Error at instruction #" + i3 + ' ' + InlineCodegenUtilsKt.insnText(abstractInsnNode, insnList2) + ": " + e.getMessage() + "\ncurrent: " + dump(frame), e);
                }
            } catch (AnalyzerException e3) {
                e = e3;
            } catch (Exception e4) {
                e = e4;
            }
        }
    }

    private final void checkAssertions() {
        Collection<AbstractInsnNode> collection = this.method.instructions;
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

    private final void computeExceptionHandlerFast(TryCatchBlockNode tcb) {
        LabelNode labelNode = tcb.start;
        labelNode.getClass();
        int iIndexOf = indexOf(labelNode);
        List<TryCatchBlockNode> arrayList = this.handlers[iIndexOf];
        if (arrayList == null) {
            arrayList = new ArrayList<>();
            this.handlers[iIndexOf] = arrayList;
        }
        arrayList.add(tcb);
    }

    private final void computeExceptionHandlers(MethodNode m) {
        for (TryCatchBlockNode tryCatchBlockNode : m.tryCatchBlocks) {
            boolean z = this.useFastComputeExceptionHandlers;
            tryCatchBlockNode.getClass();
            if (z) {
                computeExceptionHandlerFast(tryCatchBlockNode);
            } else {
                computeExceptionHandlersForEachInsn(tryCatchBlockNode);
            }
        }
    }

    private final void computeExceptionHandlersForEachInsn(TryCatchBlockNode tcb) {
        AbstractInsnNode next = tcb.start;
        next.getClass();
        LabelNode labelNode = tcb.end;
        while (!Intrinsics.areEqual(next, labelNode)) {
            if (UtilKt.isMeaningful(next)) {
                int iIndexOf = indexOf(next);
                List<TryCatchBlockNode> smartList = this.handlers[iIndexOf];
                if (smartList == null) {
                    smartList = new SmartList<>();
                    this.handlers[iIndexOf] = smartList;
                }
                smartList.add(tcb);
            }
            next = next.getNext();
            next.getClass();
        }
    }

    private final String dump(Frame<V> frame) {
        StringBuilder sb = new StringBuilder("{\n  locals: [\n");
        int i = this.method.maxLocals;
        for (int i2 = 0; i2 < i; i2++) {
            sb.append("    #" + i2 + ": " + frame.getLocal(i2) + '\n');
        }
        sb.append("  ]\n  stack: size=");
        int stackSize = frame.getStackSize();
        sb.append(stackSize);
        if (stackSize == 0) {
            sb.append(" []\n");
        } else {
            sb.append(" [\n");
            for (int i3 = 0; i3 < stackSize; i3++) {
                sb.append("    #" + i3 + ": " + frame.getStack(i3) + '\n');
            }
            sb.append("  ]\n");
        }
        sb.append("}\n");
        return sb.toString();
    }

    private final void fastMergeControlFlowEdge(int dest, F frame, boolean canReuse) {
        boolean z;
        Frame<V>[] frameArr = this.frames;
        if (frameArr[dest] == null) {
            if (!canReuse || this.isMergeNode[dest]) {
                Frame frame2 = (Frame) this.newFrame.invoke(Integer.valueOf(frame.getLocals()), Integer.valueOf(frame.getMaxStackSize()));
                frame2.init(frame);
                Unit unit = Unit.INSTANCE;
                frame = (F) frame2;
            }
            frameArr[dest] = frame;
            z = true;
        } else {
            z = false;
        }
        updateQueue(z, dest);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    /* JADX WARN: Multi-variable type inference failed */
    private final void fullMergeControlFlowEdge(int dest, F frame, boolean canReuse) throws AnalyzerException {
        Frame<V>[] frameArr = this.frames;
        StoreLoadFrame storeLoadFrame = frameArr[dest];
        boolean zMerge = true;
        if (canReuse && !this.isMergeNode[dest]) {
            frameArr[dest] = frame;
        } else if (storeLoadFrame == 0) {
            Object objInvoke = this.newFrame.invoke(Integer.valueOf(frame.getLocals()), Integer.valueOf(frame.getMaxStackSize()));
            ((Frame) objInvoke).init(frame);
            Unit unit = Unit.INSTANCE;
            frameArr[dest] = objInvoke;
        } else if (this.isMergeNode[dest]) {
            try {
                zMerge = storeLoadFrame.merge(frame, this.interpreter);
            } catch (AnalyzerException e) {
                throw new AnalyzerException((AbstractInsnNode) null, e.getMessage() + "\nframe: " + dump(frame) + "\noldFrame: " + dump(storeLoadFrame));
            }
        } else {
            storeLoadFrame.init(frame);
        }
        updateQueue(zMerge, dest);
    }

    private final void initLocals(F current) {
        current.setReturn(this.interpreter.newReturnTypeValue(Type.getReturnType(this.method.desc)));
        Type[] argumentTypes = Type.getArgumentTypes(this.method.desc);
        int i = 1;
        boolean z = (this.method.access & 8) == 0;
        if (z) {
            current.setLocal(0, this.interpreter.newParameterValue(true, 0, Type.getObjectType(this.owner)));
        } else {
            i = 0;
        }
        argumentTypes.getClass();
        for (Type type : argumentTypes) {
            current.setLocal(i, this.interpreter.newParameterValue(z, i, type));
            int i2 = i + 1;
            if (type.getSize() == 2) {
                current.setLocal(i2, this.interpreter.newEmptyValue(i2));
                i += 2;
            } else {
                i = i2;
            }
        }
        while (i < this.method.maxLocals) {
            current.setLocal(i, this.interpreter.newEmptyValue(i));
            i++;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    private final void mergeControlFlowEdge(int dest, F frame, boolean canReuse) throws AnalyzerException {
        if (this.useFastMergeControlFlowEdge) {
            fastMergeControlFlowEdge(dest, frame, canReuse);
        } else {
            fullMergeControlFlowEdge(dest, frame, canReuse);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    public static /* synthetic */ void mergeControlFlowEdge$default(FastAnalyzer fastAnalyzer, int i, Frame frame, boolean z, int i2, Object obj) throws AnalyzerException {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: mergeControlFlowEdge");
            return;
        }
        if ((i2 & 4) != 0) {
            z = false;
        }
        fastAnalyzer.mergeControlFlowEdge(i, frame, z);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    private final void processControlFlowEdge(F current, AbstractInsnNode insnNode, int jump, boolean canReuse) throws AnalyzerException {
        if (visitControlFlowEdge(insnNode, jump)) {
            mergeControlFlowEdge(jump, current, canReuse);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    public static /* synthetic */ void processControlFlowEdge$default(FastAnalyzer fastAnalyzer, Frame frame, AbstractInsnNode abstractInsnNode, int i, boolean z, int i2, Object obj) throws AnalyzerException {
        if (obj != null) {
            c41.a("Super calls with default arguments not supported in this target, function: processControlFlowEdge");
            return;
        }
        if ((i2 & 8) != 0) {
            z = false;
        }
        fastAnalyzer.processControlFlowEdge(frame, abstractInsnNode, i, z);
    }

    private final void updateQueue(boolean changes, int dest) {
        if (changes) {
            boolean[] zArr = this.queued;
            if (zArr[dest]) {
                return;
            }
            zArr[dest] = true;
            int[] iArr = this.queue;
            int i = this.top;
            this.top = i + 1;
            iArr[i] = dest;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    private final void visitJumpInsnNode(JumpInsnNode insnNode, F current, int insn, int insnOpcode) throws AnalyzerException {
        JumpInsnNode jumpInsnNode;
        if (insnOpcode != 167) {
            jumpInsnNode = insnNode;
            processControlFlowEdge$default(this, current, jumpInsnNode, insn + 1, false, 8, null);
        } else {
            jumpInsnNode = insnNode;
        }
        LabelNode labelNode = jumpInsnNode.label;
        labelNode.getClass();
        processControlFlowEdge$default(this, current, jumpInsnNode, indexOf(labelNode), false, 8, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    private final void visitLookupSwitchInsnNode(LookupSwitchInsnNode insnNode, F current) throws AnalyzerException {
        LabelNode labelNode = insnNode.dflt;
        labelNode.getClass();
        processControlFlowEdge$default(this, current, insnNode, indexOf(labelNode), false, 8, null);
        for (LabelNode labelNode2 : insnNode.labels) {
            labelNode2.getClass();
            processControlFlowEdge$default(this, current, insnNode, indexOf(labelNode2), false, 8, null);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    private final void visitMeaningfulInstruction(AbstractInsnNode insnNode, int insnType, int insnOpcode, F current, int insn) throws AnalyzerException {
        if (insnType == 7) {
            insnNode.getClass();
            visitJumpInsnNode((JumpInsnNode) insnNode, current, insn, insnOpcode);
            return;
        }
        if (insnType == 12) {
            insnNode.getClass();
            visitLookupSwitchInsnNode((LookupSwitchInsnNode) insnNode, current);
        } else if (insnType == 11) {
            insnNode.getClass();
            visitTableSwitchInsnNode((TableSwitchInsnNode) insnNode, current);
        } else if (insnOpcode != 191) {
            if (insnOpcode < 172 || insnOpcode > 177) {
                visitOpInsn(insnNode, current, insn);
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    private final void visitNopInsn(AbstractInsnNode insnNode, F current, int insn) throws AnalyzerException {
        processControlFlowEdge(current, insnNode, insn + 1, true);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    private final void visitOpInsn(AbstractInsnNode insnNode, F current, int insn) throws AnalyzerException {
        processControlFlowEdge$default(this, current, insnNode, insn + 1, false, 8, null);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    private final void visitTableSwitchInsnNode(TableSwitchInsnNode insnNode, F current) throws AnalyzerException {
        LabelNode labelNode = insnNode.dflt;
        labelNode.getClass();
        processControlFlowEdge$default(this, current, insnNode, indexOf(labelNode), false, 8, null);
        List list = insnNode.labels;
        list.getClass();
        for (LabelNode labelNode2 : CollectionsKt.asReversedMutable(list)) {
            labelNode2.getClass();
            processControlFlowEdge$default(this, current, insnNode, indexOf(labelNode2), false, 8, null);
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    public final Frame<V>[] analyze() throws AnalyzerException {
        if (this.nInsns == 0) {
            return this.frames;
        }
        checkAssertions();
        computeExceptionHandlers(this.method);
        for (TryCatchBlockNode tryCatchBlockNode : this.method.tryCatchBlocks) {
            boolean[] zArr = this.isTcbStart;
            LabelNode labelNode = tryCatchBlockNode.start;
            labelNode.getClass();
            zArr[indexOf(labelNode) + 1] = true;
        }
        beforeAnalyze();
        analyzeMainLoop();
        return this.frames;
    }

    public void beforeAnalyze() {
    }

    public final F getFrame(AbstractInsnNode insn) {
        insn.getClass();
        Frame<V> frame = this.frames[indexOf(insn)];
        if (frame != null) {
            return frame;
        }
        return null;
    }

    public final int indexOf(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return this.method.instructions.indexOf(abstractInsnNode);
    }

    public boolean visitControlFlowEdge(AbstractInsnNode insnNode, int successor) {
        insnNode.getClass();
        return true;
    }

    @Metadata(d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0018\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007¨\u0006\b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/common/FastAnalyzer$Companion;", Argument.Delimiters.none, "<init>", "()V", "findMergeNodes", Argument.Delimiters.none, "method", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final boolean[] findMergeNodes(MethodNode method) {
            method.getClass();
            boolean[] zArr = new boolean[method.instructions.size()];
            ListIterator it = method.instructions.iterator();
            it.getClass();
            while (it.hasNext()) {
                JumpInsnNode jumpInsnNode = (AbstractInsnNode) it.next();
                jumpInsnNode.getClass();
                int nodeType = UtilKt.getNodeType(jumpInsnNode);
                if (nodeType == 7) {
                    zArr[method.instructions.indexOf(jumpInsnNode.label)] = true;
                } else if (nodeType == 11) {
                    TableSwitchInsnNode tableSwitchInsnNode = (TableSwitchInsnNode) jumpInsnNode;
                    zArr[method.instructions.indexOf(tableSwitchInsnNode.dflt)] = true;
                    Iterator it2 = tableSwitchInsnNode.labels.iterator();
                    while (it2.hasNext()) {
                        zArr[method.instructions.indexOf((LabelNode) it2.next())] = true;
                    }
                } else if (nodeType == 12) {
                    LookupSwitchInsnNode lookupSwitchInsnNode = (LookupSwitchInsnNode) jumpInsnNode;
                    zArr[method.instructions.indexOf(lookupSwitchInsnNode.dflt)] = true;
                    Iterator it3 = lookupSwitchInsnNode.labels.iterator();
                    while (it3.hasNext()) {
                        zArr[method.instructions.indexOf((LabelNode) it3.next())] = true;
                    }
                }
            }
            Iterator it4 = method.tryCatchBlocks.iterator();
            while (it4.hasNext()) {
                zArr[method.instructions.indexOf(((TryCatchBlockNode) it4.next()).handler)] = true;
            }
            return zArr;
        }

        private Companion() {
        }
    }
}
