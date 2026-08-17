package org.jetbrains.kotlin.codegen.optimization.fixStack;

import com.intellij.util.containers.Stack;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.fixStack.FixStackAnalyzer;
import org.jetbrains.kotlin.codegen.pseudoInsns.PseudoInsn;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.utils.SmartList;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;
import org.jetbrains.org.objectweb.asm.tree.analysis.Interpreter;
import org.jetbrains.org.objectweb.asm.tree.analysis.Value;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000[\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t*\u0001\u000f\b\u0000\u0018\u0000 '2\u00020\u0001:\u0002'(B'\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t¢\u0006\u0004\b\n\u0010\u000bJ\u0006\u0010\u001f\u001a\u00020 J\u0016\u0010!\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001d2\u0006\u0010\"\u001a\u00020\u0015J\u0016\u0010#\u001a\n\u0012\u0004\u0012\u00020\u001e\u0018\u00010\u001d2\u0006\u0010\"\u001a\u00020\u0015J\u000e\u0010$\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0015J\u000e\u0010%\u001a\u00020\u00182\u0006\u0010\"\u001a\u00020\u0015J\b\u0010&\u001a\u00020 H\u0002R\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0010R6\u0010\u0011\u001a*\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u00140\u0012j\u0014\u0012\u0004\u0012\u00020\u0013\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00150\u0014`\u0016X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\u0019\u001a\u00020\u00182\u0006\u0010\u0017\u001a\u00020\u0018@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u001bR6\u0010\u001c\u001a*\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d0\u0012j\u0014\u0012\u0004\u0012\u00020\u0015\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001e0\u001d`\u0016X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackAnalyzer;", Argument.Delimiters.none, "owner", Argument.Delimiters.none, "method", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "context", "Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackContext;", "skipBreakContinueGotoEdges", Argument.Delimiters.none, "<init>", "(Ljava/lang/String;Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackContext;Z)V", "getContext", "()Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackContext;", "analyzer", "org/jetbrains/kotlin/codegen/optimization/fixStack/FixStackAnalyzer$analyzer$1", "Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackAnalyzer$analyzer$1;", "loopEntryPointMarkers", "Ljava/util/HashMap;", "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "Lorg/jetbrains/kotlin/utils/SmartList;", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "Lkotlin/collections/HashMap;", "value", Argument.Delimiters.none, "maxExtraStackSize", "getMaxExtraStackSize", "()I", "spilledStacks", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackValue;", "analyze", Argument.Delimiters.none, "getStackToSpill", "location", "getActualStack", "getActualStackSize", "getExpectedStackSize", "recordLoopEntryPointMarkers", "Companion", "FixStackFrame", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class FixStackAnalyzer {
    public static final int DEAD_CODE_STACK_SIZE = -1;
    private final FixStackAnalyzer$analyzer$1 analyzer;
    private final FixStackContext context;
    private final HashMap<LabelNode, SmartList<AbstractInsnNode>> loopEntryPointMarkers;
    private int maxExtraStackSize;
    private final boolean skipBreakContinueGotoEdges;
    private final HashMap<AbstractInsnNode, List<FixStackValue>> spilledStacks;

    @Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0010\u001e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0086\u0004\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u000e\u0010\u000b\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u0001H\u0016J\b\u0010\f\u001a\u00020\rH\u0016J\u001e\u0010\u000e\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012H\u0016J\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00020\u0017J\u0010\u0010\u0018\u001a\u00020\r2\u0006\u0010\u0019\u001a\u00020\u0002H\u0016J\u0016\u0010\u001a\u001a\u00020\r2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00020\u001cH\u0002J\b\u0010\u001d\u001a\u00020\u0002H\u0016J\u0018\u0010\u001e\u001a\u00020\r2\u0006\u0010\u001f\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u0002H\u0016J&\u0010 \u001a\u00020!2\u000e\u0010\"\u001a\n\u0012\u0006\b\u0001\u0012\u00020\u00020\u00012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00020\u0012H\u0016J\u0010\u0010#\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010$\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010%\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010&\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002J\u0010\u0010'\u001a\u00020\r2\u0006\u0010\u000f\u001a\u00020\u0010H\u0002R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u0013\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0014\u0010\u0015¨\u0006("}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackAnalyzer$FixStackFrame;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackValue;", "nLocals", Argument.Delimiters.none, "nStack", "<init>", "(Lorg/jetbrains/kotlin/codegen/optimization/fixStack/FixStackAnalyzer;II)V", "extraStack", "Lcom/intellij/util/containers/Stack;", "init", "src", "clearStack", Argument.Delimiters.none, "execute", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "interpreter", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Interpreter;", "stackSizeWithExtra", "getStackSizeWithExtra", "()I", "getStackContent", Argument.Delimiters.none, "push", "value", "pushAll", "values", Argument.Delimiters.none, "pop", "setStack", "i", "merge", Argument.Delimiters.none, "frame", "executeBeforeInlineCallMarker", "saveStackAndClear", "executeAfterInlineCallMarker", "executeRestoreStackInTryCatch", "executeSaveStackBeforeTry", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public final class FixStackFrame extends Frame<FixStackValue> {
        private final Stack<FixStackValue> extraStack;

        public FixStackFrame(int i, int i2) {
            super(i, i2);
            this.extraStack = new Stack<>();
        }

        private final void executeAfterInlineCallMarker(AbstractInsnNode insn) {
            AbstractInsnNode abstractInsnNode = FixStackAnalyzer.this.getContext().getOpeningInlineMethodMarker().get(insn);
            if (getStackSize() <= 0) {
                List list = (List) FixStackAnalyzer.this.spilledStacks.get(abstractInsnNode);
                list.getClass();
                pushAll(list);
            } else {
                FixStackValue fixStackValueM70pop = m70pop();
                clearStack();
                List list2 = (List) FixStackAnalyzer.this.spilledStacks.get(abstractInsnNode);
                list2.getClass();
                pushAll(list2);
                push(fixStackValueM70pop);
            }
        }

        private final void executeBeforeInlineCallMarker(AbstractInsnNode insn) {
            saveStackAndClear(insn);
        }

        private final void executeRestoreStackInTryCatch(AbstractInsnNode insn) {
            AbstractInsnNode abstractInsnNode = FixStackAnalyzer.this.getContext().getSaveStackMarkerForRestoreMarker().get(insn);
            HashMap map = FixStackAnalyzer.this.spilledStacks;
            abstractInsnNode.getClass();
            Object obj = map.get(abstractInsnNode);
            if (obj != null) {
                pushAll((List) obj);
            } else {
                xzd.a(InlineCodegenUtilsKt.getInsnText(insn), ": Restore stack is unavailable for ", InlineCodegenUtilsKt.getInsnText(abstractInsnNode));
            }
        }

        private final void executeSaveStackBeforeTry(AbstractInsnNode insn) {
            saveStackAndClear(insn);
        }

        private final void pushAll(Collection<? extends FixStackValue> values) {
            Iterator<T> it = values.iterator();
            while (it.hasNext()) {
                push((FixStackValue) it.next());
            }
        }

        private final void saveStackAndClear(AbstractInsnNode insn) {
            FixStackAnalyzer.this.spilledStacks.put(insn, getStackContent());
            clearStack();
        }

        public void clearStack() {
            this.extraStack.clear();
            super.clearStack();
        }

        public void execute(AbstractInsnNode insn, Interpreter<FixStackValue> interpreter) {
            insn.getClass();
            interpreter.getClass();
            if (PseudoInsn.SAVE_STACK_BEFORE_TRY.isa(insn)) {
                executeSaveStackBeforeTry(insn);
            } else if (PseudoInsn.RESTORE_STACK_IN_TRY_CATCH.isa(insn)) {
                executeRestoreStackInTryCatch(insn);
            } else if (InlineCodegenUtilsKt.isBeforeInlineMarker(insn)) {
                executeBeforeInlineCallMarker(insn);
            } else if (InlineCodegenUtilsKt.isAfterInlineMarker(insn)) {
                executeAfterInlineCallMarker(insn);
            } else if (insn.getOpcode() == 177) {
                return;
            }
            super.execute(insn, interpreter);
        }

        public final List<FixStackValue> getStackContent() {
            ArrayList arrayList = new ArrayList();
            int stackSize = super.getStackSize();
            for (int i = 0; i < stackSize; i++) {
                arrayList.add(super.getStack(i));
            }
            arrayList.addAll(this.extraStack);
            return arrayList;
        }

        public final int getStackSizeWithExtra() {
            return super.getStackSize() + this.extraStack.size();
        }

        public Frame<FixStackValue> init(Frame<? extends FixStackValue> src) {
            src.getClass();
            this.extraStack.clear();
            this.extraStack.addAll(((FixStackFrame) src).extraStack);
            Frame<FixStackValue> frameInit = super.init(src);
            frameInit.getClass();
            return frameInit;
        }

        public boolean merge(Frame<? extends FixStackValue> frame, Interpreter<FixStackValue> interpreter) {
            frame.getClass();
            interpreter.getClass();
            throw new UnsupportedOperationException("Stack normalization should not merge frames");
        }

        /* JADX INFO: renamed from: pop, reason: merged with bridge method [inline-methods] */
        public FixStackValue m70pop() {
            if (this.extraStack.isEmpty()) {
                Value valuePop = super.pop();
                valuePop.getClass();
                return (FixStackValue) valuePop;
            }
            Object objPop = this.extraStack.pop();
            objPop.getClass();
            return (FixStackValue) objPop;
        }

        public void push(FixStackValue value) {
            value.getClass();
            if (super.getStackSize() < getMaxStackSize()) {
                super.push(value);
                return;
            }
            this.extraStack.add(value);
            FixStackAnalyzer fixStackAnalyzer = FixStackAnalyzer.this;
            fixStackAnalyzer.maxExtraStackSize = Math.max(fixStackAnalyzer.getMaxExtraStackSize(), this.extraStack.size());
        }

        public void setStack(int i, FixStackValue value) {
            value.getClass();
            if (i < super.getMaxStackSize()) {
                super.setStack(i, value);
            } else {
                this.extraStack.set(i - getMaxStackSize(), value);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [org.jetbrains.kotlin.codegen.optimization.fixStack.FixStackAnalyzer$analyzer$1] */
    public FixStackAnalyzer(final String str, final MethodNode methodNode, FixStackContext fixStackContext, boolean z) {
        str.getClass();
        methodNode.getClass();
        fixStackContext.getClass();
        this.context = fixStackContext;
        this.skipBreakContinueGotoEdges = z;
        final FixStackInterpreter fixStackInterpreter = new FixStackInterpreter();
        final Function2 function2 = new Function2() { // from class: sg5
            public final Object invoke(Object obj, Object obj2) {
                return FixStackAnalyzer.a(this.b, ((Integer) obj).intValue(), ((Integer) obj2).intValue());
            }
        };
        this.analyzer = new FastStackAnalyzer<FixStackValue, FixStackFrame>(str, methodNode, fixStackInterpreter, function2) { // from class: org.jetbrains.kotlin.codegen.optimization.fixStack.FixStackAnalyzer$analyzer$1
            @Override // org.jetbrains.kotlin.codegen.optimization.common.FastAnalyzer
            public boolean visitControlFlowEdge(AbstractInsnNode insnNode, int successor) {
                insnNode.getClass();
                return (this.skipBreakContinueGotoEdges && (insnNode instanceof JumpInsnNode) && this.getContext().getBreakContinueGotoNodes().contains(insnNode)) ? false : true;
            }
        };
        this.loopEntryPointMarkers = new HashMap<>();
        this.spilledStacks = new HashMap<>();
    }

    public static FixStackFrame a(FixStackAnalyzer fixStackAnalyzer, int i, int i2) {
        return fixStackAnalyzer.new FixStackFrame(i, i2);
    }

    private final void recordLoopEntryPointMarkers() {
        Iterator<AbstractInsnNode> it = this.context.getFakeAlwaysFalseIfeqMarkers().iterator();
        it.getClass();
        while (it.hasNext()) {
            AbstractInsnNode next = it.next();
            next.getClass();
            AbstractInsnNode abstractInsnNode = next;
            JumpInsnNode next2 = abstractInsnNode.getNext();
            if (next2 instanceof JumpInsnNode) {
                HashMap<LabelNode, SmartList<AbstractInsnNode>> map = this.loopEntryPointMarkers;
                LabelNode labelNode = next2.label;
                SmartList<AbstractInsnNode> smartList = map.get(labelNode);
                if (smartList == null) {
                    smartList = new SmartList<>();
                    map.put(labelNode, smartList);
                }
                smartList.add(abstractInsnNode);
            }
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    public final void analyze() throws AnalyzerException {
        recordLoopEntryPointMarkers();
        analyze();
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final List<FixStackValue> getActualStack(AbstractInsnNode location) {
        location.getClass();
        FixStackFrame fixStackFrame = (FixStackFrame) getFrame(location);
        if (fixStackFrame != null) {
            return fixStackFrame.getStackContent();
        }
        return null;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int getActualStackSize(AbstractInsnNode location) {
        location.getClass();
        FixStackFrame fixStackFrame = (FixStackFrame) getFrame(location);
        if (fixStackFrame != null) {
            return fixStackFrame.getStackSizeWithExtra();
        }
        return -1;
    }

    public final FixStackContext getContext() {
        return this.context;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final int getExpectedStackSize(AbstractInsnNode location) {
        location.getClass();
        List listListOf = (SmartList) this.loopEntryPointMarkers.get(location);
        if (listListOf == null) {
            listListOf = CollectionsKt.listOf(location);
        }
        Iterator it = listListOf.iterator();
        while (it.hasNext()) {
            FixStackFrame fixStackFrame = (FixStackFrame) getFrame((AbstractInsnNode) it.next());
            if (fixStackFrame != null) {
                return fixStackFrame.getStackSizeWithExtra();
            }
        }
        return -1;
    }

    public final int getMaxExtraStackSize() {
        return this.maxExtraStackSize;
    }

    public final List<FixStackValue> getStackToSpill(AbstractInsnNode location) {
        location.getClass();
        return this.spilledStacks.get(location);
    }
}
