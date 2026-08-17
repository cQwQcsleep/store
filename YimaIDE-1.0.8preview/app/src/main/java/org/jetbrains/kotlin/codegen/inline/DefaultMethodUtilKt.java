package org.jetbrains.kotlin.codegen.inline;

import defpackage.oh3;
import defpackage.yp6;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.function.Predicate;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.Triple;
import kotlin.TuplesKt;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.InsnSequence;
import org.jetbrains.kotlin.codegen.InsnSequenceKt;
import org.jetbrains.kotlin.codegen.inline.DefaultMethodUtilKt;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.kotlin.util.OperatorNameConventions;
import org.jetbrains.org.objectweb.asm.ClassReader;
import org.jetbrains.org.objectweb.asm.ClassVisitor;
import org.jetbrains.org.objectweb.asm.MethodVisitor;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.commons.Method;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.FieldInsnNode;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.InsnNode;
import org.jetbrains.org.objectweb.asm.tree.JumpInsnNode;
import org.jetbrains.org.objectweb.asm.tree.LabelNode;
import org.jetbrains.org.objectweb.asm.tree.LineNumberNode;
import org.jetbrains.org.objectweb.asm.tree.LocalVariableNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodNode;
import org.jetbrains.org.objectweb.asm.tree.TryCatchBlockNode;
import org.jetbrains.org.objectweb.asm.tree.TypeInsnNode;
import org.jetbrains.org.objectweb.asm.tree.VarInsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000d\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u001e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u001f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0011\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a@\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\u0006\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00060\u00012\u0006\u0010\b\u001a\u00020\u00062\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00060\n\u001aD\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00020\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u00012\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0018\u0010\u0011\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u00100\u00130\u0012H\u0002\u001a#\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00180\u00172\u0006\u0010\u0019\u001a\u00020\u0006H\u0002¢\u0006\u0002\u0010\u001a\u001a\u001e\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u00182\u0006\u0010 \u001a\u00020!\u001a\u001c\u0010\"\u001a\u00020\u001c*\u00020\u00042\u0006\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001f\u001a\u00020\u0018H\u0002¨\u0006#"}, d2 = {"expandMaskConditionsAndUpdateVariableNodes", Argument.Delimiters.none, "Lorg/jetbrains/kotlin/codegen/inline/ExtractedDefaultLambda;", "node", "Lorg/jetbrains/org/objectweb/asm/tree/MethodNode;", "maskStartIndex", Argument.Delimiters.none, "masks", "methodHandlerIndex", "validOffsets", Argument.Delimiters.none, "extractDefaultLambdasInfo", "conditions", "Lorg/jetbrains/kotlin/codegen/inline/Condition;", "toDelete", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "toInsert", Argument.Delimiters.none, "Lkotlin/Pair;", "defaultLambdaFakeCallStub", "Lorg/jetbrains/org/objectweb/asm/tree/MethodInsnNode;", "args", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/Type;", "lambdaOffset", "([Lorg/jetbrains/org/objectweb/asm/Type;I)Lorg/jetbrains/org/objectweb/asm/tree/MethodInsnNode;", "loadDefaultLambdaBody", "Lorg/jetbrains/kotlin/codegen/inline/SMAPAndMethodNode;", "classBytes", Argument.Delimiters.none, "classType", "isPropertyReference", Argument.Delimiters.none, "inlineBridge", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class DefaultMethodUtilKt {
    public static Condition a(List list, int i, int i2, VarInsnNode varInsnNode) {
        AbstractInsnNode next;
        AbstractInsnNode next2;
        AbstractInsnNode next3;
        AbstractInsnNode next4;
        AbstractInsnNode next5;
        AbstractInsnNode next6;
        varInsnNode.getClass();
        AbstractInsnNode next7 = null;
        if (!expandMaskConditionsAndUpdateVariableNodes$isMaskIndex(i, list, varInsnNode.var) || (next3 = varInsnNode.getNext()) == null || (next4 = next3.getNext()) == null || next4.getOpcode() != 126 || (next5 = varInsnNode.getNext().getNext().getNext()) == null || next5.getOpcode() != 153) {
            if (i2 != varInsnNode.var || (next = varInsnNode.getNext()) == null || next.getOpcode() != 198 || (next2 = varInsnNode.getNext().getNext()) == null || next2.getOpcode() != 187) {
                return null;
            }
            JumpInsnNode next8 = varInsnNode.getNext();
            next8.getClass();
            return new Condition(0, 0, varInsnNode, next8, null);
        }
        AbstractInsnNode next9 = varInsnNode.getNext();
        if (next9 != null && (next6 = next9.getNext()) != null) {
            next7 = next6.getNext();
        }
        next7.getClass();
        JumpInsnNode jumpInsnNode = (JumpInsnNode) next7;
        int iIntValue = ((Number) list.get(varInsnNode.var - i)).intValue();
        AbstractInsnNode next10 = varInsnNode.getNext();
        next10.getClass();
        int constant = InlineCodegenUtilsKt.getConstant(next10);
        VarInsnNode previous = jumpInsnNode.label.getPrevious();
        previous.getClass();
        return new Condition(iIntValue, constant, varInsnNode, jumpInsnNode, previous);
    }

    public static boolean b(LinkedHashSet linkedHashSet, TryCatchBlockNode tryCatchBlockNode) {
        return linkedHashSet.contains(tryCatchBlockNode.start) && linkedHashSet.contains(tryCatchBlockNode.end);
    }

    public static boolean c(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    public static boolean d(LinkedHashSet linkedHashSet, Collection collection, LocalVariableNode localVariableNode) {
        return (linkedHashSet.contains(localVariableNode.start) && linkedHashSet.contains(localVariableNode.end)) || collection.contains(Integer.valueOf(localVariableNode.index));
    }

    private static final MethodInsnNode defaultLambdaFakeCallStub(Type[] typeArr, int i) {
        return new MethodInsnNode(184, InlineCodegenUtilsKt.DEFAULT_LAMBDA_FAKE_CALL, InlineCodegenUtilsKt.DEFAULT_LAMBDA_FAKE_CALL + i, Type.getMethodDescriptor(Type.VOID_TYPE, (Type[]) Arrays.copyOf(typeArr, typeArr.length)), false);
    }

    public static boolean e(int i, int i2, List list, AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        if (!(abstractInsnNode instanceof VarInsnNode)) {
            return true;
        }
        VarInsnNode varInsnNode = (VarInsnNode) abstractInsnNode;
        if (expandMaskConditionsAndUpdateVariableNodes$isMaskIndex(i2, list, varInsnNode.var)) {
            return varInsnNode.getOpcode() == 21;
        }
        return i != varInsnNode.var || varInsnNode.getOpcode() == 25;
    }

    public static final List<ExtractedDefaultLambda> expandMaskConditionsAndUpdateVariableNodes(MethodNode methodNode, final int i, final List<Integer> list, final int i2, final Collection<Integer> collection) {
        Map mapEmptyMap;
        LocalVariableNode localVariableNode;
        methodNode.getClass();
        list.getClass();
        collection.getClass();
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        Sequence sequenceFilter = SequencesKt.filter(SequencesKt.takeWhile(InsnSequenceKt.asSequence(insnList), new Function1() { // from class: qh3
            public final Object invoke(Object obj) {
                return Boolean.valueOf(DefaultMethodUtilKt.e(i2, i, list, (AbstractInsnNode) obj));
            }
        }), new Function1<Object, Boolean>() { // from class: org.jetbrains.kotlin.codegen.inline.DefaultMethodUtilKt$expandMaskConditionsAndUpdateVariableNodes$$inlined$filterIsInstance$1
            /* JADX INFO: renamed from: invoke, reason: merged with bridge method [inline-methods] */
            public final Boolean m62invoke(Object obj) {
                return Boolean.valueOf(obj instanceof VarInsnNode);
            }
        });
        sequenceFilter.getClass();
        List list2 = SequencesKt.toList(SequencesKt.mapNotNull(sequenceFilter, new Function1() { // from class: rh3
            public final Object invoke(Object obj) {
                return DefaultMethodUtilKt.a(list, i, i2, (VarInsnNode) obj);
            }
        }));
        final LinkedHashSet linkedHashSet = new LinkedHashSet();
        ArrayList<Pair> arrayList = new ArrayList();
        List<Condition> list3 = list2;
        ArrayList arrayList2 = new ArrayList();
        for (Object obj : list3) {
            Condition condition = (Condition) obj;
            if (condition.getExpandNotDelete() && collection.contains(Integer.valueOf(condition.getVarIndex()))) {
                arrayList2.add(obj);
            }
        }
        List<ExtractedDefaultLambda> listExtractDefaultLambdasInfo = extractDefaultLambdasInfo(arrayList2, linkedHashSet, arrayList);
        List list4 = methodNode.localVariables;
        if (list4 != null) {
            ArrayList arrayList3 = new ArrayList();
            for (Object obj2 : list4) {
                if (((LocalVariableNode) obj2).index < i) {
                    arrayList3.add(obj2);
                }
            }
            mapEmptyMap = new LinkedHashMap(RangesKt.coerceAtLeast(MapsKt.mapCapacity(CollectionsKt.collectionSizeOrDefault(arrayList3, 10)), 16));
            for (Object obj3 : arrayList3) {
                mapEmptyMap.put(Integer.valueOf(((LocalVariableNode) obj3).index), obj3);
            }
        } else {
            mapEmptyMap = MapsKt.emptyMap();
        }
        for (Condition condition2 : list3) {
            JumpInsnNode jumpInstruction = condition2.getJumpInstruction();
            Iterator it = new InsnSequence(condition2.getMaskInstruction(), condition2.getExpandNotDelete() ? jumpInstruction.getNext() : jumpInstruction.label).iterator();
            while (it.hasNext()) {
                linkedHashSet.add((AbstractInsnNode) it.next());
            }
            if (condition2.getExpandNotDelete() && (localVariableNode = (LocalVariableNode) mapEmptyMap.get(Integer.valueOf(condition2.getVarIndex()))) != null) {
                localVariableNode.start = condition2.getJumpInstruction().label;
            }
        }
        for (Pair pair : arrayList) {
            methodNode.instructions.insert((AbstractInsnNode) pair.component1(), (AbstractInsnNode) pair.component2());
        }
        List list5 = methodNode.localVariables;
        final Function1 function1 = new Function1() { // from class: sh3
            public final Object invoke(Object obj4) {
                return Boolean.valueOf(DefaultMethodUtilKt.d(linkedHashSet, collection, (LocalVariableNode) obj4));
            }
        };
        list5.removeIf(new Predicate() { // from class: th3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj4) {
                return DefaultMethodUtilKt.f(function1, obj4);
            }
        });
        List list6 = methodNode.tryCatchBlocks;
        final Function1 function2 = new Function1() { // from class: uh3
            public final Object invoke(Object obj4) {
                return Boolean.valueOf(DefaultMethodUtilKt.b(linkedHashSet, (TryCatchBlockNode) obj4));
            }
        };
        list6.removeIf(new Predicate() { // from class: vh3
            @Override // java.util.function.Predicate
            public final boolean test(Object obj4) {
                return DefaultMethodUtilKt.c(function2, obj4);
            }
        });
        MethodInlinerUtilKt.remove(methodNode, linkedHashSet);
        return listExtractDefaultLambdasInfo;
    }

    private static final boolean expandMaskConditionsAndUpdateVariableNodes$isMaskIndex(int i, List<Integer> list, int i2) {
        return i <= i2 && i2 < i + list.size();
    }

    private static final List<ExtractedDefaultLambda> extractDefaultLambdasInfo(List<Condition> list, Collection<AbstractInsnNode> collection, List<Pair<AbstractInsnNode, AbstractInsnNode>> list2) {
        Triple triple;
        List<Condition> list3 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list3, 10));
        for (Condition condition : list3) {
            VarInsnNode varInsNode = condition.getVarInsNode();
            varInsNode.getClass();
            AbstractInsnNode previous = varInsNode.getPrevious();
            if (previous instanceof TypeInsnNode) {
                TypeInsnNode typeInsnNode = (TypeInsnNode) previous;
                if (typeInsnNode.getOpcode() == 192) {
                    previous = typeInsnNode.getPrevious();
                }
            }
            if (previous instanceof MethodInsnNode) {
                MethodInsnNode methodInsnNode = (MethodInsnNode) previous;
                Intrinsics.areEqual(methodInsnNode.name, "<init>");
                final String str = methodInsnNode.owner;
                AbstractInsnNode abstractInsnNode = (AbstractInsnNode) SequencesKt.single(SequencesKt.filter(new InsnSequence(condition.getJumpInstruction(), condition.getJumpInstruction().label), new Function1() { // from class: ph3
                    public final Object invoke(Object obj) {
                        return Boolean.valueOf(DefaultMethodUtilKt.extractDefaultLambdasInfo$lambda$0$1(str, (AbstractInsnNode) obj));
                    }
                }));
                AbstractInsnNode next = abstractInsnNode.getNext();
                if (next != null) {
                    next.getOpcode();
                }
                collection.addAll(CollectionsKt.listOf(new AbstractInsnNode[]{abstractInsnNode, abstractInsnNode.getNext()}));
                collection.addAll(SequencesKt.toList(new InsnSequence(previous, varInsNode.getNext())));
                AbstractInsnNode previous2 = abstractInsnNode.getPrevious();
                ReifiedTypeInliner.Companion companion = ReifiedTypeInliner.INSTANCE;
                previous2.getClass();
                if (!companion.isNeedClassReificationMarker(previous2)) {
                    previous2 = null;
                }
                triple = new Triple(Type.getObjectType(methodInsnNode.owner), Type.getArgumentTypes(methodInsnNode.desc), Boolean.valueOf((previous2 != null ? Boolean.valueOf(collection.add(previous2)) : null) != null));
            } else {
                if (!(previous instanceof FieldInsnNode)) {
                    StringBuilder sb = new StringBuilder("Can't extract default lambda info ");
                    sb.append(condition);
                    String insnText = InlineCodegenUtilsKt.getInsnText(previous);
                    sb.append(".\n Unknown instruction: ");
                    sb.append(insnText);
                    throw new RuntimeException(sb.toString());
                }
                collection.addAll(SequencesKt.toList(new InsnSequence(previous, varInsNode.getNext())));
                FieldInsnNode fieldInsnNode = (FieldInsnNode) previous;
                AbstractInsnNode previous3 = fieldInsnNode.getPrevious();
                ReifiedTypeInliner.Companion companion2 = ReifiedTypeInliner.INSTANCE;
                previous3.getClass();
                if (!companion2.isNeedClassReificationMarker(previous3)) {
                    previous3 = null;
                }
                triple = new Triple(Type.getObjectType(fieldInsnNode.owner), new Type[0], Boolean.valueOf((previous3 != null ? Boolean.valueOf(collection.add(previous3)) : null) != null));
            }
            Type type = (Type) triple.component1();
            Type[] typeArr = (Type[]) triple.component2();
            boolean zBooleanValue = ((Boolean) triple.component3()).booleanValue();
            typeArr.getClass();
            list2.add(TuplesKt.to(varInsNode, defaultLambdaFakeCallStub(typeArr, condition.getVarIndex())));
            type.getClass();
            arrayList.add(new ExtractedDefaultLambda(type, typeArr, condition.getVarIndex(), zBooleanValue));
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean extractDefaultLambdasInfo$lambda$0$1(String str, AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        return abstractInsnNode.getOpcode() == 187 && Intrinsics.areEqual(((TypeInsnNode) abstractInsnNode).desc, str);
    }

    public static boolean f(Function1 function1, Object obj) {
        return ((Boolean) function1.invoke(obj)).booleanValue();
    }

    private static final SMAPAndMethodNode inlineBridge(MethodNode methodNode, byte[] bArr, Type type) {
        InsnList insnList = methodNode.instructions;
        insnList.getClass();
        Iterator it = insnList.iterator();
        Object obj = null;
        boolean z = false;
        while (true) {
            if (!it.hasNext()) {
                if (!z) {
                    break;
                }
                break;
            }
            Object next = it.next();
            MethodInsnNode methodInsnNode = (AbstractInsnNode) next;
            if ((methodInsnNode instanceof MethodInsnNode) && Intrinsics.areEqual(methodInsnNode.owner, type.getInternalName())) {
                if (!z) {
                    obj = next;
                    z = true;
                }
            }
            obj = null;
            break;
        }
        MethodInsnNode methodInsnNode2 = (MethodInsnNode) obj;
        if (methodInsnNode2 == null) {
            throw new IllegalStateException(("no single invoke of method on this in '" + methodNode.name + methodNode.desc + "' of default lambda '" + type.getInternalName() + '\'').toString());
        }
        Method method = new Method(methodInsnNode2.name, methodInsnNode2.desc);
        SMAPAndMethodNode methodNode2 = InlineCodegenUtilsKt.getMethodNode(bArr, type, method);
        if (methodNode2 == null) {
            StringBuilder sb = new StringBuilder("can't find non-bridge invoke '");
            sb.append(method);
            ej7.a(sb, "' in default lambda '", type.getInternalName());
            return null;
        }
        Type[] argumentTypes = method.getArgumentTypes();
        argumentTypes.getClass();
        int size = 0;
        for (Type type2 : argumentTypes) {
            size += type2.getSize();
        }
        int i = size + ((methodNode2.getNode().access & 8) != 0 ? 0 : 1);
        List listReversed = ArraysKt.reversed(argumentTypes);
        int size2 = listReversed.size();
        int size3 = i;
        for (int i2 = 0; i2 < size2; i2++) {
            Type type3 = (Type) listReversed.get(i2);
            size3 -= type3.getSize();
            methodNode.instructions.insertBefore(methodInsnNode2, new VarInsnNode(type3.getOpcode(54), size3));
        }
        if ((methodNode2.getNode().access & 8) == 0) {
            methodNode.instructions.insertBefore(methodInsnNode2, new InsnNode(87));
        }
        LabelNode labelNode = new LabelNode();
        LabelNode labelNode2 = new LabelNode();
        methodNode.instructions.insertBefore(methodInsnNode2, labelNode);
        methodNode.instructions.insert(methodInsnNode2, labelNode2);
        ListIterator it2 = methodNode2.getNode().instructions.iterator();
        it2.getClass();
        while (it2.hasNext()) {
            AbstractInsnNode abstractInsnNode = (AbstractInsnNode) it2.next();
            int opcode = abstractInsnNode.getOpcode();
            if (172 <= opcode && opcode < 178) {
                methodNode2.getNode().instructions.set(abstractInsnNode, new JumpInsnNode(167, labelNode2));
            }
        }
        for (LocalVariableNode localVariableNode : methodNode2.getNode().localVariables) {
            if (localVariableNode.index < i) {
                localVariableNode.start = labelNode;
                localVariableNode.end = labelNode2;
            }
        }
        InsnList insnList2 = methodNode.instructions;
        insnList2.getClass();
        ArrayList arrayList = new ArrayList();
        for (Object obj2 : insnList2) {
            if (obj2 instanceof LineNumberNode) {
                arrayList.add(obj2);
            }
        }
        Iterator it3 = arrayList.iterator();
        while (it3.hasNext()) {
            methodNode.instructions.remove((LineNumberNode) it3.next());
        }
        methodNode.instructions.insertBefore(methodInsnNode2, methodNode2.getNode().instructions);
        methodNode.instructions.remove(methodInsnNode2);
        methodNode.localVariables = methodNode2.getNode().localVariables;
        methodNode.tryCatchBlocks = methodNode2.getNode().tryCatchBlocks;
        methodNode.maxLocals = Math.max(methodNode.maxLocals, methodNode2.getNode().maxLocals);
        methodNode.maxStack = Math.max(methodNode.maxStack, methodNode2.getNode().maxStack);
        return new SMAPAndMethodNode(methodNode, methodNode2.getClassSMAP());
    }

    public static final SMAPAndMethodNode loadDefaultLambdaBody(byte[] bArr, Type type, boolean z) {
        SMAPAndMethodNode sMAPAndMethodNode;
        SMAP smapIdentityMapping;
        bArr.getClass();
        type.getClass();
        final String strAsString = (z ? OperatorNameConventions.GET : OperatorNameConventions.INVOKE).asString();
        strAsString.getClass();
        final Ref.ObjectRef objectRef = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef2 = new Ref.ObjectRef();
        final Ref.ObjectRef objectRef3 = new Ref.ObjectRef();
        new ClassReader(bArr).accept(new ClassVisitor() { // from class: org.jetbrains.kotlin.codegen.inline.DefaultMethodUtilKt$loadDefaultLambdaBody$$inlined$getMethodNode$1
            /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
            {
                super(589824);
            }

            public MethodVisitor visitMethod(int access, String name, String desc, String signature, String[] exceptions) {
                name.getClass();
                desc.getClass();
                Method method = new Method(name, desc);
                if (Intrinsics.areEqual(method.getName(), strAsString) && Intrinsics.areEqual(method.getReturnType(), AsmTypes.OBJECT_TYPE)) {
                    Type[] argumentTypes = method.getArgumentTypes();
                    argumentTypes.getClass();
                    for (Type type2 : argumentTypes) {
                        if (Intrinsics.areEqual(type2, AsmTypes.OBJECT_TYPE)) {
                        }
                    }
                    Ref.ObjectRef objectRef4 = objectRef;
                    MethodNode methodNode = (MethodNode) objectRef4.element;
                    if (methodNode == null) {
                        objectRef4.element = new MethodNode(589824, access, name, desc, signature, exceptions);
                        return (MethodVisitor) objectRef.element;
                    }
                    yp6.a(name, methodNode.name, methodNode.desc, desc);
                }
                return null;
            }

            public void visitSource(String source, String debug) {
                objectRef2.element = source;
                objectRef3.element = debug;
            }
        }, 4);
        MethodNode methodNode = (MethodNode) objectRef.element;
        if (methodNode != null) {
            String str = (String) objectRef3.element;
            if (str == null || (smapIdentityMapping = SMAPParser.INSTANCE.parseOrNull(str)) == null) {
                SMAP.Companion companion = SMAP.INSTANCE;
                String str2 = (String) objectRef2.element;
                String internalName = type.getInternalName();
                internalName.getClass();
                smapIdentityMapping = companion.identityMapping(str2, internalName, CollectionsKt.listOfNotNull(methodNode));
            }
            sMAPAndMethodNode = new SMAPAndMethodNode(methodNode, smapIdentityMapping);
        } else {
            sMAPAndMethodNode = null;
        }
        if (sMAPAndMethodNode != null) {
            return (sMAPAndMethodNode.getNode().access & 64) == 0 ? sMAPAndMethodNode : inlineBridge(sMAPAndMethodNode.getNode(), bArr, type);
        }
        oh3.a("can't find erased invoke '", strAsString, "(Object...): Object' in default lambda '", type.getInternalName(), 39);
        return null;
    }
}
