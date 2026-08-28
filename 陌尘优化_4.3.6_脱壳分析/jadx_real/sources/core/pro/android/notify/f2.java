package core.pro.android.notify;

import android.app.Activity;
import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class f2 extends ViewOutlineProvider {
    public final Activity a;

    public f2(Activity activity) {
        this.a = activity;
    }

    @Override // android.view.ViewOutlineProvider
    public void getOutline(View view, Outline outline) {
        float fC = 0.0f;
        String str = "۠ۜۖۧۦ۠ۘۥۢۢۥۙۥۦۜ۫۬ۨۦ۬ۖۘۦۨۡ۫۬ۖۦۚۜۖۦۥۡۡۚۛ۫ۜ۫ۡۥۘ";
        while (true) {
            switch ((((str.hashCode() ^ 23) ^ 63) ^ 510) ^ 88113667) {
                case -1148415814:
                    str = "ۗ۫ۦ۠ۘۛۚ۟۠ۧۧۛۨۘۧۘۨۖۨۧ۠ۨ۬ۤۙ۬ۜ۟ۡۗۘ۠ۤۘۘ۟۫ۗ";
                    break;
                case -70626911:
                    return;
                case 618919470:
                    str = "ۗۦۧۘۨۥۜ۠ۗۤۡ۬ۤ۠ۧۖۘۥۧۥۘۖۗۡۘ۠ۧۦۡ۠ۖۖۡۜۘ۫ۗۜۘۤۦۥۘۖ۫ۛۜۛۡۘۥۦۨ۫ۛۡۘ";
                    break;
                case 996981103:
                    str = "ۗ۫ۥۡۧ۬ۦۗۦۘۙۡۦۚۢ۟ۚۘۥۚۥۨۥ۠ۖۘۚۨۨۖۜۗ";
                    break;
                case 1021533071:
                    fC = k2.c(this.a, 10);
                    str = "ۘۨۘ۬ۧ۬ۖۖۜۘ۬ۢۜ۠ۧۘ۟ۢۖۘۦۧۤۤۡۖ۠ۨ۠ۨۗۖۘۦۡۥ۬ۨۚۧۡۛۖۨۚۨۘۡۘۛ۬ۘ";
                    break;
                case 2125573170:
                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), fC);
                    str = "ۜۢۨ۠ۢۡۧ۟ۦ۟ۜۧ۫ۦۡۘۛۥۡۘۙۗۘۜۨۛ۠ۗ۟ۙۤۦۘ۠ۡۧۘۧۚۖۘۛۨ۫ۛ۫ۢۖۡۥۘ۟ۡ۟ۜۖۥۙۢۨ";
                    break;
            }
        }
    }
}
