package com.android.tools.r8.internal;

import com.android.tools.r8.DataResource;
import java.io.IOException;
import java.math.RoundingMode;

/* JADX INFO: renamed from: com.android.tools.r8.internal.y5, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class AbstractC3083y5 {
    public static final C2912w5 a;

    static {
        new C2912w5(new C2741u5("base64()", new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '+', DataResource.SEPARATOR}), '=');
        a = new C2912w5(new C2741u5("base64Url()", new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', 'a', 'b', 'c', 'd', 'e', 'f', 'g', 'h', 'i', 'j', 'k', 'l', 'm', 'n', 'o', 'p', 'q', 'r', 's', 't', 'u', 'v', 'w', 'x', 'y', 'z', '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', '-', '_'}), '=');
        new C2998x5(new C2741u5("base32()", new char[]{'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '2', '3', '4', '5', '6', '7'}), '=');
        new C2998x5(new C2741u5("base32Hex()", new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V'}), '=');
        new C2826v5(new C2741u5("base16()", new char[]{'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'}));
    }

    public final String a(byte[] bArr) {
        int length = bArr.length;
        DX.a(0, length, bArr.length);
        C2741u5 c2741u5 = ((C2998x5) this).b;
        StringBuilder sb = new StringBuilder(AbstractC2410qA.a(length, c2741u5.f, RoundingMode.CEILING) * c2741u5.e);
        try {
            a(sb, bArr, length);
            return sb.toString();
        } catch (IOException e) {
            x01.a(e);
            return null;
        }
    }

    public abstract void a(StringBuilder sb, byte[] bArr, int i);
}
