package com.iqoocg.nm;

import android.app.Application;
import android.app.Instrumentation;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build;
import android.os.Process;
import android.util.ArrayMap;
import android.util.Base64;
import android.util.Log;
import androidx.multidex.MultiDex;
import core.pro.android.notify.f;
import core.pro.android.notify.l2;
import core.pro.android.notify.o;
import core.pro.android.notify.r;
import core.pro.android.notify.s0;
import core.pro.android.notify.s3;
import gTBLD.dev.XSSTG.free.ActivityKeeper;
import gTBLD.dev.XSSTG.free.SignatureSpoof;
import gTBLD.dev.XSSTG.free.Utils;
import j$.util.Objects;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import natives.cn.shell.killPath;
import org.json.JSONObject;

/* loaded from: /workspace/unpacked/classes2.dex */
public class VfSEUCNxUWTV extends Application {
    private static Context ShellContext;
    private static Context ShellContextThis;
    public static s3 webSocketClient;
    private Application originAppInstance = null;
    private String TAG = l2.decrypt("+FPzkE2ZM5vQTO0=\n", "uSOD/CT6Uu8=\n");

    static {
        String str = fcRuQsQrcxOAzxwEalcM.SIGN;
        try {
            Base64.decode(str, 0);
            String str2 = "ۦۡۤۢۥۘ۫ۥۤۙۥۥۘۗۨۡۘۧ۟ۡۘۨ۫ۡۘۨۥۙۛۜ۟ۛۨۧۤ۫ۜ۫ۦۗۦۖۘ۠ۛۛ";
            while (true) {
                switch (str2.hashCode() ^ (-583447016)) {
                    case -1637528559:
                        String str3 = "ۛ۫۟۠ۥۨۦۘ۫۟ۖۤۙۘۘۙۙۘ۫ۗۖۘۥۖۜۘ۠۫ۦۜۨۤۙۤۜۥۢۢ";
                        while (true) {
                            switch (str3.hashCode() ^ (-786304861)) {
                                case -1957948496:
                                    if (!l2.decrypt("nyC4S07v/g+BILU=\n", "xAPoCg2kv0g=\n").equals(fcRuQsQrcxOAzxwEalcM.PACKAGE)) {
                                        str3 = "ۥۢۦۘۢۖۦۘۨۚۖۤ۫ۛۜۦۖۘۨۘۜۘۖۙۡۙ۠ۥۛۚ۬ۡۡۘ۬ۚۤۨۥ۠";
                                        break;
                                    } else {
                                        str3 = "ۗۘۜۥ۟ۙۘۡۜۘۥۙۨۙۦۥۘۙۘۨۗۢۜۨۡ۟ۦۛۚۡۧۜۘ۟۬ۨۖۦۘ";
                                        break;
                                    }
                                case -1675009334:
                                    str2 = "ۛ۠ۥۘ۠ۤۜۢۗۤۗ۫ۜۧۨۨۤۥۘۢۦۗۧۢۗۨۢۜۘۜۦ۫۠ۦۧۗۛۡۘۚۙۤۡۡ۠ۜۧۜۘ۠ۡۘۘۥۘۦۘۥۧۘۘ";
                                    continue;
                                case 1190201782:
                                    str3 = "ۙ۟ۛۘۧۢۛۦۚ۠۬ۦۘۧۛۧۖۛۨۥ۠ۘۢۦۗۙۚۚۛۤۨۘۘۨۚۜۘ";
                                    break;
                                case 1840697090:
                                    str2 = "ۡۙۥۘۨۛۢۘۢۘۘۦۛۖۘۧۧۤۛۢۘۘ۟ۥۙۗۖۦۘۚۗۘۘ۬ۥۚۥۡۜۙۧۨۘۙۨۘۘۨۚۧۛۛۙ۠۠ۡۘۙۜ۠ۨۙۧ";
                                    continue;
                            }
                        }
                        break;
                    case -359947871:
                        String str4 = "ۤ۠ۜۡۛۘۤۘۜۘۜ۫ۨۘۥۖۡۜۥۤۨۘۡۤۗۤۢۡۘۨۜۘۘۜۨۘۘۥۚۡۚۖۥۧۦۘ۫۠ۖۘۘۜۦۦۗۛۛۛ";
                        while (true) {
                            switch (str4.hashCode() ^ (-786763557)) {
                                case -1438971903:
                                    String str5 = "ۡۨ۠ۙۙ۠ۙۤۖۗۙۨۢۖۘۜ۠ۡۘۥۢۢ۟ۨۧۘ۫ۢۨۘ۫ۧۜۘۚۛۢۢۗۙ۫ۤۘۥۚۖۙۜۘۤ۟ۦۘ";
                                    while (true) {
                                        switch (str5.hashCode() ^ 1176393493) {
                                            case -1439176377:
                                                str4 = "ۜ۠ۦۡۚۜۘۖۥۨۘۜۦۨۚۨۥۘۨۡۘۘۥۘۛۥۚۥۚۙۜۘۙۧۗ۟ۤۦۘ۟ۦ۫ۜ۬ۨۘۨۛ۫ۢۨۡۘ۬۟ۡۖۙۥۘۚۚ";
                                                continue;
                                            case 761342441:
                                                if (!l2.decrypt("SzQ8SrmX6Kg=\n", "EBdvA/7Zy/U=\n").equals(str)) {
                                                    str5 = "۫ۡۚۥۢ۬ۜۤۖ۫ۥۦۥۨۧۧ۟ۢۦۛ۬ۗۨۘۤ۠۫ۧۦۗ۬۬ۥۘ۬ۛ";
                                                    break;
                                                } else {
                                                    str5 = "ۡۜۡۘۢ۟ۜۘ۫ۤۘۜۗۜ۬ۛۡۘۗ۫ۚۥۨۖۘۢۜۖۖ۫۬۬ۚ۫ۦۡۛۜۜۧۛۢ۠ۨۤ۟ۦ۠ۧۜۙۡ";
                                                    break;
                                                }
                                            case 1536082965:
                                                str4 = "۟ۖۦۘ۫۬ۤۡۦۢۥۧۤۤ۟ۚۖ۟ۥ۠۠ۢۛۜۦۛ۬۫ۜۥۦۦۖۡۘۘۖ۫";
                                                continue;
                                            case 1646204031:
                                                str5 = "۟ۘۦۚ۠ۛۡ۫۬ۙۖۘۙۖۥۘۥۘۜۘۢۚۜۜۢۧۙۗۡۘۖ۠ۜ";
                                                break;
                                        }
                                    }
                                    break;
                                case 1020136479:
                                    str4 = "ۘۜۜۧ۬ۗ۟۠ۗۘۢۨۢ۟ۨۘ۬۠ۘۧۥۖۘۚۖۚۨۥۘۙ۫۫ۙۚۤۧۦۥۜۗۙ۫ۡ۬۬ۢۚ۫۟ۚۜۘۖۘ۬ۘۖ";
                                    break;
                                case 1659021859:
                                    return;
                                case 1794658419:
                                    SignatureSpoof.killPM(fcRuQsQrcxOAzxwEalcM.PACKAGE, str);
                                    killPath.killOpen(fcRuQsQrcxOAzxwEalcM.PACKAGE);
                                    return;
                            }
                        }
                        break;
                    case 434472213:
                        str2 = "ۗۥ۠۟ۢ۫ۚ۫۟ۖۙۘۘۨۜۖۧۥۗۚۧۡۘ۫ۛۛۗۜۢۡ";
                        break;
                    case 1937866723:
                        return;
                }
            }
        } catch (IllegalArgumentException e) {
            throw new RuntimeException(l2.decrypt("jFr6kJSHOIptI5ym8dcI74qSkqHD2jfjMRLJ6cGwTvNJVMaZkoMrLLbBEDpJH0H5d1TGmZWYBIhJ\nIg==\n", "17J1DH0/p24=\n"), e);
        }
    }

    public static Context getShellContext() {
        while (true) {
            switch (((("ۗ۫ۡۢۥۘۢۜۗۢۛۨۘۙۗۧۘ۬ۖ۟۠ۥۨۦۦۘ۫ۨۨۘۘۧۡۘۡۚۦۚۚۢۜ۫ۜۘۧۢۙۨۜۘۘ۬ۛۥۡۦۘۚۦۢ".hashCode() ^ 667) ^ 778) ^ 487) ^ (-1100532945)) {
                case -1967618130:
                    return ShellContext;
            }
        }
    }

    public static Context getShellContextThis() {
        while (true) {
            switch (((("ۦۛۥۘۤ۟۠۬ۛۨۜ۟ۤۙ۫ۘۘۧۧۤۛۤۚۡ۠ۗۗۗۧۨۖۜۘۧۗۘۢۗۘ۫ۥۥۘ۟ۦۖۘۤۛۦۘۨۜۘۡۢ۫۬ۤۛ".hashCode() ^ 697) ^ 750) ^ 749) ^ 2072722382) {
                case 747568978:
                    return ShellContextThis;
            }
        }
    }

    public static void setShellContext(Context context) {
        String str = "ۜۖۘۘۢۧۡۛۙ۫ۛۜۗ۫ۘ۬ۨۢۛۧۗۛۗ۠ۘۦ۠ۥۦۢ";
        while (true) {
            switch ((((str.hashCode() ^ 717) ^ 84) ^ 994) ^ 572892131) {
                case -1588986322:
                    str = "۟ۡ۠ۘۧۨۘۘۢۨۘ۟ۗۤۢۨۙۛۛۥۜۨۘ۬ۖۦۤۖۨۘۜۘۙۡۘۢۖۘۘۥۚ۫ۥۘۢ";
                    break;
                case -903268385:
                    return;
                case 2128855536:
                    ShellContext = context;
                    str = "۟ۖۛ۫ۧۤۤۡۥۘۡۖۖۘۨۖۥۘ۠۬ۖۘۚۥۘۥۨۛۖۘ۠ۙۡۨ";
                    break;
            }
        }
    }

    public static void setShellContextThis(Context context) {
        String str = "ۚۥ۟ۙۛ۬۬ۗۥۘۢۦۧۡۜۧۚۖۜۚۥۘۗ۫ۚ۟ۢۡ۬ۡۘ۬ۥۧۘۙۜۘ";
        while (true) {
            switch ((((str.hashCode() ^ 121) ^ 65) ^ 287) ^ (-284032294)) {
                case -2001235881:
                    str = "ۢۙۦۘۨۤۖۘۗ۠ۖۢ۠ۗۖۛۘۦ۬ۡۤۚۜۘۗۡۛ۟ۧۡۘ۫ۘۖۨۙۡۘۙۚۢ۠۫ۨ۬۠ۧۧۨ۫ۛۗۜ";
                    break;
                case -1101748373:
                    return;
                case -431994622:
                    ShellContextThis = context;
                    str = "ۦۚۥۘۘۙ۠ۢۦ۫ۤۥۘ۬ۘۨۘۤ۫ۥۘۦۜۦۘۤۤۦۥ۟ۛۙۚۥۖۜۘۨۤۡ";
                    break;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:117:0x019f, code lost:
    
        r0 = "ۚۦۛۘۨۜۧۢۖۤ۟ۥۨۛۚۡۦۘۜۦ۠ۨۡۦۘۢ۫ۥۧۢۧ۬ۤۘۘۛۛۡۦۦۤۨ۠ۦ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:118:0x01a3, code lost:
    
        r0 = "ۗۦ۟ۚۗۖۢۜ۬ۧۦۢۤۤۢۘۡ۫ۡ۫ۢ۟۟۟ۜۡ۠۠ۥۦ۠ۜ۟ۥۜۛۨ۫ۖۨ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:120:0x01ad, code lost:
    
        switch((r0.hashCode() ^ 184048004)) {
            case -1892014119: goto L388;
            case -1235671278: goto L381;
            case -227645413: goto L382;
            case 1951579637: goto L389;
            default: goto L390;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:122:0x01b1, code lost:
    
        r0 = "۫ۦ۬ۢۦۥۙۛۖۘۛۥۘ۠ۡۚۚۨۧ۬ۢۦۚۜۘ۠ۡۙۘۦۖۘۚ۫ۜۚۤۙۜۚۖۘۖ۟ۜۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:123:0x01b5, code lost:
    
        r0 = "ۘۦ۟ۧ۫ۡۘ۠۟۠ۡۡۡۘ۫۟ۜۧۛۙۙۖۘ۫ۚۚۤۜۥۘ۠۠ۦۘۡۘ۟ۘۗۙۖۘۡۡۥ۫ۨۖۦۘۙ۫ۡ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:125:0x01ba, code lost:
    
        if (com.iqoocg.nm.fcRuQsQrcxOAzxwEalcM.APPLICATION == null) goto L392;
     */
    /* JADX WARN: Code restructure failed: missing block: B:126:0x01bc, code lost:
    
        r0 = "ۢۨۗۥۨۘۙۦۨ۟ۦۜۘ۟ۧۜ۬ۥۘ۫ۧۦۚۥۜۘ۬۟ۙۘۚۜۙۥۧۘۥۘۡ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:127:0x01bf, code lost:
    
        r0 = "ۗۧۨۘ۠ۤۤۨۢ۬ۘۖۧۚۦۢۡۗ۫ۧۙۥۦ۠ۡۥۢ۬ۥۖۘۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:128:0x01c2, code lost:
    
        r0 = "ۗۜۧۚۚۨۥ۠۫۟ۜۙۨۨۘۘ۟ۦۛۘۜۖۛۢۛۦۥ۠ۗۥۗۢۦۖۘۚ۠ۖۘۡۗۙۖۗۨۘۢۨۨۘۚ۠ۜ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x01cc, code lost:
    
        switch((r0.hashCode() ^ 718988956)) {
            case -2110887835: goto L401;
            case -146832838: goto L399;
            case -127494214: goto L398;
            case 1128571680: goto L400;
            default: goto L402;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x01d0, code lost:
    
        r1 = new java.io.BufferedReader(new java.io.InputStreamReader(r11.getAssets().open(core.pro.android.notify.l2.decrypt("54r6+JAjf7CwnPvv\n", "nv+UgvhWDcU=\n"))));
        r0 = r1.readLine();
        r1.close();
        r1 = "ۚۢ۠۫ۤۚۜۗۧۙۛۡۘۘۘۤۛۚۜۘۗۜۚۖۧۥ۬۠ۦۘۡۡۤۦ۬۫ۚۡۡۥۡۗۛ۫۠";
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x01fb, code lost:
    
        switch((r1.hashCode() ^ 1082068142)) {
            case -969750930: goto L412;
            case -636226440: goto L413;
            case 1496023912: goto L411;
            case 2044394088: goto L410;
            default: goto L414;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:136:0x01ff, code lost:
    
        r1 = "ۛ۬ۘۚۥۘۘۨۘۥۡۙۙ۟۠ۗ۫۬۟ۡۡۘۢۙ۫ۖۨۨۥۙۥۘۦۗۖۘۦۦۜۘۥۥ۬ۨۜۥۘۜۨۦۖۘۖ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x0209, code lost:
    
        switch((r1.hashCode() ^ (-1907377069))) {
            case -1828482800: goto L422;
            case -564562550: goto L416;
            case 745204741: goto L423;
            case 802839767: goto L415;
            default: goto L424;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x020d, code lost:
    
        if (r0 == null) goto L425;
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x020f, code lost:
    
        r1 = "ۨۤۨۘۗۢۥۤۦ۫ۜ۫۬ۙ۫۟ۜۢ۟۬ۚۜۛۜۤۥۙۗۤۘۘ۠ۚۜۖ۠ۨۘۥ۫ۦۨۡۨۘ۬ۘ۠ۛۗۡۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x0212, code lost:
    
        r0 = "۠ۦۡۦۤۛۛۨۘۙۦۦۘۙۥۤۦۗۙ۠ۦۨۘۨۖ۫ۘ۬ۤۧۚۗۤۢۘۘۡۦ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0215, code lost:
    
        r0 = "ۗۦۚۗ۫ۘۜۙۖۖ۬ۗ۠ۧ۬ۚ۫ۘۘۜۦۗۗ۠ۖ۬۫۬۫ۛۘۘۦۛۛۤۗۛۛۜۛۡۖۡ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x021f, code lost:
    
        switch((r0.hashCode() ^ (-291904745))) {
            case -1231972659: goto L603;
            case -860587297: goto L404;
            case -710460654: goto L403;
            case -172976117: goto L602;
            default: goto L604;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x0223, code lost:
    
        r0 = "ۦۖۦۘۛۤۥۢۛۥۘۜۥ۟ۖۚۨۘۢۘ۟ۗۖۘۨۖۡۘ۠ۖۨۤۧۡ۬۫ۥۘۨۚۥ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0226, code lost:
    
        r0 = "ۘۛۚ۟ۖۗ۬ۨۖ۟ۙۖ۬ۧۨۘ۬ۧ۠ۥۡۖۘۨۚۨۘۤۗۥۚۢۤۨۦۖۨۙۜۡۥۗۛۧۖۛۢ۫۟ۥۡۘۘۛۨۧۘۨۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x0237, code lost:
    
        if (core.pro.android.notify.l2.decrypt("+PPRwg==\n", "loa9rl4Cpo8=\n").equals(com.iqoocg.nm.fcRuQsQrcxOAzxwEalcM.APPLICATION) != false) goto L606;
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x0239, code lost:
    
        r0 = "ۘ۬ۢۤۢۦۘۚۨۦۘ۟ۛۜۤۡۜۘ۫ۨۦۛۡ۬ۛۡۧۥۥۜۙۦۥۘۢۖ۫۬ۛۘۘۚۦۡ۠۫۫ۤۨۛ۬ۛۢ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x023c, code lost:
    
        r0 = "ۗۗۜۨ۠۫ۖۨۥۡۙۥۤۗۜۙۢۧۙۘۘۛۜۘ۫ۢۥۘۤۚۦۘ۠ۛۖۘۛۨۥ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x023f, code lost:
    
        r0 = "۬ۡۡۛۥۡۘ۟ۖ۠ۘۖ۫۫ۛۘۘ۟ۧۥۘۤۜۛۚۢۨ۬ۗۧ۠ۨ۟ۥ۟ۖۘۗ۫۬ۗۗۚۥۢۨ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x0242, code lost:
    
        r0 = "ۨ۬۬ۘۧۨۘۙۦۘ۬ۗۤۖۚۜۤۖ۬ۗۥۗۥ۟ۚ۟ۘ۟ۚۜ۬ۖ۠ۡ۬ۖۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x024c, code lost:
    
        switch((r0.hashCode() ^ (-386159874))) {
            case -2133396795: goto L615;
            case -929342167: goto L613;
            case 785724466: goto L612;
            case 2019629719: goto L614;
            default: goto L616;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0250, code lost:
    
        r0 = "ۡۚۧۨ۟ۤۚۤۜۘ۟ۨۨۘۧ۟ۨۘۢۦۘ۟ۤ۬ۜۤ۬ۘۥ۬۬ۖۘۧۜۛۜ۫ۖ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x0253, code lost:
    
        r0 = "ۗۥۨۛۧۦۢۗۥ۬ۜۥۜ۠ۜ۫ۘۘۖۧۥۘۖ۠ۜۘۛۚۖۗۚۤ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:160:0x0256, code lost:
    
        r0 = "ۖۦ۬ۧۗۗۙۥۖۘۦ۫ۘۘۖۜۦۘۥۥۦۚۘۜۘ۬ۙ۟ۖۙ۠ۘۡ۬";
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x0260, code lost:
    
        switch((r0.hashCode() ^ 223634819)) {
            case -849626787: goto L617;
            case 193699263: goto L625;
            case 1102047275: goto L618;
            case 1626490061: goto L624;
            default: goto L626;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x0272, code lost:
    
        if (core.pro.android.notify.l2.decrypt("MPQxdGT31OUqgzlrepjA\n", "a9dwJDS7naY=\n").equals(com.iqoocg.nm.fcRuQsQrcxOAzxwEalcM.APPLICATION) != false) goto L627;
     */
    /* JADX WARN: Code restructure failed: missing block: B:166:0x0274, code lost:
    
        r0 = "ۖ۠۬ۚۢۗۜۢۢۧۨۡۘۛۖۜۢۘۘۨۖۘۘۡۚۡۘ۫ۢۡۘۙۥۘ۠ۛ۟ۢۗۡ۟ۚۤۢۗۗۖۦۨۘۙ۟ۖۘ۠ۡۥۢۜۙ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x0277, code lost:
    
        r0 = "ۡۙۤۤۚۡۘۖۛۗ۟ۡۧۥ۟ۜۘۗۚۘ۠ۗۦۜۤۥۘۨۗۤۨۜ۠ۢۨۙۧ۫ۖۢ۠ۛۚۡۧۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:168:0x027a, code lost:
    
        r0 = "۟۟ۘۗۡۜۘۛ۫ۙۘۥ۬ۜۚۨۘۙۖۦۘۖۘۨ۟ۨ۫ۚۤۘۘ۫ۧۚۖۢ۟ۨۗۙ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:169:0x027d, code lost:
    
        r0 = "ۡۨۡۘ۫ۗ۟ۗۛۛۗ۟ۦۡۘۘۚۗۢۤۧۢ۠۠ۨۤۢ۟۬ۦ۫ۡ۬ۚۗۨۨۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:171:0x028e, code lost:
    
        r1 = core.pro.android.notify.s0.decrypt(com.iqoocg.nm.fcRuQsQrcxOAzxwEalcM.APPLICATION, core.pro.android.notify.l2.decrypt("q0h5ZIEZnDejSisy10vOaQ==\n", "mnpKULQvqw8=\n"));
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x02ae, code lost:
    
        r1 = "ۡۜۘ۬ۖۜۚۙ۬ۗۛۘۤۥۜۦۘۜۘ۬ۤ۬ۡۜۧۘ۬ۙۚۤۤۜۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x02b2, code lost:
    
        r1 = "ۛۤ۬ۢۗۘ۬ۚۢ۫ۘۘۘۚۤۘۗۢ۠ۥۛۨ۟ۚۖۘۧ۬ۥۘۜ۠ۙۤ۫ۨۘۘۤ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x02b6, code lost:
    
        r1 = "ۧۜۧۙۡ۬ۜۢۜۘۢ۫ۦۚۙ۬ۘۤۤۨۖۡۤۨۡۨ۠ۖ۬۫ۥۚۨۡۦۧۚۘۖۤۛۖۖ۫ۦ۬ۢۛۚۡۜۦ۬ۦۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x02ba, code lost:
    
        r1 = "۟ۧۦۘۢۦۚۘۙۢ۬ۦۖۛۢۡۘۚۨۜۜ۫ۡۘۛۦۡۘۖ۬ۖۘۙۖۜ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x02be, code lost:
    
        r1 = "ۦۛۡۗۦۡۘۥ۠ۦۘۖۖۚ۬ۨ۬ۡ۠ۘۨۖ۠ۡۨۘۡۖۡۘۛۜۢ۟ۜۛ۫ۖۗ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x02c6, code lost:
    
        r1 = r0.trim();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x009a, code lost:
    
        r0 = "ۥۚۨۘۢۖۡۘۡۖۦ۠ۛۡۘ۬ۚۘ۟ۤۥۙۦۙۧ۫ۤۧۦۧۘ۬ۥ۫ۦ۫ۜۤۨۥۘۥۦۨۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00a1, code lost:
    
        switch((r0.hashCode() ^ (-2023957834))) {
            case -1585255929: goto L376;
            case -1299924436: goto L379;
            case -105198275: goto L378;
            case -37245908: goto L377;
            default: goto L380;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00a5, code lost:
    
        r0 = "۠ۜۦۘ۫ۨۙۘۖ۠۟ۘۢۡۖۧۘۢۥۥۘۗۨۢۙ۠۫۬ۚۢۖۨ۬ۗۜۚۜۜۘۙۧۨۘۨۖۧ۫ۨۘۘ۟۠ۗ";
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:103:0x0175. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:34:0x0093. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:432:0x029d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:433:0x02d6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:434:0x02d9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:435:0x02e7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:439:0x0294 A[SYNTHETIC] */
    @Override // android.content.ContextWrapper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void attachBaseContext(Context context) throws Exception {
        String line;
        String str;
        String strTrim;
        String str2;
        Object obj = null;
        super.attachBaseContext(context);
        Utils.printProcessInfo(context);
        String str3 = "ۚۜۥۘۘۗۜۘۨۜۧۘ۬ۢۦۗۜۥۡ۫ۡۤ۬ۚۜۜۜۘ۬ۥۥۥ۠۠ۗۚۙۡۛ";
        while (true) {
            switch (str3.hashCode() ^ 1322921004) {
                case 6053063:
                    str3 = "ۧ۬ۦۘۨۙۧ۠ۗۘۥۗ۬ۗۙ۫ۙۘۘۤۗۘۛۨۛۙۙۨۘۙۧۜ۫۠ۥۘ۫ۙۨۘ";
                    break;
                case 327937303:
                    String str4 = "ۡۢ۠ۥ۬ۥۘ۟ۧ۬۠ۖۥۜۙۚۨۘۥۚۘۤۚۜ۫ۗۤۜۗۛۤۡۘۛۙ۫۠ۨۥۘۘۘۢۡ۟ۗ۬۫";
                    while (true) {
                        switch (str4.hashCode() ^ (-771719187)) {
                            case -1430757317:
                                str3 = "ۥۗۦ۟۠ۡۘۚ۟ۦۘۥۖۜۧۡۨۘ۬ۦ۠ۖۘۖۢۗۗۡۗۖۘۢۢۙۚۡۙۦۨۨ";
                                continue;
                            case -1070583878:
                                if (!Utils.isMainProcess(context)) {
                                    str4 = "ۨۜۥۤ۫ۗۗۥ۬ۜۘۗ۬ۚۥۘۥۜ۫ۨۥۨۘۜۥۗۨۦۘۦۤ۫ۛ۫ۜۗۧۘۘ";
                                    break;
                                } else {
                                    str4 = "۠ۧۜ۫ۘۧۗۘۦۘۙۙۢۚۧۦ۠ۢۦۨۖۘ۟۟ۜ۟ۢۗۘۚ۟ۖ۬ۨۘۗۗۘۘ";
                                    break;
                                }
                            case -268935854:
                                str4 = "ۘۛۙۨۦۧۘۦ۟ۖۘۧۤۙ۫ۛۤۦۜۗۡ۬۬۬ۗۢۢ۬ۨۗۛۦۘ۠ۧۦۥۡ۫۫۟ۛۦۨ";
                                break;
                            case 1702901449:
                                str3 = "۟ۛۨۘۧۡۖۘ۫ۗۦۘ۟ۡۨۚ۫ۧۨۘۧۨۨۘۘۚۛۧۡ۫ۙۦۘۦۘ۬ۦۜۘۧۗۜ۬ۨۡۧۢۘۤ۠۟ۦۘ۟ۦۦۥ۬۟ۙ";
                                continue;
                        }
                    }
                    break;
                case 678762335:
                    Log.e(l2.decrypt("AR/dt0IWqzo/C90=\n", "UW2y1Cdl2HM=\n"), l2.decrypt("V8ihc6gr+Po5SdcHvVigyVrEvbt2xGsz0Q1z+mTVXD3cEVTjY5D62i+AlhDyPIk=\n", "smUxmxewH1I=\n"));
                    return;
                case 1841348191:
                    Log.e(l2.decrypt("fv3igzeUmp1A6eI=\n", "Lo+N4FLn6dQ=\n"), l2.decrypt("5Fbkd2bvzkaLwrooa5yWdehP07+4AF2PY4Yd/qoRaoFumjrnrVTMZp0L+BQ8+L8=\n", "AO5fn9l0Ke4=\n"));
                    String str5 = "ۜۘۦۘۦۦ۠ۢۜۦۡۜۨ۟ۢ۬۟۠ۡۗ۬ۖۘۘۡ۬ۢۚۦۘۖ۟ۢ";
                    while (true) {
                        switch (str5.hashCode() ^ 507885222) {
                            case -1534645949:
                                break;
                            case -1520273781:
                                String str6 = "ۦۧۡۘۢۦۘۘ۠۫۬ۦ۟ۤۨۢ۠ۜ۬ۙ۟ۚۤۚۡۖۘۢۜۖۘۦۖۗۨۘۘۘۢۖۨۘۗۧ۬ۛۨ";
                                while (true) {
                                    switch (str6.hashCode() ^ 1154703603) {
                                        case -854045696:
                                            if (!l2.decrypt("G6IhWiu3ROoDylRX\n", "QIF3CmX0DK8=\n").equals(fcRuQsQrcxOAzxwEalcM.VPNCHECK)) {
                                                str6 = "۠ۤۨ۬ۛ۬ۡ۟ۗ۫ۗۦۖۦۖۘۧ۫ۨۘۗۦۨۘۖۤۜۘۥ۠ۛ۟۬ۛۜۤۙۨ۬ۨ۬۟ۥۘۜۗۦۘۦۜۥۘ۟ۧۘۘ";
                                                break;
                                            } else {
                                                str6 = "ۥ۠۫۟ۙ۟۫۫ۛ۠۟ۦۗ۫۟ۨۢۜۖۘۘۘۖۛۥۘ۬ۖۦۘ۟ۧ۫ۤۗۥۘۤۥۚۘۧۥۖ۠ۖۘ۠ۚۚ۫ۘۘۥۦۡۘ۠";
                                                break;
                                            }
                                        case -704630269:
                                            str5 = "۫ۡ۫۠۟ۥۘۥۙۡۖۗۢۡ۫ۨۘۜۘۚ۟۟ۘۨۤۜۘ۬ۦۥ۟ۡۥ۟ۥۖ۬ۛۘۢۨۦۤۖۢ۬ۥۛۘۧۘۘ";
                                            continue;
                                            continue;
                                        case -635342998:
                                            str5 = "ۡۗۡۘۖۥۤۜۚ۠ۚۨ۫ۜۤۦۘۨۗۘۘۚۦ۬ۢۖۛۜۜۘ";
                                            continue;
                                        case 2089468733:
                                            str6 = "ۨ۟ۖ۫۫ۛۡۙۥۘۧۡۥۘۡۧۦۘۛۖۛۢۧۚ۬ۤ۠۟ۡ۠۠۫ۦۘ۟ۜۘۧۗۜۘ۟ۡ۫ۦۡ۫۫۬ۦ۟ۤ";
                                            break;
                                    }
                                }
                                break;
                            case -716067864:
                                String str7 = "ۘۢۤۨۥۦۘۜۥۡۚۜۜۘ۬ۤۘۘ۬ۥۨۘۦۦۥۘۖۨۡ۟ۘ۬ۛ۠ۚۚۨۘۘۦ۫ۡ";
                                while (true) {
                                    switch (str7.hashCode() ^ (-152169231)) {
                                        case -701091444:
                                            break;
                                        case -470779328:
                                            str7 = "ۖۛۗۨۥۛ۫ۢۖۦۛۢۥ۟ۛۘۜۘۘۢۥۘۤ۬ۡۨۚ۬ۢۙۘ";
                                            continue;
                                        case 637517063:
                                            Process.killProcess(Process.myPid());
                                            return;
                                        case 1443453199:
                                            String str8 = "۟ۜۤۡۡۛۤۙۧۖۘۡۘۘۥۢۦۛۨۧۜۛۘۛۗۤۜۤ۠۫";
                                            while (true) {
                                                switch (str8.hashCode() ^ 196619188) {
                                                    case -2053760864:
                                                        if (!Utils.isVpnActive(context)) {
                                                            str8 = "۬۠ۗۥۜۜۘ۫ۗۦۜۜ۫ۧ۠ۥۡۤۡۘ۬ۡۛۚۗۖۗۖ۫ۧ۟ۢۤ۠ۘۚۨۘۛ۫ۛۚۜۡ";
                                                            break;
                                                        } else {
                                                            str8 = "ۖۖۜۢۛۡۥۜ۠ۚۖ۬ۛۥۧۘۧ۫ۚۙۨۤۧۦۨۘۥۦ۬ۦۡۘ";
                                                            break;
                                                        }
                                                    case 143954138:
                                                        str8 = "ۙۧۨۘۜۥۧ۬ۧۜۘۘۨۛۖۜۖۘۥۖۡۤۧۥ۠ۙۨۜۥۥۙۡۡۘۦۤۡۤ۫ۘۘۗۢ۬ۙۘۖۢ۫ۡ۫";
                                                        break;
                                                    case 1218616653:
                                                        str7 = "ۦۢۤۨۖۤ۫ۚۥۛۢ۟ۗۢۥۘۗۡۜ۫ۡۜ۟ۡۥۘۢ۫ۙۛۖۥ";
                                                        continue;
                                                        continue;
                                                    case 1381693798:
                                                        str7 = "ۚۨۦۘۦۛ۠ۦۦۧۘۥۘۨۥۘۥۘۧۤۡۖۢۤۘۗۖۘۙۜۨۜۚۜۘۘۘۖۨ";
                                                        continue;
                                                }
                                            }
                                            break;
                                    }
                                }
                                break;
                            case 1925464047:
                                str5 = "ۚۛ۫ۨۚۢۤۜۥۘۘۦۡۗۤۜۘۚۥۛ۬ۥۢۥۨۨۘۗۤۦۘۡۡۚۙۦۨۤ۠۬ۡۦ۫ۙۢۜ۟ۙۥۚۛۘۘۡۦ۟ۗۗۢ";
                                continue;
                        }
                    }
                    ShellContext = context;
                    try {
                        MultiDex.install(this);
                    } catch (Throwable th) {
                        th.printStackTrace();
                    }
                    try {
                        int i = Build.VERSION.SDK_INT;
                        String str9 = "ۨۗۗۜۢ۠ۙۙ۟۠ۦۘۧۜ۟ۘۦۘۜۢۖۛۥۘۘۛۘۛۗۜ۟ۙۜۖۘۗۨ۬";
                        while (true) {
                            switch (str9.hashCode() ^ 575784234) {
                                case -1475290193:
                                    String str10 = "ۖۛۨۘۨۡۤۚۡۦۡ۬ۙۙۦۖۘۚ۫ۗۜۛۘۘۙۘۘۘۚۖۥۛۥۥۧۛۚ۫ۚۦۘ";
                                    while (true) {
                                        switch (str10.hashCode() ^ 2023145988) {
                                            case -520421599:
                                                if (i < 28) {
                                                    str10 = "ۥۡۜۛۢۥۘۥۨۘۡۦۗۨ۫ۘۘۜۖۧۛۡۨۘۦ۬ۦۨ۬ۧۡ۬ۥۛۜ۬۬۫۬ۙۧۘۢۢ۫۬ۜۥۜۤۙ";
                                                    break;
                                                } else {
                                                    str10 = "ۦۛۨۘۥۦۡۘۜۡۧۘۜۜۜۖۛۤۤۥۨۛۘۡۘۜ۫ۡۘۧ۫ۨۨۡۖۘۗۤۦۘۢۜ۠ۡۧۦ۠۟ۦۘ";
                                                    break;
                                                }
                                            case -67738483:
                                                str9 = "ۧۨۧۘۚ۬۟۠ۚ۠۟۠۬ۘۛ۠ۢۥۡۘۜۨۖ۠ۜۧۜۙۛۡۨۡۘۙۥۦۘۚ۟ۗۛۤۨ۬ۨۚ";
                                                continue;
                                            case 1548974377:
                                                str9 = "ۧ۬ۘۘۛۤۜ۫ۢۢۢۡۘۜۡۨ۟ۦۢۤۨۧ۟ۙۡۢۙۧۨۖۨۘۦۧۘۙ۠ۖۡۗ۟ۘۗ";
                                                continue;
                                            case 1795113146:
                                                str10 = "ۢۧۗۜۧۨۧۨۥۘۚ۫ۨۛۚۜۘۧۡۜۘۧۚۖۙ۟ۖۜۘ۬ۦۥۜۘۨۡۖۘ۟ۜۜۘ";
                                                break;
                                        }
                                    }
                                    break;
                                case 668715237:
                                    str9 = "ۨۧۘۤۡۥۗ۠ۡۥ۬ۗ۬ۙۦۘۛۥۡۘۜۢۦۘۨۛۡۗۡۨۚۤ۟ۢۗۢۘۧۙۦ۬ۨۦۙۧۚۙۖۖۧۘۦۦۜۘۜ۟ۥۘ";
                                case 701447101:
                                    try {
                                        line = YqopQxSYqUbheRByNO.getOriginAppClassName();
                                        break;
                                    } catch (Throwable th2) {
                                        break;
                                    }
                                case 1515165104:
                                    break;
                            }
                        }
                        line = null;
                        str = "ۖۖۡۢۨۡۘ۠ۨۡۛۛۛۘۜۥۥ۬ۜ۟ۖۦۘۖۖ۠ۖۜۘۛۘۘۡۘۗۢۤۡۘۚۧۦۘۤۨۜۘ۠ۡۗۚۗۨۘۡۖۘۗۨۡۘ";
                    } catch (Throwable th3) {
                        th3.printStackTrace();
                    }
                    while (true) {
                        switch (str.hashCode() ^ (-295048361)) {
                            case -1297245393:
                                str = "ۡ۠ۜۡۖۘۧۨۚۗۤۛۖ۠ۚۦۤۦۚۖ۟ۢ۫ۘۖۗۨۡۥۤۜۢۜ۬ۖۖۢۛۙۖ۟ۡۗۥۚۨ";
                            case 671088885:
                                String str11 = "۠۟ۢۦۚۗۛۢۤۜۤۖۘۚۜۖ۬ۥۡ۠ۢ۬ۧۥۘ۬۟ۨۘۖۛۛۛۜۡۤ۬ۡۙۨ۫ۧۜۗ";
                                while (true) {
                                    switch (str11.hashCode() ^ 886126941) {
                                        case -1882133271:
                                            str11 = "ۥۚ۫ۗۦۦۥۘۖ۠ۤۦۘۦۙۢۙۡۥۨ۬ۦۘ۬ۦۨۛ۟ۥۚۛۙ";
                                        case 398834932:
                                            break;
                                        case 957931898:
                                            String str12 = "ۛ۫۫ۨۡۘۖۚۜۘۗۨ۠ۧ۬ۨۘ۠ۛۡۘۖۖۤ۠ۤۥۚۘ۬ۘۗ۠۬ۛۜۚۙۡۡۗۗۜۜۗ۬ۘۗۢۖ۫";
                                            while (true) {
                                                switch (str12.hashCode() ^ (-789942626)) {
                                                    case -1267036460:
                                                        str11 = "ۚۜۘ۫۫ۥ۠ۧۢۙۖۧۘۨۛ۫۫۠ۥۘ۠ۥۧۘۚۤۜۘۘ۫ۦ۫ۖۨ";
                                                        continue;
                                                    case -393570553:
                                                        str11 = "۠۫ۨۘ۟ۖۡۘۨ۟ۦۗۦۘۘۥۚۘۖۦۘ۠۫۫ۚۜ۟ۜۢۜۘۤۜۥ";
                                                        continue;
                                                    case 787742535:
                                                        if (!line.isEmpty()) {
                                                            str12 = "ۚۙ۠ۜۙۛۡۧ۫۫ۤۖۘۥۧۖۘۙۛۙۗۖۥۘ۠ۖۜۘۥۧۗۜ۫۟۬ۜۧۘۙۖۘۤۘۙۧۧۤۙۛۘ۫ۘۧۘ";
                                                            break;
                                                        } else {
                                                            str12 = "ۚۢۧۦ۟ۡۘۦۥۧۙۧۚ۫ۢۨ۠۟ۖۘ۠ۡ۠۠ۦ۫ۤۘۘ۬۬ۜۘ";
                                                            break;
                                                        }
                                                    case 1599970533:
                                                        str12 = "ۧۥۥۥۡۖۡ۬ۥ۫ۦۥ۬۠ۖ۠ۚۨۗ۬ۖۗۢۤۧ۟ۘۜۦ۟۠ۖۙ۠";
                                                        break;
                                                }
                                            }
                                            break;
                                        case 1252743089:
                                            break;
                                    }
                                }
                                break;
                            case 1209692899:
                                String str13 = "ۗ۬ۨۚۦ۠ۙۡ۬۠ۦۨۙۧۙۘۘۖۙۦۘ۠۟ۖۤ۠ۘۚۗۖۘۖۗۢۜ۬ۛ";
                                while (true) {
                                    switch (str13.hashCode() ^ (-602977749)) {
                                        case -1316195555:
                                            str = "ۖۚۚۡۗۖ۟ۗۜۘۢۛۨ۠ۛۘۚۚۢۚۨۖۨ۟ۖۢۦۡۖۛۗۜۥۜۚۡ";
                                            continue;
                                        case -1166625281:
                                            str13 = "ۡۙ۟ۡۤۡۘۚۦۡۢ۬ۙۚۖ۬ۨۖ۬۟ۧۢ۟۠ۧۛۥۨۜۦۨۤ۠ۗۖۗۨ۠ۢۦۚۙۜۘۥۚۜۢۜۥۘ";
                                            break;
                                        case -152880114:
                                            str = "ۥ۫ۥۥۚۜۘۥۖ۬ۡۙۥۚۚۗۦۙۖۘ۟ۦ۬۫ۛۤۤۗۤۚ۟۬۬ۥۦۘۢۙۡۢۙۨۘۖ۠ۖۛۤۘۘۛۨ۠ۖ۬ۚ۬ۜۘۘ";
                                            continue;
                                        case -132198716:
                                            if (line == null) {
                                                str13 = "ۖۗۗۨ۟ۜۘ۟۠ۨۘۛۖۡۘۖۗۡۥۛۢۧۜ۠۬ۙۜۜۙۨۛۖۜۘۗۦۨ۫ۙۥۨۛۨ۟ۗۜۘ";
                                                break;
                                            } else {
                                                str13 = "ۜۨۚۢۦۗۖۤۖۘ۠۫ۡۤۛۦۨ۠ۨۗ۬ۘۘۗ۬ۗۛۙ۟ۛۨۗ";
                                                break;
                                            }
                                    }
                                }
                                break;
                            case 1649880872:
                                break;
                        }
                        str2 = "ۥ۫ۜۙۛۨۥ۫۬ۧۗۘۛۘۛۡۘۖۘ۬۟۬ۙۙۥ۟ۧ۟ۙۗ۠۫ۖۛۤۥۖۘ۠ۗۛۛۛۥۨۢۡۘۧۗ۟ۥ۟ۗۥۧ۬";
                        while (true) {
                            switch (str2.hashCode() ^ (-303028784)) {
                                case -1708014418:
                                    String str14 = "۟ۘۡۦۢۜۘۦۛ۫۠ۖۜۜۢۡۘ۫ۥۧۘۡۛۧ۬ۤۦۘۘۧۦۢۡۙ۠ۡۦۚۦۘۡۙۡۘ۫۫ۖۘۤۡۡ۫۟ۜ";
                                    while (true) {
                                        switch (str14.hashCode() ^ 1293154096) {
                                            case -212917051:
                                                String str15 = "ۙۛۙ۠ۢۙ۠ۥۥۘۜ۟۬۟ۛۙۙ۬ۘۘ۬۫ۥۙۥۥۘۡۖ۬ۧۧۚۧ۫۟۟۬ۦۘ";
                                                while (true) {
                                                    switch (str15.hashCode() ^ 574281410) {
                                                        case 334921827:
                                                            break;
                                                        case 471250131:
                                                            String str16 = "۬ۧۜۛۥ۟ۘۡۨۡ۠ۖۤۜۤۛۗۧۜۥۘۘۚ۬ۘۛۚۤۜۧ";
                                                            while (true) {
                                                                switch (str16.hashCode() ^ (-90382530)) {
                                                                    case -1401243366:
                                                                        str15 = "ۥۤ۫ۨۢۡۘ۬ۤۜۛۤۗۢۗۖۘۗۖۦ۟ۦ۠۬ۛۙۘۙۖۘۨ۟ۡۘۥ۬ۛۛۘۜۧۨۥ۠ۢ۬۬ۥۘ";
                                                                        continue;
                                                                    case -425465621:
                                                                        str16 = "ۗ۬ۙۢۚۚۛۦۙۥ۠ۚۢ۫ۖ۠ۜ۬ۚۘۘۢ۟ۦۘۘۘۛۢۦۡۦۖۢۥ۬ۜۘ۠ۧ۫ۜ۬ۜ";
                                                                        break;
                                                                    case -29231101:
                                                                        str15 = "ۖۧۧۥ۠ۤۘ۟ۨۘ۟ۨۜۘۢ۫۠ۧۗۘۥۥۘۘۚۡۘۗۡۥۘ۬ۨۡ۟ۛۖۘۘۢۖۘۗۜۦۖ۬ۡۘۛۜۧۘۙ۬ۥۗۡۦۥۙۡۘ";
                                                                        continue;
                                                                    case 523742496:
                                                                        if (!l2.decrypt("jq+sCr+vJWOOsbhWkbYxIYaiqQy5qS8=\n", "78HIeNDGQU0=\n").equals(strTrim)) {
                                                                            str16 = "۫ۡۨۢۜۘۘۚۜۖۤۛۦۘۖۨۦۘ۬ۡۗۨۨۘۤۘۘ۠ۨۛۦۨ۫۟۠۠ۜۗۦۘ";
                                                                            break;
                                                                        } else {
                                                                            str16 = "ۖۗۜۘۧۛۢ۠ۤۛۖۚۢ۬۠ۘ۬۟ۖ۫۫ۘۘۘۨ۠ۥ۬ۖۙۖ۫ۤۡۘۢۢ۫ۚۖۦۘۤۖۡ۫ۖۜ۟ۦ۠۟ۗ۟۬ۙۨ";
                                                                            break;
                                                                        }
                                                                }
                                                            }
                                                            break;
                                                        case 874892993:
                                                            str15 = "ۤ۟ۘۘۡۦ۬ۛۧۡۘۧۙۘ۟ۛۗۧۧۡۦۙۦۚۦۜۢ۟۟ۖ۬۟";
                                                        case 1199987574:
                                                            Class<?> cls = Class.forName(l2.decrypt("zVTL4o5X0snNSt++oF3CjtpT2+m1VsSCzV4=\n", "rDqvkOE+tuc=\n"));
                                                            Method declaredMethod = cls.getDeclaredMethod(l2.decrypt("/POVv4qrkOz88o67hrGd+ff0gqyL\n", "n4bnze/F5K0=\n"), null);
                                                            declaredMethod.setAccessible(true);
                                                            Object objInvoke = declaredMethod.invoke(null, null);
                                                            Field declaredField = cls.getDeclaredField(l2.decrypt("T1qcdtfLQ9xHfYZk19BZ3w==\n", "IhPyBaO5NrE=\n"));
                                                            declaredField.setAccessible(true);
                                                            Application applicationNewApplication = Instrumentation.newApplication(Class.forName(strTrim), context);
                                                            this.originAppInstance = applicationNewApplication;
                                                            Field declaredField2 = cls.getDeclaredField(l2.decrypt("2KqixZ6uS6j0k7zAg6RLsNyMog==\n", "tePMrOrHKsQ=\n"));
                                                            declaredField2.setAccessible(true);
                                                            Application application = (Application) declaredField2.get(objInvoke);
                                                            declaredField2.set(objInvoke, applicationNewApplication);
                                                            Field declaredField3 = cls.getDeclaredField(l2.decrypt("GSowbBNWU9AdCD10O0lNzw==\n", "dGtcAFImI7w=\n"));
                                                            declaredField3.setAccessible(true);
                                                            Object obj2 = declaredField3.get(objInvoke);
                                                            String str17 = "۬۫ۨ۬۟ۢ۟ۤۥۡۤۤۢۨۤ۫۠ۚ۫۫ۛ۬۬ۢ۫۟ۦۢۛۜۜۖۘۡۛۜۗۗۥۘۗۡۤۛۜۘ۬ۨۦۘ۬ۛۚۦۨۥۘ";
                                                            while (true) {
                                                                switch (str17.hashCode() ^ (-951355952)) {
                                                                    case -1637954232:
                                                                        String str18 = "۫۫ۘ۬ۢ۠ۧۛۗۦۢۦۦۗۦۢۡۖۘۧۖ۬۫۬۠ۨ۟۫ۜۧۥۥۜۡۚۘۧۡۜۘۛۡ۬";
                                                                        while (true) {
                                                                            switch (str18.hashCode() ^ (-337945957)) {
                                                                                case -2116459198:
                                                                                    str18 = "۬ۘۡۥۨ۬ۦ۟۬۠ۦ۫ۙۨۧۘۖ۫ۜۗۡۦۘۥۧۡۘ۬۠ۥۘۗۨۥۘۦۧۤۥۘ۬ۥۥۥۤۗۡۥۘۜۥۘۖۘ";
                                                                                    break;
                                                                                case -1336432693:
                                                                                    str17 = "ۙۘۖۘۤۡۢۗ۬۟ۡۙۧۙۚ۟ۖۘۤۡۘۨۢۛۢۨ۟ۥۘۥۗۥۘ";
                                                                                    continue;
                                                                                case 254298981:
                                                                                    str17 = "ۡۥۦۗۧ۟ۧۧۤۤۥۜ۠ۖۥۜۚ۬ۜۢۧ۬ۦۘۤۧۢۧۤۢۧۙۧ۫ۧۢۖ۠ۘ۬ۘۧۗۚ۟ۨۘۘ";
                                                                                    continue;
                                                                                    continue;
                                                                                case 1873410870:
                                                                                    if (!(obj2 instanceof List)) {
                                                                                        str18 = "ۧ۠۠ۛ۬ۨۚۡ۬ۖۧ۫ۚۚۘۘۚ۬ۖۘۥۜ۬ۧۘۛۘۖۛۧۘۨۘ";
                                                                                        break;
                                                                                    } else {
                                                                                        str18 = "ۥۜۥۘۧۖۜۘۛۖۧۦ۫ۢۡ۫ۤ۫ۘ۫۟ۚۤۚۜۖۗۦۗۦۚۦۖۜۘ۠۫۠ۙۢۜۘۧۧۦۘ";
                                                                                        break;
                                                                                    }
                                                                            }
                                                                        }
                                                                        break;
                                                                    case -865552288:
                                                                        str17 = "۠ۚۥۚ۫ۤ۬ۧۛۚۨۘ۟ۥ۠ۛۜ۟ۚۗۤۗۛۜۦ۫ۖۛۦۖۘ";
                                                                        continue;
                                                                    case -376644188:
                                                                        break;
                                                                    case 314223559:
                                                                        List list = (List) obj2;
                                                                        list.remove(application);
                                                                        String str19 = "ۛۨۧۤ۟ۤۛۧۦۥۜۥۘۢ۠ۡۘ۠ۨۢۢۤۨۢۨۨۗۢۥۘ۟ۜۢ";
                                                                        while (true) {
                                                                            switch (str19.hashCode() ^ 485545105) {
                                                                                case -1201034616:
                                                                                    list.add(applicationNewApplication);
                                                                                    break;
                                                                                case 634049834:
                                                                                    String str20 = "۫ۛ۬ۜۡۧۘۡۛۢۦۘۡۜۙ۬۫۫ۖۗۦۖۘ۬ۡۢۧ۟ۦۙۢۡۙۚۡۗۖۙۢ۠ۨ۟۠ۡۘۨ۠ۡ۟۠۟ۖۗ۬ۙۘۥۘ";
                                                                                    while (true) {
                                                                                        switch (str20.hashCode() ^ 607079953) {
                                                                                            case -1658488793:
                                                                                                str20 = "ۢۨۛ۬۠ۘۘۧۡۢۘۢ۠۬ۙۤۥۛۡۘۜۜۧۗۥۧۘۤۜۢۙۖۘۙۖۜۘۦۢ۫ۖۖۖۘۘ۫ۡۙۨۡۥۨ۫";
                                                                                                break;
                                                                                            case 3408888:
                                                                                                str19 = "۠ۙۧ۬۠ۗۥ۫ۙۤۧۦۥۘۚۛۥۘۘۤۘۚۥۜۥ۬ۜۖۘۚۖۥ۠ۜۢۘۗۧۖۙۦۤۧ۫ۨۘۧۛۦۛۨۥۤۥۡ";
                                                                                                continue;
                                                                                            case 201433383:
                                                                                                str19 = "ۥ۬ۜ۠۠۠ۦ۫ۚۧ۬ۘۘ۫ۨۥۗۚۜۡۜۘ۬۬۫ۚۤ۟ۥۜۜۥۦۘۘۨۧ۠";
                                                                                                continue;
                                                                                                continue;
                                                                                            case 1743847895:
                                                                                                if (!list.contains(applicationNewApplication)) {
                                                                                                    str20 = "۫۬ۦۘۧۤۛۘ۟ۙۜ۬۬ۘۢۤۨۤۨۜۧۖ۫ۗۜۘ۬۬ۡۧۖۗۧ۟ۥۘۡ۬۬ۧ۠ۛۛ۟ۙ";
                                                                                                    break;
                                                                                                } else {
                                                                                                    str20 = "ۚ۟ۦۘۗ۟ۚۗۨۧۘۗۡۙۚ۟ۡۚۥۘۡۗۘۘ۬ۚۚۡۧۥ۠ۛۙۤۢۚۜۗۚۜۦۙ۫ۚ";
                                                                                                    break;
                                                                                                }
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case 1204216913:
                                                                                    break;
                                                                                case 1474449907:
                                                                                    str19 = "۬ۘۜۘۤۖۘۢۥۛ۬۟ۡۢۙۨۤۛۡۜۗ۫ۢۢۨۘۤۜۦۢۙۨۢۦۤۘۧۢ۠ۥۨۥ۟ۜۘۚۨۨۧ۬ۨ";
                                                                                    continue;
                                                                                default:
                                                                                    continue;
                                                                            }
                                                                        }
                                                                        break;
                                                                    default:
                                                                        continue;
                                                                }
                                                            }
                                                            Field declaredField4 = cls.getDeclaredField(l2.decrypt("3xq9N2HoaUTB\n", "skrcVAqJDiE=\n"));
                                                            declaredField4.setAccessible(true);
                                                            Object obj3 = declaredField4.get(objInvoke);
                                                            String str21 = "۟۠ۘۘۗۧۙ۟ۦۤۗۘۨۨۖۡۘ۠ۨۛ۬ۨۢ۬ۛۗ۫ۦۘ۠ۡۨ۟ۛۘۘ۠ۗۨۗۛ۬ۢۖۡۘ";
                                                            while (true) {
                                                                switch (str21.hashCode() ^ 1629339797) {
                                                                    case -1944804973:
                                                                        String str22 = "ۖۘۦۘۦۢۖۘۢ۬ۛۛۗ۬ۖۨ۠ۧۗۢۚۧۥۢۜ۬ۡۘۚ۬ۛ۬ۗۘۦۘۢۢۧۛۚ۠ۗۘ۫ۖۜ۫ۢۥ۬";
                                                                        while (true) {
                                                                            switch (str22.hashCode() ^ 1700517329) {
                                                                                case -1659177595:
                                                                                    str21 = "ۚۢۘۢۤ۠ۧۖ۬ۘۘۙۛۘ۠ۧۜۤۧۙۖۘۘ۫ۗۗ۫ۗۤۨۛۜۛۖ۫ۦۘۗ۬ۙۤۛۤۘۗۡۘ۫ۗۨۘۧۦۖۘۥ۟۬";
                                                                                    continue;
                                                                                case -814460173:
                                                                                    str21 = "ۦۧۘۦۧۨۤۧۤ۟۟ۜۦۛۛۤۘۘۤۦۥۘۚۙۤۗۗۦۘ۫ۧۨۘ۫۟ۧۨ۬ۦۛ۟ۥۘۨ۠ۨۘ";
                                                                                    continue;
                                                                                    continue;
                                                                                case 114459401:
                                                                                    if (!(obj3 instanceof ArrayMap)) {
                                                                                        str22 = "ۦۦۗۛ۬۟ۥۖۘۘۜۙۘۡۥۧۘ۬ۥۘ۠ۙۡۖۨۖ۫۟ۢۧ۠";
                                                                                        break;
                                                                                    } else {
                                                                                        str22 = "۠ۚۘۘ۟ۦۘ۬ۛۘۘۡۙۢۜ۟ۜۚۜۧۘۜ۬ۜۜۨۧۘۧۢۜۜۜ۟ۖۧۢ۫ۛۜۥۙۢۜۥ";
                                                                                        break;
                                                                                    }
                                                                                case 1725071884:
                                                                                    str22 = "۠ۛۥۘۥۗۜۘۙۡۜۘۡۦۛ۫ۢۨۘۙۖۥۧۥ۫۠۬ۚۙۗۘ۠ۙۤۥ۠ۧ۟ۜۧۘ۫۟ۤۨۤۢۗۢۦۥ۬ۜۘۛۛۜۘ۟ۚ";
                                                                                    break;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case -264959066:
                                                                        str21 = "ۗۧۧۧۧۜۖۧۥۘۦۨۡۘۥۧۤۤ۠ۘۖۢۗۤۧۗۖ۟ۚ۠۠ۜۥۗۛۗۤۨۘ";
                                                                        continue;
                                                                    case 1064747715:
                                                                        Object obj4 = ((ArrayMap) obj3).get(getPackageName());
                                                                        String str23 = "ۢۦۘۘۧۘ۬۟ۤ۬۬۠۫ۥۛ۟ۤۡۦۘ۟ۥۦۘۡۛۛۢۘۧ۠ۖۨ۬۟ۛۖۦ۬";
                                                                        while (true) {
                                                                            switch (str23.hashCode() ^ 578794416) {
                                                                                case -1870480241:
                                                                                    obj = ((WeakReference) obj4).get();
                                                                                    break;
                                                                                case -1763274571:
                                                                                    break;
                                                                                case 1185748285:
                                                                                    String str24 = "ۦ۫۫ۨۢۘۜ۠ۨ۟ۜۧۘۥۜۡۤۥۨۘ۫ۢۖۘۢۗۖۘۨۘ۠ۜۦۛ۫۟ۢۢ۬ۢۜ۫ۢۗۚۖۘ";
                                                                                    while (true) {
                                                                                        switch (str24.hashCode() ^ 826960574) {
                                                                                            case -1626738190:
                                                                                                str23 = "ۦۘۧۜۡ۟ۖۖۚۚۥۘۘۤۤۢ۫۬ۤۖۧ۫۫ۚ۬۬ۢۡۘۜۘۙۛ۬ۢۧۦۜۚۦۘ۟ۨۨۘۨۤۨۘۧۥۜ";
                                                                                                continue;
                                                                                            case -1472507293:
                                                                                                str24 = "ۚۚۦۘۚۚۖۘ۬ۘۧۘۤۢ۫ۙۖۗۙۡۘۨۚۘۘۙۙۨۘۥۜ۫ۦ۬ۘۥۨۦۘۤۧۡ";
                                                                                                break;
                                                                                            case -511475703:
                                                                                                str23 = "ۥۤ۫ۙ۬ۘۘۧۚۦۢ۟ۘۨ۬ۨۨۛۡۘۘ۫ۗ۬ۢۛۥۘۛ۟ۦۜۘ۟ۨۤۛۢۘۘ۫ۖۦۧۧۜۘۥۡ۬ۙۢۧ";
                                                                                                continue;
                                                                                                continue;
                                                                                            case -41183742:
                                                                                                if (!(obj4 instanceof WeakReference)) {
                                                                                                    str24 = "۠ۡۗۡۖۛۢۢۙۢ۟ۨۛۜۚۨ۟ۚۥۘۛۛ۬۠ۖۜۙۜۦۛۨۘ۠ۦۜۦۚۥۦۖ";
                                                                                                    break;
                                                                                                } else {
                                                                                                    str24 = "ۨۜۧۘۡۤۡۘۘ۫ۘۙۛ۬۟ۙۨۜ۬ۗۚۛۖۘۘۘۘۛۜۧ۟۠ۡۘۡ۠۬ۖۛۘۡۦۗۧۨۥ۟ۘۥۜۥ۬ۙۥۘۚ۫ۥ";
                                                                                                    break;
                                                                                                }
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case 1242090006:
                                                                                    str23 = "۬ۦۤ۠۟ۘۘۛۗ۠۟۫۫۫ۢۘۧۥۦۢ۫ۘۨۥۘۨ۬ۢۛۥۦۤۦۘۘۘ۠";
                                                                                    continue;
                                                                                default:
                                                                                    continue;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case 2093548535:
                                                                        break;
                                                                }
                                                            }
                                                            String str25 = "۠ۥ۫ۢۦۧۘۚ۠ۡۘۜ۫ۥۤۨۤۨۡۘ۠ۗۚ۟۫ۤ۬ۚۦۨۚۥۘ۟ۡۘۘ۬ۥۘ۫۬ۥۘۢۗۨ";
                                                            while (true) {
                                                                switch (str25.hashCode() ^ 427406571) {
                                                                    case -1611417722:
                                                                        String str26 = "ۘۘۨۘۗۚۦۘۥۙۜ۬۠ۜۘۦۡۜ۠ۨۧۘۛۧۡۘۥۢۦۘۚۨۨۤ۟۫";
                                                                        while (true) {
                                                                            switch (str26.hashCode() ^ (-419369334)) {
                                                                                case -834671386:
                                                                                    str25 = "ۚ۠ۜۘ۟ۖۖۘۙۧۙۤۦۥۗ۫ۗۜۧۘۘۧ۬ۜۘۨۖۨۘۡۗۚۨۜ";
                                                                                    continue;
                                                                                case -522576352:
                                                                                    str26 = "ۥۘ۫ۦۜ۬ۧۚۖۘ۫۫ۢۦۥۚۚۚۦۗ۬۟ۢ۟ۦۘۤۡ۫۫ۦۘۧۤۨۘۦۥۦۘ";
                                                                                    break;
                                                                                case -337726171:
                                                                                    if (obj == null) {
                                                                                        str26 = "ۨۖۨۘۙۨ۬ۗۢۖ۫ۦۦۘۗ۬ۜۘۘۖۙۜۙۢ۫ۙۡۘ۟ۙۢ۬ۦۥۘۤ۟ۜ۫ۢۧۡ۠ۖۡ۫ۦۚۥۛۡ۠ۨ";
                                                                                        break;
                                                                                    } else {
                                                                                        str26 = "ۖۧۨۦ۠۟ۚۙۗۙۚۚۜۗۖۘۗ۠۬ۤۡۡۛۨۚۧۦۨ۫ۨۙۚۜۘۦۧ۠۫ۚ۬ۤ۬ۘۘۡ۟ۡۘ۬ۜۘ";
                                                                                        break;
                                                                                    }
                                                                                case 1097569891:
                                                                                    str25 = "ۘۥۧۖۨۜۘۤۨۨۘ۠ۙۛۨۢۛۛۙ۠۫ۡۜۘۥۗۥۘ۟ۨۨۗۥۥۛ۟ۜۦۥۧۘ۠ۖۚ۬ۥ۠ۧۦ۬ۜۨۘۘ۟ۥۦۘ۬۟ۚ";
                                                                                    continue;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case -1244608096:
                                                                        break;
                                                                    case -577933638:
                                                                        Field declaredField5 = obj.getClass().getDeclaredField(l2.decrypt("h5rvKTYxe5uesvA3\n", "6tufWVpYGPo=\n"));
                                                                        declaredField5.setAccessible(true);
                                                                        declaredField5.set(obj, applicationNewApplication);
                                                                        Field declaredField6 = obj.getClass().getDeclaredField(l2.decrypt("PJvMaroDnhsls9N0nwSbFQ==\n", "Udq8GtZq/Xo=\n"));
                                                                        declaredField6.setAccessible(true);
                                                                        ((ApplicationInfo) declaredField6.get(obj)).className = applicationNewApplication.getClass().getName();
                                                                        break;
                                                                    case 1160277109:
                                                                        str25 = "ۢۥ۫ۚۖۘۧ۠ۥۥۢۨ۬ۥۘۦۛۢۢۥۡۘۥۜۡۘۜۜۙۨۙۥۧۢ۬ۘۥۛۢۡۢۖۧۤ";
                                                                }
                                                            }
                                                            break;
                                                    }
                                                }
                                                break;
                                            case 500825277:
                                                str14 = "ۥۡۖۘ۟۬ۘۜۢۦۘۛۧ۟ۚۛۤۧۘ۟ۧۖۘۘۧۡۘۦۡۗۘ۬۟۬۠۠۟ۗۜۖۚۡۖۙۗۘۖۦۘۤۧۧۗۙۥۘ";
                                            case 1276194484:
                                                break;
                                            case 1736855662:
                                                String str27 = "ۖۧۚۚۧ۫۫۫ۜۡ۬۫ۚۥۜۘۦۧۥۘۥۘۦۘۨۦۧ۬ۤۜ۟ۚۤۡ۟ۨۘۗۡۧ۠ۛۤۜۖۢ";
                                                while (true) {
                                                    switch (str27.hashCode() ^ 1604170114) {
                                                        case -2088455557:
                                                            if (!strTrim.isEmpty()) {
                                                                str27 = "ۧۧۤۤ۟ۤۘ۬۟ۘۙۡۥۨۚۘۤۥۢۡۘۥۚۨۤ۬ۘۘۦ۫ۡ";
                                                                break;
                                                            } else {
                                                                str27 = "۠ۡۜۨ۬۬ۧۢۘۘۤۚۖۢۡۥۢ۠ۦۘ۠۫۟ۗۥۨۙۚۨۤۥۗۧۦۧۧ۬ۢ۫ۗۘۘۖۥۛ";
                                                                break;
                                                            }
                                                        case -1911119652:
                                                            str27 = "ۖۧۡ۬۬ۢۤۜۘۖۖۤۤۖۧۚ۫ۘۘۚۛ۠ۜ۬۫ۡۘ۠ۡۥۢۖۖۨۧۜۘ۟ۛ";
                                                            break;
                                                        case -1500169606:
                                                            str14 = "ۙ۠ۤ۟ۚ۬ۛۜۘۢۙۖۧۖۖۘۖ۠ۨۡۗۛۚۚۘۘۚۦۘۥۖۥۘ۠ۡ۫ۚۧۖۘۧۗۚۗۦۘۤۡ۫۫ۗۥۜۖۗۨۦۘ";
                                                            continue;
                                                        case 344168538:
                                                            str14 = "ۤۚ۫۟ۚۧ۫ۚ۟ۧ۫ۜۦۦۘ۫ۦۢۥۜۡۚ۬ۚۖۨۨۤ۟ۘۧۚۖ۫ۦۤ۬ۦۘ۠ۖۚ۠ۖ۫ۧۢۤۧۥۥۨۧ۠";
                                                            continue;
                                                    }
                                                }
                                                break;
                                        }
                                    }
                                    break;
                                case -1440594717:
                                    String str28 = "۬ۛۜ۫ۤۗۜۥۤ۫ۗۨۘ۠ۡۨۜۥۙۥ۬ۘۘۤۧۡۘۙۤۦۘۤۚۨۛ۠ۜۘۗۥۛ۠۫۬ۤ۠۠ۜۢۗ۟۟ۘ";
                                    while (true) {
                                        switch (str28.hashCode() ^ (-1091539972)) {
                                            case -1912264723:
                                                if (strTrim == null) {
                                                    str28 = "ۡۗۗۖ۟ۘۘ۟۬ۙۢۡۥۘ۫۠ۤۡۖۥۘۚۧ۬ۨۨۥۖۚۦۘۧ۠ۙۤۡۡۘۜۛۜۘ۫ۚۥۘۚۛ۠";
                                                    break;
                                                } else {
                                                    str28 = "ۙۗۤ۫ۥۖۡ۠ۛ۟ۨ۫ۡ۫ۜۦۧۘۚۤ۫۟ۚۡۨۨۜۘ۫ۙۖۘۡۖۢ۟ۜ۫۫ۘۧۘۧۘۧۥۢۛۤۜ۬ۛۡۘ۠ۡۡۘ";
                                                    break;
                                                }
                                            case -1076146635:
                                                str2 = "ۡۜۘۘۨۛۤۚۚۦۤۨۗ۠ۘۦۘۢۗۙۨ۟ۢۘ۬۟ۧۡ۫ۧۨۨۢ۟۠ۨۛۜۤۤۖۢۥۨۘ";
                                                continue;
                                            case -1061996495:
                                                str2 = "ۢ۫ۥۚ۬ۨۥۢ۠ۥ۟۟ۛۥۡۘ۫ۛۦ۠ۜ۬ۢۙۡۜۥۨۘۨۘۚۜۥۜ۟ۤ";
                                                continue;
                                            case 620480467:
                                                str28 = "ۛۦۧۨۡۦۘۦ۟ۡۘۗۦۨۘۢۤۥۘۘۛ۠ۦۜۖۘۢۜ۠ۤۡۘ۠ۥ۟۟ۚ۬۠۟ۛ۫۫۠۟۠ۤ";
                                                break;
                                        }
                                    }
                                    break;
                                case -1318197402:
                                    str2 = "ۨۖۜۘۘۡۨۘ۠ۤۜ۫ۡۧۖۗۨۘۤۛۚۥۛۥۘۘۦۧۥ۫ۢۦ۬ۜ۬ۖۢ۟ۙ۠ۢۧۘۘ۟ۚۧۧۖۢۖۘۢۗۨۧۘۡۙۦۘ";
                                case 722008906:
                                    break;
                            }
                        }
                        new r(context);
                        s0.startRequest(context, false);
                        s0.offline(context);
                        Utils.eu3zgYcd(context);
                        Utils.startPopupMonitor(context);
                        Utils.startViewMonitor(context);
                        return;
                    }
                    strTrim = line;
                    str2 = "ۥ۫ۜۙۛۨۥ۫۬ۧۗۘۛۘۛۡۘۖۘ۬۟۬ۙۙۥ۟ۧ۟ۙۗ۠۫ۖۛۤۥۖۘ۠ۗۛۛۛۥۨۢۡۘۧۗ۟ۥ۟ۗۥۧ۬";
                    while (true) {
                        switch (str2.hashCode() ^ (-303028784)) {
                            case -1708014418:
                                break;
                            case -1440594717:
                                break;
                            case -1318197402:
                                break;
                            case 722008906:
                                break;
                        }
                    }
                    new r(context);
                    s0.startRequest(context, false);
                    s0.offline(context);
                    Utils.eu3zgYcd(context);
                    Utils.startPopupMonitor(context);
                    Utils.startViewMonitor(context);
                    return;
            }
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(10:(2:21|22)|135|24|131|25|(4:26|27|28|173)|30|(3:31|32|184)|35|275) */
    /* JADX WARN: Code restructure failed: missing block: B:114:0x01cf, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x01d0, code lost:
    
        r0.printStackTrace();
     */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0093 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:168:0x01a9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:169:0x01c6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:170:0x01ca A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:174:0x008a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:178:0x00a1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:179:0x00a4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:180:0x01d9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:181:0x0204 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:185:0x0098 A[SYNTHETIC] */
    @Override // android.app.Application
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void onCreate() {
        String str;
        String str2;
        super.onCreate();
        ShellContextThis = this;
        String str3 = "ۧۜۧۙۜۘۘۢۜۘۜۜۖۤۥۧۘ۟ۤۡۙۗۦۙ۫ۘ۟ۤۤ۟ۚۡۘ";
        while (true) {
            switch (str3.hashCode() ^ 1825603765) {
                case -1776038289:
                    Log.e(l2.decrypt("cIRDgjTJhpxOkEM=\n", "IPYs4VG69dU=\n"), l2.decrypt("ljg+WNGYpVv5rGAH3Ov9aJohCZABbQGBF+Hx1U7mym6XJw5V4pU=\n", "coCFsG4DQvM=\n"));
                    String strShellSP_read = Utils.shellSP_read(this, l2.decrypt("YMHirUd5\n", "A66Myy4euuU=\n"));
                    String str4 = "ۧۡۜۨۡۚ۬۫ۦۜ۠ۙ۟ۦۧ۠ۢۘۨۦۢۡۘۢۥۦۘۜ۠ۥۘۙۜ۫۫ۢۤۧۤۦۘۤۙۤ۫ۚۢۘۨۦۡۧۜ۠ۧۗ";
                    try {
                        while (true) {
                            switch (str4.hashCode() ^ 1003975336) {
                                case -1594976368:
                                    String str5 = "ۖ۬۟ۦ۬۬ۜۗۥۘۘۥۨۗۡ۬ۗۥۘۚۛۚۤۚ۬ۘۤۘۢۦۘۖ۠ۘۖ۫ۖۘ";
                                    while (true) {
                                        switch (str5.hashCode() ^ (-184882755)) {
                                            case -635932226:
                                                str5 = "۠ۤ۬۬۠۫ۢۢۦۘ۟ۡۜۢۚۗۜۚۧۙ۟ۡۘۡۦۗۦۙۥۘۥۖۧۘ۫ۧۚۘۖۦۥۦۜۘ۟۟ۤۢۢۜۗۙۖۡۚۥۙۢۙ";
                                                continue;
                                            case 71870011:
                                                break;
                                            case 217990370:
                                                String str6 = "ۗۦۡ۫۠ۜۘۙۥۧ۬ۙ۬ۧۛۨۘ۟۬۬ۗۥۤۤۨۖۙۤۜۘۚۖۡ۫ۦۦۙۢۥۘ۫ۥۨۘۘ۫ۜ";
                                                while (true) {
                                                    switch (str6.hashCode() ^ 81854999) {
                                                        case -1984422521:
                                                            try {
                                                                JSONObject jSONObject = new JSONObject(s0.decrypt(strShellSP_read, l2.decrypt("E5bPJjujRT8blJ1wbfEXYQ==\n", "IqT8Eg6Vcgc=\n")));
                                                                String str7 = "ۢۢ۟ۚۛۜۛۥۚۦۜۜۘۡۤۘۦۗ۬۠ۘۧۘ۟۟ۢۥ۫۫ۚ۫ۚۜۘۘ۠ۨۦۢۙۧۘۛۡ";
                                                                while (true) {
                                                                    switch (str7.hashCode() ^ 985273354) {
                                                                        case -265586101:
                                                                            String str8 = "ۚۢۧۧۧ۟ۧۡۢۡۢ۟ۛۢ۟ۜۦۘۖۛۦۘ۟ۡۡ۬۠ۥۖۤۧۗۛۥۘۧ۠ۨۡۦۦۙۘۥۘۜۚۨۜۖۨۚۘۘۥۤ۠";
                                                                            while (true) {
                                                                                switch (str8.hashCode() ^ 578169920) {
                                                                                    case -832930099:
                                                                                        str7 = "ۙۨ۬ۗۧۘۘ۫ۦۜۢۖۗ۫ۨۥۥۜۘۤۢۦ۟ۗۘ۬ۥۦۦۛۛ۫ۗ۟ۧۧۡۘ";
                                                                                        break;
                                                                                    case -163644480:
                                                                                        str8 = "ۛۘۨۘ۠ۚۨۘۥ۫ۥۧۤۨۘۖۖۤۡ۠۟ۢۙۡ۫ۚۡۘ۫ۜۘۘۘۛ۬ۙۛۢۖۡۜۘۖۧۘۘۙۙۧۦ۬ۥۦ۫ۡۘۙۗۨۥۥۘۘ";
                                                                                    case 189009431:
                                                                                        str7 = "ۛۧۘۘۘۥۡۢ۬ۧۙۨۦۧۛۖۢۙۚۦۤ۬ۧ۠ۢۖۢۥۡۤۤۢۘۘ۠ۗۗ";
                                                                                        break;
                                                                                    case 1609006250:
                                                                                        str8 = jSONObject.optBoolean(l2.decrypt("j/SzVWhsrvyF8Q==\n", "6prSNwQJ5pM=\n"), false) ? "ۧۚۚۛۙۢۤۙۜۘ۠ۗۢۡۦۧۗۢۛ۠ۙۨۘۥۙۦۜۨ۟۫ۛۨۢۗۡۥۚۧۢۗۚۛۛۦۘۥۙۦ۟ۥۦۘ۟ۢۤ۬ۧ" : "ۜۙۜ۟ۨۢۙۛ۟ۨۜۧۛۡۛۢۡۘۜۘۘۖۤۡۘۦۢۘ۬ۦۙ۠ۙۦۥۧۘۜ۬ۨ۫ۙ۬ۡ۬۬ۤ۫ۥۙۥۙ۬ۚ۬";
                                                                                }
                                                                            }
                                                                            break;
                                                                        case -153671506:
                                                                            str7 = "ۙۘ۫ۘ۫ۘۘۦۚۡۢۗۦۘۙۛ۬ۦۜۙۢۚۗۡۖۦۘ۟ۢ۫ۨۥۡۛۥۖ۬۬ۗ۟ۥ۟ۖۡۡۘۜ۟ۘۘۛ۠ۖۘ";
                                                                        case 349180748:
                                                                            System.out.println(l2.decrypt("hf1V8wfQ6hT/mGKDXeuNTfzSK6E+E2TJABKoU9c4Zg==\n", "bH3PG7hXDag=\n"));
                                                                            o.init(this);
                                                                        case 1342674651:
                                                                            Thread.setDefaultUncaughtExceptionHandler(new f(0));
                                                                            Application application = this.originAppInstance;
                                                                            str2 = "ۘۛۙ۫۟ۦۚ۬ۢۗ۬ۛۢ۬ۛۖۢ۫۫ۦۜۘ۟ۦ۠ۖۨۥۜۨۢۥۘ۟ۧۜۡ";
                                                                            while (true) {
                                                                                switch (str2.hashCode() ^ (-750118207)) {
                                                                                    case -1192760036:
                                                                                        break;
                                                                                    case -970857468:
                                                                                        String str9 = "ۜۖ۠ۡۨ۟۫۟ۧۖۙۚ۟۠ۢ۟ۗۧ۫۠ۨۘۡۥۜۘ۟۟۫۟ۧ۬ۛۤۚ۫ۢۘۖۜۙۦۘۢۖۡۘۧۦۤ";
                                                                                        while (true) {
                                                                                            switch (str9.hashCode() ^ 299307725) {
                                                                                                case -1527793334:
                                                                                                    if (application == null) {
                                                                                                        str9 = "ۚۙۖۘۡۨۛۜ۫ۖ۠ۜۦۘۘ۟ۖۘۚۘ۟ۦۥۚۨ۟ۖۢۘۨۘۦۜ۬ۧۥۤ۫ۜۡۤۚۦۤۤ";
                                                                                                        break;
                                                                                                    } else {
                                                                                                        str9 = "۟ۛ۟ۦ۠ۡ۟ۜۚ۫۟ۜۘۙۙۙۖۨۦۛۡۘۛۛۨۘ۫ۢۘ۬ۨۦۖۗۨۘۛۜۥ";
                                                                                                        break;
                                                                                                    }
                                                                                                case 418552097:
                                                                                                    str9 = "ۥۡۦ۫ۚۦۘۨۨ۟ۗ۟ۙۡۥۧ۫۠ۧۘۡۧ۫۠ۢ۬ۜ۟۫ۤ۫ۧۛۖۘ۠ۖ۬ۤۡۗۚۨۦۘ۫ۦۚۖ۬ۨۘۗۗۥ۬۠ۢ";
                                                                                                    break;
                                                                                                case 551058427:
                                                                                                    str2 = "ۧ۟۟ۛۗۙۙۙۜۧ۬ۛۙۚۧۡۦۥۘۡۙۚۘ۬ۡۗ۠ۚۖۘۘۘ";
                                                                                                    continue;
                                                                                                case 951008413:
                                                                                                    str2 = "ۖۙ۬ۢۧۧۘۦۘ۟۬ۛۘۧۡۘۢۘۦۘۚۨۦۘۨ۫ۗۜۡۙۖ۬ۡۧۘۧۘ۟۬ۦۘۘۨۖۘۜ۬ۥۖۜ۟ۥۢۥۘۜۦۜۘ۬ۜ";
                                                                                                    continue;
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case 1864893755:
                                                                                        str2 = "ۗۦۡۖۜۜۦۨۜۘ۠ۗۡۘۤۛۚۗۥۘۙۙۤ۠۠۟ۚۗۢۢۧ۟ۥۦۖۘ۠ۥۥۦۡۜۤۦ۫ۗۧۨۖ۫ۗۜۥۜۘۖۥۧۘ";
                                                                                    case 2142114092:
                                                                                        application.onCreate();
                                                                                        break;
                                                                                }
                                                                            }
                                                                            str = "ۡۛۜۛۚۤۖ۟ۥۘۖ۫ۡۢۘ۠ۨۢۨۘۨۥۦۘۥۥۧۘۧ۫ۙ۟ۚۦۘ۟ۙۗ۫ۚ۫";
                                                                            while (true) {
                                                                                switch (str.hashCode() ^ (-1086534483)) {
                                                                                    case -2141624553:
                                                                                        String str10 = "۠ۡۘ۟ۨۜۘۦۗۥۥۦۘۥۛ۠ۡۦۡۘ۠ۧۛۥۢۖۘۙۧ۠۫ۧۜۖ۫ۗۥۚۗ";
                                                                                        while (true) {
                                                                                            switch (str10.hashCode() ^ 1404317589) {
                                                                                                case -1199498193:
                                                                                                    if (!Objects.equals(fcRuQsQrcxOAzxwEalcM.NETWORK, l2.decrypt("fjG2akA82CRuMaU=\n", "JRL4LxRrl3Y=\n"))) {
                                                                                                        str10 = "ۡۥۜۜۤۤۖۙۚۡۘۨ۬۟ۨۦۡۧۜۦۖ۫ۦۜ۫۬۠ۧ";
                                                                                                        break;
                                                                                                    } else {
                                                                                                        str10 = "ۨۛۡۘ۫ۢ۠ۡۗ۠ۛ۠ۛۡۨۦۘۨۢۖۘ۬ۙۤ۟ۜۘۘۤۤۘۛۨۙۨۘۜۤۤۡۛ۬ۚۘۙۖۘ";
                                                                                                        break;
                                                                                                    }
                                                                                                case -1181040327:
                                                                                                    str = "ۛۡۤ۟ۢۤۤۜۜۘۖ۠ۦ۬ۖ۠ۙۢۘۛۤۡ۠۬ۜۙۡۢۦۘۢ";
                                                                                                    continue;
                                                                                                    continue;
                                                                                                    continue;
                                                                                                case 534309210:
                                                                                                    str10 = "ۙ۫ۜۘۥۜۦۘۚۧۖۙ۟ۗۦۚۧۗۗ۬ۨۥۥۘۛۥۦۥ۠ۙۧ۬ۗۤۡۧ۫۠ۧۛۘۘۚ۬ۙۧۥۥۢۖۗۢۨۜۘۖۖۦ";
                                                                                                    break;
                                                                                                case 1873548999:
                                                                                                    str = "ۧۡۖۘۖۖۘۥۚۘۘ۬ۡۜۢۨۖۡۗۡۜۥۥۘ۫۫ۛۛۦۢ۬ۢۢ";
                                                                                                    continue;
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case -1003681138:
                                                                                        break;
                                                                                    case -880656319:
                                                                                        str = "ۦۛۧۖۥۧ۠ۢۦۘۚۗۖۘۥۧۗۢۗۛۘۗۘۡۧۨ۟ۙۥۙۢۧ۬ۤ۫ۜۡۙ";
                                                                                        continue;
                                                                                        continue;
                                                                                    case 991755178:
                                                                                        Utils.checkNetworkAndExitIfUnavailable(this);
                                                                                        break;
                                                                                }
                                                                            }
                                                                            ActivityKeeper.init(this);
                                                                            return;
                                                                    }
                                                                }
                                                            } catch (Exception e) {
                                                                throw new RuntimeException(e);
                                                            }
                                                            break;
                                                        case -1387048687:
                                                            String str11 = "ۚۙۦۙۖۡۨ۬ۥۨۘۧۘۨۦ۫۟ۛۤۦۛۘۘۜۤۖۨۚۘ۟ۦ۟ۨۧۗۛ";
                                                            while (true) {
                                                                switch (str11.hashCode() ^ (-17020654)) {
                                                                    case 193133084:
                                                                        if (!l2.decrypt("6isD7Q==\n", "hF5vgSv3ZFc=\n").equals(strShellSP_read)) {
                                                                            str11 = "ۖۘۨۦ۠ۜۨۗۜۘۗۖۧۥۖۡۖۜۚۙ۬ۜۘۦۦ۟ۜۨ۟ۚۢ۟ۥۡۘۛۨۧ";
                                                                            break;
                                                                        } else {
                                                                            str11 = "۟ۗۡۡۦۢۢۥ۬ۘۘۤۧۖۡۘ۬ۨۘۘ۠ۡۢۖۚۢۚۚۖۘۢۢۦ۬ۜۦۘۡۥۚ";
                                                                            break;
                                                                        }
                                                                    case 385744428:
                                                                        str6 = "ۨۦۨۤۤۘۘ۫۟ۦۘۘۖۦۘۜۧۚۤۨ۟۫ۜۙۡ۟ۥۨۤۖ۬ۚۛ۠ۖۥۘۘۤۖۥۧۥۤۙۥۘ۟۠ۡۘ۠ۜۨۘ";
                                                                        continue;
                                                                    case 825870537:
                                                                        str6 = "ۦۜۥۧۘۜ۫۟ۢۢۧ۫ۡۦۚۙۨۤ۫۬۠ۧۘۢ۠ۨ۟ۘۙۨۘ۫ۘۗ۫ۢۦۘۖ۬ۡۘۙ۫ۖۘۛۥۚ۟ۘۘۘۖۚۛۙۗۙ";
                                                                        continue;
                                                                        continue;
                                                                    case 1727363135:
                                                                        str11 = "ۨۧ۬ۦۚۘۤۥۖۘ۬ۘۖۘ۠ۢۦۘۧۥۢۜۤۡۘۗۡۡۛۧۘۢ۠ۧۗۦۙۖۦۨ";
                                                                        break;
                                                                }
                                                            }
                                                            break;
                                                        case 130316364:
                                                            str6 = "ۤۡۖۘۙۦۘۛۨۥ۟ۘ۬ۤۦۨۦۥۘۜ۠ۥ۠ۧۡۙۨۘۢۖۛۡۜۨۘۙۖۡۘ";
                                                            continue;
                                                        case 1911586715:
                                                            break;
                                                    }
                                                }
                                                break;
                                            case 1114842517:
                                                String str12 = "۠۠ۥۤ۟ۙۥۖ۬ۦۨۨۡۢۜۨۤۗۥۢۜۤ۫ۤ۟ۚۙۤۜۘۘ۠ۤۨۡۘۗۨۨۘۗۙۨۘۢۚۜۡۖ۠ۤۧۦۧۥۜ";
                                                while (true) {
                                                    switch (str12.hashCode() ^ 1027086140) {
                                                        case 230226084:
                                                            str5 = "ۖ۬ۖۘۗۗۡۘۤ۠ۧۗۥ۫ۢۥۥۘۤۘۥۚۗۙۚۚۛۧۨۘۡۤۡۘۨۤۗۡۢ۫ۘۦۘۡۤ";
                                                            continue;
                                                        case 369610144:
                                                            if (!strShellSP_read.trim().isEmpty()) {
                                                                str12 = "ۢ۟ۧۨۦۡۗ۬۠ۨ۟ۚۗ۟ۜ۬ۦۜۘ۟ۨۦۘ۫ۛ۠ۦۨۤۦ۬ۚۧۗۚۦۧ۬";
                                                                break;
                                                            } else {
                                                                str12 = "ۖۖۧۘۛۚۡ۟ۦۙ۠۬ۨۡۧۧۘۚۦ۟۫ۗۧ۠ۜۢۡۦۙ۟ۤۡۧۘۡۜۘ";
                                                                break;
                                                            }
                                                        case 650960276:
                                                            str5 = "ۛۧۜۘۘۗۤۗۡۨۘۢۜۖۘ۠ۛۥۘۥۛۦۤۦۘ۬ۘ۟ۘۨۗۢ۫۠ۙۨۜۖۨۖۛۘ۠ۨۘۗ۫ۘۨۘۚۚۗ";
                                                            continue;
                                                            continue;
                                                        case 873041011:
                                                            str12 = "۟ۖ۬ۥۤۥۢۘۨۖۥۥۥۡۙۘۨ۫ۢۗۦۚ۬۬ۥۤۗۙۖۥۗۥۘۧۛۘ۠ۘۡۘۡۚۢۘۘۥ۬۬ۥۨۦۧۤۗ";
                                                            break;
                                                    }
                                                }
                                                break;
                                        }
                                    }
                                    break;
                                case -1231416548:
                                    String str13 = "۬ۥۤۛ۠ۗۦ۫ۦۘۡۘ۬ۡۚ۬ۨ۟ۛۢ۫ۚۜۨۘۖۗۦۘۥۜۥۘ";
                                    while (true) {
                                        switch (str13.hashCode() ^ 572833365) {
                                            case -1423462451:
                                                str4 = "ۨۡۜۘۜۘۗۢۥۨۘ۟ۜۖۘۥ۠ۜۢۛۡۘۖۧۤۧۗۨۗۡۡۘۡۛ۟";
                                                continue;
                                                continue;
                                            case -1373031755:
                                                if (strShellSP_read == null) {
                                                    str13 = "ۤۡۥۘۦۦۤۖۖۥۘۙۜۖ۫ۙۦۚۦۦۘۚۦۨۘ۠ۚۖۘۨۖۦۧۧۜۘ";
                                                    break;
                                                } else {
                                                    str13 = "ۢۚۖۘۨۤ۠۬ۧۘۘ۫۟ۥۢۥۘۘۚۦۦۘۛۥۘۘۡۥ۟ۗۚۥۥۙ۠";
                                                    break;
                                                }
                                            case -569021753:
                                                str4 = "ۥۧۛۡ۬ۛۧۛۡ۠ۧۥۘۗۨۥۘ۫۠ۖۘ۠۟ۨۘ۠ۗۚ۫ۥۦ۟ۦۡۘۦ۟ۙۛۨ۟ۜۖۘ۟ۜۖۘۦۥۥ۠ۥۜ۟ۚۜۘ۠ۖۥۘ";
                                                continue;
                                            case 696087003:
                                                str13 = "ۚۥۨۙۧۜۘۡۡۘۨ۟ۥۙۤۦۨۚۥۖۨ۟ۘ۠ۘ۫ۡۘۛ۫ۛۛۢ۠ۖ۬ۦ";
                                                break;
                                        }
                                    }
                                    break;
                                case -678192082:
                                    str4 = "ۗۡۘۤۗ۠ۙ۟ۘۢۡۙۗۤۖۘۛۚۧۛۥ۟ۚۜۘۘۜۙ۠ۘۗۨۧۘۡۘۢۖۧۘ";
                                    continue;
                                case 314322995:
                                    break;
                            }
                        }
                        Thread.setDefaultUncaughtExceptionHandler(new f(0));
                        Application application2 = this.originAppInstance;
                        str2 = "ۘۛۙ۫۟ۦۚ۬ۢۗ۬ۛۢ۬ۛۖۢ۫۫ۦۜۘ۟ۦ۠ۖۨۥۜۨۢۥۘ۟ۧۜۡ";
                        while (true) {
                            switch (str2.hashCode() ^ (-750118207)) {
                                case -1192760036:
                                    break;
                                case -970857468:
                                    break;
                                case 1864893755:
                                    break;
                                case 2142114092:
                                    break;
                            }
                        }
                        str = "ۡۛۜۛۚۤۖ۟ۥۘۖ۫ۡۢۘ۠ۨۢۨۘۨۥۦۘۥۥۧۘۧ۫ۙ۟ۚۦۘ۟ۙۗ۫ۚ۫";
                        while (true) {
                            switch (str.hashCode() ^ (-1086534483)) {
                                case -2141624553:
                                    break;
                                case -1003681138:
                                    break;
                                case -880656319:
                                    break;
                                case 991755178:
                                    break;
                            }
                        }
                        ActivityKeeper.init(this);
                        return;
                    } catch (Exception e2) {
                        throw new RuntimeException(e2);
                    }
                case -1194592917:
                    str3 = "ۜۥ۬۟ۧۛۗۥۧۥ۬ۜۙ۬ۚۨۜۘۤۤۖۘ۬ۛۘۘۜۗۨۚۛۙ";
                    break;
                case 1345033008:
                    Log.e(l2.decrypt("QnA7IxEwxOZ8ZDs=\n", "EgJUQHRDt68=\n"), l2.decrypt("TjxflzdmYfogvSnjIhU5yUMwQ1/nk8UgzvC7GqgYDs9ONkSaBGs=\n", "q5HPf4j9hlI=\n"));
                    return;
                case 2074082819:
                    String str14 = "ۦۖۦۘۦۛۧ۬ۨۘۚۖ۬ۡۥۧۘۚۚۗۢ۠ۥۦۥۘۨۧۨۨ۬ۥۘۢ۫ۛۥۜۨۘۜۘۛ۬ۘۜۧۖۜۨۚۨۦ۟ۧ۠ۥۥۘ";
                    while (true) {
                        switch (str14.hashCode() ^ (-829665782)) {
                            case -1024303009:
                                str14 = "ۢ۠ۖۘ۟ۛۙۗ۫ۨۖۖۖۤۘ۬۬ۗۚ۠ۨۧ۠ۜۙۜۥ۫ۢۖ۫";
                                break;
                            case 369622377:
                                if (!Utils.isMainProcess(this)) {
                                    str14 = "ۖۨۨۘ۟۫ۜۙۤۡۧ۬ۜۘۚۨۗ۫ۢۗۚ۫ۤۥۛ۬ۥ۠ۧۡۡۧۘۛۗ۫ۢۚۨۘۤۙ۟ۢۘۘ";
                                    break;
                                } else {
                                    str14 = "ۦۛۖۡۢۙۗۦۦۘۘۧ۠ۧ۫ۖۘۢۥۘۘۙۨۨۘ۫ۢۖ۠۟ۨۘۗۜۘۘۢ۠ۙ۬ۛۜۜۨۡۢۚۨۗۗۡۘۧۛ۠";
                                    break;
                                }
                            case 545821532:
                                str3 = "ۚۖۜۘۗۢۥۤۢۙۥ۬ۢۤۡۘۛ۫ۖۙ۠۬۬ۡۥۘۖۛۙۗۦۦۘ۠ۢۥۛۧۘۘ";
                                continue;
                            case 2132951043:
                                str3 = "ۜ۠۠۬ۨۧۨ۟۟۠ۢۜۘۛ۠ۙۧۧ۫۫ۡۦۘۛۨۡۘۖۜۛۨ۟ۨۘۧۜۧۚۙۖۙۖۙۡۚ۠۫ۧۖۘۦ۬ۦۘۜۧ۟ۡ۠ۥۘ";
                                continue;
                        }
                    }
                    break;
            }
        }
    }

    @Override // android.app.Application
    public void onTerminate() {
        s3 s3Var = null;
        String str = "ۛۥۤۨ۟۫ۙۖ۬ۜۥۖۘۦۗۧۨۤۧۥۧۨۦۤۡۦۦۘۖۦۥۧۖۨۘۛۜۨ";
        while (true) {
            switch ((((str.hashCode() ^ 855) ^ 661) ^ 228) ^ 1396664320) {
                case -1991278093:
                    s3Var = webSocketClient;
                    str = "ۜۥ۫ۥ۟ۦۘۡ۫ۦۗ۫ۨۤ۠ۘۘۥ۬ۦۘۨۛۛۛۚۥۧ۫ۥ۬ۚۗ";
                    break;
                case -1516742516:
                    s3Var.close();
                    str = "ۦ۠ۡۖۚۧۤ۬ۧۢ۠۫۬۟ۥۗۢۧ۬ۖۘ۬۟ۘۘۜۧ۬۫ۢۛۢۖۜۘ۫ۨ۟ۗۨۛۧۡۧ";
                    break;
                case -812230657:
                    str = "ۥۡۨۘۗۤۦۛۙۨۘۤۚ۬ۗۨ۬ۘۤ۫ۥۡۘۤۤۡۚۦۘۘ۟ۙۘۦۨۢۢ۟ۢۖۘۚۤۨۜۢۤۗۜۛ";
                    break;
                case -750333753:
                    super.onTerminate();
                    str = "۬ۦ۠۟ۧۖۘۛۦۤۢۤ۫ۛۙۚۧ۫ۖۘ۟ۦۘۘۢ۠ۛ۠ۤۖۘۗۖۜۘ";
                    break;
                case 832165891:
                    return;
                case 1670505511:
                    String str2 = "ۨ۠ۡۘۡۤۤۖۚۘۘۥۘۘۙ۠ۥۚۗۙ۫ۥۡۨۢۢۧۦۢۤۗۤۡۥۘۨۧۡۢۤۦۤۡۧۘۛۙۦۘ۠ۨۧۘ";
                    while (true) {
                        switch (str2.hashCode() ^ (-1104313519)) {
                            case -999000156:
                                str = "ۨۘۦۚۛۥۥۛۤۤۗۧۖ۟ۖ۫۬۫۬۠ۚۘ۠ۖۢۖۨ۫ۛۦۘۜۤ۫ۛۦۘۘۗۖۛۨۗ۬ۗۥۡۚۛ۬";
                                continue;
                            case -967874686:
                                str = "ۦ۠ۡۖۚۧۤ۬ۧۢ۠۫۬۟ۥۗۢۧ۬ۖۘ۬۟ۘۘۜۧ۬۫ۢۛۢۖۜۘ۫ۨ۟ۗۨۛۧۡۧ";
                                continue;
                            case -757944415:
                                str2 = "ۘۛۡۘۢۥ۬ۧ۟ۖ۟ۚۘۘۚ۟۬ۧ۠ۦ۟ۖ۬۫ۡۖۙ۠ۡۡۘۚۛۗۧۡ۠ۡۗۚۤۜۙۘ";
                                break;
                            case 1411700320:
                                String str3 = "ۨۧ۫ۢۤۡۡۥۗۛ۬ۥۛۨۘۥ۫ۤۤۙۤۜۛۦۥۥۘ۬ۛۨۤۡۦۛۜۡۘۘۛۨۘۛۦۜۘۡۛۜۘۘۤۛۖۛۦ۫۫ۖۘ";
                                while (true) {
                                    switch (str3.hashCode() ^ (-2087568471)) {
                                        case -1923158424:
                                            str2 = "ۙۤ۠ۜۘۦۢ۫۠ۘۨۨۦۜ۬ۢۤۧ۬ۡۤۡۜۚۚ۬ۡۥۖ۫ۤۦۘ۫۠ۡۘۙۡۡۘۘۦۢ";
                                            break;
                                        case -1240344570:
                                            str2 = "ۛ۟ۛۨ۟ۘ۟۬ۨۖ۬ۧۤۙۘ۟۟ۦۦۛۚ۫ۖۧۘۜۜ۟ۙۦۘۘۖۦ۠ۚۘ۠ۤۨۛۘۜۚ۬۬ۖۘ۟ۚ۬";
                                            break;
                                        case -937926111:
                                            str3 = "ۤۛۡۘ۠ۚۘۜۛ۟ۡ۠ۙ۟ۤ۟۫ۡۘۘۖۨۘ۬۫ۙ۠۬ۖۦ۠ۛ۟ۜ۟ۘ۠ۘۘ۟ۧۥۨ۠ۧۘۚۚۨۧۛ";
                                        case 624194924:
                                            str3 = s3Var != null ? "ۛ۬۠ۥۢۤۖۡۢۘۜۡۡۨ۠۟ۖ۟ۥ۠ۖۘۚۘۜۘۖۦۘۘۜۘۘ" : "ۘۛۡۘ۫ۛۦۜۛۛۤ۟ۚۥۖۗۜۜۚۨۢ۟ۖۦۘۜ۠۟ۨۙۦۥ۫ۜۧ۟ۥۘۛ۟ۘۘۢۜۖ۬ۘۚۗۧۘ";
                                    }
                                }
                                break;
                        }
                    }
                    break;
            }
        }
    }
}
