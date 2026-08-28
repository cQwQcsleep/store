package com.vivo.shell;

import java.io.ByteArrayOutputStream;
import moe.shizuku.api.C0006;
import obfuse.NPStringFog;

/* renamed from: com.vivo.shell.۟ۤۨۦۥ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0003 {

    /* renamed from: ۤۥۢۨ, reason: not valid java name and contains not printable characters */
    public static int f3 = 485;

    /* renamed from: ۟ۢۥۡ۠, reason: not valid java name and contains not printable characters */
    public static String m264(String str) {
        String strDecode = "";
        int i = 0;
        String str2 = "";
        while (i < 15) {
            strDecode = new StringBuffer().append(strDecode).append(Integer.toHexString(i)).toString();
            String string = new StringBuffer().append(str2).append(((int) (Math.random() * 10)) ^ i).toString();
            i++;
            str2 = string;
        }
        while (strDecode.length() > 0) {
        }
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(str.length() / 2);
        for (int i2 = 0; i2 < str.length(); i2 += 2) {
            byteArrayOutputStream.write((strDecode.indexOf(str.charAt(i2)) << 4) | strDecode.indexOf(str.charAt(i2 + 1)));
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        int length = byteArray.length;
        int length2 = str2.length();
        for (int i3 = 0; i3 < length; i3++) {
            byteArray[i3] = (byte) (byteArray[i3] ^ str2.charAt(i3 % length2));
        }
        return new String(byteArray);
    }

    /* renamed from: ۣۣ۟ۤ, reason: not valid java name and contains not printable characters */
    public static int m265() {
        return (-485) ^ C0006.f4;
    }

    /* renamed from: ۟ۤۢ۠ۦ, reason: not valid java name and contains not printable characters */
    public static int m266(Object obj) {
        return obj.hashCode();
    }

    /* renamed from: ۣۢۤۧ, reason: not valid java name and contains not printable characters */
    public static String m267(short[] sArr, int i, int i2, int i3) {
        char[] cArr = new char[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            cArr[i4] = (char) (sArr[i + i4] ^ i3);
        }
        return new String(cArr);
    }
}
