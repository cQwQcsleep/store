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
import com.shadow.okhttp3.internal.http.StatusLine;
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
public class ProxyApplication extends Application {
    private static Context ShellContext;
    private static boolean hasInit;
    public static s3 webSocketClient;
    private Application originAppInstance = null;

    static {
        String str = fcRuQsQrcxOAzxwEalcM.SIGN;
        try {
            Base64.decode(str, 0);
            String str2 = "ۡۛ۫ۖۤۖۘۡ۟ۘ۫ۥۘۘ۠ۦۙۧ۠ۥۘۚۡ۠ۛۗۤ۫ۘۢۖۙۗ";
            while (true) {
                switch (str2.hashCode() ^ (-214994866)) {
                    case -1931443986:
                        break;
                    case -1431976296:
                        str2 = "ۛۘۘۧۧ۬ۦۨۦۨۛۜۜۥۘۘ۬ۤۖۥۛۢۖۤۖۤۙۢۥۚۤۨۢۧۢۦۧ";
                        break;
                    case -1192625443:
                        String str3 = "ۚۙۛۙ۬ۨۢۖۘۦۗۖۡۡۨۡۜۘۧۤۘۘۗۨۖ۬ۡۤۦ۬۠ۡۘۥۘۘۙۧ";
                        while (true) {
                            switch (str3.hashCode() ^ (-557879619)) {
                                case -1467957094:
                                    str3 = "۬ۡۢۗ۬ۛۡۢۨ۬ۚۘ۫ۚۧۖۧۤۥۖۧۤۤ۠ۡۘۘۨۤۨۡ۟۫ۖۨۖۚۗ۬ۦۥۛۨۤۥۘۚۚۖ";
                                    break;
                                case -1259920694:
                                    SignatureSpoof.killPM(fcRuQsQrcxOAzxwEalcM.PACKAGE, str);
                                    killPath.killOpen(fcRuQsQrcxOAzxwEalcM.PACKAGE);
                                    break;
                                case 486932674:
                                    break;
                                case 1132941733:
                                    String str4 = "ۤۢۦۘۧۥۡۘۧۦۤۗۙ۠ۦۗ۠ۦ۟ۧۛۖۧۘۛ۟ۥۘۗۚۡۘ۫۬ۖۗ۟ۚۜۖۖۙۨ۬ۤۗۧ";
                                    while (true) {
                                        switch (str4.hashCode() ^ 2105119522) {
                                            case -2130122344:
                                                str3 = "ۖۗ۬ۤ۠ۦ۟۫ۜۘۨۜۘۘۜۖۦۘۛۨۡۘۘۖۘۘۘۗۤۤۤۡ۬ۚۢۚۤۡۥۜ۬۬ۡۖۢۡۚ";
                                                continue;
                                            case -1023394700:
                                                str3 = "ۦۖۘۡ۟ۖۦۧۘۘ۠ۢ۟ۖ۫ۨۢۧۡۜۘۘۘۙۗۡ۠۟ۛۢۤۤ۬۠ۘۜۡ۫ۤ۫ۙۘۚۙ";
                                                continue;
                                            case 416980961:
                                                str4 = "ۥۤۦ۟ۜۡۘۗۧۤۡۗۦۙۙۡۘ۫ۨۘۚۛۨ۟ۜ۫ۢ۫ۘۘۧۜۨۜۜۛ۟ۦۜۚۙۖ۬۟";
                                                break;
                                            case 1464508766:
                                                if (!"[#SIGN#]".equals(str)) {
                                                    str4 = "ۡۘۥۘۜ۫ۡۖۛۖۨۖۘۘۢۛۡۚ۬ۡۡۚۦۦۜۜۘۢۤۗ۫۬ۜ۟ۧۖ۬ۙۚ۬ۚ۫ۨۤۥ۬ۦۘ۫ۧ۫";
                                                    break;
                                                } else {
                                                    str4 = "۟۠ۡۘۙۧۖۘۧ۫ۦۘۘۢ۠ۜۜۥۘۘ۠ۘۘۥۖ۫۫ۤۘۘ۟ۚۗۚۢۖۘۜۥ۫ۨۦۜۘ";
                                                    break;
                                                }
                                        }
                                    }
                                    break;
                            }
                        }
                        break;
                    case -809810604:
                        String str5 = "ۧۙ۫ۢ۫ۜۛۨۜۘ۠ۧۡۘ۫ۜۡۤۥۥۙۦۘۛۧۡۘۥۘۨۦۤۤۡۤۚۖۧۖۘ";
                        while (true) {
                            switch (str5.hashCode() ^ (-1843865321)) {
                                case -639071806:
                                    str2 = "ۦۘۨۘ۫۟ۡۨ۬ۖۘۡۨ۠ۖۥۚۧۦۜۘۤۗۥۘۨۤ۫ۛۚۥۘ۬ۜۡۘۡۚۙۘ۠ۦۘۥۚۡۚۛۗۧۚۤۡۙۡۘ";
                                    continue;
                                case 810241047:
                                    if (!"[#PACKAGE#]".equals(fcRuQsQrcxOAzxwEalcM.PACKAGE)) {
                                        str5 = "ۗۨۗۙۡۜۘۦۢ۠ۥ۬ۥۘۗۖۥ۬ۧۖۘۛۢۨۘۚۨ۫ۖۘۦۖۙۙۘۘۢۘۤ";
                                        break;
                                    } else {
                                        str5 = "ۗۜ۬۠۬ۖ۫ۢۖۜۖۜۧۜ۬ۢ۬ۤۘۢۜۦ۠۫ۚۤۧۥۛۙۨۘۜۥۘ۬ۙۢۜ۠ۡۨۘ۬ۚ۠ۨۤۧۢۥۢۧ۟";
                                        break;
                                    }
                                case 1104502055:
                                    str2 = "ۜۙۗۢ۠ۜۥۦۨۘۨۨۘۨۘۚۤۚۜۘۦۘۘۧۡۘۘۥۛۨ۬ۦۘ۬ۥۡۛۜ";
                                    continue;
                                case 1910202354:
                                    str5 = "ۨۡۜ۠ۘۧۘۚ۫ۧۛۖ۫۠ۘۙۛۡۘۘۙ۬۟ۜۙۚۘۧۤۧۡۘۨ۟ۚۙۡ۠";
                                    break;
                            }
                        }
                        break;
                }
            }
            hasInit = false;
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("[菜鸟云验证] 签名格式非法，Base64 无法解析", e);
        }
    }

    public static Context getShellContext() {
        while (true) {
            switch (((("ۧۦۤۘۘۧۘۡۘ۫ۨۧۧۦۗۦۖۥۜۙۨۡۛۘۡۘۚۘۧۘۡۤ۟".hashCode() ^ 559) ^ 982) ^ 68) ^ (-1431087795)) {
                case 1602989465:
                    return ShellContext;
            }
        }
    }

    public static native void init();

    /* JADX WARN: Code restructure failed: missing block: B:138:0x01dd, code lost:
    
        r0 = "ۨۥۧۘۛ۬ۨۢۨۙ۬ۦۛۢۖۤۡۧۚۦ۬ۢۘۦۘۘۖۥۘۘ۬ۤۙۡۚۖ۟۟ۙۧ۟ۛۥ۟ۘۛۨ۬ۖۛۘ۫ۖۨۜۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:139:0x01e1, code lost:
    
        r0 = "۟ۧۚۘۢۖۘۚ۬ۤۥ۠ۙۗۡۛ۬ۜۡۥۘۙۗ۬ۨۡۡۘ۫۬ۛ۫۟۠ۧۚ۫۠۟ۡۘۜۘۦ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:141:0x01eb, code lost:
    
        switch((r0.hashCode() ^ (-6362503))) {
            case -1246924746: goto L462;
            case -31404517: goto L463;
            case 584071210: goto L456;
            case 1344473814: goto L455;
            default: goto L465;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:144:0x01f1, code lost:
    
        if (com.iqoocg.nm.fcRuQsQrcxOAzxwEalcM.APPLICATION == null) goto L464;
     */
    /* JADX WARN: Code restructure failed: missing block: B:145:0x01f3, code lost:
    
        r0 = "ۡۧۙۜ۠ۥۖۨۚ۠۟ۥۧۛۙۤۗۜۘ۠۫ۙۗۘ۟ۙۡۡۦۦۨۙۦۧۨۜۦۘۧۥۧۖ۠ۛۚۛۚۙ۠۬ۡۦۜ۬ۚۚ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:146:0x01f6, code lost:
    
        r0 = "ۥۢ۠۠ۙۘۜۗۜۧ۟۫۟ۖۘۘۛۦ۫ۜۧۧۘۚۧۙۨۦۘۨ۟ۡۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:147:0x01f9, code lost:
    
        r0 = "ۡ۟ۜۘ۫ۚۖۘۛۖۥ۠ۢۘۘ۠ۙ۫ۘۚۤۥۢۡۘۘ۟ۤۙۢۘۘ۠ۗۡۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:148:0x01fc, code lost:
    
        r0 = "ۛۚ۫ۡۛۙۧ۟ۥۘۡۛۧ۫ۥ۠۫ۙۛۥۥۧۖۡۜ۬۠ۤۦۢۥۜۚۛۨ۬ۨۘۖۧۤۧۜۨۗۨۘۘۘۨۦۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:149:0x0200, code lost:
    
        r0 = "ۗۦ۬ۥ۠ۡۘۦۛۥۘ۠ۚۥۛۖ۬ۢۤۖۘۖۥۖۖۗۡۦۤۤۜۜۦ۠ۜۥۘۥۜۦۘۛۖ۬ۧۦۘ۬ۡ۠ۥۘۖۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:151:0x020a, code lost:
    
        switch((r0.hashCode() ^ (-922486946))) {
            case -126605864: goto L473;
            case 62301015: goto L472;
            case 243018259: goto L474;
            case 348424381: goto L475;
            default: goto L477;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:153:0x020e, code lost:
    
        r0 = "ۤ۫ۦۘۜ۬ۡۙۡۚۘۖۚۨۡۦ۠۬ۗۡۙۚۢۡۨۘۛۢ۬۠ۡۨۜ۟ۨۘۢۜ۫ۦۗۙۢۙۨۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:155:0x0218, code lost:
    
        switch((r0.hashCode() ^ 1121742162)) {
            case -736769078: goto L486;
            case -643431786: goto L485;
            case -309474577: goto L484;
            case 1299866716: goto L487;
            default: goto L488;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:158:0x022a, code lost:
    
        r1 = core.pro.android.notify.s0.decrypt(com.iqoocg.nm.fcRuQsQrcxOAzxwEalcM.APPLICATION, core.pro.android.notify."1234567890abcdef");
     */
    /* JADX WARN: Code restructure failed: missing block: B:175:0x026b, code lost:
    
        r0 = "ۡۨۘ۠ۢۥ۬ۢۖ۬ۦۚۨۜۧۧ۟۬۬۫ۥ۬۫۠ۘۧۘۗۗۗۖۢۙۨۘۦۚ۫۬۠ۘۡۘۜ۫ۦۦۧۖ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:176:0x026e, code lost:
    
        r0 = "ۗۘۨۤ۫ۢۙ۟ۧۡۖۧ۟ۡۚۥۙۛۥۖۨۚۥۚۥۤۥۧۦۘۘۢ۫ۙۜۙۨۧۘ۟ۙۧ۬ۥۥۘۥۨۡ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:178:0x0278, code lost:
    
        switch((r0.hashCode() ^ 509294993)) {
            case -902031143: goto L478;
            case 51419866: goto L476;
            case 233874683: goto L718;
            case 1855085443: goto L719;
            default: goto L720;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:181:0x028a, code lost:
    
        if (core.pro.android.notify."null".equals(com.iqoocg.nm.fcRuQsQrcxOAzxwEalcM.APPLICATION) != false) goto L721;
     */
    /* JADX WARN: Code restructure failed: missing block: B:182:0x028c, code lost:
    
        r0 = "ۘۧۥۗ۫ۥۙۚ۠ۗ۟ۜ۫ۜۘۜۛۡۤۥۜۘ۫ۚۗۦۡۗۚۘ۟ۖۜۦۘ۫ۥۡۜۨۚۖ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x028f, code lost:
    
        r0 = "ۘۤۖۘ۫ۢۗۡۨۧۡۡۥۘۥۦۖۚۛۙۛۘۡۜۤۥۙۤ۟۬ۛ۠ۡۘۧۧۙ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x0292, code lost:
    
        r0 = "۫ۨۢۖۡۖۘۥۛۖۘۙۖۨۧۗۦۘۗۥۛۖۘۙ۬ۨۘۙۘۜۘۦ۬ۤۥ۟ۦۘۥۗۜۘۤۥۙۘ۫ۨ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:185:0x0295, code lost:
    
        r0 = "ۦۛۚۚۤۖۘۘۦۘۥۦۘۥۥۢۙ۟ۦ۫۫ۨۘ۠۬ۢۢۢۙۤۖۘۘۤۚۨ۬ۙۘۜۥ۠ۢۘ۠";
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x0299, code lost:
    
        r0 = "۠ۧۘۗۚ۟ۛۤۧۜۥ۠ۚۘۘۨ۟ۘۗۙۨۘۖ۟ۖۘۨۘۘۘۡۢۦۘ۬ۙ۫ۢۧۙۤۜۘۖۥۗ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:187:0x029d, code lost:
    
        r0 = "ۡۦۦۘۘ۬ۥۚۘۜۘۡۧۨ۫ۚۥۘۛۢۜۜ۠ۘۘۧۘ۫۬۫ۜۘۡۢۨۢ۫۬ۖۚۜۘۖۜۛۢ۟۟ۘۜۨۙۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x02a1, code lost:
    
        r0 = "ۥۛۘ۟ۧۘۘۗۘۖۘۛۡ۬۬۫ۙۤ۟ۤۚۜۘ۫۬ۢ۟ۜۡۘۙۡ۟ۚۙۙۥ۬ۡۙۤۢ۫۫";
     */
    /* JADX WARN: Code restructure failed: missing block: B:190:0x02ab, code lost:
    
        switch((r0.hashCode() ^ 1413896712)) {
            case -2053866833: goto L686;
            case -1478913924: goto L687;
            case -963259748: goto L490;
            case -214188391: goto L489;
            default: goto L689;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x02af, code lost:
    
        r0 = "ۚ۠ۛۖۤۥۘۜۤۧۜۥۘۢۡۥۤۚ۫ۜۧ۟ۖۖۘۦۜ۟ۤۤۨ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x02b3, code lost:
    
        r0 = "ۤۖۧۢۛ۟۠ۢۡۘ۟ۘۨۘۤۧۧ۬ۦۖۘۙۗۧۛ۟ۦۘۗۛۢۤ۟۫ۙ۬ۥۗۖۡ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:195:0x02c4, code lost:
    
        if (core.pro.android.notify."[#APPLICATION#]".equals(com.iqoocg.nm.fcRuQsQrcxOAzxwEalcM.APPLICATION) != false) goto L690;
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x02c6, code lost:
    
        r0 = "ۥۧۙۙۛ۫ۘۖۜۖۗۖۨ۫ۜۧۘۨ۫ۦ۟ۦۚۡۤۖۦۘۗۦۢۦۙۛۜ۠ۥۜۢۢۨۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:197:0x02c9, code lost:
    
        r0 = "ۢۖۤۦۚۧۜۥۖۦۗۧۖۜۦۛۜۥۘۢۛ۠ۜۘۘۘۚۨۙۨۢۜۘۗۖ۟ۧۛۨۛۡۨۗۚۨۗۦۧۢۜۦ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:198:0x02cc, code lost:
    
        r0 = "ۤۧۦۨۨۖ۠۟ۢۙۡۖۧۙ۟ۢۚۡۛۤ۟ۙ۫ۦۢۙ۬";
     */
    /* JADX WARN: Code restructure failed: missing block: B:199:0x02d0, code lost:
    
        r1 = new java.io.BufferedReader(new java.io.InputStreamReader(r12.getAssets().open(core.pro.android.notify."yunzhuru.com")));
        r0 = r1.readLine();
        r1.close();
        r1 = "ۙ۫ۘۘۖ۟۠۠ۡۦۤۗۥۘۧ۠ۜۘ۬ۘۖۧۦ۟ۤۚۖ۬۠ۛۥۙۦۜ۟۠۬ۦۜۥۛۥۘ۠ۢۡۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:201:0x02fb, code lost:
    
        switch((r1.hashCode() ^ (-1505646653))) {
            case -1454173379: goto L699;
            case -131877320: goto L696;
            case 1978470: goto L698;
            case 1324351650: goto L697;
            default: goto L700;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:203:0x02ff, code lost:
    
        r1 = r0.trim();
     */
    /* JADX WARN: Code restructure failed: missing block: B:204:0x0306, code lost:
    
        r1 = "ۦۚۨ۫ۡۨ۬ۤ۬ۜۙۥۛۙۡۘ۫ۦۧۛۖۦۤۡۚۤۦۨۥ۬ۦۨۡۜۧۧۥۘ۬ۚۖ۠ۛ۠ۥۖۧۘۚۖۜۘۦۜۨۘۡ۬۬";
     */
    /* JADX WARN: Code restructure failed: missing block: B:205:0x0309, code lost:
    
        r1 = "ۡۥۧۘۧۡ۫ۨۢۖۘۤۦۥۘ۬ۜۖۨۙۥ۬ۙۦ۠ۘۦۛۦۙۥۚۧۢۧۤۖۢۢۦۤۖ۟ۤۗ۫ۢۧۨۢ۟ۗۘۛ۠۠۟";
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x0313, code lost:
    
        switch((r1.hashCode() ^ 771499801)) {
            case -1451104792: goto L708;
            case 2034662: goto L709;
            case 128608993: goto L701;
            case 2036857941: goto L702;
            default: goto L710;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:209:0x0317, code lost:
    
        r1 = "ۤۘۘۘ۬ۗۥۘ۬ۤۗۢۜۡۤۙۘۘۛۤۛۨۨۢۥۖ۫ۡۦۥۛۖۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:210:0x031a, code lost:
    
        r1 = "۟ۛۜۘۡۨۥۨۡۛۧۚۗۛ۠ۜ۟ۤ۫ۜ۠ۥۦۧۗۢۥۖۘۛ۫ۙۥ۠۟ۛۙۖۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:211:0x031d, code lost:
    
        if (r0 == null) goto L712;
     */
    /* JADX WARN: Code restructure failed: missing block: B:212:0x031f, code lost:
    
        r1 = "۫ۧۧۨۘۘۖۙ۠ۛۡۡۘۖ۫۟۟ۦۡۥۘۧۚۥۘۤۦۡۘ۠ۧۢۡۡۥۘۛۥۥۚۜۜۘۗۛۢۦۧۖۛۦۖۘۙ۟ۥۘۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:213:0x0322, code lost:
    
        r1 = "ۡ۟ۡۘۖۘ۬ۧ۬ۘۙۚ۫ۖ۠ۦۘ۫۬۠ۢۤۤۢۛۘۘۗۨ۫۠ۨ۫۟ۛۙۖۦۥۘۚۘۘۜۚ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:215:0x0325, code lost:
    
        r1 = "ۚۥۡ۬ۚۖ۠۠ۜۘۛۨۨۤۢۡۗ۬ۘۘ۫ۖۢۨۘۘ۟ۨۥۜۦۘۘ۟ۖۧۘۡۘۦۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:69:0x00ed, code lost:
    
        r0 = "ۗۧ۬ۦۨ۫ۥ۠ۘۜۖۘۚۗ۫ۚۗۥۖۗۢۢۜۘۙۨۛۤۘۡۘۗ۟۬ۖ۫ۥۙۢۙۛ۬";
     */
    /* JADX WARN: Code restructure failed: missing block: B:71:0x00f4, code lost:
    
        switch((r0.hashCode() ^ (-1561464964))) {
            case -1313876393: goto L453;
            case -1009607969: goto L451;
            case -780237854: goto L452;
            case -164339113: goto L450;
            default: goto L454;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:73:0x00f8, code lost:
    
        r0 = "ۥۘۧ۫۬ۘۘۥۨۢۦۤۡۘ۠ۥۜ۟ۙۡۤ۬ۚۚ۬ۘۗۗۚۢۥۘ";
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:124:0x01b3. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:66:0x00e6. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:496:0x0239 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:497:0x0247 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:498:0x032c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:499:0x0349 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:503:0x0230 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:518:0x025a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:519:0x059c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:520:0x05bd A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:521:0x05c1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:525:0x0251 A[SYNTHETIC] */
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
        String str4 = "۫ۜۥۘۥۡۢۥۚۤۗۤۧ۠ۚۢۥۙۢۘۦ۫ۚ۟ۨۘ۠ۡۜۢۖۡۘۜۢۥۨۢۖۘۖۡۖۘۥۜۘۘۖۚۛۥ۟ۗۨۖۤ۟ۨۖ";
        while (true) {
            switch (str4.hashCode() ^ 1066918137) {
                case -1307988963:
                    hasInit = true;
                    String str5 = "ۧۥۗ۫ۥۢۘۗۜۖۨۜۥۘۘۛۙ۫ۖ۬ۜۘۖۤۘۘۢ۬ۡ۠۬ۜۘۘۦ۬۬ۨۜۥۢۖۧۥۗ";
                    while (true) {
                        switch (str5.hashCode() ^ (-2109548370)) {
                            case -2126626769:
                                str5 = "۬۟۟ۨۜۡ۫ۘۥۘۚۚ۫ۡۨۦۘ۟ۛۜۘ۟ۦۥۘ۟ۥۥ۟۫ۢۧۙۛۡ۫ۥۘۙۜۡ";
                                break;
                            case 597507443:
                                String str6 = "۟ۥۗۧۚ۠۫ۘۘۙ۬ۥۘۖۨۦۨۨۧۘۤۡۜۧۤۨۘ۟ۙۦۚ۫ۧۦۘۘۙۗ۠ۥۜۘۧۦۖۘۗۜۜۘۜ۫ۦ";
                                while (true) {
                                    switch (str6.hashCode() ^ (-1331706271)) {
                                        case -1748302101:
                                            str6 = "ۥ۠ۥۘۛۛۘۨۜۘۗۘۨۘۙۨۨۤۥ۬۟ۖۤۡۧۘۛۛۚۤ۟ۧۖۨۡۘ۟۫ۙ";
                                            break;
                                        case -1533192969:
                                            if (!Utils.isMainProcess(context)) {
                                                str6 = "ۡۘۡۛۢۡۘۢۡ۟ۜۢ۟ۨ۫ۘۤۘۘۘۧ۬ۖۘۘ۫ۥۧۘۜۙۙۥۗۤ۬۬";
                                                break;
                                            } else {
                                                str6 = "ۘ۬۬۠ۚۜۘ۟ۙۢۚ۬ۦۘۛۨۘۛۗۦۘ۠ۨۡۖ۟ۙ۫ۗۤۖۖۘ";
                                                break;
                                            }
                                        case -1027117231:
                                            str5 = "ۙۥۗۚۥۥۡۛۗۛۚۖۘۙۢۦۥ۫ۡۘۗۜۡۛۖۖۙۡۘۜ۬۠ۙ۟ۤۢۛۚۙ۟ۥۘۥۥۧ";
                                            continue;
                                        case 2099028339:
                                            str5 = "ۘۧۨۘ۫ۧۘ۫۠ۧۘۜۙ۠۬ۙۛۙ۬ۥ۫ۧ۫ۦۖۘۜۧۨۧۤۙۗۚۤۥۛۛۖۦۘۗ۬ۖۘ";
                                            continue;
                                    }
                                }
                                break;
                            case 703807562:
                                return;
                            case 1999520177:
                                String str7 = "۬۫ۤ۟ۘۘۗۦۧۤۢۙۖۢۧۘۖۖۙ۬ۤۥۨ۫ۧۙۥۘۛۙۨ";
                                while (true) {
                                    switch (str7.hashCode() ^ (-2000530677)) {
                                        case -632140833:
                                            String str8 = "۬۬ۡۘۨۧۛۘۤۨۙۦ۟۠۬ۙ۫ۙۤۖۚۙۖۨۛۖۗۡۜۧۨۙۦۘۡ۟ۗۙۡۚۧۥ۠";
                                            while (true) {
                                                switch (str8.hashCode() ^ 703426472) {
                                                    case -1373331123:
                                                        str8 = "ۥ۫۟ۜۜۖۘۧۚ۫۟ۖۢۡ۟ۤ۫ۤۦۘ۠ۧۥۦۤۖۛۨۙۨۨۢۤۥۦۘۙۡۜۨۥۤۜۢۥ";
                                                        break;
                                                    case 37075195:
                                                        if (!"[#VPNCHECK#]".equals(fcRuQsQrcxOAzxwEalcM.VPNCHECK)) {
                                                            str8 = "ۥۧ۬ۤۚۛۛ۠ۥۤۦۨ۠ۜۨۘۗۨۖۦۜ۟ۗۘۖۥۖ۬۟ۙۢۙ۠ۥ۬ۖۜ";
                                                            break;
                                                        } else {
                                                            str8 = "ۧ۟ۤ۠ۘ۬ۧ۠ۙۥۖۥۘۧ۠ۘ۫ۜۘۛۦۡۘۗ۠ۙ۫ۥۢۧۥۗۚۚۡۘۧۚۛ";
                                                            break;
                                                        }
                                                    case 86280526:
                                                        str7 = "ۜ۫ۨۘ۟ۜۥۘۛۙۤۖ۟ۖۘ۟ۙ۠۫۬ۥۘۦ۟ۨۦۡۧۘۚۨۦۙۥۘۨۥۧۨ۟ۛۧۧۦۜۨۤۘۡۡۜۨۙۖۗۦۘۜۥۥ";
                                                        continue;
                                                    case 1279502014:
                                                        str7 = "ۖ۟ۘۘۢۜۢ۠ۛ۬۬ۛ۬ۢۛۧۥۤۨۘۡۨۖ۟۬ۙۡۖ۫ۥۥۧۡۨ۠ۧۖۘ";
                                                        continue;
                                                        continue;
                                                }
                                            }
                                            break;
                                        case 1422109275:
                                            String str9 = "ۨۗۤۛ۠ۘۘ۬۫ۛۨۙۦۘۚۗۢ۠ۧۖۘ۟ۦۖۨۤۖۘۗۢۖۛ۬۫ۜۖۦۘۥ۠۫ۛ۟ۥ۬ۦۨۧ۠ۤۤۨ";
                                            while (true) {
                                                switch (str9.hashCode() ^ (-45300873)) {
                                                    case -1634633307:
                                                        Process.killProcess(Process.myPid());
                                                        return;
                                                    case -1253986797:
                                                        break;
                                                    case -603340582:
                                                        str9 = "۠ۜۚۥۙۘۧۨ۫ۖ۬ۖۘۛۛۘۤۦۙ۠ۗۦ۫ۡۛۤۙۧۗۙۧۙۢۡ۫ۤ۟";
                                                        continue;
                                                    case -478773722:
                                                        String str10 = "۟ۨۨۘۥۡۨۘۛ۟ۡۤۜۛۛ۟ۦۘۡ۟ۤۗۢۥۘۘۧۨۘۙۦۧ۫ۗۧۨ۬ۜۘۤ۟ۚۧ۟ۨۘ۠ۛۜۛۖۦۘۤۥۜۘ";
                                                        while (true) {
                                                            switch (str10.hashCode() ^ (-827185296)) {
                                                                case -805822639:
                                                                    str9 = "ۛۜ۠ۖۡۚۧۜۤۢۡۘۨۡۛ۬ۖۘۗۡۜۘۗۢ۬ۘ۫ۥۦ۟ۢۛۤۛۤۥۛۦۡۘ۬ۡۢ";
                                                                    continue;
                                                                case -260543243:
                                                                    str10 = "ۧۡۨۘۛۥۗ۟۬ۘۨ۠ۥۘۢۜۦۘۦۤۨۧ۟ۦۢۗۨ۠ۚۢۜۨ";
                                                                    break;
                                                                case 1399243924:
                                                                    str9 = "ۡ۬ۡۜۤۤ۠ۢۡۘۥۛۘۨ۫ۤۗۤۦۘۖۜ۫ۨ۬ۖۗۡۨۘ۫ۥۛ۬ۜۧۘۖۘۥۘ۠ۙۡۘ۬ۖ";
                                                                    continue;
                                                                    continue;
                                                                case 1974474423:
                                                                    if (!Utils.isVpnActive(context)) {
                                                                        str10 = "ۡ۫۟ۜۛۜۢۘۥۘۤۙۢۧۦ۠ۦۥۛۦۛۖۘۖ۫ۥۧۚۜۚۘۜۘ";
                                                                        break;
                                                                    } else {
                                                                        str10 = "ۡۙۖۘۦۡۗ۟۬ۖۘ۬ۥ۠ۧۡۡۤۗۖۘۥ۫ۤۧۨۗۢۨۛۙۢۘۥۜۘۤۜ۟ۘ۬ۢۦۨۗ";
                                                                        break;
                                                                    }
                                                            }
                                                        }
                                                        break;
                                                }
                                            }
                                            break;
                                        case 1479955451:
                                            break;
                                        case 1731224090:
                                            str7 = "ۛۥ۟۫ۦۜۘۙۧۜ۠ۚۤۤۥۘ۬ۗۤۙۨۨۘۥ۫ۤۛۨۦ۟ۗ۬";
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
                                    Log.e("HookApplication", "initNativeHook 加载失败", th2);
                                }
                                try {
                                    ByteHook.init();
                                } catch (Throwable th3) {
                                    Log.e("HookApplication", "bhook 加载失败", th3);
                                }
                                try {
                                    int i = Build.VERSION.SDK_INT;
                                    String str11 = "ۛۖۜ۟ۨۜ۫ۘۡۚۘۗ۠ۥۦۜ۬ۖۘ۫۫۟ۤۦۘۨۘۜۗ۟ۨۘۘۛ۫ۛۡۡۘۥۜۢۗۙۤۥۖۙۚۤۙ۠ۨۗۨۘ";
                                    while (true) {
                                        switch (str11.hashCode() ^ 1187835307) {
                                            case -1627661385:
                                                try {
                                                    line = ShellAppComponentFactory.getOriginAppClassName();
                                                    break;
                                                } catch (Throwable th4) {
                                                    break;
                                                }
                                            case -1208493856:
                                                break;
                                            case -856152886:
                                                str11 = "ۧۙۦۛۢۖۘ۫ۥۛۛۧ۫ۘۡۤۥۨۥۙۛۖۘۙۢۥۨۤۛۦۧ۠ۘ۫ۚ۟۬ۚ";
                                            case 950403846:
                                                String str12 = "ۤۚۜ۬ۜۙ۟ۖۦۘ۬ۚۨۘ۫ۚ۬۫ۡۜۛۗۖۧ۫ۢ۟ۧۜ۫۬ۜۘ۫ۘۤۘ۠ۜ۠ۗۚۛۘۙۚۜۡۘۜۚ۬";
                                                while (true) {
                                                    switch (str12.hashCode() ^ 208554267) {
                                                        case -432845826:
                                                            if (i < 28) {
                                                                str12 = "ۜۨۢۧۦۧۘۧۢۗۚۧۘۢۗۡۘۤ۠ۦۘۗ۟ۛۛۚۦۧۡۖۚۗۡ۬ۛ۟۫۠۬";
                                                                break;
                                                            } else {
                                                                str12 = "ۚۗۧۘۨۜۘۡۨۖۛۤۖۦ۟۟ۤۤۜۘۘۗۥۘۚۡۜ۬ۘۥۘۘۧۖۘۖۥ۫ۢۦۘ";
                                                                break;
                                                            }
                                                        case -35940902:
                                                            str11 = "ۚ۬ۡۘۨۥۦۘۖۜۖ۟ۖۘۘ۟ۢ۟ۡۛۖۘ۬ۤۖۨۙۡۛۙۦۜۥۘ۟۬۫ۘۢۗۦۨۡۘۙۥ۠ۙ۬ۘۜۙۙ";
                                                            continue;
                                                        case 608847369:
                                                            str11 = "۠ۚۜۘ۟ۘۙۤۗۢ۟ۡ۟ۦۛۙ۟۬ۚ۟ۢۖ۟ۖۜۙۙۘۧۗۛۘ۫۠ۡۚ۬ۖۘۖۘۛ۠ۘۨۗ۠ۙ";
                                                            continue;
                                                        case 779103782:
                                                            str12 = "ۖۚۖۚۙۦۗۜۨ۫ۘۧۘۘۘۦۥۛ۬۠ۥۧۚۚۚۡۛۖۘۡۛ۫";
                                                            break;
                                                    }
                                                }
                                                break;
                                        }
                                    }
                                    line = null;
                                    str2 = "ۚۧۡۡۡۖ۠ۚۖۡۧۤ۠۠ۡۚۨۜۛۖ۠ۥۙۗۢۢۚۤۙۡۘۘۤۜۥ۠ۚ۠ۘۙۨ۫ۨ۠ۜ۬ۧۗۤ";
                                } catch (Throwable th5) {
                                    th5.printStackTrace();
                                }
                                while (true) {
                                    switch (str2.hashCode() ^ (-994793022)) {
                                        case -2027809734:
                                            String str13 = "۠۟۠۫ۗۨۢۘ۫ۧۥۛۢۛۨۘۙۤۚۤۢۢ۫ۖۧۘۧۧۡۘۜۧۚ";
                                            while (true) {
                                                switch (str13.hashCode() ^ 1197091063) {
                                                    case -1573379493:
                                                        str13 = "۟ۗۡۘۛۦۜۘۢۛۚۡ۫ۦ۟ۢۢۢۖۥۨۥۨۡۖۧۘۦۗ۟ۚۚۘۘ";
                                                    case -850993641:
                                                        break;
                                                    case -611540495:
                                                        break;
                                                    case 1294962668:
                                                        String str14 = "ۖۜۙ۠ۦۗۥۗۗۢۡۧۘۤۦ۟ۖۘۛ۫ۜۥۘۥ۬ۡۥۙۘ۠ۘۨۡۥ۬۟ۙ۬۫ۦۗۗۦ۟۟ۧۚۨ";
                                                        while (true) {
                                                            switch (str14.hashCode() ^ (-1581661531)) {
                                                                case -1832516218:
                                                                    if (!line.isEmpty()) {
                                                                        str14 = "ۘ۫۟ۜۚ۬۟۠ۛۧۘۘۘۗۥۨۖۢ۠ۢ۟ۜۘۙۧۨۘۧۗۧۙۘۨۘۜۧۤۦۗ۠";
                                                                        break;
                                                                    } else {
                                                                        str14 = "ۛۤ۠۫۬ۨۘۙۤ۫ۢۢۚۗۗۡۘۨ۫ۥ۬ۡ۟ۦ۠ۦۘۘۜ۠۬ۧۗۡۨۖۘۛ۬ۘۚۦۢۡۡۘ";
                                                                        break;
                                                                    }
                                                                case -1515821322:
                                                                    str14 = "ۡ۬۠ۢۤۗۖۘۧۧۨ۠ۢۤۥۚۖۧۤۗۧۥ۠۠ۤۥۧۦۖۤ۟۟ۨۘۚۗۜۘۚ۠۬";
                                                                    break;
                                                                case 444496871:
                                                                    str13 = "ۨ۟ۙۙۖ۟ۦ۬۫۠ۡۖۘ۠۬۠ۗۧ۠۟ۤۢۡۡۨ۫ۚۨۙۛۨۘۡ۠ۨۢۛۡۘۥۚ۠ۛۦ۟";
                                                                    continue;
                                                                case 1935221401:
                                                                    str13 = "ۡۚۗۢ۟ۖۨ۟ۗ۬۬ۖۢۗۦۨ۬ۖۘۚۛۥ۟ۤۛۜۢۙۙۙۡۘ۠ۘۜۙۜۤۜۤۤۥۤۘ";
                                                                    continue;
                                                            }
                                                        }
                                                        break;
                                                }
                                            }
                                            break;
                                        case 339751424:
                                            str2 = "ۗۡۧۘۢ۟ۥۖ۟ۜۘۡۘۨۢۗۥۢۦۘۤۧ۟ۨۥۜۘۚۥۘ۫ۨۜۚۥ۬۬ۧ۟ۜ۟ۥۘۨۘۥ";
                                        case 798169228:
                                            String str15 = "۠۫ۜۖۛۘ۬ۥۖۘ۫ۜ۟ۛۚۚۛۧ۫ۤ۬ۡۘۗۛۘ۟۬ۦۧۦۜۧۖۡۘۙۘۖۛۡۡۘۡۙۜۘ";
                                            while (true) {
                                                switch (str15.hashCode() ^ 1299200857) {
                                                    case -817745420:
                                                        str15 = "ۘۡۢۤۜۘۦۢۖ۬ۚۙۧۧۗۛۨۢ۟ۜۤۡ۠ۗ۫۫ۥۘۥۛۖۘ۫ۢ۟ۧۡۨۘۨۡۘ۫۟ۦۗۨ۟ۜۥۥ";
                                                        break;
                                                    case -206460889:
                                                        if (line == null) {
                                                            str15 = "ۘ۫ۧۧۨۙۙۘۜۙۜۜۧۨۡۜۤ۬ۚۥۜۦۧۧۜۖۢۚۖۖۛۤ۠ۛۨۘ۫ۛۘۘۤ۫۫۠ۥۜ۟ۖۤ";
                                                            break;
                                                        } else {
                                                            str15 = "ۙۡۥۘۘۗۦ۫ۥ۫ۡۗ۠ۚۥۖۖۦۨ۬ۡۜۘۜۥۚۚۙۗ۠ۡۦۘۗۢۦۘۥۖۧۘۦۚۖۘ۫ۡۡۘۦۗۘۘۛۨۧۥۛۢۗ۬ۤ";
                                                            break;
                                                        }
                                                    case 149653618:
                                                        str2 = "ۤ۠ۜۘۖۨ۫ۡۡۘۦۗۗۚۜۨۗۨۛ۬۟ۚۧۨۙ۟۠ۖۧۨۖۘ۬ۙۡۘۦۛۗۗ۟۟ۤ۬ۢ۬۠ۖۙۦۨۘ۫ۦۖۡ۠ۨ";
                                                        continue;
                                                    case 1380023288:
                                                        str2 = "ۦۥۖۗۘۜۘۖ۠ۘۖۜ۫ۗۜۡۖۗۥۘۥۢۖۚۡۦۘۧۨۤۙۜ۟ۧۦۘۡۙۧۙۖۤۗۥۘۘ۟۠۟ۗۖۘۡۜۨۙۢ";
                                                        continue;
                                                }
                                            }
                                            break;
                                        case 1691187650:
                                            break;
                                    }
                                    str3 = "ۜۙۡۦۙۥۘ۠ۚۜۘۛۗۙ۫۟ۗ۫۬ۢۢۨۤۥ۟ۡۘۜ۫ۥۘۢۦۙۜۘۨۛ۬ۘۘۨ۬۫۫ۜۛۨۦۙۧۧۗ";
                                    while (true) {
                                        switch (str3.hashCode() ^ (-1091865696)) {
                                            case -199458234:
                                                String str16 = "ۢۡۨۘۧ۫ۜۘۙ۬ۛۚ۠ۙۢۖۗۛۖ۫۫ۘۙۤۗۖۖۤۧۡۤۘۘ";
                                                while (true) {
                                                    switch (str16.hashCode() ^ (-548343817)) {
                                                        case -1976085317:
                                                            String str17 = "۫ۖۘۜۧۙۦۡۧۘۤۨ۫ۧۘۜۘۦۡۘۚۗۦۥۛ۠ۗۤۤۢۡۢۤ۠ۜۗۚۦۘ۠ۗۗۤۤۛ";
                                                            while (true) {
                                                                switch (str17.hashCode() ^ 1000711903) {
                                                                    case -1938070065:
                                                                        break;
                                                                    case 1124021650:
                                                                        String str18 = "ۡۚۢۡۜۛۦۛۦۖۜۘۖۦۨۘۗۜۗۢۖۖۙۗۜۘ۟ۧۗۦۦۛ";
                                                                        while (true) {
                                                                            switch (str18.hashCode() ^ 382086062) {
                                                                                case -1790799200:
                                                                                    str18 = "ۚۦۖۘۤۧ۠۫ۘ۟ۢۘۘۛۘۖۘۧۥۚۨۘۘۚۜۧۘۚۜۦۘۦ۫ۦ۬ۢۨ۬ۛۙۦۜۙۖ۫۟";
                                                                                    break;
                                                                                case -1238713471:
                                                                                    str17 = "ۡ۫ۤۤۖۖۙۤۦۥۚ۟ۢۖۙۘ۫ۘۜ۟ۜۦ۫ۛۛۚۖۘۗۘۘۘ";
                                                                                    continue;
                                                                                case -1036681088:
                                                                                    if (!"android.app.Application".equals(strTrim)) {
                                                                                        str18 = "ۚ۠ۨ۠ۡۛ۬ۦۥۖۘ۠ۤۧۖۘۨۨ۫ۡۙۧ۟ۙۢۛۧۥۖۘۜۚۜۘۢۘۤۛۧ۟ۨۡۙ";
                                                                                        break;
                                                                                    } else {
                                                                                        str18 = "ۧۡۧۘۗۢۡ۬ۚۘۘۧۤۡۘ۠ۢۡۘۜۦۨ۫ۨۙۦ۫ۡۘۢۛۘۙۢۜۦۜۘۘۜ۬ۦۘۜۜۘۘ۠ۤ۫ۨۧ۠ۘ۠";
                                                                                        break;
                                                                                    }
                                                                                case 546890612:
                                                                                    str17 = "ۨۚۖۘۖ۟۠۟ۨۚۚۥۖۧۥۤ۠۠۠ۥ۬ۘۨۨۘۘۡۤۜۘۡۖۧۘۡ۟۫۟ۙۘۘۛۦ۟ۙۧ۫ۚۗۗۖۡ۫ۢۨۧۥۜۘ";
                                                                                    continue;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case 1507518151:
                                                                        str17 = "ۖۙۘ۫۬ۜۦۘۨ۫ۤۙۚ۠ۚۖۥۡۘۦۨۨۘۨ۬ۡۛ۬۠ۨۦۙ۟ۨۨۗۛۜۘ۟ۘۦۘۖ۠ۖۘۜۡ۠ۘۤۧۥ۬ۜۧۗ۬";
                                                                        break;
                                                                    case 1889869032:
                                                                        Class<?> cls = Class.forName("android.app.ActivityThread");
                                                                        Method declaredMethod = cls.getDeclaredMethod("currentActivityThread", null);
                                                                        declaredMethod.setAccessible(true);
                                                                        Object objInvoke = declaredMethod.invoke(null, null);
                                                                        Field declaredField = cls.getDeclaredField("mInstrumentation");
                                                                        declaredField.setAccessible(true);
                                                                        Application applicationNewApplication = Instrumentation.newApplication(Class.forName(strTrim), context);
                                                                        this.originAppInstance = applicationNewApplication;
                                                                        Field declaredField2 = cls.getDeclaredField("mInitialApplication");
                                                                        declaredField2.setAccessible(true);
                                                                        Application application = (Application) declaredField2.get(objInvoke);
                                                                        declaredField2.set(objInvoke, applicationNewApplication);
                                                                        Field declaredField3 = cls.getDeclaredField("mAllApplications");
                                                                        declaredField3.setAccessible(true);
                                                                        Object obj2 = declaredField3.get(objInvoke);
                                                                        String str19 = "ۜ۠ۥۥۦ۬ۧۡۚۛۦۘ۠ۦۧۢ۬ۡۤۥ۠۠ۨۘۙۜۘۘۨ۫۫ۘ۟ۘۘۨۘۖۘ۠ۢ۠ۛ۠ۡ";
                                                                        while (true) {
                                                                            switch (str19.hashCode() ^ 287102558) {
                                                                                case -2110329607:
                                                                                    break;
                                                                                case 796400363:
                                                                                    List list = (List) obj2;
                                                                                    list.remove(application);
                                                                                    String str20 = "ۛۥۨۘۢۢۡۨۥۙۦۢۚۤۗۖۘ۠۫ۡۖ۠ۨۥ۟ۢۙ۬ۚۗۛۘۘۥۧۦۘۡۛ۬ۨۖۗۨۙۛۚۦۡۤ۬ۨۘ۬۬ۜۘۛۛ۬";
                                                                                    while (true) {
                                                                                        switch (str20.hashCode() ^ 493253080) {
                                                                                            case -1433179220:
                                                                                                break;
                                                                                            case -949033087:
                                                                                                String str21 = "ۚۦۘۧۥ۟۠ۡ۬ۤۡۤۖۢۛۜۤۧ۟ۨ۬ۦۦۘۗۙ۠ۢۦ۟ۘۚۦۛۡۘۤ۫ۛۥۥۙۛۙۡۘۢۛ۬ۦۢۨۘۧ۟ۗ";
                                                                                                while (true) {
                                                                                                    switch (str21.hashCode() ^ 497409874) {
                                                                                                        case -1761642310:
                                                                                                            str20 = "ۛۘۥ۬ۡۨۨۙۨۘ۟۟ۘۘۧ۫ۦۘۘ۬ۦۘۢ۟ۨ۫ۨۖۚ۫ۚۗۛ۠";
                                                                                                            continue;
                                                                                                            continue;
                                                                                                        case -1362623759:
                                                                                                            str20 = "ۜ۬ۘۛ۫ۜۜۦۦۤۢۘۘۨ۟ۥۘ۠۬ۚۨ۟ۦۜۚۙۥۛۜۖۧۢۡۘ۠۟ۨۥۘ۬ۧۧۤ۟ۢۚۖۡۙۤۗ۬ۜۘۨۥۗ";
                                                                                                            continue;
                                                                                                        case 2060061001:
                                                                                                            if (!list.contains(applicationNewApplication)) {
                                                                                                                str21 = "ۜ۟۬ۤۦۖۘۜۗۙۚۙۘۙۥۨۘ۟ۦۙۡۚۜۢۖۦۢ۟۟ۖۤ۫ۨۤ۟ۥۢۖ";
                                                                                                                break;
                                                                                                            } else {
                                                                                                                str21 = "۠ۘۡۘ۬ۤۢۛۙۛ۬۫ۘۘۚۘ۬ۚۦۖۘۨ۠ۥۘۥۗۥۘۦۙۙۡۜۢۜۖۗۥۥ۬ۨۦ۟۠ۦۧ۫۫۫ۛۗ۫۠ۤۤۖۚۨ";
                                                                                                                break;
                                                                                                            }
                                                                                                        case 2140119900:
                                                                                                            str21 = "ۥۡۘ۟ۡۧۘۤۨۙۧۜۥۘۢۧۨۘۖۤۛۧۦۧۘۖۖۜۘۛۙۨ۬ۙۡۡۛۨۡۥۘۚۦۙۘۧۦۘ";
                                                                                                            break;
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                            case -530615541:
                                                                                                list.add(applicationNewApplication);
                                                                                                break;
                                                                                            case -332938703:
                                                                                                str20 = "ۚۜۢۡۨ۠ۗۚۚۦۧۜ۠ۡۥ۟ۘۗۤۢۛۨۚۡۙۛۖۛ۫ۢۛۖۘۥ۠ۖۘ";
                                                                                                continue;
                                                                                            default:
                                                                                                continue;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case 952720201:
                                                                                    str19 = "۬ۗۨۛۤ۠ۦۥۘۘۥۚۜۘۡۖۘۖۨۜۧۙ۬ۨۗ۠ۦ۬ۤۢۦ۟۫ۢ۠ۦۜۧۚۜ۠ۖۚ۬ۦۡۘ۠ۗۜ۟ۡۛۜۙ۬";
                                                                                    continue;
                                                                                case 1319589408:
                                                                                    String str22 = "ۙ۟۬ۡۦۜۜۢۙۡۧۨۘ۬ۧۜۦ۬ۘۘۘۨۘۘۦۧ۠ۖۚۡۘۜۖۦۘ";
                                                                                    while (true) {
                                                                                        switch (str22.hashCode() ^ (-2108618933)) {
                                                                                            case -2113964484:
                                                                                                str19 = "۬ۚۨۜۗۨ۬ۡۘ۟ۡۡۘۗۙۙۖ۠۟ۨ۠ۨۢۦۚۖ۫۟ۘۥۦۘۤ۬ۥۜۙۜۘۢۢۖ۬ۚۘۘۘۡ۠ۧۚۗ";
                                                                                                continue;
                                                                                                continue;
                                                                                            case -651286383:
                                                                                                str22 = "ۥۙۥۘۚۙ۫ۘۖ۠ۧۥ۫۫۠ۜۘۨۗ۟ۢۗۧۚۚۨۘۥۜۚۦ۫ۖۨۨۧۛۖۘ";
                                                                                                break;
                                                                                            case 1050312154:
                                                                                                str19 = "ۥۙۜۘۙۤۤۛۛۛۢۥۧۦۚۡۘۨۘۗۚۨۡۘۚۡۥۘۗۥۘ۫۟ۜۘۡۥۡۘۗۡۘۘ۬۬ۚۥۙ۟";
                                                                                                continue;
                                                                                            case 1933845132:
                                                                                                if (!(obj2 instanceof List)) {
                                                                                                    str22 = "ۥۗۢۥۡۨۘۖۖۘۘۗۡۙۢۜ۠ۗۖۤۛۦۘۖۦۨۚۘۦۗۜۘ۫ۛۨۗۡ۫";
                                                                                                    break;
                                                                                                } else {
                                                                                                    str22 = "ۥۦۘۧۘ۟ۖۘۦۘ۬ۥۖ۟ۖۚۗۡۜۚۚۥۘۜۧۖۘ۟ۥۥۘۢۦۘ";
                                                                                                    break;
                                                                                                }
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                default:
                                                                                    continue;
                                                                            }
                                                                        }
                                                                        Field declaredField4 = cls.getDeclaredField("mPackages");
                                                                        declaredField4.setAccessible(true);
                                                                        Object obj3 = declaredField4.get(objInvoke);
                                                                        String str23 = "۫ۧۡۘۢۢۥ۫ۗۚۥۛ۟ۜ۟۬۫ۙۡۘۢۢۘۘۦۨۡۛ۟ۦ۫ۙۦۘۜۘۥۘ۟ۘۤۙۨۘۥ۠ۖۤ۬ۢۥ۬ۗ";
                                                                        while (true) {
                                                                            switch (str23.hashCode() ^ (-889061530)) {
                                                                                case 645442669:
                                                                                    break;
                                                                                case 981480870:
                                                                                    Object obj4 = ((ArrayMap) obj3).get(getPackageName());
                                                                                    String str24 = "ۢۥۧ۠ۖ۫ۤۘۘۤۛۨۢۖۢۖ۫ۨۘۨۤۚۖۡۜ۟ۖۧ۫۟ۗ";
                                                                                    while (true) {
                                                                                        switch (str24.hashCode() ^ 459937645) {
                                                                                            case -1511266456:
                                                                                                str24 = "ۙۡۨۙ۫ۢۗۡۖۘۥۥۙ۠۫۟ۘۨ۫۬ۘۧۙۤ۬ۨۙۗۗۗۦ۫ۧ۠ۜ۠";
                                                                                                continue;
                                                                                            case -1181786477:
                                                                                                obj = ((WeakReference) obj4).get();
                                                                                                break;
                                                                                            case 860877517:
                                                                                                break;
                                                                                            case 1717363798:
                                                                                                String str25 = "ۛۧ۫ۢۢۦۘۗۙۗۡ۟ۨۛۥۖۚ۟ۧۢۡۤۦۥ۟۟ۡۜۚۚۖۘۨۢۜۘۦۥ۫ۘۖۛۚۗۜ";
                                                                                                while (true) {
                                                                                                    switch (str25.hashCode() ^ (-1126525437)) {
                                                                                                        case -1708222480:
                                                                                                            if (!(obj4 instanceof WeakReference)) {
                                                                                                                str25 = "۫ۖۙۨۤ۬ۙۖۧۧۘۖۚ۠ۡۘ۟ۖۥۗۛۤۤۚۛ۠ۛۜۙۛۗۚۦۡۘۢۡ";
                                                                                                                break;
                                                                                                            } else {
                                                                                                                str25 = "ۧ۬ۗۙۘ۬ۥۢۙۛ۫ۥۘۙۤۥۡۖ۫ۘ۬ۨۥ۠ۦۘۚۛۦۘۛۡۤ";
                                                                                                                break;
                                                                                                            }
                                                                                                        case -895818246:
                                                                                                            str24 = "ۥۜۡۘۚۙۗ۬ۥۦۘۙۜۥۘۛ۫ۥۘ۠۬۫ۤ۟ۢۖۖ۟ۡۨۧۦۖ۬";
                                                                                                            continue;
                                                                                                            continue;
                                                                                                        case -724180375:
                                                                                                            str25 = "۫ۦۜۚۛ۠۬ۖۦۛۗۖۘۢۧۙۧۚۥۘ۟ۘ۬ۡۜۦۥۘۨۘۤۘۖۘۘۡ۟ۧۨۥ۬ۥ۠ۦۜۥ۟ۘۜۘ۟ۜۖۘ";
                                                                                                            break;
                                                                                                        case -371831124:
                                                                                                            str24 = "ۧ۬ۘۙ۠ۘۨ۬۫ۥۙ۬ۗۜ۫ۤۛ۟ۛۚۘۚۨۗۡۢ۬ۨ۠ۥۢۙۦ۟۠ۥۘ";
                                                                                                            continue;
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case 1511418811:
                                                                                    str23 = "ۨ۟ۖۜ۬ۡۘۦ۠ۘۤۘۘۘۧ۬ۢۜۘۗۦ۬ۘۜۤ۬ۙ۬ۛۘۗۙۙۖۗۨۜ";
                                                                                    continue;
                                                                                case 1600638214:
                                                                                    String str26 = "ۡۤۧۦ۠ۥ۬ۨۦۙۨۜۡۙۧ۬ۗۨۧۡۛۜ۠ۦۘۜۦۧۘۤۢۡۘۧۙۡۘۘۛۛ";
                                                                                    while (true) {
                                                                                        switch (str26.hashCode() ^ (-1165655736)) {
                                                                                            case -2009526935:
                                                                                                str23 = "ۨ۠ۤۚۚۤ۟ۧۗۖۦۧۘۧۤۨۘ۠ۙ۫ۖۘۖۗۡۘۢۚۡۘۖۢ۟";
                                                                                                continue;
                                                                                                continue;
                                                                                            case -1687500536:
                                                                                                str26 = "ۧۦۖۡۦۨ۠ۘۖۘۤۤۥۘ۠۬۬ۘۥۦۘۛۗۛۜ۠ۧۦ۫ۦۘۘۚۦۧۨۛۡۨۛۢۨۡۘۘۢۘۘۡ۬۬ۨۛۖ۫ۚۜۡۙۖۘ";
                                                                                                break;
                                                                                            case -1186397624:
                                                                                                if (!(obj3 instanceof ArrayMap)) {
                                                                                                    str26 = "ۙۖ۟ۗ۬ۚۦۨۚۜۙۖۘۛۥۜۘ۫ۢۢۦۨۜۘۚ۟ۗۢۨ۠۟ۛۛۗۨۖۘ۟ۚۦۘۛ۬ۙۧ۬ۡۘ";
                                                                                                    break;
                                                                                                } else {
                                                                                                    str26 = "ۦ۫ۜۜۧۘۘۨۥۘۖ۟ۚۘۜۘ۠ۨ۠ۦۥۖۨۢۘۘۛ۟ۦۘۦۛ۠۟ۗۚۘۖ";
                                                                                                    break;
                                                                                                }
                                                                                            case 2075922868:
                                                                                                str23 = "ۗۖۨۤۚۜۘۜ۬ۦۘۛۦۖۘۜۨۥۤۦۧۘۤۚۤۦۧۗۦۧۖۨۨ";
                                                                                                continue;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                default:
                                                                                    continue;
                                                                            }
                                                                        }
                                                                        String str27 = "ۢۡۜ۟۬ۖۛ۬ۥۨۗۜۘۖۧۙۖۦۡۛۢۖۘ۠ۘۢۙۜۥۦۙۜ";
                                                                        while (true) {
                                                                            switch (str27.hashCode() ^ 362903820) {
                                                                                case -634245353:
                                                                                    str27 = "ۙۥۦۚ۬ۘۘ۬۫ۡۘ۫۠ۦ۬ۢۨۘ۟ۜۚ۟ۦۤۤۤ۬ۦۜۥۘۡۘ۠ۖۦ۫ۦ۟۠ۙۙۘۛ۬ۨۘۙۘۥۥ۬ۛۛ۬ۗۡۙۗ";
                                                                                    break;
                                                                                case -590008729:
                                                                                    Field declaredField5 = obj.getClass().getDeclaredField("mApplication");
                                                                                    declaredField5.setAccessible(true);
                                                                                    declaredField5.set(obj, applicationNewApplication);
                                                                                    Field declaredField6 = obj.getClass().getDeclaredField("mApplicationInfo");
                                                                                    declaredField6.setAccessible(true);
                                                                                    ((ApplicationInfo) declaredField6.get(obj)).className = applicationNewApplication.getClass().getName();
                                                                                    break;
                                                                                case 807814888:
                                                                                    break;
                                                                                case 1861694784:
                                                                                    String str28 = "ۡ۠ۜۜۡۖ۟ۨۧۘ۬ۖۜۘ۬ۜ۫۠ۘ۬ۛۧۗۙ۫ۦۘۚۜۧۘۢ۬ۜ";
                                                                                    while (true) {
                                                                                        switch (str28.hashCode() ^ 1671248811) {
                                                                                            case -1498502598:
                                                                                                str27 = "ۨۖۨۥۦۨ۠ۚۗۧۖ۬ۘ۟ۗۗۗۖۘۘ۟۠ۛۖۥۘۤۜۘۘۦۛۛۦ۬ۗ۟۠ۜۘ۫ۦۚۦۡۧۤۥۡۘۜ۬ۖ۠ۦ۬ۥ۠ۦ";
                                                                                                continue;
                                                                                            case -1403961824:
                                                                                                str27 = "ۨۨۙ۠ۨۘۤۢۖ۫۟ۜ۬ۖۗۘ۠ۧۡۨۥۜ۫۫۫ۦۦۢۗ۬ۤۤۡۢ۠ۖۙۗ۬ۘۥۘۚۛۖۡۧۡۘ";
                                                                                                continue;
                                                                                            case 83647117:
                                                                                                str28 = "ۦۜۦۛ۫ۜۖۨۗۘۖۚۨۧ۟۟ۤۤۛۦۙۦۡۥۜۜ۟۫ۤۦۜۜۛۘ۟ۡۦۡۥۖۢ۫ۙۛۗۜۤۘ";
                                                                                                break;
                                                                                            case 1997052726:
                                                                                                if (obj == null) {
                                                                                                    str28 = "ۧۥۖۗۥۘۘۚ۠۠ۧۦۥۘۙۨۡۛۜۡۡۨۥۥۚۘ۬ۜۢۛۤۤۘۥۙۙۘۡۘۢۛۤۚۡۘۘ۠ۢۥۘ۫ۙۦ";
                                                                                                    break;
                                                                                                } else {
                                                                                                    str28 = "۫ۛ۫۫۟ۗۗۗۨ۬ۡۨۧۨۡۡۢۖۜۤۗۘۧۦ۬ۦۛۚۙۢۧۜۘۜۧۖۚۨۛ۫۫ۤۜۤۜۖۜۘۚ۠ۜۘۛۚۖ";
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
                                                        case -1684390487:
                                                            String str29 = "۟ۙۥۦۖۤۘۤۨۚۖۧۤۥ۠ۛۡۖۘۡۘۨۢۧۡۘۖ۟ۚۤۙۨۘۡۧۙۘۘۧۚۖ۟ۡ۟ۨ۫ۨۘۜۗۧۡۨۥ۬۠";
                                                            while (true) {
                                                                switch (str29.hashCode() ^ (-109192112)) {
                                                                    case -1799160620:
                                                                        str16 = "ۦۙ۫۟ۤۗۨ۠ۗ۫ۦۜۘۙۖۤ۫ۗۦۙۛۡ۫۠ۥۨۢ۠ۡۜۛۗ۠ۥ۟ۖۘۢۨۘۤۡۜۘ";
                                                                        continue;
                                                                    case -1229784040:
                                                                        str16 = "ۛۤۖۡۥۥۘۛۚۤۨۦۡۘ۠ۙۦۗۤۜۘۘۦۥۨ۟ۥۘۦۦۚۜ۟۟";
                                                                        continue;
                                                                    case -278757634:
                                                                        if (!strTrim.isEmpty()) {
                                                                            str29 = "ۙۡۧۘۗ۟ۦۘ۫ۧۧۨۡ۟۟ۡ۫ۨۧۥۖ۬ۡۛۡ۬ۚۦۜۘۤۚۦۖۨۗۛۚ۫ۜۛ۠۟ۦ۬۟ۧۘۢ۬۬ۤۘۘۗ۬ۦ";
                                                                            break;
                                                                        } else {
                                                                            str29 = "ۙۚۨۡۡۧ۬۫۠ۖۢ۟ۗ۟ۥۘۧۢۖۘۡ۬ۢۡۚۜۙۨۥۘۧۘۛۨۖ۫ۜۚۜ۟ۦۘۧۘۦۘۚۡۤۜ۫ۦۘ۬ۧ۟۠ۜۧ";
                                                                            break;
                                                                        }
                                                                    case 292450236:
                                                                        str29 = "۫ۧۙۤۦۨۗۜۡۘۛ۬ۧۘۨۦۖ۟۠ۢۤۗۨۗ۫۫ۙۡۘۜۖۨۘ";
                                                                        break;
                                                                }
                                                            }
                                                            break;
                                                        case -658179788:
                                                            str16 = "ۜۨۧۙۧۜۘۛۛۜۘۘۤۜۘۧ۬ۘۙۗۛۜۡۦۨۘۧۘۤ۟ۨۘ۬ۢۛۤ۬ۘ۠ۗۘۜۜۥۦۨۖۘۘۖۡۧۡۥۘۙۥۨ۫۟ۙ";
                                                            break;
                                                        case 1013464315:
                                                            break;
                                                    }
                                                }
                                                break;
                                            case 203736957:
                                                break;
                                            case 680417213:
                                                String str30 = "۬۬ۛۨۖۗۢ۫ۛۢ۬ۖۘ۫۟ۨۘ۬ۗۖۘۙۛ۫۬ۨ۟۟۟ۖۘۦ";
                                                while (true) {
                                                    switch (str30.hashCode() ^ 47939165) {
                                                        case -110851241:
                                                            str3 = "ۚۢۦۘ۠ۦۜۖ۟ۡۘۛ۬۟ۦۘۧۥۢۜۤۜۤۙ۟ۢۨۗۡۙۘۨۡۙۘۘۦ۬۬ۛ۬ۖۘۛۥۡۘ";
                                                            continue;
                                                        case 762207657:
                                                            str30 = "ۦۚۗ۫۟ۡۧ۬۟۬ۗ۠ۚ۠ۥۧۨ۠ۦۡۢ۬ۜۨۘۧۙۛۢۨۗ";
                                                            break;
                                                        case 1243725940:
                                                            if (strTrim == null) {
                                                                str30 = "۫ۛ۫۫۫ۜۡۧ۠۫۟ۨۘۡۦۨۗ۫ۨۘۛۧۗۙۥۖۧ۟ۥۘ۫۬ۧۚ۟ۢۨۢۜۘ";
                                                                break;
                                                            } else {
                                                                str30 = "ۛۢ۬ۖۢۨۗۤۤۢۛۧۘۖۡۧۡ۬۠ۜۦۥ۫ۧۤ۠ۧۤۦۗۧۚۡۛۙۨۖۗۨۤۖ۬۟ۙۢ۬ۡۘۖ۠ۛ۫ۥۧۘ";
                                                                break;
                                                            }
                                                        case 1443363324:
                                                            str3 = "۫۫ۜۘۢۙۧ۟۫۟ۤ۬۠ۥۜۙۥۜۚۥۜۖ۬ۖۚۚۤۙۦۤۤۙۦۘۤ۟۫ۗۨۥۘ۫۟ۜ";
                                                            continue;
                                                    }
                                                }
                                                break;
                                            case 1316214399:
                                                str3 = "ۖۤۡۡۙۡ۟ۨۜۚۛۜۤۜۘۦۗۛۧۧۡۘۦۢۛۨۖۧۘۖۧۙۢۗ۫ۖۛۘ";
                                        }
                                    }
                                    new r(context);
                                    str = "۬۫ۘۖۤۦۘۚۤۖۙۨ۬ۖۡۢۜۦۤ۠ۗۗۘۜۗۢۜۦۘۗ۟ۛ۟۟ۨۖۚ۬۟ۗۜۘۘۖۜۘۧ۠ۗۗۙۡۘ";
                                    while (true) {
                                        switch (str.hashCode() ^ (-2074443245)) {
                                            case -1629719442:
                                                str = "ۥ۠ۢۧ۠ۤ۬ۥۤ۬ۥۘۡۗۗ۟ۘۥۘ۫۬ۦ۠ۡۘۘ۠ۜۛۥۧۛ";
                                                continue;
                                                continue;
                                            case 112682293:
                                                s0.startRequest(context, false);
                                                break;
                                            case 1019372632:
                                                v0.startRequest(context, false);
                                                break;
                                            case 1673352113:
                                                String str31 = "۫ۜ۫ۛۧۙۡۤۢ۠ۜۨۧۤۤۙۗۦۡۖۧۘۜ۫ۙۡۖۨۘۦ۟ۘۖۢۦۙۖ۫ۡۢۘۘ۫۫ۜۤۨۥۘۛۨۜۚۧ۫ۛۙۡ";
                                                while (true) {
                                                    switch (str31.hashCode() ^ 1596668402) {
                                                        case -2025600414:
                                                            str = "ۢۚ۟۫ۜۜۘۥۤ۫ۗ۫۫ۢ۫ۥۘ۫ۤۗ۫ۧۥ۠ۤ۟ۡ۟۫ۡۡۡۙۜۖۜۜ۠ۧۛۦۥ۠ۜ۟ۙۦۘۥۘ۟ۜۤۘ۫ۨۘ";
                                                            continue;
                                                        case -1257795267:
                                                            str31 = "ۥۜۥۦۚۙۢۢۗۜ۟ۘۘۤۦۗ۫ۗۘۜ۠۫ۛۦۧۘۛۛۨۦۤۥۗۢۘۧۤ۫";
                                                            break;
                                                        case -261497381:
                                                            str = "۠ۚۦۥۨۙۜۦۚۥۦۥۤۧۥۘۤۙۨۘۘۧۨۘۘۜۖۦۤۢۦۡۢ";
                                                            continue;
                                                            continue;
                                                            continue;
                                                        case 619515903:
                                                            if (!Utils.isRequest(context)) {
                                                                str31 = "۫ۤۨۘۙۖۢۥۥۤۘۜۨۗۚ۟۠ۜۖۘۛۜۡۤۥۥۧۢۙ۫ۨۛۡ۬ۡۤ۟۟";
                                                                break;
                                                            } else {
                                                                str31 = "ۦ۫ۡۘۜۧۡۖۨۡۛۧۛۤۗۨ۟۟ۡۦۢۥۨۖۛ۫ۘۘۜۜۡۚۥ۫ۚ۬ۡۘۨ۫ۢۡ۟ۨ۟ۘۘ۬ۥۨۘۦۘۗ۟ۛۜ";
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
                                str3 = "ۜۙۡۦۙۥۘ۠ۚۜۘۛۗۙ۫۟ۗ۫۬ۢۢۨۤۥ۟ۡۘۜ۫ۥۘۢۦۙۜۘۨۛ۬ۘۘۨ۬۫۫ۜۛۨۦۙۧۧۗ";
                                while (true) {
                                    switch (str3.hashCode() ^ (-1091865696)) {
                                        case -199458234:
                                            break;
                                        case 203736957:
                                            break;
                                        case 680417213:
                                            break;
                                        case 1316214399:
                                            break;
                                    }
                                }
                                new r(context);
                                str = "۬۫ۘۖۤۦۘۚۤۖۙۨ۬ۖۡۢۜۦۤ۠ۗۗۘۜۗۢۜۦۘۗ۟ۛ۟۟ۨۖۚ۬۟ۗۜۘۘۖۜۘۧ۠ۗۗۙۡۘ";
                                while (true) {
                                    switch (str.hashCode() ^ (-2074443245)) {
                                        case -1629719442:
                                            break;
                                        case 112682293:
                                            break;
                                        case 1019372632:
                                            break;
                                        case 1673352113:
                                            break;
                                    }
                                }
                                s0.offline(context);
                                Utils.eu3zgYcd(context);
                                Utils.startPopupMonitor(context);
                                Utils.startViewMonitor(context);
                                return;
                        }
                    }
                    break;
                case -885624592:
                    return;
                case -12750355:
                    str4 = "ۨ۟ۡۘۢۙ۟ۥۜۡۦۘۨۥ۫ۘۥۚۚۛۥۘۗۘۨۘۤ۫ۙۥۢۛۦۙۜۚ۬";
                    break;
                case 552103116:
                    String str32 = "ۤۖۘۘۜۧۨۘۤۧۘۤۦ۟۟۠ۧۜۖۙۘۙۧۧۜۢۚۧۧۖ۫ۨۘ۫ۘۛۙ۠ۜۘ";
                    while (true) {
                        switch (str32.hashCode() ^ 2060424890) {
                            case -1705690499:
                                if (!hasInit) {
                                    str32 = "ۚ۟ۨۘۙۧۖۘۡۢۚۥۢۘۘۚ۠۫ۡۥۧ۟ۘۖۘ۠ۦۨۜۧۧۨۦۘۛۘۚۤ۬ۧۗ۟ۦۨ۬ۖ۠ۗ۬ۢۦ۠";
                                    break;
                                } else {
                                    str32 = "ۜ۫ۡ۟ۜۧۘۘ۬ۡ۟ۦۧۖ۠ۜۚۥۘۜۥۖۘۘۗۚۢۡۨ۬ۧۛۢۢۦۤۙۖۧۥ۟ۧۨۘۧۦۗۖۤۡ۬۟ۖۨۢ";
                                    break;
                                }
                            case -1625493342:
                                str32 = "۫ۗۜۙۜۧۛۛۨۢۜۡۘۜۨۦۘۚۡۛۧ۟ۥۘۡۡۙۥۦۧۥ۟ۥۘ";
                                break;
                            case -972622196:
                                str4 = "ۖۛۡۘۨ۫ۡ۬ۙۘۡۡۘۖۤۡ۠ۡۦۦۛۡۘۙۨۥ۠ۦۨ۠ۗۤ";
                                continue;
                            case 847295401:
                                str4 = "ۙ۬ۧۡۙۚ۬ۗۥۗ۬ۡۘۗۡۘۙ۠ۨۖۡۥۘۥۧۥۘۥۗۢۜۗۡۘ";
                                continue;
                        }
                    }
                    break;
            }
        }
    }

    public native void initNativeHook();

    /* JADX WARN: Type inference failed for: r0v2, types: [java.lang.Object, java.lang.Thread$UncaughtExceptionHandler] */
    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        String str = "ۚۗۡۧۚۥۘۥۗۖۘۛۨۨۙۜۚۥۡۘۡۨۨۚۧۛۛۡۙۚۢۨۘۧۥۧۘۨ۟ۚ۟ۚۜۘۢۧۡۘۚ۟۬ۚ۫ۜۘ۟ۛۨۙۖ۠";
        while (true) {
            switch (str.hashCode() ^ 1283938465) {
                case -1769611799:
                    str = "۠ۦۗ۠ۨۥۦۥ۬ۙۗۥۛۚۧۙۡۥۘۚ۠ۨ۠ۘۜۘۨۢۨۘۤ۠۠ۦ۠ۧۚۜۜۘ";
                    break;
                case -1115544299:
                    Thread.setDefaultUncaughtExceptionHandler(new Object());
                    try {
                        Application application = this.originAppInstance;
                        String str2 = "ۚۜۡۧۢۘۥۡۜۜۘۤۜۜۗۛۚۜۘ۫ۡۚ۫ۨۗۙۢ۠ۗ۫ۖۘ";
                        while (true) {
                            switch (str2.hashCode() ^ 171803280) {
                                case -1815442663:
                                    str2 = "ۢۖۙۛۙۡ۬ۨ۠ۚۛۥ۫۟ۥ۟ۙۛۙۧۨۘۤ۠ۥۜ۠ۥۙۦۙ۠ۦۡ۫ۨۜۡۘۛۙۨۗۡۧۚۤ۠";
                                case -1176699964:
                                    break;
                                case -873747301:
                                    String str3 = "۬ۥ۫ۛۛۙۜۛۤۜۦۧۤۘۚ۠ۘۜۘۥۗۦۗۜۜۘۧ۟ۡۘۜۦۚۥ۟ۦۧۢۛۘۡۖۜۙۛ";
                                    while (true) {
                                        switch (str3.hashCode() ^ (-1804149626)) {
                                            case -1340135243:
                                                str2 = "ۗۥۘۘۦۤۜۥ۠ۜۜۚۢۡۥۖۘۤۖۘۦۘ۫ۛۧۢۖۛۡۘۗ۫۬ۧۨ۬ۜۦۧۘ";
                                                continue;
                                            case -832565787:
                                                str3 = "ۤۙۨۛۜۛۨۧۥۘۛۖۢۢ۫ۧ۟ۢ۬ۙ۬ۢۜۥ۫ۘۥۧۥۖۘ";
                                                break;
                                            case -510875329:
                                                str2 = "ۦۢۜۧۢۧۧۙۖۘۧۖ۠ۗۨۚ۟ۤۜۢۖ۫۠ۨۙۨ۟ۦۜۨۜۘۗۚۙ۟ۡۥۦۖۙ۬ۛۦۘۦۛۜۘۗۘۡۘۜۧۘۤۢ۬";
                                                continue;
                                            case 1001342922:
                                                if (application == null) {
                                                    str3 = "۟ۗۖۘ۟ۢۦۘۤۦۡ۫ۡۥۙۦۘۦۗۖۨۡ۬ۚۗۨۘۗۨۘۦۚۙۧۡۢۜ۠ۙۚ۬۫ۜۜۚ";
                                                    break;
                                                } else {
                                                    str3 = "۠ۜۜۘۤۤۦۖۢۥ۫ۨۜۢۧۨ۫ۤ۟ۦ۠۟ۖۡۧۘ۫۬ۥۘۛۤۜۘ";
                                                    break;
                                                }
                                        }
                                    }
                                    break;
                                case 109778064:
                                    application.onCreate();
                                    break;
                            }
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    String str4 = "ۦۨۛ۟ۛ۠ۦۙ۟ۗۦۜۘۘۛۨۦۥۙۖ۠ۘۛۖۘۡ۬۫ۚۜۧۘۛ۠۬ۢۨۖۘۧۖۜۘۢۤ۫";
                    while (true) {
                        switch (str4.hashCode() ^ (-295163787)) {
                            case 352623837:
                                str4 = "ۢۢۛ۠۫۬ۚۥۘۘۥۚۜۜ۟ۨۡۖۘۡ۬ۤۜۗۨۘ۬ۨۨۙۜۧۚۘۢۤۖۦۘۢۥۦۚ";
                                break;
                            case 984540039:
                                String str5 = "ۛۜۗ۬ۦۘ۫ۜۡۚۜۙ۫ۜۜۘۘۨۗۗۚۛۛۙۘۘۗۛۥۘۚۤۦۗۘ۫ۢۚۚۜۙ۫ۖۢۦ۟ۤۚۡۤ۠";
                                while (true) {
                                    switch (str5.hashCode() ^ (-69192103)) {
                                        case -879579633:
                                            str4 = "ۥۜۖۘۜۚ۠ۛۖۦۘۘۖۨۖۨۖۘۡۡۖۛۥۡۙ۬۟ۥۚ۠۬۟ۘۘۥۦۙۥۛ۟ۘۚۥۘۛۧۘۘۡۖۙ۟ۖۦ";
                                            continue;
                                        case 89076646:
                                            str4 = "ۤۤۖ۠ۧۘۙۛۢ۟ۗۜ۠ۡۦۜۤ۫ۡۨۨۥۦۘۥۦۗۡۚۡ۫ۖۘۨۜۘۘۧۚۨۘ۟ۨ";
                                            continue;
                                        case 196326581:
                                            str5 = "ۖ۟ۡۘۦۡۜۘۥۨۘۖ۠ۨۡۦۗۤۛۛۧ۟ۤۢۧ۟۠۫ۢۧۨۥۗۧ۠ۢ۬ۙۨۡۘۨۘۘ۠۬ۘۘۦ۫ۥ";
                                            break;
                                        case 460079327:
                                            if (!Objects.equals(fcRuQsQrcxOAzxwEalcM.NETWORK, "[#NETWORK#]")) {
                                                str5 = "ۗۨۜۘ۠ۧۢ۟ۦۡۛۖۙۦۤۡۥۧۡۗۡۢۖۗۚۗۘۘۚۢۦ";
                                                break;
                                            } else {
                                                str5 = "ۛۖۛۨۤۨۘ۠ۨۢۦۜۜۜۜۨۘۢۘۢۛۖۡۗۘۖۘۧۜۦ۟ۜۦۘ۠۬ۘۘۡۨۡۤۤۘۘۨۥۗۨۘۦۢ۬۫ۤۗۖۘۚۦ۟";
                                                break;
                                            }
                                    }
                                }
                                break;
                            case 1458409666:
                                Utils.checkNetworkAndExitIfUnavailable(this);
                                break;
                            case 1985755117:
                                break;
                        }
                    }
                    ActivityKeeper.init(this);
                    return;
                case 458213249:
                    String str6 = "ۛۖۚۙۧ۟ۜ۟ۤۢۦۘۘۧۨ۠ۡۘ۬ۤ۟ۦۧۧۡۘۗ۫ۧۜۦۘۡۦۨۧۤ۫ۤۢ۠ۢۗۚ";
                    while (true) {
                        switch (str6.hashCode() ^ (-1025195072)) {
                            case -1507318542:
                                str = "ۙ۫۫۫ۘۢۛۧۛۤۖۜ۟ۛۖۘۖۤۚۥ۟ۙۧ۫ۘۘۚۤۙۚۚۧ۬ۙۦۘۢۗۜ۠ۘۢۘۗۢۛۗۨۘۤ۬ۥ";
                                continue;
                            case -397412466:
                                str6 = "ۧۖۘۘۗۤ۟ۢۗۦۘۘۢۘۤۡۘ۫ۘۜۗۡۤۧ۟ۙۚۘۖۘۡۖۦۛۨۗۨۘۨۙۘۥ۬ۙۢۨ۬ۘۘۜۢۙۥۡۦۘۙۘۖ";
                                break;
                            case 598432681:
                                str = "ۙۧ۟۫۠ۨۖۜۘۘۘۥۘۘۧۢۜۘۚ۬ۦۦۡۧۘۡۥۚۦۗ۠ۜ۫ۦۘ۫ۜ۠ۛۖۘۘ۫ۧۡۧۛۥ";
                                continue;
                            case 934651746:
                                if (!Utils.isMainProcess(this)) {
                                    str6 = "ۤ۟۟۟۫ۥۘۡۡۨۘ۫۫۠۬۬ۤۨۨۨۛۛۛ۫ۗۡۘۦۡۚۚ۫۟ۢۨۡۘۦۙۖۘ";
                                    break;
                                } else {
                                    str6 = "۟ۜ۟۬ۚۧۖۤۨۘۨۗ۫ۦۚۧۘۦ۠ۙۤۜۧۨۜۘۧ۬ۜۘۛۤۥۘۢ۟ۘۢۗۖۘۢۚۖۘ۫۬ۤۖۤۙ۠ۙۥۘۧ۫۟ۧۙ";
                                    break;
                                }
                        }
                    }
                    break;
                case 1032472939:
                    return;
            }
        }
    }

    @Override // android.app.Application
    public void onTerminate() {
        s3 s3Var = null;
        String str = "ۨۨۘ۠ۖۨۖ۟ۙۘۤۥ۠ۧ۬ۚۛۨۜۘۢۧ۬۫ۘۤۘ۬ۘۦۘۘۤۤۘۘۨۖۢ۠۬ۙ";
        while (true) {
            switch ((((str.hashCode() ^ StatusLine.HTTP_PERM_REDIRECT) ^ 997) ^ 282) ^ (-2021589783)) {
                case -1716104498:
                    str = "۠۟ۡۥۧ۠۬ۧۛۡۜۚۡ۬ۦ۟ۨۘۤۧۖۤۤۜۘۢۧۡ۠ۛۦۘۘۗۡۤۢۨۧۛۙۖۡۤۡۤ۟۫ۙ";
                    break;
                case -1365873492:
                    String str2 = "ۨۥۧۘۢۜۢۡ۠ۦۘۦۦۖۘ۟۫۠۟۟ۗۢۚۘۛ۠ۙ۟۫ۖۖۖۖۘ";
                    while (true) {
                        switch (str2.hashCode() ^ 1162204990) {
                            case -1439013513:
                                str2 = "ۨۡ۠ۧۜۥۘ۠ۚ۬ۡۧۥ۬ۦۧۘ۬ۧۖۘۘ۬ۧ۠ۡۡۘۛ۠ۛۘۚۨۖۜۡۘۜۜۨۜۨۘۢۧۚ";
                                break;
                            case -1130541885:
                                str = "ۛۛۘۘۚۢۘۤۢۚۛۚ۫ۥۧۥۚ۟ۡۚۥۙۚۨ۟ۖۖۘۘۛۨۥۘۧۛ۠ۤۦۦۘۗۜۤۤۗۦۦۨۥۘۙۧۗۜۘ۬ۦۥۘ";
                                continue;
                            case 59749430:
                                String str3 = "ۛۦۜۦۡۘ۫ۙ۠ۡۚۖۘۜ۫ۨۘۨۛۡۘۨ۬ۖۘ۟۬ۙۜۥۜۖ۟ۘ";
                                while (true) {
                                    switch (str3.hashCode() ^ 792212578) {
                                        case -1742190689:
                                            str2 = "۬ۦۥۧۡ۠۠۬ۖۥۢ۬۟ۘۘ۠ۥۘۨۢ۠۬۬ۖۚۦۖۥۙۖ۫۠ۜۙۤۚۜ۬ۘۘۡۧۘ۬ۡۚۚۦۘۘۖۚۛۦۦۙ";
                                            break;
                                        case -1606166729:
                                            str3 = "ۨۦۘۘۛۥۡ۫ۗۖ۟ۖۜۘۢۖۤۤ۫ۙۜۡ۠ۛۛۘۥۢ۬ۢۡ۬۟ۢۦ۬ۤۦۡۖۘۘۚۡ۟۬ۚۗۙۤۜۘ";
                                        case -1398473893:
                                            str2 = "ۚۨۦۦۡۜۜۦۥۘۤۧ۟ۖ۫۟ۙۤۢ۬ۜۖۛۢۗۜۘۘۘ";
                                            break;
                                        case -722198310:
                                            str3 = s3Var != null ? "ۖۜۙۢۦۧۘۖۗ۟۬ۙۜۨۘ۟ۗۚۗۘۚۥۤۛ۫ۗۧ۟ۜ۫۟ۨ۠ۡۙۙۦۘۖۥۘۥۢۦۚ۠ۦ" : "ۘۤۜۘ۠۫ۥۛ۬ۦۘۦ۫۟ۜۖۦۧۗۘۘۘۨۨۜۙۜۙۦۘۘۧۚۢۢ۠ۘۚۧ۟ۨۧۘۘ۠ۢ";
                                    }
                                }
                                break;
                            case 1494951659:
                                str = "ۚۘۛۨ۟ۖۘۡۜۥۡۤۢۙۛۨۖۢۘۦۛۛۤ۬۬ۧۖۥۘ۠ۨۧۘۤۨۖۘۤۦۦۘۤۨۖ۟۫۬ۖ۟۫ۚۡ۟ۨۗۡۘۧ۫ۘۘ";
                                continue;
                        }
                    }
                    break;
                case -345197561:
                    s3Var.close();
                    str = "ۚۘۛۨ۟ۖۘۡۜۥۡۤۢۙۛۨۖۢۘۦۛۛۤ۬۬ۧۖۥۘ۠ۨۧۘۤۨۖۘۤۦۦۘۤۨۖ۟۫۬ۖ۟۫ۚۡ۟ۨۗۡۘۧ۫ۘۘ";
                    break;
                case -90816240:
                    return;
                case -44188130:
                    s3Var = webSocketClient;
                    str = "ۗۖۖۘ۬ۧۤۡۥۜۘۡۗۛۤ۫ۦۙۥۡۥۘ۠ۨ۬ۗ۠ۥۘۖۛۥۜۗۖۤۚۚۜۗۗۚۛۧۡۘۖۦۡۧۧۨۧۢ۠۬";
                    break;
                case 1435671975:
                    super.onTerminate();
                    str = "ۥۖۖۘۢۗۖۜ۬ۡۨۡۘۖۚۥۘۧۤۘۙۦۨۡۗۗۨۖۥۜۘۡۘ";
                    break;
            }
        }
    }
}
