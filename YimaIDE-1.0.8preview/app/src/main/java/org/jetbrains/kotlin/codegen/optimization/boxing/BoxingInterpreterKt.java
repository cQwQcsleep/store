package org.jetbrains.kotlin.codegen.optimization.boxing;

import com.google.common.collect.ImmutableSet;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import kotlin.Metadata;
import kotlin.Triple;
import kotlin.collections.CollectionsKt;
import kotlin.collections.SetsKt;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.kotlin.builtins.StandardNames;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.cli.common.modules.ModuleXmlParser;
import org.jetbrains.kotlin.codegen.CodegenUtilKt;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.codegen.state.KotlinTypeMapper;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.name.FqName;
import org.jetbrains.kotlin.name.Name;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.kotlin.resolve.jvm.JvmPrimitiveType;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.AbstractInsnNode;
import org.jetbrains.org.objectweb.asm.tree.MethodInsnNode;
import org.jetbrains.org.objectweb.asm.tree.analysis.BasicValue;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000R\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0015\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\u001a\u0012\u0010\u0006\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n\u001a\u0012\u0010\u000b\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\n\u001a\n\u0010\f\u001a\u00020\u0007*\u00020\b\u001a\n\u0010\r\u001a\u00020\u0007*\u00020\b\u001a1\u0010\u000e\u001a\u00020\u0007*\u00020\b2\u0006\u0010\u000f\u001a\u00020\u00102\u0017\u0010\u0011\u001a\u0013\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00070\u0012¢\u0006\u0002\b\u0014H\u0086\bø\u0001\u0000\u001a\u0010\u0010\u0015\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0002H\u0002\u001a\u0010\u0010\u0017\u001a\u00020\u00072\u0006\u0010\u0016\u001a\u00020\u0002H\u0002\u001a\u0010\u0010\u0018\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0002H\u0002\u001a\n\u0010\u001a\u001a\u00020\u0007*\u00020\b\u001a\u0010\u0010\u001c\u001a\u00020\u00072\u0006\u0010\u0019\u001a\u00020\u0002H\u0002\u001a\n\u0010\u001d\u001a\u00020\u0007*\u00020\b\u001a\f\u0010\u001e\u001a\u00020\u0007*\u00020\u0013H\u0002\u001a\n\u0010\u001f\u001a\u00020\u0007*\u00020\b\u001a\u0014\u0010 \u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002\u001a\u0014\u0010!\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002\u001a\u0014\u0010\"\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002\u001a\u0014\u0010#\u001a\u00020\u0007*\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002\u001a\u0014\u0010$\u001a\u00020\u0007*\u00020\u00132\u0006\u0010\t\u001a\u00020\nH\u0002\u001a\u0014\u0010%\u001a\u00020\u0007*\u00020\u00132\u0006\u0010\t\u001a\u00020\nH\u0002\u001a\u0014\u0010&\u001a\u00020\u0007*\u00020\u00132\u0006\u0010\t\u001a\u00020\nH\u0002\u001a\u0014\u0010'\u001a\u00020\u0007*\u00020\u00132\u0006\u0010\t\u001a\u00020\nH\u0002\u001a\u0018\u0010(\u001a\u00020\u0007*\u00020\b2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*\u001a\n\u0010,\u001a\u00020\u0007*\u00020\b\u001a\u0018\u0010-\u001a\u00020\u0007*\u00020\b2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*\u001a\u0010\u00100\u001a\u00020\u00072\u0006\u00101\u001a\u000202H\u0002\u001a\u0018\u00103\u001a\u00020\u0007*\u00020\b2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*\u001a\u0014\u00104\u001a\u00020\u00072\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*\u001a\n\u00105\u001a\u00020\u0007*\u00020\b\u001a\u001c\u00107\u001a\u00020\u00072\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*2\u0006\u00108\u001a\u00020\n\u001a\u0018\u00109\u001a\u00020\u0007*\u00020\b2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020+0*\u001a\n\u0010:\u001a\u00020\u0007*\u00020\b\"2\u0010\u0000\u001a&\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002 \u0003*\u0012\u0012\f\u0012\n \u0003*\u0004\u0018\u00010\u00020\u0002\u0018\u00010\u00010\u0001X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0004\u001a\n \u0003*\u0004\u0018\u00010\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0016\u0010\u0005\u001a\n \u0003*\u0004\u0018\u00010\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000\"\u000e\u0010\u001b\u001a\u00020\u0002X\u0082\u0004¢\u0006\u0002\n\u0000\"\u0014\u0010.\u001a\b\u0012\u0004\u0012\u00020\u00020/X\u0082\u0004¢\u0006\u0002\n\u0000\"\u001c\u00106\u001a\u0010\u0012\f\u0012\n \u0003*\u0004\u0018\u000102020/X\u0082\u0004¢\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u009920\u0001¨\u0006;"}, d2 = {"UNBOXING_METHOD_NAMES", "Lcom/google/common/collect/ImmutableSet;", Argument.Delimiters.none, "kotlin.jvm.PlatformType", "KCLASS_TO_JLCLASS", "JLCLASS_TO_KCLASS", "isUnboxing", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/AbstractInsnNode;", "state", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "isBoxing", "isPrimitiveUnboxing", "isJavaLangClassUnboxing", "isMethodInsnWith", "opcode", Argument.Delimiters.none, "condition", "Lkotlin/Function1;", "Lorg/jetbrains/org/objectweb/asm/tree/MethodInsnNode;", "Lkotlin/ExtensionFunctionType;", "isWrapperClassNameOrNumber", "internalClassName", "isWrapperClassName", "isUnboxingMethodName", ModuleXmlParser.NAME, "isPrimitiveBoxing", "BOXING_CLASS_INTERNAL_NAME", "isJvmPrimitiveName", "isCoroutinePrimitiveBoxing", "isBoxingMethodDescriptor", "isJavaLangClassBoxing", "isInlineClassBoxing", "isMultiFieldValueClassBoxing", "isInlineClassUnboxing", "isMultiFieldValueClassUnboxing", "isInlineClassBoxingMethodDescriptor", "isMultiFieldValueClassBoxingMethodDescriptor", "isInlineClassUnboxingMethodDescriptor", "isMultiFieldValueClassUnboxingMethodDescriptor", "isNextMethodCallOfProgressionIterator", "values", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/tree/analysis/BasicValue;", "isIteratorMethodCall", "isIteratorMethodCallOfProgression", "PROGRESSION_CLASS_FQNS", Argument.Delimiters.none, "isProgressionClass", ModuleXmlParser.TYPE, "Lorg/jetbrains/org/objectweb/asm/Type;", "isAreEqualIntrinsicForSameTypedBoxedValues", "areSameTypedPrimitiveBoxedValues", "isAreEqualIntrinsic", "shouldUseEqualsForWrappers", "canValuesBeUnboxedForAreEqual", "generationState", "isJavaLangComparableCompareToForSameTypedBoxedValues", "isJavaLangComparableCompareTo", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class BoxingInterpreterKt {
    private static final String BOXING_CLASS_INTERNAL_NAME;
    private static final String JLCLASS_TO_KCLASS;
    private static final String KCLASS_TO_JLCLASS;
    private static final Set<String> PROGRESSION_CLASS_FQNS;
    private static final ImmutableSet<String> UNBOXING_METHOD_NAMES = ImmutableSet.of("booleanValue", "charValue", "byteValue", "shortValue", "intValue", "floatValue", new String[]{"longValue", "doubleValue"});
    private static final Set<Type> shouldUseEqualsForWrappers;

    @Metadata(k = 3, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
    public static final /* synthetic */ class EntriesMappings {
        public static final /* synthetic */ EnumEntries<JvmPrimitiveType> entries$0 = EnumEntriesKt.enumEntries(JvmPrimitiveType.values());
    }

    static {
        Type type = AsmTypes.JAVA_CLASS_TYPE;
        Type type2 = AsmTypes.K_CLASS_TYPE;
        KCLASS_TO_JLCLASS = Type.getMethodDescriptor(type, new Type[]{type2});
        JLCLASS_TO_KCLASS = Type.getMethodDescriptor(type2, new Type[]{type});
        FqName fqName = StandardNames.COROUTINES_JVM_INTERNAL_PACKAGE_FQ_NAME;
        Name nameIdentifier = Name.identifier("Boxing");
        nameIdentifier.getClass();
        BOXING_CLASS_INTERNAL_NAME = CodegenUtilKt.topLevelClassInternalName(fqName.child(nameIdentifier));
        PROGRESSION_CLASS_FQNS = SetsKt.setOf(new String[]{"kotlin.ranges.CharRange", "kotlin.ranges.CharProgression", "kotlin.ranges.IntRange", "kotlin.ranges.IntProgression", "kotlin.ranges.LongRange", "kotlin.ranges.LongProgression"});
        shouldUseEqualsForWrappers = SetsKt.setOf(new Type[]{Type.DOUBLE_TYPE, Type.FLOAT_TYPE, type});
    }

    public static final boolean areSameTypedPrimitiveBoxedValues(List<? extends BasicValue> list) {
        list.getClass();
        if (list.size() != 2) {
            return false;
        }
        BasicValue basicValue = list.get(0);
        BasicValue basicValue2 = list.get(1);
        if ((basicValue instanceof BoxedBasicValue) && (basicValue2 instanceof BoxedBasicValue)) {
            BoxedBasicValue boxedBasicValue = (BoxedBasicValue) basicValue;
            if (!boxedBasicValue.getDescriptor().getIsValueClassValue()) {
                BoxedBasicValue boxedBasicValue2 = (BoxedBasicValue) basicValue2;
                if (!boxedBasicValue2.getDescriptor().getIsValueClassValue() && Intrinsics.areEqual(CollectionsKt.single(boxedBasicValue.getDescriptor().getUnboxedTypes()), CollectionsKt.single(boxedBasicValue2.getDescriptor().getUnboxedTypes()))) {
                    return true;
                }
            }
        }
        return false;
    }

    public static final boolean canValuesBeUnboxedForAreEqual(List<? extends BasicValue> list, GenerationState generationState) {
        list.getClass();
        generationState.getClass();
        List<? extends BasicValue> list2 = list;
        if ((list2 instanceof Collection) && list2.isEmpty()) {
            return true;
        }
        for (BasicValue basicValue : list2) {
            Type type = basicValue.getType();
            type.getClass();
            Type type2 = basicValue.getType();
            type2.getClass();
            Type type3 = (Type) CollectionsKt.singleOrNull(BoxedBasicValueKt.getUnboxedTypes(type, generationState, BoxedBasicValueKt.getMultiFieldValueClassUnboxInfo(type2, generationState)));
            if (type3 == null || shouldUseEqualsForWrappers.contains(type3)) {
                return false;
            }
        }
        return true;
    }

    public static final boolean isAreEqualIntrinsic(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        if (abstractInsnNode.getOpcode() != 184 || !(abstractInsnNode instanceof MethodInsnNode)) {
            return false;
        }
        MethodInsnNode methodInsnNode = (MethodInsnNode) abstractInsnNode;
        return Intrinsics.areEqual(methodInsnNode.name, "areEqual") && Intrinsics.areEqual(methodInsnNode.owner, "kotlin/jvm/internal/Intrinsics") && Intrinsics.areEqual(methodInsnNode.desc, "(Ljava/lang/Object;Ljava/lang/Object;)Z");
    }

    public static final boolean isAreEqualIntrinsicForSameTypedBoxedValues(AbstractInsnNode abstractInsnNode, List<? extends BasicValue> list) {
        abstractInsnNode.getClass();
        list.getClass();
        return isAreEqualIntrinsic(abstractInsnNode) && areSameTypedPrimitiveBoxedValues(list);
    }

    public static final boolean isBoxing(AbstractInsnNode abstractInsnNode, GenerationState generationState) {
        abstractInsnNode.getClass();
        generationState.getClass();
        return isPrimitiveBoxing(abstractInsnNode) || isJavaLangClassBoxing(abstractInsnNode) || isInlineClassBoxing(abstractInsnNode, generationState) || isCoroutinePrimitiveBoxing(abstractInsnNode) || isMultiFieldValueClassBoxing(abstractInsnNode, generationState);
    }

    private static final boolean isBoxingMethodDescriptor(MethodInsnNode methodInsnNode) {
        return JvmPrimitiveType.isBoxingMethodDescriptor(methodInsnNode.owner, methodInsnNode.desc);
    }

    public static final boolean isCoroutinePrimitiveBoxing(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        if (abstractInsnNode.getOpcode() == 184 && (abstractInsnNode instanceof MethodInsnNode)) {
            MethodInsnNode methodInsnNode = (MethodInsnNode) abstractInsnNode;
            if (Intrinsics.areEqual(methodInsnNode.owner, BOXING_CLASS_INTERNAL_NAME)) {
                String str = methodInsnNode.name;
                str.getClass();
                if (StringsKt.startsWith$default(str, "box", false, 2, (Object) null)) {
                    String str2 = methodInsnNode.name;
                    str2.getClass();
                    String lowerCase = str2.substring(3).toLowerCase(Locale.ROOT);
                    lowerCase.getClass();
                    if (isJvmPrimitiveName(lowerCase)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    private static final boolean isInlineClassBoxing(AbstractInsnNode abstractInsnNode, GenerationState generationState) {
        return abstractInsnNode.getOpcode() == 184 && (abstractInsnNode instanceof MethodInsnNode) && isInlineClassBoxingMethodDescriptor((MethodInsnNode) abstractInsnNode, generationState);
    }

    private static final boolean isInlineClassBoxingMethodDescriptor(MethodInsnNode methodInsnNode, GenerationState generationState) {
        if (!Intrinsics.areEqual(methodInsnNode.name, KotlinTypeMapper.BOX_JVM_METHOD_NAME)) {
            return false;
        }
        Type objectType = Type.getObjectType(methodInsnNode.owner);
        objectType.getClass();
        Type typeUnboxedTypeOfInlineClass = BoxedBasicValueKt.unboxedTypeOfInlineClass(objectType, generationState);
        if (typeUnboxedTypeOfInlineClass == null) {
            return false;
        }
        return Intrinsics.areEqual(methodInsnNode.desc, Type.getMethodDescriptor(objectType, new Type[]{typeUnboxedTypeOfInlineClass}));
    }

    private static final boolean isInlineClassUnboxing(AbstractInsnNode abstractInsnNode, GenerationState generationState) {
        return abstractInsnNode.getOpcode() == 182 && (abstractInsnNode instanceof MethodInsnNode) && isInlineClassUnboxingMethodDescriptor((MethodInsnNode) abstractInsnNode, generationState);
    }

    private static final boolean isInlineClassUnboxingMethodDescriptor(MethodInsnNode methodInsnNode, GenerationState generationState) {
        if (!Intrinsics.areEqual(methodInsnNode.name, KotlinTypeMapper.UNBOX_JVM_METHOD_NAME)) {
            return false;
        }
        Type objectType = Type.getObjectType(methodInsnNode.owner);
        objectType.getClass();
        Type typeUnboxedTypeOfInlineClass = BoxedBasicValueKt.unboxedTypeOfInlineClass(objectType, generationState);
        if (typeUnboxedTypeOfInlineClass == null) {
            return false;
        }
        return Intrinsics.areEqual(methodInsnNode.desc, Type.getMethodDescriptor(typeUnboxedTypeOfInlineClass, new Type[0]));
    }

    public static final boolean isIteratorMethodCall(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        if (abstractInsnNode.getOpcode() != 185 || !(abstractInsnNode instanceof MethodInsnNode)) {
            return false;
        }
        MethodInsnNode methodInsnNode = (MethodInsnNode) abstractInsnNode;
        return Intrinsics.areEqual(methodInsnNode.name, "iterator") && Intrinsics.areEqual(methodInsnNode.desc, "()Ljava/util/Iterator;");
    }

    public static final boolean isIteratorMethodCallOfProgression(AbstractInsnNode abstractInsnNode, List<? extends BasicValue> list) {
        abstractInsnNode.getClass();
        list.getClass();
        if (abstractInsnNode.getOpcode() != 185 || !(abstractInsnNode instanceof MethodInsnNode)) {
            return false;
        }
        MethodInsnNode methodInsnNode = (MethodInsnNode) abstractInsnNode;
        BasicValue basicValue = (BasicValue) CollectionsKt.firstOrNull(list);
        Type type = basicValue != null ? basicValue.getType() : null;
        return Intrinsics.areEqual(methodInsnNode.name, "iterator") && Intrinsics.areEqual(methodInsnNode.desc, "()Ljava/util/Iterator;") && type != null && isProgressionClass(type);
    }

    public static final boolean isJavaLangClassBoxing(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        if (abstractInsnNode.getOpcode() != 184 || !(abstractInsnNode instanceof MethodInsnNode)) {
            return false;
        }
        MethodInsnNode methodInsnNode = (MethodInsnNode) abstractInsnNode;
        return Intrinsics.areEqual(methodInsnNode.owner, "kotlin/jvm/internal/Reflection") && Intrinsics.areEqual(methodInsnNode.name, "getOrCreateKotlinClass") && Intrinsics.areEqual(methodInsnNode.desc, JLCLASS_TO_KCLASS);
    }

    public static final boolean isJavaLangClassUnboxing(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        if (abstractInsnNode.getOpcode() != 184 || !(abstractInsnNode instanceof MethodInsnNode)) {
            return false;
        }
        MethodInsnNode methodInsnNode = (MethodInsnNode) abstractInsnNode;
        return Intrinsics.areEqual(methodInsnNode.owner, "kotlin/jvm/JvmClassMappingKt") && Intrinsics.areEqual(methodInsnNode.name, "getJavaClass") && Intrinsics.areEqual(methodInsnNode.desc, KCLASS_TO_JLCLASS);
    }

    public static final boolean isJavaLangComparableCompareTo(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        if (abstractInsnNode.getOpcode() != 185 || !(abstractInsnNode instanceof MethodInsnNode)) {
            return false;
        }
        MethodInsnNode methodInsnNode = (MethodInsnNode) abstractInsnNode;
        return Intrinsics.areEqual(methodInsnNode.name, "compareTo") && Intrinsics.areEqual(methodInsnNode.owner, "java/lang/Comparable") && Intrinsics.areEqual(methodInsnNode.desc, "(Ljava/lang/Object;)I");
    }

    public static final boolean isJavaLangComparableCompareToForSameTypedBoxedValues(AbstractInsnNode abstractInsnNode, List<? extends BasicValue> list) {
        abstractInsnNode.getClass();
        list.getClass();
        return isJavaLangComparableCompareTo(abstractInsnNode) && areSameTypedPrimitiveBoxedValues(list);
    }

    private static final boolean isJvmPrimitiveName(String str) {
        EnumEntries<JvmPrimitiveType> enumEntries = EntriesMappings.entries$0;
        if (enumEntries != null && enumEntries.isEmpty()) {
            return false;
        }
        Iterator it = enumEntries.iterator();
        while (it.hasNext()) {
            if (Intrinsics.areEqual(((JvmPrimitiveType) it.next()).getJavaKeywordName(), str)) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isMethodInsnWith(AbstractInsnNode abstractInsnNode, int i, Function1<? super MethodInsnNode, Boolean> function1) {
        abstractInsnNode.getClass();
        function1.getClass();
        return abstractInsnNode.getOpcode() == i && (abstractInsnNode instanceof MethodInsnNode) && ((Boolean) function1.invoke(abstractInsnNode)).booleanValue();
    }

    private static final boolean isMultiFieldValueClassBoxing(AbstractInsnNode abstractInsnNode, GenerationState generationState) {
        return abstractInsnNode.getOpcode() == 184 && (abstractInsnNode instanceof MethodInsnNode) && isMultiFieldValueClassBoxingMethodDescriptor((MethodInsnNode) abstractInsnNode, generationState);
    }

    private static final boolean isMultiFieldValueClassBoxingMethodDescriptor(MethodInsnNode methodInsnNode, GenerationState generationState) {
        if (!Intrinsics.areEqual(methodInsnNode.name, KotlinTypeMapper.BOX_JVM_METHOD_NAME)) {
            return false;
        }
        Type objectType = Type.getObjectType(methodInsnNode.owner);
        objectType.getClass();
        GenerationState.MultiFieldValueClassUnboxInfo multiFieldValueClassUnboxInfo = BoxedBasicValueKt.getMultiFieldValueClassUnboxInfo(objectType, generationState);
        if (multiFieldValueClassUnboxInfo == null) {
            return false;
        }
        String str = methodInsnNode.desc;
        Type[] typeArr = (Type[]) multiFieldValueClassUnboxInfo.getUnboxedTypes().toArray(new Type[0]);
        return Intrinsics.areEqual(str, Type.getMethodDescriptor(objectType, (Type[]) Arrays.copyOf(typeArr, typeArr.length)));
    }

    private static final boolean isMultiFieldValueClassUnboxing(AbstractInsnNode abstractInsnNode, GenerationState generationState) {
        return generationState.getConfig().getSupportJvmInlineMultiFieldValueClasses() && abstractInsnNode.getOpcode() == 182 && (abstractInsnNode instanceof MethodInsnNode) && isMultiFieldValueClassUnboxingMethodDescriptor((MethodInsnNode) abstractInsnNode, generationState);
    }

    private static final boolean isMultiFieldValueClassUnboxingMethodDescriptor(MethodInsnNode methodInsnNode, GenerationState generationState) {
        Type objectType = Type.getObjectType(methodInsnNode.owner);
        objectType.getClass();
        GenerationState.MultiFieldValueClassUnboxInfo multiFieldValueClassUnboxInfo = BoxedBasicValueKt.getMultiFieldValueClassUnboxInfo(objectType, generationState);
        if (multiFieldValueClassUnboxInfo == null) {
            return false;
        }
        List<Triple<Type, String, String>> unboxedTypesAndMethodNamesAndFieldNames = multiFieldValueClassUnboxInfo.getUnboxedTypesAndMethodNamesAndFieldNames();
        if ((unboxedTypesAndMethodNamesAndFieldNames instanceof Collection) && unboxedTypesAndMethodNamesAndFieldNames.isEmpty()) {
            return false;
        }
        Iterator<T> it = unboxedTypesAndMethodNamesAndFieldNames.iterator();
        while (it.hasNext()) {
            Triple triple = (Triple) it.next();
            Type type = (Type) triple.component1();
            if (Intrinsics.areEqual(methodInsnNode.name, (String) triple.component2()) && Intrinsics.areEqual(methodInsnNode.desc, Type.getMethodDescriptor(type, new Type[0]))) {
                return true;
            }
        }
        return false;
    }

    public static final boolean isNextMethodCallOfProgressionIterator(AbstractInsnNode abstractInsnNode, List<? extends BasicValue> list) {
        abstractInsnNode.getClass();
        list.getClass();
        return (CollectionsKt.firstOrNull(list) instanceof ProgressionIteratorBasicValue) && abstractInsnNode.getOpcode() == 185 && (abstractInsnNode instanceof MethodInsnNode) && Intrinsics.areEqual(((MethodInsnNode) abstractInsnNode).name, "next");
    }

    public static final boolean isPrimitiveBoxing(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        if (abstractInsnNode.getOpcode() != 184 || !(abstractInsnNode instanceof MethodInsnNode)) {
            return false;
        }
        MethodInsnNode methodInsnNode = (MethodInsnNode) abstractInsnNode;
        String str = methodInsnNode.owner;
        str.getClass();
        return isWrapperClassName(str) && Intrinsics.areEqual(methodInsnNode.name, "valueOf") && isBoxingMethodDescriptor(methodInsnNode);
    }

    public static final boolean isPrimitiveUnboxing(AbstractInsnNode abstractInsnNode) {
        abstractInsnNode.getClass();
        if (abstractInsnNode.getOpcode() != 182 || !(abstractInsnNode instanceof MethodInsnNode)) {
            return false;
        }
        MethodInsnNode methodInsnNode = (MethodInsnNode) abstractInsnNode;
        String str = methodInsnNode.owner;
        str.getClass();
        if (!isWrapperClassNameOrNumber(str)) {
            return false;
        }
        String str2 = methodInsnNode.name;
        str2.getClass();
        return isUnboxingMethodName(str2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final boolean isProgressionClass(Type type) {
        return PROGRESSION_CLASS_FQNS.contains(type.getClassName());
    }

    public static final boolean isUnboxing(AbstractInsnNode abstractInsnNode, GenerationState generationState) {
        abstractInsnNode.getClass();
        generationState.getClass();
        return isPrimitiveUnboxing(abstractInsnNode) || isJavaLangClassUnboxing(abstractInsnNode) || isInlineClassUnboxing(abstractInsnNode, generationState) || isMultiFieldValueClassUnboxing(abstractInsnNode, generationState);
    }

    private static final boolean isUnboxingMethodName(String str) {
        return UNBOXING_METHOD_NAMES.contains(str);
    }

    private static final boolean isWrapperClassName(String str) {
        return JvmPrimitiveType.isWrapperClassInternalName(str);
    }

    private static final boolean isWrapperClassNameOrNumber(String str) {
        return isWrapperClassName(str) || Intrinsics.areEqual(str, Type.getInternalName(Number.class));
    }
}
