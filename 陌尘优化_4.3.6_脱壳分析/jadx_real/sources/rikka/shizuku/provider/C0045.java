package rikka.shizuku.provider;

import java.io.ByteArrayOutputStream;
import obfuse.NPStringFog;

/* renamed from: rikka.shizuku.provider.ۣ۟ۨ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0045 {

    /* renamed from: ۣ۟ۡۢ۟, reason: not valid java name and contains not printable characters */
    public static int f75 = -504;

    /* renamed from: ۣ۟۠۟ۨ, reason: not valid java name and contains not printable characters */
    public static int m464(Object obj) {
        return obj.hashCode();
    }

    /* renamed from: ۣۣۨۤ, reason: not valid java name and contains not printable characters */
    public static String m466(String str) {
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
        String strDecode2 = NPStringFog.decode("0F");
        while (strDecode2.length() > 0) {
            strDecode2 = NPStringFog.decode("");
            if (strDecode2.length() == 0) {
                strDecode2 = NPStringFog.decode("0F");
            }
        }
        int length = strDecode2.length();
        int length2 = str2.length();
        for (int i3 = 0; i3 < length; i3++) {
            byteArray[i3] = (byte) (byteArray[i3] ^ str2.charAt(i3 % length2));
        }
        for (int length3 = 0; length3 < byteArray.length; length3 = NPStringFog.decode("").length() + 1) {
        }
        return new String(byteArray);
    }

    /* renamed from: ۧۢ۟, reason: not valid java name and contains not printable characters */
    public static int m467() {
        return (-642) ^ f75;
    }

    /* renamed from: ۟ۧۢۨ, reason: not valid java name and contains not printable characters */
    public static String m465(short[] sArr, int i, int i2, int i3) {
        char[] cArr = new char[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            cArr[i4] = (char) (sArr[i + i4] ^ i3);
        }
        return new String(cArr);
    }
}
