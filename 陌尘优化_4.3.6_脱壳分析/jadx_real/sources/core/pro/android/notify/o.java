package core.pro.android.notify;

import android.app.Application;
import android.content.Context;
import android.view.View;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class o {
    public static volatile boolean a;

    public static void a(View view, int i) {
        int i2 = 0;
        while (true) {
            String str = "۠۠ۖۘۥۥۦۘۦۤۡۜ۠۬ۙۚۡۘۤۡۘۘۦۥۚۤۘۘۧۦۘۙۨۥۘۙۘۡۘ۠ۢۘۨۥ۫ۗۛ۫";
            while (true) {
                switch (str.hashCode() ^ 183424119) {
                    case -653557025:
                        str = "ۡۗۖۘ۫۫ۘۘۜ۫۫ۜۜۘۛۤۤۥۧۖۥۢۙۗۦۜ۬ۘۖۘۘۖۖ";
                    case -43595422:
                        String str2 = "ۧۗۘۗۡۨ۫ۡۦۢۧۘۙۡۜۘۚۘۛۥۘۦۘ۟ۛۡ۬ۘۦۘۡ۠ۤۤۜۘۨۢ۫ۦۡۨۘۤۙۡ۬ۨۦۖ۠";
                        while (true) {
                            switch (str2.hashCode() ^ 296103153) {
                                case -1848187002:
                                    str = "ۦ۫ۜۦۨۙۦۖۦ۫۠ۘۨ۬ۚۚۜۧ۫۫ۘۘ۬۠۫ۧۙۜۢ۬ۨۚ۬ۜۘۤۗۗۡۨۘۖۗۗۖۡۘۨۛۜ";
                                    break;
                                case -754988691:
                                    str2 = "ۡۡۚۧۦۛۖۧۘۘۢۖۛۜ۫ۘۗ۫ۚ۟ۙۖۘۢۤۧۗ۟ۜۘۚۜۡ۬ۧۥۘۗۡۜۘ";
                                case 646627962:
                                    str2 = i2 < i ? "۠ۢۦ۫ۤۥۘۙۢۜۜ۠ۡ۠ۗۨۘۨ۟ۦۘۦ۬ۜۘ۠۠ۦۗۚۥۦۜۗۚۖۙۧۙۘۘۛ۬ۧ۫ۡۢۛۥۜۘ۬ۖۘ" : "ۘۥۤۚ۟ۗۜۦۙۤ۫ۡۚۨۧۛۛۜۘ۫۠ۢۨۛ۟ۧۗۦۘۢۢۧۢ۟ۥۘۨۗۤ۠ۡۨۘ۠ۤۘۘۢۜ۬ۙۛ۬ۧ۠ۜۘ۟ۗۥ";
                                case 1455356908:
                                    str = "ۛۡۥۥۥۢۦۢۨۘۦۨۘۘۨۤۢۢۙۚ۫ۧ۬۟۠۟ۦۥۙۗۨۜۘ۬ۥۥۤۦۧ۠ۖۨۢۙۢۙ۠۠ۡۙ۬ۛۖۜۧۚ";
                                    break;
                            }
                        }
                        break;
                    case 1254568859:
                        String str3 = "ۘۤۜۡۖۥۤ۠۬۬ۙۧ۬ۜۜۘ۠۫ۖۘۥۜۦۦۨۛ۟ۨۥۧۧ۫۟۠ۙۥۗۚۡۜۙ۬";
                        while (true) {
                            switch (str3.hashCode() ^ 209561251) {
                                case -728061991:
                                    String str4 = "ۨۥۜۘ۬ۛۖۧۚۧۤۜۗۨۗۜۧۢۘ۬ۘۡۨۢ۫ۚۙۗ۫۠ۨ۠ۡۥۡ۟ۗۦۘۡۗۚۛۚۙۡۤۙ";
                                    while (true) {
                                        switch (str4.hashCode() ^ (-1850526308)) {
                                            case -551061371:
                                                str4 = view == null ? "ۢۘۗۥۥۘۘۜۘۢۨۧۘۘ۟ۧ۠ۢۤ۟ۖۡۖ۠ۨۙۦۘ۠۬ۘۖ۟ۛۛۛۡ۠ۖۗۘ۠ۧ" : "ۜ۫۠ۛۢ۟۠ۧۨۘ۟۟ۥۘ۠۬ۡۘۙۦۨ۬۫ۦۗۡۦۦۥۜۧۘۚۧ۠ۚ۠ۡۙۖۨۘۨۥۥۘۡۢۖۢۨۘ";
                                            case 953517796:
                                                str4 = "ۡۚۡۘۨۥۚۦۢۤ۠ۖ۠ۡۨۚۨۘۡۡۘۖۢۦۘۖۧۡۘ۫ۙ۠۟ۜۘۦۗۛ۟ۜۢۧۧۙۗۘۨۨۗۗۗۘۘۘۚ۬ۖۘ";
                                            case 1049943069:
                                                str3 = "ۦۛۧۚۖۗ۠۟ۥ۬۟ۘۘۛۜۚۚۨۨۛۚ۠ۘۖۘۛۖۜۘۤۥۥۘۡۙۢۗ۬ۡۘۧۡۜۘۥۙۨۘ";
                                                break;
                                            case 1163303208:
                                                str3 = "ۚۚۥۘۚ۬ۥ۬ۛ۟ۤۨۦ۟ۨۥۘۜۨۥ۬ۛۡۖۢ۟ۖۘۘ۠ۡۘۙۧ۟ۦۧۡ";
                                                break;
                                        }
                                    }
                                    break;
                                case -272663496:
                                    str3 = "۬ۧۡۜۢۥۨۢۨۘۛۡۧۚ۫ۧۤۨۖۜۧۢۧۧۧۡۤۜۘۤ۠۫";
                                case 1064574111:
                                    String str5 = "ۙ۠ۥۘۢۜۦۘ۟ۛۖ۟ۤۚۘۤۜۘۡۨۢۢۧۖۦ۠ۖ۫ۨ۠ۘۘۦ۫ۙۦۛ۬ۥۘ۟ۦۗۛۨۙۙۨ۠ۛۖۜۨۡۛۜۧ";
                                    while (true) {
                                        try {
                                            switch (str5.hashCode() ^ (-1596415095)) {
                                                case 898515636:
                                                    str5 = "۟ۖۢ۟ۡ۟۫۫ۘۥۦۡ۟۠ۜۘۨ۬ۙۛ۠۠ۖۘۤ۫ۡۡۢۖۛۘۖۘۛ۟ۤۚ۠ۥۘۤۢۦۘ۠ۧۦۥۙ۟";
                                                    break;
                                                case 1263810385:
                                                    break;
                                                case 1570028268:
                                                    String str6 = "ۢۡۖۘۙۚۖۘۙۨۧۘۨ۫ۧ۟ۤۤۥ۠ۥۨۘۥۢۙۦۗۘۤۤۦۢۙۢۙ۠ۧۘۘۜۡۜ۬ۜۥۘۤۜۜۦۦ۫ۗۘۧۨۥۜۘ";
                                                    while (true) {
                                                        switch (str6.hashCode() ^ (-2024579648)) {
                                                            case -2129133389:
                                                                str6 = !(view.getParent() instanceof View) ? "ۡۨۛۜۢ۬ۢۧۦۜ۫ۙۗ۫ۨۘۚۤ۬۬ۥۛۥۥ۬ۖۘ۬۫ۖۡ" : "ۦۢۤ۟ۘۦۨۛ۠ۜۤۜۘ۫ۡۖۘۜ۫ۦۜۧۖۢ۟ۦ۫ۘۚۘ۫ۥۘۙۖۡۘۥۛ۬ۤۛۘ۫ۢ۫ۜۛۛۢۛۤ";
                                                            case -348338942:
                                                                str5 = "ۘۙۚۧ۠ۡ۟ۢۦۘۦۛۚۜۙۜۘۖۗۡۨۡۨۘ۟ۨۢۜۤ۬ۥۤۥۘۨ۬ۜۘۨ۟ۦۘۢۖۚۖ۠ۖۘ";
                                                                break;
                                                            case 129483481:
                                                                str6 = "ۥۡۧ۫ۖۡۥۦۡۛ۬ۚۛۦۧۘ۟ۛۗ۫ۡۦۘۗۢۥ۫ۦۡۘۤۙۜ۫ۤۥۧ۫";
                                                            case 1476391298:
                                                                str5 = "ۘۜۚۨ۫ۙ۫ۨۜۘ۫۫ۙۛ۬ۨۘۚۡۖۘ۠۬ۦۘۘۜۘۚۚ۟ۨۗۦۘۦۤۘۛۛۛ۫ۙۢۨۘۘۥۖ۬۫ۥۢۗۜۧۤ";
                                                                break;
                                                        }
                                                    }
                                                    break;
                                                case 1697045813:
                                                    break;
                                            }
                                        } catch (Throwable th) {
                                            System.out.println(l2.decrypt("WeqL11u0RsJtwYnrF7NI7mf+g8RStVXIe+KHwFK3AW+mHwoCkvtN73TLjos=\n", "Aq7itjfbIYo=\n") + i);
                                            return;
                                        }
                                    }
                                    break;
                                case 1440655333:
                                    break;
                            }
                        }
                        break;
                    case 1269770699:
                        String str7 = "ۨ۟ۘۤۡۗۦۢۗۖۡۡۘۖ۫ۙۥ۠ۗ۬ۙۢۨۗ۟۬ۨۧۦ۫ۡۛۧ۬ۤ";
                        while (true) {
                            switch (str7.hashCode() ^ 1090465868) {
                                case -1069577083:
                                    view.setVisibility(8);
                                    break;
                                case 66069611:
                                    break;
                                case 359775328:
                                    String str8 = "۫ۛۤۦۗ۠ۢۦۧۘ۟ۙۡۘۥۥۨۘ۠ۥۛۜۙۛۦۧۘۦۖۡۘۢۤۦ۟۟ۜۗ۟ۨۘۦۙۜۙۛ۠ۦۖۚۗ۫ۘ";
                                    while (true) {
                                        switch (str8.hashCode() ^ (-1217872819)) {
                                            case -26527019:
                                                str8 = "ۘۗۦۘ۠ۡۤۛۙۦۦ۟ۗۖۛۤۙۙۗۚۘۗۨۘۖۖۚۥۘۘ۟ۜۡۘۡۖ۬ۨۡۨۘۗۡ۬ۚۛۗۥۖ۫ۜۡۖۧۥۧ";
                                                break;
                                            case 1102486507:
                                                str7 = "ۡ۬ۡۡۖۦۚ۠ۤۦۧۛۛۖۖۘۡۧۨۢۚۖۢۥۚۨۨ۠ۛ۠۠۬ۢ۫ۥۤۧ";
                                                continue;
                                                continue;
                                            case 1603091337:
                                                if (view == null) {
                                                    str8 = "۬ۨۦۗ۬ۨۨۧۛۚۨۖۘۚ۟ۘۘۘۡ۬ۘۚۛ۠۟ۧ۟ۜ۬۫ۘۨ۫ۖۥۦۘۜۘۗۡ۫ۥۘ۫ۚ۬ۨۜۥۛۛۦۚۚۘ";
                                                    break;
                                                } else {
                                                    str8 = "۬ۢۙۥ۫ۨۘ۫ۘۦۜۖۧۜ۫۫ۜۗۨۘۡۙۡ۫ۙۨۦۡۦ۠ۦۤۙ۟ۜۧ۟ۛۘۦۖۗۛۥۦۗۚۨۛ";
                                                    break;
                                                }
                                            case 1969439237:
                                                str7 = "ۛۖۧۘۘۘۘۚۖۡۘ۫ۡۡۘ۟۠ۖۘۗ۫ۨۘۡ۬ۚۢۡۙ۬ۢۖۖ۟ۖ۫۬ۚۜۥۖ۬ۢۡۘ۬ۙۛۢۥۖۘۛۛ";
                                                continue;
                                        }
                                    }
                                    break;
                                case 1618616409:
                                    str7 = "ۘۚۦ۬ۙ۫ۤۜۖۢۥۧۘ۫ۧ۠ۢۙۦۗۛۖ۫ۤۤۧ۫ۡۧۖۢۗۘۗۤ۫ۘۘۧۢۡۥۡۦۘ";
                                    continue;
                            }
                            System.out.println(l2.decrypt("WeqL11u0RsJtwYnrF7NI7mf+g8RStVXIe+KHwFK3AW+mHwoCkvtN73TLjos=\n", "Aq7itjfbIYo=\n") + i);
                            break;
                        }
                }
                return;
            }
            i2++;
            view = (View) view.getParent();
        }
    }

    public static void b(Context context) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        String str = "ۜۛ۟ۡۖۧ۬۬ۦۨۦۖۛۥۙۥ۬ۡۘۥۙۜۘۚۜ۟ۡۡۖۗ۬ۥ۬ۧ۠ۜۡ۠ۚۡۗۢۚ۫";
        j jVar = null;
        ClassLoader classLoader = null;
        Class<?> cls = null;
        Object objInvoke = null;
        Method declaredMethod = null;
        Object obj = null;
        Object obj2 = null;
        Field declaredField = null;
        Class<?> cls2 = null;
        while (true) {
            switch ((((str.hashCode() ^ 297) ^ 572) ^ 558) ^ 1955004224) {
                case -1952667935:
                    jVar = new j(obj);
                    str = "ۨۤۦۘۦۥ۠ۦ۫۠۫ۖ۬ۥ۬ۗ۫ۨۘۥ۟ۘ۬ۨۜۧۡۜۧ۫ۨۘ۫ۦۘۚۥۥۧۢ۠ۖۢ";
                    break;
                case -1823429524:
                    str = "ۦۜۨۚۘۗۗۧۨۘۙۙۨۤ۟۠۟ۜۘۘۦۡۡۘۨۛ۟ۤۘۜ۫ۗۧۢۥۨۘۢۛۖ۠ۗۖۘ۠۫ۜۘ";
                    break;
                case -1739828539:
                    cls2 = Class.forName(l2.decrypt("+2sikaH2Ky/sbCOU4Mgmb/5qMa6v8S5m/3cBj6H9Lm0=\n", "mgVG486fTwE=\n"));
                    str = "۠ۦۡۦ۟ۚۗ۟۟۟ۨۡۨۤ۬ۙۨۙۢ۬ۜۘۙۘۖۘۦۚۡۘۛ۠ۦۘۥ۫ۖۨ۠ۨۦ۫۬ۜۙۖۘ";
                    break;
                case -1574399478:
                    declaredField = cls2.getDeclaredField(l2.decrypt("gwRtWlxWCa2RPWVTXUsthYIlbVdd\n", "8FMENDg5fuA=\n"));
                    str = "ۨ۠ۘ۠ۘۖۡۢۜۘ۬ۖ۫ۙۗۛ۬۟۟ۜۚۨۘۧۤ۬۬۠۟ۦۚۤۢ۠ۤۘ۫۬ۚ۬ۚۤۡۜۘۛ۟ۥۚۘۗۛۦۙۜۛۘ";
                    break;
                case -1312825569:
                    declaredMethod.setAccessible(true);
                    str = "۬ۡۥ۟ۧۜۘۦ۠ۨۙۛۜۘۧ۟ۜۘۤۧۖۛۧۖۘ۠ۤۜۚ۬ۤۦ۫ۜۤۤۦۨۡۗۜۜۜۘ۫۠ۡ";
                    break;
                case -1090447078:
                    obj2 = declaredField.get(null);
                    str = "ۥۘۧۧۧۘۘۗ۫ۖۤۗۚۘۜۜ۫۠ۢ۠ۚۦۗۖۜۜۘۛۜ۠ۧۤۙۜۘۜۖۡۨ۟۬ۙۧۜ۠۟ۢۚۡۚ";
                    break;
                case -849213337:
                    String str2 = "ۨۢۚ۟۠ۡۘ۟ۛۧۘۘۗۜۜۧۘۚۖۖۘۚۨۤۢۨۨ۬ۡۗۥۚ۫ۤۖۤۧۦۘ۟ۧۗۡ۬ۚ";
                    while (true) {
                        switch (str2.hashCode() ^ 1591901452) {
                            case -1488386298:
                                str = "ۘۛۙۙۤۥۜۦۗۛۡۨ۟۫ۖۘ۠ۥۤ۟ۨۘۘۦۤۘۘۖۘۗۜۜۛ۬ۙۛۧۤۡۘۗ۬۟ۘۛۜۘۛۛ۫ۦۨۨۘۙ۫۠۠ۨۜ";
                                continue;
                            case 592594515:
                                str2 = "ۡۡ۠ۨۡۘ۫۟ۜۘۙۦۖۖۤ۠ۡۘۘۘ۟ۦۤۘۜۜۨۖ۠ۗۘۥ۠۫ۡۨۘۦۧۛۗۖۥۖۘۡ۫ۚۛ۟ۦ";
                                break;
                            case 1171088502:
                                String str3 = "ۦ۠ۚ۫ۧۗۧۦۙۘۛۦۜۨ۬ۛۥۦۘۦۗۧۙۦۢ۫ۚۚۤۛۚۙۨۚۤ۠ۚۜۦۘۦۛۧۗۗ۬۫ۛۙۘۨۜۘۧۘ";
                                while (true) {
                                    switch (str3.hashCode() ^ (-1471872793)) {
                                        case -1799967321:
                                            str3 = context.getSystemService(l2.decrypt("wdpM/gFh\n", "trMimm4W7Jw=\n")) == null ? "ۦ۟ۗۘۦۗۤۜۗۨۦۙۚۛۢۡۡۜۘۛۚۦۘ۠ۗۙ۬ۘۧۙۨ۬" : "ۧۦ۫ۚ۠ۖۘۙۜۘۤۚۤۤۛ۠۠ۖۖۤ۠ۡۘ۟ۢۘۚۖۘۘۢۛۙۖۛۖۘ۬۟ۗۢ۠۬ۢۨۦۦۧۗ۬ۧۘۘ";
                                        case -817810641:
                                            str2 = "ۚۘ۬ۚۛۧۤۘۧۜۛۖۤۡۗۨۡ۬ۙۘۛ۠۟ۚۙۧۨ۬ۥۤۜۛۘۘۤۛۘ۠ۛۖۘ۠۫ۙ۟ۢۡۦ۟ۥۘ";
                                            break;
                                        case -503208320:
                                            str2 = "۬ۤۦۘۗۛۖۘۚۦۛۦۨۛۢۛۗۖۤۥ۬ۡۧ۟ۦۗۚۗۦۚۧۤ";
                                            break;
                                        case -119016102:
                                            str3 = "ۗۚۦۙۖ۠ۛۨ۟۠۠ۨۡۤۡۚۚۥۛۥۧۘ۫ۦۡۤۘۜۚۡۥۦۖۛۦۖۜۧ۫ۛۘۛۦۘ";
                                    }
                                }
                                break;
                            case 1432967034:
                                str = "۠ۨۥۘۘ۬ۡۛۥۡۘۡۘۘۦۖۙۧۤۜۘۙۙۘ۟ۗۨۘ۠ۙ۬ۢ۬ۤ۠ۡۨۥۜۡ۟ۡۘۗ۬ۚۖۡۜۢ۬ۧ";
                                continue;
                        }
                    }
                    break;
                case -710035211:
                    str = "ۡۨ۫ۨۥ۫ۘۚۤۤۡۗۨۛۜۙۗۘۦۡۗ۫۟ۜۘۨۙۜۘۛ۠۠ۛۘۦ۟ۨۜۙۡۛۨۗ۟";
                    obj = objInvoke;
                    break;
                case -325276385:
                    declaredField.set(null, Proxy.newProxyInstance(classLoader, new Class[]{cls}, jVar));
                    str = "ۧۥۨۢۥۘۡ۟ۡۘۛۦۖۘۥ۫ۜۘۡۡۢ۫ۦ۟۠ۨۙۡ۬ۖۘۛ۫ۦۘ۟ۡۖۘۢۡۖۘ۠ۖۜۘ۫ۗۧۗ۫ۨۨ۠ۖۗۛۥۧۡۙ";
                    break;
                case -259409093:
                    classLoader = cls.getClassLoader();
                    str = "ۨ۫ۖۘۡ۠ۨۘۖۢۜۘۡۙۗ۠ۖۜۙۙۜۘۙۧ۫ۡۤ۠۬ۦۚۜۧ۠ۚ۫ۚۥۧ۠ۦۗ۠ۥۘۜۛۛۖۛۦۘ";
                    break;
                case 209930023:
                    String str4 = "۬ۦۥۘۗ۠۫ۦۢۨۘ۫ۦۜۘۤۙۨۘ۟ۛ۫ۚۗۙۢۦۜۖۤۥۘ۬۟ۦۘ۟ۜۗۗۡۚۤۡۗۜۛۥۘۚ۬ۜۘۤۚۦۘ";
                    while (true) {
                        switch (str4.hashCode() ^ (-209771796)) {
                            case -999637484:
                                String str5 = "۠ۙۛۨۜۧۘۦۧۚۛۧۥۘۚۜۧۘۚ۠ۗۤۘۥۚ۟ۚۥ۟ۦ۫ۢۧۨۜۥۘ۟۠ۨۘ۫ۡ۟ۧۧ۠ۦۧ۟ۚ۠ۥ";
                                while (true) {
                                    switch (str5.hashCode() ^ (-125077456)) {
                                        case -2014196422:
                                            str4 = "ۤۧ۠ۢۙۘۘۨۛۨۚۢۜ۠ۘ۫ۚۨۨۥۥۖۘۗۨ۫ۜۨۥۛۙۛ۠ۥۘۜۗۡۦۖۥۘ۟۟۫ۥۢۦۘۚۖ۬";
                                            break;
                                        case -1573857994:
                                            str5 = "ۥۤۦۘۜۚ۠۬ۚ۫ۗۦۖۖۥۛۥۥ۬ۙۗۖ۠ۥۘۗۘۜ۟ۤ";
                                        case -93274955:
                                            str5 = obj2 == null ? "ۖۚۥ۬ۙۧۧۤ۬ۘۚۘۨۥۦۨۖۦۨ۬۬ۥۡۧۚۦۗ۫۬ۨۚ۠ۖۢۛۡ" : "ۨۚ۬۫ۚ۫ۙۧۧۙ۫ۨۘ۟ۡ۟ۡۧۗۧ۬ۤۥۜۦۘۤۘۡۜ۟ۨۘۘۖۧۘۜۤۧۤۤۥۨۛۘۘ";
                                        case 1686088765:
                                            str4 = "ۤ۫ۢۦۜۧۘۢۘ۬ۨ۬ۖۘۙۙۦۘۖۢۨۘۡۤۥۘۥۧ۬ۨۗۘ۬۬ۛۨۦۛۗۨۘۘۚ۫ۛۨۘ۟";
                                            break;
                                    }
                                }
                                break;
                            case -603648028:
                                str = "ۧۜۖۤۛۜۘۦ۬ۜ۫۟ۖۖ۟ۚۛۗۖۡۡ۠ۜۧۘۚۨۖۘۙۥۨۛۜۘۘۨۙۤۢۡۥۜۥۖۛۡ۬ۚۚۗۗۤۘۜۨۧ";
                                continue;
                            case 974791042:
                                str4 = "ۚۚۦۘۚۨۙۙۜۧۨ۟ۖۘۘ۬ۗۖۛۡۧۡۜۘۖۥۚ۫ۥۗۛۨۤۗۡۚۧۡ۠";
                                break;
                            case 1287978233:
                                str = "ۡۨ۫ۨۥ۫ۘۚۤۤۡۗۨۛۜۙۗۘۦۡۗ۫۟ۜۘۨۙۜۘۛ۠۠ۛۘۦ۟ۨۜۙۡۛۨۗ۟";
                                continue;
                        }
                    }
                    break;
                case 610089723:
                    declaredField.setAccessible(true);
                    str = "ۥۨۨۘۧ۫ۢ۬۫ۡۜۨۜۘۘۗ۟۟ۜۨۙۨۘۘۡۢۙۧۘۢۨ۬ۡۤۦۙۘۖۢۡۜ۬۫ۛۡۙ";
                    break;
                case 1017312615:
                    str = "۫ۨۦۘ۠ۚۤۛ۬ۖۢۢ۬ۗ۠ۜۦۦۥ۠ۨۦۨۡۤۘ۬ۥۘۘۙۥۘ";
                    obj = obj2;
                    break;
                case 1116104492:
                case 1652548992:
                case 1817933350:
                    return;
                case 1311695783:
                    String str6 = "ۤۨۡۘۚۜۥۘۗۡۗۗۡ۬۟۫ۧ۠ۨ۬ۚۡۧۘۜۚۢ۫ۧۧۧۘۘ۠ۛۢۗ۟ۜۖۦۢۦۚۨ";
                    while (true) {
                        switch (str6.hashCode() ^ (-1325598113)) {
                            case -1913595548:
                                str6 = "ۘۛ۬ۘۧۧۦ۠ۧ۟۬ۘۘۨۨۚۛۥۛۙۢۧ۟۠ۧۜۡۘۙۦ۬ۤۜۜۢۥۧۘ";
                                break;
                            case -1215088387:
                                str = "ۙۢۖۧۚۦۘ۫ۦۤ۫ۘۛۡۛۡۗ۬۬ۡ۠ۙۜۧۜۘۦۘۘ۫۬ۖۘ۠ۖۥۢۧ۫ۨۥۤۘ۟ۤ۠ۦۢ۠ۘۧۘۜ۟ۡ۬ۛۡۘ";
                                continue;
                            case 284462540:
                                str = "ۚۗۧۢ۫۠۟ۜ۠ۧۦۧۘۜۙ۬ۛۜۚۛۨۥۘۡۧۡۘ۠ۡۙۖۢۙ۟ۥۗ۟ۙۨۦ۬ۗ۟ۢۗۢ۠ۡۜۜ";
                                continue;
                            case 492814038:
                                String str7 = "ۖۧۖۜۛ۟۟۫ۦ۠ۤۘۙ۫ۢۚۢۗۡۜۨۜۡۘ۠ۜۚۡۛۘۘۜۚۘۘ۠۫ۨۘۢۛ۫ۗۙۦ۠ۗۡۘۙۛۚ";
                                while (true) {
                                    switch (str7.hashCode() ^ 180801228) {
                                        case -1659657971:
                                            str7 = obj == null ? "ۜۧۖۘۡۖۤ۠۬ۛۜۢۖۘۧۗۜۘۗۥۘۥۚۧۧۛۨۘۧۡۘۘۖ۠ۦۘۦۜۡۘۨ۫ۛۙۚۖۘ۠ۥۢ۟۫۟۫ۚۥ" : "ۚۤۧۚۜۤۜۖۜۧ۫ۚ۠۟ۡۘ۬۫ۚۨۦۦۡ۟ۙۖۜۗۖ۠۟";
                                        case 145107897:
                                            str7 = "ۧۛۨۘ۠ۥۨ۫ۦۜۢۜۡۘۧۦ۬ۢۢ۟ۖۥۖ۬۟ۛۖۢۘۘ۠ۚ۫ۚۢۖۘۥ۫ۖ";
                                        case 229754635:
                                            str6 = "ۗ۫ۤۦۗ۟ۢۚ۟ۛ۫ۘۢ۬ۛۗۗۤۘ۟ۜۦۧۛۖ۫۬ۤۖۜۘ۟ۛۖۘۖۚۧۨۚ۫ۛ۟ۗۙۛۤ۬";
                                            break;
                                        case 1384480783:
                                            str6 = "ۘۤۚ۟ۥۦۘۢ۬ۦۡ۬ۗۖۚ۬ۤۦۘۡۡۖ۬ۢۡۘۙۤۨۨ۟۫ۘۘۖ۠ۗ۟ۦۖۡۘۦۧۥۘۢۛ۬ۥۢ۠";
                                            break;
                                    }
                                }
                                break;
                        }
                    }
                    break;
                case 1337465978:
                    cls = Class.forName(l2.decrypt("44yyDCJkNbP0i7MJY0QG9OyGuQkAbD/85Yek\n", "guLWfk0NUZ0=\n"));
                    str = "ۘ۟ۘۘۡۗۜۧۥۥۥۜۨۘۤۧۢۨۥۧۘۛۚۢۧۜ۬ۨۜۦ۫۟ۗۨۦ۠ۡ۠ۥۘۛۙ۟۬ۦۥۘۚۡۜۘۖۤۜۘ۫ۢۘۘۢۦۥ";
                    break;
                case 1617906399:
                    declaredMethod = cls2.getDeclaredMethod(l2.decrypt("Wy6J54Mym99LBpzeizuawm8uj8aDP5o=\n", "PEv9sOpc/7A=\n"), null);
                    str = "ۛ۟ۨۘۜۥۘۦۧۨۘۚۗۛۧۜۥۗۦۨۘۢ۬ۥ۬ۙۥۘ۟۟ۚۧۥۜۘۘۙۘ۬۫ۤ";
                    break;
                case 1753054795:
                    objInvoke = declaredMethod.invoke(null, null);
                    str = "ۖۡۥۡۙۢۚۘۢۢۡۥۘۨۚ۫ۖ۫ۖۘۧۨۜۜۥۡ۟۬۬ۨ۟ۜۢ۟۟۬ۙۥۖۜ۠۟ۨۨۘ۫ۢ۫ۛۥۜ";
                    break;
            }
        }
    }

    public static void init(Context context) {
        String str = "ۖۡۤۚۜۘۘۦۢ۫۟ۦۛۢۜۛۛۛۘۚۡۚ۫ۜۘۖۦۗۡ۠ۡۘ۠ۚ۫ۚۡۦۙۘۘ۠ۗۤ۬ۥ۫ۧۥۘ";
        while (true) {
            try {
                switch (str.hashCode() ^ (-1234982051)) {
                    case -1901748555:
                        str = "ۦۥۦۖۤۛۛ۫ۨۢ۫ۚۜ۠ۜ۬ۘۧ۫ۤۜۥۖۗ۠ۡۧۘۘۗۖۘ۫ۡۧ۟۠ۗۦۘۧۧۙ۠";
                        continue;
                    case -1498531423:
                        String str2 = "ۢۙۦۘۙۚۥۖۗۙۗۧۦۘۥۚۖۤۖۘۘۥۚۤ۠ۗۨۜۙۧۘۜۡۘۥۤۚ۠ۡۜۦ۬ۦۘۘۗۨۘ۠ۘۢ۠ۘۧۧۙۤ۟ۥۖۘ";
                        while (true) {
                            switch (str2.hashCode() ^ 955163933) {
                                case -683366340:
                                    str2 = "ۛۜۢۨۖۧۘۗۛۡۢ۟ۦۜ۟ۘۨۙۤۢۗۛۡ۠ۡ۫ۨ۬ۜۢۤۘۢۛۚۙۨۧۦۘۜۨۢۤ۠ۜ۫۫ۙ";
                                    break;
                                case -579555094:
                                    str = "ۥۘۜۘۚۥ۟ۖۤۜ۠۫ۖۛۦۢۢۘۖۘ۠ۚۦۘۢۥ۟ۘۢۖۚۢۦۧ۬ۜ۠ۖۘۦۥ۠ۨۚۖۚ۬ۚ۠ۡ۟";
                                    continue;
                                case 915457538:
                                    if (!(context instanceof Application)) {
                                        str2 = "ۜۘۦۚۦۨۙۧۗۢۙۗۗۨۧۘۖۙۤۥۖۡۘۨۚۡ۟ۘۖۘۦۛۢ";
                                        break;
                                    } else {
                                        str2 = "ۧۙۥۨۡۨ۟ۤۗۚۤ۬ۤۡ۟۠ۘۜۘۡۖۤ۬ۘۘ۟ۗۖۘۥۚۦ۫ۛۦۜۗۘ";
                                        break;
                                    }
                                case 2121151582:
                                    str = "ۙۡۖۘۚۥۗۘۥۡۘۛۙۡۘۛۛۡۢۚۚۦۘۧ۟ۚ۠ۢۘ۫ۡۦۨ۠ۖ۠ۘ";
                                    continue;
                                    continue;
                            }
                        }
                        break;
                    case -1193911772:
                        String str3 = "ۙ۠۠ۛۧۘۦۘۢۚۤۙۡۙۥۘ۫ۗ۠۠ۘ۫۠ۨۧۘۤۨۜۥۤۡۡۨۡۘۥۥ۟۟ۥۜ۠۟ۤۢۦۙۡ۫ۡۘ";
                        while (true) {
                            switch (str3.hashCode() ^ 172933613) {
                                case -499533047:
                                    System.out.println(l2.decrypt("TlHVAag5eZp6etc95LOpYPCdIYVj3fteg/oA7CzhrTqqkg==\n", "FRW8YMRWHtI=\n"));
                                    return;
                                case 1275644020:
                                    synchronized (o.class) {
                                        String str4 = "۟ۤۦۘۢۨۙۡۤۗۜۗۤ۫ۖۘۧۦۘۦ۠ۗۧۘۘۥ۫ۚۙۛۙ۫۟۟ۖۖۢ";
                                        while (true) {
                                            try {
                                                switch (str4.hashCode() ^ (-1425368948)) {
                                                    case 730809061:
                                                        Field declaredField = Class.forName(l2.decrypt("ileZP1XWf+idUJg6FOhyqI9WigBb0Xqhjku6IVXdeqo=\n", "6zn9TTq/G8Y=\n")).getDeclaredField(l2.decrypt("4E1KVkWC+y/ydEJfRJ/fB+FsSltE\n", "kxojOCHtjGI=\n"));
                                                        declaredField.setAccessible(true);
                                                        Object obj = declaredField.get(null);
                                                        String str5 = "ۖۙۨۘۛۛ۬ۥۛۢۖۢۨۘۜۨ۟ۗۦۘۙۖۤۗۛۗ۬۫ۚۥۘۢۖۢۥۙۧۢۡۢۧۥ۟۟";
                                                        while (true) {
                                                            switch (str5.hashCode() ^ 1266781429) {
                                                                case -1791608299:
                                                                    str5 = "ۢۡۙۛۤ۠۫ۢۤۛۧ۟ۙۡ۫ۖ۬ۜۧۡۧۡۚۧۖ۠ۗۖۖۚ";
                                                                    continue;
                                                                case -563578582:
                                                                    String str6 = "ۤۖۢۤۦۜۘۘۛۢ۬ۛۦۗ۫ۥۘ۬۫۟ۖۙۡ۠۠ۙۥۡۤۡۗ۟۠ۥ۟ۦۤۙ";
                                                                    while (true) {
                                                                        switch (str6.hashCode() ^ (-119705979)) {
                                                                            case -877567930:
                                                                                str6 = "ۙ۬ۛ۬ۤۖۘۧ۫ۛۥۦۦۘۦۙۥۚۥۨۘ۫ۜۜۙۙ۠ۢ۟ۖۜۡۘ۬ۚۛۧۤۗۘ۠ۡۘۧۤۥۘۘۚ۟ۥۦۡۘۥۢۥۘۜۜۖ";
                                                                                break;
                                                                            case 100371509:
                                                                                if (obj == null) {
                                                                                    str6 = "ۛۧۙ۠ۧۡ۬ۡۘۦۤۨۗۜۤ۬ۗۙۖۛۖۗۚۖۤۡۦۘ۬ۚۛ";
                                                                                    break;
                                                                                } else {
                                                                                    str6 = "ۘۛۡۨۡۖۘۧۙ۬ۦ۬ۡۘ۫ۙۧۖ۟ۖۘۦۨۘۘۦۛۖۤۧۖۡ۬ۨ";
                                                                                    break;
                                                                                }
                                                                            case 239127524:
                                                                                str5 = "ۢۨۡۗۨۖۘۧ۟ۤ۟ۙۙۜۘ۬ۚۖۥۡۨۥۛۜۜۚۨۦۘۗۘ۟۫۫ۜۘ۠۬ۥۗۛ۫ۦ۟ۖۦۘۦۦۤۦ";
                                                                                continue;
                                                                                continue;
                                                                            case 1411685692:
                                                                                str5 = "ۤۙۦۘۘۧۤۛ۠ۜ۠ۥۚ۬ۚۥۡۦۡۗۜۙۤۘۘۙ۠۬ۛۚۖۘۡۗۘ۬ۚۙۖۢۜۘۦۦ۬ۨۥۥۤۚۙۦۜۥۚۧ";
                                                                                continue;
                                                                        }
                                                                    }
                                                                    break;
                                                                case -231206178:
                                                                    break;
                                                                case 1173247777:
                                                                    String str7 = "۬ۚۖۥۛۜۧ۟۠۟ۘۙۤ۟ۧۤۥۘۥۧۘۘۢۚ۫ۤۡۘۘۥۙۜ۟۠ۡۘۘۨۥۗۦۜۡ۫ۘۘ";
                                                                    while (true) {
                                                                        switch (str7.hashCode() ^ (-28763859)) {
                                                                            case -2091989918:
                                                                                break;
                                                                            case -341963556:
                                                                                str7 = "ۛۤۖۡۤ۬ۨۛۙۦۢۦۘۦۤۢ۠ۦۚۡۢ۫ۚ۟۬ۧۦۘ۠ۢۖۨ۠۫ۙۥۜۛۖۛۖۖۨۘ۬ۛۨۘۢۗۥ";
                                                                                continue;
                                                                            case 738204280:
                                                                                String str8 = "ۚۘۡۘۦۘ۬ۘۙ۟۟ۥۦۘ۫۟۬۫ۘۛۙۥۘۘۦۡۖۦ۟ۥۘۙ۠ۘ۫ۛۨ۟ۥۘۥۥۛۜۡۢۘۘۗۗۦۨۘۤۤۤ";
                                                                                while (true) {
                                                                                    switch (str8.hashCode() ^ (-1039595468)) {
                                                                                        case -2075848831:
                                                                                            str8 = "ۙۚۗۜ۫ۗ۫ۧۢۥۦۜۚۚۧۥۙۗۥۡۘۤ۠ۜۘۗۘۘۘۧۖۦۘۜۗ۠ۥ۠۟ۨۖۘۘۨۛۜۡ۫ۗۗ۬ۗۚۢۜۖۧۛ";
                                                                                            break;
                                                                                        case -2038139232:
                                                                                            str7 = "ۖۤ۬ۤ۠ۘۘۨۛ۬ۢ۠۬ۚۡۛۧۧۡۘۧ۠۟ۡۥۨ۠ۛۥۘۖۨۧۘۥۡ۠ۧۤۘۘ۠ۛۖۨۗۡۤۡۨۘۧۛ۟";
                                                                                            continue;
                                                                                            continue;
                                                                                        case -1977220369:
                                                                                            str7 = "۟ۘۦۘۗۜۨۘۦۤۖۗۗۙ۠ۘۖۘ۬ۤۜۘۗۛۙۡ۫ۚۡۢۜۤ۬ۦۦۨۧ۟ۗۚۜۙۦۥۚۥۤ۬۟۬۟ۨ";
                                                                                            continue;
                                                                                        case 397326451:
                                                                                            if (!Proxy.isProxyClass(obj.getClass())) {
                                                                                                str8 = "ۖ۬ۗۥۘۨۘۧۧۘۙۚۚۡۙۥۘۥۡۦۘۖ۫ۦۧۤۢۥۡۖۘۨۚۜۘۦۡ۟ۢۜۛ";
                                                                                                break;
                                                                                            } else {
                                                                                                str8 = "ۧۢۨۥ۟۟ۢۖۥۚ۟ۖۘۚۦۗ۫ۙۘۙۛۖۘ۬۬ۥۡۖۜۘۛۤ۟ۗ۬ۜۥ۠ۤۛۨۨۚۨۡۚۖۚۥۧۙۜۗۜۘۜۦ۬";
                                                                                                break;
                                                                                            }
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case 1809193747:
                                                                                System.out.println(l2.decrypt("/q9F6/O6WxLKhEfXvzCL6EBGtG8DfdjhBgy8DHBpsLISWMQ1GA==\n", "pessip/VPFo=\n"));
                                                                                a = true;
                                                                                return;
                                                                            default:
                                                                                continue;
                                                                        }
                                                                    }
                                                                    break;
                                                                default:
                                                                    continue;
                                                            }
                                                        }
                                                        b(context);
                                                        a = true;
                                                        System.out.println(l2.decrypt("O2P/iUM8uYwPSP21DxuxqwsHc2CytnlPhasADYHfOEzw\n", "YCeW6C9T3sQ=\n"));
                                                        return;
                                                    case 875567253:
                                                        return;
                                                    case 1329311053:
                                                        String str9 = "ۙ۬ۥ۫۫ۦۨۘۖ۬ۜۥۘ۫۟۬۟۠ۨۘ۟ۧۚۧۚۧۧۜۡۘۙۘۘ۠ۙۗ۠ۖۗ";
                                                        while (true) {
                                                            switch (str9.hashCode() ^ 691057693) {
                                                                case -1964072921:
                                                                    if (!a) {
                                                                        str9 = "ۢۚ۠ۦ۠ۨۘۦۙۚۨۦۗ۬ۡۨۘ۬ۨ۟ۨۤۢۢۧۧ۫۠۬ۢۧۥۘۗۗۜۘۢ۫ۢ۬ۨۢۧۙۗۢۨۦۖۚ";
                                                                        break;
                                                                    } else {
                                                                        str9 = "ۤۨ۟۫۟۫۫ۨۚۜۡۨۘ۫ۧۜۛۥۥۖۦ۠ۥۜۘ۟ۖۗۖۢۜۘ۫ۖۧۡۚۨ";
                                                                        break;
                                                                    }
                                                                case -1591576252:
                                                                    str9 = "ۜ۫ۨۘۢ۟ۘۘۧۛۜۘۜۗ۬ۡۗۥۨ۟ۡۦۨۘۨۢۥۘۛۖۜۨ۬ۜۘۢۧۛۦ۟۬ۖ۠ۜ۬۫ۜۘ۬ۖۨۜۘۡ";
                                                                    break;
                                                                case 612307748:
                                                                    str4 = "ۚۛۚۤۥۡۖۧۤۢۥۜ۫۟ۚۧۦۖ۟ۘۖ۫ۘۤۥۘۛۨۡۘ۬ۜۘۘۦۜۨۘ";
                                                                    continue;
                                                                case 1074784412:
                                                                    str4 = "ۜۧۥۡۢۛۜۢۥۘۚ۬ۤۦۗ۬ۛۥۜۘ۬ۤۜۘ۠ۨۨۧ۫ۖۘۤۦۘ۠ۙۡۚۥۨۢۛۥۤ۠ۖۖۘ۠ۤ۬۠";
                                                                    continue;
                                                            }
                                                        }
                                                        break;
                                                    case 1488414985:
                                                        str4 = "ۖۡ۫۫ۙۜۡ۠ۧۢۛ۟ۚۧۡۢۤۘۡۥۦۘۦۙۚۙ۠ۛۧۜۨۘۙ۫ۡ";
                                                        break;
                                                }
                                            } finally {
                                            }
                                        }
                                    }
                                    break;
                                case 1319695144:
                                    String str10 = "ۗ۟ۙۗ۠ۜۤۗ۬ۗۘۛ۬ۛۦۜۧۦۖۤۨ۠ۛۤۗۚۖۨۥۘۙ۟۟ۢۦ۠";
                                    while (true) {
                                        switch (str10.hashCode() ^ 2013013547) {
                                            case -1636554281:
                                                str3 = "ۥۚۡۘۗۘۧۗۡۡۘۗۨۧۘ۫ۨۘۘۜۦۨۢۧۡۘۗ۠۠ۥۙۤۦۖۚ۬ۥ۠ۚ۫ۚۥۛۥۘۤۨ۫";
                                                continue;
                                                continue;
                                            case -1458209996:
                                                str3 = "ۖ۠ۙۡۗۖۘۦۤۨۘۚۤ۠۫۫ۥۘۦۖۜۡ۫ۙ۟ۨۛۘۖۘۚۢ۠";
                                                continue;
                                            case -1433234038:
                                                if (!a) {
                                                    str10 = "ۖۡۖۚۜۨۘۧ۠ۗۡۦۙۜۙۨۡۧۘ۠ۖۤۜ۟ۥۘ۠ۤۜۘ۠۠ۧۢۥۡۘۛۜۗ";
                                                    break;
                                                } else {
                                                    str10 = "ۡۡۡۙۘۦۘ۠ۚۜۘۜۖۘ۟ۡۘۘۥۘۧۘۖۥۤۢۡۤ۠ۘۢۨ۬ۧۢۤۨ۠ۚۖۘ۠۫ۛۡۘۘۚ۬ۡۘۘ۫ۡۘۙۖ۫ۡ۠ۘۘ";
                                                    break;
                                                }
                                            case 56070023:
                                                str10 = "ۚ۬ۚۤۚۥۘۥۙۖۥ۠ۘۜۗۘ۫ۨۥۥۥۧۘۙۖۖۥ۬ۢۧۘ۟ۚۘ۬ۥۙۚ";
                                                break;
                                        }
                                    }
                                    break;
                                case 1320014022:
                                    str3 = "ۧ۠ۥۘۤۖۖۨۗۖۙۥ۬۬۠ۙۖۡۘۥ۬ۡۡۨۦۘۙۗۘۖۘۘ۫ۥۢ۠ۖ۬";
                                    continue;
                                default:
                                    continue;
                            }
                        }
                        break;
                    case 1733144651:
                        System.out.println(l2.decrypt("/OLN3LObotfIyc/g/x1YAYfn1M2znab+08/L0/+3qvHTw9zJMEhJehgbQyh6\n", "p6akvd/0xZ8=\n"));
                        return;
                    default:
                        continue;
                }
            } catch (Throwable th) {
                System.out.println(l2.decrypt("LPRc0tqdv64Y317ulhdQe5IXvlY6ZD1a9VWNC1lOQg==\n", "d7A1s7by2OY=\n") + th);
                return;
            }
            System.out.println(l2.decrypt("LPRc0tqdv64Y317ulhdQe5IXvlY6ZD1a9VWNC1lOQg==\n", "d7A1s7by2OY=\n") + th);
            return;
        }
    }
}
