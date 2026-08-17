package org.jetbrains.kotlin.codegen.optimization;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter;
import org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.optimization.fixStack.StackTransformationUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.InsnNode;
import org.jetbrains.org.objectweb.asm.tree.IntInsnNode;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LdcInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0003\u000f\u0010\u0011B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0018\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tH\u0016J\f\u0010\n\u001a\u00020\u000b*\u00020\tH\u0002J\f\u0010\f\u001a\u00020\u000b*\u00020\rH\u0002J\f\u0010\u000e\u001a\u00020\u000b*\u00020\rH\u0002¨\u0006\u0012"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/ConstantConditionEliminationMethodTransformer;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;", "<init>", "()V", "transform", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "hasOptimizableConditions", Argument.Delimiters.none, "isIntConst", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "isIntJump", "ConstantConditionsOptimization", "IConstValue", "ConstantPropagationInterpreter", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class ConstantConditionEliminationMethodTransformer extends MethodTransformer {

    @Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\b\u0002\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0004\b\u0006\u0010\u0007J\u0006\u0010\f\u001a\u00020\rJ\u0014\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u000fH\u0002JB\u0010\u0012\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\"\u0010\u0018\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u0019j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010`\u001aH\u0002JB\u0010\u001b\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00170\u00162\"\u0010\u0018\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u0019j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010`\u001aH\u0002JD\u0010\u001c\u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u001e2\"\u0010\u0018\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u0019j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010`\u001aH\u0002J4\u0010 \u001a\u00020\u00112\u0006\u0010\u0013\u001a\u00020\u00142\"\u0010\u0018\u001a\u001e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u00100\u0019j\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010`\u001aH\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/ConstantConditionEliminationMethodTransformer$ConstantConditionsOptimization;", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;)V", "getInternalClassName", "()Ljava/lang/String;", "getMethodNode", "()Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "run", Argument.Delimiters.none, "collectRewriteActions", Argument.Delimiters.none, "Lkotlin/Function0;", Argument.Delimiters.none, "tryRewriteComparisonWithZero", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/JumpInsnNode;", "frame", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "actions", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "tryRewriteBinaryComparison", "rewriteBinaryComparisonOfConsts", "value1", Argument.Delimiters.none, "value2", "rewriteBinaryComparisonWith0", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ConstantConditionsOptimization {
        private final String internalClassName;
        private final MethodNode methodNode;

        public ConstantConditionsOptimization(String str, MethodNode methodNode) {
            str.getClass();
            methodNode.getClass();
            this.internalClassName = str;
            this.methodNode = methodNode;
        }

        public static Unit b(ConstantConditionsOptimization constantConditionsOptimization, JumpInsnNode jumpInsnNode, boolean z) {
            InsnList insnList = constantConditionsOptimization.methodNode.instructions;
            insnList.insertBefore(jumpInsnNode, new InsnNode(87));
            insnList.insertBefore(jumpInsnNode, new InsnNode(87));
            if (z) {
                insnList.set(jumpInsnNode, new JumpInsnNode(167, jumpInsnNode.label));
            } else {
                insnList.remove(jumpInsnNode);
            }
            return Unit.INSTANCE;
        }

        public static Unit c(ConstantConditionsOptimization constantConditionsOptimization, JumpInsnNode jumpInsnNode) {
            int i;
            InsnList insnList = constantConditionsOptimization.methodNode.instructions;
            insnList.insertBefore(jumpInsnNode, new InsnNode(87));
            switch (jumpInsnNode.getOpcode()) {
                case 159:
                    i = 153;
                    break;
                case 160:
                    i = 154;
                    break;
                case 161:
                    i = 155;
                    break;
                case 162:
                    i = 156;
                    break;
                case 163:
                    i = 157;
                    break;
                case 164:
                    i = 158;
                    break;
                default:
                    pe1.a("Unexpected instruction: ", InlineCodegenUtilsKt.getInsnText(jumpInsnNode));
                    return null;
            }
            insnList.set(jumpInsnNode, new JumpInsnNode(i, jumpInsnNode.label));
            return Unit.INSTANCE;
        }

        private final List<Function0<Unit>> collectRewriteActions() {
            ArrayList<Function0<Unit>> arrayList = new ArrayList<>();
            final ArrayList arrayList2 = new ArrayList();
            Frame<BasicValue>[] frameArrAnalyze = MethodTransformer.analyze(this.internalClassName, this.methodNode, new ConstantPropagationInterpreter());
            frameArrAnalyze.getClass();
            AbstractInsnNode[] array = this.methodNode.instructions.toArray();
            int length = frameArrAnalyze.length;
            for (int i = 0; i < length; i++) {
                AbstractInsnNode abstractInsnNode = array[i];
                Frame<BasicValue> frame = frameArrAnalyze[i];
                if (frame == null) {
                    if (!(abstractInsnNode instanceof LabelNode)) {
                        arrayList2.add(abstractInsnNode);
                    }
                } else if (abstractInsnNode instanceof JumpInsnNode) {
                    JumpInsnNode jumpInsnNode = (JumpInsnNode) abstractInsnNode;
                    int opcode = jumpInsnNode.getOpcode();
                    if (153 <= opcode && opcode < 159) {
                        tryRewriteComparisonWithZero(jumpInsnNode, frame, arrayList);
                    } else if (159 <= opcode && opcode < 165) {
                        tryRewriteBinaryComparison(jumpInsnNode, frame, arrayList);
                    }
                }
            }
            if (!arrayList2.isEmpty()) {
                arrayList.add(new Function0() { // from class: org.jetbrains.kotlin.codegen.optimization.e
                    public final Object invoke() {
                        return ConstantConditionEliminationMethodTransformer.ConstantConditionsOptimization.collectRewriteActions$lambda$0$0(this.b, arrayList2);
                    }
                });
            }
            return arrayList;
        }

        /* JADX INFO: Access modifiers changed from: private */
        public static final Unit collectRewriteActions$lambda$0$0(ConstantConditionsOptimization constantConditionsOptimization, ArrayList arrayList) {
            InsnList insnList = constantConditionsOptimization.methodNode.instructions;
            insnList.getClass();
            UtilKt.removeAll(insnList, arrayList);
            return Unit.INSTANCE;
        }

        public static Unit d(ConstantConditionsOptimization constantConditionsOptimization, JumpInsnNode jumpInsnNode, boolean z) {
            InsnList insnList = constantConditionsOptimization.methodNode.instructions;
            insnList.insertBefore(jumpInsnNode, new InsnNode(87));
            if (z) {
                insnList.set(jumpInsnNode, new JumpInsnNode(167, jumpInsnNode.label));
            } else {
                insnList.remove(jumpInsnNode);
            }
            return Unit.INSTANCE;
        }

        /* JADX WARN: Code duplicated, block: B:7:0x0015  */
        private final void rewriteBinaryComparisonOfConsts(final JumpInsnNode insn, int value1, int value2, ArrayList<Function0<Unit>> actions) {
            final boolean z = false;
            switch (insn.getOpcode()) {
                case 159:
                    if (value1 == value2) {
                        z = true;
                    }
                    actions.add(new Function0() { // from class: org.jetbrains.kotlin.codegen.optimization.g
                        public final Object invoke() {
                            return ConstantConditionEliminationMethodTransformer.ConstantConditionsOptimization.b(this.b, insn, z);
                        }
                    });
                    break;
                case 160:
                    if (value1 != value2) {
                        z = true;
                    }
                    actions.add(new Function0() { // from class: org.jetbrains.kotlin.codegen.optimization.g
                        public final Object invoke() {
                            return ConstantConditionEliminationMethodTransformer.ConstantConditionsOptimization.b(this.b, insn, z);
                        }
                    });
                    break;
                case 161:
                    if (value1 < value2) {
                        z = true;
                    }
                    actions.add(new Function0() { // from class: org.jetbrains.kotlin.codegen.optimization.g
                        public final Object invoke() {
                            return ConstantConditionEliminationMethodTransformer.ConstantConditionsOptimization.b(this.b, insn, z);
                        }
                    });
                    break;
                case 162:
                    if (value1 >= value2) {
                        z = true;
                    }
                    actions.add(new Function0() { // from class: org.jetbrains.kotlin.codegen.optimization.g
                        public final Object invoke() {
                            return ConstantConditionEliminationMethodTransformer.ConstantConditionsOptimization.b(this.b, insn, z);
                        }
                    });
                    break;
                case 163:
                    if (value1 > value2) {
                        z = true;
                    }
                    actions.add(new Function0() { // from class: org.jetbrains.kotlin.codegen.optimization.g
                        public final Object invoke() {
                            return ConstantConditionEliminationMethodTransformer.ConstantConditionsOptimization.b(this.b, insn, z);
                        }
                    });
                    break;
                case 164:
                    if (value1 <= value2) {
                        z = true;
                    }
                    actions.add(new Function0() { // from class: org.jetbrains.kotlin.codegen.optimization.g
                        public final Object invoke() {
                            return ConstantConditionEliminationMethodTransformer.ConstantConditionsOptimization.b(this.b, insn, z);
                        }
                    });
                    break;
                default:
                    pe1.a("Unexpected instruction: ", InlineCodegenUtilsKt.getInsnText(insn));
                    break;
            }
        }

        private final void rewriteBinaryComparisonWith0(final JumpInsnNode insn, ArrayList<Function0<Unit>> actions) {
            actions.add(new Function0() { // from class: org.jetbrains.kotlin.codegen.optimization.f
                public final Object invoke() {
                    return ConstantConditionEliminationMethodTransformer.ConstantConditionsOptimization.c(this.b, insn);
                }
            });
        }

        private final void tryRewriteBinaryComparison(JumpInsnNode insn, Frame<BasicValue> frame, ArrayList<Function0<Unit>> actions) {
            BasicValue basicValuePeek = StackTransformationUtilsKt.peek(frame, 1);
            basicValuePeek.getClass();
            BasicValue basicValue = basicValuePeek;
            BasicValue basicValuePeek2 = StackTransformationUtilsKt.peek(frame, 0);
            basicValuePeek2.getClass();
            BasicValue basicValue2 = basicValuePeek2;
            if ((basicValue instanceof IConstValue) && (basicValue2 instanceof IConstValue)) {
                rewriteBinaryComparisonOfConsts(insn, ((IConstValue) basicValue).getValue(), ((IConstValue) basicValue2).getValue(), actions);
            } else if ((basicValue2 instanceof IConstValue) && ((IConstValue) basicValue2).getValue() == 0) {
                rewriteBinaryComparisonWith0(insn, actions);
            }
        }

        /* JADX WARN: Code duplicated, block: B:14:0x002b  */
        private final void tryRewriteComparisonWithZero(final JumpInsnNode insn, Frame<BasicValue> frame, ArrayList<Function0<Unit>> actions) {
            IConstValue pVar = StackTransformationUtilsKt.top(frame);
            pVar.getClass();
            IConstValue iConstValue = pVar instanceof IConstValue ? pVar : null;
            if (iConstValue == null) {
            }
            final boolean z = false;
            switch (insn.getOpcode()) {
                case 153:
                    if (iConstValue.getValue() == 0) {
                        z = true;
                    }
                    actions.add(new Function0() { // from class: org.jetbrains.kotlin.codegen.optimization.d
                        public final Object invoke() {
                            return ConstantConditionEliminationMethodTransformer.ConstantConditionsOptimization.d(this.b, insn, z);
                        }
                    });
                    break;
                case 154:
                    if (iConstValue.getValue() != 0) {
                        z = true;
                    }
                    actions.add(new Function0() { // from class: org.jetbrains.kotlin.codegen.optimization.d
                        public final Object invoke() {
                            return ConstantConditionEliminationMethodTransformer.ConstantConditionsOptimization.d(this.b, insn, z);
                        }
                    });
                    break;
                case 155:
                    if (iConstValue.getValue() < 0) {
                        z = true;
                    }
                    actions.add(new Function0() { // from class: org.jetbrains.kotlin.codegen.optimization.d
                        public final Object invoke() {
                            return ConstantConditionEliminationMethodTransformer.ConstantConditionsOptimization.d(this.b, insn, z);
                        }
                    });
                    break;
                case 156:
                    if (iConstValue.getValue() >= 0) {
                        z = true;
                    }
                    actions.add(new Function0() { // from class: org.jetbrains.kotlin.codegen.optimization.d
                        public final Object invoke() {
                            return ConstantConditionEliminationMethodTransformer.ConstantConditionsOptimization.d(this.b, insn, z);
                        }
                    });
                    break;
                case 157:
                    if (iConstValue.getValue() > 0) {
                        z = true;
                    }
                    actions.add(new Function0() { // from class: org.jetbrains.kotlin.codegen.optimization.d
                        public final Object invoke() {
                            return ConstantConditionEliminationMethodTransformer.ConstantConditionsOptimization.d(this.b, insn, z);
                        }
                    });
                    break;
                case 158:
                    if (iConstValue.getValue() <= 0) {
                        z = true;
                    }
                    actions.add(new Function0() { // from class: org.jetbrains.kotlin.codegen.optimization.d
                        public final Object invoke() {
                            return ConstantConditionEliminationMethodTransformer.ConstantConditionsOptimization.d(this.b, insn, z);
                        }
                    });
                    break;
                default:
                    pe1.a("Unexpected instruction: ", InlineCodegenUtilsKt.getInsnText(insn));
                    break;
            }
        }

        public final String getInternalClassName() {
            return this.internalClassName;
        }

        public final MethodNode getMethodNode() {
            return this.methodNode;
        }

        public final boolean run() {
            List<Function0<Unit>> listCollectRewriteActions = collectRewriteActions();
            Iterator<T> it = listCollectRewriteActions.iterator();
            while (it.hasNext()) {
                ((Function0) it.next()).invoke();
            }
            return !listCollectRewriteActions.isEmpty();
        }
    }

    @Metadata(d1 = {"\u0000\u001a\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0002\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u0010\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0016J\u0018\u0010\b\u001a\u00020\u00052\u0006\u0010\t\u001a\u00020\u00052\u0006\u0010\n\u001a\u00020\u0005H\u0016¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/ConstantConditionEliminationMethodTransformer$ConstantPropagationInterpreter;", "Lorg/jetbrains/kotlin/codegen/optimization/common/OptimizationBasicInterpreter;", "<init>", "()V", "newOperation", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "merge", "v", "w", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class ConstantPropagationInterpreter extends OptimizationBasicInterpreter {
        @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
        public BasicValue merge(BasicValue v, BasicValue w) {
            v.getClass();
            w.getClass();
            if ((v instanceof IConstValue) && (w instanceof IConstValue) && Intrinsics.areEqual(v, w)) {
                return v;
            }
            BasicValue basicValueMerge = super.merge(v, w);
            basicValueMerge.getClass();
            return basicValueMerge;
        }

        /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
        @Override // org.jetbrains.kotlin.codegen.optimization.common.OptimizationBasicInterpreter
        /* JADX INFO: renamed from: newOperation */
        public BasicValue mo59newOperation(AbstractInsnNode insn) throws AnalyzerException {
            insn.getClass();
            int opcode = insn.getOpcode();
            if (2 <= opcode && opcode < 9) {
                return IConstValue.INSTANCE.of(insn.getOpcode() - 3);
            }
            if (opcode == 16 || opcode == 17) {
                return IConstValue.INSTANCE.of(((IntInsnNode) insn).operand);
            }
            if (opcode != 18) {
                BasicValue basicValueMo59newOperation = super.mo59newOperation(insn);
                basicValueMo59newOperation.getClass();
                return basicValueMo59newOperation;
            }
            Object obj = ((LdcInsnNode) insn).cst;
            BasicValue basicValueOf = obj instanceof Integer ? IConstValue.INSTANCE.of(((Number) obj).intValue()) : super.mo59newOperation(insn);
            basicValueOf.getClass();
            return basicValueOf;
        }
    }

    private final boolean hasOptimizableConditions(MethodNode methodNode) {
        Collection<AbstractInsnNode> collection = methodNode.instructions;
        collection.getClass();
        if ((collection instanceof Collection) && collection.isEmpty()) {
            return false;
        }
        for (AbstractInsnNode abstractInsnNode : collection) {
            abstractInsnNode.getClass();
            if (isIntJump(abstractInsnNode)) {
                Collection<AbstractInsnNode> collection2 = methodNode.instructions;
                collection2.getClass();
                if ((collection2 instanceof Collection) && collection2.isEmpty()) {
                    return false;
                }
                for (AbstractInsnNode abstractInsnNode2 : collection2) {
                    abstractInsnNode2.getClass();
                    if (isIntConst(abstractInsnNode2)) {
                        return true;
                    }
                }
                return false;
            }
        }
        return false;
    }

    private final boolean isIntConst(AbstractInsnNode abstractInsnNode) {
        int opcode = abstractInsnNode.getOpcode();
        if ((2 <= opcode && opcode < 9) || abstractInsnNode.getOpcode() == 16 || abstractInsnNode.getOpcode() == 17) {
            return true;
        }
        return abstractInsnNode.getOpcode() == 18 && (abstractInsnNode instanceof LdcInsnNode) && (((LdcInsnNode) abstractInsnNode).cst instanceof Integer);
    }

    private final boolean isIntJump(AbstractInsnNode abstractInsnNode) {
        int opcode = abstractInsnNode.getOpcode();
        if (153 <= opcode && opcode < 159) {
            return true;
        }
        int opcode2 = abstractInsnNode.getOpcode();
        return 159 <= opcode2 && opcode2 < 165;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
    public void transform(String internalClassName, MethodNode methodNode) {
        internalClassName.getClass();
        methodNode.getClass();
        if (hasOptimizableConditions(methodNode)) {
            while (new ConstantConditionsOptimization(internalClassName, methodNode).run()) {
            }
        }
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0002\u0018\u0000 \u000f2\u00020\u0001:\u0001\u000fB\u0011\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0014\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0096\u0082\u0004J\n\u0010\f\u001a\u00020\u0003H\u0096\u0080\u0004J\n\u0010\r\u001a\u00020\u000eH\u0096\u0080\u0004R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/ConstantConditionEliminationMethodTransformer$IConstValue;", "Lorg/jetbrains/kotlin/codegen/optimization/common/StrictBasicValue;", "value", Argument.Delimiters.none, "<init>", "(I)V", "getValue", "()I", "equals", Argument.Delimiters.none, "other", Argument.Delimiters.none, "hashCode", "toString", Argument.Delimiters.none, "Companion", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class IConstValue extends StrictBasicValue {

        /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
        public static final Companion INSTANCE = new Companion(null);
        private static final IConstValue[] ICONST_CACHE;
        private final int value;

        static {
            IConstValue[] iConstValueArr = new IConstValue[7];
            for (int i = 0; i < 7; i++) {
                iConstValueArr[i] = new IConstValue(i - 1);
            }
            ICONST_CACHE = iConstValueArr;
        }

        private IConstValue(int i) {
            super(Type.INT_TYPE);
            this.value = i;
        }

        @Override // org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue
        public boolean equals(Object other) {
            if (other != this) {
                return (other instanceof IConstValue) && ((IConstValue) other).value == this.value;
            }
            return true;
        }

        public final int getValue() {
            return this.value;
        }

        @Override // org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue
        public int hashCode() {
            return this.value;
        }

        @Override // org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue
        public String toString() {
            return "IConst(" + this.value + ')';
        }

        @Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\b\u001a\u00020\u00062\u0006\u0010\t\u001a\u00020\nR\u0016\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0007¨\u0006\u000b"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/ConstantConditionEliminationMethodTransformer$IConstValue$Companion;", Argument.Delimiters.none, "<init>", "()V", "ICONST_CACHE", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/optimization/ConstantConditionEliminationMethodTransformer$IConstValue;", "[Lorg/jetbrains/kotlin/codegen/optimization/ConstantConditionEliminationMethodTransformer$IConstValue;", "of", "value", Argument.Delimiters.none, "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public static final class Companion {
            public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
                this();
            }

            public final IConstValue of(int value) {
                return (-1 > value || value >= 6) ? new IConstValue(value, null) : IConstValue.ICONST_CACHE[value + 1];
            }

            private Companion() {
            }
        }

        public /* synthetic */ IConstValue(int i, DefaultConstructorMarker defaultConstructorMarker) {
            this(i);
        }
    }
}
