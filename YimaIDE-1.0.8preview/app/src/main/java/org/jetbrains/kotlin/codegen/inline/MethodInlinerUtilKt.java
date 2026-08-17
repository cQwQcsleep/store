package org.jetbrains.kotlin.codegen.inline;

import java.util.Collection;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.InsnSequence;
import org.jetbrains.kotlin.codegen.inline.MethodInlinerUtilKt;
import org.jetbrains.kotlin.codegen.optimization.common.FastMethodAnalyzer;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.optimization.nullCheck.RedundantNullCheckMethodTransformerKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicInterpreter;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;
import org.jetbrains.org.objectweb.asm.tree.analysis.Interpreter;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000H\n\u0000\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u001e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u001a\u0018\u0010\u0000\u001a\u00020\u0001*\u00020\u00022\f\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0006\u001a\u0010\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\b0\u0004*\u00020\u0002\u001a\f\u0010\t\u001a\u0004\u0018\u00010\u0005*\u00020\u0005\u001a\f\u0010\u000f\u001a\u00020\u0010*\u00020\u0005H\u0000\u001a-\u0010\u0011\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0004\u0012\u00020\f\u0018\u00010\u00130\u00122\u0006\u0010\u0014\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0016H\u0000¢\u0006\u0002\u0010\u0017\"\u0019\u0010\n\u001a\u0004\u0018\u00010\u000b*\u0004\u0018\u00010\f8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000e¨\u0006\u0018"}, d2 = {"remove", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "instructions", "Lkotlin/sequences/Sequence;", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", Argument.Delimiters.none, "findCapturedFieldAssignmentInstructions", "Lorg/jetbrains/org/objectweb/asm/tree/FieldInsnNode;", "getNextMeaningful", "functionalArgument", "Lorg/jetbrains/kotlin/codegen/inline/FunctionalArgument;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "getFunctionalArgument", "(Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;)Lorg/jetbrains/kotlin/codegen/inline/FunctionalArgument;", "isAloadBeforeCheckParameterIsNotNull", Argument.Delimiters.none, "analyzeMethodNodeWithInterpreter", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "node", "interpreter", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicInterpreter;", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicInterpreter;)[Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class MethodInlinerUtilKt {

    @Metadata(d1 = {"\u0000)\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000*\u0001\u0000\b\n\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\u0006\u0010\u0003\u001a\u00020\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0004¢\u0006\u0004\b\u0006\u0010\u0007J\u001e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00020\rH\u0016¨\u0006\u000e"}, d2 = {"org/jetbrains/kotlin/codegen/inline/MethodInlinerUtilKt$analyzeMethodNodeWithInterpreter$BasicValueFrame", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "nLocals", Argument.Delimiters.none, "nStack", "<init>", "(II)V", "execute", Argument.Delimiters.none, "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "interpreter", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Interpreter;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class BasicValueFrame extends Frame<BasicValue> {
        public BasicValueFrame(int i, int i2) {
            super(i, i2);
        }

        public void execute(AbstractInsnNode insn, Interpreter<BasicValue> interpreter) throws AnalyzerException {
            insn.getClass();
            interpreter.getClass();
            if (insn.getOpcode() == 177) {
                return;
            }
            super.execute(insn, interpreter);
        }
    }

    public static boolean a(FieldInsnNode fieldInsnNode) {
        fieldInsnNode.getClass();
        AbstractInsnNode previous = fieldInsnNode.getPrevious();
        AbstractInsnNode previous2 = previous != null ? previous.getPrevious() : null;
        VarInsnNode varInsnNode = previous2 instanceof VarInsnNode ? (VarInsnNode) previous2 : null;
        if (fieldInsnNode.getOpcode() != 181) {
            return false;
        }
        String str = fieldInsnNode.name;
        str.getClass();
        return InlineCodegenUtilsKt.isCapturedFieldName(str) && (fieldInsnNode.getPrevious() instanceof VarInsnNode) && varInsnNode != null && varInsnNode.var == 0;
    }

    /* JADX WARN: Type inference failed for: r6v3, types: [org.jetbrains.org.objectweb.asm.tree.analysis.Frame<org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue>[], org.jetbrains.org.objectweb.asm.tree.analysis.Frame[]] */
    public static final Frame<BasicValue>[] analyzeMethodNodeWithInterpreter(MethodNode methodNode, BasicInterpreter basicInterpreter) {
        methodNode.getClass();
        basicInterpreter.getClass();
        try {
            return new FastMethodAnalyzer("fake", methodNode, basicInterpreter, true, new Function2() { // from class: r1a
                public final Object invoke(Object obj, Object obj2) {
                    return MethodInlinerUtilKt.b(((Integer) obj).intValue(), ((Integer) obj2).intValue());
                }
            }).analyze();
        } catch (AnalyzerException e) {
            rc6.a(e);
            return null;
        }
    }

    public static Frame b(int i, int i2) {
        return new BasicValueFrame(i, i2);
    }

    public static final Sequence<FieldInsnNode> findCapturedFieldAssignmentInstructions(MethodNode methodNode) {
        methodNode.getClass();
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        Sequence sequenceFilter = SequencesKt.filter(new InsnSequence(insnList), new Function1<Object, Boolean>() { // from class: org.jetbrains.kotlin.codegen.inline.MethodInlinerUtilKt$findCapturedFieldAssignmentInstructions$$inlined$filterIsInstance$1
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m66invoke(Object obj) {
                return Boolean.valueOf(obj instanceof FieldInsnNode);
            }
        });
        sequenceFilter.getClass();
        return SequencesKt.filter(sequenceFilter, new Function1() { // from class: s1a
            public final Object invoke(Object obj) {
                return Boolean.valueOf(MethodInlinerUtilKt.a((FieldInsnNode) obj));
            }
        });
    }

    public static final FunctionalArgument getFunctionalArgument(BasicValue basicValue) {
        FunctionalArgumentValue functionalArgumentValue = basicValue instanceof FunctionalArgumentValue ? (FunctionalArgumentValue) basicValue : null;
        if (functionalArgumentValue != null) {
            return functionalArgumentValue.getFunctionalArgument();
        }
        return null;
    }

    public static final AbstractInsnNode getNextMeaningful(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        AbstractInsnNode next = abstractInsnNode.getNext();
        while (next != null && !UtilKt.isMeaningful(next)) {
            next = next.getNext();
        }
        return next;
    }

    public static final boolean isAloadBeforeCheckParameterIsNotNull(AbstractInsnNode abstractInsnNode) {
        AbstractInsnNode next;
        AbstractInsnNode next2;
        AbstractInsnNode next3;
        abstractInsnNode.getClass();
        return abstractInsnNode.getOpcode() == 25 && (next = abstractInsnNode.getNext()) != null && next.getOpcode() == 18 && (next2 = abstractInsnNode.getNext()) != null && (next3 = next2.getNext()) != null && RedundantNullCheckMethodTransformerKt.isCheckParameterIsNotNull(next3);
    }

    public static final void remove(MethodNode methodNode, Collection<? extends AbstractInsnNode> collection) {
        methodNode.getClass();
        collection.getClass();
        Iterator<T> it = collection.iterator();
        while (it.hasNext()) {
            methodNode.instructions.remove((AbstractInsnNode) it.next());
        }
    }

    public static final void remove(MethodNode methodNode, Sequence<? extends AbstractInsnNode> sequence) {
        methodNode.getClass();
        sequence.getClass();
        Iterator it = sequence.iterator();
        while (it.hasNext()) {
            methodNode.instructions.remove((AbstractInsnNode) it.next());
        }
    }
}
