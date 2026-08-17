package org.jetbrains.kotlin.codegen.optimization.common;

import java.util.Iterator;
import java.util.List;
import java.util.Set;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Value;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010 \n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\b&\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0016J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0004J\u0018\u0010\n\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\tH\u0004J\u0018\u0010\u000b\u001a\u00020\t2\u0006\u0010\u0006\u001a\u00020\t2\u0006\u0010\u0007\u001a\u00020\tH\u0014J\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0005H\u0002J\u001c\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u00132\b\u0010\u0015\u001a\u0004\u0018\u00010\u0013H\u0004J\u001a\u0010\u0016\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0005H\u0016J\u001a\u0010\u001a\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u0005H\u0016J\"\u0010\u001b\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u0005H\u0016J*\u0010\u001e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u001c\u001a\u00020\u00052\u0006\u0010\u001d\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u0005H\u0016J \u0010 \u001a\u0004\u0018\u00010\u00052\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00050\"H\u0016J\u001e\u0010#\u001a\u00020$2\u0006\u0010\u0017\u001a\u00020\u00182\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00050\"H\u0014J \u0010%\u001a\u00020$2\u0006\u0010\u0019\u001a\u00020\t2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010&\u001a\u00020'H$R\u001e\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\r*\u00020\u00058BX\u0082\u0004¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011¨\u0006("}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/common/ReferenceTrackingInterpreter;", "Lorg/jetbrains/kotlin/codegen/optimization/common/OptimizationBasicInterpreter;", "<init>", "()V", "merge", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "v", "w", "createTaintedValue", "Lorg/jetbrains/kotlin/codegen/optimization/common/TrackedReferenceValue;", "createMergedValue", "createPossiblyMergedValue", "mergeDescriptors", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/optimization/common/ReferenceValueDescriptor;", "referenceValueDescriptors", "getReferenceValueDescriptors", "(Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;)Ljava/util/Set;", "getMergedValueType", "Lorg/jetbrains/org/objectweb/asm/Type;", "type1", "type2", "copyOperation", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "value", "unaryOperation", "binaryOperation", "value1", "value2", "ternaryOperation", "value3", "naryOperation", "values", Argument.Delimiters.none, "checkRefValuesUsages", Argument.Delimiters.none, "processRefValueUsage", "position", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public abstract class ReferenceTrackingInterpreter extends OptimizationBasicInterpreter {
    private final Set<ReferenceValueDescriptor> getReferenceValueDescriptors(BasicValue basicValue) {
        return basicValue instanceof TrackedReferenceValue ? ((TrackedReferenceValue) basicValue).getDescriptors() : SetsKt.emptySet();
    }

    private final Set<ReferenceValueDescriptor> mergeDescriptors(BasicValue v, BasicValue w) {
        return SetsKt.plus(getReferenceValueDescriptors(v), getReferenceValueDescriptors(w));
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue binaryOperation(AbstractInsnNode insn, BasicValue value1, BasicValue value2) {
        insn.getClass();
        value1.getClass();
        value2.getClass();
        checkRefValuesUsages(insn, CollectionsKt.listOf(new BasicValue[]{value1, value2}));
        return super.binaryOperation(insn, value1, value2);
    }

    public void checkRefValuesUsages(AbstractInsnNode insn, List<? extends BasicValue> values) {
        insn.getClass();
        values.getClass();
        List<? extends BasicValue> list = values;
        for (BasicValue basicValue : list) {
            if (basicValue instanceof TaintedTrackedReferenceValue) {
                Iterator<T> it = ((TaintedTrackedReferenceValue) basicValue).getDescriptors().iterator();
                while (it.hasNext()) {
                    ((ReferenceValueDescriptor) it.next()).onUseAsTainted();
                }
            }
        }
        int i = 0;
        for (Object obj : list) {
            int i2 = i + 1;
            if (i < 0) {
                CollectionsKt.throwIndexOverflow();
            }
            BasicValue basicValue2 = (BasicValue) obj;
            if (basicValue2 instanceof TrackedReferenceValue) {
                processRefValueUsage((TrackedReferenceValue) basicValue2, insn, i);
            }
            i = i2;
        }
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue copyOperation(AbstractInsnNode insn, BasicValue value) {
        insn.getClass();
        value.getClass();
        if (!(value instanceof TrackedReferenceValue)) {
            return super.copyOperation(insn, value);
        }
        checkRefValuesUsages(insn, CollectionsKt.listOf(value));
        return value;
    }

    public final TrackedReferenceValue createMergedValue(TrackedReferenceValue v, TrackedReferenceValue w) {
        v.getClass();
        w.getClass();
        return ((v instanceof TaintedTrackedReferenceValue) || (w instanceof TaintedTrackedReferenceValue)) ? createTaintedValue(v, w) : new MergedTrackedReferenceValue(getMergedValueType(v.getType(), w.getType()), mergeDescriptors(v, w));
    }

    public TrackedReferenceValue createPossiblyMergedValue(TrackedReferenceValue v, TrackedReferenceValue w) {
        v.getClass();
        w.getClass();
        return createTaintedValue(v, w);
    }

    public final TrackedReferenceValue createTaintedValue(BasicValue v, BasicValue w) {
        v.getClass();
        w.getClass();
        Type mergedValueType = getMergedValueType(v.getType(), w.getType());
        Set<ReferenceValueDescriptor> setMergeDescriptors = mergeDescriptors(v, w);
        setMergeDescriptors.isEmpty();
        return new TaintedTrackedReferenceValue(mergedValueType, setMergeDescriptors);
    }

    public final Type getMergedValueType(Type type1, Type type2) {
        if (type1 == null || type2 == null) {
            Type type = AsmTypes.OBJECT_TYPE;
            type.getClass();
            return type;
        }
        if (Intrinsics.areEqual(type1, type2)) {
            return type1;
        }
        Type type3 = AsmTypes.OBJECT_TYPE;
        type3.getClass();
        return type3;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue merge(BasicValue v, BasicValue w) {
        v.getClass();
        w.getClass();
        if ((v instanceof ProperTrackedReferenceValue) && (w instanceof ProperTrackedReferenceValue)) {
            return Intrinsics.areEqual(((ProperTrackedReferenceValue) v).getDescriptor(), ((ProperTrackedReferenceValue) w).getDescriptor()) ? (TrackedReferenceValue) v : createTaintedValue(v, w);
        }
        if (v instanceof TrackedReferenceValue) {
            return w instanceof TrackedReferenceValue ? createPossiblyMergedValue((TrackedReferenceValue) v, (TrackedReferenceValue) w) : createTaintedValue(v, w);
        }
        if (w instanceof TrackedReferenceValue) {
            return createTaintedValue(v, w);
        }
        BasicValue basicValueMerge = super.merge(v, w);
        basicValueMerge.getClass();
        return basicValueMerge;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue naryOperation(AbstractInsnNode insn, List<? extends BasicValue> values) {
        insn.getClass();
        values.getClass();
        checkRefValuesUsages(insn, values);
        return super.naryOperation(insn, values);
    }

    public abstract void processRefValueUsage(TrackedReferenceValue value, AbstractInsnNode insn, int position);

    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue ternaryOperation(AbstractInsnNode insn, BasicValue value1, BasicValue value2, BasicValue value3) {
        insn.getClass();
        value1.getClass();
        value2.getClass();
        value3.getClass();
        checkRefValuesUsages(insn, CollectionsKt.listOf(new BasicValue[]{value1, value2, value3}));
        return super.ternaryOperation(insn, value1, value2, value3);
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue unaryOperation(AbstractInsnNode insn, BasicValue value) {
        insn.getClass();
        value.getClass();
        checkRefValuesUsages(insn, CollectionsKt.listOf(value));
        return super.unaryOperation(insn, value);
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    /* JADX INFO: renamed from: naryOperation */
    public /* bridge */ /* synthetic */ Value mo53naryOperation(AbstractInsnNode abstractInsnNode, List list) {
        return naryOperation(abstractInsnNode, (List<? extends BasicValue>) list);
    }
}
