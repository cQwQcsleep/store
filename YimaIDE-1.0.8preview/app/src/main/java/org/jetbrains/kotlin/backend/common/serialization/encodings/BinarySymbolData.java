package org.jetbrains.kotlin.backend.common.serialization.encodings;

import kotlin.Metadata;
import kotlin.enums.EnumEntries;
import kotlin.enums.EnumEntriesKt;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.DefaultConstructorMarker;
import org.eclipse.jdt.internal.compiler.util.Util;
import org.jetbrains.kotlin.codegen.optimization.CapturedVarsOptimizationMethodTransformerKt;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0087@\u0018\u0000 \u001e2\u00020\u0001:\u0002\u001d\u001eB\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u000f\u0010\b\u001a\u00020\tH\u0002¢\u0006\u0004\b\n\u0010\u000bJ\u001b\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0001HÖ\u0083\u0004¢\u0006\u0004\b\u0015\u0010\u0016J\u0011\u0010\u0017\u001a\u00020\tHÖ\u0081\u0004¢\u0006\u0004\b\u0018\u0010\u000bJ\u0011\u0010\u0019\u001a\u00020\u001aHÖ\u0081\u0004¢\u0006\u0004\b\u001b\u0010\u001cR\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0011\u0010\f\u001a\u00020\t8F¢\u0006\u0006\u001a\u0004\b\r\u0010\u000bR\u0011\u0010\u000e\u001a\u00020\u000f8F¢\u0006\u0006\u001a\u0004\b\u0010\u0010\u0011\u0088\u0001\u0002¨\u0006\u001f"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/BinarySymbolData;", "", "code", "", "constructor-impl", "(J)J", "getCode", "()J", "symbolKindId", "", "symbolKindId-impl", "(J)I", "signatureId", "getSignatureId-impl", "kind", "Lorg/jetbrains/kotlin/backend/common/serialization/encodings/BinarySymbolData$SymbolKind;", "getKind-impl", "(J)Lorg/jetbrains/kotlin/backend/common/serialization/encodings/BinarySymbolData$SymbolKind;", "equals", "", "other", "equals-impl", "(JLjava/lang/Object;)Z", "hashCode", "hashCode-impl", "toString", "", "toString-impl", "(J)Ljava/lang/String;", "SymbolKind", "Companion", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
@JvmInline
public final class BinarySymbolData {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private final long code;

    @Metadata(d1 = {"\u0000\f\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0002\b\u0013\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003j\u0002\b\u0004j\u0002\b\u0005j\u0002\b\u0006j\u0002\b\u0007j\u0002\b\bj\u0002\b\tj\u0002\b\nj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012j\u0002\b\u0013¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/BinarySymbolData$SymbolKind;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "(Ljava/lang/String;I)V", "FUNCTION_SYMBOL", "CONSTRUCTOR_SYMBOL", "ENUM_ENTRY_SYMBOL", "FIELD_SYMBOL", "VALUE_PARAMETER_SYMBOL", "RETURNABLE_BLOCK_SYMBOL", "CLASS_SYMBOL", "TYPE_PARAMETER_SYMBOL", "VARIABLE_SYMBOL", "ANONYMOUS_INIT_SYMBOL", "STANDALONE_FIELD_SYMBOL", "RECEIVER_PARAMETER_SYMBOL", "PROPERTY_SYMBOL", "LOCAL_DELEGATED_PROPERTY_SYMBOL", "TYPEALIAS_SYMBOL", "FILE_SYMBOL", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public enum SymbolKind {
        FUNCTION_SYMBOL,
        CONSTRUCTOR_SYMBOL,
        ENUM_ENTRY_SYMBOL,
        FIELD_SYMBOL,
        VALUE_PARAMETER_SYMBOL,
        RETURNABLE_BLOCK_SYMBOL,
        CLASS_SYMBOL,
        TYPE_PARAMETER_SYMBOL,
        VARIABLE_SYMBOL,
        ANONYMOUS_INIT_SYMBOL,
        STANDALONE_FIELD_SYMBOL,
        RECEIVER_PARAMETER_SYMBOL,
        PROPERTY_SYMBOL,
        LOCAL_DELEGATED_PROPERTY_SYMBOL,
        TYPEALIAS_SYMBOL,
        FILE_SYMBOL;

        private static final /* synthetic */ EnumEntries $ENTRIES = EnumEntriesKt.enumEntries(values());

        public static EnumEntries<SymbolKind> getEntries() {
            return $ENTRIES;
        }
    }

    private /* synthetic */ BinarySymbolData(long j) {
        this.code = j;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ BinarySymbolData m284boximpl(long j) {
        return new BinarySymbolData(j);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static long m285constructorimpl(long j) {
        return j;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m286equalsimpl(long j, Object obj) {
        return (obj instanceof BinarySymbolData) && j == ((BinarySymbolData) obj).m293unboximpl();
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m287equalsimpl0(long j, long j2) {
        return j == j2;
    }

    /* JADX INFO: renamed from: getKind-impl, reason: not valid java name */
    public static final SymbolKind m288getKindimpl(long j) {
        return (SymbolKind) SymbolKind.getEntries().get(m291symbolKindIdimpl(j));
    }

    /* JADX INFO: renamed from: getSignatureId-impl, reason: not valid java name */
    public static final int m289getSignatureIdimpl(long j) {
        return (int) (j >>> 8);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m290hashCodeimpl(long j) {
        return Long.hashCode(j);
    }

    /* JADX INFO: renamed from: symbolKindId-impl, reason: not valid java name */
    private static final int m291symbolKindIdimpl(long j) {
        return (int) (j & 255);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m292toStringimpl(long j) {
        return "BinarySymbolData(code=" + j + Util.C_PARAM_END;
    }

    public boolean equals(Object obj) {
        return m286equalsimpl(this.code, obj);
    }

    public final long getCode() {
        return this.code;
    }

    public int hashCode() {
        return m290hashCodeimpl(this.code);
    }

    public String toString() {
        return m292toStringimpl(this.code);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ long m293unboximpl() {
        return this.code;
    }

    @Metadata(d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u0016\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\tJ\u0015\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0005¢\u0006\u0004\b\r\u0010\u000e¨\u0006\u000f"}, d2 = {"Lorg/jetbrains/kotlin/backend/common/serialization/encodings/BinarySymbolData$Companion;", "", CapturedVarsOptimizationMethodTransformerKt.INIT_METHOD_NAME, "()V", "encode", "", "kind", "Lorg/jetbrains/kotlin/backend/common/serialization/encodings/BinarySymbolData$SymbolKind;", "signatureId", "", "decode", "Lorg/jetbrains/kotlin/backend/common/serialization/encodings/BinarySymbolData;", "code", "decode-9x8F8T0", "(J)J", "org.jetbrains.kotlin:ir.serialization.common"}, k = 1, mv = {2, 2, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX INFO: renamed from: decode-9x8F8T0, reason: not valid java name */
        public final long m294decode9x8F8T0(long code) {
            return BinarySymbolData.m285constructorimpl(code);
        }

        public final long encode(SymbolKind kind, int signatureId) {
            kind.getClass();
            return (((long) signatureId) << 8) | ((long) kind.ordinal());
        }

        private Companion() {
        }
    }
}
