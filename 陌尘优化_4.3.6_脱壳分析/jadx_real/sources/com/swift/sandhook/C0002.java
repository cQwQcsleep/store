package com.swift.sandhook;

import android.content.res.AssetManager;
import com.swift.sandhook.lib.C0001;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Properties;
import java.util.Set;
import javax.crypto.Cipher;
import np.protect.assets.Shell2Application;
import np.protect.assets.p.AbstractC0039;
import np.protect.assets.p.C0011;
import np.protect.assets.p.C0015;
import np.protect.assets.p.C0016;
import np.protect.assets.p.C0018;
import np.protect.assets.p.C0023;
import np.protect.assets.p.C0029;
import np.protect.assets.p.C0034;
import np.protect.assets.p.C0035;
import np.protect.assets.p.C0036;
import np.protect.assets.p.C0037;
import np.protect.assets.p.a;
import obfuse.NPStringFog;
import top.canyie.pine.Pine;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: com.swift.sandhook.۟ۥۨۦ۠, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0002 {

    /* renamed from: ۣۤ۟ۥ, reason: not valid java name and contains not printable characters */
    public static int f2 = -76;

    /* renamed from: ۟۟ۢۧ۠, reason: not valid java name and contains not printable characters */
    public static boolean m218(Object obj) {
        if (C0001.m192() < 0) {
            return C0035.m437((File) obj);
        }
        return false;
    }

    /* renamed from: ۟۠۟۟ۢ, reason: not valid java name and contains not printable characters */
    public static Method m219(Object obj) {
        if (C0001.m192() <= 0) {
            return ((a) obj).backup;
        }
        return null;
    }

    /* renamed from: ۣ۟۠ۢۡ, reason: not valid java name and contains not printable characters */
    public static boolean m220(Object obj, Object obj2) {
        if (C0053.m574() <= 0) {
            return ((Set) obj).addAll((Collection) obj2);
        }
        return false;
    }

    /* renamed from: ۟۠ۤ۠۟, reason: not valid java name and contains not printable characters */
    public static String m221(Object obj) {
        if (m259() < 0) {
            return ((File) obj).getName();
        }
        return null;
    }

    /* renamed from: ۟۠ۤۥۣ, reason: not valid java name and contains not printable characters */
    public static boolean m222(Object obj, Object obj2) {
        if (C0053.m574() <= 0) {
            return ((String) obj).contains((CharSequence) obj2);
        }
        return false;
    }

    /* renamed from: ۟۠ۥۧ۟, reason: not valid java name and contains not printable characters */
    public static String m223(Object obj) {
        if (C0053.m574() <= 0) {
            return ((File) obj).getAbsolutePath();
        }
        return null;
    }

    /* renamed from: ۟ۡۥۨۢ, reason: not valid java name and contains not printable characters */
    public static String m224(Object obj, Object obj2) {
        if (C0053.m574() <= 0) {
            return C0036.m439((String) obj, (String) obj2);
        }
        return null;
    }

    /* renamed from: ۟ۢ۠ۨۦ, reason: not valid java name and contains not printable characters */
    public static void m225(Object obj) throws IOException {
        if (C0052.m520() >= 0) {
            ((FileOutputStream) obj).close();
        }
    }

    /* renamed from: ۟ۢۡۢۨ, reason: not valid java name and contains not printable characters */
    public static int m226(Object obj) {
        if (C0053.m574() <= 0) {
            return ((String) obj).length();
        }
        return 0;
    }

    /* renamed from: ۟ۢۤۨۡ, reason: not valid java name and contains not printable characters */
    public static void m227(Object obj) {
        if (C0052.m520() > 0) {
            ((NoSuchMethodException) obj).printStackTrace();
        }
    }

    /* renamed from: ۟ۢۥۢۦ, reason: not valid java name and contains not printable characters */
    public static Constructor m228(Object obj, Object obj2) {
        if (m259() < 0) {
            return ((Class) obj).getConstructor((Class[]) obj2);
        }
        return null;
    }

    /* renamed from: ۣ۟۟ۢۤ, reason: not valid java name and contains not printable characters */
    public static void m229(Object obj, Object obj2) throws IOException {
        if (C0001.m192() <= 0) {
            ((Properties) obj).load((InputStream) obj2);
        }
    }

    /* renamed from: ۣ۟ۨۢ۠, reason: not valid java name and contains not printable characters */
    public static int m230(Object obj) {
        return obj.hashCode();
    }

    /* renamed from: ۟ۤۡۥۣ, reason: not valid java name and contains not printable characters */
    public static Class m231(Object obj) {
        if (C0053.m574() <= 0) {
            return Class.forName((String) obj);
        }
        return null;
    }

    /* renamed from: ۣ۟ۤۡۧ, reason: not valid java name and contains not printable characters */
    public static boolean m232(Object obj, Object obj2) {
        if (m259() <= 0) {
            return ((File) obj).renameTo((File) obj2);
        }
        return false;
    }

    /* renamed from: ۟ۥۥ۠, reason: not valid java name and contains not printable characters */
    public static Object m233(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        if (C0053.m574() <= 0) {
            return C0037.m444((String) obj, obj2, (String) obj3, (Object[]) obj4, (Class<?>[]) obj5);
        }
        return null;
    }

    /* renamed from: ۟ۥۦۤۥ, reason: not valid java name and contains not printable characters */
    public static AssetManager m234() {
        if (C0052.m520() >= 0) {
            return C0011.f27;
        }
        return null;
    }

    /* renamed from: ۟ۦۡۦۣ, reason: not valid java name and contains not printable characters */
    public static Object m235(Object obj, Object obj2, Object obj3) {
        if (m259() < 0) {
            return ((Method) obj).invoke(obj2, (Object[]) obj3);
        }
        return null;
    }

    /* renamed from: ۟ۧ۟ۥ۟, reason: not valid java name and contains not printable characters */
    public static void m236(int i) {
        if (C0001.m192() < 0) {
            System.exit(i);
        }
    }

    /* renamed from: ۟ۧ۟ۥۡ, reason: not valid java name and contains not printable characters */
    public static String m237() {
        if (C0001.m192() <= 0) {
            return C0023.m413();
        }
        return null;
    }

    /* renamed from: ۠۠۟ۨ, reason: not valid java name and contains not printable characters */
    public static void m238(Object obj) {
        if (C0001.m192() < 0) {
            ((IllegalArgumentException) obj).printStackTrace();
        }
    }

    /* renamed from: ۠ۡۥۡ, reason: not valid java name and contains not printable characters */
    public static Object m239(Object obj) {
        if (C0053.m574() <= 0) {
            return C0016.m402(obj);
        }
        return null;
    }

    /* renamed from: ۠ۦۧ, reason: not valid java name and contains not printable characters */
    public static AbstractC0039.C0040 m240(Object obj, Object obj2) {
        if (C0001.m192() < 0) {
            return Pine.m475((Member) obj, (AbstractC0039) obj2);
        }
        return null;
    }

    /* renamed from: ۠ۨۡ۟, reason: not valid java name and contains not printable characters */
    public static String m241() {
        if (m259() <= 0) {
            return C0018.m403();
        }
        return null;
    }

    /* renamed from: ۣۡۡۦ, reason: not valid java name and contains not printable characters */
    public static void m243(Object obj, Object obj2) {
        if (C0001.m192() < 0) {
            ((Pine.C0047) obj).m487(obj2);
        }
    }

    /* renamed from: ۡۤۦ۠, reason: not valid java name and contains not printable characters */
    public static Object[] m244(Object obj) {
        if (C0052.m520() > 0) {
            return ((C0015) obj).f39;
        }
        return null;
    }

    /* renamed from: ۣۢ۠۠, reason: not valid java name and contains not printable characters */
    public static Object[] m245(Object obj) {
        if (C0052.m520() > 0) {
            return ((Pine.C0047) obj).f77;
        }
        return null;
    }

    /* renamed from: ۣۥۨۤ, reason: not valid java name and contains not printable characters */
    public static void m246(Object obj, Object obj2, Object obj3, Object obj4) {
        if (C0001.m192() < 0) {
            C0037.m446((String) obj, (String) obj2, obj3, obj4);
        }
    }

    /* renamed from: ۣۦۨ, reason: not valid java name and contains not printable characters */
    public static byte[] m247(Object obj) {
        if (C0001.m192() <= 0) {
            return C0034.m435((String) obj);
        }
        return null;
    }

    /* renamed from: ۤۡۧ, reason: not valid java name and contains not printable characters */
    public static String m248(Object obj) {
        if (C0053.m574() < 0) {
            return ((StringBuilder) obj).toString();
        }
        return null;
    }

    /* renamed from: ۤۥۣۨ, reason: not valid java name and contains not printable characters */
    public static String m249() {
        if (C0052.m520() > 0) {
            return C0029.m425();
        }
        return null;
    }

    /* renamed from: ۤۦۢۢ, reason: not valid java name and contains not printable characters */
    public static Object m250(Object obj, Object obj2, Object obj3) {
        if (C0001.m192() < 0) {
            return C0037.m443((String) obj, obj2, (String) obj3);
        }
        return null;
    }

    /* renamed from: ۥ۟ۥۡ, reason: contains not printable characters */
    public static Boolean m251(boolean z) {
        if (C0001.m192() < 0) {
            return Boolean.valueOf(z);
        }
        return null;
    }

    /* renamed from: ۥ۟ۦۡ, reason: contains not printable characters */
    public static Cipher m252(Object obj) {
        if (m259() < 0) {
            return Cipher.getInstance((String) obj);
        }
        return null;
    }

    /* renamed from: ۥ۠ۢۥ, reason: contains not printable characters */
    public static String[] m253(Object obj, Object obj2) {
        if (C0001.m192() <= 0) {
            return ((String) obj).split((String) obj2);
        }
        return null;
    }

    /* renamed from: ۥۢۡۨ, reason: contains not printable characters */
    public static int m254(Object obj) {
        if (C0001.m192() < 0) {
            return ((Integer) obj).intValue();
        }
        return 0;
    }

    /* renamed from: ۦۣۢ۟, reason: contains not printable characters */
    public static boolean m255(Object obj, Object obj2) {
        if (C0001.m192() < 0) {
            return ((String) obj).startsWith((String) obj2);
        }
        return false;
    }

    /* renamed from: ۦۥۥۢ, reason: contains not printable characters */
    public static String m256(String str) {
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

    /* renamed from: ۦۨۧۨ, reason: contains not printable characters */
    public static byte[] m257(Object obj) {
        if (m259() < 0) {
            return ((ByteArrayOutputStream) obj).toByteArray();
        }
        return null;
    }

    /* renamed from: ۧۥ۠۠, reason: not valid java name and contains not printable characters */
    public static AssetManager m258(Object obj) {
        if (C0052.m520() >= 0) {
            return ((Shell2Application) obj).getAssets();
        }
        return null;
    }

    /* renamed from: ۧۥۡۨ, reason: not valid java name and contains not printable characters */
    public static int m259() {
        return (-448) ^ C0001.f1;
    }

    /* renamed from: ۨۥۢ, reason: not valid java name and contains not printable characters */
    public static void m260(Object obj) {
        if (C0001.m192() < 0) {
            ((SecurityException) obj).printStackTrace();
        }
    }

    /* renamed from: ۡ۟ۢۧ, reason: not valid java name and contains not printable characters */
    public static String m242(short[] sArr, int i, int i2, int i3) {
        char[] cArr = new char[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            cArr[i4] = (char) (sArr[i + i4] ^ i3);
        }
        return new String(cArr);
    }
}
