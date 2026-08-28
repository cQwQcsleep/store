package core.pro.android.notify;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkCapabilities;
import android.net.NetworkInfo;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import gTBLD.dev.XSSTG.free.Utils;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Map;
import java.util.Set;

/* loaded from: /workspace/unpacked/classes2.dex */
public final /* synthetic */ class w1 implements Runnable {
    public final int a;
    public final Object b;

    public /* synthetic */ w1(int i, Object obj) {
        this.a = i;
        this.b = obj;
    }

    /* JADX WARN: Removed duplicated region for block: B:512:0x02c8 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:513:0x0339 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:514:0x0357 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:518:0x02bf A[SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void run() throws Throwable {
        int i;
        boolean zHasCapability;
        boolean z;
        HttpURLConnection httpURLConnection;
        HttpURLConnection httpURLConnectionI;
        String str;
        Object obj = this.b;
        switch (this.a) {
            case 0:
                Set<String> set = k2.closedPopupIds;
                ((ScrollView) obj).fullScroll(130);
                return;
            case 1:
                Set<String> set2 = k2.closedPopupIds;
                Activity activity = (Activity) obj;
                activity.finishAffinity();
                activity.getWindow().getDecorView().postDelayed(new a(8), 300L);
                return;
            case 2:
                try {
                    Activity activityECt8jHZ4 = Utils.ECt8jHZ4();
                    Map.Entry entry = (Map.Entry) obj;
                    String str2 = "ۡۙۙۨۗۙۖۜۤۢۘۢۦۘۛۖ۠ۥۖۖۘ۠ۢۢۡۘۜۨۖ۫ۥۛ۟ۚۚۚۗۚۢ۬ۡۛۜۢ۬ۗۨۥ۠ۘۢۦ";
                    while (true) {
                        switch (str2.hashCode() ^ (-634208025)) {
                            case -359491859:
                                str2 = "۬ۗۜۘۜۦۘۤ۠ۘ۟ۘۤ۟۬ۛۧۜۢ۬۟۟ۡۧ۫ۡ۬ۙۡۙۧۖ۬ۦۧ۫";
                                continue;
                            case -135027226:
                                break;
                            case -120797118:
                                String str3 = "ۤۗۧۤۢۡۤ۟ۙ۠ۛۜ۫ۛۗۖ۠ۢۗۧۛۢ۠ۛ۠ۡۡۘۙۤۦۜۖۛۧۜۗۘۡۜۨۖ۫ۥ۠ۥۘۜ۫ۨۘۥۘۜۘ۟۟ۨ";
                                while (true) {
                                    switch (str3.hashCode() ^ 425321733) {
                                        case -1921116977:
                                            String str4 = "ۖۢۨۘۖۘ۟ۖۗ۟ۧۘۧۨۧۘۨ۫ۖۘۛۙۙۚۡۦ۠ۧۖۘ۠۫ۙ۟ۧۙۢۤۗۥ۠۟ۡۖۨۚ۫ۡۘۢۘۦۜۦ۬ۗۨ۫";
                                            while (true) {
                                                switch (str4.hashCode() ^ 417127383) {
                                                    case -2030939051:
                                                        break;
                                                    case -1526606483:
                                                        str4 = "ۡۨ۠۬۫ۖۘۢۚۦۘۗۛۨۘۚۛۢۜۖۥۛ۫۬ۖۢۢۚۨۤۨۗۘۘ";
                                                        break;
                                                    case 139512222:
                                                        new AlertDialog.Builder(activityECt8jHZ4).setTitle(l2.decrypt("lQ/ZJ1rA\n", "c4BJwP56QcM=\n")).setMessage((CharSequence) entry.getValue()).setPositiveButton(l2.decrypt("7lA0RjYIXCWbPB8n\n", "CNiloamttaQ=\n"), (DialogInterface.OnClickListener) null).setCancelable(true).show();
                                                        return;
                                                    case 186089670:
                                                        String str5 = "ۙۙۥۙۡۙۚۖۛ۬ۗۚ۟ۨۛۖ۟ۜۘۧۨۗۥۢۙۘۗۜۘۘ۟ۡ";
                                                        while (true) {
                                                            switch (str5.hashCode() ^ (-309083411)) {
                                                                case -622907309:
                                                                    if (!activityECt8jHZ4.isDestroyed()) {
                                                                        str5 = "ۙۨۨۗۙۦۘۚ۫ۤۤۜۘۘ۬ۡ۟ۗۛۡۘۤۨۜۘۜۜۙۙ۬ۥۘۢۡۛ۠ۖۥۘۘ۠ۖۗۤۦۘ۫ۜۡۘ";
                                                                        break;
                                                                    } else {
                                                                        str5 = "۟۠ۙۘۜۧۘۙۗ۟۫ۘۘۙۘ۬ۗۘۘۦۨۛۥۖۖۘۦۗۜۘۙۧۖۜۢۨۘۛۨۖۘۖۤۥۘۚ۫ۤۜۙۙ۟ۗۥ";
                                                                        break;
                                                                    }
                                                                case 380743139:
                                                                    str4 = "ۘۛۡۙ۠ۡۨۥ۫ۗۧۥۘۘ۬ۘۘۦۡۤۗ۫ۙۚۥۘۘۨۜۙۧۖ۟ۗۚۘۛۜۘۜۥۖۡۧۙۗۡۘ۬ۚۛ";
                                                                    continue;
                                                                case 1136749886:
                                                                    str4 = "۟ۚۚۘۥۡۨۤۙۤۡۘۚۦۖۘۢۘۛۚ۬ۗ۫۫ۦۛ۬ۦۘۖۜۙۛ۬ۖۘ۟۠ۘۘۦۘۘۘۘۗۨ۬ۧۚ۫۫ۡ";
                                                                    continue;
                                                                case 1303052965:
                                                                    str5 = "ۦۢۜۥۨۙۦ۬ۖۡ۠۬ۗۙۡۤۛۨۘ۟۟ۢۛۤۥۜ۫ۨۘۛۚۡۥۜۦۡۢۧۧۘۘۥۢۥۤۢۗۤۧۡ";
                                                                    break;
                                                            }
                                                        }
                                                        break;
                                                }
                                            }
                                            break;
                                        case 1873300277:
                                            break;
                                        case 1972018196:
                                            str3 = "ۛ۬۠ۦۤۥۙۦۥۘۚۧۜۘۗ۠۫ۡۘۜ۫۟۠ۙۚۥۚۨ۬ۛۖ۠ۚۡۢۤۨۡۡۨۘۖۤۨۘ";
                                            break;
                                        case 2108895190:
                                            String str6 = "ۡۗۗۗۚ۬ۜۨۜۗ۫ۦۨۛۜۘ۫ۨۧۘۖ۟ۦۤۥۘۘ۬ۙ۫ۦۥۙ";
                                            while (true) {
                                                switch (str6.hashCode() ^ (-1522152771)) {
                                                    case -1245984071:
                                                        str3 = "ۖۗۗ۬ۧۖۜۨ۬ۨۚۤۖۨۗۘۧۢۦۚ۟۠ۖۨ۫ۙۢۖۧۘۖۚ۠ۜۛۦۘۢۖۜۘ۠ۗۜۘ۠ۖۥۘ۫ۦۜۘ";
                                                        continue;
                                                    case -356614066:
                                                        if (!activityECt8jHZ4.isFinishing()) {
                                                            str6 = "ۗۤۦۨ۬ۤۘۤۦۘۚۛۡۨۧۚ۠ۙۡۜۘۥۜ۬ۦۗۤۖۖۛۦ۬ۘۖۖ";
                                                            break;
                                                        } else {
                                                            str6 = "ۛۤۤۛۥۜۦ۠ۘۘۧۘ۠۟ۤۜۦ۬ۡۘۜ۬۠ۜۡۖۘۗۡ۠ۖۨۛۧ۠۬ۚ۟۠";
                                                            break;
                                                        }
                                                    case 290377476:
                                                        str6 = "ۤۡۦ۟ۙۥۛۥۢ۠ۧۘۘۡۢ۠ۖۚ۠۠ۢۘۘۢۡۨۧۗۤۦۤ۫ۦۘۘۡ۟ۘ۠ۖۖۚۤ۟ۧۢۗۜۡۖ۠۟ۡۚ۟ۧ";
                                                        break;
                                                    case 621743233:
                                                        str3 = "ۦۜۨۜۤۤۙۥۢۜ۠ۙۘ۠۠۬ۤۨۘۘۡۘۙۥ۠۟ۚۨۘ۠ۡۖۘۗۡۜۘ";
                                                        continue;
                                                }
                                            }
                                            break;
                                    }
                                }
                                break;
                            case 892555468:
                                String str7 = "ۚ۫ۙۢۚۦۘۛۧ۬۫ۦۙۗۚۖ۟۟ۚۡۤۨۧۢۤۗۘۘۘ۠۬ۜۘۤۗۦۗۖۤۖۦۘۘۨ۬ۤۗۥۡۘ۟ۘۦ";
                                while (true) {
                                    switch (str7.hashCode() ^ (-686961768)) {
                                        case -2130832010:
                                            if (activityECt8jHZ4 == null) {
                                                str7 = "ۨۚۢۥ۫ۢۗۥۢۜۘۙۛۦۧۡۢۗۤ۠ۙۘۘۙۘۥۘۦۗۛۘۢۨۘۙۙۘ";
                                                break;
                                            } else {
                                                str7 = "ۦۡۡۨ۫ۥۧۗۙۧۜ۠ۚۧۨۡۙۗۚۙ۠ۗۖۥۚۦۘۙۧ۬ۘ۟ۛۧۘۥۘ۟ۗ۟ۦ۬۬ۥۦۦۘۘۙۢۖۘۥۥۤۤ";
                                                break;
                                            }
                                        case -819479288:
                                            str2 = "۬ۛۦۥۡۘۨۘۦۘۦۢۙ۠ۡۢۗۤۢ۠ۗۚۗۧۜۖۖۢۦۛۘۘۢۧۡۘۛۗۦ";
                                            continue;
                                            continue;
                                        case 1737105580:
                                            str2 = "ۥ۫ۥۚۖۦۘۤۗۙۛۘۡۡۙۜۛۨۗۘۡۨۙۨۜۘۢ۬ۜۛۤۘۘ۟ۧ۠ۙۤ";
                                            continue;
                                        case 2052107722:
                                            str7 = "۬ۗۨۖ۟ۡۛۜۧۤۥۘۦۚۦۘۚۗۦۦۚۧۙۡۜۘۙۢ۬۠ۖۨۨۖۦۖۗۚ";
                                            break;
                                    }
                                }
                                break;
                            default:
                                continue;
                        }
                    }
                    System.out.println(l2.decrypt("/SRUJXi/dUOco5jB61mu3Sjvz/CCMJaaWvS1qpZo5p4Goa/c6XK71QDd\n", "vEcgTA7WATo=\n") + ((String) entry.getValue()));
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case 3:
                Activity activity2 = Utils.a;
                k3 k3Var = (k3) obj;
                TextView textView = k3Var.e;
                String str8 = "ۤۗۦ۬ۘۙۦۧۗۢۗۘۥۧۘ۫ۜۙۜۛۖ۟ۦۨ۟۠ۨۛۘۘ۟ۖ۬۬ۦ۠ۘۦۨۥۡۤ۟ۗۡۙۡۜۘ";
                while (true) {
                    switch (str8.hashCode() ^ (-1811851426)) {
                        case -1355617463:
                            String str9 = "ۗ۠۠ۖ۟ۘۘ۠۠ۥۘۥۜۧۘۜۧۦۚۥۜۥۦۙۢۡۘۧۥۥۢۛۢۦۡۘۚۥ۬۬ۘۧۘۙۤ۟ۙۦۜۚۥۢ۠ۦۡۜۢۖ";
                            while (true) {
                                switch (str9.hashCode() ^ (-1617737541)) {
                                    case -2015378809:
                                        if (!k3Var.d) {
                                            str9 = "ۜ۫ۡۘۡۜۦۘۜۗ۠ۧۦۜۧۗۚۛۦ۫ۧ۬ۖۗۘۧۤ۬ۘۘ۠ۖۨۘۖۧۗۛۖۡۘ۟ۧۡۘۤۢۦۤ۫ۜۘۧ۟ۥ";
                                            break;
                                        } else {
                                            str9 = "۠ۦ۠۟ۧۥۨ۫ۜ۟ۧۛۗۧۡۘ۬ۘۥۘۙ۬ۙۚ۠ۡۥۧۜ۠ۗۚۜۧۧۡۢۚ۟ۤۤۨ۫ۦۘ";
                                            break;
                                        }
                                    case -99286739:
                                        str9 = "ۖۨ۟۟ۨۢۚۥۜۘۜۗۦۨۡۙۙۢۡۘ۠۠ۗ۬ۛۚ۠ۘۜۘۥ۬ۜۧۥۘۨۚۢ";
                                        break;
                                    case 258642304:
                                        str8 = "ۦۦۧۖۢ۫ۧۨ۫ۖۡۗۡۛۢۥۘۜۜۖۘۘ۬ۖۘۘۡۧۘۖ۬ۧۛ۠۬ۛۧۖۥۦۛۨۖۗ۫ۤ۫۫ۨ";
                                        continue;
                                        continue;
                                    case 1472834702:
                                        str8 = "ۘۥۛۢۙۨۧۤۚۙۘۦۘ۫۠ۥۡۧۦ۠ۗۙۧ۫۠ۤۘۘۘۨۘۛ۬ۨۧۘۥۢۥۘۧۢۦۘۡۘۦۡ۠ۛۧۘۙ";
                                        continue;
                                }
                            }
                            break;
                        case -1220426003:
                            i = -16711936;
                            break;
                        case 1528345857:
                            str8 = "۠ۘۙۚۧۡۖۧۚ۫ۤۗۢۚۚۡۨۢۗۘ۬ۨۚ۟ۢ۫ۦۦۚۖ۬ۙۜۘۗۧۦۘ";
                            continue;
                        case 1592159249:
                            i = -7829368;
                            break;
                    }
                }
                textView.setTextColor(i);
                return;
            default:
                final Context context = (Context) obj;
                Activity activity3 = Utils.a;
                try {
                    ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(l2.decrypt("TPViDjv5sxFZ83gZ\n", "L5oMYF6ax3g=\n"));
                    String str10 = "ۚۘ۠ۨۚ۠ۚ۫ۙۧۜۧۘۡۤۦۘۦۧۖۘ۟ۤ۬۬ۙۡ۫ۘۧۘۡۧۦۘ۠ۛۧ۬۬ۘ۠ۨۡۘ۟۠۟ۧۨۡۘ۫ۜۢ";
                    while (true) {
                        switch (str10.hashCode() ^ (-1559221058)) {
                            case 23931783:
                                break;
                            case 643648925:
                                String str11 = "ۦۗۘۘۘۜۤۦۦۧ۟۟ۦۘ۠ۜۦۢۤۛ۠۟ۨۘۨۙۧۗۧۥۗ۟";
                                while (true) {
                                    switch (str11.hashCode() ^ 1481227533) {
                                        case -1301312862:
                                            if (connectivityManager != null) {
                                                str11 = "۠۬ۦ۟ۖۧۘۘۘۛ۠ۤۤ۬ۡۘۚۜۘۦۨۢ۟ۛۥۘۤۘۡ۠ۤۦۘ۫ۤۛ۠ۡ۠ۨۙۛۖ۬ۜۘۚۨۛۨۨۡۘ";
                                                break;
                                            } else {
                                                str11 = "ۦۤ۠۫۠ۡۘۖۥۖۖۘۘۘ۬ۚ۬ۘۚۥۘۥۤۡۥ۬ۙ۬ۦ۟ۚۦۘۖۗ۠ۗۜۘۜۡۖۘۨۗۡۘۦۨۦۨۡۧۘ";
                                                break;
                                            }
                                        case -996014850:
                                            str10 = "ۤۢۧۖۢۦۘۤۤۡۘۘۙۜۘۧۤۚۜۢۦۦ۟ۨۘ۫ۛۦۖ۠ۥ۫ۘۧۚ۟ۗ۟۠ۛ";
                                            continue;
                                        case -93226925:
                                            str11 = "ۙ۬ۦۘۗ۫ۡۨۚۜۡۜ۠ۧۤۜۜۨۘۘۛۜۖۘ۟۟ۚۙ۟ۖۨۗ۠ۨۖۢ۟ۛۤۜۥۘۘۛۦۢ۟ۢ۫ۥۤۙ۫ۜۡۘۛۜۨ";
                                            break;
                                        case -18660434:
                                            str10 = "۫ۦۥۨۧۤۡۨۖۚۦۜۗۧۤۙۨۜۘۛۚۨۘۦۛۦۘۧۦۧۘۤۥۙۘۧۗۚۢۜۘۙۙۜۛ۫ۦ۫ۛۘۛۥۚۚۘ۟۠ۚۦۘ";
                                            continue;
                                    }
                                }
                                break;
                            case 1331586728:
                                String str12 = "ۢۧۗ۫۟ۨۘ۟ۖۨۡۜۘۜۖۖۖۚ۬ۧ۬ۘۧۘۦۨۙۡۘۖۡۘۘۢ۫ۡۖۖۥۘۛۥۧۘۨۚۙ";
                                while (true) {
                                    switch (str12.hashCode() ^ (-1019224347)) {
                                        case -1023650358:
                                            str12 = "ۡۙۧ۫ۤۡۘۙۨۦۘۛۗۖۘ۟۠ۤۧۛ۟ۛ۠ۜۛۙۘۡۥ۟۬ۖۤۜۧۘۘ۫ۥ";
                                        case -770258335:
                                            String str13 = "۟ۜۡۘۤۗۤۖ۫۟۠ۡۘۛ۠ۥۦۡۚ۫۟ۖۘ۟ۢ۬ۤۙۥ۬۟ۥۤۘۜۥۜۙۡ۟ۦۖۥۚ";
                                            while (true) {
                                                switch (str13.hashCode() ^ 1280599962) {
                                                    case -1359806129:
                                                        str12 = "ۡ۟ۙۙۙ۫ۙۗۘۘۥ۫۫ۖۤۙۨ۫ۢ۠۠ۦۗۡۛۦۦۜۖۘۦۘۡ۬۫۫ۢ۫ۨۚ۠ۡۤۢۜ۫ۦ۫۟ۛ";
                                                        continue;
                                                    case -1287228244:
                                                        str12 = "ۙ۠ۨۥۛۙ۟ۖۚۧۤ۟ۖۙۥۧۦۙۛۖۥۨۤ۬ۜ۫ۤۨۥۘۘۙۥۤۘۤ۬ۘۛۦۘۧۢۘۘۧ۠ۡۘۧۤ۬ۘ۫۟۫ۤۢ";
                                                        continue;
                                                    case -597327469:
                                                        if (Build.VERSION.SDK_INT < 23) {
                                                            str13 = "ۥ۬ۚۥۚۡۘۗۜۤۡ۟ۖ۟۟۠ۘۡۖۨۦ۠ۗۙ۠ۧۧۦۢ۬ۚۜۗۤۨ۟۫";
                                                            break;
                                                        } else {
                                                            str13 = "ۗۘۨۘۚۡۜۘۤۗ۫ۤۜ۫ۙۖۦۨۡۜۘۥۡۥۢۥۖۘۦ۫۠ۛ۫۠ۨۖۦۥۗۧ۟ۥۜ۫ۙۛ۟۟۫ۛۘۘۧۘۖۜۡ۬";
                                                            break;
                                                        }
                                                    case 786034944:
                                                        str13 = "۫۬ۜۘۜۨۙ۠۫ۢۤۗۥۢۗۚۗۤۙۡۨۥۘ۫ۘۜ۫ۨۧۘۜۧۧۦۛ۟ۡۘۘۙۦۜۘۘۘOۚ۬۫";
                                                        break;
                                                }
                                            }
                                            break;
                                        case 1007205672:
                                            Network activeNetwork = connectivityManager.getActiveNetwork();
                                            String str14 = "ۙۜ۬۟ۨۧۦۙۥۘۥۚۖۘۗۨۘۡۘۤۙۖۘۘۙۨۚ۬ۖۥۚۘۖۘ۬۟ۢۧۦۦۡ۫ۦۘۨۧۜ";
                                            while (true) {
                                                switch (str14.hashCode() ^ 411951103) {
                                                    case -1496649765:
                                                        String str15 = "۠ۚۜۘۡۤۘۘۘۨۧۘۙۙۖۤۙۡۚۙۗۖۛۛۛ۠ۗۢۚۡ۫ۡۙۧۜۛۘۘۜۖۧۧ۟ۜۘ";
                                                        while (true) {
                                                            switch (str15.hashCode() ^ 1028121420) {
                                                                case -940860661:
                                                                    str14 = "۫ۙۛۛۖۜۖۦۙ۫۫ۢۧۘۧۖ۟ۡۘۜۚۦۘۧۥۨۘۥۤۡۘۛۧۨۗۙۖۘۚۙۤۡ۠ۖۚۤۘۘ۬ۖۗۖۜ۠۫ۥ۫ۛۛۚ";
                                                                    continue;
                                                                case -936333071:
                                                                    str15 = "ۜۙۜۗ۠ۥ۟ۨۖۨ۫ۧۖ۫ۦۡۤ۠ۤۤۨۦ۬۬۬ۖۘۘۡۦۘۚ۫ۨۤۤ۠ۦۛۜۦۦۖۘۦۘۖ۠ۘ";
                                                                    break;
                                                                case 597398421:
                                                                    if (activeNetwork != null) {
                                                                        str15 = "ۗۥۖۘ۟ۙۤۢۚۡۘۢۙ۟۠ۖۨۘۖۙۨ۫ۗۡۘۙۦۡ۫۬ۚ۬ۜۙۧ۟ۥۙۜۡۥۜ۫ۡۜۘ";
                                                                        break;
                                                                    } else {
                                                                        str15 = "ۦۚۥۘ۠ۥ۬ۦۛۙ۬ۜۖۘۛۤۦۘ۠ۘۖۘ۫۬ۥۘۡۚۥ۫ۨۥۘۨۛۥۛۙ۠ۛۖ۬ۘ۬ۘۘۡۤۖۘ";
                                                                        break;
                                                                    }
                                                                case 1003025623:
                                                                    str14 = "ۦۛۡۙۢ۟ۢۤۖۧۦۧۙۜۖۘۗۢۡۙۘۥۛۧۗ۟۬ۛۤۙۛۗۖۥۨۡۘۡۢۤ۬۫ۡۘۥ۫ۖۥۘۦ";
                                                                    continue;
                                                            }
                                                        }
                                                        break;
                                                    case 803071532:
                                                        NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(activeNetwork);
                                                        String str16 = "ۗۙۜۛۥۘۖۢۜۘ۫۬ۥۘ۬ۘۘ۬۬ۡۘۙۜۡۗ۬ۜۨۦۜۤۖۘ۟ۧۡۘۜۦۘۦ۫ۖۘ۠ۘۛ";
                                                        while (true) {
                                                            switch (str16.hashCode() ^ (-582073136)) {
                                                                case -1851060607:
                                                                    break;
                                                                case -1812241183:
                                                                    String str17 = "ۥۖۨۚۘۧۜۤۤ۟ۗۨۘ۠ۘۙۜۤۦۚۦۥۧۙۡۘ۬ۘۙ۬ۤ۫ۜۥۧۛۜۙۢۧۡۗۚۘۘۦ۟ۢۜ۠ۦ";
                                                                    while (true) {
                                                                        switch (str17.hashCode() ^ 1804546619) {
                                                                            case -1750788270:
                                                                                str17 = "۫ۖۖۘۛۙۜۘۗۚۜۘۖ۫ۚ۫۫ۥۡۙۤۚۡۘۦ۬ۜۢ۠ۘ۠ۘۥۘۗۖۧۘۗۖ۟ۛ۬ۜۘۜۧۧۖۢۛۢۦۘۖۨۥۘۖۖۦ";
                                                                                break;
                                                                            case -162517250:
                                                                                if (networkCapabilities != null) {
                                                                                    str17 = "ۤۗ۠۬ۧۖۚۥۘۡۤۦۘ۬ۡۧۧۡۖۘۜۡ۠۟ۙۖۘۖۖۗۜۖۡ";
                                                                                    break;
                                                                                } else {
                                                                                    str17 = "ۖۘ۫ۡۥ۟ۤۚۤۤ۟ۗۜۦ۠ۚ۬ۘۗۛۡۙۧۜۘۤۡ۠ۖۨۢ۫ۙۗۤۨۘۘ";
                                                                                    break;
                                                                                }
                                                                            case 302598454:
                                                                                str16 = "ۛۚۦ۫۟ۧۛۥۡۘۗۗۡۘۘۘۨۘۖ۫۟۫ۗۖۘۚۚۖ۠ۨۚۛۧۗۧۤۡۢۢۢۘۘۡ۟ۨۘۨ۠۫۬ۨۧۙۧۙۢۥۚ";
                                                                                continue;
                                                                            case 928488440:
                                                                                str16 = "ۗۙۡۘۤۢۖۘۦۢۖۚۤۢۗۥۦۙۥۚۤ۫ۥۘۖۦۨۛۢۡۘۤۖۢ۠ۜ۟ۛۙۦۘۧۖۧۘۨ۟ۜ۬ۡۥ۬ۘۘۘ";
                                                                                continue;
                                                                        }
                                                                    }
                                                                    break;
                                                                case 1630337465:
                                                                    str16 = "ۗۙ۬ۤ۫ۖۘۚۧۘۘۢ۫ۦۛ۬ۛۡۘۨۙۧۗۡۡ۬ۧۦۘۗ۬ۖۡۛۦۘۨۦۘۙۧۘۨۖ۬ۛ۫ۗ۫۬ۦ";
                                                                case 1897967851:
                                                                    zHasCapability = networkCapabilities.hasCapability(12);
                                                                    break;
                                                            }
                                                        }
                                                        break;
                                                    case 1040795163:
                                                        break;
                                                    case 1974338314:
                                                        str14 = "ۤۧ۬۫ۜۘۘۦ۬ۜۥۧۚۖۗۡ۫۫ۙۦۘۘۘ۠۫ۚۨ۠ۚۨۛ۬ۥ۟ۥۨۖۘ۟۬ۜۜۡۘۘۡ۫ۥۧۗۙ";
                                                }
                                            }
                                            break;
                                        case 1432656244:
                                            NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
                                            String str18 = "۫ۗۡۤ۬۫ۘۥۙۢ۟ۡۜۨ۬ۙۨۢ۫ۦۡۗ۠ۛ۟ۥۡۦۡۢۜۢ۫ۢۖۘۙۜۚ۠ۦۦۙۚۨۗۛۥۘ۟۠ۜۢ۬ۖ";
                                            while (true) {
                                                switch (str18.hashCode() ^ (-80913342)) {
                                                    case -1706988348:
                                                        str18 = "ۜۙۦۘۥۛۤۙۜۗۛۨ۟۫ۦۢۗۙۜۘۦ۠ۜۘۙۙۘۘۡۨۜۘۙۦۙ";
                                                    case -1161005516:
                                                        String str19 = "ۗۤۧۤۡۘۘۦۡۜۘۘۜۗۙۥۗۥۨۛ۠۟ۦۢۘ۠ۗۥۚ۫ۢۡۘۡۜۡۖۡۨۘۤ۟۠ۖۤ";
                                                        while (true) {
                                                            switch (str19.hashCode() ^ (-2085883917)) {
                                                                case -963605062:
                                                                    str18 = "ۛۨ۟۬۠ۖۘ۫۬۬ۧۤ۟ۖۨۤ۠ۥۦۘۗۖۜۘۙۨ۬ۤۧۖۘۧۤۛ۬ۨ۟ۡ۫ۥۘ۫ۨۡۘۖ۫ۨۘۚۛ۟۠ۥۧۘۛ۬ۚۜۤۗ";
                                                                    continue;
                                                                case 406189701:
                                                                    str18 = "۬ۧۨ۫۠ۜ۟ۦۘۘ۬ۥۢۘۡۥۥۡۥۘۚ۟ۦۘۜ۬ۢ۬ۛۚ۫ۖۘۖۚۘۘۤۙۙ";
                                                                    continue;
                                                                case 1145881136:
                                                                    str19 = "ۨۛۧۦ۬ۥۛۤۡ۠ۡ۫ۥۡ۫ۥ۠ۖۢۘۦۘۡۘۨۡۤ۫ۤۢۘۜۘۘۘۤۘۘ";
                                                                    break;
                                                                case 2033811012:
                                                                    if (activeNetworkInfo == null) {
                                                                        str19 = "ۨۥۘۘۢۜۙۚۜۦۘۧۛۨۥۨ۟ۢۗۛۗۗۡۘ۫ۡۘۖ۠ۜۘۡۥۤۜۧۥۘ۫ۥۜ۫ۘ۠ۗ۟ۨۘۚۙۦ۠ۧۛۢۨۧۘۜۧۜۘ";
                                                                        break;
                                                                    } else {
                                                                        str19 = "ۧۖۦۘۙۡۤۚۨۚۢ۫ۛۙۧۡۘۥۜۖۚۚۘۘ۟۟ۚ۟ۚۤ۫ۖۨۘ";
                                                                        break;
                                                                    }
                                                            }
                                                        }
                                                        break;
                                                    case -300819808:
                                                        break;
                                                    case 243046409:
                                                        boolean zIsConnected = activeNetworkInfo.isConnected();
                                                        String str20 = "۬۟ۛۥ۠ۚۛ۬ۡۘۡۛۖۘۦ۫ۚۡۦۤۡۘۚۦ۫ۧۨۛۜۘۧۜۖۘ";
                                                        while (true) {
                                                            switch (str20.hashCode() ^ 1928792986) {
                                                                case -1107424078:
                                                                    break;
                                                                case 407785015:
                                                                    str20 = "ۘۢۛۗۜۦۘۤۛۚ۟ۡۛۘۘ۫۠ۥ۬ۚۤۦۡ۟ۚۜۡۘۚۢ۬";
                                                                case 884748624:
                                                                    String str21 = "۫ۢۡۘۙۤۥۘ۠ۥۘۘۨۡۦۢ۫ۥۙۡۘۥ۟۠ۗ۬ۡۢۙۢۗۜ۟۫ۙۧ۫ۢۖۘۨۚۨۨۙۤۜۜۘۘۖ۫ۤۙۗۥۨۜۛ";
                                                                    while (true) {
                                                                        switch (str21.hashCode() ^ (-2066801153)) {
                                                                            case -1128354559:
                                                                                str20 = "ۥۧۛۖ۠ۥۡۘۘۦۙۜۘۥۥۚۦۧ۬ۧ۟ۖۘۙۦۦۥۜۛۜۘۦ۠ۘ۫";
                                                                                continue;
                                                                            case -207551624:
                                                                                str20 = "ۛۤۖۘۤۡۖ۫ۧۜۘ۠۟ۡۤۧۤ۠ۗۦۘ۫۬ۦۜۙۦۤ۟ۘۘۧۚۨ";
                                                                                continue;
                                                                            case 852442256:
                                                                                str21 = "ۦۡۨۤ۫ۚۧۙۜۘۜۦۘۢۜ۠ۜۖۡۗۥۚۤۖۧۘۧۜۦ۟۠";
                                                                                break;
                                                                            case 1580633818:
                                                                                if (!zIsConnected) {
                                                                                    str21 = "ۦ۟ۖۥۜ۟ۙ۠ۦ۠ۦۧۦۘۗ۠ۖۘۢ۠ۢۜ۠ۖۘۢۤۘۘۥۖۚ";
                                                                                    break;
                                                                                } else {
                                                                                    str21 = "ۤ۫ۨ۠ۙۘۘۤۙ۬ۖ۬ۦ۠ۢۦۘۖ۟ۥۖۥۨۘۧۛۨ۟ۙۥۢۛۡۘ۫ۦ۬۟ۖۥۘ۟ۚۨۘ۫ۖۥ";
                                                                                    break;
                                                                                }
                                                                        }
                                                                    }
                                                                    break;
                                                                case 1150698929:
                                                                    zHasCapability = true;
                                                                    break;
                                                            }
                                                        }
                                                        break;
                                                }
                                            }
                                            break;
                                    }
                                }
                                break;
                            case 1405125936:
                                str10 = "ۘۖۨۚۚۚۗۘۡۘ۬ۡ۠ۧۛۛۢۜ۫ۢۙ۠ۗۘۘۘۘۛۚۢۤۜۦۥۡۘۧ۠ۨ";
                        }
                    }
                } catch (Exception e2) {
                }
                zHasCapability = false;
                String str22 = "ۗ۟ۡۘۚۡۜۘۥۨ۠ۛۖۦۘ۬ۤۖۙۗۧۛ۬ۥۘۤۡۨۙ۠ۗ۬ۛۖۡۚۧۧۘۢۢۘۜۘ۬ۧۡۡۤۜۘۜۗۨ";
                while (true) {
                    switch (str22.hashCode() ^ (-1746435239)) {
                        case -1006586104:
                            break;
                        case -329323600:
                            str22 = "ۖۢۘۗ۫ۦۗۧۦۘۤ۬ۛۜۜۡۘۡ۫ۛۡۖۦۚ۬۟ۡۢۜۘۡۢۘۡۧۡ۠ۥ۬ۖۛۖۛۜۦ";
                        case 177979601:
                            String str23 = "۫ۦۖۛۗۦۥۗۥ۟ۨۡۘۢ۠ۜۚۛۦۘۚۧ۠ۚ۫ۥۦۗۚۖۦۡۘۧۛ۠۟ۥ۫ۨۦۤۤۧ";
                            while (true) {
                                switch (str23.hashCode() ^ 1021894666) {
                                    case 793720224:
                                        str23 = "ۨۗۦۘ۟ۢۡۘۙۥۚۜۨۛۖۢۥۡۗۡ۟ۚۡۖ۟۟ۦۘۧۛۤۢۧ۠ۚۢۘۚۚۨۘۨۢۥۨۡۘۘۧۧۜۘۜۨۖۢۥ۟";
                                        break;
                                    case 937034846:
                                        str22 = "۠ۛۡۘۗۢۛۢۘۗۗ۟۠ۧۨ۬ۚۘۡۖۘۚۚۥ۠۫ۚۤ۠ۜۡ۠۟ۡۖ۟";
                                        continue;
                                    case 1318215460:
                                        if (!zHasCapability) {
                                            str23 = "ۖۗۗۥۤۧ۟ۤۜۘۦۙۛۖۛۛۢۘۢ۫ۧۗۡۘۤ۟ۨۥ۟ۧ۟۫ۢۡۘۦۦ۬۬۠۫ۦۘۡۘۖۥۢۛۙۛۨ۠۬ۥۢۡ";
                                            break;
                                        } else {
                                            str23 = "ۤۦۢۘۙ۫۟ۗۗۡۡ۬ۧۨۘ۫ۧۤۥۖۥۘۦۛۖۘ۬ۙ۠ۢۥۜۛۦۧۥۚ۠ۜۡۚ۟ۢۦۘۥۦۨ۬ۘ۫";
                                            break;
                                        }
                                    case 1464864231:
                                        str22 = "۟ۢۦۘ۬ۨۦۘۙ۫ۛۡۜۧۘۚۖۥۛۙۨۘ۟ۘۡۘۥۖۢۙۨۦۘۡ۫ۦۨۘۖۢۤ";
                                        continue;
                                }
                            }
                            break;
                        case 1094127990:
                            String strDecrypt = l2.decrypt("1qzTZGp1ejLJr9A6ey48ecv2xHt0\n", "vtinFBlPVR0=\n");
                            String strDecrypt2 = l2.decrypt("Sf8kfuwXtf9W/Ccg7ly0s07m\n", "IYtQDp8tmtA=\n");
                            String strDecrypt3 = l2.decrypt("dq0RitCMqahnrAuAy8P08jC6Cpc=\n", "Htll+qO2hoc=\n");
                            int i2 = 0;
                            while (true) {
                                String str24 = "ۘۗۖۘۨۙۜۘۤۗ۠ۗۙۜۘۙۖۨۘۥ۬۠ۤۡۡۘۧۖۨۘۙۘ۠۫ۢ۬۠ۢۘۡۧۤ";
                                while (true) {
                                    switch (str24.hashCode() ^ 384906849) {
                                        case -1847403105:
                                            String str25 = "ۘۗۥ۫ۡۨۘۢۧۖۗۙۜۘۗۥۧ۠ۦۡۘ۟ۛ۠ۦ۫ۚۢۦ۬۟۟ۧ۠ۤۢۙۖۙ";
                                            while (true) {
                                                switch (str25.hashCode() ^ 1459587000) {
                                                    case -301283591:
                                                        str25 = i2 < 3 ? "ۤۥۤ۬ۤۜۘۘۨۚۤۖۙۥۨۘۙۗۘ۠۟ۨۘۦۢ۬ۨۨۙۤۜۨ" : "ۗۘ۬۟ۨۧۦۗۜۘۥۘ۠۫۬ۛ۫ۧۙۢ۟ۛۘۛۤ۠ۦ۫ۚۥۖۘۙۛ۫ۥۦۖۘ۫ۢۥۘۘۚۥۘۚۥۢۗۥۜۘ";
                                                    case 318881197:
                                                        str24 = "ۖ۬ۡ۠ۡ۟ۘۗ۟ۢۦۧۘۢۘۙۤ۫ۖۙ۠ۛۚ۟۬ۖۗۛۙۘۨۥۧۧۢۥۚۦ۠ۢۙ۫ۢ";
                                                        break;
                                                    case 752822375:
                                                        str25 = "ۗۤۗۡۥۡۘۥۥۨۜۥۤ۫۫ۨۘۛۨۤۛۥۙۖۗۚۖۚۛۛۤۙ";
                                                    case 1138404330:
                                                        str24 = "ۨ۠ۧ۬ۡۤۖۧ۫ۨۖۘۙۗۥۘۡۧۨۘۜ۠ۧۡۧۨۨۚۡ۠ۥۨ";
                                                        break;
                                                }
                                            }
                                            break;
                                        case -836384405:
                                            break;
                                        case 1307463896:
                                            try {
                                                httpURLConnectionI = Utils.i(new URL(new String[]{strDecrypt, strDecrypt2, strDecrypt3}[i2 % 3]));
                                            } catch (Exception e3) {
                                                httpURLConnectionI = null;
                                            } catch (Throwable th) {
                                                th = th;
                                                httpURLConnection = null;
                                            }
                                            try {
                                                httpURLConnectionI.setRequestMethod(l2.decrypt("LhX3Dw==\n", "ZlC2S1ass84=\n"));
                                                httpURLConnectionI.setConnectTimeout(1500);
                                                httpURLConnectionI.setReadTimeout(1500);
                                                httpURLConnectionI.setInstanceFollowRedirects(true);
                                                httpURLConnectionI.setUseCaches(false);
                                                httpURLConnectionI.connect();
                                                int responseCode = httpURLConnectionI.getResponseCode();
                                                String str26 = "ۧۥ۬ۗۛۗۥۤۥۘۘۤۥۧ۬ۖۘۢۘۚۚۡ۬ۜۘۦ۫۫ۘۖۥ";
                                                while (true) {
                                                    switch (str26.hashCode() ^ 794287526) {
                                                        case -1856604744:
                                                            String str27 = "ۤ۫ۢ۫ۥ۠ۖۗۜۖ۫ۜۙۜۜۘۖۜۚۗۜۙ۟ۦۨۘۤۘۘۖۜۤۡۙۖۘۚ۫ۨۢۤۦ۫ۦۘۘ";
                                                            while (true) {
                                                                switch (str27.hashCode() ^ 1175183831) {
                                                                    case -1311771586:
                                                                        String str28 = "۟ۢۜۘۥ۟ۤۗ۬ۥۤۘۘۘۤۚۜۘ۬ۢۡۗ۠۟ۖۢۜۜۡۤۛۘ";
                                                                        while (true) {
                                                                            switch (str28.hashCode() ^ (-791907447)) {
                                                                                case -1792446055:
                                                                                    str28 = "ۡۛۜۡۙۙۤۘۚۧۘۧۖ۫ۧۤۚۦۡۗۨۘۦۡ۫۬۬ۖۥ۬ۖۘ";
                                                                                case -1035394921:
                                                                                    str27 = "ۢۙۖۘۖۗۡۘۖۛۢ۠۟ۗۛۨۖۚۨۜۘۧۦۘ۠۫ۙۦ۠ۡ۠ۙۡۘۜۦۛۥۥۜۧۜۢ۠ۤۜۘۧ۬ۦۘ۫۫ۖۘۢۗۥۘ۫۠ۥ";
                                                                                    break;
                                                                                case -44172437:
                                                                                    str28 = responseCode < 400 ? "ۘۦ۬ۙۨ۫ۡۦۚۚۚۖۘۗۛۚۨۙۜۗۛۨۘۙۨۤ۬ۘۗۚۢۡۗۘۢۦۖ" : "ۤۗۡۘ۠۬ۨۘۗۤۥۢۜۖۘۨ۬ۖۥۢۖۘ۬ۜۙۙ۫ۥۘۘۛۗۥۨۥۘ۬۟۠۬۫۠ۢۥۘۛۘۘۥۗۥۘۙۗۚ";
                                                                                case 376885515:
                                                                                    str27 = "ۖۢۘۗ۬ۗۖ۟ۦۘ۫ۖۧۘۦۥۚۗ۟ۖۘۜۚۡۜۜ۠ۚۙۖۘۛ۫ۦۘۧ۠۬ۛ۟۟ۗۧۙۥۤۨۚ۟ۤۚۥ";
                                                                                    break;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case -95017098:
                                                                        httpURLConnectionI.disconnect();
                                                                        z = true;
                                                                        break;
                                                                    case 272871133:
                                                                        str27 = "۟ۛۛۙ۫ۨۘۚۨ۬ۥۙ۬۠۫ۛۦۛۜۘۘ۬۟ۦ۬ۤۙۧۨۖۚۜۘ۟ۧۘۚۨۡۨ۬ۘۥۛۖۘۧۢۤ";
                                                                    case 1047114687:
                                                                        break;
                                                                }
                                                            }
                                                            break;
                                                        case 246625434:
                                                            str26 = "ۗۗۘ۟ۨۡۘۦۗۡۙۜۖۘۥۖۛ۫۬ۢۚۤ۫ۦۨۗۖ۠ۚۚۨۥۘۥۥۖ۫ۛۧۧۡ۠۫ۜۧۘ";
                                                        case 1735089851:
                                                            break;
                                                        case 1805829025:
                                                            String str29 = "۠ۡۤۥۚۜۘ۫ۜۦۗ۬ۢۘۘۖۘۢۢۗۧۛۨۚۥۤۡۦۘۛ۟ۡۡۦۧۘ۟ۘۧۨۗ۫ۜۙۖ";
                                                            while (true) {
                                                                switch (str29.hashCode() ^ (-1372179820)) {
                                                                    case -620930100:
                                                                        str26 = "ۢ۬۫ۖۢۢۗۚۦۥۧۥۘۜۨ۠ۛۘۥۘ۠ۦۜۘۢۜۛ۬ۚۖۘۥۥۛۧۜۨۘ۟ۡ۠ۗ۬ۘۗۢۜۘۜۧۤۗۢۢ";
                                                                        break;
                                                                    case 1339753545:
                                                                        str29 = responseCode >= 200 ? "۟ۨۖۘ۫ۡۡۘۚ۬ۘۤ۠ۡۢۘۡۘۘ۫ۡ۬ۖۖۘۙ۠ۡۘ۫۫ۨ۬ۗۙ" : "ۡۦ۟ۤۙۖۘۢۚۧۛۘۦۧۧۧ۟ۨۢۚۤۧ۟ۦۦۘۥۘۜۘ۟۠ۖۘ۠ۘۦۘ۬۫ۦۘۘۧۘۡۤۘۘۡ۬ۖۙ۫۟ۖۘۧۗۜ";
                                                                    case 1845179150:
                                                                        str29 = "۫ۗۥۢۤ۬ۛۘۦۘۛۡۦۘ۟ۜ۬ۥۖۚۢ۠ۡ۟۠۫۠ۦۡۘۘ۠ۘ۟ۥۧ۫ۧۧۘۡ۟ۧۛۡۡ۫ۤۘۜۜۙ۬ۖۚۖۦۘ";
                                                                    case 2137626646:
                                                                        str26 = "ۧ۟ۖۧۘۧۤ۠ۧۢ۠ۧ۬۟ۛ۟ۨۤۡۘۚۙۥۖۖۗۢۗۜ۬ۡۖۨ";
                                                                        break;
                                                                }
                                                            }
                                                            break;
                                                    }
                                                }
                                            } catch (Exception e4) {
                                                String str30 = "ۧ۬ۗ۟ۥۖۤۘۗ۫ۖۙۥۜۥۖۨۘۦۚۘ۟۬ۡۘۥ۬ۨۘۜۡۨۘۡ۟ۘۖۨۖۘۗۛۙۗ۟۫";
                                                while (true) {
                                                    switch (str30.hashCode() ^ (-948911543)) {
                                                        case -1805433073:
                                                            break;
                                                        case -1257325385:
                                                            break;
                                                        case -606423485:
                                                            str30 = "ۢۧۦۘۖۤۚۛ۬ۖ۫ۗۨۘۥۙۚۙۛۤۛۥۛۘۤۥۘۥۢۖۘۧ۫ۦۘۤۖۡ۫۟ۡۘۚ۫ۚۜۖۤ";
                                                        case 1912529120:
                                                            String str31 = "۠ۤۥۘۥ۟ۜ۟ۚۘۢ۫ۜ۫۟ۙ۬ۘۡۧۤۧۢۡۥۘۡ۠ۘۦ۬ۡۢۖۚۖۙۡۡۖۖۘۢۤۦ";
                                                            while (true) {
                                                                switch (str31.hashCode() ^ (-1145013888)) {
                                                                    case -2040885624:
                                                                        str31 = httpURLConnectionI != null ? "۟۟ۗۥ۟ۨۘ۬ۥۡۘۥۢۙۖۗ۠ۥ۠ۛ۬ۖ۟ۧۢۢۨۗۖ۫ۘۜۦۥۦۨۡ۟۫ۦۜۘۦۥ۫" : "ۧۛۜۖۨۘۚۚۨۚۢ۫ۤ۟ۛ۫ۜۘۛ۬ۙۡۖ۠۫ۧۦۘۨۙۨۦۚۡ۠ۨ۠ۗۜۘ۫ۙ۬ۢۡ۬ۨۨ۬";
                                                                    case -146264432:
                                                                        str31 = "ۢ۬ۨۘ۫ۨۧۘۖۛۜۖۚۢۨۚۡۦ۬ۨۖۨۧۥۢۖۡ۠ۧۤۦۥۗۖۘۤ۠ۨۘۚۚۚۖۡ۠";
                                                                    case 1248601371:
                                                                        str30 = "ۚ۬ۛ۬۬ۜۘۗ۟ۨۜۥۛ۫ۗۜۛۘۥ۟ۜۜ۟ۦۛۗۢۥۦۥۧۘ";
                                                                        break;
                                                                    case 2133746463:
                                                                        str30 = "ۚۦۦۘۢۘۖۘۧۘۨۘ۬ۥۦۥۗۦۚۡۘۧۚۦ۠ۖۙۧۚ۫۟";
                                                                        break;
                                                                }
                                                            }
                                                            break;
                                                    }
                                                }
                                                str = "ۨ۫۬ۢۘۗ۟ۨۥۤۧۜ۬ۚ۫ۨ۠ۦۦ۬ۧۤۨ۬ۗۦ۟ۗۗۛۘ۬ۡۘ۬ۤ۟ۜۤۘۛ۟ۨۛۚۡ۟ۥۦۡۘۛۛ۬۬";
                                                while (true) {
                                                    switch (str.hashCode() ^ (-423098890)) {
                                                        case -1523503973:
                                                            break;
                                                        case 663069176:
                                                            break;
                                                        case 937873517:
                                                            break;
                                                        case 1920672039:
                                                            break;
                                                    }
                                                }
                                                i2++;
                                            } catch (Throwable th2) {
                                                th = th2;
                                                httpURLConnection = httpURLConnectionI;
                                                String str32 = "ۜۙۨ۟ۤۥ۬ۧۧ۬ۡۦۘۙ۠ۚ۬ۥ۟ۚۖۖۘۚۖۧۘۤۡۖۢۚ۫";
                                                while (true) {
                                                    switch (str32.hashCode() ^ (-8356571)) {
                                                        case -2138450203:
                                                            break;
                                                        case -69097755:
                                                            str32 = "ۥ۠ۨۦۘۨ۬ۛۦۡ۟ۥۦۥۘۦۘۘۚ۬ۨۨۢۧۧۜۖۘۙۡۨۤۡۥ۬ۦۜۙ۟ۙۥۖۨۘ";
                                                            continue;
                                                        case 1887631155:
                                                            String str33 = "ۨ۠ۗۡۗۨۘۘۙۨۨ۠ۨۘۖۘۘۤۡۨ۟ۧۦۛۡۘۘۛۚۡۧۨ۠۬ۘۛۦۥ۠ۤۡۖۘۚ۬ۙ";
                                                            while (true) {
                                                                switch (str33.hashCode() ^ 1704822415) {
                                                                    case -1763765230:
                                                                        str33 = "ۦۧۨۨۙۘۤۡ۫۫ۡۘۥ۟ۦۚۦۘۖۥۧ۟۫ۖۘۨۢۤۘ۠ۤ";
                                                                        break;
                                                                    case -962729269:
                                                                        str32 = "ۜۤۖۘ۠۫۬۠۫ۜۘۚۥ۠ۨ۠ۡۘۘ۬ۦۘۙۛۡۘۗۖۚۧۡۘۙ۠ۜۘ۟ۥ۟ۢۤ۬ۦۦۘ۟ۧۜۘ";
                                                                        continue;
                                                                    case -110745637:
                                                                        if (httpURLConnection == null) {
                                                                            str33 = "۠ۙ۫ۤۨۛۥۨ۟ۛۨۨۘۘ۬ۙۦۡۥۘۡ۫ۘۘۘۘ۠ۨۘۥۧۢ۬ۙ۠۫ۥۦۖۘ۫ۡۥ۬۫ۡۘ";
                                                                            break;
                                                                        } else {
                                                                            str33 = "۟۬ۖۗۘۦ۠۟ۘۘ۠ۜۘۧ۬ۗۛۨۥۘۡۜۧۥۢۨۘۢۖۡۘۢۖۛ۬ۧۖۡ۠ۡۘ";
                                                                            break;
                                                                        }
                                                                    case 976912446:
                                                                        str32 = "ۙۖ۠ۜۗۤ۬ۚۧ۬ۗۤ۟ۢۖۗ۠ۙۚۗۘۘ۟ۗۨۖۦ۠ۤ۠ۛۛۨۘۜۘۨ";
                                                                        continue;
                                                                        continue;
                                                                }
                                                            }
                                                            break;
                                                        case 2141524075:
                                                            httpURLConnection.disconnect();
                                                            break;
                                                    }
                                                }
                                                throw th;
                                            }
                                            httpURLConnectionI.disconnect();
                                            str = "ۨ۫۬ۢۘۗ۟ۨۥۤۧۜ۬ۚ۫ۨ۠ۦۦ۬ۧۤۨ۬ۗۦ۟ۗۗۛۘ۬ۡۘ۬ۤ۟ۜۤۘۛ۟ۨۛۚۡ۟ۥۦۡۘۛۛ۬۬";
                                            while (true) {
                                                switch (str.hashCode() ^ (-423098890)) {
                                                    case -1523503973:
                                                        String str34 = "ۗۛۨۚۥۙۥۛۦۘۢۦ۫۟ۡۧۘۤۦ۬ۖۜۜ۫ۡ۠ۜۘۜۢۚۧۜۖۤۗ۬۬۟۠۫ۡۦۦۦۢ۟ۘۛۨ";
                                                        while (true) {
                                                            switch (str34.hashCode() ^ 970391540) {
                                                                case -1163271841:
                                                                    str34 = i2 < 2 ? "ۦۚۤۥۗۗۗۨۘۙۘۘۘ۟ۡ۬ۜۧ۬ۛۛۜ۠ۙ۬ۡۦ۟۠ۦۜۥۙ۟۫ۖۘۢ۟ۥۘۧۢۤۢۨۥۢ۟۫۫ۗۛۡۚۘۘ" : "ۧ۟ۥۘ۟ۘۚۖۘ۟۠ۛۨۘۘۙۡۘ۠ۙۖۚۦۢ۫ۖۨۨ۟ۘ۫ۖۧۘۘۙۚۧۙ۫ۨۦۧۨۗۥۧ۬ۘۘۖۜۦۖۤۚۦ۠ۚ";
                                                                case 733906185:
                                                                    str34 = "۫ۨۙۙۢۢۡۦۦۘۦۗۖۘۙ۫ۡۧۦۡۚۚۧۧۦۗ۬ۥۘۖۢۤ۟ۘۤۦۨۢۚ۟ۚۦ۬ۘۘۤۢۚۥۧ";
                                                                case 1743459307:
                                                                    str = "ۨۜۡۤۘۖۚۥۡۘۙۨ۫۟ۜۙ۟ۡۙۜۤۦۙۛۢۨۜۘۜۨۙۡۜۗۡۘۛۚۥۛۧۙۜۧۜۜ۬ۛۜۧۘۦۘۤ";
                                                                    break;
                                                                case 2117852392:
                                                                    str = "ۥ۫ۧۨۤۙۥۧۦۥۢۡۘ۬ۙۗۛۦۡۤۡ۟ۜ۫ۤ۬ۧۙۛۗۦۗۖۢۤۚ۫ۥۛۚ۠ۛۘۘۨۡۘۖۨۨۘ";
                                                                    break;
                                                            }
                                                        }
                                                        break;
                                                    case 663069176:
                                                        break;
                                                    case 937873517:
                                                        str = "۟ۦ۫۬ۥۜ۫۠ۘۘۢۤۙۢ۬ۦ۟ۙۘۨۥۡ۟ۡۖۤۡۢۤۚۧ۠ۢۘۥۧۦۘ۟ۤۥۨۖۦۙۨۦۦۡۘۘ";
                                                    case 1920672039:
                                                        try {
                                                            Thread.sleep(300L);
                                                            break;
                                                        } catch (InterruptedException e5) {
                                                            Thread.currentThread().interrupt();
                                                            break;
                                                        }
                                                }
                                            }
                                            i2++;
                                            break;
                                        case 1477064011:
                                            str24 = "ۘۢۛۢۨۤۖۧۤۚۙۦۘ۬ۛۜۛۤۖۘۚۙۦۘۛ۫ۧۗۤۗۖ۬ۙۡ۟ۥۡ۬ۙۧ۫ۜۨۧ۫";
                                    }
                                }
                            }
                            break;
                    }
                }
                z = false;
                final Handler handler = new Handler(Looper.getMainLooper());
                String str35 = "۬ۡۚۜۘۨۘۨۨۡۘۖۨۚۛ۠ۖۡۚۜۗ۬ۨۘۦۦ۫ۚۗۖ۠ۜ۫ۢ۬ۜۜۡۘۘۨ۫ۗۧۜۜۥۗۘۚ۬ۤ";
                while (true) {
                    switch (str35.hashCode() ^ 1788161513) {
                        case -850763207:
                            str35 = "ۦۗۛ۫ۦ۟ۚۘۙ۟ۨۖۘۛۦۡۘ۠ۡۛۛۗ۬ۛ۠ۘۘۚۙۘۥ۟ۚۚ۠۟ۢۜۘ";
                            break;
                        case -313218916:
                            final int i3 = 0;
                            handler.post(new Runnable(context, handler, i3) { // from class: core.pro.android.notify.v2
                                public final int a;
                                public final Context b;
                                public final Handler c;

                                {
                                    this.a = i3;
                                    this.b = context;
                                    this.c = handler;
                                }

                                @Override // java.lang.Runnable
                                public final void run() {
                                    String str36 = "ۤۛۙۛۚۤ۟ۡ۬ۚ۠ۚۤ۠ۙۙ۠ۙ۠ۢۙۨ۬ۙۢۚۨۘ۫ۙۜۘۗۡۨۘۖۢۙۦۤۜۜۙۛۡۖۗۚ۟";
                                    Context context2 = null;
                                    Handler handler2 = null;
                                    while (true) {
                                        switch ((((str36.hashCode() ^ 73) ^ 973) ^ 97) ^ 1106440597) {
                                            case -2081625860:
                                                switch (this.a) {
                                                    case 0:
                                                        str36 = "۟۠ۙۨ۠ۡۘۨۨۖۥ۬ۗ۟۠۟۠۫ۥۡۦۘ۬ۥۢ۬ۘۧ۠۟ۖۘۡۚ۬ۨ۬ۦۘ۫ۙ۟۟ۘۘۘۚۙۘۢۤ۫۟ۦۖ۟";
                                                        break;
                                                    default:
                                                        str36 = "ۨۧۨۥۘۡۘۘ۬ۖۘۥ۬ۦ۟ۚۤۖۘۜ۫ۚۜۥۚۦۨۘۘۢۗۛۢۙۜۘۙۡۜۨۦۢۧۤۜۘۙۦۗۜۥ۫ۙ۬ۛۗۥۥۘ";
                                                        break;
                                                }
                                            case -1538382916:
                                                Toast.makeText(context2, l2.decrypt("7QxZmCtKZbeIVHDH\n", "CrHIf5DWgAs=\n"), 0).show();
                                                str36 = "ۢۥۦۘۛۢۙ۬ۧۡۘۨۘ۟ۛۛۥۘ۠ۜۗۘۘۦۘ۬ۧۤۛۦۘۘ۟ۢ۠ۥۡۥ۟ۧۨ۫ۧۢۙۚ۫ۗۧۘۗۚ۠";
                                                break;
                                            case -1316778921:
                                                handler2.postDelayed(new a(16), 1200L);
                                                str36 = "ۤۤۡۘۨۦۗ۫ۤۛۦۗۡۘۢۤۗۛۤۨۘۙۘۘ۫۬ۘۛۦۖۦۘ۬۠ۤۦۨۤۗۙۦ۟۟۠ۚۗۙۘۘۙۚۙ۫ۦۤۛۜۘ";
                                                break;
                                            case -986749791:
                                            case -940594803:
                                                return;
                                            case -41312778:
                                                str36 = "۬۫ۨۘ۫ۛۡۨۤۘۘۘۦۡۘ۠ۖۡۘۘ۫ۡ۟ۙۘۘ۠ۨۜۘۥۥۗۜۜ۬";
                                                break;
                                            case 188211235:
                                                handler2.postDelayed(new a(16), 1200L);
                                                str36 = "۟۟۟ۖۛۘۙۛۖۘۚۜۘ۟ۦۡۘۛۚۦۘۗۢۡۢۖۡۘ۟ۤۡۘۦۦۘ۟ۡۜۘۨۦۗ۠ۗۜۚۧۜ";
                                                break;
                                            case 648261209:
                                                Toast.makeText(context2, l2.decrypt("F3BJ6OfdYARaOEOfo+Ya\n", "/9/+DkRdhps=\n"), 0).show();
                                                str36 = "ۘ۠ۙۙۨۨۘۤۡۘ۬ۧۨۤۨۘۜۘۖۨۛۡۘۧۧۤ۟ۗۘۘۡۦۘۛۘۨۚ۠ۜۥۥۧۘ۫ۤۥ";
                                                break;
                                            case 779004508:
                                                Activity activity4 = Utils.a;
                                                str36 = "ۡۥۜ۫۟۬ۘۖۚۦ۟ۢۖۥۧۥ۟ۦۗۥۢۘۨۘۨۦۦۙۨۘۛۥۜۘۚ۫ۖ۫ۜۜۦ۬ۧۦ۬ۜۧۦۥۘ";
                                                break;
                                            case 1450398145:
                                                str36 = "۫ۚۜۘۦۘۗ۬۠ۥ۟ۤۚۖۚۥۨۘۦۙ۫ۤ۟ۥۗ۟۟ۨ۬۠";
                                                handler2 = this.c;
                                                break;
                                            case 1908018746:
                                                context2 = this.b;
                                                str36 = "۫ۥ۬۫ۜۨۘۖۗۡۘۙۖۘۗۚۛۦۛۜ۬ۖۦۗۤۜۧۘۖۘۦ۬ۚۗۨ۟۫ۜۙۢۨۚ۫۟ۘۘۙۙۦۘ۠ۢ۟";
                                                break;
                                            case 2118133338:
                                                Activity activity5 = Utils.a;
                                                str36 = "ۨۥۛۜۜۤۙۗۛۧۛۜۘۧۗۢۢۛۢۛۙۢۤۘۨۘۚۥۘۜۚۜۘۨ۟ۖۜۚۥۘ۠ۥۤ";
                                                break;
                                        }
                                    }
                                }
                            });
                            return;
                        case 612151845:
                            String str36 = "ۘۘۜۘۘ۠ۙۢۢۡ۠ۨۥۦ۠ۘ۠ۧۡۘۨ۬ۖ۬ۧ۬ۘۥۗ۠ۜ۠ۥۦۜۧۨۜۘۦۗۖۘۤ۟۠";
                            while (true) {
                                switch (str36.hashCode() ^ (-1516569092)) {
                                    case -2105221580:
                                        str35 = "ۨۖۢۨۗ۬ۙۗۦۡۘۜۘۤۗۜۘ۫۠ۘۚ۟ۧ۫ۢۙۘۡۦ۬ۛۚ";
                                        continue;
                                    case -2036586399:
                                        str36 = "ۘۥۧۘۖ۫ۨۘۥ۫ۦۢۖۖۢۧۡۦ۬ۙۘ۬۫۫ۙۨۘۙۦۥۘۧۨۢ";
                                        break;
                                    case -1623975902:
                                        if (!zHasCapability) {
                                            str36 = "۬۫ۜۘۤۙۥ۠ۦۖۘ۫ۛ۠ۚۡ۬۟ۧۛۤۨۘ۠۫ۜۛۛۦۛۡۜۘۡۨۘ۟۟ۤ";
                                            break;
                                        } else {
                                            str36 = "ۛۢۥۘۜۗۚۜۙۘ۫ۗۡۘۚۢۘۖۨۜ۠ۡۘۛ۬ۧۗۢ۠۟ۦ۠ۙۥۙۛۨ۬ۤۡۖۘ۬ۢۧ";
                                            break;
                                        }
                                    case -330669994:
                                        str35 = "ۛۥ۠۬۠ۘۗۗۖۘۜۚۜۘۦۤۤۧۛۦۘۨ۫ۡۘۘ۫ۥۘۨۧ۬۫ۖۡۗۥۖ۟ۦۨۘ۟ۥۚۖۜۗ";
                                        continue;
                                }
                            }
                            break;
                        case 1970424184:
                            String str37 = "ۙۦۥۘۦ۟ۚ۬۠ۡۛۤۢۚۢۛۤۛۡۘۚۛۘۘۚ۬ۨۘۜ۠ۤۖۤۢۗۡ۫۬ۡۘۘۙۛۦۘۛۙ۠ۛۙ۠۫ۗۜۘ۠۟ۘۘ";
                            while (true) {
                                switch (str37.hashCode() ^ (-1215256771)) {
                                    case -326017809:
                                        str37 = "ۖۨ۬ۧ۟ۘۧۘ۫ۦ۬ۧۖۤۖۥۜۘۧۢۜۘۛۦۖۘ۬ۚ۟ۜۙۡ";
                                        break;
                                    case 735277691:
                                        String str38 = "ۚۧۡۘۛۧۨۚۧۜۛ۫ۚۖۥۦۘۜ۬ۧ۟ۤۢ۟ۧ۬ۧۡۙۨۛۡۨۨۜۘۛۡۖ۬ۜۡۘۥ۟ۤۡۤ۟ۘۖ۫ۢۤۘ۫ۦۡ";
                                        while (true) {
                                            switch (str38.hashCode() ^ (-1947975620)) {
                                                case -1422439040:
                                                    str37 = "ۘ۫ۙۤۢۥۤۤ۠۟ۗۥۘۥۢۘۘ۫ۡۦۘ۫ۤۛۘۧۚ۫۬ۧۨۚۘۘ۫ۨۦۨ۟ۦۘ";
                                                    continue;
                                                case 1422678063:
                                                    str38 = "۠ۧۖۗۗۘ۬۟ۨۘۛۦۘۗۖۦۚ۫ۥۘۦۢۡۘ۟ۙۖۘۜۨۦ۫ۚۗۖ۫ۦ۠ۜۖۗۜ۫۟ۙۦۘ۟۫ۚۦۖۘ";
                                                    break;
                                                case 1793848865:
                                                    str37 = "ۦ۬ۦۚۨۘۥۘۢۚۜۦۘۛۘۜۗۨۘۘ۠ۨۨۦ۠ۦۘۚۙۗۘۧۗۖۧۛۜ۠";
                                                    continue;
                                                case 2135017301:
                                                    if (!z) {
                                                        str38 = "ۘۨۚۢۙۨۜۥۥۗ۟ۜۘۖ۫ۜ۠ۖۜۘۤۧ۠۫۠۠ۗۖۦۗۧۧۥۜ۬۬ۖۤۧۨ۬ۦۡۖ";
                                                        break;
                                                    } else {
                                                        str38 = "ۦۜۖ۬ۖۥۘۚۘ۟ۥۘ۟ۧۤ۟ۜۥۤۜۥۙۙۛۘۘۖۦۡۘۦۙۨ";
                                                        break;
                                                    }
                                            }
                                        }
                                        break;
                                    case 1139818260:
                                        return;
                                    case 2031408160:
                                        final int i4 = 1;
                                        handler.post(new Runnable(context, handler, i4) { // from class: core.pro.android.notify.v2
                                            public final int a;
                                            public final Context b;
                                            public final Handler c;

                                            {
                                                this.a = i4;
                                                this.b = context;
                                                this.c = handler;
                                            }

                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                String str362 = "ۤۛۙۛۚۤ۟ۡ۬ۚ۠ۚۤ۠ۙۙ۠ۙ۠ۢۙۨ۬ۙۢۚۨۘ۫ۙۜۘۗۡۨۘۖۢۙۦۤۜۜۙۛۡۖۗۚ۟";
                                                Context context2 = null;
                                                Handler handler2 = null;
                                                while (true) {
                                                    switch ((((str362.hashCode() ^ 73) ^ 973) ^ 97) ^ 1106440597) {
                                                        case -2081625860:
                                                            switch (this.a) {
                                                                case 0:
                                                                    str362 = "۟۠ۙۨ۠ۡۘۨۨۖۥ۬ۗ۟۠۟۠۫ۥۡۦۘ۬ۥۢ۬ۘۧ۠۟ۖۘۡۚ۬ۨ۬ۦۘ۫ۙ۟۟ۘۘۘۚۙۘۢۤ۫۟ۦۖ۟";
                                                                    break;
                                                                default:
                                                                    str362 = "ۨۧۨۥۘۡۘۘ۬ۖۘۥ۬ۦ۟ۚۤۖۘۜ۫ۚۜۥۚۦۨۘۘۢۗۛۢۙۜۘۙۡۜۨۦۢۧۤۜۘۙۦۗۜۥ۫ۙ۬ۛۗۥۥۘ";
                                                                    break;
                                                            }
                                                        case -1538382916:
                                                            Toast.makeText(context2, l2.decrypt("7QxZmCtKZbeIVHDH\n", "CrHIf5DWgAs=\n"), 0).show();
                                                            str362 = "ۢۥۦۘۛۢۙ۬ۧۡۘۨۘ۟ۛۛۥۘ۠ۜۗۘۘۦۘ۬ۧۤۛۦۘۘ۟ۢ۠ۥۡۥ۟ۧۨ۫ۧۢۙۚ۫ۗۧۘۗۚ۠";
                                                            break;
                                                        case -1316778921:
                                                            handler2.postDelayed(new a(16), 1200L);
                                                            str362 = "ۤۤۡۘۨۦۗ۫ۤۛۦۗۡۘۢۤۗۛۤۨۘۙۘۘ۫۬ۘۛۦۖۦۘ۬۠ۤۦۨۤۗۙۦ۟۟۠ۚۗۙۘۘۙۚۙ۫ۦۤۛۜۘ";
                                                            break;
                                                        case -986749791:
                                                        case -940594803:
                                                            return;
                                                        case -41312778:
                                                            str362 = "۬۫ۨۘ۫ۛۡۨۤۘۘۘۦۡۘ۠ۖۡۘۘ۫ۡ۟ۙۘۘ۠ۨۜۘۥۥۗۜۜ۬";
                                                            break;
                                                        case 188211235:
                                                            handler2.postDelayed(new a(16), 1200L);
                                                            str362 = "۟۟۟ۖۛۘۙۛۖۘۚۜۘ۟ۦۡۘۛۚۦۘۗۢۡۢۖۡۘ۟ۤۡۘۦۦۘ۟ۡۜۘۨۦۗ۠ۗۜۚۧۜ";
                                                            break;
                                                        case 648261209:
                                                            Toast.makeText(context2, l2.decrypt("F3BJ6OfdYARaOEOfo+Ya\n", "/9/+DkRdhps=\n"), 0).show();
                                                            str362 = "ۘ۠ۙۙۨۨۘۤۡۘ۬ۧۨۤۨۘۜۘۖۨۛۡۘۧۧۤ۟ۗۘۘۡۦۘۛۘۨۚ۠ۜۥۥۧۘ۫ۤۥ";
                                                            break;
                                                        case 779004508:
                                                            Activity activity4 = Utils.a;
                                                            str362 = "ۡۥۜ۫۟۬ۘۖۚۦ۟ۢۖۥۧۥ۟ۦۗۥۢۘۨۘۨۦۦۙۨۘۛۥۜۘۚ۫ۖ۫ۜۜۦ۬ۧۦ۬ۜۧۦۥۘ";
                                                            break;
                                                        case 1450398145:
                                                            str362 = "۫ۚۜۘۦۘۗ۬۠ۥ۟ۤۚۖۚۥۨۘۦۙ۫ۤ۟ۥۗ۟۟ۨ۬۠";
                                                            handler2 = this.c;
                                                            break;
                                                        case 1908018746:
                                                            context2 = this.b;
                                                            str362 = "۫ۥ۬۫ۜۨۘۖۗۡۘۙۖۘۗۚۛۦۛۜ۬ۖۦۗۤۜۧۘۖۘۦ۬ۚۗۨ۟۫ۜۙۢۨۚ۫۟ۘۘۙۙۦۘ۠ۢ۟";
                                                            break;
                                                        case 2118133338:
                                                            Activity activity5 = Utils.a;
                                                            str362 = "ۨۥۛۜۜۤۙۗۛۧۛۜۘۧۗۢۢۛۢۛۙۢۤۘۨۘۚۥۘۜۚۜۘۨ۟ۖۜۚۥۘ۠ۥۤ";
                                                            break;
                                                    }
                                                }
                                            }
                                        });
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
