package com.swift.sandhook.lib;

import android.os.Build;
import android.os.ParcelFileDescriptor;
import com.swift.sandhook.C0002;
import com.swift.sandhook.SandHook;
import com.swift.sandhook.wrapper.HookErrorException;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.net.URLDecoder;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.KeySpec;
import java.util.List;
import np.protect.assets.Shell2Application;
import np.protect.assets.p.C0010;
import np.protect.assets.p.C0016;
import np.protect.assets.p.C0019;
import np.protect.assets.p.C0024;
import np.protect.assets.p.C0030;
import np.protect.assets.p.C0031;
import np.protect.assets.p.C0035;
import np.protect.assets.p.C0037;
import obfuse.NPStringFog;
import top.canyie.pine.entry.C0052;
import top.canyie.pine.entry.C0053;

/* renamed from: com.swift.sandhook.lib.۟۟ۧۧۤ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0001 {

    /* renamed from: ۥۦۦۣ, reason: contains not printable characters */
    public static int f1 = 355;

    /* renamed from: ۟۟ۦۦ, reason: not valid java name and contains not printable characters */
    public static boolean m182(Object obj, Object obj2) {
        if (m192() <= 0) {
            return ((String) obj).equals(obj2);
        }
        return false;
    }

    /* renamed from: ۟۠۠, reason: not valid java name and contains not printable characters */
    public static Object m183(Object obj, Object obj2, Object obj3) {
        if (C0053.m574() <= 0) {
            return C0037.m441((Class<?>) obj, obj2, (String) obj3);
        }
        return null;
    }

    /* renamed from: ۟ۡۨۤ, reason: not valid java name and contains not printable characters */
    public static PrivateKey m184(Object obj, Object obj2) {
        if (m192() <= 0) {
            return ((KeyFactory) obj).generatePrivate((KeySpec) obj2);
        }
        return null;
    }

    /* renamed from: ۟ۢۥۤ۠, reason: not valid java name and contains not printable characters */
    public static boolean m185(Object obj) {
        if (C0053.m574() <= 0) {
            return ((File) obj).isDirectory();
        }
        return false;
    }

    /* renamed from: ۟ۢۦ۠ۨ, reason: not valid java name and contains not printable characters */
    public static void m186(Object obj) {
        if (C0053.m574() <= 0) {
            ((InvocationTargetException) obj).printStackTrace();
        }
    }

    /* renamed from: ۣ۟۠ۢۥ, reason: not valid java name and contains not printable characters */
    public static String m188() {
        if (C0053.m574() < 0) {
            return C0019.m405();
        }
        return null;
    }

    /* renamed from: ۣ۟ۡۧۡ, reason: not valid java name and contains not printable characters */
    public static Method m189(Object obj, Object obj2, Object obj3) {
        if (m192() <= 0) {
            return ((Class) obj).getDeclaredMethod((String) obj2, (Class[]) obj3);
        }
        return null;
    }

    /* renamed from: ۣۣ۟ۥۡ, reason: not valid java name and contains not printable characters */
    public static int m190(Object obj) {
        return obj.hashCode();
    }

    /* renamed from: ۣ۟ۥۢۡ, reason: not valid java name and contains not printable characters */
    public static String m191(Object obj, Object obj2) {
        if (m192() < 0) {
            return Shell2Application.m283((String) obj, (String) obj2);
        }
        return null;
    }

    /* renamed from: ۣ۟ۦۣۢ, reason: not valid java name and contains not printable characters */
    public static int m192() {
        return (-589) ^ f1;
    }

    /* renamed from: ۟ۥۣۡۤ, reason: not valid java name and contains not printable characters */
    public static void m193(Object obj) {
        if (C0052.m520() >= 0) {
            ((IOException) obj).printStackTrace();
        }
    }

    /* renamed from: ۟ۥۦۥۤ, reason: not valid java name and contains not printable characters */
    public static String[] m194() {
        if (m192() < 0) {
            return Build.SUPPORTED_ABIS;
        }
        return null;
    }

    /* renamed from: ۟ۥۧ۠ۥ, reason: not valid java name and contains not printable characters */
    public static void m195(Object obj, Object obj2) {
        if (m192() < 0) {
            C0035.m438((InputStream) obj, (OutputStream) obj2);
        }
    }

    /* renamed from: ۟ۦ۟۠ۧ, reason: not valid java name and contains not printable characters */
    public static String m196() {
        if (m192() < 0) {
            return C0024.m415();
        }
        return null;
    }

    /* renamed from: ۟ۦۣۢۢ, reason: not valid java name and contains not printable characters */
    public static boolean m197(Object obj, Object obj2) {
        if (C0053.m574() <= 0) {
            return ((List) obj).contains(obj2);
        }
        return false;
    }

    /* renamed from: ۟ۦۣۨۢ, reason: not valid java name and contains not printable characters */
    public static void m198(Object obj) {
        if (C0002.m259() <= 0) {
            ((ClassNotFoundException) obj).printStackTrace();
        }
    }

    /* renamed from: ۣ۟ۧۡۥ, reason: not valid java name and contains not printable characters */
    public static void m199(Object obj, boolean z) {
        if (C0052.m520() >= 0) {
            ((Field) obj).setAccessible(z);
        }
    }

    /* renamed from: ۣ۟ۧۦۧ, reason: not valid java name and contains not printable characters */
    public static boolean m200(Object obj) {
        if (C0053.m574() <= 0) {
            return ((File) obj).exists();
        }
        return false;
    }

    /* renamed from: ۟ۧۦۨ۟, reason: not valid java name and contains not printable characters */
    public static boolean m201(Object obj) {
        if (C0052.m520() > 0) {
            return ((File) obj).mkdirs();
        }
        return false;
    }

    /* renamed from: ۠ۢۦ۟, reason: not valid java name and contains not printable characters */
    public static String m202() {
        if (C0052.m520() >= 0) {
            return C0031.m429();
        }
        return null;
    }

    /* renamed from: ۣ۠ۥۥ, reason: not valid java name and contains not printable characters */
    public static boolean m203(Object obj) {
        if (m192() < 0) {
            return ((File) obj).canWrite();
        }
        return false;
    }

    /* renamed from: ۠ۦ۠ۦ, reason: not valid java name and contains not printable characters */
    public static String m204() {
        if (C0053.m574() <= 0) {
            return C0030.m427();
        }
        return null;
    }

    /* renamed from: ۡ۟ۨۤ, reason: not valid java name and contains not printable characters */
    public static int m205(Object obj) {
        if (m192() <= 0) {
            return ((InputStream) obj).available();
        }
        return 0;
    }

    /* renamed from: ۣۡۢ۟, reason: not valid java name and contains not printable characters */
    public static int m206() {
        if (m192() <= 0) {
            return Build.VERSION.SDK_INT;
        }
        return 0;
    }

    /* renamed from: ۣۣ۠ۥ, reason: not valid java name and contains not printable characters */
    public static Object m207(Object obj, Object obj2, Object obj3, Object obj4) {
        if (C0002.m259() <= 0) {
            return C0037.m445((String) obj, (String) obj2, (Class[]) obj3, (Object[]) obj4);
        }
        return null;
    }

    /* renamed from: ۣۦ۠ۡ, reason: not valid java name and contains not printable characters */
    public static long m208(Object obj) {
        if (C0053.m574() < 0) {
            return ((ParcelFileDescriptor) obj).getStatSize();
        }
        return 0L;
    }

    /* renamed from: ۣۦۤۡ, reason: not valid java name and contains not printable characters */
    public static String m209(Object obj, Object obj2) {
        if (C0002.m259() < 0) {
            return URLDecoder.decode((String) obj, (String) obj2);
        }
        return null;
    }

    /* renamed from: ۤۡۤۨ, reason: not valid java name and contains not printable characters */
    public static Object m210(Object obj, Object obj2, Object obj3) {
        if (C0002.m259() < 0) {
            return C0016.m401((Method) obj, obj2, (Object[]) obj3);
        }
        return null;
    }

    /* renamed from: ۤۥۦۡ, reason: not valid java name and contains not printable characters */
    public static StringBuilder m211(Object obj, Object obj2) {
        if (C0002.m259() < 0) {
            return ((StringBuilder) obj).append((String) obj2);
        }
        return null;
    }

    /* renamed from: ۤۥۦۦ, reason: not valid java name and contains not printable characters */
    public static void m212(Object obj) throws HookErrorException {
        if (C0053.m574() <= 0) {
            SandHook.addHookClass((Class[]) obj);
        }
    }

    /* renamed from: ۥۤۢ۠, reason: contains not printable characters */
    public static ParcelFileDescriptor m213(Object obj, int i) {
        if (C0002.m259() <= 0) {
            return ParcelFileDescriptor.open((File) obj, i);
        }
        return null;
    }

    /* renamed from: ۥۦ۟ۧ, reason: contains not printable characters */
    public static URL m214(Object obj, Object obj2) {
        if (m192() < 0) {
            return ((Class) obj).getResource((String) obj2);
        }
        return null;
    }

    /* renamed from: ۦ۟ۡۨ, reason: contains not printable characters */
    public static String m215() {
        if (C0002.m259() <= 0) {
            return C0010.f23;
        }
        return null;
    }

    /* renamed from: ۦۣۦۦ, reason: contains not printable characters */
    public static String m216() {
        if (C0052.m520() > 0) {
            return Shell2Application.f6;
        }
        return null;
    }

    /* renamed from: ۣۣۨۢ, reason: not valid java name and contains not printable characters */
    public static String m217(String str) {
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

    /* renamed from: ۟ۢۨۢۨ, reason: not valid java name and contains not printable characters */
    public static String m187(short[] sArr, int i, int i2, int i3) {
        char[] cArr = new char[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            cArr[i4] = (char) (sArr[i + i4] ^ i3);
        }
        return new String(cArr);
    }
}
