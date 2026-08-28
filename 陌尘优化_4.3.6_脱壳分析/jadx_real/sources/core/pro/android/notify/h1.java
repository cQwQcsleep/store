package core.pro.android.notify;

import android.app.Activity;
import android.app.Dialog;
import android.os.Handler;
import android.view.KeyEvent;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;
import gTBLD.dev.XSSTG.free.JsInterface;
import gTBLD.dev.XSSTG.free.Utils;
import java.util.Set;
import org.json.JSONException;

/* loaded from: /workspace/unpacked/classes2.dex */
public final /* synthetic */ class h1 implements Runnable {
    public final int a;
    public final boolean b;
    public final Object c;
    public final KeyEvent.Callback d;
    public final Object e;
    public final Object f;
    public final Object g;

    public /* synthetic */ h1(Activity activity, boolean z, Dialog dialog, String str, WebView webView, JsInterface jsInterface) {
        this.a = 0;
        this.c = activity;
        this.b = z;
        this.d = dialog;
        this.e = str;
        this.f = webView;
        this.g = jsInterface;
    }

    public /* synthetic */ h1(int[] iArr, TextView textView, Handler handler, Runnable[] runnableArr, boolean z, Runnable runnable, int i) {
        this.a = i;
        this.c = iArr;
        this.d = textView;
        this.e = handler;
        this.f = runnableArr;
        this.b = z;
        this.g = runnable;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0036. Please report as an issue. */
    @Override // java.lang.Runnable
    public final void run() throws JSONException, NoSuchFieldException, SecurityException {
        Object obj = null;
        boolean z = false;
        Object obj2 = null;
        Object obj3 = null;
        Object obj4 = null;
        Object obj5 = null;
        int[] iArr = null;
        int i = 0;
        Button button = null;
        StringBuilder sb = null;
        Runnable runnable = null;
        int[] iArr2 = null;
        int i2 = 0;
        TextView textView = null;
        StringBuilder sb2 = null;
        Runnable runnable2 = null;
        Activity activity = null;
        Dialog dialog = null;
        x0 x0Var = null;
        WebView webView = null;
        JsInterface jsInterface = null;
        String str = "ۡۗ۫ۢۨۖۜۘۖۘ۫۠ۨۛۖۡۘۘ۟ۗۜۦۡۛۗۦۦ۠ۦۘ۟ۨۘۘۗ۬۠ۚ۠۟ۙۙۜۘۧۤۛۚۦۡۘۢۜۛ";
        while (true) {
            switch ((((str.hashCode() ^ 80) ^ 222) ^ 771) ^ 1692111235) {
                case -2058567704:
                    Utils.monitorDialog(dialog);
                    str = "۠ۜ۟۫ۖۗۗۗۜۘۨۤۛۖۜۘ۬ۢۘۘ۟ۦۥۚۢۘۘ۫ۨۜ۬ۗۡ۠ۨۛۤۜۖۘ۠ۙ۟ۚۘۡۢۛۧۛۚۦ۠ۧۜۘۚۤۤ";
                case -2010903847:
                    obj2 = this.e;
                    str = "۫ۢۤۢۘۧۘۗۤۛۗۢۥۘۚۛۖۘۢۡۘۜۛۛۘۤۧۜۜۢۧۙۙ";
                case -1902350881:
                    Set<String> set = k2.closedPopupIds;
                    str = "ۢۥۖۘۥ۟ۢۦۡۜۘۚۛۢ۠ۡۦۗۤۘۘۤۥۗ۫ۚۘۘۖۧۘۘ۟ۨۡۡۚ۬ۙۨۧۤۦۧۗ۬ۦۘۚۤۚۚ۫ۖۨۘۗۚۙۨ";
                case -1738967428:
                    String str2 = "ۡۢۡۜۜ۠۟ۜۡ۠ۜۖۤۛۚۧ۠ۧۡۖۡۘۛۛۥۘۡ۟ۢ۟۟ۚۚۢۙۖۢ۠ۖۘۚۢۢۖۙۖۘ۬ۢۧۗۦۘۗۡۤ";
                    while (true) {
                        switch (str2.hashCode() ^ (-1442904943)) {
                            case -1965995841:
                                break;
                            case -1534248803:
                                str = "ۗۚۡۘۨۧۨۘۦۥ۫ۖ۠ۢۖ۟ۘۙۖۦۨ۫ۢۤۨۖۘۙۘۦۘ۠ۜۢ";
                                break;
                            case 1377013122:
                                str2 = "ۨۡۧ۬ۘۥۘ۬ۨۜۘۜۚۨۡۖۥۘۢۖۨۘ۠ۘۗۚۤۖۢۜۤۡۙۦۦ۟ۡ۬ۘۘۙۧۧ۠ۧۦۘ";
                            case 1509775023:
                                String str3 = "ۧۧ۠ۤۗۢۘۦۙۛۗۤۥۨۙۧۦ۠۠۠ۜۘۨۜۦۚۚ۫ۘ۟ۡۘ";
                                while (true) {
                                    switch (str3.hashCode() ^ (-557023603)) {
                                        case -1679994928:
                                            str2 = "۠۫ۦۘ۬۬۟ۡۤۨۘ۬ۜۦۧۡۘۘۛۘۢۖۙۢ۬ۗۘۘ۟ۢۘۡ۠۬ۧ۠ۦۘ۟۬ۜۘۤۙۥۘ۫ۢۙ";
                                            break;
                                        case -1266344117:
                                            str3 = "ۛ۬ۧۡۥۨۘۢۨۤۗۤۥۘۨۨۦۨۡۢۨۤۧۢۨۘۘۡ۟ۜۘ۬ۘۚۘۘۗ۫ۚ۫ۚۥ۠۠ۢۡۘۤۤۨۘۨۦۦۘ۟ۨۜۘۗ۠ۤ";
                                        case 1842780231:
                                            str3 = z ? "ۚۧۘۗۖۖ۟ۤۤۜۨۚۗ۬ۗ۬۟ۙ۬ۦۜۘۙۤۡۡ۬ۧۗۙۡۢۢۥۘ۫ۚ۬" : "۬ۛۡۘ۫ۗۧۛۙ۬ۡ۬ۢۨۤۨۤۛۢۖ۬ۖۘۨۚۨۘۖۧۖۚۛۛۥ۟۟۠ۢۖۘ";
                                        case 1871213066:
                                            str2 = "ۙۡۧۘ۠ۤۢۜۡۙۚۘۘ۫ۛۗۚۜۨ۟ۘۡۘ۟ۙۤۖ۫ۥۘ۟۟ۛ۠ۚۚ۫ۢۖۘۙۜ۬ۘۤۜۘۛۜ۬ۜ۟ۘۘ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case -1662505388:
                case -677618980:
                case 2137297085:
                    break;
                case -1577132754:
                    sb.append(iArr[0]);
                    str = "۬ۢۖۘۡۗ۠۟ۗ۟ۙۥۙ۠ۤ۬۠ۗۖۘۘۗۤۙۡۡۥۚۛ۬ۤۧ۬ۛۚۦۖۥۦۡۦۤۛۘ۬ۘۛ۟ۢ۟ۚۢۤ۫ۙ";
                case -1470061921:
                    sb = new StringBuilder();
                    str = "۬۟ۜ۟ۢ۟ۦۥ۟ۡۖۧۘۙ۠ۗ۬ۖ۬ۢۦۖۤۘۥۗ۫ۖۘۛۙۡۘ۫ۤۖۘۤۧۤ۠ۜۘۘۜۡۦۘۥۘۧۘۡ۟ۡ";
                case -1403900360:
                    str = "ۖۘ۬ۜۡ۬ۙۥ۫ۡۨ۬۠ۙ۠ۛ۟ۢ۟ۧۜۚ۠ۨۗۚۛۥۛۙۨۘ۟۫ۖۘۘ";
                case -1334654702:
                    String str4 = "ۖۛۦۘۙ۟ۡۘۗ۫ۨۜۘۚۢۤۗۥ۟ۖۡۢ۠۬ۜۦۘۚ۟ۚۖۚۙۡ";
                    while (true) {
                        switch (str4.hashCode() ^ 472627653) {
                            case -1707551626:
                                str = "ۥۢۙۧ۠ۜۘۙۚۘۖۙۛ۠ۗۡۚۨۙۘۦۘۗۗۚۢۗۥۘۡ۠ۛ";
                                continue;
                            case -1247474168:
                                str = "ۚۘۧ۠ۙۡۘۥ۫ۖ۫ۨۖۥ۠ۨۗ۬۠ۛۤۡۘۢۖۗۗۜۢۗۨۦۘۢۚۤ۠۫ۥۘۢۤۧۨۥۘۘۜۨۧۘۙ۟ۖ";
                                continue;
                            case -162094240:
                                String str5 = "۠ۨ۬ۘ۬۬ۥۧۛۦۡۘۘۤۖۡۘۚۘ۠ۚۖۡۗۜ۫ۜۗۧۗ۫ۦۘ۟ۘۧۜ۟ۙۘۨۖۘۧۗ";
                                while (true) {
                                    switch (str5.hashCode() ^ (-2013591551)) {
                                        case -1185637660:
                                            str5 = "ۤ۫ۜۘۧۜۖۘۜ۟ۦۘۨۥۘۦۡۙۧۗۗۦۗ۠۬ۢۗۧۚۥۢ۬ۖۘۜۙ۟ۙۗۦۘ۟ۧۛۨۜۚۙۖۙ۠ۨ";
                                        case -658369972:
                                            str4 = "ۙۤ۫۫۫ۥۛۡۨۛۢ۬ۖۢۡ۫ۦۨۥۥۜۤۛۧ۠ۡۤ۟۬ۜۘۛۘۖۘۚۡۚۨۙ۬ۡۘۘۙۖۗۖۜ۫";
                                            break;
                                        case 1019274615:
                                            str4 = "ۜۙ۫ۛ۬ۜۘۜۛۙۗ۟۠ۧۡ۫۠ۘۛۨۧۨۧۡ۟ۖۛۘۙۚۗۡۢۜۘ۬ۜۜ";
                                            break;
                                        case 1531236931:
                                            str5 = i > 0 ? "ۘۥۜۘۡۗۡۨۙۥۜ۬ۜۘۦۘۤ۬۠ۜۘۢ۠۫ۧۜۘۨۚۨۤۛۦۘ" : "ۖۧۘۘۥۥۨۧ۫ۜۜۗۙۗ۟۫ۜۤۤ۠ۙۛۚ۟ۦۘ۫ۜۦۢۧۤ";
                                    }
                                }
                                break;
                            case 1594311712:
                                str4 = "ۗ۠ۛۦۖۚۜ۫ۜ۟ۦ۠ۧۤۛۛۤ۟ۢۧۥۘۛۦۛ۟ۖۦۘۢ۫ۨۨ۫۬۟ۢۛۧۛۡۤۘۡۥۖۧۗۛۤ";
                                break;
                        }
                    }
                    break;
                case -1318620521:
                case 531631812:
                    str = "۠ۘۚۛۦ۫ۜ۫ۦۘۦ۠ۚ۫ۧۥۘۥۢۗۤۚۘۘۥ۬ۖۧ۠ۜۘۧۙ۠ۙ۠ۘۥۘ";
                case -1122739635:
                    x0Var = new x0(dialog, activity);
                    str = "ۦ۟۟ۡۨۦۘۤۧۢۧ۟ۧ۫ۦۧۗۜ۟ۦۘۧۘۡۘۨۛۢۛ۠ۤ۬ۖۛۙۨۥۘ";
                case -1118971027:
                    z = this.b;
                    str = "ۢۢۥۦۨۜۘۗۛۜۘۦۦۧۘۤ۫ۨۙ۟ۙۨۘۘۘ۟ۛۥۘۤۘ۟ۥۢۦۘۚۡۜ۫۬ۗۚۦۖۘۖۛ۠";
                case -1095043393:
                    str = "۬ۘ۬۬ۚ۠۠ۗۖۙۗۦۘ۫ۗۥۤۘۘۘۖۡۡۚۙۗۥۛۨۘ۬ۧۗۥۤۤۤۤۛ";
                    activity = (Activity) obj5;
                case -995165524:
                    x0Var.addOnDismissCallback(new x(activity, (String) obj2, webView, jsInterface));
                    str = "ۙۦۡۖۥ۫ۢۛۛۙۘۖۘۙۘ۫ۡ۫ۢۢ۫ۨۘۨۢۜۘۗ۫ۖۘۖۗۦۢۧۛۦۤۤۨۜۜۘۤۤۦۘ";
                case -927608371:
                    k2.logToFloatingWindow(l2.decrypt("lUzKFXGBwvlDnVrEig05sxO7FlDhdBz5SJZb+72g4lG4yBTr\n", "9C++fAfothw=\n"), l2.decrypt("9UbeTg==\n", "giesIKTh32M=\n"));
                    str = "۠ۘۚۛۦ۫ۜ۫ۦۘۦ۠ۚ۫ۧۥۘۥۢۗۤۚۘۘۥ۬ۖۧ۠ۜۘۧۙ۠ۙ۠ۘۥۘ";
                case -844864017:
                    Set<String> set2 = k2.closedPopupIds;
                    str = "۟ۨۡۘۧۦۜۜۧۦۧۥۨۘ۫ۙۘۘۚۚۚۨ۫ۧۙۥۘۖ۠ۧ۬۬ۖۘ۟ۘۘۦۤۖۘ";
                case -733915044:
                    String str6 = "ۜۙۗۛ۠ۢۘۧۘۗۘۤۥۛۢۢۙۨۘ۫ۜۢۘۙۢۖۥۗۧۥۘ";
                    while (true) {
                        switch (str6.hashCode() ^ (-1228425430)) {
                            case -1024265665:
                                str = "ۥ۟ۥۘۘۢ۟ۚۨۖۘۚۘ۠ۧۚۢۙۖۙۨۨۡۤۧۜۦ۫ۜۘۨ۫ۙۘۢ۬ۡ۟ۙۛۦۗۛ۟۫ۙۧۜۘۗۛۡۥۤۚۗۗۨ";
                                continue;
                            case -916354318:
                                String str7 = "ۨۙ۫ۜۛۙ۟ۛۢۨۜۘۘۤۤ۬ۨ۠۬ۡۨۜۘۘۨۙۗۢۖۘۨۛۡۨۙۦۤۛۚۡۜۧۘۦۦۧۘ";
                                while (true) {
                                    switch (str7.hashCode() ^ 1574719572) {
                                        case 447927932:
                                            str7 = z ? "ۧ۟ۜۧۤۦۘۚ۫ۙ۠ۖۖۘۦۨۨۘ۟۫ۛ۬ۨۤۧۨۗ۫ۖۘۥۗۘۘۜۦۛۛۨ۬ۤ۫ۖۘ۟ۥۥۘ۠ۖ۠ۨۦۡ" : "ۦ۬ۨۜۧۜۘۡۧۥۘۧۦۤ۠۟ۗۥۛۙۨۦۜۘۡۙ۫ۖۛ۫ۤۘ۟ۡۗۛ۟ۙۜۘ";
                                        case 480125854:
                                            str6 = "ۡۨۜۘۧ۠ۖ۫ۤۚۗۜۨۘ۬ۚۢۥۡۥۚۙۗۥۘۘ۬ۡۡۘ۟ۨۘۛۖۨۘۥۛ۠ۚۤۗۚۜۗۖۜۜۘ۫ۖۛ";
                                            break;
                                        case 939145438:
                                            str7 = "ۧۜۗۥۧۚۘ۬ۧ۬ۖۢۨۛۙۢۡۨۘۜۢ۬ۘ۬۬ۤۛ۬۠ۢۘۙۤۨ۫ۘۘۖۥۢۢۘۘ";
                                        case 1676399615:
                                            str6 = "ۚۦۨۘ۟ۡۥۦۚ۬۫ۦ۟ۡۙۘۘ۬ۚۨۨ۫ۦۘۛۢۚۥۢۜۘۨ۫۟ۤۖۚۤۖۤ";
                                            break;
                                    }
                                }
                                break;
                            case -665639930:
                                str6 = "ۚۙ۬ۢۙۖۡۨۦۚۖۨۨۨۘۗۢۚۦ۟ۗۚۦۦۛۚۖۘۚۦۖۜۚۘۖ۫ۘۛ۠ۙۦۖۧۘ";
                                break;
                            case -435895820:
                                str = "۠۫ۛۧۜۤۜۨ۬ۜۜۡ۫ۢۢۚ۫۠ۢۦۙۡۗۘۚۜۜۚۨۘۜۧۘۘ۠ۘۜۛ۟ۙۨۦۖۜۛۥۚ۠ۜۗۗۨۙ۫ۖۘ";
                                continue;
                        }
                    }
                    break;
                case -601365620:
                    obj3 = this.f;
                    str = "ۗۡۘۘۦ۫ۖ۬ۦۘۨۖۧۘۦۛ۠۠ۖۘۘ۟ۘۘۘ۟ۧۜ۫ۘ۬ۙۘۨۘ";
                case -531308251:
                    x0Var.show();
                    str = "ۡۨۥۜۘۡ۬ۖۦ۬ۥۗۡ۟ۖۡ۬ۖۘ۟ۦۧۙۨۦۘۙۡ۠ۢۘۘۛۛۨۘۗۛ۟ۦۜۛۦ۫ۧ";
                case -490879054:
                    str = "۬ۧۜۘۧۛۥۜۜۤۧۖۨۛۙۖۜۥۘۘۢ۬ۘۛۨۘۛۛۘۘۥۨ۠";
                    iArr2 = (int[]) obj5;
                case -462549148:
                    ((y) obj).run();
                    str = "ۖۗۡۖۗۥۘ۠ۙۘۧۘۜۜۛ۫ۙۡۚۥۥۥ۫ۨۘۡۧۦۘۢۧۙۛۢۛ۬ۧۚ۟ۥ۫ۨۘۘ۫ۤۖۧۘۦۘ";
                case -393618678:
                    obj5 = this.c;
                    str = "۫ۦۡۘۖۜۚۧۦۛۥ۟ۛۢۥۧ۫ۛۦۘۤۧۖۨۨۢ۠ۘۘ۫ۙۘۘ";
                case -319481230:
                    button.setText(sb.toString());
                    str = "ۜۡۖۡ۫ۛ۫ۡۥۦۢۦۙ۬ۜۙۜۜۘ۠ۥۦۘۚۡۨۘۙۗۖۘۡۥۖۖۘۙۡۢۛۘۧۙۤۦۢ";
                case -294476090:
                    str = "ۚۘۧۘۥۧۘۘ۫ۥۦۨۨ۟۬ۡۘۘۡۚ۬ۜۢۘۨ۟۬ۙۜۛۛۧ۬۠ۨۖۧۡۘۙ۬ۥۢۛۜۘ";
                    iArr = (int[]) obj5;
                case -194413904:
                    obj4 = this.d;
                    str = "ۥۧۧۤۘۤۨۦۘۥۙۡۤۢۧ۫۬ۘ۫ۨۡۘۗ۫۫ۛۧۢۗۘۨۘ۠ۤۖۚۢ۫ۧ۬ۜۛۗۖ";
                case -190450940:
                    String str8 = "ۚۤۡۚۨۗۦۤۘۘۚۗۨۨۨۦۚۖ۠ۢۧ۠ۘۡۥ۟ۖ۟ۢۤۗۘۘۘۨۗۥۘۧۡۦۗۘۦۦۨ۠۬ۢۘۘۖۨۨۘۦۥۘ";
                    while (true) {
                        switch (str8.hashCode() ^ (-1635728211)) {
                            case -338366759:
                                str = "۠۫ۖۡۦۡۘۖۙۥۘۢۚۛ۬ۦۡۘۨۨۡ۟۠ۖۜۖۥۘۜۧۗ۬ۤۨۙۦۘۡۨۖۛۘۘۧ۫۟ۛ۠۬ۚۦۚ";
                                continue;
                            case 1106090528:
                                str = "۟۠ۗۨۢۨۜۜۘۘۙۢۨۘۗۗۘۢۡۡۛۦۘۦۡ۬ۚۨ۟۫ۨۖۘ";
                                continue;
                            case 1950066690:
                                String str9 = "۬ۨۘۘۗۥ۫ۦۨۤ۫ۚ۠۠ۨۖ۠ۘۗۢ۫ۢۧ۬ۧ۟ۤۡۥۛۜۨۦۥۦۨ۫ۚۙۘۘۗۢۖۘۚ۟ۥۖۜۘۘ۟ۘۢ۫ۘۧۘ";
                                while (true) {
                                    switch (str9.hashCode() ^ 108704301) {
                                        case -1235550639:
                                            str8 = "ۜ۟ۖۘۨۥۘۘۦۜۡۘ۠ۗۥۙۙۨۘۥۘۨۗۘ۬ۦۨۡ۠ۨۤۢۢۨۘۚۥۦۘ۠ۗۜ۠ۚۜۧۛۘۦۜۜۘۧۧۗۚۗۙۚۧۦۘ";
                                            break;
                                        case 44273455:
                                            str8 = "ۨۦۖۚ۫۠۫ۥ۟۟ۢۧۙۜۗۛۗۜۗ۬ۤۦۜ۠ۨۖ۫۟ۤ۬ۧۖۙۗ";
                                            break;
                                        case 552470286:
                                            str9 = i2 > 0 ? "ۘۥۛۛۧۚۜۗۧ۬ۖۘۜۢۖۜۦ۬ۧۖ۠ۘۚۧۙۡۖۘۚ۟ۘۥۚۦۧۡۘۘۗۖۜۘۨۚۢ" : "ۢۘۢۦۤ۠ۜۤۘ۟ۖۧۘۗۡۦ۠ۥ۠ۛۗۦۥۘۘۥۨۘۦۥ۠۫ۧۖ۠ۖۡۘۧ۫ۛ۬۫ۨۛۗۘۘۧ۟ۧ";
                                        case 1112463654:
                                            str9 = "۫ۛۛۨۘۗۜ۬ۥۚۧ۟ۚ۟ۥ۠ۥۘۜۡۨۘۡۤۛ۬ۡۖۘۦۦۤ۠۟۬ۤۖۘۘ۫ۗۥۘۖۥۘۦۖۦ۠ۖۡۘۧۜ۫ۚۢ";
                                    }
                                }
                                break;
                            case 2009952291:
                                str8 = "ۥ۟۬ۙۙۦۘۥۗۦۘۚ۠ۘۘۗۧۖۨ۟ۡ۬۬ۗ۫۬ۨۙ۫۬ۖۙۜۘۙۘۡۘ۟ۤۥۨۥۦۛۤ۬";
                                break;
                        }
                    }
                    break;
                case -173873917:
                    Set<String> set3 = k2.closedPopupIds;
                    str = "ۗۘۨۘۚۙۡۘۘ۫ۖ۠ۙۨ۬ۜ۟ۢۖۨ۟ۡۜۨۚۦ۠۬ۙۨ۬ۛ";
                case 115883387:
                    iArr2[0] = i2;
                    str = "ۧۛۦۘ۫ۖۥۙ۬ۖۘۚۜۜۘ۬۫ۡۘ۬ۙۤۤۡۨۘ۟ۨۥۤۥۜۡۤۘۖۛۛۦۢۥۙۘۢۦ۫";
                case 119018873:
                    sb.append(l2.decrypt("Y/dLiw==\n", "QxDsGQG5HxM=\n"));
                    str = "ۨ۟۫ۖۖۜۗ۟ۛۜۨۘۜۛۦۘۦۛۖۘ۬ۜۖۘ۠ۦۥۘ۟ۗۤۧۦۘۥ۠ۤ۫ۙۡۤۧۖۘۘۗۡۘ۠ۡۧۦۢۘ";
                case 230922048:
                    i2 = iArr2[0] - 1;
                    str = "ۘ۠ۦۘۘۘ۫۠ۤۦۘۢۗ۬ۧۡۛ۠ۙ۫۫ۘۤ۠ۘۢۛ۠ۦۘۨ۬۫ۚۙۙ۬۫ۤۦۗ۫ۥۙۘ۟ۖ۫ۘ";
                case 260009576:
                    str = "ۚۙ۟۫۫ۙۤۧۨۘۘۤۥۘۛ۟ۨ۬ۢۥۘ۫۟ۗۖ۟۫ۚۘۢ۟۠ۦۘۦۚۦۘۦۤ۫ۖ۫ۡۘۨۙۘ";
                    textView = (TextView) obj4;
                case 337353809:
                    ((l1) obj).run();
                    str = "ۙ۬ۦۧ۟ۥۘۗۡۜۘۧۖۖۙۛۘ۬ۙۖۘۙۘۜۘۤۨۙۚۦۘۚۙۘۘ";
                case 437182460:
                    String str10 = "ۖۢۛۢۢۘۘۡۡ۬۠ۢ۬ۚۛۨۨۛ۠۬ۨۥۧۡۗۨ۬ۜۤۘ۫ۛۡۘۨۖۥ";
                    while (true) {
                        switch (str10.hashCode() ^ 468459715) {
                            case -1714940632:
                                String str11 = "ۤ۬۟۟ۘۧۙۚۧۚ۠ۚۡۜۤ۬ۢۦۧۡۘ۟ۚۖۘۖ۬ۖ۬ۚۢۢۢۜۢ۬ۦۡ۫ۘۘۡۥۢۖۖۥۘۛ۠ۗ۬ۜۧۘ۫۠ۨ";
                                while (true) {
                                    switch (str11.hashCode() ^ 531503126) {
                                        case -628418177:
                                            str11 = "ۥۖۖۘۨۤۤ۫ۙۜۘۙۙ۬۟ۗۤۛ۬۬ۦ۬ۗۦۙۜ۠ۘۗۤۡۜۘ";
                                        case -379088939:
                                            str10 = "ۖۡۘۨۨۥۘۖۘۥۘۛۙ۠ۧۤۜ۟ۛ۠ۡ۠ۜۙۨۡۘۖۡۗ۬۠ۤۖۨۧۘۚۗۥۘ";
                                            break;
                                        case -326017006:
                                            str10 = "ۧۙۡ۫ۙۧۦۦۨۖ۠۬ۨۗ۟ۛۜۖۗۚۨ۫ۗۙۢۥ۠ۡۜۖۡۖۘۗۜ۬ۢۦۗۨۘ";
                                            break;
                                        case 293663762:
                                            str11 = !activity.isDestroyed() ? "ۥ۬ۜۘۚۜۘ۫۬۫ۡۨۡۘ۠ۧۜۘۛۧۖ۟ۡۘۢ۫ۘۚۗۨ۠ۥ۟ۥۥۛۙۖۘۘۡۢۗۨۡۗۧۨۖۥۨۛ" : "ۘۖۜۢۛۘۘۜۡۧۘۗۢۤۤۥۢۤۡۜۘۨۚۢۡۜۨۘۛۜۦۘ۫ۜۡۘۜ۫ۢۚۧۜ۫ۥۖۥۙۡۘ";
                                    }
                                }
                                break;
                            case 283787:
                                break;
                            case 1068746294:
                                str = "ۖۖۤ۠ۙۡۘۥۙ۬۬ۥ۬ۥۚۢۗۢۜۘۖۙۜۡۗ۬ۧ۫ۤ۬ۦۙۦۖۨۘۘۛ۟۟ۚۥۚۘۘ۫ۙ۫ۖ۫ۥۖۘۤۤۦۘ";
                                break;
                            case 1876359187:
                                str10 = "ۥ۟ۢۢ۟ۖۜۨۘۨۙۨۘ۠ۛۦۘۙۗۗۚۤۡۘۢۡۘ۟ۦۚۚۧۤ۬ۗۘۘۤۡۥۗۥۜۚۘ۠ۤۙ۬۫ۦۚۜۗ۠۫ۖۘ";
                        }
                    }
                    str = "ۜ۫ۙۧۤۛۛ۠ۜۥۨ۟ۛ۬ۗ۬۬ۛۥ۫ۖۘۜۦۨۘۦۚۨۘۥۧۙ";
                    break;
                case 458714761:
                    obj = this.g;
                    str = "۟ۚۖۘ۫ۦ۫ۥۤۜۘۙۛۥۡۙۙ۟ۙ۫ۥۧۛۤ۬ۡۘۥۖۧ۬ۦۡۘۥ۠۠ۜ۬ۤ۬ۖۢۛۖ۬ۘۖۢ۫ۘۘ";
                case 496685948:
                    k2.logToFloatingWindow(l2.decrypt("IBHzEyR80D7O3WDu+jlBZ/iXAMAaQemXptgQ\n", "QXKHelIVpNs=\n"), l2.decrypt("PUuoeA==\n", "SiraFgOecRQ=\n"));
                    str = "ۦۦۧۖ۫ۖۦۤ۟ۤ۠ۘۘۢۖۨۘ۫ۡۦ۫ۙۢۛۜۤۗۨۗۚۛۢۛۨۦۦۖۗۛۦۧۡ۫۫ۤ۫۬ۧۡۦ";
                case 671809804:
                    str = "۟ۘۖۘ۠ۢۘۘۘۨۥۦۛۦۘۦ۟ۨ۠۫ۖۘ۫ۜۚۡۦ۫۟ۡۛۗۡۘۢۢۤۚۙۨۖۥۘۛۤۨۘۛۘۗ۠ۙ۠۫ۥۢۗۢۚ";
                    jsInterface = (JsInterface) obj;
                case 671895515:
                    sb.append(l2.decrypt("YzEPO6bPlg==\n", "hrim3xtWttU=\n"));
                    str = "ۥۧۨۘۜۤۡۘۙ۟ۥ۬ۦۗۨۤۛۥ۫۫ۚۦۦۘ۠۠ۡۦۘۢ۬ۨۘۦۘۙۥۢۤۗ۫ۡۘۖۙۛ۟ۡۛۦۨۦۘ۠ۖۡۘ۠ۙۘۘ";
                case 679831373:
                    sb2.append(l2.decrypt("TTcpwQ==\n", "bdCOUybGigQ=\n"));
                    str = "۬۠ۧ۠۫ۙۜۗۜۘۖ۟ۡۘۨۤ۟ۛۗۥۘ۟ۨۙۛۖۡ۫ۨۖۤۚۜ۟ۦۧۥۖۘ";
                case 716355150:
                    switch (this.a) {
                        case 0:
                            str = "ۗ۫ۘۡۚۘۨۡۗ۠۠۬۟ۛۤۘۦ۠ۨۤۜۦۜۙۙۤۗۢۜ۠ۧۡۜۗۗۘۘۘۦۨۘۙ۬ۘۘ۫ۧ۬۟ۤۥۘ۬ۗۡۘۢۢۘ";
                            break;
                        case 1:
                            str = "ۙۖ۟۫ۛۙۖۚۨۘۗۖۡۦۗۨۦۘۘۢۨۗۘۢۜۘۜۛۘۘ۠ۛۡۢۚۜۘ۫۠ۙ۫ۙۨۘۖۛۡۘۘۥۢ۬ۤۖ";
                            break;
                        default:
                            str = "۫۠ۗ۟ۖۖۘۡۤ۠ۚۡۧ۬ۙۘۘ۫ۙۖۘ۟۟ۛۖۥۢۜ۠ۤ۬۠ۛ۟ۡۘۥۦ۫ۧۥۡۘۥ۬ۜۘۢۖۚۗۢۥۛۙۨۘۨۡ";
                            break;
                    }
                case 743881012:
                    String str12 = "ۚ۬ۛ۬ۡۜۖۡۙۧۡۧۘۗ۠ۨ۠۠ۜۘۡۗۙۧۚۥۘۜ۫ۗۢۧ۠";
                    while (true) {
                        switch (str12.hashCode() ^ (-1452508101)) {
                            case -498956102:
                                str = "ۡ۬ۦۤۛۜۗۗۘۦ۠ۙۖۨۤۘۖۘۜۚ۟ۤۘۗ۟ۛ۬ۖ۟ۤۛ۠ۚۜ۠ۜۛۘ۫ۛۛۖۘۡۢ۫۠ۙۦۙۛۙۧ۬";
                                break;
                            case -296454776:
                                str12 = "ۤۦۦۧۢۨۥۘۜۖۛۖۡ۬ۚ۠ۚۢۤ۟ۥۖۧۖۤ۠ۨۧۤۤۡۘۘۡۡ";
                            case 1836137543:
                                String str13 = "ۤۙۡۘ۬ۗۢۜۧۜۘۗۜۤۢۢ۟ۥۚ۬۬ۦۖ۠ۙۗۚۧۥۘۙ۟ۤ";
                                while (true) {
                                    switch (str13.hashCode() ^ (-1414483708)) {
                                        case -672443518:
                                            str12 = "ۦۚۜۘۚۡ۬۫ۨۖۤۡ۫۫۠ۦ۬۬ۧۧۖۨ۬ۙ۬ۦۦۛۡۜۢ۟ۗۖۤۨ۬ۥۖ۬۫۟";
                                            break;
                                        case 368576245:
                                            str12 = "ۚۦۖۖ۠ۜۛ۬۠ۥۢۙۢۚۘ۟ۤۡۘۛ۫ۥۥۨۨۚۧ۟ۛ۠ۘ";
                                            break;
                                        case 923971603:
                                            str13 = !activity.isFinishing() ? "ۡۧۜۘۧ۟ۖۘ۠ۙۢۢۡۡۘۤۙ۬۬۬۠ۡۖ۫ۥ۠ۘۘۧۧۛۧۧۜ۠ۨۖۛۧۦ۟ۤ۟ۧۦۘۖۦۘۧۜۜۘۡۘۨۘ۠ۜۦۘ" : "ۤۨۥۖۤۚۢۚۗۖۗۘۘۚۥۥۘۙ۟ۜۨۧۘOۙۧۥ۫ۢۢۘۥۗۜۜۜۘۜۤ۫ۛۢۚۦۧۨۘ۬ۢۡ";
                                        case 1918657113:
                                            str13 = "ۚۙۨۗ۫ۘۘۚۗۛۜ۫ۙ۫ۗۘۥۖۨۘۡ۠ۡۘ۟ۥ۫۟ۘۨۗۧۘۧۛۦۘۧۚۛۖۦۙۧۡۨۡۡۘۖۧۘۘ";
                                    }
                                }
                                break;
                            case 1885020867:
                                break;
                        }
                    }
                    break;
                case 755403679:
                    sb2.append(iArr2[0]);
                    str = "۬۬۬ۡۨۡۘۥۘ۫ۘۥۘۖۛ۟ۤۧۥۛۗۖۧۖۚ۟ۧۥ۠۠ۨۘۖۘۧۘ۫ۖۡۤۥۢۡۛۡۘۧۛۙ۫ۥۜ";
                case 767268478:
                    runnable = ((Runnable[]) obj3)[0];
                    str = "ۘ۫ۘۚۤۢۗۛ۟ۙ۫ۖۥ۬ۜۘۚۨۨ۬ۥۤ۟ۢۡۘۗ۫ۙۤۥۗ";
                case 783700063:
                    dialog.show();
                    str = "۫ۧۚ۫۟ۨۘ۟ۥ۫ۛۜۦۘۦۧۘۘۤۘۗۙ۬ۛۤ۟ۦۘ۬ۗۡۘۧۙۜ";
                case 912521744:
                    button.setText(l2.decrypt("uwqSPuHo\n", "U7UJ22RNAc4=\n"));
                    str = "ۨۙۨۡۧ۬ۙۧۛۚ۬ۗۤۥۜۘ۫ۜۙۦۥۤۙۦۤۢۚ۬ۢۜۦۥ۠ۡۘۨۗۘۘۙۡۥۘۧۖۧۧۙۜۘ۠ۥۧۘ";
                case 920256705:
                    str = "ۙ۬ۦۧ۟ۥۘۗۡۜۘۧۖۖۙۛۘ۬ۙۖۘۙۘۜۘۤۨۙۚۦۘۚۙۘۘ";
                case 1058826857:
                    ((Handler) obj2).postDelayed(runnable2, 1000L);
                    str = "۠۫ۜ۠ۡۨۗۗۥۚۦۛۨۙۢۡۢۤۥۜۗۢۧۛ۠۠۫ۖۗۥۖۛۡ۫ۤۨ۬ۧۤۡۖۜ";
                case 1063672508:
                    i = iArr[0] - 1;
                    str = "ۖۖۘۘۖۨۗ۫ۚۨۘۜۡ۟ۜۛۨۚۛۖۛ۟ۦۘۚۨۗۙۚۙۢ۫ۚ";
                case 1217923651:
                    str = "ۙۤۛۤۙۡۘ۫ۙۚۡۢۖۘۛۘۡۘ۫ۨۖۗۗۡۘۖۜۨۘۧۛۨۜۛۦۚۧ۠ۜۤۡۤۥۛۛ۠ۥۘۨۘۗۗۨۧۧۦ۠۬ۤۢ";
                    dialog = (Dialog) obj4;
                case 1343787650:
                    str = "ۨ۫ۡۘۡۨۜۧۖۘۘۢ۬ۤۛۧۗۙۥۥ۬ۖ۠۠ۨۘۛۘۥۧۢۡۘ";
                    webView = (WebView) obj3;
                case 1371378180:
                    iArr[0] = i;
                    str = "ۡۦۧ۠ۗۜۨۚۛۢۥۖۘۤۨۦۚ۠ۡۚ۬ۨ۠ۙۗۧۨۧۘۡۧۡۧۢۚۥۢ۬۫ۘۦۘۜۨۦ۫ۜۛۤۢۙۤۗۥۡۚۥ";
                case 1374012049:
                    k2.confirmPopupShown(dialog);
                    str = "۫ۨۥ۟۬ۦۘ۟ۡۦۤۙۜۢۥۧۘۚۤۥۡۧۦۘ۟ۜ۟۟ۜۜۡۦ۫ۚ۠ۨۘ";
                case 1457196663:
                    str = "ۤۘۤۨ۫ۨ۠ۘۧۘۤۨۖۢۙۧۤۗ۫ۥۙۨۘ۫ۗۛۡۧۡۨ";
                    button = (Button) obj4;
                case 1696217100:
                    runnable2 = ((Runnable[]) obj3)[0];
                    str = "ۖۚۘۘ۟ۤۤۘۙۛۜۘۨۢۚۘۘۡۢۦۗۖۡۘۜ۟۫ۧۧۦۘۘۦۘۘۖ۟ۨۘۢ۬ۧۙ۬ۛۗۗۜۘۚۢۡۘۖۧۗۚۡ۠ۦۙۨۘ";
                case 1859823509:
                    ((Handler) obj2).postDelayed(runnable, 1000L);
                    str = "ۜۛۧۥۡ۟ۥۨۘۛۨۚۤۡۚ۟۟ۥۗ۟۫ۡ۫ۙۙۥۡۡۤۦۘۥۧۡۛۗۖۘ۟ۛۡۚۘۘۢۙ۫ۧۗۤ";
                case 1863624998:
                    sb2.append(l2.decrypt("DociM0ukPQ==\n", "6w6L1/Y9HUE=\n"));
                    str = "۫۟ۙۥۙ۟۬ۗۦۤۡۙۛۗۡۘۙ۬ۨۘۛ۠ۘۘۥ۠ۡۖۘۨۘۤۡۢ۬ۨ۠ۢ۬ۡۧۦۖۗۗۗ۫ۘۘۜۜ۟ۚ۫ۚ۟۟ۤ";
                case 1905109220:
                    textView.setText(sb2.toString());
                    str = "۟ۧۡۘ۬۠ۘۤۖۘۛۛۖۘۢۧۛ۫۬ۜۧۦۘۡۙۚۗۢۗۙۥ۬ۤۛۖۘۜۡۤ۫ۦ۠ۖۛۘۜ۫ۖۘۥۖۡۘۖ۟ۧ۠ۤۜ";
                case 1921622015:
                    sb2 = new StringBuilder();
                    str = "۠ۙۥۘ۠۬ۛۙ۫ۨۘۛۖۦ۟ۚۦۖۧۚۖۚۙ۟ۘۘۧۢۘۢ۠ۢۜۢۚ۟ۘ۫ۡۚۦۘۧۨۧ";
                case 2076104450:
                    String str14 = "۫۫ۚۘۦۧۘ۬ۧ۟ۘۤۜۡۙۚۤۙۖ۬۠ۗۢۗۚۗ۬ۛۦۘ";
                    while (true) {
                        switch (str14.hashCode() ^ 2109299561) {
                            case -713738322:
                                break;
                            case 35087905:
                                str = "ۙۦ۬ۚۗۡۗ۟ۜۘۖۨۙۗ۟۫ۙۡۥ۬ۨۖۥۨۥ۬ۢۨ۟ۧۗۜۘ۠ۜ۫ۗۗۤ۠ۙۨۜ۠ۥۨۖۘ";
                                break;
                            case 492044027:
                                String str15 = "ۧۧۥۘۢ۠ۨۗۗ۠ۥۡۨۘۨۜۚۖۚۥ۫ۥۙ۬۟ۤۚۦۡ۟ۘۘۘۚۡۙۚۚۦۛۗۚۦۧ";
                                while (true) {
                                    switch (str15.hashCode() ^ 983697030) {
                                        case -1673006875:
                                            str14 = "ۗ۬ۘۢۙۥۗ۬ۙۗۨۘۦ۫۠ۗۜۥۘۢۥ۠ۚۗۥۘۚۤۡۥۙ۠۠ۨۘۡ۫ۡۘۢۚۢۦ۫ۗۙۛۥ۠ۨۛ";
                                            break;
                                        case -285327678:
                                            str14 = "ۘۖۡۚۖۡۧۢۨ۫۟ۖ۫ۚۚ۫ۨ۠ۖۦۦۙۙۥۗ۬ۘ۠۟۫";
                                            break;
                                        case 1485155677:
                                            str15 = "ۧۖۥۘۦ۫ۙۜۢۡ۫ۖۧۘۦۦۡۘۡۡۡۘ۫ۙۛۙۖ۟ۥ۟ۜۤۥۧۘۧۨۙۢۢۥۘۘ۫ۗ۫ۤۗۡۥۡۥۙۛ";
                                        case 1527436505:
                                            str15 = z ? "۠ۘ۠ۤۙۦۘ۠ۖۜۘ۬۠ۗۚۡ۠ۗ۬ۖ۬ۧۘۘۜۡۥۚۗۖۘۥۨۘ" : "ۖۖۢ۬ۗ۫ۛۢۥۘۛۡۤ۬ۜۧۗۛ۟ۡ۬ۛۗ۟ۗ۟ۢۜۘۦۤۜۘۛۛۨۚۡۡۜۥۘ۟ۗۧۤۚۜۤۤۦۘ";
                                    }
                                }
                                break;
                            case 1653027845:
                                str14 = "ۧ۠ۗۜۨۥۧۡۨۘۡۚۥ۬ۗۘۘ۬۬ۦۡۦۘ۟ۢۡۘ۟ۧۧۥۘۧۘۖۙۡۘ۫ۖۡۘۢۥۦۘۥۧۜۨۙۗ۟ۨۘ";
                        }
                    }
                    break;
                case 2095815401:
                    textView.setVisibility(8);
                    str = "ۗۚۖۗ۟ۨۘۧ۬ۨۖۡۤۗۨۡۘ۬ۘۨۘۡۖۡۗۤ۬۟ۤ۫ۨۘۜۗ۠ۘۦۚۙۥۧۜۘۢۚۖ";
                case 2116802972:
                    str = "ۖۗۡۖۗۥۘ۠ۙۘۧۘۜۜۛ۫ۙۡۚۥۥۥ۫ۨۘۡۧۦۘۢۧۙۛۢۛ۬ۧۚ۟ۥ۫ۨۘۘ۫ۤۖۧۘۦۘ";
            }
            return;
        }
    }
}
