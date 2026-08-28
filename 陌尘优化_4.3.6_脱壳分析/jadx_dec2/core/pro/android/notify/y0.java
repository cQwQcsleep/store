package core.pro.android.notify;

import android.util.Log;
import java.lang.Thread;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class y0 implements Thread.UncaughtExceptionHandler {
    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        StringBuilder sb = null;
        String str = "ۥۘۙۖۦ۟ۡۖۖ۟ۚۘۦۛۜۢ۟۠۟ۗۚۛۨۨۙۜۙۥۡۘۨۢۚۢۚ";
        while (true) {
            switch ((((str.hashCode() ^ 112) ^ 197) ^ 119) ^ (-513407388)) {
                case -555310898:
                    return;
                case -376572191:
                    str = "ۨۛۡۘۢ۫ۛۢۜۥۘ۫ۡۖۘۙۤۜۡۖۜۘۗۛۜ۠ۥ۬ۦۡۡۚۚۜۘۢۛ۠ۧۡۛۨۦۧۘ۫ۚۤۢ۬ۧۘۡۦۛ۬ۡۘۜ۫ۤ";
                    break;
                case -153590969:
                    str = "ۡ۬ۜۜ۠ۧۘۚۜۗ۫ۤۥ۟۫۬ۥۜۘۧۖۖ۠ۚۤۘۧۢۥۚۡۘۢۜۧۘۤ۠ۦۙۧۡ۬۬ۛۧۖۙ۟ۨۘ۬ۘۜۘۡ۫ۧ";
                    break;
                case 61379993:
                    sb = new StringBuilder();
                    str = "ۚۡۦۘۨۥۘۘ۟ۚۗۢ۠ۧۥۡۘۤۚۤۦۤ۫ۙ۫ۨۘۡۢ۠۠۬ۡۘۙۖۡۘۚۤۜۘ";
                    break;
                case 557126161:
                    sb.append(th.toString());
                    str = "۫ۙ۫ۥ۬ۡۥۦ۫ۘۤ۬ۧۡ۫۬ۢۖ۬ۜ۟ۚۘ۫ۘۡۘۙ۠ۖ";
                    break;
                case 600059806:
                    str = "ۧۖۨۘۚۛۚ۬ۦۡۨۜۜۧۧۧۗۡۗۛۤۨۘۛۗۤۦۨۤۙۛۛ۠ۜۘ۠ۚۨۘ";
                    break;
                case 1360203078:
                    k2.logToFloatingWindow(sb.toString(), "error");
                    str = "ۚۥۜۘۧۗۛۧۚۨۚۢۡۜۗۦۘۚۧۛۘ۠ۗۧۨۦۘۦۨ۟ۥۚۡ";
                    break;
                case 1771579108:
                    sb.append("崩溃日志：");
                    str = "ۗۦۤۥۢۖۙۗۖۙۘۛۡ۫ۖۘۘۦ۫ۦ۟ۗۦۨ۟ۙ۟ۥۗۥۨۖۦۨۘ۠ۤۖ۬ۙۢۡۨۘۙۧۡ۟۠۠";
                    break;
                case 1835471262:
                    Log.e("UncaughtException", "拦截异常：", th);
                    str = "ۙۢۙۢۖۘۘۙ۫ۨۘۧۡۢۙ۠ۨۘۜۙ۠ۛ۫۬ۘ۬ۚۙۜۧۘۜۥۖۥۛ۫ۜۚ۟۫۫ۤۡۨۥۘۡۖۜۘۡۛۚۢۧۨۗۤۖ";
                    break;
            }
        }
    }
}
