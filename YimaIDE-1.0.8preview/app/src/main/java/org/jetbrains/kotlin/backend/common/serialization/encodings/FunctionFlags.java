package org.jetbrains.kotlin.backend.common.serialization.encodings;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.backend.common.serialization.IrFlags;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.ir.declarations.IrConstructor;
import org.jetbrains.kotlin.ir.declarations.IrSimpleFunction;
import org.jetbrains.kotlin.load.java.JvmAbi;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.Flags;
import org.jetbrains.kotlin.serialization.deserialization.ProtoEnumFlags;
import org.jetbrains.kotlin.serialization.deserialization.ProtoEnumFlagsUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0013\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 42\u00020\u0001:\u00014B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010$\u001a\u00020%H\u0002¢\u0006\u0004\b&\u0010'J\u001b\u0010(\u001a\u00020\u00112\b\u0010)\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b*\u0010+J\u0011\u0010,\u001a\u00020-HÖ\u0081\u0004¢\u0006\u0004\b.\u0010/J\u0011\u00100\u001a\u000201HÖ\u0081\u0004¢\u0006\u0004\b2\u00103R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0016\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\u0018\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\u001a\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0013R\u0011\u0010\u001c\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0013R\u0011\u0010\u001e\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0013R\u0011\u0010 \u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b!\u0010\u0013R\u0011\u0010\"\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b#\u0010\u0013\u0088\u0001\u0002¨\u00065"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/FunctionFlags;", "", "flags", "", "constructor-impl", "(J)J", "getFlags", "()J", "modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "getModality-impl", "(J)Lorg/jetbrains/kotlin/descriptors/Modality;", "visibility", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "getVisibility-impl", "(J)Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "isOperator", "", "isOperator-impl", "(J)Z", "isInfix", "isInfix-impl", "isInline", "isInline-impl", "isTailrec", "isTailrec-impl", "isExternal", "isExternal-impl", "isSuspend", "isSuspend-impl", "isExpect", "isExpect-impl", "isFakeOverride", "isFakeOverride-impl", "isPrimary", "isPrimary-impl", "kind", "Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor$Kind;", "kind-impl", "(J)Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor$Kind;", "equals", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(J)I", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "Companion", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
public final class FunctionFlags {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final long flags;

    private /* synthetic */ FunctionFlags(long j) {
        this.flags = j;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ FunctionFlags m337boximpl(long j) {
        return new FunctionFlags(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m338constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m339equalsimpl(long j, Object obj) {
        return (obj instanceof FunctionFlags) && j == ((FunctionFlags) obj).m355unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m340equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: getModality-impl, reason: not valid java name */
    public static final Modality m341getModalityimpl(long j) {
        return ProtoEnumFlags.INSTANCE.modality((ProtoBuf.Modality) Flags.MODALITY.get((int) j));
    }

    /* JADX INFO: renamed from: getVisibility-impl, reason: not valid java name */
    public static final DescriptorVisibility m342getVisibilityimpl(long j) {
        return ProtoEnumFlagsUtilsKt.descriptorVisibility(ProtoEnumFlags.INSTANCE, (ProtoBuf.Visibility) Flags.VISIBILITY.get((int) j));
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m343hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    /* JADX INFO: renamed from: isExpect-impl, reason: not valid java name */
    public static final boolean m344isExpectimpl(long j) {
        Boolean bool = Flags.IS_EXPECT_FUNCTION.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isExternal-impl, reason: not valid java name */
    public static final boolean m345isExternalimpl(long j) {
        Boolean bool = Flags.IS_EXTERNAL_FUNCTION.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isFakeOverride-impl, reason: not valid java name */
    public static final boolean m346isFakeOverrideimpl(long j) {
        return m353kindimpl(j) == CallableMemberDescriptor.Kind.FAKE_OVERRIDE;
    }

    /* JADX INFO: renamed from: isInfix-impl, reason: not valid java name */
    public static final boolean m347isInfiximpl(long j) {
        Boolean bool = Flags.IS_INFIX.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isInline-impl, reason: not valid java name */
    public static final boolean m348isInlineimpl(long j) {
        Boolean bool = Flags.IS_INLINE.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isOperator-impl, reason: not valid java name */
    public static final boolean m349isOperatorimpl(long j) {
        Boolean bool = Flags.IS_OPERATOR.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isPrimary-impl, reason: not valid java name */
    public static final boolean m350isPrimaryimpl(long j) {
        Boolean bool = IrFlags.IS_PRIMARY.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isSuspend-impl, reason: not valid java name */
    public static final boolean m351isSuspendimpl(long j) {
        Boolean bool = Flags.IS_SUSPEND.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isTailrec-impl, reason: not valid java name */
    public static final boolean m352isTailrecimpl(long j) {
        Boolean bool = Flags.IS_TAILREC.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: kind-impl, reason: not valid java name */
    private static final CallableMemberDescriptor.Kind m353kindimpl(long j) {
        return ProtoEnumFlagsUtilsKt.memberKind(ProtoEnumFlags.INSTANCE, (ProtoBuf.MemberKind) Flags.MEMBER_KIND.get((int) j));
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m354toStringimpl(long j) {
        return "FunctionFlags(flags=" + j + Util.C_PARAM_END;
    }

    public boolean equals(Object obj) {
        return m339equalsimpl(this.flags, obj);
    }

    public final long getFlags() {
        return this.flags;
    }

    public int hashCode() {
        return m343hashCodeimpl(this.flags);
    }

    public String toString() {
        return m354toStringimpl(this.flags);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m355unboximpl() {
        return this.flags;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/FunctionFlags$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "encode", "", "function", "Lorg/jetbrains/kotlin/ir/declarations/IrSimpleFunction;", JvmAbi.ERASED_INLINE_CONSTRUCTOR_NAME, "Lorg/jetbrains/kotlin/ir/declarations/IrConstructor;", "decode", "Lorg/jetbrains/kotlin/backend/common/serialization/encodings/FunctionFlags;", "code", "decode-UIJRpeM", "(J)J", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: decode-UIJRpeM, reason: not valid java name */
        public final long m356decodeUIJRpeM(long code) {
            return FunctionFlags.m338constructorimpl(code);
        }

        public final long encode(IrSimpleFunction function) {
            function.getClass();
            boolean z = !function.getAnnotations().isEmpty();
            ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
            return Flags.getFunctionFlags(z, ProtoEnumFlagsUtilsKt.descriptorVisibility(protoEnumFlags, function.getVisibility().normalize()), protoEnumFlags.modality(function.getModality()), function.isFakeOverride() ? ProtoBuf.MemberKind.FAKE_OVERRIDE : ProtoBuf.MemberKind.DECLARATION, function.isOperator(), function.isInfix(), function.isInline(), function.isTailrec(), function.isExternal(), function.isSuspend(), function.isExpect(), true, false, ProtoBuf.ReturnValueStatus.UNSPECIFIED);
        }

        private Companion() {
        }

        public final long encode(IrConstructor constructor) {
            constructor.getClass();
            return IrFlags.getConstructorFlags(!constructor.getAnnotations().isEmpty(), ProtoEnumFlagsUtilsKt.descriptorVisibility(ProtoEnumFlags.INSTANCE, constructor.getVisibility().normalize()), constructor.isInline(), constructor.isExternal(), constructor.isExpect(), constructor.isPrimary());
        }
    }
}
