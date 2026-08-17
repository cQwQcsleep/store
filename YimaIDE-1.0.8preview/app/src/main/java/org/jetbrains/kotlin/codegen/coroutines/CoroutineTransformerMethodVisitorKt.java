package org.jetbrains.kotlin.codegen.coroutines;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IndexedValue;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.ClassBuilder;
import org.jetbrains.kotlin.codegen.InsnSequence;
import org.jetbrains.kotlin.codegen.InsnSequenceKt;
import org.jetbrains.kotlin.codegen.coroutines.CoroutineTransformerMethodVisitorKt;
import org.jetbrains.kotlin.codegen.inline.InlineCodegenUtilsKt;
import org.jetbrains.kotlin.codegen.optimization.common.UtilKt;
import org.jetbrains.kotlin.codegen.optimization.common.VariableLivenessFrame;
import org.jetbrains.kotlin.codegen.optimization.common.VariableLivenessKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.load.java.JvmAbi;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.InstructionAdapter;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\u008c\u0001\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\n\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0011\n\u0002\b\u0005\n\u0002\u0010\u001e\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u001a\u0012\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000f*\u00020\u0010H\u0002\u001a\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0010H\u0002\u001a@\u0010\u0013\u001a\u00020\u0012*\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u00192\b\u0010\u001a\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u001b\u001a\u00020\u00042\u0006\u0010\u001c\u001a\u00020\u001dH\u0002\u001a\u0014\u0010\u001e\u001a\u00020\u0004*\u00020\u00162\u0006\u0010\u001f\u001a\u00020\u0001H\u0002\u001a%\u0010 \u001a\u00020!2\u0017\u0010\"\u001a\u0013\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00120#¢\u0006\u0002\b$H\u0086\bø\u0001\u0000\u001a\n\u0010%\u001a\u00020\u0016*\u00020\u0016\u001a\u001b\u0010&\u001a\u00020\u0019*\b\u0012\u0004\u0012\u00020'0\u000f2\u0006\u0010(\u001a\u00020)H\u0080\u0002\u001a\u0018\u0010*\u001a\u00020\u00012\u0006\u0010+\u001a\u00020\u00042\u0006\u0010,\u001a\u00020\u0001H\u0000\u001a+\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00160.2\u0006\u0010+\u001a\u00020\u00042\u0006\u0010/\u001a\u00020\u00192\u0006\u00100\u001a\u00020\u0004H\u0002¢\u0006\u0002\u00101\u001a\u0010\u00102\u001a\u00020\u00192\u0006\u0010,\u001a\u00020\u0001H\u0002\u001a:\u00103\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000105042\u0006\u0010+\u001a\u00020\u00042\u0006\u00106\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u00100\u001a\u00020\u0004H\u0002\u001a+\u00107\u001a\b\u0012\u0004\u0012\u00020\u00160.2\u0006\u0010+\u001a\u00020\u00042\u0006\u0010/\u001a\u00020\u00192\u0006\u00100\u001a\u00020\u0004H\u0002¢\u0006\u0002\u00101\u001a\u0018\u00108\u001a\u00020\u00122\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u00109\u001a\u00020\u0001H\u0000\u001a\n\u0010:\u001a\u00020\u0004*\u00020\u0010\u001a\u001a\u0010;\u001a\u00020\u0004*\u00020\u00102\f\u0010<\u001a\b\u0012\u0004\u0012\u00020=0\u000fH\u0002\u001a&\u0010>\u001a\u00020\u00122\u0006\u0010?\u001a\u00020\u00102\u0006\u0010@\u001a\u00020\u00192\f\u0010A\u001a\b\u0012\u0004\u0012\u00020B0\u000fH\u0002\u001a\u001c\u0010C\u001a\u00020\u0012*\u00020\u00102\u0006\u0010D\u001a\u00020E2\u0006\u0010F\u001a\u00020\u0001H\u0002\u001a\f\u0010G\u001a\u00020\u0012*\u00020\u0010H\u0002\u001a\n\u0010H\u001a\u00020B*\u00020\u0010\u001a\n\u0010I\u001a\u00020B*\u00020\u0010\u001a@\u0010J\u001a\u00020\u0019*\u00020E2\u0006\u0010?\u001a\u00020\u00102\f\u0010A\u001a\b\u0012\u0004\u0012\u00020B0\u000f2\u0006\u0010K\u001a\u00020B2\f\u0010<\u001a\b\u0012\u0004\u0012\u00020=0\u000f2\u0006\u0010L\u001a\u00020\u0001H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0002\u001a\u00020\u0001X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\t\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\n\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\u000b\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\f\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006M"}, d2 = {"COROUTINES_DEBUG_METADATA_VERSION", Argument.Delimiters.none, "COROUTINES_DEBUG_METADATA_VERSION_BEFORE_2_3", "COROUTINES_METADATA_SOURCE_FILE_JVM_NAME", Argument.Delimiters.none, "COROUTINES_METADATA_LINE_NUMBERS_JVM_NAME", "COROUTINES_METADATA_NEXT_LINE_NUMBERS_JVM_NAME", "COROUTINES_METADATA_LOCAL_NAMES_JVM_NAME", "COROUTINES_METADATA_SPILLED_JVM_NAME", "COROUTINES_METADATA_INDEX_TO_LABEL_JVM_NAME", "COROUTINES_METADATA_METHOD_NAME_JVM_NAME", "COROUTINES_METADATA_CLASS_NAME_JVM_NAME", "COROUTINES_METADATA_VERSION_JVM_NAME", "WRAP_CONTINUATION_METHOD_DESCRIPTOR", "collectSuspendLambdaParameterSlots", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "extendSuspendLambdaParameterRanges", Argument.Delimiters.none, "generateContinuationConstructorCall", "Lorg/jetbrains/org/objectweb/asm/commons/InstructionAdapter;", "objectTypeForState", "Lorg/jetbrains/org/objectweb/asm/Type;", "methodNode", "needDispatchReceiver", Argument.Delimiters.none, "internalNameForDispatchReceiver", "containingClassInternalName", "classBuilderForCoroutineState", "Lorg/jetbrains/kotlin/codegen/ClassBuilder;", "fieldNameForVar", "index", "withInstructionAdapter", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "normalize", "contains", "Lorg/jetbrains/kotlin/codegen/coroutines/SuspensionPoint;", "insn", "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "getLastParameterIndex", "desc", "access", "getParameterTypesForCoroutineConstructor", Argument.Delimiters.none, "hasDispatchReceiver", "thisName", "(Ljava/lang/String;ZLjava/lang/String;)[Lorg/jetbrains/org/objectweb/asm/Type;", "isStatic", "getParameterTypesIndicesForCoroutineConstructor", Argument.Delimiters.none, "Lkotlin/Pair;", "containingFunctionAccess", "getAllParameterTypes", "replaceFakeContinuationsWithRealOnes", "continuationIndex", "nodeTextWithVisibleVariables", "nodeTextWithLiveness", "liveness", "Lorg/jetbrains/kotlin/codegen/optimization/common/VariableLivenessFrame;", "updateLvtAccordingToLiveness", "method", "isForNamedFunction", "suspensionPoints", "Lorg/jetbrains/org/objectweb/asm/tree/LabelNode;", "extendCompletionsRange", "completion", "Lorg/jetbrains/org/objectweb/asm/tree/LocalVariableNode;", "slot", "extendParameterRanges", "getOrCreateStartingLabel", "getOrCreateEndingLabel", "extendRecordIfPossible", "endLabel", "nextSuspensionPointIndex", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class CoroutineTransformerMethodVisitorKt {
    public static CharSequence a(MethodNode methodNode, IndexedValue indexedValue) {
        indexedValue.getClass();
        return nodeTextWithVisibleVariables$visibleVariables(methodNode, indexedValue.getIndex()) + InlineCodegenUtilsKt.getInsnText((AbstractInsnNode) indexedValue.component2());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final List<Integer> collectSuspendLambdaParameterSlots(MethodNode methodNode) {
        AbstractInsnNode previous;
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        ArrayList<AbstractInsnNode> arrayList = new ArrayList();
        for (Object obj : insnList) {
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode) obj;
            abstractInsnNode.getClass();
            if (InlineCodegenUtilsKt.isSuspendLambdaParameterMarker(abstractInsnNode)) {
                arrayList.add(obj);
            }
        }
        ArrayList arrayList2 = new ArrayList();
        for (AbstractInsnNode abstractInsnNode2 : arrayList) {
            AbstractInsnNode previous2 = (abstractInsnNode2 == null || (previous = abstractInsnNode2.getPrevious()) == null) ? null : previous.getPrevious();
            VarInsnNode varInsnNode = previous2 instanceof VarInsnNode ? (VarInsnNode) previous2 : null;
            Integer numValueOf = varInsnNode != null ? Integer.valueOf(varInsnNode.var) : null;
            if (numValueOf != null) {
                arrayList2.add(numValueOf);
            }
        }
        return CollectionsKt.toList(arrayList2);
    }

    public static final boolean contains(List<SuspensionPoint> list, AbstractInsnNode abstractInsnNode) {
        list.getClass();
        abstractInsnNode.getClass();
        List<SuspensionPoint> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return false;
        }
        Iterator<T> it = list2.iterator();
        while (it.hasNext()) {
            if (((SuspensionPoint) it.next()).contains(abstractInsnNode)) {
                return true;
            }
        }
        return false;
    }

    private static final void extendCompletionsRange(MethodNode methodNode, LocalVariableNode localVariableNode, int i) {
        localVariableNode.start = getOrCreateStartingLabel(methodNode);
        localVariableNode.end = getOrCreateEndingLabel(methodNode);
        localVariableNode.index = i;
        methodNode.localVariables.add(localVariableNode);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void extendParameterRanges(MethodNode methodNode) {
        Object next;
        LinkedHashSet linkedHashSet = new LinkedHashSet();
        LabelNode orCreateStartingLabel = getOrCreateStartingLabel(methodNode);
        LabelNode orCreateEndingLabel = getOrCreateEndingLabel(methodNode);
        String str = methodNode.desc;
        str.getClass();
        int lastParameterIndex = getLastParameterIndex(str, methodNode.access);
        if (lastParameterIndex >= 0) {
            int i = 0;
            while (true) {
                List list = methodNode.localVariables;
                list.getClass();
                Iterator it = list.iterator();
                do {
                    if (!it.hasNext()) {
                        next = null;
                        break;
                    }
                    next = it.next();
                } while (((LocalVariableNode) next).index != i);
                LocalVariableNode localVariableNode = (LocalVariableNode) next;
                if (localVariableNode != null) {
                    localVariableNode.start = orCreateStartingLabel;
                    localVariableNode.end = orCreateEndingLabel;
                    List list2 = methodNode.localVariables;
                    list2.getClass();
                    ArrayList arrayList = new ArrayList();
                    for (Object obj : list2) {
                        LocalVariableNode localVariableNode2 = (LocalVariableNode) obj;
                        if (localVariableNode2.index == i && !Intrinsics.areEqual(localVariableNode2, localVariableNode)) {
                            arrayList.add(obj);
                        }
                    }
                    CollectionsKt.addAll(linkedHashSet, arrayList);
                }
                if (i == lastParameterIndex) {
                    break;
                } else {
                    i++;
                }
            }
        }
        methodNode.localVariables.removeAll(linkedHashSet);
    }

    private static final boolean extendRecordIfPossible(LocalVariableNode localVariableNode, MethodNode methodNode, List<? extends LabelNode> list, LabelNode labelNode, List<VariableLivenessFrame> list2, int i) {
        Object next;
        LabelNode labelNode2;
        Iterator it = CollectionsKt.drop(list, i).iterator();
        do {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            labelNode2 = localVariableNode.end;
            labelNode2.getClass();
        } while (!SequencesKt.contains(new InsnSequence(labelNode2, labelNode), (LabelNode) next));
        LabelNode labelNode3 = (LabelNode) next;
        if (labelNode3 == null) {
            labelNode3 = labelNode;
        }
        VarInsnNode next2 = localVariableNode.end;
        int iIndexOf = methodNode.instructions.indexOf(next2);
        while (next2 != null && !Intrinsics.areEqual(next2, labelNode3)) {
            if (list2.get(iIndexOf).getControlFlowMerge()) {
                return false;
            }
            if (next2.getOpcode() == 176 && !Intrinsics.areEqual(labelNode3, labelNode)) {
                return false;
            }
            if (UtilKt.isStoreOperation(next2) && next2.var == iIndexOf) {
                return false;
            }
            next2 = next2.getNext();
            iIndexOf++;
        }
        localVariableNode.end = labelNode3;
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void extendSuspendLambdaParameterRanges(MethodNode methodNode) {
        AbstractInsnNode next;
        List<Integer> listCollectSuspendLambdaParameterSlots = collectSuspendLambdaParameterSlots(methodNode);
        if (listCollectSuspendLambdaParameterSlots.isEmpty()) {
            return;
        }
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        Object obj = null;
        for (Object obj2 : insnList) {
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode) obj2;
            abstractInsnNode.getClass();
            if (InlineCodegenUtilsKt.isSuspendLambdaParameterMarker(abstractInsnNode)) {
                obj = obj2;
            }
        }
        AbstractInsnNode abstractInsnNode2 = (AbstractInsnNode) obj;
        if (abstractInsnNode2 != null) {
            next = abstractInsnNode2.getNext();
            while (next != null && !(next instanceof LabelNode)) {
                next = next.getNext();
            }
        } else {
            next = null;
        }
        LabelNode labelNode = next instanceof LabelNode ? (LabelNode) next : null;
        if (labelNode == null) {
            return;
        }
        Iterator<Integer> it = listCollectSuspendLambdaParameterSlots.iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            List list = methodNode.localVariables;
            list.getClass();
            ArrayList arrayList = new ArrayList();
            for (Object obj3 : list) {
                if (((LocalVariableNode) obj3).index == iIntValue) {
                    arrayList.add(obj3);
                }
            }
            LocalVariableNode localVariableNode = (LocalVariableNode) CollectionsKt.firstOrNull(arrayList);
            if (localVariableNode != null) {
                methodNode.localVariables.removeAll(arrayList);
                localVariableNode.start = labelNode;
                localVariableNode.end = getOrCreateEndingLabel(methodNode);
                methodNode.localVariables.add(localVariableNode);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String fieldNameForVar(Type type, int i) {
        StringBuilder sb = new StringBuilder();
        String descriptor = type.getDescriptor();
        descriptor.getClass();
        sb.append(StringsKt.first(descriptor) + InlineCodegenUtilsKt.CAPTURED_FIELD_PREFIX);
        sb.append(i);
        return sb.toString();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final void generateContinuationConstructorCall(InstructionAdapter instructionAdapter, Type type, MethodNode methodNode, boolean z, String str, String str2, ClassBuilder classBuilder) {
        instructionAdapter.anew(type);
        instructionAdapter.dup();
        String str3 = methodNode.desc;
        str3.getClass();
        for (Pair<Type, Integer> pair : getParameterTypesIndicesForCoroutineConstructor(str3, methodNode.access, z, str == null ? str2 : str)) {
            instructionAdapter.load(((Number) pair.component2()).intValue(), (Type) pair.component1());
        }
        String thisName = classBuilder.getThisName();
        Type type2 = Type.VOID_TYPE;
        String str4 = methodNode.desc;
        str4.getClass();
        if (str == null) {
            str = str2;
        }
        Type[] parameterTypesForCoroutineConstructor = getParameterTypesForCoroutineConstructor(str4, z, str);
        instructionAdapter.invokespecial(thisName, "<init>", Type.getMethodDescriptor(type2, (Type[]) Arrays.copyOf(parameterTypesForCoroutineConstructor, parameterTypesForCoroutineConstructor.length)), false);
    }

    private static final Type[] getAllParameterTypes(String str, boolean z, String str2) {
        Object[] array = CollectionsKt.listOfNotNull(!z ? null : Type.getObjectType(str2)).toArray(new Type[0]);
        Type[] argumentTypes = Type.getArgumentTypes(str);
        argumentTypes.getClass();
        return (Type[]) ArraysKt.plus(array, argumentTypes);
    }

    public static final int getLastParameterIndex(String str, int i) {
        str.getClass();
        Type[] argumentTypes = Type.getArgumentTypes(str);
        argumentTypes.getClass();
        Iterator it = ArraysKt.dropLast(argumentTypes, 1).iterator();
        int size = 0;
        while (it.hasNext()) {
            size += ((Type) it.next()).getSize();
        }
        return size + (!isStatic(i) ? 1 : 0);
    }

    public static final LabelNode getOrCreateEndingLabel(MethodNode methodNode) {
        methodNode.getClass();
        LabelNode last = methodNode.instructions.getLast();
        if (last instanceof LabelNode) {
            return last;
        }
        LabelNode labelNode = new LabelNode();
        methodNode.instructions.insert(last, labelNode);
        return labelNode;
    }

    public static final LabelNode getOrCreateStartingLabel(MethodNode methodNode) {
        methodNode.getClass();
        LabelNode first = methodNode.instructions.getFirst();
        if (first instanceof LabelNode) {
            return first;
        }
        LabelNode labelNode = new LabelNode();
        methodNode.instructions.insertBefore(first, labelNode);
        return labelNode;
    }

    private static final Type[] getParameterTypesForCoroutineConstructor(String str, boolean z, String str2) {
        Object[] array = CollectionsKt.listOfNotNull(!z ? null : Type.getObjectType(str2)).toArray(new Type[0]);
        Type[] argumentTypes = Type.getArgumentTypes(str);
        argumentTypes.getClass();
        return (Type[]) ArraysKt.plus(array, ArraysKt.last(argumentTypes));
    }

    private static final Collection<Pair<Type, Integer>> getParameterTypesIndicesForCoroutineConstructor(String str, int i, boolean z, String str2) {
        ArrayList arrayList = new ArrayList();
        int size = 0;
        if (z) {
            arrayList.add(TuplesKt.to(Type.getObjectType(str2), 0));
        }
        Iterator it = ArraysKt.dropLast(getAllParameterTypes(str, !isStatic(i), str2), 1).iterator();
        while (it.hasNext()) {
            size += ((Type) it.next()).getSize();
        }
        arrayList.add(TuplesKt.to(CoroutineCodegenUtilKt.CONTINUATION_ASM_TYPE, Integer.valueOf(size)));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isStatic(int i) {
        return (i & 8) != 0;
    }

    public static final String nodeTextWithVisibleVariables(final MethodNode methodNode) {
        methodNode.getClass();
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        return CollectionsKt.joinToString$default(CollectionsKt.withIndex(insnList), "\n", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, new Function1() { // from class: t13
            public final Object invoke(Object obj) {
                return CoroutineTransformerMethodVisitorKt.a(methodNode, (IndexedValue) obj);
            }
        }, 30, (Object) null);
    }

    private static final String nodeTextWithVisibleVariables$visibleVariables(MethodNode methodNode, int i) {
        int i2;
        int i3 = methodNode.maxLocals;
        char[] cArr = new char[i3];
        for (int i4 = 0; i4 < i3; i4++) {
            List list = methodNode.localVariables;
            list.getClass();
            List<LocalVariableNode> list2 = list;
            if ((list2 instanceof Collection) && list2.isEmpty()) {
                i2 = 0;
            } else {
                i2 = 0;
                for (LocalVariableNode localVariableNode : list2) {
                    if (localVariableNode.index == i4 && methodNode.instructions.indexOf(localVariableNode.start) <= i && i < methodNode.instructions.indexOf(localVariableNode.end) && (i2 = i2 + 1) < 0) {
                        CollectionsKt.throwCountOverflow();
                    }
                }
            }
            cArr[i4] = i2 == 0 ? ' ' : String.valueOf(i2).charAt(0);
        }
        return new String(cArr).concat("|");
    }

    public static final Type normalize(Type type) {
        type.getClass();
        int sort = type.getSort();
        if (sort != 9 && sort != 10) {
            return type;
        }
        Type type2 = AsmTypes.OBJECT_TYPE;
        type2.getClass();
        return type2;
    }

    public static final void replaceFakeContinuationsWithRealOnes(MethodNode methodNode, int i) {
        methodNode.getClass();
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        for (AbstractInsnNode abstractInsnNode : SequencesKt.toList(SequencesKt.filter(InsnSequenceKt.asSequence(insnList), CoroutineTransformerMethodVisitorKt$replaceFakeContinuationsWithRealOnes$fakeContinuations$1.INSTANCE))) {
            InsnList insnList2 = methodNode.instructions;
            insnList2.getClass();
            UtilKt.removeAll(insnList2, CollectionsKt.listOf(new AbstractInsnNode[]{abstractInsnNode.getPrevious().getPrevious(), abstractInsnNode.getPrevious()}));
            methodNode.instructions.set(abstractInsnNode, new VarInsnNode(25, i));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Code duplicated, block: B:50:0x00c6  */
    /* JADX WARN: Code duplicated, block: B:64:0x00fe  */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1, types: [int] */
    /* JADX WARN: Type inference failed for: r9v3 */
    public static final void updateLvtAccordingToLiveness(MethodNode methodNode, boolean z, List<? extends LabelNode> list) {
        List<? extends LabelNode> list2;
        int i;
        LocalVariableNode localVariableNodeUpdateLvtAccordingToLiveness$findRecord;
        LabelNode labelNodeUpdateLvtAccordingToLiveness$min;
        boolean zExtendRecordIfPossible;
        List<VariableLivenessFrame> listAnalyzeLiveness = VariableLivenessKt.analyzeLiveness(methodNode);
        ArrayList arrayList = new ArrayList();
        Iterator it = methodNode.localVariables.iterator();
        while (it.hasNext()) {
            arrayList.add((LocalVariableNode) it.next());
        }
        methodNode.localVariables.clear();
        LinkedHashMap linkedHashMap = new LinkedHashMap();
        int i2 = methodNode.maxLocals;
        for (?? r9 = !z; r9 < i2; r9++) {
            if (!arrayList.isEmpty()) {
                Iterator it2 = arrayList.iterator();
                while (it2.hasNext()) {
                    if (((LocalVariableNode) it2.next()).index == r9) {
                        int size = methodNode.instructions.size() - 1;
                        int i3 = 0;
                        LabelNode labelNode = null;
                        int i4 = 0;
                        while (i4 < size) {
                            AbstractInsnNode abstractInsnNode = methodNode.instructions.get(i4);
                            boolean z2 = abstractInsnNode instanceof LabelNode;
                            if (!z2 || i3 >= list.size()) {
                                list2 = list;
                            } else {
                                list2 = list;
                                if (Intrinsics.areEqual(list2.get(i3), abstractInsnNode)) {
                                    i3++;
                                }
                            }
                            if (!updateLvtAccordingToLiveness$isAlive(listAnalyzeLiveness, i4, r9) && updateLvtAccordingToLiveness$isAlive(listAnalyzeLiveness, i4 + 1, r9)) {
                                labelNode = z2 ? (LabelNode) abstractInsnNode : null;
                                if (labelNode == null) {
                                    abstractInsnNode.getClass();
                                    AbstractInsnNode next = abstractInsnNode.getNext();
                                    while (next != null && !(next instanceof LabelNode)) {
                                        next = next.getNext();
                                    }
                                    labelNode = next instanceof LabelNode ? (LabelNode) next : null;
                                }
                            }
                            LabelNode labelNode2 = labelNode;
                            if (!updateLvtAccordingToLiveness$isAlive(listAnalyzeLiveness, i4, r9) || updateLvtAccordingToLiveness$isAlive(listAnalyzeLiveness, i4 + 1, r9) || (localVariableNodeUpdateLvtAccordingToLiveness$findRecord = updateLvtAccordingToLiveness$findRecord(arrayList, methodNode, i4, r9)) == null || Intrinsics.areEqual(localVariableNodeUpdateLvtAccordingToLiveness$findRecord.name, "$continuation") || Intrinsics.areEqual(localVariableNodeUpdateLvtAccordingToLiveness$findRecord.name, CoroutineConstantsKt.SUSPEND_CALL_RESULT_NAME)) {
                                i = i3;
                            } else {
                                String str = localVariableNodeUpdateLvtAccordingToLiveness$findRecord.name;
                                str.getClass();
                                if (JvmAbi.isFakeLocalVariableForInline(str)) {
                                    i = i3;
                                } else {
                                    LabelNode labelNodeUpdateLvtAccordingToLiveness$nextLabel = updateLvtAccordingToLiveness$nextLabel(abstractInsnNode.getNext());
                                    if (labelNodeUpdateLvtAccordingToLiveness$nextLabel != null) {
                                        LabelNode labelNode3 = localVariableNodeUpdateLvtAccordingToLiveness$findRecord.end;
                                        labelNode3.getClass();
                                        labelNodeUpdateLvtAccordingToLiveness$min = updateLvtAccordingToLiveness$min(methodNode, labelNode3, labelNodeUpdateLvtAccordingToLiveness$nextLabel);
                                        if (labelNodeUpdateLvtAccordingToLiveness$min == null) {
                                            labelNodeUpdateLvtAccordingToLiveness$min = localVariableNodeUpdateLvtAccordingToLiveness$findRecord.end;
                                        }
                                    } else {
                                        labelNodeUpdateLvtAccordingToLiveness$min = localVariableNodeUpdateLvtAccordingToLiveness$findRecord.end;
                                    }
                                    LabelNode labelNode4 = labelNodeUpdateLvtAccordingToLiveness$min;
                                    LabelNode labelNode5 = labelNode2 == null ? localVariableNodeUpdateLvtAccordingToLiveness$findRecord.start : labelNode2;
                                    LocalVariableNode localVariableNode = (LocalVariableNode) linkedHashMap.get(localVariableNodeUpdateLvtAccordingToLiveness$findRecord);
                                    i = i3;
                                    if (localVariableNode != null) {
                                        LabelNode labelNode6 = localVariableNodeUpdateLvtAccordingToLiveness$findRecord.end;
                                        labelNode6.getClass();
                                        zExtendRecordIfPossible = extendRecordIfPossible(localVariableNode, methodNode, list2, labelNode6, listAnalyzeLiveness, i);
                                    } else {
                                        zExtendRecordIfPossible = false;
                                    }
                                    if (!zExtendRecordIfPossible) {
                                        LocalVariableNode localVariableNode2 = new LocalVariableNode(localVariableNodeUpdateLvtAccordingToLiveness$findRecord.name, localVariableNodeUpdateLvtAccordingToLiveness$findRecord.desc, localVariableNodeUpdateLvtAccordingToLiveness$findRecord.signature, labelNode5, labelNode4, localVariableNodeUpdateLvtAccordingToLiveness$findRecord.index);
                                        linkedHashMap.put(localVariableNodeUpdateLvtAccordingToLiveness$findRecord, localVariableNode2);
                                        methodNode.localVariables.add(localVariableNode2);
                                        LabelNode labelNode7 = localVariableNodeUpdateLvtAccordingToLiveness$findRecord.end;
                                        labelNode7.getClass();
                                        extendRecordIfPossible(localVariableNode2, methodNode, list, labelNode7, listAnalyzeLiveness, i);
                                    }
                                }
                            }
                            i4++;
                            i3 = i;
                            labelNode = labelNode2;
                        }
                        break;
                    }
                }
            }
        }
        Iterator it3 = arrayList.iterator();
        it3.getClass();
        while (it3.hasNext()) {
            Object next2 = it3.next();
            next2.getClass();
            LocalVariableNode localVariableNode3 = (LocalVariableNode) next2;
            if (!Intrinsics.areEqual(localVariableNode3.name, "$continuation") && !Intrinsics.areEqual(localVariableNode3.name, CoroutineConstantsKt.SUSPEND_CALL_RESULT_NAME)) {
                String str2 = localVariableNode3.name;
                str2.getClass();
                if (!JvmAbi.isFakeLocalVariableForInline(str2)) {
                    if (Intrinsics.areEqual(localVariableNode3.name, CoroutineConstantsKt.SUSPEND_FUNCTION_COMPLETION_PARAMETER_NAME)) {
                        List list3 = methodNode.localVariables;
                        list3.getClass();
                        List list4 = list3;
                        if (!(list4 instanceof Collection) || !list4.isEmpty()) {
                            Iterator it4 = list4.iterator();
                            do {
                                if (it4.hasNext()) {
                                }
                            } while (!Intrinsics.areEqual(((LocalVariableNode) it4.next()).name, CoroutineConstantsKt.SUSPEND_FUNCTION_COMPLETION_PARAMETER_NAME));
                        }
                        String str3 = methodNode.desc;
                        str3.getClass();
                        extendCompletionsRange(methodNode, localVariableNode3, getLastParameterIndex(str3, methodNode.access));
                        break;
                    }
                    if (Intrinsics.areEqual(localVariableNode3.name, "this") && !z) {
                        extendCompletionsRange(methodNode, localVariableNode3, 0);
                    }
                }
            }
            methodNode.localVariables.add(localVariableNode3);
        }
    }

    private static final LocalVariableNode updateLvtAccordingToLiveness$findRecord(List<? extends LocalVariableNode> list, MethodNode methodNode, int i, int i2) {
        for (LocalVariableNode localVariableNode : list) {
            if (localVariableNode.index == i2 && methodNode.instructions.indexOf(localVariableNode.start) <= i && i < methodNode.instructions.indexOf(localVariableNode.end)) {
                return localVariableNode;
            }
        }
        return null;
    }

    private static final boolean updateLvtAccordingToLiveness$isAlive(List<VariableLivenessFrame> list, int i, int i2) {
        return list.get(i).isAlive(i2);
    }

    private static final LabelNode updateLvtAccordingToLiveness$min(MethodNode methodNode, LabelNode labelNode, LabelNode labelNode2) {
        return methodNode.instructions.indexOf(labelNode) < methodNode.instructions.indexOf(labelNode2) ? labelNode : labelNode2;
    }

    private static final LabelNode updateLvtAccordingToLiveness$nextLabel(AbstractInsnNode abstractInsnNode) {
        while (abstractInsnNode != null) {
            if (abstractInsnNode instanceof LabelNode) {
                return (LabelNode) abstractInsnNode;
            }
            abstractInsnNode = abstractInsnNode.getNext();
        }
        return null;
    }

    public static final InsnList withInstructionAdapter(Function1<? super InstructionAdapter, Unit> function1) {
        function1.getClass();
        MethodNode methodNode = new MethodNode();
        function1.invoke(new InstructionAdapter(methodNode));
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        return insnList;
    }
}
