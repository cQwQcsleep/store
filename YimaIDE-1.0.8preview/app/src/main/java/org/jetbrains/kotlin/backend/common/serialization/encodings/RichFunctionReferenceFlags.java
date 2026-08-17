package org.jetbrains.kotlin.backend.common.serialization.encodings;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.expressions.IrRichFunctionReference;
import org.jetbrains.kotlin.metadata.deserialization.Flags;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\r\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u001e2\u00020\u0001:\u0001\u001eB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\u0012\u001a\u00020\t2\b\u0010\u0013\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b\u0014\u0010\u0015J\u0011\u0010\u0016\u001a\u00020\u0017HÖ\u0081\u0004¢\u0006\u0004\b\u0018\u0010\u0019J\u0011\u0010\u001a\u001a\u00020\u001bHÖ\u0081\u0004¢\u0006\u0004\b\u001c\u0010\u001dR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000bR\u0011\u0010\u0010\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0011\u0010\u000b\u0088\u0001\u0002¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/RichFunctionReferenceFlags;", "", "flags", "", "constructor-impl", "(J)J", "getFlags", "()J", "hasUnitConversion", "", "getHasUnitConversion-impl", "(J)Z", "hasSuspendConversion", "getHasSuspendConversion-impl", "hasVarargConversion", "getHasVarargConversion-impl", "isRestrictedSuspension", "isRestrictedSuspension-impl", "equals", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(J)I", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "Companion", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
public final class RichFunctionReferenceFlags {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final Flags.BooleanFlagField HAS_SUSPEND_CONVERSION;
    private static final Flags.BooleanFlagField HAS_UNIT_CONVERSION;
    private static final Flags.BooleanFlagField HAS_VARARG_CONVERSION;
    private static final Flags.BooleanFlagField IS_RESTRICTED_SUSPENSION;
    private final long flags;

    static {
        Flags.BooleanFlagField booleanFlagFieldBooleanFirst = Flags.FlagField.booleanFirst();
        booleanFlagFieldBooleanFirst.getClass();
        HAS_UNIT_CONVERSION = booleanFlagFieldBooleanFirst;
        Flags.BooleanFlagField booleanFlagFieldBooleanAfter = Flags.FlagField.booleanAfter(booleanFlagFieldBooleanFirst);
        booleanFlagFieldBooleanAfter.getClass();
        HAS_SUSPEND_CONVERSION = booleanFlagFieldBooleanAfter;
        Flags.BooleanFlagField booleanFlagFieldBooleanAfter2 = Flags.FlagField.booleanAfter(booleanFlagFieldBooleanAfter);
        booleanFlagFieldBooleanAfter2.getClass();
        HAS_VARARG_CONVERSION = booleanFlagFieldBooleanAfter2;
        Flags.BooleanFlagField booleanFlagFieldBooleanAfter3 = Flags.FlagField.booleanAfter(booleanFlagFieldBooleanAfter2);
        booleanFlagFieldBooleanAfter3.getClass();
        IS_RESTRICTED_SUSPENSION = booleanFlagFieldBooleanAfter3;
    }

    private /* synthetic */ RichFunctionReferenceFlags(long j) {
        this.flags = j;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ RichFunctionReferenceFlags m386boximpl(long j) {
        return new RichFunctionReferenceFlags(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m387constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m388equalsimpl(long j, Object obj) {
        return (obj instanceof RichFunctionReferenceFlags) && j == ((RichFunctionReferenceFlags) obj).m396unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m389equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: getHasSuspendConversion-impl, reason: not valid java name */
    public static final boolean m390getHasSuspendConversionimpl(long j) {
        Boolean bool = HAS_SUSPEND_CONVERSION.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: getHasUnitConversion-impl, reason: not valid java name */
    public static final boolean m391getHasUnitConversionimpl(long j) {
        Boolean bool = HAS_UNIT_CONVERSION.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: getHasVarargConversion-impl, reason: not valid java name */
    public static final boolean m392getHasVarargConversionimpl(long j) {
        Boolean bool = HAS_VARARG_CONVERSION.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m393hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    /* JADX INFO: renamed from: isRestrictedSuspension-impl, reason: not valid java name */
    public static final boolean m394isRestrictedSuspensionimpl(long j) {
        Boolean bool = IS_RESTRICTED_SUSPENSION.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m395toStringimpl(long j) {
        return "RichFunctionReferenceFlags(flags=" + j + Util.C_PARAM_END;
    }

    public boolean equals(Object obj) {
        return m388equalsimpl(this.flags, obj);
    }

    public final long getFlags() {
        return this.flags;
    }

    public int hashCode() {
        return m393hashCodeimpl(this.flags);
    }

    public String toString() {
        return m395toStringimpl(this.flags);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m396unboximpl() {
        return this.flags;
    }

    @Metadata(d1 = {"\u0000(\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\u0011J\u0015\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u000f¢\u0006\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0004\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0011\u0010\n\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0011\u0010\f\u001a\u00020\u0005¢\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u0007¨\u0006\u0017"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/RichFunctionReferenceFlags$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "HAS_UNIT_CONVERSION", "Lorg/jetbrains/kotlin/metadata/deserialization/Flags$BooleanFlagField;", "getHAS_UNIT_CONVERSION", "()Lorg/jetbrains/kotlin/metadata/deserialization/Flags$BooleanFlagField;", "HAS_SUSPEND_CONVERSION", "getHAS_SUSPEND_CONVERSION", "HAS_VARARG_CONVERSION", "getHAS_VARARG_CONVERSION", "IS_RESTRICTED_SUSPENSION", "getIS_RESTRICTED_SUSPENSION", "encode", "", "reference", "Lorg/jetbrains/kotlin/ir/expressions/IrRichFunctionReference;", "decode", "Lorg/jetbrains/kotlin/backend/common/serialization/encodings/RichFunctionReferenceFlags;", "code", "decode-YNQTm0I", "(J)J", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: decode-YNQTm0I, reason: not valid java name */
        public final long m397decodeYNQTm0I(long code) {
            return RichFunctionReferenceFlags.m387constructorimpl(code);
        }

        public final long encode(IrRichFunctionReference reference) {
            reference.getClass();
            Companion companion = RichFunctionReferenceFlags.INSTANCE;
            return companion.getIS_RESTRICTED_SUSPENSION().toFlags(Boolean.valueOf(reference.isRestrictedSuspension())) | companion.getHAS_UNIT_CONVERSION().toFlags(Boolean.valueOf(reference.getHasUnitConversion())) | companion.getHAS_SUSPEND_CONVERSION().toFlags(Boolean.valueOf(reference.getHasSuspendConversion())) | companion.getHAS_VARARG_CONVERSION().toFlags(Boolean.valueOf(reference.getHasVarargConversion()));
        }

        public final Flags.BooleanFlagField getHAS_SUSPEND_CONVERSION() {
            return RichFunctionReferenceFlags.HAS_SUSPEND_CONVERSION;
        }

        public final Flags.BooleanFlagField getHAS_UNIT_CONVERSION() {
            return RichFunctionReferenceFlags.HAS_UNIT_CONVERSION;
        }

        public final Flags.BooleanFlagField getHAS_VARARG_CONVERSION() {
            return RichFunctionReferenceFlags.HAS_VARARG_CONVERSION;
        }

        public final Flags.BooleanFlagField getIS_RESTRICTED_SUSPENSION() {
            return RichFunctionReferenceFlags.IS_RESTRICTED_SUSPENSION;
        }

        private Companion() {
        }
    }
}
