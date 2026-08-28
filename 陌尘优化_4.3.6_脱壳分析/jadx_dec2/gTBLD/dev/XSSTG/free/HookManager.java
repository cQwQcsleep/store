package gTBLD.dev.XSSTG.free;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Instrumentation;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.os.Bundle;
import android.os.IBinder;
import core.pro.android.notify.h;
import core.pro.android.notify.k2;
import core.pro.android.notify.l2;
import core.pro.android.notify.s0;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;

/* loaded from: /workspace/unpacked/classes2.dex */
public class HookManager {
    public boolean proxy = false;

    @SuppressLint({"DiscouragedPrivateApi"})
    public static void hookInstrumentation(Context context) {
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread", null);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, null);
            Field declaredField = cls.getDeclaredField("mInstrumentation");
            declaredField.setAccessible(true);
            try {
                declaredField.set(objInvoke, new Instrumentation(context, (Instrumentation) declaredField.get(objInvoke)) { // from class: gTBLD.dev.XSSTG.free.HookManager.1
                    final Context val$context;
                    final Instrumentation val$originalInstrumentation;

                    {
                        this.val$context = context;
                        this.val$originalInstrumentation = instrumentation;
                    }

                    /* JADX WARN: Code restructure failed: missing block: B:392:0x01c1, code lost:
                    
                        continue;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:393:0x01c1, code lost:
                    
                        continue;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:396:0x01c1, code lost:
                    
                        continue;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:752:?, code lost:
                    
                        return (android.app.Instrumentation.ActivityResult) r0.invoke(r13, r3);
                     */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                    */
                    private Instrumentation.ActivityResult invokeExecStartActivityCompat(Instrumentation instrumentation, Context context2, IBinder iBinder, IBinder iBinder2, Activity activity, Intent intent, int i, Bundle bundle) throws Exception {
                        Object objInvoke2;
                        Object obj;
                        Object[] objArr;
                        ArrayList arrayList = new ArrayList();
                        Method[] declaredMethods = Instrumentation.class.getDeclaredMethods();
                        int length = declaredMethods.length;
                        int i2 = 0;
                        while (true) {
                            String str = "ۛۥ۠ۛۡۖۜۖۛ۟۫ۗۖ۬ۘۜۧ۬ۙۜ۠ۙۨۘۡۦۘۦۖۙۖۘۙۤ۬ۦۗۨۥۘ۫ۧ۟ۦۗۦ۬ۨۗ";
                            while (true) {
                                switch (str.hashCode() ^ (-268731309)) {
                                    case -1867498371:
                                        Method method = declaredMethods[i2];
                                        String str2 = "ۡۛۙۜ۫ۦۘۥۡۨۨۙۧۦۥۥ۫ۧۢۧۗۘۘۥۨۧۘۘۘۜۘ۠ۛۦ";
                                        while (true) {
                                            switch (str2.hashCode() ^ (-1060941159)) {
                                                case -1915385909:
                                                    str2 = "۬ۨ۟ۙۘۖۘۙۧۢۗۙ۫۠ۧۖۧۨۡۘۛۦۥۘۛۘۘۗۢۢ۫۫ۧۥۘۚ۫۫ۤۜۨۘۢۙۛۛۗۢ۬ۜ۫۫ۜۘۡۤۖۘ";
                                                    break;
                                                case -1147223882:
                                                    Class<?>[] parameterTypes = method.getParameterTypes();
                                                    int length2 = parameterTypes.length;
                                                    int i3 = 0;
                                                    while (true) {
                                                        String str3 = "ۧۜۡۘۢ۟ۘۦۦۗۤۥۘ۠ۗ۫۠ۨۚ۠۠ۤۘۛۙۨۥۢۢۥ۠ۘۨۤ۟۫۫ۦۗۡۘۥۥۘۗ۬ۦۗۘ۠۫";
                                                        while (true) {
                                                            switch (str3.hashCode() ^ (-1502453484)) {
                                                                case -1422756165:
                                                                    String str4 = "ۥۥۦۘ۬۠ۡۘۢۥۦۚۥۡۘۦۧۖۤۢ۟ۖۙۚ۫ۘ۫۟ۛۡۗۥۧۚۛۡۘۢۗۖۘۜۖۗ۫۫۬ۡۦۨۗۨۖ۠ۙۖۘۧۗ۠";
                                                                    while (true) {
                                                                        switch (str4.hashCode() ^ 1040590811) {
                                                                            case -1479771428:
                                                                                str3 = "ۛۛۧۛۤۗۚۢۖۢۜ۬ۖۨ۠ۧ۬ۘۡ۫ۢ۫۬ۡۘۢۢۜۘۚۖۚۖۙۤۢۙ";
                                                                                break;
                                                                            case -1435572158:
                                                                                str4 = "ۨۡۥۘۛ۫ۗۖۖۤ۬ۥۦۢۨۤۘۤۥۤۚۡۘ۬ۦ۟۟ۗۛۘۢۦ۬ۦۦۡ۬۫ۛۙۘۘۘۖۡۘ۟ۦۦۘ۠ۧ۠";
                                                                            case 932701308:
                                                                                str3 = "ۗۛۘۘ۟ۡ۟ۨۙۢۙۙۨ۫۬ۧۨۡۨۘۡۤۤۛۘۖۢۥۜۖۛۥۛۨ۟۟۟ۛۢۦۨۘۘۤ";
                                                                                break;
                                                                            case 1971243485:
                                                                                str4 = i3 < length2 ? "ۜۖۚۜۢۡ۬ۙۤۥ۠ۗۡۥۘۛۡۥۘۡۡۛۘۥۜۜۡۙۦ۟ۚۦۦۜۗ۟ۦۘۗۦۚۡۥۦۘ" : "ۙۦۧ۫۬ۘۜۛۨۤۛۤ۟ۗۤ۬ۚ۬ۙۗۖۘ۟ۤۡ۠ۢۚۖ۬ۡۘۥۡۥۘ۠ۗ۫۠ۧۜۨۥۥ۬ۨۘ۫ۥۡ";
                                                                        }
                                                                    }
                                                                    break;
                                                                case -463641005:
                                                                    str3 = "ۙۦۘۥۚۖۥۢۥ۠ۨۘ۠ۡ۟۫ۧۡۘۖۙۤۦۘ۟ۢۨۜۥۤۧ۬ۡۦۤ۟ۦۖۥۨۘۙۦۦۘۛۨۦۘۘۛۗ";
                                                                case 127296504:
                                                                    String str5 = "۫۫ۨۘۗۢۘۛۡۧۘۚۘ۟ۘ۫ۡ۫ۧۚ۟۠ۨۚۡۥۘۗۢۨۘۙۛۨۘ";
                                                                    while (true) {
                                                                        switch (str5.hashCode() ^ (-1866876892)) {
                                                                            case -986253730:
                                                                                break;
                                                                            case -645886260:
                                                                                String str6 = "ۘۚ۫ۛ۫ۤ۠ۤۨۘۧۡۧۘۛۦۚۥۙ۠۟ۘۡ۠ۜۨۧۧۡۘۚ۟ۥۖۡۡۗۢۧ۟۫۫ۡۗۖۘ";
                                                                                while (true) {
                                                                                    switch (str6.hashCode() ^ (-976472160)) {
                                                                                        case -1417906254:
                                                                                            str6 = "ۗۛۡۘۘۧ۫ۛ۠ۜۘۤۛۜۘ۟ۤۡۢۤۙۛۡۘۙۨۢۚ۟ۙۡۡۙۡۛۨۜ۬ۜۧۘۘۧۚۤ۟ۦۙۚ۟ۨ";
                                                                                            break;
                                                                                        case -138378701:
                                                                                            String str7 = "ۘۥۙۡۥ۟ۥۨۢۧۢۨۤۡۢ۬ۖۗ۫۠ۦۤۖۗۥۢ۠ۢۧۨۙۨۜۘۗۙۨۘۢۤۥۘۦۙۢۢۘۖۘۛۧۨۘۗۛ۫";
                                                                                            while (true) {
                                                                                                switch (str7.hashCode() ^ 1123670202) {
                                                                                                    case -1936385899:
                                                                                                        str7 = parameterTypes.length >= 6 ? "ۘۚۚۚۗۧۜۚۥۘۥۖۘۖۛۗ۟ۡۢ۫ۦۘۦۘۘۘۢۘۜۜۢۘۘ۟ۤۛۘۢۡ" : "ۢۥۨۘۖۤۢۦۡۘۡ۬ۢ۠ۨۨۘۦۜۦۦۙۘۘۢۘۖۘۛۨۛۜۘۡۘۢ۠۠ۢۜۧۢ۠ۚۜۨۘۚۙ۠ۜ۫ۚۛۧۘۘۧۚۤ";
                                                                                                    case -1384149334:
                                                                                                        str7 = "ۡۨۥۨۜۡۘۗۢۘۘۛۜۘۘۡۖۜۘۨۨۡۜ۟ۛ۬۫ۛۤ۠ۧۚۘۗۙۨۛ۠ۜۘ";
                                                                                                    case -145901987:
                                                                                                        str6 = "ۜ۫ۘۘۤۤۦۘۗ۠ۗ۟ۙۧۦۖۜۘۚۛۛ۬ۦ۟ۚۙۗۧۗۜۢۜۚ";
                                                                                                        break;
                                                                                                    case 253371463:
                                                                                                        str6 = "۠ۚۤۗ۟ۛۢۘۖۘۘۛ۠۠ۛۘ۟ۨۙۦۗۖۘۢ۫ۨۜۙۥۘۡۛۛۨۧۨۘۨۧۙۥۙ۫ۡۧۘ";
                                                                                                        break;
                                                                                                }
                                                                                            }
                                                                                            break;
                                                                                        case 561560463:
                                                                                            String str8 = "ۚۦۡۘۜۦۘۘ۫۠ۜۘۢ۬ۤۡۢۜۘۥ۟ۜۙۢۢۙ۠۫ۥۗۗ۠۟ۦۘۥۨۢۖۢۖۙۖۦۛۡۥۘۙۥۢۗۨۧۘۤۛۙۚۜۧۘ";
                                                                                            while (true) {
                                                                                                switch (str8.hashCode() ^ 999806861) {
                                                                                                    case -1769172060:
                                                                                                        arrayList.add(method);
                                                                                                        continue;
                                                                                                        continue;
                                                                                                        continue;
                                                                                                    case -588168826:
                                                                                                        str8 = "ۙۨ۟ۨ۟ۥۗۤ۟ۘۤۚۖۦۜۦ۟۬ۖۦۘ۬ۚ۠۟ۧۖ۬";
                                                                                                        break;
                                                                                                    case 1421228607:
                                                                                                        break;
                                                                                                    case 2013396479:
                                                                                                        String str9 = "۠ۚۢۨۗۦۖۧۙۡۤۥۢۥ۠ۚۦ۫ۡۗۦۥۧ۬۟ۦۘۧۛۨۙ۫ۜۡۤۜۥ۟۬ۨۗۙ";
                                                                                                        while (true) {
                                                                                                            switch (str9.hashCode() ^ (-1387664454)) {
                                                                                                                case -1594946272:
                                                                                                                    str8 = "ۥۖ۟ۖۖۘۘۨۜۙۢۡۖۘ۬ۢۘۜ۠ۛۛۢۜۘۤۗۙ۠ۥۡۘۡۘۤۙۥۖۘۘۚۧۖۜۨۘۚ۟۬ۙۖۡۧۦۙ";
                                                                                                                    break;
                                                                                                                case -760714699:
                                                                                                                    str9 = "ۛۚۦۘۤۥۨۘۢۚ۠ۜۚ۬ۙۛۖۘۦۖۙۛۗۥۘۜ۫ۥۗۡۨۘۧۗ۟ۙۢ۟ۚۗۦۘۤۧۦۚ۫";
                                                                                                                case 1461892042:
                                                                                                                    str8 = "ۧۖۦۖۗۛۗۧ۫ۤۡۛۧۥۜۘۜۜۚۗۥۨۘۤۢۚۙۚۜۥۡۘۙۘۦ۠ۤۦۘۦۦۦۛ۠ۥۘۗۘۨۘ۫ۡ۟";
                                                                                                                    break;
                                                                                                                case 2046515157:
                                                                                                                    str9 = parameterTypes.length <= 8 ? "ۨ۬ۤ۟ۢۥ۫ۘۧۘۧۢۛۦۗ۟۟۟ۜۢ۫ۨۘۥۛۛۤ۟ۜ۬۟ۛۜۗۙ۠۬ۘۨۚۨۘۡۛۖۘۜۢۜۢ۠ۙۥ۟ۖۘۥۦۢ" : "ۗۙۥۛۛ۟۟۟ۡۘۚ۫ۘۘۜ۬ۘۘ۫۟ۖۘۥ۫ۡ۬ۙۚۖ۫ۘۙ۫۫ۧۛۧۖۛ۬ۘۢ۟۫۬";
                                                                                                            }
                                                                                                        }
                                                                                                        break;
                                                                                                }
                                                                                            }
                                                                                            break;
                                                                                        case 1327930933:
                                                                                            break;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case -622261154:
                                                                                str5 = "۠ۜ۟ۢۦۚۨۜۤ۠ۥۥ۟ۢۡۘ۟ۨۢۨۚۙ۠ۨۛۜۛ۫ۤ۟ۥۗۚۡۘۨۡۡ";
                                                                            case 1912056437:
                                                                                String str10 = "۫ۨۦ۬ۗۨۘ۫۬ۘۧۚۥۘۢۖۡۘۡ۟ۨۘۚ۟ۖۘ۬ۡ۠ۖۘۨۘۘۘۛۡۙۥۢۧۨۘۖۖۖ۟ۜۜۘۤۚۡۘ۫ۢۢ";
                                                                                while (true) {
                                                                                    switch (str10.hashCode() ^ 1288940316) {
                                                                                        case -592089006:
                                                                                            str5 = "ۥۧۘۦۖۥۡ۟ۥۢۥۚۛۥۨۜۗۦ۬ۥۘۢۜۧۘۙۦۧۖۘۨ";
                                                                                            break;
                                                                                        case 453807447:
                                                                                            str5 = "۫ۨۘۘ۠ۚۦۘ۟ۢۨۥۨۨۘۢۘ۟ۤۚۥۘۘۦۢۘۛۚ۟ۨۚ۬۫ۙۨ۬ۖۘۖۘۦۢۗ۟ۧۥۡۦۗۤۘۗۤۤ۟ۢۢۜۥۘ";
                                                                                            break;
                                                                                        case 1547302758:
                                                                                            str10 = Intent.class.equals(parameterTypes[i3]) ? "ۨۤ۟۠ۤۜۘ۫ۤۗۢۜۘۨۧۙۙۙۜۢۙۛۛۜۚ۫ۡۜۖ۟ۢۥۘۙ۠۬۠۟ۡۥۢۢۦۘۧۖۥۘ۫ۙۘۘۢۢۛۦۛۖ" : "ۗۡۜۘ۟ۘۛۤۧ۫ۘۡۙ۟۫ۨۖۘۖۘۤۙۛۚۧۦۘۧ۠ۦۛۛ۬ۦۘۜۥۗۢ";
                                                                                        case 1816469420:
                                                                                            str10 = "ۧۨۙۤۚۧ۟ۨۘۚۤ۠۟ۧۙ۟ۨۢۖۡۦۘ۟ۗۦ۫ۡۨۘۧۦۙ";
                                                                                    }
                                                                                }
                                                                                break;
                                                                        }
                                                                    }
                                                                    break;
                                                                case 1451064738:
                                                                    break;
                                                            }
                                                        }
                                                        i3++;
                                                    }
                                                    break;
                                                case 497510298:
                                                    break;
                                                case 793366040:
                                                    String str11 = "ۜۢۤۛۨۦۘ۫ۧ۫ۙ۫ۡۨۚۡ۬ۦۜۘۘۙ۠ۤۚۦۤ۬ۛۚۜۙ۬ۢۖۥۗۗۡۢۛۡۦۘ";
                                                    while (true) {
                                                        switch (str11.hashCode() ^ 1452711695) {
                                                            case -1260831905:
                                                                str11 = "ۘۘۧۘۘۥۥۙۗۡۦ۟ۨ۫ۘۜۖۘۘۦۘۙۦۨۧ۬ۢۚ۬ۛۧۘۛۡۖۘۘ۬ۨۘۦۖ";
                                                            case -910209113:
                                                                str2 = "ۡۦۘۤۚۡۢ۬۠۠ۙ۬ۚ۟۟۬ۚ۠ۛ۫ۙۡۖ۠۫۠ۛۛۘ۫ۘۘۘۦۜۨۡۗۗ۫ۡۘ";
                                                                break;
                                                            case -278799525:
                                                                str2 = "ۜ۟ۙ۠ۦۥ۫ۖۜۘۤۡۗۗ۟ۜۘۖ۠ۧۖۚۘۥۢۦۘ۠۟ۡ۠۟۟۫ۤۨۤۢۦۜ۠ۦ۬ۗۘۘۡۦۧۘۧۦۦ";
                                                                break;
                                                            case 328095700:
                                                                str11 = !"execStartActivity".equals(method.getName()) ? "ۨۚۨۘۖۗۖۘۧ۠ۥۘۙۘۨۘۗۧۛۛ۬۫ۢۚۙۗۗۦۘۘ۫ۖ۫۠ۦۘۡ۬ۖۘۛۡۧۘۛۡۚۘۢۤ" : "ۙۛۧۛ۬ۦۘۦ۠ۡۘ۬ۚ۬ۦۧۥۘۥۗ۟ۜ۠ۨۘۢۤۤ۫ۦۡ۫ۥۧ۬ۗۡۘۢۡۨ۫ۗۡۜ۟ۗۚۦۥۧۚۡ۟۟ۜۤۦۘ";
                                                        }
                                                    }
                                                    break;
                                            }
                                        }
                                        i2++;
                                        break;
                                    case -1550470326:
                                        String str12 = "۬ۙۜۧ۫ۥۘۖ۠۟ۛۧۙۤ۟ۥ۬ۗ۟۫ۡۧۢۧۚۖ۟۬ۦۛۨۗۙۧۦۛۥۧۛۨۘۨ";
                                        while (true) {
                                            switch (str12.hashCode() ^ (-1617522755)) {
                                                case -1562883024:
                                                    str = "۠۬۠ۤ۠ۡۘۤۢۖۘۢۨۘۢ۬ۘۧ۠ۦۙۡۨ۬۟ۚۖۦۘۦۢۜ";
                                                    break;
                                                case 1219785902:
                                                    str12 = "۬ۖۖۘ۠۟ۧ۬ۧ۫ۖۚۙ۬ۡۚۡۗۥۥۦۧۘۨ۠ۚۘۡۦۘۘۘۧۨۜۘۛ۫ۗۢۚۨۘ۫۟ۦۙۤۦۧۨۧۘ";
                                                case 1804295904:
                                                    str12 = i2 < length ? "۠ۗۗۖۖۖۙۤۥۡۗۘۧۘۘۡۖۨ۠ۧۖۗ۬۫ۧۗۜۗۙۖۘۗۤۖۛۦۨ" : "ۥۛۦۚۧۥ۟ۦۜۘۚ۫ۨۜۡۥۘ۠ۢۡۙۗۚۢ۫ۘۘۗۖ۬ۗۘۧۨۧۢۡ";
                                                case 2140765628:
                                                    str = "۬ۗۥۘۡۢۙ۫ۥۥ۬۬ۡۘ۫ۦۘ۟ۜۨۛۖۦۘۘ۠ۢۘۨۧۘۥ۠ۦۘۚۡۖۘۢۤۖۘ۬۠ۖۡۙۡۘۢۧۙۜ۟ۢ";
                                                    break;
                                            }
                                        }
                                        break;
                                    case -405728806:
                                        str = "۠ۜۦ۬ۨۦۘۨۛۦۘۛۜۡۘۢۙۦۘ۟ۙۦۦۡۜۤۗۨ۠ۡ۟۟ۨۗۨ۬ۛۤۡۧۘۥۥۥۘ۟ۥۜۜۛ۠ۡۡ۟";
                                        break;
                                    case -130983559:
                                        String str13 = "ۖۦۨۧۥ۠ۦۧۧ۫۟ۨۤۤۙۥ۬ۜۘۘۤۛۤ۟ۖۘۡۗۜ۬ۗۥۘۡۙۚۨۥۜ۬ۦۘۗۖۡۘ";
                                        while (true) {
                                            switch (str13.hashCode() ^ (-1415202317)) {
                                                case -1611187768:
                                                    String str14 = "ۛۥۢ۫ۚ۬ۤۜ۟ۜۢۖۘۤۦ۟۫ۨ۠ۨۖ۬ۘۘۢۜۤۧۨ۟";
                                                    while (true) {
                                                        switch (str14.hashCode() ^ 64194154) {
                                                            case -1490951998:
                                                                str13 = "ۦۚۥۘۖۨۖۘۧۧ۠ۦۧۘۚۡۥۚ۬ۘۖ۬ۘۦۡۥۗۢۚۨۘۘۘۙۤۨۧۢۥ۫ۚۤۦۜۚۨ۠ۨۘۨ۬ۘۘۦۧۨۜۚ";
                                                                continue;
                                                                continue;
                                                            case 543980472:
                                                                str14 = "ۢۧۨۛۦۛۡ۟ۘۤ۟ۤۗ۠ۥۘۚ۬ۜۘۙۘۧۘۨۡۙۨۥۦۦۧۤۤ۟ۦۥۨۗۢۧۦۘۢۘۢ";
                                                                break;
                                                            case 1175559150:
                                                                str13 = "ۗ۫ۨۖ۟ۖۘ۬ۗۥۘ۠ۙۡۚۜۜۘۧۗۧۧۖۚ۫ۡۦۜۜۢ۫ۢ۫ۨۡ۟ۘۚۥۘۢ۟ۖۘ۫ۦۛۚۨۨۙۖۜۘۡ۫ۗۗۡۖۘ";
                                                                continue;
                                                            case 1325825000:
                                                                if (bundle == null) {
                                                                    str14 = "ۜۡۦ۫ۥۥۗۦۙ۠۠ۦۛ۬ۥۦۨۢۧۜۙۛ۟ۚۚۜۧۘۘۦۨۥۤۘۘۦۜۦۘۛۧۨ۟ۢۨۗ۫ۛۥ۫ۖۘۛۦۘ۬ۖ۟";
                                                                    break;
                                                                } else {
                                                                    str14 = "ۥۛۖۙۦۖۛۚۖۘۥۥۦۜ۫ۘۘۛۦۧۘۢۡ۫ۤۤۜۘۗۙ۬ۘۢۥ";
                                                                    break;
                                                                }
                                                        }
                                                    }
                                                    break;
                                                case -1286153662:
                                                    break;
                                                case -139281797:
                                                    try {
                                                        objInvoke2 = Class.forName("android.app.ActivityOptions").getMethod("fromBundle", Bundle.class).invoke(null, bundle);
                                                        break;
                                                    } catch (Throwable th) {
                                                        break;
                                                    }
                                                case 1970403987:
                                                    str13 = "ۢۚ۬ۗۙ۬ۡۙۜ۫۠ۖۘۨ۟ۖۢ۟ۖۗ۬۠۬ۘۜۘۘ۬ۥۘۜۘۘۘۙ۫۠ۡۧۨۢۘ۟ۤۖۘ";
                                                    continue;
                                            }
                                        }
                                        objInvoke2 = null;
                                        Iterator it = arrayList.iterator();
                                        Exception exc = null;
                                        while (true) {
                                            String str15 = "ۡۘ۠۬۫۫ۥۘۨۨۘۘۙۘۜۘ۟۬ۧۗ۫ۤۤۖۡۙۙۥ۟ۧۚۧۨۤ۟۠ۖۡ۟ۗۜ۟ۘۘۥ۫ۦۘ۫ۥۥ";
                                            while (true) {
                                                switch (str15.hashCode() ^ (-994767823)) {
                                                    case -219163637:
                                                        try {
                                                            Method declaredMethod2 = Instrumentation.class.getDeclaredMethod("execStartActivity", Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, Integer.TYPE, Bundle.class);
                                                            declaredMethod2.setAccessible(true);
                                                            return (Instrumentation.ActivityResult) declaredMethod2.invoke(instrumentation, context2, iBinder, iBinder2, activity, intent, Integer.valueOf(i), bundle);
                                                        } catch (Exception e) {
                                                            Exception exc2 = e;
                                                            String str16 = "۫ۗۖۜۡۨۢۢۡۢۜۗ۫ۨۧۘۢ۠ۚۢۦۧۘۜۜۙۘۥۖۘۡۤ۠ۤۘۜۡۚۨ";
                                                            while (true) {
                                                                switch (str16.hashCode() ^ (-1499448171)) {
                                                                    case 400647358:
                                                                        break;
                                                                    case 973044573:
                                                                        exc2 = exc;
                                                                        break;
                                                                    case 1333871209:
                                                                        str16 = "ۦۘۨۘ۠ۧۘۘۗۘۡۢۘۨ۬ۖۖۘۨۗۨۘۖ۬ۢۢۖۜ۠۫ۦۢۢۤ";
                                                                        continue;
                                                                    case 1478687391:
                                                                        String str17 = "ۢۧۘۢۥۡۘۙ۫ۜ۬ۤۗۜۘۘۥ۬ۧۧۙ۫ۘۘۙۢۤۢۤۘۜۘ۫ۛۢۦۚ۠ۜۜۗۘۦۦ";
                                                                        while (true) {
                                                                            switch (str17.hashCode() ^ 905450335) {
                                                                                case -1851286625:
                                                                                    str16 = "ۦۥۨۘۧ۬۠ۤ۠ۙ۫ۤۥۜ۟۬ۖۤۚۚۨۘۧۜۛۛۧۖۘۦۥۖ۠۠ۢۧۡۖۙ۬۟ۡۢۜۘۡۚۦۘ۬ۖۗ۫۠ۘ۬ۦۗ";
                                                                                    continue;
                                                                                    continue;
                                                                                case -1346588486:
                                                                                    if (exc != null) {
                                                                                        str17 = "۫ۥۛ۠۟ۘۥ۟ۦۜۥۦۜۡۘۤۤۢ۫ۚ۟ۨۙۛ۬۟ۖ۬۫ۨۘۛ۬۠ۘۧۥۘ";
                                                                                        break;
                                                                                    } else {
                                                                                        str17 = "۟ۥۖۡۢۧۢ۬۫ۤ۟ۖۗۦۖۥ۬ۦ۬ۜۜۚۘۨۘۤۙۨۘۥۜۘۖ۬ۜۘۛۘ";
                                                                                        break;
                                                                                    }
                                                                                case -166148154:
                                                                                    str17 = "ۘۥۙۧۘ۠۟ۙۖۡۘۘۚۡۖ۟۬ۛ۠ۘۗۙۘۡۧۛۤۦۘۙۛۖۤ۬۠ۜ";
                                                                                    break;
                                                                                case 963915566:
                                                                                    str16 = "۟ۡۚۧ۠ۥۧۢۖۘۥ۫ۚ۫ۜۧۘۙۙۡۧۖۜۘ۠ۤۘۨۙۙۜۘۡ۠۬ۨۘۢۤ۫ۢۖۡ۬۟ۤ۬ۦۘۡۡ۟";
                                                                                    continue;
                                                                            }
                                                                        }
                                                                        break;
                                                                }
                                                            }
                                                            try {
                                                                Method declaredMethod3 = Instrumentation.class.getDeclaredMethod("execStartActivity", Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, Integer.TYPE);
                                                                declaredMethod3.setAccessible(true);
                                                                return (Instrumentation.ActivityResult) declaredMethod3.invoke(instrumentation, context2, iBinder, iBinder2, activity, intent, Integer.valueOf(i));
                                                            } catch (Exception e2) {
                                                                throw exc2;
                                                            }
                                                        }
                                                    case 34798696:
                                                        str15 = "ۤۥۢۢۦۜۘۙۢ۫ۙۛۦ۟۠ۘۤۚۡۘ۬ۤۡۥۨۜۛۖۥۙۗۢ";
                                                        break;
                                                    case 352078255:
                                                        String str18 = "ۛۤۚۤۗۨۤۦۜۘۤۛۡۘۧۚۜۘۢۦ۬ۙۛۢۖ۠ۥۘۢۥۦۥۦ";
                                                        while (true) {
                                                            switch (str18.hashCode() ^ (-2002835864)) {
                                                                case -238385768:
                                                                    str18 = it.hasNext() ? "ۥۢۛ۟ۨۘۧ۠ۡۘۦۖ۠ۛ۫ۙۚۙۖۘۨۦۢۥۧ۟ۘۛۡۘۡۦۘۘۤ۟ۦۘۛۤۘۘ۠ۛۦ۟۫ۘۧ۫ۖۦۜۨۘۤۙۗۘۗۘۘ" : "۫ۘۗ۠ۧۥۨۤۗۧ۫ۜ۠ۜۦۡۧۛۨۜۢۘۚۚۡ۫ۘۦۤ۬ۙۥ۟ۡۛۖۘۦۙۤۡۚۡۘۜۗۥۘ۬ۗۦۘۡۨۦۡۥ";
                                                                case -97785291:
                                                                    str15 = "۫ۖۢ۟۬ۧۜ۠ۖۘۧۙ۟ۖ۠ۗۡۢۥۘۗۥ۬ۢ۟ۘۜ۟ۥۨ۬ۖ۬ۨۖۘۧۨۡۗۨۨۖۖ۟ۗۛۦۘۨۥۧۖۘۥۖۦۘ";
                                                                    break;
                                                                case 227381768:
                                                                    str15 = "ۧۡۖۘۧ۬ۨۘ۠ۧۡۚ۟ۗۖۜ۠ۛۖۘۤۨ۟۬۟ۜۘۧۨۢۨۘۜۘ۠ۨۡۘۨۙۙ";
                                                                    break;
                                                                case 304959259:
                                                                    str18 = "ۡۧۨۘۗۦۨۜۧۜۡۧۘۗۢۥۙۘۘۘ۟۠ۘ۫ۛۛۥۧۖۘۘ۟ۨۘۚۥۤ۬ۖۜ";
                                                            }
                                                        }
                                                        break;
                                                    case 639351290:
                                                        Method method2 = (Method) it.next();
                                                        try {
                                                            method2.setAccessible(true);
                                                            Class<?>[] parameterTypes2 = method2.getParameterTypes();
                                                            String str19 = "۫ۢۦۗۖۧۙۡۡۘ۠۠ۖۘۜۤۨۘۜۧۛ۟ۤ۟ۦۜۖۘۖۛۡۘۖۖۜۘۜۙۡۢ۟ۘۘۖۖۚۦۛ۬۬ۗ۟۫ۢۛ";
                                                            while (true) {
                                                                switch (str19.hashCode() ^ (-1376963044)) {
                                                                    case 20032670:
                                                                        String str20 = "۠ۘۖۨۥۘۥۘۨۘۚۖۗۧ۫ۥۘۘ۟ۨۘۜ۟۠۟ۜۜۘ۠۬ۡۘۚۖۙۨۗۘۘۢ۟ۚ";
                                                                        while (true) {
                                                                            switch (str20.hashCode() ^ (-1524314389)) {
                                                                                case -1876784778:
                                                                                    str20 = "ۦۙۨۦ۫ۨۘۛۧۘۘۘۧۥۘ۫ۛۦۘۚۥۘ۟ۖۨ۬ۛ۬ۛۢۚۘۤۥۘۜۤۨۢۤۧ۟ۥۥۘۘۤۡۥ۫ۛۡۖۘۢ۠ۖۘۤ۠ۥ";
                                                                                case 652493798:
                                                                                    str19 = "ۨ۟ۥۦۨۨۥۜۗۨۜۖ۠ۗۖ۟۫ۛۜۥۘۘۢ۟ۡۗۛۘۚۦ۫ۢۖ۟ۡۜۗۥۘ۬ۚۥۘ";
                                                                                    break;
                                                                                case 1251549820:
                                                                                    str19 = "ۢۦۗۛۡ۬۫۠ۜ۬ۢۤۨۛۖۘۜۘۨۡۨۦ۠ۙ۬ۚۨۛۦۧۡۘۜۡۜۡۥۚ";
                                                                                    break;
                                                                                case 1926181194:
                                                                                    str20 = parameterTypes2.length == 7 ? "۬ۦۜۘۗ۬ۥۘۢۦۙ۫۬ۖۘۙۥۛۗ۠ۦۘۚۙۗۨۛ۫ۙۤۗ۫ۗۛ۠ۜۢۧۚ۫ۘۡۚۜۨۘۡ۫ۥۘۛۥۤ" : "۫ۙۖۤۙۢۢۡۖۘۖۥۘۘۥۤۤ۟۬ۥۦۥ۬ۢۚۖۘۛ۬ۨۘۙ۬ۦۘۚۥ۫ۤ۬۫۟۫ۢ۬ۙ۫ۨۦۡۥۨۘۨۤۧۜۢۜ";
                                                                            }
                                                                        }
                                                                        break;
                                                                    case 94467229:
                                                                        String str21 = "ۦۖۖۘ۟ۖۨۥۖ۠ۗۛۤ۫ۤۡۘۢ۟ۢۤۧۥۡۥۛۦۜۦ۟ۨۘۛۧۚۛۡۨۘۥۚ۫۫ۤۖۧۘۡۘۖۡ۫";
                                                                        while (true) {
                                                                            switch (str21.hashCode() ^ (-669179570)) {
                                                                                case -1537990720:
                                                                                    str21 = "ۦۤۖۘۜۥۧۘۛۗۨۥۢۥ۠ۘۨۖۤۧۛۥۙۢۨۨۘۗۡ۟۫۫ۖۖۗۚۥۡۖۤ۫ۘۘۙۦۘۘۖۛۜۚ۫ۦۡۖۨ۫ۧۨ";
                                                                                case 755259325:
                                                                                    objArr = new Object[6];
                                                                                    objArr[0] = context2;
                                                                                    objArr[1] = iBinder;
                                                                                    objArr[2] = iBinder2;
                                                                                    objArr[3] = activity;
                                                                                    objArr[4] = intent;
                                                                                    objArr[5] = Integer.valueOf(i);
                                                                                    break;
                                                                                case 886554467:
                                                                                    String str22 = "ۨ۟ۙۜۗ۠ۧۜۡۤۛۥۘۡ۟ۖۘ۬ۘۥۜۤۦۦۧۖۨ۫ۥۨۘۙۘۛ۠ۙۡۡۖۘۗ۬ۗ۬ۡۧۘۧۜۨ۫ۦۡۚۧ۠";
                                                                                    while (true) {
                                                                                        switch (str22.hashCode() ^ 160473959) {
                                                                                            case -1131265699:
                                                                                                String str23 = "ۡ۟ۦۘ۟ۘۢۦۤۡ۬ۙۘۘۤ۟۠ۦ۬ۘۡۨۜۘۢۙۚۙۢۘۦۖۤ۠ۨۡۘ۬ۙۘۘۢۢۚۤۧ۟ۚ۫ۨ۟ۘ۟ۤۚ۠ۗ۟ۥۘ";
                                                                                                while (true) {
                                                                                                    switch (str23.hashCode() ^ (-851301869)) {
                                                                                                        case -1733042193:
                                                                                                            str22 = "ۧۚۨ۬ۧۚۤۛۖۘۗۧۢۢۚۡۡۤۧۤۨۤۛ۫۬ۚۦۘۘۚ۫۟ۧۘۧۘۚ۠ۜ";
                                                                                                            break;
                                                                                                        case -1131532483:
                                                                                                            str23 = "۬۠ۥۗۙۙۧۙ۠ۢۤۜۢۘۧۗ۬ۜۗۧۘۥۤۗۗۡۧۜۦۘۚۜۜۘۨ۬ۧ";
                                                                                                        case -487968972:
                                                                                                            str23 = parameterTypes2.length == 8 ? "ۗ۠ۨۨۜۦۚۤۢ۠۠ۚۡ۟ۘۘ۬۬ۗۖۘۦۡۘۤۜۢۤ۟ۗۜۙۗۢ۟" : "ۚۤۨۘۡۡۨۤۦۥۙۦۨۘۢ۬ۖ۠ۦۥۘۚۘۧۗۧۢۚۧۚۢۘۙۗۧۤۜۧۧۘۘۢۗ";
                                                                                                        case 2017293820:
                                                                                                            str22 = "ۙۚۜۘ۬ۧۢ۠ۧۗۖۦۘۘ۠ۨۚۙۨۦۥۨۛۗۘ۠ۤۢۤۜ۬ۗۥۗۛۙۙۥۘۘۜۢۤ۬۟";
                                                                                                            break;
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                            case 702080469:
                                                                                                break;
                                                                                            case 1387041115:
                                                                                                String str24 = "۠ۗۖۘۖ۬ۛۥۥۗۗۜۦۢ۫ۗۚۜۤۜۧۦۙۘۢۥ۠ۙۗۦۥۦۦۜۘۜ۟ۚۥۙۥۘۥ۠ۥۥۚ۟ۗۖۘ";
                                                                                                while (true) {
                                                                                                    switch (str24.hashCode() ^ (-204802998)) {
                                                                                                        case -1363430750:
                                                                                                            String str25 = "ۘۥ۟ۥۥۘ۫۫ۙۢ۬ۜۚۛۖۤۥۧۙۥۦ۫ۤۗۧۚۜۜۖۤۤۢۢۤۜۖۨ۬ۙۚ۠ۘ";
                                                                                                            while (true) {
                                                                                                                switch (str25.hashCode() ^ (-1479570773)) {
                                                                                                                    case 149886092:
                                                                                                                        str25 = "ۡ۬ۢۖۙۢۙۡۤۘۘۘۘ۫ۡۛۛۨۨۘۥۗۢۚۨ۟ۖۚۙۨ۠ۧۗۘۜۘۚۤۦۥ۟۠ۧۚ۠ۡۜ۠۬ۢ۬";
                                                                                                                    case 222171943:
                                                                                                                        String str26 = "ۢۛۧۥۘۤ۠ۖ۟ۨۥۥ۫ۘۘۧ۟۬ۤۧۢۤۗۥۘۘۛۨۨۧۛ۬۟ۡۢۖۨۚۗۥۜۖۘۜۜۦۘۙۡ۫";
                                                                                                                        while (true) {
                                                                                                                            switch (str26.hashCode() ^ 2123043811) {
                                                                                                                                case -1802712564:
                                                                                                                                    str25 = "ۡۡ۠ۛۚۡۘۗۡۘۘۙ۟ۡۘۛۚۜۘۧۜۛۙۥۘ۠ۧۥۘۡۜۜۘۜۜۖۘۦۨۦۘ۠۠ۖۘ";
                                                                                                                                    break;
                                                                                                                                case -943110265:
                                                                                                                                    str26 = objInvoke2 != null ? "ۚۚ۠۫ۜ۬۠ۦۜۛۤ۫ۥۥۗۛۤۗ۠ۗۡۖۘۘ۫ۘۥۤۚۘۘ" : "ۥۧۦۢۙ۠ۦۨۖۘۜۨۡۘۖۤۛۧۙۧ۠۠ۤ۠ۦۥۨۧۘۨۛۛۧ۬ۦۘ۫ۥۦۛۙۡۡۡۚ";
                                                                                                                                case 998328525:
                                                                                                                                    str25 = "۬ۥ۫ۚۧ۠ۡۙۗۖۙۘۤۗۨۖۢۖۡۡۜ۟ۢۛۦۛۢۜۛۜ";
                                                                                                                                    break;
                                                                                                                                case 1981520039:
                                                                                                                                    str26 = "۫ۦۥۜۛۡۥۨۘۛۥۛۛ۠ۛۤۖۢ۬ۨۙۨ۠ۘۤۛۡۨۘۘ";
                                                                                                                            }
                                                                                                                        }
                                                                                                                        break;
                                                                                                                    case 1033936837:
                                                                                                                        String str27 = "۫ۚۧ۠ۘۨۧۙۚۜۖۤۧ۬۫ۧۜ۫ۧ۟۬ۦۜۦۘ۬ۙۥۘۜۢۡۦۚۖۘۙۘۥۘۗۜۛ۬ۙۥ";
                                                                                                                        while (true) {
                                                                                                                            switch (str27.hashCode() ^ 147394221) {
                                                                                                                                case -1629630465:
                                                                                                                                    break;
                                                                                                                                case -848447582:
                                                                                                                                    obj = objInvoke2;
                                                                                                                                    break;
                                                                                                                                case 158723167:
                                                                                                                                    str27 = "ۨۢۥۘۡۦ۫ۖۦۜۡۙ۟ۖۢ۫ۦ۟ۜۢۨۥۘۗۢۛۘۨ۬ۥۨۡۘ۫ۜۘۨۦۙۛۦ۬ۥۛۧۜۙ۟۬ۢۦۘ";
                                                                                                                                case 1701214174:
                                                                                                                                    String str28 = "۠ۢۜۘۢۡۗ۫ۗۘۘۨۜۖ۠۬ۦۘۨۥۛۤ۫ۨۘ۫ۨۜۤ۫۠ۧۗۚۚۦۘۙۖ۠ۡۗ۬ۛۧۖۘۧۚ۬۬ۦ۫";
                                                                                                                                    while (true) {
                                                                                                                                        switch (str28.hashCode() ^ (-1682990009)) {
                                                                                                                                            case -831205893:
                                                                                                                                                str27 = "ۗۙۛۤۨۢۨ۟ۜۘۖۙۧۢۥۧۘۘۙۗۖۚۖۥۦ۬ۚ۫ۖۘۛ۟ۥۜۤۥۘۨۥۚۨۙۚۡ۬۟ۛۖۨۘۙۡۥۘ۬ۘۡۘۥ۠";
                                                                                                                                                break;
                                                                                                                                            case 871293054:
                                                                                                                                                str28 = "ۛۘۡۡ۬ۖۘۤۖۚۖۜۦۥۧۦۘ۫ۤۗۧۧۤۢۘۖۢۘۡۙۦۗ";
                                                                                                                                            case 1235932087:
                                                                                                                                                str28 = parameterTypes2[7].isInstance(objInvoke2) ? "ۛۧۧۧ۬ۨۦۗۡۘ۫ۚۘۨۛۦۘ۠ۡۡ۟ۜۜۘ۫ۛۦ۟ۗۜۘۨۛۛ" : "ۗۘۧۘۢۘۛۧۚۥۢۙۧۛ۫ۡۘۦۗۛۧۡۘۖۘۧ۬ۧۜۘۧۚۚۙۙۥۗ۠ۡۘۥۥۘۢۗۚۘۥۨۚۛۡ";
                                                                                                                                            case 1739489351:
                                                                                                                                                str27 = "۟۫ۡ۬ۘۘۤۤۛ۫ۘۜۤ۫۟۠ۙۡ۫ۜۘۡۗۛۖۘۤۤ۬ۧۨ۠۠ۦۢۢۥۦۧۘۤۤۘ";
                                                                                                                                                break;
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    break;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        break;
                                                                                                                    case 1311444243:
                                                                                                                        break;
                                                                                                                }
                                                                                                            }
                                                                                                            parameterTypes2[7].isPrimitive();
                                                                                                            obj = null;
                                                                                                            break;
                                                                                                        case 305799019:
                                                                                                            String str29 = "ۜۤۥۘۜۨۛۧۢۖۚۧ۟ۡۚۚۦۛۖۚ۬۬ۡۜۘ۫ۥۧۘ۟ۢۥ";
                                                                                                            while (true) {
                                                                                                                switch (str29.hashCode() ^ 1590572605) {
                                                                                                                    case 855204665:
                                                                                                                        str29 = "ۗۧۘ۫ۖ۠۠ۚۢ۟ۗۙۜ۫ۖۛۥۛۤۨۡۘ۫ۗۚۥۚۖ۠ۥۡۘۦۤ۫ۧ۠";
                                                                                                                    case 1858338228:
                                                                                                                        str29 = Bundle.class.isAssignableFrom(parameterTypes2[7]) ? "ۦۚۦ۟ۙۜ۬ۢۤۛ۬ۙۖۢۘۗۡۚۗۦۘۢۚ۫ۙۧۥۗۗۧۜۙۦۘۜۥ۫ۙۗۚ۟ۤۚ" : "۬ۥۗۤۨۡۘۖۜ۬ۛۧۧۛ۠ۨ۬ۜ۫ۥۘ۫ۖ۫ۧ۟ۙ۫ۛۦ";
                                                                                                                    case 2040911457:
                                                                                                                        str24 = "ۜۧۗۢ۫ۤۧ۠ۨۘ۟ۢ۬ۘۚۗۦۦۥۘۜۛۘۘۥۘۡۘۥۗۧۘۜۤ۠ۦۙۢ۠۫ۥ۫ۦۥۢۘۘۜۨ۬ۛۤۙ";
                                                                                                                        break;
                                                                                                                    case 2086261905:
                                                                                                                        str24 = "۫ۧۥۢ۫۠ۙۧۖۤۥۛۛۥۗ۠ۗۢ۫ۗۖۥ۬ۙۘۜۙۢۚۡۜۡۘۙ۟ۨۙۡۦۘۤۦۜ";
                                                                                                                        break;
                                                                                                                }
                                                                                                            }
                                                                                                            break;
                                                                                                        case 558413533:
                                                                                                            str24 = "ۦۛۧۧ۠ۖۜۡ۟۫۬۠ۛۥۘۥۦۧۜۨۚۛ۫۠ۚۜۘۨۘۖ";
                                                                                                        case 1028162960:
                                                                                                            obj = bundle;
                                                                                                            break;
                                                                                                    }
                                                                                                }
                                                                                                try {
                                                                                                    objArr = new Object[]{context2, iBinder, iBinder2, activity, intent, Integer.valueOf(i), null, obj};
                                                                                                    break;
                                                                                                } catch (Exception e3) {
                                                                                                    e = e3;
                                                                                                    String str30 = "ۢۦ۫ۙۚۖۜۨۥۘۤۥۘۥۚۜۙۧۡۘۢۖۥۖۖۗۢۛۡۘۛۚۛۤ۟ۡۡۤۥۦۜۨ۬ۛ۠ۧۛۥۡۤۗ";
                                                                                                    while (true) {
                                                                                                        switch (str30.hashCode() ^ 917329666) {
                                                                                                            case -773671930:
                                                                                                                break;
                                                                                                            case 325548850:
                                                                                                                exc = e;
                                                                                                                continue;
                                                                                                            case 1379529440:
                                                                                                                String str31 = "۟۬ۤ۠ۡۨۜۚۙۦۚۨۙۤ۫۫۫ۙ۬ۛ۠۠۬ۡۘۢۛۦۘ۬۟ۢۢۤۡۘۜۨۜۢۘۚ۫ۤ";
                                                                                                                while (true) {
                                                                                                                    switch (str31.hashCode() ^ 2063999910) {
                                                                                                                        case -1526850859:
                                                                                                                            str30 = "ۛۘۨۤۨۚ۠۠۟ۨۨۧۜ۟ۗۨۙۧۘۜ۫ۗۖۢ۟ۡ۠۠ۨۘۘۡۦۘۤۡۢ۠۟۬ۤ۬۫ۖ۬ۘۘۡۡ۫۬ۖۡۘۚۡۦۘ";
                                                                                                                            break;
                                                                                                                        case -737229965:
                                                                                                                            str30 = "۠ۛۖۛۤۚ۟ۜۧۡۢۢۛۧۦۘۡۥۜۙۗۜۘۘۖۦۘۢ۠۠ۧۦۛۖۧۧۚۢ۠ۡۢۗ۫ۗۥۘۨۜۧۨ۟ۜۘ";
                                                                                                                            break;
                                                                                                                        case 760710744:
                                                                                                                            str31 = "ۡۧ۠ۦۦۚۚۘ۟ۡۙۥۘۥۚۤ۬ۤۘۘۙۧۚۗۨۢۦۜۦۢۡۧۘ۠۟۠ۘ۠ۚۤۤۘۖۢۘۘ";
                                                                                                                        case 1105386974:
                                                                                                                            str31 = exc == null ? "ۖۘۢۜۤۘۢ۟۫ۛۛۥۚۨۦۘۖۜ۫ۘۧۥۘۛۛۡۘۛۡ۫ۖۘ۫ۦۘۤۘۚۥۘ۫ۚۜ۟۬۬" : "۫ۦۖۘۗۙۡۙۡۥۖۢۡۨ۠ۥۖ۬۬ۙۤۛ۠ۤۦۛۢۢۨۛۜ۟ۙۜۘۗ۟ۤۨۥۜ۫۫ۨۘ۬۟ۡۥۘ";
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            case 1596042128:
                                                                                                                str30 = "ۡ۟ۜۥۡۤۙ۠ۘۘۡۚ۬ۗۖ۬۫ۤۖ۫ۖۦۘ۠۟ۦ۬ۘۡۚۗۜۘۥۢۧۥ۟ۗۤۤۤۧۥۖۜ۬ۥۗ۠ۜۘۚ۫ۖۘۚۛۡ";
                                                                                                                break;
                                                                                                        }
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                            case 2096938079:
                                                                                                str22 = "ۦۦۘۘۛۗۖۜۦۜۨۖۦۘ۠۠ۤ۬ۚۤۤۜۜۗ۫ۜۘۚۨۧ۫ۜۛۧ۠ۗ۠ۦۥ";
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case 1809422984:
                                                                                    String str32 = "ۥۢ۬۠۟ۘۢۢۨۘۚۨۥۥۚۘۘ۠ۤۗۢ۬۠۟۠۬ۘۨۗۤۖۜۘۤۡۢۨۤۘۘۗ۫ۛ۟ۢ۫ۦ۬ۤ۟";
                                                                                    while (true) {
                                                                                        switch (str32.hashCode() ^ (-1753235791)) {
                                                                                            case -1887528829:
                                                                                                str21 = "ۦۚۚۤۜۡۙۜۧۙ۬ۡۤۡۥ۫ۘۘۧۢ۫۬ۚۦۘۖ۟ۖۘۚۖ۫ۖۛۖۖۛۦۘۘۦۧۙۛۤ";
                                                                                                break;
                                                                                            case -978490986:
                                                                                                str32 = "ۘۚۜۘۖۢۥۘۛ۟۬ۗۜۜۧۦ۬ۖۥۖ۠ۧۙۤۦۥۡۛۖۘۘۥۖۘ";
                                                                                            case 153795861:
                                                                                                str32 = parameterTypes2.length == 6 ? "ۤ۟ۦۘۤۥۧۘ۠ۨۚۨ۬۫۫ۡۡۘۖۙۦۘۗۛۗ۬۠ۛۚۜۡۘۜۨۜ۠ۨ۟۬ۢۖۛۘۥۘۢۧۜۘ" : "ۖۙۦۘ۠۬ۚۜۢۤ۬ۚۦۘۡۦۘۘ۠ۖۦۘۗۤۜۘۨۛۘۚۚۗۘۜ۬ۡۡۤۤ۬ۜۘۗۜۡۘۢۚۘۛۘۡۘۧ۬ۢ";
                                                                                            case 1111903891:
                                                                                                str21 = "۠ۙ۠ۤ۠ۜۘۦۖۨۛ۫ۡۘۙ۠ۨۘۧۦۚۧۛۨ۟ۛۨۨۢۨ۫ۥۘۧ۫ۜۛۡۥۢۜۜۛۨۛۙۨۛۤۨۜۜۧۜ۫ۗۜۘ";
                                                                                                break;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case 236643433:
                                                                        String str33 = "ۦۛۘۘ۫ۗۖۘۙۗۢۨۘ۟ۦۛ۟ۨۙۗۧۘۥۜ۬۟ۚۙۦۦۦۚۥۦۡۘۡۘۢ";
                                                                        while (true) {
                                                                            switch (str33.hashCode() ^ 1910949142) {
                                                                                case -859927955:
                                                                                    String str34 = "ۨۖۥۘۗۛۜۛۢۜ۟۬ۦۢۘۜۢۖۜۢۡۜۘۗۛۛۙ۫ۧۘ۬ۚۚۜ۟ۡ۠ۡۖۥۜۘۦۢۨۘۘ۟ۦۤۨۨ";
                                                                                    while (true) {
                                                                                        switch (str34.hashCode() ^ (-1179788553)) {
                                                                                            case -1970948719:
                                                                                                str34 = "ۥۖۖۘۧ۠ۡۛۧۧ۟ۗۦۘۚ۬ۨۜۦۢ۬ۦۚۥۗۨۘۨۛۘۘ۟ۘۗۘۗۛۧۧۢۛۢۡ۬ۦۡ۫ۙۘۛۨۘ";
                                                                                            case -1956566938:
                                                                                                str34 = Bundle.class.isAssignableFrom(parameterTypes2[6]) ? "ۡۗۜۘۥ۫ۤۤ۟ۡۘ۠ۢۢۜۖۘۦۧۜۛ۠ۘ۠ۛۦۧۙ۫ۨۨۜۘۜۚۚۨۜۘ" : "۠ۦۨۘۥۥۜۦۘۘۗۙۖۘۖۖۦۢۚۡۘۢ۠ۧۡۤۙۥۘۖۘۧۤۥۘۨۜ۫ۜ۬ۤۥۡۨ۠۫ۨۛۙۡۘ۟ۤۡۘ";
                                                                                            case -812636715:
                                                                                                str33 = "۠ۙۙۥ۫۠ۙۖۧۘۦۢۦۘ۫ۘۥۛۜ۠۠ۦۗۧۙۡۚۜ۫ۧ۫ۤۗۢۡۤ۬ۧ۫۠ۘۜۤۛۨۘۡ۬";
                                                                                                break;
                                                                                            case 1937284952:
                                                                                                str33 = "ۙۡۘۘۖۙۡۨ۫ۨۨۛۜ۬ۥۙۡۗۚۙۘۨۘۤۤۥۘ۠۠۟ۙۢ";
                                                                                                break;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case 63606160:
                                                                                    String str35 = "ۤۚ۟ۦۧۙ۫ۡۨۘ۠ۤۡۘ۠ۧۙ۬۠۬۫۟۫۫ۥۜ۟ۧۡۘۙ۬۠ۖۥۥۚۤۧ";
                                                                                    while (true) {
                                                                                        switch (str35.hashCode() ^ (-1544097008)) {
                                                                                            case -1613513532:
                                                                                                String str36 = "ۦ۠۟ۥۘۙۜۤۘۛۘۨۧۢۡۘۖۘ۫ۢۜۘۘ۬ۚۖۘۥۢۨۗۨۥۡۗ۠۬ۙۨۗۘ۟ۗۙ۠ۘۗۖۡۥۘ";
                                                                                                while (true) {
                                                                                                    switch (str36.hashCode() ^ 1305939273) {
                                                                                                        case -911593194:
                                                                                                            str36 = "ۤۥۜۚۤۖۨ۬ۜۧۙۘۧۥۨۗۚۚ۠ۖۡۘۢ۠ۦۜۘۙ۫ۥۖۚۜۘۡ۠ۡ۫ۡۖۘ۠۬۫";
                                                                                                        case 347338737:
                                                                                                            str35 = "ۦۜۢ۟ۢ۬۠ۥۤۚ۫ۚۘۡۖۚۖۧۚۢۖ۬ۢۨۧۗۦۧۖۡۘۤۗۜۘۙ۬ۤۛۧۘۘۡ۬۟";
                                                                                                            break;
                                                                                                        case 1014219940:
                                                                                                            str35 = "ۤ۬۟۠ۙۡۘ۬۫ۚ۫۠ۘۘۤ۬ۦۘۜۙۙ۬ۛ۫۫۟ۚۘۖۖۘۚۜۥۢۡۦۘۚۦۘۘۘ۠ۜۛۘۢ۟ۡ۟ۥ۠ۖۘ";
                                                                                                            break;
                                                                                                        case 1440065717:
                                                                                                            str36 = objInvoke2 != null ? "ۖۗۡۢۚۙۙۥۡ۟ۨ۫ۚۙۗ۫ۢۘۡۢ۬ۖ۟ۤۛۧۦۘۨۧ۟۠ۜۖۘۛۗۨ۬ۜ۫۫۟ۡۜۥۙ۬۠" : "ۜ۫۠۟ۛۛۡ۠ۨۘ۠۠ۨۦۦۥۘۖۨۥۘۙۙۦۢۜۙۦۤۜۙۡۢ";
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                            case -1183765284:
                                                                                                str35 = "ۦ۬۟ۦۙۜۘۛ۬ۥۚۤۜ۠۠ۡۥۤ۟ۡ۠۠ۘۦۛۢۨۘۘ۠۬ۙۚۗ۟ۘۘۦۘۚۢ۠ۨۘ";
                                                                                            case 1299954217:
                                                                                                break;
                                                                                            case 1348054170:
                                                                                                String str37 = "ۡۘۧۖۨۥۘۢۢ۫۟ۤۘۘۥۦۖۘۥۖۘۜۜۢۡۨۗۖۙۡۘۚۤۜۘۦۡۧۡۧۘۘ";
                                                                                                while (true) {
                                                                                                    switch (str37.hashCode() ^ 1503972315) {
                                                                                                        case -218312054:
                                                                                                            str37 = "ۧ۠ۚۦۥۤۖۥۥۘۤۖۦۘۥۜۘۛۡ۟ۢۦۘۘ۟ۗۙۤۧۨۦۢۦۥۧۗۙ۬۬۬۫ۘۘ۠ۛۘۤ۠ۘۗ۫ۜ";
                                                                                                        case 56611453:
                                                                                                            objArr = new Object[7];
                                                                                                            objArr[0] = context2;
                                                                                                            objArr[1] = iBinder;
                                                                                                            objArr[2] = iBinder2;
                                                                                                            objArr[3] = activity;
                                                                                                            objArr[4] = intent;
                                                                                                            objArr[5] = Integer.valueOf(i);
                                                                                                            objArr[6] = objInvoke2;
                                                                                                            break;
                                                                                                        case 1815286654:
                                                                                                            break;
                                                                                                        case 1880215680:
                                                                                                            String str38 = "ۥۤۖۖۙۛۧۚۘ۠ۤۢۢۜۥۚۥۧۤۥۙ۬ۧۗۨۜۜۚۨ۟۬ۚۖۗۦۤ";
                                                                                                            while (true) {
                                                                                                                switch (str38.hashCode() ^ 137635514) {
                                                                                                                    case -953653840:
                                                                                                                        str38 = "ۘۢۦۡۥۡۘۥ۠۟ۖۨۙۡۥۥۡۥۛۘۡ۬ۢ۫ۙ۫ۤۨ۠ۧۘۦۧۘۙۦۗ۬۬ۡۢۥۢ";
                                                                                                                    case 51357855:
                                                                                                                        str38 = parameterTypes2[6].isInstance(objInvoke2) ? "۫ۨۦۡۡۙۤۨۧۘۨۨۦۥۥۥۦۢۦۥۗۦ۫ۧۧۦۚۘۘۧۘ۟۬ۦۚۡۨ" : "ۘۙۥۛۤۘۘۖۡۡ۟ۨۗ۫ۨۨۥ۟ۥۘۡۦۗۚۢۧۤۥۖۘۨۤۖۘۥۦۜۜۧۖۜۜ۫ۦۨۨ۟ۤ۫ۖ۬";
                                                                                                                    case 484695114:
                                                                                                                        str37 = "ۖ۫ۥۘۦۡۜۘۙ۬ۤۥۗۧۤۛۨۘ۟ۧۗۨۥ۬ۜۥ۬ۡۖۙۘ۫ۜۘۜۚۡۜۜۥۤۢۨ۫۠ۖۘ";
                                                                                                                        break;
                                                                                                                    case 1839267082:
                                                                                                                        str37 = "ۙۛۥۘۥ۬۫۬ۨۛۚۘۘۘۧۛۥۥۗۜۘۢۢ۟۫ۚ۫۟ۦۚۦۨۘ";
                                                                                                                        break;
                                                                                                                }
                                                                                                            }
                                                                                                            break;
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case 408911678:
                                                                                    str33 = "ۢۘۖۖۗۡ۠ۥۡۢ۬ۘۘۜۗۜۘۦ۬ۙۛۡۚ۫ۥۚۦۗۧۤۦۖۧۙۦۘۡۙۘ۫ۚ۟ۗۤۡ";
                                                                                case 860017366:
                                                                                    objArr = new Object[7];
                                                                                    objArr[0] = context2;
                                                                                    objArr[1] = iBinder;
                                                                                    objArr[2] = iBinder2;
                                                                                    objArr[3] = activity;
                                                                                    objArr[4] = intent;
                                                                                    objArr[5] = Integer.valueOf(i);
                                                                                    objArr[6] = bundle;
                                                                                    break;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case 2082724591:
                                                                        str19 = "ۤۜۧۗ۠ۚۨۦۦۖۢ۫ۚۡۧۛ۫ۘۡ۬ۙ۟ۛۦۡۤۜۖۘۡۘ";
                                                                }
                                                            }
                                                        } catch (Exception e4) {
                                                            e = e4;
                                                        }
                                                        break;
                                                }
                                            }
                                        }
                                        break;
                                }
                            }
                        }
                    }

                    private void logIntentDetails(String str, Intent intent) {
                        Bundle extras;
                        String str2;
                        String strDecrypt;
                        try {
                            StringBuilder sb = new StringBuilder(str);
                            sb.append("\n- component: ");
                            sb.append(intent.getComponent());
                            sb.append("\n- action: ");
                            sb.append(intent.getAction());
                            sb.append("\n- data: ");
                            sb.append(intent.getData());
                            sb.append("\n- type: ");
                            sb.append(intent.getType());
                            sb.append("\n- pkg: ");
                            sb.append(intent.getPackage());
                            sb.append("\n- flags: 0x");
                            sb.append(Integer.toHexString(intent.getFlags()));
                            Set<String> categories = intent.getCategories();
                            String str3 = "ۥۗۘۧۙۥۧ۟ۜۦ۫۬ۙۗۗۗۨۤۤۦۘۢۦۗۨۧۨ۠ۗۖ";
                            while (true) {
                                switch (str3.hashCode() ^ (-1554430366)) {
                                    case -1931241665:
                                        String str4 = "۠۬ۘۘۚۢۛۥۦۤۡۧۜۖ۬ۧۤۚۖۢۚۖۛۜۦۨۦۘۙۗۜۚۜۥۘ۟ۢۚ۟ۨۨ۠۫ۘۥۜ۬ۚ۫ۚۛ۫ۙۚۥۙ";
                                        while (true) {
                                            switch (str4.hashCode() ^ (-1372189997)) {
                                                case -1619664946:
                                                    break;
                                                case -628592288:
                                                    str4 = "ۙۘ۠ۢۤۖ۫ۚ۫ۗۜۧۘۙۜۧۘۗۦۗ۟ۜ۬ۡۘۨ۠ۛۛۥۦۥۢۥۘۨۦۖۢۘۢۢ۠۠ۛۖۙۜۚۥۥ۠ۛ۟ۚۘۘ";
                                                    continue;
                                                case -372735578:
                                                    String str5 = "۫ۛۨۘۜ۟ۖۤۦ۫ۨ۠ۡ۠ۡۜ۟ۗۙ۟ۧۘ۟۠ۦۖۦۗۙۗۥۚ۟ۛۢۘۨۛ۠ۛۧۥۨۘۙۜۡۦ۟ۛ";
                                                    while (true) {
                                                        switch (str5.hashCode() ^ (-1126991399)) {
                                                            case -1867121514:
                                                                str5 = "ۘۛۜۦۖۘۤ۠ۗ۟۫ۙۗۜۧۘۦۘۖۘ۠ۛۢۧۗۘۢ۟ۘۘۦ۫ۛ۟ۛۖۘۢ۫۠ۢ۫ۦ۠ۧۥ";
                                                                break;
                                                            case -684788460:
                                                                str4 = "ۛۙۚۚۥ۟ۨۨۡۘ۫۠ۚۥۖۥۖۛۦۨۨۖۘۗ۟ۦۘۗ۠ۡۙ۫ۘۘۢۜۤۙۢۧۥۥۡۢ۟۠ۡۛۦۘ۫ۧۜ";
                                                                continue;
                                                                continue;
                                                            case -87798809:
                                                                if (!categories.isEmpty()) {
                                                                    str5 = "ۦۚ۠ۘۡۨۡ۠ۜۘ۬۠ۖۘۦۜۖۦۥۖ۟۬ۘۧ۟ۢ۬ۗۡۘ۬ۚۡۘۙۙۘۛ۬ۙ۫ۦۜۘۦ۟ۧۚۨۚۚۦ۫ۧۢۙۜۖۨۘ";
                                                                    break;
                                                                } else {
                                                                    str5 = "ۢ۟ۢۙۨۡۙۙۤ۫۟ۜۥۘۦۨۜۘۘ۬ۘۗ۟ۦۜۘۢۚۡۨۙۤ";
                                                                    break;
                                                                }
                                                            case 1177245595:
                                                                str4 = "۟۫ۙۜۧۨۘۡۛۛۗ۫ۥۘ۬ۡۡ۬ۙۚۡۘ۟ۘۤۛۧۙۘۡۚ۬ۘ۫ۙۥۘۘۦۖۡۘۦۥۘۛۦۚ۬ۦۨۘۡۡۧ۫ۜۚ";
                                                                continue;
                                                        }
                                                    }
                                                    break;
                                                case -91662451:
                                                    sb.append("\n- categories: ");
                                                    sb.append(categories);
                                                    break;
                                                default:
                                                    continue;
                                            }
                                        }
                                        break;
                                    case 749132991:
                                        String str6 = "ۤۘۧۘۥۙۧۥۘۘۘۡۡ۬ۗۧۛۗ۟ۢۜ۟۫ۧۖۨۘۡۛۜۘۚۡۨۘۙۖۧۘۤ۬ۦۖۜۥۘۤ۫ۜ";
                                        while (true) {
                                            switch (str6.hashCode() ^ 910841506) {
                                                case -1505988866:
                                                    str6 = "ۗۛۢۥ۠ۤۗۢ۟۫ۡۖۡ۬ۗ۫ۨۘۗۨۧۖ۬ۨۜۥ۟ۤۡۧۦۗۛۘۘۥۘۨۢۨۨۛۡۖ۠ۦ";
                                                    break;
                                                case -282575657:
                                                    str3 = "ۙ۫ۨۘۥۘ۟ۡۢ۠ۛۢۦۙۧۚۘۦۥۥۗ۟۟ۙ۬ۧۧۘۘۖۗۘۘ۟ۧۙۙ۟ۧ۬۬ۦ۫ۜۥۘ";
                                                    continue;
                                                    continue;
                                                case -84360122:
                                                    str3 = "ۧۖۥۗ۠ۡۢۤ۠ۡۤۥۘۡۚۜۘۤۤۢۚۨۜۘۗۘۨۘۚۜۘۢۙۥۦۨ۟ۧۤ۟ۗۥۖۧۥ";
                                                    continue;
                                                case 924215588:
                                                    if (categories == null) {
                                                        str6 = "ۘۨۚۨۗۦۛۢۘۜ۟ۡۘۜ۫ۜۥۖۖ۬ۛۘۢۛۘۥۗۧۨۡۖۘ";
                                                        break;
                                                    } else {
                                                        str6 = "ۨۨۖۘۤۥۛ۬ۥ۫ۘۢ۠ۢۤۤۧۦ۠۬۫ۜۘۚۡ۠ۢۜۗۥ۟ۜۘۘۧۦۘۘۜۦۘۨۤۜۘۙۨۡۘۡۚۢۜۤۨ";
                                                        break;
                                                    }
                                            }
                                        }
                                        break;
                                    case 1196702097:
                                        break;
                                    case 1746207357:
                                        str3 = "ۥ۫ۖۤ۫ۦۘ۠ۥۨ۠ۘۛۙۘۡ۬ۦۧۙۖۜۘ۬ۙۢ۟ۗۖۤ۟ۢ۫ۧۦۘ۫ۤۦۘ۬ۚۡۤ۫ۤۚۜۜۚ۬ۚ۠ۤۦۘ۟ۥۛ";
                                        continue;
                                    default:
                                        continue;
                                }
                            }
                            k2.logToFloatingWindow(sb.toString(), "debug");
                            extras = intent.getExtras();
                            str2 = "ۢۙۤۡ۬ۜ۫ۗۘۘۤۦۗ۫ۚ۟ۘۥ۟ۦۙۥۥۖۘۙۖۛ۬ۙۚۢۜ۫ۢ۬ۨۘ";
                        } catch (Throwable th) {
                            k2.logToFloatingWindow("【打印 Intent 详情异常】" + th.getMessage(), "error");
                            return;
                        }
                        while (true) {
                            switch (str2.hashCode() ^ 1805187307) {
                                case -1551152252:
                                    return;
                                case -1457364600:
                                    String str7 = "ۤۚۛۡۗ۠ۦ۫ۨۘ۠ۙۥۘ۬ۘۙۗۖۧ۫۫ۢۗۙ۟۟ۥۨۡۙۖۧۨۦۜۦ۠ۡۜۘ۠ۛۛ";
                                    while (true) {
                                        switch (str7.hashCode() ^ 295313279) {
                                            case -1967371564:
                                                return;
                                            case 972170899:
                                                String str8 = "۟۠۬ۡۛۗۖ۟ۦ۟ۥۘ۬ۙۤۡۖۦۘۘۡۡۘۚۡۧۜۜۦۘ۠ۨۛۦ۬ۦۘۜۤۙۖۚۚ۟۬ۡۛۤۨۥۦۖۢۡۚۥۗ";
                                                while (true) {
                                                    switch (str8.hashCode() ^ 603297917) {
                                                        case -562726532:
                                                            str7 = "ۢۗۜ۫ۧۙۚۖۢۦ۫ۡۘۢۛۛ۬ۨۡۘۢۥ۫ۖۦۘۢۥ۠ۛۦۨۢ۟ۦۘۥۜۡۦۥۘۥۖۧۛ۟ۦۙۢ۟ۧ۬ۗۨۛۜ";
                                                            continue;
                                                        case 189569541:
                                                            if (!extras.isEmpty()) {
                                                                str8 = "ۙ۫۬ۨۤۡ۠۬ۡۘۛۙۛۧۢۧۜۦۗۖۛۚ۬ۦۘ۫ۖۘۘۚ۟ۨۖۢ۟ۙ۠ۡۤ۟ۨ۬ۨۡۥۛ۬ۡۙۧ";
                                                                break;
                                                            } else {
                                                                str8 = "۫ۖۧ۫۬ۦۘۦۧۘۡۗۦۢۚ۟۬ۛۡۘۨۧۘۘۨۢۥۘۙۨ۫۬ۗۘۘۡۥ۠ۥۤ۠ۜۢ۟۟ۧۤ";
                                                                break;
                                                            }
                                                        case 401635235:
                                                            str7 = "ۥۛ۠۠۬۟ۧۘۖۜ۫ۜۥۜۘۘۛۤۥ۠ۥۧۘۚۤۘۨ۫۠ۢۤۖۘۛۤۧۢ۬";
                                                            continue;
                                                        case 1807853002:
                                                            str8 = "ۦۚۜۘ۫ۦۖۘۘۡۥۡۜۡۙۨۤۚۗ۟۬ۙ۬ۡۘۧۘۡۚۨ۟ۨۖ";
                                                            break;
                                                    }
                                                }
                                                break;
                                            case 1571835219:
                                                StringBuilder sb2 = new StringBuilder("【跳转参数】共 " + extras.size() + " 项：");
                                                Iterator<String> it = extras.keySet().iterator();
                                                while (true) {
                                                    String str9 = "ۜۤۙۦۘۡۘۙۦۗۚ۟ۙۚ۬ۖۛ۠ۧۧۥۘۜۦۘۘۚۗۥ۬ۜۧ";
                                                    while (true) {
                                                        switch (str9.hashCode() ^ 1315288193) {
                                                            case -1443222906:
                                                                String next = it.next();
                                                                try {
                                                                    Object obj = extras.get(next);
                                                                    String str10 = "ۖۚۘۘۧۦۛۧۘۢۜۧۤۨ۟ۢ۟ۨۧۨ۫ۙۚ۬ۜۘۛۨۜۘۥۗۦۘۖۡۡۤۙۜۖۡۧۘۥۛۦۘ";
                                                                    while (true) {
                                                                        switch (str10.hashCode() ^ (-1016606327)) {
                                                                            case -340879942:
                                                                                strDecrypt = "null";
                                                                                break;
                                                                            case 252833754:
                                                                                str10 = "۫۬ۨ۫ۤۡۘۚۗۨۧۡۜۥۙۡۘۡۙۨۘ۫ۚۘۘۖۦۗۦۥۦ۟ۖۥۘۥۢۢۛۜۗ۫ۖ۟ۘۧ";
                                                                            case 860764826:
                                                                                String str11 = "ۘۢۡۘ۠ۖۘۘ۫ۢۨ۫ۗۨۘۛۖۧۚۙۧۚ۬ۖۘۢۢۜۦ۟ۢ۫ۜۦۗۛۜۘۤۨۧ۠۟ۖۧۘ۟";
                                                                                while (true) {
                                                                                    switch (str11.hashCode() ^ 1127627505) {
                                                                                        case -854005856:
                                                                                            str11 = "ۥ۫ۜۘۧ۠ۤۥۧۘۛۚۨۘۧ۠ۦۦۖۧۡۢ۬ۥۧۖۢ۠ۖۙۢ۬ۢۙۨۘۗ۫ۨۢۜۥ۠ۧۥ";
                                                                                        case 317083800:
                                                                                            str10 = "۟۬ۡۘۛۚۡ۠ۗۜ۫ۜ۠ۧ۟ۧۨۨۘۘۥۦۦۘۙۥۧۘۦۜ۬ۙۦ۟ۖۨۘۗ۫ۚ";
                                                                                            break;
                                                                                        case 450470825:
                                                                                            str11 = obj != null ? "ۙۥۙۗۜۡۘۙۦ۫ۧۨۘۦ۫ۚ۟ۘۡ۟۬ۦۘۘۚۥۘۙۥۢۧ۠ۦۤۖۗۧۤۡۘۤۚۘۘۡ۬۫" : "۫ۧۖ۟ۡۡۘۙۗۥۡ۫۠۬۠ۖۘ۬ۗۗ۬ۜۘۧۢ۠ۘ۟ۘۘۢۖۗۢۤۜۘ۠ۦۦۘۢۡۖ۬۬ۥۘ";
                                                                                        case 1779725271:
                                                                                            str10 = "ۗۥۗۨۧۜۘ۟ۖۙۧۡۡۡ۫ۜۗۗۘۧۗۡۗۥۤۘۥۦۦ۟۟ۤ۠ۧ۬ۗۨ";
                                                                                            break;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case 1643038865:
                                                                                strDecrypt = obj.getClass().getSimpleName();
                                                                                break;
                                                                        }
                                                                        return;
                                                                    }
                                                                    sb2.append("\n- ");
                                                                    sb2.append(next);
                                                                    sb2.append(" (");
                                                                    sb2.append(strDecrypt);
                                                                    sb2.append(") = ");
                                                                    sb2.append(String.valueOf(obj));
                                                                } catch (Throwable th2) {
                                                                    sb2.append("\n- ");
                                                                    sb2.append(next);
                                                                    sb2.append(" = <读取失败: ");
                                                                    sb2.append(th2.getClass().getSimpleName());
                                                                    sb2.append(">");
                                                                }
                                                                break;
                                                            case -937983856:
                                                                String str12 = "ۥۗۖۥۧۚۨۚۜۘۢۦۚۨ۟ۦۦۧۥۚ۫۬ۤ۬ۨۧۢۡۥۙۤۗۘۘ۬۟ۖۙۛۖۜۤۨۘۤۧۜۨۜۥۘ";
                                                                while (true) {
                                                                    switch (str12.hashCode() ^ (-156176536)) {
                                                                        case 465269649:
                                                                            str12 = "ۥۗۧۜ۟ۘۘ۟ۥ۠ۨۙۥۘۢۨ۬۫ۧۜۘۥۤۤۡۚ۬ۚۡۖۦۦ";
                                                                        case 512727472:
                                                                            str12 = it.hasNext() ? "۫ۥ۫۫ۙۘۘ۬ۚۗۖۧۘۖۦۡۘۢۨۨۖۧۧۢۙۙ۫ۘ۟ۡۧۥ۟۟۠ۚ۠ۨ" : "ۗ۫ۦۙۡۥ۠ۚۡۘۘۡۖۘۛۘۥۡۢۘۥۜۡ۟ۨۧۘ۠ۦۘۖۦۨ";
                                                                        case 815056892:
                                                                            str9 = "ۛۥۦۗۤۘۦۖ۠۫۠ۛۥۢۚۗۜۘۦۚ۟ۨۡۗ۫۫ۘۤۗۡۘۘۙ۫ۙۡۘۢۡۨ۫۟ۗ";
                                                                            break;
                                                                        case 1152952385:
                                                                            str9 = "ۡۜۧۘ۠ۦۘۖۛۤ۟ۨۘۘۛ۬ۨۘۛۧۦۚۘۨۦۡ۟ۡۧۦ۟۬ۧۡۢۥۘۧۨ۟ۙۜۖۛۤۘۚۥۢ۠ۦۖۘ";
                                                                            break;
                                                                    }
                                                                }
                                                                break;
                                                            case -459256475:
                                                                str9 = "ۤۡۖۘ۬۠ۙۖۛۦۘۢ۬ۧۧۥ۠ۖۥۘ۟۬ۖۘۜۙۦۘۦ۠ۜۤۘۦۘۦۗ۬۬ۛۤۙۥۥۘۡۨۢۜۖۨۖۨۡۥ۬۠ۜۛ۟";
                                                                break;
                                                            case -123659083:
                                                                k2.logToFloatingWindow(sb2.toString(), "debug");
                                                                return;
                                                        }
                                                    }
                                                }
                                                break;
                                            case 2034173445:
                                                str7 = "ۥۙ۠ۜ۬ۥ۬ۘۡۘ۬ۛۨۘۚۗۙۨ۬ۧ۬ۗۖۜۢ۬ۡۧ۫۟ۢۛۥۛۥۥۦۦۚۘۖۖۢ۟ۥ۫ۥۘۖۘ";
                                                break;
                                        }
                                    }
                                    break;
                                case -1396226311:
                                    str2 = "ۧۗۖۥۥۡۘۛۨۗۚۗۜۘۥۚۖۖۨ۠ۘۡۗۢ۠ۜۘۧۚۢۘۡ۠۟ۥۨۘۚۜۘ";
                                    break;
                                case 1751612555:
                                    String str13 = "ۧ۟۫۠ۚۦۘۦۡۡ۬ۥۤۥۚۢ۫ۗۨ۟ۢۡ۟ۥۚۡۦ۟ۖۛۦۛۖۚۡۘۚ۠ۦۛ۠ۗۖ";
                                    while (true) {
                                        switch (str13.hashCode() ^ (-1586090668)) {
                                            case -1858621715:
                                                str13 = "ۛۡۘۘۨۗۥۚۜ۫۠ۖۖۘۨۡ۫ۢۤۨ۬ۢ۬ۧۜۦۧۢۜۘۙۨۨۘ۫ۧۨ۟ۦۘۛۘ۬ۜۘۙ";
                                                break;
                                            case 661167172:
                                                str2 = "ۜ۟ۖۘۚۛ۬ۨۧۨ۬ۡۢۙۡۗۤۤۡۨۡ۬ۘ۟ۥۜۖۘ۠۬ۧۜۛۘۧۛۧۡۦۘۗۜۜ۟ۧۘۘۙۘۦۘۤۜۨۚۜۦ";
                                                continue;
                                            case 1176402858:
                                                if (extras == null) {
                                                    str13 = "۫ۥۨۘ۟ۗ۬۫ۦۤ۟ۡۜۤۜۚۨ۠ۘۚۗۤۡۚۜۘ۠۫ۧۨ۫۬";
                                                    break;
                                                } else {
                                                    str13 = "ۥۘۗۦۚۗ۠ۖۦۘۗۡۢۥۙۡۧ۟ۥۘۙۨۘۦ۟ۡۘۡ۠ۛ۫۬۬۟ۦۨۚ۠ۛۨۘۥۘ۬۬ۖۘ۠ۙ۫۟۫ۢ۫ۧۢۗۘۢ";
                                                    break;
                                                }
                                            case 1689688541:
                                                str2 = "۫۫ۨۘۡۡۦۛۚۜۖۘ۠۟ۥۖۧۙۙۥ۠ۚۖۡۧ۫ۡۚۚۗۦ۠ۤۥۜۦۨۘۛ۫ۢۛۡۧۜۡۛۛۤ۬۟۫ۦۖۜ۫";
                                                continue;
                                        }
                                    }
                                    break;
                            }
                        }
                    }

                    /* JADX WARN: Can't wrap try/catch for region: R(7:217|218|235|245|236|237|543) */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                    */
                    private String safeResolveTargetClassName(Context context2, Intent intent) {
                        PackageManager packageManager;
                        String str = "۟۟ۧۙۗۛۨۢۗۙ۠ۥۘۙ۬ۖۘۛۛۡۘۥ۫۟ۚۗ۬ۚۦ۟ۡ۫ۙۢ۟۟ۙۦ";
                        while (true) {
                            try {
                                switch (str.hashCode() ^ (-951902142)) {
                                    case -77748494:
                                        String str2 = "۫ۜۗ۫ۡۨۛۥۢۖۖ۠ۖ۟۠ۙۦۦۖۜۘ۟ۙۛۡ۬ۜۘۡۘۤ۫ۗ۟۟۟";
                                        while (true) {
                                            switch (str2.hashCode() ^ (-1979637389)) {
                                                case -1033070398:
                                                    str2 = "ۦۜۛۘ۟ۜ۠۫ۦۘۗۖۡۘۚۦۥ۟ۥ۟ۧ۬ۙۛۥۧۘۖۛۜۘۤۖۖ۬ۥۙ۬ۗۖۤ۠۠۠ۡۡۢۖۥۚ۠ۥ";
                                                    break;
                                                case 695026593:
                                                    if (intent.getComponent() == null) {
                                                        str2 = "ۙۚۜۜۧ۟ۡۨۨۘۖۖۧۘ۟۠ۨۙۨ۫ۥۧۘۘ۬۫ۜۘۚۜ۬ۥ۠۠ۨ۟ۤۢ۠ۤ";
                                                        break;
                                                    } else {
                                                        str2 = "ۜۦۚۖۦۖۧۖۨۡۖۢ۬ۚۨ۫ۗۗ۬ۨۘ۠ۖۦۘۦۡۨۘ۬ۢۘۘۚ۬ۨۘۢۛ۠ۤ۠ۧۦۙۥۜۜۦۘ۠ۜ۫ۤۚۙۘۚۛ";
                                                        break;
                                                    }
                                                case 1188440479:
                                                    str = "ۖۤۨۘۜۙۤۘۦۡۘۥ۫۫ۗۧۖۘۛ۬ۥۘۨ۠ۨۘۦۨۨۛۖۡۗۚۗ۠ۧۢ۬ۙۡۘۡ۫ۨۛ۬ۤ";
                                                    continue;
                                                case 1272006452:
                                                    str = "ۤ۫ۘۜۘ۟ۘۡ۫ۤۚ۟ۨۛۘۘۤۚۗۧۗۖۖۜۘۧۧۥۨۢۨۘ";
                                                    continue;
                                            }
                                        }
                                        break;
                                    case 354327927:
                                        str = "ۜ۬ۖۡۛۜۢۘ۬ۡۦۦۘۘۗۡۛۦ۬ۡۢۦۘۨۤۘۘۜۢۦۗۘ۫";
                                        break;
                                    case 1257706316:
                                        String strResolveImplicitIntentTarget = Utils.resolveImplicitIntentTarget(context2, intent);
                                        String str3 = "ۚ۟ۘۥۨ۫ۤۘۗۡ۫ۜۨۛۡۨ۫ۨۡۨۘۖۡۗۛۥ۟ۗ۫۠ۖۧۚۨۥۗۗۖۧۘ۟۟ۧ";
                                        while (true) {
                                            switch (str3.hashCode() ^ (-524005686)) {
                                                case -329249887:
                                                    boolean zIsEmpty = strResolveImplicitIntentTarget.isEmpty();
                                                    String str4 = "۫ۚ۠ۛۧۖۘۢۤۦۘۨۙۡۥ۬ۥۘۥۖۨۘ۫ۖۢۙۘۡۖۦۡ۠۫ۘۘۙۨۘۨۢۦ";
                                                    while (true) {
                                                        switch (str4.hashCode() ^ (-233343759)) {
                                                            case -2058944633:
                                                                str4 = "ۗۤ۟ۧۨۘۛۨۘۘۦ۫ۦۘۦۙۙۚۗ۫ۡۜۨۘۤۥۜۘۚۖ۫ۧۢۨۘۙۗۦۖ۠ۛۚۢۛۙ۟ۤۜۖۨ۠ۢۜۧ۫ۙ۬ۢۡۘ";
                                                                continue;
                                                            case 140125919:
                                                                break;
                                                            case 289114733:
                                                                String str5 = "۟ۚۦۡۧۡۢۡۦ۠ۖۥۜۘۥ۠ۨۤۛۙۡۗ۬ۨۘ۫ۤ۠ۙۖۘ۟ۤۥ۠ۗۜۘ";
                                                                while (true) {
                                                                    switch (str5.hashCode() ^ (-1833607097)) {
                                                                        case -2008039376:
                                                                            str4 = "ۜۘۖۦۜۤۧۜۦۘۦۜۡۘۘ۠۬۬ۙۨۧۜۖۘۡۢۘ۟ۢ۬ۘۜۜۡۧۘۛۥۚ";
                                                                            continue;
                                                                        case -1169425306:
                                                                            str5 = "ۢۢۚۢۧ۟۠ۢۖۤۢۢۗۢۡۘۦۨۚۦۙ۠ۤۜۨ۠ۖ۬۟ۨۙ";
                                                                            break;
                                                                        case 1340682564:
                                                                            str4 = "ۥۖۖۘۡۜۥۥۚۥۘ۟ۥ۟۫ۤ۠ۜۤۜۥۚۗۙ۠ۘۘ۫ۤۡۘۙۨۡۘۘۙ۟ۚۦۛۙۛۦۨۖۦۗۘۖۗ۫ۗۢۘۢۗ۬";
                                                                            continue;
                                                                            continue;
                                                                        case 1496927141:
                                                                            if (!zIsEmpty) {
                                                                                str5 = "ۘۡ۟۫ۦۨۜۚۡۖ۟ۥۘۜۜۨ۟ۡ۟ۘۛۥۖ۫ۘۘۘۤۛۛۧۦۘۧ۟ۧۡۡۜۘۧۢۗۛۘۢ";
                                                                                break;
                                                                            } else {
                                                                                str5 = "ۡۢۦۘۨۛۧۜ۟ۘۘۙۨ۬ۤۗۡۘۗۡۤۜۛۢۚۢ۫۠ۦۗ۫ۘۢۧۢۖۘۘۡۘ";
                                                                                break;
                                                                            }
                                                                    }
                                                                }
                                                                break;
                                                            case 2053872432:
                                                                return strResolveImplicitIntentTarget;
                                                            default:
                                                                continue;
                                                        }
                                                    }
                                                    break;
                                                case 1160440275:
                                                    String str6 = "ۙ۠ۗۤۤۜۘۙۧۗۜۖۘۘ۫ۙۙۡۧۤۖ۠ۥۡ۟ۖۤۖۧۘ۠ۙۦۚۡۦۘۗۖۛۘۗۤۡۢۖۢۥۘۖۜۙ";
                                                    while (true) {
                                                        switch (str6.hashCode() ^ 1567163271) {
                                                            case -1976787223:
                                                                if (strResolveImplicitIntentTarget == null) {
                                                                    str6 = "ۤۖۖۘۥ۫ۨۡۜۗۗۧۡۘۜۜ۟۬ۘۖۘۙۘ۬ۥۘۤۛۚۡۛۛۚ";
                                                                    break;
                                                                } else {
                                                                    str6 = "ۜۚۦۘۡ۫ۧۧ۬ۗ۫ۨۥۙۛۢۨۜۘۘ۠ۘۛۜۚۗۡۦۘۛۙۚۖ۬ۡ۬ۛ۟ۘۨۜ۬ۗۧ";
                                                                    break;
                                                                }
                                                            case -1785985790:
                                                                str6 = "ۤۙۧۧۧۤۜ۠ۨ۫ۘ۬ۘۧۦۤ۟ۘۘ۠ۗۘۘۗۢۚۢ۠ۥۧۥۙۧ۟۫ۢۦۗۚ۟ۤۙۙۤ";
                                                                break;
                                                            case -1618825013:
                                                                str3 = "ۢۛۖۘۙۨۚۗ۬ۡۘۨۧۡۘ۫ۗۡۘۢ۠ۚۚۚۖۘۙۛۨۨۗۙ۫ۡۘۗۥۗۦ۫ۥ";
                                                                continue;
                                                                continue;
                                                            case -101485549:
                                                                str3 = "ۧۤ۠ۘۨ۫۫ۡۧۘۗۨۛۤۙۥۘۦۜۤۛۡ۬۟۠ۦ۬ۙۖۗۚۖۘ";
                                                                continue;
                                                        }
                                                    }
                                                    break;
                                                case 1568521726:
                                                    break;
                                                case 1827941766:
                                                    str3 = "۬ۚ۟ۗۘ۟ۦۨۖۘۙۦۜۘۙۖ۠ۤۙۤۦ۬ۛۛۜۛۥۥ۬ۙۛۜۥۛۙ۬ۧۥۘۚۥۘۚۘۡۘ۠ۥۨۘۜۥۗۡۖ۠ۚۗۤ";
                                                    continue;
                                                default:
                                                    continue;
                                            }
                                        }
                                        String str7 = "ۖۖۚۗۥۜۘۤۚۥۘۘۜۧۨۧۤ۬ۛۛۧۧ۬ۛۡ۫ۡۗۜۡ۬ۚ";
                                        while (true) {
                                            switch (str7.hashCode() ^ (-336534627)) {
                                                case 159627243:
                                                    try {
                                                        PackageManager packageManager2 = context2.getPackageManager();
                                                        String str8 = "۫ۧۧ۫ۡۡۘۧ۠ۥۘۘۥۘ۟ۡۦۘۧۦ۟۫ۛۜۘۜ۟ۨۘ۟ۙۨۘۗ۟ۜ۟ۡۘۡۗۦۗۜۗۨ۬ۚۜۧۧۥۙۚ";
                                                        while (true) {
                                                            switch (str8.hashCode() ^ (-594374741)) {
                                                                case -457447277:
                                                                    ResolveInfo resolveInfoResolveActivity = packageManager2.resolveActivity(intent, 65536);
                                                                    String str9 = "ۢۦۜۘ۫ۥۖۘۚۗۢۧۘ۫ۧ۬ۨۘۤۧۦۘۛۘۡۗۨۘۢ۟ۥ۠ۨۙۥ۫ۡۘۖۤۤۗۖ۟۠ۨۘۢۦۡ۬ۡ۠۬ۜۖۚۧۘۘ";
                                                                    while (true) {
                                                                        switch (str9.hashCode() ^ 1576371913) {
                                                                            case -1533640663:
                                                                                ActivityInfo activityInfo = resolveInfoResolveActivity.activityInfo;
                                                                                String str10 = "ۦۢ۟ۘۢ۠ۙۙۡۚۚۨۘۨ۬ۜۦۧ۬۫ۨ۬ۛۨۘۚۥۗۙۤۖۥۚۡ۠ۜۛ";
                                                                                while (true) {
                                                                                    switch (str10.hashCode() ^ 2016836673) {
                                                                                        case -1606601497:
                                                                                            String strConcat = activityInfo.name;
                                                                                            String str11 = "ۚۨۛۢ۟ۚ۠۬ۗۡۢۥۡۗۗۗ۬ۖۙۤۗ۠۟ۗۗۡ۫";
                                                                                            while (true) {
                                                                                                switch (str11.hashCode() ^ (-2030012044)) {
                                                                                                    case -538733128:
                                                                                                        break;
                                                                                                    case 96485368:
                                                                                                        String str12 = "ۡۤ۟ۛۜۘۘ۬ۥ۬ۜ۬ۖۢۢۨۘۡۖۨۘۚۛۛۛۤۤۛۤ۬ۖۢ۬ۙۡۜۥ۬ۖۘ";
                                                                                                        while (true) {
                                                                                                            switch (str12.hashCode() ^ (-1363141727)) {
                                                                                                                case -1166439525:
                                                                                                                    if (strConcat == null) {
                                                                                                                        str12 = "ۘۧۗۘۧۗۙ۠۟ۛۗ۬۟ۤۘۘ۫۟ۦۢۨ۫ۗ۟ۙۨۢۧۦۢۜۘ۬ۡۥۧۗۥۙ۠ۗۙۨۥۙۤۙۖۧ۬ۜۜۜۘ۫ۦۡۘ";
                                                                                                                        break;
                                                                                                                    } else {
                                                                                                                        str12 = "۬ۧۡۥۢ۫ۤۤۨۧۥۦۛۘۚۛ۬ۥۘۥۢۘۘۤۥۧۘۗۡۚۧ۬ۧۖۨ۠ۧ۠ۙ۬۬ۧ۫ۖ";
                                                                                                                        break;
                                                                                                                    }
                                                                                                                case 801614907:
                                                                                                                    str11 = "۬ۥ۠ۘۤۜ۫۫۬ۖۤ۠ۗۖۨۖۥۘۥ۠ۦۖ۬۠ۜۜ۬۬ۙۖ";
                                                                                                                    continue;
                                                                                                                    continue;
                                                                                                                case 1154911047:
                                                                                                                    str12 = "۠ۥۧۘ۬ۘۗۙۧۛۧۦۥۘۤۛۡۘ۠ۥۜۗۛۡۘ۫ۖۤ۫ۡۜ۟ۖۘ";
                                                                                                                    break;
                                                                                                                case 2144990155:
                                                                                                                    str11 = "ۜ۬ۛۦۤۨۛۧ۫ۙۢۦۢۜ۬ۗ۬ۖۙۖۨۖ۟ۘۗ۠ۥۙۛۚ";
                                                                                                                    continue;
                                                                                                            }
                                                                                                        }
                                                                                                        break;
                                                                                                    case 469296735:
                                                                                                        str11 = "۫ۧۧ۫۫ۡۡۘۦۘ۠ۖۜ۫ۥۧۨۢۜۦۙۡ۟۠ۨۦۘۢۦۨۙۥ۟۬ۘۘۘ۫ۜ۬ۘۛۛۧۘۗ۠۟ۦۘۜۦۖۘۖ۬۬";
                                                                                                        continue;
                                                                                                    case 1415192934:
                                                                                                        String str13 = "ۙۨ۫ۥۡۥۘۢۤ۠ۛۨۧۘۨ۫ۦۘۡۦۢۛۛۗۖۚۦۜۚۦ";
                                                                                                        while (true) {
                                                                                                            switch (str13.hashCode() ^ 719866268) {
                                                                                                                case 571265422:
                                                                                                                    String str14 = resolveInfoResolveActivity.activityInfo.packageName;
                                                                                                                    String str15 = "ۛ۟ۖۘۤۜۥۘۨۨۘ۠ۗۜ۬ۗۜۨۤۡۢ۬۟۬ۚۤۡۘۛۨۨ۬ۜ۬ۦۡۤۜۙ۫ۤ۠ۘۨۤۛۖۜۥۤ";
                                                                                                                    while (true) {
                                                                                                                        switch (str15.hashCode() ^ 2035637924) {
                                                                                                                            case -1925159470:
                                                                                                                                strConcat = str14.concat(strConcat);
                                                                                                                                break;
                                                                                                                            case -1416076320:
                                                                                                                                str15 = "ۘۦۢۢ۬ۥ۫ۗۛۙۨۙۢۤۛۡۘۜۢ۟ۧ۫ۧۥ۟ۧۢۘۚۥ";
                                                                                                                                continue;
                                                                                                                            case 466375219:
                                                                                                                                break;
                                                                                                                            case 1855140444:
                                                                                                                                String str16 = "ۛ۠۫ۧۡۖۘۧ۬ۦۜۦۙۚۘۜۘۚۧۜۘۘۚۥۧۧۗۥۚۦۘ۟ۘۦۘ۬ۜۛ۫ۖ۫ۨۢۘۘۜۤۘۘ";
                                                                                                                                while (true) {
                                                                                                                                    switch (str16.hashCode() ^ 1243587160) {
                                                                                                                                        case -393648529:
                                                                                                                                            str15 = "ۜۙۦۘۚۚۗۛۖۥۘ۠ۡۧۢۖۛۗۘۜۘۢۗۖۜ۠ۡۚۢ۬ۘۡۡ۫۬ۛۥ۠ۨ";
                                                                                                                                            continue;
                                                                                                                                        case -104894547:
                                                                                                                                            str16 = "ۢ۟ۦۤۧۜۨۗۜۢۜۖۘۥ۫ۖ۠ۨۗۜۢۖۘ۠ۘۘۥۦۜۘۢۗۖ";
                                                                                                                                            break;
                                                                                                                                        case 21988636:
                                                                                                                                            str15 = "ۤۖۧۘۤۤۤ۟ۗۡ۫۬ۖۥ۟ۥ۟۟ۨۦۙۘۘۖۗۗۨ۠ۦۖۜۚۧۥۨۘۘ۬ۨۘۖۘۡۛۥۜ";
                                                                                                                                            continue;
                                                                                                                                            continue;
                                                                                                                                        case 1839976200:
                                                                                                                                            if (str14 == null) {
                                                                                                                                                str16 = "ۖ۬ۖۘ۬ۨۦۘۛۥۧۘۖۥۧۘ۟ۤۨۘۚۡ۠ۧ۟ۡ۠ۢۖۛۧۥۘۤۗۗ";
                                                                                                                                                break;
                                                                                                                                            } else {
                                                                                                                                                str16 = "ۢ۟ۦۥ۫ۘۘۡۢۡۘۨۚۜۛ۠ۖۘۡ۬۟ۖۨ۬ۤۜ۫ۨ۠ۦۘ۬ۧۡۥۙۡۜۜۧۨۨۥۘۢۢۡۤۥۦۗۜ۫ۛۙ۫۫۬ۧ";
                                                                                                                                                break;
                                                                                                                                            }
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                break;
                                                                                                                        }
                                                                                                                    }
                                                                                                                    break;
                                                                                                                case 679387002:
                                                                                                                    String str17 = "ۤۡۜ۟ۡۨۤۦۤۜۛۜۘ۟ۘۚۚۢۜۘۗ۟ۙۘۜ۬۟ۖۜۘۢۜۡۦۚ۬ۙۨۘۖۚۗ۫ۢۦۘۨۘۦۘۦۚۢ";
                                                                                                                    while (true) {
                                                                                                                        switch (str17.hashCode() ^ (-812748424)) {
                                                                                                                            case -378787306:
                                                                                                                                str13 = "۠ۖ۟ۙۚۙۙۤۜۙ۟ۢۨۨۧۧۨۛ۟ۢۧۗۢۥۖۢ۬ۡ۟";
                                                                                                                                continue;
                                                                                                                                continue;
                                                                                                                            case 80781815:
                                                                                                                                if (!strConcat.startsWith(".")) {
                                                                                                                                    str17 = "ۙۛ۠ۤۙۦۘ۬ۡۛ۠ۨۜۜۖۥۘۗ۟ۖۘ۬ۚۜۧۚۡۧ۬۠ۦ۟ۙ۠ۖۖۦۗ۠ۤۧۘۖۚۨ";
                                                                                                                                    break;
                                                                                                                                } else {
                                                                                                                                    str17 = "ۤۗۚۡ۬ۛۢۤۘۙۛۙۜۖۘۘۨۤۗۘۡۨۗۦۘ۫ۜۤ۟ۚۤ";
                                                                                                                                    break;
                                                                                                                                }
                                                                                                                            case 936216303:
                                                                                                                                str17 = "ۤۙۤۥۦۦۛۖ۬ۧۧۙۖ۟ۚۨ۠ۨۘ۟ۥۖۘ۫ۚۜۘۨۖۧ۟۫۠ۡ۫ۨۢۖۗ";
                                                                                                                                break;
                                                                                                                            case 1945585813:
                                                                                                                                str13 = "ۥۥ۠ۖۢۛۥۘ۬۠ۚۗۜۢۘۘۛۤۛۡۖۙۘ۠ۛۜۘۜۙۗۜۘ";
                                                                                                                                continue;
                                                                                                                        }
                                                                                                                    }
                                                                                                                    break;
                                                                                                                case 808978094:
                                                                                                                    break;
                                                                                                                case 1522442215:
                                                                                                                    str13 = "ۨۧۘ۠ۚۤۡ۬ۜۚۜۢۧ۬ۜۘۛ۫ۙۖۚۙۤۖۘۡۤۘۘ۠ۡۖۘۢۦۥۢۛۡۘ";
                                                                                                                    continue;
                                                                                                            }
                                                                                                        }
                                                                                                        break;
                                                                                                }
                                                                                            }
                                                                                            String str18 = "ۧۤۛۢۘۜۘۧۧۚۤۤۡۘۚۤ۬ۢ۠ۘۘ۬ۚۜۧ۬ۢ۠ۤۖۥۘۘ";
                                                                                            while (true) {
                                                                                                switch (str18.hashCode() ^ (-1626377786)) {
                                                                                                    case -1581846131:
                                                                                                        String str19 = "ۛۡۦۨۗۢۨۥۦۘۢۡ۫ۜۚۖۗۨۖۚ۫ۜۥۚ۠ۜۢ۫ۡۜۤۖ۟ۙۚۜۛ۬۠ۖۘۖۘ۬ۙۘۢ۠۬";
                                                                                                        while (true) {
                                                                                                            switch (str19.hashCode() ^ (-571713464)) {
                                                                                                                case -634249650:
                                                                                                                    str19 = "۫ۢ۫ۡۥ۟ۢۧۘۘۧۘۖۘ۫ۢۦۡۙ۫ۙۤ۫ۘۢ۟ۢۨۚۛۖۖۘ";
                                                                                                                    continue;
                                                                                                                case 333000273:
                                                                                                                    return strConcat;
                                                                                                                case 661593937:
                                                                                                                    break;
                                                                                                                case 1296240663:
                                                                                                                    String str20 = "ۙۤۜ۬ۤۙ۟ۖۧۘۢۖۦۘۙۙۨۘۜۤۜ۟ۨۧ۬ۗۥۤۚۧ۠ۥۛ۟ۜۧۘۘۡۨ";
                                                                                                                    while (true) {
                                                                                                                        switch (str20.hashCode() ^ 263586182) {
                                                                                                                            case -501572015:
                                                                                                                                str20 = "ۡۨۖۘۙۛ۫ۘۤۡۘۘۙ۠ۜۗۧۡۚۖۙۦ۟ۘۤۦ۫ۚۙۡۨۨ۫ۙ۬ۗۗۚ۫ۖۥۚۙۖ۬ۤۢۙۢۜۘ";
                                                                                                                                break;
                                                                                                                            case -273290292:
                                                                                                                                if (!strConcat.isEmpty()) {
                                                                                                                                    str20 = "ۜۦۡۘۛۛۡۨۥ۬ۚۖ۟ۘ۬۫ۛ۬ۗۛۨ۟ۖۤۦۚۛۘۦۨۛۘۥ۠ۧۖ";
                                                                                                                                    break;
                                                                                                                                } else {
                                                                                                                                    str20 = "ۦۜ۠ۤۤۨۛۡۧۘۜۚ۫ۚۘۜۘۧۦۗۨۦۦۘۗۛۖۘ۬ۛۜۘۥۜ۠ۜ۫ۚ۟ۡۘۛۗۧۗۖ۠ۢۨۢۥۦۜۘ";
                                                                                                                                    break;
                                                                                                                                }
                                                                                                                            case -156405008:
                                                                                                                                str19 = "ۘۙۙۢۙ۠۫ۖۘۢۥۨۘۚۧۖ۟ۚۚۗ۬۬ۚۢ۫۠ۙۘۘ۠ۥ۬ۧۤۗۦۛۤۘۢۘۥۘۘۛۤۗۥۙ";
                                                                                                                                continue;
                                                                                                                                continue;
                                                                                                                            case 1179476748:
                                                                                                                                str19 = "۫ۛۥۘۤۜۨۘۘۙۖۘۛۥ۟ۗۙۙۦۘۡۛۨۙ۠ۛ۠ۥۚۜۥۥ۠۠ۨۥۜۗۚۧۛۧۙۨۢۧۦۛۧ۠۟ۥۛۧۚ";
                                                                                                                                continue;
                                                                                                                        }
                                                                                                                    }
                                                                                                                    break;
                                                                                                                default:
                                                                                                                    continue;
                                                                                                            }
                                                                                                        }
                                                                                                        break;
                                                                                                    case 1139873320:
                                                                                                        str18 = "ۥۨۚۖ۬ۖۡۘ۬ۘ۠ۥ۠ۚۢۥۤۘۘ۫۫ۜۘ۟ۦۜۘۗ۟ۘۘۦۨۜۛۥ۬ۙ۠ۗ";
                                                                                                        continue;
                                                                                                    case 1247257863:
                                                                                                        String str21 = "۟ۙۦۜۖۤۤۜۡۘۦۛ۬ۘۧۜۗ۠ۢ۫ۦۖۘۘ۠ۨ۠ۙ۠ۥۨ۠۫ۨۧۘ۟ۛۨ۠ۢۚۨۖۘ";
                                                                                                        while (true) {
                                                                                                            switch (str21.hashCode() ^ (-996269957)) {
                                                                                                                case -1375758560:
                                                                                                                    str21 = "ۗ۬ۦۙۢۛۥ۬ۜۘۡۘۖۘۡۙۨۜ۠ۥۘۛ۠ۗۚ۬ۗۧۤۘۖ۠ۨۘۚۗۛۙۖ۫";
                                                                                                                    break;
                                                                                                                case -865972298:
                                                                                                                    if (strConcat == null) {
                                                                                                                        str21 = "ۛ۬ۜۘ۬ۡۦۜ۫ۡۘۗۖۖ۬۠ۜۘۥ۟ۖۘ۫ۦۡ۟۫ۛۢۘۤۥ۠ۧۜۙۚۜۗۜۘ۫ۘۦۘۙۡۚ";
                                                                                                                        break;
                                                                                                                    } else {
                                                                                                                        str21 = "ۨ۠ۘ۠ۗۥ۟ۖۡۘۜۙۧۡۤ۬ۛۤۦۘۨۦۖۘ۟ۦۥۘۛۛۛۗۚۗۢۦۢۘ۬ۤۖۘۜۘۨۨۘۡۥۘۥۘۘۘ۠ۤ۠۬ۙۜ";
                                                                                                                        break;
                                                                                                                    }
                                                                                                                case 1550260452:
                                                                                                                    str18 = "ۖۦۧۘۤۙۧۡۤۥۛۨۦۘ۠ۤۤۙ۬ۧۢۛۦۘۛۤۙۙۚۜۗۨۘۢ۟ۦۦۖۘۘۢۙۛۦۨۡ";
                                                                                                                    continue;
                                                                                                                    continue;
                                                                                                                case 1720140531:
                                                                                                                    str18 = "۟۠۠ۤۧۢۜۜۤۜۢۗۙۥۘۤ۟ۦۘۨۧۨۤۦۙۘۧۧۤ۫۟۟ۘۥۘۚۢۜ";
                                                                                                                    continue;
                                                                                                            }
                                                                                                        }
                                                                                                        break;
                                                                                                    case 1889943073:
                                                                                                        break;
                                                                                                }
                                                                                            }
                                                                                            break;
                                                                                        case -1565231561:
                                                                                            break;
                                                                                        case -1114932674:
                                                                                            String str22 = "ۙۖۨۤۨۡۘۧ۫ۡۘۜۦۢۡۖۖۥۖۛ۠ۤ۠ۚۥ۟ۡۧ۫۟ۤۖۘ۬ۙۨۘۨۨ";
                                                                                            while (true) {
                                                                                                switch (str22.hashCode() ^ (-1888177931)) {
                                                                                                    case -1819707286:
                                                                                                        str22 = "ۦۥۗۧۖۘۘۨۢۙۖۗ۠ۙۗۥۘۥۖۖۢۙۚ۠ۙۢۥۛۥۜۗۦ";
                                                                                                        break;
                                                                                                    case -1407474982:
                                                                                                        str10 = "۫ۨۥۘۢۥۜ۟ۘ۠ۖۢۤۢۧ۠ۥۖۜۘ۬ۛ۫ۤۤۖۧۜ۠ۗۨۘۥۙۛۛۦۨۘ";
                                                                                                        continue;
                                                                                                        continue;
                                                                                                    case -261267860:
                                                                                                        str10 = "ۡۛۗۛۚۖۘ۟ۤۡۘۗۨۨۜۖۡ۫ۛۜۘۤۘۢ۟ۖۦۘۘۨۜۘۖۙۦ";
                                                                                                        continue;
                                                                                                    case 167517284:
                                                                                                        if (activityInfo == null) {
                                                                                                            str22 = "ۘ۟۠ۥۡۜۘۗ۠ۜۘۦۨۘۧۥۦۘۨۡۘۤۦۘۘۚۜۢۡۘۢۙۛۡۘۛۛۛۨ۫ۛ۬ۚۚ۟ۢۤۜۦۡۜۘۥ";
                                                                                                            break;
                                                                                                        } else {
                                                                                                            str22 = "۫ۡۥۘۢۢۗۖ۬ۖۘۢۢۤۜۛۘۜۖ۬ۙۥۤۚۗۥۡۤۚۗۧ۟";
                                                                                                            break;
                                                                                                        }
                                                                                                }
                                                                                            }
                                                                                            break;
                                                                                        case 2083322239:
                                                                                            str10 = "۟ۛۥۘۤ۬۫ۦۖۗ۬ۨۖۘۦۨۘۜۜۨۘۨۥۨۚۜ۠ۚۥۘ۟ۡۨۖۖۜۥۨۡۘۖۥۙ۟ۚۖ";
                                                                                            continue;
                                                                                        default:
                                                                                            continue;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case -221478454:
                                                                                String str23 = "۠۫۫۠۠ۧۚۨۡۘۢ۠ۦۘۛۙ۠ۚۗۥۖۛ۬ۚۧۖۧ۟۬ۡۘ۫ۘۤۛۖۨ۫۬ۖۖۧ۫۬۠ۧۡ۬ۚۦۡ۬ۗۧۦ";
                                                                                while (true) {
                                                                                    switch (str23.hashCode() ^ (-379507385)) {
                                                                                        case -1769005991:
                                                                                            str9 = "ۗۡۢ۫۫۫ۥۡۘۘ۟ۧۜۨۗۖۤۧۛۖۦ۬ۡۧۘۤۡ۬۫ۙۤ۬ۨۨۘۨۚۨۘ";
                                                                                            continue;
                                                                                        case -49132132:
                                                                                            str23 = "ۡ۬ۜ۟۟ۨۘۜۛ۟ۤۨۘۘۛ۠ۥۘ۬ۙۙ۠۟۟ۖ۟ۡ۬ۗۙۙۥۖۨۖۦۘۛۛۚۛۨۖۦۧۥۨۧۢۧۘۖۘۢۚۦۘ۟ۙۨ";
                                                                                            break;
                                                                                        case 252486532:
                                                                                            if (resolveInfoResolveActivity == null) {
                                                                                                str23 = "ۡۜۥۘۤۖۥۘ۬۠ۢۢۛۗۦۥۛۡۥۘۘۦۦۘۙۚۛۛۜ۟ۜۗۡۘ۫ۢۗ۠ۛۨۨۦۡۘ";
                                                                                                break;
                                                                                            } else {
                                                                                                str23 = "ۘۜۨۘۨۤۘۛۥۨۘ۟ۛۥۘ۠۫ۜۘ۟ۗۥۖ۫ۘۘۛ۫ۘۘۦۧۜۗۡۧۘۗۢۚ۟ۥۖۘ";
                                                                                                break;
                                                                                            }
                                                                                        case 1611712404:
                                                                                            str9 = "ۛۚۡۤۤۛۚ۫ۜۘ۟ۦۛ۠ۧۖۦۡۙ۬ۧۥۢۜۨ۠ۘۗۚ۬ۨۜۙۦۘ۫ۥۖۘ";
                                                                                            continue;
                                                                                            continue;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case 879256714:
                                                                                break;
                                                                            case 1721934536:
                                                                                str9 = "ۚ۬ۢۧ۠ۧۧۛۜۘۢۥۗۧۧۛۛ۫ۜۦ۫۠۠ۛۨۙۙ۫ۚۘ۫ۤۜۧۡۛۖۘ";
                                                                                continue;
                                                                            default:
                                                                                continue;
                                                                        }
                                                                    }
                                                                    break;
                                                                case 16959212:
                                                                    str8 = "ۖ۠۫ۡۛۚۢۥ۫ۤ۟ۖۜۜ۬ۗۘۤۦۨۢۡۧۘۦۤۖۘۨۨۗۙۢ۫۠ۖۙۗۡۢ۫ۡۧۘۛۢۖۘۤۦ۠ۦ۬ۧۦ";
                                                                    continue;
                                                                case 1609952453:
                                                                    String str24 = "ۧۢۡ۫۫ۛۦۧۡۘۙ۬ۛۖۘۡۘۦ۟ۖۘ۠ۛۜۛۛۥۘۜ۬ۜۘۧۨۨۘ۫ۢۡۘۘۙۦۗۨ۟ۘۘۧۘۘۦۦۘۛۡ۟ۥ۟ۤ۬ۤ۬";
                                                                    while (true) {
                                                                        switch (str24.hashCode() ^ (-810069109)) {
                                                                            case -2102638011:
                                                                                str24 = "ۖۧۙۗۢۗۘۙۗ۫ۢۘۚۧۖۖۜ۟۠ۜۜۦۥۘ۫ۜۦۘۛۧۜ۫ۛۖۦۧۖۡۜۧ۫ۘۦۘۧۡۧۧ۬ۧ۠ۛۦۙۦ۟";
                                                                                break;
                                                                            case -2004074542:
                                                                                if (packageManager2 == null) {
                                                                                    str24 = "ۛ۟ۦ۫ۥۘ۠۫ۢۜۢۦۗۦۢۨۙۨۘۙ۠ۨۡ۫ۗۖۛۘۘۥۢۨۘ۠ۘۡۘۢۙۗ۫ۨۜۗ۟ۡۘ";
                                                                                    break;
                                                                                } else {
                                                                                    str24 = "ۨۛۡۘۛۗۡ۠۟ۤۜۡۨۨۥۙ۠ۦۡۡۧۚ۫ۦۘۢۛۤۖۨۘۜۨۜۘۢۛۡۘۜۤ۬ۡ۬ۥۘ";
                                                                                    break;
                                                                                }
                                                                            case 911244424:
                                                                                str8 = "ۥ۫ۛۤۙۤۥ۟ۨۘۘۨۜۘۦ۫ۙۦ۫ۡ۠ۛۤۘۧۨۧۘۨۘ۫ۗ۟ۖ۬ۛۧۗۜۘ";
                                                                                continue;
                                                                                continue;
                                                                            case 1074140977:
                                                                                str8 = "ۚۢۛۜۡۥۘۜ۟۫ۨۗ۟۠ۤۦ۬ۤۗۥۗۖۘۤۨۧۘۢۧۨ۫ۚ۠ۧۢۛۜۢ۬ۘۗۥۥ۟ۥۘۨ۠ۖۘۡۨۦ";
                                                                                continue;
                                                                        }
                                                                    }
                                                                    break;
                                                                case 1757794516:
                                                                    break;
                                                            }
                                                        }
                                                    } catch (Throwable th) {
                                                        break;
                                                    }
                                                    break;
                                                case 1662301762:
                                                    String str25 = "ۤۤ۟ۜ۫ۜۘۘۥۚ۬ۤۗ۟ۢ۫۟ۨۜۙۦۘۘۙ۬ۜۘ۠ۗۛۛۘۢۥۛۨۡۦۢ";
                                                    while (true) {
                                                        switch (str25.hashCode() ^ 906326020) {
                                                            case -1841859108:
                                                                str7 = "ۙ۠ۖۥۗۛۜۦۡۤۗۙ۠ۘۜۥۤۖۘۦ۠ۡۤ۟ۜ۠ۦۘ۬ۤۦۧۗ۫ۤ۬ۥۘ";
                                                                continue;
                                                            case -1435938963:
                                                                str7 = "ۜۜۦۚۡۦۘ۠ۨۖۥ۟ۢۦۜۨۢۧۘۖۜۖۦۛ۠ۚۜۜۗۘۘۥۙۨۘ۬ۧۚۨۧۦۘ۠ۦۖ۟۠ۜۘۢۗۦۘۙۨۦۘۤۡ۟";
                                                                continue;
                                                            case -1360502699:
                                                                str25 = "ۛۦۡۖۥۥ۟ۡۧۙۚۦۘ۟۟ۡۗۡۜۛۢۨۘۡۙۧۚۗۙۡۚۖۧۦۘۘۛ۫۫ۨۦۗۙۛۥۘۦۘۙۙۜۢۖ۟ۦ۟ۥ";
                                                                break;
                                                            case -1220433109:
                                                                if (context2 == null) {
                                                                    str25 = "ۙۥۜۨۖۙ۬ۘۗۤۧۦۗۗ۠ۘ۬ۧۗۛ۟۟ۖۘۘۦ۬ۥۛۨۘ";
                                                                    break;
                                                                } else {
                                                                    str25 = "ۤۧۥۘۨۛۛۗۛۢۘۚ۟ۘۙۚۡۨۘ۬ۧۦۛۗۨۨ۬ۥۘۨۧۘۥ۠ۛۢۤۛ";
                                                                    break;
                                                                }
                                                        }
                                                    }
                                                    break;
                                                case 1701136505:
                                                    break;
                                                case 1944193178:
                                                    str7 = "ۘۖۧۨۥ۬ۨۧۙۚۘۨۘ۠ۗۜۘۡۡۡۘ۟ۨۡۜۚۗ۫ۡۦ۬۠ۡۘۘۨۙۘۖۥۡۖۚ۫ۜۢ۬ۧۡ۠ۖۚ";
                                            }
                                        }
                                        String str26 = "۬ۡۧۘۖ۫ۡۘۧۥۨۥۜۖۢۜۘۨۤۘۘۙ۟ۡۗۗۡۘۚۗۥۘۜۛۘۘۦۗۧۜۜ۠";
                                        while (true) {
                                            switch (str26.hashCode() ^ (-433455803)) {
                                                case -1501547587:
                                                    String str27 = "ۡۜۢۜۛۖ۬ۡۛۤ۫ۨ۫ۦۘ۬ۘۤ۬ۚۙۗۧۨۘ۫ۦۘۘۜ۫ۗ۬ۥۛۦۥۛ۫ۙ۫ۦۚۧۢۛۨۘ۟ۙۖ";
                                                    while (true) {
                                                        switch (str27.hashCode() ^ 1183347789) {
                                                            case -1754358826:
                                                                if (context2 == null) {
                                                                    str27 = "ۤۡۘۘۚۜۥ۫ۡ۫ۧ۫ۢۜۛۘۘۥۡۛۘ۫۬ۙ۬۫ۗۘۧۘۚۜۦۙۖۘۛۡۧۘ۫ۜۛۘۜۘۡ۟ۨۤۡۧۘۚۡۖۘۙۦۖۘ";
                                                                    break;
                                                                } else {
                                                                    str27 = "ۚ۬ۨۚۢۡۘۙۧ۟۟ۢۖۘۢۥ۬۬۬ۤۤۜۚۗۧۦ۬ۦۘ۬ۤۡۘ۟۠ۗۡۨۥۘ";
                                                                    break;
                                                                }
                                                            case -1524513003:
                                                                str26 = "ۚۤۜۘۛ۠ۙۡۧۨۦ۫ۙۡ۫ۧۥۦ۬ۢۤۚۥۖۛۤۙۢۘۖۡۚ۬ۙ۬ۥۘۙۨۗۖۡۧۘ";
                                                                continue;
                                                                continue;
                                                            case -51498018:
                                                                str27 = "۠ۖ۠ۡۦۧۘ۬ۨۘ۬ۛ۠ۨۜۘ۟۠۫۟ۗۜۘۦۗۥۘۛۦۚۖۢ۠";
                                                                break;
                                                            case 975112539:
                                                                str26 = "ۢۚۦۘۗۛ۠ۨۙ۟ۜۖ۠ۛۛۨۜۙۡۧۦۗۡ۫ۙ۫ۨ۬ۥۥۖۛۥۘ۬ۢ۫ۥۙۦۘ۬ۖۦۦۢۥ۟ۤۖۘۘۘۖۘۤ";
                                                                continue;
                                                        }
                                                    }
                                                    break;
                                                case -939593749:
                                                    packageManager = context2.getPackageManager();
                                                    break;
                                                case 394663928:
                                                    packageManager = null;
                                                    break;
                                                case 1963680819:
                                                    str26 = "ۘۚۨۢۤ۫ۘۛ۫ۡۘۡۛۤۦۘۛۡۖۤ۬ۤۛۨۛ۫ۢۘ۬ۤۤۧۥۘ۠ۢۙۢۨۡۘ۫ۦ۬ۤۤۢۗۙۨ";
                                                    continue;
                                            }
                                        }
                                        ComponentName componentNameResolveActivity = intent.resolveActivity(packageManager);
                                        String str28 = "ۘۖۚۤۛۦۢ۠ۢۚۖۘۛۥۙۢۘۨۘۤۚۥۙۗ۫ۥۘۘۡ۠ۡۘۗۜۛۧۘ";
                                        while (true) {
                                            switch (str28.hashCode() ^ (-400562524)) {
                                                case -1204210617:
                                                    str28 = "ۤۘ۬ۨۜۗۢ۬ۜ۟۟ۗۛۦۥۙۡۤۗۧ۟۬۠ۖۢۨۘۚۗ۟۬ۘۚۜ۬ۖۘۜ۟ۡ۬ۙۘۘۖۜۘۖۥۛ۠ۨۥۚۢ۬";
                                                    continue;
                                                case -636807931:
                                                    return componentNameResolveActivity.getClassName();
                                                case -167955888:
                                                    String str29 = "ۘۨۘۛۡۦۘۨۙۨۘۜۦۚۨۥ۟ۗۤۗۡۡۥۦۡۘۧۖۜۜۚۖۥۤۥۘۚ۠۫ۢۚۡۘ۬ۧۡۘۗ۬ۜ۟ۢۦ";
                                                    while (true) {
                                                        switch (str29.hashCode() ^ (-1233273579)) {
                                                            case -1294870549:
                                                                str28 = "ۛ۫۟ۢۜۤۖۘۜۧ۬ۡۜ۬ۖۘ۟ۥۙ۠ۤۖۘ۠ۦۖۦۙ۟۬ۦۨ۫ۢۛۘۘۦۡۜۘۛ۟ۢ۫ۜۥۘۥۙۢۡۗۖۡ۟ۥ";
                                                                continue;
                                                                continue;
                                                            case -513305245:
                                                                if (componentNameResolveActivity == null) {
                                                                    str29 = "۠ۡۡۘۗۜ۟ۢۤۦۘۙۥۥۘۢۧۖۘۢۦۘۜۚۨۘ۠۬۫ۢۧۥ۠ۧۚ۠۠ۚۥۙۢۥۙۜۘۡۨۜۘ۟ۛۦۢۗۥۜۗۖۛۗ";
                                                                    break;
                                                                } else {
                                                                    str29 = "ۧۨۗۚۖۜۘۚ۟ۜۘۥۡۦۜۢۡۘۧ۠ۤۜۢۦۘۘ۠۫ۧۖۖۦ۫ۥۘ";
                                                                    break;
                                                                }
                                                            case 589973554:
                                                                str29 = "ۢۨۘۘۚۢۨۙۚۚۘۚۜۘۢۜۖۚ۬ۙۙ۟۟۫ۥۥۤۨۨۚ۫";
                                                                break;
                                                            case 1163998990:
                                                                str28 = "ۙۤۜۘۥ۠ۗۛ۫ۢۖ۫ۛ۠ۚ۟ۘۖۗۜۡۧ۠ۦۘۢۨۥ۫ۧۥۡۤۥۥ۫ۦۘ";
                                                                continue;
                                                        }
                                                    }
                                                    break;
                                                case 1211821410:
                                                    k2.logToFloatingWindow("【目标类名解析失败】将启动的 Intent 关键信息：" + "\n- action: " + intent.getAction() + "\n- data: " + intent.getData() + "\n- type: " + intent.getType() + "\n- pkg: " + intent.getPackage() + "\n- categories: " + intent.getCategories() + "\n- flags: 0x" + Integer.toHexString(intent.getFlags()), "warning");
                                                    return null;
                                                default:
                                                    continue;
                                            }
                                            k2.logToFloatingWindow("【目标类名解析失败】将启动的 Intent 关键信息：" + "\n- action: " + intent.getAction() + "\n- data: " + intent.getData() + "\n- type: " + intent.getType() + "\n- pkg: " + intent.getPackage() + "\n- categories: " + intent.getCategories() + "\n- flags: 0x" + Integer.toHexString(intent.getFlags()), "warning");
                                            return null;
                                        }
                                    case 1714788811:
                                        return intent.getComponent().getClassName();
                                }
                            } catch (Throwable th2) {
                                k2.logToFloatingWindow("【解析目标类名异常】" + th2.getMessage(), "error");
                                return null;
                            }
                        }
                    }

                    @Override // android.app.Instrumentation
                    public void callActivityOnDestroy(Activity activity) {
                        String name;
                        String str = "ۤۛۖۢۘ۬ۚۜۥۘۡۥۦۘۖ۫ۜۘۘۤ۟ۦۛۡ۟ۜۗۖۛ۫ۖ۟ۛ";
                        while (true) {
                            switch (str.hashCode() ^ 265181990) {
                                case -2060966487:
                                    name = "<null>";
                                    break;
                                case -1007978884:
                                    String str2 = "ۛۧۦۘۦۤۘۥۜۙۛۦۘ۫ۜۧۘۜۢۛ۠ۘۘۗۜ۠ۢۡ۠۠۬ۖۛۡۚۥۥۨ";
                                    while (true) {
                                        switch (str2.hashCode() ^ (-684912676)) {
                                            case -721989804:
                                                str = "ۤۚۨۘۛۗۦۛۡ۠ۥ۫ۙ۬ۙۜۚۥۚۡۨۛۥ۫ۥۘۥ۬ۛ۫ۚۧۛۥۨۘۥۤۢۢ۟ۨۘۖ۠ۚ";
                                                continue;
                                                continue;
                                            case -119985608:
                                                if (activity == null) {
                                                    str2 = "ۥ۫ۤۙۖۦۘۧۘۦۧۗۚۙۜۘ۟۠ۖۗۙ۫ۨۥۥۨ۬ۡۙۛۘۖۙ۫ۢۦ۟";
                                                    break;
                                                } else {
                                                    str2 = "ۜۥۛ۠ۧۥ۫۠ۤۗ۠ۙۛ۬ۜۘۛۧۢۡۤۙۥۨۖۘۘۘۗۜۗۡۧۘۥۘۜۧۛۧۤ۠ۗۗۡ";
                                                    break;
                                                }
                                            case 20198161:
                                                str = "ۚۘۥۧ۬ۖۘۘۥۘۨۡۥۘۗۘۧۥۘۨۘ۫ۘۢ۬ۘۚۧۙۢۦۧۡۘ";
                                                continue;
                                            case 648046176:
                                                str2 = "ۧ۟ۦۘۘۥۡۘۘۖۦۦ۠۠۟ۥۚۙۗۛۧۙۡۘۗۥۙۙۖۦۗ۫ۤۤۖۦۘۡۖۘۤۜۡ۟ۥ۬";
                                                break;
                                        }
                                    }
                                    break;
                                case 943108471:
                                    name = activity.getClass().getName();
                                    break;
                                case 1417500200:
                                    str = "۠۬ۖۘۗۙۨۘ۬۫ۙ۫۠ۨۚۜۛۥۤۧ۟ۜۘۦ۫ۤ۠ۗۜۨۗۧۖۡۗۤۛۗۖۛۖۚۤۙ";
                                    continue;
                            }
                        }
                        k2.logToFloatingWindow(h.e("sBC92wUl9kHXXIW/ZTqF\n", "V7oqPoqGH9U=\n", new StringBuilder(), name), "info");
                        try {
                            Utils.onActivityDestroyed(activity);
                        } catch (Throwable th) {
                            k2.logToFloatingWindow("onActivityDestroyed 调用异常：" + th.getMessage(), "warning");
                        }
                        try {
                            this.val$originalInstrumentation.callActivityOnDestroy(activity);
                        } catch (Exception e) {
                            k2.logToFloatingWindow(h.d("rZ1I69sycpgkQadNLO47jSxZskMh3jeIMV+kdW9/9kqtmW42bw==\n", "RS3LDE+aUvs=\n", new StringBuilder(), e), "error");
                        }
                    }

                    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
                    /* JADX WARN: Failed to find 'out' block for switch in B:96:0x01d3. Please report as an issue. */
                    /* JADX WARN: Removed duplicated region for block: B:395:0x05c6 A[EXC_TOP_SPLITTER, FALL_THROUGH, SYNTHETIC] */
                    /* JADX WARN: Removed duplicated region for block: B:555:0x0605 A[SYNTHETIC] */
                    /* JADX WARN: Removed duplicated region for block: B:556:0x0711 A[SYNTHETIC] */
                    /* JADX WARN: Removed duplicated region for block: B:557:0x072e A[SYNTHETIC] */
                    /* JADX WARN: Removed duplicated region for block: B:558:0x0732 A[SYNTHETIC] */
                    /* JADX WARN: Removed duplicated region for block: B:559:0x0604 A[SYNTHETIC] */
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                    */
                    public Instrumentation.ActivityResult execStartActivity(Context context2, IBinder iBinder, IBinder iBinder2, Activity activity, Intent intent, int i, Bundle bundle) {
                        Context context3;
                        String strSafeResolveTargetClassName;
                        JSONObject jsonResult;
                        String str;
                        String str2;
                        String strDecrypt;
                        String strDecrypt2;
                        String strDecrypt3;
                        try {
                            logIntentDetails("【即将启动窗口】Intent 基本信息", intent);
                            String str3 = "ۙۤۦۖۡۘۘ۬ۡۜۘۚۧۗ۟ۧ۬ۘۥۡ۟۠ۨۢۨۘۖۥۡۜۖۘۘۚۤۢۤۖۖۘۡۡۦۜۜۧ";
                            while (true) {
                                switch (str3.hashCode() ^ 410005879) {
                                    case -2021206583:
                                        context3 = this.val$context;
                                        break;
                                    case -2016980825:
                                        context3 = context2;
                                        break;
                                    case -1049920853:
                                        str3 = "۫۠ۡۙۗ۟ۧۜۘۛۢۖۘۛۛ۠ۜۢۤۡ۫ۥۧۜ۠ۘۦۘۘ۟ۡۥ";
                                        continue;
                                    case -136797736:
                                        String str4 = "ۨۢۦۘۗۡۜۘۙۜۥۖۘۧۨۖۚ۫ۖ۠ۜۖۘۥۧۗۢۢۥۡۤۚ۟۠۬ۛۨۦۜۙۚ۟ۗ۟";
                                        while (true) {
                                            switch (str4.hashCode() ^ (-801395423)) {
                                                case -1616869873:
                                                    str3 = "ۖ۬ۛ۬ۧۚۢۨۦۖۙ۠ۥ۟ۛ۟ۛۙ۠ۘ۬۟ۘۡۜ۫ۖۘ۟ۡۘۧۖۛۨۖۘ";
                                                    continue;
                                                    continue;
                                                case -661847411:
                                                    str3 = "ۘ۬ۗۤۘۦ۬ۦۧ۟۟ۢۦ۠ۖ۠ۤۥۧۙۚۥۡۡۚۙۘۘۡۖۘ";
                                                    continue;
                                                case 483185566:
                                                    if (context2 == null) {
                                                        str4 = "۫ۥۥۦۤۖۘۜۗۛ۟۟ۤ۬ۙۢۗۙۡۘۖۛۨۖۜۘۜ۟ۘۚ۟ۘۘ";
                                                        break;
                                                    } else {
                                                        str4 = "ۜۜۙۘۖ۠ۧۗۡۘۡۧۗۜۤۥۤۜۚ۫ۤۤ۫ۘۡۢ۟۬ۧ۬ۛ۠ۤ۬ۜۚ";
                                                        break;
                                                    }
                                                case 1856920177:
                                                    str4 = "ۢۙۚۘۘۦۘۤۡۘۦۙۜۘۡۛۥۥۘۥۘۧ۫ۛۢۢۡۙۤۜۙۡ۟۬ۘۘۖۢۥۘۘۥۧۘ۟ۚۢۜۘۦۗۥ";
                                                    break;
                                            }
                                        }
                                        break;
                                    default:
                                        continue;
                                }
                            }
                            strSafeResolveTargetClassName = safeResolveTargetClassName(context3, intent);
                            String str5 = "۬ۧۦۘۛۙۤۙۤۜۘۗ۬ۖۨۚۢ۠۟ۛ۬۬ۨۘ۫۠ۖۘ۠ۡۡۖۘۘۘ۫ۚۧۨۙ۬ۛ۠ۗۢۛۛۖۡۢۛ۬۫ۗۤۖۛۨۧ";
                            while (true) {
                                switch (str5.hashCode() ^ (-1160203262)) {
                                    case -745383986:
                                        k2.logToFloatingWindow("窗口切换到：" + strSafeResolveTargetClassName, null);
                                        break;
                                    case -366056937:
                                        String str6 = "ۖۦۦۤۙۢۨ۬ۘۘۙ۠ۧ۫ۗۢۧۜ۟۫ۖ۫۬ۦۘۘۦۜۡۘ۠ۨۛۚۘۜۛ۠ۖۘۜۥ۟۠ۛۡۘ۟ۨۡۥۧۢ";
                                        while (true) {
                                            switch (str6.hashCode() ^ (-1497017913)) {
                                                case -588580529:
                                                    str6 = "ۥۖۘۘۦۚۦۘۚ۟ۡ۬ۜۘۡۙۧۙۙۡۘۥۥۗ۫ۘۙ۟ۛ۫ۧۦۘۙۤۘۡۧ";
                                                    break;
                                                case 422419927:
                                                    if (strSafeResolveTargetClassName == null) {
                                                        str6 = "ۧۙۘۚ۫ۤۙۖۢۜۨۨ۫ۦۙ۬۫ۜۘ۠ۘ۫ۗۗۚۚۙۗۙۢۨۧ۟۬";
                                                        break;
                                                    } else {
                                                        str6 = "ۜۛۚ۫۟ۡۛ۟۬۠۟ۘۘۧۧۡۡۥۢۦۥ۬ۙۜۦۘۙۥ۫ۧۢۧۚ۠ۡۘ۠ۜۘۘۚۥۤۨ۬ۜۜۡۨۥۦۗ";
                                                        break;
                                                    }
                                                case 1583767510:
                                                    str5 = "ۖۜۨۘۘۤۢۛۥۖۙۡۡ۟ۧۦۘۘۧ۬ۛۢۥۛۦۧۘۧۡۢۖۨۜۘۚۚۧۖ۠ۨ";
                                                    continue;
                                                case 2094369206:
                                                    str5 = "ۘ۠ۛۡۢۡۘ۟ۤۘۗ۫ۗۤ۬ۢۙۙۥۘۚۗۙۥ۠ۘ۫ۡۧۢۨۘۖۤ۬ۡۖۜۥ۟ۢۨۛۡۘ";
                                                    continue;
                                                    continue;
                                            }
                                        }
                                        break;
                                    case 1487819691:
                                        String str7 = "ۥۥۖۘۖۦ۟۫ۘ۫ۥۖۡۘۢۡۛۨۦۘ۟۫ۨۘۛ۠ۘۖۘۖۧۚۢۚۥۙۢۨۢۤۧۖۜۜۘ";
                                        while (true) {
                                            switch (str7.hashCode() ^ (-1345269993)) {
                                                case -2131679089:
                                                    k2.logToFloatingWindow("【隐式跳转】未解析到目标类名，URI: " + intent.getData(), "info");
                                                    break;
                                                case -2032378247:
                                                    String str8 = "ۡ۟ۨ۟ۥۜۜۗۡۦۛۚۦۙۦۘۥ۫ۚۘۚ۠ۜۜۥۘۘۚۙ۫ۡۧۘۨۗۤۘۗ۠ۛۖۦۘ۫ۖۚۧۚۥۘ۫ۨۘ۟ۖۧۘۤ۠ۖ";
                                                    while (true) {
                                                        switch (str8.hashCode() ^ 640860386) {
                                                            case -2102363849:
                                                                str8 = "ۦ۬ۥۘ۟ۨۘۘۙۤۘ۠ۨۘۘۛۘۘۘۙۛۡۨۢۨۜ۫ۤۧۨۧۨۨۗۚۥۘۘۜۨۘ";
                                                                break;
                                                            case -1287037415:
                                                                str7 = "ۗۦۗۜۘۢۚ۟ۨۘۙ۬ۘۘۨۡۗۖۖۚۖۡۘۚۨۦۧۘۧۛۦۤۧۚ۬ۙۚۙ";
                                                                continue;
                                                                continue;
                                                            case -313369408:
                                                                if (intent.getData() == null) {
                                                                    str8 = "ۜۧۘۧۙۙۧ۬ۥۘ۫۫ۛۜۜۤ۠ۜۤ۠ۥ۟۟ۤۖۘۡۖۧۘۚۜۗ";
                                                                    break;
                                                                } else {
                                                                    str8 = "۠ۤ۠ۗۖۖۘۨ۠ۙ۠۬ۥۢۢۛۗۥ۠ۙۨۢۙۡ۫ۢۤۛۘ۬ۘۛۥۨۘۜۚۘۨۧۥۘ۟۟۟۠ۢۨۘۡ۠ۚ";
                                                                    break;
                                                                }
                                                            case 1618566168:
                                                                str7 = "ۖۚۛۤۡۧۚۚۡۘ۫۫۟۠ۡۦۘ۟۟ۢۘۜۡۘ۟ۘ۫۠ۜۢۧۚۦۘۥۙۘۢۗۘۘۦۘۨۘۦ۫";
                                                                continue;
                                                        }
                                                    }
                                                    break;
                                                case -253934056:
                                                    k2.logToFloatingWindow("【隐式跳转】未解析到目标类名，action: " + intent.getAction(), "warning");
                                                    break;
                                                case 1621529029:
                                                    str7 = "۠۫ۛۤ۫ۖۘۙ۠ۘۖۢ۬ۡۗۘۘۤۚۜۖۖۨۖۤۨۖۦۨۘۘۛۡ۟ۜۛ۬ۜۖۘۘۡۢۢۚۦۘۧۤ۬";
                                                    continue;
                                                default:
                                                    continue;
                                            }
                                        }
                                        break;
                                    case 1901403191:
                                        str5 = "۬۫ۨۘۖۤۢ۠ۥۘۗۦۥۨۥۜۚۛۦۘۨ۟ۚۧۡۡۚۜۘۘۦۢ";
                                        continue;
                                    default:
                                        continue;
                                }
                            }
                            try {
                                Bundle extras = intent.getExtras();
                                String str9 = "ۘۘۖۘۙ۬ۥۘۙۜۙ۠ۗۤ۫ۢۨ۠ۢۨۘۥۥۥۙۙۨۙۛۡۘۘۦۘۘۢۛۨۗۧۧ";
                                while (true) {
                                    switch (str9.hashCode() ^ 1281213967) {
                                        case -1718891003:
                                            break;
                                        case -1687425223:
                                            String str10 = "ۘۡۨۘۙ۫ۢۦۤۢۖ۠ۖ۟۬ۖۛۗۙ۬ۜۙۥ۬ۖۘ۟ۘۨۘ۬۬۠۬ۦۘۗۜ۠";
                                            while (true) {
                                                switch (str10.hashCode() ^ 2009200622) {
                                                    case -2010269014:
                                                        str9 = "ۖۧ۬ۙۜۧۘۦۤۘۦۗۨۖۘۧۢۥۘ۫ۗ۫ۥۛۘۘ۠ۗۥۘۡۤۦۘۗۤۧۙۨ۠ۢۤۧۢۜۨۧ۬ۖۖۧ";
                                                        continue;
                                                    case -289679653:
                                                        if (extras == null) {
                                                            str10 = "۟۠ۦۙۦۖۘۗۗۗۙ۬ۨۘۨۛ۬ۜۘۖۘۨۘۜۥۡۘۧۚۖۘۦۧۗۛۜ۠ۙۙۛۙۤۡۨۛۦۧ۟ۡۙۢۛۧ۬ۥۢۨ";
                                                            break;
                                                        } else {
                                                            str10 = "ۗۥ۫ۚۦ۬ۘۙۙۚۥۙ۠ۖۚۙۧ۬ۚ۟ۜۘۚۤۘۘۜ۟ۖۘۨ۠ۘۘۖ۟ۥۘۘۙۚ۠ۘ۟ۨۘۧۡ۬ۨۘۙۤۜۘۚۜۛ۠ۡۘ";
                                                            break;
                                                        }
                                                    case -37490436:
                                                        str10 = "ۖۨ۫ۗۙۦ۬ۗۡ۬ۚۗ۫ۧۛۘۨۙۡۚ۫ۡۖۢۙۥۙۦۘ۬ۗۗ۟ۦۡۧۘۛۡۛۢۤۖۘۖ۟ۘۧۧ";
                                                        break;
                                                    case 2070843711:
                                                        str9 = "۬ۤ۫ۦۨۢۗۥ۬ۥۜ۬ۨۘۙۥۢۖۘۘ۠۠ۜۥۧۘ۬ۤۖۜۦۘۥۛ۟ۧۛۡۦۧ۫ۜۛۧۘۥۘۥۥۡ۟ۖۖ۟ۙۡۘ";
                                                        continue;
                                                }
                                            }
                                            break;
                                        case -371692011:
                                            str9 = "ۜۥۧۘۗۘۘۨۤۜۘۘۗۦۘۘ۠ۤۙ۬ۖۢۜۚۘ۟۫۬ۗۖۚۘۜۘۚۙ۬ۖۚۜۘۢ۟ۛ۟ۘۜۜ۟ۨۜۙ۠";
                                        case 1967424979:
                                            String str11 = "۬ۚۨۗۧۘۤۜۗۙۤ۫۬ۖۙۘۖۜ۬ۥۘۧ۬ۚۤۘۧ۟ۜۧۘ";
                                            while (true) {
                                                switch (str11.hashCode() ^ 44161267) {
                                                    case 159662266:
                                                        str11 = "ۥۤۚۦۖۥۖۦۦۖ۠ۥۚۚۚۛۙۦۘ۠۟۬ۘۤۗۙۦۨۘۤۤۦۘۘۨۥۘ۠ۨ۟۠ۖۨۖۧۢ۟ۜۧۘۥۡۜۘ۠ۛۡۘ۠ۚۗ";
                                                    case 659745406:
                                                        String str12 = "ۨ۟ۧۛۘۧ۬ۖۜۚۨۖۘۘ۟ۦ۠ۜۛۢۖۘۘۘۥ۬ۥۘۧۘۛۤۘۘ";
                                                        while (true) {
                                                            switch (str12.hashCode() ^ (-1497140759)) {
                                                                case -1488419373:
                                                                    str11 = "ۧۛۘۘۧۙۖۘۚۡۗۥۜۡۘۜ۬ۡۚۙۙۜۛۡۢۚ۫ۗۖۘۘۛۚۨ۬ۧۜۘۙ۠ۙ۫ۗ۫۟ۦۡۘۜۘ۟۠ۚۖ";
                                                                    continue;
                                                                case -886777623:
                                                                    str12 = "۬ۡۨۚۢۡۘۡ۬ۥۘ۫۫ۥۛۖ۠ۜۧۘۢۚۡۘۢ۠ۘۘۡۙۖۘۧۢۜۘۡۙۜۗ۟ۡۘۧۨۥ۟ۧۥۘ";
                                                                    break;
                                                                case -352311719:
                                                                    if (!extras.isEmpty()) {
                                                                        str12 = "ۘۛۙ۬ۨۘۛۖۗۢۧ۟ۚۦۨۙۧ۫ۗۢ۬ۥۦۖ۬ۥۖۡ۬ۘۘۚۘۖۘۛۘ۠ۨۨۧۘۧۖۜۧ۟ۧۥۧۡۘ";
                                                                        break;
                                                                    } else {
                                                                        str12 = "ۤۚۥۖ۫۬ۙۧۘۡۘۢ۫ۙۛ۠ۤۘۗۙۘۜ۫۫ۛۙۖ۬ۙ۠ۢ۟۟۠ۛۛۘ۬۫ۡۦۙۨۛ۟ۢ۫ۘ۟ۜۨۖ";
                                                                        break;
                                                                    }
                                                                case 329959774:
                                                                    str11 = "۟ۙ۫ۤۚۜۘۗۧۥۘ۠ۙۦۘۧۖۡۧ۠ۖۘ۟ۡۜ۫ۦۥ۫ۡۗۦۦۗۡۛۘۜۖۘۢ۫ۥۘۡ۫ۜۘ۬ۦۛۨۛۙ";
                                                                    continue;
                                                            }
                                                        }
                                                        break;
                                                    case 1289444345:
                                                        StringBuilder sb = new StringBuilder("跳转参数：");
                                                        Iterator<String> it = extras.keySet().iterator();
                                                        while (true) {
                                                            String str13 = "ۚ۬ۨۜۗۡۘ۬۟ۖۘۢۦۦۦ۬ۘۘ۟ۢۘۘۥۧۘۛ۟ۚۘۤ۠۠ۧۡۘۘۧۘۘۙۖۧۘۚ۬۬ۚۦۜۗ۟ۚۤۚ۟";
                                                            while (true) {
                                                                switch (str13.hashCode() ^ (-1548252390)) {
                                                                    case -1742556600:
                                                                        String next = it.next();
                                                                        Object obj = extras.get(next);
                                                                        String str14 = "۟ۛۦۘۢۜۥۘۢۤۨۡۥ۫۬۬ۡۘۡۡۥۖۢۨ۫ۖۥۘ۬ۡۧۡ۬ۢۨۚ۫ۦۡۥۧۨ۠ۤ۫ۘۦ۫ۤۤۚۛ";
                                                                        while (true) {
                                                                            switch (str14.hashCode() ^ (-1179362932)) {
                                                                                case -1270339871:
                                                                                    strDecrypt3 = "null";
                                                                                    continue;
                                                                                case -500789781:
                                                                                    strDecrypt3 = obj.getClass().getSimpleName();
                                                                                    continue;
                                                                                case -230521180:
                                                                                    str14 = "ۡۤ۬ۙ۟۬ۜۚۡۘۢۛۡۤۤۢۚۡ۬ۙۗۚۤۚۘۙۜۖۘۡۜۡۘ۫۠ۦ۬۟ۥۘ۫ۚ۬ۛۙ۬ۘۥۚ۟ۢۥ";
                                                                                    break;
                                                                                case 171422938:
                                                                                    String str15 = "ۖ۠ۘۘۥۜۘۧۜۘۘۥۤۚۚۚۧ۫۟۠ۤ۟ۚۨۡۘۜۥۖۘۧۧۨ";
                                                                                    while (true) {
                                                                                        switch (str15.hashCode() ^ (-1047227383)) {
                                                                                            case -1687935488:
                                                                                                str15 = obj != null ? "ۖۘۡ۬۟ۘۘۥۘۜۥۡۛۘۙۧۙۡۡۘۧۡۜۘ۫ۢۥ۬ۦۜۘۥۢۡۗۛۙۜۧۘۘ۫ۘ۠ۤ۬ۥۘ۬ۨۖ۠ۙ۠" : "ۨۛۤۘۘۨۙ۟ۖۘۦۙ۟ۤۗۖۘۚۧۡۘۢ۠۫ۨۜۧۚۛۥۦۡ۬ۖۢ۬ۧۨۜۧۚۨۙۢ۟ۡۗۥۢۜ۬ۨۚ۠۟ۙۙ";
                                                                                            case 820231072:
                                                                                                str14 = "ۢۦۖۘ۫ۙ۬ۗ۫ۦۘ۠ۤۦۡۦۚۘۘۧ۬ۦ۟۠۟ۘۛۗۜۥۜۘ۟ۡۙۜۘۨۘۡۗۦۢۖۙ۫ۖۦ۬۫";
                                                                                                break;
                                                                                            case 1129145543:
                                                                                                str15 = "ۘۚۘۘۦ۠ۡۧ۟ۛۖ۠ۢ۟ۙ۬ۨۢۥۘۗۖۘۗ۫ۜۘۢۖۦۨۙۨۘۘۜۢۨۘ";
                                                                                            case 1672059446:
                                                                                                str14 = "۫۫ۥۘۦۛۜۛ۬ۥۦۛۦ۠۠۫ۧۖۧۘ۠ۡۙۖۢ۟۫ۥ۠ۦۨۛۚۦۘۚۦۘ";
                                                                                                break;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                            }
                                                                        }
                                                                        sb.append("\n- ");
                                                                        sb.append(next);
                                                                        sb.append(" (");
                                                                        sb.append(strDecrypt3);
                                                                        sb.append(") = ");
                                                                        sb.append(String.valueOf(obj));
                                                                        break;
                                                                    case 210906068:
                                                                        String str16 = "ۗۘۗۖۨۨۦۨۢۜۦۦۡۥۨۘ۟ۤ۟ۥۤۖۢۦۧۜۜۢۘۦ۫ۗۘۘۛۜۧۘ";
                                                                        while (true) {
                                                                            switch (str16.hashCode() ^ (-2044077710)) {
                                                                                case -1911547253:
                                                                                    str16 = "ۨ۠ۨۘۛۙۜۘۗ۟ۛۙۖۘۙ۟ۡۘۥۛۗۦ۬ۖۘۘۘۨ۠ۘۤۛۨ۟ۗ۠ۦۡ۠ۗۛۛۚۙ۫ۤ۠۠۫ۜ۫ۧ۠ۛ۠۟ۘۖ";
                                                                                case -1693646826:
                                                                                    str13 = "ۚۦۗۛ۟ۡۛۜۦۜۛ۠ۖۘۨۙۘۘ۠ۚ۬ۥۙۦۤۨۥۘۙۘ۟";
                                                                                    break;
                                                                                case -1081721206:
                                                                                    str16 = it.hasNext() ? "ۖ۠ۙۚۧۖۘۖۦۚ۬ۚۦۘۥۨۧ۠ۜۥۧ۟ۗ۟ۤ۬۫ۨۡۘۚ۟ۧ۬ۢۖۦۤۘۘۖۢ۬ۗۤۥۘۖۨ۟ۖۦۥۘ" : "ۢۖۧۘۜۗۤۨۖۥۙۡ۠ۙۤۚۙۜۦۗۥۘۥۘۗۘۨۥۥۙۚۢۥۧۗ۠";
                                                                                case -218085413:
                                                                                    str13 = "۫۟ۜۢۛۨۜۡۘ۠ۜۡۘ۟۫۫۠ۨۗ۠۫ۧۜۘ۠ۧۦۘۛۖۜۘۧۥ۫ۢۤۜۖ۠۬ۙۗۖۘ۟۟ۨۛۚۨ";
                                                                                    break;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case 370555250:
                                                                        str13 = "ۧ۠ۡۘۦۗۗۚ۬ۨۛۛۘۘۜۖۥ۬ۚۥۘۥ۟ۢۡۢ۫ۘۚۘۘۘۙۨۘۙۦۙۦۡۧۘۤۜۜۘ۟ۖۚۚۚۨۘ۫ۢۥۘ";
                                                                    case 934932231:
                                                                        break;
                                                                }
                                                                k2.logToFloatingWindow(sb.toString(), "debug");
                                                                break;
                                                            }
                                                        }
                                                        break;
                                                    case 2105821222:
                                                        break;
                                                }
                                            }
                                            break;
                                    }
                                }
                            } catch (Throwable th) {
                                k2.logToFloatingWindow("【extras 打印异常】" + th.getMessage(), "warning");
                            }
                            Set<String> setLoadBlackActivitiesFromFile = HookManager.loadBlackActivitiesFromFile(this.val$context);
                            String str17 = "ۘۗۘۢۦۘۘۤ۬ۧ۫ۛۘۘۛۧۥۜۚۚۧ۬ۜۛۖ۬ۦۚ۠۟ۥۘۜۡۤۚۛ۠ۤۦۙۘۖۛۛۤۖۙۘۛۢۥۘ۫۫۠";
                            while (true) {
                                switch (str17.hashCode() ^ (-466269393)) {
                                    case -1934119065:
                                        str17 = "ۨۗۖۘ۟ۦۘۚ۫ۖۘۙۗ۠ۗۜۥۨۛۙۚۦۙ۟ۜۤۛۢۡۖ۟ۡۘ";
                                        continue;
                                    case -1477223179:
                                        break;
                                    case -277023590:
                                        String str18 = "ۥۡۖۚۥۘ۠ۦۨۡۖۦۘۧۘۨۗ۫۟ۧۤۙۙۖۥۢۖ۫ۖ۠ۗۢ۬ۚۡۧۘ";
                                        while (true) {
                                            switch (str18.hashCode() ^ 299488513) {
                                                case -2061653809:
                                                    if (strSafeResolveTargetClassName == null) {
                                                        str18 = "۬ۦۚۜۜۥۤۡۚۚۡۜۘۘۛ۟ۧ۬ۜۤ۫ۚۤۢۘ۬۟ۖ۫ۢ۬ۚۖۤۖ۬ۙ";
                                                        break;
                                                    } else {
                                                        str18 = "ۗۧۧ۟ۜۗۥۨۜۘۥ۫ۜ۬ۖۘ۬ۖۘۚۘۘۗۖۨۘۜ۬ۖۢۖ۫۫ۘۘ۫ۜۜ";
                                                        break;
                                                    }
                                                case 798197199:
                                                    str17 = "ۗ۫ۥۜۦۦۘۙۧ۠ۗۜ۠ۛۧۖۥۢۥ۠ۖ۠ۨ۫ۗۦۦۡ۫ۘۘ";
                                                    continue;
                                                    continue;
                                                case 1267892907:
                                                    str17 = "ۖۜۤۤ۟ۦۙۗۨۘۢۛۡۦۖۖ۬ۢ۫۟ۢ۟۠۠ۛۥ۠ۘۘۚ۫ۗۦ۟ۚۖۨۜۘۘۥۥۘۖۦۜۖ۬ۢۚۖۘۚۥۘۘۧۛۖ";
                                                    continue;
                                                case 1612214951:
                                                    str18 = "ۥۙۦۡۖۖۘ۬ۙۦۘۨۜۥۘۛ۟ۗ۟ۡۢ۟ۧۗۡۢۡۗ۟ۢۨۘۦۤ۟ۡۚۜۗ۫۫ۥۘۚۘۚ۫ۨ۟ۧۜۙۨۢۘۡۧ۟";
                                                    break;
                                            }
                                        }
                                        break;
                                    case 2117047364:
                                        String str19 = "ۗ۫ۤ۠ۤۡۘۚۤۗۢۢۡۘ۬ۗۨۘۚۤۖۘۥۛۜۘۨۢۛۤۘۡۚ۬";
                                        while (true) {
                                            switch (str19.hashCode() ^ (-321228444)) {
                                                case -1842952940:
                                                    String str20 = "ۚۤ۟ۤۙۡۘۙۢ۟۟ۖۘ۠ۛۥۘۙۥۤ۫۟ۦۚ۟ۜۘ۠ۥۧۘۦۜ۬ۧۚۥۨۘۘۖۖۚۘۨۡۘ";
                                                    while (true) {
                                                        switch (str20.hashCode() ^ 604084347) {
                                                            case -1433170162:
                                                                str20 = "ۚ۠۠ۢ۟ۧ۠ۘ۟۬۫ۧۙ۠ۢۙۘۘۢۨۚ۬ۜۛۧ۠ۨ۠ۖۛ";
                                                                break;
                                                            case -1031244921:
                                                                str19 = "۟ۗۘۨۛۦ۠ۧۜۘۙ۠ۘۘۚ۟ۢۢۡۚ۫ۚۥۤۗۦۛۥۦۘۛۥۨۘ";
                                                                continue;
                                                                continue;
                                                            case 721481961:
                                                                str19 = "ۙ۬ۤۨۧۤۗۦۖ۫ۛۜۘ۟ۢۡ۠ۘۘۘۨۘۨۘۛۡۖۘۚۘۜۘۚۥۜۘ۠ۥۘۘۥ۠ۘۘۦ۬ۦۘۧۤۤ";
                                                                continue;
                                                            case 2085403515:
                                                                if (!setLoadBlackActivitiesFromFile.contains(strSafeResolveTargetClassName)) {
                                                                    str20 = "ۘۥۜۘ۬ۡۦۘ۠۟ۤۖۖۖۘۖۤۚۘۚۧۙۚۗۚ۟ۨۡۨ۟۬ۗۜ۠ۦۘ۠ۥۧۘ";
                                                                    break;
                                                                } else {
                                                                    str20 = "ۜۡۧۤۙۙ۠۟۠ۡۜۙۨۘۤۖۧۘۨۡۥ۟ۙۨۤۘ۬ۢۘ۫ۜۧۗۙۗۜۘ";
                                                                    break;
                                                                }
                                                        }
                                                    }
                                                    break;
                                                case -1648793429:
                                                    break;
                                                case -2648246:
                                                    str19 = "ۥۜۛۧۖۧۗ۫ۦۦۛۘۙۨۤۡ۬ۛۦۜۡۛۧ۟ۛ۬ۘۤ۬ۦۘ";
                                                    continue;
                                                case 1628332669:
                                                    String str21 = "ۜۚۜۘۚۚۚۥ۫۠ۦۚۡۗۥۥ۫ۚۘۡۚ۠ۚۜۖۦۖۜۘۙ۟ۥ";
                                                    while (true) {
                                                        switch (str21.hashCode() ^ (-1033772438)) {
                                                            case -1014667157:
                                                                Activity activityECt8jHZ4 = Utils.ECt8jHZ4();
                                                                String str22 = "ۚۤۗۤۤۦۘۖۛۨ۬ۘۘۘۧۘۥۘ۠ۖۜۘ۟ۦۡۗۚۖۤ۟ۜۧ۫۠ۙ۬۟ۘۦۙۤۦۢۘۖۦ";
                                                                while (true) {
                                                                    switch (str22.hashCode() ^ (-1047275299)) {
                                                                        case -1524601557:
                                                                            k2.k3zLJuvX(activityECt8jHZ4, "即将要启动的窗口命中黑名单类：" + strSafeResolveTargetClassName);
                                                                            break;
                                                                        case -949283447:
                                                                            str22 = "ۛۗ۫ۧۧۖۡۘۗۤۨۘۖ۫ۗۦۘۖ۬ۙ۟ۨ۬ۤ۫۠۟ۛۖۛ۟ۗۨۨۥۘ۠ۙۨۜۡۛۧۤ۠ۨۛۙ";
                                                                            continue;
                                                                        case -68039559:
                                                                            break;
                                                                        case 1899954972:
                                                                            String str23 = "ۡۖۚ۫ۙۥۘ۫ۗ۠ۨۦۙۗۖۧۘ۬ۧ۠ۦ۟ۚۖۢۥۘ۠ۖ۫۠۫ۘۘۡۗۦۘۧۗۨۘۥ۠ۜۘۜۥ۠";
                                                                            while (true) {
                                                                                switch (str23.hashCode() ^ (-592782901)) {
                                                                                    case -515667248:
                                                                                        str23 = "۬۠ۡۘ۬ۨ۬ۡۗۨۘۢ۫ۘۘۜ۟۟ۗۖۤۙۨۨۛۦ۫ۡۘۜۥۡ";
                                                                                        break;
                                                                                    case -386940394:
                                                                                        str22 = "۟ۤۧۚۨۘۨ۫ۡۘۜۦۘۘ۠ۥۨۛۨۤۜۗ۫ۨۖۧ۫ۘۖ۫ۗۗۦۘۡۦۖ";
                                                                                        continue;
                                                                                        continue;
                                                                                    case 464501916:
                                                                                        str22 = "ۢۛۧ۟ۘۧ۠ۙۥۘ۠ۡۧۘۥۥۡۘۙ۟ۚۥۧۦۜۚۛۧ۠ۜۨۡۜۢ۠ۜۦۤ";
                                                                                        continue;
                                                                                    case 568783163:
                                                                                        if (activityECt8jHZ4 == null) {
                                                                                            str23 = "ۤۦۨۘۨۥ۟ۜ۠ۜۘۘۙۜۛۧۤۧۖۜۘ۬ۧۗۙۧۡۨۘۛۥۥۨۜۡۜۡۜۗۧۨۜۘۙۧۗۜۗۨۚ۟";
                                                                                            break;
                                                                                        } else {
                                                                                            str23 = "ۤۦۢۤ۬ۧۦ۫ۥۘۥۨۥ۬۬ۛۙۗۘۘۦۨۡۘۘۛۤ۫۟ۨۘ۫ۧۡ۬ۡۡۘۡۘۧۘ";
                                                                                            break;
                                                                                        }
                                                                                }
                                                                            }
                                                                            break;
                                                                        default:
                                                                            continue;
                                                                    }
                                                                }
                                                                k2.logToFloatingWindow("黑名单窗口拦截：" + strSafeResolveTargetClassName, "warning");
                                                                break;
                                                            case 9918393:
                                                                break;
                                                            case 463628928:
                                                                str21 = "ۜۚ۫ۢۡ۟ۦۦۡۘۦۚۢۛۡ۫ۚۖۛ۟ۨ۬ۚ۠ۜۛۧۛۨۧۢۜۘۨۡۨۥۖۜۚۡۖۖۘ۬ۡۚ۫ۘ۟";
                                                                continue;
                                                            case 1518621707:
                                                                String str24 = "ۚۥۙ۬ۖۧۚۖۙ۫ۖۘۨۦ۟ۨۙۛۖۙۨۘۜ۟۟ۤۖ۫ۤۤۛ۬ۡۤۡۜۗۘۥۤ۠ۡۤ۬ۨۖ۠ۜۜۘۨ۟۠ۘۜۘ";
                                                                while (true) {
                                                                    switch (str24.hashCode() ^ (-565669871)) {
                                                                        case -468373360:
                                                                            if (!s0.isDebug()) {
                                                                                str24 = "ۙۙۧۤۤۖۙۜۖۘۖ۠ۗۙۙۡ۠ۨۜۘۨ۠ۦ۫۫ۨۘۦۡۥۧ۫ۘۜ۫ۖ۫ۤۨۘ۠۬ۥۗۤۨۥۦۘ۫ۜۥۜ۠۟ۡۡۥۘ";
                                                                                break;
                                                                            } else {
                                                                                str24 = "ۜۚ۬۠ۗۡۘۗۧۡۧۢۥۘۚۧۥۤۤۨ۫۬ۥۡۖۛۗۚ۠ۛ";
                                                                                break;
                                                                            }
                                                                        case 506359970:
                                                                            str24 = "ۙۜۤۥۗۜۜۦ۠ۦۙۨۘ۫ۘۗ۫ۜ۠ۧۖ۠ۘ۫ۖۘۥۛۤۦۧۘۢۚۨۘ۠ۨۥۘۚۦۨۡ۬";
                                                                            break;
                                                                        case 1065596298:
                                                                            str21 = "ۗۖۛۧۗۨۧ۫ۥۜۢۡ۬ۙۥۖۥ۠ۧۘۘۘۖۨۜۖۡۜۘۚۜۖۘۨۚۦۘۨۚ۟ۢۜۧۥۡۦۘۘۡۙۗ۟ۡ۬ۥۘۘۙ۟ۢ";
                                                                            continue;
                                                                        case 1460644347:
                                                                            str21 = "۫ۧ۠ۙۤۛۘۚۜۘۖۨ۫۫ۤۜۘ۠ۛۡۢۢۡۘۡۥ۫۠۫ۡۤۘۘۙۛۜۘۙۘۗۚۢۖ۟ۦۥۘ";
                                                                            continue;
                                                                            continue;
                                                                    }
                                                                }
                                                                break;
                                                            default:
                                                                continue;
                                                        }
                                                    }
                                                    return null;
                                                default:
                                                    continue;
                                            }
                                        }
                                        break;
                                }
                            }
                            jsonResult = s0.getJsonResult();
                            str = "ۧۘ۠ۨۜۘۘۥۥۦۥۛۖۘۥۗۢۘ۬۫ۘۢۗۡۙ۟ۤ۫ۦۤۤۗ";
                        } catch (Throwable th2) {
                            k2.logToFloatingWindow("execStartActivity 外层异常：" + th2.getMessage(), "error");
                            throw new RuntimeException("execStartActivity 异常", th2);
                        }
                        try {
                            while (true) {
                                switch (str.hashCode() ^ (-1530629149)) {
                                    case -2133268350:
                                        StringBuilder sb2 = new StringBuilder();
                                        sb2.append("【跳转劫持】");
                                        String str25 = "ۚۤۤۗۦ۠ۙ۬۟ۨ۬ۡۘۗ۬ۧۜ۠ۦۥۡۢۗۚ۫ۜۥۘۢۙۖۘۖ۬ۥۧۙۙۦۦۛۛ۬ۙۜۡۘۘۜۛۥۘۗۢۡۧۗۢ";
                                        while (true) {
                                            switch (str25.hashCode() ^ 448790641) {
                                                case -2131972881:
                                                    String str26 = "۠ۖۙۘۤۦۘۚۙۛۖۦۥۘۥۥۚۨۙۘۘۧۦ۟ۛۦۧۘۢ۬ۜۜۖۛۛ۟ۥۘ۫ۙۙۥۦۖۘۢ۬ۚ۟ۢۘۖۢۡۜۢۖۡۢۥۘ";
                                                    while (true) {
                                                        switch (str26.hashCode() ^ (-889062958)) {
                                                            case -711433473:
                                                                if (strSafeResolveTargetClassName == null) {
                                                                    str26 = "ۘ۠ۧۙۨۧ۬ۨۘۘۘۦۛۚۨۘۢۘۦۘ۫۠ۧۚۤ۠ۡۡۘ۠۟ۜۘۜۙ۬ۜۜۥۚۥۥۗ۬ۥ";
                                                                    break;
                                                                } else {
                                                                    str26 = "۟ۖۧ۟۫ۜۘۧۗۜ۠ۦۚ۠ۙ۬ۧۥۥۘۙۨۦۘۨ۟ۦۘ۟ۙ۠ۨۧۢ";
                                                                    break;
                                                                }
                                                            case -593675975:
                                                                str26 = "ۢۗۢۨ۠ۖۨۤۖۡۖۘۢۚۘۘۨۖ۫ۗۛ۟۬ۧۧۧۜ۫ۡۨۧ";
                                                                break;
                                                            case -137051916:
                                                                str25 = "ۗۜۡۨۤۘۘۥۤۢۨ۟ۘۥۘۦۘۘۘۖۘ۬ۤۧۛۜۖۘۙۙۚ۫ۧۨۦ۬۠ۤۚ";
                                                                continue;
                                                                continue;
                                                            case 121309267:
                                                                str25 = "ۗۨۨۨۤۛۡ۬ۤۦ۟ۜۘۥۜۥۘۨۙۖۛۚۦۘۚۢۜۨۛۨۘۜۜۢۚۨ۟ۤۜۤ۫۬۫۬۟ۧۙۗ۟ۘ۫ۥۘۨۤۙ۠ۨۢ";
                                                                continue;
                                                        }
                                                    }
                                                    break;
                                                case -2088274945:
                                                    strDecrypt2 = "<未知>";
                                                    break;
                                                case 496567480:
                                                    str25 = "۟ۛ۠۟ۜ۠۠ۘۛۡۙۨ۬۬ۙۘۤۘ۬ۜ۠۬ۢ۬ۙۗۛۛۢۤۛۡۚ۬۫ۡ۫ۢ۟۬ۗۢۢۙۘۛۦۘ";
                                                    continue;
                                                case 1917361439:
                                                    strDecrypt2 = strSafeResolveTargetClassName;
                                                    break;
                                                default:
                                                    continue;
                                            }
                                        }
                                        sb2.append(strDecrypt2);
                                        sb2.append("\nshell跳转,不劫持");
                                        k2.logToFloatingWindow(sb2.toString(), "warning");
                                        break;
                                    case -1942299809:
                                        str = "۬ۤۚۨۛ۬۬ۡ۠ۖ۬ۗ۟ۥ۠ۢۡۥۘۦ۠ۚۜ۬۫ۨۗۥۘۖۤۚ";
                                        break;
                                    case -913248617:
                                        String str27 = "ۨۡۤۙۙۖۜۗۧۧۦۛۛۨۥ۟ۖۧۘ۫ۥ۫ۘۥۢ۟ۤۗۘۦۦ";
                                        while (true) {
                                            switch (str27.hashCode() ^ 984948644) {
                                                case -2079107733:
                                                    if (!intent.getBooleanExtra("shell_protected", false)) {
                                                        str27 = "ۙۧۨ۬ۦۜۘ۠ۤ۫ۜ۠۫ۧۧۚۙۘۜۘ۟ۡۧۨۨ۟۠ۖۡۥۚۜۘ";
                                                        break;
                                                    } else {
                                                        str27 = "ۖۗۦۘۡ۫۠ۤ۫ۡۢۚۛۦۡۢۙۢۘ۬۟ۨۦۤۘۘۚ۠ۘۘۘۜۥ";
                                                        break;
                                                    }
                                                case -72899784:
                                                    str = "ۘۛۧۜۖۖۤ۬۫ۚۖۘۘۤۙۦ۬ۙ۠۬ۘۧۤ۬ۘۤۜۘۧۚۡۘۧ۬ۚۙۤۨۜ۫ۧۚۙۧۚۜۚۛۨ";
                                                    continue;
                                                case 1800786973:
                                                    str = "ۖۤ۟۫۟۬ۧۧۦۘۖ۠۫ۚۙۜۢۛۦۧۘۡۗۜۚۡ۫۠ۥۨۘ";
                                                    continue;
                                                case 2033307193:
                                                    str27 = "ۘ۟ۡۘۚۘۤۥ۠ۦۘۙۘۜۘۤۘۖۘۨۧ۫۠۫ۜۘۢ۫۟ۡۤۥۘۚۦۙۡۙ۫ۙۛۦۦۢۗۚ۫ۛۚۙۦۢ۫ۥۘۜۤۡۨۤۙ";
                                                    break;
                                            }
                                        }
                                        break;
                                    case 1483527699:
                                        String str28 = "۠ۛۧۧ۠ۦۦۚۦۘۚۚۛۧۜ۠ۜۖۛۧ۟۫ۧ۫ۧۧۗۥۘۦ۫۫۠ۘۤۜۗ۠ۜ۟ۖۘۚۥۦۥۥ۟ۡ۫ۦۤۜۙۗۘ";
                                        while (true) {
                                            switch (str28.hashCode() ^ (-2023595518)) {
                                                case -1524549048:
                                                    String str29 = "ۧۧ۬ۙ۫ۨۘۢۦۦ۠ۦۨۙ۠ۦۧۥۦۘ۠۫۠۟ۧۛ۟ۥۖۘۨۜ۟ۤ۬ۨۛۥۦۘۚ۫ۥۘۙۜۡ۫۫ۘۘۚۘۘۥۨۘۛۘۖۘ";
                                                    while (true) {
                                                        switch (str29.hashCode() ^ (-101087727)) {
                                                            case -1089472813:
                                                                str29 = "۟ۥۦۛۗۦۘۛۦۛ۟ۚۧۢۙۧۦۤۥ۬۬۟ۡۦۢۧۜ۠۫ۡۘۡۛۗۜۗۧۡۡۘ۬ۦۧۘۖۢۦۘۨۙ۫";
                                                                continue;
                                                            case -682907347:
                                                                String str30 = "ۖۜۦۘۥ۟۟۠۫ۜۘۘۖۨۘۖۙ۫ۦۧۜۘۗۢۢۚۨ۟ۗۛۗ۬ۛۖۘۥۢ۠ۦۨۨۦۦ۠ۧۦۘۡۨۜۙ۬ۜۘ";
                                                                while (true) {
                                                                    switch (str30.hashCode() ^ (-836684510)) {
                                                                        case -680094804:
                                                                            str29 = "ۤ۠ۤۦۨۡۘۦ۠ۨۨۗۘ۫ۨۙۥۛۘۘۥۦۙۜ۠ۖۜۖۡۘۘۙۥ۫ۛۜۘۖۚۦۧۙۦۜۗۢ۬۫۟ۦۘۘۨ۟ۥ";
                                                                            continue;
                                                                            continue;
                                                                        case 310314057:
                                                                            str30 = "ۚۜۢۙۚۧ۫ۖۢۙ۟ۡۘ۫ۤۡۢ۫ۥۘۥۡۚۢۤ۫ۙۨۚۥۤ۟ۖۨۛۦۥ۫ۢۥۘۘۜۙۜۨ۠۟۬ۖ";
                                                                            break;
                                                                        case 1633747427:
                                                                            str29 = "۠۠۠۫ۢ۬ۚۘۨۖۡ۠ۘ۬۟ۘۦۗۨۛۢۚۢۘۘۢۜۥۚ۠ۘۘ۬۠ۜۛ۟ۡۘۖ۬ۦۚۧۛ۠ۧۥۘۡۖۜۘ";
                                                                            continue;
                                                                        case 1709540995:
                                                                            if (!jsonResult.has("replace")) {
                                                                                str30 = "ۧۧۖۡۡ۠ۦۘۨۚۘۜۗۖ۬ۦۗۢ۟ۖۗۦ۟ۛۡ۟ۛ۠۟ۥۘۧۨۜۢۚ۟۬ۨۗۛ۠ۦ۫ۡۤۢۧۖۘۛۧۨۘۗۨۚ";
                                                                                break;
                                                                            } else {
                                                                                str30 = "ۛ۫ۙۛۙۤۙۗۡۡۛۘۧۧ۫ۗۨۚۨ۫ۘۛ۠ۜۘۘۧۨۘۢ۠ۛۚ۫ۡۘۘۧ۠";
                                                                                break;
                                                                            }
                                                                    }
                                                                }
                                                                break;
                                                            case 1716820335:
                                                                String str31 = "ۜۨۚۥۜۢۜۢۖ۫ۥۧۖۤۜۘ۠ۡۧۦۘۜۙۦۗۧۥ۟ۨ۟۬ۘۘ۟ۜۧۥۘ";
                                                                while (true) {
                                                                    switch (str31.hashCode() ^ 1883165555) {
                                                                        case 353052927:
                                                                            str31 = "ۡۢۤۛ۬۫ۘۦۤۛ۟ۗۦۨۘۗ۫ۙ۠ۨۥۘۤۢۜۘۛۤۘۘ۟ۙۡۜ۟ۚ۠ۨۖۘ";
                                                                            continue;
                                                                        case 1465230248:
                                                                            JSONObject jSONObjectOptJSONObject = jsonResult.optJSONObject("replace");
                                                                            String str32 = "ۦۡۡۖۜۘۘۛۥۤۥ۫ۢۙۨۗۙۡۘ۬ۗۥۦۤۤۡ۬ۤۧ۬ۚۨۤ۬ۢۜۘ";
                                                                            while (true) {
                                                                                switch (str32.hashCode() ^ (-1271047288)) {
                                                                                    case -1322806396:
                                                                                        String str33 = "ۨۢۢۜ۟ۗۙۜۡۡ۬ۦۤۨۚۢ۫ۡۚۖ۬۠۟ۙ۫ۘۘۥۛۦۘۘ۠۟ۥۧۦۖۨۙۙۧۨۘۥۧ۠ۦ۠ۡ";
                                                                                        while (true) {
                                                                                            switch (str33.hashCode() ^ 526019321) {
                                                                                                case -1313006884:
                                                                                                    str33 = "ۙ۫ۥۘۙۗۘۨۚ۫ۛ۫ۢۘۧۘ۟ۧ۠ۥ۠۫ۨۖۗۦۥۧۘۖ۫۫ۖۛۜۗۙۦۘ";
                                                                                                    break;
                                                                                                case 545998978:
                                                                                                    str32 = "ۜۥ۠ۤۘ۟ۤۙۚ۠۟ۨۘۖۚۢۜۖۘ۠ۨۦۧۙۡۚۜۙۢۚۦۥۜۤۚ۠ۥۘۦۧۨۦۥۘ";
                                                                                                    continue;
                                                                                                    continue;
                                                                                                case 1196239226:
                                                                                                    if (jSONObjectOptJSONObject == null) {
                                                                                                        str33 = "ۚۢۛۙۙۨۘۡۘۧ۠ۢ۫ۘ۠ۙۥ۫ۦۘۚۖۧۘۛۖۖ۟ۘۦ۠ۢۘۥۥۧۘۤۚۙۘۙۨ۟ۨۦۗۤۘۘۖۘۘۘ";
                                                                                                        break;
                                                                                                    } else {
                                                                                                        str33 = "۠ۥ۟ۢۨۖۡۗۖۘۚۖۘۘۛۖۧۘۥ۫ۛۧۥۥۗۨ۬ۗۢۚۡۦۡۘۤۛۘ۟ۧۖ";
                                                                                                        break;
                                                                                                    }
                                                                                                case 1837054676:
                                                                                                    str32 = "۠ۢ۫ۢ۬ۘۥۛۢۜۗۜۡۚۜۘۡۗۖ۫ۖۤۗۛۙۜۘۧۘ۠ۢۚۦ۫۟ۙۗۥۖۥۗۗۘۗۦۛۤۡۜۜۗۡ۟ۡۧۘ";
                                                                                                    continue;
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case -1220289102:
                                                                                        String str34 = "ۥۤۖۦۜ۟۫۬۠ۖۥۧۘۧۙۢ۟ۖۦ۠ۖۢۡ۫ۥ۠ۤۘۡۚۨۦۜۦۢۗ۫ۤۖۘۢۥۖۘۗۗۜۘۗۖۘۡۜۡۘۙۙۤ";
                                                                                        while (true) {
                                                                                            switch (str34.hashCode() ^ (-814863165)) {
                                                                                                case -1360161241:
                                                                                                    String str35 = "ۢۧۜ۬ۘۢۛۡۥ۫ۦۙۙۘۧ۬۠ۘۦ۬ۨ۠ۛۗۤۗۨۘۢۦۚۚۖۥۢۚۧۤۥۧۤۤۚۢۙۡۘۖۡۤۘۧۗۤۤۦۘ";
                                                                                                    while (true) {
                                                                                                        switch (str35.hashCode() ^ 693454499) {
                                                                                                            case -302980755:
                                                                                                                str34 = "ۙۥۤۘۦۛۤۗۤۨۜۚۦ۬ۡۤۥۢ۬ۚ۠ۚۨ۬ۦۖۘۖۛۥۘۚۦۥۥۗۨۘ";
                                                                                                                continue;
                                                                                                            case -277775859:
                                                                                                                str35 = "۠ۙۚۡۙۧۢ۠ۥۨ۬ۘۦ۫ۘۗۖۘۦۛۛۢۘۘۘۙۨۜ۟ۚۥۘ۫۠ۛ۠ۘۘ۬ۧۚ۟ۢ۬ۢۢۦۥ۠";
                                                                                                                break;
                                                                                                            case -243562165:
                                                                                                                if (!jSONObjectOptJSONObject.has(strSafeResolveTargetClassName)) {
                                                                                                                    str35 = "ۤۜۧۤۜۛۨۜۥۗۥ۠۫۬ۜ۟ۙۤۧۘۦۘۖۡۡ۠ۦۦۘ۫۫ۢ۟ۗۢۦۥۘۘ";
                                                                                                                    break;
                                                                                                                } else {
                                                                                                                    str35 = "ۦ۠ۗۛۡۢۜۢۨۘ۬ۥۨۜۦۜۖۧۖۘ۫ۤ۟۠ۘۥۘ۠ۛۙ۠۠۟ۧۜۙ۫ۗۤۗۦۗۛۡۡ";
                                                                                                                    break;
                                                                                                                }
                                                                                                            case 771514270:
                                                                                                                str34 = "۠ۖۙ۟ۡۤۤۡۖۚۡۢ۠ۧۛ۬۠ۘۜ۠ۖۘ۠ۤ۠ۙۢۨۤۜۛ۟۟ۛۛۥۘۧۧۜۘ۫ۡۧۡۢۢۡۥۛۡۦۡۘ۠ۨۨۘ";
                                                                                                                continue;
                                                                                                                continue;
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case 573577024:
                                                                                                    str34 = "ۢ۫ۖۘۡ۟ۥ۟ۛۤۦۢۛۜۛۦۛۖۤۧۛۥۛۘۧۜۜۥۘۢۗۨ";
                                                                                                    continue;
                                                                                                case 814438577:
                                                                                                    String strOptString = jSONObjectOptJSONObject.optString(strSafeResolveTargetClassName);
                                                                                                    String str36 = "ۖ۬ۡ۬ۙۘۢۢۜۗ۠۠ۖۛۜۘۜۦۜۘۚ۫ۢۘۢ۬ۡۚ۫ۙۢۚۨۗۗۥۨۘۘۢۧ۟ۤ۫ۦ";
                                                                                                    while (true) {
                                                                                                        switch (str36.hashCode() ^ 753832674) {
                                                                                                            case -998487859:
                                                                                                                str36 = "ۜ۠۬۫ۢۦۘۤۧۙ۫ۖۜۛۚۧۙۦۘۨ۫ۤۚۙۚۛۜۖۤۨۡۘۡۛۚۡۘ";
                                                                                                                continue;
                                                                                                            case -485508366:
                                                                                                                String str37 = "ۢۚ۟ۚۖ۬ۜۚ۠ۗ۟ۜ۬۟ۦۘۜ۫ۨ۬ۡۘۘۛۤۡۗ۟ۘ۠ۦۤۖۡۘ۠۫ۥۨۘۢۨ۬ۦۙۚۘۨۗ۠ۜۤۢ۠ۖۘۘ";
                                                                                                                while (true) {
                                                                                                                    switch (str37.hashCode() ^ 334624800) {
                                                                                                                        case -1156952471:
                                                                                                                            String str38 = "ۧۚۚ۠ۢۗۦۤۘۚ۬ۖۘۦ۬۟ۙۜۖۙۜۘۚۧۘۛ۠۟ۖۛۖۙۜۦۜۛۚ۫ۢۛۨۗۚ";
                                                                                                                            while (true) {
                                                                                                                                switch (str38.hashCode() ^ 129427701) {
                                                                                                                                    case -1415223906:
                                                                                                                                        str38 = "ۢۜۨۧۚۧۛۢۜۧۖۘۗۨ۟ۤۦۥۘۛۘۡ۟ۗۧۚۧۜۘۘ۬ۖۘۜۙۚ۟ۤۥۘ";
                                                                                                                                        continue;
                                                                                                                                    case 287180759:
                                                                                                                                        k2.logToFloatingWindow("【跳转劫持】" + strSafeResolveTargetClassName + "\n替换 URI 为：" + strOptString, "warning");
                                                                                                                                        intent.setData(Uri.parse(strOptString));
                                                                                                                                    case -607391984:
                                                                                                                                        Instrumentation.ActivityResult activityResultInvokeExecStartActivityCompat = invokeExecStartActivityCompat(this.val$originalInstrumentation, context2, iBinder, iBinder2, activity, intent, i, bundle);
                                                                                                                                        StringBuilder sb3 = new StringBuilder();
                                                                                                                                        sb3.append("【启动已发起】requestCode=");
                                                                                                                                        sb3.append(i);
                                                                                                                                        sb3.append("，目标=");
                                                                                                                                        str2 = "ۚۨۧۘ۠ۥۖۧۖۜۖۜ۫ۙۦۥۗۖۗۢۥۗ۬ۥۡۘۛ۬ۤۤۧۢۗ۬ۥۦۥۢ";
                                                                                                                                        while (true) {
                                                                                                                                            switch (str2.hashCode() ^ 1701957358) {
                                                                                                                                                case -610554601:
                                                                                                                                                    String str39 = "ۙۡۧۗۙ۠ۤۦۦۘۥۦۖۛۖۦۡۧۘۗۛۡۛۦۤۥۢۥۗ۠ۚ۫ۥۢۗۚۤۨ۠ۢۤۦۖۘۥۡۛۡۛۥۘ۟ۡۘۘۙۢ";
                                                                                                                                                    while (true) {
                                                                                                                                                        switch (str39.hashCode() ^ (-13917815)) {
                                                                                                                                                            case -1658002714:
                                                                                                                                                                str2 = "ۙۥ۠ۥۚۛۥ۬ۨۘۧۦۖۘ۫ۛۤۛۖۚۚ۬ۗۦۥۨۘۢۜۘ۫ۢۘۘۛۚۘۘۚۨۡ۟ۥۡۨۚۛ";
                                                                                                                                                                continue;
                                                                                                                                                                continue;
                                                                                                                                                                continue;
                                                                                                                                                            case -1186387387:
                                                                                                                                                                str39 = "ۤۘ۠۬ۖۧۘۘۗۖۘ۬۬ۨۨۛۤۡۧ۟ۤۜ۬ۛ۬ۥۘۚۧۖۜۢ۟ۚۧۗۤۡۘۤۦۚ۫۫ۖ";
                                                                                                                                                                break;
                                                                                                                                                            case 896269428:
                                                                                                                                                                str2 = "ۦ۠ۡۘۙۙ۬ۙۡۢۦ۬۬ۛۖۜۧۙۗۥۤ۫ۙۦۘۡ۟ۢۙۢۡ";
                                                                                                                                                                continue;
                                                                                                                                                            case 1602607065:
                                                                                                                                                                if (strSafeResolveTargetClassName == null) {
                                                                                                                                                                    str39 = "ۤۥۧۘ۫ۥۡ۫ۧۘۦۙۤۨۖۘ۠ۤۘۙۥۖۖ۫ۜۘۡۚ۠ۤۘۤۘۤۖۘۤۖۤ۟۫ۜۘۛۤۢ";
                                                                                                                                                                    break;
                                                                                                                                                                } else {
                                                                                                                                                                    str39 = "ۥۛۗۦۜۖۘ۠ۘۥۥۚۡۡۜۜۘۜۘۤ۬۬ۜۧۛۧۙۢۢ۠ۗۥۜۤۦۨ۠ۙۡ۫ۖۚۚۨۘۖۢۚۛۚۥ";
                                                                                                                                                                    break;
                                                                                                                                                                }
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                    break;
                                                                                                                                                case -250588848:
                                                                                                                                                    strDecrypt = "<未知>";
                                                                                                                                                    break;
                                                                                                                                                case 1197625718:
                                                                                                                                                    strDecrypt = strSafeResolveTargetClassName;
                                                                                                                                                    break;
                                                                                                                                                case 1985033474:
                                                                                                                                                    str2 = "ۘ۟ۗۨۡۧ۬ۙۥۘۚ۠ۡۘۚۗ۬۬ۢۦۘۘۢۨۧۘۘۘ۟ۤۖۘۙۢۛ";
                                                                                                                                                    continue;
                                                                                                                                                    continue;
                                                                                                                                                default:
                                                                                                                                                    continue;
                                                                                                                                                    continue;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        sb3.append(strDecrypt);
                                                                                                                                        k2.logToFloatingWindow(sb3.toString(), "info");
                                                                                                                                        return activityResultInvokeExecStartActivityCompat;
                                                                                                                                    case 747703443:
                                                                                                                                        String str40 = "ۛ۬۫ۛ۫ۥۘۖ۟۫ۧۧۥۧ۫ۜۘۖۖۘۦۦۗۘۡ۫ۦۦۧۘۛۖۢۚۡۨۘ۫۬ۦۘۧۦۧۘۘۨ۬ۙ۫ۥۥ۠ۘ۟ۤۥۘۢۨۨۘ";
                                                                                                                                        while (true) {
                                                                                                                                            switch (str40.hashCode() ^ 354644328) {
                                                                                                                                                case -1741593253:
                                                                                                                                                    str38 = "ۤ۬۫۠۟ۢۢۢ۬ۙۘ۟۠ۜ۫ۜۜ۫ۢ۫ۛۧۦۛۧۖۨۧۜۛۦۦۘ۠۟ۛ";
                                                                                                                                                    continue;
                                                                                                                                                    continue;
                                                                                                                                                case -1607144685:
                                                                                                                                                    str38 = "ۥ۫ۦۜ۠ۨۨۗ۬۬ۦۜۡۤۜۘۛ۫ۖۦۛۖۘۛۤۢۧ۠ۡۗۧۥۢۗۜۘ۫ۘۧۚۤ۫۠ۜۢۤۧۥۘۙۗۡۚ۠ۖۘۨۗ۬";
                                                                                                                                                    continue;
                                                                                                                                                case -487924753:
                                                                                                                                                    if (intent.getData() == null) {
                                                                                                                                                        str40 = "ۚۢۜۘۥۖۦ۠ۡۨۘۖۛۜۛۖۡۘۡۜۡۘۧ۬ۘۡۖۧ۬ۘۜ۫ۥۘۗۦۥۘۨ۟ۙ۠۠ۦ۟ۡۢ۟ۡۘۨۦۤۗۢ۠ۡ۫ۘۘ";
                                                                                                                                                        break;
                                                                                                                                                    } else {
                                                                                                                                                        str40 = "ۗۙۨۦۙۥۜۢۜۘۡ۟ۖ۬۠ۙۤۢۖۥۢۖۘۥۨۤۢۛۘۘ۬ۗۘۗۜۗ۬ۦۡۘۤ۟ۤۚۤۡۤۜۜۛۦۧۘ";
                                                                                                                                                        break;
                                                                                                                                                    }
                                                                                                                                                case 463707350:
                                                                                                                                                    str40 = "۫ۦۛۙ۬ۚۚۨۨۘۡۘۘۘۛۜۜ۟ۥ۬ۘۡۘۖۗۘۘۖ۬ۘۘۙۨۖۚ۬ۥۨۧ۟ۦ۫ۜۘ۫ۨ";
                                                                                                                                                    break;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        break;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            break;
                                                                                                                        case -1859344286:
                                                                                                                            break;
                                                                                                                        case -879667423:
                                                                                                                            String str41 = "ۜۥۥۢۢۖۘۤ۬ۘ۬ۤۨۥۜۡ۟ۡۗ۠ۨۙۗۚ۫ۢۦۢ۬۟ۨۨۡۦۜۚ";
                                                                                                                            while (true) {
                                                                                                                                switch (str41.hashCode() ^ (-883595782)) {
                                                                                                                                    case -1422225578:
                                                                                                                                        str37 = "ۧ۠ۖۘۘۛ۬ۨۖۨۗۛۡۤۡۡۖۖۦۙ۫ۥۡۙۖ۫ۥۡ۫ۘ۫ۙۙ۬ۢۗ۟ۚۜۖۛۦۢ۠ۥۜ۠ۨ۫";
                                                                                                                                        continue;
                                                                                                                                    case 79964142:
                                                                                                                                        if (!strOptString.isEmpty()) {
                                                                                                                                            str41 = "ۛۖۦۘۢۥ۬ۢۙۚۡۧۥۜۥ۬ۘۤۚۖ۠ۦۘۥۘۨۦ۫۬ۙ۠۫ۚۡۘۤۨۡۘۥۖۘۙ۠ۛۡۖۨۘۘ۠۫";
                                                                                                                                            break;
                                                                                                                                        } else {
                                                                                                                                            str41 = "۠ۙۡ۬ۜۖ۟ۨۜۘۧۜ۫ۙ۬۬ۧۡۦۨۘۖۥۜۥۘ۫ۢ۟ۘ۫۫ۡۘۘۧ۫ۘۢۨۚۚۧۧۘۘۤۡۜۘۧۧۘۚۚ";
                                                                                                                                            break;
                                                                                                                                        }
                                                                                                                                    case 761456066:
                                                                                                                                        str41 = "۬ۥ۟ۙۨۖۘۡۘۢۚۤۘۘ۬ۙۗ۠ۤۖۦ۠ۨۧ۠ۨۘۗ۫ۘۤۛۛۡۤۘۥۜۡۖۤ۬۠ۢۨۘۦۛۢۤۜۘ";
                                                                                                                                        break;
                                                                                                                                    case 2146788775:
                                                                                                                                        str37 = "ۜۧۖ۠۟ۖۤۛۨۘ۠ۨۡۘۨ۫ۨۨ۬ۘۘ۬ۦۧۨۨۘۢۦۛۙۖۜۦ۬ۡۤ۠۟ۘۥۥ۬ۨ";
                                                                                                                                        continue;
                                                                                                                                        continue;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            break;
                                                                                                                        case -121660318:
                                                                                                                            str37 = "ۨۧۡۘۜۚ۬ۛ۟ۘۛۗۘ۫۠ۘۗۛۤۗۤ۬ۡ۬۫۠ۡ۠ۤۗۨۨۙ۟ۧۜۘ";
                                                                                                                            continue;
                                                                                                                        default:
                                                                                                                            continue;
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            case -389574468:
                                                                                                                String str42 = "ۛۤۘۖۦۥۘ۟ۢۖۧ۬ۡۢۤۦۘۡۧۥۘۥۖۦ۠ۘۡۘۚۧۡۡۧ۬ۡۜۘۖۗۦ۫۫۬ۚۙۡ۬ۢۢۢ۟ۨ";
                                                                                                                while (true) {
                                                                                                                    switch (str42.hashCode() ^ 901623989) {
                                                                                                                        case -933036706:
                                                                                                                            if (strOptString == null) {
                                                                                                                                str42 = "ۥۦۦۘۧۨۚۥۥۤۛۨۨۘ۠ۖۡۖۤۚۘۡۘ۫۫ۘۘۦ۬۠ۨۚۨ۬ۥۧۘ۫ۡۘۘۚۙ۟ۜۥۗۜۜۥۘۢۧۥۘ";
                                                                                                                                break;
                                                                                                                            } else {
                                                                                                                                str42 = "ۖ۟ۦۗۢۡۦۤ۟ۙۘۖۜۖۗۥۖۧۢۚۙۜۚۛۗ۠ۧۜ۫ۖۘۙۖۛۖۛ۟ۧۚۡۘۖۘ۫ۢۗۧ۬ۗ";
                                                                                                                                break;
                                                                                                                            }
                                                                                                                        case -364762403:
                                                                                                                            str36 = "ۛۦۗۛ۟ۖۘۢۦۘۨ۟ۧۤۜۢۘۡۘۘۘۧۛۡۤۥ۠ۥۡۥۜۨۖۛۧۛۙۧ۫ۥۤۤ۬ۦۖۘۥۘۙ۬۫ۡۘۥۛۙۨ";
                                                                                                                            continue;
                                                                                                                        case 251763888:
                                                                                                                            str42 = "ۗ۠ۖ۫ۥۤۛۚۡۘۘۤۘ۬ۘۛۚ۠ۢۢۧۡۘۜۦ۬ۛۙۛ۠ۨ";
                                                                                                                            break;
                                                                                                                        case 1915412752:
                                                                                                                            str36 = "۫۫ۜۡۤۗۦ۬۫ۧۨۛۘۘۘ۬ۖۘۨۢ۬ۢۥۧۘۖۨۥۘۗ۫۫";
                                                                                                                            continue;
                                                                                                                            continue;
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            case 1546428598:
                                                                                                                break;
                                                                                                            default:
                                                                                                                continue;
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case 1934808532:
                                                                                                    break;
                                                                                                default:
                                                                                                    continue;
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case 1181515416:
                                                                                        str32 = "۬ۦۡۙ۫ۜۛۥۖۨۦۘۚۙۡۘۚ۟ۥۦۘ۬ۧ۠ۥۙۗۘۘۗ۬۫ۚۖۘ۠ۛۨۘ";
                                                                                        continue;
                                                                                    case 1265670432:
                                                                                        break;
                                                                                    default:
                                                                                        continue;
                                                                                }
                                                                            }
                                                                            break;
                                                                        case 1306896547:
                                                                            break;
                                                                        case 2025471156:
                                                                            String str43 = "ۚۤۥۜۥۧۘۙۨ۠ۚۦۜۘۦۖۤ۫۟ۦۘۤۙ۬ۚ۟ۚ۟ۦۖۘۛۧۗۚ۫ۦۘۢۧۚۘ۠ۜۘۨۦ۟ۙۡۘۡۨۘ";
                                                                            while (true) {
                                                                                switch (str43.hashCode() ^ (-565472760)) {
                                                                                    case -1287109741:
                                                                                        str43 = "ۧۛۘۘ۟ۚۘۘ۬ۥۘۗ۟۬۟ۚ۟۠ۜۢۘۘۜۧۗۦۤۨۧۙۖ۬ۥۜ۟ۙ";
                                                                                        break;
                                                                                    case -1229092334:
                                                                                        if (strSafeResolveTargetClassName == null) {
                                                                                            str43 = "۬ۦۤۛ۠۬ۗۙۛۜۖ۫ۢۡۡۘۦۖۘۦۘۜۘۘۡۡۖۤۨۘۢ۫ۦۘۙۦۡ۫۟ۡۢۧۖۡۧۨۘۥۢۖ۠ۜۥ";
                                                                                            break;
                                                                                        } else {
                                                                                            str43 = "۫ۤۡۢ۠ۧۦۦۦۨۦۨۘۤۙۢۜۙۖۘۢ۟ۦۡۖۨۛۤ۫ۙۢۧ۬ۨۨۘۤۛۤۗۦۥۘۤۡۦۜۡۥۘۢۧۢ۬۬۠ۡۧۡ";
                                                                                            break;
                                                                                        }
                                                                                    case 1763117968:
                                                                                        str31 = "۟ۤۡۘۦ۠ۥۘۦۛۖۧ۬ۤۖ۬ۢ۟۟ۧۤ۠ۥۘۛ۠ۙۤۧ۟ۙۨۦۘۛۖۖۧۤۜۖۡۚ۫ۜۛۜۨۥۦۘۖۗۘۘۙۡ۫";
                                                                                        continue;
                                                                                    case 2000362213:
                                                                                        str31 = "ۚۡ۠ۤ۬ۗۘۚۡۘۢۜ۬ۡۜۘۘۡ۫ۖۘ۟ۗ۟ۤۛۘۘۤۜۨۘۥۤۡۡۖۖۨ۬ۛۡۨۘۖ۫ۖ";
                                                                                        continue;
                                                                                        continue;
                                                                                }
                                                                            }
                                                                            break;
                                                                        default:
                                                                            continue;
                                                                    }
                                                                }
                                                                break;
                                                            case 432154085:
                                                                break;
                                                            default:
                                                                continue;
                                                        }
                                                    }
                                                    break;
                                                case -1300451935:
                                                    break;
                                                case -829797389:
                                                    String str44 = "ۗۛۥۡ۫ۖۦۜۥۘۖۧۦۤۥ۠ۙۨۧۚ۟ۦۘۡۛ۫ۡۤۗۖ۬ۨۜۧ۠ۛۨۘۜۨۖۘۦۤۖۘۙۜۢۜۧۖۘۛ۬ۘۖۛۜ";
                                                    while (true) {
                                                        switch (str44.hashCode() ^ (-447310422)) {
                                                            case -1563104144:
                                                                if (jsonResult == null) {
                                                                    str44 = "ۛۗۜۘۗ۠ۗۖ۠ۦۙۙۗۙۧ۫۫ۧ۬۟۫ۚۜۦۗۢۜۡۧۘۜۧۢۢۘۘۙۖۥۘۦۛ۠ۧۨۡۧۖ۟";
                                                                    break;
                                                                } else {
                                                                    str44 = "ۡۢ۫ۚۚۡۗۥۥۘۗۤۘۥۖۦۙۚۦ۬ۘۘۘ۫ۘۦۘۛ۠ۥۥۨۘۛۥۦۘ۫ۦۦۘۛۛۢۥۨۖۘۚۦۦۜۧۥ";
                                                                    break;
                                                                }
                                                            case -793502847:
                                                                str28 = "ۨۨۨۜۧۡۛۚۨۘ۬۫ۜۘۛۘۜ۬ۡۛۢۜۗۘۡ۟ۛۦۦۤ۠ۜۗۜۤۛ۬ۗ۠۬ۥۘۘۖۖۘ";
                                                                continue;
                                                            case -629363023:
                                                                str44 = "ۧ۫ۧۚ۬۬ۨۛۥۘۛۦۦۡۥۜۘۙۥۛۗۖۤ۠ۨۨۤۤۜۧۚۘۜۗۢۘۨۧۘ";
                                                                break;
                                                            case 816074681:
                                                                str28 = "ۢۖۚۡۘ۟ۧ۠ۦۗۘ۬ۧۢۡۤۥۡۘ۫ۖ۟ۘۧۖۗۨۡۘ۬ۢۦۨ۟۠ۚۖۘۘۦۧۥۘۧۘۖ۬ۗۙۙۦۘۛۦۜۧۗۥ";
                                                                continue;
                                                                continue;
                                                        }
                                                    }
                                                    break;
                                                case 301098566:
                                                    str28 = "ۜۨۢۛۚۥۘ۫ۘۖۘۢۙۡۘۥۗۛۤ۫ۡۡۖۘۦۙۗۤ۫ۖۗۚۗۘ۫ۗۖۥ۬ۦۦۤۥۡۧۘۖۘۥۥۜۧۚۚۡۚ۬";
                                                    continue;
                                                default:
                                                    continue;
                                            }
                                            k2.logToFloatingWindow("execStartActivity 外层异常：" + th2.getMessage(), "error");
                                            throw new RuntimeException("execStartActivity 异常", th2);
                                        }
                                }
                            }
                            Instrumentation.ActivityResult activityResultInvokeExecStartActivityCompat2 = invokeExecStartActivityCompat(this.val$originalInstrumentation, context2, iBinder, iBinder2, activity, intent, i, bundle);
                            StringBuilder sb32 = new StringBuilder();
                            sb32.append("【启动已发起】requestCode=");
                            sb32.append(i);
                            sb32.append("，目标=");
                            str2 = "ۚۨۧۘ۠ۥۖۧۖۜۖۜ۫ۙۦۥۗۖۗۢۥۗ۬ۥۡۘۛ۬ۤۤۧۢۗ۬ۥۦۥۢ";
                            while (true) {
                                switch (str2.hashCode() ^ 1701957358) {
                                    case -610554601:
                                        break;
                                    case -250588848:
                                        break;
                                    case 1197625718:
                                        break;
                                    case 1985033474:
                                        break;
                                }
                            }
                            sb32.append(strDecrypt);
                            k2.logToFloatingWindow(sb32.toString(), "info");
                            return activityResultInvokeExecStartActivityCompat2;
                        } catch (Exception e) {
                            k2.logToFloatingWindow("调用 execStartActivity 失败：" + e.getClass().getSimpleName() + " / " + e.getMessage(), "error");
                            throw new RuntimeException("调用 execStartActivity 失败", e);
                        }
                    }

                    /* JADX WARN: Code restructure failed: missing block: B:334:0x0223, code lost:
                    
                        continue;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:335:0x0223, code lost:
                    
                        continue;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:336:0x0223, code lost:
                    
                        continue;
                     */
                    /* JADX WARN: Code restructure failed: missing block: B:60:0x012c, code lost:
                    
                        r1 = r3;
                     */
                    @Override // android.app.Instrumentation
                    /*
                        Code decompiled incorrectly, please refer to instructions dump.
                    */
                    public Activity newActivity(ClassLoader classLoader, String str, Intent intent) throws IllegalAccessException, InstantiationException, ClassNotFoundException {
                        String str2;
                        String str3;
                        RuntimeException runtimeException;
                        int i = 0;
                        String str4 = "ۦ۟ۚۚۜۤۥۡۢۡۦۥۢۙ۫ۡۧۦ۬۟ۥۗۖۘۜۖۖۘۘۜۘۘۘ۬۬ۡۘ۫ۥۘۘ۬۫۫ۢ۠ۗۢۨ۠ۥۘۘ۟ۛۦ";
                        while (true) {
                            switch (str4.hashCode() ^ (-155124759)) {
                                case -1033036988:
                                    str4 = "ۡ۬۬ۘۙۦۜ۬۠ۡۥۨۡ۬ۨۦ۬۟ۥ۬ۙۖ۫ۦ۟ۖۡۘۘۨۨۛۢۢۗۗۥۤۧۡۘ۬ۚۖ";
                                case -243304476:
                                    String str5 = "ۦ۠۟ۖ۫ۖۘۖۨۗۙۘۘۗۢۦۘۛ۬ۚۧ۠ۖۦۚۜۘۙۛۦۘۢۨۧۘۙۤۧۦۨ۠ۢۦۙۤۜۨۘ";
                                    while (true) {
                                        try {
                                            switch (str5.hashCode() ^ (-1210778374)) {
                                                case -1463785869:
                                                    break;
                                                case -657896521:
                                                    str5 = "ۘۛۜۘۥۡۦۢ۬ۖۘۚۡۘۘۖۙۘۘ۫ۜۤۥۙۗۧ۠ۥۚۙۧ۫ۦۥ۬ۗ۠ۦۜۜۜۗۜۘ۠ۗۥۘ۫ۥۡۢ۟ۥۘۜۧۘ۠ۘۚ";
                                                    continue;
                                                case 881580453:
                                                    String str6 = "ۛۘۡۙۦۢۧۛۜۘۤۙۜۙۚ۟ۦۦۘۙ۫۟ۢۢۖۛۧۦۗۜۨۜۤۤۚۥۧۘۢۙۨ۬ۖۛۦۡۙۛۛۡۚۚۤ۬ۖۖۘ";
                                                    while (true) {
                                                        switch (str6.hashCode() ^ (-2132674229)) {
                                                            case -1322133485:
                                                                str6 = "ۧۢۡۘۨۡۛۢۥۧ۟ۖۦۧۙ۫ۦۘۛۤۡ۠ۖ۬۬ۥۜۢۦۘۤۘۨۖۨ۟ۡۘۙۧۦۘ";
                                                                break;
                                                            case -352191397:
                                                                str5 = "ۤۥۢۘۚۦۘۨۘ۠ۗۚ۟ۛۗ۫ۜۦۡۘۦۙۖۘ۫ۘۦۘۨ۠ۜۘۖۖۦۘ";
                                                                continue;
                                                            case 1959110988:
                                                                if (!intent.hasExtra("android.app.activity_class")) {
                                                                    str6 = "۟۬ۡ۟ۡۦۘۛۚۨۨ۟ۛۜۜۦۘۡۥۢ۬ۜ۫ۦۘ۠ۙ۬ۘۘۛۙۥۥۤۤۢۗۙۦۧۨۘۢۤۥ۟۫۬۠ۛ۟ۚۤۨۛۙۥۘ";
                                                                    break;
                                                                } else {
                                                                    str6 = "ۡۦۥۘ۠ۡۨۤۘۘۖۖۜ۫ۖۛۧۗۥۘۥۧۥۘۚۦۜۘ۬ۜۤۤۡۨۘۗۡۜۘۖۙۜ۟۠ۘۘۡۜۦۘ۬ۜ۠ۢۥۚۦ۫ۙۤ۠ۘ";
                                                                    break;
                                                                }
                                                            case 1960431812:
                                                                str5 = "ۦۜۛۛۡ۟ۘۥۜۘۦ۟ۜ۬ۗۘۨۗۥ۟۠ۘۨۧۘۢۥۘۚۚۗۗۘۖۘۥۡ۟";
                                                                continue;
                                                                continue;
                                                        }
                                                    }
                                                    break;
                                                case 2015934247:
                                                    String stringExtra = intent.getStringExtra("android.app.activity_class");
                                                    intent.removeExtra("android.app.activity_class");
                                                    str2 = stringExtra;
                                                    break;
                                                default:
                                                    continue;
                                            }
                                        } catch (Throwable th) {
                                            k2.logToFloatingWindow("newActivity 外层异常：" + th.getMessage(), "error");
                                            throw new RuntimeException("newActivity 创建失败", th);
                                        }
                                    }
                                    break;
                                case 1751474622:
                                    String str7 = "۫ۦۘۘ۫ۛۡۜ۟ۗۖۨۡۘۗۤۥۘۚۚۜۜ۬ۜ۫ۤۦۘۡۜ۬ۚ۠ۨۘۥۦۡۘ۫۫ۨۧۜۖ۟ۚۨۤۛۤۢۦ";
                                    while (true) {
                                        switch (str7.hashCode() ^ (-1846698298)) {
                                            case -1698144943:
                                                str7 = "ۡۚۨۘ۫ۛۡۤۘ۫ۜۗۡۘۧۘۧۘۤ۫ۨۘۛ۬ۘۘۘۨۖۘۧۥۜۘۗۢۘۖۥۤۛۚۧۨۢ۠ۚۥ۠ۜۘۥۘ۬ۙۨ";
                                                break;
                                            case -298274308:
                                                if (intent == null) {
                                                    str7 = "ۙۦ۠۫ۧۖ۠ۦۙۤۥ۬ۦۢۜۡ۫ۡ۠ۥۘۡۚ۟ۙۧۗۘۥۦۘۗ۠ۥۜۧۙۚۡۧۘۧ۠۠ۧۤۦۘۦۤۗۖۦۖۡۤۚ";
                                                    break;
                                                } else {
                                                    str7 = "ۧۘۙۖۦۥۘۤۚۡۘۨۜۡۘۡۤ۠ۚۜۤۖۛۘۥۡۢ۟۬ۖۖۜۗۥۢ۫۫۫ۚۗۧۨۘ۫ۧۢ";
                                                    break;
                                                }
                                            case -122009486:
                                                str4 = "ۗۙۥۧۧۦۛ۠ۤۜۙۙۛۢ۠ۥۢۗۚۧۨۨۜۘۘ۠ۜۧۛ۬ۚۨۤۘۨۘۥۘ۫ۡ۬ۚۛۜۘۤۥۥۥۜۦۘ";
                                                continue;
                                            case 797376923:
                                                str4 = "ۨۦۙۧۚ۟ۢ۟ۚۜۖ۫ۡ۠۠ۗۢۖۖۛۘۘۙۜ۟ۛۗۘ۟ۤۗۘۛۦۘ۬ۜۙ";
                                                continue;
                                        }
                                    }
                                    break;
                                case 2141369951:
                                    break;
                            }
                        }
                        str2 = str;
                        int i2 = this.val$context.getApplicationInfo().targetSdkVersion;
                        String str8 = "ۙۨۦۜۜۖۘۚۨۥۘ۫ۡۧۘ۫ۥۥۘۢۚۘۘۘۘۛۡۖۘۙۖۙۦۤ۫ۘۜۜۘ۠ۨۗۨۤۨۡۧۙ";
                        while (true) {
                            switch (str8.hashCode() ^ 1144300014) {
                                case -1836635150:
                                    k2.logToFloatingWindow("targetSdk >= 28，跳过Activity替换逻辑 → " + str2, "info");
                                    Method declaredMethod2 = Instrumentation.class.getDeclaredMethod("newActivity", ClassLoader.class, String.class, Intent.class);
                                    declaredMethod2.setAccessible(true);
                                    return (Activity) declaredMethod2.invoke(this.val$originalInstrumentation, classLoader, str2, intent);
                                case -545353969:
                                    str8 = "ۚۡ۬ۖ۬ۘۘۘ۫ۢۜۖۖۨۘۤۙۥۨۘ۬ۤۛۦۢۡۤۘۜۘۡ۠۟ۙ۟ۡۘ۟ۛۥۘۢۖۢۥۚۙۜۥۨۤۗۦۘ";
                                    continue;
                                case -264671844:
                                    JSONObject jsonResult = s0.getJsonResult();
                                    String str9 = "ۙ۬ۡۗۨۘۧۗۢۘ۟ۨۘۙۘ۟ۤۜۨۦۤ۫ۢۙۙۖۙۡۘۛۢۨ۬ۖۥۘۨۥۤ۟ۖۛ۟ۖۡ";
                                    while (true) {
                                        switch (str9.hashCode() ^ (-292783648)) {
                                            case -794883042:
                                                String str10 = "ۛۛۜۘۛۙۨۘۦۙۨۖۧ۫ۜۖۘۛۛۗ۟ۗۖۘ۬۟ۜ۬ۡ۫ۛۚۖۘۦ۠ۛۙ۟ۦۘۛۤۢ۬ۚۗۖۥۦۘۤۡۜۘۚۧ۠ۘۨۘ";
                                                while (true) {
                                                    switch (str10.hashCode() ^ (-1509218065)) {
                                                        case -1537013689:
                                                            if (jsonResult == null) {
                                                                str10 = "ۚۙۖۢۥۛۖۥۦۖۜۗۚۡۚۨۘ۬ۨۚۗۖۛۖۘۨۦۨۛۙۡۘ";
                                                                break;
                                                            } else {
                                                                str10 = "ۢۙۦۛۜۗۤۚۙۚۦۚۨۙۖۜۜۜۘۜۗۥۘۡ۫ۘۦ۬۬ۥ۟۟۠ۦۛۙ۠ۧ";
                                                                break;
                                                            }
                                                        case -1385547308:
                                                            str10 = "۫ۙ۟ۡ۫۫ۖۘۜۘۙۦۙ۠ۗۖۘۛۖۨۘۚۙۨۖۤۤۤۧۧ۠ۧ۫۫ۗۖۖۛۛ";
                                                            break;
                                                        case -961449036:
                                                            str9 = "ۢۖۜۘۤ۬ۘۨۚۡۛۖۦۜۨۗۘ۫ۗ۟ۢۧۚ۫ۨۘۥ۬۠ۗ۠۠ۧۢۛۧ۟ۖ";
                                                            continue;
                                                        case 61494168:
                                                            str9 = "ۦۖۥ۫ۧۖۘ۬ۜۘۢ۟ۡ۫ۨۙۙۖۨۘۨۜۡۨۨ۠۫ۜۘۢۡۖ";
                                                            continue;
                                                    }
                                                }
                                                break;
                                            case -452144074:
                                                String str11 = "ۖۢ۠ۦۤۙ۫ۡ۬ۢۘۤۥۨ۟ۧۡۘۢۚۨۧ۫ۤۙ۟ۦۘۢۤ";
                                                while (true) {
                                                    switch (str11.hashCode() ^ (-1348853481)) {
                                                        case -1708604740:
                                                            String str12 = "ۥۧ۠ۗ۬ۖۘۜۦۖۘۢۗۜۘۨۘۢ۬ۤ۫۫۬ۧۛۧۡۘۦۡۧۚۘۧۢۨۘۦۡۘۘۖ۠ۨۘۡۦ۟";
                                                            while (true) {
                                                                switch (str12.hashCode() ^ 908202256) {
                                                                    case -1493087877:
                                                                        str11 = "۠ۤۢۚ۟ۨۗ۟ۙ۟ۜۢۚ۫۫ۜ۫ۡۚۛ۫۫ۡۜۨۚۦۘۛۙۛ۫ۗۘۨ۟ۧۛۡۘۗ۠۬۠۠۬ۡۜۧۧ۠ۧ۟ۜ۠";
                                                                        continue;
                                                                    case -1028272349:
                                                                        str12 = "ۖۥۥۘۖۦۛۘۖۘۘۥۗۖۘۥ۫ۨۧۚۡۘۘۛ۬۟ۗۨۘۛ۬ۨ۫۫ۚۢۦۘۤۦ۠";
                                                                        break;
                                                                    case -8851850:
                                                                        if (!jsonResult.has("newactivity")) {
                                                                            str12 = "ۗۨ۠ۖۘۧۘۜۡۗ۫۫ۦۜۚۜۘۢۡۡۘ۬ۨۖۘ۬ۖۥۘۛۚۜۥۖۘ";
                                                                            break;
                                                                        } else {
                                                                            str12 = "ۥۖۘ۫ۗۖۧ۟ۡۘۨۙۛۡۚۖۤ۟ۖۘۨۖۘۨۢۡۦۧۘ۫ۧۗۢۢۖۘۚ۫ۨۘۧۚۡۘ۠ۧۛۚۖۚۚۗۜۘ";
                                                                            break;
                                                                        }
                                                                    case 1136616573:
                                                                        str11 = "ۙۢ۟ۡۤۢۗۗۢۦۖۛۢ۠ۨۙ۟ۥۘۙۡۙۜۥۘۘۢۖۜۘۚۦۙۧۡۥۨۤۨۖۨۘۧۥ۠ۜۗۦۘۛۤۛ";
                                                                        continue;
                                                                }
                                                            }
                                                            break;
                                                        case -1188261629:
                                                            str11 = "۫ۢۜۘۛۚۧۛ۠۫ۥ۠ۨۘۨۙۥۘۘۛۨۘۡ۠ۖۘۜۤۖۘۤۖ۬۫ۛۧۨ۬ۡۘۘۦۨۘۨۖۤ۠ۢۜ۫ۜۥۙۡۡ";
                                                        case 314835850:
                                                            JSONArray jSONArrayOptJSONArray = jsonResult.optJSONArray("newactivity");
                                                            String str13 = "ۖۛۡۘۜۖۨۦۖۚۜۚۨۘۢۘۙۢۜۡۧۨۦ۫ۨۢ۫ۦۙۗۤۨۘۙۥ۠ۘۥۧ۫ۡۦۧۚ۠ۡ۬ۡۧۙ۬ۥ۬ۨۤ۫ۘۘ";
                                                            while (true) {
                                                                switch (str13.hashCode() ^ 652907347) {
                                                                    case -247346365:
                                                                        break;
                                                                    case -29120399:
                                                                        while (true) {
                                                                            String str14 = "ۙۥۜۘۛ۫ۛۘۢۢۛۥۙۚۜۥۛۦۘۦۖۥۘۧۢۖۡۖ۟۫۠ۖ۫۫ۜۚۨ";
                                                                            while (true) {
                                                                                switch (str14.hashCode() ^ (-1012705764)) {
                                                                                    case -1991574525:
                                                                                        String str15 = "ۘ۟ۗۨۤۦ۠ۥۡۘ۠ۡۛ۟۫ۗۙۘ۠ۙۨۧ۫ۙۧۖۢۦۗ۠ۘۨۨۖۙۨۖۘۘۥۢۨۖۙۙۖ۬۠۫ۡۘ";
                                                                                        while (true) {
                                                                                            switch (str15.hashCode() ^ 482847698) {
                                                                                                case -1152707943:
                                                                                                    str14 = "ۤۡۥۘ۟ۡۜۤۙ۠ۤۢۚ۫۫ۚۗۧۗۦۚۚۤۨۦۦۘۜۜۜۘۘۚ۫ۡۘۖۧۦۡۥۨۦۤۙ";
                                                                                                    break;
                                                                                                case -1121188953:
                                                                                                    str15 = "۫ۤۥۘ۠ۤۙۦۛۧۦۧ۟ۧۤۦۥۜۙۡۧۡ۠۬ۛۨۤۢۡۜۥۘ";
                                                                                                case -976140330:
                                                                                                    str15 = i < jSONArrayOptJSONArray.length() ? "۬ۤۘۗۖۖۘۡ۬ۜۗ۫ۡۘۛۤ۟۟ۚ۠ۦۧۨۗۨۥۘ۬ۥۜۘۖۡۛۗۤۘۖ۫ۛ" : "ۦۦۘۢۗۢۚ۠ۖۦۚ۟ۨۧۖۘۧۛۦۤ۫ۖۘۖۗۥۘۜ۬ۙ";
                                                                                                case 42571552:
                                                                                                    str14 = "ۡۗ۫ۚۤۥۘۚۚۖۧۨۙۢۥۘۖ۟ۢۤ۟۫۠ۨۘۚۥۖ۟۬ۖۤۡ۟ۗ۬ۦۘ";
                                                                                                    break;
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case -1761111295:
                                                                                        JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
                                                                                        String str16 = "ۖۜۡۘۨ۫ۥۖۛۗ۠ۦۤۜۗۦۢۜۨۘۗ۠ۦۘۢۡۥ۬ۤ۟ۡۡۘۤۚۥۘۗ۫ۡۘۦ۬ۦۘۗۘۥۘ";
                                                                                        while (true) {
                                                                                            switch (str16.hashCode() ^ 105277550) {
                                                                                                case -1973116426:
                                                                                                    String str17 = "ۘۜۜۘۖ۬ۨۘۛۗ۫۫ۡۥۘ۫ۙۖۘۗ۫ۤۤ۫ۜۘۘۗۦۢۘۘۨ۠ۚۡۥ۫۠ۤۦ۟۬ۖۘ۫ۤ۬ۙ۠ۧۛ۠ۘۛۨ۬۠ۖ۠";
                                                                                                    while (true) {
                                                                                                        switch (str17.hashCode() ^ 1622939512) {
                                                                                                            case -1815207217:
                                                                                                                str16 = "۬ۨۘۘۧۥۥۘ۬ۤۜ۠ۥۖۘۚۚۖۛ۬ۘۘۚۙۢۗۧۦۘۨۦۙۥۙۙۚۦۦۘۙۜۦۘۗۘ۠ۚۥ۫";
                                                                                                                break;
                                                                                                            case -1645836325:
                                                                                                                str17 = "ۚۦۜۗۥۘۙۛۥۘۜۡ۠ۙۦۡ۠۟ۖۦ۬ۥۤ۠ۚۦ۠ۧۧۥۘۗ۠ۥۘۢۨۡۘ۟ۜۚ۟ۜۨ۠ۨۜۗۗۘۗۡۥۘ";
                                                                                                            case -1367914853:
                                                                                                                str16 = "۬ۙ۠ۡۙۗ۟ۤۜۙۥۗۨ۟ۜۘۜۖۜۥۘۧۡ۬ۚۛۡۦۨۡۘۨ۫ۘۘ۠ۚۥۘ";
                                                                                                                break;
                                                                                                            case -393342839:
                                                                                                                str17 = jSONObjectOptJSONObject != null ? "ۥۤۦۛۡۡۦۙۡۘۜۘۨۘۤۥۘۖۗۚۥۗۨۘۥۨۘۧۢۗۢۜۛۥ۟ۧۚ۠ۘۧۡۡۘۢ۬۬۠۠ۥۧۘۙ" : "ۖۚۗۡ۫ۗۙۛۤۘۖۧۤۥۡۚۦۘۢ۫۟ۢۘۘۛۚۥۤۥۢۥۡۨۦۖۧۘۨۧۧۧۖۦۚۘۜۘۘۗۧ۬ۖ۠۫۠ۦۘ";
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case 573136855:
                                                                                                    str16 = "ۙۨۖۘ۠ۤۛۜۤۨۤۛۤ۠ۧۦۘۙۗ۬ۖۘۧۘ۬ۨۨۘۤۡۨۘۚۘۥۘۛۙۛ۬ۙۘۛۗۥۘۙۡۖۛ۫ۦۘۨۜۨۘ۫ۧ۠ۦۜۡۘ";
                                                                                                case 738803959:
                                                                                                    String strOptString = jSONObjectOptJSONObject.optString("activity");
                                                                                                    String strOptString2 = jSONObjectOptJSONObject.optString("newactivity");
                                                                                                    String str18 = "۟ۜۘۛۥۗۖۖۜۘۖۥۗ۫ۨۢۧ۠۫ۜۛۡۤۦۦۘۤ۫ۙۥۙۗۘۛۗ۠ۡ۠۫ۜ۟۠ۜۚ۬ۖ۬ۡ۫۟۫۫ۤۜۛ";
                                                                                                    while (true) {
                                                                                                        switch (str18.hashCode() ^ (-1490958517)) {
                                                                                                            case -13562046:
                                                                                                                String str19 = "ۡۗۥۘۛۘۥۘۤ۫ۗۨ۬ۙۚ۬ۦۛۙۦۘۤۨۘۤۘۘ۬ۥۢۘۖۨۨۚۦۚۡ۫ۥ۟ۢۡۘ۠ۢۘۘۖۖ۬";
                                                                                                                while (true) {
                                                                                                                    switch (str19.hashCode() ^ 728763573) {
                                                                                                                        case -1402491727:
                                                                                                                            break;
                                                                                                                        case -199775929:
                                                                                                                            String str20 = "ۘۛۡۧۨۢۡۚۦۘۚۡۨۘۢۤۦۘۢۛۚۚۡۘۘۦ۫ۨۘ۠ۖۖۛ۬ۢۨۢۛۘۧۛ";
                                                                                                                            while (true) {
                                                                                                                                switch (str20.hashCode() ^ (-395069777)) {
                                                                                                                                    case -1129344379:
                                                                                                                                        str19 = "ۥۧۦۙ۠ۨ۟۟ۦۨۧ۬ۢۘۗۜۘۛۨ۟ۤۖۡۡۥۗۗۡۡۘۤۢۘۘ۫۫ۘۡۡۘۘۘۙۡ";
                                                                                                                                        break;
                                                                                                                                    case -661634650:
                                                                                                                                        str20 = "ۖۨۡۘۚۧۚۥۤۛۚۙۗۥ۠ۥۦۛ۠۬ۙۚ۬ۜۙ۠ۧۦۘۖۥۡۡ۫ۙۛۡۘۤۙۘ۫ۜۤ۠ۢۗۧۛۘۘۗ۬ۗ۠ۡۖ";
                                                                                                                                    case -70691441:
                                                                                                                                        str20 = strOptString2 != null ? "ۥۦۚۨۖۘۜ۠ۨ۫۟ۖ۫ۧۘۚۥۥۨۡۘۘۘۖۤ۠ۦۘۤۙۡۜ۠۟ۦۖۚ" : "۫ۧۜۘۤۥۥۘۧ۟ۢۗ۬ۥ۠۟ۡۘۢ۟ۚۦۤۚۛۛۜۜۦۢۙ۟ۖ۫۟ۗۘۥۜۤۦۘۨۙۘ";
                                                                                                                                    case 621405122:
                                                                                                                                        str19 = "ۚۖۥۘۗۘۨۖۥۥۘ۟ۜ۟ۗۡۖۘۜۨۧۗۚ۟ۢۖۦۢ۟ۤۜۦۜۘۖ۟ۧۡۥۗۤۜۚ۠ۢۥۥ۫ۨۚۚۥ";
                                                                                                                                        break;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            break;
                                                                                                                        case 414810775:
                                                                                                                            boolean zEquals = strOptString.equals(str2);
                                                                                                                            String str21 = "ۤۖۥۘۜ۠۟ۤۨ۬۬ۤ۫ۨۘۥۛۦ۫ۜۜۘۨۦۗۥۖۥۘ۫ۜ۟۫ۡۥۘ۟ۚۘۘۘۜۡۡۖۡۘ۠ۡ۫ۦۤ۟";
                                                                                                                            while (true) {
                                                                                                                                switch (str21.hashCode() ^ (-1652166256)) {
                                                                                                                                    case -2002913173:
                                                                                                                                        try {
                                                                                                                                            classLoader.loadClass(strOptString2);
                                                                                                                                            k2.logToFloatingWindow("Activity 替换：" + strOptString + " → " + strOptString2, "warning");
                                                                                                                                            str3 = strOptString2;
                                                                                                                                            break;
                                                                                                                                        } catch (ClassNotFoundException e) {
                                                                                                                                            k2.logToFloatingWindow("替换失败：目标类不存在 → " + strOptString2, "error");
                                                                                                                                            break;
                                                                                                                                        }
                                                                                                                                    case -1572184433:
                                                                                                                                        String str22 = "ۘۨۨ۫ۧۧۚۙۜۘۥۥۘۥۛ۟ۧ۫ۚ۫ۖۘۘ۠ۗۨۨۦۨۘ۠ۜۡۘ۠ۦۜۘۢ۬۬";
                                                                                                                                        while (true) {
                                                                                                                                            switch (str22.hashCode() ^ 425593426) {
                                                                                                                                                case -1708244347:
                                                                                                                                                    str21 = "ۧۗ۠ۨۨۧۘۤۢۘۢۦۘۙۡۢ۬۬ۨۦۘ۫۠ۥۚۢۥۥۡۘۙۚۨۖۘ۟ۤۜۖۧۤۘۚ۟۠ۦ۬ۥ۟ۡۡۗۘۘ۠ۚ";
                                                                                                                                                    break;
                                                                                                                                                case -1222474782:
                                                                                                                                                    str22 = zEquals ? "۟ۨۚۤۙۨ۫۠ۨۦ۫ۗ۟ۡۧۘۦۚۜۘ۟ۨۨۦۡۖۧۗۘۥۚۜۖۨۤۤۖۨۘ" : "ۘۜ۟ۥ۠ۥۘۙ۫ۖۤۨۦۖۡۘۛۡ۫ۖ۠ۜ۬ۜۘۡ۟ۡۛۤ۬ۛۖۘ۫ۚۗۦۡۘۘۥۢ۬";
                                                                                                                                                case -1004361594:
                                                                                                                                                    str21 = "ۙۧۡۦۥۥۥۖۦۘۛۚۡۘ۫ۜۥۘ۟ۨ۠۬ۦ۫ۙ۫ۘۘۨۧۢۛ۫۬ۛۡۘۥۚۜۚ۠ۤۖۜ۬ۨۦۖۘۜۧ۠";
                                                                                                                                                    break;
                                                                                                                                                case 1277358407:
                                                                                                                                                    str22 = "ۛۤۨ۫ۧۜۘۚۧۜۦۗۖۚ۠ۘۘۦۘۛۢۖۜۨ۠ۡۘۙ۠ۘۘۖۦۢۥۘۜۘۖۨۢ";
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        break;
                                                                                                                                    case -284299123:
                                                                                                                                        str21 = "ۘۗۖ۫ۦۡۘۧۡ۫۟ۙۖۘۥۥۘۜۢۘۘ۫ۢ۬ۗۘۦۘۗۚۛۚۢۥۘ۠ۚۜۘۖ۟ۦۘۡۦۧۘۘ۫ۜۘ";
                                                                                                                                    case 1687096179:
                                                                                                                                        break;
                                                                                                                                }
                                                                                                                            }
                                                                                                                            break;
                                                                                                                        case 744781500:
                                                                                                                            str19 = "ۥۜ۠۬ۥۖۘ۟ۢۖۘۤۡۥۖ۫ۢ۬ۘۤ۫۟ۢۨۘ۫۠ۛۦۥۗۨۖۜۗۛۤۙۨۧ۫ۢۗۖۘ";
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            case 49375326:
                                                                                                                String str23 = "ۖۛ۬ۘۦۚۢۙۡۘۙۘۘۜۦۛۥۖۖۘ۬ۡۧ۠ۡۨۘۚۛۘۘۘۥۥۘۥ۠ۥۢۤۗۜۥۗ۫ۘۘۙۡۜۘۦۨۤ";
                                                                                                                while (true) {
                                                                                                                    switch (str23.hashCode() ^ (-1967359829)) {
                                                                                                                        case -2049792093:
                                                                                                                            str18 = "ۘۛۖۘۙۜۖ۫ۦۦۘۢۦۛۨۗۥۘۗۙۧۦۗۙۙۨۦۦۗۧۢۖۥۗۜۥۥۙۥۘۜۨۨۘۤۛۥۘ";
                                                                                                                            break;
                                                                                                                        case -1374778237:
                                                                                                                            str23 = "ۛۥۜۘ۟ۤۜۜۖۡ۬ۡۘۢۜۢ۠۠ۨۜۖۧۧۖۦۤۖۥۧۡۦۘ";
                                                                                                                        case -312234309:
                                                                                                                            str23 = strOptString != null ? "ۙۤ۫ۜۤۘۧۛۘ۠ۛ۫ۥۤۦۖۦۘ۬۟ۨۘۗ۫ۘۢۜۡۘۤ۬ۥ" : "ۜ۠ۥۖۨۜۛۜۦۘ۠ۤۙۢۢۦۦۜۖۘ۫ۙۜۚۦۤۗۢۖۘۨۖۚۢۙۤۢۖۡۘۡۡۧ۬ۤۡ";
                                                                                                                        case 102954561:
                                                                                                                            str18 = "ۙ۟۠ۗۗۥۘۘۧۛ۬ۙۡۢۙۖ۬ۗۜ۠ۚۥ۟۠ۙۛۜۤۙۘۦۤۙۙۦۖۛۜ۬۠ۙۛ۠";
                                                                                                                            break;
                                                                                                                    }
                                                                                                                }
                                                                                                                break;
                                                                                                            case 1331338502:
                                                                                                                break;
                                                                                                            case 1748108215:
                                                                                                                str18 = "ۖۛۡۦۙۦ۟ۢ۟ۗۧۛۢۗۚۙۢۛ۟ۘۦۘۖ۬ۤۧۖۨۢۨۦۢ۟ۨۤۡۧ۠ۤ۠ۚۡۜۘ";
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case 1076807444:
                                                                                                    break;
                                                                                            }
                                                                                        }
                                                                                        i++;
                                                                                        break;
                                                                                    case 1102353442:
                                                                                        break;
                                                                                    case 1826963471:
                                                                                        str14 = "ۛ۟ۘۘۛۤۘۘۖۙۘۘۗۡۜۘۘۚۨۘۦۗۡۚۥۙۨۘ۠ۥۡ۫ۧۘۘ۟ۛۘۘۡۚۙۛ۬ۙۤۜۧۘ۬۟ۤۜۖۖۘۦ۫۟۠۬ۥ";
                                                                                }
                                                                            }
                                                                        }
                                                                        break;
                                                                    case 590718147:
                                                                        String str24 = "ۘۥۧۘۛۤۡۘۜۙۘۘ۟ۛۡۘ۬ۖۜ۠ۧۨۗۛۛۦۡۤۦ۠ۥۗ۬۫۠ۦۡ۬ۙۦۘۜۘۥۙۢۦۚۦۘ۬ۚۡۘ";
                                                                        while (true) {
                                                                            switch (str24.hashCode() ^ 434821134) {
                                                                                case -422051985:
                                                                                    str13 = "ۢۦۥۘۙۗۖۛۢۥۛۥۨۨۨۙۚ۟ۜ۫۠ۘۘۧۘۜۘۦۚۦۘۗۥۚۘۨۚۦ۠ۧ۠ۛۨ۫ۗۥۘۙۚۘۦۜۙ";
                                                                                    continue;
                                                                                case -16113837:
                                                                                    if (jSONArrayOptJSONArray == null) {
                                                                                        str24 = "ۥۘۘۨۗۛۨ۟۬۠ۗ۠ۢ۠ۡۘۜۦۦۥۖۡۘۤۖۛۦۘ۠ۛۨۚۡۛۙۢۨۨۘۦۤۥۘۚۡۚ";
                                                                                        break;
                                                                                    } else {
                                                                                        str24 = "۠ۘۛۢ۠ۦۖۛۚۜۤ۟۫ۨۨۥۢۤۧۖ۫۠۫ۚۨۛۘۘۢۧۗۜۙۦ۬ۥۘۧ۠ۖۛۘۥ";
                                                                                        break;
                                                                                    }
                                                                                case 590802084:
                                                                                    str24 = "ۧۤۛۜۥۡۘۚ۫ۨۤ۬ۗۗۢ۟ۡۗۜۘۘۧۨۘۜۖۢۘۘۚۧۨۥۘۢۜ۟ۧ۠ۧ";
                                                                                    break;
                                                                                case 1395587120:
                                                                                    str13 = "ۛۗۗ۬ۡۤۨۥۗۛۧۥۛۨۚۚ۫ۗۡۘۙ۟ۤۤ۫ۢۢ۠ۢۡۢ۠۟ۛ۬";
                                                                                    continue;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case 1829744230:
                                                                        str13 = "ۗۤۖۘۥ۫۫ۤۖۡ۫ۘ۫ۨۨۧۘۛۢۦۗ۠ۦۘۧۖۢۜۡۗۥۖۧۘۖۢ۠ۗ۠ۗۜۘۖۜۛۘۙۦ۬ۧۨ";
                                                                }
                                                            }
                                                            break;
                                                        case 1845823329:
                                                            break;
                                                    }
                                                }
                                                break;
                                            case 1145850156:
                                                str9 = "ۚۡۗۚۧۘۘۨۧۜۚۥۖ۠ۥۧۘۚۡۨۘۙۨۖۘۥ۟ۦۘۜۨۗۤ۠ۛۗۢ۫ۡ۬۟ۘۢۦۘ۟ۙۖۘ۬ۙ۟ۖۤۡ";
                                            case 1177413885:
                                                break;
                                        }
                                    }
                                    try {
                                        Method declaredMethod3 = Instrumentation.class.getDeclaredMethod("newActivity", ClassLoader.class, String.class, Intent.class);
                                        declaredMethod3.setAccessible(true);
                                        return (Activity) declaredMethod3.invoke(this.val$originalInstrumentation, classLoader, str3, intent);
                                    } finally {
                                        try {
                                        } catch (Throwable th2) {
                                        }
                                    }
                                case 64449753:
                                    String str25 = "۬ۜۖۘۖ۠ۤۢۚۧۥۛۘۖۢۤۤۡ۫ۜ۠ۧۙۖ۟ۚۙۜۚۧۤۥۜۘۖۦۚ";
                                    while (true) {
                                        switch (str25.hashCode() ^ 1782028591) {
                                            case -2124424966:
                                                str8 = "ۜۛۙ۬۠ۥۜۢۢۖۙۗۨۙۗۜۤۢ۫ۖۛۢۦ۠ۡ۫ۥۤۘۦۘ";
                                                continue;
                                            case -134880388:
                                                if (i2 < 28) {
                                                    str25 = "ۘۘ۫ۚۢۚ۠ۜۘۡ۠ۖۗ۫ۗ۟ۘ۫۠ۤۥ۟ۚۥۤ۬ۜۘۙ۬ۡۘۛۡۘ۟";
                                                    break;
                                                } else {
                                                    str25 = "۠ۦۡۘۡۦۤ۟۟۠ۥۚۧ۠ۡۚۨۥۘۗ۬۫ۜۛۖۘ۬۫ۙۨۨۜۚۡۖۘ۬ۘۧۦۘۧ۬ۥۤۨۘۥ۬۫ۦۘ";
                                                    break;
                                                }
                                            case 435945934:
                                                str25 = "ۖ۬ۙ۠ۥۤۙۘۘ۠ۧۛ۬ۧۘ۟ۤۛۜۧۥۥۥۙۧۖۛۙ۟ۤ";
                                                break;
                                            case 734544590:
                                                str8 = "ۙۧ۫ۛۛۨ۠ۗۨ۫ۘۖۘۖۤۜۘۜۨ۬۬ۖۨۤۥۨۘۙۨۦۘۗۚ۠ۜۦۨۦۥۘ۠ۘۨۘۦ۠۬";
                                                continue;
                                                continue;
                                        }
                                    }
                                    break;
                                default:
                                    continue;
                            }
                            k2.logToFloatingWindow("newActivity 外层异常：" + th.getMessage(), "error");
                            throw new RuntimeException("newActivity 创建失败", th);
                        }
                    }
                });
                k2.logToFloatingWindow("Instrumentation 替换完成", "info");
            } catch (Throwable th) {
                k2.logToFloatingWindow("Instrumentation 替换失败：" + th.getMessage(), "error");
                throw th;
            }
        } catch (Exception e) {
            k2.logToFloatingWindow(h.d("J1ZqeCF+EA89TGh2BmQCDyZWazONmP6e6LLgn/71x8qnjaD81Io=\n", "TzkFE2gQY3s=\n", new StringBuilder(), e), "error");
            e.printStackTrace();
        }
    }

    private static void hookInstrumentation2(Context context) throws IllegalAccessException, NoSuchFieldException, NoSuchMethodException, ClassNotFoundException, SecurityException, IllegalArgumentException, InvocationTargetException {
        try {
            Class<?> cls = Class.forName("android.app.ActivityThread");
            Method declaredMethod = cls.getDeclaredMethod("currentActivityThread", null);
            declaredMethod.setAccessible(true);
            Object objInvoke = declaredMethod.invoke(null, null);
            Field declaredField = cls.getDeclaredField("mInstrumentation");
            declaredField.setAccessible(true);
            Instrumentation instrumentation = (Instrumentation) declaredField.get(objInvoke);
            Instrumentation instrumentation2 = new Instrumentation(context, instrumentation) { // from class: gTBLD.dev.XSSTG.free.HookManager.2
                final Context val$context;
                final Instrumentation val$originalInstrumentation;

                {
                    this.val$context = context;
                    this.val$originalInstrumentation = instrumentation;
                }

                /* JADX WARN: Code restructure failed: missing block: B:394:0x005b, code lost:
                
                    continue;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:395:0x005b, code lost:
                
                    continue;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:398:0x005b, code lost:
                
                    continue;
                 */
                /* JADX WARN: Code restructure failed: missing block: B:754:?, code lost:
                
                    return (android.app.Instrumentation.ActivityResult) r0.invoke(r13, r3);
                 */
                /*
                    Code decompiled incorrectly, please refer to instructions dump.
                */
                private Instrumentation.ActivityResult invokeExecStartActivityCompat(Instrumentation instrumentation3, Context context2, IBinder iBinder, IBinder iBinder2, Activity activity, Intent intent, int i, Bundle bundle) throws Exception {
                    Object objInvoke2;
                    Object obj;
                    Object[] objArr;
                    ArrayList arrayList = new ArrayList();
                    Method[] declaredMethods = Instrumentation.class.getDeclaredMethods();
                    int length = declaredMethods.length;
                    int i2 = 0;
                    while (true) {
                        String str = "ۘۨۥ۬ۜۘ۠ۧ۠ۘۛۦۘۥۙۛۗ۟ۖۖ۟۟ۘۚۙ۟ۥۤۤ۫ۡ";
                        while (true) {
                            switch (str.hashCode() ^ (-1298437569)) {
                                case -1331525696:
                                    String str2 = "ۨ۬ۢۡۜۜۚ۬ۤۢۤۥۘۦۗۙۢۢۥۘۘ۟ۖۘۚۨۗۧۜۜۜۢۚ۠ۢ۫ۦ۠۬ۘۤۧۗۢۨۖۨۘۚۛۨۘۗ۫ۢۤۙ۟";
                                    while (true) {
                                        switch (str2.hashCode() ^ 1563564745) {
                                            case -2034134343:
                                                str = "ۖۖۧۘۨ۬ۦۚۚۖۘ۟ۧۡۥۘۜۘۤۖۘۘۘۨۢ۟ۜۧ۟ۛۦۘۛ۠ۦۛۤۖۘۡ۠ۦ۟ۦۛۨۘ";
                                                break;
                                            case -1595735557:
                                                str2 = i2 < length ? "ۧۚۨۘۖۤۗۛۡۨ۬ۦۨۘۤۖۤۛۥۖۙۦۧۘۜ۟ۙ۫ۛۤۜۦۗۥۧ۟۬ۛۧۦۨۚۙۥۗ" : "ۖۜۢۖۖۛۧ۟ۡۡۤۖ۫ۦۧۧۛۙۨۨۘۜۢۡۘ۬۫ۗۘۜۜۘۘۡۡۗۦۘۥۖۚۗۤۡۚۘۨۘۖ۟ۦۘ۬ۙۡۘۤ۬ۦ";
                                            case -1175012490:
                                                str2 = "۠ۘ۠۬۟ۢۢ۠ۤۚۙۛۗۖۢۢ۟ۙ۠۫ۚۢۘۘۘۦۙۢۜۖۚ۫۬ۘۧۛۥۘ";
                                            case 152811479:
                                                str = "۬۬ۧۨۡۦۤ۟ۗۤۜۘۢۖۡۤۨ۬ۨۜۘۗۜۡۘۥۤ۫ۜ۠۬۟ۘۦۚۚۖ";
                                                break;
                                        }
                                    }
                                    break;
                                case -1032311325:
                                    String str3 = "ۜۨۛۦۙۗۜۢۙۚۧۡۘ۟ۡۜۘ۫ۛۗۢۙۙۗ۠ۨۦۗۜۘۚ۠ۙۗۛۗۘ۟ۡۚۜۥۘۢۖۖۘۢۛۥۘ۠ۤ۫";
                                    while (true) {
                                        switch (str3.hashCode() ^ 2097472923) {
                                            case -28912900:
                                                String str4 = "ۧۦۨۘۦۜۢۤ۟ۜۘۢۥۛۢ۫ۨۘۖۗۥۘ۫ۧۘۘۦ۟ۚۦ۠ۖ۠ۘۘۦۚ۠ۙۨۛ";
                                                while (true) {
                                                    switch (str4.hashCode() ^ (-542328344)) {
                                                        case -181799814:
                                                            str3 = "ۤۥۜۘۗۛۜۘۙۡۤۧۧۨۘۨۚۙۥۥۡۘۢۖۢۢۜۨۘۜ۬ۤ۠ۛ۬ۡ۠۬ۖۗۜۙ۬ۥۘ۫ۘۨۘۗۡۚۚ۫ۙۦۙۨۘ۬ۜۛ";
                                                            continue;
                                                            continue;
                                                        case 785454640:
                                                            if (bundle == null) {
                                                                str4 = "۠ۨۨۘۧۢۖۘ۠ۚۘۘۗۨۘ۬۟ۥۘ۬ۘ۬ۦۧ۟ۨ۠ۡۘ۬۫ۖۘۚۧۜ۠ۦۡۘ۬ۧۦۛۧۤ۟ۡۙ";
                                                                break;
                                                            } else {
                                                                str4 = "ۖۤۢۗۦۖ۠ۥۙۛۨۘ۬ۢۙۜۛۘۘۙۢۖۘ۫ۤۘۨۚۖۘۚۧۙ";
                                                                break;
                                                            }
                                                        case 1403690242:
                                                            str3 = "ۙۚۚۛۜۗۛۨۛۡ۬ۡۤۚۡۨۚۦ۟ۜۛۧۛۤۛۨۖۜۛۨۤۗ۬ۘۘ";
                                                            continue;
                                                        case 1738134100:
                                                            str4 = "ۦ۠ۧۨۥۥۜۘۨ۠ۚۦ۟ۡۦۙ۫ۨۥۖ۬ۛۤۨۨۥۧۨۧۡۘ۠ۧۨ";
                                                            break;
                                                    }
                                                }
                                                break;
                                            case 432937858:
                                                str3 = "ۖۖۧۤۦۡۘۙۜۥۘۡۙۨۨ۠ۖۘۙۦ۬۟ۛۤۙۜ۟۫ۦۦۡۘۛۗۡ۟ۡۘۦۨۙۤۙۨ۠ۤۢۦۘۤۢۦۘ";
                                                continue;
                                            case 627533730:
                                                break;
                                            case 1873987851:
                                                try {
                                                    objInvoke2 = Class.forName("android.app.ActivityOptions").getMethod("fromBundle", Bundle.class).invoke(null, bundle);
                                                    break;
                                                } catch (Throwable th) {
                                                    break;
                                                }
                                        }
                                    }
                                    objInvoke2 = null;
                                    Iterator it = arrayList.iterator();
                                    Exception exc = null;
                                    while (true) {
                                        String str5 = "ۘۘۤۥ۬ۜۜۤۥۘ۟ۗۙۢ۫ۗۗۨۘۖ۬ۙۡۘۜۜۗۗۚ۟۫ۚۖۖۘ۠ۘ۫ۧۛۡۘۘ۠۫ۢۦۧۛۥۦ۬ۘۦ۬۬۫";
                                        while (true) {
                                            switch (str5.hashCode() ^ 1921526602) {
                                                case -1242908444:
                                                    Method method = (Method) it.next();
                                                    try {
                                                        method.setAccessible(true);
                                                        Class<?>[] parameterTypes = method.getParameterTypes();
                                                        String str6 = "ۚۨۧۘ۬ۙۙ۫ۨۧۘۤۥۥ۫ۚۘۚۦۘۛۘۨ۫ۥۘ۟ۨ۫ۢۤۜۘ۠ۡۡۘۨۦۨۤۥۤۨۤۤۙ۟ۘۘۡۜۤۥۤۘۘۢۜۜ";
                                                        while (true) {
                                                            switch (str6.hashCode() ^ 240785282) {
                                                                case -1152868142:
                                                                    String str7 = "ۛۦۘۘ۬ۜ۬ۦۜۘۘۦ۫۠ۥۙۥۘۢۜۘۧۢۥۗ۠ۥۘۘۧۦۡۦ۟ۧۥۚۡ۟۬۫ۤۖ۬ۨ";
                                                                    while (true) {
                                                                        switch (str7.hashCode() ^ 1149927809) {
                                                                            case -547859104:
                                                                                String str8 = "۠ۚۨۘۜۗۗ۫ۚۜۙۦۘۡۗۗ۟ۤۖۘۡ۠ۡۘ۬۟۟۟ۖۗۦۜۗ";
                                                                                while (true) {
                                                                                    switch (str8.hashCode() ^ 1165790967) {
                                                                                        case -2062277562:
                                                                                            str7 = "ۢۢ۬ۥۖۜۘ۬ۖۘۗۙۧۙۘۧۛۜۧ۬۟۬ۙۛۦۘۢۦۨۘۖۡۨۡۖۥ۠ۢۗۙۥۚۤ۟ۙۜۧۜۚۡۧ";
                                                                                            break;
                                                                                        case -265383160:
                                                                                            str8 = parameterTypes.length == 6 ? "ۦۗۡۘ۫ۤۚۤۘۡۘ۟ۧۧۨۡۖۘۗ۬ۨۘ۠ۛۨۤۚۚ۟۟ۧۤۦۘۚ۟ۥۛ۫ۥ" : "۬۬ۥۘ۬۠ۚۦۨۥۤۦۘۙ۟۠۟ۤۖۘۦۥۦۘۙۚۡۘۥۚۥۘۗۢۡ۬ۢۨۘ۠ۗۚ۟ۦۧۘۦۗ";
                                                                                        case 717734241:
                                                                                            str8 = "۟ۢۨۘۛۦ۟ۢۦۡۙۚ۟ۥۙۧ۟ۡۚ۟ۡۘۙۗۙۨۡۗ۟۬ۙۧ۫ۚۘۜ۬ۛۤۙۜ۟ۘ";
                                                                                        case 840967275:
                                                                                            str7 = "ۘ۬ۧۖ۠ۤۘ۠ۜۥ۫ۦۘۙ۠ۘۘۥۦۨۚۘۜۘۤ۠۟ۙ۠ۘۘۥ۫ۖۘۖۗۡۙۨۦۘ۫ۧۥۙۨ۠ۘۧۦۘ۫ۘۖۘۜۛۖۘ۠ۚۤ";
                                                                                            break;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case 462594991:
                                                                                str7 = "ۧۜۥۨۤۦۡۤۛۢۥۢۖۥۧۤۢ۫۫ۘۙۦ۟ۚۛ۟ۢۙۖ۫ۙۤۥۘۛۙۨۘۙۚۥۤۨۚ";
                                                                            case 710728076:
                                                                                String str9 = "ۜ۫ۡ۟ۖۡۖۢۘۘۜ۠ۖۦۘۖ۟ۚۛۘ۟ۢ۠ۙۜۙ۬ۦۥ۬۟ۡۤ۟ۖ۬ۘۨ۠ۛ۟ۡۜۘ۠ۨۘۧۥۧۘ";
                                                                                while (true) {
                                                                                    switch (str9.hashCode() ^ 251311436) {
                                                                                        case -1152191878:
                                                                                            str9 = "۫ۖۖۘۥۗۡۗ۠ۥۙ۫۬ۤۧۙ۬۫ۦۛۨۗ۠ۡۢۨۦ۫ۚ۠ۨۘ۟ۜۦۖ۟ۢۜ۬ۨۘ۠ۘۧ";
                                                                                        case -123881384:
                                                                                            break;
                                                                                        case -118719465:
                                                                                            String str10 = "ۘۤۖۡۤۦۘۜۤۘۘۗ۬۬۟ۛۘۘۡۢۖۘۢ۟ۙۛۦۘۖۨۜۘۧ۬ۧۥۡۡ۟۟ۡ";
                                                                                            while (true) {
                                                                                                switch (str10.hashCode() ^ 1369150859) {
                                                                                                    case -1492652645:
                                                                                                        str10 = parameterTypes.length == 8 ? "ۡۢۖۘۖۡۜۘۚۜۗ۬ۛۨۢۤۘۜۦۚۘۢۡۦۜۚۥۢ۠ۛۢۘۖۦۖۘۜۚ۬" : "۫ۖۜۘۙۨۛۢۡۘۗ۟ۜۘۚۜۘۛۦۡ۫ۨۢۘۤۛۢۡۜۘۛۖۘۘۦۨۥۦۚ۠";
                                                                                                    case -435499798:
                                                                                                        str9 = "ۧ۠ۡۘ۟ۢۦۘۙ۬۫ۘۦ۫۫ۘ۟ۢۚ۬ۛ۬۟ۗ۟ۜۛۜۜۛۚۜ";
                                                                                                        break;
                                                                                                    case 526412082:
                                                                                                        str10 = "ۧۙۜۙۥۙۤۗۡۦۗۡۡۗ۫ۤۖۘۜۡۨۘۤۢ۬ۘۢۥۘ۫ۤ۬ۨۚ۬ۥۦ۫ۘۧۘۘۥ۠ۡ";
                                                                                                    case 740366623:
                                                                                                        str9 = "ۧۦ۠ۢۚۘۘۢۖۥۗۢۖۘ۬ۛۖۘۨۨۨ۬ۡ۠ۥۗۨۦۨۜۛۜۥۙۥۛۜۡۖۜۗۜ۬ۥۦۦ۠ۦۘۛۜۘ";
                                                                                                        break;
                                                                                                }
                                                                                            }
                                                                                            break;
                                                                                        case 1143366408:
                                                                                            String str11 = "۬۬۟ۚۦ۬ۘۤ۬ۘۙۗۜۧۛۚ۟ۗۡۚۜۨۘ۟ۖۨۘ۫ۦۦۖۖۢۖ۫۬ۗۚۨۘۡۘۤ";
                                                                                            while (true) {
                                                                                                switch (str11.hashCode() ^ (-459809497)) {
                                                                                                    case -1862030713:
                                                                                                        String str12 = "ۚۡۦۘۢۢ۬ۚۗۙ۫ۦۗۖۙۜۧۥۢۚۥ۟۠ۜۧۢۦۘۧۗۙۤۦۛۨۨۨۘۡۧۗۢۘۧۘ۟۟ۛۧۗۛۖۛ۫ۘۗۥ";
                                                                                                        while (true) {
                                                                                                            switch (str12.hashCode() ^ 104787002) {
                                                                                                                case -402033450:
                                                                                                                    break;
                                                                                                                case -247335117:
                                                                                                                    String str13 = "۟۬ۦۛۡۘۘۨ۠ۜۘۥ۠ۜۘۢۚ۫ۛۤۨۙ۬ۛ۫ۢۥۧ۬ۡۤۨ۠ۛ۫ۥۘۦۡۗۥ۬ۗ";
                                                                                                                    while (true) {
                                                                                                                        switch (str13.hashCode() ^ 777664580) {
                                                                                                                            case -1357586686:
                                                                                                                                str12 = "۬ۙۥۘ۬ۨۤ۟ۘۨۙۚۡۡۜۖۘۛۛۖ۫ۡ۠ۡۜۨۘۚۙۚۗۜۜۗۚۗۢۗۜۧۧۜۘۦ۠۟ۢۧۨۘۦ۬ۥۘ";
                                                                                                                                break;
                                                                                                                            case -839453149:
                                                                                                                                str12 = "ۨۖ۬ۜۗۖۘۨۦۨ۫ۖۜۢۤۦۘۥۙۨۘۚۙۢۢۘۦۘۦۤۙۢۧۛ";
                                                                                                                                break;
                                                                                                                            case 1398051735:
                                                                                                                                str13 = objInvoke2 != null ? "ۜ۬ۤۡۥۧۡۨۖۥۗ۟ۘۜۘۦۥۨۘۦۖۗۤ۠ۜۘۧۤۥۘۘۙۛۢۚۚۧۙۡۨۤ۠ۥ" : "۫ۦۘۥۥۜۘۜۙۨۘ۬ۜۨۜۡۗۙۧۦ۟۠ۙۖۘۗۖۡۘ۬۫ۨۘ";
                                                                                                                            case 1845380875:
                                                                                                                                str13 = "ۦۜۦۤۢۛ۫ۦۦۘۜ۬۟ۖ۟ۨۡ۠ۡۦ۟ۖۘۛۗ۬۫ۜۥۘۢۖۧۘ";
                                                                                                                        }
                                                                                                                    }
                                                                                                                    break;
                                                                                                                case -118317303:
                                                                                                                    str12 = "۠ۡ۟ۨ۠ۨۘۛۤۖۙ۠ۙۧۙۙۘ۫ۨۥ۠ۡۢۨۢۗۚۡۨ۬ۡ";
                                                                                                                case 373606388:
                                                                                                                    String str14 = "ۖۤۤ۠ۖۤۛ۠ۘۘۤۧۖۘۙۛۨۘ۬ۙۧۛۛ۠۠ۙۘۘۢۡۡۢۦۨۘۦۖۘۘۜ۟ۘ";
                                                                                                                    while (true) {
                                                                                                                        switch (str14.hashCode() ^ (-158069359)) {
                                                                                                                            case -1330840429:
                                                                                                                                String str15 = "ۧۛۥۦۗۖۤۤۛۛۧۢۚۗۧۖۖۥۘ۟ۛۜۧۖۙۘۧۦۗۡۧ";
                                                                                                                                while (true) {
                                                                                                                                    switch (str15.hashCode() ^ (-1317442874)) {
                                                                                                                                        case -2090455026:
                                                                                                                                            str15 = "ۥۤۥۘۡۜ۬ۜۖۙۧۥۨۨ۠ۗۥ۫ۛۚۜ۠۟ۤۛۡۖ۫۟ۖۘ";
                                                                                                                                        case -1911874493:
                                                                                                                                            str14 = "ۡۢۨۖۗۦۘ۟ۚ۟ۧۥۧۖۡۡ۬ۘ۠ۜۥۛۛۨۘۘۥۘۤ۬ۨۘۜۙ۟ۤۨۡۘ";
                                                                                                                                            break;
                                                                                                                                        case -671294152:
                                                                                                                                            str15 = parameterTypes[7].isInstance(objInvoke2) ? "ۥۘۖۘ۬ۛ۠۫ۡۘۘ۠ۜۘۗۘۧۘۡۛۛۥۜۤۗۦۨۘۥۤۜۘ۠ۚۙۨ۬ۖۘۘۡۡۤۨۧۙۛۢۧۧۥۘۜ۟ۘۘ" : "ۥۜۢۛۤۗۘ۠ۨۥۦۘۢۨۘۥۖۚۢۧۙۜۢۖۖۖۧۘۛۗ۟";
                                                                                                                                        case 1091098661:
                                                                                                                                            str14 = "ۜۧ۟ۜۡۧۘۙۘۛۦۗۨۤۚۛۡۜ۠ۛۢۜۜۢۤۖۖۜۘ۠۟ۘ";
                                                                                                                                            break;
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                break;
                                                                                                                            case -522306591:
                                                                                                                                break;
                                                                                                                            case 585306285:
                                                                                                                                obj = objInvoke2;
                                                                                                                                break;
                                                                                                                            case 1955148606:
                                                                                                                                str14 = "ۙۥ۠۬ۚۜۖۚۥۛۤۘۘ۫ۡ۬ۢۜ۠ۛۖۜۙۤۡۧۖۤۛۗ";
                                                                                                                        }
                                                                                                                    }
                                                                                                                    break;
                                                                                                            }
                                                                                                        }
                                                                                                        parameterTypes[7].isPrimitive();
                                                                                                        obj = null;
                                                                                                        break;
                                                                                                    case -1329468196:
                                                                                                        String str16 = "۟ۙۦۖۖۘۘۚ۟ۗۧۗۦۧۢۨۖ۟ۜۘ۟ۘۦۘۡۥۨۙ۫۠ۡۖۡۘۗ۠ۛۦۨۘۘۨۨۧۙۗۙ۫ۢۦۤ۬۫۫ۨۡۡ";
                                                                                                        while (true) {
                                                                                                            switch (str16.hashCode() ^ 1744012713) {
                                                                                                                case -1789490625:
                                                                                                                    str16 = Bundle.class.isAssignableFrom(parameterTypes[7]) ? "ۢۚۦۘۡۨۦۘۡۢۤۥۨ۬۟ۡۘ۠ۙ۠ۖۜ۟ۡۧۘۘۗۡۜۘۙ۬ۨۥۦۥۨۗ۬۠ۥۜۖۚۥۙۨۧ۬ۙۦ۠ۢ۠ۘۛۦۘ" : "ۗۢۦۖۗۖۘۘۤۢۥۧۚۙۨۘۖۗۦۦۦۡۘۤۚۧ۠ۘۖۙ۬ۨ";
                                                                                                                case 1102212604:
                                                                                                                    str11 = "۠ۖ۬ۢۨۥۘۨۡۡۘ۟ۥۦۘۡۨۡۥۥ۟ۧۗ۫ۜۢۧۥۨۚۨۜۡۘ";
                                                                                                                    break;
                                                                                                                case 1312237559:
                                                                                                                    str11 = "ۚۘۦۘۢ۬ۖۗۡ۠ۗ۠ۗ۬۠۠ۘ۟ۛۗ۫ۨۢ۬ۧۚۤ۫ۦۦۡۛۚ۠ۦ۟ۤۧۖۦۘۗۗۤۙۦۜۙۜۘۚۛۢۨۧۨۘ";
                                                                                                                    break;
                                                                                                                case 1939516972:
                                                                                                                    str16 = "ۡۨۡۛۨۙۙۜۘۚ۫ۢۗ۬ۘۘۜۧۥ۟ۧۢ۫ۨۛۥ۠۠۟ۘۚۛۡۢۛۡۖ۬ۦۧۜۘۘۥۢۗۜۚ";
                                                                                                            }
                                                                                                        }
                                                                                                        break;
                                                                                                    case 24506330:
                                                                                                        obj = bundle;
                                                                                                        break;
                                                                                                    case 499499825:
                                                                                                        str11 = "ۢۥۨۨۦۥۢۦۛۧۜۗۥۤۥۨۗۡۧۛۜۦۖۙ۟ۦۥۘۗۨۛۨۖۘۘ۠ۚۜۚۡۘۜۨۧ۬ۦۜۘۖۗۥۢ۫۫۟ۢۜ";
                                                                                                }
                                                                                            }
                                                                                            try {
                                                                                                objArr = new Object[]{context2, iBinder, iBinder2, activity, intent, Integer.valueOf(i), null, obj};
                                                                                                break;
                                                                                            } catch (Exception e) {
                                                                                                e = e;
                                                                                                String str17 = "ۖ۫ۤ۟ۥۖۢ۬ۨ۫۟۟ۢۧۥۘ۬۠ۡ۫ۤۖۧۖ۬ۨۤۧ۬ۙۜ۬ۦ۠ۚ۟ۥۦۢۦ۫۬۟";
                                                                                                while (true) {
                                                                                                    switch (str17.hashCode() ^ (-1652607009)) {
                                                                                                        case -1418027651:
                                                                                                            str17 = "ۢۜۨۘۤۙۜ۫ۗۧ۫ۤۤ۬۠۫ۧۢۗۨۤۥۘۙ۟ۧۖۢۤۨۥۤۥ۫ۧۖۤۛۖۖ۫ۗۡۖۗۖۗۡۘۥۥۢۗۨۜ";
                                                                                                            break;
                                                                                                        case -964428666:
                                                                                                            String str18 = "۟۠ۨۘ۫ۤ۠ۗۖۥۘۗۖۨۡۦۚۖۖۘۜۢۢۧۢۜۗۗۖۜ۫ۙۤۖۦۦۜۘۙۘ۫ۖ۟ۤ";
                                                                                                            while (true) {
                                                                                                                switch (str18.hashCode() ^ 1127307009) {
                                                                                                                    case -2059211200:
                                                                                                                        str18 = "ۙۨۖۘۙۤۖ۠۫۫۫ۖۡۧۛۚۤۡۗۢۡۜۘۦۤۢۙۖۢۘۡۜ";
                                                                                                                    case 1409463771:
                                                                                                                        str18 = exc == null ? "ۜۦۖ۫ۦۦۨۡۘۘۦۤۜۘۖۢۖۦۙۡۘۦۖۘۛ۫ۜ۬۬ۥۘ۫ۖۥۘۙ۬ۥۖ۫ۡۚۥۥۘۜۖ۠ۖ۟ۥۦۚۚ" : "۠۠ۜۘۥۨۡۧۤۛۧۛ۠ۘۛۗ۫ۥۥۜۜۦۤۘ۠ۢۧۘۘۙۢۡۘۧۜۦ۠ۧۥۘۧۜ۫۬۬ۢۥ۟ۦ۠ۚۦۘ";
                                                                                                                    case 1554608146:
                                                                                                                        str17 = "ۚ۟ۜۘۤۢ۟ۘۘۦۧۘۢۨ۬۫۫ۘۡۨۢۖۘۧۡۖۘ۬ۦۖۡۛۜۘ";
                                                                                                                        break;
                                                                                                                    case 1605172882:
                                                                                                                        str17 = "ۡۖۗۛۥۖۡۢۡۛۦۜۘۚۨۦۗۜۢۗۚ۠ۤۚۜ۠ۡ۠۠ۜۧ";
                                                                                                                        break;
                                                                                                                }
                                                                                                            }
                                                                                                            break;
                                                                                                        case -29825245:
                                                                                                            exc = e;
                                                                                                            continue;
                                                                                                        case 331553000:
                                                                                                            break;
                                                                                                    }
                                                                                                }
                                                                                            }
                                                                                            break;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case 1362845080:
                                                                                objArr = new Object[6];
                                                                                objArr[0] = context2;
                                                                                objArr[1] = iBinder;
                                                                                objArr[2] = iBinder2;
                                                                                objArr[3] = activity;
                                                                                objArr[4] = intent;
                                                                                objArr[5] = Integer.valueOf(i);
                                                                                break;
                                                                        }
                                                                    }
                                                                    break;
                                                                case -399586869:
                                                                    str6 = "ۚۖۜۤۛۚۧۛۜۨۜۘۨۢ۬ۘۚۧۢ۫۟ۦۛ۟ۡۜ۬ۛۦۘ";
                                                                case 1352990484:
                                                                    String str19 = "۫ۘۥۘۨۗۧۦ۫ۖۘۛۖۛۘۦ۬ۛۡۥۧۤۡۘ۠ۘۡۧۜۨۦ۠۫ۥۤۧۡۜ۫";
                                                                    while (true) {
                                                                        switch (str19.hashCode() ^ (-288442447)) {
                                                                            case -1948504886:
                                                                                str6 = "ۜ۫ۡۘۡۡۧۘۢۦۡۗۥۨ۟ۖ۬۬ۛ۬ۚۖ۟۠ۖۦۘۦۡۘ۠ۥۧۨۨۦۘ۫ۖۡۗۢۛ۬ۚۛۤۧۛۥ۬ۡۘ";
                                                                                break;
                                                                            case -397901428:
                                                                                str19 = "۠۟۫ۧۙۗ۠۟ۘۗ۟ۛۢ۠ۨۘۥۤۡۢۨۜۡۢۖۘۤۖۤ۠ۗ۠۬۠۬ۛۛۤ";
                                                                            case 510895764:
                                                                                str6 = "۫۫۫ۧۨۛ۫۬۠ۨۥۘۘ۬۬ۧۤ۬ۤۗ۠ۜۚۧۛۗۖ۠۬ۘ";
                                                                                break;
                                                                            case 1920706184:
                                                                                str19 = parameterTypes.length == 7 ? "ۤۙۡۘۨۤ۟ۤۥۧۘۥۦۦۚۜۧۙۗۛۢۦۤۛۜ۬ۥۢ۠ۖۖۘۖۙۡۘۧۤۡ۟ۧ۠ۥۨۢ" : "ۧ۠ۗ۫ۘ۟ۧۨۚۨ۟ۜۘۛ۟ۜ۟ۗۘۘۛۛ۬ۢۥۧۙ۬ۡۘۦۤ۫ۦۧۦۘۥ۬ۢۧۡۥۘۖۢۛۨۢۗۥ۬ۜ";
                                                                        }
                                                                    }
                                                                    break;
                                                                case 1397764608:
                                                                    String str20 = "ۜ۬۟ۤ۠۬ۤۨۘۜۙ۠ۗۖ۬ۨ۠ۖۘۘۡۖۘۢ۬ۨۤۨ۫ۦۢۥۗۡۢۙۥۘۜۥۛۥۧ۠";
                                                                    while (true) {
                                                                        switch (str20.hashCode() ^ (-1219627894)) {
                                                                            case -968560215:
                                                                                str20 = "ۘۖۖ۫ۤۢۘۜۗۤۗ۟۫ۖۧۗۖ۟ۦۥۛۦ۬ۥۜۙۙۧ۟۠۬ۜۥ۫ۤۖۨۚۧۥ";
                                                                            case 924442079:
                                                                                String str21 = "ۤۦۡۛۨۧۖۥۘۘۢۛۘۘۛ۟ۡۘۖۗۜۙۘۡۚۥۙۤ۬ۥۘ۬ۦۦۡۖ۠۫۠ۗ";
                                                                                while (true) {
                                                                                    switch (str21.hashCode() ^ 319943135) {
                                                                                        case -1386001952:
                                                                                            String str22 = "ۚۖۖۥۤۦ۠ۤۦۛۦۙۡۛۘۖۜۘۗۤ۬ۖۗۦۙۚ۟۫ۖ۠ۤ۠ۚۛۤ۟";
                                                                                            while (true) {
                                                                                                switch (str22.hashCode() ^ (-956379489)) {
                                                                                                    case -1860471627:
                                                                                                        str22 = "۟۫ۤۜۥۜۘۥۛۤۨۖۧۢ۬ۦۘۥۘۘ۠۠۟ۛۚۛۜۛ۬ۡۦۖ";
                                                                                                    case -980573437:
                                                                                                        str21 = "ۧ۠ۖۡۗ۫ۖۡۡۛۛۤۤۦۙۙۨۜۘۖۤۡۗ۟ۢۙۗۛۡۚۨۘۖۜۖ۟ۛۘ";
                                                                                                        break;
                                                                                                    case 1371630909:
                                                                                                        str22 = objInvoke2 != null ? "ۘ۟ۢۢ۟ۜ۬ۥۚۗۨۢۦۢۖۘۗۚۡۘۛ۟ۨۘۛۙۜۘۜۗ۬ۙ۠ۙۤۦۨۘۖۘۙ" : "ۢۗۡ۬۬ۙۖۡۚۥۨۥ۠ۦۘۛ۠۫۟ۛ۫ۥ۬ۦۘۙۗۘۘۖۤۜۘ";
                                                                                                    case 2017359005:
                                                                                                        str21 = "۫۟۫ۚۙۗۗۖۘۗۥۖۘۡ۬ۨۘ۬ۡۦۘ۫ۙ۫۬ۧۦۘۧۖۥۛۡۡۘۡۤۧۚ۬ۧ۫ۥۜۦۛۥ";
                                                                                                        break;
                                                                                                }
                                                                                            }
                                                                                            break;
                                                                                        case -940576755:
                                                                                            str21 = "ۚۛۚۧۢۗۧۜۢۚۖۦۘۤۖۤۢۡۚ۬ۦۖۜ۟ۖۦۚۜ۬۠ۛۜۤ۠۬ۚۘۘ۟ۛۡۚ۬۠";
                                                                                        case -708622544:
                                                                                            break;
                                                                                        case 2138243367:
                                                                                            String str23 = "ۘۦۨۘۦۦۛ۬ۙۦۤۢ۟ۗ۠ۘۦۥ۟ۙۛۤ۟ۧۘۨۥۘۘ۬ۛۧ۟ۨۡۗۡۜ۬ۚۡۜ۠ۨۘۧۖۛۥۖۘۘۘۘۡۥۤۖۘ";
                                                                                            while (true) {
                                                                                                switch (str23.hashCode() ^ 1654511810) {
                                                                                                    case -2139729561:
                                                                                                        break;
                                                                                                    case -437851128:
                                                                                                        objArr = new Object[7];
                                                                                                        objArr[0] = context2;
                                                                                                        objArr[1] = iBinder;
                                                                                                        objArr[2] = iBinder2;
                                                                                                        objArr[3] = activity;
                                                                                                        objArr[4] = intent;
                                                                                                        objArr[5] = Integer.valueOf(i);
                                                                                                        objArr[6] = objInvoke2;
                                                                                                        break;
                                                                                                    case 1730030659:
                                                                                                        str23 = "ۦۢۖۖۚۜۘۙۡۤۡ۫۫ۥ۫۫ۜۡۥ۬۫۫ۨۜۜۨۖۚۗ۫ۜۘۜ۟ۙۘۘۦ۬ۢۜۨۜۨۛۚۗۧۥۘۙۡۢ۟ۢۡ";
                                                                                                    case 2049138073:
                                                                                                        String str24 = "ۨۜۡ۟ۡۤۗۜ۟ۧۡۘۚۙ۟ۤۢۡۘۡ۟ۜۘ۠۟ۗۘ۬ۤۘۦۚۧۘۘۢ۠ۥۙۛۡۢۡ۠ۧۗۢۢۡۥۘ";
                                                                                                        while (true) {
                                                                                                            switch (str24.hashCode() ^ 1928704519) {
                                                                                                                case -597652239:
                                                                                                                    str23 = "ۛۦۢۘ۟ۘۘ۟۫ۤۡ۠ۘۘۙۜۖۘۦۡۙۙ۠ۢ۟ۘۡ۫ۘۧۘۥ۬ۥۘ";
                                                                                                                    break;
                                                                                                                case 1096193213:
                                                                                                                    str24 = "ۢۙۨۘۖۢۗۘۦۜۘۜۙۘۦ۠ۗ۟۬۬ۧۗۜۘۨ۠ۗۤۧۥۘۥۧۨۢ۟ۙۢ۫ۘۘ";
                                                                                                                case 1536723774:
                                                                                                                    str23 = "ۦ۟ۡۘۦۘۥ۟۠ۚۖۛۨۘۘ۟ۥۛۡۥۥۛۦۘ۟۟ۨۡۡۨۖۖ۫ۦۘۢۚۘۦۨۗ۫ۥۙۗۙۧۖ۟ۘۛۜۗ۬ۗۗ";
                                                                                                                    break;
                                                                                                                case 2127773603:
                                                                                                                    str24 = parameterTypes[6].isInstance(objInvoke2) ? "ۛۖۥۘۚۖۛ۫ۚۙ۫ۢ۟ۚۙۥۢۘۜۦۘۚۛۦۖۛۢ۟ۥۡۖۜۢۛۧۦۚۥۧۦۦۖ" : "ۙ۟ۧۚۛۨۧۧۡۘۚۚۛۨۧۘۤ۟۬ۗۨ۫ۡۡۦۖۖۜ۬ۡۘۧۗۥۗ۬ۨ۠ۚۤۨۛ۬";
                                                                                                            }
                                                                                                        }
                                                                                                        break;
                                                                                                }
                                                                                            }
                                                                                            break;
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case 1656654388:
                                                                                String str25 = "ۙۖ۫ۜۚۜۗۤۧۢۢۤۘۘۦۡۤۨ۬ۙۢ۠ۡۘۨۘۨۗۚۜۘۛۨۡۘۜۤۜۘ";
                                                                                while (true) {
                                                                                    switch (str25.hashCode() ^ 1835809806) {
                                                                                        case -1958626018:
                                                                                            str20 = "ۛۥۘۘۗ۬ۡۘۙۘۥۘۛۘۛۦۚۦۘۖ۬ۦۢۘۚۨۗۨۘ۫ۗۜۘۖ۫ۘۘۚ۟ۥۢۥۦۛۗۘۘۘ۟ۘۘ۫۫ۤۡۦۚ";
                                                                                            break;
                                                                                        case -659026486:
                                                                                            str25 = Bundle.class.isAssignableFrom(parameterTypes[6]) ? "ۡۢۥۘ۬ۡ۠ۥۖۜۘۜۤۥۘ۬ۡۧۘۦۨۧۢۚۘ۠ۚۥۛ۟ۨۖۜۖۤۖ۟ۗۗۙ" : "۫ۢ۫ۢ۫ۥۤۢۚۖۘۗۦۤۛ۟۫ۖۘ۬ۤ۬ۙۙ۬۠ۨۗ";
                                                                                        case 586468035:
                                                                                            str20 = "ۤۡۨۤۤۜۚۙۜۘۖ۫ۦۘۦ۫۠ۘۤۚۖۛۘ۠ۙۜۘۛۢۘۘ۟۫";
                                                                                            break;
                                                                                        case 1223594189:
                                                                                            str25 = "ۤۧۥۘۡۤۜۘ۠۠ۘ۠ۡۦۘۤۖۘۡۤ۠ۖ۫ۖۜۥۜۘۖۗۦۘۡۦ۬ۗۜۡ۟۟ۜ۫ۗۨۤۥۚۚ۬ۜۤۚۗۤۦۥۤ";
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case 2145506079:
                                                                                objArr = new Object[7];
                                                                                objArr[0] = context2;
                                                                                objArr[1] = iBinder;
                                                                                objArr[2] = iBinder2;
                                                                                objArr[3] = activity;
                                                                                objArr[4] = intent;
                                                                                objArr[5] = Integer.valueOf(i);
                                                                                objArr[6] = bundle;
                                                                                break;
                                                                        }
                                                                    }
                                                                    break;
                                                            }
                                                        }
                                                    } catch (Exception e2) {
                                                        e = e2;
                                                    }
                                                    break;
                                                case 42343313:
                                                    try {
                                                        Method declaredMethod2 = Instrumentation.class.getDeclaredMethod("execStartActivity", Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, Integer.TYPE, Bundle.class);
                                                        declaredMethod2.setAccessible(true);
                                                        return (Instrumentation.ActivityResult) declaredMethod2.invoke(instrumentation3, context2, iBinder, iBinder2, activity, intent, Integer.valueOf(i), bundle);
                                                    } catch (Exception e3) {
                                                        Exception exc2 = e3;
                                                        String str26 = "ۥۜۨ۫ۖۧۥۘۗ۬ۘۧۘ۫۟۟ۖۢۡ۫ۥۜۗ۟ۛۢۤۘۡۙۦۘۙۛۘۖۛ";
                                                        while (true) {
                                                            switch (str26.hashCode() ^ (-1002819789)) {
                                                                case -713141216:
                                                                    exc2 = exc;
                                                                    break;
                                                                case -493871692:
                                                                    break;
                                                                case 199118271:
                                                                    String str27 = "ۘۧۤ۟ۘۨۘۜۜۛۖۛۧۚۥۡۘۚ۬ۘۘۚۢۙۗۚۜۘ۠ۘۧ۫۫۬۟۠ۘۦۚۡۘ";
                                                                    while (true) {
                                                                        switch (str27.hashCode() ^ 603091007) {
                                                                            case -1428094799:
                                                                                if (exc != null) {
                                                                                    str27 = "ۛۨۥۥۗۦۤۘۧۘۙۚۖۘ۫ۗ۬ۧۜۥۗۛ۬ۖۤۨۘۧۘۜۖۖۖۤ۠ۡۘۖۜۘۡۧۖۘۥۗ۠ۢۡ۬۫ۡۡۘ";
                                                                                    break;
                                                                                } else {
                                                                                    str27 = "۫ۛۛۡۛۖ۠ۨۥۘ۬۟۠ۥۥ۠ۨۛ۠ۙۦۘۘۗ۠ۨۘۗ۬۟ۥۤۨۘۤۗۘۘۙ۟ۘۖۧۖۙۨۢۗۢ۬ۗۥۛۤۡۨ۟ۙۧ";
                                                                                    break;
                                                                                }
                                                                            case -284717303:
                                                                                str27 = "ۖۢ۠ۧۙۖۧۛۜ۠ۤۘۘۥۗۘ۫ۙۨۘۘ۟ۢ۫۠ۡۘۢۛۤ۫۟ۤۡ۫ۗۛۚۙۜۜۛۖ۠ۨ۬ۢۡۘۨ۠ۘۡۜۤۦۛۦۘ";
                                                                                break;
                                                                            case 585827758:
                                                                                str26 = "۫۠ۤۜۚۜۘ۟ۥ۟ۤۙ۟ۥ۟ۜۘۢۘۛۥۚۦۗ۠ۡۦ۟ۦ۠ۜۧۛۧۧۛۢۖۘۥۧۘۙۖ۟";
                                                                                continue;
                                                                                continue;
                                                                            case 1454025929:
                                                                                str26 = "ۛ۬ۚۢۘۖۘۥۡۦۨۦۙۧۤۡۜۨۨۘۜۘۢۢ۬۟۠ۙۥۘۘۢۘۘ";
                                                                                continue;
                                                                        }
                                                                    }
                                                                    break;
                                                                case 1740159717:
                                                                    str26 = "ۨۖۜۘۧۙۨ۫ۗۢۡ۠ۘۘۡۤۗۘ۠ۧۚۘۦۤۡۦۘ۟ۖۖۚ۟ۨۡۖۨۘۦۨ۫ۗ۠ۨۧۚۜ";
                                                                    continue;
                                                            }
                                                        }
                                                        try {
                                                            Method declaredMethod3 = Instrumentation.class.getDeclaredMethod("execStartActivity", Context.class, IBinder.class, IBinder.class, Activity.class, Intent.class, Integer.TYPE);
                                                            declaredMethod3.setAccessible(true);
                                                            return (Instrumentation.ActivityResult) declaredMethod3.invoke(instrumentation3, context2, iBinder, iBinder2, activity, intent, Integer.valueOf(i));
                                                        } catch (Exception e4) {
                                                            throw exc2;
                                                        }
                                                    }
                                                case 892741965:
                                                    String str28 = "ۖۗۡۘۙۛۥۘۚۦۦۘۘۙ۫۠ۖۨۘۢۤۙۘۙۡۡۡۨۘۢۜۛۖ۟۬ۨۜۜۢۥۡۘ";
                                                    while (true) {
                                                        switch (str28.hashCode() ^ 1420285840) {
                                                            case -1957487246:
                                                                str28 = it.hasNext() ? "ۦۤۧ۟ۤۥۜۗۛۖ۬ۜۧۥۨۛۧۡ۬ۚۜ۟ۦۧۡۜ۬۬ۘۚۜۜۘۢۜۦۘ۠۟ۗۖۖۤ" : "۫ۖۡ۫ۛۜۘۧۜۨۘۧۧۤۧۤۥۘۘ۠ۖۘۥۨۧۘۤۜۨۘ۬ۡۚۤۙ۠ۗۖۨۘۗۙۖۘۢ۟ۜۘۦۤ۬ۗۧۥۖۦۘۗۨۖۘۧۢ۟";
                                                            case -378571819:
                                                                str5 = "ۜۚۡ۫ۙۙ۬۫ۖۧۘۜۛ۬ۗۥۨۛۘ۬ۡۘۘۙۤۘۘۡۘۢۤ۟";
                                                                break;
                                                            case -338309819:
                                                                str5 = "۫ۨۧۘۨ۬۫ۙۖۥۘۤ۬ۤ۬ۧۙۢۙۨ۬ۥ۬ۚ۟ۖ۠ۘۛ۬ۚۦۘ۬۠ۜۛ۫ۖۘ";
                                                                break;
                                                            case 1705587305:
                                                                str28 = "ۗ۬ۘۙۜۜۖۖۧۘ۠ۜۧۗ۠۠ۦۡۧۘ۠ۤۨۦۨۦۘۢۡۜۡ۟ۨۘ۠ۖۨۘۖۥۧۘۤۙۨۢۜۚ";
                                                        }
                                                    }
                                                    break;
                                                case 1104779094:
                                                    str5 = "ۢ۬ۥۘۧۧ۟۬ۥ۫ۥۙۢ۠ۙۡۘۨۢۥۗۚۜۘ۟ۘۦ۫۠ۦۜۡۚۛۖ۟ۦۡۘ";
                                                    break;
                                            }
                                        }
                                    }
                                    break;
                                case 1227043519:
                                    str = "ۖۨۛۘۛۦۛۛ۟۠ۤۡۧۧۖۤۢۘۘۜۦۚۡۧۤۦۨۛۦ۬ۘۘۜۚۡۘۜۗۗۢ۬ۢۙ۟ۥۖۜۖۚۦ۬۠ۤۥۘۛۥ۬";
                                    break;
                                case 1227603293:
                                    Method method2 = declaredMethods[i2];
                                    String str29 = "۟ۢۖۙ۟۟ۦۡۖۤۖۙۥ۟ۖۨ۟ۨۘۥۢ۠ۧۜۙۚۙۨۘۧ۫ۗۖۚۦۘۘ۫ۜۛۧۛۘۦۦ۫ۢۡۚۧۗ";
                                    while (true) {
                                        switch (str29.hashCode() ^ 277892318) {
                                            case -1901600824:
                                                Class<?>[] parameterTypes2 = method2.getParameterTypes();
                                                int length2 = parameterTypes2.length;
                                                int i3 = 0;
                                                while (true) {
                                                    String str30 = "ۡۡۜۖۧ۬ۖۘۦ۟ۚ۠ۧۜۜۨ۫۟۫ۖۘۥ۠ۧۗ۠ۢ۠ۤ۬ۦۥۘ۫ۦۥۘ";
                                                    while (true) {
                                                        switch (str30.hashCode() ^ 1678430956) {
                                                            case -1283747251:
                                                                str30 = "ۘۜۜۘۙۚۤۚۘۡۘ۠۟ۛۗۖۜۘۤۚۙۗۙۨۖۧۢۖۧۘۘ۠ۧ۠۫۠۠۠ۧۘۙ۫ۦۘ۫ۥۘ";
                                                            case -868364632:
                                                                String str31 = "ۧۡ۠ۚۢ۠ۦۚۦۚ۬ۥۘۙ۠ۖۘۢۜۥۘ۟ۖۖۙ۬۟ۙۘۥۘۥۦۧ";
                                                                while (true) {
                                                                    switch (str31.hashCode() ^ (-1371761329)) {
                                                                        case -989398997:
                                                                            str30 = "۠ۖۜۤۨۦۤ۟۫ۚۙۦۘۢۘ۬۬ۢۖۘۦۖ۬ۥۧ۟ۗ۫۟ۚ۠ۗۚۡۡۘۖۦۘۘۨۘ۫ۨۥۛ";
                                                                            break;
                                                                        case 49325759:
                                                                            str30 = "۬۬ۥۗۧۦۘۦۡۧۘۚۘۛ۫ۛۘۘۘ۬ۡۘۨۡۦۜ۟ۘۧۗۘۦ۟ۚۘۜۤۜۜ۬ۨۘۚۜۙۜۘ";
                                                                            break;
                                                                        case 313751501:
                                                                            str31 = "ۘۖ۬ۖ۟ۦۘۜ۫ۗ۟ۨۨۘۡ۟ۗۨۨۦۘ۟۬۠ۛۥۚۜۘۜۚۛۚۘۤۘۘۜۛۦ۫ۤۨۤ۠ۨۘۖۢۨۚۛۘ";
                                                                        case 2134448931:
                                                                            str31 = i3 < length2 ? "ۢۦ۠ۜۤۦۘۨۥۡۘۚۤ۟ۜۦۜۥۗۜۘۛۦۘۘۖۧ۫ۛ۫۬ۗۡۜۘۙ۬ۘۘ۠ۡۘۤۘۥۘۨ۠ۛۘۦۨۘۤۥ۠ۤۨۚۚۥۖۘ" : "ۥ۟ۚ۠ۢۘۘۥۘۥۜۦۘۗۤۖۘۘۧۚ۠ۢۤۡۨۜ۫ۡۙۥۚۗۦۛۙۗۦ۫ۛۖۤ۬۫ۜۜۦۥۘۚۜۦۡۖۨۘ";
                                                                    }
                                                                }
                                                                break;
                                                            case 1499790167:
                                                                String str32 = "ۧۚۙۤۥۜۡۜۜۛۖ۟ۤۘۧۘۗۨ۫ۙۗۙۖۚۖۘۙۤۨۘۡ۬ۚۤۖۜۘ۠ۗۧ";
                                                                while (true) {
                                                                    switch (str32.hashCode() ^ (-787799900)) {
                                                                        case -2026471110:
                                                                            String str33 = "ۤۛۗۚ۫ۙۥۡۡۡۨۘۖۖۛ۫ۨۜۗۦۖۘۙۛۥ۠۟ۤ۟ۥ۠ۢ۟ۚ۠ۖۥ";
                                                                            while (true) {
                                                                                switch (str33.hashCode() ^ 827370576) {
                                                                                    case -514424333:
                                                                                        String str34 = "ۨۘ۟ۚۜ۠ۖۦۛۢ۫ۛ۟ۦۨۘۥۧۙ۫ۙۡۢ۟ۖۘۡ۟ۦۚۚۧۤۡۛۤۧۛۦۘۦۢ۠ۡۘ";
                                                                                        while (true) {
                                                                                            switch (str34.hashCode() ^ (-1326636625)) {
                                                                                                case 474154375:
                                                                                                    String str35 = "ۛۖۦ۫ۘۜ۬۟ۡ۠ۧۡ۬ۤۨۘ۠ۙۚۘۜۜۘ۬۟ۖ۬ۛۘۘ۟ۡۧۘۧۤۨۘۖۖۘ";
                                                                                                    while (true) {
                                                                                                        switch (str35.hashCode() ^ 1863105973) {
                                                                                                            case -1473221048:
                                                                                                                str35 = "ۖ۬ۛۗۖۗۥۘ۫ۗۥۥۗۘ۫۬ۚۥۜ۟ۚ۬ۢۤ۟۠ۖۘۢ۟ۗۡۗۛۤۖۘۘۜ۟ۜۘ۬ۤۤۧ۟ۚۚۥۜ";
                                                                                                            case -1370789038:
                                                                                                                str34 = "۟ۘۚ۠ۥۗۛۦۙۗ۫۠ۜ۠ۗۡۘۢۨۙۚۤۛۘۙۚۥۤۡۧۘ۬ۡۛ۫ۤ۟ۜۚ۟۟۠ۡۤۖۤ۬ۦۤ";
                                                                                                                break;
                                                                                                            case -400079654:
                                                                                                                str35 = parameterTypes2.length <= 8 ? "ۥۥۡ۟ۤۨۨ۬ۧۡۖۜۘۨ۟ۡۗ۟۠ۦ۟ۜۘۦۡۡۘۢۨۢۤۚۦۘ" : "۠ۖۖۘۛ۟ۥۨ۟۠ۦۡۨۘۛۛۥۥۢۡۘۢۧۤۡ۟ۡۘۧۥۨۗۢۖۚۙۡ۬ۜۜۘ";
                                                                                                            case 630293939:
                                                                                                                str34 = "ۨۙۜۘۚۚۨۘۦۨۖۢ۟ۧۤۙۤۜۤۛ۫ۨۘۨۡۢ۫ۥۘۙۥۗۚۜ۬ۧۜۨۘ۟ۨۛ۫ۘ۫ۧۡ۟ۦۙۜ";
                                                                                                                break;
                                                                                                        }
                                                                                                    }
                                                                                                    break;
                                                                                                case 1574793058:
                                                                                                    str34 = "ۚۗۤۤۥۖۥۡۚۙۙۦۘ۫ۖ۫ۥۖۦۘۛۥ۫ۦۢۨۥۜ۠ۘۨ۫ۤۛۘۧۗۚۚۥ۬ۧ۫";
                                                                                                    break;
                                                                                                case 1974925189:
                                                                                                    break;
                                                                                                case 2147407185:
                                                                                                    arrayList.add(method2);
                                                                                                    continue;
                                                                                                    continue;
                                                                                                    continue;
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                    case 1335594046:
                                                                                        break;
                                                                                    case 1543928516:
                                                                                        str33 = "ۛ۫ۥۜۖ۟ۘۗۗ۫ۛۖۦۙ۫ۦۗۖ۬ۜۦۡۚۜۘ۟ۦ۬ۦۗۙ";
                                                                                        break;
                                                                                    case 1612018161:
                                                                                        String str36 = "ۜ۫ۨۗۚۘۧۜۧۡۤۗ۬ۧ۬ۖۧۗ۟ۨۛۦ۫ۘۙۥ۟ۗ۟۠ۥ۠۟ۢۙۨۘ۟۠ۖۜۧۘ";
                                                                                        while (true) {
                                                                                            switch (str36.hashCode() ^ 1169004179) {
                                                                                                case -698222168:
                                                                                                    str36 = "ۨ۠ۙۜۨۡۘۛ۬ۡۧۜۡۘۚۚ۠۠ۥۡۡۗۥۛۦۦۘۥۚۘۗۖۜۘ";
                                                                                                case 252548405:
                                                                                                    str33 = "ۙۥۨۘۥۤۧۜ۠ۘۘ۟ۜۘ۠ۧۜۘۘ۠ۡ۠۟ۜۨۧۖۘ۟ۘۢۛۜۘۘۡۨۘۘۖۨۚۦ۫ۡۘۙ۫ۜۘ";
                                                                                                    break;
                                                                                                case 576112858:
                                                                                                    str33 = "ۚۨۜۘۚۨۡۘۙۛۥۘۘۘۖۗۨ۟ۖۘۘۛۗۢۧۦ۟ۖۗۙ۬ۤ۠ۢۤۘۜۧ۫";
                                                                                                    break;
                                                                                                case 1730468505:
                                                                                                    str36 = parameterTypes2.length >= 6 ? "۟۬۫ۤۘۡ۟ۜۨۚۡۨۘۦ۟ۖۘۗۦۙۚۚۖۘۤۗۤۦۨۨۙۙۖۥۤۦۦۡۢۨۛ۫ۛۥۦ۬ۡۢ۬ۢۧۜۤۚ۟۠ۥۘ" : "ۨۧۨۘۘ۬ۛۚۗۧۥۛ۟ۙۨۘۖۙۨۖۨۙۦ۟ۦۘۜۜۢۗۛۛۖۧۨ۫ۨۦۘۙۙۛ";
                                                                                            }
                                                                                        }
                                                                                        break;
                                                                                }
                                                                            }
                                                                            break;
                                                                        case -1738878314:
                                                                            String str37 = "ۜۘ۠ۜ۬ۘۙۚۨۘۡۤۜۜۜۗ۟۬ۙۢ۠ۨۘۧۙۦۘۤ۟ۜۧ۫ۢ";
                                                                            while (true) {
                                                                                switch (str37.hashCode() ^ 889440037) {
                                                                                    case -342237043:
                                                                                        str37 = Intent.class.equals(parameterTypes2[i3]) ? "ۥۖۚ۟ۢۦۤ۠ۘۗۙۜۢۜۘ۟ۖۦۤۖۤ۠ۡۖۘۡۥۗۦۡۤۨۨۡ۠ۚۗۧ۟۟ۛۧۛۛۨۘ۬ۦۚۧۨۡۘۚۘۦ" : "ۗۥۜۘۦۚۙ۫۬ۥۘ۬ۖۗ۫ۚۜۙۥۛ۫ۥۘۗۨۖۦۧۨۖ۠ۘ";
                                                                                    case 1395103883:
                                                                                        str32 = "ۥۗۖۘۛۡۖ۫۠۫ۥۚۤۦۘۥۘ۫ۦۘۜۡۧۦۡۜۘۤۖۡۨ۟ۦۘۜۗۦۥ۫ۤ۠ۡۤۛۜۗ";
                                                                                        break;
                                                                                    case 1586345040:
                                                                                        str37 = "ۗۖۛۥۧ۬ۖ۟ۥۘۥ۬ۧۖ۠۟ۢۛۨۙۧۢ۟ۖۡۘ۠۬ۖۘۗۥۨۚۦۘۘ۠ۨۖۘۥۤۥۘۚۧۦۘۦۗۨۘ۠ۖۖۨۤۤۗۗۘۘ";
                                                                                    case 1660514095:
                                                                                        str32 = "ۘۨۙۢۧۜۦ۬ۙ۟ۡۘۘۜ۠ۧۤۜۤۖ۫ۛۗ۠ۚۤۤۗۛۢۡۘۧ۠ۡ۬ۛۚ";
                                                                                        break;
                                                                                }
                                                                            }
                                                                            break;
                                                                        case -116542164:
                                                                            break;
                                                                        case 1289692217:
                                                                            str32 = "ۙۦۜۧۚ۟ۖۡۡۘ۟ۥۧۘ۠۫ۘۤۙۖۘۚ۬ۖۘۧۜۗۛۦۘۘ۠ۢ۫";
                                                                    }
                                                                }
                                                                break;
                                                            case 1641192093:
                                                                break;
                                                        }
                                                    }
                                                    i3++;
                                                }
                                                break;
                                            case -887422438:
                                                str29 = "ۖ۬۟ۜۗۢۢ۫ۦۦۗۡۘۨۤۛۗۢۥ۠ۤۜۘ۫ۖۖۗۨ۫ۛۨ۬ۢۖۘۡۦۘۘۙۥۖۘۡۗۢۦ۟ۘۨۚۦۘ۬۠ۜۖۘ";
                                                break;
                                            case -509139820:
                                                String str38 = "ۥۡۜۘ۫۠ۗۡۢ۬۟ۨۜۘۗۤۛۢۜۤۗۡۦۘۥۖۧۘۚۨ۠۠ۙ۟";
                                                while (true) {
                                                    switch (str38.hashCode() ^ 584924420) {
                                                        case -1441612507:
                                                            str29 = "ۜ۫ۨ۠ۨۘۥۗۦۘۖۡۥۘۜۘ۬ۡۗ۫۬ۘۢۚۡۘۘ۬۟ۖۛ۫ۖۘ";
                                                            break;
                                                        case -1090805663:
                                                            str29 = "ۖۚۚۨ۟ۖۨۛ۬ۗ۠ۘۗۧۦۘۨۧۘۘ۠ۘۖۘۦۥۡۜۢۘۜۗۡۢ۬ۛ۬ۚۧۧۤۖۙۥۦۘ۟ۨۗۜۢ۫ۘ۠۠ۗۡۜ";
                                                            break;
                                                        case 285962147:
                                                            str38 = "ۡۤۗۘۖۘۗ۬۬ۡۗۨۘ۬ۖۗۘ۟ۖ۬ۙۡۜۖۨۘۜ۬۟ۜ۠ۙۤ۫ۗۘۥۥۘ";
                                                        case 2144378640:
                                                            str38 = !"execStartActivity".equals(method2.getName()) ? "۟ۗۥۨۜۧۘۗۘۡۥ۟ۛۨۖۛ۠ۡۥۘۢۖ۠۠۫ۜۘ۟۫ۢۦ۫ۡ۠ۘۘۘۛۛۨۘۜۡۘۘۨ۟ۖ" : "ۤۢۚ۠ۖۦۚ۠ۜ۠ۚۤۙ۬۠ۚۡۖۘ۟ۥۚۛۢۦۡۜۛۨۡۗۤۧۤۢۨۜۥۘۖ۬ۨۘۘۚۛ۠ۢۗ";
                                                    }
                                                }
                                                break;
                                            case 571885140:
                                                break;
                                        }
                                    }
                                    i2++;
                                    break;
                            }
                        }
                    }
                }

                private void logIntentDetails(String str, Intent intent) {
                    String simpleName;
                    try {
                        StringBuilder sb = new StringBuilder(str);
                        sb.append("\n- component: ");
                        sb.append(intent.getComponent());
                        sb.append("\n- action: ");
                        sb.append(intent.getAction());
                        sb.append("\n- data: ");
                        sb.append(intent.getData());
                        sb.append("\n- type: ");
                        sb.append(intent.getType());
                        sb.append("\n- pkg: ");
                        sb.append(intent.getPackage());
                        sb.append("\n- flags: 0x");
                        sb.append(Integer.toHexString(intent.getFlags()));
                        Set<String> categories = intent.getCategories();
                        String str2 = "ۚۧۜۙ۠ۜۨۙ۫۫۠ۛۤۡۨۖۧ۬ۤۢۘۢۚ۫ۥۧ۠ۗۘۥۘۗ۟ۥۘۡ۟ۢ";
                        while (true) {
                            switch (str2.hashCode() ^ (-123089625)) {
                                case -455060685:
                                    break;
                                case 34970645:
                                    String str3 = "ۤ۬ۛۥۤ۠ۛۖۘۘۜۧۡۘۙۖۢۥۙ۫۟ۤۜۗۛۢۧۤۜۙۖۢ";
                                    while (true) {
                                        switch (str3.hashCode() ^ 1490777338) {
                                            case -2085732097:
                                                str3 = "ۧۗۨ۬ۢۖۘۢۨۦۧ۠ۤۤۢۘ۬۠ۨۦۖۨۥۢۚۧۤۧ۫ۢۨۘۥۦۙۗۚ۠";
                                                continue;
                                            case -1380094426:
                                                sb.append("\n- categories: ");
                                                sb.append(categories);
                                                break;
                                            case -821346096:
                                                String str4 = "ۛۨۢۢۤۙۚ۟ۡۘۙۗۖۢۨۨۧۖۡۘۨۚۨۥۧۨۥ۟ۛۛ۠ۘۤۚ۟ۧۛۤ";
                                                while (true) {
                                                    switch (str4.hashCode() ^ 476444201) {
                                                        case -370390766:
                                                            str4 = "ۛۜۥۗ۟ۤۢ۫ۦۗۘۧۘۚۨ۬ۦۦۚۙۧ۠۠ۖۥۘۨۧ۬ۖۧۨۦۜۥۘۥۖۗۘۙۖۛ۫۠";
                                                            break;
                                                        case 55584538:
                                                            str3 = "۠ۗ۫ۚۖۦ۬ۥۗ۠ۥۛۥۢۤۦۜۤۘ۬ۦۘۖۡۡۘۦۦۚۚۜۧۘ۫ۛۧ۫۟ۨۘۛۘۘۚ۬ۘ";
                                                            continue;
                                                        case 985775407:
                                                            str3 = "ۚۙ۬ۦۦۡۘ۠ۢ۬۫۠۠ۛ۬۠ۜۙۛ۫ۧۘۘۖ۠ۙۢ۬ۙۨۦۡۘ";
                                                            continue;
                                                            continue;
                                                        case 1433178046:
                                                            if (!categories.isEmpty()) {
                                                                str4 = "ۥۨۜۘۨ۠۠ۢۘۖۘۦ۠ۤۢۘۚ۠ۛۘۘۙ۫ۖۤۙۢۚۢۢۚۨۤ";
                                                                break;
                                                            } else {
                                                                str4 = "ۚۤۨۘ۟ۥۙۢ۠۫۬ۖۖۘۗ۫ۦۘۦۢۙۘ۬ۨۘۢۖۦ۬ۧۖۘۦۡۜۘۚۦۨۘۚۦ۬ۘۙۜۢۘۧۦۦۡۘۙ";
                                                                break;
                                                            }
                                                    }
                                                }
                                                break;
                                            case 97028748:
                                                break;
                                            default:
                                                continue;
                                        }
                                    }
                                    break;
                                case 1216568533:
                                    String str5 = "۫ۜۚۨۘۡۘۛۛۡۘۤۧۢۧۗ۠ۨۙۥ۠۬ۥۥۡۘۥۤۚ۫۟۬ۨۧۦۘۚۧۛۢۖۡۗۤۥۘۙۗۚۥ۟ۥۥۗ۠ۜۘ";
                                    while (true) {
                                        switch (str5.hashCode() ^ 1806379380) {
                                            case -898606787:
                                                str2 = "ۡۛۜۢۙۧۧۘ۬ۖ۠ۘۘۘ۫۠ۖۢ۫ۘۢۖۘۖۥۧۘ۫ۥۘۜۛۨ۫ۛۦۘ۫ۥۤۗ۠ۨۜۨۡ۬۬۬ۛ۫ۚ";
                                                continue;
                                                continue;
                                            case 195791709:
                                                str2 = "ۥ۬ۦۥۨۛۘۚۨۛۚۗۢۡ۟ۙۖ۠ۧۥۡۜۧۘۛۦۚۚۙۤۤۡۨۘۜۙۦۜۜۖۘۜۙۡ";
                                                continue;
                                            case 699026107:
                                                if (categories == null) {
                                                    str5 = "ۘۘۧۘۛۙۚۡۗۘۚۤۡۜۚۛۙۥ۫ۧۖۤۦۗ۟ۡۤۘۨۜۤۜ۫ۡۘ۠۠ۥ";
                                                    break;
                                                } else {
                                                    str5 = "۟ۘۛۛ۟ۡ۬ۖۖۗۜ۟ۢۥۡۘ۬ۘۘۘ۫۠ۡۘۡۨۧۙۛ۬ۦ۟ۘۘۢ۠ۥ۟ۚ۠۫۫ۖۖۚ۫۫ۖۥۦۨۖۘۥۚ۠ۜۢۗ";
                                                    break;
                                                }
                                            case 1730652611:
                                                str5 = "ۜۘۙۡ۫ۧۖۗۙ۠ۨۦۤۡۡۛۙۦۘۧ۫ۘۘۥۗۙۙ۫ۡۙۡۥ۠ۦۘۘۨۘۨۘ";
                                                break;
                                        }
                                    }
                                    break;
                                case 1346329275:
                                    str2 = "ۧۖۛۨ۬ۥۘۖۥ۟ۨۜۦۘۛۗۢۡۙ۠ۛۡۤۥۥۙۚ۫ۜۘۙۙۘۘۨۜۙۨ۠ۘۘۤۡۢ۠ۜۧۘ";
                                    continue;
                            }
                        }
                        k2.logToFloatingWindow(sb.toString(), "debug");
                        Bundle extras = intent.getExtras();
                        String str6 = "ۗۧۗۢ۬ۨۘۧۚ۟ۗۥۤۗۖۜۨ۠ۡۦ۠ۗۦۘۦۗۖ۬ۨۛۡۖۖۘۢۥۦ۟ۛۡۖ۠ۧ";
                        while (true) {
                            switch (str6.hashCode() ^ 1103651359) {
                                case -1715974258:
                                    String str7 = "ۢۤۖۘۨۚۥۘ۬۠ۦۨۨۘۜۤۢۡۡۧۘ۠ۙۖۘ۟۠ۗۘ۫ۖۘۖۘۖۘ۬۠ۤ۬ۗۨۘۢۛ۟۫ۤۖۦۤۖۧۖۖۘۥۥۜۙ۫ۤ";
                                    while (true) {
                                        switch (str7.hashCode() ^ 1204274632) {
                                            case -1242037219:
                                                String str8 = "ۨۦۗۡۡ۠ۜۧ۫ۚۨۛۙۨۡ۠۬ۥۘ۫ۨۛۘۗۙۥۡ۟ۗۚۘۘ۬ۛۚ۬ۡۛۦ۬ۛۛۗۥۥۚۤۚۛ۬";
                                                while (true) {
                                                    switch (str8.hashCode() ^ (-1026540831)) {
                                                        case 279268121:
                                                            if (!extras.isEmpty()) {
                                                                str8 = "ۧۢ۟ۗۧۜۘ۠۠۟ۜ۟ۘۙۛۨۛ۟ۥۙۢۧۡ۠ۢۖۦ۠ۡ۬ۤ";
                                                                break;
                                                            } else {
                                                                str8 = "ۗ۟ۢۦۜۚ۠ۜۨ۬ۥۜ۟ۥۘۗۡۧۧۚۛۤ۟ۦۘ۠ۦۜ۠ۘ۠۫ۡ۠ۛۜۙ";
                                                                break;
                                                            }
                                                        case 615661313:
                                                            str7 = "۬۫ۤۗ۬ۨۘۤ۠ۥ۟ۡ۬ۖ۫ۛۜۜۦۘ۠ۡۚۧۧۨ۟ۡۘۧۨۡۘۧ۠۬۬ۦۧۘ۫ۦۜۚۧۗ";
                                                            continue;
                                                        case 776041800:
                                                            str7 = "ۜۘۦۛ۟ۨۖ۟ۤۛۤۡۧۜۘۧۗۨۘۘۚۨۘۧ۠ۥۢۨۧۧۧۢۜۖۛۘۘۛ۠ۥ۬ۙۗۡۘۤۢۥۘۚۦۘۘۨ۟ۚۢۚۚ";
                                                            continue;
                                                        case 1152058670:
                                                            str8 = "ۜ۠ۛۡۛۖۜۜۘۘۦۨ۟۠۟ۢۜۘۤۨ۬۫۫ۘ۫ۤۡۧۖۧۘۤۥۜۘۡۢۡۘۘۨۛۖۙۨ";
                                                            break;
                                                    }
                                                }
                                                break;
                                            case -168001731:
                                                str7 = "۬۟ۛۦۖۛۚۡۘۙ۟ۨۘۥ۠ۘ۟ۚۙۡۜۡۙۨ۟ۢۖۖۘ۠ۧۙۢۦۦۜۢۘۘۤۘۦۘۥۧۡۢۦۙۥ۟ۡۘ";
                                                break;
                                            case 337454727:
                                                return;
                                            case 1185527104:
                                                StringBuilder sb2 = new StringBuilder("【跳转参数】共 " + extras.size() + " 项：");
                                                Iterator<String> it = extras.keySet().iterator();
                                                while (true) {
                                                    String str9 = "۬ۗۘۘۖۦۙۖ۬ۖۘۤۥۦۘ۟ۦ۬ۨۧۨۘۤۙۡۘ۠ۦۥۙۤۗۖۚۨۚۧۥۘۗۨۘۧۙۦۘۡۨۗ";
                                                    while (true) {
                                                        switch (str9.hashCode() ^ (-1969514558)) {
                                                            case -1315572387:
                                                                str9 = "ۨۡۛۛۥۥ۫ۚۜۘۖ۫۫ۖۥۗۦۚۖۚۚۢ۬ۘۨۘ۬ۘۜۨۦۘۤۘۚۢۤۜۘ۟۠ۡ۬ۨۛۘۡۨۦۢۖۘ";
                                                                break;
                                                            case -321271360:
                                                                k2.logToFloatingWindow(sb2.toString(), "debug");
                                                                return;
                                                            case 364543284:
                                                                String next = it.next();
                                                                try {
                                                                    Object obj = extras.get(next);
                                                                    String str10 = "ۙ۫ۘۙۧۢ۠ۖۧۡۧۛۧۥۦۘۖۨۗۦۛۛۥ۫ۜ۠۬ۧ۠۠ۘۘۜۗۥۡۙۙ۫ۙۨۙۥ۠ۚۚۚۤ۠ۦۥۦۘۧۗۘۘ";
                                                                    while (true) {
                                                                        switch (str10.hashCode() ^ (-301788435)) {
                                                                            case -348067661:
                                                                                String str11 = "ۗۨۛۗ۬ۗۨۦۗ۟ۗ۬ۨۗۢۚ۬۬ۙۘۥۤۤۦۚۖۚ۫ۘۨۦۖۨۘۗ";
                                                                                while (true) {
                                                                                    switch (str11.hashCode() ^ 1238205151) {
                                                                                        case -2055020129:
                                                                                            str11 = obj != null ? "ۙۚۤ۫۫ۙ۟ۛۡۜۦۘۘۢ۟ۛۤۨۘۘۢۘۨ۠ۖۛۚۡۥۘۨ۟ۚۢ۠ۡۘ۠ۙۘۥۦۧۛۨۖۘ۫۟ۛۙۚ۟ۤ۬۟ۧ" : "ۖۖۘۛۗۧۛۖۛ۫ۘ۫ۧ۫ۜۘۦۦۙۘۛۗۜۘۢۧۙۧۢۖۨۘۦۦۗۡ۫۟ۤۤۡۘ۬ۙۤۥۖۖۧۜۧۢۦۗۧ۬ۥ";
                                                                                        case -1347422074:
                                                                                            str10 = "ۗۛۖۘۛ۠ۥۛ۫ۚۨۢۥۘۜۚۚۜۛۥۨۨۥۛۛۨ۫ۥ۠ۛ۬ۧۙۙ۬ۦۧۘۖۘ۫۫ۙۨۘۨۤۘۦۖۧۘ";
                                                                                            break;
                                                                                        case -567911422:
                                                                                            str10 = "ۙۧۥۘۜۖ۠ۧۨۦۙ۟ۚ۫ۜۥۘ۬۟۫ۙۧ۠ۛ۟ۡۘ۠ۥۦۘۙۖۛ۫ۡۖۘۗۧۘۧ۟ۤۦۘۘ";
                                                                                            break;
                                                                                        case 150355584:
                                                                                            str11 = "ۙۛۨۘۥۛۖۨ۬ۥۘۤۤۢۗ۟ۢ۟۫ۖۡۤۨۦۙ۠ۗۡۥۘۚۚۤۙۥۤۤۜۦۡۦۘ۠ۜۦۘ";
                                                                                    }
                                                                                }
                                                                                break;
                                                                            case 94353451:
                                                                                simpleName = obj.getClass().getSimpleName();
                                                                                break;
                                                                            case 885390749:
                                                                                simpleName = "null";
                                                                                break;
                                                                            case 1989584086:
                                                                                str10 = "ۗۙۜۘۧۡۡۧۗۜۘۢ۫۟ۥۖ۫ۘۛۙ۫ۜۘۗ۠ۧۖۤۘۘۡۧۨۤۘ۟ۧۧۘۘ۟ۛۘۘ۟ۗۥ";
                                                                        }
                                                                    }
                                                                    sb2.append("\n- ");
                                                                    sb2.append(next);
                                                                    sb2.append(" (");
                                                                    sb2.append(simpleName);
                                                                    sb2.append(") = ");
                                                                    sb2.append(String.valueOf(obj));
                                                                } catch (Throwable th) {
                                                                    sb2.append("\n- ");
                                                                    sb2.append(next);
                                                                    sb2.append(" = <读取失败: ");
                                                                    sb2.append(th.getClass().getSimpleName());
                                                                    sb2.append(">");
                                                                }
                                                                break;
                                                            case 1600963239:
                                                                String str12 = "ۤۦۥۨۦۘ۟ۜۢ۬ۡ۟ۡۡۥۘۨ۠ۨۘ۫ۢۡ۠ۨۜ۠ۦ۫ۖ۫ۘۦۢۘۜۥۗۚ۟۬۟ۙۜۘ";
                                                                while (true) {
                                                                    switch (str12.hashCode() ^ (-63769630)) {
                                                                        case -629162689:
                                                                            str9 = "ۖۦۜۨۢ۬ۤ۬ۨۘ۠ۤ۬۠ۚۨۘ۫۫ۨۡۤۧۡ۬ۛۗۘۧۘۘۘ۬ۙۦۘ۬ۜۨۦۢۥۘۜ۟ۘۚۘۡۚۜۨ";
                                                                            break;
                                                                        case 1018073034:
                                                                            str12 = "ۖۗۨۤۦۨۘ۫ۚۘۘۢۨۜۦۦۘ۠ۧۜۘۖ۬ۜۘۤۘۥۨۖۗۛۗ۠ۡ۬ۙۧۡۜ";
                                                                        case 1488572227:
                                                                            str9 = "۬ۤ۟ۦۤۜۘۢۘۥۚۦ۬۫ۘۙۜ۫ۚ۟ۛۘۘۧ۠ۦۖۛۦۢۨۡۘۛۡۘۚۜۜۘۖ۬۟ۗۛۙۗۤ۬ۗۙۜۘ";
                                                                            break;
                                                                        case 1667881166:
                                                                            str12 = it.hasNext() ? "ۧۧۗۗۚۤ۠ۜۗۢۢۡۢۗۢ۬ۥۦۛۛۗۜۚۡۗۜۜۘ۬ۛۜۘ" : "۟۠ۦۘۢۙۜۘۘۜۥۙۤ۠ۜۜۥۘۜۥۘ۠ۜۥۘۤ۬ۦۦۥۘۘۢۨۤۙ۟ۘۘۧۥۘۜۛ۫ۧۨۨ";
                                                                    }
                                                                }
                                                                break;
                                                        }
                                                    }
                                                }
                                                break;
                                        }
                                    }
                                    break;
                                case -856668387:
                                    return;
                                case -515379923:
                                    String str13 = "ۜۚۜۘۥۢ۫ۧۙۗۘۘۜۛ۫ۢ۟ۦۘۖۖۛۘۘۢۘۦۢۦ۬۟";
                                    while (true) {
                                        switch (str13.hashCode() ^ 2039242729) {
                                            case -473385441:
                                                if (extras == null) {
                                                    str13 = "ۨۜۨۘ۠ۧۧۦۢۖۘۧۚۘۘۨۜۜۘ۬ۚ۬ۘ۠ۘۘۨۨۙۨۦۗۡ۫۠۟۬ۧۚۥ۟۬ۜۘۚۚۘۥۦ۟ۤۗۖۘۗۙۛۦ۟";
                                                    break;
                                                } else {
                                                    str13 = "۟ۛۦۘۨۨ۠۬ۨۢۤۧ۬۬۠ۖۘ۟ۘۚۧۥۖۘۧۗۜۗ۬ۨۧۖۧ";
                                                    break;
                                                }
                                            case 57330281:
                                                str6 = "۬ۚۘۘ۬ۙۘۘۛۙۨۘۜۗۜۘۚۘۗۦۦۤۗۖۘۗۜۨ۫ۖۥۘ۬ۗ۟ۥۦۥۘ۟ۥۜۘۡۗۙۤۗۜ";
                                                continue;
                                            case 888070751:
                                                str6 = "۫۟ۜۘۨۧۥۘۚۘۜۘۡۖۛۘۧۧۥۧ۠۫ۤۜ۫ۜ۬۠۟ۗ۫ۧۖۖۦۘۨۥۖۧۚۦۙۢۦۢۙۦۘۚ۠ۡۘۙۥۦۘۗ۬۠";
                                                continue;
                                            case 1435447858:
                                                str13 = "ۛ۫ۨ۠ۦۖۗۙ۠۠ۘۡۡۘۥ۬ۤۚۥۘ۠ۜۖۧۦۜۘۘۜۧۘ";
                                                break;
                                        }
                                    }
                                    break;
                                case 1406135810:
                                    str6 = "ۖۘ۟ۧۢۦۗۗۦۜۜ۬ۖۤۦۘۗۧۘۘۘۡۤۢ۬ۘۖ۟ۡۘۧ۫ۘ۬ۢۥۗۖۘ۠ۗۤۢۛۦۘ";
                                    break;
                            }
                        }
                    } catch (Throwable th2) {
                        k2.logToFloatingWindow("【打印 Intent 详情异常】" + th2.getMessage(), "error");
                    }
                }

                /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
                    jadx.core.utils.exceptions.JadxRuntimeException: Failed to find switch 'out' block (already processed)
                    	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.calcSwitchOut(SwitchRegionMaker.java:200)
                    	at jadx.core.dex.visitors.regions.maker.SwitchRegionMaker.process(SwitchRegionMaker.java:61)
                    	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:112)
                    	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
                    	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeEndlessLoop(LoopRegionMaker.java:281)
                    	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:64)
                    	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
                    	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
                    	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.processHandlersOutBlocks(ExcHandlersRegionMaker.java:113)
                    	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.collectHandlerRegions(ExcHandlersRegionMaker.java:80)
                    	at jadx.core.dex.visitors.regions.maker.ExcHandlersRegionMaker.process(ExcHandlersRegionMaker.java:38)
                    	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:27)
                    */
                private java.lang.String safeResolveTargetClassName(android.content.Context r8, android.content.Intent r9) {
                    /*
                        Method dump skipped, instructions count: 1468
                        To view this dump add '--comments-level debug' option
                    */
                    throw new UnsupportedOperationException("Method not decompiled: gTBLD.dev.XSSTG.free.HookManager.AnonymousClass2.safeResolveTargetClassName(android.content.Context, android.content.Intent):java.lang.String");
                }

                @Override // android.app.Instrumentation
                public void callActivityOnDestroy(Activity activity) {
                    String strDecrypt;
                    String str = "ۥۢ۠ۘۛۗۡۗۤ۬ۖۥۜۗۛ۬ۧۨۙۨ۟ۧۡۙ۟ۘ۫۫";
                    while (true) {
                        switch (str.hashCode() ^ (-490154175)) {
                            case -14826453:
                                strDecrypt = "<null>";
                                break;
                            case 615672068:
                                str = "ۤۛۖۘۢۤۗۡ۟ۚۚۧۢۢۜۚۖۚۥۘۘۗۜۜۧۥ۬ۜۙۦۛۦۘ۫۬ۚ۫ۘ۫";
                                continue;
                            case 763112274:
                                String str2 = "ۚۥۦۘۛ۟ۨۧۜۡۙۡۘۙۥۡۡۢۜ۬ۦۖۘ۫ۨۚ۫ۧۧ۠ۖ۬ۗۨۧۦ";
                                while (true) {
                                    switch (str2.hashCode() ^ 797236915) {
                                        case -2041005096:
                                            if (activity == null) {
                                                str2 = "ۨۜۜۤ۟ۧۙۤۨ۠ۛ۫ۧ۫۫ۚۜ۬ۤۛۢۗۗ۬ۘۥۖۘۧۡ۠";
                                                break;
                                            } else {
                                                str2 = "ۧۨ۟ۜۨ۬ۙۗ۟ۙۛ۬ۗۥۖۘۨۚۤۤۜۤ۠ۥۘۘۧۧۡۘۘ۟ۧ";
                                                break;
                                            }
                                        case -552105061:
                                            str = "ۛ۟ۘۘ۬ۛۥۡ۠ۚۙ۬ۥۘ۫۫ۗ۟ۢۙۜ۬ۨۘۡۡۖ۫ۘۜ۬ۗۜۘۦۘۧۛۨ۬۫ۚۗۛۢ۟ۨ۫ۖۘ۟۠ۡۘ";
                                            continue;
                                            continue;
                                        case -195340100:
                                            str2 = "ۨۨۜۤۦۙۙۡ۬ۗۦۧۘۢۡۚ۟ۘۘ۟۟ۥۘ۫۫ۤۥ۟۠۠ۦ";
                                            break;
                                        case 931062511:
                                            str = "ۥۨۤۨۨۡۘۧ۬ۥۛۡۘۘۘۚۘۘۢۨۙۜۨۤۦۡۙ۫ۖۚۜ۠";
                                            continue;
                                    }
                                }
                                break;
                            case 1537516855:
                                strDecrypt = activity.getClass().getName();
                                break;
                        }
                    }
                    k2.logToFloatingWindow(h.e("J1MI/9ozNElAHzCbuixH\n", "wPmfGlWQ3d0=\n", new StringBuilder(), strDecrypt), "info");
                    try {
                        Utils.onActivityDestroyed(activity);
                    } catch (Throwable th) {
                        k2.logToFloatingWindow("onActivityDestroyed 调用异常：" + th.getMessage(), "warning");
                    }
                    try {
                        this.val$originalInstrumentation.callActivityOnDestroy(activity);
                    } catch (Exception e) {
                        k2.logToFloatingWindow(h.d("GKCaEVunHHyRfHW3rHtVaZlkYLmhS1lshGJ2j+/qmK4YpLzM7w==\n", "8BAZ9s8PPB8=\n", new StringBuilder(), e), "error");
                    }
                }

                /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
                @SuppressLint({"DiscouragedPrivateApi"})
                public Instrumentation.ActivityResult execStartActivity(Context context2, IBinder iBinder, IBinder iBinder2, Activity activity, Intent intent, int i, Bundle bundle) {
                    Context context3;
                    String strSafeResolveTargetClassName;
                    String str;
                    Activity activityECt8jHZ4;
                    try {
                        logIntentDetails("【即将启动窗口】Intent 基本信息", intent);
                        String str2 = "۫ۥۡۘۥۥۥۘۦ۬۫ۦۘۡۜۢ۫ۙۖۤۥۦۧۘ۫۬ۙۗۗۜۗۤۙۜ۟۟ۥۤۢۤ۫ۖۧۛۘ۟ۙۤۚۖۥ";
                        while (true) {
                            switch (str2.hashCode() ^ (-339375176)) {
                                case -1773001976:
                                    context3 = context2;
                                    break;
                                case -1649386119:
                                    String str3 = "ۜۛ۫۠ۙۨۙۗ۠ۙۨۖۘۧۘۧۘۖۡۧۤۡۡۗۖۘ۟ۥۨ۟ۨ۟ۛۘۙۡۘ۟۬۟۠ۧۗۨۘ۠ۗۛۤۨ۠۠ۘۦۖۦ۠";
                                    while (true) {
                                        switch (str3.hashCode() ^ 811992764) {
                                            case -1910879916:
                                                if (context2 == null) {
                                                    str3 = "ۡۘۧۘ۬۠۫ۡۧۜۤۛۥۘ۟ۤۖۘۛۜ۟ۥۢۚۢۧۜۦۙۙۘۜۘ";
                                                    break;
                                                } else {
                                                    str3 = "ۛ۟ۧۢۛۙۤۧ۬ۨۘۡۜ۠ۘۘۖۖۘۡۚ۫ۚۛ۠ۙ۟ۙۤۧ۬ۥۧۙۙۦ۬ۦۨۙۙۥۢ۠ۚۖۘۖۜۖۘ";
                                                    break;
                                                }
                                            case 557722322:
                                                str2 = "ۚۦۨۘۖۛۦۛۙۦۘ۠ۥۦۤۗۨۘۖۙۤ۟ۘۨۨۦ۫ۜ۠ۜۗۘ";
                                                continue;
                                            case 1117318497:
                                                str3 = "ۤۧۚۗ۟ۜۘ۠ۜۖۛۛۜۘۨۡۥۘۗۚۨ۠ۗ۬ۡۥۥۘۖ۬ۚۙۗ۠ۗۛۗ۬ۦۦۧۨۖ۠ۜ۬۟۫ۗ۟ۧۜۘ";
                                                break;
                                            case 2068660179:
                                                str2 = "ۛۜۦۘۚ۟ۛۦۙۙۘۛۖۘۧۢۖۘۛۘۜۢۤۡۘ۟ۤۨ۫ۦۢۡۨۜۢۜۙۘۡۡۖۖۜۗۦۘ";
                                                continue;
                                                continue;
                                        }
                                    }
                                    break;
                                case -281898671:
                                    context3 = this.val$context;
                                    break;
                                case 117298780:
                                    str2 = "ۗ۬ۨۛۛۦۘۡ۠ۖۙۘۖۦۘۘۘۖۜۖۘۛۢۦۘۚۡۤۧ۟ۥۘۢ۟ۥۥ۬۬ۦ۬ۧۙ۟ۦۙۘۛ";
                                    continue;
                                default:
                                    continue;
                            }
                        }
                        strSafeResolveTargetClassName = safeResolveTargetClassName(context3, intent);
                        str = "ۦۦۘۖۛۘۘۧۘۘۘۜۦۥ۫ۜۡۘۗۗۥ۬۬ۨۘۛ۟ۨۘۤ۠ۗۙ۠ۢ";
                    } catch (Throwable th) {
                        k2.logToFloatingWindow("execStartActivity 外层异常：" + th.getMessage(), "error");
                        throw new RuntimeException("execStartActivity 异常", th);
                    }
                    while (true) {
                        switch (str.hashCode() ^ (-691469731)) {
                            case -1093512983:
                                String str4 = "۬ۗۡۦۚ۠ۙۥۦۜ۟ۜ۬ۚۗۢ۠ۘۘۖۖۙۙۖ۠ۛۨۚۜۘ۬";
                                while (true) {
                                    switch (str4.hashCode() ^ (-1117092534)) {
                                        case -2102244290:
                                            str = "ۥۙۤۙۤۚۨۨۨۚۘۖۘۡۚۘ۟ۘۨۘۥۢ۠ۨۛۘۘۤۥۖ۫۫ۤۢۛۨۘۛۢۘۡۗۜ۟ۤۤۤۚۨۘۦۧۙۜ۟ۚۥۧۡ";
                                            continue;
                                            continue;
                                        case 59132565:
                                            if (strSafeResolveTargetClassName != null) {
                                                str4 = "ۜۦ۫ۖ۫ۥ۠۬ۦ۫ۚۥۘۧۧۨۘۢ۟ۨۘۜۚۢۡۧۖۘۤۚۧۥۙ۫";
                                                break;
                                            } else {
                                                str4 = "ۜ۟ۥۤۤۧۦۙۙۖۖۘۚۗۘۘۢۦۢۚ۠ۦۘۥۦۡ۟ۧۥ۠ۤۜ۫ۨۘ۟ۨۖ۠ۢۡۥ۠ۖۘۖۜۜۨۙ۬";
                                                break;
                                            }
                                        case 939719644:
                                            str4 = "۟۬ۥۘۘ۠ۘۘۤ۠۬ۗۧۦۤۘۙۘ۟ۤۚ۬ۛۤۦۤۜۨۘ۬ۧۜ";
                                            break;
                                        case 1586094498:
                                            str = "ۧۗۨۦۖۖۧۜ۬ۤۢ۠ۗۨۖۥۘۨۘ۠ۥ۟ۛۢۢۡۦۛ۠ۜۨۙۦ۠ۜ۟۬ۛ۬ۗۙۦۥۘۧۡۘۘۨۙۡ";
                                            continue;
                                    }
                                }
                                break;
                            case 1109839066:
                                String str5 = "ۢۦ۟ۨۖۖۦۖۦۘۜۙۤ۟۟ۡۧۡ۫ۥۦۘ۠۫ۙۧۛۨۘۢ۫ۖۥۦۦۧۙۦۚۦ۫ۢۨۘۨۧۛۨۤۤ";
                                while (true) {
                                    switch (str5.hashCode() ^ (-775782759)) {
                                        case -1340664059:
                                            k2.logToFloatingWindow("【隐式跳转】无法解析目标类，URI: " + intent.getData(), "warning");
                                            break;
                                        case 1073792657:
                                            k2.logToFloatingWindow("【隐式跳转】无法解析目标类，action: " + intent.getAction(), "warning");
                                            break;
                                        case 2029134145:
                                            String str6 = "۟ۦۡۖۛۤۢ۟ۜۘۧ۠ۥ۬ۧۥۘۢۧۗۖۦ۬ۥۗ۟ۡۜۖ۬۬ۦۗۘۖۘۤۜۤۗ۠ۥۘۧۧ۬";
                                            while (true) {
                                                switch (str6.hashCode() ^ (-1417934248)) {
                                                    case -483986719:
                                                        if (intent.getData() == null) {
                                                            str6 = "ۘ۫ۦۘۖۥۦۘ۬ۛۢۚ۬ۙۜۚۢۨۨ۬۟ۘۘۚۨۡۘۡ۫ۨۖۤۜۘۡۦۘۚۜۤۥۢۥۚۥۗۛۡۘ۫";
                                                            break;
                                                        } else {
                                                            str6 = "ۦۛۢ۟ۘ۬ۖۙۢۚۙۘۜۖۘۘۧۡۡۗۡۗ۬ۜۦۛۙۘۖۤۧ";
                                                            break;
                                                        }
                                                    case 573283702:
                                                        str5 = "ۗۤۦۘۛ۠ۙ۠ۤۚۚۖۤۙ۟ۦۦۜۘۡ۬ۨۘۚۥۙۙۛ۫ۛۢ";
                                                        continue;
                                                        continue;
                                                    case 645018029:
                                                        str6 = "ۛۡۧۘ۟ۚ۟ۙ۠ۖ۫ۜۢۢ۫۟ۚ۬۟۫۫ۧۧۨۥۘۙۧ۠ۘۛ۬۠ۤۦۢۢۤۨۧ۠ۡ۬۬";
                                                        break;
                                                    case 1465533194:
                                                        str5 = "ۤۢۥ۬ۜۦۘۚۥۘۘۧۤ۫ۥۡ۬ۖ۬ۡۙۥۘ۟ۢۗۚۢۧۢۦۚۤۨۧۘۚۡۥۘۛ۠ۥ۟ۚ۠";
                                                        continue;
                                                }
                                            }
                                            break;
                                        case 2130071359:
                                            str5 = "۫۟ۤۥ۠۠ۤۦۥۘ۫ۙۖۚۖۘۤۢۘۘۗ۫ۖۤ۟ۖ۟ۨۘۥۘۚۨۚۦۗۛۧ";
                                            continue;
                                        default:
                                            continue;
                                    }
                                }
                                return null;
                            case 1979734353:
                                k2.logToFloatingWindow("窗口切换到：" + strSafeResolveTargetClassName, null);
                                String str7 = "۬ۢۛۧۛۗۗ۠۟ۧ۬۠ۖۚۦۜۡۡۘۨ۫ۡۘۥۤۘۚۡ۟ۚۚ۠ۛ۠ۙۨ۠ۦۘۧ۫ۖۘۢ۠ۥ";
                                while (true) {
                                    switch (str7.hashCode() ^ (-2093549709)) {
                                        case -1848891395:
                                            boolean zIsDebug = s0.isDebug();
                                            String str8 = "ۙۖۦۘۙۤۢ۟ۖۥۗۙۡۘۧۥۧۘۥۚۙۨۘۢۢۡۛۨ۫ۙ۟ۗۦۘ";
                                            while (true) {
                                                switch (str8.hashCode() ^ (-981407188)) {
                                                    case -1580937226:
                                                        break;
                                                    case -646665748:
                                                        String str9 = "ۧۜۘ۟ۖۨۧۖۦۦۙۢۥۡۥۘۜۥۨۖۥۚۜۚۥۧۛۛۛ۫ۢۤ۫ۛۚۙۙۤۥۗۖۡۜ۠ۦۡۡۤۜۚۡۢۧۡۧۘ";
                                                        while (true) {
                                                            switch (str9.hashCode() ^ (-1803052319)) {
                                                                case 309605655:
                                                                    str9 = "ۤۧۧۖۚ۫ۤۖۜ۬۠۟ۧۡۘۙۢۘۖۤ۟۠ۡۚۧۜۗ۠ۚ";
                                                                    break;
                                                                case 764966046:
                                                                    str8 = "ۧۜۦۘۧۨۧۘ۟۠ۛۢ۬ۗۜۥۨۘۖۥۥۘۡۤۖۖۘۦۘۗ۬ۙ۫ۢ۟۟ۛۥۨۛۡۘۨۗۖۦۤۡۘ";
                                                                    continue;
                                                                    continue;
                                                                case 1690751223:
                                                                    if (!zIsDebug) {
                                                                        str9 = "ۗۚۛۜۚۤ۬۫ۜۘۘۚۗۧ۫ۙۧۚۡۘۘۤۢۚۥۡۘۚۢۛۚ۠ۡۘۡ۟ۙ۫ۚۘۘ۠ۡۜۘۡ۫۫ۜۧۤۙ۫۟۠ۛ۟ۜۜۘ";
                                                                        break;
                                                                    } else {
                                                                        str9 = "ۜۨۧۘۦۢ۫۠ۦۖ۠۬ۨۘۤ۬ۡۘ۟ۦۢ۟ۧ۟ۚ۟ۘۙۧۘۘۜۤۘۘۤۜۜۙۜۧ۬ۦۗۗۥۥۗۧۧۤۥ۠";
                                                                        break;
                                                                    }
                                                                case 1900955596:
                                                                    str8 = "۟۫ۚ۟ۜۦۘۤۦۧۜۙۙ۠ۚۨۘ۬۟ۙۤۢۧۗۙۡۨۦۧۛۨۛۧ۬۫۠۟ۨ۠۠ۖۜۨ۟ۤ۠ۧۢ";
                                                                    continue;
                                                            }
                                                        }
                                                        break;
                                                    case 1595516702:
                                                        try {
                                                            activityECt8jHZ4 = Utils.ECt8jHZ4();
                                                        } catch (Throwable th2) {
                                                            activityECt8jHZ4 = null;
                                                        }
                                                        String str10 = "ۙ۫ۡۘۛۚۚۚۗۘۘۚۜۧۙۧۡۗۚۜۘ۠ۤ۬ۡۙۜۚ۬ۥۚۥۚ";
                                                        while (true) {
                                                            switch (str10.hashCode() ^ 343612766) {
                                                                case 533358790:
                                                                    str10 = "ۘۧۨۘۙ۟۠ۥۛۧۦۡۥۢ۫ۡۘۗۛۦ۫۬ۘۘۧۗۦۧۜۗۙ۫ۥۘۙ۠ۡۘ۬ۥۜۦۜۘ۬۟ۛ";
                                                                    continue;
                                                                case 779643340:
                                                                    break;
                                                                case 943612181:
                                                                    k2.k3zLJuvX(activityECt8jHZ4, "即将要启动的窗口命中黑名单类：" + strSafeResolveTargetClassName);
                                                                    break;
                                                                case 1871923338:
                                                                    String str11 = "ۖۗۘۢۤ۠ۚۙۦۙۖۘۥ۬ۘۦ۟ۥۖۧۘۚۨۦۢۖۜۘۧۨ۬ۙۙۢۢ۫ۨۘ";
                                                                    while (true) {
                                                                        switch (str11.hashCode() ^ (-2035194855)) {
                                                                            case -1395679778:
                                                                                if (activityECt8jHZ4 == null) {
                                                                                    str11 = "۟ۙۙ۟ۡۧۘ۫ۨ۫ۘۜ۠ۨ۫ۨ۫ۘۘۤۡۘۘۘۙۦۘۖۦ۠ۨۖۧۘ";
                                                                                    break;
                                                                                } else {
                                                                                    str11 = "ۤۦۘۛ۫ۥۤۥۢۗۥۦۧ۟ۖۘ۠ۨۙۚ۫ۦ۫ۢۦۘ۬ۚۧۤۡۥۚ۬ۡ۬ۘ۟";
                                                                                    break;
                                                                                }
                                                                            case 604579744:
                                                                                str10 = "ۚۡۜۘۘۙۢۧۨۘۘۗۗۘۨۙۘۘۘ۟ۜۘۡۥۡۗۜۗۦۘۨۖۧۧۙۨۖ۫۠۬";
                                                                                continue;
                                                                            case 994837705:
                                                                                str11 = "ۛ۟ۧۛۚۥۘۢۚۢۛۛۛۙۢۗۜۜۜ۬۫ۜۘۙۛۘۥۢ۟۬ۚۘۦۘۘۡۘۖۤ۫۬۠ۙ۟";
                                                                                break;
                                                                            case 1544135216:
                                                                                str10 = "ۗۡ۬ۗۤۖۚ۟۟ۚۡۧۡ۟ۜۘۤ۟ۙۚۧۨۚ۟ۥۖ۬۫ۡۡۧۘۢۦ۫ۤۤۖۗۨۧۘۖۙۖۥۘۤۢۙۨ";
                                                                                continue;
                                                                                continue;
                                                                        }
                                                                    }
                                                                    break;
                                                            }
                                                        }
                                                        k2.logToFloatingWindow("黑名单窗口拦截：" + strSafeResolveTargetClassName, "warning");
                                                        break;
                                                    case 2019003777:
                                                        str8 = "ۘ۫ۦۛۖۢۘۤۢ۫۟ۧۨۧۨۘ۟۬ۛۡۧ۬ۚۚ۬ۘۖۘۚۨۡۧۜۦۦۜۘۘۜۛۙۤۨۥۚۢۧۥۨۡۡۥۘۡ۟";
                                                        continue;
                                                }
                                            }
                                            return null;
                                        case -266879524:
                                            String str12 = "۟ۖۖۘۡۛ۫ۨ۠ۖۙۢۜۘۡۛۜۘ۬ۗۤ۟۠ۨۡۖۘۧۤۜ۠ۤۖۘ۬ۚۘۘۨ۬ۧۘ۫ۦۘۚۦۚ";
                                            while (true) {
                                                switch (str12.hashCode() ^ 181781196) {
                                                    case -1860776290:
                                                        str12 = "۫ۗۧ۠ۥۙۜۗۡۘۨۧۖۦۜۨۗۦۥ۫ۦۘۙۗۜۨۘۘۘۤۘۧ۠۠ۧۤۧۥۢۛۥ۬۬ۗۛۚ۟ۢۦۦۤۚۧۗ۟";
                                                        break;
                                                    case -1249200215:
                                                        if (!HookManager.loadBlackActivitiesFromFile(this.val$context).contains(strSafeResolveTargetClassName)) {
                                                            str12 = "۟ۦۤۚۧۨ۬ۘۡۖ۠ۥ۠ۚۥ۫ۦۡۘۨۤۢۙۘۘ۟ۤۘۛۛۘۘۢۚ۬۬ۗۙۦۤ۬ۢۛۘ۬ۜۡۥ۠۫ۙۘۘ۫ۜۧ";
                                                            break;
                                                        } else {
                                                            str12 = "ۛۧۥۘۘۥۨۗۖۧۜۦۨۛۤۛۙۜۘۗۗۢۜۥۨ۫ۡۥۦۚۤۗۧۜۘۧۢ۫ۙۢۦۘ۟ۦۜۘۤۙۨۘۤ۠ۖۘۥ۠ۗۚ۫ۡۘ";
                                                            break;
                                                        }
                                                    case 102093602:
                                                        str7 = "ۛ۟۟۠ۛۧۧۨۘۙۗۡۧ۠ۖۘ۬ۚۜۘۘۗۖۘۖۨۜۤۡ۬ۨۧۤۙۙۜۚۧ۠ۖۙۛۙۢۦۘ";
                                                        continue;
                                                    case 2073443987:
                                                        str7 = "ۥۥۜۜۢۘۗۦ۬ۤۢۡۘ۬ۥۛۡۘۦ۟ۖۖۛۢۢۖۗۘۡ۫ۡۘۦ۫ۨۘۚ۬۟ۡۢۗۘ۠ۨ";
                                                        continue;
                                                        continue;
                                                }
                                            }
                                            break;
                                        case 10275756:
                                            JSONObject jsonResult = s0.getJsonResult();
                                            boolean booleanExtra = false;
                                            try {
                                                booleanExtra = intent.getBooleanExtra("shell_protected", false);
                                            } catch (Throwable th3) {
                                            }
                                            String str13 = "ۜۘۡۗۦۥ۬۠ۦۜ۬ۚۢۨۤۜ۠ۦ۠ۚ۫ۙ۟۫ۦۦۖۗۗۥ۠ۨ۟ۖۡۦۘۤۚۦۡ۠ۢۖ۠ۨ۠ۗۚ۫ۥ۠ۚۜ۟";
                                            try {
                                                while (true) {
                                                    switch (str13.hashCode() ^ (-1079664497)) {
                                                        case -1147983111:
                                                            str13 = "ۙۡۜۘۙۥ۠۬ۚ۬ۙۨۘۘۙۖۜۤۧۦۘۤۜۘۧۘۖۥ۟ۖۘۜۡۜۘۘ۟ۥۘۘۛۘ۟ۖۥۙ۟ۖۛۤۢۘۢۡۦۧۘۘۥۧۘ";
                                                            break;
                                                        case -798908497:
                                                            k2.logToFloatingWindow("【跳转劫持】" + strSafeResolveTargetClassName + "\nshell跳转,不劫持", "warning");
                                                        case -721333316:
                                                            String str14 = "ۘ۬ۦۘۗۤۖۘۗۘ۫ۤۦۤۨۙۜۖۤۜۘۥۢۦۘۖۧۦۘۢۚۘۦۖۧۛۚۦۛۨۜۨۡ۟ۘۦۘ";
                                                            while (true) {
                                                                switch (str14.hashCode() ^ 1336246569) {
                                                                    case -1772033465:
                                                                        str14 = "ۘۡۛ۟ۘۖۨ۟ۖۘۡۙ۠۟ۨۤ۫ۗ۫ۙۙۦۤۡۨۡۗۦ۠ۚۖ";
                                                                        continue;
                                                                    case 8089968:
                                                                        String str15 = "ۨۜۜۡ۬ۡۙۥ۟ۜ۫ۜۘۖۤۖۘ۬ۡۢ۠ۛۛ۟۠ۙ۠۬ۖۘۤۛۚۨۙۜ۟ۛۜۚۡۢ۫۟۫";
                                                                        while (true) {
                                                                            switch (str15.hashCode() ^ 301724304) {
                                                                                case -1755019791:
                                                                                    str15 = "ۢ۟ۥۘۦۨۚ۬ۖۢۨ۬ۡۘ۫۫ۛۨۛۡۘ۫ۦ۬ۗۙ۫ۢۧۥۘۤۖۖ";
                                                                                    continue;
                                                                                case -704856979:
                                                                                    break;
                                                                                case -332483151:
                                                                                    String str16 = "ۡۛ۬ۦ۫ۘۛۥ۫ۦ۫ۜۚۘۢ۟ۙۨۘ۫ۧۜۥ۟ۛۦۗۤۘ۫ۗ۠۬ۢۦۤۘۘۘۖۛۗۗۜۘۨۧۧ۬ۤۚۤۛۨۘۚۙۘۘ";
                                                                                    while (true) {
                                                                                        switch (str16.hashCode() ^ 449965301) {
                                                                                            case -1655959407:
                                                                                                str15 = "ۦۨۥۦۙۡۘۛۦۜۘۨۗۛۜۤۦۘ۠ۨۥۘ۠ۖۙ۟ۜۨۘۨۜۘۘۧ۬ۖۛۤۗۧ۫ۚۘۖۛ۬ۨۡۘ۟ۦۥۘ۠ۦۥۘ";
                                                                                                continue;
                                                                                                continue;
                                                                                            case -902914405:
                                                                                                if (!jsonResult.has("replace")) {
                                                                                                    str16 = "ۚۦۡۖۜۚۧۢۨۤ۫ۖۦۛ۠ۜۚ۫ۤۜۛۙۥۦۤۖۤۢۜۡۘۛۡ۟ۥۦۥ";
                                                                                                    break;
                                                                                                } else {
                                                                                                    str16 = "ۜ۫ۖۘۢ۟ۤ۬ۙۡۘۤۡۡۘۙۤۗۛۚۡۨۖۖۜ۫ۘۡۚ۬ۥۘۦۡۢۤ۟ۖۖۥۖۘۖۖۛ۬ۡۧۘۘۤۨۘ";
                                                                                                    break;
                                                                                                }
                                                                                            case 531798369:
                                                                                                str15 = "۫۟ۢ۟ۘۧۚۧۢۘۗ۫ۥ۬ۤۡ۠ۗۨۗۧۧۨۘۜۨۛۢ۟ۘ";
                                                                                                continue;
                                                                                            case 1070767458:
                                                                                                str16 = "۫۬۫ۜۤ۫ۧۥۥۨۛۚۧۧۜۜۤۦۦۙۨۘۜۗۦۘۥۚۤۢ۫ۡۘ";
                                                                                                break;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case 59922986:
                                                                                    JSONObject jSONObjectOptJSONObject = jsonResult.optJSONObject("replace");
                                                                                    String str17 = "ۦۡۜۘۛۙ۬۬ۥۖ۠ۦۜۘۧۜۨۘۙۚ۟ۡۙۙۧۥۥۦ۫ۨۧۙ۫ۗۡ۫ۡۘۘ";
                                                                                    while (true) {
                                                                                        switch (str17.hashCode() ^ (-2144604370)) {
                                                                                            case -1148391037:
                                                                                                String str18 = "ۡۗۜۘۜ۬ۖۘ۠۫ۤۘ۫ۨۢ۫ۖۘۛ۟ۦۤۖۥۘۡ۟ۦۘ۬ۥۧۘۛۨۘ۬ۢۤۙۗۖۘۤۚۘۤۗۤ۠۟ۨۘۥۖۜۘ";
                                                                                                while (true) {
                                                                                                    switch (str18.hashCode() ^ (-245550866)) {
                                                                                                        case -2034195158:
                                                                                                            if (jSONObjectOptJSONObject == null) {
                                                                                                                str18 = "ۛۨۢۥۢۛۥ۟ۨۥ۟۠ۚۜۘ۟۬ۨۘ۬ۦۖۘۦۘۨۥۛۚ۟۫۠";
                                                                                                                break;
                                                                                                            } else {
                                                                                                                str18 = "۟ۖ۟ۚۘۨۘۖۨۗۛۘۘۨۡۖۢۨۜۘۙۦۥ۠ۡۛ۫ۗۡۘۙۚۛ";
                                                                                                                break;
                                                                                                            }
                                                                                                        case -1605157977:
                                                                                                            str17 = "ۦۧ۬ۦۢۡۘۗ۬ۗۤۨۢۤۦۡۘۖ۟ۦ۟ۡۦۘ۟۟ۘۘۙۛ۬ۚ۫ۜ۠ۘ۬۟ۦ۟ۜۙ۬ۖ۫ۧ";
                                                                                                            continue;
                                                                                                            continue;
                                                                                                        case 882128726:
                                                                                                            str17 = "ۜۘۖۖۧۥۘۗۨۜۘ۫ۗۨۦ۟ۙۧۥۧۖۖۘۢۖۘۥۤۦۘۜۡۢۨۨۖۤۚۖۘ";
                                                                                                            continue;
                                                                                                        case 1803902668:
                                                                                                            str18 = "ۡ۟ۥۘ۠ۦۨۘ۟ۜ۟ۨ۫ۦۘۡۤۘۘ۬ۙ۟۠ۖۨۘۜۚ۠۬ۤۘۥۥۜۘۨۡۦۚۙۨۢۥ۠۠ۖۧۘ";
                                                                                                            break;
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                            case 323939846:
                                                                                                break;
                                                                                            case 643938344:
                                                                                                str17 = "ۨۗ۬ۡۨۡۡۖۙۡۚ۠ۛۧۥ۟ۛۧۗ۫ۜۡۥۘ۫ۘ۫ۢ۟ۖ۫ۛۗۨۨۚۡۚۘۘ۬ۢ";
                                                                                                continue;
                                                                                            case 818500149:
                                                                                                String str19 = "ۗ۫ۛۙۘۧۖ۫ۗۘۥۖۖۖۘۙۦۜۘۢۨۜ۬ۤۚۡۙۘ۟ۛۧۦۖۘۘۚۤۗۤۧۖۡۥۙ";
                                                                                                while (true) {
                                                                                                    switch (str19.hashCode() ^ (-2073747980)) {
                                                                                                        case -1083841925:
                                                                                                            String str20 = "ۤۨۥۤۙۦ۫۫ۨۘۙۡۙۗۗۖۘۜۛۢۖۜۖۘۗۜ۬ۖۖۘۖ۬ۗۙ۟۟۫۬ۗ";
                                                                                                            while (true) {
                                                                                                                switch (str20.hashCode() ^ (-2053713496)) {
                                                                                                                    case -1248483172:
                                                                                                                        str19 = "۟ۢۜۛ۟ۦۘ۠ۢۜۘ۟ۙۘۘۜۛۧۢۜۘۧ۠ۗۗۨۜۗۙۡۘۘۘۗ۟ۛۢۨۥۜ";
                                                                                                                        continue;
                                                                                                                        continue;
                                                                                                                    case 194228521:
                                                                                                                        str20 = "ۘۙۜۘۘۛۚۜ۬ۥۡۦ۠ۨۖ۟ۡۙۨۘۥۡۥۘۨۗۛۚ۫۫ۜۦۖ۠ۖۧۘۜۘۥۛۨۗ۬ۛۢۧۘۚ";
                                                                                                                        break;
                                                                                                                    case 267860529:
                                                                                                                        str19 = "ۡ۠ۖۘۥ۠ۦۘۚ۠ۗۦۢۡۘۤۧۧۜۨ۫ۜۡۘۥۖۚ۟ۗۗۙۚۘۘۧ۫ۨۜۖۡۘ";
                                                                                                                        continue;
                                                                                                                    case 1931468989:
                                                                                                                        if (!jSONObjectOptJSONObject.has(strSafeResolveTargetClassName)) {
                                                                                                                            str20 = "ۢۙ۟ۘۙ۬ۛ۠ۦۘۜ۬ۦۧ۟ۦۘۨۖ۠ۘ۫ۨۘۦۧۛ۫ۥۥۢۤۢۗۢ۠۟۫ۤۖۘۡۨۘۨۗۖۘۖ۫۫";
                                                                                                                            break;
                                                                                                                        } else {
                                                                                                                            str20 = "ۘۤۤۤۙۘۦۗۥۘۛۖ۟ۗۤۥۙۨۤ۫۠ۖۘۖۥ۟ۜۨۖۦۜۚ";
                                                                                                                            break;
                                                                                                                        }
                                                                                                                }
                                                                                                            }
                                                                                                            break;
                                                                                                        case -627682040:
                                                                                                            String strOptString = jSONObjectOptJSONObject.optString(strSafeResolveTargetClassName);
                                                                                                            String str21 = "ۦۚ۟ۛ۬ۜۜۗۡۡۤ۟ۚۛۖۛ۠ۦۨ۠۠ۡۚۘۘ۠ۘۜ۠ۙۘۘ";
                                                                                                            while (true) {
                                                                                                                switch (str21.hashCode() ^ 1654946918) {
                                                                                                                    case -412076735:
                                                                                                                        String str22 = "۟ۖۘۘۥ۬ۤۢۛۦ۟ۖۨۘ۫ۘۧۗۙۜۧۨۥۗۜۚۙ۬ۜۘۡ۫ۘۘۥۢۙۦ۟ۡۘ۬ۧۗۢۥۖۘۖۢۜۘ۫ۖۢ";
                                                                                                                        while (true) {
                                                                                                                            switch (str22.hashCode() ^ (-1202208673)) {
                                                                                                                                case -1925428836:
                                                                                                                                    break;
                                                                                                                                case -415690723:
                                                                                                                                    String str23 = "۬ۢۡۘۜۨۘۘۧۛ۠ۜۗۖۛۚ۫ۦ۫ۜۘۤ۬ۢ۟ۢۚۡۡۘۜۧۖ";
                                                                                                                                    while (true) {
                                                                                                                                        switch (str23.hashCode() ^ (-856434162)) {
                                                                                                                                            case -1843388397:
                                                                                                                                                str22 = "ۘۨۖۜۜۜۢ۫ۢۤۚۧۧۛۤۙۘۖ۠ۤۛۙۛۥۘۦۦۧۘۢۙۢ";
                                                                                                                                                continue;
                                                                                                                                            case -505767973:
                                                                                                                                                if (!strOptString.isEmpty()) {
                                                                                                                                                    str23 = "ۤ۬ۢۛۡۨۘۢۚۥ۟ۡۡۘ۫۟ۡۧۘ۬ۥۗۖۢۙ۫ۨۢۛۘۥۦۨۦۚۜ";
                                                                                                                                                    break;
                                                                                                                                                } else {
                                                                                                                                                    str23 = "ۡۛ۬۬ۚۦۘۧۦۗۧۥۘۥۤ۫ۦۥۚۥۜۚۚۜۘۚ۠ۛۚۙۧۘۙۙۖۦۘۘۙۜۦۚۙۘۘ۫ۘۖۘ۬ۗۙۥۤۖۘۗۧۨۘ";
                                                                                                                                                    break;
                                                                                                                                                }
                                                                                                                                            case 471127554:
                                                                                                                                                str23 = "ۙ۬ۥۘۨۤۚۚۦۘۜۨۦۘۦۨۜۘ۫ۥ۟۟ۤۨۛۘۡۦۚۥ۠۬ۚۚۗۡۘ۬ۗۧۤ۟ۛ۫۠ۨۘۢۙۨۘۛۙۦ";
                                                                                                                                                break;
                                                                                                                                            case 1129943711:
                                                                                                                                                str22 = "۫ۖۥۘۡۙ۬ۜۧۜۜۧۦۨۦۦۘ۬ۦۘۘ۫ۦۜۘۤۥۧۘ۬ۖۧۖۤۥۘۗۗۦۙۡۗۨۜۢۖۘۧ";
                                                                                                                                                continue;
                                                                                                                                                continue;
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    break;
                                                                                                                                case -320666700:
                                                                                                                                    str22 = "ۙۡۨۘۢ۟۬ۥۢۡۘ۫ۨۦۘ۠ۨ۟ۧۙۙۥۙۖۢۥۛۜۦۘۢ۬۬ۛۢۨ۟۬۟ۖۢۚۨ۠۫";
                                                                                                                                    continue;
                                                                                                                                case 1231945941:
                                                                                                                                    String str24 = "ۚۤ۫ۚۜۥۙۢۦۘ۫ۚۦۘۛۨۧۘۦۥۦۘۥۚۨۘۙۦ۠ۥۧۛۦۢۖ";
                                                                                                                                    while (true) {
                                                                                                                                        switch (str24.hashCode() ^ 1026284716) {
                                                                                                                                            case -1680729517:
                                                                                                                                                str24 = "ۗۙۥ۫ۧ۬ۤۨۜۘۧۧ۬ۡۖۗۧۢۚ۠ۘۖۤ۫ۥۘ۬۫ۜۘۦ۠ۜۘۛۛۛۨۥۥ";
                                                                                                                                                continue;
                                                                                                                                            case -1262070538:
                                                                                                                                                break;
                                                                                                                                            case -385220594:
                                                                                                                                                String str25 = "ۗۢۙۥ۟ۤۚ۫ۘۢۢۨۚۖۨۘۚۡۜۤ۬ۜۖۦۜۜۦۧ۫ۘۙۗ۫ۛۛۦۦ۟ۙ۫ۘۨۖۜۧۘۛۧۥۨۙ۫ۢۢۗ";
                                                                                                                                                while (true) {
                                                                                                                                                    switch (str25.hashCode() ^ 1313137114) {
                                                                                                                                                        case -1811240097:
                                                                                                                                                            if (intent.getData() == null) {
                                                                                                                                                                str25 = "ۘۘۢۤۥۤۙۗ۠ۡۧۘۘ۬۟ۘۤ۫ۨۘۖۗۥۘۥۧۥۥ۫ۡۘ۠ۥ۫ۨۡۜۧۧۨۨ۬ۤۖۧ۟ۗۚۦۘۧ۫ۢ";
                                                                                                                                                                break;
                                                                                                                                                            } else {
                                                                                                                                                                str25 = "ۤ۬ۥۚۜۚۙۛ۬ۧۖۡ۫۠۟ۧ۫ۙۥۤۜۧۖۨۦۗۡۜۙۚۥۤۘۘۤۙۡۘ";
                                                                                                                                                                break;
                                                                                                                                                            }
                                                                                                                                                        case -1081708963:
                                                                                                                                                            str24 = "ۢۤۚۧۥۨ۫ۨۦۘ۬ۜۜۡۘۦۗۚ۠ۜۤۥۖۦۙۖۧۦۦۙ۬ۨۚ۬ۨۚۦ۫ۤۖ";
                                                                                                                                                            continue;
                                                                                                                                                        case -495258542:
                                                                                                                                                            str24 = "ۦۛۛۦۘۖۘۖ۟ۛۛۦۨ۫ۧ۠ۛ۟ۥ۬۬ۜ۫ۨ۫ۨۡۘ۠ۨۧۚۨۙۨ۫۠۬ۙ۟۫ۙۧۖۧۘۛۧۡۘۜۛۧۧۛ";
                                                                                                                                                            continue;
                                                                                                                                                            continue;
                                                                                                                                                        case -426331272:
                                                                                                                                                            str25 = "۠ۛۡۢۚۖۙ۟ۥۘۥۖۜۥۗۗۦ۠ۡۘۢۦۤۡ۠ۨۘۜۜۧۗۘۥۘۙۧۙۡۡۘۡۢۚۤۗۧۧۦۚۡۦۘ";
                                                                                                                                                            break;
                                                                                                                                                    }
                                                                                                                                                }
                                                                                                                                                break;
                                                                                                                                            case 1314129974:
                                                                                                                                                k2.logToFloatingWindow("【跳转劫持】" + strSafeResolveTargetClassName + "\n替换 URI 为：" + strOptString, "warning");
                                                                                                                                                intent.setData(Uri.parse(strOptString));
                                                                                                                                            default:
                                                                                                                                                continue;
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    break;
                                                                                                                                default:
                                                                                                                                    continue;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        break;
                                                                                                                    case -737485662:
                                                                                                                        Instrumentation.ActivityResult activityResultInvokeExecStartActivityCompat = invokeExecStartActivityCompat(this.val$originalInstrumentation, context2, iBinder, iBinder2, activity, intent, i, bundle);
                                                                                                                        k2.logToFloatingWindow("【启动已发起】requestCode=" + i + "，目标=" + strSafeResolveTargetClassName, "info");
                                                                                                                        return activityResultInvokeExecStartActivityCompat;
                                                                                                                    case 634628362:
                                                                                                                        str21 = "ۦ۬ۖ۟ۨۥۧ۟ۡۘۨۤۜۤۡۧۘۢۗۢۦۧۜۘۚۨۨۗۦۗۡۧۜۘ";
                                                                                                                        continue;
                                                                                                                    case 1271089144:
                                                                                                                        String str26 = "ۙ۠ۦۧ۟ۜۘۘۦۘۖ۠ۥ۫ۚۙۥۥۖ۠۠ۨ۫ۨ۫ۨۦۢۗ۠۫ۨ۫ۥۛۜۘ۟ۗۚۚ۟ۢۨۘ۟ۡۖ";
                                                                                                                        while (true) {
                                                                                                                            switch (str26.hashCode() ^ (-1454787960)) {
                                                                                                                                case -1969876133:
                                                                                                                                    str21 = "۟ۗۖۘۧۥۡۘۤ۫ۦۦۤۥۘۥۨۡۦ۬ۘۘۥۢۜۘۙۖۚ۬ۦ۫ۜ۠ۤ۫ۖۛۦ۬ۗۦۡۖۘۥ۫ۨۘ۬۟ۦۘۢۨۚۤۛ۠۬ۚ";
                                                                                                                                    continue;
                                                                                                                                    continue;
                                                                                                                                case -1916508325:
                                                                                                                                    str21 = "ۘۥۧۘۖ۬ۗ۠ۤۡۗ۟ۗۡۖۧۤۨۘۦۙۤۙۦۗۜۛۥۘۚ۠ۖ۠۠۬۫ۖۢۦ۟ۡۗۛ۠ۖۘۥۗۦ۬ۙۘۘۖ۬ۜۘ";
                                                                                                                                    continue;
                                                                                                                                case -975260696:
                                                                                                                                    if (strOptString == null) {
                                                                                                                                        str26 = "۟۬۬۠ۘ۬ۥۧۗۖ۫ۥۘۙ۠۬ۚۘۘۥۡۗ۠ۨۖۘۥ۫۟۠ۚ۟ۦۛۘۘۤ۟ۗ";
                                                                                                                                        break;
                                                                                                                                    } else {
                                                                                                                                        str26 = "۠ۗۛۦۧۥۘ۟۫ۥۡۚۗۚۤۘۘۧۥۦ۬۬۠ۚۚۨۘۙۘۥۘ۫۠۠ۚۡۧۗۦۘۡ۫ۨ۟ۤۡۘۢۚۜۘۧ۟ۢۢۘۨۚۡ";
                                                                                                                                        break;
                                                                                                                                    }
                                                                                                                                case 1548067458:
                                                                                                                                    str26 = "ۥۤۘۚۛۚۨۤۤ۟ۧۦۘۧۢ۫۫۠ۨۘۖۢۥۘ۟ۢۚۨۖۜ۬ۤۨۘۧۙۨۜ۫ۧ";
                                                                                                                                    break;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        break;
                                                                                                                }
                                                                                                            }
                                                                                                            break;
                                                                                                        case -135459239:
                                                                                                            str19 = "۠ۧۦۜۡۘۨۖۥۘۧ۬ۙۘۨۛۙۨۥۢۘ۬ۥ۫ۢۜۦۗۜ۟ۚ۠ۙ۠ۦۖۘۚۙۨۘۚۡۤۛ۫ۨۤۛۘ";
                                                                                                            continue;
                                                                                                        case 1366141544:
                                                                                                            break;
                                                                                                        default:
                                                                                                            continue;
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                            default:
                                                                                                continue;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                            }
                                                                        }
                                                                        break;
                                                                    case 513363284:
                                                                        String str27 = "ۤ۠ۖ۟ۥۖۗ۟ۧۗ۟ۙۡۢۜۘ۫۠ۡۘۢ۠ۜۘۜۤۘۘۚ۠ۨۘۨۗۗۢ۬۠ۤ۟ۜۘۖۡۦۘۖۨۘۘ";
                                                                        while (true) {
                                                                            switch (str27.hashCode() ^ 883074100) {
                                                                                case -1366799406:
                                                                                    str27 = "۬ۛۦۦ۟ۚ۫۠ۗۘۧۙ۬۠ۦۘۛ۟ۜ۠ۘۘۘ۬۟ۨۡۚۨۜۨ۠۬۟ۘۘۜۖۡۘۚ۫ۚۢۚۖۘۖۥۥۡۙۤ";
                                                                                    break;
                                                                                case -774507431:
                                                                                    str14 = "ۨ۟ۘۨۙۛۡۤۖۘۘۡ۠ۙۚۚۖۙۥۘۜۛۥۘ۟ۗ۟ۥ۬ۧۡۦۘ۠۟ۙۛۛۡۘۡۦۦۘۢۛۘۘ";
                                                                                    continue;
                                                                                case -524236441:
                                                                                    str14 = "ۛ۟ۢۘ۫ۗۛۡ۬ۧۘۦۖۥۘ۠ۙۘۘ۟ۛۢۗۦۘ۫ۤۡۙۚۧ";
                                                                                    continue;
                                                                                    continue;
                                                                                case 857322249:
                                                                                    if (jsonResult == null) {
                                                                                        str27 = "ۜۖۤۤ۫ۥۘۧۤۛۗۧۧۖۨۘۡ۫۫ۚۗۙۙۘۢۙۥۖۘ۬ۢۜۘۖۤۜۘۗۥۤۧۜۙۧۜۙ";
                                                                                        break;
                                                                                    } else {
                                                                                        str27 = "۫ۙ۬ۘۖۡۨۖ۫ۖۖۜ۫ۢۜۜۚۥۘۚۜ۬ۦۙۜۘۡۘۢۜۘ۟ۤۗۚۡۚۤۢۥ۬ۢۗۦۘۧ۫ۛۜۡۛۤۦۜ۠۬۠";
                                                                                        break;
                                                                                    }
                                                                            }
                                                                        }
                                                                        break;
                                                                    case 1844255254:
                                                                        break;
                                                                }
                                                            }
                                                            break;
                                                        case 1984426044:
                                                            String str28 = "۬۬ۨۘۨ۬۬ۚۤۡۛۢۖۘۚۡۨۘ۟ۘۡۘۙۦۡۨۦۖۘۨ۫۬ۙۨۘۜۥۤۧ۫ۥۘۤ۬ۗۜۛۛۜ۫ۜۘۜۧۙۛۤۗۡۢۥ";
                                                            while (true) {
                                                                switch (str28.hashCode() ^ (-2102989451)) {
                                                                    case -1390804246:
                                                                        if (!booleanExtra) {
                                                                            str28 = "ۡۗۘۢۗۧۘۤۨ۟ۙۚۘۦۥۤ۫ۦۘ۬ۥۡۥۘۨۘۖۨ۠ۘۘۧۨۨۤۗۨۥۖ۬۬ۡۛۥ۬۠ۥ۠";
                                                                            break;
                                                                        } else {
                                                                            str28 = "ۚۜۧۘ۠ۚۚۖۤۦۘۙۨۛۧۥ۠ۙۙۢۛۜۢۥۦۥۛۗۙۧۤۚۤ۫ۥ۠ۥۨۘۛۘۧۜ۬ۡۘ";
                                                                            break;
                                                                        }
                                                                    case -1153111927:
                                                                        str28 = "۬ۡۡۘۥۢ۟۟ۚ۬ۥۡۦۘۧۘۙۙۨۢ۟ۗۢۧۜۨۗ۠ۘۜ۬ۤۚۜۗ۫ۢۡ۠ۡۡۘۙ۠ۜ";
                                                                        break;
                                                                    case 117888307:
                                                                        str13 = "۟ۙۨۥ۠ۦۙۨۗۛۚۦۤۛۘۘۗۜۨۙ۠ۨ۬ۜۥۛۛۘۘ۠ۡۚ۫ۚۡ۬ۥۘۘ";
                                                                        continue;
                                                                    case 1554678997:
                                                                        str13 = "۫ۗۖۛ۫ۙۜۜۘۘۦۚۨۨۘۨۚۥۚۦۘ۫۟ۖۘۥۚۦۘۦۦۘۢۗۧۚۙۧۛۘۙۜۜۡۘ";
                                                                        continue;
                                                                }
                                                            }
                                                            break;
                                                    }
                                                }
                                                Instrumentation.ActivityResult activityResultInvokeExecStartActivityCompat2 = invokeExecStartActivityCompat(this.val$originalInstrumentation, context2, iBinder, iBinder2, activity, intent, i, bundle);
                                                k2.logToFloatingWindow("【启动已发起】requestCode=" + i + "，目标=" + strSafeResolveTargetClassName, "info");
                                                return activityResultInvokeExecStartActivityCompat2;
                                            } catch (Exception e) {
                                                k2.logToFloatingWindow("调用 execStartActivity 失败：" + e.getClass().getSimpleName() + " / " + e.getMessage(), "error");
                                                throw new RuntimeException("调用 execStartActivity 失败", e);
                                            }
                                        case 70415964:
                                            str7 = "ۗۥۜۘۦ۬ۜۘۘ۬۫ۙۥۛۡۧۖۚۘۖ۫ۤۥۘۤۘۖۘ۟۫ۖۘۘۗۦۘۙۦ۠۟ۙ۠ۙۥۧۘۦ۬ۖۖۙۙۧۦۨ۟ۚۦۨۡۨۘ";
                                            continue;
                                        default:
                                            continue;
                                    }
                                }
                                break;
                            case 2017789954:
                                str = "ۤۨۨۡۛۨ۠ۡۘۥۥۧۘۛۦۖۘ۬ۖۘۘ۫۟۟ۧۢۡۘۖۚۥۨۡۨ۬ۢۦۢۘۘ۠۠ۡۛۡۦ";
                                continue;
                            default:
                                continue;
                        }
                        k2.logToFloatingWindow("execStartActivity 外层异常：" + th.getMessage(), "error");
                        throw new RuntimeException("execStartActivity 异常", th);
                    }
                }
            };
            String str = "ۨ۫ۦۜۗ۬ۥۥۘۘۧۡ۬ۥۚ۟ۙۛۧ۟ۜۘۤۚ۫ۨۦۨۘ۬ۧۙ۟ۙۘۘۧۘۘۙۙۦۘۚۧۥ";
            while (true) {
                try {
                    switch (str.hashCode() ^ 881945835) {
                        case -583451269:
                            String str2 = "ۘۖۘۘۨۘۦۨۦۜۘۘۛۗۢۗۛ۬۬ۥ۫۟ۜۘۖۡۖۖۙۗۚۙ۫۬ۧۚ۟ۜۤۦۗۥۘۧۡۜۘۡ۬ۚۚۢۙۜۛۤۚۦۗ";
                            while (true) {
                                switch (str2.hashCode() ^ 30686113) {
                                    case -2071598328:
                                        str = "۬ۗۡۘ۬ۧۖۙۡۦۘۚۢۨۙۘۨۘۗۛۜۘۢۧ۬ۡۛۘۘۗۥۢۚۤ۟ۦۥۧۛۡۜ";
                                        continue;
                                    case -1871273535:
                                        str2 = "ۖ۫ۖۘۘۢۤۦۗۡۡۛۨۘۥۥۧ۠۬ۘۙۧۦۡ۫ۢۚۖ۫ۡۜۘۘۙۙۜۥۡ۠ۤۧۖۘۢۢۤ";
                                        break;
                                    case -1346996812:
                                        str = "ۦ۬۫ۜۗۦۡ۟ۧ۫ۨۘۘۨۛۥۜ۫ۨۘۢۜۧۘۨۚۘ۠ۛۢۘۗۤ۟۠ۘۗ۫ۙ۫۠ۖ۟۬ۜۚۜ۫ۖۖۘۗۥۦۛۧۢ";
                                        continue;
                                    case 2144742717:
                                        if (instrumentation != instrumentation2) {
                                            str2 = "ۚۘۘۘ۬ۥۤۨۨۡۘۛ۠۬ۙۦۘۥۖۥۘۘ۟ۡۛۥۧۘۨ۬ۥۚۘۨۚۗۘۘ۠ۧ۫";
                                            break;
                                        } else {
                                            str2 = "ۨۗۜۘۖ۟ۧۖۨۧۙۧۖۘۙۘۜ۠۠ۜۨۦۡۖۡۖ۫ۦۘۨۚۖ";
                                            break;
                                        }
                                }
                            }
                            break;
                        case -225352833:
                            str = "ۜ۫ۜۢۚ۟ۘۡۘۙۤۘۘۗۡۤۖۢۖۘۡ۠ۨ۠ۜۡۙۦۢ۬ۧۧۨۖۦۘۤۘۦۚۦۘۜ۟ۧ";
                            break;
                        case 2034094202:
                            declaredField.set(objInvoke, instrumentation2);
                            k2.logToFloatingWindow("Instrumentation2 替换完成", "info");
                            break;
                        case 2130262660:
                            k2.logToFloatingWindow("Instrumentation 已是代理，无需重复替换", "info");
                            break;
                    }
                } catch (Throwable th) {
                    k2.logToFloatingWindow("Instrumentation2 替换失败：" + th.getMessage(), "error");
                    throw th;
                }
            }
        } catch (Exception e) {
            k2.logToFloatingWindow(h.d("o/OsRcct6/+56a5L4Df5/6LzrRyuphAWLjtIywLVfS96dHeLYf8C\n", "y5zDLo5DmIs=\n", new StringBuilder(), e), "error");
            e.printStackTrace();
        }
    }

    public static void initHooks(Context context) {
        String str = "۫ۙۛۛۜۥۘۨۛۡۘۨ۟ۘۘۦۖۜۧۡۘۥ۬ۘۘۙ۫ۙۨۨۜ۠ۙۘ";
        while (true) {
            switch ((((str.hashCode() ^ 746) ^ 677) ^ 510) ^ (-1564945016)) {
                case -1836862082:
                    hookInstrumentation2(context);
                    str = "ۢۢ۟ۤۦۜۘۖۦۨۗۘۖۨۡۘۘۤۡۡۦۖۦۘۦۜ۬ۚۗ۠۠ۥۛۡۥۘ۠۠ۘ";
                    break;
                case -412922730:
                    str = "ۗۥۘۘ۬ۚۢۛۤۤۖۦۦۘۖ۫ۘۦۦۘۘۜ۫ۡ۟ۛۚۖۢۘۖ۠۠ۡۨۘ۫ۧۘۜۢۜۘۙۧۤ";
                    break;
                case 1410286691:
                    return;
            }
        }
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:11:0x0041. Please report as an issue. */
    public static Set<String> loadBlackActivitiesFromFile(Context context) throws IOException {
        BufferedReader bufferedReader;
        StringBuilder sb;
        HashSet hashSet = new HashSet();
        File file = new File(context.getFilesDir(), "black_activities_cache.json");
        String str = "ۚۥۘۗۚۖۚۤۡۘۙۨۚ۠ۧۥۗۘۘۘ۠ۗۦۘۦۚۘ۟ۛۜۘۨۥۜۚۙۙۢۜۥۘۙۙۡ۫ۡۚۚۥۨۖۘۜ";
        while (true) {
            switch (str.hashCode() ^ 53473899) {
                case -1737994005:
                    String str2 = "۬ۘۗۜۚۡۛۥ۬ۧۨۘۘۖۘۨۘۛۚۗۜۤ۫ۛۗۖۥۘۙۘۥۚۢۡۜۧۜۘ";
                    while (true) {
                        switch (str2.hashCode() ^ 999003900) {
                            case -1671649981:
                                if (!file.exists()) {
                                    str2 = "ۧۛۡۚۥۦۛۗۨۘۦۦۥۖۨۥۦۗۤۦۤۘۧۤۦۘۖۜۚۦۦۜ۫ۛۖۘ۟ۦۡۘ";
                                    break;
                                } else {
                                    str2 = "ۚ۠ۨۜۢۚ۟ۖ۫۟ۙۥ۫۫ۡۡۗ۬۫ۛۧۛ۬۫ۗۤۚ۬ۨۘۘۛ۬۠ۡۜۗۙۜۘۦۤ۫ۨۘۥۘۡۚۘۡ۬ۚۜۥۦ";
                                    break;
                                }
                            case -890532839:
                                str = "۟ۤۘۛۤۦۘۨۜۜۖۛ۬ۘ۠ۖۘۡۥۛۘۢۢۗۨ۬ۛۧۧۤ۫ۛۚۤ۬ۢ۠ۖۥۨۨۘۧۤۙۗ۠ۡۡۨۜ";
                                continue;
                            case -170707391:
                                str = "ۘۘۖۜۥۘۥ۬ۥۖۛۤۥۧۗ۟ۘ۟ۡۢۡ۬ۙ۟ۙ۫ۜۘۧ۫ۗۡۜۨۘۨۚۘۜۜۚۧ۠ۘۘۘۤۘۘۛۚۛ";
                                continue;
                            case 52623306:
                                str2 = "ۤ۫ۧۢۙۤ۠ۖۗۗۧۘۘۘۘۚۙۤ۬ۛۚۗۡۘ۬ۢۦۘ۫ۨۨۜۖ۠ۡۘۘ۬۠ۜۧۥ۬ۘۥۢۢ۠ۙۛۙۨ۫۬";
                                break;
                        }
                    }
                    break;
                case 227645384:
                    return hashSet;
                case 774209339:
                    try {
                        bufferedReader = new BufferedReader(new FileReader(file));
                        try {
                            sb = new StringBuilder();
                        } finally {
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                    while (true) {
                        String line = bufferedReader.readLine();
                        String str3 = "ۛۥۥۢۛۘ۟ۤۦۖۜۖۘۖۙۘۘ۫۫ۨۘۡۦۧۘ۫ۘۘۗۗۢۤ۟۫ۘۘۧۘ۠ۛ";
                        while (true) {
                            switch (str3.hashCode() ^ (-525223534)) {
                                case -1582562227:
                                    break;
                                case -1183361774:
                                    str3 = "ۧۡۚۢۘ۫۬ۛۡۘ۟ۨ۬ۢۙۙۡۨۖ۬۟ۢۛۖ۟ۨۡۜۛۚۡ۫ۛۢ۠ۡ۬ۛ۬ۚۛۢۡۖۡۗۢۨ";
                                case 343560068:
                                    break;
                                case 1975891429:
                                    String str4 = "۠۫ۛ۬ۚۡۜۘۦۘۗۨۙ۫ۙۨۤ۠ۦۘۡۖۦۚۘۡۚۤۦۘۘۘۡۥۢۨۘۙ۠ۗۢۛۘۦۙ۠ۘۧۚۨۦۘۨ۠۟۫۟ۛ";
                                    while (true) {
                                        switch (str4.hashCode() ^ (-1526545437)) {
                                            case -2136176394:
                                                str3 = "ۜۜۖۛۛۜۗۛ۫ۛ۬ۙۨۖۢۨ۟ۨۘ۟ۖۧۥۙۡۘۘۡۜۘ۫۟ۜۘۡۚۨۘۢۡۤۚ۬ۗۢۘۡۘۤۖۥۖ۟ۨ۬۠ۨۘۖۢۤ";
                                                break;
                                            case -400248707:
                                                str4 = line != null ? "ۖۦۘۘ۫ۡۧۖۚۚۨۜ۫ۙۖۨۨۦۙۖۨ۠ۚۡۘۧۜۦۙۜ" : "ۙ۫ۙۡۗۤۘۖۘۙۡ۫۠ۦۘ۟۬ۗ۠ۡۡۗ۬ۤ۫ۦ۟ۙۧ۠ۛ۠ۚ۫ۧۡ";
                                            case 1495189405:
                                                str3 = "ۤۧۦۘۗۦۧۢۢ۬ۥۨۨۡۤۧۜۤۘۨۦۦۘۧۜۗۨ۫ۘۘ۫ۦۧۨۗۢۤۙۦۜ۬ۘۘ۠ۨۥۘۖۙۜۤۡۛۤ۫ۜۘۡۘۧ";
                                                break;
                                            case 1856862716:
                                                str4 = "ۛۙۗ۠ۙ۟ۥ۟۫ۘۡۧۘۙۘۧۘۖ۠۬ۨۛۧۙۤۚۦ۠ۧۚۡۤۡۢۧۤۨۨۛۧۢ۬ۚۡ۟ۗ۟۬ۛۖۘ";
                                        }
                                    }
                                    break;
                            }
                            JSONArray jSONArray = new JSONArray(sb.toString());
                            int i = 0;
                            while (true) {
                                String str5 = "ۛۛ۟ۖۛۘ۟ۦۧۘۜۙ۫۟۟ۛۛۡۦۛۤ۬ۦۙ۬ۗۧۘ۠ۨۨۘۜۥۤۘۤۨۗۖۘ۬ۜۡۘۢ۠ۙۤۢۜۘۦۥۡۘۢۚۛ";
                                while (true) {
                                    switch (str5.hashCode() ^ 1033657193) {
                                        case -1969027363:
                                            String str6 = "ۜۥۖۗ۬ۧ۟ۧۘۘ۟ۧۛ۫ۢۖ۫۠ۡ۟۠ۨۨۖۛ۠۬۟ۖۖۛۥۘۦۘۢۦۡۜۗ۠ۛۥۥۘ۫ۙ۟ۧ۫ۜۘ۠ۜۖۙ۟ۢ";
                                            while (true) {
                                                switch (str6.hashCode() ^ 512384179) {
                                                    case -1330019007:
                                                        str5 = "ۡۗۖۧۗۨۘۜۤۤۧۢۗۤ۫ۢۡۛۡۦۗۤ۟ۦۥۖۘۡۢۥۛۜ۫ۜۘۢۛ۟ۤۧۧۥۡۘۢۡۛۡۙۜۘۢ۠ۖۦ۟ۥ";
                                                        break;
                                                    case -1042924644:
                                                        str5 = "ۤ۟ۘۦۨۤۘۨۘۚۜۥۘ۫ۛۥۘۥۘ۟ۙۗۜۙۨ۟۠ۛ۠۫ۖ۬۟ۜۛۢۨۘ";
                                                        break;
                                                    case 913634299:
                                                        str6 = i < jSONArray.length() ? "ۙۙۥ۠ۨۨۗۡ۟ۖ۬ۜۘ۫ۥۥ۠ۨۨ۠۬ۚ۠ۡۜۜۢۜۜۘۘۘ" : "ۚۦۜ۫۠ۖۘۚۡ۬۫ۚۡۘ۬ۙ۬ۖۧۦۘۜ۠ۚۥۛۢ۫ۚۚۜۥۦۘ";
                                                    case 1862426359:
                                                        str6 = "ۛ۫ۚۚۤۥ۫۫ۜۘۘۙۨۘۢۨ۟ۢۚۘۗۧۨۘ۬ۧۘۥۤۥۙۗ۟۟ۡۡۘۛ۬۬";
                                                }
                                            }
                                            break;
                                        case -1337439238:
                                            str5 = "ۢۡۨۘۨۜۖۗۨ۠ۙۜۡ۠۠ۢۜۨۨۘۦ۟۠ۖۛۛۧۜۨۘ۠ۗۨۙۚۙۤۛۤ۠ۡۡۨۧ";
                                        case 412716032:
                                            hashSet.add(jSONArray.optString(i));
                                            i++;
                                        case 1632247560:
                                            bufferedReader.close();
                                            break;
                                    }
                                }
                            }
                        }
                        return hashSet;
                        sb.append(line);
                        break;
                    }
                case 1643500735:
                    str = "ۛۗۤۛۛۛ۬ۛۡۘۤ۫ۖۘۜۥ۠۠ۨۚۦ۠ۖۘۧۥۜۘۦۖۖ۫ۜۧ";
                    break;
            }
        }
    }
}
