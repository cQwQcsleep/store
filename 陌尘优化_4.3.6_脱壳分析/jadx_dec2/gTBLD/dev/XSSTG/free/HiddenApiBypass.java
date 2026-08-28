package gTBLD.dev.XSSTG.free;

import android.util.Log;
import com.swift.sandhook.utils.FileUtils;
import core.pro.android.notify.l2;
import java.lang.reflect.Method;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class HiddenApiBypass {
    private static final String TAG;

    static {
        String str = "ۘ۬ۜۘۦۗۥۜ۫ۖۘۨۨۘۘۚۖۨۘۛۤۦۘۛۦۧ۫۟۬ۨۙ۬ۥۚ۟";
        while (true) {
            switch ((((str.hashCode() ^ FileUtils.FileMode.MODE_755) ^ 546) ^ 302) ^ 609637305) {
                case -1175361057:
                    TAG = "HiddenApiBypass";
                    str = "ۥۤۜۘۚۢۥۘۛۤۨۨۧۦۗۨۘۨۛۦۘ۟۟ۨ۠ۗۡۘۤۤ۬۠ۦۥۘۥۧۖۚۘ۟ۤۙۘۘۙۡۡۡۖۘۢۘۢۢۚۖ۫ۧۡ";
                    break;
                case -753786446:
                    return;
            }
        }
    }

    public static boolean addHiddenApiExemptions(String... strArr) {
        try {
            Class<?> cls = Class.forName("dalvik.system.VMRuntime");
            Method declaredMethod = cls.getDeclaredMethod("getRuntime", null);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, null);
            Method declaredMethod2 = cls.getDeclaredMethod("setHiddenApiExemptions", String[].class);
            declaredMethod2.setAccessible(true);
            declaredMethod2.invoke(objInvoke, strArr);
            return true;
        } catch (Throwable th) {
            Log.w(TAG, "Failed to set hidden API exemptions", th);
            return false;
        }
    }
}
