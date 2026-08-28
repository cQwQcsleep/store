package gTBLD.dev.XSSTG.free;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.iqoocg.nm.fcRuQsQrcxOAzxwEalcM;
import core.pro.android.notify.e0;
import core.pro.android.notify.f0;
import core.pro.android.notify.h0;
import core.pro.android.notify.k0;
import core.pro.android.notify.k2;
import core.pro.android.notify.l2;
import core.pro.android.notify.m0;
import core.pro.android.notify.n0;
import core.pro.android.notify.s0;
import core.pro.android.notify.t3;
import java.lang.reflect.Method;

/* loaded from: /workspace/unpacked/classes2.dex */
public class MainActivity extends Activity {
    private boolean isNightMode;
    private int rootBackgroundColor;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private boolean isDestroyed = false;
    private final Runnable checkRunnable = new k0(this);

    public static /* synthetic */ int access$000(MainActivity mainActivity, int i) {
        String str = "ۜۙۥۘۢۖۥۘۛۢۦ۬ۢۖۘۥۦۨۡۧۘۤۢ۟ۧ۠ۥۘۜ۫ۧۡۧۘۜۙۖۘۜۤۤۗۥۛۤۜۜ۬ۗۥ۠ۦۦۘ";
        while (true) {
            switch ((((str.hashCode() ^ 745) ^ 707) ^ 454) ^ 1037559909) {
                case -2051398765:
                    str = "ۜ۟ۘۙۘۛۖۙۢۡ۟ۥۢۨۧ۫ۡۥۖۧۖۢۥۘ۬ۨ۠ۡۧۥۘۚۗۥۘۚۨۖۘ۫ۘۧ۟ۦۘۙۙۙۚۡۖ۟۠ۧۨۨۡۘ";
                    break;
                case -1154209911:
                    str = "ۥۗۘۘۜۚۥۜ۠ۤۨۨۖۘۚۢ۠ۢۗۛۜ۟ۜۘۖۦۜۘ۬۟ۥۘۤۙۢۚۡۜۨ۟";
                    break;
                case -940777694:
                    return mainActivity.dp(i);
            }
        }
    }

    public static /* synthetic */ boolean access$100(MainActivity mainActivity) {
        String str = "۫ۨۤۜۖۧۘ۬ۗۚۗۙۢۖۚ۬ۦۡۥۘۧ۬ۜ۫ۚۘۘ۫ۜۘۢ۫ۧ";
        while (true) {
            switch ((((str.hashCode() ^ 377) ^ 686) ^ 389) ^ (-2031014371)) {
                case -1788264539:
                    str = "ۧ۬ۜۤۧۡۘ۬ۦۡۘ۟ۨۤۡۥۤۥۘۙۦۗۨۛۥۛ۬ۡۙ۟ۥۘۢۡ۠۬ۦۚ۟ۗۖۘ۫ۘۧ";
                    break;
                case 1103666805:
                    return mainActivity.isNightMode;
            }
        }
    }

    public static /* synthetic */ boolean access$200(MainActivity mainActivity) {
        String str = "ۡۨۢۜۛۧ۫۠ۙۤۖۢۘۡۥۛۚۜۘۖۦ۫۠ۡۖ۬ۡۜۗۨۘ۠ۙۤۜۙۗۡۦۧۘ۬ۖۘ";
        while (true) {
            switch ((((str.hashCode() ^ 276) ^ 109) ^ 548) ^ 2055668145) {
                case -1888323872:
                    return mainActivity.isDestroyed;
                case 523129789:
                    str = "۫ۧۗ۬ۢۜۨۨۜۘۜ۬ۗۗۡۜ۟۬ۚۖۙۛۘۥ۠ۦۖ۠ۘۨ۟۬ۗۜۘۡۥۘۘۤۡۥۘۛۗۛۘۡۜۘۥۤ۟";
                    break;
            }
        }
    }

    public static /* synthetic */ Handler access$300(MainActivity mainActivity) {
        String str = "ۢۙۥۘۖۨۦۘۜۢۖۥۛۦۘ۟ۛۛ۫ۥۘۦۗۘۥۗۡۘۡ۠ۜۖۦۜ۟ۦۘۥۚۜ";
        while (true) {
            switch ((((str.hashCode() ^ 638) ^ 262) ^ 555) ^ 703825012) {
                case -1267695269:
                    return mainActivity.handler;
                case -1095422389:
                    str = "ۤۨۨۘۢۤۖۘۖۥۦۘۜۥۗۨۥۚۡۧۢۥۢۛۘۦۘ۟ۨۦۚۘۜۧۢۘۘ۟ۚۨۚۧ۟ۙ۫ۥۜۗ۟۠ۗۥۘ";
                    break;
            }
        }
    }

    public static /* synthetic */ boolean access$400(MainActivity mainActivity) {
        String str = "۠ۛۖۘۜۗۥۘۖۜۜۨۤۥۘۙۗۘۘۙۡۜۘۦۨۖۛۧۚۖۢۖۘۡ۫";
        while (true) {
            switch ((((str.hashCode() ^ 109) ^ 834) ^ 724) ^ (-1669476689)) {
                case 533115221:
                    return mainActivity.hasSelfType2Window();
                case 704592410:
                    str = "۠ۛۨۘۧ۟ۨۘ۟ۢۥ۠ۧۨۘ۬۬ۙۖۨۥۜ۫ۧۘۢۧۗۚۨۘۗ۠";
                    break;
            }
        }
    }

    public static /* synthetic */ Runnable access$500(MainActivity mainActivity) {
        String str = "ۚۤۨۘۜۗ۬۟ۢۚ۬ۥۖۘۜۡۜۘ۟ۛۦۘ۟ۨۗۨۧۘۘۦۛۢۖ۬ۜۘۗۚۘۘۛۥ۫ۦۖۘۢۤ۟";
        while (true) {
            switch ((((str.hashCode() ^ 293) ^ 652) ^ 370) ^ 1223669458) {
                case -1279507805:
                    return mainActivity.checkRunnable;
                case 301406942:
                    str = "۫۫ۜۖۜۡۘۥۤ۬ۢۚۥ۟۠۟ۦ۠ۘۗۜۦۘۥۗۧۢ۫ۤ۫ۖۦۘۧۡۖۘۜۙۖ";
                    break;
            }
        }
    }

    public static /* synthetic */ void access$600(MainActivity mainActivity) {
        String str = "ۡۘۨۘ۬ۚ۬ۢۛۖۘۜۜۚۛۘۦ۟ۧ۟ۡۢۛۧۦ۟ۙۧۨۦ۟ۤۧۘ۟ۛۛۨۘۤۡ۫ۚۙ۫۬ۡۢۤۥۖۗۜۘۛۨ۟";
        while (true) {
            switch ((((str.hashCode() ^ 224) ^ 822) ^ 404) ^ 1129377202) {
                case -2038147566:
                    mainActivity.launchNextActivity();
                    str = "ۙۥۡ۠ۘۘ۬ۚۥۘ۫ۥ۫ۚۘۗۡۢ۫ۦۗۘۥ۟۬ۚۡۦۤۧۜۘ";
                    break;
                case -2021958835:
                    return;
                case 1676489734:
                    str = "ۜۖ۫ۤۖۜۚ۬ۥۜۛۛ۠۟ۤۨۨۘۨۖ۟ۗۛۚ۠ۘ۠ۡۗۦۘۙۜۚۡۢۤ";
                    break;
            }
        }
    }

    private void clearAllPopupIdCache() {
        String str = "ۦۡ۟۬ۚ۠ۨۛۦۦۖۜۘۗۨۜۘ۠۬۟ۜۙۛۨۥۘۢۜۥۧۛۡۡۨۨ۠ۘۘۡۚۚۙۛۗۙ۠۫ۦۖۦۨۨ۬ۙۛۖۘ";
        while (true) {
            switch ((((str.hashCode() ^ 613) ^ 87) ^ 549) ^ (-684007032)) {
                case -2038431039:
                    k2.closedMessagePopupIds.clear();
                    str = "ۥۖ۟ۥۨۗۥۙۙۡ۟ۙۨۧۜۙۖۗۗۗۥۘۦۡۛۤ۟۟ۡۦۘ۫ۦۥ۬ۨۙۛۢۘۘۜۧۘۦۖۡۘۧۛۚ۫ۢۙۥۜ";
                    break;
                case -1562578138:
                    t3.needTextPopupIds.clear();
                    str = "۫ۧۘ۬ۦۧۚۡ۫۠ۢۦ۫۬ۨۘۛۙۜۘۢۗۨۘ۟ۚۗ۬ۨۙۖ۫۬ۚ۫ۢۥۥ۬";
                    break;
                case -1143512697:
                    k2.closedPopupIds.clear();
                    str = "ۤۚ۟ۥۗۨۧۙۥۘۗۥ۟ۡۨۨۜ۬ۦۙ۫ۤۥۗۗۤ۠ۛۦۧۜۘۛۥۖۘۤۘۡۘۜ۟ۖۢ";
                    break;
                case -1096229775:
                    t3.needFullscreenPopupIds.clear();
                    str = "ۢ۫ۨۧۦۥۢۢۡۜۚۧۗۨۖۖۘۗۧۡۘۘۡ۠ۜۨۗۙۗۥۜۧۙۖۘۖۘۧ۠ۥ۟ۚ۫ۥۢۜۦۘۤ۠ۡۚۙۜۘۧۖۧ";
                    break;
                case -937906545:
                    t3.needMessagePopupIds.clear();
                    str = "ۛۜ۬۬۫ۘۨ۠۫ۙۥۘۨۙۡ۠ۜۦۘ۬ۢۢ۠ۚ۫ۚۙ۫ۖۗ۠ۛ۠ۗۘۛ۟ۚۖۡۘۤۢۤۦ۬ۘۢۙۛۥۚۖ۫ۙۛ";
                    break;
                case -638404740:
                    k2.closedTextPopupIds.clear();
                    str = "ۦۦۦۥ۟ۜۢۘۛ۬ۥۘۥ۬ۨۘ۟ۚۚۗ۟ۛۖۡۦۘۚۖۡۘ۬ۗۨۤۢۖۘ۬ۧۡۘ";
                    break;
                case -615752728:
                    str = "ۦۙۚۥۥۨۘ۠۫۫۬ۨۨۥۡۢ۬ۘۜۥۧۥۜۦۖۚۜۘۚۙۥۘۚۧۚۥۖۡۢۨۥۘۤۧۡۘ۬ۢۥۥۨۘ";
                    break;
                case -405523229:
                    t3.needHtmlPopupIds.clear();
                    str = "ۢ۬ۦ۠ۨۦۘ۬ۛۡۘۙۗۧۡۖۦۢۘۤ۬۟ۡۘ۟۠۠ۨۖ۫۠۬ۗ";
                    break;
                case 130184080:
                    k2.closedHtmlPopupIds.clear();
                    str = "۬۟ۡۜۡۦۘۡۡۦ۟ۙۡۘ۠ۦ۟۫ۜۖۘۢۜ۬۠ۥ۠ۙۛۥ۫ۙۥۘۧۜۢۛ۬۠";
                    break;
                case 569942460:
                    return;
                case 1630846293:
                    System.out.println(l2.decrypt("3qx22+YR4tvgnWv2s4QWBWNEnEw620QyLRWaA3bdGFAva1DvdN0yUihk9hcbD8TS4dwyi/ANzsTg\nmPYXGg==\n", "hfwZq5Nhobc=\n"));
                    str = "ۨۗۜۘۧۡ۟ۚۖۡۘۧ۬ۦۘۛۢۚ۠۟ۖۧۘۨ۬ۘۚۜ۫ۘۘۢ۟ۡۘۚۙۧۤۖ۟۠۠ۘۖۡ۟";
                    break;
                case 1821039172:
                    k2.closedImagePopupIds.clear();
                    str = "۬ۗ۟۬ۥۖۦۥۘۙۦۘۘ۫ۚۥۘۙۧۥۢۖۡۘۨۧۦۘۜۤۤۥ۠۬ۗۤۖۧۡ۫ۛۗۚۧۖۙ۬ۡۚۗۖ۬ۦۜۘ۬ۚۚ";
                    break;
                case 1939052536:
                    t3.needImagePopupIds.clear();
                    str = "ۦۡۤۡۨۗۨۡ۠ۘ۬۫ۥۚۢۧۧۚۜۚۡ۫ۨۥۡۘۢ۟ۘۘۦۜۖۨۤۛ";
                    break;
            }
        }
    }

    private int dp(int i) {
        String str = "ۦۡۥۘۘ۬۫ۛۨۖ۫ۚۚ۫۠ۚۚۘۜۘۘۖ۠ۖۜۧۘۗۧۨۥۜۜ۬۟ۡۘ۟ۙ۫ۛۦۙۘۙ۬";
        while (true) {
            switch ((((str.hashCode() ^ 39) ^ 495) ^ 945) ^ (-1832577672)) {
                case 1118154716:
                    return (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
                case 1471014125:
                    str = "ۧۗۛۢۨۢۛۗۡۚۦۥ۫ۢۨۗۙۢ۫ۨۘۛۡۤۛۚۢۧۢۘۥۚۜۘۥ۫ۢۖۖۘۛۚ۟ۤۤۥۘ۟۫ۧۢ۟ۜۘۤۡۙ";
                    break;
                case 1516264083:
                    str = "ۙۜۧۘۥۜۛۗۗۥۙۡۘۘۚۜۦۢۗۦۘۥۗ۠ۖۜۧۛۥۘۢۚۦۘ۫ۥۥۗۜۥۘ";
                    break;
            }
        }
    }

    private String getAppVersion() throws PackageManager.NameNotFoundException {
        try {
            return l2.decrypt("pgiI6knPMoY=\n", "8G36mSCgXKY=\n") + getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (Exception e) {
            return l2.decrypt("TjR4nnKR9p4n\n", "GFEK7Rv+mL4=\n");
        }
    }

    private Drawable getApplicationIcon() {
        try {
            return getPackageManager().getApplicationIcon(getPackageName());
        } catch (Exception e) {
            return null;
        }
    }

    private boolean hasSelfType2Window() {
        int i;
        l2.decrypt("iT+CHwd26bO3Xro=\n", "0mznc2EikMM=\n");
        try {
            Class<?> cls = Class.forName(l2.decrypt("6HgnhJapkLH/fyaB15ed8e15NLuYrpX47GQEmpailfM=\n", "iRZD9vnA9J8=\n"));
            Object objInvoke = cls.getMethod(l2.decrypt("q5Jw9Z+Yyp6ilGE=\n", "zPcEvPHrvv8=\n"), null).invoke(null, null);
            Method declaredMethod = cls.getDeclaredMethod(l2.decrypt("LAz2UZSTlfEkBvZJnJuH0A==\n", "S2mCB/324qM=\n"), null);
            Method declaredMethod2 = cls.getDeclaredMethod(l2.decrypt("V9U+rxRs8PZZ1T0=\n", "MLBK/XsDhKA=\n"), String.class);
            declaredMethod.setAccessible(true);
            declaredMethod2.setAccessible(true);
            String[] strArr = (String[]) declaredMethod.invoke(objInvoke, null);
            String str = "ۖ۠ۡۘۚۤۨۘۥۛ۫ۘۗۙۤۘۘ۬ۖۨۧۚۨۚۚ۠ۢۙۛۚۥۘۦۥۖۗۙ۟ۖ۫ۡۘۚ۬۬ۥ۠۫ۦۧۨۘۢۚۚۗ۟ۢ";
            while (true) {
                switch (str.hashCode() ^ (-1676558746)) {
                    case -42898361:
                        int length = strArr.length;
                        int i2 = 0;
                        int i3 = 0;
                        while (true) {
                            String str2 = "۫ۡ۫ۚۢۦۘۡۖۚ۟ۖ۫ۛۨۦۢۛۧۢۖۘۘۢۧۖۜ۟ۡۢۛۛ۠ۦۘ۠ۤ۫۟ۤۥۗۥۘ۬ۦۗ۠۫۟";
                            while (true) {
                                switch (str2.hashCode() ^ 350801800) {
                                    case -1198703148:
                                        str2 = "ۧۙۡۥۙۦۘ۟۟ۖۘۡۦۖ۫ۤۥۥ۫ۢۡۦۡۘ۫۬۠ۡۦۥۨۙۗ۟ۨۙ۟ۜ۠۠ۙۘ۬۬۠ۚ۬ۘۡۘۤ۬ۖ۬ۙ";
                                        break;
                                    case 313046558:
                                        String str3 = "ۚ۟ۖۘۚۧ۟ۜۚ۫ۖۦۡۘۙۗۨۘۖۡۘۘۥۘۗۨۙۘۡۘۗۡۛۙ۬۟ۜ۬ۨ";
                                        while (true) {
                                            switch (str3.hashCode() ^ 1604563799) {
                                                case -799747677:
                                                    return true;
                                                case -568057553:
                                                    str3 = "ۦۚ۫ۚۡ۫۫۟۬ۘۥۙۚ۬ۤۧۗۜ۠۟ۘ۬ۧۥۢۦۘۖۤ۫ۚۘۤۛۗۨۧۛۛۨۢۘۘۖۥ۫ۤۖۜ";
                                                    break;
                                                case -442408810:
                                                    String str4 = "۠۠ۜۘۨۖۖۨۘۨۜ۟ۦۙۚۢۖۢۘۛۨۚۖۙۤۦۧۥۢ";
                                                    while (true) {
                                                        switch (str4.hashCode() ^ (-375654229)) {
                                                            case -1741177697:
                                                                if (i2 <= 0) {
                                                                    str4 = "ۗۨۖۘۖۨۡۘۦۖۨ۠۠ۦۥۘۘۤ۬ۡۘ۫۫ۗۛۚۖۘۙ۟ۤۧۡۛۤ۟۬ۦۘۡۦۢۛۢۧۛۨۘۚ۟ۡ";
                                                                    break;
                                                                } else {
                                                                    str4 = "ۛۜۧۤۚ۟۠ۤۖۧۥۧۘ۟۫ۜۘۚۦۡۘۨ۟۫ۤۜۗۨۜ۠ۖۙ۫۠ۨۧ۬ۜۨۘۗۤۚ۫ۨ۠";
                                                                    break;
                                                                }
                                                            case -30332283:
                                                                str3 = "ۗۗۨۛۘۥۘۜۦۦۡۖۧۘۛۢۖۙۙۢ۟ۨۡۘ۟ۥۜۘۙۘۢۗ۫";
                                                                continue;
                                                            case 1801867342:
                                                                str4 = "ۚ۫ۥۛۥۘۡۚۜۖۖۦۥۧ۬ۛۘۢ۟ۙۜۨۨۡۛۙۘۘ۬ۡۤۖۖۘ۫ۙۖۘ";
                                                                break;
                                                            case 2109691933:
                                                                str3 = "ۘ۬۬ۦۛۘۛۗۥۘ۫ۛۖۘۤ۫ۘۘۥ۬۫ۘۤۖۜۘۢۡۢۙۘ۟ۥۘ";
                                                                continue;
                                                        }
                                                    }
                                                    break;
                                                case 509108658:
                                                    return false;
                                            }
                                        }
                                        break;
                                    case 435391340:
                                        String str5 = "۟ۙ۫ۖۧۖۥۛۥۧۗۥ۫ۘۖۧۗۜۛۛۜۨۤۖۤۢۜۘ۠۬ۙۙۛۘۘۥۘۖۘۗۛۦۥ۟۠ۧ۠ۦۘۚۦۧ";
                                        while (true) {
                                            switch (str5.hashCode() ^ (-777613555)) {
                                                case -454983667:
                                                    str2 = "۠ۖۙۤ۬ۡۘۙۡۨۤۛۧۢۗۤۥ۬ۘۢۥۘۖ۫ۨۘۨ۬ۡۘ۠ۤ۬ۧۖۚۦۡۛ";
                                                    break;
                                                case 677631950:
                                                    str2 = "ۨۙۥۘۖ۟۬ۙۥۜۘ۟۟ۖ۠ۢۢۜۨۧۗۨۢ۫۫۫ۨۡۧۘۥۨۧ";
                                                    break;
                                                case 1579633127:
                                                    str5 = "۬ۘۥۦۧۡۧۥۡۧۡ۬۠ۘ۬۟۬ۤۙ۠ۖ۫ۦۡۥۗۥ۠ۥۨ";
                                                case 1930025424:
                                                    str5 = i3 < length ? "ۙۤۤۦۖۦۘۛۚۖۙ۟ۡۘ۠۟ۘۚۘۖۧۙۛۥۚۚۤۘۜۤ۬ۛۚۗۦۢۨۚۙۦ۬ۧۢ۫ۤۜۚۜۙۖۘۤۢۧۢ۠۫" : "ۜ۠ۚۥۦۘۜۦۚۨۖۡ۬ۙۤۨ۬ۡۖ۠ۛۧ۬ۧۧۧۗۦۚۛۥۤۘۘۗۡۗۦۚۜۤۜ۠";
                                            }
                                        }
                                        break;
                                    case 1830756441:
                                        View view = (View) declaredMethod2.invoke(objInvoke, strArr[i3]);
                                        String str6 = "ۚۥۡۘ۫ۛۗۚۡۘۘۘ۟ۘۘۖۥۖۘ۬۫ۢۢ۠ۦۘۛۚۜۤۥۜۘۘۖۢۙ۟ۜۘۦۥ۬";
                                        while (true) {
                                            switch (str6.hashCode() ^ (-1890685307)) {
                                                case -1128358265:
                                                    Object tag = view.getTag();
                                                    String str7 = "۟ۙۜۢۤۢۜۜۧۘ۬ۙۚۡۘۜۘۜۤۦ۬ۙۜۥۤۢ۠ۦ۫ۢۦۘۧۖۚۙۥۦۛ۬ۦۘ۬ۛۚ";
                                                    while (true) {
                                                        switch (str7.hashCode() ^ (-1780732516)) {
                                                            case -1796210920:
                                                                break;
                                                            case -1605419046:
                                                                str7 = "ۢ۟ۥۡۢۖۘۨ۠ۘۘۥ۬ۘۦۗۧ۬ۘۘۧۦۨۘۦ۫ۜۢۦۢۚۗۡۘۥۨ۬ۚۙۖۘۦۨۖۘۘ۟ۚ۫۫ۜۘۛ۫۠ۗ۫ۥ";
                                                            case -1549021867:
                                                                String str8 = "۠ۢۛۛۗۡۨۡۧۘۧۨۨۘ۬ۨۥۘۨۜ۬ۡۦۧۘۛ۠۠۠ۙ۫ۛۥ۟۠ۖۧۘۡۨۧۧ۠ۥۡۢۗۢۤ۬ۡۥ۬۫ۧۚۚ";
                                                                while (true) {
                                                                    switch (str8.hashCode() ^ 768608909) {
                                                                        case -1840056475:
                                                                            str7 = "۫ۤۦۖ۬ۚ۟ۘ۟ۤۢ۫ۦۢۜۜۙۥۦۧ۬ۖ۠ۥۘۗۨ۟ۦۙۥ";
                                                                            break;
                                                                        case -1728937897:
                                                                            str7 = "ۛۗ۠ۧۙ۠ۨۙ۫ۘۘۦۘۡۥۡۦ۫۠ۘۙۧۜۥۢۘ۟ۧ۫ۦۨۘ۬ۨۜۘۤ۟ۦۘۦۚۧ۠ۡ۫ۖۛۨۦۘۥ";
                                                                            break;
                                                                        case 1858055770:
                                                                            str8 = tag instanceof String ? "ۢۘۖۘۤۛۘۡۢۡۘۡۜۚ۟۬۬ۖۙۘۘۚ۠ۦۘۢ۫ۧۦۧۜۤۢۘۘۡۤۜۘۡۦۘۢۨۙ۫ۘۡۥۙۦۘۢۨ" : "ۛۦۥۘۜ۟ۨۤۥ۟ۛۢۢۡۜ۬ۗۙ۬ۢۖ۬ۛ۟۫ۨۚۚ۠۟ۥ۬ۨ۟ۧ۬ۗۤۢۙۥۖۘۘۡۧۡۢۛۛ";
                                                                        case 1983209895:
                                                                            str8 = "ۥ۫ۜۘۚۥۥۘۙۜۙۡۥ۟ۘۙۚۙ۠ۨۘۨۡۨۘۨ۫ۡۘۤۚۖ۫ۖۜۗ۬ۘۘۨ۬ۤۧ۫ۢۗ۫ۖۘ";
                                                                    }
                                                                }
                                                                break;
                                                            case -1067188564:
                                                                String str9 = "۬ۚۡۦ۟ۡۘۚۢۨۘۚۦۢ۬ۥۘۡۢۖۘۙۦۘۙ۫ۦۤۛۡۘ۬ۛۖۘۥۦۜۘ۫۠۠ۜ۫ۙۗۤۜ";
                                                                while (true) {
                                                                    switch (str9.hashCode() ^ (-1416909635)) {
                                                                        case -1587121732:
                                                                            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                                                                            String str10 = "ۡۥۥۘۚۗۥۘۙۖۗۧۨۘۨۗۜ۬ۦۥۘۦۦۡۛ۠ۚ۫ۨۥۘ۠ۙۢ";
                                                                            while (true) {
                                                                                switch (str10.hashCode() ^ (-1746368040)) {
                                                                                    case -984624585:
                                                                                        String str11 = "۬ۡ۠ۦۚۚۡ۫۠ۧۚۥ۟۬۠ۖۜۛۨۨۘ۟۬ۖۘ۠ۘۜۨۡ۬ۙۚۥۘۤۤۜۘۘۤ۫ۗ۟ۡۥۨۨۖۖۨ۠ۡۘۘۧۦۧ";
                                                                                        while (true) {
                                                                                            switch (str11.hashCode() ^ 229126793) {
                                                                                                case -2035235090:
                                                                                                    str10 = "۬ۡۦ۫ۨۦۡۖ۫ۘۜۘۛۛۛ۫ۘۘۘۥۙۜۛۛۜۘۧۡۘۘۥ۟ۘۘ۟۟ۘ۟ۗۨۘۘۥۜۨۘ۟";
                                                                                                    break;
                                                                                                case -1713941608:
                                                                                                    str10 = "ۥ۫ۤۦ۟ۖ۟ۜۖۧۖۡۘ۫ۘۚ۠ۧۦۥۥۜۘۧۧۧۚ۟ۗۙۥۦۘۡ۬ۗ۠ۡۧۜۘۘۖۘۥۘۗۙ۠۠ۜۨۘۛۡۘ۠ۦۛ";
                                                                                                    break;
                                                                                                case 466734313:
                                                                                                    str11 = "۬۬ۗ۫ۖۥۙ۫ۛۢ۫ۛۚۖۛۥ۠ۥۥۖۙ۫ۗۧۗ۫ۗ۠ۘ";
                                                                                                case 1708103860:
                                                                                                    str11 = !(layoutParams instanceof WindowManager.LayoutParams) ? "ۜ۟ۨۢۗ۠۬ۧۖۢۖۧۘۢۦۢۤۨ۬ۚۜۘۘۢ۠ۨۚۗۢۘۘۧۨ۠ۦۘۛۦۛ" : "ۛۧۡۘۜۨۘۘۙۦۘۧۛۗۡۖۨ۫ۥۜ۟ۧۨۘ۠۬ۘۥۡۡۘۛ۬۬ۧۤ۠ۦۥۤۦۜ۫ۗۦۘۘۡۚۖۘۡۨۡۚۦۡۤۦۡۘ";
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case -820859998:
                                                                                        String str12 = "ۡ۟ۖۘۙ۫ۘۘ۬ۚۛ۠ۡۘۘۨۡ۟ۧ۬ۖۛۨۖۙۥۨۘۜۘۤۗ";
                                                                                        while (true) {
                                                                                            switch (str12.hashCode() ^ (-1602648083)) {
                                                                                                case -1082456314:
                                                                                                    str12 = "ۘۨۙۜ۬ۗۗۘۚۛۜۧۘۡۙۜۧۖۘۨۚۨۘۜۖۦۘۜۡۤۧۨۖۘ۠ۧۦۘۚ۟ۘۘۘۜۘۦۡۦۘۚۚ۠ۥۗۧۦ۬۟ۦۙۘ";
                                                                                                case -181875537:
                                                                                                    i2++;
                                                                                                    System.out.println(l2.decrypt("qL9U5qqexJ2W3myqr6XIg4fR\n", "8+wxiszKve0=\n") + i2);
                                                                                                    break;
                                                                                                case 439749408:
                                                                                                    String str13 = "۫ۡۖۦۛۖۙۧۦۘۜۚۘ۠ۨۨ۬ۗۘ۫ۤۦۜۧۘۘۧ۫ۧ۠ۙۤ۠ۙۨۘ۠ۦۦۘ";
                                                                                                    while (true) {
                                                                                                        switch (str13.hashCode() ^ (-2015720761)) {
                                                                                                            case -1662323258:
                                                                                                                str12 = "ۗۤ۟ۛۙۛۢۘۘۢۡۨۥ۠۟ۘۖۘ۬ۡۥۘۤۘۦۙۛۙ۬ۙ۬۠ۥۥۥۢۨۜۤۡۘۚۦۤ";
                                                                                                                break;
                                                                                                            case 108321717:
                                                                                                                str13 = "ۜۤۚۖۙۦۨۘۖۢۜ۬ۜ۠۬ۨۤۡۨۘۗۘۤ۟ۥۥۛۢۘۘۤۙۨۘۨۦۥ";
                                                                                                            case 234503922:
                                                                                                                str12 = "ۙۜ۠۟۬ۡۘۨ۟ۚ۟ۚ۠ۛۡۜۘۥۛۜۘۜ۟ۥۢۡۥۘۨ۠ۡۘۧ۟۠ۢۦۤۜۥۢۘ۟ۗۢۤۖۡۙۘۢۨۘ";
                                                                                                                break;
                                                                                                            case 295919804:
                                                                                                                str13 = ((WindowManager.LayoutParams) layoutParams).type == 2 ? "ۜ۠ۤ۫ۚۚۡۥۗۚۛۨۘۡۚۢۤۦۖۘۥۜۖۘۙ۫ۗۨ۫۬ۦۥۘۗ۠ۜۘ۫ۚۛۡۖۖۡۧۚ" : "ۗۥۗۧۡۙۧۗۚۖۗۗۖ۠ۨ۠ۥۤۥ۟ۛۡۜۙۧۗۘۛۗ۫۬۠۠۬ۜۘۚۗۦ۫ۚۖ";
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case 760469961:
                                                                                                    break;
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case -550682539:
                                                                                        str10 = "ۚۥۤۤۙۜۛۧۗۖ۠ۨ۬ۧۧۖۢۨۡۤۨۘۥۨ۟۫ۡۚ۫ۡ۫ۛۨۜۥۘۨۚۨۧ۬ۨۥ۟ۥۘۙۜۖۜۥۘۢۦۨ";
                                                                                    case 1117333997:
                                                                                        i = i2;
                                                                                        continue;
                                                                                }
                                                                            }
                                                                            break;
                                                                        case -1187696252:
                                                                            break;
                                                                        case 154075353:
                                                                            String str14 = "ۗۦ۟ۥۢۛۜۖۢۧۦۖۘۘۦۚۧۥۧۜۖۨۘۦۦۦۡۢۙ۬ۘۗۙۤ۟۠ۜۘۥۧۧۢ۫";
                                                                            while (true) {
                                                                                switch (str14.hashCode() ^ (-626845279)) {
                                                                                    case 452832401:
                                                                                        str9 = "ۥۛۦۘۨۥۗۥۜ۫ۤۢۜۘ۬۫ۨ۬ۗ۬ۜ۫۠۬۠ۥ۟ۜۜۖۘۘۤۙۙۦۥۜۘۧۘۚ۬ۙ۠ۤۗۢۙۥ";
                                                                                        break;
                                                                                    case 635205166:
                                                                                        str14 = ((String) tag).contains(l2.decrypt("qKQLemc1YE20uAt1fw90\n", "28xuFgtqED8=\n")) ? "ۤ۟۠ۜۗۙۡ۬ۥۘۤۨۨۘۗۛۥۘ۫ۦۜۘ۫ۚۜۦۢۘۦۜۘ۟ۤۡۗ۠ۨ۬ۤۗ" : "ۤۙ۟۟ۢۡۘۜۧۡۤۙۛۙۖۢ۫ۛۥۘ۠ۡۨۘۢۙۡۘ۬ۚۧۢۗۡۘۖۡۘۨۨۖۘۤۚۗۢۜۖۘۛۢ۫ۛۡ۠ۥۚۜۡۜۦ";
                                                                                    case 785301283:
                                                                                        str14 = "ۥ۠۠ۗۖۜۘۙۘ۟۬۠ۨ۟ۦۦۖۗۤۤۗۡۘۗۢۜۤۜۨۘۢۦۧۖۢۡۘ۫ۤۗۜۧۘۚۢۖ";
                                                                                    case 1559354715:
                                                                                        str9 = "ۚۛۦۤ۬ۥۘۙۜ۠ۖۗۘۘۨۚۜۙۜۡ۫ۢۚۥۤۜۘۖ۬ۨۘۤۢۜۘۤ۟ۦۘ۟۟ۨۛۗۥۘۜۦۡ";
                                                                                        break;
                                                                                }
                                                                            }
                                                                            break;
                                                                        case 775831808:
                                                                            str9 = "ۨۙۜ۫ۨۤۤۡۨ۟ۡۜۘۦۥۤۗۧۛۦۦۘۖۦ۟ۚۙۤۖۛۡ";
                                                                    }
                                                                }
                                                                break;
                                                        }
                                                    }
                                                    i = i2;
                                                    break;
                                                case 465400727:
                                                    String str15 = "ۛ۫۫ۘۙۥۘۡۖۦ۟۬ۨۛ۟ۢۛۚۘۘۧ۠ۦۘۚۥۡۤ۠ۙۗۚۦۡۡۧۘۙۘۛۨۦ۠ۗۛۨۘ";
                                                    while (true) {
                                                        switch (str15.hashCode() ^ (-1121118137)) {
                                                            case -2115299072:
                                                                str6 = "ۡۗ۬ۜ۟ۜۤۧۦۖۡۖۘۡۛۗ۠۫ۡۘۥۘۨۙۜۢۧۤ۟۟ۢۧ";
                                                                break;
                                                            case -1563567457:
                                                                str15 = view == null ? "ۡۨۤۡ۟ۥۦ۟ۨۘۚۨ۬ۦۨۥۘۙۖۖ۠۫ۖۢۗۨۦۢۗۛۢۖۘ۠ۢۛۗۗۘ۫ۗۘۘۥۚۙۥ۠ۜ۟ۨۢ" : "ۧۦۧۘۢ۬۬ۤۦۨۘ۟ۖ۫ۜۛ۠ۤۘۙۜ۟ۡ۬ۥۤۙۘ۠ۘۙ";
                                                            case -1204527799:
                                                                str15 = "ۨۖۨۘ۠ۘۖۘ۬۬ۥۘ۟ۡۙۢۦ۬۠ۡۖۘۨۘۡۦ۟ۧۡۛۦۗۖۘۛۜۖۚ۠ۚۙۢۖۗ۟ۤۨۥۢۧ۬ۡ۠ۨۙۚ۬ۜ";
                                                            case 1602624550:
                                                                str6 = "ۥۘ۫ۢۖۛ۬ۚۘۢۤۥۘ۬ۧۙۨۢ۟ۦ۠ۡۘ۠ۢۙ۫ۛ۬۫۬۫ۦۨ۫۠ۙۧ";
                                                                break;
                                                        }
                                                    }
                                                    break;
                                                case 2019167675:
                                                    str6 = "ۚۙۛۜۧ۫۬ۘۢۦۨ۠۠ۦۡۚۢۜۘ۠ۤۨ۬ۨۤۢۦۛۦ۫ۙۚۜۢۦۖۖۖۧۘۦ۫ۢ۬ۨۗۖ۟ۤ۬ۙۥۥۜۦ";
                                                    break;
                                                case 2042510786:
                                                    i = i2;
                                                    continue;
                                            }
                                        }
                                        i2 = i;
                                        i3++;
                                        break;
                                }
                            }
                        }
                        break;
                    case -35184455:
                        String str16 = "۫ۘۥۚۦۤۤ۠ۜۘۢ۫ۜۘۘۡۢ۠ۚۢۙۜۧۘۡۛ۫۠ۙۤۛۘ۬ۘۖۜۡۙ۠ۦۦۦۘ۟ۡ۠ۛۖ۫۫ۗۡۘۖ۫ۡۘۚۜۜۘ";
                        while (true) {
                            switch (str16.hashCode() ^ (-1259581702)) {
                                case -2121009613:
                                    str16 = "ۤۖۘۘۜۦ۠ۡۦۘۚۢۦ۠ۛۡۛۦۛ۫ۙۖۤ۟ۙۙۦۖ۟ۦۘۘ۬۟ۗۛۚۥۢۤۜۘۦۙۡۘ۫ۨۡۗۗۜۘ۫ۡ۬ۘۚ";
                                    break;
                                case -180321494:
                                    if (strArr != null) {
                                        str16 = "ۙۗۜۘۤۢۨ۟ۤۤۤۥۚۛۡۘۚۚۨۘۢۗۖۘ۫۬ۗۤ۟ۖۤۜ۬۟۬ۜ۠ۙ۫۬ۢۚۜۤۙۖۘۥۘ۬ۦۡۘ۬۠ۥۘ۠۫۬";
                                        break;
                                    } else {
                                        str16 = "ۚۦۤ۬ۤۗۘۛۨۘۨۦۗ۠ۘۧۘۚۨۗۜ۟ۨۘۦ۠ۘۜۢۥۘۡۗۦۘۨۥۡ۫۫ۡۜۚۡۘۚۦ";
                                        break;
                                    }
                                case 1025358622:
                                    str = "ۢ۠ۢۚۚۜۘۤۦۗ۫ۗۘۥۥۙۛۙۡۧۤۖۘۛۥۛۤۙۧۙۙ";
                                    continue;
                                case 1128154233:
                                    str = "ۘۢۨ۫ۡۤ۬ۡۘۛۥۘۘۜۨۥۘ۠ۦۥ۠۫ۜۧۥۡ۬ۡۛ۟ۙۚۤۘۛۢۚ۟۟ۡۜۤۛ۠ۡۤۢۤ۫۫ۚ۫۟ۘۤۥۘ";
                                    continue;
                            }
                        }
                        break;
                    case 639723116:
                        str = "۫ۖۧۜۜ۟ۤۖ۠ۛۖۧۨ۟ۤۧ۠ۖۘۧۡۜۙ۠ۜۘ۠ۛ۠ۥۛۜۦۦۨۘ۬۠۬ۜۤ۬ۜ۫ۘۘ";
                        break;
                    case 1270207692:
                        System.out.println(l2.decrypt("dyLpQa9sV+ZJQ9ENv1FL4WIQ4Ui6BUDjQB0=\n", "LHGMLck4LpY=\n"));
                        return false;
                }
            }
        } catch (Throwable th) {
            System.out.println(l2.decrypt("iA54zYWb8lO2b0CBBnMJxmvl8h15\n", "010doePPiyM=\n") + th);
            th.printStackTrace();
            return true;
        }
    }

    private boolean isAllNeededPopupsClosed() {
        String str = "ۗۚ۠ۖۖۨۘۚ۫ۖۦۧۙۨۧۜۘۥۜۚ۠ۦۚۜ۬ۗۖ۬ۖۗۡۘۙۥۚ۬ۜۘ۫ۚۧ۫ۡ۫ۧۥۧ۬۟ۦۘۚۙۘۚ۫ۗ";
        while (true) {
            switch ((((str.hashCode() ^ 304) ^ 133) ^ 374) ^ 1069121532) {
                case -1616008934:
                    return false;
                case -1495796092:
                    str = "ۡ۫ۥۘۙۗۦۘۖ۟ۧۢۜۘۡۢۜۘ۠ۨۥۘۦۙۖۘۜۘۦۘۘۧۖۘۦ۬ۢۚۧۧۖۙۢۘۜۜۘۛۚۘۡۢۨۤ۟ۜۖۘۖۘۨۥ۟";
                    break;
                case -1199427524:
                    return true;
                case -995470340:
                    String str2 = "۬ۖۨۡۘۙ۫ۙۜۘۛۜۖ۠ۢۦۘۧۙۚ۫ۧۜۘ۠۫ۘۘ۟۫۟۠ۛۚ";
                    while (true) {
                        switch (str2.hashCode() ^ (-1026406839)) {
                            case -1197183056:
                                str2 = "ۙۨۨۜۧۥۘ۫ۛۙۜۘۘۘ۫ۨۤۘۘۛۥۤۨۘۡۤۚۛۗ۫۠ۖۡۘۨۗۥۘۜۖۡۘ";
                                break;
                            case -682651647:
                                str = "ۨۜ۫ۖۥۢۧۨ۬ۥۤۘۥۨۦۘ۬ۨۢۢۗۦ۟ۨۚۚۧۘ۬ۛۜۘۜۢۘۛۖ۬ۚ۠ۤۧۨۘ۠ۜۥ۟۬ۘۥۥۢۚۘۜ";
                                continue;
                            case 1036114453:
                                String str3 = "ۧ۫ۚ۠۠ۚ۬ۙۥۘۖۘۧۘۦۡۢ۫ۗۡۘ۫ۖۖ۠۠ۛ۬ۥۛۚۘۘۨ۟ۖۥ۫۟";
                                while (true) {
                                    switch (str3.hashCode() ^ (-1669971767)) {
                                        case -1756910269:
                                            str2 = "ۨۖ۬ۡ۟ۡۘ۠ۤۦۘۙ۬ۖۘ۫ۘۘۘۖۤ۫۫ۧۡ۫ۢۖۘۘۦۘ۟ۛۡۘۡۨۢ۬ۧۜۛۛ۬۫ۢۖۙۛۥۘۤۤۧ";
                                            break;
                                        case -1471560430:
                                            str3 = "ۖۧۡۘ۠۬ۨۘۖۦۜۘۦۧۧۤۘۨۘۢ۠ۡۘۡ۫ۡۘۦۧ۬ۨۚۨۚۤۖۘۘۛۘۘۙ۬۟ۧۖۖۧ۟ۢۜۘۧۘ۫ۘۨ";
                                        case -482922752:
                                            str2 = "ۤۖ۫ۢۛۖۚۛۤ۟ۥۧۥۥۚۨ۫ۘۤۤۗۙۙۖۡ۬ۖۘۡۦ۠ۘۛۥۘ۬ۙۘۘ۫ۦۜۨۤۥۘ۬ۨۘۘۚۗۙۧۚۦۘ۠ۖۙ";
                                            break;
                                        case 1336185372:
                                            str3 = !k2.closedHtmlPopupIds.containsAll(t3.needHtmlPopupIds) ? "ۖۛۙۗ۬ۧۥۙۧۨۡۨۨ۫ۛۘ۬ۙۧۥۙۤۦۘۦۛۡۘ۫ۤۢ" : "ۡ۫ۡۛ۫ۚۚۘۦۘ۟ۗۙۢۥۜۘۘۡۙ۫ۡۘۚۥۡۧۡۥۜۢ";
                                    }
                                }
                                break;
                            case 1735394240:
                                str = "ۛ۬ۜۘ۠ۤ۠۟ۖ۫ۦۘۖۘۧۛۖۢۨۜۜۢۘۘ۫ۜ۫ۜۘۧۛۛ";
                                continue;
                        }
                    }
                    break;
                case -399210760:
                    return false;
                case 221420240:
                    String str4 = "ۖ۫ۜۘ۟ۖۘۘۖ۬۬ۨۘۘۛۨۢۦ۫ۨۘ۠ۡ۟ۥۧۘۘۧۥۖۧۧ۫ۙۜۘ۬ۚۦۘۛۦۙۚۧۛۖۦۦۘۥۥۘۘ";
                    while (true) {
                        switch (str4.hashCode() ^ (-1998229446)) {
                            case -2020036274:
                                String str5 = "۬ۚ۟ۥ۬۠ۨۜۢۧ۫ۨۘۖۖۡۘ۠۠ۜۘۦۖۢۤ۠ۦۘۗۥۛ۟ۙۛ";
                                while (true) {
                                    switch (str5.hashCode() ^ (-68361618)) {
                                        case -1420548254:
                                            str5 = "ۙۜۦۨۦۨۘۘۢ۟ۦۘ۬ۚۦۚۚۜ۟۟۬۟ۛۤۗۜۛۜ۫ۧ";
                                        case -53839168:
                                            str4 = "ۢۨ۬ۧۚۖۢۘۥۘ۠۠ۤۨ۬ۘۧۜۜ۫ۦۧۘۘ۠ۖۗۖۖۚۚۙۗۥۘۚۧ۫ۗۨۜۙ۠۬ۡۦۤۚ";
                                            break;
                                        case -53834314:
                                            str4 = "ۜۦۘۘۖۚۙۖۡ۟ۗۙۧۨۛۖۗۤۥۘۤۦۡۜۚۦۙۖ۠ۚ۬ۜۘۤۤۦ۬ۛۜۘۜۜۦۘۚۥۗ";
                                            break;
                                        case 312413769:
                                            str5 = !k2.closedImagePopupIds.containsAll(t3.needImagePopupIds) ? "ۗۦۖۘۛۧۥ۟ۨۜ۠۬ۜۘۡۖۡۘۦۤۖۘ۫ۥ۫۫۫ۤ۬۬ۛ۫ۤۨۡ۟ۜۢۢۖۥ۫ۘۖۖۘۥۦۘ۠ۢۡ" : "ۤۛۥۘ۟۠ۘۡ۟ۗۙۨۘۘۢۘۦۘۚۗۦۧ۟ۥۘۛۖۘۗۥۗۖۡۖ";
                                    }
                                }
                                break;
                            case -1830546928:
                                str4 = "ۜ۫۟ۖۡۨۖۛۙۚ۫ۨۘۛۢ۠ۚ۬ۡۘ۠۟۬ۖۛ۠ۨ۠ۦۛۘۖۘ";
                                break;
                            case -825296538:
                                str = "۟ۡۢۘۙۥۘۘۘۨۘۚۛ۫ۖ۠۠۬ۨۛۗۙۗۛۤۚۛۜۢۥۘۥۢۘۨۘۦ۫ۖ۠ۢۜۥۡۜۘ";
                                continue;
                            case 1829156543:
                                str = "ۖۖۛۢۘۡۘۚۥۜۘۡ۬۟ۛۘۡۨۘۛۚۢۡۦۨۢۗۥۤ۟۬ۗ";
                                continue;
                        }
                    }
                    break;
                case 442640356:
                    String str6 = "ۚۖۦ۟ۘۥۘ۟ۙۙۧۤۥۡۙۡۢ۫ۨ۟۬ۥۤۚۡۘ۠ۤۨۘۡۜۤۖ۫۟ۡ۫ۦۡۜۚۢۛۢۘۨۜ۠ۛ";
                    while (true) {
                        switch (str6.hashCode() ^ 358517830) {
                            case -1390666037:
                                str6 = "ۨۜۖۘ۠ۛۖۘۤ۬ۨۘۧۡۨۘۦۦۘۙۚۨۖۚ۫ۦۜ۠ۖۚ۬ۖۦۥۤۘ۫ۚۤۦۥۖۘۢۗ۠";
                                break;
                            case -1340328222:
                                str = "ۘۡۙ۬۠ۙۖ۟ۨۜ۟ۨۧۙۚۦۨۘ۟ۦ۫ۙۘۘۛۗ۠ۧۤ";
                                continue;
                            case 75079579:
                                str = "ۧۦۥۡ۠۫ۜۙۜ۠ۨۜۘ۟ۘۖ۬ۡۢۢۘۦۛ۫ۘۛۙۦۗۦۦ";
                                continue;
                            case 1845630240:
                                String str7 = "ۘۦۨ۟ۧ۬ۧۨۜۘۙۚۚۡۧ۫ۘ۬۫ۨۘۗۖۦۧۘۡۧ۫ۛۗۨۘۡ۠ۛۘۚۧۖۚۡۘۦۧۜۘ";
                                while (true) {
                                    switch (str7.hashCode() ^ (-408938828)) {
                                        case -1962422118:
                                            str7 = "ۥۢۙ۟ۙۗۨۤۖۜۘۛۦۨۥ۟ۜۘۥ۠ۡۗۗۡۗۡۘۘۜۜۗۦۙ۟ۦۘۖۡۚۙۤ۟ۜۘۨۛۚ۟ۚۧ۫۟۟ۘۨ۠";
                                        case -1223366719:
                                            str6 = "ۖۤۡۘۦۦۡۘۡۖۖۦ۫۟ۡۜۦۘۛۜ۠ۜۘۧۘۗۘ۟۫ۖۗۨ۫۠ۨۥۘۥۤۢ";
                                            break;
                                        case -1006784518:
                                            str7 = !k2.closedMessagePopupIds.containsAll(t3.needMessagePopupIds) ? "ۖۢۡۘ۬ۤۛۥۘۘۘۢۢۤۧۖۘۖۖ۬ۧۘ۬ۛ۫ۖۧۗۧۦۘۘ" : "ۜۨۨۘۦۜ۠۟ۛۖۘۛ۫ۤۨۘ۫ۜۙۙۥۤۘ۠ۥۖۦۡۜۨۢ۟ۖۦۗ۫۟ۚۨۛۙۦ۫ۘۧۖ۟۟ۖۥۖۥۘۘۜۛۛ";
                                        case 1820687082:
                                            str6 = "ۘۖۢۦ۟ۨۘۜ۟ۨۘۡۦ۠ۤۖۦ۠ۨۘ۟ۧۖۥۜۜۜۧۨۧۧۥۘۨۥۖۘۛۗۚ۠ۧۗۡ۟ۡۨۥۖۘ۟ۡۜۘۤۨۘۢ۟";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case 820983659:
                    return false;
                case 1499878679:
                    return false;
                case 1620297179:
                    return false;
                case 1746241722:
                    String str8 = "۠ۚۛۧۗۡۘۡ۬ۥۘۘ۟ۢۖ۠ۧۘۗۢ۠ۙۨۚۖۛۛۨۘۙ۟ۖ";
                    while (true) {
                        switch (str8.hashCode() ^ (-310208872)) {
                            case -947839271:
                                str = "ۜۦۙۖۢۥ۫۠ۨ۫۠۫ۚ۬۟۠ۖۨۘ۫ۨۢۚۚۨۘۛۡۜۛۥۦۥ۟۟ۦ۫ۜۘ۬ۖۘۢۖ۬ۙۧ۟ۗ۬ۦ";
                                continue;
                            case 99686705:
                                str = "ۨ۟ۨۨۗۥۙۢۧۗۤۛۚ۬ۢ۠ۨۜۛۡۢۜۢۡ۟ۜۜۘ۠ۗۡۘۘۧۖۗ۠ۙ";
                                continue;
                            case 1887162152:
                                String str9 = "ۤۦۥۧۗۢ۠ۥۜۤۜۛ۟ۙۢۚۥ۟۬ۘ۠ۙ۠ۙۚ۟ۜۘ۫ۚۘ";
                                while (true) {
                                    switch (str9.hashCode() ^ 792244523) {
                                        case -1952046486:
                                            str9 = "۫ۜ۫۠ۢ۬ۖۡ۠ۧۚۡۢۚۚۤۤۥۘ۬۟ۢۦۥۥۘۖ۠ۡۘۡۡ۠۬ۛۗۜ۟۬۫ۛ۬ۚۜۖۗۦ۠ۗۖۥ";
                                        case -1907131736:
                                            str9 = !k2.closedTextPopupIds.containsAll(t3.needTextPopupIds) ? "ۗۘۘۘۧۧۜۧ۠ۖۨۤۦ۠ۨ۟۠ۢۜۧۖۖۜ۬ۥۘۤۜۧۘۚۘ۬ۚۘ۟ۤۥ۠ۥۗۤ۬ۙۜ" : "۫ۗ۬ۥۙۧۡۚۥۘۜۦۡۘۡۘ۟ۖۡۘۗۚۛۨۛۦ۠۫ۨۦۨ۟ۜۦۦۚۦۘ";
                                        case -1509953581:
                                            str8 = "ۜۛۜۘۜۛۛۦۦۖۗۜۢۡۡۖۘ۬ۦۘۨۚۥۘۜ۠ۗۖۖۘۥۦ۠ۤۤۙۨۘۧۙۛۡۥۛۡۘ";
                                            break;
                                        case 932734235:
                                            str8 = "ۢۦۨۘۜۦۘۘۗۜ۟ۡۛۜۜ۫ۧۥۡۘۖ۟ۙۖ۫ۥ۟ۛۧۘ۬ۖ";
                                            break;
                                    }
                                }
                                break;
                            case 1889394037:
                                str8 = "ۥۦۗۦۚۛۧۤۡۛۧۘۘۚۧۘۘۥۜ۬ۥۘۗۧۡۥۘۛۨۘۖ۟ۜۘۡ۟ۖۡۧۡۘۗۗۤ۟۬ۨۡۖۨۡۙۖۘ";
                                break;
                        }
                    }
                    break;
                case 1935495884:
                    String str10 = "۬ۤۥۘۨۗ۫۬ۗۧۚۦۡۘۖ۟۟۟ۙۛ۬ۚۤ۫ۤ۟۠ۤۦۘۗ۫۠ۜ۫ۚۦۜۘۚۙۙۧۘۡ";
                    while (true) {
                        switch (str10.hashCode() ^ (-84531567)) {
                            case -2030837525:
                                String str11 = "ۥۦۧ۬۬ۘۘ۠ۘ۠ۚۜۜۢ۠ۢۚ۬ۖۘۚ۬ۙۧۖۡۢۖۛۡۨۖ۫ۜۛۚۢۦۘۢ۠ۜۘ۫ۥۘۛ۠ۜ۠";
                                while (true) {
                                    switch (str11.hashCode() ^ 1106132929) {
                                        case -2083632288:
                                            str10 = "۬۫ۖۘۢۛۖۘ۟ۖۖۘۥۚۚۙۨۦ۠ۢۨۘۙ۠ۦۙ۫ۚۢۤۢۘۨۚۤۦۨۛۢۦۘۧۨۜ۫۫ۢۛۥۤۙۚۨۘ";
                                            break;
                                        case -532801539:
                                            str11 = !k2.closedPopupIds.containsAll(t3.needFullscreenPopupIds) ? "ۘۡۚۡۗۤۗۢ۟ۢۙۘۛۜۧۜۤۙۡ۟۟۬ۧۘۚۦۥ۠۟ۘۘۡۖۗۗۖۚۛۦۘۘۤۜۜۨۢۧۦۖۗ" : "۟ۤۘۢۡۥ۠ۛۦۙۢۦۛۘۨۘۧۤۡۗۘۨۘۗۙۖۢ۬۫ۜۤ۠ۢۛ۫ۦ۟ۜ";
                                        case -211910536:
                                            str10 = "ۘۥۨۘۚۥۜۢۧ۫ۧ۟ۗۖۥۡۘۡۧ۠۫ۘۨۘۙ۟ۖۘۥۘ۬ۗۚ۟";
                                            break;
                                        case 1906645682:
                                            str11 = "ۢ۟ۦۘۧۥۘۘۚۡۥۘۨۜۡ۫۫ۙۙۖۘۘۛ۠۠ۧۤۧ۬۫ۦۙۤۗۨۨۨۘ۟۬ۘۘ";
                                    }
                                }
                                break;
                            case -758325796:
                                str = "ۘۦۦۚۦۙۜ۫ۥۘۛۡۛۛۗ۠ۥۚۜۧۜۧۘ۫ۨۢۡۧۧۘۖۜۘۛۡۤ۟ۦۘۘۙۜۛ۟ۡۗ";
                                continue;
                            case 155873248:
                                str10 = "ۗ۠ۤۚۤۨ۫ۚ۬ۚۗۥۦۢ۫ۖۡۥۛۘۧۘۙۛ۬ۧۧ۠۬ۖۦۘۚۘۘۥۖ۟";
                                break;
                            case 1888360798:
                                str = "ۙ۬ۦ۠۬ۦۖۦ۠ۘۨۘۖ۫ۙۜۚۙۥ۠۠ۗۜۙ۠ۦۖۡۥ";
                                continue;
                        }
                    }
                    break;
            }
        }
    }

    private void launchNextActivity() {
        String str = "ۛۦ۫ۥ۫۠ۨۥ۟ۗۧۜۘ۠ۢ۟ۙۥۚۢ۠ۘۘۤۚۡۘۡۛۦ۟ۖۡۦۤ۫ۘۜۖۘۤ۫۫۫ۥۖۦۘۘۗۧ۠";
        while (true) {
            switch (str.hashCode() ^ (-58878276)) {
                case -1819409892:
                    String str2 = "ۙۚۦۘ۫ۜ۬ۤۡۥۘۗۢۧۜ۟۟ۤۨۜۥۦۜۢۤۥۜۗۜۡۘۡۤۧۤۧۦۘ";
                    while (true) {
                        try {
                            switch (str2.hashCode() ^ 2089224284) {
                                case -163056861:
                                    break;
                                case -42875045:
                                    str2 = "۫۫ۦۨۘۧۘۘۦۥ۠ۥ۟۬ۙ۫ۗۦۘۡۚۦۧۘۥۘۘۘۘۡۨۛۤۥ۬۠ۛۛۘۙ۠ۨۘ";
                                    continue;
                                case 610560725:
                                    String str3 = "۟ۘۖۚ۬ۢ۠ۧ۫ۚۡۧۦ۠ۜۡ۬ۖۚۘۤۥۢۙۗۥۥۛۨ۬ۚ۟ۥۘۢۤۦ۬۠۟ۧ۠ۥۘ۬ۨۘۘۨۖۖۘۗ۫ۡۘۚۡۖ";
                                    while (true) {
                                        switch (str3.hashCode() ^ 1389403170) {
                                            case -1902444795:
                                                str3 = "۠۠۠۬ۨۖۥۦۛۤۙ۠۠ۖۖۛۢۨۘۜۗ۠۬۬ۨۘۗ۬ۜۘ۟ۨۧۧۡ۟ۗۨۧۘۗۗۚۚۧ۬ۙۖۨۘۦ۠ۧۙۨۥۘ۠ۘۜ";
                                                break;
                                            case -1587121432:
                                                str2 = "۟ۧۧۙ۟۫ۦ۬ۘۘ۠ۦۖۘ۟ۗۦۙۛ۟ۘۡۛۗۜۨۘ۬ۡۘۘۨ۬ۖۘۛۗۜۘۚۘۦۘ";
                                                continue;
                                                continue;
                                            case -785557547:
                                                if (fcRuQsQrcxOAzxwEalcM.LAUNCHER == null) {
                                                    str3 = "ۖۤۡ۫ۘۙۨۨۜۘۧۧۜۘ۠ۧۥۘۢۜۜ۬ۧ۟ۙۜۥ۠ۨۨۘۗۛۡۘۛۥۘۘۙ۫ۖۘ۫ۤۦۖۤۥۙ۫ۚ۫ۢ۠ۖ۠ۢۧۤۤ";
                                                    break;
                                                } else {
                                                    str3 = "ۢۡۖۚ۫ۘ۬ۢۙۨ۬ۦۘ۟ۢۖۛۗۚۧۦۘۢۡۥۚ۬ۥۘۥۨ۟ۗۜۘۦۦۡۜۜۡۘۚۛۨۘۨ۫ۖۘ۬ۛۧ";
                                                    break;
                                                }
                                            case -77785918:
                                                str2 = "ۥۨۨ۠ۥۚ۟ۖۘۘۢۗۨۜ۫ۤۘۦ۟ۛۘۦ۠ۘۜ۠ۛۡۧ۠ۜ۬ۢ۠ۢۗ";
                                                continue;
                                        }
                                    }
                                    break;
                                case 1514392734:
                                    String str4 = "ۛۨ۬ۚۢۜۖۢ۟۟۫ۧۜۘۗۥۧۘ۬۟۬ۦۚ۟ۘۖۧۛۖۘۖ۫ۛۘ۫۟ۢۡۨۖ۠ۡ۬ۦ۫ۗۧۨۘ";
                                    while (true) {
                                        switch (str4.hashCode() ^ (-963654909)) {
                                            case -1294229572:
                                                String str5 = "ۙۡۖۥۧۘۡۖۚۢۨۘۢۘۘۘۖۤۡۘ۫ۨ۫ۦۗۡ۬ۢۘۘۜۤۛ";
                                                while (true) {
                                                    switch (str5.hashCode() ^ 1280032424) {
                                                        case -1613453971:
                                                            str5 = "ۤ۠ۘۘ۟ۜۤ۟۟۫ۡ۫۬ۜۤۧۢۨۥ۬ۡ۟ۤۗۙۜ۬ۜۥ۫۬ۥۚۢۦۘ۠۫ۙۘۡۨۨۘ";
                                                            break;
                                                        case -1162698537:
                                                            str4 = "ۖۛۤ۟ۡۤۢ۠ۖۤۛۚۜۜۧۘۤۢۦۘۨۚۘۘۗ۟ۜۘۢۚۨۘۦۛۜۘۧۢۧ۠ۗۗۡۜۙۖۛۧۡ۟ۖۙۙۢۡۧۘۖۡۢ";
                                                            continue;
                                                        case 105750947:
                                                            if (!l2.decrypt("V2azIg==\n", "ORPfTrcGjdE=\n").equals(fcRuQsQrcxOAzxwEalcM.LAUNCHER)) {
                                                                str5 = "ۜۡۢۨ۬ۤۖۚۤ۠ۖۘۡ۠ۦۘۘۙ۟ۡ۠ۥۘۦۨۧۘ۫۫ۙ۫ۖۜ";
                                                                break;
                                                            } else {
                                                                str5 = "ۨ۠۟ۦۤۖۘۚۤۡۡۢۘ۠ۛۙ۠ۖۨۧۗ۟ۨۘۘۛۤ۬۬ۥۡۘ";
                                                                break;
                                                            }
                                                        case 1458882912:
                                                            str4 = "ۚ۟ۜۖ۫ۗۗۛۗۡ۬۬۠ۧ۬ۢۢۧۥۥۘۘۡ۫۟۟ۧۛۡۨ۬ۛۙ۫ۢۡۘۗ۠ۜۛۙ۫";
                                                            continue;
                                                    }
                                                }
                                                break;
                                            case 121334033:
                                                str4 = "ۥۡۗۨۧۖۘۡۤۚۤ۠ۡۘ۫ۙۨۘۡۘۢۢۖۜۧۜ۟ۚۙۥۘۘۡۘۡ۬ۙۤ۠۫ۛۖۘۖۦۘ";
                                                break;
                                            case 1297516166:
                                                break;
                                            case 1961654699:
                                                String str6 = "ۡۘۥۢۦۗۛۛ۫۫ۡۘۥۗۛۜۡۧ۬ۧۨۦۧۨۘۗۦۗ۠۟ۗۨۛۚۢۥۗۦ۟ۡۥۡۛۚۤۙۖۨۜۘ";
                                                while (true) {
                                                    switch (str6.hashCode() ^ 479578282) {
                                                        case -96992682:
                                                            break;
                                                        case 44841450:
                                                            String str7 = "ۥۚۙ۫ۨۥۢۤۡ۬ۙ۬۟ۧۡۘ۬ۤۘۘۘۘۨۢۥۤۚ۬ۡۢۡۥ";
                                                            while (true) {
                                                                switch (str7.hashCode() ^ 1438594020) {
                                                                    case -1371566309:
                                                                        str6 = "ۘۚۤ۟ۙۡۚۘۨۘ۟۫ۘۢۡ۬ۙۗۜۖۚۨۘۘۥۤۙۥۖۘۘۛۦ";
                                                                        continue;
                                                                    case -669728113:
                                                                        str6 = "ۡ۬ۗ۬۫ۥۗۘۙۙۛ۟ۦ۟ۨۘ۬ۦۜۙۨ۠ۨ۫۫ۡۙ۬ۚۜ۠۫۫ۖۘۤ۟ۘۘ۬۟۠ۜۢۚ";
                                                                        continue;
                                                                    case 604500053:
                                                                        str7 = "ۦۤۖۡۚۡۨۜۘ۬ۢۧۛۤۙۜ۬ۥۦۤۡ۫ۢۙۚ۬ۜ۫ۤ۬ۢ۟ۦ۫ۢۡ";
                                                                        break;
                                                                    case 1456044847:
                                                                        if (!l2.decrypt("3Ry9xU5giKnDbdLZ\n", "hj/xhBsuy+E=\n").equals(fcRuQsQrcxOAzxwEalcM.LAUNCHER)) {
                                                                            str7 = "۟ۘۨ۬۬ۛۧۨۖۚۨۚۙ۟ۜۘ۫ۦ۟ۜۡۘۨۧۜ۫ۗۡۢ۫ۡۤ۫ۖۛۢۨ۟ۤ۟ۢ۫ۢۜ۟ۘۛۢۤ۟ۢۜۘ۟ۙۥۘ";
                                                                            break;
                                                                        } else {
                                                                            str7 = "ۚۤ۫ۙ۟۬ۙ۠ۜ۫۟ۜ۬۬ۜ۟۠ۢ۟ۨۖ۟ۤۦۧۡۤۜۚۖۥۧ۠۬ۚۢ۬ۜۜۙۙۦۧۘ۬۫ۘۘ";
                                                                            break;
                                                                        }
                                                                }
                                                            }
                                                            break;
                                                        case 885325909:
                                                            str6 = "ۧۚۚۨۖۡۘۤ۟ۘۘۨۥۦۡۡۖۘۘۘ۠ۗ۬ۛ۬ۚۘۡۗۡۘۖۘۘۜۘۦۘۛۘۨۘۧۤۦۘۥۥۡۛۦۘ۫ۗۢ";
                                                            break;
                                                        case 2095450109:
                                                            String strDecrypt = s0.decrypt(fcRuQsQrcxOAzxwEalcM.LAUNCHER, l2.decrypt("doBKYrVw4W1+ghg04yKzMw==\n", "R7J5VoBG1lU=\n"));
                                                            Intent intent = new Intent();
                                                            intent.setClassName(getPackageName(), strDecrypt);
                                                            startActivity(intent);
                                                            finish();
                                                            return;
                                                    }
                                                }
                                                break;
                                        }
                                    }
                                    break;
                                default:
                                    continue;
                            }
                        } catch (Exception e) {
                            System.out.println(l2.decrypt("PFK4H3dRNUQPe7oBRBK1sNT2ZMb8jt/i36Y21oM=\n", "Zx7ZahkyXQc=\n") + e.getMessage());
                        }
                    }
                    Toast.makeText(this, l2.decrypt("q6T2nqYv1A7a083TySOAXPqR\n", "TjRZeyyHMbQ=\n"), 0).show();
                    System.out.println(l2.decrypt("1D9cUIxeBU/nFl5Ovx2InCCWt40HmdzkO9bSmXhxLFnBMHVgsB2Lmy+VqK0=\n", "j3M9JeI9bQw=\n"));
                    this.handler.postDelayed(new n0(this), 1500L);
                    return;
                case -1563663490:
                    System.out.println(l2.decrypt("G8XSkxy8vo8o7NCNL/8zcPluGXGXb0wq3CNWY9o2VWSlDAAP5XI5cMxuCEGVZHsr7QBWWPc=\n", "QImz5nLf1sw=\n"));
                    this.handler.postDelayed(new m0(this), 300L);
                    return;
                case 109693883:
                    String str8 = "۬ۧۧ۫ۦۜ۠ۨۨۥ۟ۚ۟ۜۦۘۢۦۢ۟ۥ۫ۡۤۧۧ۬۫ۤ";
                    while (true) {
                        switch (str8.hashCode() ^ 119105895) {
                            case -464040173:
                                str8 = "۟۬ۦۡ۫ۢۙۡ۫ۛۜۘۘۢ۬ۥۚ۬ۢۛۤۜۗۧۚ۫ۤۦ۬۠۫ۨۨۧۛۖۛۖۙۥۛۨۨۘۡۧۨۜۚۦ۫ۘۧۥۗۜۘ";
                                break;
                            case 91393235:
                                str = "ۡۥۜۧۙۜۜۥۘ۬ۚ۫ۡۗۗۨۡۧۘۡۢۨۚۨۜۢۥۧۘۛۡۘۘۖ۫۠ۚۢ۫ۗۦ۫ۢۚۙ۟ۖۡۘۨۚۡۘ";
                                continue;
                            case 952668400:
                                if (!isAllNeededPopupsClosed()) {
                                    str8 = "ۗۦۧۤۢۨۖۖۤۤ۠ۨۘۙ۫۬۫ۗۨ۠ۜۨ۟۫ۡۤۙۚۜ۬۠۠ۥۘۚۡۨۚۚۦۘ۫ۢ۟";
                                    break;
                                } else {
                                    str8 = "ۜ۠ۜۘ۬ۢ۟ۖۢۚۧۜ۬ۗۗ۬ۗ۟۬ۧۦ۬ۥۘۘۙۨۡ۟ۨۦ۫ۖۖۘۢۛ۫ۥۢۦۘۖۧۜۘ";
                                    break;
                                }
                            case 1142020944:
                                str = "ۜ۫ۢ۠۬ۥۘۗۦۡۘۙۜۚۖۢ۬ۖ۫ۥۘۧۘۖ۠ۤ۟۬ۢۥۘ۟ۗۦ۫ۗۘۘۦۚ";
                                continue;
                        }
                    }
                    break;
                case 605695098:
                    str = "۫۫ۨۖۥۜۚ۟ۡۘۡۨۜۘ۫ۥۧۘۘۘۢۖۢۖۡ۬ۨۘۘ۠۠ۥۚ۫۫ۛۖۜۛ۟ۨۨۜۚۘۙۘۗۗۘۛۜۘۖۛۙۢ۟ۥ";
                    break;
            }
        }
    }

    private void resolveSystemThemeColor() {
        String str = "۫۟ۚۗۗۨۜ۟ۘۚۥ۟۫ۨ۟ۘۗۜ۬ۡۖۘۨۘۥۡۡۜۧۜۘۢ۟ۗۦۗ";
        int i = 0;
        int i2 = 0;
        int color = 0;
        boolean z = false;
        boolean z2 = false;
        while (true) {
            switch ((((str.hashCode() ^ 233) ^ 491) ^ 706) ^ (-956918752)) {
                case -1968698741:
                    String str2 = "ۙۖۘۘ۫ۘۤۚۦ۟ۛ۠ۗ۠ۨۗۤ۫ۖۡۨۖۚ۠ۚۚۡۛ۫۫۟ۨ۟ۢۜۡۘ";
                    while (true) {
                        switch (str2.hashCode() ^ (-442300108)) {
                            case -1977610027:
                                str = "ۘۢۨ۠ۨۜۘۛۨۚ۬ۦۨۖۦۧۘ۬ۖ۠ۜۢ۬ۚ۠ۦۘ۠ۡۛۨۦۨ۬ۜۛۙۜۘۘۦۖۗ۠ۜۡۘۤۢۙۗۜ۫ۚۖۨۙ۫ۤ";
                                continue;
                            case -823313536:
                                str2 = "ۗۜۗ۟ۚ۫ۨۜۗۙۦۨۤ۠ۡۘۘۧۡۘ۟ۥۧۘۡ۟ۙ۟ۤۜۥۚۛۖۜۛۖۦۖۨۙۤۤۗۨۘۘۗۥۘۙۚۖ۟ۚۡۘۙۜۗ";
                                break;
                            case -104016652:
                                str = "ۧۗ۟ۨۛۜۚ۟ۖۛ۟ۘۘۖۢۨ۫ۡۥۘ۬ۘۘۘ۠ۛ۟۫ۦۘۘ۠ۤۖۘ۫ۘۘۖ۫ۛۜ۟ۨۘ۟ۧ۬ۧۚ۬ۙ۟ۥ";
                                continue;
                            case 301632357:
                                String str3 = "ۜۜۥۚ۫ۛ۠ۧۥۜۗۖۤۖۘۛۡ۫۟ۙ۠ۧۥۘۨۗۖۢۜۨۘ۟ۖۦۘۙۘ۠ۦ۬ۥ۠ۦۘۤۘۥۘۖ۬ۦۘۥۙۜۛ۠ۦ";
                                while (true) {
                                    switch (str3.hashCode() ^ (-2120479596)) {
                                        case -1450250621:
                                            str3 = (getResources().getConfiguration().uiMode & 48) == 32 ? "ۨۗۘۦۢۡۘۚۦۥۢ۫ۘۘۛۧ۠ۨ۫ۘۡ۬ۧۗۥۘۘۛۦ۫ۚۨۥۛ۠ۨۘۧۡۥ" : "ۥ۫ۡۜۡ۫۫ۚۤۛ۟ۜۘۙ۟ۥۘۗۡۦۢۙۜ۟ۡۦۦۨۖۘۛۨۡۘۥۦۢۥۚ۬ۡۤۢ۟۬ۖۘ۬ۜۢ۬ۛ۠ۨۖۗ۠ۤ۬";
                                        case -1341030590:
                                            str2 = "ۤۥۖۚۦۚۢۛۗۥۤۨۘۖۜۚۖۥۧۘ۟۬ۥ۫۫ۡۘۥ۟ۢ۠ۘ۬ۙۧۙۨۗۥۚۥۥۘ۫ۡۚۦۙۥۧۙۥ";
                                            break;
                                        case -19241914:
                                            str2 = "ۛۜ۬۫ۡۛۦۛۨۤ۠۠ۡۛۦۘۖۡۘۘۦ۬ۦۧۡۘۢۦۢۗۤۡۘۡۛۦۘۙۛۤ";
                                            break;
                                        case 1616565672:
                                            str3 = "ۖۜۚۙ۠ۡۤۨۛۙۨۗۧۧ۫ۖۖۖۜۧۤۨۘ۠۠ۧۤۦۨۘ";
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case -1949993374:
                    str = "ۚ۬ۘۘۜۗۦۘ۫ۜۧۦۡۘۗۡۡۘۨ۠ۜۘۘۘ۬ۛۚۖۧ۠ۤۛ۟ۨۘۗۢۤۚۖۖۘۖۡۥۘۜ۫ۜۥۢۧۥۧۨۘ";
                    break;
                case -1830996915:
                    z2 = true;
                    str = "ۗ۠ۡۤۜۡۧ۟ۘ۫ۢ۬ۡۨۥۤۥۦ۠۠ۚ۠ۨ۠ۙۛۤۢۙ۬ۛۥۘۛ۫ۖۜۙۜۜۤ";
                    break;
                case -1816065191:
                    str = "ۛۛۖۛۡۥ۫ۚۦۘۧۥۥۘ۫ۨ۠۬ۦۘۗۦ۟ۥ۟ۗۛۡۤۜۡۦۧۛۖ۟ۘۖۘۥۘ۫ۗۙۤ";
                    break;
                case -1593971491:
                    str = "ۘۚۥۘۘۡۦۘۢ۫ۗۖۜۗۛۡۤۢ۠ۗۢۛۦۨۦۘۦۧۖ۬ۦۛۖۘۥۘۢۚۖۘ";
                    i2 = color;
                    break;
                case -1567447270:
                    str = "ۙ۟۠ۗۜۡۘۛۦۨۘۜۦ۠ۙۛۙ۫ۧۡۘۙ۟ۨۘۜۦۦۘ۟ۗۦۘۛ۠۬ۛۦۜۘۚۗۜۘۨۜۜۘ۬ۤۚۥ۫ۦۧۜۙ";
                    break;
                case -1378020109:
                    i = -1;
                    str = "ۢۚ۬ۜۨۥۤۢۥ۫ۨۛ۠ۨۦۜۤۘۘ۟ۡۦ۟ۥۘۡ۫ۧ۬ۥۡۘۖۙۦۘۤۤۧ۟۟۠۠ۧ۫";
                    break;
                case -1018052419:
                    return;
                case -466831306:
                    String str4 = "ۚۗۤۡ۬ۢۤۜۖۘۗۙۡۧۜۦۛۗۙۧۜۥۛۢۘۘۙۡۡۦۤۢۥ۬ۥۘۜۦۚۡۢ۫ۘۘۧۗۜۘۧۥۚۢۥ۠۠ۛۘۘ";
                    while (true) {
                        switch (str4.hashCode() ^ 1192339798) {
                            case -1421245045:
                                String str5 = "ۛۢۡۙۘۚۨۡۨۡۖۜۘۧۙۖ۫ۧۘۘۙۦۘۗۚۨۘۦ۫ۙۨۘۙ";
                                while (true) {
                                    switch (str5.hashCode() ^ (-933294330)) {
                                        case -1366508318:
                                            str4 = "ۛۡۧۗۨۥۥۗ۫ۙۘۥۘۨۥۘ۠ۛۗۚۧۢۗۥۘۘۧۛۥۖۥۥۧۗۨۘۚ۟۫ۙ۫ۡۘۥۢۛۧ۟ۨۘۜ۬ۤۛ۠ۧۚۤۤ";
                                            break;
                                        case 861031183:
                                            str5 = "ۨۡۦۘۧ۫۠ۨۜۛ۬ۛۘۘۢۚ۬ۨۘۘ۫ۡۢۡ۬ۢۗ۟ۖۘۨۜ۠";
                                        case 1040071736:
                                            str4 = "ۢۨۢۥ۬ۧۗۨۤ۫۬ۨ۫ۛ۫ۧ۬ۛ۫ۛۛۨ۫ۙۥۦۦ۠ۦۧ";
                                            break;
                                        case 1797554356:
                                            str5 = z ? "ۛۥ۠ۗۛ۟ۖۡ۠۬ۡۛۡۛۘۘۨۤۡۘۘۙۢۧۥۤۚۛۤۗ۫ۘ" : "ۦۤۨ۫ۢۘۘ۠ۘ۟ۚۜۜۥۤۜۨۦ۠ۜۤ۠۬ۜۦۥۜۙۨۡ";
                                    }
                                }
                                break;
                            case -643453008:
                                str = "ۘۛۢ۟ۖ۫۟ۢۗۨۗۤ۫ۗۜۘۢۦۚ۫۟ۜۘۜۥۘۥۧۧ۟ۚۢ۠۠ۗۛۦۚۥۘۜ۫ۤۗۨۡۨۥ۟";
                                continue;
                            case 164334055:
                                str = "ۖۖ۬ۗ۟ۘۘ۠۬ۨۢ۫ۚۧۢۡۚۖ۫ۦ۬ۦۘۤۥۦۨۨۡ۫ۥ۟";
                                continue;
                            case 829376364:
                                str4 = "۬ۤۦۘۧۧۜۘۘۗۘ۫۠ۡۜۜۧۘۥۗ۫ۨۦۗۜۛۜۨۘۘۘۤۛۗۚۢۙ۟ۦ۫۠ۛ۠ۤۖۘ";
                                break;
                        }
                    }
                    break;
                case -339688386:
                    this.rootBackgroundColor = i2;
                    str = "ۡۛ۠ۦ۟ۥ۬ۡۛۨ۠ۘۘۧ۬ۡ۫ۜۥۛۗۗۚۚۢ۟ۨ۫ۢۛۙۜۘ۬ۘۘۘ";
                    break;
                case 353190390:
                    str = "ۗ۟ۙۡۖ۠ۡ۬ۘۡۡۘۙۙۗۡۧۛۖۧۚۡۧۢۜۚۦۘۢۤۦۧۖ۫ۡ۬ۥۜۚۨۘ۠ۡۥۘ";
                    break;
                case 371282865:
                    str = "ۗۘۧ۫ۧۙۤۙۘۘ۬ۤۥۘۤ۬ۥۛۙۨۨۘۘۦۧۘۛ۠ۘ۟۫ۦ";
                    z = z2;
                    break;
                case 592997601:
                    str = "ۗ۟ۙۡۖ۠ۡ۬ۘۡۡۘۙۙۗۡۧۛۖۧۚۡۧۢۜۚۦۘۢۤۦۧۖ۫ۡ۬ۥۜۚۨۘ۠ۡۥۘ";
                    i2 = i;
                    break;
                case 1106826832:
                    this.isNightMode = z;
                    str = "۟ۥۛ۫ۚۛۦۘۢۤۡۧۤۧۖۧۚ۬ۥ۟ۡ۬ۛۤ۠۟ۙۢ۠ۡۜ۬ۦۙۧۜۖۖۘۗۨۜۖۜ۫۟ۢۘۘۤۖۡۦۜ";
                    break;
                case 1337946497:
                    str = "ۙ۟۠ۗۜۡۘۛۦۨۘۜۦ۠ۙۛۙ۫ۧۡۘۙ۟ۨۘۜۦۦۘ۟ۗۦۘۛ۠۬ۛۦۜۘۚۗۜۘۨۜۜۘ۬ۤۚۥ۫ۦۧۜۙ";
                    z = false;
                    break;
                case 1530497023:
                    color = Color.parseColor(l2.decrypt("rETXN1Dp9Q==\n", "j3XlBmLYx5s=\n"));
                    str = "۟۟ۘۘۜۨۧۘۤ۟ۧۨۡۚ۫ۘۛۛۖۡۘۢ۬ۢۤۡۘۗۡۛ۠۟ۖۘۗۥۥۘۨۥۜ";
                    break;
            }
        }
    }

    private void setupImmersiveWindow() {
        Window window = null;
        String str = "ۛ۟ۢۗۨۘۖۘ۟ۚۗۖۚۚۜۘۨۘۢۛ۠ۗۥۚۨۘۖۥۦۘ۬ۤۖۘۘۘۖۘۜ۫";
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            switch ((((str.hashCode() ^ 940) ^ 954) ^ 812) ^ (-1078980348)) {
                case -2122574453:
                    String str2 = "ۙ۠ۥۢ۬ۙۤۛۢۛ۫ۥۘ۬ۘۚۥۖ۬ۙ۠ۨۘ۫ۢۦۚۢۜۘ۬ۨۡ";
                    while (true) {
                        switch (str2.hashCode() ^ 300861189) {
                            case -1948581241:
                                str2 = "ۗۥۚ۠ۚۦۘۨۢۦۖ۫ۜ۫ۤ۬ۙۢۥۘ۫ۨۤ۫ۛۖ۟ۖۦۢۖۜۧۛ۬۟ۗۥۘ";
                            case -573942602:
                                String str3 = "ۨۨۚ۫ۘۡۢۥۜۘۡۛ۬۟ۤۚۖۖۥۛ۫ۗۜ۬۫۬ۥۡۢ۠";
                                while (true) {
                                    switch (str3.hashCode() ^ (-1306625254)) {
                                        case -1434836277:
                                            str2 = "ۛ۬۬۠ۘۧۘۧۗۧۥۙۜ۬ۚۘۖ۬ۥۘۘ۟ۥۧۜۡۘ۬ۘ۠ۚۜۜۘ";
                                            break;
                                        case -494822633:
                                            str3 = "ۨ۫ۜۤۦۗۜۘۦۘۚۙۥ۟ۗۡۥۤ۟۬ۛۥۧ۠ۦۖۜۘۡۦۥۘۜۨۜۘۦۜۥۘ";
                                        case 100098091:
                                            str2 = "ۧۗ۠۟ۥۖۘۨ۠ۗۚۜۖۤۚۜۨۖۜۘۗۗۖۘۖۚۘۘ۠ۧۡۘ۟ۤ۠ۧۢۡۥۘۖۘ۫۟ۜۘۧۢۘۘۦ۬ۦۖۨۨۘۛۧۘ۠ۖۨۘ";
                                            break;
                                        case 1642683923:
                                            str3 = !this.isNightMode ? "ۦۤۨۘۦ۠۟ۡۧۤۖۡۥ۠ۘۨۘۖ۠ۛ۠ۨ۬ۘ۫ۧۘۤۖۡۢۙ۠ۙ۟ۘۛۛۘ۠ۨۢۡۧۢۧۢۧ۬ۡ" : "ۖ۫ۚۡۛۖۡۛۘ۬ۚۜۘۗۤۨۘۘۙۚۙ۫ۡۘۨۦۧۨۤۢۤۦۤ";
                                    }
                                }
                                break;
                            case -242946198:
                                str = "۟۫ۖۘۖ۫ۜ۫ۢۙ۬ۜۙۖۛۖۘۙ۠ۢۖۛۥۤۖۨۘۘ۫۟۠ۢۧۜ۠ۖۘۚۙۘۜۡۢۖۜۘۗ۟۬ۡۤۛ۠ۨ۠ۡۥۘۘ";
                                break;
                            case 2102336579:
                                break;
                        }
                    }
                    break;
                case -1752033068:
                    str = "ۛۦۚۛۦۚۙۛۦۘۦۘۖۘۗۜۛۜۦۗۗ۫ۤۦۘۘۖ۬ۗ۬ۨۢ";
                    i2 = i;
                    continue;
                case -1592068148:
                    str = "۬ۗۗ۟ۢۦۘ۠ۤۗ۠ۨۡۘۢۧۖۘۧۖۜۘۜۗۜۘ۟۟ۨۗ۫ۗۚۘ";
                    continue;
                case -1517478612:
                    i3 = 13570;
                    str = "ۤۘۧۘ۠ۨۦۥۚۦۘۗۘۨۘۛۜۨۘۜۡۢۡۨۖۘۥۨ۫ۡۚۡۥۗۙۤۜۨۦۦۘۘۛۦۡۘۦۗ";
                    continue;
                case -1337398644:
                    str = "ۛۦۚۛۦۚۙۛۦۘۦۘۖۘۗۜۛۜۦۗۗ۫ۤۦۘۘۖ۬ۗ۬ۨۢ";
                    continue;
                case -1130071010:
                    String str4 = "ۛۤۦۘۥۖۧۦ۟ۜۘۚۤ۫۠۬ۗ۫ۤۙۥۧۙۗۚۧ۫ۥۘۢۧ۬۟ۙۜۥ۬۠ۛۨۘ۫۠ۥۘۥۜۤۖۨۥۘۨ۠ۧۙ۫ۙ";
                    while (true) {
                        switch (str4.hashCode() ^ (-1089887953)) {
                            case -326429627:
                                String str5 = "۠ۛۢۚۗۥۘۙۡۤۦ۬ۥ۫ۤۘۘ۬ۖۥۘۛۛۗۦۗۖۘ۫ۙۦۛۧۙۥ۟ۘۗۢۥۘ۠ۙۙۧۧۚۜۗ۠ۚ۬ۡۨ۫ۥۙۡۘ";
                                while (true) {
                                    switch (str5.hashCode() ^ (-304068998)) {
                                        case -1096459600:
                                            str4 = "۫ۧۤۙۙۤ۬ۛۤ۟ۧۡۘۛ۟ۖ۠ۛۥۘۤۡۨۨۧۖ۟ۨۢۘۢۘۢۨۦۘۚۗۚ۬ۜ۟ۖۡۧ۫۠ۙۢۜۤ";
                                            break;
                                        case 269547130:
                                            str4 = "ۥۙۨۨۡۡۘۗۙۢۙۙۧۨۘۨۘۗ۠ۖۘ۫ۚۥۛۡۥۛ۟ۡۚۤۦ۬ۤۘۤۗۖۗ۠ۗۛۤۢۖۛۤۥ۬ۖۘۜۜۧۤ۟ۘ";
                                            break;
                                        case 1036160069:
                                            str5 = i4 >= 30 ? "ۖ۠ۜۘۤۦ۟۟ۖۧۘۨ۟ۡۗۖۜۘۘۨۧۨۘۢۛۜ۟۬ۡ۠ۧۨۤ" : "۫۫ۗۤ۟۬۠۫ۦۘۥۛۗ۫ۜ۟ۜ۫ۖۘۛۤۖۥ۫ۦۜۙۥ۠ۧۦۖۦ۬ۙۜ";
                                        case 1093441359:
                                            str5 = "ۚۗۤۥۦ۠۠ۨۦۗۖ۠ۖۨۖۙ۬۬ۧۜۢۚۨۘۨ۬ۛۦۧۜۘۧۚۖۘۗۤۛۧۙ۠ۛ۟ۙ۠ۢۨۥۚ۬";
                                    }
                                }
                                break;
                            case 5368014:
                                str = "۫ۚۥۤۧۡۦۖۘۡۨۤۦۗۜۘ۠۬ۙ۬ۨۖۘۤۚ۬ۙۦ۟ۘۜۨۤۛۥۘۦ۬ۨ";
                                continue;
                            case 766640032:
                                str4 = "ۜۤۖۘۙۡۚۘۙ۠ۘۡۘۛۨۡۘۖۡۦۜۙۢۙ۬ۧ۬ۡ۟ۖ۠ۜۘۙۦۘ۟۫۫ۛۢۜۖۚۤ";
                                break;
                            case 1081615581:
                                str = "ۘۨۜۘۦۧۡۘۘۨ۬ۜ۟ۛۦۖۨۘ۫ۥۜۥۨۘۘۛۢۜۘۢ۫ۡۘۖ۫ۤۨۥۦۚۙۥۢۙۜۘۙۦۧۘ";
                                continue;
                                continue;
                        }
                    }
                    break;
                case -710660214:
                    str = "ۦۥۨۘۥۧۦۨۢۜۘ۠ۛۡۙۘۧۘۖ۠۬ۡۘۖ۟ۙۦ۠ۦۥۘ۠ۤۨۤۘۖۘۛۥۦۘۨ۟ۖۘۤۖ۟۫۟ۨۘۡۙۛ";
                    i4 = Build.VERSION.SDK_INT;
                    continue;
                case -36980874:
                    return;
                case 260005529:
                    String str6 = "ۤۡۢۜۜۧۛ۬۫ۦۜۨۚ۟ۡۘۛۨۘۧۛۥۢۦۧۘ۠ۦۙۙۖۜۚۨۘۡۛۡۘۗۤ۠ۙۜۦۘۙ۠ۥۘۡۙۖۘۥۙ۬۠ۤۘۘ";
                    while (true) {
                        switch (str6.hashCode() ^ (-1895932846)) {
                            case -1900135540:
                                String str7 = "ۙۚۖۥۤۜۘۙۙۤ۬۟۟ۤۥۧۘ۠ۡۧۘۜۚۙۚۜۧۤۘۘۘۡۥۜۘۖۤۜۘۤۡۢ۠ۤۦۘ۟ۗۦۘ";
                                while (true) {
                                    switch (str7.hashCode() ^ (-1994005487)) {
                                        case -1565265915:
                                            str6 = "ۘۜ۠ۥۘۚۥۦۨۦۜۦۜۘ۠ۛۤۘۘ۟۫ۧۛۖ۠ۙۚۜۙ۬ۦۡ۟ۧۚ۟ۥۘۛۗۛۤۚ۬ۗ۠ۚۚۗۦۘۗۛۜ۠ۖۨ";
                                            break;
                                        case -67378484:
                                            str7 = "ۖۨ۠ۦۙۡۘۗۤۜۘۤۥۧۘۢۗ۠۟۫ۦۜ۠ۙۖۢۙۛۡۗ۠ۛۛۗۛۤ۠ۚ۠ۙۘۖۘۚۖۤۤۢۨۙۗ۬ۗۛۡۘۘۧ۫";
                                        case 23788756:
                                            str6 = "۠ۗۘۦ۬۟ۘ۫ۖۡۗۨۡۖۨۤۖۖۧۗۜ۬ۡۤۖۦۜۘۙ۫ۥۘۧۜۦۘۚ۬ۘۘ۟ۜۢۘ۫۫";
                                            break;
                                        case 1078128944:
                                            str7 = i4 >= 23 ? "ۛۛۡۘۗۘۨۜۨ۟ۥ۬ۖ۟ۗ۟ۢۛۨۘۦۘۘۙۜۘۤ۫ۨۖ۠ۡۘۢ۫ۗۗ۟ۛۡۜ۫ۚۗۖ" : "ۡۡۙۜۤ۟ۘۡ۬۬۟ۤۗۙۥۘۖ۫ۡۘۦۜۨۘۡۤۙۗ۟ۖ۬۫۫ۗۢۘۘۧۚۛۖۜۧ۬ۛۜۘۦ۟۬ۖۗۨۘ";
                                    }
                                }
                                break;
                            case -1302273745:
                                str = "۬ۗۦۧۡ۬۫ۚۥ۟۬ۤۙۘۢ۫۠۫ۢۥۨۤۜۡۘۙۨۚۚۨۢۛ۫ۧۘۙۘ";
                                break;
                            case 586706986:
                                str6 = "ۧۡۦۘ۠ۢۜۛۚۗۧۖۚۢ۬ۥۘ۠ۦۨۧۡ۟ۤۧۨۘۡ۠۫ۡۦ۟۬۠ۦۘ۫۠ۦۥۜ۠۬ۦۦۘۙ۫ۛ";
                            case 1783732207:
                                break;
                        }
                    }
                    break;
                case 417334730:
                    window.getDecorView().post(new h0(this, window));
                    str = "۟۟ۤۨ۫ۢۧ۬ۙ۬ۖۖۙۥۘۘ۬ۛۧ۫ۨۦۜۘۘۤ۬ۙۢۦۜۘۚۦ۫۬ۦۧۤۧۚۜۦۘۘ";
                    continue;
                case 517116845:
                    str = "ۚۛۜۛۛۜۗ۬ۡۧۢۥۘۤۥ۬ۜۛۨ۟۠ۢۤۛ۬ۦ۫ۖۘ۠۫ۨۘۥۘ۬ۨۢۨۛۚۥۘۖۥۨۢ۟ۙۖ۫۠";
                    window = getWindow();
                    continue;
                case 761309886:
                    str = "ۗۛۢۨۤۥۘۤ۟ۤ۟ۛ۠ۛۧۦۢۛۙۤۜۤ۟ۡۨۨۢۘۚۛۤۘۘۖۦۘۖۙۧ۠ۥ۠";
                    i2 = i3;
                    continue;
                case 1391668154:
                    window.setStatusBarColor(this.rootBackgroundColor);
                    str = "۬ۢۡۗۨۗۦۢۤۤ۟ۜۘ۫ۦۘ۟ۚۨۘ۠ۜ۬۬ۢۚ۟ۙۙۛۥۘۘۜ۬ۖۥۘۘۖۥۗۦۙ۫";
                    continue;
                case 1459663312:
                    str = "ۢۡۡ۬ۢۘۘ۬ۡۖۘۥ۟ۖۙۗۤۚ۫۟ۨ۫ۖۤۘۨۘۡ۬۬ۙۤۦۜۙۘۘۨ۠ۙۦ۟ۧ۟ۚۥۘ۬ۧۗ";
                    continue;
                case 1816332815:
                    e0.c(window);
                    str = "ۜۡۖ۟۫ۧۨۤۡۘ۟۟ۨۖۧۤۘۨۖۘۙۡۧۘ۬ۦۜۘۡ۫ۥۧۨۧۘ۠ۖۖۚۢۛۡۦۧۘۥۛۨۗ۠ۖۘ۠۠ۨۘ۬ۤۢ۟۬۫";
                    continue;
                case 2055423396:
                    window.getDecorView().setSystemUiVisibility(i2);
                    str = "ۢۡۡ۬ۢۘۘ۬ۡۖۘۥ۟ۖۙۗۤۚ۫۟ۨ۫ۖۤۘۨۘۡ۬۬ۙۤۦۜۙۘۘۨ۠ۙۦ۟ۧ۟ۚۥۘ۬ۧۗ";
                    continue;
                case 2102105809:
                    i = 5378;
                    str = "۠ۤۧۘۦۢۧۨۗ۬۫۫۠ۛۨۘ۟ۡۡۘۢۦ۬۟۬۫ۨۜۨ۟ۨۢۦۗۤۚۥۨۘۛۖۡۘۥ۠۟";
                    continue;
            }
            str = "۠۠ۚۗ۫ۥۘۚۗۚۢۤۜ۬ۗۚۙۗۡۘ۠ۡۡ۠ۚۜۨۛۨ۫ۛ۟ۧۛ۫ۛۛۤ";
        }
    }

    private void startConfigCheckLoop() {
        String str = "ۨۘۧۘۚۦۨۘۦۦۦ۟ۨ۟۟۫ۨۜۘۤ۠ۧۚ۠ۡۜۜ۫ۡۖۘۖۖ۟ۦۙ۟ۜۜۖۘۧۡۨۨۢۥۨ۟ۖ";
        while (true) {
            switch ((((str.hashCode() ^ 682) ^ 429) ^ 236) ^ 1581284640) {
                case -1393079897:
                    return;
                case -113735343:
                    this.handler.post(this.checkRunnable);
                    str = "ۙ۠ۥۡۢۘۘۗۘۥۘ۟ۖۤ۟ۜۥۘۗۧۦۘۨۙۨۘۙۦۤۚ۬۠ۤۦۙۧۨۨۘۜۧۘۚ۟۠ۚۜۖۘۘۛۢۥۨ";
                    break;
                case 854118059:
                    str = "ۜۖۦۤۨۙۡۨۥۘۦۦۥۢۚۡۢ۬ۖۛۨۜۢۗ۫ۤۨۡۘۨۛۘۘ";
                    break;
            }
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        FrameLayout frameLayout = null;
        LinearLayout linearLayout = null;
        FrameLayout.LayoutParams layoutParams = null;
        int iDp = 0;
        FrameLayout frameLayout2 = null;
        GradientDrawable gradientDrawable = null;
        ImageView imageView = null;
        Drawable applicationIcon = null;
        TextView textView = null;
        FrameLayout.LayoutParams layoutParams2 = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        String str = "ۨۡۘۘۘۤۙ۠ۡۖۘۛۤۦ۠ۗۜۚۡۘۥ۬ۢۢ۬ۦۘ۟ۖ۬ۖۦۖۡۨۙۧۖ۬ۦۜۙۙۨ۫";
        while (true) {
            switch ((((str.hashCode() ^ 834) ^ 425) ^ 260) ^ (-2180966)) {
                case -2087568930:
                    textView.setText(getAppVersion());
                    str = "ۖۛۨۘۖۦۖ۟ۤۜۘ۟ۡۘۚۜۜ۫۬ۚ۟ۗۦۨۦۘۤۥۢۢۥۨۘ";
                    continue;
                case -2069668775:
                    linearLayout.addView(frameLayout2);
                    str = "ۧۛۚۚۡۖ۫۠ۡ۫ۢۢۧۦۜۥۜۥۘ۠ۗ۫ۢۗۡۥۘ۠ۡۜۗۤۧۘۘۥۘۧۘۘۛۗۖۡ";
                    continue;
                case -2064603361:
                    frameLayout.addView(textView);
                    str = "ۡۙ۫ۥۛ۬ۡ۠۫ۘۖۥۘ۠ۜۥۚۦ۟ۦۗۥۘ۬ۗۖۜۡۚۛ۟۬ۛۛۦۘۥۡۛ۟ۤۨۢ۫ۧۙۛۗۗۦۖ";
                    continue;
                case -2045913313:
                    textView.setLayoutParams(layoutParams2);
                    str = "ۛۙ۟ۨ۫ۨۘۤۤۤۖۤۜۦۧۗ۬۠ۢۛۨۡۘ۠ۢۦۘۙۧۖ۬ۜۖۘۨ۬ۤۢۥۖۥۤۙۤۤۧ۠ۛ۠۫۟ۦۘ";
                    continue;
                case -1947139251:
                    layoutParams2 = new FrameLayout.LayoutParams(-2, -2);
                    str = "ۦۗۘۨۧۦ۫ۥۜۥۚۙۛۘۡۨۘۡۘۦ۟ۖ۬ۗۡ۫۟ۦۘۜۛۘۘۛۨۥۥۦ۟ۦۢۜۘۘۗۥۘۢۖ۫ۛۤۗۙ۠ۥۡۘۢ";
                    continue;
                case -1940387177:
                    imageView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                    str = "ۛۖۧ۟ۤۡۘ۟۫ۨۡۚۖۖۨۘ۟ۚۚۜۤۜۗۢۘۘۚۧۢۖۘ۬۫ۙۖۘ۠۫";
                    continue;
                case -1895315217:
                    linearLayout.setOrientation(1);
                    str = "۫ۡۚۢ۫ۦۢ۠۠ۘۢۥۘۖۤۚۢۢۖۙۖۘۢۚۥۘۨ۠ۛۙۧۨۘۧۥۚۤۤۦۘۚۢۗۙۤ";
                    continue;
                case -1810064006:
                    return;
                case -1772892976:
                    frameLayout.addView(linearLayout);
                    str = "ۛۢۡ۠ۚۚۚ۬ۤۜۜۧۗ۠۟ۗ۟ۤۙۧۧۤ۟۟ۚۦۘۘۨۚ";
                    continue;
                case -1747450829:
                    layoutParams.gravity = 17;
                    str = "۟ۜۜۢ۬ۘۜۤۖۥۙۛۤۚۜۘۙۥۢۜۚۢۡ۫ۙۚۧۖۘۛۧ۫ۘۖۨۚۡۗ۠ۡۘۛۗۜۘ";
                    continue;
                case -1679412975:
                    str = "ۥۤۤۧۜ۠۟۟ۚ۟ۙۡ۬ۨۗ۬۠ۛۨۘ۟ۙۘۘۨۙۨۤۥۜۘۜۢۤۙۥۤ۬ۤۙۤۢۛ";
                    i3 = i2;
                    continue;
                case -1580260382:
                    startConfigCheckLoop();
                    str = "ۢۡۦۘۚۙۗۨۧۤۘۘۘۙ۟ۗۢ۠ۚۥ۟ۢۢۤۜ۠ۡۧۘ۟۫ۤ";
                    continue;
                case -1517885253:
                    setupImmersiveWindow();
                    str = "ۨۙ۬ۤۤ۠ۡۤۘۘۗۧ۟۟ۤۦۘ۠ۥۥ۟ۙۤ۬۟ۚۢ۠۟ۙ۠ۨۡۙۢۚۛۢۜۙۢۧۨۛ۫۠ۡۘۧۗۨۘ";
                    continue;
                case -1490484300:
                    textView.setTextColor(i3);
                    str = "ۖ۬ۦۥۜۡ۟ۡۢ۬ۧ۠ۘۛۚۛۜۖۘۚۥۖۤۧ۫ۙ۠ۘۢ۫ۖۤۛۚۥ۠ۡ";
                    continue;
                case -1221078137:
                    frameLayout2 = new FrameLayout(this);
                    str = "ۛۧۧۛ۟۬ۥۥ۬ۜۖۘۛۥۘۘ۠ۚۖۘۘۗۖۗۥۥ۫۫ۡۙ۬ۨۗۛۨۤۖ۟ۗ۬ۥۧ۠ۢۛۖۡۘۖ۫۠";
                    continue;
                case -1174465622:
                    String str2 = "ۖ۟ۘۘ۫ۡۚۚۦۨۘۚۖۥۦ۫ۡۘۚۢۡۘ۟ۨۤ۬ۖۥۘۢۤ۟ۙ۠ۦۘۗ۟ۛۦۜۘۘۢۧۤ۠ۙۢۡۧۘۜۙۥۘۛۗ۫ۚ۠ۢ";
                    while (true) {
                        switch (str2.hashCode() ^ 922268156) {
                            case -1677758272:
                                str = "ۘ۬۟ۜ۟ۥۙۧۤۧۖۧ۫ۢۜۚۦۢۤ۫ۡۧۥۧۚۢۢ۟ۜۙۛۥۙ";
                                continue;
                            case -649615486:
                                str = "ۦۖۤ۠ۖۗۚۧۗ۫ۗۙۙۢ۫ۘۦ۬ۙۛۖۖۤۨۛۜۨ۠ۛ۠ۤۗۚۛ";
                                continue;
                                continue;
                            case 209901498:
                                str2 = "ۙۙۡۘۡۖۗۘۙۢ۬ۘۥۘۤۜۛۨۜۧۥۙۖۙۡۡۘۢۢۗۢۤۜۘۙۤۚۜۢۘۘ";
                                break;
                            case 1736244219:
                                String str3 = "ۙۖۜۦ۠ۚۨۦۦ۫ۦ۫۟ۖۜ۬ۚۤۗۙ۫۬ۥۤ۫ۡۡۧۖۗۡۛۨۢ۟ۘۦ۬ۤ۟۬ۧ";
                                while (true) {
                                    switch (str3.hashCode() ^ 1845096646) {
                                        case -865228011:
                                            str2 = "۬ۢ۠ۖۙ۫ۧۜ۬۟ۖۨۥۨۘۛۛ۫ۘۧۘۘۨۡۙۢ۟۠ۤۜۘۛۧ۬ۚۥۦۘۤ۠ۘۛۦۧ۫ۚۨۗ۟ۖۘ";
                                            break;
                                        case -670837404:
                                            str3 = this.isNightMode ? "ۢۜۥۧۚۙۗ۟ۗۡۡۦۘۜ۠ۙۤۗۘ۬ۘۘۦۥۘ۟ۢ۠ۛۙۨۘ" : "ۙۧ۠ۥۗۤۨۦ۠ۨۢۨۢ۠ۚۚۖۗۖۖ۠ۖۜ۟ۢۤ۬ۚۤۜۘ۫ۘۘۤۧۛۦ۠ۗۤۤ۬";
                                        case -186220434:
                                            str2 = "ۛۤ۟ۛ۫ۜۘۛۦۖ۟ۜۘ۟ۖۨۘ۠۫۠ۙۖ۬ۗۥۡۡ۬ۘۘۗۙ۫";
                                            break;
                                        case 644214126:
                                            str3 = "ۚۘ۫ۡۜۨۖۙۥۘ۟ۛۡۘۨ۬ۜۤۡۡ۫ۤۜۘۚۤۡ۬ۜۜۘۚ۟ۙۘۛۥۨۨۧۖۡۨۘۢۜۜۘۡۡۛۛۤۢۥ۟ۨۘۚۢۦۘ";
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case -1074462423:
                    layoutParams2.bottomMargin = dp(16);
                    str = "ۤۥۦۘۜۙۗۥۥ۫ۛۙۗۘۘۧۙۘۘۘۥۛ۬ۘۨۖۘ۠ۜۜ۫ۨۥۘۤۛۢۙۧۥۘۡۡۖۘۗ۟ۨۡ۫ۘۘۖ۠ۧۗ۫۫ۥۤۜۘ";
                    continue;
                case -1051942046:
                    linearLayout.setGravity(17);
                    str = "ۙ۟ۥۘۨ۬ۢۘۨۥ۟۟ۤۨۛۦۘۘۛۜۘ۠ۗۡۘۧ۟ۙۙۛۢۢۥ۟";
                    continue;
                case -951997935:
                    i2 = -12303292;
                    str = "ۜۥۧۘۦ۟۟۟۬ۘۘۙۙۜ۟ۧۦۥ۟ۦۨۘۗۚۛ۬۬ۖۘۥۛۖۘۙۗۨۧۢۘۘ۠ۡ۟ۘۜۘۘ۠۫ۥۥۨۘ۬ۥۡۧ۬";
                    continue;
                case -938974110:
                    textView.setTextSize(2, 12.0f);
                    str = "ۧۖۡۘ۟۬ۡۘۙۦۦ۫ۦۖ۟ۜۖۘ۟ۥۖۘۥۗۖ۟ۗ۟ۢۥۜۜۦۥۡۗۘۦۦۘ۫ۡۤۤۜۘۡ۬ۦۘۗۥۖ";
                    continue;
                case -936631358:
                    frameLayout.setBackgroundColor(this.rootBackgroundColor);
                    str = "ۨ۟ۥۧۚۤۧۢ۟ۦۥۜۖ۟ۨۨۘۙۙۖۨۛۚۜۦۨۨۖۡۘۤ۟ۖۘۦۧۙ";
                    continue;
                case -865744245:
                    gradientDrawable = new GradientDrawable();
                    str = "۟ۡۥۘۦۗۙۛۚ۠ۜۧۜۘ۟ۢۤۨۜۖۘۧ۫۠ۨ۫ۛۥ۫ۜۘۥۜۗ۟ۜۨۘۦۢۡ۬ۛۙۨۢۥۘۘۡ۠ۧۜۖ";
                    continue;
                case -696102249:
                    super.onCreate(bundle);
                    str = "ۢ۬ۘۘ۟ۚ۟ۗۡۦۘۙۦۛۥۖۡۚ۟ۘۚ۫۠ۗ۠ۥ۠ۡۡۦۜۤۥۡۖۘۨۗۖۖۜۧۘۤۡ۠";
                    continue;
                case -630029681:
                    frameLayout2.setOutlineProvider(new f0(this));
                    str = "ۡ۟ۘۘۗۤۜۗۜۡۙۚۧ۬۫ۜ۠ۦۘۘۚۚۜۘۗۚۢۙۘۡۘۦۦۙۤۖۧۗ۬ۗۢۛۦۖ۬";
                    continue;
                case -548444501:
                    frameLayout2.setLayoutParams(new FrameLayout.LayoutParams(iDp, iDp));
                    str = "ۥۦۜۙ۟ۛ۠۫ۧۥ۟ۘۘۗۡۜۥ۬ۦۢۘۛۛۥۡۘۢۗۢۛۧۘۘۛ۫ۚ۬ۙۖۡۚۚۚ۟ۙۡ۠ۨۘۜ۬ۖۘۧۜ۟ۗۖۘ";
                    continue;
                case -528308173:
                    frameLayout2.setClipToOutline(true);
                    str = "ۘۗ۟ۗۘۖۘۖۦۡۖۢۛۜۢۨ۫۬ۥۘۘ۬۫ۚۦۘ۠ۘ۫ۨۚۧۖۗۡۥۛۚۧۙۧ۟ۨۘۗۗۛۢۙۢ";
                    continue;
                case -476199026:
                    layoutParams2.gravity = 81;
                    str = "ۗ۫ۖۧۚۖۘۢۥۜۜ۠ۗۦ۫ۛۢۘۘ۠۬ۦ۫ۦۡۘۧ۫ۘۡۢ۫ۛۧ۫ۢۢۚۛۦۥۢۧۛ";
                    continue;
                case -454534664:
                    imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                    str = "ۢۗۖۘۥ۬۟ۦۛ۟ۙۙ۫ۛ۠ۗۤ۬۬۟۫۬ۛۛۛۜۦۥۘ۫ۘۢۤ۫۫۬ۡ۫ۗۘۛۛۨۜ۬ۨۛۥۘۖۤۡۘۤۚۢ";
                    continue;
                case -451111061:
                    str = "ۢۗۜۘۨۦۘۘۥۖۢۛۡ۟۟ۦۧ۟ۥۦۚۜۧ۟ۢۜۘۡۢۚۥۚۧۜۗۘۘۗۗۦ";
                    i3 = i;
                    continue;
                case -397599572:
                    String str4 = "ۜۡۧۘۧۘۥۛۡۘۡ۬۫ۥ۠۫ۜۜ۬ۙۚۘۖۧۡ۫ۨ۬۟";
                    while (true) {
                        switch (str4.hashCode() ^ 876596256) {
                            case -1760605071:
                                String str5 = "۠ۨۡۘۚۥۜۘۡ۬ۙۢۚۤۗۧۗۛۨۖ۬ۘۚ۟ۦۘۚۖۗۚۘۘ۫ۗۚۗۙ۟ۥۘۘ۬ۗۡۘۙۨۥۡۘ";
                                while (true) {
                                    switch (str5.hashCode() ^ (-961142714)) {
                                        case -1921225646:
                                            str5 = "ۖ۬ۘۘ۬ۙ۠ۨۡۘۖۡۦۘۢ۬ۖۖۢ۟ۚۛۘ۬ۡۜۘۤۙ۠ۢۘۤ";
                                        case -1814308090:
                                            str4 = "۠ۙۦۘ۬ۖۜ۫ۘۧۙۖ۬ۨ۟ۧ۠ۦۜۘ۠ۡۧۘۡ۫ۖۖۧۨۜۗۡۘۜۙۙۢۜۧ۫۠ۙ۠ۗۧۙ۬ۥۦۤ";
                                            break;
                                        case 417042139:
                                            str5 = bundle == null ? "۫ۦۘۤ۬ۗۗۚۜۘۢۥۙۘۥ۬ۘۡۘۙۜۧۢ۬ۙۢۡۥۗ۠۟ۥ۟ۢۘۧۛ" : "ۦۚ۫ۢۡۖۨۛۢ۟ۦۧۛۛۦۦۘۢۜ۠۫ۢۨۥۡۡۘۥۧ۟ۦۜۜ۟ۗۙ۟ۤۘۢۥۖۘۨ۠ۚۗۦ۬";
                                        case 1596471491:
                                            str4 = "ۙۥۦۢۤۥۦۛۛۧۤۦۥۗۤۛۤۦۗۦۛ۟ۨ۠ۙۙۙۢ۠ۦ";
                                            break;
                                    }
                                }
                                break;
                            case -707821714:
                                break;
                            case -282115695:
                                str4 = "۟ۘۗ۠ۛۛ۬ۢ۬ۨ۬ۧ۬۟ۨۘۗۦۦۥ۠ۚ۠۟ۥۘۨ۠ۜۘۜۥۖۤۤۙ۫۫ۨۘۘۚ۠ۨۡۘۙ۟ۦۜ۠ۤ";
                            case 1255136605:
                                str = "ۧۨۙۖۡۘۘۜۜ۫ۤۗۙۤۧۤ۠ۡۧۘۙۢۤۢۚ۬ۖۨۘۧ۟ۙ";
                                break;
                        }
                    }
                    break;
                case -347487568:
                    str = "ۥۤۤۧۜ۠۟۟ۚ۟ۙۡ۬ۨۗ۬۠ۛۨۘ۟ۙۘۘۨۙۨۤۥۜۘۜۢۤۙۥۤ۬ۤۙۤۢۛ";
                    continue;
                case -333076354:
                    frameLayout2.addView(imageView);
                    str = "ۦ۠ۖۛۛۙۙۧ۟ۤ۠ۨۧ۫ۡۡ۬ۨۘۤۛ۠ۛ۫۟ۙ۟ۡ۫ۦۜ";
                    continue;
                case -325948249:
                    textView = new TextView(this);
                    str = "ۜۦۧۘۗۗۗۙۧۥۘ۠ۗۦۗۚۥۘۡۤۙۥۘۘۖۛ۟ۤ۠ۘۨۤۧۤ۫۫ۨ۬ۚۜ۬۫۟ۛ۬ۧ۟ۨۛۜۘۘ";
                    continue;
                case -232687570:
                    clearAllPopupIdCache();
                    str = "ۛۜۨۜۢ۫ۡۥۡۘ۟ۥۗۚ۠ۙۧۗ۬ۘ۫ۥۙۥۘۛ۠ۢۦۤۨۢۥۧ۬ۚۦۘ۫ۜۥۘ";
                    continue;
                case -183271446:
                    frameLayout.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                    str = "ۡۗ۫ۗۨۥ۟ۙۗۤ۫ۥۜۡۤۧۖ۟ۡۖ۫ۙۙۘۨۖۘۙۥۜۗ۫۬۟ۤۛ";
                    continue;
                case -178052214:
                    frameLayout2.setBackground(gradientDrawable);
                    str = "ۚۗۙ۟۟ۜۘ۟ۥۦۥۤۜۧۗۙۙۧ۟۫ۥۘۢ۬۬ۙۨۙۨۡۚۥۘۡ۠ۦۢ";
                    continue;
                case -169061701:
                    imageView = new ImageView(this);
                    str = "ۜۙۙۦۚ۬ۤۧۦ۠ۗۚۥۧۗۦۙۧۨۦۘۚۧۨ۠ۙۦۤ۫ۖ۫ۧۗۧۧۙۥۘۢۖۡۤۢۤۨۚۖ۟۟ۚۚۘۜۢ";
                    continue;
                case -73874148:
                    String str6 = "ۘۧ۫ۙۤ۠ۤۤۥۘۖۖۙۡۚۙۘ۬۬ۤۛ۟ۥۗ۟ۨۖ۠ۤۡۚ۫ۨۘ۟ۨۢۨ۠ۖ۠۫ۨ۬ۛ۬ۧۚ۬";
                    while (true) {
                        switch (str6.hashCode() ^ 105261602) {
                            case -1288589884:
                                str6 = "ۜۢ۟۟ۜ۫ۡۥۘۙۚۡۧ۫ۛۗۜ۬ۧۙۤۜۦ۟ۢۗۘۘۡ۠ۖۜۧۥۘۘ۟ۥۘۦۙ۫۠ۦۡۘۗ۠۬ۜۨۡ";
                            case 467580067:
                                str = "ۙۥۖۘ۬ۦۦ۟ۧۦۘ۫ۨۙۧ۬ۛۤۛۛ۬۬ۥۙۜۥۛۦۘۢ۟ۦۘ۟ۗۤۨ۫ۧۤۖ۫ۚۡ";
                                break;
                            case 591966988:
                                String str7 = "ۦۜۘۘۧۜۘۘۡۖۘ۬ۧۜۘۚ۬ۜۘۜ۬ۨۘۜۗۥۘۙۖۦۘۚۘۧۘۤۥۤۦۖ۫۠۟ۧۥۗۘۘ۫۬ۡۖۜ۫۠ۙ";
                                while (true) {
                                    switch (str7.hashCode() ^ (-485965257)) {
                                        case -1370001088:
                                            str7 = isTaskRoot() ? "۟ۦۛۘۗۙۧۦ۟ۜۜۡۚۨۘۥۡۤۜۡۚۥۙۨۘۜۘۧۦۛۖ" : "۠ۨۚۦۘۘۘۦۛۙۗ۟ۗۜۨۧۧۚۨۨ۫ۧ۬۬ۤۦۧۙۢۤۤۢۢۛۢۜۘ۟ۥۗ۬ۜ۟۫ۤ۠۠ۛۚ";
                                        case -92865571:
                                            str7 = "ۥۛ۠ۧۨۘۘ۬ۡۜۗ۬ۤۜۦۘۡۘۘۘۢۤ۠ۤۜ۠ۦۧ۫ۡۥۨۘۜ۬ۦۘۙۨۘۥۗ۟ۜۘ۫ۨۤۙ۫ۖۘ";
                                        case 308852699:
                                            str6 = "ۡۢ۬ۧ۠ۦۗۧۜۘۧۨۚۙۢ۠ۖۖۜۢۨۨۛۜۘۡۙۤۤۙۡ۟ۨۖۘۨ۬ۚ۟۫ۘۘۡۖۜ۟ۖۥ";
                                            break;
                                        case 1057577440:
                                            str6 = "ۢ۬ۖ۬۟ۡۥۥۧۘ۬ۚۘ۟ۢۦۖۙۨۘۚ۫ۘۘۗۡۢۜۨۘۗۧۘۖۥۚ۠ۛۨۘۗۙ۠۬ۤ۠ۖۜۜۘۛۨۥۥ۫ۦۘۥۥۘ";
                                            break;
                                    }
                                }
                                break;
                            case 1200640763:
                                break;
                        }
                    }
                    break;
                case 68381250:
                    frameLayout = new FrameLayout(this);
                    str = "ۚۥۢۛۖۤۛ۟ۧۦۨۥۗۢۙۢ۟ۘۜۡۜۘۜۦۥۘۖ۫۫ۨۨۤۗۘۜۙۨ";
                    continue;
                case 98906946:
                    String str8 = "ۢ۬ۥۗ۫ۙۛۘۘۙۗۡۢۢۜۘ۬۠ۖۘۦ۬ۦۗۦۡ۟ۢۙۚ۫ۖۚ۬ۛۧۢ۫ۥۦۘ۬ۙۖ۠۟ۛۙۙۡۘۛۥۨۘۚۙ";
                    while (true) {
                        switch (str8.hashCode() ^ 885304520) {
                            case -1872026174:
                                str8 = "ۗۘۜ۬ۨۡۧۢۦۘۧۥۛۙۚۘۥۖۘۨ۟ۤۖۚۛۘۜۦۨ۬ۜ";
                                break;
                            case -1292352002:
                                str = "۫ۛ۠ۛ۬ۦۘۗۛۘۦ۫ۛۜ۫ۖۥۛۥۚۤۘۨۨۘۧ۠ۢۥۜۖۢ۫ۧۚ۠ۥۘۚۚۨۘۦۨۡۘ";
                                continue;
                            case 597548038:
                                String str9 = "ۧ۬۫ۗ۠ۨۘۨۢۥۘۥۥۥۘۨۜ۫ۢ۬ۖۜۡ۫ۚ۠ۨۜۨ۠ۢۡۘ";
                                while (true) {
                                    switch (str9.hashCode() ^ (-1847403842)) {
                                        case 545094631:
                                            str8 = "ۚۖۡۗ۬ۦۜۚ۫ۧۨ۠ۗۤۢۖۚۨ۟ۨۦۨۧۥۘۢۖۛۤۗ۟۟ۘۖۡۨ۠ۤۦۦ۟ۙۥ";
                                            break;
                                        case 739359545:
                                            str9 = "ۖۡۘۘۛۜۥۘۚۙۛۢۤ۠۬ۢۦۘۧ۬ۦۙۙۚۚۚۨۦۗۗۢۡۨۢ۠ۖ۬ۖۘۤۚۡ۠ۡۜۘ۟۠ۨۘۖۥۧۘ۬ۗۡۘۗۢ۠";
                                        case 945034003:
                                            str8 = "ۜۨۥۙۜۦۘۥ۬ۨۜۜۡۦۤۨۢۗ۠ۚۨۨۢۦۘۜۛۛۦ۠ۙۢۦۙ۟۟ۢۜۦۨۘ۟ۜۗۚۚۗۤۢ۫ۗۨۧۡۤ";
                                            break;
                                        case 1378280395:
                                            str9 = applicationIcon != null ? "ۜۢۡۥۜ۟ۡۜۘۜۤ۬ۘۜ۠ۖۧۘۙۥ۠ۦۖۦۖۙۦۘۙۖۨۘۨۘۡۘۚۖۡۘۤ۫ۡۥۗۖۖ۬ۧۦۛ۟ۧۢۥۙۢۚ" : "ۘۘۨۥ۠ۥۢۤ۫ۢۙۢۦۥ۬۬ۨ۠ۘۗۙ۬ۛ۫ۥۥۗ۬ۘۨۘ۬۠ۖۥۡۜۘۚۧ۠ۗۖۛ";
                                    }
                                }
                                break;
                            case 834647924:
                                str = "ۢ۫ۚۗ۫ۙ۫ۨۛ۟۠ۙ۫۬۫۫ۚۦۤۛۙۢۧۨۖ۠ۢۘۧ";
                                continue;
                                continue;
                        }
                    }
                    break;
                case 225683509:
                    linearLayout.setLayoutParams(layoutParams);
                    str = "ۗۜۖۘۡۨۙ۬ۖۗۛ۟ۡۛ۬ۡۗۙۢ۫ۖ۟ۚۤۧۧۨۘۚۤۧ۫ۢۡۢ۟ۜۦۡۤۤۛۜۘ۫۫۬ۛۜ";
                    continue;
                case 458127713:
                    i = -7829368;
                    str = "۬ۨۤۥ۟۟۫ۢۨۧ۫ۦۘۥۨۧۦۡۦۘ۬ۚۡۢ۠ۢ۫ۛۡ۟۠۫ۘۨۜۘ۫ۤۡۘۗۙۨۙ۠ۦۜۡ۫ۢ۠";
                    continue;
                case 628645334:
                    imageView.setImageDrawable(applicationIcon);
                    str = "ۢ۫ۚۗ۫ۙ۫ۨۛ۟۠ۙ۫۬۫۫ۚۦۤۛۙۢۧۨۖ۠ۢۘۧ";
                    continue;
                case 735431100:
                    gradientDrawable.setColor(0);
                    str = "ۚ۫ۤۢۥ۬ۙۛۡۤۗۚ۠۬ۧ۬ۙۤ۠ۧۥۘۛۙۖۘۙۚۥۘ۬ۗۗۙۥۡۛ۬ۖ";
                    continue;
                case 1192384937:
                    linearLayout = new LinearLayout(this);
                    str = "ۢۖۖۥۗ۫ۡۛ۫ۢۘ۫۟ۤۢ۬ۢۤۤۧۡۘۥۧۡۘۡۥۨۘۖۘۙ۟ۘ۫ۥۤۤۛۥۘۖۖۥۦۖۙ۠۟";
                    continue;
                case 1282262434:
                    str = "ۥۡۦ۫ۡۥۘۜۡۘۘۤۖۦۧۡۨ۬ۖۗۘۜۢ۬ۥۜۙ۟ۥۘۢ۬ۙ۫ۦۦۘۧۦ";
                    continue;
                case 1415516917:
                    iDp = dp(128);
                    str = "۠ۡۧۘۛ۬ۡۘۗۙۨ۟ۨۦۤۨ۫۬ۖۦۘ۫ۦۡۘ۬ۦ۬ۛۡۛۚۙۜۘۖۤ۬ۜۛۙ۠ۘ۬۠ۗۖۘۙۜۨۘۤۘۜۘ";
                    continue;
                case 1528158517:
                    resolveSystemThemeColor();
                    str = "ۙۦۧۥۖۙۦ۬ۨۦ۬ۚ۠ۗۘۘۨۨ۫۫ۥۧۦۖۘۚۥۡۛۡۛ";
                    continue;
                case 1552454033:
                    setContentView(frameLayout);
                    str = "۫ۙۨ۫ۜۙۦۥۡۚۨۡۘۤۤ۬ۢۨ۬ۤۜۨۗۢۡۗۧۦۘۡۛۦۘۖۨۨۘۦۨۢ";
                    continue;
                case 1777840048:
                    applicationIcon = getApplicationIcon();
                    str = "ۢ۫ۜۘ۬ۜ۫ۢۡ۬ۥۘۧۘۗۢ۟ۤ۫۬ۘۥۘۖۤۜۘۙۡۖۥۡۨ۟ۘۨۛ۫ۧۢ۠۠ۖۧۨۖ۬ۘۗۖۨۜۥۜۘ۠ۥۖۘ";
                    continue;
                case 1784226754:
                    layoutParams = new FrameLayout.LayoutParams(-2, -2);
                    str = "ۛ۫ۛۡۡۘۙۦ۠۠ۗۦ۬ۤ۠۟ۡۘۜۜۨۘۤۤۦۨ۟ۚ۫۟ۨۘۦۥۖۘ۬۟ۘۘ۬ۡ۫ۦۛۘۤ۟ۖۘۥۖۗۚۛۘۘۚۨۡۘ";
                    continue;
                case 1856997519:
                    str = "ۜۨۦۘ۟ۖۛ۫ۛۢ۠ۙ۬ۙۚ۫ۖۙۖۤۤۤۦۡۨۤۚۥۜۙۧۛۘۥۘۘۡۙۨۥۡۡ۬ۚۖۚۖۥۛۖ۫ۚۤۤۥۨ";
                    continue;
                case 2001071905:
                    gradientDrawable.setCornerRadius(dp(4));
                    str = "ۡ۠ۡۘۙۚۡۘۗۗۘۦۦۥۘۢ۟ۤ۬ۤۚۧۨۖۘ۬ۧ۫ۦۗۗۦ۬ۖۧۚ۠۬ۛۖۘ";
                    continue;
            }
            str = "ۛۜۨۜۢ۫ۡۥۡۘ۟ۥۗۚ۠ۙۧۗ۬ۘ۫ۥۙۥۘۛ۠ۢۦۤۨۢۥۧ۬ۚۦۘ۫ۜۥۘ";
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        String str = "ۢۛۤۧۢۘۥۤۘ۬ۙۖۘۡۜۗۘۛۚۚۙۘ۫ۨ۠ۘ۫۬ۦۦ۬ۢۗۙۤۚ";
        while (true) {
            switch ((((str.hashCode() ^ 881) ^ 917) ^ 806) ^ (-2083964856)) {
                case -182891816:
                    this.isDestroyed = true;
                    str = "ۗۡۛ۬ۛۙۧ۟ۛۦۙ۬ۧۨۦ۠ۜۥۘۖ۟ۖۘۛۢۘۚۗ۠ۛ۠ۙۤ۟ۧۗۗۡ";
                    break;
                case 702212326:
                    str = "ۥۤۘ۬۬۠ۢۙۛ۟ۨۘۘۛۜۘۡۤۜۘۨۜۨۘۧۦۡۜ۬۫۬ۘۖۨۙ۠ۦۢۚۥۧۖۘ۠ۗ۫";
                    break;
                case 1300888046:
                    super.onDestroy();
                    str = "۟ۢۡ۠ۦۜۘۛۢ۠ۦ۟ۥۡۡ۟۠ۜۘۜۛۖۘۖۥۧۘۜۜۘۦۖۖۨ۠ۜۧ۟۬ۗۡۘۥۦۦۘ";
                    break;
                case 1391890317:
                    this.handler.removeCallbacksAndMessages(null);
                    str = "ۦۨۜۨۨۗۧۘۨۛۨ۠۠ۗۡۨ۬ۧۧۧۜۥ۬ۘۚۤۡۘ۫۠ۧ";
                    break;
                case 2109698720:
                    return;
            }
        }
    }
}
