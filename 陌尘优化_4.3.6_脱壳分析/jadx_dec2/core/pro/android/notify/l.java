package core.pro.android.notify;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.pm.PackageManager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.Toast;
import gTBLD.dev.XSSTG.free.Utils;
import j$.util.Objects;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: /workspace/unpacked/classes2.dex */
public final /* synthetic */ class l implements Runnable {
    public final int a;
    public final Object b;
    public final Object c;

    public /* synthetic */ l(Object obj, Object obj2, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
    }

    /* JADX WARN: Code restructure failed: missing block: B:76:0x019e, code lost:
    
        r5 = false;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:49:0x00fc. Please report as an issue. */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void run() {
        WindowManager windowManager;
        String str;
        boolean z;
        boolean z2;
        switch (this.a) {
            case 0:
                View view = (View) this.c;
                ((n) this.b).getClass();
                try {
                    windowManager = (WindowManager) view.getContext().getSystemService("window");
                    str = "ۛۘۨۨۛۢ۬۟ۚۥ۫ۚۢ۠ۖۡۛۗۢ۠ۗۢ۟ۤۨۧۖۧۤۡۘۤۖۘۖۦۚ۬ۤۡۘۗۚۥۘۚۡۙ۬ۢۡۘ۟ۛۨۘۚۢۧ";
                } catch (Throwable th) {
                    System.out.println("[DialogHook] removeViewImmediate 失败：" + th);
                    return;
                }
                while (true) {
                    switch (str.hashCode() ^ (-636603615)) {
                        case -1839777563:
                            String str2 = "ۡۛۦۖۛ۬ۨۜ۟ۗۨۡۜۜۨۜۧۢ۫ۗۦۘ۬۫ۗۡۦۜۘۤۨۚۚۖۦ۫ۨۧۘ";
                            while (true) {
                                switch (str2.hashCode() ^ 8525096) {
                                    case 253449242:
                                        str = "ۦۦ۟ۙۙ۠۫ۢۙۜۡۥۨۘۛۗۨۨۨۥۚۨ۠۟ۥۧ۟۟ۡۢ۬ۛۘۘۗۗۤ";
                                        continue;
                                        continue;
                                    case 1168524017:
                                        str = "ۦۖۡۜۙۥۘ۠۟ۥۘۖۙۘۘۨ۠ۚۢۛۜۘۖۘۜۘۡ۫ۡ۟ۢ۫ۗۛۦۘۡ۟ۨ۠ۜۡۥۥۡۤ۟ۥۘۧ۠۬ۗۙۘۘۥۡۨۗۜ۫";
                                        continue;
                                    case 1498108391:
                                        str2 = "۟ۖۧۗۦۖۦۘۙۥۘۜۛۛۨۘۥۦۜۧۥۦۤۡۦ۠۬ۜۘۜ۬ۘۤۦ۟ۚۡۖ۬ۘۙ۠";
                                        break;
                                    case 1584140742:
                                        if (windowManager == null) {
                                            str2 = "ۨۗۘۧۡۘۥ۫ۛۜۚۦۘ۟ۙۨۧ۠ۖۘۦۦۢۥۜۘۘۘۦ۬ۨۗۜۡۨۚ۠ۗۜ۟۟ۜ۫ۜۡۘۥۦ۟ۨۦۘۘ";
                                            break;
                                        } else {
                                            str2 = "ۡۘۗۗۙ۫۠ۨۛۦۙۘۘۢۦۢ۫۬ۦۗۤۘۘۡۤ۠۟۫ۡۘۘ۠۫ۖۚۥۤ۬ۛۨۗۡۘۢۥ";
                                            break;
                                        }
                                }
                            }
                            break;
                        case 23787936:
                            return;
                        case 633892668:
                            str = "۬ۢ۟ۙۨۛۖۦۖۨۘۘۘۜۦۦ۫۟ۖۘۚۙۚ۟ۡۦۚۡۡۘ۬ۡۥۘۤۥۦۘۚۡۖۘۘۢ۬۫۫ۦ۫ۗۥۘۖۗ۟ۛۥۖۗ۫ۨۘ";
                            continue;
                        case 1572458814:
                            windowManager.removeViewImmediate(view);
                            System.out.println("[DialogHook] removeViewImmediate 成功");
                            return;
                    }
                    System.out.println("[DialogHook] removeViewImmediate 失败：" + th);
                    return;
                }
            case 1:
                String str3 = (String) this.c;
                int i = x0.e;
                x0 x0Var = (x0) this.b;
                x0Var.getClass();
                try {
                    x0Var.a.getWindow().getDecorView().setTag("shell_protected" + str3);
                    return;
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
            case 2:
                String str4 = (String) this.b;
                Dialog dialog = (Dialog) this.c;
                synchronized (k2.a) {
                    Integer num = (Integer) k2.b.get(str4);
                    String str5 = "ۛۛۚۧۨۦۘۚۧۖۦۤ۠ۜۖۚۖۖۜۘۥۢۚ۫ۤۨۢۧۛۗۘۚۛ۬ۡۘۢۛۖ";
                    while (true) {
                        switch (str5.hashCode() ^ 754289369) {
                            case -1979833557:
                                String str6 = "۠ۖ۫ۜۙۗ۠ۤۦۘۖ۬ۜۥۜۧۘۜۦۜۘ۫ۛ۟۟۫ۛۡ۬ۖۘ۬ۥۥۘۦۚۖۛۥۖ۟ۚ۫ۖ";
                                while (true) {
                                    switch (str6.hashCode() ^ 1481125314) {
                                        case 1347817056:
                                            String str7 = "ۡۡۚۡۜ۠ۦۤۦۘ۠ۘۛۘۥۜۥ۫ۛۛۙۚ۠۠ۥۘۦۙ۟۫۫ۘۘۦۗۖۘۘۢۦۘ۟ۨۨۘۦۖۡۘۗۗۦۘۘۜۥۘ";
                                            while (true) {
                                                switch (str7.hashCode() ^ (-293894462)) {
                                                    case -1657349664:
                                                        str6 = "ۡۘ۫ۤ۟ۚ۟ۚۧۘۦۧۖۗۚۖۧۥۘۙۙۧۨۦ۬۟ۘۤ۫ۛۡۘۧۢۨۘ۠ۚۢۥۢۘۘۦ";
                                                        continue;
                                                    case -669297709:
                                                        str6 = "۬ۙۜۗۘ۟ۙۗۛۛۘۡۚۥ۠ۙۗۡۖۢۚۛۦۛۨۡۨۢۡ۟ۧۢۗۥۙۥۘۘۢۨۘۛۖۥۜ۬ۦۡۧۖ";
                                                        continue;
                                                    case -129365119:
                                                        if (num == null) {
                                                            str7 = "۠ۗۨ۬ۘ۫ۤۖۡۘۗۢۡۦ۟۫ۘۖۜۘ۠ۛ۟ۤۢۜۖۦ۫ۧ۠ۖۦ۬ۘۘۦ۠ۦۘۛۨ۫ۚۨۨۘۙۤ۬۟۫ۥۥۗۤۤ۠ۦ";
                                                            break;
                                                        } else {
                                                            str7 = "ۚۜۤۚۜۧۨۧۡ۫ۦ۫ۦۖۘۤ۠ۦ۟۫۟۠ۗۥۘ۬ۧۨۘ۬۫۠ۢۤۜۗۦ۟";
                                                            break;
                                                        }
                                                    case 390224000:
                                                        str7 = "ۛۚۗۧۢ۫۠ۨ۠ۗۛۥۘۧۨ۬ۘ۬ۦۘۢۛۢۥۖۚۥۨۡۘۡۚۢ۬ۛ۠ۨۛۥۡۧۥۗۚۗ۟ۖۚۚۤ";
                                                        break;
                                                }
                                            }
                                            break;
                                        case 1483998364:
                                            break;
                                        case 1606262118:
                                            String str8 = "ۡۘۥۘۦۤ۫ۤۚۥۘۦۧۛۚۥۨۘۤۥۢۡۛ۫ۚۛۘۜۨۤۗۧۧۤ۠ۤۨۘۤۤۚۧۤۢۘۖۖۙۖۜۘ";
                                            while (true) {
                                                switch (str8.hashCode() ^ (-1233642143)) {
                                                    case -1816016150:
                                                        String str9 = "ۥۙۢۚۢۙ۬ۢۥ۫ۖۡۜۧۖ۫ۥۡۘ۠۠ۚ۬ۤۦۘۚۥۡ۫ۙۚ";
                                                        while (true) {
                                                            switch (str9.hashCode() ^ 185299419) {
                                                                case -1110954036:
                                                                    str8 = "ۦۗۡۘۥۙۡۘۤۚۛۙۚۜۚۚۨۘ۟ۙۖۜۖۨۘۛۨۙ۟ۥۛ۬ۢ۠ۖۘۘۘ۬ۛۛ۬ۗۛۤۖۡۘ۫ۜۥۘۢۘ۟۟ۜۘۧۜۥ";
                                                                    continue;
                                                                case -1091284830:
                                                                    str9 = "ۜۨۙۘ۬ۢۡ۫۟ۧۛ۬ۧۧۥۘۛۢۢۦۡ۬ۚۖۦۘۦۜۡ۠ۘۖۘۢ۟ۖۘ۟ۦۜۘۖۧۖۘۙۗۛۨۥۡ۬ۡ۫ۧۚۨۘۨۗۙ";
                                                                    break;
                                                                case -619422989:
                                                                    if ((num.intValue() & 2) != 0) {
                                                                        str9 = "ۗۤۦۘۡۜ۠ۖۨۡۘۗ۬۠ۧۜ۠ۡۥۦۡ۠ۧۡۦۖۘۨۜۙ۫ۘۨۨ۟ۖۘۗۧۥۡۛ۬۬ۡۘۗ۠۠ۛۡۛۥۘۡۙۨۗ";
                                                                        break;
                                                                    } else {
                                                                        str9 = "ۚۡۘۘۙۥۗۤۗۚۗۚۧۡۗ۟ۖۨۙ۬ۙ۟ۨۘۙ۫ۧۘۖۥۘۘۢۘۛۧۡۘۙۖ۬ۜۖ۠ۢ۬ۗۨۧ۠";
                                                                        break;
                                                                    }
                                                                case 487264328:
                                                                    str8 = "ۧۡۥۙۨۗۤۦۡۘۜ۠ۨۘۦ۫۫ۛۜۚۨۗۙۨۥۤ۫ۛۜۘۗۨۨۘۡۢۥۘۛ۫ۘ";
                                                                    continue;
                                                            }
                                                        }
                                                        break;
                                                    case -788671754:
                                                        str8 = "ۗۗۦۨۨۦۤۦۥۘۨ۠ۘۡۛۧ۬ۙۦۘۢۖۘۙۥ۬۟ۛ۬ۥۚ۫ۘۤ۬ۚۙۦۘ۟۠ۜۧۥۤ";
                                                    case -242960116:
                                                        break;
                                                    case 670623640:
                                                        z = true;
                                                        break;
                                                }
                                            }
                                            break;
                                        case 1914538800:
                                            str6 = "ۚۚۢ۟ۙۚۘۨۨۨۛ۠۠ۤۤ۠۬ۡۗۨۘۗۦۛ۫ۚۢۨۘۗۦۖۛۦ۫۫ۥ۫ۗ۟۟ۙ";
                                    }
                                }
                                break;
                            case -691783145:
                                break;
                            case 459591296:
                                str5 = "ۨۤۨۘۨۦۨۘ۠۠ۖۘۗ۟ۜۘۤۖۨ۟ۥۤ۠ۛۦ۫۠ۥۘۛ۬ۥۦۚۦۘۚ۫ۜۘ۫ۨۡۚۛۨۦۨۚۨۘۘۤ۟ۜ";
                            case 759324691:
                                String str10 = "ۧۦ۫۬ۥۖۘۘ۬ۥ۫ۧۡۘ۬ۤۤ۟۟۬۬ۥۨۙۨۜۘ۬ۛ۫ۖۗ۫ۢۨ۠ۥ۫ۗۢۤۢ۠ۘۗۡۥۘۜۢۦۘۙ۟۫ۚۤۦۘ";
                                while (true) {
                                    switch (str10.hashCode() ^ (-1354669919)) {
                                        case -1943013564:
                                            str10 = "۠ۦۖۜۤۨ۠ۧۧۖۜۥۘۡ۫ۖۘۖۦۛۦ۠۠ۙ۬ۚۛۛ۬ۜۘ۠ۜۢ۬ۘ۫ۙ۫ۥ۫ۡۖ۬ۖۗۙۚۖ";
                                            break;
                                        case -1246412771:
                                            str5 = "ۧۤۘۦۥۘۖۖۥۚۚۖۘ۠ۤۤ۠۫۟ۤۦۥۥۜۦۘۖۘۜۨۤۖ۠ۡ۬ۧۢۡۘ";
                                            continue;
                                        case 124450999:
                                            if (!str4.equals(k2.c.get(dialog))) {
                                                str10 = "ۛۛۨۘۙۤۥۨ۬۬ۦ۠۫ۧۖۥۧۘۜ۫ۥۚۢ۠ۜ۠ۧۛۘ۠ۨۛۦۘ۫ۢۨۘۛۗۥۙ۟ۖ۟ۚ۠ۛۧۡۘۨۧۥۤۦۥۘ";
                                                break;
                                            } else {
                                                str10 = "ۢ۬۬۠۟ۛۦۖۡۘۛۤۖۡ۬۟ۤۚۙۙۚۙ۟۟۟ۡۨۨۢ۫ۖۘۨۘۘۛۗۚۡۜۨۘ۠ۚ۠ۚۡۖۘۤۡۥۘۡۦۦۧ۬ۗ";
                                                break;
                                            }
                                        case 980096830:
                                            str5 = "۟ۤۤۡۘۦۗۜۦۘۛۜۧ۫ۘۨۙ۠ۚۧۘۧۘۢۘۜۘۢ۠ۛۖۙۚۨۚۚۡ۟ۧۚۙۛۘۚۦۘۦۦۛۜۤۘۘ";
                                            continue;
                                    }
                                }
                                break;
                        }
                    }
                }
                String str11 = "ۨ۫ۦۘۛ۫ۛۘۖۙۨ۟۫ۖۤ۫ۘۡۘ۬ۡۜۘۦۛۥۘۦ۟ۡۤۛۖۦۡۖۘۙۤۜ";
                while (true) {
                    switch (str11.hashCode() ^ 480219027) {
                        case -1786583631:
                            return;
                        case -1689430942:
                            str11 = "ۙ۫ۦۘۨۛۨ۬ۥۧۘۡۦۡۥۖۡۨۧۘۦۙۖۨ۟ۖۘۘۥ۫ۤ۫ۦۖۨۧۘۢۘۚ۬۬ۖۘ۫ۛۖۘ";
                            break;
                        case 177946513:
                            String str12 = "ۖۥۤۚۢۖۗۡۨۚۘۜۢ۟ۤۖۥ۠ۤۛۜۛۥۖۛۢۜۤۗ۫۠ۢ۫ۧۙۙۥۘ۟۫ۜۦۘۚۥۖۥ۟ۧ";
                            while (true) {
                                switch (str12.hashCode() ^ 1407937144) {
                                    case -1182256301:
                                        break;
                                    case -192693506:
                                        String str13 = "ۖۥۧ۟ۙۜۤۨۡ۬ۚۢۢۗۙ۬ۜۤۙۧۘ۟ۗۘۛ۫ۘۧۡۗ۫ۢۚۗۥۘ";
                                        while (true) {
                                            switch (str13.hashCode() ^ (-2128655631)) {
                                                case -1374328557:
                                                    str12 = "ۖۛۢۨۗۨۘۚۛۧۧۧۡ۟ۤۡۘۧۤۖۘۧۡۛ۬ۢ۠۟۟ۜۙۖۘۦۙۗۨۙۖۘۦۛۧۤۜۙ۬ۙۗۗۥۧۜ۫ۜۘ۫ۖۥ";
                                                    continue;
                                                case -1258880910:
                                                    if (!dialog.isShowing()) {
                                                        str13 = "ۥ۠ۥۘۡ۬ۡۘۘۦۧۘۨۦۢ۫ۜۘۗۙۛۧ۬ۘۗۦۨۖۚۢۚۖۧۘۢۗ۠ۤۗۨۘۚۧۧۙ۟ۚۖۙۨۘۗۚۚۡۢۨۘ۠ۘۡۘ";
                                                        break;
                                                    } else {
                                                        str13 = "ۨۛۘۘۡۢ۟ۙ۠ۚ۟ۘۘۘۢۛۤ۬ۨۘ۬ۚ۫ۙۡۘۨ۬۠۟ۦۘۖۡ۠ۜۥ۬۠۠۟ۦ۟ۨۘۥۦۦۚۧۨۡۤۨۘۜ۫ۜ";
                                                        break;
                                                    }
                                                case -364804213:
                                                    str12 = "ۘۦۥۘۨۖۜۖ۬ۖۘ۫ۖ۟۠ۛۘۘۧۧۛۚ۠ۙ۠ۛ۫۠ۘۘ۫ۤۛۜۖۡۘ۬ۢۢ";
                                                    continue;
                                                case 1502927573:
                                                    str13 = "ۢۚۧ۠ۚۚ۠ۧ۟ۘۜۛۢۢۥۛۢۡ۬۟ۖ۟ۗۤۘ۫ۘۘۢۧۙۢۜۥۜۖۨۘ";
                                                    break;
                                            }
                                        }
                                        break;
                                    case 1589581932:
                                        str12 = "ۡۤۡۛۘۧ۠ۡ۟۠ۤۡۗۦۦ۟ۢۡ۠۬۠ۖۨ۫ۨۛۘ۬ۤۖۘ۟ۛۜۥۘۘۦ۠۬ۗۨۜۘۤۡۦۘۢۥۛ";
                                    case 1999079087:
                                        String str14 = "ۛۦۚۜۙۖۘۛۘۨۦۦۗۤ۟ۙۢ۫۬۠ۤۤ۫ۚۙۤۤۜۘۗ۟ۦۨ۟۠۫ۛۗ۠ۦۧۘۖۚۨۘ";
                                        while (true) {
                                            switch (str14.hashCode() ^ (-1462750634)) {
                                                case -1217097580:
                                                    str14 = "ۜۗ۟۫ۦ۫۟ۗۙۖۚۘۘۡۘۡۘۙۖۨۘۙ۫ۗ۠۫ۖۘۖۛۜۢۚ۫ۤ۟ۢۦۦۨۘۚۘۡۙ۠ۖۘۢۚۘۧۚۘ";
                                                case -990842307:
                                                    View decorView = dialog.getWindow().getDecorView();
                                                    String str15 = "ۧۚ۫ۗۘۘۘۚۦۢۦۘۤۥ۫ۖۘۗۘۦۢۚۜۘ۫ۨۗۥۖۖۘۨ۫ۢۥۛۘۧ۫ۡۘ";
                                                    while (true) {
                                                        switch (str15.hashCode() ^ (-1896463201)) {
                                                            case -1550140304:
                                                                String str16 = "ۡۦۦۜۦۦۖۘۤۜۙ۠ۙۜۘۧۢۡۘۜۥۧۡۖۙۜۨۦۘۧ۠۠ۘۛۢ";
                                                                while (true) {
                                                                    switch (str16.hashCode() ^ 673147975) {
                                                                        case -243286108:
                                                                            str15 = "ۦۨۚ۠ۘۜۘۙۧۢۗۘۘۥۤۡۦۗۛۚۡۤۚۛۢ۫ۦۖۘ۬ۡۚۜۜۨۘۧ۫ۤۢۘۨۘۤ۠ۨۘ۫ۦۚۤۢۦۘ۬ۧۖۘۧۧۜۘ";
                                                                            continue;
                                                                        case -219935008:
                                                                            if (decorView == null) {
                                                                                str16 = "ۤۥۥۘۡ۟ۢۨ۬ۖۤۗۘ۟۬ۦۘۜۦۨۤۛۜۘۙۘۚ۠ۛۨۘۜۡۘۘۖۖ۟۫ۘۥۘ";
                                                                                break;
                                                                            } else {
                                                                                str16 = "ۤۗۚۡۚۜ۬ۖۨۘۖ۬ۘۘ۫۟ۗۢۗۚ۬ۦۗۥۙۨۘۥ۬ۦۖ۫۠ۨۧۗ۟ۥ";
                                                                                break;
                                                                            }
                                                                        case 406761272:
                                                                            str16 = "۟ۧۦۘۚۙۖۘ۬ۛۡۢۢۨۘۙ۫ۦۢ۠ۘۘۘ۠ۥۙۛ۬ۤۦۘۘۗۖۨۘ۫۟۠ۛۖۜۘۙۙۡۥۡۘۧۙۜۘۥ۫ۖ";
                                                                            break;
                                                                        case 1711286282:
                                                                            str15 = "ۨۡۘۘۡۥ۬ۤۢ۫ۗ۫ۛۙۙۢۢۚۙۢۛۚۤۥۘۧۨۨۘ۫ۖۦۘۘۧۥۦ۬ۨۘ";
                                                                            continue;
                                                                    }
                                                                }
                                                                break;
                                                            case -1326147585:
                                                                str15 = "ۛۤۘۘ۫ۚ۬۟ۛۗۥۨۦ۠ۜ۬ۛۗۤ۠۟۠ۛۦۨۘ۟ۤۚ۟ۚ۬";
                                                            case -1089087892:
                                                                break;
                                                            case 294556187:
                                                                String str17 = "ۖۦۜۘۢۖۘۘۜۛ۬ۗ۫ۨۘۧ۫ۘۘۖۘۗ۠۬ۘۛۚۚ۟ۜۘۘۜ۟ۨۘۙ۠ۗ۟۟ۘۘ";
                                                                while (true) {
                                                                    switch (str17.hashCode() ^ (-1545556068)) {
                                                                        case 408356504:
                                                                            str17 = "ۜۙ۬ۗۢۘ۟ۦۤۤۙۤ۠ۗ۫ۦ۠ۜۘۤۥۦۘۤۛۦۘۤۤۖۘ۬ۖۖ";
                                                                            break;
                                                                        case 744617741:
                                                                            String str18 = "ۢۥۘۦۢ۟ۦۙۥۦۧۥ۬ۡۦۢۛۖ۫ۖۜۘۗۢ۟ۢۖۢۧ۟ۢ۟۫۠ۛۡۜۙۜۧۙۖۡۢۥۡۥۜۘۛ۬۠ۧ۬۠";
                                                                            while (true) {
                                                                                switch (str18.hashCode() ^ (-2070679493)) {
                                                                                    case 260292164:
                                                                                        str18 = "ۧ۠ۥۘۙ۟ۦۘۘ۟ۜۙ۫ۤۗ۬ۖۡۧۗۖۢۡۜۨۙ۫ۢ۬ۥۘ";
                                                                                        break;
                                                                                    case 478043364:
                                                                                        str17 = "۫ۥۨۤۖ۠ۚۥ۬ۚ۬۫ۘۢۛۤۘۙۨۛۗۢۢۨۘۛ۫ۧ۟۫۟";
                                                                                        continue;
                                                                                    case 727441077:
                                                                                        if (!decorView.isAttachedToWindow()) {
                                                                                            str18 = "ۗۖۘۘ۟۬ۖۘۙ۟ۨۥۥۦ۟۬۬ۢۤۥۖۘۦۘۘۖۙۜۖۖۘۨۧۖۘ۬۫ۚ۫ۨۜۘ";
                                                                                            break;
                                                                                        } else {
                                                                                            str18 = "ۡۡۥۘ۫ۖۧۘۖۧۜۚ۫ۘۜۛۘۤۤۖۜۨۡۘ۠ۘۙۨۗۦ۫ۖۡۘۚۥۙۢۙۦۘۜۥۚۢۡۦۘۖۖۗ۫۫ۨ";
                                                                                            break;
                                                                                        }
                                                                                    case 1755439069:
                                                                                        str17 = "ۚۦۦۘۥۖۗۧۨۖۙۖۘۘۦۘۘۙۡۦۘ۫ۜۘۘۙۡۦ۠ۘ۫ۖ۬ۜۢۘۤۥ۬ۜ";
                                                                                        continue;
                                                                                }
                                                                            }
                                                                            break;
                                                                        case 1776944104:
                                                                            String str19 = "ۙۜۛۚۦۧۤۥۚۡۖۢۨۛۙۚۨۘۜۨ۬ۢۜۘۨ۫ۡۘۜۨ۫۫ۥۗۧۛۗۤۨۜ۬ۦۧۘۙۢ۟ۗۗۦۨۜۜۧۚ";
                                                                            while (true) {
                                                                                switch (str19.hashCode() ^ (-1345925577)) {
                                                                                    case -1702437810:
                                                                                        String str20 = "ۗۥۨۖۙۨۙۛۚۗۜۛۧۤۥۘ۫ۥۧۘۚۥۢ۟۬ۥۘۤۢۦۘۜۧ۬ۜۡۛۗۘۛۘۘۥۘۜۡۚۧۡۜۘۙ۟ۤ";
                                                                                        while (true) {
                                                                                            switch (str20.hashCode() ^ 1691475847) {
                                                                                                case -1153256050:
                                                                                                    String str21 = "ۥۨۦ۫ۖۢۜۤۛۖ۟ۦۥۖۡۤۦۘۘۧۖۦۘۧ۫ۛۙۛۦۡۗۡۜۧۛۧۨۤۖۗۤ۠ۥۘ";
                                                                                                    while (true) {
                                                                                                        switch (str21.hashCode() ^ 6471933) {
                                                                                                            case -914080751:
                                                                                                                String str22 = "ۤۧۨۨۘۨۦۜۡۘۜۗۜ۬ۧۘۘ۫ۨۘۘۡۗ۬ۖۤۢۗۘۜۘ۟ۦۖۘۜ۠۟ۡۢۘۘۦ۠ۚ۬ۨۨۘ۟ۥۜۦۢۘۘ";
                                                                                                                while (true) {
                                                                                                                    switch (str22.hashCode() ^ 60335079) {
                                                                                                                        case -1986167975:
                                                                                                                            str21 = "۠ۙۥۖۡ۫ۥ۫ۘۜۚۢۗۧ۬۠ۥ۬۟ۘۘۘۨ۠ۤۙۤۘۘۥۡۜۗۚۡۘۡ۫ۖ";
                                                                                                                            continue;
                                                                                                                        case 723629135:
                                                                                                                            str21 = "ۗۤۥۘۡۤۡۘۜۤ۬ۥۙۘۢۡۨۨۜ۫ۜ۫ۚۜۥۡۚۦۦۘۜۖۢۢۖۢ۫ۛۤۜ۬ۥۧۘۥۚۧۛۙۙ";
                                                                                                                            continue;
                                                                                                                        case 1483928495:
                                                                                                                            str22 = "ۚۙۜۘۚۚۙۗ۬ۗۦۨۗۘۤۡۖۨ۟ۤۛۡۥۚۖۥ۟۬۠ۨ۫ۤۙۘۥۛۢۨۢۡۘ۠ۙۦۘۘ۠ۦ";
                                                                                                                            break;
                                                                                                                        case 1916808674:
                                                                                                                            if (decorView.getAlpha() <= 0.01f) {
                                                                                                                                str22 = "۫ۚۘ۫ۨۛۢۚ۫ۡۚۖ۫ۖۜۜۧۦۘۘۡۢۧۥۜۘۘ۠۟ۧۖ۠";
                                                                                                                                break;
                                                                                                                            } else {
                                                                                                                                str22 = "ۘۦۚۗۘۦۘ۫ۤ۠ۨۡۢۥۙۡۘۨۗۢۧۨۡۖ۬ۥۘۤۧۧۦۘۡۘۗۡۦۘۜۥۘ";
                                                                                                                                break;
                                                                                                                            }
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            case -525195501:
                                                                                                                z2 = true;
                                                                                                                break;
                                                                                                            case -264772606:
                                                                                                                break;
                                                                                                            case 1816245321:
                                                                                                                str21 = "ۙۖ۠ۥ۟ۢ۟۫ۦۘۤ۟ۢۙۡ۟ۨۗۚۦۚۦۜۚ۠ۜ۫ۡۘۛ۫ۡۛۜۨۘۢۚۨۘ۬ۖ۬ۖۜۡۨۖۨ۠ۘۧۘ";
                                                                                                                break;
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case -869451609:
                                                                                                    break;
                                                                                                case 1350593986:
                                                                                                    str20 = "ۡۙۡۘۘۡۛۥۗۗ۫۬ۜۘۜۤۦۨۘۦۗۥۥۚۜۦۡۡۡۘ۟ۤ۬ۤۜ۫۠ۨۘ";
                                                                                                    break;
                                                                                                case 1612981839:
                                                                                                    String str23 = "ۚۡۛ۠۠ۦۘ۟۠ۘۤۘۙۘۚۦۖۤۡۘۡۜۖۥۛۚ۬ۗۧۖۡۧۡۨۛۥ۟۬ۧۗۜۨ۬ۘۘ";
                                                                                                    while (true) {
                                                                                                        switch (str23.hashCode() ^ 379677258) {
                                                                                                            case -1791278496:
                                                                                                                str20 = "۫ۥۡۘۘۥۙۢۨۙۥۥۜۥۢۖۛۖۨ۟۬۟ۡۖۘۥۘۖۨۘۦ۟ۜۗ۫ۤۗۜ۠ۜۜۥ۟ۜۧۡۚۙ۟ۙۜ۠ۙۜۘ";
                                                                                                                continue;
                                                                                                            case -1447980290:
                                                                                                                if (decorView.getVisibility() != 0) {
                                                                                                                    str23 = "ۘ۠ۢۨ۠ۘۖۚ۬ۨۙ۫۬ۖۖۜۜۘۘۧۚۨ۟ۖۘۦ۫ۜۘۛۤۛ۫ۛۘۘۥ۬ۖۘ";
                                                                                                                    break;
                                                                                                                } else {
                                                                                                                    str23 = "ۥ۬۫ۜۤ۫ۘۘۦۘ۠ۥۜۙۜۡ۫ۦ۫ۤۛۜۘۢۤۥ۬ۥۤۤۦۥۢۖۦۘۥۢۙۖۡ۫ۢۜۘۜۗۤۙۜۘۦۛ۫ۦ";
                                                                                                                    break;
                                                                                                                }
                                                                                                            case -451496886:
                                                                                                                str23 = "ۦۗۚۡ۬ۜۥۛۛۦۘ۠ۗۘۨۘۗۘ۬ۖۧۚۡۛۨۘۖۘۜۦۘ۬ۢ۟ۥۘۖۗ۬۠ۡۜۘ۫۬ۗ۠ۛ۬ۡ۠ۦ";
                                                                                                                break;
                                                                                                            case 1843649556:
                                                                                                                str20 = "ۨۥۘۘۗۨۖ۫ۙۨۘۥۨۘۘۘۢۛۙۘۦۛ۟۠۟۠ۦ۟ۨۦۖۛۗ۫ۧۧۢۦۚۜ۠ۖۡۦۨۨۦۨۘۚۗۖۘۗۘۜۘ۫ۜۧۘ";
                                                                                                                continue;
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case -1584754803:
                                                                                        String str24 = "۫۟ۙۖۚۡۘۢۥۡۧۜۧۧۗۢۨۥۦ۠ۙۙۧ۫ۢ۬ۤۖۘۤ۠۟۠ۧۦۘۢۢۛۨۢۡۨۢۥ";
                                                                                        while (true) {
                                                                                            switch (str24.hashCode() ^ (-1178960304)) {
                                                                                                case -2057845175:
                                                                                                    str19 = "ۢۜ۫ۛۚ۬ۤۗۗۡۘ۠ۚۥۜۘۧۖۥ۫ۜۦۘۥۨۛۖۘۘۘۗۙ۬۬ۜۚۦ۬ۖ۬ۦۘۘۦۧۘۙۚۘۖۖۘۘ";
                                                                                                    continue;
                                                                                                case 630730718:
                                                                                                    str19 = "ۚۡۘۘۖۨۘۨ۬ۖۚۗۖۘۦۨۘۜۥۙۤۨ۫ۗۖۥۢۤۜۘ۫ۚ";
                                                                                                    continue;
                                                                                                case 1384874670:
                                                                                                    str24 = "۬ۗۙۥ۬ۖۘ۟ۚۘۚۛ۬ۛ۬ۖۘۙۛۦۘۧۥۚ۟ۗۥۦۘۚۙۘۘ";
                                                                                                    break;
                                                                                                case 1936546038:
                                                                                                    if (decorView.getWindowToken() == null) {
                                                                                                        str24 = "ۨ۠۫ۧ۟ۖۖ۬۬ۦۛۗ۠۫ۛ۬ۚۤ۟ۡ۟ۖۖۨۘۢۚ۬ۙ۟ۖۘۤۙۗۤۦۦۤ۫ۦۘ۟ۡۧ۠۟ۨۘۧۙۘ";
                                                                                                        break;
                                                                                                    } else {
                                                                                                        str24 = "ۘۧۖۘۖۛۘۘۚۧۙۙۧۧ۠ۦۖ۠ۥۦۘۥۥۖۘ۠ۢۡۘۤۛۙۡۦ۫ۘۛۜۢۥۡۜ۟ۛۦۛۢۧۚۗۦۗۨۨۥۛۜ۬";
                                                                                                        break;
                                                                                                    }
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case -1044406346:
                                                                                        break;
                                                                                    case -67951313:
                                                                                        str19 = "ۢۘۜۘ۬ۤۗۖۗۜۘۛۨۦۘۛۤۦۘۙۖۚۚۢۙۖۤۤ۟ۨۧۘۦۛۦۙۛۢ۫ۥۛۛۥ۠ۚۗ";
                                                                                        break;
                                                                                }
                                                                            }
                                                                            break;
                                                                        case 1961765037:
                                                                            break;
                                                                    }
                                                                }
                                                                break;
                                                        }
                                                    }
                                                    break;
                                                case -647221854:
                                                    z2 = false;
                                                    break;
                                                case 1826254004:
                                                    String str25 = "۟ۢۚۦۧۖۘۙۗۥۨۛۡۖۥۨۦۜۥۙ۟۫ۛۡۛۤۙۨۨۢۤ۬ۗۘۦۘۛۡۜ۟ۢۚ";
                                                    while (true) {
                                                        switch (str25.hashCode() ^ (-88964281)) {
                                                            case -1140380279:
                                                                str14 = "ۘۥۧۘۦۤۘۙۖۜۘۤۢۧۤۨۘۥ۫ۛۘۜۡۥۡ۬۟۫۬ۙ۫ۗ۠ۖ۠ۢۧ";
                                                                continue;
                                                            case -811015291:
                                                                if (dialog.getWindow() != null) {
                                                                    str25 = "ۧۨۢۛۚ۠ۛۛۥ۫ۘۘۘۡۨۥۜۖۘۘۖۧۘۘۛۥۘۧ۠ۘۙۗۧ";
                                                                    break;
                                                                } else {
                                                                    str25 = "ۘ۟ۢ۬ۘۚۛۥ۫ۜۥۧ۠ۨۖۜ۠ۜۘ۟۬ۨۘ۫۬ۢۦ۠ۢۡۡۦ۫ۜ۫۟ۘۧۘۤۨۖۘۥۛۡۦۘۧۘ۟ۗۡۘۙۨۘۘۨ۠ۧ";
                                                                    break;
                                                                }
                                                            case 1702354510:
                                                                str25 = "ۛۨۨۘ۫۬ۘۡۤۛۨۛۨۘۤۦۤۗۢ۟ۥۡۨ۬ۛۜۘۦ۠ۘۘۦۥۗۦۗ۫ۖ۫ۨۢۡۘۤۨۘ";
                                                                break;
                                                            case 1844372710:
                                                                str14 = "ۚۚۦۘۡۜۜۘۤۧۥۡۥۜۘۚۙ۬ۡۘۨۦۘۚۧۖۥۦۚۨۘۗ۫ۘۗۥۥۘۦۨۚۘۜۗۨۤ۠ۧۥۗ۟ۥۘۚ۟ۤۜۥۧ";
                                                                continue;
                                                        }
                                                    }
                                                    break;
                                            }
                                        }
                                        break;
                                }
                            }
                            z2 = false;
                            k2.h(str4, dialog, z2);
                            String str26 = "ۦۡۛۤۦۘۘۨۜۥۘۘۧ۬ۥۢۜۘۢۚۜۘۗ۬ۙۥ۫ۥۘۗۤۥۘۧۛۚ";
                            while (true) {
                                switch (str26.hashCode() ^ (-921297652)) {
                                    case -1449226392:
                                        k2.logToFloatingWindow(h.e("CW1dULSgwb02dQ1SpfOSuzZ2DUqm89enL2dJH+Q=\n", "WQItJcSAstU=\n", new StringBuilder(), str4), "warning");
                                        return;
                                    case -1414591419:
                                        str26 = "۠ۚ۠ۗۦۧۘ۟ۥۘۘۨ۫۠ۜۡۥۘۤۨۘۧ۟۬ۨۡۘۚۖۧۘۡ۫ۥۘۡۨۦۘ۫ۤۥۚ۬ۤۛۧۡۘۥ۫ۥۥۧ۬ۛۘۙۜۡۨۘ";
                                        break;
                                    case 888861638:
                                        String str27 = "ۤۛۘ۫ۜۘۡۜۘۘۘ۠۠ۚۨۗۚۦۧ۟ۧۡۦۦۨۗۢ۫ۙۛۥۘ۟ۥۜۢۧۜۖۧ۫ۦۛۛ۬ۦ۠۬ۤۘۘۖۘۧۗۦ";
                                        while (true) {
                                            switch (str27.hashCode() ^ 1917946951) {
                                                case -1962697443:
                                                    str26 = "ۢۧ۬ۚۗۜۘۨۜۨۜۚۥۘۨۥۘۡۡۜۘۧۗۡۥۜ۫ۖۦ۫۬ۖۘ";
                                                    continue;
                                                case -1250353401:
                                                    if (!z2) {
                                                        str27 = "ۦۘۨ۫ۗۨۚۗۡۗۗۙۘ۟۟۠۬ۤۡۧۜۘۧۛۡۥ۫ۘۜۦۢۜۛۡۖۘۛۖ۠ۜۦۨۗ۟ۛۨۘۘۥۖۘۢۖ۟ۖۖۘ";
                                                        break;
                                                    } else {
                                                        str27 = "۠ۜۦۘۥۘ۟ۙۢۧۦۨۘ۠ۚ۟ۨۥ۟ۙۗۗۙۗۦۘۡۙۡ۟ۚۨۘۚ۫ۥۨۙۥۘ";
                                                        break;
                                                    }
                                                case -457005121:
                                                    str26 = "۠ۚۘ۬ۘۜۘۤۡۧ۠ۜۧ۫ۗۙۘۤۚۢۥۧۘ۟ۨۗۥ۫ۚۘ۟ۘۘ";
                                                    continue;
                                                case 341170943:
                                                    str27 = "ۦۘۥۘۧ۬ۢۥۗۖۡۨۦۘۖ۠ۖۘۧ۫ۥۘ۬ۦۧ۬ۖۥۗۛۙۤ۬ۖۚۘۥۧۚۜۘ۠ۧۡۘۢۤۚ۬۫ۨۘ۠۫ۥ";
                                                    break;
                                            }
                                        }
                                        break;
                                    case 1832113188:
                                        return;
                                }
                            }
                            break;
                        case 807690512:
                            String str28 = "ۢ۠ۦۘۜ۬ۚۦۢۨۗۨۡۥۦۘۥۥۡ۟ۖ۠ۤۛ۫ۘۛ۠ۡ۟ۚ۬ۦ۟ۚۙۢ۬ۨۥۘۚۧ۟ۗۙ۬ۡۘۘۘۦۚۦ";
                            while (true) {
                                switch (str28.hashCode() ^ 656396248) {
                                    case -1723717122:
                                        str28 = "ۛۥۖۡۨۜۦۛۜۜۖۤۦۘۨۘ۟ۤۡۘ۠ۜ۫ۗۦۜۢۦ";
                                    case -659301988:
                                        str28 = !z ? "ۥۢۥۛۚۥۛۛۜۢۢۖۛۡۖۚۗۥۢۘۘ۬ۜۦۨۨۥۘۢۜۨۖ۫۬ۘ۟ۤ۬۫ۡۘۜۖۨۘۢۢۤۢ۠۫" : "ۖۙۥۧۚۜۤۤۨۜۨۙۚ۫ۖۘۨۚۖۘۚۨۘۢۙۦۦۘۘۘ۠ۡۤ";
                                    case 1469833889:
                                        str11 = "ۜۘۙۧۙۥۘۗۦۡ۬۫ۦۚۜۥۘ۫ۙ۫۟ۦۧۘۦۦۡ۬ۦۜ۫ۦۧ";
                                        break;
                                    case 1758539796:
                                        str11 = "ۡۦ۫۫ۨۚۤ۫ۙۖۦۗۨۢۦ۫ۨۜۖۤۘۢۜۗۤۜۢ۫ۛۛۧۡ۬ۦ۠ۖۡۘۤ۠ۢۦۘ۟ۜۡۚ۫";
                                        break;
                                }
                            }
                            break;
                    }
                }
                break;
            case 3:
                Set<String> set = k2.closedPopupIds;
                Utils.a((AlertDialog) this.b, (int[]) this.c);
                return;
            case 4:
                Set<String> set2 = k2.closedPopupIds;
                Toast.makeText((Activity) this.b, "提交异常：" + ((Exception) this.c).getMessage(), 0).show();
                return;
            case 5:
                String str29 = (String) this.c;
                q3 q3Var = (q3) this.b;
                q3Var.getClass();
                String str30 = "ۨۚۖۘۖۧۗۘۥۜۗۨۘ۠۫ۖۘۥۤۦۘۙۡۡۘۖۜۥۘۡ۬ۘۖۗۙۧ۫ۢ۫ۜ۬ۖۦۘ۟ۗۨۘۡۢۘۘۥۗ۬ۜۛۗۗۖۚ";
                while (true) {
                    try {
                        switch (str30.hashCode() ^ 1693003507) {
                            case -1651965529:
                                String str31 = "ۗۡۜۘ۬۟ۡۘۧۨ۫۟ۧ۟ۖۥۘۜۤۖ۫ۦۘ۠ۧۨۘ۟ۦۜ۬ۨۘۗۦۢۤۡ۫۫ۚۦۛۡۨۢۤۨۜۥۛ";
                                while (true) {
                                    switch (str31.hashCode() ^ 275542454) {
                                        case -1731148592:
                                            str30 = "ۨۙ۠۫ۥۢۨۙ۫ۗۘۜۗۨۜۤۧۖۖۡ۠ۤۜۙ۫ۦۥۙۚۚۨۜۙ۬";
                                            continue;
                                        case -1428784451:
                                            if (!Objects.equals(str29, "pong")) {
                                                str31 = "ۙۢۨۘۤۦۜ۟ۖۜۖ۫ۚۤۢۘ۬ۨۘ۠ۚۖۦۡ۫۫ۧۧۘۧۘ۬۬۬ۢ۬ۘۡۙۜۘۚۧۥۘ۟ۡۘۘ۠ۡۡۨ۟ۚۖ۫";
                                                break;
                                            } else {
                                                str31 = "۟۫ۘ۟ۜۘ۠۬ۢۨ۫ۦۘۥۚ۫ۘ۠۫۬ۡۨۦۥۦۥۢۥۛ۫ۧ";
                                                break;
                                            }
                                        case -666141005:
                                            str30 = "ۢ۟ۘۨ۬ۥۖۨۘ۫ۨۡۘۨۘۖۘ۠ۚۙۨۗۖ۬۬ۡۘ۟ۧ۬۟ۚ۠۟ۢۖۘۧ۫ۚ";
                                            continue;
                                        case -248123718:
                                            str31 = "ۧۦۧۘۖۖۨۖ۬ۨۘۧۙۘۜۦۖۘۖ۬ۥۘۛۜۥۚۢۚۖۢ۟ۙۗ";
                                            break;
                                    }
                                }
                                break;
                            case 964025143:
                                str30 = "۠ۥۗۖۘۧۨ۠ۥۘۦ۬ۧۧ۠ۨۘۘۥۚۢۥۡ۟ۙۚ۬ۨۘۥۘۥۘ";
                                break;
                            case 1200252524:
                                Log.w("WebSocketClient", "pong");
                                return;
                            case 1203679480:
                                JSONObject jSONObject = new JSONObject(s0.decrypt(str29, "1234567890abcdef"));
                                String strOptString = jSONObject.optString("type", "");
                                String strOptString2 = jSONObject.optString("message", "");
                                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
                                HashMap map = new HashMap();
                                String str32 = "ۚ۬ۚۛ۟ۤۜۘۤ۬ۚۜۚۖۜۘۥۚ۠ۙۢۙۗۢۜ۠ۧۘۛۢ۫۬۠ۧۜ۟ۜۡۚ۬ۖۗۚۗ۠ۤۖۢ۠";
                                while (true) {
                                    switch (str32.hashCode() ^ 781457046) {
                                        case -1949584648:
                                            String str33 = "۬ۤۡۘۧۗۦۥۙۙ۠۬ۨۘۥ۟ۚۨۚۖ۫۟۠ۧۙۨۛۨۡ۫ۢۢۘۙۚ۫۟۠ۦ۫ۧۡ۫ۧۧ۠ۖۘ۫۫";
                                            while (true) {
                                                switch (str33.hashCode() ^ 1656307707) {
                                                    case -1817423290:
                                                        str32 = "۫۬ۜۡ۫ۜۘ۫ۥۜۗۡۨۦۗۥۚ۫۟۫۫۫ۦۨۘۥۜۜۘ۫۠ۨۘۜۘۘۘۧۙۢۖۦ۫ۤۙ۠ۘۦۨۘ";
                                                        continue;
                                                        continue;
                                                    case 1261073917:
                                                        str32 = "ۧ۠۠ۖۧۘۢ۬۠۟۬۠ۜ۟ۖۘۘۤ۫۠ۚۦۘۙ۟ۡۖۧۡۘۘۖۘۘۡۨۖ۫ۛۦ";
                                                        continue;
                                                    case 1831650768:
                                                        str33 = "ۨۜۖۘ۬ۨۧ۫ۚ۠ۧۤۗۨۤۗۨۨۡ۟ۘۗ۟ۡۧۧۗۡۘ۬ۖۡۤۛۗۙ۬ۙ۠ۛ۟ۙ۟ۜ";
                                                        break;
                                                    case 1833388179:
                                                        if (jSONObjectOptJSONObject == null) {
                                                            str33 = "۬۠ۖ۠ۚۧۨۥۙۛۘۥۧۡۘۧ۠ۨۘ۟ۦۜۘۥۨۢۛۡۜۘۥ۠ۛ۫۫ۦۨۦۘۢۡۡۘۡۧۜ۫ۨۜۥۚ۬ۗ۫ۦۘۚۜ۠";
                                                            break;
                                                        } else {
                                                            str33 = "۫ۧۤ۟ۨۨۘۡۜۘ۫۫۫ۨ۫۟ۡۘۧۘ۬ۡۤۥۙۨۘۗۙۢۥۙۦۧ۬ۡۜۖۘۗۦۨۢ۟۠ۘۢۤۙۨۘ";
                                                            break;
                                                        }
                                                }
                                            }
                                            break;
                                        case -1306463511:
                                            break;
                                        case 322229342:
                                            str32 = "ۙ۟ۦۜۤۘۜۘۘۡۛۡ۟ۥ۟ۙۢۥۘۖۘۙ۠ۖۚ۫ۧۖۙۗ۟۫ۧۨۘۨۡۨۦۥۦۘۦۨۦۘۡۙۘۦۥ۠";
                                            continue;
                                        case 375724685:
                                            Iterator<String> itKeys = jSONObjectOptJSONObject.keys();
                                            while (true) {
                                                String str34 = "۠ۡ۬ۧۖ۫ۤۧۢۨۧۜۧۘۧۦۙۖۤۜۢۜۖۧۘۡ۬ۨۘۡۖ۫۠۠ۗۛۡۖ۠ۖۧۘ۠ۡۨۘۚۥۦۘۙۚۖ";
                                                while (true) {
                                                    switch (str34.hashCode() ^ 1731757537) {
                                                        case -1231577500:
                                                            String str35 = "ۖۡۗۡۚۖۥۜۡۤۤۤ۟ۢۧۨۖۢ۫۫ۢ۠ۜ۠۠۬ۘ۬ۡۜۘۘۗۢۛ";
                                                            while (true) {
                                                                switch (str35.hashCode() ^ (-1252300043)) {
                                                                    case -1169145055:
                                                                        str35 = "ۨۥۡۜۨۙ۠ۥۥۥۜۚۗۧۥ۟ۤۡۘ۠ۧۥۡ۠ۡۘۗۢۛۥ۫ۚۗۛ۬ۢۨ۟ۘۡۨۘ۫ۥ۠ۜۘۦۜۦۖۘۛۚۖۘ۠ۖ";
                                                                    case -154826675:
                                                                        str35 = itKeys.hasNext() ? "ۚۘۢۦ۫۫ۦۛۧۨ۬ۗۖۙۨۧۨ۫۬ۚۚۛۚۢۦۛ۠۠ۨ" : "ۙۜۖۘۚۢۜۘۘۚۧ۫ۚۨۘۜۙ۟ۚۡۜۚۚۧۚ۟ۗۗۖۥۚۥۢۦ۟ۜۘۥ";
                                                                    case 1338678515:
                                                                        str34 = "ۦۖۧۘۜۘۘۤۨۚۗ۟ۘۥۤ۫ۘۖۙۢۛۤۘۢۦۦۥۢۥۗۤۦۦۗۢ۠۠ۨۘۧ۟ۥۘ۟ۨۨۘ۫ۧۨۘ";
                                                                        break;
                                                                    case 1642557553:
                                                                        str34 = "ۡۧۜۘ۫ۧۤۜ۫ۗۗۜۥۘ۫ۦۖۦۘۘۧۨ۠۫ۧۦۘۙۜۨۜۜۥۘۗ۫ۥۖ۫۠";
                                                                        break;
                                                                }
                                                            }
                                                            break;
                                                        case -921150355:
                                                            break;
                                                        case -520465013:
                                                            str34 = "ۧ۬ۨۘ۠۬ۛۙۗ۟ۢۧۦۢۧۦۘ۫ۨ۬ۗۨۤ۬ۛۛ۠ۨۚۜ۬ۜۛۚۧۥۡۘۡۦۜۘۡۧ۠ۢۧۦۙ";
                                                        case 218201390:
                                                            break;
                                                    }
                                                    break;
                                                }
                                                String next = itKeys.next();
                                                map.put(next, jSONObjectOptJSONObject.optString(next, ""));
                                            }
                                            break;
                                        default:
                                            continue;
                                    }
                                }
                                s3.c(q3Var.a, strOptString, strOptString2, map);
                                return;
                        }
                    } catch (JSONException e2) {
                        return;
                    } catch (Exception e3) {
                        return;
                    }
                }
                break;
            default:
                Activity activity = (Activity) this.b;
                JSONObject jSONObject2 = (JSONObject) this.c;
                t3.U9oqb6XR(activity, jSONObject2);
                t3.snfR8HbO(activity, jSONObject2);
                t3.EQ7YPPSJ(activity, jSONObject2);
                try {
                    t3.OWRFQJrb(activity, jSONObject2);
                    return;
                } catch (PackageManager.NameNotFoundException e4) {
                    throw new RuntimeException(e4);
                }
        }
    }
}
