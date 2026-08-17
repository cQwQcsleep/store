package org.jetbrains.kotlin.wasm.ir.debug;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import org.eclipse.jdt.internal.compiler.util.Util;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
@Metadata(d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004¢\u0006\u0004\b\f\u0010\rJ\u0011\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0012\u001a\u00020\u0013HÖ\u0081\u0004¢\u0006\u0004\b\u0014\u0010\u0015R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002Ê\u0001\u0002\b\u0017¨\u0006\u0016"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/debug/DebugData$RawBytes;", "Lorg/jetbrains/kotlin/wasm/ir/debug/DebugData;", "value", "", "constructor-impl", "([B)[B", "getValue", "()[B", "equals", "", "other", "", "equals-impl", "([BLjava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "([B)I", "toString", "", "toString-impl", "([B)Ljava/lang/String;", "org.jetbrains.kotlin:wasm.ir", "Lkotlin/jvm/JvmInline;"}, k = 1, mv = {2, 4, 0}, xi = 48)
@JvmInline
public final class DebugData$RawBytes implements DebugData {
    private final byte[] value;

    private /* synthetic */ DebugData$RawBytes(byte[] bArr) {
        this.value = bArr;
    }

    /* JADX INFO: renamed from: box-impl, reason: not valid java name */
    public static final /* synthetic */ DebugData$RawBytes m2192boximpl(byte[] bArr) {
        return new DebugData$RawBytes(bArr);
    }

    /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
    public static byte[] m2193constructorimpl(byte[] bArr) {
        bArr.getClass();
        return bArr;
    }

    /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
    public static boolean m2194equalsimpl(byte[] bArr, Object obj) {
        return (obj instanceof DebugData$RawBytes) && Intrinsics.areEqual(bArr, ((DebugData$RawBytes) obj).m2198unboximpl());
    }

    /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
    public static final boolean m2195equalsimpl0(byte[] bArr, byte[] bArr2) {
        return Intrinsics.areEqual(bArr, bArr2);
    }

    /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
    public static int m2196hashCodeimpl(byte[] bArr) {
        return Arrays.hashCode(bArr);
    }

    /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
    public static String m2197toStringimpl(byte[] bArr) {
        return "RawBytes(value=" + Arrays.toString(bArr) + Util.C_PARAM_END;
    }

    public boolean equals(Object obj) {
        return m2194equalsimpl(this.value, obj);
    }

    public final byte[] getValue() {
        return this.value;
    }

    public int hashCode() {
        return m2196hashCodeimpl(this.value);
    }

    public String toString() {
        return m2197toStringimpl(this.value);
    }

    /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
    public final /* synthetic */ byte[] m2198unboximpl() {
        return this.value;
    }
}
