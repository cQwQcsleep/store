package org.jetbrains.kotlin.wasm.ir.debug;

import kotlin.Metadata;
import kotlin.jvm.JvmInline;
import kotlin.jvm.internal.Intrinsics;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\u0016\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\bv\u0018\u00002\u00020\u0001:\u0002\u0002\u0003\u0082\u0001\u0002\u0004\u0005ø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u0006À\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/debug/DebugData;", "", "StringData", "RawBytes", "Lorg/jetbrains/kotlin/wasm/ir/debug/DebugData$RawBytes;", "Lorg/jetbrains/kotlin/wasm/ir/debug/DebugData$StringData;", "org.jetbrains.kotlin:wasm.ir"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public interface DebugData {

    @Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0018\u0002\b\u0087@\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0004\b\u0004\u0010\u0005J\u001b\u0010\b\u001a\u00020\t2\b\u0010\n\u001a\u0004\u0018\u00010\u000bHÖ\u0083\u0004¢\u0006\u0004\b\f\u0010\rJ\u0011\u0010\u000e\u001a\u00020\u000fHÖ\u0081\u0004¢\u0006\u0004\b\u0010\u0010\u0011J\u0011\u0010\u0012\u001a\u00020\u0003HÖ\u0081\u0004¢\u0006\u0004\b\u0013\u0010\u0005R\u0011\u0010\u0002\u001a\u00020\u0003¢\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007\u0088\u0001\u0002Ê\u0001\u0002\b\u0015¨\u0006\u0014"}, d2 = {"Lorg/jetbrains/kotlin/wasm/ir/debug/DebugData$StringData;", "Lorg/jetbrains/kotlin/wasm/ir/debug/DebugData;", "value", "", "constructor-impl", "(Ljava/lang/String;)Ljava/lang/String;", "getValue", "()Ljava/lang/String;", "equals", "", "other", "", "equals-impl", "(Ljava/lang/String;Ljava/lang/Object;)Z", "hashCode", "", "hashCode-impl", "(Ljava/lang/String;)I", "toString", "toString-impl", "org.jetbrains.kotlin:wasm.ir", "Lkotlin/jvm/JvmInline;"}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    @JvmInline
    public static final class StringData implements DebugData {
        private final String value;

        private /* synthetic */ StringData(String str) {
            this.value = str;
        }

        /* JADX INFO: renamed from: box-impl, reason: not valid java name */
        public static final /* synthetic */ StringData m1354boximpl(String str) {
            return new StringData(str);
        }

        /* JADX INFO: renamed from: constructor-impl, reason: not valid java name */
        public static String m1355constructorimpl(String str) {
            str.getClass();
            return str;
        }

        /* JADX INFO: renamed from: equals-impl, reason: not valid java name */
        public static boolean m1356equalsimpl(String str, Object obj) {
            return (obj instanceof StringData) && Intrinsics.areEqual(str, ((StringData) obj).m1360unboximpl());
        }

        /* JADX INFO: renamed from: equals-impl0, reason: not valid java name */
        public static final boolean m1357equalsimpl0(String str, String str2) {
            return Intrinsics.areEqual(str, str2);
        }

        /* JADX INFO: renamed from: hashCode-impl, reason: not valid java name */
        public static int m1358hashCodeimpl(String str) {
            return str.hashCode();
        }

        /* JADX INFO: renamed from: toString-impl, reason: not valid java name */
        public static String m1359toStringimpl(String str) {
            return "StringData(value=" + str + ')';
        }

        public boolean equals(Object obj) {
            return m1356equalsimpl(this.value, obj);
        }

        public final String getValue() {
            return this.value;
        }

        public int hashCode() {
            return m1358hashCodeimpl(this.value);
        }

        public String toString() {
            return m1359toStringimpl(this.value);
        }

        /* JADX INFO: renamed from: unbox-impl, reason: not valid java name */
        public final /* synthetic */ String m1360unboximpl() {
            return this.value;
        }
    }
}
