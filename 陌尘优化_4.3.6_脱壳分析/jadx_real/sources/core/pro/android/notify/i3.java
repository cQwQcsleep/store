package core.pro.android.notify;

import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class i3 implements View.OnTouchListener {
    public float a;
    public float b;
    public int c;
    public int d;
    public final WindowManager.LayoutParams e;
    public final WindowManager f;
    public final FrameLayout g;

    public i3(WindowManager.LayoutParams layoutParams, WindowManager windowManager, FrameLayout frameLayout) {
        this.e = layoutParams;
        this.f = windowManager;
        this.g = frameLayout;
        this.c = layoutParams.x;
        this.d = layoutParams.y;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        WindowManager.LayoutParams layoutParams = null;
        String str = "ۘۙۘۗۘۨۙۦ۠ۢۖۤۖۥۡۘۡۘۧۧ۟ۗۘۜۜۢۚۨۘ۠ۜۨۘۘۖۖۘ۟ۢ۠ۦۛۦ۠ۢۚ۬ۥۨ۟۬ۨۘ۟۠ۥۨۙ";
        float f = 0.0f;
        float rawY = 0.0f;
        float rawX = 0.0f;
        int action = 0;
        while (true) {
            switch ((((str.hashCode() ^ 631) ^ 968) ^ 792) ^ (-207261463)) {
                case -1929553468:
                    this.b = motionEvent.getRawY();
                    str = "ۤۘۜۖۖۜۘۦۧۨ۟ۢۛ۠ۥۖۤ۟ۦۘ۫ۘۦۙۨ۬ۘۢۥۘ۟۟۟";
                    break;
                case -1882325115:
                    this.d = layoutParams.y;
                    str = "ۨۚ۟ۛۗ۬ۜۘ۬ۗۜۡۡۘۦۘ۬ۗ۟۟ۛۡۘۥۜ۬۬ۦ۠ۙۧۥۘۤۧۘۨ۬ۨۧۜۛۢۖۨۘ۫ۖۢ۠۬ۥۘ۠۠ۡۘۙۦۡۘ";
                    break;
                case -1760296039:
                    return true;
                case -1603237397:
                    this.f.updateViewLayout(this.g, layoutParams);
                    str = "ۚۦۜۢ۠ۤۘ۠ۖۘۧۢۦۘۡ۠ۤۥۧۘ۫ۡۧۘۦۧۙۗ۠ۜ۫ۨۘۘۥۧۙۚ۟ۖۘ";
                    break;
                case -1562155810:
                    str = "ۤ۠ۘۘۤ۟ۦۚۦۦۛۜۤۛ۠ۨۘۨۦۜۘۥۧۧۤۥۘۘۨۛۙۡۜۤۖۛۥۘۦۢۜۘ";
                    layoutParams = this.e;
                    break;
                case -1344406022:
                    layoutParams.x = (int) (this.c - (rawX - this.a));
                    str = "۟ۖۖۘ۬ۛ۫ۦۜۢۡۚۡۘۥۗۘۘۤۧۥۖۢۡۡۦۨۘۥۚۡۘۗۛ۟ۘۛۡۘ۫ۙۢ";
                    break;
                case -1162507396:
                    String str2 = "ۢۚۧ۬ۖۖۡۤ۫ۤۤ۠۬ۚۛ۫ۥۖۘ۟ۦۨ۫۫ۖۗۚۖۘۧۦۚۙۗۡۘۦۨ۟";
                    while (true) {
                        switch (str2.hashCode() ^ (-2030491546)) {
                            case -1645504478:
                                str = "ۧۚۖۘ۬ۤۗۚۥۤۨۥۘۧۙۖۨۛۨ۬ۗۢۖۚۢۗۨ۫ۙۥۖۘ";
                                continue;
                            case 1267648477:
                                String str3 = "ۡۧۢ۟ۡۦۘۛۢۥۘۙۦۜۚ۫ۘۚۚ۟ۚ۠ۖۜۥ۠ۦ۫ۚۚۡۢ۟۬ۡ";
                                while (true) {
                                    switch (str3.hashCode() ^ 1940895222) {
                                        case -1694460196:
                                            str3 = action != 1 ? "ۤۧۥۘۖۗۛۦۢۜۘۛۡۦۘۖۧۙۜۙ۫ۘۧۤ۬ۥۗۤۖۙۡۢۦ۫۠۬ۡۥۢ" : "ۧۖۧۥۛۦۛۜۜ۟۬ۡۥۡۨۘۖ۠ۖۘۚۗۥۘۤۦۙ۫ۚۤۥۗۤ۫۬ۗۢۚۨ";
                                        case 1563264768:
                                            str2 = "ۜۧ۠۠ۦ۠ۤۚۗ۫ۚۤ۠ۛ۟۟۬ۘۘۦۗۖۧۚۛۖۖۖۛۛۢۨۜۧۘۨۡ";
                                            break;
                                        case 1893550206:
                                            str3 = "ۢۥۜ۠ۨۗۘۤۡۥۗۥۢۤۛۛۦۘ۠ۧۗۖۛۖۨۤۧۤۧۚۤۛۜۙ۫ۦۜۚ۠ۥ۟۟ۢۡ۬ۖۦ۬";
                                        case 2143854915:
                                            str2 = "ۧ۬۟ۚۘۖۘۢ۟۟۠ۨۜۘۧۗ۟ۗۨۥ۬ۙۥۖۡۖۘۡۛۜۡۘۖۘۖۧ۠۫ۡ۠ۥۥۥۘ۟۠ۦۗۛۨۘ";
                                            break;
                                    }
                                }
                                break;
                            case 1692279459:
                                str2 = "۫۫ۥۤۨۥۘ۠۬ۨۘ۟۠ۛۦۧۖۢۧۥۚۖۖۘۗۧ۬ۢۚۙۡۨۘۢۙۙۦۖۨۘۛ۟ۘۘ۬۟ۖ۬ۚ۟ۛۢۜ";
                                break;
                            case 1824469476:
                                str = "۫ۡۤ۫ۛۦۘ۠ۜۧۘۢۦۨۘۗۦۘۨۜۘۘۦۤۚۖۡۤۢۙۙۢۛۨۘ";
                                continue;
                        }
                    }
                    break;
                case -1030881101:
                    str = "ۜۧۡۘۚۜ۬ۨۡۘۘ۠ۨۧۤ۬ۘۗۨ۠ۧ۠ۜۤۧ۠ۦۚۨۘ۟ۦۛ۟ۜۥۤۘۘۗ۬ۘ۠۫";
                    action = motionEvent.getAction();
                    break;
                case -736535593:
                    String str4 = "ۜۤۢۥۨۥۘۘ۫ۨۘۢۤۧۦۡ۫۠ۙۜۘۙۨۦ۠ۡۘۙۡ۟ۧۗۗۡۡۧ۬ۙۡ";
                    while (true) {
                        switch (str4.hashCode() ^ (-402621911)) {
                            case -1795119185:
                                str = "۠ۛ۬ۨۨۥ۫ۖ۬ۦۖۘۘۘۚۥۘۢۤۢۧۙۥ۠ۙۜۘۙ۫ۢۛ";
                                continue;
                            case -1404991366:
                                str4 = "۫۬ۥ۫ۖۘۦ۫ۨۤۖۛۨۢۥۘۡ۠ۨۖۛۥۘۙ۬ۦۘۤۧۖۘۗۥۖۘۗۖۦۘۤۗۘ۬ۛۚ۠ۗ۟";
                                break;
                            case -1363450757:
                                str = "۬ۦ۫ۘ۠ۜ۠ۥ۬ۤ۟ۨۘۥ۫ۨ۠ۦۢۗۛ۟۠ۙۡ۫۟۟۫ۦ";
                                continue;
                            case 962451353:
                                String str5 = "۠ۦۥۘۡۗۜۢۥۖۜ۫ۜۘ۟ۡۥۘ۫ۛ۠ۗ۟ۜۦۢۛۛۥ۬۫ۗۨۘ۬ۦۧۘۦۢۦ";
                                while (true) {
                                    switch (str5.hashCode() ^ 1274573222) {
                                        case -562276150:
                                            str4 = "۬ۗ۟ۡۦۡۘۛۦ۫ۜۘۨ۬۠ۛۢۡۘۛۚۤۜۘۛۡ۫۠ۡۧۧۤ۬ۗۗۦۦ۬ۤۨ۬ۤ۠";
                                            break;
                                        case 518334816:
                                            str4 = "ۨ۫ۛۛ۫ۜۘۥۨۧۘۖۤ۫ۜ۟ۦۘۨۚۨۤۡ۠ۛ۠ۘۘۧ۠ۘۘۢۧۨۥۧۧۡۤ۠ۡ۟ۚ۬ۦۦ";
                                            break;
                                        case 555815524:
                                            str5 = "ۦۦۖۘ۫ۙۜۘۢۖۘۘۘۖۢ۬۠ۨۛۢ۬ۛۙۥۘ۠۬ۡۘۧۦۘ۠۬۬ۧۚۙۡۖۧۨ۫ۦۘ۫ۧ۫ۥۦۜۘ۫ۛۗ";
                                        case 1497746064:
                                            str5 = action != 0 ? "ۨۡۘۘۚۖۜۗۘۛۤ۫ۘۘۦۗۖ۠ۖۘ۠ۚ۬ۤۚۜ۠۠ۗۤۗۗ۟ۖۘ۟ۨ۬ۤۘۘۜۛ۫" : "ۘ۠ۘۘ۬ۘۗۤۙۜۖۘۘۘ۬ۖۖۨۨۧۧۢۥ۠ۢۖۛۗۜ۬ۢ۟ۦ۠ۙۜۧۨۘ";
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case -355923696:
                    f = this.d;
                    str = "۬ۤۜۘۡۘۧۘۤۡۜۘۤ۟ۡۜ۟ۖۜۧ۫ۨۨۗۖۡۖۘۗۨۥۘۛ۟ۥۘۗۢۤۛۧ۠ۜۡۘۘۙۡ";
                    break;
                case -299046141:
                    str = "ۨۘ۫ۗۚۢ۬ۥۚۢ۠ۛ۬ۜۨۛۥ۫۟ۦۖۡ۟ۛۛۨۘۚۘ۠ۥۖۤۨۧۙۜ۟ۚۨۧۢ";
                    rawX = motionEvent.getRawX();
                    break;
                case -180246649:
                    layoutParams.y = (int) ((rawY - this.b) + f);
                    str = "۟ۛۨۦ۟ۡۖۥۖۘۧۢۖۘۧۨ۫ۚ۬ۛۨۥۚۜۢۖۖۚۘۛۧۡۙۤۚۙ۫ۢۛۙۗۦۤۥۘ۬ۧۛ۬ۥۜ۫ۛ۫ۗ۫ۥ";
                    break;
                case -48801075:
                    str = "ۨۗۖۧۛۤۡۙ۫ۜۛۘۘ۬ۡۙۨۤۖ۬۟۫ۤۨ۫ۨۡۡ۫ۡۨۘ۬ۢۧۗ۫ۤ";
                    break;
                case -36091733:
                    this.a = motionEvent.getRawX();
                    str = "۫۠ۦ۠ۧۜۥۤ۟ۘۡۥۨۡۥۤۤۜۘۖۨۘۘۦۤۚ۟۠ۥۘۙۨۦۙ۟ۧۦۨۥۧ۫ۖۨۛ۬ۖ۬۫۫ۢ۬";
                    break;
                case 27728367:
                    str = "۠ۤۜۘۖ۟ۨۢۤ۠ۡۢۘۙۛۨ۫ۚۦۘۚۧۡ۠۬ۡۙۥۚۘۧۨۤۘ۠ۢۧۜۚۖ۟ۦ۬۠ۛۘۙۖۙۖ";
                    rawY = motionEvent.getRawY();
                    break;
                case 449578287:
                    return true;
                case 1123664251:
                    return true;
                case 1146248232:
                    str = "ۧۦۢۘۤۘۨۛۤۜۥۧۘ۟ۚۨۘۘۡۤ۠ۘۤۥۛ۠ۖۘۢۛۤۜ۫ۡۥۘۛۥۘۘۘۗ۠۬";
                    break;
                case 1489653435:
                    return false;
                case 1733347530:
                    this.c = layoutParams.x;
                    str = "۠ۡۛۗۧۨۥۖۨۘ۬ۚۡۘ۠ۛۡۘۢۗۡ۫۬۟ۘۥۦۘۛۙۙۧۙۛۥۚۢۥۨۥۘ۬ۜۚۗۧۡۘۤۥۦۘۧ۟ۙ";
                    break;
                case 1876331633:
                    str = "ۧ۠ۡ۬ۦۢ۬۬۬ۜۥ۠ۛ۬ۥۘۨۗۨۗۗۛۢۛۜۥۨ۟ۨۡ۬ۡۙ۠۫ۢ۟ۖۗ۠ۦۤۘۨۥ۬ۥۤ۠ۦۧۙۧ۫۬";
                    break;
                case 2136795052:
                    String str6 = "ۗۨۤۧۖۨۘۜۢۘۘۢۤۨۘۥۤۙۧۙۢۨ۠ۖۘۚۧۨۘۙ۠ۥۘۖۢۡۘۢۘۗۤ۟ۜۘ";
                    while (true) {
                        switch (str6.hashCode() ^ 1834493863) {
                            case -979848570:
                                str6 = "ۡۛ۫ۘ۠۟ۛۥۙۨۜۘۦ۫ۨۖۤۢۚۤۜ۟۬ۘۘۧۘۜۡۥۘۚۘۗ۟۟۬۬ۖۥۘۦۗ۟";
                                break;
                            case -357905437:
                                String str7 = "ۚۧ۠ۢۢۜۖ۫۠ۖۨۡۘۧۖۦۙ۠ۘۥۡۤ۠ۨۥۘۧۤۙۧ۟ۤۖۛۙ۟ۗۛۢۗۥۜۡ";
                                while (true) {
                                    switch (str7.hashCode() ^ 1588410570) {
                                        case -2123034943:
                                            str7 = "ۚۘۖۜۘۥ۬ۨۘۗۘ۫ۡۧۜۦۦۘۗۖۖۤ۟۬۬ۛۛۛۦۨۘۧۚۨۚۦ۫";
                                        case 760313404:
                                            str6 = "ۖۥۨ۠ۜۥۤۚۙۦۘۢ۫ۘۨۡۘۖۘۨۛ۫۟ۢۤ۬ۙ۫ۥۛ۬ۡۡۨۡۦۜۘۤۙۖ۠ۚۘۥۥۜ۠ۜ۫";
                                            break;
                                        case 1540955282:
                                            str7 = action != 2 ? "۬ۙۖۦۦۘۗۡ۫ۧ۟۟ۛۘۘۧۜۗۖۘۘۘ۬ۗۧۦۨۛۘۘۧۘۡۜ۠ۦۚ۠۟ۖۧۘۥۛۜ" : "ۨۧ۟ۗۨۦۘ۬۬ۧۗ۟ۥۘۡۨۖۘۜۤۖۘۤۜۥۘ۬ۢۧۜۗۡۢۗۘۤ۫ۧۚۜۛ";
                                        case 1873327872:
                                            str6 = "ۘۗۖۘۜۛۥۘۨۖۦۘۥ۬ۗ۬ۥ۬ۥۖۛۜ۠ۘۘ۫ۙۗۘۘۥۦۛۤۦۘۦ۟۫ۗ۟ۛۜۘۜۡۙ";
                                            break;
                                    }
                                }
                                break;
                            case -114073363:
                                str = "ۚۜۚۡۨۧۜ۟ۨۧۘ۬ۖۧۡ۟ۗ۫ۙۨۙۢۘ۬ۙۘ۟ۨۢ";
                                continue;
                            case 1217383525:
                                str = "ۚۙۦۘۥ۬ۖۥۢۙۙ۟۬۬۠ۙۤۡۗۛۧ۬۬۫ۥۡۗۥۘۡ۟۟";
                                continue;
                        }
                    }
                    break;
            }
        }
    }
}
