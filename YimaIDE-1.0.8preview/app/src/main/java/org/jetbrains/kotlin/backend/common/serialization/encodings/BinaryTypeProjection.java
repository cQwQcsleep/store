package org.jetbrains.kotlin.backend.common.serialization.encodings;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.types.Variance;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000  2\u00020\u0001:\u0001 B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\b\u001a\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\u0016\u001a\u00020\r2\b\u0010\u0017\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b\u0018\u0010\u0019J\u0011\u0010\u001a\u001a\u00020\tHÖ\u0081\u0004¢\u0006\u0004\b\u001b\u0010\u000bJ\u0011\u0010\u001c\u001a\u00020\u001dHÖ\u0081\u0004¢\u0006\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\f\u001a\u00020\r8F¢\u0006\u0006\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0010\u001a\u00020\u00118F¢\u0006\u0006\u001a\u0004\b\u0012\u0010\u0013R\u0011\u0010\u0014\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u0015\u0010\u000b\u0088\u0001\u0002¨\u0006!"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/BinaryTypeProjection;", "", "code", "", "constructor-impl", "(J)J", "getCode", "()J", "varianceId", "", "varianceId-impl", "(J)I", "isStarProjection", "", "isStarProjection-impl", "(J)Z", "variance", "Lorg/jetbrains/kotlin/types/Variance;", "getVariance-impl", "(J)Lorg/jetbrains/kotlin/types/Variance;", "typeIndex", "getTypeIndex-impl", "equals", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "Companion", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
public final class BinaryTypeProjection {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    public static final long STAR_CODE = 0;
    private final long code;

    private /* synthetic */ BinaryTypeProjection(long j) {
        this.code = j;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ BinaryTypeProjection m295boximpl(long j) {
        return new BinaryTypeProjection(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m296constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m297equalsimpl(long j, Object obj) {
        return (obj instanceof BinaryTypeProjection) && j == ((BinaryTypeProjection) obj).m305unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m298equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: getTypeIndex-impl, reason: not valid java name */
    public static final int m299getTypeIndeximpl(long j) {
        return (int) (j >>> 2);
    }

    /* JADX INFO: renamed from: getVariance-impl, reason: not valid java name */
    public static final Variance m300getVarianceimpl(long j) {
        m302isStarProjectionimpl(j);
        return (Variance) Variance.getEntries().get(m304varianceIdimpl(j));
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m301hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    /* JADX INFO: renamed from: isStarProjection-impl, reason: not valid java name */
    public static final boolean m302isStarProjectionimpl(long j) {
        return j == 0;
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m303toStringimpl(long j) {
        return "BinaryTypeProjection(code=" + j + Util.C_PARAM_END;
    }

    /* JADX INFO: renamed from: varianceId-impl, reason: not valid java name */
    private static final int m304varianceIdimpl(long j) {
        return ((int) (j & 3)) - 1;
    }

    public boolean equals(Object obj) {
        return m297equalsimpl(this.code, obj);
    }

    public final long getCode() {
        return this.code;
    }

    public int hashCode() {
        return m301hashCodeimpl(this.code);
    }

    public String toString() {
        return m303toStringimpl(this.code);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m305unboximpl() {
        return this.code;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005¢\u0006\u0004\b\r\u0010\u000eR\u000e\u0010\u000f\u001a\u00020\u0005X\u0086T¢\u0006\u0002\n\u0000¨\u0006\u0010"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/BinaryTypeProjection$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "encodeType", "", "variance", "Lorg/jetbrains/kotlin/types/Variance;", "typeIndex", "", "decode", "Lorg/jetbrains/kotlin/backend/common/serialization/encodings/BinaryTypeProjection;", "code", "decode-2ztXSlc", "(J)J", "STAR_CODE", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: decode-2ztXSlc, reason: not valid java name */
        public final long m306decode2ztXSlc(long code) {
            return BinaryTypeProjection.m296constructorimpl(code);
        }

        public final long encodeType(Variance variance, int typeIndex) {
            variance.getClass();
            return (((long) typeIndex) << 2) | ((long) (variance.ordinal() + 1));
        }

        private Companion() {
        }
    }
}
