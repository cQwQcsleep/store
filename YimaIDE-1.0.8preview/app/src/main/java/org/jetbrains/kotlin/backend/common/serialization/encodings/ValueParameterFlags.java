package org.jetbrains.kotlin.backend.common.serialization.encodings;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.backend.common.serialization.IrFlags;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrValueParameter;
import org.jetbrains.kotlin.metadata.deserialization.Flags;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\u0012\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004¢\u0006\u0004\b\u0018\u0010\u0019J\u0011\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004¢\u0006\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000b\u0088\u0001\u0002¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/ValueParameterFlags;", "", "flags", "", "constructor-impl", "(J)J", "getFlags", "()J", "isCrossInline", "", "isCrossInline-impl", "(J)Z", "isNoInline", "isNoInline-impl", "isHidden", "isHidden-impl", "isAssignable", "isAssignable-impl", "equals", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(J)I", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "Companion", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
public final class ValueParameterFlags {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final long flags;

    private /* synthetic */ ValueParameterFlags(long j) {
        this.flags = j;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ ValueParameterFlags m418boximpl(long j) {
        return new ValueParameterFlags(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m419constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m420equalsimpl(long j, Object obj) {
        return (obj instanceof ValueParameterFlags) && j == ((ValueParameterFlags) obj).m428unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m421equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m422hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    /* JADX INFO: renamed from: isAssignable-impl, reason: not valid java name */
    public static final boolean m423isAssignableimpl(long j) {
        Boolean bool = IrFlags.IS_ASSIGNABLE.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isCrossInline-impl, reason: not valid java name */
    public static final boolean m424isCrossInlineimpl(long j) {
        Boolean bool = Flags.IS_CROSSINLINE.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isHidden-impl, reason: not valid java name */
    public static final boolean m425isHiddenimpl(long j) {
        Boolean bool = IrFlags.IS_HIDDEN.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isNoInline-impl, reason: not valid java name */
    public static final boolean m426isNoInlineimpl(long j) {
        Boolean bool = Flags.IS_NOINLINE.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m427toStringimpl(long j) {
        return "ValueParameterFlags(flags=" + j + Util.C_PARAM_END;
    }

    public boolean equals(Object obj) {
        return m420equalsimpl(this.flags, obj);
    }

    public final long getFlags() {
        return this.flags;
    }

    public int hashCode() {
        return m422hashCodeimpl(this.flags);
    }

    public String toString() {
        return m427toStringimpl(this.flags);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m428unboximpl() {
        return this.flags;
    }

    @Metadata(d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u0015\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u0005¢\u0006\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/ValueParameterFlags$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "encode", "", "param", "Lorg/jetbrains/kotlin/ir/declarations/IrValueParameter;", "decode", "Lorg/jetbrains/kotlin/backend/common/serialization/encodings/ValueParameterFlags;", "code", "decode-eyNVhwA", "(J)J", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: decode-eyNVhwA, reason: not valid java name */
        public final long m429decodeeyNVhwA(long code) {
            return ValueParameterFlags.m419constructorimpl(code);
        }

        public final long encode(IrValueParameter param) {
            param.getClass();
            return IrFlags.getValueParameterFlags(!param.getAnnotations().isEmpty(), param.getDefaultValue() != null, param.isCrossinline(), param.isNoinline(), param.isHidden(), param.isAssignable());
        }

        private Companion() {
        }
    }
}
