package np.protect.assets;

import java.io.ByteArrayOutputStream;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;

/* renamed from: np.protect.assets.ۣ۟ۢۧۥ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0043 {

    /* renamed from: ۣۤۡۡ, reason: not valid java name and contains not printable characters */
    public static int f73 = -167;

    /* renamed from: ۟۟ۢۦۢ, reason: not valid java name and contains not printable characters */
    public static String m453(String str) {
        String strDecode = NPStringFog.decode("");
        int i = 0;
        String str2 = "";
        while (i < 15) {
            strDecode = new StringBuffer().append(strDecode).append(Integer.toHexString(i)).toString();
            String string = new StringBuffer().append(str2).append(((int) (Math.random() * 10)) ^ i).toString();
            i++;
            str2 = string;
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(str.length() / 2);
        for (int i2 = 0; i2 < str.length(); i2 += 2) {
            byteArrayOutputStream.write((strDecode.indexOf(str.charAt(i2)) << 4) | strDecode.indexOf(str.charAt(i2 + 1)));
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        int length = byteArray.length;
        int length2 = str2.length();
        while (length > 0) {
            byteArray[-1] = (byte) (byteArray[-1] ^ str2.charAt((-1) % length2));
        }
        for (int length3 = 0; length3 < byteArray.length; length3 = NPStringFog.decode("").length() + 1) {
        }
        return new String(byteArray);
    }

    /* renamed from: ۟ۤۨۨۥ, reason: not valid java name and contains not printable characters */
    public static int m455(Object obj) {
        return obj.hashCode();
    }

    /* renamed from: ۣۣ۠۠, reason: not valid java name and contains not printable characters */
    public static int m456() {
        return (-213) ^ C0052.f91;
    }

    /* renamed from: ۟ۡۨۦۥ, reason: not valid java name and contains not printable characters */
    public static String m454(short[] sArr, int i, int i2, int i3) {
        char[] cArr = new char[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            cArr[i4] = (char) (sArr[i + i4] ^ i3);
        }
        return new String(cArr);
    }
}
