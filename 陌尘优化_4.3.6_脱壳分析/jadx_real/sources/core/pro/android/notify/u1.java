package core.pro.android.notify;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.Spinner;
import android.widget.Toast;
import java.util.Set;

/* loaded from: /workspace/unpacked/classes2.dex */
public final /* synthetic */ class u1 implements View.OnClickListener {
    public final int a;
    public final Object b;
    public final Object c;

    public /* synthetic */ u1(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        Object obj = this.b;
        Object obj2 = this.c;
        switch (this.a) {
            case 0:
                Set<String> set = k2.closedPopupIds;
                ((DialogInterface.OnClickListener[]) obj)[0].onClick((AlertDialog) obj2, -1);
                break;
            case 1:
                Set<String> set2 = k2.closedPopupIds;
                ((DialogInterface.OnClickListener[]) obj)[1].onClick((AlertDialog) obj2, -2);
                break;
            case 2:
                Set<String> set3 = k2.closedPopupIds;
                ((DialogInterface.OnClickListener[]) obj)[2].onClick((AlertDialog) obj2, -3);
                break;
            default:
                Activity activity = (Activity) obj2;
                Set<String> set4 = k2.closedPopupIds;
                String str = (String) ((Spinner) obj).getSelectedItem();
                String str2 = "۬ۧۤۛۥ۟۬۠ۛ۟۬ۤ۠ۢۥۨۘ۫ۥۢۦۘۥۛۨۚۜۨ۫ۧ۟۠ۨۨ۬۫ۘۘۢ۫ۢۨۦ";
                while (true) {
                    switch (str2.hashCode() ^ (-192237785)) {
                        case -538764861:
                            String str3 = "ۢۧ۫۬ۚۥۘ۠۫ۧۦ۫ۢ۟ۜۙۖۢۚ۟ۗۘۛۤۚۥ۟ۢۖۧۨ";
                            while (true) {
                                switch (str3.hashCode() ^ 171566618) {
                                    case -1732111154:
                                        str2 = "ۙ۟ۥۘۜ۬ۨۘۡۖۡۤۥۤ۬ۗۥ۟۠ۛۢۤۙۤۥۘۘۦۙۢۤۤ۠";
                                        continue;
                                    case 62492537:
                                        str2 = "ۧۚۨۦۚۦۘۗۙۜۥۘ۠ۥ۬ۨۘ۟ۙۘۘۛۖۛۧۚۡۘۙۜۦۦۦۡ";
                                        continue;
                                    case 592861792:
                                        if (str == null) {
                                            str3 = "ۡۖۘۢۖ۬۬۟ۜۘۧۚۖۘۛۚۨۘ۟ۢۡۘۖۙۜۛۖۧۘۚۖۥۢۦۛ۟ۜۢۜۘۤ";
                                            break;
                                        } else {
                                            str3 = "ۢۙۛۥۖۙۖۙۥۧۢ۬۫ۜۗۤۨ۬۬ۡۘۧۨ۬ۤ۬ۦۚۦۦۘ۟ۜۧۘۚۨ";
                                            break;
                                        }
                                    case 1128297665:
                                        str3 = "ۚ۠۟ۢۛۜۥۗۨۘۜ۫ۧ۟ۨۧۦۙۦ۬۟ۦۙۚۥۘ۠ۖۦۘۗۢۜ";
                                        break;
                                }
                            }
                            break;
                        case 1539275054:
                            str2 = "۫ۢۗۤۦ۬۠۫ۨ۫ۗ۠۟ۢۖۛ۠ۨۘۧۚۡۘ۠ۡ۟ۢ۬ۥ۠ۤۙ";
                            break;
                        case 1900164192:
                            String str4 = "ۚۜۢۛۚۨۘۛۘۘۢ۬ۢۜ۟ۥۘۖۨۧۘۢۖۤۘۤ۟ۦۚۢۚ۠ۙ۠ۘۦۦۘۗ";
                            while (true) {
                                switch (str4.hashCode() ^ (-1357073582)) {
                                    case -1568687722:
                                        String str5 = "۬ۧۖۛۥۢۙۡۖۥۡۘۖۨۛۗۘۧۗۖۜۥۦۖۖۧۦۨۘ۟ۛ۬۟ۦ۬";
                                        while (true) {
                                            switch (str5.hashCode() ^ 1982066189) {
                                                case 11288369:
                                                    str4 = "ۧۨۡ۠ۖ۫ۙۨۡۘۙۨ۬ۛۙۨۧۙ۠ۦۘۡۘۗ۫ۡ۫ۛۦۘۖۤۢۖۨۖ۫ۢۢۛۤۗ۟ۤۢ";
                                                    continue;
                                                case 331118694:
                                                    str4 = "ۢۚ۫۟ۗۦۘۦ۫ۥۛۨۛۦۡۨ۟ۡۜۘۧۜۥۙۦۖۘۜۥۧۘۨۛۖۙ۬ۖۙ۫۠ۡۗۨۗۘۤ۟ۦۧۡۛۤۚۘۥۘۦۦۙ";
                                                    continue;
                                                case 623894647:
                                                    if (!str.isEmpty()) {
                                                        str5 = "۫۫ۡۘ۟ۥ۫ۡۦۘۚۦۚۘ۫ۖۘۥۦۡۘۡۤۚۤۢۥۘۛۙۘۘۖۚ۬۟ۦۤۜ۫۫ۡ۬ۡۧۗۧۚۡۡۘۘۧۘۘۛ۠ۢۗۢ۬";
                                                        break;
                                                    } else {
                                                        str5 = "ۘۚۛۙۙۦ۠۠ۦۘ۬ۜۖۘ۠ۦۦۛۖۘۖ۬ۜۘ۟ۚۜۘ۠ۦۡۘۤۖۖۛۘۖ۬۠ۨ";
                                                        break;
                                                    }
                                                case 1874536516:
                                                    str5 = "ۛۚ۟ۙۘۨۗۨۥۨ۬ۥۚۗۛ۫ۨۡۘ۬ۗ۬ۜۖۘۡۥۛۥۜۤۨۜ۫۫۬ۗ۫ۦۘۘۡ۟۬ۥ۟ۚۤۡۥۘۛۙۥ";
                                                    break;
                                            }
                                        }
                                        break;
                                    case 46024824:
                                        str4 = "ۥۦۥۘۚۘۙ۫ۜ۫ۡۥۥۗۙۧۨۘ۠ۡۤ۟ۨۙۖۦ۟ۤۘۡ۬ۡ۬ۤۨۚۘۤۥۜۘۖۡۥۘۗ۫ۡ";
                                        break;
                                    case 377274922:
                                        break;
                                    case 1268684089:
                                        try {
                                            Intent intent = new Intent();
                                            intent.setClassName(activity.getPackageName(), str);
                                            activity.startActivity(intent);
                                            break;
                                        } catch (Exception e) {
                                            Toast.makeText(activity, h.d("7XfRKO6ceuOcG81ut7gsob9brXHI\n", "C/5CzVIcnUk=\n", new StringBuilder(), e), 0).show();
                                        }
                                }
                            }
                            break;
                        case 1973562846:
                            break;
                    }
                }
                break;
        }
    }
}
