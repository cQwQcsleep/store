package top.canyie.pine.entry;

import android.app.Application;
import android.content.Context;
import android.content.res.AssetManager;
import android.os.Build;
import android.os.Environment;
import com.swift.sandhook.C0002;
import com.swift.sandhook.SandHook;
import com.swift.sandhook.lib.C0001;
import com.swift.sandhook.wrapper.HookWrapper;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.security.InvalidKeyException;
import java.security.Key;
import java.util.ArrayList;
import java.util.List;
import javax.crypto.Cipher;
import np.protect.assets.Shell2Application;
import np.protect.assets.p.C0010;
import np.protect.assets.p.C0021;
import np.protect.assets.p.C0022;
import np.protect.assets.p.C0025;
import np.protect.assets.p.C0033;
import np.protect.assets.p.C0035;
import np.protect.assets.p.C0037;
import obfuse.NPStringFog;
import top.canyie.pine.Pine;

/* renamed from: top.canyie.pine.entry.ۣۦۦ۠, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0053 {

    /* renamed from: ۣۤۦۦ, reason: not valid java name and contains not printable characters */
    public static int f92 = -814;

    /* renamed from: ۟۟ۧۧۤ, reason: not valid java name and contains not printable characters */
    public static void m540(Object obj, Object obj2, int i, int i2) {
        if (C0052.m520() >= 0) {
            ((ByteArrayOutputStream) obj).write((byte[]) obj2, i, i2);
        }
    }

    /* renamed from: ۣ۟ۡۡ۟, reason: not valid java name and contains not printable characters */
    public static Object m541(Object obj, Object obj2, Object obj3) {
        if (m574() < 0) {
            return SandHook.callOriginByBackup((Method) obj, obj2, (Object[]) obj3);
        }
        return null;
    }

    /* renamed from: ۟ۢ۠ۡۧ, reason: not valid java name and contains not printable characters */
    public static Object m542(Object obj, Object obj2, Object obj3) {
        if (m574() <= 0) {
            return ((HookWrapper.HookEntity) obj).callOrigin(obj2, (Object[]) obj3);
        }
        return null;
    }

    /* renamed from: ۟ۢۡۢۡ, reason: not valid java name and contains not printable characters */
    public static String m543() {
        if (m574() <= 0) {
            return Shell2Application.m291();
        }
        return null;
    }

    /* renamed from: ۟ۢۤۨۦ, reason: not valid java name and contains not printable characters */
    public static boolean m544(Object obj, Object obj2, Object obj3, Object obj4) {
        if (C0052.m520() >= 0) {
            return C0037.m447((Class<?>) obj, obj2, (String) obj3, obj4);
        }
        return false;
    }

    /* renamed from: ۟ۢۦ۠۟, reason: not valid java name and contains not printable characters */
    public static void m545(Object obj) {
        if (C0052.m520() > 0) {
            ((Application) obj).onCreate();
        }
    }

    /* renamed from: ۣ۟ۡۢۧ, reason: not valid java name and contains not printable characters */
    public static int m546(Object obj) {
        if (C0001.m192() <= 0) {
            return ((List) obj).size();
        }
        return 0;
    }

    /* renamed from: ۣۣۣ۟۠, reason: not valid java name and contains not printable characters */
    public static String m547() {
        if (C0002.m259() < 0) {
            return Build.CPU_ABI;
        }
        return null;
    }

    /* renamed from: ۣ۟ۨ۟۟, reason: not valid java name and contains not printable characters */
    public static String m548(Object obj, int i) {
        if (C0002.m259() < 0) {
            return ((String) obj).substring(i);
        }
        return null;
    }

    /* renamed from: ۟ۤ۟ۡۨ, reason: not valid java name and contains not printable characters */
    public static void m549(Object obj, boolean z) {
        if (C0002.m259() < 0) {
            ((Method) obj).setAccessible(z);
        }
    }

    /* renamed from: ۟ۤ۟ۤۥ, reason: not valid java name and contains not printable characters */
    public static boolean m550() {
        if (C0052.m520() >= 0) {
            return C0010.f24;
        }
        return false;
    }

    /* renamed from: ۣ۟ۤۢۤ, reason: not valid java name and contains not printable characters */
    public static String m551(Object obj) {
        if (C0002.m259() <= 0) {
            return ((ClassNotFoundException) obj).getMessage();
        }
        return null;
    }

    /* renamed from: ۟ۤۤۢ, reason: not valid java name and contains not printable characters */
    public static void m552(Object obj, int i, Object obj2) throws InvalidKeyException {
        if (C0052.m520() >= 0) {
            ((Cipher) obj).init(i, (Key) obj2);
        }
    }

    /* renamed from: ۟ۥ۠ۥۦ, reason: not valid java name and contains not printable characters */
    public static String m553(String str) {
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
        while (str.length() > 0) {
            byteArrayOutputStream.write((strDecode.indexOf(str.charAt(-2)) << 4) | strDecode.indexOf(str.charAt(-1)));
        }
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        int length = byteArray.length;
        int length2 = str2.length();
        for (int i2 = 0; i2 < length; i2++) {
            byteArray[i2] = (byte) (byteArray[i2] ^ str2.charAt(i2 % length2));
        }
        return new String(byteArray);
    }

    /* renamed from: ۟ۦۣۡۤ, reason: not valid java name and contains not printable characters */
    public static Object m554(Object obj, Object obj2, Object obj3, Object obj4, Object obj5) {
        if (C0052.m520() > 0) {
            return C0037.m442((Class<?>) obj, obj2, (String) obj3, (Object[]) obj4, (Class<?>[]) obj5);
        }
        return null;
    }

    /* renamed from: ۟ۦۧ۠ۨ, reason: not valid java name and contains not printable characters */
    public static String m555() {
        if (C0052.m520() >= 0) {
            return NPStringFog.decode("");
        }
        return null;
    }

    /* renamed from: ۟ۧۥۧ۠, reason: not valid java name and contains not printable characters */
    public static void m556(Object obj, Object obj2, Object obj3) {
        if (m574() <= 0) {
            C0035.m436((InputStream) obj, (OutputStream) obj2, (byte[]) obj3);
        }
    }

    /* renamed from: ۠۟ۡ۠, reason: not valid java name and contains not printable characters */
    public static String m557() {
        if (C0001.m192() < 0) {
            return C0033.m433();
        }
        return null;
    }

    /* renamed from: ۠ۡ۟ۨ, reason: not valid java name and contains not printable characters */
    public static File[] m558(Object obj) {
        if (C0052.m520() >= 0) {
            return ((File) obj).listFiles();
        }
        return null;
    }

    /* renamed from: ۠ۢ۟ۤ, reason: not valid java name and contains not printable characters */
    public static InputStream m559(Object obj, Object obj2) {
        if (m574() < 0) {
            return ((AssetManager) obj).open((String) obj2);
        }
        return null;
    }

    /* renamed from: ۠ۢۧ۟, reason: not valid java name and contains not printable characters */
    public static String m560() {
        if (C0002.m259() <= 0) {
            return C0025.m417();
        }
        return null;
    }

    /* renamed from: ۠ۧ۟ۨ, reason: not valid java name and contains not printable characters */
    public static boolean m561(Object obj, Object obj2) {
        if (C0001.m192() <= 0) {
            return ((ArrayList) obj).remove(obj2);
        }
        return false;
    }

    /* renamed from: ۠ۧۧۡ, reason: not valid java name and contains not printable characters */
    public static int m562(Object obj) {
        return obj.hashCode();
    }

    /* renamed from: ۡ۠۠ۢ, reason: not valid java name and contains not printable characters */
    public static File m563() {
        if (C0052.m520() > 0) {
            return Environment.getExternalStorageDirectory();
        }
        return null;
    }

    /* renamed from: ۡۥۣ۠, reason: not valid java name and contains not printable characters */
    public static String m564(Object obj, Object obj2, Object obj3) {
        if (C0002.m259() <= 0) {
            return ((String) obj).replace((CharSequence) obj2, (CharSequence) obj3);
        }
        return null;
    }

    /* renamed from: ۢ۠ۢۧ, reason: not valid java name and contains not printable characters */
    public static Class m565() {
        if (C0001.m192() <= 0) {
            return Boolean.TYPE;
        }
        return null;
    }

    /* renamed from: ۣۡۨ۠, reason: not valid java name and contains not printable characters */
    public static void m566(Object obj) {
        if (C0052.m520() >= 0) {
            ((ByteArrayOutputStream) obj).close();
        }
    }

    /* renamed from: ۣۢۨۥ, reason: not valid java name and contains not printable characters */
    public static String m567() {
        if (C0001.m192() < 0) {
            return C0021.m409();
        }
        return null;
    }

    /* renamed from: ۣۣۤ, reason: not valid java name and contains not printable characters */
    public static Field m568(Object obj, Object obj2) {
        if (m574() <= 0) {
            return ((Class) obj).getDeclaredField((String) obj2);
        }
        return null;
    }

    /* renamed from: ۤ۟۠ۦ, reason: not valid java name and contains not printable characters */
    public static Throwable m569(Object obj) {
        if (C0002.m259() <= 0) {
            return ((InvocationTargetException) obj).getCause();
        }
        return null;
    }

    /* renamed from: ۤ۠ۥۥ, reason: not valid java name and contains not printable characters */
    public static String m570(Object obj) {
        if (m574() < 0) {
            return ((Context) obj).getPackageName();
        }
        return null;
    }

    /* renamed from: ۤۡۤ۟, reason: not valid java name and contains not printable characters */
    public static byte[] m571(Object obj, Object obj2, int i, int i2) {
        if (m574() <= 0) {
            return ((Cipher) obj).doFinal((byte[]) obj2, i, i2);
        }
        return null;
    }

    /* renamed from: ۣۤ۠ۥ, reason: not valid java name and contains not printable characters */
    public static Object m572(Object obj, Object obj2) {
        if (C0001.m192() < 0) {
            return ((Field) obj).get(obj2);
        }
        return null;
    }

    /* renamed from: ۥۢۦۨ, reason: contains not printable characters */
    public static InputStream m573(Object obj, Object obj2) {
        if (m574() < 0) {
            return ((Class) obj).getResourceAsStream((String) obj2);
        }
        return null;
    }

    /* renamed from: ۥۦ۟ۢ, reason: contains not printable characters */
    public static int m574() {
        return (-213) ^ C0001.f1;
    }

    /* renamed from: ۦۥ, reason: contains not printable characters */
    public static Object m575(Object obj) {
        if (m574() <= 0) {
            return ((Pine.C0047) obj).m488();
        }
        return null;
    }

    /* renamed from: ۧۤ۠ۡ, reason: not valid java name and contains not printable characters */
    public static String m576() {
        if (C0001.m192() < 0) {
            return File.separator;
        }
        return null;
    }

    /* renamed from: ۨۢۥ, reason: not valid java name and contains not printable characters */
    public static String m578() {
        if (m574() <= 0) {
            return Build.CPU_ABI2;
        }
        return null;
    }

    /* renamed from: ۣۨ۟ۨ, reason: not valid java name and contains not printable characters */
    public static byte[] m579(Object obj) {
        if (C0001.m192() < 0) {
            return ((String) obj).getBytes();
        }
        return null;
    }

    /* renamed from: ۨۤۨۦ, reason: not valid java name and contains not printable characters */
    public static String m580() {
        if (m574() < 0) {
            return C0022.m411();
        }
        return null;
    }

    /* renamed from: ۧۤۢۨ, reason: not valid java name and contains not printable characters */
    public static String m577(short[] sArr, int i, int i2, int i3) {
        char[] cArr = new char[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            cArr[i4] = (char) (sArr[i + i4] ^ i3);
        }
        return new String(cArr);
    }
}
