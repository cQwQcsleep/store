package core.pro.android.notify;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;
import gTBLD.dev.XSSTG.free.Utils;
import java.util.Set;

/* loaded from: /workspace/unpacked/classes2.dex */
public final /* synthetic */ class i1 implements View.OnClickListener {
    public final int a = 0;
    public final int b;
    public final String c;
    public final Activity d;
    public final String e;
    public final String f;

    public /* synthetic */ i1(int i, Activity activity, String str, String str2, String str3) {
        this.b = i;
        this.d = activity;
        this.c = str;
        this.e = str2;
        this.f = str3;
    }

    public /* synthetic */ i1(int i, String str, Activity activity, String str2, String str3) {
        this.b = i;
        this.c = str;
        this.d = activity;
        this.e = str2;
        this.f = str3;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int i = 1;
        String str = this.f;
        String str2 = this.e;
        Activity activity = this.d;
        String str3 = this.c;
        int i2 = this.b;
        switch (this.a) {
            case 0:
                Set<String> set = k2.closedPopupIds;
                String str4 = "۟۟ۗۧۡۛۤۜۥۖۨۛۦۚۨ۫۬ۛ۟ۧۘۗۡ۟ۦۨ۠ۘۜۘۡۥۥۘۤۜۚۦۦۗۖۖۘۨۜۜ۫ۛۘۘ۟ۡۙۤۥۘۘ";
                while (true) {
                    switch (str4.hashCode() ^ 1834873973) {
                        case -883992204:
                            Toast.makeText(activity, "无动作", 0).show();
                            break;
                        case 495975709:
                            str4 = "ۨۡ۬۬۫ۚۜۧۚۦۘۦۘۗۥۥۘۦ۟ۥۘۡ۟ۛۦۖۘۘۢۢۖۧ۫ۥۘۨۡۖ۟ۧ۬";
                            break;
                        case 614726320:
                            String str5 = "ۧۦۨ۬ۘۨۨۜۥۘ۬ۢۡۘۤۥۙۨۖۨۜۜۧۘۖ۠ۨۘۢۛ۟ۗ۬ۙ۫ۚ۫۫ۤۦۘۢۧۥۘۘۥ۬";
                            while (true) {
                                switch (str5.hashCode() ^ 1639027405) {
                                    case -1997400801:
                                        if (i2 == 0) {
                                            str5 = "۫ۡۦۦ۠۬ۚ۟ۘۙ۬ۨۘۘۥۜۡۤۜۘۤۥ۫ۖۢۘۡۜۦۧ۬ۦۘ۟۫ۨۗۙۨۘۛۛۙۖ۫ۥۘۙ۫ۡۘۧۗ۠";
                                            break;
                                        } else {
                                            str5 = "ۥۦۧۙ۟۬۬ۡۘۡ۬ۘۘۗۥۦ۟ۢ۠ۦۤۥۚ۠ۘۖ۟ۛۚ۫ۜۙ۬ۡ۬۬ۥ";
                                            break;
                                        }
                                    case -441426429:
                                        str4 = "ۛۗۨۘۡ۠ۚۚۚۡۘۤۢ۠ۖۙۤ۠۫ۗ۟ۡۥۘ۫ۘۗۧ۬ۨۦۨۡۘۘ۫ۤۡۜۧۜۢۦۤۜۦۘ";
                                        continue;
                                    case -145251517:
                                        str5 = "۟ۢۘۘۤۦۨۚۖ۬ۨۜۧۜۗۘۘۗۦۜۘۗۤۗ۠۫ۖۥۘۜۘۚۧ۫";
                                        break;
                                    case -21406001:
                                        str4 = "۬ۛۜۤ۟ۘۛۛۜۘۤۥۨۧۨۘۛۥۘۡۘۚۥۡۜ۬ۘۧۘۦۤۦ۬ۜ۫ۤۖۙ";
                                        continue;
                                }
                            }
                            break;
                        case 1642700426:
                            String str6 = "ۦ۠ۡۦ۫ۡۘۜۦۨۦۦۥۨۘۤۦۦۖۘۚۧ۫ۡ۬ۢۥۥۙۢۥۥۛۗۨۗۘۤ۠ۖۖۚ۟ۖۘۧ۟ۨۘۛۦۜۘۡۥۥۘۜۦ۬";
                            while (true) {
                                switch (str6.hashCode() ^ (-329882065)) {
                                    case -1868404735:
                                        str6 = "ۦ۟ۘۘۙۡۡۘۦۘۨۛۡ۬ۙۙۡۘ۬ۢۡ۠ۗۦۖۨ۟۠ۖۖۛۥ۬";
                                        break;
                                    case -1706588381:
                                        String str7 = "۬ۨۡۘۥۢ۬۬ۘ۬۠۫ۥۜۛۤ۠ۜۡۘۡۨۘۙ۫ۚۡۗۦۖۢۘۚ۫ۖۚۖۘ";
                                        while (true) {
                                            switch (str7.hashCode() ^ 1757328408) {
                                                case -866690590:
                                                    str7 = "ۙ۠ۨۤۤۦۘ۟ۥۥۧۧۡۘۜ۟۟ۡۧۘۖ۫ۘۘۜۡۘۙ۟ۥۘۦۡۤۘۖۡ۠ۗۖۘۙۡۘۤ۟";
                                                    break;
                                                case -325630110:
                                                    String str8 = "ۘۗۗۧۥۡۘۗۛۥۘۨ۟ۜۚۚۘۥ۟ۘۘۜۥ۬ۡۥۘۛ۫ۡۘۛۤۗ۠ۚۨۘۧۦ۫۠۠ۦۗ۫۬۠ۨۙ۠ۤۢ";
                                                    while (true) {
                                                        switch (str8.hashCode() ^ (-1024977356)) {
                                                            case -1715226040:
                                                                String str9 = "ۧۙۜۚۚۢۘۡۘ۫ۦ۠ۤۡۤۤۛۦۘ۠ۢۢۖۥۨۘۥۖۡۘۚۤۥۘۙۖۜ۫ۢۧۧۘۡۜۤ۟";
                                                                while (true) {
                                                                    switch (str9.hashCode() ^ (-411598411)) {
                                                                        case 275952783:
                                                                            if (i2 == 3) {
                                                                                str9 = "۟ۛۦۢۖۧۘۨ۠ۦۘۙ۬ۛۨ۟ۢۥۖۡۘۤۘۨۘۖۜۛۨۦۡۦۤۨۘۨ۫۬ۢۨۚۘۦۨۡۙ";
                                                                                break;
                                                                            } else {
                                                                                str9 = "ۤۧۡ۬۟ۘۗۡ۠۠ۤۦۜۡۡۦۖ۟۠ۢ۫ۤۨۘۥۢ۟ۜ۠ۗ۟ۢۜۗۥۖۡۦۚۛۦۦۘ";
                                                                                break;
                                                                            }
                                                                        case 342737526:
                                                                            str8 = "ۧۗۘۘۡۜۥۘۚۨۖۘۦۢۡۘۡۧۘ۫ۨۡۘۨۙۜۘۨۚۦ۫ۨۜۗۚ۟ۨ۠ۨۖۤۜۚۘۗۗۛۥۘۛۚۜۘۨۘ۟";
                                                                            continue;
                                                                        case 824219946:
                                                                            str8 = "۟ۤۜۖۦۛۤ۫ۜۨ۠۫ۜۚۗ۫ۚۢ۟۫ۚۧۖ۟ۦۖۨۘۘۖۦ۫ۗۛۥۘ";
                                                                            continue;
                                                                        case 1984688436:
                                                                            str9 = "ۘۘۦۘۛۘۡۘۨ۟ۖۛۢۤۜۨۨۜ۟ۙۢۢۡۘ۫ۡۜۙۗۚۥۚۥۘۢ۬ۘ۠ۤۘۙۜۜۘۥۚ۬";
                                                                            break;
                                                                    }
                                                                }
                                                                break;
                                                            case -1173371095:
                                                                String str10 = "۬ۘ۬ۧۖۡۗۥۘۜۨۜۢ۟ۤۛۨۦۡۖ۫ۙۡ۬ۜۧۙۨۨۧۘۢۚۦۡۖۢ";
                                                                while (true) {
                                                                    switch (str10.hashCode() ^ 1006380921) {
                                                                        case -2147436985:
                                                                            str10 = "ۜۜۧۛۤۖۗۡ۠۬۬ۜۗۖۧۘۚ۫۠۟ۖۧۙۖۗۘۚۡۘۦۧۗ۟ۦۡۘ۬ۥ۟ۘۥۥۘۥۡۛ۠ۘۦۦ۠۠ۖ۫۫ۖ۬ۖ";
                                                                            break;
                                                                        case -1843376865:
                                                                            String str11 = "ۦۦۤۗ۬ۚۦۢۘۘۛۘۨۘۗۚۜۧۤۗۚ۟ۥ۠ۨۘۘ۫ۤۦۤۡۘۜۗۚ۟ۧۛۨۜۡۗۖۢ";
                                                                            while (true) {
                                                                                switch (str11.hashCode() ^ (-1880025032)) {
                                                                                    case -1561146426:
                                                                                        str11 = "۟۠ۙۨ۠ۘۘۙۗۤۖ۫ۖۘۙۛۤۢ۠ۤ۫ۚۢۨۖۥۚۥۘۡۤۨۘ۠ۦۡۖۤۙ";
                                                                                        break;
                                                                                    case 1063185677:
                                                                                        if (i2 == 4) {
                                                                                            str11 = "ۘۗۚۖ۫ۜۘۘۜۧۘۥۢۨۘۡۦۤۘۗۘۘۗۦۨۘۦۧۖ۠ۧ۫ۚۚۥۘۗۧۙۘۗۡۦۘۧۘ۫ۖۥۖۤ۬ۦۗ۬ۜۛۨۗۘ";
                                                                                            break;
                                                                                        } else {
                                                                                            str11 = "ۥۥۙۛ۠ۜۘۧۛۦ۬ۛۛۜۘۧۘۦۥۦۘۨۦ۫ۜ۫ۨۖ۫ۗۡۙۖۘ";
                                                                                            break;
                                                                                        }
                                                                                    case 1758073432:
                                                                                        str10 = "ۦۨۢۧۧۦۘۜ۫ۖۛۥ۫ۘ۠ۡۘۢۗۘۘۖۛۜۘ۟ۥۛ۟ۖۥۦۧۜۘۤۙ۠ۜ۬ۜۘۘۢۢۜ۫ۡۘۦ۟ۡۘۨۙۦۧۚۜۘۗۢۦۘ";
                                                                                        continue;
                                                                                    case 2036917091:
                                                                                        str10 = "۟ۚ۟ۚ۫ۦۘۦۘۨ۬ۢۜۘۡۚۧۧۡ۠ۗۢۦۘۘۡۥ۟ۦۘۚۙۜۘ۫ۘۡۘۘ۫ۜۘۥ۫ۨ۬۫ۦۘ۬ۛۦ۠ۛۛۨۗۗۜۛۢ";
                                                                                        continue;
                                                                                }
                                                                            }
                                                                            break;
                                                                        case -1808163200:
                                                                            String str12 = "ۛ۠ۡۘ۬ۜۛۤ۟ۥۘۛۖۡۘۤۗۥۖۗۥۘۤۨۗ۠ۗ۫۫ۗۡۡۗۤ";
                                                                            while (true) {
                                                                                switch (str12.hashCode() ^ 110336056) {
                                                                                    case -1447329822:
                                                                                        ((ClipboardManager) activity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, str3));
                                                                                        Toast.makeText(activity, "已复制", 0).show();
                                                                                        break;
                                                                                    case -306265482:
                                                                                        String str13 = "ۥۢ۟ۘۙ۟ۡ۬ۖۘ۫ۧۖۨۙۘۘۥ۬ۡ۠ۗۙۡ۠ۖۘۗۙۖۘۥۛۜۘ۟۟ۤۙۚۨ";
                                                                                        while (true) {
                                                                                            switch (str13.hashCode() ^ 1865129715) {
                                                                                                case -1379517707:
                                                                                                    str13 = "ۙۘۡۥ۫ۡۢۛ۠ۢ۟ۧۚۙۥۘ۫ۚۚۗۦ۟ۢۖۦۜۜۢۧۚۤۧۧۤۛ۠ۡۘۥۘۡۙۛۦ۫ۜۘۛۚۗ";
                                                                                                    break;
                                                                                                case -1046477974:
                                                                                                    Toast.makeText(activity, "未知点击事件类型：" + i2, 0).show();
                                                                                                    break;
                                                                                                case -266708713:
                                                                                                    String str14 = "ۗۥۡۘۛۖ۟ۥۙ۠ۛۙ۠ۤۖۖۛۚۨۘۢۘۜۘۙۚ۠ۥۧۘۘۧۖ۫ۥۙۖۘۙۦ";
                                                                                                    while (true) {
                                                                                                        switch (str14.hashCode() ^ (-1298743128)) {
                                                                                                            case -993530682:
                                                                                                                if (i2 == 7) {
                                                                                                                    str14 = "ۘۢۦۧۡۘۘۖۘۢۙ۠ۖ۟ۦۡۖۜۦۙۚ۠ۙۗ۬ۗۨۥۖۦۢ۟ۙۖۜۧۘۛۡۜۚۥۧۘۚۦ۫ۤۡ۫۬ۥۙۗ۟ۡ";
                                                                                                                    break;
                                                                                                                } else {
                                                                                                                    str14 = "ۘۖۜ۫ۘۡۘۡ۟ۨۜۘۚۜۢۧۘ۟ۨۛۧۘ۟ۛ۠ۨۘۡۚ۟ۗۤۛۘ۬۫ۢ";
                                                                                                                    break;
                                                                                                                }
                                                                                                            case 623018009:
                                                                                                                str14 = "ۘۥۡۘۜۤۘۘۖۖۥۘۡۙۡۤۖۥۦۗ۬ۚۦۥۧۖۛۨۨۖۧۖ۟ۚۚۛۘ۟ۜۘۙۚۖۜۤۤۡ۟ۡۘۘ۟ۘ";
                                                                                                                break;
                                                                                                            case 907592862:
                                                                                                                str13 = "ۤۡۦۘۥۗۚ۠ۙۘۘۤۥۚۧۘۘ۠ۨۡۖۢۖۘ۬ۖۘ۫ۦۥۘ۟ۡۛۗۙ۠۬ۘۡۘۜۧۜ۫ۖ۬ۡۤۗۦۙۜۘۜ۫ۜۘۚۚۧ";
                                                                                                                continue;
                                                                                                            case 1468320057:
                                                                                                                str13 = "ۨۡ۬۟ۘ۬ۤۚۢ۫ۢۦ۠ۘۤۗۨۦۘۥۧۖۘ۫۟ۘۨۦۥۦۨۛۢ۠ۗ۫۫۟ۧۧۚۜۘۡۘۚۗۙ۫ۧۢۖۨۜ۫۫ۨۘ";
                                                                                                                continue;
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case 582980572:
                                                                                                    String str15 = "۫ۙۘۡۜۥ۠۬ۢۗۢۡۗ۠ۦۘۛۨۚ۬۠ۧۢۢۚۗ۟ۘۖ۠ۜۘۨ۠ۥۚۚۢۗ۠ۜۘۚۚۧۘ۟ۦۘۨۖۚۛۗۨۘۡۥۖۘ";
                                                                                                    while (true) {
                                                                                                        switch (str15.hashCode() ^ (-83593533)) {
                                                                                                            case -2124147646:
                                                                                                                str15 = "ۧ۟ۨۦ۬ۖۘۚۚۢۚۚۤ۫۟ۦۘۦۗۧ۫۬ۡۚۤۢۢ۬ۘۦ۠ۡۘۖۡۘۙۚۖۘۤۢۦۘۛۘۘ";
                                                                                                                break;
                                                                                                            case -1024628015:
                                                                                                                break;
                                                                                                            case 493158287:
                                                                                                                String str16 = "ۜۡۜۜۚۖۘۥۨۘۘۛ۬ۥۡ۬ۙۙۡۖۧۢۡۘۢۧۡۘۜۗۘۨۥ";
                                                                                                                while (true) {
                                                                                                                    switch (str16.hashCode() ^ 604560930) {
                                                                                                                        case -2023632249:
                                                                                                                            str16 = "ۤ۟۫۠ۖۡ۫ۢۥۨۙۥۘ۠ۥۘۘۖۚۥۘۗۦۤۚۡۘۤ۬ۖۘۙ۫ۦۘۙۖ۫ۙۚۧۛ۠ۥۗ۠";
                                                                                                                            break;
                                                                                                                        case -1976078826:
                                                                                                                            try {
                                                                                                                                Intent intent = new Intent();
                                                                                                                                intent.setClassName(activity.getPackageName(), str3);
                                                                                                                                activity.startActivity(intent);
                                                                                                                                break;
                                                                                                                            } catch (Exception e) {
                                                                                                                                Toast.makeText(activity, h.d("IUnFWcGNDYJQJdkfmKlbwHNluQDn\n", "x8BWvH0N6ig=\n", new StringBuilder(), e), 0).show();
                                                                                                                                return;
                                                                                                                            }
                                                                                                                        case 1176088112:
                                                                                                                            String str17 = "۠ۗۤۡ۬ۡۧ۠ۜۘۡۤۜۘۖۨۜۘۘ۟ۡۘۢۤۦۘ۟ۨۖۘۚۖ۟۟۟ۙۗۜۨۢ۫ۤ";
                                                                                                                            while (true) {
                                                                                                                                switch (str17.hashCode() ^ (-1885752742)) {
                                                                                                                                    case -378764276:
                                                                                                                                        if (!str3.isEmpty()) {
                                                                                                                                            str17 = "۠ۢۘۘۧۛۗ۟ۗۖۢ۬۫ۙۢۥۘۤ۬ۖۛ۫۠ۙ۫ۤۚۥ۠ۧۥۘ۟ۗ۫ۦۘۦۘۙ۟ۡ۟۟۬۠۠ۡۘۗ۟ۗ";
                                                                                                                                            break;
                                                                                                                                        } else {
                                                                                                                                            str17 = "ۨۜۘۜۢۘۙۗۖۘۘۦ۠ۢۗۧ۫ۥ۬ۘۡۜۘۦۨۨۘۢۘۥۘ۬ۘ۬ۢۢ۠۟ۤ۬۫ۧۛ۬۫ۜۜۛۗۚ۫ۘۘۢۢۖۘۗۢۜ";
                                                                                                                                            break;
                                                                                                                                        }
                                                                                                                                    case -336912833:
                                                                                                                                        str17 = "ۢ۬۬ۖ۠ۤ۠ۡ۫ۖۡۡۚۢۢۖۙۖۜۜۤۥ۠۟ۜۗۛ۫ۘۜۘۨۥۥۘۘۧۢ";
                                                                                                                                        break;
                                                                                                                                    case 710414189:
                                                                                                                                        str16 = "ۘۡۚۤۡۖۚۛۨۥۛۛ۠۬ۤۙ۫ۙۙۥۛۙۚۧ۬ۧۘۘۦۜۙۜۖۢۤۨۘ۠ۙۖۘۢۤۘۜ۫ۡۘۛۗۨۘ";
                                                                                                                                        continue;
                                                                                                                                    case 1169483165:
                                                                                                                                        str16 = "ۜۛۡۤ۠ۧۛ۠ۦۘۜۚ۟ۚۡۚۗۙۧۜۚۡۦۧۘۨۛۚۧ۫۬";
                                                                                                                                        continue;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            break;
                                                                                                                        case 2054277898:
                                                                                                                            break;
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            case 1759750806:
                                                                                                                String str18 = "ۦۦۘۖۖۨۘۚۢۥۘۜۧۚ۫ۧۘۘۧۘۘۘۨۚۢۖ۠ۥۘ۠ۘۘۘۨۡۦۢۨۤۗۚۧ";
                                                                                                                while (true) {
                                                                                                                    switch (str18.hashCode() ^ 2027788608) {
                                                                                                                        case -1232159851:
                                                                                                                            str15 = "۟ۘ۠ۙۡۡۘۖ۬۟ۚۖ۫ۡۗ۫ۖۤۙۙ۠ۡ۠ۗۨ۟ۛ۠ۖۘۙ۫ۚۨۧۨ۫ۗ۟ۥۗۧۗ۠ۢ۫ۗ۠ۢۜۛۥۘۧۢۥ";
                                                                                                                            continue;
                                                                                                                        case 707271879:
                                                                                                                            str18 = "ۘ۫ۤ۠ۙۥۗ۠ۖۦۡۡۡۜۗۤ۫ۡۨۙۧۚۜۨۨۨۥۨۨۘ";
                                                                                                                            break;
                                                                                                                        case 902100483:
                                                                                                                            str15 = "ۦۡۨۚۢۛۥۗۚۧۖ۠ۛۙۥۘۤۦۥۡۖۨ۠ۡۗۦۛۛۛۤ۫ۘۨۦۘۧۖۧۘۘۧۗ۬ۚۤ";
                                                                                                                            continue;
                                                                                                                        case 1172558087:
                                                                                                                            if (str3 == null) {
                                                                                                                                str18 = "۠ۜۧۘۦۗ۫ۥۗۜۘۤۛۤۧ۟۬ۧۡۘۛۚۦۘۨۨۘۚۖۘۜۙۦۘۧۨۘۦۘۥۧ۬ۖۗۙۖۘ۟ۙۖ";
                                                                                                                                break;
                                                                                                                            } else {
                                                                                                                                str18 = "ۗ۫ۙۨۖۤ۟ۖۚ۟ۥۡۤۡۦۨۦۘۧۜۜ۠۠ۨۘۦۚ۬ۚۦۥۘۘ۫ۜۘۦ۟ۥۨۖۜۗۖۛۖۗۥۗۡۚۢۨۡۘۘۢ۫";
                                                                                                                                break;
                                                                                                                            }
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case 180416681:
                                                                                        String str19 = "ۨ۠ۚ۟ۢۚ۟ۘۘۡ۬ۦۘۛۗۦ۫۟ۜۡۜۘۦۜۦۘ۬ۖۘۘۨۦۘۛۧۜۘۖۖۘ۫ۚۖۘۙۡۜ";
                                                                                        while (true) {
                                                                                            switch (str19.hashCode() ^ (-788130425)) {
                                                                                                case -878223360:
                                                                                                    str19 = "ۛۖۚۘۚۡ۠ۙۡۤۥۚۧۚۧۧۗۧۥ۫ۙۛۙۗۗۧ۠ۧۛۖۡۦۢۤ۫ۤ";
                                                                                                    break;
                                                                                                case -518244000:
                                                                                                    str12 = "۠ۦۦۘۛۧۦ۟ۖ۟۬ۤۨۢۥۢۦ۠ۨۡۜ۠ۥ۠۫ۛ۬۫۟ۗۥۤۢۥۜۤۖۘ";
                                                                                                    continue;
                                                                                                case 244289796:
                                                                                                    if (i2 == 6) {
                                                                                                        str19 = "ۢۙۘۘ۟۠۬۟ۦۚۦۗۦۘۙۘۥۧۧۗۙۛۘۘۗ۫ۖۘۢۚۘۘۢۨۦ۬ۧۙ۬ۙۢ۬ۡۥۢۘۙ";
                                                                                                        break;
                                                                                                    } else {
                                                                                                        str19 = "ۧ۟ۥۢۧۨۘۤۗ۠ۛ۠ۚۘۧ۫ۘۛۜۘۙ۬ۜۜۢۢۗۢۖۘ۟ۛۨۖۥۖۘ۠ۦۙ";
                                                                                                        break;
                                                                                                    }
                                                                                                case 1881159787:
                                                                                                    str12 = "ۘۦۢ۫۠ۗۨۚۦۘۜ۠ۛۗۗۨۘۡ۟ۨۚۛۖۗۘۧۖ۠۠ۨۤ۬ۗۜۡۢۥۤ۟ۥ۫ۜ۫ۛ";
                                                                                                    continue;
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case 414054324:
                                                                                        str12 = "ۢۛۜۘۧ۠ۦۘۖۧۧۨ۬ۙۘۧۛۢۘ۟ۧۘۖۚۛۖۘۖۤۨۖۤۘۜ۟۫ۧۘۖۛۡۦ۫ۦ۫ۥۨۘۤۥۛ";
                                                                                        break;
                                                                                }
                                                                            }
                                                                            break;
                                                                        case -1689466710:
                                                                            try {
                                                                                Intent intent2 = new Intent("android.intent.action.SEND");
                                                                                intent2.setType("text/plain");
                                                                                intent2.putExtra("android.intent.extra.TEXT", str3);
                                                                                intent2.putExtra("shell_protected", true);
                                                                                activity.startActivity(Intent.createChooser(intent2, "分享内容"));
                                                                                break;
                                                                            } catch (Exception e2) {
                                                                                Toast.makeText(activity, "无法分享", 0).show();
                                                                                return;
                                                                            }
                                                                    }
                                                                }
                                                                break;
                                                            case 1461271996:
                                                                str8 = "ۡۧۥۢۥۜۘۖۜۥۘۖ۟۠ۖۢۖۙۘۦۨ۟ۚۗۙۧ۠ۤۡ۟۫۬ۦ۬ۢۥۦۢۦۨۜۘۡۗۘ";
                                                                break;
                                                            case 1903881205:
                                                                new Handler(Looper.getMainLooper()).postDelayed(new w1(i, activity), 1000L);
                                                                break;
                                                        }
                                                    }
                                                    break;
                                                case 635995252:
                                                    String str20 = "ۦۜۥۤۛۗ۫ۜۢۨۥۚۦۚۖۘۢۦۜۘۢ۫ۚۜۖ۫۠۬۟ۚ۠۟ۦۖ۫ۙۦۨۢۥۤ۬ۧۥۚۚ۠ۥۖۛۢۡۥۢ۫ۜ";
                                                    while (true) {
                                                        switch (str20.hashCode() ^ 1476070220) {
                                                            case -1298231870:
                                                                str7 = "ۗۘۥۖ۠ۢ۬ۙۖۤۜۤۢۥۘۘۧۘۦۘۗۛۜ۟ۤۘۧۨۗۜۖۜۛۡۗۚۚۦ۟ۘ۠ۜ۠";
                                                                continue;
                                                            case -1166763910:
                                                                str20 = "ۘ۠ۤۢۚۦۙ۟ۛۡۘۛۗۤ۫ۚۚۥۜۖۘۛ۟۬ۤۦۙۤ۫ۗۦۥۘۧۡۡۨۛۤ۫۬۫۫ۜۥۢۖ";
                                                                break;
                                                            case -605067846:
                                                                str7 = "۠۠ۛ۫ۨۘۘ۬ۤۥۛۜۜۘۘ۬ۛۜۖۘ۫۟ۖۥۡ۬ۛۡۘۨۦۖۛ۠ۗۙۧۨۡۗ۫ۗۦۙ";
                                                                continue;
                                                            case 2122696565:
                                                                if (i2 == 2) {
                                                                    str20 = "ۤ۠۠ۖۥۥۘ۬ۚ۟۟ۧۛۤۖۨۘۚۨۥۢ۬ۗۜۙۜۤ۟ۧۢۛۜۛۦۤۤۖۜ۠ۥۘۦۛۨۘۖۙۘۘۧۡ";
                                                                    break;
                                                                } else {
                                                                    str20 = "ۢۚۙۧۦۛۘۦۧۧۨ۠ۢۚ۠ۗۘۡۚۨ۠ۗۥۡۘۨ۠ۨۧ۫ۡۙۡ۬۠ۘۙۖۦۙۖۨۚۖۡۖۦۥۙ";
                                                                    break;
                                                                }
                                                        }
                                                    }
                                                    break;
                                                case 1307633354:
                                                    String str21 = "ۨۖۜۘۥۨۖ۟ۚۦۘۗۖۘۘ۫۟ۛۢۜۜ۫ۗۖۥۖۜۘۦ۟ۙۖۗۜۘ۬۫ۢ۠۠ۦۛ۫۬ۧ۬ۧۤۙۘۜۥۘۨۦۜۘۡۖۦ";
                                                    while (true) {
                                                        switch (str21.hashCode() ^ (-767517969)) {
                                                            case 169738659:
                                                                String str22 = "ۖۨ۠ۛ۫ۡ۠ۥۤۤۛۛۗ۫ۖۘۘۢۘ۫ۨۜۘۙ۬۬ۤۗۜۘ۠ۘۚۡۙۤ۫۠ۖۛۢۗۚۧۡ";
                                                                while (true) {
                                                                    switch (str22.hashCode() ^ 1172934908) {
                                                                        case -1271343374:
                                                                            str21 = "ۘ۠ۘۘ۬ۥۜۘۜ۟ۖۘۗۗۦۘۖ۫ۦۘۦۙۙۙۡۗ۠ۧۧۛۖۥۢ۬ۧۡۦ۬ۚۥۧۘۦۗ۟ۥۤ۠۫ۙۦ۠ۜۜ";
                                                                            continue;
                                                                        case -1040800019:
                                                                            str22 = "ۜۖۚۨۤۘۘۜ۠۟ۥۨۥۘ۬ۢ۠ۗۚۥ۠ۜۘۘۦۦۦۘ۠ۖۥۚۥۘۙۧۚۛ۟ۢ";
                                                                            break;
                                                                        case 171324343:
                                                                            str21 = "ۘۧۨۘۢ۠ۗۦۥۜ۬ۤۚۤۘۘۡۦۥۡۛۗ۠ۡۜۘۚۚۘۘۗۙۛۥ۫ۗۨۗۛۤ۟ۧ۬ۥۚ";
                                                                            continue;
                                                                        case 1534519040:
                                                                            if (!Utils.joinQQGroup(activity, str3)) {
                                                                                str22 = "ۚۗۢ۠۟۠ۘۢۙ۫ۢۘۘۚۢۘۢ۬ۡۘ۟ۛۡۘ۠ۖ۠ۗۦۛ۫ۤ۬ۙ۠ۤۚۡۥۗۦۧۘ۬ۛۦۘۤۘۚۥۡۡۜۜۘ۬ۦ۫";
                                                                                break;
                                                                            } else {
                                                                                str22 = "ۧۤۡۖۖۜۘۧۦۛۨۖۗۚۦۧۘۛۧۜۘ۬ۡۘۖۤۖۜۙۢۛۗۤۧۗۜۘۛۦ۟";
                                                                                break;
                                                                            }
                                                                    }
                                                                }
                                                                break;
                                                            case 177554912:
                                                                Utils.sendCallback(activity, str2, str);
                                                                break;
                                                            case 897276222:
                                                                Toast.makeText(activity, "无法加入QQ群", 0).show();
                                                                break;
                                                            case 1287828677:
                                                                str21 = "ۘۖ۫ۛۢۖۘۦۤۧۧۜۚۙۥۥۘ۠ۗۖۘۙۢۥ۟ۜۥۘۚ۫ۚ۫ۧ۠ۛۖۜۘۢ۫ۛۘ۬ۜۘۘۛۘۘ";
                                                                break;
                                                        }
                                                    }
                                                    break;
                                            }
                                        }
                                        break;
                                    case 1027212885:
                                        String str23 = "۫۟ۧۦۛۘۧ۫۟ۘۤ۟۠۟۬ۖۗ۫ۢ۬ۜۘۥ۟ۡۘۛۥۖۖۗ۟ۥۨۡۘ۟";
                                        while (true) {
                                            switch (str23.hashCode() ^ 317427475) {
                                                case -1232075750:
                                                    str6 = "ۨۨ۬ۨۦۦۘۨۡۘۘ۠ۙۡۘ۬۫ۥۘۜۚ۫ۥ۫ۡ۠ۡۤۜ۟ۘۛۗۦۘۦۦۘۨۤۚ۟ۢۜۦ۫ۥۘۨۙۡۘۧ۫ۜۘ";
                                                    continue;
                                                case -627544990:
                                                    str23 = "ۧ۠۬ۙۖۡۘ۟ۨۜۘۤۛۛۧۡۜۛ۟ۜۚۧۙۗ۬ۦۖۧۘۘ۠ۢۦۘۧۦۖۖۢۨۘ۟ۗ۟ۖۜۡۘ";
                                                    break;
                                                case 1110250423:
                                                    str6 = "۟ۨۦۥۥۥۚۢۢ۬۟ۦ۠ۨۢۚۖۤۦۘ۠ۖۙۧ۟۟ۦۡ۟۬۟۟ۖۗۖۘۘۢۙۙۜۘۘۢ۠ۖۖۦۨ";
                                                    continue;
                                                case 1205377060:
                                                    if (i2 == 1) {
                                                        str23 = "۠ۡۦۘۥۤۨۤ۬ۨۘۡۜۦۘۦۨۢۜۦۧۘۛ۫ۡۥۖ۟ۖۦۢۖ۬۫ۧۖۧۜۘۛۥۢۜۘۗۛۘۘۤۜۨۘۖۨۢ";
                                                        break;
                                                    } else {
                                                        str23 = "ۙۖ۠ۤ۫۟ۚۢۛۧۜۘ۠۠ۦ۫ۙۨۘ۫ۦۡۚۧۤ۫ۥۘۧۗ۠ۧۡۢ۠۟ۖ";
                                                        break;
                                                    }
                                            }
                                        }
                                        break;
                                    case 1766544973:
                                        try {
                                            Intent intent3 = new Intent("android.intent.action.VIEW", Uri.parse(str3));
                                            intent3.putExtra("shell_protected", true);
                                            intent3.addFlags(268435456);
                                            activity.startActivity(intent3);
                                            Utils.sendCallback(activity, str2, str);
                                            break;
                                        } catch (Exception e3) {
                                            Toast.makeText(activity, "跳转失败", 0).show();
                                            return;
                                        }
                                }
                            }
                            break;
                    }
                }
                break;
            default:
                Set<String> set2 = k2.closedPopupIds;
                String str24 = "ۢۛۥۘۨۨۜۧ۫۫۫ۜۡۘۡۧۚۗ۬ۦۘ۫۫۟ۙۡۜۛۖۡۘ۠ۚۛ";
                while (true) {
                    switch (str24.hashCode() ^ 477365733) {
                        case -2029350847:
                            String str25 = "ۥۛۨۥ۫ۖۧ۫۫۬ۜۥۘۥۡ۠۠ۤۜۘۖۚ۬۫ۡۘۚ۫ۦۧۜ۫";
                            while (true) {
                                switch (str25.hashCode() ^ (-1746543504)) {
                                    case -259728628:
                                        String str26 = "ۖ۫۫ۙ۟۬ۚۜۖۘۙۧ۠ۡۙۢ۠ۡۨ۠ۡۛۦ۬ۖۥۙۖۘۡ۠ۥۡۘۡۘۤۧۦ";
                                        while (true) {
                                            switch (str26.hashCode() ^ 205312571) {
                                                case -1071229307:
                                                    String str27 = "ۘۨۡۧ۫ۗۡۨۨۥۛۢ۠۬ۜۘ۬۫ۖۘۤۢۥۘ۠۟ۗۥۙۥۗۖۚۦ۬ۡۘ۟ۚۧ۠۟ۤ۠ۢۙۗۥۥۘ۠ۙۦۡۛۧۦۙۜ";
                                                    while (true) {
                                                        switch (str27.hashCode() ^ (-1311567725)) {
                                                            case -1894996027:
                                                                String str28 = "ۨۙۚ۫ۨۧۘۦۡۧۘۢ۟ۨۦۨۗ۬ۛ۠ۦۙۤ۫ۥ۬ۖۘۙۦۨۘۖۛۨۧۤۢۖۥۡۦۖۧۘۛۜۘ۫ۘۘۛۥۘۘۘۥۨۘ";
                                                                while (true) {
                                                                    switch (str28.hashCode() ^ 134010888) {
                                                                        case -1266208625:
                                                                            str28 = "۫ۛۤۢۗۛۡ۫ۜۘۜۨ۠ۘۛۜۘۤۙۖ۟ۚ۬ۖۙۜۘ۫ۜۜۘۘۢۥۘۖۡۜۙ۫ۜۘ";
                                                                            break;
                                                                        case -1186051260:
                                                                            try {
                                                                                Intent intent4 = new Intent("android.intent.action.SEND");
                                                                                intent4.setType("text/plain");
                                                                                intent4.putExtra("android.intent.extra.TEXT", str3);
                                                                                intent4.putExtra("shell_protected", true);
                                                                                activity.startActivity(Intent.createChooser(intent4, "分享"));
                                                                                break;
                                                                            } catch (Exception e4) {
                                                                                Toast.makeText(activity, "无法分享", 0).show();
                                                                                return;
                                                                            }
                                                                        case 1123604899:
                                                                            String str29 = "ۖۘۧۘ۬۫۠۟ۥۥۦۢۦۘۜ۫۫ۖۘۧۘۘۘۗۦۖۙ۟ۖۖۘ۫۠ۛۚ۠۫۫ۖۘۦۨۙ۠ۙۦۘ۠ۤ۫ۢۙۖۘ";
                                                                            while (true) {
                                                                                switch (str29.hashCode() ^ (-1697802126)) {
                                                                                    case -1529264323:
                                                                                        str29 = "ۥۗۖۘۗۖۨۘۘۥۧۘۖۨۜۘۦۗۚ۬ۤۘۘۤۖۘۘ۟ۧۨۥۨۢۧۜۧۡ۬ۡۡۖۘۘ۠ۙۘ۬۬ۢ";
                                                                                        break;
                                                                                    case 164347821:
                                                                                        String str30 = "ۡ۬ۙ۫ۜۥۘۜۘۘۗۘۖۘ۟ۧۗۖۜۘۙۧۜۙۦۘۘۖۧۜۚۧۢ۠ۗۛ۠ۢ۟ۥۡۘۜۡ۫ۥ۫۠ۗ۬ۦ";
                                                                                        while (true) {
                                                                                            switch (str30.hashCode() ^ (-721874521)) {
                                                                                                case -1813312696:
                                                                                                    String str31 = "۟ۜۗۡۤۧ۠ۙۖ۠ۤۗۚۙۚۦۘۙۙۢۤۦۜۧۥۗۜۘۚ۠ۛ۠ۙۥۨۘۨۘ";
                                                                                                    while (true) {
                                                                                                        switch (str31.hashCode() ^ 120844088) {
                                                                                                            case -231010922:
                                                                                                                str30 = "ۙۡۜۦۨۛۦ۬ۛ۫ۙ۠ۥۛۨ۬ۥۦ۫ۘۜۘۘۥۘۘۢۦۘۘۦۚۨۤ۬ۡۘۘۦۗۢۙۛ۫ۨۧ";
                                                                                                                continue;
                                                                                                            case 236793671:
                                                                                                                str30 = "ۡۗۖۘۖۗۦ۫ۖۦۘۙۤۡۘ۠۬ۨۘ۫ۛۜۙۤۜۘ۠ۖۜۘۖۧۖۘۨ۫ۖۗۤۖۘۤۡ۠ۙ۬۫ۛۚ۫";
                                                                                                                continue;
                                                                                                            case 445202154:
                                                                                                                str31 = "۬ۘۘۘۛۖۡۘۡۡۦۘۨۛۨۥ۟ۡۘۡ۫۠۠۠ۜ۠۫۫ۤۥۛ۫ۥ۟ۖۤۚۛۡۛ۟ۦۦۘ۠ۖۦ";
                                                                                                                break;
                                                                                                            case 565930751:
                                                                                                                if (i2 == 7) {
                                                                                                                    str31 = "ۥۢۜۘۤۢۛ۟ۥۗۥۨ۠ۛۦۘۜ۫ۨۦۖ۫ۚ۟ۦۡۖۘ۬ۦۧۘۥ۠ۘۗ۟۬ۚۢۙۛۚۜۘۥۢۘۘۧ۟ۦۢ۬ۢۡ۟۬";
                                                                                                                    break;
                                                                                                                } else {
                                                                                                                    str31 = "ۘ۫۬ۖۡ۬ۖۧۦۨ۠ۛۨۚۖۘۚۙۛۖۥۘ۫ۛۥۛ۟ۗۧۜۙۜۨۡ۟۬ۜۘۛۤۥۘۥۛۨۖ۫ۡۙۢ۬ۜۜۛۙ۬ۖۘ";
                                                                                                                    break;
                                                                                                                }
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case -1712205470:
                                                                                                    str30 = "۠ۜۡۥۢۜۘۙۚۛ۬ۧۘۜۤۜۘۡۧۧ۬ۚۜۤۤۡ۠ۖۧۘۚۚ۫ۘۥۧۘۢۜۘۢ۟ۢۙۗۨۘ";
                                                                                                    break;
                                                                                                case -832363921:
                                                                                                    Toast.makeText(activity, "未知点击事件类型：" + i2, 0).show();
                                                                                                    break;
                                                                                                case 2051989940:
                                                                                                    String str32 = "ۗۤۧۧۤۨۘۥۦۦۢۥۧۘۖۙ۠ۚۢ۫ۜۖۖۜۦۡۘۛ۟ۤۛ۠ۥۗۧۦۘۘۧۢۤۥۜۘۚ۠ۥۡۖۦۘۛۙۖۘۗۨۙۨۤۖۘ";
                                                                                                    while (true) {
                                                                                                        switch (str32.hashCode() ^ 1136796878) {
                                                                                                            case -2074404519:
                                                                                                                break;
                                                                                                            case -1368252982:
                                                                                                                str32 = "ۦۨۡۘ۠ۖۜۦۨۖۘۖۙۥۘۢۨۡۘۜۢۧۜۖۨۘۤۘۧۘ۟ۧۙ۬۟ۡۤۛۦۘۤ۠ۦۘ";
                                                                                                                break;
                                                                                                            case -127666553:
                                                                                                                String str33 = "ۡۜۗۥۚۘۘۥۚ۫ۡ۫۟ۤۢۛ۫ۦۧ۬ۖ۟۫ۥۥۘۥۤ۟ۙ۬۬ۘۢۚۨۡۘۘۧ۫ۘۘۤۖۜۘ۫ۥۚ۟۠";
                                                                                                                while (true) {
                                                                                                                    switch (str33.hashCode() ^ (-744553658)) {
                                                                                                                        case -1863412732:
                                                                                                                            if (str3 == null) {
                                                                                                                                str33 = "۟ۚۛۚۨۡ۠ۗۜۘۨۖۥۘۡ۠ۖۘۖۙ۬۟ۙۖۘۧۗۙۦۛۡۘۙۦۜ";
                                                                                                                                break;
                                                                                                                            } else {
                                                                                                                                str33 = "ۧ۟ۥۥۢۜۘۨۗۨۘ۬ۘ۠ۦۖ۠ۜ۫ۙۜۨۡۘۧۚۦۘۡ۬۫ۥۖۧۘ";
                                                                                                                                break;
                                                                                                                            }
                                                                                                                        case -1426550665:
                                                                                                                            str33 = "ۖ۟ۤ۬ۡۦۜۖ۠ۥۘۡۡۨۡۘ۟۫۫ۥۤۘۨۡۙۢ۟ۗۢۢۖۘ۠۫ۚۥۦۚ۟۟ۖۢۤۖۘۗۘۦۘۥ۬ۙۥۨۡۖۤۜۘ";
                                                                                                                            break;
                                                                                                                        case 112195697:
                                                                                                                            str32 = "ۡ۫ۧۤ۠ۜۚۤۘۘ۠۟۠۟ۘۨۘۙۥۙۧۥۡۘۖۚۧ۠ۘۜۘ۬ۜۙ";
                                                                                                                            continue;
                                                                                                                        case 174032353:
                                                                                                                            str32 = "ۚۤ۫ۖۡ۠ۙۜۨۘۦۙۘۘۦ۟ۨۧۢۦۢۨ۟ۜۥ۬ۢ۠ۢۛۙۖۘ";
                                                                                                                            continue;
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            case 1471417934:
                                                                                                                String str34 = "۬ۧۛۜۛۖۘۘ۫ۜۥ۫ۦۘۤۥۘۘۡ۠ۗۙ۫ۚۛ۫ۡۗۖۜۘۚۜ۬";
                                                                                                                while (true) {
                                                                                                                    switch (str34.hashCode() ^ 133191888) {
                                                                                                                        case -1189999205:
                                                                                                                            break;
                                                                                                                        case -909016737:
                                                                                                                            String str35 = "ۘ۟ۨۘۢۦۨۦۤۨۘۤۥۧۘۨۧۥۗۢۘۨۦۥۘۨۖۘۘۜۤۨۖ۫ۛۜۜۦۘۧ۠ۡ";
                                                                                                                            while (true) {
                                                                                                                                switch (str35.hashCode() ^ 2035467947) {
                                                                                                                                    case -905071354:
                                                                                                                                        str35 = "ۦ۫ۦۘ۠ۧۚ۠ۢۨۘ۟۠ۛۜۗ۬ۖۘۢ۟ۡۘۜۗۥۜ۠ۦۙۜۧ۬ۖۡۥۙۘۘ۟ۙۦ۫۬ۙ";
                                                                                                                                        break;
                                                                                                                                    case 441423938:
                                                                                                                                        str34 = "ۧ۬ۦۨۗۧ۬ۧۢۛ۬۫ۡۙۨۦۘۧۢۛ۠۬ۜ۫ۨۗۖۘۜۨۧۘۤ۬ۜۘۚۨ۫۬ۛۖۘۥۜۤۚۚۗۘۨ۫ۖ۫ۤۨ۫ۥ";
                                                                                                                                        continue;
                                                                                                                                    case 1425292310:
                                                                                                                                        if (!str3.isEmpty()) {
                                                                                                                                            str35 = "۠ۘۤ۠ۛۖۘۧۖ۬ۙۙۦۡۥۢۚۤۛۤۜۘۙۗ۬ۤۡۦۧۖۖ۠ۤۢۨ۟ۦۘۡۜۡۨۥۚ";
                                                                                                                                            break;
                                                                                                                                        } else {
                                                                                                                                            str35 = "ۡۜۢۗ۬ۗۗ۬ۢۧ۟ۦۧۙۨۘ۠ۢۢۢۤۡ۠۠ۖۗۖۗۨۜۥۘۛۤۢۦۗۨۘۥۧۚۚۦۜۜۥۦۘۤۦ";
                                                                                                                                            break;
                                                                                                                                        }
                                                                                                                                    case 1808264269:
                                                                                                                                        str34 = "ۗۧۥۗ۫ۥۘۥۛۥۘۤ۠ۡۛۗۖۘۘ۠ۡۘ۟ۛۖۡۡۦ۠ۚۘۜۨۖۛۦ۬ۗۛۛ۟ۨ۫ۘۘۦ";
                                                                                                                                        continue;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            break;
                                                                                                                        case -841792598:
                                                                                                                            try {
                                                                                                                                Intent intent5 = new Intent();
                                                                                                                                intent5.setClassName(activity.getPackageName(), str3);
                                                                                                                                activity.startActivity(intent5);
                                                                                                                                break;
                                                                                                                            } catch (Exception e5) {
                                                                                                                                Toast.makeText(activity, h.d("JXkO2OMmASZUFRKeugJXZHdVcoHF\n", "w/CdPV+m5ow=\n", new StringBuilder(), e5), 0).show();
                                                                                                                                return;
                                                                                                                            }
                                                                                                                        case -481445473:
                                                                                                                            str34 = "ۥ۫ۛۧۧۢۨۨۡۘۦۦۚ۬۟۟ۙۥۗۥۡۘ۫ۤۘۘۥ۟۫۟ۥۦۗۨ۬۬ۨۘۡۤۘۘۥ۠ۨ";
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
                                                                                    case 260453955:
                                                                                        String str36 = "ۢۚۥۘۚۡۘ۟ۧۨۘ۬ۗۖۘۜ۟ۦۘۛۙۘۘ۫ۢۡۧۜۙۨ۬۫ۚۜۜۙۗۜ۠ۤۥۘۦۛۖ۫۬۠";
                                                                                        while (true) {
                                                                                            switch (str36.hashCode() ^ 1572117622) {
                                                                                                case -1608862664:
                                                                                                    str29 = "۠۠۫ۥۜۡ۬ۚ۫ۗۢۗۥ۫ۢ۟ۛۤ۟ۢ۟ۧۧۚۘۡۘۖۢ۠";
                                                                                                    break;
                                                                                                case 760883070:
                                                                                                    if (i2 == 6) {
                                                                                                        str36 = "ۢۢۘۘۤ۬ۡۘ۫ۧۦۦۖۦۚۦ۠ۘ۬ۦۧۘۥ۟۫ۖۥۢۦ۠ۢۚ";
                                                                                                        break;
                                                                                                    } else {
                                                                                                        str36 = "ۚۡۖۘۛۚۘۥۧۨۘۗ۫ۦۦۜ۟ۘ۬ۤۢۜ۫ۜۤۛۥۦ۫۠ۙ۫ۥ۬ۛۢۨۘۥ۬ۡۥۛۥ۠ۚۤۦۡ۫";
                                                                                                        break;
                                                                                                    }
                                                                                                case 1906929740:
                                                                                                    str36 = "۠ۦ۟۟۫ۤۗ۬ۡۘۦۧۚۛۜۥۗ۬ۖۡۡۙۥۖۛۜۧۘۤۚۥۘ۠۫ۚۥ۠ۧ";
                                                                                                    break;
                                                                                                case 2032481057:
                                                                                                    str29 = "ۘۡۤۤ۟ۘۘ۫ۗۦۘ۟ۧۦۜۨۘ۫ۚ۫ۖۛ۟ۛۨۡۜۤۖۘۖ۠ۘۙ۠ۥۘ۬ۜۤۖۡ۬۟ۨۢ۟ۗۖۤ۠ۨۡ۠ۥۧۧۥ";
                                                                                                    break;
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case 375256335:
                                                                                        ((ClipboardManager) activity.getSystemService("clipboard")).setPrimaryClip(ClipData.newPlainText(null, str3));
                                                                                        Toast.makeText(activity, "已复制", 0).show();
                                                                                        break;
                                                                                }
                                                                            }
                                                                            break;
                                                                        case 1686567616:
                                                                            String str37 = "ۦۘ۟ۦۛۗۚۦۨۘۨ۟ۚ۬۬ۘۘۤ۠۠ۧۨۘۘ۬ۖۨۖ۟ۨۖ۟ۚۘۙۖۘۨۡۤ۬۠۬ۘ۫ۚۜۜۨۘۜۨۤ";
                                                                            while (true) {
                                                                                switch (str37.hashCode() ^ (-408710784)) {
                                                                                    case -1683556803:
                                                                                        str28 = "ۢۘۚۤۤۘۡۤۤۘ۟ۘۢۥۡ۫ۤۤۢۧۛۙۜۨۘۘ۟۫۫ۘ۫۠ۚۖۖۢۥۘۘۧ۬ۘۘۧۗۙۢۤۥۘۗ۟ۢۘۨۧ";
                                                                                        continue;
                                                                                    case -531704698:
                                                                                        str28 = "ۨۤۘۘۢۢ۟۬ۤۛۙۨۡۡۦۚۘۤۙۡ۬ۛۥۥۢ۫ۗۥۡ۫۟ۧۡۥۧ۬۟ۨۖۛۗۦۘۥۡ۬ۚۙ";
                                                                                        continue;
                                                                                    case -304955873:
                                                                                        str37 = "۠ۡ۬ۨۚۖۢ۫ۤ۫ۖ۬ۘۥۥۘۛۙۘۙ۫ۙ۠ۢۛۤ۬ۦۛۘۥۘ۟ۘۤۦۨۜۘۧۨۥۡ۠ۖۘ";
                                                                                        break;
                                                                                    case 729038158:
                                                                                        if (i2 == 4) {
                                                                                            str37 = "ۦۤۥ۬ۥۦۡۦ۫ۗ۬۫۬ۨۛۢۗۜۘۛۥۘۡۧۛۛۛۘۛۖۘ";
                                                                                            break;
                                                                                        } else {
                                                                                            str37 = "ۛ۫ۤۦۖۢۧۦۥ۫ۨۡۙۗۢۧۖۗ۬۬ۨۘۗۡۚۚۥۛۥۡۥۧ۟ۡۘۗۙۤ۬۫ۘ";
                                                                                            break;
                                                                                        }
                                                                                }
                                                                            }
                                                                            break;
                                                                    }
                                                                }
                                                                break;
                                                            case -1196090878:
                                                                activity.finishAffinity();
                                                                activity.getWindow().getDecorView().postDelayed(new a(10), 300L);
                                                                break;
                                                            case -1026563099:
                                                                str27 = "۬ۚ۠۬ۨۤۢۙۡۢۨۖۘ۟ۨ۬۠ۤۛ۠ۢۢۥۖ۫ۙۥ۠ۤۜۘۜۡۘ۠ۖۦۘۛۡۥۘ۠ۦۦۘ";
                                                                break;
                                                            case -781859471:
                                                                String str38 = "ۛۨۜۘۡ۠ۘۘۘۗۛۢۛ۟ۘۦۥۘ۟ۛۢۙۢۦۘۛۥۥۘۤۡۦۨۘۛۥۚۖۘۘۢ۬ۥۗ۫۠۫ۖۗ۬ۡۘۤۙۜۚۧۦ۫ۗۜ";
                                                                while (true) {
                                                                    switch (str38.hashCode() ^ (-1730586309)) {
                                                                        case -1445992986:
                                                                            str38 = "۠ۖ۬ۢۨۛۨۤۧۗۦۡۘۘۛۚۙۚۧ۫۟ۚۦۜ۫ۗۡۛۜۦۘۛۗۡۘۧ۠ۨۘ";
                                                                            break;
                                                                        case 459400658:
                                                                            str27 = "ۦۤ۬ۗ۠ۡۘۡۚ۠ۙ۫ۨۘ۬ۚۥ۟۟ۗۖۗ۫ۙۨۗۖۚۥ۬ۚۘۘ";
                                                                            continue;
                                                                        case 1056370565:
                                                                            str27 = "۫۬ۚۡۤۖۘ۠۫ۡۧ۠۬ۚ۫ۧۛۢۡۗۘۖۢۧۢۦ۠ۙۧۢ۟ۜۤۥۘۡۡۘۘۘۙ۬ۗ۬۫ۢ۫ۢۢۡۗ";
                                                                            continue;
                                                                        case 1784949113:
                                                                            if (i2 == 3) {
                                                                                str38 = "ۖ۟ۥۘۢۤ۠۬ۦۡۧۥۨۘۧۦۘ۬ۘۧۚ۟۠ۛۡ۟ۡۧۘۘۗۥۡۘ";
                                                                                break;
                                                                            } else {
                                                                                str38 = "ۧۤۥۡۛۢۗۦۤۜۤۦۜۨۖۘ۟۟ۛۗۗۨۘۨۛۨۘۚۙۗۖ۟۠ۙۥۗۦ۫ۥۘ۠ۙۘۘۦۨۥ";
                                                                                break;
                                                                            }
                                                                    }
                                                                }
                                                                break;
                                                        }
                                                    }
                                                    break;
                                                case -355723187:
                                                    String str39 = "ۦۢۖۘ۠ۖۘۙۛۘۛۜۖۘۗۧۡۘ۬ۛۖۦۖۖۡۥ۟ۚ۟ۜ۬ۛۦۘۜۘ۟ۜۢ";
                                                    while (true) {
                                                        switch (str39.hashCode() ^ (-187352425)) {
                                                            case -1778578692:
                                                                str26 = "ۨۦۜ۠ۦۨۘۨ۟ۢ۟ۗ۟۟ۛۙۦۘۚۢۘۡ۫۠۟ۢۥۦۘۦ۟ۢۚۥۙۖۨۙۚۗۜ۠۬ۖۘ";
                                                                continue;
                                                            case -1124482180:
                                                                str26 = "۬ۗۘۘۜۙۗۨۢۤۦۧۡۗۧۖۘۜ۟ۦۘۧۨۨۚۚۨۘ۠۟ۡۨۘ۠ۘۖۘ۠۬ۚۥۜ۫ۦۖۨۘ۬ۥۗۜۨۜۘ";
                                                                continue;
                                                            case -1103172321:
                                                                str39 = "۟۠ۨۨ۠ۜۘۙۛۙۗۘۜۘۖۚۤۥۜۨۘۜ۬ۖۘ۫ۦۧ۠ۛ۟ۖۗۤ";
                                                                break;
                                                            case 1324293990:
                                                                if (i2 == 2) {
                                                                    str39 = "ۥۨۖۘۥ۬۟ۦ۟۬ۗۧۖۘۚۛۘۗۙۗۙۦۙۢۛۚۗۤ۬۬ۙۖۜۧۙۧۢۥ";
                                                                    break;
                                                                } else {
                                                                    str39 = "ۚۘۦ۬ۘۡۘۙ۠ۥۘۢ۠ۘۘۜۜۤ۫۟ۖۗۛۧۢ۬ۥ۠ۜۙۧۢۥۤۘۘ۬ۜۨۖۗۗۨۙۛ۬۠۫۟ۜۖۗۜۙۚۨۚ";
                                                                    break;
                                                                }
                                                        }
                                                    }
                                                    break;
                                                case 238502340:
                                                    str26 = "ۢۚۦۘۡۡ۬۠ۢۥۨۙۛۦۨۨۧۘۛۙ۬ۗۙۙۢۥۥۢ۫ۚۦۘۨ۫ۘۘۦۨۙ۟ۗۧ۬ۥۜ۟۠ۦۜۡۘ";
                                                    break;
                                                case 448175873:
                                                    String str40 = "ۘۚۨۘۨۡۨۨۧۙۨۚۖۘ۠۟ۖۘ۠ۖۥۦۡۘۘۘ۠ۙ۟ۡۘۥۨۛۖۘۘۘۜۜۡ";
                                                    while (true) {
                                                        switch (str40.hashCode() ^ (-967558521)) {
                                                            case -1770055976:
                                                                str40 = "ۖۨ۟ۛۚۧۜۘۦۜۘ۟ۙۘۦ۟۫ۘۡۡۥۘۚ۠ۥۘۚۙۢۘۘۖۤۙۖۚۡۧۘ";
                                                                break;
                                                            case 992694026:
                                                                Toast.makeText(activity, "无法加入QQ群或未安装手Q", 0).show();
                                                                break;
                                                            case 1240018371:
                                                                String str41 = "ۦۜۘۘۖ۬ۡۖۡۜۘۥۖۙۖۗ۫ۢۡۢۜۘ۬ۦۘۚۥ۟ۧۜۧۦۢۖ۫ۜ۫";
                                                                while (true) {
                                                                    switch (str41.hashCode() ^ (-368208542)) {
                                                                        case -1991162084:
                                                                            str40 = "ۢ۠۫۬۠ۦۦ۟ۥ۟ۚۥۥۤۨۘۛ۬۫۟ۛۖۘ۟ۡۙ۟ۥ۫ۧ۫۫ۚۜۧۘ۬ۡۧۥۜۜۜ۟۠ۗۗ۠ۜۗۤ";
                                                                            continue;
                                                                        case -1648315114:
                                                                            str41 = "ۜۨۜۘۚۨۙۖۥۥۥۤ۬ۚۗ۬۬ۡۖۗۥۡ۬ۢۙۙۚ۬۟ۤۤۜۘۘۛۙۥۤۢۗۖۤ۟۠۠ۦۘ۠ۜۧۘ";
                                                                            break;
                                                                        case 563410832:
                                                                            str40 = "ۙۜۦۦ۬ۜۙ۫ۛ۫ۤۦۘۤۖ۫ۧۖۘۧۦۥۜۜۥۘ۠ۚۧۜۤۖۨ۫ۢۜۤۨ۫ۛ۬ۙۚۦۦۖۘۖۙۗ";
                                                                            continue;
                                                                        case 772846777:
                                                                            if (!Utils.joinQQGroup(activity, str3)) {
                                                                                str41 = "ۦۘ۬ۙۙۗۙۨۢۢ۬ۨۘ۠۟ۥۧ۫ۚ۫ۤۘۘۢۗ۠ۡۗۛۧۙۙۤ۫۟ۚۥ۬ۡ۬ۨۘۨۦۦۘۦۢۗۙۡۘۦۛۜۘۖ۠ۖ";
                                                                                break;
                                                                            } else {
                                                                                str41 = "۠ۖۧۛ۟ۜۘۖۢۢۜ۠ۥۦۢۨۘۙۚۘۘ۠ۙۦۘۦۧۡۘۤۦۙ۠۬ۦۘۡۘۚۤۧۡۘ";
                                                                                break;
                                                                            }
                                                                    }
                                                                }
                                                                break;
                                                            case 1759968582:
                                                                Utils.sendCallback(activity, str2, str);
                                                                break;
                                                        }
                                                    }
                                                    break;
                                            }
                                        }
                                        break;
                                    case 398006287:
                                        try {
                                            Intent intent6 = new Intent("android.intent.action.VIEW", Uri.parse(str3));
                                            intent6.addFlags(268435456);
                                            intent6.putExtra("shell_protected", true);
                                            activity.startActivity(intent6);
                                            Utils.sendCallback(activity, str2, str);
                                            break;
                                        } catch (Exception e6) {
                                            Toast.makeText(activity, "无法打开网页", 0).show();
                                            return;
                                        }
                                    case 508266128:
                                        str25 = "۬ۨۧۗۜ۬ۗ۟۟ۘ۬ۚۘۖۛۡۘۘ۬ۘۛۦ۬ۡۘۦۤۨۘۜۥ۬ۧ۠ۡۜ۬۫۫۬ۖۖۢۤ۟ۤۥۙۛۦۘۤۗۡۘۘ۫۟";
                                        break;
                                    case 2080348259:
                                        String str42 = "ۖۚ۟۠۟ۦۛۡۦۘ۠ۚۨۘۥۚۘۥ۬ۘۚۧۛۢۖۘۘۘۦۘۛۡۛۦ۫ۘۘۢۛ۫";
                                        while (true) {
                                            switch (str42.hashCode() ^ 169672123) {
                                                case -2008298173:
                                                    str25 = "ۥ۫ۥۖۨۥۘۙۖ۬ۘۤۜۤۖۡۘۧۘ۫ۤ۬۬۟ۙۜۢۤ۟ۘۤ";
                                                    continue;
                                                case -1891304729:
                                                    if (i2 == 1) {
                                                        str42 = "ۤۥۨ۟ۢۘۜ۬ۖۧ۟ۦۚۡۘۧۨۛ۫ۙ۬ۦۙۦۡۤۘۘ۫ۙۜۘۦۡۦۘۛۥۘۘۧۙۧۥۧۗ";
                                                        break;
                                                    } else {
                                                        str42 = "ۜ۟ۦۘۡۚۘۘۤۡۢۗ۫ۨۘۙۨ۫ۥۧۗۡ۫ۨۘۧۥۗ۠ۖۢۢۧۚۤۖۥۚ۫ۢۘ۟۬ۦۜۡ";
                                                        break;
                                                    }
                                                case -1800210783:
                                                    str25 = "۫ۜۚۜ۬ۤ۠ۘۖۗ۟ۗ۠ۤ۠ۚۘۥ۟ۜۘ۬۫۠۟۫ۗ۫ۘۘۛۥۢ۠ۡ۬";
                                                    continue;
                                                case 1234907235:
                                                    str42 = "ۖۦۢۥۤۢۖ۟ۚۦۥۢۙۘ۠ۥۚۖۛۡۘۥۥۗۥۥۜۘۤۥۖۘ۟ۡۜۗۚۨۗۘۤۤۚۘ۫۬۬ۧۢۡۙۤۢۜۘۡ";
                                                    break;
                                            }
                                        }
                                        break;
                                }
                            }
                            break;
                        case -1092718519:
                            break;
                        case -844422789:
                            String str43 = "ۛۚۨۘۙۙۚۥۡۘۧ۫ۛۢۢۖۘۦۧۤۛۤۦۘۙ۬ۘۘ۟ۢۘۘ۬ۧۥ۟۬ۜۘۜۡ۬ۜۜۢۧۙۨ۠ۧۨۘۛۘۢۧۚۙۘ۬ۡۘ";
                            while (true) {
                                switch (str43.hashCode() ^ (-1737763152)) {
                                    case -2071657817:
                                        if (i2 == 0) {
                                            str43 = "ۢۖۖۘۦۨۘ۠ۚۖۘ۫۟ۧۥۦ۫ۢۘ۫۟ۙۥۘۛۖۨۘ۫ۦ۬۫ۡ۠ۖ۫ۤۖۖۦۘۙۖۚۖۡ";
                                            break;
                                        } else {
                                            str43 = "ۖۢۡۘۦ۠ۛۨۘ۫ۜۚۢۨۙۥ۬ۡۥۖۚۜۘۛۚ۠ۖۢۙۥۧ۠ۚۡۖۘۨۜۧۘۖۘۖۘۖ۫۫۟ۨۘۥۤۡۘ۬۠ۙ۟۬ۤ";
                                            break;
                                        }
                                    case -1655177622:
                                        str24 = "ۨۤۖۚ۬ۧۦۡ۫۠ۧۤۘۚ۬ۢۘۢۚۗۢۨۤۗۤۗۤۜۜۦۦ۠ۥۗۚۨۘ۫ۜ۫ۢۗۨۘۨ۫ۦۙ";
                                        continue;
                                    case -54885038:
                                        str43 = "ۨۧۛۦ۟ۜۛۦۢۤۡۖۘۚۖۤۥۡۨۢۘۢۡۛۡۥ۟ۤۗ۬ۧ۟ۢۤۥۨۢۡۦۛۙۙۢۚۜۧۘۤۥۙ";
                                        break;
                                    case 2054769397:
                                        str24 = "ۢ۟۟ۤۚۨۡۘۖۜ۫ۥۗ۫ۜۗ۬ۢ۬ۛۚۤۗ۠۠۟ۧۜۗۡ۠۠ۤۛ۠ۥۘۙۥۗۢۧ۬ۡ۬۬۬۬ۡۘۚۥۜۜۢۦۘ";
                                        continue;
                                }
                            }
                            break;
                        case 238785976:
                            str24 = "ۢ۬ۢۛۖۖ۟ۤ۫ۚۗۜۥۘ۫۟ۗۘۘ۫۬ۗۖ۫۫ۙۤۨ۫ۜ۟۫۫ۡۨ۫ۗۙۗۚۚ۟ۜ۟ۢۛ۫ۢ۠";
                            break;
                    }
                }
                break;
        }
    }
}
