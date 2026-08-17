package org.jetbrains.kotlin.wasm.ir.convertors;

import java.util.ArrayList;
import kotlin.Metadata;
import kotlin.UByte;
import kotlin.collections.ArraysKt;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.FunctionReferenceImpl;
import kotlin.text.StringsKt;
import kotlin.text.UStringsKt;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000,\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0005\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\f\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\t\n\u0000\u001a\f\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0002H\u0002\u001a\f\u0010\u0003\u001a\u00020\u0001*\u00020\u0004H\u0002\u001a\u000e\u0010\u0005\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u0001\u001a\u000e\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n\"\u000e\u0010\u000b\u001a\u00020\fX\u0082T¢\u0006\u0002\n\u0000\"\u000e\u0010\r\u001a\u00020\u000eX\u0082T¢\u0006\u0002\n\u0000¨\u0006\u000f"}, d2 = {"toWatHexString", "", "", "toWatString", "", "sanitizeWatIdentifier", "indent", "isValidWatIdentifierChar", "", "c", "", "F32_CANON_NAN", "", "F64_CANON_NAN", "", "org.jetbrains.kotlin:wasm.ir"}, k = 2, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class WasmIrToTextKt {
    public static final boolean isValidWatIdentifierChar(char c) {
        if ('0' <= c && c < ':') {
            return true;
        }
        if ('A' > c || c >= '[') {
            return ('a' <= c && c < '{') || StringsKt.contains$default("!#$%&′*+-./:<=>?@\\^_`|~", c, false, 2, (Object) null) || StringsKt.contains$default("$.@_", c, false, 2, (Object) null);
        }
        return true;
    }

    public static final String sanitizeWatIdentifier(String str) {
        str.getClass();
        if (str.length() == 0) {
            return "_";
        }
        for (int i = 0; i < str.length(); i++) {
            if (!isValidWatIdentifierChar(str.charAt(i))) {
                ArrayList arrayList = new ArrayList(str.length());
                for (int i2 = 0; i2 < str.length(); i2++) {
                    char cCharAt = str.charAt(i2);
                    arrayList.add(isValidWatIdentifierChar(cCharAt) ? Character.valueOf(cCharAt) : "_");
                }
                return CollectionsKt.joinToString$default(arrayList, "", (CharSequence) null, (CharSequence) null, 0, (CharSequence) null, (Function1) null, 62, (Object) null);
            }
        }
        return str;
    }

    private static final String toWatHexString(byte b) {
        return "\\" + StringsKt.padStart(UStringsKt.toString-LxnNnR4(UByte.constructor-impl(b), 16), 2, '0');
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String toWatString(byte b) {
        if (b == 9) {
            return "\\t";
        }
        if (b == 10) {
            return "\\n";
        }
        if (b == 13) {
            return "\\r";
        }
        if (b == 34) {
            return "\\\"";
        }
        if (b == 39) {
            return "\\'";
        }
        if (b == 92) {
            return "\\\\";
        }
        return (32 > b || b >= 127) ? toWatHexString(b) : String.valueOf((char) b);
    }

    /* JADX INFO: renamed from: org.jetbrains.kotlin.wasm.ir.convertors.WasmIrToTextKt$toWatString$1, reason: invalid class name */
    @Metadata(k = 3, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
    public static final /* synthetic */ class AnonymousClass1 extends FunctionReferenceImpl implements Function1<Byte, String> {
        public static final AnonymousClass1 INSTANCE = new AnonymousClass1();

        public AnonymousClass1() {
            super(1, WasmIrToTextKt.class, "toWatString", "toWatString(B)Ljava/lang/String;", 1);
        }

        public /* bridge */ /* synthetic */ Object invoke(Object obj) {
            return invoke(((Number) obj).byteValue());
        }

        public final String invoke(byte b) {
            return WasmIrToTextKt.toWatString(b);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static final String toWatString(byte[] bArr) {
        return ArraysKt.joinToString$default(bArr, "", "\"", "\"", 0, (CharSequence) null, AnonymousClass1.INSTANCE, 24, (Object) null);
    }
}
