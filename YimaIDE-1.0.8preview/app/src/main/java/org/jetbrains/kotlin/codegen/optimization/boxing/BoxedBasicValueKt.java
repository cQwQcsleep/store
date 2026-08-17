package org.jetbrains.kotlin.codegen.optimization.boxing;

import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.cli.common.arguments.Argument;
import org.jetbrains.kotlin.codegen.AsmUtil;
import org.jetbrains.kotlin.codegen.state.GenerationState;
import org.jetbrains.kotlin.config.MavenComparableVersion;
import org.jetbrains.kotlin.descriptors.ClassDescriptor;
import org.jetbrains.kotlin.resolve.InlineClassesUtilsKt;
import org.jetbrains.kotlin.resolve.jvm.AsmTypes;
import org.jetbrains.org.objectweb.asm.Type;
import org.jetbrains.org.objectweb.asm.tree.InsnList;
import org.jetbrains.org.objectweb.asm.tree.InsnNode;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
@Metadata(d1 = {"\u0000\"\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a\u0016\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0000\u001a&\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b2\b\u0010\t\u001a\u0004\u0018\u00010\n\u001a\u0018\u0010\u000b\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b\u001a\u0018\u0010\f\u001a\u0004\u0018\u00010\n2\u0006\u0010\u0006\u001a\u00020\u00042\u0006\u0010\u0007\u001a\u00020\b¨\u0006\r"}, d2 = {"makePops", "Lorg/jetbrains/org/objectweb/asm/tree/InsnList;", "unboxedTypes", Argument.Delimiters.none, "Lorg/jetbrains/org/objectweb/asm/Type;", "getUnboxedTypes", "boxedType", "state", "Lorg/jetbrains/kotlin/codegen/state/GenerationState;", "multiFieldValueClassUnboxInfo", "Lorg/jetbrains/kotlin/codegen/state/GenerationState$MultiFieldValueClassUnboxInfo;", "unboxedTypeOfInlineClass", "getMultiFieldValueClassUnboxInfo", "org.jetbrains.kotlin:backend"}, k = MavenComparableVersion.Item.LIST_ITEM, mv = {MavenComparableVersion.Item.LIST_ITEM, 4, MavenComparableVersion.Item.INTEGER_ITEM}, xi = 48)
public final class BoxedBasicValueKt {
    public static final GenerationState.MultiFieldValueClassUnboxInfo getMultiFieldValueClassUnboxInfo(Type type, GenerationState generationState) {
        ClassDescriptor classDescriptor;
        type.getClass();
        generationState.getClass();
        if (generationState.getConfig().getSupportJvmInlineMultiFieldValueClasses() && (classDescriptor = (ClassDescriptor) CollectionsKt.singleOrNull(generationState.getJvmBackendClassResolver().resolveToClassDescriptors(type))) != null) {
            if (!InlineClassesUtilsKt.isMultiFieldValueClass(classDescriptor)) {
                classDescriptor = null;
            }
            if (classDescriptor != null) {
                return (GenerationState.MultiFieldValueClassUnboxInfo) generationState.getMultiFieldValueClassUnboxInfo().invoke(classDescriptor);
            }
        }
        return null;
    }

    public static final List<Type> getUnboxedTypes(Type type, GenerationState generationState, GenerationState.MultiFieldValueClassUnboxInfo multiFieldValueClassUnboxInfo) {
        type.getClass();
        generationState.getClass();
        Type typeUnboxPrimitiveTypeOrNull = AsmUtil.unboxPrimitiveTypeOrNull(type);
        if (typeUnboxPrimitiveTypeOrNull != null) {
            return CollectionsKt.listOf(typeUnboxPrimitiveTypeOrNull);
        }
        if (Intrinsics.areEqual(type, AsmTypes.K_CLASS_TYPE)) {
            return CollectionsKt.listOf(AsmTypes.JAVA_CLASS_TYPE);
        }
        Type typeUnboxedTypeOfInlineClass = unboxedTypeOfInlineClass(type, generationState);
        if (typeUnboxedTypeOfInlineClass != null) {
            return CollectionsKt.listOf(typeUnboxedTypeOfInlineClass);
        }
        if (multiFieldValueClassUnboxInfo != null) {
            return multiFieldValueClassUnboxInfo.getUnboxedTypes();
        }
        aca.a("Expected primitive type wrapper or KClass or inline class wrapper, got: ", type);
        return null;
    }

    public static final InsnList makePops(List<Type> list) {
        list.getClass();
        InsnList insnList = new InsnList();
        Iterator it = CollectionsKt.asReversed(list).iterator();
        while (true) {
            boolean z = false;
            while (true) {
                if (!it.hasNext()) {
                    if (z) {
                        insnList.add(new InsnNode(87));
                    }
                    return insnList;
                }
                Type type = (Type) it.next();
                int size = type.getSize();
                if (size == 1) {
                    if (z) {
                        insnList.add(new InsnNode(88));
                    }
                    if (z) {
                        break;
                    }
                    z = true;
                } else {
                    if (size != 2) {
                        l6d.a("Illegal type size: ", type.getSize());
                        return null;
                    }
                    if (z) {
                        insnList.add(new InsnNode(87));
                    }
                    insnList.add(new InsnNode(88));
                    break;
                }
            }
        }
    }

    public static final Type unboxedTypeOfInlineClass(Type type, GenerationState generationState) {
        type.getClass();
        generationState.getClass();
        ClassDescriptor classDescriptor = (ClassDescriptor) CollectionsKt.singleOrNull(generationState.getJvmBackendClassResolver().resolveToClassDescriptors(type));
        if (classDescriptor != null) {
            if (!InlineClassesUtilsKt.isInlineClass(classDescriptor)) {
                classDescriptor = null;
            }
            if (classDescriptor != null) {
                return (Type) generationState.getMapInlineClass().invoke(classDescriptor);
            }
        }
        return null;
    }
}
