package core.pro.android.notify;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.os.Build;
import android.os.Handler;
import android.os.Looper;
import android.view.Window;
import gTBLD.dev.XSSTG.free.JsInterface;
import gTBLD.dev.XSSTG.free.Utils;
import j$.util.Collection$EL;
import j$.util.Objects;
import java.io.Serializable;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;

/* loaded from: /workspace/unpacked/classes2.dex */
public final /* synthetic */ class b0 implements Runnable {
    public final int a;
    public final boolean b;
    public final Object c;
    public final Object d;
    public final Object e;
    public final Object f;

    public /* synthetic */ b0(Activity activity, boolean z, AlertDialog alertDialog, String str, int[] iArr) {
        this.a = 2;
        this.c = activity;
        this.b = z;
        this.e = alertDialog;
        this.d = str;
        this.f = iArr;
    }

    public /* synthetic */ b0(Object obj, Object obj2, boolean z, Serializable serializable, Object obj3, int i) {
        this.a = i;
        this.c = obj;
        this.d = obj2;
        this.b = z;
        this.e = serializable;
        this.f = obj3;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:107:0x0202. Please report as an issue. */
    @Override // java.lang.Runnable
    public final void run() throws NoSuchFieldException, InterruptedException, SecurityException, UnknownHostException {
        final String strReplace;
        boolean z;
        switch (this.a) {
            case 0:
                JsInterface.h((JsInterface) this.c, (String) this.d, this.b, (String) this.e, (String) this.f);
                return;
            case 1:
                boolean z2 = v0.a;
                while (true) {
                    final CountDownLatch countDownLatch = new CountDownLatch(1);
                    ArrayList arrayList = new ArrayList();
                    k2.logToFloatingWindow("开始并发请求所有接口", "info");
                    Iterator it = ((ArrayList) this.c).iterator();
                    while (true) {
                        boolean zHasNext = it.hasNext();
                        final Context context = (Context) this.d;
                        String str = "ۥ۫ۖۤ۫ۦۧۤۜۡۥۨۘۙۡۜۨ۫ۜۘۖۙۢۚ۟۠ۚۘۡۘ۟ۧ";
                        while (true) {
                            switch (str.hashCode() ^ 1393356218) {
                                case -150691964:
                                    str = "ۜۤ۟ۛۧۥۘۛۡۧ۬ۢۘۦۤۜۧۧۛۙۥۛۦۨۘۜۡۜۛ۠ۥ";
                                    break;
                                case 201370902:
                                    try {
                                        countDownLatch.await();
                                    } catch (InterruptedException e) {
                                    }
                                    String str2 = "ۙۜۖۗۚۤۜۘۨۘۧۥۜۧۦ۬ۗۡۦۘۢ۠ۘ۬ۨۚۡۛۘۧۡۨۘۘۨ۟ۚۗۧۦ۟ۥۚ۬ۛۦۧۘۗۥۤ";
                                    while (true) {
                                        switch (str2.hashCode() ^ (-1752393305)) {
                                            case -13046861:
                                                k2.logToFloatingWindow("所有请求失败，1 秒后重试", "warn");
                                                String strDecrypt = "所有请求失败，5秒后重试";
                                                String str3 = "۬ۥۜ۬۠ۗۘۦۧۘ۬ۙۤۦۘۧۛۖ۠ۨۡۤ۠ۡۘۧ۟ۗۦۨۘۤۦۘۘ۫۟۟ۙۥۢ۠ۖ";
                                                while (true) {
                                                    switch (str3.hashCode() ^ (-617963706)) {
                                                        case -2021312285:
                                                            new Handler(Looper.getMainLooper()).post(new q0(6, context, strDecrypt));
                                                            break;
                                                        case -267005272:
                                                            str3 = "ۥۡۦۤۨۖۤۦۚۥۚۧۨۤ۫ۢ۟ۖۧۖۨۚۧۚ۟ۗۗۤۥۘ۟ۧۙ۬ۙۨۘۧۥۧۙۙۘ";
                                                        case -228094152:
                                                            break;
                                                        case 1528029288:
                                                            String str4 = "ۗۘ۠ۙۧۖۘۨۗۜۜۤ۬ۗۢۥۘۢۙۡۙۡ۫ۨۨۖۘ۟۫ۥۘۚۥ۟ۚ۠۠ۘۘۧۨ۟۫ۨۙۡۦۘۗۚۗۘۘۢۖۢ۠۟ۤ";
                                                            while (true) {
                                                                switch (str4.hashCode() ^ (-597671148)) {
                                                                    case -2007312220:
                                                                        str3 = "ۧۦۙۧۧۧۦۦۥۜ۬ۙۚۨۚۥ۫ۦ۟ۙۗ۟ۙۥۚۚۡۘۖۤۦ";
                                                                        break;
                                                                    case -848367527:
                                                                        str4 = "ۜۛۜ۫ۗۥ۬ۤۜۙۛۜۘۧۚۦۖۘۦۡۢۦۘۘۢۙۖۡۧۘۘۧۗۘۢۥ۫ۖۧۘۧۤۥۢۙۜۨۤۨۘۤۤۡۘ";
                                                                    case 384119420:
                                                                        str3 = "ۘۢۨۘۚۥۨۘۚۚۗ۬ۦ۟ۧ۠۠۟ۗۙ۠ۤۖۘۜۦۚ۫ۨۤۘۖۚۥۧۨۙۚۢۦ۫ۛ۟ۥۘۖۥۛ۬ۗۨۘ۬۟ۘۘۚۦۧۘ";
                                                                        break;
                                                                    case 617849422:
                                                                        str4 = v0.isDebug() ? "ۡ۫ۢۥۜۢ۟ۨ۠ۤۗۜۢۨۦۚۖۡۘ۟ۘ۟۫ۦۗۖ۠ۦۧۧۨۦۜۨۗۤۗۖۧۜۗۘۘ۟ۦۤۦۙۗۙۦۜۡ" : "ۚۡۖۘۧۧۖ۬ۖۡۘۧ۠ۨۘۚۨ۬ۤۚۜۘۡ۠ۧ۫ۙۧ۬ۙۡۘ۬ۗۛۚ۫ۦۤۖ۠ۗ۫ۧۧ۟ۜۚۢۦ۠ۖۥۚ۬ۚۥۢ";
                                                                }
                                                            }
                                                            break;
                                                    }
                                                }
                                                try {
                                                    Thread.sleep(1000L);
                                                } catch (InterruptedException e2) {
                                                }
                                                break;
                                            case 61209049:
                                                str2 = "ۧۥ۠ۡ۟ۢ۠ۛۜۘۖۢۡۖۛۨۘ۬۫ۖۦۢۢۥۛۨۨۤۖۨۗ۠ۧۖۙ۫ۦۘ";
                                                break;
                                            case 850177691:
                                                return;
                                            case 1236435665:
                                                String str5 = "ۦ۫ۜۘۗۚ۠۠ۗۤۚۘۘۡ۬۟ۙۦۖۤ۬ۘۘۖۢۚۗۦۥۘۡۙۨۘۤۦ۫ۨۥۡۨ۫ۥۘۨۡۥۘۙۤ۟ۖ۠ۥۘۘۢۛۥۙۚ";
                                                while (true) {
                                                    switch (str5.hashCode() ^ (-1344677844)) {
                                                        case -1514781957:
                                                            str5 = Collection$EL.stream(arrayList).anyMatch(new Object()) ? "۠۟۫ۤۧ۬ۙۤۧۖ۬ۦۡۚۦۤۚۤ۟ۘۥۨۡۘۚۡۧۘۤۘۜۢ۟ۛۚۨۥۘۘۨۡۘۤ۬ۨ۠ۚۦۘۢ۬ۡ۠ۗ۟۬۟ۨ" : "ۙۖۤۜۖۛ۟ۨۡۖۛۨ۫ۛۢۖۚۚۙۧۖ۫ۡۧۜۦ۫ۖۢ۫";
                                                        case -949189601:
                                                            str5 = "۫ۜۚۡۚۚۢ۟ۘۥۛۖۘۢ۬ۘۘ۬ۨۙۤۤۖ۠۠ۦ۠ۢۜۘۡ۫ۛ۬ۗۖ۫ۗۗ۬ۚۙۖۚۧ";
                                                        case -909450436:
                                                            str2 = "ۙۙ۠ۦۙۨۗ۫ۤ۟ۦۥۘۤۘۥۤۢۧۜۨۘۥۡۧۘۜ۠ۖۘۥۘۙ";
                                                            break;
                                                        case -810656689:
                                                            str2 = "۠ۖۥۘ۫۟۠ۘۙۧۧۡۥۘۥۛۗ۫ۢۘۥۧۛ۫۠ۦۖۦ۬ۤۜۗۛۨۤۚۛۖۘۚۛۖۙۡۜۢۢۦۢۚۥۘۥۘ۫ۛ۬ۤ";
                                                            break;
                                                    }
                                                }
                                                break;
                                        }
                                    }
                                    break;
                                case 825762546:
                                    String str6 = "ۥ۟۟ۜ۟ۖۚۥ۫ۢۛ۫ۖۜۡ۫ۦۥۘ۟۠ۧۜ۬ۤۡۚ۬۠۟ۡۙۚ۬ۛۙۘ۬ۙ۬ۜۘۡۘ۫۫ۦ۟ۜۘۚۜۚۡ";
                                    while (true) {
                                        switch (str6.hashCode() ^ (-1036839487)) {
                                            case -2141873801:
                                                str6 = "ۗۤۡۘۘ۟ۨۡۗ۠ۗۖۧۘ۬۬ۥ۬ۢ۬ۖۡۡۥۚ۠ۜۤۡ۬ۥۘ";
                                            case -2044446598:
                                                str6 = zHasNext ? "۫۬ۨۙۘۦۡۛ۬ۧ۬ۜۘ۠۬ۡۘۚۢ۬۫۫ۧ۠ۤۦۘ۟۟۬۠ۥۡۜۚۗۜۧۥۚۖۥۛۘۙۥ۬ۢۡۢ۟" : "ۚۛۡۘۢۨۤ۠ۦۤۥۗۖۘ۫ۖۖۘۡ۬ۥۘۡۙ۬۠ۦۢۢ۫۠۫ۨۙۛۤ۠ۚ۟۟ۨۢۡۢۛۘۦۦۨۘۚ۟۠";
                                            case 133322925:
                                                str = "۬ۢۜۧۤۡ۟۫ۥ۠ۢ۟ۢۧۥۢ۟ۘۘۜۜۧۘۦۦۢۙۚ۠۟۫ۥ۠ۘۧۢۘۤۧۤۦۘۜۛۡۨۨۥۘۗ۫ۗ";
                                                break;
                                            case 903157512:
                                                str = "ۙۜۗۦۛۜۘ۟ۧۜۜۚۛۖۖۦۖ۫ۖۘۗۗۦۘۚۖۥۘۛۦۚۨۗۚۚۖۥۢۤ۟ۘۥۡۘۡۤۡۘ";
                                                break;
                                        }
                                    }
                                    break;
                                case 1890835902:
                                    String str7 = (String) it.next();
                                    String str8 = "ۦ۟ۘۚ۫ۖۘۖۥۘۖۢ۠ۜ۫۠ۨۜ۬ۦۘۖۥۜۘۘ۫ۗۙۢۡ۫ۢ۟ۛ۫ۤ۬ۥۡۤ۬۟۬ۢۛۡۜۚۢ";
                                    while (true) {
                                        switch (str8.hashCode() ^ (-378339972)) {
                                            case 126027329:
                                                strReplace = str7;
                                                break;
                                            case 750411599:
                                                str8 = "ۙۙۢۛۤۛۢۜۗ۬ۥۦۘۗۖۚۦۛۜۜۚۤۗۢۦۘۗ۫ۖۘ۟ۛۛۙۡۤ۠ۥۥ";
                                            case 791007826:
                                                strReplace = str7.replace("*", Utils.generateRandomString());
                                                break;
                                            case 1727279684:
                                                String str9 = "ۘۗ۫ۖ۠ۖۥۘۚۜۙۛۥۛۢۧۖۡۘۗۜۧۚۡۜۘ۠ۨۥۗ۬۬ۦ۬۟ۤۧۧۡۢۗۗۗۦ۫ۙۨۧ۫ۧۢۤۖۡۘ";
                                                while (true) {
                                                    switch (str9.hashCode() ^ 971812428) {
                                                        case -1983913738:
                                                            str9 = "۠ۖ۟ۖۚۧۢ۟ۥۘ۫۬۟ۨۙۜۘۢۘ۬ۚۖ۫ۨۘ۠ۨۖۘۖ۟ۖۧۢۖۙ۫ۦۛۛۡۖۡۘ";
                                                        case 148367979:
                                                            str8 = "۬ۦۥ۟ۢ۫ۢۦۘۘۗۨۘۢۥۨۘۦۢۨ۫ۢۧۖۦۘۨ۫۠ۥۜۦۘۙۨۖۘۙۛۨۘ۟ۛۙۦ۠ۖ";
                                                            break;
                                                        case 945497874:
                                                            str8 = "ۖۡۜۘۡۗۚۢۡۛۤۦۥۘ۬ۙۛۚۨۧۜۨۜۘۛۘۡۘۢۨ۬۟ۧۦ";
                                                            break;
                                                        case 1179703695:
                                                            str9 = str7.contains("*") ? "۬ۥۘۘۨۖۥۦۡۦۨۢۗۢ۠ۡۘۡۥۡۗۢۜۘ۟ۘۨۗۜۨۘۨۤۛ" : "ۙ۟ۘۘۛ۬ۥۘۡۜۦۘۨۦۜۙۖۧۙۜۖۘۥ۬ۧۜۜۦۘ۬ۤۗۡ۫ۦ";
                                                    }
                                                }
                                                break;
                                        }
                                    }
                                    final String hostAddress = "";
                                    try {
                                        InetAddress[] allByName = InetAddress.getAllByName(new URL(strReplace).getHost());
                                        String str10 = "ۤ۬۬ۤۤۜۘۤۢ۫ۨۧۘۘۤۛۙ۬۫۫ۜۦۘ۟ۨۧۘۖۗۧۦۚۚۖ۟ۥۦۛۢ";
                                        while (true) {
                                            switch (str10.hashCode() ^ (-1033065591)) {
                                                case -1234655958:
                                                    String str11 = "۬ۨ۠ۤ۫ۥ۟۠ۗۢۙۙۥۥ۠ۛۗ۟ۢ۟ۤۜۤ۠ۛ۬ۚۦۡ۬ۡۥۙۨۛۗ۬ۗۨۦ۠ۜۖ۬۫ۗۡۜ۫ۜۧۤۤ";
                                                    while (true) {
                                                        switch (str11.hashCode() ^ 1899227637) {
                                                            case -1932748423:
                                                                str10 = "ۥ۠ۘ۠ۘۥۘۢۨۢۢۥۦۢۜ۠ۜۘۜۘۦۥۦۡۢ۫ۖۖۜۨ۬ۜۘۗۖۤ۠۬۟ۖۘۗۙۖۘ";
                                                                break;
                                                            case -1632314781:
                                                                str11 = "۫۟ۢ۠ۗۨ۠ۢۥۦۘۚ۫ۗۡۘۘۢۥ۫ۤۡۤ۟ۤ۠ۚۘۦ۠ۗ۬ۡۖ۟ۗۡۛۦۤ۫ۚۘۘۨۨۘۥۧۧۗۨۦۤ";
                                                            case -1069696877:
                                                                str10 = "ۢۨۨۨۥۧۘۥۨۚۜ۟ۥ۬۫ۥۘ۟۫ۜۡ۟ۨۧ۟ۘ۟۠ۥۖۡۛۥ۟ۘۘۜۚ۟ۧۖۧۚۧۡ";
                                                                break;
                                                            case 1058889302:
                                                                str11 = allByName.length > 0 ? "۠ۜۥۘۧۧۥۖۥۘۘۛۢۜۘۢ۫ۨ۠ۡۤۡ۟۠۫۟۠ۛۥۧۘۧۚۖۘۚ۫ۗ۫ۥۨۘۢ۫ۗۚۤۤ" : "۠۠ۤۥۤۧ۟ۢۡۜۦۖۘۖۙۙۨۡۨۚ۠ۦۘۙۗ۠ۤ۫ۘۧۛ۠";
                                                        }
                                                    }
                                                    break;
                                                case -882143572:
                                                    hostAddress = allByName[0].getHostAddress();
                                                    k2.logToFloatingWindow("系统DNS解析IP：" + hostAddress, "info");
                                                    break;
                                                case -217377513:
                                                    str10 = "ۡ۠۟ۢۙۥ۫ۖۖۘۢۥ۬۫ۚ۟ۧۖۧۘ۫۟ۛ۠ۗۘ۟ۗۥ۫۟";
                                                case 1101385111:
                                                    break;
                                            }
                                        }
                                    } catch (Exception e3) {
                                        k2.logToFloatingWindow(h.d("97t0BqsmfPlD4GhC9ieoUrS5J1W1VoQt\n", "EAjP4RC5OLc=\n", new StringBuilder(), e3), "warning");
                                    }
                                    final boolean z3 = this.b;
                                    final String[] strArr = (String[]) this.e;
                                    arrayList.add(((ExecutorService) this.f).submit(new Callable(context, hostAddress, strReplace, countDownLatch, z3, strArr) { // from class: core.pro.android.notify.t0
                                        public final Context a;
                                        public final String b;
                                        public final String c;
                                        public final CountDownLatch d;
                                        public final boolean e;
                                        public final String[] f;

                                        {
                                            this.a = context;
                                            this.b = hostAddress;
                                            this.c = strReplace;
                                            this.d = countDownLatch;
                                            this.e = z3;
                                            this.f = strArr;
                                        }

                                        @Override // java.util.concurrent.Callable
                                        public final Object call() {
                                            CountDownLatch countDownLatch2 = null;
                                            String str12 = "ۨ۫ۡۚۜۘۘ۟ۛۦۘۡۡۙۢۤ۬۟ۥۥ۬ۢ۠ۚۖۧۘۢ۫ۖۥۚۦۘۥۡ۫ۜۢۖۘۢۡۜۘۘۥۙۡۛۜۥۘ۠۠۠ۥۘۥۚۖۘ";
                                            while (true) {
                                                switch ((((str12.hashCode() ^ 28) ^ 498) ^ 605) ^ 611518013) {
                                                    case 1143534398:
                                                        countDownLatch2 = this.d;
                                                        str12 = "ۡۤ۠ۧ۠ۨۘۧۥۦ۫ۗ۬ۘ۫ۥۢ۬ۙۘۗ۫ۤۚۤ۬۟۠ۥۗۢۧۦۨۘۨ۬ۜۧۨۖ۫۠ۜۢۦ۟ۜۥۙۜۛۦۛۥۘ";
                                                        break;
                                                    case 1499707769:
                                                        return v0.a(this.a, this.b, this.c, countDownLatch2, this.e, this.f);
                                                    case 1786053506:
                                                        str12 = "ۦۧ۫۠ۗۦۘ۠ۚۡۘۢۛۧۡۘۡۧ۟۬ۦۙۘۧ۬ۧ۠ۤۛۜۧۘۤۡۦۘ۠ۜۨۘۨۨۗۦۧۖۘۚۖۨۘۦ۫۟ۢۦۛ۟ۥۗ";
                                                        break;
                                                }
                                            }
                                        }
                                    }));
                                    break;
                            }
                        }
                    }
                }
                break;
            default:
                Set<String> set = k2.closedPopupIds;
                Activity activity = (Activity) this.c;
                String str12 = "ۨ۠۟ۧۦۛۚۗۥۘۨۖۖۨۡۘ۟ۜۥۤۢۦۧۙۦۨۜۖۘ۠ۘۖ۠ۡۧ۠۫ۙۦۘۚۚۡۙۦۖۥۦۤۚ۟ۖۘۤۥۚ";
                while (true) {
                    switch (str12.hashCode() ^ (-1794974033)) {
                        case -928272520:
                            String str13 = "ۨۖۧۡ۟ۦۘۦۧۤۦۛۗۚۧۗۜۜۖۘۜ۠ۖ۬۫ۦۚۛۨۦۜۥۘۡۧ۟ۨۛ";
                            while (true) {
                                switch (str13.hashCode() ^ 1668970736) {
                                    case -1262349899:
                                        str13 = "ۧۘۥۙۛۨۘۢۖۨۘۘۙۛۧۧۙۤۢۡۨۜۢۥۙۡۘ۟ۡۖ۫ۚۦ۟ۦۦۢ۠ۜۛۤۖۘۚۗۧۥۡۙ";
                                        continue;
                                    case -771404849:
                                        k2.logToFloatingWindow("activit可用,弹出系统风格窗", "warn");
                                        String str14 = "۟ۗۨۘۖۢۢ۬۟ۥۘۚ۫ۡۨۛۨۢۖۜۛ۬ۜ۟ۤۥۘ۟ۢۡۥۤۜۘۨۢۜ۟ۘۘ";
                                        while (true) {
                                            switch (str14.hashCode() ^ (-1604972360)) {
                                                case -1953337916:
                                                    z = false;
                                                    break;
                                                case -1747078346:
                                                    z = this.b;
                                                    break;
                                                case -829104213:
                                                    str14 = "ۜۧۜۘۗۛۢ۫ۛۜۘ۬ۦ۠ۚۙۜۘۗ۟ۗۚۢ۠ۘۚۙۜۗۘۘۙۜۥۘۢ۫ۧ۬۟ۖۘۧۤ۬۠ۙۢ۟ۨۡۘۨۛۛ";
                                                    break;
                                                case 257831962:
                                                    String str15 = "ۙ۫ۛۤۢۘۘۚ۫ۡۚۛۥۥۜۥۦۦۘۡۘۛۚۙۖۘۦۤۚۙۥۙ۬ۘۚ۬ۖۜۘ";
                                                    while (true) {
                                                        switch (str15.hashCode() ^ 605230382) {
                                                            case -1927360768:
                                                                str15 = "ۥۘۢ۬۬ۜۨۚۜۘۤ۬ۦۚۙۙۗۤۦۘۘۤۚۗۦۧۦۡ۫ۧۡ۬ۧۗۜۘۥۧۥۧۡۥۜۧۤ";
                                                                break;
                                                            case 1361077955:
                                                                str14 = "ۙۙۛۘ۫ۘۗۥۚۦۦۖۘۙۛۡ۟ۨۧۘۖۥۧۨۡۙ۟۫ۨۘۛ۫ۡۖ۫ۜۘۙۦۤ";
                                                                continue;
                                                            case 1853572717:
                                                                if (Build.VERSION.SDK_INT >= 24) {
                                                                    str15 = "ۥۘۖۛ۟ۥۢۥۙۗۦۡۘۦۖۘۦۚۡۘۚۧۛۚۜۘۜۢۛۧ۟ۦۘۧۜۘۖۖۘ۠۬۫۬۠ۨۚۨ۬ۙۢۨ";
                                                                    break;
                                                                } else {
                                                                    str15 = "ۜ۟ۘۤۥۘۡۥۚۨۚۘۜۥۦۨۖۜۧۡۗۘۜۜۘۥۦۢۙۜۤ۠۫ۘۘ۠۫ۗۖۥۨ۟ۤۡۘ";
                                                                    break;
                                                                }
                                                            case 1893391533:
                                                                str14 = "۫ۚۨۘۘۜۤۨۙ۫۠۠ۜۘۡۖۦۗۤۙ۬ۢۦ۟ۚۧ۬ۘۘۛ۬ۤ";
                                                                continue;
                                                        }
                                                    }
                                                    break;
                                            }
                                        }
                                        AlertDialog alertDialog = (AlertDialog) this.e;
                                        int[] iArr = (int[]) this.f;
                                        String str16 = (String) this.d;
                                        String str17 = "ۨۤۜۧۦۙۨ۫ۥۥۗۘۨۗۤۚۡۧۘۧۧۥۘۛۡۦۡ۬ۥۘۥۖۦ";
                                        while (true) {
                                            switch (str17.hashCode() ^ (-1090727476)) {
                                                case -568346170:
                                                    Utils.monitorDialog(alertDialog);
                                                    new x0(alertDialog, activity).show(str16);
                                                    alertDialog.getWindow().getDecorView().post(new l(alertDialog, iArr, 3));
                                                    return;
                                                case -375634910:
                                                    str17 = "ۙ۠ۜۘۤ۬۬ۤۨۜۘۙۚ۫ۙ۫۟ۦۤۗۨۚۨۘۛۤۜ۫ۤۜۨۘۙۖۖۨۜ۫۟";
                                                    break;
                                                case 268044113:
                                                    alertDialog.show();
                                                    k2.confirmPopupShown(alertDialog);
                                                    Utils.a(alertDialog, iArr);
                                                    try {
                                                        Window window = alertDialog.getWindow();
                                                        Objects.requireNonNull(window);
                                                        window.getDecorView().setTag("shell_protected" + str16);
                                                        k2.confirmPopupShown(str16, alertDialog);
                                                        return;
                                                    } catch (Exception e4) {
                                                        throw new RuntimeException(e4);
                                                    }
                                                case 1217345301:
                                                    String str18 = "ۚۤۨۘۛۜۛۛۘۘۢ۟ۛۨۖۢۧ۬ۡ۟ۡۥۘۙۨۦۘۡ۫ۙۤ۟ۧۘۚ۬۠ۜۥۢۙۡۦ۬ۦۙۦۘۛۦۧ";
                                                    while (true) {
                                                        switch (str18.hashCode() ^ (-177298304)) {
                                                            case -1694175275:
                                                                str18 = "ۚۡۜۢ۟ۖۘۧ۠ۛۧۗۨۘۜۤۨۘ۬۫ۡۘۥۤۦۖۦۘ۟ۛۘۘۙ۟ۜۘۧۘۦۘۢ۟۟ۜۛۚۤۢۡ۫ۥۦۛۗۧۥۚۘۘۨۤۨ";
                                                                break;
                                                            case -1678575436:
                                                                str17 = "ۖۙ۠ۗۙۛۦۖۜۛۚۗۥ۠ۨۘۚۜۥۘۚ۫ۚۡۜۜۘۗۤۘ۫ۖۛ";
                                                                continue;
                                                            case -1637950964:
                                                                str17 = "ۡۦۖۘۙۚۦۘۥۦۦۘۛۗۥۘۗۜۦۘۧ۟ۥۘۡ۬ۥۘۤۛ۟ۦ۠ۢۤۛۘۥ۠ۛۧ۫۬ۤۙۦۘۢ۟ۘۛۘۡۦۘۗ۟ۡۜۦۢ";
                                                                continue;
                                                            case -1231651292:
                                                                if (!z) {
                                                                    str18 = "ۨۢۢۤۘۥۘۤ۠۟ۖۡۦۖۢۜۦ۫ۥۤۤۡۦۚۗۘۚۘۙۦۚۥۧۧۥۙۘۢ۟ۘۚۖۥۘ۫ۜۖۨۗۨۛۡۨۖۗ";
                                                                    break;
                                                                } else {
                                                                    str18 = "ۤۦۖۘۦۥۛۧۥۥ۟ۨ۬۬ۧۨۘۘۚۧ۫۫ۙۗ۠ۢ۬ۖۡۧۡ۬ۛۖۛ۬ۢ";
                                                                    break;
                                                                }
                                                        }
                                                    }
                                                    break;
                                            }
                                        }
                                        break;
                                    case -236744260:
                                        break;
                                    case 1943725861:
                                        String str19 = "ۚۜۨ۟ۧۜۖۖ۟ۛۡۙ۬ۧۘۘۧۗ۫ۤۜۘۘ۬ۜۖۘۙۗۜۘ۠ۛۘۘۦۥۖۘۡ۫ۨۘۨۡۦۘۖۚ۫ۜ۬ۜ۟ۢۗ";
                                        while (true) {
                                            switch (str19.hashCode() ^ 1960058487) {
                                                case -2087980485:
                                                    if (!activity.isDestroyed()) {
                                                        str19 = "۠ۖۨ۬۬ۦۘۡ۬ۦۘۧۘۘۨۨ۟ۛۧۥ۟ۥۡۨۘۜۘۗۗۥۘۖ۠";
                                                        break;
                                                    } else {
                                                        str19 = "ۙۛۗۚۘۡۜۘۙۘ۠ۘۨۗۡۘ۫ۤۤ۠ۖۖۘۦۛۜۨ۟۟ۨۧ۬ۧۦۡۘۗۙۡۘۤۜ۠۟۟۟";
                                                        break;
                                                    }
                                                case -1646901631:
                                                    str13 = "ۧۡۦ۬ۦۘۛۦۙ۫ۤۚ۠ۢۡۘۘۘۤ۫۟۟ۗۤۤ۬ۨ۠ۥ۠ۙۥۘۚۛ۬";
                                                    continue;
                                                case -128325012:
                                                    str13 = "ۗ۫ۜۖۜ۠ۛۧ۬ۥ۠ۘۚۖ۠ۙۚ۠ۦۥۚۗ۠ۡۘۦ۫ۤۨۧۨ۠ۘۥۘ۟۬ۖۘۢۥۡۖۢۨۘ";
                                                    continue;
                                                    continue;
                                                case 1523093843:
                                                    str19 = "ۧۚۜۥۨۘۙ۬۫ۧۚۜۘۗۛۨۖۧۧۨۗ۬ۙۡۘ۫ۙۤۢۢۗ۬۫ۙۡ۬ۛۖۢ۠ۜۦۢ";
                                                    break;
                                            }
                                        }
                                        break;
                                }
                            }
                            break;
                        case -203706983:
                            break;
                        case -197339008:
                            str12 = "۟ۗۥۧۗۙ۠ۜۡۜۛۖۘ۬ۥۢOۥۢۤ۬ۡۦۗ۠۬۫۬ۘۖ۫ۘۥۚۖۘۡۗ۟ۗۘ۟ۦۜ۠۟ۨۚ۫ۧۥۧۥۖ";
                            continue;
                        case 1072178862:
                            String str20 = "۠ۦۜۨۥۖۦۨۡۘۘۥۨۘ۠۠ۖۘۥ۠ۚۥۧۘۡۙۗۨۦۘۙۗ۫ۤۘۘۙۥۜۘ";
                            while (true) {
                                switch (str20.hashCode() ^ 322631788) {
                                    case -352232992:
                                        str20 = "۟ۡۖۘۧۘۢۖۨۡۘۤۛ۬ۚۥ۬۬ۡۜۧۥۗۖ۫۟ۦۤ۫ۜۦۘۧۖۡۦۛۘۧۧۡۘۤۘۨۘۢۨۘۢ۟ۨ";
                                        break;
                                    case 196342304:
                                        if (!activity.isFinishing()) {
                                            str20 = "ۦۧۨۙۡۘۘۢۛۗ۫۫ۡۧۧۚۥ۟ۥۡ۠۬ۨ۬۫ۡۡۚ۫ۚۘ";
                                            break;
                                        } else {
                                            str20 = "ۢۘۨۘ۫ۗۜۚۖ۠ۚۡۥۨۚۤۥۧۖ۬ۦۥ۠ۦۜۘۧ۠ۡۢۖۛ۫ۖۤۘۖۘۙ۟۟ۨۜۦۢۖۦۗۥۢۖ۠ۦۦۧۥۘ";
                                            break;
                                        }
                                    case 1726877762:
                                        str12 = "ۗۥۨ۬ۙۡۖۖۜۥۖۤۚۧۛ۬ۚۖۘۥۧۦۛۜۘۤۧۧۦۗ۫";
                                        continue;
                                    case 2124594960:
                                        str12 = "۫ۚۛۘۨۧۡۗۦۙۡۤۘۢ۬ۡۧۡۙۜۧۘۜۛۖۘۦۗۥ۬ۥۦۜۨ۟ۛۡۜ۟ۖۜۧۧۦ۬ۜۙۖ";
                                        continue;
                                        continue;
                                }
                            }
                            break;
                    }
                }
                k2.logToFloatingWindow("activit已不可用,未弹出系统风格窗", "warn");
                return;
        }
    }
}
