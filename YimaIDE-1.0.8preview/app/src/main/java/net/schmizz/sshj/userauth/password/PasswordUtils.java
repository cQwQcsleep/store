package net.schmizz.sshj.userauth.password;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class PasswordUtils {
    public static void blankOut(char[] cArr) {
        if (cArr != null) {
            Arrays.fill(cArr, ' ');
        }
    }

    public static PasswordFinder createOneOff(final char[] cArr) {
        if (cArr == null) {
            return null;
        }
        return new PasswordFinder() { // from class: net.schmizz.sshj.userauth.password.PasswordUtils.1
            @Override // net.schmizz.sshj.userauth.password.PasswordFinder
            public char[] reqPassword(Resource<?> resource) {
                char[] cArr2 = (char[]) cArr.clone();
                PasswordUtils.blankOut(cArr);
                return cArr2;
            }

            @Override // net.schmizz.sshj.userauth.password.PasswordFinder
            public boolean shouldRetry(Resource<?> resource) {
                return false;
            }
        };
    }

    public static byte[] toByteArray(char[] cArr) {
        ByteBuffer byteBufferEncode = StandardCharsets.UTF_8.encode(CharBuffer.wrap(cArr));
        int iRemaining = byteBufferEncode.remaining();
        byte[] bArr = new byte[iRemaining];
        byteBufferEncode.get(bArr, 0, iRemaining);
        return bArr;
    }
}
