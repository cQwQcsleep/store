package com.shadow.okhttp3.internal.ws;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;
import com.shadow.kotlin.text.StringsKt;
import com.shadow.okhttp3.Headers;
import com.shadow.okhttp3.internal.Util;
import java.io.IOException;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class WebSocketExtensions {
    public static final Companion Companion = new Companion(null);
    private static final String HEADER_WEB_SOCKET_EXTENSION = "Sec-WebSocket-Extensions";
    public final Integer clientMaxWindowBits;
    public final boolean clientNoContextTakeover;
    public final boolean perMessageDeflate;
    public final Integer serverMaxWindowBits;
    public final boolean serverNoContextTakeover;
    public final boolean unknownValues;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        /* JADX WARN: Removed duplicated region for block: B:33:0x00b5 A[PHI: r7 r9
          0x00b5: PHI (r7v6 java.lang.Integer) = (r7v4 java.lang.Integer), (r7v4 java.lang.Integer), (r7v8 java.lang.Integer) binds: [B:52:0x00e6, B:49:0x00dd, B:32:0x00b3] A[DONT_GENERATE, DONT_INLINE]
          0x00b5: PHI (r9v9 java.lang.Integer) = (r9v4 java.lang.Integer), (r9v6 java.lang.Integer), (r9v4 java.lang.Integer) binds: [B:52:0x00e6, B:49:0x00dd, B:32:0x00b3] A[DONT_GENERATE, DONT_INLINE]] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
        */
        public final WebSocketExtensions parse(Headers headers) throws IOException {
            String strTrimSubstring;
            CloseableKt.checkNotNullParameter(headers, "responseHeaders");
            int size = headers.size();
            boolean z = false;
            Integer numV = null;
            boolean z2 = false;
            Integer numV2 = null;
            boolean z3 = false;
            boolean z4 = false;
            for (int i = 0; i < size; i++) {
                if (StringsKt.g(headers.name(i), WebSocketExtensions.HEADER_WEB_SOCKET_EXTENSION)) {
                    String strValue = headers.value(i);
                    int i2 = 0;
                    while (i2 < strValue.length()) {
                        int i3 = i2;
                        int iDelimiterOffset$default = Util.delimiterOffset$default(strValue, ',', i2, 0, 4, (Object) null);
                        char c = ';';
                        int iDelimiterOffset = Util.delimiterOffset(strValue, ';', i3, iDelimiterOffset$default);
                        String strTrimSubstring2 = Util.trimSubstring(strValue, i3, iDelimiterOffset);
                        int i4 = iDelimiterOffset + 1;
                        if (StringsKt.g(strTrimSubstring2, "permessage-deflate")) {
                            if (z) {
                                z4 = true;
                            }
                            while (i4 < iDelimiterOffset$default) {
                                int iDelimiterOffset2 = Util.delimiterOffset(strValue, c, i4, iDelimiterOffset$default);
                                int iDelimiterOffset3 = Util.delimiterOffset(strValue, '=', i4, iDelimiterOffset2);
                                String strTrimSubstring3 = Util.trimSubstring(strValue, i4, iDelimiterOffset3);
                                if (iDelimiterOffset3 < iDelimiterOffset2) {
                                    strTrimSubstring = Util.trimSubstring(strValue, iDelimiterOffset3 + 1, iDelimiterOffset2);
                                    CloseableKt.checkNotNullParameter(strTrimSubstring, "<this>");
                                    if (strTrimSubstring.length() >= 2 && StringsKt.startsWith$default$1(strTrimSubstring, "\"") && StringsKt.f(strTrimSubstring, "\"", false)) {
                                        strTrimSubstring = strTrimSubstring.substring(1, strTrimSubstring.length() - 1);
                                        CloseableKt.checkNotNullExpressionValue(strTrimSubstring, "substring(...)");
                                    }
                                } else {
                                    strTrimSubstring = null;
                                }
                                i4 = iDelimiterOffset2 + 1;
                                if (StringsKt.g(strTrimSubstring3, "client_max_window_bits")) {
                                    if (numV != null) {
                                        z4 = true;
                                    }
                                    numV = strTrimSubstring != null ? StringsKt.v(strTrimSubstring) : null;
                                    if (numV == null) {
                                        z4 = true;
                                    }
                                } else if (StringsKt.g(strTrimSubstring3, "client_no_context_takeover")) {
                                    if (z2) {
                                        z4 = true;
                                    }
                                    if (strTrimSubstring != null) {
                                        z4 = true;
                                    }
                                    z2 = true;
                                } else if (StringsKt.g(strTrimSubstring3, "server_max_window_bits")) {
                                    if (numV2 != null) {
                                        z4 = true;
                                    }
                                    numV2 = strTrimSubstring != null ? StringsKt.v(strTrimSubstring) : null;
                                    if (numV2 == null) {
                                    }
                                } else if (StringsKt.g(strTrimSubstring3, "server_no_context_takeover")) {
                                    if (z3) {
                                        z4 = true;
                                    }
                                    if (strTrimSubstring != null) {
                                        z4 = true;
                                    }
                                    z3 = true;
                                }
                                c = ';';
                            }
                            i2 = i4;
                            z = true;
                        } else {
                            i2 = i4;
                            z4 = true;
                        }
                    }
                }
            }
            return new WebSocketExtensions(z, numV, z2, numV2, z3, z4);
        }

        private Companion() {
        }
    }

    public WebSocketExtensions() {
        this(false, null, false, null, false, false, 63, null);
    }

    public static /* synthetic */ WebSocketExtensions copy$default(WebSocketExtensions webSocketExtensions, boolean z, Integer num, boolean z2, Integer num2, boolean z3, boolean z4, int i, Object obj) {
        if ((i & 1) != 0) {
            z = webSocketExtensions.perMessageDeflate;
        }
        if ((i & 2) != 0) {
            num = webSocketExtensions.clientMaxWindowBits;
        }
        Integer num3 = num;
        if ((i & 4) != 0) {
            z2 = webSocketExtensions.clientNoContextTakeover;
        }
        boolean z5 = z2;
        if ((i & 8) != 0) {
            num2 = webSocketExtensions.serverMaxWindowBits;
        }
        Integer num4 = num2;
        if ((i & 16) != 0) {
            z3 = webSocketExtensions.serverNoContextTakeover;
        }
        boolean z6 = z3;
        if ((i & 32) != 0) {
            z4 = webSocketExtensions.unknownValues;
        }
        return webSocketExtensions.copy(z, num3, z5, num4, z6, z4);
    }

    public final boolean component1() {
        return this.perMessageDeflate;
    }

    public final Integer component2() {
        return this.clientMaxWindowBits;
    }

    public final boolean component3() {
        return this.clientNoContextTakeover;
    }

    public final Integer component4() {
        return this.serverMaxWindowBits;
    }

    public final boolean component5() {
        return this.serverNoContextTakeover;
    }

    public final boolean component6() {
        return this.unknownValues;
    }

    public final WebSocketExtensions copy(boolean z, Integer num, boolean z2, Integer num2, boolean z3, boolean z4) {
        return new WebSocketExtensions(z, num, z2, num2, z3, z4);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof WebSocketExtensions)) {
            return false;
        }
        WebSocketExtensions webSocketExtensions = (WebSocketExtensions) obj;
        return this.perMessageDeflate == webSocketExtensions.perMessageDeflate && CloseableKt.areEqual(this.clientMaxWindowBits, webSocketExtensions.clientMaxWindowBits) && this.clientNoContextTakeover == webSocketExtensions.clientNoContextTakeover && CloseableKt.areEqual(this.serverMaxWindowBits, webSocketExtensions.serverMaxWindowBits) && this.serverNoContextTakeover == webSocketExtensions.serverNoContextTakeover && this.unknownValues == webSocketExtensions.unknownValues;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r0v1, types: [int] */
    /* JADX WARN: Type inference failed for: r0v12 */
    /* JADX WARN: Type inference failed for: r0v13 */
    /* JADX WARN: Type inference failed for: r2v3, types: [boolean] */
    /* JADX WARN: Type inference failed for: r2v6, types: [boolean] */
    public int hashCode() {
        boolean z = this.perMessageDeflate;
        ?? r0 = z;
        if (z) {
            r0 = 1;
        }
        int i = r0 * 31;
        Integer num = this.clientMaxWindowBits;
        int iHashCode = (i + (num == null ? 0 : num.hashCode())) * 31;
        ?? r2 = this.clientNoContextTakeover;
        int i2 = r2;
        if (r2 != 0) {
            i2 = 1;
        }
        int i3 = (iHashCode + i2) * 31;
        Integer num2 = this.serverMaxWindowBits;
        int iHashCode2 = (i3 + (num2 != null ? num2.hashCode() : 0)) * 31;
        ?? r22 = this.serverNoContextTakeover;
        int i4 = r22;
        if (r22 != 0) {
            i4 = 1;
        }
        int i5 = (iHashCode2 + i4) * 31;
        boolean z2 = this.unknownValues;
        return i5 + (z2 ? 1 : z2 ? 1 : 0);
    }

    public final boolean noContextTakeover(boolean z) {
        return z ? this.clientNoContextTakeover : this.serverNoContextTakeover;
    }

    public String toString() {
        return "WebSocketExtensions(perMessageDeflate=" + this.perMessageDeflate + ", clientMaxWindowBits=" + this.clientMaxWindowBits + ", clientNoContextTakeover=" + this.clientNoContextTakeover + ", serverMaxWindowBits=" + this.serverMaxWindowBits + ", serverNoContextTakeover=" + this.serverNoContextTakeover + ", unknownValues=" + this.unknownValues + ')';
    }

    public WebSocketExtensions(boolean z, Integer num, boolean z2, Integer num2, boolean z3, boolean z4) {
        this.perMessageDeflate = z;
        this.clientMaxWindowBits = num;
        this.clientNoContextTakeover = z2;
        this.serverMaxWindowBits = num2;
        this.serverNoContextTakeover = z3;
        this.unknownValues = z4;
    }

    public /* synthetic */ WebSocketExtensions(boolean z, Integer num, boolean z2, Integer num2, boolean z3, boolean z4, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? false : z, (i & 2) != 0 ? null : num, (i & 4) != 0 ? false : z2, (i & 8) == 0 ? num2 : null, (i & 16) != 0 ? false : z3, (i & 32) != 0 ? false : z4);
    }
}
