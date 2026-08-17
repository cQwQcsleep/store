package org.jetbrains.kotlin.codegen.optimization.boxing;

import com.intellij.openapi.util.Pair;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.function.BiFunction;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.collections.MapsKt;
import kotlin.comparisons.ComparisonsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.boxing.RedundantBoxingMethodTransformer;
import org.jetbrains.kotlin.codegen.optimization.common.FastMethodAnalyzer;
import org.jetbrains.kotlin.codegen.optimization.common.StrictBasicValue;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.optimization.fixStack.StackTransformationUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.org.objectweb.asm.Label;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.InsnNode;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TypeInsnNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;
import org.jetbrains.org.objectweb.asm.tree.analysis.Frame;
import org.jetbrains.org.objectweb.asm.tree.analysis.Value;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008e\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0015\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0001\n\u0002\b\u000e\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u0018\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bH\u0016J\u0018\u0010\f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eH\u0002J*\u0010\u000f\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00130\u0011H\u0002J5\u0010\u0014\u001a\u00020\u00072\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\n\u001a\u00020\u000b2\u0016\u0010\u0017\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00190\u0018H\u0002¢\u0006\u0002\u0010\u001bJ3\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000b2\u0014\u0010\u0017\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00190\u0018H\u0002¢\u0006\u0002\u0010\u001eJ5\u0010\u001f\u001a\u00020 2\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000b2\u0016\u0010\u0017\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00190\u0018H\u0002¢\u0006\u0002\u0010!J\u0010\u0010\"\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u000eH\u0002J$\u0010#\u001a\u00020 2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00132\f\u0010%\u001a\b\u0012\u0004\u0012\u00020&0\u0013H\u0002J=\u0010'\u001a\u0014\u0012\u0004\u0012\u00020\u0012\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00120\u00130\u00112\u0006\u0010\n\u001a\u00020\u000b2\u0014\u0010\u0017\u001a\u0010\u0012\f\u0012\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00190\u0018H\u0002¢\u0006\u0002\u0010(J;\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001a0\u00132\u0006\u0010*\u001a\u00020\u00122\u0006\u0010\n\u001a\u00020\u000b2\u0016\u0010\u0017\u001a\u0012\u0012\u000e\b\u0001\u0012\n\u0012\u0004\u0012\u00020\u001a\u0018\u00010\u00190\u0018H\u0002¢\u0006\u0002\u0010+J\u0018\u0010,\u001a\u00020-2\u0006\u0010\u001d\u001a\u00020\u000e2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010.\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u000eH\u0002J\u0018\u0010/\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00100\u001a\u000201H\u0002J\u0018\u00102\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00100\u001a\u000201H\u0002J,\u00103\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00100\u001a\u0002012\u0012\u00104\u001a\u000e\u0012\u0004\u0012\u000206\u0012\u0004\u0012\u00020&05H\u0002J \u00107\u001a\u0002082\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00109\u001a\u0002062\u0006\u00100\u001a\u000201H\u0002J\u0010\u0010:\u001a\u00020;2\u0006\u00109\u001a\u000206H\u0002J \u0010<\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00109\u001a\u0002062\u0006\u00100\u001a\u000201H\u0002J\u0018\u0010=\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00109\u001a\u000206H\u0002J\u0018\u0010>\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00109\u001a\u000206H\u0002J(\u0010?\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00109\u001a\u0002062\u0006\u0010@\u001a\u0002082\u0006\u0010A\u001a\u000208H\u0002J \u0010B\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00109\u001a\u0002062\u0006\u0010C\u001a\u000208H\u0002J \u0010D\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00109\u001a\u0002062\u0006\u00100\u001a\u000201H\u0002J\u0018\u0010E\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00109\u001a\u000206H\u0002J\u0018\u0010F\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00109\u001a\u000206H\u0002J\u0018\u0010G\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00109\u001a\u000206H\u0002J\u0018\u0010H\u001a\u00020\u00072\u0006\u0010\n\u001a\u00020\u000b2\u0006\u00109\u001a\u000206H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006I"}, d2 = {"Lorg/jetbrains/kotlin/codegen/optimization/boxing/RedundantBoxingMethodTransformer;", "Lorg/jetbrains/kotlin/codegen/optimization/transformer/MethodTransformer;", "generationState", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "<init>", "(Lorg/jetbrains/kotlin/codegen/state/GenerationState;)V", "transform", Argument.Delimiters.none, "internalClassName", Argument.Delimiters.none, "node", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "sortAdaptableInstructionsForBoxedValues", "valuesToOptimize", "Lorg/jetbrains/kotlin/codegen/optimization/boxing/RedundantBoxedValuesCollection;", "replaceVariables", "variablesForReplacement", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/LocalVariableNode;", Argument.Delimiters.none, "interpretPopInstructionsForBoxedValues", "interpreter", "Lorg/jetbrains/kotlin/codegen/optimization/boxing/RedundantBoxingInterpreter;", "frames", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;", "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "(Lorg/jetbrains/kotlin/codegen/optimization/boxing/RedundantBoxingInterpreter;Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;[Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;)V", "removeValuesClashingWithVariables", "values", "(Lorg/jetbrains/kotlin/codegen/optimization/boxing/RedundantBoxedValuesCollection;Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;[Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;)V", "removeValuesClashingWithVariablesPass", Argument.Delimiters.none, "(Lorg/jetbrains/kotlin/codegen/optimization/boxing/RedundantBoxedValuesCollection;Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;[Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;)Z", "removeValuesFromTaintedProgressionIterators", "isUnsafeToRemoveBoxingForConnectedValues", "usedValues", "unboxedTypes", "Lorg/jetbrains/org/objectweb/asm/Type;", "adaptLocalSingleVariableTableForBoxedValuesAndPrepareMultiVariables", "(Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;[Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;)Ljava/util/Map;", "getValuesStoredOrLoadedToVariable", "localVariableNode", "(Lorg/jetbrains/org/objectweb/asm/tree/LocalVariableNode;Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;[Lorg/jetbrains/org/objectweb/asm/tree/analysis/Frame;)Ljava/util/List;", "buildVariablesRemapping", Argument.Delimiters.none, "adaptInstructionsForBoxedValues", "adaptInstructionsForBoxedValue", "value", "Lorg/jetbrains/kotlin/codegen/optimization/boxing/BoxedValueDescriptor;", "adaptBoxingInstruction", "adaptCastInstruction", "castWithType", "Lcom/intellij/openapi/util/Pair;", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "adaptInstruction", Argument.Delimiters.none, "insn", "throwCannotAdaptInstruction", Argument.Delimiters.none, "adaptAreEqualIntrinsic", "adaptAreEqualIntrinsicForInt", "adaptAreEqualIntrinsicForLong", "fuseAreEqualWithBranch", "ifEqualOpcode", "ifNotEqualOpcode", "ifEqual1Else0", "ifneOpcode", "adaptJavaLangComparableCompareTo", "adaptJavaLangComparableCompareToForInt", "adaptJavaLangComparableCompareToForLong", "adaptJavaLangComparableCompareToForFloat", "adaptJavaLangComparableCompareToForDouble", "org.jetbrains.kotlin:backend"}, k = 1, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class RedundantBoxingMethodTransformer extends MethodTransformer {
    private final GenerationState generationState;

    public RedundantBoxingMethodTransformer(GenerationState generationState) {
        generationState.getClass();
        this.generationState = generationState;
    }

    public static AbstractInsnNode a(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return abstractInsnNode.getPrevious();
    }

    private final void adaptAreEqualIntrinsic(MethodNode node, AbstractInsnNode insn, BoxedValueDescriptor value) {
        Type type = (Type) CollectionsKt.singleOrNull(value.getUnboxedTypes());
        Integer numValueOf = type != null ? Integer.valueOf(type.getSort()) : null;
        if ((numValueOf != null && numValueOf.intValue() == 1) || ((numValueOf != null && numValueOf.intValue() == 3) || ((numValueOf != null && numValueOf.intValue() == 4) || ((numValueOf != null && numValueOf.intValue() == 5) || (numValueOf != null && numValueOf.intValue() == 2))))) {
            adaptAreEqualIntrinsicForInt(node, insn);
            return;
        }
        if (numValueOf != null && numValueOf.intValue() == 7) {
            adaptAreEqualIntrinsicForLong(node, insn);
        } else {
            if ((numValueOf != null && numValueOf.intValue() == 10) || numValueOf == null) {
                return;
            }
            s22.a("Unexpected unboxed type kind: ", type);
        }
    }

    private final void adaptAreEqualIntrinsicForInt(MethodNode node, AbstractInsnNode insn) {
        InsnList insnList = node.instructions;
        AbstractInsnNode next = insn.getNext();
        if (next == null || !(next.getOpcode() == 153 || next.getOpcode() == 154)) {
            ifEqual1Else0(node, insn, 160);
            insnList.remove(insn);
        } else {
            fuseAreEqualWithBranch(node, insn, 160, 159);
            insnList.remove(insn);
            insnList.remove(next);
        }
    }

    private final void adaptAreEqualIntrinsicForLong(MethodNode node, AbstractInsnNode insn) {
        InsnList insnList = node.instructions;
        insnList.insertBefore(insn, new InsnNode(148));
        AbstractInsnNode next = insn.getNext();
        if (next == null || !(next.getOpcode() == 153 || next.getOpcode() == 154)) {
            ifEqual1Else0(node, insn, 154);
            insnList.remove(insn);
        } else {
            fuseAreEqualWithBranch(node, insn, 154, 153);
            insnList.remove(insn);
            insnList.remove(next);
        }
    }

    private final void adaptBoxingInstruction(MethodNode node, BoxedValueDescriptor value) {
        if (!value.isFromProgressionIterator()) {
            node.instructions.remove(value.getBoxingInsn());
            return;
        }
        ProgressionIteratorBasicValue progressionIterator = value.getProgressionIterator();
        if (progressionIterator == null) {
            k2d.a("iterator should not be null because isFromProgressionIterator returns true");
        } else {
            node.instructions.insertBefore(value.getBoxingInsn(), new TypeInsnNode(192, progressionIterator.getType().getInternalName()));
            node.instructions.set(value.getBoxingInsn(), new MethodInsnNode(182, progressionIterator.getType().getInternalName(), progressionIterator.getNextMethodName(), progressionIterator.getNextMethodDesc(), false));
        }
    }

    private final void adaptCastInstruction(MethodNode node, BoxedValueDescriptor value, Pair<AbstractInsnNode, Type> castWithType) {
        AbstractInsnNode abstractInsnNode = (AbstractInsnNode) castWithType.getFirst();
        MethodNode methodNode = new MethodNode(589824);
        new InstructionAdapter(methodNode).cast(value.getUnboxTypeOrOtherwiseMethodReturnType(abstractInsnNode instanceof MethodInsnNode ? (MethodInsnNode) abstractInsnNode : null), (Type) castWithType.getSecond());
        AbstractInsnNode[] array = methodNode.instructions.toArray();
        array.getClass();
        for (AbstractInsnNode abstractInsnNode2 : array) {
            node.instructions.insertBefore(abstractInsnNode, abstractInsnNode2);
        }
        node.instructions.remove(abstractInsnNode);
    }

    private final int adaptInstruction(MethodNode node, AbstractInsnNode insn, BoxedValueDescriptor value) {
        AbstractInsnNode abstractInsnNode = insn;
        Ref.IntRef intRef = new Ref.IntRef();
        int opcode = abstractInsnNode.getOpcode();
        int size = 0;
        if (opcode == 25 || opcode == 58) {
            boolean z = abstractInsnNode.getOpcode() == 58;
            Type type = (Type) CollectionsKt.singleOrNull(value.getUnboxedTypes());
            if (type == null) {
                ArrayList arrayList = new ArrayList();
                for (Type type2 : value.getUnboxedTypes()) {
                    arrayList.add(new VarInsnNode(type2.getOpcode(z ? 54 : 21), ((VarInsnNode) abstractInsnNode).var + size));
                    size += type2.getSize();
                }
                if (z) {
                    List listAsReversed = CollectionsKt.asReversed(SequencesKt.toList(SequencesKt.take(SequencesKt.generateSequence(abstractInsnNode.getPrevious(), new Function1() { // from class: o8c
                        public final Object invoke(Object obj) {
                            return RedundantBoxingMethodTransformer.a((AbstractInsnNode) obj);
                        }
                    }), value.getUnboxedTypes().size())));
                    List<Type> unboxedTypes = value.getUnboxedTypes();
                    ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(unboxedTypes, 10));
                    Iterator<T> it = unboxedTypes.iterator();
                    while (it.hasNext()) {
                        arrayList2.add(Integer.valueOf(((Type) it.next()).getOpcode(21)));
                    }
                    List list = listAsReversed;
                    ArrayList arrayList3 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list, 10));
                    Iterator it2 = list.iterator();
                    while (it2.hasNext()) {
                        arrayList3.add(Integer.valueOf(((AbstractInsnNode) it2.next()).getOpcode()));
                    }
                    if (Intrinsics.areEqual(arrayList2, arrayList3)) {
                        for (kotlin.Pair pair : CollectionsKt.zip(list, arrayList)) {
                            AbstractInsnNode abstractInsnNode2 = (AbstractInsnNode) pair.component1();
                            VarInsnNode varInsnNode = (VarInsnNode) pair.component2();
                            arrayList.remove(varInsnNode);
                            node.instructions.insert(abstractInsnNode2, varInsnNode);
                        }
                    } else {
                        Iterator it3 = CollectionsKt.asReversedMutable(arrayList).iterator();
                        while (it3.hasNext()) {
                            node.instructions.insertBefore(abstractInsnNode, (VarInsnNode) it3.next());
                        }
                    }
                } else {
                    Iterator it4 = arrayList.iterator();
                    while (it4.hasNext()) {
                        node.instructions.insertBefore(abstractInsnNode, (VarInsnNode) it4.next());
                    }
                }
                node.instructions.remove(abstractInsnNode);
            } else {
                node.instructions.set(abstractInsnNode, new VarInsnNode(type.getOpcode(z ? 54 : 21), ((VarInsnNode) abstractInsnNode).var));
            }
        } else if (opcode == 87) {
            node.instructions.insert(abstractInsnNode, BoxedBasicValueKt.makePops(value.getUnboxedTypes()));
            node.instructions.remove(abstractInsnNode);
        } else if (opcode == 89) {
            int totalUnboxSize = value.getTotalUnboxSize();
            if (totalUnboxSize != 1) {
                if (totalUnboxSize != 2) {
                    intRef.element = value.getTotalUnboxSize();
                    int i = node.maxLocals;
                    List<Type> unboxedTypes2 = value.getUnboxedTypes();
                    ArrayList arrayList4 = new ArrayList(CollectionsKt.collectionSizeOrDefault(unboxedTypes2, 10));
                    Iterator<T> it5 = unboxedTypes2.iterator();
                    while (it5.hasNext()) {
                        int size2 = ((Type) it5.next()).getSize() + i;
                        arrayList4.add(Integer.valueOf(i));
                        i = size2;
                    }
                    for (kotlin.Pair pair2 : CollectionsKt.asReversed(CollectionsKt.zip(value.getUnboxedTypes(), arrayList4))) {
                        node.instructions.insertBefore(abstractInsnNode, new VarInsnNode(((Type) pair2.component1()).getOpcode(54), ((Number) pair2.component2()).intValue()));
                    }
                    while (size < 2) {
                        for (kotlin.Pair pair3 : CollectionsKt.zip(value.getUnboxedTypes(), arrayList4)) {
                            node.instructions.insertBefore(abstractInsnNode, new VarInsnNode(((Type) pair3.component1()).getOpcode(21), ((Number) pair3.component2()).intValue()));
                        }
                        size++;
                    }
                    node.instructions.remove(abstractInsnNode);
                } else {
                    node.instructions.set(abstractInsnNode, new InsnNode(92));
                }
            }
        } else if (opcode == 182) {
            if (value.getUnboxedTypes().size() != 1) {
                MethodInsnNode methodInsnNode = (MethodInsnNode) abstractInsnNode;
                GenerationState.MultiFieldValueClassUnboxInfo multiFieldValueClassUnboxInfo = value.getMultiFieldValueClassUnboxInfo();
                multiFieldValueClassUnboxInfo.getClass();
                int iIndexOf = multiFieldValueClassUnboxInfo.getUnboxedMethodNames().indexOf(methodInsnNode.name);
                Type type3 = value.getUnboxedTypes().get(iIndexOf);
                Ref.BooleanRef booleanRef = new Ref.BooleanRef();
                booleanRef.element = true;
                Ref.BooleanRef booleanRef2 = new Ref.BooleanRef();
                for (IndexedValue indexedValue : CollectionsKt.asReversed(CollectionsKt.toList(CollectionsKt.withIndex(value.getUnboxedTypes())))) {
                    int index = indexedValue.getIndex();
                    Type type4 = (Type) indexedValue.component2();
                    if (index != iIndexOf) {
                        if (adaptInstruction$canRemoveInsn(booleanRef, index, iIndexOf, methodInsnNode, type4, index > iIndexOf)) {
                            InsnList insnList = node.instructions;
                            AbstractInsnNode previous = methodInsnNode.getPrevious();
                            if (index < iIndexOf) {
                                previous = previous.getPrevious();
                            }
                            insnList.remove(previous);
                        } else {
                            abstractInsnNode = insn;
                            adaptInstruction$saveToVariableIfNecessary(booleanRef2, index, iIndexOf, intRef, type3, node, abstractInsnNode);
                            adaptInstruction$insertPopInstruction(node, methodInsnNode, type4);
                        }
                    } else if (iIndexOf > 0 && !adaptInstruction$canRemoveInsn(booleanRef, index, iIndexOf, methodInsnNode, type4, false)) {
                        adaptInstruction$saveToVariableIfNecessary(booleanRef2, index, iIndexOf, intRef, type3, node, abstractInsnNode);
                    }
                    abstractInsnNode = insn;
                }
                if (booleanRef2.element) {
                    node.instructions.insertBefore(abstractInsnNode, new VarInsnNode(type3.getOpcode(21), node.maxLocals));
                }
            }
            node.instructions.remove(abstractInsnNode);
        } else if (opcode != 184) {
            if (opcode == 185) {
                if (!BoxingInterpreterKt.isJavaLangComparableCompareTo(abstractInsnNode)) {
                    throwCannotAdaptInstruction(abstractInsnNode);
                    wq6.a();
                    return 0;
                }
                adaptJavaLangComparableCompareTo(node, insn, value);
            } else if (opcode == 192) {
                node.instructions.remove(abstractInsnNode);
            } else {
                if (opcode != 193) {
                    throwCannotAdaptInstruction(abstractInsnNode);
                    wq6.a();
                    return 0;
                }
                node.instructions.insertBefore(abstractInsnNode, BoxedBasicValueKt.makePops(value.getUnboxedTypes()));
                node.instructions.set(abstractInsnNode, new InsnNode(4));
            }
        } else if (BoxingInterpreterKt.isAreEqualIntrinsic(abstractInsnNode)) {
            adaptAreEqualIntrinsic(node, insn, value);
        } else {
            if (!BoxingInterpreterKt.isJavaLangClassBoxing(abstractInsnNode) && !BoxingInterpreterKt.isJavaLangClassUnboxing(abstractInsnNode)) {
                throwCannotAdaptInstruction(abstractInsnNode);
                wq6.a();
                return 0;
            }
            node.instructions.remove(abstractInsnNode);
        }
        return intRef.element;
    }

    private static final boolean adaptInstruction$canRemoveInsn(Ref.BooleanRef booleanRef, int i, int i2, MethodInsnNode methodInsnNode, Type type, boolean z) {
        boolean z2 = false;
        if (!booleanRef.element) {
            return false;
        }
        int opcode = (i < i2 ? methodInsnNode.getPrevious().getPrevious() : methodInsnNode.getPrevious()).getOpcode();
        if (opcode == type.getOpcode(21) || (opcode != 92 ? !(opcode != 89 || !z || type.getSize() != 1) : !(!z || type.getSize() != 2))) {
            z2 = true;
        }
        booleanRef.element = z2;
        return z2;
    }

    private static final void adaptInstruction$insertPopInstruction(MethodNode methodNode, MethodInsnNode methodInsnNode, Type type) {
        methodNode.instructions.insertBefore(methodInsnNode, new InsnNode(type.getSize() == 2 ? 88 : 87));
    }

    private static final void adaptInstruction$saveToVariableIfNecessary(Ref.BooleanRef booleanRef, int i, int i2, Ref.IntRef intRef, Type type, MethodNode methodNode, AbstractInsnNode abstractInsnNode) {
        if (!booleanRef.element && i <= i2) {
            booleanRef.element = true;
            intRef.element = type.getSize();
            methodNode.instructions.insertBefore(abstractInsnNode, new VarInsnNode(type.getOpcode(54), methodNode.maxLocals));
        }
    }

    private final void adaptInstructionsForBoxedValue(MethodNode node, BoxedValueDescriptor value) {
        adaptBoxingInstruction(node, value);
        Iterator<Pair<AbstractInsnNode, Type>> it = value.getUnboxingWithCastInsns().iterator();
        while (it.hasNext()) {
            adaptCastInstruction(node, value, it.next());
        }
        Iterator<AbstractInsnNode> it2 = value.getAssociatedInsns().iterator();
        int iMax = 0;
        while (it2.hasNext()) {
            iMax = Math.max(iMax, adaptInstruction(node, it2.next(), value));
        }
        node.maxLocals += iMax;
    }

    private final void adaptInstructionsForBoxedValues(MethodNode node, RedundantBoxedValuesCollection values) {
        Iterator<BoxedValueDescriptor> it = values.iterator();
        it.getClass();
        while (it.hasNext()) {
            BoxedValueDescriptor next = it.next();
            next.getClass();
            adaptInstructionsForBoxedValue(node, next);
        }
    }

    private final void adaptJavaLangComparableCompareTo(MethodNode node, AbstractInsnNode insn, BoxedValueDescriptor value) {
        Type type = (Type) CollectionsKt.single(value.getUnboxedTypes());
        switch (type.getSort()) {
            case 1:
            case MavenComparableVersion.Item.LIST_ITEM /* 2 */:
            case 3:
            case 4:
            case 5:
                adaptJavaLangComparableCompareToForInt(node, insn);
                break;
            case 6:
                adaptJavaLangComparableCompareToForFloat(node, insn);
                break;
            case 7:
                adaptJavaLangComparableCompareToForLong(node, insn);
                break;
            case 8:
                adaptJavaLangComparableCompareToForDouble(node, insn);
                break;
            default:
                s22.a("Unexpected unboxed type kind: ", type);
                break;
        }
    }

    private final void adaptJavaLangComparableCompareToForDouble(MethodNode node, AbstractInsnNode insn) {
        node.instructions.set(insn, new MethodInsnNode(184, "java/lang/Double", "compare", "(DD)I", false));
    }

    private final void adaptJavaLangComparableCompareToForFloat(MethodNode node, AbstractInsnNode insn) {
        node.instructions.set(insn, new MethodInsnNode(184, "java/lang/Float", "compare", "(FF)I", false));
    }

    private final void adaptJavaLangComparableCompareToForInt(MethodNode node, AbstractInsnNode insn) {
        node.instructions.set(insn, new MethodInsnNode(184, "kotlin/jvm/internal/Intrinsics", "compare", "(II)I", false));
    }

    private final void adaptJavaLangComparableCompareToForLong(MethodNode node, AbstractInsnNode insn) {
        node.instructions.set(insn, new InsnNode(148));
    }

    private final Map<LocalVariableNode, List<LocalVariableNode>> adaptLocalSingleVariableTableForBoxedValuesAndPrepareMultiVariables(MethodNode node, Frame<BasicValue>[] frames) {
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        for (LocalVariableNode localVariableNode : node.localVariables) {
            if (Type.getType(localVariableNode.desc).getSort() == 10) {
                for (BasicValue basicValue : getValuesStoredOrLoadedToVariable(localVariableNode, node, frames)) {
                    if (basicValue instanceof BoxedBasicValue) {
                        BoxedValueDescriptor descriptor = ((BoxedBasicValue) basicValue).getDescriptor();
                        if (descriptor.getIsSafeToRemove()) {
                            Type type = (Type) CollectionsKt.singleOrNull(descriptor.getUnboxedTypes());
                            if (type == null) {
                                GenerationState.MultiFieldValueClassUnboxInfo multiFieldValueClassUnboxInfo = descriptor.getMultiFieldValueClassUnboxInfo();
                                multiFieldValueClassUnboxInfo.getClass();
                                List<Triple<Type, String, String>> unboxedTypesAndMethodNamesAndFieldNames = multiFieldValueClassUnboxInfo.getUnboxedTypesAndMethodNamesAndFieldNames();
                                ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(unboxedTypesAndMethodNamesAndFieldNames, 10));
                                Iterator<T> it = unboxedTypesAndMethodNamesAndFieldNames.iterator();
                                int i = 0;
                                while (it.hasNext()) {
                                    Triple triple = (Triple) it.next();
                                    Type type2 = (Type) triple.component1();
                                    String str = localVariableNode.name + '-' + ((String) triple.component3());
                                    LabelNode labelNode = localVariableNode.start;
                                    LabelNode labelNode2 = localVariableNode.end;
                                    int size = i + type2.getSize();
                                    arrayList.add(new LocalVariableNode(str, type2.getDescriptor(), null, labelNode, labelNode2, i));
                                    i = size;
                                }
                                linkedHashMap.put(localVariableNode, arrayList);
                            } else {
                                localVariableNode.desc = type.getDescriptor();
                            }
                        }
                    }
                }
            }
        }
        return linkedHashMap;
    }

    public static Frame b(int i, int i2) {
        return new BoxingFrame(i, i2);
    }

    private final int[] buildVariablesRemapping(RedundantBoxedValuesCollection values, MethodNode node) {
        HashMap map = new HashMap();
        Iterator<BoxedValueDescriptor> it = values.iterator();
        it.getClass();
        while (it.hasNext()) {
            BoxedValueDescriptor next = it.next();
            int totalUnboxSize = next.getTotalUnboxSize();
            if (totalUnboxSize >= 2) {
                Iterator<Integer> it2 = next.getVariablesIndexes().iterator();
                while (it2.hasNext()) {
                    Integer numValueOf = Integer.valueOf(it2.next().intValue());
                    Integer numValueOf2 = Integer.valueOf(totalUnboxSize - 1);
                    final AnonymousClass1 anonymousClass1 = AnonymousClass1.INSTANCE;
                    map.merge(numValueOf, numValueOf2, new BiFunction() { // from class: n8c
                        @Override // java.util.function.BiFunction
                        public final Object apply(Object obj, Object obj2) {
                            return RedundantBoxingMethodTransformer.c(anonymousClass1, obj, obj2);
                        }
                    });
                }
            }
        }
        int i = node.maxLocals;
        Collection collectionValues = map.values();
        collectionValues.getClass();
        int iSumOfInt = i + CollectionsKt.sumOfInt(collectionValues);
        node.maxLocals = iSumOfInt;
        int[] iArr = new int[iSumOfInt];
        for (int i2 = 0; i2 < iSumOfInt; i2++) {
            iArr[i2] = i2;
        }
        for (Map.Entry entry : map.entrySet()) {
            int iIntValue = ((Number) entry.getKey()).intValue();
            int iIntValue2 = ((Number) entry.getValue()).intValue();
            int i3 = iIntValue + 1;
            int lastIndex = ArraysKt.getLastIndex(iArr);
            if (i3 <= lastIndex) {
                while (true) {
                    iArr[i3] = iArr[i3] + iIntValue2;
                    if (i3 != lastIndex) {
                        i3++;
                    }
                }
            }
        }
        return iArr;
    }

    public static Integer c(Function2 function2, Object obj, Object obj2) {
        return (Integer) function2.invoke(obj, obj2);
    }

    private final void fuseAreEqualWithBranch(MethodNode node, AbstractInsnNode insn, int ifEqualOpcode, int ifNotEqualOpcode) {
        InsnList insnList = node.instructions;
        JumpInsnNode next = insn.getNext();
        next.getClass();
        JumpInsnNode jumpInsnNode = next;
        LabelNode labelNode = jumpInsnNode.label;
        if (jumpInsnNode.getOpcode() == 153) {
            insnList.insertBefore(insn, new JumpInsnNode(ifEqualOpcode, labelNode));
        } else if (jumpInsnNode.getOpcode() == 154) {
            insnList.insertBefore(insn, new JumpInsnNode(ifNotEqualOpcode, labelNode));
        } else {
            pe1.a("IFEQ or IFNE expected: ", InlineCodegenUtilsKt.getInsnOpcodeText(next));
        }
    }

    private final List<BasicValue> getValuesStoredOrLoadedToVariable(LocalVariableNode localVariableNode, MethodNode node, Frame<BasicValue>[] frames) {
        Frame<BasicValue> frame;
        BasicValue local;
        ArrayList arrayList = new ArrayList();
        InsnList insnList = node.instructions;
        int iIndexOf = insnList.indexOf(localVariableNode.start);
        int iIndexOf2 = insnList.indexOf(localVariableNode.end);
        Frame<BasicValue> frame2 = frames[iIndexOf];
        if (frame2 != null && (local = frame2.getLocal(localVariableNode.index)) != null) {
            arrayList.add(local);
        }
        while (iIndexOf < iIndexOf2) {
            if (iIndexOf >= 0 && iIndexOf < insnList.size() && (frame = frames[iIndexOf]) != null) {
                VarInsnNode varInsnNode = insnList.get(iIndexOf);
                if (varInsnNode.getOpcode() == 58 || varInsnNode.getOpcode() == 25) {
                    VarInsnNode varInsnNode2 = varInsnNode;
                    if (varInsnNode2.var == localVariableNode.index) {
                        if (varInsnNode2.getOpcode() == 58) {
                            Value pVar = StackTransformationUtilsKt.top(frame);
                            pVar.getClass();
                            arrayList.add(pVar);
                        } else {
                            arrayList.add(frame.getLocal(varInsnNode2.var));
                        }
                    }
                }
            }
            iIndexOf++;
        }
        return arrayList;
    }

    private final void ifEqual1Else0(MethodNode node, AbstractInsnNode insn, int ifneOpcode) {
        InsnList insnList = node.instructions;
        LabelNode labelNode = new LabelNode(new Label());
        LabelNode labelNode2 = new LabelNode(new Label());
        insnList.insertBefore(insn, new JumpInsnNode(ifneOpcode, labelNode));
        insnList.insertBefore(insn, new InsnNode(4));
        insnList.insertBefore(insn, new JumpInsnNode(167, labelNode2));
        insnList.insertBefore(insn, labelNode);
        insnList.insertBefore(insn, new InsnNode(3));
        insnList.insertBefore(insn, labelNode2);
    }

    private final void interpretPopInstructionsForBoxedValues(RedundantBoxingInterpreter interpreter, MethodNode node, Frame<BasicValue>[] frames) {
        Frame<BasicValue> frame;
        int length = frames.length;
        for (int i = 0; i < length; i++) {
            AbstractInsnNode abstractInsnNode = node.instructions.get(i);
            if ((abstractInsnNode.getOpcode() == 87 || abstractInsnNode.getOpcode() == 88) && (frame = frames[i]) != null) {
                Value pVar = StackTransformationUtilsKt.top(frame);
                pVar.getClass();
                BasicValue basicValue = (BasicValue) pVar;
                interpreter.processPopInstruction(abstractInsnNode, basicValue);
                if (basicValue.getSize() == 1 && abstractInsnNode.getOpcode() == 88) {
                    Value valuePeek = StackTransformationUtilsKt.peek(frame, 1);
                    valuePeek.getClass();
                    interpreter.processPopInstruction(abstractInsnNode, (BasicValue) valuePeek);
                }
            }
        }
    }

    private final boolean isUnsafeToRemoveBoxingForConnectedValues(List<? extends BasicValue> usedValues, List<Type> unboxedTypes) {
        List<? extends BasicValue> list = usedValues;
        if ((list instanceof Collection) && list.isEmpty()) {
            return false;
        }
        for (BasicValue basicValue : list) {
            if (basicValue != StrictBasicValue.UNINITIALIZED_VALUE) {
                if (!(basicValue instanceof CleanBoxedValue)) {
                    return true;
                }
                BoxedValueDescriptor descriptor = ((CleanBoxedValue) basicValue).getDescriptor();
                if (!descriptor.getIsSafeToRemove() || !Intrinsics.areEqual(descriptor.getUnboxedTypes(), unboxedTypes)) {
                    return true;
                }
            }
        }
        return false;
    }

    private final void removeValuesClashingWithVariables(RedundantBoxedValuesCollection values, MethodNode node, Frame<BasicValue>[] frames) {
        while (removeValuesClashingWithVariablesPass(values, node, frames)) {
        }
    }

    private final boolean removeValuesClashingWithVariablesPass(RedundantBoxedValuesCollection values, MethodNode node, Frame<BasicValue>[] frames) {
        boolean z = false;
        for (LocalVariableNode localVariableNode : node.localVariables) {
            if (Type.getType(localVariableNode.desc).getSort() == 10) {
                List<BasicValue> valuesStoredOrLoadedToVariable = getValuesStoredOrLoadedToVariable(localVariableNode, node, frames);
                ArrayList arrayList = new ArrayList();
                for (Object obj : valuesStoredOrLoadedToVariable) {
                    if (obj instanceof BoxedBasicValue) {
                        arrayList.add(obj);
                    }
                }
                if (!arrayList.isEmpty() && isUnsafeToRemoveBoxingForConnectedValues(valuesStoredOrLoadedToVariable, ((BoxedBasicValue) CollectionsKt.first(arrayList)).getDescriptor().getUnboxedTypes())) {
                    Iterator it = arrayList.iterator();
                    while (it.hasNext()) {
                        BoxedValueDescriptor descriptor = ((BoxedBasicValue) it.next()).getDescriptor();
                        if (descriptor.getIsSafeToRemove()) {
                            values.remove(descriptor);
                            z = true;
                        }
                    }
                }
            }
        }
        return z;
    }

    private final void removeValuesFromTaintedProgressionIterators(RedundantBoxedValuesCollection valuesToOptimize) {
        ProgressionIteratorBasicValue progressionIterator;
        for (BoxedValueDescriptor boxedValueDescriptor : CollectionsKt.toList(valuesToOptimize)) {
            if (boxedValueDescriptor != null && (progressionIterator = boxedValueDescriptor.getProgressionIterator()) != null && progressionIterator.getTainted()) {
                valuesToOptimize.remove(boxedValueDescriptor);
            }
        }
    }

    private final void replaceVariables(MethodNode node, Map<LocalVariableNode, ? extends List<? extends LocalVariableNode>> variablesForReplacement) {
        if (variablesForReplacement.isEmpty()) {
            return;
        }
        List<LocalVariableNode> list = node.localVariables;
        list.getClass();
        ArrayList arrayList = new ArrayList();
        for (LocalVariableNode localVariableNode : list) {
            List<? extends LocalVariableNode> listListOf = variablesForReplacement.get(localVariableNode);
            if (listListOf != null) {
                Iterator<? extends LocalVariableNode> it = listListOf.iterator();
                while (it.hasNext()) {
                    it.next().index += localVariableNode.index;
                }
            } else {
                listListOf = CollectionsKt.listOf(localVariableNode);
            }
            CollectionsKt.addAll(arrayList, listListOf);
        }
        node.localVariables = CollectionsKt.toMutableList(arrayList);
    }

    private final void sortAdaptableInstructionsForBoxedValues(MethodNode node, RedundantBoxedValuesCollection valuesToOptimize) {
        InsnList insnList = node.instructions;
        insnList.getClass();
        Iterable<IndexedValue> iterableWithIndex = CollectionsKt.withIndex(insnList);
        LinkedHashMap linkedHashMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(iterableWithIndex, 10)), 16));
        for (IndexedValue indexedValue : iterableWithIndex) {
            kotlin.Pair pair = TuplesKt.to((AbstractInsnNode) indexedValue.component2(), Integer.valueOf(indexedValue.getIndex()));
            linkedHashMap.put(pair.getFirst(), pair.getSecond());
        }
        Iterator<BoxedValueDescriptor> it = valuesToOptimize.iterator();
        it.getClass();
        while (it.hasNext()) {
            BoxedValueDescriptor next = it.next();
            next.sortAssociatedInsns(linkedHashMap);
            next.sortUnboxingWithCastInsns(linkedHashMap);
        }
    }

    private final Void throwCannotAdaptInstruction(AbstractInsnNode insn) {
        throw new AssertionError("Cannot adapt instruction: " + InlineCodegenUtilsKt.getInsnText(insn));
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: org.jetbrains.org.objectweb.asm.tree.analysis.AnalyzerException */
    @Override // org.jetbrains.kotlin.codegen.optimization.transformer.MethodTransformer
    public void transform(String internalClassName, MethodNode node) throws AnalyzerException {
        internalClassName.getClass();
        node.getClass();
        Collection<MethodInsnNode> collection = node.instructions;
        collection.getClass();
        if ((collection instanceof Collection) && collection.isEmpty()) {
            return;
        }
        for (MethodInsnNode methodInsnNode : collection) {
            methodInsnNode.getClass();
            if (BoxingInterpreterKt.isBoxing(methodInsnNode, this.generationState) || (methodInsnNode.getOpcode() == 185 && (methodInsnNode instanceof MethodInsnNode) && Intrinsics.areEqual(methodInsnNode.name, "next"))) {
                RedundantBoxingInterpreter redundantBoxingInterpreter = new RedundantBoxingInterpreter(node, this.generationState);
                Frame<BasicValue>[] frameArrAnalyze = new FastMethodAnalyzer(internalClassName, node, redundantBoxingInterpreter, false, new Function2() { // from class: m8c
                    public final Object invoke(Object obj, Object obj2) {
                        return RedundantBoxingMethodTransformer.b(((Integer) obj).intValue(), ((Integer) obj2).intValue());
                    }
                }).analyze();
                interpretPopInstructionsForBoxedValues(redundantBoxingInterpreter, node, frameArrAnalyze);
                RedundantBoxedValuesCollection candidatesBoxedValues = redundantBoxingInterpreter.getCandidatesBoxedValues();
                if (candidatesBoxedValues.isEmpty()) {
                    return;
                }
                removeValuesFromTaintedProgressionIterators(candidatesBoxedValues);
                removeValuesClashingWithVariables(candidatesBoxedValues, node, frameArrAnalyze);
                Map<LocalVariableNode, List<LocalVariableNode>> mapAdaptLocalSingleVariableTableForBoxedValuesAndPrepareMultiVariables = adaptLocalSingleVariableTableForBoxedValuesAndPrepareMultiVariables(node, frameArrAnalyze);
                UtilKt.remapLocalVariables(node, buildVariablesRemapping(candidatesBoxedValues, node));
                replaceVariables(node, mapAdaptLocalSingleVariableTableForBoxedValuesAndPrepareMultiVariables);
                sortAdaptableInstructionsForBoxedValues(node, candidatesBoxedValues);
                adaptInstructionsForBoxedValues(node, candidatesBoxedValues);
                return;
            }
        }
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.codegen.optimization.boxing.RedundantBoxingMethodTransformer$buildVariablesRemapping$1, reason: invalid class name */
    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function2<Integer, Integer, Integer> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(2, ComparisonsKt.class, "maxOf", "maxOf(II)I", 1);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj, Object obj2) {
            return invoke(((Number) obj).intValue(), ((Number) obj2).intValue());
        }

        public final Integer invoke(int i, int i2) {
            return Integer.valueOf(Math.max(i, i2));
        }
    }
}
