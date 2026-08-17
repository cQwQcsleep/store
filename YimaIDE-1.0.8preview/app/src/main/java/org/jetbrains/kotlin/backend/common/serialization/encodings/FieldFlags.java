package org.jetbrains.kotlin.backend.common.serialization.encodings;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.backend.common.serialization.IrFlags;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.descriptors.DescriptorVisibility;
import org.jetbrains.kotlin.ir.declarations.IrField;
import org.jetbrains.kotlin.metadata.ProtoBuf;
import org.jetbrains.kotlin.metadata.deserialization.Flags;
import org.jetbrains.kotlin.serialization.deserialization.ProtoEnumFlags;
import org.jetbrains.kotlin.serialization.deserialization.ProtoEnumFlagsUtilsKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000  2\u00020\u0001:\u0001 B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\u0014\u001a\u00020\r2\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004¢\u0006\u0004\b\u001a\u0010\u001bJ\u0011\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004¢\u0006\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000fR\u0011\u0010\u0012\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u0013\u0010\u000f\u0088\u0001\u0002¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/FieldFlags;", "", "flags", "", "constructor-impl", "(J)J", "getFlags", "()J", "visibility", "Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "getVisibility-impl", "(J)Lorg/jetbrains/kotlin/descriptors/DescriptorVisibility;", "isFinal", "", "isFinal-impl", "(J)Z", "isExternal", "isExternal-impl", "isStatic", "isStatic-impl", "equals", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(J)I", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "Companion", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
public final class FieldFlags {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final long flags;

    private /* synthetic */ FieldFlags(long j) {
        this.flags = j;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ FieldFlags m325boximpl(long j) {
        return new FieldFlags(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m326constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m327equalsimpl(long j, Object obj) {
        return (obj instanceof FieldFlags) && j == ((FieldFlags) obj).m335unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m328equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: getVisibility-impl, reason: not valid java name */
    public static final DescriptorVisibility m329getVisibilityimpl(long j) {
        return ProtoEnumFlagsUtilsKt.descriptorVisibility(ProtoEnumFlags.INSTANCE, (ProtoBuf.Visibility) Flags.VISIBILITY.get((int) j));
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m330hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    /* JADX INFO: renamed from: isExternal-impl, reason: not valid java name */
    public static final boolean m331isExternalimpl(long j) {
        Boolean bool = IrFlags.IS_EXTERNAL_FIELD.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isFinal-impl, reason: not valid java name */
    public static final boolean m332isFinalimpl(long j) {
        Boolean bool = IrFlags.IS_FINAL.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isStatic-impl, reason: not valid java name */
    public static final boolean m333isStaticimpl(long j) {
        Boolean bool = IrFlags.IS_STATIC.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m334toStringimpl(long j) {
        return "FieldFlags(flags=" + j + Util.C_PARAM_END;
    }

    public boolean equals(Object obj) {
        return m327equalsimpl(this.flags, obj);
    }

    public final long getFlags() {
        return this.flags;
    }

    public int hashCode() {
        return m330hashCodeimpl(this.flags);
    }

    public String toString() {
        return m334toStringimpl(this.flags);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m335unboximpl() {
        return this.flags;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/FieldFlags$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "encode", "", "field", "Lorg/jetbrains/kotlin/ir/declarations/IrField;", "decode", "Lorg/jetbrains/kotlin/backend/common/serialization/encodings/FieldFlags;", "code", "decode-TfoGUTA", "(J)J", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: decode-TfoGUTA, reason: not valid java name */
        public final long m336decodeTfoGUTA(long code) {
            return FieldFlags.m326constructorimpl(code);
        }

        public final long encode(IrField field) {
            field.getClass();
            return IrFlags.getFieldFlags(!field.getAnnotations().isEmpty(), ProtoEnumFlagsUtilsKt.descriptorVisibility(ProtoEnumFlags.INSTANCE, field.getVisibility().normalize()), field.isFinal(), field.isExternal(), field.isStatic());
        }

        private Companion() {
        }
    }
}
