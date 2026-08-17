package org.jetbrains.kotlin.resolve;

import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.DeclarationDescriptor;
import org.jetbrains.kotlin.name.Name;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\n\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0014\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007b\u0002\b\u0014J\u0014\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007b\u0002\b\u0014J\u0014\u0010\u0016\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007b\u0002\b\u0014J\u0014\u0010\u0017\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0007b\u0002\b\u0014J\u0018\u0010\u0018\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0019\u001a\u00020\u0005H\u0002J\u0010\u0010\u001a\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0013H\u0002R\u0015\u0010\u0004\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006¢\u0006\u0002\n\u0000R\u0015\u0010\u0007\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006¢\u0006\u0002\n\u0000R\u0015\u0010\b\u001a\u00020\u00058\u0006X\u0087\u0004\u0092\u0002\u0002\b\u0006¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000f\u0010\u000b¨\u0006\u001b"}, d2 = {"Lorg/jetbrains/kotlin/resolve/InlineClassDescriptorResolver;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "BOX_METHOD_NAME", "Lorg/jetbrains/kotlin/name/Name;", "Lkotlin/jvm/JvmField;", "UNBOX_METHOD_NAME", "SPECIALIZED_EQUALS_NAME", "BOXING_VALUE_PARAMETER_NAME", "getBOXING_VALUE_PARAMETER_NAME", "()Lorg/jetbrains/kotlin/name/Name;", "SPECIALIZED_EQUALS_FIRST_PARAMETER_NAME", "getSPECIALIZED_EQUALS_FIRST_PARAMETER_NAME", "SPECIALIZED_EQUALS_SECOND_PARAMETER_NAME", "getSPECIALIZED_EQUALS_SECOND_PARAMETER_NAME", "isSynthesizedBoxMethod", "", "descriptor", "Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor;", "Lkotlin/jvm/JvmStatic;", "isSynthesizedUnboxMethod", "isSynthesizedBoxOrUnboxMethod", "isSpecializedEqualsMethod", "isSynthesizedInlineClassMemberWithName", "name", "isSynthesizedInlineClassMember", "org.jetbrains.kotlin:frontend"}, k = 1, mv = {2, 4, 0}, xi = 48)
public final class InlineClassDescriptorResolver {
    private static final Name BOXING_VALUE_PARAMETER_NAME;
    public static final Name BOX_METHOD_NAME;
    public static final InlineClassDescriptorResolver INSTANCE = new InlineClassDescriptorResolver();
    private static final Name SPECIALIZED_EQUALS_FIRST_PARAMETER_NAME;
    public static final Name SPECIALIZED_EQUALS_NAME;
    private static final Name SPECIALIZED_EQUALS_SECOND_PARAMETER_NAME;
    public static final Name UNBOX_METHOD_NAME;

    static {
        Name nameIdentifier = Name.identifier("box");
        nameIdentifier.getClass();
        BOX_METHOD_NAME = nameIdentifier;
        Name nameIdentifier2 = Name.identifier("unbox");
        nameIdentifier2.getClass();
        UNBOX_METHOD_NAME = nameIdentifier2;
        Name nameIdentifier3 = Name.identifier("equals-impl0");
        nameIdentifier3.getClass();
        SPECIALIZED_EQUALS_NAME = nameIdentifier3;
        Name nameIdentifier4 = Name.identifier("v");
        nameIdentifier4.getClass();
        BOXING_VALUE_PARAMETER_NAME = nameIdentifier4;
        Name nameIdentifier5 = Name.identifier("p1");
        nameIdentifier5.getClass();
        SPECIALIZED_EQUALS_FIRST_PARAMETER_NAME = nameIdentifier5;
        Name nameIdentifier6 = Name.identifier("p2");
        nameIdentifier6.getClass();
        SPECIALIZED_EQUALS_SECOND_PARAMETER_NAME = nameIdentifier6;
    }

    private InlineClassDescriptorResolver() {
    }

    @JvmStatic
    public static final boolean isSpecializedEqualsMethod(CallableMemberDescriptor descriptor) {
        descriptor.getClass();
        return INSTANCE.isSynthesizedInlineClassMemberWithName(descriptor, SPECIALIZED_EQUALS_NAME);
    }

    @JvmStatic
    public static final boolean isSynthesizedBoxMethod(CallableMemberDescriptor descriptor) {
        descriptor.getClass();
        return INSTANCE.isSynthesizedInlineClassMemberWithName(descriptor, BOX_METHOD_NAME);
    }

    @JvmStatic
    public static final boolean isSynthesizedBoxOrUnboxMethod(CallableMemberDescriptor descriptor) {
        descriptor.getClass();
        if (INSTANCE.isSynthesizedInlineClassMember(descriptor)) {
            return Intrinsics.areEqual(descriptor.getName(), BOX_METHOD_NAME) || Intrinsics.areEqual(descriptor.getName(), UNBOX_METHOD_NAME);
        }
        return false;
    }

    private final boolean isSynthesizedInlineClassMember(CallableMemberDescriptor descriptor) {
        if (descriptor.getKind() != CallableMemberDescriptor.Kind.SYNTHESIZED) {
            return false;
        }
        DeclarationDescriptor containingDeclaration = descriptor.getContainingDeclaration();
        containingDeclaration.getClass();
        return InlineClassesUtilsKt.isInlineClass(containingDeclaration);
    }

    private final boolean isSynthesizedInlineClassMemberWithName(CallableMemberDescriptor descriptor, Name name) {
        return isSynthesizedInlineClassMember(descriptor) && Intrinsics.areEqual(descriptor.getName(), name);
    }

    @JvmStatic
    public static final boolean isSynthesizedUnboxMethod(CallableMemberDescriptor descriptor) {
        descriptor.getClass();
        return INSTANCE.isSynthesizedInlineClassMemberWithName(descriptor, UNBOX_METHOD_NAME);
    }

    public final Name getBOXING_VALUE_PARAMETER_NAME() {
        return BOXING_VALUE_PARAMETER_NAME;
    }

    public final Name getSPECIALIZED_EQUALS_FIRST_PARAMETER_NAME() {
        return SPECIALIZED_EQUALS_FIRST_PARAMETER_NAME;
    }

    public final Name getSPECIALIZED_EQUALS_SECOND_PARAMETER_NAME() {
        return SPECIALIZED_EQUALS_SECOND_PARAMETER_NAME;
    }
}
