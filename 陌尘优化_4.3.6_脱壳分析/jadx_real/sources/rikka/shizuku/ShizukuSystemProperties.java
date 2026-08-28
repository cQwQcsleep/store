package rikka.shizuku;

import android.os.RemoteException;

/* loaded from: /workspace/unpacked/classes.dex */
public class ShizukuSystemProperties {
    public static String get(String str) throws RemoteException {
        return Shizuku.requireService().getSystemProperty(str, null);
    }

    public static String get(String str, String str2) throws RemoteException {
        return Shizuku.requireService().getSystemProperty(str, str2);
    }

    public static boolean getBoolean(String str, boolean z) throws RemoteException {
        return Boolean.parseBoolean(Shizuku.requireService().getSystemProperty(str, Boolean.toString(z)));
    }

    public static int getInt(String str, int i) throws RemoteException {
        return Integer.decode(Shizuku.requireService().getSystemProperty(str, Integer.toString(i))).intValue();
    }

    public static long getLong(String str, long j) throws RemoteException {
        return Long.decode(Shizuku.requireService().getSystemProperty(str, Long.toString(j))).longValue();
    }

    public static void set(String str, String str2) throws RemoteException {
        Shizuku.requireService().setSystemProperty(str, str2);
    }
}
