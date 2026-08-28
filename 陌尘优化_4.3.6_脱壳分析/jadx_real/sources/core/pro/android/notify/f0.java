package core.pro.android.notify;

import android.graphics.Outline;
import android.view.View;
import android.view.ViewOutlineProvider;
import gTBLD.dev.XSSTG.free.MainActivity;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class f0 extends ViewOutlineProvider {
    public final MainActivity a;

    public f0(MainActivity mainActivity) {
        this.a = mainActivity;
    }

    @Override // android.view.ViewOutlineProvider
    public void getOutline(View view, Outline outline) {
        String str = "۠ۘۖۘۙۡۡۘۧۥۡۘۜۥۦۢۛۛۡۚۨۘۘ۟ۛۚ۬ۡۘۤۛۥۥۜۘۦ۟۫ۖۜۦ۫۫ۗۛۖۧۘ";
        while (true) {
            switch ((((str.hashCode() ^ 536) ^ 652) ^ 420) ^ 1508508754) {
                case -1126025601:
                    return;
                case -1018701129:
                    str = "ۗ۠ۗۗۚ۟۬ۛ۫ۡۨۦۘۧۖۖۚۗ۬ۘۤۤۛۧۘۖ۬۠ۗۗۡۘۚۨۦۤ۬ۧ۠ۚۤۥۘ";
                    break;
                case -162506740:
                    str = "ۗۚۙۗ۠ۨۘۥۗۥۧ۠۟ۗۜۡۘۖ۠ۙۘۧۥۘۤۙ۠ۧۡ۫ۡۧۘۡۗۧۛۚۘۘ۬ۡۘۢۗۖ";
                    break;
                case 418384090:
                    outline.setRoundRect(0, 0, view.getWidth(), view.getHeight(), MainActivity.access$000(this.a, 4));
                    str = "ۥۙۨۘۜ۠ۛۦۥۘۛۘۖۘۚۙۡۦۧۘ۫ۖۦۧ۫ۦۘۡۘۦ۫ۗۚۥۖۥ۟ۢۜۜۘۨۨۢۨ";
                    break;
                case 1442493731:
                    str = "ۤۜۙۖۜۦۦۚۚۢۜۦۘ۠ۤۙ۫۟ۦ۟ۚۧۘۗۢۚۖۛۜۖ۠";
                    break;
            }
        }
    }
}
