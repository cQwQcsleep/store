package core.pro.android.notify;

import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.FrameLayout;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class i2 implements View.OnTouchListener {
    public float a;
    public float b;
    public int c;
    public int d;
    public final WindowManager.LayoutParams e;
    public final WindowManager f;
    public final FrameLayout g;

    public i2(WindowManager.LayoutParams layoutParams, WindowManager windowManager, FrameLayout frameLayout) {
        this.e = layoutParams;
        this.f = windowManager;
        this.g = frameLayout;
        this.c = layoutParams.x;
        this.d = layoutParams.y;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View view, MotionEvent motionEvent) {
        WindowManager.LayoutParams layoutParams = null;
        String str = "ۗۗ۬ۙ۠ۡۨۚۘۘۡ۫ۜۘۧۥۡۘ۫ۖ۫ۥۙۧۛۤۜۘۙۡۨۘۥ۟ۗۨۨۙۗۦۤ";
        float rawY = 0.0f;
        float rawX = 0.0f;
        int action = 0;
        while (true) {
            switch ((((str.hashCode() ^ 483) ^ 974) ^ 224) ^ (-2081226609)) {
                case -1942845448:
                    this.b = motionEvent.getRawY();
                    str = "ۧ۠۬ۦۗۦۗۧۧۧۡۗۨۘ۫ۛۗۤۛۜۦۜۨۘۙ۟ۖۙۥۦۘۗ۟ۘ۠۫۠ۦ۬ۥۘ۠ۨۥۚۖ۫ۦ۠ۛ";
                    break;
                case -1819021605:
                    str = "ۦ۬ۢۧۙۗۗۦۡۥ۟۬۠ۗ۠ۘ۠ۦۧۦۜۘۡ۫ۘۘ۠ۖۦۘۤۤۘۘۛۙۡۘۤۜۢ";
                    rawX = motionEvent.getRawX();
                    break;
                case -1659026595:
                    return true;
                case -1486433084:
                    this.d = layoutParams.y;
                    str = "۬ۚۨۘۜ۬ۥۘ۠ۙۘۡ۠ۘۘۗۗۨۘۙۥۧۦۙ۟ۙۗۥۜۤۛۙۗۙۙۙۥۛۥۖۘ";
                    break;
                case -1136889402:
                    this.a = motionEvent.getRawX();
                    str = "ۧۡۜۘ۬ۜ۫۬ۢ۠ۨۗ۫ۜۢ۬ۖۥ۬ۚۛۗۢۖۛۥۢۙۛ۬۠ۥۘۥۧۡ";
                    break;
                case -930564998:
                    String str2 = "ۚۦۖۘۥ۟۟ۥۦۜ۫ۖۤۨۖۘۖۦۦۚۡۨۘۗۥ۠ۨۜۜۘۖۖۨۘۧۗۨۤۢۜۚ۠ۚۙ۠ۦۘ۟ۡۘۚۛۗ";
                    while (true) {
                        switch (str2.hashCode() ^ (-695070674)) {
                            case -2146436465:
                                str = "ۦۨۛۡۧۥۦ۟ۡۘ۬ۜۨۜۡۖۙ۠ۦۧۖۘۥۨۦۘۘۧۦۘۗ۠ۡۘۖ۬ۢۤۘۗ۟ۖۗۛۦ۬ۛۖۨۘ۫ۚۗۨۡۗ۬ۨ";
                                continue;
                            case -1360159016:
                                str = "ۛ۟ۤۗۙۖۥۧۦۘۧۢ۫ۙۦ۠ۤ۬۟ۘۜۧۘۤۧۤۤ۬ۡ۫ۖۛ";
                                continue;
                            case 487840819:
                                str2 = "ۜ۠۠ۖۥۧۘۢۢۚ۠ۨۧۘ۟ۗۨۘ۬ۜۗ۟ۨۜۘ۠ۧۢ۠ۤۖۘۡۜ۟ۜۡۤۨۦۨۧۨۖۘۥۥۛۗۡۥۚۗ۬۟ۦۦ۟۠ۥ";
                                break;
                            case 1192438147:
                                String str3 = "ۛ۬۟ۛۡۙ۟ۦۨۘۤۖۜۢۘۨۘۨ۫ۙۛۦۜ۠ۛۛۦ۬ۨۘۢۖ۟۫ۡۘۧ۟ۢۤۤۥۘۙۙۥۡۥۥۗ۬";
                                while (true) {
                                    switch (str3.hashCode() ^ (-1389455469)) {
                                        case -1929946254:
                                            str2 = "۟۫ۡۘۡۨۧۛ۟ۥ۟ۛ۬ۗۥۗۧ۟ۙۦۨ۬ۨۦۨۦ۠ۦۧۖۘ";
                                            break;
                                        case -1110690905:
                                            str3 = "۠ۚۡۘۗۙۤۨۖۗۢۙۗۦۚ۬ۨ۟ۦ۠ۥۘۛ۟ۦۜۖ۬ۜۖۗۢۦۢۢۖۡۨۡۗۜۤۨۘۨۗۡۧۥۗ";
                                        case 459205827:
                                            str3 = action != 0 ? "ۙۧۖۙۜۧۘ۬۠ۖۘۙۗ۫ۜ۠ۨۘۜ۫ۛ۠ۛۘۘۚۚۢۖ۟ۤۨۛۘ" : "ۡۡۦۗۧۦۘۧۢۡۚۨۤۚۥۡۘۚۖۜۘۙۡۖۘۖۙۧۢۡۨۜۧۥ۠۬ۛ۬ۗ";
                                        case 1053634118:
                                            str2 = "ۘۚۜۘۥۢۙۧ۫۠ۜ۟ۖ۠ۨۘ۫۟ۢۡۡۢ۫۠ۜۘۨۘۥۥۥۘۜۧۢۧۖۥۥۨۜۘ۬ۘۨۘ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case -363720433:
                    layoutParams.x = (int) ((this.c + rawX) - this.a);
                    str = "ۥ۟ۖ۬ۜۡۘۦ۠ۖۘۥۥۢ۫ۙۘۨۖ۠ۤۡۧۘۡۡ۠ۘۧ۫ۙ۬ۨۗۚۥۗ۟ۗ";
                    break;
                case -289301606:
                    str = "ۜۥۘۘۨۧۡۡۤۨۘۘۚۡۘۧۘ۠ۦۡۜ۠ۘۗ۬ۥۙۛ۟ۚۜۦۗۥ۫۟۬ۨۥ۠۫۫ۨۘۡۘۛۥۦۘ۠ۜۘ";
                    action = motionEvent.getAction();
                    break;
                case -223903922:
                    str = "۫ۢۚۚۗۥۜۜۚۚ۫۫۬ۗۜۘۚ۬ۡۘۨۘۡۘۥۧۘۘۨۛۥۜۤۥۘۦۧۘۙۧۖۘۚۦۧۙۡۡۖ۠ۥۤۗ";
                    break;
                case -32366374:
                    return true;
                case 5758197:
                    str = "ۨۚۧۜۘۥۘۥ۟۠۬۫ۘۥۡۘۦۨۦۘۙۦۘۘۤ۬ۢۧۘۤۜۖۚۙۘۡ۬ۢ۬ۖۖۘۘۥۧۥ۠ۜ۬۟ۖۙ۬ۚ۟ۨ";
                    layoutParams = this.e;
                    break;
                case 223971695:
                    layoutParams.y = (int) ((this.d + rawY) - this.b);
                    str = "۠ۢۖ۟ۡۥۚ۬ۘۨۡۨۡۘۡۘۥۥۘۛۜ۠ۚ۫ۜۘۛۗۨۘۙۡۜۘۙۖ۫ۨۖۜۘ";
                    break;
                case 374412833:
                    return false;
                case 761735657:
                    str = "۬ۖ۟ۖۧۥۚۜۛۛۤۗ۟ۖۗۗۛۥۘ۟ۖۨۘۧۡۛۛۥۨۛ۟ۦۖۙۖۘۛۘۖۢۚۗۥۨۡۘ۠ۜۖۘۜۖ۟ۥۘۧۘ۫ۖۢ";
                    break;
                case 1211860684:
                    str = "۠ۤۡۤۛۜۘ۠ۜۘۙۜۖۘۨۗ۟ۙۨۛۧۙۨۘۘۤۡۘۤۘۤۤۙۘۘۖۥۘۤ۟۫ۜۤۗۤ۠ۘۨ۬ۤۛۜۧۘ";
                    break;
                case 1255057512:
                    rawY = motionEvent.getRawY();
                    str = "ۥ۬ۨۧۥۡۘۘۢ۟۬ۘۚ۠ۘۘۜۨ۬ۗۨۛۗۜۨ۟ۗۘۘۧۘۛۤۡ۬۠ۖۦۘ";
                    break;
                case 1431050207:
                    String str4 = "۬ۨۜۘۜۡ۠ۜ۬۬۫ۥۛۤ۟ۢۥۖۙۛۚ۟ۜۘ۠ۡۖۨۘ۠ۡۨ۫ۖۗۘۧۥۘ";
                    while (true) {
                        switch (str4.hashCode() ^ (-945837850)) {
                            case -662253121:
                                String str5 = "ۥۢۗ۠ۘۡ۠۟ۦۖۗ۫ۧ۬ۘۘۖۖۖۗۦۚۖ۟ۢۛۢۡۘۘۚ۟";
                                while (true) {
                                    switch (str5.hashCode() ^ 1952026897) {
                                        case -2131870012:
                                            str4 = "ۤۚۜۜۜۡۘۧۖۤۘ۫ۤۢۜۢۨ۠ۖۧۥۡۡۛۜ۟۫۫ۨ۠ۛ";
                                            break;
                                        case -1137480381:
                                            str5 = action != 2 ? "ۜۛۦۡۡۖۖ۟۬ۧۘۦۘ۠ۙۤۤۡ۟ۥۤۖ۬ۙۖۘۤۚ۬ۘۚۘۜۧۨۡۜۤۘۖ۠ۗۙ۫ۖۜۘ۬ۖۨۘۧۘۖ۬۠ۗ" : "ۘۖۙۜۥ۟۟۠ۡۘۙۧۧ۟ۛۨۘ۠ۢۛ۫ۘۨۜۦۧۘۡۧۡ۟ۚۜ";
                                        case -894377362:
                                            str5 = "۠۫ۨۘ۟ۘۚۦۛۢۖۛۨۘۤۢۖۖۧۜۘ۬۠ۡۘ۫۫ۙۖۖۜ۫ۜۥۘۢۙۖۧۤۥ۬ۙۢ۬ۨۗ۬ۡۘۖۚۗۗۥۥۘۦۥۡ";
                                        case -660736522:
                                            str4 = "ۛۤۦۘ۬ۙۛ۟ۖۥۤۤ۫ۜۗۡ۬ۨۤۜۥۡۘۡ۫ۧۦۤۜۜۜۜۨۚۜۙۘۘ";
                                            break;
                                    }
                                }
                                break;
                            case 945790361:
                                str = "ۘ۫ۙ۟ۜ۬۬ۗۛۧۛۚۧۜۥۘۜۥۘۛۙ۠۟۠ۘۘۤۙ۠ۘۥ۬ۦۤۘۨ۬۠ۙۚۦۧ۫ۥۘۚ۫ۚۡۥۖۘۚۦۧ۠۬";
                                continue;
                            case 1046943998:
                                str4 = "ۚۚۛۚۛۚۤۜ۫ۧۖۖۚۚ۬ۚۦۤۛ۠ۦۘۥ۠ۜۙ۬۟ۧۥۦۘۤۙۗ۬ۚۡۗۘ۬ۡ۠ۨ";
                                break;
                            case 1526250516:
                                str = "ۙ۟ۧۨۖ۠ۡۚ۟ۗۥۥۘۖۡۘۘۘۥۨۘۥۢۨۘۜ۟ۜۢ۫ۨۘ۟ۤۨۜۧ۟ۧۤۦۢۖۙۘۡۖ";
                                continue;
                        }
                    }
                    break;
                case 1489078202:
                    this.c = layoutParams.x;
                    str = "ۥۛۢۛۘۗۖۥۖ۠۟۬ۨۘۙ۠ۖۢ۠ۨۙۙۥۧۗۢۦ۫ۧۦۘۤۖۗۤۘ۠ۡۚۡۡ۫ۛ۟ۨۜۗۗۡ";
                    break;
                case 1539718028:
                    this.f.updateViewLayout(this.g, layoutParams);
                    str = "۠ۢۦۦ۫۟ۛۢ۠ۚۘۖۘۘۨۨۚۚۙۘ۠ۧۜۘۗۗۨۘ۟ۜۧۘۚۤۡۙۡۤۡۜ۟۬ۛۛۙ۠ۤۗۜۛۚ۠ۙۗ۫ۤ";
                    break;
                case 1745379074:
                    return true;
                case 1853468405:
                    String str6 = "ۥۘۨۘۖ۟ۡۘۛ۟ۘۛۡ۬ۡۖۘۖ۬۟ۧۗۖ۟۠ۖۨ۫ۚۜۨ۬۠۬ۘۗۗ۬ۥ۟ۦۘۦۤۜۘۗۖۥۘۜۢۗ";
                    while (true) {
                        switch (str6.hashCode() ^ (-1374736661)) {
                            case -1091901983:
                                str6 = "ۜۧۢۤ۟ۛ۬ۥ۟ۛ۟ۜۘۢۦۤۛۜۥ۬ۢۢۦۡۙۡۨۘۘۡۧۜ";
                                break;
                            case -143165129:
                                str = "۫۟ۤۘۡۜۘۤ۬ۡۘ۟۬ۨۘۘۘ۫ۨ۟ۥۖۜۨۗ۟ۨ۠ۥۘ۟ۥ۟ۤۜ۠ۤۜۘ۬ۡۢۚۛۛ";
                                continue;
                            case 1389874721:
                                str = "۫۠ۤۗۨۗ۟ۨ۟ۢۗۚۧۡۦۘۢۛ۬ۥۨ۠۟۫ۜۘۜۥۦۘۤۨۛ";
                                continue;
                            case 1699733901:
                                String str7 = "ۛۢۦۘۚۖ۟ۥۥۨ۠ۨ۬ۥۥ۟ۚۡۛۘۖۦۘۡۛۧ۟ۛ۫۟ۥۘۜۦۘ۬ۜۡۘۨۥ۟۠۬ۢ";
                                while (true) {
                                    switch (str7.hashCode() ^ 2093744357) {
                                        case -677532159:
                                            str7 = action != 1 ? "۟ۚۗۖ۫ۨۘۗۚ۬ۘۙۡۨۛۨۘ۟ۢۨۘۢۤۘ۫ۦۛۦۗ۬۠ۜۤ" : "ۜ۟ۗۚۜۥ۫ۛۧ۟ۚۙۧ۫ۤ۫ۖۡۘ۬ۢ۫ۗۙۚۡۥۡۙۘۨۘۘۦۗۦ۬ۢ";
                                        case -410271200:
                                            str7 = "ۘۙ۟ۙ۬ۢ۟ۗۗ۬ۗۗۛ۫ۧ۫۠ۗۚۢ۫ۢۙۜۘۤۤۦۘۢۚ۟";
                                        case 20168021:
                                            str6 = "۫ۛ۬ۚۜۜۘۦ۬ۜۘۦۦ۫ۥۢۛۤۦۘۗۜۡۚۨۡۘۜۨۥۘۤۛۛۗۙۧۤ۫ۚۥۗۧۗۨۗ";
                                            break;
                                        case 1869913997:
                                            str6 = "۫ۢۖ۬۟ۛۧۗۡۦۘۗۖۦ۠۬ۜۘ۬ۢۙ۫۠ۡۘۖ۠ۚ۬ۚۦۧ۫ۖ۫ۘۧ";
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
