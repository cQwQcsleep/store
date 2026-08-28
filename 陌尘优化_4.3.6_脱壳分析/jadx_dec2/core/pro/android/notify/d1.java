package core.pro.android.notify;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.res.Resources;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;
import gTBLD.dev.XSSTG.free.Utils;
import java.util.Set;
import org.json.JSONException;

/* loaded from: /workspace/unpacked/classes2.dex */
public final /* synthetic */ class d1 implements View.OnClickListener {
    public final int a;

    public /* synthetic */ d1(int i) {
        this.a = i;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) throws IllegalAccessException, JSONException, NoSuchFieldException, Resources.NotFoundException, SecurityException, IllegalArgumentException {
        switch (this.a) {
            case 0:
                Set<String> set = k2.closedPopupIds;
                return;
            case 1:
                Set<String> set2 = k2.closedPopupIds;
                Activity activityECt8jHZ4 = Utils.ECt8jHZ4();
                String str = "۫ۙۙۢۙۙۘ۠ۙۨۗۦۚۢۗ۠ۧۗۤ۠ۖۗ۟ۙۙۖۜۘۜۥۦۖۛ۟ۙ۫ۨ۟ۚۡۘۛۜ۫";
                while (true) {
                    switch (str.hashCode() ^ (-1106538678)) {
                        case -1655889923:
                            str = "ۡ۫۟ۤۨۨ۬ۢۥۗۢۖۚۤۨۘۨۤۘ۠۫ۙۤۥۧۘ۠ۜۥۤۧۡۘۡۡۚ۬ۨ۫";
                            break;
                        case 494138149:
                            activityECt8jHZ4.finish();
                            return;
                        case 1629753435:
                            return;
                        case 2000502087:
                            String str2 = "ۤۧۖۜۤۜۡ۠ۨۖۧۨ۟ۦ۬ۤۜۢ۬۬ۚ۫ۜۜ۟ۖۦۘ۬۬ۚۤۨۚۤۢۜ۠ۗۡۘۛۨ۬ۜ۫ۙۜۛ۠";
                            while (true) {
                                switch (str2.hashCode() ^ (-1798760878)) {
                                    case -1300622667:
                                        str = "ۧۡۧۘۥۛ۬ۜۖۡۦۚۘۦۗۦۘ۬ۘۢۘۚۜۘۜۡۦۘ۫ۜۘۙۧۨ۟ۖ۫ۡ۟ۨۘۡۘۘۦۥۘۘ";
                                        continue;
                                    case -574866778:
                                        if (activityECt8jHZ4 == null) {
                                            str2 = "ۘۡۦ۬۫ۡۙۚۨۘۘ۠ۗۡ۫ۛۥۤۢۥ۠ۤۦۧ۫ۦۤۛۘۘۧۦۧۦۢۚ";
                                            break;
                                        } else {
                                            str2 = "ۧ۫ۡ۬ۧۘۘۤۦۘۙۖ۟ۡۤۢۖۢۘۘۙۨۨ۟ۙۛ۟ۦۥۤۜۡۧۥۡۘۢۘۡ۫ۖۗۙۢۡۘۛ۟ۜۘ۬۠۠";
                                            break;
                                        }
                                    case 441609131:
                                        str = "ۦۖۡۘۢۤۘۘۨۢۡۘ۬۠ۢۙ۟ۘۘۗۦۛۚ۫۬ۛ۠ۦۢ۠۠۫ۥۖۡۡۘۢۡۜ";
                                        continue;
                                    case 1951570670:
                                        str2 = "۠ۡۡ۟ۙۙۦ۫ۨ۟ۜۗۘۧۜۥۤۤ۟ۦۦۘۨۢ۬ۜۡۢۙۥۗ";
                                        break;
                                }
                            }
                            break;
                    }
                }
                break;
            case 2:
                Set<String> set3 = k2.closedPopupIds;
                Activity activityECt8jHZ42 = Utils.ECt8jHZ4();
                String str3 = "ۙ۟ۙۜۨ۫ۛۧ۫ۢۜۖۦۢ۠ۖۧۦۦۢ۠۫ۖۖۘ۬۬ۘۘۗۥ۟۫ۧۛۙ۬ۨۘۙۤۧۚۖۡۘ۟۠ۚۘۖۦ۫۫۬ۗۗۥۘ";
                while (true) {
                    switch (str3.hashCode() ^ 385762761) {
                        case -1882214754:
                            k2.logToFloatingWindow("无法获取当前 Activity", "error");
                            return;
                        case 536789731:
                            View decorView = activityECt8jHZ42.getWindow().getDecorView();
                            StringBuilder sb = new StringBuilder();
                            k2.d(decorView, 0, sb);
                            k2.logToFloatingWindow("布局信息：\n" + ((Object) sb), "info");
                            return;
                        case 563155239:
                            str3 = "ۥۨۗ۫ۘۘۚۤۖۛۢۛۢۚۗۚۨ۬۠ۖۚۦۚ۫ۖۢ۬۠ۡۡۚ۬ۛۗۨۨۦۖۙ";
                            break;
                        case 700449536:
                            String str4 = "ۖۦ۟ۧۥۖۘ۠ۤۗۗۙۥۘۧۢۗۥۚۦۘۥۜۘۘ۟۬۬ۤۗۜۦۚۡ۠ۨۖۘۦۙ۬۠ۗ۟ۧ۠";
                            while (true) {
                                switch (str4.hashCode() ^ (-934825040)) {
                                    case -2056433553:
                                        str3 = "۫۟ۦ۫ۢۨۘۢۗ۟۬ۘۤ۫۫ۡۙۘۤۚۥۥۖۖۘ۠ۚ۟۬ۖ۬ۡۥ۬ۙۨ۫ۚۗۘۤۗۢ";
                                        continue;
                                    case -1293986332:
                                        if (activityECt8jHZ42 == null) {
                                            str4 = "ۦۥۧۘۦۘۧ۫ۡۗ۫۬ۜۘۤ۫۠ۚ۫ۢۨۡۚۧ۬ۘۡۖۗۨۧۜۘۧۛۤۗۜۤۧۤۥۧ۟ۙ";
                                            break;
                                        } else {
                                            str4 = "ۚ۠ۛۡۨۗۧۧ۠۠۬ۢ۟ۡۥۜۨۘۤۖۘۘۡۢۢۖ۫ۖۖۦۧۨۦ۬ۦۦۦ۟ۜۘۜ۠ۖۦۘۘۥۘۦ۟ۡۗۛۚۜ";
                                            break;
                                        }
                                    case 521328690:
                                        str3 = "ۜ۬ۘۘۖۤۤ۠ۤۜ۫ۨۘۘۤۗۙۥۙۡۘۜۚۜۖۜۛ۬۬ۥۘۙ۫ۦۘۦۛۨۙ۟۠";
                                        continue;
                                    case 1678625151:
                                        str4 = "۬۬ۚۙۡ۫ۛۨۘۛۗۥۘ۫ۜۘۢۢۨۤ۬۫ۚۤۗ۬۫۬۠ۘۘۖۘۡۘۡۛۘۘۥۧۢۜۘۙ";
                                        break;
                                }
                            }
                            break;
                    }
                }
                break;
            case 3:
                Set<String> set4 = k2.closedPopupIds;
                Activity activityECt8jHZ43 = Utils.ECt8jHZ4();
                String str5 = "۠ۦ۟۠ۤ۟ۥ۠۫ۚۚۖۦۗۛۧۜۛۥۧۘۤۦۙۢۘۛۢۦۚ";
                while (true) {
                    switch (str5.hashCode() ^ (-1510902646)) {
                        case -1118990979:
                            str5 = "ۚ۫۫ۨۢۖۘۡۘۨۡۛۗۨۛۗۜۛۜۛ۬ۨۘۦۘۤۨۥۘ۬ۦۘۘ۫۫ۜۘۢۚۥۘ";
                            break;
                        case 377683500:
                            try {
                                k2.logToFloatingWindow("布局JSON：\n" + k2.e(activityECt8jHZ43.getWindow().getDecorView(), 0).toString(2), "info");
                                return;
                            } catch (JSONException e) {
                                k2.logToFloatingWindow("布局JSON获取失败", "error");
                                throw new RuntimeException(e);
                            }
                        case 1263050635:
                            k2.logToFloatingWindow("无法获取当前 Activity", "error");
                            return;
                        case 1967315548:
                            String str6 = "ۦۡۡۘ۠ۤۡۧۗۧۨ۬۬ۧۢ۟ۢۜۖۤۥۖۥ۠ۛ۠۟۠ۢۥۥۘ۬ۥۚۧۘۖۘ۬ۙۖۤۗۥ";
                            while (true) {
                                switch (str6.hashCode() ^ 1298274030) {
                                    case -1303926936:
                                        str5 = "۟ۡۚۖ۬ۢۡۨۥۘۢۨۗۡ۫ۘۘ۫ۜۖۘۛۦۡۘۨۢۢۖۤ۠۠ۨ۫";
                                        continue;
                                    case -108825215:
                                        str6 = "ۥۛۧۜۦۢۙۤۢۦۖۛۙۨۖۘۖۗۨۙۙۚۡۖۢۘۗۢۦۗ";
                                        break;
                                    case 1251030759:
                                        str5 = "ۨۛۢۤۦۘۛۢۨۖ۫ۨۨۜۡۘۗۘۦۘۘۖ۫ۜۨۤۘۧۜۘۛۖ۫ۗۙۡۘ۟۬ۗۧۨۚۚۛۤۛۧۨ۫ۘۡۜۛۨۘۥۦۡ";
                                        continue;
                                    case 1645015083:
                                        if (activityECt8jHZ43 == null) {
                                            str6 = "ۧۥۦۡۚ۫ۚۨ۠ۡۖۡۘۚۜۛ۬ۦۧۘۥۦۗ۠ۘۦۖ۫ۙۜۖۢۚ۫ۢۧ۠ۧ";
                                            break;
                                        } else {
                                            str6 = "ۚۤ۬ۨۖۧ۬ۦۙ۬ۤۡۘۧۙ۟ۘۢۡۦۨۖۥۛۜۥۥۜۗ۠۠ۘۛ۫ۛۧۖۥ۟ۛۜۚ";
                                            break;
                                        }
                                }
                            }
                            break;
                    }
                }
                break;
            case 4:
                Set<String> set5 = k2.closedPopupIds;
                Activity activityECt8jHZ44 = Utils.ECt8jHZ4();
                String str7 = "ۛۡۨۨۘ۬ۛۡۙۤۘۦۘۦۚ۫ۜۚۖۘۡ۟ۖۨۢۥۘ۟ۨۡۘ۬ۤۥۘ۟۟ۡ۫ۥۢۚۦۦ۬ۙۜۨ۬ۨۛۦۥۘۙ۬۠۬۠ۧ";
                while (true) {
                    switch (str7.hashCode() ^ 528249716) {
                        case -1633569154:
                            k2.logToFloatingWindow("无法获取当前 Activity", "error");
                            return;
                        case -946443029:
                            str7 = "۟ۛۥۘ۟ۡۧۥۚۛۛ۟ۦۘۚۘۜ۟ۦۖۘۘۗۤ۬ۗ۟۬ۥۢ۠ۙ۟";
                            break;
                        case -251229696:
                            String str8 = "۬ۗۡۘۤ۠ۡۡۥۗۨۖۧۘ۟ۜ۫ۘۢۢۢۧ۟ۜۜۘۘۛ۟ۘۘۗۧۗ";
                            while (true) {
                                switch (str8.hashCode() ^ 1837345923) {
                                    case -1994858797:
                                        str8 = "۟۫۫ۨ۠ۛۛۚ۟ۘۚۙۢۚۦ۫ۤۢۘ۠ۨۘۖۥۤۜۘۨۘۦۖۜۢ۫ۜۘۙۖ۟ۚۖۖۘۛۚۘ";
                                        break;
                                    case -1837042476:
                                        str7 = "ۧۘۥۚۡۜ۬ۚۚۤۦۚۖۜۖۘۨۥ۫ۗۚۥۘۢۡ۫ۖ۬ۘۘ۟ۚۛۦ۠ۧۤ۫ۦ";
                                        continue;
                                    case -1248312351:
                                        if (activityECt8jHZ44 == null) {
                                            str8 = "ۢۘۚۧۙ۫ۙۦۚۦۖۨۢۜۨۡۨۛۗ۠۬ۙۜ۬ۢۙ۬ۧۨۛ";
                                            break;
                                        } else {
                                            str8 = "۬۠ۢۛۤۗۗۛۘۘۛۦۘۘۚ۫ۦۘ۬۬ۥۙۛۡۛ۫ۢ۟ۛۘۘۜۙۚۤۤۖۘۡۧ۬ۤۦۚۡۢ";
                                            break;
                                        }
                                    case 318354162:
                                        str7 = "۟۫ۦۘۛۚ۬ۙ۫ۖۘ۟ۗۥۡۘۘۘۜۜۘۤۖۥۘ۫ۙۖۘ۠ۙ۠ۧۙۚۙۦۤ۠ۡۖۨ۠۫ۡۘۘۧۡۖۡ۬ۥۘۗۢ۠ۜۘۚ";
                                        continue;
                                }
                            }
                            break;
                        case -234172999:
                            k2.a(activityECt8jHZ44.getWindow().getDecorView().getRootView());
                            k2.logToFloatingWindow("✅ 布局调试事件绑定完成，部分控件原有的点击可能会失效，重启APP恢复", "ok");
                            return;
                    }
                }
                break;
            case 5:
                Set<String> set6 = k2.closedPopupIds;
                Activity activityECt8jHZ45 = Utils.ECt8jHZ4();
                String str9 = "۟ۥۜۦۡۡۤ۠ۤۡۥۦۤۖۘۜۥ۟ۢۧۥۘ۠ۗۧۡۤۨۘۦۡۥۘۗ۫ۡۡۚۦۡۡ۫۫ۚۦۘۤۨۡ۠ۖۦۘۙۤۨۘۨ۟";
                while (true) {
                    switch (str9.hashCode() ^ 347070900) {
                        case -1906606154:
                            return;
                        case -1798911979:
                            String name = activityECt8jHZ45.getClass().getName();
                            ((ClipboardManager) activityECt8jHZ45.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText("ActivityClass", name));
                            Toast.makeText(activityECt8jHZ45, h.e("wQjA2vGV5UiSWMOEsIiNL5gl\n", "JL9yP1UYAMA=\n", new StringBuilder(), name), 0).show();
                            return;
                        case 1073513554:
                            str9 = "ۤۥۦۜۥۘۤۢۙۙۧۜۨ۟۬ۦۥۥۨۤۤۖ۠ۨۧۢۢۤۜۚۦۗۧۡۧۖۚ۠ۘۘۖۥۥۘ";
                            break;
                        case 1950336766:
                            String str10 = "ۧۨۥۜۡۤۚۨۡۛۛۗۖۘۥۘۦ۟ۥ۟ۛۨۥۜۘۦۧ۬ۘۧۖ۬ۗ۠ۚۘۘۥ۟ۤ۫۟۟۬ۙۦۘۘ۬";
                            while (true) {
                                switch (str10.hashCode() ^ (-800475579)) {
                                    case -110671179:
                                        str9 = "ۜۦۗۤ۬ۨۛ۠ۗ۠ۢۨۘۥۥۥۥۖۘۘۜۖۜۤۖۧۘ۠۬ۘۛۤۥۘۜ۬ۗۙ۬ۥۘۜۘۙۢ۬ۙۦ۫۟۟ۦۨۘ۟۫۬ۖۧۙ";
                                        continue;
                                    case 661249118:
                                        str9 = "۬ۛۦۘۖۘۥۙۧۨۢ۟ۢۗۥۧۜ۟ۛۨۖۘۨ۫ۘۨۥۤۖۖۘۚۗۜۙۘۥ۟ۖۜۘ۬ۡۜۖۚۨۘ۬ۙ۬";
                                        continue;
                                    case 954259975:
                                        if (activityECt8jHZ45 == null) {
                                            str10 = "ۧ۠ۘۘ۠ۢۛۥۡ۟ۛۖۧۥ۠ۘۘۦۜۘۖۛۘۡۡۧۘۛۧۦۘۥۨۡۡ۬ۘ۟ۥۡۧۛۚۦۖ۬۟ۛۛۖۜۗ";
                                            break;
                                        } else {
                                            str10 = "۠ۥ۟ۗۡۜۧۛۦ۫ۗۡۘۚۙۥۚۚۦۘۧۖۧۘۧۙۥۛۤ۠ۗۤۚۥۛۦۛ۟ۡۜۘۘۥۗ۬";
                                            break;
                                        }
                                    case 2090075132:
                                        str10 = "ۤۨۖۥۨۥۘۡ۟ۜۘۦۘۚۧۚۚ۠ۥۦۘ۠ۦۤۨ۬ۚۘۜۙۨۤۖۘۛۢۖۖ۫ۡۨۛۖۗۥ۬";
                                        break;
                                }
                            }
                            break;
                    }
                }
                break;
            case 6:
                LinearLayout linearLayout = k2.f;
                String str11 = "ۗ۟ۛۦ۬ۡۘۘۛۥۘۨۙۚۡۧۘۘۛۚۘۨ۫ۘۚۖۦۘۧ۬۬ۡۨۧۖۦۚ۬ۡۧۗۧۧۜۨۜۘ";
                while (true) {
                    switch (str11.hashCode() ^ (-698859221)) {
                        case -1854689108:
                            return;
                        case -1440139264:
                            String str12 = "ۛۙۨۧۚۧ۫ۛۙ۬ۛۤ۫ۙۨۘۨ۟ۙ۟۬ۤۙۧۚۢۘۦۘ۟۫ۗ۠ۤۡۖ۬ۜۦ۫۫ۖۤ۟";
                            while (true) {
                                switch (str12.hashCode() ^ (-1443294057)) {
                                    case -1375473937:
                                        str11 = "ۨۜۡۘۘۢۦۘۢۚۘۗۛۧۜۦۙۖۜۘ۟۟ۦۦۦۛۗۘۘۖ۫ۙۜۘۦۜ۠ۘۘۖۨۘۘۙۛۗۙۡۘ۟ۨۦۘ";
                                        continue;
                                    case 661111432:
                                        str12 = "ۤۙۚۖۜۨۛۥۜۘۡ۫ۡۘ۬ۡۨۘ۫ۘۗۤۗۙۤۗۦۚۜۘۚ۫۠ۗۘۥۘۤۤۗۛ۟ۘۘ۫ۖۜۘۢ۟۫ۧۜۢۡۥۡۘۜ۟ۜ";
                                        break;
                                    case 1309647875:
                                        if (linearLayout == null) {
                                            str12 = "۠ۡۜۘۘۗۨۦۦۙۘ۟ۨۘ۟ۡۖ۬ۡۡۘۧۥۜۘ۫ۥۧۘ۠ۖۗ۠ۖۨۘ۠۟ۗۢۖۖۡۛ۬ۥۛۘۘ";
                                            break;
                                        } else {
                                            str12 = "۟ۧۥۡ۠ۨۘ۬ۥۢۖۘۗ۫ۗ۠ۧ۠۫ۥ۫ۚ۫ۨۘۘۧۦۚۗۖ";
                                            break;
                                        }
                                    case 1873784687:
                                        str11 = "ۡۚۨ۬ۤۗۢۧۘۘۜۨۥۛۦۡ۬ۡۘ۬ۤۥۘ۟ۨۚۖۥۡۢۗۧۚۢۚۜ۠ۡۤۦ۫ۤۧ۫ۡۦۜۘۚۖ۟ۦۡ۬ۢ۬۠";
                                        continue;
                                }
                            }
                            break;
                        case -1411384413:
                            str11 = "ۨۛۦۘۙۖ۠ۘ۟ۛ۫ۚۖۘۥۢۜۘۦ۠ۢۥۘۜۘۦۦۥۘۥۡۨۘۘۜۦۜۛۡۗۙۤۤۚۜۘ۬ۢۘۘ";
                            break;
                        case 2079139551:
                            linearLayout.removeAllViews();
                            return;
                    }
                }
                break;
            case 7:
                String str13 = "ۤۧۨۘۙۨۜۜۜۧۘۜ۬ۨۘۛۨۗۘۧۥۘ۬ۦۘۘۥۨۘۜۚۦ۠۠ۜۛۢ۠ۧ۟ۜۘۢۤۗۙ۠ۦۘ";
                while (true) {
                    switch (str13.hashCode() ^ (-16595251)) {
                        case -1328821172:
                            ((ScrollView) Utils.p.getParent()).setVisibility(0);
                            break;
                        case 48245770:
                            str13 = "ۙۗۧۨۧۦۦۡۧۘ۫ۤۗۖۦۧۘۨۡۗ۟ۛۦۘۢۙۛۛۧ۫ۨۧ۠ۚ۬ۦۤۖۘ";
                            continue;
                        case 2018568879:
                            break;
                        case 2122338434:
                            String str14 = "ۢۜۧۚ۟۬۠ۘۚ۫ۚۘۘۤۗ۠ۚ۟ۡۥۤۡۙۗۢۘ۠ۖۤ۬ۖۥۧۘۙۨۦۛۡ۟ۙۦۖۘ";
                            while (true) {
                                switch (str14.hashCode() ^ (-1491571773)) {
                                    case -1218928760:
                                        str13 = "ۧۘۖۘۗۦۖۘۘۗۜ۬ۜ۟ۡۡۛۥۥۙۤ۫ۜۡۖۘۧ۫ۦۡۖ۟ۖ۫۟ۥۦۘۘۖ۬ۧۗ۠ۘۡۥۗ۫۫ۥۘ";
                                        continue;
                                        continue;
                                    case 169890492:
                                        if (!(Utils.p.getParent() instanceof ScrollView)) {
                                            str14 = "ۨۢۜ۫ۘۧۘۥۢۖ۠ۧ۟ۙۘۘۙۛۜۦۜۛۧۦۡۘۙ۟ۥۘۢۤۛۨ۟ۥۘۨۢۘ۬۠ۙ۬ۦ۫";
                                            break;
                                        } else {
                                            str14 = "ۘ۠ۡۙۥۖۘۥۥ۫۬ۧۥۦۙۜۘ۫ۡۧۚۙۙ۠ۚۨۘۙۗۚۗۥۖۢۚ۬ۢۨۢۤۛ۟ۡۦ۫۫ۖۧۗ۫ۥۚۖۘۛۨۧۘ";
                                            break;
                                        }
                                    case 1890748888:
                                        str13 = "ۗ۟ۧۚۧۘۛ۫ۘۚۧۘۘ۠ۥۚ۫۠۠ۤۥۘۘۥۛ۫ۧۡۥۘ۬ۤۜ";
                                        continue;
                                    case 2144244296:
                                        str14 = "ۙۢۘۛۗۗۦۙۘۘۘ۫ۤۛۧۙۜۜۜ۬ۧۤۖ۬ۛۢۗۦۘۛ۠ۙۧۤۤۢ۠۠";
                                        break;
                                }
                            }
                            break;
                    }
                }
                String str15 = "۟ۤۥۖۧ۬ۡۚۛۜۢۛۡۦۦ۫ۨۘۥۙۗۡ۫ۖ۫ۦۘۨ۫ۙۜۦۜۧۗۡۘۚۘ۬ۚۧۨۘ۫۟ۖۨۙۛ";
                while (true) {
                    switch (str15.hashCode() ^ (-1878973903)) {
                        case -1662497560:
                            str15 = "ۛ۠ۛۗۢ۠ۜۢۨۘۥۚۖۘۖۧۘۘۢۥۢۤۚۖۗۛۙۤۚۢۥۗ۟ۖۘۘ۠۠ۥۘۧۘ۬ۦۨۥۘۢۡۥ۫ۗۤ۠ۡۖۤۙ۬";
                            break;
                        case -1637978974:
                            ((ScrollView) Utils.q.getParent()).setVisibility(8);
                            return;
                        case 179253742:
                            return;
                        case 660499957:
                            String str16 = "ۨۛۜۙۡۖۘ۬ۚۧ۬ۡ۫۠ۗۘۤۡۖۢۤۚ۫ۘۨ۠ۡۤۧۗۤۤۙۨۜ۟۫ۙ۠ۤۤۛ۫۬ۖۘۡ۠۬ۤۨۛۨۘ۠";
                            while (true) {
                                switch (str16.hashCode() ^ 1513935356) {
                                    case -1883236408:
                                        str16 = "ۜۖۡۡ۠ۘۤۖۜۦۘ۠ۚۢ۬ۗOۥۜ۬ۚۤۖۖ۫ۨۧۛۤۚۥۛۙۜ۬ۙ۟ۨۘ۫ۧ۠۬ۢ۬۟۟ۡ۟ۜۥۘ";
                                        break;
                                    case -1511658806:
                                        str15 = "۫۫ۜۘۥۖۢۗۚۜۗۚۧۤۖۚۨۨۘ۬ۛۧۚ۟ۨ۟ۤ۠ۨ۠۠ۧ۫ۗۧ۬ۖۦۡۧۘ۟ۘۤ";
                                        continue;
                                    case -343297796:
                                        if (!(Utils.q.getParent() instanceof ScrollView)) {
                                            str16 = "ۦ۟ۨۨۧۖۘۦۡ۠۟ۤۨۘ۟ۙۡۘۥۥ۠ۘۢۦۘۡۧۡۘۡۘۗۦۤۡۘۨۦۙۨۨۢۛۢ۫ۧۦۙۘۥۘ۟ۚۨۘ";
                                            break;
                                        } else {
                                            str16 = "۬ۖۘۘۚ۠ۘ۠ۤ۫ۢۦۙۨ۬ۦ۟ۘ۬ۜۧۧۧ۠ۨ۠ۙ۬ۥۘۨۖۚۧ۠ۡۢۗۦ۬ۖ۫ۜۘ";
                                            break;
                                        }
                                    case 1726859070:
                                        str15 = "۫ۛۥۘۢۙ۠ۗۜۘۡۨۘۧۥ۬۠ۨ۫ۥۘۨ۬ۚۦ۟۠ۙۚۨۚۢۤ۫۬ۛۧۡ۫ۥ۬ۙۙ۫۫۟ۗۘۡۘۚ۫ۗ";
                                        continue;
                                }
                            }
                            break;
                    }
                }
                break;
            default:
                LinearLayout linearLayout2 = Utils.p;
                String str17 = "ۥۖۜۛۡۦۘۛۗۥۘۘۖ۬۬ۚۦۘۨ۠ۦۗ۫ۦ۠ۘۗۖۧ۟ۦۜۤۦ۠ۗۗۤۥ";
                while (true) {
                    switch (str17.hashCode() ^ 414290514) {
                        case -1422573368:
                            String str18 = "ۖ۬ۥۘۘۗۙ۟ۗ۟ۡۧۨۘۢۚ۟ۛۡۖۡ۟۬۠ۜۨۘۨۘۡ۟ۜۗۨۤۗۥۙ";
                            while (true) {
                                switch (str18.hashCode() ^ 502244187) {
                                    case -1969093268:
                                        str17 = "۟ۡۥۘۖۙۡۛ۫ۤۤۚۢۥ۫ۥۘۡۚۖۖ۬ۥۘ۬ۗۚۚۗۨۡۘ";
                                        continue;
                                    case 1091803546:
                                        if (linearLayout2 == null) {
                                            str18 = "۟ۡۘۤ۬ۦۥۘۘۘۛۜ۟ۜ۠ۥ۫ۚۢ۫ۗۥۘۘۚۥۘۥ۟۠ۨۢۘۘ۬ۘۜۘ۟ۢۨۘۙۢۖۘۗ۟ۘۦۗۗۡ۟ۨ";
                                            break;
                                        } else {
                                            str18 = "ۜۨۜۡۚۘ۟۠۠ۘۖۧۘۡۤۚ۟ۜ۫۠ۛۘۜۦۖۘۤ۫۫ۖ۬ۧۗۥۛۦ۟ۛۢۤۖۧۡۙۗۚۛ۫ۨ۟ۡۤ۫ۢۚۨ";
                                            break;
                                        }
                                    case 1174788681:
                                        str18 = "ۘۥۜۘۨۢۛۛ۫۠ۙۧۡۘ۫ۦۦۘۘۘۨ۠ۙۜۘۘ۠ۡۘۡۦۦۦۜۖۥۘۖۚۤ";
                                        break;
                                    case 1766850279:
                                        str17 = "۟ۤۜۘۢ۬ۤۛۜۜۘ۬ۚۛۧ۟ۤۢۘۡۚۖۘ۠ۢۦ۫ۘۜۘ۫ۦۢ";
                                        continue;
                                }
                            }
                            break;
                        case -1226053299:
                            break;
                        case -729819366:
                            linearLayout2.removeAllViews();
                            break;
                        case 1068832370:
                            str17 = "ۗۖۙ۠ۦۧۗ۟ۦۘۡ۫ۙۨ۬۠ۤۚۖۘ۬ۖۦۤ۠ۡ۟ۜۥۘۘۗۨۘ۬۫ۨۖۙۦ";
                            break;
                    }
                }
                Utils.m.clear();
                return;
        }
    }
}
