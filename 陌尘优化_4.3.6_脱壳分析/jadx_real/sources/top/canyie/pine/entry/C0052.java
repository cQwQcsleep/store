package top.canyie.pine.entry;

import com.swift.sandhook.C0002;
import com.swift.sandhook.lib.C0001;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.net.URL;
import java.security.KeyFactory;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import np.protect.assets.Shell2Application;
import np.protect.assets.p.C0007;
import np.protect.assets.p.C0011;
import np.protect.assets.p.C0013;
import np.protect.assets.p.C0020;
import np.protect.assets.p.C0026;
import np.protect.assets.p.C0027;
import np.protect.assets.p.C0028;
import np.protect.assets.p.C0032;
import np.protect.assets.p.C0037;
import np.protect.assets.p.a;
import obfuse.NPStringFog;
import top.canyie.pine.Pine;

/* renamed from: top.canyie.pine.entry.ۣ۠ۦۣ, reason: contains not printable characters */
/* loaded from: /workspace/unpacked/classes.dex */
public class C0052 {

    /* renamed from: ۦۥ۠ۢ, reason: contains not printable characters */
    public static int f91 = -162;

    /* renamed from: ۟۟۟ۥۣ, reason: not valid java name and contains not printable characters */
    public static byte[] m498(Object obj) {
        if (C0053.m574() <= 0) {
            return C0007.m340((byte[]) obj);
        }
        return null;
    }

    /* renamed from: ۣ۟۟ۦۧ, reason: not valid java name and contains not printable characters */
    public static Object m499(Object obj, Object obj2) {
        if (m520() > 0) {
            return ((Constructor) obj).newInstance((Object[]) obj2);
        }
        return null;
    }

    /* renamed from: ۣ۟۠ۦۡ, reason: not valid java name and contains not printable characters */
    public static void m500(Object obj) {
        if (C0002.m259() < 0) {
            System.load((String) obj);
        }
    }

    /* renamed from: ۟ۡۥ۠ۥ, reason: not valid java name and contains not printable characters */
    public static String m501() {
        if (C0001.m192() < 0) {
            return C0032.m431();
        }
        return null;
    }

    /* renamed from: ۟ۡۨۤۤ, reason: not valid java name and contains not printable characters */
    public static boolean m502(Object obj, Object obj2) {
        if (C0001.m192() < 0) {
            return ((Set) obj).contains(obj2);
        }
        return false;
    }

    /* renamed from: ۟ۡۨۥ۟, reason: not valid java name and contains not printable characters */
    public static int m503(Object obj) {
        return obj.hashCode();
    }

    /* renamed from: ۟ۢۢ۠۠, reason: not valid java name and contains not printable characters */
    public static int m504(Object obj, Object obj2) {
        if (C0002.m259() <= 0) {
            return ((InputStream) obj).read((byte[]) obj2);
        }
        return 0;
    }

    /* renamed from: ۟ۢۢۢ, reason: not valid java name and contains not printable characters */
    public static void m505(int i, Object obj, Object obj2, Object obj3, Object obj4) {
        if (m520() >= 0) {
            C0011.m346(i, (String) obj, (String) obj2, (String) obj3, (String) obj4);
        }
    }

    /* renamed from: ۟ۢۥۨۦ, reason: not valid java name and contains not printable characters */
    public static a m506(Object obj, Object obj2, Object obj3) {
        if (C0002.m259() < 0) {
            return a.m333((Member) obj, (Method) obj2, obj3);
        }
        return null;
    }

    /* renamed from: ۣ۟۟۟ۧ, reason: not valid java name and contains not printable characters */
    public static boolean m508(Object obj) {
        if (m520() >= 0) {
            return ((File) obj).delete();
        }
        return false;
    }

    /* renamed from: ۣ۟ۦۤۧ, reason: not valid java name and contains not printable characters */
    public static Class m509(Object obj) {
        if (m520() >= 0) {
            return obj.getClass();
        }
        return null;
    }

    /* renamed from: ۣۣ۟ۧۤ, reason: not valid java name and contains not printable characters */
    public static Integer m510(int i) {
        if (C0001.m192() < 0) {
            return Integer.valueOf(i);
        }
        return null;
    }

    /* renamed from: ۣ۟ۨۦ۠, reason: not valid java name and contains not printable characters */
    public static String m511(String str) {
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

    /* renamed from: ۟ۤ۟ۦۤ, reason: not valid java name and contains not printable characters */
    public static boolean m512(Object obj, Object obj2) {
        if (C0001.m192() <= 0) {
            return ((String) obj).matches((String) obj2);
        }
        return false;
    }

    /* renamed from: ۣ۟ۤۢۦ, reason: not valid java name and contains not printable characters */
    public static String m513(Object obj, Object obj2, Object obj3) {
        if (C0053.m574() < 0) {
            return Shell2Application.m284((String) obj, (String) obj2, (String) obj3);
        }
        return null;
    }

    /* renamed from: ۟ۤۦۥۡ, reason: not valid java name and contains not printable characters */
    public static String m514(Object obj) {
        if (C0001.m192() < 0) {
            return ((ByteArrayOutputStream) obj).toString();
        }
        return null;
    }

    /* renamed from: ۟ۥۢۦۨ, reason: not valid java name and contains not printable characters */
    public static String m515(Object obj, Object obj2) {
        if (m520() > 0) {
            return ((Properties) obj).getProperty((String) obj2);
        }
        return null;
    }

    /* renamed from: ۟ۥۣۥۧ, reason: not valid java name and contains not printable characters */
    public static List m516(Object obj) {
        if (C0002.m259() < 0) {
            return Arrays.asList((Object[]) obj);
        }
        return null;
    }

    /* renamed from: ۟ۥۥۦۢ, reason: not valid java name and contains not printable characters */
    public static void m517(Object obj) {
        if (C0001.m192() <= 0) {
            ((IllegalAccessException) obj).printStackTrace();
        }
    }

    /* renamed from: ۟ۦۡۦۣ, reason: not valid java name and contains not printable characters */
    public static KeyFactory m518(Object obj) {
        if (m520() >= 0) {
            return KeyFactory.getInstance((String) obj);
        }
        return null;
    }

    /* renamed from: ۟ۧۦۣۤ, reason: not valid java name and contains not printable characters */
    public static String m519() {
        if (m520() >= 0) {
            return C0027.m421();
        }
        return null;
    }

    /* renamed from: ۠۠ۧۧ, reason: not valid java name and contains not printable characters */
    public static int m520() {
        return 735 ^ C0001.f1;
    }

    /* renamed from: ۠ۥۢ۠, reason: not valid java name and contains not printable characters */
    public static void m521(Object obj, Object obj2, int i, int i2) throws IOException {
        if (C0053.m574() <= 0) {
            ((FileOutputStream) obj).write((byte[]) obj2, i, i2);
        }
    }

    /* renamed from: ۠ۦۨ۠, reason: not valid java name and contains not printable characters */
    public static void m522(Object obj) {
        if (C0002.m259() <= 0) {
            System.loadLibrary((String) obj);
        }
    }

    /* renamed from: ۡۥۡ۟, reason: not valid java name and contains not printable characters */
    public static String m523() {
        if (C0002.m259() <= 0) {
            return C0028.m423();
        }
        return null;
    }

    /* renamed from: ۣۢۥ, reason: not valid java name and contains not printable characters */
    public static File m524(Object obj) {
        if (m520() > 0) {
            return ((File) obj).getParentFile();
        }
        return null;
    }

    /* renamed from: ۢۦۥ, reason: not valid java name and contains not printable characters */
    public static void m525(Object obj, Object obj2) {
        if (C0053.m574() <= 0) {
            Throwable.class.getDeclaredMethod(NPStringFog.decode("0F1409321B111717171D030805"), Throwable.class).invoke((Throwable) obj, (Throwable) obj2);
        }
    }

    /* renamed from: ۢۧۧۢ, reason: not valid java name and contains not printable characters */
    public static boolean m526(Object obj, Object obj2, Object obj3, Object obj4) {
        if (C0002.m259() <= 0) {
            return C0037.m448((String) obj, obj2, (String) obj3, obj4);
        }
        return false;
    }

    /* renamed from: ۣۢۦ, reason: not valid java name and contains not printable characters */
    public static void m527(Object obj) {
        if (m520() >= 0) {
            ((NoSuchFieldException) obj).printStackTrace();
        }
    }

    /* renamed from: ۣۣۡ, reason: not valid java name and contains not printable characters */
    public static Class m528() {
        if (C0001.m192() < 0) {
            return Integer.TYPE;
        }
        return null;
    }

    /* renamed from: ۣۧۢۡ, reason: not valid java name and contains not printable characters */
    public static void m529(int i) {
        if (C0002.m259() < 0) {
            C0013.m388(i);
        }
    }

    /* renamed from: ۤ۠ۦۧ, reason: not valid java name and contains not printable characters */
    public static Method m530(Object obj, Object obj2, Object obj3) {
        if (m520() > 0) {
            return ((Class) obj).getMethod((String) obj2, (Class[]) obj3);
        }
        return null;
    }

    /* renamed from: ۤۤۡۢ, reason: not valid java name and contains not printable characters */
    public static long m531(Object obj) {
        if (m520() > 0) {
            return ((File) obj).length();
        }
        return 0L;
    }

    /* renamed from: ۤۨۤ, reason: not valid java name and contains not printable characters */
    public static void m532(Object obj, Object obj2, Object obj3) {
        if (C0001.m192() < 0) {
            ((Field) obj).set(obj2, obj3);
        }
    }

    /* renamed from: ۥۡۨۨ, reason: contains not printable characters */
    public static String m533(Object obj) {
        if (m520() >= 0) {
            return ((URL) obj).getPath();
        }
        return null;
    }

    /* renamed from: ۥۥۢ۠, reason: contains not printable characters */
    public static ClassLoader m534(Object obj) {
        if (C0053.m574() < 0) {
            return ((Class) obj).getClassLoader();
        }
        return null;
    }

    /* renamed from: ۥۣۨۢ, reason: contains not printable characters */
    public static String m535() {
        if (C0053.m574() <= 0) {
            return C0026.m419();
        }
        return null;
    }

    /* renamed from: ۦۧۢۦ, reason: contains not printable characters */
    public static void m536(Object obj) {
        if (m520() >= 0) {
            ((Exception) obj).printStackTrace();
        }
    }

    /* renamed from: ۧۤۥۤ, reason: not valid java name and contains not printable characters */
    public static String m537() {
        if (C0002.m259() < 0) {
            return C0020.m407();
        }
        return null;
    }

    /* renamed from: ۣۧۨ۠, reason: not valid java name and contains not printable characters */
    public static void m538(Object obj, Object obj2) {
        if (C0002.m259() < 0) {
            ((Pine.C0047) obj).m486((Throwable) obj2);
        }
    }

    /* renamed from: ۨ۠ۡۡ, reason: not valid java name and contains not printable characters */
    public static void m539(Object obj) {
        if (C0053.m574() < 0) {
            ((File) obj).deleteOnExit();
        }
    }

    /* renamed from: ۟ۢۧ۠ۥ, reason: not valid java name and contains not printable characters */
    public static String m507(short[] sArr, int i, int i2, int i3) {
        char[] cArr = new char[i2];
        for (int i4 = 0; i4 < i2; i4++) {
            cArr[i4] = (char) (sArr[i + i4] ^ i3);
        }
        return new String(cArr);
    }
}
