package org.jetbrains.kotlin.codegen.optimization.nullCheck;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeInliner;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeInlinerKt;
import org.jetbrains.kotlin.codegen.optimization.boxing.BoxingInterpreterKt;
import org.jetbrains.kotlin.codegen.optimization.boxing.PopBackwardPropagationTransformerKt;
import org.jetbrains.kotlin.codegen.optimization.boxing.ProgressionIteratorBasicValue;
import org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter;
import org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue;
import org.jetbrains.kotlin.codegen.pseudoInsns.PseudoInsn;
import org.jetbrains.kotlin.codegen.pseudoInsns.PseudoInsnsKt;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Value;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0012\u0010\u0006\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\u000e\u0010\n\u001a\u00020\u000b*\u0004\u0018\u00010\fH\u0002J\u001c\u0010\r\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\b\u0010\u000e\u001a\u0004\u0018\u00010\u0007H\u0016J\f\u0010\u000f\u001a\u00020\u000b*\u00020\tH\u0002J\f\u0010\u0010\u001a\u00020\u000b*\u00020\tH\u0002J \u0010\u0011\u001a\u0004\u0018\u00010\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0013H\u0016J\u0018\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0007H\u0016J\u0018\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0015\u001a\u00020\u00182\u0006\u0010\u0016\u001a\u00020\u0018H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/nullCheck/NullabilityInterpreter;", "Lorg/jetbrains/kotlin/codegen/optimization/common/OptimizationBasicInterpreter;", "generationState", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "<init>", "(Lorg/jetbrains/kotlin/codegen/state/GenerationState;)V", "newOperation", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "isReferenceType", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/Type;", "unaryOperation", "value", "isTypeOf", "isReifiedSafeAs", "naryOperation", "values", Argument.Delimiters.none, "merge", "v", "w", "mergeNotNullValuesOfSameKind", "Lorg/jetbrains/kotlin/codegen/optimization/common/StrictBasicValue;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class NullabilityInterpreter extends OptimizationBasicInterpreter {
    private final GenerationState generationState;

    public NullabilityInterpreter(GenerationState generationState) {
        generationState.getClass();
        this.generationState = generationState;
    }

    private final boolean isReferenceType(Type type) {
        Integer numValueOf = type != null ? Integer.valueOf(type.getSort()) : null;
        if (numValueOf != null && numValueOf.intValue() == 10) {
            return true;
        }
        return numValueOf != null && numValueOf.intValue() == 9;
    }

    private final boolean isReifiedSafeAs(AbstractInsnNode abstractInsnNode) {
        MethodInsnNode previous = abstractInsnNode.getPrevious();
        MethodInsnNode methodInsnNode = previous instanceof MethodInsnNode ? previous : null;
        return methodInsnNode != null && ReifiedTypeInliner.INSTANCE.isOperationReifiedMarker(methodInsnNode) && ReifiedTypeInlinerKt.getOperationKind(methodInsnNode) == ReifiedTypeInliner.OperationKind.SAFE_AS;
    }

    private final boolean isTypeOf(AbstractInsnNode abstractInsnNode) {
        MethodInsnNode previous = abstractInsnNode.getPrevious();
        MethodInsnNode methodInsnNode = previous instanceof MethodInsnNode ? previous : null;
        if (methodInsnNode == null) {
            return false;
        }
        ReifiedTypeInliner.Companion companion = ReifiedTypeInliner.INSTANCE;
        AbstractInsnNode previous2 = abstractInsnNode.getPrevious();
        previous2.getClass();
        return companion.isOperationReifiedMarker(previous2) && ReifiedTypeInlinerKt.getOperationKind(methodInsnNode) == ReifiedTypeInliner.OperationKind.TYPE_OF;
    }

    private final StrictBasicValue mergeNotNullValuesOfSameKind(StrictBasicValue v, StrictBasicValue w) {
        return Intrinsics.areEqual(v.getType(), w.getType()) ? v : NotNullBasicValue.INSTANCE.getNOT_NULL_REFERENCE_VALUE();
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue merge(BasicValue v, BasicValue w) {
        BasicValue basicValueMergeNotNullValuesOfSameKind;
        BasicValue not_null_reference_value;
        v.getClass();
        w.getClass();
        NullBasicValue nullBasicValue = NullBasicValue.INSTANCE;
        if (v == nullBasicValue) {
            return w == nullBasicValue ? nullBasicValue : StrictBasicValue.REFERENCE_VALUE;
        }
        if (w == nullBasicValue) {
            return StrictBasicValue.REFERENCE_VALUE;
        }
        if (v instanceof ProgressionIteratorBasicValue) {
            if (w instanceof ProgressionIteratorBasicValue) {
                not_null_reference_value = mergeNotNullValuesOfSameKind((StrictBasicValue) v, (StrictBasicValue) w);
            } else {
                not_null_reference_value = w instanceof NotNullBasicValue ? NotNullBasicValue.INSTANCE.getNOT_NULL_REFERENCE_VALUE() : super.merge(v, w);
            }
            not_null_reference_value.getClass();
            return not_null_reference_value;
        }
        if (!(v instanceof NotNullBasicValue)) {
            BasicValue basicValueMerge = super.merge(v, w);
            basicValueMerge.getClass();
            return basicValueMerge;
        }
        if (w instanceof ProgressionIteratorBasicValue) {
            basicValueMergeNotNullValuesOfSameKind = NotNullBasicValue.INSTANCE.getNOT_NULL_REFERENCE_VALUE();
        } else {
            basicValueMergeNotNullValuesOfSameKind = w instanceof NotNullBasicValue ? mergeNotNullValuesOfSameKind((StrictBasicValue) v, (StrictBasicValue) w) : super.merge(v, w);
        }
        basicValueMergeNotNullValuesOfSameKind.getClass();
        return basicValueMergeNotNullValuesOfSameKind;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue naryOperation(AbstractInsnNode insn, List<? extends BasicValue> values) throws AnalyzerException {
        insn.getClass();
        values.getClass();
        BasicValue basicValueNaryOperation = super.naryOperation(insn, values);
        Type type = basicValueNaryOperation != null ? basicValueNaryOperation.getType() : null;
        if (BoxingInterpreterKt.isBoxing(insn, this.generationState)) {
            return new NotNullBasicValue(type);
        }
        if (!BoxingInterpreterKt.isIteratorMethodCallOfProgression(insn, values)) {
            if (BoxingInterpreterKt.isNextMethodCallOfProgressionIterator(insn, values)) {
                return new NotNullBasicValue(type);
            }
            return PseudoInsnsKt.isPseudo(insn, PseudoInsn.AS_NOT_NULL) ? new NotNullBasicValue(values.get(0).getType()) : basicValueNaryOperation;
        }
        ProgressionIteratorBasicValue.Companion companion = ProgressionIteratorBasicValue.INSTANCE;
        Type type2 = values.get(0).getType();
        type2.getClass();
        return companion.byProgressionClassType(insn, type2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    /* JADX INFO: renamed from: newOperation */
    public BasicValue mo59newOperation(AbstractInsnNode insn) throws AnalyzerException {
        insn.getClass();
        BasicValue basicValueMo59newOperation = super.mo59newOperation(insn);
        Type type = basicValueMo59newOperation != null ? basicValueMo59newOperation.getType() : null;
        if (insn.getOpcode() == 1 && !isTypeOf(insn)) {
            return NullBasicValue.INSTANCE;
        }
        if (insn.getOpcode() == 187) {
            return new NotNullBasicValue(type);
        }
        return ((insn.getOpcode() == 18 && isReferenceType(type)) || PopBackwardPropagationTransformerKt.isUnitInstance(insn)) ? new NotNullBasicValue(type) : basicValueMo59newOperation;
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue unaryOperation(AbstractInsnNode insn, BasicValue value) throws AnalyzerException {
        insn.getClass();
        BasicValue basicValueUnaryOperation = super.unaryOperation(insn, value);
        Type type = basicValueUnaryOperation != null ? basicValueUnaryOperation.getType() : null;
        int opcode = insn.getOpcode();
        if (opcode == 188 || opcode == 189) {
            return new NotNullBasicValue(type);
        }
        if (opcode != 192) {
            return basicValueUnaryOperation;
        }
        return isReifiedSafeAs(insn) ? new StrictBasicValue(type) : value;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    /* JADX INFO: renamed from: naryOperation */
    public /* bridge */ /* synthetic */ Value mo53naryOperation(AbstractInsnNode abstractInsnNode, List list) {
        return naryOperation(abstractInsnNode, (List<? extends BasicValue>) list);
    }
}
