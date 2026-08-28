package gTBLD.dev.XSSTG.free;

import android.app.Activity;
import android.app.Dialog;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.webkit.JavascriptInterface;
import android.webkit.WebView;
import android.widget.Toast;
import com.iqoocg.nm.fcRuQsQrcxOAzxwEalcM;
import com.shadow.okhttp3.internal.http.StatusLine;
import com.swift.sandhook.utils.FileUtils;
import core.pro.android.notify.b0;
import core.pro.android.notify.h;
import core.pro.android.notify.k2;
import core.pro.android.notify.l2;
import core.pro.android.notify.v;
import core.pro.android.notify.w;
import core.pro.android.notify.x;
import core.pro.android.notify.y;
import core.pro.android.notify.z;
import gTBLD.dev.XSSTG.free.JsInterface;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: /workspace/unpacked/classes2.dex */
public class JsInterface {
    private final Activity activity;
    private volatile Dialog boundDialog;
    private final String boundPopupId;
    private volatile WebView boundWebView;
    private volatile boolean disposed;
    private boolean isPrepared;
    private String jsOnBuffering;
    private String jsOnEnd;
    private String jsOnError;
    private final Handler mainHandler;
    private MediaPlayer mediaPlayer;

    public JsInterface(Activity activity) {
        this(activity, null);
    }

    public JsInterface(Activity activity, String str) {
        this.mainHandler = new Handler(Looper.getMainLooper());
        this.jsOnEnd = null;
        this.jsOnError = null;
        this.jsOnBuffering = null;
        this.isPrepared = false;
        this.activity = activity;
        this.boundPopupId = str;
    }

    public static /* synthetic */ void a(JsInterface jsInterface) throws IllegalStateException {
        String str = "ۡۙۤۢۜۦۘۢ۠ۡۘۚ۫ۚۚۘۦۘۧۜۧۘۧۢۘ۫ۛۚۜۢۜۜۨۧۜۤ۟ۨۛ۬ۡ۠ۨۘۚۧۜۘۘۖ۫ۡۢۤ۫ۗۦۘۘۘ۬";
        while (true) {
            switch ((((str.hashCode() ^ 171) ^ 641) ^ 47) ^ 1639815708) {
                case -1907176369:
                    return;
                case 212177018:
                    jsInterface.lambda$resumeMusic$14();
                    str = "ۡۛۥ۬ۙ۫۠ۜۖۘۨۘۧ۟ۜ۬ۜ۫ۢۨۡ۠ۥۨۧۘ۫ۨ۠ۘ۟۠ۢۥۗۥۚۤۦۥۧۘۛ۟";
                    break;
                case 1075038563:
                    str = "ۗ۬۟۬ۖۡۜ۠ۦۘۚۘۦۡۤۖۘۧۘۖ۟۟ۜۘۨ۟ۨۘۦۜۖۘۧۧۙ";
                    break;
            }
        }
    }

    public static /* synthetic */ void b(JsInterface jsInterface) throws IllegalStateException {
        String str = "ۥ۠ۢ۬ۤ۬ۘ۟۟ۜۚۨۛ۟ۡۡۚ۟ۤۚۨۙۚۡۘ۟ۘۚۧ۬۠";
        while (true) {
            switch ((((str.hashCode() ^ 445) ^ 419) ^ 399) ^ 273615090) {
                case -1849184950:
                    return;
                case 555892159:
                    str = "۟۟ۜ۠ۢۖۢۖۜۘۖۖۚۦۥۨۘۜۜۖۘۦۥ۫ۨۨۧۘ۬ۜۦۦۥۗۖۢۨۨ۫ۜۘ۟ۖۙ۬ۤۙ۬ۘۢۧ۫ۡ";
                    break;
                case 1919735942:
                    jsInterface.lambda$stopMusic$15();
                    str = "ۖۜۦۘۜۖۜۥۗ۠ۘۖۦۘۜۦۜۘۥۢۛۨۚۢۥۨۨۖ۟ۗۤۙ۟ۦۤۧ۟۟ۘۧۥ۠ۧ";
                    break;
            }
        }
    }

    public static /* synthetic */ void c(JsInterface jsInterface, MediaPlayer mediaPlayer, int i) {
        String str = "۠ۢۡ۟ۥۨۘ۠ۘ۫ۙ۬ۙۖۧۖۘۗ۫۫۟۠ۖۘۛۖۤۘۗۘۘۨۗۨۘ";
        while (true) {
            switch ((((str.hashCode() ^ 997) ^ 928) ^ 320) ^ (-1728906434)) {
                case -1167528166:
                    jsInterface.lambda$playMusic$11(mediaPlayer, i);
                    str = "۬۬ۥۘۡۙ۬ۜۛۢۥۜۨۘۨۦۖۨۜۦۙۜۚ۟۟ۖۢۥۡ۟ۙۚۙۖۦۘ۬ۙۦۘۛ۬ۙ۟ۢۘ";
                    break;
                case -115746780:
                    str = "ۚ۠ۧۨۜۜۘۨ۬ۨۢۨ۫ۜۘۡۖۥۜۜ۫ۙۤۢ۫ۡۛ۬ۨۚۢۗ۟ۜۢۥۨۘ";
                    break;
                case 1015356452:
                    return;
                case 1731976723:
                    str = "ۙۡۘۜ۠ۡۚ۟ۖۘۡۗۤۧۧ۟ۦۛۙۗۡۖۘۛ۟۟ۗ۠ۡۘۚۤۙ";
                    break;
                case 1999888407:
                    str = "۬ۧۘۚۘۛۢۧ۠ۙۨۗۧ۬ۜ۫ۦ۟ۨۨۜۜۢۢۥۗۡ۠ۢۦۘۜۖۤۢۢۘۘ";
                    break;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0013. Please report as an issue. */
    private void closeBoundPopup(String str) {
        Map<String, WebView> map = null;
        String str2 = "۠ۚ۟ۨۛۚ۬ۗۛۢۢۘۧۖۖۘ۬ۡۡۜۙۖۦ۬۫ۦۛۥۗ۠ۛۜۜۗ۬ۢ";
        while (true) {
            switch ((((str2.hashCode() ^ 417) ^ 67) ^ 373) ^ 617155341) {
                case -2048197230:
                case -528164414:
                    break;
                case -2026490603:
                    String str3 = "۬۬ۢۨۢۨۘۛۚۚ۟ۨۥۚ۬ۡۜ۫ۜ۬ۙۨۨۧۘۗۢ۟۠ۡۡۘۗ۠ۦۘ۬۠۬ۦۖ۫۬ۜ";
                    while (true) {
                        switch (str3.hashCode() ^ (-1630012218)) {
                            case -1040044077:
                                str3 = "ۦۘۘۘ۫ۖۖۧۚ۫۟ۙۘۜ۠۠ۘۢۦۘۤۖۜۘۘۥ۬ۖۨۜۘۥۛۡۘۚۜۜۤۨۨ۬ۦۧۚۗ";
                            case -561652371:
                                break;
                            case -92658677:
                                str2 = "ۤۛۨۘۗۦ۬۠ۚۨۗۥۦۘۨ۠ۨۘۛۧۗۨ۫ۡۦ۬ۜۦ۟۬۫ۦۥ۟ۘۡ۟ۨۜۘۖۜۖۘۦۤ۬ۨ۠ۤۘۨۘۜۧۛۜۗۜۘ";
                                break;
                            case -27791945:
                                String str4 = "۬ۧۘۨۧۧ۬ۥۖۡۜۡۛۗ۟ۘۘۗۥۖۘۨۥۖۥ۫ۡۘۨۚۘۢۛۢۖ۟۬ۥۛ۟۠ۙۧ";
                                while (true) {
                                    switch (str4.hashCode() ^ (-390727997)) {
                                        case 707787821:
                                            str4 = !this.disposed ? "ۙۖۗۖۢۜۗۙۦ۟۠ۡ۫ۖ۬ۢۦۧۘۤۢۡۘۗۧۥۘۥۢۘۘۗۙۦۘۡۖۡۘۦۦ۫ۤۡۘ۫۠ۖۚۖۗۗ۠ۜۦۤۘۘ۠ۧۖۘ" : "ۘۨۨۗۙۜۗۘ۬ۛۦۗۖۜۜۗۦۘ۠۫ۗۥۘۙ۟ۤۜ۫ۗ";
                                        case 996657780:
                                            str4 = "۟ۚۗۙۢۢ۬ۛۡۘۦ۫ۨ۟ۦۢۜۗۥۡۜۦۘۜ۫ۡۘۖۨۜۘۧ۠۟";
                                        case 1136945616:
                                            str3 = "۠ۨۡۦۘۧۘۡۧۤۨۢۖۘۙۙۘۛ۟ۨۦۘۘۘۛۙۖۘۖۜۖۘۗۚۗۧ۬ۜۘۥۢۦۘ۫ۜۜۦۧۢۦۢۗۚۖۘۘۥۧۥۘۙ۟ۧ";
                                            break;
                                        case 1770763253:
                                            str3 = "۬۬ۘۘۧۦ۫ۨۚ۠ۦۖۢۛۨ۠ۢ۬ۤۜۥۧۧۙۧۙۥۘۨۜۨۘۤۧۖۘ۬ۜۦۙۗ۟۟ۥۜۘۧۥۥۘ۫۫ۤۢۥۗۧۧ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    str2 = "ۢۧۘۘۛۢۙۘۢۖۧۨۦۘۘۗۨۜ۬ۦۛۨۘۘۦۢۖۘ۠ۥۦ۫ۢ۬";
                    break;
                case -1764299785:
                    map.remove(this.boundPopupId);
                    str2 = "ۖۨۧۘۖۚۦ۟ۧۥۜ۠۫ۘۛۢۛۧۨۘۢۤۙۤۛۡۙۡۡۘ۠ۙ۟۫ۧ۠ۘۥۘۚ۬ۖ۫ۥۥ";
                case -1370936996:
                    map = k2.htmlPopupWebViews;
                    str2 = "ۡۤۨۢۥۤۖ۬ۖۘۡ۟۠ۗ۬۬ۛۜۘۘۡۚۢۗۨۥۧۜ۠ۚۚۛۛۨۘ۟ۜۗۖۖۚۚۚۗ";
                case -1358311629:
                    String str5 = "ۛۢۦۘۢ۟ۦۨ۫ۖۗۧۛ۠ۥۦۘ۟ۥ۠ۦ۟ۜ۟ۛۗۘۚۢ۠۠ۚ۫ۢ۠ۥۦۘۧۚۛۛۥۦۦۛۡۘ۠";
                    while (true) {
                        switch (str5.hashCode() ^ 1017872005) {
                            case -1280286798:
                                str2 = "ۛ۟ۦۖ۠ۨۘۧۚۚۙ۬ۛۘۨ۟ۗ۫ۢۨۖۦ۬ۗۦۘۛ۠ۖۘۙۖ۟ۘۛۨۘۛۢۨۘ";
                                break;
                            case 655744167:
                                str5 = "ۡۧۛۖۡ۫ۖۡۦۘۧۡۜۘۖۖۜۘۨۛۥۘۖۥۖۥۢۤۗۙۡۘۤ۟ۗۜۛۤۛۤ۠۬ۦۘۜۘۦۘۧۘۨۘۢۥۜۘ";
                            case 920466975:
                                break;
                            case 2058119104:
                                String str6 = "ۜۢۨۘۢۚۖۘۧۨۧۗۜۘ۫ۢۛ۬ۤۨۘۛۡۥۘ۫ۡۛۦۥۜۗۚۢ۟ۖۖۛۜۥۘۤ۬ۖۘۘ۫۠۬۫ۘۢۧۘ";
                                while (true) {
                                    switch (str6.hashCode() ^ 357703039) {
                                        case -2057345713:
                                            str5 = "ۡۛۥۘۥۘۥ۠ۜۨۘۙ۬ۨۚۥۡۘۢۢ۟ۜۛۢ۫ۨۘۡۘۢ۟ۤۦ۬ۨ۟۫ۜ۬ۜ۟ۤۛۦۤ۟ۜۖۘۘ۠۠";
                                            break;
                                        case -1862913226:
                                            str6 = str != null ? "ۜۢۦ۠ۖۚۛۖۙۙۘۖۘۚ۬ۖۘ۫ۜۗۘۘۜۘۡۡۧۗ۫ۨۢۘ۠ۙۘۗۨۢۘۘۥۡۘۘ۠۟ۦۘۚۗۚۡ۟ۗ" : "ۚۖۨۘ۟ۨۖۘ۠۬ۖۦ۬ۧ۠۬ۡۘۡۡ۬ۡۗۜۦ۬ۖ۬ۧۘۖ۟۫ۥۤۖۖۦۘ";
                                        case -1032951826:
                                            str6 = "ۧ۟ۦۘۧۙۦۦ۫ۥۘۘۖۛۖۦۘۗ۫۫۟۟ۜۘۛۜۧۘ۫ۦۖۙۧ۠ۥۧۗ۬۠ۢۘ۬ۤۗ۟";
                                        case 2123672356:
                                            str5 = "ۥ۠۠ۖ۬ۦۛۗ۫۟ۦۜۖۥۗۗۦ۫ۤۨۧۘۢۖ۬ۥۡ۬ۨۢۦ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case -1068172923:
                    str2 = "ۢۧۘۘۛۢۙۘۢۖۧۨۦۘۘۗۨۜ۬ۦۛۨۘۘۦۢۖۘ۠ۥۦ۫ۢ۬";
                case -911496985:
                    String str7 = "ۡۤۖ۫ۢۗۦۖ۬ۤۘۥۘۢۤۘۘۥۜۘۘۨۧۦۥ۠ۖۡۛۧۨ۠ۖۢ۠ۘۘ۟ۦۚ";
                    while (true) {
                        switch (str7.hashCode() ^ (-828024285)) {
                            case -1151686171:
                                str7 = "ۚۚ۠ۧۜۦۘۛۦۡۘۜۜ۬۫ۖۘۙ۫۠۬ۗۙۚۧ۬ۖۜۧۘ۬ۖۘۤۡۙ۫ۨۧ۫ۗ۟ۥۚۘۘۖۙۘۘۚ۠ۖۘ";
                            case -558167698:
                                break;
                            case -453851326:
                                String str8 = "۠ۤۥۘۨۜۜ۬ۚۜۘ۫ۘۨۘۤۜۡۘ۟ۖۦۘ۠۟ۨۘۙۜۘۗۧۘۘۡۘ۠ۖۜۤۧ۠۬";
                                while (true) {
                                    switch (str8.hashCode() ^ 1845013063) {
                                        case -2022367448:
                                            str8 = "۬ۦۡۤ۬ۗۧۙۡ۫ۥۤۦۢۜۘۡۖ۬ۛۖ۬۬۠ۘۘۜۦ۟ۛ۫ۙۚۖۚۨۡۙۜ۬ۨۦۥۡۘ";
                                        case -1958692344:
                                            str7 = "۫۠ۖ۬ۡۛۚۖۘۘۜۥۧۘۨۤۡۘ۫ۘ۠ۗ۟۫ۢۥۚۤۥۢۥ۫ۡۜۚۗۗۦۙۚ۬۠۟ۦۡۨۙ۫۠۠ۜۘ";
                                            break;
                                        case -221126849:
                                            str8 = this.boundPopupId != null ? "۬ۖۦۡۜۗۨۦۥۤۥۚۛۚۜۘ۫ۙۤۗ۠۫ۧۙۚۖۡۘ۟ۤۦ۬ۜ۫ۦۡ۟۫ۘۗۤ۬ۦۜۧۨۤۨۨۖۛ۫ۨۥ۫" : "ۛۛۥۘۨۛۘ۫ۤۖۚۜۛ۠ۤۙۥۖۘۦ۫ۤۢۧ۬ۨۢۢ۠ۡۜۘ۟ۤ۬۬۠ۧ۫ۖۗۥۜۤ";
                                        case 1725673389:
                                            str7 = "ۜۙۨۚۜۢۜ۠ۨۘۙۛ۬ۚۘۘۘۢۤۦۘۚۛۨۘ۫ۢۗۢۨۘۘۦ۬ۘۘۘ۠ۨ۟ۛ۫ۘۚۤۖ۟ۧۛۡۦ";
                                            break;
                                    }
                                }
                                break;
                            case 399524864:
                                str2 = "۫۫۫ۦۙۥۘۨ۟ۧۤۗۤۡۚۦۘۖۧۨ۠۫ۜۨۘۤۢۗ۬ۤۤۤ";
                                break;
                        }
                    }
                    str2 = "ۢۧۘۘۛۢۙۘۢۖۧۨۦۘۘۗۨۜ۬ۦۛۨۘۘۦۢۖۘ۠ۥۦ۫ۢ۬";
                    break;
                case -781743606:
                    k2.markOtherPopupShown(this.activity, l2.decrypt("cynU4A==\n", "G125jLviKaU=\n"), this.boundPopupId);
                    str2 = "ۤۨ۫ۨۡۗ۬ۥ۟۬ۗۖۛ۠۬۟ۦ۠ۗۗۡۨۚۤۛۙۨۘۧ۫";
                case -328540618:
                    String str9 = "ۢۥۦۘۛۦۤۙۗۤ۫ۘ۠ۢۗۜۖۤۨۡ۬ۛۛۚۜۙ۫۟ۤۖۜۥ۠ۧ۫ۗۡۘ۟ۦۛۜۢۧ۟ۛۗۚۤۤ۟ۥۡۖۚۢ";
                    while (true) {
                        switch (str9.hashCode() ^ 1375788170) {
                            case -1443869071:
                                str2 = "ۜ۠ۜ۠ۡۘۙۤۖۥۧۖۘۙۚۥۘۧۨۖ۟ۜ۟ۤۦۤۗۛۘۘۘۥۦۘۚۖۘۢ۫ۨۥۜۖۛۜۨ";
                                break;
                            case 33504538:
                                String str10 = "ۖۤ۟ۧۢۨۤۢۛۚۧ۫ۛۡۘۘۙۗۖۛۧۥ۫۟ۜ۠۫ۥۨۥۚۤۡۘۦۖ";
                                while (true) {
                                    switch (str10.hashCode() ^ 232339083) {
                                        case -1493623057:
                                            str10 = !this.boundPopupId.equals(str) ? "ۛۗۨۘ۫ۡۖۥ۫ۨۘۢ۫ۘۘ۬ۛۜۥۥ۠ۦۜۙۖۤۨۗۧۚۙۚۘۘۗۘ۫۟۠ۢ" : "۠۬ۛۘۥۧۘۧ۬ۡۖۥۡۗۢۧۖۨۧ۠ۦ۬ۙۙۨ۫ۥۘ۟۬ۜۘ۬۟ۜۘۖۨۧۘ۟ۚۜۘۧۗۜۦۜۨۚۥۥۘۚۥ۫ۘۦۙ";
                                        case 801118792:
                                            str9 = "ۜۙۤ۠ۙۖۘ۟ۤۢۘ۟ۘ۫ۛ۠ۢۖۗۗۚۨۘ۫ۥۖۘۚۖۗ۠۟ۤ";
                                            break;
                                        case 1764996398:
                                            str10 = "۠ۧۡۘۡۧۚۤۙۖۛۥۖۗۗۜ۠ۢۛۤۜۨۖ۬ۜۘۚۖۖۘۤۥۦۘۚ۟ۖۘۧۡۙ";
                                        case 2032870794:
                                            str9 = "ۧۛۡۘۥۚۥۘۙۗۤ۬ۙۘۘۗۡۨ۠ۥ۠ۤ۟ۥۗۖۘۚ۠ۢۨ۟ۡۘۤۚۦۘ۫ۦ۬";
                                            break;
                                    }
                                }
                                break;
                            case 936975112:
                                str9 = "ۨۡۢ۠ۨۜۘۖ۫ۨۘ۬ۥۜۦ۬ۧۗۨۚۛۡۖۘۧۥ۬ۛۨۨۖۘۘۧۚۖ۟ۥۧۨ۬ۖۙۛۡۜۜ۫۬ۚۨۙ۬ۡۨۗ";
                            case 1006930785:
                                break;
                        }
                    }
                    str2 = "۬ۡۙۥۙۜۘ۠۬ۥۛۤۙۜۜۘۤ۫ۦۘۘ۟ۥۘۘۤۗۥۗ۫۬ۢۛۧۖۥ۟ۡۘۘۖۡۖۦۘۧ۫ۗۨۡۡۜ۠ۦ۬۬ۡۦ";
                    break;
                case -15867445:
                    this.boundDialog.dismiss();
                    str2 = "ۜۥۡۘۖۛۦۘۡۙۡۘۜۨۤۧ۠ۘۥۨۘۡۗ۟ۙ۫ۜۚ۠ۚۢ۫۬";
                case 517538578:
                    k2.closedHtmlPopupIds.add(this.boundPopupId);
                    str2 = "ۡ۫ۚ۠۟ۛۥۗۦۤ۬۟۫ۗۦۦ۠ۖۥۥۘۘۥۚۖۥۧۨۛۖ۠ۧۢۚ۠ۤۦۘ";
                case 1294200164:
                    k2.logToFloatingWindow(l2.decrypt("iS/yLfuneAmyHp8IvKp7FKQfhUG/rXUKrhyfCLW3YAevGNpBv6txFeEV0BX7qXUSohM=\n", "wXu/YdvEFGY=\n"), l2.decrypt("mY7CdyzNjw==\n", "7u+wGUWj6Nc=\n"));
                    str2 = "ۧ۠ۡۘ۫ۦۥ۟ۡ۫ۗۜۖۥۤۦۘۙ۫ۘ۟ۛۖۘۢۧ۫۟ۡ۟ۧ۫ۜۘ";
                case 1296443860:
                    str2 = "ۖ۟۫۠ۘۨۘ۬ۦۜۘ۬ۤۖ۬ۚۨۘۚۧۛ۫ۦۘۢۗۧۜۚ۬۬۬ۗۦۨۥۖۖۘ۠ۧۤ۠ۚۜۘۧۤۛۖۦۘۘ";
                case 1301459275:
                    String str11 = "ۛۗۡۗ۬ۥۘۛۧۙۙۚۥۧۦۙ۠ۙۛ۫ۨ۬ۘۥ۠۠ۚۖۘۚۚۘۘۚۛۡۘۜۘۘ۟ۗ۫۫ۜ";
                    while (true) {
                        switch (str11.hashCode() ^ 2093161200) {
                            case -1127421572:
                                String str12 = "۬۫ۦۘ۟ۢ۬۟ۜۘ۫ۤۧۤۘ۫ۧۥۦۘ۟۟ۖۚۦۜۘۤ۠ۙۥۦۥۧۘۘ۫ۘۤۧۥۖۤۚۨۘۘۗۙۢۤۡۘۚ۬ۡۚ۟۟";
                                while (true) {
                                    switch (str12.hashCode() ^ 1812847162) {
                                        case -1453398973:
                                            str12 = map.get(this.boundPopupId) == this.boundWebView ? "ۘۘۡۖ۟ۧۙۜۜۢ۫ۖۘۛۚۡۨۗ۟ۥ۬ۨۥۢ۫ۨۥۗۧ۬ۘۧۚۢ۬ۡۢۢ۫۟ۡۥۙۢۡۘ۠ۦۨ" : "ۥ۟ۗۢۦۚۢۚۡۚۜۥۜۗۙۗۖۧۖۨۦۘۤۥۛۖۖ۬ۚ۠ۥۘۥۨۙ۫۠۬ۙۜ۟ۘۙۖۘ";
                                        case -763838029:
                                            str11 = "ۖۛ۬۟ۘۘۛۡ۫ۛۤۥ۠ۚۤۛۥۥۘۚ۬ۥۡۤ۠ۚۦۙۜ";
                                            break;
                                        case 1867007355:
                                            str12 = "۬ۘ۟ۘۜۗۡ۫ۦۘۤۢۦۖ۬ۡۘ۟ۚ۬ۥۚۛ۬۫ۡۡۚۖۨۛۨۘ۬ۚۡۧ۫ۢ۠ۜ۟ۢۛ۠ۘ۠ۚۥۦۡ";
                                        case 1992967848:
                                            str11 = "۫ۢۥۘۥۨۥۥ۬ۖۘ۬۠ۚۨۥۚۧۧۦۘ۟ۧۚ۠ۘۖۘ۟ۧ۟ۖۦۘۘۗۗۘۘۙۘۛۤۤۨۜۘ۠ۙۘۢۘۗۜۘ";
                                            break;
                                    }
                                }
                                break;
                            case 676249562:
                                str2 = "ۤۡۡ۬۠ۥۚۘۤۜۡۘۘۤ۫ۖۘۢۖ۟ۖۗۛ۬ۜۖ۟ۘ۟۠۫ۗۜۥۖۜ۠۫ۘۜ۬ۤ۫ۨ۫ۛۘۘۨۖۖۘۙۨۤۗۡۡۘ";
                                continue;
                            case 856459195:
                                str2 = "ۖۨۧۘۖۚۦ۟ۧۥۜ۠۫ۘۛۢۛۧۨۘۢۤۙۤۛۡۙۡۡۘ۠ۙ۟۫ۧ۠ۘۥۘۚ۬ۖ۫ۥۥ";
                                continue;
                            case 2108054145:
                                str11 = "ۛۥ۫ۢۛۨۨۜ۬ۢۙۧۤۜۜۦۜ۫ۗۜ۬ۡۨۙۡۨۡۦۘ";
                                break;
                        }
                    }
                    break;
                case 1580021712:
                    String str13 = "ۢۖۜ۠ۨۦۤۨۡۨ۠۟ۜ۟۫ۜ۫ۨۖۨۜۡۦۥۘۡۨۛۖۖۜۡۘۥۘۘ۟۟ۤۨۛ۟ۗۖۧۥۨۘۘۤۦۨۤۖۥۧۥۘ";
                    while (true) {
                        switch (str13.hashCode() ^ (-2054034674)) {
                            case -458610095:
                                String str14 = "۟ۘۡۧۦۧۘۤ۟ۡۤ۬ۜۨۖۥۨ۠ۜۘۡۦۨۢۗۦۘ۫۬ۤۥۥۨۨۢۤۙۖۤ";
                                while (true) {
                                    switch (str14.hashCode() ^ 1488014174) {
                                        case -1576116511:
                                            str14 = "۟ۚۦۘۜۧۨۗۙۥ۫ۗۙۦۜۘۥۧۚۖۡۜۦۜۗۤۜ۫ۡۡۧۘ۠ۨۖۘۛ۟ۥۘۘۤۛۖۚ۬ۤۙۦۘۖ";
                                        case -644640433:
                                            str13 = "ۚۤۘۘۢۘۡ۠ۖۗۥۨۥۘۤۨۦ۫۬ۛۦ۬ۖ۫ۢۜۡۨۦۘۚۢۚ۠۫۫ۧۘۖۥۘ۬ۙۥۗۡۥۘ۬ۗۤ۠ۚۥۘۙۨۘۘ";
                                            break;
                                        case -368692102:
                                            str13 = "ۗۡ۠۟ۡۨۧۛۦۘۦۘ۫ۥۢۘۛۗۜ۠ۥۘۚۨۤۛۗۨۘ۟ۖۧۘۗۡ۟ۖۦ۟";
                                            break;
                                        case 2018286393:
                                            str14 = this.boundWebView != null ? "ۜ۫ۡۘۧ۠ۥۧۢۧۡۦۡۘ۟ۨۘۘۡۢۨۡ۟ۘۘۤۙ۟ۧ۬ۨۘۤۤۤ" : "۫ۘۢ۬ۜۧۘۚ۬ۜۘۡ۫۬ۥۤۧ۟ۤۨۘ۫ۥ۬۟ۗۘۙۜۖۘۗۘۖۘۨ۠ۘۤۘۤ۠ۜۨۖۘۙۛۘۘۦۛۜۘ۠ۡۧۚ۬۠";
                                    }
                                }
                                break;
                            case -417153641:
                                break;
                            case -335537346:
                                str2 = "ۘۘۖ۫۬ۦۘۙۜۤۛ۬ۙ۬ۦۘۡۡۙۦۗۦۡ۬ۥۧۤۘ۟۠ۧۖۜ۠ۜۦ";
                                break;
                            case -329584224:
                                str13 = "ۖۜ۬۟ۛۥۘۗۥۛۚ۠ۤۤ۟۟ۤۜۤۘۖۘ۬ۨۜۘۙۡ۠ۡۦۢ۟۟۠ۨۛۚ";
                        }
                    }
                    str2 = "ۢۧۘۘۛۢۙۘۢۖۧۨۦۘۘۗۨۜ۬ۦۛۨۘۘۦۢۖۘ۠ۥۦ۫ۢ۬";
                    break;
                case 2009293698:
                    String str15 = "ۧۢۘۗۡۖۚۨۙ۟ۢۨۦۡۘ۟۠ۘۧ۬ۨۘۙۥۢۡۤۥۘۡۗۥۛ۫ۜۨۤۜۨ۟ۗۥۦ";
                    while (true) {
                        switch (str15.hashCode() ^ (-711134130)) {
                            case -251004553:
                                str15 = "۟ۦۜۘۜ۟ۜۘۚۨۡۘۖۧۥۚۜۧۗۗۨ۠ۢۨ۬ۧۥۧۜۥۗ۟ۡۘ۫۫ۧۖۢۖ";
                            case 1124630928:
                                str2 = "ۤ۬ۡۢۦۥۘۥۙۜۘۜ۠ۚ۟ۛۡۥۙۦۖۘ۟ۡۚۥۘۛۗ۟ۡ۬ۨۜ۠ۨۦۦ۬ۥۗۖۘۛۖۡۨۧ۟ۛۢ";
                                break;
                            case 1694705837:
                                break;
                            case 2030978684:
                                String str16 = "ۙۖۨۘۘ۫ۛ۟ۨۨ۠۠ۜۘۧۘۦۙۗۖ۬ۥۘ۠ۗۗۡۢۡ۬ۤۥۘۘۖ۠ۜ۠ۖۤۘۨۘۜۘۘ۫ۥ۫ۘۗ۠";
                                while (true) {
                                    switch (str16.hashCode() ^ (-2035357354)) {
                                        case -648078201:
                                            str16 = this.boundDialog != null ? "ۡ۠ۢۜۥۙۗۜۘۡۚۘۘۜ۟ۘۘۛۦۚۤ۟ۙۗۢۨۚ۟ۢۨۚۘۘ" : "ۢ۫ۤۛۨۛ۫ۜۛۥۧۚۜۧۦۘۘۦۖۘۗ۟ۚۦۙۥۘۘۡۢۘۢۖۘۢۚ۬ۢ۫ۗۢ۫ۙۡۜۛۘۙۤۙ۫ۥۙۗ۟ۢ۟ۜ";
                                        case 141278261:
                                            str15 = "ۨۧۖۘۘۚۛۛۙۜۘ۬ۦۜ۟ۚۚۜۖۘۖۙۡۘۛۜۥۢۦ۫ۙۖۤۡ۟ۖ۫ۙۤ";
                                            break;
                                        case 1537849077:
                                            str16 = "ۙ۠ۧ۫۠ۘۘ۠ۜۢۧۢۤۛ۬ۧۘۖۜۧۖۧۡۖۘۧۛۦ۠ۖۢۦۢۙۡۨۛۧۤۘ۫۬ۨۘۧ۬ۜۘۚ۫ۢۥۜۚۡ۟ۤ";
                                        case 2127693093:
                                            str15 = "ۖۛۛۛ۫ۜۛۤۦۘۘۥۡۨۥۘۘۗۚ۫۫ۚۦۚۗ۟ۤۖۡۛۗۚۗۤۧۘۡۤ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    str2 = "ۢۧۘۘۛۢۙۘۢۖۧۨۦۘۘۗۨۜ۬ۦۛۨۘۘۦۢۖۘ۠ۥۦ۫ۢ۬";
                    break;
                case 2063346330:
                    str2 = "ۙۥ۬ۖۙۘۘۘ۫ۢ۬ۨۛۘۖۢۚۜۦۜۤۨ۟۫ۦۖۡۘۖۗۤ۟ۥۧۥۗ۟";
                case 2091755596:
                    String str17 = "ۙۚ۟ۢۜ۟ۤۡۖۘ۠ۡۥۘۢۨۢۗۘۧۘۙۤۨۢ۬ۖۛۙۡۘۨ۟ۥۘۧۛۚۨۧۙۜ۬ۥۢ۬ۤۗۘۚۛ۟ۛ۟ۗۤۜۧۘ";
                    while (true) {
                        switch (str17.hashCode() ^ (-1376469255)) {
                            case -1787018681:
                                str2 = "ۜۥۡۘۖۛۦۘۡۙۡۘۜۨۤۧ۠ۘۥۨۘۡۗ۟ۙ۫ۜۚ۠ۚۢ۫۬";
                                continue;
                            case -581248893:
                                String str18 = "ۛۛۜۘۤۚۧۜ۫ۖۨۦۖۢۢۦۥۖۗ۟ۛۤۧۥۡۨ۟۫۬ۚۚ۬ۖۚۨ۫ۜۘۗۥۘۨۤۘۘ۠۬ۨۘ۬ۖۗ۬ۤۗۡ۟ۖ";
                                while (true) {
                                    switch (str18.hashCode() ^ (-1306831145)) {
                                        case -1764453650:
                                            str17 = "ۘۚ۫ۡ۟ۘۘۚۜۡۙۥۘۨ۬ۗۡۖۘۨۧ۠ۜ۟ۛۘۡۘۤۖۡۘۗۗۨۘ۬ۚۥۦۤۚۤۛۙ";
                                            break;
                                        case 437654989:
                                            str18 = this.boundDialog.isShowing() ? "ۛۧۖۘ۠ۧۚۜ۠ۖ۫ۛۨۘۗۜۘۚ۠ۛۖۥ۟ۚ۫ۤۖ۬۠ۘۨۢ" : "ۗۤ۬ۖۙۥ۫ۜ۫ۚ۠ۦ۠ۙۤۡۚۨۛ۬۟ۜۖ۟ۘۦۛۜۡۨۘۨ۬ۖۘۨ۠ۜۘۨ۬ۖۘۧۗۨۘۘۙۙۧۖۨ";
                                        case 1884155469:
                                            str18 = "۫ۨ۬۟ۘۧۙۗۜۗۧۡۜۢۡۘۘ۟ۜ۫۟ۡۘۡۗ۬ۛۙۚۨۢ۠۬۬۠ۨۙۡۘ";
                                        case 1888143272:
                                            str17 = "ۥۖۚۧۥۥۘۡۦۚۗۨۜۘۛۧۢۘۤ۫ۧۤۨۘ۫ۢۡۘۚۧۤۨۡۘۘ۟ۡۡۢۦۘۘ۟ۛ۠ۙۡۧۘ";
                                            break;
                                    }
                                }
                                break;
                            case 887445683:
                                str2 = "ۛۛۥۘۖۖۖۧ۫ۦۘۨۨ۠ۤۖۥۘۨۤۨۛۦۙ۫۫ۖۢۡ۟۠ۢۢ";
                                continue;
                            case 1794135913:
                                str17 = "ۢۚۥۡۥۡۨۙۢ۫ۚۤ۠۠۬۫ۖۛۦ۫ۨۘۢۜۘۗۤۖ۠ۡۖۨۦۡۥ۠۟۫ۖۦۧۤۜۜۛۤ۫ۖۘ";
                                break;
                        }
                    }
                    break;
            }
            return;
        }
    }

    public static /* synthetic */ void d(JsInterface jsInterface) {
        String str = "ۡۨۦۘۚۦۢۚۜ۟۟ۛۧۢۛۜۘۨ۫۬۬۠ۡۗۤۙۧۦۘۧۚۡ";
        while (true) {
            switch ((((str.hashCode() ^ 102) ^ 792) ^ 88) ^ 179139011) {
                case -1975062760:
                    return;
                case 656880184:
                    str = "۠ۘۡۘۘ۟ۗ۟ۜۥۗۙۚۗۦۦۘۦۗۤ۫ۦۤ۠ۘۘۘۛۗۡۢۜۗۘۜۘۗۥۘ";
                    break;
                case 2121683147:
                    jsInterface.lambda$close$1();
                    str = "ۡۘ۠ۛۛۚ۠ۙۘۜۡۗۡۜۖۥۢۗۨۧۘۧ۫۫ۦۢۥۘۚۦۨۘۧۚ۟ۨۘ۟ۜ۫ۙۖۨ";
                    break;
            }
        }
    }

    public static /* synthetic */ void e(JsInterface jsInterface, String str, JSONObject jSONObject, String str2) {
        String str3 = "۟ۛ۬ۨۧۗۥۖۦۘۢۨۚۗۦۨۜۢۧ۟ۧ۬ۥۢۧۙۦۘۦۜۘۨۤۘۘۧۚ۬ۚۧۚۚۨۗ";
        while (true) {
            switch ((((str3.hashCode() ^ 955) ^ 767) ^ 524) ^ (-1726557812)) {
                case -1530700926:
                    str3 = "ۗۥۗۜ۠ۜۦۜ۬۠ۖۘۨۙ۟ۗۢۦۖۛۛۨۛۖۨۡۘۚۦۜۨۢ۬ۖۙ۫ۗ۟۠ۖۛۢۚۨۧۡۘۡۧۜۖۤۡۢ";
                    break;
                case -1270315615:
                    str3 = "ۙۛۘۥ۟ۡ۬ۥۛ۠ۚۚۖۜۘۤۛ۫۫۟ۡۥ۬ۙۙ۟۟ۤۚۖۥۘۦۛ۫ۢۛ۠ۘ۫ۧۘۛۙۡ۟۫ۜۘ";
                    break;
                case 781030578:
                    return;
                case 950084607:
                    jsInterface.lambda$http$6(str, jSONObject, str2);
                    str3 = "ۥ۬ۗۙۙۗۛۖۤۜۨۚ۟ۧ۟ۥۚۛۡۙۘۘۗ۫ۤۥۤۧۤۢۚ۬ۢۗۙۘۜ۠ۖۡۧۘۨۘۧۨۘۙۘ۬ۜۙ۟۠ۖۥۘ";
                    break;
                case 1081270034:
                    str3 = "ۙۛۚۜۨۨ۬ۤۥ۟ۘۜۘ۟ۙۜۘۛۤۖۡۢۘۘۥ۠ۥۢ۬ۥۜۥۧۘۧۘۡۥۦۘۗۚۥۘۥۚۘۘ۟۠ۚ۫ۜۤۜ۬ۦ۬ۨۜ";
                    break;
                case 1628898927:
                    str3 = "ۧۧۘۘۢۦۧۘۗ۫ۛۗۜۘۧۨۦ۬ۦۛ۬۫ۖۘۚۨۦۘۨ۬۟ۨۡۨۘۧۢۙۛۚۜ";
                    break;
            }
        }
    }

    public static /* synthetic */ void f(JsInterface jsInterface, String str) {
        String str2 = "۬ۧۥۦۘۥۘۚۢۦۘ۟ۚۤۥۜۥۨۙۘۘۘۜ۬ۙۤۡۨۛۘۥۨۜۘۘۖۦۘۖۘۚ۫۠ۖۜۖۜۘ۟ۢۤۚۛۨۥ۫ۜۥۖۙ";
        while (true) {
            switch ((((str2.hashCode() ^ 898) ^ 633) ^ 529) ^ (-1027261785)) {
                case -811056607:
                    return;
                case 885370729:
                    jsInterface.lambda$runJS$16(str);
                    str2 = "ۚۧ۠ۚۧۦۘۙ۫۬ۜۙۖۘۘۨۦۘۤۗۥۘ۬ۡۦۥۙ۬ۗ۫ۘۧۤۗ";
                    break;
                case 999534699:
                    str2 = "ۡ۫ۛۙۚۡۘۨۡۜۗۡۡۛۖۥۘۢۘۙۙۦۥۘۘ۬ۤ۫ۧۚ۬ۦۘۘۛۨۥۡ۫۫ۗ۫ۖۧ۬ۢ";
                    break;
                case 2071678931:
                    str2 = "ۛۚۜۘۜ۬۟ۘ۟۟۬ۗ۟ۛۨۨ۟ۛۜۡۚۚۙ۟ۚۦۧۡۨ۫ۡ";
                    break;
            }
        }
    }

    public static /* synthetic */ void g(JsInterface jsInterface, String str) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        String str2 = "ۗۡۥۘ۬ۙۢۖ۟ۧۤۘۥ۟ۡۥۘ۠ۗ۬ۜ۟ۨۘ۫ۚ۬۬۠ۜۛۡ۬";
        while (true) {
            switch ((((str2.hashCode() ^ 767) ^ 196) ^ StatusLine.HTTP_PERM_REDIRECT) ^ (-706326528)) {
                case -676299993:
                    jsInterface.lambda$playMusic$12(str);
                    str2 = "ۗۛۧۥۦۗۖۡۜ۠۫ۡۢۚۤۧۗۜۢۨۨۗ۫ۙ۫ۥۙۙۘۦ۬۬۟ۧۡۛۛۡۘۡۥ۬۟۫ۘۘۗ۟۬ۖ۫ۥۚۘۡ";
                    break;
                case -528692100:
                    str2 = "ۜۜۜۜۜۡۛۘ۠ۨۦۜ۫ۧۘۘ۠ۤ۫ۤۢۢۙ۟ۗۤۡۢۗ۠۟";
                    break;
                case 1360993055:
                    return;
                case 1935241126:
                    str2 = "ۜۜۢ۫ۚۙ۟۟ۡۚۢۜۤۘ۟۬ۥۘ۬ۙۖۘ۠ۙۨۘ۬۬ۙ۬۠۟ۥۨۧۨۙۤ";
                    break;
            }
        }
    }

    public static /* synthetic */ void h(JsInterface jsInterface, String str, boolean z, String str2, String str3) {
        String str4 = "۫ۘۢۗ۫ۖۘۨۦۙۤۢۖ۠۠ۥۚ۠ۡۙۖۘۤۥۚۧۘۜۘۙۖۖۗ۫۬ۙ۬ۢۘۨ۫ۥۦ۟ۙۢۘۜۖۜۙۘۧۘۛۧۘ";
        while (true) {
            switch ((((str4.hashCode() ^ 622) ^ 370) ^ 732) ^ 1643356506) {
                case -2084657645:
                    return;
                case -1784952871:
                    str4 = "ۦۨۗۚۛۥۘۛۨۤۜ۠۠ۨۥۖۘۛۨۘۧ۟۠ۥۦۜۘۧۨۥۘ۟ۥۚ۫ۥ۬۬۬";
                    break;
                case -1045890619:
                    str4 = "ۥۥۡۛ۬ۗۤ۟ۤۦۤۚۙۙۘۘۡ۫ۜۘۦۥۥۘۗ۫ۜۧۡۥۘۢۡۧۙ۟ۘۢۧۧ";
                    break;
                case -288776061:
                    str4 = "۬ۖۗۛۘۖ۫ۧۗ۟ۖۨۘۚۧۤ۟ۡۜۥۚۗ۫ۤۚ۫ۖۧۘۨۙۨ۫ۚۢۗۚۙۖۦۦ۬ۦۦۘ";
                    break;
                case 256479497:
                    str4 = "۟ۦۛ۠ۚۡۨۗۤۤۖۨۤۛۗ۫۠۠ۜ۟ۢۢ۠ۨۦۖۢۛۥۥۡۘۚۥۥۤۢۥۘۢۗۜۛ۟ۜۘۦ۫ۖۘ";
                    break;
                case 721498999:
                    str4 = "۠ۛۥۨ۟ۢ۠ۚۡۘۤ۠ۙ۫ۦۡۘۚۛۖۧۧ۬ۤۢۘۥۨۘۙ۟ۥۢۨ۟۬۫۫";
                    break;
                case 866711814:
                    jsInterface.lambda$verifyCardKey$2(str, z, str2, str3);
                    str4 = "۟۬ۦۘ۫۟۬ۡ۠ۨۥ۬ۛۢۡۘۗۚۗۢۘۜۢۜۥۨۙ۟ۖۖ۟۬ۙۘۘۖۥۚ";
                    break;
            }
        }
    }

    public static /* synthetic */ void i(JsInterface jsInterface, String str, String str2, String str3, String str4, String str5, String str6) throws JSONException {
        String str7 = "ۨۛۥۛۢۚۙۚۗۖۨۢۤۢۚ۬ۖۡۘۜۡۜۘۜۡۘۚۤۘۢۜ۫ۨۦۡۘۛۖۦۤۡ۬ۦۛۘۖۘۘۘ۫ۘۨ";
        while (true) {
            switch ((((str7.hashCode() ^ 216) ^ 857) ^ 451) ^ (-877797678)) {
                case -1783588127:
                    str7 = "۠ۖۦۗۧۨۘۛۥۘۘۘۡۘۛۧۛۘۤۧۚۢۖ۠ۡ۫ۗۘۥۡۗۜۡۨۡۘۢۨۜۘۥۖ۠ۢ۠ۧۦۚۥۘۗ۫ۦۘۘۙۥۘۜۦۘۘ";
                    break;
                case -1759064397:
                    str7 = "ۥ۬ۜۘ۟ۤۥۨۥۗ۟ۥ۟ۤۨۧۤۛۗۙۤۖۘۜۛۜۛ۬۬ۥۦۡۘ۫۬ۚۗ۫ۚۢۗۘ۬۫ۢۚۥۧۜۥۧ";
                    break;
                case -840083662:
                    str7 = "ۛۘ۫ۢۢۧۤ۠ۗۜۥ۠۠ۛۙۘۦ۠ۡ۬ۗۧۤ۠ۗ۟ۚۛۤۜۘۚۚۡۘۥ۟ۥۘۢۖۜۚۖۦۘۧ۠ۜۗ۟ۦۘ";
                    break;
                case 7066253:
                    str7 = "ۛۦۧۘۚۙۜ۠ۜۜۘۜ۠ۥۘۖۚۨۘۚ۫ۡۖۛ۟ۙۢۛۘۤۛۗ۬ۢۥۛۦۘۤۚ۬ۨ۠ۖۢۤۥۘۙۥۚۤۦ۬";
                    break;
                case 436495195:
                    str7 = "ۜۘۘۘۜۙۦۨۦۡۘۡۚ۬ۜۨۤۗۚۨۗۥۨۥۧۡ۠ۤۡۨ۫ۜۘۖۘۙۗۦۘۗۗۦۘ۬۠ۖۘ";
                    break;
                case 612029400:
                    return;
                case 1392617202:
                    str7 = "ۤۧۙ۫ۡ۟ۙۡۦۚۦۜۘۗۡۨۡ۠ۙۜۤ۠ۛۤۦۘ۬ۗۨۖ۫ۥ۫۠ۦۛۘۢۢۗۜۘۜ۬ۘۘۦۤۨۢ۫ۘ";
                    break;
                case 1701858147:
                    jsInterface.lambda$http$7(str, str2, str3, str4, str5, str6);
                    str7 = "ۦۡۘۘۦۤۘ۬ۧۙ۬ۧۘۘ۫ۢۦۘۨۤۡ۬۠ۜۘ۬ۗۢۖۤۨۙۚۜۢۗۖۛ۠۬ۗۨۘۘۥۢۡۘۦۜۡۘۙۚۚۤۙۤۚۖۘ";
                    break;
                case 2081798164:
                    str7 = "ۜۜۘۘ۫ۦ۫ۚۤ۫ۗۨۤۥۘۛۡۡۜۨ۫ۤۡۛۡۘ۟ۙۡۘ۫۠ۖۘۧ۬ۘۘۚۜۘ۬ۢۥۘ۫ۡۖۘ۟ۖۙۚۡۘ";
                    break;
            }
        }
    }

    private boolean isActivePopup(String str) {
        String str2 = null;
        String str3 = "ۥۖ۫۠ۧۤۧۚۥۢ۫ۧۨۗۦۘۥ۬ۦۘۙۤ۠ۚۙ۟ۖۗۚ۠ۖۡۘۙ۫ۛۜۡۨۘۧ۟ۘۗ";
        boolean z = false;
        boolean z2 = false;
        while (true) {
            switch ((((str3.hashCode() ^ 524) ^ 716) ^ 812) ^ 2011925161) {
                case -1821352591:
                    str3 = "ۗۦۘۘ۟ۤۦ۫ۢۦۘۡ۟ۜۙۜۡ۠ۢۘۘۗۤۛۨۦۘ۬۫ۗۥۨ۬ۤۢۚۜۥۨۨۧۦۘۛۙ";
                    continue;
                case -1460274444:
                    return z;
                case -1392717143:
                    String str4 = "۠۬ۖ۠ۛۗۘۤۜۘ۬۠ۜۘۛۡۡۘۧۧۡۘۚۨۜۘۧ۬ۖۘۖۦۦۘۖۡۢ۬ۘۢ۟ۖۙ";
                    while (true) {
                        switch (str4.hashCode() ^ (-1826100196)) {
                            case 77369902:
                                String str5 = "ۧۨ۠ۦۗۡۥۙۢۨ۫ۖۚۖۘۨۦۚ۟ۚ۠ۡۜۦۤۛۨۛۚۘۘ";
                                while (true) {
                                    switch (str5.hashCode() ^ (-1179574377)) {
                                        case -1014230872:
                                            str5 = "ۧۚۘۘ۬ۜۜۘۡۗۘ۬ۧۖۘۤۦۡۧۖ۟ۚۤۜۦۨۡۡۡۗۨۦۤۘۤۖۨۡۛ";
                                        case -885017998:
                                            str4 = "ۚۧۥۘۜ۫۬ۘۧۗۜۖۘۤ۬ۦۙۘۧۨۧۦۘۗ۠ۙۥۡۛۥ۟۫ۘۧۘۡ۬ۥۘۢۘۨۛ۫ۜ";
                                            break;
                                        case -269377277:
                                            str5 = !this.disposed ? "ۡ۫ۚۨۤۨۡۖۢۗۘۨۚۡۖۗۚۥۘۛۡ۟۫ۨۡۤۧۥۘ۠ۤۖۘ۠ۜۨۘۢ۠۟" : "ۧۤۧۡۨۗ۫ۤ۫ۡۢۗ۟۫ۢۦ۫۟ۨ۫ۖ۠۫ۖۘۘۜۥۘۙۚۨ";
                                        case 1448034857:
                                            str4 = "ۙۤۖۡۡۜۗۨ۠ۧۢۗۙۙۙۥ۟ۧ۫ۡۢۙۗ۠۫ۜۙۦۨۚۤۘ۫ۡ۠۟ۚ۠ۦۢ۫";
                                            break;
                                    }
                                }
                                break;
                            case 335029361:
                                str4 = "۟ۢۖۘۧۡۖۤۤۛۧۢۖۥۧۡۘۥۖ۠ۤ۬ۡۗۜۘۛ۟ۗ۬ۥۧۡۜۡۘۥۛۢ";
                            case 629518452:
                                break;
                            case 1579269882:
                                str3 = "ۢۢۖۤۧۚۡ۟۠ۜۢۘۥ۠۠ۡۚۗ۫ۚۗۤ۬۫ۥۡۜۜۡ۠ۨۢۚ۫";
                                break;
                        }
                    }
                    break;
                case -1387657188:
                    str3 = "ۘۢۖۘۦ۬ۦۦۙۘۘۦۥۘۙ۠ۙۨۙۦ۫۫۟ۛۚ۟ۗۙۡۘ۫ۜۚۖۚۘۖۘ۟ۖۥۘ۟ۢۖۘ";
                    z = z2;
                    continue;
                case -1309738439:
                    z2 = true;
                    str3 = "ۢۨۚ۫۬ۗۨۛۦۦ۟ۨۗ۟ۥۘ۫ۙۙ۬۬ۥۘۛۙ۫ۛۙۘۙۖۘۘۥۖ۬ۛ۠ۨۘۗۖۚۘۤۜۤۛ۟ۖۥۡۘ۬ۢۖۘۢۙ۠";
                    continue;
                case -1293490905:
                    str2 = this.boundPopupId;
                    str3 = "ۤۖۗۤۡۨۗۗ۫ۦۜ۫ۤۦۥ۠ۧۥۘۛ۟ۚۨۢۥۧۤ۟ۜۨۘۧۚۘۘۧۜۘۢۦۡۚ۬ۨۘۘۗۜۘۗۜۧ۫ۜۤ۟ۘ";
                    continue;
                case -826109211:
                    String str6 = "۟ۢۦۘۚ۫ۜۘ۬ۖۦۘۨۙۖۘ۠ۜۖۘۧۜۨۘۘ۠ۘۘۘ۬ۦۥۥۘۖۜۘ۠ۜ۟ۥۖۥۘ۬ۘۖ";
                    while (true) {
                        switch (str6.hashCode() ^ 1270403542) {
                            case -2092230084:
                                break;
                            case -1766166306:
                                str3 = "ۤۗۨۘۛۦ۟ۡ۫ۡۘ۫ۦۡۡۘۜۘۤۨۨۘۙ۠ۨۦ۟ۡۢۜۨۥ۫";
                                break;
                            case -1503547545:
                                str6 = "ۚۜۦۘۜ۠ۛ۬ۗ۫ۙۖۜ۫ۤ۫ۖ۫ۢۤۡۢۗ۬ۨۖۡۤۧ۬۬ۧۦۦۘ۫ۨۡۘۚۨۤۖۧۢۡۢۦۤ۠ۙۦۥۢۧۦۡۘ";
                            case 1995403173:
                                String str7 = "ۤۛۧۙۘۨ۟ۚۜ۟۟ۙۥۥۘۡۤۘۡۚ۠ۨۨۜۨۨۗ۬ۦۚۨۘۤۗۧ۬ۦ۠۟ۦۧۘ";
                                while (true) {
                                    switch (str7.hashCode() ^ (-1757883779)) {
                                        case -2009128935:
                                            str6 = "ۘ۠ۦۥۦۛۧ۠۬ۛۢۧۜ۠۟ۗ۬ۡۚ۫ۦ۠ۢۥۘۥۛۧ۫۟ۤ۫ۜۗۤۛۧۛۛۘۘۡۗۖۘ";
                                            break;
                                        case 37838930:
                                            str6 = "ۢۖۘۘ۟ۤۨۘۡۜۘۖۚۧۧۤۤۛۦۤۢ۠ۨۘۜۛۜۘۖ۬ۗۨ۬ۢۘۥۧ۠ۥ۫ۡۖۘۢ۟ۜۚۘۥۙۛۨۘ";
                                            break;
                                        case 1317121744:
                                            str7 = "۟ۘ۫ۘۧ۠۟ۤۙۡۡ۫ۛۗۚ۟ۘۧۘۨۜ۟ۧۛۗۜ۫ۤۦ۠ۦۘۙۡۥۘۙۨۥ";
                                        case 1898401627:
                                            str7 = str2 != null ? "۬۟ۘۘۡۙۦۨۦۚۢۥۥۘۤۧۨۙۖ۬ۖۤ۟۫ۥۘۧۖ۟۫۟ۡۘ" : "ۜۖۖۚ۫ۖۖۦۥۘۘۨ۟۟ۡ۫ۛۚۡۗۢۘۘۦ۬ۜۘۖۨۨ۫۫۟ۛۡ۫ۡۚۛ";
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case -750710994:
                    str3 = "۬۠ۢۙ۫ۘ۠۟ۜۥۚۚۡۧ۠۠۠ۢ۫ۤۚ۫۠ۖۦۘۘ۠۠ۥۘ";
                    continue;
                case -514350857:
                    String str8 = "ۤۡۘۜۙۘۚۥۖۦۙۦۘۘۨۙ۫۫ۛۡۘۜۜۥۖۘ۬ۘۦۘ۠ۤ۠۫ۙۡۘۚۡۜۘ";
                    while (true) {
                        switch (str8.hashCode() ^ (-1841169235)) {
                            case 65350317:
                                str3 = "ۢۘۚ۬ۙۘۜۖ۫ۦۦۨ۬۬ۢۢۘۗ۟۠ۜۜۙۨۘ۠ۛۥۨۧۘۧۘ۠ۚۡۘ";
                                break;
                            case 596166549:
                                String str9 = "ۖ۫ۖۘۡۖۛۚۛۗۨۛۘ۫ۦ۫ۤۙۨۖۛۤ۟ۡۦۚۖۦۥۗۚ۫ۤۡۤۘۘۡ۟ۦۥۥ۟ۛ۟ۥۘۥۦۘ";
                                while (true) {
                                    switch (str9.hashCode() ^ (-377886737)) {
                                        case -1490201019:
                                            str8 = "ۤ۫ۘۛ۬۬ۖۦ۠۟ۨۧۗۧ۬ۗۨۧۙۧۨۘۖۘۜۘ۠ۖۛۚ۟۬ۗۜۧۜۗۧ";
                                            break;
                                        case -1284405238:
                                            str8 = "۬۫ۜۘۢۥ۫۫ۘ۟ۨۥۥۛۤۢۘ۫ۛۤۙ۫۬۫ۚۚۙۜ۬ۤ۠ۜۙ۫۟ۚۥۘۦ۬ۛۚۘ۠۠ۜۗۧۚۗ";
                                            break;
                                        case -258704524:
                                            str9 = "ۜۦۛۙۜۦۥۘۖۘۙۡۨۘۗۤۥۘ۫ۚۨۘۜ۫ۢۨۡۦۨۘ۟ۚ۬ۜۘۖ۠ۗۜۢۡۘ";
                                        case 1669533606:
                                            str9 = str2.equals(str) ? "ۡۖۨۘۗ۬ۛۚۙۜۘۛۦۛۢۛۜۘۖۛۨۘۨۘ۠ۢۡۚۦۙۘۦۨۘۖۖۜۘۖ۟ۥۘۨ۟ۡۨۖ۟۫ۥۦۘۨۧۡۘۜۡ۬۟۬ۖۘ" : "ۧۗۖۛ۬ۖۘۢۡۚۙۥۖ۫ۨۤۗۛۜۘۚۛۡ۟ۛۨۘۨۨۗۛ۟۬ۡۦۜۘۥۨۦۘ۬ۢ";
                                    }
                                }
                                break;
                            case 739335517:
                                str8 = "ۥۧۘ۟ۘۗۗۦۜۖۨۘۘ۟ۘۤ۠ۤ۠ۨۘۘۥۜۨۨۖ۠ۖ۬ۜۘۗ۬ۗۧۤ۬";
                            case 1940262039:
                                break;
                        }
                    }
                    break;
                case -209419136:
                    String str10 = "ۥۘۤ۟۟ۥۧۗۦۘۘۥۘۘۦۥۡۦۤ۫ۤۛ۠ۡۥۥ۟ۢۦۘۤ";
                    while (true) {
                        switch (str10.hashCode() ^ (-26014355)) {
                            case -1399621308:
                                String str11 = "۠۬۟ۦ۬ۗۚ۠ۡۘۚۛۦۛۖۘۡ۫ۗۡ۫ۚۡۤ۫۫ۚۧۘۡۗۖۨۡۘ۟ۢۘۙ۬ۙ۫ۧۧ";
                                while (true) {
                                    switch (str11.hashCode() ^ (-1799562528)) {
                                        case -1945894659:
                                            str11 = "ۢۗۗ۫ۦ۠ۨۙ۬ۗۤۥۘۤۡۡۘۗۖۡۘ۟ۧۖۘ۠ۛۚۨۛۦۗۡۘۜۖۘۤۦ۟";
                                        case -1651711442:
                                            str10 = "ۦۧۢۨۙۦۙ۠ۦۘۙ۫ۧۢۗ۟ۤ۟ۥۖۜۥۢۤۗۙۡۖۘۧۗۦۘۘ۫ۜۘ۬ۡۧۚۡۖۛۛۥۘ۠ۨ۟ۢ۫ۦ";
                                            break;
                                        case -1119959283:
                                            str10 = "ۢ۬ۖۘ۠ۙۦۘۨۙۨۘۗۖۥ۟ۢۤۥۧ۬۬ۤۖۘ۬ۘۨۖۥۖۘۤ۫ۢ";
                                            break;
                                        case 1179680745:
                                            str11 = this.boundWebView != null ? "ۡۦ۬ۚۧ۫ۖ۟ۖۘۚۗۚۚ۫۠ۢ۬ۛۛ۬ۦۚۧ۠۠ۛۚۗ۟ۖ۠ۛۡۢ۠ۘۘۡۤۘۘ" : "۠ۘ۫۠ۨۖۘۦ۟ۥ۟۟ۧۘ۫۬ۢۘۦۛۥۨۦۦۜۘ۟ۖۙ۫ۦۡۘۥۛۘۘۚۜۥۢۧۚۚۚ۟";
                                    }
                                }
                                break;
                            case 332693653:
                                str3 = "۠ۗۥۘۧۛۗۗۤ۟ۤ۠ۜۘۙ۟ۦۜۤۗۡۢۢۦۘۘۨۢۦۘۗ۬ۦۦۤۡۘ۠ۦۦۜ۟ۨۥۡۘ";
                                break;
                            case 1205340402:
                                str10 = "۬ۤۧۦۧۨۘۖۛۖۘ۠ۦۚۛۨۛۥۡۖ۠ۧۖۘۘۙۦۘۡۘۨۘۧۧۖۡۢۗۢۦۘ";
                            case 1796623810:
                                break;
                        }
                    }
                    break;
                case 6730481:
                    String str12 = "ۦۗۦۢۛ۫ۨ۫ۘۘۖۦۖۘ۫ۙۥۜۨۗۥۘ۫ۖۚ۫ۢ۠۠۟ۦ۫۠۠ۖۡ۟ۥۘ۬ۘۘۥۛ۠ۛۘ۬ۡۤۦۦۙۡ۬ۡۘ";
                    while (true) {
                        switch (str12.hashCode() ^ 1148867482) {
                            case -1463177946:
                                str12 = "ۘۧۗۛۡۥۗۛۥۘۡۖۘۘۡۙۡ۬ۛ۬ۙۧۧۚۥۡۘ۟ۢۧۡ۫ۘۘۗۦۧۚۜۘۖۦۢۗۖۘ۫ۘ۫۟ۦۜ";
                            case -968735730:
                                break;
                            case 1981847885:
                                String str13 = "ۙۢۗۜۛۗ۫ۨۘۘۦ۟ۥۙۨۜۘۥۧۨۘۥۥۥۘ۬ۛۖۢۛ۬۠ۨۧۘ";
                                while (true) {
                                    switch (str13.hashCode() ^ (-1131456455)) {
                                        case -796762541:
                                            str13 = "ۙۦۤ۠ۢۜۘ۟ۤۛۜۗۚۚۙۡۛ۠ۡ۟ۧۡۡۡۥۛۛۜ۬ۤۛۚۚۡۘۢۡۡ۬ۤ۠ۚۥۘۘ";
                                        case -619775581:
                                            str13 = this.boundDialog.isShowing() ? "ۜۤۥۘۧ۬ۧۧ۟ۡۘۤ۟۠ۦ۫ۧۡۢۨۘۗ۫ۦۙۨۜۘۗ۫ۛ۟ۛۨۘۙۙۥۛۧۖۗۢ۟ۦۛ" : "ۗۚۖ۠ۘۧۙۛۦۚۛ۬ۦۜۦۖۛ۬ۛ۫ۥۘۛ۬۟ۦ۟ۖۘۧۘۦۘۙۛ۬ۙۛۚۥۜۘۘۙۛ";
                                        case 505269903:
                                            str12 = "۫ۦۧۘۛ۟ۨۘ۟ۡۡۘۘۨۘۖۨ۟ۥۛۦۘۘۧ۠ۧۥ۟ۘۨۜۧۤۧ";
                                            break;
                                        case 1167988810:
                                            str12 = "ۘۥۡۚۧۥۘۡۙ۫ۜۧۨ۟ۢۧۘۘۛۨ۠ۘۘۙۖۧۘۚۘ۫ۜۤۘۘ۠ۦۥۘۨۥۘ۟ۥۧۘۜۨۧۘ";
                                            break;
                                    }
                                }
                                break;
                            case 2113734428:
                                str3 = "ۗ۟ۖۘ۬ۘۦۘۤۧۡ۬۬۫ۜۜۡۘۜۛۡۘۖۥۗ۫ۖۦۘۧۦۥۚ۫ۨۤۗۚۡۗۨۘ۫۬ۜۘۗۦ";
                                break;
                        }
                    }
                    break;
                case 494490389:
                    str3 = "۠ۛۘۤۙۡۙۨۛ۫ۧۨۘۦۖ۫ۥۙۤۥۦ۬۠ۛۜۘۜۨۡۨ۫ۜۘ۠۬ۡۖ۟ۥۧ۟ۖۘۦۥۥ۬ۨۛۢۜۖۘۖ۠ۘۘۢۙۛ";
                    continue;
                case 672393681:
                    str3 = "۬۠ۢۙ۫ۘ۠۟ۜۥۚۚۡۧ۠۠۠ۢ۫ۤۚ۫۠ۖۦۘۘ۠۠ۥۘ";
                    z = false;
                    continue;
                case 1642127238:
                    str3 = "ۚۗۗ۫ۗۗۥۦۤ۠ۖ۫ۥ۟۬ۛۤۨۚۜۛۨۗۘۚۗۡۨ۬ۢ۬ۜۤۛۧ۠۫ۧۖ۫۬ۥۘ";
                    continue;
                case 1663439438:
                    String str14 = "۫ۡۛۜۖ۫ۨۘ۟۬ۚۨۘ۬ۖۤۗۙۙۗۙۧۨۖۗۨۥۗۙۗۡۘ";
                    while (true) {
                        switch (str14.hashCode() ^ (-498239711)) {
                            case -1919864903:
                                str14 = "ۢۜۢۥۙۡۦۢۡۡۦ۠ۢ۟۬۬ۗۡۗ۫ۚۗۥ۟ۤۦۘۛۨۖ۟۬ۘ۬ۡۦۘ۬ۡۘۘ۠ۚۛ";
                            case 507823181:
                                break;
                            case 1245556763:
                                str3 = "ۗۤۥۡ۫ۢۜۨ۬ۧۢۘۜۗ۠ۢ۫ۛۡ۬ۦۘۘۙۦ۠ۤ۠ۧ۠ۘۨۧۤۨۘ";
                                break;
                            case 2019004428:
                                String str15 = "۟ۛ۠ۤۗۧۡۜۘۚ۬ۤۙۜۨۨۚۘۘ۠۬ۖ۬ۨۨۘ۫ۚ۟ۢ۟۟ۥۥۡۘۤۚۡۘۦۦ۠۬۟ۘۘ۠ۥۘ۟ۜۡۘۜ۬ۨۤۨۧۘ";
                                while (true) {
                                    switch (str15.hashCode() ^ (-887728938)) {
                                        case -924275031:
                                            str15 = this.boundDialog != null ? "ۥ۟ۚ۠ۜۥۦۧ۠ۡۡۖۖۧۧۘ۬ۨۘ۟ۢۜۤۦۜۨ۬ۦ۠ۢۨۘ۠ۦۖۢۥۛ" : "ۛ۬ۙۘۧۢۖۙۘۗۖۜ۫ۤۙۡ۫۫۠ۖۢۙۜۘۢۢۚۡۡۨۘ";
                                        case -668866533:
                                            str14 = "ۚۘۜۘۖۧۘۤۢۤۤۖۚۚۥۛۡۜۛۜۥۡۘۢۗۜۘۥۖۡۤۜۤ";
                                            break;
                                        case -519286825:
                                            str15 = "ۘۗۜۦۖۦۘۜۢۘۥ۟ۨ۟ۘۡۘۢۚۛۦۨۙ۟ۙۥ۬ۛ۟ۜ۠ۢۚۤۘۘۚۥۤۚۚۨۘۘ۬ۘۘۢۨۘۘۡۢۛ";
                                        case 1103068045:
                                            str14 = "۟۟ۢۥۢۘۘۥۡ۠ۧ۬ۦۤۚ۟ۘ۟ۖۘۖۤۥ۟۠ۖۤۦۧ۬ۡ۬ۖ۬ۥۜۚۨۨۡۙ۟ۨۘۨۚۙۦۜۥ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
            }
            str3 = "ۖۥ۠ۢۦۚ۬ۦ۠ۛۤۦۘۗۨۢۨ۟ۢ۬ۜۘۙۚۜۘۜۢۡۖۧۥۘ";
        }
    }

    public static /* synthetic */ void j(JsInterface jsInterface, String str, String str2, String str3, String str4) throws PackageManager.NameNotFoundException {
        String str5 = "۟ۗۖۘۜۗۥۘۛۡ۠۟ۢۡۘۨۘۤۥ۟ۛ۬ۧۨۘۧۘۥۧۨ۟ۗ۬ۗۙۛۡۙۥۘۥ۠ۧۜۥ۫ۧۡۘۘ۠۟ۤ";
        while (true) {
            switch ((((str5.hashCode() ^ 125) ^ 672) ^ 372) ^ (-1821956291)) {
                case -1346903200:
                    str5 = "ۡۚۡۘۧۡ۫ۤۚۧۤۢۜ۬۬ۘ۟ۨۙۡۘۤۤۢ۬ۢۗۧۨۤۡۨ۠ۨۘۖۛۦۘۘۛۛۘۧۜۘ۬ۢ۠ۖ۬ۙ";
                    break;
                case -180744768:
                    str5 = "ۗۥۥۖ۫ۘۜۧۛۢۙ۫ۖۗۡۘۙۢۗۢۙۜۘۖۥۡ۠۬ۦۘ۫ۡۖ۠ۨۜۘۙۤۢ۫ۢ۠ۦۛۦ";
                    break;
                case 585741727:
                    return;
                case 648884628:
                    str5 = "ۤۤۦۘ۫ۥۚۡۘۨۘۦۗۨۘۜۤۚۡۛۦۘ۠ۗ۬۠ۛۘۘۘۜ۫ۢۘۗ۫ۜۘۥۗۥۖۜۦۘ۟ۨۢ۠ۢۧۦ۬۬";
                    break;
                case 1753417237:
                    jsInterface.lambda$verifyCardKey2$5(str, str2, str3, str4);
                    str5 = "۠ۙۥۚۙۙۦۚۦۘ۟ۙۥۘۘۤۦۘۡۤۜۘۢۖۡۘۜ۬ۨۘ۠۠۫ۚ۫ۘۥۚۚ۬ۥۤ";
                    break;
                case 1762854039:
                    str5 = "ۙۖ۟ۚ۫ۡۘۗۢۤ۬ۢۤۗۖۢۙۨۧۛۛۜۘۤۛۙۤ۫ۥۛۚ۬";
                    break;
                case 2035375270:
                    str5 = "ۗۙۡۘۙ۬ۖۘۦۙۜۛۙۛۖۖۦۘۤ۟ۘۛۘۘۗۗۦۥۛۖۛۧۨۥ۬ۧۦ۟ۦۘ۫۠ۛ۠۫ۖ";
                    break;
            }
        }
    }

    public static /* synthetic */ void k(JsInterface jsInterface, String str, boolean z, int i, String str2, String str3, String str4) {
        String str5 = "۟ۜۖۖۖۖۛ۬ۙۜۡۤۦۢۙۦ۬ۧۘۡۘۘۥۡۢۥ۫ۥۘۜۡۘۥۥۢۧ۫ۡ";
        while (true) {
            switch ((((str5.hashCode() ^ 807) ^ 338) ^ 746) ^ (-1130314677)) {
                case -1567697528:
                    str5 = "۟ۚۘۘۦۥۧۘۤۧۖۘۧۢۡۢۤ۠۟ۘۘۚ۟ۜۘ۫ۜۢۚ۠ۦۘۧۘۢ۬ۖۗ۠ۨۥۘۥۧۢۢ۬ۡۘ";
                    break;
                case -607136700:
                    str5 = "ۡۛۜۘۧۧۦۤۤۧۨۧۙۙۨۤۚۨۧۘۗۙۚۜۗۦۢ۬ۤ۟۬ۡۗۘۖۧۚۧۘۙۖۘ۠ۦۘۘۚۢۖۘ۫ۢ۠ۜۘۧۘۢۜ۠";
                    break;
                case -299281123:
                    return;
                case 244871006:
                    str5 = "ۗۗۗۥ۫ۦۙۥۧۘۙۢۗۤۥ۬ۤۙۘ۟ۧۜۘۘۧۛۡۤ۬ۗ۟ۤۙ۟ۙۧۗۘۖۦۗۗۦۘۙ۫ۗۤ۟ۙ";
                    break;
                case 346748511:
                    jsInterface.lambda$verifyCardKey2$4(str, z, i, str2, str3, str4);
                    str5 = "ۦۘۧۛۡۡۘۘۗۥۘۘۖۨۘۧۢۡۘۜۦۘۢ۟ۨۗۡۥۨۨ۬۠ۛ۫۫۫ۘۡۖۧۘۚ۬ۨۘۦۤ۟ۗۧۛۦ۫ۚ";
                    break;
                case 858443028:
                    str5 = "ۤۢۢۨۚۛ۠ۦۘۘۨۨۘۚ۫۬۟ۙۛۦۜۜۥۥۜۖ۠ۖۘۙۡ۬ۥۨۧۧۖۡۥۖۘۤۧۥۤۛ۠۟ۚ";
                    break;
                case 1032159864:
                    str5 = "۬ۘۨۥ۬ۡۘۧۧۥۘ۠۬ۨۛ۟ۥۘۙۘۡ۫ۙۨۢ۫ۙۙۢ۟ۚ۠ۦۘ";
                    break;
                case 1207720418:
                    str5 = "ۛۜ۟ۚ۬ۙ۫ۜۥۢۤۖۢۥۤۥۨۖ۠۫ۘ۠ۖۦۘ۬ۥۜۦ۫ۛۢۛۢ۟ۚۚ۫ۜۧۡۡۡۘ";
                    break;
                case 1435906733:
                    str5 = "ۧ۬ۨۜۨ۟ۥۘۨۘۘۘۤۢۛۤۥۗۤۜ۫ۖۘۙۦ۠ۥۚ۟ۜۢۡۢ۠ۚۙۘۗۚۜۘۡۡۡۘ";
                    break;
            }
        }
    }

    public static /* synthetic */ void l(JsInterface jsInterface, MediaPlayer mediaPlayer) {
        String str = "ۧۦۧۘۘ۬ۜۘۥۚۜۘۗۦ۟۬ۚۡۛۥ۠ۛۡۧۨ۟ۜۘ۬ۛۖۡ۟ۨۗۜۦۘۥ۫ۦۤۤۧۦ۠ۖ۬ۖ۫ۧۢۘ۠ۙۤ۫ۡ۟";
        while (true) {
            switch ((((str.hashCode() ^ 967) ^ 282) ^ 670) ^ (-1929767877)) {
                case -376311616:
                    return;
                case -289731653:
                    str = "ۡۡۧۘ۬ۙۘۢۨۨۘۦۚۢۤ۠ۛۖۥۛ۟ۚۜ۟ۢۤۗۜۥۘۡ۬ۨۢۢۙ۫ۙۥۘۘۛۨ۬ۦۡۨۜ۬ۘۨۦۨۙۙۥۖ";
                    break;
                case -264819147:
                    jsInterface.lambda$playMusic$9(mediaPlayer);
                    str = "ۘۧۡۘۘۚۦۘۚ۠ۚۜۘۘۦۚۜ۠ۗ۫ۧۚۧ۠ۨۛۘۜ۬ۚ۠ۙ۠۬ۡۘۛۦۘ۫۠۠ۖۤۛۨۗ۫ۡۡۨۘ";
                    break;
                case 1269117569:
                    str = "ۧ۟ۤۤۡ۫ۢ۠ۡۘۧۢۤۥۙۤۤۚۨۦۛۚ۬ۨ۟ۤ۫ۦ۬ۦۜ۫۫ۨۘۤۧۧۦۙ۫۠ۧۚ۠ۤ۠۟ۨۘۗ۟ۤ۬۫ۛ";
                    break;
            }
        }
    }

    private /* synthetic */ void lambda$close$0(String str) {
        String str2 = "ۦۘ۫ۚۗۦۘۗۡۘۘ۫ۙ۟ۢۚۜۘۦۙۥۢۨۘۘۘۨ۟ۥۥ۠۬ۛ۠ۡ۬ۘۜ۠ۘۘ۟ۦۙۡۙ۫ۡۜۗۜ۠ۙ";
        while (true) {
            switch ((((str2.hashCode() ^ 702) ^ 646) ^ 11) ^ (-544164791)) {
                case -1073123830:
                    str2 = "ۢۥۘ۬ۧ۟ۚۛۦۖ۬ۦۙۚۨ۬۫ۥۚۧۥۘۘۡۦۨۖۥۘۨ۬ۜۘۚۘ۟۟ۘۜۘۙۥۜۡ۟ۥۚۜۧۘۙۡۢ";
                    break;
                case -568151738:
                    closeBoundPopup(str);
                    str2 = "ۧ۠ۗۙ۟۬ۧۚۖۘۛ۬ۗۛۢۨۘۢۙۡۘۙۡۚۨۧۘۨۤۧۢۜۙۜۙۤ۫۫ۤ";
                    break;
                case 206945404:
                    return;
                case 1657886661:
                    str2 = "۠ۧۜۘۡۡۛ۠ۚۘۘ۬۫ۘۦۤۡ۟ۧۚ۟۬ۚۜۦۖ۬ۥۘ۟ۙۖ۬ۖۨۥۢۡ۠ۢ۟۫ۥۨۘۢۢۜۥۦۙ۬ۗۧۙۘ۬";
                    break;
            }
        }
    }

    private /* synthetic */ void lambda$close$1() {
        String str = "ۦۗۜۜۜۜۘۙۙۨۘۚۚ۠ۥۦۧۢۗۢ۟ۨۘ۟ۨۖۧۗ۟ۖ۬ۜۘۥۗۚ۫ۙ۫";
        while (true) {
            switch ((((str.hashCode() ^ 800) ^ 128) ^ FileUtils.FileMode.MODE_755) ^ (-1177524990)) {
                case -1426366059:
                    return;
                case -910819321:
                    str = "ۨۜۢۙۙ۟ۖۧۘۘۥ۫ۧۗۚۨ۬۫ۘۘ۬ۦۡۨۙۥۘ۬ۥۥ۠۫ۜۘۘۖۧۚۥۧ۠۟ۡۤۦۛۢۛ۫ۡۘ۟ۡۚۗ۫ۥ";
                    break;
                case 1423810244:
                    closeBoundPopup(null);
                    str = "ۜۘۙ۟ۧۡۧۢۘۗ۟ۗۢۜۥۘ۫ۖۤ۫۬ۦ۟ۡۧۘۡ۫ۗۤۖۗۚۡ۟۫۟ۘۢۜۚ۫ۦۗ";
                    break;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0012, code lost:
    
        core.pro.android.notify.k2.logToFloatingWindow(core.pro.android.notify.l2.decrypt("vJFlQGNOkXa6nlbpsDl3pyth1ey5Fvtr0vR9uhD/erEJeJB9\n", "XxH1CjCoH9M=\n"), core.pro.android.notify.l2.decrypt("w4/gk+M=\n", "p+qC5oRU9iA=\n"));
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0025, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private /* synthetic */ void lambda$http$6(String str, JSONObject jSONObject, String str2) {
        try {
            WebView webViewFindCurrentWebView = findCurrentWebView(str);
            String str3 = "ۤۡ۠۫۬ۙ۠۟۫ۘۙۜۗۗۙۧۙۥۘۤۛۥۘۨۧ۫ۢ۬۟ۡۘۗۡۦۛۗ۟۟۫ۢ۟ۦۙۖ۬۫ۙۢ۠ۜۦۘۢۘۛۦ";
            while (true) {
                switch (str3.hashCode() ^ 1622828477) {
                    case -1380855845:
                        String str4 = "ۦۡۖۧۖۖۘۧۥۧۘۥۨۚۚۚۢۚۗ۟ۚۡۡۙ۬ۤۘۘۘۧۙۙۦۨۛۥۘۤۖۙۘۘ۬ۙۙۧ۬۟ۧۥ";
                        while (true) {
                            switch (str4.hashCode() ^ (-1507478211)) {
                                case -1882753676:
                                    if (!this.disposed) {
                                        str4 = "ۖۢۥ۠ۤۨۘۨ۟ۥۘۘۖۙۧۚۖۘۛۙۧۘ۫ۤۘۜۢ۫۫ۜۘۧۘۜۘ۠ۨۜۘۗۖ۟ۙۛۖۙۜۡۘ۟ۦۖۘۙۗۖ";
                                        break;
                                    } else {
                                        str4 = "ۡ۟ۙۡۤۢۚۥۖ۫ۘۥۨۘۗ۠۠ۘۘۨ۬ۨۘ۬ۜ۠ۡۚۧۗ۬ۦۘۛۙ۬ۤۡ۟";
                                        break;
                                    }
                                case -1857884470:
                                    str4 = "ۦۤۜۘۢۛۤ۟ۥۘۗ۫ۜ۠ۘۙۥۖۖۖ۫۠ۥۤ۟ۘۢۤۤۤۚۡۖۥۘۨۡ۠۫ۗ۫ۥۢۗ۬ۙۨۜۦ۬";
                                    break;
                                case -794470626:
                                    str3 = "ۚ۬۠ۢۖۜۘۖۡۡۘۘۢۥۘۖۤۚ۠ۦۦۘ۬ۘۜۘۢۨۨۙ۟۟ۦۥۨۘۥۖۘۘۦۚۥۘ۫ۛۗۦۦۨۦۖ۟ۢۖۘۜۗۤۦ۫ۨۘ";
                                    continue;
                                case -196607652:
                                    str3 = "ۜۛۥۜۛۡۦ۫ۚۛ۬ۗ۬ۡۖۢۢۡۧ۬ۖۘۨۜۜۘۤۘۘۗ۫ۥۘ۬ۖۡۘۡ۫۠";
                                    continue;
                            }
                        }
                        break;
                    case -272901699:
                        String str5 = "ۚۧۦۦۘۘۤۚ۟ۜۧۜۘۢۜ۬ۤۖۤۡ۟ۜۘۗۘۘۜۖۜ۫ۤۖ";
                        while (true) {
                            switch (str5.hashCode() ^ 774311722) {
                                case -2056392899:
                                    str5 = "ۙۤۗ۫ۗۧۙ۫ۤۜۜۧ۠ۥۚ۬ۘ۫ۥۧۡۜ۟ۘۥ۬۫ۡۢ۟ۧۨۥۘۡۥۡۘۚۢۡۘ۫۠ۗۘۥۜ۠ۥۥۘ";
                                    break;
                                case -1114861633:
                                    String str6 = "ۥۛۢۡۘۙۜۢۥۘۛۦۤۦۥۖۘۘۡ۟۬ۗۜ۟ۛۘۘۙۧۥ۠ۚۖ";
                                    while (true) {
                                        switch (str6.hashCode() ^ (-1245981450)) {
                                            case -211009551:
                                                if (webViewFindCurrentWebView == null) {
                                                    str6 = "ۥۧۡ۫ۨۦ۠ۨ۬ۜۙۨۦۧ۠ۙۦۗ۠ۦۤۤۨۖۘۧ۬ۥ۬ۤ۫ۨۧۘۘۙۧۨ";
                                                    break;
                                                } else {
                                                    str6 = "ۜۘ۠ۧ۫ۨۘۨۗۙۚۡۘۜۜۛ۠ۤۦۤۦۦۥۚۜۥۜ۬ۡۘ۬۫ۢۥۧۛ";
                                                    break;
                                                }
                                            case 667299131:
                                                str5 = "ۖۢ۫ۗ۟ۡۚۧۥۤۦۧۢ۫۬ۛ۟ۧۜۦۘۚ۬ۥۘ۠ۙۙۥۘ۬ۢۨۧۡۗ۫۬۫ۘۘۨ۫ۡۘۨۦۨۘ۫ۜۨ";
                                                continue;
                                            case 1662295638:
                                                str5 = "ۧۗ۟ۗۥۤۜۢۛۨۗۡۛۖۨۘۙۘۧۘۡۜۘۗۡۢ۠ۧۖۖۗۥۘۜ۠ۨۘۡۘۙۖ۟ۖۡۙۡۙۢ۟ۖ۠ۥۙۢ۫۟ۜۘۘ";
                                                continue;
                                            case 2044198799:
                                                str6 = "۟ۘۨۘۥۤۘۘۥۗ۫ۘۖ۫۠ۖۙ۟ۛۥۘۢۨۙۖۚ۬ۨ۟ۚۚۚ۟ۛۜۘۘۘۧ۠ۤ۟ۦۘۢۨۨۘۚ۬ۨ۫ۘۗۢۧۨۘۨۧۡ";
                                                break;
                                        }
                                    }
                                    break;
                                case -992410469:
                                    k2.logToFloatingWindow(l2.decrypt("Ii7BO0iwb+QkIfKSm8eJNbXecZeN7wfyVEjY1vP3baZ6Pbfvh7N63yke0g==\n", "wa5RcRtW4UE=\n"), l2.decrypt("xVBYU4A=\n", "oTU6JufsGqw=\n"));
                                    String str7 = String.format(l2.decrypt("dnQYTxbcko0kblF2UeGa5U8zBkoL2KyCJG5fAg==\n", "AR12K3mryao=\n"), str2, JSONObject.quote(jSONObject.toString()));
                                    Log.d(l2.decrypt("Q3y3My1drm+iBdEFSA2eClLHZ+yFqX3W\n", "GJQ4r8TlMYs=\n"), str7);
                                    webViewFindCurrentWebView.evaluateJavascript(str7, null);
                                    return;
                                case -82560525:
                                    break;
                            }
                        }
                        break;
                    case 886385318:
                        break;
                    case 1845480627:
                        str3 = "ۖۜۛ۠ۖ۟ۤۤۚۥۜۛۛ۟ۜ۫۬ۚ۟ۦۘۦۨ۫۬ۘۧۘ۬ۜۤ۟ۚۘۖۘ۟";
                        break;
                }
            }
        } catch (Exception e) {
            k2.logToFloatingWindow(h.d("wqscQ2Ks0czEpC/qsds3HVVbrOyq1LfZos4ouNn++g==\n", "ISuMCTFKX2k=\n", new StringBuilder(), e), l2.decrypt("B+ETQtc=\n", "Y4RxN7Bftjw=\n"));
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:78:0x015b. Please report as an issue. */
    private /* synthetic */ void lambda$http$7(String str, String str2, String str3, String str4, String str5, String str6) throws JSONException {
        String str7;
        JSONObject jSONObject;
        Iterator it;
        HashMap map = new HashMap();
        String str8 = "ۛۜۥۘۥ۠ۥۘۥۘۘ۠ۘۥۘۥۗۥۘۦۚۢۙۗۨۙۡۜۢ۬ۡۢ۠ۘ";
        while (true) {
            switch (str8.hashCode() ^ 1069946053) {
                case -2111415461:
                    break;
                case -2050565233:
                    str8 = "۠ۖۡۨۧ۫۬ۥۖۙ۟ۚۨۙۜۘۥۛۚۜۥ۟ۧۘۘۘۖ۫۟ۛ۫ۡۘۦۜۤۚ۬ۨۘ۟ۚۖۘۤۧۗۢۗۖۘۥۜۦۙۛۘۘۥۛ۬";
                    continue;
                case -803481847:
                    String str9 = "ۧ۬ۡۖۦۚۦۨۜۘۛۚۘۖۘۦ۠ۘۤۦۜۘ۬ۙۦۘۢۡۘ۫ۗۘۘۙ۟ۙۡۧۘۛۢۥۘۗۙۖۘ۟ۚۘۘۡۡۛ۟۫ۨۙۛۦ";
                    while (true) {
                        try {
                            switch (str9.hashCode() ^ (-539003444)) {
                                case -106373646:
                                    break;
                                case 255143708:
                                    str9 = "۫ۛ۟۟ۖۘۗۧۘۘۛ۠۬۫۫ۢ۟ۢۗ۟ۗۘۥۡۜ۟۬ۗۡۢۡۘۨۙۨۚۥۡۘ";
                                    continue;
                                case 1856292162:
                                    JSONObject jSONObject2 = new JSONObject(str);
                                    Iterator<String> itKeys = jSONObject2.keys();
                                    while (true) {
                                        String str10 = "ۘۜ۫ۧۗ۫ۧۗۦۚۚۦۗۤۨۗۜۘۧۜۤۦۗۛۚ۫ۘۢۖۘۤۖۦۘۡۧۨۤ۠ۤۖۗۘۘۛۧ۬ۨ۟ۧۘ۬ۘ۫ۢۗ";
                                        while (true) {
                                            switch (str10.hashCode() ^ 1543498096) {
                                                case -1082432074:
                                                    break;
                                                case -767233082:
                                                    str10 = "ۢۜۙۢۛۦۥۦۘۘۨۥ۠۬ۥ۬ۧۧۥۘۡۙۘۘ۬ۗۙۘۥۙۗۛ۠";
                                                case -84570812:
                                                    String next = itKeys.next();
                                                    map.put(next, jSONObject2.getString(next));
                                                case 1208313083:
                                                    String str11 = "ۜۤۦۖۜۘۘۙ۠ۡ۬ۘۡۥۥۙۨۡۚ۠ۖ۬ۧۗ۠ۖۦ۫ۨ۫ۤ۬ۢۡۘۨۜ۫ۘۛۢۢۧۜ";
                                                    while (true) {
                                                        switch (str11.hashCode() ^ (-1728732208)) {
                                                            case -1751041301:
                                                                str11 = itKeys.hasNext() ? "ۚ۬ۚۖۖۗ۫ۖۚۛ۬ۥۛۡ۫ۧۡۛۥۦۤۜۥۘۘۢۙۦۛۥۙ" : "ۚۧۦ۫ۖۘۘۢۥۨۗۢۛۖۡۜۢۨ۬ۚۜۡۗ۠ۥۚۡۚۖۚۦۘۘۚۗۖۧۢۢۢ۠ۗۦۧۘۦۜۨۘۢۨۛ۠ۦ۟ۢۧۨۘ";
                                                            case -946054138:
                                                                str10 = "ۧ۠ۡۘۛ۟ۜۥ۬ۨۘۤۚۢۧ۠ۧۜ۟ۧۜۧۘۥۛ۫ۤۛۡۧۦۦۦۙۡۤۨۧ۬ۘۖۘ۟۟ۧۛۥۖۗۛۚۖۘۢۚ۠ۖ";
                                                                break;
                                                            case 858852204:
                                                                str11 = "ۚۖ۠ۢۜۜۜۜۚ۟۟ۡۘۗۚۥۘ۠۬ۦ۬ۡۘ۫۬ۛۙ۠ۦ۠ۦ۫ۨۦۥ۠ۖ۫۫ۨۚۙ۠۟ۚۥۚۖۦ۟ۙۜۦ۫ۡۘ";
                                                            case 1861857323:
                                                                str10 = "ۙۘۚۙ۠ۜۘۘ۟ۨ۫ۘ۠ۤۢۢۧۜۢۜۘۢۚۢۜۘۖۚۖۘ۬ۛ۟۠ۧۜۡۘۧۧۘۘۙۧۧ";
                                                                break;
                                                        }
                                                    }
                                                    break;
                                            }
                                        }
                                    }
                                    break;
                                case 1872635006:
                                    String str12 = "ۡۦۘۜ۠ۡۘ۫ۤ۬ۙۗۥۚۚۛۦۙۜۢۨۘۨۗۦ۫ۙۗۨۙۤ۬ۛۘۘۢ۫۬ۖۚۧۦ";
                                    while (true) {
                                        switch (str12.hashCode() ^ 1379318711) {
                                            case -1643649263:
                                                str9 = "۫ۚۖۘۗۦۧۘ۠ۚۢۛۗۛۢۥۡ۫ۨۛۗۡۥۚۘۨۚ۬۫ۧۖۡ";
                                                continue;
                                                continue;
                                            case -1166138066:
                                                str9 = "ۦۧۥۙۜۡۘۗۖۜۘۜۧ۬ۜۥ۫ۛۦۖۚۢۜۘۨۖۤۖ۫۬ۛ۫ۨۘ۫ۜۥۘۢ۬۫ۛۦۦۘۙۘۡ";
                                                continue;
                                            case 42204527:
                                                if (!str.trim().isEmpty()) {
                                                    str12 = "ۗۘۥۘۖۜۙ۫ۛ۫ۧ۠ۗ۬ۦۥۘۢۗۥۥ۠ۢۛۖۖ۫ۡۖ۟۫ۗۨۖۚۚۚۘ۟ۤۘۘۧۧۘۨ۬ۚۘ۟ۗ";
                                                    break;
                                                } else {
                                                    str12 = "ۛ۬ۥۘۦۡۙ۬ۤۡۘۤۙ۟ۙۗ۠۫ۛۥۖۦۘۧ۠ۙۥ۬ۚۦۡۘۥ۟ۚۗۚۜۘۦۨۧۘۗۙ۫ۤۥۧۗۗۘۘ";
                                                    break;
                                                }
                                            case 618372342:
                                                str12 = "ۜۗ۟ۖۜۥۗۥۘۗۖۚۥۗ۬۟۬ۦۘ۬۬۠۠ۘۖۤۤۚ۠ۙۖ۫ۦ۬ۨۘۦۗۗ۫ۢۜۘ";
                                                break;
                                        }
                                    }
                                    break;
                                default:
                                    continue;
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            break;
                        }
                        e.printStackTrace();
                    }
                    break;
                case -555146451:
                    String str13 = "۬ۧۧۚۡ۠ۦ۠ۡۖۛ۬ۨۥ۬ۗ۬ۘۜۘۧۜ۟ۙۜۛۘۥۦ";
                    while (true) {
                        switch (str13.hashCode() ^ (-1922838030)) {
                            case -1936971172:
                                if (str == null) {
                                    str13 = "ۥۨ۫۟ۜۛ۟ۘ۠ۗۛۘۘۖۛۡۘۜۚۡۚۗۡۘ۠ۤ۠ۨ۠ۖۘۜۖۦ۬ۜۢۨۦۦ";
                                    break;
                                } else {
                                    str13 = "ۜۙۡۘۤۖۡۘۡ۟۠ۤۡ۠ۜۚۡۘۘۥ۠ۗۧۡۦۨۤۧۡۢ۠ۛۘ۫۫ۚ۠ۖ";
                                    break;
                                }
                            case -1403840512:
                                str8 = "ۧۘۤۖۦۛۥۢ۠۬ۥۖۘ۬ۜۖۗۨ۠ۚۗۡۢ۠ۜ۠ۗۗۢۛۨۘ۬ۨۗۙ۫۫";
                                continue;
                            case -132060168:
                                str13 = "۠۠ۧۧۥۡۚۢۘۤ۠۬ۛۘۚۖۦۧۢ۟ۘۘۥۢ۟ۗۥۜۘۤۘۘ";
                                break;
                            case -7334279:
                                str8 = "۬ۡۜۘ۬ۢۡۖۥۖ۟۬ۚۜۚۘۘۦۡ۫ۤۜ۠۠ۤۜۘۢۢۢۗۨۖۘۜۦۛۛۦۧ";
                                continue;
                                continue;
                        }
                    }
                    break;
            }
        }
        Object[] objArrHttpRequest = Utils.httpRequest(str2, str3, map, str4);
        boolean zBooleanValue = ((Boolean) objArrHttpRequest[0]).booleanValue();
        Map map2 = (Map) objArrHttpRequest[1];
        String str14 = (String) objArrHttpRequest[2];
        JSONObject jSONObject3 = new JSONObject();
        try {
            jSONObject3.put(l2.decrypt("XfPDdO7xjQ==\n", "LoagF4uC/vo=\n"), zBooleanValue);
            String strDecrypt = l2.decrypt("zeDmRA==\n", "r4+CPfCKx2c=\n");
            String str15 = "۬ۢۘۦۤۖۘ۟۫ۚۨۨۨۘۥۡۖۘۧۨۦۘۘۗۘۗۦۖۘۥۙۨۘۚۛۥۘۤۨۧۘۦۥۨۘۨۜ۠۫ۢۖ";
            while (true) {
                switch (str15.hashCode() ^ 1153359727) {
                    case 326673923:
                        String str16 = "۠ۙۘ۠۬۫ۖۙۖ۫ۧۥۘۛۛۖۘۖۘۛۙۙ۟ۛۨۗۖۨ۠۫ۖۘ۟ۦۖ۟ۢۤۘۜۛۗ۠ۦ۟ۡۜ۟۠ۨۨۖۘۨۡ۬";
                        while (true) {
                            switch (str16.hashCode() ^ (-638164135)) {
                                case 738157140:
                                    if (str14 != null) {
                                        str16 = "ۗۡۖ۫ۙۤۜۡۤ۟۠ۤۨ۫ۤۗۥۤۧۡۦۘۚۦۧۛ۫ۦۘ۬۬ۛۙۧۗۖۧۡ";
                                        break;
                                    } else {
                                        str16 = "ۖ۠ۢۚ۟ۦۘۧۡۖۘۢۘۘۖۥۖۘۚۛ۬ۡۦۘۘۗۛۢۦ۠ۖۦ۟ۖۤۤۜۖ۟ۛۚ۬ۢۛۤۚۛۤۚۙۗ";
                                        break;
                                    }
                                case 1078182808:
                                    str16 = "ۦۨۨۘۙۘۜۘۦ۠ۘۨۥۦۘۘۛۡۘۖ۬ۗ۠ۛۡ۬ۡۧۘۢۖۦۘۛ۟ۨ۟ۦ۫ۚۤۜۘۗ۠ۘۛۗۥۧۚۘۘۜۧ۠";
                                    break;
                                case 1420271502:
                                    str15 = "۫ۡۖۘ۬ۡۢۜ۠ۥۥۗۛۚ۬ۦۢۖۧۘۗۢ۟۟ۡۗۦۙۢۡۜۙۦۨ۫ۙۖۢ۬۬ۢۥ۬ۘۘۨۚۤۥۖۘۘ۬ۗۨۤۜۙ";
                                    continue;
                                    continue;
                                case 2067096493:
                                    str15 = "۫۬ۛۘ۬ۛۧ۟ۖۘۚۗ۟ۦۜۚۧۘۘۘ۬ۡۙ۠ۡۘۗ۟ۦۘۙۚۨۨۥۦۢ۬ۥۘ";
                                    continue;
                            }
                        }
                        break;
                    case 567647177:
                        str15 = "۠۬ۥۙ۫ۥ۠ۨۧۤۘۤۨ۫ۜۘۥ۠ۨ۬ۘۙ۫ۙۜۤۖۤ۬ۨۢ";
                        continue;
                    case 711372233:
                        str7 = str14;
                        break;
                    case 1256229527:
                        str7 = "";
                        break;
                    default:
                        continue;
                }
            }
            jSONObject3.put(strDecrypt, str7);
            k2.logToFloatingWindow(l2.decrypt("Gn81v+rOCB0ccAYWObnuzI2PhRMvkWALbBcKQl+ZBF9CbENrJRI=\n", "+f+l9bkohrg=\n") + str14, l2.decrypt("LgT81Uo=\n", "SmGeoC08h0g=\n"));
            jSONObject = new JSONObject();
            it = map2.entrySet().iterator();
        } catch (JSONException e2) {
            e2.printStackTrace();
        }
        while (true) {
            String str17 = "ۡۙۤۙ۫ۡۘۜ۟ۘۘۚ۟ۦۤۡۙۢۤۡۛۨ۬ۤۧ۟ۧۨۜۡۚ۬";
            while (true) {
                switch (str17.hashCode() ^ 392754389) {
                    case -2076747948:
                        String str18 = "ۗۘۗۦۚۥۧۜۥۘ۫ۥۘۖۧۗ۫ۨۖۘۚۖ۬ۚۡۡۘۧۖۡۡۚ";
                        while (true) {
                            switch (str18.hashCode() ^ (-1467444118)) {
                                case -1970656268:
                                    str17 = "ۛۦۨۥۥۧۢۥۖۧۙ۟ۚ۫ۡۨ۫ۖۖۚۜۖۜ۟ۛ۟ۙۥۚۜۘۜۘۖۘۜۚۢ۠ۛۛۖۨۢۛۚ۟ۗۥ";
                                    break;
                                case -569359955:
                                    str18 = it.hasNext() ? "ۚ۟ۛۧ۠ۗۧۘۖۗۜۨۘۚۤۦۥۖۦۘۘۢۡ۟ۤۘۘ۬ۜ۬ۡۗۡۘۨ۫۟۠ۦ۫۫ۧ۫۠ۜۜ" : "ۜۤۤ۠ۥۨۘ۬ۡ۠ۖۗۨ۠ۗۦۚۢۥۘ۟ۜۙ۬ۨۤۚۛۥۘ۫ۛۙۘۧۡۙ۫ۢ";
                                case -319664128:
                                    str17 = "ۖ۟ۥۘۢ۬۫ۤ۫ۖۘۨۥۘ۟ۢۦۡۤۧۦۖۧۚۤۦۘۛۨۖۛۤۤۖۗۘۗۛۖۘ";
                                    break;
                                case -269878978:
                                    str18 = "ۘۧۥۙ۬ۚۖۤۚۧ۬۬ۚۧ۬ۙۤۤۤ۬ۜۘۘۤۦۘ۫ۖۜۥۦ۬ۨۥۢۨۖۘۘۙۖۧۧۦۚ۠ۙۡۛۙۘۘۛ۬ۙۡۙۖۘ";
                            }
                        }
                        break;
                    case -1059908477:
                        str17 = "ۦ۠ۨۚۚۡۦ۟ۥۜۢۚۡۦۗۖۦۢۢۘۥۘۘۖۨۙۥۚۢۙۡ";
                    case -57413097:
                        break;
                    case 146829747:
                        break;
                }
                jSONObject3.put(l2.decrypt("02sqGLWcWA==\n", "uw5LfNDuK4c=\n"), jSONObject);
            }
            this.mainHandler.post(new x(this, str5, jSONObject3, str6));
            return;
            Map.Entry entry = (Map.Entry) it.next();
            jSONObject.put((String) entry.getKey(), new JSONArray((Collection) entry.getValue()));
        }
    }

    private /* synthetic */ void lambda$pauseMusic$13() throws IllegalStateException {
        MediaPlayer mediaPlayer = null;
        String str = "ۛۧۢۨۤۨۘۜ۫۠ۢۧۜۘۖۦۦۤۚۖ۫ۖۜۘ۟۫ۢۦۤۛ۫ۘ۟ۡۨۤۛۡۦۘۦ۬ۢۖ۟ۡۘۨ۬ۙۤۧۘۘ";
        while (true) {
            switch ((((str.hashCode() ^ 767) ^ StatusLine.HTTP_TEMP_REDIRECT) ^ 391) ^ (-1888909062)) {
                case -1573143581:
                case 625246582:
                    return;
                case -1570002104:
                    String str2 = "ۛۧۦۘۚۧۜۘۙۘۤۛۥۥۤۚ۟ۥۢ۫ۘۘ۠ۤۡۨ۬ۜۧۖ۫ۚۖۢۜۧۖۤۤ۠ۚۧۥۥۘۙۚۜۘۖۢۨ";
                    while (true) {
                        switch (str2.hashCode() ^ (-1722806263)) {
                            case -1432586819:
                                str = "ۦۡ۬ۤ۠ۥۢۦۧۘۜۜۗۦۨۙۢۚۦۘ۠ۡ۟ۚ۫۫۬ۘ۠ۦ۫ۘۘۤۦۘۧۛۦ۠ۡۡۛ۟ۙۡۥۛۛۢ";
                                continue;
                                continue;
                            case -651846198:
                                str = "ۥۙۘۘۖۙ۟ۤۛۤ۬ۜۥۘۥ۟ۥۘۛۨۡۘۧۚۖۨۡۥۘۘۘۖۙۧۡۘ";
                                continue;
                            case 597877529:
                                String str3 = "۟ۧۡۘ۫ۖۥۘۙ۟ۡۘۤۨۖۘ۬ۛۧۨۢۨ۫ۦۛ۟ۥۘۦۨۦ۠ۜۧ۟ۙۛ۬ۨۦۘ";
                                while (true) {
                                    switch (str3.hashCode() ^ 2123658867) {
                                        case -23183530:
                                            str2 = "ۧۗۖۘۙۡۗۡۦۦۖۙۦۗۙۙۛۖۢۡ۠ۘ۟ۘۥۥۗۚ۟ۢ۬";
                                            break;
                                        case 352751923:
                                            str3 = this.disposed ? "ۧۢۨۢۢۡۧۧ۠ۘۦ۬ۡۧۙۜۤۡۘ۟ۖۜۦۧۡۘ۫ۨۨۛۖۗۜ۫ۡۘۧۡ۠" : "ۧ۫ۨۛۜۙۥۖۗۦۥۘ۫ۗۢۦۢۚۡۤۧۦۤۛۙۚ۠ۙۦۡۘۥۦۖۘۜۖۜۘۗۡۜۤ۫";
                                        case 521358849:
                                            str3 = "ۘۗۜ۫ۤۥۘۥۖ۠ۗۛۤۨۦۙۛ۫ۜۤۘۨۘۖۥۚۗۨۜ۫ۜۥۘ۠ۢۥۘۗۛۜۘۚۥۦۘۥۦۥۘ";
                                        case 1436021130:
                                            str2 = "۟ۨۜۘۖۘ۠۠۟۫ۢۛۢۖۘۥۘۥۥۧۘ۠ۧ۬۟۟ۜۘۤۚۗۨ۫ۙۡۛ۟ۚۚۨۘ۬ۤۥۘ۫ۛۚۨۚۥۘۚۧۨ";
                                            break;
                                    }
                                }
                                break;
                            case 903399323:
                                str2 = "ۥۦ۟ۘۖۨۡۥۢۦۜۥۘۨۙۚۢ۟ۦۘۤۙۖۨ۟۬ۗۗۨۘ۫ۦۚۢۖۖۘۛۨ۫۫ۨۥۘ۫ۗۖۘۧۤۦ۟۠ۦۘ";
                                break;
                        }
                    }
                    break;
                case -1511942217:
                    String str4 = "ۡۛۤ۠۟ۨۙۙ۫ۘۚۘۛۧۙ۬ۡۜۘ۬۬ۨۘۥۖۘۘۥۨۖۘۚۛۥۘۗ۟ۤۢۙۦۘۥۢۚۡۦۨۦۧ۟ۢۙۡ";
                    while (true) {
                        switch (str4.hashCode() ^ 1921083925) {
                            case -1305112181:
                                str4 = "ۜۧۥۘۦۡۤ۟۫ۛ۬۠ۜۦۦۜۘۢۤۥۘ۫۟ۥۘ۠ۤۛۢ۟ۛ۟ۗۜ۟ۘ۬ۖۤۤ";
                            case -646559765:
                                break;
                            case -196283535:
                                str = "ۘۖۥۦۧۨۘۢۗۢ۠ۜۢۖۙۨۦ۠ۥۘۥۨۧ۬۬ۢ۫ۥۦ۫۬ۤۖۜۧۜۨۘۛۙۦۘۘۡۗ";
                                break;
                            case 1861681852:
                                String str5 = "ۜ۫ۜۗۙ۬۫ۦۧ۬ۡۛۤۘۥۥۤۧۚۢۙۙۢ۫۬ۙۖۜۘ";
                                while (true) {
                                    switch (str5.hashCode() ^ 34145350) {
                                        case -1869082068:
                                            str5 = "ۥۨ۫ۜ۬ۖۘۜۦۤۘ۬ۖۚۦۜۥۗۥۘ۠۬ۗۦۛۜ۫۠۬ۨۨۖۘۛ۠ۥۢ۫۬";
                                        case -334627072:
                                            str5 = mediaPlayer != null ? "ۗۢۜۘۧۛۘۘ۫ۚۖۘۘۛ۟ۨۡ۫ۤۚۜۘۗۧۗ۠ۙۥۨۖۥۘۛۡۙۨۢۡۘ۠۠ۡۘۡۧۚۛۖۘ" : "ۧۙۦ۠ۗۖۘۘۢۦ۬ۖۘۚۡۙۡۦۧۘ۫ۤۛۨۨ۬ۢ۠ۛۤۥۦۦۡۘۧۙۗ";
                                        case 1042125963:
                                            str4 = "۠ۘۦ۬ۜۚ۫۬ۖۥۙۛ۫۟ۜۡۙ۠ۥۜۡۘۤ۟ۛ۫ۧۚۗ۬ۥۢۢۤۥۦۖۘۧۙۤۡۛۙ";
                                            break;
                                        case 1754320485:
                                            str4 = "۟ۖۛۥۛ۫ۚۘۦۦۥۡۘۘۚۙ۟ۢ۠ۨۖۘۜۢۖۘ۟ۘۧۥ۫ۦۗۗۘۘۧۘۘۘ۬ۨۥۘۨۦۜۘۜ۫۬ۦۢۗ۟ۦۨۘ۫ۤۗ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case -750491485:
                    this.mediaPlayer.pause();
                    str = "۬ۚ۫ۥۤ۟ۧۨ۬ۥۚۛ۟ۤ۠ۖۘ۬۫ۛۦۥ۟۫ۢۘۘۧۜۡ";
                    continue;
                case -177765108:
                    String str6 = "ۥۧۧ۟ۧ۫ۛۜۖۦ۬ۧۖۧۘۘۥۤۦۘۚ۠۫ۚۘ۫ۗۨ۠۟ۜۗۜۦۚۢ۬ۧۗۖۡۖۘ۫ۙۢۧۧۥۘۚۘۡ۟۟ۡۘ";
                    while (true) {
                        switch (str6.hashCode() ^ 1985750854) {
                            case -1571395507:
                                String str7 = "ۖۛۡۘۡۦۦۘۥ۬ۧۘ۠۬ۖۚۦۗۡۗۚۤۜۘۖۥۦۘۦۦۘ۬ۡ۫ۤۡ۫ۗۡ۠ۡۖ۟ۚۥۥۨۢۧۨ۬۠۫ۤۜۚ۟";
                                while (true) {
                                    switch (str7.hashCode() ^ 154558825) {
                                        case -1764576232:
                                            str7 = "ۗۥۥ۟ۡۨۗۛۢۖۛ۬ۜۧۘۖۧۢۘ۠ۚۚۗۥۘۧۥ۬ۘ۟ۥۧۛۡۢۡۘۚۚۚ۫ۙۡۘۜۗۖۦۚۛ";
                                        case 424994483:
                                            str7 = mediaPlayer.isPlaying() ? "۫ۜۧۜ۫ۦۘۢ۠ۥۘۗ۫ۡۘۙ۬ۨۘۚۚۥ۫ۚ۟۟ۤۜۘۥۗۘۧۛۨۘۖۤۗۘۧۢۜۡۖۗۖۘۥۧۧۦۗۥ۟ۖۖۙ۫ۙ" : "۫۬۟ۙ۟۫۟۬۠ۦ۠ۖۥۜۨ۫ۚۧۨۘۥۚۥۘۙۖۨۘ۫ۖۡۘ";
                                        case 727401367:
                                            str6 = "ۨۥۡۘۚۚ۬ۙۧ۫ۧۥۥۘۧۜۚۤۦ۬ۢۨۢ۫ۥۜۘۛ۫ۘۘۖۢ۠";
                                            break;
                                        case 1060939232:
                                            str6 = "۠ۖۢۜۘۧۘۘۚۧۤۚۘۦۗۘۘۥۥ۫ۨۥۡۘ۟ۘۢۧۢۚۡۛۧۜۘۧ۫ۦ۬ۤۡۘۜۘۢ۫ۧۘۘۦ۟ۜۘۦۤۗۥۡۛ";
                                            break;
                                    }
                                }
                                break;
                            case -1310730047:
                                str6 = "ۙۤۥۘ۬ۦ۫ۥۦۥۘۚۤۗۡ۬ۖۘ۠ۜۧۘۙۘ۟ۤۚ۫ۚۚۙ۟ۗ";
                            case -1048336725:
                                break;
                            case -146709126:
                                str = "۠ۜۘۘ۠۟ۖۘۗۢۡۘۧ۫ۧۗۘۜۘ۠۠ۘۘۙۧۘۘۨۤ۟ۚۚۨۙۘۦۥ۫ۗۧۥۜۦۧۘۘۜۥ۫ۦۚۡۘ۟ۢۢۜ۟ۘۢۢۨۘ";
                                break;
                        }
                    }
                    break;
                case 1170002774:
                    str = "ۢۧ۟ۜۤ۫ۦۛۛۙۡۥۘۘۢۦۤۢۙۛۙۘۘۨۘۤۜۛۘۦۙۦ";
                    continue;
                case 2096073748:
                    mediaPlayer = this.mediaPlayer;
                    str = "ۤۤۦۘۧۜۘۛۨ۬ۖۖ۠ۖۘۡۤۗۜۛۨۘۨۖۘۘۢ۬ۘۘۜۧۘۚۜۜ۬ۘۜۚۢۦۧۦ۠ۢۛۨۢۦۤ۠ۢۙۙۢۖ";
                    continue;
            }
            str = "۬ۚ۫ۥۤ۟ۧۨ۬ۥۚۛ۟ۤ۠ۖۘ۬۫ۛۦۥ۟۫ۢۘۘۧۜۡ";
        }
    }

    private /* synthetic */ boolean lambda$playMusic$10(MediaPlayer mediaPlayer, int i, int i2) {
        StringBuilder sb = null;
        String str = "ۚۛ۫ۙۖ۟ۥۛۡۥۨۨۖۘۨۘ۬ۖۡۘ۬ۧۜۘۘ۫ۧۥۡ۟ۧ۠۟ۡۗۙۚۦ۠ۘۙۡۘۢۢۥۘۥ۠ۨۡۥ۟";
        while (true) {
            switch ((((str.hashCode() ^ 365) ^ 49) ^ 562) ^ 1926047445) {
                case -2098366255:
                    str = "ۛۙۥۚۥۗۖ۟ۚ۟ۧۦۘۖۦۥۘ۠۫ۢ۟ۤ۬ۦۦۛ۟ۢۖۘ۫ۖۢۘ۟ۥۘ۫ۙ۠ۙۥۤۡ۬ۜۛۘۜۘ۬ۦۜ";
                    continue;
                case -2048071058:
                    return true;
                case -1921906076:
                    sb.append(this.jsOnError);
                    str = "۬ۦۧ۫ۥۜۥۢۜ۬ۤۙۦۥۜۘۗۚۧۦ۟ۘۘۜۖۜۤۡۜۦۙۤۤۢۖ۫ۖۘ";
                    continue;
                case -1910694082:
                    str = "ۢۚۧۨ۟ۜۘۧۦ۟ۘۖۚۘۗۤۜۖۘۦۖۗ۫ۦۖۘۖۤۥۘ۟ۨۥۦ۫۠ۗۘۖۘۖۤۧۖۥۦۘ";
                    continue;
                case -1863528360:
                    sb = new StringBuilder();
                    str = "ۗۡۛۡۘۡ۫ۢ۟ۗۥۚۛۜۖۘۘۥۚۥۖۧۘۧۥۛۡۚۥۘۙۡۢۚۗ۟۟ۚۜ";
                    continue;
                case -1441776030:
                    String str2 = "ۨۖۙۖۚۙۚ۫۫ۗۥۘۦ۬ۨۘۙۜۧ۫ۙۖۘۙۢ۠ۖۗۧ۬۬ۡۘۡۨ۟ۜۨۨۘۢۤۜۢ۟ۧۚ۫ۢۦۥۧۘ۫ۦۥۘۤۥ";
                    while (true) {
                        switch (str2.hashCode() ^ (-457798572)) {
                            case -1094481119:
                                str = "ۗۡۥۢۚۦۘۤۜۧۘ۠ۦۙ۟ۦۧ۬ۨۙۜۥۚۚۡ۟ۜۛۦۘۥۚۖۚۛۛۨۧۘۘ۟۠ۧۙۙ۬ۖ۟ۤۚۨۨۘ۟ۗۥۖ۫ۗ";
                                break;
                            case -996613950:
                                String str3 = "ۗ۬ۖۘۖۛ۫ۧۥۧۘۖۦۖۘۥۧۘۧ۟۠۟ۜۗۙۛۘۘۛ۠ۙ۫ۢۜ";
                                while (true) {
                                    switch (str3.hashCode() ^ (-2014650538)) {
                                        case -1533624632:
                                            str2 = "ۦۖۢۚۤۡۘۜۨۖۘۜ۬ۦ۫۬ۗ۬ۜۘۘۙۗ۫ۜۖ۬ۜۤۜۨ۟ۨۘ";
                                            break;
                                        case -1105790732:
                                            str2 = "ۘۤۘۛ۟ۖۘۖۥ۫ۚۖۖۛۚۖۘۨۖۧۤ۟ۡۘۤۚۨۘۡۥۙۛۜ۠ۦۙۧ۫ۥۡۘ۬ۙۨۘۨۖۛ۬ۜۘۗۧۛ";
                                            break;
                                        case -828753123:
                                            str3 = mediaPlayer == this.mediaPlayer ? "ۢۚۡۗۥۜ۬۫۫ۘۤۡۘ۬۟۠ۗۘۜۖ۫۬۟ۜۚۛ۫ۗۧ۫ۡۛۧۜۥۡۧ" : "ۖۤۦ۬ۚۡۙۤۤۛۛۦۘۢۗۦۙۚۖۘۨۛۗۗۙۗۚۡۦ۬۫ۧ";
                                        case 189019897:
                                            str3 = "ۨۜ۫ۥۦۚۤۚ۬۫ۜ۬۫ۖۘ۬ۢ۟ۜۡۧ۟ۢۜۘۤۡۥۘ۟ۚۥۘۢۡۧۘۚۘۥۘۗۡۡۘۦۘۡۘۚۡۜۘۡۙۘۘ";
                                    }
                                }
                                break;
                            case -454484756:
                                str2 = "ۙۚ۟ۚۗۗۙۤۡۘۨۥۘ۫ۘۧۛ۬ۡۘۙۖۢۚۙۦۘۦ۫ۜ۠۠ۚ۠ۜۦۘۤۜۗ۫ۖۧۨۘۨۡ۠ۘۘۜۛ۬";
                            case 1234763953:
                                break;
                        }
                    }
                    break;
                case -1255039052:
                    sb.append(l2.decrypt("xQ==\n", "7KfW+aEIKqw=\n"));
                    str = "ۛۗۡۘۡۧۘۧۥۖۘۘ۬ۗۢۢ۫۫۟۠ۜ۟ۦۜۧۤۗۨ۟ۥۘۛۙۥۘ۫ۦۧۘ۫ۜۨۙۨۘۗ۟ۜ۬ۖۘ۠ۖۥۘۙۨ";
                    continue;
                case 370779150:
                    String str4 = "ۨۧۦۘۙۡۡۙۜ۫ۨ۟ۘ۠ۛۗۖۤۜۘ۬ۥۥۘۨۡۦۙۤ۠ۛۜۢۥۚۜۚۧۡۛۡ۠ۨۗۜۘ";
                    while (true) {
                        switch (str4.hashCode() ^ (-1786143024)) {
                            case -1717065919:
                                str4 = "ۖۥۥۡ۟ۖۘۗۡۢۚۡۡۨۙ۟۫۫ۚ۫ۦۘۨۥ۠ۥۙ۬ۦۢۘۘۜۥۙۜۤۤ۫ۖۖۚۥۥ۫ۤۨۘۤۛۦ";
                            case -1147833551:
                                str = "ۚۡۚۨۙۚ۫۬ۥ۬ۚۦۗۨۙۜ۠ۡۘۖۛۘۗۢ۟ۖۥۥ۠ۡۜۘ";
                                break;
                            case 53814411:
                                break;
                            case 683286560:
                                String str5 = "ۚۙۦ۬۬ۚۦۡۜۧۘۙۛۢ۠ۛ۠ۗۡۛۖۗۚ۟ۤۥۨۘۥۦۥۘۦۛۤ۬ۤ۟ۙۦۘۙ۠ۚ";
                                while (true) {
                                    switch (str5.hashCode() ^ (-1846041590)) {
                                        case -977430862:
                                            str5 = !this.disposed ? "ۤۢ۬ۨۘۧۖۤۛۧ۟ۘۘۖ۫ۥۘۙۛۥۘۤۘۘ۫ۚۘۚۗ۠۬ۛ۫۠ۡۜ۫ۜ۫ۚۜۦۘۦۛ۫ۤۜۥۗۘۘۘ۫ۦۖۗۜ" : "۫ۘۢۘۦۦۘۜ۟ۤۢ۠ۙۡ۟ۥۡۧۛ۫۬ۥۗ۫ۤۢۨۡۜ۟۟ۤۨۧ۟ۧۘۘ";
                                        case -530341301:
                                            str4 = "ۛ۬ۡۘۢۙۤۤ۫ۡۘۚۥۖۘۥۛۦۘۤۧۥۘۙۜۡۘ۟ۦ۠ۙۚۨۨ۟۟ۨۤۘۢۜۡۘ";
                                            break;
                                        case -81069484:
                                            str5 = "ۨۡۜۘۚۚۜۗۢ۬ۨۙۤۙۙ۟ۡ۫ۡۘ۟ۛۖ۫ۘۘ۠ۛۥۤۗۥۨۛۥۦۤۡۥۢۜۢۢۡۘ";
                                        case 2001000207:
                                            str4 = "ۧ۠۟ۤ۟ۘۘ۫ۖۨۢۗۖۘۤۧۚۦۘۘۘۧۥۙ۠۬۟ۛۢۛ۫۠ۖۗۦۘۨ۫";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case 1079543420:
                    runJS(sb.toString());
                    str = "۠۫ۘۘۦۦ۠ۜۧ۠ۛۜۚۙۦۧۘۦۛۙۜ۟ۤ۠ۥۧۦۖۘۜۗ";
                    continue;
                case 1754631877:
                    sb.append(l2.decrypt("7g==\n", "xiTexRNNrII=\n"));
                    str = "ۡۜۖۘۡۡۘۘۤ۟ۨۘۡۙۙۧ۟ۜۖۤ۠۟ۘۨۘۙ۬ۧۘۡ۬ۚۛۢۛ۫ۛ۟ۢۚ۟ۧۨۢۧۢ۠ۜۨۛۢۚ۟ۖۘۥۦۡ";
                    continue;
                case 1799548723:
                    str = "۟ۗۧ۬ۙ۠ۢ۬۟ۤ۠ۡۘۛۢۜۨ۟ۦۜۗۥۦۗۖۡۧۘۖۜۜۘ";
                    continue;
                case 1933716608:
                    str = "۬۠۫ۗۚۥ۬ۦۧۤۨۨۧۙۧۘۜۜۘ۫ۜ۬ۥۧۘۦۙۖ۟ۚۡۡۢۨۨ۠ۗۤۙۦ۬۬۬ۨ۫ۦۘۙۖ۫";
                    continue;
                case 1966302061:
                    String str6 = "ۛۖۦۘ۠ۚ۟ۢ۠ۧۧۜۡۡۦۤۖ۠۬ۖۨۘۨ۫ۜۘۨۦۢ۟ۘۙ۬۬۬۠ۨۦۗ۬۬ۘۗۡۘ۠ۢۡۥۚۘ۬ۧۖۦۥ۠";
                    while (true) {
                        switch (str6.hashCode() ^ (-714978801)) {
                            case -1854255023:
                                str6 = "ۨۤ۫ۚۢۖۘۖۡۤۜۜۙ۟ۨۨۘ۬ۡۗۡۘۖۧۧۦۥۧۗ۫ۛ۫";
                            case -470285822:
                                break;
                            case 623917042:
                                str = "ۢ۟۬ۜۥۥۧۦۚۥ۠ۥۘ۠ۧۜۘ۠ۢۦۘ۫ۖۤۚۥۖۡ۬ۡ۠ۘ۬ۧۦۡۤ۬ۦۤۖۙۨۡۘ۟ۧۦۘۗۥۦۗۖ۬۫ۦۖۘ";
                                break;
                            case 1559049389:
                                String str7 = "ۤۖۨ۠۬ۥۧۨۘۦۚ۫۠ۜ۠ۘۤ۟ۡ۫۬ۡۜۙۤ۠ۥۜۛۜۘۖۚۙۛۘۘۘ";
                                while (true) {
                                    switch (str7.hashCode() ^ (-1333002713)) {
                                        case -1022858789:
                                            str7 = this.jsOnError != null ? "ۙۢۤۤۢۦۘۦۗۜۜۧۜ۠ۜۙ۫ۧ۫ۛۙۨۨۦۥۛۜۦۘ۠۬ۡۙۗۖۢۚۡۘۖۚۜۘۤۡۡۘۖۙۡۘ۫ۤۨۘۚۥۙ۟ۥۡ" : "ۥۥۤ۬۟ۡۤۚۢۧ۫۫ۘۙۜۘۘۧ۟ۛۤۦۘۙۚۗۨۡۨ۟ۤۜ۟ۦۥۙۦ۬ۜۛۡ۬ۡۘ";
                                        case 141179099:
                                            str6 = "ۧۡۦۘ۟ۡ۫ۚۡۦ۫۟ۦۘۨ۬ۙۜۧۖۘۦ۫ۡۘۗۗۜ۠ۢۦۢۜۢ۠ۛۦۘۨۖۖۘ۫۫ۥۘۘ۬۬";
                                            break;
                                        case 817402192:
                                            str6 = "ۖۢۨۘ۠۫ۥۢۤۦۛۢۙۙۨۘۘۗۦۧۘۜۨ۬ۢۨۨۜ۟ۢۜۖۜۘۨۦۖ۠ۡۡۘۘۖۢۚۜۧۤۡ۫ۙۖۘۛ۫ۜۡۚ۬";
                                            break;
                                        case 2017742990:
                                            str7 = "ۛۖۡ۬ۨۥۘۥۛۗۗۧۨ۬ۢۙ۟ۗۥۘۨ۫ۦۖۘۡۜۛۧۚۤۗۦۨ۬ۦۘۥۚ۬ۜۗۘۨۛ۠ۖۨۜ";
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case 2101129450:
                    sb.append(i);
                    str = "ۦۗۘۘۜۡۡۤۤۦۘ۬ۨۨۘ۬ۢۖۘۙۚۜۘۡۨۡۘۥۤۢۥۥۛۡۧۜۧۜۘۤۤۨۛ۫ۥۘ۫ۨۖۘ۬۫۬ۢ۫ۖۘ";
                    continue;
            }
            str = "۠۫ۘۘۦۦ۠ۜۧ۠ۛۜۚۙۦۧۘۦۛۙۜ۟ۤ۠ۥۧۦۖۘۜۗ";
        }
    }

    private /* synthetic */ void lambda$playMusic$11(MediaPlayer mediaPlayer, int i) {
        StringBuilder sb = null;
        String str = "ۜ۟ۜۤۡۖۧۛۢ۟۠۫ۡ۫ۚۡۛ۬ۚۡۢ۬ۚۘۙۙۘۘۚ۟ۜۘۥ۫ۨۘۢ۬ۚۧۥۨۢۥۦۙۖۘۖۥ۬";
        while (true) {
            switch ((((str.hashCode() ^ 199) ^ 905) ^ 710) ^ (-772046095)) {
                case -1814803736:
                    sb.append(i);
                    str = "۫ۡۥۤۥۚۗ۟ۘۘۤ۫۫ۗۚۦۖۡۖۘۧ۟ۘۘۤۨۘۨۦۨۤۨۙۧۨۨۘۛۙۧۙۨۜۘۨۜۜۘۜۥۡۘۦۗۡ";
                    continue;
                case -675342453:
                    str = "ۙۖۖۘۙ۠ۖ۬ۚ۟۫ۛۡۘۧۖۖۘۛۖۨۜۚۡۘۚۜ۠ۜۛ۟۬ۖۙ";
                    continue;
                case -634305635:
                    String str2 = "ۡۚۗ۫۬ۘۖۙۥ۬۟ۢ۠ۘ۠۫ۛۦۘۗۤۛۘ۠ۨۨۤۜۘۤۙۥۘ۫ۧۖۜ۠ۖۘ";
                    while (true) {
                        switch (str2.hashCode() ^ 1321924215) {
                            case -2089811371:
                                str = "ۦۥۛۧۚۤۙۖۜۘۙۡۖۘۗۢۡۛ۠ۖۘۧۘۥۗۡۘ۠ۡۗۙۥۘۢۢۥۘۖۖۥۘۛ۟ۥۘۖۢۦۘ۟ۛۢۛۧ۫";
                                break;
                            case -921001795:
                                String str3 = "ۚۚۘۘۧۤۦۦۖ۠ۛۗۥۨۗۖۜۚۘۘۗۢۘۘۘۖۚۛۘۥۘۥۚۦۤ۬ۧ۬ۗ۠ۥۤۦ۫ۡۗ";
                                while (true) {
                                    switch (str3.hashCode() ^ (-2073644452)) {
                                        case -1087431681:
                                            str3 = !this.disposed ? "ۗۤۖۙ۠ۦۘ۠ۨۚ۠ۖۧۘۚۛۘۘۚۖۘۗ۫ۡۧۦۤۧۜۚۧۗۚۙۦۦۘ۬ۨۖۘۧۨۤۥۧۖۙۤۧ۠ۦۘۡۚۨۘۜۛۖ" : "ۧۦ۠ۚ۫ۨۧۤۦ۫ۚۤۤۤۦۘۛ۠ۨ۟ۖۖۙۖۧۘۙۥۦۚۜۘ۬۠۟ۨۛۜ";
                                        case -363937291:
                                            str3 = "۟ۢۨۘ۠ۤ۠ۗ۠۫ۗ۟ۜۨ۬ۙۥۙۙۛۨۜۘۘۖۧۢۧۤۢۡۙ";
                                        case 142768676:
                                            str2 = "ۡۙ۬ۡۤۚ۠ۛۘۘۘۘۢ۠ۘۦۢۧ۫ۖۨۖۥۤ۬ۥ۠۫۟ۖۡۖۨۘ۠۬ۛۛۢۡۗ۟ۡۘۚ۫ۢۨۡۗ";
                                            break;
                                        case 1386408844:
                                            str2 = "ۛ۟۠ۢۦۦۘ۟ۚۡۖۛۜۘ۬ۥۦۘۡۙ۬ۙۘۗۧۜۜۖۛۖۢۦۛ۬ۚ۟ۦۢۢ۟۟ۗ۫ۙۧ۫۟ۖۘۦۨۥۘۗۛۦۦ۟۟";
                                            break;
                                    }
                                }
                                break;
                            case 219879273:
                                break;
                            case 1117249784:
                                str2 = "ۧۛۛۡۚۢۚۚ۬ۛۙۨۛۗۡۢۗ۬ۜۖۙۗۙۛ۠ۛۡۘۡ۬ۗۙۘۛۤۦۘۖۧۙۗۥۥۤۚۗ۟ۘۡۘۗ۠ۧۘۙۧ";
                        }
                    }
                    break;
                case 249238160:
                    str = "ۨۧۘ۫ۨۨ۬ۤۗۨ۬ۖۧۨۡۘۗۙۧۦ۠ۦۘۘ۟ۨۦۥۘۜۦۖۘ۫ۘۨۘۘۢۥۘ";
                    continue;
                case 262420345:
                    sb.append(this.jsOnBuffering);
                    str = "ۜ۠ۛۖۜ۫ۜۗ۫ۡۘۥۗۗۤ۟۫ۚۙۥۡۘ۠ۥۥۘۘۛۛ۠ۙۘۘۧۤۚۙۨۗ";
                    continue;
                case 295438962:
                    return;
                case 365826782:
                    str = "ۨۨۥۨ۠ۘۘۙۤ۠۠ۘ۠ۢۜۘۧ۬ۦ۬ۜ۫ۦۥۘۛۗۛۗ";
                    continue;
                case 789989587:
                    sb = new StringBuilder();
                    str = "۟ۜ۫۠ۧ۬۫ۢۥ۬ۖۗۗۨۤۘ۬ۖۥ۬۬ۥۛۥۘۜۢ۟ۥۡۦۗۘۘۘۤۥۧۘ۠ۤۨۖۤۥ";
                    continue;
                case 1014708609:
                    runJS(sb.toString());
                    str = "۬ۚ۟ۖۛۡ۟ۖۢۖۦۜۘۗ۟ۡۘۥۗۨۘۡ۠ۢۡ۠ۤ۬۠ۙ۬۬ۗۜۤۚۙۘۜۦۦۗۢ۬۠";
                    continue;
                case 1059140895:
                    sb.append(l2.decrypt("Qw==\n", "aiTVBGU+M2A=\n"));
                    str = "ۢ۫ۘۗ۟ۖۜۨ۬ۙۦۧۘۜۛۚ۬ۨۜۨ۫ۙۛ۬ۧۗۤۦۧۡ۫ۧۧ۟۟۟۟ۘۜۥۧۡۤۖۛ۠۫ۡۤ";
                    continue;
                case 1136615554:
                    String str4 = "ۦۥۨۘۛ۬۟ۤۚۥۙۡۖۘۨۥۥۘۢۜۥۘۛۨ۟ۖۤۖۗۢۚۢۤۤۚ۬۫۠ۗۡۘ";
                    while (true) {
                        switch (str4.hashCode() ^ (-1762539983)) {
                            case -1798979490:
                                str4 = "ۘ۬ۡ۬۟ۗ۠۬ۜۘۙۜۨۘۙۜۘۘۛۥۢۥۘۚۚۘۘۥۡۙۘۡۦۙۡ۟ۜۚۦۛۡۦۘۥ۠ۦۘۛ۬ۛۙۦۨۘ";
                            case -994035287:
                                break;
                            case -505652406:
                                str = "ۥ۬ۜۘۘ۫ۖۘۦۢۖۗ۟ۨۘۖۗ۟ۖ۠ۛ۫۫ۗۨۧۥۘۦ۫ۜۗۥۨ۫ۖۡۨۧۨۙ۟ۦۢ۠ۖ";
                                break;
                            case 655303638:
                                String str5 = "۫ۙۥۘۧ۫ۡۘۧ۬۟ۦۤ۟ۥۨ۠۟ۘۘۨۘۤۦ۟۫ۦۨۘۧۙۢۘۛۨۘ۟ۘ۬ۙۦۤۧۘۜ";
                                while (true) {
                                    switch (str5.hashCode() ^ 657169020) {
                                        case -1621680127:
                                            str4 = "ۜۗۜۘۗۚۨۘۙۜۧۘۛ۟ۢۖۥۙ۬ۡۧۘۢۡۡ۫ۢۚۛۗ۠۫ۛۜۘۧۥۥۜۖۡۘۡۗۘۚۜۙۤ۠ۧۦۘۘۘ";
                                            break;
                                        case -1048077333:
                                            str5 = this.jsOnBuffering != null ? "۬ۚۗۤۢ۟ۗ۫ۛۦۥۦۘۚۜۤۦۦۘۛۗۜۤۥ۫ۡۡۙۧۜ۬ۘۥۙۘۖۥۘۢۙۛۨ۟ۚۗۛۛ۫ۜۚ" : "۫ۘۤۘۖۖۘۚۨۥۘۛۚۛۥۤۜ۠ۜ۬ۦۗۥۘۖۨۦۘ۫ۢۘۘۤۡۥۥۥۗۦۧۥۡۗۥۢۥۘۦۧۧۥۥ۠";
                                        case 528781708:
                                            str5 = "۬ۤۦۘ۟ۘۜۘۘۦۘۙ۟ۜۘۜۛۚۧۖۡۥۡۥۘ۟ۧ۫ۦۘۨۜۛۙ۫ۘ۟ۤۚۦۘۡۡۜ۟ۗۢۨۤۖۘۖ۠ۙۤۡۡۘۙۥۘۘ";
                                        case 1542008227:
                                            str4 = "ۗۚۛۛۨۨۘۘ۫ۖۘ۠ۡۥۘۧۗۨۘۙۚۚۙۡۦۘۡۥۛۡۜۛ۟ۥۘۘۢۦۤۢۜۖۘ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case 1450097584:
                    String str6 = "ۚۖ۫ۥۗۡۦۧۛۨۗۘۛ۠ۢ۠ۤۘۡۢۨۡ۬۫ۗۢۦۘۡۨۛۧۡۨ۬۠ۦۘ۠ۗ";
                    while (true) {
                        switch (str6.hashCode() ^ 1318244090) {
                            case -1248502410:
                                str6 = "ۗ۬ۦۘ۟۟ۙۜۙۥۙۜۧۨۙۚۚۡۨۘ۬ۜۙۢۡۧۘ۟ۗۤ۬ۘۘۖ۠۠ۢۚۤۛۨۘۡۗۘ۫ۥۚۢۥۛ";
                            case -239629615:
                                break;
                            case 147402742:
                                str = "ۜۖۛۗۛۨۤۛۢ۠ۚۛۘۙ۫ۤ۠ۛ۟ۥۛۦۙۗۤۥ۟ۥۖۘۛۦۗۜۙۙۨ۫ۥۙۙۘۘۙۘۘۙۜ۫";
                                break;
                            case 1495434185:
                                String str7 = "ۜۨ۠ۜ۫ۥۛۚۢۨۥۚ۟۠ۜۘ۫۟ۤۧۧۥۛۥۖۘۘۚۦۘ۠۠ۖ";
                                while (true) {
                                    switch (str7.hashCode() ^ 1587738425) {
                                        case -2070953220:
                                            str6 = "ۡۨۛۢۨۧۗۗۦۘۜۗۨ۠ۥ۠۬ۘۜۘۧۨۚۨۙۥۘۤۧۡۘۢۛۧ";
                                            break;
                                        case -285759792:
                                            str7 = "۬ۚۦۘۧۘۘۨۗۙۙ۠۫ۜۘۘۨۗۤ۬ۖۜۗۘۜۘۧ۫۟ۜۗ۫ۛۦۚ۠ۨۘۢۛۨۜۡۘۛۦۡۘۢۥۙ";
                                        case 28722801:
                                            str7 = mediaPlayer == this.mediaPlayer ? "۟ۚۢۖۚۖۘۡۜۘۢ۬۫ۧ۠ۘۘۜۥۧۘ۫ۙۧۢ۬ۤۢۡۤ۬ۦ۟ۢۨ۠۫ۘۖ۫ۥۡ۬ۧۗۨ۠ۗۙۢۨ" : "ۢ۫ۧ۟ۘۢ۠ۥۥۤۢۛۨۖۤ۫ۡۦۘۘۗۦۘۙۥۜۜۖۧۘۢۦۥ";
                                        case 1182844308:
                                            str6 = "۫۬۟ۢۖۚۜۙۥۘۢۤ۬۬ۧۦۜۘۘۘۜۦۘۚۦۦۘۤ۫ۥۘۚۥۢۛۥۜۡ۟ۗۖۖۘۘۡ۫۟ۖۚۚۨۧۘ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case 1702528754:
                    sb.append(l2.decrypt("3A==\n", "9CGkVQxsgSw=\n"));
                    str = "ۧ۠ۜۘۛۧۡۦۛۦۘۘۡۚۜۖۘۧ۟ۥۨۙۘۤ۬ۛ۫ۤ۬۫ۦۚ";
                    continue;
            }
            str = "۬ۚ۟ۖۛۡ۟ۖۢۖۦۜۘۗ۟ۡۘۥۗۨۘۡ۠ۢۡ۠ۤ۬۠ۙ۬۬ۗۜۤۚۙۘۜۦۦۗۢ۬۠";
        }
    }

    private /* synthetic */ void lambda$playMusic$12(String str) throws IllegalStateException, IOException, SecurityException, IllegalArgumentException {
        String str2 = "ۚ۫ۗۢۧۗۨ۬ۜۛۖۘۧۤۖۘۘۨۨۢۧۡۗۜۗۨۖۦ۠ۗۖۡ۫ۘۙۧ";
        while (true) {
            switch (str2.hashCode() ^ (-2068827824)) {
                case -865185054:
                    str2 = "ۥ۠ۦۘ۠ۨۦۘ۫۟ۥۙۘۗۘ۫ۡۢۥۧۧۙۘۥۚۘۘ۠ۥۧۘ۬ۡۦ";
                    break;
                case -70655513:
                    String str3 = "۬ۤۘۦۖۡ۬ۨۥۘۡ۫ۗۚۨۘۘ۬ۦۖۧ۬ۜۨ۠ۥۘ۫ۘ۬۟ۙۙۥۤۡۘ۠ۚۜۛۥ۫ۧ۫ۛ";
                    while (true) {
                        switch (str3.hashCode() ^ 780716021) {
                            case -2019048904:
                                str2 = "۟ۙۜۘ۫ۢۨۘۜۖۢۨۢۗۗۚۖ۬ۡۙ۬ۗۗ۠ۥۖۘۚ۬ۘۡۙۦۨ۠ۗۙۜۘۘۗۖۘۨۗۜۘ۟ۢۗ۠ۙۖ۟ۧۘۚ۠ۥ";
                                continue;
                            case -1339764652:
                                if (!this.disposed) {
                                    str3 = "ۤ۬ۢۡۛ۬ۦۥۜۛۢۧۛ۟ۜۘۦۢۙۥۢ۠ۦۚ۟۠ۙۘۘۙۙۙۜۢۚۥۖۧۧۛۜ۫ۖۧ";
                                    break;
                                } else {
                                    str3 = "۟۫ۘۘۘ۟ۚ۟ۛۘ۠ۤۥ۫ۧ۫ۡۡۧۢۦۦ۠ۡۥۨۚۗۘ۫۬۬ۛۗ۟ۨ۬ۦۙۤ۬";
                                    break;
                                }
                            case -238625083:
                                str3 = "۬ۜۧۨۨ۫ۥۥۖۘۜ۟ۤۖۢۨۘ۟ۨۖۜۤۖۘۥۗ۠ۥۥۜ۠۠ۡ۬ۡۘ۠ۡ۬ۘۦ۟ۖۢ۠ۘۚۘۤۧۖۘ";
                                break;
                            case 1920219673:
                                str2 = "ۡۖۜۚۛ۟ۗ۠ۛۡۚۛۘۡ۬ۙۧۧۦۤۢۡۙۤ۟ۚۧۛۛۦۘۢۛۘۧۥۜۘۥۥۛۚۤ۟۟ۙۡۘ۬ۘۜۤۦۨۚۡۘ";
                                continue;
                        }
                    }
                    break;
                case 216791444:
                    return;
                case 617740160:
                    try {
                        stopAndReleaseMusic();
                        MediaPlayer mediaPlayer = new MediaPlayer();
                        this.mediaPlayer = mediaPlayer;
                        mediaPlayer.setAudioStreamType(3);
                        this.mediaPlayer.setDataSource(str);
                        this.mediaPlayer.prepareAsync();
                        this.mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener(this) { // from class: core.pro.android.notify.c0
                            public final JsInterface a;

                            {
                                this.a = this;
                            }

                            @Override // android.media.MediaPlayer.OnPreparedListener
                            public final void onPrepared(MediaPlayer mediaPlayer2) throws IllegalStateException {
                                String str4 = "ۚۙۖۘۛ۟ۖۧۛ۠۬ۢۨۘۖۡۙۜۛۚۧۧۥۗۨۢۥۗۘۧۙ۫۟ۛۥۘۖ۠ۘۘۛ۬۟ۡۘۜۘۦۖۦۘۤۛۥۘ۬۫ۥۘۗ۫ۜۘ";
                                while (true) {
                                    switch ((((str4.hashCode() ^ 553) ^ 632) ^ 816) ^ 109547834) {
                                        case -2043244891:
                                            JsInterface.m(this.a, mediaPlayer2);
                                            str4 = "ۨۡۜۘۙۡۜۘۚۢۙۢۨۧۘۚۚ۟ۤۘ۬ۡۤۢ۫ۥ۫۠ۜۘۘۤ۟ۚۥۙ۬۫ۗۤۖۨۨ۠ۢۚۥۚۡۘۗۗۙ";
                                            break;
                                        case -1074704216:
                                            str4 = "ۘۧۨۘۙۡۥۖۨۨۦۜۖۘ۟۫۟ۜۡۜۘۥۦ۟ۡۘۢۛۗ۠ۚۧ۬ۜۥۘۥ۫ۧ";
                                            break;
                                        case 789531410:
                                            str4 = "۟۫ۡ۫ۙۘۘۗۡۡۧۢۡۙۦۨۘۦۧۡۘ۫ۧۦۨۗۡۨ۫ۘۘۚۙۤ";
                                            break;
                                        case 1984724345:
                                            return;
                                    }
                                }
                            }
                        });
                        this.mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener(this) { // from class: core.pro.android.notify.d0
                            public final JsInterface a;

                            {
                                this.a = this;
                            }

                            @Override // android.media.MediaPlayer.OnCompletionListener
                            public final void onCompletion(MediaPlayer mediaPlayer2) {
                                String str4 = "ۛ۠ۤ۫۫ۧۚۗۘۘۙ۠ۥۘۢۦۖ۠ۜۧۥۧۗۚ۟ۥۥۜۘۖۨۘۜ۟ۘۦۖ۟ۛۢۙۚۡۜۘ";
                                while (true) {
                                    switch ((((str4.hashCode() ^ 166) ^ 903) ^ 212) ^ (-2074001784)) {
                                        case -1833309230:
                                            return;
                                        case -1421416690:
                                            str4 = "ۖۦۘۜۘۥۢ۬ۥۘ۟۫ۖۙۦۘۜۜۜ۫ۥۨۘۧۨۙۜۦۚۘۡۡۘۢۦۧۨۛۤۙ۫ۡۦۧۧۖۤۘۘۤۛۨۘ";
                                            break;
                                        case 1041184580:
                                            str4 = "ۢۗ۟۫ۘ۟ۥۘۘۗۙ۟ۢۗ۠۟ۨۥ۬ۗۘۡۢۜۘ۟۠ۤۨۨۨۘۖۨۜۛۚ۬ۦ۫۠۟ۗ۟۠ۜۘۧ۫ۖ";
                                            break;
                                        case 1365860002:
                                            JsInterface.l(this.a, mediaPlayer2);
                                            str4 = "ۘۜۖۖۥۘۘۛۛ۠ۚۛ۬۠ۦۨۘۚۗۤۡۡۤ۟ۥ۫۟ۥۙۧۚ۫ۛۙۢۚۦۢ۠۟ۜۘۡ۬ۡۘۙ۫ۤ۫ۨۜۘۢ۟ۥۘۛۗ۬";
                                            break;
                                    }
                                }
                            }
                        });
                        this.mediaPlayer.setOnErrorListener(new MediaPlayer.OnErrorListener(this) { // from class: core.pro.android.notify.t
                            public final JsInterface a;

                            {
                                this.a = this;
                            }

                            @Override // android.media.MediaPlayer.OnErrorListener
                            public final boolean onError(MediaPlayer mediaPlayer2, int i, int i2) {
                                String str4 = "ۦۦ۠۠۠ۙۖۦۨۘۡۜ۟ۜ۠ۘۜۨۧ۟ۤ۫ۜۜۦۘۦ۬ۖۛۗۜۛۖۢۤۨۨۘۙ۠ۡۖۧۦۖۘۙۚۡۡۘ";
                                while (true) {
                                    switch ((((str4.hashCode() ^ 569) ^ 410) ^ 133) ^ (-2067956788)) {
                                        case -1428571671:
                                            str4 = "ۚۨۧۤۛۤۘۦ۠ۘۡ۬ۤ۬ۜۘۚۡۜۘ۫ۗۥۘۡۥۡۘۡۢۥ۠ۤۘۘ";
                                            break;
                                        case -660624914:
                                            str4 = "۠ۜۘۘۡۥۧۖۧۦۛ۫ۥۘ۠ۗ۠ۨ۟ۨۘۦۘ۬ۥۖۙۘۗۖۥۤ۫ۢۚۜۛۦۘۘ۫ۦۧۤ۫ۖۤۜۜ۬ۗۢ";
                                            break;
                                        case 124778984:
                                            str4 = "ۡۖۜۘۘۥۚۗۦۘۘۤۡۢۚۖۦ۠ۥ۟ۢۗۖ۬ۜۘۖۡۖۘۛ۬ۜۘ";
                                            break;
                                        case 404925153:
                                            return JsInterface.q(this.a, mediaPlayer2, i, i2);
                                        case 471769823:
                                            str4 = "ۥ۟ۖۘ۬ۨۘۚۛ۠ۢۨۡۘۤ۟ۖ۫ۡ۠ۢ۟ۢۧۥۡۜۖۚۚۥۦ";
                                            break;
                                    }
                                }
                            }
                        });
                        this.mediaPlayer.setOnBufferingUpdateListener(new MediaPlayer.OnBufferingUpdateListener(this) { // from class: core.pro.android.notify.u
                            public final JsInterface a;

                            {
                                this.a = this;
                            }

                            @Override // android.media.MediaPlayer.OnBufferingUpdateListener
                            public final void onBufferingUpdate(MediaPlayer mediaPlayer2, int i) {
                                String str4 = "ۥۥۖۘۘۖۜۘۛۙ۬ۥ۫ۦۦۛۤ۟۫ۢۗ۬ۨۛ۫ۥۘۥۚ۬ۙۡۘۗۚۖۘۜۙۖۧۖۜۘۧۢۦ";
                                while (true) {
                                    switch ((((str4.hashCode() ^ 139) ^ 451) ^ 559) ^ (-1201698334)) {
                                        case -923134131:
                                            JsInterface.c(this.a, mediaPlayer2, i);
                                            str4 = "ۦۧۦۘۘۤۥ۬ۡۜۘۦۖۗۥ۟ۡۘ۫ۘۜۘۥۚۧۚ۠ۧۛۥۘۧۘۚ۬۬۠۫ۥۘ۟ۥۖۘ۠۫ۤ۫۬ۨۚۨۡ";
                                            break;
                                        case -750210040:
                                            str4 = "ۢۚۦۧۨۛ۬ۘۥۦۛ۠ۚۧۡۘۘۧۨۥۦۡۧ۟ۨۡۘ۟ۛۡۚۦۖ۟ۘۛۘۘۡۘۡۘ۠۠ۨۡۤۖۚۡۧ";
                                            break;
                                        case -712573187:
                                            str4 = "ۢ۟ۘۘۛۜۡۤ۫ۨۛۖۙ۫ۗ۠ۛۢۖۘۘ۫۫ۚۛۗۨۦۛ۫ۖ";
                                            break;
                                        case -230192818:
                                            return;
                                        case 1853311619:
                                            str4 = "ۡۘۜۧۦۜۘ۫ۦۙۥۡۚۚۨۡۘۛۥۥ۬ۙۘ۬ۛۜۘۘۛۡ۠ۥۚ۫ۤ۠ۜۙۤۚۤۥۘۙۢ۫ۙۧۦۘ۠ۜۘۘۘۘۖ۬ۡۙ";
                                            break;
                                    }
                                }
                            }
                        });
                        return;
                    } catch (Exception e) {
                        e.printStackTrace();
                        return;
                    }
            }
        }
    }

    private /* synthetic */ void lambda$playMusic$8(MediaPlayer mediaPlayer) throws IllegalStateException {
        String str = "ۢۢۙۗۚۛۙ۟ۖۨۦۥۘۗ۟ۡۘ۟ۦۖۖ۟ۖۘ۠ۡۡۘۘۤۦۘۙۙۜ۬۠۠ۤۖۧۘۤۥۜۗۦۘۘ";
        while (true) {
            switch ((((str.hashCode() ^ 239) ^ 722) ^ 547) ^ 1168318499) {
                case -1949493155:
                    String str2 = "۟۠ۡۘۨ۫ۚۖ۬۠ۥۖ۬ۨۧۨۘۤۡۡۘۗۦۜۧۦۥۨ۠۟۠۬۬ۥۖۤۡۢۙۜ۟ۖۗ۬ۖۘۖۖۛۤۜۜ";
                    while (true) {
                        switch (str2.hashCode() ^ 503532519) {
                            case -263548521:
                                break;
                            case 66056889:
                                str2 = "۬۠ۨ۠ۛۚۥ۬ۗۡۧۤ۠۬ۧۧۤۖۦۘۧۚۤۘ۠ۙ۟ۛ۬ۖۘۙۛۤۛۧ۬ۗ۠ۖۘ۠ۙۘۘۜۤۘۘۦۗۘ۟ۡ۫ۖۘۨۘ";
                            case 1199770304:
                                str = "ۚۛۘۘۚۘۛۗ۠۟ۨ۬ۜۥۛۡۙۦ۟ۜۚۛۖ۟ۛۗ۫۠ۦ۟ۖۤۖۦۥۨۢ۬۫ۗ۬۠";
                                break;
                            case 2054494318:
                                String str3 = "۠ۨۦۛ۬ۜ۟ۛۚۜ۬ۡۘ۫ۧۗۜۤۚۖۘۡۨۢۨۡۦۛۦۡۖۙۚۚ۠ۡۧۤ۟ۢۢۡۚ";
                                while (true) {
                                    switch (str3.hashCode() ^ (-1457837154)) {
                                        case -1863411622:
                                            str3 = !this.disposed ? "ۖۥۨۘۘۥۜۘۢۨ۠۟ۖۨۤۧۜۘۘۚ۫ۨۥۥۘ۟ۢۗۧۡ۫ۛۡۘۘ۟ۨۘ۫۠ۛۖ۬ۥۘۛۨ۬" : "ۦۖۖۢۜۨ۟۫۠ۛۧ۬ۥ۬ۥۘ۠ۛۚ۫ۨۨۘۘ۫ۜۘۨ۠ۥۘۦ۠ۢ";
                                        case -498587527:
                                            str2 = "ۧ۟ۡۘۡۖ۬ۖۥۥۦۛۡۘۦ۫ۢۦۨ۫ۚۥۘۙۨۨۘ۬ۡۘۘ۬ۗۖۢۘۗۤۛۦ";
                                            break;
                                        case 495195022:
                                            str2 = "ۛۗۥۘۦۢۥۘۚۡۥۘۘۡۘۘۤ۫ۡۘۤ۠ۖ۫۬ۨۗۙۨۘۨۢۜۘ۟ۛ";
                                            break;
                                        case 1383828191:
                                            str3 = "ۘۧۜۘۗ۠ۦۘۦۨ۠ۘ۬ۜۡۘۘۥ۠ۘۦۙۘ۠ۖۘۚ۬ۡ۠ۨۥۧۖۘۘۙ۬ۤۜۤۘ۟ۧ۫ۢ۫ۚ۬ۛۚۦۧۘ۬ۜۖ";
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case -1511628664:
                    this.isPrepared = true;
                    str = "ۢۛۡۘۧۡۗۧۥۘۢۚۡۘۦ۟ۚۚۧۡۘۨۢۤۘۤۥۘۤۘۦۢۤۚ";
                    continue;
                case -920241017:
                    mediaPlayer.start();
                    str = "ۗۢ۟۠ۘ۫ۥۙ۫ۡۗۢۢۧۖۙۖۗۛ۟ۥ۬۬ۥۘۙ۬ۢۙۥۙ";
                    continue;
                case -5156203:
                    str = "ۨۡ۟۫ۖۗۥۖۙۧۤۚۢ۟ۤۛۤۧ۟ۗۛۨۨۘۧۚۦ۠ۥۦۦۨۥۙ۬ۜۘۗۗۛۜۦۡ۬۠ۢۦ";
                    continue;
                case 1762771276:
                    str = "۫۟ۤ۟ۜۛۦۧ۬ۨۗۘۘۢۙۙۢۦۦۘۘۛ۟ۧۢۙۤ۟ۤ۫ۡۨۘۥۥ۬ۙۖۘۤۛ۬ۤ۬ۥۘۨ۠ۨۤۡ۟ۚ۬۠ۙۨ۫";
                    continue;
                case 1949657371:
                    return;
                case 2053088299:
                    String str4 = "ۧ۬ۧۨۚۡ۫ۤۤۘۦۘۘۡۙۜۘۙۛۖۧ۠ۖۨۧۘ۠ۦۡۘ۬۫ۜۙۡۢۚۘۘ۫۫ۖۘۢۢ۫ۛۖ۠۫۠ۥۘۨۨۦۘۦۜۥۘ";
                    while (true) {
                        switch (str4.hashCode() ^ 377979228) {
                            case -31351276:
                                str = "ۘۘۗۨۧۥۘۤۥۥ۟۫۫ۖۥۥۦۢۚ۬ۜ۫ۤۙۚۤ۬ۛ۠ۡۘ";
                                break;
                            case 82992490:
                                break;
                            case 301119170:
                                str4 = "۠ۖۛۚۤۢۨ۬۫ۦۤ۫ۜ۟ۙۦ۫ۘۚۙ۫ۛ۠۬ۜۥۨۤۘ۟ۤۤ۟ۙۖۢۛۜۗۗۡ۟ۥۘ۫ۡۖ۠ۜۧۘۖ۬ۖ";
                            case 420761267:
                                String str5 = "۬ۙۦۘۛۛۨۘۦ۟ۗۖۡۡ۬۠۬ۤۗۥۛۡۢۨۗۦۘۡۖۘۧۦۘۘ۫ۙۖۘۡ۠ۖۛ۠ۥ۬ۦ۠";
                                while (true) {
                                    switch (str5.hashCode() ^ 134164432) {
                                        case -2058222889:
                                            str5 = mediaPlayer == this.mediaPlayer ? "۬ۡۛۙۗۜۧۜۨۨۤۚۘۗۙ۬ۚۨ۟ۧ۫ۗۤۜۦۗۧۚۛۢۨۧۘۛۙ۠ۜۙۚ۠ۘۢۥۙۦۘ۫ۖۚ" : "۟ۢۨۘۙۨۜۘۗۡۨۘۦۨۜۘۧ۟ۜۘۡۜۙۤۨۛۢۧۘۘۚۚۦۘۗۦۘۡۗۛۙۘۜۘ";
                                        case -842961565:
                                            str4 = "ۙ۟ۡۘ۠ۧ۫۫ۘۦۘۗۡۢۛۙۦۘ۟ۜ۬ۚۢۧۦۧۙۗۛ۬ۥ۠ۛ";
                                            break;
                                        case -59651967:
                                            str4 = "ۥ۠ۥۜ۟ۘۨۗۖۢۖۜۘۤۖۨۤۤۗ۠ۛۨۤۧۗ۠ۜۧۘۛۧۥۘۥۧۤۧۜۤۧۡۛ۫ۙۦۘۤۗۦۘۛ۫۟";
                                            break;
                                        case 2125962057:
                                            str5 = "ۢۨۜۧۢ۫ۧ۫ۛ۬ۥ۫ۨۙۘۘۖۚۥۘ۟ۨۨۘۦۚ۟۠ۢۜ۠ۥۗۜۤۖۘۖۗۤۦۨۧۡۛ";
                                    }
                                }
                                break;
                        }
                    }
                    break;
            }
            str = "ۢۛۡۘۧۡۗۧۥۘۢۚۡۘۦ۟ۚۚۧۡۘۨۢۤۘۤۥۘۤۘۦۢۤۚ";
        }
    }

    private /* synthetic */ void lambda$playMusic$9(MediaPlayer mediaPlayer) {
        StringBuilder sb = null;
        String str = "ۡۜ۠ۤۥۖۜۛۖۘۦۨۛ۟ۦۦ۟ۗۖۧ۬ۥۤۖۘۜۢۨۘۗۚۦۘ۬۫ۧۚ۠ۨۗۜۡۖۤۥۧ۟ۙۙ۬";
        while (true) {
            switch ((((str.hashCode() ^ 462) ^ 413) ^ 491) ^ (-1609756025)) {
                case -2065529158:
                    String str2 = "ۘۧۖۘۨۨۘۢ۫ۚۦۨۨۘ۬ۤۜۨ۬ۚۡۙۡۡۛ۠ۦ۫ۚۚۘۜ۟ۨۜۘۘ۫ۨۘ۟۠ۦۘۥۨ۫ۥ۬ۘۘۛۘۧ";
                    while (true) {
                        switch (str2.hashCode() ^ 1943663739) {
                            case -2145746307:
                                str = "ۙۙۤۡ۬ۜۚ۬ۘۘ۟ۛۗۡۦ۬۟ۜۡۘۦۢ۬ۚۦۦۘۥۛۦۘۘۢۛۧۘۘۘۘۜۦ۫۠ۚۚۜۤ";
                                break;
                            case -1678554231:
                                str2 = "۟ۢۘۘۘۖۤۘۗۛۡ۠ۡۡۨۧۦۨۡ۟ۙۤۧ۟ۘۘۗۧۜۘۦۡۧ";
                            case 386598458:
                                break;
                            case 1179138449:
                                String str3 = "ۨۖۜۘ۠۫ۖۦ۠ۥۤۨۘۘۜ۬ۘۜۧۖۦۨۡۘۙۡۡۘۦۡۨۢۦۦۨۡۜۘۨۥۘۤۥۚۢۥۗۧۦۘۖۧۨۘ";
                                while (true) {
                                    switch (str3.hashCode() ^ (-893648554)) {
                                        case -1916493088:
                                            str2 = "ۜ۟ۥۘۡۤۡۘ۬۠ۦۘۘۦۥۘۗۦۦۜۙ۬ۛۗۡۚۛۖۘۛ۬ۜۘ۟ۨۦۘ";
                                            break;
                                        case -1179036831:
                                            str3 = this.jsOnEnd != null ? "ۘۡۨۘۚ۟ۚۧ۬ۖۘ۟ۜۖۥ۟ۜۘۧۖۨۘۖۨۗۙۤۧۤۥۧۘۛۘۘۡۢۨۘۗۨۜۘۙۧ۫ۜۤۥ۫ۡۚۛۤۡۘۥۦۡۤۦۚ" : "۬ۢۗ۫ۗۥۘۜۙ۠ۚۨۤۡۛۡۦۨۡۤۦۡ۟۫ۖۘ۬ۨ۟ۦۖۗۘۘۖۘ۠ۗۥۘۡۛۧۨ۟ۥۘۨۚۘۘۘۡۖ";
                                        case -723853823:
                                            str3 = "۠ۤۜۘۘۦۤ۟ۜۦۧۦ۬ۢۗۜۗۗۢۡۨۥۘۚۥۖ۟ۗۤ۠ۖۡۘۦۗۛ۫ۚۨۘۤۢۚۙۙۦۜۡۜۘۨ۟۫ۖۜۖۘۤۡۤ";
                                        case 1933272244:
                                            str2 = "ۛۥۗ۬ۤۤۦۛ۟ۧۚۙۘۦۛۜۗۜۘۨۢۖۘۧۗۡۘۖۚۘۘۤۤۖۘۚۥۗ۟ۨۘۨۖۙۢۛۡ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case -1049087912:
                    str = "ۧۥ۟ۢ۟ۖ۫ۧۥۘ۠ۧۡۤ۠ۗ۟ۤۥۘۥۤ۬ۜۦۥۙۜۥۡۙ۬ۚۘۤ۬ۨۡۙۢۨۦۦۚۘۥۜۘ۬۟ۦۜۨۘۚۙ۫";
                    continue;
                case -922492785:
                    str = "ۙۧۖ۠ۛۜۗ۟ۢۨۘۡۖۗۡ۠۫ۥۘ۠ۤۜۨۨۜۘۗۤۨۦۛۘۛۧۡۤۛۥۘۖۡۙۚۤۚۜۙۙۧۘ۬";
                    continue;
                case -887366229:
                    sb = new StringBuilder();
                    str = "ۖۦۘۥ۟ۤۤۜ۬ۦۦۦۘ۬۬ۦۘ۠ۙۜۤۦۡۘۘۚۘۥۗۗۚۛ۟ۡۜۧۘ۟ۥ۬ۖۨۖۘۗ۠ۥ۠ۨۖۘۥۥۗ۫ۛۜۘۨۨۙ";
                    continue;
                case -618312684:
                    sb.append(l2.decrypt("v8k=\n", "l+DdpI/63GQ=\n"));
                    str = "ۙۘۚۛۜۤۚۙۢۙۥۧۘۙۥۜ۠ۗۚۨ۬ۛۜۥۨۥۘۘ۟ۖۚۢۜۛۚۖ";
                    continue;
                case -96361491:
                    String str4 = "ۙ۬ۛۛۜۨۥۢۤۗۖۢۜۙۡۘۛۗۘۘۡۜۥۤۛۤۘۘۘۖ۠ۚ";
                    while (true) {
                        switch (str4.hashCode() ^ (-1807971157)) {
                            case -1778770963:
                                String str5 = "۫ۛ۫ۡ۬۫ۙ۟ۢۢۧۙۤۛۘۛۚ۠ۖ۬ۡۛۙۗۙ۬ۗۡ۬ۙۘۨ۬ۘ۠ۦۖۖۘۘۨۖۜ";
                                while (true) {
                                    switch (str5.hashCode() ^ 1380305800) {
                                        case -1562686753:
                                            str5 = !this.disposed ? "ۜۨۢۥۤۥۚۚ۬ۛۛۜۤۡۛ۟ۖۖۘۛۛۥۥۨۨۢۤۡۘ۬۠ۡۘ۫ۘۥۧۛۨۘ۟ۜۛ۟ۥۢ۫ۥ۫ۜۤ۬ۡ۫۟ۘۦ" : "ۖۙ۠ۧ۟ۨۘۚۡۖۛۦ۬ۢۡۥۘۙۥۜۘ۬ۗۥۘۖۖۦۘۦۨۧ۫ۛۢۤۡۥۤۡ۟۫ۤۜۘۥۧۙۡ۟ۢ۟ۚۚۛۤ۫ۧۙۜ";
                                        case -385008382:
                                            str4 = "ۖۚۙۥۜۜۘۨ۠ۘۘۨۛ۠ۥۛۤۧ۫ۤۧۥۜ۠ۦ۟ۨۨۡۘۢۗۙ۫ۡۚۡۚۡۘۗۜ۬ۘۘۘ";
                                            break;
                                        case 100844089:
                                            str5 = "ۛ۫۠ۘۚۨۢۨۥۢ۫ۗۜۧ۠ۛۡۘۘۦۢۛۛۤ۫ۙۜ۬ۦۡۘۜۡۗ۠۟ۛۨۨ۫۠ۜۚ";
                                        case 1461022069:
                                            str4 = "ۢۨۧۧۚۜۢۚۦۘ۠ۨۘۨۢۛ۬ۗۖۜۥۘۦۜۘ۬ۖ۟ۢۧۘۘۥۚۥۘۖۚۖۗۙۨۘۡۢ۟ۤۦۡۘ۟ۨۦ";
                                            break;
                                    }
                                }
                                break;
                            case 177349561:
                                str4 = "ۚۘۗ۠ۦۖۛ۫ۦۘۤ۟ۚۧۖۖۘۢۗۡۗۖ۠ۢ۫ۥۘۤ۫۠ۨۜۡۘۖۘۙۗ۫ۚ";
                            case 675519456:
                                str = "ۦۦۦۥۡۢۤ۠ۥۘۚۖۡۘۜۘ۠ۧۗ۠ۧۤۧۧۖ۟ۥۛ۟ۤۖۜۖۘۥۧۘۘۦۚۥۥۥ۠";
                                break;
                            case 1991061896:
                                break;
                        }
                    }
                    break;
                case 1549484279:
                    String str6 = "ۖۚۥۜ۟ۛۗۜۜۘۤ۬ۦۨۘۜۘۦۘ۫ۤ۟۬ۢۚۛۛۚۥۨۢ";
                    while (true) {
                        switch (str6.hashCode() ^ 1707950942) {
                            case -2111099653:
                                str = "ۢۢۙۤۗۘۘۦ۠۟ۖۗۜۘ۟ۥۦ۟ۘ۫ۜۛ۟ۤ۬ۗۙ۫ۢۖۥ۬ۖۢۘۘۛ۫۠ۗۨۙۦۡۘۤۢ۠ۚۖۜۘ";
                                break;
                            case -1228854120:
                                break;
                            case -669460391:
                                String str7 = "۠ۦۗۖۢ۟ۛۢۖۖۢۥۘۙ۫ۡۧ۠ۥۘ۠ۥۦۡۢۥۛۤۙۦۚ";
                                while (true) {
                                    switch (str7.hashCode() ^ (-2073875899)) {
                                        case -1968110617:
                                            str6 = "ۤۤۦۘۚۦۡۖۤۡۤۦۨۘۛۜ۠ۚۨۨ۟ۗۜۛۚ۠ۖ۟ۖۘۤۥ";
                                            break;
                                        case -1609643733:
                                            str6 = "ۡۗۨۛۖۢۦۨۨۘ۫ۙۥۡۤۛۦۙۜۦۦۘ۠۫۬ۛۜۡۢ۬ۚۛۗۥۘۖۡ۫ۤ۟ۖۘۖ۠ۗۙۨۧۘۢۗۨۙۨۧ۫ۤ۫";
                                            break;
                                        case 131834935:
                                            str7 = mediaPlayer == this.mediaPlayer ? "ۢۥۨۘ۬ۢۥ۠ۙۖۘۜ۬۟ۤ۟ۥ۟ۖ۟ۙۤ۟۠ۦۙ۟۠ۧۚ۟" : "۠ۧۨۤۜۡۢۜۗۧۧۦ۟۠ۜۘۙۨۡۙۘۨۘۗ۠ۧۗۜۥۥ۟ۗۢۡۘۡۦۘ";
                                        case 528918611:
                                            str7 = "۫ۤۙۘۥ۬ۨ۬ۛۡ۬۟ۚۖۢ۬۬ۤۨۙۚ۟ۥۗۥۛۡۘۘۛۜۘ";
                                    }
                                }
                                break;
                            case 2134762666:
                                str6 = "۠ۡۘۘۘۦۧۦۛۡۢۙۙ۫ۖۦۗۘۦۢۛۜۘۨۖۜۘۚۗۜۤۥ۟۬ۖۙۛۚ۫ۢۡۡۘۥ۠۬ۨۙۜۨ";
                        }
                    }
                    break;
                case 1680994451:
                    runJS(sb.toString());
                    str = "ۡ۟ۥۡۛۙۚۨۗ۠۫ۛۢۙۚ۫ۛۘۜۚ۠ۚ۟ۚ۟ۤۘۜۡۘ";
                    continue;
                case 1961701480:
                    return;
                case 2102854888:
                    sb.append(this.jsOnEnd);
                    str = "ۚ۟ۘۘ۠ۜۘۘۧ۫۠ۡۚۨۚۚۘۘۨۚۥۘ۠۫ۖۘ۫ۥۜۦۦۤۘۗۤ۟ۤ۬۠۟ۛۤۧۜۘۤۗۧۢۛۜۘۢۢۥۘ";
                    continue;
            }
            str = "ۡ۟ۥۡۛۙۚۨۗ۠۫ۛۢۙۚ۫ۛۘۜۚ۠ۚ۟ۚ۟ۤۘۜۡۘ";
        }
    }

    private /* synthetic */ void lambda$resumeMusic$14() throws IllegalStateException {
        MediaPlayer mediaPlayer = null;
        String str = "۠ۨ۬ۚ۬ۨۢۧۡۘۨۤۚۘۦۖ۠ۙۦۚۜ۬ۥۧۜۗۛۤۡۘۥۘ";
        while (true) {
            switch ((((str.hashCode() ^ 400) ^ 628) ^ 619) ^ 1962089181) {
                case -2136635026:
                case -166352269:
                    return;
                case -1583405923:
                    mediaPlayer = this.mediaPlayer;
                    str = "ۗۢۥۘۛ۫۬ۛۡۤۡ۟ۦۘۡۥۦۘۢۥ۫۟ۨۙۗۛۖۙۨۗۦۘۦ۟ۦ۠ۢۧ۫۟ۚۨۙۦ";
                    continue;
                case -554701280:
                    String str2 = "ۗۘۡۘ۫ۚ۟ۜ۫ۥۘ۫ۙۧۙۚ۬۬ۚ۟۫ۛ۫ۜ۟ۙۗۤۡۤۜۘۚ۟ۗۦ۟ۧ۟ۤۢ۟ۜۘۘۙۛۨ۫ۗ۟۠ۢۛۨۡ";
                    while (true) {
                        switch (str2.hashCode() ^ (-974243013)) {
                            case -1906410936:
                                String str3 = "ۙۦۚۙۦۦۗۗۘۨ۟ۨ۠ۖۜۘ۫ۗۡۖۖۧۢۜۖۘۧ۫ۛۡ۬ۙۚۜۜۘۛۦۡۚۨۛۙۛۨۘۢۥۦۘۡۡۦۢۜ۟۫ۨ";
                                while (true) {
                                    switch (str3.hashCode() ^ 115895334) {
                                        case -2134397858:
                                            str3 = "۟ۙۡۘۧۗۨۘۚۧۚۨۢۚ۠ۦ۬ۢۚۗۛۜۘۡۥۧۘۚۖۖۛۚ۬ۖۥۙۜۡۜ";
                                        case -1190165335:
                                            str2 = "ۛ۟ۗۦۨۛۛۚۡ۬ۗۘ۫۠۠ۗۡۘ۫ۤۘۥۖۛ۟۠ۤۡۢۡۘ۬ۨۜ۠ۚۧۙ۬ۧۥۗۖۤ۠ۘۦۡۥۦۘۦۘۖۤ۫";
                                            break;
                                        case 53585860:
                                            str2 = "۫ۘۤۧۡۜۧۚۛۡۧۗ۫۠ۧۛۤۦۘۖۜۛ۬ۡ۟۫ۛۥۛۢ۫ۨ۟۫ۦ۬۫";
                                            break;
                                        case 1601294770:
                                            str3 = this.disposed ? "ۜۡ۠ۗۥۦۦۛۛۤۘ۠۫۬ۚۙۨۦ۠ۚۜۘ۫۬ۖۗۧۥۘ۠ۛۥۘ۬۫ۘۨۥۧۘ" : "ۢۥ۠ۢۦۨۗ۠۬ۘۢۨۡۘۛۚۘۘ۟۫ۛۥۧ۠ۙۗۤۛ۠ۦ";
                                    }
                                }
                                break;
                            case -224289963:
                                str = "ۤۜۜۘ۟ۥۥۘۧۢۗۤۦۗۨۥۨ۟ۧۥۘۜۙۥۖ۟ۤۜ۠ۖ۬ۙۛ";
                                continue;
                                continue;
                            case -28996151:
                                str2 = "ۚۗۜۨۥۛ۬ۛۘۘۡۘۚ۠ۙ۠۫ۘۘۜۚ۬ۦ۬ۤۧۖۗ۬۬ۧ۬۬ۨۘۥ۬ۜۘ";
                                break;
                            case 1396969376:
                                str = "ۗۧۨۙۧۤۡۜۡ۫ۤۨۜۘۘۗۡۛۧۦۤۘۖۗۦ۬۫ۛۗۨۥۚۛ۫";
                                continue;
                        }
                    }
                    break;
                case -536828817:
                    String str4 = "ۢۤ۠ۘۙۦۘۧۢ۬ۜۢۗۧۖۖۡۥ۠ۛۨۗ۫ۧۨۘ۠ۢ۟ۧۚ";
                    while (true) {
                        switch (str4.hashCode() ^ 1195714226) {
                            case -2079586335:
                                break;
                            case -2037464042:
                                str = "ۥ۬ۚۥۥۗ۬ۗۙۧۚۗۙۦۜ۬ۡۦۘ۬ۨۤۚۥۥۢ۫ۡۘۥ۟ۦۘ۬ۥۛۧۦۜۘ۫ۦ۟ۘۢۗۘۥۘۘۦ۬ۗ۫ۙۨۘ۠ۙ";
                                break;
                            case -539579310:
                                String str5 = "۬ۧۙۚۨۨۙۦۥۥۨۛۧۙۢۥۦ۬ۤۨۖ۠ۛۡۨۧۘۙ۬ۜۘ۟ۛ۬ۚۦۦ";
                                while (true) {
                                    switch (str5.hashCode() ^ 135585188) {
                                        case -977368125:
                                            str5 = mediaPlayer != null ? "ۜ۫ۦۛ۠ۡۘۘۛۤۖۖۘۨۗۘۢۛۖۨ۟ۜۘ۬ۦۥۘۥ۫۬۬۟ۖۘۨ۫ۤۙۢۘ۟ۛۨ۟۠ۛ" : "ۖۖۧۥۗۖۘ۬ۙۢۤۨۖ۠ۙۥۙۖۨۘۢۤۦۘۨۢۦۦۙۙۙ۟۫ۧۡۖۘۚۨۦۘ";
                                        case 373256553:
                                            str4 = "ۥۧۜۖۚۖۘۙۡۙۤۜ۫ۘ۟۬ۘۨۛ۟ۨۦ۠۫ۤۤۦۙۘۗ۟ۖۛۘۡ۟ۨ۬ۘۨۤۘۦۘ";
                                            break;
                                        case 1651692701:
                                            str5 = "ۚۧ۟ۡۡۘۘۗۡ۫ۨۡۦۡۥۨۘۛۙۦۚۡۦۖۡ۬ۡۡۨۘۡۜۦۚۗۘۚۖۡ";
                                        case 1726322966:
                                            str4 = "۟۫ۗۡ۬ۖۙۜۢۛ۬ۢۚ۫ۖۚۘۢ۠ۖۢ۠ۥ۟ۤۨۧۘ۟ۘ۠ۜ۟۟ۥۨۘۤۡ۫ۗۥۘ۫ۚۛۘۜۛۥۚۡۜ۬ۜ";
                                            break;
                                    }
                                }
                                break;
                            case 1708436908:
                                str4 = "ۖۡۧۘ۟۫۠ۨۗ۟۟ۜ۟ۦ۫ۢۥ۟ۗۨ۬ۖۥۙۖۧۦۜۘ۬ۘ۠ۥۡۜ۠ۨۥ۟۠ۜۛۙۘۙۖۨۘۖۢۘ";
                        }
                    }
                    break;
                case 741388279:
                    str = "ۦ۬ۡۘ۬ۧۖۙۨۡۡ۟ۦۘۦۨۨۤ۬ۥۤۦۘۦۜۜ۫ۨۥ۠۠ۜۚۥۧ۫۠ۛ۠ۘۡۘۛۙۨ";
                    continue;
                case 745623361:
                    this.mediaPlayer.start();
                    str = "ۗ۠۫۟ۛۚ۠۠ۜۘ۠ۘۧۘۢۘۙۙ۟ۘۘۘۥۢۡۘۜۘۡ۫۠ۥ۟ۖۘ۬ۚۢۢۤۜۘۢۢۚۛ۟ۡۘۚۡۗ۫ۨۤۖۚۥۗۧۜۘ";
                    continue;
                case 1826612733:
                    String str6 = "۟۠ۦۙۘۧۚ۠ۜۘۢۖۥ۟ۛۥۜۙۚۗۜۚۧۢۖۢۢ۠ۛۧ۬ۘۙۜۥۛۜۘۙۦۚۦۜ";
                    while (true) {
                        switch (str6.hashCode() ^ 794565467) {
                            case -764546466:
                                str = "ۛ۫ۤ۟ۜۛۦۧۜۘۖۢۨۤۢۖۘ۠ۤۙۙۦۖۨ۠ۧۦۖۧۘۡۘۡۦ۠۟ۥ۬ۨۘۡۨۦۘ۟۬۠۠ۛۜۡۚ۬";
                                break;
                            case 55417413:
                                break;
                            case 151292063:
                                str6 = "ۡۜۥۘۧۥۛۦ۬ۗۘ۟۠ۡۛ۬ۙۜۘۜۦۧۧ۬ۘۘۖۗۧۛ۬ۚ۠ۜ۬۬۬ۢۧۜۘۘۤۥۘ";
                            case 1869107271:
                                String str7 = "ۦۤ۟ۤۗ۠۠ۥۖۘۛۧ۬ۦ۠ۘ۬ۤۢۙۦۧ۠ۜۦۘۘۦۨۗۦۤۤۙ۬ۨۘ۟ۤ۟ۡۜۨۖ۫ۧۨۚۗ۟۫۬۟ۘ";
                                while (true) {
                                    switch (str7.hashCode() ^ (-2010496198)) {
                                        case -2076971464:
                                            str6 = "ۨۦۥۘۙۢ۠ۤۥۢ۠ۢۖۘۜۛ۠ۗۛۙۨۖۨۢ۬ۘ۬ۖۖۘۖۦۛۘ۟ۖۚۖۤ۟ۥۘۥ";
                                            break;
                                        case -520821108:
                                            str7 = !mediaPlayer.isPlaying() ? "۟۠ۥۘ۠۬ۜۘۙ۠ۡۤۦۨۘۖۙۛۛۜۧۘۨۛۥۘۧۛۙ۠۟ۡۛۨۜۙۤۘۥۚ۫ۖۜۥۧۤۨۘ" : "ۙۙۗ۠۬ۨۘۘ۫ۧ۬ۜۖۘۧۨۧۘۧۤۦۘۧ۟ۦۘۧۥۡۘۜۨۦۘ۬ۧۥ";
                                        case 1984033643:
                                            str7 = "ۖۛۚۜۛ۟ۧۘۖۤۜۧ۠ۦۦۧۘۖ۬۫ۡۡۢۤۘ۟ۚۡۡۘ۟ۦ۫۫ۘۙۗۢۜۘۥ۠۬";
                                        case 2141988835:
                                            str6 = "۠۟ۨۘۜۨ۟ۗۘۡ۫ۨۡۘۦۚۨۚۤۧ۫۬ۚ۬۬ۛ۫۫ۘۘۗۚۘۘ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
            }
            str = "ۗ۠۫۟ۛۚ۠۠ۜۘ۠ۘۧۘۢۘۙۙ۟ۘۘۘۥۢۡۘۜۘۡ۫۠ۥ۟ۖۘ۬ۚۢۢۤۜۘۢۢۚۛ۟ۡۘۚۡۗ۫ۨۤۖۚۥۗۧۜۘ";
        }
    }

    private /* synthetic */ void lambda$runJS$16(String str) {
        WebView webView;
        String str2;
        try {
            webView = this.boundWebView;
            str2 = "ۖۡ۬۬۫ۧ۠۬ۡۘۥۛ۬ۥ۠ۡ۠ۦۘۦۡۜ۬ۚۥۧۡۧۛۨۥۥۘۨۗۡۘ۠ۥۗۦۦۤۙۗۦۙ۠ۖۘ";
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        while (true) {
            switch (str2.hashCode() ^ 288944975) {
                case -1496354206:
                    String str3 = "ۘۗۦۘۦۘۦۡۧ۬ۢۧۡۘۗۢۜۤۚ۠ۗۧ۠ۘۢۖۦۘۙ۠ۜۘۖ۟ۡۘۦ۫ۨۤۦۘۢۚۢۚۘۘۥۙۗۦۧۖۘ۫ۜۨۘ";
                    while (true) {
                        switch (str3.hashCode() ^ (-396434872)) {
                            case -1851669136:
                                str2 = "ۨ۫ۘۘۗۦۧۘۧۤ۬ۥۛۥ۫۫ۗ۠ۙۨۘۗۡۧۗۧۦۘ۟ۥۤۡۖۘۗۢۢۚۛۡۗۗۙۜۧۜ";
                                continue;
                                continue;
                            case 431910515:
                                if (!this.disposed) {
                                    str3 = "۟ۦۡ۠ۦ۫ۨۖۨۘۘۥۘۢۖۡۤۡۚۖۢ۬ۙۘ۟ۨۜۛۥ۬۟ۗۘۤۤۧۨۢۡۛ۠ۡۧۘۗۢۘۘۘۡۘ۟۟۫";
                                    break;
                                } else {
                                    str3 = "ۙۡۧۥۘۛۦۦۡۜ۟ۦۘۘۖۦۙ۫ۦۚۦۖۘۗۤۨۘۧۡۡ۟ۘۨ۫ۦ۫۟ۧ۫ۙۡ۬ۛ۟ۦ";
                                    break;
                                }
                            case 589685304:
                                str3 = "ۧۗ۟۠ۢۜۘۨۥۤۗ۫ۤ۬ۚ۫ۢۜۥۘۖۘۦۢ۫ۡۘۖۚ۬ۨۗ۟";
                                break;
                            case 613662965:
                                str2 = "ۚۙۡۘۧۜۧۦۗۧۖۥۖۘۗۛۡۖ۠ۡۘۥ۠ۨۘۙۜۧۤۢ۠ۤۡۖۜۚۨۙۢۥۧۖۢۢۤ";
                                continue;
                        }
                    }
                    break;
                case -691723287:
                    return;
                case 1598648071:
                    str2 = "ۜ۬ۥۧ۫ۜۘ۬ۘۧۚۜۥۘ۬ۜۤ۟۠ۚۦ۠ۜۢۖۘۜ۟ۤۧۤۦ۟۫ۛۖۜۘ";
                    continue;
                case 1867867362:
                    String str4 = "ۛۧۗۗ۟ۦۘۡ۟ۜۦۛ۟۠ۧۙۚۦۢۦۧۥۚۗۨۘ۫ۧۨۘۤ۠ۨۛۤۧۥۗ۠ۚۨۗ۫ۤۥۧۜۙ۬ۘۧۘۜۢۚۗۡۖ";
                    while (true) {
                        switch (str4.hashCode() ^ 1811624105) {
                            case -1661227327:
                                webView.evaluateJavascript(str, null);
                                return;
                            case -1165421493:
                                String str5 = "ۚ۠۫ۧۚ۟۫ۨۖۘۜۚۢۜۦۨۘۛۚۡۘۨۡ۫ۙۘۥ۬ۙ۠ۜۨۤۢۥۤۚ۟ۘۘ۟ۖۜۘۥۦۘ";
                                while (true) {
                                    switch (str5.hashCode() ^ (-558975885)) {
                                        case -1663046634:
                                            if (webView == null) {
                                                str5 = "۫ۦۜۘ۫ۨ۠ۥۥۥۘۜ۫ۖۨ۠ۧۛۚۖۚ۬ۤۢۗۧۜۤۤۚۧۜۘۥۙۖۦ۬۫ۜۡ۬ۗۙۨ";
                                                break;
                                            } else {
                                                str5 = "ۚۛۜۙۚۦۨۤۜۧۚۘۘۖۨۖۨۗۦۛۤ۫ۛۗۨۘۡ۟ۜۘ۬ۛ";
                                                break;
                                            }
                                        case -748368444:
                                            str4 = "ۘۥۚ۫ۗۘۖۧۥۘۥ۫ۦۘۙۜۘ۫ۨۨۘۙۘۧۤۧۜۗۥۢ۬ۡۘ";
                                            continue;
                                        case 112213960:
                                            str5 = "ۨۙۢۨۡۛۤۥۘۤ۠ۨۘۙۢۥۘ۠۠ۛۥۘۢۢۦۥۨۧ۫ۙۜۨۡۚ۟ۦۘۢۨۦۘۥۚۨۘۘۡۡۘۢۨۜۡۧۤۨۛۤ";
                                            break;
                                        case 519836063:
                                            str4 = "ۙ۠ۘۚۦۜۘ۫ۚۢۘۡۛۦ۠ۗۗۧۗ۫ۧۦۙ۠ۥۜۧ۟ۖۦۜ۠ۥۖۤۜۚۡۘۙۖۥ۟ۙۜۚۛۧ";
                                            continue;
                                            continue;
                                    }
                                }
                                break;
                            case -296168353:
                                str4 = "ۨۖۤۘۧۗۧۢ۫ۛۘۧۚۜۜۘۖۘ۠ۘۦۡۘۗۨ۬ۢ۬۬ۜ۟ۜۦۜۥۦۜۘۘۨۙۨ۠ۦۜۧۖ۬ۢۦۖۘۜۢۙۛۚۢ";
                                continue;
                            case 2013838542:
                                return;
                            default:
                                continue;
                        }
                    }
                    break;
                default:
                    continue;
            }
            e.printStackTrace();
            return;
        }
    }

    private /* synthetic */ void lambda$stopMusic$15() throws IllegalStateException {
        String str = "ۤۙۨۖۡۧۚۦۘ۫۫ۤۢۧۖۘۦۢ۬۫ۗۜ۬۟ۧۙۘۙۜۥۤ";
        while (true) {
            switch ((((str.hashCode() ^ 321) ^ 585) ^ 391) ^ (-1469211839)) {
                case -2138932292:
                    stopAndReleaseMusic();
                    str = "ۦۡۖۘ۠۬ۨ۬ۖۗۚۘۜ۬۟ۥۘۤۥۤۡۜ۬ۥۤۛۗۙۡ۟ۚۧۥۡۦۢۦۦۘۧۗۖۛۨۢ";
                    break;
                case -1199341852:
                    this.isPrepared = false;
                    str = "ۧۚۘۥۗۥۘۥۥۨ۫ۦۡۘۙ۟ۦۘۙ۟ۥۘۢ۫ۜۦۙۗۡۖ۟ۦۥۢۜۨۗۧۙۧۧ۫ۥۤ۬ۚ";
                    break;
                case -688263741:
                    str = "ۖۧۘۘۤۛۜۤۙۜۤۨۨۘۢۡۤۦ۟ۖۛۥۥۘۗۦۘۗۦۨۘ۠ۨۤۛۥۙۛۢۘ۠ۨۡۘۙ۠ۦۘ۠ۥ۬ۢۚۧ";
                    break;
                case 1096307560:
                    return;
            }
        }
    }

    private /* synthetic */ void lambda$verifyCardKey$2(String str, boolean z, String str2, String str3) {
        try {
            String str4 = String.format(l2.decrypt("X2oabeCrpyhfahVh\n", "ehkySIKHhw8=\n"), str, Boolean.valueOf(z), str2.replace(l2.decrypt("TA==\n", "a648JQh9GWE=\n"), l2.decrypt("W4Y=\n", "B6EE5zLKUEM=\n")));
            WebView webViewFindCurrentWebView = findCurrentWebView(str3);
            String str5 = "ۥ۬۟ۧۧ۠۟۬۬ۘۦۧۘۖۨۙۙۧۘۗۘ۬ۦۚۜۘۖۗۗۢۙۛۜۚۜۗ۠ۛۡۥۧۧۜ۬ۚۡۖۤ";
            while (true) {
                switch (str5.hashCode() ^ (-1303096937)) {
                    case -2101605139:
                        String str6 = "۟۬ۘۘۖۗ۟ۢ۫۟ۢۦۖۦ۬ۨۘۨۘ۠ۡۥ۬ۨۜۡۘۘ۠۬ۘۦۡ۬ۢۙۗۙ۫۠ۥۨۘۥۛۤۦۥ۟ۨۤ";
                        while (true) {
                            switch (str6.hashCode() ^ 1388749886) {
                                case -2071789682:
                                    break;
                                case -1982192516:
                                    str6 = "ۤۢۚ۟ۖۨ۫ۚۜۘۙۨۗۡۡۦۘۜۖۨۨۗۜۥ۠ۢ۬ۗۨۗ۫ۖۦۙۤ۫۟ۥۘ";
                                    continue;
                                case 435145600:
                                    String str7 = "ۥ۫ۘۧۛۛۚ۠ۧۛ۟ۘۘۥۥۤۜۨۘۨ۬ۚۧ۬۠ۤۨۜۖۜۖۚۤۥ۟۠ۨۘۛۖۨۚۖۦۜۛۜۘۨۘۤ۟۫ۢۙ۠ۙ";
                                    while (true) {
                                        switch (str7.hashCode() ^ (-1240281233)) {
                                            case -1121433727:
                                                str7 = "ۘۤۡۘۛۡۡۚ۟ۚۛۛ۟ۦۜۥ۫ۢۤۨۤۚۚۢۛۤۨ۟ۚ";
                                                break;
                                            case -228416641:
                                                str6 = "ۧۘۙۦۛۖۛۥ۟ۙۡۖۘۘۘۡۘۥۖۜۖۙۥۘۘ۬ۡۦۨۙۚۜۨۘۥۥۜۡۘۖۘۘۡ۫۫ۘ۬ۧۜۧۜ۠ۧ۟ۥ۫ۚۨ";
                                                continue;
                                            case 556675726:
                                                if (webViewFindCurrentWebView == null) {
                                                    str7 = "ۚۚۡۘۖۤۜۧۦۨۦ۟ۡۘ۬ۡۡۘ۠ۖۗۘۢ۠ۥۘۦۙ۠ۙۨۤۦۘ";
                                                    break;
                                                } else {
                                                    str7 = "ۙ۟۠ۛ۬ۡۙ۟ۗۧۙ۟ۢ۠ۗۧ۟ۜ۬ۤۢۗۢۨۘۤۚۢۛۨۤۖ۟ۜۘۗۢۥۘۖۖۢۚ۠۠";
                                                    break;
                                                }
                                            case 1351855675:
                                                str6 = "۟ۘۡۘۗ۠ۚۜۖ۬ۧۘ۫۫ۢۢۗۧۡۘ۟ۗۡ۬ۚۚ۟ۖۧۜۦۜۘۗۘ۠ۗۘۛۚ۟ۧۥۖۚ۟ۛۥۤۥۘۤ۟ۨۘ۬ۘۧۘ";
                                                continue;
                                                continue;
                                        }
                                    }
                                    break;
                                case 1054192443:
                                    k2.logToFloatingWindow(l2.decrypt("LXH/zytMOV4rfsxm+DvBnryYCfw7y8WfhZQWpZ48Dh19ZIkM30IWdylK/GPmNlJgUBnfBg==\n", "zvFvhXiqt/s=\n"), l2.decrypt("g6O3ZgY=\n", "58bVE2E7GNk=\n"));
                                    webViewFindCurrentWebView.evaluateJavascript(str4, null);
                                    return;
                                default:
                                    continue;
                            }
                        }
                        break;
                    case 682913:
                        str5 = "۬ۨۘۘ۟ۡۘۡ۠ۙۤۦۖۚ۠ۡۧۘۖۛ۟ۡۘۙۨۖۥۜۥۘۗۜۧۛۜۥۘۖۖۘ";
                        continue;
                    case 153446042:
                        String str8 = "ۛۖۙ۟ۚۡۘۥ۬ۜۗۖۘۥۖۘۛ۠ۘۡ۬ۤۘۨۧۧۡۘۘۢۧۚۖۗ۬ۢۘ۫ۥۗۢۚۘ۟ۨۢۤۚۨۘۥ۠ۥۥۢۨۘ";
                        while (true) {
                            switch (str8.hashCode() ^ 97787328) {
                                case -1450401596:
                                    str5 = "ۛ۟ۤۚۛۨۘۦۢۢۨ۬ۨۤۢۗ۠۫ۦ۫ۙ۠ۘۨۨۘۘۥ۟۠ۦۨۡ۠ۦ۫۟ۛ";
                                    continue;
                                case 253774533:
                                    str8 = "ۢۗۦۥۥۧۘ۟۟ۚ۠ۦۘۘ۫ۥۨۘۦۛۙۨۚۖۘۘۘۜۛۜۢۢۜۢۢۖۥۖۧۘۜۧۦ۫ۧ۫ۧۜۘۚۡ۬";
                                    break;
                                case 343356671:
                                    if (!this.disposed) {
                                        str8 = "۟ۖۜۧ۫۟ۧۢۖۘۗۛۡۘۛۙۡۦۤ۬۠ۧۘۧۖۗۘۢۛۧۛۖ۟ۤۥۘۥۖ۠ۡۜۘ۟ۗ۠ۥۗۦ۫ۘۥ";
                                        break;
                                    } else {
                                        str8 = "ۥ۫ۧۧۡۧۡۧۛۖۧ۬ۥ۬ۨۘۦ۫۟ۙۨۚۢۢۘۙ۬ۢۖ۟ۥۛۧ۠ۥۛۦۙۚۗۖۦۥۘۖۚۖۘۥۨۜ۟ۢۨ";
                                        break;
                                    }
                                case 706371487:
                                    str5 = "ۚۨۜۡۥۜۨ۟۟ۢۗۡۖ۟ۦۘۨۙۘۘۨۘۘۘۚۖۜۘۘۖۧۥۧۙۜۗۥۚۥۧۘ";
                                    continue;
                                    continue;
                            }
                        }
                        break;
                    case 1879672742:
                        break;
                    default:
                        continue;
                }
            }
            k2.logToFloatingWindow(l2.decrypt("M00kmJsyw1A1QhcxSEU7kKKk0quLtT+Rm6jN8i5d8xFoQFFaePQakLKb3be/\n", "0M200sjUTfU=\n"), l2.decrypt("Cgq8fD0=\n", "bm/eCVqDb2c=\n"));
        } catch (Exception e) {
            k2.logToFloatingWindow(l2.decrypt("7Eo15Z569KbqRQZMTQ0MZn2jw9aO/QhnRK/cjygH5Ou/SUALfHTOpg==\n", "D8qlr82cegM=\n"), l2.decrypt("iRfAp3I=\n", "7XKi0hUGxys=\n"));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0026 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:50:0x002e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00ec A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:52:0x010d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:56:0x001d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:60:0x00be A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:61:0x00d4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:62:0x0114 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x0130 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00b5 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private /* synthetic */ void lambda$verifyCardKey$3(String str, String str2, String str3, String str4) throws PackageManager.NameNotFoundException {
        String strDecrypt;
        int i;
        String str5;
        String str6;
        int i2 = 0;
        String packageName = this.activity.getPackageName();
        String strReplaceAll = "";
        try {
            PackageInfo packageInfo = this.activity.getPackageManager().getPackageInfo(packageName, 0);
            strDecrypt = packageInfo.versionName;
            try {
                i = packageInfo.versionCode;
            } catch (PackageManager.NameNotFoundException e) {
                i = 0;
                str5 = "ۙۤۘۘۧۦ۟۠ۡۘۙۧۚۦۦ۟ۡۖۡۨۖۘۡ۫ۨۖۘۧۗۦۘ";
                while (true) {
                    switch (str5.hashCode() ^ 1075075117) {
                        case -2064524352:
                            break;
                        case -1549216336:
                            break;
                        case -763300147:
                            break;
                        case 1112819458:
                            break;
                    }
                }
                HashMap map = new HashMap();
                map.put(l2.decrypt("77j/Xmc=\n", "htaPKxNgdGA=\n"), str);
                map.put(l2.decrypt("QZayafI=\n", "IObCIJYvd5o=\n"), fcRuQsQrcxOAzxwEalcM.APP_ID);
                map.put(l2.decrypt("rIRADgHg\n", "zfQwRWSZvEE=\n"), fcRuQsQrcxOAzxwEalcM.APP_KEY);
                map.put(l2.decrypt("J+SG5/kxdsg=\n", "Q4HwjppUP6w=\n"), Utils.getUniqueDeviceId(this.activity));
                map.put(l2.decrypt("+sDjpAZNvg==\n", "iqGAz2cq2zA=\n"), packageName);
                map.put(l2.decrypt("Ah+g10BgLTYaG7/B\n", "dHrSpCkPQ2k=\n"), strDecrypt);
                map.put(l2.decrypt("ouaYDuWBHoq37I4Y\n", "1IPqfYzucNU=\n"), String.valueOf(i));
                map.put(l2.decrypt("o5UNJgkWEeemmBo5DA==\n", "1fB/VWB5f7g=\n"), l2.decrypt("i8Of\n", "uvWufcHR6tA=\n"));
                Object[] objArrPostRequestOnce = Utils.postRequestOnce(str2, map);
                boolean zBooleanValue = ((Boolean) objArrPostRequestOnce[0]).booleanValue();
                Object obj = objArrPostRequestOnce[3];
                str6 = "ۧۙۜۜۚۥۨۢۥۘۙ۫ۦۜ۫ۧۡۜۡ۠ۧۢۛۛۜۨ۫۫ۘۦ۬ۤۦۘ۠ۡ";
                while (true) {
                    switch (str6.hashCode() ^ (-121108822)) {
                        case -1129902605:
                            break;
                        case -333018635:
                            break;
                        case 623253137:
                            break;
                        case 1351185570:
                            break;
                    }
                }
                this.mainHandler.post(new b0(this, str3, zBooleanValue, strReplaceAll, str4, i2));
            }
        } catch (PackageManager.NameNotFoundException e2) {
            strDecrypt = "";
        }
        str5 = "ۙۤۘۘۧۦ۟۠ۡۘۙۧۚۦۦ۟ۡۖۡۨۖۘۡ۫ۨۖۘۧۗۦۘ";
        while (true) {
            switch (str5.hashCode() ^ 1075075117) {
                case -2064524352:
                    strDecrypt = l2.decrypt("HdvC9E6CUVtzofS/\n", "+0doE9EnttI=\n");
                    break;
                case -1549216336:
                    str5 = "ۡۗۙۚۧ۫۠۟ۘۧۘۡۥۨۦۘۗۗۢۤ۠ۜۗۖۚۖۦۡۥۥۙۨۚۤۧ۠";
                    break;
                case -763300147:
                    break;
                case 1112819458:
                    String str7 = "ۨ۠ۢۘ۫ۜۦۨ۫۬ۢ۠ۙۜ۫ۥۡۤۧۢۖ۟۬ۥۘ۬ۚۗۧۗۡۘ";
                    while (true) {
                        switch (str7.hashCode() ^ 623568378) {
                            case -1822914867:
                                str5 = "ۙۖۨۘ۟ۛۛۗۥۘ۠ۚۘۜ۬ۙۖۙۡۘۤۨ۠ۗۛۗۡۚۨۘ۬ۨۘۘۨ۠ۢۡۚۘۤ۫ۚۧ۬ۦ۫۫۠ۢ۠ۢ";
                                continue;
                            case -1272051788:
                                str7 = "ۙۥۥۘۢۢۤ۟ۙۘۘۡۖ۟ۢۙۗۡۘۡۢۤۨۗۘۧۘۙۥۘۦ۟ۦۘۚۦۘۘۥۜۖۘ۬ۡۧ۫۬ۤۢۜۢۤۛ";
                                break;
                            case 442063983:
                                if (!strDecrypt.isEmpty()) {
                                    str7 = "۟۠۟ۜۥۚۖ۫ۜۗۤ۫ۦۛۜۡ۬۬ۧۘۗۨۢۚۢ۫۟ۘۡۘۤۧۡۛ۟۟ۖۤۙۥۨۘۧ۫۟ۜۖ";
                                    break;
                                } else {
                                    str7 = "۠ۧۨۗۢۦ۠ۢ۬ۘۙۗ۠ۙ۫ۛۦۢ۠۫ۖۗۢۦۘ۟ۚۡۦۚۜۘ۬ۨۖۘۖۨ۠ۡۘۢۜۢۦۘۖ۬ۚ۬ۨۢ";
                                    break;
                                }
                            case 1006942383:
                                str5 = "ۗۥۨۘۡۥ۟ۢۡ۠۫ۘۗۛۧۥۘۛ۫۟ۙ۠ۘۘۗۛۡۘۚۧ۬۫ۥ۫۟ۧۡۘ۟ۖۧۘۢۗۖۙ";
                                continue;
                        }
                    }
                    break;
            }
        }
        HashMap map2 = new HashMap();
        map2.put(l2.decrypt("77j/Xmc=\n", "htaPKxNgdGA=\n"), str);
        map2.put(l2.decrypt("QZayafI=\n", "IObCIJYvd5o=\n"), fcRuQsQrcxOAzxwEalcM.APP_ID);
        map2.put(l2.decrypt("rIRADgHg\n", "zfQwRWSZvEE=\n"), fcRuQsQrcxOAzxwEalcM.APP_KEY);
        map2.put(l2.decrypt("J+SG5/kxdsg=\n", "Q4HwjppUP6w=\n"), Utils.getUniqueDeviceId(this.activity));
        map2.put(l2.decrypt("+sDjpAZNvg==\n", "iqGAz2cq2zA=\n"), packageName);
        map2.put(l2.decrypt("Ah+g10BgLTYaG7/B\n", "dHrSpCkPQ2k=\n"), strDecrypt);
        map2.put(l2.decrypt("ouaYDuWBHoq37I4Y\n", "1IPqfYzucNU=\n"), String.valueOf(i));
        map2.put(l2.decrypt("o5UNJgkWEeemmBo5DA==\n", "1fB/VWB5f7g=\n"), l2.decrypt("i8Of\n", "uvWufcHR6tA=\n"));
        Object[] objArrPostRequestOnce2 = Utils.postRequestOnce(str2, map2);
        boolean zBooleanValue2 = ((Boolean) objArrPostRequestOnce2[0]).booleanValue();
        Object obj2 = objArrPostRequestOnce2[3];
        str6 = "ۧۙۜۜۚۥۨۢۥۘۙ۫ۦۜ۫ۧۡۜۡ۠ۧۢۛۛۜۨ۫۫ۘۦ۬ۤۦۘ۠ۡ";
        while (true) {
            switch (str6.hashCode() ^ (-121108822)) {
                case -1129902605:
                    strReplaceAll = obj2.toString().trim().replaceAll(l2.decrypt("4nVY\n", "vgZzVO3+OF0=\n"), "");
                    break;
                case -333018635:
                    break;
                case 623253137:
                    str6 = "ۗۙۦۘۥۚ۠ۨۗ۟ۢۦۦۦۗۘۘۖۗۦۘۛۨۜۘۨۖۗ۬ۨۛۥۨۨ";
                    break;
                case 1351185570:
                    String str8 = "ۚۛۨۦۜۘۥۢۗۗ۫ۥۥۘۘۛۧ۠۠۬ۨۘ۫۫ۡ۬ۧۘۗۦۖۘۥۤ۬ۜۘ۠۫۟ۛۦ۟ۚ۟۫ۤۦۤۙۢۦ۟۫۠ۘۘ";
                    while (true) {
                        switch (str8.hashCode() ^ (-1361493991)) {
                            case -1867466677:
                                if (obj2 == null) {
                                    str8 = "ۧۥۤۢ۠ۨ۫ۘۨۘۚۙۗۢۢۦۡۡۜۘۜۙ۠ۧۜ۬۬ۤۚۥۚۚۖۘ۬ۛۦۘ۫ۥ۫ۧۨ۬";
                                    break;
                                } else {
                                    str8 = "ۛۥ۠ۥۛۙۨۥۨۘۡۛۗۥ۬۠ۨۤ۟ۛ۟ۜۘۤۘۨ۠ۛ۫ۢۦۘ۟ۤۚۨۧۧۨۧۘۘۚۛۙۦۧۜۘۡۚۖۘۛۧۖۗۢۥۘ";
                                    break;
                                }
                            case -1312266618:
                                str6 = "ۙۨۛۖۥۙۤ۫ۖۘ۬ۚۢ۫ۢۧۙۜۨۢۨۡۘۤۗ۠ۙ۫ۘۘۛۦۖ۫۫ۢۥۚۦۘۛۢ۟ۘۛۖۘ";
                                continue;
                            case 744356396:
                                str8 = "۫ۛۦۤ۬ۤ۬ۥۦۘۘۤۖۨ۫ۖۧۖۢ۫ۚۘۙۤۧ۠ۗۡۘۘۜۥۘ";
                                break;
                            case 798244104:
                                str6 = "ۢۦۢۧۦۘۖۚۧۚۤۥۘۧۙۢۛۤ۫ۢۚۖۘۤ۠ۧۢۜۤۜۢۨۘۗۜۛۦ۬ۨ۫ۡۢ۬ۚۘۖۗۘۘۙۙ۬";
                                continue;
                        }
                    }
                    break;
            }
        }
        this.mainHandler.post(new b0(this, str3, zBooleanValue2, strReplaceAll, str4, i2));
    }

    /* JADX WARN: Code restructure failed: missing block: B:6:0x0060, code lost:
    
        core.pro.android.notify.k2.logToFloatingWindow(core.pro.android.notify.l2.decrypt("AZNaaU+5ntUHnGnAnM5mFZB6rFpfPmIUqXazETy5mc4Gq0fGlO8wJ4dxnEp5KA==\n", "4hPKIxxfEHA=\n"), core.pro.android.notify.l2.decrypt("69LS3Z4=\n", "j7ewqPm6BwA=\n"));
     */
    /* JADX WARN: Code restructure failed: missing block: B:7:0x0073, code lost:
    
        return;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private /* synthetic */ void lambda$verifyCardKey2$4(String str, boolean z, int i, String str2, String str3, String str4) {
        try {
            String str5 = String.format(l2.decrypt("KrIIK9/u8sVr7QApmLH1zC/mBX2a6w==\n", "D8EgDr3C0uA=\n"), str, Boolean.valueOf(z), Integer.valueOf(i), str2.replace(l2.decrypt("lw==\n", "sCuGjH7R73s=\n"), l2.decrypt("0aA=\n", "jYekG9/b/Dc=\n")), str3.replace(l2.decrypt("iA==\n", "r+zwXkid/Mk=\n"), l2.decrypt("gd4=\n", "3fnzocbQlas=\n")));
            WebView webViewFindCurrentWebView = findCurrentWebView(str4);
            String str6 = "۫ۤۥۘۨ۠ۥۙۜۨ۫ۤۛۛ۠ۘۧ۬۬۫ۙۦۜۙۗۖۚۚۗۚۨۖۡۧ۟";
            while (true) {
                switch (str6.hashCode() ^ 2088636245) {
                    case -1629984022:
                        String str7 = "ۖۤۦۘۦ۫ۡۘۘۜۛۢۥۧۦ۬ۦ۬۟ۧۨۡۦۘ۟۠ۘۜۘۜۙ۫";
                        while (true) {
                            switch (str7.hashCode() ^ 1011603151) {
                                case -1248381904:
                                    str7 = "ۦۙۖۥۨۘۦۗۗ۟ۛۧۘۖۛ۠ۖۖۗۘۘ۠۠ۡۛ۫ۨۘۛ۟۬ۡۛۡۢۥۧۘ";
                                    break;
                                case -687531464:
                                    k2.logToFloatingWindow(l2.decrypt("2p5+J9c7+HjckU2OBEwAuEt3iBTHvAS5cnuXX6Q74GTfrXuLDXqefLX5Vf5iQ+o4ooAG3Qc=\n", "OR7ubYTddt0=\n"), l2.decrypt("s5ehLQA=\n", "1/LDWGe4XV8=\n"));
                                    webViewFindCurrentWebView.evaluateJavascript(str5, null);
                                    return;
                                case 362774690:
                                    break;
                                case 574323884:
                                    String str8 = "ۤۙ۟ۧۧۗۦۡ۠ۡۜۖ۟ۗۡۘۚۢۢ۫ۤۧۧۡۗۨۥۚۦۧۢۤۛۛۤۢ۫";
                                    while (true) {
                                        switch (str8.hashCode() ^ (-854424321)) {
                                            case 606592326:
                                                str7 = "۬ۢۚ۫۟ۥۧۛۚۜ۬ۢۜۙۗۜ۫۟ۚۡۜۘۡۚۢۘ۫ۡۥۡۘۙۨۥۡۨ۬ۡۥۤۢۡۘۖۥۗ۬ۡۦۘۖۗۘۘۛۗۘۘ";
                                                continue;
                                            case 814028059:
                                                if (webViewFindCurrentWebView == null) {
                                                    str8 = "ۨۧ۫ۖ۬ۡۢ۟ۡۘۙۥۧۘۦۘۗۙۡۘۨۘۦۘۤۢۧۙۢ۠۠۟ۖۘ";
                                                    break;
                                                } else {
                                                    str8 = "ۘۖۜۥۖۜۢۤ۟۟ۖۡۘ۫ۚ۬ۤ۬ۦۜۛۦۦۦ۟ۥۦۖۙ۬ۜۘ۬ۜۥ۟ۛۚۨۤۢ۠ۨ۟۠ۤۡۘۤ۟ۗ";
                                                    break;
                                                }
                                            case 1005765381:
                                                str8 = "۟ۢۨۘۦۦۤ۠ۙۜۖ۬ۛ۬ۜۘۜۗۜۤۧۖۗۥۜۘ۫ۚۡۦ۬۟ۨۤۡۘ۬ۧۤۜۘۨۘ۠ۗ۫ۦۡۤۙۧۦ";
                                                break;
                                            case 1733849683:
                                                str7 = "۫ۧۜۛۥۗۛۥۤ۟۫ۖۦۜۧۘ۟ۙۡۤۨۜ۠۬ۧۤۧۜۨۚۥۢ۫ۢۤۦۨۘۖ۫۟۬ۥۚۨۡ۠ۚ۬ۙۥ۫ۘۥۢ";
                                                continue;
                                        }
                                    }
                                    break;
                            }
                        }
                        break;
                    case -1525677709:
                        String str9 = "ۥۘۨ۟۫ۡۡۙۙۚۘۨۘ۠۟ۨۘۧۥۖۥۨۨ۟ۧۢ۠ۤ۠ۢۜۘۛۡۡۘۖۘ";
                        while (true) {
                            switch (str9.hashCode() ^ 296318007) {
                                case -1643124348:
                                    str6 = "ۨۜۨۧۙ۬ۗۦۜۘۤۨۨۘ۠ۢ۫ۚۦۘۜۢۙۚ۠ۨۘۥۜۨۤ۫ۛ";
                                    continue;
                                case -1251746219:
                                    str6 = "ۗ۬ۡ۠ۥۥۘۡۤۤۖۛۨۦۧۡۡۨۨۧۗ۟ۚۡۥۘۥۛۗۢۥۢ";
                                    continue;
                                case -788570623:
                                    str9 = "ۦۚۜۘۢ۠ۥ۬ۦۘ۫ۘۛ۬ۦۗۨۡۘ۫ۜۜۤۢۡ۟۬ۛۤ۠ۗ۫ۢۙ۟ۜ۬ۦۗ۟ۤۛۧۡۦۗۦ۠۬۟۫۟ۘۧۘ";
                                    break;
                                case -648355065:
                                    if (!this.disposed) {
                                        str9 = "ۙۘۘۘ۟ۚۧۥ۟ۘۘ۬ۧۥۘۡ۬ۙ۬ۖۥۘۖۡۜۘ۫ۦۦۙۜۚۦۡۤۢۜۧۘۜۛۛۢۧۡۖۜۧ";
                                        break;
                                    } else {
                                        str9 = "ۨۦۧۜۖۦۘ۬۫ۧۙۛۡۘۖۢۜۘۘۚۖۘ۬۠ۘ۬ۥۛۖ۟ۧۢۦ۫۟ۧۛۢ۠ۨۘ";
                                        break;
                                    }
                            }
                        }
                        break;
                    case 716239541:
                        break;
                    case 1343432753:
                        str6 = "ۛۙ۠ۛۤ۟ۖۖۜۡۥۖۡۢ۬ۛ۠۬ۥ۫ۜ۠ۥۘۥۘۤۙۗۦۚۖۘ۟ۜۘۘ";
                        break;
                }
            }
        } catch (Exception e) {
            k2.logToFloatingWindow(h.d("s+LgMQt/MOu17dOY2AjIKyILFgIb+MwqGwcJSXh8JdC40vOe/ChW+vWNzOE=\n", "UGJwe1iZvk4=\n", new StringBuilder(), e), l2.decrypt("NSijj18=\n", "UU3B+jjl83Q=\n"));
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x0107 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:102:0x011c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0138 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:104:0x013b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:108:0x00fe A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00b6 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00c2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:83:0x00de A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00f4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00ad A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private /* synthetic */ void lambda$verifyCardKey2$5(String str, String str2, final String str3, final String str4) throws PackageManager.NameNotFoundException {
        String str5;
        int i;
        String str6;
        String str7;
        PackageInfo packageInfo;
        String packageName = this.activity.getPackageName();
        final String strReplaceAll = "";
        try {
            packageInfo = this.activity.getPackageManager().getPackageInfo(packageName, 0);
            str5 = packageInfo.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            str5 = "";
        }
        try {
            i = packageInfo.versionCode;
        } catch (PackageManager.NameNotFoundException e2) {
            i = 0;
            HashMap map = new HashMap();
            map.put(l2.decrypt("6Zq4gf0=\n", "gPTI9Inrw9Q=\n"), str);
            map.put(l2.decrypt("SVQGIqo=\n", "KCR2a85mC2k=\n"), fcRuQsQrcxOAzxwEalcM.APP_ID);
            map.put(l2.decrypt("/DKT5Nz+\n", "nULjr7mHOik=\n"), fcRuQsQrcxOAzxwEalcM.APP_KEY);
            map.put(l2.decrypt("8c73QNW9vCQ=\n", "lauBKbbY9UA=\n"), Utils.getUniqueDeviceId(this.activity));
            map.put(l2.decrypt("uLe4dXD4Ow==\n", "yNbbHhGfXnA=\n"), packageName);
            map.put(l2.decrypt("eTNzqRFguldhN2y/\n", "D1YB2ngP1Ag=\n"), str5);
            map.put(l2.decrypt("6nfzsn6ra4v/feWk\n", "nBKBwRfEBdQ=\n"), String.valueOf(i));
            map.put(l2.decrypt("J7Qj6SYlKCoiuTT2Iw==\n", "UdFRmk9KRnU=\n"), l2.decrypt("d1Np\n", "RmVYxZreLeQ=\n"));
            Object[] objArrPostRequestOnce = Utils.postRequestOnce(str2, map);
            final boolean zBooleanValue = ((Boolean) objArrPostRequestOnce[0]).booleanValue();
            final int iIntValue = ((Integer) objArrPostRequestOnce[1]).intValue();
            Map map2 = (Map) objArrPostRequestOnce[2];
            Object obj = objArrPostRequestOnce[3];
            str6 = "۬ۘۥۘۛ۟ۤۖۦۨۘۤ۠ۙۖۨۨۦ۫ۥۗۘۢۢ۟ۥۘۢۢ۬ۗۤۡۦۨۙۡۙۨ۟۬ۡۘ۠۬ۜۘ۫ۨۢ۬۟ۗۙ۠ۡۘ";
            while (true) {
                switch (str6.hashCode() ^ 419616829) {
                    case -492620237:
                        break;
                    case 880480520:
                        break;
                    case 1588147686:
                        break;
                    case 1924700221:
                        break;
                }
            }
            StringBuilder sb = new StringBuilder();
            str7 = "ۘۚ۫ۖۧۡۚۛۥۡۙ۫۬ۘۙۥ۠ۦۘۙۘۦۘۖۛۖۘ۫ۧۥۘۡۢۦۘۗۨۦ۫ۦۦ";
            while (true) {
                switch (str7.hashCode() ^ (-907175858)) {
                    case -1503025872:
                        break;
                    case -1085114923:
                        break;
                    case 510171060:
                        break;
                    case 1681162970:
                        break;
                }
            }
            final String string = sb.toString();
            this.mainHandler.post(new Runnable(this, str3, zBooleanValue, iIntValue, strReplaceAll, string, str4) { // from class: core.pro.android.notify.a0
                public final JsInterface a;
                public final String b;
                public final boolean c;
                public final int d;
                public final String e;
                public final String f;
                public final String g;

                {
                    this.a = this;
                    this.b = str3;
                    this.c = zBooleanValue;
                    this.d = iIntValue;
                    this.e = strReplaceAll;
                    this.f = string;
                    this.g = str4;
                }

                @Override // java.lang.Runnable
                public final void run() {
                    int i2 = 0;
                    String str8 = null;
                    String str9 = "۬۠۟ۢۙۖ۟ۢۥۤۡۥۘۚ۫ۘۡۥۡۚۚۡۥ۟ۡۘۗۢۦۢۦۧۘۖۖۡۥۘ۠";
                    while (true) {
                        switch ((((str9.hashCode() ^ 180) ^ 144) ^ 586) ^ (-1231680239)) {
                            case -251524064:
                                i2 = this.d;
                                str9 = "ۨ۠ۖۘۦۜۦ۬ۤۢۧۙ۠۫ۧۨۢۧۜۘۙۡۧۚۚۦۚۦۨۘ۠ۥۨۘۗۦۛۨۨۘۗ۟ۦۘ۠۫۟ۙۗ۠ۢۗۡۦۚۥۚۚۜۘ";
                                break;
                            case 281997057:
                                str9 = "ۛۡ۟۫ۜۨۧۡۢ۬ۦۨۘۥ۬ۙۥۚۤۧۦۚۖۡۙۦ۠ۡۘ۫ۜۥۡۚۧۜۜۡۦۚۥۘۛۘۗ۫ۥۜۘۘ۟ۧۧ۠ۖۦۘۥ";
                                break;
                            case 1252851992:
                                return;
                            case 1466056772:
                                JsInterface.k(this.a, this.b, this.c, i2, str8, this.f, this.g);
                                str9 = "ۙۖۖۘ۟۫۠ۨۦۧۘ۬ۨۤ۬۟ۘۘۥۥۦۘۗۘۧۨ۬ۙۨ۬۫ۧۧ۫ۨۧۘۖ۠ۘۘ";
                                break;
                            case 1939414079:
                                str8 = this.e;
                                str9 = "۬ۥۧۚۙۜۘۥۦۘۨۨۜۨۤۖۘۧۨۨۜۚۡ۬ۗۦۘ۫۬ۧۖۗۗۜ۫ۙۙۘۤ۟۬۬ۛۦۚۜۜ۠ۧۢ";
                                break;
                        }
                    }
                }
            });
        }
        HashMap map3 = new HashMap();
        map3.put(l2.decrypt("6Zq4gf0=\n", "gPTI9Inrw9Q=\n"), str);
        map3.put(l2.decrypt("SVQGIqo=\n", "KCR2a85mC2k=\n"), fcRuQsQrcxOAzxwEalcM.APP_ID);
        map3.put(l2.decrypt("/DKT5Nz+\n", "nULjr7mHOik=\n"), fcRuQsQrcxOAzxwEalcM.APP_KEY);
        map3.put(l2.decrypt("8c73QNW9vCQ=\n", "lauBKbbY9UA=\n"), Utils.getUniqueDeviceId(this.activity));
        map3.put(l2.decrypt("uLe4dXD4Ow==\n", "yNbbHhGfXnA=\n"), packageName);
        map3.put(l2.decrypt("eTNzqRFguldhN2y/\n", "D1YB2ngP1Ag=\n"), str5);
        map3.put(l2.decrypt("6nfzsn6ra4v/feWk\n", "nBKBwRfEBdQ=\n"), String.valueOf(i));
        map3.put(l2.decrypt("J7Qj6SYlKCoiuTT2Iw==\n", "UdFRmk9KRnU=\n"), l2.decrypt("d1Np\n", "RmVYxZreLeQ=\n"));
        Object[] objArrPostRequestOnce2 = Utils.postRequestOnce(str2, map3);
        final boolean zBooleanValue2 = ((Boolean) objArrPostRequestOnce2[0]).booleanValue();
        final int iIntValue2 = ((Integer) objArrPostRequestOnce2[1]).intValue();
        Map map22 = (Map) objArrPostRequestOnce2[2];
        Object obj2 = objArrPostRequestOnce2[3];
        str6 = "۬ۘۥۘۛ۟ۤۖۦۨۘۤ۠ۙۖۨۨۦ۫ۥۗۘۢۢ۟ۥۘۢۢ۬ۗۤۡۦۨۙۡۙۨ۟۬ۡۘ۠۬ۜۘ۫ۨۢ۬۟ۗۙ۠ۡۘ";
        while (true) {
            switch (str6.hashCode() ^ 419616829) {
                case -492620237:
                    str6 = "ۧۚۤ۫ۤۛۜۦ۟ۗۘۜۙۥۧۙ۫ۛۛۤۛۗۦ۫ۥۖۘۛ۠ۧۡۤۗ۫ۗۨۥۨۢۜۘۦۘ";
                    continue;
                    continue;
                case 880480520:
                    String str8 = "ۛۦۘۦۜۚۨ۫ۜۢ۟۬ۖۧۘ۫ۘ۬۬ۛۜۥ۬ۛۦۦۜۘ۬ۗۛ";
                    while (true) {
                        switch (str8.hashCode() ^ (-719340563)) {
                            case -747829448:
                                str8 = "ۢۜۨۡۗ۫ۛۜۦۘ۬ۜۛ۬ۧۦۤۦۧۘ۟ۡۧ۫ۚۧۦۥۧۘۖۜۧ";
                                break;
                            case -467831707:
                                if (obj2 == null) {
                                    str8 = "ۡ۠۠ۨۨۡۨۖۥ۟ۧۙۙ۫۬۟۠ۘۘۜ۫ۘۘۧ۟۫ۚۦۘۧ۠ۖۘۦۥۡۜۡۧۡۤۜۘۖۛۚ۫ۡۨ۠ۢ۠ۙۙۜۘۜۡ";
                                    break;
                                } else {
                                    str8 = "۟۠۬ۦۖۦ۟ۦۨۘۘۨۘۗۖۚ۟ۥۨۧۦۛۥۤۘۘۦۜۧۙۨۙۡۛۡ۬ۗ۟ۘ۬ۘ۬ۤۢۡۨ۫ۚ۬ۘۦ۬ۦۘ۟۬ۖ";
                                    break;
                                }
                            case -179361198:
                                str6 = "ۥۘۢۢۚۖۤۜۘۛۥۧۘ۬ۥۧۖۦۙۙۥۥ۟ۙۨۘۧۜۙۢ۫ۘۦۨۢۚۧۜۤۡۘۛۚۜۘ";
                                continue;
                            case 545673820:
                                str6 = "۫ۧۨۧۤۧۚ۬ۡ۟۫ۚ۬ۦۜۘ۬ۖۧۘ۫ۧۦۦۥۧۘۤۚ۠ۢۨۧۖۘۢۢ۠ۗۧۘۖۖۢۦۗۢۜ۫ۘ۠ۢۡۦۘ۬۟ۡۘ";
                                continue;
                                continue;
                                continue;
                        }
                    }
                    break;
                case 1588147686:
                    strReplaceAll = obj2.toString().trim().replaceAll(l2.decrypt("GA5J\n", "RH1iJ06v6cg=\n"), "");
                    break;
                case 1924700221:
                    break;
            }
        }
        StringBuilder sb2 = new StringBuilder();
        str7 = "ۘۚ۫ۖۧۡۚۛۥۡۙ۫۬ۘۙۥ۠ۦۘۙۘۦۘۖۛۖۘ۫ۧۥۘۡۢۦۘۗۨۦ۫ۦۦ";
        while (true) {
            switch (str7.hashCode() ^ (-907175858)) {
                case -1503025872:
                    break;
                case -1085114923:
                    String str9 = "۠ۤۤۡۦۘۘۨۗۛۜۚۢۥۥ۫۠ۡۨ۠۟ۜۚ۬ۢۧۗۛۛۦۘۘۧۘۨۘۙۛۜ";
                    while (true) {
                        switch (str9.hashCode() ^ (-663229449)) {
                            case -1364389899:
                                break;
                            case -958629109:
                                str9 = "۟ۚ۬ۙۖۘۗۗۥۘۜۘ۫ۧۖۥ۟ۨۥ۬ۚۗۦۦ۟ۖۜ۠ۧۨۘۗۗۜۘۡ۫۫۟ۤۡۘۦۘۡۧۥۘۦۥۧۘۡۖۥۘۥۜۥۘ";
                                break;
                            case 649950584:
                                String str10 = "ۦ۬۠ۚ۠ۨۘۜۖۚ۠ۤۦۘۧۨۛۢۨ۬ۜ۟ۤ۟ۧۡۖۢۙۤۤۗ۟۟ۖۚۛۖ";
                                while (true) {
                                    switch (str10.hashCode() ^ (-699081491)) {
                                        case -1290787458:
                                            if (!map22.isEmpty()) {
                                                str10 = "۠ۢۦ۠ۜۡۤۥۘۘۢۥۨۘۘۖۚۘۚۡۦ۬۟ۜ۠ۛۜۨۜۜۜ۠ۖۘۘ۠ۚۗۜ۟ۥۖۗۙۢۢۨۢۗۤ";
                                                break;
                                            } else {
                                                str10 = "ۨۙ۟ۤۙۥۘۖۛۜ۠ۙ۬ۨۙ۫ۙ۠ۜۨۚۥۘۨۡۥۡۛۢۘۧ۟ۖۘ۫ۤ۠ۤۥۘ۟ۥۗۦۚۚۚۥۨ";
                                                break;
                                            }
                                        case -367845863:
                                            str9 = "ۤۨۜۘۚۡۧۘۘۨۨۤۢۨۘۚۥۜۦۥۧۜۤۡۡۥۙۡۧۘۖۖۥۖ۬۫ۗ۫ۗ";
                                            continue;
                                        case 643748839:
                                            str10 = "ۘ۠ۛۙۖۦۛۘۧۙ۬ۨۘۥۘۧۤ۫۬ۙۖۘۢۨۘۙۨۘۘۛ۫ۡۘ۫ۨ۫۟ۛۖۘۦۨۥۘۦۙۘۘۖۖۦۜۦۜۤۙۢۦۗۘ";
                                            break;
                                        case 1498380968:
                                            str9 = "ۜۜۧۙۖۛۨۖۚۦۘۜۘۚۤۡۗ۠ۗ۟ۧۘۘۡۘۘۢۤۢۚۡۛ۫۠ۧۥۡۜۙ۬۠ۘۢ۫ۡ۠ۥۘۛۛۚ۫ۤۥۘ۠ۡۜۘ";
                                            continue;
                                    }
                                }
                                break;
                            case 1217740468:
                                Iterator it = map22.entrySet().iterator();
                                while (true) {
                                    String str11 = "ۨ۬ۚۛ۫ۥۘ۫ۤ۫ۘۙۡۘۛۢۙ۬ۜ۬ۤۜۖۘۚۥۤ۫۟ۗۡۧۤ۟ۧۧۙۜۦۘۖۤۘۘۚۙۘۨۧۘۛۘۥ";
                                    while (true) {
                                        switch (str11.hashCode() ^ 1132436026) {
                                            case -2084505889:
                                                String str12 = "ۙۨۜۘۚۗۨۘۧۨۘۖۥۨۘۧۨۘۤۢۡۘۜۗۜ۠ۧۘۘ۫ۦ۠۟ۘۥۦۜ۬۫۬ۨ";
                                                while (true) {
                                                    switch (str12.hashCode() ^ (-980929741)) {
                                                        case -1941930933:
                                                            str11 = "۟ۡۖۘۡۥۖۘ۫ۢۖۘۦۧ۫ۧۤۨۘ۬۟ۦۙۡۛۦ۫ۘۡۘۗۨ۠";
                                                            break;
                                                        case -1776591423:
                                                            str12 = it.hasNext() ? "۫ۘ۠۫ۧ۬ۘ۬ۙۤۧۥۡۙۡۘ۟۫ۖۨۛۤ۫۬ۨۧ۟ۙۗۜۦ۟ۘۜۚۡۙۨ۟ۢۨۖۧ" : "ۖۛۜۘۡۥ۬ۤۦۗۢۥۘۘۧۦۡۘۨ۠ۨۘۙۦۜۘۚۧ۬ۜۜۡۘۦۘۜۚۙۜۡۧۡۙۢۛۥۢۚۡۜۘۛۙۡۦۗۧۨ۠۫";
                                                        case -1405122489:
                                                            str12 = "ۗ۟۬ۤ۬۠ۡۧۘۡۡۧۘۤۢۦۚۜ۠ۦۥۗۚۜۧۘۥۢۦۗۚۡۥۗۦۘۤۗۙۧۖۢۙۦ۬ۧۖۦۘۢۨۡۡ۠ۖۘۛ۠ۨۘ";
                                                        case -1384173906:
                                                            str11 = "۫ۤۥۘۦۗۧ۟ۘۛۦۦۧۘ۫۫۟۬ۤۨۘۤۙۨۡۡۥۘۢ۠ۧ۠ۤ۬ۖۧۙۡۤۨۘۡۚ۟ۖۡۜۘ۫ۚۜ۬۟ۦۘ";
                                                            break;
                                                    }
                                                }
                                                break;
                                            case -64807485:
                                                str11 = "ۢۖۛۗۜۙۤ۠ۖۘۢۤۨ۬ۜۦۘۤۘۘ۫۬۫ۦ۬۬ۜۗۘۧۘۘۥۧۨۘۨۢۖۘۗۖۡۘۡۤۖۘ";
                                                break;
                                            case 700452716:
                                                break;
                                            case 1716482531:
                                                break;
                                        }
                                    }
                                    Map.Entry entry = (Map.Entry) it.next();
                                    sb2.append((String) entry.getKey());
                                    sb2.append(l2.decrypt("l3A=\n", "rVDOLa2z2vc=\n"));
                                    sb2.append((String) entry.getValue());
                                    sb2.append(l2.decrypt("MqA=\n", "CYBL8jhfeQY=\n"));
                                }
                                break;
                        }
                    }
                    break;
                case 510171060:
                    str7 = "ۜۢۦۘ۟ۙۤۨۙۦۡۚۦۧۨۦۘ۠ۤ۠ۙۛۨۘۦۖ۟ۢۤۚۡۛۗۘۜۡ۬ۗۨۖۤۖ۬ۥۙ";
                    break;
                case 1681162970:
                    String str13 = "ۖۗ۫ۜۜۘۥۖ۫ۥۛۙۖۢۙۖۧ۟ۥۧۧۗۢۧ۫ۘۛۘۥ۫ۧۙۨۘۗۦۡۘۤ۠ۜۘۜ۟ۨۘۚۨۢۖ۟ۜۘۛۤۤۖۢۖ";
                    while (true) {
                        switch (str13.hashCode() ^ (-1261283706)) {
                            case -746877360:
                                if (map22 == null) {
                                    str13 = "۬۬ۤۧۥۨۘ۫ۗۗۘ۫ۧۗۨۧۘ۠۫ۖۘ۫ۛۜۘۛۧ۫ۨۥ۬۟ۖۘۗۡۗۗۚ۬۬ۛۗۡۨۘۚۨۖۘۖۥۡۘ";
                                    break;
                                } else {
                                    str13 = "ۢ۟ۙۤۙۡۘۗۖۖۚ۠ۚۚۢۖۘۛۧۢ۬ۦۗۨۥ۫ۖۛۦۖۢۖۗۜۨۘۗۢۥ";
                                    break;
                                }
                            case 736837090:
                                str13 = "ۧۢ۬۬ۧۥ۬۬ۨۘۖۤۦۘۢۜۘۘۗۜۢۘۡۛۡ۠ۦۗۡۘۨۘۖۘۜۚۦۗ۠۟ۙۡۡۤۧۥۘ۫۫ۛۨۡۛۡۡۨۨۘۢ";
                                break;
                            case 1257882343:
                                str7 = "ۚۜۚۙۙۦۘۘ۬ۧۙ۠ۢۚۤۧۡۛ۠ۖۡ۠ۘۥۥۙ۠۫۬ۤۨۜ۬ۜۘۧۢۘۨ۬ۥ";
                                continue;
                            case 1840790448:
                                str7 = "ۧۛۧ۫ۢۢۚۚۖۗۛ۫۫۟ۙۜۢ۟ۦۘۘۡۗۨۘۡۤۥۙ۠ۘۘ۠ۢۡۘ۠۟ۢۡۗۡۘۜۗۜۖۥۥ۠ۨۘ";
                                continue;
                        }
                    }
                    break;
            }
        }
        final String string2 = sb2.toString();
        this.mainHandler.post(new Runnable(this, str3, zBooleanValue2, iIntValue2, strReplaceAll, string2, str4) { // from class: core.pro.android.notify.a0
            public final JsInterface a;
            public final String b;
            public final boolean c;
            public final int d;
            public final String e;
            public final String f;
            public final String g;

            {
                this.a = this;
                this.b = str3;
                this.c = zBooleanValue2;
                this.d = iIntValue2;
                this.e = strReplaceAll;
                this.f = string2;
                this.g = str4;
            }

            @Override // java.lang.Runnable
            public final void run() {
                int i2 = 0;
                String str82 = null;
                String str92 = "۬۠۟ۢۙۖ۟ۢۥۤۡۥۘۚ۫ۘۡۥۡۚۚۡۥ۟ۡۘۗۢۦۢۦۧۘۖۖۡۥۘ۠";
                while (true) {
                    switch ((((str92.hashCode() ^ 180) ^ 144) ^ 586) ^ (-1231680239)) {
                        case -251524064:
                            i2 = this.d;
                            str92 = "ۨ۠ۖۘۦۜۦ۬ۤۢۧۙ۠۫ۧۨۢۧۜۘۙۡۧۚۚۦۚۦۨۘ۠ۥۨۘۗۦۛۨۨۘۗ۟ۦۘ۠۫۟ۙۗ۠ۢۗۡۦۚۥۚۚۜۘ";
                            break;
                        case 281997057:
                            str92 = "ۛۡ۟۫ۜۨۧۡۢ۬ۦۨۘۥ۬ۙۥۚۤۧۦۚۖۡۙۦ۠ۡۘ۫ۜۥۡۚۧۜۜۡۦۚۥۘۛۘۗ۫ۥۜۘۘ۟ۧۧ۠ۖۦۘۥ";
                            break;
                        case 1252851992:
                            return;
                        case 1466056772:
                            JsInterface.k(this.a, this.b, this.c, i2, str82, this.f, this.g);
                            str92 = "ۙۖۖۘ۟۫۠ۨۦۧۘ۬ۨۤ۬۟ۘۘۥۥۦۘۗۘۧۨ۬ۙۨ۬۫ۧۧ۫ۨۧۘۖ۠ۘۘ";
                            break;
                        case 1939414079:
                            str82 = this.e;
                            str92 = "۬ۥۧۚۙۜۘۥۦۘۨۨۜۨۤۖۘۧۨۨۜۚۡ۬ۗۦۘ۫۬ۧۖۗۗۜ۫ۙۙۘۤ۟۬۬ۛۦۚۜۜ۠ۧۢ";
                            break;
                    }
                }
            }
        });
    }

    public static /* synthetic */ void m(JsInterface jsInterface, MediaPlayer mediaPlayer) throws IllegalStateException {
        String str = "ۚۤۡۘۡۤۦۜ۟ۙۤۖۧۛۤۡۡۙۛۘۡۤۥۗۙۚۨۥۡۧۘ";
        while (true) {
            switch ((((str.hashCode() ^ 753) ^ 667) ^ 84) ^ (-376794165)) {
                case -329267202:
                    return;
                case 1321140934:
                    str = "ۛۗۢۚۘۥۘۦ۟ۡۡۢ۬۬ۙۖۜۡۜۖۧ۠ۚۧۘۘۨۙۢۙۤۚ";
                    break;
                case 1341757024:
                    jsInterface.lambda$playMusic$8(mediaPlayer);
                    str = "ۨۡۦ۬۟ۡ۟ۛۜۧۤۦۘۖۤۢۡ۟ۘۧ۟ۘۘۘۘۜۙ۠ۦۘۚ۟ۖۢ۟۟ۛۙۥۘ";
                    break;
                case 1508035431:
                    str = "۟۟ۙۥۘۧۘۛۡۘ۠۫ۡۘۨ۠ۥۘۛۡۚ۠ۛ۬۬ۚۡۘۘۢۨۘ۬۫ۜ۠ۚۜۘۖ۫۟";
                    break;
            }
        }
    }

    public static /* synthetic */ void n(JsInterface jsInterface) throws IllegalStateException {
        String str = "ۥۦۗۨۧۖۘۢۤ۫ۘ۫ۘۚۡۥۘۥۨۘۧ۬ۙۤۛۖۘۙۡۥۘۛۡۖۘ";
        while (true) {
            switch ((((str.hashCode() ^ 152) ^ 765) ^ 672) ^ (-1468020316)) {
                case -51963991:
                    str = "ۚۚۜۘ۬ۜۖۘۢ۫ۡۗۜۧ۠۟ۧۧ۠۬ۢ۫ۦۤ۟ۙۗۤۛ۫۟ۧ۫۫ۧۥۚۖۥۙۚۘۗۚ";
                    break;
                case 547571389:
                    return;
                case 570870841:
                    jsInterface.lambda$pauseMusic$13();
                    str = "ۛۤۤۡۚۡۘ۟ۖۡۘۧۛۗ۬ۡ۠ۢۦ۬ۚۘۧۨۨۧۙۘۘۚۦۜ۫ۦ۠ۧۢۜۘۦۡۡۦۧ۫ۗۧۨۘۛ۠ۥ";
                    break;
            }
        }
    }

    public static /* synthetic */ void o(JsInterface jsInterface, String str) {
        String str2 = "ۗ۫ۘۘۤ۬ۖۘۡۧ۟ۨۚۦۘ۫ۢۦۤ۬ۦۘ۬ۤۤ۫ۨۙ۠ۡۤۚۙۨ۠ۨۥۤۦۧۜۡۖۚۚ";
        while (true) {
            switch ((((str2.hashCode() ^ 862) ^ 33) ^ 55) ^ 1464619321) {
                case -1527593602:
                    jsInterface.lambda$close$0(str);
                    str2 = "ۙۧۨۜۥۜۘۦۨ۫ۖۜۧۜ۟ۡ۬ۢۗۜ۟ۙۛۜۨۗۗۨۤۛۡۘ۫ۘۡۘۢۘۥۘۤۖ۬ۘۦۛۥۙۢۗۦۤ۟۠ۤۧۦۘ";
                    break;
                case -1139889352:
                    str2 = "ۤۖ۟ۜۡۨۘۧ۟۠ۘۦۙۗۖۛۖۘۜۤۢۖۘۢ۠ۡۧۡۖۘ۫ۘۦۘ۠ۦۦ۫ۗۘۛۜۦۛۙۨۘۢ۬۠ۧۧۨۘۘۙۖ۬ۘۦ";
                    break;
                case -892899175:
                    return;
                case -800217490:
                    str2 = "ۗۦۧۧۚۦۚۙۨ۬ۢۧۚۧۛ۫ۖۘۛۢۥۨۗۜۨۤ۬ۜۘۗۛۨۘ۫ۥۥۘ۟ۥۨۘ۬ۘۧۘ";
                    break;
            }
        }
    }

    public static /* synthetic */ void p(JsInterface jsInterface, String str, String str2, String str3, String str4) throws PackageManager.NameNotFoundException {
        String str5 = "ۧ۬ۦۘۨۧۧ۬ۨۢۗۡۛۢۜ۫۬ۖۜۘۗ۟ۤۤ۫ۚۚۧۘۚۦۚ۬ۥۘۘۦۡۡۥۖۨۤۜ۫";
        while (true) {
            switch ((((str5.hashCode() ^ 940) ^ 73) ^ 434) ^ (-1988406641)) {
                case -1648745404:
                    jsInterface.lambda$verifyCardKey$3(str, str2, str3, str4);
                    str5 = "ۡۗۙۗۤۡۘۚ۟ۢۚۖ۟۫ۗۘ۬ۡۖۘۗۛۦۘۗۥۨۘۤۗ۬۫۠ۥۘۦۦۜۘۗۚۛ۟ۜۦۚۛ۟ۙۥۤۛ۟ۡ۫ۖۧۘۥ۟";
                    break;
                case -540163092:
                    str5 = "۠۫ۤۘ۬ۤۚۚۥۥۚۜۘۛۥۨۡۦۘۦۜۦۘۙ۠ۜۖۦ۫ۧۖۘۘۡۙۧۡۖۦۘ";
                    break;
                case -66285087:
                    str5 = "۬۠۬۠ۤۛۧۨۤۧۛۛۦ۬۠ۙ۟۟ۙ۟ۢ۟ۖۚۦۛ۟ۖۛۚ۫ۢ۫ۦۨۜۙۙۘۤ۠ۨۘۗۢۡۜۦۖۘۘۙۙۛۦۧ";
                    break;
                case -53941139:
                    return;
                case 548203908:
                    str5 = "ۘ۟۟ۛۖۙۘۦۘۛۤۛۗ۟ۨۖۤۦۘۡۥۚۚۦ۬ۙۨۘۘۖۚۖۜۤۖۨ۫ۦۘ";
                    break;
                case 1732997093:
                    str5 = "۟ۥ۠۫ۗ۫ۨۢۥۢۚۦ۬۬ۢۥۚ۫۫۠ۖۘۛۛۨۘۧۧۚ۬ۘۨۙۖۡۘۗۖۗۢۗۛۚ۟ۥ";
                    break;
                case 1772984573:
                    str5 = "ۥۜۧ۟ۜۖۖۦ۠ۧۜۖۦۜۚۙۜۡۥۧ۠ۥ۬ۜۘۧۢۛ۟ۗۚۢۡۘۨۗۖ";
                    break;
            }
        }
    }

    public static /* synthetic */ boolean q(JsInterface jsInterface, MediaPlayer mediaPlayer, int i, int i2) {
        String str = "ۚۖۙۢۖۨ۟ۖۖۖۢۖۡۥۗ۬ۜ۠۬ۦ۫ۦ۠ۦۨۙۤۤۥۡۚۜۤۦ۫۠ۛۖ۬ۡۡۢ";
        while (true) {
            switch ((((str.hashCode() ^ 176) ^ 451) ^ 2) ^ 693608407) {
                case -2099996122:
                    str = "ۜۨۥۘۧۨۜۗۙۨۘۗۥ۠ۜ۟ۢ۬ۜۨۢۤۡۜۛۖۛۚۜۤۢۘۗۛۥۥۘۙۜۖ۬ۖۘ";
                    break;
                case -929614345:
                    str = "۟ۚۙۛۦ۟۠ۚۦۘ۟۠ۚۨۖۗ۫۟ۥۘ۟ۜۨۘ۬۫ۜۘۙ۬ۡۘۡۗۥۢۖۘۢۗ۬";
                    break;
                case 171007857:
                    str = "ۥۥۜۘۙۚ۫۟ۥۧۘۚۤۦۡۨۗۥۘۨۘۨ۟ۥۘۤۙۨۧۥۖۘ۬ۗۥۖۧۖۘۘۚۢ";
                    break;
                case 519819387:
                    str = "ۨۧۗۧۚۥۘۥ۫ۚ۠ۖۨۘۜۛۡۘ۫ۢۖۘۤ۫ۜ۬ۥۡۥ۫ۨ۟ۥ۟۠ۥۖۘۧۦۢۚۛۨۘۦۢۦۗۙۦۘۦ۠ۙۧ۠ۖۘۧۡ";
                    break;
                case 1882583208:
                    return jsInterface.lambda$playMusic$10(mediaPlayer, i, i2);
            }
        }
    }

    private void runJS(String str) {
        String str2 = "ۖ۟ۖۘۙۛۖۘ۫ۢۛۖ۠۠ۗۦۤۗۥۘۤۤ۠ۘۨ۠ۚۨۖۢۤۢ";
        while (true) {
            switch ((((str2.hashCode() ^ 670) ^ 121) ^ 912) ^ (-21087653)) {
                case -2040515508:
                    return;
                case 769295218:
                    str2 = "ۢۡۨۘۗۙۗۧۨ۬۬ۢۧۥۧۡۘۖ۠ۘ۫ۡۥۤۨۘ۫ۜۨۤ۬ۗ";
                    break;
                case 802391332:
                    this.mainHandler.post(new z(this, str, 1));
                    str2 = "ۜۨۥۨۨۖۘۛۗۥۘۧۦۛۥۢۦۢۖۡۘۤۧۡۘۗۛۘۨۗۤۥۛ۠ۥ۟ۦۘۖۖۘۢۥ۟ۙۥۧۘۨۥۧ۟۟ۨۘ";
                    break;
                case 1345960234:
                    str2 = "ۤۛۥۤۘۨۘۨ۬۟ۤۥۘۦۡۖۦۗۖ۠۠۫ۙۤۖۘ۟ۡۖۢۜۗۥۦۖۘۨۛۡۘۢ۟ۢۡ۬ۢ";
                    break;
            }
        }
    }

    private void stopAndReleaseMusic() throws IllegalStateException {
        MediaPlayer mediaPlayer = this.mediaPlayer;
        String str = "۫ۛ۬ۥۜۛۥۛ۟۟ۛۥۘۢۤۜ۠ۡۖۦۛۥۦۚ۫ۥۙۧۦ۬ۗۙۧۧۧ۠ۤ";
        while (true) {
            switch (str.hashCode() ^ (-986354830)) {
                case -2135502256:
                    String str2 = "ۤۛۗۜۘۦۘۘۢۘ۟ۦۖۘ۟ۦۧۤۤۢۚ۠ۨۘۘۛۗۗۙۡۘۙۦۡۤۘۧۡۢۖۘۜ۟ۨ۠ۦۡۘۗۥۧۘ۫ۙۘۘۙۨۘۨۜ۬";
                    while (true) {
                        switch (str2.hashCode() ^ (-431955902)) {
                            case -1564850885:
                                str = "ۨۡۤۗ۫ۛۚۨۗۘ۠ۚۢۨۡۘۜ۫ۗۢ۬ۘۘۘۡۧۘ۟۫ۤ۬ۡۖۘ";
                                continue;
                            case -1301354560:
                                str2 = "ۦ۟ۨ۬ۨۥۘ۬ۛۨۘۗۖۛۜ۬ۚۡۥۙۥۧۘۘۨۚۘۘ۠ۨ۠ۢۚ۟ۥۧۚۖۤۡ";
                                break;
                            case -1120947350:
                                str = "ۖۤۖۘۙ۟ۖۙۘۖۗۙۚۨۘۘۤۥۢۖۘۘۦۙۢۛۡۡۖۧۦۜۙۡۤۚۨۘۚۜۘۦۖۧۘ";
                                continue;
                            case 1980628082:
                                if (mediaPlayer != null) {
                                    str2 = "ۥۦۚ۫ۗۖ۠ۥۖۘۜۜۡۘۦۖۙۢۗۥۧۡۦۢۖۘۦۙۥۤۙ";
                                    break;
                                } else {
                                    str2 = "ۜۖۡۗ۫۠۫ۨۗ۬۠ۡۦۗۛۤۚۨۘۖ۬ۙ۟ۦۘۘۙۦۥۘۤۧۗ";
                                    break;
                                }
                        }
                    }
                    break;
                case -1447732777:
                    String str3 = "ۜۨۦۤۖۧ۠ۘۘۜۙۤۨ۠ۘۥۨۘ۟ۢۨۘۘۘۜۗۚۘۘۜۛۡۘۖ۬ۜۘۥۚۡ";
                    while (true) {
                        try {
                            switch (str3.hashCode() ^ 1759801695) {
                                case -1899179428:
                                    break;
                                case -1033736101:
                                    this.mediaPlayer.stop();
                                    break;
                                case -704520931:
                                    str3 = "ۨۜۛۡۨۚۥ۠ۘۧۙ۟ۤۤۙۜۢۢۨۗۨۥۢ۟ۤۥۡۜ";
                                    continue;
                                case 840311792:
                                    String str4 = "ۥۨۡۘۨۡ۟۫ۗۖ۫ۘۚ۟ۖۘۦۖۖۜ۟ۤۥ۫ۤۨ۠ۚۨ۬۟۠ۚۖۡ۠ۘۥۛۙۦۨۦۘۛ۬ۖۘۤۡۗ";
                                    while (true) {
                                        switch (str4.hashCode() ^ 1067791414) {
                                            case -1146485503:
                                                if (!mediaPlayer.isPlaying()) {
                                                    str4 = "ۥۧ۫۟ۡۖ۟۟ۙۚۛۛۦۘۡۤ۟ۡۘۚۙۢۥۖۧۘ۟۟ۨۘ۟ۗۛ۬ۛ۟ۘۛ";
                                                    break;
                                                } else {
                                                    str4 = "ۖۖۢۖۥۦۙۧ۫۟ۘۘۨۤۧۨۢۗۧۚۜۨۗۘۘۤۤۘۘۥۡ۟ۖۚۘۦۦ۬۟۬ۙۘۡۤۙۨ۠ۢۧ";
                                                    break;
                                                }
                                            case -313573251:
                                                str3 = "ۨۚۚۚۤ۟ۗۡۧۘۥۜۨۧۥۘۘۥۤۡ۬ۢۦۘۜۜۘۘ۫ۛ۬۠ۧۛۛ۟ۢۚۛۦۘ۫ۥۜۘۖۜۨۘ";
                                                continue;
                                                continue;
                                            case 404571181:
                                                str4 = "ۛۗۜۘۚۜ۫ۥۥۦۘۘۛۖۘ۠۫ۡۘۚۚۨۤۡۜۘۢۤۙۤ۫ۤۢۜۡۘ۟ۥۧۖۤ۬ۗۥ۟۫ۤۥۘ۬ۗۛۚۧ";
                                                break;
                                            case 442023174:
                                                str3 = "ۨۦ۠ۦۧۨۘ۠ۗۥۧۥۜۘ۬ۨۢۡۥۖۧۦۘۘۘۜۧۤ۟ۜۗۡۖۚۤۗۙۨۚ۫ۧ۫ۥۥ";
                                                continue;
                                        }
                                    }
                                    break;
                                default:
                                    continue;
                            }
                        } catch (IllegalStateException e) {
                        }
                    }
                    this.mediaPlayer.release();
                    this.mediaPlayer = null;
                    return;
                case -912142663:
                    return;
                case 1248693192:
                    str = "ۘۢۗۜۤۨۨۙۥۘۚۡۖۤۢۜۙۤۥۢۙۙ۫۫۬ۖۨۘۗۥۛۨۜۥۡ۬ۨۗۡۨۛ۫ۛۤۙۤۨۢ";
                    break;
            }
        }
    }

    @JavascriptInterface
    public void QQGroup(String str) {
        String str2 = "۠ۗۗۘۥۙۢۡۤۧۦۘۡۜ۫ۘۗۜۥ۠ۜۘۗۚۛۥۜۧۘ۫۠ۖۘ۟ۜۘ۟۟ۙۢۛۖۗۛۥۘ";
        while (true) {
            switch ((((str2.hashCode() ^ 220) ^ 905) ^ 817) ^ (-2031837623)) {
                case -1514866443:
                    Toast.makeText(this.activity, l2.decrypt("tQ8xu6mC3EjzfRT4S0befPd+Gcv8i5Mn/RF5/p/xsEkC\n", "U5iRXRoXOcI=\n"), 0).show();
                    str2 = "ۡ۫ۡ۟ۨۘۘ۫ۢۡۘۗۗۖۨ۬ۙ۫ۛ۫۟ۗۘۘۙۦۨۗۢۖۗۦۢ";
                    break;
                case -1392278907:
                    String str3 = "۬ۚۨۥۚۚۗۛۜۘۘۙۡۦۙۦۦۖۘۡۛۢۢۖۘۘۗ۟ۖۘۧۤۖۘ۟ۤۨۗۙۥۘ۬ۖ۟ۚۜۤ";
                    while (true) {
                        switch (str3.hashCode() ^ (-854443708)) {
                            case -1607176050:
                                String str4 = "ۨۙۗۜ۟ۚۤۦۘۘۧۘۚ۫ۨۨۨۧۘ۠ۢ۠۠۠ۦۘۨ۫ۛۨۧۖۘ";
                                while (true) {
                                    switch (str4.hashCode() ^ 2031451791) {
                                        case -1857804188:
                                            str3 = "ۚ۬ۨ۟ۤۜۘۨ۠ۥۡ۫ۦۘۧۤۦ۟ۦ۬ۖ۬ۖۘۧۨۢۗۤۦۨۨۦۢۘ۬ۜۥۘۖۛ۠ۘ۟ۨۘۜۗۘۢۛ۠ۤ۬ۖۨ۫ۘۘ";
                                            break;
                                        case 539704802:
                                            str4 = !Utils.joinQQGroup(this.activity, str) ? "۬ۛۙۘۙۘۘ۟ۤۡۘ۬ۖۡۗۖۡ۫۟ۛۛۙ۫ۡ۟ۦۙۦۘۧۨۢۜ۫ۥۘۗۘۢۚۚۛۢۗۡۚۥۨۘ۟ۛ۫۠ۘۘۦۛۥۘ" : "ۖۖۥۢۙۦۦۥۗۗۧۥۘۥۡۖۘۨۢۘۘۗ۠ۡۘۚ۫ۡۘۛۨۘۢۛۜۢۨۜۡۦ۬ۛۜۡۚۜۦۙۥۘۦۚ";
                                        case 950340017:
                                            str4 = "۫ۛۨۦۥۨۘۛۗۤۜۜۚۦ۬ۖۘۢۦۢ۠۫ۛ۫ۖۢۡۥۜۙۛۦۘۧۤۥۘۖۙۚۙۤۢۙۛۥ";
                                        case 2060946701:
                                            str3 = "ۘ۠ۖۘۤۨۛۢۢۜۘ۟ۚۛ۠ۜ۠ۘۗۖۧۤۛۤۧۘۚ۠ۜۘ۟ۚ۫ۖ۫ۧۘ۬ۨۡۥ۫ۨۦ";
                                            break;
                                    }
                                }
                                break;
                            case -184940334:
                                str3 = "۬ۨۦۘ۟ۦۦۡۚۜۤۧۥۘۖ۟ۜۗۗ۠۠ۗۦ۟ۗۡۘۖ۬ۡۡ۬ۧ۠ۚۜۤ۠ۛۛۤۗۘۨ۬";
                                break;
                            case 1461949249:
                                str2 = "ۡۢۨۤۡۦۗۚ۟۠ۚۚۨ۫۬ۙۥۡۘ۫ۗۤ۬ۜۦۘۘۜۘۘۙۗۥۥۖۖۘۗۜۧۘ";
                                continue;
                            case 1696161234:
                                str2 = "ۡ۫ۡ۟ۨۘۘ۫ۢۡۘۗۗۖۨ۬ۙ۫ۛ۫۟ۗۘۘۙۦۨۗۢۖۗۦۢ";
                                continue;
                        }
                    }
                    break;
                case 9682224:
                    return;
                case 692663376:
                    str2 = "ۧۖۗ۫ۥۗۨۖۥ۠ۡ۬ۚۦۖۘۜ۟ۧۡ۠ۗ۫۟ۖۛۦۖۘ۬ۘۘۘۨۡۦۗۢۢۘۗۘ۬ۧۥۘۘۨۜ۬۬ۧ";
                    break;
                case 1651799150:
                    str2 = "۟ۚۜۛۢۤ۠ۖۨۘۨ۟ۦۧۘۤ۫ۦۘ۟۠ۦۢۥۚۙۨۢۧۡۜۘۡ۬ۘۗۘ۫";
                    break;
            }
        }
    }

    @JavascriptInterface
    public void Toast(String str) {
        String str2 = "ۡۦۦۘۥۗۧۛۨۙۧ۠ۖۘۥ۟ۖۥۨۜۗۧۨۨ۫ۨ۟ۖۥۧ۬ۥۘ۠ۚۦۘۜۙۧۥۦۨۚۜۧۘ۫ۢۨۢۙۡۘۦۜۦۘۤۤ۟";
        while (true) {
            switch ((((str2.hashCode() ^ 323) ^ 467) ^ 364) ^ 1378705600) {
                case -1750593653:
                    Toast.makeText(this.activity, str, 0).show();
                    str2 = "ۖۜۧۘۨۤۡۖۡۢۢ۬۠ۡۛۙۨ۟ۙۨۛۘۛۜۦ۬۠ۢۙۤۜ";
                    break;
                case -586299911:
                    k2.logToFloatingWindow(l2.decrypt("hGOoPzZ6XsuCbJuW5Q2EAQaQTFWDCmmI1HbQ1850YO2Ad5A=\n", "Z+M4dWWc0G4=\n"), l2.decrypt("XDeU56Y=\n", "OFL2ksHbtIw=\n"));
                    str2 = "ۥۥۨۘۡ۬ۤۚۚۖ۟ۙۥۘ۫ۘۧۧۜۥۘۦۤۘۧۡ۟ۢۢۘۦۘ";
                    break;
                case 670062139:
                    str2 = "ۧۘۥۨۢۛ۬ۡۡۘۦۤ۠ۚۨۚۖۖۖۘۧ۫ۘۜۜۛۗۖۖۘۜ۬۟";
                    break;
                case 821266238:
                    str2 = "ۚۙۘۘۥ۠ۥۘۢۖۖۘۦۢۢۥۤ۠ۛۨۘۥۡ۫ۢ۫ۚۙۙ۠ۚۙۙ۫ۛۘۚۗۥۘ۟ۨۘۚۙۗۦۧۖ۫۠ۥۘ";
                    break;
                case 1532448143:
                    return;
            }
        }
    }

    public void bindDialog(Dialog dialog, WebView webView) {
        String str = "۠ۤۢۡۢۛۗۘۘۜۜۡ۠ۧۙۢ۠ۡۘۦۦۖۘۙ۟ۙۖۜۦۜ۫۟ۨۧ۟ۗۡۘ";
        while (true) {
            switch ((((str.hashCode() ^ 103) ^ 571) ^ 797) ^ 785819801) {
                case -703590412:
                    this.boundWebView = webView;
                    str = "۠ۜۖۘۛۤۦۘ۬۫ۦۘ۬ۥۢۖ۬۬۠ۛۥۘۢ۬ۘۘۥۥ۠ۦۡۜۙۨ۟ۙۥۥۘۨۥ۠";
                    break;
                case -687043076:
                    this.disposed = false;
                    str = "ۙۧۨۘۘۜۧۗۡۤ۠ۚۛۡۨ۟ۖۚۘۘۨ۠ۥۘۧۡۜ۠ۦۖ۟ۧۧۘۨۜۘۚۜۡ";
                    break;
                case -220141625:
                    str = "۫۠ۙۦۜۥۚۙ۬ۦۡ۟ۙۗۥ۠ۤۧۙۨ۟۫ۦۖۥۗۤۨۚۚۢۚۥۘۥۙ۬ۡۚۦۥۥۘ۠۠ۡۘۨۤۧ۟۠ۨۘۗۤ";
                    break;
                case 229052145:
                    str = "ۖۖ۠ۤۢۚۢۢۤۗۧ۠ۧۡۡۘ۫ۗۡۛۖۧۖۤۥۘۗۦۖۚۗۡۘۨۙۜ۬۟ۗۖۡۚۤۛۜۘۦۖ۬۬ۨۘ";
                    break;
                case 256436517:
                    return;
                case 323089089:
                    this.boundDialog = dialog;
                    str = "ۤۛۗ۬۫ۥۥۜۚ۫ۜۡۘۥۡۛۚۤۡ۫ۛۡۧۨۡۘۙۦ۬ۖۨۖۘۧۙ۟ۡۗۨ";
                    break;
                case 1709805302:
                    str = "ۥۨ۫۟ۦۖ۬ۜ۠ۗ۫ۡۘ۟۠ۦۘۤۢۛۢۖۨۘ۟ۗ۬ۙ۠ۖۖۛۦۨۘۡۘۥۖۚ۬ۡۦۘۛۙۜۘۤۘۥۘ۠ۜۧۗۖۡۧ۫ۨۘ";
                    break;
            }
        }
    }

    @JavascriptInterface
    public void close() {
        String str = "ۡۙۚ۬ۥۡۙۖۘۗ۫۫۟ۤۡۨۡۨ۠۟ۜۘۢۚۡۘۢۦۥۘۥۘۥۘۡ۬ۘۤۨۖۘ۫ۤۜۘ۠ۙۜۘ";
        while (true) {
            switch ((((str.hashCode() ^ 203) ^ 178) ^ 525) ^ 1813837445) {
                case -800803339:
                    this.mainHandler.post(new w(this, 0));
                    str = "ۢۢ۬ۡۗ۬۟ۢۙۦۛ۠ۙۥۘۦۘۧۘۦ۟ۖۘ۠ۤ۟۠ۘۥۘۜۙۘۦۜ۬ۦۗۥ";
                    break;
                case -416695822:
                    k2.logToFloatingWindow(l2.decrypt("XclWXqwJeXxbxmX3f36UtdE6ozzWzxFPB691gRdNXDEOyiGAVw==\n", "vknGFP/v99k=\n"), l2.decrypt("cgJZU50=\n", "Fmc7JvoXZbM=\n"));
                    str = "ۡ۟ۜۛۚۗۙۙۜۘۗۚۡۘۡۘۖۜۢ۬۬ۥۥۖۢۢۗۦ۬ۤۜۘ";
                    break;
                case 384654928:
                    str = "ۙۙۖۘۡ۬۠ۗۦ۬ۥۖۜۘ۬ۚۦ۫ۜۘۨۚۢ۟۟ۨۧۧۦۨۨ۫";
                    break;
                case 1535341935:
                    return;
            }
        }
    }

    @JavascriptInterface
    public void close(String str) {
        String str2 = "ۙۦۜۘۜۨۧۤۧۡۘۘۜۨۚۦۖۥ۠ۖۘۤ۬ۦ۟ۜۗۨۧۨۘۛۨۜ";
        StringBuilder sb = null;
        PrintStream printStream = null;
        while (true) {
            switch ((((str2.hashCode() ^ 393) ^ 728) ^ 595) ^ 389375521) {
                case -1274455638:
                    sb.append(l2.decrypt("5mdUt96R5266BU3J\n", "A+LnXkk8AtI=\n"));
                    str2 = "ۗۤۚۢۨۘ۬ۘۧۗۢۡۘۨۘۥ۟ۡ۬ۦ۟ۜۘۤۥۘۖۦۜۖۤۜۘۙۗۨۘۨۤۙۚۜ۬ۖ۟ۥۘ";
                    break;
                case -1196707410:
                    str2 = "۟ۘ۬ۧۙۨۤۙ۫ۧۛۘۜۨۘۥۡۨۧ۟ۥۡ۠ۦۘۤ۫۟۫ۡۙۢۜۨۡۤۗۥۜ۠ۘۛ۟";
                    break;
                case -1004657523:
                    str2 = "ۤ۬۟ۢۜ۬۠ۙۡۗۖ۫ۙۛۡۢۚ۬ۥۢ۠ۙۦ۟ۛۡۛۢۡۧ";
                    printStream = System.out;
                    break;
                case -980289380:
                    this.mainHandler.post(new z(this, str, 0));
                    str2 = "ۘۗۘۘۙۚۜۘۗ۠ۤۗۧۘۘۤ۠ۢۤۧۜۘۦۖۤۢۗ۠۠ۨۜۘۚۧۡۧ۟ۘۘۛۧۘۘ";
                    break;
                case -908947644:
                    return;
                case -835884941:
                    printStream.println(sb.toString());
                    str2 = "ۚۢ۠۟ۥۧ۬۠ۡۢۤۥۦۥۖۙۚۧۘۢ۬ۨۖۦۨۙۢۨۘۗۥ۟۟۬ۤۜۧ۟ۦۡ۬ۗ۟ۡۘ۬ۙۥۘ";
                    break;
                case -826585966:
                    k2.logToFloatingWindow(l2.decrypt("HWIhf/4vBbUbbRLWLVjofJGR1BVLXzL2TXdZlwYhO5MZdhk=\n", "/uKxNa3JixA=\n"), l2.decrypt("AN4yFWw=\n", "ZLtQYAvzfVA=\n"));
                    str2 = "ۚۦۧۖۛۨۦ۟ۥۘ۠ۥۘۥۡ۬ۥۢۗۥۛۘۘۢۤۖۘۗ۠ۡۗۛۧ";
                    break;
                case -506529701:
                    sb.append(str);
                    str2 = "ۚۛۗۗ۠۟ۙ۟ۨۘۧۥۜۨۨۘۘۥ۠۠۫ۖۜۘۤۨۨۘۘۥۘ۟ۢ۬ۛۜۘۤ۟۬";
                    break;
                case 753772886:
                    str2 = "ۘۢۥۘۥ۟ۖ۬ۛۤ۫ۨۨۦۘۢۢ۫ۛۢۥۘ۟ۛۨۘۧ۫ۥۘۚۖۥۘۖۦۡۥ۬ۘۙۤۖۘۡ۠ۢ۫ۦۨۘۙۡۦ";
                    break;
                case 771641429:
                    sb = new StringBuilder();
                    str2 = "ۧ۟ۜۘۚۡ۠ۗ۫ۖۙۥۦۘۗۧۜۜۗۢ۠ۡۖۘۜۗ۟ۨۘ۬ۧۚۧ۠۬ۛ۟ۤۤۥ۬۫ۤۜ";
                    break;
            }
        }
    }

    @JavascriptInterface
    public void copyText(String str) {
        String str2 = "ۘ۫ۥۢۜۜۙ۟ۛۡۧۜۡ۫ۨۢۤۜۚ۟ۦ۫ۢۢ۠۬ۙۖۗۙ۠ۛۜۘۖۖۡۢۥۡۡ۠ۘ";
        while (true) {
            switch ((((str2.hashCode() ^ 324) ^ 853) ^ 695) ^ 817628459) {
                case -1759434147:
                    ((ClipboardManager) this.activity.getSystemService(l2.decrypt("pf+czN2l0q+i\n", "xpP1vL/Ks90=\n"))).setPrimaryClip(ClipData.newPlainText(l2.decrypt("+7UY4BGh\n", "HjOdBb8Ynj8=\n"), str));
                    str2 = "ۨۛۜۛۡۡۧۢۖۘۜۗۗ۠۟ۛۧ۬ۢ۟ۙۙ۫ۥۘۨ۫ۚۙۘ۬ۢۛۡۥۨۡۘ";
                    break;
                case -1726497970:
                    k2.logToFloatingWindow(l2.decrypt("/3+y0089vpn5cIF6nEpTU2yGdvxkrxDaikbEKokzkpf0T6F+iHM=\n", "HP8imRzbMDw=\n"), l2.decrypt("vnpiyvU=\n", "2h8Av5JfCZw=\n"));
                    str2 = "ۨۦۡۤۢۗۘۤۖۘۛۧۘۦۘ۫ۤ۠ۥۙۦۗۖ۠ۤ۟ۘ۫ۨۘۗۤ۠ۗۦۘ";
                    break;
                case -702311662:
                    Toast(l2.decrypt("OVd1rRy/PolqBU/4Xbtx6WhUIdUH\n", "3ODHSLgy2wE=\n"));
                    str2 = "ۖۧۤۙۗۘۘۖۖۧۜۤۥۘۡۥ۟ۥۘۥۙ۫۬ۚۚۜۡۚۨ۠ۡۘۥۙۥۘ۠ۧۘۘ";
                    break;
                case -455758380:
                    str2 = "ۦۘ۠ۜۥ۟ۙۤۧۚۨۦۤۘۖۘۦ۟ۙۨ۬ۖ۟۠ۚۦۚۨۖۢۙۢۛۨۘ۠۠ۖ۫ۗۜۛ۫ۢۘ۫ۙۡۙۜۘۜۥ۟ۤۢ";
                    break;
                case 855440429:
                    return;
                case 1551359022:
                    str2 = "ۜ۫ۛۢۤۨۘۚۧۡۡۖۘ۟ۗۤۛۦۘۛۧ۫ۦۘ۠ۘۚۡۘۚۗ۬";
                    break;
            }
        }
    }

    @JavascriptInterface
    public void exitApp() {
        String str = "ۥۢۧ۬ۛۛۖۥۡ۬ۢۨۘ۟ۖۡ۫ۡ۬ۜ۬ۜۘ۫ۜۘۢۖۗۢۚۨ";
        while (true) {
            switch ((((str.hashCode() ^ 595) ^ 993) ^ StatusLine.HTTP_MISDIRECTED_REQUEST) ^ (-1939486387)) {
                case -1152061188:
                    str = "ۙۖۙۦۥۥۘۡ۫ۘۘۖۨۦۚ۬ۡۨۤۚۦۧۧ۠ۥ۟۬ۙۤۡۨۧۘۨۡۢۤۡۘۘۙ۠۬۫۠ۦ۬ۚۦۘ۟ۙۖۢۨۨۚۧۘ";
                    break;
                case -316031092:
                    return;
                case 1547935193:
                    Utils.safeExitApp(this.activity);
                    str = "۠۫ۨۘۖ۟ۚ۬ۧۤۨ۠ۢۥۘۡۘۧۘۖۢۙۗۚۥۤ۟ۤۖۘۥۤۥۘ";
                    break;
            }
        }
    }

    public WebView findCurrentWebView(String str) {
        String str2 = "ۢۢۤۦۥۦ۫ۡۧۘ۟ۚۙ۟۟ۦ۫ۙۘۧ۬ۤۧۧۡۧۘۙۛۛ۟ۡۡۘۘۨۚۦۘ۬ۚۛۛۨۧۡۦۖۜۧۜۘ";
        String str3 = null;
        while (true) {
            switch ((((str2.hashCode() ^ 42) ^ 239) ^ 215) ^ (-414936611)) {
                case -1866630227:
                    return null;
                case -1773564711:
                    str2 = "ۙ۟ۜ۟ۥۦۘۛۦ۟ۛۨۚۧ۟ۗۜۙۦۖ۠ۙ۠۫۬ۙۗۧۨۗۧۤۖۧۘ۬ۡۛۙۛۤ۟ۖۛۖۛۛۛۡۨۛۚۢۧۢۖ";
                    continue;
                case -773331177:
                    String str4 = "ۚۤۙۘ۠ۥۘ۫ۦۜ۟ۥۜۨ۟ۚۖۗ۟ۜ۟ۘۛۨۡۗۡ۫۫ۨ۫";
                    while (true) {
                        switch (str4.hashCode() ^ (-1844492953)) {
                            case -834559455:
                                String str5 = "ۧۙۖۘۖۥۚ۫۟۬ۥۘۜ۟ۗۦۜۖۘ۟ۨۨۘۤۨۘۘۨۛۡۜۙۛۗۤۗۚۡ";
                                while (true) {
                                    switch (str5.hashCode() ^ (-1048521947)) {
                                        case -382844663:
                                            str5 = this.boundWebView != null ? "ۡۗۗ۬ۤۘۘۨۡۚۚۥۖۘۖۦۡۘ۫۬ۙۡۧ۫ۥۛۨۧۚۘۗۜۡۘ۫۬ۜۘۜ۟ۡ" : "۫ۥۡۘۢۗۨۙۛۥۘۖ۬۟ۦۦۖۤۦۘ۠ۖ۬ۡۜۙ۫ۖۘۢ۟ۨۨۤۥۡ۫ۘۥۧ۠ۗۢۡۛۤۖۘۘ۬۫۠ۨۡۘ۠ۦۦ";
                                        case 756114508:
                                            str5 = "۟ۧۤۛۚۡ۠۠ۙۥۧۡۗۚۥۘۗۥۨۘۢۢۜۧۤۥۘۤۖۖ۠ۨۚۥۗۦۢ۫ۦۡۧۘۨۛ۠ۤۖۦۘ۠ۗۜ";
                                        case 1506972486:
                                            str4 = "ۜۛۢۧۢ۟ۦ۬ۚۘ۫ۥۨ۟ۜۘۨۗۡۘ۠ۦۧۘۦ۠ۥۘۧۥۘۘۙۚۖۘۗۥۘۜ۬ۛ۟ۚۗۜ۬ۦ۠ۖۛ";
                                            break;
                                        case 1544909583:
                                            str4 = "ۨۨۜۡۙ۬ۡ۟ۗۥۖۛۡۧ۠ۛۦۥۘ۟ۥۨۡۨۗۢۙۤ۟ۛۜۘ";
                                            break;
                                    }
                                }
                                break;
                            case -222425676:
                                str2 = "ۦۧۖۖۘۘۧۗۚۖۡۧۘ۬ۖۦۘۡۡۡۘۘۘۖۖۜۥ۟ۢ۫ۡۖۢۛۜۥۘۤۘۧۘۘۦۧۘۡۡ۫۫۬ۛۗۦ۟۬ۢۢ۬ۢۤ";
                                break;
                            case 120813290:
                                str4 = "ۦۛۖۘۖۚۙۖۢۜۘۖۨۙۤۙ۠ۥۙۦۧۗۥۘۡ۫۠۟ۡۧۘۧ۬ۖ";
                            case 587407055:
                                break;
                        }
                    }
                    break;
                case -739461913:
                    String str6 = "ۤۨۚ۠ۨۖۨۜۜۗۢ۟ۨۦۡۛۙۗۢۗۗ۠ۚۦۦۡۧۘۡ";
                    while (true) {
                        switch (str6.hashCode() ^ 28119991) {
                            case -1489035438:
                                break;
                            case 77761049:
                                str2 = "ۢۛ۠ۜۡۜۙۡ۠ۗۚۜۙۨۤۛۧ۟ۙ۬ۤۜۜ۟۠۬۠ۦۨۧۘ۬ۚۙ۟۠ۖۦۖۗ۬ۤۛۚۖ۟۠ۛۖۘۥۗۤۥۢۗ";
                                break;
                            case 467507366:
                                String str7 = "ۘۡۨ۟ۖۢ۟ۚۜۧ۟ۘۘۗۢۦۢۛۧ۟ۥۚ۫ۤ۠ۚ۟ۚۡۛۖۙ۫ۧۡۜۡۘ۫ۨۧۛۘۘۖۘۙۘۧۘۘ";
                                while (true) {
                                    switch (str7.hashCode() ^ (-757061200)) {
                                        case -1465964541:
                                            str6 = "ۨۛۛۖۜۧ۟ۢۚۨۡۡۡۤۖۛ۟ۖۘ۟۠ۜۗۗۦۥ۫ۘۘۥ۟ۜ۠ۙ۫ۗۡۧۦۨۙۙۘۘۖۤۡۜۥۦۘۚۡۘۢۥۨۘ";
                                            break;
                                        case 652243095:
                                            str6 = "ۢ۫ۥۙۥۨۘۘ۬۠ۥۜ۠ۘۦۚ۟ۡۘ۟ۚۤۥۥۤۨ۬ۘۘۡۙ۟ۧۦۥۜ۟ۚۜ۟ۛ۠۠ۜۘۤۚۜۘۤۡۨ";
                                            break;
                                        case 864644504:
                                            str7 = !this.disposed ? "ۡۖۖۧۚ۫ۜۗۦۘ۫ۡۢۧۚ۫ۗۨ۠ۖۗۜۘ۟ۘۛۙۡۛۦ۟ۡ" : "ۤ۠۟ۥۦۥۜۜۘ۟ۖۛ۟ۘۘۨۜۗۦۗۗۙ۬ۖۜۜ۠ۖ۫۬ۨۛۤۗۥۘۗۘۦ۫ۨۜۘۘۙۗ۬ۥۢ";
                                        case 1037740523:
                                            str7 = "ۢ۫ۢۡۢۘۧۘۧۢ۟ۖۘۛۨۙۢۨۥۚۡ۬ۖ۟ۗ۫ۗۡۘۗ۠ۦ";
                                    }
                                }
                                break;
                            case 888110952:
                                str6 = "ۗۗ۟ۗۘۗۡۖۘۤۛۘۘۛۡۗۛۘۨۘ۠ۢۨۘۛۙۧ۫ۘۘۘۙۛۤۖ۠ۖۘۤۛۤۙۙۥۧۨۨۘ";
                        }
                    }
                    break;
                case -600168203:
                    return this.boundWebView;
                case -287783368:
                    str2 = "ۡ۠ۡۢۚ۠ۚۤۘۘۙۥۡۘۖۖ۟ۥۗۤۖۗۛۧ۬ۦۘ۠ۡۗۧ۫ۦۢۡۘۡۙۢ";
                    continue;
                case -184361939:
                    String str8 = "ۗۗ۬ۢۚۚۙ۠۬ۢۛۘۘۡۡ۫ۡۡۙ۟ۤۤۤۦۧۙۧۙ۠ۨۤۧۛۛۖۧۘۢۤۚۧۚۙ";
                    while (true) {
                        switch (str8.hashCode() ^ 962243181) {
                            case -2102835402:
                                String str9 = "ۙۨۖۡۨۤۦۨۘۙ۫ۡۛۜۥۜ۬ۛۧۨ۫ۧۜۡۧۜۖۗۖۘۤۗ۫ۛۦۨۧ۟ۖۧۛۨ";
                                while (true) {
                                    switch (str9.hashCode() ^ 2076809088) {
                                        case -1805610166:
                                            str9 = "ۙۧۧۤ۫ۦۘۨۛۢۤ۟ۜ۟۬ۘۜۢ۬ۥ۫ۖۘۜۖۖۥۥۢ۠ۦۖۘۦۘۥۧۜۥ۟ۡۜۡ۠ۚ";
                                        case 886374635:
                                            str9 = str3 != null ? "ۨۜۥۘۡ۟ۦ۟ۙۡۢ۬ۨ۟ۜۡۙ۫۠ۜ۟۫ۗ۟ۚۖۙۧ۬ۢۜۡۘۡۘۛۛۡۦۤۜ۠۬ۖۧۨۡۘۦۘۥ" : "۫۬ۛ۬ۡۙۗۜۙۚۢۖۘۨۜۨۚۧۨۘۤۡۙۦ۟۠۠ۤۖۧۚۡۜ۫۟ۖۥۦۘۚۗۥ۬ۛۦ۠ۢۘۜۥۥۦ۫ۜۘۦۘۚ";
                                        case 1353971525:
                                            str8 = "ۥۙۧۥ۠ۘۘۦۙۥۘۨۨۢ۠ۘۛۛۘۖۘۧۨۚۡۛۙۙۖۨ۟۠۫ۛ۫۬۟ۡۦ";
                                            break;
                                        case 1812916115:
                                            str8 = "ۧۢۧۜۤۚۜۥۦۘۗۢۛ۬ۦۚۨۧۙۖۧۘۢۜۧ۟ۙۛ۟ۢۡۜۚ۫ۥۖۜۘ۠ۙۖۘۙۡۨ";
                                            break;
                                    }
                                }
                                break;
                            case -1568810790:
                                str8 = "ۙۛۨۘۙۚ۠ۤۛۛ۬ۜۥۘۜۘ۟۬ۚ۟۬ۗۧۥۨۡۘۚۘۦۘۡۤۨۘۦۛۥۙ۟ۤ۬ۗۦۥۘ";
                            case -1320779166:
                                str2 = "۟ۦۘۜۚۦۘۥۛۜۘۦۤۖۘۙ۟۟۟ۧۧۤ۫ۡۘۤ۠ۡۘۨ۟۟ۡۨ۬ۜۙۡۨۤۖۜۤۥۘۚۙ";
                                break;
                            case 1000279660:
                                break;
                        }
                    }
                    break;
                case 237928632:
                    String str10 = "ۗۗۛۧۨ۟ۧۛۖۗۦۘۥۛۥ۟ۦۡۘۡۘۥ۠ۦۤۛۤۛۛ۟ۢۥۡۘۗۨۜۘ۟۫ۖۨۚۚۜۥۧۘ۠ۙ۟";
                    while (true) {
                        switch (str10.hashCode() ^ 558497031) {
                            case -633493568:
                                str10 = "ۛۡ۫ۖۖۦۘۙۚۦۘۛۤۥۘۧۖۤۚۖۚ۬ۢۦ۫ۗۧ۬ۥۜۘۥۗ۟۬۟ۡۛ۫ۤۤۦۘۘۢۧۚۥۚۖۜۧۧ";
                            case -483750963:
                                str2 = "ۤۖۧۘۧۢۥۙۛۧۨۜۦ۬ۦۥۘ۬ۖۘ۠۬ۡۦۦۚۦۥۛۘ۟۬ۘ۟۟۠۠۟ۙۡۘۧۘۙ";
                                break;
                            case -335439679:
                                break;
                            case 1180397571:
                                String str11 = "ۚۜۢ۬۟ۢۦ۠ۗۘۧ۠۟ۗ۠ۦۙۚۧۢۦۗ۬ۨۘۛۨۦۘۦۧۨ۟ۢۨۘ۬۠۫۬۫ۨۘ۫ۨۡۖ۫ۥۛ۟ۡۘ";
                                while (true) {
                                    switch (str11.hashCode() ^ 1338131434) {
                                        case -1626235499:
                                            str10 = "ۘۖۦۘۥۦۧۘۤۜۙۙۥۚۨ۟۫ۚۢۦۘۡ۬ۘۘ۟ۡۗۨۙ۬ۦۗۘۧۤۡۘ۬ۥۜ۫ۖ۫ۛۤۚ۬ۨ۬ۦۧۘ۫ۥۥۘۤۦۥ";
                                            break;
                                        case -644714608:
                                            str10 = "۬ۡ۫ۘۨۙۛۦۘۜۥۘۧۢۖۢۛۗ۫ۖۥۥۥۘ۠ۥۛۖۢۦۘۤۤۧۧ۟ۜۥ۠ۜ۫ۙۛ";
                                            break;
                                        case 994459722:
                                            str11 = str3.equals(str) ? "ۖ۠ۘۘۨ۟ۜۖۥۜۘۙ۬ۚ۟ۚۘۡۘۨ۟ۨ۬ۧ۫۟۟ۨۘ۬۠ۖۦۖۜۦۘۚۨۗۛۙۨۘ" : "۫ۙۖۤ۬ۤۥۗۦۢۗۦۢۙ۬ۧۜۨۗ۟ۘۥ۫ۥۘۢۨۡۘ۬۫ۗۤۖۘۘۡۧۗۛ۟ۚۜۧۦ۟ۧۧۢۧ۠";
                                        case 1681000243:
                                            str11 = "ۨۖۦۖۡۜۘۖۘ۟ۥۚۜ۫۟ۘۘۜۡ۠ۧۚۘۘۚ۠ۡۜۨۧۘۨۥۡۘۖۢ۟ۖۤۥۡۘۘۡۡۘۗۢۢ۠ۤۛۜۜۚۢ۠ۜ";
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case 1743619402:
                    str3 = this.boundPopupId;
                    str2 = "ۤۘۡۘ۠ۨۧۘۧ۬ۧ۟ۘۙ۬۟۠۬ۨۜۘۜ۠ۦ۫ۙۙۨۗۥۘۨۙۧ۟ۨۘۢۙ۠";
                    continue;
            }
            str2 = "۠ۦ۬۫ۜ۠ۤۤۜۘ۬ۗۗۡۚۜۡ۟ۜ۠ۙۦۡۘ۠ۨ۬ۗ۟ۧۧۖۖ۫ۘ۫ۦۘۥۨۘۡۤۜۘۖۗۦۘۘ۟ۤۢۘۖۥۥۦ";
        }
    }

    @JavascriptInterface
    public String getAppInfo() throws JSONException, PackageManager.NameNotFoundException {
        k2.logToFloatingWindow(l2.decrypt("93BQeYtaOZDxf2PQWC3QUGCxsEOR0tFaNBZWij4PIt22WyiDW1sjnQ==\n", "FPDAM9i8tzU=\n"), l2.decrypt("KN8bDSQ=\n", "TLp5eEMyQmA=\n"));
        try {
            JSONObject jSONObject = new JSONObject();
            jSONObject.put(l2.decrypt("lS9GPx4=\n", "9F82dnrX0Go=\n"), fcRuQsQrcxOAzxwEalcM.APP_ID);
            jSONObject.put(l2.decrypt("tcp1BXtG\n", "1LoFTh4/jaU=\n"), fcRuQsQrcxOAzxwEalcM.APP_KEY);
            jSONObject.put(l2.decrypt("fc+uwSs2rPE=\n", "GarYqEhT5ZU=\n"), Utils.getUniqueDeviceId(this.activity));
            String packageName = this.activity.getPackageName();
            jSONObject.put(l2.decrypt("aDwRnX7HGg==\n", "GF1y9h+gfzM=\n"), packageName);
            PackageInfo packageInfo = this.activity.getPackageManager().getPackageInfo(packageName, 0);
            jSONObject.put(l2.decrypt("ov8RifpUA1W6+w6f\n", "1Jpj+pM7bQo=\n"), packageInfo.versionName);
            jSONObject.put(l2.decrypt("gcqYd7kFMSKUwI5h\n", "96/qBNBqX30=\n"), String.valueOf(packageInfo.versionCode));
            return jSONObject.toString();
        } catch (Exception e) {
            return l2.decrypt("E8M=\n", "aL729J2Husw=\n");
        }
    }

    @JavascriptInterface
    public int getCurrentPosition() {
        MediaPlayer mediaPlayer = null;
        String str = "ۥۨۧۡۡۨۡۡۦۘۡۧۜ۬۬ۘۘۦۧۘۘۡۦ۬۠ۤۙۨۥۘ۬۟ۦۘ";
        while (true) {
            switch ((((str.hashCode() ^ 582) ^ 238) ^ 30) ^ (-1202394408)) {
                case -2069180861:
                    return 0;
                case -2061394593:
                    String str2 = "ۘ۫ۦۥۤ۫ۡ۬ۥۛۗۤۧۖۥۘۡۨۡۨ۟۬ۜ۠۟۫ۡۚۚۥ۟ۧ۟ۤۖۛۖۘ";
                    while (true) {
                        switch (str2.hashCode() ^ 560635427) {
                            case -1738707916:
                                str2 = "ۢۘۙۙۥۧۚۖۚۢۦ۟ۜۧۛۢۧۜۘ۟ۘۚۡۤۖۘۨۚۛ۟ۛۨۘۙۤۢ۟ۘۘۘۢۖۡۘۦۘۘۚۘۨۜ۬ۥ۬۫ۨۙۙۖۘ";
                            case -1413097283:
                                break;
                            case 1060516015:
                                String str3 = "ۗۗۧۢۘۗۚۘۘۘۢۜۜ۠ۦۙۘۘ۫۠۫ۖۦۢ۠ۛ۠ۖۢۚۙ۫۠۬ۜ۬ۖۘۖ۫ۤۗۦۢۚ۬ۜۜۧ۟ۥ۬ۖ۬";
                                while (true) {
                                    switch (str3.hashCode() ^ (-1327330997)) {
                                        case -1972553822:
                                            str3 = mediaPlayer != null ? "ۛۚۢ۟ۤ۟۬۬ۛ۟ۥۖۘ۫ۡ۟ۛ۬ۧ۬۬۠ۦۜۧۘۚ۟ۢۘۧۛۖۥۨۜۙ۬ۦ۫ۘۘ۫۬ۥۘۙۘۦۛۖۘۘ" : "ۤۚۖۨۤ۫ۡۦۘۗ۠ۖۘۗ۠۬ۨۖۘۧ۠ۦۗۡۨۤۥۚۡ۠ۗ";
                                        case -1332834179:
                                            str3 = "ۦ۟ۜۘۡۡۖۘ۫ۨ۟۠ۨۜۘۖۡۖۘۜۡۘۗۙ۟ۘۗ۫۫ۖۘۘ۟ۢۦۘۜۚۘۘۗ۫ۘۘ۠ۚۥۚۜۨۘ";
                                        case 364926573:
                                            str2 = "ۥ۫ۧۘۨ۬ۦۗۨۢ۬ۥۥۨۖۘۨ۬ۨۘۤۤۖۦۘۥۘۚۢ۫۬ۥۗ";
                                            break;
                                        case 1492515566:
                                            str2 = "ۧ۟ۦۛ۬ۘۙ۠۠ۤۨۗۙ۟ۥ۫ۥۘۨۦۘ۟ۛۦۘۤ۫ۛۡ۫ۖۘۢۙۘۘۘۨۜ۫۟ۦۘۛ۟ۢۗۤۜۘۗۗ۠۬ۖۘ۠ۧۢ";
                                            break;
                                    }
                                }
                                break;
                            case 1279050089:
                                str = "ۤۚۧ۠ۜۦۘۡۥۚۨۨۖۘۛۚۦۘۛۢۢۙۥۗۘۡۘۘۛۥۗۙۛۦۘۚۘ۠ۤۦ۟ۡۛۖ۠ۧۨۘ";
                                break;
                        }
                    }
                    break;
                case -731984200:
                    return mediaPlayer.getCurrentPosition();
                case 200203948:
                    mediaPlayer = this.mediaPlayer;
                    str = "ۖۢۜۘۙۘۗۛۨۧۖۨۘۤۧۖۘۗۘۙ۠ۚۨۧۚۖۘۗۙۜۚۤۛۜۧۘۦۡۖ";
                    continue;
                case 1906436692:
                    String str4 = "ۡۖۥۘۚۙۖۘۨۤۜۘۛۙۡۘۘۦ۟ۡۖ۟ۖۦۚۘۙۥۘۦۡۥۘۙۜۡۘۥۨۧۗۥۥۛۙۦۢ۟ۥۘۤۛۧۥۢۚۧ۠۫ۗ۟ۨ";
                    while (true) {
                        switch (str4.hashCode() ^ (-1867771371)) {
                            case -1784206791:
                                break;
                            case -364552708:
                                String str5 = "ۚۥۤۤۚۦۦۛۖۗۤۘۘۤۚۢۦۢۙ۟ۖۨۖۨۤ۬ۢۧ۫۟ۢۨۘۧۦۢ";
                                while (true) {
                                    switch (str5.hashCode() ^ (-384180808)) {
                                        case -1539539230:
                                            str4 = "۫ۤۦۘۥۙ۟ۘۧۜۨۘۥۢۡۙۛ۫ۙۢۨۘۗ۠ۥۛۧۡۜۧۚۘۥۧۡۜۥۘۖۤۚ۟۬ۖ";
                                            break;
                                        case -741570315:
                                            str5 = this.isPrepared ? "ۗۥۙۨۚۗۤۧ۬ۨۥۚۤۤۢۨۛۧۗۢۜۧۧۥۘۦۖۘۘۘۡۨۦ۟ۖۛۖ۫۫ۥۡۖۘۦۥ۫ۜۘۜۘ۠" : "ۛۖۚۗ۬ۘۘۡۢۤۘۤ۠ۤۦۦۘۗ۫ۨۘۨۡۧۖۤۥۦۘۡۗۘ۬ۜۢۥۘۡۨۗ۟ۦۨۘ۟ۥ۠ۛ۫ۛۤ۬ۙ";
                                        case -598490932:
                                            str4 = "ۥۛۧۦۙۥۗۡۡۘۥ۬ۦۙۢۦۙۛۥ۫ۦۧۚۙۨۘۦۜ۠ۛۜ۟";
                                            break;
                                        case 786618143:
                                            str5 = "ۘۛۘۦۚۦۘۨ۠ۧۚۦۦۘ۟ۢۘۘۙ۟ۙۧ۬ۨۘۙۜ۠ۧۦۖۘۢۧۘۗۜ۬۬۫ۜۘ";
                                    }
                                }
                                break;
                            case 85325199:
                                str = "ۥۧۖۘۙۡۖۘۚ۟۠ۘۖۧۘۧۨۚۨۗ۠ۙۡ۟ۡۤۧۘۨۛ۫ۢۦۗۤۥۘ۠ۡ۫ۛۛۥۘۙۥۦۘ";
                                break;
                            case 789946495:
                                str4 = "ۧۧ۫ۦۧ۟ۖۚۖۘۡۨ۬ۤ۬۟۫ۨۘ۬۫ۦۘۡۛۢ۟ۥ۟ۥۜۨۘۗۢۦۘۨۤۚ۬۫ۡۡۢۚ۫۠ۙ۠ۥۨۘ";
                        }
                    }
                    break;
                case 2106990879:
                    str = "ۛۤۖۥۛۛ۟ۖۧۘ۫۬ۥۡۘ۠ۚۡۜۙۥۧۜۘۘۖۜۦ۫ۚۛۥ۬ۖۘۤ۠ۘۘ";
                    continue;
            }
            str = "ۗۦۙۙۚۨۘۜۚۢۛۥۘۘۖ۠ۢۙ۫ۖۡۢ۠ۜ۠۠ۘۜۘۜۤۧۨۚۥۘۗۢۦۘۤۘۘۥۗۘۘ";
        }
    }

    @JavascriptInterface
    public int getDuration() {
        MediaPlayer mediaPlayer = null;
        String str = "۬ۢۥۘ۬۫ۡ۟ۛۥۚۖۚۛۛۡۗۤۖۘ۟ۢۜۘۙۘۛۚ۠۟۠ۗۖ";
        while (true) {
            switch ((((str.hashCode() ^ 451) ^ 828) ^ 218) ^ (-318886341)) {
                case -1598171950:
                    return mediaPlayer.getDuration();
                case -505712481:
                    str = "ۢۚۦۚۤ۫۠ۜۨۧۜۘۗ۫ۙۢ۬۫۬ۤۖۘۨۖۦۘۢۚۖۦۘۨۘ۫ۢۡۘۘۘ۬";
                    continue;
                case -364003392:
                    mediaPlayer = this.mediaPlayer;
                    str = "ۨۨۘۘۤۡۧۧۛۦۚۡۧ۟ۖ۠ۥ۫۟ۡۖۘۖۗۜۥۨۘۘۦۖۦۛ۟ۖ۠ۧۖ۟ۛ۬ۤۨۦۘ";
                    continue;
                case 352680551:
                    String str2 = "۬ۥۦۘۛۚ۫ۧ۫ۨۖۢ۬ۗۨۛۥۘۛۖ۠۫۬ۗۜ۬ۙۙ۟ۦۢۡۗۨ۟ۜ";
                    while (true) {
                        switch (str2.hashCode() ^ (-1851928610)) {
                            case -1812219143:
                                str2 = "ۚ۟ۘۙۖۘۛۦۗۘۡۨۥۜ۫ۡۤۘۨۥۙ۬ۨۘۚۙۡۖ۠ۨۘ";
                            case -149844322:
                                str = "ۛۘۗۤۤۘۚۤ۟۠ۗۨۗ۠ۡۘۦ۠ۚ۬ۗۤۥ۫ۘۘ۠ۢۙ۬ۛ۟ۜۨ۠ۡۤۧۡۢۨۘۦۢۛ";
                                break;
                            case 1556206354:
                                break;
                            case 1905434027:
                                String str3 = "ۢۧۨۘۙ۟ۘۘۜۘۖ۠ۜ۬۫۟ۛۧ۠ۘۘ۬ۛۙۛۨۨۨۤ۟۠ۦۧۛ۠ۦۖ۬ۡۛۢۘۦۜۨۘۥۘۜۘ۟ۨۜ";
                                while (true) {
                                    switch (str3.hashCode() ^ (-1551369053)) {
                                        case -1176326988:
                                            str2 = "ۛۤۜۘۗ۬ۚۗۙۡۘۛۙۛۧۥۦۜۨۨۘۚۛۙۜۙۥۢۤۦۘ۟ۚۜۘۧۥۙۖۧۡۘۨۢۗ۟ۙۛ";
                                            break;
                                        case -948963274:
                                            str3 = this.isPrepared ? "ۚۥۖۚۛۘۗ۠ۘۚۚ۫ۡۚۛۦۦ۬ۢۘۘۛ۫ۖۘۧۘۥۧۥۗ۟ۢ۟۫ۘ۠ۤۢۢۡۜۗ" : "ۡۧ۫۫ۧۧۡۥۗۙ۠ۨۥۛۥۧ۟ۤ۬۫ۡۘۛۨۢۙۚ۫ۙ۫۟ۨۘۘ۬ۢۖۘۚۨۦۘۡۖۛ";
                                        case -623427727:
                                            str2 = "ۥۨۢۧ۠ۚۦۚۦۥۜۜۘ۟ۘۥۘۧ۫ۦۘ۠ۦۖۘۢ۫ۖۘۡۦۦۘۧ۟";
                                            break;
                                        case 2037767189:
                                            str3 = "ۘ۫ۢۧ۫ۢۘۖ۬ۚۦ۬ۡۡۙۥ۟ۡۘۢۜۡ۟ۦۗۗۦۡۘۚ۟۫۬۟ۢۤۘۦۢۡ۫ۙۢۙۚ۠ۨۚۚ۟";
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case 1078508403:
                    return 0;
                case 1080358824:
                    String str4 = "ۗ۟ۜۘۥ۬ۖۜۜۨۚ۠۟ۢۖۡۡۛۧۛۨۙۜۤۥۜۨۜۦۨۧۜۥۜۢۥۦۘ";
                    while (true) {
                        switch (str4.hashCode() ^ 1458103578) {
                            case -587332582:
                                break;
                            case 708987129:
                                str = "ۘۦۜۘۜ۫ۖۘۚۥۗۡۢۚۗۤۦۘۧۨۚۜ۫۟ۚ۫ۧۡۡۜۥۜۙۤۖۘۖۛۘۙۙۖۘۙۙۧۧۡۘۡۘۥۚۙۧۗ";
                                break;
                            case 1144657962:
                                str4 = "ۦۚ۠ۤۡۘۘۙۜۡ۟ۘۡۘۘ۟ۧۤۖ۠ۜۦۘۘۤۤۙۖۙۤۗۖۘ۠ۡۨ۠ۡ۠۟ۖۘ۬ۧۢ۠ۛۦۡ۫ۘۘ";
                            case 2061542076:
                                String str5 = "۬۠ۡۘۙ۟ۡۘۡۨۖۘۧ۟ۢۛۨۘۤۜۦۙۛۙۖ۠ۨۚۦۗۧۙۦۧ۬ۖۘۢۤۤۛۥۢۢ۟ۡۘ۫ۡۦۦ۬۠";
                                while (true) {
                                    switch (str5.hashCode() ^ 1341246781) {
                                        case -1541171066:
                                            str4 = "۠ۛۤۛۦۜۘۘۦۧۘۥ۬ۖۙ۬۠ۧۘۡۨۛۨۘۥۧ۫ۜۖۢ۫ۘۦۡۡۙۖۗۦ";
                                            break;
                                        case -910634858:
                                            str5 = "۫ۖۜ۬۫ۜۖ۬ۙۚۘۦۚۥۚۦۘۦ۠ۤ۠۟ۦۥۖۢۢ۬ۛ۬ۖۧۘۚۦۖۜۚۥۢۖۡ";
                                        case -861011782:
                                            str5 = mediaPlayer != null ? "۠۠ۢ۫ۧۦۘۨۤۢۜۤۖۘۚۥۖۘ۠ۧۘۘۜۦۨۘ۟ۨۘۦۢ۫ۥ۫۟" : "ۚ۟ۙۡ۠ۥۦۨۖۘۨۥۘۗۢۧۡۚۜۘۖۥۜۘ۟ۧۛۨۢ۠ۜ۟ۛۜ۠۠ۚۜۦۧۗ۟ۙۜۗ";
                                        case -339413771:
                                            str4 = "ۖۢ۬ۛۚۘۘۚۧۖ۫ۨ۫۫ۜۧۙۜۜۘۡۖۦ۫ۛۦۘۥۡۦۜۦۙۨۡۥ۬ۙۜۡۙۜۛۦۤۚ۠ۜۘۦۧۤۘۗۧۖۦۦ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
            }
            str = "ۚۡ۬۫۫۫ۘۢۜۤۗۚۤۚۚ۠۫ۦۘۡۙۡۘۜۙۦۘ۬ۗۛۨۧۘ";
        }
    }

    @JavascriptInterface
    public String getShellVersion() {
        String str = "ۛ۫ۚۡۗۗۤۖۤۖۜۤ۟ۙۧ۬۬ۙۗۧ۠۟ۢۙۢۙۗۚ۠ۜۘ";
        while (true) {
            switch ((((str.hashCode() ^ 798) ^ 614) ^ 761) ^ 685712899) {
                case -1264148692:
                    return l2.decrypt("R4Zr\n", "drBa7ThgJDM=\n");
                case -480347057:
                    str = "۫۫۬ۜۦۘۘۖۜۖۜۧ۫ۗۗۨۘ۟ۚۜ۫ۢۧ۫ۖۙۗ۠ۥۘ۬ۨۡۧۧۥۘ۠ۙۚ";
                    break;
            }
        }
    }

    @JavascriptInterface
    public boolean hasPermission(String str) {
        boolean z;
        k2.logToFloatingWindow(h.e("Iolc5Rdia3ckhm9MxBWNs7JZqd0p7ZahqGaij6ISXDRynCQN72xVUSadZI+iJ2U0XqwqMsdtfEIu\ntVY=\n", "wQnMr0SE5dI=\n", new StringBuilder(), str), l2.decrypt("/nN3mNk=\n", "mhYV7b5oK5Q=\n"));
        String str2 = "۬۟ۦۘۘۡۖۧ۟ۚ۟۟ۘۘۗ۬ۨۦۤۘۘۚ۬ۡۡۘ۟ۘ۠ۥۜۤ";
        while (true) {
            switch (str2.hashCode() ^ 1931987078) {
                case -1497600900:
                    break;
                case 614516933:
                    str2 = "ۘۙۢۨۥۢۥ۠ۛۡۢۗۥ۬ۖۛۤۘۘ۠ۚ۫ۛۡۚۚۛۥۡۖۘ۫ۢۤۧۢۡ";
                    break;
                case 761416959:
                    String str3 = "ۢ۬ۥۘۢ۫ۥ۫۟ۢ۬ۙۥۥۛۗ۬ۛۨۘۥ۬ۛۘۥۛۜۙۜۘ۠ۚۖۥۜۧ۠۟ۢۥۚۥۧۜۧ۬ۛۛ۬ۚۢۜۚۗۧۢۨۘ";
                    while (true) {
                        switch (str3.hashCode() ^ (-586182852)) {
                            case -797182602:
                                str3 = "ۖۗۘۘۖۦۘۨۙۦۘۜۢۥۛ۬۬ۥۧۥۘ۫ۨ۬۫ۜۦۛۢۧ۠۠ۥۘۡۡۜۘۨۢۗۡۗۨۘۚۗ۟ۘۡۢۤۗ";
                                break;
                            case 695649791:
                                String str4 = "ۥۚۥۛۜۘ۫ۥ۬ۦۜۖۡۤ۫ۡ۫ۜۘ۬ۙۡۖ۬ۦۦ۬ۜۨۜۜۘۖۙۡۦۦۘۛ۠ۦۚۤۡۘۜۛۢۧۖ۫";
                                while (true) {
                                    try {
                                        switch (str4.hashCode() ^ 794413759) {
                                            case -1444324267:
                                                z = true;
                                                break;
                                            case -905175119:
                                                str4 = "ۨۜۡۘۡۧۘۡۛۤۜۚۙ۬ۙۛ۟ۥۤ۬ۦۘۦۥۜۡ۟ۘۘۘۤۡۦۙۖۘ۟ۙۘۜۤۨۘۥ۟ۘۘۖۖۗ۟ۨ۟";
                                                continue;
                                            case -486216282:
                                                z = false;
                                                break;
                                            case 1786261327:
                                                String str5 = "۫ۗۨۨۥۡۖۧۡۥ۬ۚ۬۠ۙۖۖۘۚ۬ۖۘ۬ۜۧۖۤۜۥۗۡۥۧۛۘۜۡۥۜۨۡۨۘ";
                                                while (true) {
                                                    switch (str5.hashCode() ^ 528878193) {
                                                        case -1227515041:
                                                            str4 = "ۡۘۚۥۤۜۧۛۡۘۗ۟ۙ۠۟ۜۘ۬ۜ۫ۨۦ۟ۙۡۧ۫ۡۨۘۗ۫ۚ";
                                                            continue;
                                                            continue;
                                                        case -1205927365:
                                                            if (this.activity.checkSelfPermission(str) != 0) {
                                                                str5 = "ۘۦۛۥۖۖۘۥۦۨۘۚ۬ۜ۬ۛۛۨۗۙۗۚۧۦۙۚۜۡۧۘۤۧۘۥۦ۟۬ۗۤۗۛۥۘۦۚۘۤ۠ۙۗۧۙ";
                                                                break;
                                                            } else {
                                                                str5 = "ۧۦۡۘۘۢۙۘۖۘ۫ۤۤ۬۫ۡۘۘ۟ۗ۠ۜۖۖۙۦۨۨۘۨ۫۫ۖۨۗ۠ۛۧ۬۬ۥۘۡۙۥۘۚۚۛۡ۟۠ۤۢۢۘۤ";
                                                                break;
                                                            }
                                                        case 209239146:
                                                            str5 = "ۧۛۡۦۖۦۘۖۘۗۤۥۘۜۥۥۘ۫ۢۦ۟ۚۚۖ۬ۤ۬ۥۥۜۘۘ۟ۡۙۦۦۤ";
                                                            break;
                                                        case 1404650184:
                                                            str4 = "۬ۦۧۖۜۨۢۨۘۚ۟ۘۘ۠۠ۘۘۛۥۘۚۧۗۗۦۦۘ۬ۗۦۢ۬ۥۘ";
                                                            continue;
                                                    }
                                                }
                                                break;
                                            default:
                                                continue;
                                        }
                                    } catch (Exception e) {
                                        k2.logToFloatingWindow(h.d("ZW1748uR1QBjYkhKGOa9BgYLdAx+6thMH30ODSmf7wBpUXE=\n", "hu3rqZh3W6U=\n", new StringBuilder(), e), l2.decrypt("x38vRLY=\n", "oxpNMdHJj78=\n"));
                                        return false;
                                    }
                                }
                                k2.logToFloatingWindow(l2.decrypt("2txvMxY2XLLc01yaxUE0irq1Zull\n", "OVz/eUXQ0hc=\n") + str + l2.decrypt("8RgmjdG1L+NfdlqYvMo1nw==\n", "0f6+IjQliQU=\n") + z, l2.decrypt("V5yIT28=\n", "M/nqOggZzV0=\n"));
                                return z;
                            case 1213020169:
                                String str6 = "ۤۨۜۘۖۤ۫ۤۧۤ۬ۤۘۘۧۖ۟ۢۖۗۗۢۘ۠۫ۜۘۥۘۧۛۚ۟۫ۨۘ۟ۥۦۘۙۧۘۘۤۡۨۘۥۤۗۖۘۡۘۙۥۘ۬ۜۜۘ";
                                while (true) {
                                    switch (str6.hashCode() ^ 1621310226) {
                                        case -2066706282:
                                            if (!str.isEmpty()) {
                                                str6 = "۫ۥ۟ۨۗۖ۠ۡۥ۠ۜۜۥۚۗۦۡۘۘۦۥۚ۠ۡۘ۫ۚۚۡ۠ۨۘۛۚۘۘۜۧۙ";
                                                break;
                                            } else {
                                                str6 = "ۥ۠۟۟۠ۥۘۛ۟ۥۢۙ۟۟ۗۘۘۧۤۘۢۦۚۖۛۨۢۜ۬ۛ۠ۜۤۘ۠۟ۘۗۤۜۗۛۢۥۦۖۡۙۚ";
                                                break;
                                            }
                                        case 233967225:
                                            str6 = "۫ۛۢ۫ۨۥۘۡ۬ۦۘۜۛۖۘۦۧۦۢۛۡۘ۫ۗ۠ۜ۬۠۬ۙۗۛۤۜۘۦۧۜۘۡ۟ۥ۬۠۠ۧۙ۟";
                                            break;
                                        case 1637173119:
                                            str3 = "ۦۦۨ۟۫ۤۗۧۗۙ۟ۦۘ۠۠ۤۧۡ۟۠ۛ۫ۜۙ۠۬ۚۛۘۖ۬ۦۚ۠ۛ۫ۛۨۨۖۘۦۛۤ";
                                            continue;
                                        case 1708382114:
                                            str3 = "ۢ۠ۧۗۙۚۡۛۘۘۛ۬ۢۥۦۧۘۥۨۧۘ۫ۨۘۘ۠ۧۦۖۧ۫۠ۨۡۖۨۤۥۡۡۘ۫ۦۛۧۖۦۤۤۘ۬ۧۢۛۢۧ۠ۗۢ";
                                            continue;
                                    }
                                }
                                break;
                            case 1588870947:
                                break;
                        }
                    }
                    break;
                case 989544403:
                    String str7 = "ۜۚۧۘۘۤۥۖ۠ۢۙۙ۫ۗۡۚۛۢۧۖۦ۟ۚ۟ۤۧ۟ۡۘ";
                    while (true) {
                        switch (str7.hashCode() ^ (-657480731)) {
                            case -1978212401:
                                str2 = "۟ۤۚۤۙۦۘۖۧۢ۠ۗۢۦۙۖ۫ۥۗۢۜ۫ۜۛۥۘ۬ۨۡۘ۟ۖۚۗۨۗۥۤۤۚۨ۠ۜ۬ۡۘۧۖۖۘۦۥۜ۫۫ۘۘۗۚ۬";
                                continue;
                            case -217959947:
                                str2 = "ۦۡ۫ۦۨۨۘۨۛۛۜۦۘۚ۟ۧۚۖۥۘۡ۠ۚۡۡۨۖۖۘۚۚۧۖۤۡۘۡۤۙۜۨۥۨۖۖۧۘۘۥ۬ۖ";
                                continue;
                            case 1347478505:
                                if (str == null) {
                                    str7 = "ۛۘ۫ۛۧۨۘۦۡۘۢۥۡۘۚۤ۟ۚۡ۠ۘ۬ۤۛ۠ۢ۠ۜۘ۫ۨۥۘۜۜۙۦۢۢۜ۬ۦۖۥۘۘ";
                                    break;
                                } else {
                                    str7 = "ۖۘۧۘۡۘۧ۫۫ۨۘۡۢ۬ۤۡۢۡۨۜۙۛۖۗۙۨۘ۬۬ۘ۫۬۟ۙۛۥۖ۫ۙۥ۟ۡۘۚۨۥۜۢۘۘ۟ۜۗۦ۬ۨۨ۟ۤ";
                                    break;
                                }
                            case 1978604285:
                                str7 = "ۨۗۨۘۤۗۚۜۥ۟ۗۛۥۨۘۘۖۙۙۙۧۘۘۙۘۚ۬ۤۥۦ۠۫";
                                break;
                        }
                    }
                    break;
            }
        }
        k2.logToFloatingWindow(l2.decrypt("Va4Ka2Q9kMRToTnCt0r4/DXHA7HSS5OFDpR9iI0=\n", "ti6aITfbHmE=\n"), l2.decrypt("t4NCUBI=\n", "0+YgJXWsyts=\n"));
        return false;
    }

    @JavascriptInterface
    public void http(String str, String str2, String str3, String str4, String str5, String str6) {
        String str7 = "ۧۖۦۧ۟ۦۘۙۢۜۤۧۜۘۧۗۧۘۖۦۘۢۨۧۗۧۛۨ۟ۢۨۘۦۘۘۛۜۧ۟ۖۘۚ۠۟ۙۥۥۘۡۖۡۗۧۙۥۨۨۘ۠ۥ۫";
        while (true) {
            switch ((((str7.hashCode() ^ 733) ^ 181) ^ 967) ^ (-516581967)) {
                case -2044470364:
                    str7 = "ۥۦۖۥۨۧۘۢ۬ۛ۫ۛۨۜ۬ۦۢۘۜۢۥۧۘۥۖۡۘۙۦۜۘۗۤۛۛۢۨۘۧۙ۬";
                    break;
                case -1712012118:
                    new Thread(new y(this, str4, str3, str2, str5, str, str6)).start();
                    str7 = "ۖ۟ۢۜۛۡۥ۠ۡۘۜۡۦ۫ۥۘۘۢ۫ۛۚۖۤۖۥۘ۟۟ۤ۬ۦۘ۬۟ۨۘۛ۫ۘۜۘۘۜ۠ۡۙۧۚ۫ۡۜۖۛۨۡۙۦۘ";
                    break;
                case -495262492:
                    return;
                case -456267783:
                    str7 = "۟۫ۙ۟ۗۙ۬ۧ۠ۜۥۘۦ۠ۡ۟ۥۨ۫ۦ۫ۚ۟ۜۘۛۗ۬ۨۦ۫۠۟۬ۙۧۢۨۧۢۢ۠ۙ";
                    break;
                case -325963597:
                    str7 = "ۧۘۙ۠ۙۨۛۘۦۘ۬ۜۨۘۧۢ۠۠ۤۨۘۛۡۙۥۗۨۜۡۨۘۡ۫ۚ۠ۢۜ۫ۢۢ";
                    break;
                case 51315576:
                    str7 = "ۨۧۙۚۡۙ۫ۦۙ۬ۢۛۨۢۖۗۦۛۜۘۦۖۧۘ۫ۘۜۢۨ۠ۥۗۖ۬ۢۖۘۦۦ۬۬ۨۦۘ۫ۤۖۛۡ";
                    break;
                case 224050980:
                    str7 = "ۤ۠ۙۤ۫ۜۘۙۛۦۦۨ۟ۧۡۘ۠ۤۡۗۖۘۘۜۘ۠ۧۜۚۙۨۚۥۖۡۚۜۖۜۦۘۛ۫ۨۙۘ۟ۜۡۧۘ۟ۛۙۤۡ۬";
                    break;
                case 462484458:
                    str7 = "ۛۖۥۘۙۗۗ۫ۢۘۦۖۘۢۛۜۘۤۨۢ۫۟ۡۖ۟۠ۛ۬ۤۦۙۥۘۤۖۚ۬ۤۢۙ۠ۡۥۜۘۨۦۧۚ۟";
                    break;
                case 1050612762:
                    str7 = "ۥۘۨۦۤۦۘۛۘۚۘۘۛۡۦۙ۠ۖ۬۟ۦۘۦ۫ۢ۠ۨۖۘۢۦۙ";
                    break;
                case 1649747472:
                    k2.logToFloatingWindow(h.e("/EUoCZsHJ6j6ShugSHDBeWu1mKVeWE++ii0a6CBRKuqLbZgzp5HcfVahV/9S\n", "H8W4Q8jhqQ0=\n", new StringBuilder(), str), l2.decrypt("SrOAHb0=\n", "LtbiaNrVsp4=\n"));
                    str7 = "ۤۤۗۘ۫ۦۘۤ۫ۨۢۙۜۘ۠ۤۜۘۗ۟۬ۨ۠ۖۥۛ۠ۛۦۡۘ۬۫۫۟۟ۗۗۦۧۘۧ۠ۨۛۛۖۥۡۡۘۢ۠ۡۘ";
                    break;
            }
        }
    }

    @JavascriptInterface
    public boolean isAppInstalled(String str) throws PackageManager.NameNotFoundException {
        k2.logToFloatingWindow(h.e("i4uiFwSNj4GNhJG+1/poVyl7QhQ5GHVFBGdXOXeNl52OuKe19cDplOvspvV3jaKkjr65uNvu5LTl\n5I7H\n", "aAsyXVdrASQ=\n", new StringBuilder(), str), l2.decrypt("hAQ4FB8=\n", "4GFaYXgLnmY=\n"));
        String str2 = "ۦ۟ۨ۟۟۟ۜۤۜۚۖۡۢۜۘ۠۟۫۟ۛۥۖۗۛۨ۠ۨۘۗۜۥۘ";
        while (true) {
            switch (str2.hashCode() ^ 286759099) {
                case -943242821:
                    String str3 = "ۜ۟۠ۘۥۛۘ۟ۘ۫ۖۘ۟ۙ۫۠ۚۥۡ۟ۨۧ۫ۢۤ۠ۜۘ۠ۙۖ۟ۘۢ۫ۥۜۥۜۙۥۥۙۙۚۛ۬ۦۖۘ";
                    while (true) {
                        switch (str3.hashCode() ^ (-1683423236)) {
                            case -1098160206:
                                str3 = "ۤ۟۠۫ۢۜۘۤۛ۫ۡۧۚ۫ۧۢۤۤ۫ۢۖۤۛۨۤۨۘۘ۟ۢ۠ۖۚ۬ۛۦۦۛۚۜۘ۫ۢۦۛۜۘۚۘۗ";
                                break;
                            case -1015926954:
                                str2 = "ۜ۬ۧۢۖۘۡۛۨۢ۬ۥۜۦ۫ۛ۬ۦ۟ۥۧۘۤ۬ۦۢۦۖۘۡ۠ۨۘ۬ۖۡۘۚ۫ۖ";
                                continue;
                                continue;
                            case -110784315:
                                str2 = "ۦۗۙ۫ۡۗ۬ۗۨ۫۟ۧۚۦۛۚۖۚۧۘۘۧ۟ۤۧۧۛۜۤۡۘۥ۟ۘۢۧۚۨۜۘۖۥۦۘۘۚ۫ۗۗۦۘۖۘ۟ۦۖ۫";
                                continue;
                            case -30941952:
                                if (str == null) {
                                    str3 = "ۖۡۨۘۢۖۖۜۦ۬۫ۤۜۡۖۨۘۥۦۦۘۖۥۢۛۦۚۧۜۡۘۤۨۜۘۜۥۗۨۨۥۘ";
                                    break;
                                } else {
                                    str3 = "ۨۖۖۘۦ۠ۥۥۙۨۘ۫ۨۨۘۤۨۢۚ۟ۢ۫۠ۥۘۛۛۛۚ۠ۘۘۘۨۢ۟ۨۘۘۥۦ";
                                    break;
                                }
                        }
                    }
                    break;
                case -350264296:
                    String str4 = "۫ۨۛۨۚۘۘۡۛۧۛ۬ۧۥۛۥۘۘۙۘۢۜۥ۫۬ۡۘۦ۟ۜۚۘۚۦۜۖۥۜۙۙۚۥۘ۫ۖۡۤۡۤ۠ۗۖۘ";
                    while (true) {
                        switch (str4.hashCode() ^ (-5243554)) {
                            case -1807839933:
                                try {
                                    this.activity.getPackageManager().getPackageInfo(str, 1);
                                    k2.logToFloatingWindow(l2.decrypt("FDPgSreotAASPNPjZN/fKXJW3ZgB0pJKSyk=\n", "97NwAOROOqU=\n") + str, l2.decrypt("/EayOy4=\n", "mCPQTkmaSdM=\n"));
                                    return true;
                                } catch (PackageManager.NameNotFoundException e) {
                                    k2.logToFloatingWindow(h.e("N8l4gr+UyRExxksrbOOiOFGtUEUJ399RSOEHdHY=\n", "1EnoyOxyR7Q=\n", new StringBuilder(), str), l2.decrypt("8wiudcQ=\n", "l23MAKOyzyY=\n"));
                                    break;
                                }
                            case -864155790:
                                break;
                            case 1115825055:
                                str4 = "ۡ۠ۖۘۖۦ۬ۡ۟ۢۨۚۛ۟ۤۡۢ۟۫ۚ۫ۘۢ۬ۜۜۨۧۚ۬ۘ۠ۢۡۘۚۜۥۘۨۚۦۦۜ۠";
                                continue;
                            case 1816020985:
                                String str5 = "۬ۦۤۛۨۗ۟ۜۙۘۘۗۨۤۘۘۨۦ۫۬ۥۡۚۛۙۧۧۡۘۢ۫ۙۧۚۡۦۘۨۘ";
                                while (true) {
                                    switch (str5.hashCode() ^ 468958341) {
                                        case -1234636174:
                                            str4 = "ۡۦۗۖ۫ۗ۠۟ۖۘۖۖۛۡ۬ۡۘ۠ۦۦۥۖۥۜۘۘۧۢۢۢۜۗۥۛۛۡۗۦ";
                                            continue;
                                        case 29227960:
                                            if (!str.isEmpty()) {
                                                str5 = "ۛۚۡۘ۠ۧۡۛ۟ۦۧۤۛ۫ۖۜۘ۫۬ۢۚۡۙۡۖ۫ۤۨۧۘۙۛۗ۬ۘۘۘۘ۬ۦۘ۠ۤۥۘۡۧۨ";
                                                break;
                                            } else {
                                                str5 = "ۜۧۡۢ۟۬ۘۥۘۥۘۡۦ۠ۖۗۛۚۢۘۘ۠ۗۡۚ۫ۧۙۙ۠ۢۘۚ۫ۢ۠۟ۨۧۡ۠ۛۡ۠ۖۘۖ۫ۖۘ";
                                                break;
                                            }
                                        case 1793951993:
                                            str4 = "ۙۙۗۡۦۡۘۛۜۘۘۢۙۨۗۦۘۢۥۦۦۚۜۘ۟ۖۘۥۨۘۛۡۢ";
                                            continue;
                                            continue;
                                        case 1920491142:
                                            str5 = "ۨۘۡۨۡۦۤۛۥۘۖۢۥۘ۬ۗۙ۫۠ۨ۫۟ۖ۫ۗۜۜۢۦۚ۬ۨۘۨۨۡۜ۬ۥۘۖۛۘۘۗۜۛۚ۠ۢۗۛۙۦ۟ۦۖۢۥ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case -21119283:
                    str2 = "۟ۡ۬ۧۛۢۤۦۦۘۙ۠ۤۗۧ۠ۨۦۨ۫ۛۦۥۤۥ۠ۦۖۘۛۡۙۗۜۜ۫ۗۦۘ۫ۙۡۘۨۤۛ۫ۛۛۙۛۢۗۨۘۦۘ";
                    continue;
                case 1822267353:
                    break;
            }
        }
        return false;
    }

    @JavascriptInterface
    public boolean isPlaying() {
        MediaPlayer mediaPlayer = null;
        String str = "ۨ۬ۥۗۘۘۖۖۥۜۧۛۚۚۦۘۦۥۡۧۛۥۘ۬ۢ۟ۛۖۙۗۛۙۜۚۖۘۢۗۦۘۗۖۜ۬ۧۜۘۦۗ۟۫۟ۙۗۡۘۦۨ۟";
        boolean z = false;
        boolean z2 = false;
        while (true) {
            switch ((((str.hashCode() ^ 824) ^ 429) ^ 91) ^ 638332199) {
                case -2058133924:
                    return z;
                case -1887675039:
                    mediaPlayer = this.mediaPlayer;
                    str = "ۙۗۙۖ۟۟ۜ۟ۘۤ۟۠۟ۥۘ۠ۚۡ۟۠ۜۗۧ۟۠ۖ۫ۙ۬ۤۦۜۜ۬۫ۡۘۗۛۧۤۨۖۘۦۚۡۘۜ۟ۦ";
                    continue;
                case -1432270510:
                    String str2 = "ۧۚۜ۠ۙۡۘۤۦۙۨۖۚۗ۫ۜۚۘۘۗۥۢۧ۫۠ۗۧ۬ۧۜۧ";
                    while (true) {
                        switch (str2.hashCode() ^ (-2051949864)) {
                            case -1938654336:
                                break;
                            case -360634931:
                                str2 = "ۜۥۨۘ۠ۚ۫۫ۖۖۘۛۜۢۢۧۙ۬ۚۙۘۨ۟ۥۦۨۨ۟ۤ۟ۨ۬ۢۖۚۤۨۛۙۢ۟ۥ";
                            case 837438278:
                                String str3 = "ۜۨۥۘۜۡۛۗ۬ۡۢۧۦۘۧۚۧ۫ۙۘۥۡ۫ۦۛۜۛۖۖۘۢۨۦۘۧۤۚۖ۠ۨۘۚ۟۟ۖ۟ۡۘ";
                                while (true) {
                                    switch (str3.hashCode() ^ (-1500057076)) {
                                        case 802421470:
                                            str2 = "۟ۜۖۘۧۦ۠ۤۤ۫۫ۚۗ۬ۚۥۙۖ۬ۙۨۗۚ۬ۚۨۤۨۘۨۗۦۘ۬۫ۡۙۛۢۥ۟ۧۛۥۘۘ";
                                            break;
                                        case 1021933598:
                                            str3 = mediaPlayer != null ? "۠۫ۚۖۗۛۗۜۢۥۖۨ۟ۤ۠ۗۛ۟ۥ۬ۡۨۚ۫ۡۛۦۨۖۗۖۚۤۚۛۤ" : "ۘۘۜۚۖۨۢۥ۫ۛۥۧۜۙۥۘ۫ۖۙۖ۠ۜۘ۬ۤۙۛۤ۫ۖ۫";
                                        case 1214913032:
                                            str3 = "ۦۘۘۗۧۢۖۙۘ۬ۤۗۡۦۨۛۘۘۘۚۧۙۡ۠ۘ۬۫ۨۧۡۘ۠ۡۙۨۢۨۨۥۘۘ۬ۚۨۘ۬ۖۛۗۨ۬۠ۙۖۢۚ۠";
                                        case 2124654457:
                                            str2 = "۬ۘۦۡ۬ۥۘۖۨۢۧۖۘ۫ۘۦۥ۠۠ۛۚۜۘۧۨۥ۟ۚۚۘۜۘۨۨۥۗۢۦۘۢۗۥۛۨۖ۫ۧۖۘۗۡۧۖۨۘۡ۠ۘ";
                                            break;
                                    }
                                }
                                break;
                            case 953501337:
                                str = "ۘۦۧۙۘۜۢۛۘۘۖۖۜۤۚ۬ۧۙۡۘۘۖۦ۬ۦۘۘۥۥۙ۫۟ۙ۟ۧۢ۬ۨۘ";
                                break;
                        }
                    }
                    break;
                case -1000864659:
                    z2 = true;
                    str = "۬۟ۨۘۗۦۦ۠۫۟ۦۚ۫ۗۨۛۥۘۘۛۙ۬۬ۨۤۜۧۦۗۤۚ";
                    continue;
                case -919993184:
                    str = "۫ۚۨۘۤۜۚۗ۠ۢۗۚۢۤ۟ۥۘۦۗۗۢۥۢۜۧۖۘۙۢۦۘۗ۬ۡۦ۠ۤۥۖ۟ۖۢ۠ۢۨۗۤۡۘۛۥۘ";
                    continue;
                case -563855943:
                    str = "ۘۙۜۢ۫ۚۢۥۦۘ۬۫ۢۖۡ۠۠ۨۦۤۖۘۚ۬ۖۘ۟ۘۦۘۨ۫۬۫ۥۦ۫ۡۖۘ۟۬ۦۡ۟ۥۘۤۦۦۡ۫ۘ";
                    z = false;
                    continue;
                case 227432715:
                    str = "ۤ۟۠ۗ۟ۘۘۛۥۥۘۖۚۡ۠۫ۡۘ۟ۛۖۘۛۖۜۙۜۤ۫ۡۡۜۜۖۘۘۥۜۘۚ۟ۚۢۨۡۗۗۛۥۚۚۤۢ۫ۛۤ۫ۢۤۚ";
                    continue;
                case 721144269:
                    str = "ۘۙۜۢ۫ۚۢۥۦۘ۬۫ۢۖۡ۠۠ۨۦۤۖۘۚ۬ۖۘ۟ۘۦۘۨ۫۬۫ۥۦ۫ۡۖۘ۟۬ۦۡ۟ۥۘۤۦۦۡ۫ۘ";
                    continue;
                case 1377316183:
                    str = "۬ۜ۬ۧۧ۬ۧ۫ۜۘۡۢۧ۬ۚۦۛۡۜۘۛۚۦۘۖۛ۫ۖۘۘۗۙۨۘ۫ۗۖۨ۬ۤۤ۟ۡۘۜۘ۠۠ۢ۫ۥۗۘۙۗۗ۬ۙ";
                    z = z2;
                    continue;
                case 1801781689:
                    String str4 = "ۙۡ۬ۦ۬ۧ۠ۘۦۘۤۡۖۘۙۖۖۘ۟ۗۥ۬ۜ۫ۙۚ۫ۜۨۘ۟ۥ۟ۤۘۢۙۘۜۘ۟ۗۖۘۦۘ";
                    while (true) {
                        switch (str4.hashCode() ^ (-228538484)) {
                            case -1904346753:
                                str = "ۜۜۤۤۦۧۘۡ۫ۖۤۖۚۥۥۤ۟ۚ۠ۧ۬۬ۖۢۨۡ۠ۨ۫ۧۛۗۦۜۦۜ";
                                break;
                            case -1367907424:
                                str4 = "ۧۢۜۤۤۘ۟۠ۘۚۡۚۙۨۡۧۛۚۢۤ۬۠ۨ۬ۡ۬ۙ۬ۚۜ۫ۖۙۤ۫ۦۘۚۨ۫ۦۛۡۖۙ۫";
                            case -963911907:
                                String str5 = "ۖ۬۫ۜۦۥۥۖۥۘۦۘۛۚۨۚۢ۟ۤ۫ۦۙ۟ۘۥۘۙۨۨۘۗ۫۟ۖ۠ۡۘۘۦۖ۟ۗ۟ۚ";
                                while (true) {
                                    switch (str5.hashCode() ^ (-722259255)) {
                                        case -2068832797:
                                            str4 = "۬ۥۖۘۗۢ۠ۡۘۦۘ۟۫ۘۘ۟ۡۖۡۦۥۘۧۡۜۘۚ۠۫ۙۨۖ۠ۜۤۡۗۖۦۢۥۘۘۗۜۘۗ۫ۘۖۥۧۚۨ۟";
                                            break;
                                        case -2057449909:
                                            str4 = "ۧ۟ۥۘۢ۫ۜۘۢۤۥۙ۠ۤ۬ۘۖۘۤۧۡ۫ۛۦۧۚۧۡۥۥۘ۫۠ۜۨۥۡ۫ۤ۫۫ۤۨۗۚۤ";
                                            break;
                                        case -395060041:
                                            str5 = mediaPlayer.isPlaying() ? "ۙۘۙۗۨۧۘۚۗۖۦۛ۟ۜۦۚ۠ۗۖۙۦۡۘ۟ۜۥۙۢۙۛۖۗ۬۬ۤ۫ۨۖ۫ۡ۬ۖۨۥۘۜۧۘۘۗ۫۫ۘۡۖۘۤ۬" : "ۤۡۥ۟ۘۨۘۖۢۚۥۡۖۘ۫ۘ۫ۨ۫۟ۖۦۙ۟ۙۤۤۨۢۨ۟۠ۨۢۖۘۗۤۜۘ";
                                        case 466400612:
                                            str5 = "ۖۦۦۘۛۗۡۘ۫ۗۦۘ۫۟ۦۘۤ۬ۙۖۛۡۘۡۡۘۘ۫ۢۙۥۦۥۘۚۦۜ۟۠ۡۘ۠ۤ۬";
                                    }
                                }
                                break;
                            case 1598172490:
                                break;
                        }
                    }
                    break;
            }
            str = "ۖۡۘۘۧۜۘۘۢۗۜۧۦۢۢۗۥۘ۬ۛۦۘ۠۬ۜۖۘ۠۫۫۬۬ۘۘۖۤۗۦۤۨۚ۠ۥۘۨۤۘۘ۟ۧۙۧۛۢۛۥۤۖ۬۬";
        }
    }

    @JavascriptInterface
    public boolean isPrepared() {
        String str = "ۤۨۛۧۘۛ۬ۖۧۗ۫ۖۙۛ۬ۚۡۦۦۨۡۡۡۖۘۧ۫ۦۚۤ۠";
        while (true) {
            switch ((((str.hashCode() ^ 151) ^ 386) ^ 488) ^ 970549567) {
                case -879246070:
                    str = "ۧۤ۫ۚ۬۫۫۠ۜۘۘ۫ۧ۫ۗۡۨ۠۟ۛۘۤۨۗۡۦۨۤۘۛۚ";
                    break;
                case 462607944:
                    return this.isPrepared;
            }
        }
    }

    @JavascriptInterface
    public void log(String str) {
        String str2 = "ۢۤۘۖۗۙۚۗۤۢۤۥۤۖۖۘۤۖۥۘۨۘۦۘۢۜ۠ۘۘۖ۟۫ۛۥۧۙۘۡ۠۬ۨۨۘۜ۟ۡۘۖ۬۟۬ۦ";
        while (true) {
            switch ((((str2.hashCode() ^ 664) ^ 970) ^ 976) ^ (-1575900358)) {
                case -1306283922:
                    k2.logToFloatingWindow(l2.decrypt("hCAgYCYvZlKCLxPJ9ViEmACAVrzML1tijwIbwsVKD2PP\n", "Z6CwKnXJ6Pc=\n"), l2.decrypt("IMchApE=\n", "RKJDd/YjSyY=\n"));
                    str2 = "۬ۗۥۘۛۗۜۢۛۙۜ۟ۨۘۢۙ۠۬ۙۥۗۤۘۘۢ۟ۤۨۧۘ۫ۖۜۥۙۜۖۨۛۥۘۘۖۨ۬۬ۙۤۙ۫";
                    break;
                case -581161158:
                    str2 = "۬ۖۨۢ۠ۘۡ۟ۧۡۘۦۡ۬ۘۘۘۥ۟ۤ۟ۥ۬ۚۜۛۗۥۜۥۗ۟ۤۗ۬۬ۥۧۧۦۖۦۘ۬ۗۤۚۘۦۘۦ۟۫۫ۢۥۘ";
                    break;
                case -504005703:
                    k2.logToFloatingWindow(str, l2.decrypt("E2vZ3g==\n", "egW/sXq1KwE=\n"));
                    str2 = "ۥ۫ۗۚ۫ۜۦ۠ۗۜۚۨ۫ۧ۬ۦۜۛ۫ۢۡۦۨۡۖ۫ۚۛۗۜۛۗۢۙ۫۫ۥ۟ۘۨ۫";
                    break;
                case -238898097:
                    str2 = "ۧۙۤۢۤۡۘۘ۫۟ۘۢ۠ۦ۫ۚۜ۫ۜۥۛۙۢۡۘۖۚۖۘ۫۟ۨۘۥ۟ۡۘ۠ۘۗۛۤ۫ۨۘۦۢۡۙۗ۫ۚۚ۫ۖۦ";
                    break;
                case 1035147418:
                    return;
            }
        }
    }

    public void onDialogDismissed() throws IllegalStateException {
        String str = "ۜۖ۟ۛۨۤۧۛۦۘۙۧ۟ۦۜۘۧ۫۟۠ۖۛۛ۬ۘۛۢۜۥۚۜ";
        while (true) {
            switch ((((str.hashCode() ^ 730) ^ 624) ^ 497) ^ (-641978715)) {
                case -2065512386:
                    this.jsOnBuffering = null;
                    str = "ۚۤۡۦۗ۫۫ۡۜۡ۫۠ۛ۫ۚۜۚۦۤۢۦۘۘ۫ۦ۠ۙۦۙۛۚۦ۟ۗۜۘۤ۠۟۬ۡۦۨۘ";
                    break;
                case -1929811636:
                    this.boundDialog = null;
                    str = "۟ۛۦۘۦۜۘ۠ۛ۬۫ۦۥۘۡۚۧۙۙ۠ۤۨۙ۫۬۫ۘۗۡۤۡ۬ۜۦۘۢۥۗ";
                    break;
                case -1804855163:
                    this.isPrepared = false;
                    str = "ۖۛ۬ۥۥۜۘ۬ۘۨۘۛ۬ۚۢ۟ۧۨۧ۫ۥ۠ۥ۫ۛۙۛۨۗۗۧۡ";
                    break;
                case -702452502:
                    this.disposed = true;
                    str = "۟۫ۖۘ۠ۡۥ۠ۜۨۚۛۘۢ۠۟ۗۗۖۙۛۤ۠ۘۨۘۜۤۚۜۘۘ۬ۚۦۘۢۖۘۘۧۚۖۨۥ۠";
                    break;
                case -690254836:
                    this.mainHandler.removeCallbacksAndMessages(null);
                    str = "ۤۦۨۘۜ۫ۛۡۙۨۘ۠ۡۤۨۙۡ۟۠ۧۡ۬ۧۢۖۛۚۡۦۙۤۚۤۦۤۘۧۨۘ";
                    break;
                case -587796423:
                    this.jsOnError = null;
                    str = "ۧ۬۫ۗۚۦۘ۟ۨۜۘۙ۠ۙ۬ۛۡۨ۬ۖۗۤۨۡۡۦۢۦۖ۫ۧۗۘۜۜ۟ۛۦۘ";
                    break;
                case -486185893:
                    this.boundWebView = null;
                    str = "۫ۥۘۘۥۚۖۦۦۦۘۘۡ۬ۡۢۗۚۗۦۘ۠۬ۤۜۗۘۘۖۗۧۤۤۢۨۡۘ۠ۜۜ";
                    break;
                case 35153952:
                    this.jsOnEnd = null;
                    str = "ۘۙۤۡۨ۠ۜۚۘۦۡ۫ۥ۫ۖۘۨۡۘۘ۠ۥۖۘۚۜۥۖۜۘۚۥۢۨۧۧۘۚۥۘۡ۬ۦۘۜۥۤ۬ۢۨۖۨۖ";
                    break;
                case 272554008:
                    str = "ۡ۟ۧۤۦۦۘ۫ۤ۠ۥۚۙۧۡ۠ۦۘۨۘ۬ۚ۠۫ۡ۟ۦۚۤۘۧۡۢۤ۠ۙۛۙۧۨۡۘۛۤۥۜۚۢۤۡۡۘ";
                    break;
                case 688427633:
                    stopAndReleaseMusic();
                    str = "ۦ۬ۦۘۙۦۖ۫ۧۘۙۛۗۖۦۗۢ۬ۡۘۥۜۙۥ۠ۘ۫۫ۨۘۜۘۢ۟۠۫ۨۗۖۘ";
                    break;
                case 1311798667:
                    return;
            }
        }
    }

    @JavascriptInterface
    public void openActivity(String str) {
        k2.logToFloatingWindow(l2.decrypt("76/S1Iw1f8bpoOF9X0KeE2lBA/2ruocKeFZieElqF9CZx+A1N2NyhJiH\n", "DC9Cnt/T8WM=\n"), l2.decrypt("Xctv+eI=\n", "Oa4NjIXD6Yc=\n"));
        try {
            this.activity.startActivity(new Intent(this.activity, Class.forName(str)));
        } catch (ClassNotFoundException e) {
            Toast(l2.decrypt("PdBufDlX45xrvmEjbmac\n", "21nQmIHaBhQ=\n") + str);
        }
    }

    @JavascriptInterface
    public void openUrl(String str) {
        k2.logToFloatingWindow(l2.decrypt("U3BiAPzlCEVVf1GpL5LpkNWepzjDI2B2CRZB30ehLQgAcxXeBw==\n", "sPDySq8DhuA=\n"), l2.decrypt("ajT5hZI=\n", "DlGb8PVOG9w=\n"));
        try {
            Intent intent = new Intent(l2.decrypt("EkZvk5+4ex4aRn+EnqUxURBcYo6e/0l5Nn8=\n", "cygL4fDRHzA=\n"), Uri.parse(str));
            intent.addFlags(268435456);
            intent.putExtra(l2.decrypt("QPpGbVxx7gRc5kZiREv6\n", "M5IjATAunnY=\n"), true);
            this.activity.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(this.activity, l2.decrypt("1bpWCamJOCugyEpv849gRL2I\n", "My327xoc3qI=\n"), 0).show();
        }
    }

    @JavascriptInterface
    public void pauseMusic() {
        String str = "ۘۢۙۨۥ۫۬ۙۨۘۡۛۖۘۖۗۙۘ۬۫ۡۘ۟ۚۚۢۜۡۘ۟ۙۘۘ۫۠ۥۡۜۘۤۚۗۘۧۜۘ";
        while (true) {
            switch ((((str.hashCode() ^ 500) ^ 348) ^ 526) ^ (-542470471)) {
                case -1170847063:
                    this.mainHandler.post(new w(this, 1));
                    str = "ۘۡۖۘۗۦۚ۫ۧۢۘۛۨۘۥۧۥۘ۟ۖ۟ۖۛۧۨۧۡۜۜۡۘۛۤۨۘۤۚۘۘۡۧۗۧۨۧۦۗ";
                    break;
                case 1953596348:
                    str = "۬۠ۚۨۜۨۘۖۖۡۘۜۙۥۦۗ۠۬ۤۦۗ۟ۘۘۧۡۢۥ۟ۘ۟ۗۥۜۤۜۘۖ۟ۧۧۖۦۡۡۖۘۜ۟ۘ۬ۤۡۘۤۦ۬ۢۖۘ";
                    break;
                case 2102833177:
                    return;
            }
        }
    }

    @JavascriptInterface
    public void playMusic(String str) {
        String str2 = "ۦۨ۫ۙۨ۫۫ۜ۠ۙۦ۫۟۬ۗۙۘ۬ۙۛۜۘۢۥۦۨ۠۠۫ۜۢۖۤۨ۠ۚۘۛۦۘۥۜۨ";
        while (true) {
            switch ((((str2.hashCode() ^ 784) ^ 93) ^ 101) ^ (-605860355)) {
                case 29765316:
                    str2 = "ۥۨۦۘۥۚۡۘۥ۫ۦۧۚ۟ۛ۠ۤۙۦۤ۟۟ۡۘۧۘ۬ۜ۟ۨۧۤۨۧۜۘ۫۟ۚۧ۠۬۟ۧۚ";
                    break;
                case 1734348655:
                    this.mainHandler.post(new z(this, str, 2));
                    str2 = "ۤۘۥۛ۠ۘۘ۫ۢ۠ۦۗۡ۟۫ۚۨۥۨۥۜۘۜۨۘۛ۠ۘۘۨۘ";
                    break;
                case 1909207026:
                    str2 = "ۜۛۘۜۛ۟ۡۡۜۚۖۘۛۖ۬ۚ۠ۡۘۖۖۘۘ۠ۜ۬ۜۙ۠ۙۚۚۨ۟۬ۗۛۥۘۨۥۜۘۘۗۖۘۨ۟ۥ۠ۥۦ";
                    break;
                case 2131361871:
                    return;
            }
        }
    }

    @JavascriptInterface
    public String readSP(String str) {
        String str2 = "ۨ۟ۙ۟ۥۜۘۚۤۜۘ۬۟ۥۘ۫۟۟۟۟ۜ۠۟ۧۦۡۘۢۙ۠ۡۖۗ";
        StringBuilder sb = null;
        String string = null;
        while (true) {
            switch ((((str2.hashCode() ^ 225) ^ 603) ^ 314) ^ (-1054363928)) {
                case -1514359840:
                    str2 = "۫ۥۧۦۢ۠ۛ۫ۜ۫۫۬ۙۙۧۤ۫۠ۘ۫ۜۘۘ۟ۥۘ۠ۥۨۘۖۗۨۗۗۤۥۜۜۜۥۦۜۧ";
                    break;
                case -1044182715:
                    k2.logToFloatingWindow(h.e("iA==\n", "ssPTtVxdW5A=\n", sb, string), l2.decrypt("xlos2dQ=\n", "oj9OrLPhHPo=\n"));
                    str2 = "۬۠۫ۘۧۤ۠۫ۡۘۧ۠ۘۙ۬ۨۘۘۘۡۘۤ۟ۘ۬ۗۛ۬ۧۢۤۤۢۛۥۜۤۢۥ";
                    break;
                case 13389167:
                    h.f("QBe4XXbsevRGGIv0pZuGNMLze0cF7GLoRSS9/4ehHOEgcLy/BeJb6kYYvv6ghxPsDXiUjQ==\n", "o5coFyUK9FE=\n", sb, str);
                    str2 = "ۛۧ۬ۖۙۨۘۦۖۜۥۤۢ۫۟ۙۜۥۥۗ۟ۙۚۚۖۜۘۘ۫ۥۧۘۚۧ۠ۡۗ۟ۙۖۤۨۡۖ";
                    break;
                case 199277549:
                    sb = new StringBuilder();
                    str2 = "۟ۧۢ۬ۦۛۛۚۚۚۜ۠۫۠ۧۦۨۥۘ۠ۦۡۘۜۦ۬ۡ۠ۜۘۧۨۗ۟ۜۘۙۨۨۘ";
                    break;
                case 346384263:
                    str2 = "ۧۡۛۜۘۨۘۖۗۦۙۘۢۙۡۦۥۜ۫ۛ۟۟ۘۨۖۜ۫ۨۗۦۘۘۧۘۗۨۥۚۘۨ۬ۜۛ";
                    break;
                case 432099540:
                    return string;
                case 981619407:
                    str2 = "ۧۛۡۙۨ۫ۢۨۘ۟ۖۘۚۧۧۖۢ۫ۜۢۜ۬ۘ۟ۤۤۘ۟ۢۦۘۜۨۢ۫ۘۛ";
                    string = this.activity.getSharedPreferences(l2.decrypt("aCx/cYO1w2Q=\n", "EVkRC+vAsRE=\n"), 0).getString(str, "");
                    break;
            }
        }
    }

    @JavascriptInterface
    public void resumeMusic() {
        String str = "ۦ۫ۙۨۘۙۙۛۛۥ۬۟۟ۢۚۜۙۗۚۦۧ۟ۧۢۢ۠ۘۘۖۧۜ۫ۘۢ۫ۨۛۡۜۘۙۢۗۤۧ۟ۦۚۘۘ";
        while (true) {
            switch ((((str.hashCode() ^ 310) ^ 698) ^ 451) ^ (-1195542017)) {
                case -2053779834:
                    this.mainHandler.post(new w(this, 3));
                    str = "۬ۦۧ۬ۤۙۤۦۥۘ۠ۖۤۗۘۜۛۛۡۘۙۨۨۜۨۚ۟ۦۤۖۜۦۘۛۗۢۛۗۛۤ۠ۜۥۥۧۘ";
                    break;
                case 237607439:
                    str = "ۧۦۧۘۛۗ۟ۢۛۚۨ۬ۢ۫ۥۘۧۗ۫ۜۦۢ۫ۨۘۡۤۖ۠ۚۤۨۚۖۘۧۦۜۘ";
                    break;
                case 1132173205:
                    return;
            }
        }
    }

    @JavascriptInterface
    public void seekTo(int i) throws IllegalStateException {
        MediaPlayer mediaPlayer = null;
        String str = "ۖۧۜۜ۠۫ۖۤ۠ۦۗۘۘۢ۠ۦۙۤ۠ۧۛ۫ۨ۟ۗۜۙۘۘۚۤۤ";
        while (true) {
            switch ((((str.hashCode() ^ 433) ^ 542) ^ 676) ^ 1956381628) {
                case -1322938383:
                    return;
                case -1279699780:
                    String str2 = "ۖۡۡۘۛۧ۫ۙۢۚ۫ۦۖ۫ۨۗۥۙ۫۟۬ۜۧۢۘۛ۟۠ۘۦ۫۟ۨۤۧۜۤۜۗۦۘۥۢۨ۠ۛ۟ۜۤۘۘۗۖۨۛۖۗ";
                    while (true) {
                        switch (str2.hashCode() ^ 749297705) {
                            case -1235781981:
                                break;
                            case 206865697:
                                String str3 = "۠ۛۙۛۡۙۧۚۘۘۦۛۨۘۙ۟ۜۜۘۘ۫۟ۘۘۖۛ۠ۢۢۤۤۘۚ۟ۢ۟ۤۛۙۧۛۛۨ۬۠";
                                while (true) {
                                    switch (str3.hashCode() ^ 1855334187) {
                                        case 1131425283:
                                            str2 = "ۨ۬ۡۘۖۚۨۛ۬ۚ۠ۖۛۗۙ۠ۚۥۘۙۗۜۘۘۚۡۗ۟ۘ۫۫ۥۛ۬ۛۧ۫ۘۘۢۨۗۘ۠ۘۚۥۚۜۡۘۥۨۧۨۙۘۘ";
                                            break;
                                        case 1139660502:
                                            str3 = "ۤۖۤۘۡۥۘ۟۠ۗۛۚۥۘۤۢۘۘۧ۫ۚۛ۫ۚۛۦۘۘۡ۠۫ۘۜۘۙۘۨۛۢۙۢۤۖۘ۫ۧۙۧۢۡۡۥۦ";
                                        case 1218011578:
                                            str3 = mediaPlayer != null ? "ۙ۠ۦۢۙۥۘۙۜۙۤۧۤۙۧۡۘۖ۟ۖۗۚ۬ۖ۫ۧۧۥۗۛ۠ۛۢۡۘۙۖۚۦۢۤ۫۫ۥۘ" : "ۘ۬ۡۚۦۨۘۚۨۦۘۜ۟ۢۤۛۥۢۖ۬ۙۚ۠ۢۡۤۧۜۡۘۗۘۛ";
                                        case 1323479142:
                                            str2 = "ۤۜۡۘۛۨۧۛۘۚۥۦ۟ۢۥۜۖۛ۟ۘ۫ۛۜۥۙۘۥۖ۟ۢۛ۠ۘ۬ۗ۟ۗۨۙ۫ۢۦۡۦۧ۫ۤ۬ۨۘ";
                                            break;
                                    }
                                }
                                break;
                            case 930383977:
                                str2 = "ۨۢۜۘ۟ۧ۫۠ۡۘ۠ۘۤ۟۠ۦۘۗۖ۠ۖۜۘۨۦۛۧ۠ۦۘۧۖۘۛ۟ۛۨ۟ۨ۠ۘ۠۫۟۟";
                            case 949335693:
                                str = "۫ۥۘۘۚۜۨۙۤۡ۠ۖۡۚۧۨۘ۬ۨ۟۫ۘۚۢ۬ۢۛ۟۠۬ۤ۟";
                                break;
                        }
                    }
                    break;
                case -1212628416:
                    mediaPlayer.seekTo(i);
                    str = "ۥۙۨۘۦ۬ۘۘۡۛۨ۟ۙۥۘۖۖۤۙۖۦۘ۟ۘ۫۟ۗۛۧۙۗ۠ۖۜۦۡۨۛۦۖۘ۬ۧۘۢۛۜۦۖۡۜۘۖۙۜۨۘ۠ۨۖ";
                    continue;
                case 954219127:
                    str = "ۧ۠ۖۘۙۢ۠ۨۤۡۧۖۧۘۢۧ۠ۧۤۙۙ۟ۙ۠ۡۤۨۜۛ۬ۛۘۗ۫ۜ۫ۖۘۤۘۢۢۦۡۡۧۗۜۙۨۤۗ۠۟ۥ";
                    continue;
                case 1483376734:
                    str = "ۦۢۨۘۚۛۥۖۜۘۘۢۨۘۙ۟ۜۚۨۘۘۢۧۤۦ۠ۜۡۙۗۦۘ۠ۛ۟ۘۢ۬ۜۤۡۗۧۨۘ";
                    continue;
                case 1601241130:
                    mediaPlayer = this.mediaPlayer;
                    str = "ۖۜ۫ۙۦۨ۟ۢ۟۫ۚۦ۟ۤۥۚ۬ۘۙۦۘۖۦ۬ۡۡۡۦۜۚ";
                    continue;
                case 2058246668:
                    String str4 = "ۤۧ۟ۢۛۜۚۥۘۢۜۙ۟ۚۦۙۜۙۖ۠ۨۗ۬ۘۧۜۘۘۦ۫ۗۡۡ۫ۧۜۘۢۤۙۤۢۜۡۖۗۤۦۗۖۡۡ۠ۨۘ";
                    while (true) {
                        switch (str4.hashCode() ^ (-519971351)) {
                            case -1910021062:
                                break;
                            case 1845809238:
                                str4 = "۟ۚ۟ۗۜۥۧۘ۟۫ۜۛ۠ۧۦۘۖ۠۠۟ۙۚۧۥۡۚۡۘۨ۫۟";
                            case 1901784297:
                                String str5 = "ۗ۟ۚۤۥۧۘۗۢۥۘۗۤۖۛۘۗۗۥ۫۟ۢۥۘۚۘۧ۠ۙۨۗۦۜۨۧۘۦۢ۬";
                                while (true) {
                                    switch (str5.hashCode() ^ 956405405) {
                                        case -1501555391:
                                            str5 = "ۢۢۜۘۚۤۨۘ۫ۜ۟ۚ۟ۘۘ۬ۙ۫۠ۘۥۡ۬ۤۚۨۘۨۧۙۦۤۦۘۥۥۜ۟ۘ۠ۜ۠ۚ۬ۦۥۦۖۘۜ۟ۜۘ";
                                        case -6765789:
                                            str5 = this.isPrepared ? "ۦۥ۟ۗۢۘۧۡۥ۟ۙۨۥۛۤ۬ۤۨۘۜۖۘۛۚۡۜۥۨ۬ۦ۫ۡۖۙۤۜ۬۠ۖۥۘۖۡ۠۫ۥۜۘۛ۠ۗ" : "ۙۛۖۙۥۖۨۙۙۢۘۦۘۘۛۘ۬ۢۨۧۨۧۗۦۨۤۤۨۜۡۘۙۥۛ۫ۖۨۙ۬ۘۤ۫ۛۧۗۛۡۢۘۘ";
                                        case 1890216903:
                                            str4 = "ۧۖۗۤۢۗۜۦۥۘ۠ۜ۬ۜۛۡۨۦ۬ۘۡ۫ۗۡ۫ۙۧۥۘۘ۫ۘۘۦۙۤۖ۫ۖۖۗۡۘۤۛۚ۟ۨۥۡۧۨۚۦۥۘۜۖۘ";
                                            break;
                                        case 1927662087:
                                            str4 = "ۛ۫ۡۙۢ۬ۚ۟ۨۨۘۥۘۢ۠ۨۦۖۦۘۗ۫ۥۛۖۨۤۗۚۦۥۥۤۖ۠ۖۖۥۘۛۛۙۛ۬ۘۘ۫ۙۖۜۦۙۖۡۨۢ۬ۗ";
                                            break;
                                    }
                                }
                                break;
                            case 2043939546:
                                str = "ۚۤۜۘ۫ۥۘ۬ۖۨۛۜۚۨ۠ۚۛ۬ۡۘ۠ۚۨۘۗۘۚ۟۠ۤۖۦۖۦۡۧۖۤۡۘ۠ۜۢۧۜۦۘ";
                                break;
                        }
                    }
                    break;
            }
            str = "ۥۙۨۘۦ۬ۘۘۡۛۨ۟ۙۥۘۖۖۤۙۖۦۘ۟ۘ۫۟ۗۛۧۙۗ۠ۖۜۦۡۨۛۦۖۘ۬ۧۘۢۛۜۦۖۡۜۘۖۙۜۨۘ۠ۨۖ";
        }
    }

    @JavascriptInterface
    public void setLoop(boolean z) {
        MediaPlayer mediaPlayer = null;
        String str = "۬ۚۦۘۙ۠ۢۥ۫ۥۖۨۧۥۨۜ۟ۨۥۘۜۛۥۘۜۛۙ۠ۖۙۛۚۡۚۘۢ۠۬ۚ";
        while (true) {
            switch ((((str.hashCode() ^ 592) ^ 135) ^ 222) ^ (-890199175)) {
                case -1200292899:
                    str = "ۢۨ۟ۤۡۥۘۗۘۦ۬۫ۖۘۥۦۨۘۚۗۨۗ۟ۨۧۧۚ۫۟ۚۖۘۛۥۜ۬ۙ";
                    break;
                case -1084869282:
                    str = "۫ۡۥۘۜۥ۫ۧۖۧۘۡ۟ۨۘ۟ۖۗۚۥۡۘ۟ۦۚۗۖۖۡۖ۫ۦ۟۠ۗۢۦۧۘۡۦۘۥۨۜۘ۬ۖۖ۬۫۠ۡۜۧۘۛ۠ۧ";
                    break;
                case -509761660:
                    return;
                case 1014803844:
                    String str2 = "ۘۘۨۤۤۘۘۨ۫ۡۘ۫ۡۗ۠ۨۡۘۧۧ۬ۥۨۨۘۘۡۖۢۤۦۘۜۙۡۢۦۦۘۤۜۡۘ۫ۘۥۨۧۘۨۖ۫ۛۘۨ";
                    while (true) {
                        switch (str2.hashCode() ^ (-117030597)) {
                            case -1687696672:
                                str = "۟ۥۖۢۘۨۘۜۚۘۡۘۙۚۜۨۘۘۖۘۤ۟ۗۜۙۖۘۚۘۙ۫ۨۡۘۗۤۖۨۨۘۙۧۦۘۖۜۜۘ۬ۨ۟ۥۖ۟ۗۢۤۥۢ";
                                continue;
                            case -846526301:
                                String str3 = "ۘۙۙ۠ۢۖۙ۬ۦ۠ۗ۬ۢۜۥ۫ۚۖ۫۬ۜۘۜ۫ۢۛۜۙۢۖۗۗ۬ۢۖ۟۬۫ۢ۟ۤۡۘ";
                                while (true) {
                                    switch (str3.hashCode() ^ (-659111424)) {
                                        case -1925793060:
                                            str2 = "ۨۡۥۨۙۦۧۗۚۘۗۙۤۙ۬ۘ۬ۤۧ۬۠ۙ۬ۖۨۨ۟ۜۜ۟ۜۨۡۘۘۤ۬ۚۙۢۘۤ۟۫۟ۨۥۦۨ";
                                            break;
                                        case 450344400:
                                            str3 = mediaPlayer != null ? "ۦۤ۠۫ۘۥۘۜۗۘۘۢۘۘ۠ۛۚۖۧۧۤۘۗۜۙۖۖۚ۫ۗۚ۟" : "ۧ۟ۘۖۧۛۦۧۘۙۚۨۘۖۨۜۙۘۥۖۗۥۢ۠ۡۘ۠۠ۥۜۡۧ";
                                        case 1241145063:
                                            str3 = "ۤۙۜۘۛۙۘۘۖۢۨۘۜۖۦۘۜ۟ۘۘ۬ۘۘ۠ۙۖۧۢۥۘۚۥۥۢۘۨۘۜۘۨۧ۬۬۬ۛ۠ۖۡۚۛۤۘۚۖۚ";
                                        case 1641059247:
                                            str2 = "ۦۜۡۙۚۙۦ۫ۨۨۛ۫ۗۛۦۘۨۨۖۨ۟ۘۚۧۚۡۚ۠ۙۗ۠";
                                            break;
                                    }
                                }
                                break;
                            case -494456099:
                                str = "۟ۨ۠ۤۡۥۛۗ۬ۚۜ۟۠۬ۢۦۚۘۘۗۧۦۙۚ۠ۘۗۥۘۥۘۡۢ۫ۦۡ۫ۖۗۙۛۥۘۜۦۧۘۨ۫";
                                continue;
                            case 798657918:
                                str2 = "ۚۚ۟ۚۤ۫ۧۧۥۨۚۢ۬ۗ۬۟۫۠ۖۘۨۧۤ۟ۨۥۗۖۥۛۤۜۖۛۖۨۘ";
                                break;
                        }
                    }
                    break;
                case 1475974796:
                    mediaPlayer.setLooping(z);
                    str = "۟ۨ۠ۤۡۥۛۗ۬ۚۜ۟۠۬ۢۦۚۘۘۗۧۦۙۚ۠ۘۗۥۘۥۘۡۢ۫ۦۡ۫ۖۗۙۛۥۘۜۦۧۘۨ۫";
                    break;
                case 1741999035:
                    mediaPlayer = this.mediaPlayer;
                    str = "ۘۛۙۗ۟ۦۘۘ۟ۛ۟ۙۚ۫ۨ۬ۘۙۨۘۜ۫ۢۤۘۜۥۜۦۘۘۦۚۜۥۦۨ۫ۢۚۘۧۘۗۦۘۛ۬ۜۘۤۖۦۘۗ۠ۗ۬۟ۦۘ";
                    break;
            }
        }
    }

    @JavascriptInterface
    public void setOnMusicBuffering(String str) {
        String str2 = "ۜ۟ۜۘۖ۠ۖۘۚۜۘ۠۬ۖۛۙۗۨۛ۠ۢۜۛۢۡۘ۫ۨۢۙۦۧ";
        while (true) {
            switch ((((str2.hashCode() ^ 232) ^ 334) ^ 196) ^ (-482326094)) {
                case -2069436949:
                    this.jsOnBuffering = str;
                    str2 = "ۦۥۦۗ۬ۚۡۘۦۘۥ۫ۖۘۗۙۘۢۚۚۧۡۨۙۜۨۘۙۖۜۘۦ۫ۨۦ۟۟ۗۥۘۜ۬۬ۗۡۘۛ۬ۚۜۨ۠ۨۥۖۙۜۗ";
                    break;
                case -1397735184:
                    return;
                case -1330219994:
                    str2 = "۠ۤۙ۟۬۟ۗۡۨۧۘۘۖۙۥۘۚۦۧۘ۬۟ۗۚۧ۠ۨ۬۠ۧۛۤۧۛۘ۠ۜۨۘۛۡۤۘۦۡۨۛۡۘۖ۠ۘ";
                    break;
                case 1193804496:
                    str2 = "ۡۜ۫ۗۤۗۜۘۖۘۢۛ۟۫ۥۧۦ۬ۘۛۨ۬ۜ۬ۛ۟ۥۦۘۚۛ۟ۨ۫۠ۜۚۖۘ";
                    break;
            }
        }
    }

    @JavascriptInterface
    public void setOnMusicEnd(String str) {
        String str2 = "۫۟ۨۘ۠ۙ۟ۘۡۖۘۦۧۥۘۗۖۜۚۤۤۧ۫ۨۘ۟ۖۖۙ۟ۤۜۖ";
        while (true) {
            switch ((((str2.hashCode() ^ 717) ^ 935) ^ 611) ^ 1080504033) {
                case -525037619:
                    str2 = "ۛۜۘۘۡۢ۬ۧۡۨۘ۫ۢۨۘۛ۫ۗۛۖۨۘۘۦۧۘ۠ۚۛۛۚ۠ۦ۠ۜۚۙۗۡۛۡۛۥۦۧۘۘ";
                    break;
                case 937502510:
                    this.jsOnEnd = str;
                    str2 = "۫۫۫ۛۨۧۘۛۘۡۧۚۖ۠ۥۤۧۜۤۜۙۚ۟ۨۦۘۧۦۘۙ۬۠";
                    break;
                case 1031037052:
                    return;
                case 2060390819:
                    str2 = "ۢ۟ۤۛۡۜۛۨۙۘۢ۬ۧۧۡۦۨۘۗۨۡۙۖۚۨ۬ۨۘ۠ۤۨۘ۟ۚ۟ۦۚۜۘ";
                    break;
            }
        }
    }

    @JavascriptInterface
    public void setOnMusicError(String str) {
        String str2 = "ۨۘۡۘۜۚۘۘ۟ۖۜۦۜۜ۫ۘۘۛۡۚۜۤۗۧۢۙۛۜۖ۫ۘۡۘۥ۠ۤۢۙ۬ۙۙ۠ۖ۫ۤۢۙۥ۠ۦۥ";
        while (true) {
            switch ((((str2.hashCode() ^ 636) ^ 286) ^ 871) ^ 2144912693) {
                case -1627644297:
                    str2 = "ۚ۫ۧ۟ۜۧۖۛۥۘۖۚۛۦۤۛۧۥۖۢ۫ۧۘ۬ۜۧۦۧ۟ۛۜ۫۠ۢۢۗۙۡۦۦۘۥ۬ۨۘ۫ۛۘۘۤۧ۟ۢ۬ۛۤۧۚ";
                    break;
                case -1325566611:
                    return;
                case 1540158763:
                    this.jsOnError = str;
                    str2 = "ۧۘۙ۬ۜۙۛۥۙۘۢۗۧۥۗ۠ۗۥۘۘ۟ۥۢۛۤۗۚۢ۠۠۟ۨۡۘۘۨۛۢۤۦ۬۠ۜۙۖ۬۠۫ۨ۬";
                    break;
                case 2032283893:
                    str2 = "ۖۚۘۜۢۡۥۘ۫ۥۘۛ۠۟۫ۥۜ۬ۢۗۨ۬ۨۥۧۢۢۢۙۗۡ۠ۥۥۙ۬۫ۨۛ۠ۘۜۨۘۘۗۜۥۦۜۘۤ۫ۥ";
                    break;
            }
        }
    }

    @JavascriptInterface
    public void setSpeed(float f) {
        MediaPlayer mediaPlayer = null;
        String str = "ۖۖۦۘۚ۬ۚۥۧ۠ۤۙۧۦۛ۫ۦ۟ۘ۬ۜۜۘۚۘ۠۫ۡ۠ۖۗۗ۫ۧۗۙۧۡۢۗ۠۟ۘۥۦۛۜۘۨ۟ۘۘ";
        while (true) {
            switch ((((str.hashCode() ^ 285) ^ 676) ^ 231) ^ (-662916301)) {
                case -2092286627:
                    str = "۠ۡۜۚ۬ۖۙۥۧۘۛۙ۬ۦۛۨۘۛۡۥۘۙۧ۠ۗ۟ۜۗۦۨ۟ۨۜۘ";
                    continue;
                case -1592347813:
                    str = "ۥۜۜۘۗ۟۬ۚۚۨ۬ۨۚ۠ۙۗۛۖۢۧۙ۠ۛۧۧۢۧۡۖۨ۟ۙۖۗۜۘۦۖۧۡۖۙۡۥۘۜۛۖۘۗۘۙۧۜۤ";
                    continue;
                case -1366018909:
                    String str2 = "ۦۨ۠ۘۘۡۡۘۛ۠ۜۡۖۛۨۗۛۖۦۙ۫ۡۘۙۘۥ۠ۦۧ";
                    while (true) {
                        switch (str2.hashCode() ^ 161341561) {
                            case -62599277:
                                break;
                            case 174603381:
                                str2 = "۠ۧۧۨۚۗۘۙۥۤۡۧۘۦۘۡۡۖۨۤۤۤ۬۫ۢۛ۠۫ۨۢۙۡ۫ۙۛ۠۬ۘۘ۠۫ۗۜۘۛۜۙۧۜۡۘ";
                            case 450300238:
                                String str3 = "ۛۜ۟۬ۢۘۛۘۘۙۜۜۤۨۖۘۤ۫ۦۘۧۜ۬ۡۦۚۗ۬ۙۛۤۨ";
                                while (true) {
                                    switch (str3.hashCode() ^ 1388975508) {
                                        case -1828846073:
                                            str2 = "ۗۦۡۨ۟ۦۘۖۥۘۘۢۧ۠ۙۧۨ۬ۢۖۘۢۛۘۙۦ۬ۨۤۜۡ۬ۜۛۢۘۘۜۜۚۨۙۖۘۖ۟ۡ۟ۦ۠۠۬ۤۙۙۛ۠ۤۦ";
                                            break;
                                        case -213509394:
                                            str3 = mediaPlayer != null ? "ۧ۠ۢۡۧۦۖ۠ۦۘۛۥۧۦ۫۬ۗۧۦ۬ۚۨۗ۟ۦۛۙۖۘۜ۟۠۫ۥۗۜۘۙۖ۬ۜۡ۟ۦۘۦۨۗۛۘۘۥۙۜۘۗۙۡ" : "۠ۨۨۜۥۧۖۨۡۘۙۗۡۘۡۤ۟ۜۗۧ۬ۥۜۘ۬ۡۚۥۢۨۖ۠ۗۗۦۘۘۡۘۨۜۛۥ۬ۜۘۚۚۨۘۧ۬";
                                        case 1513967683:
                                            str2 = "ۥۙۘۘۤۨۢۧۢۡۗۙ۟ۦۥۜۥۛۦۜۜۨۥۥۜۘۤ۟ۥۢۙ۫۠ۡۢۚۧۥۘۤۤ۟ۘۘۖۖۤۡۘۛۗ۫";
                                            break;
                                        case 2064059303:
                                            str3 = "۟ۛۤۦۥۥۨۗ۬۟ۚۜۘۨۧۥ۠ۥ۟ۗۨۧۚ۠ۡۦۖۘۥۥ۠ۥ۬ۥۢۥۦۘ۟ۛۘۘۜۜۧۛ۟ۛۤۤۦۘ۟ۥۥۜۢۜۘ";
                                    }
                                }
                                break;
                            case 764292548:
                                str = "ۛۙۖۜ۬ۖۘۤۦ۟ۖۢۨۘۤۚ۠ۧۡۜۘۦۖۗۗۧۖۘۛ۫ۢۧ۫ۗۙۘۦۙۥۘۥ۫ۙۗۦۧۛۨۜۘۛۨۧۛ۠ۧۖۨۘۘ";
                                break;
                        }
                    }
                    break;
                case -821608193:
                    return;
                case -56935813:
                    String str4 = "ۛۡۘۘۛۡ۬ۘۢۦۘۖۤۛۢ۫ۥۘۧۜۢۖ۫ۦۧ۟ۧۡ۟۬ۗۡۥۘ۬ۚۢۡۖۤ";
                    while (true) {
                        switch (str4.hashCode() ^ (-1018988232)) {
                            case -1827220909:
                                str4 = "ۛۥۢ۫۫ۥۤۜۘۧۙۡۘۧ۫ۚۡۗۘۘۦۤۘۘ۬۫ۦۘۖۘۜۡۨۨ۠ۛۜۘۧۜۛ۟ۜۙۨۘۢۛۖۥۘ";
                            case -683448058:
                                break;
                            case -455440939:
                                str = "۠۫ۧ۟ۙۡ۟ۡ۫۟ۜۜۘۤۦۖۘۧۘۙۥۛ۠ۖۙۨۘۙ۬۠ۥ۠ۚۚۜ۟ۧۨۖۘۧ۬ۡۢۢۜۙ۠ۦۘۦۚ۟";
                                break;
                            case 272298288:
                                String str5 = "ۤۗ۫ۧ۟ۧۜ۟۬۠ۦۛۜۡۘۢۗ۟ۨ۬ۚ۫ۜ۬ۘۢۙۤۜۘۛۢۘۡۨۨۘ";
                                while (true) {
                                    switch (str5.hashCode() ^ (-151157785)) {
                                        case -1128820159:
                                            str4 = "ۚۦۘۦۖ۠۫ۖۡۡۡۘۧۛۡۚۛ۫ۚۙۨۘۤۚۨۜۥ۬ۚۖۘۘ۟ۖۘۧۙۡۘ";
                                            break;
                                        case -763271668:
                                            str5 = "ۤۢ۠ۗۛۖۘ۫ۡۥۘۜۧۨۘۤ۫۠ۡۡۨۖۙۡۘۖۖۧۧۥ۠ۜۜۘۘ۫۬ۨۖۥۥ۫ۚۥۘۙ۠ۥ۫ۘۨۤۚ۫ۘۢ۫۬ۧ۫";
                                        case 1010251039:
                                            str5 = Build.VERSION.SDK_INT >= 23 ? "ۛۦۗ۟ۨۨۘ۠۠ۡۘ۫۬ۜۖۗۤۚ۬ۜۚۧۢۗ۫ۢۘۤ۬ۜۤۡۘ۫ۨۙ۠۬۠ۖۢۜۘۜۘ۬ۜۜ۫ۖۧۘ۠ۢۜۘۛۜۘۘ" : "۫ۖ۬ۚ۬ۜۘ۫ۛ۟۠ۗۡۘ۟ۤۥ۫ۖۧ۠۬ۗۚۙ۟ۜۥۨ۫ۤۘ۠ۘۗۤۛۛۗ۟۫۫ۦۢ";
                                        case 2101761615:
                                            str4 = "ۖۜۨۥۚۡ۫ۤۜۘۘۦۖۘ۫ۙۡۧۙۨۘۧۜۧۘۥۥۦۘۡۨۦۘۨ۠ۚ۠۫ۦۦۜۤ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case 24247065:
                    mediaPlayer.setPlaybackParams(mediaPlayer.getPlaybackParams().setSpeed(f));
                    str = "ۜۦۧۢۛ۠۫ۡ۬ۨۘۜ۬ۙۢ۬ۗۦۘۡ۟ۜ۬ۙۘ۟۬ۖۘۛ۫۬ۡۛۘ۟ۡۘۘۖۘۘۘ۟۬ۢۘۗۡۘۦۛۨۘۥۤۖ۠۫ۘ";
                    continue;
                case 2127210714:
                    mediaPlayer = this.mediaPlayer;
                    str = "ۨۢۜۖۚۜۦۡۘۗ۟ۧۖۘ۠ۢۤۦ۟ۜۧۘۢ۫ۚۤۢۘۘۘ۫ۖۘۙۘۨۚۗۖۡۖۧۘۘۛ۟۫ۡ۫ۤۖ۫ۜ۫ۛۤۛ۟";
                    continue;
            }
            str = "ۜۦۧۢۛ۠۫ۡ۬ۨۘۜ۬ۙۢ۬ۗۦۘۡ۟ۜ۬ۙۘ۟۬ۖۘۛ۫۬ۡۛۘ۟ۡۘۘۖۘۘۘ۟۬ۢۘۗۡۘۦۛۨۘۥۤۖ۠۫ۘ";
        }
    }

    @JavascriptInterface
    public void setVolume(float f, float f2) {
        MediaPlayer mediaPlayer = null;
        String str = "ۘ۫ۖۢۛۤ۟ۜۦۘۥۦۜۘۡۖۗۢۢۘۘۧۜۜۘ۠۟ۥ۬ۤ۫ۧۢۛۘۖۢۧۥۘۨۡۛۖۙۦۦۦۘۦ۟ۙ";
        while (true) {
            switch ((((str.hashCode() ^ 491) ^ 743) ^ 378) ^ 1690258087) {
                case -1154066999:
                    return;
                case -176932902:
                    String str2 = "ۜۛۙۗ۬ۥۘۧۖۛۜۖ۫ۙۙۚۙۘۘ۠ۨۗۗۙۤۘۧ۟ۛۦۨۛۢۗۡ۠ۡ";
                    while (true) {
                        switch (str2.hashCode() ^ 866162458) {
                            case -364265468:
                                str = "ۧۥ۟ۚۡۦۘۘ۠ۘۘۛۙۜۘۖ۬ۛۜۦ۫ۖۧۜۛۜ۬ۖۡۡۘۚۘۘۘ";
                                continue;
                            case 1695983312:
                                str2 = "۬۫ۧۤۧ۫۠ۘۦ۫ۖۖۚۨۛۨۚۜۧۢۙۛۛ۠ۢۚ۫ۨۙ۬ۖۡۘۘۜۧۜۤۚۛۗۧ۬ۗۙۗۘۗ۬ۚۛ۬۫ۨ";
                                break;
                            case 1774041442:
                                String str3 = "ۡ۠ۨ۠ۤ۬ۧۘۦۖ۬۬ۡۧۨۥ۠ۨۤۧۜۘ۫ۧۖۘ۬۬ۨۘۜۥۜۘۖ۠۬۫ۡۨۘ۟ۦۖ۫ۧۨ";
                                while (true) {
                                    switch (str3.hashCode() ^ (-1414700077)) {
                                        case -1578901989:
                                            str3 = mediaPlayer != null ? "۫ۖۤۤۗۘۧۦۛۗۛۨۘۡۘۘ۟۫ۛ۫ۜۜ۫ۛۡۤۤ۬۫ۖ۫ۡۛۥ۠ۡۦۘۢۜۤۤ۟ۢ" : "ۖۘۦۙۜۨۥۡۧۘۥۧۘۘۖ۟۫ۘۢۦ۠۫ۧۚۡۥۧ۬ۥۘۜ۟ۨۡ۟ۜۧۦۡۘ";
                                        case -347215521:
                                            str2 = "ۘۖۨۘۗۨ۬ۗۡۦۙۤۦۚۡۦ۟ۖۘۚۛ۬ۛ۬ۥۨ۬ۢۢۧۖ";
                                            break;
                                        case 297101920:
                                            str2 = "ۙ۬ۤۛۦۦۤۨۨۤۨۗ۫ۦۨۨۥۧۘۤۖۖۘ۬ۛۡۘۡۖ۫ۘۜۜۥۤۖۘۗۛۛۘ۠ۘۘۗۗۥۥۦۘۚ۫ۡ";
                                            break;
                                        case 2113336860:
                                            str3 = "ۡۜۜۘۨ۬ۜۨۖۡۨۦۡۘ۟۫ۘۘۛۖۜۢۨۨۘۢۧۛۛۛۨۘۧ۬ۨ۫ۘۡۢ۫۠ۚۛۜۘۖۘۤۗۘ۬۟ۥۧ۠ۨۘۗۗۥۘ";
                                    }
                                }
                                break;
                            case 1909218285:
                                str = "ۚ۬ۧۨ۬ۧۥۜ۫ۥۥۦۘۦۧۨ۠۠ۗۨۚۥۤ۟ۜۙۤۤۚۛۦۦۦۘۘۢۥۦۘۥۚ۟ۥۥۨۘ۫ۥۜۘۨۚۦ";
                                continue;
                        }
                    }
                    break;
                case 415676022:
                    str = "ۗۢۦۘۗۚۦۘ۟ۢۜۘۧۥۨۘ۠ۦۥۘۗ۟ۛۘۙۘۘۢۗۚۨ۟ۨۗۧۛۥۘۨۧۨۡۘۧ۟۫۟۟۠ۨۚ۫ۙۛۡۘ";
                    break;
                case 860689706:
                    mediaPlayer = this.mediaPlayer;
                    str = "ۧۧۛۡۖۢۨۜۡۙۙۧۛ۟ۢۚۛۤ۬ۘۘۥۢۢۢۚۚۤۗۘۘ";
                    break;
                case 1287795462:
                    str = "ۖۡۧۧۦۜۘۘۛ۠ۚۚۦۘۘۘ۬۫ۦۙۛۤۦۘۖۛۦۘۨۨۘۨ۠ۜۘ۫ۜۜۘ۬ۚۙۢۨۦۥۡ۟۠ۚۜۦۜ";
                    break;
                case 1404186466:
                    str = "ۥۛۖۗۙۢۥ۫ۡۘۨ۠ۛۢ۟ۤۗۚ۠۫۟۬ۥۜۗۦۗۖۦۜۘۖۖۧۘۜۚۚۢۛۘۥۢ۬ۨۗۤ۠ۚ۬";
                    break;
                case 1985941514:
                    mediaPlayer.setVolume(f, f2);
                    str = "ۧۥ۟ۚۡۦۘۘ۠ۘۘۛۙۜۘۖ۬ۛۜۦ۫ۖۧۜۛۜ۬ۖۡۡۘۚۘۘۘ";
                    break;
            }
        }
    }

    @JavascriptInterface
    public void stopMusic() {
        String str = "ۥۨۥۡۙۡۘۡۤۘۢۤۨۘۗۧۘۚۨۡۘ۟ۘۙۡۥۘۛ۟ۜۘۧۢۛۚۦ۬ۗۢ۟۬ۨۘۙ۬ۤ";
        while (true) {
            switch ((((str.hashCode() ^ 705) ^ 651) ^ 995) ^ (-430886117)) {
                case -491639870:
                    this.mainHandler.post(new w(this, 2));
                    str = "ۛۨۦۛۧۖۘۖۦ۫ۜ۟ۢۡۖ۠۟ۖ۠ۦ۫ۜ۬ۜۥۤ۬ۖۚۧۤۛۛۖۙۙۡۙۜۘۖ۬ۖ۟ۨ۫ۨۦۘۡ۠۬۠ۦۨۘ";
                    break;
                case -311226729:
                    return;
                case 1024340300:
                    str = "ۜۦۗ۟ۦۧۘ۫ۚ۠ۖۛۖۘۦۡۖ۬۟ۜۡۨۖۚۛۘۘۡۛۜۘۛۧۚۗۡ۠ۖۢ۫ۗۖۘۧ۫ۛ";
                    break;
            }
        }
    }

    @JavascriptInterface
    public void verifyCardKey(String str, String str2, String str3, String str4) {
        String str5 = "ۖۤۖۡۡۢۖ۠ۥۢ۬ۙۥ۠۬ۛ۟ۙۤ۠۬۟ۡۦۘۖۥۦۘۚۖۘۗۢۘۡۤۜۘ";
        while (true) {
            switch ((((str5.hashCode() ^ 978) ^ 664) ^ 734) ^ 637850191) {
                case -1895409320:
                    str5 = "۬ۘۢۨۖۜۦۙۥۦۗ۬ۛۥۥ۠ۧۚۜۡۦۘۤۡۦۘۛۖۢۤۢۦۘۗ۟ۧۗۜ۬ۢ۫ۛۡۚۜۛۤ۟۫ۗۘۨۤۥ۫ۖۨۘ";
                    break;
                case -1336552746:
                    new Thread(new v(this, str3, str2, str4, str, 0)).start();
                    str5 = "ۦۘۨۡۗۜۦۙۜۘ۬۠ۚۜ۟ۤۢۧۛ۟۫ۘۘۗۛۗۖۘ";
                    break;
                case -1051054952:
                    str5 = "۟ۙ۠ۤۥۨۡ۬ۡ۫ۥۖۘۚۨۦۘۖۥۖۜۢۙۡۢۗ۟ۡۚۘۙۛۥ۟ۡۙۖۘ";
                    break;
                case -412599944:
                    str5 = "ۜۛۚۢۘۖۙ۠ۧۡۗۗۗۚۘ۠ۢۢۤ۠۠ۥۖۛ۬ۦۛۜۥۘۛۦۧۧۚۢۡۗۤۤۙۖ۬ۜۦۘۦۗۗ۫۠ۥۘۥۧۘۘ";
                    break;
                case 324584950:
                    return;
                case 548738580:
                    k2.logToFloatingWindow(h.e("3IAh4K0Y3bbajxJJfm8ldk1p19O9nyF3dGXIihho6vWMlVkIVRbjkNiUGYqOkSNmT0nVRUJk\n", "PwCxqv7+UxM=\n", new StringBuilder(), str), l2.decrypt("/vOtc+k=\n", "mpbPBo4UFvg=\n"));
                    str5 = "ۡ۫ۜۖۘۧۘۗۤ۠ۘۢ۫۟ۤۖۦۦۨۦۤۦۘۛۘۛۥۢۡ۫ۗۖۜۢۢۢۧ۠ۥۖۤۘۢۡ";
                    break;
                case 1160472009:
                    str5 = "ۘۧۨۘۨۦۚۧۢۡۘۡۡۧ۫ۢۨ۠ۤۡۘۧۦۥ۟ۦۢ۫۫ۘۥۢۡۘ";
                    break;
                case 1971744134:
                    str5 = "ۙۦۧۢۜ۬ۧۧۜۘ۟ۖۡۗۨۘۢ۬۠ۛۧۙۢۙۜۘۚۥۘۥۥۘۘۘۛۨۚۜۦۘ۫ۗۜۚ۬ۦ۫ۡۘۤ۟ۢۛۜۘۡ۬ۤ";
                    break;
            }
        }
    }

    @JavascriptInterface
    public void verifyCardKey2(String str, String str2, String str3, String str4) {
        String str5 = "۬ۢ۬ۜ۬ۙۧۢۦۤۗۢۖۙ۟ۚ۟ۜۘۜۢۥۤۛۦۘ۬۫ۦۘۘۦ۟ۦۛۙۧ۠ۦ۠ۧ۫ۢۚۧۛۧۖ۠ۧ۫ۨۥۘۜۜۥ";
        while (true) {
            switch ((((str5.hashCode() ^ 729) ^ 18) ^ 388) ^ (-1673056400)) {
                case -1137655543:
                    str5 = "ۛۦۤ۟ۧۦۛۨۨۤ۫ۥۦ۬ۥ۬ۙۥۘۤۙ۟ۖ۟۟ۗۖۖۘۧۛۗۧۗۙۜۙ۫ۜۥۜۛۖ۬۫ۚۦۢۜۘۢۢۤۦ۬۟";
                    break;
                case -707431124:
                    new Thread(new v(this, str3, str2, str4, str, 1)).start();
                    str5 = "۫ۛۜۘۜۦۨ۠ۤۜۘۚۧ۠ۘۘ۟۫ۗۡۦۤ۠۟ۗ۟ۛۗۤۧ۟ۚ";
                    break;
                case -18112065:
                    k2.logToFloatingWindow(h.e("tn063z7CxXqwcgl27bU9uieUzOwuRTm7HpjTp03C3WazTj99z4+jb9YaPj1NVCSvII3j8YKY0Q==\n", "Vf2qlW0kS98=\n", new StringBuilder(), str), l2.decrypt("vljdJMM=\n", "2j2/UaQz+DY=\n"));
                    str5 = "۬۬ۘۗ۟ۡۘۘۨۦۘ۬ۜ۟۬ۡۥۛۙۥۘ۟ۗۗۚۦۖۘ۫ۤۥۘۘۡۥ۟ۥۥۧۥۢ";
                    break;
                case 48799009:
                    str5 = "ۚۢۧۧ۫ۦۨۡۘۗۚۥ۠ۘۡ۟ۥۨۘ۬۫ۗۗۢۙۚۙۥۢۖۡ۬ۘۘۘۛۨۦۘۧۡۥۘۙۢۖۘۖۘۗۘۥۧۘ";
                    break;
                case 149157893:
                    str5 = "ۖۛۘۛۢۦۢۛۨۘۧۜۥۖ۟۬ۗۢۚۖۧ۫ۥۜۦۨۚۤ۠ۢ۫۟ۤۨۢۦۘۖۦۦۘۨ۠ۘۘ۟ۘۙۚۛ۫۬ۜۚۜۖۦ";
                    break;
                case 243304425:
                    return;
                case 687426773:
                    str5 = "ۢ۠ۚ۠ۢۥۘ۬ۡۢۤۦۖۘۤۤۘۛ۫ۚۚۤۢۚۥ۠ۤۡۘۚۛۜۘۥ۬ۨۥۗۦۘۤ۠ۨۘۤۙۡۘ";
                    break;
                case 1645681003:
                    str5 = "۠۬ۜۘۚ۟ۢۗۥۜۘ۬ۛۚۦۗۨۜۤۗ۫ۦۨ۬ۚ۬ۡۡۢۛۛۦۛۛۜۘۖ۠۟";
                    break;
            }
        }
    }

    @JavascriptInterface
    public void writeSP(String str, String str2) {
        StringBuilder sb = null;
        String str3 = "ۤۡۡۛ۠۟ۥۘۥۘۥۢۢ۬ۘۥۘۜ۟ۘۚۙۘۘۦ۟ۤۘ۠ۡۘۥۛ۫";
        while (true) {
            switch ((((str3.hashCode() ^ 944) ^ 742) ^ 503) ^ (-360105764)) {
                case -1088301659:
                    str3 = "ۙۖۡۢۜۥۘۨۘۡۧۘۘ۠ۡۖۜۛ۠ۙۥۘ۟ۙۢۢۖۘۘۦۘۧۡۤۙ۬ۦۚ";
                    break;
                case -842282207:
                    sb = new StringBuilder();
                    str3 = "۬ۦۜۘۤۥ۠ۘۘۡۘۤۗ۬ۜۘۥ۠ۧۥ۠ۘۗۦۦۖۢۛۜۜۙۡۘۡۘۨۚ۫ۗۛۙۡۙۥۦۘۧۘ۟۟ۢۜۘ";
                    break;
                case 266845417:
                    str3 = "ۚۤۦۘۡ۬ۡۘۗۙۜۜۛۥۤۖۡۥۘۘۢۗۙۦۨۧ۫۬ۥ۬ۨۘۛۤۜۘۡۛۤۤۛۤۗۨۥۖۜۘ۬۫۟ۤ۫ۜۘ۬ۨۘ";
                    break;
                case 645985062:
                    return;
                case 1054528329:
                    k2.logToFloatingWindow(h.e("yw==\n", "8ZKvg/d29bw=\n", sb, str2), l2.decrypt("GjwcGuA=\n", "fll+b4cpSb8=\n"));
                    str3 = "ۖۦۖۢۥ۟ۡۗۥۘ۫ۧۡۙۢۨۘۦۧۦۘۤۘۛ۬۠ۙۗۗۥۡۖۦۘۥۗ۬ۦۧۦۘ";
                    break;
                case 1446306478:
                    h.f("0j7zJexTUCTUMcCMPySp81jKBjzvlTgXiFjQ+lcXdWmBPYT7F5U7B6hb5spWMFNmjBCM0yU=\n", "Mb5jb7+13oE=\n", sb, str);
                    str3 = "ۥۨۛ۠ۚۨۘ۟ۤۜۘۡ۟ۡۚۦۗ۫ۥۗۚۧ۟ۢ۬۟۫ۨۥۘۦۚۖۗ۟ۧۦۤ۠ۘۜۡۘ۫ۧۖۘۨۚۜۜ۟";
                    break;
                case 1483483594:
                    str3 = "ۨۜۦۘۥۚۦۘۙۤۦۘۢۥۢ۬ۢۘۘۡ۬ۥۘۚۚۤۜۡۖۦۨۜ۫۟۟ۥۥۜۚ۠ۘۜۡۘۦۨ۬";
                    break;
                case 1750705228:
                    this.activity.getSharedPreferences(l2.decrypt("9UvteyYoBSs=\n", "jD6DAU5dd14=\n"), 0).edit().putString(str, str2).apply();
                    str3 = "ۡۡۛۤ۟ۛۙ۬ۛۦ۬ۦۧۤۘۘۡ۫ۥۘ۠ۢۖۘۖۛۨۘۚۗۘۘۛۘۢۖۚۨۙۙۧ";
                    break;
            }
        }
    }
}
