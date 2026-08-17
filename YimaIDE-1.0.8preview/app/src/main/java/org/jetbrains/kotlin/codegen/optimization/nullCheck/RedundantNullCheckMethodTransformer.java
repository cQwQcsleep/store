package org.jetbrains.kotlin.codegen.optimization.nullCheck;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.CodegenUtilKt;
import org.jetbrains.kotlin.codegen.inline.ReifiedTypeInliner;
import org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.optimization.fixStack.StackTransformationUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.codegen.pseudoInsns.PseudoInsn;
import org.jetbrains.kotlin.codegen.pseudoInsns.PseudoInsnsKt;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.kotlin.utils.SmartList;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.InsnNode;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TypeInsnNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u00002\u00020\u0001:\u0001\fB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/nullCheck/RedundantNullCheckMethodTransformer;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;", "generationState", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "<init>", "(Lorg/jetbrains/kotlin/codegen/state/GenerationState;)V", "transform", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "TransformerPass", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RedundantNullCheckMethodTransformer extends MethodTransformer {
    private final GenerationState generationState;

    @Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u0002\u0018\u00002\u00020\u0001:\u0002)*B\u001f\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007¢\u0006\u0004\b\b\u0010\tJ\u0006\u0010\u0012\u001a\u00020\u0011J\u0014\u0010\u0013\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014H\u0002J\f\u0010\u0017\u001a\u00020\u0011*\u00020\u0015H\u0002J\u001c\u0010\u0018\u001a\u00020\u00192\u0012\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020\u00160\u0014H\u0002J\u0018\u0010\u001b\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u001d2\u0006\u0010\u001e\u001a\u00020\u0011H\u0002J \u0010\u001f\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020 2\u0006\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020\u0016H\u0002J\u0018\u0010$\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010%\u001a\u00020\u0011H\u0002J\u0018\u0010&\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\"H\u0002J\u0018\u0010'\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\"H\u0002J\u0018\u0010(\u001a\u00020\u00192\u0006\u0010\u001c\u001a\u00020\u00152\u0006\u0010!\u001a\u00020\"H\u0002R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0011\u0010\u0006\u001a\u00020\u0007¢\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006+"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/nullCheck/RedundantNullCheckMethodTransformer$TransformerPass;", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "methodNode", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "generationState", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "<init>", "(Ljava/lang/String;Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;Lorg/jetbrains/kotlin/codegen/state/GenerationState;)V", "getInternalClassName", "()Ljava/lang/String;", "getMethodNode", "()Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "getGenerationState", "()Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "changes", Argument.Delimiters.none, "run", "analyzeNullabilities", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "Lorg/jetbrains/kotlin/codegen/optimization/common/StrictBasicValue;", "isOptimizable", "transformTrivialChecks", Argument.Delimiters.none, "nullabilityMap", "transformTrivialNullJump", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/JumpInsnNode;", "alwaysTrue", "transformInstanceOf", "Lorg/jetbrains/org/objectweb/asm/tree/TypeInsnNode;", "nullability", "Lorg/jetbrains/kotlin/codegen/optimization/nullCheck/Nullability;", "value", "transformTrivialInstanceOf", "constValue", "transformTrivialCheckNotNull", "transformTrivialCheckNotNullWithMessage", "transformTrivialCheckExpressionValueIsNotNull", "NullabilityAssumptionsBuilder", "NullabilityAssumptions", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final class TransformerPass {
        private boolean changes;
        private final GenerationState generationState;
        private final String internalClassName;
        private final MethodNode methodNode;

        @Metadata(d1 = {"\u0000J\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u0086\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\u001d\u0010\u0011\u001a\u0002H\u0012\"\b\b\u0000\u0010\u0012*\u00020\r2\u0006\u0010\u0013\u001a\u0002H\u0012¢\u0006\u0002\u0010\u0014J%\u0010\u0015\u001a\u00020\u00162\u0017\u0010\u0017\u001a\u0013\u0012\u0004\u0012\u00020\u0019\u0012\u0004\u0012\u00020\u001a0\u0018¢\u0006\u0002\b\u001bH\u0086\bø\u0001\u0000J\u0006\u0010\u001c\u001a\u00020\u001aR-\u0010\u0004\u001a\u001e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00070\u0005j\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0007`\b¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR!\u0010\u000b\u001a\u0012\u0012\u0004\u0012\u00020\r0\fj\b\u0012\u0004\u0012\u00020\r`\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u0010\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/nullCheck/RedundantNullCheckMethodTransformer$TransformerPass$NullabilityAssumptions;", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/codegen/optimization/nullCheck/RedundantNullCheckMethodTransformer$TransformerPass;)V", "originalLabels", "Ljava/util/HashMap;", "Lorg/jetbrains/org/objectweb/asm/tree/JumpInsnNode;", "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "Lkotlin/collections/HashMap;", "getOriginalLabels", "()Ljava/util/HashMap;", "syntheticInstructions", "Ljava/util/ArrayList;", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "Lkotlin/collections/ArrayList;", "getSyntheticInstructions", "()Ljava/util/ArrayList;", "synthetic", "T", "insn", "(Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;)Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "listOfSynthetics", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "block", "Lkotlin/Function1;", "Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;", Argument.Delimiters.none, "Lkotlin/ExtensionFunctionType;", "revert", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public final class NullabilityAssumptions {
            private final HashMap<JumpInsnNode, LabelNode> originalLabels = new HashMap<>();
            private final ArrayList<AbstractInsnNode> syntheticInstructions = new ArrayList<>();

            public NullabilityAssumptions() {
            }

            public final HashMap<JumpInsnNode, LabelNode> getOriginalLabels() {
                return this.originalLabels;
            }

            public final ArrayList<AbstractInsnNode> getSyntheticInstructions() {
                return this.syntheticInstructions;
            }

            public final InsnList listOfSynthetics(Function1<? super InstructionAdapter, Unit> block) {
                block.getClass();
                MethodNode methodNode = new MethodNode();
                block.invoke(new InstructionAdapter(methodNode));
                InsnList insnList = methodNode.instructions;
                insnList.getClass();
                ListIterator it = insnList.iterator();
                it.getClass();
                while (it.hasNext()) {
                    AbstractInsnNode abstractInsnNode = (AbstractInsnNode) it.next();
                    abstractInsnNode.getClass();
                    synthetic(abstractInsnNode);
                }
                return insnList;
            }

            public final void revert() {
                InsnList insnList = TransformerPass.this.getMethodNode().instructions;
                Iterator<T> it = this.syntheticInstructions.iterator();
                while (it.hasNext()) {
                    insnList.remove((AbstractInsnNode) it.next());
                }
                for (Map.Entry<JumpInsnNode, LabelNode> entry : this.originalLabels.entrySet()) {
                    entry.getKey().label = entry.getValue();
                }
            }

            public final <T extends AbstractInsnNode> T synthetic(T insn) {
                insn.getClass();
                this.syntheticInstructions.add(insn);
                return insn;
            }
        }

        @Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0082\u0004\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u0002\u0010\u0003J\n\u0010\n\u001a\u00060\u000bR\u00020\fJ\b\u0010\r\u001a\u00020\u000eH\u0002J\u0018\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\bH\u0002J\u0018\u0010\u0012\u001a\u00020\u000e2\u0006\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0013\u001a\u00020\u0014H\u0002J\f\u0010\u0015\u001a\u00060\u000bR\u00020\fH\u0002J \u0010\u0016\u001a\u00020\u000e*\u00060\u000bR\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\bH\u0002J \u0010\u0018\u001a\u00020\u000e*\u00060\u000bR\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\u0019H\u0002J \u0010\u001a\u001a\u00020\u000e*\u00060\u000bR\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\bH\u0002J \u0010\u001b\u001a\u00020\u000e*\u00060\u000bR\u00020\f2\u0006\u0010\u0017\u001a\u00020\u00062\u0006\u0010\u0010\u001a\u00020\bH\u0002J\u0018\u0010\u001c\u001a\u00020\u000e*\u00060\u000bR\u00020\f2\u0006\u0010\u0010\u001a\u00020\bH\u0002J\u0018\u0010\u001d\u001a\u00020\u000e*\u00060\u000bR\u00020\f2\u0006\u0010\u0010\u001a\u00020\bH\u0002R6\u0010\u0004\u001a*\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u00070\u0005j\u0014\u0012\u0004\u0012\u00020\u0006\u0012\n\u0012\b\u0012\u0004\u0012\u00020\b0\u0007`\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u001e"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/nullCheck/RedundantNullCheckMethodTransformer$TransformerPass$NullabilityAssumptionsBuilder;", Argument.Delimiters.none, "<init>", "(Lorg/jetbrains/kotlin/codegen/optimization/nullCheck/RedundantNullCheckMethodTransformer$TransformerPass;)V", "checksDependingOnVariable", "Ljava/util/HashMap;", Argument.Delimiters.none, Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "Lkotlin/collections/HashMap;", "injectNullabilityAssumptions", "Lorg/jetbrains/kotlin/codegen/optimization/nullCheck/RedundantNullCheckMethodTransformer$TransformerPass$NullabilityAssumptions;", "Lorg/jetbrains/kotlin/codegen/optimization/nullCheck/RedundantNullCheckMethodTransformer$TransformerPass;", "collectVariableDependentChecks", Argument.Delimiters.none, "addDependentCheckForCheckNotNull", "insn", "checkedValueInsn", "addDependentCheck", "aLoadInsn", "Lorg/jetbrains/org/objectweb/asm/tree/VarInsnNode;", "injectAssumptions", "injectAssumptionsForInsn", "varIndex", "injectAssumptionsForNullCheck", "Lorg/jetbrains/org/objectweb/asm/tree/JumpInsnNode;", "injectAssumptionsForNotNullAssertion", "injectAssumptionsForInstanceOfCheck", "injectCodeForThrowIntrinsic", "injectCodeForStoreNotNull", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
        public final class NullabilityAssumptionsBuilder {
            private final HashMap<Integer, List<AbstractInsnNode>> checksDependingOnVariable = new HashMap<>();

            public NullabilityAssumptionsBuilder() {
            }

            private final void addDependentCheck(AbstractInsnNode insn, VarInsnNode aLoadInsn) {
                HashMap<Integer, List<AbstractInsnNode>> map = this.checksDependingOnVariable;
                Integer numValueOf = Integer.valueOf(aLoadInsn.var);
                SmartList smartList = map.get(numValueOf);
                if (smartList == null) {
                    smartList = new SmartList();
                    map.put(numValueOf, smartList);
                }
                ((List) smartList).add(insn);
            }

            private final void addDependentCheckForCheckNotNull(AbstractInsnNode insn, AbstractInsnNode checkedValueInsn) {
                if (!(checkedValueInsn.getOpcode() == 89 && (checkedValueInsn = checkedValueInsn.getPrevious()) == null) && checkedValueInsn.getOpcode() == 25) {
                    addDependentCheck(insn, (VarInsnNode) checkedValueInsn);
                }
            }

            private final void collectVariableDependentChecks() {
                AbstractInsnNode previous;
                AbstractInsnNode previous2;
                AbstractInsnNode previous3;
                AbstractInsnNode previous4;
                VarInsnNode previous5;
                ListIterator it = TransformerPass.this.getMethodNode().instructions.iterator();
                it.getClass();
                while (it.hasNext()) {
                    AbstractInsnNode abstractInsnNode = (AbstractInsnNode) it.next();
                    abstractInsnNode.getClass();
                    if (RedundantNullCheckMethodTransformerKt.isInstanceOfOrNullCheck(abstractInsnNode)) {
                        AbstractInsnNode previous6 = abstractInsnNode.getPrevious();
                        if (previous6 != null) {
                            if (previous6.getOpcode() == 25) {
                                addDependentCheck(abstractInsnNode, (VarInsnNode) previous6);
                            } else if (previous6.getOpcode() == 89 && (previous = previous6.getPrevious()) != null && previous.getOpcode() == 25) {
                                addDependentCheck(abstractInsnNode, (VarInsnNode) previous);
                            }
                        }
                    } else if (RedundantNullCheckMethodTransformerKt.isCheckNotNull(abstractInsnNode)) {
                        AbstractInsnNode previous7 = abstractInsnNode.getPrevious();
                        if (previous7 != null) {
                            addDependentCheckForCheckNotNull(abstractInsnNode, previous7);
                        }
                    } else {
                        VarInsnNode varInsnNode = null;
                        if (RedundantNullCheckMethodTransformerKt.isCheckNotNullWithMessage(abstractInsnNode)) {
                            VarInsnNode previous8 = abstractInsnNode.getPrevious();
                            if (previous8 != null) {
                                varInsnNode = previous8.getOpcode() == 18 ? previous8 : null;
                                if (varInsnNode != null && (previous2 = varInsnNode.getPrevious()) != null) {
                                    addDependentCheckForCheckNotNull(abstractInsnNode, previous2);
                                }
                            }
                        } else if (RedundantNullCheckMethodTransformerKt.isCheckParameterIsNotNull(abstractInsnNode)) {
                            AbstractInsnNode previous9 = abstractInsnNode.getPrevious();
                            if (previous9 != null && previous9.getOpcode() == 18 && (previous3 = previous9.getPrevious()) != null && previous3.getOpcode() == 25) {
                                addDependentCheck(abstractInsnNode, (VarInsnNode) previous3);
                            }
                        } else if (RedundantNullCheckMethodTransformerKt.isCheckExpressionValueIsNotNull(abstractInsnNode) && (previous4 = abstractInsnNode.getPrevious()) != null && previous4.getOpcode() == 18 && (previous5 = previous4.getPrevious()) != null) {
                            if (previous5.getOpcode() == 25) {
                                varInsnNode = previous5;
                            } else if (previous5.getOpcode() == 89) {
                                AbstractInsnNode previous10 = previous5.getPrevious();
                                if (previous10 != null) {
                                    if (previous10.getOpcode() == 25) {
                                        varInsnNode = (VarInsnNode) previous10;
                                    }
                                }
                            }
                            if (varInsnNode != null) {
                                addDependentCheck(abstractInsnNode, varInsnNode);
                            }
                        }
                    }
                }
            }

            private final NullabilityAssumptions injectAssumptions() {
                NullabilityAssumptions nullabilityAssumptions = TransformerPass.this.new NullabilityAssumptions();
                for (Map.Entry<Integer, List<AbstractInsnNode>> entry : this.checksDependingOnVariable.entrySet()) {
                    int iIntValue = entry.getKey().intValue();
                    Iterator<AbstractInsnNode> it = entry.getValue().iterator();
                    while (it.hasNext()) {
                        injectAssumptionsForInsn(nullabilityAssumptions, iIntValue, it.next());
                    }
                }
                ListIterator it2 = TransformerPass.this.getMethodNode().instructions.iterator();
                it2.getClass();
                while (it2.hasNext()) {
                    AbstractInsnNode abstractInsnNode = (AbstractInsnNode) it2.next();
                    abstractInsnNode.getClass();
                    if (RedundantNullCheckMethodTransformerKt.isThrowIntrinsic(abstractInsnNode)) {
                        injectCodeForThrowIntrinsic(nullabilityAssumptions, abstractInsnNode);
                    }
                }
                return nullabilityAssumptions;
            }

            private final void injectAssumptionsForInsn(NullabilityAssumptions nullabilityAssumptions, int i, AbstractInsnNode abstractInsnNode) {
                int opcode = abstractInsnNode.getOpcode();
                if (opcode != 184) {
                    if (opcode == 193) {
                        injectAssumptionsForInstanceOfCheck(nullabilityAssumptions, i, abstractInsnNode);
                        return;
                    } else {
                        if (opcode == 198 || opcode == 199) {
                            injectAssumptionsForNullCheck(nullabilityAssumptions, i, (JumpInsnNode) abstractInsnNode);
                            return;
                        }
                        return;
                    }
                }
                if (RedundantNullCheckMethodTransformerKt.isCheckNotNull(abstractInsnNode) || RedundantNullCheckMethodTransformerKt.isCheckNotNullWithMessage(abstractInsnNode) || RedundantNullCheckMethodTransformerKt.isCheckParameterIsNotNull(abstractInsnNode) || RedundantNullCheckMethodTransformerKt.isCheckExpressionValueIsNotNull(abstractInsnNode)) {
                    injectAssumptionsForNotNullAssertion(nullabilityAssumptions, i, abstractInsnNode);
                } else if (PseudoInsnsKt.isPseudo(abstractInsnNode, PseudoInsn.STORE_NOT_NULL)) {
                    injectCodeForStoreNotNull(nullabilityAssumptions, abstractInsnNode);
                } else {
                    pe1.a("Expected non-null assertion: ", UtilKt.getDebugText(abstractInsnNode));
                }
            }

            private final void injectAssumptionsForInstanceOfCheck(NullabilityAssumptions nullabilityAssumptions, int i, AbstractInsnNode abstractInsnNode) {
                LabelNode labelNodeLinkWithLabel;
                LabelNode next = abstractInsnNode.getNext();
                if (next == null) {
                    return;
                }
                if ((next.getOpcode() == 153 || next.getOpcode() == 154) && (next instanceof JumpInsnNode)) {
                    JumpInsnNode jumpInsnNode = (JumpInsnNode) next;
                    if (jumpInsnNode.getOpcode() == 154) {
                        LabelNode labelNode = jumpInsnNode.label;
                        labelNode.getClass();
                        labelNodeLinkWithLabel = CodegenUtilKt.linkWithLabel(labelNode);
                        nullabilityAssumptions.getOriginalLabels().put(next, jumpInsnNode.label);
                        next = nullabilityAssumptions.synthetic(new LabelNode(new Label()));
                        TransformerPass.this.getMethodNode().instructions.add(next);
                        jumpInsnNode.label = next;
                    } else {
                        labelNodeLinkWithLabel = null;
                    }
                    InsnList insnList = TransformerPass.this.getMethodNode().instructions;
                    MethodNode methodNode = new MethodNode();
                    InstructionAdapter instructionAdapter = new InstructionAdapter(methodNode);
                    Type type = AsmTypes.OBJECT_TYPE;
                    instructionAdapter.load(i, type);
                    PseudoInsnsKt.asNotNull(instructionAdapter);
                    instructionAdapter.store(i, type);
                    if (labelNodeLinkWithLabel != null) {
                        instructionAdapter.goTo(labelNodeLinkWithLabel.getLabel());
                    }
                    InsnList insnList2 = methodNode.instructions;
                    insnList2.getClass();
                    ListIterator it = insnList2.iterator();
                    it.getClass();
                    while (it.hasNext()) {
                        AbstractInsnNode abstractInsnNode2 = (AbstractInsnNode) it.next();
                        abstractInsnNode2.getClass();
                        nullabilityAssumptions.synthetic(abstractInsnNode2);
                    }
                    insnList.insert(next, insnList2);
                }
            }

            private final void injectAssumptionsForNotNullAssertion(NullabilityAssumptions nullabilityAssumptions, int i, AbstractInsnNode abstractInsnNode) {
                InsnList insnList = TransformerPass.this.getMethodNode().instructions;
                MethodNode methodNode = new MethodNode();
                InstructionAdapter instructionAdapter = new InstructionAdapter(methodNode);
                Type type = AsmTypes.OBJECT_TYPE;
                instructionAdapter.load(i, type);
                PseudoInsnsKt.asNotNull(instructionAdapter);
                instructionAdapter.store(i, type);
                InsnList insnList2 = methodNode.instructions;
                insnList2.getClass();
                ListIterator it = insnList2.iterator();
                it.getClass();
                while (it.hasNext()) {
                    AbstractInsnNode abstractInsnNode2 = (AbstractInsnNode) it.next();
                    abstractInsnNode2.getClass();
                    nullabilityAssumptions.synthetic(abstractInsnNode2);
                }
                insnList.insert(abstractInsnNode, insnList2);
            }

            private final void injectAssumptionsForNullCheck(NullabilityAssumptions nullabilityAssumptions, int i, JumpInsnNode jumpInsnNode) {
                boolean z = jumpInsnNode.getOpcode() == 198;
                LabelNode labelNode = jumpInsnNode.label;
                labelNode.getClass();
                LabelNode labelNodeLinkWithLabel = CodegenUtilKt.linkWithLabel(labelNode);
                nullabilityAssumptions.getOriginalLabels().put(jumpInsnNode, labelNodeLinkWithLabel);
                JumpInsnNode jumpInsnNode2 = (LabelNode) nullabilityAssumptions.synthetic(new LabelNode(new Label()));
                jumpInsnNode.label = jumpInsnNode2;
                JumpInsnNode jumpInsnNode3 = z ? jumpInsnNode2 : jumpInsnNode;
                if (z) {
                    jumpInsnNode2 = jumpInsnNode;
                }
                InsnList insnList = TransformerPass.this.getMethodNode().instructions;
                insnList.add(jumpInsnNode.label);
                MethodNode methodNode = new MethodNode();
                InstructionAdapter instructionAdapter = new InstructionAdapter(methodNode);
                instructionAdapter.aconst((Object) null);
                instructionAdapter.store(i, AsmTypes.OBJECT_TYPE);
                if (z) {
                    instructionAdapter.goTo(labelNodeLinkWithLabel.getLabel());
                }
                InsnList insnList2 = methodNode.instructions;
                insnList2.getClass();
                ListIterator it = insnList2.iterator();
                it.getClass();
                while (it.hasNext()) {
                    AbstractInsnNode abstractInsnNode = (AbstractInsnNode) it.next();
                    abstractInsnNode.getClass();
                    nullabilityAssumptions.synthetic(abstractInsnNode);
                }
                insnList.insert(jumpInsnNode3, insnList2);
                MethodNode methodNode2 = new MethodNode();
                InstructionAdapter instructionAdapter2 = new InstructionAdapter(methodNode2);
                Type type = AsmTypes.OBJECT_TYPE;
                instructionAdapter2.load(i, type);
                PseudoInsnsKt.asNotNull(instructionAdapter2);
                instructionAdapter2.store(i, type);
                if (!z) {
                    instructionAdapter2.goTo(labelNodeLinkWithLabel.getLabel());
                }
                InsnList insnList3 = methodNode2.instructions;
                insnList3.getClass();
                ListIterator it2 = insnList3.iterator();
                it2.getClass();
                while (it2.hasNext()) {
                    AbstractInsnNode abstractInsnNode2 = (AbstractInsnNode) it2.next();
                    abstractInsnNode2.getClass();
                    nullabilityAssumptions.synthetic(abstractInsnNode2);
                }
                insnList.insert(jumpInsnNode2, insnList3);
            }

            private final void injectCodeForStoreNotNull(NullabilityAssumptions nullabilityAssumptions, AbstractInsnNode abstractInsnNode) {
                VarInsnNode previous = abstractInsnNode.getPrevious();
                if (previous.getOpcode() != 58) {
                    return;
                }
                InsnList insnList = TransformerPass.this.getMethodNode().instructions;
                MethodNode methodNode = new MethodNode();
                InstructionAdapter instructionAdapter = new InstructionAdapter(methodNode);
                int i = previous.var;
                Type type = AsmTypes.OBJECT_TYPE;
                instructionAdapter.load(i, type);
                PseudoInsnsKt.asNotNull(instructionAdapter);
                instructionAdapter.store(i, type);
                InsnList insnList2 = methodNode.instructions;
                insnList2.getClass();
                ListIterator it = insnList2.iterator();
                it.getClass();
                while (it.hasNext()) {
                    AbstractInsnNode abstractInsnNode2 = (AbstractInsnNode) it.next();
                    abstractInsnNode2.getClass();
                    nullabilityAssumptions.synthetic(abstractInsnNode2);
                }
                insnList.insert(abstractInsnNode, insnList2);
            }

            private final void injectCodeForThrowIntrinsic(NullabilityAssumptions nullabilityAssumptions, AbstractInsnNode abstractInsnNode) {
                InsnList insnList = TransformerPass.this.getMethodNode().instructions;
                MethodNode methodNode = new MethodNode();
                InstructionAdapter instructionAdapter = new InstructionAdapter(methodNode);
                instructionAdapter.aconst((Object) null);
                instructionAdapter.athrow();
                InsnList insnList2 = methodNode.instructions;
                insnList2.getClass();
                ListIterator it = insnList2.iterator();
                it.getClass();
                while (it.hasNext()) {
                    AbstractInsnNode abstractInsnNode2 = (AbstractInsnNode) it.next();
                    abstractInsnNode2.getClass();
                    nullabilityAssumptions.synthetic(abstractInsnNode2);
                }
                insnList.insert(abstractInsnNode, insnList2);
                TransformerPass.this.getMethodNode().maxStack++;
            }

            public final NullabilityAssumptions injectNullabilityAssumptions() {
                collectVariableDependentChecks();
                return injectAssumptions();
            }
        }

        public TransformerPass(String str, MethodNode methodNode, GenerationState generationState) {
            str.getClass();
            methodNode.getClass();
            generationState.getClass();
            this.internalClassName = str;
            this.methodNode = methodNode;
            this.generationState = generationState;
        }

        private final Map<AbstractInsnNode, StrictBasicValue> analyzeNullabilities() {
            Frame[] frameArrAnalyze = MethodTransformer.analyze(this.internalClassName, this.methodNode, new NullabilityInterpreter(this.generationState));
            frameArrAnalyze.getClass();
            AbstractInsnNode[] array = this.methodNode.instructions.toArray();
            LinkedHashMap linkedHashMap = new LinkedHashMap();
            int length = array.length;
            for (int i = 0; i < length; i++) {
                Frame frame = frameArrAnalyze[i];
                if (frame != null) {
                    AbstractInsnNode abstractInsnNode = array[i];
                    abstractInsnNode.getClass();
                    BasicValue basicValue = (RedundantNullCheckMethodTransformerKt.isInstanceOfOrNullCheck(abstractInsnNode) || RedundantNullCheckMethodTransformerKt.isCheckNotNull(abstractInsnNode)) ? (BasicValue) StackTransformationUtilsKt.top(frame) : (RedundantNullCheckMethodTransformerKt.isCheckNotNullWithMessage(abstractInsnNode) || RedundantNullCheckMethodTransformerKt.isCheckExpressionValueIsNotNull(abstractInsnNode)) ? (BasicValue) StackTransformationUtilsKt.peek(frame, 1) : null;
                    StrictBasicValue strictBasicValue = basicValue instanceof StrictBasicValue ? (StrictBasicValue) basicValue : null;
                    if (strictBasicValue != null && NullabilityValuesKt.getNullability(strictBasicValue) != Nullability.NULLABLE) {
                        linkedHashMap.put(abstractInsnNode, strictBasicValue);
                    }
                }
            }
            return linkedHashMap;
        }

        private final boolean isOptimizable(AbstractInsnNode abstractInsnNode) {
            return abstractInsnNode.getOpcode() == 198 || abstractInsnNode.getOpcode() == 199 || abstractInsnNode.getOpcode() == 193 || RedundantNullCheckMethodTransformerKt.isCheckNotNull(abstractInsnNode) || RedundantNullCheckMethodTransformerKt.isCheckNotNullWithMessage(abstractInsnNode) || RedundantNullCheckMethodTransformerKt.isCheckExpressionValueIsNotNull(abstractInsnNode);
        }

        private final void transformInstanceOf(TypeInsnNode insn, Nullability nullability, StrictBasicValue value) {
            ReifiedTypeInliner.Companion companion = ReifiedTypeInliner.INSTANCE;
            AbstractInsnNode previous = insn.getPrevious();
            previous.getClass();
            if (companion.isOperationReifiedMarker(previous)) {
                return;
            }
            if (nullability == Nullability.NULL) {
                this.changes = true;
                transformTrivialInstanceOf(insn, false);
            } else if (nullability == Nullability.NOT_NULL && Intrinsics.areEqual(value.getType().getInternalName(), insn.desc)) {
                this.changes = true;
                transformTrivialInstanceOf(insn, true);
            }
        }

        private final void transformTrivialCheckExpressionValueIsNotNull(AbstractInsnNode insn, Nullability nullability) {
            AbstractInsnNode previous;
            if (nullability == Nullability.NOT_NULL && (previous = insn.getPrevious()) != null) {
                if (previous.getOpcode() != 18) {
                    previous = null;
                }
                if (previous == null) {
                    return;
                }
                InsnList insnList = this.methodNode.instructions;
                insnList.getClass();
                RedundantNullCheckMethodTransformerKt.popReferenceValueBefore(insnList, previous);
                insnList.remove(previous);
                insnList.remove(insn);
            }
        }

        private final void transformTrivialCheckNotNull(AbstractInsnNode insn, Nullability nullability) {
            AbstractInsnNode previous;
            if (nullability == Nullability.NOT_NULL && (previous = insn.getPrevious()) != null) {
                if (previous.getOpcode() != 89 && previous.getOpcode() != 25) {
                    previous = null;
                }
                if (previous == null) {
                    return;
                }
                InsnList insnList = this.methodNode.instructions;
                insnList.remove(previous);
                insnList.remove(insn);
            }
        }

        private final void transformTrivialCheckNotNullWithMessage(AbstractInsnNode insn, Nullability nullability) {
            AbstractInsnNode previous;
            AbstractInsnNode previous2;
            if (nullability == Nullability.NOT_NULL && (previous = insn.getPrevious()) != null) {
                if (previous.getOpcode() != 18) {
                    previous = null;
                }
                if (previous == null || (previous2 = previous.getPrevious()) == null) {
                    return;
                }
                AbstractInsnNode abstractInsnNode = (previous2.getOpcode() == 89 || previous2.getOpcode() == 25) ? previous2 : null;
                if (abstractInsnNode == null) {
                    return;
                }
                InsnList insnList = this.methodNode.instructions;
                insnList.remove(abstractInsnNode);
                insnList.remove(previous);
                insnList.remove(insn);
            }
        }

        private final void transformTrivialChecks(Map<AbstractInsnNode, ? extends StrictBasicValue> nullabilityMap) {
            for (Map.Entry<AbstractInsnNode, ? extends StrictBasicValue> entry : nullabilityMap.entrySet()) {
                AbstractInsnNode key = entry.getKey();
                StrictBasicValue value = entry.getValue();
                Nullability nullability = NullabilityValuesKt.getNullability(value);
                int opcode = key.getOpcode();
                if (opcode != 184) {
                    if (opcode == 193) {
                        transformInstanceOf((TypeInsnNode) key, nullability, value);
                    } else if (opcode == 198) {
                        transformTrivialNullJump((JumpInsnNode) key, nullability == Nullability.NULL);
                    } else if (opcode == 199) {
                        transformTrivialNullJump((JumpInsnNode) key, nullability == Nullability.NOT_NULL);
                    }
                } else if (RedundantNullCheckMethodTransformerKt.isCheckNotNull(key)) {
                    transformTrivialCheckNotNull(key, nullability);
                } else if (RedundantNullCheckMethodTransformerKt.isCheckNotNullWithMessage(key)) {
                    transformTrivialCheckNotNullWithMessage(key, nullability);
                } else if (RedundantNullCheckMethodTransformerKt.isCheckExpressionValueIsNotNull(key)) {
                    transformTrivialCheckExpressionValueIsNotNull(key, nullability);
                }
            }
        }

        private final void transformTrivialInstanceOf(AbstractInsnNode insn, boolean constValue) {
            InsnList insnList = this.methodNode.instructions;
            insnList.getClass();
            RedundantNullCheckMethodTransformerKt.popReferenceValueBefore(insnList, insn);
            insnList.set(insn, constValue ? new InsnNode(4) : new InsnNode(3));
        }

        private final void transformTrivialNullJump(JumpInsnNode insn, boolean alwaysTrue) {
            this.changes = true;
            InsnList insnList = this.methodNode.instructions;
            insnList.getClass();
            RedundantNullCheckMethodTransformerKt.popReferenceValueBefore(insnList, insn);
            if (alwaysTrue) {
                insnList.set(insn, new JumpInsnNode(167, insn.label));
            } else {
                insnList.remove(insn);
            }
        }

        public final GenerationState getGenerationState() {
            return this.generationState;
        }

        public final String getInternalClassName() {
            return this.internalClassName;
        }

        public final MethodNode getMethodNode() {
            return this.methodNode;
        }

        public final boolean run() {
            Collection<AbstractInsnNode> collection = this.methodNode.instructions;
            collection.getClass();
            if ((collection instanceof Collection) && collection.isEmpty()) {
                return false;
            }
            for (AbstractInsnNode abstractInsnNode : collection) {
                abstractInsnNode.getClass();
                if (isOptimizable(abstractInsnNode)) {
                    NullabilityAssumptions nullabilityAssumptionsInjectNullabilityAssumptions = new NullabilityAssumptionsBuilder().injectNullabilityAssumptions();
                    Map<AbstractInsnNode, StrictBasicValue> mapAnalyzeNullabilities = analyzeNullabilities();
                    nullabilityAssumptionsInjectNullabilityAssumptions.revert();
                    transformTrivialChecks(mapAnalyzeNullabilities);
                    return this.changes;
                }
            }
            return false;
        }
    }

    public RedundantNullCheckMethodTransformer(GenerationState generationState) {
        generationState.getClass();
        this.generationState = generationState;
    }

    @Override // org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
    public void transform(String internalClassName, MethodNode methodNode) {
        internalClassName.getClass();
        methodNode.getClass();
        while (new TransformerPass(internalClassName, methodNode, this.generationState).run()) {
        }
    }
}
