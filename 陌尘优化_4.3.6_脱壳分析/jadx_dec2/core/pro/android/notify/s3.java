package core.pro.android.notify;

import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import android.util.Log;
import android.widget.Toast;
import com.shadow.okhttp3.OkHttpClient;
import com.shadow.okhttp3.Request;
import com.shadow.okhttp3.WebSocket;
import gTBLD.dev.XSSTG.free.Utils;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

/* loaded from: /workspace/unpacked/classes2.dex */
public class s3 {
    public static final String q;
    public final Context a;
    public final String b;
    public final String c;
    public final String d;
    public final OkHttpClient e;
    public WebSocket f;
    public ScheduledFuture j;
    public ScheduledFuture k;
    public final Handler g = new Handler(Looper.getMainLooper());
    public final ExecutorService h = Executors.newSingleThreadExecutor();
    public final ScheduledExecutorService i = Executors.newSingleThreadScheduledExecutor();
    public volatile boolean l = false;
    public volatile boolean m = false;
    public volatile boolean n = false;
    public int o = 0;
    public final Random p = new Random();

    static {
        String str = "ۡ۬ۦۘۢ۟ۜۧۜ۠۬ۗۖ۫۬ۜۜۢ۟ۧۢۜۨۘۥ۟ۦۚۛۥۦۘۗۨۖۘۘۢۢ۬ۧۗۢۥۘۡۘۢۛ۠";
        while (true) {
            switch ((((str.hashCode() ^ 841) ^ 730) ^ 53) ^ (-1350094266)) {
                case -860276292:
                    return;
                case 2054817741:
                    q = "WebSocketClient";
                    str = "ۢۗۖۢۘۡۗۨ۬ۡ۠ۘۘ۫۫ۙۜ۠ۗۧۙ۬۫ۢۛۛۤۦۘۡۘۦۘۛۗۦۘۡۖۨۘۚۨۨۘۦۢۡ";
                    break;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:6:0x0068. Please report as an issue. */
    public s3(Context context, String str, String str2) {
        String string;
        byte[] bArrDigest;
        StringBuilder sb;
        int length;
        int i = 0;
        this.a = context.getApplicationContext();
        this.b = str;
        this.c = str2;
        try {
            bArrDigest = MessageDigest.getInstance("MD5").digest((str + str2).getBytes());
            sb = new StringBuilder();
            length = bArrDigest.length;
        } catch (Exception e) {
            Log.e(q, "生成key失败", e);
            string = "";
        }
        while (true) {
            String str3 = "ۤۦۗۙۚۙۜ۬ۖۘ۠ۤۖۘۚۖ۬۠ۚۥۦۧۘۘۥۥۦۥۖۘۚۘ۟ۗۖ۬ۜۧۚۜۚۜۘۤۙۨۘۧۢۤۧۚۚۖۘۥۤۢ";
            while (true) {
                switch (str3.hashCode() ^ 6061011) {
                    case -630367446:
                        String str4 = "ۡۢۢۗ۟ۖۘ۠ۧۤۨۘۧۛ۠ۡۘۧۙ۫ۥۚ۟ۛۖۨۘ۫ۜۥۘۗۧ۠۫ۙۘۘۨۖ۟ۨۧۛۖ۟ۡ";
                        while (true) {
                            switch (str4.hashCode() ^ (-707008276)) {
                                case -873736714:
                                    str3 = "ۗ۠ۜۘ۟۠ۛۖ۟ۖۜ۠ۖۚۤۚۙۥۗۗ۫ۙۡۦۧۘۗۥۧۘ۠ۜۚ";
                                    break;
                                case -497166351:
                                    str3 = "ۗۜۥۚۙۧ۟ۡۘۙۥ۠۟ۘ۫ۘۢۡۥۚۙۙۗ۠ۥۛۤۡ۬ۨ";
                                    break;
                                case 771744668:
                                    str4 = i < length ? "ۧۛۜۘۘۡۡۘۨۜۢۧۖۛ۫ۖۧ۫ۧۛۙۖۘۨۙ۬۠ۖۗ۬ۛ۠ۚۦۧۗۗۧۖۜ۫ۗۧۨۥۚۨۖۨۘ" : "ۜۛۖۘۨ۠ۥۖۤ۬ۨ۫ۤۙۨۘۚ۟ۜۘ۬ۡۤ۬۟ۨۘ۠۟۫۠ۨۥۘۢ۟ۨۦۦۚۢۤۦ۠ۜۘۤۛ۫۟ۥۘۢۥ۬ۛۤ";
                                case 1000527039:
                                    str4 = "ۗ۟ۧۙۛۗۨۧ۠ۘۚۦۘۤ۟ۡ۠ۨۧۘۨۘ۫ۢۜ۠ۦ۬ۖۤ۬ۥۖۙۖۘ۫۬ۜۤۡۜۘۛۛۨۘۤۦ۠ۥۦۤۥۨۦۘۧۢۖ";
                            }
                        }
                        break;
                    case -316733845:
                        break;
                    case -135097732:
                        sb.append(String.format("%02x", Byte.valueOf(bArrDigest[i])));
                        i++;
                    case 2051152810:
                        str3 = "ۧۧۘۘۦۦۦۗۙۖۛۛۖۘ۫۫ۨۘۘ۠ۡۚۧ۟ۜۡۥ۫ۜۘۤۦۖۘۡۗ۠۫ۙۦۧۗۜۘۛ۫ۦۘۘۚۡۘۚۜ۫ۘ۟۬ۧۡ";
                }
                string = sb.toString();
            }
            this.d = string;
            this.e = new OkHttpClient.Builder().retryOnConnectionFailure(true).pingInterval(30L, TimeUnit.SECONDS).build();
            return;
        }
    }

    public static void a(s3 s3Var) {
        s3Var.getClass();
        try {
            Activity activityECt8jHZ4 = Utils.ECt8jHZ4();
            String str = "ۡۤۨۘۚۗۚۢۨۘۘۤ۟ۦۘۛۖ۟۫ۧۘۘ۫ۗۢۢ۬ۛۜۚۡۡۥۖ۫ۡۨۘ۟ۦۡ";
            while (true) {
                switch (str.hashCode() ^ 1709825356) {
                    case -920032853:
                        String str2 = "ۤۧۖۛۙۢ۠۠ۚۡۤۜۘ۬۠ۖۜۢۜۥۖۗۦۘۗۨۖۘۧ۬ۧ۬ۗۜۘۤ۠ۨ";
                        while (true) {
                            switch (str2.hashCode() ^ 1465607900) {
                                case -887590226:
                                    if (activityECt8jHZ4 == null) {
                                        str2 = "ۘۗۗۦۥۖ۠ۗ۠ۦۘۥ۫ۦۘۚ۫ۦۗۚۚۦۢۛۗ۬ۥۘۗۧۘۘۦۦ۫ۧۢۢ۟ۨۗۦۚۥۢۦۜۘۢۧۘ";
                                        break;
                                    } else {
                                        str2 = "۠ۚۢۛۚۤۜ۟ۨ۟ۚۖۘۨۥۧ۠ۢۖۤ۟ۚۙۡۖۛۚۨۘ۬ۜۘۘۡۢۨۘۧۜۛ۟ۘۧۘۜۖۛۗۦۦۤ۟ۧۢۡ۬ۦۨۘ";
                                        break;
                                    }
                                case -308909937:
                                    str = "ۖۛۛۦ۬ۘۢۦۚۤۚۗۙۧۘۘۘۨۧۘ۟ۙۖۦ۫۫ۖۗۛۙ۟ۖ۫ۦۛۥۨۨۘ۠ۛ۠ۦ۠ۛۗۙۙ۠ۘۖۘۨۖۘ۫ۛۢ";
                                    continue;
                                case 297844769:
                                    str2 = "ۨۤ۫ۖۢۨۘۤۧۨۘۜۨ۬ۦۗۙۦۡۧۢ۫ۖۘ۫۟ۥۘ۟ۤۛۤۚۢۙۡ۬ۗۢۘۡۨۢۢۡۗۨ۬ۚۨۙۜۚۛۥ۠ۙۧ";
                                    break;
                                case 1217993871:
                                    str = "ۘۦۜ۟ۚۘۘۤ۠۫ۥ۠ۗ۫ۖۘۘۢۢۘۘۢۦۨۢ۫ۦۘۗ۫ۢۡۨۨۘ";
                                    continue;
                            }
                        }
                        break;
                    case -828706933:
                        break;
                    case -741653300:
                        str = "ۗۡۢۧۧۦۧۦۖۗۡۢۜۢۖۦۗ۫ۛ۟ۖۘۢ۫۠۟ۗۧۙ۫۟۠ۡ۫۠ۚۥۘۡۥۙۨۛۙۥۗ۠ۨۛۧ";
                        break;
                    case 1529405044:
                        String str3 = "ۜ۬ۦۦۜۨۨۙۦۘۜۛ۫ۖ۠ۜۘ۫۟ۘۥۢۘۙۙۗۨۖۖ۠ۢۗۜۧۖۢۥۤ۫ۜۢۛۥۦۘۖ۟ۥۨۢ۫ۖۗۢ۠ۚ";
                        while (true) {
                            switch (str3.hashCode() ^ (-1484011149)) {
                                case -2111232936:
                                    break;
                                case -1810350273:
                                    str3 = "ۗ۠۟ۜ۠ۛۥ۠ۤۚ۠ۖۘۘ۬ۦۖۘۥۘۧۤۗۜۖ۠ۜۘۡۚۘۚۦۖۘۖۤۥۢۢۘۘۢۖۧ";
                                    break;
                                case -1103438406:
                                    String str4 = "۟ۗۦۘ۫ۤ۟ۢۙۨۛۨۥۘۦۛۥۘۦۚۙۜۜ۫ۥۛ۟ۚۛۗۜۖ۟ۚۧۜۘ۫ۦ۟ۗۛۘۘۡۗ۬ۗۗۢۛۗ۠";
                                    while (true) {
                                        switch (str4.hashCode() ^ (-921200365)) {
                                            case -1684667779:
                                                str3 = "۠۠ۗۢ۟ۚۘۨۘۡۦۥۖۛۨۘ۟ۛۢۜۧۤۚۥۦۘۢ۠ۦۜۡۖ";
                                                continue;
                                            case -1678892610:
                                                str3 = "۟ۘۙ۠ۧۥۛۘۖۜۥۜۘۜۨۧۘۢۙۗۤ۬ۢۛۚۘۦۨۦۘۨۖۥۘ۟ۨۡۘ۟ۤۜۜۥۙۖ۟ۦۘ";
                                                continue;
                                            case -1627875811:
                                                if (!activityECt8jHZ4.isFinishing()) {
                                                    str4 = "۠۫ۧۖ۟ۦۤۧۖۜۧۦۗۛۖۘۜۛۥۧ۫ۨۡۗۧۡۗۡۨۚۜۜۡۜۦۙۢۦۡۡ۫ۥۘۤۥۨۧۚۤ۠۠۬ۤ";
                                                    break;
                                                } else {
                                                    str4 = "۟ۘۖۦۚ۬ۥ۫ۙۦۚۛۚۜۘۢۖۘۘ۟۠۫۬ۡۖۘۡۗۜۘۘۚۚۤۥ۫ۨۙۨ";
                                                    break;
                                                }
                                            case 199456838:
                                                str4 = "ۛ۟ۛۜۨۨۘۨۧۡۘۗۜۙ۠ۦۦۧ۠۟ۢۤۨ۠ۙۖۘۦۨۡ۠ۨۦۢۗۜۘۦۙۗۘۨۦۘۥ۟۟ۧ۠ۨۗۢۡۚۢۜۘۜ۬ۨ";
                                                break;
                                        }
                                    }
                                    break;
                                case 1523507481:
                                    activityECt8jHZ4.finish();
                                    break;
                            }
                        }
                        break;
                }
            }
            s3Var.g.postDelayed(new a(17), 500L);
        } catch (Exception e) {
            Process.killProcess(Process.myPid());
            System.exit(0);
        }
    }

    public static void b(s3 s3Var) {
        synchronized (s3Var) {
            boolean z = s3Var.l;
            String str = "۟ۙۦ۫ۡۘ۟ۙ۫ۗۤۖۘۚ۫ۡ۬۟۫ۘ۟۬ۦ۠ۜۨ۫ۙۘۚۤۧۢ۬ۡ۟۬۫ۛۧۛ۫ۦۨۜ۠ۡ۟ۧ۟ۤۘ۟";
            while (true) {
                switch (str.hashCode() ^ (-1282007956)) {
                    case -736466867:
                        long jMin = Math.min((long) (Math.pow(2.0d, s3Var.o) * 2000.0d), 10000L);
                        long jNextInt = s3Var.p.nextInt(1000);
                        s3Var.o++;
                        s3Var.d();
                        s3Var.k = s3Var.i.schedule(new o3(s3Var, 0), jMin + jNextInt, TimeUnit.MILLISECONDS);
                        return;
                    case 17684837:
                        str = "ۚ۫ۘۘۚۡۦۡۨۚۜۥۚۚۥۧۜ۟ۗۘۘۨۧۜۧۘۤۙ۬ۨۘۦۤۛۡۥ۫۠ۙۦۙۡ۬ۢۥ۬ۗۘ۬";
                        continue;
                    case 414934092:
                        return;
                    case 1633527308:
                        String str2 = "ۗۦۥۥۛۚۜۗ۫ۘۢۡۘۜ۟ۖۗۤۢ۫ۘۚۗ۫۠ۛۨۡۘ۬ۨ۟ۘۗۨۘۦ۫ۥۘۙۛۖۘۜۢۛۚ۬ۖۘۘۤ۟";
                        while (true) {
                            switch (str2.hashCode() ^ 1545408759) {
                                case -1295066489:
                                    if (!z) {
                                        str2 = "۟ۢۨۘ۟۫ۖۨۜۖۘۗ۫ۨۘۧۛۡۦۦۥۘۙۚۚۙ۠ۦۥ۠ۥۖۙ";
                                        break;
                                    } else {
                                        str2 = "۬ۥۦۘۤۦۡ۬ۗۚ۟ۨۚۤۨۜۚ۟۟ۦۦ۫۠ۙۨۛۦۘ۬۬ۛ۠ۜۨۘ۫ۦۘۦۙۘۘۡۖۘ";
                                        break;
                                    }
                                case -174066584:
                                    str = "ۥۖۥۘۘۤ۬۬ۘۙۜۘۚۧ۟۬ۙۙ۫۟ۡۗ۬۟ۦۘۥۚ۫ۤۖۡۢۛۡۘ۠ۦ۠ۤۗۗ۟ۧ۟ۙۤ۠ۖۘ";
                                    continue;
                                    continue;
                                case 347800190:
                                    str2 = "۠ۡۨۘۘۜ۟ۦۜ۫ۖۗۘ۟۠ۥۘۧ۟ۡۛۜۡ۫ۢۦۗ۠ۥۘۢۜۤ۟ۧۘۘۨۛۘۘۗۤۥۘۘۛ۬ۜۦۡ۬ۘۥۘۡۡۘۘۜۗۘ";
                                    break;
                                case 1449094159:
                                    str = "۠ۥۦۘۙۦ۫ۡۜۜۨۦ۬ۖ۫ۥۤۘۛۖۢ۬ۤۢۨۖۤۧۤۢۨۘۚ۟۬۠۬ۢ";
                                    continue;
                            }
                        }
                        break;
                }
            }
        }
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public static void c(final s3 s3Var, String str, final String str2, HashMap map) {
        String strDecrypt;
        char c = 0;
        s3Var.getClass();
        String str3 = q;
        try {
            switch (str.hashCode()) {
                case 108417:
                    boolean zEquals = str.equals("msg");
                    String str4 = "۫ۢۡۡۜۨۘ۟ۤۜۜۧۘ۠۟۫ۖۙۜۘۤۘۥۡۛۘۘۥ۟ۨۥۘ۫۠۫ۚۛۙۘ";
                    while (true) {
                        switch (str4.hashCode() ^ 1895474072) {
                            case -1135340240:
                                break;
                            case -64148872:
                                String str5 = "ۗۜۜ۫ۡ۠ۛۛۘۘۧۙ۬۠۠ۡ۫۟ۛ۫ۖۢۥ۟ۖۘۤۚۡۘۖۗ۬ۛۥۘ۫ۛۧ";
                                while (true) {
                                    switch (str5.hashCode() ^ (-340806594)) {
                                        case -1749566559:
                                            str4 = "ۛۚۘۘۦۧۙۥۘۧۘۤۙۚۘۦۨۖۘۥ۫۬ۡۘۨ۟ۨۗۗۖۘۨۛۛ۬ۖۘۘ۬ۖۡ۫ۡۘۤۨۖ";
                                            continue;
                                        case -1718569999:
                                            str4 = "ۜۛ۫ۜۘۨۘۛۛ۠ۤۘ۫۫ۢ۠ۨۜۡۘۥۥۨۡۥۘۤۛۧ۫۫ۗ۠۟ۚۧۦۢۙۡۘۧ۟۠ۖۦۡۘۜۡ۬";
                                            continue;
                                        case -419450785:
                                            if (!zEquals) {
                                                str5 = "ۧۖۜۘۜۧۖۘۦ۠۫ۚ۠ۤۖۦۨۘۛ۟۠ۨۙۥۥۙۚۛۘۥۘۦۗۙ";
                                                break;
                                            } else {
                                                str5 = "۫ۧ۬۟ۜۙۙۦۨۗۧۢۚۤ۠۫ۙ۫ۢۖ۠ۘۜۜ۠ۥۦۤۚۡۘ";
                                                break;
                                            }
                                        case 688205670:
                                            str5 = "ۢۜۡۦۡۘۘ۫ۨۨۘۛۗۜۘۡۘۖۥۙۦۜۢۡ۠ۢۨۨۦۢۨۧۜۦ۫۬ۨۡۘۥۙۛۨۙ۬ۢۛۛۤۢۦۘۤۗۥۦۤۡۘ";
                                            break;
                                    }
                                }
                                break;
                            case 276492664:
                                break;
                            case 1109199687:
                                str4 = "ۛۚۛۜ۠ۘۛۜۧۘۘۖۖۘ۟۬ۥۘ۠ۦۗۧۜۧۘۜۖۙۖۨ۬ۛۥۢۨۨۡۦۛۗۢۢۚۜۘۨۢۨ۠ۘۡ";
                        }
                    }
                    break;
                case 3127582:
                    String str6 = "ۖ۠ۡ۠ۥۧۘۡۖۖۘ۫ۢۥۘ۬ۖۨۗۘۘۢۧۜۤۗۚۘۨۦۘۥۢۦۚۙ۟ۦۜۡۘ";
                    while (true) {
                        switch (str6.hashCode() ^ (-448756343)) {
                            case -1984862865:
                                c = 2;
                                break;
                            case -1324463249:
                                str6 = "ۗۛۤ۠ۛۙۗۤۦ۬ۧۦ۫۫۟ۤۛۢۗۨۘۘۗۙ۬ۘۤۤ۠ۥۘۗ۬ۨۧۤۜۗۨۖۘۥۤ۬ۚۢۧۖ۬ۥ";
                            case 773508852:
                                String str7 = "ۥۡۨۦ۫۬ۢۘۨۘۛۘۘۥۜۘۛۢۧۡۧۗۙۖۗۖۥۘ۬ۚ";
                                while (true) {
                                    switch (str7.hashCode() ^ 883020076) {
                                        case -1559618163:
                                            str6 = "ۘۥۖۘ۫ۢ۠ۖۢۚ۬ۥ۬ۧۜ۠ۡۤۡۗۤۘ۬ۧۘۧۜۘ۬۬ۖۘ۫۠ۤۛۨ۬ۘ۬ۨۘ۫ۥۡۙۥۡۘ۫ۨۘ";
                                            continue;
                                        case 716457183:
                                            str6 = "ۡۙۢ۫ۜۚ۫ۗۦۛۥۛۛۤۡۘۗۚۖۧۛۥۥ۫ۙۜۦۛ۫ۚ۠";
                                            continue;
                                        case 1120245753:
                                            if (!str.equals("exit")) {
                                                str7 = "ۧۗۥۘ۟ۨ۫۫ۡۨ۟ۡۨۨۖۘ۫ۤۚ۟۠ۦۘۤۦۦۘ۠۬ۢۖۤ";
                                                break;
                                            } else {
                                                str7 = "ۤۖۘۘۘۙۘۘۚۙۚۢۨۘ۠ۦۤۧۛۜۘۜ۠ۤۢۡۦۙ۠ۨۘۛۜۛ۠ۘۧۘۧ۫ۤۢۧ۟ۙۛ";
                                                break;
                                            }
                                        case 1455921297:
                                            str7 = "ۖۛۧۨۤۦۘۦ۠ۦۛۦۘۘ۠ۚۖۘۛۨۘۤۙۥۘۖۥۥۘۛۙۗۚ۬ۚ";
                                            break;
                                    }
                                }
                                break;
                            case 1706333206:
                                break;
                        }
                    }
                    break;
                case 109403696:
                    String str8 = "ۗۤۧۨۚۦۘۙۧ۫ۛۘۦۘ۠ۜۤۤ۬ۖۘ۫۠ۘۘ۬۟۬ۧۚۦۘۙۤۡۘۥۘۢ۬ۛۖ۫ۡۘۜۡۦۨۛۡۖۜۨ";
                    while (true) {
                        switch (str8.hashCode() ^ (-372109849)) {
                            case -694836203:
                                break;
                            case 1170469685:
                                String str9 = "ۜۙۦۤۚۛ۬ۤۨۘۗۗۚۨۚۤۨ۟ۨ۟ۢۘ۫ۖ۬ۗۦۦۧۥۘۡ۬ۙۘۡۥۘ۫ۜۦۘۘۗۚ";
                                while (true) {
                                    switch (str9.hashCode() ^ (-1755380285)) {
                                        case -1512025970:
                                            str8 = "ۙۖ۟۫۬۟ۘ۠ۢۜۙۜۨۤۧۡۨۙۛ۬ۡۘۢۡۘۗ۬ۡۘ۟ۖ۠ۡۥۥۤۢۦۦۤۢۗۜۢۤۦۛۗ۬۠";
                                            continue;
                                        case -1486011471:
                                            if (!str.equals("shell")) {
                                                str9 = "۠۟ۥ۟ۗۘۜ۫ۚۖۨۙۧۥۘ۫ۙۖۘۡ۬ۚۥۗۚۚۦۦۖۜۚۥۗۥۚۨۦۘ";
                                                break;
                                            } else {
                                                str9 = "ۜ۟۟۟ۙۖۙۚۙۤۨۢۢۡۜۜۘ۠ۡۥۘ۠ۦۨۖۦۛ۫ۡۜ۬۬ۘۦۡ۬ۖ۫ۧۥۡ";
                                                break;
                                            }
                                        case 1565095580:
                                            str9 = "۬ۡۥۘۥ۫ۜۥۡۜۘۨۙۘۘۛۢۖۘۢ۬۫ۚ۠ۖۚۘۨۡۖۘۧۛۨۘ۟۠۫۫ۥۥ";
                                            break;
                                        case 2111225935:
                                            str8 = "۫ۘۙ۟۟ۜۡۥۧۡۤۢۨۥۥۘۜۧۛۚۙۜۨۢۦۘ۬۬ۜۘ۫۬ۡۗ۟ۜۨۙۛۦۢۦۤۛۗۨۧۡۘ۬ۜۧۘ";
                                            continue;
                                    }
                                }
                                break;
                            case 1439984689:
                                c = 3;
                                break;
                            case 1669513377:
                                str8 = "۟ۨۜۢۚۦۛۙ۠ۚۖۡۦۦۜۗۤ۠ۢۡۤ۫ۖۗۖۢۘۖۙۜۘۜۚۧۗۘۜۥۙۡۦۙۗۨۡۗۦۖ";
                        }
                    }
                    break;
                case 110532135:
                    String str10 = "ۗ۬۫۠ۢۤۗ۫ۨۙۧۘۘ۟ۦۧ۬ۢۦۘ۠ۥۨ۫ۡ۬ۚۗ۟ۚۢ۠";
                    while (true) {
                        switch (str10.hashCode() ^ (-998062417)) {
                            case -42941676:
                                String str11 = "ۦۘۘۤ۠ۘۙ۟ۜۖۥۖۜۘۡۗۨۦۘۢۘۢۤۛۨۧۘۥۘ۬ۙۘۘۥۖۨۨ۟ۗۙۨۖۥۦۦ۠۠ۘۧۗۘۘ";
                                while (true) {
                                    switch (str11.hashCode() ^ (-1166031172)) {
                                        case -836398986:
                                            if (!str.equals("toast")) {
                                                str11 = "ۥۥۜۘۡۧۖۘۢۗۤۜۡۛۦۨۧۘۢۥۛۛۚ۠ۡۛۘۦۚۨۨ۠ۜۘۡۚۘۡ۬ۧۚۢۨۡۛۘۛۡۢۚۤ";
                                                break;
                                            } else {
                                                str11 = "ۢۚۦۥۗۗۖۚۡۘۜۖۡۘۚۥۧۨۗۡۘۨۦۡۦۜۖۘۙ۬ۜۘۚۡۘۚۘۨ۬ۗۘۘ";
                                                break;
                                            }
                                        case 7245619:
                                            str11 = "۬ۦۜۘۘۥۘ۫ۗۦۘ۬ۙۜۘۨۖۖۘۦۢۖۘۚ۠ۧۢ۟ۤۦۦۨۘۧ۫ۥۤ۟۠ۗۖۧ۫ۥۡۘ۟۫۬۬ۙۛۖۤۖۡۥۤۢ۬ۚ";
                                            break;
                                        case 507881126:
                                            str10 = "ۗ۠۬۫ۢۧۢ۫ۥۚۘۤۧۨۥ۬ۖۘۜ۬ۥۘۦۚ۟۠ۛۘۖۦۧۚۥۤۡۦۘۤۧۛۡۚ";
                                            continue;
                                        case 2052214153:
                                            str10 = "ۦۧۦۖۨۦۧ۠ۚۤۤۥۘۡۥۨۥۨۧۡۛۖۘۢۧۛ۫ۙۖۘۘۗۗۢۤ۠ۨۦۥۢ۬ۜۘۜۜۥ۠۠ۤۧۘۙۡۙ۬۠ۘ";
                                            continue;
                                    }
                                }
                                break;
                            case 322166218:
                                str10 = "ۙ۠ۧۦ۫ۢ۠ۗۡۤ۟ۥۘۤۖۘۛۙۖۗۙ۬ۚۖۘۨۥ۠۟ۛۨۘ۬ۤۗۙۦۘۥۨۜۘۖۨۗۧۦۘ۠ۧۜۨۖۖۛۦ۟";
                            case 1870373625:
                                break;
                            case 2103917085:
                                c = 1;
                                break;
                        }
                    }
                    break;
                case 1550503497:
                    String str12 = "ۡۛۡۡ۠ۡۖ۬ۥۨۘ۫۠ۥۨ۟ۘۘ۠ۗ۬ۜۚۥۥۘۧۘۚ۫ۘۘۥۦۢ۬ۤۨۘۖۢۨۘ۫ۥۖۛۨۧۘۧۨۗ";
                    while (true) {
                        switch (str12.hashCode() ^ (-713757858)) {
                            case -1799667479:
                                String str13 = "ۦ۟ۛۛۤۘۜۙۚ۠ۚۨۦۗۚۡۨۨۢۘۡۤۡۘۥۘۘ۬ۗۖۘ";
                                while (true) {
                                    switch (str13.hashCode() ^ 1518862742) {
                                        case -1026749454:
                                            str12 = "ۛۗۦۘ۫۠ۥۨۘۛۧ۠ۨۘۘۜۘۥ۫۟ۛ۠ۘۘۙ۬ۘۚۜۘۥۧۦ";
                                            continue;
                                        case -299896422:
                                            if (!str.equals("opendebug")) {
                                                str13 = "ۤ۠۟ۚۜ۟۫۟۠ۨۜۜۦۨۘ۠ۧۦ۬ۢۗ۫ۢۢ۬ۨۙ۠ۜۜۘۨ۫ۦۘۖۡۚ۠۫ۖۘۦۥۧۘۙۗۛۚۥۨۘ";
                                                break;
                                            } else {
                                                str13 = "ۛۢۛۢ۫ۧۜۘۡۘۥۡۗ۫ۥ۟ۛۢۚۚۡۥۗۡۢۗۙۥۗۦۘۚۧۛۖۜۨۘۘ۫ۗۡۢۦۘۛ۟ۘۜۤۗ";
                                                break;
                                            }
                                        case 123539995:
                                            str12 = "ۗۖ۠ۢۧۡۘۢ۬ۦۖ۫ۡۘۡۗۗ۠ۡۖۥ۫۫ۗۚۖۘ۬ۡۜۢۜ۬ۨۥۜۘۛۘۧۘۢۜۨۢۘۛ";
                                            continue;
                                        case 286925836:
                                            str13 = "ۤ۬ۥۘ۫۫ۡۥۡ۠ۗۨۘۘۤۦۡۘۢۧۜۙۡ۟ۤۘۘۨۧۜۤۧ۟ۘۦۧۘ۟ۧۖۘ";
                                            break;
                                    }
                                }
                                break;
                            case -1471384469:
                                c = 4;
                                break;
                            case -1285447638:
                                break;
                            case 260053065:
                                str12 = "۠۠ۖۘۢۥ۠ۢۚۥۧۖۨۜۤۛۖۥۚۚۜ۟۠ۨۚۗۚ۠ۚۗۚۧ۠ۚۜۤ";
                        }
                    }
                    break;
                default:
                    c = 65535;
                    break;
            }
            Handler handler = s3Var.g;
            String str14 = "ۛۢ۫۫ۡۨۘۗ۬ۛۛۜۛ۬ۙ۟ۗ۠ۖۢۢۧ۬۟ۦۘۚۧۥۜۨۥۘۧۛۥۘۚۙۙۧۖۘ۬۫ۖۘ";
            while (true) {
                switch (str14.hashCode() ^ 1002069553) {
                    case -901603414:
                        String str15 = "ۡۢۙ۠ۚۘۥۖۘۖۨۧۦۨۨۘۢۚۧۘۜۥۘۗ۠ۖۛ۫ۜ۠ۘۧۗۚۨۥ";
                        while (true) {
                            switch (str15.hashCode() ^ (-1263892380)) {
                                case -1691495772:
                                    str15 = "۫ۥۦۥۖۦۘۗۧۖۘۨ۠ۦۘ۠۬ۧۗۜۨ۟۠ۧۨۙۨۘۜۙۖۘ۫ۛۨۘۤۚۛۦۢۖ۬ۘۙۛۨۚ";
                                    break;
                                case -1239376500:
                                    str14 = "ۗۨۦۧ۠۟۬ۙ۬ۙۧۧۨۗۦۤۢۘۘ۫ۛۦۘۙۘ۫ۢۢۦۘۢ۫۫ۜۗۛۡۜۗۡۦۘۘۚۘۘ۟ۚۙۙۘۘۖۦۘۘۥۡ۫";
                                    continue;
                                case -440439585:
                                    str14 = "۫ۡۜۘۢۨۨۚۘ۫ۥۜۘۘۙۗۨۖۘۗ۫۠ۢۡۚۨ۫ۜ۟ۗۡۥۘ";
                                    continue;
                                case -418878316:
                                    if (c == 0) {
                                        str15 = "ۜ۫ۘۨۦۨۘۖۢۖۦۤۦۧۨۡۚۡۖۛۢۡۘۡۗ۬ۜۜۨۘۤۥۘۥ۬ۡۥۗ۬ۨۜۖۘۘۤۖۙۢۜۘۡ";
                                        break;
                                    } else {
                                        str15 = "ۛۥۦۚۧۘۘۤۡ۬ۗۧۚۢ۟ۡ۟ۦۗۚۖۥۘۨۤۛۛۨۡ۠ۥۚۖۨۨۛ۠ۚ";
                                        break;
                                    }
                            }
                        }
                        break;
                    case 214436758:
                        String str16 = "ۦ۫ۜۘۙ۠ۛۚۥ۫ۗۖۘۡۡۛۛۘۛۖ۠ۗۢۨۖۘۙۦۤۛۥۙ۫ۖۘۘۜۜۜ";
                        while (true) {
                            switch (str16.hashCode() ^ 1917404358) {
                                case -1689355334:
                                    str16 = "ۖۜ۫ۡۨ۬ۧۧۚۨ۬ۢۙۡۖۚۨۘۦۦۛۘۛۙۤۜۦۘۖۛۧ۠ۘۥۘۜۖۦۘ";
                                    break;
                                case -187594734:
                                    String str17 = "ۛ۫ۜۘ۬ۡۖۘۧۜۜۘۚ۠ۥۘۗۚۖۘ۠ۥۦ۠ۘۥۘ۬ۙۥۘۙۨۗۧۦۧۖۖۧۢ۟ۦۚ۠۠ۜۤۤۚۥۘۙۚۖۚ۠۬ۗ۫ۥۘ";
                                    while (true) {
                                        switch (str17.hashCode() ^ 102349731) {
                                            case -1665535868:
                                                String str18 = "ۘۘۡۘۤۤۡۗۗۥۘۗۦۨۜ۠۫ۛۘ۠ۦ۟ۡۘۡۜ۬ۦ۟ۦۛۧۗۦۘۚۡ۫۫۬ۖۘۧۥۨۨۤۛۗۖ۟";
                                                while (true) {
                                                    switch (str18.hashCode() ^ 1691484303) {
                                                        case -482067870:
                                                            str17 = "ۚۛۥۛ۟ۡۖ۬ۦۛۘ۠ۢۧۖۘۤ۟ۖۘ۬ۧۧ۬ۜۦۥۨۢۖۨۦۘۦۦۗ۟ۙۛۗ۠ۛ۫۟ۜ";
                                                            continue;
                                                        case 240278746:
                                                            str17 = "۠۬ۖۘ۫ۖۜۚۘۘۘۙۜ۟۠۬ۡۘۤ۫ۜ۟ۙۥۘۦۖۤ۫ۜۚۖۗۛۖۘۗۥ۟ۛۨ۠ۛۖۡۘۜۤ۬ۛۛ۠ۜۘ۟ۥۘۘ";
                                                            continue;
                                                        case 454927218:
                                                            if (c == 2) {
                                                                str18 = "ۖۖۨۢۘۘۥۖۜۢۧۛۘۛۘۤۚۡۘۢۘ۬ۢۡۛۙ۫ۥۘۥۜۘۘۘۡۛۜ۫ۗ";
                                                                break;
                                                            } else {
                                                                str18 = "۬ۢۤۢۖ۫ۘۥۧۘۤ۫ۦۘۖۛۤۙۙۥۦۤۖۗۛۥۘۤۛۤۨۖۦۘ۬ۧۨۘۜ۠ۦۘ";
                                                                break;
                                                            }
                                                        case 686665002:
                                                            str18 = "۟ۨۘۘۢ۬ۨۨۡۨۤۤۙۤۖۡۘۡۘۡۘۤ۟ۗۢۥۘۨۙۜۨۨ۬ۥۦۖۧۥۙۛۥۥۘۡۜ۬ۧۘۧۥۗۗ";
                                                            break;
                                                    }
                                                }
                                                break;
                                            case -1075468542:
                                                String str19 = "ۚۚۢۤۗۥۘۥۚ۠۬۟ۖۤۧ۟ۖۧۨ۫ۧۖۘۨۥ۟۟ۥ۫ۥ۬ۗ۬۫ۗۥۨ۫ۦۦۡۧ۟ۗ";
                                                while (true) {
                                                    switch (str19.hashCode() ^ (-1987970436)) {
                                                        case -1706926902:
                                                            String str20 = "۫۠ۦ۫ۦ۟ۤۜۖۘۗ۫۟ۘۜۥۢۦۥۘۨۖ۟ۦ۬۠۠ۚۖۘۤۥۢۖۡۘۡۗۦۘۖۧۤۥۦۤ";
                                                            while (true) {
                                                                switch (str20.hashCode() ^ 97727126) {
                                                                    case -577364899:
                                                                        str20 = "ۥۡۨۘۙۙۨۘۚۘۘۢۜۧۘۚ۠۠ۚۘۥۡۘۤۥۡ۟ۡ۠۠ۧۜۨۡۘۥۘۥۗۖۘۚۥ۠ۘۨۖ";
                                                                        break;
                                                                    case 182092539:
                                                                        handler.post(new o3(s3Var, 2));
                                                                        return;
                                                                    case 737533371:
                                                                        Log.d(str3, "未知消息类型: " + str);
                                                                        return;
                                                                    case 1053821877:
                                                                        String str21 = "ۜۧۧۖۨۧ۬ۧۤۜ۟ۡ۠ۦۡۨۤ۠ۡۖۡۘۖۢۥ۟ۢۨۥۡۛۦ۟ۦۘ۫۠ۥۥۗۦۧۡۜۘۨ۠۠ۧۘۘۗ";
                                                                        while (true) {
                                                                            switch (str21.hashCode() ^ 605690657) {
                                                                                case -1784351495:
                                                                                    str20 = "ۧۤۘ۠ۢۘۘۖۨۘۘ۫ۗۤۖۚۡۘۡ۫ۚۧۗۢۚۧ۟ۛۘۜۦ۫ۥۘۨۚ۠ۛ۟ۖ۠۫ۡۖۘۖ";
                                                                                    continue;
                                                                                case -960039326:
                                                                                    if (c == 4) {
                                                                                        str21 = "ۤ۟ۘۡۧۗۥۘۧۢۛ۠ۨ۫۟ۙ۫ۜۘۢۗۨۧۛۗ۠ۥۗۙۛۖۨ۠۠ۙۦۡۙۛۧ۫ۖۘۤ۟۟ۥۢ";
                                                                                        break;
                                                                                    } else {
                                                                                        str21 = "۬ۖۚ۠ۦۤ۟ۖ۟ۡ۬۬ۚۛۤۢۚۖۖۗۜۘۤۥۨۘۗۦۨۘۜۛۜ";
                                                                                        break;
                                                                                    }
                                                                                case 1350156694:
                                                                                    str20 = "۫ۤۖۘۥۦۗۛۚۜۡ۬ۜۚۛۨ۫ۛۧۦۜۧۘۖۧۚۛۡۥۙۨ";
                                                                                    continue;
                                                                                case 1721190799:
                                                                                    str21 = "ۛۖۡۘۨۧۜۧۥۜۜۖۨۤۨۙۘۘۦۦۤۚۦۨۙۘۘۘۛۨۤۢۢۘۘۨۘۨۘۢ۟ۖ۫۠ۙ۫ۛۘۗۡۖۥۥۚۧۥۚ";
                                                                                    break;
                                                                            }
                                                                        }
                                                                        break;
                                                                }
                                                            }
                                                            break;
                                                        case -741065218:
                                                            String str22 = "۠ۗۘۘۘۨۖۡۛ۟ۘۘۖۘۜۢۡۘۚۖۧۘۛۛۖۘۤۘ۟ۨۘۗۙۤ۟ۛ۬ۤۗۘۧۘ۬۟ۘۧۧۡ";
                                                            while (true) {
                                                                switch (str22.hashCode() ^ (-256546310)) {
                                                                    case -1424753693:
                                                                        str22 = "ۡۢۗۥ۠ۦۘۧ۫ۦۘ۠ۖۨۤۦۢۥۡۚ۫ۥۙۨۙ۟ۧۧ۫ۤۡ۠ۡۡۥۛۥ۟ۡۤۡۤ۟ۙۤۢۗۤۙۜۨۘۥۘ۠";
                                                                        break;
                                                                    case -986383465:
                                                                        if (c == 3) {
                                                                            str22 = "ۥۖۛ۬ۛۥۨ۟ۗ۫ۨۥۥۢۤ۬۟ۜۘۤ۬ۜۘ۠۠ۜۘ۠ۨۢۢۢۜ";
                                                                            break;
                                                                        } else {
                                                                            str22 = "ۥۗۖۘۡۦۖۦۦۙ۫ۥۖۗ۬ۜۘۨۗۗۗۖۥۘۢۗۤ۠ۘۘۥ۟۬۟۬ۜۘۨۙۢ۠۠۠ۤۗۖۖۗ۫۫ۜۡ";
                                                                            break;
                                                                        }
                                                                    case -497498242:
                                                                        str19 = "ۘ۠ۘۢۦ۟ۜۚ۠۫ۢۦۜ۬۬ۙۦۙۦۙۢ۠۟ۡۘۘۧۨۨۘۤۛ۬ۧۘۖۜۥۜۢۥۢۙۨ۫۫ۛۚ";
                                                                        continue;
                                                                    case 1931877499:
                                                                        str19 = "ۦۡۧۘ۫ۤۤۖۧۦۨۧۖۧۤۜۘۤ۠۠ۖۜۥ۬ۥۙ۫ۙۜۨ۠۠";
                                                                        continue;
                                                                }
                                                            }
                                                            break;
                                                        case 340528281:
                                                            str19 = "ۢ۫ۥۘۖ۬ۥۨۚۥۦۨۜۘ۬ۖۘۛ۬۟ۚۗۙۦۙۦۤ۠ۗۚۙۡۘ۫۫ۥۜۢۨ۟ۦۡۢۗ";
                                                            break;
                                                        case 1933963018:
                                                            k2.closedPopupIds.clear();
                                                            k2.closedImagePopupIds.clear();
                                                            k2.closedHtmlPopupIds.clear();
                                                            k2.closedTextPopupIds.clear();
                                                            k2.closedMessagePopupIds.clear();
                                                            s0.startRequest(s3Var.a, false);
                                                            return;
                                                    }
                                                }
                                                break;
                                            case 71560330:
                                                str17 = "ۢۦۘۘ۟ۡۨ۠ۜۘۧۚ۫۬۟۟ۜۚۛۖ۬۠ۛۖ۟۟ۢۜۘۛ۟ۡۘ۠ۛۤۜۚۡۚ۫ۤ۟ۖۘۜۧۡۘۡۖۦۘۡۡۥۘ۫ۥۢ";
                                                break;
                                            case 2121886934:
                                                final int i = 1;
                                                handler.post(new Runnable(s3Var, str2, i) { // from class: core.pro.android.notify.p3
                                                    public final int a;
                                                    public final s3 b;
                                                    public final String c;

                                                    {
                                                        this.a = i;
                                                        this.b = s3Var;
                                                        this.c = str2;
                                                    }

                                                    @Override // java.lang.Runnable
                                                    public final void run() {
                                                        String str23 = "ۙۚۘۘۤۛۖۘۖۥۨۢۧۦۛۤۡ۠ۨۤۗۛۗۡ۠ۡۥ۠ۛۗ۬ۗۜۨۚۧۘۦۘۚۘۧۙۚۨۘ";
                                                        String str24 = null;
                                                        Handler handler2 = null;
                                                        s3 s3Var2 = null;
                                                        while (true) {
                                                            switch ((((str23.hashCode() ^ 2) ^ 670) ^ 838) ^ (-1648407196)) {
                                                                case -1685819990:
                                                                    String str25 = "ۙۙۨۘۤۡۦۖۧۡۡۘۢۥۡۧۘۜۢ۠۟ۤۖۢۨۖۘ۫ۡۥۘۨۛۤ";
                                                                    while (true) {
                                                                        switch (str25.hashCode() ^ 1750040255) {
                                                                            case -1826121590:
                                                                                str23 = "ۗ۠ۢۡۨۨۥۨۜۘۘۨۨۖۤ۟ۜ۟ۘۛۘۦۘۦۥۤ۬ۥۦۥ۟ۚۗۙ۠ۧۡ۫ۘۢ۟ۗۢ";
                                                                                break;
                                                                            case -634820768:
                                                                                str25 = "۫ۚۦ۠ۙۤ۟ۙۥۡۜۨ۬ۛۨۘ۟ۜۨۘۢۤۢۘۚۨۘۡۜۤۜۖۖۘ";
                                                                            case 630513815:
                                                                                String str26 = "ۡ۫۠ۤۢۚۜۦ۬ۘۜۖ۬ۛۘۘۙۡۧۢۥۙۡۥۖۘۚۤۙ۟ۗۘۦۙۡۘۜۗۗۙۚۥ۫ۖۘ";
                                                                                while (true) {
                                                                                    switch (str26.hashCode() ^ 1108066038) {
                                                                                        case -1964461789:
                                                                                            str25 = "ۦۖۧۘۦۢۘۘۤۤۖۘۥۛۖ۠ۢۤۥ۫ۛۨۜۨۘۢۙ۟ۛۖۗ۫ۦۘ۟ۙۡۘۤۥۜۘ";
                                                                                            break;
                                                                                        case -1039212868:
                                                                                            str25 = "ۨۦۖۘۗ۫۟ۚ۟ۥۘ۫ۜۦۘۗۖۥۨۢۙۡۧۧ۠ۤۡ۠ۡۜۚۥۘۚ۟ۦۤ۟ۘۙۡۧۙۦۖۘ";
                                                                                            break;
                                                                                        case 276399102:
                                                                                            str26 = str24 != null ? "ۢۜۤۡ۬ۖۘۢۖۖ۟ۤۜۘۦۢۙۥ۬۬ۢۛۨۘۚۖۥۘ۠۫ۢ۫۬ۨ" : "ۗۦ۟ۙۦ۫ۤۦۧۘۧۘ۫۟ۗۨۘۧ۫ۜۚ۫ۚۥۖۦۘۙۥۥۘۥ۠ۙۖۙۘۘۜۚۛۢۜ";
                                                                                        case 319276554:
                                                                                            str26 = "ۙۙۘۛ۫ۦۘۡۥ۫۟ۗۦۘۥۡۨۘۘۛۥۘۚۜۖۦۚۦۘۘۨۦۘۖ۫ۦۘ۫ۧ۬۬ۛۗۚۨ۠۠ۡۖۘۛ۠۫ۚ۬ۙۚۥۛ۫ۤ";
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case 1035781850:
                                                                                break;
                                                                        }
                                                                    }
                                                                    break;
                                                                case -1657302062:
                                                                    Toast.makeText(this.b.a, this.c, 0).show();
                                                                    str23 = "ۖۜۛ۠ۧۗۚۗۘۘۦ۟ۜۘۘۢۨۧۢۘۛۦۦۖۛ۠۫ۨۤۧۘۛۙۢۦۘۢۦۦۘۘۜۙۙۗۨ۟ۖۤۤۙۖۘ";
                                                                    continue;
                                                                case -958490216:
                                                                    str23 = "ۜۙ۠ۥ۬ۗ۟ۛۙۤۚۛۘۡۨۖۢۦۜۡۗۙۧ۠۠ۨۘ۫۬ۨۧۢۡۘۙۤۜۛ۠ۥۘ۫ۨۡۘ";
                                                                    continue;
                                                                case -661961839:
                                                                    str24 = this.c;
                                                                    str23 = "ۧۚۨۘۥۨۥۘۙۛۨۚۧۧۖۙۘۢۚ۠ۚۘۦۘ۠ۡ۠ۜۥۜۧۛۜ۫ۜ۠ۡۜۘۘۜۜۥۛۥۘۘۥۖۨۘ۟۬۫";
                                                                    continue;
                                                                case -644908610:
                                                                    str23 = "ۥ۠ۜۘۧۙ۬ۢۨۤۛۦ۫ۛۤۜۜۨۘۘۙۨۦ۠ۤۤۧۗ۫ۤۨۦۘۨ۬ۦۘۜۜۤ";
                                                                    continue;
                                                                case -415238887:
                                                                    handler2.postDelayed(new o3(s3Var2, 3), 300L);
                                                                    str23 = "ۜۙ۠ۥ۬ۗ۟ۛۙۤۚۛۘۡۨۖۢۦۜۡۗۙۧ۠۠ۨۘ۫۬ۨۧۢۡۘۙۤۜۛ۠ۥۘ۫ۨۡۘ";
                                                                    continue;
                                                                case -94310887:
                                                                    str23 = "ۦ۬ۥۘۘۤۡۖۖۜۡۚۘۘۖ۟ۘۘۖۦ۟ۚۛۡۘ۟ۙۧۨ۬ۜۤ۬ۨۘ۟ۡۗ۠ۙۨۖۙ۬ۖۜۧۘۧۙۤ۬ۢۙ";
                                                                    s3Var2 = this.b;
                                                                    continue;
                                                                case 240078513:
                                                                case 940095386:
                                                                    return;
                                                                case 283211377:
                                                                    str23 = "ۙۧۚۥۗۡۘۢۦۖۗۡ۬۠۟۫ۛۨۘۗ۟ۘۖۚۥۤۚۧ۫ۥۘۧ۟ۨۘۨۜۧۘ";
                                                                    handler2 = s3Var2.g;
                                                                    continue;
                                                                case 445593918:
                                                                    switch (this.a) {
                                                                        case 0:
                                                                            str23 = "۬ۗۡۘۢۨۥ۫ۥۘۜۥۥۚۦۖۘ۬ۢ۟۫۟ۘۘۚ۟ۖۜۦۛۨۡۘۘۡۖۡۘۧۨۦۘ";
                                                                            continue;
                                                                        default:
                                                                            str23 = "ۦ۬ۦۨۚۡۘۜۛۖۘۦ۟ۜۘۙۨۡۦۚۖۘۨ۬ۨۥ۬۠۟۬ۙۥۤۖۘۢۘۜۧۧۘۘۡ۫ۜۘ۫ۛۗۜۖ۟ۚ۠ۨ";
                                                                            break;
                                                                    }
                                                                case 1615506567:
                                                                    String str27 = "ۗۜۜ۟ۦۙ۬۬ۖۘۦۧۖۘۦ۫ۢۗۚ۟ۨۙ۠ۖۗۨۛۡۗۗۦۤ۬ۜ۠ۘۧ۠ۥۧ۠ۘۨۘۗ۠ۥۘ۟ۙ۠ۥ۫۬ۗۜۡۘ";
                                                                    while (true) {
                                                                        switch (str27.hashCode() ^ (-1166621404)) {
                                                                            case -1224038358:
                                                                                break;
                                                                            case -1124838:
                                                                                String str28 = "ۛۥۖۘ۫ۢۗۖۢۦۡۖۤۥۘۤۗۛۨۘۢۦۧۡۦۘ۠۫ۖۘۤۜۛۧۧۥۘۖۢۖۘ";
                                                                                while (true) {
                                                                                    switch (str28.hashCode() ^ (-617637410)) {
                                                                                        case -1156813803:
                                                                                            str28 = !str24.isEmpty() ? "ۢۡۦۘۛۢۦۘۧ۠ۖۘ۠ۙۦۘ۬ۘۧ۫ۗۦۚۦۤۛۢۢ۫ۖۨۦۖ۟ۤۡۧۧۜۢۛ۟ۜۧۖۧۘۢۗ۫۟ۛ۫ۘۢۜۗ۟ۧ" : "۟ۧۨ۬۫ۨۨۘۨۘ۬ۛۖۜ۠ۛۤۜۚۜۚۜۘۚۘۖۘۢۡ۟۟ۢۜۘۤ۠ۨۤۡ۬";
                                                                                        case -1031521587:
                                                                                            str27 = "ۗۥ۠ۛۧۨۤۘۖۤ۬۫۫ۥۘ۫ۢۥ۬۠ۚۜۖۥۘ۬ۜۜۘ۫ۡ۠ۢۧۙۖۙۜۘۗۦۥۘۚ۠ۦ";
                                                                                            break;
                                                                                        case -292256159:
                                                                                            str27 = "۫ۥۗۥۢۡۨ۟ۘۘۤۨۡ۠ۨۨۢۤۡۜۦۘ۫۬۬ۜۡۗۡ۠ۘۘ۫ۜۛ۬ۦۦۨۤ۟ۙ";
                                                                                            break;
                                                                                        case 732593801:
                                                                                            str28 = "ۚ۟ۘۘۛۢۗۗۥۙۚۧۡۧ۟ۡۘۦۙۖۘۙۢۙۧ۠ۜۘۦۗۛۛۦ۟۠ۚۥۨۧ";
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case 904273619:
                                                                                str23 = "ۜۨۗۘۢۙۖ۟ۢۖۤۛۨ۫ۨۘ۫ۘۧۚۤۢۦۘۖۢۤۨ۠ۖۘۙۙۨۖۘۗ۬ۡۨۧۗۦۧۨۖۘۡۨۡۘ";
                                                                                break;
                                                                            case 1250005301:
                                                                                str27 = "ۗۨۦۘۥۘۧۘۢۜۨۚ۠ۘۘۥۧۦۧۥۦۖۜۙ۠ۖۧۥۨۨۤۜۗ۠ۡ۠ۡۚۧ۬ۜۗ۠ۚۡۘۡۥۘۘۘۜ۟";
                                                                        }
                                                                    }
                                                                    break;
                                                                case 1917381654:
                                                                    Toast.makeText(s3Var2.a, str24, 0).show();
                                                                    str23 = "۫ۧۡۘۨۜۛۤۨۡۘۢۦۦۤۨ۬۬ۢۖ۟ۛۦۗۚۧۜۙۘۦ۬ۤۧ۠ۨۖۗ۬";
                                                                    continue;
                                                                case 2091886298:
                                                                    handler2.postDelayed(new o3(s3Var2, 3), 2000L);
                                                                    str23 = "ۜ۟ۦۘۥۧۦۖۢۢ۬ۚۖۢۧۘۘۙ۟ۖۡۛۤۙۘۦ۫ۧ۫ۤۡۘۧۙۢ۟۠۟۠ۘۜ۬ۗۙۚۥۜۥۥ";
                                                                    continue;
                                                            }
                                                            str23 = "ۖۥۙۘۜۛۗۛۦۙۚۘۛۜۡۘ۬۬ۗۘۢۦۘۡۦۡۘۥۡۜۘۢۢۛۧۙۡ۟۟ۖۤۥۦۘۗۘۖۘ۬ۖۚۗۥۚ";
                                                        }
                                                    }
                                                });
                                                return;
                                        }
                                    }
                                    break;
                                case 910850685:
                                    final int i2 = 0;
                                    handler.post(new Runnable(s3Var, str2, i2) { // from class: core.pro.android.notify.p3
                                        public final int a;
                                        public final s3 b;
                                        public final String c;

                                        {
                                            this.a = i2;
                                            this.b = s3Var;
                                            this.c = str2;
                                        }

                                        @Override // java.lang.Runnable
                                        public final void run() {
                                            String str23 = "ۙۚۘۘۤۛۖۘۖۥۨۢۧۦۛۤۡ۠ۨۤۗۛۗۡ۠ۡۥ۠ۛۗ۬ۗۜۨۚۧۘۦۘۚۘۧۙۚۨۘ";
                                            String str24 = null;
                                            Handler handler2 = null;
                                            s3 s3Var2 = null;
                                            while (true) {
                                                switch ((((str23.hashCode() ^ 2) ^ 670) ^ 838) ^ (-1648407196)) {
                                                    case -1685819990:
                                                        String str25 = "ۙۙۨۘۤۡۦۖۧۡۡۘۢۥۡۧۘۜۢ۠۟ۤۖۢۨۖۘ۫ۡۥۘۨۛۤ";
                                                        while (true) {
                                                            switch (str25.hashCode() ^ 1750040255) {
                                                                case -1826121590:
                                                                    str23 = "ۗ۠ۢۡۨۨۥۨۜۘۘۨۨۖۤ۟ۜ۟ۘۛۘۦۘۦۥۤ۬ۥۦۥ۟ۚۗۙ۠ۧۡ۫ۘۢ۟ۗۢ";
                                                                    break;
                                                                case -634820768:
                                                                    str25 = "۫ۚۦ۠ۙۤ۟ۙۥۡۜۨ۬ۛۨۘ۟ۜۨۘۢۤۢۘۚۨۘۡۜۤۜۖۖۘ";
                                                                case 630513815:
                                                                    String str26 = "ۡ۫۠ۤۢۚۜۦ۬ۘۜۖ۬ۛۘۘۙۡۧۢۥۙۡۥۖۘۚۤۙ۟ۗۘۦۙۡۘۜۗۗۙۚۥ۫ۖۘ";
                                                                    while (true) {
                                                                        switch (str26.hashCode() ^ 1108066038) {
                                                                            case -1964461789:
                                                                                str25 = "ۦۖۧۘۦۢۘۘۤۤۖۘۥۛۖ۠ۢۤۥ۫ۛۨۜۨۘۢۙ۟ۛۖۗ۫ۦۘ۟ۙۡۘۤۥۜۘ";
                                                                                break;
                                                                            case -1039212868:
                                                                                str25 = "ۨۦۖۘۗ۫۟ۚ۟ۥۘ۫ۜۦۘۗۖۥۨۢۙۡۧۧ۠ۤۡ۠ۡۜۚۥۘۚ۟ۦۤ۟ۘۙۡۧۙۦۖۘ";
                                                                                break;
                                                                            case 276399102:
                                                                                str26 = str24 != null ? "ۢۜۤۡ۬ۖۘۢۖۖ۟ۤۜۘۦۢۙۥ۬۬ۢۛۨۘۚۖۥۘ۠۫ۢ۫۬ۨ" : "ۗۦ۟ۙۦ۫ۤۦۧۘۧۘ۫۟ۗۨۘۧ۫ۜۚ۫ۚۥۖۦۘۙۥۥۘۥ۠ۙۖۙۘۘۜۚۛۢۜ";
                                                                            case 319276554:
                                                                                str26 = "ۙۙۘۛ۫ۦۘۡۥ۫۟ۗۦۘۥۡۨۘۘۛۥۘۚۜۖۦۚۦۘۘۨۦۘۖ۫ۦۘ۫ۧ۬۬ۛۗۚۨ۠۠ۡۖۘۛ۠۫ۚ۬ۙۚۥۛ۫ۤ";
                                                                        }
                                                                    }
                                                                    break;
                                                                case 1035781850:
                                                                    break;
                                                            }
                                                        }
                                                        break;
                                                    case -1657302062:
                                                        Toast.makeText(this.b.a, this.c, 0).show();
                                                        str23 = "ۖۜۛ۠ۧۗۚۗۘۘۦ۟ۜۘۘۢۨۧۢۘۛۦۦۖۛ۠۫ۨۤۧۘۛۙۢۦۘۢۦۦۘۘۜۙۙۗۨ۟ۖۤۤۙۖۘ";
                                                        continue;
                                                    case -958490216:
                                                        str23 = "ۜۙ۠ۥ۬ۗ۟ۛۙۤۚۛۘۡۨۖۢۦۜۡۗۙۧ۠۠ۨۘ۫۬ۨۧۢۡۘۙۤۜۛ۠ۥۘ۫ۨۡۘ";
                                                        continue;
                                                    case -661961839:
                                                        str24 = this.c;
                                                        str23 = "ۧۚۨۘۥۨۥۘۙۛۨۚۧۧۖۙۘۢۚ۠ۚۘۦۘ۠ۡ۠ۜۥۜۧۛۜ۫ۜ۠ۡۜۘۘۜۜۥۛۥۘۘۥۖۨۘ۟۬۫";
                                                        continue;
                                                    case -644908610:
                                                        str23 = "ۥ۠ۜۘۧۙ۬ۢۨۤۛۦ۫ۛۤۜۜۨۘۘۙۨۦ۠ۤۤۧۗ۫ۤۨۦۘۨ۬ۦۘۜۜۤ";
                                                        continue;
                                                    case -415238887:
                                                        handler2.postDelayed(new o3(s3Var2, 3), 300L);
                                                        str23 = "ۜۙ۠ۥ۬ۗ۟ۛۙۤۚۛۘۡۨۖۢۦۜۡۗۙۧ۠۠ۨۘ۫۬ۨۧۢۡۘۙۤۜۛ۠ۥۘ۫ۨۡۘ";
                                                        continue;
                                                    case -94310887:
                                                        str23 = "ۦ۬ۥۘۘۤۡۖۖۜۡۚۘۘۖ۟ۘۘۖۦ۟ۚۛۡۘ۟ۙۧۨ۬ۜۤ۬ۨۘ۟ۡۗ۠ۙۨۖۙ۬ۖۜۧۘۧۙۤ۬ۢۙ";
                                                        s3Var2 = this.b;
                                                        continue;
                                                    case 240078513:
                                                    case 940095386:
                                                        return;
                                                    case 283211377:
                                                        str23 = "ۙۧۚۥۗۡۘۢۦۖۗۡ۬۠۟۫ۛۨۘۗ۟ۘۖۚۥۤۚۧ۫ۥۘۧ۟ۨۘۨۜۧۘ";
                                                        handler2 = s3Var2.g;
                                                        continue;
                                                    case 445593918:
                                                        switch (this.a) {
                                                            case 0:
                                                                str23 = "۬ۗۡۘۢۨۥ۫ۥۘۜۥۥۚۦۖۘ۬ۢ۟۫۟ۘۘۚ۟ۖۜۦۛۨۡۘۘۡۖۡۘۧۨۦۘ";
                                                                continue;
                                                            default:
                                                                str23 = "ۦ۬ۦۨۚۡۘۜۛۖۘۦ۟ۜۘۙۨۡۦۚۖۘۨ۬ۨۥ۬۠۟۬ۙۥۤۖۘۢۘۜۧۧۘۘۡ۫ۜۘ۫ۛۗۜۖ۟ۚ۠ۨ";
                                                                break;
                                                        }
                                                    case 1615506567:
                                                        String str27 = "ۗۜۜ۟ۦۙ۬۬ۖۘۦۧۖۘۦ۫ۢۗۚ۟ۨۙ۠ۖۗۨۛۡۗۗۦۤ۬ۜ۠ۘۧ۠ۥۧ۠ۘۨۘۗ۠ۥۘ۟ۙ۠ۥ۫۬ۗۜۡۘ";
                                                        while (true) {
                                                            switch (str27.hashCode() ^ (-1166621404)) {
                                                                case -1224038358:
                                                                    break;
                                                                case -1124838:
                                                                    String str28 = "ۛۥۖۘ۫ۢۗۖۢۦۡۖۤۥۘۤۗۛۨۘۢۦۧۡۦۘ۠۫ۖۘۤۜۛۧۧۥۘۖۢۖۘ";
                                                                    while (true) {
                                                                        switch (str28.hashCode() ^ (-617637410)) {
                                                                            case -1156813803:
                                                                                str28 = !str24.isEmpty() ? "ۢۡۦۘۛۢۦۘۧ۠ۖۘ۠ۙۦۘ۬ۘۧ۫ۗۦۚۦۤۛۢۢ۫ۖۨۦۖ۟ۤۡۧۧۜۢۛ۟ۜۧۖۧۘۢۗ۫۟ۛ۫ۘۢۜۗ۟ۧ" : "۟ۧۨ۬۫ۨۨۘۨۘ۬ۛۖۜ۠ۛۤۜۚۜۚۜۘۚۘۖۘۢۡ۟۟ۢۜۘۤ۠ۨۤۡ۬";
                                                                            case -1031521587:
                                                                                str27 = "ۗۥ۠ۛۧۨۤۘۖۤ۬۫۫ۥۘ۫ۢۥ۬۠ۚۜۖۥۘ۬ۜۜۘ۫ۡ۠ۢۧۙۖۙۜۘۗۦۥۘۚ۠ۦ";
                                                                                break;
                                                                            case -292256159:
                                                                                str27 = "۫ۥۗۥۢۡۨ۟ۘۘۤۨۡ۠ۨۨۢۤۡۜۦۘ۫۬۬ۜۡۗۡ۠ۘۘ۫ۜۛ۬ۦۦۨۤ۟ۙ";
                                                                                break;
                                                                            case 732593801:
                                                                                str28 = "ۚ۟ۘۘۛۢۗۗۥۙۚۧۡۧ۟ۡۘۦۙۖۘۙۢۙۧ۠ۜۘۦۗۛۛۦ۟۠ۚۥۨۧ";
                                                                        }
                                                                    }
                                                                    break;
                                                                case 904273619:
                                                                    str23 = "ۜۨۗۘۢۙۖ۟ۢۖۤۛۨ۫ۨۘ۫ۘۧۚۤۢۦۘۖۢۤۨ۠ۖۘۙۙۨۖۘۗ۬ۡۨۧۗۦۧۨۖۘۡۨۡۘ";
                                                                    break;
                                                                case 1250005301:
                                                                    str27 = "ۗۨۦۘۥۘۧۘۢۜۨۚ۠ۘۘۥۧۦۧۥۦۖۜۙ۠ۖۧۥۨۨۤۜۗ۠ۡ۠ۡۚۧ۬ۜۗ۠ۚۡۘۡۥۘۘۘۜ۟";
                                                            }
                                                        }
                                                        break;
                                                    case 1917381654:
                                                        Toast.makeText(s3Var2.a, str24, 0).show();
                                                        str23 = "۫ۧۡۘۨۜۛۤۨۡۘۢۦۦۤۨ۬۬ۢۖ۟ۛۦۗۚۧۜۙۘۦ۬ۤۧ۠ۨۖۗ۬";
                                                        continue;
                                                    case 2091886298:
                                                        handler2.postDelayed(new o3(s3Var2, 3), 2000L);
                                                        str23 = "ۜ۟ۦۘۥۧۦۖۢۢ۬ۚۖۢۧۘۘۙ۟ۖۡۛۤۙۘۦ۫ۧ۫ۤۡۘۧۙۢ۟۠۟۠ۘۜ۬ۗۙۚۥۜۥۥ";
                                                        continue;
                                                }
                                                str23 = "ۖۥۙۘۜۛۗۛۦۙۚۘۛۜۡۘ۬۬ۗۘۢۦۘۡۦۡۘۥۡۜۘۢۢۛۧۙۡ۟۟ۖۤۥۦۘۗۘۖۘ۬ۖۚۗۥۚ";
                                            }
                                        }
                                    });
                                    return;
                                case 1317924757:
                                    String str23 = "ۚۧۥۘۥ۠ۖۘۗ۫ۙۡۛ۠ۨۨ۫ۡ۫ۡۘ۟ۚۘۨۧ۬ۦۜ۬ۗ۬ۢۜۜ۟ۦۥۚۜۦۘۡ۟ۡ۫ۜ۬ۡۚۘۨۖۦ۬ۙۖۘ";
                                    while (true) {
                                        switch (str23.hashCode() ^ 1001404697) {
                                            case -1871055730:
                                                str16 = "ۙۢۥۢ۫ۨۡۥۘۗۤۜ۫ۥۜۘۦۨۧۧۤۨۘ۫۫ۥۘ۬۫ۦۘۖۛۥۖۨۨۥ۬ۛ";
                                                continue;
                                            case -882453449:
                                                str16 = "ۘ۠ۘۘۖ۠ۥۘۢۥۜۘۙۚۨ۠ۘۡۘ۠ۡۢۖۘۜۘۙۚۜۡ۬۫ۙۢ";
                                                continue;
                                            case 402214660:
                                                if (c == 1) {
                                                    str23 = "ۢۥۢۚۦۦ۬ۥۘۘۢۘۥ۫ۢۖۚۧۙۡۖۖۘۢۛۘۘۙۦ۟ۧۥۗۜۗ۠۟ۤۥۘۙۤۘۖۨۙۨ۫ۨۘۙۨۨ";
                                                    break;
                                                } else {
                                                    str23 = "ۥۨۨۡ۠ۘۘۚۚۜۨۨۤ۬۫ۚ۟۬ۨۘۛۖۗۥۤۨۘۛۢ۬ۡۚۨ";
                                                    break;
                                                }
                                            case 2077891536:
                                                str23 = "ۡۦۖۘۘ۠ۘۦۥ۠۫ۗ۠ۗۦ۫۠۫ۙۘ۟ۥۘ۫ۛۥۘۜ۠ۢۡۖ۟";
                                                break;
                                        }
                                    }
                                    break;
                            }
                        }
                        break;
                    case 414353226:
                        str14 = "ۨۚۨۘ۠ۡۚۛۡ۠ۨۤۥۘۘۥ۬ۜ۬ۦۥ۫ۡۢۖۦۘۙۜۥۘ۟ۤۙۚۗ۟ۗۦۦۛۥۤۥۨۗ۫ۥ۬۟۠ۥ";
                        break;
                    case 1365513627:
                        String str24 = (String) map.get("title");
                        String str25 = (String) map.get("msg");
                        String str26 = (String) map.get("bt");
                        String str27 = "۟ۦ۠ۚۗ۟ۥ۫۟ۤۦۖۘ۟ۧۖۥۗۖۢۧۥۖۧۤۜۥ۠ۥۘۨۙۢۦۚۢ";
                        while (true) {
                            switch (str27.hashCode() ^ (-1972263552)) {
                                case -1935747796:
                                    str27 = "۟ۙۦۘۤۨۘۛۧۘۦۨ۫ۙۢۖۘ۠ۥۙ۟ۛۥۢ۟ۜۘ۠۠ۘۤ۟ۖۘۜۗۥۘۛۦۖ";
                                case -1733404730:
                                    String str28 = "ۛۖ۠ۢۥۗۡۖۧۘۤ۬ۦۘ۫ۨۧۘۚۦۥۗۜۛۤۢۚۡ۫ۢ۬ۖۘۛۥ۫۬ۦۥۘ";
                                    while (true) {
                                        switch (str28.hashCode() ^ (-1563557702)) {
                                            case -2050627439:
                                                break;
                                            case -614424242:
                                                str28 = "ۡۦۜۘۢۡۤۘۛۘۘ۫ۗۦۨۦۧۘۡ۬ۖۗۢۘۖۧۤۖ۬ۛۖۘۦ۬ۜۙۦ۠۠";
                                            case -321552076:
                                                strDecrypt = str26;
                                                break;
                                            case -126324564:
                                                String str29 = "ۚۧۜۘۘۜۧۚۚۘۘۨۨۥۢۜۨ۠ۛۚۤۙ۫ۦۘۧ۬ۛۘۥۧۦۖۡۘۧۗۘ";
                                                while (true) {
                                                    switch (str29.hashCode() ^ (-689372787)) {
                                                        case -1555850572:
                                                            str28 = "ۧۖۖۧۤۛۤ۫ۦۘۙۥۡ۟ۨۧۤۙ۠۬ۖ۫۫ۙۡۧۢۘۦۗۘ";
                                                            continue;
                                                        case -1187654916:
                                                            if (!str26.isEmpty()) {
                                                                str29 = "ۡۗۖۘ۬۠ۨۘۢۘۧۜۤ۟ۡ۟۫۫۠۟ۙۜ۫ۖ۠ۙ۟ۙ۟۠ۥۜۘۖۜۘۘۚۜۧۘ";
                                                                break;
                                                            } else {
                                                                str29 = "ۚۜ۠ۘ۫ۦۘۢۙۛۨۧۘۢ۬ۖۘۡۡۙۙۛۘۤ۬ۗۖۢۦۘۨۥۜۘ۠ۜۘۢ۬۟";
                                                                break;
                                                            }
                                                        case -328739849:
                                                            str28 = "ۗۨ۠ۧۢۧۖۙۥۘۙۨۤۛ۟ۦۨۢۢ۟ۡ۫ۚۢۙۧۖۜۜۢۡۘۛ۬ۜۥۘۘ۠ۢ۟ۦۜۗ۫ۗۙۖۥۘ";
                                                            continue;
                                                        case 1659775123:
                                                            str29 = "ۨۙ۫ۜۨۗۡۙۧۚۧۚ۠ۧۛۜۛۨۧۡۥۘۨۤ۠ۘۨۨۘ۟ۘۦ";
                                                            break;
                                                    }
                                                }
                                                break;
                                        }
                                    }
                                    break;
                                case -1354957430:
                                    String str30 = "ۨۛۨۘۗ۟ۨۧ۬ۜۢ۟ۘۘۜۤۘ۬۟۠ۤۦۗۧ۠ۨۘۛ۫ۖ۬ۛ۫ۨۗۘۘۛ۠ۨ۠ۡۖۧۤۡۥۦۦۘۤ۠ۛۚۘۢۧۢۚ";
                                    while (true) {
                                        switch (str30.hashCode() ^ (-921499205)) {
                                            case -898815201:
                                                str30 = "ۢ۫ۘۡۘ۠۠ۚۡۚۘۙۡۜۛۖۜۘۦۥۜۙۨۥۘۙ۬ۗۗۢۦ۬ۚۡۘۤ۟ۚ۟ۘ۟ۜۤۜۘ۫۫ۡۘۨ۠ۖۘۜۚۨۘۡۦۨ";
                                                break;
                                            case 373936101:
                                                str27 = "ۜۧۨ۫ۧۡۘۧ۟۠ۙۗ۠ۜۡۡۢ۫ۥۡۦۛۤ۠ۤۤۤۢۡۚۦۧۨ۬ۤۚ۬۠ۥۛ۠ۢ۬ۘ۠۟ۧۧۛۡ۬ۦۘۚ۠ۙ";
                                                continue;
                                            case 850028031:
                                                if (str26 == null) {
                                                    str30 = "ۚۛۗۦ۟ۘۡۢۧۚۗۡۖۚۡۛ۬ۨۘۗۛۨۘ۬۫ۜۛۛۡۗۤۨۧۥۘۗۡۜۘ";
                                                    break;
                                                } else {
                                                    str30 = "۟۫ۜۘ۫ۡۛۘۨۥۖۤۖۘۡۦۡۘۤۥ۟ۡ۫ۜۢ۟ۘۘۥۡۖۘۤۥۚ۟ۜۘۘۜ۬ۗ";
                                                    break;
                                                }
                                            case 1099651951:
                                                str27 = "ۡۙ۠ۦ۬ۗۚ۟ۥۧ۠۟۟ۢ۫ۨۚ۠ۦۚۤ۬ۤۚۜۤۨۘۘۖۢۜۢۨۘۡۚۥ۫ۖۗۡۢۘۘۜۨ۬ۙۚۜ";
                                                continue;
                                        }
                                    }
                                    break;
                                case 1868192055:
                                    break;
                            }
                        }
                        strDecrypt = "确定";
                        handler.post(new x(s3Var, str24, str25, strDecrypt, 3));
                        return;
                }
            }
        } catch (Exception e) {
            Log.e(str3, "handleParsedMessage执行错误", e);
        }
    }

    public void close() {
        synchronized (this) {
            this.l = true;
            f();
            d();
            WebSocket webSocket = this.f;
            this.f = null;
            String str = "ۗۖۚ۬ۨۧۥۚۦۛۨۘۖ۟ۖۜۜ۫ۧۗۤۜۗۥۘۤۦۤۥۚۖ۠ۧۡۤ۫ۘ۬ۜۡۘۨۜ۬ۘ۫ۙ۠ۤۚ";
            while (true) {
                switch (str.hashCode() ^ 839851225) {
                    case 33499710:
                        try {
                            webSocket.close(1000, "正常关闭");
                            break;
                        } catch (Exception e) {
                            break;
                        }
                    case 268942175:
                        break;
                    case 586806248:
                        String str2 = "ۥۘۖۘۜۢۡۘ۫۬ۦۘۙ۬ۡۘۨ۬۬ۜۨۗۛ۟ۡۚۨۘۘ۠ۙۡۘۘ";
                        while (true) {
                            switch (str2.hashCode() ^ (-2096472964)) {
                                case -1492902194:
                                    str2 = "ۗۜۥۡۦۙۧۗۛۢۢ۬ۢۜۘۘ۟۫ۜۧ۫۟ۤۗۗۚۚۜۚۚۛۖۗۖۤۡۤۡۨۥۘۨۤۖۘ";
                                    break;
                                case -907773005:
                                    if (webSocket == null) {
                                        str2 = "۬ۨۡ۫ۘۦۜۦۘۗۘۛۗۥ۠ۖۛۚۜۨۗ۟ۨۘۘۢۥۘ۬۫۬ۖۜۧۧ۫ۗۖۛ۠ۜ۠";
                                        break;
                                    } else {
                                        str2 = "ۚۡۙۢۗ۠ۘۥۡۧۙۦۘۡۨۡۘۜ۟ۛۚۚۨۘ۠ۧۨۤ۫ۦۘۜۜۚۤۙۚۛ۬ۥۘۘۢۛ۬ۢۛ";
                                        break;
                                    }
                                case 16234136:
                                    str = "۠ۚۨۘۛ۫ۡۢۚۤۙۢۥۘۧۖۜۢۥ۠ۚۖ۬ۤۥۛۨۘۤۥۜۡۧۨۘۗۖ۬ۗ۫ۜۘۡۥۡ";
                                    continue;
                                case 1409510095:
                                    str = "ۧۛۦۛۛۙ۫ۥۧۨۨۥۘۛۨ۟ۘ۫ۜۚۙۜۘۘۙۡۚۜۢۜۘۛۢ۫ۦۢۚۗۧۢۡ۬ۥۢۚۚۜ۬۟ۤۡۘۘۧ۫ۥۘ";
                                    continue;
                                    continue;
                            }
                        }
                        break;
                    case 1359933587:
                        str = "ۙۡۢۡۨۦۘۖۨۜ۠ۙۗۗ۠ۘۛ۠۬۟ۦۦۜ۠ۘۚۤۦۘۜۡ۠ۦۘۨۘۦۘ۟۫ۙۤۧۨۨۘ";
                        continue;
                    default:
                        continue;
                }
            }
        }
    }

    public void connect() {
        synchronized (this) {
            String str = "ۛۜۘۘۨۢۡۜۨۖۛۛۧۡۥۨۨۖۥ۬ۙۖۘ۟ۖۖۡۖ۠ۦۢۖۡۜۘۦۡ۠";
            while (true) {
                switch (str.hashCode() ^ (-1374118524)) {
                    case -1032754984:
                        Log.w(q, "已关闭状态，忽略connect()");
                        return;
                    case -997365611:
                        String str2 = "ۜۥۚۨۢۜۜۥۚۙ۠ۖۘۙۙۦۜۡۥۘ۫ۤ۠۠ۤۦۚ۬ۥۨۗ۟ۦۢۖۘۦ۫۠۟۬ۡۘ۠ۨۢ۟ۖۢۦۜۧۚۡۨۡۤۥ";
                        while (true) {
                            switch (str2.hashCode() ^ (-205044548)) {
                                case -1260770334:
                                    str = "۟ۚۚۨۘۢۨۛ۠ۜۚۜۘۗۢۜۘ۫ۗۖ۠ۙۤۘۖ۠ۚۤ۫ۧۥۖ۠ۗۚ۠ۨۜۨ۠۟ۧۥۧۘ";
                                    continue;
                                case 378414713:
                                    str2 = "ۨ۠ۖۘۢ۬ۖۘۡۥۖۚ۟ۙۢۦۖۘۙۖۦۤۗۢۢۨۘۘۚۧ۟ۗ۟ۡۘۙۘۦۘ۬ۚۜۘۡۘۖ۟ۤۛۨۤۦۘۥۨۥۘ";
                                    break;
                                case 554566705:
                                    str = "۫ۨۧۘۧۦ۬ۜۥۜۗۗۘۘۨۤ۬ۙۖۥۘۧۜۖۢۗۘۘۦۨ۬ۥۖۤ۟۫ۜ۫ۖۨۘ";
                                    continue;
                                case 723083281:
                                    if (!this.l) {
                                        str2 = "ۖ۟ۨۥ۠ۥۚ۟ۜ۟۬ۖۘۤۢ۠۬ۙۨۛۙۧۗۢۨۘۗۥۨۖۡ۟ۚ۟ۦۨۧۡۘۤۘۢۜۖۘۘ۠۬ۥ";
                                        break;
                                    } else {
                                        str2 = "ۛۙۤۤۥۨۢۗ۬ۜۜۤۗۙۘۜۙ۫ۛۚۨۘۨۦۘۘۡ۠۟۠ۗۖۡۘۧۘ۬ۛۜۘ";
                                        break;
                                    }
                            }
                        }
                        break;
                    case -728032036:
                        str = "۫ۜۡۥۦۤ۠ۖۘۘۦۦۨۘۛۙۘۘۦۜۢۦۡۜۘۨۗۘۘۘۘۖۘۖۧ۫ۡ۠۠۠ۥۘ۫۟ۧۡۦۥۘ";
                        break;
                    case -267246100:
                        String str3 = "۠ۤۖ۟ۜۘۤ۟ۡۘۡۤۨۘۙۘ۠ۨۙ۟ۦۜۦۘۡ۟ۧۢۜ۠ۢۙۗۦۧۦۙۘۡۘۗۚ۟ۙۖ۬ۦۨۛ۫ۦۘۤۚۨ۟ۚۙ";
                        while (true) {
                            switch (str3.hashCode() ^ 174194279) {
                                case -2933510:
                                    str3 = "ۚۘۜۘۢۜۜۜۦۧۘۡۧۨۘۚۨۥۘۧ۠ۨۘۖۚۜ۬۫ۦۜۚۜ۫۬۟ۚۢۜۧ۫ۖۘۖۙ۠ۤۦۥۘ۟ۥ۟ۡۗۥۘۖۜ۫۫ۥۤ";
                                    break;
                                case 565205081:
                                    String str4 = "ۡ۟ۖۘۨۖۘۜۗۙۦۤ۫ۚ۠ۨۘۦۖۖۘۜۙۦۘ۠ۗۤۥۢۜۘۤۨ۫ۢۖۥۘۚۙۡۘۖ۟۠ۡۢۙ۫ۥۦ۠ۧۖۘ";
                                    while (true) {
                                        switch (str4.hashCode() ^ (-1818909991)) {
                                            case -2010735927:
                                                str3 = "ۨۚۖۚۡۢۜ۫ۘۘۧۤۥ۟ۥۘۡۢ۬ۜۢۛ۟ۨۖۘۤۙ۬۫ۢۦ";
                                                continue;
                                            case -1959916193:
                                                if (!this.n) {
                                                    str4 = "۟۠۫۬۠ۡۘۨۛۘۜۜۦۖۘۘ۬۬ۜۘۤۨۛۛۜۡۘ۟۫ۗۜۘۢۡۘۙۗۥ۟ۨۜۛۙۥۘ";
                                                    break;
                                                } else {
                                                    str4 = "ۦۗۦۚ۬ۘ۟ۤۜۤۛۨۘۦۨۖۚۢۦۘۙۨۘۙۢۥۘۘ۟۟ۧ۫ۨۘ۫ۧۨ۬ۤۥۢۤ۫ۢۙۜۧۡۘۚ۠ۢ";
                                                    break;
                                                }
                                            case 389499675:
                                                str3 = "ۘۖۡۤۡۥ۟۫ۨ۠ۘۧ۬ۗۢۗۨۨ۬ۧۨۚۛۤۢۜ۠ۥۦۧۘۜۙۨۨۗۥۨۧ۬۟ۛۚۜۘۘۢۨۗ";
                                                continue;
                                            case 1679179546:
                                                str4 = "ۘۜۤ۟ۡۘۚۤۥۘۢۢۚ۠ۧۘۘ۠ۘۜۘۥۡۚۥۡ۫ۡۖۖۘۖۨۙۢ۬ۢۦۜۥۘ۠ۡ۟ۤۚ۠";
                                                break;
                                        }
                                    }
                                    break;
                                case 2026710458:
                                    Log.i(q, "已连接，忽略重复connect()");
                                    return;
                                case 2079337724:
                                    String str5 = "ۦۥۢۥۜ۟ۖۗۜۨۤۛ۠ۥۦۤۤۘۘ۟ۢۤۘۘۢۤۤۚ۠ۛۨ۟ۘۘ۬ۦۘۤۙۛ۬ۨ۠ۖ۫ۨ۬ۖۨۙ۠ۗۖۗ۠";
                                    while (true) {
                                        switch (str5.hashCode() ^ 92770890) {
                                            case -1210627895:
                                                String str6 = "ۤۥ۟ۨۖۧ۬۠ۥۢ۫ۥۧۨۧۨۥۛۥ۬ۘۛ۬۬ۛۚۢۚ۫ۜ";
                                                while (true) {
                                                    switch (str6.hashCode() ^ (-1377908140)) {
                                                        case -1589305084:
                                                            if (!this.m) {
                                                                str6 = "ۜۚۢۢۡ۫ۚ۬ۖۘۚ۟ۚۧ۟ۧۖ۬۬ۡۖۥ۠۬ۚۤۨۚ۬ۥۘۦۥ۠ۧۜۧۘۡۡۘۘۚۧۘۘ۠۫ۛۧۖۥ";
                                                                break;
                                                            } else {
                                                                str6 = "ۧۜۛۚۦۢۘۘۤۡۘۨۘ۬ۙۚۚ۬ۛۗ۠ۜۘ۫ۦۨۘۡۖ۬ۤۨۧۘۚ۬ۚۤۙۦۘ۫ۡۗۥۙۡۖۨۡۘۙ۬ۢۘۖۧۙۗۛ";
                                                                break;
                                                            }
                                                        case -672393176:
                                                            str5 = "ۗۥۦۨۗۜۘۖ۠ۖ۟ۖ۬ۘۘۙۥۜۖۚۨۘ۬ۗۛۤۧۨۘۧۗۜ۬ۥۨۘۗ۟ۦۧۦۤۨۛۢ۠ۤۘۡۖ۬";
                                                            continue;
                                                        case 1387448880:
                                                            str6 = "ۘۙۨۧۤۚۙۜۢۛۥۢ۟ۤۨۘ۟ۨۦۘۜۗۦۡ۠ۧۜۖۖۘ۫ۢۖۦۛۤۢۡ۟ۜۙۜۖ۠ۜۡۖۜۘۗۤۥۘۧ۬ۜۘۚ۠۟";
                                                            break;
                                                        case 2111559495:
                                                            str5 = "ۤۨۢۖۧۡۤۧ۫ۖ۬ۙۦ۟ۦ۬ۧۢۜۘۨۘۖۥ۟ۜۖۜۢۢۛۨۧۘۘۜۗۙ۬ۨۧ۟ۤۥۢۧۦ۠ۦۘۗ۫۠ۥۨ۟";
                                                            continue;
                                                    }
                                                }
                                                break;
                                            case -232352117:
                                                str5 = "۟ۙۖ۬ۧۙۙۡۖۘۜ۠ۥ۟ۘۧۘۚۙۨۗۜۘ۫ۘۘۖۥۦۘۛۧ۠ۗۚۖۘۚۥۛ";
                                                break;
                                            case 216843312:
                                                Log.i(q, "正在连接中，忽略重复connect()");
                                                return;
                                            case 1109230549:
                                                e();
                                                return;
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

    public final void d() {
        synchronized (this) {
            ScheduledFuture scheduledFuture = this.k;
            String str = "ۚۡۙ۫ۥۙۤۡۨۘۘۧۚۨۢۡۤ۬ۥۚۤۥ۫۟۠ۗ۫ۢۢۛۦۡۗ۟ۙۢۜۘ";
            while (true) {
                switch (str.hashCode() ^ 86513105) {
                    case -1901253474:
                        break;
                    case -1366032300:
                        str = "۬ۢۢ۫ۛ۫ۦ۟۠ۤ۬ۛۢۥ۬۟ۧۧۢ۬۠ۙۢۙۦ۠۟ۤ۠ۖۜۦۢۖ۟";
                        continue;
                    case -366154810:
                        String str2 = "ۥۙۖۘۘۦۨۘۗۙۥ۠۬ۢ۠۫۫۫۟۟ۚۥۧۤۡۘ۟ۢۡۧۦۙ";
                        while (true) {
                            switch (str2.hashCode() ^ (-920889306)) {
                                case -696057246:
                                    str = "ۥۧۜۖۧۜۘۥۙۖۘۤ۬ۧۘۙ۫ۜۧۥ۬ۡۨۘۨۤۧۥۡۚۨۘ۟";
                                    continue;
                                    continue;
                                case -219505749:
                                    if (scheduledFuture == null) {
                                        str2 = "ۛۥۨۧۢۘۘۦۘۦۘۚۜۜۢۙۘ۟ۜۘ۫۬ۦۢۚۖ۟ۚۜۦۛ۠ۤ۠ۛۜۘۘۥۛ۟۠ۚۧ";
                                        break;
                                    } else {
                                        str2 = "ۧۢۢۦۨۧۦۤۜۘۨۥۘۖۙ۬ۢۤۖۘ۬ۛۢ۫ۘۢۢۦۖ۟ۘ";
                                        break;
                                    }
                                case -83891491:
                                    str = "ۦۢۥۛ۫ۗۤۗ۬ۨ۠ۛۡ۟ۧۨۢۘۜۨ۬ۛ۬ۛ۬۫۬ۗ۬ۘۧۘۖۛۜۘ";
                                    continue;
                                case 1819355769:
                                    str2 = "۠۟ۚۡۛۖۘۚۙ۟۫۟ۚۦۙۥۡۡۨۘ۫ۖۚۜۨۛ۬ۛ۟ۖۘ۟۟ۦ۟ۦۘ۫ۨۡۘ۠ۜۜۘ";
                                    break;
                            }
                        }
                        break;
                    case 1797808304:
                        scheduledFuture.cancel(true);
                        this.k = null;
                        break;
                }
            }
        }
    }

    public final void e() {
        synchronized (this) {
            this.m = true;
            d();
            this.e.newWebSocket(new Request.Builder().url("wss://ws.yunzhuru.com/ws?appid=" + this.b + "&devices=" + this.c + "&key=" + this.d).build(), new q3(this));
        }
    }

    public final void f() {
        synchronized (this) {
            ScheduledFuture scheduledFuture = this.j;
            String str = "ۦ۟ۥۢ۠ۛۤۤۥۘ۫ۡۘۤ۫ۦۥ۫ۥ۟ۜۨۘ۬ۙۧۖۛۘ۫ۡۖ";
            while (true) {
                switch (str.hashCode() ^ 793395816) {
                    case -121351770:
                        str = "ۙ۠ۡۤۗۦۘۢ۬۟۬ۡۛ۫ۦ۟ۙ۬ۦۘۗۨۢۢ۬ۙۛۨۜۧۧۦۖۡۤۘۤۛۛۗۡۘۢۧۥۘۨۘۘۤۚۜۘۖۜ۬ۥۦۘ";
                        continue;
                    case 7074941:
                        break;
                    case 204257780:
                        String str2 = "ۘۤ۠ۢۗۘ۬ۡۡۥۧۥۘۧۛۡۘۡۡۘۘۗۗۜۘۡۨۘ۬۫ۘۨۥۘۤۥۨۘۚۨۤۨ۫ۖۧ۟ۦۘۜ۟۫ۙۦۨۤۗ۟ۦ۫ۘۘ";
                        while (true) {
                            switch (str2.hashCode() ^ 1651941419) {
                                case -2137353881:
                                    if (scheduledFuture == null) {
                                        str2 = "ۙۦۛۧۛۗ۠ۦۥ۠ۚ۬ۖۢۥۘۜۨۧۘۡۛ۟۠۟ۘۘۛۗۗۦۖۡۘۦۢ۬ۢۖۘۖ۬ۖۥ۟ۖۘۥۢۘۛۧۥۘ";
                                        break;
                                    } else {
                                        str2 = "۫ۜ۟ۜۗ۫ۧۘۨۥۦۘۙۗۙۥ۠ۤۗۙۦۤۨ۫ۢۚۢۚۜۦۥ۠ۥۘۘۡۨۘۨۡۦۘ۬ۗۜۘۤۖۡۘۢۛۜۤۜۗۜ۟";
                                        break;
                                    }
                                case -472014593:
                                    str = "ۜۡۛ۟ۘۨۘۤۚۖۘۚۧ۫ۨۛۥۧۜۛ۫ۥۦۘ۬ۙۖۡۛۘۘۜۥۖۘۥۗۦۛ۟ۢۨۡ۟ۡۡۛ۬ۡۜۥ۫ۨۤ۠ۡۘۦۧ";
                                    continue;
                                case -146239515:
                                    str2 = "۫ۚۘۘ۠۠ۡ۫۠ۥۘۥۖۨۘۨۨۨۘ۫ۦ۠۬ۦۘۜۘۡۘ۫ۘۜۨۢ۠۬ۜۥۘ۟ۗۡۘۦ۬ۘۙ۫۟۟ۗۨۘۡۚۛۚۚ۬ۚۨ";
                                    break;
                                case 1898714704:
                                    str = "ۗۤۜۦۘۨۘۚۤۘۗ۫۫ۜۘۚۘۢۜۡۚۡۡ۬ۦۡۦۦۡۤۢۙ۠ۧۚۦۦۘۜۨۜۘۘۢ۟";
                                    continue;
                                    continue;
                            }
                        }
                        break;
                    case 1028125140:
                        scheduledFuture.cancel(true);
                        this.j = null;
                        break;
                    default:
                        continue;
                }
            }
        }
    }

    public void sendMessage(String str) {
        WebSocket webSocket = null;
        String str2 = "ۘۚ۠۫ۙۜۚۜ۬ۙۛۛۦ۫ۥ۫ۡۧۘۙۙۦۘۡۤۚۢۖۦۘ۫ۗ۟ۨۚۦۘۥۖ۫۠۬ۢ۠ۘۤ";
        while (true) {
            switch ((((str2.hashCode() ^ 661) ^ 214) ^ 275) ^ (-1043064203)) {
                case -1593527392:
                    webSocket = this.f;
                    str2 = "ۗۜۤۘۗ۠ۡ۬ۜۜۜۘۢۤۡۤۙۙ۠ۚ۠ۢۥۜۘۧۥۡۘۤۖۘۖۡ۠ۥۥ۠";
                    break;
                case -575857137:
                    return;
                case -285115698:
                    str2 = "ۨۜۧۡۜۥۘۥۙ۠ۥۧۦۘۙ۫ۖۡۜۘۜۤ۠ۥ۠۫ۙۥۖۧۡۡۘۛۢۖۘ۠۬۬ۧۢۘۘۥۜۜۦ۫ۤ۠ۛۡ";
                    break;
                case 521432768:
                    String str3 = "۟۫ۗۖ۠ۜ۠ۗۖۖۨۖ۬۬ۦۘ۫ۧۥۤۦۛۛۘۘۡۘۛۘۨۘ۠۠ۖۘۙۧۙۛۛۨۤۚۜۜۗۦۘۢۜۤۧۖۙۖۛ";
                    while (true) {
                        switch (str3.hashCode() ^ 413843701) {
                            case -1907389366:
                                str2 = "ۚۘۛۢۜۛ۠۬ۢ۬۫ۡۥۨۦۘۤۡۖۙۡۥۘۨۗ۟ۨ۟ۦ۠۠ۚ";
                                continue;
                            case -1555961035:
                                str2 = "ۙۚۢ۫۟ۘۘۦۨۜۘۚ۟۠ۘۘۚۘۥۜۚۛ۬۠ۡۘۥۨۛ۟ۡۖۜۘۦۘۤۥۗۙۦۧۘ۬ۦۘۘۢۙۤۧۘۡۘۨۧۨۘۛ۠ۜۘ";
                                continue;
                            case -590155541:
                                str3 = "ۜۛۨۙۘۦۢ۬۟ۗۨۡ۟ۛۦۗۧ۠ۜۨۥۖۖۦۘ۬۬ۦۘۚۨۥۘ";
                                break;
                            case 1933916125:
                                String str4 = "ۤۦۖۘۖۨۢۖ۠ۨۘۛ۬ۨ۠ۥۙ۟ۚۘ۬۟ۙ۫ۤۨۘۗۤۜ۟ۖۡۘ۬ۥۛۦۛ";
                                while (true) {
                                    switch (str4.hashCode() ^ 1397594099) {
                                        case -2001366164:
                                            str3 = "۠ۧۜۘۚۚ۟۫ۙۗۖۘ۬ۨۖۗۦۨۘۖ۠۠۫ۛۢۦۛ۠ۚۖۡ";
                                            break;
                                        case -1757673591:
                                            str4 = "۠ۜۜۚۜۦۘۘۡۧۘۙۚۦۡۧۖ۬ۢۘۢۢۙۦۧۡۢۗ۬ۧۗۨۘۨ۬ۛۖۛۨۗۜۚ۠ۢ۟۠ۖۦۗۖۥ";
                                        case -1547010404:
                                            str4 = webSocket != null ? "ۙۗ۠ۙ۠ۙۙۚ۬۟ۨۦۘۨۧ۫ۖۘۥۘۦۢۖۤۨۚۢۙۡۘۛۧۦۨۧۖۘۧۤ۠ۘ۫ۛۗۖۤۦۦۜۘۧۙۦۘ" : "۟۟ۖۙۦۧۘۗۨۗۧۘۙۥۢۨۥۨۘۙ۠ۡۘۘۤۥۘۡۗۘۙ۫ۨۨۗ۬ۖ۟ۧۚۗ۟ۜۘۘ۫ۤۨۘۤ۬ۖۘۦۥ۬ۚۥۘ";
                                        case -161294366:
                                            str3 = "ۥۢۨۘۖۡۦ۫ۗۨۡ۫۫۟۬ۢ۫ۜۘۤۘۧۘۡۛۦۜۥۨۦۢۡۤۡۨۘ۫ۡ۠ۜۜۢۖ۠ۜۘۤۢۘۙ۫ۢۤۖۘۧۥۙ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case 1445650889:
                    webSocket.send(str);
                    str2 = "ۙۚۢ۫۟ۘۘۦۨۜۘۚ۟۠ۘۘۚۘۥۜۚۛ۬۠ۡۘۥۨۛ۟ۡۖۜۘۦۘۤۥۗۙۦۧۘ۬ۦۘۘۢۙۤۧۘۡۘۨۧۨۘۛ۠ۜۘ";
                    break;
                case 1692433209:
                    str2 = "ۦ۫ۡۘۘۖۥۘۛۨۘۧۗۙۜۛۖۗۜۨۗ۫ۦۗۗۦۘۘۙۜۜۢۥۘۚۢۢۙۚ۟";
                    break;
            }
        }
    }
}
