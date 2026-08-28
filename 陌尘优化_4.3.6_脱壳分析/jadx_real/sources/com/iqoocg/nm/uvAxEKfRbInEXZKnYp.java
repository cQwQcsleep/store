package com.iqoocg.nm;

import Ark.VMProtect;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;
import core.pro.android.notify.e0;
import core.pro.android.notify.i0;
import core.pro.android.notify.k2;
import core.pro.android.notify.l0;
import core.pro.android.notify.l2;
import core.pro.android.notify.s0;
import core.pro.android.notify.t3;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.lang.reflect.Method;
import java.util.Set;

/* loaded from: /workspace/unpacked/classes2.dex */
public class uvAxEKfRbInEXZKnYp extends Activity {
    private boolean isNightMode;
    private int rootBackgroundColor;
    private final Handler handler = new Handler(Looper.getMainLooper());
    private boolean isDestroyed = false;
    private boolean isRequesting = false;
    private final Runnable checkRunnable = new l0(0, this);

    static {
        VMProtect.ArkSafeVM(1432387398);
    }

    public static /* synthetic */ int access$000(uvAxEKfRbInEXZKnYp uvaxekfrbinexzknyp, int i) {
        String str = "ۨۥۛۛ۬ۦۘ۫ۦۤۚۚۜۙۛۙ۟۫ۚۡۘۢۘۘۙ۫ۚۜ۬ۧ";
        while (true) {
            switch ((((str.hashCode() ^ 546) ^ 556) ^ 711) ^ (-1731472749)) {
                case -1713381458:
                    str = "ۡۢ۟ۛۜۦۘۥۢۙۢۛۥۘۜۖۢۦۨۖۢۜۚۖۧۨ۟۟ۧۜ۟ۧۘۤۙۖۧۖۘۢۨ۠ۘۡۘۨ۫۠ۤۙۚ";
                    break;
                case -978466980:
                    return uvaxekfrbinexzknyp.dp(i);
                case 45547829:
                    str = "ۛ۟ۧ۬ۨ۠۬ۥۘۘۥۙ۬ۡۖۡۚۧۖۢۘۗۙۢۚ۟ۗ۠۠ۗۡ۟ۚۚۘۖۘۘۙۧۡۚ۫";
                    break;
            }
        }
    }

    public static /* synthetic */ boolean access$100(uvAxEKfRbInEXZKnYp uvaxekfrbinexzknyp) {
        String str = "ۨۜ۫ۨۧۖۘۗۖۤ۬ۥۡۘۛۙۡۚۙۘۘ۫۟ۚۙۜۙ۬ۖۘۘۥۧۦۘۖۨۘۨ۬ۨۚ۬ۜۧۤۜۘ۬ۨ۠ۙۙۘ";
        while (true) {
            switch ((((str.hashCode() ^ 232) ^ 746) ^ 964) ^ 1270876389) {
                case -660242998:
                    return uvaxekfrbinexzknyp.isNightMode;
                case 1351088741:
                    str = "ۖۤۡۘۗ۫ۛۥۙ۬ۖۜۧۘۚ۟ۦۥۚۤۖ۫ۨۘۢۢۤۖ۬ۘۦ۠ۙ۬ۖۚۥۦۨ۟ۤۚۥۥ۬ۦۜۢۖۖۦ";
                    break;
            }
        }
    }

    public static /* synthetic */ boolean access$200(uvAxEKfRbInEXZKnYp uvaxekfrbinexzknyp) {
        String str = "ۚ۫ۢۢۧۛۦ۫ۧۤۨۡۘۜۡ۬ۨۘۖۦۜۘۤۧۨۗۥۦۡۡ۟ۛۨۦۘۡۗۡۤۦۘۦۡ۬";
        while (true) {
            switch ((((str.hashCode() ^ 789) ^ 271) ^ 872) ^ 545692653) {
                case -2084891783:
                    str = "ۥۜۜۨۤۖۘۜۡۧ۫ۥۢۧۙۤۥ۫ۥ۬ۘۘ۬ۗ۫۫ۡ۠ۜ۟ۜۢۖۥۛۡۘۢۤۗ۠ۖۥ۫ۥۦۘۧۧۘۘۡ۟ۚۡۢۘ";
                    break;
                case 238015876:
                    return uvaxekfrbinexzknyp.isDestroyed;
            }
        }
    }

    public static /* synthetic */ boolean access$300(uvAxEKfRbInEXZKnYp uvaxekfrbinexzknyp) {
        String str = "ۛۥۦ۬ۧۚۚ۬ۙ۟ۛۤ۬ۜۘ۫۬ۜۘۥۢۚ۬ۦۥۘۤۖ۠ۖۖۧ";
        while (true) {
            switch ((((str.hashCode() ^ 880) ^ 826) ^ 747) ^ 1970745121) {
                case -2122049438:
                    str = "۫ۨۜۛۛۨۘۗۙۦۘۗۜۜۡۢۥۨۦۧۘۤۖۢ۫ۚۦۘۡۚۛ۫ۢ۬ۤۗ۬۬ۘۨۘۚۙ۬ۙۤۗۦۖۗ۬ۗۖۘۡۖۥۘ۫ۖۦۘ";
                    break;
                case 810680394:
                    return uvaxekfrbinexzknyp.isRequesting;
            }
        }
    }

    public static /* synthetic */ boolean access$302(uvAxEKfRbInEXZKnYp uvaxekfrbinexzknyp, boolean z) {
        String str = "ۥ۟ۥ۬ۥۙۤۡۗۢۛ۠ۗۚ۬ۙۖۘۘ۠ۦۘۜۘۥۘ۫ۥ۠ۚۘۧۘۦۙۖۚۖۘ";
        while (true) {
            switch ((((str.hashCode() ^ 768) ^ 904) ^ 46) ^ (-519820720)) {
                case -2097592569:
                    str = "ۢۖۗ۠ۜۧۘ۟۬ۚ۟ۦۖۚۦۘۗۗ۬ۗۥ۠ۤۥۖۘۚۖۜۘۨۖۥۘۨۡۥ۫ۨۘۘۡۙۧ۠ۥۧۘ";
                    break;
                case -1402025924:
                    return z;
                case 155660750:
                    str = "ۗ۬ۗۚۥۜۛ۬ۢۡۖۖۥ۬ۘۘۨۦۜۘۖۡۥ۬ۢۗۙۘۢ۫ۧۥۚ۫۟ۗۦ";
                    break;
                case 1202084838:
                    uvaxekfrbinexzknyp.isRequesting = z;
                    str = "ۗۖۦۘۘۗۘۢۜۥۙۖۨ۬ۖۘۘۢ۫۫ۘۥۗ۬ۡۨۘۨۦۨۖ۫ۘۘ۬ۥۘۘۡۤۦ";
                    break;
            }
        }
    }

    public static /* synthetic */ Handler access$400(uvAxEKfRbInEXZKnYp uvaxekfrbinexzknyp) {
        String str = "ۚۘۜۘۘۨۡۘۗۡۛ۫۟ۗۢۦۖۖ۠۟ۖ۫ۖۗۥۛ۠ۘ۬ۗۗۖۚ۠ۧ";
        while (true) {
            switch ((((str.hashCode() ^ 371) ^ 344) ^ 278) ^ 1400142919) {
                case -2058766553:
                    str = "۬۠ۨۨۗ۫ۧۥۖۘۦۦۥۦۨۥۘۙۜۜۘۨۚۤۙ۬ۙۚۙۡۢۗ۠ۧۖۦۥۨۘۧۨۧۤ۬ۜ۟ۤ۬ۘۖۖۘ۟ۦۘۘۗۦۨۘ";
                    break;
                case 2074385961:
                    return uvaxekfrbinexzknyp.handler;
            }
        }
    }

    public static /* synthetic */ boolean access$500(uvAxEKfRbInEXZKnYp uvaxekfrbinexzknyp) {
        String str = "ۥۥ۬۫ۥۦ۫ۙۗۘۧۧ۠ۥ۫ۨۡۖۘۨۧۧ۟ۜۨ۬ۚ۬ۢ۠ۚ۠۟ۚۜۙۗۗۘۛۡۘ۬۟ۦۛۛ";
        while (true) {
            switch ((((str.hashCode() ^ 418) ^ 4) ^ 679) ^ 1676089864) {
                case -94799321:
                    return uvaxekfrbinexzknyp.hasSelfType2Window();
                case 1646512477:
                    str = "۟ۡ۬ۚۜ۬۫۟ۢۤ۫ۗۚۖۛۦۖۘۡۤۖۦۥۜۜۙ۟ۦۜ۬ۗۘۜۘ۬ۖۜۘۢ۫ۖۖ۫ۦۧۡۦۘۥۛۜۥۗ۫ۨۖۧۘ";
                    break;
            }
        }
    }

    public static /* synthetic */ Runnable access$600(uvAxEKfRbInEXZKnYp uvaxekfrbinexzknyp) {
        String str = "۠ۖۗ۫۬۠ۡ۟ۜۨۦ۫ۗۚ۠ۢۢۗۛۚۡۤ۫ۥۘۚۡۨۚۜۡۘۢۤۘۘ۫ۨۧۘۜۥۛۢ۫ۢۙ۠۫ۦ";
        while (true) {
            switch ((((str.hashCode() ^ 602) ^ 406) ^ 728) ^ 214002804) {
                case -914014498:
                    str = "۠ۡۗۙ۬ۥۨۘۗۡۙۘۢۛۦ۠۬ۥۘۚۘۖۘۖۤۖۢۖۥۘۚۤ۬";
                    break;
                case 1783776970:
                    return uvaxekfrbinexzknyp.checkRunnable;
            }
        }
    }

    public static /* synthetic */ void access$700(uvAxEKfRbInEXZKnYp uvaxekfrbinexzknyp) {
        String str = "ۚۗۨۘۨۚۡ۟۟ۘۧۘۖۘۚ۠ۜۘۡۦۘۘۡۨۨۦۜۖۘۧۖۨ۟۠ۡۚۜۜۢۤۗۙۥۨۙۧۥ۠ۨۚ۫ۦ۟";
        while (true) {
            switch ((((str.hashCode() ^ 953) ^ 975) ^ 520) ^ 1664236694) {
                case -1930735440:
                    str = "ۙۖۧۥۙ۬ۗۡۘ۟ۤۘۘۘۘۥۘۡۦۧۤۗۦ۫ۗۢۘۚۧۢۡۖ۬ۦ۠ۥۛۥۘ";
                    break;
                case 254125400:
                    uvaxekfrbinexzknyp.launchNextActivity();
                    str = "ۨۡ۫ۛ۟ۦۘۙۨ۠ۙۤ۫ۙۧ۟ۙۘۙۜۡ۬۬ۧ۟ۧۤ۬ۦۖۦۘۛۘۛۗۥۘۗ۫ۧۖۦۘۘ۟ۙۖۘۢۗۨۘ";
                    break;
                case 445932844:
                    return;
            }
        }
    }

    private void clearAllPopupIdCache() {
        String str = "ۤۥۢۡ۠ۤۧۙۖۘۛۨ۟ۥۗۚۤۨۘۙۙۤۙۨۨۘۖۥۜ۠ۗۡۡۨۖۨ۫ۛ";
        while (true) {
            switch ((((str.hashCode() ^ 237) ^ 612) ^ 738) ^ (-2136529492)) {
                case -1612127063:
                    System.out.println(l2.decrypt("xMeXdCailgT69opZczdi2nkvfeP6aDDtN357rLZubI81ALFAtG5GjTIPF7jbvLAN+7fTJDC+uhv6\n8xe42g==\n", "n5f4BFPS1Wg=\n"));
                    str = "ۤۚۡۘ۟۫۠۬ۦۧۘۙۨۚۨۙۨۘۜۡ۟ۖۖۙۥۚۤۙۛۙۤۤۦۖۜۧۘۖ۠۠";
                    break;
                case -1498321179:
                    t3.needTextPopupIds.clear();
                    str = "ۜ۠ۨۘۚۨۨۨۙۥۘ۠۠ۨۢ۟ۥۘۢ۫ۚۤ۠ۤۘۨۡۘۢ۬ۜۘۙۡۥۜۧ۟ۘ۠ۛۨۤۢ۫";
                    break;
                case -1122326636:
                    k2.clearPopupLifecycleCache();
                    str = "ۢ۠ۥۧۦۜۘۛۥۧۘۛۦۘۢۦۨۘۡۧۙۨ۫ۨۢۚ۠ۗۗۨۘۦ۬ۖ";
                    break;
                case -904189981:
                    t3.needHtmlPopupIds.clear();
                    str = "ۗۘ۬ۡۢۗۡۗۖۛۨۦۢۥۧۗۙۖۘۧۖۧۙۛۡۘ۟ۤۗۖۗۡۢ۠ۗۙۖۘۚۦ۫۠ۨ۬";
                    break;
                case -886481592:
                    t3.needMessagePopupIds.clear();
                    str = "ۗۤۡ۟ۜۡۥۘۦۢۗۛ۬ۚۢۧۨ۟ۛۦۘۚۖۥ۟۟ۜۘۢۙۡۛۡۘۘ۬ۡ";
                    break;
                case -758749859:
                    return;
                case 391712133:
                    k2.closedHtmlPopupIds.clear();
                    str = "ۙۤۜۚۙۖۡۚۤۦۜۤۥ۬ۢۙ۬۟۠ۢۨۘۥۗۜۘۦۖۨۜۨ۠ۡۛۖۘ۬ۘۥۘۘۘۗۥۡۗۜۙۚۜۛ۟";
                    break;
                case 473421997:
                    t3.needFullscreenPopupIds.clear();
                    str = "ۖ۠ۗۜۡۖۢۢۦۥ۠ۘۗۗۡ۟ۧ۟ۤۡۘۧۘۥۘ۠۠ۥ۠۠ۘۘ";
                    break;
                case 1131486512:
                    t3.needImagePopupIds.clear();
                    str = "ۘ۟ۦۘۘۗۨۘ۠۬۠ۖۢۨۘۚۨۘۗۘ۫ۚۜۥۧ۟ۢۘۧۗۢۖۜۘۘۜۡۨ۫ۛ";
                    break;
                case 1667083226:
                    k2.closedMessagePopupIds.clear();
                    str = "ۜۨۧۚۤۡۚۧۚ۟ۥۥۘ۠ۘۧۡۦۘ۫ۖۖۘۘۢۨۘۗ۬ۢۤۘۦۡۛۚۙۦۘۘ";
                    break;
                case 1839100196:
                    str = "ۚۤۥۘۜۙ۠ۚۖۡۘ۬ۦۢۢۚۜ۠ۨۜۗۨۛۛۙۗۡۨۚۤۡ۬";
                    break;
                case 1979953311:
                    k2.closedTextPopupIds.clear();
                    str = "ۡ۬ۥۘ۟ۘ۫ۜۡۧۖ۠ۗۥۨ۫ۜ۬۠ۗۗۦۜ۬ۛۖۜۘۢ۫ۚۗۜۛۖۘ۠ۦۗۦۧۘ۫ۗۘۘۢۡۛ";
                    break;
                case 1980021225:
                    k2.closedImagePopupIds.clear();
                    str = "ۚۙۗۡ۬ۙۚۡۨۚۗۥ۬ۜ۫ۨۙۨۛۜ۬ۛ۠ۗۚ۬ۘۢۜۖۘ";
                    break;
                case 2009551367:
                    k2.closedPopupIds.clear();
                    str = "ۦۜۡۘۡ۫ۦۘۢ۬ۨۤ۫ۡۙۛۡۘۚۡۘۘ۟ۛ۫ۤ۟ۗۡ۫ۗۤۦۧۘ";
                    break;
            }
        }
    }

    private int dp(int i) {
        String str = "ۡۤۚۘۖۡۢ۬ۜۘۡۖۡۘۘۥ۟ۖۙۦۘۢ۟۬ۘۛ۠۠ۥۚۢۡۨۧۗۙۡۦۘ";
        while (true) {
            switch ((((str.hashCode() ^ 179) ^ 242) ^ 229) ^ (-1703238390)) {
                case -1841363153:
                    return (int) TypedValue.applyDimension(1, i, getResources().getDisplayMetrics());
                case -1758948089:
                    str = "ۦۖۘۥ۠۫ۢۘۦۘۧۗۡ۬ۗۨۘ۟ۘ۬۫۫ۧۜ۬ۙۤۖۚ۠ۖ۫ۙ۠ۖۧۗۚ";
                    break;
                case 1997278811:
                    str = "ۤۡۥۜۜۧ۠ۨۧۘۨۡۦۦۙۘۘۨۤ۫ۙۥۧۘۗۜۥۘ۬۠ۖۤۜۧ";
                    break;
            }
        }
    }

    private String getAppVersion() throws PackageManager.NameNotFoundException {
        try {
            return l2.decrypt("3O6C8WreTHk=\n", "iovwggOxIlk=\n") + getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
        } catch (Exception e) {
            return l2.decrypt("eJHmesRVeU8R\n", "LvSUCa06F28=\n");
        }
    }

    private Drawable getApplicationIcon() throws IOException {
        Drawable drawableCreateFromStream;
        String str;
        try {
            InputStream inputStreamOpen = getAssets().open(l2.decrypt("hvtJw6D1A1E=\n", "6pQurI6FbTY=\n"));
            drawableCreateFromStream = Drawable.createFromStream(inputStreamOpen, null);
            inputStreamOpen.close();
            str = "۫ۧۦۧۛۘ۠۠ۚۖۥۖۘۗۗۦۙۢۖۤۧۧۨ۬ۦۘۦۙۦۘۨۙۗ";
        } catch (Exception e) {
        }
        while (true) {
            switch (str.hashCode() ^ 49401894) {
                case -1086010953:
                    try {
                        return getPackageManager().getApplicationIcon(getPackageName());
                    } catch (Exception e2) {
                        return null;
                    }
                case 581873177:
                    return drawableCreateFromStream;
                case 1058235865:
                    String str2 = "ۢۛۧۥۡۙۨۚۗۖۥۦۘۘۡۘۧ۫ۤۖ۟ۨۘۦۘۙۨۧۖۘ۟ۛ۫ۙۧ۠ۧ۠ۗۛۖۧۨۘۛۛ۫ۧۦۚۦ";
                    while (true) {
                        switch (str2.hashCode() ^ (-1708760628)) {
                            case 426081825:
                                str2 = "ۗۤۖۛ۬ۡ۬ۜۖۘۚۗۜۘۛ۠ۢۘۗۜۘۗۥۧۘۦۧۛۦۛۦۘۙۧ۠ۦۥۡۘۜۧۢۨۘۜۘۚۧۜۘ۫ۦۛۚۨۙ";
                                break;
                            case 852155478:
                                str = "ۘۦۘۘۧۤۖۡۥۘ۫ۙۛۥۧۖۘ۬ۖ۟ۨۜۥۘۖ۫ۗۛۨۤۧۤۗ";
                                continue;
                            case 1837973858:
                                if (drawableCreateFromStream == null) {
                                    str2 = "۫ۚۜۗۛۨۘۤۦۤۜۜۡ۠۟ۤۤۢۚۛۥۘۘ۫ۜۡۥۢ۬ۢۦۨۘۤۙۖۘۙۥۡۘۘۛۗ۠۫۫";
                                    break;
                                } else {
                                    str2 = "ۗۡۡۘۦۧۛۦ۬ۦۘۢۢۤۨۚ۠ۖۥۘۛۤۖۘۘۧۤ۟ۛۤۘۦۢۨ۫ۖۘ۠ۖۡۘۦۜۧۘ۫ۤۧ";
                                    break;
                                }
                            case 1936275399:
                                str = "ۧ۫۫ۨۘۨۘۖۖ۠ۢۤۨ۟ۥۖۡ۟ۚ۟ۖۡۘۧ۬ۖۘۙ۬ۢ۫ۖۖ۟ۢ۬ۦۧۦۘ";
                                continue;
                        }
                    }
                    break;
                case 1778498292:
                    str = "ۤ۫ۚۖۘۘۘۥۖۖ۫ۜۥۘۧۥۗ۟ۚۜۘ۫ۨۜۛۜۘۗۧۥۦۚۡ۬۬ۖۘۛ۬ۘۘ۠ۢۢ۫۬ۗۛۧۗۤۙۤ۠۠ۡۦ۫ۦۘ";
                    break;
            }
        }
    }

    private boolean hasSelfType2Window() {
        int i;
        l2.decrypt("4Iwl/KnydQne7R0=\n", "u99AkM+mDHk=\n");
        try {
            Class<?> cls = Class.forName(l2.decrypt("ph7yIAPUkJuxGfMlQuqd26Mf4R8N05XSogLRPgPfldk=\n", "x3CWUmy99LU=\n"));
            Object objInvoke = cls.getMethod(l2.decrypt("CTlS1shbd7IAP0M=\n", "blwmn6YoA9M=\n"), null).invoke(null, null);
            Method declaredMethod = cls.getDeclaredMethod(l2.decrypt("3MVUGkeofW3Uz1QCT6BvTA==\n", "u6AgTC7NCj8=\n"), null);
            Method declaredMethod2 = cls.getDeclaredMethod(l2.decrypt("bC6hh9MFdSZiLqI=\n", "C0vV1bxqAXA=\n"), String.class);
            declaredMethod.setAccessible(true);
            declaredMethod2.setAccessible(true);
            String[] strArr = (String[]) declaredMethod.invoke(objInvoke, null);
            String str = "۫۬ۦۘۢۥۦۖ۬ۗۗۘۚ۫ۡۙۤۤۛۜۨۦۢ۟ۜۘۛۚۥۘۡۗۡۘۥۥۜۚۥۧۘۘ۬ۨۘۚۤۗۚۨۢ۫ۨۧۘۦۜۧۘ۠ۜ";
            while (true) {
                switch (str.hashCode() ^ 829555495) {
                    case -1933183511:
                        str = "ۨۖۗۙۛۖۨۚ۟۫ۨۘۖۛۢۙ۟ۡۨۗ۟۠ۗ۫ۘۘ۟ۛۘۚ۬ۙ۟ۜۨۘۖۥ۬ۗ۬ۨۘۧ۠۠ۗۡۦۘۦۖۥۢۦۧ";
                        break;
                    case -514353517:
                        int length = strArr.length;
                        int i2 = 0;
                        int i3 = 0;
                        while (true) {
                            String str2 = "ۢۖۡۘۘۚۦۘۛۥۜۘ۠ۙۨۨۗۖۥۢۡۘۢ۠ۖۘۢ۫ۨۗۛۡۘۤۙ۟۠ۗۡۘۜۥۥۡۛۢۤۜ";
                            while (true) {
                                switch (str2.hashCode() ^ (-846778958)) {
                                    case -1736532319:
                                        String str3 = "۠ۦ۫ۗ۫ۜۘۥۦۨۗۘۖۘ۠ۦۥۘۜ۠ۥ۟ۦۢۗۙۨۥۘۜۘۘۛۖۘۥ۠ۡۖ۠ۖۖ۬۫۠ۗۦۘ";
                                        while (true) {
                                            switch (str3.hashCode() ^ 681488152) {
                                                case -1509465440:
                                                    str2 = "ۧۘۚ۫ۢۖۤۥ۬ۧۚۨۘۨۤۙۧ۟ۨۡۢۨ۫ۧۨۧۢ۬ۚۚ";
                                                    break;
                                                case 350484029:
                                                    str3 = "۟ۧۨۘۛۜۜۤۙ۟ۘۡۥۗ۟ۜ۬ۖ۬ۦ۬ۜۘ۬ۗۢ۫ۙۨ۬ۡۨۘۗ۟ۦۘۚۤۥۡۤۗۘۧ";
                                                case 821566167:
                                                    str2 = "۫۠ۛۙۧۧۛۦۘۨۙۦۘ۟ۥۧۘۖ۬۠ۖۡۗۗۨۧ۬ۙۡۡۖۘ";
                                                    break;
                                                case 2044281694:
                                                    str3 = i3 < length ? "ۨۦۘۥۨۛۥۛۤ۬ۤۜۢۧۡۘۗۚۦۨۛۡۘ۟ۖ۬ۥۥۖۘۤ۠ۘۜ۠ۥۥۡۘۖۢۘۘۖۡۙ" : "ۖۛۢۧۦۥۢۥۖۗۜۙ۠ۚۚۦۢۙۛۧ۠ۥۦۖ۠ۨۘۚۧۤ۠ۤۥۘۡ۠ۧۨۥ۬ۙۨۦ۫ۗۨۘۥۨۖۘۡۖۦۖ۟ۧ";
                                            }
                                        }
                                        break;
                                    case -680461571:
                                        str2 = "ۗۙ۫ۧۤۚۖ۫ۖۘۤۛۦ۠۬ۦۘۗۘۜۜ۟ۖۧۜۢۢۢۘۘ۟ۨۨ۫ۙۧۥۛۨۦۜ۠ۜ۟ۦۘۦۥۧۘۥۨۥ";
                                        break;
                                    case -235351830:
                                        View view = (View) declaredMethod2.invoke(objInvoke, strArr[i3]);
                                        String str4 = "ۘۗۗۙۜۖۘۗۡۜ۠۠۟ۥۡۜۦۤۜۘۢ۠۟ۧۦۘۚۦ۠ۘۨۘ۟ۖۧۘۢۨ۟ۤۘ۟ۖۧۖۘ";
                                        while (true) {
                                            switch (str4.hashCode() ^ 1069388617) {
                                                case -2107520516:
                                                    Object tag = view.getTag();
                                                    String str5 = "ۢۢۘۘۦۢۤۨۖۨۘۛۘ۠ۖۘۘ۫ۖۘۜۨۥۖ۬ۚۘۥۘۢ۬۬ۛ۟ۡۘ۠ۚ۬۬ۜۜۖۢۖۢۜۥ۟";
                                                    while (true) {
                                                        switch (str5.hashCode() ^ (-498631225)) {
                                                            case -2072459553:
                                                                str5 = "ۧۡۖۘۢ۟ۨۘۥۘۚ۟۟ۥۗۛۧ۟۬۠ۨۛۘۖ۠ۦۘ۟ۧ۠۟ۢۨۥۙۨۜۡ۫ۡ۫۫۠۫ۖۘۚ۬۟ۛ۠ۧۢۡۢ۠ۦ";
                                                            case 682894568:
                                                                String str6 = "۫۬ۨۡ۬ۛۘۙۖۘ۟۫۟ۦۤۧۤ۬ۖۘۨۛۥۘ۠ۜ۟ۘ۠ۖۧۜۜۚۙ۫ۙۜۘ";
                                                                while (true) {
                                                                    switch (str6.hashCode() ^ 1139263062) {
                                                                        case -517040170:
                                                                            break;
                                                                        case -134949616:
                                                                            str6 = "ۛ۫ۛۖۥ۠ۗ۟ۡۘۨۗۨۘۦۡۢۖ۠ۦۘۚۨۤۧۗۥۘۗ۫ۢ۬ۤۦ۬ۧۧۤ۫۫ۘۤۢۘۛ";
                                                                        case 1791867909:
                                                                            String str7 = "۠ۙۦۘۨۤ۠ۛۧ۟ۡۥۛۖۙۖۥ۠ۖۘۥ۠ۖۘۡۧۛ۫ۡۖۤۖۛ۠۠ۘ۬ۥۚۖۚۜۘۦ۬ۥۡ۫ۜۖ";
                                                                            while (true) {
                                                                                switch (str7.hashCode() ^ 402427010) {
                                                                                    case -1522106872:
                                                                                        str7 = "ۚۡۧۘ۬ۙۚۦۙۘۘۤۙۘۘۢۚ۟ۙۡۢۡۢۚۦ۫۬ۢ۫ۛ۟ۚۗ۠۠ۦۙۘۨۘ";
                                                                                    case -1365023185:
                                                                                        str6 = "ۨۡۙۥۘۦۡ۟ۗۖ۠ۥۘۨۗ۟۟۬ۙۥۥ۫ۜۧ۟ۥۜۜۖۥۘۧۤۜۙ";
                                                                                        break;
                                                                                    case -456559449:
                                                                                        str6 = "ۤۦۜۘۨۗ۫ۖۨۜۚۖۘۢۜ۠ۡۜۧۘ۬ۢۢۨ۠ۘۘۘۚۙۤۙۘۙۗۖۘ۫ۛۢۘۘۚۛۨ۟ۤۡۤۙ۠ۛ۟ۜۛۤۜۥۘ";
                                                                                        break;
                                                                                    case 1858125422:
                                                                                        str7 = ((String) tag).contains(l2.decrypt("CL2eTC/fy1UUoZ5DN+Xf\n", "e9X7IEOAuyc=\n")) ? "۬ۡۚۜۨۧ۫ۛۘۘۗۖۖۜۚۡ۟ۛۡۥۗۚۚۥ۬ۦۙۥ۠ۛ۫" : "ۘۙۦۘۜۦۛۚۥۡۘۗۚۤۙۜۥۘۡۧ۫ۗۤۦۧۗ۟ۘۜۢۘۖۦۘ۠ۜۜۘۛ۟ۦۘۘۙۨۜۥۢۤ۫ۘۚۨۚ";
                                                                                }
                                                                            }
                                                                            break;
                                                                        case 2067457553:
                                                                            ViewGroup.LayoutParams layoutParams = view.getLayoutParams();
                                                                            String str8 = "ۥۦۘۚۘۧۘۦۢۚۙۦۗۘ۟ۨۘۨۛۡۧ۫ۖۘۛۨۘۘۖۖۛۧۖۧۥ۠ۘۘۖ۟۬";
                                                                            while (true) {
                                                                                switch (str8.hashCode() ^ 41645393) {
                                                                                    case -934976338:
                                                                                        i = i2;
                                                                                        break;
                                                                                    case 56441650:
                                                                                        int i4 = ((WindowManager.LayoutParams) layoutParams).type;
                                                                                        String str9 = "ۗۚۨۘ۬ۖۘۥ۟ۜ۬۫ۘۘۧۡۘۘۙ۟ۦۘ۫ۤ۟ۥۡۘۘۢ۫۬۫ۙۚۥ۟ۚۡۢۤۘ۠ۦۜۘۦۖ۬ۦ۠۠";
                                                                                        while (true) {
                                                                                            switch (str9.hashCode() ^ 1541436948) {
                                                                                                case -1346581350:
                                                                                                    String str10 = "ۗ۫ۖۙ۟ۡۘۦۚ۫ۧ۫ۖۘ۠ۘۨۘۗۡۘۡ۠ۘۘۜۡۜۘۡۖ۫ۛۚۛ";
                                                                                                    while (true) {
                                                                                                        switch (str10.hashCode() ^ (-436076963)) {
                                                                                                            case -1328671657:
                                                                                                                str9 = "ۦۡۜۨۥۗۗۤۨ۬ۙۛۘۙ۟ۧۤۛۚۚۙۧ۠ۧۡ۟ۥۘۦۜۘ۬ۢ۬ۛۛۡۢۘۦۘ۫ۘۢۦ۫ۧۨ۬";
                                                                                                                break;
                                                                                                            case 525247322:
                                                                                                                str9 = "ۨۚۖۥۦۥۖۙۚۧ۟ۘۘۜۤۧۦۛۗۦۛۖۢۨ۟ۖۘۖ۠۫۠ۤۙۚ۬ۗۖ";
                                                                                                                break;
                                                                                                            case 747761459:
                                                                                                                str10 = i4 == 2 ? "ۖۖۨۘۡ۟۠ۜۨۛۛ۫ۢۧۘۡۚۖۘ۟ۥ۬ۡۘۖۜۛۚۨ۬ۥ" : "ۙ۬ۨۥۤۖۘۜۧۢ۫۬ۥۘۘۥ۟ۘۤۛۙۛۖۡ۫ۢۦۖ۠۫۫ۧۧ۟ۖۤۛۘۚۡ۟۠ۘۧۘ۫ۥۦۘۗۡۘۘۜۚۗۜۛۘ";
                                                                                                            case 1301033405:
                                                                                                                str10 = "ۥ۠ۦۘ۫ۡۜ۫ۜۦۦۥۨۘ۫ۖ۟ۢۥۖۖۖ۬ۨۢۘۚۥۧۗۢۛ۬ۨۖۘۗۤۚۙۙۡۛۗۧۘۖۥۤۛ۟";
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case 1823424719:
                                                                                                    i = i2 + 1;
                                                                                                    break;
                                                                                                case 1915353733:
                                                                                                    break;
                                                                                                case 1988246912:
                                                                                                    str9 = "ۚۧۥۘۜۦ۬ۡۚۚۗۨۘۖۙۥۖۚۜۘۤ۬ۤۛۤۘۨۡۘۗ۠ۖ";
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case 810513461:
                                                                                        str8 = "۠۬ۨ۠ۘ۫ۥۡ۟ۗ۠ۥۢۜۚۧۡۖۘۤ۟ۗ۟ۖۡۘۤۢۦ۫ۦۦۘۤۧۨ۫ۘۚ";
                                                                                    case 1019875106:
                                                                                        String str11 = "ۖۖۥ۫ۢۡۘۤۡۦۘ۬ۢۜۢۘۜۦۜ۬ۖ۬ۢۢۦۥۘۚ۬ۨۘۦ۬۠۫ۘۘۖۨۘۘۗۖۧۗۚۚۜۗۢۤۤۥۘۧۙ۬ۘ۫ۨۘ";
                                                                                        while (true) {
                                                                                            switch (str11.hashCode() ^ 1507913904) {
                                                                                                case -1984591114:
                                                                                                    str11 = !(layoutParams instanceof WindowManager.LayoutParams) ? "ۚۜۦۘۡۖۘۦ۬۟ۧۡۖ۠ۨۘ۟ۥۥۘۦۥۡۚ۫۫ۨۘۘۘ۫ۦۜۘ۬ۗۡۘ" : "۫۫۬ۢ۫ۥ۬ۨۜۤۦۜۘۛۗۦۘۤۡ۫۬ۢۜۘۢۦۡۜ۫۟ۡۦۘۙۡۚ۠۬ۛۡۚ۫ۥۗۘۘ";
                                                                                                case -1475638821:
                                                                                                    str8 = "۠ۥۨۗ۠۬ۤۖۧۛ۟ۙۙۜۘۙۜۧۘ۟۬ۖۘۛۢ۟ۡۜۦۘۧۜۖۘ";
                                                                                                    break;
                                                                                                case -1127854213:
                                                                                                    str8 = "۬ۨۥۘۡۚۤۤۥۤۚ۠ۙۢ۬ۦۘۥۢۘۧۧۖۘۧۦۧۘۧ۫ۡۘۡ۬ۧۡۢۦۤۗۚۧۜۘۘۥ۠ۜۘۡ۫ۗ";
                                                                                                    break;
                                                                                                case 1091376680:
                                                                                                    str11 = "ۜۜۚۨۡۥۘ۟ۥۛۧۧۜ۠ۢۨ۫ۜۛ۟ۤ۟ۤۜۧۘۥۡۙ۬ۡۜۘ۫ۙۥ۬ۥۡۘ";
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                }
                                                                            }
                                                                            break;
                                                                    }
                                                                }
                                                                break;
                                                            case 699093428:
                                                                String str12 = "ۘۢۨۗ۟ۖۘ۟ۢۙۖۧۨۡۛۧ۫ۥۖۙ۟ۘ۠۠۫ۢۙۧۧۢۤۧ۟ۤ";
                                                                while (true) {
                                                                    switch (str12.hashCode() ^ 1850035923) {
                                                                        case -1097429829:
                                                                            str12 = "ۨۛ۫ۧۨۘ۠ۘۗۦۙۛۡۧۥۘۗۧ۬۫ۧۜۘۗۗۦ۟ۙۘۧۢۡ۬ۤۚۗۘ۬ۢ۬ۥۥۖۥۘۥۜۜۛۧۨۜ۬ۡۘۦۖۘۘ";
                                                                        case -284706518:
                                                                            str5 = "۫ۥۘ۠۟ۦۦۛۦۘ۟ۡۖۘۥۜ۬ۚۢۙۦۜۜ۠ۧ۫ۤۘۘۚ۫ۥۥۙۨۘ۬ۥۗ۠ۥۜۘ۟ۥۧۘۨۚۥۘ۟۫ۖۘۗ۠ۨۗۙۨۘ";
                                                                            break;
                                                                        case 663290618:
                                                                            str5 = "ۤ۠ۥ۟ۚۦۤۙۨۘ۫ۡۘۗۧۧ۬ۜ۬ۡۦۨۘۗۛ۬ۜ۫۫ۗ۟ۜۖ۬۟ۨۖۖۘۛۤۨۗۤۦۘۦۗ۫۫ۖۧۙۖۡۦۢۦ";
                                                                            break;
                                                                        case 1312774673:
                                                                            str12 = tag instanceof String ? "ۡۙۨ۟۬۟ۨۛ۠ۜۘۧۙۗۛۗۤۨۘ۟ۤۚۙۦۥۜۡۦۘۨۥۛۙۗۜۗۢۙۗ۠ۡۡۚۘۘۚ۠ۘۘۙۨۨۙۛۡۢۧ" : "ۤۛۥۚۗ۠۫ۖۥۙۥ۟ۗۢۡ۬۫ۢۤۥۛۦ۬ۗۙۛ۠ۨۡۧ۟۟ۨۘۡۡ۫";
                                                                    }
                                                                }
                                                                break;
                                                            case 1864349101:
                                                                break;
                                                        }
                                                    }
                                                    i = i2;
                                                    continue;
                                                case -1675795552:
                                                    String str13 = "ۘۖۛۦۘۜۘۡۚۤۖۜۘۙۡۤۚ۟ۥۘۥۢۛۡۜۦۘۢۖۧۘۥۜۡۘ";
                                                    while (true) {
                                                        switch (str13.hashCode() ^ 105635160) {
                                                            case -1840507681:
                                                                str4 = "ۢۚۦۦۦ۫ۚۧۡۘ۫۬ۙۧۙۜۧ۠۠ۘۥۧ۟ۦ۠ۡۗۘۘۚۥۢۧ۬ۡۙۥۨ";
                                                                break;
                                                            case -1141649005:
                                                                str4 = "ۨۖۡۘۚۘۧۨۙۤۡ۫ۨۘۥۤۤۙۚۡۘۦۚۙۙ۟ۡ۬۟ۚۦۜۖۘ";
                                                                break;
                                                            case 380274116:
                                                                str13 = view == null ? "ۚۨۡۘ۫ۙ۟ۚۜۖۚۡۦۘۦۛۤۚۦ۟۟ۜۧۘۤ۠۬ۜ۬ۢۘ۠ۤۡۗ۫ۢۘۜۘۖ۠ۖۘ۫ۗۡۘۗۚۥۧۦۘۘۧۥۧۗۨۧۘ" : "ۚۤۧۗۖ۟۠ۨۘۥۧ۬۫۟ۖۙۙۢۘۡۗ۟۟ۥۢ۠۠ۧۢۛۢ۫۬ۙۜۘۦ۟ۨۘۨۦۘۘ";
                                                            case 1127346977:
                                                                str13 = "ۥۛۖۢۜۘۢ۫۬ۥۚ۠۬ۡۢۤۚۚۜۜۗۡۤ۠۠ۛۨۦۘۚۨۦۜۘ۠ۘۡۘ";
                                                        }
                                                    }
                                                    break;
                                                case -1310260089:
                                                    str4 = "ۛۘۘۘ۫ۘۥۦۢۖۢۖۦۛۥ۫ۚ۫ۥۙ۫۫ۜ۟ۥۘۢۧۧۦۘۚۘۨۨ۬ۜۘ۫ۡۖۘ۫ۜۜۘۖۙ۫ۜۛۡۘ";
                                                    break;
                                                case -977662701:
                                                    i = i2;
                                                    continue;
                                            }
                                        }
                                        i2 = i;
                                        i3++;
                                        break;
                                    case -209606125:
                                        String str14 = "ۢۧۥۜ۠ۗۤ۫ۥ۠ۛۨۦ۠ۥۢ۫ۙۧۖۖۘۖۖۡۘۨۥۙ۫ۨۘۘۘۡ۬ۦۧ۬ۢۧۖۘۜۤۘۛۚۤۢۦۜۘۦۧ۬ۥۗۙ";
                                        while (true) {
                                            switch (str14.hashCode() ^ 333367391) {
                                                case -1398064136:
                                                    return true;
                                                case -251446481:
                                                    str14 = "ۤ۠ۗۤۙۦۘۖۧۚ۬۠ۤ۠ۡ۬ۡۤۨۧۛۙۥ۟ۜۙۜۘۡۨۤۛ۟ۥۘۚ۠۬";
                                                    break;
                                                case 360936489:
                                                    return false;
                                                case 933640949:
                                                    String str15 = "۬ۜ۠ۚۡۨ۬۠ۥۘ۫۟۠ۛ۠ۦۘ۬ۗۧۖۖۨۚۥۧۘۖۢ۫ۚۢۙۦۚۨۘۨ۠ۜۘۤۨ۟۠ۛ۬ۖۜۨۦ۠ۤ";
                                                    while (true) {
                                                        switch (str15.hashCode() ^ (-929219731)) {
                                                            case -1877818986:
                                                                if (i2 <= 0) {
                                                                    str15 = "ۛۜۙۨۢۢۨۜۙ۬ۦۖۘۚۜۤۧۤۨۘۚ۟۬۠ۚۧۙ۠ۢۖۧۦ";
                                                                    break;
                                                                } else {
                                                                    str15 = "ۖۡۘۘ۠ۨۨۘۜۦۘۘۙۨۥۥۛۡۜۧۡۚۤ۬۫۠ۜۘۛۤۗ۠ۡۦۙۥۨۨ۟ۨ۫ۨۥۘۥ۠ۨ";
                                                                    break;
                                                                }
                                                            case -1642808831:
                                                                str14 = "ۘۨ۫ۦ۬۠ۙ۫ۨۘۤۨۙۖ۫ۖۘۢۤۥۖۘۨۚۦۦۘۙۤۥۘۤۨ۟۫ۚ۬ۗۧ۠ۜۘۛۗۜ۠ۢۖۙۡۢۖ";
                                                                continue;
                                                            case 1589234559:
                                                                str15 = "ۗ۟ۗۡ۫۬ۖۦۨۙ۟ۨۘ۠ۢۖۡۛۦۗ۬۠ۡ۟ۖۘۨۛۘۚۦۥۘ";
                                                                break;
                                                            case 1868391571:
                                                                str14 = "۟ۦۨۗۥۡۘۦۖۨۘۛۤۗۚ۫ۚۥۛ۠ۛۤۧۥۗ۠ۖ۫ۛۗۤ۫ۥۘۦۖ۬ۙۖۧۛۥۜۤ۬ۖۥۛۛۙۛۚۘۜۧۘ";
                                                                continue;
                                                        }
                                                    }
                                                    break;
                                            }
                                        }
                                        break;
                                }
                            }
                        }
                        break;
                    case 867252741:
                        System.out.println(l2.decrypt("dv5i45GEkL5In1qvgbmMuWPMauqE7Ye7QcE=\n", "La0Hj/fQ6c4=\n"));
                        return false;
                    case 1136234469:
                        String str16 = "ۗۨۨۘۥۦۡۘۛۗ۟ۧۛۡۘۤ۠ۘۘۦۙۚۡ۫ۖۥۛۘۡ۠ۨۘ۫ۙۘۨ۬ۙۗۚۛۜ۟۟۟ۦۥۤۤۡۘۙۡۖ";
                        while (true) {
                            switch (str16.hashCode() ^ (-779835967)) {
                                case -1229997421:
                                    str = "ۗ۠ۨ۟ۜۡۘۙۨۡۙۦۘۖۛۨۘۢۛۘۨۡۘ۫ۗۨۙۚ۬۬ۜۖۘۗ۟ۙۦۚۨۧۜۤۨۘۦۡۚۨۦۤ۬ۦ۫ۛ۟ۘۘ";
                                    continue;
                                case -359735454:
                                    if (strArr != null) {
                                        str16 = "۟۠۫ۧۖۧۢۘۢۘۥۨۘۧۗۨۘۡۧۨۘۥ۟ۤۧۢۢۘ۬ۤ۫ۛ";
                                        break;
                                    } else {
                                        str16 = "ۜ۟ۖۘۤۤۨۢۚ۫ۖۙۢۥۥۡ۬ۖۧۡۖۜ۟ۦۘۥۜۖۡ۫۫ۚۥۥۚۖۙۙۥۘۢ۬۟ۨ۟۠ۖۗۢ";
                                        break;
                                    }
                                case 117691113:
                                    str = "ۤ۠ۛۤۧۨۚۖۚ۟ۛۦۘۚۦۜۘۚۖۛ۟ۘۨۘۨۧۡۘۘ۠ۨۤ۟ۢۙۥۛۚۨۘۢۨۢ۫ۥۜۙۢۧۡۤۗ";
                                    continue;
                                case 1816999866:
                                    str16 = "ۘۜۗۛ۠ۚۛۥۡ۠ۚۖۘۢۦۛ۫ۥۚۧۢۘۘۨ۫۟ۘۗ۠ۗۗۤۘۛۘ۫ۚۢ";
                                    break;
                            }
                        }
                        break;
                }
            }
        } catch (Throwable th) {
            System.out.println(l2.decrypt("24IMpAe/Jgfl4zTohFfdkjhphnT7\n", "gNFpyGHrX3c=\n") + th);
            th.printStackTrace();
            return true;
        }
    }

    private boolean isAllNeededPopupsClosed() {
        PrintStream printStream = null;
        StringBuilder sb = null;
        Set<String> set = null;
        StringBuilder sb2 = null;
        StringBuilder sb3 = null;
        Set<String> set2 = null;
        StringBuilder sb4 = null;
        StringBuilder sb5 = null;
        Set<String> set3 = null;
        StringBuilder sb6 = null;
        StringBuilder sb7 = null;
        Set<String> set4 = null;
        StringBuilder sb8 = null;
        StringBuilder sb9 = null;
        Set<String> set5 = null;
        StringBuilder sb10 = null;
        String str = "ۡ۬ۖۘۥۨۘۤۡۡۗۜۦۜۛۜۗۡۨۘۦۘۥ۠ۨۡۖ۠ۘۘۤۚۤ";
        while (true) {
            switch ((((str.hashCode() ^ 741) ^ 953) ^ 227) ^ (-1910905088)) {
                case -2096978677:
                    String str2 = "ۨۘۛۛۦۖۥ۬ۤۛۛۗۜ۟ۚۧۨۘۖۢ۠ۖۖۛۜ۟ۧۜۛۡۘۘۙۖۡۗ۬ۦۨۦۘۨۦ۟ۛۤۖ۠ۤۛۖۦۜۧۘۦ";
                    while (true) {
                        switch (str2.hashCode() ^ 1970962234) {
                            case -2097558664:
                                str2 = "ۨۚۘۘ۫ۜۥۡ۟ۚۡۤۙۤ۬۬ۚۡۛۢۦۘۚۖ۬ۢۛۚۖۦۡۨ۫۠ۖ۫ۗۨۧ۫ۦۤۗۨۥۖۘ۬ۦ۫۬ۜۢۥۛۜ";
                                break;
                            case -1811430020:
                                String str3 = "۬۫ۡۤۗ۠۬۫ۖ۠ۖۙۙۧۨۘۥۗۖۡۦۥۘ۠ۢ۫۫۬۬۫۠ۘۚۙۦۨ۫ۥۘۙۦۤۗۘۥۢۨۧ۬ۖۢۦۨۗ";
                                while (true) {
                                    switch (str3.hashCode() ^ (-2106796786)) {
                                        case -1304036320:
                                            str3 = !k2.areRequiredPopupsLegitimatelyClosed(set4) ? "ۚۢۘۘۛ۟۟۬ۛ۬ۧۡۢۛۘۦۘۙۨۜۘ۟ۙ۟ۗۗۥۘۢۦۨ۫ۢۜۘ۬ۙۜۢۛۦۘۗۛ۟ۡۚۖۦۥۗۧۢ۬ۡۗۚۗۙ۟" : "۟ۖ۫ۡۧۘ۠ۤۘۢۚۦۗۧۖۧۤۦۗۘۖۘ۠ۦۘۘۧۘۖۜۙۨ";
                                        case -834270844:
                                            str2 = "ۥۗۧۦۨۜۙۨۡۘ۬۫ۜۘۖ۫ۢۜۢۦۘ۟ۦۜۘۙۛ۠ۗۛۛ۟ۨۡۢۗۙۤ۟ۨۘ";
                                            break;
                                        case -98298811:
                                            str2 = "ۤۗۦۘۤۜۖ۫ۛۘۘۨۚۜۘۗۜۗۖۦۜۦۦ۠ۖ۬ۢۜۗۚۙۧ۠ۚۙۙ۬";
                                            break;
                                        case 1160356761:
                                            str3 = "ۤ۠ۘۘ۫۠ۦ۫۟ۤ۟ۙۜۘۜۛۨۘۨۤ۟۠ۡ۟ۚۥ۠ۥۤ۠ۙ۠ۘ۬ۢۙۤۥ۬ۚۥۨۨۖۘ۟ۤۛۡۖۗ";
                                    }
                                }
                                break;
                            case -1119811482:
                                str = "ۢۛۚۨۚۥ۫۫ۛۜۥۧۛۚۚۨۖۘۤۜۨۘۦۦۨۘۜ۟۟ۘۘۜ";
                                continue;
                            case 11550728:
                                str = "ۧۢ۬۟ۘ۫ۙۦۡۘۧ۬ۨۘۡۨۙۚۨ۬ۧ۠ۘۖۗۘۢ۫ۜ۟ۨ۟ۘۨۦۛۙۥ";
                                continue;
                        }
                    }
                    break;
                case -1972271565:
                    str = "ۥ۠ۛۗۙۨۘۨ۟ۛۨۡۘۗۦۘۘۚۦۢۚۘ۬ۧۚ۬ۚۘۨۧۖۧۨۥ۠ۨۖۧۜۖ۫ۜۘ";
                    break;
                case -1904946845:
                    sb8 = new StringBuilder();
                    str = "ۗۛۗۡۥۧۖۗۡۘۦۙۨۘۤ۫ۘۘۤۜ۠ۗۥۛۚ۠ۖۘۥۦۢ۠ۥۢ";
                    break;
                case -1883602656:
                    sb.append(l2.decrypt("JXuM6QfQqQMbSIjEUkVvw5uabHzOGQ3B6QsKBfJITOpD\n", "fivjmXKg6ms=\n"));
                    str = "۟ۢۤ۠ۘۦۦۗۜۘۜ۟۫ۧۧ۬ۡۤۚ۟ۧۙۘۨۥۛۚۧ۫ۨ۟ۖۦۚۙۥۦۘۚۖۖۘۧۖۙۜۦ۬ۗۗۥۘ";
                    break;
                case -1825892400:
                    printStream.println(sb8.toString());
                    str = "۬۬ۥ۟۟ۙۚۙۨۘۦۦۦۜۥۗ۬ۧۖۗۚۛ۬ۢ۬ۢۚۢ۬۟ۛ";
                    break;
                case -1766600584:
                    return false;
                case -1741872080:
                    return false;
                case -1740797279:
                    printStream.println(l2.decrypt("84e5H5woqyTzh6R58XrmbL757EfCfss5KwYExwaecLpOXBuHRKkv/mQtYacS/AG0KTAyxCGUtiTz\nh7kfnCirJPM=\n", "zrqEIqEVlhk=\n"));
                    str = "ۨ۟ۖۘۢۗۦۧۥۦۘۚۘۛۢۘۤۜۤۘۘۨۗۦۜۧۖ۫ۢ۫ۨۥۧۢۤۘۤۖۦۘ";
                    break;
                case -1724438437:
                    sb7.append(k2.closedTextPopupIds);
                    str = "ۡۨۢ۫ۚ۟۠ۥۛۧ۬ۘۘۦۢۤۥۘۢۥۨۘۢ۟ۘۛۦۗۘۧۧ۬ۨۜۘ۫ۤۢۢۜ۠ۥۚۡۘ۬ۦۨۡۤۢ";
                    break;
                case -1703409196:
                    printStream.println(sb2.toString());
                    str = "ۥۘۘۘۡۤۦ۫۫ۙۜۡۨۥۚۚۗۧ۫ۡۖۙۥۘ۫ۛۦۦ۬ۢۦ۟ۛۧۤۖ۠ۢۡۘۡ۬ۡۘ۬ۦۗۗۘ۫ۜ۬ۨۘ۠ۡۜ";
                    break;
                case -1691521293:
                    printStream.println(l2.decrypt("1q9Gm3SSEEPonEK2IQDOp60arEPkU9zOMUbOQZYG6KZrY6ANnUi2rj4WvkbmeNdiyQ==\n", "jf8p6wHiUys=\n"));
                    str = "ۘۥۜۘۧۢۙۗۘۛ۠ۦۛۗۙۖۘۡۛۛۛۡ۫۬ۛۛۖۡ۬۬۬ۡۡۘۡۘۜ۬ۨ۫ۗۡۗۤۘۖۦۧۘۧۨۚۧۖۦۤۜۤ";
                    break;
                case -1605519449:
                    sb.append(set);
                    str = "ۨ۫ۜۘۨۡۘۖۤۢۗۜۛۡۗۡ۟ۥۥۘۖۚۨۘۘ۠ۘۘۦۡۧۚ۟ۜۘۙۢۚ۬۬ۘۘۡۤۨۘۢ۬ۢۦۚۘۡۙ۠";
                    break;
                case -1593182637:
                    return false;
                case -1554279810:
                    return false;
                case -1543699428:
                    String str4 = "ۨۘۘۥۜۧۘۦۛۚۗۗۘۘ۠۟ۨ۬ۗۖۙ۫۠ۙۦۦۘۙۨۘۘ۠ۖۚ۟ۨۧۧۚۧ۟ۘۖ۠ۥۥۘ";
                    while (true) {
                        switch (str4.hashCode() ^ (-312814508)) {
                            case -1777751245:
                                str = "ۜ۫ۘۘۛۥۡ۫ۜۨۜۘۙۨۦۢ۬ۡۖۘۙۘۗۚۛۦۘ۟ۡۧۨۜ۬";
                                continue;
                            case -1713625548:
                                String str5 = "ۡۤۦۡۗۡۜۧۗۨۙۖۘ۬ۙۙۦ۠ۨۖۥۥۘ۫ۤۘۘۤۧ۟ۢ۬ۗۚ۬ۦۗۤ۫";
                                while (true) {
                                    switch (str5.hashCode() ^ (-1247992448)) {
                                        case -1855102321:
                                            str5 = !k2.closedTextPopupIds.containsAll(set4) ? "ۢۚۖۘۦۤۧۥۢۤۘۦۘۙۜۦۢۥۦۖۖۘۡ۬ۤ۠ۖ۬۠۫ۜۘۨۥۦۧۨۘ۬ۥۜۘۧ۬ۗ" : "ۜ۫۠ۧ۟ۡۘۘۤۦۙۤۗۗۗ۫ۛۡۨۘۛۙۨۘۤۧۤۨۥ۟ۚ۟ۖۘ۫ۖۛۜ۬ۡۚۙۛ۬ۨۦۘ۟ۗۡۘۜۜۘۘ";
                                        case -529868358:
                                            str4 = "۫ۧ۫ۘۚۦۚۢ۟ۙۥۛۧ۬ۧ۫ۗۥۘ۫ۙۢۨ۟ۛۥ۬ۛۛۜۢۦۜۜ۬۫ۨۘ۬۫ۜۘۗۡۦ۟ۜۘۨۗ۬ۚ۬ۡۤ";
                                            break;
                                        case -387444123:
                                            str5 = "۟ۖ۟ۧ۬ۦۘۚۦۢۤۡۜۘۚ۬ۛۡۦۧۙ۬ۨۘۖۦۘۡۥۘۗ۟ۗۗۧۜۘۙۨۤۧۢۘۘ۠۟ۖۘ۟۠۫ۘۜۙۗۦۥۚۥۢ";
                                        case 1450376243:
                                            str4 = "۠ۗۦۗۤۦۘ۬ۘۜۘۛۗۤۤۘ۫ۧۡ۠ۜۧۦ۬ۛۡۘۡۗۖۘۚۧ";
                                            break;
                                    }
                                }
                                break;
                            case -563761248:
                                str4 = "۟ۚۦۥۢۜۘۥ۫ۤۡ۟ۡۛۘۖ۫ۤ۠۬ۛ۠ۢۢۦۜ۟ۛۛۖۦۢۦۥۘۘۡۧۦۧۨ۟ۤۚۢۚۨۘ۟ۖۧۜ۠ۢۛۖ";
                                break;
                            case 722571057:
                                str = "۬ۖۤۗۛ۟ۢۚۦۘۧ۬۫ۙۘۖۘۨۙۘۘۘۡۥۖۨ۟ۨۢۗ۫ۥ۬ۘۡۜۥۧۖ";
                                continue;
                        }
                    }
                    break;
                case -1521347950:
                    sb7 = new StringBuilder();
                    str = "۬ۤۦۘۘۥۘۘ۬ۙۤ۟ۥۤۖۤۡۢۧۗۢۖۡۡۡ۠ۘۦۧۡۜۤ";
                    break;
                case -1503920161:
                    sb10.append(k2.describePopupLifecycle(set5));
                    str = "ۜۢۜۤۢۡ۠ۘۨۘ۫ۡۥۘۥۥۨۘۖۙۗۢۚۜۘۡۨۚۧۧۦۘۢۖۧۘ";
                    break;
                case -1489531757:
                    printStream.println(sb9.toString());
                    str = "ۜۚۗ۟ۙۡۘۖۥۦ۬ۜۙۧۧۥۘ۫ۨ۫۟۟۫ۗ۫ۖ۟ۖۧۜۨۦۘۥۢۘۘۨ۬ۡۘۢ۫ۤۦ۬ۤۘۗۢۗۛ۠";
                    break;
                case -1372647867:
                    set = t3.needFullscreenPopupIds;
                    str = "۟۠ۗۚۤۧ۬ۙ۟ۨۗۙ۫۟ۘۥ۫ۜۘۖ۠ۗۗۨۖۘۡ۫ۚۖۚۦۘ۠ۧۦۘۧۢۡۘ";
                    break;
                case -1343588756:
                    String str6 = "ۜۚ۠۬ۜۘۚۚۦۘۢۚۥۡۤ۬ۙۜۖۘ۠ۥ۬ۖۥۦۘ۟۬ۦۤۜۧۘ";
                    while (true) {
                        switch (str6.hashCode() ^ 1000493685) {
                            case -1010992474:
                                str = "۟ۢۥۘۛۧۨۘۜۥ۬ۖۚ۟ۦۡۡۘۙۨۘۦ۠۠ۤ۫ۚ۟ۘۡۘۨ۬ۜۛۡۘۧۤۥ۬ۙ۬ۜۖ۬ۘ۫ۖۘۜۨۥۘۜ۠۬۠ۜۥ";
                                continue;
                            case -685050330:
                                String str7 = "۫ۜۡۛۨۡۘۘۧ۬ۨ۫ۚ۬ۨۤۚۥۡۘۛۡۦۨۨۘۛۙۗۥۖ۟۠ۦۖۘۗ۫ۘۘۡ۠ۨۦۦۨۡۘۥۘۚۚۘۘۧۘۖ۠ۙۘ";
                                while (true) {
                                    switch (str7.hashCode() ^ (-789481754)) {
                                        case -2000142962:
                                            str7 = !k2.closedImagePopupIds.containsAll(set2) ? "ۢۖۜۘۡ۬ۢۘۡۤۦۨۛۜۘ۬ۨۘۚۡ۟ۜۥۧۘۤۖ۬ۗۤۙ۠۟ۙۚ۬ۤۗ۫ۢ۬ۗ" : "۬ۘۛۦ۟ۙۥۧ۟ۜۡۜ۟ۡ۠ۗۤۜۖۜۖۢ۫ۢۗۖۘۛ۠ۖ۫ۤۖۡ۬ۘۘۧۘۤۧۡۗ۟ۧ۬ۜ۟ۧ۬ۗۙۗۤ";
                                        case -77603264:
                                            str6 = "ۙۦۨ۫ۚۦۘۨ۫۠ۖ۫ۨۘۚۛۚۙۛۨۡۥۦۘ۬ۛ۠۟ۨۙ۠ۨۛۨ۫ۚۢۗ۫";
                                            break;
                                        case 1073893982:
                                            str7 = "ۜۙۨۢ۬۬ۨۖۗۢۨۧۘۚۨۜۦۡۗۜۧۡۘۘۡۜۦ۬ۙۘۧۘۙۢۖۗۧۖۘۖۨۦۘۛ۠ۖۧۜۤۗۗۦۢ۠ۨ۬۟ۖ";
                                        case 2019867304:
                                            str6 = "ۜۜۘۘ۫ۤۢۗۖ۟ۖۙۖۘۢۘ۫ۨۖۜۘۦۖۡۘۛۢۦۘۖۘ۬ۖۨ۠ۧۦ۫ۜۖۜۘۖ۟۫ۛۛ۟۟ۘۛۙ۠ۨۘ";
                                            break;
                                    }
                                }
                                break;
                            case -234389569:
                                str = "ۨۘۦۢۥۧۤ۬ۥۘ۫ۦۨۢۘ۠ۦ۫ۢۨۘ۠ۨ۫ۥۤ۫ۦۛۜۘ۟ۦۚۥۨۖۖ۫ۧۦۦۗۦ۫ۚۢ۬ۤ";
                                continue;
                            case 1866144564:
                                str6 = "ۘۥۚۜۤۥۘۙۤۘۚۖۘۧۜۙۙۦۛۚ۫ۧۜۛ۠ۤۜۘۜۛۦ";
                                break;
                        }
                    }
                    break;
                case -1190365278:
                    return false;
                case -1157909960:
                    printStream.println(sb5.toString());
                    str = "ۜ۟۟۬ۡۦۘ۬ۦۦۘۜ۟ۢۙۗۦۘ۬ۨ۬۠۫ۨۤۤۦۥۘۨۥ۟ۘۘۙۛۘۙ۫ۘۧۛۙۖۨۨ";
                    break;
                case -1105966952:
                    printStream.println(sb.toString());
                    str = "ۥۤۜ۟۫ۚ۠ۘۗ۠ۢۛۥۘۘۘۥۤۚۥۛ۫۠۟ۘۘۜۗۜۘۤ۠ۨۘ";
                    break;
                case -1091328313:
                    sb7.append(l2.decrypt("ujAd238xJ1UNeJc=\n", "mtWqaZq0lLw=\n"));
                    str = "ۖۢۡۘ۬ۨ۟۟۫۫ۤۜۙۢ۬ۚۢۘ۠ۛۥۡۖۛۡۖۢۜۘۛ۫ۗۜۛۨۦ۟";
                    break;
                case -1048350451:
                    sb4.append(k2.describePopupLifecycle(set2));
                    str = "ۜ۫ۘۢۢۧۙۤۧ۟ۨۤۜۘۥۖ۫ۗۖۥۡۦ۟ۖۗۡۜۙۗۘۘۛ۫۟ۗۥۘۦۤۥۘۨۜۚۦۛۘۥۗۛ";
                    break;
                case -1039347676:
                    sb2.append(k2.describePopupLifecycle(set));
                    str = "ۚۖۜ۬۠ۛ۟ۨۧۢۦ۬ۚۛۧۗۘ۠ۖۘۡۦ۬ۡۛۜۥۘۧۜۚ";
                    break;
                case -999398237:
                    sb3.append(l2.decrypt("o12wDjrfPDudbrQjb0rk7R+EWJvzFpj5by024s9H2dLF\n", "+A3ffk+vf1M=\n"));
                    str = "۫۠ۜۖۧۢۖ۫ۡ۫ۨ۠ۨۢ۬ۚۧۛ۠ۧ۟ۙۥۤۡۚ۟۟ۛۨۘۙۦۦۚۥۜۘۜۙۡۘۥۗۦۘۨۧۜ۫ۦ۟";
                    break;
                case -987657904:
                    return false;
                case -956348485:
                    sb8.append(k2.describePopupLifecycle(set4));
                    str = "۫۟ۡۘۧ۫ۢ۫ۧۜۗۘ۬۬ۨ۠ۘۢۗۛۦۗۥ۠ۨۘۡۙۚۧۡۖۤ۟ۗۧۛ۟ۢ۠ۖۘۧۦۢ۫ۙۜۨ۫ۘۚۨۛ۫ۨۨ";
                    break;
                case -922173059:
                    sb10 = new StringBuilder();
                    str = "ۗۗۘۢ۟ۨۘ۟ۚۡۚۡۖۖۘۘ۫۟ۛۗۢ۫ۛۥۖ۠ۡ۟ۢۢ";
                    break;
                case -856103353:
                    printStream.println(sb6.toString());
                    str = "ۨۖۜۙۜۚ۠ۛۧۤۦۙۚۗ۠ۜۤۨ۠ۦۖۘۦۡۨ۫ۚ۟ۘۙۦۘۜۥۗۡۦۛۖۛۤۨۘۤ۠۫۠ۨۘۗ";
                    break;
                case -793540801:
                    sb3.append(l2.decrypt("z25l5Ymwwfx4Ju8=\n", "74vSV2w1chU=\n"));
                    str = "ۘ۬ۢۛ۟ۤۜۦ۟ۢۡۜۨۢ۟۠ۧۖۘۛۘۧۤۡۨۘۖۤۧ۬ۦۖۘۤۢۘۘۢۢۗ";
                    break;
                case -783530481:
                    printStream.println(l2.decrypt("oFMHe72PDnqeYANW6B3QnttLPEaEGvGrHKn/73Nyq45y5fShLXr++2yuj5FMtgk=\n", "+wNoC8j/TRI=\n"));
                    str = "ۖۡۧۥۧۧۡۨ۬۠۫ۥۘۢۨۦۘۛۧ۫ۤ۟ۗۖۧۛۥۜۨۘ۬ۖۛۖۘۥۘۜ۠ۗ";
                    break;
                case -744022258:
                    printStream.println(l2.decrypt("5yLY9p0RD0DZEdzbyIPRpJyaCRUN5OnOHfRSOlGG5r9YyTpgdOiqtBaXMjUB9uHPJvb+wg==\n", "vHK3huhhTCg=\n"));
                    str = "۠ۗۚۨۜۚۚ۟ۡ۫۬ۦۘۚ۟۬ۛ۬ۧۚۤۜۗ۫ۦۘ۟ۗۛۥۘۥۘۨۖۜۛۙۛۢ۟ۧ۬ۘۧۘۙۘۧۘۙۨ۟ۛۤۡۖۤۙ";
                    break;
                case -674589169:
                    sb3.append(set2);
                    str = "۠ۧۘۘۦ۟۬ۛۙۛ۟۬۠ۦ۠ۦۥۛ۬۬ۥۦۘۗ۟ۚۘۛۡ۬۬۫ۘ۟ۜۛۢ۬ۛ۠ۢۗۧۗ۬ۛ۫ۛۡ";
                    break;
                case -665026096:
                    return false;
                case -664518435:
                    return false;
                case -659756904:
                    sb6 = new StringBuilder();
                    str = "ۘۦ۬۫ۦ۠ۦۛۦۚ۟ۥۛۨۦۘۨ۬ۚۚۦۖۘۨۗۨۥ۬ۜۘۤۤۤ۬ۥۗ۟ۥۛۨۜۘۜۦ۠ۛۤۨۤ۬ۤ";
                    break;
                case -648143841:
                    sb7.append(l2.decrypt("/94akIR1DJ7B7R690e3xZUEL0AZQg6pKHWnfd9Hs03ZMKPTd\n", "pI514PEFT/Y=\n"));
                    str = "ۤ۟۬ۖۛۡۘۛۖ۟ۨۜۘۘۛۙۘۘ۫ۖۧۘۦۘۜ۫ۚۖۢۥ۫ۚۘۛ";
                    break;
                case -599139273:
                    sb9.append(set5);
                    str = "ۡۜ۫ۡۧ۬ۧۡۢۘ۫ۗ۠ۦۥۥ۠ۜۛۨۨۢۡۨۘۜۨۜۘۤۦۘۘۗۤۘۡۙۨۘ۫ۙۜۘۘۥۛۘ۬ۦۘۧ۬ۙۙۤۤۜ۫";
                    break;
                case -565203353:
                    sb3 = new StringBuilder();
                    str = "ۚۘۥۘۛ۠ۨۛۢۡۖۘۛۚۡۘ۬ۙۨۚۙۡۤۤۖۘۨۗ۟۟۫ۜۘۤۚۖۛۨۚ";
                    break;
                case -519573487:
                    sb9.append(l2.decrypt("GVxvGAb+Ntwnb2s1U2jDPKSNr43PN5Ie1Szp9PNm0zV/\n", "QgwAaHOOdbQ=\n"));
                    str = "ۧ۬ۡۧۥ۠ۖۢۛۨۛۙۚۚۨۤۡۥۨۚۚۘۧۚ۟۫۠ۘۘ۠ۢ۟ۘۘۖۘۙ۠ۥۙۛ۬۠ۘۜۜۡ";
                    break;
                case -441866174:
                    sb6.append(l2.decrypt("bMOsk/bMkjlS8Ki+o15M3Rfbl67PWW3o0DlUBBcjNMCKdlJLZSBOt5cyKkkPWk373hNZCzw763E=\n", "N5PD44O80VE=\n"));
                    str = "ۘۜۧۤۦۥۡۡۜۘۘۦۖۨ۫ۜۨۧۦۘۙۗۤۨ۫ۚۗۜۨۘۜ۬ۦۘۨۙۙ۠ۢۦۘ۠۬ۦۘۖۛ۟";
                    break;
                case -401645861:
                    printStream = System.out;
                    str = "ۡ۬ۤۛۚۤۖ۟ۛۦۧ۠ۙۜۧۙۤۗ۫ۥۘۚۘۘۘ۫۠ۚۨۡ۠ۧۗۥۘۜۡۧۘ۫۟ۧ۟ۜۚۦ۫ۦ۟";
                    break;
                case -399465167:
                    String str8 = "ۨ۬ۧۡۗۗ۬ۛۦ۬ۘۧۛ۠۟ۦۦۘۢ۠ۨۡۢۤۧۙۚۙۤ۠ۗۚۚۜۧۛۘۘ۠۫ۚ";
                    while (true) {
                        switch (str8.hashCode() ^ (-603204042)) {
                            case -626260917:
                                str = "ۡۥۖ۟ۡۖۦۚ۬ۛۜۧۚۛۨۜ۬۟ۡ۬ۖۗۚۛۘۢۦۙۥ۬ۘۛۘۘ۟ۘ۬ۢ۫ۘۦۘۡۘۥۘۖۘۛۧۨۘ";
                                continue;
                            case 518764640:
                                str = "ۦۧۗۛۧۜ۟ۥۘۙۗۥۘۥۛۥۘۧۧۖ۟ۜۧۜۢۛۛۢۘۙ۠ۡۘ";
                                continue;
                            case 760813075:
                                str8 = "ۛۚ۬ۖۗۨۘۧۘۜۥۥۜۗ۫ۨۦۜۡ۟ۖۘ۫۠ۘۘۖۡۗۨۧۖۘ";
                                break;
                            case 940754086:
                                String str9 = "ۨۜۡ۫۬ۜۘۧ۫۟ۧ۫ۡۘ۫ۜۖۘۜۡۡ۠ۢۗۛ۬۫ۨۜ۫ۢۤۨۘۖۨ۫ۖۥ۫۬ۘ۟ۢۚۛۘۦۢۛۗۧ";
                                while (true) {
                                    switch (str9.hashCode() ^ (-1232275949)) {
                                        case -2039160903:
                                            str8 = "ۚۘۥۙ۫ۚۜۥۖۜ۬ۘ۟ۡۥۚۜۘۛۛۨۚۚۘۘۖۦۨۗۖۦۘۤ۫ۖۘۜۧۘۡۡۥۘۨۖۧۘۡۜ۬۠ۜۜ۠ۧۥ۫ۘ";
                                            break;
                                        case -1522896478:
                                            str9 = !k2.areRequiredPopupsLegitimatelyClosed(set5) ? "ۤۨۨۦۢۧۦ۠۟ۦ۟ۤۖ۫ۖۙۡۨۘۡۨۧۤۦۘۗۚۘۘۨ۬ۡۘ۠۬۬۠ۡ" : "ۙۘۢۦۤۦۗۙۜۦ۟۬ۜۥ۫۟ۘۘۘۡۙۦ۠۠ۤۨۛۜۘ۟ۛۜۘۧۛ۫ۥۛۨ۬ۗۦۜ۠ۘۘ";
                                        case 1466082290:
                                            str9 = "ۥۦۧۘۧۤ۟ۗ۬ۘۘۖۜۡ۫ۥۗۙۨۘ۠ۚ۬ۖۘۥۘۖۘ۫ۜۥۘ۟۟ۨۘ۠۠ۙۘۙۘۘۗۜۥۢۙۚۡۗۘۘ";
                                        case 1872156971:
                                            str8 = "ۚۤۖۘۧۗۤۨ۟۬ۧۨۘۘ۫ۡۜۤۨۜۘۚۖ۬ۥۛۢۖۘ۟ۨۤ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case -369665707:
                    set2 = t3.needImagePopupIds;
                    str = "۫ۨۨۘ۟ۨۢۥۨۖۘۘ۬ۨۘۖۚ۠ۥۙۥۛۥۖۘ۫ۢۤ۬ۜ۠ۤۢ۬۬ۢۡۘۜۧۜۘۖۚۢۜۜۡۥۤۘۛۚۙۤۢۘۘۨۖۡۘ";
                    break;
                case -319877108:
                    sb10.append(l2.decrypt("4eUWCi74663f1hIne2o1SZpTz/K9CQcgBgye0MxvPFpfJMSfyiBOWSVT2duyIiQjJh+Q+sFgF0KA\nlQ==\n", "urV5eluIqMU=\n"));
                    str = "ۥ۬ۖۗۤۥۦ۠ۦ۬۫ۧۤۙۤۙۦۘۜۧ۬ۧۙۘۘۖۚۘ۫ۜۚۙۥۜۘ۬ۦۡۘ";
                    break;
                case -228680769:
                    printStream.println(l2.decrypt("TyKX/sNLSd5xEZPTltmXOjSUTgZQuqVTqMsfJCHfsTvy7nFoKpHvM6ebbyNRoY7/UA==\n", "FHL4jrY7CrY=\n"));
                    str = "۠۟ۙۢۙۦۢۥۗۤۧۛ۫ۘۛۗۥۘۤۘۛۡۢۨۜ۠ۘۘۨۗۘۘۢ۬ۜۢۖ۟ۡۖۖۘۚ۟";
                    break;
                case -206011715:
                    set3 = t3.needHtmlPopupIds;
                    str = "ۚۦۜۘۙۖۘۤۛۡۡۚ۫ۗۗ۟ۡۢۦۘۚۡۖۢۘ۫۬ۥۘۘۗ۟۫ۙۛۘۘ۟ۨۜۘۗ۠ۖ۟ۚۡۘۢ۫ۙۜۖ۟";
                    break;
                case -181978145:
                    return true;
                case -70545342:
                    sb5.append(k2.closedHtmlPopupIds);
                    str = "۫ۜۥۘۥۢۦۘۚۚۛۦۗۘۧۢۧۛۤۤۨۢۖۘ۠ۤۖۤۦۗۡۤۗ";
                    break;
                case -65076403:
                    sb9 = new StringBuilder();
                    str = "ۦۥۦۗۦۥۛۨۡۦۥۧۢ۫ۜۖ۠ۦۘۙۛۙۧۥۘ۠ۦ۠ۡۤۧۜ۬۬ۖۙۦۡۖ۠ۚۚۗ۫ۥۘۖ۬ۜۖۜۥۡۤ";
                    break;
                case 27447155:
                    printStream.println(l2.decrypt("KbBcAOUftd4Xg1gtsI1rOlIFqM535nFTzlnU2geLTTuUfLqWDMUTM8EJpN139XL/Ng==\n", "cuAzcJBv9rY=\n"));
                    str = "ۢۚۙۦ۫ۘۤۛ۟۟۠ۗۛۨ۠ۜۛۗ۟ۡۧۢۖۡۦۧۚ۠ۧ۬۠ۥۧۙ۟";
                    break;
                case 107805631:
                    sb5.append(set3);
                    str = "۬ۚۦۧۨۨۦ۫ۗ۠۟ۙۖ۟ۘۦۧۡۥ۬ۨۦۘۙۢۜۦۘۦۢۡۘ۠ۛ۠ۖۚۦۘۜۙۥۘۗۡۦ";
                    break;
                case 135868965:
                    String str10 = "ۚۚۘۘۨۥۡۥۡۘ۟ۢۥۘۖۥۘۤۦۡۘ۠ۨۡۚۧۨۘۦۘۖۨۨۦ";
                    while (true) {
                        switch (str10.hashCode() ^ (-1339713758)) {
                            case 282218892:
                                str = "ۗ۫ۤۨۦۧۚۖۜۦۢۗۧۜۢۛۦۙۨۖۘۥۦۜۗ۠ۥۘۗۦۦۧۨۜۘۢۜۘ";
                                continue;
                            case 1030903477:
                                str = "ۢ۬ۖۨۨ۟ۛۙۚۥ۠ۡۘۤ۬ۜۘۖۥۘۛۦ۠۠۬ۜۙۦۢۖۙۨۛۡۜۘۧۘۘ۬ۡۦۘۦۗ۬ۜۛۖۘۡ۠ۜۘ";
                                continue;
                            case 1264052750:
                                str10 = "ۗۡۦ۟ۧ۟ۙۙۚ۟ۨۙۖۦۘۡۛۛۨۗۖۘۢۥۗ۟ۤ۫ۛۜۜۤۤۗۛۨۚۜۨۚ۬ۢۡۦ۟ۚۡۗۤ";
                                break;
                            case 1282514534:
                                String str11 = "ۙ۠۟ۧۨۨۚۧۡۘۗۛۙۙ۟ۛۛۧ۠۫۫ۖۘۛ۫ۢۖۗۨ۟۟۟۬ۘۧۨۜ۬ۤ۬ۘۖۦۜۘۖ۬ۥۘۡۦۦۜۦۖۚۧۗ";
                                while (true) {
                                    switch (str11.hashCode() ^ 840340321) {
                                        case -8674294:
                                            str10 = "ۘۧ۬ۘ۫ۡۗۛۥۜۖۦۘۗۚۨۘۢۜۛۗۙۜۘۦۨۦۦۢۚۘۧۘۛۖۦۘۢۦۜۘ";
                                            break;
                                        case 77707935:
                                            str11 = !k2.closedPopupIds.containsAll(set) ? "ۙ۟۬۟۬ۥۘۥۗۘۘۧ۫ۚۖۡۖۘۗ۟ۖۨ۟ۦۗۡۖۤ۬ۦۡۨ۟ۧۡۛۤ۠ۧ" : "۠ۛۖۘۜۗۤۚ۠ۨۙۜۤۧۙۖۡۥۤ۬ۢۛۙ۫۠ۧۙۚۗۖۗۘۥۥۢۘۙۜ۬ۗۙۖ";
                                        case 870842712:
                                            str10 = "۟ۦۜ۠ۘۡ۟ۚ۬ۨۦۢۦۤۖۘۙۡۚ۬ۛۦۘ۠ۡۦۘۥۤۥۗ۫ۥۘۡۖۥۘۛۥۡۘ";
                                            break;
                                        case 1997364741:
                                            str11 = "ۛۡۜۘ۟۟ۙ۟ۢۘۘ۠ۥۜۘۤ۟ۢۙ۫ۧۤۘ۠۟ۙۙ۟ۛۥۙۙۖۙۘ۬ۛ۬ۥ۠۬ۦۘۥۛۘ۠ۢ۠ۗۦ۬ۨۗۗۛ۟ۦۘ";
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case 193782792:
                    printStream.println(sb3.toString());
                    str = "۟ۘۨۘۙۘۚۡۧۖۘۜ۬ۚۥۤۨۘۜۖۤ۟ۤۥۘۜۜۨۘۧۡۨۨۚ۠";
                    break;
                case 310726497:
                    sb5.append(l2.decrypt("4paavFJ1CINV3hA=\n", "wnMtDrfwu2o=\n"));
                    str = "۬ۖۖۡ۟ۘۘۗۖۨۘۨۗۛ۫ۘۘ۬ۥ۬ۡۡۥ۟ۘۘۥۢۙ۠۬ۦۦۥۜۧ۟ۖۥۚۥۘۗۜۘۨۘ۠ۙۢ۟ۤۗۦۥ۬";
                    break;
                case 441097966:
                    sb.append(k2.closedPopupIds);
                    str = "ۘۧۘۘۤۥۘ۟ۜۢۚۦۡۘۨۢۙۤۗۦۘۤۦۚۡۘۤۡ۬ۙۚ۬";
                    break;
                case 453818800:
                    String str12 = "ۧۥۛ۫ۚۛۖۙۛۧۧۜ۫ۦۜۘۘۘۤ۠ۥۜۛۦۜۢۛۜۘۨۘۖۙۖۗۙ۬ۗۖۢ۟ۖۥۘ";
                    while (true) {
                        switch (str12.hashCode() ^ 1977969701) {
                            case -1817195290:
                                str = "ۡۖ۫۠۠ۤۙۦۜۘ۟ۗۢۖۨۨۘۙۡۨۦۖۥۦۥ۟ۦۗ۟ۗۘۖۘۘ۟ۡۦ";
                                continue;
                            case -1167591720:
                                String str13 = "ۘ۬ۙۥۛۦۘۘ۫ۗۦۥۛۥۖۛۢۚۖۘۘۚۛۥۘۧۤۨۘۡ۠ۜۘۢۥۦۤۜۦۘ";
                                while (true) {
                                    switch (str13.hashCode() ^ (-1453262463)) {
                                        case -1689150540:
                                            str12 = "ۢۥۧۜۙۥۘۖۙۦۘ۟ۘۡۧۜۖۛۧ۬ۗۦۨۥۤۢ۠ۦۘۘۛ۬ۧ۟ۢۧۚۡۙ";
                                            break;
                                        case 1916316281:
                                            str13 = !k2.areRequiredPopupsLegitimatelyClosed(set3) ? "۠ۨۨۤ۫۫ۦۨۦۛ۬ۨۘ۠۟ۛۨۜۡ۟۟ۢۙۖۗۤۙۘ۟ۨۜۘۗۤۖۨۥۚ" : "ۚ۟ۨۘۦۘۦۖ۬ۥ۫ۥۢۢۗۢۘۨۦۘۛ۬ۥۘۢ۬ۨۨۥۙ۠ۨۥ۠ۚۚۘۤۡۨۥۘۦۡۙۥۥۘۘۥۦۥۘ";
                                        case 2024144659:
                                            str13 = "ۛۧۜۘۧۨ۠ۚۜۚۛۛۤۙۨۦۘۢۧۜ۠۟ۜۘۚۢۗۥ۫۠ۚۖ";
                                        case 2094054193:
                                            str12 = "ۜۤۡۘۤۤ۟ۡ۠ۥۘۘۚۨۘۘۜۤۨۛۙ۠ۖۡۖۜۚۜۦۢ۟ۨۢۦۘۙۙۥ۟ۛۨۘۤۜۡۘۨۛۜ۠ۨۡ";
                                            break;
                                    }
                                }
                                break;
                            case -158337517:
                                str12 = "ۧ۫ۤۦۤۨۤۗۦۤۥ۠ۡ۬ۘۘۘۨۜۘۘۘۚۛۨۢۨۨۡۘ۫ۡۦۘ۬۫۫ۦۙۥ۬ۛۥۧۡ";
                                break;
                            case -44557745:
                                str = "ۗۢۚۧۜۜۘۨۥ۬ۘۙۥۘ۟۫ۘۘ۠۟۬۠۟ۜۧۧ۠ۙۧۗۘ۟ۤۖۗۚ۬ۙۡۛۡۘۢۘ۫ۤۘۥ۬۬۫";
                                continue;
                        }
                    }
                    break;
                case 475464814:
                    printStream.println(sb4.toString());
                    str = "ۥ۠ۥۘۘۡۦۘۤۗۦۘ۫ۥۦۖۘۡۛ۬ۨۘۘۘۖۘ۬ۢۜ۟۫ۢۦۗ۟۟ۡۜۘۡۛ۠۠ۢۚۥ۫ۥۢۙۧۨۜۥۘ";
                    break;
                case 495529988:
                    sb8.append(l2.decrypt("L/Edzb87J6wRwhng6qn5SFRJzC4vzsEi1SeXAXOszlOTNe1YW/aBVdxH7iIs68Ut3i2UIWCi5F6c\nHvWH6g==\n", "dKFyvcpLZMQ=\n"));
                    str = "ۢۙ۫۬ۥۡۦۦۦۜۖۧۘۦۜۖۘۤۚۛۛۖۛ۟ۙۛۤۧۚ۬ۜۖۛ۬ۜۖۚۖۘ۠ۤۘۘۛۨۖۘ";
                    break;
                case 598036708:
                    return false;
                case 621255951:
                    printStream.println(sb10.toString());
                    str = "ۡ۟ۘۘۖ۫ۜۘۡۖ۟ۤۧۖۘ۫ۘۖۘۨۧۨۤ۬ۥ۠ۤۥۘۗۢۥۘۖۡۘ۠ۨ۬ۜۨۖ";
                    break;
                case 653183566:
                    printStream.println(sb7.toString());
                    str = "ۤۦۦ۫۫ۧۚۘ۫۟ۡۥۢۤۡۘ۟ۜۥۘۛۢ۟ۚ۬ۤۜۡۖۘۧۛۖۖۜ۫۟ۦۦ۫ۨۘۘ۫ۢۘۘۥ۠ۥۤۦ۫ۢۚۧۙۘۥ";
                    break;
                case 693772255:
                    printStream.println(l2.decrypt("E1ngp8+7fowtauSKmimhYWjvBldcV7QN1IlncTssp2CttTYwEFx0oK2UCDINedhh++AYeg==\n", "SAmP17rLPeQ=\n"));
                    str = "۫۬ۢ۫ۧۖۚۢۨۘۙۛ۬ۖۢۖۢۡۨۜۚ۬ۥۧۥۢۘۚ۬۬۠۟ۗۚ۬ۢ۬ۜۘۘۦۢۛۡۘۘۛ۬ۤۜ۫ۙ۫ۗۖۘ";
                    break;
                case 721145753:
                    sb9.append(k2.closedMessagePopupIds);
                    str = "ۡۦۡۥ۠ۖۗۜ۠ۗۡۘ۟ۗۨۖۧۦۘۜۡۥۘۚ۫۠ۚۨۡۘۤۗۚ";
                    break;
                case 752760392:
                    String str14 = "ۛ۬ۜۘۚۦ۟ۥۧۘۘ۬ۛۖۘ۫۫ۨۦۥ۟ۙۛۜۘ۟ۦۛۖۘۗ۫ۗۗۙ۠ۛۜۘۧ۠۫ۡۦۘۛۢۥۘۜۧ";
                    while (true) {
                        switch (str14.hashCode() ^ (-316205851)) {
                            case -1809207977:
                                str = "ۥۘۨۦۡ۫۬ۧۤۢۡۢۙ۠ۧۛۦۜۘۖۗۨۖۚۥۨۙۚۥ۠ۧ";
                                continue;
                            case -1030512231:
                                str = "ۤ۟ۤۘۢۥۘۜۘۧۙۜ۫ۜ۟ۥۤ۬ۜۘۖۛۨۡۨۜ۬۬ۘ۟ۚۛۜۚۦۚۦۙۢۨۦۜ۠ۛۜۢۙۧۛ۠۠ۢۧۘۜۧ";
                                continue;
                            case 1569904320:
                                str14 = "ۤ۫ۡ۟ۛۨۘۖۥۨۘ۟ۦۦۡۜۘۨ۫ۘۥۦۦۘۘۨۤ۫ۛۘۤ۫۬۬ۖۧۘۧۘۥۘۗ۠ۤۤۤ۬";
                                break;
                            case 2109588830:
                                String str15 = "ۤۖۗۗ۬ۖۘۗ۟ۤۧۥۗ۬۫ۦۡۦۘۘۡۗۖۘۗ۟ۦۘ۬۟ۘۘۖۦۨۤۦۖۘ۟ۘۚۛ۟ۧ۬ۨ";
                                while (true) {
                                    switch (str15.hashCode() ^ (-260931429)) {
                                        case -1371089748:
                                            str14 = "ۙۖۨ۫ۤۚۘ۠ۜۤۚۗ۬۬ۤۥۖۦۥۢۖۘۚۚۥۘۢۛۖۘۦۥ۠۫۬ۨۧۖۛ";
                                            break;
                                        case -762242087:
                                            str15 = !k2.closedHtmlPopupIds.containsAll(set3) ? "ۜۦ۬ۥ۬ۨۚۙ۠ۦ۠ۖۡۤۡۢ۬۬ۜۤ۫ۨۚ۠ۚ۟ۦۡۖ" : "ۜۨۜۘۘۖۧ۟۫۠۠ۖۦۘۧۘۤۚۦۤۙۢۤۤۘۚۗۜۢ۠ۜۚۖ۟۠۫ۖ۟ۚۙۖۨ۫ۘ۫ۧۖۧۘۘۛۨۡۦۢۜ";
                                        case -184473477:
                                            str14 = "ۤ۟ۖۘۤۘۜ۫ۚۦ۠ۧۜۘۛۛۥۘۜۘۘۘ۬ۛ۠ۡۤۨ۫ۘۥۘۙۗۖۘ";
                                            break;
                                        case 1423920150:
                                            str15 = "ۨۥۧۘۛۢ۟ۤۘۘۘۙۖۜ۠ۗ۬ۢۛۥۥۡۨۘۥۚۡ۠۬ۦۘۦۛۧۡۡۤۤۜ۟ۢۥۙۤۡ۟۬ۚۜۘۖ۬ۦۤۢۜۘۜۗۡۘ";
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case 763456243:
                    String str16 = "ۡۥۙۚۚۥۘۧ۠ۗۨۥۦۘۡۢۘۢۡۙۜۢۗ۠ۛۨ۬ۛۨۘۡۥ۬ۙ۫ۖۛۤۦۨۘۗۚ۫";
                    while (true) {
                        switch (str16.hashCode() ^ (-357437064)) {
                            case -964336429:
                                str = "ۢۙۘۘۗۚۡۘ۟۠ۥۘ۠ۖۦۘ۫ۖۘۖ۠ۧۥۚ۠۫۬۠ۜۗۚۧۥ۬ۖ۟ۨۘۗۛۖۘۛۡۤۛۗۥۖۘۜ۬ۜۘۘ";
                                continue;
                            case -616040031:
                                str = "ۖۦۙۖۦۥۥ۟ۛ۠ۚۤۖۜۘۜ۬ۨۛۡۢۡۤ۟ۘۦۖۧۤۙۤۦ۟ۨۗۧۨۤۖۤۨۢ";
                                continue;
                            case 889910023:
                                str16 = "ۜ۫۫ۘۙۨۗۤۙۡۦۥ۠ۨۧۤۚۡۖۙۥۘۚۤۧۙۜۘۚۛۤۛۗۡۘ۬۠ۜ";
                                break;
                            case 1685549922:
                                String str17 = "۬۬۟ۗۡۚ۬ۘۢۨۤۥۘ۬ۦۚۘ۠۟۬ۨۚۚۤۜۨۜۥۜۤ";
                                while (true) {
                                    switch (str17.hashCode() ^ 903659790) {
                                        case -1275691532:
                                            str17 = !k2.closedMessagePopupIds.containsAll(set5) ? "ۤۢۨۤۗۜۛۦۖۘۡۥۢۙۤۜۘ۬ۖۤۧۜۦۘۤۙۡ۫ۚۙۗ۬ۙ۫ۡ۬۠۠ۜۤۤۨۘۧۘۜۧۘ۟" : "ۗۢۜۘۤ۬ۦۘ۬ۨۥۘۜ۠۬ۤۧۧۖۚ۟ۜۘۨۘۥ۠ۚ۟ۖۧ۠ۘۘۚۛۖۘۡۖۜۘ۠ۢۙۘۡ۬ۡۘۧۘۚۖ۬ۛۥۧۚۤۖ";
                                        case -1047000364:
                                            str16 = "ۙۥۜۖۦ۠ۧۙۗۦۦۙۘ۟ۢۦۘۧۛۖ۟۫ۤ۫ۧ۠ۚ۬ۧۜ۬ۤ۬ۚ۠۠ۧۡۛۛۥۢۗۛۙۛ";
                                            break;
                                        case 1287691321:
                                            str17 = "ۙۜۨۥۡ۟ۗۢ۠ۢ۫ۡۧ۠ۤۖ۬ۛ۠ۘۡۗ۬ۚ۫ۡۜۘ۬ۤ۠ۨ۟ۡۥ";
                                        case 1464416493:
                                            str16 = "۬ۙۥۘۧۜۨۨۡۨۚۛۦۛۚۛۙۥۜ۟ۜۧۘۚۥۥۘۨۡ۫ۙۖۘ۫ۦۥۨۤۡۘ۠ۗۡۘۦۛۨۘۚ۬۠۟ۖۥۚۖ۠ۡۤۨۘ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case 781263092:
                    String str18 = "۬ۘۜۘۢۡۘۘ۬ۙۨۘۧۦۜۘ۬۬ۧۜۙۚۜۤۨۡ۟ۤۛ۠ۡۨۥ۬ۧ۠ۧۢۨۘۢ۬۠ۥ۫";
                    while (true) {
                        switch (str18.hashCode() ^ (-688787000)) {
                            case -1185858718:
                                String str19 = "۟۟ۥۘۢۥ۟ۦۥۘۘۢۥ۬ۙ۬ۜۗۚۥۘۢ۠ۚۧ۫ۘ۠ۧ۠";
                                while (true) {
                                    switch (str19.hashCode() ^ (-177592442)) {
                                        case -1720678335:
                                            str19 = "ۛۖۖۘۛۛۚۜۚۚۤۘۨۘ۟ۦۧۘ۫ۙۤۗ۟ۘ۟ۙ۬ۚۛۦۤۙۜ۟ۜۖ۠ۜۘۢۦۨۨۦۤۖۘۖۘۡۖۧۡۗۦۚۥۜ";
                                        case -1195949019:
                                            str19 = !k2.areRequiredPopupsLegitimatelyClosed(set2) ? "ۦ۠ۦۘۦۙۢۡۦۙۥۖۖۤۡۧ۠۫ۦۘۗۨۨۘ۫ۧۨۤۛ۬ۦ۬۟ۘۘۘۖۘۛۦۜۘۗۖۚۡۜۜۘۧ۬ۚۜۡۥۡۙۡۘ" : "ۛۧۥۘۖۢۙۧ۠ۛۛ۬ۡۢۛۜۘۖۧ۟ۘ۟ۗ۬ۨۥۜ۠ۨۘۥۧۜۘ";
                                        case 559711822:
                                            str18 = "ۧۤۖۘۧۘۖۜۨۥۥۥۘۘۡۘۘۗۦۦۘ۬ۖۙۙۧۚۧۙ۬ۜۘۧۦ۫ۖۘۘ۟ۦۜۛۖۘۧۚۦۘۨۨۖۘ۠ۘۨۘ";
                                            break;
                                        case 570588621:
                                            str18 = "ۦۡۚۨۨۡۘۘۙۨ۠ۧۗۥ۬ۦۘۤۤ۠ۥۙۥۘۥۡۧ۟ۘۤ۬۠ۖۘۢ۠ۦۤ۫ۚۜۘۢۡۨۨۘۢ۠۠۠ۦۘۘۧ۠۫ۛۜۥ";
                                            break;
                                    }
                                }
                                break;
                            case -568853661:
                                str = "ۦ۬ۡۖ۫ۡۘ۠ۙۖۘ۫ۚ۠۟ۙۢۖۨۘ۟ۘۛۛۤ۠ۛۨۥۢۚۧ۠ۜۜۘ۟ۤۚۘ۬ۙۡۦۦۘۨۛۦۦ۟ۖۘ";
                                continue;
                            case 1623831621:
                                str18 = "ۢۢۢ۠ۙۤۖۜۖۘۡۜۢۜۖۜۥۘۦۘۘ۟ۥۚۧ۫ۧۖۘۘۧۗۜ۬۫";
                                break;
                            case 1914640035:
                                str = "ۚ۫۠ۨ۫ۢۜۧۥۙ۟ۙ۠ۖۧۖۤۥۥۘۥۘ۬ۚۡۘۛ۠۟ۘۦۘۖۢۤۡۜۨ۟ۢۘ۠ۤۨۥۡۡۘۢۤۤ";
                                continue;
                        }
                    }
                    break;
                case 796843415:
                    sb2.append(l2.decrypt("YGOJD8MigYZeUI0ilrBfYhvWY9dT400Lh4oB1SG1VnHeoluaJ/okcqTVRt5f+E4Ip5kP/yy6fWkB\nEw==\n", "OzPmf7ZSwu4=\n"));
                    str = "۠ۧۦۛۨۤۚۧ۠۟ۨۧۘۛۦۦۘۨۦۘ۠ۥۧۘ۟۬ۘۚۧۨۛۡۖۧۤۘۖۘ";
                    break;
                case 835957165:
                    set5 = t3.needMessagePopupIds;
                    str = "ۖۢۖ۠ۗۦۘۨۤ۫ۨ۠ۥۡۚۖۚۛ۟ۗۡۜۗۧۨۗۥۘۛ۬ۙ۠ۡۤۙۥۗۨ۫ۥۘۤۜۜۘ";
                    break;
                case 912499721:
                    sb5 = new StringBuilder();
                    str = "ۚۜۜۧۤ۬۠ۢۛۛۙ۫ۢۗۜۘۖۤ۟ۧۙۦۘۤۗۙۢۧۡۡۦ";
                    break;
                case 1036063604:
                    sb9.append(l2.decrypt("S1GBzCoLPcn8GQs=\n", "a7Q2fs+OjiA=\n"));
                    str = "ۨ۠ۦۘ۠ۢۤۜ۫ۚۦۖۨۘ۠۫ۧۗۙ۠۫ۨۘۘۨۦۨۜۦۘۢۢۡۛ۠ۖۘۗۛۖۗۦۖ۫ۗۥۘ";
                    break;
                case 1369033121:
                    sb2 = new StringBuilder();
                    str = "ۡۛ۟ۧۨۥۚ۠ۡۢ۬ۗۛۥۜ۬۫ۦۘۙۥۖۛۢۨۘۛ۠ۧۜۛ۬ۦۚ۟ۙ۬ۛۢ۟ۤۤۦۜۘۢۥۢ۟۠ۚ";
                    break;
                case 1446453656:
                    sb7.append(set4);
                    str = "ۚ۠ۙۛۘۥۘ۬ۤ۟ۛۤۡۘۙۨ۫ۤ۫ۨۘۦ۫۟ۧۘۚۤ۠ۦۖۤۘۘۘۥ۫ۘۛۥۘۨۚۜۧۡۧۢ۫ۛۛۜۖۘ";
                    break;
                case 1597718975:
                    String str20 = "۬ۧ۟ۖۗۜۗۗۘۘۙۛۤۡۜۘۤۜ۟ۖۧۘۘ۫ۚۦۘۚۦۗۙۗۢۙ۠ۜۘ۟ۦۡۡ۠ۡۥ۟ۜۘۨۤۡۘ۠ۜۚ۬ۛۘۘۘۢ۬";
                    while (true) {
                        switch (str20.hashCode() ^ (-2141094819)) {
                            case -528098437:
                                str = "ۗۖ۬ۖۦۦ۫ۡۚ۠ۜۤۥۥۨۘۡۙۢ۫ۦۛۛۜ۟ۛۗۚۛۦۜ۟ۦۘۘۛۛ۠ۥ۫ۧ۟۠ۜۘۦ۬ۥۘ۠ۜ";
                                continue;
                            case 369353972:
                                String str21 = "ۥۡ۫۟ۜۚۖ۠۫ۜۜۘۘۧۧۨۤۢۦۘۘۘۤۚ۠ۢۧۚ۟ۦۙۗۖۘۘۚۡ۠ۘۘۘ۫ۗۘ۟ۛۖۖۘۗۗ۠ۦۙۜ";
                                while (true) {
                                    switch (str21.hashCode() ^ 175416541) {
                                        case -747258689:
                                            str20 = "ۜۤۦۘۡۙۖۚۜ۫ۛۚۘۘۗۨۡۢۧۤ۬ۖۡۦۡۤ۟ۧ۟ۨۜۡۚۗۛۢۦۗۡۘ۬۬ۖۛ";
                                            break;
                                        case -45294526:
                                            str20 = "ۚۜ۬ۜۧ۠ۤۥۚ۟ۜ۬ۤۥ۬۫ۘۗۜۗۥۘۥۥۡۘۗۥۖۘۛۨۖۖۦۘۨ۬ۥۘۨۖۡۘۜۘۘۦ۠ۜۘۡۤ۫";
                                            break;
                                        case 560581476:
                                            str21 = !k2.areRequiredPopupsLegitimatelyClosed(set) ? "۠۫ۤۜۙ۫ۧۙ۠ۜ۫۟۬ۜۦ۫ۧ۫۫ۛۦۘۥۨۜ۟ۧۜۘۗۜۥۘۢۧۦۘۗۛۧۢۥ۠ۘۜۧ" : "ۢۜۖۘۤ۫ۘۢۧۜۙ۬ۨۧ۠ۙۗۤۜ۠۠۫ۤۥۚۜۡۨۘۢۜۖۘۧۡۘۢۤۗ۫ۛۗۦۜۘۨۢۦۘۡۥۢ";
                                        case 628047378:
                                            str21 = "ۜۦۥۦۦۥۘۨۜۖۘ۟۫۫ۨ۟ۛۙۘۘ۠۠ۖۘۢۡۘۘۡۤۨۘۡۚ۟";
                                    }
                                }
                                break;
                            case 705149003:
                                str = "ۦ۠ۨۘۥۚۤۙۚۖۘ۫۫ۙ۫ۦ۫ۛ۫ۚ۬۟ۖۘ۠۫ۛۨۤ۬۫ۢۖۧ۫ۗۥ۫۫۟ۧۙۤۙۨۙۢ۬ۚۥ۫ۦۨ۬ۨ۬";
                                continue;
                            case 1401134833:
                                str20 = "۟ۨ۟ۤۦۗۤۖۚۘۦۧۥۜۥۘۙ۠ۥۘ۠ۨۧۢۛۥۧ۫۠ۛۦۘۦ۬۟ۗۘۧۦ۫ۜ۠ۛۘۘۙۢۦۘۡۧۤ۟ۧۖۘۦۚۤ";
                                break;
                        }
                    }
                    break;
                case 1634144510:
                    sb4 = new StringBuilder();
                    str = "۬۠ۤۙۨۖۘۥۦۦۦ۠ۚۚۦۦۘ۠ۜ۬ۨ۟ۥۦۧۡ۬ۥۦۘۤۜۛۡۧۛۛ۬ۤ";
                    break;
                case 1661300848:
                    sb = new StringBuilder();
                    str = "ۦۡۤۧۥۡۜۢۥ۫ۨۥۘۜ۠۫ۤۤۤۘۙۡۘۚۚۦۘۘ۬ۘ۬ۧۢ";
                    break;
                case 1740334136:
                    sb3.append(k2.closedImagePopupIds);
                    str = "۫ۦ۠ۥۡۙۚۤۜۘۘۦۦۘۙۧۥۥۥۡۨۙۙۗۤۧۜ۬ۜۖۜۨ۫ۛ۬ۚۢۧۢۘۚ۟ۛ۬ۡۢۜۘۘۖۘۘۡۜۘۖۘۦۘ";
                    break;
                case 1820904401:
                    sb4.append(l2.decrypt("2I9Pq2RwZMrmvEuGMeK6LqM6u2X2iaBHP2bHcYbnsz1mTp0+gKjBPhw5gHr4qqtEH3XJW4vomCW5\n/w==\n", "g98g2xEAJ6I=\n"));
                    str = "ۦۦۤۚۧۨۘ۠ۨۦۘۜۙۡۘۚۜۖۥۦۜۘۦۙۨۘ۟۠ۡۘۥۛۘۘۛ۠ۚۚۧۦۨۜۨۖۛۤۢۛۡۘ";
                    break;
                case 1831446697:
                    sb5.append(l2.decrypt("WqxghtUFCXhkn2SrgD0eXU0Zs09H390w6GCPHgb0dw==\n", "AfwP9qB1ShA=\n"));
                    str = "ۤۖۘۨۜۧۡۢۜۙۦۨۙ۠ۡۘۖۨۗۙۧۥۚۦۘۖۦۡۡۜۧۘۖۤۦۘۗۗۛۦۤۗۢ۫ۗ";
                    break;
                case 1875726200:
                    sb.append(l2.decrypt("0vy59zi9tDBltDM=\n", "8hkORd04B9k=\n"));
                    str = "۟۫ۥ۬ۧۜۥۗۙۜ۠۫ۥۚۨۦۚۘۘۦۙ۠ۖ۬ۢۤۦۚۦۙۢۖۘ۬۫ۜۡۨۦۤ۬۠ۦۘ";
                    break;
                case 1925254120:
                    set4 = t3.needTextPopupIds;
                    str = "ۖۧۚۙۘۥۨ۫ۧ۫ۖۘۡۛۢۢۜۥۛ۟ۨۘۥۢۜۘۙۨۗۘۤۥ";
                    break;
                case 2014498393:
                    sb6.append(k2.describePopupLifecycle(set3));
                    str = "۟ۡۜۗۡ۫ۤۛ۠ۙۦۡۘۧۢۗۖۜۨۘۧۦۖۘۨۙۖۘۜ۠ۗۧۛۥۥ۫۠ۘۙۥۘۧ۠۫ۧۦۨ۟ۧۚۤۧۦ";
                    break;
                case 2101393689:
                    printStream.println(l2.decrypt("KbroXFexFKYpuvU6OuNZ7mTEvQQJ53S78iRVh/UpziCHYUj+SrEUpim66FxXsRQ=\n", "FIfVYWqMKZs=\n"));
                    str = "ۦۚۨۘۛۚۘۘ۟۠ۨۘۧۚۘۧۘ۠ۦۘۧ۫ۤ۟ۢۘۨ۟ۗۨ۬ۖۘۢۛۢ۟ۧۖۨۡۙ۠ۦۢۛۘۧۥۗۚ";
                    break;
                case 2128645249:
                    return false;
            }
        }
    }

    private void launchNextActivity() {
        PrintStream printStream = System.out;
        printStream.println(l2.decrypt("C5oZouB/Bsw4sxu80zyLM9Az31xov+5p5V2eTyH5/im1WdczNbmGOOM+xXs=\n", "UNZ4144cbo8=\n"));
        String str = "ۦۧ۟ۚ۬ۗۖۨۢ۠ۧۖۗ۬ۜۖۜ۬ۚۦ۟ۨۧۨۡۧۨۤۥۗۜۖۘ۠ۙ۟";
        while (true) {
            switch (str.hashCode() ^ 1042904749) {
                case -1984198911:
                    str = "ۗۥۨۘ۬۟ۦۘ۟۫ۗۤۘۜۘۢۛۧۙ۬۟ۧ۬۫۠ۙۘۦ۟۟ۤۧۨۘ";
                    break;
                case -1561573638:
                    String str2 = "۟ۙۖۘۖۢۘۘۦۙۦۜ۟۫ۡ۫ۥۡ۫۫ۨ۫ۙۦ۠ۘۘۡۧۚۧۛ۟ۜۜ۠۠۬ۨۘ۠ۤۘۤۥۦۘ۬ۜۧۘۚۦۨۘ";
                    while (true) {
                        switch (str2.hashCode() ^ 267057696) {
                            case -1946787700:
                                if (!isAllNeededPopupsClosed()) {
                                    str2 = "ۗ۬ۨ۟ۧۘۡ۠ۥ۟ۘۧۘۡ۬ۙۖۛۧۨۤۙۦۜۗۜۗ۠ۘۗۘۙ۟۬ۨۘۥۥۜۡۖۥۘ";
                                    break;
                                } else {
                                    str2 = "ۚۦۢۨۘۦۧۡۜۘۖ۟ۥۘ۫ۧۤۜۥۢۜۦۤۡۛ۠ۙۛۥۘۛ۟۬ۖۘۙۖۙۡ۬۫ۥۤۘ۬ۧۚ۟ۜۢۥۘ";
                                    break;
                                }
                            case -1835795219:
                                str2 = "ۙۦۥ۟ۦۜۨ۟ۤۤۦ۠ۨۥۖۨۥۙۜ۫ۦ۬ۧ۬ۚ۫ۦۘۗۡۧۗ۟۟۫۠ۖۘۧۛۨۜۥۦ";
                                break;
                            case -12279768:
                                str = "ۢۚۜۡۗۡۘۢۡۙۥۜۘۘۖ۠ۨ۟۟ۜۘۖۧ۟ۡۡۘۘۖۦۙۤۦۧۘ۫۬ۢ۬ۡۧۘۦۧ۟۠ۤۤۗۨۘۨۖۜۘۛۚۚۤ۠ۖ";
                                continue;
                            case 413376008:
                                str = "ۖ۟ۨۙۙۜۢۢۡۙۨۖ۠ۖۡۘۚۗۛۦ۠ۥۘۖۘۛۚ۫ۢۚۤ۬ۦ۟ۜۗۖ۟";
                                continue;
                        }
                    }
                    break;
                case -1291618457:
                    printStream.println(l2.decrypt("MAB4VzYCEE0DKXpJBUGdstKrs7W90eLo9+b8p/CI+6aOyarLz8yXsuerooW/2tXpxsX8nN0=\n", "a0wZIlhheA4=\n"));
                    this.handler.postDelayed(new l0(1, this), 300L);
                    return;
                case 1223503330:
                    printStream.println(l2.decrypt("ofSK78nWlZqS3Yjx+pUbUHpedxNCCUQ+UC8OLRVQeGoTL0Z1GzkbUF1QShZPAk4xRxQ=\n", "+rjrmqe1/dk=\n"));
                    String str3 = "ۘۘۨۧۘۨ۠ۧۨۘ۫۠ۨ۠ۤ۟ۦۜۖۤ۟ۤۡۛ۠ۨۦۜۚۤۛۖۧ۠ۨۖۙۥۘۦۢۖ";
                    while (true) {
                        try {
                            switch (str3.hashCode() ^ (-603518646)) {
                                case -943778845:
                                    String str4 = "ۦۙۖۘ۫ۢۤۗۡۜۘۘۚ۟۫ۧۗۚۨۦۚۙۡۘۙۙۥۦ۟ۗۛ۫ۢۥۧۗۚۚۦۘۜۗ۬۬۠ۖۢۥۙ۬";
                                    while (true) {
                                        switch (str4.hashCode() ^ (-1628978297)) {
                                            case -1945822944:
                                                if (fcRuQsQrcxOAzxwEalcM.LAUNCHER == null) {
                                                    str4 = "ۢۛۨۘ۬ۧۖۧۨ۫ۡۦۨۘۜۦۧۘۛۘۥۘۦۛۛۡۙۙۨۦۥۘۡۡۘ۠ۛۡ۟ۙۜۘۜۚۦۘۨ۫ۡ۠ۨۧۘۘۨۛۡۘ۠ۥۛ";
                                                    break;
                                                } else {
                                                    str4 = "ۥۥۢۖ۬ۛۡۧۧۖۚۘۥۨۦ۟ۚۜۖۘ۬ۜ۫ۚۚۜۨۘۘۗۗۦۘۛۚۦۘۗۚۥ۠۟ۜۘۡ۠ۥۜۚۧۜۘۧۧۛ";
                                                    break;
                                                }
                                            case -1389877708:
                                                str3 = "ۢۡ۫ۦۜۙۥۘۨۘۦ۬ۖۦ۠ۨۘۖۛۗۧۥۥۛ۫۟ۧۧۡۘۦۛۥۘ";
                                                continue;
                                            case 1345059444:
                                                str4 = "ۢۛۚۘۨۨۨۤۦ۠ۥۙ۠ۨۜۜۦۧۘۢۚۛۨ۫ۙۢۨۦۘۦۧۢۢۖ۟ۡۚ۟ۨۜۥۗ۠ۜۘ۟ۡ۠ۜۥۤۜ۫ۘۘۢۙۖۘ";
                                                break;
                                            case 1809590079:
                                                str3 = "ۨۚۖۨۖۨۡۘۡۘ۟ۥۘۤۡۛۨ۟۬ۥۖۦۢۖۗ۬ۖۨۗۡۘۢۧۙۦۨۧ";
                                                continue;
                                                continue;
                                        }
                                    }
                                    break;
                                case -846031426:
                                    str3 = "ۖۚۚۚۘۦ۫ۨ۠ۘۥۘۛ۠۟۬ۚۘۘ۟ۗۨۥۜۨۛۘۛۥ۠۠ۥۡۘۖۚۥۘۛۦۦ۠ۗۦۘۤ۠۟۠۬ۢۡۛۥۙۤ";
                                    continue;
                                case -77203370:
                                    String str5 = "ۦۘۜۘۧۚۡۖۗۦۗۙۢۢۥۖۥۚۙۦۥ۬۫ۡۚۦۤۗۙۘۥۘ";
                                    while (true) {
                                        switch (str5.hashCode() ^ 61190460) {
                                            case -1652984589:
                                                str5 = "۟ۖۖ۬۫ۖۘۦۧۧۥۨ۬۠ۡۨۢۨۙ۫۟ۖۚۗۢۗۗۘۡ۠۟ۨۖۘۙۧۨۘۡۗ۟ۗۨۘۗ۟ۖۘۛ۫۫";
                                                continue;
                                            case -777752556:
                                                String str6 = "۠ۗۛ۟۟۟ۡ۟ۙۚ۟۠ۧۧۡۘۢۢۢ۬۟ۨۚ۠ۡ۫ۗۤ۟ۥ۠ۘۤۦۚۧ";
                                                while (true) {
                                                    switch (str6.hashCode() ^ (-303526495)) {
                                                        case -869098752:
                                                            str6 = "ۢۥۡ۬ۡۡۘۦ۬ۥۘۘۜۗۛۛۜۘۥۢ۠ۥۘۘۘۖۙۜۜۡۢۙ۠۫";
                                                            break;
                                                        case -624576937:
                                                            str5 = "ۤۜۧۘۡۗۡۘۚۦۜۘۥۚۡ۠ۙۡۤۘۡۘۘ۫ۛۜ۫ۡۘۤۘۨۘ۟ۢ۟";
                                                            continue;
                                                        case 1545483694:
                                                            if (!l2.decrypt("uj/ggg==\n", "1EqM7sriWYE=\n").equals(fcRuQsQrcxOAzxwEalcM.LAUNCHER)) {
                                                                str6 = "ۚۡۢۚۗۧۡۦۥۘ۠ۜۥۢ۠ۘ۬ۛ۠ۤ۠ۡۧ۬ۛۜۙۛۗۚۤ۠ۗ۫ۦۙۖۘۥۤ۠ۗۦۘۘۢۖۡۘۧۡۛ۬ۦۦ۬۬۫";
                                                                break;
                                                            } else {
                                                                str6 = "۫ۘۘۘۦۜ۫ۛۥ۠ۢۨۡۘۖۚۚۨۗ۠ۤۗ۟ۤۢۢ۫ۤ۫ۥۢۧۜۙ۟ۚۘ";
                                                                break;
                                                            }
                                                        case 1624624907:
                                                            str5 = "ۛۨۥ۟ۖ۟ۛۘۜۘ۟ۡۧۘ۟ۗۧۗ۬ۨۘۨۡ۬ۡۦۚ۬ۡ۟ۨۖۢۘۡ۬ۤۙۨۘ";
                                                            continue;
                                                            continue;
                                                    }
                                                }
                                                break;
                                            case 1169918201:
                                                String str7 = "ۢۜۧۡۗۙۗۤۗۙۨۙۢۥ۟ۛۖۜۨۗۥۥۘۜ۫۠ۜۙۥۘۘۧۗ۟ۘۗ۬";
                                                while (true) {
                                                    switch (str7.hashCode() ^ (-1306340547)) {
                                                        case -1952831362:
                                                            String str8 = "ۖ۠ۘۘۤۛۛۤۢ۠ۥ۫ۥۧۦۜۜ۫ۡۙ۟ۛۚ۫۫۟ۜۦۥۡۧۤۡۧۗۛ۟ۢۤۢ۟ۛۙ";
                                                            while (true) {
                                                                switch (str8.hashCode() ^ 1670017559) {
                                                                    case -1415428088:
                                                                        str7 = "ۘۡ۟۠ۨ۟ۥ۠ۡۛۢۜۜۜۡۘ۠۠ۢۛۤۜۘۢۥۛۡۢۧۨۜ۟۟ۘۢۗ۬ۦۘ";
                                                                        continue;
                                                                        continue;
                                                                    case -155327469:
                                                                        str7 = "ۜۥۖۘۦۡۤۨ۬ۦۘ۠ۡۜۘ۬ۡ۬ۛۥۗۡۜۖۘۥ۬ۦۡۜۜۥۖۗۛ۬ۘۛۙ۟ۘۗۤ۠ۜۢۡۡۡ۠ۚۦۘ";
                                                                        continue;
                                                                    case 214145366:
                                                                        if (!l2.decrypt("0OyUh6qC6RrOnfub\n", "i8/Yxv/MqlI=\n").equals(fcRuQsQrcxOAzxwEalcM.LAUNCHER)) {
                                                                            str8 = "ۢۜۡۘۜۤ۬ۛۗۖ۠ۨۘ۟ۘۘۘۛ۟۫ۤۘۖۘۡ۫۫ۡۛۗۖۧۖ";
                                                                            break;
                                                                        } else {
                                                                            str8 = "ۥ۫۟ۛۗۜ۠ۨۦۘۦۜۥۘۘۤۨۢۧۜۘ۫ۙۖۘۛۢۥ۫ۖۨۘۘۖۨۘۘ۫ۜ۟۠ۦ۬ۘۤۗۨۖ۫ۨۘۡۧۨۘ";
                                                                            break;
                                                                        }
                                                                    case 831868825:
                                                                        str8 = "ۤۡۜۨۨۡۨۜۧۘۡۜۛۙۤۦ۬ۘ۫ۧۦۥۘ۬ۨۘۡۜۤۖۦ۫";
                                                                        break;
                                                                }
                                                            }
                                                            break;
                                                        case -1183274385:
                                                            break;
                                                        case -1064855218:
                                                            str7 = "ۚۖۧۘۖۛۨۘۗۢۗ۟ۚۢۢۚۦ۟ۢۙۙۛۦ۬ۨۘۘۛۢۘۦ۬ۘۘۛۢۦۘۚۥ۠ۦۨ۟ۖۗۙۜ۠ۥۘۙۦ";
                                                            continue;
                                                        case 379811426:
                                                            String strDecrypt = s0.decrypt(fcRuQsQrcxOAzxwEalcM.LAUNCHER, l2.decrypt("ehbbipCNxV5yFIncxt+XAA==\n", "SyTovqW78mY=\n"));
                                                            Intent intent = new Intent();
                                                            intent.setClassName(getPackageName(), strDecrypt);
                                                            startActivity(intent);
                                                            finish();
                                                            return;
                                                        default:
                                                            continue;
                                                    }
                                                }
                                                break;
                                            case 1965329016:
                                                break;
                                            default:
                                                continue;
                                        }
                                    }
                                    break;
                                case 1517802771:
                                    break;
                                default:
                                    continue;
                            }
                        } catch (Exception e) {
                            System.out.println(l2.decrypt("g+UB9M08+LWwzAPq/n94QWtB3S1G4xITYBGPPTk=\n", "2KlggaNfkPY=\n") + e.getMessage());
                        }
                    }
                    Toast.makeText(this, l2.decrypt("wNRiIcHkJyyxo1lsruhzfpHh\n", "JUTNxEtMwpY=\n"), 0).show();
                    printStream.println(l2.decrypt("KS6I5MELmT0aB4r68kgU7t2HYzlKzECWxscGLTUksCs8IaHU/UgX6dKEfBk=\n", "cmLpka9o8X4=\n"));
                    this.handler.postDelayed(new l0(2, this), 1500L);
                    return;
            }
        }
    }

    private void resolveSystemThemeColor() {
        String str = "۫۫ۥۘ۫۠ۖۘۜۛۗۗۖۦۦۖۦۘ۠ۛۡۜۥ۬ۖۗ۟ۤۦۦۦۧۖ";
        int i = 0;
        int i2 = 0;
        int color = 0;
        boolean z = false;
        boolean z2 = false;
        while (true) {
            switch ((((str.hashCode() ^ 800) ^ 508) ^ 646) ^ (-251643321)) {
                case -2011692582:
                    str = "ۗۜۘۘۗۘ۟ۜۖۦ۠ۢۡۗۦۦۘۜۘۗ۬۬ۨۢۖۥۢۦۦ۫ۖۘۤۖۨۦۜۡ۟۟ۥۘ۟۟ۡۥۗۖۘۘۢۧ";
                    i2 = i;
                    break;
                case -1935159928:
                    str = "ۛۦۘۘۢ۠ۥۢۨۨۖۡۡۘۥۖۥۘ۫ۢۨۜۨۛۢۘۚۦۖۛۡۢۖ";
                    z = false;
                    break;
                case -1835480327:
                    str = "ۙ۟۠ۡۙۨۨ۟ۗۗۤۧ۟۫ۚۢۚۨۗۘۜۘۙۢۖۥۧۘۡ۬ۜۜ۠ۨۙ۬ۡۘ";
                    break;
                case -1826320455:
                    this.isNightMode = z;
                    str = "ۥۖۗۢۡۘ۠ۙۘۧۦۡۘۙۛۖ۠ۡۥۙۢۨۘۧۤۚۡ۠۫ۜۧۘۥۤ۬ۘۢۜۘۗۗۦۘۖ۟۬ۛ۠ۦۘۡۛۧ";
                    break;
                case -1814986312:
                    str = "ۚ۫۬ۡۢۦۥۛۥۘ۬ۘۡ۫۠ۙۦۚۗۗۙۙ۬ۚۜۘۛۗۜۙ۠ۥۘۙۨۧ۟ۘۚۘۜۢۛۖۘۘۚ۠ۗۜ۬ۜ";
                    i2 = color;
                    break;
                case -1644117456:
                    str = "ۗۢۨۘۖۤۙۛ۟۠ۢۢۜۘۦۦ۠ۘۦۘ۫ۤۘۘۖۜۜۘۥۥۨۡۥۡۚۗۨ۟۟۬۟ۚۡۨۘ";
                    z = z2;
                    break;
                case -1317285266:
                    str = "ۛۦۘۘۢ۠ۥۢۨۨۖۡۡۘۥۖۥۘ۫ۢۨۜۨۛۢۘۚۦۖۛۡۢۖ";
                    break;
                case -674317407:
                    i = -1;
                    str = "ۨۚ۠ۥ۟ۘۨۧۨۚۗۜۘۡ۫ۥۘۢۖ۠ۨۥۧۘۘۗۢۤ۬ۦۘۛ۠ۨۘ";
                    break;
                case -445317160:
                    String str2 = "ۛ۟ۦۘۛۨۦۘ۫ۗۛۡۖۜۥۧۖۘۖۛۧۧۤ۟ۡ۟۟ۙۖۘۥۖۧۙۨۧۘۖ۫";
                    while (true) {
                        switch (str2.hashCode() ^ 267025696) {
                            case 315702526:
                                str2 = "ۢۥۧۘ۟ۤ۫ۘ۬ۥۘ۟۫ۘۘۡۧۨۘۡۧ۫۠ۨۘۘۥۦۧۘۧ۠ۡۙۢۡۦۗۤۡۡۘ";
                                break;
                            case 936956473:
                                str = "ۖۧۧۖۤۙۨۡۖۘۚۡۚۥۥۙۙۖۥۚۜۧۘۨۛۨۥۙۡۘۧۥۨۗۗ۠۠ۡۘ";
                                continue;
                            case 1504563616:
                                String str3 = "۠۟ۘۘۦۙۜۘۨۥ۠ۤۗۛۗۡۚۛ۠ۛ۠۠ۘۘۤۙۛۦۢۤۤۖۙ۬ۙۦۚۧۘۡۤ۠ۡ";
                                while (true) {
                                    switch (str3.hashCode() ^ 404568208) {
                                        case -1820397898:
                                            str3 = "ۨۚۨۗ۠۬ۨۗ۬ۧۙۖۥۡۦۘۤۛ۫ۜۘۘۘۛۤۧۛۥۘۨ۫ۦۘ۫ۖۘۘ۠";
                                        case -937158958:
                                            str2 = "ۢ۫۬ۨۢۡۚۧۜۘۜۜۡۦۚ۫ۤۗ۟ۤۚۨۘۧۙۜۨۜۘۘۥۛ۟ۙ۫ۘۘۜۢۧ۠ۘۧۘ۠ۥ۟ۧۛۨۘۚۜۗۡۛۡۘ۬ۧ۟";
                                            break;
                                        case -677450742:
                                            str2 = "۟۫ۖۘۚۦۛۜۤ۫ۤ۟۬ۨۖۨۘۦۜۖۘۦ۫۟ۙۨ۫ۙۦ۬ۙۧۗۚۤۜۘۖۤۦۙۦ۟۫۠۠۠۬ۜۘ۬۟۫";
                                            break;
                                        case 1916900306:
                                            str3 = z ? "ۙۤۥۨۥۧۢۨۧۘۘۗۜ۬ۚۨۗۥۥ۬۫ۡۘۥ۫ۡۘۘۦ۬ۨ۟ۦۘۦ۫ۙۤۥۦۘ" : "۬ۢۘۘ۫ۢۗۧۡۛۗۢۤ۬۠ۤۘۥۜۘۢۦۙۦۜۢۚۗۘۘۡۚۥ۫ۖۚۙۖۥ";
                                    }
                                }
                                break;
                            case 1902863030:
                                str = "۠۬ۥۘۥۨۢۧۗۖۘ۠ۧ۟ۙۖۜۚ۠۫۬۟ۥۘۙۢۨۘۥۙۚۘ۬ۦۗۚۨۘۗۡۥۡۨ۫ۡۜۘۦۡۢ۫۬ۚ";
                                continue;
                        }
                    }
                    break;
                case -320412405:
                    return;
                case 46368347:
                    color = Color.parseColor(l2.decrypt("qw3cWE5xZg==\n", "iDzuaXxAVAc=\n"));
                    str = "ۥۚۘۘۙۥۖۥۨۖۘ۫۠ۦۧۢۦ۫۠ۨۖۦۨۘ۠ۢۜۘۦۤۥۚۢۡۘۥۛۜۘۛ۫ۨۘۡۚۥۘ۟ۦ۟۬ۜۜۤۛۥۘ";
                    break;
                case 741764394:
                    str = "ۧ۬ۙۦۦۧ۟ۥۥۡۛۜۘۤۘۙۢۦۧۚۢۡۘۤۚۢۡۚۜۖۨۜۖۘۘ۫ۥۧۘ";
                    break;
                case 994981621:
                    z2 = true;
                    str = "ۢۦ۠ۚ۟ۜۗ۟ۧۥۡۚۛ۫ۡۘۜۥۚۥۘۧۘۛۡۛ۬ۧۜۘ۫ۖۢۨۚۥۘۖ۬ۦۘۥۨۥ۫ۦ۫۬ۥۨۤۜ";
                    break;
                case 1390940880:
                    String str4 = "ۛۤۨۘۖ۬ۙۧ۫ۖۢۙۜۤۜۥۘۢ۠ۢ۠ۜ۟۫ۧۡۚ۟ۜۡۛۡ۬ۦۘۖ۬ۦۡۧ۫ۖۛ۫۫ۖۛۥۘۘ";
                    while (true) {
                        switch (str4.hashCode() ^ (-1392527897)) {
                            case -1433975842:
                                str4 = "۠ۜۥۙۜۥۡۜۨۢۥۧۘۢۥۖۛۚۘۙۦۡۧ۟ۡۗ۫ۗۥۤۦۖۤۡۘۥۥۘ۟ۥ۟ۘۥۨۖۨۘۢۖ۫ۛۜۘۜۗۘۘ";
                                break;
                            case -841239276:
                                str = "ۥۙۡۘۛۥ۬ۚۧۥۛ۠ۥۘۜۙۚۥ۟۫ۚۡۘۤ۬۬ۧۡ۠ۡ۫ۖۙۙۦۘۢۚۦ۫ۥۨۤۚۖ";
                                continue;
                            case -124103593:
                                str = "۟۟ۡ۠۠ۜۜۚ۫۟ۨ۠ۜۜۨۘ۫ۢۛۨۤۢ۟ۙ۠ۘۦۨۘۦۙۗۗۧ۠ۜۛ۟ۖ۠ۖۢۘۧۖ۠ۧۢۥۘ";
                                continue;
                            case 412116433:
                                String str5 = "ۦۛۚ۠ۦۗۚۛ۫ۚ۫۫ۗۡۦۡۗۛۙۤۨۚۛۥۘۤۜۧۘۜۗۥۘۖۥۨۘۜۧۨۨۦۨۡۧۡۧۛۢۙۘۗ";
                                while (true) {
                                    switch (str5.hashCode() ^ (-1172598833)) {
                                        case -85797927:
                                            str5 = (getResources().getConfiguration().uiMode & 48) == 32 ? "ۧۦۘۤ۬۬ۗۚۖۘۧۥۤۘۖۘۘ۟ۚۘۦۜۖۘۡۦۨۗ۫ۙۡۙۥ۠ۜۧۘۤۚۘۘۗۖ۠ۖۚۨۛۛۖۤۚ۟" : "ۖۜۡۘۢۦۡۘۦۚۛ۟۟ۥۘ۠ۧۘۘۗۤۗۙ۟ۡۘۥۜۦۘ۫ۖ۟ۥۤۦۘۙ۬ۗۛۢۜۙۤۥۘۗ۫۠ۜۚ۠ۗۘ۬";
                                        case 135981991:
                                            str4 = "۠ۙۥۗۢ۟ۜۘ۬۠۟ۚۗۦۘۛۨۘۗۙۢۡۦۧۜۖ۠ۙۦۨۘۛۥۜۘۘۛۧ۠ۤۨۘۤۗ۬ۡۚۤۨ";
                                            break;
                                        case 607782412:
                                            str5 = "۬ۗۛ۟ۙۨۛۢ۟ۧۥ۫ۡۥۚۚ۟۬ۧۡۧۘۖۡۥۡۡۢۛۛۜۤۥۡۜۙ";
                                        case 1690647991:
                                            str4 = "۫۟ۙۧۘۥۖۦۜۘ۫۠ۖۘۧۗ۟ۨۤۖۚ۟ۘۙۘۘۛۤۛۤۙۚۗۦۗۡۦ۬ۧ۬ۜۘ۬ۘۦۥۜۚ۬ۨۡ۟ۨۘۥۤۡ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case 1613026031:
                    this.rootBackgroundColor = i2;
                    str = "۬ۢۤ۬ۧ۟ۜۗ۟ۙ۬ۙۦۘۘۚۦۘ۫۟ۥۛۘۛۘ۠ۨۜۥۢۡۖۘۧۢۨۘۛ۟ۙۤۥۜۘۦۗۗۡۙ";
                    break;
                case 1906743244:
                    str = "ۗۜۘۘۗۘ۟ۜۖۦ۠ۢۡۗۦۦۘۜۘۗ۬۬ۨۢۖۥۢۦۦ۫ۖۘۤۖۨۦۜۡ۟۟ۥۘ۟۟ۡۥۗۖۘۘۢۧ";
                    break;
            }
        }
    }

    private void setupImmersiveWindow() {
        Window window = null;
        String str = "۠ۙۨۘ۟ۙۙ۬ۗۘۘ۬ۥۤۡ۫ۤۤۨۘۧۢۗۡ۠ۚۜۛۡۘۢۤۛۦۖۦۤۡۡۘۖۖۨۘۙۗ۠";
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        while (true) {
            switch ((((str.hashCode() ^ 2) ^ 539) ^ 509) ^ 702930934) {
                case -2017601846:
                    str = "ۡۖۖۖۥ۟ۜۥۜۙ۬ۜۧۥۡۘۘ۬۠ۧۛۘۘۙۧۙۥ۠ۖۘۢ۫ۥۘ";
                    continue;
                case -1847475251:
                    String str2 = "ۚۖۖۦ۬ۖ۟ۖۘۥۧۦ۠ۡۢۘۢۢ۟۟ۦۘۤۗۘۘۚ۟ۘۘ۬ۖۡۘ۠ۨۥۙۜۧۘۘۨۖۦۤۜ۟ۢۦۘۧۛۘۢۜۚۛۤۨۘ";
                    while (true) {
                        switch (str2.hashCode() ^ (-2130553091)) {
                            case -1016993799:
                                break;
                            case 944011613:
                                str2 = "ۘ۠ۜۧۚۖۘۚۦۘۤۡۨۘ۫۫ۥۘۛۜ۫ۚۦۨۨۡۧۤۢ۬ۥ۫ۖۘۨۧۚۘۦۖۘۖۘۦۜۚۦۘ";
                            case 1576004411:
                                str = "ۡۛۨ۬ۢۦۘۢۨۙ۬۠ۥ۬۠ۨۘۘۧۜ۬ۛۥۘۥ۫ۥۘۨۡۜۘۨۘ۬ۖۘ۠۫ۗ";
                                break;
                            case 2081907557:
                                String str3 = "۫ۘ۬ۜۘۘۨۛۡۘۖۤۘ۫ۧۨۘۛۜۨۘ۟ۙۛۨۚۛۙۜۦۘ۬ۗۦۦ۟ۢۨۦۙۖۛۨ۟ۜۚ";
                                while (true) {
                                    switch (str3.hashCode() ^ (-1196744559)) {
                                        case -80247388:
                                            str2 = "ۤۤۜ۬ۨۤۤۖۤۢۗۡۢۢۡۘۨ۬ۡۘۦۙ۟ۦۛ۫ۖۗۨۥ";
                                            break;
                                        case 414898192:
                                            str2 = "ۥۗۥۘۖۚۛۧۛۥۘۖۡۗۦۦ۟۠ۖۙۖۖۘۧۖ۟ۙ۫۠ۘۜۜۘۤۨۤۢ۫ۘۗۘۧۘ۬ۧۘۡ۟ۥۜۡۛۦ۬ۘۘۛ۠ۛ";
                                            break;
                                        case 1529909586:
                                            str3 = "ۡۢۙ۬۫ۧ۬ۛۚۨۢۗۗۜۜۘ۠ۡۘ۫ۙۥۛۢ۟ۘۚۚ۬ۖۙۤۨۦ۫۬ۡۘۨ۫ۖۘۙۖۥۘۢۘۘۙ۠ۛ";
                                        case 1565195867:
                                            str3 = !this.isNightMode ? "ۢۧۜۘۚۚ۬ۘ۟۫ۦۙ۬ۥۙۦۥ۬ۜۘۘۖۗۡۧۙۨۙۖۦۜ۟۫ۧ۫ۨۖۧۖۚۖۦۦۘ۟ۖۤ۟۠ۦۦۙۤ۫ۘۦ" : "۠ۨۛۥ۠۟۫ۖۜۘۗۥۨۘۦۦۧۘۦ۠ۤۢۧ۬۬ۙۚ۠۬ۡۘۗۦۢۨۦ۫ۜۤۥۘ";
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case -1513550132:
                    window.getDecorView().setSystemUiVisibility(i2);
                    str = "ۦۦ۠ۘۤۜۘ۫ۙۡۦ۫ۗۗۜۥۘۡۗۙۙۛۖۙۧۡۘۢۧۥ۫ۢۗۗۗۦۘۖ۫ۦۘ";
                    continue;
                case -1471466174:
                    window.getDecorView().post(new i0(this, window));
                    str = "ۧ۫ۘۘۧ۬ۢ۟ۜۙ۬ۚۡ۠۟۟ۤ۫ۘۘۛۖۜۘ۠۠ۡۧۢۚۦۢۖ۟ۘۜۘ۟۫ۥۘۚۤۗۘۧ";
                    continue;
                case -1458614434:
                    String str4 = "ۛۢۖ۟ۦۙ۟ۥۜۘۤۚۡۘۨۛۦۘۙۧۡۚۚ۫ۤۦۖۙ۬ۤۚ۬ۢۖۦۖ۬ۦۘۛۗۖۘۗۙۧۖۢۛ۠ۥۢ";
                    while (true) {
                        switch (str4.hashCode() ^ (-1988451958)) {
                            case -2122306477:
                                String str5 = "۠ۨۡۖ۫ۖۙ۫ۘۘۦۛۗۛۢۧۛۤۥۜۚۧۥۜۤۛۘۘۘۧۡۘۡۡۥۚ۬ۚ۫ۡۨۘۢۤۥۘ";
                                while (true) {
                                    switch (str5.hashCode() ^ 20785942) {
                                        case -1438679583:
                                            str5 = i4 >= 30 ? "۟ۡۨۖ۟ۢ۠۫ۗۢ۠۟ۢۥۜۘۘۤۛۙ۫ۨۘۥۙۢۛ۟۟ۢۦۖ۠ۘۖۜۤۘ۫ۨۙ۠ۢۘ" : "۠۫ۥۡۡۘۨۤۥۘ۫ۜۖۢ۬ۛۘۦۘۘۢۨ۟ۦۧۗۡۘۘۦۥۤ";
                                        case 602388497:
                                            str5 = "ۖۢۨ۟ۧۘۗۜۜۗۚۜۘ۫ۢۖۘۡ۟ۡۖۚۡۗۡۡۢۡۘۡ۠ۨ";
                                        case 972218980:
                                            str4 = "ۗۧۜۘۜۜۘۖۤۡۘۛ۠ۡ۠ۦۥۘۢ۫ۧ۬ۡ۟۟ۛ۫ۜۘۛۗ";
                                            break;
                                        case 1415356926:
                                            str4 = "ۗۗ۟ۙۥۖۘۜۚۢۤۜۘۜۖ۫ۛۢ۫ۖۡۧ۫ۘۧۘۛۙۡۘ۫ۦ۟۫ۡ۬ۡۢ۟ۙ۟ۚ۟ۛۦۘ۫ۛۧۛۖۢۘۨۙ۟ۙۚ";
                                            break;
                                    }
                                }
                                break;
                            case 187537693:
                                str = "ۨۗ۠ۨۜ۠ۤۢۖ۬ۨۘۦ۬ۡۗۡۖۛۙ۠ۨۡۚۘۚ۠ۦۥۜ۟ۨۡۙ۬۫ۦۖۢۜۜۘۘۛۘۢ۬ۨۖۨۨۘ";
                                continue;
                            case 1207983802:
                                str = "ۚ۠ۥۢۡۤ۫۫ۦۘۗۨۡۨۨۛۛ۠ۨۘۖۙۢۖۧۡۘۢۚۜۤۗۧ";
                                continue;
                                continue;
                            case 1682630301:
                                str4 = "ۜۨۛۨۜۙ۫ۥۦۘۧۚۥۘۖۜۙۘۡۖۘۛۗ۟ۡ۫ۨۦ۟ۗۦۚۧۡۢ۫ۤۛۛۖۙۘۘۚ";
                                break;
                        }
                    }
                    break;
                case -1395979517:
                    i3 = 13570;
                    str = "ۡۢ۬ۗۧۧ۬ۨۜۘ۫ۗۛۖۦۡۘ۠ۤۨۘۦۡۥ۠۠ۖۧۦۡۘۖ۫ۗۧ۬ۡۢۧۖۚ۟ۡۘ۫ۙۖۘۗۙۢۚ۟ۘۘ";
                    continue;
                case -1311102601:
                    str = "ۤۨ۟۟۬۟۟۬۫ۗۧۘۘۚۙ۫ۚۨۡ۫ۖۜۘۨۛۛۘ۠ۨۥۧۜۥۚۦۧۜ";
                    i4 = Build.VERSION.SDK_INT;
                    continue;
                case -966726438:
                    str = "ۜۧۧۚۘۧۘۖۛۘۘۜۡۛۖ۠ۘۛ۠۠ۙۢۛۨۗ۬ۜۨۧۘۚۖۦۘۨ۬ۘۛۦۡۥۜۨۘ۫۫ۡ";
                    continue;
                case -404121299:
                    e0.c(window);
                    str = "ۨۗۗۡ۟ۦۘۙۢۗۦۡۢۖۡۜۘ۟ۢۗۦۥ۬ۘۢۜۘۤۡۡۨۦۡۡ۠ۖۘۧۛۛ";
                    continue;
                case -57182056:
                    str = "ۢۤۥۘ۬ۛۗۧۖۖۘۗۥۙۙ۫ۘۜۨ۠ۗ۬ۤۖ۠ۡ۫ۤۜۤۦۨۘ";
                    i2 = i3;
                    continue;
                case 88807963:
                    window.setStatusBarColor(this.rootBackgroundColor);
                    str = "ۢۜۤۛۛۤ۫ۛۘۘۜۦۗۥۤ۟ۡۦۨۘۤۤۖۗۧۥۘۚ۫ۘۚۚۘۘ۠ۥۨۘۡ۫۫";
                    continue;
                case 415234621:
                    str = "ۖۡۨۥۨۨۧۖۥۘۙ۬ۚ۠ۧۙ۫ۨۜۘۖ۟۠ۗۡۥۘۥۖۥۘ۟ۢۡۚۨۘۘۙ۫ۜۢۖۥ۬ۢۤ";
                    window = getWindow();
                    continue;
                case 759487036:
                    return;
                case 1000300135:
                    i = 5378;
                    str = "ۧۛۛۗۘ۠ۚۥ۠ۢۘۚۢۛۦۘۢۢۖۗۚ۬۬۟ۤۘۖۘۜۤ";
                    continue;
                case 1154665352:
                    str = "ۦۦ۠ۘۤۜۘ۫ۙۡۦ۫ۗۗۜۥۘۡۗۙۙۛۖۙۧۡۘۢۧۥ۫ۢۗۗۗۦۘۖ۫ۦۘ";
                    continue;
                case 1765354148:
                    str = "ۡۖۖۖۥ۟ۜۥۜۙ۬ۜۧۥۡۘۘ۬۠ۧۛۘۘۙۧۙۥ۠ۖۘۢ۫ۥۘ";
                    i2 = i;
                    continue;
                case 1850771745:
                    String str6 = "ۤ۟ۨۘۧ۫ۙۜۙۦۘۜۖۜۢۚۤۦۗۚ۟۬ۢۨ۟ۥۘۨۘۧ۟ۘۛ۟ۜۘۙۚۢ۠۬ۧۡۜۜۘ۫۬۫ۜۗ۫ۜۦ۫۫۟ۦۘ";
                    while (true) {
                        switch (str6.hashCode() ^ (-1403961092)) {
                            case -82724934:
                                break;
                            case 1262007454:
                                str = "ۘۘۤ۠۬ۤۘۘۡۘۚۖۡۛۦۦۘۜۚۥۚۡۙۗۛۚۗۥۙۤ۠۟ۚ۟ۨ۠ۥ۟ۚۖۘ۬ۛۙ";
                                break;
                            case 1487878629:
                                String str7 = "ۖۨۡۘ۬۬ۙۤۤۨۖۧۘۙۧۘۨ۟ۖۦۗۨۘۦۢ۬ۛۧۘۘۥۛۨۨۜۨ۫ۛۢ";
                                while (true) {
                                    switch (str7.hashCode() ^ 1791129770) {
                                        case -338582769:
                                            str7 = "ۗۖۖۘۦ۠۠ۨۛۨۙۧۘۘۨۖۘۘۛۙ۬ۗۨۦۘۗۙ۟ۥۢۘ۟ۨ۠۬۠۬۠ۡۗۚۢۖۦۡ۫ۢ۠ۧۤۤۗ۬۠۬۟۟ۜ";
                                        case -212812308:
                                            str6 = "۫ۦۘۘۧ۟۠ۙ۬ۤۧۥۧۘۘۖۜۘۤۜۧۘۗۜۧۖ۬ۙۦۙۘ۟ۡۥۗ۫ۗۗۧۥۘ";
                                            break;
                                        case 151131763:
                                            str7 = i4 >= 23 ? "ۤۛ۫ۙۘۗۖۦۖۖ۟۫ۥۘۚۤ۫ۡۛۨۥۧۚۚۡۧۦۦۖۚ۠ۧۦۘ" : "۬۠ۡ۫ۢۢۧۖۥۥۡۖ۠ۗ۠ۙۥ۟ۤۤۤۚۧۘۙۘۡۨۜۘۘۨۨۘۤۡۘۚۡ۬ۥۢ۫ۤۨۗۢۜ۠";
                                        case 268041672:
                                            str6 = "ۦۗ۬ۢۚۥۘۢ۠۠ۜۥۥۘۖ۟۠۟۫ۚ۫۟ۨۘۥۙۨۘ۬ۦ۠۬۬ۖۘ۫ۥۥۘۘۛۖۢۚ۫۟ۤ۬ۘۙۨ۟ۖ۫ۛۧۥۗۙ۫";
                                            break;
                                    }
                                }
                                break;
                            case 2028575181:
                                str6 = "ۗۧ۫ۥۙۥۢۨۘۤۤ۫۫۠ۖۜۥۥۗۛۥۙۨۦۘۜۘ۬ۜۦ";
                        }
                    }
                    break;
            }
            str = "ۙ۠ۚ۫ۤۙۧۙ۬ۚۧۘۡ۠ۛۛۡۘۨۖ۬ۡۘۘۘۦۛۤۦۥۘۜۡۘ۫ۤ۟ۗۨۗۙۤۨ";
        }
    }

    private void startConfigCheckLoop() {
        String str = "ۛ۫ۖۘۥ۟ۛۗۜۡۡۗ۟ۤۙۢۛۘ۠۠ۧۢ۟ۤۜۜۦۤ۟ۧۥۗۘۧۘۚۦۛ";
        while (true) {
            switch ((((str.hashCode() ^ 954) ^ 723) ^ 568) ^ (-501547128)) {
                case -1652643942:
                    this.handler.post(this.checkRunnable);
                    str = "ۦۦۘۘۥۚۚۢۡۧۘ۬ۧۢ۟ۚۥۤ۠ۦۖۘۜۨۦۤ۬۬ۚۡۤۨۤ۠ۜۗۜۘۥۘۡۘ۟۠ۢ۫ۢۡۗ۠ۨۤ۠ۡۘۨۗ۬";
                    break;
                case -464658635:
                    str = "ۢۜۧۘۘۚۡۛۧ۠ۤۜ۠۟ۤۙ۬۫ۛۛۧ۠ۤۘۢۚۘ۬ۙۘۦۢۚۨۤۘۖۘ";
                    break;
                case 1072318563:
                    return;
            }
        }
    }

    @Override // android.app.Activity
    public native void onCreate(Bundle bundle);

    @Override // android.app.Activity
    public void onDestroy() {
        String str = "ۜ۟۟ۢۖۧۘۤۢۙۜۛ۟ۗۘۦۨۦۨۘۢ۠ۦۘ۠ۡۦۘۘۨۦۘۙۧۧۦ۫ۡۗۚۗ۟ۘۘۛ۟ۨۤۜۨۘ۬ۖۘ۫ۜۙۡ۫ۖۘ";
        while (true) {
            switch ((((str.hashCode() ^ 78) ^ 250) ^ 511) ^ (-259130770)) {
                case -2131215209:
                    str = "۠۬ۜۥۨۨۜۦۢۗۡۘۚۗۖۢۜۜۜۢۡۘۗۦۨۛۖۘۘۨۗۖۧ۫ۤۜۨۡ";
                    break;
                case -1636477174:
                    this.handler.removeCallbacksAndMessages(null);
                    str = "ۖۢ۟۠ۦۨۘۜۤۜۘۚۦۧ۟ۤ۠۬ۤ۬ۥ۠ۖ۬۫ۚ۟ۙۚۢ۬ۗ۬ۜۘۘۡۨۦۚۖۘۖۧ۬۠۫ۚ۫ۙۢ";
                    break;
                case -1000133318:
                    return;
                case -179547092:
                    this.isDestroyed = true;
                    str = "ۖۖۧۜ۠ۗ۠ۢ۠۠ۥۤۚۘۡۘۨۤۦۖۢۙۥ۟ۘۡۥۨۘۤۨۛۦ۫ۖۘ۠ۢۘۙۨۗۛۦۙ۟ۛۚۢ۠۠";
                    break;
                case 1604732252:
                    super.onDestroy();
                    str = "ۙۚۦۘۦۧۘۢۢۙۙۦۜۘۢۜۡۘ۟ۧۙۛۘ۠ۚۦۖۨۘۢۘۘ";
                    break;
            }
        }
    }
}
