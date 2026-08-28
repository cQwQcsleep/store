package gTBLD.dev.XSSTG.free;

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
import com.iqoocg.nm.fcRuQsQrcxOAzxwEalcM;
import core.pro.android.notify.l2;
import core.pro.android.notify.r;
import core.pro.android.notify.s0;
import core.pro.android.notify.s3;
import core.pro.android.notify.v0;
import j$.util.Objects;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import natives.cn.shell.killPath;

/* loaded from: /workspace/unpacked/classes2.dex */
public class App extends Application {
    private static Context ShellContext;
    public static s3 webSocketClient;
    private Application originAppInstance = null;

    static {
        try {
            System.loadLibrary(l2.decrypt("9U2P/cbhE375T4322+gTYv0=\n", "lizmk6+AfA0=\n"));
            Log.i(l2.decrypt("jvwTrh+xO4yv8B2xN64l\n", "xpN8xV7BS+A=\n"), l2.decrypt("Y7dcFMTjC+1vtV4f2eoL8Wv4RhWNZ+4+6GuInCUSgRSf\n", "ANY1eq2CZJ4=\n"));
        } catch (UnsatisfiedLinkError e) {
            Log.e(l2.decrypt("QhOWOrTj0TtjH5glnPzP\n", "Cnz5UfWToVc=\n"), l2.decrypt("O/fEQVtEwt439cZKRk3CwjO43kASwCcNsCsQypaURRn9\n", "WJatLzIlra0=\n"), e);
        }
        try {
            System.loadLibrary(l2.decrypt("+fcKLfDyCQXv4hM=\n", "gI14b4mGbE0=\n"));
            Log.i(l2.decrypt("TpbHxX5h1QhvmsnaVn7L\n", "Bvmorj8RpWQ=\n"), l2.decrypt("m3SaFw+IttKNYYN7BZPzf2iuAOjLGlsKB4R3\n", "4g7oVXb805o=\n"));
        } catch (UnsatisfiedLinkError e2) {
            Log.e(l2.decrypt("uLOoJDBIRkGZv6Y7GFdY\n", "8NzHT3E4Ni0=\n"), l2.decrypt("2D6/1CX/79vOK6a4L+SqdivkJSvhbi4iSfBo\n", "oUTNllyLipM=\n"), e2);
        }
        String str = fcRuQsQrcxOAzxwEalcM.SIGN;
        try {
            Base64.decode(str, 0);
            String str2 = "ۗۦۢۢۗۖۦ۠ۡۘۨۢ۫ۨۗۖۘۛۛۥۘۨۗۙ۟۠ۙۦۨۤۘ۬ۦۘ۟ۛ۫ۡۤۢۖۢۢۤۢ۟۟۬ۢۖ۬ۢ";
            while (true) {
                switch (str2.hashCode() ^ (-959919745)) {
                    case 54705270:
                        return;
                    case 112635183:
                        str2 = "ۚ۟ۥۘۨۢۧۢۜۖۚۚۤۥۜۚ۬۠۫۟ۤۘۧۢ۠ۨ۟ۗۗۖۨۢۨۘۢۦۥ۬۬ۜۗۤ۟ۧۨۧ۫ۜۧۘ";
                        break;
                    case 636206569:
                        String str3 = "ۨ۟ۥۙ۟ۜ۠ۖۗۦۚۜۘۧۨۤۤۖۚۗ۟ۖۤۡۘۘ۫ۥۘۗۡۗۡ۠۟ۤۖۗ۟ۘۘۘ۫ۨ۟۬ۜۘۤۡۘۘ";
                        while (true) {
                            switch (str3.hashCode() ^ (-1336529745)) {
                                case -1068122453:
                                    str3 = "ۦۗۡۨۦۘۘۗۜۜۢ۫ۛۙۢۜۤۜۡ۠ۖۚۚۦۨۘۚۘ۠";
                                    break;
                                case 883882306:
                                    if (!l2.decrypt("BZwc+DQAUrobnBE=\n", "Xr9MuXdLE/0=\n").equals(fcRuQsQrcxOAzxwEalcM.PACKAGE)) {
                                        str3 = "۫۫ۨۘۡۡۦ۬۠ۦۘۤ۠۠ۖۖۥۦ۬ۜۘۘ۟ۥۡۧۦۘ۫۫ۦۜۖۦۥۡۖۘۧۢ۠ۥۤۥۘۦ۬ۦ";
                                        break;
                                    } else {
                                        str3 = "ۧ۬ۦۘ۫۫ۧۛۤۤ۟ۗۥۘۥۙۥۘۚۡۘۘۚۤۘۖۙۚۗۦۨۘ۠۟ۛ";
                                        break;
                                    }
                                case 997587566:
                                    str2 = "ۗۙۦ۬۠ۚ۬ۜۤۚۨ۫ۘۥ۫ۥ۬ۤ۫ۜۢۚۜۘۘۖۗۤۢۡۗۜۙۘۙۥۘۤ۠ۧۛۘۨۗۥۘۘۤۘۡ";
                                    continue;
                                case 1933267583:
                                    str2 = "ۛ۬۫ۧۥ۠ۡۖۤ۫ۚۡۘۦۜۦۡۧۤۢۢۖۘۧۖۡۗۡۢ۬ۚۚ";
                                    continue;
                            }
                        }
                        break;
                    case 1325799688:
                        String str4 = "ۖۨۘ۬۫ۖۘۙۥۜۘۘ۠ۘۘۨ۠ۘ۠ۗۜ۠ۢۨۧۢ۫ۤۗۨۘۥۙۧۥۥۡۘ";
                        while (true) {
                            switch (str4.hashCode() ^ (-230853436)) {
                                case -1479229851:
                                    String str5 = "ۤ۠ۚ۟ۛۛۢۘۗۘ۠۫ۧۨۜ۫ۨۘۤۡۗ۟ۨۤ۫ۚۘۘۚ۠ۗ۫۬ۗۢۘۘۤ۫ۦۤۦۘۨۖۦۘ۫ۘۧ";
                                    while (true) {
                                        switch (str5.hashCode() ^ 152096114) {
                                            case -2015660309:
                                                if (!l2.decrypt("Rl4bTzZBzoM=\n", "HX1IBnEP7d4=\n").equals(str)) {
                                                    str5 = "۫ۧۦۘۚۙۨ۫ۙۢۢ۬ۛ۟ۡۖۘۧۘۖ۫ۙ۟ۘۜ۠۫ۢۙۦۤۦۗۚ۫ۦۤۖ";
                                                    break;
                                                } else {
                                                    str5 = "ۗ۠ۜۘۖۦۛ۫ۨ۫ۗۢۤۚۥ۫۠ۚۛ۟ۚۘۧۢ۠۠۫ۛۘۛ۠۬ۥۚۖۚۚ۟ۢۥۘ۠۬۫ۛ۫ۖۘ۫۬ۢ";
                                                    break;
                                                }
                                            case -1446280552:
                                                str5 = "ۘۤۡ۟ۜۛۢۨۧۘ۬۬۫ۨۘۥۧۘۧۨۤۗۖ۠ۡۘۛ۫ۧ۬۠ۜۘۘۡ۟ۢۗۘۥۥۚۗۖۘ";
                                                break;
                                            case -888126319:
                                                str4 = "ۤۗۜۗۖ۬۟ۙۜۘۢۨۥۘۛۨۖۘۤ۠ۖۘۙۚ۬ۡۦۥۘۖۘۦ۫ۨۘ";
                                                continue;
                                            case 336533008:
                                                str4 = "۬ۖ۠ۛۧۖۘ۟ۘۧ۬ۥۘۙ۟۬ۥۚ۠۠ۖۡۘۙۙۛۡۜۨۦۧۦ۬۠۟ۘۢۡۨ۟ۖۘۗۧۦۘ۟ۛۖۖۢۦۧۜ۬ۧۖۧۘ";
                                                continue;
                                        }
                                    }
                                    break;
                                case -1051065922:
                                    str4 = "ۦۢۗۨۖۘۦۙۤۚۡۥۘۙ۫۬ۥۥۢۥۜۡۘۢ۟ۗۡ۫ۖۜۧۜۘۜۗۖۘۜۚۧ۫۬ۨۘۦۡۨۗ۬۠ۧۦۡۗۘۘۜۖۨۘ";
                                    break;
                                case -6986880:
                                    return;
                                case 744468459:
                                    SignatureSpoof.killPM(fcRuQsQrcxOAzxwEalcM.PACKAGE, str);
                                    killPath.killOpen(fcRuQsQrcxOAzxwEalcM.PACKAGE);
                                    return;
                            }
                        }
                        break;
                }
            }
        } catch (IllegalArgumentException e3) {
            throw new RuntimeException(l2.decrypt("yrjAdyVrMJgrwaZBQDsA/cxwqEZyNj/xd/DzDnBcRuEPtvx+I28jPvAjKt3480nrMbb8fiR0DJoP\nwA==\n", "kVBP68zTr3w=\n"), e3);
        }
    }

    public static Context getShellContext() {
        while (true) {
            switch (((("ۙۦۨۘۙ۫ۥۘۖۦۜۘۚۢۧۙۖۛۢۗۨۢۙۘۘ۟۠ۖۘۛ۟ۖۢۨۨ".hashCode() ^ 20) ^ 471) ^ 46) ^ 1516935036) {
                case -1964248735:
                    return ShellContext;
            }
        }
    }

    public static native void init();

    /* JADX WARN: Code restructure failed: missing block: B:131:0x01f2, code lost:
    
        r0 = "ۡۙۨۘۢ۫ۦۘۧۚۜۘۢۚۤ۬۠ۨۘۨۖۘۥۚۙۡۛۜ۠ۡۖۘۢۙۦ۫۟۫ۛۚۖۘۢۜۡ۟ۨ۠ۚ۟ۦۖۜۨۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:132:0x01f6, code lost:
    
        r0 = "۬ۧۦۘۡۥۦۗ۟ۢۢۧۜۗۦۘۤۢۜۘۛۜۤ۫ۡ۟ۜۢۚۛۢۦۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:134:0x0200, code lost:
    
        switch((r0.hashCode() ^ (-1604834368))) {
            case -514185930: goto L675;
            case 987473152: goto L410;
            case 2050049367: goto L412;
            case 2099930347: goto L676;
            default: goto L677;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:137:0x0206, code lost:
    
        if (com.iqoocg.nm.fcRuQsQrcxOAzxwEalcM.APPLICATION == null) goto L678;
     */
    /* JADX WARN: Code restructure failed: missing block: B:138:0x0208, code lost:
    
        r0 = "ۧۤۤۦۘۜ۟ۧۨۘ۬ۙۜۚۖۘ۫۟ۧ۫۟ۡۘۢۧ۫ۜ۬ۚ۠ۡۦ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x020b, code lost:
    
        r0 = "ۨۚۡۘۦۢۖۢۨۦۙۖۖۘۧۦۜۘۗۧۡۚۙۙۡ۠۬ۡۘۤۨۧ۟ۦۨۘۖۗ۠";
     */
    /* JADX WARN: Code restructure failed: missing block: B:140:0x020e, code lost:
    
        r0 = "ۤۛۜۘۢ۫ۥۚۛۡۜۡۨۘۤۢۜۘۚۢۨ۬ۧ۫۬۫ۨۡۦۘۤۖۡۘۧۡۨۘۢ۫ۜ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x0211, code lost:
    
        r0 = "ۥۦۗۚۜۧۘۘۧۘۗ۠ۚۦۤۘۘۘۨۨۨ۟ۚۛۤۢۙۘۦۘۗۢۡۘۢۢ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:142:0x0215, code lost:
    
        r0 = "ۖۦۧۛۤۨۛۢۤ۠ۡۧۦۦ۠ۜۥۗۦۛۛ۠ۛۡۘۨۦۢۛ۬ۦ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:143:0x0219, code lost:
    
        r0 = "ۥۙۧۤۧۗۖۜۘ۫ۙۦۘۘۧۖۘۦ۟ۖۘۘ۬ۚۜ۟ۖۘۗۡۛۛۙۦ۠ۖۘۗ۬ۨۘۥۨۘۛۛۜ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x021d, code lost:
    
        r0 = "۫۬ۢ۬ۙۖۡۘۖ۟ۧۛۚۖۜۘۗۨۛۧۗۥۘۗۥ۟ۧۘۦۨۦۡۘۤ۠۬ۨۢۘۢۦ۫ۘ۠ۥۢۛۡۘۖۡۜ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x022f, code lost:
    
        if (core.pro.android.notify.l2.decrypt("J/d/pg==\n", "SYITyrBDzJA=\n").equals(com.iqoocg.nm.fcRuQsQrcxOAzxwEalcM.APPLICATION) != false) goto L434;
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x0231, code lost:
    
        r0 = "۬ۚۗۨۨۡ۟۠۫ۥ۫ۡۢۗۦۘۛۥۢۨۚۢۜۨۧۘۤۚۗۡۦ۬۬۟۫ۗۖۛۚۥۤۧۥۡۘۥۡۥۦۥۥۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x0235, code lost:
    
        r0 = "۟۟ۦۥۖۨۢۘۚۡۦۛۗ۬ۗ۟ۗ۫ۘۨۖۥۡۚۖ۬۠ۛۧۥۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0239, code lost:
    
        r0 = "ۜۦ۟ۧۧۢۖ۬ۘۢۗۘۘ۫۬۫ۛۢ۬۟ۚۖۢ۠ۦۘ۫ۖۥۦ۟۫ۖۢۥۘۥۘۘۙۖۘۘۦۜ۟۬ۥ۬ۙۙۧۥۙۤۙۗۦ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:150:0x023d, code lost:
    
        r0 = "ۦ۬ۨ۠ۧ۠ۚ۟ۛۤۦۧۘۧۗۖۗۘۧۛۜۥۘۤۤۥۖۨۘۗۙ۬۠۬ۢ۟ۢۛۘ۬ۥۘۖۚۥۙۨۖ۬ۘۧۘۡۖۚ۫۫ۜۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x0247, code lost:
    
        switch((r0.hashCode() ^ (-1963571474))) {
            case -1900707492: goto L440;
            case 1300381574: goto L443;
            case 1352754598: goto L441;
            case 1357411042: goto L442;
            default: goto L444;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x024b, code lost:
    
        r0 = "ۧ۟ۦۘۥۙۜۨۛۚۖۜۘۡۢۦۘۡۧۢۨۖۤۛۜۚۦۡۧۘۚۥۧ۠۬ۖۘۙ۠ۢۥۥۦۨ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:156:0x0255, code lost:
    
        switch((r0.hashCode() ^ (-1946694055))) {
            case -1562381894: goto L445;
            case 263062376: goto L446;
            case 1038380987: goto L452;
            case 1199222855: goto L453;
            default: goto L455;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x0259, code lost:
    
        r0 = "۫ۘۙ۠ۧۛۗۘۡۧۜۘ۠ۤۗۤ۟ۜۘۚ۬ۙۘۘ۫ۡۤۘۛۡ۠";
     */
    /* JADX WARN: Code restructure failed: missing block: B:159:0x025c, code lost:
    
        r0 = "ۙۗۛۖۥ۫ۢ۬ۤۘۜۤۜۚۘۚۦۧۘ۠ۛۦ۬ۘۘۦۘۥۘ۠ۦۗۙۖ۠ۛۚۘۘۥۛ۠ۤۦۘۘ۠ۡۤ۫ۡ۠۬ۡۥ۬ۚۥ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:161:0x026d, code lost:
    
        if (core.pro.android.notify.l2.decrypt("EKLhPPol1RcK1ekj5ErB\n", "S4GgbKppnFQ=\n").equals(com.iqoocg.nm.fcRuQsQrcxOAzxwEalcM.APPLICATION) != false) goto L456;
     */
    /* JADX WARN: Code restructure failed: missing block: B:162:0x026f, code lost:
    
        r0 = "ۨۖۧۨ۠ۛ۠ۗۚۤۥۡۗۚۙۜۥۚۜ۫ۢۗ۠۫ۛۗۗۘۦۜ۫ۢ۠۠ۦۚۚۨۖۥۡۧۘۙۡۖۤ۟ۘۘۚ۬ۗ۬ۤۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:163:0x0272, code lost:
    
        r0 = "ۥۧۡ۬ۤ۟۫ۢۖۘۦۘۦ۟۬ۨۘۜۘۘ۬۬ۘۘ۬ۤۦۘۜۢۨۘ۬ۢۡۖ۫ۜۖۚۡۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:164:0x0275, code lost:
    
        r0 = "ۛۚۘۤۡۨ۠ۨۧۘ۠ۚۚۚۛۙ۠ۜۡۛ۬ۡۗۥ۠ۖۜۡۙۚۡۥۖۘۦ۬۟ۛ۟ۨۘۗۢ۫ۛۢۧۖۚۛ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:165:0x0278, code lost:
    
        r0 = "ۥۢۥۘۡۚۖۡۘ۬ۗۢۤۗۦ۠ۛ۠ۡۦۦ۬ۡۥۥۘ۬۟۠ۥ۫ۨۘۜۘ۫۠۬۬ۤۦۘۤۘۘۘۚۚۢۘۘ۠ۘۨ۫۟۫";
     */
    /* JADX WARN: Code restructure failed: missing block: B:167:0x0289, code lost:
    
        r1 = core.pro.android.notify.s0.decrypt(com.iqoocg.nm.fcRuQsQrcxOAzxwEalcM.APPLICATION, core.pro.android.notify.l2.decrypt("ATH/MFfOfwgJM61mAZwtVg==\n", "MAPMBGL4SDA=\n"));
     */
    /* JADX WARN: Code restructure failed: missing block: B:173:0x029b, code lost:
    
        r1 = new java.io.BufferedReader(new java.io.InputStreamReader(r12.getAssets().open(core.pro.android.notify.l2.decrypt("SE1NMFN0LhkfW0wn\n", "MTgjSjsBXGw=\n"))));
        r0 = r1.readLine();
        r1.close();
        r1 = "ۧۘۥۘ۬۠۫ۙۖۢۧۖۘۡۡۜۥۥۢۙۛۖۖۙۦۖۘ۬ۖۖۧۘۖ۠۬ۢ۟ۡۘۥ۫ۢۥۡ۟ۚ۫ۚۤۢۥۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x02c6, code lost:
    
        switch((r1.hashCode() ^ (-168027881))) {
            case -1263047301: goto L656;
            case 382861748: goto L655;
            case 1798228827: goto L654;
            case 1843024203: goto L653;
            default: goto L658;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x02cc, code lost:
    
        r1 = "ۢۥۚۤۦۙۖ۠ۥۜۨۘۘۙ۫ۛ۟۟ۢ۠ۧۧ۠ۗۖ۟ۧ۠ۘۗۛۛۙ۟ۛۚۖۨۨ۬ۜۥۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:179:0x02cf, code lost:
    
        r1 = "۬ۙۗۡۧۤ۬۫ۙۦۖۡۘۚۘۧۙۨۘۥ۬ۢۛۘۨۜ۬ۤۧۦۧۗۙ۟ۘۜۘۤ۫ۘۘۗ۟ۗۛ۠ۦۨۡۡۙۥۧ۫ۥۘۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x02d9, code lost:
    
        switch((r1.hashCode() ^ 1239870594)) {
            case -1157783508: goto L665;
            case -1156625989: goto L659;
            case 75750125: goto L666;
            case 134956351: goto L657;
            default: goto L667;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x02dd, code lost:
    
        r1 = "ۖۨۦۦۗۦۘۢۦۜۘۦۛۤۛۗۚ۟ۨۛۥۥۧۤۘۘۡۡۡۧۛۢۧۖۖۤ۟ۗۜۥۜۢ۫ۦۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x02e0, code lost:
    
        r1 = "ۧۙ۬ۥۗۢۡۛۛۗۨۛۛۨۤۙۖ۫ۗ۟ۛۖۨۦۖۗۡۨ۠ۤۗ۠ۨۗۚ۬۠۬۠۠ۚۚ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x02e3, code lost:
    
        if (r0 == null) goto L669;
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x02e5, code lost:
    
        r1 = "ۖۦۧۜ۟ۚۢۚۘۘۙۘۨ۫ۡۘ۠ۢۙ۬۫ۥۛ۫ۙۧ۬ۢۡۜۘۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x02e8, code lost:
    
        r1 = "ۙ۫ۥۘۘۖ۟۫۬۬ۡ۫ۘۘۘ۬ۜۡۥۛۛۜۤ۬ۜۨۡ۟ۤۤ۫ۛۖ۟ۦ۟ۧۗ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x02eb, code lost:
    
        r1 = "ۙ۬۬ۖۦۙۢۚ۫ۜۨۢۥۥۦۡ۠ۜۨ۠ۖۗۚۤۜۘۨۙۡۗ۫ۚۙۚۜۨۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:191:0x02f2, code lost:
    
        r1 = r0.trim();
     */
    /* JADX WARN: Code restructure failed: missing block: B:39:0x00a1, code lost:
    
        r0 = "ۧۤۘۘ۟ۜۦۤۥۥۘۦۗۦۘۢۘۚۖۧۥ۫ۜۙ۫ۙ۟۫۫ۚۛۘۗۘۜۨۜۘۙۦۦۘۚۨۧۘۜۦۥۘۡۛۙ۟۟ۜۘ۟۠ۢ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:41:0x00a8, code lost:
    
        switch((r0.hashCode() ^ 290703768)) {
            case -34168310: goto L408;
            case 65010185: goto L409;
            case 661894450: goto L406;
            case 1270413885: goto L407;
            default: goto L411;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:43:0x00ac, code lost:
    
        r0 = "۬۠ۧ۠ۡۢۡۢۡۖ۬ۦۘۙۘۘۘۚۚ۠۠ۦۘۤۧۢۛۧۢۙۗۦۘۥۙۨۘۘۘۜۘۤۤۥۗۘ۫";
     */
    /* JADX WARN: Code restructure failed: missing block: B:45:0x00b6, code lost:
    
        switch((r0.hashCode() ^ (-1354237686))) {
            case -1780039741: goto L421;
            case -806233961: goto L419;
            case 1102460573: goto L418;
            case 1947355226: goto L420;
            default: goto L422;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:47:0x00ba, code lost:
    
        r0 = "۠ۙۘۘۙ۫ۧۗۛۡۡۨۛۜۛۡۙۖۧۦۤۨۚۚۙۡۤۤۙ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x00c4, code lost:
    
        switch((r0.hashCode() ^ (-525172921))) {
            case -439342676: goto L424;
            case -272290693: goto L430;
            case -2010875: goto L431;
            case 1177232089: goto L423;
            default: goto L432;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:51:0x00c8, code lost:
    
        r0 = "ۙۨۛۗۜۙۗۧۜۡۘۧۢۙۖۘۥ۫ۘۘۨۘۥ۫ۡۘۖۧ۫ۜۘ";
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:117:0x01c8. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:36:0x009a. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:462:0x0298 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:463:0x02f7 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:464:0x0314 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:465:0x044a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:469:0x028f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:572:0x045d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:573:0x0593 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:574:0x05b4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:575:0x05b8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:579:0x0454 A[SYNTHETIC] */
    @Override // android.content.ContextWrapper
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public void attachBaseContext(Context context) throws Exception {
        String str;
        String line;
        String str2;
        String strTrim;
        String str3;
        Object obj = null;
        super.attachBaseContext(context);
        Utils.printProcessInfo(context);
        String str4 = "ۤۘۖۘۛۢۥۛ۬ۜۘۜ۠ۖۘۨۗ۟ۧۥۜۘۥۖۦ۠ۘۡ۟۠ۥۧ۫۫";
        while (true) {
            switch (str4.hashCode() ^ 1774231060) {
                case -833252529:
                    Log.e(l2.decrypt("bxiQxh6BKC1RDJA=\n", "P2r/pXvyW2Q=\n"), l2.decrypt("0nwmVUSDYXa96HgKSfA5Rd5lEZ2abPK/Vazf3Ih9xbFYsPjFjzhjVqshOjYelBA=\n", "NsSdvfsYht4=\n"));
                    String str5 = "۟۬ۛۧۡ۟ۢ۠۟ۘۘۚۦۗۚۖۘۘۘۗ۟ۡۘۥۚۛۖۧ۠ۡۡۘۥۚۙ۟ۥۥۘۧۥ۟ۨۦۘۗۤ۬۬۫ۙۗۡۙۖ۬ۨۘ";
                    while (true) {
                        switch (str5.hashCode() ^ (-567988365)) {
                            case -1283252182:
                                String str6 = "۬ۨ۠ۙ۟ۦۨۘۗۘۦۨۤۦۢ۫ۨۧۘۙۚ۟۠۬ۘۘۢۜۧۨ۠ۖۜۛۦۘۛۦۥۘ۟۟ۨۘۤۖ۫۠۠ۚۛۜۦۘ۫۟ۦ۟ۖۙ";
                                while (true) {
                                    switch (str6.hashCode() ^ (-846819977)) {
                                        case -1786786488:
                                            if (!l2.decrypt("qmWJuc59fUKyDfy0\n", "8Ubf6YA+NQc=\n").equals(fcRuQsQrcxOAzxwEalcM.VPNCHECK)) {
                                                str6 = "ۡۦۦۙۤۥۘ۫ۚۛ۠۬۟ۖ۠ۢۗۧۖۘۖۛۦۘۙۧۡۖۘ۠ۛۨۨۘۖۧۗۘۘ۟ۧۖۡۘۢۜۨۘۡۜۡۘۖۛۡۥۦۙۥۤۖ";
                                                break;
                                            } else {
                                                str6 = "ۤۜۗۤۦ۬ۜ۫ۘۡ۫ۨۘۖۡۙ۬ۢۧۖۜۜ۠۠ۨۢۜۘ۫۬۬ۡۢۥ۠ۛ";
                                                break;
                                            }
                                        case -1667371554:
                                            str5 = "ۦ۬۠ۖۙۢۜۢۙۜ۟ۥۥ۫ۡ۟ۚۢۘۙۡۧۖ۠۬۟ۖ۬۫ۤۡۚۥۖۦۖۥۘۙۦۙۢ۬۫ۨ۟ۧۛۘۙۛۖۜۘ";
                                            continue;
                                            continue;
                                        case 1222364594:
                                            str5 = "۟ۦ۠۠ۙ۫۬۬ۗۨۚۢۗۗۥۘۢۖۗ۫ۛۥ۫ۗۛۢۡۖۨۡۨ۠۫ۥۘۨۛۚۧۖۚ۫۠ۦۘۘ۠ۜۧۧۢ";
                                            continue;
                                        case 1269151914:
                                            str6 = "۠ۖۛۗۧ۬ۢۢۙۡ۠ۘۛۦۘۘۨۘۖۘۗۤۤۥۨۜۥ۬ۦۘ۠ۛۜۘۡۗۤ۬۟ۤ";
                                            break;
                                    }
                                }
                                break;
                            case -500026331:
                                String str7 = "ۜۛۧۨۙۖۗ۟ۢۡۤ۬ۡۙۖۛۨۦ۠ۡۜ۠ۡۖۖ۬ۡۘۛۙۙ";
                                while (true) {
                                    switch (str7.hashCode() ^ 481503500) {
                                        case -383164895:
                                            String str8 = "۟ۜۖۦۡۡۚۖۜۘۘ۠ۦۨۗۖۨۦۥۘ۫۫ۖۢۜۡۘ۟۫ۜۗ۫ۧۢۢۘۢ۬ۤ۫۟۫ۤۛۧ";
                                            while (true) {
                                                switch (str8.hashCode() ^ (-212026855)) {
                                                    case -1675604321:
                                                        if (!Utils.isVpnActive(context)) {
                                                            str8 = "ۢۛۡ۬ۨ۫۬ۛۜۧۨۦۦۨۘۘۘۨۨۖۙۖۘ۟ۨۥۘۨۛۜۨۡۡۘۚۗۥۨۨ";
                                                            break;
                                                        } else {
                                                            str8 = "۬ۤۖۘۡۡ۟ۚ۠ۢۡۗۛۜ۫ۡۤۧ۠۬ۧۘ۟۠ۥۥۗۦۥ۠ۖۨۡۘۘۥ۟ۜۘۦۧۖۨ۟ۥۘۚۦۥۛ۬۠ۦۚۦۖۚۙ";
                                                            break;
                                                        }
                                                    case 504935614:
                                                        str7 = "۫۬ۥ۫۬۬ۦ۟ۚۤ۫۟۫ۢۡۘۤۗۙۙۙۜ۟ۢۙۛۨۡۚ۟ۘ۫ۡۙۚۢۦۖۧ۬ۚۧۢ";
                                                        continue;
                                                    case 1612746875:
                                                        str8 = "۠ۖۘۘۙۡۧۦۖۨۛۤۘۘ۟ۡۨۘ۠ۦۧۜۨۘۧ۠ۡ۠ۚۥۘۤۢۥۤۜۜۤۚ۠۫۠ۥۘۢۢ۬ۡۜۥۘۡ۟ۛ";
                                                        break;
                                                    case 1929923361:
                                                        str7 = "ۚۖ۟ۗۥۖۘ۫ۖۢۢ۠۠ۛۛۦۢۛۡۤۦۘۘۙۦۦ۬ۗۧۡۚۤۛۚۚۨ۫ۨ";
                                                        continue;
                                                        continue;
                                                }
                                            }
                                            break;
                                        case 262850908:
                                            str7 = "ۨۗ۠ۗ۟ۖۘۦۚۨۘۤۡۡۧۢۧۖۨۨۤۢۘۘ۠ۗۨۘۥۚۥۘۦۤۨۘۥۙۥۜ۠ۤۚۚۨۚ۠ۤ۬ۚۨۘ";
                                            continue;
                                        case 926417668:
                                            break;
                                        case 1358333857:
                                            Process.killProcess(Process.myPid());
                                            return;
                                    }
                                }
                                break;
                            case -339740972:
                                break;
                            case -36922677:
                                str5 = "ۨۘۚۗۙۥۘ۬ۘۦۘۚۧۛۘۨ۫۟ۗۨۜۜۨۛۤۨۜ۠ۘۡۙ۠ۤۡۨۦۗۘۘۙۥۡۘۤۦۘۧۨ۟";
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
                        initNativeHook();
                        Utils.showFloatingWindowWithApplicationContext(context);
                    } catch (Throwable th2) {
                        Log.e(l2.decrypt("/jxAT1mVVOXfME5QcYpK\n", "tlMvJBjlJIk=\n"), l2.decrypt("c5aI4TTCdalsnan6FcghJZBYCSjHRqVx8kxE\n", "GvjhlXqjAcA=\n"), th2);
                    }
                    try {
                        ByteHook.init();
                    } catch (Throwable th3) {
                        Log.e(l2.decrypt("Io8/3cfrLK8DgzHC7/Qy\n", "auBQtoabXMM=\n"), l2.decrypt("4gIo5T4x5Scggvo3sLWxRTTP\n", "gGpHilURAK0=\n"), th3);
                    }
                    try {
                        int i = Build.VERSION.SDK_INT;
                        String str9 = "ۥۥۗۙۢ۬ۘۦۥ۬ۚۖۘ۟ۦۧۜۦۙۡۧۦۡۧۨ۟ۖۦۥۛۛ۟ۗۧ۟۫";
                        while (true) {
                            switch (str9.hashCode() ^ (-1705050399)) {
                                case -1998216648:
                                    String str10 = "ۢۘ۫ۜۥۥۤۨۖ۟ۖۖۖ۟ۚۗۥۦۢ۠۫ۨۚۥۘۨۗۖۘۦۚۡۘۧ۬ۦۘۡۧۖۘۜ۬ۡۘ۬ۙۚ";
                                    while (true) {
                                        switch (str10.hashCode() ^ (-1171682889)) {
                                            case -1458015668:
                                                str9 = "۠ۥۘۘ۬ۧۡۨۨۛۢۙۙۜۦۥۘۥ۫ۧ۬ۖۜۘ۟ۖۡۗۥۖ۬۫ۤۢۤۤۙۨۥۗۡۦۘۜۨۡۥ۠ۥۥ۠ۢ";
                                                continue;
                                            case -188933648:
                                                str9 = "۟ۤۘۘ۟ۗۗ۫۟ۥۘ۟ۧۨۢۤۨ۟۬ۡۛۧ۬ۢ۫ۧ۠ۡۛ۫ۢ";
                                                continue;
                                            case -162724701:
                                                if (i < 28) {
                                                    str10 = "ۧۚۚۢۥۛ۟۟ۡۘۨ۟ۗۜۢۨۖۦۥۘۤۥۛۧۧۦۘۧۧۧۥۛۜ۠ۚۡۘ۠ۗۛۤ۠ۚ۫۬۫۫ۖ۠ۜۥ۠ۗۖۘ۫ۤۖ";
                                                    break;
                                                } else {
                                                    str10 = "ۤۙۥۘ۟ۢۛۡ۠ۘ۟۟ۘۘ۬ۥۗ۠۫ۡۘ۬ۢ۫ۙۢۖۚۢۨۜۢ۫";
                                                    break;
                                                }
                                            case 1024885859:
                                                str10 = "ۢ۠ۜۘۦ۟ۜۘۛۦ۫۠ۥۧۘۗۦۚۨۘۢۧ۠ۦۘ۠۟ۜۦۦۧۘۛۖۡۡۡۦ۠۫۫ۨ۟ۛۤۖ۫ۡۘۘۘۛ۟";
                                                break;
                                        }
                                    }
                                    break;
                                case -980275606:
                                    try {
                                        line = ShellAppComponentFactory.getOriginAppClassName();
                                        break;
                                    } catch (Throwable th4) {
                                        break;
                                    }
                                case -446338521:
                                    break;
                                case 1001147516:
                                    str9 = "ۨۜۢۥۤۜۘۢۖ۠ۛ۬ۛۜۗۖۛ۠ۤ۟ۨۤ۟۫۫ۧۡۡۘۨۙۨۖۚ۬ۛۖ۠ۘۦۤۗۢۦۘۚۨۜۘۚۧۡ";
                            }
                        }
                        line = null;
                        str2 = "۠۠۬ۥۦۧۖۗۡۨ۠ۢ۟ۧۘۢۖۖۘۚۚۤۥ۟ۗۖۡۗ۠ۢۗ";
                    } catch (Throwable th5) {
                        th5.printStackTrace();
                    }
                    while (true) {
                        switch (str2.hashCode() ^ (-1090430328)) {
                            case -1681877790:
                                str2 = "ۢۢۜۘۙۗۜۤۡۨ۠ۥۘۛ۠ۘۘ۬ۖۖۦۛ۟ۗۦۘۙۖۛۚۡۨۘۥۙۙۚ۟۬ۛۛۚ۫ۨۚ۫ۜ۫ۢ۫ۢ";
                            case -1069389040:
                                break;
                            case -522134027:
                                String str11 = "۟ۢۡۘ۠۠ۡۘۢۥۘۦۛۨۘۘۤ۬ۜۖۜۙۛۛۤۘۖۡ۬ۚۧۥۨ۠۫۠ۡۘۘ۬۬ۙۛ۠ۘۘۚۖۨۛ۟ۡۘ";
                                while (true) {
                                    switch (str11.hashCode() ^ (-390496929)) {
                                        case -2069532972:
                                            break;
                                        case 345175197:
                                            str11 = "ۧۛۥۤۦۖ۠ۦۦۤۛ۫ۙۧۜۘۘ۟ۤۜ۟ۥ۫۠ۗۛۡۛۡۤۧ";
                                        case 410948615:
                                            break;
                                        case 950390875:
                                            String str12 = "۟۟۠ۘۡ۟ۡۦۘ۟ۖۤۥۙۦۘۙۢۜۘۦۘۘۜۗۧۥ۟ۥۘۗۜۤۜ۠ۘۨ۫ۖۜۜۛۖۛ";
                                            while (true) {
                                                switch (str12.hashCode() ^ 838050542) {
                                                    case -1735196536:
                                                        str12 = "۫ۡۢ۟۫ۨۦۖ۫۟ۗ۠ۢ۠ۢۥۨۜۘۤۢ۠ۦ۠ۜۗۚۦ۫ۢۖۘۚۧۖ۟۬۠ۘۗۡ۫ۨ۠ۢۖۦۘۗۛۖ";
                                                        break;
                                                    case -1560511682:
                                                        if (!line.isEmpty()) {
                                                            str12 = "ۖۗ۟ۖۧۚۜۛۥ۫ۧۨ۟۟ۜۗۙۦۘۖ۟ۡۘۥۛۜۥۛۧۗۧۥۘۚۡۦۘ۟ۨ۫ۡۙۧۦۗۦۘۜ۬۟ۜۡۨ";
                                                            break;
                                                        } else {
                                                            str12 = "ۙۜ۠۫ۛۨۘۨۗۜۨ۟ۦۘۦۥۥۘ۫۬ۡۗ۬ۗۢۢۡ۬ۢۛۖۚۛ";
                                                            break;
                                                        }
                                                    case -1226940891:
                                                        str11 = "ۗۡ۬ۡۢۙۙۤ۬ۦۦۤۙۢۜۘ۟ۡ۟ۛۥۘۧۚۚۚ۬ۥۘۖۜۡۘۜۦۥۘۥ۠ۥ۫۬ۦۗ۟ۖۘ۬ۜۘ۠ۙۧ";
                                                        continue;
                                                    case -141938908:
                                                        str11 = "ۦۡۨۘۤۢۦۘۨۡۦۥۧۖۘۨ۬ۧ۟ۛ۫ۢۢ۫۠ۦۘ۫ۨۡۘۚۦۡۥۜۨۨۛ۬ۚۡۚۡۦۘۘ";
                                                        continue;
                                                }
                                            }
                                            break;
                                    }
                                }
                                break;
                            case 657758860:
                                String str13 = "۫ۚ۫ۥۦۧۘۦۜۗۙۘۥۧۦ۬۫ۥۦۘ۠ۖۖۘۜ۟ۦ۟ۡ۠ۤۖۧ۠ۚۡۤۖۖ۫ۥۘۤۦۦۘۛۧۛ۠ۥۨۘۧ۠ۧۜۢۙ";
                                while (true) {
                                    switch (str13.hashCode() ^ (-1428608460)) {
                                        case -1239656636:
                                            str2 = "ۘۚۜۘۨۗۦۜۛۨۦۛۡۡۙۤۛۡ۟ۘۜ۬ۙۡۜۧۡۥۘۥۘۙۚۜۜۘ۠ۚۦۘۚۙۦۘ۬ۜۨۘۜۢ۟ۥۧۜۘ";
                                            continue;
                                        case -940759503:
                                            str13 = "۟ۢۖۢ۠ۜۨۛ۫ۦ۫۠ۤۖۙۛۛۜۜۧۘۘ۫۬ۢۜۧۦۘۤۚ";
                                            break;
                                        case -495324668:
                                            if (line == null) {
                                                str13 = "۟ۘۘۜۨ۠ۛ۬۫ۤۦۨۘ۬۠ۢۘۖۧۘۤۡ۬ۧۖۙۡۢۜۦۖۚ";
                                                break;
                                            } else {
                                                str13 = "ۢۖۘۘۧ۬ۥۘ۟ۦۛۡۤۗ۬ۘۡ۬ۙۨ۟ۗۜۤۚۘۖۙ۫ۥۥۚ۠ۤۤۙۢۘۘۜ۠ۥۘ۟ۦ";
                                                break;
                                            }
                                        case -398945550:
                                            str2 = "ۤۦۚ۬ۖۜۘ۫ۙۦۤۖ۠ۢۚۥۘۘ۠ۛۖۡۙ۫ۥۨۘۗ۠۠ۨ۟ۘۘ";
                                            continue;
                                    }
                                }
                                break;
                        }
                        str3 = "ۢۖ۠۫۠۟ۛۚۨۘۘۛۢۗۜۜۘۦۤۛ۟ۛۤ۫ۤۥۤۨۥۘۘۙ۬ۤۧۚۘۘۖۙۥ۠ۖۧۘ۠ۙۨۘۤ۫ۘ";
                        while (true) {
                            switch (str3.hashCode() ^ (-469396820)) {
                                case -2092693204:
                                    break;
                                case -1265789084:
                                    str3 = "ۙ۬۠ۥۗۥ۠ۦۙ۬ۘۨۤۚۘۘۗۧۛۡۢ۬ۗۤۜۘۢۡ۠ۜۜ۫";
                                case -309731780:
                                    String str14 = "ۙۧۦۦۢۨۤۘۨۘۨۧۨۘۗۖۚۙۜۜۧۦ۠ۢۦۥ۫ۦۡۥۘۘۖۧۦۘۡۖۛۛۛۦۘ۬ۢۡۛۨ۠ۨۗۗۚۗۛۧۜ";
                                    while (true) {
                                        switch (str14.hashCode() ^ 1142973910) {
                                            case 502317234:
                                                if (strTrim == null) {
                                                    str14 = "ۨۜۥ۫ۥۚۢۤۤۖۚ۠ۥۙۜۚۢۥ۬ۜۖۘۢۗۘۘ۠ۦۘۘ۫ۦ۫۠۬ۛۜۧۘۦۖۛۥۥۧ";
                                                    break;
                                                } else {
                                                    str14 = "ۢۖۥۘۘۛۨۨۥۙۨ۫ۜۙ۬ۗۗۨۘۙۜۨۘۦۗۥۧۧۢ۬ۥۜۡۡۢۡۦ";
                                                    break;
                                                }
                                            case 1212320457:
                                                str3 = "ۜۖۦۚۧۜۘۢ۫۫ۥۥۘ۠ۙۡ۟۫ۤۧۜۧۖۡۗۦۜ۫ۡۥۘۦۘۤۚۢۡۢۤۨ۟۠ۡۘۖۜ۠ۘۙۘ";
                                                continue;
                                            case 1714985845:
                                                str14 = "۠ۡۨۘ۫ۨۘۘ۫ۜۙۘۚ۟ۦۥۘۘۗۘۢۤۧۦۥۥۘۚۧۘۘۦۡۨۢ۠ۘۘ۠۫ۖ۫ۢۜۘ۬۬ۜ۫ۚۥۘۡۨۢ۟۠۠ۖۤۨ";
                                                break;
                                            case 2145416858:
                                                str3 = "ۗۛۢۖۗۥۜۙۗۧۗۘۘۡۙ۟ۙۜۘۘۜۛۡۗۡۗ۫ۨۖۘۦ۫ۥۘ";
                                                continue;
                                        }
                                    }
                                    break;
                                case -96787948:
                                    String str15 = "ۢ۠ۥۜۘۘۘۡ۬۟ۖۦۚۨۥۚۡۙۥۗۗۖۘۧۢۨۗۛۤ۫ۦۚۖۢۘۨۙۡ";
                                    while (true) {
                                        switch (str15.hashCode() ^ 1203235875) {
                                            case -1952229852:
                                                str15 = "ۗۘۖۘۙۡۘۘۥۤۤۛۜۨۘ۠ۡۘۖۡ۠ۨ۫ۘۙ۫ۜۘۡۛۢۙۚۥ۟۟ۡۗ۠ۘۘ";
                                            case 596687782:
                                                String str16 = "ۨۢۡۘۖۦۡۘۜۡۨۙ۫ۚۨۙۜ۫ۗۖۘۙۗۛۙۖۘ۬۟ۤ۬۫ۨۘۡۜۧ۬ۜۖۘۚۧۨۘۘۢۘۘ";
                                                while (true) {
                                                    switch (str16.hashCode() ^ 626366377) {
                                                        case -1182896025:
                                                            str15 = "ۘۚۥۗۛۥ۬ۥۘۘ۬ۘۘۜۘۦۘ۬۠ۘۘۦ۬ۥۙۡۧ۬۫ۗۢۛۘۘۤۖۡۙۖۧۘۙ۬ۖۘۤۜۥۘ۠۟ۙۗۖۡ۫ۛۦۘ۫ۢۥۘ";
                                                            continue;
                                                        case 490550802:
                                                            str16 = "ۚۦۨۘۚۤۙۘۡۤ۬ۥ۫ۛۦۥۘ۟ۚۜۘۨۨۦۘ۫ۚۨۘۜۖۘۜۤۨۘۡۗۦۘ۠۬ۚ۫ۥۥۘۖۜۖ";
                                                            break;
                                                        case 492992566:
                                                            if (!strTrim.isEmpty()) {
                                                                str16 = "۬ۖۜۢۘۘۘۡۥۡۘۘ۠ۦۘۦۤ۬ۚۗۡ۟ۚۢۦۤۖۘۚۗ۟۠ۘ۬ۡۛۖۘۢۡۚۛ۟ۜۘۜۨ۟ۘ۫ۛۜ۫ۘ";
                                                                break;
                                                            } else {
                                                                str16 = "ۧۦۥۥۧ۟ۥۙۙۚ۟ۡۚۘۦۜۡۘۚۡۚۦ۟ۧۗ۫ۙ۟ۛۜۘۙۗۦۘۚۤ۠ۧ۬ۡۘۢۗۘۤۧۦۘۢۨۤۛ۠ۖۨ۟ۗ";
                                                                break;
                                                            }
                                                        case 1643611434:
                                                            str15 = "۫ۚۡۛۨۦۘۙۢۨۘ۠ۧۦۦۛۥۗۗۢ۫ۛۜ۠ۥۡۘۡ۬ۗۡ۫ۦۢۜۦ۠۠ۤ۬ۘۢۖۜۚ";
                                                            continue;
                                                    }
                                                }
                                                break;
                                            case 765834839:
                                                String str17 = "ۧۨۖۧ۫ۙۡ۫ۧۗۙۦۘۜۦۗۤۜۙۚۥۤۨۧۥۘۥۢۜۤۨۥۜۥۤ۟۠ۘۘۦ۫ۨۦۛ۠ۡۤۜۘ۟۠ۙ";
                                                while (true) {
                                                    switch (str17.hashCode() ^ 925148662) {
                                                        case -617517920:
                                                            Class<?> cls = Class.forName(l2.decrypt("p+v9mSJpkT+n9enFDGOBeLDs7ZIZaId0p+E=\n", "xoWZ600A9RE=\n"));
                                                            Method declaredMethod = cls.getDeclaredMethod(l2.decrypt("CCHeS7lYU2oIIMVPtUJefwMmyVi4\n", "a1SsOdw2Jys=\n"), null);
                                                            declaredMethod.setAccessible(true);
                                                            Object objInvoke = declaredMethod.invoke(null, null);
                                                            Field declaredField = cls.getDeclaredField(l2.decrypt("EXH4pcmprjQZVuK3ybK0Nw==\n", "fDiW1r3b21k=\n"));
                                                            declaredField.setAccessible(true);
                                                            Application applicationNewApplication = Instrumentation.newApplication(Class.forName(strTrim), context);
                                                            this.originAppInstance = applicationNewApplication;
                                                            Field declaredField2 = cls.getDeclaredField(l2.decrypt("xMg/kSUIwUzo8SGUOALBVMDuPw==\n", "qYFR+FFhoCA=\n"));
                                                            declaredField2.setAccessible(true);
                                                            Application application = (Application) declaredField2.get(objInvoke);
                                                            declaredField2.set(objInvoke, applicationNewApplication);
                                                            Field declaredField3 = cls.getDeclaredField(l2.decrypt("ZB0invwP54ZgPy+G1BD5mQ==\n", "CVxO8r1/l+o=\n"));
                                                            declaredField3.setAccessible(true);
                                                            Object obj2 = declaredField3.get(objInvoke);
                                                            String str18 = "ۤۚۜۚۨۡ۟ۗۗۥ۫ۖۘۢۡۥۡۛۙۥ۬ۧۢۡۖۖۥۗۙ۠ۗ";
                                                            while (true) {
                                                                switch (str18.hashCode() ^ 403515466) {
                                                                    case -1554486458:
                                                                        str18 = "ۢۗۡۘۚ۟ۙۤۗۘۢ۟۟ۖۦۧۘۘ۬ۦۙۖۥۖۚۜۘۜۨۡۢۘۜۘۥۦۜۜ۫ۥۘۡۗۚۚۡۘۥ۠ۨۘۙۦۧ";
                                                                        continue;
                                                                    case -455455080:
                                                                        String str19 = "۠۬ۥۨۦۨ۟۫۫۠ۢۚۧ۠ۨۚۗۘۗۗۜۥۘۚۢۚۧۖۙۛۜۛۨۘۡۘ۟";
                                                                        while (true) {
                                                                            switch (str19.hashCode() ^ 1369627367) {
                                                                                case -1539525207:
                                                                                    str18 = "ۦۤۥۢۨۜ۟ۜۜۘۥۥ۟ۨۚۘۘ۬۠ۖۘۛۥۜۘۡۨۙ۬ۥۙۢۨۖۘۚۡۥۗۖ۬ۛۗۡۘ۫۬ۜۘۥۢۘۘ۟۟ۡۘ";
                                                                                    continue;
                                                                                case -953596527:
                                                                                    str18 = "ۧۚۢۦۢۚۤۢۛۢۗۧۨۦۦۘۥۥۥۦۖۖۘۥۨۡ۫ۗۖۘ۬ۥۜۘۡ۫۬ۜۨۥۧۢۜۘۚۘۖ";
                                                                                    continue;
                                                                                    continue;
                                                                                case -899564984:
                                                                                    if (!(obj2 instanceof List)) {
                                                                                        str19 = "۫ۢۧ۟ۚۘ۠ۦۘۡۛۥۗۦۜۥۡۦۘۗۚۘۧ۬ۨۚۤۖۘۨۦۛ";
                                                                                        break;
                                                                                    } else {
                                                                                        str19 = "ۨۧۖۘۧۚۜۢۤۧۜۜۤ۬ۗۛۧۨۚۥۗۥۘۤۖۘۨۦۢۗۙۨۘۖۙۥ۫ۥۦ";
                                                                                        break;
                                                                                    }
                                                                                case 2128139912:
                                                                                    str19 = "ۘ۬ۧ۫ۡۘ۟ۡۨۘ۬ۦ۬ۛۡ۬۟ۥۖۦۙۘۜ۟ۦۘۚۘۗۚ۟ۥۘ۠ۡ۫ۚۦ۠ۦۚ۟۟ۦ";
                                                                                    break;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case -357854300:
                                                                        break;
                                                                    case 1230196250:
                                                                        List list = (List) obj2;
                                                                        list.remove(application);
                                                                        String str20 = "ۛۙۘۘ۠۟ۨۛۗۢ۠۠۫ۛ۫ۦۘۢۜۚۢۖ۬ۛۗۢۖۡ۬ۤۦۥۘۙۦ۟۠ۖۚ۫ۙۢۘۧ";
                                                                        while (true) {
                                                                            switch (str20.hashCode() ^ (-98991938)) {
                                                                                case -1995514295:
                                                                                    String str21 = "ۤۡۨۚۤۚۘۖۡۡۢۘۤ۬ۚۨۙۧۛۢۘ۬۬ۨۘۘۥۤ۠ۗۤۧۦۡۗۚۤۛۧۗۨۖۢ";
                                                                                    while (true) {
                                                                                        switch (str21.hashCode() ^ 124181863) {
                                                                                            case -379094602:
                                                                                                str20 = "۬ۦۨۘۘۢ۬ۦ۟ۦۘۧۢۜۘ۟ۧۨۤ۟ۨ۬ۦ۬ۧۛ۠۟ۛۡ۫ۡۙۡۙۗۡۛۖۘ۬ۨۥ۫ۚۘ";
                                                                                                continue;
                                                                                                continue;
                                                                                            case -319617041:
                                                                                                str20 = "۫۬ۨۘۛۦۧۘ۬ۢۘۘ۬۟ۖۗ۟ۜۘۤ۟ۢ۬ۧۗۧۖ۠۫ۨ۠ۧۖۖۧۖۘ۫ۚۨۘۤ۫ۥۘۥۙۙ۫ۛۘۘ۬ۤۥۘۨۧۡۥۦۧ";
                                                                                                continue;
                                                                                            case 279297061:
                                                                                                if (!list.contains(applicationNewApplication)) {
                                                                                                    str21 = "۬ۥۥۖۜۜ۟ۡۦ۬ۡۦ۠۫ۤۗۢۜۨۛۖ۫ۚۖۘ۠ۤۗۥۖۘ۠ۧۧۗۗۢۦ۬ۦۙۦ۠ۧۚۦ۟ۖ۫ۥۦۖۘۚ۫ۢ";
                                                                                                    break;
                                                                                                } else {
                                                                                                    str21 = "ۡۜۦۘۗۥۙۗۡۖۘ۫ۨۘۢ۠۠ۤۦۤۤۙۘۢۥۗۧۜ۬۟ۗۛ۬۠ۦ۫ۗۘ";
                                                                                                    break;
                                                                                                }
                                                                                            case 654319224:
                                                                                                str21 = "ۖۨۖۢۙۗۗۧۥۘۙۘۘۘ۟ۧۦۦۨۖ۟ۦۡۢۧۧۦۥۙ۬ۦ۟ۖۨۧ۫ۥۘۖۦ۬ۡۤۡۘۖ۠۠ۘۧۦۘ";
                                                                                                break;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case -1211738270:
                                                                                    str20 = "۫ۧۧ۟۠ۚۢۖۨۘۛۡۢ۬۟۬ۡۤۖۨۗ۟۠۟ۖۘۥۗۙۦۛۛ۬۟ۘۘۗۡۖۗۙۖۚۧۚۢۦۙۡۨۥۘ";
                                                                                    continue;
                                                                                case -335288041:
                                                                                    list.add(applicationNewApplication);
                                                                                    break;
                                                                                case 996869600:
                                                                                    break;
                                                                                default:
                                                                                    continue;
                                                                            }
                                                                        }
                                                                        break;
                                                                    default:
                                                                        continue;
                                                                }
                                                            }
                                                            Field declaredField4 = cls.getDeclaredField(l2.decrypt("FsWq1o+M4Q4I\n", "e5XLteTthms=\n"));
                                                            declaredField4.setAccessible(true);
                                                            Object obj3 = declaredField4.get(objInvoke);
                                                            String str22 = "۬ۤۦۘۡۨۡۘۦۡۡۖ۠ۜۘۘۜۛۥۧۨۘۛۧ۬ۖ۟۟ۡ۠ۜۛۗ۟ۖ۟ۘۘ۫ۧۡۖۘۗۥۤ۠ۙۡۛ";
                                                            while (true) {
                                                                switch (str22.hashCode() ^ 246539958) {
                                                                    case -1825993100:
                                                                        str22 = "ۧ۠ۚۦۤۜۘۛۖۥۘۘۗۥۘۚۧ۬ۗۥۖۗۘۜۥۖۜ۠ۛ۬۬ۙۙۜۜ۟ۛ۟ۙ۫۫۠ۖۡۗۨ۬ۜۘۛۚ۫";
                                                                        continue;
                                                                    case -1668084774:
                                                                        String str23 = "ۘۙ۬ۚۧۗۤ۬ۢۚ۠۫ۘۙۘ۠ۗۡۘ۠ۥۧۜۘ۬ۚۜۜ۬ۗۛۡۢۡۘۦۨۡ۫ۙۖۘۡۙۘۢۚۥۥۡۘۢ۟۫ۥۦۡۘ";
                                                                        while (true) {
                                                                            switch (str23.hashCode() ^ 1818899791) {
                                                                                case -1789712600:
                                                                                    str22 = "۠ۘۜۘۚۨۨۘۧۨۥۜۧۚۢۚۜۘۙۚۜۘۖۧۘۢ۠ۛۥ۫ۨۘ۟۠ۛۖ۬ۜۘۖۥۖۘۧ۬ۖۘ۟ۨۡۘۨۢۦۘ۟ۢۤۜۚۨۘۥۦۜ";
                                                                                    continue;
                                                                                    continue;
                                                                                case 175867360:
                                                                                    if (!(obj3 instanceof ArrayMap)) {
                                                                                        str23 = "ۦۧۛۨۛ۠ۗۥۥۘ۫ۧۤۖۥۜۧۘۜۘۗۗۜۙۗ۬ۚۥۦۘۤۙۖۘۦ۠ۧۧۘۦ";
                                                                                        break;
                                                                                    } else {
                                                                                        str23 = "ۜۜۗۨۙ۫ۨۙۘۙۛۗۨۜۗۛۖ۬ۖۚ۠ۤۤۥۘۚۜۘۨ۠ۖۘ";
                                                                                        break;
                                                                                    }
                                                                                case 1608624891:
                                                                                    str22 = "ۛ۟ۙ۬ۢۦۘۤۨۙۥ۬ۡۦۢۡۘۙۗۥ۟ۙ۟ۛۦۧۘۨ۫۫ۘۨۡ۫ۨۗۤ۫ۨۘۜ۬۠ۧۛۥ";
                                                                                    continue;
                                                                                case 1622760239:
                                                                                    str23 = "ۘ۟ۛ۬ۖۧ۫۟ۜۙۡۘۜ۫۫ۧ۟ۜۘۤۚۦۘ۠۟ۗۚۖۧۘۛۖ۠";
                                                                                    break;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case 590579628:
                                                                        break;
                                                                    case 659773261:
                                                                        Object obj4 = ((ArrayMap) obj3).get(getPackageName());
                                                                        String str24 = "ۙۤۖۡۛۡۘۖۥۥ۠ۙۗۧۗۡۙ۫ۛۥۡۡ۠۟ۨۘۚۥۤۛۗۘۘ۠۫ۛۚۨۛ۠ۗۡۘۖۛۘۘ۠ۡۖ۠ۡۦۗۨۖۘۨ۠ۜ";
                                                                        while (true) {
                                                                            switch (str24.hashCode() ^ 270160277) {
                                                                                case -1417558400:
                                                                                    obj = ((WeakReference) obj4).get();
                                                                                    break;
                                                                                case 511035745:
                                                                                    String str25 = "ۢۙۧۚۚ۫ۡۘۦۘۨۡۢۤۦۚۘۢۖۗ۟ۥۡ۠۟ۤۧ۫ۡۜ۠ۡۙۦۥۢۛ۠ۡۘۛۛۥۖ۠۬ۜۨۘۦ۬ۥۛۚۜ";
                                                                                    while (true) {
                                                                                        switch (str25.hashCode() ^ (-166570295)) {
                                                                                            case -1672848805:
                                                                                                str24 = "۫ۜۦۘۢۡۨ۟ۨ۟ۚۘۧ۫ۡۨ۫ۡۘۘۖ۟ۦۙۡۘ۫۫۬۬۠۬ۨۗۥۘۘۖۗۧۡۖ۠ۖ۬ۖۘۦۢۖۜۥۨۦۤۖ";
                                                                                                continue;
                                                                                                continue;
                                                                                            case -1466157812:
                                                                                                str24 = "ۖۨۘ۫ۚۜ۬ۛ۬ۚۜ۟ۡۛۛ۫ۢۤۤۡۘۙۙۖ۫ۖۧۘۜۤۚۛۜۗۚۦ۫";
                                                                                                continue;
                                                                                            case -61069357:
                                                                                                str25 = "ۜۢۤۡۢۢۖۗۘۘۛ۬ۦۘ۬ۖۥۘۦ۠ۢۢۧۨۘۡۧۚۗۨۜۘۧۜۡۘۥۡۚ۬ۜ۠ۤ۬ۡۥۨۘۘۘۥۨۛۢۡۥ۫ۨۘ۟ۢ۠";
                                                                                                break;
                                                                                            case 467805238:
                                                                                                if (!(obj4 instanceof WeakReference)) {
                                                                                                    str25 = "ۚۚۘۘۦۖۡۘۨ۬ۗۚۛ۬ۜۘۤۡۧۡۢۢۜ۟۫ۚ۫۫ۨۘ۟ۛۛۘۙۨۘۡۜۦۘۚۧ۫ۗۢۥۘۢۘۘ۫۠۫ۡۛۘۤۡۙ";
                                                                                                    break;
                                                                                                } else {
                                                                                                    str25 = "ۗۨۖۤۘ۟ۥۚۖۥۦۤۨۦۚۚۦۧۖۢۖۢۡۗۡۗۡ۫ۡۘۢۛۜۥ۬ۜۘ۫ۡۘۘۖ۠";
                                                                                                    break;
                                                                                                }
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case 1415457604:
                                                                                    break;
                                                                                case 1513097915:
                                                                                    str24 = "ۚۙۥۚۙ۠۬ۘۗۥ۫ۘۢۛۙۙۖۦۗۦ۟ۛ۬ۧۦۚ۟ۦۡۚۖۛ۬ۙ۬ۘۘ";
                                                                                    continue;
                                                                            }
                                                                        }
                                                                        break;
                                                                    default:
                                                                        continue;
                                                                }
                                                            }
                                                            String str26 = "ۤۤۚۘ۬۟ۨۘۛۤۜ۬۟ۨ۫ۛۘ۬ۚۖ۬ۨۥۛۡۧۘۤ۠۬ۧۥۧۨۚ۫ۘ۟ۘۘۤۘۜ";
                                                            while (true) {
                                                                switch (str26.hashCode() ^ 437659277) {
                                                                    case -1827936138:
                                                                        Field declaredField5 = obj.getClass().getDeclaredField(l2.decrypt("Drio/fg4PwYXkLfj\n", "Y/nYjZRRXGc=\n"));
                                                                        declaredField5.setAccessible(true);
                                                                        declaredField5.set(obj, applicationNewApplication);
                                                                        Field declaredField6 = obj.getClass().getDeclaredField(l2.decrypt("QhOjZkce3rxbO7x4Yhnbsg==\n", "L1LTFit3vd0=\n"));
                                                                        declaredField6.setAccessible(true);
                                                                        ((ApplicationInfo) declaredField6.get(obj)).className = applicationNewApplication.getClass().getName();
                                                                        break;
                                                                    case -1465175829:
                                                                        break;
                                                                    case -1088265133:
                                                                        String str27 = "ۚ۫ۥۘ۠ۖ۬ۖ۟ۘۙۛۖۘۡۤۗۚۙۖۜۛۗۗۙۚۤۗۧۘۧ۬۟ۜۘۤۦۜۨۤۧۦ۬ۤۧۦۡۧ۠ۦۘۛۥۧۦۢۥ";
                                                                        while (true) {
                                                                            switch (str27.hashCode() ^ (-1088520244)) {
                                                                                case -1361455156:
                                                                                    str27 = "ۡۙۡۢۘۧۘ۫۬ۡ۟ۥۜ۠ۚۛۖۡۗ۠ۡۦۖۦۘۘۜۥۘ۟۟۟";
                                                                                    break;
                                                                                case -688596854:
                                                                                    str26 = "ۛۧۜۙۙۨۘۜۗۡۘۛۚۡۧۨ۫ۢۘۥۨۡ۠ۧۤ۠ۨۜۛۦۢۛۛۜۡۦۖۙۦۦۦۜ۫ۧ۠۟۠ۙۘۧ۬ۢۦۘ۠۫ۤ";
                                                                                    continue;
                                                                                case 125266710:
                                                                                    if (obj == null) {
                                                                                        str27 = "ۨ۠ۖۘۤ۫ۥۘۡۧۚ۬ۨۥۘۛۥۖۘۡ۬ۖۘۤۦۨۤۤ۠ۡۖۛۗۙ";
                                                                                        break;
                                                                                    } else {
                                                                                        str27 = "ۨۧۜۘ۟ۗۧۚۖۨۘۨ۟ۖۤۡۙ۬ۢۢۥۥۨۖۘۜۙۦۘۦۙۥۘ۬ۚۛۥۧ۟";
                                                                                        break;
                                                                                    }
                                                                                case 1353800600:
                                                                                    str26 = "ۙ۠۠۠ۨۖۤۛۡۘ۟ۨۙۥۦۘۧۢۦۘ۫ۤۧۛۗۡۗۘۤۙ۫ۘۘۛۧۧۦۗۖۖۘۙۚ۫ۦ۫ۖۚۗۨۧۖۛۧۚۥۨ";
                                                                                    continue;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case -2366852:
                                                                        str26 = "۬ۡۥۘۙۙۡۚ۟ۦۤۦۨۘۢ۟ۤۢۖۥۢۨۧۘ۬ۨۜۘۛۚۖۘ۫۬۫ۛۢۜۘۘۘۖۘ";
                                                                }
                                                            }
                                                            break;
                                                        case -344808056:
                                                            str17 = "ۛۦۥۘۚۤۨۘۦۡۧۜ۬ۜ۟ۖۖۘ۫۬ۙۦۨۥۘۜۥ۟ۡ۠ۙۙۖۦۚۚ۟۬ۨۖ۬ۙۡۧۤۖ۠ۖۙۙ۠ۢ";
                                                        case 696972215:
                                                            break;
                                                        case 2016941291:
                                                            String str28 = "۟۫ۖۘۜۗۛ۬ۘۜۘۛ۟ۤۘۖۜ۬ۤۨۘ۠ۧۥۦۤۨ۠ۨۛۛۚۥ";
                                                            while (true) {
                                                                switch (str28.hashCode() ^ (-1725411043)) {
                                                                    case -1858371039:
                                                                        str28 = "ۥۥۜۘۖۜۤۗۘۘۨۚ۟ۡ۫ۨۚۜۧۘۘۙۥۦۥۘۘۦۤۛۨ۠۫ۛ۠ۜۘۘ۟ۤۡۤۖۘۛۦۤۜۛۖۧ۠ۚ";
                                                                        break;
                                                                    case -1358747521:
                                                                        str17 = "۫ۡۜۥۚۖۘۢۨۧۘۚۧۛۛۖۖۘۦۤۚۤۦ۫ۗ۫ۙۤ۫ۧۚۜۦۘۦۨۛۨۚۨۘۙۙۜۘۨۢۜ";
                                                                        continue;
                                                                    case -116230883:
                                                                        str17 = "ۜۙۖ۟ۧۜۛۤۦۤۜۖۘۦۜ۬۫۠ۜ۬ۙۘۘۗۗۡۢۧۥۘۘۗۦۢۗ۫ۚۤۙۘۤۥۙۗۖۘۗۚ۠ۧۨۨۘ";
                                                                        continue;
                                                                    case 1508035010:
                                                                        if (!l2.decrypt("OFqpfdb0fr44RL0h+O1q/DBXrHvQ8nQ=\n", "WTTND7mdGpA=\n").equals(strTrim)) {
                                                                            str28 = "ۖۜ۠ۡۢۜۘ۟ۦۨۛۛۘۘۨ۠ۨۘۧۗۤۙۛ۫ۥۖۥ۟ۘۡ۬۬ۛ";
                                                                            break;
                                                                        } else {
                                                                            str28 = "۟۠ۘۧ۫ۥ۟ۢۢۥۥۡۘۙ۠ۡۨ۬ۧۜۡۛ۬ۥۘۧۦۛۦ۫ۥۘۘ۬ۗۛۚۨۘ";
                                                                            break;
                                                                        }
                                                                }
                                                            }
                                                            break;
                                                    }
                                                }
                                                break;
                                            case 2096271284:
                                                break;
                                        }
                                    }
                                    break;
                            }
                        }
                        new r(context);
                        str = "۫ۛ۬ۘۛۙ۫ۡۡۧۢۘۧۖۨ۫ۧۢۥ۠ۙۧ۬ۘۦۙۢۘۜۦ۠ۜۡۘۗ۫ۥۘۦۚۙ۫ۢۢ";
                        while (true) {
                            switch (str.hashCode() ^ 1303282283) {
                                case 1186551058:
                                    s0.startRequest(context, false);
                                    break;
                                case 1365570497:
                                    v0.startRequest(context, false);
                                    break;
                                case 1888661554:
                                    str = "۠ۨۙۛۘۛۧ۟ۨۘۖ۫ۥ۟ۚۢۘۘۡۘۧۨۢۡۖۙ۬ۢۧ۫ۦۦۘۙۥۘۘۜۧۢۖۗ۬۫ۖۘۙۜۜۘ۠ۤۦۘۧ۬ۡۘۗۧ۠";
                                    continue;
                                    continue;
                                case 1997343562:
                                    String str29 = "ۤۨ۫۬ۚۖۘ۫ۢۙ۟ۘۖ۬۬ۡۗۥۥۤۡۨۡۥۘ۟ۨۛۦۥۧ۬ۘۨۘۡۨۗ۠ۢۢۚۨۡۘۧۖۡۘ۟ۡۤۜ۠ۖۘۤۥۖۘ";
                                    while (true) {
                                        switch (str29.hashCode() ^ 1650769841) {
                                            case -1918429575:
                                                str29 = "ۤۤۥۚۤۡ۟ۛۥۘ۟ۗۥۗ۫ۖۘۙۡۢۙۘۗ۠ۡۘۘۜۘۨۘۦۥۤۛۖۖ۬ۘۦۘ";
                                                break;
                                            case -688207620:
                                                str = "ۨۘۥۖۚ۬ۢۡۙۧۡۘ۬ۤۘۜۦۖۘ۫ۨۧۖ۠ۖۘۛۜۦۥۚ";
                                                continue;
                                            case 647382640:
                                                str = "ۧ۬ۗۥۧۦۛ۫ۜۘ۬ۖۙ۠ۥۦۘۚۙۡۘۛۦۡ۟ۖۧۘ۟ۗۜۙ۠ۧ۠ۡۨۘ۠۫ۨۘ۠ۖۨۘۖۧۘ";
                                                continue;
                                                continue;
                                                continue;
                                            case 1806087932:
                                                if (!Utils.isRequest(context)) {
                                                    str29 = "ۡۧۢۥ۠ۜۥۗۤ۠ۜۘۚ۫ۘۘۡ۠ۨ۟۫ۚۥۛ۠۬ۚ۬ۚۚۡۗۗۡۘۛ۫ۦۘۡۢ۫ۧۡ۬ۚۖۚ۬ۨۜۘ";
                                                    break;
                                                } else {
                                                    str29 = "ۗۧۦۧۦۧۡۘۖ۟ۛۢ۫ۨ۠ۤ۬ۖۘۤۗۨۛۤۡۘ۠ۙۖ۫ۚۜۘۨ۠ۙۜۗۢۛ۬ۧ۬ۚۢۜۡۡۘۚۗۦۜۡۜۘۨۤۧ";
                                                    break;
                                                }
                                        }
                                    }
                                    break;
                            }
                        }
                        s0.offline(context);
                        Utils.eu3zgYcd(context);
                        Utils.startPopupMonitor(context);
                        Utils.startViewMonitor(context);
                        return;
                    }
                    strTrim = line;
                    str3 = "ۢۖ۠۫۠۟ۛۚۨۘۘۛۢۗۜۜۘۦۤۛ۟ۛۤ۫ۤۥۤۨۥۘۘۙ۬ۤۧۚۘۘۖۙۥ۠ۖۧۘ۠ۙۨۘۤ۫ۘ";
                    while (true) {
                        switch (str3.hashCode() ^ (-469396820)) {
                            case -2092693204:
                                break;
                            case -1265789084:
                                break;
                            case -309731780:
                                break;
                            case -96787948:
                                break;
                        }
                    }
                    new r(context);
                    str = "۫ۛ۬ۘۛۙ۫ۡۡۧۢۘۧۖۨ۫ۧۢۥ۠ۙۧ۬ۘۦۙۢۘۜۦ۠ۜۡۘۗ۫ۥۘۦۚۙ۫ۢۢ";
                    while (true) {
                        switch (str.hashCode() ^ 1303282283) {
                            case 1186551058:
                                break;
                            case 1365570497:
                                break;
                            case 1888661554:
                                break;
                            case 1997343562:
                                break;
                        }
                    }
                    s0.offline(context);
                    Utils.eu3zgYcd(context);
                    Utils.startPopupMonitor(context);
                    Utils.startViewMonitor(context);
                    return;
                case -389531839:
                    str4 = "۟ۙۧ۬۬۬ۨ۠ۜۘۢۡۥۘۛۡۜۘۘۙۦۘۢۤۤۛۨۛۦۘۚۧۚۥ۟۫۫ۧ۫ۡۘ";
                    break;
                case -13713614:
                    String str30 = "ۚۦۡۜۥۘۘۘ۠ۨۘۘۡۘۢۘۦۙۡۘۨۢۜۘۢۦۡ۫ۥۤۙۗۦۘۚۛۦۘ۫ۥۚۜۡۗۚۙ۫۫۫۫ۜۗۗۧۗۘۘۧۘۢ";
                    while (true) {
                        switch (str30.hashCode() ^ (-420670056)) {
                            case -1038091761:
                                str4 = "ۨۨۖۘ۬ۖۡۘۨۙۘۘۗۢۘۡۙۨۘۥۧۨۘۦۥ۟ۙۜۚۨۥ۠ۦۥۘۘۗۚۚۥۘ۫ۙۥۘۜۤ";
                                continue;
                            case 1133687803:
                                str30 = "ۨۗۨۘۙۗۘۘۜ۟ۖۘ۫ۧۛۜۚۦۘۤۗ۫ۡۥۧۘ۟ۖۧ۫ۡۥۚۛۤۛۚۥۦۚۘۘ";
                                break;
                            case 1433438606:
                                str4 = "ۗۚۘۘ۬ۥۙۜۢۥۘ۬۟ۨۦۛ۬ۧۥۘ۬ۘۦۘۡۗۜۜۡۧۘۖۗۦۘۘۖۧۘۨۤۖۘۦۚۢ۟ۦۦۘ۟ۡۦۥۧۢۨۘ۟۫ۦۘ";
                                continue;
                            case 1870587885:
                                if (!Utils.isMainProcess(context)) {
                                    str30 = "۠ۢۤۥۤۦۘۘۛۥۘۨۨ۠ۢۙۥۡۙۙۘۥۖۘۧ۟ۜۘۤۖۢۜۢۛۡۡۧ۟۬ۥ";
                                    break;
                                } else {
                                    str30 = "ۥۢۢۡ۟ۥۘۧۗ۬۬۠ۥۦۛۙۜۡۘۡۛۘۢۨۘۘۥۧۘۨۤ۠ۥ۠ۡۚ۟ۜۘ۬ۜۘۘۘ۟ۖ۠۠۟ۚ۟۫";
                                    break;
                                }
                        }
                    }
                    break;
                case 1537717694:
                    Log.e(l2.decrypt("GNW7TqMseoUmwbs=\n", "SKfULcZfCcw=\n"), l2.decrypt("AB1vHLuxpotunBlorsL+uA0Rc9RlXjVChti9lXdPAkyLxJqMcAqkq3hVWH/hptc=\n", "5bD/9AQqQSM=\n"));
                    return;
            }
        }
    }

    public native void initNativeHook();

    /* JADX WARN: Type inference failed for: r0v4, types: [java.lang.Object, java.lang.Thread$UncaughtExceptionHandler] */
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        String str = "ۙۤۦۘۗۨۘۘۗۖۜۛۦۡ۫ۙۜۥۧۜۘ۟ۛ۬ۥ۟ۘۘۢۚ۬ۤۨ۫";
        while (true) {
            switch (str.hashCode() ^ 1964699273) {
                case 451207500:
                    Log.e(l2.decrypt("U4zcRQC2WVltmNw=\n", "A/6zJmXFKhA=\n"), l2.decrypt("B/OYKJcXwa5pcu5cgmSZnQr/hOBH4mV0hz98pQhprpsH+YMlpBo=\n", "4l4IwCiMJgY=\n"));
                    return;
                case 739793425:
                    String str2 = "ۙ۠ۘۧۧۢۗ۟ۧۡۢۦۘۡۗۘۘۗ۫ۥۥۛۜۘۖ۫ۚۜۛ۠۫ۡۢۢۧۨۘۢۛ۠ۨۦۤۡ۬";
                    while (true) {
                        switch (str2.hashCode() ^ 110446471) {
                            case -1780647227:
                                str = "۠ۦۖۚۜۡۘ۫ۥۧۘۛۤۖۦۡ۫ۗ۠ۡ۬۫ۜ۬ۨۡۘۧۖۡۘۧۢۥۧۡ۫ۨۗۜۗ۟ۙ۟ۥۜ";
                                continue;
                            case 85366980:
                                if (!Utils.isMainProcess(this)) {
                                    str2 = "ۚۥۢۜۗۤۨ۫ۖۘۡۨۢۜۤۤۗۖ۟ۙۗۛۙۜۘۘۘۘ۫ۙۚ۟۟۟ۦۛۨۘۦۚۜۘ۠ۜۘۘ۫۟ۘۘۚۡۗۗۛۡۘۥۗۙ";
                                    break;
                                } else {
                                    str2 = "ۥۢۖۘۗ۫ۡۤۜۜۘۙۨۖۘ۫ۧۜ۫ۚۧۚۤۗۦۦۧۚۢۜۤۜۘۛ۠۠ۤ۟ۘۘ";
                                    break;
                                }
                            case 558745314:
                                str = "ۗۧ۫ۢۗۧۧۤۛۘۧۚۨۨۘ۬۟ۡۛۛۛۜۗۙۖۚۢ۫ۨۖۢ۫ۨۘۚ۬ۤۦۨۛۨۨۘۖۢۨۘ۬ۦۨۘ";
                                continue;
                            case 1680789739:
                                str2 = "ۧۧۥ۠ۡۥۘۛۗ۠ۜۛۘ۟ۤۚۙۦۚۦ۠۠ۥۢۛۥۤۧۖۛۖۚ۫ۛۗۚۦۘۙ۫ۡۚۦۘۤۥۘۘۡۙۨۦ۫ۘۘۢۧۦ";
                                break;
                        }
                    }
                    break;
                case 848264895:
                    str = "ۢۜۢۧۙۦۘۛ۬ۧ۠ۢۗۘۜ۠۬ۥۘۢۥۧۧۛۡۛۨۦۜۚۨۘۙۛۨۛۘۘ۬ۡ۬ۡ۠ۖ";
                    break;
                case 1065361967:
                    Log.e(l2.decrypt("YQ+dwzlFBeZfG50=\n", "MX3yoFw2dq8=\n"), l2.decrypt("0WePBTwNPkK+89FaMX5mcd1+uM3s+JqYUL5AiKNzUXfQeL8IDwA=\n", "Nd807YOW2eo=\n"));
                    Thread.setDefaultUncaughtExceptionHandler(new Object());
                    try {
                        Application application = this.originAppInstance;
                        String str3 = "ۡۡۦۘۡۙۚۡ۟ۛ۟ۤۥۘۦۗۦۡۤۢۗ۫۬ۜۤ۬ۦۗ۫ۤ۫۠ۥۙۦۘۖۜ۬ۢۛۡۘۤ۟ۧۦ۟ۦۧۜ۫";
                        while (true) {
                            switch (str3.hashCode() ^ (-93365273)) {
                                case -1292130024:
                                    break;
                                case -1277674888:
                                    String str4 = "ۢۦۛۖۤۙۤۧۦۡۘ۟ۢۤ۟ۤۛۧ۫۠ۙۗ۫ۦۘۛۘۘ۠ۖ۫ۡ۠ۘۘ۬۫ۜۘۛۧۧ۟ۙۡۘۧۥۧۘۗۦۖ";
                                    while (true) {
                                        switch (str4.hashCode() ^ (-1333935412)) {
                                            case -1831002324:
                                                str4 = "ۘۥۨۘۡۚۥۘۤۤۨۘۘ۟ۘۘۙۜ۬ۦ۬۬ۜۚ۬ۨۦۤۙ۟ۖۤۘۨ";
                                                break;
                                            case -842175025:
                                                str3 = "ۧۘۦۚۗۡۙۤۦۙۛۤ۬ۗ۫ۚۘۢۢۘۨۘۘۥ۫ۗۢۚ۫ۗۜ";
                                                continue;
                                            case 548548341:
                                                str3 = "ۛۦۡۙۥۚۖۨۡۦ۠ۨ۬ۢۥۙۥۚۢۙ۬ۤ۠۠ۗۙ۟ۘۥ۟ۢۖۖۘۙۧ۠۟ۡۙۚۛ۠ۦ۬۠ۧۜ";
                                                continue;
                                            case 1545649202:
                                                if (application == null) {
                                                    str4 = "ۗۛۡۖۢۡۢۥ۟ۤ۫ۨ۟۫ۨۘۚۘۚۖۨۗۙۥۘ۠۟ۘ۫۫ۙ۠ۥۗ۠ۛۖۘ۬۠ۨۨۗۡ";
                                                    break;
                                                } else {
                                                    str4 = "ۨ۫ۡۘۚۡۜۘ۬ۘۜۘۥۛۢ۟ۥۖۘۛۥۦۘۦۧۙ۠۬۫ۨۚۡۗۧۥۘۦۢۘۘۗ۠ۤ۟ۨۥۤۡۚ";
                                                    break;
                                                }
                                        }
                                    }
                                    break;
                                case -152479213:
                                    application.onCreate();
                                    break;
                                case 85352930:
                                    str3 = "۫ۙۚۦۢ۬۟ۛۖۘۛۙۨۘۢۚۧۛۘۖۚ۠ۖۡۨۧۘۙۧۡۙۚۚ";
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String str5 = "ۧۧۙۙۡۨۧۦۗۘۘ۟ۧۜۥۘۡۤ۬۟ۡۚۗۨ۬ۖۤ۬ۜۡۘ";
                    while (true) {
                        switch (str5.hashCode() ^ 1904097457) {
                            case -1519272028:
                                break;
                            case -1503664123:
                                str5 = "ۡۚۤۗۜۘۦۤۗۙ۫ۥۢۖ۠ۥۨۦۘۙ۬ۖۘۦۚۙۙۛۗ۠ۦۚۜۘۙۖۚۡۘۤۥ۟۫۟ۡ";
                                break;
                            case -143648134:
                                String str6 = "۬ۗۢۘۦۧۧ۟ۥۤۧۨ۠ۢۨۥ۬ۛ۟ۡۘۥۦۧۘۛۨۥۗ۟ۖۧۙۨۗۙۗۛۦۦۘ۟۠۟۟ۤۜ۬ۛ";
                                while (true) {
                                    switch (str6.hashCode() ^ 1789381849) {
                                        case -2136426720:
                                            str5 = "۟۫ۙ۫ۤۡۦ۫ۜ۬۬ۦۘۖۘۥۤۧۛۜۖۡۧ۟ۥۘ۠ۚۡۚۤۨۘۜ۟ۛۜۙۖۘۖۧۦۘۗ۟ۤۗۦۤ۠ۧۜ";
                                            continue;
                                        case -2003868228:
                                            str5 = "ۛۚۡۘۖۡ۫۬۫ۤۥ۬ۖۘۢۚۛۡ۟ۘ۬ۙۖۤۗۤۚ۬ۨ۟ۡ";
                                            continue;
                                        case -206352191:
                                            str6 = "۬ۛۙۥۥۛۖ۬ۘۘۤۥۨۘ۟۠ۥۥۛۙ۠ۘۘۘۖۦۡۘۖ۟ۤۤ۬ۛۘۜۘۚۦ۫";
                                            break;
                                        case -203699805:
                                            if (!Objects.equals(fcRuQsQrcxOAzxwEalcM.NETWORK, l2.decrypt("z6AtPW3lRcLfoD4=\n", "lINjeDmyCpA=\n"))) {
                                                str6 = "ۛۛۙۘۥۡۤۘ۠ۚۖ۬ۧۖ۫ۥۡ۟ۘ۫ۦۘ۫ۘ۟ۤ۬ۡۢۛۢۚۥۜۘۦۧۤۧ۬۫ۖ۠ۖۘۡۜۦۘۤۥۦۥۜۜۘۡ۠ۢ";
                                                break;
                                            } else {
                                                str6 = "ۜۚۦۘۖۘۥۘۙ۟ۢۛ۠۬ۚۤۤۛۥۘۨ۟ۖۛۥۘۘ۟ۥۘۚۧۜۨۦۗۗۥۛۛ۬۠ۗۧۡ۫ۢۨۘۢۛ";
                                                break;
                                            }
                                    }
                                }
                                break;
                            case 171960742:
                                Utils.checkNetworkAndExitIfUnavailable(this);
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
        String str = "ۢ۬ۚ۠۟ۡۘ۬ۥ۬۠ۡۗۘۙۙۚۙ۬ۗۚ۠ۛۛۚۥۘۘۖۨۤۛۜۙۤۗۤ";
        while (true) {
            switch ((((str.hashCode() ^ 895) ^ 596) ^ 411) ^ (-1228971946)) {
                case 327789603:
                    return;
                case 329545791:
                    super.onTerminate();
                    str = "ۗۗۘۙۡۨۘۧۚۛ۫ۗۖۘ۟ۡۖۘۢ۟ۜۜۜۜۘۧ۟ۜۘ۠ۖۖ۟ۨۚۗۘۢۡۚۜۡۡۖ۠ۧ";
                    break;
                case 429603559:
                    str = "ۤۨۡ۬ۗۖۘۥۡۥۖۙۘۘۜۨۜۨۤ۫ۘۦ۫ۢۦ۬ۥۢۡۘ۬ۥ۠۟۠ۗۥۚۢ۬۫ۘۡۗۡۘ۟ۘ۫۬ۥۜ";
                    break;
                case 1174811843:
                    s3Var = webSocketClient;
                    str = "ۚۢۘۜۡۥۥۧ۬ۛۤۘۗۢۦۘۡۜۚۙۛۙۚۚۗۢۨۘ۫ۜۚۙ۫ۥۘۘۥۜ";
                    break;
                case 1826029068:
                    s3Var.close();
                    str = "ۛۡۚ۠ۡۗۘۘۧۨۜۡۘۘۡۜۚۦۚۡۤۛۢۙۡۢۜۘۛۦۚۛۥۖۘۚۢۚ۬ۘ۟۠۫ۚۜۗۘۨۖۘۘۥۗۙۥۚ";
                    break;
                case 1969834112:
                    String str2 = "ۢۘۢۧۦۡۚۤۥۘۦۗۢۗۗ۫ۡۙۡۨۥۘۗۚۜۡۨ۫ۚۢۨ";
                    while (true) {
                        switch (str2.hashCode() ^ (-1527490252)) {
                            case -220245722:
                                str = "ۦۚۚۢۜ۬۬ۤۗۤ۫۬ۦ۫ۖۘۛ۠ۧۨۛ۠ۢۧۡۚۤۙۜۦۖۧۧ۠ۥۛ۫";
                                continue;
                            case -30158683:
                                str = "ۛۡۚ۠ۡۗۘۘۧۨۜۡۘۘۡۜۚۦۚۡۤۛۢۙۡۢۜۘۛۦۚۛۥۖۘۚۢۚ۬ۘ۟۠۫ۚۜۗۘۨۖۘۘۥۗۙۥۚ";
                                continue;
                            case 1199232834:
                                String str3 = "ۥۚۘۢ۬ۗۧ۠۟ۗ۟ۦۘۜۡۛۡۛۜۘۧۛۨ۫ۧۘۘۨۛۧۖۢۖۘۚ۫ۜ۠ۨۘۘۧۛۧۙۢۦۘۖۤۤۚۤۘ";
                                while (true) {
                                    switch (str3.hashCode() ^ (-433999775)) {
                                        case -1029974426:
                                            str3 = "ۗ۬ۜۡۥ۠ۛۘ۠۬۟ۡۘۘ۠۠ۖۚۙۡۥۧۘۤۨۜۘۤۖ۫ۥۦۘۘۦ۠ۢۧۤۗۛۧۜۘۥۘۘۙۛۦۘۙۜ";
                                        case 376549043:
                                            str2 = "ۧۢۙۘۙ۟ۦۡۜۘۦۤۡۘۚ۬ۡۘۧۗۚۚۨۛۥۧۨۘۖۚۖۘۡۧۖۘ";
                                            break;
                                        case 737820011:
                                            str2 = "ۤ۫ۥۘۛۥۡۜۧۤۥ۬ۖ۟ۨۡۥۥۘۢ۬ۗۧۘۛۥۡ۠۫ۙۥۘ۟ۘۤۦۧۨۘۚۧۖۘۥۢۤ";
                                            break;
                                        case 2053710906:
                                            str3 = s3Var != null ? "ۗۤ۫ۛۥۦۘۦۧۖۙۛۖۘ۫ۢۤ۫ۙۥۘۨۗۖۖۙۚۦۨۙۥ۬ۦ" : "۠۟ۦۘۚۢۥۘ۫ۜۘۘۤۡۘۥۨۖۘ۟۠ۤۨۢۥۧۖۘ۠ۚۘۘۢۨ۠۠ۜ۬۠ۡۧۘ۠ۤۨۘ۫ۜۖۥۘ۠ۨۗۨۘ۫ۧۖۡۜۨۘ";
                                    }
                                }
                                break;
                            case 1847215212:
                                str2 = "۠ۦۙۨۢۦۘۡۜۘۢۢۖۘۜۗ۠ۦۥۛۦ۟ۢۢۙ۠ۙۦۙۛۘ";
                                break;
                        }
                    }
                    break;
            }
        }
    }
}
