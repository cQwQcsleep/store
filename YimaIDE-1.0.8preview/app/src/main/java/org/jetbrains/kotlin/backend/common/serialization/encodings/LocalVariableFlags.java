package org.jetbrains.kotlin.backend.common.serialization.encodings;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.backend.common.serialization.IrFlags;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;
import org.jetbrains.kotlin.ir.declarations.IrLocalDelegatedProperty;
import org.jetbrains.kotlin.ir.declarations.IrVariable;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u000b\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0004\b\u0087@\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\u0010\u001a\u00020\t2\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b\u0012\u0010\u0013J\u0011\u0010\u0014\u001a\u00020\u0015HÖ\u0081\u0004¢\u0006\u0004\b\u0016\u0010\u0017J\u0011\u0010\u0018\u001a\u00020\u0019HÖ\u0081\u0004¢\u0006\u0004\b\u001a\u0010\u001bR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\b\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bR\u0011\u0010\f\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\u000f\u0010\u000b\u0088\u0001\u0002¨\u0006\u001d"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/LocalVariableFlags;", "", "flags", "", "constructor-impl", "(J)J", "getFlags", "()J", "isVar", "", "isVar-impl", "(J)Z", "isConst", "isConst-impl", "isLateinit", "isLateinit-impl", "equals", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(J)I", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "Companion", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
public final class LocalVariableFlags {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final long flags;

    private /* synthetic */ LocalVariableFlags(long j) {
        this.flags = j;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ LocalVariableFlags m357boximpl(long j) {
        return new LocalVariableFlags(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m358constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m359equalsimpl(long j, Object obj) {
        return (obj instanceof LocalVariableFlags) && j == ((LocalVariableFlags) obj).m366unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m360equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m361hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    /* JADX INFO: renamed from: isConst-impl, reason: not valid java name */
    public static final boolean m362isConstimpl(long j) {
        Boolean bool = IrFlags.IS_LOCAL_CONST.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isLateinit-impl, reason: not valid java name */
    public static final boolean m363isLateinitimpl(long j) {
        Boolean bool = IrFlags.IS_LOCAL_LATEINIT.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: isVar-impl, reason: not valid java name */
    public static final boolean m364isVarimpl(long j) {
        Boolean bool = IrFlags.IS_LOCAL_VAR.get((int) j);
        bool.getClass();
        return bool.booleanValue();
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m365toStringimpl(long j) {
        return "LocalVariableFlags(flags=" + j + Util.C_PARAM_END;
    }

    public boolean equals(Object obj) {
        return m359equalsimpl(this.flags, obj);
    }

    public final long getFlags() {
        return this.flags;
    }

    public int hashCode() {
        return m361hashCodeimpl(this.flags);
    }

    public String toString() {
        return m365toStringimpl(this.flags);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m366unboximpl() {
        return this.flags;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007J\u000e\u0010\u0004\u001a\u00020\u00052\u0006\u0010\b\u001a\u00020\tJ\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/LocalVariableFlags$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "encode", "", "variable", "Lorg/jetbrains/kotlin/ir/declarations/IrVariable;", "delegate", "Lorg/jetbrains/kotlin/ir/declarations/IrLocalDelegatedProperty;", "decode", "Lorg/jetbrains/kotlin/backend/common/serialization/encodings/LocalVariableFlags;", "code", "decode-QnIyQoc", "(J)J", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: decode-QnIyQoc, reason: not valid java name */
        public final long m367decodeQnIyQoc(long code) {
            return LocalVariableFlags.m358constructorimpl(code);
        }

        public final long encode(IrVariable variable) {
            variable.getClass();
            return IrFlags.getLocalFlags(!variable.getAnnotations().isEmpty(), variable.isVar(), variable.isConst(), variable.isLateinit());
        }

        private Companion() {
        }

        public final long encode(IrLocalDelegatedProperty delegate) {
            delegate.getClass();
            return IrFlags.getLocalFlags(!delegate.getAnnotations().isEmpty(), delegate.isVar(), false, false);
        }
    }
}
