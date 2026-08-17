package org.jetbrains.kotlin.codegen.coroutines;

import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.InsnSequenceKt;
import org.jetbrains.kotlin.codegen.optimization.boxing.PopBackwardPropagationTransformerKt;
import org.jetbrains.kotlin.codegen.optimization.common.FastMethodAnalyzer;
import org.jetbrains.kotlin.codegen.optimization.fixStack.StackTransformationUtilsKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicInterpreter;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;
import org.jetbrains.org.objectweb.asm.tree.analysis.Value;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000^\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\"\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010#\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010%\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0010 \n\u0002\b\u0002\b\u0002\u0018\u00002\u00020\u0001B\u0015\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003¢\u0006\u0004\b\u0005\u0010\u0006J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0002J\u0018\u0010\u0014\u001a\u00020\u00112\u0006\u0010\u0015\u001a\u00020\t2\u0006\u0010\u0012\u001a\u00020\u0016H\u0002J)\u0010\u0017\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010\u00190\u00182\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d¢\u0006\u0002\u0010\u001eJ\u0012\u0010\u001f\u001a\u00020\u00132\b\u0010 \u001a\u0004\u0018\u00010\tH\u0016J\u001c\u0010!\u001a\u0004\u0018\u00010\u00132\u0006\u0010 \u001a\u00020\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J\u001c\u0010\"\u001a\u0004\u0018\u00010\u00132\u0006\u0010 \u001a\u00020\t2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0016J&\u0010#\u001a\u0004\u0018\u00010\u00132\u0006\u0010 \u001a\u00020\t2\b\u0010$\u001a\u0004\u0018\u00010\u00132\b\u0010%\u001a\u0004\u0018\u00010\u0013H\u0016J0\u0010&\u001a\u0004\u0018\u00010\u00132\u0006\u0010 \u001a\u00020\t2\b\u0010$\u001a\u0004\u0018\u00010\u00132\b\u0010%\u001a\u0004\u0018\u00010\u00132\b\u0010'\u001a\u0004\u0018\u00010\u0013H\u0016J\"\u0010(\u001a\u0004\u0018\u00010\u00132\u0006\u0010 \u001a\u00020\t2\u000e\u0010)\u001a\n\u0012\u0004\u0012\u00020\u0013\u0018\u00010*H\u0016J\u001e\u0010+\u001a\u0004\u0018\u00010\u00132\b\u0010$\u001a\u0004\u0018\u00010\u00132\b\u0010%\u001a\u0004\u0018\u00010\u0013H\u0016R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR#\u0010\f\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\n\u0012\b\u0012\u0004\u0012\u00020\t0\b0\r¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000f¨\u0006,"}, d2 = {"Lorg/jetbrains/kotlin/codegen/coroutines/UnitSourceInterpreter;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicInterpreter;", "localVariables", Argument.Delimiters.none, Argument.Delimiters.none, "<init>", "(Ljava/util/Set;)V", "unspillableUnitValues", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "getUnspillableUnitValues", "()Ljava/util/Set;", "unitUsageInformation", Argument.Delimiters.none, "getUnitUsageInformation", "()Ljava/util/Map;", "markUnspillable", Argument.Delimiters.none, "value", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "collectUnitUsage", "use", "Lorg/jetbrains/kotlin/codegen/coroutines/UnitValue;", "run", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "(Ljava/lang/String;Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;)[Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "newOperation", "insn", "copyOperation", "unaryOperation", "binaryOperation", "value1", "value2", "ternaryOperation", "value3", "naryOperation", "values", Argument.Delimiters.none, "merge", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
final class UnitSourceInterpreter extends BasicInterpreter {
    private final Set<Integer> localVariables;
    private final Map<AbstractInsnNode, Set<AbstractInsnNode>> unitUsageInformation;
    private final Set<AbstractInsnNode> unspillableUnitValues;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public UnitSourceInterpreter(Set<Integer> set) {
        super(589824);
        set.getClass();
        this.localVariables = set;
        this.unspillableUnitValues = new LinkedHashSet();
        this.unitUsageInformation = new LinkedHashMap();
    }

    private final void collectUnitUsage(AbstractInsnNode use, UnitValue value) {
        for (AbstractInsnNode abstractInsnNode : value.getInsns()) {
            if (!this.unspillableUnitValues.contains(abstractInsnNode)) {
                Map<AbstractInsnNode, Set<AbstractInsnNode>> map = this.unitUsageInformation;
                Set<AbstractInsnNode> linkedHashSet = map.get(abstractInsnNode);
                if (linkedHashSet == null) {
                    linkedHashSet = new LinkedHashSet<>();
                    map.put(abstractInsnNode, linkedHashSet);
                }
                linkedHashSet.add(use);
            }
        }
    }

    private final void markUnspillable(BasicValue value) {
        UnitValue unitValue = value instanceof UnitValue ? (UnitValue) value : null;
        if (unitValue != null) {
            CollectionsKt.addAll(this.unspillableUnitValues, unitValue.getInsns());
        }
    }

    public BasicValue binaryOperation(AbstractInsnNode insn, BasicValue value1, BasicValue value2) {
        insn.getClass();
        markUnspillable(value1);
        markUnspillable(value2);
        return super.binaryOperation(insn, value1, value2);
    }

    public BasicValue copyOperation(AbstractInsnNode insn, BasicValue value) {
        insn.getClass();
        if (value instanceof UnitValue) {
            if (insn instanceof VarInsnNode) {
                VarInsnNode varInsnNode = (VarInsnNode) insn;
                if (varInsnNode.getOpcode() == 58 && !this.localVariables.contains(Integer.valueOf(varInsnNode.var))) {
                    collectUnitUsage(insn, (UnitValue) value);
                    return value;
                }
            }
            CollectionsKt.addAll(this.unspillableUnitValues, ((UnitValue) value).getInsns());
        }
        return super.copyOperation(insn, value);
    }

    public final Map<AbstractInsnNode, Set<AbstractInsnNode>> getUnitUsageInformation() {
        return this.unitUsageInformation;
    }

    public final Set<AbstractInsnNode> getUnspillableUnitValues() {
        return this.unspillableUnitValues;
    }

    public BasicValue merge(BasicValue value1, BasicValue value2) {
        if (!(value1 instanceof UnitValue) || !(value2 instanceof UnitValue)) {
            markUnspillable(value1);
            markUnspillable(value2);
            return super.merge(value1, value2);
        }
        UnitValue unitValue = new UnitValue((Set<? extends AbstractInsnNode>) CollectionsKt.union(((UnitValue) value1).getInsns(), ((UnitValue) value2).getInsns()));
        Set<AbstractInsnNode> insns = unitValue.getInsns();
        if ((insns instanceof Collection) && insns.isEmpty()) {
            return unitValue;
        }
        Iterator<T> it = insns.iterator();
        while (it.hasNext()) {
            if (this.unspillableUnitValues.contains((AbstractInsnNode) it.next())) {
                markUnspillable(unitValue);
                break;
            }
        }
        return unitValue;
    }

    public BasicValue naryOperation(AbstractInsnNode insn, List<? extends BasicValue> values) {
        insn.getClass();
        if (values != null) {
            Iterator<T> it = values.iterator();
            while (it.hasNext()) {
                markUnspillable((BasicValue) it.next());
            }
        }
        return super.naryOperation(insn, values);
    }

    /* JADX INFO: renamed from: newOperation, reason: merged with bridge method [inline-methods] */
    public BasicValue m61newOperation(AbstractInsnNode insn) {
        if (insn != null && PopBackwardPropagationTransformerKt.isUnitInstance(insn)) {
            return new UnitValue(insn);
        }
        BasicValue basicValueNewOperation = super.newOperation(insn);
        basicValueNewOperation.getClass();
        return basicValueNewOperation;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    /* JADX WARN: Type inference failed for: r8v1, types: [java.lang.Object[], org.jetbrains.org.objectweb.asm.tree.analysis.Frame<org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue>[], org.jetbrains.org.objectweb.asm.tree.analysis.Frame[]] */
    public final Frame<BasicValue>[] run(String internalClassName, MethodNode methodNode) throws AnalyzerException {
        internalClassName.getClass();
        methodNode.getClass();
        ?? Analyze = new FastMethodAnalyzer(internalClassName, methodNode, this, false, null, 24, null).analyze();
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        for (Pair pair : SequencesKt.zip(InsnSequenceKt.asSequence(insnList), ArraysKt.asSequence((Object[]) Analyze))) {
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode) pair.component1();
            Frame frame = (Frame) pair.component2();
            if (frame != null && abstractInsnNode.getOpcode() == 87) {
                BasicValue pVar = StackTransformationUtilsKt.top(frame);
                if (pVar instanceof UnitValue) {
                    collectUnitUsage(abstractInsnNode, (UnitValue) pVar);
                }
            }
        }
        return Analyze;
    }

    public BasicValue ternaryOperation(AbstractInsnNode insn, BasicValue value1, BasicValue value2, BasicValue value3) {
        insn.getClass();
        markUnspillable(value1);
        markUnspillable(value2);
        markUnspillable(value3);
        return super.ternaryOperation(insn, value1, value2, value3);
    }

    public BasicValue unaryOperation(AbstractInsnNode insn, BasicValue value) {
        insn.getClass();
        markUnspillable(value);
        return super.unaryOperation(insn, value);
    }

    /* JADX INFO: renamed from: naryOperation, reason: collision with other method in class */
    public /* bridge */ /* synthetic */ Value m60naryOperation(AbstractInsnNode abstractInsnNode, List list) {
        return naryOperation(abstractInsnNode, (List<? extends BasicValue>) list);
    }
}
