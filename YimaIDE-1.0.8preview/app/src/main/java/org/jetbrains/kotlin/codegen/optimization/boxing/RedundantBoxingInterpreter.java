package org.jetbrains.kotlin.codegen.optimization.boxing;

import com.google.common.collect.ImmutableSet;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.CommonConfigurationKeysKt;
import org.jetbrains.kotlin.config.LanguageFeature;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TypeInsnNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u000b\b\u0000\u0018\u0000 (2\u00020\u0001:\u0001(B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u001a\u0010\f\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0016J\"\u0010\u0011\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\rH\u0016J*\u0010\u0014\u001a\u0004\u0018\u00010\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\r2\u0006\u0010\u0013\u001a\u00020\r2\u0006\u0010\u0015\u001a\u00020\rH\u0016J\u0018\u0010\u0016\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rH\u0016J\u0016\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\rJ\u0010\u0010\u001a\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u001bH\u0014J \u0010\u001c\u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u001b2\u0006\u0010\u001d\u001a\u00020\u001eH\u0014J \u0010\u001f\u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u001bH\u0014J \u0010 \u001a\u00020\u00182\u0006\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0012\u001a\u00020\u001b2\u0006\u0010\u0013\u001a\u00020\u001bH\u0014J\u0010\u0010!\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u001bH\u0014J\u0010\u0010\"\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u001bH\u0014J\u0018\u0010#\u001a\u00020\u00182\u0006\u0010$\u001a\u00020\u001b2\u0006\u0010%\u001a\u00020\u001bH\u0014J\u001a\u0010&\u001a\u00020\u00182\b\u0010\u0010\u001a\u0004\u0018\u00010\r2\u0006\u0010\u0019\u001a\u00020\u000fH\u0002J\u0010\u0010'\u001a\u00020\u00182\u0006\u0010\u0010\u001a\u00020\u001bH\u0002R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\b\u001a\u00020\t¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006)"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/boxing/RedundantBoxingInterpreter;", "Lorg/jetbrains/kotlin/codegen/optimization/boxing/BoxingInterpreter;", "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "generationState", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "<init>", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;Lorg/jetbrains/kotlin/codegen/state/GenerationState;)V", "candidatesBoxedValues", "Lorg/jetbrains/kotlin/codegen/optimization/boxing/RedundantBoxedValuesCollection;", "getCandidatesBoxedValues", "()Lorg/jetbrains/kotlin/codegen/optimization/boxing/RedundantBoxedValuesCollection;", "unaryOperation", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "value", "binaryOperation", "value1", "value2", "ternaryOperation", "value3", "copyOperation", "processPopInstruction", Argument.Delimiters.none, "insnNode", "onNewBoxedValue", "Lorg/jetbrains/kotlin/codegen/optimization/boxing/BoxedBasicValue;", "onUnboxing", "resultType", "Lorg/jetbrains/org/objectweb/asm/Type;", "onAreEqual", "onCompareTo", "onMethodCallWithBoxedValue", "onMergeFail", "onMergeSuccess", "v", "w", "processOperationWithBoxedValue", "markValueAsDirty", "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RedundantBoxingInterpreter extends BoxingInterpreter {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final ImmutableSet<Integer> PERMITTED_OPERATIONS_OPCODES = ImmutableSet.of(58, 25, 87, 89, 192, 193, new Integer[0]);
    private static final ImmutableSet<Integer> PRIMITIVE_TYPES_SORTS_WITH_WRAPPER_EXTENDS_NUMBER = ImmutableSet.of(3, 4, 5, 6, 7, 8, new Integer[0]);
    private final RedundantBoxedValuesCollection candidatesBoxedValues;
    private final GenerationState generationState;

    /* JADX WARN: Illegal instructions before constructor call */
    public RedundantBoxingInterpreter(MethodNode methodNode, GenerationState generationState) {
        methodNode.getClass();
        generationState.getClass();
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        super(insnList, generationState);
        this.generationState = generationState;
        this.candidatesBoxedValues = new RedundantBoxedValuesCollection();
    }

    private final void markValueAsDirty(BoxedBasicValue value) {
        this.candidatesBoxedValues.remove(value.getDescriptor());
    }

    private final void processOperationWithBoxedValue(BasicValue value, AbstractInsnNode insnNode) {
        if (value instanceof BoxedBasicValue) {
            checkUsedValue(value);
            if (PERMITTED_OPERATIONS_OPCODES.contains(Integer.valueOf(insnNode.getOpcode()))) {
                INSTANCE.addAssociatedInsn((BoxedBasicValue) value, insnNode);
            } else {
                markValueAsDirty((BoxedBasicValue) value);
            }
        }
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue binaryOperation(AbstractInsnNode insn, BasicValue value1, BasicValue value2) {
        insn.getClass();
        value1.getClass();
        value2.getClass();
        processOperationWithBoxedValue(value1, insn);
        processOperationWithBoxedValue(value2, insn);
        return super.binaryOperation(insn, value1, value2);
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue copyOperation(AbstractInsnNode insn, BasicValue value) throws AnalyzerException {
        insn.getClass();
        value.getClass();
        if ((value instanceof BoxedBasicValue) && insn.getOpcode() == 58) {
            ((BoxedBasicValue) value).getDescriptor().addVariableIndex(((VarInsnNode) insn).var);
        }
        processOperationWithBoxedValue(value, insn);
        BasicValue basicValueCopyOperation = super.copyOperation(insn, value);
        basicValueCopyOperation.getClass();
        return basicValueCopyOperation;
    }

    public final RedundantBoxedValuesCollection getCandidatesBoxedValues() {
        return this.candidatesBoxedValues;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.boxing.BoxingInterpreter
    public void onAreEqual(AbstractInsnNode insn, BoxedBasicValue value1, BoxedBasicValue value2) {
        insn.getClass();
        value1.getClass();
        value2.getClass();
        BoxedValueDescriptor descriptor = value1.getDescriptor();
        this.candidatesBoxedValues.merge(descriptor, value2.getDescriptor());
        descriptor.addInsn(insn);
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.boxing.BoxingInterpreter
    public void onCompareTo(AbstractInsnNode insn, BoxedBasicValue value1, BoxedBasicValue value2) {
        insn.getClass();
        value1.getClass();
        value2.getClass();
        BoxedValueDescriptor descriptor = value1.getDescriptor();
        this.candidatesBoxedValues.merge(descriptor, value2.getDescriptor());
        descriptor.addInsn(insn);
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.boxing.BoxingInterpreter
    public void onMergeFail(BoxedBasicValue value) {
        value.getClass();
        markValueAsDirty(value);
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.boxing.BoxingInterpreter
    public void onMergeSuccess(BoxedBasicValue v, BoxedBasicValue w) {
        v.getClass();
        w.getClass();
        this.candidatesBoxedValues.merge(v.getDescriptor(), w.getDescriptor());
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.boxing.BoxingInterpreter
    public void onMethodCallWithBoxedValue(BoxedBasicValue value) {
        value.getClass();
        markValueAsDirty(value);
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.boxing.BoxingInterpreter
    public void onNewBoxedValue(BoxedBasicValue value) {
        value.getClass();
        this.candidatesBoxedValues.add(value.getDescriptor());
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.boxing.BoxingInterpreter
    public void onUnboxing(AbstractInsnNode insn, BoxedBasicValue value, Type resultType) {
        insn.getClass();
        value.getClass();
        resultType.getClass();
        BoxedValueDescriptor descriptor = value.getDescriptor();
        if (Intrinsics.areEqual(descriptor.getUnboxTypeOrOtherwiseMethodReturnType(insn instanceof MethodInsnNode ? (MethodInsnNode) insn : null), resultType)) {
            INSTANCE.addAssociatedInsn(value, insn);
        } else {
            descriptor.addUnboxingWithCastTo(insn, resultType);
        }
    }

    public final void processPopInstruction(AbstractInsnNode insnNode, BasicValue value) {
        insnNode.getClass();
        value.getClass();
        processOperationWithBoxedValue(value, insnNode);
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue ternaryOperation(AbstractInsnNode insn, BasicValue value1, BasicValue value2, BasicValue value3) {
        insn.getClass();
        value1.getClass();
        value2.getClass();
        value3.getClass();
        processOperationWithBoxedValue(value3, insn);
        return super.ternaryOperation(insn, value1, value2, value3);
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.boxing.BoxingInterpreter, org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
    public BasicValue unaryOperation(AbstractInsnNode insn, BasicValue value) {
        insn.getClass();
        value.getClass();
        if ((insn.getOpcode() == 192 || insn.getOpcode() == 193) && (value instanceof BoxedBasicValue)) {
            boolean zSupportsFeature = CommonConfigurationKeysKt.getLanguageVersionSettings(this.generationState.getConfiguration()).supportsFeature(LanguageFeature.AvoidWrongOptimizationOfTypeOperatorsOnValueClasses);
            Companion companion = INSTANCE;
            BoxedBasicValue boxedBasicValue = (BoxedBasicValue) value;
            String str = ((TypeInsnNode) insn).desc;
            str.getClass();
            if (!companion.isSafeCast(boxedBasicValue, str, zSupportsFeature)) {
                markValueAsDirty(boxedBasicValue);
            }
        }
        processOperationWithBoxedValue(value, insn);
        return super.unaryOperation(insn, value);
    }

    @Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\nH\u0002J\u0018\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R2\u0010\u0004\u001a&\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006 \u0007*\u0012\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R2\u0010\b\u001a&\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006 \u0007*\u0012\u0012\f\u0012\n \u0007*\u0004\u0018\u00010\u00060\u0006\u0018\u00010\u00050\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/boxing/RedundantBoxingInterpreter$Companion;", Argument.Delimiters.none, "<init>", "()V", "PERMITTED_OPERATIONS_OPCODES", "Lcom/google/common/collect/ImmutableSet;", Argument.Delimiters.none, "kotlin.jvm.PlatformType", "PRIMITIVE_TYPES_SORTS_WITH_WRAPPER_EXTENDS_NUMBER", "isSafeCast", Argument.Delimiters.none, "value", "Lorg/jetbrains/kotlin/codegen/optimization/boxing/BoxedBasicValue;", "targetInternalName", Argument.Delimiters.none, "avoidWrongOptimization", "addAssociatedInsn", Argument.Delimiters.none, "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final void addAssociatedInsn(BoxedBasicValue value, AbstractInsnNode insn) {
            BoxedValueDescriptor descriptor = value.getDescriptor();
            if (descriptor.getIsSafeToRemove()) {
                descriptor.addInsn(insn);
            }
        }

        /* JADX INFO: Access modifiers changed from: private */
        public final boolean isSafeCast(BoxedBasicValue value, String targetInternalName, boolean avoidWrongOptimization) {
            Type type;
            if (Intrinsics.areEqual(targetInternalName, Type.getInternalName(Object.class))) {
                return true;
            }
            if (Intrinsics.areEqual(targetInternalName, Type.getInternalName(Number.class))) {
                return ((value.getDescriptor().getIsValueClassValue() && avoidWrongOptimization) || (type = (Type) CollectionsKt.singleOrNull(value.getDescriptor().getUnboxedTypes())) == null || !RedundantBoxingInterpreter.PRIMITIVE_TYPES_SORTS_WITH_WRAPPER_EXTENDS_NUMBER.contains(Integer.valueOf(type.getSort()))) ? false : true;
            }
            if (Intrinsics.areEqual(targetInternalName, "java/lang/Comparable")) {
                return (value.getDescriptor().getIsValueClassValue() && avoidWrongOptimization) ? false : true;
            }
            return Intrinsics.areEqual(value.getType().getInternalName(), targetInternalName);
        }

        private Companion() {
        }
    }
}
