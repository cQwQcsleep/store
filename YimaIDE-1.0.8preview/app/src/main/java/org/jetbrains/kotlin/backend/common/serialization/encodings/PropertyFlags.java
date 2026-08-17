package org.jetbrains.kotlin.backend.common.serialization.encodings;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.CallableMemberDescriptor;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.descriptors.Modality;
import org.jetbrains.kotlin.ir.declarations.IrProperty;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.Flags;
import org.jetbrains.kotlin.serialization.deserialization.ProtoEnumFlags;
import org.jetbrains.kotlin.serialization.deserialization.ProtoEnumFlagsUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 02\u00020\u0001:\u00010B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010 \u001a\u00020!H\u0002¢\u0006\u0004\b\"\u0010#J\u001b\u0010$\u001a\u00020\u00112\b\u0010%\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b&\u0010'J\u0011\u0010(\u001a\u00020)HÖ\u0081\u0004¢\u0006\u0004\b*\u0010+J\u0011\u0010,\u001a\u00020-HÖ\u0081\u0004¢\u0006\u0004\b.\u0010/R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u0013R\u0011\u0010\u0016\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0013R\u0011\u0010\u0018\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0019\u0010\u0013R\u0011\u0010\u001a\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u0013R\u0011\u0010\u001c\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u001d\u0010\u0013R\u0011\u0010\u001e\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u001f\u0010\u0013\u0088\u0001\u0002¨\u00061"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/PropertyFlags;", "", "flags", "", "constructor-impl", "(J)J", "getFlags", "()J", "modality", "Lorg/jetbrains/kotlin/descriptors/Modality;", "getModality-impl", "(J)Lorg/jetbrains/kotlin/descriptors/Modality;", "visibility", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "getVisibility-impl", "(J)Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "isVar", "", "isVar-impl", "(J)Z", "isConst", "isConst-impl", "isLateinit", "isLateinit-impl", "isExternal", "isExternal-impl", "isDelegated", "isDelegated-impl", "isExpect", "isExpect-impl", "isFakeOverride", "isFakeOverride-impl", "kind", "Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor$Kind;", "kind-impl", "(J)Lorg/jetbrains/kotlin/descriptors/CallableMemberDescriptor$Kind;", "equals", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(J)I", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "Companion", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
public final class PropertyFlags {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final long flags;

    private /* synthetic */ PropertyFlags(long j) {
        this.flags = j;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ PropertyFlags m368boximpl(long j) {
        return new PropertyFlags(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m369constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m370equalsimpl(long j, Object obj) {
        return (obj instanceof PropertyFlags) && j == ((PropertyFlags) obj).m384unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m371equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: getModality-impl, reason: not valid java name */
    public static final Modality m372getModalityimpl(long j) {
        return ProtoEnumFlags.INSTANCE.modality((ProtoBuf.Modality) Flags.MODALITY.get((int) j));
    }

    /* JADX INFO: renamed from: getVisibility-impl, reason: not valid java name */
    public static final DescriptorVisibility m373getVisibilityimpl(long j) {
        return ProtoEnumFlagsUtilsKt.descriptorVisibility(ProtoEnumFlags.INSTANCE, (ProtoBuf.Visibility) Flags.VISIBILITY.get((int) j));
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m374hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    /* JADX INFO: renamed from: isConst-impl, reason: not valid java name */
    public static final boolean m375isConstimpl(long j) {
        Boolean bool = Flags.IS_CONST.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isDelegated-impl, reason: not valid java name */
    public static final boolean m376isDelegatedimpl(long j) {
        Boolean bool = Flags.IS_DELEGATED.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isExpect-impl, reason: not valid java name */
    public static final boolean m377isExpectimpl(long j) {
        Boolean bool = Flags.IS_EXPECT_PROPERTY.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isExternal-impl, reason: not valid java name */
    public static final boolean m378isExternalimpl(long j) {
        Boolean bool = Flags.IS_EXTERNAL_PROPERTY.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isFakeOverride-impl, reason: not valid java name */
    public static final boolean m379isFakeOverrideimpl(long j) {
        return m382kindimpl(j) == CallableMemberDescriptor.Kind.FAKE_OVERRIDE;
    }

    /* JADX INFO: renamed from: isLateinit-impl, reason: not valid java name */
    public static final boolean m380isLateinitimpl(long j) {
        Boolean bool = Flags.IS_LATEINIT.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isVar-impl, reason: not valid java name */
    public static final boolean m381isVarimpl(long j) {
        Boolean bool = Flags.IS_VAR.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: kind-impl, reason: not valid java name */
    private static final CallableMemberDescriptor.Kind m382kindimpl(long j) {
        return ProtoEnumFlagsUtilsKt.memberKind(ProtoEnumFlags.INSTANCE, (ProtoBuf.MemberKind) Flags.MEMBER_KIND.get((int) j));
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m383toStringimpl(long j) {
        return "PropertyFlags(flags=" + j + Util.C_PARAM_END;
    }

    public boolean equals(Object obj) {
        return m370equalsimpl(this.flags, obj);
    }

    public final long getFlags() {
        return this.flags;
    }

    public int hashCode() {
        return m374hashCodeimpl(this.flags);
    }

    public String toString() {
        return m383toStringimpl(this.flags);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m384unboximpl() {
        return this.flags;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/PropertyFlags$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "encode", "", "property", "Lorg/jetbrains/kotlin/ir/declarations/IrProperty;", "decode", "Lorg/jetbrains/kotlin/backend/common/serialization/encodings/PropertyFlags;", "code", "decode-85tB66k", "(J)J", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: decode-85tB66k, reason: not valid java name */
        public final long m385decode85tB66k(long code) {
            return PropertyFlags.m369constructorimpl(code);
        }

        public final long encode(IrProperty property) {
            property.getClass();
            boolean z = !property.getAnnotations().isEmpty();
            ProtoEnumFlags protoEnumFlags = ProtoEnumFlags.INSTANCE;
            return Flags.getPropertyFlags(z, ProtoEnumFlagsUtilsKt.descriptorVisibility(protoEnumFlags, property.getVisibility().normalize()), protoEnumFlags.modality(property.getModality()), property.isFakeOverride() ? ProtoBuf.MemberKind.FAKE_OVERRIDE : ProtoBuf.MemberKind.DECLARATION, property.isVar(), property.getGetter() != null, property.getSetter() != null, false, property.isConst(), property.isLateinit(), property.isExternal(), property.isDelegated(), property.isExpect(), false, ProtoBuf.ReturnValueStatus.UNSPECIFIED);
        }

        private Companion() {
        }
    }
}
