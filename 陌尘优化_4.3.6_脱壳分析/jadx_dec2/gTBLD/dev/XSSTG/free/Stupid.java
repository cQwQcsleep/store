package gTBLD.dev.XSSTG.free;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Process;
import android.util.ArrayMap;
import android.util.Base64;
import android.util.Log;
import com.iqoocg.nm.fcRuQsQrcxOAzxwEalcM;
import core.pro.android.notify.l2;
import core.pro.android.notify.r;
import core.pro.android.notify.s0;
import core.pro.android.notify.s3;
import core.pro.android.notify.v0;
import j$.util.Objects;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import natives.cn.shell.killPath;

/* loaded from: /workspace/unpacked/classes2.dex */
public class Stupid extends Application {
    private static Context ShellContext;
    private static boolean hasInit;
    public static s3 webSocketClient;
    private Application originAppInstance = null;

    static {
        try {
            System.loadLibrary("cainiaosockethook");
            Log.i("HookApplication", "cainiaosockethook.so 加载成功");
        } catch (UnsatisfiedLinkError e) {
            Log.e("HookApplication", "cainiaosockethook.so 加载失败", e);
        }
        try {
            System.loadLibrary("yzrByteHook");
            Log.i("HookApplication", "yzrByteHook.so 加载成功");
        } catch (UnsatisfiedLinkError e2) {
            Log.e("HookApplication", "yzrByteHook.so 加载失败", e2);
        }
        String str = fcRuQsQrcxOAzxwEalcM.SIGN;
        try {
            Base64.decode(str, 0);
            String str2 = "۟۟ۘۘۧۢۦۨۚۧۚۡۜۗۜۧۘ۠ۨۜۘ۫ۛۦۘۦ۫۠۠ۦ۬ۨ۬ۢۦ۟ۛۜۦۛ۬ۧۖۗۦۙۛۚۜۦۜۘ";
            while (true) {
                switch (str2.hashCode() ^ 86137437) {
                    case -2018116454:
                        str2 = "ۘۘۨۧۖۧۘۖۗۥ۬۟ۖۘۛ۬ۛۢ۟ۥۙۦۚ۟ۦۙۙۛۨۘۨۙۙۨۤۜۘۘۘۡۘۘۜۜۘ۠۟ۜۤۤۜۘۨۜ۫";
                        break;
                    case -1605674392:
                        break;
                    case -230009966:
                        String str3 = "ۙۙۨۙۚۧۗۙ۬ۙۚۛۛۛۙۗۥۧۘ۬ۧۦۘ۠ۚۖۨۡۨۘۗۜ۠";
                        while (true) {
                            switch (str3.hashCode() ^ (-865993016)) {
                                case -1856576219:
                                    String str4 = "۬ۛۨۘ۠۟ۗۡ۟ۨۘۖۥ۫ۨۖۜۘۥۢۤۚ۬ۤۡۦۜۘۦۘۨ۠ۢۢۗۥ۬ۤۘۘۧۡۘ۬ۢۙ";
                                    while (true) {
                                        switch (str4.hashCode() ^ 1645720748) {
                                            case -1428497628:
                                                str3 = "۟ۛۘۘۙۢۘۘۖ۬ۘۘ۬ۢۖۘۢۜۙۢ۬ۘۢ۠ۘۘۗۘۘۘۚۖۘۡۤ۬۟ۤۚۙۥ۠ۗ۬ۜ۠ۗۜ";
                                                continue;
                                            case -1233710452:
                                                if (!"[#SIGN#]".equals(str)) {
                                                    str4 = "۠ۧۡۘۢۢۘۘۤ۫ۨۥۗ۫ۦۥۘ۫ۖۧۘۙۨۢ۬ۧۨ۟ۚۥ۠ۘۗۥۧۡۥ۟۟";
                                                    break;
                                                } else {
                                                    str4 = "ۨۡۖۘۤۗۤۚۨۘ۫ۚ۬ۧۖۤۛۙۦۘۡۗۦۜ۠ۗۜۗۘۘ۟ۗۦۘ۠ۡۥ۬۟ۦۘۙۥۗۘۡۢۘ۠۬ۛ۟ۥۘۢۢۤ۬ۧۡۘ";
                                                    break;
                                                }
                                            case 1942047064:
                                                str4 = "ۡۜۖۘۖۤۡ۫ۗۙۡۥۦۘۙۧۡۘۤۤۖۘۢۛۦۘۨۛۗۚۤۨۘۤۨۨۘۢۨۜۧۗۛ";
                                                break;
                                            case 2058988194:
                                                str3 = "ۚۧۥۘۛۡۡۘۘۦۘۦۧۗ۟۠ۜ۠ۨۘۢ۬ۧ۬ۦۨ۟ۦۜۗۢۤ۠ۜۜ۟ۙ۬۟ۢۦۢۢۦۘۨۨۨۢۥۜۘ";
                                                continue;
                                        }
                                    }
                                    break;
                                case -1399182113:
                                    break;
                                case -4530333:
                                    SignatureSpoof.killPM(fcRuQsQrcxOAzxwEalcM.PACKAGE, str);
                                    killPath.killOpen(fcRuQsQrcxOAzxwEalcM.PACKAGE);
                                    break;
                                case 390944675:
                                    str3 = "ۦۢۗۙۢۖ۫ۧۢ۠ۘۛۛۖۘ۬۠۠ۜۙۙۥۜۤۧۨۚۨ۫ۗ۬ۢۦۚ۟ۡۜۛۧ۬ۨۨ۠ۖۦۥ۠ۗۢۡۙۥۡۛ";
                                    break;
                            }
                        }
                        break;
                    case 708091922:
                        String str5 = "ۥۜۙۚۚۦۘ۬ۙۥۘ۟ۨ۟ۦ۠ۙۥۚ۬ۚۘۖۙۚۤ۫ۜۘۢۜۜۘ۫ۚۦۘ۬ۨۖۘۢۨ۟۠۟ۖۘ";
                        while (true) {
                            switch (str5.hashCode() ^ 1335381519) {
                                case -2051775190:
                                    str2 = "ۛۙ۬ۗۛۤ۫ۙۦۛۤۦ۠ۘۡۤۙۥۘ۫ۨۙۜۡۙۘۛۙۗۗۚۨۤۦۨۘۙ";
                                    continue;
                                case -1708366868:
                                    str5 = "ۡۖۜ۫ۦ۬ۗ۫۟ۧۜۘ۬ۖۘۡۙۡۛۖۖۘۤۡۘۖۥۧۜۙۖۘۥ۫ۙۗۖۜ";
                                    break;
                                case -501028404:
                                    if (!"[#PACKAGE#]".equals(fcRuQsQrcxOAzxwEalcM.PACKAGE)) {
                                        str5 = "ۡۦۖۘۙۛۚۜۢۧۛ۠۟۫ۜۙۢۚۨۙ۬۟ۗۤۜۡۨۨۦۧۜۖۖ۟ۜۗۡ۫ۧۖۘ۠ۛ۠ۛۡۦۘۙۧۚ";
                                        break;
                                    } else {
                                        str5 = "ۗۗۥۦۙۘۦۤۡۘۢ۟ۦ۬ۘۗۨۘ۠ۨۘۘۛ۬ۨۘۦۗۗۢۘۖۥ۟ۨۘ۬ۛۛۤ۟ۡۖۚۗ";
                                        break;
                                    }
                                case 1506771055:
                                    str2 = "ۛۧۜۘۧ۟ۤۨۤۢۡۨۦ۬۫۬۫ۘ۠ۖۛۖ۠ۛۥ۠ۧۥۨۚۡۦۗ۠۫۫ۨۨۨ۠ۤۙۖ";
                                    continue;
                            }
                        }
                        break;
                }
            }
            hasInit = false;
        } catch (IllegalArgumentException e3) {
            throw new RuntimeException("[菜鸟云验证] 签名格式非法，Base64 无法解析", e3);
        }
    }

    public static Context getShellContext() {
        while (true) {
            switch (((("ۛۗ۠ۦۡۗۗ۟ۡۘۧ۟ۥۘ۟۠ۥۘۥۢۗۚ۬ۨۘۢۨ۟ۧۙۦۘۚۙۡۘ۟ۛۗۙۜۨ".hashCode() ^ 324) ^ 249) ^ 235) ^ (-1193123916)) {
                case 2032920981:
                    return ShellContext;
            }
        }
    }

    public static native void init();

    /* JADX WARN: Code restructure failed: missing block: B:135:0x01dc, code lost:
    
        r1 = new java.io.BufferedReader(new java.io.InputStreamReader(r12.getAssets().open(core.pro.android.notify."yunzhuru.com")));
        r0 = r1.readLine();
        r1.close();
        r1 = "۠ۥۗۥۡ۬۟۬ۡۘۖۗۨۘۙۧۡۖۧ۟ۚۤۗۨۥ۠ۖ۬۬ۨۘۘ۠ۡۘۨۧۘ۫ۘۜۘۨۚ۫";
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x0207, code lost:
    
        switch((r1.hashCode() ^ (-1288233773))) {
            case -1443430612: goto L472;
            case -784404725: goto L471;
            case 321025848: goto L470;
            case 1450175258: goto L473;
            default: goto L474;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x020f, code lost:
    
        r1 = r0.trim();
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x0303, code lost:
    
        r1 = "ۦۜۢۥۛۘۛۦۨۘ۟ۡۜ۬ۚۗۤۡۖۘ۠ۦۥۘۜۖۨۘۘۗۖۘۨۡۖۦۙۗۜۛ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:0x0307, code lost:
    
        r1 = "۫ۘۙۥۛۦۘۖۢۥۘۜۜۤۛ۫ۥ۠ۖۘۢۧ۟ۦۢۦۤۨۛ۫ۛ۫۫۬ۙۗ۠ۗ۫ۚۙۙۜۥۘۘۗۖ۬ۛۜ۟ۢۧ۠ۗ۟";
     */
    /* JADX WARN: Code restructure failed: missing block: B:205:0x0311, code lost:
    
        switch((r1.hashCode() ^ (-633078263))) {
            case -1436620975: goto L673;
            case -749303206: goto L476;
            case 913687959: goto L475;
            case 1792160763: goto L674;
            default: goto L675;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x0315, code lost:
    
        r1 = "۬ۙۜۘ۫ۚۘۘۥ۟ۧ۟ۛۧۛۗۜۘۛۘۖ۟ۛۖۨۚۖۘ۬۠۠ۚۗۘۛۙۦۘ۫ۖ۬ۗ۟۠ۗ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:208:0x0318, code lost:
    
        r1 = "ۖۧۖۦۤۥ۠ۤۜۘۦۧۖۢۛۙۛۗۥۗۧۢۨۚۨۨۡۖۘ۫ۥۡۘۙۨ۠ۖ۠ۧ۠ۨۘۢۖ۟";
     */
    /* JADX WARN: Code restructure failed: missing block: B:209:0x031b, code lost:
    
        if (r0 == null) goto L677;
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x031d, code lost:
    
        r1 = "ۢۙ۠ۤۘۛۗۧۜۙۖۜۖۛ۬ۜۘۢۧ۬ۗۛۡۥۜ۬ۤۗۜۦۤ۫ۘۦ۫ۡۥۤۧ۫";
     */
    /* JADX WARN: Code restructure failed: missing block: B:211:0x0320, code lost:
    
        r1 = "ۗۚۨۘۤۖۦ۫ۥۡۨۜ۟ۛ۠ۙ۟ۥۘ۬ۢۜۘۡ۫ۗۖۤ۫ۨۥۦ۬ۡۧۘۗۖۦۚۦۤۦۥۘ۠ۛۖۘ۫ۢۙ۠ۗ۠ۡ۬ۦۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:213:0x0324, code lost:
    
        r1 = "ۤۥۡۘ۠۬ۡۘ۬ۥ۠ۗۨ۠۠۟ۦۘۙۧۥۧۗۘۙ۬ۥۨۜۢۖۥۛ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:26:0x0056, code lost:
    
        gTBLD.dev.XSSTG.free.Stupid.ShellContext = r12;
     */
    /* JADX WARN: Code restructure failed: missing block: B:27:0x0058, code lost:
    
        androidx.multidex.MultiDex.install(r11);
     */
    /* JADX WARN: Code restructure failed: missing block: B:352:0x05c1, code lost:
    
        r1 = r0;
     */
    /* JADX WARN: Code restructure failed: missing block: B:84:0x011d, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:85:0x011e, code lost:
    
        r0.printStackTrace();
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:117:0x01a4. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:99:0x0171. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:357:0x0163 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:405:0x0074 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:406:0x015f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:407:0x0179 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:408:0x0073 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:416:0x0175 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:417:0x017e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:418:0x019a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:419:0x01ce A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:423:0x016c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:458:0x01dc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:459:0x0253 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:460:0x0272 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:461:0x0276 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:463:0x01db A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:482:0x021e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:483:0x022c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:484:0x032c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:485:0x0349 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:489:0x0215 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:504:0x023f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:505:0x0597 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:506:0x05b8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:507:0x05bc A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:511:0x0236 A[SYNTHETIC] */
    @Override // android.content.ContextWrapper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void attachBaseContext(Context context) throws PackageManager.NameNotFoundException, IOException {
        String str;
        String str2;
        String line;
        String str3;
        String strDecrypt;
        String str4;
        String str5;
        Object obj = null;
        super.attachBaseContext(context);
        String str6 = "ۗۘۢۨۙۚ۟ۦۥۘۚۙۨۘ۫ۥۘۘۢۢۧۗۢۙۦۘۙۡۢۥ۬ۗۖۘ";
        while (true) {
            switch (str6.hashCode() ^ (-578954056)) {
                case -1611692596:
                    str6 = "ۜۢۘۘۤۢۧۧۨۙۛۚۘۘۧۖۗۜ۠۟ۢۢۦۤ۟ۜۖۙ۫ۥۡۦ۫۟ۜۙۢ۟۬ۡۚۘۧۗۘۖۤۦ۬ۦ";
                    break;
                case -902030620:
                    String str7 = "ۜۘۙ۠ۡ۬ۗۧۨۘۖۡۧۗ۬ۤۧۧ۬۬۟ۖۘۚۗ۠ۚۨۡ۟ۘۙۨۦۥۘۨ۬ۙۦۤۤۢۜۨ";
                    while (true) {
                        switch (str7.hashCode() ^ 597663641) {
                            case -1934614544:
                                str6 = "ۤۤۙۧۜۖۘۤۘۜۛۢ۫۟۟ۥۘۖۛۢۘ۬ۗ۟ۜۧۗۛ۬ۡۧۦۘۡۧ۫ۦۦ۬ۡ۠ۡۘۜۖۖۘ";
                                continue;
                            case -916442555:
                                str7 = "ۚ۫ۡۘۧۘ۟۬ۢۨۘۗۖۥ۠ۤۚۘ۬ۤ۟۠ۡۘۛۡۙۙ۬ۥ۟۠ۧۡۜۤۥۗۨۢۚۙۤۘۨۧۜۧۘۨۥۧ";
                                break;
                            case -382005819:
                                if (!hasInit) {
                                    str7 = "ۡۖۢ۫ۘ۠۠ۙۥۘۛۤۨۘ۟۠ۙۡۘۥۘ۬ۥۘۘۡۛۦۨۖ۟ۛ۟ۧۦۢۚۜ۟ۥۖۘ۠ۗۥ۫ۜۦۘۧ۬ۙ";
                                    break;
                                } else {
                                    str7 = "ۥ۬ۧ۟ۤۡۥۥ۫ۚۗۘۘۢ۬۟۫ۚۖۘۖ۠۬۠ۦۧۛ۬ۛۘۖۘۨۦۤۗۖ۠ۜۨۡۘ۠ۛۚۥۛۨۤۗۥۘۚۤۘۧۙۜۘ";
                                    break;
                                }
                            case -146466450:
                                str6 = "۬ۜ۠۠۬ۚۤۡۥۧۙۦ۫ۖۥۡۙ۟۟ۖۛۥۤۙۛ۫ۘ۟ۙۜۛۦ۫ۧۦ";
                                continue;
                        }
                    }
                    break;
                case 582338656:
                    hasInit = true;
                    String str8 = "ۡۛۜۘۗۖۧ۬ۤۙۢۗ۠ۧۤۧۖۧ۠ۦۘۡۜۥۦۜ۟ۧۚ۬ۚ";
                    while (true) {
                        switch (str8.hashCode() ^ 2015531859) {
                            case -1978581657:
                                String str9 = "ۖۖۦ۬ۨۘۤۥۥۘۖۧۖۘۢۛۨۘۙۨۖۘ۟ۘۛ۬ۢۧۨ۟ۦۘۘۤۥ۟ۨۥۥۡۘۨ۟ۦ۟۫ۙ۫ۨۦ۫ۙۘۘ";
                                while (true) {
                                    switch (str9.hashCode() ^ (-1889031931)) {
                                        case -1825321019:
                                            String str10 = "ۢۤۘۘۥۨ۟ۖۦ۠۫۬ۚۢۤۛۖۨۥۦۦۥۧۧۚۖۡۛۘۢۨۡ۬ۨۘۨۜۦۖ۟ۧۘۨۧۘ۟ۨۦۘ۟ۗۤۖۢۗۙۡۤ";
                                            while (true) {
                                                switch (str10.hashCode() ^ 236277174) {
                                                    case -1316767743:
                                                        str10 = "ۚۡۚۤۥۦۖۚۡۙۛ۟ۤۡ۟ۘۘۢۜۧۥۗۡۘ۟ۨ۬ۧۤۦۡۛۖ۠ۧۚۤ۟ۜۘۧۢۨۙۖۦ۟ۢ۠";
                                                        continue;
                                                    case 79726668:
                                                        String str11 = "ۛۘۗۗۤۢۧۥۤۚۚ۟ۤۚۧۛ۫ۦ۫ۧۨۜۖۘۘۡۚۚۖۗۚۨۗۦۢۡۤ";
                                                        while (true) {
                                                            switch (str11.hashCode() ^ (-432637181)) {
                                                                case -794706069:
                                                                    str11 = "ۦۦۦۘۧۛۦۧۤۘۘۨۜۘۜۨۙۨۢۗۛۧۛ۟ۚ۟ۨۛۖۘ۟ۘۡۘ";
                                                                    break;
                                                                case -713688142:
                                                                    str10 = "ۥۙ۫ۚۧۥ۟ۨۙ۠ۜۜۢۚۘۘۚۙۧۢۛۖۘۘۜۦۢ۠ۨۤۥۘۤ۫ۥ۬۟ۖۡۖۥۡۗۥ۬ۦۛۡۡۜۘۡ۫ۦۡ۠";
                                                                    continue;
                                                                    continue;
                                                                case -481156432:
                                                                    if (!Utils.isVpnActive(context)) {
                                                                        str11 = "۫ۨۗۦۢۧۛ۫ۘۥۧۡۧ۟ۨۤۛۨۜۗۜۜ۫ۨۗۨۘۢۨ۬ۤ۫ۖ۠۬۫ۗۜۡ۫ۦ۬";
                                                                        break;
                                                                    } else {
                                                                        str11 = "ۨۡۡۜۨۖۜۥۜۘۚ۫ۡۡۡۘۚۖۜۘۨۜ۟ۤ۫۬ۤ۟ۥۘۦ۬ۥۘ";
                                                                        break;
                                                                    }
                                                                case 853063142:
                                                                    str10 = "ۛۡۘۘۦۖۧۖۡۘۢۤۥۘۛۤۢۙۙ۫۠ۧۥۘۤ۠ۨۚ۠ۢۢ۫ۙۡۜۨۘۙ۬ۡۜ۫ۡۘۥ۬۟ۦ۟۫ۚۚۖۥۢۘۘۡۦۘۘ";
                                                                    continue;
                                                            }
                                                        }
                                                        break;
                                                    case 164414574:
                                                        break;
                                                    case 565335761:
                                                        Process.killProcess(Process.myPid());
                                                        return;
                                                }
                                            }
                                            break;
                                        case -1094696118:
                                            String str12 = "ۗ۬۟۠ۥۜۘۘۧۙ۫ۜۖ۫۫ۥۘۧۙۦۘۤۤ۟ۢۦۘۥۨۦ۟ۤۖ";
                                            while (true) {
                                                switch (str12.hashCode() ^ (-262807889)) {
                                                    case -1932268210:
                                                        str9 = "ۜۡۦ۫ۦ۟ۘۢۧ۠ۚۡۘۦۧۜۘۥ۠۠ۢۡۜۘۛۗۨۤۖۡۘۗۢۗۙۥۗۗ۫ۨۘۤۢۢۙ۟۟";
                                                        continue;
                                                        continue;
                                                    case 954786921:
                                                        str9 = "۟ۡۘۛۨۚۡۥۙۧۜۙۙۤۦۘۛۛۖۘ۠ۖۙۨ۫۟ۥ۟ۙۧۘۙۖۤۜۘ۠ۨۥۘ";
                                                        continue;
                                                    case 1380842506:
                                                        str12 = "ۥۘۜۘۛۖۥ۟ۖۖ۠ۡۥ۫ۡۥ۬ۡۨۤ۫ۚۨۚۖۘۤۡۜۘۦۢۢ۠۠ۡۘۜۧۜۡۦۥۢۙۙۘۥۜ۫ۥۘ";
                                                        break;
                                                    case 1993112911:
                                                        if (!"[#VPNCHECK#]".equals(fcRuQsQrcxOAzxwEalcM.VPNCHECK)) {
                                                            str12 = "۠۟ۦۦ۫۬ۡۜۜۡۥ۫ۚ۬ۧۛۛۤۦۤۖۨۗ۫ۡۦۖۦۙۦۘۘ۟ۜۘۗۡۛ۬ۚۡ۬۬ۧ";
                                                            break;
                                                        } else {
                                                            str12 = "ۜ۫ۘۥۚۡۘ۬ۙۥۢ۬ۤۛۥۘ۬ۢۙ۟ۘۨۘ۬ۜۦ۠ۦۚۨۙۖۘۖۙۡ۬ۧۙۖۛۡۛ۫ۘۘ";
                                                            break;
                                                        }
                                                }
                                            }
                                            break;
                                        case -823012087:
                                            break;
                                        case -621949360:
                                            str9 = "ۘۥ۠۟ۛۖۘ۬ۚۢۥۨ۬ۙۚۧ۬ۤۖۡۖۗ۫۬ۚۢۚۘۘۚۦۤ۟ۛۦۘۨۜۢۜۡۜۤۨۨۘۤۙۨۘۡ۬ۦ";
                                            continue;
                                    }
                                }
                                break;
                            case -948693413:
                                str8 = "۟۬ۜ۠ۦۧ۬ۗۡۦۨۜۡۡۨۙۨ۬ۛ۫ۡۗۤ۠ۛۧۖۘ۠۟ۡۘ۫۫ۢۨۜۘۜۨۜۦۙ۬ۦ۠ۗۧۢۗ";
                                break;
                            case 580904200:
                                return;
                            case 1877232640:
                                String str13 = "ۡۙۦۘۛۨۧۘۧۢۖۖۡۖ۫۠ۖۧۡۥۖۨ۟ۥۗۖۤ۫۬ۚۛۖۘۦۧۡۜۖۖۘۥۤۛۡۘۘۢۡۧۥۖۘۘۤۥۥ۟ۥۚ";
                                while (true) {
                                    switch (str13.hashCode() ^ 577322977) {
                                        case -1758275920:
                                            str13 = "ۛۗۦۘۘ۬ۙۙ۫ۥۨۤۥۘ۫ۦۘۚۗۤۨۦۗ۬۬ۡۘۙۨ۫ۡۜۧۘۨۜۛۦ۫ۥۘۡۡۙۛۙۥ";
                                            break;
                                        case -667968490:
                                            if (!Utils.isMainProcess(context)) {
                                                str13 = "ۖۦ۫ۖۨۧۘ۫۬ۛۗۤۜۘ۬۫ۡۦۥۢۖۗۖ۟ۛۘۘۦۡۨ۟۟ۨۧ۠ۦۘۜۨۢOۤ۠ۖۘۗۗۨۘۘۘۦۗۥۧ۬۫ۛ";
                                                break;
                                            } else {
                                                str13 = "ۥۥۘۘۤۛۛۖۢۨۘۡۤۖۘۡۗۘۡ۠ۢ۫ۛۡۛۨۗۥۤۨ۫ۡۙ";
                                                break;
                                            }
                                        case 721629656:
                                            str8 = "ۥ۠ۢۥۗ۠۟ۦۡۢۨۘۙۥۘۘۡۙۤۘۙۙۧ۫ۨۘ۬۬ۡۢۘۧۥۤۥۘۛ۫ۥۘ۟ۘۨۘۧۦ۬";
                                            continue;
                                        case 1441113377:
                                            str8 = "ۡۤۧۧ۬ۗۙۙۗۦۧۡۗۥۚۡۨۘ۬ۥ۟ۦ۬ۦ۠ۛۘۘۖۛۥۙۚۘۘ۠ۗۘۘۜۤۧ۬ۖۦۘ۠ۢۗۤۢۦۘۛ۠ۡۘۨۡۖۘ";
                                            continue;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case 1753059319:
                    return;
            }
        }
        try {
            int i = Build.VERSION.SDK_INT;
            str2 = "ۤۙ۫ۘ۠ۘۘ۫۫ۚۗۘ۫ۤ۟ۘۘۨۜۘۢ۠ۥۘۦۡۜۡۗ۠ۙۨۥۘ";
            while (true) {
                switch (str2.hashCode() ^ 916059140) {
                    case -1474211126:
                        break;
                    case -1218422523:
                        str2 = "۫ۤۧۦۡۖۘۗ۫۟۬۬ۗۦۦۘۚ۫۟۫ۙۡۘۗۖۨۤۦۛۡۥ۬ۧۥۢۙ۠ۛۘۖۘۦۛۦ";
                    case 456815283:
                        String str14 = "ۗ۠ۡۘۚۤۧۢۘۖ۫ۚۖۘۢ۫ۢۦۜۥۘ۠ۦۥۘۛۗۡ۫ۨۨ۟ۙۛۥۘۖ۬۠ۖ";
                        while (true) {
                            switch (str14.hashCode() ^ 660266744) {
                                case -1950691141:
                                    str2 = "ۡۘۥۗۖ۠ۛۨۨۘۢ۟۟۫ۨۘۦ۬۠ۖۤ۠۟ۥۨۘۙۦۦۘ۟ۨ۠۠ۖۜۘ۫۫ۢ۟ۙۡۘۛۛۜۘۡۤۡۘۧۙۖ";
                                    continue;
                                case -1850257445:
                                    str2 = "ۥ۬۫ۨۖۘۘۤۦۜۘۘۨۢۛۖۚۗ۫ۥۘۢۧۡۘۖۘۘۘۙۘۛۙۙۡ۫۬ۧۙ۟۫ۘۧۛۛۚۦ";
                                    continue;
                                case -1014197075:
                                    str14 = "ۧۖۗۥ۠ۨۡۗۢۦۘۧۘۘۦۜۘۢۤۜۥۤۚۧۡۧۤۢۡۘ۬ۦۗۘ۫ۙ۫۬ۚ";
                                    break;
                                case 1373654763:
                                    if (i < 28) {
                                        str14 = "ۨۛۖۘۘۘۤ۠ۖۙۘۖۖۙۥ۠ۘۘۧۤۛۡۥ۫ۗۘۗ۠ۘۚۨ";
                                        break;
                                    } else {
                                        str14 = "۠ۙۘۘۙۧۨۧۘۥۘ۟ۢۘۛ۟ۢ۬ۨۘۘۦۢۖۤۖۥۘۥ۠ۗۧ۫ۖۘۨۗۘۡ۫ۘۨۙۖۤ۠ۜۡۦۨۦۡۦ";
                                        break;
                                    }
                            }
                        }
                        break;
                    case 1184135625:
                        try {
                            line = ShellAppComponentFactory.getOriginAppClassName();
                        } catch (Throwable th) {
                            line = null;
                            str3 = "ۙۖۖۘۚۙۨۢ۫ۡ۠ۧۢۨۗۨۘۢۗۤۦۤۚ۠۠ۦۚۚۥۘۢۢۜ۟ۧ۟ۜۛۨۘ";
                            while (true) {
                                switch (str3.hashCode() ^ (-1829006975)) {
                                    case -710751157:
                                        String str15 = "ۨۛۢ۟ۖ۬ۤۗۦۘۤ۠ۘۘۢۤۗۦۢ۬۫ۨۡۘۖۥۧۜۙ۬۠ۚ۫ۚۤۖۚۛ۟ۗۜۘۘۡۥ۫ۥۡۜۖ۠۫ۗۥ۠ۗۚۨۘ";
                                        while (true) {
                                            switch (str15.hashCode() ^ 914045921) {
                                                case -1871025630:
                                                    str3 = "ۡۖۡۖۘۘۘۨۙ۬۟۬ۡۘۥ۟ۚۨ۬ۘۦۚۚ۟ۦ۬ۚ۟ۖۘۚۨۢۗ۫ۙۢۜۚۗۥ۠ۤۢ۫";
                                                    continue;
                                                case -1098110593:
                                                    str3 = "ۥ۠ۢۥۡۦۢۥۤۗۥۤ۟۬ۥ۠ۛۡۘۘۛۨۢۦۡۘۙۧۙۡۚۡۘۥۛۡۘۖۦۢۦۜۗۜۨۘۘ۟۬ۖۘۙ۫ۗ";
                                                    continue;
                                                case -788244252:
                                                    str15 = "ۚۛۘۘ۠ۗۥۘۡۧۖۘۤۙۚۗۤۡۡۚۥۦ۫ۥۘ۠ۦۤۤ۟ۗۛۦۥۘ۠ۙۚۧۤۙ۠ۡۡۘۢۨۧۘۤۚۦۖ۬";
                                                    break;
                                                case 468149177:
                                                    if (line == null) {
                                                        str15 = "ۧۘ۠ۨۛۤۘ۫ۜۘۖۢۡۘ۟ۜ۫ۦ۫ۚۛۛۚۚۦ۫ۧۗ۫ۨۧۛۚۥۘۢۖۥۦ۫ۨۧۤۥۘۦۘۡۘ۬ۦۦۘ";
                                                        break;
                                                    } else {
                                                        str15 = "ۖۖۢ۬ۚۧۙۨ۫ۗۨۚۦۡۘۘۜۖۘۧۖۡۘۘۜۢۜۧ۟ۗۦۦۛۗۘۘۤ۬ۦۘۥۥۜۘۖۧۤ";
                                                        break;
                                                    }
                                            }
                                        }
                                        break;
                                    case 43954592:
                                        str3 = "ۥۨۦۖ۟ۡۘۥ۬ۜۥۤۙۡۢۘ۬ۨۖۡۗۜۗۜۙۜ۟ۜۘۢ۟ۥۘۘۥۧۧۥۥۘ۟ۧۦۘۗۛۢۢ۟۫۫۟ۘۘ";
                                    case 777432382:
                                        break;
                                    case 1285731008:
                                        String str16 = "۫۠ۚۤۡۘۜۥۖۦۚ۟۫۟ۗ۟ۦۦۛۛۜ۟ۥۘۙۖ۟ۥۚۡۘۚۖ۫ۙۤ۫";
                                        while (true) {
                                            switch (str16.hashCode() ^ (-268426363)) {
                                                case -954230756:
                                                    String str17 = "۟ۤۡۘۨۢ۬ۦۥۛۦۧۜۘۦ۠۬ۙۨۛۛۘ۟ۘۘۨۘۧ۟ۗۗ۬۠ۛ۫۟۫۬ۖ۫ۡۢۜۘۥۘ";
                                                    while (true) {
                                                        switch (str17.hashCode() ^ 1642192489) {
                                                            case -845549022:
                                                                str16 = "ۚۗۘۥۡۨۘۧۖۚۨۡۘۙۢۥ۟ۜۘۙۖۘۖۗ۟۬ۤۚۗۨۖۘۜۗۥ۫ۨۥ";
                                                                continue;
                                                            case -513290138:
                                                                str17 = "۠۫ۛۥۜۘۘ۟ۥ۟ۦۚ۠ۙۜۘۨۤۛ۬۠۫ۧ۟ۨۘ۟ۧۘۖۡۢۨۦۡۘۦۧۖۖۜۜۛۨۘ۟ۤۜۘۙۜۡۘ۟ۡۨۥۘۘ";
                                                                break;
                                                            case 239811140:
                                                                str16 = "۫ۤۥۘۜۧۦۘۦۗۦۘۙۙۖۘ۬ۢۨۖۦ۬ۘۘ۠ۚ۠۠ۥ۬ۡۚۡۘۛ۬ۡۘۤۛۡ۫ۦۘۖۥۜۘ۫۬ۜۡۖۗ";
                                                                continue;
                                                            case 1409412125:
                                                                if (!line.isEmpty()) {
                                                                    str17 = "ۢۜ۟ۧۛۨۘۢۘ۠۠۬ۧۛۛۥۘ۠ۦۤۚۤۡۘۛۧۡۥۚۜۛ۠۠ۗۚۛۜۜ۬";
                                                                    break;
                                                                } else {
                                                                    str17 = "ۘۖۙ۫۬ۜۢۨۚ۫۠ۥۘۢۥۚۥۙۚۜۢۥۨۗۗۛۤۙۧۡۥ";
                                                                    break;
                                                                }
                                                        }
                                                    }
                                                    break;
                                                case 1276413427:
                                                    str16 = "ۡۛۖۛۚۗۤۤۖۘۘۤۢۦ۬ۙۙۛۖۘۨۜۙۗۛۜۘۗۨۘۘۛۛۨۢۖۚۧ۬";
                                                case 1426530399:
                                                    break;
                                                case 1778893075:
                                                    break;
                                            }
                                        }
                                        break;
                                }
                                str4 = "ۢۘۗۙ۬ۢۗۢۖۘ۠ۨۨۘۜ۟۬ۢۤۨۘۚ۬ۙۥۗۘۘۢۘۘۘۥۛۖۘۙۗ۫۫ۤۚ";
                                while (true) {
                                    switch (str4.hashCode() ^ 333054673) {
                                        case -2006298974:
                                            String str18 = "ۦۘۡۢۤۘۘۖۨۜۘۡۘ۬ۗ۠ۚۖۗۜۘ۬ۧۖۘۦۨۛۛۢۘۘۙۛۙ۠۠۫ۢۖۜ";
                                            while (true) {
                                                switch (str18.hashCode() ^ (-1665124703)) {
                                                    case -1890692950:
                                                        str18 = "۟ۛۘۙۗ۟ۨۗۘ۠۬ۜۚۖۨۘۦۘۡۘ۬ۚۖۘۢۚۘۚۧۦۘۖۛۤۥ۟ۤۘۚ۟";
                                                        break;
                                                    case -1385220824:
                                                        if (strDecrypt == null) {
                                                            str18 = "ۖۥۦۘۤۨۥۘۢۨۦۧۥۜۨۙ۬ۘۙۨۘۘۙۡ۬۫ۥۚ۠ۡۘۥ۠ۨۘۢۖۖۚۙۜ";
                                                            break;
                                                        } else {
                                                            str18 = "ۧۥۜۘۙۖۜۘۤۨۡۙۨۤۤۨ۠ۨۡۛ۟ۛۦۘ۠ۥۧ۬ۙ۬ۘ۠ۙۤۡۡۥۘۦۘۥۖ۫ۢۧ";
                                                            break;
                                                        }
                                                    case 309545733:
                                                        str4 = "۬۠ۢ۠ۛۚۤۛۜۜۖۖۘۥۨۖۘۧۢۡ۟ۚۥۘۢۘ۬ۦۤۦۘ۬۬ۦۘ";
                                                        continue;
                                                    case 498922110:
                                                        str4 = "ۗۨۖۘۤۥۨۘۚۜۡ۬ۡۚۨۘۦۘ۬۠ۢ۫۟۟ۙۙ۠ۜۗۢۖۘ۫ۧۘۖۘۥۘۜۘۧ۫ۖۘۖ۫ۘ";
                                                        continue;
                                                }
                                            }
                                            break;
                                        case -1742338691:
                                            String str19 = "۫۠ۙۘۦۖۘۖ۟ۥۘ۬۟۠۬ۥۙۦۜۨۘۧۨۘۘۤۜۘ۠ۘ۬ۛ۬ۤ";
                                            while (true) {
                                                switch (str19.hashCode() ^ 1695756707) {
                                                    case -2108870282:
                                                        String str20 = "ۛ۟ۗۤۗ۠ۡۙۡۘۙۥۚۗۜ۬ۧۧۘۘۤۡۖۘۡۤ۫ۤۦۦ۟ۢ۟ۚۘۘۜۙ";
                                                        while (true) {
                                                            switch (str20.hashCode() ^ 1965812415) {
                                                                case -2050951877:
                                                                    str19 = "۟ۚۖ۟ۥۦۛۢۡ۠ۧ۬ۗۘۘۗۢۡۘۘۖ۟ۦۢۚۧۘۗۜۗۚۤۡۡۛۧ۫ۗۤۗۦۘۘ";
                                                                    continue;
                                                                case -1763874847:
                                                                    str20 = "ۙۨ۫۟ۜۖۜۡۦ۟ۙۛۥ۫ۖۘ۟ۜۧۘ۫ۦۡۘۤۘ۫ۢۦۥۘۤۚۧۖۛ۫ۗۧۚ۫۬ۖۤۢۘۗ۬ۥۘۖۦۦۖۢۦۘ";
                                                                    break;
                                                                case -1340597625:
                                                                    if (!strDecrypt.isEmpty()) {
                                                                        str20 = "۬ۨۦۘ۫ۗۥۥۥۖۘۖ۟ۡۗۗۚۜۜ۬ۨۛۥۚ۟ۘ۟ۙۡ۫ۨۙۡۜۜۘ۠۫ۜ۟ۗۛۘۘۘۚ۫ۦۧۚۜۘۛۜۥۙۡۢ";
                                                                        break;
                                                                    } else {
                                                                        str20 = "ۦۖۢۤۛۘۖۥۖۦۨۤۛۧۙ۬ۥۘ۬۟۟۬ۘۢۨۛۢۗ۫ۤۡۗۦۘۛۙۤۛۨۛۙۨۥۘۗۥ۫";
                                                                        break;
                                                                    }
                                                                case -639554908:
                                                                    str19 = "ۡ۟ۡۘ۠۠ۖ۠ۤۡۘۨۧۧۤۚۧ۫ۥ۫ۡ۬ۢۘۗۘۧۤۘ۟۬ۖ۟ۖۨۙۜ۟ۡۛۨۥۧ۟";
                                                                    continue;
                                                            }
                                                        }
                                                        break;
                                                    case -1693023339:
                                                        break;
                                                    case 365941717:
                                                        str19 = "ۛ۟۫ۨۜۘۜۧۧ۠ۢ۫ۘۖۦ۬ۢۚ۟ۤۖۦۧۘۤ۬ۧۛۘۨۘۢ۫ۢۡۨۤ";
                                                    case 1303109543:
                                                        String str21 = "ۖ۬ۛۚۨۡ۠ۨۡۨ۫ۜۘۙۖۜ۟ۢۘۗۘۗۙ۫ۘ۫ۙۗۘۖۘۨۚ۟۫";
                                                        while (true) {
                                                            switch (str21.hashCode() ^ 230262389) {
                                                                case -1931042285:
                                                                    break;
                                                                case 347332204:
                                                                    str21 = "۫ۙ۠ۢۦۛۗۚ۬ۤۙۢ۫۠۬ۗۖۤۚ۠ۛۨ۬۟ۚۡۨۛۘۥۨۧۧۢۡۘۤۦ۫ۛۥۨۘۧۖۤۚۙ۬";
                                                                case 1067106324:
                                                                    Class<?> cls = Class.forName("android.app.ActivityThread");
                                                                    Method declaredMethod = cls.getDeclaredMethod("currentActivityThread", null);
                                                                    declaredMethod.setAccessible(true);
                                                                    Object objInvoke = declaredMethod.invoke(null, null);
                                                                    Field declaredField = cls.getDeclaredField("mInstrumentation");
                                                                    declaredField.setAccessible(true);
                                                                    Application applicationNewApplication = Instrumentation.newApplication(Class.forName(strDecrypt), context);
                                                                    this.originAppInstance = applicationNewApplication;
                                                                    Field declaredField2 = cls.getDeclaredField("mInitialApplication");
                                                                    declaredField2.setAccessible(true);
                                                                    Application application = (Application) declaredField2.get(objInvoke);
                                                                    declaredField2.set(objInvoke, applicationNewApplication);
                                                                    Field declaredField3 = cls.getDeclaredField("mAllApplications");
                                                                    declaredField3.setAccessible(true);
                                                                    Object obj2 = declaredField3.get(objInvoke);
                                                                    String str22 = "ۥۜۖۘۗۦ۟ۜۦۨۛ۠ۜۘ۫ۦۜۦۘۥۘۥۚۚۡ۫ۙۖۘۚۙۤ";
                                                                    while (true) {
                                                                        switch (str22.hashCode() ^ (-1737236681)) {
                                                                            case 560860152:
                                                                                List list = (List) obj2;
                                                                                list.remove(application);
                                                                                String str23 = "۠ۨۘۨۡۖۘۥۚۦۧ۫ۘۘۢ۠ۦ۫ۗ۟۠ۖۙۙ۬ۖۘۥۥۨۘۖۘۚۨۗۦۘۜۜۥ";
                                                                                while (true) {
                                                                                    switch (str23.hashCode() ^ (-499637178)) {
                                                                                        case -2144098567:
                                                                                            String str24 = "۠ۜۡۘۙۨۥ۟ۨۘۘۨۛۗۘۛۨۡ۟ۜۘۖۛۗۙۢ۟ۥۗۦۘ۬ۥ۠۫۠ۡۘۗۦۖۘۖۤۛۧۧ۬";
                                                                                            while (true) {
                                                                                                switch (str24.hashCode() ^ (-1787038364)) {
                                                                                                    case -1619040642:
                                                                                                        str24 = "ۥۜۦۘۦۖۧ۟ۙۤ۬ۜۡ۟ۧۗۨۖ۫ۖۘۤ۬ۜۨۖۗۧۤۨۦۗۙ۬ۛۦۡۦۥۜۛۚۦۘۡۚۥۘۜۗۗۗۥۢ۠ۡۜ";
                                                                                                        break;
                                                                                                    case -1167913202:
                                                                                                        str23 = "ۢۤۖۘۥۗۦۨۡۘ۫ۜۡۘۢ۠ۨ۫ۧ۟ۖ۠ۜۡۢۖۘۚۘۛۙ۫ۨ۟ۖۥۜۡ۟۟۟۫ۗۡۘۘ";
                                                                                                        continue;
                                                                                                        continue;
                                                                                                    case -880763728:
                                                                                                        if (!list.contains(applicationNewApplication)) {
                                                                                                            str24 = "ۗۙۘۘۡۤۗۤۙۡۘۧۥۦۘۦۥۙۢۙۡۖ۟ۡۘۜۥۗ۠ۧۚ۬ۘۚۥۦۖۨۘ";
                                                                                                            break;
                                                                                                        } else {
                                                                                                            str24 = "ۦ۫۬ۡۗۤۙ۠۠ۨۤ۠ۛۘۤۙۨۦۨۗۚ۠ۛۥ۫ۡ۠ۦ۠ۡۧۛۥۘۗۡۡۘ";
                                                                                                            break;
                                                                                                        }
                                                                                                    case -154282014:
                                                                                                        str23 = "۫۫ۗۥۛۙۦۛۘۘ۬ۜ۬ۗۘۚۤ۟۬ۡۦۘ۟۟ۧۥۗۥۘۛۢۘ";
                                                                                                        continue;
                                                                                                }
                                                                                            }
                                                                                            break;
                                                                                        case -1386245178:
                                                                                            list.add(applicationNewApplication);
                                                                                            break;
                                                                                        case 1635989549:
                                                                                            str23 = "ۙۛۘۘۡۥۧۘۨ۫ۦۘۧۧۨۘۚۤۖۡۥۖۤۡۘۛۧۜۘۛۧۗۢۤۧ۟ۨۦۦۨۥۘۚ۟ۖۘۨۤۜۜ۬ۗۤۦۧ";
                                                                                            continue;
                                                                                        case 1894248771:
                                                                                            break;
                                                                                        default:
                                                                                            continue;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case 1259104908:
                                                                                String str25 = "۫ۤۧۘ۟ۙۜۛۤۖۢۖۘ۠ۛۘۘۨۘۥۘۜۨۨۛۤۨۘۜۧ۟ۗۧۧ۠ۘۘۤۥ۟ۤۧۨۛۗۖۘۛۢۘۚۜۥ";
                                                                                while (true) {
                                                                                    switch (str25.hashCode() ^ 1567185145) {
                                                                                        case -1267058003:
                                                                                            str22 = "۫ۗۢ۠ۙۥۘۦۦۖۘۙۙۧۦۦۗۙ۠ۦۜۜۡۘۖۢۛۗۧۢۜۤ۬ۡ۬۠ۚۘۨۘۜۜۖۘۜۨۥۘۡۢ۬ۧۤ";
                                                                                            continue;
                                                                                            continue;
                                                                                        case -227928030:
                                                                                            str25 = "ۛۗۨۘ۬ۚۖ۫ۡۜۤۜۘۘۡۧ۟ۚۘۖۖ۟ۦۘۗۛۥۛ۫ۖۘۘۧۜۚۦۧ۠ۤۚۚۛۡ۬ۧۖۘۦۖۙۢۙ";
                                                                                            break;
                                                                                        case -227485195:
                                                                                            if (!(obj2 instanceof List)) {
                                                                                                str25 = "ۚۘۘۧۦ۫ۛ۟ۗۤ۠۬ۤ۬ۡۜۚۘۢۛۘۘۛ۠ۘۥ۠ۥۗۥۦۘۦۤ۫ۛۜ۠";
                                                                                                break;
                                                                                            } else {
                                                                                                str25 = "ۙۜۤۦۛ۬ۘۧۤ۠ۖۧۦۥۘۦۗۗۨۙۦۡۖۨ۫ۡۖ۠۫ۦۜۦۤۜۘۙ";
                                                                                                break;
                                                                                            }
                                                                                        case 335634315:
                                                                                            str22 = "ۖۤ۬ۗۧ۠۠ۗۨۘۙۡۥۘۨ۫ۤۤۡۤۗ۬ۦۧ۟ۢۗۖۘۗ۬ۙ";
                                                                                            continue;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case 1690433012:
                                                                                str22 = "ۢۤۘۧۜ۫ۤۦۚۜۜۦۢۢۡۤۗۜۤ۟ۛۨۛ۬ۖ۫ۡۗۡۘۙۦۗ۠ۦۨۘ";
                                                                                continue;
                                                                            case 1876342217:
                                                                                break;
                                                                            default:
                                                                                continue;
                                                                        }
                                                                    }
                                                                    Field declaredField4 = cls.getDeclaredField("mPackages");
                                                                    declaredField4.setAccessible(true);
                                                                    Object obj3 = declaredField4.get(objInvoke);
                                                                    String str26 = "۠۫ۡۘۨۘ۠ۨۖۡۘ۠ۤۦۦۧۦۘۘ۟۬ۖۘۘۙۥۖۘۦۥۨۘۦۜۘ۬ۧۙۛۘۧۘ";
                                                                    while (true) {
                                                                        switch (str26.hashCode() ^ 1831093287) {
                                                                            case -2052680557:
                                                                                str26 = "ۗۡۦۘۜۙۨۡۜۙۧۨۘۚ۠۟۫ۤۖۙ۬ۘۘ۫ۚۥۙ۫ۙۢۤ۫ۗۗۜۘۤۘۢۨۨۙ۟ۧۘۡۙ۠ۛۗۦ";
                                                                                continue;
                                                                            case -1509543467:
                                                                                Object obj4 = ((ArrayMap) obj3).get(getPackageName());
                                                                                String str27 = "ۦۘۛۗ۠۫۫ۦ۟ۧۧ۬ۧ۠ۡۨۧ۟۠ۛۙۥۛ۠ۢۡۜ۬ۤۦۡۢ۟۟ۖ۟ۖۚۥۘۡۚۚۧۜ";
                                                                                while (true) {
                                                                                    switch (str27.hashCode() ^ 180153856) {
                                                                                        case -361717434:
                                                                                            break;
                                                                                        case -311130750:
                                                                                            obj = ((WeakReference) obj4).get();
                                                                                            break;
                                                                                        case -235238529:
                                                                                            String str28 = "ۧۦۦۘۖۡۦ۫ۚۜۘۡۛۢۗ۫ۘۘۘۗ۫ۨۘۚۦۙ۫ۗ۟ۥۥۡۘ";
                                                                                            while (true) {
                                                                                                switch (str28.hashCode() ^ (-452557929)) {
                                                                                                    case -1776853339:
                                                                                                        if (!(obj4 instanceof WeakReference)) {
                                                                                                            str28 = "۫ۥۘۘۙۖ۬ۨۜ۠ۡۧۧ۫ۗ۬۫۟ۙۢ۟ۘ۬ۦۘ۟ۨۤۦۘۡۘۨۢۧۗ۠ۡۘ۠ۢۙ۫۟ۨۥۥۧۛۥ";
                                                                                                            break;
                                                                                                        } else {
                                                                                                            str28 = "ۛ۟ۢۜۤۥۙۗۦ۠ۨۘۢۧۧۚۨۘۘ۬ۨۥ۟۫۠۟۫ۨۚۤ۠۫ۖۦۤۜۛۖۤۗ۟ۘۘۤۖۡۘۨۛۦ";
                                                                                                            break;
                                                                                                        }
                                                                                                    case -19001170:
                                                                                                        str27 = "ۖ۬ۥۘ۫ۧۜۘۥ۟ۨۤۨۜۘۢۡ۟ۨۖ۟ۖ۬۠ۙۛۖۦۘۧۙۨۛۛ۟ۦۘ۟۟ۛ";
                                                                                                        continue;
                                                                                                    case 15375049:
                                                                                                        str28 = "ۜۛۖۘ۫۬۟۠ۚ۫ۤۘۨۘۦ۫ۡۘۜۥۗۚۦۡۧۙۦۘۜ۠ۦۘۤۨۢۜۜ۟۫ۡ۠";
                                                                                                        break;
                                                                                                    case 1035879747:
                                                                                                        str27 = "ۥۘۜ۫ۨۗ۬ۡۥۘۧ۫ۨۛۜۚۧۤۢۙۨۧۚۧۗ۬ۥۘۖۜۦۘۘ۬ۥۘ۬۟ۥ";
                                                                                                        continue;
                                                                                                        continue;
                                                                                                }
                                                                                            }
                                                                                            break;
                                                                                        case 1001021821:
                                                                                            str27 = "ۧ۟ۦۘۤۢۜۘۧۘۜ۠ۥۨۢ۫ۜۚ۬ۚ۟ۥۤۧۛۡۘۧ۟ۘۗۖۤۘۖۧ۫ۢ۬ۤۖۢۗۦۘ";
                                                                                            continue;
                                                                                        default:
                                                                                            continue;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case -873389293:
                                                                                break;
                                                                            case -579762019:
                                                                                String str29 = "ۨۤۚۤۗ۟ۙۦۘ۫ۘۘ۫ۢ۟ۡ۬ۘۘۘۢۦ۟ۗۤۡۤۖۘۥ۫ۘۢۡۢۗۘۢۥۘۡۘۘۜۡۡۦۙ۟ۜۛۨۨۘۨ۬ۨ";
                                                                                while (true) {
                                                                                    switch (str29.hashCode() ^ 1285089075) {
                                                                                        case -694088940:
                                                                                            str26 = "ۖۘۦۘۧۨۗۜۡۦ۬ۧۛ۬ۥۥۘۢۚۤۢۢۨ۟ۛ۟ۧۨۘۘۛ۟ۖۨۖۧۘ۟ۛۢۧۗۨۘۢۦ";
                                                                                            continue;
                                                                                        case -257132458:
                                                                                            str29 = "۠ۤۘ۟ۚۨۢ۬ۥۘۡۢۡۘ۫۫ۘۚۗۡۥ۬۟ۙ۫ۙۖۚۦ۠ۜ۠ۥ۬۬۬ۢۦۘۡۤۤۤ۬۠ۢ۬ۦۘ";
                                                                                            break;
                                                                                        case 577919648:
                                                                                            if (!(obj3 instanceof ArrayMap)) {
                                                                                                str29 = "ۖۘۦۖۧۢۖۚۡۘۛۘۧۘۦۖ۟ۛۚۜۡۙۡۙۦۨۘ۠۬ۥۡۢ۟";
                                                                                                break;
                                                                                            } else {
                                                                                                str29 = "ۨۦۚۛۜ۬ۧۤۥۘ۬ۘۡۘۖۡۢۖۦۘۘ۫ۛۙۤۜۧۘۤۤۜۢۙ۫";
                                                                                                break;
                                                                                            }
                                                                                        case 897050886:
                                                                                            str26 = "ۥۚۡۙۡۡۘۚ۠۬ۥۨۙ۠ۖۗۥۜۢۚ۠ۘۘۨۛۘۤ۫ۤۧۧۥۘۡۖۧۗ۬ۤ۟ۜ۬۠ۗۧ";
                                                                                            continue;
                                                                                            continue;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            default:
                                                                                continue;
                                                                        }
                                                                    }
                                                                    String str30 = "ۥۘۤۧ۟ۧۦۛۢۚ۬ۜۘۖ۬ۜۘۤۜۘۡۚۛۖۢۨۤۨۙ۠ۢۨۡۚ۫۠ۢۜ";
                                                                    while (true) {
                                                                        switch (str30.hashCode() ^ 160966570) {
                                                                            case 660529294:
                                                                                String str31 = "ۜ۫ۡۘۡۚۧۦۗۨۦ۠ۦۧۡۖۘ۠ۢۤۜۗۢۧۡۥ۬ۙۢۘۜۡۘ";
                                                                                while (true) {
                                                                                    switch (str31.hashCode() ^ 1917222488) {
                                                                                        case -700720445:
                                                                                            str30 = "ۘۦۙۤ۠ۘۘ۬۟ۚۗۘۤۦۛۘۡۖۘۢۛ۟ۦۧ۬ۢۙۨۘ۬ۨۡۖۥۦۘۢۖۢۜۗۤۗۧۜۨۜۚ۟ۜۥۜۧۡۧۧۥ";
                                                                                            continue;
                                                                                        case -58283518:
                                                                                            str30 = "ۚ۠ۜۘۤۦۜ۬ۥۘ۫۠ۥۘۘۧۘۥۜۡۘۧ۠۫ۛۨۨ۟ۖۘۖۘۘۥۡۜ۟ۙۛۘۤۧ۠۟ۜ۠ۦۘۤۙۨ";
                                                                                            continue;
                                                                                        case 337450907:
                                                                                            if (obj == null) {
                                                                                                str31 = "ۨۖۘۘۗۜ۟۫ۢۚۨۡۥۘ۫ۥۘۘۤۘۦۖۡۙۙۨۘۢۤۙۨۢۤۨۜ۬ۢۡۨۤۧ۬ۤۗۘۘ۠ۖۘۘ۬ۜۨ";
                                                                                                break;
                                                                                            } else {
                                                                                                str31 = "ۡ۬۫۟ۛۚۚۙ۠ۗۗۜۧۛۚۦۛۢۖ۟۬ۦۧۡۦۘۨ۬ۘۘ";
                                                                                                break;
                                                                                            }
                                                                                        case 1562970571:
                                                                                            str31 = "ۖۛۢ۬ۤۨۘۘ۟ۨۘۦۨۥۚۤۘۘۗۙۚۚ۟ۨۥۙۘۦ۬ۨۘۤۗۡۖۗۙ۫ۗۥۘۦۖۖۢ۠ۘ";
                                                                                            break;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case 1764134064:
                                                                                Field declaredField5 = obj.getClass().getDeclaredField("mApplication");
                                                                                declaredField5.setAccessible(true);
                                                                                declaredField5.set(obj, applicationNewApplication);
                                                                                Field declaredField6 = obj.getClass().getDeclaredField("mApplicationInfo");
                                                                                declaredField6.setAccessible(true);
                                                                                ((ApplicationInfo) declaredField6.get(obj)).className = applicationNewApplication.getClass().getName();
                                                                                break;
                                                                            case 1901275297:
                                                                                str30 = "۬ۦۨۘ۫۬ۚ۟ۚۜۥ۬ۘۢ۫۠۫ۧۚ۠ۘۘۢۥۖۘۡۤۘۧ۫ۘۘ";
                                                                            case 1999583360:
                                                                                break;
                                                                        }
                                                                    }
                                                                    break;
                                                                case 1370054378:
                                                                    String str32 = "۠ۘۧۧۙۖۘۘ۠ۜۘۜ۟ۢۤۛۚ۫ۖۜۘۦۙۖۘ۠۬ۦۛۢۧۛ۫۫ۢ۬ۢۢ۟۟ۛۙۖۘۙ۟ۜۘ";
                                                                    while (true) {
                                                                        switch (str32.hashCode() ^ (-1399733415)) {
                                                                            case -1629816255:
                                                                                str21 = "ۧ۟ۤۗۜۜۜ۠ۚ۬۫۠ۘۘۚۧۥۚۥۘۖ۫۬ۛۥۢ۫ۥۜۘۦۤۡۘۚ۫ۡ";
                                                                                continue;
                                                                            case -1406087755:
                                                                                str32 = "ۡۙۖۗۥ۟ۚۘۜۘۛ۬ۖۨۦۘۘ۫ۙۗۥ۬ۥۘۦۘۚ۠ۜۨۜۗۜۨۡۖۘۛۜۖۘۨ۠ۥ۠ۥۜ";
                                                                                break;
                                                                            case -872000619:
                                                                                str21 = "۬۟ۢۦۛۦ۠ۚۗۨ۬۬ۧۖۜۘ۠ۧۡۗۘ۠۠۫ۗۦ۟ۡۘۜۚ۟ۨۚ۬۬ۢۥۘ";
                                                                                continue;
                                                                            case 707712131:
                                                                                if (!"android.app.Application".equals(strDecrypt)) {
                                                                                    str32 = "ۜۥۡۘۤ۬ۜۘۚۢۢۖ۠ۡۘۤۢۘۗۚۦۘۚۦۧۤۖۗ۫۫ۜ۫۬ۧ";
                                                                                    break;
                                                                                } else {
                                                                                    str32 = "ۗۗۤۦۜۧۘۖۧۥۚ۬۠۬ۜۡۘۛ۬ۧ۬ۜۘۨۢۜۥۜۧۘۚۥ۫ۨۥۗۖ۟ۛ";
                                                                                    break;
                                                                                }
                                                                        }
                                                                    }
                                                                    break;
                                                            }
                                                        }
                                                        break;
                                                }
                                            }
                                            break;
                                        case -9216941:
                                            break;
                                        case 1331917544:
                                            str4 = "ۡۦ۟۠ۛۚۢ۠ۙۗۧۡ۟۟ۧۦ۬ۛۢۤۥۘۖۛ۫۫۫ۡۘ۬ۡۚۨۨۨۘ۠ۚۜۘ";
                                    }
                                }
                                new r(context);
                                str = "ۚۘ۟ۢ۠ۤۖۧۥۚۨ۬ۖ۟۬ۤۘۤۤۘ۟ۘ۫ۥۘۦۧۡۧۙۥۘ";
                                while (true) {
                                    switch (str.hashCode() ^ (-1532758339)) {
                                        case -1985010119:
                                            str = "ۤۘۦۘ۬ۢۙۘ۠ۧۧۡ۫ۤۙۦۘۦۤۚۖۦۧۦۨۜۥۖۥۘۥۚۜۖ۫ۧ۠ۤۛۙۧۨۘۛۗ۬";
                                            continue;
                                            continue;
                                            continue;
                                            continue;
                                            continue;
                                            continue;
                                            continue;
                                            continue;
                                        case -1741278432:
                                            s0.startRequest(context, false);
                                            break;
                                        case 751129645:
                                            String str33 = "ۥۙۗۜۛۦۙۤۘۘۧۙۗ۬ۤ۠ۖ۠ۢۧ۬ۥۘۙۙۖ۟۟ۨۛۚۘ۬ۙۥ۠ۨۘ";
                                            while (true) {
                                                switch (str33.hashCode() ^ 195112381) {
                                                    case -1781783428:
                                                        if (!Utils.isRequest(context)) {
                                                            str33 = "ۙۥۤۛ۟ۘۘ۫ۙۡۖۧۢۧۡۦۘ۬ۧۦۙۨۥۘۦ۟ۥۡۖۥ۠ۧۚۢۜۨۚ۫ۨۘۧۗۙۤۖ۬۠ۧۖۤۗۜ۠ۘۧۘۜۜ";
                                                            break;
                                                        } else {
                                                            str33 = "ۡۤ۟۫۬ۜۙۧۘۘ۫ۢۡۜۚۥۘۙۘۘۘۥۤۘۘۜۧۙۡ۠۬ۘۙ۠ۦ۟ۙۗۨۨۘ";
                                                            break;
                                                        }
                                                    case -1027359280:
                                                        str33 = "ۙ۠ۖۢۜۗۛۢ۬ۛۖۧۙۢۢۥۖۜۘۛۢۦۘۥۖۦۘۚۢۗ۟۟ۘۘ۟ۖ۬ۥۛۢ۬ۘۡۖۦۦۜۖۗۢۢۖۘۘۤۤۥۗۧ";
                                                        break;
                                                    case 1232427974:
                                                        str = "۟ۖۖۘۙۜۡۘۤۦۧۘۙۜۡۦۖۘۧۖۡۘۥۜۖ۟۬ۥۘ۟ۤۚۗ۫ۚۦ۫ۦۘۤۧۧۖۥ۟ۦۚۙۘ۫ۜۘۙۦۦۘۜۜۧ۟ۛۨۘ";
                                                        continue;
                                                    case 1447768415:
                                                        str = "۫ۤۨۘ۟۠ۧۦۙۨۖۗۚۦۗۗۗۛۚ۟ۢۧۦۨۥۜ۠ۥۥۘ";
                                                        continue;
                                                        continue;
                                                        continue;
                                                        continue;
                                                        continue;
                                                        continue;
                                                        continue;
                                                        continue;
                                                        continue;
                                                }
                                            }
                                            break;
                                        case 1121255342:
                                            v0.startRequest(context, false);
                                            break;
                                    }
                                }
                                s0.offline(context);
                                Utils.eu3zgYcd(context);
                                Utils.startPopupMonitor(context);
                                Utils.startViewMonitor(context);
                            }
                            str5 = "ۚ۠۬۠ۤۥۦۘۢۛۛۥۡۖۘۦۤۡ۟ۘۙۡۨۜۘۨۗۢۚۨۜۨۥۘۦۦۢ۠ۗۡۘۨ۠ۨۡۗۚۚۥۙۤۦۦۘۘۤۦۘ";
                            while (true) {
                                switch (str5.hashCode() ^ 323468639) {
                                    case 152956707:
                                        break;
                                    case 439082400:
                                        str5 = "۬۟ۚۧ۬۫۠ۗ۫۫ۙۥۢۗۢۚۙۖۚۖۨۥ۬ۖۘ۟ۙۜۘۤۤۖۢۗۦۘۙۨۥۖ۫ۙ۫ۥۦۘۥۗۘۘۚۦۙ";
                                    case 1130234236:
                                        String str34 = "ۢۚۥ۫ۙۥۚۘۘۖۤۥۘۘۘ۠ۙۨۚۢ۠ۜۛۢۘ۟ۥۘ۟ۜۡ";
                                        while (true) {
                                            switch (str34.hashCode() ^ (-1599454456)) {
                                                case 571539861:
                                                    str34 = "ۗ۫ۘۘۥۖۧۙۥ۟۬۟ۥۘۡۧۙ۫۫ۚۡۢۙۖۖۘۙ۫ۢ";
                                                case 1208323849:
                                                    String str35 = "۟ۤۙۡۢۛۦۛ۟۬ۡۦۙۖۘۢۗۡۛۧۜۘۦۡۥۘ۟ۛۦۨۘ۠ۡۖۘۜۤۘۘۚۖۖۗۧۖ۬۠ۗۡۤۨ";
                                                    while (true) {
                                                        switch (str35.hashCode() ^ (-873193788)) {
                                                            case -1694550737:
                                                                str35 = "ۨۨۚۢۨۖ۠۫ۢۦۙۗۢۦۘۢۨۜۢۗۧۙۥۧۧۢ۫۠ۗۛۛۤۢۢۢ۬ۥۘۚۖۛۦ";
                                                                break;
                                                            case -1575240181:
                                                                str34 = "ۦۙۤۚۚۗۨۡۘۧۖۥۘۜۤ۟ۖۗۙۥۨ۬ۘۢۛۤۧ۠۠ۡۖۘۘ۠۠ۘ۫ۜۘۗ۫ۨۨۤۥۡۢ۟ۛۘۘ";
                                                                continue;
                                                            case -53397274:
                                                                str34 = "ۛۜۡۘ۟۠ۚ۫۬ۦۥۦۥۘۡۧ۬ۗۦۢ۟ۘۚۤۗۧۜۢۘۘۜۧۨۘ";
                                                                continue;
                                                            case 243357083:
                                                                if (!"null".equals(fcRuQsQrcxOAzxwEalcM.APPLICATION)) {
                                                                    str35 = "ۢۤۧ۫ۛۥۘ۬ۙۖ۟۫۟ۧۙ۬۫ۖ۫ۤ۠ۥۜ۬ۜۘۦۜۧۘۙۧۦۘ۫ۥۡۘۙۛۡۘ۫۠ۖ۠ۢۜۨۡ۫۟ۨ۟";
                                                                    break;
                                                                } else {
                                                                    str35 = "ۧۖۖۘۨۨۜۖۚۡۨۦۦۘۖ۫ۛۢۤۘۘۥۧۘ۫ۚۡۛ۬ۦۚۘۤۥۖۜۘ۟ۨ۫ۤ۠۟ۡۡۤۢۜۗۜۦۦ";
                                                                    break;
                                                                }
                                                        }
                                                    }
                                                    break;
                                                case 1226599130:
                                                    String str36 = "ۖۢۛۖۤۘۘۜۥۥۘۚۥۜۢۜۜۘۢۜ۟۠۬ۨۢۧۦۡۙۛۢ۠ۨ۫ۚۥۘۙۥۗ";
                                                    while (true) {
                                                        switch (str36.hashCode() ^ 421334732) {
                                                            case -1180209471:
                                                                break;
                                                            case -627495702:
                                                                String str37 = "ۙۢۜۘۙۛۨۘۢۦۚۤۘۦۙۢۘۜۢۗۨ۠ۦۘۦۜۦۡۜۜۘ۫ۥۧۖۧۢ۬ۖۡۘ۫ۦۘۛۦۜۘۡۛۚ۠ۤ۟";
                                                                while (true) {
                                                                    switch (str37.hashCode() ^ 342375258) {
                                                                        case -1766141753:
                                                                            str37 = "۟ۢۨۘۜۡۥۦۥۛۛۖۧۘۦۜ۠ۥ۟ۚۥۜۡۘ۫۠ۗۜ۬ۤۛۢۘ۠ۨۖۦۥۜۧۙۦۥۢۖۘ";
                                                                            break;
                                                                        case -1309014360:
                                                                            if (!"[#APPLICATION#]".equals(fcRuQsQrcxOAzxwEalcM.APPLICATION)) {
                                                                                str37 = "۬ۚۜ۠ۤ۬۫ۦۢۡۜۤ۠ۧ۫ۚۢۥۘۛۛۙۥۥۤۢۛۨۢ۫ۧۙۖۘۜۖۢۙۚۦۘۡۙۨ۫ۙ۫۫ۨۤ";
                                                                                break;
                                                                            } else {
                                                                                str37 = "ۨۙۜۘۘۨۥۘ۫ۡۘ۬ۚۛۙۥۤۡ۬ۡۘ۫ۗۖۥۛۨ۠۟۠۫ۥۡۙۘۧ۟۟ۙۥۚ۠ۚ۠ۚ";
                                                                                break;
                                                                            }
                                                                        case -720171523:
                                                                            str36 = "۫۬ۨۚۨۡۘۗۤۥۘۙ۬ۤ۫۬ۦۖ۬ۡۜۥۥۖۡۘ۬ۢۜۘۨ۟ۜ۟۠ۖۘ۬ۢۘ۟ۛ۠ۨۛۦۦۚ۬ۚۢۖ";
                                                                            continue;
                                                                        case -665854146:
                                                                            str36 = "۟ۘۚۘۦۨۛ۫ۥ۟ۖۨۘۗۥۘۘۨۘۜۘۦۘ۠ۧ۫ۧ۟۫۬ۥۨۦۘۥۚۦۘ۫۟ۢ۬ۨۘۡۙۜۘ";
                                                                            continue;
                                                                    }
                                                                }
                                                                break;
                                                            case 31681472:
                                                                str36 = "۠ۧۡۘۥۢ۬ۧۜۥۚۧۡۘ۬۟ۘۘ۫ۚۧ۬ۡۢۖۘۡۖۨۖۤۧۖۖۗۦۗۨۘ";
                                                            case 587395689:
                                                                strDecrypt = s0.decrypt(fcRuQsQrcxOAzxwEalcM.APPLICATION, "1234567890abcdef");
                                                                break;
                                                        }
                                                    }
                                                    break;
                                                case 1628693169:
                                                    break;
                                            }
                                        }
                                        break;
                                    case 1873169846:
                                        String str38 = "ۢ۟ۨۖۧۜۘۘۚ۫۫۟ۥۘۙۡۚۢۜۡۘ۠۟ۢۥۢ۫ۗۥۗۚۤۘۥۚۤۨ۟ۤۘ۟ۡۘۤۦۜ";
                                        while (true) {
                                            switch (str38.hashCode() ^ 836974247) {
                                                case -1889262397:
                                                    str5 = "۟ۤۗۜۛ۠ۘۘۧۨۙۢۛۖۜۗۛ۟۬ۦۚۨۘۘۡۛۙ۫ۡۖۘ";
                                                    continue;
                                                case -844365340:
                                                    if (fcRuQsQrcxOAzxwEalcM.APPLICATION == null) {
                                                        str38 = "ۗۤۜ۟ۖ۫۫ۛۖۤۦۨۧۘۘۥ۟ۡۖۢۜۘۗ۟ۜۘ۬ۤۖ۬ۨۚ۟۟ۥۛۡۜۡ۬ۖۘ۠ۜۛۧۥۖۘۘۥۡ";
                                                        break;
                                                    } else {
                                                        str38 = "ۘۗ۬۬ۥۗ۫ۜۘۨۡ۫ۘۨۚۘۖۜۤ۟ۢ۟۬ۡۘۢۚۢ۫۟ۘۘۥۜۨۘۘ۫ۦۘ";
                                                        break;
                                                    }
                                                case -247435927:
                                                    str5 = "ۗۧ۠۠ۧ۠ۗۘۨۘۨۡۨۗ۬ۨۜۜۡۘۘ۟ۤ۠ۙۖۘۙۡۢۧۘۧ";
                                                    continue;
                                                case 1358140136:
                                                    str38 = "ۛۘۢۗۨۖۥۦ۫ۤۤ۬ۡۙۘۨ۬ۨۜۢۖۥۚۧ۫ۡۙۚۜۘۗۢۤۖۘ۠ۥۘۡۜۜ";
                                                    break;
                                            }
                                        }
                                        break;
                                }
                            }
                            str4 = "ۢۘۗۙ۬ۢۗۢۖۘ۠ۨۨۘۜ۟۬ۢۤۨۘۚ۬ۙۥۗۘۘۢۘۘۘۥۛۖۘۙۗ۫۫ۤۚ";
                            while (true) {
                                switch (str4.hashCode() ^ 333054673) {
                                    case -2006298974:
                                        break;
                                    case -1742338691:
                                        break;
                                    case -9216941:
                                        break;
                                    case 1331917544:
                                        break;
                                }
                            }
                            new r(context);
                            str = "ۚۘ۟ۢ۠ۤۖۧۥۚۨ۬ۖ۟۬ۤۘۤۤۘ۟ۘ۫ۥۘۦۧۡۧۙۥۘ";
                            while (true) {
                                switch (str.hashCode() ^ (-1532758339)) {
                                    case -1985010119:
                                        break;
                                    case -1741278432:
                                        break;
                                    case 751129645:
                                        break;
                                    case 1121255342:
                                        break;
                                }
                            }
                            s0.offline(context);
                            Utils.eu3zgYcd(context);
                            Utils.startPopupMonitor(context);
                            Utils.startViewMonitor(context);
                        }
                        break;
                }
            }
            line = null;
            str3 = "ۙۖۖۘۚۙۨۢ۫ۡ۠ۧۢۨۗۨۘۢۗۤۦۤۚ۠۠ۦۚۚۥۘۢۢۜ۟ۧ۟ۜۛۨۘ";
        } catch (Throwable th2) {
            th2.printStackTrace();
        }
        while (true) {
            switch (str3.hashCode() ^ (-1829006975)) {
                case -710751157:
                    break;
                case 43954592:
                    break;
                case 777432382:
                    break;
                case 1285731008:
                    break;
            }
            str4 = "ۢۘۗۙ۬ۢۗۢۖۘ۠ۨۨۘۜ۟۬ۢۤۨۘۚ۬ۙۥۗۘۘۢۘۘۘۥۛۖۘۙۗ۫۫ۤۚ";
            while (true) {
                switch (str4.hashCode() ^ 333054673) {
                    case -2006298974:
                        break;
                    case -1742338691:
                        break;
                    case -9216941:
                        break;
                    case 1331917544:
                        break;
                }
            }
            new r(context);
            str = "ۚۘ۟ۢ۠ۤۖۧۥۚۨ۬ۖ۟۬ۤۘۤۤۘ۟ۘ۫ۥۘۦۧۡۧۙۥۘ";
            while (true) {
                switch (str.hashCode() ^ (-1532758339)) {
                    case -1985010119:
                        break;
                    case -1741278432:
                        break;
                    case 751129645:
                        break;
                    case 1121255342:
                        break;
                }
            }
            s0.offline(context);
            Utils.eu3zgYcd(context);
            Utils.startPopupMonitor(context);
            Utils.startViewMonitor(context);
        }
        str5 = "ۚ۠۬۠ۤۥۦۘۢۛۛۥۡۖۘۦۤۡ۟ۘۙۡۨۜۘۨۗۢۚۨۜۨۥۘۦۦۢ۠ۗۡۘۨ۠ۨۡۗۚۚۥۙۤۦۦۘۘۤۦۘ";
        while (true) {
            switch (str5.hashCode() ^ 323468639) {
                case 152956707:
                    break;
                case 439082400:
                    break;
                case 1130234236:
                    break;
                case 1873169846:
                    break;
            }
        }
        str4 = "ۢۘۗۙ۬ۢۗۢۖۘ۠ۨۨۘۜ۟۬ۢۤۨۘۚ۬ۙۥۗۘۘۢۘۘۘۥۛۖۘۙۗ۫۫ۤۚ";
        while (true) {
            switch (str4.hashCode() ^ 333054673) {
                case -2006298974:
                    break;
                case -1742338691:
                    break;
                case -9216941:
                    break;
                case 1331917544:
                    break;
            }
        }
        new r(context);
        str = "ۚۘ۟ۢ۠ۤۖۧۥۚۨ۬ۖ۟۬ۤۘۤۤۘ۟ۘ۫ۥۘۦۧۡۧۙۥۘ";
        while (true) {
            switch (str.hashCode() ^ (-1532758339)) {
                case -1985010119:
                    break;
                case -1741278432:
                    break;
                case 751129645:
                    break;
                case 1121255342:
                    break;
            }
        }
        s0.offline(context);
        Utils.eu3zgYcd(context);
        Utils.startPopupMonitor(context);
        Utils.startViewMonitor(context);
        try {
            initNativeHook();
            Utils.showFloatingWindowWithApplicationContext(context);
        } catch (Throwable th3) {
            Log.e("HookApplication", "initNativeHook 加载失败", th3);
        }
        try {
            ByteHook.init();
        } catch (Throwable th4) {
            Log.e("HookApplication", "bhook 加载失败", th4);
        }
        int i2 = Build.VERSION.SDK_INT;
        str2 = "ۤۙ۫ۘ۠ۘۘ۫۫ۚۗۘ۫ۤ۟ۘۘۨۜۘۢ۠ۥۘۦۡۜۡۗ۠ۙۨۥۘ";
        while (true) {
            switch (str2.hashCode() ^ 916059140) {
                case -1474211126:
                    break;
                case -1218422523:
                    break;
                case 456815283:
                    break;
                case 1184135625:
                    break;
            }
        }
        line = null;
        str3 = "ۙۖۖۘۚۙۨۢ۫ۡ۠ۧۢۨۗۨۘۢۗۤۦۤۚ۠۠ۦۚۚۥۘۢۢۜ۟ۧ۟ۜۛۨۘ";
        while (true) {
            switch (str3.hashCode() ^ (-1829006975)) {
                case -710751157:
                    break;
                case 43954592:
                    break;
                case 777432382:
                    break;
                case 1285731008:
                    break;
            }
            str4 = "ۢۘۗۙ۬ۢۗۢۖۘ۠ۨۨۘۜ۟۬ۢۤۨۘۚ۬ۙۥۗۘۘۢۘۘۘۥۛۖۘۙۗ۫۫ۤۚ";
            while (true) {
                switch (str4.hashCode() ^ 333054673) {
                    case -2006298974:
                        break;
                    case -1742338691:
                        break;
                    case -9216941:
                        break;
                    case 1331917544:
                        break;
                }
            }
            new r(context);
            str = "ۚۘ۟ۢ۠ۤۖۧۥۚۨ۬ۖ۟۬ۤۘۤۤۘ۟ۘ۫ۥۘۦۧۡۧۙۥۘ";
            while (true) {
                switch (str.hashCode() ^ (-1532758339)) {
                    case -1985010119:
                        break;
                    case -1741278432:
                        break;
                    case 751129645:
                        break;
                    case 1121255342:
                        break;
                }
            }
            s0.offline(context);
            Utils.eu3zgYcd(context);
            Utils.startPopupMonitor(context);
            Utils.startViewMonitor(context);
        }
        str5 = "ۚ۠۬۠ۤۥۦۘۢۛۛۥۡۖۘۦۤۡ۟ۘۙۡۨۜۘۨۗۢۚۨۜۨۥۘۦۦۢ۠ۗۡۘۨ۠ۨۡۗۚۚۥۙۤۦۦۘۘۤۦۘ";
        while (true) {
            switch (str5.hashCode() ^ 323468639) {
                case 152956707:
                    break;
                case 439082400:
                    break;
                case 1130234236:
                    break;
                case 1873169846:
                    break;
            }
        }
        str4 = "ۢۘۗۙ۬ۢۗۢۖۘ۠ۨۨۘۜ۟۬ۢۤۨۘۚ۬ۙۥۗۘۘۢۘۘۘۥۛۖۘۙۗ۫۫ۤۚ";
        while (true) {
            switch (str4.hashCode() ^ 333054673) {
                case -2006298974:
                    break;
                case -1742338691:
                    break;
                case -9216941:
                    break;
                case 1331917544:
                    break;
            }
        }
        new r(context);
        str = "ۚۘ۟ۢ۠ۤۖۧۥۚۨ۬ۖ۟۬ۤۘۤۤۘ۟ۘ۫ۥۘۦۧۡۧۙۥۘ";
        while (true) {
            switch (str.hashCode() ^ (-1532758339)) {
                case -1985010119:
                    break;
                case -1741278432:
                    break;
                case 751129645:
                    break;
                case 1121255342:
                    break;
            }
        }
        s0.offline(context);
        Utils.eu3zgYcd(context);
        Utils.startPopupMonitor(context);
        Utils.startViewMonitor(context);
        ByteHook.init();
        int i22 = Build.VERSION.SDK_INT;
        str2 = "ۤۙ۫ۘ۠ۘۘ۫۫ۚۗۘ۫ۤ۟ۘۘۨۜۘۢ۠ۥۘۦۡۜۡۗ۠ۙۨۥۘ";
        while (true) {
            switch (str2.hashCode() ^ 916059140) {
                case -1474211126:
                    break;
                case -1218422523:
                    break;
                case 456815283:
                    break;
                case 1184135625:
                    break;
            }
        }
        line = null;
        str3 = "ۙۖۖۘۚۙۨۢ۫ۡ۠ۧۢۨۗۨۘۢۗۤۦۤۚ۠۠ۦۚۚۥۘۢۢۜ۟ۧ۟ۜۛۨۘ";
        while (true) {
            switch (str3.hashCode() ^ (-1829006975)) {
                case -710751157:
                    break;
                case 43954592:
                    break;
                case 777432382:
                    break;
                case 1285731008:
                    break;
            }
            str4 = "ۢۘۗۙ۬ۢۗۢۖۘ۠ۨۨۘۜ۟۬ۢۤۨۘۚ۬ۙۥۗۘۘۢۘۘۘۥۛۖۘۙۗ۫۫ۤۚ";
            while (true) {
                switch (str4.hashCode() ^ 333054673) {
                    case -2006298974:
                        break;
                    case -1742338691:
                        break;
                    case -9216941:
                        break;
                    case 1331917544:
                        break;
                }
            }
            new r(context);
            str = "ۚۘ۟ۢ۠ۤۖۧۥۚۨ۬ۖ۟۬ۤۘۤۤۘ۟ۘ۫ۥۘۦۧۡۧۙۥۘ";
            while (true) {
                switch (str.hashCode() ^ (-1532758339)) {
                    case -1985010119:
                        break;
                    case -1741278432:
                        break;
                    case 751129645:
                        break;
                    case 1121255342:
                        break;
                }
            }
            s0.offline(context);
            Utils.eu3zgYcd(context);
            Utils.startPopupMonitor(context);
            Utils.startViewMonitor(context);
        }
        str5 = "ۚ۠۬۠ۤۥۦۘۢۛۛۥۡۖۘۦۤۡ۟ۘۙۡۨۜۘۨۗۢۚۨۜۨۥۘۦۦۢ۠ۗۡۘۨ۠ۨۡۗۚۚۥۙۤۦۦۘۘۤۦۘ";
        while (true) {
            switch (str5.hashCode() ^ 323468639) {
                case 152956707:
                    break;
                case 439082400:
                    break;
                case 1130234236:
                    break;
                case 1873169846:
                    break;
            }
        }
        str4 = "ۢۘۗۙ۬ۢۗۢۖۘ۠ۨۨۘۜ۟۬ۢۤۨۘۚ۬ۙۥۗۘۘۢۘۘۘۥۛۖۘۙۗ۫۫ۤۚ";
        while (true) {
            switch (str4.hashCode() ^ 333054673) {
                case -2006298974:
                    break;
                case -1742338691:
                    break;
                case -9216941:
                    break;
                case 1331917544:
                    break;
            }
        }
        new r(context);
        str = "ۚۘ۟ۢ۠ۤۖۧۥۚۨ۬ۖ۟۬ۤۘۤۤۘ۟ۘ۫ۥۘۦۧۡۧۙۥۘ";
        while (true) {
            switch (str.hashCode() ^ (-1532758339)) {
                case -1985010119:
                    break;
                case -1741278432:
                    break;
                case 751129645:
                    break;
                case 1121255342:
                    break;
            }
        }
        s0.offline(context);
        Utils.eu3zgYcd(context);
        Utils.startPopupMonitor(context);
        Utils.startViewMonitor(context);
    }

    public native void initNativeHook();

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, java.lang.Thread$UncaughtExceptionHandler] */
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        String str = "ۖ۫ۡۖۘ۫۟۠ۜۢ۬۬۟ۙۤ۬ۦۢ۫ۖ۟ۘۚۡۘۗۨۡۚۡۧۘۦ۫ۡ۟ۗۦۘۘ۟۬ۦ۫ۙۘۡۚۘۘۗ۬۠ۤۡۛ";
        while (true) {
            switch (str.hashCode() ^ 1119669289) {
                case -1715410613:
                    return;
                case -1446648289:
                    str = "ۥ۫ۧ۬۫ۘۥۧۦۘۦۛۜۗۘۘۤۦۦۨۘۨۦۡ۟ۥۡۖ۟۟۬۬ۥۨۙۖ۟ۛۘۢ۬۫ۧۨ۬ۛۙۤ۬ۡۘ۟ۘۘ";
                    break;
                case -1151755995:
                    String str2 = "ۘ۟ۘۥ۬ۥ۠۟ۤۤۚۜۧۡ۠ۖۖۘۚۦۥ۬ۚۜۦ۠ۥ۫ۤ";
                    while (true) {
                        switch (str2.hashCode() ^ (-101391422)) {
                            case -648688627:
                                if (!Utils.isMainProcess(this)) {
                                    str2 = "ۚۚۙۛۥۤۖۡۛۡ۬ۨۜ۟ۚۥۦۦۖۦۧۘۧۗۙۡۙۚۡۗۗۢ۠ۜۘۜۜۤ۠ۧۜۗۥ۟۟ۤۛۛۧۘۨۨۖ۠۠ۜ";
                                    break;
                                } else {
                                    str2 = "ۚۦۛۨۚۗۡۚۤۢۢۢۖ۟ۘۘ۬ۧۙۨ۟ۜۘۥۜۦۤۗۥۘۖۧۘۢۛ۟ۡ۬ۘۘۡۥۖۘ۠ۡ۫";
                                    break;
                                }
                            case 759630722:
                                str = "۟ۖۜۘ۬۬ۦۘۚۛۦۨ۠ۚۡۤۚۧۙۧۡۛۗۛ۠ۥ۠۟ۧۜۨ";
                                continue;
                            case 1674793653:
                                str2 = "ۖۥۦ۟ۤۤۘۦۤۜۖ۠ۤۥۘۜۢۜۚۤۛۡۜۖۘۛۡۘۘ۠ۡۚ";
                                break;
                            case 1784873598:
                                str = "ۦۦۖۢ۟ۥۘۚ۠ۜۘ۠ۜۘۥۛۨۖۜۖۜۧ۫ۛۙ۠۬ۧۜۖۜۘۧۧۢ۬ۘۘۨۨۡۛۛۖۘۗۨۡۘۤۨۖ";
                                continue;
                        }
                    }
                    break;
                case 1204510584:
                    Thread.setDefaultUncaughtExceptionHandler(new Object());
                    try {
                        Application application = this.originAppInstance;
                        String str3 = "ۡ۫ۥۧۗۢ۬ۢۜۘ۫ۗۢ۟ۢ۠ۚ۟ۛۙۘۨۤ۬ۜۢۜۘۧۥۦۥۖ۬ۗۥۘۖۨ۫ۖۢۡۘۜۢۨۘۥۦۧۘۚۥۘۜۛۨ";
                        while (true) {
                            switch (str3.hashCode() ^ 305290581) {
                                case 399020810:
                                    application.onCreate();
                                    break;
                                case 506938218:
                                    str3 = "ۧۜ۠ۜۚۖۘۗۙۡۚۢۚۡۥۜۘۢ۫ۜۜۦۛ۟ۖۜۘۡ۫ۨۥۨ۬ۛۘۘۚۨۡۘۙۗۨۚۢۥۘ";
                                case 544170563:
                                    String str4 = "ۢ۟۠ۤ۬۫ۗۡۖۘۘ۫ۨۘۛۖ۬ۦۘۛۙۢۥۘ۟۬ۜ۟۠ۙ۟۟ۦۛۛۡ۬ۢ۬۠ۜۦۘ۠ۚۢۛۥۢۖۢۨۘ";
                                    while (true) {
                                        switch (str4.hashCode() ^ (-2033988482)) {
                                            case -1690027795:
                                                str3 = "ۜۗۚۛۙۡۤۧۙۘۧ۠۬ۗۛۖۛۚۜۖۗ۟ۛۖۖۦۘ۟ۦ۬۫ۚۤۗۧ۟ۖۢۡۘۡۘۤۖۛۤۘۧۛ";
                                                continue;
                                            case -1210696147:
                                                str3 = "۠ۘۢۘۢ۟ۘۖۜۘۚۤۡۘۘۧۤۙ۫ۤۖۘۘۨۡۥۘۛۨۢ۫ۘۗ";
                                                continue;
                                            case -1102690729:
                                                if (application == null) {
                                                    str4 = "۠۠ۡۘۨۙۥۦۚۛۦۥۥۘۛ۠ۗۢۙۦۘۢۛ۟۬ۛۢ۫ۜۜۖۤۖۘ۠ۚۛۜۗۡۧ۬ۚ۫ۙۢ";
                                                    break;
                                                } else {
                                                    str4 = "۠۠۠ۖۙۙ۫۠ۡۡۛۡۘۚ۫ۖۘۧۧۧۗ۟ۘۗۥۦۘۥۡۨ۬ۢ۬ۤۘۥۘ۟ۢۨۛۘۢۨۛۡۘۢ۫ۥ۬ۥۦۘۙۘۖۘۨۢۚ";
                                                    break;
                                                }
                                            case -94402936:
                                                str4 = "۠ۤۗۖۨۚۡ۟ۥۘۜۥۜۙۚۨۘۗۗۨۘۜ۠ۤۜ۫ۦۘۤ۬ۚۡۡ۬ۦۖۘۧۙ۟ۦۛۥۘ۬ۛۡۘۘ۫ۗ۠ۜۘۜۖۧۘۜۙۥۘ";
                                                break;
                                        }
                                    }
                                    break;
                                case 2119660225:
                                    break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String str5 = "ۙۦۙۦ۟ۚ۬ۦۦۡۧۛۢۖۨۘۡۦۡۜۢۛۤ۟ۛۧۧۜۘ۬ۡ۬ۦۢ۫۠ۧۙ";
                    while (true) {
                        switch (str5.hashCode() ^ (-482948869)) {
                            case -2110161854:
                                String str6 = "ۤۦۢۛۘۛۥۚۜۘۧۧۥۧۘۛۦۤ۠ۙ۟ۖۘۛۢۤۜ۟ۘ۟ۖۡۘۨۛ۫۟ۨۦۘۛۢۧۦۦۜۙۛ۟ۚۘۧ۠۠۫ۚۨۘ";
                                while (true) {
                                    switch (str6.hashCode() ^ 1953011472) {
                                        case -757723256:
                                            str5 = "ۥۘ۟ۡ۬ۧۖۨۘۗۨ۠ۧۡ۫۬ۙۡۖۥۘ۬ۢۖۢۘۘۗۖۜۛۦۤۥ۬ۡۘ";
                                            continue;
                                        case -635670440:
                                            str5 = "۬ۜۡۘۖۨۛۖۥۚۙۤۛ۠ۗۗ۠ۢۛ۟۟ۡۘۥۜۡۘۙۜۧۖۜۚۡۥۖۨۗ۠";
                                            continue;
                                        case 856142670:
                                            if (!Objects.equals(fcRuQsQrcxOAzxwEalcM.NETWORK, "[#NETWORK#]")) {
                                                str6 = "ۘ۬۟ۙۗۜۘۚۜۘ۫ۗ۫ۤ۬۬ۛۖۜۘۢۘ۟ۚ۠ۘۦۘۙۤۦ۬ۨۘۘ۬۠ۙۢۛۤ۫ۧۛۤۘۘ۟ۚۥۘ";
                                                break;
                                            } else {
                                                str6 = "۠ۘۦ۬ۤۛۨۧۡ۠ۖۧۘۢۚۨۘۜۗۨۥۗۛۢ۠۬ۙۦۖۥ۫ۘۛۛۤۚۥۜۜۜۖۤۦۘۖۡۡۦۨۧۗۜ۠۫ۤۥۘ";
                                                break;
                                            }
                                        case 1919994524:
                                            str6 = "ۙ۫ۦۗۢۚۤۘۘۨۘۧۘ۫ۚۜۘۨۡ۟ۨۨۢۘۢ۬ۦ۠ۘۤۘۘۘ۠۟ۤۦۦۘۨۘۢۡۦۛ";
                                            break;
                                    }
                                }
                                break;
                            case -1872848302:
                                str5 = "ۖ۟ۚۙۜۖۘ۠ۤ۬ۥۚ۬ۤۧۜ۟ۦۙ۟ۤۦۗۖۥ۟ۜۗۡۤۚ۟ۖۢۦۚۚ۫۬۠ۥۚۨۘۦ۬ۡۘۤ۟ۗ۠ۥ۫ۙ۟ۜۘ";
                                break;
                            case 301786313:
                                Utils.checkNetworkAndExitIfUnavailable(this);
                                break;
                            case 570145359:
                                break;
                        }
                    }
                    ActivityKeeper.init(this);
                    return;
            }
        }
    }

    @Override // android.app.Application
    public void onTerminate() {
        s3 s3Var = null;
        String str = "۫ۦۡۦۘۘ۫۫ۥ۬۬۬ۗ۫ۦۤۨۨۘۢۧۜۘ۫ۗۥۘۧۘۤۙۨۜۙ۬ۜ۫۫۬ۤۛۨۘ۬۫۟";
        while (true) {
            switch ((((str.hashCode() ^ 480) ^ 396) ^ 254) ^ 1317898307) {
                case -2101016544:
                    String str2 = "ۥۙۗۨۤۨۨۖۧ۟ۗ۠ۡۖۖۚۦۧۘۘۗۚۖۨۥ۠ۢ۠ۙۨۦۘۖۙۨۖۦۥۘۙۜۜۧۜۡۢۢۡ۫۫ۧۤۦ۠۟";
                    while (true) {
                        switch (str2.hashCode() ^ 1277357605) {
                            case -2039378624:
                                str2 = "ۦۛ۬ۗۜۗ۠ۘۢۜۢۨۢۜۘ۟ۥ۟۬ۙ۬۫۟ۘۘۙۚۖۘ۟ۜ۠ۙ۠ۖۘۜۨۢ۠ۦۡۘۨۡ۬";
                                break;
                            case -1267759037:
                                str = "ۚۤۛۜۖۡۘۚ۫ۡۘۜۥۘۘۛۧۨۘۖۨۚۙۦ۟۠ۦۥۚۛۥۘۜۛۜۘۗۨۢۦۗۨۘ۬ۦۨۨۘۖۘۘۢ۠۠ۜۖۘ";
                                continue;
                            case -1213983871:
                                str = "۟ۤۦۘ۫ۛۦۜۛۨۥۦۧۘۛۦ۬ۢۢۜۤۙۡۘۚۖۘۘ۟۟۫ۛۜۡۘ";
                                continue;
                            case 596521700:
                                String str3 = "۬ۙۧۚ۟ۚ۬ۗۨ۟۠۟ۦۡۘۙۥۧۦ۟ۦۧۗۘۗۚۛ۫ۘۡ۬ۢۜ۫۫ۜۘ";
                                while (true) {
                                    switch (str3.hashCode() ^ 2079095575) {
                                        case -1324988998:
                                            str3 = "ۛۚۗ۬ۤۥۘ۟ۜۦۤۜۦۘۡۡۖۙۡۖۖۨۥۘۚۖۖۘ۬۬ۦۧۨۥۘ";
                                        case -391366176:
                                            str2 = "ۗۥۘۘ۬ۨۘ۠ۥۖۡ۟ۡۘۖۥۖ۠ۥۘۘۦ۠ۡۘۥۥۙۛۖۨۖ۬ۦۘۙۘۦ۟۟۬";
                                            break;
                                        case 1005076504:
                                            str2 = "ۨ۟ۗ۫ۖۤۗۤۦۘۢۖۚۤۤۨۘۥ۬ۙۗۚ۬ۤ۬ۖۨۥۘ۬ۛۚ۬ۦۗۙۘۘ";
                                            break;
                                        case 1129907782:
                                            str3 = s3Var != null ? "ۨۘۦۘۧۡۚۘۚۚ۠۟ۨ۟۬ۜۘۡ۫۠ۘۚۦۥۧۜۘۢۖۨۘۜ۟ۜۤۚۡۘۜ۬ۛ" : "ۛۦۦۘۢ۫ۜۘۤۛۥ۠ۢۢۚۦۡ۟ۚۜۘۜ۬ۜۦۛۗۘۦۡۘۙۧۙۖۤۘۥۚۤۦۚۚ۬ۘ۬";
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case -1553315410:
                    super.onTerminate();
                    str = "ۡۦ۠ۤ۬ۤۜۛۧۜ۫ۧۚۚ۟۠۠ۦۙۘۖۛۥ۠ۛ۟ۙۥۛۘ";
                    break;
                case -1543032736:
                    return;
                case -349827028:
                    str = "ۗۘۨ۟ۢۦۘۖۙۧ۠ۙۜۤ۠ۘۘۖۤۖۘ۬ۛۘ۬ۚ۟ۧۢۚ۫ۢۦ";
                    break;
                case 764359477:
                    s3Var = webSocketClient;
                    str = "۠ۗۨۘۖۢ۫ۗۜۖۘۗ۬ۘۘۜ۠ۨۢ۫ۘۘۡۡۖۛۙۡۗۖۨۘۥ۟ۦ۬ۤۥۘۚۥۛۢۖ۟ۚۜ۟ۗ۬۬ۨۢ";
                    break;
                case 1721769941:
                    s3Var.close();
                    str = "ۚۤۛۜۖۡۘۚ۫ۡۘۜۥۘۘۛۧۨۘۖۨۚۙۦ۟۠ۦۥۚۛۥۘۜۛۜۘۗۨۢۦۗۨۘ۬ۦۨۨۘۖۘۘۢ۠۠ۜۖۘ";
                    break;
            }
        }
    }
}
