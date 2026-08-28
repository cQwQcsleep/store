package core.pro.android.notify;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Handler;
import android.os.Looper;
import android.os.Process;
import com.iqoocg.nm.fcRuQsQrcxOAzxwEalcM;
import gTBLD.dev.XSSTG.free.ProxyApplication;
import gTBLD.dev.XSSTG.free.Utils;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: /workspace/unpacked/classes2.dex */
public class t3 {
    public static boolean a;
    public static final Set<String> needFullscreenPopupIds;
    public static final Set<String> needHtmlPopupIds;
    public static final Set<String> needImagePopupIds;
    public static final Set<String> needMessagePopupIds;
    public static final Set<String> needTextPopupIds;

    static {
        String str = "ۛۗۘۘۨۗ۬ۨۥۨۘۡۜۗۛۜۧۥۧۢۢۖۛۛ۬ۛۛ۬ۥۘ۬۫ۛۦ۟ۢۦۢۘۘ";
        while (true) {
            switch ((((str.hashCode() ^ 987) ^ 278) ^ 191) ^ (-932614610)) {
                case -1608861709:
                    needImagePopupIds = new HashSet();
                    str = "ۘۡۛۙۨۨۘۙۗۥۘۦۥۙ۟ۛۘۗۤۧۖۛ۠۟ۨۚۡۥۦ۠۬ۨۜ۬۫ۗۛ۟";
                    break;
                case -717650364:
                    needFullscreenPopupIds = new HashSet();
                    str = "ۗۡۚۖ۟ۚۡۨۧۡۢۥۥۖۥۜۚۖۚ۬ۡ۠۬۬ۗ۟ۦۘۚ۬ۤۘۘۧ۫ۧۘۤۤۛۘۧۘ۫ۙۜۘۧ۫";
                    break;
                case -655175401:
                    return;
                case 553326778:
                    needHtmlPopupIds = new HashSet();
                    str = "ۥ۠ۘۘ۠ۤۜۘۦۖ۠ۢۢۥۘ۟۟ۙۘۙۘۗۧۜۜۧۤۘۡ۬ۛۛۤ۬ۛۨۘۧۛۨۚ۠ۤ۟ۗۥۛۘۥۘۘۖۜۘۚۖ";
                    break;
                case 728355850:
                    needTextPopupIds = new HashSet();
                    str = "ۖۧۘۘۘۗ۠۟۟ۢۖ۫ۚۥ۫ۚۜۦۘ۠ۙۧۧۡۡۘ۠ۧۜۘۡ۫ۧ۠ۦۘۦۖۥۘۨۢۡ۬۬ۥ";
                    break;
                case 1129610679:
                    needMessagePopupIds = new HashSet();
                    str = "ۗۥۤۚ۟ۢۚۤ۫ۖۨۜۘۗۦۘ۟ۛۖۛۧۡۡ۫۫ۢۚۛۢۤۦۘۤۖۦۘۡۛۤ۠ۚۜۘۖۥۤ";
                    break;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0035. Please report as an issue. */
    public static void EQ7YPPSJ(Activity activity, JSONObject jSONObject) {
        JSONArray jSONArrayOptJSONArray = null;
        int i = 0;
        ArrayList arrayList = null;
        int i2 = 0;
        ArrayList arrayList2 = null;
        int i3 = 0;
        String str = "ۛۡۡ۫۟ۤۜۡ۟۬ۚۡۘۤ۟ۖ۟۬۠ۖ۫ۚ۠ۛۢۥۨۜۧۤۡۖۛ۫ۚۘۚۚ۟ۚۙۘۙۧۘۜ۟۟ۧ۬ۤۛۡۥۨ";
        int i4 = 0;
        int i5 = 0;
        int i6 = 0;
        JSONArray jSONArrayOptJSONArray2 = null;
        int i7 = 0;
        int i8 = 0;
        JSONArray jSONArrayOptJSONArray3 = null;
        JSONObject jSONObjectOptJSONObject = null;
        int i9 = 0;
        while (true) {
            switch ((((str.hashCode() ^ 571) ^ 688) ^ 298) ^ (-1444481477)) {
                case -2107615296:
                    str = "۟ۛۘۘۧۦۛۙۙۡۘۗۙۙۜۤۡۘۡۛۨۘ۬ۖۧۘۖۖۥۧۗۜۘۖۚۙۥۥۖۤۘۡ۬ۖۛۗۚۘۘۙۙۜۜۨۡۘ";
                case -2025947124:
                    arrayList2.add(jSONArrayOptJSONArray2.optString(i6));
                    str = "ۖ۟۟ۘۥۡ۬ۨۘۘۡۙ۫۟ۡۖ۟ۤۨۢۖۦۘ۫ۗۥۘۢۢۦۤۚ۠ۘۤۤۢۙۜۤۚۡۘۘۛۦ";
                case -1811229541:
                    arrayList2 = new ArrayList();
                    str = "ۙۦۛۧۨۛۛ۫ۡۘۜۤ۬ۖۖۛ۠ۛۥۘ۠۫ۙۡۖۦۜ۫ۤۤۚۘۜۚۛۘۨۥۘ";
                case -1763578177:
                    arrayList.add(jSONArrayOptJSONArray3.optString(i8));
                    str = "ۙ۫ۥ۠ۧۘۘۥۙۡۘۨۥۘۧۛ۟ۤۦۤۘۙۛۙۦۢۖ۠ۨۘۨ۟۬ۡۜۧۘۚۥۤ";
                case -1758399841:
                    String str2 = "ۨ۟۟ۢۚۜۡۡۖۥ۫ۥ۟ۚۦۘ۠ۧ۠ۖۙۖۘۥۧۖۘۖۗۘۘۛ۬ۢ";
                    while (true) {
                        switch (str2.hashCode() ^ (-2100909049)) {
                            case -1495432057:
                                String str3 = "۟ۚۘۘ۠ۡۧۧۗۥۘۡ۟ۗۤۖۥۘۥۡۖۘۖۜ۟۟ۨۢۗۨۢۦۥۘۙۦۖۢۧ۬ۙ۬ۢ۫ۖۗۗۜۖۘ۟ۚۥ";
                                while (true) {
                                    switch (str3.hashCode() ^ 1642042393) {
                                        case -1741940981:
                                            str3 = jSONArrayOptJSONArray3 != null ? "ۢ۬ۦۘ۠ۡۡۘ۟ۚۜۦۗۜۘۤۖۘۥ۠ۥۘ۫۬۠ۚۦۜۘۢۢۖۢۦ۬ۢۖ۫ۢۚۨۘ" : "ۙۨۧۖۗ۠ۤۛۥۜۤۥۤۨۨۘۚ۠ۙ۟ۗۡۙ۫ۦۘۢۜ۬ۗۧۧۢۛ۠ۖ۬ۡۨۙۘۘۛۧۘۘ";
                                        case -1626164167:
                                            str3 = "ۡ۬ۦۘۡۖۨۥ۠ۢ۠ۚۖۢۛ۠۬ۘۦۘۘۤۢۗۜ۬ۨۘ۬ۤۜ۫ۚۙۛ۠ۖۘۦۙۖۘۤۨۘ۠ۦۡۘۤۛۖ";
                                        case -1488271406:
                                            str2 = "ۨ۠ۖۘۡ۫ۦۧۡۘۡۡۦ۫ۗۧۥۜۦۗۖۛۧ۠ۦۜۖۘۥ۫";
                                            break;
                                        case 1174717235:
                                            str2 = "ۜۙۨۘۛ۠۫۬ۚۙۚۥۖ۫ۜۢۧۥۜۧۢۧ۠ۥۡۘۢۙۡۤ۬ۛۨۗۤۥۜۛۘۢۦۘۘ۠ۡۘۢ۬۠۫ۧۘۧ۟ۦۘ";
                                            break;
                                    }
                                }
                                break;
                            case -1347605691:
                                str = "ۦۙ۠ۛ۟ۘۘۦ۬ۦۚۚۘۧ۠ۘۦۧ۬ۡۜۦۘۤۜۤ۫ۗۖۜۨ۠ۦۛ۫۟ۙۤۗۚۦۥۙۙۡۜۨۘۙۥ";
                                break;
                            case -566361630:
                                break;
                            case 1030250450:
                                str2 = "۠ۥۧۘۙ۫ۛۢ۫ۚۜۤ۟ۘ۫۫ۢ۟ۖۥ۬ۡۧ۠۟ۡ۟ۥۘۥۖۡۘۘ۟ۚۚۤۚۡۖ۬ۛ۠ۢ";
                        }
                    }
                    break;
                case -1580214212:
                    str = "ۖۡۖۛۦۡۖۜۦۘۧۜۢۜ۠ۤۘ۟ۖۛۢۖۗۘۖۘۨۗ۬ۥۙۗۨۥۗۚ۟ۛ۠ۧۨۥ۟۬";
                    i6 = i5;
                case -1437471085:
                    String str4 = "ۨۡۜۘۦۖۢۤ۬ۖ۟ۖۚۡۦۖ۫ۥۥ۠۟ۜۘۡۜۨۘۚ۠ۘۘۡۨۦۘ";
                    while (true) {
                        switch (str4.hashCode() ^ (-535971081)) {
                            case -469328816:
                                break;
                            case -260371140:
                                str4 = "ۨۚۘۘۗۜۖۡۡۦۘۤ۠۬ۚۗۡۘ۟ۢۦۜۖۨۚ۠ۗۗۖۘۨۦۢ";
                            case 1640519494:
                                String str5 = "ۙ۬ۘۘۛۜۘۘۧۡۧۘۖۤۦ۠۟ۛۥۤۨۘ۠ۨۢ۠۠ۘۖۗۥ۬ۢۜۘ۠۬ۢۥ۠۟ۜۦ۟ۛ۬ۜۦۛۥ۫ۦۦۘ۫ۤۦ۠ۖ۟";
                                while (true) {
                                    switch (str5.hashCode() ^ (-115004173)) {
                                        case -983816997:
                                            str4 = "۬ۖۦۘۡۗ۫ۦۡۙۘۜۨۘۥ۠ۛ۠ۤۤۥۥۡ۠ۚۗۚۧ۫۠ۢۘۘ";
                                            break;
                                        case 1895776072:
                                            str4 = "ۢۖۨۖۘۧۜۧ۟ۖۘۥۥۦۦ۠ۘ۫ۢۜۦ۠ۚۘۘ۠ۢۗۧۦۘۢۤ۫۫۬ۨۦۡۘۖۚۘۘ";
                                            break;
                                        case 1932739516:
                                            str5 = jSONObject.optBoolean("enableImagePopups", false) ? "ۜۚۜۘۡۗۜۘ۟۠ۜۤۜۧۘۘۧۡۘۧۘۘ۬ۥۥۚۢۘۘۚۢۨۛۗۨ۫ۡۢۥۨۦۨۨۧۘۥۚ۬" : "ۙۛۖ۠ۤ۫ۛۤۡ۟ۧۖۧۥۘۙ۠ۜۗۡۖۛۘۖۚۛۘۘۖۢۨۖۢۜۘۨۦۗۜۙۘۢۢ۫۬ۤۥۢۨۗۜۨۡۘ۠۫ۤ";
                                        case 1948215435:
                                            str5 = "ۡۧۡۙ۫۫۫ۥۥ۟۠ۤ۫۠ۡۛۘۦۙۛۖۘۖۡۡۘۙۗۘۘ۠ۗۧۛۢۧ۬ۖۧۘۢۙۨۧۧۥۘۨۖۢۤ۬ۢ";
                                    }
                                }
                                break;
                            case 1739596905:
                                str = "۟ۦۖۘ۬ۚۜۢۡۘ۬ۥ۟ۦۗۖ۠۟ۦۡۙ۫۠ۙۥۘۗۢۜۘۨ۟ۙۧۙۗۗ۟ۙۙۛۢۛ۠ۡ۟ۧۚۡۨۘۘ۬ۦۢۖۤ";
                                break;
                        }
                    }
                    str = "ۢۗۘۛ۟ۜۨۖۤۙۗۨ۟ۘ۫ۤۨۢ۟ۖۤۘۦۘۢۦۡۘ۫ۡۙ";
                    break;
                case -1423330280:
                    str = "ۛۗۘۖ۫ۨۘۚ۠ۜۘۖ۟ۜۙ۬ۘۘۧۤۡ۬۠ۗۥۨۦۘۦۜۧۘۗۧ";
                    i6 = i3;
                case -1417550909:
                    str = "ۡۧۢۛۤ۬۫۟ۙۖۗۨۥۥ۫ۘ۬ۛ۬ۧۨ۫ۖۦۘ۬ۧۖۘ۬ۜ۠";
                case -1213632566:
                    jSONObjectOptJSONObject.optString("id", "");
                    str = "ۛۘۖۡۥ۟۬ۘۛۛۨۧۖۧۜۚۚۦۘۧۗۥۡ۬ۡۘۚۙۜۘۖ۬ۡۘۙۘۛۙۗۜۘۦۚۨۙۧۨۘ";
                case -1176400911:
                    arrayList = new ArrayList();
                    str = "ۢ۫ۜ۫ۛ۬۠ۚۤۡۖۧۢۨۛۡۧ۬ۤۛۢۦ۬ۡۛۛۨۘۢۨۨ۠ۘۛۢۘۖۘ";
                case -1004490808:
                    i3 = 0;
                    str = "۠۬ۤۛۖۧۘ۫ۢۤۖۚ۫۫ۢۡۨۧۘۗۥۨۘۗۘۨ۫ۖۜۘۖۘ۬ۙ۬ۙۗۖۜۡۦۚۙ";
                case -995966979:
                    str = "ۜۜۥۘ۟ۖۡۘ۠ۨۨ۟۠ۘۘۦۤ۠۬ۛۦۘۥ۬ۦۧۛۖ۟۟ۦۙۛۘ";
                case -943932464:
                    String str6 = "ۤۗ۫ۙۘۘ۫ۦۢۧ۠ۦۘ۟۟ۖۨ۟ۥۜۧ۫ۦۛ۫ۦ۬۠ۚۦۨۘۢۤۨۘۛۥ۫ۗ۬ۨۥ۬ۢۦ۫ۙۡ";
                    while (true) {
                        switch (str6.hashCode() ^ 850645117) {
                            case -707068432:
                                String str7 = "ۤۚۥۢ۬۟ۢۘۜۘۘۜۨۘۜۤۜۘۛۦ۠ۖۡۥۖۖۨۘۨۨ۬۫ۢۡۢ۬۬۟ۢۦۜۥۚۘۘ۫ۨۜۦۘۡۘۤۘۘۘۘۗ۬";
                                while (true) {
                                    switch (str7.hashCode() ^ 1494699630) {
                                        case -1490035199:
                                            str7 = "۫ۘۦۘۢۨۘۧۡۨۘۧ۠ۘۧۧۜۗۡ۫ۨ۟ۥۘ۫۟ۡۚۘ۫ۜ۬ۤۛۥۡۘۘۤۤ۫ۡۖۘۚۦۥ";
                                        case -699431645:
                                            str6 = "ۙۗۥۘۡ۬ۨۘۙ۬ۥۢۡۘۙۖۜ۬ۜۙۥۜۧۘۧۢ۬۟ۨۤۢۦۤۡۨۧ۫ۗ۬ۗ۟ۧۧ۫۬ۡ۬ۜۘۛ۬ۨ";
                                            break;
                                        case -614197744:
                                            str7 = i9 < jSONArrayOptJSONArray.length() ? "ۨۨۧۘۧ۠ۥ۬ۚۥ۠۫ۜۘ۠ۗ۬ۖ۬ۦۘۡۢۗ۫ۤ۬ۚ۫ۡ۬ۢۦۗۘۗۢۗۛۦ۬ۗ۠ۗۡۧۦۘۥۜ۟" : "ۚۧۤۜۨۡۗۜۢۚۚۨ۬۫ۢۘۧ۠۬۫۬ۧۗۘۘۗۙ۟ۨۜ۬ۢۢۥۧۘۘۛۜ۠ۗۨۘۥ۠ۨۘۦۘۢ";
                                        case 2003949684:
                                            str6 = "ۤۜۦۘ۬ۜۧۥۢۡۜۥۥۖۙ۬ۧۤ۠ۥۘۡ۬ۡۘۗۤۥۨ۠ۙ۫ۡۘۥۢۜۖ۫۬ۢۘۢۨۛۦۦۥۡ۠۟ۦۡۚۚ";
                                            break;
                                    }
                                }
                                break;
                            case -429498560:
                                str = "۬ۗۘۘۥۘۜۦۘۧۘۦۖۘۧۗۛ۬ۦۚۧ۬ۜۘۚۙۜۦۗۡۙ۫ۡۘۨۦ۟ۚ۟ۦ";
                                break;
                            case -273002933:
                                str6 = "ۡ۫ۖۥۗۘۘۢۧۨۘۖۤۨۥۘۘۘۜ۠ۜۚۤۘۘ۬۟ۜۖ۬ۗۦۢۥۚۘۛۜ۫۠";
                            case 806004357:
                                break;
                        }
                    }
                    str = "ۢۗۘۛ۟ۜۨۖۤۙۗۨ۟ۘ۫ۤۨۢ۟ۖۤۘۦۘۢۦۡۘ۫ۡۙ";
                    break;
                case -833622846:
                    str = "ۜۦۗۥۖۡۘ۠ۘۡۚۜۢ۟۬۫ۗۤ۟۫ۖۘۦۖ۫ۤۘۥۥۘۜۢۧۘۘۨۙۘۘۚۧۜۘۙۨۚ";
                    i5 = i6 + 1;
                case -782337983:
                    str = "ۚ۬ۤ۬ۤۘۨۤۧ۠ۦۖۘۡۡۗۨۤۖۢۢ۫ۜۥۤۦ۠ۘۥ۟ۥ";
                    jSONArrayOptJSONArray3 = jSONObjectOptJSONObject.optJSONArray("white_list");
                case -658796366:
                    str = "ۡۧۢۛۤ۬۫۟ۙۖۗۨۥۥ۫ۘ۬ۛ۬ۧۨ۫ۖۦۘ۬ۧۖۘ۬ۜ۠";
                    i8 = i2;
                case -590918694:
                    String str8 = "۠ۢۥۘۗۢۘۚۦۤۖۨۛۨۤۜۘۧ۟ۨۘۤۥۧۥۘ۠ۨۙۢۦۘۡۡۦۦۘۧ۬";
                    while (true) {
                        switch (str8.hashCode() ^ (-2101513544)) {
                            case -2039061073:
                                break;
                            case -1617193849:
                                str8 = "ۦۛۢۥۖ۟ۖۚۥۘۡۗۤۚۢ۟ۙ۬ۙۥۤ۬ۡۤۘۨ۟ۨۚ۟۠۠ۘۥ۟ۜۖۘۘۦۢۜ۠ۖۘۢۧۗۨۧۙ";
                            case 32506002:
                                String str9 = "ۘۥۧۘ۟ۖ۠۟ۜ۠ۛ۟ۡۘ۫ۤۡ۠۟۬ۛۘۥۘۚۨۦۘۧۖ۬ۢۙۥ";
                                while (true) {
                                    switch (str9.hashCode() ^ (-680402814)) {
                                        case -2112660049:
                                            str8 = "ۤۥۨۘۥۧۚۨۧۨۖۚۡۘۚ۫ۜۙ۫ۖۘۧۥۡۖ۬ۖۖۙۨۚۛۨۘ";
                                            break;
                                        case -669209630:
                                            str8 = "ۡۤ۠ۥۤۡۥۘ۟۠ۗ۬ۢۙ۬۠ۖۦۚۧۘ۫ۦۖۘۧۜۘۛۡۙۜ۬ۦۘۧ۠ۜۘ۠ۗۚۚۦ۟ۘۛۖۗۛ۠";
                                            break;
                                        case 1240183624:
                                            str9 = "۬۠ۤۡۥ۟ۢۜۡۨۧۧۢۗ۠ۨۘۖۘۚۡۦۘۧۢۜۘۘۗ۠";
                                        case 1579546555:
                                            str9 = jSONArrayOptJSONArray2 != null ? "ۚۤۛۥۛۚۚ۠ۘۛۖۨ۠ۥۖۘۘۛ۟ۢۖۨۜۜۚۖ۠ۧ۫ۜ۫" : "ۖ۫۠ۦۡۘ۠ۨۘۧ۬ۛۚ۟ۗۨۨ۬۟ۘۨ۫ۖ۬ۢ۠ۧۥ۟ۘۘۜ۫ۜۘۡۡ۟ۤۛۜۧۜۧۘۛۛۜۥ۫ۙ";
                                    }
                                }
                                break;
                            case 1363226389:
                                str = "ۢۨۘۘۧۥ۫۫ۢۧ۠ۚۛ۫ۥ۫ۢۘۜۤۡۘۡۨۛۤۜۨۘ۫۫۟۫ۤۦۜۢۧۛۤۙ۫۬ۘۥ۟ۥۜ۫۠";
                                break;
                        }
                    }
                    break;
                case -524183301:
                    String str10 = "۟ۡ۟ۗ۫ۦۢۡ۬۟۠ۤۖۙۙ۟۫۬ۨۦۙۘۦۜ۬ۜۡۘۗ۟ۨۘۜۡۥۜۗۘۘۘۨۡۛۧۢ۫ۗۗۜۚ۠ۥ۬ۗۜۗ";
                    while (true) {
                        switch (str10.hashCode() ^ 854921205) {
                            case -474988437:
                                break;
                            case 1113155406:
                                String str11 = "۬ۧۦۘ۬ۚۗۙۗۤۗۦۘۨۘۗۤۜۘۘۧ۫ۢۗۜۚۥۘۜۙۧۜۘۡۢۦۘۜ۠ۨ";
                                while (true) {
                                    switch (str11.hashCode() ^ 610064865) {
                                        case -984628204:
                                            str11 = "ۜۖۦۘۛۚۤۨۢۥۘۧۨۘۢ۬ۤۛ۟ۘۘ۟ۥۤۦۥ۫۟ۙ۫۟۟ۘۘۥۜۚۤۛۥۘ";
                                        case -142616492:
                                            str11 = jSONObjectOptJSONObject != null ? "۫ۨ۠ۧۘۘۘۡۛۨۘۤ۟ۘۘۧۜۧۢۗۢۨۘۦۢۦۥۘۚ۫ۚۡ۬ۚۧۖۦۜۥۗۨۖۡۥۗۦۘۤ۟ۛ۟ۢۗ" : "ۗۚۡۛۨۡۘ۠ۥۖۢۥۥۦۤۘ۟ۙۛۖۢۖۦۧۘۘۧۚۖۛۛۢۜۗۘۙۗۤۨۗ۬ۥ۠ۙۤۘ۫۠ۦۥۦۥۗۡۢ";
                                        case 613616214:
                                            str10 = "ۨۢۖ۬ۥۡۘۜۙۥۗۥۛۨۖۡۘۢۨۘۛ۬ۢۡۘۤۦۚۜۡۤۥۦ۠ۦۘ۟ۢۗ۫ۙۗۗۥۡۘۛۖۡۘۥۙۘۘ";
                                            break;
                                        case 1721585878:
                                            str10 = "۫ۢۜۖۦۨۨۙ۠ۨۚۜۘۢ۠ۦۖۡۜۘۨۦۙ۟ۢۥۘ۫۬ۘۘ۬ۛۨۥۨ۫ۡ۬ۖۘۤ۫۠ۖۚۥۘۜۤۛۗۦۖۘ";
                                            break;
                                    }
                                }
                                break;
                            case 1578590152:
                                str10 = "۫ۙۡۘۚۤۗۜۦ۫ۢۨۖۘ۬ۧۢۤۖ۬ۧۥۧۘۘۨۤۨ۠ۨۘۧۢ";
                            case 1714146529:
                                str = "ۗۥۘۖ۟ۖۘ۟ۛۙۢۚۡۤۡۡ۠۟ۡۨۢ۬ۛ۬ۛۡ۫ۜۙۘۙۜۘۘۨۤۡۧۛۥۢۤ";
                                break;
                        }
                    }
                    break;
                case -497145460:
                    str = "۟ۜۥۘۚۙۢۘۡۘۛ۟ۦۨۘۘۥۚۜۤۛۨۘۘۤۧۤ۬ۜۘ۠۠ۜ";
                case -405678277:
                    i2 = 0;
                    str = "ۖ۟ۖۘۚ۟ۘۥۢ۟ۜۢ۫ۧۜ۟ۡۘۦ۬ۨۥۛۨۘ۬ۢ۬ۛۢۦۘۖۢ۫۬ۥۗ۟ۜۡۘۘۙۚۢ۬۬ۙۧۨۚۤۡۚ۬ۘۘ";
                case -330403148:
                    String str12 = "ۥۘۥۛۡ۠ۧ۫ۦۦۥۧ۟ۙۨۙۤۛ۫ۙ۬ۚ۬ۚۨۢۘۘۧۜۡۘۘ۫ۙۦۜۜۘ۟ۛۡۘۗۙۗ۫ۨ۠ۢۘۖۧ۟ۙۚۧۙ";
                    while (true) {
                        switch (str12.hashCode() ^ 915427832) {
                            case -960433038:
                                break;
                            case 215335467:
                                str = "ۙ۬ۡۘ۟ۛۘۘۗۡۥۘۘۤۥۘۢۧۦۛۢۜۘۦۘۥۜۘۗ۫ۧۨۘۘۙۘۖۧۙ۟ۘۘ۬ۧۤۥۦۡۘ۬۫ۖۖ۫ۖۘ";
                                break;
                            case 1434742122:
                                String str13 = "ۡ۬ۤ۟ۖۥۘۛۘۘۘۤۤۖۜۙۖۧۜۚۖۖۘۘۜۜۙۡۡۚۤۗۚۨۜۚۖۧۘ";
                                while (true) {
                                    switch (str13.hashCode() ^ 1727604918) {
                                        case -1833622652:
                                            str13 = "۟۬ۖۘۨۗۡۙۛۨۘۖۙۘۘ۬۫ۧۨۘۗۙۤۦ۫ۢۘۘۘۚۧ۫۟۟۟ۥۢۥۛۘۘۦۘۘۘۖۖ۟ۤۘۥۘ۫ۧۥۘۖۦۘۗۢۨ";
                                        case -1392161068:
                                            str12 = "ۡۤۥۛۜ۫ۛۥۡۗۜۦۘۛۢۜۘۜ۬ۗۥۥۨۛۥۚ۫ۦۖۗۧۡۘۨۙۧۡۢۥ";
                                            break;
                                        case -1160977069:
                                            str13 = i8 < jSONArrayOptJSONArray3.length() ? "ۙۘۨۘۥۚ۠۬۬ۥۘۚۢ۟ۗۦۥ۫ۙۜۘۦۘۢۦ۬ۦۤۦۤۜۨۤ۬۟ۤۗۥ۫ۚۢۨۦ۟ۗ۠ۤۦۜۘ۬۫ۨۘۨۙۜۘ" : "۫ۚۦۘۨۛۢ۠ۧ۫۟ۦۢۥۛۖۦۛۖۘۤ۟ۢۨۚ۠۬ۦۖۘۛ۫ۧۛۖۥۘ۟ۗ۬ۘۜۜۗۥ";
                                        case 1525598065:
                                            str12 = "ۢۛۖۙۘۦ۬۟ۚۘۨۡۘۨۚۨۛۜۤۢۡۘۦۘۖۘۥ۬ۨ۠ۢۜ۬۬ۛ۟";
                                            break;
                                    }
                                }
                                break;
                            case 1585721473:
                                str12 = "ۚۧۜ۫۫ۨۘۢۢ۬۠ۛۥۘۜۡۡۛ۟ۚۙۖۤۨ۠ۘۘۖ۬ۢۤۚ۫";
                        }
                    }
                    str = "ۙۗۘۨۘ۬۫ۜۖۗۘۖۘۧۦۤ۠ۜۛۗۚۙۘۦ۠۬ۚۛۚۛۦۗ۠ۥۢۚ۬ۘۗۦۖۨۘ۬۬ۥۘۤۚۛۤۢۖۘۤۢۜۘ";
                    break;
                case -204815636:
                    str = "ۗۥۤۘۖۘۘ۠۟ۥۗۦۘۜ۠۫ۥ۬ۜۙ۬ۖۘۚ۠ۜۨۚۡۖۘۡۘۗۧۙ۬۠۫ۜۗۥۘۘۡۤۡۖۢۙۤۚۦۘۦۨۖۧ";
                    i9 = i4;
                case -183732381:
                    str = "ۛۧۦۨۧ۫۫ۨۦۘۙ۫ۧۜۤۗ۟ۙۥۜۦۙۨۘۦ۫۠۟۟۫ۖۘ";
                    jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i9);
                case 227088529:
                    String str14 = "۫ۤۘۜ۫۬ۨۜۙۥۗۜۘۡۚ۟ۥۦۥۘۛۥۙ۟ۧ۠ۥ۬ۥۘۥۗۦۢۜۛۛ۠۟ۤۤ۫ۙۘ۫ۖۨۥۘۗۛ";
                    while (true) {
                        switch (str14.hashCode() ^ (-1423879872)) {
                            case -1306401897:
                                str14 = "ۢۜۖۧۜ۬ۘۦ۟ۢ۟۟ۨۘۤۤۤۚۖۛۡ۫۬ۨۖۘۥۘۦۘۨۘۥۡۦۘ۫۟۫۟ۚ۬ۜۙۨ";
                            case -387439345:
                                str = "۠ۤۗۨۜۗۚۢۙۘۙۗۗۥۘ۟ۤۦۡ۠۬ۤۥۖۤۡۖۘۖ۬ۙ";
                                break;
                            case 364912854:
                                break;
                            case 1027849007:
                                String str15 = "ۜۢۗ۬۠ۢۛ۠ۖۘۖۥۥۘ۫ۥۦۘۘ۟ۦۘۗۛۦۚۢۖۘۦۙ۠ۥ۬ۦۨۡۧۘ۠ۨۛۨۘۧۡۢۗۡ۟ۖۘۧۡۨۘۦۙۦۖۖۥ";
                                while (true) {
                                    switch (str15.hashCode() ^ (-1273912013)) {
                                        case -1410453476:
                                            str14 = "ۙ۫ۚۦۗۘۛۧۥۘۡۤۦۛۜۤۥۜۛۦۥۘۙ۠ۥۥۜۧۘۥۘۨۨۜۚۙۘ۬۠ۙۘ";
                                            break;
                                        case -1289204178:
                                            str15 = "۬ۥۜۘۚۤۡۘۥۗۖۗۖۙۚۛۡۘۖ۫ۥۘۗۧۜۘۜ۟ۨۘ۬ۧ۟ۛۧۢۥۗۖۘۛۢۥۘۗۗۨۦۢۨۛۖۢۨ۫ۖ";
                                        case -621768600:
                                            str14 = "ۛ۬ۗ۬ۖۥۘۙۖۨۘۦۡۨۦۗۥۘۢ۫ۛۨۙۗۜۦۡۥۙۚۤ۫ۗۖۢۜۖۖۥۘ";
                                            break;
                                        case 1626633602:
                                            str15 = jSONObjectOptJSONObject.optBoolean("enable", false) ? "ۢۦۡۘۧۢۚ۠ۖۤ۬ۖۘۘۨۖۨۘ۬ۤۘۦۙۢۜۧۘۙ۬ۜۘ۠ۖۛۧۦۘۘۦ" : "۬۫۟ۤ۫ۤ۬ۧۚۛۡۘۘۤۥۜۛۢ۠ۜ۠ۘۘۡ۬ۦۘ۠۟ۡۘ۠ۛۧ";
                                    }
                                }
                                break;
                        }
                    }
                    str = "۠ۙۡۘۜ۟ۜۡۙ۟ۜۨۘۘ۬ۧۛ۠ۢۜ۠ۧۗۘۙۛۡ۫ۘۘ۟ۤۖۗۢۖۨۡ";
                    break;
                case 499522324:
                    String str16 = "۟۟ۡۘۨۢۦۥۨۨۘۘۧۨ۬ۚ۫۫ۦۥۤۧۡۥۘۧۘۢۢۖۘۨۦۨۘۤ۬ۘۘ۟ۛۢ۠ۖۤۛ۟ۧ";
                    while (true) {
                        switch (str16.hashCode() ^ (-879075510)) {
                            case -859442077:
                                break;
                            case -830512060:
                                str = "ۢۖۨۧۜ۬ۧۥ۠۬ۢۙۡ۟ۚ۟ۤۛ۟ۚۡۘۜۦۛ۬۬ۥۘ۫ۨۥۡۖۗۥ۠ۙۚ۠ۘۘۙۙ";
                                break;
                            case -71857138:
                                str16 = "ۥۨۜۡ۟ۡ۬۬ۙۛۖۘۡۨۡۨۢۡ۫ۥۥۥۖۘۧۛۧۜۗۡۘ";
                            case 1143605653:
                                String str17 = "ۖۖۚ۬۬ۛۗ۫ۨۘۗۛ۫ۨۡۘ۟ۘۦۘۖۤۘۜ۟ۢۧۦۚۜۘۖ";
                                while (true) {
                                    switch (str17.hashCode() ^ 547512602) {
                                        case -2048323210:
                                            str16 = "ۢۡۘۘ۟ۖۘۘۤۘ۟۠ۡۧۘ۬ۢۙۡۗ۟ۜۤۜ۟ۛۛۜ۫ۖۦۚۨ۠ۙۘ۫۫ۜۘۨۚۜ۟ۦۤ";
                                            break;
                                        case -2048162375:
                                            str16 = "ۗۨۧۘ۫ۘۡۘۦۘۥ۫ۥۛۥۜۥۘ۟۫۠۬ۜۖۘۨۥۖ۟ۛ۟ۛۦۡ";
                                            break;
                                        case -2028933266:
                                            str17 = "ۡۥۤۗۤۜۘ۠ۤۙۨۗۜۘ۠۠ۦۡ۫ۘۘۘۥۛۨ۬ۤۨۦۡۘۜۚۤۗۡۥۘ۟ۦۜۦۘۦۘۙ";
                                        case -985854842:
                                            str17 = i6 < jSONArrayOptJSONArray2.length() ? "ۚ۫ۨۘ۬۫۫۬ۦۛۨۖۘۧ۟۬ۥۦ۠۬ۜۛۛ۫ۥۢۙۢۘ۠ۘۙۖۨۘۡ۟ۘۚۢۜۘۨۘۖۘ۟ۖۢۤۨۥ۟۠۟ۜ۫ۖ" : "ۦۨۤۛۡۥۜۗۤۥۚ۟ۙ۟ۚۛ۬ۙۚۧۘۙۧۖ۟۠ۗۘۖۘۤۘۦۘۜۘ۟ۧ۠ۛ۫ۙ۟۬۠۟۬ۜۛۜۗۜۛ۟";
                                    }
                                }
                                break;
                        }
                    }
                    str = "ۛۧۡۘۚۨۦۤ۟ۦ۬۫ۤ۠ۢۡ۠ۗۖۚۢ۠ۡۘۜۤۘۘۦ۟ۛۛ۟۟ۚۘۘۧۙۗ۬ۙۤۦۛۜۘۡۛۦۘ";
                    break;
                case 528322048:
                    str = "ۢۦۡ۠ۥۗ۫ۦۦۘۨ۬ۚ۫۟ۨۘۥ۠ۖ۫ۘۥۘۦۚۨۙ۫ۖۘۡۧ";
                    i7 = i8 + 1;
                case 668935089:
                    str = "ۨ۫ۨۘۙۚۜۘ۬۫۠۠ۤۨ۫ۥۚۘ۠ۚۢ۠ۢۦۦۤۛۖۧۘۗۧۗ";
                    i4 = i9 + 1;
                case 1014979604:
                    String str18 = "ۜۗۚۗۙ۬ۚۛۦۘ۬۫ۘۘ۠۟ۥ۬ۧۜۘۗۦۥۘۖۢۖۥۢۖۘۛۥۨ۫ۛۖۦۘۘۦ۬ۖۘۙۦۖۘ";
                    while (true) {
                        switch (str18.hashCode() ^ (-580402444)) {
                            case -2069278043:
                                break;
                            case 34342010:
                                String str19 = "ۛۚۧۢۦۡۘۤ۬ۢۨۢۥۘۚۗۖۚ۟۫۟ۡۗۢۧۨۗۚ۬۫ۦۧۢ۟ۘۘۡۘۧۘ";
                                while (true) {
                                    switch (str19.hashCode() ^ 1955694643) {
                                        case -1545414892:
                                            str19 = jSONArrayOptJSONArray != null ? "ۗۗۘۘ۫ۜۦۜۢۡۢ۟ۜۘۥۛۧ۠ۡۦۦۨۘۤۜۧۘ۠ۛۖ۠ۨ۬۫ۛۙۘۘۢ۟ۖۦۘۦۤۜۘۤۨۙۤۦۦۨ۠۬ۙۧ" : "ۨۡۙۘۚۤۙۡۢۗ۫۠ۦۡۘ۬۬ۛۨۛۨۘۜۦ۟ۗۜۘۡۜۤۤۤۡۘۖۛۧۢ۫ۚ۠ۜۨۜۚۥ۫۫ۧۨۘۡۦ";
                                        case -613762607:
                                            str19 = "ۥۛۦۘۡۤۥۘۘ۟ۤۘ۫ۚ۠ۧۗۛۚۨۙۨ۫ۖۘ۠ۙۡۘۨۤۜۜۚۘۘۙۤۥۘ";
                                        case 764896284:
                                            str18 = "ۜۙۜۘ۠ۚۥۛۦۙۙ۬ۡۡۖۨ۫ۥۡۘۜ۬۟ۧۛۨۘۨ۠ۙ۫ۢۧ";
                                            break;
                                        case 1141337974:
                                            str18 = "ۧۜۤۢۥۨۘۛۚۤۧۚۜۡۤۡۘ۫ۤۨۘۖ۫ۦۘۨ۬ۨۘۨۙ۫ۙ۫ۘۘۜۖ۠ۙۨۘۘۘۘۗ۠۫۬۠ۦۥۘۙۚۗ۬ۚۡ۫ۧۚ";
                                            break;
                                    }
                                }
                                break;
                            case 1192134980:
                                str18 = "ۛۨۤۖ۬ۨۘۙۚۦۙۜۘ۫ۦۙ۫۠ۨۖۗۨۘۥ۠ۚۖۜۜۛۜۦۘۨۛۚۧ";
                            case 1220815145:
                                str = "ۦۜۨۘۥ۟۠۫ۚۥۚۖ۬ۘۤۨ۠ۚۦۛۚۛ۟ۥۜۧ۫ۤۛ۬ۘۘۦۧۧ۬ۥ۫ۡۡۚۘۜۨۧۗۧۦۛۦۘۡۢ۫ۗۗۥ";
                                break;
                        }
                    }
                    str = "ۢۗۘۛ۟ۜۨۖۤۙۗۨ۟ۘ۫ۤۨۢ۟ۖۤۘۦۘۢۦۡۘ۫ۡۙ";
                    break;
                case 1121767564:
                    i = 0;
                    str = "۠ۡۛۨۘۡۙۤ۠ۛۦۜ۬ۙۜۤۜۥۢۧۖۘ۠۬ۖۘۤۡۗۖۗۥۥۜۜۘۘۧۘۘۡۚ۟۬۬";
                case 1287995826:
                    k2.iLPflsJX(activity, jSONObjectOptJSONObject.optString("id", ""), jSONObjectOptJSONObject.optString("imageUrl", ""), jSONObjectOptJSONObject.optInt("clickAction", 0), jSONObjectOptJSONObject.optString("clickText", ""), jSONObjectOptJSONObject.optString("callback", ""), jSONObjectOptJSONObject.optInt("countdown", 3), jSONObjectOptJSONObject.optBoolean("canSkip", true), jSONObjectOptJSONObject.optBoolean("autoClose", false), jSONObjectOptJSONObject.optBoolean("lock", false), arrayList, arrayList2);
                    str = "۠ۙۡۘۜ۟ۜۡۙ۟ۜۨۘۘ۬ۧۛ۠ۢۜ۠ۧۗۘۙۛۡ۫ۘۘ۟ۤۖۗۢۖۨۡ";
                case 1416968271:
                    str = "ۨۦۗۖ۬ۜ۫ۡ۫ۗۗۧۨۨۡۘۛ۫ۢۛۦۙۙۧ۫ۗۛۛۗۢۦۘ۬ۧۖۥۤ۟";
                    jSONArrayOptJSONArray = jSONObject.optJSONArray("imagepopups");
                case 1527555025:
                    str = "ۡۗۦۘۘۢۨۘۡۛۘۙۘۙۢۛۧۗۨۜۘ۟ۜ۬ۡۙۦۦۙ۟ۦ۫ۨۗۢۥۘۚۦۜۘۘۚۙۧۘۨ";
                    i8 = i7;
                case 1673717006:
                    str = "۟ۛۘۘۧۦۛۙۙۡۘۗۙۙۜۤۡۘۡۛۨۘ۬ۖۧۘۖۖۥۧۗۜۘۖۚۙۥۥۖۤۘۡ۬ۖۛۗۚۘۘۙۙۜۜۨۡۘ";
                    i9 = i;
                case 1676165814:
                    str = "ۛۗۘۖ۫ۨۘۚ۠ۜۘۖ۟ۜۙ۬ۘۘۧۤۡ۬۠ۗۥۨۦۘۦۜۧۘۗۧ";
                case 1808514732:
                    break;
                case 1869046850:
                    str = "۟ۦ۟ۚۨۜۧۖۗ۟ۨۢ۠ۘۙۗۥۙۢ۠ۨۥ۫۬ۤۖۧۘ۬ۡۜ۫ۜۖۢۧۢ۬ۚۡۘۥۜ۠ۧۥۜۢۨۧۙۘ۫ۢ۬ۖۘ";
                    jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("black_list");
            }
            return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x002b. Please report as an issue. */
    public static void OWRFQJrb(Activity activity, JSONObject jSONObject) throws PackageManager.NameNotFoundException {
        JSONArray jSONArrayOptJSONArray = null;
        int i = 0;
        JSONObject jSONObjectOptJSONObject = null;
        ArrayList arrayList = null;
        JSONArray jSONArrayOptJSONArray2 = null;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        ArrayList arrayList2 = null;
        JSONArray jSONArrayOptJSONArray3 = null;
        int i5 = 0;
        JSONArray jSONArrayOptJSONArray4 = null;
        int i6 = 0;
        String str = "ۡۧۖۘۙۖۧ۟ۨ۫ۨۙۥ۫ۙۥۥۘۘۘۚۖۡۖۨۦ۬ۗۙۖۧۖۘۥۗۛۖۘۖۘۥ۠ۦ۠ۨۥۘۡۢۥۥۛ";
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        while (true) {
            switch ((((str.hashCode() ^ 12) ^ 349) ^ 12) ^ (-1132932620)) {
                case -1952772165:
                    arrayList2 = new ArrayList();
                    str = "ۧۖ۫ۖ۠ۜۙۢۡۙۚۢۗ۬ۛۙۙۗۖ۫ۜۙ۟ۦ۬ۖۘۗۘۜۘۦۡ۠۠ۛۛ۫ۤۖۦۜۥۘ";
                case -1737470673:
                    String str2 = "ۘ۠ۥۤۖۖۜۘۥۘ۬ۘۥۘۖۤۥۘۦۢۨۡۨۘۛۧ۫ۙۘۥۙۘ۬ۨۥ۠۠ۨۦۤۥ۫ۤۥۘۚۗ۫ۖۤ";
                    while (true) {
                        switch (str2.hashCode() ^ 1345781175) {
                            case -70523746:
                                String str3 = "ۚ۟۟۫۫ۚ۫ۖۧۥ۟ۧ۬ۥۦۡۦۨۘۗۧۛۜۗۨۦ۟ۘۘۜۛۜۘۜۨ۠ۛۡ۠ۢ۫ۤۧۙ۫ۘۨۦۚۨۜ۟۫ۥۘ۬۬۠";
                                while (true) {
                                    switch (str3.hashCode() ^ (-1691680605)) {
                                        case -1913492099:
                                            str3 = jSONArrayOptJSONArray != null ? "۟ۙۛۗۤۙۡ۬ۧۜۚۦۘۗ۫ۡۡۙۗ۬ۢۢ۫۠۠ۛۖۡ۬ۨۤ۫۠ۢۤ۫ۛۥۘ۫ۚۖۘ۟ۧۨۘ۟ۛ" : "ۖۖۡۘۘۘۤۛۢۨۢۤۦۘ۟ۢۖۘۢۤۨۘۧۢ۟ۙۧۜۡۘۗۥۛ۟۬ۧۚۙۜۘ";
                                        case -976194950:
                                            str2 = "ۦۜۛ۠ۗۢۧۚ۫ۛۨۡۘۢۘۢ۟ۡۛۢۙۨۘۘۨۨ۬ۡۢۖۘۥۘۤۦۡۘۘ۬ۦۘۨۖۨ۠ۥۖۘۦۢۥۘۤ۟ۛ";
                                            break;
                                        case -543953392:
                                            str3 = "۫ۢۗۗۢۘۢ۠ۖۗۖۡۘۨۗۥۡۛۥۘ۟ۜۨۧۘۡۛ۫ۨۧۙۚۘ۟۠ۦۘ۠ۤۢۖۚۢۨۢ۬ۚۜۙ";
                                        case 406033963:
                                            str2 = "ۢۖۢۖۨۢۙ۠۫ۨۨۘ۟ۖۙۧۢۦۘۢۚۗۧۨۤۜۜ۟۬ۦۖۘۨۢۖۚ۟ۦۘ";
                                            break;
                                    }
                                }
                                break;
                            case 728986996:
                                str2 = "ۚۡۡۘۗۢۛۘۙۢۖۗۨ۠۟ۥ۠ۘ۫ۜۘۜۘ۬ۦۡۡۘ۠ۖۙۛۖۘۦۙۘۘۢۘۧۗۜۢۚۥۖۖ۬ۧۘۧۘۘۨۥۛ";
                            case 1130075064:
                                str = "ۙۨۘۘ۬ۖۢۚۜۨ۬ۤۦ۫۫ۜۘۛۧۖۘۥ۬ۘۧ۠ۘۛۡۗۙۘۤۢۛۙۡۗۘۘۡۜۚۡۢۦۘ";
                                break;
                            case 1553301602:
                                break;
                        }
                    }
                    str = "۫ۛۚ۟۫ۥۘۘۖ۟۠ۙۘۘ۬ۤۗ۟ۙۡۖۦۡۘۗۡ۟۟ۛۨۘ۬۟ۖۘۜ۫ۜۧۧۖۚۖۙۙۙۢ";
                    break;
                case -1666434005:
                    i5 = 0;
                    str = "ۖۢۖ۠ۛۤ۠ۙ۠ۗۤۖۖۗۘۘ۠۠ۡۘ۠۠ۡۘۦۙۘۘۖۦ۫۟۠۟";
                case -1588949927:
                    String str4 = "۫۟ۦۘۙۡ۟ۦ۟۟ۖ۬ۘۘۖۚۗۘۗۖۘۚۚۛۥۜۙ۬ۘۜۘۡۦۛ۫ۨۘ۫۫ۦۚۜۖۘۛۚۥۢۨۘۘۚۡۚ۬۬ۖ۬۠ۥ";
                    while (true) {
                        switch (str4.hashCode() ^ 1461868573) {
                            case 635634304:
                                break;
                            case 709522898:
                                String str5 = "ۚۡۨۘۧۢۗۢۗۥۘۧۨۗۚۚۢۘۜۘۦۗۖۥۛۦۘ۫۬ۡۘۛۦۗۧۨۘۖ۬۫۟ۢۦۘ۟۟۬۬ۖۡۘۤ۫ۘ۟ۨۧۦ۠۬";
                                while (true) {
                                    switch (str5.hashCode() ^ 377264289) {
                                        case -1199580860:
                                            str4 = "ۨۖۖۤۦۘۘۧۢۘۚ۫ۛۤۢۙۙۤۘ۟۬ۢۡۡۜۘۨ۬ۥ۟ۡۘ";
                                            break;
                                        case -118192340:
                                            str5 = jSONArrayOptJSONArray2 != null ? "ۨۢۛۢۜۖ۫ۢۘ۠ۤۢۘۛۘۘۚۜۜۘۤۢۥ۠ۘۙ۫ۦۖۘۖۡ۬۠۠ۦۘۥۛۨۦۢ۟ۧۦۜ" : "ۗۗ۬ۢۡۦۖۛۡۚۛۦۘۢ۬ۙۧۛۛۘۘ۫۬ۘۘ۬ۨۜۡۖۖۘۦ۟ۦۧۧۖۘ";
                                        case -33759512:
                                            str5 = "ۛۥ۟ۖۧ۟ۥۦۜۗۨۚۘ۫۠ۦ۠۫ۗ۫ۙۡۢ۟ۜۘۙۦۢ۠ۢۜۧۡۗ";
                                        case 1450256697:
                                            str4 = "ۢۡۛۛۚۘۘۗ۟ۦۖۨۢۢۤۧۦۧۜۜۨۙۦۥۜۥۘۦۖۘۦۛۥۙۘۘۘۛۚۗۖۗۦۦۛۧۙۥ۫ۘۙۥۘۤۨۨۘ";
                                            break;
                                    }
                                }
                                break;
                            case 1012770738:
                                str4 = "ۤۖۗۙۛ۬ۙۘۤۙۘۖۘۥۘۗۡۢۖ۠ۙۙۢۥۢۦۘۥۘۖ۠ۥۘۥۥۛۘۖۛۜۢ۬ۜۥۘ";
                            case 1371720419:
                                str = "ۤۙۖۘ۟۟ۖۖۥۘۘ۫ۦۥۘ۫ۛۨۘۢۥۡۘۧۜۗۨۤۦۗۡ۠ۖۧۘۦ۫ۥۘۥۤۘۘ";
                                break;
                        }
                    }
                    break;
                case -1357612519:
                    str = "ۚۗۢۛۙۨۢۤۦۘ۬۫ۡۘۜۗۖۘۙۜ۠۠ۦۥۘۦۖۛ۠۫ۥۨۧۦۡۘۨۘ۟۠ۧ";
                    jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i6);
                case -1212807144:
                    String str6 = "ۜۨۙۗۤۧۧۡۨۘۤۜۖۘ۠۠ۥۨۗ۬۬ۥۖۦۦۘ۟ۡۚۖۦۖۡۧۘۘۢۥۚۤۧۨ۬ۙ۠ۧۨ۟ۨۘۘۨۦۜۥۡۘ";
                    while (true) {
                        switch (str6.hashCode() ^ 1299927537) {
                            case -1846111425:
                                break;
                            case -1795092036:
                                str6 = "۬ۡ۬ۚۛۢۨۨۥۘ۬ۨۨۜۢۡۢۡۜۘۘۡۦۘۥۨۡۘۨۜۧۤۧۧۖ۠۟ۥ۠۟ۗۛ۫۬ۘۚ";
                            case 433056082:
                                str = "ۢۢۨۢۦ۫ۖۨۤۧۧۢۙۗۙ۬ۡۘۜۡۡۘۢۜۤ۟ۥۨۖۛۦۥۨۧۘۗ۟ۢ۟ۘ۫ۧۧۡۘ۫ۖۘۧ۫ۢۙۢۘۘۖۛۜ";
                                break;
                            case 1436447524:
                                String str7 = "ۗۨۧۚۖۘۜۙۘ۟ۦۜۨۗۧۘۦۘۚۖۡ۬۬۬۬ۚۢۜۦۛۘۙۧۙۦۘۥۧۘۗ۬ۘۡۖۗۦۖۥۘ";
                                while (true) {
                                    switch (str7.hashCode() ^ 174314093) {
                                        case -1827268365:
                                            str7 = "ۙ۫ۘۘۘۨۨۘ۫ۙۧۙۚ۬ۤۛ۬ۦۦۡ۫ۘۨۚ۟ۜۨۙۧۥۖۘۨۙۘۨۙۛ۫ۘۙۘ۬ۥۗ۫ۗۢۘۥۘۨ۫ۧ۠ۜۨۘ";
                                        case -1498921618:
                                            str6 = "ۛۜۧۘ۠۠ۦۚۤۨۘۥۙۥۘۙۦۡ۟ۢۥۘۡۦۙۧۜ۬ۖۥ۠ۛۥۘۢۙۡۡ۠ۗۦۜۨۘۦۧۘ";
                                            break;
                                        case -1330366416:
                                            str7 = i9 < jSONArrayOptJSONArray3.length() ? "ۛۚۛۛۗ۟ۚۡۙۗ۬ۘۨۚۦۘۘۚۘۖۤۗۧ۠ۢۙۤۜۨ۠ۚۤۢ۫ۖ۠۬ۧۨۛۢۨۨۙۙۤۢ۟ۢۨۘۧ۫ۛ" : "ۨ۟ۖۘ۬ۗۥۘۡۨۜۘۥۤۘۦۦۜ۟۬ۗ۬ۡۤۚ۠ۤۨ۠ۗۤۤۛۘۛۤۡۛۙۙۥۘۘۤۛۜ";
                                        case 1167427492:
                                            str6 = "ۙ۬ۘۖۧۦۘۘۧۡۨ۫۟۟ۘۥۨۧۦۘ۠ۖۦۢۦۨۘۘۚۚۛۚۖۘۖۛۤۗۗ۠ۖ۬ۦۘۢ۟ۗۚۙۜۜۖۦۨۜۘۡۦۖ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    str = "ۖۛۘۜۧۘۘۗ۫۬ۚ۬۟ۙۘۨۤۖۖۧۢۗۡۖۖۘۘۧۖۘۧۡۡۘ";
                    break;
                case -1152067990:
                    str = "۫ۜۛ۠ۙۨۘ۬ۙۡۘ۟ۢۛۤۚۚۧۨۡۘۚۥۧۘۡ۟ۦۛۨۜۘۖ۠ۙۚ۟ۥۘۜ۟ۥۧۚ۠ۛۜۗۛۘۖۦۢ";
                    i7 = i6 + 1;
                case -1108339983:
                    str = "ۗۜ۬۫ۙۦۡۦۡۛۜ۟ۢۛۧۚۘ۬ۢۜۜۛۙۖ۬۬ۘۥۥۚۥۦۨۘۜۤ";
                case -962276126:
                    str = "۟ۢۙ۠ۖۜ۠ۨۦۙ۠۟۠۟ۘۘۦ۟۠ۧۨۖۛۗۢۜۘۧۡ۠ۖۢ۫ۦۡۨۦ۠ۚۜۜ۫ۛۗ۟۠۬ۡ";
                    jSONArrayOptJSONArray = jSONObject.optJSONArray("htmlpopups");
                case -886786601:
                    arrayList.add(jSONArrayOptJSONArray2.optString(i4));
                    str = "ۦۜۤۡۥۨۘۧۦۤۗۡۨۘۨۥ۬۫ۙۢۖۚۗ۠۟ۦۘۦۜۦۘۗۘۖۛۨۡۖۡۢ";
                case -821346772:
                    str = "ۧۜۥۗۢۨۘ۟ۛ۬۫ۥ۠ۡۚۨۗ۠ۙۡۚۜۗۦۦۖۖۘ۫ۦۜ۬ۚۢۧۦ۫۫ۦۜ۠ۖۙۥۦۙۤۘ۫";
                case -509383648:
                    str = "۠ۨۖۖۚۜۘۗۜۡۘۚۚۛۘۚۡۚ۟ۨۘۗ۫ۥۘۙ۟ۖۡ۟۟ۧۢۘۘ۬ۦۛۗۡۘۜۡۖۤۦ۟ۤۤۘۘۢ۟ۢۘۦۥۘۜۙۚ";
                    jSONArrayOptJSONArray3 = jSONObjectOptJSONObject.optJSONArray("black_list");
                case -400331795:
                    String str8 = "ۘۤۡۤۖۚۦۚۘۨۥۘۡۘۜۡۖۧۘۖۨۖ۟۟ۨۨۨۘۜ۠ۤۦۗ۬ۥۥۛۖۥۢۜۘۘ";
                    while (true) {
                        switch (str8.hashCode() ^ (-213137385)) {
                            case -1015550378:
                                str = "ۗۧۡۗۛۜۜۘۡۘۤۛۘۡۘۙۤ۬۠ۤۡۡۖۢۚۤۦۘۢۤۜۚۤ۫ۛۧۢ۬ۛۖ۫ۗۘۘۢۛۥۘۛ۟ۦۘۢ۬ۗۘ۬ۗ";
                                break;
                            case 151333639:
                                break;
                            case 901214771:
                                str8 = "۟ۤ۬ۖۚۗۨۛۚۙ۫ۛۚ۬ۜۘۛۢۦۨۚۡۡۜۖۘۙ۫ۚۙ۠ۛ۬ۛۡۘ۫ۧۘ";
                            case 1704363393:
                                String str9 = "ۘۦۦۘۗۡۧۨۤۡۜۥۨۜۥۘۘۜۜۗۨۡۘ۫ۗۢ۬ۧۦۘۡۛۗۨۘۖۘۢ۠ۢۜ۬ۢۖۨۘۥۤۦۘۡ۟ۥ";
                                while (true) {
                                    switch (str9.hashCode() ^ 1131369915) {
                                        case 101832129:
                                            str8 = "ۙۥۖۘۢۡۜۘۧۢۜ۬ۖ۟ۚۖ۬۠ۤۛۢۨۘۘۛۧۛۡ۟ۖۦۨ۫۟ۘۨ۟۠ۧۚۤۗ۠ۨۘ";
                                            break;
                                        case 763066675:
                                            str8 = "ۨۛ۫ۢ۫ۡۚۖۙ۬ۛۚۤۥۨ۠ۦۘۧۛۘۙۦۨۘ۬ۜ۟ۦ۟ۤ";
                                            break;
                                        case 1176795497:
                                            str9 = jSONObject.optBoolean("enablehtmlPopups", false) ? "ۢ۬ۨۘۚۖۧۘ۫ۗ۟ۛۥۨۜۨۜۘۧۖۨۖ۠ۥۘۧۚۤۙۛۥۘ۠ۗۖۘۙۗۦۘۧۚ" : "ۢ۠۫ۘ۠ۗۘۡۖۘۘۦ۟ۛ۬۠ۙۖۦۦۦۢۢۡۨۙۛۥۘ";
                                        case 2140139901:
                                            str9 = "۟ۗۨۨ۫ۘۛۚۢۚۛۜۘ۫ۤۤۚۜ۟ۛۥ۠ۗۘۡۙۥۖۛۜۜ";
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case -393953252:
                    str = "ۨ۠۫۠ۛۚۘۥۚۡۦۦۚۜۘۘۖۢۧۘۥ۠ۥۘۘۤۘۛۜۙ۠ۘۧۚ۠ۧۤۧۧۚۨۨۘ";
                    i4 = i3;
                case -363124176:
                    i = 0;
                    str = "ۡ۠ۛ۟ۘ۟ۙۨ۫۬ۜۥۘۤۖۗۖۜۧۘۢۚۖۘ۟ۖۖۘۤۨۡۡ۫ۡۗۗۡۘ۟۠ۡۘۗۧۧ۬ۤۛ۠۟ۜۘ۬ۤ۫";
                case -353700258:
                    str = "۠ۗۧۚۙۜۘۜۚۗۚۡ۠ۗ۬ۤۛۘۘ۬ۗۧۗۚۢۜ۠۬۬ۤۚ۟ۦ۫ۤۙ۠ۤ۫۟ۘۧۡۘۜۘۗۖۚۥۘۤۢۤۙۘ۬";
                    i6 = i7;
                case -228130657:
                    str = "ۢۢۜۘۛۘۧۘۧۥۜۘۛۡۜۘۗ۠ۚ۟ۘۤۧۛۜۦۨۡۘۚۦۦۘۨۨ۫ۥۘۥۖ۠ۡۤۢ۫ۢ۠ۨ";
                case -202979487:
                    str = "ۙۘۡۘۚۜۜۨ۬۠ۚۗۥۘۜۦۚۛ۫ۖۙۛۡۤۥۘۨۨۗۥۜۧۘۧ۠ۘ۠ۧۜ۫ۘۖۚۗۘ";
                case -201107889:
                    i2 = 0;
                    str = "ۢۘۧۘۗۜۘۜ۟ۥۘ۫ۢۚۚۢۡۘ۫ۘۥ۬۠۬۟ۖۦۤۛۗۘۨ۬";
                case -86470162:
                    jSONArrayOptJSONArray4 = jSONObjectOptJSONObject.optJSONArray("other");
                    str = "ۨۦۦ۬ۦۛۢۜۜ۬ۘۚۦۙۦ۫ۧ۬۠۫ۙۧۘۘ۬ۖۧۛۦۘ";
                case 102950060:
                    arrayList = new ArrayList();
                    str = "۟ۤۧۧۦ۠۫۬ۤۨۜۘۦۧۥۘۙۗ۠ۜۤۖۘۚۛ۬ۚۘۥۘۙۧۡۘۡۜۖۘۦۤۦۧ۬ۖۗ۫ۖۘۘۚۖۘۚۗۖۘ";
                case 118042191:
                    str = "ۚۙۡۥۦۛۛ۬ۜۘۚۢۛ۠ۖ۠۠ۥۙۖۚۡۥۢۛۧ۬۟ۢ۬ۖۘ۠ۗۥۘۨۛۗۗۦۥۘ۬۫ۡۢ۟ۢۜۡۧۘ";
                    i9 = i8;
                case 303500359:
                    str = "ۗۧۜۘ۠۟۟ۨ۠ۡۗۧۜ۫ۜۨۛۛۙۛ۫ۖۘۡۥۥۜۨ۟ۖۚۦۘۤۙۘۘۛۜۘۘ۟ۜ۠ۡۖۤ";
                    i3 = i4 + 1;
                case 362041217:
                    String str10 = "ۤۖۦۤۢ۫۫ۦۢۦۢۢۨۜۥۘۦ۬۬ۜۗۜۘۥ۬ۡۘ۠ۙۥۘۛ۬ۗۚ۟۟ۡ۠ۜۚۖۛۚۡۜۘۨۨۖۚۡۙۙۛۦۘۗۥۖ";
                    while (true) {
                        switch (str10.hashCode() ^ (-1912177771)) {
                            case -2123590601:
                                str = "ۥۡۡۘۥۖۥۘ۠ۡۨۦ۟ۧ۫ۜۥۙۦۘ۫ۙۨۨۧ۠ۙۦ۠ۦۜ۫";
                                break;
                            case -1225233099:
                                break;
                            case 872504258:
                                String str11 = "ۖۖۘۘۘۙۥۛۡۘۚ۫ۨ۠ۦۥۘۛۗۦۘۘ۫ۨۘۗۤۛۤۛۖ۠ۧۨ۟ۥۛ۟ۡۖۘ";
                                while (true) {
                                    switch (str11.hashCode() ^ (-650491515)) {
                                        case -1876801940:
                                            str11 = "۠۫ۤۜۨۤۡۙ۫ۖۙۡۤۡۘۙۡۖۘۛ۠ۨۘۥۥۥۛ۫ۡۚ۟۠۫ۥۜۙۖ۟ۗۡۨ۬ۗ";
                                        case -452871313:
                                            str10 = "ۡۜۨۚۜۜۘ۟ۘۖۜ۟ۜۚ۠ۥۘۗ۠ۤ۠ۜۤۢۥۜۧ۠ۜ۫ۙۥۦۧۙۡۥۘۚۜ۠ۙۖۢۢ۫ۙۥۜ۠ۘۚ۠ۗ۬ۥ";
                                            break;
                                        case 741582798:
                                            str10 = "۫ۜۦۘۜۡ۟ۢۚۗۜۧۜۥۦ۟ۢۚۛۥۖۗۚۡۡۘۖۖۧۘۛۨۖۘ۬ۡۘۘۛۥۘ۬ۦ۬۫ۜۚۚ۟ۜۘۜۡ";
                                            break;
                                        case 968738329:
                                            str11 = i4 < jSONArrayOptJSONArray2.length() ? "ۜۘۦ۠۬ۙۘۦۢۙۦ۟ۙ۬ۗۙۥۜۘۢۤۗۡۡۨۘۛ۫ۧ۠ۚۖۘۨۙۡۘۦۥۘۜۦۧۘۥۡۦۜۙۗۤۢۛۧۖۘ۠ۡۙ" : "۠ۛۦۧۦۖۘۜۜۤۛۙۥۥ۠ۢۦۤۧۘۙۛۨ۬۫۟۫ۦۘۢۨۘۘۤۥۘۢۥۜ۟ۧۘۘۛۙۥۘۗ۠ۦۜۗۦ";
                                    }
                                }
                                break;
                            case 1119684859:
                                str10 = "ۦۢۡۙۤۜۢۧۦۘ۫ۥۨ۟ۨ۠ۢۢ۠۬ۙۘۘۙۦۘۥۗۨۘۘۙۚۢۖۧۘۚ۬ۤۤۜۤۦۦۡۥ۠ۦۘۧۥۖۘ";
                        }
                    }
                    str = "ۢۤۡۘۧۢۦۚۦۙۥۧۜۦۦ۬ۨۘ۬ۡ۬ۗۤۥۧ۬ۗۦۛ۫ۨۘ";
                    break;
                case 385980450:
                    str = "ۖۥ۫۫ۚ۫۫ۗۘ۠ۖۖۘۖ۬ۤۦۗۜ۫ۘۖۚ۟ۚۘۗۦۡ۫ۖۘ۬ۤۜۘ۟ۨۨۜ۫ۡ۟ۤ۟ۙ۬ۗ۬ۥۘ";
                    i4 = i2;
                case 389090786:
                    k2.ZB3OAtr7(activity, jSONObjectOptJSONObject.optString("id", ""), jSONObjectOptJSONObject.optString("html", ""), jSONObjectOptJSONObject.optBoolean("lock", false), arrayList, arrayList2, jSONArrayOptJSONArray4);
                    str = "۬ۙۥۘۗۛۨۛ۫ۜ۬ۨۘ۬ۤۖۚۙۨۘۛ۟ۙۗۨۢۤۙۜۦۖ۠ۖۘۧ۠ۘۢ";
                case 617282407:
                    String str12 = "۫ۛۙۥۜۡ۫ۥۥۘۨۧۨۦۜ۠ۙۡۖۨۘۘۡ۬۟ۦۘۖۘ۬ۜۤ۠۫ۗۙ۠ۜۜ۟ۜۢۖۧۚۤۢۦ۠ۤ";
                    while (true) {
                        switch (str12.hashCode() ^ 1055798109) {
                            case -1860702984:
                                str = "ۦۙۙۥ۟۟ۚ۠۠ۖ۟ۛۨۗۡۢ۬ۡۗۧۜۘۚ۫ۧۚۖۦۚۛۡۘۢ۬ۡۘۙۛۧ";
                                break;
                            case -691050725:
                                break;
                            case 54270775:
                                String str13 = "ۢۢۖۖۧ۟ۜ۫ۖۦ۠۬ۙۜۨۘۧۨۢ۟ۛ۬ۡ۟ۤ۫ۢۛۜۧۨۘ۠ۙۖۘۜۧۙۖۤۜۘۦۙۤۨۤۥۤۛۘ۠ۙۨۘ۫۬ۖ";
                                while (true) {
                                    switch (str13.hashCode() ^ (-2122312871)) {
                                        case -83963421:
                                            str12 = "۟ۜۙۥۨۘۙ۟۟ۡۗۥۘۚۛۡۘ۫ۥۥۘۤ۬ۦۘۢۥۥۘۗۡ۟ۚۗۦۘ";
                                            break;
                                        case 187870834:
                                            str12 = "ۦ۫۟ۚۨۘ۠ۘۧۘۤۛ۠ۥ۟ۡۘ۬۟ۘۘۚۖۧۘۗ۟ۘۘۧۚۘۘ۟ۛۘ۬ۘۨ۟۟ۨۗۛۢۘۗۥۤۧۡ۠ۡۘۨۥۤ۟۫";
                                            break;
                                        case 1570707246:
                                            str13 = "۠ۚۤۢۜ۠ۦۗۦۘۨۧ۬ۧۤۙۙ۟ۥۨۤ۬ۦۦۦۤ۟ۙۡۜ۬۟ۛۦۧ۫۫ۖۗ۠ۨ۫ۥ";
                                        case 1757202907:
                                            str13 = i6 < jSONArrayOptJSONArray.length() ? "ۘۘۧۘۧۗۦ۟ۜ۟ۢۤۛۛۨ۠ۘۨۨۥۘۧۙ۟ۤ۬ۨۘۢۚۦۘۧ۟ۛۥۜۨۘۛۥۤۧۦۡ" : "۫۟ۛۥۢۨۛۛۨۘۖۢۛۗ۬ۦۤۦۨۘ۟ۜۙۙۦۘۘۤۘۧۘۦ۫";
                                    }
                                }
                                break;
                            case 1963459099:
                                str12 = "ۘۖۢۚۙۨۤۛۨۘ۫ۖۜۘۗۘۥۨۙۚۧۦۧۖۨۥۘۥۨ۫۟۟ۘ";
                        }
                    }
                    str = "۫ۛۚ۟۫ۥۘۘۖ۟۠ۙۘۘ۬ۤۗ۟ۙۡۖۦۡۘۗۡ۟۟ۛۨۘ۬۟ۖۘۜ۫ۜۧۧۖۚۖۙۙۙۢ";
                    break;
                case 792262302:
                    str = "ۖۥ۫۫ۚ۫۫ۗۘ۠ۖۖۘۖ۬ۤۦۗۜ۫ۘۖۚ۟ۚۘۗۦۡ۫ۖۘ۬ۤۜۘ۟ۨۨۜ۫ۡ۟ۤ۟ۙ۬ۗ۬ۥۘ";
                case 941397131:
                    String str14 = "ۛۡۦ۬۠ۖۘۗۡۧۘ۠ۗ۬۫۟ۨ۬ۜۚۧۚ۫۟ۦۨ۟ۜۡۤۛۢۡۤ۟ۢۙ";
                    while (true) {
                        switch (str14.hashCode() ^ 1190086372) {
                            case -794699877:
                                str = "ۜۤۘۘۨ۬۫۟ۡۥۘ۫ۤۚۗ۟ۧۥۤۥ۫ۛۜۘۧۜۦۘ۫ۤ۬ۚۤۨۘۖۧۛ۬ۢۡۘ۠ۜۢۘۘ۠ۛۤۙۨ۠ۖۨۨۗۗ";
                                break;
                            case -254956659:
                                String str15 = "۫ۗۤۥۚۛۖۜۡۖۛۡۘۚۗ۟ۛۨ۟ۖۡ۠ۥۗ۫ۜۨۨۘۢ۫ۡۘۙۧۘۘۨۗۗ";
                                while (true) {
                                    switch (str15.hashCode() ^ 756011520) {
                                        case -1213995641:
                                            str15 = "ۚۨ۠ۙۥۢۨۢۘۡۖۡۘۛ۟۬ۢ۠ۢۤۖۡۧۤۜۚۡۘۖ۟ۘۢۜۜۜ۠ۢ۠ۚۨۘۙۘۤۙ۬ۖۜۦ۟ۚۘۜۛ";
                                        case -891986143:
                                            str15 = jSONObjectOptJSONObject.optBoolean("enable", false) ? "ۢۥۖۘۖۥ۬ۘۖۨۨۛۡۙۤۘۘۖۙۥۘۗ۫ۥۘۗ۠ۜۘ۠ۜۦ۬۫ۘۘۛۢۥ۬ۢۘۖۛ۠۬ۜ" : "۠ۛۥ۬ۛۦۘ۬۫۟ۖۢۨۘۦۖۜ۫ۨۥ۫ۡۚۦۖۥۤۗۥۘ۫ۤۢۥۤۨۢۜۖۜ۠ۜ۠ۦۘۦۦۦۘۖۜۜۘ";
                                        case 51479678:
                                            str14 = "ۘۘۧۦۖۥۘۘ۫ۛۤۘۘ۠۠ۛۚ۫ۦۘۙۜۜۘۥۖۘۧۤۧۤۦۨۙۨۖ۫۟۫ۧۜۖۘۛۗۡ۠۫ۦۘۛۦۧۘ";
                                            break;
                                        case 392314822:
                                            str14 = "۟ۜۖۧ۫ۖۗۗۜۘۖۢۛۚۙۜۘۚ۟ۦۡ۟ۚۥۦۗ۟ۨۥۧۚۛۙۦۜ۬ۜۥۧ۟ۡۤۡ";
                                            break;
                                    }
                                }
                                break;
                            case 612455018:
                                break;
                            case 2090603983:
                                str14 = "۟۠۠ۥۦۖۘۦۥۡۦۦۨ۠۫ۡۜۤۦۘۨ۬ۡۘ۟ۙۦۘ۬۟۫ۤۨۜۦۡۨۘۖۧۘۘۨۤ۬ۖۗۤۛۡۦ۠ۘۦۘ۠ۨ۟ۡۙۜۘ";
                        }
                    }
                    str = "۬ۙۥۘۗۛۨۛ۫ۜ۬ۨۘ۬ۤۖۚۙۨۘۛ۟ۙۗۨۢۤۙۜۦۖ۠ۖۘۧ۠ۘۢ";
                    break;
                case 948439341:
                    arrayList2.add(jSONArrayOptJSONArray3.optString(i9));
                    str = "ۚ۟ۡۗۥۤۙ۫ۚ۫ۜۥۗۗۛۦۜۚ۠ۗۥۡۤۡۘ۬ۜۘۗۢۡۘۤۚۙ۠ۨۢ";
                case 1397261997:
                    break;
                case 1627664801:
                    str = "ۨۧۡۘۗۚۨ۠ۚۦۖ۟ۥۘۗۚۘ۫ۢۛ۫ۚۙ۠ۛۦۥۛ۟۫ۚۛ۟ۙ۬";
                    i8 = i9 + 1;
                case 1776733793:
                    str = "ۤۘۖۘ۠ۛ۟ۛۨۧۘۗۦۘۘۥۖۛۡۛۙ۠۫ۡۨۧۖۘۘۦۨۢۚ۫ۗ۟ۨۙۘۧۘ";
                    jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("white_list");
                case 1823134736:
                    jSONObjectOptJSONObject.optString("id", "");
                    str = "ۧۨۤۢۗ۬ۙۦۧۘۜۦۗۢ۠ۘۘۗ۬ۙۛۜۥ۬ۚ۫ۨۗۢۡۧۧۖۢۗۛۘۨ۠ۧۢۚۤۘ";
                case 1992728512:
                    str = "ۢۢۜۘۛۘۧۘۧۥۜۘۛۡۜۘۗ۠ۚ۟ۘۤۧۛۜۦۨۡۘۚۦۦۘۨۨ۫ۥۘۥۖ۠ۡۤۢ۫ۢ۠ۨ";
                    i6 = i;
                case 2039385679:
                    String str16 = "۠ۘۗۤۥۥۘۡۡۡۘۚۤۖۢۥ۫ۢۜۡۡ۟ۧۚۘۘۙۙۥۘۗ۫ۤ";
                    while (true) {
                        switch (str16.hashCode() ^ 327743146) {
                            case -1621907169:
                                str16 = "۟ۢ۬ۢۨۚ۠ۨۘۗۗ۫ۦ۠ۥۚ۟ۧ۬ۚۥۘۗۛۘۘۙۛۗ۠ۖۘۤۗۦۖۙ۫";
                            case -1477975058:
                                str = "ۥۥۤۨۛ۠ۧۘۛۖۚۨۧۗۦۧۛۚۦۢۦۘ۬ۧۗۜۢۦۦۖۗ";
                                break;
                            case -432733823:
                                String str17 = "ۡ۟ۛۛۖۡۖۖۧ۫ۥ۫ۨۚ۟ۨۖۖۦۥ۠ۜۧۜۘۙۚۖۛۢۥ";
                                while (true) {
                                    switch (str17.hashCode() ^ (-1959994146)) {
                                        case -1514592282:
                                            str16 = "ۧۖ۟ۦۗۥۘۘۢۜۘ۫ۤۢۘۚ۬ۡ۟ۚۚۚ۠ۜۧۘۘۜۖۛۛۚ۫";
                                            break;
                                        case 1248649693:
                                            str17 = jSONArrayOptJSONArray3 != null ? "ۛۢۦۗۧۖۖۖۦۘۤ۠ۧ۬ۚۡۖۜۘۛ۬۟ۥۡ۟ۜ۫ۤۙۜۖۘۛ۠ۜۘ۠ۦۛۥۚ۟۠۫ۦۘ" : "ۖ۟۫ۨۙۚۧۚ۟ۘۥ۠۬ۛۧ۬۟ۨۧۥۘۜۦۖۗۜۦۘۤۗۘۘۛ۟ۘ۫ۜۧۘۘۖۖۘۛۘۜۘ";
                                        case 1843415542:
                                            str16 = "ۙۧۥۚۘ۟ۤۥۧۜ۬۟ۥۖۤ۫ۡۦۦۤ۬ۧۥۧۘۦ۬ۘۘ۠ۚۥۘۜۘۖۙ۫ۙۦۚۚۥۧۙ";
                                            break;
                                        case 1987737049:
                                            str17 = "ۘۥۙۡۜۡۦ۬۟ۘۡ۟ۛۦۗۘۥۗۜۙۧۦۨۘۢ۫ۤۗۡۜ";
                                    }
                                }
                                break;
                            case -418589932:
                                break;
                        }
                    }
                    str = "ۖۛۘۜۧۘۘۗ۫۬ۚ۬۟ۙۘۨۤۖۖۧۢۗۡۖۖۘۘۧۖۘۧۡۡۘ";
                    break;
                case 2100080376:
                    String str18 = "ۥۢۗۡ۠ۦۘۙۘۗۧ۬ۖۘ۠ۤۘۘۚۡۥۘۡۚ۠ۜۦۥۘۚۨۙۧ۫ۛ";
                    while (true) {
                        switch (str18.hashCode() ^ (-2122300886)) {
                            case -1505611843:
                                str = "ۢۗۦۧۤۡۘ۬ۡ۠ۧۜ۫ۗ۟ۜۘۛۥۨ۟۟۠ۦۢ۟ۢۧۘۘۨ۟ۨ۬ۨ۟ۧ۬ۦۙۥ۟ۗۖۨۗۗۤۧۛۧ";
                                break;
                            case 559552151:
                                break;
                            case 670136484:
                                str18 = "ۧۗۨۘۛۢۡۜۛۖۘ۫ۡ۬ۢ۫ۜۘۘۧ۬ۘۘۘۙ۬ۥۘۙۤۢۘۧۦۘ";
                            case 2144079252:
                                String str19 = "ۤۚۙۗۢۙۙۢۥۚۖۡۛۤ۫ۛ۠ۡۡۦۖۘ۠ۛۧ۫ۖ۠۠ۜۙ۬ۥۦۘۦۙۚ";
                                while (true) {
                                    switch (str19.hashCode() ^ 807019005) {
                                        case -1628628232:
                                            str18 = "۬ۦۙ۬ۗۦ۫۠ۦۘۧۨۤۨ۟ۙۦۚۥۢ۟ۥۢۨۜۙۤ۫ۧۡ۫";
                                            break;
                                        case 989920416:
                                            str19 = jSONObjectOptJSONObject != null ? "ۚۧ۠ۨۤۨۧۖ۫۠ۙۜ۟ۢۦۘ۬ۧۢ۠۫۬ۦۤۜ۟ۨۖۘۡۨ۫" : "ۚۙۚۧ۬ۙ۟ۙۢۢۖۦۘۖۘۤ۫ۜ۟ۘۖۥۖۚۦ۫۠ۜۘۘۡۙۜۖۜۘۗۙۘ";
                                        case 1065906615:
                                            str19 = "ۛۡۡۨ۠ۖۖۨۖۘ۫ۗۤۙۛۘۘۚۛۥ۠ۥۖۥۤۧ۫ۦۘۚۧۨۜۙۘۧ۬ۡۥۧ۬ۥۛۜۘ";
                                        case 1205325216:
                                            str18 = "ۜۛۘ۟ۧۡۘۜۖ۟۠ۖۖ۫ۧۙ۫۬۟۟ۥۗۙ۫ۡۘۜۦۥ۬ۗۡۘۦۢ۬ۖۥ۫ۧۖۦۙۢۚ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case 2136491599:
                    str = "ۙۘۡۘۚۜۜۨ۬۠ۚۗۥۘۜۦۚۛ۫ۖۙۛۡۤۥۘۨۨۗۥۜۧۘۧ۠ۘ۠ۧۜ۫ۘۖۚۗۘ";
                    i9 = i5;
            }
            return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:102:0x0177. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:135:0x01f7. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:152:0x0269. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:183:0x02eb. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:215:0x0380. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:219:0x038f. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:223:0x03aa. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r4v2 */
    public static void U9oqb6XR(Activity activity, JSONObject jSONObject) throws NumberFormatException {
        boolean z;
        String string;
        String strDecrypt = "enableMessagePopups";
        ?? r4 = 0;
        String str = "ۥ۫ۚ۠ۚ۫ۨۨ۬۟۠ۥۢۤۗۚ۬ۨۘ۫ۡۦۘۖۘۦۘۦۗۘ۫ۤ۫ۢۗۡۦ۟ۦۥ۫ۤۙۛۚ۬ۚۤۧ";
        while (true) {
            switch (str.hashCode() ^ 169151238) {
                case -1512016653:
                    JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("Messagepopups");
                    String str2 = "۠ۚ۟۠ۦۢۜ۬ۡۨۙۙ۬۬۠۠ۚ۫ۧۚۜۗۚۜۘۤۦۛۥۡۨۘۡۤۧۜ۟ۡۥۙ۫۬ۡ۫ۖۚۧ۠ۦۨۘ";
                    while (true) {
                        switch (str2.hashCode() ^ (-874718677)) {
                            case -1776894395:
                                str2 = "ۗۡ۬ۙۧۘۨۡۖ۟۬ۜۘۧۙۤۖۡۛ۬ۘۜۛۦۖۘۜۤۤۤۦۥ۬ۜۨۘۗۙۥۘۘۜۨۘۡ۫ۜۘۤۤۥ۫ۦ۟";
                                break;
                            case -1007620995:
                                int i = 0;
                                while (true) {
                                    String str3 = "ۢۧۚ۬۟ۧۜۨۡۘۧۛۚۧۖۗۧ۟۫۬ۧ۠ۤۜۘۘ۟۟ۘۨۤۨۘۙۛۨۘۤۡ۫ۜۢۖۘۜۢ";
                                    while (true) {
                                        switch (str3.hashCode() ^ (-1330869280)) {
                                            case -1021770796:
                                                String str4 = "ۧۚۗۖۙۜۦۘۘۘۦۛۗۤۖۘۙۦۤۥۛۨۗۦ۠ۤۗ۠ۥ۫ۡ۫۟ۢ۬ۜۖۚۜۤۛۘۘۢ۬ۜۨۦۖۘۧۖ۬ۜ۠۟";
                                                while (true) {
                                                    switch (str4.hashCode() ^ (-1708779476)) {
                                                        case -437770813:
                                                            str3 = "ۥۚۘۧۘۦ۠ۢۦۨ۫ۗۙۘ۠ۧۘۘۥۙۗ۫ۥۘۘۧۘۛۗۥۚ۬ۖۚۥۥۖۜ۠ۚۨ۟ۚۘۘۨۛۧۖۤۧۙۤۜ";
                                                            break;
                                                        case -61352374:
                                                            str4 = i < jSONArrayOptJSONArray.length() ? "ۖۧ۟ۧ۫ۙۗۖۧ۟۫ۢۦۡۘ۠۫۠۟ۥۧۘۗۨۘ۠ۡۜۧ۟ۧۡۖۘۘۥ۫ۛۧۢۥۡۘۥۗۚ۫ۤۦۥۘ" : "۟ۦ۠ۧۖۨۨۢۘ۠۫ۥۘۥۡۦۦۤۥۘ۟ۚۤۡۢۖۘۘ۫ۘۘۥ۬ۧۙ۬ۥۘۦۗۢ۠ۖۘۧۖۘ";
                                                        case 1802478908:
                                                            str4 = "۬۟ۨۘۦۖۚۘۤۡ۟ۨۥۜۦ۫ۤۚۥ۫۟ۚ۫ۜۢۜۜۘۘۘۧ۬ۛۦ۫ۢۧ";
                                                        case 2072934718:
                                                            str3 = "ۨ۠۫ۚ۟ۛ۬۬ۥۥۧۜۜ۟ۛۢ۠ۨۦۘۦ۬ۘۘۜۨۡۨۨۘۛ۬ۡۘۛۛ۬ۚۤۜۨۘۥ";
                                                            break;
                                                    }
                                                }
                                                break;
                                            case -798314078:
                                                str3 = "ۡۛۡۡۖۦۤ۟ۙ۠ۨۖۖۜۘۖۡۖ۟ۥۖۤ۬ۙ۫۬۟ۧۢۜۚۛۤۚۛ۟ۡۧۖۘۡ۫ۗۖۥۢۨۘۥۛۢۨ";
                                                break;
                                            case -370867134:
                                                return;
                                            case 1345082604:
                                                JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                                                String str5 = "ۖۥۤۚۙ۠ۖ۠ۨۦۗۚۛۚۢ۫ۦۚۗ۠ۡ۠۬ۘۘۡ۬ۥۘۢۥ۫ۥ۫۠ۦۜۧۚۨۜۤۚۜۥۤ۠ۛۗۖۘ";
                                                while (true) {
                                                    switch (str5.hashCode() ^ (-1696353024)) {
                                                        case -1973109428:
                                                            str5 = "ۥۥۡۘۚۤۡۘۨۡۜۘۖۢۛۚۨۜۦۦۦ۠ۨۗ۫ۜۚ۠ۗۖ۫۬ۗۨۜۚۧۜۘۡ۬ۖۨۡۧۗۥۜ۟۫ۘ";
                                                        case -1131587377:
                                                            break;
                                                        case -294802615:
                                                            String str6 = "ۜۥۜۘۜۥۖۘۗۡ۠ۥۛۦۘۗۖۛۛۘۦۘ۟۬ۡۗ۟ۜۘۨۢۘۘۗۖۡۘۚۨۥۘۥ۫ۥۧۛۨۘۗۜ";
                                                            while (true) {
                                                                switch (str6.hashCode() ^ (-547355705)) {
                                                                    case -1978175278:
                                                                        break;
                                                                    case -1787991301:
                                                                        String str7 = "ۤۗۘۘۚۡۖۘۨۢ۬۟ۗۜۢۦۥۡۘۘۛۧۛۚۗۦۗۛۙ۟۟ۨۢۥ۬۫ۜۘ۫ۦۦ۟ۚۦۘ";
                                                                        while (true) {
                                                                            switch (str7.hashCode() ^ 1369278101) {
                                                                                case -1100716438:
                                                                                    str7 = jSONObjectOptJSONObject.optBoolean("enable", r4) ? "ۛۧۙۚۧۡۘۛۚۥۘۗۚۦۢۖ۠ۗۧۡۘۘۖۘۥۥۖ۬ۧۖۧۘۜۘۤۦۤۥۗۦۗ۠ۙۛ۬ۖۢۨۢ۠ۢ۫ۛ۫۟ۛۢۙ" : "ۧۛۥۘۤۗۚۗۡۥۘۚ۬ۙۙ۠ۡۙۛۡۘ۠ۘۥۘۜ۫ۦ۠ۖۘۢۚۦۘ";
                                                                                case -1045768324:
                                                                                    str6 = "ۛۙۘۘ۟ۙۨ۫ۘ۠ۦۘۤۡۨۘۡۢۜۘۢ۟ۘۙۜۥۘ۫ۙۚۘۧۘۘۨۗۚۙۖۘ۫ۤۢۤۧ۬۫ۖۧۘۖۖۛۗۖۚۦۜ۠";
                                                                                    break;
                                                                                case 795994117:
                                                                                    str7 = "ۚ۫ۚۢۜۜ۟ۙۨ۬ۙۨۘۤۤۛ۫۠ۙۜ۫ۥۙۜۤۦۨۡۘ۟ۡ۟ۤۥ۟۠ۢۦۘ";
                                                                                case 1399206113:
                                                                                    str6 = "ۦۘۢ۟ۧۜۘ۫ۛ۫ۢۧۨۘۗۜۚۘ۠ۘۘۚۘۦۘۜۦ۬ۙۧۥۛۚۦ";
                                                                                    break;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case 102662512:
                                                                        ArrayList arrayList = new ArrayList();
                                                                        JSONArray jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("white_list");
                                                                        String str8 = "ۡ۠ۡۘۨۙۦۘۚ۫ۜۘۘ۠۠ۗ۠ۘۨۖ۠ۤۦۘۘۜ۬ۚۘۚۚۦۗۖۘ";
                                                                        while (true) {
                                                                            switch (str8.hashCode() ^ 478301462) {
                                                                                case -1089255658:
                                                                                    break;
                                                                                case -1006618421:
                                                                                    int i2 = 0;
                                                                                    while (true) {
                                                                                        String str9 = "ۢۡۙۘۨۜۘۧۦۡۘۘ۠ۨۜ۬ۛۛۜۘۤۥ۬ۖۡۘۘۖ۟ۤۢ۠ۧۥۗۘ۫ۛۗۙۖۦۘۘ۬ۥۜۘۛ۟۠";
                                                                                        while (true) {
                                                                                            switch (str9.hashCode() ^ (-1496538579)) {
                                                                                                case -1162540578:
                                                                                                    String str10 = "۬ۥۨۘۨۙ۟ۖۗ۠ۘۘۖۤۖۚۙ۬۫ۧۙۙۚۖۘ۬ۖۥۘۚۗ۫ۘۖۜۜۘۖۘۜۡۘ۫۬۠۠ۡۘۘۗۚۖۘۙۖ۬ۙۖ۠";
                                                                                                    while (true) {
                                                                                                        switch (str10.hashCode() ^ 372163489) {
                                                                                                            case -680659515:
                                                                                                                str10 = "ۘۦۘ۠۫ۧۢۖۜۗۜۖۘۗ۬ۘۘۚۚ۬ۨۛۘۘۙۚۨۨۘۚۧ۠ۨۖۥۜۘۘۚۦۡۧۦۘۘ۬ۙۧ۟ۥۘ۟ۗۤ۫ۙۖۘۖ۫ۢ";
                                                                                                            case 45175345:
                                                                                                                str9 = "۫ۦۡ۠ۤۖۜۥۖۡۖۘۘۦۖۜۘۖۛۗۤۖۗۘۧۢۧۜۡ۟ۘۢۘۘۥۜ۟ۥ۫ۧۜۨۖۛ";
                                                                                                                break;
                                                                                                            case 693708115:
                                                                                                                str10 = i2 < jSONArrayOptJSONArray2.length() ? "۟ۗۘۘۜۡۛ۫ۚۜ۠ۜۙ۬ۦۙ۠ۜۜۘۦۨۦ۠ۡۘۘۙ۠ۜۜۖۜۘ۠ۦۥۘۡ۫ۦۘ" : "۟ۘۖۘ۫ۗۥۘۧۡۗۥ۟ۦۙۖۙۥۙ۬۬ۥۨۘ۠ۥ۟ۜۖۛۙۚۦۘۚۛ۠ۦۗۥ۫ۘۜۙ۬ۧ۟۬ۛ۟ۚۘ۠ۢۡۘۡ۫ۜۘ";
                                                                                                            case 1870674478:
                                                                                                                str9 = "۫ۙۥۘۛۜۢ۟ۖۤۦۦۢۖۙۗۥ۬ۡ۟ۚۜ۬ۖ۟ۚۚۘ۫ۥ۟۟ۨۦۘۡۤۗ۬۟ۡۘۙۢۨۘ";
                                                                                                                break;
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case -1155951187:
                                                                                                    str9 = "۠ۜۨۘۚۖ۬۬ۗۤۤۥ۬ۗ۟ۦۜۖۘۥۢۡۡۛۘۘۗۧۜۘۜۤۙۡۦۧۡۖۨۘ";
                                                                                                case -750274681:
                                                                                                    break;
                                                                                                case 1768627560:
                                                                                                    break;
                                                                                            }
                                                                                            break;
                                                                                        }
                                                                                        arrayList.add(jSONArrayOptJSONArray2.optString(i2));
                                                                                        i2++;
                                                                                    }
                                                                                    break;
                                                                                case 454482717:
                                                                                    str8 = "ۦۥۦۘۥۦۥۘۚۛۖۧۥۥۤۦۧۘۘۤۛۗۨۡۦۛ۬ۡۨۜۘۦۘۘ۬ۤۤۚۨ۠";
                                                                                case 979552878:
                                                                                    String str11 = "ۖۦ۬ۦۘۗۧۥۙ۟ۖۘۜۡۘۘۥۖۗ۠ۖۢۥۜۥۘ۟ۧۘۘۚۨۖۘۧ۫۠۫ۛۧ۫ۗۛۢ۫ۘۘۙۙ۫۟۬ۚ";
                                                                                    while (true) {
                                                                                        switch (str11.hashCode() ^ (-1774211131)) {
                                                                                            case -1055993481:
                                                                                                str11 = "۬ۨۜۘۨۡ۬ۥۛۧ۠ۦۡۘ۟ۜۤ۠۠ۥۨ۠۫۫ۥۡۘ۠۠ۤ۫ۜۦۧۤ۫ۜۖ۠۟ۘۜۘۨۥۧۘۥۥۢۧۙۖۘ۫ۨ۠۟ۤۗ";
                                                                                            case -900036090:
                                                                                                str11 = jSONArrayOptJSONArray2 != null ? "ۚۧۧۖۧۢۧۖ۬ۨۛۖۙۧ۬ۙۜۥۧۥۥۘۗ۟ۙۖ۠ۜۘۗ۬۬" : "ۢۚۖۡ۟۫۟ۖۖۘۡۗۦۥ۟ۥۘۡ۬ۧۗۖۚۢۗۖۖۘۘ۠ۤۚۦ۫ۜۖ۠ۨۥۘۡۘۧۚۥۘ۟ۤ۬ۙۥۜۘۗۙۧۤۧ۠";
                                                                                            case -860366970:
                                                                                                str8 = "ۦۚۙۖۘۖۘ۫ۜۖۘ۫ۥۦۦۖ۬ۗۤۜۘ۬ۧۚ۟۬۬ۨۘۡۗۢۛۦۦۛۘۘ۫ۗۚ۬ۥ۠";
                                                                                                break;
                                                                                            case -109990749:
                                                                                                str8 = "ۙ۫ۥۘ۬ۙۨۘۙ۠ۜۘۡ۠ۥۘۢۤۜۚۘۢۗ۠ۚۛۖۖۥ۟ۘ۟ۗ";
                                                                                                break;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                            }
                                                                        }
                                                                        ArrayList arrayList2 = new ArrayList();
                                                                        JSONArray jSONArrayOptJSONArray3 = jSONObjectOptJSONObject.optJSONArray("black_list");
                                                                        String str12 = "ۢۡۘۘۖ۬ۡۘۢ۠۠۫ۥۗۥۖۘۡ۠ۨۤۜۘۢۨۘۛۡ۟ۛۦۨۘ۫۫ۗۡۥ۠";
                                                                        while (true) {
                                                                            switch (str12.hashCode() ^ 950253525) {
                                                                                case -1657281986:
                                                                                    int i3 = 0;
                                                                                    while (true) {
                                                                                        String str13 = "ۘۙۖۘۚۦۘۤ۟۠۬۟ۗۧ۬۟ۜۖۚ۫ۧ۟ۢۦۗ۫۠ۘۤۢۢ۫ۥۘۥ۟ۖۘ";
                                                                                        while (true) {
                                                                                            switch (str13.hashCode() ^ (-1084187661)) {
                                                                                                case -767417432:
                                                                                                    str13 = "ۦ۬ۨۗ۠ۡۘۜۗۨۡ۬ۘۘۜۨۥۢۢۜۥۙۛۥۚۡۘۨۙۜۘۖۨۡۘۢۨۧۛۧۖۘ۠ۧۥۘۨۚۖۘۡۘۘۘۙۨۢۖ۫ۡۥۙۦۘ";
                                                                                                case 605681960:
                                                                                                    String str14 = "ۜۛۨۘ۟۬ۖۘۖۚۦۘۡۥۙۚۢۦۘ۫ۥۘۘۨ۟ۢ۠ۘۚۦ۠ۦۡۙ";
                                                                                                    while (true) {
                                                                                                        switch (str14.hashCode() ^ 820697672) {
                                                                                                            case -1917709629:
                                                                                                                str13 = "۬ۜۜۢۥۙۨۥۘ۟ۙۖ۬ۜ۟ۖۘۥ۬ۙۜ۟ۤۡۜۜۡۘۙۚۨۘۧۛۘۖ۬ۜۛۤۖۘۛۛۘۘۖۡۛۦۤۜۘ";
                                                                                                                break;
                                                                                                            case -1486413786:
                                                                                                                str13 = "ۖ۟ۦۥۙۜ۠ۡ۠۫ۗۡۡۘۡۘ۟ۙۨۨ۬۠ۧۨۖۘۚۤۛ۫۟ۦۥ۠ۖۘۛۘ";
                                                                                                                break;
                                                                                                            case -1071826199:
                                                                                                                str14 = "ۖۨۖ۟ۢۢۛۛۥۡۦۦۘۨۤۜۘۘۜۧۙ۬ۙۢ۠ۥۚۖۧۤۚۦۡۗ۟ۧۨۜۚۖ۠ۢ۟ۗۘۧۡ۬۠";
                                                                                                            case 1640121936:
                                                                                                                str14 = i3 < jSONArrayOptJSONArray3.length() ? "ۥۥۚۚۡۡۡۜۢۘۦۚۘۘۡۧۘۖۧۢۧۙ۟۬ۡ۟ۛۙۦۥۘۛۥ۟ۗۢ۬ۨۦۥ۟ۥۨ" : "ۛۦۡۘۘۜۦۥۛۛۧۙۦۤۚۙۤ۬ۢ۟ۗ۬ۜۥۘۘ۫ۜۤۖۗ۟۬۬۟ۨۜۗۙ۬ۚۗ۠۫ۨۖۧۙ۬";
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case 1910999322:
                                                                                                    break;
                                                                                                case 2055872048:
                                                                                                    break;
                                                                                            }
                                                                                            break;
                                                                                        }
                                                                                        arrayList2.add(jSONArrayOptJSONArray3.optString(i3));
                                                                                        i3++;
                                                                                    }
                                                                                    break;
                                                                                case 1321520619:
                                                                                    String str15 = "ۚۖ۠ۚۦۜۘ۟ۚۦۘۘۨۜۘۥۘۦۘ۟۬ۧۚۤۛۦ۠ۤۚ۫ۦۘۦۚ۬ۖ۫ۜۘۥۘۖۦۡ۠۟ۢۚۜ۬ۨۘۚ۟ۥۘ";
                                                                                    while (true) {
                                                                                        switch (str15.hashCode() ^ 67455133) {
                                                                                            case -408566607:
                                                                                                str15 = "ۥۡ۟ۛۚۤۧۤ۬ۛۛۗۦۜۜ۬۠ۘۤۚ۠۠ۨۨۘۘۧۦۨ";
                                                                                            case 246479821:
                                                                                                str15 = jSONArrayOptJSONArray3 != null ? "ۧۥۧۚۨ۫ۧۡۧۘۖۧۡ۠ۘۥۢۡۜۘۙۖۥۦ۟ۢۛۜۗۘۤۖۘ" : "ۦ۬ۜۗۢ۠ۜۜۘۨۤۚۚۢۦۘۦۛۚۘۡۛۛۢۢۡ۠ۜۘۗ۫۟ۚۤۖۘ۫ۤۤ";
                                                                                            case 722039843:
                                                                                                str12 = "۠۟ۨۘۨۡۙۗ۠ۛۖۦ۟ۜ۠ۥۧۢۤۢۜۤ۬ۖۘ۫ۖۥۖۗۨۘۙ۫۬۠ۙ۬ۗۗ۠ۜۖ۫۫ۘۧۘۢ۬ۥ";
                                                                                                break;
                                                                                            case 1504112214:
                                                                                                str12 = "ۤۢۥۢۜۦۘ۫ۙ۬ۢۘۦۘ۫ۥ۠ۡۤۘۘ۠ۖۖۢۧۚۡ۬ۙۤۡۗۡۦۨۧ۬۟";
                                                                                                break;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case 1650798743:
                                                                                    break;
                                                                                case 1933926235:
                                                                                    str12 = "ۚۜۧۦۨۦۨ۫ۙۨۘۧۘۦۘ۠ۢۜۗۥۗۢۘۦ۟ۢۖۦۘۦۦۧۘ۟۟۠۫ۜۨۘۤۜۖۘ۟ۘۤۘۜۜۘ۟ۡۜ";
                                                                            }
                                                                        }
                                                                        int iOptInt = jSONObjectOptJSONObject.optInt("popup_type", r4);
                                                                        String strOptString = jSONObjectOptJSONObject.optString("id", "");
                                                                        String strOptString2 = jSONObjectOptJSONObject.optString("message", "");
                                                                        int iOptInt2 = jSONObjectOptJSONObject.optInt("interval", r4);
                                                                        String str16 = "ۖۧۨۘۦۦۜۘۧۛ۠ۦۚۚۛ۟ۘۘ۬ۡۡۘۜۙۧۖۜۦۘۦۢۘۥۥۙۨ۫ۖ۟ۨۦ";
                                                                        while (true) {
                                                                            switch (str16.hashCode() ^ 551759540) {
                                                                                case -1570595850:
                                                                                    String str17 = "۬۠ۥۨۧۜۡۤۘۘۖۖۗ۫۠ۥۘۥۥ۠ۜۗ۬ۖۢۨۘۘۙۥۘ۬۟ۧ";
                                                                                    while (true) {
                                                                                        switch (str17.hashCode() ^ (-7497276)) {
                                                                                            case -1945615376:
                                                                                                str17 = "۠ۤ۟۟ۤۖۘۙ۠ۤۨۛۚۢۤ۫ۜۜۗۡ۠ۥۘ۠ۖۤۜۤۘۜۘۚۗۙ۠ۡۗۖۘ۬ۢۗۢۙۖ";
                                                                                            case -1386328480:
                                                                                                str16 = "ۘۥۢۥۢۜۘ۠ۦۗۨۖۥۥۘ۬ۢۡۛۦۖ۟ۚۨ۫ۛ۬ۦۘۜۤۖۛۢۙ۠ۘۧۦۘۥۘۢۥۜ۟ۛۜۘۖۚ";
                                                                                                break;
                                                                                            case -473462811:
                                                                                                str16 = "۟ۦۡۡۗۖۘۧ۬ۡ۟ۥۚۙ۠ۘۘۥۨ۫ۨۚۧۚ۬ۥۨ۟ۗۚۗۖۙۙ۬ۦۘۡۘۖۜۦۘۧ۬ۜۘ۬ۧ۫۟ۢۡ";
                                                                                                break;
                                                                                            case 1773645575:
                                                                                                str17 = iOptInt2 > 0 ? "ۚۚۨۙۜۙ۠ۦۜۘۢۤۧ۫۟ۖۧۧۨۚۥۜۦۘۘۧ۠ۥۘۧۛ۫ۚ۬ۢ۠۟ۧۚۦۘۙۧۛ" : "۫ۗۖۘ۫۬ۚۡۤۢۥۛۛۗۖۦۘ۬ۜۥۢۦۛۜۘۧۚۜۘۢۦۘۗۤۤ۟۠۠ۦۢ۠ۤۗۡۘ";
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case -1078812535:
                                                                                    SharedPreferences sharedPreferences = activity.getSharedPreferences("dialog_close_log", r4);
                                                                                    String strSubstring = null;
                                                                                    String str18 = "ۚۥۜۘۤ۫ۨۘ۫۟ۘۘ۫۟ۦ۟ۘۨۘۗۘۗۥۤۨۜ۠ۘۘۧۢ۫ۦۢۘ";
                                                                                    while (true) {
                                                                                        switch (str18.hashCode() ^ 2083257677) {
                                                                                            case -1190485183:
                                                                                                String str19 = "ۨ۬۫۬ۜۖ۠ۛ۠۟ۧۖۘۗ۫ۥۤۢ۫ۖۗۖۥ۬ۨۘۖۦ۫ۢۙ۫۬ۛۖۧۤۜۘ";
                                                                                                while (true) {
                                                                                                    switch (str19.hashCode() ^ 655766049) {
                                                                                                        case -1485472032:
                                                                                                            str18 = "ۢۗۘۘۜ۠۬ۤۚۘ۫ۨۘۢۢ۟۫ۗۡۡۢ۟ۡۖۚۥ۟ۖۦۖۘ۫۠ۡۘۨۡۦۘۦۡۨۜۡ۫ۙۧۘۖۘۗ۫۠ۜ۠۬ۤ";
                                                                                                            break;
                                                                                                        case -1358395826:
                                                                                                            str19 = "ۥۡۦۘۗ۫ۜۘۧ۠ۛۖۡۡۙۦۛ۬ۡۘۗۛۤۙ۫ۡۘۜ۠ۡۘۢ۫ۧۛۖۘ۬ۚ۫ۧۛۡۘ۠۫ۜۗۖۜۘۘ۟ۧۨۗۜۘۦۙ۟";
                                                                                                        case 1823034630:
                                                                                                            str19 = iOptInt == 0 ? "۟ۜۨۘ۫ۚۚۙۤ۠۟ۤۨۘ۠۠ۢۨۥۜۘ۫ۛۥۨۜۤۨ۫ۨۘۗۗۦۘ۬ۢۨۘۜ۟ۡۘ" : "ۜ۟ۨۘ۬۠ۘۘۛۥۡۘۥۖۜ۠ۙۤۡ۟ۚۥۧۡۘۡۛۛ۬ۛۜۥۗۤ";
                                                                                                        case 2036297496:
                                                                                                            str18 = "ۘ۬ۦۘۢۜۜۘۡۗۨۗ۬ۖۚۖۙۥۦۚۜۛۘۘۦۚۛۜۗ۟ۘۡۢ";
                                                                                                            break;
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                            case -628965285:
                                                                                                string = sharedPreferences.getString(h.e("Rshbc1M+k65txQ==\n", "FbEoBzZTx8s=\n", new StringBuilder(), strOptString), null);
                                                                                                break;
                                                                                            case 725745548:
                                                                                                str18 = "ۡۗۜۨۨۥۨۜ۫ۗ۫ۡۗ۠ۡۨۥۡۘ۠ۤۥۘۜۡۨۧۜ۠ۤۚ۠۠ۦ۠ۚۢۥۥۥۨۡۘۛۜ۫ۚۥۘۜۤۚ۟";
                                                                                            case 1172143061:
                                                                                                string = sharedPreferences.getString(h.e("MhWTRw==\n", "ZnDrM5e4r3s=\n", new StringBuilder(), strOptString), null);
                                                                                                break;
                                                                                        }
                                                                                    }
                                                                                    String str20 = "ۢۙۜۖۛۤۖۗۗۖۗۡۦۙۖۘ۠۬ۢۨ۠ۥۘ۠ۛۖۘ۫ۜۙۥ۫ۡ۠ۢۡۥۚ";
                                                                                    while (true) {
                                                                                        switch (str20.hashCode() ^ 1664921202) {
                                                                                            case -979757613:
                                                                                                String[] strArrSplit = string.split("&&&");
                                                                                                int length = strArrSplit.length;
                                                                                                long j = 0;
                                                                                                int i4 = 0;
                                                                                                while (true) {
                                                                                                    String str21 = "ۚ۠ۥۘۜ۟ۤ۬ۖ۟ۚ۫ۥۗ۠ۥۡ۠۬۬ۥۖۘۨ۫ۘۘۤۛ۟ۨۡۘ۫ۧۤۥۦۙۙۨ۠ۤۡۜۚۛ۬ۙۖۧۘ";
                                                                                                    while (true) {
                                                                                                        switch (str21.hashCode() ^ 1846971726) {
                                                                                                            case -2076453440:
                                                                                                                String str22 = "ۢۖۚۗۤۤۚۨۗۗۚ۠ۧۦۡۘۨ۟ۜۗۘۜۖۚۧ۠۟ۚۜۦۘۖۛۥۡۛۡۧۦۘۘ۟۟۟ۧۚۨۘ۬ۜۜۘ۠ۢۛۡۖۧ";
                                                                                                                while (true) {
                                                                                                                    switch (str22.hashCode() ^ 7343594) {
                                                                                                                        case -1158763855:
                                                                                                                            str21 = "ۖ۫ۨۘۙ۬ۜۦۦۘۘۙۧ۠۫۠ۗۧۘ۫۬ۦۛۗۨۗۜۨۥۜۙ۫ۘ۟ۦۡۡۛۧۚۜۜ۟۫ۥۨ۬۟ۘۧۤ۫ۤۜ۫۫";
                                                                                                                            break;
                                                                                                                        case -1082771285:
                                                                                                                            str22 = "ۘۘۡۚۜۥۘۥ۠ۘۖ۟ۧۦۧۛ۫۟ۜۘۜۨۥۘۢۖ۫ۥۖۧۘۗۢۤۡۥۤۘۧۗ";
                                                                                                                        case -649716202:
                                                                                                                            str22 = i4 < length ? "ۦ۟ۖۘۡۤۡۛۤۥۢ۠ۛۙۜ۫ۜ۟ۡۘۧۙۘۚۦۜۘۙۛۜۖۥۥۘۤۜۢۙ۟ۨۘۡۦۧۧ۟ۢۘۡۘۘۚۥۖ۬ۢۜۘۢۛۨۘ" : "۬ۜۢ۟ۢۛۜۢۖۗۙ۠ۖۗۜۘۛۖۛ۬ۘۗۥۢۘۡۖۥۘ۬۫ۚ۫ۨۖ۬ۗ۫";
                                                                                                                        case 552617904:
                                                                                                                            str21 = "ۡۢۨۘۖۥۦۥۢۘۘۘ۠ۨۘ۬ۜۜۘۢ۠ۥۘ۬ۖۖۘ۠ۛۘۘۙۦۖۙۙۧۦ۠ۚۥۨۘۙ۟ۛۗۜۛۦۛۦۘۨۦۨۜۜ۠ۡۛ۟";
                                                                                                                            break;
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            case -2028779237:
                                                                                                                str21 = "ۛۚۖۦ۫ۧۖ۠ۡۘۦۖۤۧۡۗۧۗۢۙۗۧۢ۠ۢ۫ۘ۫۫ۥۘۙ۠ۦۘ۬ۧ۫ۜۖۛۦۛۤۚ۟ۥۚ۠ۢۧۨۘۨ۠۟";
                                                                                                            case -481339401:
                                                                                                                String str23 = strArrSplit[i4];
                                                                                                                String str24 = "ۘ۟ۢۗۙۡۘ۫ۡ۬ۙۡۡۘۛ۠ۥۘۙۡۧۘۚ۠ۚۖۘ۬ۚ۫ۗۥۙۛ۟ۤۦۘۙۜۥۖۥۧ۬ۥۤ۠ۚ۟ۙۙۧۢۢۢ۬ۜۨ";
                                                                                                                while (true) {
                                                                                                                    switch (str24.hashCode() ^ 1795111374) {
                                                                                                                        case -1728235587:
                                                                                                                            String str25 = "ۦ۟ۧۥۧ۠ۡ۫۟۫ۧۚۡ۬ۜۨۤۦۜۥۘۛۡۘۘ۠ۥۛۜ۠ۜۘۨ۫ۦۗۘۧۙۥ۟ۖۗۗ۬ۢۨۢ۟ۢۚۨۚۙۚۡ";
                                                                                                                            while (true) {
                                                                                                                                switch (str25.hashCode() ^ (-1753221390)) {
                                                                                                                                    case -1802076636:
                                                                                                                                        try {
                                                                                                                                            j = Long.parseLong(str23.substring("time=".length()));
                                                                                                                                            break;
                                                                                                                                        } catch (Exception e) {
                                                                                                                                            break;
                                                                                                                                        }
                                                                                                                                    case -10580846:
                                                                                                                                        String str26 = "ۖۢۖۗۖۘۘۧۦ۠ۡۧ۟۠ۖۡۗ۬ۘۖۥۦۘۘ۫ۚۥۡۘۘۧ۫ۢۛۥۛ۟ۗ۫ۖ۟ۡۘ۟ۚۘۢۧ۠ۨۚۖ";
                                                                                                                                        while (true) {
                                                                                                                                            switch (str26.hashCode() ^ (-1873100839)) {
                                                                                                                                                case -892786476:
                                                                                                                                                    str25 = "۫ۖۨۨ۠ۦۛۜۜۘۛۡۜ۫ۜۜۘۡ۬ۧ۠ۙۗ۠ۗۢۢۥۥۘۧۘۨۘۘ۫ۚۗۢۜۥۢۧ۬ۙۥۡۧۘۖ۬ۙ";
                                                                                                                                                    break;
                                                                                                                                                case -542363114:
                                                                                                                                                    str26 = "ۨۦۚۜۚۖۘۙۢۢ۟ۗۨۘۛۥۥۘۚ۬ۚۡۚۙۛۦۘۘۗۚۘۘۜۛۦۘۙۛۘۗۖۥۧۛۘۤ۠۠ۥۘۨۘۖۦۧۘ";
                                                                                                                                                case 450546006:
                                                                                                                                                    str25 = "ۚۤۡۘۚۢۛۙ۬ۡ۫ۘۖۘۢۨۛ۠ۨ۫۬ۜۖۘۦۧۖۚ۫ۚۖ۫۬";
                                                                                                                                                    break;
                                                                                                                                                case 515874038:
                                                                                                                                                    str26 = str23.startsWith("time=") ? "ۖۜۦۘ۟۫۬۫ۤۘ۫۟ۘۢۘۢ۟۠ۜۡۤۡۡ۫ۗۗ۬ۚۖۘۘ۟۟۟ۦ۫ۨۘۤۦۦ۬۫ۡۗ۫ۜۜۙۘۘ" : "۬ۨۙۛۙۡۜۦۦۖۦۖ۠ۖۙۡۡۛۢۛۖۘۥۙ۟۠۠ۘۘۗ";
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        break;
                                                                                                                                    case -560858:
                                                                                                                                        str25 = "۬۬ۡۦۧۨۦۜۨۘۨۛۨۘۙۜۨۥ۬ۨۨۢۘۘ۫ۡۦۖۡۡۘۧۖۦۘۚۦۡۡۙۙ۟ۚۖۘۡۛۧ";
                                                                                                                                    case 2041753898:
                                                                                                                                        break;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            break;
                                                                                                                        case -1721511227:
                                                                                                                            str24 = "۬ۜۙۨۙۥۛۜ۫ۧۤۜ۟ۘۘ۫۟ۚۗۨۘۚ۫ۥۥۗۘۘۢۤۙۦۘۨۙۦۙ";
                                                                                                                        case -991603165:
                                                                                                                            String str27 = "ۡۘۨ۫۠ۡۖ۟ۢ۬۠ۜۥۖۦۘۧۨۖۘۚۜۘۧۦۧۘ۫ۚۖۘۤۧ۠ۘۧۤۨۚۨۘ۟ۢۛۘۦۘۤ۬ۡۖۘ";
                                                                                                                            while (true) {
                                                                                                                                switch (str27.hashCode() ^ 721260154) {
                                                                                                                                    case -192290766:
                                                                                                                                        str27 = str23.startsWith("message=") ? "ۙۡۚۘۦۡۘۛۢۜۘۛۘۥۦۖۡۖۛ۫۬ۙۥۡۥۘ۫ۨۘۘۡۨۘۘۚۢۖۘۛۜۜۘ" : "ۛۧۢۜۖۖ۬ۛۡۘۚۦۥۤۛۧۦ۫ۖۘۜ۠۠۟ۦۥۘۘ۫ۦۗ۫ۛ۬ۦ۟ۜۡ";
                                                                                                                                    case 694933648:
                                                                                                                                        str27 = "ۦ۬ۦۛۚۡۨۘۥۘۤ۟ۗ۬ۗۜۘۖۡۗ۬۠ۦۗۧۛۙۛۧ۫ۡۨۧۘۧۥۚۡۘ";
                                                                                                                                    case 1251785520:
                                                                                                                                        str24 = "ۜۦۥۘ۟ۨۙۙۦۖۘۚۨ۬ۨۡۥ۫ۨۨۖ۠۠۬ۤۡۘۘۘۜ۫ۛ۫ۨۜۥۘۡۗۙ";
                                                                                                                                        break;
                                                                                                                                    case 1863482868:
                                                                                                                                        str24 = "۠ۥۧۘۘۗۘۙۗۤۤۚۗ۠ۗ۫۬ۨۧۙۥۢۗۛۚ۫ۛۥۘۡۥۧ";
                                                                                                                                        break;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            break;
                                                                                                                        case 632373641:
                                                                                                                            strSubstring = str23.substring("message=".length());
                                                                                                                            break;
                                                                                                                    }
                                                                                                                }
                                                                                                                i4++;
                                                                                                                break;
                                                                                                            case 561558737:
                                                                                                                break;
                                                                                                        }
                                                                                                        String str28 = "۠ۡۛۚۥۧ۟ۦۨۘۚۖۖۘۡۢ۬ۡۤۥۘۗۗۘۤۤۗۜ۠ۢ۠ۜۦۘ";
                                                                                                        while (true) {
                                                                                                            switch (str28.hashCode() ^ (-1815948833)) {
                                                                                                                case -1486438212:
                                                                                                                    String str29 = "۟ۤۚۧۧۨۨ۟۫ۤ۟ۚۖۢۦۥ۬ۜۘۤۖۤۙۜۘ۟ۨ۫ۨۡۦۘۜ۠ۖۛۙۗۤۖۘۘۙۜۨۥ۬ۥۘ۟ۖۡ";
                                                                                                                    while (true) {
                                                                                                                        switch (str29.hashCode() ^ 380103006) {
                                                                                                                            case -1754898588:
                                                                                                                                str28 = "ۚۙۡۤۧۡ۠ۗۡۨ۟ۖۘۛۨۘۜۤۘۢ۟ۢۚۢۨۘۧۗ۠ۧ۫۬";
                                                                                                                                break;
                                                                                                                            case -691821769:
                                                                                                                                str28 = "ۙۘ۫ۧ۟ۙۗۥ۫ۚۛۥ۬ۖۜۘۚۨۚۨۢۧۙۙۚۙ۬ۢ۫ۨۧۜۤۛۨۡ";
                                                                                                                                break;
                                                                                                                            case -535622609:
                                                                                                                                str29 = "ۖۙۚۘۨۤۤۢۦۘۧ۠ۜۚۡۛ۫ۖۜۘۙۘۨۜۤ۬۫ۗۜۤ۬ۖۘۦ۟ۙۜۛۤ۟۟ۛ۬۬ۥۦۥ۠ۧۧۡۛ۠ۛۘ۬ۛ";
                                                                                                                            case 178784945:
                                                                                                                                str29 = strOptString2.equals(strSubstring) ? "ۙ۫ۘۘ۟ۘۚ۬ۖۨۨۥ۟ۖۢۦۤۘۘۛ۟ۨۘۤۡۙۦۗۚ۠ۗۙۨۡۖۚۥ۠ۤۧۨۦۗۡۘۚ۟ۛۥۚۡۘ" : "۬ۦۥۤۨۜۘۡۙۢ۬ۤۤۤۘۘ۟ۗۤۡۚۦۦ۟ۦۧۥۥۚۢۦۗۙۢۚ۟ۢ";
                                                                                                                        }
                                                                                                                    }
                                                                                                                    break;
                                                                                                                case -14214378:
                                                                                                                    str28 = "ۚۥۨۥۦۘۡ۟ۘۘۘۙ۟ۦ۟ۥۙۦۨۘۚۦۦۘۥ۫ۘ۫ۥۦۙۢۧۨۤ۫ۡۚۖۘۚۥۦۘ۫ۨۖۡ۬۬ۖۙ";
                                                                                                                case 1412492389:
                                                                                                                    k2.logToFloatingWindow(strOptString + "文字弹窗 消息内容发生变化，弹窗", "action");
                                                                                                                    break;
                                                                                                                case 1464666736:
                                                                                                                    long jCurrentTimeMillis = System.currentTimeMillis() - j;
                                                                                                                    long j2 = jCurrentTimeMillis / 3600000;
                                                                                                                    long j3 = iOptInt2;
                                                                                                                    String str30 = "ۜ۬ۥۛۢۜۘۧۚۤۡۘۤۧۖ۠ۛۧۤ۠ۜۘۖۦۘۦ۠ۛۤۚ۬۫۟ۖۘۗۧ۬ۢۜ۬۠ۜۧۘۙۜۖۘۜۛۜ";
                                                                                                                    while (true) {
                                                                                                                        switch (str30.hashCode() ^ (-1966267640)) {
                                                                                                                            case -1770029013:
                                                                                                                                str30 = "۫ۨ۫ۙۛۘۘۛۨۡۘۜۤۥۚۚۙ۠۬ۨۜۚۖۘۘۘ۫ۙۥۘۜۦۧ۠ۚۧۨ۫ۨۘ۠ۘۢ۫۫ۢ۬ۡۤۤۙۚۧۛ۫ۡۚۥۘ";
                                                                                                                            case -101976275:
                                                                                                                                k2.logToFloatingWindow(strOptString + " 文字弹窗 间隔时间未到，不弹（剩余约 " + (((3600000 * j3) - jCurrentTimeMillis) / 60000) + " 分钟）", "action");
                                                                                                                                break;
                                                                                                                            case 320259875:
                                                                                                                                break;
                                                                                                                            case 375190699:
                                                                                                                                String str31 = "ۨۘۗۗ۫۬ۙۗۛ۬ۚۧۢۜۥۘۚۖۜۘۤۦۗۛۧۘۘۡ۫ۜۤ۫۠ۛۤۤ۟ۧۜۡۛۦۘ۫ۖۖۢ۫ۡۘ۟ۤۦ";
                                                                                                                                while (true) {
                                                                                                                                    switch (str31.hashCode() ^ (-530226898)) {
                                                                                                                                        case -1877672033:
                                                                                                                                            str31 = j2 < j3 ? "۬ۗۢ۫ۜۘۘۨۦۘ۠ۢۘۘۦۧۦۖ۫ۘۤۥۨۘۘۜۜۦۖۘ۫ۖۘۨۗۤۧ۬ۙۢۤۗ۬ۤۥۤۛۦۘ۬ۢ" : "۫ۘۙۘۗۨۘۗۛ۬ۜۡۨۘۦ۠ۖۘۗ۫۬ۙۨۘۘۧۢۤۚۖۥۡۡ۠ۛ۟ۜۘۨ۬۬ۗۘۗۚۦۜۘ۫ۗ۫ۚ۬ۥ۫۬ۡۘ۟ۘ۟";
                                                                                                                                        case -1006874562:
                                                                                                                                            str31 = "ۖۦۨۘۘۛۢۥۚۛۡۨۛ۟ۥۥۤۥ۟ۦ۫ۜۘ۬ۢۚۙ۫ۢۖۦۚ۟ۨۘۙۗۢۜۡۘۚۚ۟";
                                                                                                                                        case -83780189:
                                                                                                                                            str30 = "۠۬۟ۖۡۖۧۨۚۥۨۡۖۨۨۘۚۥۙ۬ۥۢۨۡ۬ۘ۬ۦۘۨۧۤ۟ۜۗۨۜۚ۟۫ۢۚ۟ۖۘ";
                                                                                                                                            break;
                                                                                                                                        case 660138396:
                                                                                                                                            str30 = "۠۠ۜۖۦۘۘۖۦۧۨۜ۟۬ۨۦۘۖۙۨۛ۬۬ۜ۟ۘۘۢ۬ۦۦۨۨۙۛۜۘۧۨۥۘ";
                                                                                                                                            break;
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
                                                                                                break;
                                                                                            case -233114203:
                                                                                                break;
                                                                                            case 307432014:
                                                                                                String str32 = "۠ۘۧ۬ۙۜۗۙۤۘۥۨۘ۬ۗۨۨۚۨۘ۫ۦۜۡۜ۫ۨۗۤۦۜۖۨۖۙۢۜۚۛۧ۫۬ۜۧۘ";
                                                                                                while (true) {
                                                                                                    switch (str32.hashCode() ^ 409501334) {
                                                                                                        case -2112645753:
                                                                                                            str32 = string != null ? "ۢ۠ۙۜۖۦۦۢۥۘۤۨۥۘۙۛۥۙۜۥۘۢۤۨۘۘۚۛ۠ۖۨۘۚۦۦ۠ۥۜۘۢۢۜۘۘۨ۬ۗۖ" : "ۨ۠ۜۚۨۗۜۢۨ۫۬ۖۜۜۦۘ۬۫ۖۘ۠ۗۥ۬ۦۖ۟ۥۗۡۢ۬۬۟۠ۤ۠ۙۢ۫ۦۗ۠ۨۦۦۘ۫۠ۡۘۨۦۡۙۤۘ";
                                                                                                        case -1863193518:
                                                                                                            str20 = "ۜۚ۠ۗۦۖۘۥۢۡۗۥۦۦۡۧۘۢۘۖۘۡۙۚ۟ۘۗۢۦۖۘۘۚۗۧۘۧۘۡ۟ۘۘۡۘۢۘۤ";
                                                                                                            break;
                                                                                                        case -118066145:
                                                                                                            str20 = "ۛ۬۠ۦۚۜۘۖ۠ۥۡۗۘۘ۠ۡۗ۟ۡۦۘۢۜ۟ۥۜۥۥۤ۠ۦ۫ۜ";
                                                                                                            break;
                                                                                                        case 2086194073:
                                                                                                            str32 = "ۘۚۤۦۜۨ۟ۛۥۦۗۚۤۜۧۘ۬ۚۦۘۙۡۘۘۢۚۡۢۧۥۘۧ۫ۛۧۘۢۙۗۙۖۛۘۘۘۦۘ";
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                            case 2081900989:
                                                                                                str20 = "ۖ۠ۢۦۚۦۘۥ۠ۢۧ۬ۨۘۤ۬۫۠۬ۘ۫ۨۘۛۘ۠ۨ۬ۧ۠۫ۧۢۢۧۧۛۡۙۙ۬ۥۡۧۘ۟ۜۚۧ۟ۨۘۢۢۛۢ";
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case 878897178:
                                                                                    break;
                                                                                case 1785346169:
                                                                                    str16 = "ۢۥۙ۟ۦۙۥۖ۫ۤۥۗ۠ۚۦۦۡ۫ۥۖۡۘ۬ۦ۟۠ۛۨ۬۫ۥۦۧۗۘۡۙۧۙۥۘۦۘۧۥۜۨۘۤۗۜۘۡۨۘۛۦۥۘ";
                                                                            }
                                                                        }
                                                                        JSONArray jSONArrayOptJSONArray4 = jSONObjectOptJSONObject.optJSONArray("other");
                                                                        String str33 = "ۛۖۖۥ۠ۜۘ۠ۖ۫ۧ۟۠ۧۤۜۘ۬۫ۖۘۜۤ۟۟ۥۜۤۤۘۘۢۛۨۡۡۚۢ۬ۦۗ۠ۡۘۡ۟۫ۛۤۥۛ۟ۥ۬۟ۦ۠ۙۢ";
                                                                        while (true) {
                                                                            switch (str33.hashCode() ^ (-1274829160)) {
                                                                                case -2009373715:
                                                                                    String str34 = "ۨۛۤ۬ۨۨۘ۠ۜۖ۟۟ۙۥۚۥۘۥۛۘۘۗۛ۫۫ۤۖۘۖۖۖۤ۠ۖ";
                                                                                    while (true) {
                                                                                        switch (str34.hashCode() ^ (-1586178033)) {
                                                                                            case -928061000:
                                                                                                str33 = "۟ۙۨۘۡ۠۫۟ۛۙۨۥۖ۫ۤۡ۬۟ۦۘۚۜۨۚۙۧۜۖۦۘۤۘۖۛ۟ۨۘۙ۬۫ۚۗۗۨۘۚۗۧۙۚۜۘۖۨۧۘۥۖۦ";
                                                                                                break;
                                                                                            case -176904253:
                                                                                                str34 = iOptInt == 0 ? "ۨۚۧۥ۬ۢۢۚۢۧۛۛ۬ۢۘۖۨ۬ۤۖۖۘۡۘۨۡ۟ۖۘ۠ۤۦۘ" : "ۨۚۤۜۤ۟۟ۖ۠ۤۛۙ۬ۡۘۘۘۤۚۢ۟۟۠ۤۘ۫ۢۦۖۘۧۘۤۨۘۦۜۖۘ";
                                                                                            case 664692119:
                                                                                                str34 = "ۢ۠ۡۘ۬ۦۨۘۙۙۡۚ۠۟۬۫ۡۤ۠ۗ۬ۗۜۘ۫۟ۨۜۙۜۘۜۚ۫ۘۡۗ۫ۨۧۘۖۨۘ۫ۤ";
                                                                                            case 750778357:
                                                                                                str33 = "ۨۥۡۘۗۖۡۜ۫ۚۛۧۜۘ۬ۥۧۘۘۦۧۘ۬ۡۤۤۧۖ۠ۥۘۘۙۥۘۡۦ۟۫ۗۜۜۨۨۡ۠ۡۧۨۦۚ۟ۦۘ";
                                                                                                break;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case 89295739:
                                                                                    k2.HNK2clNL(activity, jSONObjectOptJSONObject.optString("id", ""), jSONObjectOptJSONObject.optString("title", ""), jSONObjectOptJSONObject.optString("message", ""), jSONObjectOptJSONObject.optInt("exitpopus", 0), jSONObjectOptJSONObject.optJSONArray("button"), jSONObjectOptJSONObject.optString("backgroundColor", "#FFFFFF"), jSONObjectOptJSONObject.optString("maskColor", "#80000000"), jSONObjectOptJSONObject.optBoolean("lock", false), arrayList, arrayList2, jSONArrayOptJSONArray4);
                                                                                    break;
                                                                                case 1049179763:
                                                                                    str33 = "ۤ۟۬۟ۨۢۖۨۙۘۗۥۘۥ۫ۗۡۖۥۘۡۘۚ۠ۡۧۧ۟ۦۘۧۙۡۜۖۛۧۥۨ";
                                                                                case 1135806894:
                                                                                    String str35 = "۬ۚۗۧ۬ۡۘۡۦۡۥۘ۟۠۟ۥۘۙۛۨۧۛۦۖۗۧۢۦۚۧ۠ۤۦۚۦۖۢۘۘۜۜۖۘۥۛۗۢۡۨۘۛۥۛ۫ۦۡۘۨۡۜ";
                                                                                    while (true) {
                                                                                        switch (str35.hashCode() ^ 515009882) {
                                                                                            case -2069931915:
                                                                                                z = false;
                                                                                                k2.b0H7kxOr(activity, jSONObjectOptJSONObject.optString("id", ""), jSONObjectOptJSONObject.optString("title", ""), jSONObjectOptJSONObject.optString("message", ""), jSONObjectOptJSONObject.optInt("exitpopus", 0), jSONObjectOptJSONObject.optJSONArray("button"), jSONObjectOptJSONObject.optString("backgroundColor", "#FFFFFF"), jSONObjectOptJSONObject.optBoolean("lock", false), arrayList, arrayList2, jSONArrayOptJSONArray4);
                                                                                                break;
                                                                                            case -1950147417:
                                                                                                break;
                                                                                            case 845412011:
                                                                                                str35 = "ۤۥۚۡۙۘۜۘۦۖ۟ۢ۫ۡۨ۟ۚۧۢۙۜۙۙۥۥۘۦۘۢۚۖۖ۬۠ۤۨۤۢۗۢۡۛ۬ۤۨۙۚۢۨ";
                                                                                            case 888936898:
                                                                                                String str36 = "ۡۗۖۨۤۨۚۗۦۤۚ۬۬۟ۡۘۘۗۘۧۘۡۢۜۜۨ۠ۢۚۤ۫ۘۖۘۥۚۦۘۡۛۖۖۦ۠۠ۦۡۡۘۛ";
                                                                                                while (true) {
                                                                                                    switch (str36.hashCode() ^ (-560880573)) {
                                                                                                        case -1760786052:
                                                                                                            str36 = iOptInt == 1 ? "ۧۦۙ۬ۦۡۘۡۥ۫ۙۖۤۧۗۘۛ۬ۤۥ۫ۜۘۜۚ۫ۛۤۘۘۛ۟ۦۛۤۨۗۖۚ۫ۤۜۨۤ۠ۦۦۜۖ۠" : "ۛۖۦۘۨۙۜ۫۠ۥۘۥۘۧۘۨۡۜۘۜ۠ۖۘۛۥۡۥ۠۠ۡ۫ۦۘۥۙ۬ۦۗۖۘۤۡۡ";
                                                                                                        case -1678356167:
                                                                                                            str35 = "ۛۢۗۚۢ۠ۙۖۘۜۜۨۘۚۚۡۜۨ۫ۧۘ۬۫ۢ۬۟ۖ۬ۦۧ۬";
                                                                                                            break;
                                                                                                        case 80979270:
                                                                                                            str36 = "ۗۖۧۧۗۚ۠ۡ۟ۙۧۡ۫ۤ۠ۡ۬ۜۧ۠ۙۘۗۡۖۜۥۥۦۗۗۦۡ۟ۚۗۦ۬ۨۘۧۧۥۘ۠۫ۡ۫۠ۥۡۢۨ۬۫ۙ";
                                                                                                        case 1810604971:
                                                                                                            str35 = "ۡ۟ۢۢۢۛ۫ۢ۟ۨۤۥۖۡ۠ۙۗۚۘۖۥۘۗۨۧۘۨۨۨۙ۠";
                                                                                                            break;
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                            }
                                                                        }
                                                                        z = false;
                                                                        break;
                                                                    case 1807433670:
                                                                        str6 = "ۢۜۨۘۧۢۢۨۢۤۨۤۛۨۛۚۡ۠ۢ۠ۗۢۚۛۦۘۤۨۤۘ۟ۧۡۢۗ۫ۜ";
                                                                }
                                                            }
                                                            break;
                                                        case 1142133079:
                                                            String str37 = "ۖ۟۟ۗۧۦۦۙۡۜۖۢ۬ۗۗۥۗ۫۫ۧۜۢۥۙۧۖ۬ۥ۬ۖ";
                                                            while (true) {
                                                                switch (str37.hashCode() ^ 613298895) {
                                                                    case -1887915253:
                                                                        str37 = "ۗۨۡۨۤۛ۫۠ۜۨۜ۟۫۠ۖۘ۫ۘۘۘۤ۬ۥۛ۟ۗۜ۠ۜ۫ۙۦۜۚۦۘۖۦۧۢۚۚۚۨۚۖۧۧۥ۠ۥۘۤۘۦۖۧ۠";
                                                                    case 1396222203:
                                                                        str5 = "ۛۘۗۡۖۥۘۙ۬ۖۘۛۖۛۢۚۖۖۚۛۦۛ۟ۡۤۥۘۗۙۖۘۘۢۙۗۡۗۧ۟ۧۖ۠ۖ۫ۚۛ";
                                                                        break;
                                                                    case 1427008192:
                                                                        str37 = jSONObjectOptJSONObject != 0 ? "ۖ۟ۦۨ۠ۡۘۦ۟ۗۘۚ۬ۖ۬۬۫۟ۚ۠ۡۡۨۜۨۘ۠ۖۧۘۨۡ۠ۜۥۤۤۗۛۙۧۤۥۗ۠ۛ۟ۚۡۘ" : "ۛۜ۠ۗ۠ۖۛۘۡۘ۫۟ۛ۬۬ۗۦۨۖۦۢۦۘۜۜۢ۠ۖۛۥۙۛ۟ۨۧۘۦ۠ۦۨۧۦۘۨۘۡۡ۬ۜ۟ۧۢ۠۬۫ۘۘ";
                                                                    case 1896599154:
                                                                        str5 = "ۛ۫ۗۨۢ۫ۦۤ۬ۖۥۨۘۖۗۙ۬۠ۢۗۛۘۨۚ۠ۚۢۘۘۢۘۥۘۚۜۨ۟ۘۥۛۘ۬ۙۖۘۢ۟ۤ۠ۢۤۜۢۛۢۘۦۘ";
                                                                        break;
                                                                }
                                                            }
                                                            break;
                                                    }
                                                }
                                                z = r4;
                                                i++;
                                                r4 = z;
                                                break;
                                        }
                                    }
                                }
                                break;
                            case 1030504119:
                                return;
                            case 1523419407:
                                String str38 = "ۙۚۜۘۖۥۜۘۘۛۘۘۢۨۦۘۚۨۢۦ۫۟ۛۛۜۘ۠ۨ۬ۗۤۥۘۜۧ۫ۛۥۜۙۚ۬ۦۙۦۘۡۦۗۜۧۘۥۘ۬";
                                while (true) {
                                    switch (str38.hashCode() ^ (-117388599)) {
                                        case -1623936266:
                                            if (jSONArrayOptJSONArray == null) {
                                                str38 = "ۜۨۘۥۙۖۖۤ۫ۤۢۦۘۙۙۢ۟ۨۦۘۙۡۜۘۙۨۡ۠۟ۜۘۖۘۜۘۘۙۜ۬ۜۚ";
                                                break;
                                            } else {
                                                str38 = "۠ۡۨۘۨۖۦۘۡۙۨۘ۟ۜۥۘۘ۫ۚۡۨۦۘۗ۟ۜۘۧۗۡۙۡۥۦۖۦ";
                                                break;
                                            }
                                        case -30127674:
                                            str38 = "۬۟ۥۘ۫ۨۨ۠ۦۖۛ۠ۗۡۧۢۧ۫۠ۦ۟ۛۦۘۡۤۥۡۘۦۗۙۨۘۗۧۨۘ۬ۡۥۘ۬ۖۗۥۗ۫ۘۦۢۛۘۨ۫۟";
                                            break;
                                        case 461738850:
                                            str2 = "ۥۡۧۦ۫ۚۨۛۜۘۡۦۢۧۘۡۘۤۦۥۧ۟ۨۡۛ۬ۥ۟ۡۘۡۨ۟";
                                            continue;
                                        case 1112662489:
                                            str2 = "ۦ۫ۛۙۜۦۘ۠ۧۨۘۦۤ۠۫ۤ۫۫ۦۦۡۜ۠ۤۢۢ۟۟ۦۧۙۜۘ";
                                            continue;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case -520568909:
                    String str39 = "ۚۦۥۧ۬ۥۘۙ۠ۖۘۘۡۚۦۨۜۘۜۧۘۘۡۙۜۢۤۜۧۖۡ۠ۦۘۖ۠ۡۤۢۥ۫ۡ۫ۖۚۚۦ۬ۥۗۧ";
                    while (true) {
                        switch (str39.hashCode() ^ (-347549877)) {
                            case -1608281703:
                                str = "ۨۤۛۗۖۚۗۚۨۘۥۥۘ۫ۥ۠ۨۢۨ۬ۤۖ۟ۡۙۚۧۡۘۤۖۨۘ۟ۖۘۜۛ۠ۗۤۖۘۖۡ۬۟ۜۘۘۗۨۛ";
                                continue;
                            case -742638336:
                                str = "ۙۗۘۖۙۖۘۢۢۧۙۥۙۗۧ۠ۨۡۥۖۧ۟ۥۖۦۘۜۦۧۘۨۡۘ";
                                continue;
                            case -341198109:
                                if (!jSONObject.optBoolean(strDecrypt, false)) {
                                    str39 = "ۧ۫ۡۘۤۙۥۘۡ۬ۘۘۤۛ۬ۚۖۤۛۢۡۦ۠ۘۘۘۨۡۘ۠ۘۗۨۨۗ";
                                    break;
                                } else {
                                    str39 = "ۙ۠ۤ۟ۦۦۙۖ۟ۛۗۙ۠ۧۛۦۜۗۡۢۖ۬ۖۢۜۚۘۧۖۦۛ۟ۡ";
                                    break;
                                }
                            case 674690552:
                                str39 = "ۥۛۡۙۛۘۘۢ۬ۜۗۖ۟ۤۢۥۧ۫ۧۢۙۨۘۥ۬ۥۘۡۦ۟ۙۙ۫ۦۧۥۘ۬۬ۖۘۧۡۛۛۜۘۦۘۧۤ۫ۡۘۜ۫ۛ۫۬ۚ";
                                break;
                        }
                    }
                    break;
                case -330237427:
                    return;
                case 507115442:
                    str = "ۤۚۚۤۗۦۥۗ۫ۢۙۚۢ۬ۜۙ۫ۦۘۜۚ۫ۨ۟ۡۜۘۡۘۧۤۥۘۨۡ۫ۦ۠ۖۘۘۥ۬ۙۖۜۧۨۗۧۢۜۦۛۛۙۦ۬";
                    break;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x001c. Please report as an issue. */
    public static void WeOiML3k(Context context, Context context2) throws IllegalAccessException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        String str = "ۨۡۘۚۚۡۧ۬ۤۥ۬۟ۥۨ۟ۛۜۢۤۖۘۙۢۤ۬۫ۨۘۢ۟ۖۘ";
        s3 s3Var = null;
        boolean zOptBoolean = false;
        boolean zOptBoolean2 = false;
        boolean zOptBoolean3 = false;
        boolean zOptBoolean4 = false;
        boolean zOptBoolean5 = false;
        Activity activityECt8jHZ4 = null;
        JSONObject jsonResult = null;
        while (true) {
            switch ((((str.hashCode() ^ 847) ^ 598) ^ 160) ^ 2027658434) {
                case -2040076154:
                    System.exit(0);
                    str = "ۢۡۨۧۥۜۙ۫ۡۤۡۤ۬ۡۖۚۡۧ۬ۘ۬ۗۗۘ۬ۖۜۘۚۥ۫ۗۢۦ۫ۛۦۖۧۨۖۢ";
                case -2033257475:
                    String str2 = "۟۫ۜۡۜۨۘۧۥۥۘ۟ۤۜۨۨۨۘۢۡۘۘۘۦۨۜۜۘ۫ۜ۠۟۟ۗۙ۬ۗۜ۬";
                    while (true) {
                        switch (str2.hashCode() ^ (-1291698590)) {
                            case -1889504164:
                                str = "ۡۨ۟ۗۥۜۥۧ۠ۦۛۗۜۘ۠ۡۡۘۘۢۚۖ۠ۡۥۘ۫۬ۦۘۢۜۧۢ۫ۡۤۦۘۘۚۙۥۢۙ";
                                break;
                            case -1475451380:
                                str2 = "ۥ۠ۡۘۤۢۘ۫ۧۘۘۥۜۧۘۤۜۨۘۜۧۦۘ۟۫۬۠ۚۘۜۥۘۥۨۡۘۜۜۥۘۦ۠ۦۘۦۚۚۜۚۥۘ";
                            case -787015373:
                                break;
                            case 1850874934:
                                String str3 = "ۖ۠ۙ۫۬ۤۦۗ۬ۢۦ۟۬۫ۨۘ۠ۤۢۥ۠ۥ۟ۧ۬۠ۙۥۗۜۢۧۥۘۘۦۥۘۚۡۦ۫ۛۜۘ۟۬ۘۙۘۙۢ۬ۥۘۥ۬۠";
                                while (true) {
                                    switch (str3.hashCode() ^ (-1872873057)) {
                                        case -1512679961:
                                            str3 = "ۚ۫ۦۘۖۘۤۖۨۜ۠۬۫ۙۨۜ۬ۦۧ۬۫۬ۚ۟ۜۛ۬ۚۗۜۘۡۘۘۡۡۙۗۘۧ۟ۦۘۘ۠ۜۧۘۗ۬ۚۥۘۗۦ۠";
                                        case -402381172:
                                            str2 = "ۧ۟ۙۗۨۡۘۦۘۖۤۛۘ۬ۢۘ۫ۡۜۧ۫ۡۡۧ۟۬ۚۘۧۙۢۛۚۜۤۗۤ۫ۧۙۚۙۥۙۤۨۧۜۗ";
                                            break;
                                        case -359120161:
                                            str2 = "ۚۤۢۡۘۥۧۙۡ۟ۙۜ۫۠ۡۖۛۦۘ۫۟ۥۤۜۧۡۨۧۚۘۥۘ۬۠ۙۗ۫ۦۧۡ۫ۤۗۥۘ";
                                            break;
                                        case 2080782216:
                                            str3 = Utils.isXposedPresent(context) ? "ۥ۟ۘۘۚ۬ۘۘۨۨۜ۟ۥۥۘۦۢ۠ۨۤۖۦۤۥۖۙۜۘۨۦۨۚ۬۫ۛۘۧ۬ۥۥ" : "ۤۥۡۘ۫ۛۡۤ۟ۤۧۜۘۜۙۦۗۚ۬ۛ۟ۡۢۙۜۤۡۨ۫ۦۘۘۥ۬ۡۘۢۤۡۘۚ۬ۛ۫۠ۘۘۚۗۘۘۢۢۖۤۤۘۘ۟ۦۜۘ";
                                    }
                                }
                                break;
                        }
                    }
                    str = "ۥۢۜۨۚ۟۠۟۬۬ۥۦۘۦۥۙۢۡۢۧۖۗ۫ۙۡۧۢ۫۠ۧۗ۬ۗۧ۫ۛۦۖۘ۟ۧۖۥۤۘۘ۠۟ۨۤۡۨۦۥ۫";
                    break;
                case -1819606995:
                    Utils.applyRemoteSPOverrides(context2);
                    str = "۫ۙ۠ۛۚ۟۟ۥۧۘۘۜ۟ۜۜ۠ۜۛۦۦۙۤ۬ۥۘ۠ۥۨۡ۠ۛ۟ۦۜۖۨۨۛ۟ۚ۫ۙۚۤۛ۬ۗۙ۠ۛۦۘ۠ۤۜۘ";
                case -1812479680:
                    Process.killProcess(Process.myPid());
                    str = "ۧۨۧۘ۫ۙۗۦۦۚۧۤۤ۫ۗۘ۫ۛ۬ۖۨۚۤۡ۟ۨۘۜۖۘۜۡۜۥۗۡۘ۬۬۫۬ۜۜۘ";
                case -1642728168:
                    String str4 = "۫ۤۦۘۗ۟ۦۘ۫ۜۥۥ۟ۧۜۚۥۜ۠ۖ۟ۦۨۚۚۗ۠ۘۘۜۚۜۙۜۦۘۤۧ۠ۢۨ۬۟ۙ۬";
                    while (true) {
                        switch (str4.hashCode() ^ 2111411405) {
                            case -1065928517:
                                str = "ۨ۠ۢۥۤ۠۟ۦۤ۫۫ۖۘۥ۠ۡۘۨۤۖۘۡۜۨۚۘۚۤۘ۠ۨۘۦۜۥ۫ۚۡ۫ۙۗۧۖۥۦ۠ۥ۟ۖۢ";
                                break;
                            case -872172222:
                                str4 = "ۙۛۗۜ۠ۡ۫ۗۙۗ۟ۙۛ۬۟ۖۘۧۙۡۢۡۨۡ۟ۜۘۨۛۛۖ۠ۢۦ۫ۦۘۢۧۨۘۨۥۘۦ۠ۤ۠ۡ۬ۘۙۢۛ۫۟";
                            case -93248230:
                                break;
                            case 63216890:
                                String str5 = "ۜۘ۬ۗۚۘۡۨۦۘ۠۟ۘۘ۟ۚۙۡۙۜۘ۫ۜ۫۫ۡۙۗۨۘۛ۫ۦۘۤۢۦۥۤۖۨۦۢ۬۬ۙ";
                                while (true) {
                                    switch (str5.hashCode() ^ (-86239747)) {
                                        case 1134855867:
                                            str5 = "ۥ۠ۡ۫ۤۛۥۛۦۙۤۥۚ۬ۜۘۛ۟ۖۘۨ۠ۨۘۘۜۜۘۛۨۚۜۧۢ۫ۚ۬ۧۦۧۘ";
                                        case 1175481276:
                                            str4 = "ۨۤۧۦۥۨۘ۬ۖۡۘ۬ۗ۬ۤۘۡ۟ۜۡۛ۫ۨۘۤ۫ۜۘۡۥۚۚۡۘۨۙۧۨۗۜۘۨۜۙۡۗۤ";
                                            break;
                                        case 1696266578:
                                            str5 = Utils.isDualApp(context) ? "۟ۙۨۛۥۘۤ۫ۨ۫ۛۡۘۤۧ۟۟ۜۜ۬۫ۥۘۢۨۤۖۧۢ۟۠ۙ۠ۚۦۘ۫ۤۙ۫ۡ۠ۧۗۦۘۚۤۜۚ۟ۗۖۙۖۘ۬ۚۥۘ" : "۠ۜۖۘ۠ۖۘۛۜۦۛۤۤۨۛۘۛۗۜۜۤۧ۬ۗ۟ۨ۟ۦ۫ۥ۟۟ۚۛ۟ۢ";
                                        case 2028754494:
                                            str4 = "ۚۤ۫ۦۧۦۜۨۥۛۛۤ۬ۚۧۨۖۧۘۦۘۦ۟ۖۘۛۥ۬ۥۚۧۥۢۨۘۙۚ۫";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    str = "ۘۧۖۘۨۤۡۘۢۢۢۨۜۡۘۚۡۦۘۖۛۜ۟ۨۜۗ۠ۛۛۗۜۘۨۖۧۙۡ۫ۨۛۚۜ۫ۧ۟ۤۢ";
                    break;
                case -1639697623:
                    s3Var.connect();
                    str = "ۤۥۙ۟ۨۗ۟ۨۨۘۨۛۛ۫ۛۨ۟ۘۜۗ۟ۗ۠۟۠ۢۖۧۙۤ۬ۦۙۢۜۗۘ";
                case -1627551193:
                case -169583577:
                    break;
                case -1547169650:
                    String str6 = "ۚ۫ۨ۫ۦ۟۠ۗۦۘۛ۟ۥۘۦ۠ۡۚۤۤ۟ۗۜۨۘۜۢ۫ۧۘۜۦۡۙۙۚۨ۠ۚۨ۟ۛۛۦۡۛۢۤۙۡ";
                    while (true) {
                        switch (str6.hashCode() ^ (-823809918)) {
                            case -1987680370:
                                str6 = "ۧۡ۟ۚ۟۟ۨۖۥۘ۫۟ۨۚۡۢ۫ۡۚۦۜۨۗۦۧۘ۫ۥ۠ۨۙۚ";
                                break;
                            case -1375265008:
                                str = "ۢ۬ۥۚۡۦۚۡۦۡۗ۬۟ۨۗۚۗۙۜۛۖۗ۫ۛۙ۬ۖۜۛ۟ۡۘ۟ۚۙۙۗۛۨۗۚۦۧۖ۫ۚۤ۠ۛۜۘۛۦۜ";
                                continue;
                            case 863778964:
                                String str7 = "ۢۘۛۡۜۘۘۙۡۚۗۛۢ۫ۨۤۗۛ۟۫ۛۗۥۨۨۨۦ۠ۤۘۤۤۘۘۨ۫ۚۗۘۨۘ۬ۜۙۢۧۨۦۗ";
                                while (true) {
                                    switch (str7.hashCode() ^ 676067051) {
                                        case -74097797:
                                            str6 = "ۦۗۘۙۤۙۗۡۤۛۘۛۡۛۖۘ۫ۧۦۘۛۨۦۘۤ۠ۡۘۤۡۛۦۜۙ";
                                            break;
                                        case 55447472:
                                            str7 = s0.isDebug() ? "ۨۦۜۘۤۧۨۘۧ۬ۛۥۙۡ۟ۙۡۡۧۤۗۙۥۢۚۖۨۛۨ۬ۡۖۥۘ۠۟ۜ۫ۦۖۡۘۖۧۨ" : "ۨۚۥۘ۠ۤۗ۫ۥۘۘۖۤۜۡۗۨۘۖۤۜۗ۟ۤۤۖۦ۟۠ۤۛۛ۠ۘۙۥۧۨ";
                                        case 1246931750:
                                            str7 = "۬۫ۡۨۖۥۥۡۨۚ۠ۜۘ۠ۜۘۖۥۡۘ۫۬ۜۚ۟ۖۘ۫ۧۡۜ";
                                        case 1295712613:
                                            str6 = "ۡۜ۟ۧ۬ۖ۠ۥۥۖۚ۟ۥۙ۬ۤۜۥۘ۟ۨۤۛۖۥۡۢۦۜ۠ۤۤۢ۫۬ۚۨۘۤۥ۟ۖۦۚ";
                                            break;
                                    }
                                }
                                break;
                            case 1574092652:
                                str = "ۗۚۗۛۖۜۛۛۘۘۜۥ۟ۗۦ۫۠۟ۜۚۖ۬ۦۗۦۜ۟ۡۦ۠۟ۥ۟ۚۤۜۛۡۚۚۖۘۤۗۨۘۖۛ۟";
                                continue;
                        }
                    }
                    break;
                case -1538053440:
                    str = "ۜ۟ۥۘ۠ۨۜۡۗۦۙۧۤ۬ۙۚۘۘۘۘ۠ۘۡۘ۬ۥۥۘۚۦۚۡۧۧۜۙۗۧۚۧۖۦۦ۬ۙۛۨۘۨ۫۫";
                    zOptBoolean = jsonResult.optBoolean("ban_DualApp", false);
                case -1506157566:
                    Process.killProcess(Process.myPid());
                    str = "ۗۖۛ۬۠ۨۢ۫ۖۤ۬ۡۨۜۚۗۡۢۚۖۧۚۛۘۜۥۧۦۘۖۡۘۧۜۖۘ۫ۦۖۘۜۜۤۖۥۖۘ۠۠۠ۨۙۨۘۧۤ۬";
                case -1443556219:
                    str = "ۢۡۨۧۥۜۙ۫ۡۤۡۤ۬ۡۖۚۡۧ۬ۘ۬ۗۗۘ۬ۖۜۘۚۥ۫ۗۢۦ۫ۛۦۖۧۨۖۢ";
                case -1437845463:
                    String str8 = "ۤ۬ۡۘۙۢۜۘۚۘۦۘۘۡۡۜۚۖۘۘۤۘۘۢۨۦۘ۟ۗۚۧ۬ۘۘۖۛ۠۠ۧۡۘۖۧۡۚۜۜۙۧ";
                    while (true) {
                        switch (str8.hashCode() ^ (-542585249)) {
                            case -1816706340:
                                str = "ۖۦۦۘۧۧ۫ۘۡۘۘۨۨۘۜ۠ۨۖۤۢۘۦۥۖۗۨۘۘۗۨ۟ۙۥۛۥۤۘۖۦۧۨۘ۫ۛ۫";
                                break;
                            case -1811849917:
                                str8 = "۟۠ۦ۟۟ۜ۠۬۬ۚۜ۬ۧۢۢۙۡۡۛۗۘۙۨۢۗۡۖۥۘۘ";
                            case -612946323:
                                String str9 = "۫۫۠ۦۛۤ۟ۤۤ۠ۛۖۧۘ۠ۦۜۖۘۡ۟ۖۘۗ۟ۘ۠ۖۢۨۤۜ";
                                while (true) {
                                    switch (str9.hashCode() ^ (-1319232799)) {
                                        case -1347006854:
                                            str8 = "ۤۨۗۘ۫ۨۘۥ۬ۤۤۨۙۤ۠۫ۨۚۜۘۦۘۗۡ۟۫ۘۤۥ۫۫ۘۘۢ۬۫ۨ۠ۜۜۙۨۦۖ";
                                            break;
                                        case 3778074:
                                            str9 = "ۦۙۢ۫ۖۗۨۛۛۥۤ۬۫ۥۚۛۥۙۜۙۜۧۨۙۙۦۘۘۘ۫ۨۘۡۚۖۥۘۘۦۨۜۚۚۥ";
                                        case 860000169:
                                            str9 = zOptBoolean4 ? "۠۠ۘۘۜ۬ۖۘۚۙۖۘۤۡۘ۫ۖ۬ۜۗ۬ۖ۬ۢۖۨۧۤۡۤۖ۫" : "۠ۥۢۙۤ۟ۜۧۘ۫ۥۧۤۥۜ۫ۛۙ۠ۥ۫ۦ۟ۜۜۤ۬ۜۡۘ۠۟ۖۘ۟۬ۛۛ۟ۥ۟ۦ۫ۤۛۚ۟";
                                        case 1956204044:
                                            str8 = "ۡۙۘۦۡۘ۟ۜۦۘۡۥۢۜۨ۫ۚۡۛۘ۠۬۠ۢۢ۬ۢۢ۬۟";
                                            break;
                                    }
                                }
                                break;
                            case -74174007:
                                break;
                        }
                    }
                    str = "ۥۢۜۨۚ۟۠۟۬۬ۥۦۘۦۥۙۢۡۢۧۖۗ۫ۙۡۧۢ۫۠ۧۗ۬ۗۧ۫ۛۦۖۘ۟ۧۖۥۤۘۘ۠۟ۨۤۡۨۦۥ۫";
                    break;
                case -1413186700:
                    Process.killProcess(Process.myPid());
                    str = "۟ۗۡۘۥۜۧۗۙ۬ۥ۬ۖ۠ۖۦ۫۫ۨۘۛ۟ۙۛۛۡۘۖۢۜۦۗۘۘۨۘۨۘۤۧۘۘۖۨۧ۫ۘۖۚ۟ۢۢۦ۬ۘ۬۫ۦۦۘ";
                case -1330507700:
                    k2.logToFloatingWindow("Root拦截触发", "error");
                    str = "ۥۢ۟ۡۦ۫ۗۦۢۡۦۘۘۨۦۢۡۡۛۦ۬ۢۘۧۥۚۖۨۘۛ۟ۜۤۗ۫ۥ۠ۥۛۜۥ۟۟ۖۘۧۜۥۦۢۗۘۜۤۡۥۦۘ";
                case -1307929824:
                    String str10 = "ۧۧۖ۟ۢۛۦۜۨۨۚۥۘۘۖۜۙۖۦۘۢۥ۫ۤۤۙ۠۟ۦ۫ۜۤۙۛۙۢ۠ۘۤۡ۟۬ۚۡ۟ۜۤۚۤ";
                    while (true) {
                        switch (str10.hashCode() ^ (-268200239)) {
                            case -673930791:
                                String str11 = "ۧ۬ۨۘۢۗ۟۬ۜ۠۫ۢۜۘ۠ۨۧۘۨۡۨۘۢۜۜۘۘۤۡۘ۠ۡۖۙۥۘۧ۬ۤۗۜ۠۬ۥۛۧۗۥۛۖۜۡ۫۬ۨۜۥۖۤۧ";
                                while (true) {
                                    switch (str11.hashCode() ^ 808297493) {
                                        case -1338176275:
                                            str11 = Utils.isDeviceRooted(context2) ? "ۗۤۛۜۨۥۘۖۖۤۥۜۡۖ۫۟ۘ۬ۧۦۖ۬ۧ۠ۗۘ۬ۥۘۥ۬ۧۥ۟۠ۨۨۡ۫ۘۤۧۛ۠" : "ۗ۠ۙۥۦۙۤ۟ۖ۠ۛۨ۟ۛۜۗۧۛ۟ۨۡۡۜۘۘ۟ۧۡۘ۬ۙۖۘۛۦۥۢ۫ۖۘۦۗۦۢۢ۟ۨ۟ۢۘ۟ۚ";
                                        case -623058962:
                                            str10 = "۫ۢۦۡۡۘۘۜ۬۬ۛ۠۬ۤۚ۠۫ۥۤۜ۟۟ۤۨۦۘ۠ۨۘۡۙۥۘۢ۟۬ۖۦۜۘۦۖۡۦۙۙ";
                                            break;
                                        case -601146050:
                                            str11 = "ۢۘۜۡۤۡۘۥۘۗۦۛۖ۟ۧۥۤۛۥۧۚۘۘۡۙۘ۠ۧۘۤۖۨۘ";
                                        case 1580498689:
                                            str10 = "۬ۡ۫ۜ۟ۥۘۜ۬ۦۗۘ۫ۢۥۦۘۚ۟ۘۘۗ۬ۢ۟ۧۚۡۛۤۦ۟ۘۜۦۛۨ۟ۚۚۧۙۡۥ۠۬ۙ۫ۗۚ";
                                            break;
                                    }
                                }
                                break;
                            case -364219092:
                                str = "ۧۛ۟۠ۨۖ۬ۜۘۛۚۗۧۧۥۘۖۘۧ۠ۖۘۗۗ۫ۗۦۛۗۗۥۘ۫ۜۚۚۧۦ";
                                break;
                            case 926707770:
                                str10 = "ۗۤۘۦ۬ۛۙۚ۠۬ۨۤ۟ۤۜۚۜ۫۟ۖۛۨ۬ۦۘۗۖۚۜۘ۠ۗۜۘۤ۫ۖ۠ۛۥۦۙۗۚۗۗۧۨ۟۠ۜۘۖ۠۫";
                            case 1592879484:
                                break;
                        }
                    }
                    str = "ۢۡۨۧۥۜۙ۫ۡۤۡۤ۬ۡۖۚۡۧ۬ۘ۬ۗۗۘ۬ۖۜۘۚۥ۫ۗۢۦ۫ۛۦۖۧۨۖۢ";
                    break;
                case -1245514792:
                    String str12 = "ۗۦ۟ۛۛۢۢۗۢ۫ۘۦۘۙۖۡۢ۠ۡۤۥۗ۬۫ۘ۬ۥۨۘۖۜۤ۟ۚۗۨۢۦ۬ۜۗ۫ۦۡۘ";
                    while (true) {
                        switch (str12.hashCode() ^ 83617390) {
                            case -1473453182:
                                str = "۠ۚۗۤۘۜۘۗ۟۠ۚۖۨۘۧ۟ۥۘۥۖۖۘۙۡۚ۬۠ۘۘۢۛۜۧ۫۠ۢۢۘۚ۟ۜۙ۫ۜۘۡۡۜۘ";
                                break;
                            case -436256674:
                                String str13 = "ۖۘ۬۟ۗۖۤۜۘۘ۟ۨ۬۬۫ۧۨ۟۬ۡۦۥۘۘۖۘۨۥۘ۬ۦۙ۠۠ۢۖۛۚۘۜۥۥۘ";
                                while (true) {
                                    switch (str13.hashCode() ^ 1968879336) {
                                        case -1789588951:
                                            str12 = "ۤۢۜۦۦۦۢۘۢ۟ۨ۫۟ۜۧۚۜۗ۫ۡۜۘۢۨۘۤۘ۫ۢۢۡۘۖۖۘۘۧۜۡۘ";
                                            break;
                                        case -997860930:
                                            str12 = "ۤۜۜۡ۫ۛۨۢۙۘۙۖ۫ۥۡۚۥ۫۟ۨۡۨۚۚ۠ۡۘۢ۬ۥۙۧۛۦ۫ۥۘۧۨۚۦۥۘۙۛ۫ۦۖۢ";
                                            break;
                                        case -567199593:
                                            str13 = zOptBoolean2 ? "ۗۙۥۘ۫ۘۛۚۢۜ۬ۚۦۘۤۥۧۘۨۨۧۘۤۚۜۘ۟ۜۡ۬۟ۦۘ۠ۧۦۘۢۘۨۘۚۚۢ۟ۗۦۤۚۨۘ۫۟ۙۨۥ۬" : "ۦۚۛۡۥۖ۟ۢۨۢ۫ۨۡۜۛۜۘۜۚۘۘۖۜۜ۠ۨۗۦ۟ۢۚ۠ۖۨۘۘ۬ۘۘ۫ۤۨ";
                                        case 1208806460:
                                            str13 = "ۢ۠ۘۦۘۖۘۖۧۨۘۨ۟ۧ۠ۡۥۤ۠ۙۨ۫ۨۙۦۡۖ۠ۘۢ۠ۥ۠۬ۦۘۜۡۜۘ";
                                    }
                                }
                                break;
                            case 826106573:
                                str12 = "ۡۛۜۘ۠ۥ۬۟ۧۦ۬ۖۙۨ۟ۖۘۘۗۦۘ۬ۡۛ۟ۤۧۦۦۛۧۙۦۘۖ۬ۡۡۦۨ۟ۛۧۡۢۚۧ۠ۙۥۨ۟ۚۜۜۡۚۦ";
                            case 962179632:
                                break;
                        }
                    }
                    str = "ۤۨۘۦۗۡۚۙۜۘۛ۟ۗۚۤۨۢ۫ۙۡۥۧۚۗ۟ۨ۫ۡۖۘ۫ۧۚۥۘۙ";
                    break;
                case -1144219144:
                    str = "ۦۗۧۚۥۘ۠ۜۙۧۨۛۘۡۥ۫ۘۚۡۨۖۘ۫ۙ۟ۧۦ۠۬ۜۘ۫۫ۙۗۨ۫ۥ۟ۧۛۘۦۘۛۖۖۧۘۙۡ۬ۢ";
                case -1119759433:
                    str = "ۥ۟ۖۗۜۖ۬ۧۚ۬ۜۘۘۨۜۚۚۛ۬۠ۥۙ۟ۙۘۜۦ۠ۖ۫۟ۦۨۧۚ۫ۦ";
                    jsonResult = s0.getJsonResult();
                case -1064855921:
                    YSQScD6K(activityECt8jHZ4, jsonResult);
                    str = "ۘ۟ۦۘ۠ۗۨۡۧۦۘۤۚ۬ۥۙۜۘۛۤۚ۟ۚۨۤۘۖۛۛ۠ۧۡۧۖۡۦۨ۬ۘ۫ۡ۠۬ۢۦ";
                case -985990020:
                    str = "ۘۧۖۘۨۤۡۘۢۢۢۨۜۡۘۚۡۦۘۖۛۜ۟ۨۜۗ۠ۛۛۗۜۘۨۖۧۙۡ۫ۨۛۚۜ۫ۧ۟ۤۢ";
                case -977183270:
                    a = true;
                    str = "ۨۛۡۘۙ۫ۡۢۘۚۗۡۘۘۦۛۧۘۡۖۘ۟ۛۛۛۖۙۧۜ۟ۜۙۘۘ۬ۖ۟ۘۘۙۨۥۡۖ۫ۡۙ۬ۨۘۗۥۦ";
                case -854134080:
                    Process.killProcess(Process.myPid());
                    str = "ۛ۠ۤۤۛ۟ۦۥ۠ۡۦ۠۟ۡ۬ۘ۬ۥۘۜۨۢۢۖۤ۬ۘۗۡۖۛۜۨۥۡۖۜۥۘۧۗۜ۠ۨۘ۠۫۠ۨ۬ۨۢۗۦ";
                case -841919947:
                    k2.k3zLJuvX(activityECt8jHZ4, "⚠️ 沙盒环境不允许使用此应用，由于在调试模式,本次不结束运行");
                    str = "۫ۧۙۖۢۜۘۙۨۙۖۥۙۚۘۡۢ۫ۘۘ۬۫ۜۤۦۗۧ۟ۨ۬ۖۘۘ";
                case -830849589:
                    str = "ۙۤۦۘۢ۟۟ۗۧۛۙ۠ۢ۠۫ۦۖۜۦۘۜۦۧۜ۠ۙۢۜ۫ۙ۟ۨۖۘۜ۫ۜۗۤۤۜۦۖۦ۫۬ۧ۫ۚۥ۫ۡۘ۫ۥۚ";
                    zOptBoolean2 = jsonResult.optBoolean("ban_VirtualApp", false);
                case -781144370:
                    s3Var = new s3(context2, fcRuQsQrcxOAzxwEalcM.APP_ID, Utils.getUniqueDeviceId(context2));
                    str = "۠ۚۥۡۖ۟۠۟ۖۘ۬ۙۡۘ۟ۥۤۧ۟۟ۛ۬۠ۚۛۜۘۗۡۤۢۥۚۘۤۦۘ۠ۗۤۛۜۥۜۥۢۗ۫۟ۗۘ۠ۛۜۧۘۡۧ۫";
                case -779193706:
                    Process.killProcess(Process.myPid());
                    str = "ۚۧ۟ۦۥ۫ۜۡۚۚۡۨۖۤۘۤۚۗۢۦۖۛۛۨۚ۟ۡۘۖ۬ۜۛۧۘۖۧۦۘ۬ۘۘۘ۫۠ۛ۫ۦ";
                case -660019678:
                    str = "ۢۦۦۘۛۦۦۖۢۜۦ۬ۗۡۤۨۦۘۙۧ۠ۛۖۘۡۤۧۨۥۖۚۗۜۨ۠ۦۘۦۚۖۘۦ۬ۖۗۛۖۦ۫ۡ";
                case -605707288:
                    String str14 = "ۥ۟۟ۡۛۨۧۖۘۘۘۚ۬۬ۜ۠۬ۡ۬ۜۧۥۘۨۖ۟ۨۦۚۤۘۜ";
                    while (true) {
                        switch (str14.hashCode() ^ (-1167144892)) {
                            case -384905783:
                                str14 = "۫ۘۜۘۤۤۤۚۥۚ۬ۥۥۘۢۢ۬۫ۦۙ۠ۗۦۥۧ۠ۜۤۦۦۜۘۗۧۛۡۛۗۖ۟۟۫ۚۧ";
                            case 1076942114:
                                String str15 = "ۦۙۜۢۦۜۘۘۧ۫۫ۤ۬ۡ۫ۖۚۢۙۦ۫۟ۘ۠ۨ۟ۦۙۘۜۤۦ۬ۙۜۚۛ";
                                while (true) {
                                    switch (str15.hashCode() ^ (-1614656429)) {
                                        case -2104962255:
                                            str14 = "ۙۤۜۧۜۜ۬ۗۜۘ۬ۢۢۥۤۖۨ۬ۨۤۦۘۚۥۤۢۙۛ۬ۗۖۘ۠ۥۡۘ۠ۚۨۘۙۛۤۦۤۜۘ";
                                            break;
                                        case -1195949275:
                                            str14 = "ۡۡۜۘۙۖۨۛۚ۫ۙۘۨۘۘۥۡۘۨۥۨۘۛۢۢ۟ۗۛۦۘۖۨۡۥ";
                                            break;
                                        case -1132668208:
                                            str15 = Utils.isInVirtualApp(context) ? "۠ۘۦۜۤۗ۬ۢۖۘۙۘۡۦۗۨۘۚ۫ۢۥۨۤۧۧۨۢۖۜ۠ۨۜۙۥۖۙۢۢ۫ۖۛۖۖۘۙۖۨ۬ۡۨۘۥۦۧۨۗۤ" : "۠۬۠ۦ۬ۜ۫ۘۤ۬ۤۚ۬ۖۜۡ۠۫ۖۦۨۘۦ۠ۛۨۡۘ۫۠ۖۗۖ۬ۨۙ";
                                        case 2098479805:
                                            str15 = "ۙۧ۫۟ۗۥۘۚۡۢۛۧۤۥۙۡۘۛ۬ۗۨۜۖۙ۬ۜۢۗۤ۫ۧۥۛۥۡۥۖۥۥۘۖ۠ۧ";
                                    }
                                }
                                break;
                            case 1119960288:
                                break;
                            case 2142967965:
                                str = "ۦ۬۫۟ۚۜۦۛ۫ۨۘۙۨۦۨۘۜۥۚ۠ۡۘۘۘۙ۫ۤۘۥۘۛۛۤ۟ۗۚ۟ۤۤۢۤۦۛۧۨۘ۠ۘ۟ۨۙۨۘۖۘ۟ۡۥۘۘ";
                                break;
                        }
                    }
                    str = "ۤۨۘۦۗۡۚۙۜۘۛ۟ۗۚۤۨۢ۫ۙۡۥۧۚۗ۟ۨ۫ۡۖۘ۫ۧۚۥۘۙ";
                    break;
                case -520600226:
                    String str16 = "ۦ۟ۢۡۖ۬ۘۙۡۥ۠ۜۗۜۖۘۦۡۙۛۚۥۧ۟ۖۨ۫ۚۥۙۖۡۨۡۨۧۘۛۤۜۘۤۖۚۢۧۨۗۤۙ۫ۢۤ۬ۚۨۘ";
                    while (true) {
                        switch (str16.hashCode() ^ (-1495933062)) {
                            case -1315970994:
                                str = "ۦۗۢ۠ۧۚۚۜۨۙۗۖۡ۫ۜۘۙۚۚۖۦ۟ۢ۫ۚۨۦۥۙۦۘۗۧ۬ۙۖۙ۬ۗۚۤۦۧ";
                                continue;
                            case 192215411:
                                String str17 = "ۛ۠ۚۛۥ۬ۖ۫ۖۘۘۢ۫ۘ۬۬ۤۚۛ۠ۖ۫۠ۛ۫ۡۘۚۚۛۜۘۛۙ۫ۤۗۢۧۘۧ۫ۦۨۘ";
                                while (true) {
                                    switch (str17.hashCode() ^ 571925909) {
                                        case 674896445:
                                            str17 = "ۥۗۘۖ۟۟ۦۦۡۙۛۧۥۧ۠ۙۚ۟ۧۗ۠ۢۛۜۗۖ۟۠ۜۘ۫ۧۖ۠ۢۦۘ۫ۨۗ۠ۢۢۜۙ۠ۧ";
                                        case 732820341:
                                            str16 = "۫۟ۢۘۛۗۙ۠ۛۙ۟ۗۙۤ۬ۧۤۦۘۡۡ۫ۤۦ۟۫ۤ۟ۘۖۤۦۘۥۘۦۤۛۥۧۨۘۚۧۜۘ";
                                            break;
                                        case 846216657:
                                            str17 = s0.isDebug() ? "۫ۛۨۥ۫ۜۘۘۡ۟۬ۙۥ۟ۚۡۘ۠ۥۚۤ۬ۦۢۦۧۘۗۙۜۥ۟" : "۫ۛ۫ۛۚۗۚۘۗۦۛۜۘۜۖۜۛۙۚۤۢۚۛ۫ۜۘۡۙۨۘۚ۬ۦۙۛۦۙۨ۬ۘۧۜۜۖۤۘۢ۟ۚۨ۠۬ۗۥ۫ۖۧ";
                                        case 1634171072:
                                            str16 = "ۧۖۦۘ۟ۙۦۧۘۧۘۥۘ۟ۡ۫۬ۜ۬ۨۘۚۢۨۚ۬ۥۘ۟ۖۦۘۗۤۡۢۥۦۘۡۜۜۘۢۘۘۖ۬ۙۡۙۖۘۧۛۖۘ";
                                            break;
                                    }
                                }
                                break;
                            case 582616316:
                                str16 = "ۜۜۘۘ۫ۥۤۖۡۧۘۡۙۥۗۥۙۡۧ۟ۢۗۧۦۤۦۜ۫ۘۜۛۤ";
                                break;
                            case 2108371997:
                                str = "ۧۦۛۘۨۨۘۙۢۙۙۤۚۙۘۨۥۖۢۗۘۜۥ۫ۖۘۘۙۗۜۛۥۘۧۧۥۘۗ۫ۥۘۜۚۘۘۗ۫ۨ";
                                continue;
                        }
                    }
                    break;
                case -499312692:
                    String str18 = "ۨۧ۫۫ۧۧۤۜۦۦۢۤۧۨ۬ۙۜۧۤ۟ۖۘۜۗ۫ۤۧ۫ۢۢۛ۠ۜۨۘۡۢ";
                    while (true) {
                        switch (str18.hashCode() ^ 646588613) {
                            case -1974450340:
                                str18 = "ۚۥۦۘ۫ۨۘۘۙۢۙ۬ۡۙۨۡۧۘۜۨ۬ۖۦۦۖۥۨ۟ۤۧ۟ۘۛ";
                            case -569398226:
                                str = "۫ۥ۬ۥ۫ۦۘ۟ۚۨۘۥۚۥۢۜۜۗۚۡ۟ۧۤۛۧۥۘۛۨۥۗۗۜ۫ۛۘ۟ۢ۠ۦ۠ۦۜۙۧ۫ۙۖۘۢۡۘ";
                                break;
                            case -190873045:
                                break;
                            case 406543620:
                                String str19 = "۟ۚ۟ۢۙۡۘ۫ۙۨۘۦۘۥۘۦۘۨۘ۟ۥ۟۬ۘۦ۬۟ۥۘۖۢۙۧۨ۫ۦ۟ۚۛۥۗۚۢ۠ۙۛ۬ۘۧۘۘۗ۠ۨۜۢ۫ۖۥۗ";
                                while (true) {
                                    switch (str19.hashCode() ^ (-90141372)) {
                                        case -1920492124:
                                            str19 = "ۜ۟ۘ۟ۛۖۤۚۛۜۧۗۡۙۜۗۖ۟ۢۨۖۘ۟۬ۚ۠ۚۖۘۛۨۦۡۖۚ۠ۤۖۘۡۜۦۤۦۘۘۥ۬ۘۘۚۥۧ";
                                        case -1818172041:
                                            str18 = "ۢۙۙۘۥۜۘۢۘۖۘۚۥۧ۫ۨۘۨۨۛۘۜۗۡ۟ۨۘۤۦۨۦۡۖۘۗۘۧۘۧۜۘۘ۫ۤۚۙۚۧۦۤۖۤۜۨۘ";
                                            break;
                                        case -1568357506:
                                            str19 = zOptBoolean3 ? "ۚۡ۠ۨۢۙۗۦۦۘۤۘۦۘۙ۬ۦۙۥ۟۠ۢۖۛ۫ۚۛ۬ۗ۫ۗۜۚۚۗۜۚۡۘ۠۠ۥۘۤۢۜۦۚۜۘۤۘۤۢ۠ۘۛ۠۫" : "ۢۛۗ۫۬ۥۦۜۘۙۢۙۡۛۙۥ۬ۧۘۙۡۘۚۗ۬ۙ۟۬۠ۖۘۘۧۢۢۡۙۖۘۜۡۥۘۤۜ۬";
                                        case -1154702479:
                                            str18 = "ۦ۬ۜۨۘۡۘۤۦۧۘۛۨ۟ۨۛۧۥۚۚ۫ۗۘ۟ۦ۟ۗۜۡۢۦۙۨۗۦۘۙۗ۬۠۬ۨۘ۠ۨۧ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    str = "۠ۡۥۘۙۖۤۨۛۚۘ۬ۛۧۡۧۛۜۘۘۘۘۢۘ۬ۚۘۚۦۘۦ۫ۛ";
                    break;
                case -462363807:
                    System.exit(0);
                    str = "ۘۧۖۘۨۤۡۘۢۢۢۨۜۡۘۚۡۦۘۖۛۜ۟ۨۜۗ۠ۛۛۗۜۘۨۖۧۙۡ۫ۨۛۚۜ۫ۧ۟ۤۢ";
                case -411505911:
                    k2.k3zLJuvX(activityECt8jHZ4, "⚠️ 模拟器环境被远程拦截，由于在调试模式,本次不结束运行");
                    str = "ۥۥۗ۟ۛۨۘۚۦۖۖۧۛ۟۠ۜۘۛۗ۬ۡۛۘۢۙۖۘۢ۫ۖۘ۠ۖۡۘۗۚۡۘۥۧۘۨۗ۫ۗ۟ۚۙۡۡۘۖۤۙۗۥ۠ۤۥۘ";
                case -409578949:
                    str = "ۥۦۧۘۚۜۧۘۤۥۦۘۗ۬ۨۜۚۥۘۦۗۜۘۗ۬۠ۨۤۙ۟ۖۜۗ۫ۘ۬ۡۘ۬ۚۨۘ";
                    zOptBoolean5 = jsonResult.optBoolean("ban_Root", false);
                case -399228913:
                    k2.EOAuPrGR(activityECt8jHZ4, context2);
                    str = "ۦ۬۠ۤ۫ۥۧۦۡۘ۟ۚ۫ۘۖۥۧۚۦۘ۫ۢۘۙ۬ۥۘۖۧۡۚۘۘۚۙۡۘۤۘ۬ۖۘۤۘۥۨۘ۟ۚۡۦۙۜ";
                case -345127924:
                    str = "ۜۨۖۘۚۡ۫ۡۨۘ۠ۙۡ۬ۖۧۗۚۥۖ۫ۥ۟ۧۧۙۢ۠ۦۚۡۙۡۡۢۦۥ";
                    zOptBoolean4 = jsonResult.optBoolean("ban_Xposed", false);
                case -298195489:
                    str = "ۛۛ۬ۛ۠ۜۘ۫ۘۘۥۗۖۘۥۘۘۛۨۦ۫ۦ۫ۥ۠ۙۨۦۘۤ۬۠ۘۛۜۢۜ۬ۥ۬ۧۨۘۗۧۚۜ۠ۖۢ۠ۥۧۥۘ";
                    activityECt8jHZ4 = Utils.ECt8jHZ4();
                case -271152091:
                    k2.logToFloatingWindow("模拟器拦截触发", "error");
                    str = "ۡۤۜۧۜۡۙۨۙۙۙۘۘۘۧۘۘ۫ۖۧۤ۬ۡۘۡۖۖۘۧۢۤ۬ۡۖۘۨۧ۬ۛۤۛ۬ۙۛ۫ۙۡ";
                case -99481778:
                    String str20 = "ۘۖۛۛ۫ۖ۟۠ۚ۬ۧۘ۠ۖۧۘۦۜۧۘۥۚۤۨۚۦۗۜۘ۬ۗۨۛۨۘۖۥ۠";
                    while (true) {
                        switch (str20.hashCode() ^ (-2130368838)) {
                            case -2128410347:
                                str20 = "ۡۚۥۘۦۥۥۘۛۤۜۡۥۡۘ۟ۖۢۡۜۘۘۧۧ۠ۡۚۨۘ۬ۡ۟ۢ۠۠ۦۧۧۨ۠ۥۛۚۚۗ۬۬ۗۗۖۨۢۚۡ۬ۙ۟ۖۛ";
                                break;
                            case -1820044822:
                                String str21 = "ۧۢۘۘۨۛۜۘۢ۟ۛۡ۠ۥۘۧ۟ۢۖ۟ۚۗۡۙۢۗۗۧۦ۫ۖۘۨ۬ۦۜۚۘ";
                                while (true) {
                                    switch (str21.hashCode() ^ (-1798237958)) {
                                        case -1948580019:
                                            str21 = "ۤۘۛۗۦۦۢ۠ۛۚۜۡۚ۟۬ۜۗۛ۬ۥۢۛۡۙۙ۟۬ۢ۫ۥۧۦۖۢ۟ۘۘۙ۬ۚ۟۬ۥۘۥۛۧۦۛۜ";
                                        case -1841010770:
                                            str20 = "ۢ۬ۖۘۘۨۘۚۥۘۡۜ۫ۥ۟ۥۘۨۜۤۢۤۡ۠ۛۨۘۜۗ۟ۧۚ۫ۗۡۧۘ۫ۜۦۙ۠ۨۘۗۖۥ";
                                            break;
                                        case -1790532821:
                                            str20 = "ۡۗۨۘ۬۠ۢۦۧ۟۬ۖ۬ۙۚۖۘ۫ۥۜۘۗۗ۬ۚ۬ۜۘۡۧ۠ۥۚۖ";
                                            break;
                                        case 1579700060:
                                            str21 = s0.isDebug() ? "ۢ۠ۗۙۘ۬ۨۚۖۘۚۗۘۢۥۦۘۗۨۢۘۥۙۦۗۢۜ۠ۘۦۤۛ" : "ۛ۫ۛۘۙۢ۠ۢۖۘۡۧۦۖۛۦۨۨۨۘۡۥۨۙۥۢۖۙۡۧۛ۬ۖۦۤ۫۟";
                                    }
                                }
                                break;
                            case -432030842:
                                str = "ۚۖۜۚ۫ۤۖۗۡۘ۫ۨۡۘۧۙ۟ۖ۠ۨۘۖۜۘۛۖۖ۫ۧۖۘۧۤۛ";
                                continue;
                            case 1369452180:
                                str = "ۛۦۤۛ۟ۥۦۗۨۘ۬۟ۙۨۦۤ۟ۧ۬ۖۙ۟ۙۛۜۜ۬ۢۖۥۚ";
                                continue;
                        }
                    }
                    break;
                case -62425984:
                    str = "ۤۨۘۦۗۡۚۙۜۘۛ۟ۗۚۤۨۢ۫ۙۡۥۧۚۗ۟ۨ۫ۡۖۘ۫ۧۚۥۘۙ";
                case -27870019:
                    k2.k3zLJuvX(activityECt8jHZ4, "⚠️ 当前设备已 Root，已被远程策略拦截，由于在调试模式,本次不结束运行");
                    str = "ۢۥۤۗ۟ۘۗۛۘۧ۬ۢۨۙۥۘۧۗۚۢ۫ۤۤ۫ۡۘۥۖۡۘۦۨۖۘ";
                case 33183375:
                    String str22 = "ۧ۫ۜۖۖۘۨ۫ۥۘۡۖۦۛۢۥۘۛۢۡۙۤۧۗۢۥ۟ۧۙ۫ۨۡۘۖۦۖۖۨۧۘۚۛۡۗۛۙۢۢ۠ۜۗۜۘۤ۬ۥۛۘۘ";
                    while (true) {
                        switch (str22.hashCode() ^ (-755558965)) {
                            case -1908529685:
                                str = "ۙۤۚۘۨۖۘۤۧۗ۟ۚۥ۠ۨۨۘۙۙۛۥۚۖ۟ۦ۫ۘۨۤۥۧۨۖۢۘۘۘۥ۬ۡۨۘۘۗۦۘۧۘۙۘ۫۫ۥۡۢۜۛ";
                                continue;
                            case 1098412674:
                                str = "۫ۛۖۘۧ۠۫ۗۨۥ۠ۢۜۘۧ۬ۥۗۨۜۘۖ۬ۧۨ۬ۜۘۙۦۖ۠ۦۘۘ";
                                continue;
                            case 1937421202:
                                String str23 = "۠ۧۨۗ۟ۧۖ۫ۨۘۦۘۢۡ۫ۘۖۜۘۛۡۖۘۡۜۘۘۛۜۢۘۗۘۘۙۤۨۡ۫ۘۧۡۧۘ۬ۧ";
                                while (true) {
                                    switch (str23.hashCode() ^ (-1875523697)) {
                                        case -1475926676:
                                            str23 = s0.isDebug() ? "ۛ۟۟ۚۛۦ۟۬ۙۡۥۘ۫ۛۤۚۗ۫۬ۧۡۙ۬ۤۖۙۨۘۢۥ۟ۦۧۖۙ۬" : "ۙۧۡۘۡۤۢۨۚۤۡ۬ۦۘۧۡۡۘۚ۟۬ۥ۬۬ۨۢۡۘۜۧ۬۟ۜۗۙۚۨۘۛۙ۬ۦۥۖۘۗ۫ۤ۠ۙۖۛ۫";
                                        case -1054678384:
                                            str22 = "ۚ۫ۤۦۤ۫ۙۜۚۢ۠ۨۘۦ۟ۨۥۘۙۧۨۙۢۧۨ۟ۤۡۘۗۚۘ۟ۤ۫۬۫ۥۨۤۡۘ۬ۖۨۤۧۦۧ۫ۡۖۥۘ";
                                            break;
                                        case -954042686:
                                            str22 = "ۥۧۤۨۦۢۙۢۧۤۘۘۜۧۘۘۨ۟ۘۘۖۦۙ۫ۚ۬۫ۨۤۨۘۨۙۤۚۘۡ";
                                            break;
                                        case -445979015:
                                            str23 = "ۥۘۗۨۗۧۛۚۡۘۡۜۗۚ۬ۧۜ۠ۡۙۗۜ۬۫ۗۥ۠ۚ۬ۗۜ۠ۡۘۥۛۥۘۗۜۥۘۚۡۘ۟ۤ۠۫ۚۜۘۢۥۘۘۨۦۡۘ";
                                    }
                                }
                                break;
                            case 2018728827:
                                str22 = "ۦۛۦۘۥۗۤۛۛۦ۫ۦۦۡۤۜۢ۟ۖ۬۫ۛۚۛ۠ۚ۬۟۠ۛۢۜۙۢۦۧۨۥ۠ۥۖۤۚۘۜۨۘۡۛۦۘۛۢۡۘ۫ۧ۫";
                                break;
                        }
                    }
                    break;
                case 100480864:
                    k2.logToFloatingWindow("Xposed拦截触发", "error");
                    str = "ۢ۬ۜۘۨۦۘۘۚۜۘۘۗۘۦۤۢۢۦۗۢۜۤ۫ۤۥۚ۬ۤۜۧۨ۬ۚۙ۠ۛۨۧۘ۟۫ۨۜ۟ۡۘۤ۬ۛۙۗۧ۠ۤۗۨۦۘ";
                case 138760670:
                    k2.logToFloatingWindow("沙盒拦截触发", "error");
                    str = "۬۫ۙۡۘۢ۠۠ۛۘۜۧۥ۠ۘۘۨۦ۬۠ۜۡۜۜۖۤۗۦۦۤۡۘۢۦۤۜ۟ۥۚ۟۬ۚۢۖۘۜۨۧۘۜ۠ۡ";
                case 197588357:
                    str = "۠ۡۥۘۙۖۤۨۛۚۘ۬ۛۧۡۧۛۜۘۘۘۘۢۘ۬ۚۘۚۦۘۦ۫ۛ";
                case 204910662:
                    str = "ۥۢۜۨۚ۟۠۟۬۬ۥۦۘۦۥۙۢۡۢۧۖۗ۫ۙۡۧۢ۫۠ۧۗ۬ۗۧ۫ۛۦۖۘ۟ۧۖۥۤۘۘ۠۟ۨۤۡۨۦۥ۫";
                case 543920768:
                    String str24 = "ۙۖۖۢۖۜۦۥۘۘۢۦ۫ۥۢۛۚۛۦۘۚ۠ۥۙۙۨۦۦۘ۫ۡۘۨۚۡۧۢۦۘۡ۫ۛۙ۠ۘۘ۬ۗۥۘ۠ۜۡ";
                    while (true) {
                        switch (str24.hashCode() ^ 2079711673) {
                            case -1994587685:
                                str24 = "ۥۗۗۦۥۜۘۧۗۖۘۙۖۦۘۤۖ۟ۡۨۛۡۙۦۘۘۜۚۧۥۜۘۖۘۨۘۨۧۡۘۦ۟ۦۘ";
                            case -596977119:
                                break;
                            case 767617657:
                                String str25 = "ۡ۟ۜۘۛ۫ۨۤۤۖۧ۬ۥۤ۬ۛۤۘۘۘۥۧۘ۠ۗۘۤۢۨ۬ۨۘۖۨۗۖۢۙۧۤ۬ۦۚۡ";
                                while (true) {
                                    switch (str25.hashCode() ^ (-2113866534)) {
                                        case -1715106955:
                                            str24 = "۫ۦ۬ۛ۫۬ۜۖۨۥۛۡۘۘ۠ۖ۬ۚۦۘۙۗۦۘۜۤ۫ۖۜۛۧۙ۫۟۠ۨۤۜۜۘ";
                                            break;
                                        case 288386258:
                                            str24 = "۬ۨ۟۠ۧۘۘ۠ۜۡۘۥۡۜۥ۬ۨۘۛۚۚ۠۫ۡۘ۠ۘۡ۟ۖۜۤۖۡۦۡۖۘ۬ۤۤۢۘۛۖۡۨۤۚۦۘ۟ۖۥۗۙۛۖۖۢ";
                                            break;
                                        case 325178360:
                                            str25 = zOptBoolean ? "ۦۧۥۖۧ۟۫ۢ۠ۢۜۚۧۢۜۘۢۤۙۗۥۘۢۤۤۢۗۢ۫ۢۖۘۗۗۡۘۨ۫ۧ" : "ۜۙۦ۟ۡۥۗۨۘۢۡۦۗ۫ۜۘۖۙۥۘۥۡۙ۬ۧۜ۬۫ۘۖۙۨۘ";
                                        case 731234724:
                                            str25 = "ۚۚۜۛۘ۠۫ۛۙ۟ۜۨۘۢۘ۬ۘۧۗ۠ۤۡۘۚۨۘۤۜۙ۬۠ۖ";
                                    }
                                }
                                break;
                            case 1373470088:
                                str = "ۤۘ۬ۥۚۜۡۢ۫ۡۤۢۡ۠ۢۘۖۚۤ۟ۦۜۤۗۧۢۡۘ۫ۧۚۨ۬ۜۥۡ۬ۨۥۤۥۗۙ";
                                break;
                        }
                    }
                    break;
                case 747357172:
                    String str26 = "۠ۙۗۥۨۥۢۖ۠ۚۜۧۚۜۦ۟ۨۘۘۖۡۜۥ۟ۢۙۗۢۦۜۘ";
                    while (true) {
                        switch (str26.hashCode() ^ 377172916) {
                            case -1807193007:
                                String str27 = "ۘۡۢۦۙۨۘۡۘۜۛۨۘۨ۫ۨۢۚۜۘۜۥۦۢۗۤ۫ۥۜۘ۠ۖۖۚۖۧۘ۟۠ۗ";
                                while (true) {
                                    switch (str27.hashCode() ^ 299745101) {
                                        case -988259028:
                                            str26 = "ۤ۟ۖۘۡۗۦۚۛۜۘۙۜۤ۟۬۫ۧۤ۬ۦۖۡۘۘ۬ۨۘۗ۠ۦۘۧ۠ۥۤ۠ۥۜ۠ۙۖۥۘۘ۠ۡۡ۬ۜۨۘۜۘ";
                                            break;
                                        case -976014778:
                                            str27 = "ۜۥۜۘۨۥ۬ۚۥۢۘۧۘۘۨۧۥۡۡۜۦۨۡۘۛۛۖۨۥۗۛۧ۟";
                                        case -118398171:
                                            str27 = jsonResult.optBoolean("websocket", false) ? "۬ۥۦۘۨۡۨۘۨۦۘۦۡۢۚۙۘۘۘۖۧۘۦ۬ۡ۠۫ۛ۬۬ۚ۟۬ۖ۟ۘۖۘۦۤۜۘۖۖۜۘۦۘۜۡۧۛۨۤ" : "ۡ۠۬ۗ۬ۛ۠ۜ۠ۦۛۡۚۗ۟ۨۖۖۘۗۙۜۘ۠ۦ۟ۧۧۦۘۘۗۘۘۧۚۥۘ";
                                        case 555749993:
                                            str26 = "ۜۛۦۖۡۘۘۚۘۜۘۗۚۡۤۖۜۛۨۨۛ۟۬ۘ۠ۧۢۢۧۧۢۛۡۘۚ۟ۡۡ۟ۧۘۘۚۜۗۤ۬ۚۤۖۘۖۙۡ";
                                            break;
                                    }
                                }
                                break;
                            case -1694753096:
                                str26 = "ۜۥۦۘۦۗۚۛ۬ۙۘ۫ۖۦۗۥۗۡۘۘۢ۠ۡۘۖۛۛ۠ۖۨۘۙۛۧ۬ۗۘۙ۟ۘۛۜۙۗۡ";
                            case -1669912919:
                                break;
                            case 548112478:
                                str = "ۥۖۧۛۜ۠ۙۙۢۤۗ۠۠ۢ۟ۜۦۚۢۤۛۗۨ۠ۘۢۘۨۙ";
                                break;
                        }
                    }
                    break;
                case 801711023:
                    k2.logToFloatingWindow("双开拦截触发", "error");
                    str = "۫ۥۦۘۧ۬ۛۧۤۤۧۥۥۚۘۛۧۖۢۖۘۧۘۢۥ۟ۚ۟ۘ۟ۢۗۙۘۙۧۚۥۘۗۘۡ";
                case 803865106:
                    String str28 = "ۢۘ۬ۖۘۨۘۜۧۜ۟ۤۘۘ۬ۗۡۘۦ۬ۡۛۢۗ۫۫۠ۡۘۜۘ۬۫ۗ";
                    while (true) {
                        switch (str28.hashCode() ^ 537646724) {
                            case -690576888:
                                str = "۬ۛۚۙۙۡۖۥۢۧ۟ۥ۫ۛۖۢۘ۠ۘۚۗۛۜۘ۬۠۟ۘۦۛۡۘۡۚۜ";
                                continue;
                            case -316396959:
                                str = "ۖۗ۠۠ۤ۠ۨ۠ۖۨۙۥۘۡۘۛۘۗۥۤۙۚۨۥ۬ۧۨ۟۠ۡۦ۫ۘۖۦۛۙۚ۠ۘۦۧۘ۫ۦۢۦۨۘۦۦۢۦۜۥ";
                                continue;
                            case 329784270:
                                String str29 = "ۜۜۧۘۤۖ۫ۖ۬ۖۘۧۡۨۘۤۤۗۙۤۥۘۢ۟ۜۦۢۥ۬۟ۢۢۜۗ۬ۡۚۥۜ۠ۗۥۛۛ۠ۙ۫ۖۤۢۛۙۤۜۖ۬۟ۗ";
                                while (true) {
                                    switch (str29.hashCode() ^ 606276427) {
                                        case -2046445863:
                                            str29 = "ۘۥۙۤۥۦۘ۫ۘۤۦۢ۫ۡۗ۬ۖۧۘۡ۬ۡۘۖۚۜۢۡۦۘۡۜۚۛ۫ۤۚۦۥ۠ۙۢۘۡ";
                                        case -1756942074:
                                            str28 = "ۛۥۦۘۤۗۨ۫ۨۡۘۢۤۤۘۧ۫ۖۢۡ۬ۚۜۘ۬ۨ۬۠ۚ۠ۚۖ۫۬ۢۛۚۗۦۧۖۚۖۤۘ";
                                            break;
                                        case -329365456:
                                            str29 = jsonResult.optBoolean("enable_popup_keywords", false) ? "ۘۜ۬ۗ۟ۛۨۛۖۦۡۘ۟۠ۡۘۘۦۨۘۚۘۜ۟۟ۨۥ۬ۨۘۙۘۙۡۦۙۥۥۖۘۛۧۨۘۚ۬ۜۘ۟ۛۜ۬ۚۖۘۗۧۨ۫ۚ۫" : "ۡۛۥۘ۫ۙ۬ۨۡ۫ۜ۠ۤۥۥۖۖۧۨۘۨ۟۠ۨۙۦۘۨۥ۫ۖۦۥۢ۫۫ۛۤۤ۠ۜۖۘۢۘۥۘ";
                                        case 367288464:
                                            str28 = "ۜۖۚۧۘۚۗ۬ۜ۟ۨۢۙۤۡۗۚ۟ۦۘۨۘۥۢۜۙ۟ۥۘۘۤۦ";
                                            break;
                                    }
                                }
                                break;
                            case 1092702944:
                                str28 = "ۗۜۖۘۗۘۢ۬ۜۧۜ۫ۙۥۘۜ۟ۥ۬ۘ۠ۘۘ۟ۚ۠ۢ۫ۘۨۘ";
                                break;
                        }
                    }
                    break;
                case 878471136:
                    String str30 = "ۚ۬ۛۤۘۚ۟ۖۧۘۡۥ۬ۨۖۘۖ۟ۦۥۜ۬ۖۜۚ۬ۚ۠ۘ۟ۜۚۘۨۙۛۦۘۡۛ۬ۤ۫۟ۘۢۨۥۧ";
                    while (true) {
                        switch (str30.hashCode() ^ (-1635929203)) {
                            case -750574162:
                                break;
                            case -508953607:
                                String str31 = "۬ۨۚۧۛۙ۫ۖۙۚۦۙ۠ۛۡۛۦۙ۬۠ۤۨۡۢ۟ۥ۟۬ۙ۬";
                                while (true) {
                                    switch (str31.hashCode() ^ 1596412123) {
                                        case -1908890835:
                                            str31 = "۬ۚۨۘ۬ۙۨۘۢۜۛۤۜ۫ۥۖۜۘۤۙۛ۫ۛۖۘۢ۬ۦۜۗۥۘۖ۟ۚ";
                                        case -1521967963:
                                            str30 = "ۨۨۢۜۡۦۘ۬ۗۦۤ۠۠ۚۡۦۥۗۦۘۡۢۛۨۦۤۗۨ۟ۛۨۡۘۖۚۤۗ۫ۖۘۘۦۛۢ۠۟ۦ۟ۧۗۜۨۘۛۗۖۗۤ۬";
                                            break;
                                        case 1197174198:
                                            str31 = zOptBoolean5 ? "ۖ۬۬۬ۦۥۙ۠ۨۘۜۢۙۢ۟ۘۢۖۖۘۦ۠ۛۖۗۗۘۘۥۘۙۧۜۘ۠ۗ۟ۚۚۤۦۙۜۘ۠ۨۦ۫ۤۨۥۜۥ" : "ۛۜۜۗۡۦۘ۫ۖۜۖۤۦۘ۫ۤۚۦۙ۠۫ۜۖۗۚۨۗۤ۟ۡ۬ۙۡ۬ۛۤۢۦۘ";
                                        case 1937815038:
                                            str30 = "ۙۡۖۜۧۦۘ۬ۚۗۛ۟ۜۘۗۧۜۘۢۥۢۘ۫ۘ۫ۢۢۡۚۖۘۘۧۚ۬ۛۦ";
                                            break;
                                    }
                                }
                                break;
                            case 370135532:
                                str = "۟ۗۧ۟ۧۘۨۦۥۖۜۖۡۖۥۙۨۥ۫ۡۛۥۡۡۘۧۛۖ۠ۜ۬۬۬۬۠۟ۜ۫۟ۘۚۘ";
                                break;
                            case 542855855:
                                str30 = "۫ۢۚۨۛۘۨۨۚۛۥۖۤ۠ۢ۫ۚۨۦ۫ۚۜۙۗۨۗۖۘ۠۠ۤ۠۟ۡۘ۫ۦۜ۬ۢۥۘۡۡۦۗۜۘۛ";
                        }
                    }
                    break;
                case 888199189:
                    String str32 = "۫ۧۚۡ۠ۢۛۦۙۘۛۡۘۥ۠ۢۥ۟ۗۦ۠ۡۘۙ۬ۛۢ۠۠ۜۙۡۗۛۖۘۧۖۧۥۛۙۦۧ۠۬۟ۥۘۗۧۜ";
                    while (true) {
                        switch (str32.hashCode() ^ (-2125698971)) {
                            case -1237276555:
                                str = "ۤۧۦۘ۬۬ۜۘۘۤۜ۫۠ۜۘ۟ۗۢۦۛۥۤۙۙۡۦ۬۫۫ۖۘۤۗۢۖۨۤۗ۠ۗۨۖۖۘۙۧۢۙۤۡۘۨۖۧۧۧۨۘ۫۫ۖۘ";
                                break;
                            case -422823064:
                                str32 = "ۦ۠۟۟ۖۡۘۧۥۨۥۜۜۘۡۥۡۛ۬ۡۘ۠ۦۨۘۛۢۘۤۧۡۘۦۛ۟";
                            case 77249551:
                                break;
                            case 1548709853:
                                String str33 = "ۨۘۦۨۙۜ۠ۦ۬۫۠ۥۢۢۗۚۢۖۛۜۦۘ۫ۛۦۘ۟۬ۢ۠۠ۜۘ";
                                while (true) {
                                    switch (str33.hashCode() ^ 963481943) {
                                        case -1747241103:
                                            str33 = Utils.isEmulator(context) ? "ۡۤۘ۫ۡ۠ۤ۠ۢۦ۠۬ۙ۠ۡۡۘ۠ۘۛۛۢۜۘۡۘۗ۠ۘۦۨۨۘۖۥۨۘۙۚۤۛۢۦۘۖ۬ۡۘۢۤۖۘ" : "ۧۖۜ۟ۚ۟ۥۙۨۤۛ۟ۤ۠ۨۘۨۛۤۧۗۡۘۛۛۗ۬۠ۗ۠ۨۢۥۤۦۘۡۤ";
                                        case -1211022213:
                                            str32 = "ۙ۬ۡۘۧ۟ۨۜ۬ۛۥۦۨۘۖۡۘۘۜۨۘۖۘۨۘ۠۟ۖۘ۟ۦۨۘۡ۠ۤۚۧۘۘ۬ۥ۫ۚۖۜ۬ۗۡۘ";
                                            break;
                                        case 948592973:
                                            str33 = "۬۠۬ۤۛۤ۠ۚۦۜۤۥۜۤ۠ۜۛۜۘۗۙۨۗۛۡۘۡۖۜۘۨۡۘۥۤ۠ۧۥۘۘۙۖۘۚۤ۬";
                                        case 1680937750:
                                            str32 = "ۦۚۦۘۤۨۗۧۖۖ۬ۤۤۛۜۚۜۢ۬ۤۚۥۘۗ۠ۚۢۘۘۥۜۧۘۖۥۧۘۥۡۧ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    str = "۠ۡۥۘۙۖۤۨۛۚۘ۬ۛۧۡۧۛۜۘۘۘۘۢۘ۬ۚۘۚۦۘۦ۫ۛ";
                    break;
                case 1117488876:
                    ProxyApplication.webSocketClient = s3Var;
                    str = "۠ۡۛ۠۠ۥۢۧۙۡۗۨۘۙۛۙۗۢۘۘۨ۠ۗۘۢۙۢۖۧ۟۠ۘ۫ۦۘۛۦۦۥۤۚۘۜۧۦۧۘۘۙۡۨ";
                case 1163485481:
                    String str34 = "ۙۦۛۜ۠ۜۘۘۘۢۘ۫۠۫۫ۜۘۦۛ۠ۢۗۘۤۡۧۤۙۖۢ۠ۖ۬ۥۘۨ۫ۡۘۧ۠ۚۛۜۧۖۛۧۚۜۨ";
                    while (true) {
                        switch (str34.hashCode() ^ 296607298) {
                            case -1741118846:
                                str = "ۦ۬۠ۤ۫ۥۧۦۡۘ۟ۚ۫ۘۖۥۧۚۦۘ۫ۢۘۙ۬ۥۘۖۧۡۚۘۘۚۙۡۘۤۘ۬ۖۘۤۘۥۨۘ۟ۚۡۦۙۜ";
                                continue;
                            case -950557893:
                                str34 = "۠ۨۖۘۖ۟ۥ۬ۨۚۜ۫ۨۤۖۙۛ۠ۥۚۜۡۗۥۖ۟ۜ۠ۧۥۡۚ۟ۥۘۧۘۧۨ۟ۤۢۜۙۤۚۗۜۖۢ";
                                break;
                            case 1511776204:
                                String str35 = "ۙۖۤۦ۠ۙۜۨۖۥۨ۬ۤۥۜۖۥۡۘۧ۠۫ۘۛ۠ۜۖ۠۫ۥ۫۬ۦ۫ۥۙۡ۠ۘۖۙۙ";
                                while (true) {
                                    switch (str35.hashCode() ^ 1634726503) {
                                        case -1909443626:
                                            str35 = "ۜ۬ۨۨۢۚۘۗ۠ۛۘۘۘۙۗ۟۬ۥۦۘۨۙۗۙۡۡۥۡۘۜۖۖۘۖۖۚۗۖۤۚۘۘۜ۬ۨۘۗ۬ۚ۫ۖۜۛۖۘۤۦ";
                                        case -1677187276:
                                            str34 = "ۙۚۛۦۡ۠ۗۥۛۨۘۡ۫ۙۤۨۘۘۖ۬ۗ۠ۥۡۘۘۧۘۡۚ۠ۘۢۨۤۢۛ";
                                            break;
                                        case -1228866587:
                                            str35 = s0.isDebug() ? "۬ۘۧۛۥ۬۬ۧۦۨ۟ۖۧۚۢۘۜۦۛۢۘۘۛۗۘ۠ۜ۟ۤۙۙۨۙ۠۠۫ۦ۬ۡۦۡۥۨۘ" : "ۡۡۙۦۤۡ۠ۜۜۤۥۙۦۘ۫ۗۥۘ۫ۜ۠ۜۧۘۜۗۖ۫ۢۤۨۢۘۘ۫ۦۖۛۘۡۘ۬۠ۨۨ۫ۡۘۛ۟ۘۘ";
                                        case 404740167:
                                            str34 = "ۤۤۦۘۤۧۨۦ۟ۥ۬ۚۢۙۘۦۛۥۘ۬۫ۢۧ۠ۨۘۙۜۡۘۘ۠ۗۨۨ۬۠ۡۡۗۡۙۦۧۙۛۨۧۘۚۜۨ";
                                            break;
                                    }
                                }
                                break;
                            case 1583984970:
                                str = "ۙۤۜۘۛ۠ۦۘ۠۟ۢۡۗ۬ۢۛ۠ۜۖۤۤ۫۫ۛۧۡۢ۫ۜۧۛۡۘۢ۫ۨۢۥۡۘۨۗۖۘۙ۫ۗ";
                                continue;
                        }
                    }
                    break;
                case 1385073131:
                    System.exit(0);
                    str = "ۤۨۘۦۗۡۚۙۜۘۛ۟ۗۚۤۨۢ۫ۙۡۥۧۚۗ۟ۨ۫ۡۖۘ۫ۧۚۥۘۙ";
                case 1439334649:
                    String str36 = "ۗۨۡۚۖۥۙ۫ۥۘۡۧۧۗۚۨۘۨۙۥۛۜۧۘۛۦۡ۬ۛۖ۟ۗۦۘۥۥۥۨۨ۟ۢ۫ۢۨ۠ۖ";
                    while (true) {
                        switch (str36.hashCode() ^ 11466286) {
                            case -1065771574:
                                break;
                            case 294634077:
                                str36 = "ۗۢۨۧ۫ۖۤۙۜۛۛ۬۟ۛۘۘۘۛۙۤۙۖۘۧۡۖۗ۫ۥۚۤۧ۬۟ۡ۬ۘۘۥ۬ۥۛ۬ۘ";
                            case 350565796:
                                String str37 = "۠۬ۥۡۘۨۜۢۗۥۧۚۗۗۦۖۤ۬۠ۖۦۡۨۦۚۡۦۘۖۦۗ۟ۘۧۡۘۚۢۧۧۤۢۥۤۨۘۙۥۘ";
                                while (true) {
                                    switch (str37.hashCode() ^ (-1340509722)) {
                                        case -1567739196:
                                            str36 = "ۧۘۧۘۢۥۦۘۢ۫ۖ۟ۖۙۤۖۥۘ۟ۢۨۘۧۢۜۛۤۖۡ۠۠ۛۛۨۘ۟ۥۤۨۤ۬۟ۥۜۘۤۛۢۘۙۜۘ";
                                            break;
                                        case -1012555223:
                                            str36 = "ۚۖۥۘ۬ۗۖۧۦ۠ۖۗۤ۟ۦۜ۬ۡۥۗ۟ۛۥ۟ۧۧ۠۬ۚ۬ۥۘۘۢۢۜ۬ۛۦ۟ۜ۠۠ۚۡ۟ۚۤۚۧ";
                                            break;
                                        case 1724377020:
                                            str37 = !a ? "ۜ۫ۢۢۧۙۦۗۧۙۥۦۖۗۨۥۜۧۘۖۘۤۚۙۘۛۧۨۗ۠ۙۛ۠ۨ۠۬ۜۧۜۧۦۜۢۨۘ۬ۗۡۘۨۤۙۗ۬ۢ" : "ۗۦۘۘۘۡۖۘۚۜۘۢۢۙۚۙۛۤۚۚۢۙۢۤ۟ۙۡۢۨۖۜۧۘ۫ۜۛۧۥۘۘۘ۫ۢۛۚۛ";
                                        case 1933534552:
                                            str37 = "ۡ۟ۦۡۜۘۛ۟ۙۚ۟ۡۘۦۡۦۘۡۘ۫ۨۗ۟ۖۗۡۤۖۘۘۨ۫ۙۤ۠ۖۦۨۢۤۗۘۡۧۖ۠۟ۡۜۘۘ۫ۤۘۘ۬ۘۛ";
                                    }
                                }
                                break;
                            case 1173611670:
                                str = "۠ۨۜۘۜ۬ۦۛۛۧۡۧۘۤۖۦ۠ۥۜۘۙۡۦۘۜۗۨۗۚۙۛۥ۠ۛ۠ۡ۠ۥۤۤۡۜۘ۟ۤ۟ۘۘۙۧۛ۫";
                                break;
                        }
                    }
                    str = "ۤۥۙ۟ۨۗ۟ۨۨۘۨۛۛ۫ۛۨ۟ۘۜۗ۟ۗ۠۟۠ۢۖۧۙۤ۬ۦۙۢۜۗۘ";
                    break;
                case 1452718308:
                    str = "ۦ۠۟ۘ۟ۥۘ۠ۚ۠۟۠ۡۚۤۚۢ۠ۦ۟۟ۥ۟ۘ۫ۡۘۢۗۛ۠ۥۜ۠ۙ۠ۢۗۢۜۧۧ";
                    zOptBoolean3 = jsonResult.optBoolean("ban_Emulator", false);
                case 1579013167:
                    Utils.scanAndRemoveViewIfMatch(context2);
                    str = "ۖۗ۠۠ۤ۠ۨ۠ۖۨۙۥۘۡۘۛۘۗۥۤۙۚۨۥ۬ۧۨ۟۠ۡۦ۫ۘۖۦۛۙۚ۠ۘۦۧۘ۫ۦۢۦۨۘۦۦۢۦۜۥ";
                case 1650492425:
                    System.exit(0);
                    str = "۠ۡۥۘۙۖۤۨۛۚۘ۬ۛۧۡۧۛۜۘۘۘۘۢۘ۬ۚۘۚۦۘۦ۫ۛ";
                case 1740369740:
                    System.exit(0);
                    str = "ۥۢۜۨۚ۟۠۟۬۬ۥۦۘۦۥۙۢۡۢۧۖۗ۫ۙۡۧۢ۫۠ۧۗ۬ۗۧ۫ۛۦۖۘ۟ۧۖۥۤۘۘ۠۟ۨۤۡۨۦۥ۫";
                case 1755328470:
                    k2.k3zLJuvX(activityECt8jHZ4, "⚠️ 检测到 Xposed，操作已被禁止，由于在调试模式,本次不结束运行");
                    str = "۠ۤۥۘۚ۟ۦۘۜۗۛۖ۟ۢۥۗ۟ۛ۠ۢۖۥۘۚۙۧۛ۬ۘۘۥۨۧۘ";
                case 1911837654:
                    String str38 = "ۖ۬ۨۡ۬ۥۘۡۥۖۢۖۨۘۜۨۡۚۥۥۙۨۦۘۢۘ۬ۧۚ۫ۦۘۧۙۡۘۧۗۦۘۨۧۘۜۜۜۨ۠ۨۘۜۨۥ";
                    while (true) {
                        switch (str38.hashCode() ^ 165460324) {
                            case -2044376660:
                                str = "ۚۙۖۘۡۥۘۘ۬۬ۧۜ۬ۦ۫ۘۥۨۡۥۘۢۤۨۘ۬ۤۖۜۜۥۘ۬ۜۤۤۘ۫ۦ۟ۨ۬۠۟ۢۘۨۧ۬ۜ۬ۨ۬۫ۨۖۨۨۘ";
                                continue;
                            case -831804520:
                                str38 = "ۛۚ۫ۙۛۚۘۘۚۘۚۜ۫ۚۘۘۜ۠ۡۘۙۢ۟ۖۤۗۨ۫ۜۘۘ۟ۜۘۤۡۜۘۡۥ";
                                break;
                            case 644265095:
                                String str39 = "ۡ۠ۨۘ۟ۤۜ۬ۜۡۘۤ۫ۨۛۗۡۘۦۜۥۘۙۗۤۢۤۨۘ۫ۛ۠ۤۘۨۘۦۖۖۘۖۚ۠ۡۘۤۜۗ";
                                while (true) {
                                    switch (str39.hashCode() ^ (-1018933666)) {
                                        case -1984402398:
                                            str38 = "ۥۗۜۘۛ۬ۨ۠ۗۦۢۡۘۗۜۧ۬ۛۜ۠۫۠ۥۥۙۖ۫ۦۖۧۖۘۖۢ۟ۚ۟ۛ";
                                            break;
                                        case -1086554220:
                                            str38 = "ۤ۫ۢۨۥ۟ۖ۠ۤۦۥۜۘ۟ۧۖۦۘۥۘۤۡ۬۬ۨۛۙۗ۬ۦ۫ۤۧۦ۠ۢۤۖۜۥۨۘۨ۟ۙۤۚۚۥ۫ۜۦۛۥۨۚۜۘ";
                                            break;
                                        case -1055907881:
                                            str39 = "ۢۗۧۡۚۜۦۖ۟ۘۢۚۢۛۚۚ۫ۗۘۦۢۢۚۨۗۦۤۢ۬ۖ";
                                        case 97963766:
                                            str39 = jsonResult == null ? "ۧۡۜۜۘۙۢ۟ۦۘۜ۟ۖۡۤۚۗۢۦۘۥ۬ۜۛۧ۟۟ۤ۠۟ۤۖۘۥ۟ۨۜ۟ۜۘۡۜۧۢۙۥۘۦۨۧۦۚۦۘ" : "ۙ۟ۜ۫۠۠ۚ۬۠۟ۦۛۖۘۖۖۧۥۘۧۥۘۖۢۖۘۨۥۦۘۧۘ۫ۚۥۘۘۧۡۡۘ";
                                    }
                                }
                                break;
                            case 1671963457:
                                str = "ۙۙۢۜ۟ۨۘۢۗۜۘۥۖۘۚۛۛۡۛۡۥۘۘۚۢۢۢ۟ۡۘۙۖۡۘۗۗۖۘۘۡۦۚ۠ۘۘۚۦۨ۠ۧۡ۬ۜ";
                                continue;
                        }
                    }
                    break;
                case 1954443601:
                    k2.k3zLJuvX(activityECt8jHZ4, "⚠️ 当前处于双开应用环境，由于在调试模式,本次不结束运行");
                    str = "۠ۖۧۘۜۥۚۡۤ۠ۚۤۛۚۧۨۘ۫ۙۥۘۦۙۜۨ۬ۦ۠۠۠ۧ۫ۥۘۙ۬ۖۘۧۤۖۘ۠ۛۛۚۥۜ";
                case 2098724380:
                    String str40 = "۫ۙۨ۫ۨ۫۫ۚ۟ۢۚ۫ۢۙۦۙ۫ۘۘۜۡۢ۬۬ۖۘ۫ۗۢ۠ۢۜ۠ۘۦۖۤۡۘ";
                    while (true) {
                        switch (str40.hashCode() ^ (-310698869)) {
                            case -1913262602:
                                str = "ۤۚ۫ۢۨۛ۫ۨۗۚۙۖ۟۫ۨۢ۬۬۠ۜۘۗۙ۟ۦۡۘ۠ۚۦۘۡۨۤۥۡۡ۬ۙۥۧ۫ۨۜ۫ۙ۬ۜۜۡۜۤۡۢۡۘ";
                                continue;
                            case -124355750:
                                str40 = "۟ۦۡۗۛۥۘ۠ۡ۬ۘۘۥ۟ۡۜۤ۬ۦ۬ۜۗۡۡۘۖ۬ۧۨۥۤۗ۬۠۬ۖ۬ۦ۬ۢۦۨۤۥۛۖۗۥۘ";
                                break;
                            case 184710321:
                                String str41 = "۟ۢۨۚ۬ۡۘۚۦۜۙۧۜۛۤۛۥۧ۬ۦۡ۫ۘۚۜۘۛۧۦۧۙ۟ۖ۬ۗ۫ۦۨۗۨۧۘ۬ۧۡۘۤۜۗۢۤۦ";
                                while (true) {
                                    switch (str41.hashCode() ^ (-2048420318)) {
                                        case 325463681:
                                            str40 = "ۡ۫ۚۧ۠۫ۡۦۧۘ۠ۨۨۘ۬ۨۡۙ۟ۜ۟ۗ۫ۥ۠ۦ۠ۦۦۘۙ۟ۨ";
                                            break;
                                        case 776108000:
                                            str41 = s0.isDebug() ? "۠ۡۦۘۖۗۖۘ۠ۙۨۥۖۦۜۤۚۘۗۥۘۥۘۜۘ۠۫ۖۘ۫ۦۙ۟۬۠ۗ۠ۦۡ۬ۨۜۗ۫ۦۢۘۘ۟ۘۧۘۨۘ۟" : "۟۫ۨۘۛۤۡۘۤۤۤۘ۬ۨۘۖ۫۫ۗۤۨۜۙ۟ۥۨۚۦۡ۬ۘۡۘۚ۬ۜۘۛۛۘ۬ۦۢۨۧۡۨۜۦۗۛۨ";
                                        case 859680944:
                                            str40 = "ۤ۠ۦۘۨ۟ۤۚ۠ۦۘۙ۠۬۫ۧۦۘۨۘ۫ۘۛۗۚۦۦ۬ۘۦۧۥۘ۠۬ۛۥۜۨۘ";
                                            break;
                                        case 1194760560:
                                            str41 = "ۦۧۦۨۚۡۙۛۡ۟ۜۚۨۢۦۘۢۧ۟ۤۢ۫۟ۜۘۖۗۡۚۛۖۧۥۦۘۡ۠ۜۖۤ۠ۙ۫ۦۘۖ۫ۨۘ۫ۗ";
                                    }
                                }
                                break;
                            case 1409278874:
                                str = "۫ۡ۟ۚ۟ۥ۠۠ۖۖۤۖۘۜ۬ۜ۬ۢۗۨۥۨۘۜۢ۠ۨۖۘۤۛۢۨۡۜ۠ۖۜ";
                                continue;
                        }
                    }
                    break;
            }
            return;
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x0035. Please report as an issue. */
    public static void YSQScD6K(Activity activity, JSONObject jSONObject) {
        l lVar = null;
        int i = 0;
        ArrayList arrayListA = null;
        ArrayList arrayListA2 = null;
        int i2 = 0;
        ArrayList arrayListA3 = null;
        ArrayList arrayListA4 = null;
        String str = "ۡۥ۫ۨۦۚۤۘۧۘ۫ۛۥ۠۬ۘۘ۟ۖ۫ۖۚ۟ۚۤ۬۟۫ۘ";
        int i3 = 0;
        JSONObject jSONObjectOptJSONObject = null;
        int i4 = 0;
        JSONArray jSONArrayOptJSONArray = null;
        int i5 = 0;
        JSONObject jSONObjectOptJSONObject2 = null;
        int i6 = 0;
        JSONArray jSONArrayOptJSONArray2 = null;
        while (true) {
            switch ((((str.hashCode() ^ 237) ^ 554) ^ 263) ^ 1298780356) {
                case -2124747730:
                case -1060535215:
                    break;
                case -2122865770:
                    arrayListA4 = a(jSONObjectOptJSONObject.optJSONArray("black_list"));
                    str = "ۘۤۘۘۙۜۚۚۢۦۘۗۥۦۘۤ۬۟۟۬ۧۧۨۗۡۘۢۥۧۢۥۤۜۨ۫۫ۨ۟ۡۘۙۚۦ۬ۚۙۨ۫ۧۤۗۥۨۤۗۢۘۛ";
                case -2089481527:
                    String str2 = "۠ۧۚۡۧۨۖۗۙۨ۟ۤۗۥ۬۟۠ۦۜۙ۬ۖۘۗ۬ۧۜۘۡۡۦۘۧۜۙۨۘۚۥۥۘۨ۟ۛ";
                    while (true) {
                        switch (str2.hashCode() ^ 1011108439) {
                            case -307257669:
                                String str3 = "ۗۡۘۚ۠ۨۘۦۦۙۘ۫ۜۘ۬ۚۖۗۛۨۤۦۥۘ۫۟ۨۘۡۡۡۨۡۛ۟ۚۥۤ۟ۦۥۘۘۜۢۚۘ۫ۢ۟ۖۡۖۨۖ۫ۖ";
                                while (true) {
                                    switch (str3.hashCode() ^ 619329219) {
                                        case -2013939531:
                                            str2 = "ۨۘۦ۫۟ۥۘ۠ۥۥۘۧ۬ۗۥۦۨۘۛ۟۠ۚۧۚۡۨۗ۫ۤۡۘ۬ۤ۟۬ۛۥۘۛۥۧۘ۫ۦ۬ۛۧۜۜۨۜۗۖ";
                                            break;
                                        case -585161143:
                                            str3 = i4 < jSONArrayOptJSONArray.length() ? "ۛۖۘۥۗۘ۫ۜۛۛۗ۫ۨ۟ۨۡ۫ۚ۠ۖۤ۬ۤۘۛ۠ۘۘۨۧۦۘ۬ۧ۟ۥۘۦۘۛۗۨۦۥۤۙۖ۠ۗۖۜۚ۠ۖۧۘۜۘ" : "۬ۤۥ۬ۧۖۘۛۗۖۘۛۨۡۘۖۥۡۘۦۥۨۘۦ۬ۚ۬ۜۧۘ۠۟۠ۢۧۙۥۧۢۤۦۤۜۦۜۘ۠ۖۛ";
                                        case 1317385499:
                                            str2 = "ۗۨۦۘۡۛۥۥۤۦۡۘۦۘۚۧۙۖۨۡ۬۠ۖۤۚۨۘۧۤۙۖۚ۟ۤۚۜۘۗۥۜۘ";
                                            break;
                                        case 1933112053:
                                            str3 = "۬۫ۦۤۛۖۘ۟ۤۚ۫ۧۜۘۖۚۤ۫۠ۖ۫۠۬۫ۡۦۘۡۖۢۙۤ۟";
                                    }
                                }
                                break;
                            case 118711162:
                                break;
                            case 948513587:
                                str = "ۢۦۥۘۦۡۦۘۦۖۖۜ۟ۥۥۖۧۘ۫ۧۖۡ۬ۡۛۗۦۧۛۜۢۚۛ";
                                break;
                            case 1175902639:
                                str2 = "ۙۗۘۦ۫۠ۥۙ۬ۜۛۢۨ۟۫۠ۧۦۘۢ۠ۜۘۘۙۥۡۢۛۢۗۡ۫ۢۘۘۙۘۛۜۦۜۘۜۡۖۘ۟۬۠ۡۗۖ";
                        }
                    }
                    str = "۬ۛۥ۟ۘ۫ۘۤۧۤۨۤۥۦ۫ۦۜۢۜۥۘۚۖۥۤۤۛ۬ۨ۬ۢۚۥۘۘۙۥۦ۟ۜۘۚ۟ۜۚۧۚۛۛ";
                    break;
                case -2063739156:
                    str = "ۧ۟ۢۙۖۖۘۤ۫۬۫ۛۖۤۨ۫۬ۡۥۦۜۥۡۤۢۤۤ۟ۚۘۧۧۨۥۧۚۥۘۥۧۦ";
                case -2033780236:
                    String str4 = "۟ۜۢۖۡۘۚۥۧۘ۠ۤۡ۬ۡۚۘۗۦۜۛۦۤۗ۠ۘۡ۠۟ۨۘۤ۠ۘۖۤۦۘۦۥۦۘۚۦۙۢۘۘۘۦۜۖ۫ۚۦۦ۫";
                    while (true) {
                        switch (str4.hashCode() ^ 54673291) {
                            case -1042860928:
                                String str5 = "ۦ۫ۙۛۜ۟ۛۤۖۘ۟ۜۨۡۨۨۘۢ۠ۦۘۥۚۚ۫۬ۡۘۘۗ۬ۙۘۥ";
                                while (true) {
                                    switch (str5.hashCode() ^ (-1533702966)) {
                                        case -1754290189:
                                            str5 = jSONArrayOptJSONArray != null ? "ۨۢۡۘ۠ۘۘۦۚۥۙۙۖۨ۫۠ۨۙۗۢۘۦۚۡۦۥ۠ۛۖ۬ۙ۫ۜۨ۟ۛۙۚۖۢ۫ۘ۟ۚۥۘۘ۟ۢ۫" : "ۧ۫ۜۥۢۨۤۜۘ۬۫ۥۗۘ۟ۘۧۙۛ۬ۖۘۨ۠۠ۚ۟۬ۡ۬ۘ";
                                        case -852697610:
                                            str5 = "ۚۛ۫ۡ۟ۗۡ۠۟ۗ۫ۥۘۗۗۘۦۙۦۡۡۥۘۖۨۘۘ۬ۤ۟۠۠ۜۘ۠ۜۨۚۛۙۡۜۧۥۨۜ";
                                        case -498081668:
                                            str4 = "ۚۗ۫ۜۥۘ۫ۘۦۘ۬ۚۨۜۥۜۘ۫ۢ۠ۧۤۤۛ۬ۙۦۜۧۖۥ۬ۥۡۗۚۛۦۘۡۧۗۥۛۥۘۛ۟ۖۘۗ۬ۜ";
                                            break;
                                        case 1258445166:
                                            str4 = "ۙۘۨۘۖۘۨۘ۠ۨ۠ۢۘۢۗۗۖۘ۬ۗۙۧ۠۟ۛۚۚۗۙۖۛۥۡ۫ۚۥۘ۬ۖۧ";
                                            break;
                                    }
                                }
                                break;
                            case -807699810:
                                str = "ۚۙ۫ۢ۠ۜۘۚۘۜ۟ۥۖ۫ۨۖۘۢۨۨۘۥ۬۠ۜۢۙۥۧ۠ۗ۫ۢ۬ۡۡۜۜۙۦ۟۫۠ۜۘ";
                                break;
                            case -644148171:
                                break;
                            case -146819513:
                                str4 = "۟۠۬۠ۢۖۛۡۡۘۡۘ۠ۡۥۜۘۧ۫ۦۘۡ۠ۤۛۛۥۘۤ۫۟ۚ۟ۡۘ";
                        }
                    }
                    str = "۬ۛۥ۟ۘ۫ۘۤۧۤۨۤۥۦ۫ۦۜۢۜۥۘۚۖۥۤۤۛ۬ۨ۬ۢۚۥۘۘۙۥۦ۟ۜۘۚ۟ۜۚۧۚۛۛ";
                    break;
                case -2023186232:
                    str = "۬ۛۥ۟ۘ۫ۘۤۧۤۨۤۥۦ۫ۦۜۢۜۥۘۚۖۥۤۤۛ۬ۨ۬ۢۚۥۘۘۙۥۦ۟ۜۘۚ۟ۜۚۧۚۛۛ";
                case -1772059016:
                    String str6 = "۟ۢۘۘۜۦۚۦۥۘۨۦۛۨۧ۫ۡ۠۫ۚۧۚۤۢۛۢۖۡۤ۟۠ۢ۠ۙۖ۫ۖۘۧۧۨۡۜۙۙۘۚۤۚۧ";
                    while (true) {
                        switch (str6.hashCode() ^ (-1546615025)) {
                            case -2068030350:
                                str = "ۙۜۛۧۢۙۖۦ۬ۢۜۘ۫ۚۦ۬ۥۚۜ۫۠ۧۛۡۘۡۥۗۨۘۦۥۥ۫ۥۧۥۘۤۥۛۥۨۦۘ";
                                continue;
                            case -237849174:
                                String str7 = "ۗۚۜۨۙ۟ۗۡ۠ۚۘۢۛۧ۟ۚۙۥۘۡۥۨۛۥۗۗۛ۬ۙۧۖۚۛۜۘۗۙۜ";
                                while (true) {
                                    switch (str7.hashCode() ^ 2019128710) {
                                        case -358911148:
                                            str7 = jSONObject.optBoolean("screen_priority", false) ? "ۙۤۜۗ۬ۧۛۢۦۗۖۘۨۢ۫ۦ۫ۗۘۥۦۤ۟ۖۦۘۨۥۜۘۗۤۥۘۙۨۘۜۖۦۜ۟۟" : "ۨۘۦۘ۠۬۫ۤۜۛ۫ۜۧۡۘۘۘۤۡۦ۫ۙۘۘۚ۟ۤۧۖ۟ۦۥۘ";
                                        case 848442454:
                                            str7 = "ۚۖۢۤۦۘۡۘ۠ۥۨۘۘۚۡۘۦۧۜۘ۫ۚۗ۬ۨۘۧۖۜۘۡ۫ۖۦ۠ۨۘۢۡۘۘ";
                                        case 1116168674:
                                            str6 = "ۢ۬ۖۘۚۖۥۗۚ۠۟ۜۖۧۧۤۙ۬ۖ۬ۧۡ۬ۧۜۘ۟۬۫ۨۨۘۧۜۜۘۘۙ۟ۘۤۨۦۘۨۘۡۥۨۗۛ۬";
                                            break;
                                        case 1637972126:
                                            str6 = "ۗۥۦۘۤۗۡۛ۫ۖۜ۬ۘۚۡۜۛۖۘۤۖۜۘۥۘۨ۬ۜۤ۟ۦۤۧۚ۟ۥۜۧۘۥۙۥ۟ۘۖ۠ۗۦۨۧۡۘ";
                                            break;
                                    }
                                }
                                break;
                            case 1852123506:
                                str = "ۡۚۡۖۙۥۘ۫ۢۖۘۙۡۗۗۨۧۘۘۖۨۘۙۤۜ۬ۢۙۥۚ۫ۤۢۥۘۧۢۜ۬ۛۨۘ۟ۥۜۢ۬ۡ";
                                continue;
                            case 1970487963:
                                str6 = "ۗۖۦۘۢۘۤۖ۟ۘۘۡۥۦۢۡۜۘۙ۫ۤۦ۬ۤۦۘۖ۬۬ۨۘۛۢۡۘ۬ۨۦۤۢۘ۠۬ۥۜ۟";
                                break;
                        }
                    }
                    break;
                case -1554035954:
                    str = "۟ۡۨۘۤ۫ۖ۟ۢۨۘ۟۬ۦۙۘۡۜۧۛۥۖ۫ۢۖۙۢ۬ۡۡۘۗ";
                    i6 = i5;
                case -1389668474:
                    String str8 = "ۘ۬ۗۦۦۤۜۢ۫۬ۚۧ۟ۦۡۢۥۧۘۚ۫ۤ۬ۡۢۗ۬ۡۘ۬ۛۗۖ۠۟ۦ۬ۗ";
                    while (true) {
                        switch (str8.hashCode() ^ 1637098828) {
                            case -764921781:
                                break;
                            case -515799613:
                                str = "ۜۘۦۖ۫۫ۨ۬ۘۘۛۖۖ۫ۢۨۘۗۛۨۘ۫ۙۥۡۜۦ۬ۛ۟ۥۥۧۙۛۜۦۧ۠";
                                break;
                            case 370925516:
                                str8 = "ۗ۬ۜۘ۫ۨۦۥۗۛۙۖۢۛ۠ۢۖۙۘۘۙۧ۬ۛۙۘۚ۫ۤۚۜۙ۫ۗ۟ۜ۬۫ۦ۫ۦۘۦۡۘۘۧۡۘ۫۫ۦۘ۫ۛۨۙۛ۟";
                            case 1123728233:
                                String str9 = "ۛۙۖۢۗ۠۬ۗۨۘۜۚۘۢۘۦۘۜۨۛ۠۬ۡۘۢۧۥ۠ۦۛ۟ۜ۫ۨۦۡ۠۟ۡۘۜۜۖۢۧۦۜ۫ۜۢۢ";
                                while (true) {
                                    switch (str9.hashCode() ^ 680594559) {
                                        case -1956855992:
                                            str8 = "ۡۖۥۛۨ۫ۘ۬ۤۗۚۛ۬ۤۧ۟ۢۥۘۨۗۦۨۛۦ۟۬ۤۗ۫ۙۙۛۨۙۚۥۘۚ۟ۖۨۧۘ۟ۦۥۥۛ۫";
                                            break;
                                        case -1737746952:
                                            str9 = "ۛ۟۬ۙۖۖ۟ۧ۠ۤۗ۟ۡۤۢۖۦ۬ۨۛۡۘۧۜۡۢۡۘۜۥۦۖۜۡ۬ۥۨۖۥۚۘۢۨ۟ۚۦۘ۠ۤ۟ۧۦۧۘۡۖ۟";
                                        case 1405021915:
                                            str9 = i6 < jSONArrayOptJSONArray2.length() ? "۬ۥۨۦۢۜۜۜۦۜۖۛۚ۟۫ۧ۬ۜۢ۫ۡۘۛۢۜۤ۬ۥۘۛ۟ۨۘ" : "ۗۖۨۘۖۦۛۙ۠ۖۘۡ۟ۖۘۘۢۦۘۥۗۡۜۤۦۡۥۘ۫ۗۖۘ۫ۚۨ۠ۘۨۘۚۜ۬ۡۡۘ۫ۘ۠";
                                        case 1483652905:
                                            str8 = "ۤ۬ۙ۬ۡۛۨ۫ۜ۬۫ۘ۬ۦۡۘۧۦۘ۫ۤۜۢۜۧۘ۠۟ۨ۫ۨۜۘ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    str = "۬۬ۢۦۧۖۦۜۥۙۜۖۘۢۨۖۘۙۛۥۘۗ۠ۥۛۤۧۧۧۤۨۧۨۜۥ۬ۛۖ۫";
                    break;
                case -1334030382:
                    str = "۬ۢۧۢ۟ۖۨۡۚ۬۟ۨۚۥۤ۬ۖ۫ۥۚۥۘۜ۬ۜۨۘۦۘۙۖۦۘ۠ۧۡۢ۬ۘۚۜۗۗۦ۟ۜۢ۠";
                    jSONObjectOptJSONObject2 = jSONArrayOptJSONArray2.optJSONObject(i6);
                case -1155578780:
                    String str10 = "۬ۧۘ۫ۛۦۚۧۚۧۤۜ۠ۦۡۚ۬۬ۙۙۡۥۚۥ۬ۢ۬ۤۙۙ";
                    while (true) {
                        switch (str10.hashCode() ^ 733790382) {
                            case -1754613131:
                                str = "ۗۧۗ۠ۚۜۘۘۘۘۙۧ۬ۖۡۡۨ۫۫ۥۙۡۚۘۡۘۦۘ۬ۦۚۚۘۘۤۧۡۘۧۧۚۢ";
                                break;
                            case -625209949:
                                str10 = "۬ۖۥۨۧۙۡۛۜۢۥۗۨۤ۟ۡۥۖ۬ۖۜۘۛۢۥۦۜ۬ۢۚۜۘۖۦۗ۫ۛۨۘۧۡۨۦۗۥ";
                            case 1193041419:
                                break;
                            case 1911396457:
                                String str11 = "ۜۡۖۘۡۖ۟۟ۢۤ۫ۖۦۜ۫ۘۥ۬ۦۙۖۙۚۖۘ۬ۨۚ۫ۜ۟ۡۖۘۘۗۗۜۨۖۙۧۙ";
                                while (true) {
                                    switch (str11.hashCode() ^ (-358652362)) {
                                        case -1428362892:
                                            str10 = "ۜۤۦۘۜ۠۠ۗۘ۠ۖۡۦۘۦۗۖۘۡۛۘ۟۠ۖۗ۫۟۫ۢۤۚۦ۫ۘۧۢۚۨ۟ۡۘ۠ۥۘ";
                                            break;
                                        case -1319490319:
                                            str10 = "ۜۢۛۜۖ۬ۤ۫ۛۡ۠ۧۦۦۜۢۚ۫ۘۧۘۜۨۥۘۛۤۦۥۚۚ۫۫ۢۧ";
                                            break;
                                        case -659610027:
                                            str11 = "ۢۤۜۗۖۥۘۖۥۛۢۜۖۘۤ۬ۤۜۘۧۘۜۨۖۧ۠ۥۢۡۦۘ۫ۛۥۚۚۜۘۡۥۥۘۜۥۘۖۘۤۜۜۧۘۢۛ۬";
                                        case 17364367:
                                            str11 = jSONObjectOptJSONObject.optBoolean("enable", false) ? "ۡ۠ۨۘ۬۟ۘۖۛۚۤ۟ۙۚ۟۠ۘۗ۠ۛ۫ۗۥۡۜ۟ۛۧۦ۫ۙۨۚۦۘ۬ۗۡۘ۫ۘۦۧ۠ۨ" : "۫۫ۦ۟۫ۘۘۦۢۧۗۧۖۛۡۦۘۨۡۢۚۥۜۖۥۜۦۘۙۢۧۘ۠ۥۘۘۜۖۨۘۛۨۡۜۘۘۛۚۜۗۡ۫ۛۗۛۘۘۚ";
                                    }
                                }
                                break;
                        }
                    }
                    str = "ۦۨۙۘ۬ۡۘۘۜ۬ۥۢۜۘۤ۫۠ۛۙۜۘ۠ۜۘۡۡۜۘۚۤۦۘۢ";
                    break;
                case -1018982269:
                    lVar = new l(activity, jSONObject, 6);
                    str = "ۘ۫۫ۥ۫ۨۛۘۥۘۡۚۦۘۧۚۦ۠ۡۧۖ۫ۨۘۧۦۜۘۥۜۧۢۛ۠ۢۗۖۘۙۘۡ";
                case -874575467:
                    str = "۠ۦۘۘۚۗۘۘ۟ۦۧۘۦۙ۠ۤ۠ۦۜۖ۫ۡۧ۠ۜۛۘۖۖۘۚۡۦۘۖۚۦۘ۬ۦۡۨۨۘۘۡۤۨۘ۟ۘۘۤ۠";
                    jSONArrayOptJSONArray = jSONObject.optJSONArray("popups");
                case -840078146:
                    jSONObjectOptJSONObject.optString("id", "");
                    str = "۬ۢۙ۠ۗۘۘ۠۬ۦۘ۟ۖۥ۫ۧۧۨ۟ۗۨ۫۫ۨۘۜۙۚ۫۠ۛۨۜ۫۠ۜۡۤۥۤۡۢۧۖۘ";
                case -787377909:
                    String str12 = "ۘۜۥۘ۬ۧۦۙ۬ۡۘۦۥۧۡۖۘۚ۫ۥۘ۫ۨۖۢۨۡۘۗ۬ۨۙۜۧ۟ۢۦۘ۠۠ۦ";
                    while (true) {
                        switch (str12.hashCode() ^ (-1771169565)) {
                            case -1755222108:
                                String str13 = "ۘۨۜۘۦ۟ۤۨۜۦ۠ۖۖۘۧۖۛۤۘۤۥۨۦۘۡۚۨۘۗۡۖۧۧۤ";
                                while (true) {
                                    switch (str13.hashCode() ^ 858867883) {
                                        case -800435218:
                                            str13 = jSONObjectOptJSONObject2.optBoolean("enable", false) ? "ۘۚۡۘۚۨۜ۠ۗۨۗۗۡۘ۫ۜ۟ۨ۠ۙۦۚۘۘۚۜۡۘۢۘۛۙ۠ۧۢ۬ۖۘ۟ۥۘۦۖۥۢۖۤ" : "ۖۗۡۧۜۧۜۧ۬ۜۧ۟ۖۧۗۥۛۜۘۚ۠ۜۘۡ۫ۜ۫ۢۗۡۢۨۘۗ۫ۘۘۢۜۧۘ";
                                        case -243464233:
                                            str12 = "۠ۦۢۘۦ۠۠ۥۖۚۥۚۦ۬۫ۥۙ۠ۖۘۘۥۛۡۘ۠ۙۥۦ۬ۛۖۜۗۦۜۖۗۨ۟۫ۜۤۜۙۥۘۥۨۡۘ";
                                            break;
                                        case 41172600:
                                            str12 = "ۤۤۡۘ۠ۖ۠۠۫ۤ۟۫ۦۘۘۥۥۘۤۢۦۢۜۘۘۧۢ۫۫۬ۗ۠ۥۨ۫ۖۥۘۧۜ۫ۗۡۧۛۦۚۛۦۨ۬ۤۤ";
                                            break;
                                        case 1319315256:
                                            str13 = "ۖۗۜۘۢۢۘۛۤۛۦۛ۟ۙۚۥ۠ۢۖ۠۫۟ۡۨۧۢۨۘۤۡۜ";
                                    }
                                }
                                break;
                            case -244107316:
                                str12 = "ۛۧۡ۬ۘۦۘ۟ۗ۟ۢۜۜۛۢۦۙ۟ۘۘۗۧۚۢۛۨۘۨۜۜۘۛۘ۟ۗۤۖۘۖۨ۠۟ۥۚۜۡ۬ۤ۠ۦۢۡ";
                            case 1498719449:
                                break;
                            case 1503480174:
                                str = "ۡۚۦۘۨۘۥۡ۬ۡۥۤ۟ۨۤ۟ۡۙۥ۬۬ۥۘۜۨۖۢ۟ۤۚۦ۟۟ۘۚ۬۠ۘۘۢۛۨۘۚۚۖۘۥۥ۠ۧ۬";
                                break;
                        }
                    }
                    str = "۬۬ۖۘۙۥۦۙۧ۠ۥ۬ۚۘۘۗۗۘۘ۬۬۫ۧۧۥۗۨ۬ۧۙۘۙۨۡۘۜ۟ۦۘۥۥۥۘۙۨۧۘ۟ۤۥۘ۠ۚۖۘ۫ۡۦۚۢۛ";
                    break;
                case -452856939:
                    String str14 = "۟ۗۜۘۧۙۘ۫۬ۜۙۢ۬ۜۛ۠۬ۖۢۨۛ۟۬ۖ۟ۗ۟۟ۤۦۥۘۨۖۥۘ۫ۧۦۘۦۙۨۨۤۗ";
                    while (true) {
                        switch (str14.hashCode() ^ (-1513441502)) {
                            case -445188724:
                                str14 = "۬ۘۙۦۘۛ۠ۡۡ۠ۙۥۘ۠ۦۙۡۦۦۙۖۦۘۥۛۡۘۜۡ۠۫ۨ۠ۥ۟ۦۤ۫ۧۗ۠ۜۘۢۤ۠ۡ۠ۥۘۛۤۖۘ";
                            case 672929922:
                                str = "ۥۚۚۥۤۖۘ۟ۢۗۗ۬ۜۘۖۜۖۘۢۙۨۘۗۧۜۘۤۖ۫ۤۡۚۗۜ۟ۤۚۜۤۗۥۘ";
                                break;
                            case 679014725:
                                String str15 = "ۘۦۧۢ۫ۛ۠ۤ۟ۜۜ۫۬ۗۨۜۧ۟ۨ۫ۨۜۡۘۚ۟ۦۘ۫۬ۙۙۢۨۘۡۦۙۙۘۙۗ۫ۖۦۘۦۦۢ";
                                while (true) {
                                    switch (str15.hashCode() ^ 84064374) {
                                        case -1922965028:
                                            str14 = "ۧۛۦۘ۟۠ۗۤۗۦۡۦۘۘۚۛۨۚۗۨۧ۫ۗۨۤۧۢ۟ۘۘۡ۠ۜۘ۟ۘۜۗۢ۬";
                                            break;
                                        case -1586483612:
                                            str14 = "۠ۧۨ۫ۡۨۘۘ۟ۙ۫ۢۙۡ۬ۧۘۧۧۥۘۘۛۥۜۚۢۖۚۦۚۘۨۡۘۘۗۚۖۛ۬ۨۧۘۨۚۚۖۦ۠";
                                            break;
                                        case -1561470986:
                                            str15 = jSONArrayOptJSONArray2.length() > 0 ? "ۢۨۨۜ۬ۤۥۙۡۚۥۗۤۥۨۘ۫ۜۧۗۢۗۨۨۡۛۥۨۘۗۨ۫ۦۘۜۚۖۖۘۗ۬۫ۙۤ۫ۘ۬ۛۘۗۚ" : "ۚۧۗۛۗۚۨۙۡۘ۬ۛۡۖۚۙۖۢۜۘۜۘۢۢۥۨۚۙۙۜۗۙ۬ۜۦۚۚۖ";
                                        case 847080025:
                                            str15 = "ۨۨۘۙۢۡۘۧۦ۠ۦۦ۫۬ۖۗۥۨ۟ۜۨۢ۫ۡ۫ۘۦۘۡۙۦ۠ۙۘۘۥۤ۟ۗۜ۬ۙۦۧۘ";
                                    }
                                }
                                break;
                            case 1457856376:
                                break;
                        }
                    }
                    str = "۬۬ۢۦۧۖۦۜۥۙۜۖۘۢۨۖۘۙۛۥۘۗ۠ۥۛۤۧۧۧۤۨۧۨۜۥ۬ۛۖ۫";
                    break;
                case -405604013:
                    String str16 = "ۗۘۖۘۤ۟ۚۜۖۨۘۧ۠ۧۛۧۘۘ۠ۦۙۗۧۦۘۦۨۖۛ۫ۘۜۦۢۧۡۖۘۥۘ۫ۙ۠ۡۘۗۖۧۘ";
                    while (true) {
                        switch (str16.hashCode() ^ 1276027861) {
                            case -1193144686:
                                String str17 = "ۥۡۨۘۢ۠ۜۡ۠ۜۘ۠ۜۧۘۗۧۘۘۘۤۛۙۛ۬ۗۖۚ۬ۡۗ۟ۘۡ";
                                while (true) {
                                    switch (str17.hashCode() ^ (-1204207546)) {
                                        case -1266783255:
                                            str16 = "۬ۤۨۘۚۜۜۘ۟۟ۘۘۧۡۦ۠۬ۘۗۦۘۖۜۢ۟ۚۖۘ۬ۥۥۚۖ۟ۚۤۙۚۤۖۘ";
                                            break;
                                        case 802936730:
                                            str17 = jSONArrayOptJSONArray2 != null ? "ۥۧۨۢۙۜۘۖ۠ۧۥۛ۬ۖ۫ۨۥۥۦۧۚۡۤۛۗ۬ۖ۫ۨۧۨۘ۟۬ۙ۬ۤۨۘۚ۬ۥۘۘۚ" : "ۘ۫ۚۦۨۡۜ۠ۖۦۖ۬ۗۤۨۡۢۤۥۥ۬۫ۤ۫۠ۗ۫ۜۗۨۦۚۢۛ۫ۦۨۜۘ۫ۘۨ";
                                        case 868948548:
                                            str17 = "۬۟ۡۘۡۚۖۘۦۦۚۘۘۧۘۤۡۗۗۥۜۙۛ۫ۡۜۗۜ۫ۜۢۚۨ۟۠ۚۛۘۗۗ۠ۤۜۡ";
                                        case 2053783790:
                                            str16 = "ۗ۫۟ۗۡ۬ۚۘۧۚۙ۬ۘ۠ۤۢۗۗۗۛۥۘۙۘۘۘۜۨۜۘ۫۬ۡۘۢۚۡۘۚ۬ۥۘۥۙۥۘۡۗۗۦ۫ۨۗۨۥ";
                                            break;
                                    }
                                }
                                break;
                            case 1128039157:
                                break;
                            case 1796949275:
                                str16 = "ۥۧۦۘۜۚۛۥۥۖ۫ۖۜۘۦۥۚۗۖۙۜۘۡ۟ۡۘۗ۬ۙۘ۫ۦۚۙ۠ۨۚۨۘ۟۫ۗۙۨ۬ۥ۬ۘۘۥۦ۠ۢۖۥ۫ۧ۟";
                            case 1951080829:
                                str = "ۜ۫ۘۚۖۧۦۙۡۛۚۧۨۦۥۥ۬۫ۡۜۙۤ۠ۙۧۤۦۨۛۡۖۗۥۘۜۥ۟ۥۙۙۡۡۢ۟ۖۘۜۦۚ";
                                break;
                        }
                    }
                    str = "۬۬ۢۦۧۖۦۜۥۙۜۖۘۢۨۖۘۙۛۥۘۗ۠ۥۛۤۧۧۧۤۨۧۨۜۥ۬ۛۖ۫";
                    break;
                case -258140042:
                    String str18 = "ۨۤۥۘۧۥۚۚۨۢۚۥۘۤۢۖۘۜۢۚۦۤ۟ۘۢۨۘ۬ۡۘۡ۫ۘۙۥۧۛۧۚۥ۠ۧ۠ۛۤ";
                    while (true) {
                        switch (str18.hashCode() ^ (-1960995422)) {
                            case -1250145627:
                                str = "ۚۡۡۘۛۛۤۨۢۘۘ۠۬ۘۤ۫۟ۖ۫ۜۘۦۗۨۘ۬ۛۡۜۡۥۘۗۧۜۘ۬ۜۡۘۘۧۥۨ۫ۥۘ۫ۥۧ";
                                break;
                            case -1046730254:
                                break;
                            case 1279849559:
                                str18 = "۟ۖۦۘ۟ۙۡۜ۠ۦۢۡۖۘۚۥۖۧۚ۬ۡۜۜۛۨۧۧۡۥۡ۠ۥ";
                            case 1495486986:
                                String str19 = "ۥۜۢۚۖۢۜۧ۠ۚۗ۠ۡ۬۫۟ۜۨۘ۟ۘۤۥۗۧۛۧۖۤۧۤۛۛۥۧۜۡۥۢۙۛۢۧ";
                                while (true) {
                                    switch (str19.hashCode() ^ (-593699852)) {
                                        case -1318107964:
                                            str19 = jSONObjectOptJSONObject != null ? "ۚ۬ۦۥۧۗۧ۠ۖۘۚۛۖۡۨۤۥۛۥ۟ۚۡۜۧۥۘۜۙ۠۟۬ۙۢۜۛۡۜۙۥۡۜۘۥۛۤ۬ۡۨۘ۫۬ۥ" : "ۡۦۨۤۖۖۘ۫۟۬ۢۤۤۙۥۗۤۗۛۖۨۘۙۡۜۥۨۨۖ۫ۡۙۡ۬۬ۖۤۘۡۡۘۥ۠ۧ";
                                        case -844172873:
                                            str18 = "ۚ۠ۡۘۚۧ۟۬ۡۧۘ۫ۙۖ۬۟۟۬۬ۙۧۘ۬ۧۨۗۡۧ۟ۛۖۘۗۢۧۤۚۡۧ۠ۖۢ۫ۤۜ۟ۦ۫ۚۡۘۨ۫ۥۘۥۜۘۘ";
                                            break;
                                        case -449874180:
                                            str19 = "ۙ۠ۡۘۖۡۧۡۗ۟ۜ۟ۙ۫ۙۡۧۜۘۦۛۗ۫۫ۘۘ۬ۦۦۘۥۗۦ۫ۡۛۖۚۨۚۢۜۡۤۨۘ";
                                        case 1495360723:
                                            str18 = "ۦ۟ۥۘۡ۠ۨۘۚ۬۬ۚۧۢۨۤ۫ۗۚۘۘۖ۟ۡۛ۠ۜۘۥ۟ۚۥۢۤۘۖ۠ۥۗۚۧۚۤۡۦۦۘ۠ۨۛۦۨ۬";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case -63526282:
                    k2.otTlU4Xc(activity, jSONObjectOptJSONObject2.optString("id", ""), jSONObjectOptJSONObject2.optString("imageUrl"), jSONObjectOptJSONObject2.optInt("clickAction", 0), jSONObjectOptJSONObject2.optString("clickText", ""), jSONObjectOptJSONObject2.optString("callback", ""), jSONObjectOptJSONObject2.optInt("countdown", 5), jSONObjectOptJSONObject2.optBoolean("canSkip", true), jSONObjectOptJSONObject2.optBoolean("autoClose", false), jSONObjectOptJSONObject2.optBoolean("lock", false), arrayListA, arrayListA2, lVar);
                    str = "۟ۙۚۧۙۥۖ۫۬ۨ۫ۙۥ۬ۚۢۦ۠ۛۧۙۜۧۥۗۜۘۨ۟ۗ";
                case -31596106:
                    new Handler(Looper.getMainLooper()).postDelayed(new a(18), 500L);
                    str = "ۖۦۥۘۦ۟ۢۧۦۗۢۤۚ۫ۚ۠۟ۡۘۘۤۘۘۢۚۜۘۚۧ۟ۨ۫ۘۘ";
                case 217666718:
                    String str20 = "ۤۥۡۤ۠ۙۢۖ۠ۗۤۤۧۨ۟ۙۘۥۚۚ۠ۛ۠ۢۥۘۧۘۖۥۜ۟۠ۤۘۡۥۛۛۖۘۙ۫ۥ۟ۨۦۙۜۘۙۧ۬ۨۘۧ";
                    while (true) {
                        switch (str20.hashCode() ^ (-658142972)) {
                            case -2026203519:
                                str20 = "ۚۚۨ۬ۨۜۘۦ۫ۖۘۖۚۛۡ۟ۡۢۤۖۖۧۥۘۡۖۨۘۛۢ۫ۖۦۗۥۧۛۦۛۢۦۗۘۘۡۚ۬";
                            case 671003269:
                                str = "ۙۥۡۘ۟ۛۜۡۜۡۛۦۗۚ۫ۡۚۨۘۦۤۗۙۘۛ۫ۦۨۨ۠ۨۘۖ۫۬ۚۤۛ";
                                break;
                            case 797408297:
                                String str21 = "ۨۢۜۘ۫ۚۡ۟ۜۡ۬۫ۡۥ۟۬ۘۥۢۨۖۡۧۤۥۧۙۘ۟ۤۥ";
                                while (true) {
                                    switch (str21.hashCode() ^ (-1422198631)) {
                                        case -1691547010:
                                            str20 = "ۡۨۜۜ۫ۖۙ۟ۨۘۥ۬ۚۡ۠ۦۘۘۢۥۙ۠ۥۡۡۨۘۛۘۢ۫ۖۜ۫ۨۙۧۖۘۢۙۨۨۖۘۨۢ۫ۦۤ۟";
                                            break;
                                        case -807084043:
                                            str21 = jSONObjectOptJSONObject2 != null ? "ۧ۟ۦۘ۬ۡۤۛ۫۟ۦۢۦۜۡ۬ۖۘۤۚۥ۠۟ۤۥۥۤۧۥۗ۬ۡۖۘۘۧۖۦۥۖ۫ۛ۫ۦۦۤۢۖۥۡ" : "ۨۛۖۘ۟ۦۜۨۧۘۜۘۛۙۤۙۜۗۦۘۜۦۥ۬ۛۘۘۘۧۜۛۙۦۚۨۤ۫ۛۨۖۤۗ۫ۛۚۥۡۘۦۙۢ۟۫ۨۘۢۚۡ";
                                        case -744168219:
                                            str20 = "ۗۡۧۖۡۘۗۤۜۘۘۙۨ۟ۜۨۡۦۦۘۚۚۖ۬ۥۗۤۢۖۚ۫ۘ۫ۜۖۘۗ۠ۤ۫۬۟ۥۜۜۘۧۚ۬ۤ۟ۥ";
                                            break;
                                        case 1402572333:
                                            str21 = "ۥۛ۠۟ۗۤۢۘۗۜۡۡۘۛ۠ۧۨ۠ۥ۠ۜۡۨۘ۟ۥۧۥۙۡۨۖۡۘۛ۬ۗۛۘۥۘۗ۟ۚ";
                                    }
                                }
                                break;
                            case 1287982101:
                                break;
                        }
                    }
                    break;
                case 376987129:
                    str = "ۧۡ۟ۙۤۖۘۚۜۜۘ۬ۥۚۜ۫ۘۦ۫ۗۦۥۚ۫ۢۤۦۘۚۜۜۘۥۡۤ۬ۜ۫ۖۚ۟ۦۨۛ";
                    i4 = i2;
                case 453054172:
                    arrayListA2 = a(jSONObjectOptJSONObject2.optJSONArray("black_list"));
                    str = "ۧۛۘۛۥۤۗۨۜۘۧۗ۠ۛۦۧۘۙ۟۠ۗۜۨۘۧ۬ۦۛ۟۬۬۬ۥۨ۟۠ۥ۬ۦۙۧۥ۠۬ۘ";
                case 603028214:
                    lVar.run();
                    str = "ۗ۬ۘۦۨۧۘ۟ۘۘۥۧ۟۫ۖ۠۬ۤ۬ۦۤۙۨۤۗۧۜۦۘۘ۟ۨ۫ۘۧ۟";
                case 629376003:
                    str = "ۚۙ۬ۤۖۧۢۦۡۘۢۛ۫ۚۢۨۦۢۨۘۦۥۖۘۦۤ۫ۡۥۤ۫۬ۘۘ";
                    jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i4);
                case 644916774:
                    str = "ۧۘۥۘ۫ۤۛۘۖۙ۠۫ۤۜ۠ۡۘۚۨۘۘۙۧۡۧۚۜ۠ۛ۬ۦۥۨۘۘۦۢ۟ۨۘۘ";
                    i3 = i4 + 1;
                case 778824974:
                    arrayListA = a(jSONObjectOptJSONObject2.optJSONArray("white_list"));
                    str = "ۜۡۨۧۚۧۚ۟ۛۗۨۘۚۥۤۙۚۡۘ۬ۛۗۥۢۙ۬ۨ۟۬ۚۦ";
                case 791740348:
                    str = "ۙۛۜۘ۫ۦۘۘۗۦۛۡۤۙۙۙۦۘ۠ۘۗ۠ۗ۫ۙۡ۠ۗۛۖ۟ۤۜ۫ۤۥۘۖۙۘۘۜۡۡۘ۬ۤۜ";
                    i4 = i3;
                case 1043476319:
                    arrayListA3 = a(jSONObjectOptJSONObject.optJSONArray("white_list"));
                    str = "ۧۤۛ۬ۗۜۧۤۖۘۧۚۧۜۥۡۘۡۛ۫ۙ۠ۨۘۙۢۜۘۥۦۨۛۦۖۘۛۥۖ۠ۘۜ۟ۤ۟ۜۖۧ۠ۦ۠ۜۡۨۘۡۥۥۘۥ۬۫";
                case 1097579942:
                    str = "ۚۜ۠ۧ۬۬ۚۨۛۘۤ۬ۢ۬ۛۤۦۘۛۦۛۗۡ۟ۦۚۧۦۖۜۘۚۖۖۘۜۗۖۘۤۨۧۘۖ۬ۖۘۤۖۧۘۥۦۦۘ";
                    jSONArrayOptJSONArray2 = jSONObject.optJSONArray("popups");
                case 1484285585:
                    String str22 = "ۗۦ۟۫ۨۧۘۤ۬ۤ۫ۢۘۘۜ۬ۚۦۤۗۧۡۢۦۗۖۙۜ۫ۦۜ۬۬ۛۜۚۢ۬ۤ۬ۘۙۙۦۘۢۜۘۜۘۜۘ";
                    while (true) {
                        switch (str22.hashCode() ^ 450597912) {
                            case -522284573:
                                str = "۬ۡ۬۠۟ۦۨ۬ۛۧۧۢۦۗۤۖۗ۬ۖۧۦ۟ۡۙۖۡۧۚۜۙۗۥ۬ۚۦ۬ۥۘۖ";
                                break;
                            case -278711148:
                                str22 = "ۖۜۧۘۤ۫ۗۖۢۡۚۨۦ۫ۦۨۙۨۚۖۘۤۦ۬ۛۥۘۗۨ۬";
                            case -274593310:
                                String str23 = "ۤۤۦۘۖۥۡۘۥۧۜۘ۫ۖۧ۬ۡۛۦۜۜۢۚۡۘ۟ۛۜ۬ۜۨۘ۟ۧۘۘۛۜۡۦۛۛۢۗۚۖۗۦۘۢۡۗۡۨ۫";
                                while (true) {
                                    switch (str23.hashCode() ^ (-1982467694)) {
                                        case -1104021761:
                                            str23 = "۫ۖ۟۟۟ۦۜۦۥۘۧۨۛۙۧۜۥۥۗ۬ۡۜ۫۠۟ۛۦۡۗۛۙۛ۠۬ۤۡۥۛۜۡۦۘ۫ۙۧۡۡۧۘ";
                                        case -471383303:
                                            str23 = jSONObject.optBoolean("enablePopups", false) ? "ۚۢۛۡۦۦۘۧۗۤۦۤ۬۫۟ۡۘۜۚ۟۫ۙۜۘۡ۫ۥۤۜۤ۟ۡۛۗۨۢۛۥۜ" : "۠۫ۛ۠ۖۗۢ۫ۦۘۛۥۨۨۗۢۤ۟ۗۧۦۢ۠ۘۦۘۢۢۥۘۧۘۖۘۦ۠ۢ۠ۡ۬";
                                        case -333246647:
                                            str22 = "ۗۢۜۘ۠ۦۦۢۡۙ۬ۚ۟۫ۘۡۛۧۨۘۜۖۦۘۥ۠ۡۘۤۢۜۘۙۖۢۘۡۘ۠۟ۚۘ۟ۥۘۥۖۙ";
                                            break;
                                        case -46516739:
                                            str22 = "ۖۨۥۘۢۤۘ۠ۤۜۘۡۡ۠ۡ۟ۦۚ۬۫ۛۛۛۢۤۤۘۖۜۘۘۥۖۤۘ۟ۗ۫ۦۢۦۦ۟ۘۘ";
                                            break;
                                    }
                                }
                                break;
                            case 1951622509:
                                break;
                        }
                    }
                    break;
                case 1544177551:
                    str = "۫ۖۡۨۦ۠ۘۦۙۚۛۗ۬ۨۥۧۚۖۘۨۛۤۥۦۧۙ۫ۢ۠ۧۖۢۖ۟ۡۘۦۗ۟ۧ۬ۨۘۖۦۜۢۦۢۤۢۢۘ";
                case 1685625034:
                    i2 = 0;
                    str = "ۨۧۦۘۦۙۖۤۨۗۖۤۛۦۦۘۛۤۧ۠ۡۡۢۚۢۛ۠ۖۖۜۡۘۡۗۥۨۚ۬";
                case 1725555618:
                    str = "ۧۡ۟ۙۤۖۘۚۜۜۘ۬ۥۚۜ۫ۘۦ۫ۗۦۥۚ۫ۢۤۦۘۚۜۜۘۥۡۤ۬ۜ۫ۖۚ۟ۦۨۛ";
                case 1794697688:
                    String str24 = "ۧۛۖۙۥۤۧۙۜۥۦۨ۟ۙۥۡ۫ۖۤۗۡ۬ۙۗۢۚۗۦۘ";
                    while (true) {
                        switch (str24.hashCode() ^ 478602888) {
                            case -1941719648:
                                break;
                            case -1331040315:
                                str24 = "۫ۦۧۘۛۨ۠۟ۡۖۙۦۥ۫ۡۙۢ۬ۨۦۧۦۘۗ۟ۜۘ۫ۦۖ۟ۧۦۢۚۢۚ۠ۧۡۛۚۚۢۤ۠ۚۥۘۢۖۥۘۜۖۥۘ۠ۡۥ";
                            case 241708344:
                                str = "ۡ۟ۗۖۨۛ۠۠ۙۧ۫ۖۧۧۜۥۘۨۚۜۨۥۗۢۨۙۗۥۜۘۘۗۙۚۨۛۤۡ۬۟ۨ۠";
                                break;
                            case 545653577:
                                String str25 = "ۨۦ۠۠ۘۡۧۤۜۥۤ۬ۢۤۘۘۨ۟ۧۦۚۧ۬ۨۥ۠ۚۥۢۚۙ";
                                while (true) {
                                    switch (str25.hashCode() ^ (-1763529351)) {
                                        case -960788687:
                                            str25 = "ۦ۟ۨۙۙۡۚۖۚۗۜۖۘۢ۟۟ۢۨۧۘۢۦۘۚۛۧۨۦۖۘۧۢۨۧۖۘۘۙۡۡۘۡۡ۠ۢ۟ۙۧ۟ۨۧۢۢ";
                                        case -62057681:
                                            str24 = "ۤۙۖۘۤ۟ۜۘۦۡۚۜۛۚۥۡۧۘۛۜۥۘ۟۟۬ۨۡۜۘ۬ۢۛۙۧۨۥۢۥۘۦ۠ۡۘۛ۠ۗ۠ۜ۫ۖۢۚ۠ۦۘ";
                                            break;
                                        case 1048851292:
                                            str25 = jSONObject.optBoolean("enablePopups", false) ? "ۡۚۧۨۤ۬۬ۤۙۤۥ۟ۤۖۥۢۢ۫ۛۖ۠۬ۘۙۡ۠ۥۛۖۚ۫ۤۢۘۖۧۘۧ۠۬ۨ۬۟" : "۬ۛۤ۫ۨۚۙۘۢۨ۫ۘۘ۬ۡۘ۬۬ۜۘۢۤۖۘۦۜ۟ۦۗۖۘۙۡۥۘ";
                                        case 1066373195:
                                            str24 = "ۗ۫ۦۚۥۦۜۜۥۦۦۘۜۨۛ۠۟۬ۗۛۦۘۨۜۦ۬۠۬۫ۤۨ۟۠ۧۖ۬ۡ۫ۨۖۘ۬ۦ۟";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case 1876934631:
                    str = "۟ۗۤۢۛۥۘۢۦۢۡۤۘۚ۟ۡۘۨۜۧ۟ۗۙۖۘ۬۫۬ۥ۠ۦۘ۟ۖ۠ۗۖۘۜۛۨۖۛۘۨۤۦۘ۫ۥ۫";
                    i5 = i6 + 1;
                case 1914554768:
                    lVar.run();
                    str = "۟۬ۙ۟ۤۡۥۗۨۛۘ۫ۛۚۚۧۧ۟ۨۦۗۦ۫ۡۘۢ۟ۡۧ۬ۛۧۤ۟ۖۧۦۘۘۙۦۚۤۨۘ";
                case 1939898588:
                    str = "ۘۘۢۚۥۛۗۖۦۤۦۗ۟ۚۘ۬ۢۦۘۤۦۚۢۚۤۙۥۘۘۖ۟ۘۘۧۚۘۘۧۛۜۘۢ۟ۥۢۚۨۙۘۡۗۤۖۘۧ۫ۨۘۙۥۜ";
                case 1945722668:
                    str = "۫ۖۡۨۦ۠ۘۦۙۚۛۗ۬ۨۥۧۚۖۘۨۛۤۥۦۧۙ۫ۢ۠ۧۖۢۖ۟ۡۘۦۗ۟ۧ۬ۨۘۖۦۜۢۦۢۤۢۢۘ";
                    i6 = i;
                case 2035547469:
                    k2.otTlU4Xc(activity, jSONObjectOptJSONObject.optString("id", ""), jSONObjectOptJSONObject.optString("imageUrl"), jSONObjectOptJSONObject.optInt("clickAction", 0), jSONObjectOptJSONObject.optString("clickText", ""), jSONObjectOptJSONObject.optString("callback", ""), jSONObjectOptJSONObject.optInt("countdown", 5), jSONObjectOptJSONObject.optBoolean("canSkip", true), jSONObjectOptJSONObject.optBoolean("autoClose", false), jSONObjectOptJSONObject.optBoolean("lock", false), arrayListA3, arrayListA4, null);
                    str = "ۦۨۙۘ۬ۡۘۘۜ۬ۥۢۜۘۤ۫۠ۛۙۜۘ۠ۜۘۡۡۜۘۚۤۦۘۢ";
                case 2055356423:
                    jSONObjectOptJSONObject2.optString("id", "");
                    str = "ۜۘۨۜۤۖۘ۫ۧۦۘۨۧۡۘ۬ۙۢۨ۟۬ۦۘۥ۫ۧۥ۠ۚۛۚۥۘۥ۬ۙۜۘ";
                case 2094880980:
                    i = 0;
                    str = "ۡۧۧۥۙۥۜۤۦۛۗ۠۬ۥۧۥۘۡۦۧۙۦۘ۬ۨۡۘۨ۫ۖۨۦۜۥۛ";
            }
            return;
        }
    }

    public static ArrayList a(JSONArray jSONArray) {
        ArrayList arrayList = null;
        String str = "۠ۗۧۛۨۥۥ۟ۤ۠ۡۚۡۘۡۦۧۘۢۨۦۢۖ۟ۙۥۜۖۚۥۘۡ۫ۜۘۜۘۘۘۜۙۙۜۧ۟";
        int i = 0;
        int i2 = 0;
        while (true) {
            switch ((((str.hashCode() ^ 229) ^ 488) ^ 154) ^ (-902805790)) {
                case -2095685115:
                    return arrayList;
                case -1629799890:
                    str = "ۙۘ۠ۘۥۤ۠ۨۛۘۨۘۘۡ۠ۤ۟ۜۜۘ۠ۛ۫۠ۡ۟ۧۧۜۧۘۘۘۢۢۧۜ۠ۦۛۚۗۗۘ۬";
                    continue;
                case -1549280168:
                    i = i2 + 1;
                    str = "ۖۢۥۘ۠ۡۦۘۖۗۗ۟ۘۥۧۧۛۧۘۥۨۘۨۧۗ۬ۨۥ۠ۤۖ";
                    continue;
                case -1483494253:
                    arrayList.add(jSONArray.optString(i2));
                    str = "ۙۡۢۖۙۡۘ۟ۚۛۤۥۚ۫ۨۜۜۚۥۘۤۢۖۘ۫ۡۖۘۨ۟ۤۤۙ۫ۥۖۢ۬۬ۚ";
                    continue;
                case -1314999491:
                    str = "ۙۘ۠ۘۥۤ۠ۨۛۘۨۘۘۡ۠ۤ۟ۜۜۘ۠ۛ۫۠ۡ۟ۧۧۜۧۘۘۘۢۢۧۜ۠ۦۛۚۗۗۘ۬";
                    i2 = 0;
                    continue;
                case -765773217:
                    arrayList = new ArrayList();
                    str = "ۗ۟۟۠ۛۡۘۛۨۧۘۖ۫ۚۢۙۛۖ۫ۤۙۦۜۥ۠ۚ۟ۦۙۡ۟ۘۘ";
                    continue;
                case -420973090:
                    str = "ۜۨۘۘۚۧۧۚۚۘۙۗۜ۬ۦۘۦۤۡۘۛۚۥۢ۟ۦۧۗۘۖۘ";
                    continue;
                case 183629767:
                    str = "ۡ۬ۡۘۤۙۚ۟ۨۧۗۨۜۘۢ۬ۨۘ۠ۚۤۥۨۥۘۥۤۥۜۥۙۡۥۖ";
                    continue;
                case 586890633:
                    String str2 = "۬ۚۨۘۚۥ۫ۦۚۡۥۧۖۘ۟ۛۖۢۖۜۘۧۛۦۤۤۦۘ۫ۛۥۘ۫ۡۦۧ۬ۖۘۘۧ۫";
                    while (true) {
                        switch (str2.hashCode() ^ 1227123759) {
                            case -1349365058:
                                String str3 = "ۨۗۧ۟ۡ۫ۢۡ۬ۛۦۥۚۘۧۖ۠ۗۗۛۖۘۧۘ۫ۤۗۡۧ۟ۙ";
                                while (true) {
                                    switch (str3.hashCode() ^ 1219559200) {
                                        case -1094033723:
                                            str3 = i2 < jSONArray.length() ? "۠۟ۖۘ۟۟ۡۥۢۗۥۧۘۜۡۤۘۖۘ۟۫ۖۖۖۗۘۧۜۘ۫ۧ۟ۖۚ۟ۖۡۜۘۛ۠ۦۗۜۨ" : "ۡۗ۫ۙۙ۫ۨۢۘۜۧۨۘۧۨۡۘۤۤۖۘۦۚۡۘ۬۠ۘ۠ۡۧۡۥۤۘۤۡ۫ۛۨ";
                                        case -576107280:
                                            str3 = "ۙ۠۫۠۠۠ۤ۠ۨۘۚۛۘۙۗۤۡۤۘۗۜۥۡۦۘۛ۫ۖۘ۟ۨۦۘۨ۫ۘۘۨۙۘۘۗۡۧۘۘۢۛ";
                                        case 897514905:
                                            str2 = "ۥۧۤ۬ۗۖۢۢۦ۟ۜۘۘۛۚۙۢۧۜ۟ۜۖۡ۬ۗۡۖۛ۬ۙۙ";
                                            break;
                                        case 1341090116:
                                            str2 = "ۤۥۙۢۖۜ۟ۥۖۘۧۛۙۤ۫۠ۜۧۤۤۨۡۚۥۢۨۨۦۘۖۖۛۙ۠ۡ۟ۥۨۘ";
                                            break;
                                    }
                                }
                                break;
                            case -1156073919:
                                str2 = "ۙ۟۠ۧۙ۫ۛ۠ۡۘۛۛۢۖۢۢۢۢۜۘۛۜۜۘۛۛۡۦۘ۬ۦ۟ۤ";
                            case 582110757:
                                str = "ۘ۬ۗ۟۬ۘۘۗۜۡۙ۟۬ۧ۟ۦۨۚ۬ۙۧۢۤۦۖۡۨۦ۟۫۫";
                                break;
                            case 1423255071:
                                break;
                        }
                    }
                    break;
                case 685413605:
                    str = "ۡ۫۫ۚۚۖۘۥۗۜۗۤۦۜ۬ۧۖ۫ۙۙۢۧ۟ۥۤ۫ۛۚۢۚۗۙ۟ۘۘۘۛۘ";
                    i2 = i;
                    continue;
                case 1486064002:
                    String str4 = "۬۬۠ۗۚۥۘۗۜۧۘۙ۫ۖۙۛۙۤ۬۬ۢۦۢۜۛۘۘۦۖۢۚۛۥۘ";
                    while (true) {
                        switch (str4.hashCode() ^ 463299788) {
                            case -912976060:
                                str = "ۡۤۦۘۥۙ۬ۗۧۙۙۙۨۖۗۜۢۜ۟ۙۨۨۜۦ۫ۗۖۘ۟ۜ۬۠ۚۖۘۢۜۜۘ۬ۨۘۘۥۜۚ";
                                break;
                            case -557102705:
                                break;
                            case 1332374188:
                                str4 = "۫ۘۘۘ۠ۨۤۧۨ۟۫ۨۧ۟ۦۛۚۧۚۚۥۗ۠ۧۦۘۧ۫ۜۘۙ۫ۥ۟ۡۧۜ۬ۗ۫ۨۗۨۘۖۘ";
                            case 1899644322:
                                String str5 = "۬۬ۦۘۜۘۘۘۜۘۛ۬ۗۖۨۖۖۖۦۙۗۘۘ۬ۢۥۘۘۜ۠ۨۤۦۘ";
                                while (true) {
                                    switch (str5.hashCode() ^ 885014707) {
                                        case -2018847619:
                                            str4 = "ۧۨ۬ۤۤۧ۫ۜۗۨۡۘۙۤۦ۟ۨۘۛۖۦۘ۫ۨۘۘ۫ۗۨۘۥ۬ۨۘۧۥۙۡۦ۬ۦۢۦۤۙۜ۫ۢۖ۬۟";
                                            break;
                                        case -1639279999:
                                            str5 = "ۡۤۦۜۛۛۚۚۙۢۢۜۘۡۤۦۘۜۢۧ۠ۧۙۤۨۘۘۨۘ۬ۙۤۖۢ۫ۥۙ۟ۨۜۥۤۤۘۘۧۥۡۥ۠ۘۘۡۤۖ۬ۦۜ";
                                        case -1323186954:
                                            str5 = jSONArray != null ? "ۢۜۗ۟ۦۗۦۚ۟ۤ۫ۧ۫۠۬۬ۨۙ۬ۘۛۙۨۤۡ۫ۧ۟ۙۤۙۨۙۖۦۥۖۙۤۡۥ۫" : "ۢ۬ۧ۠ۘ۬ۤ۟ۡۘۧ۠ۦۜۦۡۢۤۡۘۚۧ۟ۥۦۡۘۡۛۧۦۖۗۗۧ۟ۙۙۜۘۥۧۥۘۥۛۨۘ";
                                        case 612851226:
                                            str4 = "ۦۧۖ۠ۡۖۛۥۜۘۨۡۥۘۡۧۨ۟۟ۡۘۧۙۖ۟ۛۡۘ۠ۙۥۢۜۢۜۨۜۘۙۖۗۜۘۙۖۡۥ۟۫ۥ۟ۥۧۥۚۙۘ۟";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
            }
            str = "ۥۥۜۜۖۗۤ۟۟ۨۢ۫۟۫ۘۢ۠ۙۛۘۧۖۤۤۖۖۙ۫ۚۘۘۙۤۨۘۘۦۡۙۡۥۘۘۛۧ";
        }
    }

    public static void clearNeedPopupIds() {
        String str = "ۦۘۡۘۤۜۨۘۘۦۥۦۨۨۘ۠ۘۘۘۤۨۘ۠ۡۙۧۡۙۘ۬ۛۛ۬ۦۛۡۘۜ۫۫۬ۧۦۘ۫ۗۘ";
        while (true) {
            switch ((((str.hashCode() ^ 164) ^ 640) ^ 533) ^ 1395586120) {
                case -1858674405:
                    needTextPopupIds.clear();
                    str = "ۛۤۘۘۦۛۛ۫ۤۛۧۤ۫ۦۙ۟ۡۨۚۤۢۨۢۘۜۘ۟۠ۚ۠ۜۙ۬ۛۦۙۘ";
                    break;
                case -1661226108:
                    needHtmlPopupIds.clear();
                    str = "ۢۦۦ۫۫ۗۘۦۘۘۛۖۧ۫ۙۨۤۗۛۤۨۘۚۖ۬۫ۖۗۜۢۚۖۡۧۛ۠ۜۘ";
                    break;
                case -930813115:
                    needFullscreenPopupIds.clear();
                    str = "ۘۙۚۡۙۖۘ۠۟۟ۥ۟ۡۘۜۢۘۘۛۦ۠ۜۙۥۘ۫ۥۘۜۙ۠۫۟۠ۡۜۘۜۧۛۙ۟ۨۖۧۡ";
                    break;
                case -669075058:
                    return;
                case -509178178:
                    needImagePopupIds.clear();
                    str = "ۨۥ۬ۗۧۡۘ۠ۦۘۙۧۦۖۨۥ۟ۖۦۘۖۘۢۛ۠ۦۘۢ۠ۨۘۜۗۧۚۧۖۘ۫۠ۘۘۚۡۡۘۥۥۦ";
                    break;
                case 928098047:
                    needMessagePopupIds.clear();
                    str = "ۗۘۖۛۙۥۡۨۧۗۜۥ۠ۨۘۦۛۥۖۡ۟ۥ۠ۜۘۗۘۧۘ۟ۖۧۘۛۚۦۤۢۨۘۖۜۤ۬۫ۘۘۦۨۨ۠ۦۨ";
                    break;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:4:0x003c. Please report as an issue. */
    public static void snfR8HbO(Activity activity, JSONObject jSONObject) {
        JSONArray jSONArrayOptJSONArray = null;
        int i = 0;
        JSONObject jSONObjectOptJSONObject = null;
        String strOptString = null;
        String strOptString2 = null;
        String strOptString3 = null;
        String strOptString4 = null;
        String strOptString5 = null;
        String strOptString6 = null;
        boolean zOptBoolean = false;
        boolean zOptBoolean2 = false;
        JSONArray jSONArrayOptJSONArray2 = null;
        ArrayList arrayList = null;
        JSONArray jSONArrayOptJSONArray3 = null;
        int i2 = 0;
        int i3 = 0;
        int i4 = 0;
        ArrayList arrayList2 = null;
        JSONArray jSONArrayOptJSONArray4 = null;
        int i5 = 0;
        int i6 = 0;
        int i7 = 0;
        int i8 = 0;
        int i9 = 0;
        String str = "ۤۢ۟۫ۤۨۘۨ۠ۨۘۗ۬ۘۘ۫ۘ۟ۗۡ۠ۜۢۗۦۧۜۘۨۜۖۤۗۖۘ۫۟ۚۦۦ۬";
        while (true) {
            switch ((((str.hashCode() ^ 650) ^ 979) ^ 981) ^ 1555517129) {
                case -2089484919:
                    String str2 = "ۜۦۙۙۜۥۘ۫ۜ۠ۖۢۨۢۥ۟ۜۘۖۛۜۘۥۡۨۘ۟ۢۘۘۥ۬۟ۗۜۜۙ۟";
                    while (true) {
                        switch (str2.hashCode() ^ (-51708931)) {
                            case -1909466256:
                                str2 = "ۗۨۘۘۜ۫ۘۘۛ۠ۛۖۛۚۧ۫ۡۘۜۨۜ۬ۜۦۡ۟ۦۢۜۖۘۜۦ۫ۥۜۛۘۤۘۤ۫۠۫ۤۧ۫ۧ۠ۘۨ۠ۤۜۦۖۡۡ";
                            case -1642870803:
                                break;
                            case -635775098:
                                String str3 = "ۦۤۤ۟ۦۜۤۨۘۖۨۡۘۗۗۗۙۖۧۧۨۘۘۙۨ۟۟ۡۘۚۖ۬ۢۧ۟ۢۗ۟ۥۗۨۘۧ۬ۥۘۡۘۛۙ۟ۚۙۛ۫۬ۢۧ";
                                while (true) {
                                    switch (str3.hashCode() ^ 887255227) {
                                        case -404838350:
                                            str3 = "ۜ۫ۥ۟ۤۜۢۚۗۢۦۡۘۨۥۤۘۧۨ۠ۛۤۛۚۗۥۛ۫۟ۗۙۙۦۘۦۢۖۘۘۦۦۧۗۙۧۤۥۘۦ۬ۧۘۜۦۘ۫ۨۢ";
                                        case 1018267147:
                                            str2 = "ۧ۟ۢ۠ۚۥۢ۠ۘۘۦ۫ۢۦۚۡۜ۫ۖۥ۠ۦۘۧۗۦۙۡۘۚ۟۠ۡۤۚ۫ۗ۬۠۟ۥ۠ۡۦۘۧۤۘ۫ۢۦۘ";
                                            break;
                                        case 1067939505:
                                            str3 = jSONObjectOptJSONObject != null ? "ۧ۫ۡۡ۠ۥۘۨۦ۫ۜ۬۬ۦ۬ۥۤ۫۠ۖۜۘۘۖۢۚۛۧۛۤۢ۟ۢۜۥۛۡۘ" : "ۦ۠۟۟ۦۜۘۜۖۦۘۨۘۜۘ۟ۥ۠ۘۨۦۘۡۜۧۨۦۗۜۙ۠ۡ۟ۨ";
                                        case 1329449449:
                                            str2 = "۬۫ۥ۫ۢۥۘۗۜۧۘۚ۫ۙ۠ۜۘۙۖۦۘ۠ۗۧ۬۟ۗۤۚ۟۠ۚۜۘۜۜۧۘۥۥۗۥۖۘ۬ۥۙ۟ۘۡۗۦۧ";
                                            break;
                                    }
                                }
                                break;
                            case 1008108499:
                                str = "ۧۦ۫ۘۥۡۗ۠ۛۜۘ۠ۨۡۥۘۙۚۦۘ۟ۦۜۗۦۨۢۛۡۘ۠ۛۗۚۤۨۘۛۧۥۨۘۖۡۨۘۧ۟۬ۘۛۛۗۙۖۚۜۚ";
                                break;
                        }
                    }
                    break;
                case -2071387732:
                    k2.FFHTC11I(activity, strOptString, strOptString2, strOptString3, strOptString4, jSONArrayOptJSONArray2, strOptString5, strOptString6, zOptBoolean, zOptBoolean2, arrayList, arrayList2);
                    str = "ۘۨۡۘۧۥۗۦۡۧۧۙۢ۫ۥۢۗۖۡۘۤۖ۠ۨۗ۟۠ۥ۫ۤ۬ۨۘۙۢ۠۫ۖۦۘ";
                case -1999904601:
                    break;
                case -1855320074:
                    strOptString3 = jSONObjectOptJSONObject.optString("message", "");
                    str = "۫ۛ۫ۦ۠۠۟ۘۨۘ۬ۤۥۘۜۥۘۡۢ۠۫ۚۡۘۦۥۤ۫ۖۗۙۜۚۢۛۡۘۛۡ۬ۜ۬۬ۘۚۦۘۤ۟ۘۘۤۗۥ";
                case -1711564648:
                    String str4 = "ۢۜۖۘ۟۬ۥۧۘ۟ۢۨۗۤ۬ۨۦۨۘۘۘ۬ۨۢۙۡۘۚ۠ۘۘۗۜۜ۟ۤۦۘۡۚۛۛ۫ۘۘۢۢۜۧۙۡۜ۬ۜۦۘۖۙۤۦ";
                    while (true) {
                        switch (str4.hashCode() ^ 1211049769) {
                            case -972966874:
                                str4 = "ۜ۠ۚۦۜۙۜۘۘۖ۠ۙۢۙۦۘ۠۠۫۬۠ۧۨۧۛۡۢۗۤۡۜۘۛۛۡۘۧۚ۟ۢۜ۠۠ۤۨۘۡۘ۟ۜۘ";
                            case -659666734:
                                str = "ۗۚ۟۫ۡ۟ۘۡۢۚۜۢۚ۠ۢۨ۠ۥۧۡۡۘۥۙ۬ۧۖۚۘۙ";
                                break;
                            case 1005450286:
                                break;
                            case 1101300750:
                                String str5 = "ۖۜۜ۠ۧ۠۫۟۫۟ۜ۠ۤۨۨۙۦ۬ۥۨۥۦۤ۫ۥۗۨۥۦۖۘ۠۟ۘۢ۟۟ۤۤۛۨ۠ۢۥۡۥۗۡۙ۫ۥۥۘۗۘۛ";
                                while (true) {
                                    switch (str5.hashCode() ^ (-205074390)) {
                                        case -1614677288:
                                            str4 = "ۢۥ۬ۢۚۗۥۨۥۥۛۜۘۘۥۧۦۤۜۘ۠ۚۧۗۥۘۖۚۖۖۚۜۘۥۗۚۜۚۘۘ";
                                            break;
                                        case -658986494:
                                            str5 = "ۢۖۦۘۙۤۦۤۡ۟ۨۤۖۘۢۗۧۜۘۢۥۨۘۘۨ۫ۢۛۜۛۖۛۖۘۧ۠ۜۥۤۥۘ";
                                        case -240994533:
                                            str4 = "ۚۛۦۘۢۗۥۦۤۦۜۡ۟۟ۘۥۗۡۧۘۤۦۖۘۤۚۙۧۚۦ۟ۧ۫۟ۡۨۨۥۛ";
                                            break;
                                        case -73179455:
                                            str5 = jSONArrayOptJSONArray4 != null ? "ۡۗۗۛۘۨۜ۬۫ۜۥۗ۟ۚۡۤۜۦ۠ۙ۬ۛۤۦۜۜۘ۫ۧۚۤ۠ۘۛ۫ۘۚۘۘۘ۠ۨ۠ۖۧۢۗۧ" : "ۧۧۡۘ۫۟ۦۘۢۜ۫ۘۚۨ۫ۛ۟ۛۧۜۘۖۖ۠ۧ۟ۖۛ۬ۡۨۚ۟ۧۚۛۤۨۦۗۥۥۘۢۚۖۘۙۢۡ۠ۜۥۘ";
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case -1540445669:
                    arrayList.add(jSONArrayOptJSONArray3.optString(i4));
                    str = "ۘۜۖۘۥۗ۠۬ۨۛۘۗۥۘۖۧۡۘۢۙۦۘۖۤۥۦۢۖۘۘۢۤۨۘۧۘ";
                case -1475813527:
                    String str6 = "ۙۖۡۘ۟ۨۤۡ۬ۨ۫ۦۧۢۥۘ۟۠ۗۡۙۦ۫ۗۛ۫۠ۤ۠ۜۡ";
                    while (true) {
                        switch (str6.hashCode() ^ 2100283792) {
                            case -842538780:
                                str6 = "ۚۡ۟۠ۙۦ۠ۥۢۥۚۛۢۥۘۨۛۢۡۤۢۗ۠ۗۡۘ۬ۢۨ";
                            case -11858626:
                                String str7 = "ۥ۫ۡۗۙۡۙۤۦۘۡ۟ۜۘ۬ۛۘۙ۟ۚ۟ۖۨۘ۬ۤۡ۠ۡۧۖۡۨۧۡۘۘۛۚۛۤ۫۫ۥۗۥۥۧ۫ۤۖۡ";
                                while (true) {
                                    switch (str7.hashCode() ^ 1221508825) {
                                        case -509926027:
                                            str6 = "ۧۙۧۚۛۜۘۦۦۜۘۖ۬ۤۘۦۧۘۨۥۚۛ۬ۦۘۜۦۨۙۢۦ۫۬۬ۧۘۖۘۨ۫ۥۘ";
                                            break;
                                        case -473739641:
                                            str6 = "ۘۨۗۗۨۥۘۘ۬ۡۚۨۧۘۥ۠ۥۖ۬ۡۘۖۖۗۧۡ۬ۡ۟ۘۚۜۡ";
                                            break;
                                        case 1535488655:
                                            str7 = "ۙۚۢۨۘۥۜۧۘ۠۫ۘۛۘۥۜۥۘۧ۟ۜۡۧۦۡۦۨۘ۟ۜۘۙ۬ۤۤۨۙ۬ۡۦۘۗۖۨۢۚ۟۫ۨۤ۟۟ۢ۟ۦۦ";
                                        case 2046665405:
                                            str7 = i7 < jSONArrayOptJSONArray4.length() ? "ۧۚۦۘۡۨۧۘ۫ۚ۬ۛۨۧۘ۠۠ۡۥۚۛۨ۫ۖۗۙۤۨۦۗ۟ۜۗ۬ۤۢۖ۬ۥۜۨۤۗۛۢۧۤۜۘۨۤۨۙۘۢۖ۬ۚ" : "ۨۘۗۙ۟ۡۘۙۧۧۤۜۡۘۡۨۘۘۙۚۘۨۘۦۘۧۙۥۜۗ۟ۖۤ۠ۘۘۗ۬ۦ";
                                    }
                                }
                                break;
                            case 744101412:
                                str = "ۢۙۙۜۢۛ۟۫ۥۘۖ۬ۖۜۚ۟ۦۨۧۘۗۡ۫ۧۤۛۨۢۦۘۚۢ۠";
                                break;
                            case 1559588774:
                                break;
                        }
                    }
                    str = "ۚۡۖۚۜ۬ۗۢۡۚۧۡ۫ۙۢ۫ۨۘ۫ۗۨۘ۫ۢۚۙ۟ۜۙۜۡۘ";
                    break;
                case -1401974032:
                    String str8 = "ۜۛۦۘۖۗۨۛۖۥۖۨۡۛۥ۬ۗۘۨۡۥۦۡۘ۫ۡۤۧۧۡ۬ۘۜۡۘ۠۠ۤۘۨۘۧۖ۠۟ۦۡ۠ۗۦۘۛ۠۬۬۟۠";
                    while (true) {
                        switch (str8.hashCode() ^ (-2017302683)) {
                            case -1105075440:
                                str8 = "۬۬ۥۖ۫ۨۘۚۚۡۘۧۦ۫ۤۧۡۘ۟ۨۡۚۤۙ۟ۢۙ۠ۜۨ۟ۡۘۧ۟ۦۡۦۥۘ";
                            case -348896078:
                                String str9 = "ۚۡۢۨۜۦۚۥۙۙۥۖ۟۠ۦ۟ۛ۫ۥ۠ۦۘۦۘۙۥۘۧۘۜۘ";
                                while (true) {
                                    switch (str9.hashCode() ^ (-244157649)) {
                                        case -192333113:
                                            str8 = "ۖۛۥۘۗۢۨۘۗۖۥۤۧۦ۟ۦۨۛۤۨ۟ۥۥۘ۬ۡ۠ۢ۟ۢ۟ۙۢۚۗۤۖۡۚۘ۠۟۫ۨۦۘۥۤ۬ۚۨۥ۠ۛۙۘۧ۟";
                                            break;
                                        case 570957693:
                                            str8 = "۬ۗ۟ۦۛۗۨۡۗۙۥۖۘۨۗۚۧۡ۟۠۫ۨۗۡۛۜۖ۟ۥۜۚۙۘۨ۠ۦۜۡۗۦۥۡۛۜۚۢۛۖۘۛۤۖۘۥۨۢ";
                                            break;
                                        case 683579186:
                                            str9 = i4 < jSONArrayOptJSONArray3.length() ? "ۖۗ۟ۙ۬ۜۘ۫ۢۡۛ۠ۜۘ۬۠ۡۧۛۦ۠ۜۤۗۦ۟ۖۙۜۙۙ" : "ۨۦۦۘۛ۟ۖۜۘ۫ۗۡۚۚۢۜۚ۟ۡۘۙۡۦۘ۫ۡۧ۬۬۟ۨۨۘ۫ۗۖۘۨۡۨۚۧۙۦۘۢۦۥۢۖۦۘۖۦ۫ۥۦۥۘ";
                                        case 1213479137:
                                            str9 = "ۛ۠ۚۡۡۤۘۜۢۚۜۨۧۘۚۤۨۤۘۜۘۜ۬ۥۘۦۦ۫۬ۨ۬۫ۙۢۤ۫ۙۜۡۥۥۙۖ۫ۧۨۘۦۙۨۚۤۨۘ۠ۚۡ";
                                    }
                                }
                                break;
                            case 1062824822:
                                break;
                            case 1082900263:
                                str = "۬ۥۥ۠ۡ۠ۗۙۖۘۛۢۜۘۥۙۛۤۖۨۘۖۙۘۘۦۘۧ۟۠ۛ۫ۨۢۡۜۖۘۧۡۚۢۙۦۘ";
                                break;
                        }
                    }
                    str = "ۥ۟۬۟ۘۦۘۥ۫ۧۥۖۘۦۨۚۨ۠ۗ۟ۨۜۘۢۙ۫ۦۚۘۘۨۘ۫۫۬۬ۙۡۜ۟ۧ۠۠۬۬";
                    break;
                case -1325940522:
                    jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i9);
                    str = "ۙۗۥۘ۫ۢۚۡ۟ۘۘۙۛ۟۬۬ۨۘۜ۠ۥۡۦۖۗۦۘۛۢۖۘۧ۫ۗ۫ۘۜۘۗ۬ۘۘ";
                case -1273461884:
                    str = "ۙۖۖۘۥ۬ۚۗۨۧۘ۫۠ۡۖۗۡۖۨۛۧ۟ۜۡۗۡۘۘۚۧۖۜۡۚۦۘۘۥۡۦۛۨۜۘ۬۠ۚۗۨۚۜۧۙ";
                    i7 = i5;
                case -1163267398:
                    jSONArrayOptJSONArray = jSONObject.optJSONArray("inputpopups");
                    str = "۬ۤۙۗۦ۟ۜۨ۠ۥۡۦۡ۟ۙ۟ۥۤۚۗ۠ۢۦۚ۠ۦۚۢۜۘۖۡۦۘ۠ۙۥۛۚ۬ۧ۬۫۬ۖۤۘۖۘ";
                case -1114508057:
                    strOptString4 = jSONObjectOptJSONObject.optString("hint", "");
                    str = "۬ۧۛۨۙۥۘ۠ۦ۬ۤ۠ۡۜۧۥۘۦۤۨۡۦۡۚۚۚ۫ۖۧۛۢۘۘ";
                case -1055801156:
                    zOptBoolean = jSONObjectOptJSONObject.optBoolean("lock", false);
                    str = "ۖۚۜۘۥۙ۬ۧۨۤ۟ۧۦ۬ۖۘ۠ۖۥۢۦ۫۬ۙۦۢۢ۫ۖۙۖ";
                case -1053209548:
                    String str10 = "ۢۘۥۘۚۘۚۧۢۜۢۦۛۨۗۡۗ۫ۖۘۘۨۚ۫ۤۖۗۦۘ۠ۤۨ";
                    while (true) {
                        switch (str10.hashCode() ^ 103275646) {
                            case -1732100371:
                                break;
                            case -1043032429:
                                String str11 = "ۙ۟ۜۘۙۦۗۘۖۚۧ۬ۦۘۖۖۨۘ۬ۧۥ۬ۦۡۘ۠۟ۦۘۙۙۥۘۥۦۡۘ۟ۡۥۤۘۥۘ";
                                while (true) {
                                    switch (str11.hashCode() ^ (-144044965)) {
                                        case -1616579227:
                                            str11 = "۠ۥۨۘۜۡۙۜۗ۬ۡۨۛۥ۬ۖ۬ۨۦۘۧۤۧۥۤۥۖۘۥۥۚ۬ۛ۠ۤ۬ۛۢ";
                                        case -1188746934:
                                            str10 = "ۤ۟ۘۤۥۤۙۛۖۘۥ۫۬ۜۚۤۚۢۧۨۨۥۘ۫۫ۦ۫ۦۨۢ";
                                            break;
                                        case -1129704668:
                                            str10 = "ۛۦۦ۫ۙۤۜ۬ۧۤۙۥۢۜ۫۬ۖۘۗۦ۟ۨ۟ۡۗۨۤۗۛ۟۟ۤۡۚۨۢ";
                                            break;
                                        case 418301749:
                                            str11 = jSONArrayOptJSONArray3 != null ? "ۘۘۖۘۤۛۡۦۚۜۘۢۖۜۘۜۥۙۧۤۥۘۘۜۨۘۢۗۧۛۥۡۛۤۤۧۤۡ۫ۢۖ۠۬ۜۘۗ۬۠" : "ۤۥۗۘ۟ۘۘ۠ۙ۫۬ۨۡۧۥۧ۠ۜۘۘۨۛۙۙ۟ۚۙ۠۠ۧۦۥۡۥ۠ۖ۫ۦ۬ۙۢۧۨ";
                                    }
                                }
                                break;
                            case -1021996549:
                                str = "ۧ۬ۨۚ۟ۡۘ۠ۤۜۘ۫ۤۨۨ۠ۘۘ۟ۤۙۚۤۨۘۘۤۧۛۚۗۛ۬۠ۦۛۚۜۘۦۢ۬ۥۚۘۜۦۗۥۚۡۘ";
                                break;
                            case 1750796341:
                                str10 = "۟ۥۨۘۧۗۛۘۨۙۘۦۜۘۤ۫ۤۢۜ۟ۨۡۨ۠۠ۧۤۡ۠";
                        }
                    }
                    break;
                case -877684020:
                    arrayList2.add(jSONArrayOptJSONArray4.optString(i7));
                    str = "۠ۥ۟ۜۧۜۘۤۚ۬ۚۘۦۘ۠ۡۧۜۤۘۘۦ۟ۜۘۦۜۘ۟ۧ۫ۢۤۤ";
                case -822656218:
                    String str12 = "ۦۥ۠ۦۢۨۥۚۡۘۤۖۧۜۧۡۘ۫ۘۘۡۥۖۨۚۜۘۛۖۧۘ۠ۤۜۛۧ۬ۙۗۡۚۦۢ۬ۖۨۘۡۖۥۜۖۖۘۧ۬ۥۘۗۡۛ";
                    while (true) {
                        switch (str12.hashCode() ^ 1609360935) {
                            case -1381932784:
                                break;
                            case -754772741:
                                str = "ۨۨۨۘۖۧۘ۬ۦ۟ۖۧۜ۬۠ۜۚۜ۫۠ۨۥۢۖۖۘ۫ۜۨۛ۟ۖۤ۠ۘۡۙۛ";
                                break;
                            case 945328728:
                                str12 = "ۗۢۨۢۜۘۘۚۚ۟۠ۢۦۘۛۤۦۘۚۘ۬۟ۡ۟ۢۜۥۗ۟ۘۚۛۥۘۛۦۤۘۦۘۢۢۤۥۤۤۖۛۥۘۦۘۚۧۨ۫۬ۜۗ";
                            case 1875304292:
                                String str13 = "۫۬ۦۥۜۖۘۖۛۡ۟ۚۦ۬ۡۘ۠۬ۖۜۜۦۘۤۨۥۦۜۛۖۛۛۤۚۦۡۡ۬";
                                while (true) {
                                    switch (str13.hashCode() ^ (-1698758827)) {
                                        case -1289649263:
                                            str12 = "۟ۛۖۘ۬ۘۤ۫ۢ۠ۧۙۗۥۜۡۢۘۥۘ۫ۦۖ۟ۛۘۡۢۡۘۡ۟۠ۥ۫ۦ۫۠ۗ۟ۘۘ۬ۘۖۘ";
                                            break;
                                        case -1038794353:
                                            str12 = "۬ۧۘۧۙۖ۬ۡۖ۟ۦۥۖۙ۫۟۠ۛ۠ۖۖۜۡۧۘۜۗۙ۠ۙ";
                                            break;
                                        case 157667134:
                                            str13 = "ۜۛۛۧۚ۠ۦ۫ۘ۠ۥۖ۠ۥۗ۟ۢۨۘۨۖۜۘۜۧۖۘۤۙۢ۟ۛۥ۫۠ۛۛۢ";
                                        case 1197554621:
                                            str13 = jSONObjectOptJSONObject.optBoolean("enable", false) ? "۬ۧۖ۬۠ۧۤۤۖۢۨۘۡۜۦۧ۫ۛ۟ۙۜۘۦۦ۠ۙۜۤۖۖۢ" : "ۚۜۘۗ۬۫ۦ۠ۦۤ۬۫ۖۚۖۙۢۛۨۛۦۘۖۚۗۦۥۜۘۥۘ۠ۖۦۦۦۜ۫";
                                    }
                                }
                                break;
                        }
                    }
                    str = "ۘۨۡۘۧۥۗۦۡۧۧۙۢ۫ۥۢۗۖۡۘۤۖ۠ۨۗ۟۠ۥ۫ۤ۬ۨۘۙۢ۠۫ۖۦۘ";
                    break;
                case -750781914:
                    String str14 = "۬۟ۨۘۧۛۜ۠ۛ۠ۚۧۜ۬ۥۚۧۙۦۙ۟ۛ۠ۘ۬ۗۘۥ۫ۛۦۘ۫ۙۥ۠ۡۗۖۦۖۙۛۦۘ";
                    while (true) {
                        switch (str14.hashCode() ^ (-2119643711)) {
                            case -2083545281:
                                break;
                            case -324923238:
                                str = "ۚۙۗۦۦۖ۠ۨۗ۬ۚۢۛۛۛۦۚۨۜۢۜۘ۠ۦۥۘۖۛ۫ۖۥۨۘۛۛۛۦ۟۠ۧۤ۠ۨۤۘۖ۬ۧ۫ۥۘ";
                                break;
                            case 225570201:
                                str14 = "ۦۜۚۧۗ۬ۤۨۛ۫ۚۢۗۨۧۡۜۨۘ۬ۧۘ۟ۦۛ۫ۤۨ۟۠۬ۘۘۘۤۥۚ۫ۖۡۨ۫ۖۜۖۤۤۦۨۥۘۡۜۥۘ";
                            case 527916023:
                                String str15 = "ۡۥۗ۠ۥ۠ۥۚۖۥۡۘۢۢۡۜۖۦ۠۠ۧۖ۬ۨۘۖۤۜۙۙۘۘۚۤۢ۟ۤۖۘۥۡۦۙۤۥۧۗۦۘۙۡۧۘ";
                                while (true) {
                                    switch (str15.hashCode() ^ (-1637199798)) {
                                        case -1663363663:
                                            str15 = jSONArrayOptJSONArray != null ? "ۙۡ۬ۜ۬ۘۜۢ۟ۦۥۥۘۡۧۡۛ۟ۛۡۢۘۘۥۥ۫ۚۘۡۢۤ۬ۘۤۡ۫ۧ۟" : "۫ۦۡۧۧ۠۠۬۠ۤۗۡۤۨۖۘۘۢۨۘ۬ۧۘۘ۬۠ۙۢۛۡ۫ۜۙ۬ۡۘۨ۠ۢۚۥ۟۫ۙ۠ۖۧۨۜۧۥۡۚۛۦۤۤ";
                                        case 179616808:
                                            str14 = "ۢۙۜۘۡ۟ۤۤۤ۫ۥۡۜۘۜۢ۟۟ۡۧۘۖۖۙۢۡۘۙۧۖۘۤۥۧ۠۟۠۠ۜۤۡۗۛ۟ۧۗۧۘۚۚۚ";
                                            break;
                                        case 738936470:
                                            str15 = "ۙۡۛۜۥۨۖۖۧۚۤۦۘۨۤۤۦۘ۟ۛۨۧۚۖ۫۬ۤۜۗۛۢ";
                                        case 745653857:
                                            str14 = "ۙۡۦۘۧۙۤۘۙۗۡۡۗۚ۬ۙۢۧۨۜ۬۫ۨۨ۠۟۟ۗۛۡ۠ۘۡۨۙۚۙۜۗۘۖۖ۟";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    str = "۫ۡ۠ۖ۟ۨۗۖۥ۠ۧۘۘۥۙۚ۟ۧ۟ۘۗۖۘۚۚۥۘۙ۠ۥۘۘۜۦۘۚۖۨۘۜۥۤ";
                    break;
                case -593331134:
                    str = "ۤۜۘ۬ۜۚۗۙ۟ۨ۬ۥۘۖۜۚۧۢۘ۫ۛۢۖ۬ۢۢ۬۫۬ۘۘۥۦۗۨۛۘۘۢۛۨ۫۫ۘ۬ۥۧ۫ۖۢ";
                case -561679297:
                    str = "ۢۡۦۢۜۧ۫ۖۡۜۗ۫ۘۘۘۚۡۥۧۙۨۦۗۖۘۡۖۡۘۨۖۖۦۡۤ۠ۧ۫";
                case -505073847:
                    String str16 = "ۧۘ۫ۡ۫ۡ۟ۛ۬ۢۥۥ۟۬ۥۘۖۛۙۨ۟ۖۧۗۦۜۦۦۘۥ۟۟ۘۘۜ۟ۢۡۡۡۨ۠ۖ۬ۦ۬ۗۨۚۡ";
                    while (true) {
                        switch (str16.hashCode() ^ (-105442796)) {
                            case -1655137386:
                                str = "ۨۥۦۘۤۧ۠۫ۙ۟ۢۢۤ۬ۜۙۡۚۢ۫ۖۜۘۨۤ۟ۤۨۖۨۚۖۘۘۡۖۘ۟۠۠ۧۡۦۗۡ۬";
                                break;
                            case -947463153:
                                String str17 = "۠۠ۗۚۖۘۥۘۘۘۥۧۡۘ۟۬۟ۤۡۘۘۧۡۙۡۙۜۘۜ۫۬ۖۛۡۡۤۛۙ۫ۘۥۚۤۢۜۦۖۜۖۨ۫ۘ";
                                while (true) {
                                    switch (str17.hashCode() ^ (-206080507)) {
                                        case -318356063:
                                            str16 = "ۥۗ۬ۦۙۦۘۗۛۦۘۙۗۘۘۛۧۢۥۛ۟ۥۚ۫ۛۧۘۘۖۘۜۤۢۘ۫۫ۛۙۧۗ۬ۜ۫ۨۘۥۖۨۥۘۦۥۘ";
                                            break;
                                        case 297642753:
                                            str17 = jSONObject.optBoolean("enableinputPopups", false) ? "۟۫ۨۘ۫ۧۛۙۢۦۘۧۤۨۘۙۦۨۚ۟ۡ۬ۜۧۘۧۦۘۘۚۚۢ۫ۘ۟ۨ۬۟ۢۢۙۖۦۡ۫ۙۜۘ" : "ۚۡۥۘۜۦۜۘۡۛۗۙۘۧۚۘۤۢۦۤۤۢۘۛۘۧۘۚۢ۟۫ۥۢۡۤۧ۫ۨۡۘۛۢۖۛۧ۫ۨۙۡۤۜۨۘۙ۠ۗۤ۬ۘ";
                                        case 827834116:
                                            str17 = "ۤ۠۠ۧۧۥۘۗۖۨۘۖۨۦۘۗۤۚ۬ۖۜۚۦۨۛ۬۬ۢ۟ۦۗۧۜ";
                                        case 1357284020:
                                            str16 = "۬ۦۖ۠ۡۥۘۜۛ۬ۡۖۢۥ۟۬۫ۥۤۘۗۛۦ۫ۜۧۜ۠ۦۗۨۥۥۖۘۛۗۖۦۘۘ۫ۜۚۢۥ۟ۡۧۜۥۚۤ۫ۜ";
                                            break;
                                    }
                                }
                                break;
                            case 120491684:
                                break;
                            case 487124718:
                                str16 = "ۜۢۦۘۙۗۡ۫۬ۙۥ۬ۙۖ۠ۡۘۡۥۦۤۦۧۘ۬ۡ۠ۧۥ۠ۥۛ۬ۥۖ۫ۙۤۥۘۦۦ۟ۨۜۡۘ";
                        }
                    }
                    break;
                case -255525610:
                    strOptString6 = jSONObjectOptJSONObject.optString("maskColor", "#80000000");
                    str = "۠۟ۘ۬۟ۢۜۚۨۘۛۛ۬ۚۖ۫ۛۙ۟ۤۘۤۛۧۡۘۙۗ۬۟ۘۧ";
                case -20902970:
                    i6 = i7 + 1;
                    str = "۫ۢۥۘ۬۠ۥۘۦۘۨ۫ۛ۠۫ۛۨ۬ۥۗۖۢۙۦ۫ۢۡۦۘۘۙۛۛۨ۠ۦۖ۬۟۬ۥۘۙۙۥ";
                case -18079727:
                    str = "ۨ۟ۚۢۗۡۘۡۜۚ۟ۦۘ۫ۗۛۥۢۛۦ۠ۤۥۖۥۘۡۡۛ۬ۚۥ۬ۦۘۘۙۘۚۙۖۚ۬ۙۤۧۜۜۥ۠ۦۘۥۗ۠ۢ۟ۚ";
                    i7 = i6;
                case 19602344:
                    str = "ۡۡۧۘ۠۟ۢۡۢۛۢۖۙ۬ۖۤۗ۫۫۟۟ۨۘۦۖۜ۟ۘۜۘۢۘۦۘۨ۠ۦ۫ۥۧۘۜ۫ۤۧۗۤ۟۫ۦۘۦۡۜۘۙ۠ۡۙۢۦۘ";
                    i9 = i8;
                case 46446863:
                    i5 = 0;
                    str = "ۧ۫ۦۨۧۨۖۘۖ۫ۧ۠۟ۖۘۘۧۙۡۘۡۙ۫۬ۙۖۘۤۨۜۥ۫ۜۘۤۧۦ۟ۨۡۘۖۛ۬ۗۙۦ۫۫ۖۘۢ۟۠ۢۡۛۢۙۤ";
                case 219098517:
                    str = "ۤۜۘ۬ۜۚۗۙ۟ۨ۬ۥۘۖۜۚۧۢۘ۫ۛۢۖ۬ۢۢ۬۫۬ۘۘۥۦۗۨۛۘۘۢۛۨ۫۫ۘ۬ۥۧ۫ۖۢ";
                    i9 = i;
                case 326811761:
                    str = "ۙۧۜۘۜۙ۬ۚۛۖۦۧۥۖۤ۠ۛ۟ۖۛۛۨۘ۠۟ۡۘ۠ۘۘۤۛ۫ۙ۬ۥۘ۫۠ۚۥۦۡ۟۟ۜۘۡ۟۬ۗۘ۬ۘۥۗۘ۬ۢ";
                case 342380476:
                    jSONArrayOptJSONArray2 = jSONObjectOptJSONObject.optJSONArray("button");
                    str = "ۗۢ۫ۡۥۚۡۘۤۛۘ۫ۦۙ۠۬ۨۘۙۦۘۘ۫ۥۜۘۤۥۨۘۜ۟ۨۘۛۤ۬ۡۢۤ";
                case 372655234:
                    strOptString2 = jSONObjectOptJSONObject.optString("title", "");
                    str = "۟۟ۘۦۛ۬ۨ۠ۜۘۙ۬ۖ۫۫ۡۘۦۢۦۘۜ۠ۧ۠ۨۘ۬۬ۘۘۙۤۨۘۘۛ۟ۘۥۘۗۡۡۧ۫ۢ";
                case 507156879:
                    str = "ۨۗ۠ۦۥۦۘۧ۬۠۫ۤ۟ۘۜۖۘۚۜۡۖۖ۬ۥۧ۬ۡۖۡۘۗۢۙۗۧۙۢۘۤۘۨ۬۬۟۠ۤ۫ۡۨ۫ۥۘۢۡۥۘۨۙۨۘ";
                case 530887333:
                    i3 = i4 + 1;
                    str = "ۥ۫۫ۚۨۧۖۜ۫ۥ۟۫ۧۜۥۢۦ۟۫ۨۢۤۥۧۛۗۘۘۢۗ۬ۦ۫ۨ۟۟ۙۡۜۖ۬ۤۧ";
                case 642609099:
                    zOptBoolean2 = jSONObjectOptJSONObject.optBoolean("autopost", false);
                    str = "۠ۦۡۤۦۥ۠۫ۙ۫۫ۙۤۤۖ۠ۢ۟ۦۗ۠ۦۗۢۙ۬ۤ۠ۤۖ۠ۛۧ۫ۢۡۥ۠۬ۥۜۦ۫ۛۦۘۤ۠ۖۘ";
                case 650522533:
                    i = 0;
                    str = "ۥۨۚۦۘۢۙۛۤۡۨۘ۟ۥۧۘۙۦۖۖۥۦۥۘۘۘۚۡ۫ۧۥۧ۬ۧۡۚۧۖۘ";
                case 662506649:
                    strOptString5 = jSONObjectOptJSONObject.optString("backgroundColor", "#FFFFFF");
                    str = "ۦ۫۠ۚۖۜۘۗ۫ۧۧۦۤۙۚ۟ۗۙ۠ۘۡۤ۬۟۟۟ۨۧۙۥۧۘ۠ۧۡۘۨۨۗۘۦ۬ۜۖۗ";
                case 669734247:
                    str = "ۙۖۖۘۥ۬ۚۗۨۧۘ۫۠ۡۖۗۡۖۨۛۧ۟ۜۡۗۡۘۘۚۧۖۜۡۚۦۘۘۥۡۦۛۨۜۘ۬۠ۚۗۨۚۜۧۙ";
                case 837807183:
                    str = "ۨۗ۠ۦۥۦۘۧ۬۠۫ۤ۟ۘۜۖۘۚۜۡۖۖ۬ۥۧ۬ۡۖۡۘۗۢۙۗۧۙۢۘۤۘۨ۬۬۟۠ۤ۫ۡۨ۫ۥۘۢۡۥۘۨۙۨۘ";
                    i4 = i2;
                case 866809108:
                    i2 = 0;
                    str = "ۜۧۚۧۙ۟ۜۘۦۘ۠ۜۛ۬ۡۘ۬ۢۙۢ۠ۙۡۥۡۘ۬ۚۖۤۖۥۘ";
                case 1021670696:
                    arrayList = new ArrayList();
                    str = "ۘۛۧۜۦۨۘ۟ۘۨۘ۫ۜۧۘ۫۫ۥۘۚۙۘۘۜۘ۟ۨۘۙۘۜۨۥۡۜ۠۫ۢۚۥۨۘۨۙ۫ۗۥۡۖۙۨۘۗۜ۬";
                case 1147733724:
                    str = "ۥۖۘۘۥ۬ۘۗۤۘۖۢۥۘۧۘۥۘۚ۬ۥۢۚ۟ۢ۟ۡۘۙ۟ۚ۠ۖۜۘۧۖۧ۟ۨۛ";
                    i4 = i3;
                case 1190751600:
                    jSONArrayOptJSONArray4 = jSONObjectOptJSONObject.optJSONArray("black_list");
                    str = "۫ۦۥ۫۟ۤ۬ۗۥۘۡۙۥۘۛۦ۫ۥۦۦۦ۫ۘ۬۬ۥۖ۠ۗۦۙ۟ۡ۬ۘۘۤۤۖۘ۬ۛ۬ۧۛۘۘۤۗۛۘۚۨ";
                case 1603416406:
                    arrayList2 = new ArrayList();
                    str = "۟۫۬ۜۤۧۡۚۚۚۙ۟ۢۜۡۘۚۡۜۘۥۦۜۘ۟ۡۡۘۛۧۜۘۗۥۛۡۛۥ۫۫ۚۢ۟ۚۘۨۖ";
                case 1795944003:
                    jSONArrayOptJSONArray3 = jSONObjectOptJSONObject.optJSONArray("white_list");
                    str = "۫ۥۤۘۛۥ۠ۛۢۚ۟ۖۘ۬ۗۨۚۛۨۚ۟ۙۦۜۢۗۨۜۘۡۗ۬";
                case 1800962310:
                    String str18 = "۬ۧۥۘۨۚۤۢۗۦۚۗ۟ۧۢ۟ۖۗۡۘ۟ۥۧۧۢۢۦ۬ۜۗۡۗۜۗۜۘۢۘ۠";
                    while (true) {
                        switch (str18.hashCode() ^ 1649844793) {
                            case -1487393951:
                                str = "ۡ۫ۨۗۚۨ۠ۜۧۨۜۨۘۧۖۖۘۖۚۚ۟ۧ۟ۤۜۧۘ۠ۛۘ۫ۖۘ";
                                break;
                            case -592032093:
                                break;
                            case -289686336:
                                str18 = "ۚۦۥ۟ۙۦۚ۠ۡۥۖۘۗۙۢۚ۫۟۬ۦۨۘۦۘۦۦۖۦۘۦۧۡۦۘۨۨۧۘۤۗ۟۫۟ۜۘ";
                            case 1078461686:
                                String str19 = "ۥ۫ۚ۟ۧۦۘ۠ۚۖۚ۫ۜۗ۟ۤۙۤۧ۫ۛۤۥۛۜۨۥۡۘ۠۬ۥۘۦ۠۟ۥۖۘۜۜۧۘۨۥۚۘۖۜۘ۫ۖۧۘ";
                                while (true) {
                                    switch (str19.hashCode() ^ 197759138) {
                                        case -1343200157:
                                            str18 = "ۖۡۧۘ۬ۤۥ۠ۛۧۖۦۨۘۢۘۧۢ۟ۜۘۥ۠ۛۛ۫ۜۘ۟۟ۦۘۢۡۨ";
                                            break;
                                        case -359733766:
                                            str19 = "ۛۖۘۖۤۤۙۨۙۧۢۡۘۚ۬ۘۧۤۡۘۨۖۧۖۡ۟ۨ۠ۘۛۙۨۡۚۦۘۤ۟ۙۡۚ۟۠ۧۘۚۧۖۘۗۖ۬ۘۜ۫۫ۚۥ";
                                        case 47969276:
                                            str19 = i9 < jSONArrayOptJSONArray.length() ? "ۘ۠۬ۗۡۧۚۚ۠ۡ۠ۡۘۜۛۚۡۦۤ۟ۘ۠ۧۥۧۚ۫ۡۥۜۜ" : "ۙۙۥ۫ۘۧۤۗۧۙ۬ۗۧ۫ۖۘۤ۠ۘۘۧ۬ۡۘۥ۫۠ۜۛۘۚۦۙۥۧۙ۬ۖ۫ۛ۟ۖۘۨ۬۠";
                                        case 56513801:
                                            str18 = "ۦۘۤۚ۠ۤۛۡۜۗۨۙۚ۫۫۟ۚۖ۟ۙۦۘ۟ۘ۬ۥ۬ۘۥۚۨ۠ۦۘۘۗۥۧۙۧۗ۫۟ۚ۠ۙۨۢ۬۬";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    str = "۫ۡ۠ۖ۟ۨۗۖۥ۠ۧۘۘۥۙۚ۟ۧ۟ۘۗۖۘۚۚۥۘۙ۠ۥۘۘۜۦۘۚۖۨۘۜۥۤ";
                    break;
                case 1847439429:
                    i8 = i9 + 1;
                    str = "۟۟ۡۘ۠۠۟۠ۡۘۡۦۡۘۜۤ۫ۘۙۨۡۧۨۘ۟ۧۖۡ۬ۤۢۥۦۘ۟ۥۤۚۡ۬۬ۛۚ۫ۚۛ";
                case 1851377458:
                    strOptString = jSONObjectOptJSONObject.optString("id", "");
                    str = "ۤۖۢ۟ۨ۬ۖۥۜۗۙ۟ۛۥۦۘۚۥۡۘ۠ۢ۟ۙۢۦۦۢۢ۫ۦۗۜۤۙۚۖۧۘ";
            }
            return;
        }
    }
}
