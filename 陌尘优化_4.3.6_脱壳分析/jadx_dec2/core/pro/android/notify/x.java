package core.pro.android.notify;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.util.Log;
import android.webkit.WebView;
import dalvik.system.DexClassLoader;
import gTBLD.dev.XSSTG.free.JsInterface;
import gTBLD.dev.XSSTG.free.Utils;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import org.json.JSONObject;

/* loaded from: /workspace/unpacked/classes2.dex */
public final /* synthetic */ class x implements Runnable {
    public final int a;
    public final String b;
    public final Object c;
    public final Object d;
    public final Object e;

    public /* synthetic */ x(Activity activity, String str, WebView webView, JsInterface jsInterface) {
        this.a = 1;
        this.d = activity;
        this.b = str;
        this.e = webView;
        this.c = jsInterface;
    }

    public /* synthetic */ x(JsInterface jsInterface, String str, JSONObject jSONObject, String str2) {
        this.a = 0;
        this.c = jsInterface;
        this.b = str;
        this.e = jSONObject;
        this.d = str2;
    }

    public /* synthetic */ x(Object obj, String str, String str2, Object obj2, int i) {
        this.a = i;
        this.c = obj;
        this.b = str;
        this.d = str2;
        this.e = obj2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:217:0x01c8, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:8:0x002c, code lost:
    
        android.util.Log.w(r4, core.pro.android.notify."当前无有效Activity，无法显示消息弹窗");
     */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void run() throws IllegalStateException, IllegalAccessException, InstantiationException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        String str;
        Method method;
        Object obj = this.c;
        Object obj2 = this.e;
        Object obj3 = this.d;
        String str2 = this.b;
        switch (this.a) {
            case 0:
                JsInterface.e((JsInterface) obj, str2, (JSONObject) obj2, (String) obj3);
                break;
            case 1:
                Map map = (Map) k2.k.get((Activity) obj3);
                String str3 = "ۦۡۢۥ۠ۘۖۜۤۥۚۗۘۨۨۘ۠ۖۜۘ۬ۚۡۘۡۥۖۘۘۚ۠ۨۡۡ۫ۦ۬ۚۖۧۘۗ۠ۜ۫ۡۢۜ۠ۡ۬ۖۘۛۛۚ۟۠۟";
                while (true) {
                    switch (str3.hashCode() ^ (-438208223)) {
                        case -1048065123:
                            break;
                        case -529347928:
                            str3 = "ۨۜۦۛ۫ۘۛۥۜۡۗۥۨۨ۠ۤۖۢۖ۟ۨ۟ۨۧۧۗۡۖۘ۫ۜۧۡۥۧۘۛۤۘۢۡ";
                            continue;
                        case 444712149:
                            map.remove(str2);
                            break;
                        case 1142764458:
                            String str4 = "۠ۨۢۚۡ۫ۦۙ۬ۡۚۘۘۚۥۖۘۡۦۙۤۥۘۘ۠۫ۦ۫ۢ۟ۤۥ";
                            while (true) {
                                switch (str4.hashCode() ^ (-613492534)) {
                                    case -2087787718:
                                        str4 = "ۚ۠ۨۘۢۥ۬ۗۥۘ۫۫ۡۘۢۨۘۘ۠ۜۧۘۧۖۙۡ۫ۘۚۤۦۘۧۘۙۤۖۘۤۘۧۘ۟ۖۧۘ۬۟۬۫ۙۘ۟۫ۤۘۖۜۘۥ۬ۥۘ";
                                        break;
                                    case -1676653770:
                                        str3 = "ۘۗۦۘۨۗۚۤۢۖۘۙ۟ۜۘۡ۟ۥۘۧۙۖۘۚ۠ۢ۫ۖۘۦ۟ۨۧۢۛۧۚۢۡۦۨۘۡۖۥۘۢۘ۠۫ۛۥۤۙۘۘۚ۟ۖ۬۠";
                                        continue;
                                        continue;
                                    case 1008302597:
                                        str3 = "۟ۛ۠ۘۚۖۘۛۢۙۧ۠ۛۚ۫ۘۘۡۤۛۨۗۚ۠ۧۖۛۤ۫ۦۧۤۚۖۨۧۘ";
                                        continue;
                                    case 1778249508:
                                        if (map == null) {
                                            str4 = "ۛۦۗۗۚ۠ۧۥۜۘۤۡۖۗۘ۬ۜ۫ۦۘۨۛ۬ۙۘۘۜۨۦۘۖ۟ۢۥۘۙۥۜۘۙۙۦ۫ۘۨۘۘ۬ۙ۠۠ۘۘۢۘۗۨ۫ۦ";
                                            break;
                                        } else {
                                            str4 = "ۘۤۜۘۙۛۡۘۘۛۚۦۧۘۦ۬ۦۜۖ۫ۡۢۜۘ۫ۘۤ۬۟ۡۘۤۘۨ۟ۢۖۘۢۛۨۘ";
                                            break;
                                        }
                                }
                            }
                            break;
                    }
                }
                Map<String, WebView> map2 = k2.htmlPopupWebViews;
                String str5 = "ۙۛۦ۟۬۟ۧۡۖۘۧۖۨ۬۟۠ۨۨۧۥۦۦ۠ۗۖۘۜۛۜۨۤۘۖۚۜۘۢۗ";
                while (true) {
                    switch (str5.hashCode() ^ 1444484320) {
                        case -1774964171:
                            map2.remove(str2);
                            break;
                        case -1536280329:
                            break;
                        case 489959436:
                            str5 = "ۥۢۛۙۡۙۛۤۥۘۨۖۡۘۥۧۤۧۘۘۙۚۨ۬ۛۧۙۡۚۢۙۖۘۥۡۙ۟ۦۘ";
                            continue;
                        case 2040415689:
                            String str6 = "ۚۜ۟ۛۜۨ۠۬ۨۜ۟ۖۧ۬ۘۘۛ۬۫ۧ۬ۜۘ۬۟ۧۖۜۖ۟ۖۨ۠ۨ۬۟۠ۜۘۘۦۙۘۜۦۨ۟ۘۚۙ۟ۥۡۨۛۜۘۘ";
                            while (true) {
                                switch (str6.hashCode() ^ (-261611782)) {
                                    case -1586774644:
                                        if (map2.get(str2) != ((WebView) obj2)) {
                                            str6 = "ۧۦۘۘ۠ۧۦۧۙۜ۠۫ۘۥۖۖ۫۫ۦ۫ۘ۠ۤ۟۫۬۠ۥ۟ۗۦ۟ۛۙۧۚۤۥ۠ۡ۫۬۠ۜۦ۫ۧۡ۠ۡۧۘۦۖۤ";
                                            break;
                                        } else {
                                            str6 = "۫۬ۜۘۤ۫ۜ۫ۜۤ۠ۥۡۤۥۡۘۘۚۜۘۛۜۗ۫ۙۙۤۚۥۘ۟۫ۦۗۘۜ۬ۧۡ";
                                            break;
                                        }
                                    case -1169612410:
                                        str5 = "ۙۧۥۘۥۦۖ۫ۛۨ۫ۛۦۚ۬ۤۜۤ۠ۦ۟ۜۘۜۦۖۥ۠ۖ۟۟ۢۦۛۛۧۚ۟";
                                        continue;
                                        continue;
                                    case 417060234:
                                        str6 = "۠ۛۧۚۛۘۘۢ۬ۡۘۘۚۢۛۖۜۤۢۚ۬ۜۡۘۤۥۘ۟ۥۛۗۗۥۨۨۙۥ۟ۥۘۦۧۛۜ۟ۦۘۖ۠ۗۧۢۜۘۤۘۘۨۖۦ";
                                        break;
                                    case 592578440:
                                        str5 = "ۤۥۘۘۛ۬ۖۘۙۗ۟ۘ۟۟ۦۛ۠ۗ۟ۜۧۖ۫ۙ۟ۡۘ۟۫ۤۖۧۜۘۗۡۥۘ۫۬ۗۤۖۦۘ۟ۦۛ";
                                        continue;
                                }
                            }
                            break;
                    }
                }
                ((JsInterface) obj).onDialogDismissed();
                break;
            case 2:
                DexClassLoader dexClassLoader = (DexClassLoader) obj;
                Context context = (Context) obj2;
                Activity activity = Utils.a;
                try {
                    try {
                        Class<?> clsLoadClass = dexClassLoader.loadClass(str2);
                        Method[] declaredMethods = clsLoadClass.getDeclaredMethods();
                        int length = declaredMethods.length;
                        int i = 0;
                        while (true) {
                            int i2 = i;
                            str = (String) obj3;
                            String str7 = "۟۟ۜۘۤۚۡۥۜۧۘۡۤۨۘ۠۟ۛۖۘ۠ۘ۬ۨۘۚۢۨۚۧ۟۫ۘۥۢ۠ۨۘۖۛۥۛۤۧۢۡ۫";
                            while (true) {
                                switch (str7.hashCode() ^ (-1475269560)) {
                                    case -2023189335:
                                        method = null;
                                        break;
                                    case -830423294:
                                        String str8 = "ۘۚۘۘۨۨۡۘۦۢۘۘۦۡۘۨۨۦۜ۫ۢۛۗۤۧۨۘۙۖۙ۬ۗۦۘۜۥۡۘۖۢ۫ۤ۬ۥۘۦۨۥۘۜۜۡۘ۬۟ۡ";
                                        while (true) {
                                            switch (str8.hashCode() ^ (-1653437131)) {
                                                case -1224261016:
                                                    str8 = i2 < length ? "ۖۖۧۜۦۧۨ۠ۨ۟ۨۘ۫ۤۙۚۙۨۥۤۡۚۨۥۛۙۛۘ۠ۨۘۖۖۜۙۨۧۘ" : "۠ۛۡۜ۬ۘۘۜ۟۠ۚۨ۫ۚ۫ۜۛۘۧۘ۫ۢۖۛۧۛۗۡۡۧۛۛ۬ۦۚۡۙۖ۟ۡۘ۠ۙۖ۟ۚۖۘۥۛۘۘۗ۠ۥۡ۠ۦۘ";
                                                case -942786764:
                                                    str7 = "ۡۦۨۘۨ۠ۥۘۤ۟ۖۘۙ۬ۙۗۗ۠۬ۧۦۘۜ۬ۛۦۙۘۘۚ۫ۢ۫ۦۖۘۜۧۖۘۢ۬ۙۚۨ۬ۗۘۡ۠۫۫ۘ۠ۨ";
                                                    break;
                                                case 38279215:
                                                    str8 = "۬ۨۗۜۛۖۘ۟ۗۗ۬۫ۖۖۤ۫ۢۗۥ۫ۦۗ۬ۙۗۛۦۗۦۥۘ۬ۡۜ۫ۥۤۦۙۘۘۚۛۚ";
                                                case 1036964903:
                                                    str7 = "ۛۗۖۢۘۘۘ۟ۖۢ۠۬ۙۧۧ۟ۤ۫ۜۘۨ۬ۚۘۢۙۖۧۘۙ۟ۥۘۥۛۛۢۙۡۘ۬ۘۢ۫ۥۤۨ۠ۧۤۜۘ";
                                                    break;
                                            }
                                        }
                                        break;
                                    case -530285130:
                                        Method method2 = declaredMethods[i2];
                                        String str9 = "ۢ۟ۖۘ۠۟ۤۘ۬ۛ۫ۙۨۘۤۧۥۘۜۗ۫ۥ۟ۥۡۡۦۘۜۦ۫ۤۦۤ";
                                        while (true) {
                                            switch (str9.hashCode() ^ 72712750) {
                                                case -1857317491:
                                                    String str10 = "ۧۡۦۢ۬ۜۘۦۘۚۛۛۘۘ۠۬ۥۘۧۗۗ۟ۜ۫۬ۗ۠۠۫ۖۘۘۨۗۚۦۚۚۥ۬";
                                                    while (true) {
                                                        switch (str10.hashCode() ^ (-1341745193)) {
                                                            case -707642651:
                                                                str9 = "ۘۛۚۧۥۗۖۦۥۗۦۤۡۜۘۥۘ۟ۜۛۧۖۢۙ۠ۜۦۘ۬ۖۘۘ";
                                                                break;
                                                            case 389955171:
                                                                str10 = method2.getName().equals(str) ? "۫۠ۦۢۜۙۛۜ۫ۨۤۛ۠ۨۖۖ۬ۧۚۚۦۖ۬ۥۚۥۘ۫ۙۦۚۖۛ۫ۜۗ" : "ۗۥۚۢ۟ۦۨۛۖۘ۠۬ۗۘۗۖۘ۠ۘۘۛۡۗۘۢۘۘۧۡۨۘ۟ۢۜۛۨۘ۟ۡۥ";
                                                            case 988106102:
                                                                str10 = "ۙۨ۠ۙۜۛ۬ۦۢۢ۠ۘۘۥ۠ۘۘۛۤۡۘۘۚۜ۬ۖۡۜۖۘۦ۫۟۫۬۠ۢۥۦۘ";
                                                            case 1508593430:
                                                                str9 = "ۜۜۧۘ۬ۤۘۘۢ۫ۛۦۢ۠ۗۥۚۗ۠ۗۥۜۖۘۘۘۜۨۛۡۖۡۤۢ۫ۛۗۥۤۨ۬ۦۘۖۧ۬ۧۥۙۘۜۢ";
                                                                break;
                                                        }
                                                    }
                                                    break;
                                                case -1376233120:
                                                    break;
                                                case 907814909:
                                                    str9 = "ۚ۫ۜۨۥ۬ۘ۬ۨۧ۬ۛۧۙۨۘۘۗۨۚۡۢۗۘۜۘۛۧۘۘ۟ۧۘۧۘۡۜۚ۟ۖ۠۫۫ۘۜۘ";
                                                case 1750708386:
                                                    Class<?>[] parameterTypes = method2.getParameterTypes();
                                                    String str11 = "ۥۥۙۗ۟۟ۖۤۦۘ۬ۗۡۘۚۛۧۤ۠ۖۘۘۢۤۧۤۡ۬ۦۦۤ۫۫";
                                                    while (true) {
                                                        switch (str11.hashCode() ^ (-1089347081)) {
                                                            case -1061013007:
                                                                String str12 = "ۨۢۘۘۗۡۥۥۨۛۗ۫ۘۘۡۡۧۘ۫ۚۖ۟۟ۘۘۜۢۧ۠ۨۖۘۥ۠ۘ";
                                                                while (true) {
                                                                    switch (str12.hashCode() ^ (-1997498582)) {
                                                                        case -1748136348:
                                                                            str11 = "۟۟ۡۤۜۗ۫ۧۧۗ۬ۥۜۚ۬۟۫ۡۘۦۢۥۘۧۚۡۜۜۦۤۦۧۧ۟ۗ۫ۧۦۖۨۤۨۜۘ";
                                                                            break;
                                                                        case -1685540722:
                                                                            str11 = "ۙۚۜۜۧۘۘ۠ۡۥۘۨۨۨۘۢۛۙ۬ۖۡۨۜ۫ۜۤۜۘۦۦۜۖۦۦۘۥۤۜۘۙ۬ۧۖۛۦۘۖۨۥۘۡۖۘ۠ۙ۬ۛۧۖۛۖۜ";
                                                                            break;
                                                                        case -1333572423:
                                                                            str12 = "۫ۙ۫ۥۗۗۢۛ۠ۧۘۘۙۛۘۚۡۖۘۛۛۡ۠ۧۘ۫۠۫ۘۚۤۚ۟ۤ۠ۗۜۥۤۘۚۗۛۨ۠۟ۧۨۦ۬۬ۜ۬ۡۙ";
                                                                        case 695517015:
                                                                            str12 = parameterTypes.length == 1 ? "ۖۤۨۦۥۡۘۨۡۘۧ۫ۖ۠ۘۗۖۘۥ۫ۙۛۛۗ۟ۛ۬ۡ۫ۗۘۙۡۜۙۖ۟ۙۗ۫ۡ۫۟۫ۛۛۥ۬ۨۘ" : "۠ۤۗۢ۟۟۠ۗۜۘۜۛۡۘۙ۬ۖۘ۟۫ۧۘۢ۟ۜۘۜۢۖۖۥ۟ۚۜۙ۟ۡ۟ۗۧۡۗۗۜۢ";
                                                                    }
                                                                }
                                                                break;
                                                            case -496313888:
                                                                String str13 = "ۦ۫ۥۘۙۗۘ۬۫ۛۗۦۖ۬ۚۦۘۖۦۢۜۙۗۢۚۨۘ۠ۦۨۛ۟ۙۦ۬ۜۘۧۗۘۜۨۧ";
                                                                while (true) {
                                                                    switch (str13.hashCode() ^ 71604508) {
                                                                        case -1251840151:
                                                                            str13 = "ۛۦۥۡۦۢۛۥ۬ۛۧۜۘۜۘۛۖۙۥۘ۫ۗ۠ۗۦۢۧۖ۠ۨ۠ۘۘۨۨ۬ۚۦۡۛۗۦۘۙۗۦۤۤۦ۬ۚۧ۟۟ۘۛۡۙ";
                                                                        case -610109145:
                                                                            break;
                                                                        case -186769633:
                                                                            method = method2;
                                                                            break;
                                                                        case 941145113:
                                                                            String str14 = "ۛۨۘۜۗ۬ۜۦۢ۟ۚۛ۟۬ۦۘۢۘۤۘۦ۫۬ۘۨۚۙۙۨۨۘۖۨۨۘۜ۬ۥۘ";
                                                                            while (true) {
                                                                                switch (str14.hashCode() ^ 1531001764) {
                                                                                    case -1665702809:
                                                                                        str14 = Context.class.isAssignableFrom(parameterTypes[0]) ? "ۤۖ۫ۚۥۦۘۧۘ۟ۘۙ۟ۙۗۥ۟ۨۘ۟ۜۖۘۛۙۜۦۜۥۦۧۡۘۖ۫ۤۚۥۧۘۥۚۖۘۜۘۥۘۗۛ۫ۙۗۘ" : "ۢ۬ۜۘۛ۟ۛ۟ۢۤ۬ۙۡۨۤ۠۟ۨۘۖۧ۠ۙۨۖ۫۫ۖۘۜۘۙۥۖۦۙۛۖۘۡ۫ۡۘۜۘۨۘ";
                                                                                    case -1036287390:
                                                                                        str13 = "ۛ۟ۛۢۖ۬ۧ۟۫۟۟۟ۜۨ۟ۨۥۡۘۗۤۦۥ۬ۦۜ۠ۜۡۛۨۘۤۨۤۖۚۢ۟ۨۜۙۙۗ۟ۨۙۡ";
                                                                                        break;
                                                                                    case 1382384566:
                                                                                        str13 = "ۛ۬ۦۡۗۜ۟ۗۨۘۧۗۨۤۙۗۛۧۗۙ۟ۥۖ۬ۙۢ۫ۢ۬ۨۦۘ";
                                                                                        break;
                                                                                    case 1593608778:
                                                                                        str14 = "ۦۙۢ۬ۚۦۛۧۗۜۢۨ۟ۧۘۚ۬ۢۙۛۘۨ۠ۥۘ۫ۥۡۘۚۧۥ";
                                                                                }
                                                                            }
                                                                            break;
                                                                    }
                                                                }
                                                                break;
                                                            case 732144378:
                                                                break;
                                                            case 896143727:
                                                                str11 = "ۘ۫ۥۘ۠ۤۘۘۘ۟ۛۧۥۘۗۥۚۧ۬ۤۤ۟ۘۘۚۨۡۡۜۜۘۜۥۘ۟۠ۙۨۗۘۘۢۢۧۡۢۛ";
                                                        }
                                                    }
                                                    break;
                                            }
                                        }
                                        i = i2 + 1;
                                        break;
                                    case 1209054136:
                                        str7 = "ۖ۟ۡۘۙۦۡ۫ۙۡۘ۫ۥۜۘۚۦۜۘۘ۟ۘۡۦۡۘ۠۫ۛۤۜۡۘ۠ۧۖۢۚۙۦۖۥۘ";
                                }
                            }
                        }
                        String str15 = "ۚۗۥۘ۬ۜۘۤ۬ۚۘ۠ۨۨۧۘۥۧۘ۠ۜۙۧ۟ۗۢۛۛۖۦ۬ۖۤۖ۬ۧۡۙ۠ۨۢۗۡ";
                        while (true) {
                            switch (str15.hashCode() ^ (-509606621)) {
                                case -186710423:
                                    Object objNewInstance = clsLoadClass.newInstance();
                                    method.setAccessible(true);
                                    method.invoke(objNewInstance, context);
                                    k2.logToFloatingWindow("✅ 远程dex执行成功：" + str2 + "." + str, null);
                                    break;
                                case 207771211:
                                    String str16 = "ۙۡۙۨۧۘۘ۫۫ۨ۬ۛۘۛۧ۟ۢۘۘۤۤۜۘ۟ۙۖۨۡۡۖ۬ۜۧۤ۠ۗۢۦ۬ۢ۬ۢ۠ۙۙۡ۫ۜ";
                                    while (true) {
                                        switch (str16.hashCode() ^ (-67744226)) {
                                            case -764043094:
                                                if (method != null) {
                                                    str16 = "۠ۥۦۘ۠ۥۥۘۘۦ۬ۧ۟ۜۗ۟ۖۘۖۨۛۥ۬ۦ۠ۗۥۚۖۚۧۡۗۢۧۢۥۤۜۨۡۘ۟۬ۙۖۨۘ۫ۜۘۢۙۘۚۜۚ";
                                                    break;
                                                } else {
                                                    str16 = "ۛۧۖۘۥۜۨۙۧۙۡۥۚۢ۟ۛۢۥۨۘۖۥ۠ۢ۬ۖۨۤ۫ۡۜ";
                                                    break;
                                                }
                                            case 1028139175:
                                                str15 = "ۢۙۦ۬ۦۧۘۗۘۖۙۘۙۡۧۘۤۜۦۘۙۗۘ۬۫ۢۛ۟ۚۥۢ۟ۛۗۘ۟ۥۗۦۢۨۘۙۡۜۘ";
                                                continue;
                                            case 1051720594:
                                                str16 = "ۤۜ۫۟ۡۘ۫ۚ۟۠ۨۨۢۢۡ۫ۤۖۘ۠ۨۘۘۗۘۥۦ۫۫ۦۜۘۚۖۦۘۜۚۜ۫ۥۘۦۘ۬ۤۡ۬ۤ۫";
                                                break;
                                            case 1297215587:
                                                str15 = "ۖ۫ۡ۬ۨۖۘۜۥۙ۬ۜۥۢ۬ۚۢۛۚۡ۬۟۫ۙۡۢ۫ۢۥ۠ۡۧۤۨۘۨۤ۟";
                                                continue;
                                        }
                                    }
                                    break;
                                case 235041620:
                                    str15 = "ۧۨۡ۫ۙۧۨۡ۫ۧۨۚۚۚۜۢۜۘۨ۠ۛ۠۠ۦۘۗۘۡ۬ۤۚ";
                                    break;
                                case 308392438:
                                    k2.logToFloatingWindow("❌ 远程dex未找到正确的入口方法：" + str + "(Context)", "error");
                                    break;
                            }
                        }
                    } catch (ClassNotFoundException e) {
                        k2.logToFloatingWindow("❌ 远程dex类不存在：" + str2, "error");
                        return;
                    }
                } catch (Exception e2) {
                    Log.e("RemoteDex", "执行出错", e2);
                    k2.logToFloatingWindow("❌ 执行出错：" + Log.getStackTraceString(e2), "error");
                    return;
                }
                break;
            default:
                String str17 = (String) obj3;
                String str18 = (String) obj2;
                String str19 = s3.q;
                ((s3) obj).getClass();
                Activity activityECt8jHZ4 = Utils.ECt8jHZ4();
                String str20 = s3.q;
                String str21 = "ۨ۠ۥۘ۟ۤۜۘۖۢۥۘۥۨۥۛ۫ۨۙۚۥۚۧۛۧۘۦۘۤ۬ۥۢۢۤۛ۬ۗۥۦۧۛۚۗۡۛ۬۠ۢۡۘۡۢۘۗۢۘۜۦۡۘ";
                while (true) {
                    switch (str21.hashCode() ^ 651595467) {
                        case -1471728844:
                            String str22 = "۟۫ۚۦۥۡۘۙۥۜ۫ۥۦ۟ۤۥۘۥۧ۫ۢ۟ۘۡ۠۠ۢۨۡۖۨۜۘ";
                            while (true) {
                                switch (str22.hashCode() ^ 1136966251) {
                                    case -71263564:
                                        str21 = "ۗۡۖۘۤۚۖۛ۟ۘۘۤۤۡ۫ۤۘۘ۠۬ۗۗۜ۫ۖۤۗۜ۟ۧۨ۫۟۟ۗۥۘۡۜۚۖ۠ۖۗۥۡۦۜۢۧۖۦۘۤۢۗۙۢۚ";
                                        continue;
                                    case 437678265:
                                        if (activityECt8jHZ4 == null) {
                                            str22 = "ۛۡۛ۫ۧۛۘۧۜۘۗ۠ۖۤۖۖۘۥ۟ۖۘۙۧ۬ۛ۬ۢۧ۟ۙۜۖۤۢۙۘۤۘۘ۫ۙۡۤۛۦۘۖۛۖۘ۠۠ۚۛۡۖۛ۫ۡ";
                                            break;
                                        } else {
                                            str22 = "ۚۥۗ۫ۜ۟ۜ۟ۡۘۥۛۤۘۧۖۘ۟ۛۘ۠ۜۘۘۥۤۖۤ۫ۜۘۨۜۖۘ";
                                            break;
                                        }
                                    case 489674062:
                                        str21 = "ۜۢ۫ۤ۬۫ۜ۟ۜۘۙ۫ۥۘ۟ۦۙۥۙۡ۠ۢۧ۟۬ۖۚ۠ۘۜۚۗۤۦۘۖۦۥۘۖۜۜۦ۬۠ۨۗۨۘۘۦۦۘ";
                                        continue;
                                    case 864308656:
                                        str22 = "ۗ۬ۦ۬ۧۜۘۚۢۚۘ۟ۜۥۘۜۘۨۨۘ۬ۜۜۖۙۛۡۤۘ۟ۥۡۡ۟ۘ۟ۘۖۘ";
                                        break;
                                }
                            }
                            break;
                        case -151893439:
                            String str23 = "ۖ۫ۤۧ۟ۦۨۚۡۚۡۜۗۚۖۘۥۗۡۘۧۚۙ۠ۨ۟۟ۤۖۨۨۤ۫۟ۦۛ۠ۧ";
                            while (true) {
                                switch (str23.hashCode() ^ (-1505246863)) {
                                    case -1465197679:
                                        try {
                                            new AlertDialog.Builder(activityECt8jHZ4).setTitle(str2).setMessage(str17).setPositiveButton(str18, new r3()).setCancelable(false).show();
                                            break;
                                        } catch (Exception e3) {
                                            Log.e(str20, "显示消息弹窗失败", e3);
                                            return;
                                        }
                                    case -696734648:
                                        str23 = "ۥۜۧۘ۫ۖ۫ۤۜۤ۟۫ۜۘ۫ۨۥۖۘۦۢۘۘۗۙ۬ۥۖۨۘۨۛۢ۬ۗۖۨۜۘۘ۠۫ۛۦۜ۠ۨ۠ۢ۟ۤۨۘۧ۠ۦۘۙۙۨ";
                                        break;
                                    case -478315626:
                                        String str24 = "ۛ۬ۗۡۚۦۜ۟ۘۚۨ۬ۚۖۡۘ۠ۢۥۡۘۛۚۜۘۘۚۨۘۜۧۖۗۚۜۖۥ۬ۧۗۖۘۢۨ۬";
                                        while (true) {
                                            switch (str24.hashCode() ^ 1055203699) {
                                                case 224440277:
                                                    str23 = "ۜۨۧۘ۠ۚۚۚۜۘۘ۟۫ۚ۠ۥۦ۬۬۟ۗۜۖۘۥ۫ۛۘۚۘۘۛۚۦۡۡۘۘۛۘۛ";
                                                    continue;
                                                case 872251714:
                                                    str23 = "ۢۤۘۘۜۨۘۜۥۙۗۧۜۛۦۘۘۚۜۧۜ۠ۙۦۚۢۤۛۘۙ۫ۡۖ۬ۤۖۢۖۘۡ۟ۥۘۚۨۙ";
                                                    continue;
                                                case 1252842010:
                                                    if (!activityECt8jHZ4.isFinishing()) {
                                                        str24 = "ۖۧۘ۠ۤۡۡۤۡۥ۠ۧۡۙۢ۟ۙۜۙۧۡۛ۬ۖۜۥۙۘ۬ۜۘ۬ۛ۠ۤ۬ۘۘۢۥۘۘۙۙۥۘ۫ۙ۟ۛۗۤ۬ۚۘۜۦۦ";
                                                        break;
                                                    } else {
                                                        str24 = "ۗۢۥۘۨۡ۫ۚۘۘۘ۬ۢۖۘ۫۫ۘۘۡۥۚۡۨۜۚۤۘۘ۬ۖۗۚۘۗۦ۟ۜۡۨ۠ۢۗۤ۬ۧۨ۬ۗ۠ۙ۟ۤ۟ۡ۬ۖۘۢ";
                                                        break;
                                                    }
                                                case 1279484065:
                                                    str24 = "ۢۨۥۨ۬۠۠۠ۥۦۚۚ۬ۜۧۘۤۗ۟۫ۗۖۘۥۚۚۤۧ۫ۧۤۡۤ۟ۦۗۤۜۙۨۥۘۡ۠۫ۢۚۖۘۚۡۨۧۤۨۧۙۧ";
                                                    break;
                                            }
                                        }
                                        break;
                                    case 1365356568:
                                        break;
                                }
                            }
                            break;
                        case 72685413:
                            break;
                        case 1651428735:
                            str21 = "ۤۜۦۘۧ۫ۨۘۨۙۙۙۥۥۦۡۦۘۧۛۥۘۛۥۙۘۙ۟ۦۖۤۢۛۥ";
                            break;
                    }
                }
                break;
        }
    }
}
