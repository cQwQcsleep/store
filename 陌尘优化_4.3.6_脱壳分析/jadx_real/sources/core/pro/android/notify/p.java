package core.pro.android.notify;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Movie;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.SystemClock;
import android.view.View;
import gTBLD.dev.XSSTG.free.Utils;
import java.io.InputStream;

/* loaded from: /workspace/unpacked/classes2.dex */
public class p extends View {
    public final Movie a;
    public long b;

    public p(Context context, InputStream inputStream) {
        super(context);
        this.a = Movie.decodeStream(inputStream);
    }

    @Override // android.view.View
    public final void onDraw(Canvas canvas) {
        long jUptimeMillis = 0;
        long j = 0;
        Movie movie = null;
        int width = 0;
        int height = 0;
        int iWidth = 0;
        int iHeight = 0;
        float f = 0.0f;
        float f2 = 0.0f;
        float f3 = 0.0f;
        float f4 = 0.0f;
        float f5 = 0.0f;
        float fMax = 0.0f;
        float fDp = 0.0f;
        Path path = null;
        String str = "ۘۙۛۧۗ۟ۘ۟ۙۚۜۘۖۜۘۖ۫ۨۘۗۜ۬ۦۡۦ۠ۗۧۘۚۧۗۗۦۘۥ۟ۛۦۢۡۘۦۘۖۛۛ۟۫ۡۘ";
        while (true) {
            switch ((((str.hashCode() ^ 927) ^ 616) ^ 503) ^ (-1516602223)) {
                case -1830583532:
                    return;
                case -1474171568:
                    movie = this.a;
                    str = "ۗۥۢ۫ۢۥۜۜۧۧۖ۫ۨ۟ۢۖۤۧ۠ۗۘۡۜۧۥۚۤۧ۫ۛۥۜۤۛۨۙ۫۬ۘۨ";
                    break;
                case -1200562773:
                    f4 = height;
                    str = "۫ۙۥۘۧ۟ۖۘۗۦۙ۟۟۠۬ۦ۟۠۫ۗ۫ۢۗ۫۟ۧۦۖۡۖۙ۠۟ۤۥۘۘۨ";
                    break;
                case -1066280165:
                    canvas.scale(fMax, fMax);
                    str = "ۖۥۜ۫ۚۤۙۖ۟ۘۛ۠۬ۧۛ۫ۧۥۘ۠ۥۡۢۧۚۨۤۘۘۢۤۘۤۘۘۘۜۙۨ۬ۙ۬ۦ۠ۙ";
                    break;
                case -1025128147:
                    canvas.restore();
                    str = "۬ۗ۠ۖۙ۟۫ۖۥۘۚۢۨۘۥۤۨۘۥ۠ۖۥۨۘۘۧۛۢ۟ۜۡۖ۠ۗۡۜۡۘۨ۫۫";
                    break;
                case -1002834120:
                    super.onDraw(canvas);
                    str = "ۢ۟ۖۤۘۖۢۦۦۘ۫ۨۨۘۨۡۛ۬ۢ۟ۨۖ۟ۚۤ۠ۘۥۦۘ۫ۚۡۘۖ۟ۦۛۙۚۜۡۙۖۢۦ";
                    break;
                case -867817929:
                    canvas.save();
                    str = "ۙ۬۟ۧۢۘۘۖۜۦۘۘۗۖۘۡۗۛۥ۬ۛۗۥۨۘ۟ۦۤۛۥ۬ۙۜۚۗۗۗۤۖۘۨۦۗ۟ۢ۫ۘۤۜۙۖ۬ۗۦ۬ۤ۟ۖۘ";
                    break;
                case -817061241:
                    fMax = Math.max(f3, f4 / f5);
                    str = "ۥۦۖ۟ۡۨۥۜۨۘۦ۠ۗۗۖۖۘۘۧۦۘۙۜ۟ۦۜۦۘۧۘۛۤۙ۫ۢۥۘۚۡۤۜۖۛۤۦۘ";
                    break;
                case -815346661:
                    canvas.clipPath(path);
                    str = "۟ۧۛۚ۬۬ۦ۫ۜۘۧۧۤۥ۟ۧۘۡۡۦۖۧۘۖۘۡۘۨ۠ۙۤۖۜ";
                    break;
                case -682448125:
                    f = width;
                    str = "ۡۢۙۖۡۡۘۘ۫ۜۛ۫ۙۛۘۘۘۡۢۜۘۙۛۚۨۗۙ۬ۤۡۦ۠ۥۘ۠۫ۡۘۢۘۢ";
                    break;
                case -625423777:
                    path.addRoundRect(new RectF(0.0f, 0.0f, f, f4), fDp, fDp, Path.Direction.CW);
                    str = "ۡۦۡۘۛۡۖۘۛ۟ۥۘ۬ۧۨۘۨۜۡۘۡ۫ۜۘۡۙۘۘۧۨۡۡ۫ۢۙۥۨۡۚۡۘ۠ۦۧۘ۠۬ۤۥۥۙۥۢۘۘ۟ۧ۬";
                    break;
                case -613556275:
                    height = getHeight();
                    str = "۠ۚ۠ۗۜۧۘۙ۟ۦۘۘۖۘۘۤۘ۠ۚۛۗۜۡۛ۠ۘۢۦۘۡ۬ۙۙۙۚۖۢۘۘ۫۟ۥۘ۟ۧ۠ۖۚۢۗ۟۬ۗ۠ۛۨۜۛ";
                    break;
                case -572015859:
                    str = "ۖۖۥۘۙۖۘۤۘۛۚ۫ۡۘ۬ۨۦۤۢۜۘۤۤۙۢۥۧۘ۠ۖۦۖۛۥۘ";
                    break;
                case -561286480:
                    path = new Path();
                    str = "ۧۨۥۘۙ۠ۙۤۙۜۘ۠۟۫ۤۖۘۘۥۙۦۘۨۦۦۘۡۦۚ۬۬ۨ۬ۦۡۘ۠ۜ۠ۨ۠ۙۡ۠ۥۘۢ۟ۛ۫ۗۘۘ۠";
                    break;
                case -174313199:
                    movie.setTime((int) ((jUptimeMillis - j) % movie.duration()));
                    str = "ۡۥ۠ۢ۬ۚۢۧۘ۫ۚۜ۬۠ۘۘ۠ۘۤۡۢۘ۬ۥ۬۟۬ۨۘۚۛۙۢۦۤ۟۫ۚۢۗۧ۠ۜۦۜ۠ۢۨۛ۫ۜ۬ۡۘۡۛ۠";
                    break;
                case -90855097:
                    iHeight = movie.height();
                    str = "ۥۘۛ۟ۘۤۤۘۦۘۥ۬۠ۢۖۤۙۧۥۢۚۗۜ۬ۨۥۙۡۘۨ۟ۤ";
                    break;
                case -51646277:
                    width = getWidth();
                    str = "ۢۤۖۜۧۨۙ۟ۡۘۡۥۜۘ۠۠ۡۡ۫ۧۛۤۨ۫۠ۦۘۧ۫۠۬۫۫ۘۘۜ۬ۜۘ۟۟ۜۘۧ۠ۜۘ۟ۦۦۧۦۘۘۡۡۖۨۤۡ";
                    break;
                case 292413641:
                    str = "ۦ۟ۡ۠ۥ۟ۛۖۥۢۦۧۘۖۚۤۥۧۧۡۖۨۗ۟ۥۘۚۤۧۘ۫۟ۛۙ۟ۛ۬ۦۘۚۛۦ۬ۛۡۘۛۙۗ۟ۛۜۦۤ۫ۧۜ";
                    break;
                case 797590323:
                    canvas.translate(((f / fMax) - f2) / 2.0f, ((f4 / fMax) - f5) / 2.0f);
                    str = "ۙۛۘۘۦ۠ۤ۫ۨۥۘۙ۫ۖۘ۬ۤۜۘۥۦۖۘۡۢۥۡۥ۠ۗۙ۫ۦۡۛۦۙ۬۫ۥ۫ۡۚۘۘۛۜۜۘ۠۫۠ۦۡ۠";
                    break;
                case 858302507:
                    f2 = iWidth;
                    str = "ۥۘۨۘ۬ۛۥۙۖۗۘۥۚۧۦ۬ۤۙۨۘۖۚۙۖۙۚۛۤۘۧۡۚ۟ۘۘۖۥۦۘۨۘۙۘۗ۠ۛۘۖۘۥ۟ۖۘ۫ۦۘۘ۟ۨۘۘ";
                    break;
                case 1017317705:
                    f3 = f / f2;
                    str = "۟ۨۜۦۡۢۥۘۘۘ۟ۖۙۚۗۨۧۥۡۚۤۤ۟۠۟ۦ۬ۙۤۡ۬ۘۘۨ۟ۨۘ";
                    break;
                case 1045062979:
                    iWidth = movie.width();
                    str = "ۨۧۖۦۗۨۘۧۧۚۛۗۖۘۗۦۧۤ۫ۥۘۤۙ۟ۥۧۢۡۨۙ۬ۦۦۘ";
                    break;
                case 1221070601:
                    fDp = Utils.dp(getContext(), 10.0f);
                    str = "ۦۢۚۙۛۡۨۤۖۘۧ۬ۙۧۗۚ۟ۚۖۘۚ۟ۦۛۥۡۛۡۜۘۡۜۜۘ۠ۢۡۘۜۗۤۛۨۦۘ۫ۜۖۘ";
                    break;
                case 1373102320:
                    j = this.b;
                    str = "ۢ۟ۘۘۜۘۤ۬ۖۘ۬ۚۨۨۥۧۥ۬ۨۘۦۤۙ۠ۛۘۘۢۦۥۜۨ";
                    break;
                case 1449140800:
                    invalidate();
                    str = "ۤۨۖۖۥ۠ۘۜۖۘۨ۫ۥۘۥ۠ۨۘۗۡۦۨۤۨۘۘۧۜۧۘۙۙۛۚۘۡۘۤۡۘۢۚۜۥۘۨۘۧۜۧۜ۬ۧ۫ۖۨۘۙۘۤ";
                    break;
                case 1512928829:
                    this.b = jUptimeMillis;
                    str = "ۤۥ۫ۖۗۤۜۖۖۘۚۦۥۗ۫ۖۘۗ۬۬ۖۙۡۘۛۜۦۥۗۦۘۤۙۧ۟۠ۖ۠ۨۧۙۦۜ۠ۜ";
                    break;
                case 1851135585:
                    movie.draw(canvas, 0.0f, 0.0f);
                    str = "۠ۥۙ۠ۙۖۘۥۧۧۨۨۦۥۨۦۘۧۘۖۘ۬ۦۜۚ۠ۥۘۚۤۥۘ۫ۨۙۚۤۖۘ۠۬ۥۗ۬ۜۛۦ۟ۙۚ۬۫۫ۙۡۥۘۤ";
                    break;
                case 1880031150:
                    String str2 = "ۛۦۥۘۨۙۢ۫۫ۥۡۖۢ۠ۡ۟ۧ۟ۨۧ۟ۚ۟ۙۨۥ۠ۡۗۧۦۘۥ۬ۦ۟ۗ۟ۥۧ۟۬ۧۥۜۛۗۗۛۧۧۖۡۙۖ";
                    while (true) {
                        switch (str2.hashCode() ^ 156324728) {
                            case -2032107929:
                                str = "ۤۥ۫ۖۗۤۜۖۖۘۚۦۥۗ۫ۖۘۗ۬۬ۖۙۡۘۛۜۦۥۗۦۘۤۙۧ۟۠ۖ۠ۨۧۙۦۜ۠ۜ";
                                continue;
                            case 622250901:
                                str = "۠۟۟ۦۚۚۥۘ۫ۢۗ۠ۦۦۖ۟ۦۧۘ۟ۧ۫ۥۚۜۘۧۨۦۚۤۙۛۥ۠ۨۨۙ";
                                continue;
                            case 1114242872:
                                String str3 = "۬۫ۘۘۚۧۛۢۗۛ۟۫ۖۡۡ۠ۛۚ۟ۤۤۡۖ۟ۢۥۜۗۜۜۘۗۘۢ۠ۗۛ";
                                while (true) {
                                    switch (str3.hashCode() ^ (-1118812274)) {
                                        case -1367591123:
                                            str3 = "ۗۘ۫ۚۖۜۘۤۤۨۢۙۡۘۤۦ۠ۤۤ۬۠ۨۨۖ۠ۛۧۜۦۡۛ۬ۗۧۢۡۘۘ";
                                        case -782718843:
                                            str2 = "ۛۚۙۘۨۧۘۘۦ۬ۙۢۜۘۙۙۗۢۘۘۢۦۛۚۨۗۡۜۧۤۦ۫ۢ۟۠ۦۘۧۧۧ۠ۤۡۘۘۤۗۢۜۥۜۙۡۢۦۘ";
                                            break;
                                        case -655114040:
                                            str3 = this.b == 0 ? "۠ۙۖ۠ۤۤۢ۫ۖۘۜۗ۠ۦۡۛۖ۫ۨۗۛۚۚۤ۬ۖۘۜۖۧۘ" : "ۖ۬ۘۤۨ۫ۨ۫ۧۚۥۛۧۢ۫ۡۢۚ۫ۘۢ۬ۡۘ۟ۙۖۘۦۢۖۘۦۥۤۚۢۙۗۦۗ۬ۧۘۡۘۖ۬ۛ";
                                        case 655268685:
                                            str2 = "ۛۛۨۨ۠ۗۥۜۦۜۙ۬ۖ۠۫ۗۜۖۧۙۦۘ۬ۘ۟ۙۙۥۘۦۜۧۤۤۥۤۜۧۨۧۜۤۥ";
                                            break;
                                    }
                                }
                                break;
                            case 1806739622:
                                str2 = "ۖۘ۬ۡۡۙۢۙۜۡۥۦۘۚۛ۬۟ۨۡ۫ۡۘۨۘۗۗۜ۬ۢۨ۟ۜۤۢۦ۬ۡۜۦ۠ۜۚۨۤۖۘۦ۟ۥۘ";
                                break;
                        }
                    }
                    break;
                case 1894313395:
                    jUptimeMillis = SystemClock.uptimeMillis();
                    str = "ۙۡۦۘ۬۠ۜ۠ۙۘۥۦۖۘۦۥۚ۬۟ۥۘۘۙ۫ۖۢ۫ۜۡ۬۫۬ۡۙ۠ۡ";
                    break;
                case 1912742766:
                    f5 = iHeight;
                    str = "۠۟ۘۘ۟۫ۖ۠ۦۤ۠ۦۘۗۧۘۧۡۖۖۗۦۘۦ۠۬ۡۜۚۜۤۖۦۢۗۙ۟ۥۛ۫۫ۦ۠";
                    break;
            }
        }
    }
}
