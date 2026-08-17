package org.jetbrains.kotlin.backend.common.serialization.encodings;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u00182\u00020\u0001:\u0001\u0018B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\r\u001a\u00020\u000e2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0012\u001a\u00020\bHÖ\u0081\u0004¢\u0006\u0004\b\u0013\u0010\nJ\u0011\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004¢\u0006\u0004\b\u0016\u0010\u0017R\u0010\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0004\n\u0002\u0010\u0006R\u0011\u0010\u0007\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\t\u0010\nR\u0011\u0010\u000b\u001a\u00020\b8F¢\u0006\u0006\u001a\u0004\b\f\u0010\n\u0088\u0001\u0002\u0092\u0001\u00020\u0003¨\u0006\u0019"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/BinaryNameAndType;", "", "decoded", "Lorg/jetbrains/kotlin/backend/common/serialization/encodings/BinaryLattice;", "constructor-impl", "(J)J", "J", "nameIndex", "", "getNameIndex-impl", "(J)I", "typeIndex", "getTypeIndex-impl", "equals", "", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "Companion", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
public final class BinaryNameAndType {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final long decoded;

    private /* synthetic */ BinaryNameAndType(long j) {
        this.decoded = j;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ BinaryNameAndType m274boximpl(long j) {
        return new BinaryNameAndType(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m275constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m276equalsimpl(long j, Object obj) {
        return (obj instanceof BinaryNameAndType) && BinaryLattice.m267equalsimpl0(j, ((BinaryNameAndType) obj).getDecoded());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m277equalsimpl0(long j, long j2) {
        return BinaryLattice.m267equalsimpl0(j, j2);
    }

    /* JADX INFO: renamed from: getNameIndex-impl, reason: not valid java name */
    public static final int m278getNameIndeximpl(long j) {
        return BinaryLattice.m268getFirstimpl(j);
    }

    /* JADX INFO: renamed from: getTypeIndex-impl, reason: not valid java name */
    public static final int m279getTypeIndeximpl(long j) {
        return BinaryLattice.m269getSecondimpl(j);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m280hashCodeimpl(long j) {
        return BinaryLattice.m270hashCodeimpl(j);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m281toStringimpl(long j) {
        return "BinaryNameAndType(decoded=" + ((Object) BinaryLattice.m271toStringimpl(j)) + Util.C_PARAM_END;
    }

    public boolean equals(Object obj) {
        return m276equalsimpl(this.decoded, obj);
    }

    public int hashCode() {
        return m280hashCodeimpl(this.decoded);
    }

    public String toString() {
        return m281toStringimpl(this.decoded);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name and from getter */
    public final /* synthetic */ long getDecoded() {
        return this.decoded;
    }

    @Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0007J\u0015\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u0005¢\u0006\u0004\b\f\u0010\r¨\u0006\u000e"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/BinaryNameAndType$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "encode", "", "nameIndex", "", "typeIndex", "decode", "Lorg/jetbrains/kotlin/backend/common/serialization/encodings/BinaryNameAndType;", "code", "decode-WXC2TjU", "(J)J", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: decode-WXC2TjU, reason: not valid java name */
        public final long m283decodeWXC2TjU(long code) {
            return BinaryNameAndType.m275constructorimpl(BinaryLattice.INSTANCE.m273decode9PDhHO0(code));
        }

        public final long encode(int nameIndex, int typeIndex) {
            return BinaryLattice.INSTANCE.encode(nameIndex, typeIndex);
        }

        private Companion() {
        }
    }
}
