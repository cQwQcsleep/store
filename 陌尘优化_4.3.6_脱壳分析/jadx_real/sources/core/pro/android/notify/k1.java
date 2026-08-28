package core.pro.android.notify;

import android.app.Activity;
import android.app.Dialog;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;
import com.iqoocg.nm.fcRuQsQrcxOAzxwEalcM;
import gTBLD.dev.XSSTG.free.Utils;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import org.json.JSONException;
import org.json.JSONObject;

/* loaded from: /workspace/unpacked/classes2.dex */
public final /* synthetic */ class k1 implements Runnable {
    public final int a;
    public final Activity b;
    public final Object c;
    public final Object d;
    public final Serializable e;
    public final boolean f;
    public final Dialog g;
    public final Object h;

    public /* synthetic */ k1(Activity activity, Object obj, Object obj2, Object obj3, Serializable serializable, boolean z, Dialog dialog, int i) {
        this.a = i;
        this.b = activity;
        this.h = obj;
        this.c = obj2;
        this.d = obj3;
        this.e = serializable;
        this.f = z;
        this.g = dialog;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public /* synthetic */ k1(String str, Activity activity, FrameLayout frameLayout, Handler handler, Runnable[] runnableArr, boolean z, Dialog dialog) {
        this.a = 0;
        this.h = str;
        this.b = activity;
        this.c = frameLayout;
        this.d = handler;
        this.e = runnableArr;
        this.f = z;
        this.g = dialog;
    }

    /* JADX WARN: Code restructure failed: missing block: B:10:0x0053, code lost:
    
        r2 = core.pro.android.notify.l2.decrypt("Ckn1vWDKBxxkM8P2\n", "7NVfWv9v4JU=\n");
     */
    /* JADX WARN: Code restructure failed: missing block: B:17:0x0114, code lost:
    
        throw new java.lang.IllegalStateException(core.pro.android.notify.l2.decrypt("QrZJa69zQmA+/GUT+GQaOSe3GzGcFBJn\n", "qhn+jR7xqt8=\n"));
     */
    /* JADX WARN: Type inference failed for: r0v13, types: [java.io.Serializable, java.lang.Runnable[]] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void run() throws PackageManager.NameNotFoundException, IOException {
        String strDecrypt;
        final String strReplaceAll;
        boolean z = this.f;
        Dialog dialog = this.g;
        Activity activity = this.b;
        Object obj = this.c;
        Object obj2 = this.h;
        Object obj3 = this.e;
        Object obj4 = this.d;
        switch (this.a) {
            case 0:
                String str = (String) obj2;
                Set<String> set = k2.closedPopupIds;
                try {
                    boolean zEndsWith = str.toLowerCase().endsWith(l2.decrypt("qMLkvA==\n", "hqWN2vGKZ7M=\n"));
                    FrameLayout frameLayout = (FrameLayout) obj;
                    Handler handler = (Handler) obj4;
                    ?? r0 = (Runnable[]) obj3;
                    Dialog dialog2 = this.g;
                    Activity activity2 = this.b;
                    boolean z2 = this.f;
                    String str2 = "ۚۢۥۦۜۧۘۦۙۜۚۘۗۛۧۡۦ۟۬ۡۘۚ۫ۜۡۢۜۧۙۧۥ۟ۗۘۦۨۛۖۚۧۘۦۘۙۚۥۘۚۚۜ";
                    while (true) {
                        switch (str2.hashCode() ^ 1860647983) {
                            case -1923173576:
                                str2 = "ۖۥۘۘۚۗۜۘۙ۟ۤۗۚۡۛۢ۫۠ۘۦۘۥ۫۟ۢۖ۠ۙۢۥۨۘۥۛۘ۫ۗۤۚۢۤۛۖۥ۠ۨۗۤ۬۟ۦۡۢۗۙ";
                                break;
                            case -992472601:
                                InputStream inputStreamOpenStream = new URL(str).openStream();
                                Bitmap bitmapDecodeStream = BitmapFactory.decodeStream(inputStreamOpenStream);
                                inputStreamOpenStream.close();
                                new Handler(Looper.getMainLooper()).post(new k1(activity2, bitmapDecodeStream, frameLayout, handler, r0, z2, dialog2, 2));
                                return;
                            case -992189147:
                                InputStream inputStreamOpenStream2 = new URL(str).openStream();
                                ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                                byte[] bArr = new byte[1024];
                                while (true) {
                                    int i = inputStreamOpenStream2.read(bArr);
                                    String str3 = "۟ۨۙۛۛۚۖۗۧۥ۟۫ۤۜۜۥۧۧۡۘۧۘۡۡۜۧۜۦۜۘ۬ۡ۫ۡۥۘۦۧۥۘۘۘۖۡۘۧۘۦ۠ۧ";
                                    while (true) {
                                        switch (str3.hashCode() ^ 899632221) {
                                            case -1878492403:
                                                String str4 = "۠ۤۥۘ۬ۤۧ۬۠ۥ۬ۧۧۦۙ۬۠ۗ۬ۡۥۧۥۥۦۘۥ۟ۘۘۖ";
                                                while (true) {
                                                    switch (str4.hashCode() ^ 1355268276) {
                                                        case -288707526:
                                                            str4 = "۬ۙۖ۟ۚۨۘ۬ۚ۬ۢۤۧ۫ۜۘ۠ۦۦ۫ۘۜۘۜۚۡۙ۬ۘ۫ۘۖۘ۟۬ۥۖۘ۫";
                                                            break;
                                                        case 225457439:
                                                            str3 = "ۤ۬ۘۚۥۘۘۤۚۖۥۘ۬۫ۧ۟ۖۥۘۘۧ۠ۦۗۥۛۤۨ۫ۢۜۤۚۛۦۘۢۚۦۘ";
                                                            break;
                                                        case 1019474059:
                                                            str3 = "ۢۤۚۘۘۜۘ۟۟ۥ۬ۦۘۛۖۢۛۢۥۦۛۤ۫ۤۚۧۜۧۘۖۛۘۖۜ۬ۢ۬ۧۧۚۧۖۖ";
                                                            break;
                                                        case 1239056162:
                                                            if (i == -1) {
                                                                str4 = "ۙۢۨۖ۠ۡۘۗۥۛۥۛۚ۫ۗۗۜۦۢ۟ۛ۫۟ۥۧۘۡۘۘۥ۟۬ۖ۟ۧۜ۟ۙ";
                                                                break;
                                                            } else {
                                                                str4 = "ۖۖۘۘ۫۟۟ۦۡ۫ۗ۠ۜۘۥۧۤۤۛ۫ۧۚ۬۠ۨۨۘۙۚۢۘۘۖۢ۠ۦۘ۠ۥۘۡۧۜۖۤۥۘۡۥۚ۠۬ۦ۟۬ۡ۟ۦۧ";
                                                                break;
                                                            }
                                                    }
                                                }
                                                break;
                                            case -1439349861:
                                                str3 = "ۤۤۛۜۗۧۚۦۧ۟ۧۜۘۦۘۗۦۧۥۦۚۧۨۛۛۦۗ۫ۘ۟۠ۢۗۦۘۜۖۛۗۤ۫ۘ۠ۗۗۦۨ۫۫ۦۘۦۚۛۥۥۡۘ";
                                            case 595255488:
                                                break;
                                            case 1990892195:
                                                inputStreamOpenStream2.close();
                                                new Handler(Looper.getMainLooper()).post(new k1(activity2, new ByteArrayInputStream(byteArrayOutputStream.toByteArray()), frameLayout, handler, r0, z2, dialog2, 1));
                                                return;
                                        }
                                    }
                                    byteArrayOutputStream.write(bArr, 0, i);
                                }
                                break;
                            case 1838672848:
                                String str5 = "ۛۙۡۘۘ۫ۜۘ۬ۤۜۙۚۘ۬ۥۘ۫ۦۖۘۨۜۡۙ۬ۚۦۡ۫ۘۛۧ";
                                while (true) {
                                    switch (str5.hashCode() ^ 1859535547) {
                                        case -1831423456:
                                            str2 = "ۤۤۨۜۦۡۘۦۡ۬ۛۖۧۤۚۜ۬۟ۖ۫ۨۥۡۚۢ۬ۘۚۗۦۘۘۡۤۘۘۧۦۥ";
                                            break;
                                        case -649376824:
                                            str5 = zEndsWith ? "ۖ۫۟ۖ۠ۜۘۢ۫ۢۜ۬ۘۘۜۥۨۘۖۖۘۦۘۜۨۜ۫ۨ۬ۘۨۘۜۗ۬ۖۜ۬ۥۘۘۛۡۧ۠۬ۦۤۢۙۜۜۘۗۖۨۘۤ۬ۦۘ" : "ۡۖۡ۬ۤۦۤۢۨ۬ۙۜۘۚۚ۬ۜۛۖ۟ۤۡۘۜۤۛۘ۟ۙۧ۠۫ۤۗۦۦۙۨۘ";
                                        case -500116795:
                                            str5 = "۠ۘۥ۬ۥ۬ۘۥۧۘۜۨۦۦۥۘۖ۬ۗۖۨۦۘۗ۫ۗ۬ۗ۟ۧۡۡ۬ۡۥۘۢۘۤ";
                                        case 476780809:
                                            str2 = "ۢۡۧۘۛۗۨ۠ۤۡۘ۬۬ۖۨ۫ۜۘۙۗ۟۠ۗۙۧۢۜۜۧ۟۬ۨۖۢۖۡۚۛۜۥۚۛۚۖۧۢۛۘۡۤۨۘ";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    return;
                }
                break;
            case 1:
                Set<String> set2 = k2.closedPopupIds;
                p pVar = new p(activity, (ByteArrayInputStream) obj2);
                pVar.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                ((FrameLayout) obj).addView(pVar, 0);
                ((Handler) obj4).postDelayed(((Runnable[]) obj3)[0], 1000L);
                String str6 = "ۖۡۥۘ۬۫ۥۘۙۖۦۘۚ۠ۖۘ۟ۨۦۘۛۜۥۘۢۤۨۘۢۚۤ۫ۘۦۘۖۦۥۢۡۥۘ۟ۚۗ۫ۜۧۘۢ۬ۥۘۚۨۜۖۡۘۘ";
                while (true) {
                    switch (str6.hashCode() ^ (-1708771367)) {
                        case -1130798373:
                            String str7 = "ۙۧۡۘۨۡۦۥۜۤ۠ۧۛۛۥۦۥۡۙۙۥۨۗۜۧۘۜۢۧۚۥۖ";
                            while (true) {
                                switch (str7.hashCode() ^ (-2061367891)) {
                                    case -1734296318:
                                        str7 = "ۥۘ۫ۙۛۧ۬ۘۗۦۗۘۘ۫ۚۥۘۙۚۡۘۢۦۚۜۖۜۦۘۨۘۦ۫ۨۘ";
                                        break;
                                    case 118626305:
                                        str6 = "ۗۛ۬ۙۖۜۘ۟۟ۚۤۦۜۗۗۨۘۧ۬ۗۨ۠۬ۨۙۧ۠ۚۚۖۙ۟ۛۢۗ۬ۖۘۙۜ۠ۘۥ۟";
                                        continue;
                                    case 542282529:
                                        if (activity == null) {
                                            str7 = "ۥۜۡۘ۠ۢۤۙۡ۬۠ۤۛ۬ۖۗۛ۟ۜۥۨۘۧ۬ۢ۫ۢۧۚۖۜ۫ۢۦۤ۟ۜۘۧۙۖۙۗۖۘ";
                                            break;
                                        } else {
                                            str7 = "ۧ۫ۖۘۛ۠ۥۧۤۙۡۢۖۖۙۤ۟۬۠ۢۢۦۘۛ۬ۨۙۖۜۖ۬ۤ";
                                            break;
                                        }
                                    case 1054610661:
                                        str6 = "ۚۗۥۘۢۘ۟ۛۤۖۤۚۜۚۧۡۤۨۢۨۡۘ۠۟ۡۛۨۡۘۖ۫ۚۡ۬ۜۘۢۧۖۘۦۨ۟ۖ۟۟";
                                        continue;
                                }
                            }
                            break;
                        case 343384427:
                            str6 = "ۗ۟۫ۜۚۧۘ۟ۖۘۤۧۘۘۜۡۖۘۡ۬ۦۘۜ۟ۨۥۘۙۚۥۡۡۖ۟";
                            break;
                        case 812555570:
                            String str8 = "ۦۢۨ۟ۖۨۘۗۦ۠۫ۘ۠ۡ۠ۖۧۗۥۘ۠۠ۛۚۦۦۘ۫ۡۤۚۚۗۗۜۧۡۧۚ۠۠ۨۜ۠۬ۖ۠ۚۤۤ۟";
                            while (true) {
                                switch (str8.hashCode() ^ (-1580603381)) {
                                    case -1356088331:
                                        String str9 = "۬ۚۗۥۚۧۢۗۧۖۡۜۘۙۤۦۛۛۧۡۦ۠ۙ۫۬ۘ۠ۦۘ۟ۜ۫ۖۙۘۘ۠۬۟ۡۦۤۧۚۢ";
                                        while (true) {
                                            switch (str9.hashCode() ^ 801540849) {
                                                case -980286699:
                                                    str9 = "۠ۤۨۘ۫ۦۗۜۢۢۗ۠ۡۜۛۙۘۦۚۙۥۧۧۨۦۘۨۥۢۙۗۦۙۙۛ۠ۤۚۧۢۢ۫۠ۘۘۦ۫ۡۘۖۗۘ";
                                                case 916372004:
                                                    str8 = "۫ۡۙۧۧۗۨ۫ۚ۠۬ۘۘۨۧ۟ۛ۫۫ۥ۬۫۠ۖ۫ۧۖۡۘۥ۬۠ۚ۬ۜۤ۬ۥۖۜ۬ۧۡۘۦۖۤۥۘۛ";
                                                    break;
                                                case 1229215636:
                                                    str8 = "۟ۨۛۖۡۡۗۛۘۢۗۘۘ۟ۘۡۘ۟ۡۥۘۘ۠ۜۖ۬ۖ۠ۦۘ۠ۚ۬ۙۛۘ۟ۡۖۦۚۚۜۛ";
                                                    break;
                                                case 1835242678:
                                                    str9 = !activity.isFinishing() ? "ۨۜۖۘۚۢۨ۫ۖۗۗۦۧۨۜۖۘ۫۠ۙۖ۬ۘۘ۟ۦۤۢۘۥۘۜۖۖ" : "ۤ۬ۤ۠۟ۛۨۗۚۧۡۧۙۚۚۛ۟ۦۨ۫ۜ۠ۡۖۗ۠ۖۘۥۘ";
                                            }
                                        }
                                        break;
                                    case -817233732:
                                        str8 = "ۧۢۨۘۘۥۧۖۚ۬ۛۛۡۘ۠۟ۦۗۗ۬ۚۤۨۘ۫۠ۥۘۙۚۢۨۥۜۘ";
                                        break;
                                    case -383213318:
                                        return;
                                    case 1053811144:
                                        String str10 = "۟ۜۗۦ۟ۙۧۡ۠۫ۚۖۘۤ۠ۜۘۦۛۨۘۤۗۥۡۚۘۘ۬ۜۥۘۘۢۨ";
                                        while (true) {
                                            switch (str10.hashCode() ^ 902643279) {
                                                case -1533199463:
                                                    return;
                                                case -1158510031:
                                                    String str11 = "ۤۜ۬ۦۨۥۘۙۗ۫ۛۗۗۦۡۦ۟۟ۦۘۥۜۘۘۘ۬ۜۘۙۤۥۡ۟ۖۘ۟ۡ۟ۖ۬ۦ";
                                                    while (true) {
                                                        switch (str11.hashCode() ^ 1107835905) {
                                                            case -772980281:
                                                                str11 = "ۛۤۖ۟ۙۙۘۖۙۡۛۖۡۨۥۚ۬ۥۢۤۦۘۗۙۨۘۦۛۨۘ۟ۤۜۘۥ۫ۙۙۗۡۖۛۗۛ۬ۡۘ";
                                                                break;
                                                            case 557361463:
                                                                str10 = "ۤۛۡۦۘۙۘ۠ۢۘۛۘ۬ۗ۬ۤۢۘ۫۠ۘۘ۟۫۫۬ۡ۠ۢۡۢۚ۬۫ۙۙۨۘۘۘۘۘۙۖ۟ۜ۠ۗ۬ۙۡ";
                                                                continue;
                                                            case 1901747905:
                                                                if (!activity.isDestroyed()) {
                                                                    str11 = "ۚ۬ۜ۠۫ۨۨۜۘۤ۟ۖۢۖۥۘۜۧۙۢۦۡۤۢۤۨۧۨۡۢۙۦۘۜۜۦۥ";
                                                                    break;
                                                                } else {
                                                                    str11 = "ۜ۟۬ۡۧۗۨۗۥۘۧۥۘۜۥۙۙۤۡۤۦۖۛۜ۟ۚۚۛۡۡۘ";
                                                                    break;
                                                                }
                                                            case 1930681500:
                                                                str10 = "۫ۚۥۧۖۢ۫ۖۗۤۖۙۛۥۜۘۡ۬ۤۨۦۥۘۛۗۛ۠ۛۡۘۦۢ۟ۙ۟ۗۨۗۧ۫ۛ۠ۖ۠۫";
                                                                continue;
                                                        }
                                                    }
                                                    break;
                                                case -769807516:
                                                    str10 = "ۘۧۘۘۙۡۥۥۨۧۤۛۤۨۙ۬۫۟ۥۦۜۧۘۜۛۙۤۚۗۚۨۡۧۦۗۛۡ۬ۢۘۧۜۤ۫";
                                                    break;
                                                case 1757137591:
                                                    activity.runOnUiThread(new e1(activity, z, dialog, 2));
                                                    return;
                                            }
                                        }
                                        break;
                                }
                            }
                            break;
                        case 864982928:
                            return;
                    }
                }
                break;
            case 2:
                Set<String> set3 = k2.closedPopupIds;
                ImageView imageView = new ImageView(activity);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                imageView.setLayoutParams(new FrameLayout.LayoutParams(-1, -1));
                imageView.setImageBitmap((Bitmap) obj2);
                imageView.setClipToOutline(true);
                imageView.setOutlineProvider(new f2(activity));
                ((FrameLayout) obj).addView(imageView, 0);
                String str12 = "ۨۡۖۘۢۥ۫ۨ۠ۦۙ۬ۨۜ۫ۜۢ۫ۦۘۜۖۡۘ۫۬ۚۤ۠ۤ۬ۥۤۧۤ۟ۜۤۚ۟ۧۛۙۗ۟";
                while (true) {
                    switch (str12.hashCode() ^ (-169569441)) {
                        case -1029109245:
                            return;
                        case -586627254:
                            String str13 = "۟ۤۥۘۖ۠ۡۡ۟ۡۙۥۦۘ۟ۥۡۘۙۧۡۦۥۦۛۘۘۘ۠ۛۤ۠ۡۙ";
                            while (true) {
                                switch (str13.hashCode() ^ 179177512) {
                                    case -1626708267:
                                        str12 = "ۤۘۦۘۨۜۗۗۤ۬ۘۘۙ۠ۨۛ۠ۚۧۛۜۚ۫ۛ۫ۜۘۖۦۧۛۨۛۢۡۘۧۡۘۘۗۙۜۚۛۢ۫ۗۜۘ";
                                        continue;
                                    case -112994533:
                                        str13 = "۟ۧۖۘۦۘۜۘ۠ۙۜ۬ۖۡۘۜۘۚۥۢ۬ۙۘۖۖۙ۬ۘۖۡۘۙۡۛ۠ۢۦۘ۬ۜ۠ۙۜ۠ۗۖ۫";
                                        break;
                                    case 858184916:
                                        str12 = "ۚۧۚۧۨۖۘۧۨۙ۬ۖۧۘ۠ۜۜۢۜۥۘۧۡۧۘۧۢۖۘ۟ۥۘۜ۫ۜۨۢۘ۬ۖۧۘۥۖۘۘۜۚ۬۟۠ۙۘۘۥۘ";
                                        continue;
                                    case 1765972311:
                                        if (activity == null) {
                                            str13 = "ۦۗۥۘ۟ۜۢ۟ۤۧۛۤۛۡۘۦ۫۠ۨ۠ۢۘۘۡۤۦۘ۫ۜۢۡۗۖۘ";
                                            break;
                                        } else {
                                            str13 = "ۙۥ۫ۚۗۨ۬۠۟۟ۘ۟ۦۥۘ۫۠ۥۡ۫ۨۘۚۖۘۘۢ۠ۡۢۚۖۤۜۡۘۥ۠ۘۘۙ۬ۤ۟۠۬";
                                            break;
                                        }
                                }
                            }
                            break;
                        case -28897874:
                            String str14 = "ۤۚۡۨۘۤۥ۫ۖۥۗ۠ۙ۬ۖۘ۫ۡۛ۬ۤۨۘۚۦۤۗۨۚ۫ۜۙۨۨ۬ۙۖۘ۠ۜۛۧۜ۫ۘۙۗ۟ۦۘ";
                            while (true) {
                                switch (str14.hashCode() ^ 477221964) {
                                    case -2013595206:
                                        return;
                                    case -372119786:
                                        str14 = "۟ۘۖۘۛۦۖۛۘۜۘۙۛ۫ۜۜۘۜ۟ۛۚۖ۟ۦۦۘۜۦۨ۫۟۠ۛ۬ۜۥۛۥۘۘۦۛ۟ۜۖۘ";
                                        break;
                                    case 1131520215:
                                        String str15 = "۬ۜۨۘۨۗۗ۫۫۟ۨۢۜۢ۟۬ۡۢۖۤۗۤ۫ۦۘۘۤۥ۫ۜۘۘۨ۬ۦ۠ۛۚۨۖۥۘۗ۠ۜ";
                                        while (true) {
                                            switch (str15.hashCode() ^ 1049502438) {
                                                case -2099608658:
                                                    String str16 = "ۘۚۤۢۛۥۜۧۡۨۗۦۛۥ۠ۢۦۤۡۡ۟ۘۦۧۨۨۘ۫ۖۛۘۧۗۧۖۧۜۧ۟ۘۡۡۘۧ۫ۜۘۧۦۦۘ";
                                                    while (true) {
                                                        switch (str16.hashCode() ^ (-454652846)) {
                                                            case -380628209:
                                                                str16 = "۠۬۟ۢۥۡۧۛۨۘۖۘۡۘۧۡۘۘۡۜۧۘۦ۠ۚ۫ۨۥۦۘۜ۟ۨۚۥۗۗۨ۫ۢ";
                                                                break;
                                                            case 616293413:
                                                                str15 = "ۜ۫ۗ۠ۖ۫ۖ۠ۡ۬۠ۥۘۜۜۘۘۧۚۜۘۥۨۦ۠ۥۧۚۦۧ۫ۙۜ";
                                                                break;
                                                            case 1496704476:
                                                                if (!activity.isDestroyed()) {
                                                                    str16 = "ۨۨۘۘ۬ۘۥۤۙۦۘۜ۫۬ۦۨۥۘۢۡۧۘ۠ۙۦۢۙۘۘ۬۠ۦۤۤۡ۠ۢۙۖۘ۠ۗۙۘۡۥ۟";
                                                                    break;
                                                                } else {
                                                                    str16 = "ۜۥۧۡ۟ۧۘ۟ۨۘ۬ۙۦۘۙۘۖۢ۫ۧۛۨۢۙۙ۠ۛۙۧۤۖۘۧۤۡۥۥۘۘ";
                                                                    break;
                                                                }
                                                            case 1882229254:
                                                                str15 = "۠ۦ۬۟ۨۥۘۖۘۧۘۛ۠ۥۨ۠ۨۘۙۖۖۜ۠ۢۡۧۦۥۨۘۨۦۢۘ۬ۢۚۦۛ۟ۗۦۦۛۡۧ۟ۥۘۧۘۜ۫ۥۘۖۥۧۘ";
                                                                break;
                                                        }
                                                    }
                                                    break;
                                                case -1037664678:
                                                    ((Handler) obj4).postDelayed(((Runnable[]) obj3)[0], 1000L);
                                                    String str17 = "ۤۥ۠ۜۧۚۨۨ۬ۛۛۡ۟ۖۥۘۢ۠۠۬ۥۡۘ۠ۧۤۚ۬ۥۜ۟ۥۜۜۡۜۨۘۗۦۨۘ۫ۡ۬۟ۨ۟ۡۧ";
                                                    while (true) {
                                                        switch (str17.hashCode() ^ (-344335011)) {
                                                            case -1148435262:
                                                                return;
                                                            case -1055867433:
                                                                str17 = "ۛۜۘۗۛۚۧۥۧۘ۠ۧ۟۟ۨۚۖۡ۟ۙۡۧۧۥۖۘۚۡۗۡ۠ۦۘۦۖۦۘۜۖۘ۫ۗۤ۠ۙۚۧ۬ۛ";
                                                                break;
                                                            case -216090716:
                                                                String str18 = "ۖۜۥۘۤۘۧۜۧۙۘۘۤ۬۠ۥۥۧۛۧۜۘۛۧۖۙۥ۬ۧ۟ۛۛ۫ۚۛ۠ۗۙۖۘۨۢۛ";
                                                                while (true) {
                                                                    switch (str18.hashCode() ^ (-81206596)) {
                                                                        case -88562457:
                                                                            str17 = "ۙ۠ۖۥۤۖۚۛ۬ۨۘ۬ۡۤۧۘۡۜۘۧۜۢ۠ۗۦۤ۠ۜۘۦۢۥ";
                                                                            continue;
                                                                        case 788460298:
                                                                            str18 = "۫ۤۡۘۦ۟۫ۥۧۘۖۥۜۨۤۡۘۡۖۥۛ۠ۡۢۧۦۖۙۢۧۥ۫ۜۘ۬ۜۗۙ";
                                                                            break;
                                                                        case 1223652652:
                                                                            if (!activity.isFinishing()) {
                                                                                str18 = "ۥۥۥۘ۫ۙۗۘۧۗۗۡۡۚۛۘۗ۠۬ۚ۫۫ۥۛۘ۫۫ۖۡۖۖۘ۠ۡۗۜۜۘ";
                                                                                break;
                                                                            } else {
                                                                                str18 = "ۨۢۚ۬ۖۢۥۘۢۡۚۥۨۖۥ۫۫ۘۘۖۡ۟ۤۚۦۘۚ۟ۡۘۡ۠ۜۜ۠ۨ۟۬ۥ";
                                                                                break;
                                                                            }
                                                                        case 1702519257:
                                                                            str17 = "ۤۜۦۘ۫ۧۛ۬ۢۥۙۡۧ۟ۨۨۚ۟ۦۙ۠۫ۢ۟۠ۢۥۛۦۦ۫ۤۦۘۜۡ۬";
                                                                            continue;
                                                                    }
                                                                }
                                                                break;
                                                            case 55784061:
                                                                String str19 = "ۖۧۥۚۛۥۘۧۡ۠ۚ۫ۤ۫ۦۘۦ۠ۨۘۙۥۖۘۡۖۘۘ۠ۦۖ۫ۚۦۘ";
                                                                while (true) {
                                                                    switch (str19.hashCode() ^ (-415255394)) {
                                                                        case -180330297:
                                                                            return;
                                                                        case 73945922:
                                                                            str19 = "۠۟ۛ۬۟۫ۤۙۜۘۚۦۜۘۘۘۘۦۥۜۚۗۢۨۚۘۘۗۢۥۢۖۦۨ۬ۘۘۗۧۨۘۚۜۦۜۦ۫ۗۢۦۚۚۡۘ۫ۚۘۘۧۢۡۘ";
                                                                            break;
                                                                        case 293080162:
                                                                            String str20 = "ۖۥۘۨ۬۫ۙۖۜۘۦۙۦۦۚۨۤ۫ۘۘۛۘۧۦۦۜۘ۟ۧ۠ۤۦۖۘۥۢۧۚ۟ۖۖۚۖۢ۬ۙۚۛۤۛۘۘ";
                                                                            while (true) {
                                                                                switch (str20.hashCode() ^ 1850923590) {
                                                                                    case -1420737001:
                                                                                        if (!activity.isDestroyed()) {
                                                                                            str20 = "ۛۡۚۗ۬ۗۧۡۡۘ۫ۦۜ۟۟ۛۤ۟ۜۖۗ۬۠ۙۨۗۦۢۨۡۜۢۛ۬ۚۡ۬ۚۧۘۘۙ۟ۢۧۧۧۤۦ۟ۜ۠ۦۘۢۘۢ";
                                                                                            break;
                                                                                        } else {
                                                                                            str20 = "ۤۘۢ۠ۨۘۘۖۧ۠ۙۦۙۢۛۖۦۦۜۜۖۘۦۚۜۘۥۛۚ۬ۙۢۘۨ۠ۤۨۦۘۜۗۦۖ۬ۘۜۥۤۤۜۤ";
                                                                                            break;
                                                                                        }
                                                                                    case -409204057:
                                                                                        str19 = "ۚ۟۟۫ۘۢۘ۠ۖۘ۟ۚۘۛۘۥۤۜۚۤۙ۠ۜۜۧۢۚۜۧ۟ۦۘۙۙۙۧۧ";
                                                                                        continue;
                                                                                    case -316260424:
                                                                                        str19 = "ۖۢۖۜۦۛۗ۟ۨۘۘۧۦۘۢۧۨۘۡۥ۫۟۫ۦۧۖ۬۠ۙۥ۬ۨۛۚۙۖۦۚۖۚۛۦۙۚۥۘۨۗۜۦ۟ۜۘ";
                                                                                        continue;
                                                                                    case 33951865:
                                                                                        str20 = "ۙ۬ۙۤۢۚۛۡۘۦ۟ۧ۠ۖۧۦۥۢۗۛ۠ۛۤۨۘۢۧۜۘۚۦ";
                                                                                        break;
                                                                                }
                                                                            }
                                                                            break;
                                                                        case 1178139875:
                                                                            activity.runOnUiThread(new e1(activity, z, dialog, 3));
                                                                            return;
                                                                    }
                                                                }
                                                                break;
                                                        }
                                                    }
                                                    break;
                                                case -378282574:
                                                    return;
                                                case -49021900:
                                                    str15 = "ۢۖ۫ۦۧۤۜۢۖۘۜۚۧۨۘۘۢۧۤۡۥۖۨ۫ۨۘۜ۫ۜۘۘۙۡۘ";
                                                    break;
                                            }
                                        }
                                        break;
                                    case 1278477451:
                                        String str21 = "ۧ۟ۜۘۤۢۢۧۧ۟ۡۛ۫ۡ۟ۢۗ۟ۥۡۘ۟ۤۖۛۜۘۡۘۡۙ۠";
                                        while (true) {
                                            switch (str21.hashCode() ^ 387443556) {
                                                case -2086107706:
                                                    str14 = "ۙۖۡۖۚۦۘۢۦۖۥۧۨۚۘۙ۫ۖۛۛۦۤۜۛۗۤ۠ۤ۟ۘۦۘ۫۠ۙۡۡۦ۬ۢۙۡ۬ۛۧۜۚۜۨۦۘۚۢۨۘ۫ۢ۬";
                                                    continue;
                                                case -1568233182:
                                                    str21 = "ۥ۫ۢ۟ۥۗۖۜ۟۟ۡۦۘۛ۠ۥ۟۫ۤۜۙ۫ۙ۫ۚۧۚۙۢۨ۫ۥۘۦۦ۟ۚۤ۠ۙۙۖۘ۬۠ۛۚۜۖ";
                                                    break;
                                                case -924507395:
                                                    if (!activity.isFinishing()) {
                                                        str21 = "ۛۧۦ۟ۚۦۘ۠۫۟ۘۧۚۧ۠ۖۘ۬ۚۗۘۦۨۘ۠ۛۨ۬ۢۗ۬ۧ۫ۙۜۘۜۨۙ";
                                                        break;
                                                    } else {
                                                        str21 = "۫ۡۙۡۧ۬ۚۗۨ۬۠ۡۘۡ۫ۖۘۥ۟ۦ۫۟ۦۘۦۙ۟ۖۥۘۦۤۡۘۛۡۜۘۛۜۥۜۜۥۘ۠ۙۙۤۥۢۖۡۘ";
                                                        break;
                                                    }
                                                case -313742312:
                                                    str14 = "ۥۡۥۘۙۙۨۛۧۥۘۢۚۤۢۘۧۘ۫ۤۡۘ۠ۢۢۚۙۛ۠ۢۖۘۛۖۜ";
                                                    continue;
                                            }
                                        }
                                        break;
                                }
                            }
                            break;
                        case 1010614885:
                            str12 = "ۘۙ۬ۙۨۤ۟ۗۜۘ۬ۘۨ۬ۥۘۖ۠ۖۘۤۜۨۘۗۥ۟ۧۧۙۥ۟ۢۜۗۙۨ۟۬ۧۢۙۦۚۘۘۛۢ۬۟ۙ۠";
                            break;
                    }
                }
                break;
            default:
                final SharedPreferences sharedPreferences = (SharedPreferences) obj4;
                final String str22 = (String) obj3;
                final boolean z3 = this.f;
                final Dialog dialog3 = this.g;
                Set<String> set4 = k2.closedPopupIds;
                final Activity activity3 = this.b;
                String packageName = activity3.getPackageName();
                try {
                    PackageInfo packageInfo = activity3.getPackageManager().getPackageInfo(packageName, 0);
                    String str23 = packageInfo.versionName;
                    String str24 = "ۥۤۘۘ۬ۗ۟ۧۨۧۘۤ۫ۡۘۥۜۘۘۜۚۖۦ۠۬ۘۤۦۘۚۢ۫ۜۡ";
                    while (true) {
                        switch (str24.hashCode() ^ (-1321944860)) {
                            case -1241034230:
                                break;
                            case -653894800:
                                String str25 = "ۡۛۧۘۜ۠۫۟ۛۗۘۙ۠۬ۜۡۜۙۦۧۤۨۚۚۗ۬ۢۚۨۘ۠ۦۖۘ۫ۙۜۨۙۨۘۙۦ";
                                while (true) {
                                    switch (str25.hashCode() ^ (-1908117887)) {
                                        case -2132525081:
                                            str24 = "ۤۗۥۙۨۨۘ۠ۛۨۖۢۨۘ۟ۥۘ۟ۡۖۘۘ۫ۜۤۡۛۜ۠ۨۚۜۙۙۢۖۖۛۥۘ۬ۡۢۤۙۚ۬ۤۙۗۖۖۘ";
                                            continue;
                                        case -1174547387:
                                            if (str23 == null) {
                                                str25 = "ۨ۟ۦ۬ۖۨۨۡۧۘۤۗۥۘ۬ۧۜۜۦۨۘ۬ۨۙۙۤۥۘۧۗۦۘۥ۟ۧ۟ۨۦ۬ۜۜۘۚۙۜۢۨۦۘۡۛ۫ۛۜۜۤۛۥ";
                                                break;
                                            } else {
                                                str25 = "ۧۙۤ۟ۢۨۦۘۘۛۙۜۢۖۘۙۨۢۘ۟ۜۘۗۢۢۨ۬ۦۘۙ۬۠ۗۧۤۖۧۜۖۧۜۖۗۚۛۢۘ۟ۘ";
                                                break;
                                            }
                                        case -187117751:
                                            str25 = "ۘۖۜۤ۬ۧ۬ۗۦۘۖۙۦۘ۬ۤۢۗۜ۟ۨۦۘۥۡۥۘۗۜ۠۠ۛۖ";
                                            break;
                                        case 1962082253:
                                            str24 = "ۜۡ۠ۢۦۗۗۧ۟ۡۚۚۢۗۦۦۘۜۥۨۘۛۤۘۘ۬ۤۧۥۛۖۘۖ۬ۗ۬ۡۘ";
                                            continue;
                                    }
                                }
                                break;
                            case -634433329:
                                str24 = "ۡۗۜۘ۫ۘۥۥۢۢۥۙ۬ۢ۟ۜۘۖۤۜۛۗ۬ۘۧۘ۠ۚۡۘۖۤۥ";
                            case 2047206901:
                                String str26 = "ۨ۫ۥۘۜ۟ۤۧ۫۠ۢۦۡۘ۫ۨۗۡۤ۟۟ۚ۠ۙۗۥۘۚ۠ۚۖۘۜ۟ۖۘۙ۬ۨۘۦۢۙ۫۟ۧۜ۫ۨۦۛ۠ۗۨۙۢۖۘ";
                                while (true) {
                                    switch (str26.hashCode() ^ 122347129) {
                                        case -1165428348:
                                            str26 = "ۦۚۧۙۨۦ۬ۘۨۖۤۛۧۨۥۨ۠۠ۖ۠۟۫ۛ۬ۖۙۤۗۧۡۙ۫ۨ۠";
                                            continue;
                                        case -67864386:
                                            strDecrypt = str23;
                                            break;
                                        case 1524370147:
                                            String str27 = "۫ۨۖۜۘ۬ۨۛۜۘ۟ۢۨۘۜۧۧۛ۠ۙ۠ۧۡ۫۟ۗۖۡۜۘ۠۫ۦۘۢۧ۬ۢۧۚۙ۫ۗۢۜۦۘ";
                                            while (true) {
                                                switch (str27.hashCode() ^ 1538088527) {
                                                    case -1419479766:
                                                        if (!str23.isEmpty()) {
                                                            str27 = "ۦۖۦۘۙۧۘۘۡۗۜۨۚۛ۫ۤۦۘ۫۟ۙۥۛۚۜۚۚۜۢۖۘۥۤۜۥۖۧ۟۫ۡۨۖۚۨ۟۬ۙۧۖۘۡۖۥۧ۠ۤ۟ۛۨۘ";
                                                            break;
                                                        } else {
                                                            str27 = "ۤۛۢۙۧۘۙۦۚۚۧۗۡ۠۠ۢۛۗۨۧۥۙ۠ۤ۟ۛ۫ۡۘۨۧۦۡۗۢ۬ۤ۫۟ۧۧۧۨۘۡۦۖۘ";
                                                            break;
                                                        }
                                                    case -407129608:
                                                        str27 = "ۡۢۜۘۙ۫ۢۙۡۡۘۧۢۚۗۧۖ۫۫ۛۜۖ۠ۚۘ۟ۢ۫۫ۘۡۘۘۜۘۡ۫ۙ۠ۖۨۙۢۧ";
                                                        break;
                                                    case 996686842:
                                                        str26 = "ۡۜۙ۟ۤۛۙۘۨۘ۠ۤۦۢۚۦۙۚۤۤۜۥۥ۬ۤۙۢۥۡۨ۬ۤ۟ۛۥۘۦۜۧۗۘۡ۬ۗۘۢۢۥۦۘۗۜ۠ۛۘۜ";
                                                        continue;
                                                    case 1203877950:
                                                        str26 = "۟ۤ۬۠ۥۗۗۖۡۘۛۡۡۥۦۡۙ۟ۦۘۤۗۖۘۤۡۘۘۨۙۧۥۜ";
                                                        continue;
                                                        continue;
                                                }
                                            }
                                            break;
                                        case 1529366979:
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    int i2 = packageInfo.versionCode;
                    HashMap map = new HashMap();
                    final String str28 = (String) obj2;
                    map.put(l2.decrypt("OTa+RkE=\n", "UFjOMzXh2pg=\n"), str28);
                    map.put(l2.decrypt("tIw+ikE=\n", "1fxOwyV9XPg=\n"), fcRuQsQrcxOAzxwEalcM.APP_ID);
                    map.put(l2.decrypt("j3hfsf6S\n", "7ggv+pvriCA=\n"), fcRuQsQrcxOAzxwEalcM.APP_KEY);
                    map.put(l2.decrypt("T7WmmOMomKc=\n", "K9DQ8YBN0cM=\n"), Utils.getUniqueDeviceId(activity3));
                    map.put(l2.decrypt("FrYt+SsBIA==\n", "ZtdOkkpmRdQ=\n"), packageName);
                    map.put(l2.decrypt("5pl0MgXV1EP+nWsk\n", "kPwGQWy6uhw=\n"), strDecrypt);
                    map.put(l2.decrypt("pFOqdbAv0OaxWbxj\n", "0jbYBtlAvrk=\n"), String.valueOf(i2));
                    map.put(l2.decrypt("51TkgFwlkiTiWfOfWQ==\n", "kTGW8zVK/Hs=\n"), l2.decrypt("6AEf\n", "2Tcun+sDrIY=\n"));
                    StringBuilder sb = new StringBuilder();
                    sb.append(l2.decrypt("dbFZnL9/HaAW22fB4FNIyS+k\n", "kz7JeAXb+CY=\n"));
                    String str29 = (String) obj;
                    sb.append(str29);
                    k2.logToFloatingWindow(sb.toString(), l2.decrypt("SHpklw==\n", "IRQC+MIU3L8=\n"));
                    try {
                        Object[] objArrPostRequestOnce = Utils.postRequestOnce(str29, map);
                        String str30 = "ۙۧ۬ۙۦۦۘۧۦۧۗۨۘ۟ۤۢۥۥ۬ۜۜ۠ۦۥۘ۬۫ۚ۠۟ۦۘ";
                        while (true) {
                            switch (str30.hashCode() ^ (-1942245267)) {
                                case -1268450139:
                                    String str31 = "ۜۦۡۧۛۜۘۦۢۖۘ۠ۧۖۖ۠ۜ۠ۦۜۘۙۗ۬ۦۨۚۙ۟ۤۜ۫۫ۤۖۖ۟۠۠ۤۘۘۡ۫ۧ";
                                    while (true) {
                                        switch (str31.hashCode() ^ (-1000564126)) {
                                            case -872453920:
                                                str31 = "ۖۦۧ۟ۤۖۘۖۧۖۘۥۛۥۘ۠ۨۦۦۚۜۜۘۦۙۡۘۨۥۨۘۙ۬ۙۜ۬۬ۚۛۘۤۨۨ۫ۗۘۥۥۘۚ۫ۨ";
                                                break;
                                            case 265707859:
                                                str30 = "۟ۘۖۘ۠ۢۢ۠۬ۧۢۖۨ۟ۙۨ۬ۧۖۘۖ۫ۖ۫ۜۦۘۢۜۜۘۗۥۢۢۛ۫۬ۚۖ۬ۢۙۦ۟ۨۘ";
                                                continue;
                                            case 993648548:
                                                if (objArrPostRequestOnce == null) {
                                                    str31 = "ۨۛۥۘۗۖۘۜۡۜۘ۬ۧۙۛ۠ۢ۟ۤۨۘۙۗ۠۠ۙۨ۟ۙۖۘۥۖۘ";
                                                    break;
                                                } else {
                                                    str31 = "۬ۙۡۘۨۢۚۥۚۨ۬۠ۦۛۨۘۛۙۡۛۖۨۦۘۘۙۢۘۙۤۡۘ۫ۥۘۘۛ۬۫ۧ۟ۜۛۨۘ";
                                                    break;
                                                }
                                            case 1699372788:
                                                str30 = "۠ۙۡۚۖ۫۟ۛ۫ۢۚۜۖۖۡۧۨۘۘ۫ۤۦۜۢۜۨۥ۬۟ۡۘ";
                                                continue;
                                        }
                                    }
                                    break;
                                case 405401698:
                                    str30 = "ۗۜ۫ۗ۫ۘۦۜۢۚۦۖۘۢۙۚ۟۫ۜ۫ۛۚ۬ۤۚۧۢۤۧۖۡۘۙۧۥۘ۠ۙۚ";
                                    break;
                                case 1892549887:
                                    String str32 = "ۙۚۦۡۜۚۢۙۨۘۜۨۚ۠ۖ۠ۥۙۛۗۤ۫ۥۖۦ۠ۖۜۗۘ۬ۚۛۛ۬ۧۧۙۜۘۚ۫ۜ۫ۙۨۘۤ۬ۘۘۤۙۛۦ۫ۜ";
                                    while (true) {
                                        switch (str32.hashCode() ^ 2123327175) {
                                            case -1898456414:
                                                String str33 = "ۘ۫ۧۛۜۦۛ۠ۥۗۧۙۢ۠ۡ۟ۗۨ۫ۧۨۙۨ۟ۢۙ۠ۘۛۡۜۖۖۘۜۡۦۦ۬ۦۘۙۗۘۘ۫ۨۡۘۦ۠";
                                                while (true) {
                                                    switch (str33.hashCode() ^ 1971712465) {
                                                        case -345262116:
                                                            str33 = "ۙۛۥۘۜ۫ۦۦ۬۬۟ۨ۬ۡۙ۟ۜ۟ۦۘ۠ۤۛۖ۟ۜۛۨۖۢۥۡۚ۫ۡۘۚۚۗ";
                                                            break;
                                                        case -271371600:
                                                            str32 = "ۤۤۗۢۤۥۘ۬ۜۘۙۡۘۘۢ۟ۦ۫ۖۧ۟ۢ۫ۗۤۜۘۢۜۡۘۤ۬ۧ۫۫ۜۘۢۥۘۙ۫ۤۤ۫ۖۤۜۘۘۚۙ";
                                                            continue;
                                                        case 829888760:
                                                            if (objArrPostRequestOnce.length < 4) {
                                                                str33 = "ۚۛۗ۫ۤ۫ۨۥ۟ۙۧۡ۫ۛۧۢۘۗۤ۫ۤۨۜ۫۬ۗۚۡ۟ۡۘ";
                                                                break;
                                                            } else {
                                                                str33 = "ۗۤۨۜۗ۟ۛۦ۟ۤۜۨ۟۬ۘۚۦۗۛۦۤۡۨۥۘۨ۬ۧ۠ۨۥۘ۫۟ۘۘۘ۠ۤۗۧۛۧۨ۬ۜۤۡ۫ۘۛ";
                                                                break;
                                                            }
                                                        case 2128234402:
                                                            str32 = "ۘۘۦۖۙ۫۟ۡۛۙۛۧۥۙۢ۬۟ۚۨۧۖۘۧ۬۫ۚۢۥۙۙۧ";
                                                            continue;
                                                    }
                                                }
                                                break;
                                            case -1883756113:
                                                break;
                                            case -1653839522:
                                                str32 = "ۚۤ۠ۗۜۦۘۡۥۢ۫ۗ۬ۖۤۙ۠ۢۜۘۡۥۚۖ۠ۚۜۥۤۥۖۖۜۡۘۡ۬ۧۥۙۡۘۤۢۚۜۚۚۡ۫ۘۗۧۜۛۡ۬";
                                                break;
                                            case -619899375:
                                                final boolean zBooleanValue = ((Boolean) objArrPostRequestOnce[0]).booleanValue();
                                                Object obj5 = objArrPostRequestOnce[3];
                                                String str34 = "ۜۘۜۙ۠ۘۘۦۖۖۘ۠ۛۨۨ۬ۘ۟ۚۘۘۖۜۧ۬ۥ۟ۚۚۧ۠۠۫ۖ۫ۦۘۛ۬ۘۘ۟۬۫ۢۢۢ";
                                                while (true) {
                                                    switch (str34.hashCode() ^ 1367043913) {
                                                        case -1827401166:
                                                            strReplaceAll = "";
                                                            break;
                                                        case -1361691211:
                                                            str34 = "ۤۙۨۘۥۜۛۢۦۗۗۤ۟ۖۦۚۜ۟ۛۘ۠ۨۘ۬ۜۛ۫۟ۚ۟ۖۦۜۖۦۦۨۦۘ۠ۤۜۘۧۚۥۘۨۢ۫۟ۛ";
                                                            continue;
                                                        case 25209641:
                                                            String str35 = "۫ۢۗۧۢۧۖۦۦۘۦۖۡۢۨۤۢ۟ۘۘ۠ۢۦۨۗۤۤۦۘۨۚۥۘۘۗ۫ۘۤۚ";
                                                            while (true) {
                                                                switch (str35.hashCode() ^ (-24125811)) {
                                                                    case 177248208:
                                                                        str34 = "ۢۚۛ۬ۚۧۘۡۘۜۧۙۙۗ۠ۛۛ۟ۘۧۖۢۛۚۛ۟ۢ۟ۖۖۘ۫ۛ۟ۙۢۛۜ۫ۨۧ";
                                                                        continue;
                                                                        continue;
                                                                    case 631493675:
                                                                        str34 = "ۘۦۦۘۡۜ۬ۢۥۘۘۘۙۛۡ۫ۡۤۡۘۧۨۛۥۖۦۘۢۗۥ۠ۧۨۘۥۡ۟۟ۜۙۗۦۜۚۧۨ";
                                                                        continue;
                                                                    case 1684416057:
                                                                        if (obj5 == null) {
                                                                            str35 = "ۡۗۘ۟ۜۡۡۡۧ۬ۨ۬ۘۢۡۜۘۘۥۖۙۤۤۘۚۡۛۚۘۘ";
                                                                            break;
                                                                        } else {
                                                                            str35 = "ۨۚۜۙۦ۫ۘۘۨ۬ۢۦۘ۟۬ۛۦۜ۫۟ۘ۫۫ۗۜۘۦۨۡۜۤۖ";
                                                                            break;
                                                                        }
                                                                    case 1952219819:
                                                                        str35 = "ۥۡ۫ۡۢۡۧۨ۫ۤۡۤۙۨۜۗۜ۠ۨ۠ۖۗۡ۟ۢ۫۫۫ۘۧۜ۫ۙۚۙۛ";
                                                                        break;
                                                                }
                                                            }
                                                            break;
                                                        case 1708182590:
                                                            strReplaceAll = obj5.toString().trim().replaceAll(l2.decrypt("/Iwm\n", "oP8N1mx4SpU=\n"), "");
                                                            break;
                                                    }
                                                }
                                                final Map map2 = (Map) objArrPostRequestOnce[2];
                                                new Handler(Looper.getMainLooper()).post(new Runnable(strReplaceAll, sharedPreferences, str22, str28, z3, activity3, dialog3, map2, zBooleanValue) { // from class: core.pro.android.notify.a2
                                                    public final String a;
                                                    public final SharedPreferences b;
                                                    public final String c;
                                                    public final String d;
                                                    public final boolean e;
                                                    public final Activity f;
                                                    public final Dialog g;
                                                    public final Map h;
                                                    public final boolean i;

                                                    {
                                                        this.a = strReplaceAll;
                                                        this.b = sharedPreferences;
                                                        this.c = str22;
                                                        this.d = str28;
                                                        this.e = z3;
                                                        this.f = activity3;
                                                        this.g = dialog3;
                                                        this.h = map2;
                                                        this.i = zBooleanValue;
                                                    }

                                                    @Override // java.lang.Runnable
                                                    public final void run() throws JSONException {
                                                        String str36 = this.a;
                                                        SharedPreferences sharedPreferences2 = this.b;
                                                        String str37 = this.c;
                                                        String str38 = this.d;
                                                        boolean z4 = this.e;
                                                        Activity activity4 = this.f;
                                                        Dialog dialog4 = this.g;
                                                        Set<String> set5 = k2.closedPopupIds;
                                                        try {
                                                            JSONObject jSONObject = new JSONObject(str36);
                                                            String str39 = "ۜۡ۬ۤ۬ۧۨۥ۬۠ۚۖۛۚ۟ۢ۫ۚۚۘۙۙۗۢ۟ۥۦۢۢۢ۫۫۠ۡۖۘۘ۟۬۬ۗ۬ۧۤ۬ۥۘۦۜۤۜ۟ۦ۫ۙۥ";
                                                            while (true) {
                                                                switch (str39.hashCode() ^ 12348721) {
                                                                    case -1327185166:
                                                                        return;
                                                                    case -1294014552:
                                                                        str39 = "ۖۢۜۘۙۥۖۗۘۜۡۜۥۦۗۜۘۨۥۘۥۨۧۘۛۧۘۘۡ۠۠۬ۚ۫ۢۧۖۘۜۗ۬ۡۜۙ۟۫ۥۘ۟ۙ۬ۤ۬ۜۘۥ۟ۦۤۗۨ";
                                                                        break;
                                                                    case -876850398:
                                                                        String str40 = "۫ۙۘۘۜۡۥ۠ۛۜۘ۬ۜۧۘ۟ۘۜۨۛۦۘۧۨۜۖ۟ۦۘۘۚۜۘۤ۫۟ۥ۬ۥۚۚ۠ۜۥۨ۠ۗۥۜۦ۟ۧۡۡۚۤۥۘۜ۟ۥۘ";
                                                                        while (true) {
                                                                            switch (str40.hashCode() ^ (-609703292)) {
                                                                                case -1836341683:
                                                                                    str40 = "۫ۖۖ۫ۢۤۖۥۢۜ۟ۖۘۜۦۥۜۖۜۧۘۚۙۘۜۤۢۜۘۜۛۥ۫ۤۨۘۦ۟ۦۖۛۜۘ۫۬ۥۤۧۘۧ۬ۨ۫۠ۧۥ۬ۤ";
                                                                                    break;
                                                                                case -720025164:
                                                                                    return;
                                                                                case -218688585:
                                                                                    int i3 = jSONObject.getInt(l2.decrypt("78bDUg==\n", "jKmnN+Z8Bn8=\n"));
                                                                                    String strOptString = jSONObject.optString(l2.decrypt("8C1WCObEyw==\n", "nUgle4ejrk0=\n"), "");
                                                                                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject(l2.decrypt("3274wA==\n", "uw+MocRdaSI=\n"));
                                                                                    String strDecrypt2 = l2.decrypt("5r2CGAI6\n", "ACEo/52fvJ0=\n");
                                                                                    String str41 = "ۡ۫۬۫۫ۨۘۚ۟ۧۧۙۢۤ۫۟۫۠ۚ۫۬ۜۧۜۧۘۙ۟۬۟ۖۨ";
                                                                                    while (true) {
                                                                                        switch (str41.hashCode() ^ 886079320) {
                                                                                            case -432201742:
                                                                                                strDecrypt2 = jSONObjectOptJSONObject.optString(l2.decrypt("I1cmXQHJwbc2bS9dEdQ=\n", "UTJLPGinqNk=\n"), strDecrypt2);
                                                                                                break;
                                                                                            case -405869838:
                                                                                                break;
                                                                                            case -234859865:
                                                                                                String str42 = "ۛ۟ۥۘۙۤۧۥۢۥۘۥۥۥۛۤۚ۟ۖۧۛۘۛۤ۠ۙۦۘۙ۠ۜۘ";
                                                                                                while (true) {
                                                                                                    switch (str42.hashCode() ^ (-134328121)) {
                                                                                                        case -267381563:
                                                                                                            str41 = "ۖ۟ۢۗۨۥۘۥۡۖۘ۫ۛ۠ۤۜۥۘۘۨ۫ۡۥ۟ۨۨۨۘۙ۫ۚ۠ۘۚ۫ۥۙۛۗ۬ۛۘۘۘۧ۫ۨ";
                                                                                                            continue;
                                                                                                            continue;
                                                                                                        case -175474621:
                                                                                                            str42 = "ۙۧۘۘۥۧۡۘۨ۬۟ۖۙۚۚ۬۟ۦۤ۟ۚۦ۬ۨۤۢۥۢۚ۫۫ۛ۟۠ۖۘۧۛۨۘ";
                                                                                                            break;
                                                                                                        case 839635917:
                                                                                                            str41 = "ۙۚۖۘۙۚۘۗۦ۟ۨۧ۬ۘۗۢۖۤۨۘ۬ۦۛۜۛ۠ۛۤۡۘۙ۫ۗۗۢۗ۫ۖۦ۠ۗۥۙۗۖۘ";
                                                                                                            continue;
                                                                                                        case 1669971012:
                                                                                                            if (jSONObjectOptJSONObject == null) {
                                                                                                                str42 = "ۥۘۡۘ۠ۘۦۘ۟۬ۜۨۦۥۘۦۘۨۚ۫ۨۘۖۜۢ۠ۧۨۘ۟۬ۜۖ۟ۚ";
                                                                                                                break;
                                                                                                            } else {
                                                                                                                str42 = "ۙ۬ۚۡۘ۠۬۬ۖ۬۟ۤۜۨۗۖۚۛ۫ۦۛۚۜۘۙۥۧۧۦۘۘۙۡۛۛۤۨۤۛۘۘۡۦۧ";
                                                                                                                break;
                                                                                                            }
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                            case 1577112052:
                                                                                                str41 = "ۚ۠ۡۘۢۘۘ۬ۧۦۙۥۚۡۖۛ۫ۖۘۢۘۦۦۙۡۜۘۖۘۨۙۗ۟ۡۚۘۘۛۦۖۘ۬۫ۥۤۘۗۡۖۤۢ۟ۗۜۘۘ";
                                                                                                continue;
                                                                                        }
                                                                                    }
                                                                                    String str43 = "ۚۚ۬۬۫۠ۜۗۙۧۜۖۛۡۘۜ۫ۜۘۡۤۘۙۖ۬۫ۘۙۥۗۤۦۨۖۢ۫ۤۨ۠۠ۥۚ";
                                                                                    while (true) {
                                                                                        switch (str43.hashCode() ^ 1972138916) {
                                                                                            case -1746066744:
                                                                                                str43 = "ۧۜۤۙۡۡ۬ۜۦۘ۬ۜۜۘۨۢۥۦۘۦۨۗۘۗۜ۫ۥۧۥ۫ۤۨۘۘۥۨۘۢۘۡۥ۟ۘۘۙ۬ۛ";
                                                                                                break;
                                                                                            case -712984534:
                                                                                                String str44 = "ۗۜ۟ۦۙۘۘۢۡۘۤۙۢۨۘۧ۫ۡۘۘ۬ۚۦۘۨ۟ۙۧۡۧۘۢۦ۠۟ۡۥۖۧۛۙۛۧۙ۫ۗ";
                                                                                                while (true) {
                                                                                                    switch (str44.hashCode() ^ (-1892542161)) {
                                                                                                        case -1830147827:
                                                                                                            String str45 = "ۨۘۢۤۛ۬۟ۜ۫ۡ۬۫ۙۥۨۘۡۧ۟ۥۤۥۥ۬ۘۘ۟۬ۖۘۢۡۛۧۗۖۘ۫ۗۗۥ۫ۡۜۦ۠";
                                                                                                            while (true) {
                                                                                                                switch (str45.hashCode() ^ 1524209263) {
                                                                                                                    case -2100693296:
                                                                                                                        str44 = "ۖ۫ۢۡۜۖۘۢۖۧ۠ۚۜۨۙ۠۟۠ۜۡۘۢۨ۬ۧۧ۫۠ۜۢ۬ۖۜۘۦۜۘۘ";
                                                                                                                        continue;
                                                                                                                    case -1768354077:
                                                                                                                        if (!strOptString.equals(l2.decrypt("y0Q=\n", "pC/M3ONZZt8=\n"))) {
                                                                                                                            str45 = "ۥۘۘ۠ۥۨ۬ۘۦۘۘۦۗۚۗ۠ۤۚۡۘۚ۠ۜۘ۬۠۫ۥۘۙۜ۬ۜۘۖۢۨ۠۠ۨ۟۟ۜۘۡۡۤۢۡۘۘۙۦۧ";
                                                                                                                            break;
                                                                                                                        } else {
                                                                                                                            str45 = "ۦۚۡۜ۟۟ۥ۫ۨۡۚۦۦۦۤۙۧۦۗۤۧ۟ۙۨۦۗۖۘۧۖۘ";
                                                                                                                            break;
                                                                                                                        }
                                                                                                                    case -1141109238:
                                                                                                                        str44 = "ۗۖۡۥ۫ۦۘۥۦۧۘۥۢۤ۟ۥۙۤۚۜۨۙ۬ۥۘۘ۠ۡۗۖۦۦ";
                                                                                                                        continue;
                                                                                                                    case 1248439707:
                                                                                                                        str45 = "۫ۡۡۘ۬ۡۡۘۥ۬ۤۦۧۗ۫ۛۛۢۡۨۘۗۡۖ۬۠ۦۘ۫ۨۗۛۥۦۘۢۜۥۖۖ۫۠۠ۦۖۛ۫ۙۨۜۤۨ";
                                                                                                                        break;
                                                                                                                }
                                                                                                            }
                                                                                                            break;
                                                                                                        case 727385294:
                                                                                                            Toast.makeText(activity4, strOptString, 1).show();
                                                                                                            k2.logToFloatingWindow(l2.decrypt("2+dl65suS2UrEMMP8Ul0AHcogEz9OzM+Fo4K\n", "kbQqpX2h24E=\n") + strOptString, l2.decrypt("WMFT3uo=\n", "PbMhsZjK334=\n"));
                                                                                                            return;
                                                                                                        case 745468602:
                                                                                                            sharedPreferences2.edit().putString(str37, str38).apply();
                                                                                                            String str46 = "ۚۥۙۙ۟ۧۙۥۜۘۘۧۙۦۡۧۤۚ۫ۢۨۥۘ۠ۙ۫ۗۧ۟۫ۨۘۛۤۧ۟۟ۨۘۤۘۛۘۜۨ۬۠۠۬۠ۘۘ";
                                                                                                            while (true) {
                                                                                                                switch (str46.hashCode() ^ (-1024336538)) {
                                                                                                                    case -1991466859:
                                                                                                                        break;
                                                                                                                    case -1791458993:
                                                                                                                        str46 = "ۜۢۢ۟ۜ۫ۢۖۛۛۘۛۦۘۘۡ۬ۥ۠ۘۘۧۙۘۙۥۥۦۨۗۥۛ۫۬ۜۧ";
                                                                                                                        break;
                                                                                                                    case -670528985:
                                                                                                                        k2.logToFloatingWindow(l2.decrypt("S2H4n75rLBo9Ac7Tz2dCQRJdrNy+\n", "ruRLdinGxKQ=\n"), l2.decrypt("Sd3QjA==\n", "ILO242f7e9Q=\n"));
                                                                                                                        k2.closedTextPopupIds.add(str37);
                                                                                                                        String str47 = "ۚۖۡۘۥۜۨۘۘۦۖۢۥۦۘۜۨۛۦۘۡۥ۫ۖۘ۠ۥۘۘ۟ۧۜۘۛۚۖ۟ۙۧ۬ۨ۬";
                                                                                                                        while (true) {
                                                                                                                            switch (str47.hashCode() ^ (-2144196582)) {
                                                                                                                                case -1645343428:
                                                                                                                                    String str48 = "ۜۜۥۗۤۦۘۥۦۜۘۨۚۘۚۖۡۤۜۘ۫ۢۦۘۨۢ۬۫۫ۜۘۖۖۘۘۥۖۢۚۡۘۦۙ۠ۗۡۘۚۧۡۘ۫ۥۥۗۖۖۜۘۧ";
                                                                                                                                    while (true) {
                                                                                                                                        switch (str48.hashCode() ^ (-573739782)) {
                                                                                                                                            case -1469496799:
                                                                                                                                                str48 = "ۜۗۡۧۤۨۜۨۥۥۦۦۘۢ۟ۖ۟ۜۘ۫ۤۨۘۥۡۦۘۤۥۜۙۡۨۘ";
                                                                                                                                                break;
                                                                                                                                            case 563081705:
                                                                                                                                                str47 = "ۨ۟ۦۘۙۜۢۖۖۙۙۤۚۗۘۙ۬ۥۗۗۨ۠ۚۚۛ۟۟ۗۙۨۚۗۦۘۥۚۜۘ";
                                                                                                                                                continue;
                                                                                                                                            case 1647983435:
                                                                                                                                                str47 = "۠ۡ۫ۨۜۥۘ۠ۙۜۘۛۡۡۘۧۘۡۘۙ۟ۧۗۙۢۗۚۢۚ۠۠ۢۥۘ۠ۛۖۘۥۧۖۘ";
                                                                                                                                                continue;
                                                                                                                                            case 2048916271:
                                                                                                                                                if (!activity4.isFinishing()) {
                                                                                                                                                    str48 = "ۥۨۜ۟ۖۖۘۙ۠ۛۘ۬۬ۜ۬ۜۙۜۚۙۧۨۙۛۖۘ۫ۛۥۥ۬ۚ";
                                                                                                                                                    break;
                                                                                                                                                } else {
                                                                                                                                                    str48 = "ۥۙ۬۬ۢۜۘۙۙۥۘۜ۟ۡۘۡۗ۠ۥ۫ۨۘۖۥ۠ۨۦۛ۠ۘۥۜۥۘۤۗ۫ۖۥۥۚۜۧۘۦۤۖ۠ۥۤۨۥ۫ۤۡ۫ۚۜۘۘ";
                                                                                                                                                    break;
                                                                                                                                                }
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    break;
                                                                                                                                case -1441655249:
                                                                                                                                    String str49 = "ۗۧۥۦ۫ۜۘۘۧۘۘۢۚۖۘۨۡۥۢۡۤۚۧۜۛ۟ۡۘۨۜۧۘۘ۟ۥۘ";
                                                                                                                                    while (true) {
                                                                                                                                        switch (str49.hashCode() ^ 915248460) {
                                                                                                                                            case -338731716:
                                                                                                                                                break;
                                                                                                                                            case 694316489:
                                                                                                                                                str49 = "ۜۥۜۘۦ۟ۥۢۦ۫ۖۗۢۚۚۥۘۜۗۤۙ۟ۥۘۚۖۛۛۤۨۢۡۨۘۧۤۜۘۘ۟ۜۘۛۡۧۖ۟ۖ۬ۥۧۘۧ";
                                                                                                                                                break;
                                                                                                                                            case 1503235357:
                                                                                                                                                String str50 = "ۤۘۡۘۗۧۘۗۦۛۥۘۡۢ۠۠ۛۨۘۖۥ۠۬ۡۜۘۛۦۥۘۡۧۡۘۤۜۢۨۛ۬ۦۥۥۖۚۖۥۘۚۢ۟ۖۚ۠ۜۦ";
                                                                                                                                                while (true) {
                                                                                                                                                    switch (str50.hashCode() ^ 182722586) {
                                                                                                                                                        case -2049470321:
                                                                                                                                                            str49 = "۟ۖۥۚۢۡۘۡۦۖۢ۠۬ۧۦۘۙۗۡۘۦۤۦۘۛۖۥۘۙۜ۬ۛۢۖۗۚۦۧۖ۠ۖ۟ۡۡ۫۟ۥۢۘۚۛۡۘۦۙۦ۬ۖۢ";
                                                                                                                                                            continue;
                                                                                                                                                        case -321498104:
                                                                                                                                                            if (!dialog4.isShowing()) {
                                                                                                                                                                str50 = "ۘۤ۫۠ۖۘۢ۟ۧۡۘۜۘۜۡۜۘۚۖ۬ۨۤۖۘۘۨ۠ۙۥۥۙۨۥۘ";
                                                                                                                                                                break;
                                                                                                                                                            } else {
                                                                                                                                                                str50 = "ۤۧۧۛۘۗ۠ۙۨۘۚۥۥۘۥۨۤۤۤۢۨۥۦۘۥۨۧۘۥۚۖۨۢۚۦۘۧ۟ۨۦۘۘۧۥۛۨۛۚۢۜ۟";
                                                                                                                                                                break;
                                                                                                                                                            }
                                                                                                                                                        case -90820183:
                                                                                                                                                            str50 = "ۗۘۖ۫۫۬ۖ۬ۧۘۤۘۘۦ۟ۜۘ۟ۖۤۥۛۨۤۡۨۘ۫ۡۜۘۙۙۘۦۜۘۘۖۗۜۙۤۦ۫ۖۦۙۨۡۘۚۨۘۘ۫ۨ۬ۤ";
                                                                                                                                                            break;
                                                                                                                                                        case 490676740:
                                                                                                                                                            str49 = "۠ۘ۟ۜۥۘۘۧۥۡۘۤۙۦۧۡۘۙۙ۬۬ۛۨۘۨ۠ۢۙۜۧۘۤ۟ۖ۠ۙۗ۠ۨ";
                                                                                                                                                            continue;
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                break;
                                                                                                                                            case 1531366452:
                                                                                                                                                dialog4.dismiss();
                                                                                                                                                break;
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    break;
                                                                                                                                case -457203790:
                                                                                                                                    str47 = "۟۠ۢۚ۠۬۬ۗۘۛۥۖۘ۫ۦۙۡۥۘۗۜۡۛ۟ۡۧ۟ۥۘۖۖ۠ۧۗۗۨۛۥ";
                                                                                                                                    break;
                                                                                                                                case 174296011:
                                                                                                                                    break;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        break;
                                                                                                                    case 1762453124:
                                                                                                                        String str51 = "ۚۡۘۗۢۜۘۥۥۜ۟ۤۤۜۥۖ۫ۢۗۡ۟ۦۘۛۥۗ۬ۙۖۘۖۧۘۘۖۚۢ۠ۘۛۦۤ۬ۘۘۖۗۖ۬ۤۢ";
                                                                                                                        while (true) {
                                                                                                                            switch (str51.hashCode() ^ (-1657006683)) {
                                                                                                                                case 257719306:
                                                                                                                                    if (!z4) {
                                                                                                                                        str51 = "۫ۥۧۨۡۖۨۨ۫ۗۡۗۨۖۖۘۢۚۛۖۗ۫۟ۡۧۗۜۚۧۗۜۘۘۥۦۘ۫ۥۥۘ۟ۛۙۡۢۨۘۢۜۨۚۘۤ";
                                                                                                                                        break;
                                                                                                                                    } else {
                                                                                                                                        str51 = "ۖۚۜۘۦ۠ۙۛۥۘۘۢۨ۫ۡۢۡۘۢۥۖۜۖۦۘۢۘۘۧۖۚۧۢۗ۟ۦۛۥۙۨۘ";
                                                                                                                                        break;
                                                                                                                                    }
                                                                                                                                case 1155561136:
                                                                                                                                    str46 = "ۢۧ۠ۚۜۘۘۦۤۨۘۥۨۜۦۥ۫ۘ۬ۜۦۥۜۘۘۛۚۙۚ۬ۡۡۥۤۨۘۛۙۨۦۙ۬ۢۘ۠";
                                                                                                                                    continue;
                                                                                                                                case 1624939111:
                                                                                                                                    str46 = "ۡۜۥۨ۟۠ۥ۠ۡۘ۟ۙۧۙۖۥۘۛۗۖ۬ۧۧۗۥۥۘۧ۟ۘۡۨ۠۬ۜۘ۫۫۬۠ۨۘۘۦۥۘۧۡۚ۬ۚۘ۠۟ۖۛۜۨۘ";
                                                                                                                                    continue;
                                                                                                                                case 1781693013:
                                                                                                                                    str51 = "۫ۤۢ۟ۗ۠ۤۧۙۡۤۚ۬۟ۜ۟ۖۨ۬۟ۢۡۘۤۖۚۘۙ۬ۘۦۛۢۧۚۘۛۚۛ۫ۗۛ";
                                                                                                                                    break;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        break;
                                                                                                                }
                                                                                                            }
                                                                                                            Toast.makeText(activity4, strDecrypt2, 1).show();
                                                                                                            k2.logToFloatingWindow(l2.decrypt("gJvi6XLQCEFwbEQNGLc3JCNIN08r2KKF\n", "ysitp5RfmKU=\n") + strOptString, l2.decrypt("LmPCWQ==\n", "Rw2kNhjLxoU=\n"));
                                                                                                            return;
                                                                                                        case 1762045047:
                                                                                                            str44 = "ۤ۠ۖۚۗۙۚۖۡۢ۠ۚ۟۠ۥ۠ۗۚۡۢۗۡۥۨۢ۬ۨۘۤ۟ۛ۠ۚۚۡۡۡۥ۠ۡۘ۬ۛ۟ۖۘۥۧۨۛۖۦۥۖ";
                                                                                                            break;
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                            case 1716955413:
                                                                                                String str52 = "ۧۤۚۢۗۨۘۨۘۖۘۖۤۖۘۡۛۦۘۡۨۘۥۘۜۛۥۧۖۘۧۨ۠ۗ";
                                                                                                while (true) {
                                                                                                    switch (str52.hashCode() ^ 44204356) {
                                                                                                        case -1213365877:
                                                                                                            if (i3 != 200) {
                                                                                                                str52 = "ۢۢۘۧۧ۫۟ۚۖۖۤۨ۟ۛۧۧۜۢۥۤۙۥۙۨۥۚۘۨۖۘۘۥۤۜۘ۬۟ۢۡۙۘۘۤۙۢ";
                                                                                                                break;
                                                                                                            } else {
                                                                                                                str52 = "ۤ۬۟ۨ۫ۗۘۚۗۧۘۚ۠ۖۨۘۖۚۨۘۘۜۘۚۧۖۡۨۥۘۧ۬ۜۖۡۦۘۚۢ";
                                                                                                                break;
                                                                                                            }
                                                                                                        case -755990247:
                                                                                                            str43 = "ۙ۫ۖۘۦۤۡۘۜۚۖۦۦۙۘۘ۠۬ۡۘۢۛۥۘۙۗۥۘۧۗۥۘ۫ۙۧ";
                                                                                                            continue;
                                                                                                        case 146280672:
                                                                                                            str43 = "۬ۜۜ۫۠۟۠ۨۨۗۖۘۥۢۢۤۡ۫۠ۖۨۜ۠ۢۡۙۤۡۙ۠ۚۥۜۘۖۖۡۦۚۦۘۜۢۜۗ۫۫ۜۢۢ";
                                                                                                            continue;
                                                                                                        case 2068209094:
                                                                                                            str52 = "ۖۜۜۘۚۢ۟ۙۗۤۙۥۧۘۥ۫۫ۜۧ۬ۢۗۡۘ۠ۛۥ۠ۛۡۘ۫ۘۨۘۘ۫ۜۤۡ۠";
                                                                                                            break;
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                            case 2027053063:
                                                                                                k2.logToFloatingWindow(l2.decrypt("AlUFyfMmx/3yoqMtmUH4mK6a4G6VM7+mzzxq\n", "SAZKhxWpVxk=\n") + strOptString, l2.decrypt("l+fALak=\n", "8pWyQtsq66I=\n"));
                                                                                                Toast.makeText(activity4, strOptString, 1).show();
                                                                                                return;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case 1051041788:
                                                                                    String str53 = "ۨۡ۫ۛۥۘۗۙۧۚۛۚ۫ۦۚۧۧۜ۫ۧۘۨۜۘۡۢۤۡۜۥۘۖ۠ۙۖ۟ۨۗۗۜۘۨۗۜۘۨۗۜۘ۟۠ۢ";
                                                                                    while (true) {
                                                                                        switch (str53.hashCode() ^ (-655326082)) {
                                                                                            case -1920997379:
                                                                                                if (!jSONObject.has(l2.decrypt("WL3CMwW5BQ==\n", "NdixQGTeYHA=\n"))) {
                                                                                                    str53 = "ۛ۬ۚۗۢ۬ۤ۠۫ۖۦ۫ۜۜۡۘۡۛۨۚ۫ۘۨۖۤۛۥۘۘۧ۬ۧ";
                                                                                                    break;
                                                                                                } else {
                                                                                                    str53 = "ۜۨۧۘ۟ۙۦ۟ۚۡۦ۫ۛۨۧۥۘۨۡۡۘ۠ۚۖۘۛۜۡۘۤۗۤ۬۫ۡۚۚۤ۠ۡۛۗۡۘۙۜۧۘ";
                                                                                                    break;
                                                                                                }
                                                                                            case -1441419720:
                                                                                                str53 = "ۗۤۘۘۚۖۚۤۜۧۘۤۗۡۘۦۦۖۘ۟ۧۜۘۡ۫ۙۢۜۦ۫ۢۨۙۢۘۘۢۦۥۘۧۧۦۥۚۥۘۨۖۧۘ";
                                                                                                break;
                                                                                            case -687223224:
                                                                                                str40 = "ۙ۟ۧۙۨۘۘۢۢۛۚۦۙۜۦۛۖۚۛ۬ۤ۟۫۟ۨ۫ۥ۫ۥۛۦۘ۬ۥ۫ۦ۟ۨ۫ۥۦۘۜۧۖۘۢۖۥۡۤ۟ۨۥۘۨۢ";
                                                                                                continue;
                                                                                            case -727299:
                                                                                                str40 = "ۚۚۜۖ۟ۤۨۖۨ۟۬۬ۤ۟ۨۨۧۘۧۦ۫ۡۤۨۘۗۖ۠ۡ۟ۛۙۢ۬ۨۡۤۦۙۚۢ۠۬ۧۧۡۤ۠۬";
                                                                                                continue;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case -215124463:
                                                                        String str54 = "ۗۜۡ۬ۦۥۙۢۛ۠ۜۖۘۤۦۥۨۚ۠ۚۨۜۗۚۜۘۥۤۛۘۚۖۘ۬ۡۦۘۛۗۨۘۡۜۚۜۚۨۤۨۢۛۥ۟";
                                                                        while (true) {
                                                                            switch (str54.hashCode() ^ 225411277) {
                                                                                case -1861521756:
                                                                                    str54 = "ۡۤ۠۬ۦ۠ۦۦ۟ۡۡۤۛۗۦۧۨۘۘۥۧۗۖۧۤۢۨۦۛ۬۟ۜۦۨۡۤۖ۬ۥۗۜۗ";
                                                                                    break;
                                                                                case -1764635943:
                                                                                    if (!jSONObject.has(l2.decrypt("kF+LJA==\n", "8zDvQW5iBRM=\n"))) {
                                                                                        str54 = "ۢ۟ۤۖۧۗۥۙۤۤ۠ۡۘۗۤ۬ۤۧۖۗۤۜۡ۠ۚ۬ۧۖۦۥۘ۬ۛۨۙ۫ۗ۬ۥۧۘ۬ۦۧۖۘۤۧۦۦ";
                                                                                        break;
                                                                                    } else {
                                                                                        str54 = "ۘۖۘۘۡۛۡۘۡۜۤۦۦۡ۟ۥۧۜۦۡۘۗ۟ۥۘۢۗۙۧۦۥۘۘۥۖ";
                                                                                        break;
                                                                                    }
                                                                                case -850614729:
                                                                                    str39 = "۠ۤۘۘۘ۫ۢۜۦۖۙۦۨۘۗۧۘۤ۟ۦۘۛۢۘۚۘۦۘۘ۫ۙۤۙ۠ۘۘۛۘۘۡۘ";
                                                                                    continue;
                                                                                case 917779638:
                                                                                    str39 = "ۤۗۛۦۢ۟ۢۗۧۚۗ۟ۢۡۥۡۜۗۨۧۦ۫ۢ۫ۤۡۨۤ۟۫۬ۛۛ";
                                                                                    continue;
                                                                            }
                                                                        }
                                                                        break;
                                                                }
                                                            }
                                                        } catch (Exception e2) {
                                                            Map map3 = this.h;
                                                            String str55 = "۠ۚ۬ۙۦۙۤۤۨۦۜۥۘۡ۟۫ۛۡۡۨۗۡۤۦۧۗۧۥۙۢۨۘ۬ۙ۬ۢۚۥۜۘ۟ۢۦۥۘۥۜ۬ۨ۠ۦۘۖۜۦۗۡۡ";
                                                            while (true) {
                                                                switch (str55.hashCode() ^ (-1145453211)) {
                                                                    case -1702808178:
                                                                        str55 = "ۘۙۡۘ۟ۥۜۘ۫ۦۖۘۘۡۨۥۧۨۘۙۚۥۘ۟ۢۡۘۛۥۦۢۘۜ۬ۙۨۘۙۛۤۧ۫ۚ۟ۙۚۘۡۘۘ۫ۙۨۙۡۘ";
                                                                        continue;
                                                                    case -452859175:
                                                                        String str56 = "ۗۦۖۘ۟ۘۘۦۡ۠ۚۙۡۘ۫ۡۙۙۙۤۦۗۥۚۦ۠ۛۜۥ۫ۦۙ۟۬ۛ۠ۜۘ۫۫ۧۗۜۚۖۢۜۘۤۙۢۤ۟ۡۚ۟ۛ";
                                                                        while (true) {
                                                                            switch (str56.hashCode() ^ (-224079473)) {
                                                                                case 205560342:
                                                                                    str56 = "۠۫ۡۘۙۤۛ۟ۦۡۘۨۥۤۛۦۥۦۦۡۢ۬ۨۚۡۜۖۗۙۚۗ۫ۜۙۗۗۦۘۨ۬ۦۜ۠ۖۘۚۨۨۘۤۗۗۗۛۘۘۢ۟ۦۘ";
                                                                                    continue;
                                                                                case 1067699909:
                                                                                    break;
                                                                                case 1315576158:
                                                                                    String str57 = "ۛۡۤۧۜۨۘۚۜۘۘ۫۫۬ۦۧۧۙۘۘ۬ۧۙۤ۠ۚۧۧۛۡۘۗ";
                                                                                    while (true) {
                                                                                        switch (str57.hashCode() ^ (-179832423)) {
                                                                                            case -1915056481:
                                                                                                str57 = "ۤۡۖۖۜ۫۠ۖۘۘۤۥۙ۫ۘۡۗۖۘ۠ۡۢۗۡۥۘۚۤۦۘ۬ۧۜۚ۫ۨۘۜۦ۟ۖۨۜ۫ۦ۬";
                                                                                                break;
                                                                                            case -101734776:
                                                                                                str56 = "ۖۙۥۘۢ۬۬ۦۦۙ۬۠ۛ۟ۖۚۡۘۨۘۤۤ۬۬ۜۘۧ۬ۤۜۖۨۜۢۙۤ۫ۤ۠ۗ۫۫ۘۦۘۛۨ۟ۢۤۨۘ۬۫ۜۙۥۡۘ";
                                                                                                continue;
                                                                                            case 588650310:
                                                                                                if (!map3.containsKey(l2.decrypt("t9JnvxRNLtyhmWM=\n", "z/8M3nkkA7U=\n"))) {
                                                                                                    str57 = "ۖۤۡۘ۫ۚۡۘۖ۟ۡۤۡۢ۫ۖۦۨۜۦ۠ۙ۠۬ۢۨۤۘۗۦۡ۫۫ۘ۬ۨۢۢ۫ۙۘۡۥۖۘۥۜۦۨۡۘۘۤۖۡۘ";
                                                                                                    break;
                                                                                                } else {
                                                                                                    str57 = "۫ۡۦۚ۟ۗ۫۬ۧۨۧۜۘۛۛۨۘۘۛۨۢۤۥۧۦۦۚۥۜۘۢۥۡۘۘۚۢۜۜۘ۠۟ۖۘۜۧۨ۟ۙ۬ۜۙ۬ۡۜۡۗ۬ۛ";
                                                                                                    break;
                                                                                                }
                                                                                            case 771338912:
                                                                                                str56 = "ۦۜۧۙۛۖۥۧۦۘۛۖۦۘ۫ۜۖۛۦۘۧ۬ۖۘ۬ۥۘۤۨۧۘ۬ۢ۟ۘۖۦۗ۬ۘ۟ۖ۠ۜۧۦۘۘ۫ۧۦۘۜ";
                                                                                                continue;
                                                                                                continue;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case 1723410496:
                                                                                    k2.logToFloatingWindow(l2.decrypt("qa0ay4oH0wG/5h6Q\n", "0YBxqudu/mg=\n") + ((String) map3.get(l2.decrypt("Y09XKMfmY/B1BFM=\n", "G2I8SaqPTpk=\n"))), l2.decrypt("AbeIxg==\n", "aNnuqZ+Sxho=\n"));
                                                                                    Toast.makeText(activity4, (String) map3.get(l2.decrypt("nLEKZMGAZpCK+g4=\n", "5JxhBazpS/k=\n")), 1).show();
                                                                                    break;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case -402628410:
                                                                        break;
                                                                    case 1673287973:
                                                                        String str58 = "ۗۛۥۘۖۗۜۦۨۦۢۦۘ۟۠۟ۙۗ۟۬ۜۡۖۖۚ۠ۥۖۘ";
                                                                        while (true) {
                                                                            switch (str58.hashCode() ^ 1840954295) {
                                                                                case -1121530320:
                                                                                    if (map3 == null) {
                                                                                        str58 = "ۨۘۜۘۢ۬ۨۘ۠۬ۨ۫ۡ۠۠ۗ۫۟۠۬ۤۦۧۘۨۤ۟ۢۦۤۦۜۢ";
                                                                                        break;
                                                                                    } else {
                                                                                        str58 = "۠ۤۨۘۢ۠ۨ۠ۢۙۗۧۨ۟ۨۧ۟۫ۧۗۡۘ۠ۜۥۘۧۧۧۡۡۨ";
                                                                                        break;
                                                                                    }
                                                                                case 7657789:
                                                                                    str58 = "ۧۤۚۤۚ۟ۧۦۡۦۧۧۜۡۧۘۢۛ۫ۤۘ۫ۛۜۖۛۦۘ۟ۚۜۙۙۘۘ۠ۙۥۜۦۜۙ۠ۥ";
                                                                                    break;
                                                                                case 575241670:
                                                                                    str55 = "۬ۡ۫ۤۙۚۥ۫ۚۥۦ۟ۢۖۘۖۦۧۘۥ۟ۜ۬ۘۨۘ۟ۡ۬۫۠ۥۖ۟ۜۦۡۙۜۧۘ۬ۥ";
                                                                                    continue;
                                                                                case 842434925:
                                                                                    str55 = "ۘۚۗۦ۬۠ۜۡۥۡۘۧۘۙ۫ۨۥۨۚۗۥۘۘۡ۫۠۫۫ۜۛ۬۠۬۠ۨۘ۠ۡۧ";
                                                                                    continue;
                                                                                    continue;
                                                                            }
                                                                        }
                                                                        break;
                                                                }
                                                            }
                                                            String str59 = "ۚۦ۟ۗۙۜۘۦۚۙۜ۫۠ۧۙ۠ۢ۬ۜۘ۫ۖۨۤ۬ۖۘ۫۫۟ۚۦۘ";
                                                            while (true) {
                                                                switch (str59.hashCode() ^ 552529044) {
                                                                    case -1856483238:
                                                                        break;
                                                                    case -210012637:
                                                                        String str60 = "ۚ۟۠۟ۙۥۥ۫ۡۜۢۙ۠ۨۘۘۥ۫ۨۘۗۥ۫ۗۤۥۘۡۢ۬ۥۚۗۢ۠ۦۖۡۘۘۜ۫۬۠ۛۧۙۧۨۥ۫ۡۘ";
                                                                        while (true) {
                                                                            switch (str60.hashCode() ^ (-700847707)) {
                                                                                case -1690072634:
                                                                                    String str61 = "ۖۤۜۘۛۖۦۘۜۡۦۥۗۡۘ۟ۛۘۡۧ۫۬۫۬ۧۙۦۧۖۢۨۚۙ۬۫ۨۘۚۛۡۘۨ۠ۘ۟۟ۧۡۡۢۡ۠ۡۘ";
                                                                                    while (true) {
                                                                                        switch (str61.hashCode() ^ (-1559293298)) {
                                                                                            case -1578999340:
                                                                                                str60 = "ۚۡۘۧۢۤۛۢۨۜۨۥۘۜ۟ۥۛۘۨۘ۬ۤۨۡۘۖ۫۟ۥۘۗۙ۫ۥ۟ۨۘۘۚۘۘۡ۟ۤ۫ۡۤۘۥ۠ۙ۠ۚ۬۠۠۫ۥۢ";
                                                                                                continue;
                                                                                            case -791393475:
                                                                                                if (!l2.decrypt("n9s=\n", "8LAm7GUVjxo=\n").equalsIgnoreCase(str36)) {
                                                                                                    str61 = "ۥۤۤۡ۬ۙۖۢ۫۬ۨ۟۫ۚۦۘۖۘۡۦ۟ۥۘۥۤ۬ۙۢۗۘ۬۟۬ۨۨۘۚۤ۟ۘۖۥۘۛۥۤ۫ۦ۫۟ۖ";
                                                                                                    break;
                                                                                                } else {
                                                                                                    str61 = "ۡۡۨۘۙۥۨۘۛۦۡۡۖۚۙۧۙ۟ۜۤ۬ۜۨۘۛۤ۠ۖۛۢۙۡۦۘ";
                                                                                                    break;
                                                                                                }
                                                                                            case -480800167:
                                                                                                str61 = "ۛۢۢۙۜۨۦۦۧۚۨۘ۬۬ۥۘۚۢۘۚۨۘۙۦۡۨۚۘۘ۫ۙۜۘۖ۬ۥۖ۬ۜۘۚۘۤۜۙۥۘۙۢۖ۠ۥۤ";
                                                                                                break;
                                                                                            case 66389907:
                                                                                                str60 = "ۘۛۤۗۗۜۘ۠ۚۙ۫ۨۙۤ۠ۦ۫ۤۜۘۘۥ۟ۧۜۦۗۘۚۡۜ۟ۛۘۘۥ۫ۙۙۧۥۘۡۘۦۨۢ۠ۛۢۦ";
                                                                                                continue;
                                                                                                continue;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case 1448619076:
                                                                                    sharedPreferences2.edit().putString(str37, str38).apply();
                                                                                    k2.logToFloatingWindow(l2.decrypt("/wVAocKB4ViVYn/EkaWSGqYN\n", "GYrQRXglCPI=\n"), l2.decrypt("LzGKpw==\n", "Rl/syJ2qI6M=\n"));
                                                                                    String str62 = "ۗ۫ۨۘۗ۫۫ۘۘۖۘ۟۬۟ۢۡ۫ۨۘۤۦۦۥۘۢۗ۫ۦ۬ۡۚۖۜ";
                                                                                    while (true) {
                                                                                        switch (str62.hashCode() ^ (-617249931)) {
                                                                                            case -1263886499:
                                                                                                k2.logToFloatingWindow(l2.decrypt("EHrVkkovCNVmGuPeOyNmjklGgdFK\n", "9f9me92C4Gs=\n"), l2.decrypt("s6Fyaw==\n", "2s8UBKPbVNM=\n"));
                                                                                                k2.closedTextPopupIds.add(str37);
                                                                                                String str63 = "ۢۥ۟ۛۜۥۘ۫ۨۦۘۥ۠ۗۢۛۖۘۙۦۘۜۢۦۘ۫ۨۨۡ۟ۛۦۙۥۘۡۡ۟ۛۡۨۘ";
                                                                                                while (true) {
                                                                                                    switch (str63.hashCode() ^ 234913815) {
                                                                                                        case -685218759:
                                                                                                            str63 = "ۚ۠ۢۚۧۨۗۥ۟ۚۥۡۨۦۢ۟ۖۖۘۗۥۦۖ۫۠ۙۨۛۛۚۥۘ۫ۙۘۚۘۨۘ۫ۦۤۜۜ۫";
                                                                                                            break;
                                                                                                        case -369961121:
                                                                                                            String str64 = "ۖ۬ۛۦۖۗۖ۬ۜۘۡ۬ۡۘۥۜۖۘۖۚۜۘۤ۟ۥۗۛۗۡۙۥۘ۫ۧۢ";
                                                                                                            while (true) {
                                                                                                                switch (str64.hashCode() ^ 1851878480) {
                                                                                                                    case -575788035:
                                                                                                                        dialog4.dismiss();
                                                                                                                        return;
                                                                                                                    case 385256953:
                                                                                                                        return;
                                                                                                                    case 860898611:
                                                                                                                        String str65 = "ۦۡۜۥۖۨۧۖۘۥۖ۟ۢۡ۠ۢۛۥۧۥۖۘۢۢۜۘۥۨۥۘ۠۬ۜۥۥۜۤۙۘۘ۟ۤۗ۠";
                                                                                                                        while (true) {
                                                                                                                            switch (str65.hashCode() ^ (-955145641)) {
                                                                                                                                case -677022019:
                                                                                                                                    str65 = "ۥۖۦۘ۫ۤۜۗ۟۠ۦۧۦۜۜۘۚۤۛۜۗۨۨۧۘۙ۟۟۫ۦۗ۠ۢۦ۠ۙۥۘۜۖۧ۠ۨۘۢۗ۠ۘ۟ۚ";
                                                                                                                                    break;
                                                                                                                                case -447481071:
                                                                                                                                    str64 = "ۗۨۖۘۘ۬ۡۨۧۦۨ۫ۥۘۙۦۦۘۦۙۘۘۘۡۢۙۗۡۡۥۡۘۚۤۥۡۛۤ۠۟ۨۘ۠ۚ۠۬ۢۥۢ۠۟ۢۘۙ۟ۦۘۨ۬۬";
                                                                                                                                    continue;
                                                                                                                                case 87177456:
                                                                                                                                    if (!dialog4.isShowing()) {
                                                                                                                                        str65 = "ۨۙۖۜۢۜۘۨ۬ۡۡۧۥۖۦۨۘۥۧۡۘ۫ۤۜۘۛۥۧOۤۙۖۢۚ۬۠ۗۥۤۙۡۘ۟۟ۗ";
                                                                                                                                        break;
                                                                                                                                    } else {
                                                                                                                                        str65 = "ۨۦۦۘۡۛۦۘۤۖۧۤۜۥۙۢۖۘ۫ۙۡۘۛۦ۟ۡۧۖۘۘ۠ۗۢۡ۠";
                                                                                                                                        break;
                                                                                                                                    }
                                                                                                                                case 1395318190:
                                                                                                                                    str64 = "ۚۤۚ۬ۗۥۡۛ۠۬ۙ۠ۡۡۛۘۥۜۘۦ۫ۗۥۘ۠ۥۜۦۘۛ۠ۗۜۛ۟ۖ۫ۘۧۡۨۘۚۧۨۦۢۖۘۖۘۜۘ";
                                                                                                                                    continue;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        break;
                                                                                                                    case 1907801770:
                                                                                                                        str64 = "ۖ۫ۦۥۜۢۡۤۧۚ۫ۧ۟ۤۦۘۦ۫ۡۘۥۛۗۚۡۜ۟ۗۙۧۥۗۚۖۛۢۦۙۖۘۚۤ";
                                                                                                                        break;
                                                                                                                }
                                                                                                            }
                                                                                                            break;
                                                                                                        case 869238245:
                                                                                                            String str66 = "ۢۘۡۘۜۙۜۘۢۧۛۗۛۚۤ۬ۢۧ۫ۦۗۖ۠۬۫۫ۚۖ۠ۛۚۖۧ۫ۨۙۡۚ۫ۖۜۘۨۚ۬ۛۨۜ۬ۦۙ";
                                                                                                            while (true) {
                                                                                                                switch (str66.hashCode() ^ (-776512560)) {
                                                                                                                    case -1758870109:
                                                                                                                        str63 = "۬ۗۘۛۥۙۥۗۧۘۗۚۨۡۥۘۡ۫ۜۘ۬۬ۨۘۧۜ۟ۖۧۥۚۢ۠ۧۗۖۘ۫ۘۗۤۨ۟ۦۡۗۙ۠ۤ۠۠ۛۖۥۘۧۨۥ";
                                                                                                                        continue;
                                                                                                                    case -795039623:
                                                                                                                        if (!activity4.isFinishing()) {
                                                                                                                            str66 = "ۗۨۖۘۛ۟ۖۡۨۨۛۖۜۦۘۥۦ۫ۡۘۜۛۘۘۛۢۥۘۤۢۢ۬۬ۛ";
                                                                                                                            break;
                                                                                                                        } else {
                                                                                                                            str66 = "ۖۢۚۛۚۜۛۨ۬ۛۦۥۘ۠۟۟ۥۥۘۡۡۥۥۦۡۘۢ۬ۖ۫ۘۢ۫۠ۖۘۨۙۨۗ۠ۘۘ۫ۦۖۘ";
                                                                                                                            break;
                                                                                                                        }
                                                                                                                    case 870649072:
                                                                                                                        str66 = "ۨ۟ۗۧۦ۬۬ۢۦۘۜۥۢۘۜۦۙۚ۬ۢ۫ۙۦۛۘ۠ۤ۫ۘۖۘ";
                                                                                                                        break;
                                                                                                                    case 896761974:
                                                                                                                        str63 = "۠ۥۛۜۗۨۘۥۦۥۘۤۥۖۘ۠ۧۨ۬ۤۚۥۘ۬ۨۥۘۢۨۨۚ۠ۢۖۡۧ۬ۚۥۦۘۖ۟ۘۖۡۚ۫ۘۚ۠ۢ۠ۗۡۚۡ";
                                                                                                                        continue;
                                                                                                                }
                                                                                                            }
                                                                                                            break;
                                                                                                        case 1428676115:
                                                                                                            return;
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                            case 779585446:
                                                                                                str62 = "ۤۗۡۜۛۦ۟ۤۙۚۡۜۘ۟۠ۗۤ۟ۗۦۘۡۦۜۘ۬ۚۨۙۤۙۖ۬ۜۘۦۙۢ۠۟ۦۘ۬ۜۦۘۧۗۨۘۜۜۘۖۥۜ۬ۚ";
                                                                                                break;
                                                                                            case 872792361:
                                                                                                return;
                                                                                            case 1836561238:
                                                                                                String str67 = "ۘۛۡۜ۠ۡۘۥۧۘۖۛۖۘ۟۫ۧۜۘۥۜۘۖۘۙۜۚۛۚۗۥ۬ۥۘ";
                                                                                                while (true) {
                                                                                                    switch (str67.hashCode() ^ 995956068) {
                                                                                                        case -1429342020:
                                                                                                            str62 = "ۤۦۙۡۦ۫۫ۦۤ۬ۤۡۛ۫ۖۜۥۦۘۜۛۤۗۘ۟۠ۧۜ۫ۖۥۙۢۘۘۙۘۘ۠۠ۗۨۖۡۘۧۡۦۤۧۡ۟۫ۤۤۖۘۘ";
                                                                                                            continue;
                                                                                                        case -493383056:
                                                                                                            str67 = "ۡۙ۟ۢۘۗ۬ۥ۟ۙ۫ۦۘۗۛۥۘۖۘۡۡۦۛ۫ۜۨۙۡ۟ۢۖۤۢۗ۫ۜۘۤۘ۫ۖۧۨۘ۠۫ۥۙۧ۫";
                                                                                                            break;
                                                                                                        case 648315151:
                                                                                                            if (!z4) {
                                                                                                                str67 = "ۛۗۜۘۡۙۗ۬۬ۜۘ۟ۜۧۜ۫ۜۦۢ۠ۜ۟ۥۥۜۥۘۦۤۦۘۢۚ۬ۡۧۢ۬ۢۥۛۥۜۧۙۚۙۥۥۨ۬ۦۘۘۨۢ۫ۜ۫";
                                                                                                                break;
                                                                                                            } else {
                                                                                                                str67 = "ۖۥ۫ۛۙۘۘ۬ۙۙۜۨۙۖۘ۬ۥۧ۫ۛۙۛ۠۫ۘۦۤۛۤۧۦ۠ۢۘۚۨ";
                                                                                                                break;
                                                                                                            }
                                                                                                        case 1767184927:
                                                                                                            str62 = "۬۬ۢۤۡۧۘۥۚ۟ۡۘۜۘۛۛۤۜۢۚۨۦۧۘۜۨۧۘ۠ۢۥۘ۫ۚۥۢۜۗۗۧۜۤۦ۫ۘ۫ۜۘ۬ۤ۟ۜۙۨۘۛ۬ۨۘ۫۟۫";
                                                                                                            continue;
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case 1984623141:
                                                                                    str60 = "ۢۢ۬ۖۡۦۘ۠ۥۚۘۖۛۤ۬ۢۡۤۖۥۢ۫ۜ۫ۦۘۤ۟ۥۦۚۡۘۨۜۦۘۖۥۦۚۘۙۨۢۙۧۛۜ۠ۢۢ۬ۜۧۗۘۜ";
                                                                                    continue;
                                                                                case 2026025465:
                                                                                    break;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case 692550545:
                                                                        str59 = "ۡۤۚۢۢۚۙۦ۠ۖۧۛ۟۟۬ۦ۠ۖۜۤۥۧۤۦۢۖۥ۬۠ۘ۟۬ۘۦۜۘۖ۬ۚ۟ۦۢ";
                                                                        continue;
                                                                    case 1928764585:
                                                                        String str68 = "۬ۦۦۘۢۘۢۖۦۘۘۨۧۘۚۜۥۢۙ۫ۗۡۥۡۛۧۧ۟ۖۘۤ۬ۖۘ۠ۡۙۚۨۢۦۛ۫ۡۤۥۘۤۦۘ۬ۜۧۘۖۛۧۙۨۖ";
                                                                        while (true) {
                                                                            switch (str68.hashCode() ^ 381645688) {
                                                                                case -63074885:
                                                                                    str59 = "ۜۡۘۘۗۧۘۦۜ۠ۧۧۚۘۢۖۧۚۦۤۨۙۡۚۤ۬ۨۤ۫ۦۚ۠ۘۘۛ۟ۥ۟ۡۛ۠۫ۖ۟ۗۧۜۦۤ";
                                                                                    continue;
                                                                                case 601461060:
                                                                                    if (!this.i) {
                                                                                        str68 = "ۙۜ۫ۤۧۗۘۛ۬ۡ۬ۖۡۦ۬ۥ۠۬ۘۛۙۛۗ۠ۧۖۥۘۗۘۖۘ";
                                                                                        break;
                                                                                    } else {
                                                                                        str68 = "۬ۧۡ۟ۛۥۨۛۧۚ۠ۚۗۙۘۘۧ۟ۛۤۗۡۘ۬۟ۨۡ۟ۜۘۗ۬ۜۛۨۚۥ۬ۥۙ۫۠ۧۡۛ";
                                                                                        break;
                                                                                    }
                                                                                case 2108352595:
                                                                                    str59 = "ۤ۟ۖۦ۬ۜۙۥۜۘۦۚۙۧۢۤۜۛۖۚۤۧۛ۫۬۫ۢۦۤ۟ۥۘ";
                                                                                    continue;
                                                                                    continue;
                                                                                case 2144270716:
                                                                                    str68 = "ۗۢۘۧۥۦۘ۫ۨۦۧ۠ۗ۬ۧۘۖ۬ۗۚۙۗۢۦۘۚۖۦ۠ۡۨۘۨۧۡۘۛۗۗ۟۠ۨۘۘۦۧۧۚۘۧ۬ۡۛۗ۫ۥۗۡ";
                                                                                    break;
                                                                            }
                                                                        }
                                                                        break;
                                                                }
                                                            }
                                                            k2.logToFloatingWindow(h.e("lhBAfYWURjP8d38Y2awFcPAFOCa4Cg==\n", "cJ/QmT8wr5k=\n", new StringBuilder(), str36), l2.decrypt("UZAuBVU=\n", "NOJcaiexSYI=\n"));
                                                            Toast.makeText(activity4, str36, 0).show();
                                                        }
                                                    }
                                                });
                                                return;
                                        }
                                    }
                                    break;
                                case 2095570843:
                                    break;
                            }
                        }
                    } catch (Exception e2) {
                        k2.logToFloatingWindow(h.d("zXgHkzEoLXOpEi/PsQ==\n", "K/eXd4uMyM8=\n", new StringBuilder(), e2), l2.decrypt("bjM2LFw=\n", "C0FEQy5JqUc=\n"));
                        activity3.runOnUiThread(new l(activity3, e2, 4));
                        return;
                    }
                } catch (PackageManager.NameNotFoundException e3) {
                    throw new RuntimeException(e3);
                }
                break;
        }
    }
}
