package core.pro.android.notify;

import android.app.Activity;
import android.os.Process;
import gTBLD.dev.XSSTG.free.Utils;
import java.io.PrintStream;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class h3 implements Runnable {
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00c4, code lost:
    
        throw new java.lang.RuntimeException(core.pro.android.notify."检测失败：Application 继承链中不存在目标类：" + r2);
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void run() {
        Object objInvoke = null;
        Activity activity = Utils.a;
        try {
            String str = i.APPLICATION;
            try {
                Class<?> cls = Class.forName("android.app.ActivityThread");
                objInvoke = cls.getMethod("getApplication", null).invoke(cls.getMethod("currentActivityThread", null).invoke(null, null), null);
            } catch (Throwable th) {
            }
            String str2 = "۟ۧۖۘۧۙ۟ۛ۟ۨۘ۬ۖ۫ۗۛۢۦۥۢۡۡۡۥۧۦۗۡۡۙۤۤ";
            while (true) {
                switch (str2.hashCode() ^ 1189407245) {
                    case -444665511:
                        String str3 = "ۦۚۥۗۦ۫ۤ۟۬ۘ۬ۙۘ۬ۡۗۛۥۢۚۗۡۖۧۢۡ۬۫۫ۢ۬ۦۙۘۖۘۘ";
                        while (true) {
                            switch (str3.hashCode() ^ (-1665321083)) {
                                case -1223843340:
                                    str3 = "ۢۖ۫ۛ۬ۡ۬ۧ۬ۗۖۡۚ۫ۥۢۧۨ۟ۖۘ۬۠ۙۜۛۥۨۘ۫ۚۥ۬ۚ۟۬۟ۖۙۚۙۚۡۤۜۘ۬۠ۡۜ۬ۘۘ۫ۥۘ";
                                    break;
                                case -876984044:
                                    str2 = "ۙ۠۟ۗۧ۬ۘۦ۟ۘۙۥۘۦۨ۬ۚۜ۠ۙۛۛ۠ۘ۠ۥۨۘ۬ۚۦ۬ۦۨۘۘ۠ۦۘ";
                                    continue;
                                case 678202183:
                                    if (objInvoke == null) {
                                        str3 = "ۘۖۦۘۡۧۘۘۙۜۧۛۗۘۘۤۤۥۘۖۤۧۖ۫ۧ۟۠ۛ۟ۥۤۥۥۜۘۥۥۨۡ۫ۜ";
                                        break;
                                    } else {
                                        str3 = "ۚۥ۬ۖ۟ۛۙۢ۫ۦۙ۠ۨ۬ۨۘۚۜۖۨۡۧۢۙۘ۟۠ۨۡ";
                                        break;
                                    }
                                case 1180736293:
                                    str2 = "ۗۖۛۖ۠ۢ۬ۥۛۡۨ۬ۡ۟ۤۦۧ۟۟ۙۡۘ۫۬ۚۘۙۘۘۦ۬ۛۛۥ۠ۤۦۖۘۧۜۘۗۗۧ۫ۛۥۘۘۜۥۘۛ۟۬۬ۘ۠";
                                    continue;
                            }
                        }
                        break;
                    case -86143156:
                        throw new RuntimeException("无法获取当前 Application");
                    case 1149526694:
                        str2 = "۠ۚۦ۠ۡۨۘۥۨۥۘ۬ۘۧ۫۬ۖۘ۫ۡۧۘۡۤ۠۬ۙۡۘۘۚ۫ۖۡۜۘ۠ۡۗۡۦ";
                        break;
                    case 2058696759:
                        Class<?> superclass = objInvoke.getClass();
                        while (true) {
                            String str4 = "۠ۡۥۘۛۢۖۘۘۘۡ۠ۖۡۖۧۧ۟۠ۥۛۗ۬۠ۢۥ۬۫ۗۥۘۙۡۖۘۢۦ۬";
                            while (true) {
                                switch (str4.hashCode() ^ 1407324790) {
                                    case -1538396585:
                                        str4 = "ۘۢۜۘۛۗۙۛ۠ۢۨۖۖۘۧۗ۫ۙۥۤۙۨۘ۠ۥ۬ۖۖۧۧۖۡۗۗۡۘۘۗ۫ۖۤۥ۟ۢۘۤۚۦۡۖۘ۬ۙۥ۟ۤ";
                                        break;
                                    case 467411021:
                                        String str5 = "ۢۙۥۥۧۤۚۡۨۥ۟ۨۜۨۖۘۦ۫ۨۙۨۘۘ۫ۧۘۧ۫ۧۧ۠۬ۧۦ۬ۥۥۧۘۢۢۧۢ۠ۖۘ";
                                        while (true) {
                                            switch (str5.hashCode() ^ (-1886965007)) {
                                                case -1910502609:
                                                    str4 = "ۜ۫ۚ۬ۘۘۦۥۡۡ۫ۦۜۙۛۗۛۦۘۗ۬ۦۦۖۚۡۨۡۜۙۘۗۜۧۘۛۘۖۜۦۜۘۦ۫ۜ";
                                                    break;
                                                case -1067676050:
                                                    str5 = "ۜ۟ۡۢۙۜۥ۟ۙۚۙۤۨۡۦۧ۟ۦۘ۬ۢۘۘۛۤۢۡۨ۠ۘۛۡ";
                                                case 772576909:
                                                    str4 = "ۗۘۘۘۜۧ۬۬ۗۤ۬۟ۗۖۤۘۚۨۘۨۦۗ۫۠ۘ۫ۤۡۢۦۤۚۢ۠ۗ۠۠ۦۡۧۡۚۧ۫ۢۧۥۘۤۡۘۙۜۛ";
                                                    break;
                                                case 992222435:
                                                    str5 = superclass != null ? "ۗۜۦ۫ۧ۫۠۬ۧ۠ۘۡۡۘۚۗۤۗۡ۠ۗۙۗ۬ۚۜۖۘۗۜۙ۟۫ۨۗ۬ۗۙۚۖۘۥ۬ۗ۟ۚۦۘ۫۟ۡۘۤۗۡۘۚۨ۟" : "ۤۚۤۥ۫ۛۘۤۦۗۘۧۘۦۘ۟۠ۘۢۤۢۧۜۥۛ۬ۨۘۘۗۚۗۧۘۘۚ۟ۜۘۖۡ۟ۘ۫ۨۘۡۡۛۦۦ۬۟۟۟ۨۛۡۘ";
                                            }
                                        }
                                        break;
                                    case 1075417925:
                                        String str6 = "ۖۢۜۡۡۨۘۚ۠۟ۘ۫ۥۚ۫ۨۘۙۢ۠۫ۦۧ۟ۗ۠ۘ۬ۡۘ۬ۥۚۦۧۡۘۨ۠ۦۘۜۡۖۘۚ۠ۜۘۢۛۥۘۤۜۦۘ۠۟ۘۘ۟ۢۦ";
                                        while (true) {
                                            switch (str6.hashCode() ^ (-1120443103)) {
                                                case -1152341522:
                                                    break;
                                                case -580388883:
                                                    str6 = "۠ۛۘۘۥۥۜۘۧ۫ۚۚ۬ۜۘۜ۟۫ۛۦ۫ۡۗۘۧ۟ۥۚۚۛ۠ۨۖۘۚۛ۬ۖ۟۠ۗۜ۠ۧۜۚ";
                                                    break;
                                                case 310946087:
                                                    String str7 = "ۥۨۜۤۜۢۡۖۧۧۗۤۦ۟۫ۡ۟ۡ۠ۨۤۗۡۘۖۧۡۘ۫ۗۡۛ۟ۙۙۘۥۡۥۦۜۘ۬۫ۖۜۖ۠ۡۗۡۘۙۜۤ";
                                                    while (true) {
                                                        switch (str7.hashCode() ^ (-1567843960)) {
                                                            case 1105633799:
                                                                str7 = superclass != Object.class ? "ۤ۟۬ۗ۬۬ۜۨۦۦۤۙۨۚۢۦۗۗۥ۫ۙۡۥۙ۟۬ۥۘ۬ۛۗۡ۬ۙۡۚۖۘ۠ۧۨۘ۫ۗۗۡ۬ۨۘۙۢۜ۟ۢ۟۟ۢۘۘ" : "ۢ۠ۚۘۘۚۗۡۥۘۤ۫ۥ۠۟ۛۘۢ۠ۜۡۨ۠۠ۡۖ۫ۖۘۙۜۧۗۚۧۘ۠ۥۗۢۚۧ۠ۦۘۧ۬ۦۘ۫ۖۧ";
                                                            case 1198806142:
                                                                str6 = "۫ۦۡۨۦۧۘۡ۫ۘۘۦۖۗۛ۫ۘۘۥۖۚۜۥۧۘۗۗۜ۫ۛۧۖۡ۟ۢۙۦۘ۠ۡۧ";
                                                                break;
                                                            case 1222730921:
                                                                str6 = "ۘۧۡۚۡۜۘۦۗۦۘۚ۠ۡۚ۬ۖۧۡۢۖۦۘۧۛۡۘۥۥۖۛۡۧۤ۟ۡۘۧۥۧۦۜۡۘۢۡۘ";
                                                                break;
                                                            case 1808482451:
                                                                str7 = "ۢۘۧۛۦۤۖۙۡۘۥۚۜۘۨۡ۬ۙۚۧ۠ۘۚۦۢۤ۠۠ۜ۟ۡۨۢۧۘ۠ۖۦۘۢ۫ۚۜ۫ۘۘۦۥۖۥۡۘۥۤۜۘۗ۠ۥ";
                                                        }
                                                    }
                                                    break;
                                                case 1616939786:
                                                    String name = superclass.getName();
                                                    PrintStream printStream = System.out;
                                                    printStream.println(" -> " + name);
                                                    String str8 = "ۦ۫ۨۦۛۦۘ۬ۜۧۘۡۜۧۢۙ۟۠ۡ۬۟ۙۦۗۡۘ۟ۧ۬ۖۥۘۙۚ۬ۡ۫ۥۘۨۡۡ۬ۡۜۘۖۤۗۤ۫ۘ";
                                                    while (true) {
                                                        switch (str8.hashCode() ^ (-1166745778)) {
                                                            case -2083392854:
                                                                printStream.println("检测成功：Application 继承链完整");
                                                                return;
                                                            case -694140662:
                                                                String str9 = "ۥۖۨۗۜۡۡۧۙۖۤۦۘۛۚۦۢۡۗۘۖۨۨۢۖۢۚۦۘ۠ۤۛۗۛۦۧ۫ۚۧۧۛ۬ۢۚ";
                                                                while (true) {
                                                                    switch (str9.hashCode() ^ 334092150) {
                                                                        case -1431154058:
                                                                            str9 = "۟ۧۦۘ۫۠ۡۘۨۛۤ۠ۥۡۡۛۛۜۗۜ۫ۜۥۘۛۙۘ۠۫ۤ۠ۛ۠۟ۦۛۢۘۦۙ۬ۥ۬ۥۧۘ";
                                                                        case -401380732:
                                                                            str8 = "ۦ۠ۘۗۧۖ۠ۛۨۘ۠ۙۜۚۤۥۗ۫ۡۘۚۦ۬ۦ۟۬۫ۚۘۙۚۡۖ۫ۙ۬ۨۘۚۖۗۧۥۨۘۤۛۜۘۘۦۨۘ";
                                                                            break;
                                                                        case 827593007:
                                                                            str9 = str.equals(name) ? "۠۟ۧۢۡۥۥۜ۫ۢۚۨۨۨۦۘ۬ۘۨۘۤۖ۬ۢ۬۫ۘۡۘۥۤۜۜۦۧۘ۠ۛۨۘ۫ۙۖۘۢۧۘ" : "ۡۡ۬ۚ۠ۤ۟۫ۦۨۢۖۖۧ۟۫ۧۘ۟ۗۢۜۛۡۜۘۡۚۦۘۖۜۤۧ۟ۥۘۡۢ۬ۥۗۧ";
                                                                        case 1041120985:
                                                                            str8 = "ۤۢۘۘۥ۬ۖۥۢۖ۟۠ۘۘۦ۫ۖۘ۟ۤۗۜ۟۫ۗۜ۠ۗۧۜۘۘۙ۬ۤۛ۬ۦۜۢۛۧۨ۠۬";
                                                                            break;
                                                                    }
                                                                }
                                                                break;
                                                            case 159771436:
                                                                break;
                                                            case 1646277769:
                                                                str8 = "ۘۥۘۜۢۘۢۧۗۛۧۡۢۤۡۘۢۨۜۘۧۖۜۘۦۥۡ۬ۖۘۦۥۨۘۚۙ۠۠ۖ۫ۜ۠ۨۘۥۦۜۥۚۖۘ۟ۛ۟";
                                                                break;
                                                        }
                                                    }
                                                    break;
                                            }
                                        }
                                        break;
                                    case 1599774212:
                                        break;
                                }
                            }
                            superclass = superclass.getSuperclass();
                        }
                        break;
                }
            }
        } catch (Throwable th2) {
            PrintStream printStream2 = System.out;
            printStream2.println("链路检测发生异常，类型 = " + th2.getClass().getName());
            printStream2.println("异常信息 = " + th2.getMessage());
            th2.printStackTrace();
            Process.killProcess(Process.myPid());
        }
    }
}
