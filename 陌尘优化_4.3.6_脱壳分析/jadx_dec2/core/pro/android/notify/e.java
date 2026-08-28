package core.pro.android.notify;

import android.util.Log;
import java.lang.Thread;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class e implements Thread.UncaughtExceptionHandler {
    @Override // java.lang.Thread.UncaughtExceptionHandler
    public void uncaughtException(Thread thread, Throwable th) {
        StringBuilder sb = null;
        String str = "ۧۦۡۜ۠ۙۙ۫ۘۘۜۖۥۧۢۜۖۧۚۗۥۢۙۚۨۘۚ۟ۖۚۜ۟۠ۦۘۗۥۗۥۤ۟ۗۨۤ";
        while (true) {
            switch ((((str.hashCode() ^ 386) ^ 225) ^ 464) ^ 233696522) {
                case -1487020161:
                    sb.append("崩溃日志：");
                    str = "ۨۖۘۤۡ۬ۚ۠ۨۘۛ۠ۘ۬ۜۦۘ۫ۚۙۖۙۨۘۖۦ۠ۥ۟ۛ۠ۢۘ۬ۜۦۙ۟ۚ";
                    break;
                case -286832396:
                    str = "ۜۜ۫ۜۦۜۜ۠ۚۘۜ۫۠ۥۡۙۧۦۘۗۤۡۘۘۙۘۦۙۡۘۢۢ۠ۛ۫۠ۡۖۤ";
                    break;
                case 55242310:
                    str = "۟ۢ۬۟ۛۧۗۤۤۢۚۦۦۙۨ۬ۨۨۤۤۜۜۙۨۗۤۘۤ۬ۙ۫ۗ۟ۜۢ۟ۦۢۨۥ۠ۢۘۢۨ۬ۗۜۖۗۘۘ۠ۛۖۘ";
                    break;
                case 679290047:
                    str = "ۘۨ۟ۤۜۧۢۖۥۙ۟۬۬ۛۡۘ۠ۥۢۜۡۥۘۢۜۧۘۘ۬۠۬ۢۘۙۤۛۘۨۖ۠۟ۡۜۖۘ۫۟ۙ۬ۗۙۗۜۧۦۙ";
                    break;
                case 1133646009:
                    sb.append(th.toString());
                    str = "ۗ۫ۘۘۗۜۥۘۘۤۧۥۥۨۦۦ۠۟ۗۢۘۦ۠۬ۡۦۢ۠ۧۜۢۧ";
                    break;
                case 1229712195:
                    k2.logToFloatingWindow(sb.toString(), "error");
                    str = "ۖۖۨۢۖۡۘۧ۟ۥۘ۬ۢۨۦ۟ۙ۟۬ۜۤۖ۠ۖۥۘۡۘۥ۟ۡۡۥۖۤۡۘۘۦۘۥۢۘۗۘۦۗۢۙۦۥ۬ۡۢۗ۬";
                    break;
                case 1868859310:
                    sb = new StringBuilder();
                    str = "ۥۡ۬ۜۨ۟ۚۗۘۘۨ۫۟ۜۡۜۘۧۜۧۡۛۦۘۡۙۘۧۡۦۘۡۛۚ۟ۗ۫ۨۘۙۛ۟ۜۙۖ۫ۦۙۖۡۥ";
                    break;
                case 1984905495:
                    Log.e("UncaughtException", "拦截异常：", th);
                    str = "ۗ۬ۜۨۙۚ۠۬ۡۦۨۢ۫۫ۦۘۢۧۦ۬ۖۙۦ۫۫ۛۗۨۘۖۘۨۤۘ۠ۖۗۚ";
                    break;
                case 2094840887:
                    return;
            }
        }
    }
}
