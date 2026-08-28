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
                    TAG = l2.decrypt("O0L0AWjJKqsaaekVbNQY\n", "cyuQZQ2na9s=\n");
                    str = "ۥۤۜۘۚۢۥۘۛۤۨۨۧۦۗۨۘۨۛۦۘ۟۟ۨ۠ۗۡۘۤۤ۬۠ۦۥۘۥۧۖۚۘ۟ۤۙۘۘۙۡۡۡۖۘۢۘۢۢۚۖ۫ۧۡ";
                    break;
                case -753786446:
                    return;
            }
        }
    }

    public static boolean addHiddenApiExemptions(String... strArr) {
        try {
            Class<?> cls = Class.forName(l2.decrypt("kQEMyQHZyxyMExTaBZyzIqcVDssB34A=\n", "9WBgv2iy5W8=\n"));
            Method declaredMethod = cls.getDeclaredMethod(l2.decrypt("m3XH4Kp6M5ORdQ==\n", "/BCzst8UR/o=\n"), null);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, null);
            Method declaredMethod2 = cls.getDeclaredMethod(l2.decrypt("b5r6pV8wljdyvv6EcyyXP2yL54JYJw==\n", "HP+O7TZU8lI=\n"), String[].class);
            declaredMethod2.setAccessible(true);
            declaredMethod2.invoke(objInvoke, strArr);
            return true;
        } catch (Throwable th) {
            Log.w(TAG, l2.decrypt("zoUOj2XVqpHnxBSGdJHijOyAAo0g8NqsqIEfhm3B/oznihQ=\n", "iORn4wCxiuU=\n"), th);
            return false;
        }
    }
}
