package core.pro.android.notify;

import android.util.Log;
import java.lang.Thread;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class m2 implements Thread.UncaughtExceptionHandler {
    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        StringBuilder sb = null;
        String str = "ۡۗۜۘۢۨۧۥۦ۫۫۬ۖ۠ۦۛ۫۫۠۠۬ۨۘۜۡۧۚۢ۫ۘۨۦۘۢۙۧۙۦۖۖۘ۫۟ۖۡۢۚۧۤۧۖۜۙۨۗۖۘ";
        while (true) {
            switch ((((str.hashCode() ^ 16) ^ 709) ^ 634) ^ 664906420) {
                case -2082938136:
                    sb.append(th.toString());
                    str = "۟ۛ۬ۧ۫ۤۚۚۚۦ۠ۢۜۥۦۡ۠ۦۖۢۡۘۤۡۧۨ۫ۡۘۨ۟ۡۖۗۘۘۗۙۗ۫ۚ۬ۜۘۚۜۖۘۛۗۤۚۡۛۛۚ";
                    break;
                case -1729021820:
                    str = "ۤۛۡۢ۠ۥۘۢۘۦۜ۫ۚۗۖ۬ۛۡۡۘۙۖۧۘ۬۟ۥۤۤۖۢۡۦۜۖۘ۬ۛ۟";
                    break;
                case -1639029254:
                    sb.append("崩溃日志：");
                    str = "ۡۖۡۘۢۤۥۗۥۡۧ۬۬ۢۡۘۡۚ۟ۜۥۤ۟ۚۜۜۥۗۙۜ";
                    break;
                case -1030649880:
                    str = "ۛۘۨۤ۠ۨۘۤۙۡۚۦۤۗۗۧۡۙۜۜۖۖۚۗۘۖ۬۠۫ۤۥ۫ۗۗۥۡۘۥۛۙۚۖۨۘ";
                    break;
                case -839107258:
                    sb = new StringBuilder();
                    str = "۠۬ۧۚ۟۠۠۫ۦۘۢۘۜۘۛۨۦ۠۬ۗۜۥۚۨ۟ۢۡۘۘۨۨۜۢۜۢۧۖۢ";
                    break;
                case -837133794:
                    k2.logToFloatingWindow(sb.toString(), "error");
                    str = "ۧۡۧۘۜۘۗ۬ۚۘۘ۫ۦۜۦۧۘۘۗ۬۬ۧۤۨۦۚۦۘۖ۟۬ۘۥۧۡۗ۫۫ۦۗۤۚ۠ۛۘۧۘ";
                    break;
                case -817402169:
                    Log.e("UncaughtException", "拦截异常：", th);
                    str = "ۚۙۜۘۚۛۘۘۥۨۜۘۤۦ۬ۡ۟۠ۧۙ۬۠ۜۤۙ۬ۦۘۥۦۨۢ۫ۡۘ";
                    break;
                case -85171125:
                    str = "ۢۢ۬ۤ۠ۡۘۢۛۥۘۚۦۛۦۥۥۡۤۢۧۛ۬ۤ۬ۡۘۜ۬ۨۘۤۙ۬ۡۘۨۧۜۨ";
                    break;
                case 1739029498:
                    return;
            }
        }
    }
}
