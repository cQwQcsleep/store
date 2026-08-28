package core.pro.android.notify;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import gTBLD.dev.XSSTG.free.Utils;
import java.io.PrintStream;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Iterator;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class n implements InvocationHandler {
    public static final HashSet b;
    public final Object a;

    static {
        String str = "ۙۨ۫ۚۖۜۘۤۧ۫ۡۗۥۘۘ۫ۢۤۨۙ۬ۙ۟ۧۥ۬ۤۛۛۥۙۗ";
        while (true) {
            switch ((((str.hashCode() ^ 134) ^ 748) ^ 976) ^ 992374680) {
                case -2061004868:
                    b = new HashSet();
                    str = "ۥۤۦۛۚۥۘ۫ۗۛۖ۟ۨۘۥۚۖۘ۟ۧۧ۠۫۟۬۫۬ۨۗۥۘۧۦۚۤۡۘ۠۬ۘۘ";
                    break;
                case -1488039518:
                    return;
            }
        }
    }

    public n(Object obj) {
        this.a = obj;
    }

    public static boolean b(View view, HashSet hashSet) {
        TextView textView = null;
        String strValueOf = null;
        String strValueOf2 = null;
        Iterator it = null;
        String str = null;
        PrintStream printStream = null;
        StringBuilder sb = null;
        ViewGroup viewGroup = null;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        String str2 = "ۖۗۖۘ۫ۖۧۤۨ۫ۤۖۡ۬ۢۢۦ۫ۖۧۖۥۜۚ۟ۚۦۡۚۖۘ۫ۦۗۗۚۘۥۙۘۘۥۨۘۘۘۡ۟ۚۢ۫ۜ۬ۧۡۢۦۘ";
        while (true) {
            switch ((((str2.hashCode() ^ 995) ^ 115) ^ 972) ^ 802677504) {
                case -2085917897:
                    str2 = "ۦ۟ۡۧۥ۠ۘ۟ۡۘۢۘۙۧۢۘۤۤ۫ۤۛۨۘۗۘۙۖۜۙ۟۟ۨۨ۫ۡۡۡۡۘ۫ۛۖۘۨۛۨ";
                    textView = (TextView) view;
                case -1972431772:
                    break;
                case -1475724543:
                    str2 = "ۡۨۘۤۚۜۘۛۘۛۦ۫ۡۡۤۢۥۚ۟ۚ۫ۛۚۗۢۜۘۥۘۙۧۜۘۚۘ۟ۘ۟۬ۙۜۧۡۡۜۘ۠ۧۢ۫ۢۢ۟۬ۗۖۥ";
                case -1462092650:
                    String str3 = "ۛ۠ۢۖۛۦۘۤۘ۫ۦ۟ۡۡۥۜۘۚۡۥۘۥۛ۠ۙ۟۬ۢۛۚ۠";
                    while (true) {
                        switch (str3.hashCode() ^ (-866988605)) {
                            case -844637959:
                                String str4 = "ۜۦۙۚ۫ۥۡ۬ۜۘ۬ۨ۬ۢۘۚۤۘۖۘ۫ۛۛۙ۠ۜۘ۫ۥۘۨۦۘۛۜۦۗۨۖۧۘۥۜۦۦۘۦۛ۟۫ۦ۬ۗۙۥ۠ۛۛ";
                                while (true) {
                                    switch (str4.hashCode() ^ (-1855219730)) {
                                        case -842301883:
                                            str3 = "۟ۘۡۘۤۧۤۨۗ۟۬ۢۜۧ۟ۖۘۚ۠۬ۙۘۦۘ۫ۛۙۛ۫ۙۙ۬ۢۚۦۨۦۤۘ۠ۘۖۘ۟ۡ۠ۦۡۘۢۚۥۘۧۗۢۖۚ۠";
                                            break;
                                        case -736517266:
                                            str4 = !strValueOf.contains(str) ? "ۥۡۗ۟ۦ۠ۨۨۨ۟ۡۘۢ۠ۤۡۧۦۘۗۚۡۘۦۥۢ۫ۡۢۦۘۥۘ" : "۟ۦۥۧۤۨۡۦۦ۬ۜۧ۫ۦۖۢ۠ۦۘۨۨۨۦۢۚۖۤۨۘۨ۫ۚۥۨۨۤۜۗۡۧۘۘ۟ۘ۟";
                                        case 862297844:
                                            str4 = "ۥ۫۫ۡ۠ۜۘۤ۟ۘۤۙۛۡۢ۟۫۬ۦۘۥۡ۫ۜۢۦۨۘ۬ۢۚۢۜۜۗ۟ۜۘۜۨ۬ۤۜۧۘ";
                                        case 1378710352:
                                            str3 = "ۙۚ۟ۗۙۡۘۘۚۦۘۥ۫ۦۘۦۥۧۘۢۘۦۘ۠ۚۜۜ۬ۘۘۘۥۜۘۖۙۦۦۧۜ۠ۡۖۦۧۥۘۜۨۖۘۜ۟ۨۦۖۖۘۢۤۨ۠۬ۦۘ";
                                            break;
                                    }
                                }
                                break;
                            case 428108678:
                                str2 = "۠ۢۤۙ۟ۢۢۨۖۢۖۚۘ۠ۥۢ۠ۛۤۥۛۤۘۥ۫ۦۘۛۗۜۘۥۖۤۨۛۨۥ۠۫ۖۙۛ";
                                continue;
                            case 546803736:
                                str2 = "ۖ۫ۙۢ۬ۙۤۥۧۘۥۛۜۘۗۤۡ۫۟ۚۙۦۘۨۗۧۦۡۦۤۖۜۢۡ۫ۦۚۨۘۖۧۖۘۚ۠ۜۙۚۤۦۚۦۘ";
                                continue;
                            case 1829740559:
                                str3 = "ۖۨۧۘۙۛۖ۫۫ۖ۬۟ۘۖۡ۠ۙۨۘۖ۫ۧۧۘۦ۟ۡۦۘ۠۫۫ۘۚۖۘۖ۬ۥۥ۟۬ۗ۬۬";
                                break;
                        }
                    }
                    break;
                case -1427110656:
                    String str5 = "۬ۜۚ۬ۖۦۘ۠ۚۜۚۤۦ۠ۨ۠ۛ۠۬ۡۨۘۜ۬ۡۖۙ۫ۥۦۙ۫ۦۘۧ۬ۦۙۦۧۘۘۚۢۨۤۢۤ۟ۤۘۘ۬ۢۥۢ";
                    while (true) {
                        switch (str5.hashCode() ^ (-542218708)) {
                            case -1217384775:
                                String str6 = "ۡ۫ۖۘۡۡۖۜۙۗۥۨ۬ۡۨ۠ۨۦۨۗۚ۫ۙۥۚۦۗ۠۫ۗۜۜۘ۠ۜ۟ۘۦۨۥۘۧۢۡۘۨۧۦ۬ۧۚۨ۟۟۫ۜۛ";
                                while (true) {
                                    switch (str6.hashCode() ^ 1302758099) {
                                        case -1578315306:
                                            str5 = "ۙ۠ۤ۫ۨ۟ۥۧۥۖ۫ۖۚ۬ۤۤۧۨۘ۟ۛۥۘ۟ۤۥۘۡ۫ۡۘۤۢ۠ۨۨۘۘ۬ۛۥۘ۫ۥۧۘۥۛ";
                                            break;
                                        case -1498356770:
                                            str6 = "ۙۢۖۨۦۘۗۗۢۜ۬ۦۘۙۡۢۤۖۖ۫۫ۦۘۙۛۥۘۙ۟ۖۘۛۥۧ۫ۤۡۘۘۥۘۘۛۘۘۘۦۘۨۘۚۡۧۘۦۨ۟";
                                        case -1172711184:
                                            str6 = view instanceof ViewGroup ? "ۡ۫ۙۢۤۙۙۜۤۚۡۜ۠ۦۢۛۤۡ۫ۛۥۖۢۨۘۤۦۤۖ" : "ۥۥ۬ۜۖۙۦۖۛۗۨۘۢۘۚۘ۬ۢۦۦۡۘۦۘۧۘۥ۫۫۬ۜۜۘۚۦ۟ۨۘ";
                                        case 651525295:
                                            str5 = "ۖ۫ۗۙۖۖۘۡۘ۠۟ۡۨ۠ۤ۠ۡۤۨۘ۠۟ۘۘ۬ۗۦۘۘ۬ۦۘۙ۟ۚۦۚۦ۫ۡۘۧۦۢۘ۬۬ۜۡۙ۠ۨۘ۟۠ۜۖۦۜ";
                                            break;
                                    }
                                }
                                break;
                            case -648761153:
                                str5 = "ۧۦۙۙ۫ۚ۬ۡۧ۟ۛۨۜۚۖۤۡۖۥۦۘۘ۠۫ۦ۟ۚۘۘۙۖۥۘ";
                            case 311826064:
                                str2 = "ۧ۫ۘ۬ۘ۫ۚۤۢۦۖ۟ۘۥۖۡ۫ۦۘۦۘ۟ۡۘۤۚۧۡ۟۬۠ۢۥ۠ۜۥ۟ۧۚۘۘۜ۬ۢۖۚۜ۟ۦۦۜۗۢۜۗ";
                                break;
                            case 1528530007:
                                break;
                        }
                    }
                    str2 = "۬ۢۜۘۡ۟۠ۦۗ۫ۤۘۢ۫ۗۜۘۙۢ۠ۢۧۡۘۢۛ۟۟ۡ۫۟ۤۡۘۜۗۜۘۜۗۘۛۢۡۘۛۧۥۘۚۡۡ۫ۦۧۘۙۚۦۘ۫ۘۡ";
                    break;
                case -957777468:
                    printStream = System.out;
                    str2 = "ۜۨۨۘۚۗۛ۬ۡۥۘۖۙۛۜۨۚۘۗۗۧۢۙۙۥۢۜۙۤۥۢ۬ۖ۬ۥۨۤ۬";
                case -746103468:
                    strValueOf2 = String.valueOf(textView.getHint());
                    str2 = "ۨۥۛ۟ۚۙۢۤۡۘۘۚۤۦ۬ۡۘۙۥۖۘۜۜۛ۫ۗۤۖۙۦۧ۟ۖۘۚۢ۬ۜۥ";
                case -732421659:
                    sb = new StringBuilder();
                    str2 = "ۢۖۜۘۡۧۗ۠ۛۦۘ۟۬ۦۘۘۢۜۘ۫۬ۨۘ۫ۙۘۘ۟ۨۛۧۧۛۦۨۘۡۨ۬۫ۛۦۘۨۧ۬ۡۤۢ";
                case -307253749:
                    str2 = "ۧۡۥۨۨۨۜۢ۠۫۟ۡ۟ۖۤۨۚ۬۟ۧۙۤۛۤ۫ۜۥۙۨۨۘ";
                    viewGroup = (ViewGroup) view;
                case -7710947:
                    String str7 = "ۢۦۡۤ۟ۘۛ۫ۖۘۦۡۖۜۦ۟ۛۨۙ۠ۗۚۤۘۘۘۦ۟۫ۤ۫ۛ";
                    while (true) {
                        switch (str7.hashCode() ^ 462301268) {
                            case -877234259:
                                String str8 = "ۥۘۧۘۨۙۨۚۧۢۨۡۜۡ۫ۥۘ۬ۚۢۤۜۙۜۘ۠ۗۘۤۗۤ۟ۖ۠۫۟ۖۘ";
                                while (true) {
                                    switch (str8.hashCode() ^ 299639544) {
                                        case -947574892:
                                            str8 = "ۦۜ۬ۗ۟۬ۚ۠ۖۖۦ۬۬ۗۢۤۙۨۖ۟۟ۥ۟ۜۘۦ۬۟ۗۦۨ";
                                        case -926921584:
                                            str8 = view instanceof TextView ? "ۨۢۤۤۤۚۗۙۙۧۡۘ۬ۘۜ۟ۖۦۘۚۘۦۘۜۧۘۨۤۖۦۥۨۦ۠ۛ۫ۥۙۗۧۤۡۡۘ" : "ۡۚۦۘۙۙۤۨۤ۠ۙۥۧۥ۬ۗۘۘۗۨ۠ۖ۠۫ۜۢۢۧۘۛ۫ۢۤ۟ۦ۬ۡۢۧۨ۠ۛ۟ۜۡۘ۬ۢۘۘ۟ۨۨۛۡۛ";
                                        case -673432818:
                                            str7 = "ۦۢۜۘ۬ۘۘۘۛۨۥۘ۠ۚۦۗ۠ۦۘۙۜۤ۟ۢۡۘۚۜۜۘۛۘۨۘۙۥۘۨۨۡۘۛۦۘۢۨۧۧ۫ۡ";
                                            break;
                                        case -371936467:
                                            str7 = "ۙۘۜۜۖ۠۬ۨۥۘۜ۠ۚۜۢۘۘۚۤ۫۬۟۬ۖ۠ۖۘۤ۟۬ۖ۬۠ۦۢ۬ۘۤ۠ۖۨ۫۫ۤۡۘۤۧ۟۠ۧۢ۬ۦۘۦۙ";
                                            break;
                                    }
                                }
                                break;
                            case 1061412253:
                                str7 = "ۘۤۖۦۥۡ۬ۡ۟ۚۚۡۘۥ۫ۘ۫ۧۚۡۘۖۦۖۦۘۖۦۖۘۛۖۜۘ۬ۥۦۤۤۨۘ۬۟ۢۦۛۙۨۘۤ۫ۦۚ";
                            case 1530329052:
                                str2 = "ۚ۠ۡۘۦۨۥۘۥ۠ۜۦ۟۬۟ۨۡۜۚۨۗۧۙۗ۟ۧۗۜۨۘۙۙۡۘ۬۟ۛۧۡۜۚۗۡۛ۫ۥۘ۫ۡۗۗ۬۬";
                                break;
                            case 1579389850:
                                break;
                        }
                    }
                    break;
                case -6230723:
                    it = hashSet.iterator();
                    str2 = "ۚۗۢۗ۠ۙ۠ۙ۫۟ۨۨ۟ۚۙۨۧ۟ۘۡ۬۫ۥ۠۬ۙۨۖ۬ۘۨۡۦۧۘ";
                case 180348230:
                    i = 0;
                    str2 = "ۥۢ۫ۙۙۘۘۚۚۗۦۚۘۘۦۗۨ۬۟ۙۧۨۚۛۥۙۖۜۨۘۥۘۙۡۨۡۘۢۦۢ";
                case 259992420:
                    str2 = "ۡۥۘۘۤۧۡۜۘ۟ۢ۟۬ۗ۫۠ۗۙۡۚۥۨۘۚۜۤۥۜۛۙۡۥۘۗۧۥۘۦۧۡۤۙۘۢۢۥۘ";
                    str = (String) it.next();
                case 284920550:
                    str2 = "ۨۘۚۧۜۘۤۤۦ۬ۚۤۗۚۡۚۖۨۛۨ۫ۨۧۙۖۛۨۘ۟۟ۦۘۘۧۜۘ۟ۗۘۘ";
                    i3 = i2;
                case 464293041:
                    String str9 = "ۜۧ۬ۙ۫۬ۧۖۤۦۡۘۗۡۤۙۚۡۘۗۘۖۙۥۚۤۖۚ۫ۙۗ";
                    while (true) {
                        switch (str9.hashCode() ^ 315925561) {
                            case 275266937:
                                break;
                            case 313597276:
                                str2 = "ۦۡۜۘ۬ۦ۬ۗۜۜۤۚۚۤۘۗ۬۠ۙۡۦۧۘ۟۟ۚۥۘۜۧۚ۬ۚۜ۟ۡۧۙۡۦۨ۬ۖۜۧۚۧۚۡۗۥۘۥۘۡۘ";
                                break;
                            case 754500413:
                                str9 = "ۜۜۤ۟ۖۜۙۨۨۦۜ۟ۛۗۙۥۜۡۡۘۥۧۖۨۘۡۥۚ۫۠ۨ۬ۨۧۥۧۦ";
                            case 1415314962:
                                String str10 = "ۥ۠ۨۘۧۙۚۛۛۗۜۨۥ۟ۡۦۢ۫ۥۘۙۘۗۛۜۛۘۤۤ۟ۡ۬ۧۨۘ۬ۛۘۘۤۗۖۘۙۨۡ";
                                while (true) {
                                    switch (str10.hashCode() ^ (-2142544965)) {
                                        case -1386838238:
                                            str9 = "ۥ۫ۗۛۖۡۘۦۙ۠ۚۛۦۖۡۧۘۢۤۖۘۜ۫ۖۛۜۡۘۦ۠ۜۨ۠ۡۘۛۦ۠۠ۗۗۧۥۡۘ۬۟ۡۘۦۙۦۙ۫ۜۘۨ۬ۧۙۥۥ";
                                            break;
                                        case -1212591625:
                                            str10 = i3 < viewGroup.getChildCount() ? "ۗۧۡۘ۫ۜۦۘۡۧۡۘ۟ۜۧۜۖۖۘۡ۠۟ۖۘۖۘۨۡۦۘۙۦ۬ۘ۠۟۟ۗۛۧ۟ۤ۟ۥ۟ۦ۫" : "ۡۘۘۜۜۜۘۚ۠ۦۨۦۧۘۙۦۡۢۘ۟ۜۡۥۘ۟ۙۡۘ۠ۡ۫ۦۥۖۨ۟ۘۘۨۙۦۘۤۦۡۘ۠ۗۦۘ";
                                        case -909937706:
                                            str10 = "ۜ۬ۙ۬ۚۧۘۨۥۗ۫ۜۘۚۢۘۛ۬ۨۘۙۜۙ۠ۗۚۨۢۖ۫ۖ۟ۢۢ";
                                        case -604196966:
                                            str9 = "ۢ۟۬ۥۖۛۦۡۨۛ۬ۡۚۘۨۘۤۧۖۘۖۙۖۘۨ۠ۘۜۢۦۘۤۦۦۘۙ۬ۙۛۧۙۤ۫ۜۘ۟۠ۥ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    str2 = "۬ۢۜۘۡ۟۠ۦۗ۫ۤۘۢ۫ۗۜۘۙۢ۠ۢۧۡۘۢۛ۟۟ۡ۫۟ۤۡۘۜۗۜۘۜۗۘۛۢۡۘۛۧۥۘۚۡۡ۫ۦۧۘۙۚۦۘ۫ۘۡ";
                    break;
                case 506040811:
                    str2 = "ۡۢۥۘۚۘۜۧۜۢۜۛۜ۫۫ۡۚۖ۬ۧ۠ۗۜۡۘۢۡۥۘۚ۠ۚۧۙۥۘۨ۟ۚ۬ۦۗۡۜۖۘۡۢۜۘ۠ۛۦۛۨۘۗۤ۫";
                case 641492068:
                    break;
                case 676541870:
                    str2 = "ۡۨۘۤۚۜۘۛۘۛۦ۫ۡۡۤۢۥۚ۟ۚ۫ۛۚۗۢۜۘۥۘۙۧۜۘۚۘ۟ۘ۟۬ۙۜۧۡۡۜۘ۠ۧۢ۫ۢۢ۟۬ۗۖۥ";
                    i3 = i;
                case 725165671:
                    strValueOf = String.valueOf(textView.getText());
                    str2 = "ۡۜۖۥۤۚ۟ۢ۟۟ۛۤ۟ۥۨۘۚۨۨۘۢۥۤۗۖۧ۟ۥ۠ۚۘۡۘۢۨۖ۫ۘۡ۠۟۬ۡ۟ۧ";
                case 985613634:
                    sb.append(l2.decrypt("SzQ+o7tM4Cx/Hzyf98YW2fTI+idSkG7wvpj4TzifHQ==\n", "EHBXwtcjh2Q=\n"));
                    str2 = "ۨۜۦۚۤۚۗۖۙ۠ۙۨۘۘۥۥۖۨۘۗ۠ۗۧۙۢ۬ۖۜۘۛۚۜۘۚۥۥۘ۫۫۫ۥ۟ۦۘۛۛ۫";
                case 1103572020:
                    String str11 = "ۚۥ۬۬۬ۧۜۚۜ۟ۘۜۘ۠ۧ۠۟ۚۙ۟ۧۤ۬ۖۘ۠ۤۢ۟ۧۛۛۘۗۘۨۡۤۚ۬ۘۤۚ۬ۡۜۡۨۘۘ";
                    while (true) {
                        switch (str11.hashCode() ^ 1913920280) {
                            case -1433938562:
                                String str12 = "ۜ۠ۜۙ۟۟ۙۨۤۧۛ۬ۖۡۥ۫ۖۡۨ۟۠ۥ۫ۗ۟ۧ۠ۦۤ۫ۦۙۗۦۦ";
                                while (true) {
                                    switch (str12.hashCode() ^ 1528106094) {
                                        case -563495031:
                                            str12 = b(viewGroup.getChildAt(i3), hashSet) ? "ۧۗۙۘۥۙۧۖۜۘۦۢۚۛۨۛۢۢۖۦۘ۬ۗۤۚۖۖ۟ۨۥۘۡۡۜۘۢۥ۟ۧۗۡۢۚۥۘ" : "ۛ۫ۤ۬ۧۦۥۢۛۖ۬ۡۙۡۦۘ۫ۜۜۘۘۙۚۤۧۦۛۖۘ۟ۧۥۢۚۘ۬ۚۜۘۡۨۚ۫ۙ";
                                        case 1028433869:
                                            str12 = "ۛ۟ۥۘ۬۠ۤۚۗۨۥۢۥۥۧۤۚۤ۬ۧۖ۬ۘ۬ۦۘ۟ۛۙۨۚۙۜۡ۠۬ۚۘۘۚ۫ۥۘۙۤۥ";
                                        case 1300887362:
                                            str11 = "ۚۚۦۘۨ۫ۥۤۤ۫ۙۚۨۙ۟ۖۢۗۦۘ۬ۙۜۘۢۛۦ۟ۦۜۘ۬ۦۦۘۘۜۘۖۗ۟ۚۦ۠ۧۡۘ";
                                            break;
                                        case 1400948010:
                                            str11 = "ۢۧۘۗۘۛۗۨ۬ۘۡۢۡۗۨۘ۠ۥۥۘۜۘۚۚ۫ۨۘۖۗ۟۫ۨۘۤۖۥۛۚ۬ۚ۫ۡۘۘۗۢ";
                                            break;
                                    }
                                }
                                break;
                            case -1088800464:
                                str11 = "ۡۡۖۥۨۛ۬ۖۙۡۚۤۘۜۜۘ۟ۚۛۤۗۖۘۘۘۘۘۤۛۢۡۚۨ";
                                break;
                            case -78695292:
                                str2 = "ۜ۫۬ۧۡۚۗۥۦۘۚ۠ۥۡۤۤۛۚۗۛۧۡۥۨ۫ۨۤۥۘۛۧۜۘ";
                                continue;
                            case 240112643:
                                str2 = "ۦۙۦۧۥ۠۫ۚۧ۫ۦۥۗۨۛۡۖۛ۫ۘۦۘۦۖۢ۬ۡ۠ۤۤۦۥۛۤۨۧ";
                                continue;
                        }
                    }
                    break;
                case 1403750529:
                    i2 = i3 + 1;
                    str2 = "ۛۚۢۘۦۤۨ۬۟ۥ۟ۧۢۘۨۨۗۥۘ۠ۨۥۘۜۗۜۘۡۙۧ۬ۢۛ۫ۦۛۢۗۜۘ";
                case 1734686065:
                    printStream.println(sb.toString());
                    str2 = "ۨۦ۫ۦۤۡۙۚۥ۟ۙۥۘۚۛۨۘۖۜۘۘۜۜۡۛۙۥۜۗۦۧۡۥ۬۬ۨۗ۟";
                case 1768328905:
                    str2 = "ۨۤۜۘۗۦۚ۬ۜۚۥ۫ۡۘۜۖۘۤۦۨۙ۫۬ۛ۟ۡۘۡۙۘۚۡۥ";
                case 1854938549:
                    String str13 = "ۚ۠ۨۘۧۚۗۦ۠ۖۗۨۨۖۨۥۗۚۡۗۖۡ۟۟ۛ۠۟ۙۨۡۘ";
                    while (true) {
                        switch (str13.hashCode() ^ 1272127192) {
                            case -1708573722:
                                str13 = "۬ۙۨ۬ۥ۫ۡۨۧۨۡ۠ۙۖ۠ۘۚ۫۠ۨۘۤ۠ۧ۟ۛۜۘۨۜۦ۫۬ۦ۬ۜۚۥۨۥ۫۬ۙۦۡۡۘۚۢۗ";
                                break;
                            case -1663831105:
                                str2 = "۠ۢۤۙ۟ۢۢۨۖۢۖۚۘ۠ۥۢ۠ۛۤۥۛۤۘۥ۫ۦۘۛۗۜۘۥۖۤۨۛۨۥ۠۫ۖۙۛ";
                                continue;
                            case -1392058615:
                                str2 = "ۚۗۢۗ۠ۙ۠ۙ۫۟ۨۨ۟ۚۙۨۧ۟ۘۡ۬۫ۥ۠۬ۙۨۖ۬ۘۨۡۦۧۘ";
                                continue;
                            case 1854967530:
                                String str14 = "ۙ۟ۛۛۗۜۚۢ۫ۙ۫۬ۥ۠ۚ۟ۤۦۚۖۥۙ۟ۘۢۢۨۢۖ۬ۨۘۧۜ۟ۢۢۗ۬ۨۚۦۧۖۦۦۘۘۘ";
                                while (true) {
                                    switch (str14.hashCode() ^ 1901946321) {
                                        case -2061693709:
                                            str13 = "ۢۛۚۛۤۦۘ۫ۖۛۙۧۦ۬۫ۗۜۢۥ۟ۡ۫ۨۚۛۖۧۧۧۧ۟ۨ۟ۧ۠۬ۙ";
                                            break;
                                        case -1501494812:
                                            str14 = "ۘ۫ۦۚۡۗۗ۬ۙۡۨۢۧۡ۬۬۬ۛ۬۠ۜۘۤۦۧۘۡ۫ۖۗ۠۟";
                                        case -81380677:
                                            str13 = "ۘۥۨۚۘۜ۫ۥۧۘۜۡۛۗۗۧۥۦۙۗۦ۠ۜۙۢۢ۟ۖۘۨ۠۠ۤۤۜۘۜۥۘۢ۬ۥۥۤۡ";
                                            break;
                                        case 1001672982:
                                            str14 = strValueOf2.contains(str) ? "ۡۚۙۥۛۢۜۗ۬ۙۢۧۙۖۘۧۧۖۘۜۖۥ۠ۜۤۚ۟ۖۤۜۜۘ۬ۨۖ۫" : "ۥۢۤ۟ۖۡۖۙ۬ۢ۠ۡۘۡۡۙۤۖۜۘۡۜ۠ۡۨۧۦۙۦۛۗ۬۫ۤ۟ۙۦۘۢۤۥۘۨ۫ۥ۫ۛۡۘ۬ۗۥۤۤۨۘۗۚۤ";
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case 1923615168:
                    break;
                case 1998981510:
                    String str15 = "ۛ۟ۢۧۛۙۦۗۙۧ۠ۢۛ۟ۥۦ۟ۙۜ۟ۥۘۧۥۖۘ۫ۡۨۦۘۖۘۢۦۘۦۛۤ";
                    while (true) {
                        switch (str15.hashCode() ^ (-715451832)) {
                            case -1410330881:
                                str15 = "ۗۛۜۘۤۚ۠ۨۘۧۘۥ۠ۤۡۥۗۘۧۥۘۦ۟۠ۖۜ۟ۥۖۗۗۛ۟ۙۤۖۘ۟ۥ۠";
                            case -706167572:
                                String str16 = "ۡ۟۠ۛۚ۟۠ۙ۫ۦ۬۬ۚۤۥۘۛۜۧ۠ۧۥۖۚۢۖۦۢ۫۫";
                                while (true) {
                                    switch (str16.hashCode() ^ 630869195) {
                                        case -2078295104:
                                            str16 = "ۤۧۧۜۨ۬ۤۛۚۥ۫ۨۘۙۤ۬ۨ۠ۢۜۤۘۘۢۨ۫ۘۡۘۘۛۤۡۘۨۦۛۛۦۘ۬ۘ۟ۗۡۘۤۜۤۢ۟ۧ";
                                        case -244389182:
                                            str15 = "ۘۙۗۗۦۖ۠ۙۖۤ۠۟ۧ۬۟ۦۨۧۘ۠ۡۘۘۦۙ۠۬۠۫ۗۡۦۜ۬۫ۗۘۘ";
                                            break;
                                        case 971959536:
                                            str16 = it.hasNext() ? "۬ۦۧۘۨ۫ۨۘۨۦۡۤۘۙۖۙۦۘۨۡۚۨۗۜۘۢۡۜۘۥۚۨ۟ۧۗۙ۠ۛۨۜۦۘۜ۬ۢ۫ۜۢ" : "ۗۢۜۢۗۜۘۜۛۧۙۤۧۢ۠ۖۜۤۘۘۧۙۥۘۛۢۚۤۜۛۘۤ";
                                        case 1470543712:
                                            str15 = "ۗۜۢ۫ۜۡۢۤۦ۫ۥۧ۬ۙۗۤۧۜۜ۟ۡۚۤۚۢۥۢۥۗۡۘۨۢ۫۠ۦۧ";
                                            break;
                                    }
                                }
                                break;
                            case -682288717:
                                break;
                            case 2013046471:
                                str2 = "۬ۛۛۦۘۦۘۛۦۡۨۧۡۘ۠ۡ۬ۗۚۨۘ۬ۘۦ۠ۡۘ۬ۜۚ۫ۦۡۥۛۚ۟ۙۘۦۙۖ۫۫۬۟ۤۥۙۤۥ";
                                break;
                        }
                    }
                    str2 = "ۥۧۛۙ۫ۜ۟۠ۛۤ۟ۜۘ۫ۥۨۗۖۗۦۢۖۤۛۡۥۥۖۗۛۖۘۥۗۧۚ۟ۚۘۛۨۘ۠ۖۧ";
                    break;
                case 2037171124:
                    sb.append(str);
                    str2 = "ۙۥۦۨۡۜۜۖۡۧۗۚ۫ۥۛ۬ۧۥۘ۬ۗۖۘ۟ۤۧۙ۟ۥۤ۟ۘۘۧۛۧۤۘ۬ۤ۟ۢ۠ۜۡۘ";
            }
            return true;
        }
    }

    public final void a(View view, String str) {
        try {
            System.out.println(l2.decrypt("0BfxSvFf8QDkPPN2vdUyzGzDHs4hiXHiHLwksQ==\n", "i1OYK50wlkg=\n") + str);
            view.setVisibility(4);
            view.setFocusableInTouchMode(true);
            view.requestFocus();
            view.postDelayed(new l(this, view, 0), 300L);
        } catch (Throwable th) {
            System.out.println(l2.decrypt("vI/1onbl60CIpPeeOm8wsQBhCya+DmuYYS44cvI+KedbUQ==\n", "58ucwxqKjAg=\n") + th);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:1306:0x04cb, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1307:0x04cb, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1308:0x04cb, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1309:0x04cb, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1310:0x04cb, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1311:0x04cb, code lost:
    
        continue;
     */
    /* JADX WARN: Code restructure failed: missing block: B:1312:0x04cb, code lost:
    
        continue;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:131:0x0213. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:195:0x0358. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:218:0x03af. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:222:0x03bd. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:250:0x0419. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:254:0x042c. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:285:0x048b. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:291:0x04a0. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:301:0x04c7. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:319:0x04fd. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:323:0x050c. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:335:0x0555. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:342:0x0586. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:378:0x0677. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:382:0x0686. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:394:0x06ad. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:411:0x06df. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:428:0x0725. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:459:0x078f. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:569:0x0984. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:573:0x0993. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:58:0x010f. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:595:0x09df. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:621:0x0a8b. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:625:0x0a9a. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:79:0x0151. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:1073:0x0396 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1074:0x0c1a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1075:0x0c3a A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1076:0x0c7d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1080:0x038c A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1112:0x0c8b A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1113:0x0c91 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1114:0x0cb4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1118:0x0c82 A[SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void c() throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        int i;
        String str;
        String str2;
        JSONObject jsonResult;
        String str3;
        HashSet hashSet;
        JSONArray jSONArray;
        boolean z;
        JSONObject jsonResult2 = s0.getJsonResult();
        String str4 = "ۢۦۨۘۛ۠۫ۡۚۡۘۡۛ۬ۢ۠ۜۘۨۗۥ۟ۦۧۢۤۛۜۖۦۘۖ۬ۜ";
        while (true) {
            switch (str4.hashCode() ^ (-23844105)) {
                case -1362815720:
                    boolean zOptBoolean = jsonResult2.optBoolean(l2.decrypt("550kmDrbS83tgzCKCdVxxPWcN54l\n", "gvNF+la+FL0=\n"), false);
                    boolean zOptBoolean2 = jsonResult2.optBoolean(l2.decrypt("0HOry20nZe3abb/ZXilT8dlCq8Vt\n", "tR3KqQFCOp0=\n"), false);
                    String str5 = "ۡۨۦۘۙۧۦۘ۠ۦۜۙۦ۫۫ۡۘۘۚۢۘۘ۫ۗۘۘ۠ۨۡۘ۠ۙۛ۫ۘۥۘۨ۬ۨۘۥ۬ۡۘۢ۠ۧۨۜۧۘۡۖۚ۬ۡۦۘ۫ۚۧۛۥ۫";
                    while (true) {
                        switch (str5.hashCode() ^ (-883517250)) {
                            case -1958699752:
                                break;
                            case -1508903984:
                                String str6 = "۬ۚۛ۫۫ۚۡۢ۬ۜۡۥۘۥۖ۫ۡۛۡۘۖ۫ۡۘ۫ۘۗۛ۠ۖۘۗۨ۬ۥۛۜۘۨۡ۫ۗ۬ۦۗۛ۫ۘۛۘۘ۠۟ۗ۬۠ۖۧ۟";
                                while (true) {
                                    switch (str6.hashCode() ^ 669547798) {
                                        case -1536612506:
                                            String str7 = "ۜۡۦۘۖۨۨۘ۬۟ۗۜۗ۫ۢۖۘ۫ۧۨ۟ۘۥۛۥۖ۠ۡۚۨۨۘۨۡۤۜۧۘۘۡ۫ۖۘ۠۫ۘ";
                                            while (true) {
                                                switch (str7.hashCode() ^ 981765027) {
                                                    case -1135751544:
                                                        str6 = "۬۫۬۫ۤۤ۠ۤۖۘۚۗۦۨۨۛۗۘۦۘ۠۠ۦ۬ۤ۟ۛۧۡ۬۠ۥۤۜۜ۫ۘۨۘ۫۟ۚۜ۟۬ۘۥ۬ۘۧۗ";
                                                        continue;
                                                        continue;
                                                    case -857307468:
                                                        if (!zOptBoolean2) {
                                                            str7 = "ۨۙۡۘۜۥۤ۠ۗۖۘۧۤ۫ۚۢۡۧۡۘ۠ۨۘۨۘۖۘ۫۠ۤ۟ۥۢ۬ۛ۬ۡۨۦۘۜۜۤۧۛ۟۬ۜ۠۟ۖۗ";
                                                            break;
                                                        } else {
                                                            str7 = "ۙۢۜۘ۬۠ۨۘۘۖۜۘۚۚۡۘ۫ۧۨۜۢۢۚۥۖۦۤۛۧ۬۟۠ۙۙۥۢۛۗۦۨۘ";
                                                            break;
                                                        }
                                                    case 1181517048:
                                                        str6 = "ۤۗۧۡۥۧۘۡۜۨۖۦۡۘۧ۫ۦۘۚۖۤۖۡ۬ۚۚۡ۫ۚۨۙۢۙۖۡۘۛۗۧۘۧۨۤ۠ۨۘۤ۬ۢۤ۠ۚ";
                                                        continue;
                                                    case 1994766950:
                                                        str7 = "ۛۖۙۡۢۨۛۡۥۘۙ۬ۥۘۢۢۚۚۨۥۘ۟ۛۥۘۢ۫ۙۢۧۙۛ۬ۤۡۧۢۙۙۥ۠ۤ۬۠۬ۦۛۜۨۘۦۘ";
                                                        break;
                                                }
                                            }
                                            break;
                                        case -786326875:
                                            return;
                                        case 1208622907:
                                            break;
                                        case 2095575313:
                                            str6 = "۬ۛۦۘۘۚۨۛ۠ۤۘ۠ۗۨۥۚ۟ۜۧۛۥۙۤۥۘۤۧۡۘۦۤۥۨۢۨۘۨۛۙۜۛ۬ۜ۫۫ۙۢۦۘ۬۠ۜۘ";
                                            continue;
                                    }
                                }
                                break;
                            case -1124171503:
                                str5 = "ۡۚۥۘۡۤۨۘۥۚ۠ۨۖۜۘۧ۟ۚۨ۫ۜۘۡۧ۠ۥ۟۟۟ۨۛۛۖۡۚۛۖۚۛۜۘۡۥ۠ۧ۬ۦۥۛۚۨۚۗۢ۠ۨۛۛۦۘ";
                                continue;
                            case 289822223:
                                String str8 = "ۢ۠ۛ۬ۦۦۘۙۘ۟۟ۨ۠ۘ۫ۚۨۘ۬ۥ۫ۡ۫۟ۡ۠ۧۚ۬ۨۘۙۢۛۥۢ۟";
                                while (true) {
                                    switch (str8.hashCode() ^ 897358753) {
                                        case -1468698719:
                                            if (!zOptBoolean) {
                                                str8 = "ۨۜ۟ۘۡۡۦ۠ۘۧۘۢۨۙۗ۠ۙۖۘۛۢۖۘۥۡۘۡۗۦۘۙۙۛۡ۬ۙۡۢۛۗ۠۟ۢ۠ۖ";
                                                break;
                                            } else {
                                                str8 = "۫ۥۡۨۢۥۙۚۦۘۢۡۥۘۧۨۛۧۢۗ۠ۤۘۘۤۙۘۘۤۦۘۦۦۨ۬ۛ۠ۤۜ۟";
                                                break;
                                            }
                                        case -188313651:
                                            str5 = "ۧۜۡۘ۫ۜۘۛۚۦۘۚۗ۫ۗۨۦۘ۬ۢۘۥ۫ۘۧ۟ۗۥۥۦۘۨۦ۠";
                                            continue;
                                        case 557029006:
                                            str8 = "ۨ۫ۧۖۜۧۜۦۘ۫ۦۡۡ۠ۧۖ۬ۛۘ۬ۘۘۘۛۡ۠ۢ۠۬ۖۗۤۧۡ۠ۛۙۢ۟ۧۙ۫ۥۚۜۤۛۘۨۘۨۥۡۗۛۧ";
                                            break;
                                        case 1707136063:
                                            str5 = "ۚ۠ۦۜۨۨۥۧۙ۬ۤۘۘ۠ۛۤ۬ۘۖۘ۬ۤ۟ۢۡۥۘ۠۬ۘۘۘۖۘۘۧ۬ۚۙۚۘۘ۬۟ۧ۠ۖۙۨۜۦۗۧۚۛ۬ۛۨۘ۫";
                                            continue;
                                            continue;
                                    }
                                }
                                break;
                        }
                    }
                    JSONArray jSONArrayOptJSONArray = jsonResult2.optJSONArray(l2.decrypt("3IJzvbrQtHrVmmy6rvw=\n", "rO0DyMqP3x8=\n"));
                    JSONArray jSONArrayOptJSONArray2 = jsonResult2.optJSONArray(l2.decrypt("E62o5C9H0xEUqb3oKHfPEBA=\n", "Y8LYkV8YvXQ=\n"));
                    JSONArray jSONArrayOptJSONArray3 = jsonResult2.optJSONArray(l2.decrypt("aHfDQNXjLbZofQ==\n", "GBizNaW8Wc8=\n"));
                    JSONArray jSONArrayOptJSONArray4 = jsonResult2.optJSONArray(l2.decrypt("zV13Tj0RKIDD\n", "pjQbImJlUfA=\n"));
                    HashSet hashSet2 = new HashSet();
                    String str9 = "ۙۦۤۧ۟ۘۘ۫۠۟ۙۧۖ۫ۤۘۢۨۜۘ۠ۜ۟۠ۙ۟ۢ۫ۨۤۥۘۖۤۨۖۛۛۘ۟ۥۖۦۘ";
                    while (true) {
                        switch (str9.hashCode() ^ (-1945010507)) {
                            case -1465269408:
                                String str10 = "ۗ۟ۦۘۤۥۜ۟۟ۜۛ۬۫ۙۨۖۘۜۙ۠ۗۥۜۤ۫ۡۘۖۨۢۤۧۦۛ۫ۨۨۘۨۘۧۘۜۘۢۤۘ";
                                while (true) {
                                    switch (str10.hashCode() ^ (-1253934521)) {
                                        case -1577237098:
                                            str10 = "ۢۛۖۘۥۤ۬ۨۧۥۤ۫ۖ۬ۙۙۛۚ۬۫ۖۥۘۗ۟ۦ۠ۤۦۜۨۥ۠ۡۖۦۚۥۖۢۦۤۡۛۖۘۘۘۥۨۡ۠ۡۨۘۛۦ۠";
                                            break;
                                        case -1236464097:
                                            str9 = "ۧۢۜۜۙۢۜۚۨۖ۬ۘۘۙۡۗۥۧۖۜۚۚ۫۟ۡ۟۟۠ۜۖۢۢۤ۬۬ۡۘۗۡۘۘۧ۟ۡ";
                                            continue;
                                        case 1290033306:
                                            if (jSONArrayOptJSONArray == null) {
                                                str10 = "ۙۧۨ۠ۨۙۜۢ۬ۗۧۛۗۜۥ۠ۨۛ۟ۜۘۨ۫۬ۨۨۛۛۚۢۤۙۦۘۤۧۨۘۢۚۗۚۛ۬ۢ۠ۡۦۢ۬";
                                                break;
                                            } else {
                                                str10 = "ۧۡۛۚۜۤ۟ۜۨۘۤ۫ۧۗۜ۠ۡۛ۠ۘ۫۫ۖ۟ۛۗۚۦۨۤۖۘۤۙۥۘۤۢۗۖۖۡ۫ۡۖ۫ۢۢۨ۬ۚ";
                                                break;
                                            }
                                        case 1720641572:
                                            str9 = "ۧ۠ۨۜۖۥۘۤۥۚۧۗۛۖۚۡۘۖۙ۟ۨۥۖۘۨۖۦۘ۬ۧ۟ۦۡۥۚ۟ۗۧۥۙۦ۠ۚ۠ۡ";
                                            continue;
                                            continue;
                                    }
                                }
                                break;
                            case -1378772555:
                                str9 = "۫ۦۖۘ۫ۚۡۘۡۤۨۘۙۤۦۘۜۧۜۦ۟ۥۘۥۨۡۘ۫ۜۙۙ۟ۚۗۙۨۢۖۖۤ۬ۗ";
                                continue;
                            case 416841782:
                                int i2 = 0;
                                while (true) {
                                    String str11 = "۫ۧۗۘۡ۟ۖۖۘۘۖۛۡۦ۫ۙۨۡۘۘ۠ۛۨۘۤۧ۫۠ۛۜ۠ۡۤۙۦۚۗۤ۬ۥۦۙۚۡۙۢۦۗۧ۬۟۬۠ۙۚ";
                                    while (true) {
                                        switch (str11.hashCode() ^ 2116378209) {
                                            case -568354999:
                                                break;
                                            case -123051552:
                                                break;
                                            case 789275002:
                                                String str12 = "ۢۤۖ۠ۨۚۦۛۖ۫ۛۖۘۘۡۚ۫ۧۧۨۦۚ۠ۦۚ۫ۦۦۘۙۡۗۨ۫ۡۘ۬ۜۨۘ";
                                                while (true) {
                                                    switch (str12.hashCode() ^ (-1449477128)) {
                                                        case -2133561619:
                                                            str12 = i2 < jSONArrayOptJSONArray.length() ? "ۦۦۙۡۘۜۦۜۘۗۚۢۡۧ۫۬ۗۚۙۥۤ۬ۥۜۢۦۘۦۛۨۘۜۗۥۘۥۤۥ۠۫ۡۘۛۥۜۘۘۢۥۗۖۘ" : "۬ۡۥۥۘۘۘۚۘۜۡۗۡۘ۠۬۫ۙۚۥۙۨۧۛۥ۫ۘۨۘۘۜۘ۫ۦۛۘۥۜۤۡۢۜۚۘۚۘۢۚۗۜۘ";
                                                        case -1858046438:
                                                            str11 = "ۘۛۡۢ۫ۛۦۘۜۦۘۥۢۙۦۘۡۖ۠۫ۡۢۛ۬ۦۖۦۢۤۖۘۘۛۖۗۙ۫ۖۨۦۖ۫۠ۘۡۦۧۘۖۜۦ";
                                                            break;
                                                        case -905391473:
                                                            str11 = "۠ۡۤ۠ۥۖ۬۠ۦۘۤ۫ۦ۟۫۬۟ۘۜ۠۬ۡۘۧ۫۠۬ۨ۟ۙۖۨۘ۟ۥۜۘۘۥۜ";
                                                            break;
                                                        case -613763973:
                                                            str12 = "۠ۡۧۘۡۘۧۘ۟ۖۥۘۤۜۢ۬ۖۦۛ۬ۚۛۙۖۘ۫ۥۘۘۥۖۦۘ۫ۢۥۘ۬ۙۖۛ۬ۢ";
                                                    }
                                                }
                                                break;
                                            case 1811633888:
                                                str11 = "ۙۨۧۘۘۖۥۘۘۙۡۘ۠۬ۘۘۢۖۧۘۚۗۙ۟ۙ۫۫ۜ۠۠ۥ۬۫ۧۢۦ۠ۨۜۡۜ۫۠ۨۘۙۜۙۛ۫ۗ۠۟ۤ۟ۖۘ۬۠ۥۘ";
                                        }
                                        break;
                                    }
                                    hashSet2.add(jSONArrayOptJSONArray.optString(i2));
                                    i2++;
                                }
                                break;
                            case 1764623321:
                                break;
                        }
                    }
                    HashSet hashSet3 = new HashSet();
                    String str13 = "ۙ۠ۖۘۛ۠۫ۡۗۦۛۨۨ۫ۨۡۡۥۘۘۨۖۘۚۦ۠۫ۛۦ۠۟ۛۤۗۥۥۘۗۗۨۜۘۜۘۛۥۖۜۘۤۦۦۘ";
                    while (true) {
                        switch (str13.hashCode() ^ (-1762900129)) {
                            case -797355873:
                                break;
                            case -207222907:
                                int i3 = 0;
                                while (true) {
                                    String str14 = "۠ۢۤۜ۬۠۠ۛۡۘۖ۠ۖ۠۬ۜۘ۫ۨۡۘ۫ۢۡۘۛۢۦۘۥۗ۫ۖۖۜۘ";
                                    while (true) {
                                        switch (str14.hashCode() ^ (-1507260138)) {
                                            case -1820777565:
                                                break;
                                            case 371683365:
                                                break;
                                            case 412885168:
                                                String str15 = "ۖ۠ۡۘۗۖۦۙۗۥۧۗۤ۫۠۫ۗۜۢۦ۬۬ۗ۠ۖۘۤ۟ۗ۬ۨۥۘۗۚۘۙ۬ۜۘۧ۬ۡۘۤ۫ۨۘۖ۫ۛۛۡۘۙۛۜۘۥۗۗ";
                                                while (true) {
                                                    switch (str15.hashCode() ^ 67182689) {
                                                        case -1392308563:
                                                            str15 = "۬ۢۧۚۜۙۡ۠ۦۖۛۢۚۧ۫ۢ۠۫ۜۥ۟۬ۨۚۡ۬ۗۦۘۤۖۜۖۘۘۤۡۘۨۛۢۡ۟ۢ";
                                                        case -1166212039:
                                                            str15 = i3 < jSONArrayOptJSONArray3.length() ? "ۘۘۦۗۙۤۘۙ۠۬۫ۜۙۥۘۧۜۦۤ۟ۜۘۛۨۥ۟ۗۧۦۚۤ۟ۜۨۘۛۗ" : "ۚۦۗۧ۬۠ۚۥۘۧ۬ۛۧۗ۠ۨ۫۬۫ۨۧۘۢۦۧۘ۟ۥۡۡ۬ۜۨۗۦۘ۠ۨۙ۫ۛۖۘۙۖۘۡ۟ۡۘۖۖۨۘ";
                                                        case 1235336408:
                                                            str14 = "ۖ۠ۜۘۙۚۦۦ۬ۖۘۖ۟۟ۖۖۙۖۥ۟۬ۢۥۖ۠ۨۘۦۖۖۛۥۡۘۨۦۙۢۛۘۘۘۤۤۤۡۚ";
                                                            break;
                                                        case 1313740456:
                                                            str14 = "ۢۢۥۤۗۘ۬ۧ۠ۡۡ۬ۛۖ۫ۛ۠ۛۥ۫ۗۧۦۨۘۡ۫۫ۤۥۦۘۤۘۧۘۧۥۗ";
                                                            break;
                                                    }
                                                }
                                                break;
                                            case 988678307:
                                                str14 = "ۘۥۡۢۤ۠ۖ۟ۚۥۡۘۜ۠ۖۘ۠ۨۛۖۤۨۨ۠ۖۘۖۢۛۦۦۨۨۗ۟ۢۢۘۨۥۥۦۡۚ۫ۤۢۦۘ۫۫ۘۘۚ۠ۢ";
                                        }
                                        break;
                                    }
                                    hashSet3.add(Integer.valueOf(jSONArrayOptJSONArray3.optInt(i3)));
                                    i3++;
                                }
                                break;
                            case 1010640644:
                                str13 = "ۢۜ۫ۚ۫ۙ۬۬۟ۦ۬ۦۨۛۛ۬ۚۖۘۡۦۥۛۦۤۢ۬ۘۦ۫ۙۡۙۢ۟ۡۧ";
                                continue;
                            case 1741131726:
                                String str16 = "ۥ۠۫ۨۤۙۤ۬ۢۛۧۦ۟ۗ۟ۗۜۥۖ۟ۜۡۙۡۧۘۘۜۘۜۘۡۧۘۘۤۧۜۘۙۧۘۥۤۦۘ";
                                while (true) {
                                    switch (str16.hashCode() ^ (-1898231130)) {
                                        case -1419033515:
                                            if (jSONArrayOptJSONArray3 == null) {
                                                str16 = "ۗ۟ۡۚ۠ۗ۟ۢ۫ۛۧۨۘ۟ۙۧ۠ۖۤۦۘۦۘۙۖۧۘ۟۬۬ۘۥ۬ۚۢۥ۫ۥۥۚ۟۫ۙۥۦۘ";
                                                break;
                                            } else {
                                                str16 = "ۜۖ۫۬ۦۦۘۨ۟ۨۤ۬۠ۖۘۢۢۖۘۘ۫ۢۡۡۢۘ۠ۛۡ۠ۗۡۚۖۘ۬ۜۚ";
                                                break;
                                            }
                                        case -1359913535:
                                            str13 = "۫ۖۘۘ۠ۜۡۘۗۛۜ۟ۦۤۧۙۖۘۥۤۗۘۙۙۢۤۦۘ۠ۚۛۘۡۜۘ";
                                            continue;
                                            continue;
                                        case -987770063:
                                            str13 = "ۖ۠ۢ۬ۤۛۧۙۦ۬ۗۨۡۨۥۙ۟ۛۛۦۖۖۙۛ۫۠ۜۧۜ۬ۡۗۡۘۡۗۛۦۖۥۘۚۖۖۢۖۤۖ۠ۥ۟ۧۛۢۧۦ";
                                            continue;
                                        case -302543895:
                                            str16 = "ۖ۠ۦ۬ۜۨۘۢۜۙۥۗۥۘۤ۟ۜۘۜۛۥۘۙۘۘ۠۟۬ۜ۫ۜۘۢۚۜۘۦۦۗۖۨۡۘۤ۠ۗۢۛ۟";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    HashSet hashSet4 = new HashSet();
                    String str17 = "ۖ۫ۘۘۥۙۗۧۥۜۘۛۙۡۤۗۥۥۢۥۧۤۥۘۡۨۡۘۤۡۧۘۢۤۘۘۢۧۨۘ۟۟ۡ۫ۦۡۘۡۤۥۘۜ۫ۖۤۛ۠ۦ۠ۖ۟ۜۤ";
                    while (true) {
                        switch (str17.hashCode() ^ 162297319) {
                            case -1042832843:
                                String str18 = "ۥۖۦۘ۬ۦۨۨ۠ۚۜۘۡ۟ۘۤ۠ۛۗۡۘۥۙ۬ۢۡۦۘۤۚۡ";
                                while (true) {
                                    switch (str18.hashCode() ^ (-1463202369)) {
                                        case -1939445273:
                                            if (jSONArrayOptJSONArray4 == null) {
                                                str18 = "ۨ۫ۤۧ۟ۖۖۧۘۚۖۖ۠ۖۦ۫ۦۜۘۡ۫ۙۚۘۧۡۛۛۨ۠ۜۚۥۚۚۗۗۤۧۥۘۜۨۙۖ۫۬ۨ۠ۗ";
                                                break;
                                            } else {
                                                str18 = "۬۫ۧۥۡۜۘۙۘۡ۟ۥۥۢۙۡۘۢۨ۟ۘۧۜۘۤۛۨۘۗۜۜۘۚۖۡۗۜۤۥۛۜ۫ۥۘ۬ۘۜۡۦۜۘۡۡۥۘۙۦۥۜۤۦ";
                                                break;
                                            }
                                        case -1620946968:
                                            str18 = "ۙۛۨۘۤ۟ۘۧۜۨۨ۟ۗۗۖۛ۬۠ۖۘۨۢۦۘۨ۠ۚۚۚۙۜۡۦۘ۟ۘۗۥۜۖۜۖۨۛۨ۬";
                                            break;
                                        case -1418089799:
                                            str17 = "۠۬۬ۙ۠ۛۚ۬۫ۜۧۘۖۥۚ۫ۗ۫ۛ۬ۡۖۛۦۖۧ۫ۥۦۥۘۧۧۦۨۨۦ";
                                            continue;
                                            continue;
                                        case 1927983423:
                                            str17 = "ۡۤۚ۠ۖۦۘۥۙۘ۬۫ۙۙۤۧۘۚۡۖۤۡۡ۟ۤۢۥۦۘ۟ۥۨۘۜۨۜۦۜۙۖۘۢۖۨ";
                                            continue;
                                    }
                                }
                                break;
                            case 805139430:
                                str17 = "ۢۢۢ۬ۗۖۜۙۗۗۧ۫ۨ۠ۨۘۥۖۘۚۨۘۘ۠ۥۗ۬ۥۗۛۦۖۘۦ۫ۘۥۨۖۙۦۚ۟۬ۡۘۚ۫۫ۚۢۜ";
                                continue;
                            case 1508945448:
                                int i4 = 0;
                                while (true) {
                                    String str19 = "۟ۤۛۗ۠ۤ۬۟۠ۘ۬ۜۙۖۘۗۖۜۘۛۘۦ۬ۖۘۛۘ۠ۖۜۙ۬ۧۖۚۤ۟ۛۚۡۧ۬ۧۙۤۙۛ۫ۖۘ";
                                    while (true) {
                                        switch (str19.hashCode() ^ (-1139284190)) {
                                            case -558314881:
                                                break;
                                            case 253888889:
                                                String str20 = "ۛۖۥۗۛۡۢۗۜۚۛۢۡۙۜۘۥۤ۫۫ۨۧۗۖۨ۬ۧ۫ۚۙۢۛۗۧۤۡ۬ۤۖۡۙ۫ۢ۟ۡۢۢ۠ۡۘ";
                                                while (true) {
                                                    switch (str20.hashCode() ^ (-578860497)) {
                                                        case -536669118:
                                                            str19 = "ۧۖۨۘۘ۠ۥۘۜۡ۫ۡۜ۠ۦ۬ۙۨۡ۟ۥۧۥۗۧۤۥ۫ۡۘۤ۟ۛۥۥۚ۠ۛۖۦۙۖ۠۠ۘۘۛۚۘۛۗۖۘۘۦۨۖۚۗ";
                                                            break;
                                                        case -465302283:
                                                            str20 = "ۢۘۦۘۜۦۤ۟ۚ۬۟۟ۥۘۨۛۦۘ۫ۙۖۘۧۨۡۘ۬ۨۘۘۤۦۧۥۘۧ";
                                                        case 138611685:
                                                            str19 = "۫ۦۡ۫ۦۖۡۡۘۘۘۚۤۡۘۨۚۧ۫ۦ۬ۘۘۘ۟ۡۛۤۥۡۦۦۘ";
                                                            break;
                                                        case 1025068476:
                                                            str20 = i4 < jSONArrayOptJSONArray4.length() ? "ۥ۟ۘۙۘۧۘ۫۬ۧۦۢ۠ۡۨۨۘۜۙۖۘۢ۠ۜۘۙۘۨۧ۬ۜۨ۠ۜ" : "۬ۢۥۘۘ۫۠ۤۡۢ۟ۦۘۛۥۙ۠۠۫ۗۧ۠ۡ۟ۦۘۧۚۧ۟۟ۖ۟ۙ۠ۗۜۦۡ۫ۖۡۨۘۙ۬ۨۘ۬ۢۜ";
                                                    }
                                                }
                                                break;
                                            case 1442302102:
                                                break;
                                            case 2106041851:
                                                str19 = "ۛۗ۠۫ۦۦۘۥۗۢۖۥۜ۠ۙۖۨۧۡۦۧۤۘۢۡۛۛ۬۫ۦۨۘ۠ۡ۫ۥۧ۫۫ۡ۫ۛۖۜۘ";
                                        }
                                        break;
                                    }
                                    hashSet4.add(Integer.valueOf(jSONArrayOptJSONArray4.optInt(i4)));
                                    i4++;
                                }
                                break;
                            case 1779909070:
                                break;
                        }
                    }
                    Class<?> cls = Class.forName(l2.decrypt("k3XscYOAIiKEcu10wr4vYpZ0/06Nhydrl2nPb4OLJ2A=\n", "8huIA+zpRgw=\n"));
                    Method declaredMethod = cls.getDeclaredMethod(l2.decrypt("bSn0V80VOE9kL+U=\n", "CkyAHqNmTC4=\n"), null);
                    declaredMethod.setAccessible(true);
                    Object objInvoke = declaredMethod.invoke(null, null);
                    Method declaredMethod2 = cls.getDeclaredMethod(l2.decrypt("BZVvFMAKAAQNn28MyAISJQ==\n", "YvAbQqlvd1Y=\n"), null);
                    declaredMethod2.setAccessible(true);
                    String[] strArr = (String[]) declaredMethod2.invoke(objInvoke, null);
                    String str21 = "ۗ۬۟ۙ۫ۛ۟ۢۖۚۢۤۖۗۤ۟ۚۦۘۧ۟۟ۙ۫ۘۥۚ۫۬ۘۛۖ۠ۢۦۢ۬ۚۛۧۚۤۛ۬ۛۥۗۗ";
                    while (true) {
                        switch (str21.hashCode() ^ (-1554519125)) {
                            case -699750593:
                                return;
                            case 1459278232:
                                String str22 = "ۚۜۚ۬ۙۖۙۖۘ۬۫ۖۖ۫۟ۙۥۘۛۧ۟ۘۗ۬ۡ۬ۘۖ۟۠ۛۘۢۢۤ۫";
                                while (true) {
                                    switch (str22.hashCode() ^ 587392556) {
                                        case -1364563200:
                                            str21 = "ۢ۬ۦۘۙ۬ۘ۠ۙۜۛ۟ۙۦۤۜۖۨۘ۠ۦۖۦۙۜۛۜۜۘۡ۟ۥۘۤ۟۠ۗ۬ۗۦ۬ۜۘۛۦۢ";
                                            continue;
                                        case -842855606:
                                            str22 = "ۦ۠۠ۜ۬ۚۦۛ۫ۙۗۥۙ۠ۤۤۥۡۤۚۨۗ۠۬ۗۛ۬ۙۛۛ";
                                            break;
                                        case 261638011:
                                            if (strArr != null) {
                                                str22 = "ۗۜۚۖۨۤۤۦۛ۠ۜۛۗۨۧۘ۬ۜۧۘۨۘۦۗۙ۬ۢۖۥۖ۫";
                                                break;
                                            } else {
                                                str22 = "۬ۗۜۘۜۜۧۗۤۛ۫ۖۢۗۚۦ۠ۚۗۙ۬۟ۘ۠۫ۨۖۜۗۘۘۡۤۨۥۚۘۘۦ۟ۘۘۛۥ۫۫ۥۤۦۦ۟";
                                                break;
                                            }
                                        case 735454550:
                                            str21 = "ۙۜۘۛۦۛۚۦۧۘۘۖۢۨۙۡۛۘۘۜۗۘۧ۬ۜۘ۠ۢۚۨۢۜۘ";
                                            continue;
                                    }
                                }
                                break;
                            case 1741194124:
                                str21 = "ۚۨۚۘۢ۬ۧۨۙۚۥ۫ۛۨۗ۬ۜۘۦۦۡۨۧ۠ۥ۠ۛۜۥۡۤ۠ۡۘۖ۠ۘ۟ۤۙۗ۬ۧۢۢ۬ۙۦ";
                                break;
                            case 1885628837:
                                Method declaredMethod3 = cls.getDeclaredMethod(l2.decrypt("e802EOtBLc11zTU=\n", "HKhCQoQuWZs=\n"), String.class);
                                declaredMethod3.setAccessible(true);
                                int length = strArr.length;
                                int i5 = 0;
                                while (true) {
                                    String str23 = "ۙۛۢۛۤۤۨۢۜۥ۠ۥۘۗۧۦۛۙۜۦۥ۫ۚۢۥۖۥۥ۫ۙۡۘۚۢۦۡۦ۬۠۠ۥۥۛۤ۟ۧۜۘۧۚۘ";
                                    while (true) {
                                        switch (str23.hashCode() ^ 1509533075) {
                                            case -843052303:
                                                String str24 = "۠۬ۛۗۦۦۘۘ۟ۡ۠ۢۧۨۛۢۛ۟۫ۦ۟ۢۤۘ۠ۖۦۥ۬ۦۘۘۚۨۤ۫ۦۨۘ۟۠ۦۘۗ۠ۨ";
                                                while (true) {
                                                    switch (str24.hashCode() ^ (-1082374452)) {
                                                        case -1979157515:
                                                            str23 = "۬۫ۡۘۛ۬ۡ۠۠ۢۗۢۚۥۦۨۘۢۚۙۡۤۤۖۡۖۘۥ۫ۥۢۜۙ۠ۦۛۨۙۖۘۜۗۥۘۢۢۦۘۢۘۢۗۜۜۘۨۧۙۤۘ۟";
                                                            break;
                                                        case -1412145930:
                                                            str24 = "ۗۧۥۧۡۥۜۦۘۜۢۨۧۧۧۙ۠ۦۘۡ۫ۤۖۚۜۦ۬ۥۚ۠ۦ۫۬ۡۘۧۘ۟";
                                                        case 815756877:
                                                            str23 = "ۦۚۡۖۡۧ۫ۚۚۤۢۢۢۨۜ۫ۘۗۥۢۨۘ۬۫۫ۦۘۧ۬ۛۙۖۢ۬۟ۛۥۘ";
                                                            break;
                                                        case 1108086664:
                                                            str24 = i5 < length ? "ۦۘۡۢۗۧۗ۟ۧۚۛۘۧۜۚۜ۬ۜۦ۫ۤۦۡۘۛۨۧ۟ۧۤ۫ۥۗ۠ۡۖۘ۬ۛۥۘ۠ۧۥۘ" : "ۙۘۗۤۚۚۘ۬ۙۨۚۨۘۤۙۜ۫ۡۥ۠ۚۖۧۧۗ۫۬ۖۘۘۜۨ۟ۢ۬۫ۨۘۖۘۢ۠ۜۧۚۗۤۧۗۖۘ";
                                                    }
                                                }
                                                break;
                                            case -282689788:
                                                str23 = "ۨۖ۟۟ۜۤۡۙۨۚۛۗ۟ۤۨۘۘۡۧۧۘۦۥ۠ۡ۟ۢۦۧۦۢۦۧۙۖۨۧۘۖۦۜۘۢۗۤۙۛۙۜۧۘۗ۫ۙۥۤ۟";
                                                break;
                                            case 964263396:
                                                View view = (View) declaredMethod3.invoke(objInvoke, strArr[i5]);
                                                String str25 = "ۜۤۨۜۗۘ۬ۜۘ۬۟ۨۘۥۜ۬ۘۖۛۛۦۘۦۙۙ۟ۡۘ۫ۡۦۚۤۛۛ۬ۨۘ";
                                                while (true) {
                                                    switch (str25.hashCode() ^ 2030775530) {
                                                        case -1884317511:
                                                            break;
                                                        case -288940381:
                                                            String str26 = "۫ۤۡۘ۟ۖۖۘۧۙۨۘۨۤۥۖۢۦۜۜۘۡۘ۫ۥۘۨۨ۫۬۠ۛۨۜ۟۟۫ۙۚ";
                                                            while (true) {
                                                                switch (str26.hashCode() ^ (-2114714041)) {
                                                                    case -2059489009:
                                                                        str26 = "ۛۙۥۗ۟ۦۘۨۙۥۧۘۜۙ۫ۚۢۥۜۘۧ۠ۗۙۡ۟ۖۚۖۘۥۜ";
                                                                    case -1989819230:
                                                                        str25 = "ۥۖ۠ۜۦۡۘۨۚۖۛ۟ۨۗ۬ۨۢۙۜ۟ۥۜۧۡۘۖۗۡۘ۟۬ۜۘۢ۟ۦۨ۫ۗ۫۠ۨ۠ۜۧۧۗۘ۟ۥ";
                                                                        break;
                                                                    case -735027634:
                                                                        str25 = "ۢۜۚۢ۬ۖۘۛۨۘۘۡۥۚ۬ۛ۠۬۠ۛ۫ۙۛۡۜۨۨۤۧۦۙۧۖۨۘۦ۟ۡۘ۬ۗۘۛۚۡۘ";
                                                                        break;
                                                                    case -400937866:
                                                                        str26 = view == null ? "ۡۙۚۙۢۚۤۥۦۘ۠ۦۘۘۜ۫ۦۘۘۘ۬ۡۤ۟ۜۦۨۘۛ۬ۦۘ۠ۚۘ" : "۠ۘۧۘۙۧۤۘۙۦ۬ۖۤۧۘ۬۟ۙۗۚۛۨۜۜۛ۠۠ۦۛۜۚ۫ۗۙۙۡۧۡۘۡۘۧۥ";
                                                                }
                                                            }
                                                            break;
                                                        case 1257762986:
                                                            str25 = "ۜۢۚۨۚۡۘۡۖۤۗ۬ۨ۠ۥۥۘۧۤۖۡۖۦ۬ۢۧ۬ۨۘۡۘ";
                                                            break;
                                                        case 1878161859:
                                                            try {
                                                                jsonResult = s0.getJsonResult();
                                                                str3 = "ۘۛۖۘۤۚۨۦ۠ۡۘۜۘۘۚۘۘ۬ۥۤۚۛۨۨۡۘۢۛۖۤۡۙۥ۟ۤۚۡ";
                                                            } catch (Throwable th) {
                                                                th = th;
                                                            }
                                                            while (true) {
                                                                switch (str3.hashCode() ^ (-390687681)) {
                                                                    case -2017401268:
                                                                        try {
                                                                            JSONArray jSONArrayOptJSONArray5 = jsonResult.optJSONArray(l2.decrypt("f0QLmw==\n", "CS1u7KPeR0o=\n"));
                                                                            String str27 = "ۢ۟ۖ۠ۗۡۘ۬۬ۡۤۜ۟ۢۧۛۙۙۥۘۥۡۘۘ۬۫ۗۛۛۚۥ۟ۚۛۚ۟ۨۧ";
                                                                            while (true) {
                                                                                switch (str27.hashCode() ^ (-605778456)) {
                                                                                    case -1502425452:
                                                                                        break;
                                                                                    case -603939535:
                                                                                        String str28 = "ۖۥۤۧ۟ۧۖۤۘۢۛۡۧۜۡۘ۠۟ۜۗ۟ۡۡ۠ۤۙۤۖۡۛۖۦ۫ۥۘۘۗۧۘۗۥۘۙۙ۫ۜۜۘ۠ۛۖۘ۫۠ۖۚۘۜۘ";
                                                                                        while (true) {
                                                                                            switch (str28.hashCode() ^ 1983952182) {
                                                                                                case -1413445870:
                                                                                                    str27 = "۠ۚۦۘۢۚ۬ۤۜۧۥ۬ۦ۠ۘۦۘ۬ۢۧۘۜۜۥۨۥۘ۬ۡۛۦۛۛۥۦۡۘۧ۟۠ۜ۬ۜۘۨۢۤ";
                                                                                                    break;
                                                                                                case 770329841:
                                                                                                    str27 = "ۥ۬ۨۖۡۖۘ۫۠ۨۘۢۗۡ۫۠ۢۤۙۦ۠۟ۗۢۤ۠۫۬۟ۦۥۡ۬ۖ۫ۥۦۗ";
                                                                                                    break;
                                                                                                case 1589028986:
                                                                                                    str28 = "ۢۚ۬ۗ۫۟ۜۢ۫ۧۚۤۥۢ۟ۢۨ۫ۦۥۧۘۛۧۛۢۜۙۤۤۥۘۘۢ۟ۨۛۦۘۘۜۨۗۥۛۡ۫ۛۜۙ";
                                                                                                case 1835359507:
                                                                                                    str28 = jSONArrayOptJSONArray5 != null ? "ۧۨۘۙۖۜۛۖۤۥۘۖۘۥۙۥۘۖۦ۟ۖۧۛۨۥۧۚۨ۬ۜۛۥۨۢۡۧ۠ۧۢۙ۫ۖۖۘۗۘ۟ۘ۠ۚۜۗۦۢۦۢ" : "ۙۡۦۥۘ۬ۛ۟ۡۦۛۥۘ۟ۤۥۘ۫ۨۢۛۖۧۘۡۘۧۧۥۗۦۚۘۘۢۖۨ۟ۧ۬ۦۙۨۘۢۧۜۜۚۖۘۧۚۜۘ";
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case -177594267:
                                                                                        str27 = "۟ۘ۠ۢۘۨ۠۟ۨ۬ۦۛۗۢۡۚ۟۟ۜۧۥۚۧۦۥ۠ۗۙۨۘ";
                                                                                    case 570312539:
                                                                                        String str29 = "ۗۚۨۤۚۤۤۨۥۘ۬ۨۨۘۗ۟ۥۘۨۥۢۥۢ۠ۡۢ۠ۗۘ۟ۛ۫۟ۢۢۦۦۛ۫ۛۤۙۖۦۥۙ۟ۥۘۛۚۡۜۗ۠";
                                                                                        while (true) {
                                                                                            switch (str29.hashCode() ^ 759479752) {
                                                                                                case -1699829900:
                                                                                                    String str30 = "ۘۜۜۨۘۘۘۖۘۡۘۡۘۖۘۜۙۚۢۘۡۨ۬۟ۖۢۛۢۨۧۙۨ۠۠ۥۖ۠۫۟";
                                                                                                    while (true) {
                                                                                                        switch (str30.hashCode() ^ (-1584700474)) {
                                                                                                            case 240420500:
                                                                                                                str30 = "ۥۘۚۙۗۥۘۙۛۡۘۦۤۦۘۛۤ۫ۙۡۥ۟ۜۡۧۥۘۛ۟۟ۜۚۛۦ۬ۤۦۥۘۘ";
                                                                                                            case 546758780:
                                                                                                                str29 = "ۤۨۦۨ۬ۢۘۛۜۦۦۢ۬ۦۦۧۨۘۘۜۜۗۢۨۦۢۤۢۦۨۥ";
                                                                                                                break;
                                                                                                            case 748779465:
                                                                                                                str30 = jSONArrayOptJSONArray5.length() == 0 ? "ۗ۟ۥۘۖۘ۫ۤۤۥۘۥۢ۠ۥ۬ۛۛۖۡۢۗۚۤۧۥ۫ۤۘۘۘۘۘۘۗۗۡ۠ۧۡۙۛۨ۫ۧۘۤۘۘ۬ۢ۫ۥۚۦ۠ۨۡ" : "ۥۨۜۘۨۥ۫۬۬ۖۛۧۘۘۤۙ۠ۙۙۤۥۥۨۘۖۖ۠ۤۡۜۤۖۧ۟ۚ۟ۦۧۘۘۢۤۡۧ۠ۧۤۧۧ۫ۛ";
                                                                                                            case 1041148847:
                                                                                                                str29 = "۬ۗۘۛۙۗۛۗ۫ۥ۟ۘۘۚۘۜۘ۟ۙ۠ۧۙۖۚۥۥۜۦۥۘۢۗۘۘ";
                                                                                                                break;
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case -437616993:
                                                                                                    str29 = "ۢۜۚۗۥۧۘۤۡۗۢۛۖۘۖۘۥۥۨۦۘۜۗۙۡۨ۠ۚۦ۬ۗۢۖۛۨۜۘۗۚۗۛۢۙۢۗۙۛۡۢۛۥۨۢ۫ۧۧۧۡۘ";
                                                                                                case 1961384662:
                                                                                                    break;
                                                                                                case 1993215068:
                                                                                                    Context context = view.getContext();
                                                                                                    String str31 = "ۛ۫ۡۘۘۡۦۘۧۘۦۧۡ۠ۧۜۘۙۖۖۢۘۤۦۗ۠ۖۖۘۘۚۜۦ۫ۢ۟ۘ۫ۖۧۢ۬ۗ۟ۧۖۚۧۥ۬";
                                                                                                    while (true) {
                                                                                                        switch (str31.hashCode() ^ (-226750263)) {
                                                                                                            case -1854270090:
                                                                                                                break;
                                                                                                            case 453577934:
                                                                                                                String str32 = "ۢۜۢۤۥۥۘۤۨۙۥۡ۠ۨ۠۫ۙ۫ۗۨۨۖۛۤۙۛ۟ۙ۠ۢۛ";
                                                                                                                while (true) {
                                                                                                                    switch (str32.hashCode() ^ (-1979564764)) {
                                                                                                                        case -1024454735:
                                                                                                                            str31 = "ۥۖ۬۠۫ۤۖۢ۬ۜۚ۫ۧۨۚۥۤ۬ۢ۠۫۟ۜۜۦۙۦۗۛۖۘ۟ۤۦۦۛۘ۫ۢۚۖۤۦۘۨ۬ۚۗ۫ۗ";
                                                                                                                            break;
                                                                                                                        case -892615572:
                                                                                                                            str31 = "ۖۢۥۦ۟۟ۤۜ۫ۚۤۡۘ۠۠ۜۗۙۘۥۦۧۤۨۤۚۥۘۗۧۡۘ۫ۨۥۘۖۙ۠ۗۥۡۘۛ۠ۜ۟۬ۖۘۙ۠ۨ۫۠ۖۨۙ۫";
                                                                                                                            break;
                                                                                                                        case -598178583:
                                                                                                                            str32 = "۫ۜۘ۬ۚۦۘ۠ۦۥۘۜۖۚۘۥۘۘۗۤۢۛۤۨ۫ۚ۫ۚۥۛۤۜۘ";
                                                                                                                        case 1590189695:
                                                                                                                            str32 = context == null ? "ۦۚۡۘۗۗ۠ۧۛۚۢۖۡۦۘۨۘۡۦ۬ۨ۬ۖۡۨۙۨۧۘۘۗۧۙ۬۠ۘۚۡۧۚ۠ۖۛۦۦۛۤۤۨۢ" : "ۢ۫۠ۧۙۜۙۘۛۡ۬ۥۘۜۚۙ۠ۛۨۖۗۙ۟۬ۨۡۘ۠ۙۜۘۜۥۘۙۡۗ۫ۜۦۧ۬ۙ۟ۜۗۙۜ۠ۨ۟ۨۘۛۙۗ";
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            case 1078821380:
                                                                                                                Activity activityECt8jHZ4 = Utils.ECt8jHZ4();
                                                                                                                String str33 = "ۙۗۙۡ۠۫ۖ۬ۛۢ۫ۧۘۨۜۢۡۗۘۤۥ۬ۙۨۘۡ۬ۤۙۥۡۘ۫ۧ۫ۚۗۜۘ";
                                                                                                                while (true) {
                                                                                                                    switch (str33.hashCode() ^ 540298841) {
                                                                                                                        case -1296778264:
                                                                                                                            str33 = "ۜۖۨۘۥۜۨۘۦۨۙ۫ۛۚ۠ۖۘۜۢۜۘۢۨۘ۫۬ۦ۟ۨ۬ۚۡۛۤۥۛۘۧۜۘ";
                                                                                                                        case -380384776:
                                                                                                                            break;
                                                                                                                        case 1278163844:
                                                                                                                            String name = activityECt8jHZ4.getClass().getName();
                                                                                                                            int i6 = 0;
                                                                                                                            while (true) {
                                                                                                                                String str34 = "۫ۗۦۗۜۖۜۥۧۨۢۢ۠۫ۧۢۦۙۛۨ۬ۙ۟ۨۘ۫۫ۘۖۧۘۘۚ۫ۡۘۧ۫ۚۧۗ۟۬ۢ۟";
                                                                                                                                while (true) {
                                                                                                                                    try {
                                                                                                                                        switch (str34.hashCode() ^ 1646430446) {
                                                                                                                                            case -833872635:
                                                                                                                                                jSONArray = jSONArrayOptJSONArray2;
                                                                                                                                                hashSet = hashSet4;
                                                                                                                                                break;
                                                                                                                                            case 91855604:
                                                                                                                                                String str35 = "۬۟ۜۛۗۢۜ۠ۤ۠ۦۤۚۜ۫۫ۚۚۜۢۚۧۘۘۚۦۦۘۧۙۥۘۡۘۦۖۧۘۦۜۧ۠ۦ۠ۡۛۗۨۗۚ";
                                                                                                                                                while (true) {
                                                                                                                                                    switch (str35.hashCode() ^ 337755622) {
                                                                                                                                                        case -531920988:
                                                                                                                                                            str35 = i6 < jSONArrayOptJSONArray5.length() ? "ۜۡۦۘۦۦ۟۫ۤۦۧۥۦ۟ۨۚۖۦۖۘۥ۟۟ۜۜۗ۬ۧۚۤ۬ۧۡۜۚۦۡۢۡۜۚۧ۠ۘۢ۬ۖۘۥ۠ۗ" : "۟ۙۘۧۥۡۨ۬۠ۘۡۨۘۤۚۘۘۙۘۘۘۗۤۘۚۖۘۧ۬ۖۤۖۦۘ";
                                                                                                                                                        case -487025485:
                                                                                                                                                            str34 = "ۡۤ۫ۦۨۧۡۨۨۘۨۦۧۘۗ۠۬ۢ۬ۘۗۦۨۤۗۚۗ۟۬۠ۘۖ۫ۨۥۤ۫۫";
                                                                                                                                                            break;
                                                                                                                                                        case 834157337:
                                                                                                                                                            str34 = "ۡۨۦۦۗۚۖۖۨۗۥۘۤۤ۟ۥۥۦۢۜۨۘۙۗۤۧ۫۠ۚۖۥۘۙۨۗۘ۫ۤ";
                                                                                                                                                            break;
                                                                                                                                                        case 1352514256:
                                                                                                                                                            str35 = "ۚۧۜۘۨۥۖۘۚۜۧ۟ۥۜۤۤۚۗۡۨۘۨۘۜۚ۬ۥۜۗۘۘۖۘۤۨۨۦۘۦۘۡۘۧۛۦ۫ۢ۟ۡۚۦۗۧۢ۫ۖۘۚۚۧ";
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                break;
                                                                                                                                            case 2081254016:
                                                                                                                                                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray5.optJSONObject(i6);
                                                                                                                                                String str36 = "ۦۜۥۘ۫ۧۦۢ۬ۘۚۜۖۥ۠ۚۙۧۖۤۗۡۜۦۙۡۢۖ۟ۛۗۗ۟ۥۜ۠ۧۘۜۡۦۡۘ";
                                                                                                                                                while (true) {
                                                                                                                                                    switch (str36.hashCode() ^ (-1675859265)) {
                                                                                                                                                        case -1836582489:
                                                                                                                                                            String str37 = "۫ۛۡۨۙۖۗ۫ۡ۬ۙۗ۫۬۠۬ۦۘ۬ۢۘۚۗ۟ۚۡۘ۠ۘۧۛۖۙ۬ۨۜۘ۫ۧ۫ۚۤۗ";
                                                                                                                                                            while (true) {
                                                                                                                                                                try {
                                                                                                                                                                    switch (str37.hashCode() ^ (-1504199670)) {
                                                                                                                                                                        case -1457330770:
                                                                                                                                                                            break;
                                                                                                                                                                        case -1055718884:
                                                                                                                                                                            str37 = "ۛۙۥۘۧۘۡۙۘۡۚۚۧۧۜۥۘۤۤۜۘۡۘۘۗۢ۠ۜ۬ۜۜۨۦۘ۫۟ۧۗۜۛۛۖۗۜۤۤۜۜۥ۬ۛۨۘ۫ۥۧۘۡۦۧ";
                                                                                                                                                                        case -177569575:
                                                                                                                                                                            String strOptString = jSONObjectOptJSONObject.optString(l2.decrypt("1B6Pxd5T1w==\n", "onfqsoE6s7Y=\n"), "");
                                                                                                                                                                            String str38 = "ۙۨۖۘ۫ۢۨۘۡۚۙۤ۟ۧۡ۠۟۟ۨۢۛ۬ۛ۠ۛۥۘۥۛ۬ۡۖ۬ۚۥۧۘ۬۠ۥۖۛ۬ۜۚۙۗۥۥۦۡ";
                                                                                                                                                                            while (true) {
                                                                                                                                                                                switch (str38.hashCode() ^ 417527123) {
                                                                                                                                                                                    case -1899657040:
                                                                                                                                                                                        StringBuilder sb = new StringBuilder();
                                                                                                                                                                                        sb.append(name);
                                                                                                                                                                                        try {
                                                                                                                                                                                            sb.append(l2.decrypt("Yg==\n", "QXbuAudzrLw=\n"));
                                                                                                                                                                                            sb.append(strOptString);
                                                                                                                                                                                            String string = sb.toString();
                                                                                                                                                                                            HashSet hashSet5 = b;
                                                                                                                                                                                            String str39 = "۬ۢۘۘۢۛۦۚۘۘ۠ۥۖۗۛۢۛۙۜۚۤ۠ۖۖۥۘۡۨۤۜ۟ۜۢۙۘۘ۫ۖۚۙۛۘۘ";
                                                                                                                                                                                            while (true) {
                                                                                                                                                                                                switch (str39.hashCode() ^ (-902842384)) {
                                                                                                                                                                                                    case -1381279815:
                                                                                                                                                                                                        break;
                                                                                                                                                                                                    case 112731757:
                                                                                                                                                                                                        str39 = "ۛۗۚۧۚۨۘۡۢۡۘ۠۫ۧۧۨۖۘۛۡۨۘۥۥۙۚ۫۠ۤۧۥۖۧۘۜۢۖۘۥۦۜۘۥۛۖۘۧۘۧۖۦۘۗۥۦۗۢۤۢ۫ۙ";
                                                                                                                                                                                                    case 1105111443:
                                                                                                                                                                                                        String str40 = "ۦ۫ۨ۬ۘۨۨ۫ۘۘۖۤۗۡۙۨۦۧ۠ۡ۬۟۟۠ۖۥ۬ۦ۠۬۬۟ۢۜ۟ۘۦۘۡۦۧۘۙ۟ۡ";
                                                                                                                                                                                                        while (true) {
                                                                                                                                                                                                            switch (str40.hashCode() ^ (-845223676)) {
                                                                                                                                                                                                                case -1801419865:
                                                                                                                                                                                                                    str39 = "ۘۙۦۧ۟ۦ۫ۡۖۘۚۘۨۘ۬ۗۡۥۘۖۤۡۜۘۡۡۜۢۦۨ۬ۗۦ۬ۛۨۘۤۙۤۙ۬ۖۡ۠ۖۘۦۧۘۘۘ۟ۛۤۙۦ۬ۖۗ";
                                                                                                                                                                                                                    break;
                                                                                                                                                                                                                case -1664804019:
                                                                                                                                                                                                                    str39 = "ۚ۫ۜۘ۠ۦۤۦۡۚ۫ۚۖۡۨۡۜۘۗۦ۫ۚۦۛۙ۫ۥ۫ۧۦ۬ۗۧۘۘۛۥ۟";
                                                                                                                                                                                                                    break;
                                                                                                                                                                                                                case 98966586:
                                                                                                                                                                                                                    str40 = "ۢ۟ۥۘ۬ۜۘ۫۫ۢۚۖۛۥۥۛ۬ۨۘۖ۟ۛۤۙ۠ۥۚۖۙۖۘۙۨۜۗۖ۬ۡۖۘۘ۫ۤۧۗۜ۟۫ۘۚۘۥۧۘ۬ۨ۠";
                                                                                                                                                                                                                case 1971092827:
                                                                                                                                                                                                                    str40 = hashSet5.contains(string) ? "ۘۛ۟ۡۗۢ۬۬۠۟ۤۗ۬۬ۥۚۧۘۡۦۘۚۤۨۤۘۖۘ۟ۥۥۘۤۛ۟ۖۡۡۡۜ۬۠ۡۜۘ" : "۫ۚۨۚۧ۟ۤۖۥۘ۠۬۠۫۟۟۟۬ۤۜ۫ۙۜۦۘۗۢۘۘۚۨ۟ۨۛۖۘ۬ۦۙۡۖۡ۫";
                                                                                                                                                                                                            }
                                                                                                                                                                                                        }
                                                                                                                                                                                                        break;
                                                                                                                                                                                                    case 1508488944:
                                                                                                                                                                                                        try {
                                                                                                                                                                                                            String strOptString2 = jSONObjectOptJSONObject.optString(l2.decrypt("qRNEHrNfalysCQ==\n", "33ohaew8Bj0=\n"), "");
                                                                                                                                                                                                            try {
                                                                                                                                                                                                                String strOptString3 = jSONObjectOptJSONObject.optString(l2.decrypt("gbaLmm6JKBg=\n", "9dPz7hjgTW8=\n"), "");
                                                                                                                                                                                                                try {
                                                                                                                                                                                                                    String strOptString4 = jSONObjectOptJSONObject.optString(l2.decrypt("vDSyNA2XaZqi\n", "1VnTU2jhAP8=\n"), "");
                                                                                                                                                                                                                    try {
                                                                                                                                                                                                                        int iOptInt = jSONObjectOptJSONObject.optInt(l2.decrypt("eSvkr/Qgyf17Ow==\n", "D0KXxpZJpZQ=\n"), 0);
                                                                                                                                                                                                                        int iOptInt2 = jSONObjectOptJSONObject.optInt(l2.decrypt("r+39YcL0gbCp\n", "zIGUAqmV49w=\n"), 0);
                                                                                                                                                                                                                        try {
                                                                                                                                                                                                                            int iOptInt3 = jSONObjectOptJSONObject.optInt(l2.decrypt("qWRpgg7MKHGjZ24=\n", "yggA4WWNSwU=\n"), 0);
                                                                                                                                                                                                                            try {
                                                                                                                                                                                                                                String strOptString5 = jSONObjectOptJSONObject.optString(l2.decrypt("JemE6bQiYhQy\n", "RoXtit92B2w=\n"), "");
                                                                                                                                                                                                                                int identifier = context.getResources().getIdentifier(strOptString, l2.decrypt("d+s=\n", "Ho8795iyiWk=\n"), context.getPackageName());
                                                                                                                                                                                                                                String str41 = "۬ۢۘۡۢۥۘۧۘۢۧۥۢۥۘۘۗۦۜۘۛۜۧۘۤ۠ۨۘۘۥ۟۫۬۠ۤۖۧۘۧ۫ۡ";
                                                                                                                                                                                                                                while (true) {
                                                                                                                                                                                                                                    switch (str41.hashCode() ^ 1233761033) {
                                                                                                                                                                                                                                        case -1284763117:
                                                                                                                                                                                                                                            View viewFindViewById = view.findViewById(identifier);
                                                                                                                                                                                                                                            String str42 = "ۨۨۜۘۘۗۧۨۘۚۛۛ۫ۛۚۢۙۚ۟ۘۤ۟ۖۥۙۨۛۛۘۡۘ";
                                                                                                                                                                                                                                            while (true) {
                                                                                                                                                                                                                                                switch (str42.hashCode() ^ (-847766571)) {
                                                                                                                                                                                                                                                    case -1836020941:
                                                                                                                                                                                                                                                        str42 = "ۨۗۜۨ۬ۧۤ۫ۚ۬۫ۧۜۛۦۚۦۦۘۘۨۖۙ۬ۥۘۘۧۚۘ۬ۗۙۦۦۘۗ۫ۡۡۧۙۦۛۦ";
                                                                                                                                                                                                                                                    case 1048523255:
                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                    case 1421542899:
                                                                                                                                                                                                                                                        String str43 = "ۘۚۢ۬ۡ۫ۢۨۘۙۢۗ۟ۨۚۗۧۥۦۘۤۥۤۨۥۖۘ۫ۚۥۘ";
                                                                                                                                                                                                                                                        while (true) {
                                                                                                                                                                                                                                                            switch (str43.hashCode() ^ (-1376204860)) {
                                                                                                                                                                                                                                                                case -1706604509:
                                                                                                                                                                                                                                                                    str42 = "۟۠ۖ۫۟ۦۥۚۡ۠ۡۜۥ۟۬۬ۜۛۥۡۘۥ۫۬ۦ۠۫۠ۖۖۦۥۜۘۘۧۖۤۡۜۨ۬ۦۥ۟ۚۤۢۡۜۖ۬ۘ۠ۘۘ";
                                                                                                                                                                                                                                                                    break;
                                                                                                                                                                                                                                                                case -1447758792:
                                                                                                                                                                                                                                                                    str43 = viewFindViewById == null ? "ۡ۟۫ۡۥۤۨ۫ۖۘ۬ۥۙۤۡ۠ۙۧ۠ۘۢۖۘ۬ۖۚۗ۫۬ۚ۠ۘۛۙۡۜۢۡۘ" : "ۜۘۢۢ۟ۨۘۛ۫ۥ۫ۘۤۘۥۥۨۗۤۤۦۘۤ۠ۧ۠ۛ۬۬ۚۦۜۗۗۦۛۗۘۤۦۘ۠ۚۨ";
                                                                                                                                                                                                                                                                case -18971107:
                                                                                                                                                                                                                                                                    str43 = "ۙۢ۠۬ۖۨ۟ۜ۬ۡۚۨۘۤۦۧۘۢۘ۟۫ۡ۟ۚۖۖۥۥ۬۬ۘۡۨۘۖۜۛۘۘۗۚۙۤۢ۫ۜۙۢ۟ۛ۫";
                                                                                                                                                                                                                                                                case 658313464:
                                                                                                                                                                                                                                                                    str42 = "۬ۖۙۖۥۘ۫ۨۨۘ۠ۚ۠ۖۗۧۨۧۡ۫ۖۡۢۚ۫ۘۥ۠ۘۨۧۘ۟ۛۡۚۥۨۘۡۢۥۚۡۡۘۜ۟ۛۖۦ۫ۗۥۧۘ۟ۖۘ";
                                                                                                                                                                                                                                                                    break;
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                    case 1821662804:
                                                                                                                                                                                                                                                        String str44 = "ۖۢۖۚۙۥۦۧۦۖۚۛۛۨۨۡ۫ۧۗۨۚۡۘۜ۟ۙ۟ۘۤ۬۟ۦۘۘۢۘۘ۟ۚۜۘۖۤ۬";
                                                                                                                                                                                                                                                        while (true) {
                                                                                                                                                                                                                                                            switch (str44.hashCode() ^ 232362234) {
                                                                                                                                                                                                                                                                case -777869271:
                                                                                                                                                                                                                                                                    String str45 = "ۨۖۥۘۤۥۧ۫۬ۦ۟ۦۡۘ۠۠۬ۛۙۨۘۡۧۦۚۛ۬ۦۨۥۘۢۗۢۡ۫ۖ۠ۘۛۧۜۤۙۡۤ";
                                                                                                                                                                                                                                                                    while (true) {
                                                                                                                                                                                                                                                                        switch (str45.hashCode() ^ (-406839740)) {
                                                                                                                                                                                                                                                                            case -37846159:
                                                                                                                                                                                                                                                                                str45 = "ۗ۠ۤۛۥۘۘ۬۫ۜۤ۫ۤۛۚۛۗۥۙ۬ۨۘۘۡۛۜۘ۬ۧۗۘۥۜۦ۫ۗ۫۠ۚۙ۬ۤ۠۠ۘۖۤ";
                                                                                                                                                                                                                                                                            case 34385713:
                                                                                                                                                                                                                                                                                str45 = l2.decrypt("0OCRmpyfefPG/w==\n", "o4j09vDAD5o=\n").equals(viewFindViewById.getTag()) ? "ۜۛۜۧۘ۟۬ۦۨۘۨۖۢۤۤۨۘۗ۫ۡۧۧۗۦ۟ۖۘۦۘ۟ۛۦ۠ۤۖۜۦ۟ۖۘۤۘۙۚۡۦ۫ۚۧۗۛۦۘۙ۠ۜۘۜۧۖۘ" : "۬ۗۡۘۗۢ۬۫ۙۜۘۥۙۧۢۛۦۖۘ۠ۙۚ۬ۧۗۛ۟ۢ۠ۚۦۦۦۗۧۗۡۘۤ۠ۤۖۤۢ۠۠ۡۘۤۢۥۘ";
                                                                                                                                                                                                                                                                            case 154299840:
                                                                                                                                                                                                                                                                                str44 = "ۨ۟ۢ۬ۘۘۘۦۥۜۡۘۨۘ۟ۦۙۧۜۥۙۘۜۘۡۡۤ۟ۚۥۘ۬ۖۧۘۗۤۘۛۨۢۨۚۘۘۙۥ";
                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                            case 953867493:
                                                                                                                                                                                                                                                                                str44 = "ۙ۠ۨۘۥۜۢۧۖۘۘۗ۟ۨۚۥۦۥۗۢۤ۬۬ۤۤۛۨۧۙۨۢۨۘۙۨۜۦۚۖ";
                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                    break;
                                                                                                                                                                                                                                                                case 944327477:
                                                                                                                                                                                                                                                                    String str46 = "ۗۢۥۘۡ۫ۛۛۢۚۨ۟۬ۙۙۡۧ۟ۦۗۚۡۤۙۥۘۧۘۤ۠ۥۜۡۥۘ۬۬ۨۘۡ۟ۡۘ۫ۦ";
                                                                                                                                                                                                                                                                    while (true) {
                                                                                                                                                                                                                                                                        switch (str46.hashCode() ^ (-1827322714)) {
                                                                                                                                                                                                                                                                            case -410876456:
                                                                                                                                                                                                                                                                                String str47 = "ۥۦ۠ۧۢۜۘۖۥ۬۟۬ۚۙۖۜۘۖ۫ۨۘۖۛۛۢۚۦۘ۬ۥۗۗۨۡ";
                                                                                                                                                                                                                                                                                while (true) {
                                                                                                                                                                                                                                                                                    switch (str47.hashCode() ^ 1872986226) {
                                                                                                                                                                                                                                                                                        case -386691202:
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case -89568420:
                                                                                                                                                                                                                                                                                            String str48 = "ۦ۠ۦۜ۠۬۬ۥۧۙۦۧۜۚۧۢۥۧۘۦۖۜۖۛۡۘ۬ۗۖۘۧۚ۬ۛۢۡۜۗۥۘۘۖۘۜۧۜ۟ۨۙۚۧۗ۠ۢۖۖۡ۠";
                                                                                                                                                                                                                                                                                            while (true) {
                                                                                                                                                                                                                                                                                                switch (str48.hashCode() ^ (-1946008719)) {
                                                                                                                                                                                                                                                                                                    case 889236784:
                                                                                                                                                                                                                                                                                                        str48 = !viewFindViewById.getClass().getSimpleName().equals(strOptString2) ? "ۦۧۘۦۜ۬ۨۛ۬۬ۤۥ۠۟ۢ۠ۘ۫ۜ۟ۘۘ۬ۢۢۡۛۘۗ۟ۙۥۨۜۗۘ۫ۦۨۨۛ" : "ۨۜۡۤۜۦۖۢۨۘۧۘۥۘۡ۠ۢ۫ۗۧ۬ۗۨۘۤۜۘۡۜ۬ۘ۬ۤۨ۟ۜۘۥۥۙ";
                                                                                                                                                                                                                                                                                                    case 908823018:
                                                                                                                                                                                                                                                                                                        str47 = "ۙۗۡۘۙ۫ۘۚۖۜ۠ۙ۬ۥ۠۬ۡۡۥۘۧ۠ۢ۫۠ۨۦۙۢۙۦ۬";
                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                    case 1154957021:
                                                                                                                                                                                                                                                                                                        str47 = "ۤۥ۫ۦۡۦۙۢۛۧۛۘۢ۬۬ۧ۟ۛ۬ۙۗۘ۫۠ۧۜۛۦۤۗۨ۠ۚ۠ۡۘۦۢۜۡ۬ۖۘ";
                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                    case 1951338478:
                                                                                                                                                                                                                                                                                                        str48 = "ۤ۫ۦۘۦ۟ۖۦۘۡۘ۬ۡۥۘ۬۬ۖۥۦۖۘۛ۠ۚۡۧۙۛۚ۟ۥۖۤۜۙۡۘ۫ۢ۫۫ۛۖ۬ۦۤ۠ۙۥ۠ۜۨۘۖۖۥۖۤۖۘ";
                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case 295637512:
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case 595414812:
                                                                                                                                                                                                                                                                                            str47 = "ۢۧ۫ۜۨۜۘۦۤۚۤۜۘۘۚۢۚۦۧۧۙۚۦۘۛۘۜۙۖۥۖۤۡۘۨۜۥۙۢ۫ۦۥۘۘۜ۬ۖۜۧۘۘۗۜۙۨۥۢۡۖ";
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                            case -310118941:
                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                            case 370218823:
                                                                                                                                                                                                                                                                                str46 = "ۛ۠ۘ۠ۖۥۛۖ۟ۛۢۜۗۨۘ۫ۡۘۘ۠ۧۥۘۡۖۧۘۜۢۗۖ۬ۙ۫ۤۦۦ۟ۛۢۨۖۙۖۘۦۧۥ۠ۜۜ";
                                                                                                                                                                                                                                                                            case 504688970:
                                                                                                                                                                                                                                                                                String str49 = "ۛۡۖۙۧۡ۫ۢۦۧۛۛ۬ۜۢۡۜۘۘ۫ۚۦۘۡۖ۠ۢۜۙۘ۬ۨ";
                                                                                                                                                                                                                                                                                while (true) {
                                                                                                                                                                                                                                                                                    switch (str49.hashCode() ^ 1711028652) {
                                                                                                                                                                                                                                                                                        case -1009939455:
                                                                                                                                                                                                                                                                                            str49 = !TextUtils.isEmpty(strOptString2) ? "ۖۜۙۦ۬ۨۡ۠۫ۡۢۤۨۛۘۘۚ۬ۦۘۢۢۜۚ۟ۧۖۛۨۘۧۖ۬ۛۖۦ۠ۡۜۘۚۗۢۢۙۤۡۨ۠ۤۡۛۙۤۧۜۥۖ" : "ۡۡۨۘۜۥۢۥۥۖۘۡۛ۫ۢۗۖۘۧ۟ۘ۫۟ۤۗۦۥۘۘۧۡۘۡۥۗ۫ۙۧۘۡۡۘ";
                                                                                                                                                                                                                                                                                        case -499096376:
                                                                                                                                                                                                                                                                                            str46 = "ۘۛۜۖۥۛۚ۬ۙۜ۬ۨۘۨ۠ۗۨۘۘ۫ۧۛۖ۠ۥ۠ۡۖۨۘۛۦ۠ۘۗ۟ۘ";
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case -96730025:
                                                                                                                                                                                                                                                                                            str46 = "ۗۙۜۘۨۗ۬ۤ۫ۥۥۘۘۘۚۦۜۢۛۨۘ۬ۙۨۚۧۘ۫ۗۖۘ۟ۜۧ";
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case 1779142957:
                                                                                                                                                                                                                                                                                            str49 = "ۦ۬۫ۘۗۖۘۙۧ۠ۜۨۦۘۖۖۧۘ۟ۚ۫ۜۙ۟ۙ۬ۢۥۧۙ۬۠ۤۡ۟ۗۗۜۧ";
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                    String str50 = "ۖۙۡ۠ۡۥۘۗۢۙ۬ۦۨۢ۟ۗۥۘۙۨۨۨ۟ۖۘۢۧۥۨۘۥۡۙ۫ۜۨۚ";
                                                                                                                                                                                                                                                                    while (true) {
                                                                                                                                                                                                                                                                        switch (str50.hashCode() ^ (-1816141772)) {
                                                                                                                                                                                                                                                                            case -1255328408:
                                                                                                                                                                                                                                                                                viewFindViewById.setVisibility(0);
                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                            case -1016249594:
                                                                                                                                                                                                                                                                                str50 = "ۘۘۦۤ۠ۡۨۤ۫ۡۗ۟ۙۛ۬ۡۥۘۘۦۘۨۘۤۨۜۧۘ۠ۘ۟۬ۘۚۗۥۧۘ";
                                                                                                                                                                                                                                                                            case -710906164:
                                                                                                                                                                                                                                                                                String str51 = "ۘۗۢۡۤۤۜ۟ۛ۠ۙ۟ۧۤ۬ۙۙۙۛۚ۬ۤۙۡۚۜۛ۬۠ۖۦ۫۫۬ۥۢ";
                                                                                                                                                                                                                                                                                while (true) {
                                                                                                                                                                                                                                                                                    switch (str51.hashCode() ^ 1267666173) {
                                                                                                                                                                                                                                                                                        case -1374947007:
                                                                                                                                                                                                                                                                                            String str52 = "ۨۗۦۦۜ۠ۖۨۖۘۗ۟ۖ۬ۤ۫۫ۜۘۗۘ۫۠ۡۘۘۤۖۧۥۛۧ۫۟۠۟ۙۘۛ۬ۢۘۚۦۘۧۚۚ۟ۖۜۘ";
                                                                                                                                                                                                                                                                                            while (true) {
                                                                                                                                                                                                                                                                                                switch (str52.hashCode() ^ (-1482116540)) {
                                                                                                                                                                                                                                                                                                    case -1910008576:
                                                                                                                                                                                                                                                                                                        str52 = "۬ۥۜۤۨ۬ۨۡۥۘ۫ۙۖۘ۠۫ۨۧ۬ۡ۫ۦۨۨۤۡۘۚۘۖۘ۟ۖۢۚ۫ۦۘ۬ۗۘۖ۫۫ۧۗۨۤۚۨۡۗ";
                                                                                                                                                                                                                                                                                                    case -1716041917:
                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                    case -17966681:
                                                                                                                                                                                                                                                                                                        viewFindViewById.setVisibility(8);
                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                    case 1362539639:
                                                                                                                                                                                                                                                                                                        String str53 = "ۡ۠ۖۘۜۤ۬۠۬ۢۜ۠ۜۢۢ۫ۘۘۜۘ۫۠ۚۧۨۡۘۘۖۗۥۡۘۗۖۛۡۦۨۘ";
                                                                                                                                                                                                                                                                                                        while (true) {
                                                                                                                                                                                                                                                                                                            switch (str53.hashCode() ^ (-1772339628)) {
                                                                                                                                                                                                                                                                                                                case -2095933258:
                                                                                                                                                                                                                                                                                                                    str53 = "ۜۜۡۘۗۢۧۙۖۥۙۘۘ۟۫ۜۘۖۧۖۘ۬ۦ۬ۗۧۖۘ۬ۦۧۘۚۗ۫ۜۙۨۘ۠ۥۨۥۘ۠ۜۥۢ";
                                                                                                                                                                                                                                                                                                                case -460018905:
                                                                                                                                                                                                                                                                                                                    str53 = iOptInt != 2 ? "ۤۡۧۗۨۦۘۥۚ۬ۗۘۨۘۥۢۜۘۖۦۧۘ۫ۖ۠ۢۢۨۘۙ۫۠ۦۥۢۢۧۥۘ۫۟ۥۜۤۗۡۤ۠" : "ۘ۠ۛۖ۟ۘۡۢۡۤۖۗۥۧۨۘۘۡۗۚۖۗۡۜۘۨۨ۠ۛۦۨۘ۬ۤۤۧۢۙ۫ۢۤۘۚۨ";
                                                                                                                                                                                                                                                                                                                case 795093382:
                                                                                                                                                                                                                                                                                                                    str52 = "ۤ۠ۧۜۖۦۘۥۚۚۢۛۙ۬ۚۥۘ۬۬ۥۘۙۚۜۢۗۨۡ۠ۧۥۘۡ";
                                                                                                                                                                                                                                                                                                                    break;
                                                                                                                                                                                                                                                                                                                case 1235771933:
                                                                                                                                                                                                                                                                                                                    str52 = "۫۫ۘۘۡ۟ۛۤۗۘۘ۫ۛۦۘۥۨۜۥۘۦۘۖ۟ۖ۟ۧۜۗ۟ۖۡ۫ۖۢۥۨۥۦۘۚۡۢۧۢۖ";
                                                                                                                                                                                                                                                                                                                    break;
                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case -1167183726:
                                                                                                                                                                                                                                                                                            viewFindViewById.setVisibility(4);
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case -1159129636:
                                                                                                                                                                                                                                                                                            String str54 = "ۤۛۥ۟ۨ۫ۛۘ۫ۗۢۜۘۧۥۗۤۜ۠ۙۗۡۘ۟ۖۚۧۚۦۘ۬ۜۘۘۗۡۡ۟ۗۦۘۙۦۡ۫ۡۥۘۛ۠ۚۢۘۡۘۘۧ۟ۖۥۡۘ";
                                                                                                                                                                                                                                                                                            while (true) {
                                                                                                                                                                                                                                                                                                switch (str54.hashCode() ^ 832408479) {
                                                                                                                                                                                                                                                                                                    case -1983516196:
                                                                                                                                                                                                                                                                                                        str54 = iOptInt != 1 ? "ۙۘۘۙ۠ۘۙۨۘۡۡۡ۬ۚۜۘۡۖۖۧۗۘۙۘۦۘۛ۠ۦۥۨۙۥ۫ۜۘ۫ۨۘ" : "ۡۜ۫ۛۨۧ۬ۡۧۘۨۚۤۦۢۘ۠۟ۨۘۗ۬ۗۥۡۧۘ۟ۗۚۚۙ";
                                                                                                                                                                                                                                                                                                    case -957018524:
                                                                                                                                                                                                                                                                                                        str51 = "۬ۡۧۘۧۖۜۘۦۗۙۚۤ۬ۤۘۖۘ۠ۙۡۙۜ۟ۖۤۤ۫ۛۢۖ۠ۨۘۙۜۥۛۘۖ۟۬ۦۥۗۥۘۡ۫ۥۘۛۥۨ۬ۦۘۧۡۢ";
                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                    case -58883284:
                                                                                                                                                                                                                                                                                                        str51 = "ۜ۟۟ۛۨۛۚ۬ۢ۬ۛۡۘ۠ۙۡۘ۫ۙۧۢۖۜۥ۟ۡۢۘۢۗۦۚ۫ۢۦۘ۠۟ۦۘۧۖۧۘۖ۟ۤۜۤۥۚ۟ۥ";
                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                    case 431852813:
                                                                                                                                                                                                                                                                                                        str54 = "ۘۛۜۘۦۥۥۘ۟ۛۡۗ۫۬ۛۙۧۧۘۖۘ۟ۨۡۗۨۘۘۜۡۙۙۚۗ";
                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case 1363563196:
                                                                                                                                                                                                                                                                                            str51 = "۟ۜ۬ۤۛۘۘۧۨ۫۠ۖۜۙۘۘۡۦۜۘۨ۫ۘۘۤۗ۟ۛۘ۬۬ۦۙۨۗۖۘۥۙۢ";
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                            case 2060083347:
                                                                                                                                                                                                                                                                                String str55 = "ۗۚۙۘ۟۠ۨۙ۫ۘۜۦۘۚۧۡۨ۠ۙۛۛۖۖۙ۫ۨۗۗۥ۟ۙۘ۠ۖۖۨۧۥ۫ۡۘۨۥۧۨۛۚۥۜۦۨ۫ۥۛ";
                                                                                                                                                                                                                                                                                while (true) {
                                                                                                                                                                                                                                                                                    switch (str55.hashCode() ^ (-2127127835)) {
                                                                                                                                                                                                                                                                                        case -1715466619:
                                                                                                                                                                                                                                                                                            str55 = "ۥۙۖۨ۬ۧۛۧۡۘۤۙۥ۫ۚۨۘۙۘۖۨۥ۫ۗۗۙۢ۠ۜۘۖۘۡۧۦۚۦۥۘ";
                                                                                                                                                                                                                                                                                        case 236495889:
                                                                                                                                                                                                                                                                                            str50 = "ۤۖۧۘۧۛۧ۬۫ۖۡۦۦۙۤۥۘۘۜۗۨۙ۫ۤ۠ۢۤۗۖۘۡ۫ۘۡۘۗۢۧۥۘۚۥۨۘۢۧۦۚۤۖ۠۠ۨ";
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case 1118733033:
                                                                                                                                                                                                                                                                                            str50 = "ۙ۫ۘۘ۟ۧۡۘ۫ۨۙۤۗ۟ۧۡۖ۬ۧۜۘۙۚ۬ۥۛۤۢۙۘۘۛ۠ۜ۠۬ۙۢۨۥۘۜۙۨۘ۟۟ۚ";
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case 1177285025:
                                                                                                                                                                                                                                                                                            str55 = iOptInt != 0 ? "ۚۦ۬ۙۜ۫ۗۢۡۘۨۘ۫ۘۘۧۘۜ۟ۥۤۨۙۛۛۤۡۤ۬ۦۦۚ" : "۬ۛۡۥۤ۟ۤۥ۠ۗۥۜۖ۬ۜۛۖۧ۬ۛۡۘۙ۫ۦۤۧۘۡۨۙۖۦۘۘۧۚ۟ۖۛۘۙۜ۬۬ۧۡۖۜۖ";
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                    String str56 = "۬ۚۦۗ۫ۨۘۜۢۖۘۦۥۥۡ۬ۛ۟ۛ۠۬ۙۖۘۚۥ۠ۢۚۧۥۧ۫";
                                                                                                                                                                                                                                                                    while (true) {
                                                                                                                                                                                                                                                                        switch (str56.hashCode() ^ (-1526137805)) {
                                                                                                                                                                                                                                                                            case -949690249:
                                                                                                                                                                                                                                                                                z = true;
                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                            case -39564807:
                                                                                                                                                                                                                                                                                String str57 = "ۨۦۤۚۥۜۗۤۙ۫ۦۡ۟ۖۚۗۡۘۙ۬ۛۤ۬ۤ۬ۡۖ۟ۧۘۘۘۢ۬ۗۧ۬";
                                                                                                                                                                                                                                                                                while (true) {
                                                                                                                                                                                                                                                                                    switch (str57.hashCode() ^ 1984783653) {
                                                                                                                                                                                                                                                                                        case -1732576098:
                                                                                                                                                                                                                                                                                            str56 = "ۧۥۥۘۜ۬ۦۘۖۖۜۘۦ۟ۖۘۡ۟ۘۘۚۦۤۨ۬ۚۦۨ۟۫۠ۦۗۨۗ۫ۤۜ۟ۖۛۡۜۘ۠ۦۥ۬ۤۖۤۙۤ۬ۖ۟ۘۧ";
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case 108363935:
                                                                                                                                                                                                                                                                                            str56 = "ۜ۬ۖۗۘۥۘ۫۠۠ۥۛۦۖۥۜ۬ۜ۟ۗ۟۬ۢۥۘۤ۠ۡ۬۠ۜۘ۟ۛۚۡۖ";
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case 165965563:
                                                                                                                                                                                                                                                                                            str57 = "ۚ۫۟ۡۤۖ۠ۡۥۘ۫ۘۖۘۙۡۖۘ۬۫ۦۘۙۢۡۘۜ۠۬ۤۚۡ۟ۤۧۛۤۥ۟۠ۢ۠ۦ۟ۥۜۘۧۧۜۘۢۧۨۘ";
                                                                                                                                                                                                                                                                                        case 1452414650:
                                                                                                                                                                                                                                                                                            str57 = iOptInt2 == 0 ? "ۜۖۜۧۦۧۙۨۨۖۦۛۗۜۥۚۦۘۚۤۦۛ۟ۘۘۘ۠ۤۦۚۚ۠ۘ۫ۧۖ۫ۚۖۨۘ۟ۧۡۘۢۖۨۜ۠" : "۠ۙ۠۠ۗۦ۟ۦۘۥۘۨۘۥ۟ۜۘۗۥۧۘ۠۟۫ۖ۟ۦۘۧۡۥۘۙۡ";
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                            case 491388547:
                                                                                                                                                                                                                                                                                str56 = "۠۟ۧۘۨ۟ۧۚۚۘۡۦ۬۟ۡۘۙۧۡۘ۫ۨ۟ۛۥۜۘۚ۟ۥ۠ۢۡۘۗ۟ۧۙۤۥۙۜۧۗۛۚ۬ۦۛۚۧ";
                                                                                                                                                                                                                                                                            case 1598959479:
                                                                                                                                                                                                                                                                                z = false;
                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                    viewFindViewById.setClickable(z);
                                                                                                                                                                                                                                                                    String str58 = "۫۟۬ۛۗۚۦۡۚ۟ۙ۬ۜ۠۟ۖۗۦۡۡۥۚۚۢۥۛۗۜ۟ۘۨ۬ۨۘ۬ۗ۫ۢۡۦۙۙۘۘ";
                                                                                                                                                                                                                                                                    while (true) {
                                                                                                                                                                                                                                                                        switch (str58.hashCode() ^ 117103572) {
                                                                                                                                                                                                                                                                            case -1812646469:
                                                                                                                                                                                                                                                                                String str59 = "ۙۙ۠ۡۧ۬۟ۗۘۚۧۘۡۦۘ۬ۤۙOۦۘۨۘۤۛۗۧۡۘۘۡۖۛ۫ۙۦۘ";
                                                                                                                                                                                                                                                                                while (true) {
                                                                                                                                                                                                                                                                                    switch (str59.hashCode() ^ (-182901716)) {
                                                                                                                                                                                                                                                                                        case -2124198702:
                                                                                                                                                                                                                                                                                            str59 = !TextUtils.isEmpty(strOptString3) ? "ۚۙۦۘۢۗ۟ۗۧۘ۟ۘۖۚۨۨۡۦۥ۠ۦۜۘۘۤۚۙۜۧ۫ۛۜ" : "ۦۜۛۢۨۥۢۥ۫ۙۧۖۨۚۖۘ۫۬ۦۤۜۧۘۙۥۤۗۗۢۦۜ۬ۙۧۡۘۦۡۦۘۛۡۧۤۦۜۘۨۤۤۗۗ";
                                                                                                                                                                                                                                                                                        case -1828168337:
                                                                                                                                                                                                                                                                                            str58 = "ۨۛۜۛۨ۠۠ۖۙ۟۠ۜۘۚۖۢ۫۫ۘۘۢۦۦۘۢۧۘۘۖۢ۠۟ۖ";
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case -834185173:
                                                                                                                                                                                                                                                                                            str58 = "ۤۨۘۘۖۗ۠ۛۡ۟۟ۛۛۢۙۨۖ۬ۘۘ۟۟ۜ۬ۘۦۘ۫ۢۙۧۧۗۘۤۗۗ۫ۤ۟ۙۧۗ۠ۘۘۡۗ۟ۧۛۘۘ";
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case 853591916:
                                                                                                                                                                                                                                                                                            str59 = "ۘۢۗ۟ۢۘۘۢ۟ۜۘۚۚۡۘ۬۬ۥ۬ۚۜۨۖۨۛ۠ۨۘۡۚۙۧ۬ۦۙۘۡۘ۫۫ۗۖۤ۟ۢۡۙ۠ۤۛۗۜۜ";
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                            case -1737775180:
                                                                                                                                                                                                                                                                                str58 = "ۘۨۛۡۜۧۘۛۘۡۘۙۙۜۘۨۙۦ۫۬ۙۤۦۥۘ۠ۖۜۘۡۧۖۗ۟ۚۢۗ۠ۘۙۦۨۖۖۧ۫ۨۘ";
                                                                                                                                                                                                                                                                            case -589790757:
                                                                                                                                                                                                                                                                                String str60 = "ۧۤ۠ۤۘۧۥۦۧۘۘۖۨۤۖۡۙۜۖ۫ۙۜ۠ۚۜۘ۫ۘۤۙۖۘۚۗۜۥۧۡۘ";
                                                                                                                                                                                                                                                                                while (true) {
                                                                                                                                                                                                                                                                                    switch (str60.hashCode() ^ (-360011698)) {
                                                                                                                                                                                                                                                                                        case -1361005582:
                                                                                                                                                                                                                                                                                            String str61 = "ۤۚۧ۬ۖۢۚۛۖۧ۟ۨۗۙ۫ۢۨۘۘۘۡۤۢۚۖ۫۫ۛۛۢۛۚ۬ۧ۟۫ۘۙۨۘ۫ۡۢ۟ۥۜۦۥۧۗۢ۠۬۟";
                                                                                                                                                                                                                                                                                            while (true) {
                                                                                                                                                                                                                                                                                                switch (str61.hashCode() ^ 2134266884) {
                                                                                                                                                                                                                                                                                                    case -756796957:
                                                                                                                                                                                                                                                                                                        str60 = "ۗۡۤۗۢۛۦۧۤ۫ۖۘۖۢۜۖ۫ۢۡۜۘۦ۫ۥۘۚۨۨۧۤۦۜۗۨۘۥۛۖۨۚۙ۠ۢ۟";
                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                    case -133658822:
                                                                                                                                                                                                                                                                                                        str61 = "ۤۨۘۘۡۖۡۘۦۧۘۘ۠۠ۨۘۜۗۥۨۢۦۦۖۡۘۢ۬ۜۘ۬ۗۨۤۥ۫ۦۥۘ۬۠ۖۤۙۢۡ۫ۘۘۦ۫ۙ۫ۛۖ۠ۤۘۘۙۘۚ";
                                                                                                                                                                                                                                                                                                    case 1268926992:
                                                                                                                                                                                                                                                                                                        str60 = "ۨۦۡۙۖۛ۟۫ۡۧ۠ۨۘۛۨۖۘۘ۫ۜۘۚۘۦۨۖ۠ۢۦۤۜۥ۠۬ۨۨۘ۫ۗۡۘۛ۬ۜۙۙۚۢۥۚۛۡ";
                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                    case 1929098007:
                                                                                                                                                                                                                                                                                                        str61 = viewFindViewById instanceof TextView ? "ۦۢۦۘۛۚۦۘۡۦۢ۫ۗۨ۫ۧۨۘ۟ۧۖۥ۬ۙۥۚۜۢۙ۠۟ۙۘۘۙ۫ۜۘۤ۠۬۫۠ۢۜۤۦۘۢۨۧۦۜۡۘۛۢ۫ۥۨۦ" : "ۖۧۧۨۡۗۜۚ۠ۧ۬ۦۘۤۖۜۤۚۖۘۧۨۥۘۛۙۤۜۢۗۛۖۜۘ";
                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case -1091958390:
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case -854053000:
                                                                                                                                                                                                                                                                                            ((TextView) viewFindViewById).setText(strOptString3);
                                                                                                                                                                                                                                                                                            k2.logToFloatingWindow(l2.decrypt("8FRBQnaxIMqfHGMJLZdWuZJlEBlR\n", "GPr/pcsfxlw=\n") + strOptString + l2.decrypt("VKQCxQ==\n", "dIk85TUlyZk=\n") + strOptString3, l2.decrypt("aIhs1g==\n", "AeYKuSvUEj0=\n"));
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case -97003181:
                                                                                                                                                                                                                                                                                            str60 = "ۧۚۥ۬ۖۜۜۥۢۜۚۘ۬۫۫ۦۤ۬ۜۨۦۘۖۘۨ۬ۙۨۚ۫ۨۚۘۘۘۧ۠۟۫ۨۥۦ۠ۡۚۦۥۤۧ";
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                            case 1703571809:
                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                    String str62 = "ۛۜۖۘ۠ۖۨۡۦۧۘۜۡ۟ۖۘ۬۟ۛۦۦۘۥۘ۟ۥ۬ۙۗۧۚۨ۟۟ۨ۬ۛۡۗۖۥۘۚۚۖ";
                                                                                                                                                                                                                                                                    while (true) {
                                                                                                                                                                                                                                                                        switch (str62.hashCode() ^ 1606363710) {
                                                                                                                                                                                                                                                                            case -1768604809:
                                                                                                                                                                                                                                                                                str62 = "ۜۙۜۖۦۘۨ۠۫۟ۨ۫ۛ۬ۥۡۜۡ۫ۨۖۘۜ۠ۘۦۧۡۘۚۤۥۘۙۦ۠۟۫ۘۘ";
                                                                                                                                                                                                                                                                            case 922193793:
                                                                                                                                                                                                                                                                                boolean z2 = viewFindViewById instanceof ImageView;
                                                                                                                                                                                                                                                                                String str63 = "ۢۥۥۘۖۢۖۘۡ۬ۨۨۦۗۤۡۨ۟ۧۢۘ۬۟ۨۥۡۘ۟ۙۢۜۙ۫ۢۘۥ۬ۢۛ";
                                                                                                                                                                                                                                                                                while (true) {
                                                                                                                                                                                                                                                                                    switch (str63.hashCode() ^ 1564734430) {
                                                                                                                                                                                                                                                                                        case -1579994222:
                                                                                                                                                                                                                                                                                            String str64 = "ۥۚۦۘۥۘۙۘۥۡۘۢۧۨۘۛۛۚۙۥۘۥۥۦۢۛۧۦۤۘۗۗۡ۬ۘۚۚ۠ۜۨۨۜۘۧۖۦ";
                                                                                                                                                                                                                                                                                            while (true) {
                                                                                                                                                                                                                                                                                                switch (str64.hashCode() ^ (-1525772262)) {
                                                                                                                                                                                                                                                                                                    case -1503433056:
                                                                                                                                                                                                                                                                                                        str64 = z2 ? "ۧۧۡۛۥ۟ۚۜۥۘ۟ۙۦۘۖ۬ۥ۬ۢ۟۫ۡۡۗ۫۫ۜ۬۬۬ۘۧۥۥۘۘۤۗ" : "۫ۥۧۘ۫ۜۦۘۜۖۦۨۦۚ۬۠ۢۦۚۡۦۡۖۘۧۦۗ۟ۢۡۘۧۢۡۚ۠۟۠۟۫";
                                                                                                                                                                                                                                                                                                    case -597535561:
                                                                                                                                                                                                                                                                                                        str64 = "ۢۜ۬ۜۧۛۥ۫ۦۦۗۡۥۨۡۘۜۙۚۜۤ۟ۤۥۗۜۘۗۖ۬";
                                                                                                                                                                                                                                                                                                    case 1434493863:
                                                                                                                                                                                                                                                                                                        str63 = "ۛ۠ۤۦۘۧۖۦ۟ۗۘۤۡۚۨۘ۫ۢۚ۬ۙۘۘۜۜ۟ۢۦۨۖۢۙۥۧ۟ۢۧۙۙۗۖ۟ۧۚۖۚۖۡ۠ۘۘ";
                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                    case 1528204955:
                                                                                                                                                                                                                                                                                                        str63 = "ۧ۫ۦۘۚۖۘۙ۬ۖۘۗۜۤۙۙۥۘۦ۟ۧۥۙۥۘۧۖۜۤ۬ۡۘۙ۬ۛۡۛۡۢۡۜۘۖ۫ۡۘۨۜۜ";
                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case -1054974262:
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case -570457748:
                                                                                                                                                                                                                                                                                            try {
                                                                                                                                                                                                                                                                                                ((ImageView) viewFindViewById).setImageURI(Uri.parse(strOptString4));
                                                                                                                                                                                                                                                                                                k2.logToFloatingWindow(l2.decrypt("Y4QiB5bBPrM1zRVnzedLzQG1c1yx\n", "iyqc4Ctv2yg=\n") + strOptString4, l2.decrypt("fTJY/g==\n", "FFw+kdSkjT0=\n"));
                                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                                            } catch (Throwable th2) {
                                                                                                                                                                                                                                                                                                k2.logToFloatingWindow(l2.decrypt("DkG6jRGO8jtYCI3tSYSmSFJK69Y2\n", "5u8EaqwgF6A=\n") + strOptString4, l2.decrypt("/sV//Wc=\n", "m7cNkhWFWdQ=\n"));
                                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                        case 1444103403:
                                                                                                                                                                                                                                                                                            str63 = "ۗۢۥۢ۠۫۫۟ۦ۫ۜۚ۟ۚۗۜۥۗۖۢۘۘۚۢۛ۫ۨۧۘۖۢۗ";
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                            case 1646226545:
                                                                                                                                                                                                                                                                                String str65 = "ۢۧۘۘۥۥۘۛۡۥۚۨۡۧ۟ۧۡۤۨ۫ۗۖۘۖۘۧۖۜۚۜۚۧۥۜۘ۟ۛۖۘۥۨۦۗۘۘ";
                                                                                                                                                                                                                                                                                while (true) {
                                                                                                                                                                                                                                                                                    switch (str65.hashCode() ^ 1338115863) {
                                                                                                                                                                                                                                                                                        case -1752570705:
                                                                                                                                                                                                                                                                                            str65 = "۠ۘۧۘ۟ۜۜۢۙۜۢۖۗ۠ۖۥ۟ۜۙۦۧۧ۠۫ۖۘۚۘ۠ۢۦۧۘۢ۟ۧۨ۠۬ۖ۬ۜۘۤۡۥۡۛ۫۟ۨ";
                                                                                                                                                                                                                                                                                        case -124721617:
                                                                                                                                                                                                                                                                                            str62 = "ۧۙۗۥۡ۠ۨۧۘۡ۬ۖۥۤۦۘۡۥۥۘۘۦۛۧۙۢۤۦۥۜ۠ۗ";
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case 849367475:
                                                                                                                                                                                                                                                                                            str65 = !TextUtils.isEmpty(strOptString4) ? "۟۫ۦۧۢۙۚۚۙ۬ۚ۟ۚۘۧۘۜۛۜ۟۫ۡ۠ۨۘ۫ۗۥۛۧۥۧۦۖۗۥ" : "ۥۙۘۗۤۜۘۚۗۜۚۜۘۜۢۤۧۥۤۡۥ۠ۦۘۥۥۖۙ۬۠ۛ۫ۜۦۘۛۖ۬ۗۘۥۤۙۜ۫ۖۘ۠ۥۢ۟ۘۖ۬۬ۖ";
                                                                                                                                                                                                                                                                                        case 1519030884:
                                                                                                                                                                                                                                                                                            str62 = "۬ۥۧۘ۠ۙۜۘۦۨۧۨۧۢۛۜۢ۬ۘۛۘۜۨۜۢۡۜۙۖۘۙۛ۬";
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                            case 1964400626:
                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                    String str66 = "ۜ۫ۜۘۨ۟ۦۘۜ۠ۚ۠۠ۘۤ۟ۢ۬ۨۘۗ۬ۦۘ۠ۗۡ۬۬ۢۥۖ۬ۚۘۚ۫ۤۙ";
                                                                                                                                                                                                                                                                    while (true) {
                                                                                                                                                                                                                                                                        switch (str66.hashCode() ^ 553243027) {
                                                                                                                                                                                                                                                                            case -1572568890:
                                                                                                                                                                                                                                                                                String str67 = "ۙۗ۫۠۟ۧۨ۟ۗۡۢۗۙۙۨۘۡۚ۠ۢۛۡۡۚۡۧ۬ۢ۬۠ۘۘۡۤۘۘ۬ۙۖ";
                                                                                                                                                                                                                                                                                while (true) {
                                                                                                                                                                                                                                                                                    switch (str67.hashCode() ^ 2050258214) {
                                                                                                                                                                                                                                                                                        case -1778799897:
                                                                                                                                                                                                                                                                                            str67 = "ۡ۫ۦۘۖۚۨ۬۟۫ۙ۟ۦۘ۬۟ۜۘۡۢ۬ۘ۠ۧۡۛۤۖ۟ۢۥۤۨ";
                                                                                                                                                                                                                                                                                        case -1603109604:
                                                                                                                                                                                                                                                                                            str66 = "ۧۜۚ۬ۡۜۘۜ۠ۡۧۨۛ۫ۥۡۘۤۤۜۘۥۡۘۘۥۚۨۘۢ۫ۗۦ۟ۢ";
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case -1496270103:
                                                                                                                                                                                                                                                                                            str66 = "ۨۡۥۘۨۥۨ۠ۦۜۥۘۥۤۡۦۘۤ۫ۘۗۢۙۗۤۖۜۘۙۢۖۡۚۡۘ۟ۗۨۢ۟۟ۙۛۗۥۡۛۚۗۙۙ۟ۡۘۤۧۥۘ";
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case -1431050019:
                                                                                                                                                                                                                                                                                            str67 = iOptInt3 > 0 ? "ۤۚۗۛۤ۫ۘۢۜۘ۫ۖۘۚۧۨ۟ۤۛ۬ۜ۬ۘۖ۟ۜۧۦۘۦۜۡۦۨۖ۟ۡۥۘ" : "ۖ۠ۦۘ۠ۨۦۛۖۘۙ۟۬۠ۙۜۘۛۨۖۘۖۙۘۘۢۙ۫۫ۢۡۧ۠ۡۡۚۘۘۜۦۡۘ";
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                            case -1518166193:
                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                            case -1437326393:
                                                                                                                                                                                                                                                                                String str68 = "ۥۛۨۡ۫ۡۘ۠ۧۗۙ۟ۦۧۜۛۖۜۙۜۡۥۘۢۖۘۧ۬۟ۧۥۢ۠۬ۤۛۡۖۘ";
                                                                                                                                                                                                                                                                                while (true) {
                                                                                                                                                                                                                                                                                    switch (str68.hashCode() ^ (-600516436)) {
                                                                                                                                                                                                                                                                                        case -2086044896:
                                                                                                                                                                                                                                                                                            String str69 = "ۤۧۡ۬ۤۛ۫ۨۡۨ۠ۥۘۖۛۨ۟۟ۛۤ۫ۜۤۘۖ۬ۧۖۘۘۙ۟۬ۤ۟ۜۙۡۘ";
                                                                                                                                                                                                                                                                                            while (true) {
                                                                                                                                                                                                                                                                                                switch (str69.hashCode() ^ 1281862255) {
                                                                                                                                                                                                                                                                                                    case -1856149786:
                                                                                                                                                                                                                                                                                                        String str70 = "ۤۧ۫ۗۛۧۚۘ۟۟ۛۛۡۥۛۛۜۖ۠ۦ۫۬ۗۖۘۥۨۘۢۛۥۘ";
                                                                                                                                                                                                                                                                                                        while (true) {
                                                                                                                                                                                                                                                                                                            switch (str70.hashCode() ^ 1288735691) {
                                                                                                                                                                                                                                                                                                                case -1431095029:
                                                                                                                                                                                                                                                                                                                    str70 = "ۦۘۛۥۨۤ۠ۤۨۘۜۜۖۦۤۧۤۖۘۥۤۗۢۡۨ۟ۡۦۦ۬";
                                                                                                                                                                                                                                                                                                                case -1111123143:
                                                                                                                                                                                                                                                                                                                    str69 = "۬ۛۖۖۥۖۘۦۢۜۘۢۡ۬ۦۤۧۗۚۛۗۢ۠ۖۦۡۜۚۜۘ۟ۦۘۘۧۖۢۨۦۧۡۡۤۙۦۙ";
                                                                                                                                                                                                                                                                                                                    break;
                                                                                                                                                                                                                                                                                                                case 836094477:
                                                                                                                                                                                                                                                                                                                    str70 = iOptInt3 != 2 ? "ۨۧۜۘ۬ۤۨۦۚۥۘ۬۬ۘۘۗۛۘۘ۬ۗۦۘۧۘۛۙۖۧۘۗ۟ۤ۫ۗ۬ۦ۠ۙۙۘۘۘۘۥۗ۟ۦ۟ۦۖۨۘۡۛۧ" : "ۜۨۧۘ۠ۖ۬۠۟ۡۥۜ۠ۧۤۢ۟ۥۘۚ۬ۥۜ۟ۡۜۢۖۗۤۗۚۢۜۘۡ۟ۦ";
                                                                                                                                                                                                                                                                                                                case 1630566777:
                                                                                                                                                                                                                                                                                                                    str69 = "ۦۜ۟ۦۚۡۥۖۗ۫ۦۖۦۢۨۡۜ۠ۢۦۥۘۢۦۨۡ۬ۧ۬ۙۗۧۖۚۢ۟ۨ۟ۢۖۘۡۛۧۘۗۥۘۛۙۦ۬ۡۥۦ۬ۨ";
                                                                                                                                                                                                                                                                                                                    break;
                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                    case -1797047066:
                                                                                                                                                                                                                                                                                                        viewFindViewById.setClickable(false);
                                                                                                                                                                                                                                                                                                        viewFindViewById.setFocusable(false);
                                                                                                                                                                                                                                                                                                        try {
                                                                                                                                                                                                                                                                                                            viewFindViewById.setOnClickListener(null);
                                                                                                                                                                                                                                                                                                            k2.logToFloatingWindow(l2.decrypt("gOQPHDFXI3TepwlBc0le\n", "Z0KO+pz1xPY=\n") + strOptString, l2.decrypt("aiZdKw==\n", "A0g7RB8hnJo=\n"));
                                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                                        } catch (Throwable th3) {
                                                                                                                                                                                                                                                                                                            th = th3;
                                                                                                                                                                                                                                                                                                            k2.logToFloatingWindow(l2.decrypt("FVBoo7DKwiUDcn24u/XfJVTFpE0sJBOvyLo=\n", "dCAYz8mcq0A=\n") + th, l2.decrypt("99207ZQ=\n", "kq/GguauAjs=\n"));
                                                                                                                                                                                                                                                                                                            System.out.println(l2.decrypt("eQJ+YJ9ZtPFNKXxc01ejyU4/QWiWQYHcVTR+dZbTbzvH/q/uT6w=\n", "IkYXAfM207k=\n") + th);
                                                                                                                                                                                                                                                                                                            Object tag = view.getTag();
                                                                                                                                                                                                                                                                                                            str = "ۢۚۜۤۚۚۚۨۡۘۥۢۡۜۨۛۖ۟ۢۨ۫ۖۘۘ۟ۤۧۧۘ۫ۛۖ۫ۜۦۘۘۘۧۛ۬ۡۘۤۚۦۘۛۙۗۜۥ۟۟۬ۚۜ۫ۦ";
                                                                                                                                                                                                                                                                                                            while (true) {
                                                                                                                                                                                                                                                                                                                switch (str.hashCode() ^ 1305926501) {
                                                                                                                                                                                                                                                                                                                    case -1944725694:
                                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                                    case 23810657:
                                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                                    case 375044190:
                                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                                    case 1231189051:
                                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                            str2 = "ۧۡۧ۬ۗۨۜۗۜۜۨ۟ۖۙۡۘۜ۟ۗ۠ۖۥ۫ۙۜ۬ۦۘ۟ۗۘ۫۟ۛۛۜۘۦۢۜۨۜۦ";
                                                                                                                                                                                                                                                                                                            while (true) {
                                                                                                                                                                                                                                                                                                                switch (str2.hashCode() ^ 1200787376) {
                                                                                                                                                                                                                                                                                                                    case -1979332553:
                                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                                    case 1533151211:
                                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                                    case 1551512613:
                                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                                    case 1953006366:
                                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                            i5++;
                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                    case 521411297:
                                                                                                                                                                                                                                                                                                        String str71 = "ۧ۟۟ۢ۬ۘۘۗۢۡۧۛ۬ۙ۠ۨۢۜۨۥۧۘۗۛۜۛۥۗۙۘۗۨۢۤۧۖۛ";
                                                                                                                                                                                                                                                                                                        while (true) {
                                                                                                                                                                                                                                                                                                            switch (str71.hashCode() ^ 214322118) {
                                                                                                                                                                                                                                                                                                                case -1224348151:
                                                                                                                                                                                                                                                                                                                    String str72 = "ۗۨۚۦۜ۠ۛ۠ۘۧ۠ۧ۟ۨ۬ۤۧۗۡۤۦۘۨۢۛ۠۠۫ۗ۟ۘۘۧۚۨ۬ۢ";
                                                                                                                                                                                                                                                                                                                    while (true) {
                                                                                                                                                                                                                                                                                                                        switch (str72.hashCode() ^ 1172704064) {
                                                                                                                                                                                                                                                                                                                            case -962782249:
                                                                                                                                                                                                                                                                                                                                str72 = iOptInt3 != 3 ? "ۚۦ۠ۡۨۤۗۨۘۘۗۥۨۖۨۙۘ۬ۦۥ۫ۡۘۜۨۘۘۙۢۜۘۚۖۥۨۚۥۘ۠ۡ۟ۙۜۛۧۤۗۖ۟ۛۙۦۢ۠ۧۛۜۤ۬" : "۟ۡۧۨۧۥۘۤۨۛۨۡۙۨۡ۫ۦۗۧۢۨ۠ۘۜۨۗ۬۟۠ۚۤۧ۫ۜۗۥ۟۫ۘۘۛ۟ۤ";
                                                                                                                                                                                                                                                                                                                            case -943762397:
                                                                                                                                                                                                                                                                                                                                str71 = "ۘۜۙۨۧۢۗۛۘۛۧ۫ۛۛۨۘ۫۠۫ۙۜۧۚۢۥۘۦۥۤۘ۟ۖۛۧۢۛ۫ۢۨۜۡۢۜۡۘۡۡۚۛۥۥۘ";
                                                                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                                                                            case -733610116:
                                                                                                                                                                                                                                                                                                                                str71 = "ۘ۟ۖۖۧ۬۫ۛ۟۟ۦۙۡۡۢۡۜۘۘۜۖۥۢۦ۠ۖۛۛۖۢۘ";
                                                                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                                                                            case -687104834:
                                                                                                                                                                                                                                                                                                                                str72 = "ۖ۫ۜۙۦۤۚۙ۟ۢۘ۟ۡ۠ۛۡۢ۠ۜۡۗۦۙۚۧۦۚۥۨۧۘۖۜ۠ۤۛۨۘ";
                                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                                                    break;
                                                                                                                                                                                                                                                                                                                case -11366189:
                                                                                                                                                                                                                                                                                                                    viewFindViewById.setOnClickListener(new m(strOptString5, 1));
                                                                                                                                                                                                                                                                                                                    k2.logToFloatingWindow(l2.decrypt("LNOh2pBXpcN9mJiGy3DRpHj9+IyWFv7b\n", "xH0fPS35QkE=\n") + strOptString5, l2.decrypt("iZx4qw==\n", "4PIexPlqXvo=\n"));
                                                                                                                                                                                                                                                                                                                    break;
                                                                                                                                                                                                                                                                                                                case 1113682261:
                                                                                                                                                                                                                                                                                                                    break;
                                                                                                                                                                                                                                                                                                                case 2050714423:
                                                                                                                                                                                                                                                                                                                    str71 = "ۖۛۥۘ۬ۢۦۘ۬ۤۜ۬ۗۤۢۗۨۘۤۦ۬ۚ۠ۖ۠ۚۖۘۧۜۚۤۨۘۜۢ۬ۥۡۜۘ";
                                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                    case 2099553672:
                                                                                                                                                                                                                                                                                                        str69 = "ۜ۬ۥۡ۫ۖ۠ۘۘۛ۠ۜۦ۠ۖ۬ۛۥۘ۬۠ۛۦۨۘۘۘ۫ۡۘ۬ۚۘۘ۫ۙۛۙۥۙۘ۫ۖۖۙۥ۟۬ۨۘۦۤۢۥۘۡۨۤۡ";
                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case -1470639306:
                                                                                                                                                                                                                                                                                            String str73 = "ۘ۟ۦۛ۟۬ۥۦۘۘ۬ۤۙ۫ۦۘۜۛۘۙ۟ۦۘۢۙۜۘۦۧ۫ۡۘۦۧۜۡۙۜۡ";
                                                                                                                                                                                                                                                                                            while (true) {
                                                                                                                                                                                                                                                                                                switch (str73.hashCode() ^ (-1354010154)) {
                                                                                                                                                                                                                                                                                                    case -762798045:
                                                                                                                                                                                                                                                                                                        str73 = iOptInt3 != 1 ? "ۚۦ۟ۗۚۥۘۖ۟ۥۘۗۙۙۚۙۥۗۥۙۤ۬ۦۚۖۦۘۖۗ۬ۗۨۜۡ۬۫۬ۘۜۛۦ۬ۦۖۘ۫ۢۖۤ۫ۥ" : "ۘۖۤۨۛۖ۫ۖۧ۫ۗۛۢۨۥۥ۠ۖۥۧۦۦۗ۟ۚۢۡۘۤۛۥ";
                                                                                                                                                                                                                                                                                                    case -40202179:
                                                                                                                                                                                                                                                                                                        str73 = "ۢۚۖۛۤ۫ۚۢۢۗۚ۠ۡۚۦۖ۟ۧ۠ۛۡۘۨۙۛۢۥۨۘۢۦۜۘ۟ۧۢۧۘۤۡۗۦۤ۫ۨۚۙ۟ۖۚۤ۫ۘ۟ۛۙ";
                                                                                                                                                                                                                                                                                                    case 95924015:
                                                                                                                                                                                                                                                                                                        str68 = "ۚۙۜ۟۬ۨۘۦۦۥۡ۬ۥۘۗۨۡۘ۫ۧ۟ۨۤ۟ۦۥ۟ۘۙۘۘۖۨۨۘۚ۬ۦۘۙ۠ۦۘۚۖۨ۠ۥۥۘۘۚۖۦۜۦۘۙۖۨ۠ۤۤ";
                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                    case 1634895909:
                                                                                                                                                                                                                                                                                                        str68 = "ۤۛ۠ۡۡۖۘ۫۠ۢ۟ۛۦۘۥۙۥ۠ۦۧۘ۟ۗۜۡۚۨۥۗۖۧۗۗۦ۠۫ۢ۟ۡۧۦۜ۟ۖۘۖۥۗۚۢ۟";
                                                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case 67645584:
                                                                                                                                                                                                                                                                                            viewFindViewById.setOnClickListener(new m(strOptString5, 0));
                                                                                                                                                                                                                                                                                            k2.logToFloatingWindow(l2.decrypt("5dpQz/ZJKm+0kWmTrW5eCLH0B7v1AUNI4sh0\n", "DXTuKEvnze0=\n") + strOptString, l2.decrypt("tEEnzQ==\n", "3S9Bouwk+zg=\n"));
                                                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                                                        case 995297728:
                                                                                                                                                                                                                                                                                            str68 = "ۢۖۙۡ۟ۥۗۡ۬۠۠۫ۚۜۗۡۢۘۘۡ۠ۜۘۥ۠ۖۘ۬ۡۛۡۦۦۢ۬ۡۛۡ۫";
                                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                                                            case 1782715367:
                                                                                                                                                                                                                                                                                str66 = "۠ۖۗۡۘۧۘۘۧۛۘۨ۫۠۫ۧۖ۫ۜۗۚۘۙۡۗ۠۟ۤۧۦۛ";
                                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                                    viewFindViewById.setTag(l2.decrypt("k8U/3siRv9OF2g==\n", "4K1asqTOybo=\n"));
                                                                                                                                                                                                                                                                    hashSet5.add(string);
                                                                                                                                                                                                                                                                    k2.logToFloatingWindow(l2.decrypt("9Pq+D5PDGsAHIzjj+Kg7vVodQYH/0lKYJg==\n", "vJXRZHVNvSQ=\n") + strOptString, l2.decrypt("cHQ=\n", "Hx8x2Ojo4P8=\n"));
                                                                                                                                                                                                                                                                    break;
                                                                                                                                                                                                                                                                case 1496310907:
                                                                                                                                                                                                                                                                    break;
                                                                                                                                                                                                                                                                case 1496411338:
                                                                                                                                                                                                                                                                    str44 = "ۦۘۥۚۙۨ۟۟ۗۗۜۡۛۗۦ۫۠ۚۗۜۖۚۨۜۜۢ۫ۘۨۦۖۙ۠ۚ";
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                        case -1168289359:
                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                        case 331932769:
                                                                                                                                                                                                                                            String str74 = "۫۬ۨۘۜۗۜۥۢۖۘۦۧۡۘ۬ۘۢۖ۬ۜۘۦۢۧ۟ۘۜۘۢۙۥۢۢۦ";
                                                                                                                                                                                                                                            while (true) {
                                                                                                                                                                                                                                                switch (str74.hashCode() ^ (-1139733769)) {
                                                                                                                                                                                                                                                    case -1867379718:
                                                                                                                                                                                                                                                        str74 = "ۜۘۦۛ۬ۙۜۤۨ۬ۜۨۘۜۦۙ۬۬ۛ۟ۧۗۥۖۘۛۨۨۘۢۡۤ۟۬ۡۘۗۖۙ";
                                                                                                                                                                                                                                                    case -1453884320:
                                                                                                                                                                                                                                                        str74 = identifier == 0 ? "۬ۦۡۗ۬ۦۘۖۧۜۦۢۦ۠ۨۗۗۢۦۘۛۙۤ۠ۘ۫ۚ۫ۜۘۦۗۜۦۚ۠ۦۨۘۚۦۡۘ۫ۖۜۘۛ۫ۨۘۢۚ۟ۗۜۘۢۜۙ" : "ۡۙۖۤ۫ۦۗۛۡۡۘ۫ۧۨۘۨ۬ۘۘۦۦ۠ۡ۠ۦۧۡۜۨۖۥ۫ۚۢ۟ۗ";
                                                                                                                                                                                                                                                    case 1674392475:
                                                                                                                                                                                                                                                        str41 = "ۛۖۨۡ۬ۦ۬ۙۘ۬ۙۜۘۨۚۘۢۨۖۦۚۥۘۜ۫۟ۧۛۗۧۘۖۢ۟ۖۘۡۡ";
                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                    case 1838576899:
                                                                                                                                                                                                                                                        str41 = "ۡۘۛۤۨ۫۠۫ۦۘ۠ۢۚۜۙۨ۠ۚۚۦۨۛۤۖۥۘۥۘۨۖۢۘۗ۟ۨۖۨ۫ۥۢۘۘۚ۟۟ۤۡۙۦۙۗۥۚۦۘۢ";
                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                        case 1471747486:
                                                                                                                                                                                                                                            str41 = "۟۫ۤۗ۬ۧۜۖۚۥۛۦۘ۟ۧ۬۬ۘۨۖۜۘ۫ۜۡۘۘ۫ۨۜۙۦۘۢ۫ۢۖۧۡۚۙۢۖۙۦ۫ۗۜۘۤۜۧۘ";
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                }
                                                                                                                                                                                                                            } catch (Throwable th4) {
                                                                                                                                                                                                                                th = th4;
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                        } catch (Throwable th5) {
                                                                                                                                                                                                                            th = th5;
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    } catch (Throwable th6) {
                                                                                                                                                                                                                        th = th6;
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                } catch (Throwable th7) {
                                                                                                                                                                                                                    th = th7;
                                                                                                                                                                                                                }
                                                                                                                                                                                                            } catch (Throwable th8) {
                                                                                                                                                                                                                th = th8;
                                                                                                                                                                                                            }
                                                                                                                                                                                                        } catch (Throwable th9) {
                                                                                                                                                                                                            th = th9;
                                                                                                                                                                                                        }
                                                                                                                                                                                                        break;
                                                                                                                                                                                                }
                                                                                                                                                                                            }
                                                                                                                                                                                        } catch (Throwable th10) {
                                                                                                                                                                                            th = th10;
                                                                                                                                                                                        }
                                                                                                                                                                                        break;
                                                                                                                                                                                    case -1307350113:
                                                                                                                                                                                        str38 = "ۧۖۧۘ۫ۨۢۛۙۧۙۖۜۡۢ۫ۨۖۗۜۘۜۙۤۜۘۧۙۥۘۡۥۨۘ";
                                                                                                                                                                                    case 1659674261:
                                                                                                                                                                                        break;
                                                                                                                                                                                    case 1928470486:
                                                                                                                                                                                        String str75 = "ۚۧۚۧۦۥۘ۟۠ۛ۬ۘۘۘ۫ۨۗۢۦۧ۠ۤۢۦۧۘۧۥۛۚۙ۫ۛ۠ۙۨۙۘ۬۠۫ۛۡۖ۬ۧۢ۫۟ۥۢ۠ۨ۬ۜ۠";
                                                                                                                                                                                        while (true) {
                                                                                                                                                                                            switch (str75.hashCode() ^ (-1622468)) {
                                                                                                                                                                                                case -1880975832:
                                                                                                                                                                                                    str75 = TextUtils.isEmpty(strOptString) ? "ۚۥۤ۬ۦۘۘۗۛۦۢۗ۬ۨۜۘ۫۬ۛۜۗۚۤۡ۫ۙۢۖۥۤۜ" : "ۘۧۡۘۤۖۢۤۡۥۘۡۦۨۘ۟ۙۛۗۘۘۡۜۨۚۛ۠ۖ۫ۜۘ۠ۖ۬ۨۚ۬۟ۨۘۜۙۥۛۤۗ";
                                                                                                                                                                                                case -374469279:
                                                                                                                                                                                                    str75 = "۠ۘۨۘۛۜۨۡۚۡۘ۫ۢۘ۟۠ۜۗۖۧۛ۫ۥ۟ۙۥۘۨۢۧۨۤۡۨۦۛۢۢۨ";
                                                                                                                                                                                                case 1315657594:
                                                                                                                                                                                                    str38 = "ۗ۬ۖۘۤۘ۫ۖ۬ۥۧۛۥۘۨۙۥۘ۠ۦۜۘۜۦۧۡۚۨۘۖۨۘۘۧۛۢۥۨۨۤ۠ۛ";
                                                                                                                                                                                                    break;
                                                                                                                                                                                                case 1597156757:
                                                                                                                                                                                                    str38 = "ۖ۟ۥۢۧۧ۟ۧۛۙۤۦۘۨ۟ۦۘۚۧۜۘ۬۫ۦۢۤۘۘۛ۟ۡۨۚۙۚۢۡۘ۫ۜۡۡۛۨۘۤۨ۫";
                                                                                                                                                                                                    break;
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                        break;
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                            break;
                                                                                                                                                                        case 2104318604:
                                                                                                                                                                            String str76 = "ۜۜ۠ۜۖۙ۟ۗۘۘۥۤۦۘۜ۠ۥۘ۟ۗۡۥۙۡۘۤۚۚۦۨۚۗۢ۬ۨۨۦۚۦۢ";
                                                                                                                                                                            while (true) {
                                                                                                                                                                                switch (str76.hashCode() ^ (-389851319)) {
                                                                                                                                                                                    case -1348749881:
                                                                                                                                                                                        str37 = "ۢۘۨۢۤۤۙۧۥۘۜۙ۫ۗۧۧۛۦۨۘۡۙۨۚۤۛ۫ۧ۠ۤ۟ۙۚۗ۬۠ۗ";
                                                                                                                                                                                        break;
                                                                                                                                                                                    case -824240837:
                                                                                                                                                                                        str37 = "ۗ۠ۦۗۧۜۘ۟۫ۡۘۜ۬ۙۙۧۖۘۗۘۡۘۦۢۥۘۧ۠ۛ۫ۦۥۨۨۡۘ";
                                                                                                                                                                                        break;
                                                                                                                                                                                    case 767269655:
                                                                                                                                                                                        str76 = "ۚ۬ۜۘۗۧ۠ۙۧ۠ۨۜۜۡۛۤۦۖۨۘۘۡۖۘۥۗۘۧۦۡۘۖۥۜ";
                                                                                                                                                                                    case 1818024417:
                                                                                                                                                                                        str76 = !name.equals(jSONObjectOptJSONObject.optString(l2.decrypt("9ZVWK6BgANA=\n", "lPYiQtYJdKk=\n"), "")) ? "۬ۛۜ۠ۤ۠ۙۤۥۚۤۗۡۛۚ۫ۥۧۛۦۜۛ۟ۡۘ۠ۖۖۘۨۙۚۛ۫ۦۙۤ۬ۛۦۜۘۥ۬ۖۦۘۘۙۡۘۘۗۘۧۘۧۗۥ" : "ۤۦۘۢۙ۟ۚۥۚۦ۫ۦ۟ۚۘۡۘ۬ۦۡۘۘۤۥۖۧ۫ۛۥۦۜۛۛۜۘۛۦۡۘۗ۬۠۫ۚ۬";
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                            break;
                                                                                                                                                                    }
                                                                                                                                                                } catch (Throwable th11) {
                                                                                                                                                                    th = th11;
                                                                                                                                                                    i = length;
                                                                                                                                                                    length = i;
                                                                                                                                                                    k2.logToFloatingWindow(l2.decrypt("FVBoo7DKwiUDcn24u/XfJVTFpE0sJBOvyLo=\n", "dCAYz8mcq0A=\n") + th, l2.decrypt("99207ZQ=\n", "kq/GguauAjs=\n"));
                                                                                                                                                                    System.out.println(l2.decrypt("eQJ+YJ9ZtPFNKXxc01ejyU4/QWiWQYHcVTR+dZbTbzvH/q/uT6w=\n", "IkYXAfM207k=\n") + th);
                                                                                                                                                                    Object tag2 = view.getTag();
                                                                                                                                                                    str = "ۢۚۜۤۚۚۚۨۡۘۥۢۡۜۨۛۖ۟ۢۨ۫ۖۘۘ۟ۤۧۧۘ۫ۛۖ۫ۜۦۘۘۘۧۛ۬ۡۘۤۚۦۘۛۙۗۜۥ۟۟۬ۚۜ۫ۦ";
                                                                                                                                                                    while (true) {
                                                                                                                                                                        switch (str.hashCode() ^ 1305926501) {
                                                                                                                                                                            case -1944725694:
                                                                                                                                                                                break;
                                                                                                                                                                            case 23810657:
                                                                                                                                                                                break;
                                                                                                                                                                            case 375044190:
                                                                                                                                                                                break;
                                                                                                                                                                            case 1231189051:
                                                                                                                                                                                break;
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                    str2 = "ۧۡۧ۬ۗۨۜۗۜۜۨ۟ۖۙۡۘۜ۟ۗ۠ۖۥ۫ۙۜ۬ۦۘ۟ۗۘ۫۟ۛۛۜۘۦۢۜۨۜۦ";
                                                                                                                                                                    while (true) {
                                                                                                                                                                        switch (str2.hashCode() ^ 1200787376) {
                                                                                                                                                                            case -1979332553:
                                                                                                                                                                                break;
                                                                                                                                                                            case 1533151211:
                                                                                                                                                                                break;
                                                                                                                                                                            case 1551512613:
                                                                                                                                                                                break;
                                                                                                                                                                            case 1953006366:
                                                                                                                                                                                break;
                                                                                                                                                                        }
                                                                                                                                                                    }
                                                                                                                                                                    i5++;
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                            break;
                                                                                                                                                        case 614217022:
                                                                                                                                                            break;
                                                                                                                                                        case 1237884578:
                                                                                                                                                            str36 = "ۙ۬۟ۘۛ۬۟ۧۡۨۡۖۖۨۛ۠ۚۧ۠۠۟ۦ۬ۥۢ۫۠۬ۚۖۘۨ۬ۦۘۜ۫ۚۛۛ۬ۤۛۖۘۢۜۤۢۨۥۘۢۡۜۘۢۛۦ";
                                                                                                                                                        case 2133231458:
                                                                                                                                                            String str77 = "ۢۨۡۘۚۨۖ۫۬ۥۚۨۜۘۛۤۛۥۤۦۘۤ۬ۖۤ۠ۧ۫ۨۧۤۨۘۥ۟ۤ۫ۦۦ۠ۚۖۚۗ۟";
                                                                                                                                                            while (true) {
                                                                                                                                                                switch (str77.hashCode() ^ (-568663253)) {
                                                                                                                                                                    case -835828388:
                                                                                                                                                                        str36 = "ۜ۠ۘۘۘۧۨۗۚ۠۫۟۫ۥۛۡۘ۟۫ۦۧۡۚ۟۠ۥۧۨ۫ۤۖۥۦۡۚۢۢ۠ۖ۫ۢۚۖۖ۬ۛۡۜۨۚۗۡۘۢۢۖ";
                                                                                                                                                                        break;
                                                                                                                                                                    case -9856002:
                                                                                                                                                                        str77 = "ۚۡۙۢۚ۫ۚۨ۠ۜۤۥۗۦۨۥ۬ۥۖۖۘۡۛ۠ۘۚۦۚۙۛ";
                                                                                                                                                                    case 1016081937:
                                                                                                                                                                        str36 = "۠ۛۛۚۥۨۡ۫ۥۘ۬۠ۦ۠ۗۗ۠ۘۧۨۚۧۨۜۢۚۢۚۚ۠";
                                                                                                                                                                        break;
                                                                                                                                                                    case 1405767845:
                                                                                                                                                                        str77 = jSONObjectOptJSONObject == null ? "ۗۜۨۡ۟ۚۖ۫۠ۤ۫۠ۥۢۘۘۚۧۛۨۛۤۢۢۖ۫ۤۘۢۤۘۥۙۖۢ۟ۜۘ" : "ۗۛۦۗۨۧۛۚۨۥۨۖ۠ۗۛۛ۠ۖۛۢۖۘۦۧۘۢۖۜۘۛۡۡۘۢۛۚۦ۟ۤ";
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                            break;
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                i6++;
                                                                                                                                                break;
                                                                                                                                            case 2143342684:
                                                                                                                                                str34 = "ۢ۟ۘۘۢۤ۟ۚۢۖۘۢۢ۬۬ۡۜۢۗۗۗۜۧۘۡۡۦۢ۠ۖۦۦۗ۠ۢ۫ۢۗۖۖۨۦ۠ۤۜ";
                                                                                                                                        }
                                                                                                                                    } catch (Throwable th12) {
                                                                                                                                        th = th12;
                                                                                                                                        i = length;
                                                                                                                                        length = i;
                                                                                                                                        k2.logToFloatingWindow(l2.decrypt("FVBoo7DKwiUDcn24u/XfJVTFpE0sJBOvyLo=\n", "dCAYz8mcq0A=\n") + th, l2.decrypt("99207ZQ=\n", "kq/GguauAjs=\n"));
                                                                                                                                        System.out.println(l2.decrypt("eQJ+YJ9ZtPFNKXxc01ejyU4/QWiWQYHcVTR+dZbTbzvH/q/uT6w=\n", "IkYXAfM207k=\n") + th);
                                                                                                                                        Object tag22 = view.getTag();
                                                                                                                                        str = "ۢۚۜۤۚۚۚۨۡۘۥۢۡۜۨۛۖ۟ۢۨ۫ۖۘۘ۟ۤۧۧۘ۫ۛۖ۫ۜۦۘۘۘۧۛ۬ۡۘۤۚۦۘۛۙۗۜۥ۟۟۬ۚۜ۫ۦ";
                                                                                                                                        while (true) {
                                                                                                                                            switch (str.hashCode() ^ 1305926501) {
                                                                                                                                                case -1944725694:
                                                                                                                                                    break;
                                                                                                                                                case 23810657:
                                                                                                                                                    break;
                                                                                                                                                case 375044190:
                                                                                                                                                    break;
                                                                                                                                                case 1231189051:
                                                                                                                                                    break;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        str2 = "ۧۡۧ۬ۗۨۜۗۜۜۨ۟ۖۙۡۘۜ۟ۗ۠ۖۥ۫ۙۜ۬ۦۘ۟ۗۘ۫۟ۛۛۜۘۦۢۜۨۜۦ";
                                                                                                                                        while (true) {
                                                                                                                                            switch (str2.hashCode() ^ 1200787376) {
                                                                                                                                                case -1979332553:
                                                                                                                                                    break;
                                                                                                                                                case 1533151211:
                                                                                                                                                    break;
                                                                                                                                                case 1551512613:
                                                                                                                                                    break;
                                                                                                                                                case 1953006366:
                                                                                                                                                    break;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        i5++;
                                                                                                                                    }
                                                                                                                                }
                                                                                                                            }
                                                                                                                            break;
                                                                                                                        case 1362560405:
                                                                                                                            String str78 = "۠ۢۚۚۜۘۡۡۦ۟ۜ۠۫ۛۤ۟ۘۘ۬ۢۨۡۘۨ۬۠۫ۨۜ۟۟ۜۚۚۨ";
                                                                                                                            while (true) {
                                                                                                                                switch (str78.hashCode() ^ 1776344002) {
                                                                                                                                    case -1870444084:
                                                                                                                                        str78 = "ۘ۫ۡ۟ۧۗ۬ۢۦۤۤۖۘۗۚۙۘۚۚۡۛ۬ۙ۬ۗۜۤ۟۬ۦۜۘۤۨۜۤۧ۬";
                                                                                                                                    case -770540080:
                                                                                                                                        str78 = activityECt8jHZ4 == null ? "ۜ۫ۦ۟ۧ۫ۨۥۦ۬ۥۘۦۡۢ۬ۗۜۚۙۤ۫ۗ۬ۘ۬ۦۖۛ۠ۛۦۛۧۦۢ۬ۛۢۙۜۘۘۦۜۘ۬۫ۜ" : "ۜۡۤ۟۟ۗ۬ۧۨۛۘۖ۫ۛۜۢۢۦۘۛۥۖ۠ۚ۠ۥۖۘۚۡۚۗۡۧۖۚۨ";
                                                                                                                                    case 659535205:
                                                                                                                                        str33 = "۟ۜۡۙۨ۬۟ۜۜۘۦۤۜۘۙۤۖۘۘۜ۫ۜۨۖۖۧۡۘۡۙۜ۬ۚ۫";
                                                                                                                                        break;
                                                                                                                                    case 1007764059:
                                                                                                                                        str33 = "ۗ۫ۨۘۧ۟۬۟ۗۥۘۢۙۗۗۥۦۜ۫۫ۧ۠ۖ۫ۥۘۙۨ۬ۤۙ۫ۘۢۗۨۚۘۘۖۘۨۤۥۙۛۡ۠ۚۚۨۘۗۚۖۘۤۗۥ";
                                                                                                                                        break;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            break;
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            case 1566944207:
                                                                                                                str31 = "ۡۦۘۘۜۘۡۗۦۤۚ۬ۘۛۜۥ۫ۨۚۡۦۨۘۛۛۧۜۜۖۘۡۧۜۤ۫۫ۥۛۚ۫ۖۚ۬ۖۦۘ";
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                }
                                                                            }
                                                                        } catch (Throwable th13) {
                                                                            th = th13;
                                                                        }
                                                                        break;
                                                                    case -356699142:
                                                                        str3 = "ۨۜۜۧ۫۬۬ۚ۟ۨۘۥۘۦ۟ۡۥ۠ۨۘۦۢۢۤ۬ۗۥ۠ۜۖۗۢۧۢۖۘۨۡۛۚۘ۬ۤ۫۫۟ۙۧۥۦۨۘ";
                                                                    case 358206644:
                                                                        break;
                                                                    case 1274683092:
                                                                        String str79 = "۟۫۟ۡۨۘۘۛۧۘۙۡۖۘۚ۬۫۟ۦۗۛۖۢ۫ۨۥۘۥ۬ۚۤۘۦۘۜۛۘۘۤ۠ۦۘ";
                                                                        while (true) {
                                                                            switch (str79.hashCode() ^ (-1745862312)) {
                                                                                case -1796171416:
                                                                                    str3 = "ۡۥۖۘ۬ۢۜۛۖۧۘۢ۫۫ۚۡۦۘ۟ۛۦۘ۟ۗۥۘۥۥۜۘۙۢۛۘۡۢ۬ۖۚ۟ۜ۟ۨۖۧۘ۟ۙۛ";
                                                                                    break;
                                                                                case -765737651:
                                                                                    str79 = "ۚۗ۬ۧۚۖۤ۠ۖۘۧۘۦۡۖۚۛۧۤۦۤۙۗۛۨۘۚۘ۬ۛۧ۠ۥۦۥۖۛ۟۠ۢۛ۟ۚ۬ۧۦۡۧۙۗ";
                                                                                case 628539787:
                                                                                    str3 = "ۤ۠ۤۗۧۧۗۚۤ۠ۢۢۨۗۛۜ۟ۢۦۢۛۦ۠ۥۙۤۖۘۤۜۚۗۧۚۥۧۜۖۢۙۙۦۙ۬ۤۡۤۘۙ";
                                                                                    break;
                                                                                case 2051040218:
                                                                                    str79 = jsonResult == null ? "ۥ۠ۜۘۧۘۘۛ۠۫ۤۤۘۤۘۤ۫۠ۦۘۗۨۘۦۢۚۙ۠ۤۡ۬ۦۙ۠۟ۜ۟ۦ" : "ۤۙۧۢۨ۠۟۫ۨۘ۟ۥۜۙۖۖۘۚۙۗۙۖۧۜۘۢ۟۫ۤۥۡۙۚۜۦۘۧۤ۬";
                                                                            }
                                                                        }
                                                                        break;
                                                                }
                                                                Object tag222 = view.getTag();
                                                                str = "ۢۚۜۤۚۚۚۨۡۘۥۢۡۜۨۛۖ۟ۢۨ۫ۖۘۘ۟ۤۧۧۘ۫ۛۖ۫ۜۦۘۘۘۧۛ۬ۡۘۤۚۦۘۛۙۗۜۥ۟۟۬ۚۜ۫ۦ";
                                                                while (true) {
                                                                    switch (str.hashCode() ^ 1305926501) {
                                                                        case -1944725694:
                                                                            break;
                                                                        case 23810657:
                                                                            str = "ۜۢۗۢۖۦۥۘۨ۬ۡۜۨۜۗۚۡۡ۬۬ۘۘۡۚۛۦۨۥۘۘۥۜۘۙ۠ۖۚۜۘۘ";
                                                                        case 375044190:
                                                                            String str80 = "۬ۖۗۨۘۜۘۛۡۨ۟۟ۙ۫ۤۚ۟ۥۥۘۦۨۧۦۡ۫ۦۖ۬۬ۨۖۘ";
                                                                            while (true) {
                                                                                switch (str80.hashCode() ^ (-78929871)) {
                                                                                    case -1858986828:
                                                                                        String str81 = "۬ۤۦۘ۟ۢۙۜۙۙۗۦۜۘۡ۠ۗۢ۟ۜۜۖۚۤۗۥۙۨۧۡ۟ۨ";
                                                                                        while (true) {
                                                                                            switch (str81.hashCode() ^ (-159330186)) {
                                                                                                case -1847572578:
                                                                                                    str80 = "ۤۙۜۘۜ۠ۡۘۖۜۧۥۧۥۘۖ۬ۗۖ۬ۡۙۡۘ۫ۨۘۙۦۙۤۥۢۥۢ۬ۘۘۜۢۚۛۡۥ";
                                                                                                    break;
                                                                                                case -995189366:
                                                                                                    str81 = "ۤ۬ۧۢۦۧۜۧۗۛۛ۬ۖ۠ۖۘۤۛۖۘۧۜۙۢۘۖۘۦۤۡ۫ۢ۠ۗۡۚۖۗۖۘۜۧۘۗۧ۫";
                                                                                                case -646847696:
                                                                                                    str81 = ((String) tag222).contains(l2.decrypt("NJyzUBY9sOoogLNfDgek\n", "R/TWPHpiwJg=\n")) ? "ۗۦۥۘ۠۟ۜۤۦۤۗۢۘۘۚۚۧۘۤۧۦۧۦۘۤۛۚۧ۟ۙ۬ۢۡۘۤۨۥ۠ۖۚۨۥۨۘ۠ۖۚۘ۬ۙ۠ۧۤۜۧۨۘۜۜ۟" : "۫ۘۦۢۡۙۡۖۤۢۡۗۛۚۧۖۤۛۡۢۨۘۘۥ۬ۦۗۗۖۛ۟ۦۖۡۦ۫ۘ۠ۢ۟ۛۤۤۦ۟ۧۚۨۦۗۨۥۜۘ۬";
                                                                                                case -635577233:
                                                                                                    str80 = "ۚۙۘۘۗ۠ۢ۬۟۠ۧۧۘۛۥۜۢۡ۟ۦۙۜۘۤۧۘۙۢۜۘ۟ۗۥ۫ۤۛۛۗۙۙۧ۟۬۠۟";
                                                                                                    break;
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case -1302302592:
                                                                                        break;
                                                                                    case -31914057:
                                                                                        str80 = "ۤۗۙۦۨۦ۫ۗۛ۠ۦ۫ۦۛ۟ۡۗۛ۬ۘۧۘۘ۠ۘ۠ۢ۫ۗ۠ۧۖۧ۠ۦۛ۬ۧۢۙۨۚ۟ۥ۠ۜۘۘ";
                                                                                    case 830237019:
                                                                                        break;
                                                                                }
                                                                            }
                                                                            break;
                                                                        case 1231189051:
                                                                            String str82 = "ۖۘۥۙۖۛۘۛۜۘۛ۠ۢ۬ۙۘۗ۠۟ۡۦ۫ۤۗۦۢ۟ۘۘۥۜۨۘۚۜۨۘ۟ۜۨۧۘۘۡۨ۠ۜۜۘۙۧۛۤۙۙۙۨۜۘ";
                                                                            while (true) {
                                                                                switch (str82.hashCode() ^ 1530465863) {
                                                                                    case -1639618360:
                                                                                        str = "۫۬ۜ۬ۜ۟ۡۡۤ۠ۧۚۛۘ۟ۡۨۙۨۗۛ۠ۚۜۘۖۘۧۘۧۘۤۚۢۖۘۖۥ۠ۨۧۥۖۚۥۘ";
                                                                                        break;
                                                                                    case -1493306613:
                                                                                        str = "ۨۙۛ۬ۨۥۘۧۙۡۘۖ۬۟ۙۙۘۧۛۖۚۧۖۘۜ۠ۘ۫ۡۧۘ۟۟ۛۧۡۡۤۡ";
                                                                                        break;
                                                                                    case -1358758807:
                                                                                        str82 = tag222 instanceof String ? "۠ۘۗۖۘ۟۟ۥ۟ۡۦۘۘۡ۫ۨۡۜۘ۫ۦۡۥۘ۬ۡۗۖۘۗ۟ۨۥۢۨۘۦۨۤۧۖۧۨۧۜۘ۟ۧۘ۟ۥۨۘۛۛ۟ۥۚ" : "ۢۙۙ۟ۧۦۘۖۚۡۘۤۖۙۨۖۤۚۦۘۘۧۜۗۨۙ۠ۘ۬ۘۘۙۖۦۘۘۨۜ۟۟ۘۢۧ۬ۧ۬ۡۘۘۦۨۘۤۡ۟";
                                                                                    case -520095963:
                                                                                        str82 = "ۨۤۦۡۨ۬ۚۧۨۜۡۘۘۦ۟ۖۘ۬ۖۛ۬ۙۜۥ۫ۙۥۗۢۛ۬ۘ";
                                                                                }
                                                                            }
                                                                            break;
                                                                    }
                                                                }
                                                                str2 = "ۧۡۧ۬ۗۨۜۗۜۜۨ۟ۖۙۡۘۜ۟ۗ۠ۖۥ۫ۙۜ۬ۦۘ۟ۗۘ۫۟ۛۛۜۘۦۢۜۨۜۦ";
                                                                while (true) {
                                                                    switch (str2.hashCode() ^ 1200787376) {
                                                                        case -1979332553:
                                                                            int i7 = ((WindowManager.LayoutParams) view.getLayoutParams()).type;
                                                                            String str83 = "ۙ۟۠ۜۘۧ۫ۚۥۜ۬ۜۘۗۤ۠ۤۢۜۛۜۜۘۥۤ۫ۦۥۖۘۥۧۙۙۥۤۗۙ۬ۧۙ۬ۦۛۜ۟ۢۡۘۤۦۘۨۢ۠ۚۛ۬";
                                                                            while (true) {
                                                                                switch (str83.hashCode() ^ (-1389712871)) {
                                                                                    case -1803700901:
                                                                                        break;
                                                                                    case -712033405:
                                                                                        str83 = "ۛۚۥۥ۟ۨۥۛۨۘۡۛۡۘ۬ۧۡۘۦۚ۠ۛ۬ۦۘۨۨۦۘۚۛۢۚ۟ۜ";
                                                                                    case 566061062:
                                                                                        String str84 = "۟ۡۢۢۜۥۗۢۦۘۜۦۗۤۤۘۡۚۦۘۖۧۦۘۜۘۡۘۘۛۘۘۖ۟ۜۘۢۜ۬ۡۥۦۘ";
                                                                                        while (true) {
                                                                                            switch (str84.hashCode() ^ (-838417555)) {
                                                                                                case -235306296:
                                                                                                    str83 = "ۘ۬۬ۧۜۨۘۛۗۜۘۘۗۥۦ۠ۢۡۘۨۧۡۖۘۜۜۖۜۤۗ۬ۗ۬۬ۚۖ۬ۡۚۚۤۛۗۤۦ۬ۙۖۖۥۦۢۖۘۡۘۘ";
                                                                                                    break;
                                                                                                case 278966790:
                                                                                                    str84 = "ۦۦۚۜۤۥۘۦۥ۬ۛۗۙ۠ۖۘ۠ۨۘۡۡۛۡۗۘۧۗۦۗۥۜۖۙۧۡۛۥ";
                                                                                                case 1845154305:
                                                                                                    str84 = zOptBoolean2 ? "ۘۧ۫ۙ۟ۛۢۘ۟ۛ۫ۜۘۡۤۥ۟ۚۡۘ۠ۖ۠۫۫ۦۘ۟ۡۤۛۦ۟ۨۢۗۡۘۗ۬ۚۖۚ۫۫" : "ۜ۟ۘۘۢۦ۬ۖ۬ۘۘ۟ۖۢۘۤۡۚۧۗۡۚ۫ۗۙۖۨ۬ۖۘۜۤۡۛۜۙ۟ۖۖۘ";
                                                                                                case 2066010516:
                                                                                                    str83 = "ۛۥۢۢۡۥۘ۬ۡۡۘۛ۫ۨۘۡۢۘۢۨۦۘ۫۫ۖۡۦۥ۫ۖۚۘۚۘ۬ۘۘ۟ۥۧۥۢۨۘۛ۬ۥۘۧ۠ۗۚۜۘۙ۬ۦۘ۫ۘۘۘ";
                                                                                                    break;
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case 1133429919:
                                                                                        String str85 = "۠ۥۧ۫ۧۤۤ۠ۤۢۧۨۘ۠ۧۨۘۨۡۜۖ۫ۘۘۙ۬ۢۛ۫ۙۦۜۢ۟ۛ۠ۖۗۨۙۗۤۤۘۘۡۗۡۘۡۘۘۘ";
                                                                                        while (true) {
                                                                                            switch (str85.hashCode() ^ (-1976887591)) {
                                                                                                case -524561294:
                                                                                                    break;
                                                                                                case 33439892:
                                                                                                    String str86 = "ۙ۫ۚ۬ۙۘۘ۫ۤۥۘۧۘ۬ۚ۫ۘۘۢۥۦۘۗۚۨۥۨۧۘۖۛۛۡ۫ۥۦۢ۟ۖۖۡ۫ۧۡۗ";
                                                                                                    while (true) {
                                                                                                        switch (str86.hashCode() ^ 973034017) {
                                                                                                            case -1281203816:
                                                                                                                str85 = "ۗۖۦۘۙۚۥۚۢۖۙۡۖۘ۬ۦۡۘۛۨۗۤۡۥ۠ۥۖۡۜۛۜۡ۫ۖۨۙۛ۟ۖ۫۫ۥۙۗۧ";
                                                                                                                break;
                                                                                                            case -520634641:
                                                                                                                str86 = hashSet4.contains(Integer.valueOf(i7)) ? "۟ۤ۟۫ۧۤ۫۫ۨۛۥۡۦ۫ۨۘۜۜۖۖۨۜۘۜۘۖۡۨ۬ۥۤۡۖۛۡ۫" : "ۚۧۧۚۙۨۘۨۘۦۤۡۥۘۨۦۜۘۡۚۡۘۨ۫ۖۘۜۛۦۘ۫ۖۘ۬ۢۛۧ۟۫ۜۦۢ";
                                                                                                            case 44841531:
                                                                                                                str85 = "ۘۚ۟ۦۗۧۘۛۚ۟۠ۢۢ۫۫ۛۥۘۗۥ۟ۦۛۜۗۡۗۦۛۖۘۜ۬ۘۘۜ۬ۨۡ۠ۘ۫۬ۘۘۜۛۡ۟ۧۤ";
                                                                                                                break;
                                                                                                            case 173652021:
                                                                                                                str86 = "ۧۡۨۘۙۘۘۖ۬ۡۤۢۘۘ۬ۧۘۗ۫ۢ۬۬ۖۘ۬۫ۨۧ۬ۘۘۙۗۥ۬ۤ۠ۦۤۙۛۥ۫ۖۧ";
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case 943911115:
                                                                                                    str85 = "ۨۗ۬ۚۘ۠ۡ۠ۦ۟ۘۖۘۨ۬ۘۦۛۥۘۢۙ۟ۜ۬۟ۜۘۖۦۨۘۗۘ۬ۧۨۧۢۘۡ۫ۦ۠۠ۦ۟ۢۙۚ";
                                                                                                case 1002406872:
                                                                                                    a(view, l2.decrypt("p6wxex/38iM3XM6g\n", "TiyrnYJ30lc=\n") + i7);
                                                                                                    continue;
                                                                                                    continue;
                                                                                                    continue;
                                                                                                    continue;
                                                                                                    continue;
                                                                                                    continue;
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                }
                                                                            }
                                                                            String str87 = "ۢۡۜۘۗۖۦۦۙۖۧۚ۠۟ۚۢۚۦۙۡۦۥۘۙۚۨۘ۠ۘۥۘۧۧۘۤۛۤ۫ۗۨ";
                                                                            while (true) {
                                                                                switch (str87.hashCode() ^ 54856115) {
                                                                                    case -2125289532:
                                                                                        String str88 = "ۨۖۦۘۘۜۡۚۘۖۘۗۜۘۢۛ۬ۜۦۖۘۧۗۗۥ۫ۘۤ۠ۜۘ۟ۤ۟ۨ۟ۡۘۤ۫ۡۘۢۘۥۥۢۤ";
                                                                                        while (true) {
                                                                                            switch (str88.hashCode() ^ 349756072) {
                                                                                                case -156516006:
                                                                                                    str88 = zOptBoolean ? "ۥۦۧۘ۬۬ۤۦۡ۠ۖۖۥۘ۬ۦ۟۫ۤۤۙۢ۟۟ۢ۠ۥۘ۟ۦۢۖ۠ۥۨ۠ۧۢۥۙۦۤۡۨۡۘۡۘۧ۬۬۬ۗۥ" : "ۥۛۨ۠ۦۧۘۤۤۙۦۛۡۖۨۘۨۘۦۢۗ۠۫ۚۙۢۦۜۘۘۖۢۤۜۨۘ۟ۗ۟";
                                                                                                case 382065422:
                                                                                                    str87 = "ۢۢۘۨ۠ۘۘۘۧ۫ۡ۠ۦۗۦۨۥ۫ۖۨۘۨ۫ۙۢۢۛۘۦۡۦۙۧۖۙۢ";
                                                                                                    break;
                                                                                                case 592425126:
                                                                                                    str87 = "ۘۗ۫۬ۚۡۘۡۛۨۡۘۜ۠ۧۨۘ۫ۡۖۘ۬ۜۦۘۦۨۧۡۜ۫ۘۖۘ۫ۖۘۥ۟۫۬ۦۡۤۥۘۦ۫ۤ۫۟ۤۤ۠ۚ۠۫";
                                                                                                    break;
                                                                                                case 1327731003:
                                                                                                    str88 = "ۛۚ۠ۨۖۨۘۧۨۦۘ۬ۚۘۚۖۨ۬ۗۧۡۛۙ۟ۛ۫۬ۥۧۘۨۜ۠ۥۜۘۥۖۖ";
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case -765145756:
                                                                                        break;
                                                                                    case -507608750:
                                                                                        String str89 = "۠ۖۛۛۚ۠ۨۛۦۙۛ۫ۗۡ۠ۡ۠ۜۡۥۥۘۨۨۘ۫۬ۚۨۚۢ";
                                                                                        while (true) {
                                                                                            switch (str89.hashCode() ^ 1181565469) {
                                                                                                case -276371212:
                                                                                                    String str90 = "ۧ۠ۛۥۡۧۛۥۤ۫۫ۥ۠ۛۦۘۨۘۗۨۘۘۙۨۡ۬۬ۜۡ۠ۜۗۛۨۘۦۜۧ";
                                                                                                    while (true) {
                                                                                                        switch (str90.hashCode() ^ (-570592211)) {
                                                                                                            case -1315051199:
                                                                                                                String str91 = "ۛۢۚۚۤۧۦۜۘۗۧۨۘ۟۬ۖۥۛۘۖۥۡۨۚۜۚۤ۬ۦۜ";
                                                                                                                while (true) {
                                                                                                                    switch (str91.hashCode() ^ 2018539546) {
                                                                                                                        case -2139843243:
                                                                                                                            str91 = "ۨۨۧۘ۫ۡۚۛۛۗۡۢۘۡۦۙۡۨۤۡۘۖۛۘۥۚ۠ۜۧۖۘۦۦ۠ۜۘ۟ۜ۬ۥۘ۫ۖ۠ۤۤ۬ۤۛۥۘ";
                                                                                                                        case -1594850491:
                                                                                                                            str90 = "ۙۥۘۘۤ۫ۜۗۨۨۘۥ۬ۗۢۛۘ۟ۧۙۜۡۥۙۛۗ۠ۗۗۥ۬ۧۢۤۤۤۦۘ۠۠ۛۢۖۨۘۛۢۨۤ۠۬ۙۖۗۜۛۦۘ";
                                                                                                                            break;
                                                                                                                        case 1199848405:
                                                                                                                            str91 = b(view, hashSet2) ? "ۘۚۥۘۡۛۨۨۚۚۧ۠ۜۘۤ۟ۥۘۧۤۙۚۡۧۘۜۧۧۜۦۚ۬ۖۖۚۡۚۤۚۢ" : "ۘۘۡۘ۬ۛۛۢۨ۫ۤۨۜۨۖۜۗۦۧۦ۬ۘۘۗۥۢۜۨۘۖۚۜۨۦۜ۬ۢۦ۬ۨۘۘ۬ۙۡ۬ۡۙۦۥۖ۫ۖۛۡ";
                                                                                                                        case 1208011928:
                                                                                                                            str90 = "ۢ۠ۙۦۥۛ۟ۘ۟۫ۤۤۚ۟ۖۘۧۤۦ۬۠۠ۥۢ۟ۤۧۜۧۤۧۗۗۜۘ۬ۜۜ۠ۦۘۥۧۥۦۚۧۘ۫ۖۤۗ۟ۥۚۦ";
                                                                                                                            break;
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            case -752527053:
                                                                                                                str90 = "ۖۢ۠۟ۖۚۚۥۨۛۖۖ۫ۤۥ۫ۙۥۘۤ۟ۦۙ۟ۗۧ۟ۗۧۚۦۘۧۢۡۡۦۘ";
                                                                                                            case 1021587302:
                                                                                                                k2.logToFloatingWindow(l2.decrypt("kMwd+E0n5UKJxyeQwFWFYN23MdiCfvoT4M8=\n", "YFOJdW3CYPE=\n") + hashSet2 + l2.decrypt("qtQJISbUacP6sQhmffIbkcf3cUkgvwLErd0ZKBH+avzi\n", "SFSUzppYjHQ=\n"), l2.decrypt("BZFQxRE=\n", "aPAkpnlEbNw=\n"));
                                                                                                                StringBuilder sb2 = new StringBuilder();
                                                                                                                sb2.append(l2.decrypt("N+MRYHjVT95fgzM0CMMKUaYf0uzR\n", "0maiiex7p3E=\n"));
                                                                                                                sb2.append(i7);
                                                                                                                a(view, sb2.toString());
                                                                                                                break;
                                                                                                            case 1157190462:
                                                                                                                break;
                                                                                                        }
                                                                                                    }
                                                                                                    String str92 = "ۖۚۘۢۜۥۛ۠ۖۧۡۘۥ۫ۙ۬ۙۦۗۘ۠۠ۨۨۘۥۙۦۤۥۧۡۙۛۨۗۖۡۚ۬ۚۘۡۘۘۗۥۜۜۘۘ";
                                                                                                    while (true) {
                                                                                                        switch (str92.hashCode() ^ 1325419689) {
                                                                                                            case -2042719732:
                                                                                                                Utils.traverseViews(view, new k(0, jSONArrayOptJSONArray2));
                                                                                                                continue;
                                                                                                            case -1502773033:
                                                                                                                continue;
                                                                                                            case 70579178:
                                                                                                                String str93 = "ۖۜۘۘۨ۫۠۠ۘۥۥۚۡۦۦۡۚۡۘۨۛۜۙۖۥۘۘۗ۟ۡۡۨۘ";
                                                                                                                while (true) {
                                                                                                                    switch (str93.hashCode() ^ 1572357362) {
                                                                                                                        case -2140112881:
                                                                                                                            str93 = jSONArrayOptJSONArray2 != null ? "ۜۡۨۘۚ۟۬ۛۧۧ۬ۥۘ۟ۧ۟ۧۦۘۤ۫ۨ۟ۥۧۘۥۖۘۧۢۛۜۚۗۛۥ۟۬۬ۚۧۡۡۖۚۘۨۧ۫" : "۠ۧۨۘۛۦۥۘ۬ۘۙۙۨۛ۟ۛۘ۠ۧۗۖۡۤ۠ۧۧ۟ۤۦ۫ۥ۠ۥ۫ۦ۟ۤ۟۫ۥۡۗۚۨۘۗۗ۬۬ۧ۟۠ۧۚۘ۠ۨۘ";
                                                                                                                        case -1484464521:
                                                                                                                            str92 = "۫ۧۙۦ۟ۦۘۢۜ۬ۦۥۘۡ۬ۥۘۙۤ۠ۛۚ۫ۤۦۗۡۧۡۖۢۧۦۖۧۖۧۧۛ۠ۥۙ۫ۢ۬ۨۧۘ۟ۡۖ";
                                                                                                                            break;
                                                                                                                        case -512113355:
                                                                                                                            str92 = "ۦۢ۟۠۬ۦۘۛ۬ۦۘۨۛۡۘ۫ۙ۟ۚۦۚۜۨۙۛۚۡۡۤۜۘۡۚۢۙۥۡۘۛۖۗۙۨۘۜۚۥۘۥ۫۟۬ۜ";
                                                                                                                            break;
                                                                                                                        case -378414040:
                                                                                                                            str93 = "۟۠ۤۛ۠ۙ۬ۥۨ۟ۜۗۛۜۛ۬ۛۢ۟ۗۨۘۖۥۥۧ۠ۡۧۙۦ";
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            case 858064462:
                                                                                                                str92 = "ۗ۟ۙۜۡۜۘۤۙۢ۫ۜ۟ۨۦۖۘۙۛۖۘۧۤ۫ۜۜۦۨۗۘۤۤۛۡۛۗۧۜ۫ۛۧۜۘۡۤ";
                                                                                                                break;
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case 285941289:
                                                                                                    continue;
                                                                                                case 1771628258:
                                                                                                    String str94 = "ۡۖۜۛۥۦ۟ۗۤۚۘۙۢ۬ۗۚۢۥۛۛ۟۬ۛۨ۫ۥۤۤۢ۟";
                                                                                                    while (true) {
                                                                                                        switch (str94.hashCode() ^ 1579161847) {
                                                                                                            case 94693372:
                                                                                                                str94 = "ۢۖۘۘۗۘۛۖۚۡۤۜۤ۠ۢۤۜۡۨۘۜۛۜۘۗۘۖۡۘ۬۟ۗۛۢۛۨۢۤۢۥۨۗۙۦۜۤ۫ۨۘۡۗۜۘۧۘۖۘۧ";
                                                                                                            case 1105308978:
                                                                                                                str94 = hashSet3.contains(Integer.valueOf(i7)) ? "ۧۤ۠۟ۢۙۗۛۘۖ۠ۘ۬۟ۥۧۡۥۖۨۡۘۖۢۦۚۢۜۤ۬ۖۘۛۜۘۢۙۨۘ" : "ۧ۠ۦۘۘۜۦۙۜۦۘۧۥۘۘۖۜ۟۠ۥ۬ۧۚۨ۬ۧۨۨۛۦ۫ۜۜۖ۟ۨۖۜۘۗۦۨۦۛ";
                                                                                                            case 1684032366:
                                                                                                                str89 = "ۨۥۤۤۢۤۚۤۚۡۗ۠۟ۧۘۘۧۜۨۘۡۨۚۦۛۥ۫۬ۛۘۘۢ۬۠ۚۨۚۦۘۡۛۦ۬ۥۡ";
                                                                                                                break;
                                                                                                            case 1743939428:
                                                                                                                str89 = "ۡۘۥۜۥ۫ۦ۫ۦۙۙۖۦۛۖۘۡۤۡۘۢۨ۬ۗ۫ۡۘۙۧۧ۫ۙۙۦۨۥۛۥۘ";
                                                                                                                break;
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case 2042924166:
                                                                                                    str89 = "ۡۜۥ۬ۜۘۙ۬ۗۗۡۜۥ۠۟ۧۥۖۗۗ۫ۚۗۥۘۥۧۨۤۜۘۡۖۥۧۦۧۘۗۧۜۗۢۗۥۧ۬۫ۥۢ";
                                                                                                    break;
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case 85251389:
                                                                                        str87 = "ۦۥۘۖۧۡۘ۬ۗۨۘ۟ۘۚۧۖۧۗۦ۬۟ۥۦۘۘۙۨ۟ۦۘۦۗۥۜۧۖۥۤ۫۫ۥ۬۬ۛ۫ۧۧۖۘۗ۬۫";
                                                                                        break;
                                                                                }
                                                                            }
                                                                            break;
                                                                        case 1533151211:
                                                                            String str95 = "ۙ۟ۨۥ۬ۢۘۚۥۘ۫ۖ۠ۖۖۛۢۜۚ۟ۤۜۙۜ۬ۗۘ۟ۙۥۨۘۧ۠ۥۘۛ۠";
                                                                            while (true) {
                                                                                switch (str95.hashCode() ^ (-1700536710)) {
                                                                                    case -1347860585:
                                                                                        str95 = !(view.getLayoutParams() instanceof WindowManager.LayoutParams) ? "ۡۧۚۛ۠ۙۖۥۜ۟ۖۧۘۥۙۢۤۖ۫ۛۨۜ۬ۜۗۨۤۢۨ۬ۘ۠ۡۤۦۡۨ" : "ۜۢۖۘۨۖۘ۟ۦۥۘ۟ۜۦۜۧۥ۠ۨ۟ۡۥۚۘۘۥۘۦۨۜۧۘۥۧۚۗۥۥۖۧۗۥۥ۬ۖۤۜۘ۬۟ۨۘۖۗۢ۬ۦ۫";
                                                                                    case -350195660:
                                                                                        str2 = "ۧ۬ۧۡ۬ۦۦۨۛۙۤۜۘۙۚ۟ۢۡۘۜ۫۟ۨۜۗۨۘۗۜۧۜ۫ۧۘۘۗۜۧ";
                                                                                        break;
                                                                                    case 170537354:
                                                                                        str95 = "ۗۖۖۚۜۜۘۛۥۘۥۙ۫ۙۖۜۜۘۘۨۦۘ۫ۡۖۙۦ۬ۡۡۛۙۗۛۦ۬۠";
                                                                                    case 426424256:
                                                                                        str2 = "ۛ۬ۙۗۤۨ۬۟۠ۤۘۚ۫۬ۖۘۧۛۜۛۜۘۖۘۢۜۜۘ۠ۤۨۘۜۤۥ۫ۚۘۘۚ۠ۢۡۨۧۘۧۨۢۥۡ۠";
                                                                                        break;
                                                                                }
                                                                            }
                                                                            break;
                                                                        case 1551512613:
                                                                            str2 = "۫ۥۧۧۗ۟ۨۨۜۘ۬ۦ۟ۗۡۜۤۖۜۘۖۗۡ۟ۖۨۘۤۖۜۦۘۜ";
                                                                            break;
                                                                        case 1953006366:
                                                                            break;
                                                                    }
                                                                }
                                                            }
                                                            jSONArray = jSONArrayOptJSONArray2;
                                                            hashSet = hashSet4;
                                                            jSONArrayOptJSONArray2 = jSONArray;
                                                            hashSet4 = hashSet;
                                                            Object tag2222 = view.getTag();
                                                            str = "ۢۚۜۤۚۚۚۨۡۘۥۢۡۜۨۛۖ۟ۢۨ۫ۖۘۘ۟ۤۧۧۘ۫ۛۖ۫ۜۦۘۘۘۧۛ۬ۡۘۤۚۦۘۛۙۗۜۥ۟۟۬ۚۜ۫ۦ";
                                                            while (true) {
                                                                switch (str.hashCode() ^ 1305926501) {
                                                                    case -1944725694:
                                                                        break;
                                                                    case 23810657:
                                                                        break;
                                                                    case 375044190:
                                                                        break;
                                                                    case 1231189051:
                                                                        break;
                                                                }
                                                            }
                                                            str2 = "ۧۡۧ۬ۗۨۜۗۜۜۨ۟ۖۙۡۘۜ۟ۗ۠ۖۥ۫ۙۜ۬ۦۘ۟ۗۘ۫۟ۛۛۜۘۦۢۜۨۜۦ";
                                                            while (true) {
                                                                switch (str2.hashCode() ^ 1200787376) {
                                                                    case -1979332553:
                                                                        break;
                                                                    case 1533151211:
                                                                        break;
                                                                    case 1551512613:
                                                                        break;
                                                                    case 1953006366:
                                                                        break;
                                                                }
                                                            }
                                                            break;
                                                    }
                                                }
                                                i5++;
                                                break;
                                            case 1024001992:
                                                return;
                                        }
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case -1185834644:
                    str4 = "ۤۢۡۘ۟ۤ۠۫ۨۡۘۧ۬ۖۘ۟ۜۦۜۦۘۤۥۖۘۢۚۥۥۧۡۤۗۜۘۧۨۘ۟۬ۢ۫ۚۘۘۨ۠ۘۢۘۡۧۛۖۘۧ۠ۜۘ۟۠۟";
                    break;
                case 802551266:
                    String str96 = "ۘۢ۬ۡۜ۠۟ۗۤۧۘۚۨۖۡ۫ۙۨ۟ۘۘۘۜۛۖۖۨۘ۫۬ۦۘۧۨ۬ۘۗۢ۠ۙۧۘۡۙۗۜۡۘۙۥۘ۠۬ۦۘۦۙۚ";
                    while (true) {
                        switch (str96.hashCode() ^ 149663203) {
                            case -382542826:
                                if (jsonResult2 != null) {
                                    str96 = "۟ۖۢۘۡ۬۟ۥۘ۫۟ۧۖۖۨۡۨۖۘۜۨۜ۟ۥۜۘ۠ۨۦۚ۟ۗ";
                                    break;
                                } else {
                                    str96 = "ۡۨۡۘۦۥۗۙ۬۫ۢۨۤۘ۫ۢۦۚۥۛۛۛۨۗ۬۠ۖۘۡۡۧۡۨ۫ۢۜۙۥۦۥۘۜۨۜۘۥۙۚۘۨۦۘ۬ۦ۟ۜۗۧ";
                                    break;
                                }
                            case 926591245:
                                str4 = "۬ۖۛ۠ۖۘۘۖۤۤۗۤۜۘۨۜۦۘ۟ۧۢۨۖۗۧ۠ۗۥۗۨۖۚ۫ۖ۬ۥۘۖۦۛۧ۫ۢ۫ۧۘۘۤۚۘۘۘۙ۠";
                                continue;
                            case 1510507500:
                                str96 = "۠ۨۜ۬۟ۡ۬ۛۖۛۚۗۜۢۥ۫ۧۨ۫ۙۘ۬ۤۢ۠ۘۧ۫ۧۢۛۥۛۦۢۦۘ";
                                break;
                            case 1850654419:
                                str4 = "ۜۧۙۙ۟۫ۧۡۘۗ۠۟ۜ۟۟ۤۘۜۥۗ۫ۥۨۥۜۜۦۘۧۗۡ";
                                continue;
                        }
                    }
                    break;
                case 1388411462:
                    return;
            }
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:34:0x007c, code lost:
    
        c();
     */
    /* JADX WARN: Code restructure failed: missing block: B:37:0x0086, code lost:
    
        r0 = move-exception;
     */
    /* JADX WARN: Code restructure failed: missing block: B:38:0x0087, code lost:
    
        java.lang.System.out.println(core.pro.android.notify.l2.decrypt("YNM5LXWs4nhU+DsROSYAg9ID/qS2TmCUv3DAyvx/B9WDL7/wgw==\n", "O5dQTBnDhTA=\n") + r0);
     */
    @Override // java.lang.reflect.InvocationHandler
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public Object invoke(Object obj, Method method, Object[] objArr) throws Throwable {
        String name = method.getName();
        String str = "ۤ۬ۖۙۙۦۘۘۨ۟۠ۛۡۘ۠ۤۘۘۥ۟ۥۘۙۜۥ۬ۛ۟ۥۨۧۢ۠۟ۤ۠ۥۤ۟۠ۛۗ۫۫ۧ";
        while (true) {
            switch (str.hashCode() ^ 767430229) {
                case -1935349849:
                    String str2 = "۫ۤۧۨۜۥۜ۫۠ۦ۠ۘ۬ۡۥ۠ۤۦ۬۟ۗۙۨۚۖۦۘ۠ۥۡۘۗۨۖۘۤۙۗ";
                    while (true) {
                        switch (str2.hashCode() ^ 1265145204) {
                            case -1285846808:
                                str2 = "ۗ۠ۤ۬ۖۥۘۧۢۘۘۘۦۨۘۚۡۘۢۨۘۨۥۘۘ۬۠ۡۘ۫ۙۡ۬ۙۥ";
                                break;
                            case -768675847:
                                str = "ۗۚۜۦۜ۫ۢۢۦۘۦ۟۟ۖۘۖۘۗۖۥۘۡ۫ۡ۫۟ۜ۠ۖۘ۠ۚۖۘ۬۫۟ۚۛۗ۠۟ۡۘۘۘۗ";
                                continue;
                            case -518602069:
                                str = "ۘۧ۠ۤ۟ۡۡ۬ۤ۬ۛۦۨۗۤ۠ۢۘۘۦ۫ۗ۫ۛ۟ۛۜۨۘ۠۟۬ۜۤ۟ۧۛۡۘ";
                                continue;
                                continue;
                            case -76632960:
                                if (!name.contains(l2.decrypt("vlmj\n", "3z3H2QXGAEA=\n"))) {
                                    str2 = "ۙۚۥۘۖۡۚۥۛۧ۬ۦ۫ۤۧۡۘۤ۟ۛۗۦۗ۟ۧۥۨ۠ۨۘۦۢ۫ۥۘۥۜۙۙۡۙۢۤ۠ۨۘۥ۠ۖۡۚۙۙۙۖۙۖۥ";
                                    break;
                                } else {
                                    str2 = "ۚ۠۠۠ۜۖۘۨۡۥۚۜۥۘ۬۠۟ۥۜۧۡۧۘۘۦۜۧ۟ۛۖۥۢۢۗ۬ۛۚۢۧۚ۟ۘۛۢۘۘۜ۬۬ۗۦۡ۠ۛۜ۟ۦۦ";
                                    break;
                                }
                        }
                    }
                    break;
                case -1360557454:
                    str = "ۚۘ۫ۥۛۘ۠ۥۢۛۛ۬ۜۦۘۢۙۦۜۙۢۙۡۚۥۖۡۘ۟ۜ۬";
                    continue;
                case -547241472:
                    String str3 = "ۢۧ۟ۘۧ۟ۛۚۘۘۗۤۜۛۖۨۙۜۥۛ۬ۨۘۚ۬ۢۖ۟۫ۚۢ۫۬ۨۘ۟۫ۨۘ";
                    while (true) {
                        switch (str3.hashCode() ^ (-338909371)) {
                            case -920904015:
                                break;
                            case -653556734:
                                str3 = "ۚۗۨۘۛۛ۬۠ۙۥۘۦۛۤۚۙۜۘۚۡ۠ۙۦۦۘ۫۬ۙ۫ۥۗ۠ۖ";
                            case 1745127730:
                                String str4 = "۟ۜ۬ۧۗۦۘۛۜۦۛۦ۟ۤۙۖۥۥۛۥ۬ۧۥۘۙۛۗۚۥۧۚ۠ۡ۟ۦۗۧۥ۟ۜۘ۫ۜۜ";
                                while (true) {
                                    switch (str4.hashCode() ^ 356994944) {
                                        case -1603596253:
                                            if (!name.contains(l2.decrypt("Pk9uO4JDyDI=\n", "TCoCWvssvUY=\n"))) {
                                                str4 = "ۨۘۧۗۜۨۧۨۖۢ۫ۘۘۢۦۛۘۙۛۥ۠ۖۘ۠ۚۧۦۗ۟ۤ۟ۧۙۤۥۘۜ۟ۤۖۡ۫ۦۡۤ";
                                                break;
                                            } else {
                                                str4 = "۫ۘۚۘۥۦۘۨ۟۟ۗۨۨۘۙ۬ۖۘۘۧۚۜۗۜۧۚۨۘ۫ۘۨۗ۠ۖۢۚۙۧۡۘۖۛۘۤ۫ۙۘ۟ۦۚۢۚۗۚۖۚۘۘۘ";
                                                break;
                                            }
                                        case -1354914854:
                                            str3 = "ۖۦۜۤۧۨۘۘۗۛۦۨۖ۬ۧۖۘۖۦۡۘۗۛۦۗۖۨۘۜۙۘۘۧ۠ۛۗ۫ۧۜ۫ۙۙۙۖۥۦۘ";
                                            break;
                                        case -932655707:
                                            str4 = "ۨۚ۬ۨۚۜ۠ۧۖۤ۠ۤۗۥۜۘۙ۠ۖۢۜۜۜ۫۬ۢ۫۟ۖ۬ۚۛۨ۠ۡۧۘۖۤۨۘۘۙۖۚۗۥۘۗۗۡۘ";
                                            break;
                                        case 1296492403:
                                            str3 = "۠ۜۖۘۨۥ۫۫ۘۨ۬ۘۙۥۗۨۨۢۥۘۙ۫۬ۡۨ۠ۡۥۦۘۚۡۘۘۦۡۙۛۡۖۦۛۨۢۦۘ";
                                            break;
                                    }
                                }
                                break;
                            case 1823744661:
                                break;
                        }
                    }
                    break;
                case 1548814137:
                    break;
            }
        }
        return method.invoke(this.a, objArr);
    }
}
