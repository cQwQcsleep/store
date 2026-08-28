package core.pro.android.notify;

import android.widget.TextView;
import gTBLD.dev.XSSTG.free.Utils;
import java.util.Set;

/* loaded from: /workspace/unpacked/classes2.dex */
public final /* synthetic */ class t1 implements Runnable {
    public final int a;
    public final TextView b;

    public /* synthetic */ t1(TextView textView, int i) {
        this.a = i;
        this.b = textView;
    }

    @Override // java.lang.Runnable
    public final void run() {
        TextView textView = null;
        String str = "ۥۤۛۛۨۜۡۥۡۘۢۙ۫ۤۖۨۘۤ۬۫ۡۚ۫ۦۜۨۘۧۡۧۘۨ۟ۦۘۘۗۨ۠ۦۜ";
        while (true) {
            switch ((((str.hashCode() ^ 548) ^ 689) ^ 540) ^ (-1344608998)) {
                case -1664517309:
                    Utils.p.addView(textView);
                    str = "ۘۛۙۨۖۨۘۨۡ۠۫ۗۘۗۘۢۗۦ۟ۢ۟ۧ۟ۨۨۤۙۘۘۚ۠۠ۛ۠۟ۡ۠ۧۡ۟۬ۛ۠ۨۘۚۚۡ۠۬ۧۚۛۦۗۖ۟";
                    break;
                case -72146611:
                    str = "ۘۗۦۤۨۘۡۗۙۧۖۥۜۛ۬۬ۛ۠ۖۧۦۘۖۥۥۦۨۗ۬ۥۘۘۘۖۖۗۘۘ";
                    break;
                case 110587483:
                    switch (this.a) {
                        case 0:
                            str = "ۥۖۧۘ۟ۥ۫ۗۡۡۗۡۧۚۧ۫ۖۡ۠ۡۤۛۛۗۥۘۨۥۨۘۙۖۙۘۚۚۚۤۖ";
                            break;
                        default:
                            str = "ۢۢۗۜۚۤۘۘۛۦۖۘۦۦۦۛۖۧۛ۠ۨۛۥۡ۬ۛۥ۬ۡۘۤۦ۬ۧۗۦۤۗۤۛۥۧۖۢۤۜۧۘ";
                            break;
                    }
                case 403879503:
                    Set<String> set = k2.closedPopupIds;
                    str = "ۙۚۗۛۥۨ۫۫ۜۘۜۙۘۘۥ۠ۜۘ۬ۢۖۡۤۡۜ۟ۥۨۤۨۘۙۨۨۢۜۡۘۤۖۚ۟۠ۘۡۡۖ";
                    break;
                case 1119380614:
                    textView.performClick();
                    str = "ۦۡۦۘۖۢۦۥۗۖۖۙ۫ۘۗۗۤۥۘۘۦۢۦۘۢۖۚۤۦۦۙۢۦۘۥۜۘۡۡۡ";
                    break;
                case 1398549489:
                case 1991199196:
                    return;
                case 1408783853:
                    textView = this.b;
                    str = "ۗۖۙۥۨۘۘۦۙ۫ۜۜۚ۫۠ۤۡ۬ۗۦۘۥۘۨۚۘۤۛۨ۫۟ۙ۫ۗ۬ۙۚۘۘ";
                    break;
            }
        }
    }
}
