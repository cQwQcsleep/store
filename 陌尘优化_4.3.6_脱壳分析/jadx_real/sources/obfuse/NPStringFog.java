package obfuse;

import java.io.ByteArrayOutputStream;

/* loaded from: /workspace/unpacked/classes.dex */
public class NPStringFog {
    public static String KEY = "npmanager";
    private static final String hexString = "0123456789ABCDEF";

    public static String decode(String str) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream(str.length() / 2);
        for (int i = 0; i < str.length(); i += 2) {
            baos.write((hexString.indexOf(str.charAt(i)) << 4) | hexString.indexOf(str.charAt(i + 1)));
        }
        byte[] b = baos.toByteArray();
        int len = b.length;
        int keyLen = KEY.length();
        for (int i2 = 0; i2 < len; i2++) {
            b[i2] = (byte) (b[i2] ^ KEY.charAt(i2 % keyLen));
        }
        return new String(b);
    }
}
