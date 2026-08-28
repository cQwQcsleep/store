package core.pro.android.notify;

import android.app.Activity;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import com.example.shell.Utils;
import com.shadow.okhttp3.ConnectionSpec;
import com.shadow.okhttp3.FormBody;
import com.shadow.okhttp3.Headers;
import com.shadow.okhttp3.OkHttpClient;
import com.shadow.okhttp3.Request;
import com.shadow.okhttp3.Response;
import com.shadow.okhttp3.ResponseBody;
import gTBLD.dev.XSSTG.free.Utils;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import org.json.JSONObject;

/* loaded from: /workspace/unpacked/classes2.dex */
public final /* synthetic */ class d2 implements Runnable {
    public final int a;
    public final Object b;
    public final Object c;
    public final Object d;

    public /* synthetic */ d2(Object obj, Object obj2, Object obj3, int i) {
        this.a = i;
        this.b = obj;
        this.c = obj2;
        this.d = obj3;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:108:0x025d. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:51:0x018e. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:61:0x01bc. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:6:0x0082. Please report as an issue. */
    /* JADX WARN: Removed duplicated region for block: B:44:0x015e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    private final void a() throws IOException {
        int i;
        boolean z;
        HttpURLConnection httpURLConnectionI;
        StringBuilder sb;
        Iterator it;
        InputStream errorStream;
        String str = (String) this.b;
        Map map = (Map) this.c;
        Object[] objArr = (Object[]) this.d;
        Activity activity = Utils.a;
        HashMap map2 = new HashMap();
        StringBuilder sb2 = new StringBuilder();
        try {
            httpURLConnectionI = Utils.i(new URL(str));
            httpURLConnectionI.setRequestMethod("POST");
            httpURLConnectionI.setConnectTimeout(10000);
            httpURLConnectionI.setReadTimeout(10000);
            httpURLConnectionI.setDoOutput(true);
            httpURLConnectionI.setDoInput(true);
            httpURLConnectionI.setUseCaches(false);
            httpURLConnectionI.setRequestProperty("User-Agent", "yunzhuru Mozilla/5.0");
            httpURLConnectionI.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            sb = new StringBuilder();
            it = map.entrySet().iterator();
        } catch (Exception e) {
            e = e;
            i = -1;
        }
        while (true) {
            String str2 = "۫ۡۖۖۡۘ۟ۢۙ۠ۥۦۤۦۚ۠ۜ۠ۨۧۤۡۢۖۘۡۚۧۧۖۨۘۘۗۤۖۤۚ۠ۡۘ۬ۥۡۚۚۥۤۜ۬۠ۤ۠ۜ۬ۜ";
            while (true) {
                switch (str2.hashCode() ^ (-1646070181)) {
                    case -1513117125:
                        String str3 = "ۤۦ۫ۧۢۥۧ۬ۤۖۨۦۛ۠ۚۥ۫ۧۖ۠ۖۘۜ۫ۙ۫۫ۘۘ۫ۖۜۘۨ۠۫۠ۡۦۘۛۗۗۡۜۘ۟ۥۥۘ۫ۛۛۧۙۙۡۚۖۘ";
                        while (true) {
                            switch (str3.hashCode() ^ (-1408905125)) {
                                case -1647593741:
                                    str2 = "ۚۜۧۘ۟۬ۘۘۙۙۨۘۜۘۦۘۛۡۥ۫ۥۗ۬۠ۚ۬ۦۤۗۦۘۦ۠ۜۚ۠ۢۢ۟ۥۘ۬ۨۢۧۗۧ";
                                    break;
                                case -1113491346:
                                    str3 = it.hasNext() ? "ۛۨۦۘۦۢۧۨۧ۫ۤ۠ۨۘۥۦۤۖۙۘۘۨۙۢۘۥۤ۟ۙ۫ۗۖ۬۫ۢۧ۬ۘۨۡۚۚۧ۠ۚۙ۬ۢۤۥۘ" : "ۡۢۖۛۘۡۘۖۚۦۗۤۨۦۨۖۘۡۡ۫ۥۧۜۖۢ۠ۦ۬ۜۘۙۦۜۚۥۦۧۥۛ۠ۛۢۖۖ۟";
                                case 544901579:
                                    str3 = "ۖ۟۫ۗ۫ۚۡۤ۟۫ۙۙۨۥۘۡۗۥۘ۬ۗۘ۫ۚۙۢۦ۟ۖ۫ۤۥ۟ۚۥۥۧۚۚ۟ۥۡۘۢۡ۟۬ۘ۠";
                                case 1386806273:
                                    str2 = "ۡۛۜۘۙۥۨۗۧۘۘۤۢۧۥۨۘۦ۬ۢۤۥۗۡۜۧۘۢۜۨۧ۫ۤۡۦۗۥ۟ۡۘ";
                                    break;
                            }
                        }
                        break;
                    case -337373391:
                        Map.Entry entry = (Map.Entry) it.next();
                        String str4 = "ۜۢ۬۫۟ۙۜۢۨۘۗۜۖۘۜۚۨۜۢ۫ۨۨۤۤۦ۠ۚۜۘۘۛ۟۬۟ۨۘۜۜۚۗۙۜۘۜۜ۟";
                        while (true) {
                            switch (str4.hashCode() ^ (-1099900925)) {
                                case -725926736:
                                    break;
                                case -264124370:
                                    sb.append("&");
                                    continue;
                                case 1343408805:
                                    str4 = "ۤۚۜۡۤۧۚۜۖۘۡۨۜۘۢۗۧۧۡۡۤۨۥۘۖۦۘۘۦ۫ۙۦۡ۠۟ۖۘۘ۬ۤۤۨۘۘۛۡۘ";
                                    break;
                                case 1598788066:
                                    String str5 = "۟ۛۛۖۤ۫ۙۦۨۦۦۢۘ۠۫ۢۚۢۙۛۤۡۗۘۘۙۜ۬۫ۖۨۘۤۛۦۘۜۛۖۘ";
                                    while (true) {
                                        switch (str5.hashCode() ^ 1446583448) {
                                            case -2136547046:
                                                str5 = sb.length() > 0 ? "ۤۚۖۘ۟ۖۢۧۘ۠ۙۙۢۤۢۦۘۦ۬ۡۤۡۘۘۘۥۚۜۘۛۘۦۥۘ۬ۗ۬۬ۤۥۘ" : "ۗۖۡۖۨۤۨۥ۫ۦ۬۫ۤۙ۫ۤۜۥۥۖۚ۠ۡۢۦۥۦۨۘۚۤۜۘۥۜۖۤۚۧ۠۠ۤ۠ۜۨۗ۠ۖۘۥۗ۬ۨ۫ۦۘ";
                                            case -185108657:
                                                str5 = "۠ۥۦۧۨ۫ۗۗۙۥۧۘۛۜ۠ۜۛۗۛۤۡۡۥۡۡۦ۫ۤۖ۬ۜۖۘ۠ۤ۫";
                                            case 1163434459:
                                                str4 = "ۡۖۘۘۖۚۨۨۤ۠ۤۙۛۖۡ۬ۢۛ۠ۗۡۖۦۘۢۥ۫ۡۡۘۚۛۙۗ۫۫۟ۚۥۘۙۗۡۘ۟ۘۗۜ۫ۧ";
                                                break;
                                            case 1672853881:
                                                str4 = "ۘۨۤۨۨۤۗۚۜ۟ۛ۠۬ۥۦۢ۬ۢۨۘۨۜۖۚۢ۟ۧۘۥۘۛ۟ۡۗۧۥۡ۬ۘ۬ۘۘ";
                                                break;
                                        }
                                    }
                                    break;
                            }
                        }
                        sb.append(URLEncoder.encode((String) entry.getKey(), "UTF-8"));
                        sb.append("=");
                        sb.append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));
                        break;
                    case 425307455:
                        break;
                    case 1803311698:
                        str2 = "ۜۨۜۘۢۛۨۘۗۧۥۘۧۙۨۘ۟ۦۙۤۢۦۥۢۢۛۥۖۘۙ۟ۘۨۗۘۘۙۙۤۙ۟ۘ";
                }
                OutputStream outputStream = httpURLConnectionI.getOutputStream();
                outputStream.write(sb.toString().getBytes("UTF-8"));
                outputStream.flush();
                outputStream.close();
                int responseCode = httpURLConnectionI.getResponseCode();
                String str6 = "ۢۨۥۘۤ۠ۖۧ۬ۘۘ۬ۜۘۗۡۢ۠ۧۚۙۙۜۘ۟ۙۗ۬ۚۧۤۛۘۘۜۜۘۨۗۚۥۙۜۘۨ۫";
                while (true) {
                    try {
                        switch (str6.hashCode() ^ (-1678125458)) {
                            case 714722698:
                                str6 = "ۨۖۜۘ۠ۥۜۘۦۜۥۘۗ۠۠۫ۛۧۖ۟ۙ۬ۙۡۘۚۥۨ۠ۡۚ۠ۚۖۘ";
                            case 1103744278:
                                String str7 = "ۛۡۙ۫ۘ۬ۤ۫ۚۦۧۥۢ۠ۨۘۦۦۚۡۥۡۨ۠ۚۘۥۦۘۢ۫ۢ";
                                while (true) {
                                    switch (str7.hashCode() ^ (-755663717)) {
                                        case -1457846795:
                                            break;
                                        case -1181268995:
                                            String str8 = "ۗ۠ۖۚۨۜۗ۬۠ۥۧۥۤۧۖۙۥۜۘۡۘۘۘ۠ۛۦۡۜۚ۬ۙ۠ۥۢۨۘۚ۟ۛۛۜۚۦۜۛ۠ۙۘ۟ۜۦ";
                                            while (true) {
                                                switch (str8.hashCode() ^ 1413326418) {
                                                    case -1625840025:
                                                        str8 = "ۦۛ۬ۙ۠ۘۘۖ۫ۚ۬۟ۘۜۖ۫ۘۡۚۨۘ۬ۦ۬ۨ۬ۥۦۘۥۨ۟ۜۜۙۛۨۜۘۧ۟ۛۘۜۧۛ۬ۡۘۙ۬ۘۘۧ۟ۥۘۗۘ۫";
                                                        break;
                                                    case -1216895221:
                                                        if (responseCode >= 400) {
                                                            str8 = "ۗۦۧۘۨۢ۬ۤۧۡۘۢۡۗۘۢ۠ۜ۫ۡۥۧۦ۠ۨۗۧۘۡ۬۠ۗ";
                                                            break;
                                                        } else {
                                                            str8 = "ۘۢ۟۠۬ۗ۬ۗۘۘۘ۫ۧۚۙۨۘ۟۠۟ۧۡۘۚۤۢ۬۬ۦۘۗۖۨۤۤ۫ۦۡۚ";
                                                            break;
                                                        }
                                                    case 906859997:
                                                        str7 = "ۧ۫ۦۘۗۨۧۘۙۧۥۘۜۘۧۦ۠ۙۙۜۥۨ۠۠۠۫ۨۢ۟ۖۛۥۤۨۢۨۘۘۨۡۘۛۦۢۧۥ۫ۥۜۙۤ۬ۘۘۦ۫ۛۛۥۗ";
                                                        continue;
                                                    case 1781885140:
                                                        str7 = "ۖۧۘ۟ۨۗۛۦۜۘ۟ۗۖۘ۠ۘۜۥۥۥۘ۬ۜۜۘۢ۫ۦ۟ۨۥۘۖۡۙۛۡۨۤ۠ۘۘ";
                                                        continue;
                                                }
                                            }
                                            break;
                                        case 1549466420:
                                            str7 = "ۗۜۧ۟ۜۖۖۘۡۚۦۜۛ۠ۨۖۦۘۚۥ۬ۤۙۡۢۖۚۤۖۦۥۦۜۤۧۜۘۘۡۦۨ۠ۡۙۥۘO";
                                        case 1943925784:
                                            errorStream = httpURLConnectionI.getInputStream();
                                            break;
                                    }
                                }
                                break;
                            case 1478516028:
                                String str9 = "۟ۢۚ۬ۘۙ۬۠ۡ۫ۥۚۛ۬ۥۧ۫ۜۛۖۡۘ۫ۗۖۘۚۘۚۛۥۖۘۥۥۛۦۥۜۘ۫ۥۜۘۜۛۨۘۤۖۙۤۙۡۘۧۖۨۧۤۤ";
                                while (true) {
                                    switch (str9.hashCode() ^ (-1394412759)) {
                                        case -2022807092:
                                            str9 = "ۖۥۜۡۧۘۛ۟ۥۘۚۘۦۥۤۘۤ۟۫۠۠ۘ۬ۘۦۘ۬ۧۜۘۘۦۧۘۖۚۤۚۢۘۘۙۧۦۤۜۘ۠ۨۨۘۨۧۘۘۘۜۢ۟۫";
                                            break;
                                        case -822358715:
                                            str6 = "ۖۡۜۧۡۖۘۙۨۥۘۗۖ۫ۘۥۤۢۘۖۘ۬۫ۛۧ۬ۧۧۜۙ۬ۜۡۙ۫۟ۙۥۥ۬ۜۥۘۘ";
                                            continue;
                                        case 89570953:
                                            str6 = "ۖ۫ۢۚ۬ۙ۬۫ۦۘ۬ۘۢ۫ۛۤۛۜۥۘۥۛۥۗۡۖۘۢ۫ۘۘ۠۬ۥۘۗۤۖۖ۫ۗۗۜۘۜۥۥ";
                                            continue;
                                        case 1578220072:
                                            if (responseCode < 200) {
                                                str9 = "۠ۖۡۘۗۗ۟ۘۗۘ۬ۛۡۘۥۡۡۦۙۖۗۖۚۦۡۜۘ۫ۧۜۘۙۗۢۥۢ۫ۢۙ۫ۖۨۙۖۤۖۘۘ۠ۨۨۤۨۘ";
                                                break;
                                            } else {
                                                str9 = "ۛۤۜۘۤۥۧۨۢۧۨۜۙۢۚ۟ۦۥۛۖۦۢ۠۫ۘۘۙۢۗ۠ۘۨۘۖۛۡۢۙۨۘۗۚۘۛۜ۟";
                                                break;
                                            }
                                    }
                                }
                                break;
                            case 1983616678:
                                break;
                        }
                    } catch (Exception e2) {
                        e = e2;
                        i = responseCode;
                        e.printStackTrace();
                        sb2.append("请求失败: ");
                        sb2.append(e.getMessage());
                        z = false;
                        objArr[0] = new Object[]{Boolean.valueOf(z), Integer.valueOf(i), map2, sb2.toString()};
                        synchronized (Utils.e) {
                        }
                    }
                }
                errorStream = httpURLConnectionI.getErrorStream();
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream));
                while (true) {
                    String line = bufferedReader.readLine();
                    String str10 = "ۨۢۘۘۡۜۦۘۜۜۦۘۨۙۗ۟ۖۛۙۘۡۘۜۡۧۥۧۘۖۦۚۚۤۧ۟ۚۚ۟ۥۧۘۤۨۘ۟ۚۗ";
                    while (true) {
                        switch (str10.hashCode() ^ 275377694) {
                            case -642671715:
                                break;
                            case -296597484:
                                break;
                            case 54496707:
                                str10 = "ۧۦۦۗۡۘۘ۠ۨۘۖ۫ۘۘۤۨۧۘۗۚ۠ۤۨۢۡۦۚۦۧۥۢۡۡۘۧ۫ۡ۟۠ۙ";
                            case 1622852436:
                                String str11 = "۠ۨۚ۠۫ۤۦۧۢ۠۟ۨۜۜ۫ۗۤۚ۬ۗۧۖۥۚ۠ۛۙۜۗۜۖ۬ۗۢ۫ۖۗۨۤۢ۟ۜ۠ۧۢ۠ۖۜۘۧۥ۬";
                                while (true) {
                                    switch (str11.hashCode() ^ (-1199755621)) {
                                        case -1616125271:
                                            str11 = "ۦ۟ۜۘۨۧ۫ۤۗۘۢۤۥۨۛۜ۫ۡۚۖ۫ۧۚۛ۠۬۟ۨۘۛۖۚ";
                                        case -957929660:
                                            str10 = "ۚۥۥۘۤۦۙۚۘ۠۠ۨۨۢۢۢۜ۠ۧ۬ۡۜۖۚۜۤۜۘۙ۠۬۬۠ۚۙ۫ۤ۬۬ۦۢ۠ۧۖۛۧۢ۠۠";
                                            break;
                                        case 535528220:
                                            str10 = "۬ۨۧۙۢۚۛۥۚۙۥ۟ۘۨۦۦ۟ۛۙۧۢۜۘۜۘۘۚۡۘ۬ۗۘۘۛۚۜۡۢ";
                                            break;
                                        case 1464698023:
                                            str11 = line != null ? "ۢۜ۫ۘ۬ۧۧۦۨۘۧ۫۬ۗۧۦۚۢۜۘۤۡۜۘۧ۫ۡۨۜۖ۬ۗۥۢۛۡۘۜ۠ۘۚ۟ۧ۠ۧۤۤۙۖۙۦۖۛۡۛۡۡۙ" : "۠ۨۛۡۘ۬ۚۢۤۤۨۘۚۢ۬۬ۘۥ۟۫۫۠ۧۖۘۥۜۚۢۥ۟ۥۧۥۘۤۜۦ";
                                    }
                                }
                                break;
                        }
                        bufferedReader.close();
                        errorStream.close();
                        httpURLConnectionI.disconnect();
                        Iterator<Map.Entry<String, List<String>>> it2 = httpURLConnectionI.getHeaderFields().entrySet().iterator();
                        while (true) {
                            String str12 = "ۛ۠ۧۘۨۥۦۛۡۘۧۤۥۘ۬ۙۛۨۦۘۦۗ۬ۡۖۖۘۘۨۖ۟ۙۨۗۜۘۙ۠ۙ";
                            while (true) {
                                switch (str12.hashCode() ^ (-1313123714)) {
                                    case -1646546637:
                                        z = true;
                                        i = responseCode;
                                        break;
                                    case -1568081956:
                                        Map.Entry<String, List<String>> next = it2.next();
                                        map2.put(next.getKey(), String.valueOf(next.getValue()));
                                    case -169211968:
                                        String str13 = "۬ۙۙۥۖۨ۫ۤۦۥ۠ۥۚۖۤۘۧ۠۟ۗۗۗ۫ۜۗۡۢ۟ۜۘۦۙ۠ۘۢۦۚۖۗۢۡۖ۫ۨ۫ۧۦۘ";
                                        while (true) {
                                            switch (str13.hashCode() ^ (-1192421346)) {
                                                case -290931403:
                                                    str12 = "ۙۡۦۘۚۘۦۘۧۢ۬ۚۙۖۢۥۙۨ۫ۤۧۘۜۘ۟ۥۧۡ۟ۙۤۤۤۘۥۨۨۜۖۘ";
                                                    break;
                                                case -261316283:
                                                    str13 = "ۥۘ۫۟ۚۨۘ۟ۤۜۘۖۘۘۗ۬ۙ۫ۛۛۧۥۘۘ۫ۡۘۨ۠ۖۘۨۤۡۚ۬ۙۢۤۧۧۘۧۦۨۥ";
                                                case 832607942:
                                                    str12 = "ۗۖۦۛ۟ۚ۠ۥۥۦۤۦۜ۠۫ۥۢۡۘ۠ۙ۫ۘۢۡۘۛۦۤۦ۟ۘۡۙۖۘۚۚۜ";
                                                    break;
                                                case 1799377229:
                                                    str13 = it2.hasNext() ? "ۡۧۛۤ۟ۦۘۦۡۜۘۖۨۥۘۤ۬ۖۦۤۗۥۢۦ۟ۧۦۦۗ۟۟ۤۜۨۙۘۦۛۚ۬ۙۗ۬ۘۘۦۧۦۘ۫ۙۥۘ" : "ۤۢۘۛ۬ۘۘ۠ۦۗۧۨ۟ۤ۟ۥ۫ۨۤۖۨۘۖۥ۫ۢۦۗۚۖۤۡ۠ۡۘۨ۫ۦۘۥۢۘ۟ۘۡ";
                                            }
                                        }
                                        break;
                                    case 379572433:
                                        str12 = "ۦۖۗ۟ۧۨۘۨۥۜۜ۬۬ۧۖ۬۠۬ۦۘ۟ۙۜۘۢۚۨ۫۫ۖۘۨۚۡۘۨۨ۬ۨۢ۫۬ۡۥۚۛۨ";
                                }
                            }
                        }
                    }
                    sb2.append(line);
                    sb2.append("\n");
                }
            }
            objArr[0] = new Object[]{Boolean.valueOf(z), Integer.valueOf(i), map2, sb2.toString()};
            synchronized (Utils.e) {
                Utils.f = false;
            }
            return;
        }
    }

    /* JADX WARN: Can't wrap try/catch for region: R(7:685|76|(4:(3:77|78|79)|671|109|(5:110|111|700|112|766))|666|106|107|108) */
    /* JADX WARN: Code restructure failed: missing block: B:115:0x0285, code lost:
    
        throw new gTBLD.dev.XSSTG.free.Utils.a(core.pro.android.notify."0X1");
     */
    /* JADX WARN: Code restructure failed: missing block: B:130:0x02ac, code lost:
    
        r8 = r11;
     */
    /* JADX WARN: Failed to find 'out' block for switch in B:480:0x097d. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:523:0x0a82. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:534:0x0ab5. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:581:0x0b4c. Please report as an issue. */
    /* JADX WARN: Failed to find 'out' block for switch in B:8:0x0071. Please report as an issue. */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:1010:0x0296 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1011:0x08cd A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1012:0x08d4 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:1016:0x028d A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:63:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:709:0x08d1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:736:0x0278 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:761:0x02b1 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:762:0x02d2 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:763:0x02d5 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:767:0x026e A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:989:0x0853 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:990:0x08f0 A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:991:0x090f A[SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:992:0x0852 A[SYNTHETIC] */
    @Override // java.lang.Runnable
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final void run() throws Throwable {
        int i;
        Exception e;
        Map map;
        final String strD;
        final boolean z;
        final int i2;
        final Map map2;
        HttpURLConnection httpURLConnectionI;
        StringBuilder sb;
        Iterator it;
        InputStream errorStream;
        boolean z2;
        int i3;
        boolean z3;
        int iCode;
        String strReplaceAsteriskWithRandom;
        OkHttpClient okHttpClientBuild;
        FormBody.Builder builder;
        Iterator it2;
        Exception e2;
        String str;
        String str2;
        boolean zIsSuccessful;
        String str3;
        Throwable th;
        switch (this.a) {
            case 0:
                Set<String> set = k2.closedPopupIds;
                int iMin = Math.min(((TextView) this.b).getMeasuredHeight(), (int) (((Activity) this.c).getResources().getDisplayMetrics().heightPixels * 0.5f));
                ScrollView scrollView = (ScrollView) this.d;
                ViewGroup.LayoutParams layoutParams = scrollView.getLayoutParams();
                layoutParams.height = iMin;
                scrollView.setLayoutParams(layoutParams);
                return;
            case 1:
                String str4 = (String) this.b;
                Map map3 = (Map) this.c;
                Activity activity = Utils.a;
                Map map4 = new HashMap();
                try {
                    httpURLConnectionI = Utils.i(new URL(str4));
                    httpURLConnectionI.setRequestMethod("POST");
                    httpURLConnectionI.setConnectTimeout(10000);
                    httpURLConnectionI.setReadTimeout(10000);
                    httpURLConnectionI.setDoOutput(true);
                    httpURLConnectionI.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    sb = new StringBuilder();
                    it = map3.entrySet().iterator();
                } catch (Exception e3) {
                    i = -1;
                    e = e3;
                    map = map4;
                    e.printStackTrace();
                    strD = h.d("5vzDTmUGCKKMtswQ7qQ=\n", "DlN0qNSE7R4=\n", new StringBuilder(), e);
                    z = false;
                    i2 = i;
                    map2 = map;
                    Handler handler = new Handler(Looper.getMainLooper());
                    final m3 m3Var = (m3) this.d;
                    handler.post(new Runnable(m3Var, z, i2, map2, strD) { // from class: core.pro.android.notify.w2
                        public final m3 a;
                        public final boolean b;
                        public final int c;
                        public final Map d;
                        public final String e;

                        {
                            this.a = m3Var;
                            this.b = z;
                            this.c = i2;
                            this.d = map2;
                            this.e = strD;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            String str5 = "۫ۧۗۨۚ۬ۧۖۨۘۥ۫۟ۨۘۖۘ۠ۡۦۤۘۡۘۥۦۥۘۖ۠ۥۧ۫ۜۢۢۘۘۡ۠ۜۘۢۛۥۘۢ۟ۦۘۚ۫ۨۘۡۖ۠";
                            while (true) {
                                switch ((((str5.hashCode() ^ 189) ^ 621) ^ 454) ^ (-1070158438)) {
                                    case -1478992145:
                                        return;
                                    case -1030395540:
                                        str5 = "ۜۘۗۙۢ۠ۘ۬ۘۘ۠ۨۘۘۗۧۜۚ۟ۚۧۙۖۘۡۚۧۜۨۘۦۖۛ";
                                        break;
                                    case 436284884:
                                        ((b3) this.a).onResult(this.b, this.c, this.d, this.e);
                                        str5 = "۠ۘۥۘۚۗۜۖۢۗۢ۠ۦۘۚۗۨۧۘ۠ۘۛۗ۟ۜۚۙۗۢۘ۟ۨۘۥ۠ۖۘۗۖۖ۠ۦ۟۠ۧۡۘۤۧ۠ۢۗۥ۬ۦۢۖۦۖۘ";
                                        break;
                                    case 1481414100:
                                        Activity activity2 = Utils.a;
                                        str5 = "ۛ۬ۙۖ۫ۤۖ۟۠ۜۢ۫ۛۖۡۘۢۢۜۧۨۥۘۨ۟ۛۨ۬ۦۛۚۜۘۧۡۛۥۧۙ";
                                        break;
                                }
                            }
                        }
                    });
                    return;
                }
                while (true) {
                    String str5 = "۠۠ۤۘۛۖۦۘۗۤ۬ۗۢۧۨۘۨۙۜۘۗ۫۬۠ۦۤۗ۬ۥۢۛۚۗ۬ۙۥۜۦۘۜ۫ۤۨۖۖۘۗۙۗۨۨ۠ۨۛۜۘ۠۬ۜ";
                    while (true) {
                        switch (str5.hashCode() ^ 1897640037) {
                            case -1837112087:
                                String str6 = "ۛۦۜۘۘۙ۠ۘۚۨ۟ۡۘۡۘۘۚۗۡۥ۫ۧۚۦۖۜۢ۟۬ۗۖۛ۫ۜۙۖ۬ۘۤ۬ۨۗ۠";
                                while (true) {
                                    switch (str6.hashCode() ^ (-318010591)) {
                                        case -2030568187:
                                            str5 = "۠۫ۖۘۛۙۖۘۨۨۗ۬ۥۘ۬ۧۡۘۨ۟۫ۨۡۡۘۙ۠ۡۢۥ۫ۖ۠ۥ۫ۦۘۜ۬ۧۚۧۚۗۦۢۨۡۡۗۨ";
                                            break;
                                        case 385223067:
                                            str6 = it.hasNext() ? "۬۟ۡۘۗۜۖۘۛۜۙۗۤۢۜۜۚ۠ۡۜۘۜۘۦۘۦۚۙۧۨۘۦۚۖۘۤ۠ۨۘۛۤۢ۬۫ۜۥۖۡ۬ۛۜۘۗ۟ۦۘ۠ۥۚ۫ۤ۬" : "ۧۘۖۚۧۥۘۨۧۧ۫ۥۥۘۢۨ۬ۧۜۨ۠ۤۙۖۜۨۦۡۨۘۢۚۖۘ";
                                        case 1518773509:
                                            str6 = "ۜۙۘۘۖ۟ۘۘۘۖۙۢۧ۠ۥۧ۫ۜ۟ۙۧۗ۬ۨۗۨ۫ۜۢۤۖۢ۬۫۠۬۬ۖ";
                                        case 2044202446:
                                            str5 = "۟۠ۙ۫۫ۨ۠ۖ۟ۖ۠ۢۤ۬ۘۘۧۜۚۚۧۤۧۥۜۜ۠۫ۘۖۤ";
                                            break;
                                    }
                                }
                                break;
                            case -1339088799:
                                break;
                            case -951264793:
                                Map.Entry entry = (Map.Entry) it.next();
                                String str7 = "۬ۢۖۛۘۖۘ۠۬۟۟ۙۨۘۧۢۤۜۚۥ۬۠ۦۘ۟ۜۥۘۢ۟ۢۦۤۨ۫ۡۧۘ۫ۘۜ";
                                while (true) {
                                    switch (str7.hashCode() ^ (-916988375)) {
                                        case -1372370006:
                                            str7 = "۟ۥۧۘ۠ۥۥۢ۫ۗۧۨۨۘۦۘۡۚ۫ۜ۟ۘۙۛۙ۟۠ۦۘۘۚۨۘۦۛۤ۟ۘۘۘ";
                                            break;
                                        case -1202004977:
                                            break;
                                        case -1037678722:
                                            sb.append("&");
                                            continue;
                                        case 473855032:
                                            String str8 = "ۘۤۜۙۗۨ۟ۛۧۜ۬ۡۘۘۛ۬۠ۥۡۤۦ۟۟ۘۜۘۛۛ۠ۗۧۧۚۡ۠ۤۜۘۘۛۦ۠ۖۨۘۗ۬ۡۘۜ۬۫ۡۡۘۖۙ۠";
                                            while (true) {
                                                switch (str8.hashCode() ^ (-1853422630)) {
                                                    case -564862902:
                                                        str8 = sb.length() != 0 ? "ۢ۬ۥۤۤۦۘۜۦۨۘۦۡۨ۠۟۬ۧۦۦۘۤۦۤۖۗۧۘۦۡۘۜ۠ۜ" : "۬ۢۘۘۢۛۨۜۚۨۘۚۤۡۗۡۦۘۗۨۦۤ۠ۢ۫۟ۜۘۤۦۢۡۜۖۘۨۖۜۤۙۧۛ۬ۥۘۘ۟ۛۖۡۦ۫ۢۘۦۡۘۜۦۘ";
                                                    case 130301839:
                                                        str7 = "ۛۢۖۘ۟ۙۡۙ۫ۦ۫ۡۡۘۢۤ۠۟۟ۜ۠ۛۢۖۡۨۘ۠ۨۙۢۥۤۗۛ۟۬ۥۡۥۤۛ۠ۛۜ۟۠ۖۘۜ۬ۚۙۤۘۛۗۨ";
                                                        break;
                                                    case 1422112813:
                                                        str8 = "ۖۘۤ۟ۚۚۖ۫۠ۡۚۥۨۡۘۦۜۢۨ۬۠ۡۜۢۡۘۜۖ۠ۙۦۥۖۨۙۤۢۨۖۘ۫۠ۜۘ";
                                                    case 1531444073:
                                                        str7 = "۫ۛ۬ۡ۟ۡۚۨۚۘۜۡۘۙ۬ۜۘۖۖۡۘۚۥۖ۬ۥۖ۫ۜۥۘ۬ۦۨۘ";
                                                        break;
                                                }
                                            }
                                            break;
                                    }
                                }
                                sb.append(URLEncoder.encode((String) entry.getKey(), "UTF-8"));
                                sb.append("=");
                                sb.append(URLEncoder.encode((String) entry.getValue(), "UTF-8"));
                                break;
                            case -202251988:
                                str5 = "ۘ۬ۖۘۥۤۧۨۤۥۘۨۜۥ۫ۨۧ۫ۘۦۢۥۚۙ۠ۚ۬ۘۗۘۘۙۚ۬ۤ۬ۘۚۚۤۥۦ۠ۜۡۖ۫۬";
                        }
                        OutputStream outputStream = httpURLConnectionI.getOutputStream();
                        try {
                            outputStream.write(sb.toString().getBytes("UTF-8"));
                            outputStream.close();
                            int responseCode = httpURLConnectionI.getResponseCode();
                            try {
                                Map headerFields = httpURLConnectionI.getHeaderFields();
                                String str9 = "ۡۧۦۘ۬ۤۧۡۡ۫۟ۗۤۖ۟ۡۘۗۤۖۤۖ۠ۜۥۙۢ۬ۦ۫ۦۘۛۦۗ۠ۛۦۗۜۨۘۢۙۥۤۧۡۢۗۢۥۧۥۗۜ";
                                while (true) {
                                    try {
                                        switch (str9.hashCode() ^ 267714410) {
                                            case -324703217:
                                                String str10 = "ۤۛۘۢۤۜۤۛۤ۟ۨۨۢۙۜۘۖۗۡۘۢۗۧۙۛ۠۠ۡ۟ۘ۬ۥۘ۫ۥ۠۠ۖۥۢ۠۬ۘۧۜۘۖۖۧۘۗۘۘۚۜۧۥۙۚ";
                                                while (true) {
                                                    switch (str10.hashCode() ^ (-102705576)) {
                                                        case -1748603742:
                                                            String str11 = "ۛۛ۠ۧۨۤۡۡۦۡ۠ۥۘۚۦۜۙۥۢ۫ۤۨۘۜۨ۟ۡۦۘۚۧۚۢۥۚۖ۬ۢ";
                                                            while (true) {
                                                                switch (str11.hashCode() ^ 653974441) {
                                                                    case -1939465354:
                                                                        str10 = "۟ۚۦۘۙ۫ۦۘۜۘۖۛۨۘۘ۟ۦۘۘۨۗۢۥۚۧۘۢ۟ۥ۬ۥ۠ۛ۠";
                                                                        continue;
                                                                    case -1463482299:
                                                                        str11 = "۟ۗۥ۟ۥۨۘۢۚۖۘۗۧۚۨۜۖۗۚۗۧۜۘۛۗۜۘ۠۠۫ۘۜ۫۠۟ۡۘۖۡۘۘۥۧۛۤۖۧۖۗۙۙۦۙ۬ۛۨۘۤۘۛ";
                                                                        break;
                                                                    case 46861239:
                                                                        if (responseCode >= 400) {
                                                                            str11 = "۬۠ۘ۬ۤۘۖۨۧۘۗۜۖۜ۬ۛ۬ۤۙۡۡۘۘ۟ۖۤۧۘۢۛ۫ۖ۬۠ۧۙۥۘۨۗۘۗۗۜۘۖۘۦۘۦۡۙۖۜۘۤۘ۬";
                                                                            break;
                                                                        } else {
                                                                            str11 = "ۢۙۖ۠۫ۙۜۥۘۦۥۢۧۙۢ۬۠ۘۘۡۤۘۦۜۦۘۜ۬ۥۘۛۜ۬";
                                                                            break;
                                                                        }
                                                                    case 381499319:
                                                                        str10 = "ۛۥ۟ۧۧۡۘۦ۬ۜۘۖۨۢ۫ۤۙۘۤۡۢ۠۫ۚۨۘۧۨۗۗۢۚۥ۬ۨۤۦۚۛۖۖۘۧۡۘۡۚۛ۠۫۠ۜۘۘ۠ۜ۠";
                                                                        continue;
                                                                }
                                                            }
                                                            break;
                                                        case -1129410080:
                                                            break;
                                                        case 74478993:
                                                            errorStream = httpURLConnectionI.getInputStream();
                                                            break;
                                                        case 1499631416:
                                                            str10 = "ۖ۠ۥۘۖۛۥ۠۫ۚۖ۬ۥۘۜۚۙۖۛۘۘۗۘۘۘ۠ۖۥۥ۫ۚۦۛ۠۫ۙۛۤ۟ۥۘۢۡۜۨۤۡ";
                                                    }
                                                }
                                                break;
                                            case 1176335406:
                                                break;
                                            case 1613986746:
                                                str9 = "ۗۧ۫۬ۙۧۦۤۨۘۨۚۧ۠ۡۨۘۜۖۡ۟ۛۙۘۡۘۧۘۧۢۦۘۛۧۡۘۢۡۜۘۖۖۨۘ۟ۥۗ";
                                            case 1812333657:
                                                String str12 = "ۢۧۨۘۧۘۨۘۧۖۡۘۥۨۜۘۥۚۤۢۛ۬ۧۤۜۙۨۥۘۛۚۥۘۢۘۙ۠ۥۨۘۧۨ۠ۡۖۦۘۡ۠ۙ";
                                                while (true) {
                                                    switch (str12.hashCode() ^ (-1005637633)) {
                                                        case -1389183761:
                                                            str9 = "ۜۨۤۦۘۡۘۨۧۘۥ۫ۡ۠۫ۨۨۢ۟ۨۡۙۗ۫ۘۘۛ۟۫۠ۙ۠ۙۧۨۘ۟ۧۢۡۦ۬۠ۗۥۨۚۚ۠ۖۘۥ۬ۖۘۗۢ۬";
                                                            continue;
                                                        case -202403405:
                                                            str9 = "ۗ۟ۘۨۡۥ۟ۖۘۘۨۦۚۜۘ۠۟ۡۦۘۛۜۘۘۥ۬ۥۧۘۗ۟ۗۘ۬ۜۗۡۖۦۤۦۤۢۛۨۧۜ۟۫ۨۘ";
                                                            continue;
                                                        case 1147982550:
                                                            str12 = "ۧ۠ۤۡۜۤۦۦۤۡ۬ۢ۫ۨۤ۟۫ۖۘۚ۫ۥۘ۠۠ۧۢۤۚۡۗۢۧۗۜۘۙۦۧ۫ۥ۫ۢۨۗۚۤۜۥ۬ۥۜۙۤ۬";
                                                            break;
                                                        case 1785266407:
                                                            if (responseCode < 200) {
                                                                str12 = "۬ۗۧ۠ۤ۠ۖۦۗۖۖۨۛۛ۫ۙۥ۬ۡۘۤ۬ۙ۟ۚۦ۫ۜۥۢۗۨۛ۬۟ۘ";
                                                                break;
                                                            } else {
                                                                str12 = "ۡۜۥۦ۬ۨۛ۠ۘۜ۠ۢۘۛ۟۫ۨۖۙۗۡ۠ۡۧۘۥۦۗۛۘۙۧۜ۬ۥۧ۫۬ۖۗۗ۫ۨۤۢۗۢۨۘۙ۫ۢۡۥۖۘ";
                                                                break;
                                                            }
                                                    }
                                                }
                                                break;
                                        }
                                    } catch (Exception e4) {
                                        e = e4;
                                        map4 = headerFields;
                                        i = responseCode;
                                        map = map4;
                                        e.printStackTrace();
                                        strD = h.d("5vzDTmUGCKKMtswQ7qQ=\n", "DlN0qNSE7R4=\n", new StringBuilder(), e);
                                        z = false;
                                        i2 = i;
                                        map2 = map;
                                        Handler handler2 = new Handler(Looper.getMainLooper());
                                        final m3 m3Var2 = (m3) this.d;
                                        handler2.post(new Runnable(m3Var2, z, i2, map2, strD) { // from class: core.pro.android.notify.w2
                                            public final m3 a;
                                            public final boolean b;
                                            public final int c;
                                            public final Map d;
                                            public final String e;

                                            {
                                                this.a = m3Var2;
                                                this.b = z;
                                                this.c = i2;
                                                this.d = map2;
                                                this.e = strD;
                                            }

                                            @Override // java.lang.Runnable
                                            public final void run() {
                                                String str52 = "۫ۧۗۨۚ۬ۧۖۨۘۥ۫۟ۨۘۖۘ۠ۡۦۤۘۡۘۥۦۥۘۖ۠ۥۧ۫ۜۢۢۘۘۡ۠ۜۘۢۛۥۘۢ۟ۦۘۚ۫ۨۘۡۖ۠";
                                                while (true) {
                                                    switch ((((str52.hashCode() ^ 189) ^ 621) ^ 454) ^ (-1070158438)) {
                                                        case -1478992145:
                                                            return;
                                                        case -1030395540:
                                                            str52 = "ۜۘۗۙۢ۠ۘ۬ۘۘ۠ۨۘۘۗۧۜۚ۟ۚۧۙۖۘۡۚۧۜۨۘۦۖۛ";
                                                            break;
                                                        case 436284884:
                                                            ((b3) this.a).onResult(this.b, this.c, this.d, this.e);
                                                            str52 = "۠ۘۥۘۚۗۜۖۢۗۢ۠ۦۘۚۗۨۧۘ۠ۘۛۗ۟ۜۚۙۗۢۘ۟ۨۘۥ۠ۖۘۗۖۖ۠ۦ۟۠ۧۡۘۤۧ۠ۢۗۥ۬ۦۢۖۦۖۘ";
                                                            break;
                                                        case 1481414100:
                                                            Activity activity2 = Utils.a;
                                                            str52 = "ۛ۬ۙۖ۫ۤۖ۟۠ۜۢ۫ۛۖۡۘۢۢۜۧۨۥۘۨ۟ۛۨ۬ۦۛۚۜۘۧۡۛۥۧۙ";
                                                            break;
                                                    }
                                                }
                                            }
                                        });
                                        return;
                                    }
                                }
                                errorStream = httpURLConnectionI.getErrorStream();
                                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(errorStream));
                                StringBuilder sb2 = new StringBuilder();
                                while (true) {
                                    String line = bufferedReader.readLine();
                                    String str13 = "۫ۗۗ۫ۛۗۚۦۡۦۜۤۗۜۖۘۘ۠ۡۘۚ۫ۧۙۘۘۦۨۥۛۥ۠ۚۧ۬ۘۨۧۘ";
                                    while (true) {
                                        switch (str13.hashCode() ^ (-1868304817)) {
                                            case -990771456:
                                                String str14 = "ۨۢۚۜۢۢۧۦۡ۟ۚۨۘۛۘۥۜۥۜۘۗ۫ۖۘۛ۫ۧۦۡۡۘۥۚ";
                                                while (true) {
                                                    switch (str14.hashCode() ^ 505532340) {
                                                        case -1691758636:
                                                            str14 = "۠۠۟ۚۧۘۡۛۛۜ۫ۚۦۥۘۘ۬ۖۜۜۗۧۖۖۥۤۡ۠۟ۜۘ";
                                                        case -1360205350:
                                                            str13 = "۟ۙۦۘ۫ۗ۠۠ۖۛۨ۬ۨۨۘۖۘۢۚۦۘۘۙۥۘۘۜۡۛۜۥۘۙۧۡ";
                                                            break;
                                                        case -85870697:
                                                            str13 = "ۚۗ۟ۜۤۜۨ۫۠۟ۦۥۘۖۛۖۘۛۤۦۜ۠۫ۚۦۗۦۢ۠ۤۧۘ۠ۗۡۘۘ۠ۥۚۥۛۛۛ";
                                                            break;
                                                        case 351462968:
                                                            str14 = line != null ? "ۚۖۘۧۛۚۧۙ۬۬ۥ۠ۧۦۗۢۛ۬ۤ۬ۘۘۜۤ۠۫ۡۘۡ۫ۦۘ" : "۬ۖۨۘۜۛۘۧ۟ۨۘۛ۬۠۫ۡۥۘ۟ۨۨۥۥ۬ۨۡۚ۫ۚ۠ۥۡۘۖۦۘۢۧۢ";
                                                    }
                                                }
                                                break;
                                            case -899126041:
                                                break;
                                            case 521550422:
                                                str13 = "ۤ۫ۡۘۢۙۨۘۚۧ۟۬ۙۡۚۙۛۛ۟ۘۢۘۦۜۚ۟ۢۤۜۘۥ۟";
                                            case 1617732584:
                                                break;
                                        }
                                        strD = sb2.toString().trim();
                                        String str15 = "۠ۙۚۢۗۘۢۜۗ۬ۜۗۢۡۜۘۚۗ۟۠ۤۘۘۘۧ۫ۤۜۧۡ۫۠ۙۖۜۢ۬ۤ";
                                        while (true) {
                                            switch (str15.hashCode() ^ 1219578146) {
                                                case -1635282056:
                                                    str15 = "ۜۨ۫ۢۥۡ۬ۥۡۘ۫ۨۖۘ۫ۡۨۨۤۦۘۙۜ۠ۛ۠ۖۥۢۦۜۙۡ۟ۖۘۘ۬ۜۜۘ";
                                                case 585596678:
                                                    String str16 = "ۦۡ۬ۙۗۧ۬ۜۦۚۨۜۖ۟۬ۖۘۡۘۥۢ۟ۛۥۚۛ۫ۙ۠۫ۨۘۥۜۢۜۧۢۥۧ۟ۜۗۚۙۨۚۘۥۢ";
                                                    while (true) {
                                                        switch (str16.hashCode() ^ (-804275547)) {
                                                            case -931073273:
                                                                z2 = true;
                                                                break;
                                                            case 300561959:
                                                                String str17 = "۠ۤ۟ۛۜۛۜۤۥۧ۟ۦۘۛۦ۬ۜۤۡۘۥۡۗ۠ۡ۠ۜۢۨۡۦۙۤۧۗ۫۠ۨۘۚۧۖۗۚ۟ۤۨۧۗۙۧۦۖۚۖۧۨ";
                                                                while (true) {
                                                                    switch (str17.hashCode() ^ 1218607168) {
                                                                        case -1448535086:
                                                                            str16 = "ۢ۟ۢ۫ۘۨۡۙۧۜ۬ۘۙۥ۬ۖۡۤۥ۫ۚۦ۬ۜۘۚ۫ۥۘ۠ۤۗ۠ۡۘ۠ۨۧۘۘ۫ۗۗ۟۟ۖۜ۫ۙۦۨۙۖۛۥۘ";
                                                                            continue;
                                                                        case -1380954795:
                                                                            str17 = "ۗۦۨۘ۟۫ۖۖۧۗ۟ۢۦۖ۠ۜۘ۫۬ۜۘۙ۬ۡۘۥۗۧۥۨۖۘۡۥۛ";
                                                                            break;
                                                                        case -291040459:
                                                                            str16 = "۟ۛۙۨۙۥۘۛۧۡۙ۫ۛۙۜۚۘۙۨۛۢۥۘۡۥۙۘۨۡۥۢۥۘۚۥۜۙۘۡ۟۬ۢۜۧۡۘۘۧۥۢ۬ۡۚۢۥۘ۠ۛ";
                                                                            continue;
                                                                        case 1731862189:
                                                                            if (responseCode >= 300) {
                                                                                str17 = "۬ۙۦۘۧۚۛ۫ۖۚۘۘ۬ۡۖۙۧ۬ۢۚ۫ۘۘ۟ۚۦۘۚۨۨۤ۠۬ۨۙۘۜ۠۠ۜۚۡۘ۠۠ۦۧ۠۟ۥ۟۬ۢۜۘ۬ۨۨۘ";
                                                                                break;
                                                                            } else {
                                                                                str17 = "۠ۜۤۛۘۖۘ۬ۤۘ۫ۛۜۘ۬ۡۥ۫ۡ۠۫۠ۤۙ۟ۜۘۚۥۜۖۨۛ۟ۨۦۘۤ۫ۡ";
                                                                                break;
                                                                            }
                                                                    }
                                                                }
                                                                break;
                                                            case 482636791:
                                                                break;
                                                            case 1354596316:
                                                                str16 = "ۥ۟ۥۘۚ۬۠ۚۘۜۘ۟ۨۨۘۛۗۖۘ۫ۛۜ۫ۨۙۜۜۗۤۥۨۜۚۥۘ۟ۖۖۤۧۡ";
                                                        }
                                                    }
                                                    break;
                                                case 615803869:
                                                    String str18 = "ۧۘ۠ۦ۟ۥۘۗۚۦۛۙۨۜۥ۠ۖۤۚۙ۫۠۟ۛۚۧۙ۬ۤۚۙ۬ۘۡ";
                                                    while (true) {
                                                        switch (str18.hashCode() ^ (-1489020824)) {
                                                            case -1288263779:
                                                                str15 = "ۧۦ۟ۢۢۘۘۦۚۤۙۛۢۜۧۜۨ۟ۡۖ۬ۥۘۘۡ۠ۚۖۡۘۥ۠ۡۨ۫ۚۦۦۨۖ۫ۚ";
                                                                continue;
                                                            case 265054106:
                                                                str18 = "ۤۘۚۢۖ۫ۥۤۨۘۦۘۖۘۙۤۥۤۚۧ۟ۖ۟ۚۨۡۨ۬ۘۘۙۥۨۢۨۤۘۘۖۗۖۘ۬ۡۘ";
                                                                break;
                                                            case 470161164:
                                                                str15 = "ۜۜۚۛۥۜۘۦۚۘۚۘۡۘ۫ۙۧ۠ۡ۟۬ۙ۬ۨۘۡۘۗۨۙۗۚۡۘ";
                                                                continue;
                                                            case 1359805261:
                                                                if (responseCode < 200) {
                                                                    str18 = "ۛۚۜۘۧۜۦۜۛۘۤۨۤۛۦۗ۬ۨۛۨۢۨۘ۠ۙۨۚۡۜۡۘۘۗۦۘۥۙ۠ۨۖۘۘۧۖۖ";
                                                                    break;
                                                                } else {
                                                                    str18 = "ۘۙۘۘۖ۫ۙۙۢۛۢۨۥۢۧۜۘۢۙۜۘۛۤۨۘۢ۬ۜۘ۠۬ۜ۠ۙۖۧۥۘ۠ۤ۠";
                                                                    break;
                                                                }
                                                        }
                                                    }
                                                    break;
                                                case 1904515556:
                                                    break;
                                            }
                                        }
                                        z2 = false;
                                        z = z2;
                                        i2 = responseCode;
                                        map2 = headerFields;
                                    }
                                    sb2.append(line);
                                    sb2.append("\n");
                                }
                            } catch (Exception e5) {
                                e = e5;
                            }
                        } catch (Throwable th2) {
                            String str19 = "۠ۙۖۘ۟ۙ۫۫۬ۘۘۗۖۖۜۜۡ۟ۙۢۦۦۖۘۥ۟۟ۘ۬۠ۜۗۖۖۖۤۛۙۘۥ۠ۥۖ۫ۙۤ۬ۨۘۜۘ";
                            while (true) {
                                switch (str19.hashCode() ^ 1036373751) {
                                    case -1344729656:
                                        try {
                                            outputStream.close();
                                            throw th2;
                                        } catch (Throwable th3) {
                                            th2.addSuppressed(th3);
                                            throw th2;
                                        }
                                    case -794231986:
                                        throw th2;
                                    case -724281377:
                                        str19 = "ۧۢۘۘۥۚۧ۫ۡ۟ۛۜۧۖ۫ۚۧۚ۬۠ۚ۠ۦۨۗۡ۬۠۟ۨۦ";
                                        continue;
                                    case 1060769240:
                                        String str20 = "۬ۡۧۜۢۡۘۧۚ۟۟ۡۤۥۘۥۨۛۛۤۨۘۘۖۘۤۦۜۧۘۥۜۨۖۡۨۡۗ۫ۜ۠ۜۙۛۦۚۜۦۘۧۛ";
                                        while (true) {
                                            switch (str20.hashCode() ^ (-1626509125)) {
                                                case -2144718529:
                                                    str19 = "۟ۚ۫۫ۢ۟ۜ۫ۨۖ۠ۧۙ۫ۨۡۚۥۘۖ۠ۥۘۥ۟ۨۖۡۧۗۚۖۢۧۛۛۢ۫ۚۙۚۤۙۙ۬ۛۥۘۚۡۛ";
                                                    continue;
                                                case -1723959795:
                                                    str19 = "ۘۨۥۘ۟ۙۜ۫ۤۜ۟۟ۨۜۘۢۦۜۖۘۨ۬۟ۨۘۚ۫۠۬ۖۥۥ";
                                                    continue;
                                                    continue;
                                                case -1679645368:
                                                    if (outputStream == null) {
                                                        str20 = "ۖ۟ۥۜۗۢۢۛ۟۬۫ۢۙ۬ۥۛۚ۟۠ۛۤۥۙۜۤۡۧۘۤۘۛ";
                                                        break;
                                                    } else {
                                                        str20 = "ۧ۟ۜۘ۟۬۫ۜۘۖۖۚ۬ۤۢ۬ۨۧۘۜۗۖۖ۫ۖۘۨۧ۠ۥۚۤ۟ۖۙۙۤ۠ۜۛۤ۫ۨۡۘ";
                                                        break;
                                                    }
                                                case -1582681518:
                                                    str20 = "ۤۦۘ۠ۘۦۘۛۤۥۘۤۛۨۘۨۤۘۤ۫ۖۚۦۘۙ۟ۜۚۨۚۤۨۙۡۥۢۢۤۦۦۡۡۥۦۧۖۗۥۗۡۡۘ";
                                                    break;
                                            }
                                        }
                                        break;
                                }
                                i = -1;
                                e = e3;
                                map = map4;
                                e.printStackTrace();
                                strD = h.d("5vzDTmUGCKKMtswQ7qQ=\n", "DlN0qNSE7R4=\n", new StringBuilder(), e);
                                z = false;
                                i2 = i;
                                map2 = map;
                                Handler handler22 = new Handler(Looper.getMainLooper());
                                final m3 m3Var22 = (m3) this.d;
                                handler22.post(new Runnable(m3Var22, z, i2, map2, strD) { // from class: core.pro.android.notify.w2
                                    public final m3 a;
                                    public final boolean b;
                                    public final int c;
                                    public final Map d;
                                    public final String e;

                                    {
                                        this.a = m3Var22;
                                        this.b = z;
                                        this.c = i2;
                                        this.d = map2;
                                        this.e = strD;
                                    }

                                    @Override // java.lang.Runnable
                                    public final void run() {
                                        String str52 = "۫ۧۗۨۚ۬ۧۖۨۘۥ۫۟ۨۘۖۘ۠ۡۦۤۘۡۘۥۦۥۘۖ۠ۥۧ۫ۜۢۢۘۘۡ۠ۜۘۢۛۥۘۢ۟ۦۘۚ۫ۨۘۡۖ۠";
                                        while (true) {
                                            switch ((((str52.hashCode() ^ 189) ^ 621) ^ 454) ^ (-1070158438)) {
                                                case -1478992145:
                                                    return;
                                                case -1030395540:
                                                    str52 = "ۜۘۗۙۢ۠ۘ۬ۘۘ۠ۨۘۘۗۧۜۚ۟ۚۧۙۖۘۡۚۧۜۨۘۦۖۛ";
                                                    break;
                                                case 436284884:
                                                    ((b3) this.a).onResult(this.b, this.c, this.d, this.e);
                                                    str52 = "۠ۘۥۘۚۗۜۖۢۗۢ۠ۦۘۚۗۨۧۘ۠ۘۛۗ۟ۜۚۙۗۢۘ۟ۨۘۥ۠ۖۘۗۖۖ۠ۦ۟۠ۧۡۘۤۧ۠ۢۗۥ۬ۦۢۖۦۖۘ";
                                                    break;
                                                case 1481414100:
                                                    Activity activity2 = Utils.a;
                                                    str52 = "ۛ۬ۙۖ۫ۤۖ۟۠ۜۢ۫ۛۖۡۘۢۢۜۧۨۥۘۨ۟ۛۨ۬ۦۛۚۜۘۧۡۛۥۧۙ";
                                                    break;
                                            }
                                        }
                                    }
                                });
                                return;
                            }
                        }
                    }
                    Handler handler222 = new Handler(Looper.getMainLooper());
                    final m3 m3Var222 = (m3) this.d;
                    handler222.post(new Runnable(m3Var222, z, i2, map2, strD) { // from class: core.pro.android.notify.w2
                        public final m3 a;
                        public final boolean b;
                        public final int c;
                        public final Map d;
                        public final String e;

                        {
                            this.a = m3Var222;
                            this.b = z;
                            this.c = i2;
                            this.d = map2;
                            this.e = strD;
                        }

                        @Override // java.lang.Runnable
                        public final void run() {
                            String str52 = "۫ۧۗۨۚ۬ۧۖۨۘۥ۫۟ۨۘۖۘ۠ۡۦۤۘۡۘۥۦۥۘۖ۠ۥۧ۫ۜۢۢۘۘۡ۠ۜۘۢۛۥۘۢ۟ۦۘۚ۫ۨۘۡۖ۠";
                            while (true) {
                                switch ((((str52.hashCode() ^ 189) ^ 621) ^ 454) ^ (-1070158438)) {
                                    case -1478992145:
                                        return;
                                    case -1030395540:
                                        str52 = "ۜۘۗۙۢ۠ۘ۬ۘۘ۠ۨۘۘۗۧۜۚ۟ۚۧۙۖۘۡۚۧۜۨۘۦۖۛ";
                                        break;
                                    case 436284884:
                                        ((b3) this.a).onResult(this.b, this.c, this.d, this.e);
                                        str52 = "۠ۘۥۘۚۗۜۖۢۗۢ۠ۦۘۚۗۨۧۘ۠ۘۛۗ۟ۜۚۙۗۢۘ۟ۨۘۥ۠ۖۘۗۖۖ۠ۦ۟۠ۧۡۘۤۧ۠ۢۗۥ۬ۦۢۖۦۖۘ";
                                        break;
                                    case 1481414100:
                                        Activity activity2 = Utils.a;
                                        str52 = "ۛ۬ۙۖ۫ۤۖ۟۠ۜۢ۫ۛۖۡۘۢۢۜۧۨۥۘۨ۟ۛۨ۬ۦۛۚۜۘۧۡۛۥۧۙ";
                                        break;
                                }
                            }
                        }
                    });
                    return;
                }
            case 2:
                a();
                return;
            default:
                String str21 = (String) this.b;
                HashMap map5 = (HashMap) this.c;
                Object[] objArr = (Object[]) this.d;
                Activity activity2 = Utils.a;
                HashMap map6 = new HashMap();
                StringBuilder sb3 = new StringBuilder();
                try {
                    strReplaceAsteriskWithRandom = Utils.replaceAsteriskWithRandom(str21);
                    OkHttpClient.Builder builder2 = new OkHttpClient.Builder();
                    TimeUnit timeUnit = TimeUnit.SECONDS;
                    okHttpClientBuild = builder2.connectTimeout(10L, timeUnit).readTimeout(10L, timeUnit).retryOnConnectionFailure(true).connectionSpecs(Arrays.asList(ConnectionSpec.MODERN_TLS, ConnectionSpec.CLEARTEXT)).build();
                    builder = new FormBody.Builder();
                    it2 = map5.entrySet().iterator();
                } catch (Exception e6) {
                    e = e6;
                    i3 = -1;
                    z3 = false;
                }
                while (true) {
                    String str22 = "ۡۡۖۘۡۢۥۘۘۦ۠ۡۢۥۚۜۛۡۘۥ۫ۧۛ۫ۢۨۧۦۘۤۤۥۘۜ۫ۜۙ۠ۘۘۥۚۜۥۡۨۜۧۖۜۘۦۘ";
                    while (true) {
                        switch (str22.hashCode() ^ (-2076855823)) {
                            case -1788913405:
                                Map.Entry entry2 = (Map.Entry) it2.next();
                                String str23 = (String) entry2.getKey();
                                String str24 = (String) entry2.getValue();
                                String str25 = "ۥۥۨ۫ۘۜۘۤۢۜۨۖۡۘۤۥۘ۟ۜۦ۟ۤ۫ۙ۟ۡ۬۬ۨۢۨ۬۟ۧۤۦۤ۟ۖۡۘ۫۫ۤۚۚۧۚۨۖۘ۫ۜۛۥۘۡ";
                                while (true) {
                                    switch (str25.hashCode() ^ (-878825542)) {
                                        case -1704160800:
                                            break;
                                        case -77241285:
                                            String str26 = "۠۬۫۬ۖۖۨۜ۠۬ۙۗ۟ۡۤ۟ۙۡۤۜۧۘ۟۟ۘ۫ۨۥۘۥۤۦ۫ۧۦ۠ۦۦۘۨۧۛۦۡۘۦ۠ۜۘ۫ۗۦ";
                                            while (true) {
                                                switch (str26.hashCode() ^ (-1756662848)) {
                                                    case -1341919911:
                                                        String str27 = "۫ۥ۟ۥۤۡۘۥۢۜۘۛ۟ۨۘۛ۬ۧۨۜۧۧۗۘۡۢ۬ۢۥۘۘ۫ۗۖۘۖۤۤ۟ۦۛۘۨۖۘۙۥۦۘۜۚۚۡۤ۟";
                                                        while (true) {
                                                            switch (str27.hashCode() ^ 1142188311) {
                                                                case -262932623:
                                                                    str26 = "ۚۨۜۘۙ۫ۖۘۖ۟۠ۡۙۛۢۚۨۡۧ۟ۖۢۥۨۖۦۘۗۖۚۚۤۡۘ";
                                                                    break;
                                                                case 252248496:
                                                                    str27 = str24 == null ? "ۗ۬۠ۗۖ۟ۗۤ۟۫ۥۥۘۙۦ۠ۤۡۡۨۨۛۡ۠ۨۙۢ۫ۜ۬ۨۜۚۤۙ۫ۢۛۖۜۢۖۘۚۤۗۘۨۖۘ" : "ۛ۬ۗۦۧۨۘ۟ۥۢۗۢ۠ۦۘۘۡۢۛۙ۠ۘۛۜ۟ۧۘۦۥ۟ۤ";
                                                                case 1873652702:
                                                                    str26 = "۟ۦۧ۠ۧۦۘ۠ۙۨۡۤۥۘۤۜۧۘۥۧۘۗ۫ۗ۬۫ۢۧۛۢۦۛۧۨۖۙۗۧۡۦۥۖۘۛ۫ۛ۠ۦ۫ۙ۬ۘۙۥۨۘ۬ۜۥ";
                                                                    break;
                                                                case 1922597764:
                                                                    str27 = "ۥ۟ۦۘۥۗۡۧ۟ۤ۟ۘ۫ۥۜۦۘ۟۟ۗۡۘۡۘۚۦۖۘۥۙ۟ۧۡۘۚۡۗۖۜۡۘ۟ۨۡۛۘۘۘ۫۠ۚۡۚ۫";
                                                            }
                                                        }
                                                        break;
                                                    case -1330290538:
                                                        break;
                                                    case 559110632:
                                                        str24 = "";
                                                        break;
                                                    case 1281072826:
                                                        str26 = "ۤ۫ۢۛۡۚۢۘۘ۟ۜۥ۬ۛۡۡۜۚۖۗۜۘ۠ۨۧۘۨۧۡۗۥۖ";
                                                }
                                            }
                                            builder.add(str23, str24);
                                            continue;
                                        case 615356178:
                                            String str28 = "ۢ۟ۗۢۨۜۘۧۙۥۦۡۛۡۚۚۚۘۘۧۚۖ۠ۥۤۦۚۦۘ۫ۢۥۘ۠۫ۨۨۧۤ۫۬ۖۧۜۥۘ";
                                            while (true) {
                                                switch (str28.hashCode() ^ (-1740365731)) {
                                                    case -150318364:
                                                        str28 = "ۥ۠ۡۘ۬ۧۗۤۦۧ۟ۘۛۖۤۙ۟ۜۘۡ۟۬۟ۙۥۙۜ۠ۙۘۡۙۡ۟ۧ۫ۘۘ";
                                                    case 1311450170:
                                                        str28 = str23 != null ? "ۦۡۘۘۧۥۘ۬۫ۥۘ۠ۨۘۜۜۙۖۙۥ۟ۡۜۘۡۤۗۡۢۖۗۚۗ۟ۛۖ۬ۨۨۘ" : "ۨ۬ۘۘ۠ۘۧۘۦۗۘۘۨۙۢۙۚۚۢۢۦۤۘۘۤ۬ۖۨۢۥۧ۟ۨۘۗۧۗۘۥۧۢ۠ۚ۟ۛۧۗ۠ۚ۠ۢ۠۬ۗۢ۫ۡۚ";
                                                    case 1595226015:
                                                        str25 = "۠ۗۚۨۥۙۦۥ۟ۙۤۖۘ۫ۖ۫۟۫ۧۢۗۗۙ۬ۚۜۦۖ۫ۗۨۘ";
                                                        break;
                                                    case 1718326648:
                                                        str25 = "۟ۜۨ۟ۧۥۘ۬۟ۡۘۥ۟ۚ۫ۜۦۘۢۘۘۦ۬ۖۤۢۖۘۙۚۡۘۜۖۜۘ۠ۙۧۘۡۢ";
                                                        break;
                                                }
                                            }
                                            break;
                                        case 1692285934:
                                            str25 = "ۛۡۡۖۗۨۘۡۗۛۘۗۡۘۚ۠ۨۘۨ۟ۘ۠ۘۨۘۤۦۘ۠ۖۜۘ۬ۜۜۗ۬ۡۘۗۨۜۘ";
                                            break;
                                    }
                                }
                                break;
                            case -1594989646:
                                str22 = "ۤۚۥۛۢۨ۟ۙۥۘۖۤۘۘۚۦۥ۠ۦۥۜۖۗۙ۟ۗۖۜۢۥۜ۫ۗۧۜۘۢۚۛۧۦۖۘۦۦۜۘۜۥۘ۠ۗۙۚ";
                            case 670752972:
                                String str29 = "ۥۘۥ۟ۜ۠ۥۧ۬ۦۧۢۜۘۘۚۡۤۜ۬ۛ۬ۛ۬۬ۛۧۥ۫";
                                while (true) {
                                    switch (str29.hashCode() ^ (-391544189)) {
                                        case -955891911:
                                            str22 = "۠ۘۨۜۥۘ۫ۦ۫ۦۥ۬ۢ۠ۗۛۖۘ۬ۗۨۘۜۦۚ۟۫ۙۨۥۤۖۦۖۦۦ۟ۨۤۘۘۜۥۦۘۚ۟ۦۧۛۡۘۧ۬۠";
                                            break;
                                        case 210531162:
                                            str22 = "ۙۥۢۢۨۨۘۨۡۦۘ۟ۧۚ۠ۥۘۚۡۘۧۖۡۘۙ۟ۘۘۚۛۘ۠ۘۨۘۡۜۧۧۜۛۗ۬ۗۨۛۖۤۘۥۘۘ۠ۧ";
                                            break;
                                        case 1481478748:
                                            str29 = "۫ۗۧۘۖۦۘۖۨۘۡۥۜۥۨ۬۬ۥۜۦۘۧۗۨ۬ۜ۠ۨ۫ۙۡۡ۟ۥۢۦۘ";
                                        case 2065507168:
                                            str29 = it2.hasNext() ? "ۛۥۢۥۛۖۧ۬ۨۨۗۘۙۡۜۢۚۗۥ۟ۜۜۘۘۖۥۖ۟ۤۘ" : "ۧۨ۠۠۟ۜۘۙۡۙۤۛۧۚ۟۟ۡۙۥ۬ۖ۟ۜۖۤۤۦۡۧ۬ۜۙۤۨۖ۠ۢ۟ۥۦۛۙۖۡ۠۠ۦ۫";
                                    }
                                }
                                break;
                            case 2055083327:
                                break;
                        }
                        Request requestBuild = new Request.Builder().url(strReplaceAsteriskWithRandom).post(builder.build()).header("User-Agent", "shell/cainiao").header("Content-Type", "application/x-www-form-urlencoded").build();
                        z3 = false;
                        int i4 = -1;
                        int i5 = 1;
                        while (true) {
                            String str30 = "ۦۙۚ۠ۤ۠۟ۗۧۘ۫ۘۤۨۜۖۡ۟ۜۗۥۗۨۧۘ۬۬ۗۦ۬ۦۘۗۥۢۨ۫۠ۥۢۖۘۨۗۢ";
                            while (true) {
                                switch (str30.hashCode() ^ 83437682) {
                                    case -1291094448:
                                        try {
                                            Response responseExecute = okHttpClientBuild.newCall(requestBuild).execute();
                                            try {
                                                iCode = responseExecute.code();
                                                String strString = "";
                                                try {
                                                    ResponseBody responseBodyBody = responseExecute.body();
                                                    String str31 = "ۚۤۘۚۜۘۡۨۤ۟ۧۙۡ۠ۜۘۧۗۚۜ۟ۥۘۙۜۦۢۛۦۘۗۧۢۖۚۡ۬ۦ۬";
                                                    try {
                                                        while (true) {
                                                            switch (str31.hashCode() ^ 1817415339) {
                                                                case -911842903:
                                                                    String str32 = "ۛ۠ۗۦۨۚۗ۬ۘۘۛۗۜۙ۬ۙۚۖۦۚۧۚۨۥ۟ۖۛۖ۫ۗ";
                                                                    while (true) {
                                                                        switch (str32.hashCode() ^ 125138995) {
                                                                            case -64311827:
                                                                                str32 = "ۧ۬۟ۢ۬ۚۖۨۤۢۢۜۘ۟۠ۥۗۜۖۘۗۧۘۘۢۢۚۧ۫ۦۘۚۡۨۘۥۧۨۨۥۜۤ۬ۜۚۤ";
                                                                            case 52449287:
                                                                                str31 = "ۚۢۨۘۖۥۜ۫ۤۧۖۨۥۛۙۥۘۘۜۙۡۘۜۘۧۛۚۤۙ۬ۛۙۛ";
                                                                                break;
                                                                            case 1103794124:
                                                                                str32 = responseBodyBody != null ? "ۛۧۤۜ۬۠ۛۗۚۢۚۨۢۦۘۚۧۧۜۢ۟ۛۘۘۘۚۗۤۥۡۛ" : "ۛ۬ۡۘۥۚۙ۫۠ۦۡ۟ۗۙۤ۫ۙۚۚ۠ۚۧ۠ۙۥ۬ۢۢۡۧۤ۟ۡۘ۠ۨۤ";
                                                                            case 1752566354:
                                                                                str31 = "ۧۤۨۘۤۥ۠ۧ۫ۜۘۙۧۜۤۖۨۦۡۗۘۘۜۘۚۚۦۘۥۤۤۡۙۘۘۨۙۘۘ۠ۜ۫ۙ۬ۙۙۥۢۧ۟ۘۘۖۧۡۗۚ۬ۚۜۡۘ";
                                                                                break;
                                                                        }
                                                                    }
                                                                    break;
                                                                case -238079968:
                                                                    str31 = "ۜۛۜ۬ۡۤ۫ۖۘۜۡۘۘۖۢۤ۠ۢۖۥۜۘۙۚۥ۫ۘۘۘ۟ۧۤ۟ۥۗ۠ۘۛۦۦۖۘۧۨۜۚ۫ۘۘۛۢۨ";
                                                                case -220227982:
                                                                    try {
                                                                        strString = responseExecute.body().string();
                                                                    } catch (Throwable th4) {
                                                                        th = th4;
                                                                        str2 = "ۢۥۥ۠ۙۙۧۜۜ۠ۦۡۘۖۜۥۘۛ۫ۥۘ۠۟ۤۢۚۥۥۨ۠ۤ۫ۘۘۘ۬ۜ۫ۦۘۘۢۘ۠ۗ۟ۛۖۥۖۦۙۡۚۛ۫ۜۢۨۘ";
                                                                        while (true) {
                                                                            switch (str2.hashCode() ^ (-1706872566)) {
                                                                                case -1917991824:
                                                                                    break;
                                                                                case -1165689843:
                                                                                    break;
                                                                                case -410278016:
                                                                                    break;
                                                                                case 1776577389:
                                                                                    break;
                                                                            }
                                                                        }
                                                                        throw th;
                                                                    }
                                                                    break;
                                                                case -221055036:
                                                                    String strOptString = new JSONObject(strString).optString("message", strString);
                                                                    String strHeader = responseExecute.header("x-kami-sign-time");
                                                                    String strHeader2 = responseExecute.header("x-kami-sign-token");
                                                                    String strHeader3 = responseExecute.header("x-kami-nonce");
                                                                    str3 = "ۖ۬۟ۢ۬ۖۘۚ۬ۤۗۘۧۘۤۢۛۛۤ۬ۤۛۗ۠ۢ۬ۡۗۥۡۛۡۘۖۛۡۢۨۘۙ۠ۖۘۖۧ۬ۡ۟۫ۡ۠۫ۨ۠ۧۡۨۦ";
                                                                    while (true) {
                                                                        try {
                                                                            switch (str3.hashCode() ^ 1743894429) {
                                                                                case -1015826713:
                                                                                    str3 = "ۥۙۨۜۛۡۜۖۘ۬۫ۥۢ۠۫ۖ۟ۡۥ۟ۡۘۨۦ۬ۛۢۧۜۨۘۘۥۘ۠ۙۘۜ۠۬۠ۛۡۤ";
                                                                                case -44532638:
                                                                                    break;
                                                                                case 437159831:
                                                                                    String str33 = "ۥ۫ۤۡۗۤۗۦۘۢۛۥۘۗۥۘۘۖ۠ۖۛۙۦۖۘۢۡۡ۫ۤۢۡ۫ۜۡۛۨ۠۬ۜ۟۫ۨ";
                                                                                    while (true) {
                                                                                        switch (str33.hashCode() ^ (-859293838)) {
                                                                                            case -821385198:
                                                                                                str33 = !TextUtils.isEmpty(strHeader) ? "۫ۚۡۛ۠ۙۡ۫۬۟ۘ۟ۥ۬ۖۖۨۨۜۡۙۛۤۛۚۘۘۡۨۙۤۙ۬۠۬ۜۘ۟۬۠ۜۡۛ" : "ۤۧۤ۫ۙ۫ۛۥۥۗۚۗۖۚۖۤۢۦۘۗۦۨۘۖۗۡۚۥۘۗۡۗ";
                                                                                            case 86346637:
                                                                                                str33 = "ۢۛۜۛ۬ۤ۫ۘۘۘۛۛۨۘ۬ۡۧۘۡ۫ۘ۟ۚۚۗۚ۬ۙ۟ۥۤۡۘ";
                                                                                            case 754366707:
                                                                                                str3 = "۫ۢۘ۟۟ۤۘۜۥۨ۫ۙۙۨۗۡۙ۫ۘۦۖۘۡۜۡۘۖۗۨۘۥ۫ۜۦ۫ۘۡۢۥ۟ۦۘۥۦۘۘۙۥۤ۠ۨۦۘۢۗۙۘ۫۠";
                                                                                                break;
                                                                                            case 1245133862:
                                                                                                str3 = "ۛۨۘۘۢۨۘۚۛۙۙۖۙۦۜ۫۬ۙۦۘۛۗۚۦۧۜۦۘۖۘۡۖ۫ۦۥۙ۟ۜۘ۟ۧۡۘۧۥۖۧۧۗۜ۫ۡۘ";
                                                                                                break;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                                case 1479192311:
                                                                                    String str34 = "ۗۨ۬ۜۜ۫ۡۨۚۧۘۚۡ۠ۧ۠ۘۘۢۛۧۨۖۜۘۡۛۨۢۤۦۘۨۛ۟ۥ۠ۜۘ۬ۜ۬ۖ۠ۜۘ۫۫ۛۛۥۖ";
                                                                                    while (true) {
                                                                                        switch (str34.hashCode() ^ (-1455722913)) {
                                                                                            case -983045986:
                                                                                                long j = Long.parseLong(strHeader);
                                                                                                String str35 = "ۡۖۜ۫ۛ۫ۤۢ۬۫۠ۡۘ۬ۗۘۘۢۖۥۘۥۘۢۧۘۖۙ۠ۗۥۧ۬";
                                                                                                while (true) {
                                                                                                    switch (str35.hashCode() ^ (-1611046330)) {
                                                                                                        case -1339044116:
                                                                                                            throw new Utils.a("0X2");
                                                                                                        case 1552697550:
                                                                                                            String str36 = "۬ۦۚۙۨۜۘۧ۟ۡۦۖۡۙۧۘۘۚ۬ۦۘۤۤۨۦ۠۠۠۬ۥ۟ۧۦۘۙۙ۠ۤۡۤ۟ۙۗۢۥۜۡۧ۠ۧۗ۬ۨۥۙۜۨۨ";
                                                                                                            while (true) {
                                                                                                                switch (str36.hashCode() ^ 217434315) {
                                                                                                                    case -1838851202:
                                                                                                                        str36 = Math.abs((System.currentTimeMillis() / 1000) - j) <= 86400 ? "ۧۗۨۘۧۦۛۖۡۧۘۘ۠۟ۚۛۡۘۗ۫ۚ۠۫ۜۘۡۘۗۙۨ۫ۛۛۡۘۚ۠ۢۤ۬۫ۢۙ۫۬ۚۦۦۥۡۘۤۢۡۘ" : "۫ۥۘۘۜۙۥۨۚۘۘۗۨۚۖۘۨ۬ۨۡۘۡۥۥۘۡۨۛۤۜۘۘۨۛۨ۫ۚۜ۬۠ۡۢۙۜۜ۫ۜ";
                                                                                                                    case -1195687881:
                                                                                                                        str35 = "۠۫ۢۘۧۧ۠ۛۢۖۢۘۚ۫ۥۧۘۧۘ۬۠ۘۘۨۢ۫ۚۙ۫ۧۦ۠ۜۗ۫۠ۚ۠ۤۜۥۘ۬ۥۦۦۦۘۙۧۖۚۤۤ۬ۖۨۘ";
                                                                                                                        break;
                                                                                                                    case -205758956:
                                                                                                                        str36 = "ۚۗۡۛۖۗۖۘۦۛۧ۬ۚۢۡۘۚۖۡۧۚۧۜۨۦۘۜۥۖۜۗۤ";
                                                                                                                    case 607055891:
                                                                                                                        str35 = "ۚۚۦۘ۟ۦۧۙۜۘۛۦۜۘ۟ۢ۠ۗۢۨۖۢۥۘۗۛۗۧۜۗۡۡۦ";
                                                                                                                        break;
                                                                                                                }
                                                                                                            }
                                                                                                            break;
                                                                                                        case 1966528092:
                                                                                                            str35 = "۠ۦۤۜۥ۟ۧ۫ۖ۬ۢۛۛ۠ۨۜۡۘۡۛۢ۟ۜۦۘۤۦۨۘۘ۫۫ۦۗۛۦۜ۟";
                                                                                                        case 2008146927:
                                                                                                            StringBuilder sb4 = new StringBuilder();
                                                                                                            try {
                                                                                                                try {
                                                                                                                    String str37 = (String) map5.get("input");
                                                                                                                    String str38 = "ۢۙۦۘۨۚۡۢ۬ۤۙۚۦۙۖۜۘۙ۫ۢ۠ۨۜۘۖۧ۫ۧۦۦ۟ۜۨۧ۬ۡ۫۟ۢۧ۠ۖ۬۫ۘۘۚۚ۫ۦۚۨ";
                                                                                                                    while (true) {
                                                                                                                        switch (str38.hashCode() ^ 278366187) {
                                                                                                                            case -2082225804:
                                                                                                                                String str39 = "ۖۧۚۤ۟ۚۤۙۖۧۥۨۘ۠۟ۥۘۜۢۚۧۦۗ۬ۘۡۘۗۦۦۘۗۥۜۘۨۢۢۗ۫";
                                                                                                                                while (true) {
                                                                                                                                    switch (str39.hashCode() ^ (-2000425817)) {
                                                                                                                                        case -1948003734:
                                                                                                                                            str38 = "ۧۡۧۘۦۖۛۥۜۦۘ۠ۦۜۘۥۥۡۘۦ۟ۛۧۘۙۦۡۛۡۢۘۛۘۖۨۡ۫ۤۚ۠ۢۛۗۙۖ";
                                                                                                                                            break;
                                                                                                                                        case -1603397984:
                                                                                                                                            str38 = "ۧۡۦۦۛۨ۬ۧۨۘۨۜۘۛ۬ۛ۬۬ۧۦ۬۟ۦ۠۟ۧۧۗۚۦۜۘۛ۠ۥ۫ۚ۬ۙۧ۬ۙۛۨۘ";
                                                                                                                                            break;
                                                                                                                                        case -423392612:
                                                                                                                                            str39 = "ۙۢۢۧۚۢۥۜۘ۠۟ۘۗۖۘۘۧۜۘۘ۫ۘۨۜۨۥ۬۟ۦۦ۬۠";
                                                                                                                                        case 199908568:
                                                                                                                                            str39 = str37 == null ? "ۘۘۦۘ۫ۖۘۦۢۦۥۧۛۗ۬ۘۥۥۗ۬ۖۘۦۤۜۘۥۜۖ۠ۦۦ۬ۡۘ۠ۥ۫ۢۡ۠۠ۜۡۘۧۦ۟۟ۤۙۡ۬ۙ۟ۗۧ" : "ۚۛۢۜ۟ۤۥ۠۠ۥۚۦۢۦۢۚۖۡۘۛۥ۫۠ۦۛۘۢۜۛۛۜۦۡۤۜۡۘ";
                                                                                                                                    }
                                                                                                                                }
                                                                                                                                break;
                                                                                                                            case -1103094142:
                                                                                                                                break;
                                                                                                                            case 1021972868:
                                                                                                                                str38 = "۬ۦۗۥۦ۟ۙۥۦۥۡۥۗ۟ۤۛ۫ۦۦ۟ۥ۬ۜۚۥۘۛۤۜۘۗۢۥۘ۟ۜۘۖۥۛۡ۫ۥۘ";
                                                                                                                            case 1676954503:
                                                                                                                                str37 = "";
                                                                                                                                break;
                                                                                                                        }
                                                                                                                    }
                                                                                                                    sb4.append(str37);
                                                                                                                    try {
                                                                                                                        String str40 = (String) map5.get("appId");
                                                                                                                        String str41 = "ۚۛۥۘۜۧ۟ۢ۬ۨ۫ۜۜۙۘۙ۟ۡۗۛ۫ۢۢ۟ۚۤۨۜۘۛۡۦ۬ۖ۬ۧۢۙ۠ۜۡۡۥۤۢۡۨۖۗۜۥ۟ۤۡۢ";
                                                                                                                        while (true) {
                                                                                                                            switch (str41.hashCode() ^ (-1539745830)) {
                                                                                                                                case -876524561:
                                                                                                                                    String str42 = "ۜۗۚۘۦۖۤ۠ۡۘۖۖۜۘ۫ۥۖۘۨۖۖۘۗ۬ۘۥۨۧۘۥۜۜۖۖ۫۟۠ۦۡۖۥ";
                                                                                                                                    while (true) {
                                                                                                                                        switch (str42.hashCode() ^ (-1153166004)) {
                                                                                                                                            case -1999033385:
                                                                                                                                                str42 = "ۜۥ۫۟ۗۘۘ۠ۡۨۘۨۘۘۘۙۦۘۘۛۗۡۘۧۨۖۗ۠ۨۢۤۡۗۨۧۘ";
                                                                                                                                            case -1541650064:
                                                                                                                                                str41 = "ۡۙۡۢۙۧۚۜۜۘۨۗۧۛۗۥۡ۟ۨۘۗۛۧۙۡ۠ۖۧ۫۬ۚۘۘۙۦۦۘۨ۟ۦ";
                                                                                                                                                break;
                                                                                                                                            case 233988232:
                                                                                                                                                str42 = str40 == null ? "ۖ۠ۤ۟۫ۢۡۘۧۘۤۧ۠ۢۢۘۡۚۨۥۥۜۤۨ۠ۖ۟ۖۘۤ۫ۨۘۡۦۘۘۧ۟۠۫ۛۥۘۦۛۤ" : "ۢۗۖ۫ۨۧۘۗۗۡۘۤۤۦۘۖۦۨۥۧۘۧ۫ۡۘۜ۬۬ۛۗ۫ۦۧۖۘۢۢۖۘۗۡۘۨۡۨۘۘۛۤۥ۠۠۠ۙ۬";
                                                                                                                                            case 569483405:
                                                                                                                                                str41 = "۬ۜۦۧۚۖ۟ۘ۠ۚۥ۬۬ۛۚۦ۫ۜۥ۟۟ۙۜۛۦ۠ۡۤۡۡۘۖۢۙۚۜۖۥۡۗۢۗۘۛۡۘۜۙۘۘۜۧ۫ۥۘ";
                                                                                                                                                break;
                                                                                                                                        }
                                                                                                                                    }
                                                                                                                                    break;
                                                                                                                                case -573964701:
                                                                                                                                    str40 = "";
                                                                                                                                    break;
                                                                                                                                case -237769733:
                                                                                                                                    str41 = "ۜۧۜ۠ۛۗ۫ۥۖۧۧۡۘۧۤۥۘۧ۠ۢۖۦ۠ۥۙۙۖۛۚۖۡۢۜۖ۠ۡۚۥۘۙۘۢۖ۠ۜ";
                                                                                                                                case 1770903218:
                                                                                                                                    break;
                                                                                                                            }
                                                                                                                        }
                                                                                                                        sb4.append(str40);
                                                                                                                        try {
                                                                                                                            String str43 = (String) map5.get("deviceId");
                                                                                                                            String str44 = "ۜۙۤ۠ۦۨ۟ۦۥۘۧ۫ۖۗۦۧۘ۫ۚۨۘۙ۠ۡۨۛ۟ۘۥۡۘۗۘۥ";
                                                                                                                            while (true) {
                                                                                                                                switch (str44.hashCode() ^ (-762729279)) {
                                                                                                                                    case -834440267:
                                                                                                                                        str43 = "";
                                                                                                                                        break;
                                                                                                                                    case 303326853:
                                                                                                                                        break;
                                                                                                                                    case 1100002035:
                                                                                                                                        String str45 = "ۥۚۤۥۜۢۙۧۡۤۡۧۘۢ۬۟ۥۧ۟ۘۡۡۦۘۖ۬ۖۛۗ۬ۡۚۡۥۘۤۢۘۘۙۡ۠ۙ۬ۡۘ۫ۗ۟ۤ۠ۨۘۥۙۤۖۢۖ";
                                                                                                                                        while (true) {
                                                                                                                                            switch (str45.hashCode() ^ (-1339667792)) {
                                                                                                                                                case -1534659913:
                                                                                                                                                    str45 = "۫۟ۘۧۢۤۘۢۚۥۡۖۘۦۘۘۘۡۨۤۦۦ۫ۚ۠ۡۗۜۗ۬ۦۤۦ۠ۙۗۧۥ";
                                                                                                                                                case -1518303824:
                                                                                                                                                    str44 = "ۗۗۥۘۤۚۥۘ۫ۘ۫ۤ۬ۡۘۦ۟ۤۦ۬ۖ۫۬ۘۘۢۥۜۘ۠ۙۥۘۜ۟ۙۘۘۦۢۛۘۘۧ۠ۤۙۗۘ۟۫۠ۦۘ";
                                                                                                                                                    break;
                                                                                                                                                case -376173933:
                                                                                                                                                    str45 = str43 == null ? "ۤۚۨۘۢ۟ۥۛۛ۟ۗ۠ۗۨۧۚ۠ۧۙۛۗۜۘۖۚۜۖۛۜۚۦۦۘۢۧۧ۫ۛۛۗ۫ۘۢ۫" : "۟۬۟۠۟ۖۖۙۚۜۨۜۘۛۦۙۥۙۖۘ۫۠۬ۤۦۥۘۙۡۧۙۚۗۢۡۖۘۤۜۥۙۖۘ۟ۢۗ۫ۡ۬۬ۨۨۘ";
                                                                                                                                                case 415369574:
                                                                                                                                                    str44 = "ۛۖۖۙۘۜۛ۠ۦ۠۬۠۠۟۫ۘۡۦۘۨۧۚۤۢ۟ۥ۫ۥۘۨۗ۬ۢ۫۠۠ۤۨۘۜۚۜۚۜۨۛۤۜۜۘۦۤۧۙۙۥ";
                                                                                                                                                    break;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        break;
                                                                                                                                    case 1653932637:
                                                                                                                                        str44 = "ۜ۬ۜ۠ۛ۬ۦۘ۟ۜۦۖۘۧۦۥۢۢۦۘۖ۟ۥۘۛۤ۠ۙۦۚۗۤۘ۫ۘۧ۟ۧۡ۟ۥۨۘۨ۟ۥۘ";
                                                                                                                                }
                                                                                                                            }
                                                                                                                            sb4.append(str43);
                                                                                                                            sb4.append(strHeader);
                                                                                                                            sb4.append(strHeader3);
                                                                                                                            sb4.append(strOptString);
                                                                                                                            String str46 = "۠ۚۨۙۗۘۖۧۜۛۛ۫۫ۗۚۡۥ۠۫ۗۢۧۤۤۛۦۗۧۖۡ۫ۥۡۢۜۤ۠ۥۜۘۤۡۙۜۜۡۘۗۜۧ";
                                                                                                                            while (true) {
                                                                                                                                switch (str46.hashCode() ^ 116610311) {
                                                                                                                                    case -371027220:
                                                                                                                                        throw new Utils.a("0X3");
                                                                                                                                    case -364622237:
                                                                                                                                        String str47 = "ۡۙۧ۠ۗۤ۠ۛۘۘۜۚۥۘۦ۬۫ۘۙۧۚۚۤO۫ۜۡ۠۫ۦۨ۫ۡۡۛۖ";
                                                                                                                                        while (true) {
                                                                                                                                            switch (str47.hashCode() ^ 39277752) {
                                                                                                                                                case -1682080605:
                                                                                                                                                    str47 = "۫ۧۨ۟ۦۨ۬ۨۨۦ۬ۥۦۥۤ۠ۥۘۗۙۜۘۥۜۨۘۧۜۧۖۖۦۘ";
                                                                                                                                                case -448616768:
                                                                                                                                                    str46 = "ۗۢ۬ۙۧۡۨ۟ۗۧۚۖۘ۬ۦ۫۟۟ۦۘۙۧۡۘۙۚۙۢ۟ۦۘۘۖ۬ۡۨۦۦۘ۫ۤۖۡۘۨۦۘ";
                                                                                                                                                    break;
                                                                                                                                                case 1375651441:
                                                                                                                                                    str46 = "ۢ۬۠ۦ۟ۨۜۢۥۡۜ۟ۨۦۡۘۚۜۥۘۦۗ۬۟ۡ۬ۨۙۧۙۨۦ";
                                                                                                                                                    break;
                                                                                                                                                case 2100562663:
                                                                                                                                                    str47 = Utils.md5(sb4.toString()).equalsIgnoreCase(strHeader2) ? "ۘۚۢۛۙۛۛ۟ۢۖۗۥۘ۬ۤ۠ۛ۠ۙۗۧ۫ۗۨۨۦۦۥۘۨ۬ۜۘۘۦۧۘۜۧۡ۠ۘۧۘۙۡۥۛۢ۬ۧۗۥۘ" : "ۗ۟ۥۘ۬ۖۘۘ۫ۧۙۘۛ۠ۧۗ۠ۥۖۖ۫ۧۚۨۦۘۗۡۨۘۛ۠ۘۙۡۡۘ۫ۖۥۢۢۖۘ۫ۘ۟";
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        break;
                                                                                                                                    case -173604961:
                                                                                                                                        boolean zEquals = strString.equals("");
                                                                                                                                        String str48 = "ۖۧۧۥۧۨۖۙ۠ۚۡ۬۠۠ۨۨۖۘۥ۬ۡۘۨ۫ۢ۬۠۬ۜۦۛۙۚۡۦ۬";
                                                                                                                                        while (true) {
                                                                                                                                            switch (str48.hashCode() ^ (-981448979)) {
                                                                                                                                                case -1512828207:
                                                                                                                                                    try {
                                                                                                                                                        sb3.append(strString);
                                                                                                                                                        break;
                                                                                                                                                    } catch (Throwable th5) {
                                                                                                                                                        th = th5;
                                                                                                                                                        th = th;
                                                                                                                                                        str2 = "ۢۥۥ۠ۙۙۧۜۜ۠ۦۡۘۖۜۥۘۛ۫ۥۘ۠۟ۤۢۚۥۥۨ۠ۤ۫ۘۘۘ۬ۜ۫ۦۘۘۢۘ۠ۗ۟ۛۖۥۖۦۙۡۚۛ۫ۜۢۨۘ";
                                                                                                                                                        while (true) {
                                                                                                                                                            switch (str2.hashCode() ^ (-1706872566)) {
                                                                                                                                                                case -1917991824:
                                                                                                                                                                    break;
                                                                                                                                                                case -1165689843:
                                                                                                                                                                    break;
                                                                                                                                                                case -410278016:
                                                                                                                                                                    break;
                                                                                                                                                                case 1776577389:
                                                                                                                                                                    break;
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                        throw th;
                                                                                                                                                    }
                                                                                                                                                case -839880236:
                                                                                                                                                    str48 = "ۛ۬ۢۖۥۥۦ۠ۥۘۦۘۙۜۛۖۦۘۧۘۦ۫ۙۚۢۨ۠۠ۥۘۚۛۗۧۥۘۢۘۢ۟۫ۥ۟ۙ۟ۘۥۘۥۙۚۙۜۘ۬ۦ۠";
                                                                                                                                                case 614704819:
                                                                                                                                                    break;
                                                                                                                                                case 1807049387:
                                                                                                                                                    String str49 = "ۥۦۖۘۖۘۢۢ۫ۤۚۢۤۛۧۦۘۚۧ۟ۖۡۥۘۧۥ۠۬۫ۡۘۧۖۜۘ";
                                                                                                                                                    while (true) {
                                                                                                                                                        switch (str49.hashCode() ^ 507810907) {
                                                                                                                                                            case -1334169772:
                                                                                                                                                                str49 = !zEquals ? "ۙۤ۬ۙۛ۬ۛۢۡۘۗۜۧۘۤ۠ۙۘۖ۠ۛۤ۬ۢ۬ۧۢۦ۟ۤۖۜۘ" : "ۦۘ۬ۚۨۤ۟ۥۦۘۨ۟ۙۖۙۛۤۙۙ۠ۦۡۘۨۙۧۢۡۗۗۙۜ";
                                                                                                                                                            case 46378006:
                                                                                                                                                                str49 = "۠۫ۨۛ۠۬ۘۙ۟ۧۢۨۡۗ۬۬۟ۦۘ۫ۖۥ۠۠ۚۨۘۙۦۘ";
                                                                                                                                                            case 641916354:
                                                                                                                                                                str48 = "۬ۘۛ۠ۡۖۘۤۥ۬ۢۤۤۗ۟ۦۘۛ۫ۘۘۘۗۧۛ۠۟ۦۦۦۡۤ۬ۘ۬۠ۡۙ۫ۨ۠ۨۦۥ۬ۢۥۗۖۡۤ۟۠ۢۨۛۜ";
                                                                                                                                                                break;
                                                                                                                                                            case 1013285722:
                                                                                                                                                                str48 = "ۨۗۚۜۦۨۖۡۨۙ۫۫ۨ۫۠ۜۤۤۙ۟ۛۡۜۧۘۥۙ۬ۨۧۥۙ۠ۦۘۛ۫ۧۨ۟ۨۘۢ";
                                                                                                                                                                break;
                                                                                                                                                        }
                                                                                                                                                    }
                                                                                                                                                    break;
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        Headers headers = responseExecute.headers();
                                                                                                                                        Iterator<String> it3 = headers.names().iterator();
                                                                                                                                        while (true) {
                                                                                                                                            boolean zHasNext = it3.hasNext();
                                                                                                                                            String str50 = "ۡۨۨۘۡۗۢۦۜۧۘۢۘۘۙۛۙۡ۠۠۫ۜۘۜۦۤۧۜۦۘۛ۫ۘ";
                                                                                                                                            while (true) {
                                                                                                                                                switch (str50.hashCode() ^ 174442156) {
                                                                                                                                                    case -371733806:
                                                                                                                                                        String str51 = "ۜۜۧۛۡۥۡۗ۬ۗۙ۬ۨۚۗۢۧۨۘۨ۬ۙ۟ۗۢ۫ۚۢۡۘۘ";
                                                                                                                                                        while (true) {
                                                                                                                                                            switch (str51.hashCode() ^ (-1632489794)) {
                                                                                                                                                                case -1774802752:
                                                                                                                                                                    str50 = "۠۫ۨۘۚۥ۠ۤۗۦۘۨۥۘۨۡۦۛۘۥ۠۫ۧۢۤۤۘۨۧۢۖ۠ۚ۟ۗۨۘ";
                                                                                                                                                                    break;
                                                                                                                                                                case -1147283800:
                                                                                                                                                                    str50 = "۬۟ۦۘۦۛۦۘۜۤۛ۫ۛۘ۠ۦۤۜۧۘۘۖۗۡۘۜ۫ۡۘۡۖ۫ۦ۫۫ۥۢ۟۠۠۬ۨ۟ۖ۟ۙۘۘ";
                                                                                                                                                                    break;
                                                                                                                                                                case -143314577:
                                                                                                                                                                    str51 = zHasNext ? "ۖۦۧ۫۫ۢ۫ۜۘۖۗۙ۬۠ۨۘۚۨۧۘ۫ۦۖ۬ۖۦۢۘ۠ۜۦۛۡۦۜ۟ۜۘۗۤۡۘ۬ۥۘ" : "ۘۤۚۖۙۡۘ۬ۧ۟ۨۘۘۧۚۧۤ۟ۖۘۢ۟ۡ۠ۧۦۚۚۚۢۜ۬ۢۘۥ۠۫ۘ";
                                                                                                                                                                case 39385134:
                                                                                                                                                                    str51 = "ۡۡۤ۟۬۫ۖۧۢۡۡۤ۟ۙۡۘ۫ۘۦ۟ۤ۟ۗۤ۟ۗۤۘۘۙۙۚۤۛۦۘۤۘۙ۠۫ۙۗۥۘ";
                                                                                                                                                            }
                                                                                                                                                        }
                                                                                                                                                        break;
                                                                                                                                                    case 899342709:
                                                                                                                                                        try {
                                                                                                                                                            try {
                                                                                                                                                                JSONObject jSONObject = new JSONObject(sb3.toString());
                                                                                                                                                                String str52 = "۫ۙۡۘ۠ۥۖۜۢۥۘ۠ۦۥۘۚ۟ۦۜۦۖۘۨۡۡۦۗ۬۬ۢۙ۬ۧۤۨۚۢۖۜۢۙۛۖۘۚۥۗۖۙۘۚۚۤ";
                                                                                                                                                                while (true) {
                                                                                                                                                                    switch (str52.hashCode() ^ (-1965520230)) {
                                                                                                                                                                        case -557800710:
                                                                                                                                                                            str52 = "ۘۨۨۙ۟ۜۨۖ۫ۨۛۗۦۢ۫ۘۨۤ۠ۙۨۧ۠ۖۦۡ۫ۦۚۡۘۚۧۜۜۗۦۘ";
                                                                                                                                                                        case -314832807:
                                                                                                                                                                            break;
                                                                                                                                                                        case 152892409:
                                                                                                                                                                            String str53 = "ۖۙۢۜۡۧۘۤ۫ۨۘۧۤ۟ۙ۠ۨ۫۬ۧ۠ۢۜ۠ۗۜ۫ۚۘۧ۟ۗ۠۟ۘۘۗۥۘۘۗۘۚۙ۬ۥۘۡ۟ۡۘۢۡۛ";
                                                                                                                                                                            while (true) {
                                                                                                                                                                                switch (str53.hashCode() ^ 16712340) {
                                                                                                                                                                                    case -1409046688:
                                                                                                                                                                                        str53 = "ۖۙۡۤۘۡۙۘ۠ۙۗۢۚۛۥۦۙ۠۟ۥۧۚۢۚ۫ۚۡۡ۬ۚ۠ۥۛۥۧۘۘ";
                                                                                                                                                                                    case -406720958:
                                                                                                                                                                                        str52 = "ۡۚۥۘ۟ۜۦۘ۫ۖۘۛۛۦۥۥۙ۫ۚۤ۟ۢۖۘۘۨۘۖۘ۟ۖ۟ۨۘ";
                                                                                                                                                                                        break;
                                                                                                                                                                                    case 950721595:
                                                                                                                                                                                        str52 = "ۤ۠ۜۤ۫ۚۗ۠ۘۘ۠ۧۚ۬۫ۨۨۜۖۨۦۙۤ۫ۜۗۛۖۗۜۖ۬ۥۢۤۤۘۘ";
                                                                                                                                                                                        break;
                                                                                                                                                                                    case 1725433957:
                                                                                                                                                                                        str53 = jSONObject.has("code") ? "ۖۧۡۘۢۚۧۛۥۡۘ۟ۢۤۛۚۡۘۖۚۦۘۚۚ۫ۘ۫ۡۘۢ۫ۥۙۖۤۙۡۖۘۖۖۜۘۥۜۚ۟۫ۗۤۜۜۜۤۡۘۧ۟ۖۗۥۖۘ" : "۫ۢ۟ۨ۠ۨۘۚۨۖۘۖۦۥ۟ۚۢۤۧۢ۫ۤۤۧۨ۫ۦۡۘۘۦ۠ۗ۫ۛۜۘۙۚۥۘۧۢۜۚۘ۟";
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                            break;
                                                                                                                                                                        case 340385155:
                                                                                                                                                                            String str54 = "ۗۗۡۘۧۦۡۘۤۖۥ۫۫ۚۤۦۥۥ۫ۥۘۗۜ۠۬ۡۧ۠ۖۚۥ۠ۛۨۗۧۢۦۙۖۤۦۘۧۖۦ۟ۡ۟۬ۜ۫ۦۥۥۘ۟۬ۜۘ";
                                                                                                                                                                            while (true) {
                                                                                                                                                                                switch (str54.hashCode() ^ (-2090414953)) {
                                                                                                                                                                                    case -174974545:
                                                                                                                                                                                        str54 = "ۤۡۗۜۜۘۘۦۘۨۘ۫ۡ۠۬ۚۛۚۧ۬۫۠ۡۤ۫ۦۘۡۦ۟۬ۦۖۗۚۦۘۨۜۡ۫ۦۦۜۧۚ";
                                                                                                                                                                                    case 32685538:
                                                                                                                                                                                        jSONObject.getInt("code");
                                                                                                                                                                                        jSONObject.optString("message", "");
                                                                                                                                                                                        JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
                                                                                                                                                                                        String strDecrypt = "-1";
                                                                                                                                                                                        String str55 = "ۛ۟ۨۙۥۦۚۙۖۘ۠ۥۖ۫ۢۢۨۚۘۘۗۡۡۨۡۥۘۡۢۡ۫ۙۜۖۘۧۚۙۚۜۦ۠ۘۖۤ";
                                                                                                                                                                                        while (true) {
                                                                                                                                                                                            switch (str55.hashCode() ^ 591275774) {
                                                                                                                                                                                                case -446781024:
                                                                                                                                                                                                    long j2 = Long.parseLong(jSONObjectOptJSONObject.optString("remaining_seconds", strDecrypt).trim());
                                                                                                                                                                                                    String str56 = "ۛۚ۫ۜۜۜۘۛ۬ۦۛۗۜۘ۟ۛۡۦۙۜ۫ۧ۬ۢۖۨۡ۫ۦۘۖۖ۫";
                                                                                                                                                                                                    while (true) {
                                                                                                                                                                                                        try {
                                                                                                                                                                                                            switch (str56.hashCode() ^ (-892405860)) {
                                                                                                                                                                                                                case -1902258791:
                                                                                                                                                                                                                    try {
                                                                                                                                                                                                                        Utils.startCountdown(j2, new x2(), new a(15));
                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                    } catch (Throwable th6) {
                                                                                                                                                                                                                        th = th6;
                                                                                                                                                                                                                        th = th;
                                                                                                                                                                                                                        str2 = "ۢۥۥ۠ۙۙۧۜۜ۠ۦۡۘۖۜۥۘۛ۫ۥۘ۠۟ۤۢۚۥۥۨ۠ۤ۫ۘۘۘ۬ۜ۫ۦۘۘۢۘ۠ۗ۟ۛۖۥۖۦۙۡۚۛ۫ۜۢۨۘ";
                                                                                                                                                                                                                        while (true) {
                                                                                                                                                                                                                            switch (str2.hashCode() ^ (-1706872566)) {
                                                                                                                                                                                                                                case -1917991824:
                                                                                                                                                                                                                                    break;
                                                                                                                                                                                                                                case -1165689843:
                                                                                                                                                                                                                                    str2 = "ۨۙۘۘ۫ۤۗۦ۠۠۬ۘۚۙ۠ۜۦۦۡۚۦۜۘ۫ۖۘۘۙۜۜۥۚۤ";
                                                                                                                                                                                                                                case -410278016:
                                                                                                                                                                                                                                    try {
                                                                                                                                                                                                                                        responseExecute.close();
                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                    } catch (Throwable th7) {
                                                                                                                                                                                                                                        try {
                                                                                                                                                                                                                                            th.addSuppressed(th7);
                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                        } catch (Exception e7) {
                                                                                                                                                                                                                                            i3 = iCode;
                                                                                                                                                                                                                                            e2 = e7;
                                                                                                                                                                                                                                            try {
                                                                                                                                                                                                                                                k2.logToFloatingWindow("第 " + i5 + " 次连接失败：" + e2.getMessage(), "warning");
                                                                                                                                                                                                                                                str = "ۚ۠ۖۚۜۗۙۘۨۜۥۘ۫ۦۥۙۘۘۖ۠ۦ۟ۚۤ۫ۡۥۘۘۡۦۘۘۢۦ۠ۤۡۘۖۜۛۗ۠ۘۘۤ۬ۜۘۦۢۤ";
                                                                                                                                                                                                                                                while (true) {
                                                                                                                                                                                                                                                    switch (str.hashCode() ^ 774184049) {
                                                                                                                                                                                                                                                        case -1884365497:
                                                                                                                                                                                                                                                            sb3.append("请求失败01: ");
                                                                                                                                                                                                                                                            sb3.append(e2.getMessage());
                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                        case -1792297956:
                                                                                                                                                                                                                                                            str = "ۧۖۨۥۡۥۗ۟ۧۚۤۛۥ۟۬ۖ۠ۚ۠ۖ۟ۤۘۜۘۜ۟۫ۖۖۘۗۡۜۗۥۤۘۦۙ۟ۦۚۡ۬۫۬ۨۘۤۚۛ۟ۗۢ";
                                                                                                                                                                                                                                                        case -1543512637:
                                                                                                                                                                                                                                                            String str57 = "ۜۥۡۨ۟ۡۘۨۜۥۖۨۜۢۘۘۚ۠ۨۗۨۧۘۗۘۦ۟۠ۘۥۥۥۢۨۦۘۜۛۡ۟ۘۧۗ۟۠";
                                                                                                                                                                                                                                                            while (true) {
                                                                                                                                                                                                                                                                switch (str57.hashCode() ^ 1608266858) {
                                                                                                                                                                                                                                                                    case -1561763327:
                                                                                                                                                                                                                                                                        str = "ۧۧ۟ۤ۬ۙ۫ۖۢۖۚۚ۬ۗۡۘۧۗۡۤۜ۫ۘۜۦۨۢ۠ۚۤۥۙ۫ۘۗۤۨۧۚۦۘۢۜۨۘ۟ۡۧۥۚۡ";
                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                    case -229298904:
                                                                                                                                                                                                                                                                        str57 = i5 == 3 ? "۠۟ۙۚۦۡۘ۟ۙۖۙۨۛۨۗۘ۬ۗ۠ۖۜۗۥۦۖۘۖۘۚۦۙۡۙۘۘۚۤ۫" : "ۥۢۨۦۨۜۘ۫ۗ۫ۘۚۙ۬۠۫ۖۨۜۚۚۖۥۡۨۘ۬ۘۥۜۖ";
                                                                                                                                                                                                                                                                    case 551756015:
                                                                                                                                                                                                                                                                        str = "ۨۖۘۘۛۡۡۘۧۖ۟ۡۛۨۘۨۖۨۙۡۖۘۤۢۜۘۦۧۢۛۚۗۙۨۖ۠۠ۢۥۤۘۗۢۦۤۛۖۨۜۗۦۗ۟";
                                                                                                                                                                                                                                                                        break;
                                                                                                                                                                                                                                                                    case 910809395:
                                                                                                                                                                                                                                                                        str57 = "ۗۧۙۛ۫ۙۤ۟ۢۥۙۨۡ۟ۦۘۧۗۧ۫ۦۥۘۖۨۨۜۖۚۙۖۦۧ۠ۧ۠ۘۖۘ۫ۜ۠ۗۜۧۘ۬۫ۙۡ۫۟ۡ۠ۥۘۘۤۘ";
                                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                        case 2146245558:
                                                                                                                                                                                                                                                            break;
                                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                                i4 = i3;
                                                                                                                                                                                                                                                i5++;
                                                                                                                                                                                                                                            } catch (Exception e8) {
                                                                                                                                                                                                                                                e = e8;
                                                                                                                                                                                                                                                e.printStackTrace();
                                                                                                                                                                                                                                                k2.logToFloatingWindow(h.d("wNkNxlqTOhOkqQGaCLxF\n", "Jky5IucA368=\n", new StringBuilder(), e), "warning");
                                                                                                                                                                                                                                                sb3.append("请求失败02: ");
                                                                                                                                                                                                                                                sb3.append(e.getMessage());
                                                                                                                                                                                                                                                iCode = i3;
                                                                                                                                                                                                                                                objArr[0] = new Object[]{Boolean.valueOf(z3), Integer.valueOf(iCode), map6, sb3.toString()};
                                                                                                                                                                                                                                                synchronized (Utils.e) {
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                        } catch (Utils.a e9) {
                                                                                                                                                                                                                                            e = e9;
                                                                                                                                                                                                                                            i3 = iCode;
                                                                                                                                                                                                                                            try {
                                                                                                                                                                                                                                                k2.logToFloatingWindow("签名校验失败，终止重试：" + e.getMessage(), "warning");
                                                                                                                                                                                                                                                sb3.setLength(0);
                                                                                                                                                                                                                                                sb3.append("请求失败03: ");
                                                                                                                                                                                                                                                sb3.append(e.getMessage());
                                                                                                                                                                                                                                            } catch (Exception e10) {
                                                                                                                                                                                                                                                e = e10;
                                                                                                                                                                                                                                                e.printStackTrace();
                                                                                                                                                                                                                                                k2.logToFloatingWindow(h.d("wNkNxlqTOhOkqQGaCLxF\n", "Jky5IucA368=\n", new StringBuilder(), e), "warning");
                                                                                                                                                                                                                                                sb3.append("请求失败02: ");
                                                                                                                                                                                                                                                sb3.append(e.getMessage());
                                                                                                                                                                                                                                                iCode = i3;
                                                                                                                                                                                                                                                objArr[0] = new Object[]{Boolean.valueOf(z3), Integer.valueOf(iCode), map6, sb3.toString()};
                                                                                                                                                                                                                                                synchronized (gTBLD.dev.XSSTG.free.Utils.e) {
                                                                                                                                                                                                                                                }
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                            iCode = i3;
                                                                                                                                                                                                                                            objArr[0] = new Object[]{Boolean.valueOf(z3), Integer.valueOf(iCode), map6, sb3.toString()};
                                                                                                                                                                                                                                            synchronized (gTBLD.dev.XSSTG.free.Utils.e) {
                                                                                                                                                                                                                                            }
                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                    break;
                                                                                                                                                                                                                                case 1776577389:
                                                                                                                                                                                                                                    String str58 = "ۖ۬ۨۘۦ۫ۙۚۙۧ۟۟ۘ۬ۨۘۛۘ۟ۦۖۦۘۡۧۦۘۢۚ۬ۥۖۖۘۧ۠ۦۘۥ۟ۖۛۘۧۖۗۖۘۚۥۧۘۙ۫ۘ";
                                                                                                                                                                                                                                    while (true) {
                                                                                                                                                                                                                                        switch (str58.hashCode() ^ 1285765800) {
                                                                                                                                                                                                                                            case -1218823357:
                                                                                                                                                                                                                                                str2 = "ۙ۟۟۠ۚ۬۟ۤۡ۟ۛۦۘۜۚۥۘۙ۟ۥۧۧ۬ۖۜۙۗۨۥۦۧ۫";
                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                            case -481463786:
                                                                                                                                                                                                                                                str58 = "ۡۥۤۙۙۤۗۨۚۡ۟ۖۡۚۡۘۙۘۥۘۢۢۚ۠ۨ۟ۚۢۦۘۤ۠۟ۜۗۥۤۥۥۜۜۜ۟ۘۙ";
                                                                                                                                                                                                                                            case -424228755:
                                                                                                                                                                                                                                                str58 = responseExecute != null ? "ۦۙۥۜۥۨ۬ۗۨۚۡۖۘۛ۠ۦۘ۠ۜۜۘ۫ۜۘۘۤۥۙۜۦۛۛۢۨۖۡۥۘۖ۬ۘۘۖ۫ۢۚۜۦۘ" : "ۖۖ۫ۢۜۘۛۥۚۙ۬۬ۤ۫۬ۦۢۢۗۜ۫۠ۨۛ۬۠۫ۗۖۘۜۦۘۘۡ۠۠";
                                                                                                                                                                                                                                            case 23829021:
                                                                                                                                                                                                                                                str2 = "ۖ۬ۙۥ۫ۨۥ۬ۖۨ۬ۜۘۡۥۡۘۛ۟ۘۘۦۛۛ۬ۖۥۘ۠ۢ۟ۡۙۨ";
                                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                                        }
                                                                                                                                                                                                                                    }
                                                                                                                                                                                                                                    break;
                                                                                                                                                                                                                            }
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                        throw th;
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                    break;
                                                                                                                                                                                                                case 26595198:
                                                                                                                                                                                                                    str56 = "ۜۢۘۡۜۗۢۙۖ۫ۚۨ۬۟ۜۜۛۖ۫۬ۧۖۥ۬۬ۚۖۛۚۧۡ۟ۡۘۢ۠ۤۧ۟ۡ۫۬ۨ۬ۤۜ۠ۙۜۜۖۤۤۤ";
                                                                                                                                                                                                                case 1154040126:
                                                                                                                                                                                                                    String str59 = "ۡ۠۠ۡ۠ۢۖۢۗۧۛ۬ۚۛۥۚۦۘۤۧۨۦۚۧ۫ۨ۬۬۠ۢ۟ۦۨۛۖ۫ۜۜ۫ۜۧۧۨۡۨۢۖۖۘۡۦۧۥۖۡۘ";
                                                                                                                                                                                                                    while (true) {
                                                                                                                                                                                                                        switch (str59.hashCode() ^ (-123544178)) {
                                                                                                                                                                                                                            case -907974468:
                                                                                                                                                                                                                                str59 = "۟ۙۨۘۧۘۛۖۥۗۨۚۘۙۢۡۤۨۧۘۗۥۢۘۥۛۘۡۜۗۨ";
                                                                                                                                                                                                                            case -774076281:
                                                                                                                                                                                                                                str59 = j2 > 0 ? "ۖۛۘۛۗۡۘۜۧ۫ۦ۬ۨۤ۫ۦ۠ۤۘۢۛۦۥۧۖۘ۠ۥۥۛۘۛ" : "ۙ۬ۤۤ۫ۤۖۨۚۧۧۧۛۡ۠۟ۜۥۗۧۚۛۨۨۘۦۖۗ۟ۥۖۦۥۤۙۙ";
                                                                                                                                                                                                                            case 416801205:
                                                                                                                                                                                                                                str56 = "۬ۘ۬ۗۤۘۘۘۧۦۘۤۧۗۛۦۘۨۨۨ۬ۤ۟ۧۨۤۙۧۧ۫ۦۙ";
                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                            case 1538941536:
                                                                                                                                                                                                                                str56 = "۫ۨۦۘۧۖۘۘۚ۬ۨۘۖ۠ۦۘۥۡۘۡۨۥۨۦۤۦ۠ۤۥۖۘۘۢۧۥۘ۟ۚۥۦ۟ۦۨۧۘۥۘۦۙۨۡۘ۫ۥۗ۟ۖۦۙۜۥ";
                                                                                                                                                                                                                                break;
                                                                                                                                                                                                                        }
                                                                                                                                                                                                                    }
                                                                                                                                                                                                                    break;
                                                                                                                                                                                                                case 1930627582:
                                                                                                                                                                                                                    k2.logToFloatingWindow("remaining_seconds <= 0，不启动倒计时", "info");
                                                                                                                                                                                                                    break;
                                                                                                                                                                                                            }
                                                                                                                                                                                                        } catch (Exception e11) {
                                                                                                                                                                                                            break;
                                                                                                                                                                                                        }
                                                                                                                                                                                                    }
                                                                                                                                                                                                    break;
                                                                                                                                                                                                case -98038969:
                                                                                                                                                                                                    break;
                                                                                                                                                                                                case 82563370:
                                                                                                                                                                                                    String str60 = "ۥۢۚ۠ۘۡۧۡۧۘۤۚۦۚۘۡۘۥ۫ۜۘۢ۟ۡۚ۠ۘۘۖۙۡۘۜۘۧۘ۟ۘۥۤۤ۠ۢۡۨۚۥۨۨۙۚۧ۠ۘ";
                                                                                                                                                                                                    while (true) {
                                                                                                                                                                                                        switch (str60.hashCode() ^ (-1539563634)) {
                                                                                                                                                                                                            case -884914571:
                                                                                                                                                                                                                str60 = "ۗۢۥۘۛۖۖ۫ۨۦۘۗۖ۬۟ۦۡۘۢۤۙ۠ۘۘۘۧۧۗۙۤۦۤۘۜۧۢۙ۟ۧ۠ۛۙۢۤۖۨۗۖ۠ۥۧۧۥۙۦ۠ۦۧ";
                                                                                                                                                                                                            case -222068451:
                                                                                                                                                                                                                str55 = "ۤ۬ۜۨۧۙ۟ۦ۠ۤۥۨۘۙۨۥۘۥۡۙۗ۫ۙۡۗۡۘۡ۟ۧۡ۟ۡۜۦۘۜۛ۫ۛۘۘ۟ۛۧ";
                                                                                                                                                                                                                break;
                                                                                                                                                                                                            case 152686065:
                                                                                                                                                                                                                str60 = jSONObjectOptJSONObject != null ? "۫۬ۡ۬ۦ۬ۤۢۛ۫ۖۥۥۨۨۤۥۘۨۧۥۘۚ۫ۢۤۚ۟ۡۧۛۦ۬ۧۗ۠۠ۖۤۤۦۖۘۛۛۥ۫ۗۜۘ" : "ۡۛۜۨۗۥ۬ۨۦۤ۠۫ۙۘۦۘۨۗ۫ۦۛۗۙۦۗۗۖۘۦۜ۠";
                                                                                                                                                                                                            case 302826981:
                                                                                                                                                                                                                str55 = "ۦۢۙۛ۬۫ۡۤۖۖ۫ۥ۫ۖۙۘ۫ۡۙۛۘۘ۬ۥۖ۬ۥۜۘ۫۬ۖۘ۬ۖۘ۫ۜۧۘۨۦۦۗۨۡۘ";
                                                                                                                                                                                                                break;
                                                                                                                                                                                                        }
                                                                                                                                                                                                    }
                                                                                                                                                                                                    break;
                                                                                                                                                                                                case 2020687293:
                                                                                                                                                                                                    str55 = "ۤۖۧۘۤۘۖۘۢۛۥۘۛۗۢۧۥۡ۫۬ۦۗۙۜۘ۬ۘ۠ۡۨۢۤ۟ۡ";
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                        break;
                                                                                                                                                                                    case 206075573:
                                                                                                                                                                                        break;
                                                                                                                                                                                    case 1382458287:
                                                                                                                                                                                        String str61 = "ۢ۬ۙۥۤۜۘ۫ۖۗۨ۫ۡۤ۬ۜ۫ۥۤۛ۫ۗۜ۬ۨۛۜۧ۠ۗ۫ۤ۠ۥ۫ۡۡۘۢۦۡۦۗۢۥۨۨۘۤۦۦ";
                                                                                                                                                                                        while (true) {
                                                                                                                                                                                            switch (str61.hashCode() ^ (-233735170)) {
                                                                                                                                                                                                case -36830141:
                                                                                                                                                                                                    str54 = "ۢۤۜۦۦۧۘ۫۬ۙ۫ۢۘۘۙۗۦۛۨۢۗۙۨۛۖۘۡۗۨۚۤۧۛۖۤۘۘۘ";
                                                                                                                                                                                                    break;
                                                                                                                                                                                                case 22277510:
                                                                                                                                                                                                    str61 = jSONObject.has("message") ? "ۗۦۨۦ۫ۦ۟ۘۘۜۘۗ۟ۖۘ۫ۡۧۘۚۤ۫ۗۚۥۖ۟ۤۤۧۥۖۜۨۘۥۤۖۘۤ۠ۚۥ۬ۘۘۤۜۥۘۢۤۦ" : "ۤۧۨۘۚ۬ۢۦۢۤۡۦۙۨۙۙۨۗ۟۬۟ۙ۟ۢۢ۫۬ۘۘۧۙۘۘۧ۬ۦۗۥۢۜۧ۬ۗۡۥۘۜ۬ۦۘۡۡۖۖۚۗۜ۬ۘۘ";
                                                                                                                                                                                                case 136777645:
                                                                                                                                                                                                    str54 = "۟۟۟ۙۥۤ۫ۙۙۜۘ۫ۤۘۘۚۦۥۙ۟ۘۘۥ۫۟ۤۧۦ۠ۘۡ۠ۗۙ۬ۘۧ۫۬ۥۜ۟ۡۘ۟ۜۢۤۥۚۡۘۡۙۡۘ";
                                                                                                                                                                                                    break;
                                                                                                                                                                                                case 1552709929:
                                                                                                                                                                                                    str61 = "ۛۗۧ۬۫۫ۤۗۡۙۗۥۡۛۤ۟ۡۦۡۘۧۘۧ۠ۗۧ۫ۘۘۜۜ۟ۢۥ۬ۦۨۥ";
                                                                                                                                                                                            }
                                                                                                                                                                                        }
                                                                                                                                                                                        break;
                                                                                                                                                                                }
                                                                                                                                                                            }
                                                                                                                                                                            break;
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                            } catch (Throwable th8) {
                                                                                                                                                                th = th8;
                                                                                                                                                            }
                                                                                                                                                        } catch (Exception e12) {
                                                                                                                                                        }
                                                                                                                                                        zIsSuccessful = responseExecute.isSuccessful();
                                                                                                                                                        try {
                                                                                                                                                            k2.logToFloatingWindow("请求成功（第" + i5 + "次），状态码：" + iCode, "info");
                                                                                                                                                            StringBuilder sb5 = new StringBuilder();
                                                                                                                                                            sb5.append("响应内容：");
                                                                                                                                                            sb5.append(sb3.toString());
                                                                                                                                                            k2.logToFloatingWindow(sb5.toString(), "info");
                                                                                                                                                            try {
                                                                                                                                                                responseExecute.close();
                                                                                                                                                                z3 = zIsSuccessful;
                                                                                                                                                            } catch (Utils.a e13) {
                                                                                                                                                                e = e13;
                                                                                                                                                                z3 = zIsSuccessful;
                                                                                                                                                                i3 = iCode;
                                                                                                                                                                k2.logToFloatingWindow("签名校验失败，终止重试：" + e.getMessage(), "warning");
                                                                                                                                                                sb3.setLength(0);
                                                                                                                                                                sb3.append("请求失败03: ");
                                                                                                                                                                sb3.append(e.getMessage());
                                                                                                                                                                iCode = i3;
                                                                                                                                                                objArr[0] = new Object[]{Boolean.valueOf(z3), Integer.valueOf(iCode), map6, sb3.toString()};
                                                                                                                                                                synchronized (gTBLD.dev.XSSTG.free.Utils.e) {
                                                                                                                                                                }
                                                                                                                                                            } catch (Exception e14) {
                                                                                                                                                                e2 = e14;
                                                                                                                                                                z3 = zIsSuccessful;
                                                                                                                                                                i3 = iCode;
                                                                                                                                                                k2.logToFloatingWindow("第 " + i5 + " 次连接失败：" + e2.getMessage(), "warning");
                                                                                                                                                                str = "ۚ۠ۖۚۜۗۙۘۨۜۥۘ۫ۦۥۙۘۘۖ۠ۦ۟ۚۤ۫ۡۥۘۘۡۦۘۘۢۦ۠ۤۡۘۖۜۛۗ۠ۘۘۤ۬ۜۘۦۢۤ";
                                                                                                                                                                while (true) {
                                                                                                                                                                    switch (str.hashCode() ^ 774184049) {
                                                                                                                                                                        case -1884365497:
                                                                                                                                                                            break;
                                                                                                                                                                        case -1792297956:
                                                                                                                                                                            break;
                                                                                                                                                                        case -1543512637:
                                                                                                                                                                            break;
                                                                                                                                                                        case 2146245558:
                                                                                                                                                                            break;
                                                                                                                                                                    }
                                                                                                                                                                }
                                                                                                                                                                i4 = i3;
                                                                                                                                                                i5++;
                                                                                                                                                            }
                                                                                                                                                        } catch (Throwable th9) {
                                                                                                                                                            th = th9;
                                                                                                                                                            z3 = zIsSuccessful;
                                                                                                                                                            str2 = "ۢۥۥ۠ۙۙۧۜۜ۠ۦۡۘۖۜۥۘۛ۫ۥۘ۠۟ۤۢۚۥۥۨ۠ۤ۫ۘۘۘ۬ۜ۫ۦۘۘۢۘ۠ۗ۟ۛۖۥۖۦۙۡۚۛ۫ۜۢۨۘ";
                                                                                                                                                            while (true) {
                                                                                                                                                                switch (str2.hashCode() ^ (-1706872566)) {
                                                                                                                                                                    case -1917991824:
                                                                                                                                                                        break;
                                                                                                                                                                    case -1165689843:
                                                                                                                                                                        break;
                                                                                                                                                                    case -410278016:
                                                                                                                                                                        break;
                                                                                                                                                                    case 1776577389:
                                                                                                                                                                        break;
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                            throw th;
                                                                                                                                                        }
                                                                                                                                                        break;
                                                                                                                                                    case 1661234170:
                                                                                                                                                        try {
                                                                                                                                                            String next = it3.next();
                                                                                                                                                            k2.logToFloatingWindow("响应头：" + next + " -> " + headers.get(next), "info");
                                                                                                                                                            map6.put(next, headers.get(next));
                                                                                                                                                            String str62 = "ۜۤۗۥۥۧۖۘۡ۫ۙ۠۟ۘۘ۫۬ۢۙۛۤۜۘۗ۫ۦۗۖۤ۫۬ۚ۠ۗۧۛ۬ۨۘۘۧۚۛۧۙۥ۫ۦ";
                                                                                                                                                            while (true) {
                                                                                                                                                                switch (str62.hashCode() ^ 1850644148) {
                                                                                                                                                                    case -1246648347:
                                                                                                                                                                        String str63 = headers.get(next);
                                                                                                                                                                        try {
                                                                                                                                                                            try {
                                                                                                                                                                                try {
                                                                                                                                                                                    long j3 = Long.parseLong(str63.trim());
                                                                                                                                                                                    String str64 = "ۦۡۧ۫۟ۡۢۜۜۘۜۤ۬ۖۖۨۘۧۜۨۘۛۘۛۛ۬ۘۥۙۢۚۡۡ۟۬ۡۘۢۛ۫۫ۨۦۘۡۛ۬";
                                                                                                                                                                                    while (true) {
                                                                                                                                                                                        switch (str64.hashCode() ^ 123748980) {
                                                                                                                                                                                            case -544309225:
                                                                                                                                                                                                str64 = "۫ۘ۬ۗۡۧ۟ۛۘۧۤۥ۠۫ۛۡۙۥۘۗۢۖۘ۫ۥۚۤۧۗۤۧۚۥۢۚۨۦۘۘ۫ۛۛۥۧۗۚۜۗۨۨۦۛۢۘۛۤ۟";
                                                                                                                                                                                            case 1260654498:
                                                                                                                                                                                                gTBLD.dev.XSSTG.free.Utils.startCountdown(j3, new x2(), new a(15));
                                                                                                                                                                                                break;
                                                                                                                                                                                            case 1723898328:
                                                                                                                                                                                                String str65 = "ۗۗ۠ۡ۠ۗ۫ۖ۠ۦۛۨۤۧۘۧۖۜۗ۟۠ۡ۫ۥۢۨۧ۟۫ۙ۫ۡۘۨۘۨۜۨۙۥۖۘ";
                                                                                                                                                                                                while (true) {
                                                                                                                                                                                                    switch (str65.hashCode() ^ (-699094083)) {
                                                                                                                                                                                                        case -2095590208:
                                                                                                                                                                                                            str64 = "۬ۡۖۘ۬ۥۥۘۛ۟ۜۘۚۙۦۘ۟ۜۦۗۥۥۙۨۖ۟۠۟۟ۧۘۘۚۘۥۙۙۥۤۢ۬ۗۛۘۨۗۜۘۡۙۦۘۤۘۦۖۗ۬ۦۧۗ";
                                                                                                                                                                                                            break;
                                                                                                                                                                                                        case -1859563088:
                                                                                                                                                                                                            str65 = j3 > 0 ? "۟ۚۢۗۧۨۧۧۧۖۢۢۡۛۢۥ۟ۖۘۥۢ۠ۦۥۡۦۛۦۘۡۤ۟ۤۚۘۘ۟ۘۤ۬ۨۖۧ۠ۤ" : "ۘۛۦۘۦۦۤۙۚ۬ۘۙۖۘۤ۟ۤۥۨۧۘۛۛۥۤۚۜۜۗۥ۠۬ۧۜۛۧ۟۬ۥۘۡۢۦۘۦۨۧۘ۬۠ۡۖۛۦ";
                                                                                                                                                                                                        case -338914583:
                                                                                                                                                                                                            str65 = "۠ۜۥۘۜۢۨۘۜۜۛۢ۠ۢۖۧۦۦۛۤۦۛۖۙ۟۠ۦۘۖۘۥۜۡۛۢۖۘۙ۬ۙ۠ۖۡۘۖ۫ۥۘ";
                                                                                                                                                                                                        case -39355377:
                                                                                                                                                                                                            str64 = "ۖۢۖۘۢۤ۫ۖۚۙۥۖۘۡ۟ۖۡۗۧۥ۟ۡۥۡۙۖۡ۠۠۬ۥۧۖۘۙۙ۠ۢۥۨۡۘ۫۟ۧۡ۠ۖۧ";
                                                                                                                                                                                                            break;
                                                                                                                                                                                                    }
                                                                                                                                                                                                }
                                                                                                                                                                                                break;
                                                                                                                                                                                            case 1795911402:
                                                                                                                                                                                                k2.logToFloatingWindow("X-Kami-Seconds <= 0，不启动倒计时", "info");
                                                                                                                                                                                                break;
                                                                                                                                                                                        }
                                                                                                                                                                                    }
                                                                                                                                                                                } catch (NumberFormatException e15) {
                                                                                                                                                                                    k2.logToFloatingWindow("X-Kami-Seconds 解析错误：" + str63, "warning");
                                                                                                                                                                                }
                                                                                                                                                                            } catch (Throwable th10) {
                                                                                                                                                                                th = th10;
                                                                                                                                                                                th = th;
                                                                                                                                                                                str2 = "ۢۥۥ۠ۙۙۧۜۜ۠ۦۡۘۖۜۥۘۛ۫ۥۘ۠۟ۤۢۚۥۥۨ۠ۤ۫ۘۘۘ۬ۜ۫ۦۘۘۢۘ۠ۗ۟ۛۖۥۖۦۙۡۚۛ۫ۜۢۨۘ";
                                                                                                                                                                                while (true) {
                                                                                                                                                                                    switch (str2.hashCode() ^ (-1706872566)) {
                                                                                                                                                                                        case -1917991824:
                                                                                                                                                                                            break;
                                                                                                                                                                                        case -1165689843:
                                                                                                                                                                                            break;
                                                                                                                                                                                        case -410278016:
                                                                                                                                                                                            break;
                                                                                                                                                                                        case 1776577389:
                                                                                                                                                                                            break;
                                                                                                                                                                                    }
                                                                                                                                                                                }
                                                                                                                                                                                throw th;
                                                                                                                                                                            }
                                                                                                                                                                        } catch (NumberFormatException e16) {
                                                                                                                                                                        }
                                                                                                                                                                        break;
                                                                                                                                                                    case -88156771:
                                                                                                                                                                        str62 = "۫ۧۖۘۤ۠ۚۤ۬ۥۘۘۖۦ۫ۘۤ۬۠۠ۗۨۘۘ۠ۤۘۦۜۖۗۤۡۘۤۙۤۡۦ";
                                                                                                                                                                    case 245299238:
                                                                                                                                                                        break;
                                                                                                                                                                    case 1486838166:
                                                                                                                                                                        String str66 = "ۖۘۖۥۘۡ۟ۢۡۤۗۚ۫ۤۦ۟ۘۨۘۚۨۜۚۚۚۢ۬ۨۘۨۧۦ۫ۧ۬ۗۡۙ۬ۨۦۛۗۨۘۧۥۚۥۨۦۘ۬ۤۙۢۤۜۘ";
                                                                                                                                                                        while (true) {
                                                                                                                                                                            switch (str66.hashCode() ^ (-1135601885)) {
                                                                                                                                                                                case -1837428300:
                                                                                                                                                                                    str66 = next.equalsIgnoreCase("x-kami-seconds") ? "۬ۛۡۛۘۜۜۤۡۚۚۥۦۧۡۗۖۡۖۨۘۘۡۗۡۘۦۛۘۘ۟۟۟" : "ۦۜۡ۟۠ۨۡۤۡۘۢۧۥۛۤۦۘۖۨۘۘ۬ۛ۠ۧۦۥ۠۟ۤۥۨۙ۫ۨۘۘۖ۟ۥۘۤۥۥ۫ۧ۠ۢۙۤۚ۟ۨۘۘ۫۫ۢ۫۠";
                                                                                                                                                                                case 999591411:
                                                                                                                                                                                    str66 = "ۗۨۢۙۛ۫۠ۙۥ۬ۤۨۘۤۥۦۢۜۤۦ۠ۙۢ۫ۘۘۧۙۨۖۨۦۘ۠ۧۨۘۦۘۛۧۧۡۖۘ۬۬ۖۘۥۙۗ۬ۦۘۘ۟ۡ";
                                                                                                                                                                                case 1401119738:
                                                                                                                                                                                    str62 = "۬ۘۥۘۥۖ۬ۙۧ۠ۧ۠ۦۘۘ۟ۗۛ۫ۤۤ۠ۗۙۛۚۙۖۜۘ۠ۤۜۨۘۦ۠ۢۤ";
                                                                                                                                                                                    break;
                                                                                                                                                                                case 1489574888:
                                                                                                                                                                                    str62 = "ۗۘ۟ۖۧۥۤۖۡۢۘ۠ۚۙۘۘۙۢۨ۟ۤۡۘۤۘۨۘۖۥۧۘۧ۠ۚ";
                                                                                                                                                                                    break;
                                                                                                                                                                            }
                                                                                                                                                                        }
                                                                                                                                                                        break;
                                                                                                                                                                }
                                                                                                                                                            }
                                                                                                                                                        } catch (Throwable th11) {
                                                                                                                                                            th = th11;
                                                                                                                                                        }
                                                                                                                                                        break;
                                                                                                                                                    case 1776836700:
                                                                                                                                                        str50 = "ۧۥۨۥۙ۟۬ۥۢ۫ۛ۠ۢۡۧۙۨۥۗۢۖۤۘۡۘۥ۟ۚۗۗۜۘۡۡ۬ۛۚۡ۟۟ۡۡۢۥۘ۟۬ۚ۟ۚۛ";
                                                                                                                                                }
                                                                                                                                            }
                                                                                                                                        }
                                                                                                                                        break;
                                                                                                                                    case 518908319:
                                                                                                                                        str46 = "ۗۗۡۤۜۜۦۗۦۘۘۛ۠ۛۧۘۘۜۛۜۘ۬ۢۥۘۖۦ۬ۥۡۘۜۡۡۘۦۚ۟ۛۙۤۛ۫ۚ۬ۘ";
                                                                                                                                }
                                                                                                                            }
                                                                                                                        } catch (Throwable th12) {
                                                                                                                            th = th12;
                                                                                                                        }
                                                                                                                    } catch (Throwable th13) {
                                                                                                                        th = th13;
                                                                                                                    }
                                                                                                                } catch (Throwable th14) {
                                                                                                                    th = th14;
                                                                                                                }
                                                                                                            } catch (Throwable th15) {
                                                                                                                th = th15;
                                                                                                            }
                                                                                                            break;
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                            case -697414361:
                                                                                                String str67 = "ۦۧۦۤۢۙ۫ۘۘ۟ۨۤۗۙۨ۬ۙ۫۟ۦۢۜۡۛۧۤۨۨۙۥۘۢۢ۫ۚ۫ۛۛۜۡۘۦۜۜۘۜۘۧ۠۟ۡ";
                                                                                                while (true) {
                                                                                                    switch (str67.hashCode() ^ 1530279900) {
                                                                                                        case -1709681714:
                                                                                                            str67 = "ۘۖۢۜ۬ۦۘۜۙ۟ۤۘ۫ۧۤۢۥۖۙۦ۟ۘۘۨۘۜ۬ۦۘۡ۫ۤۨ۟ۘۦۖۘ";
                                                                                                        case -1140417372:
                                                                                                            str34 = "۬۟ۘۘۧۥ۬ۢ۟ۡۜۦۧۘ۠۠ۗۗۗ۬ۧۗۥ۬ۗ۬ۥۗۜۘۡۨۖۘۧۧۥۘۤۦۥ";
                                                                                                            break;
                                                                                                        case -435794134:
                                                                                                            str67 = !TextUtils.isEmpty(strHeader2) ? "ۗۦ۟ۡۖۖۘۥۡ۟ۤ۬ۨۘۜۛ۬ۖۜۖۤۦۡۗۗۖۘۢۦ۫ۙۤۚ" : "ۚۘۨۜۜۜ۟ۢۨ۟ۦۧۧۢۗۖ۫۫ۥۦۜۘۤۧۦۘۢۗۨۚۜ۬";
                                                                                                        case 2144609196:
                                                                                                            str34 = "۟ۥۜۘۡۛۨۦۖ۠ۙۜۜ۫ۦۡۤۧۘۘۨۢۨ۟ۖۧۚ۫ۛۗۥۙۗۜۛۢ۫ۘۘ";
                                                                                                            break;
                                                                                                    }
                                                                                                }
                                                                                                break;
                                                                                            case 891798155:
                                                                                                str34 = "ۤۚۖۛ۫ۜۘۛ۫۠ۛ۠ۦۜ۟۟ۛۦۤ۟ۘۦۘۘۛۖۘۧ۫ۨۘۤۢۚۛۚۨۢۨۧۗۡۧۘۡۗ۠ۧۡ۬ۧۥۢ۬۬ۖۘۚ۬۠";
                                                                                            case 1008966935:
                                                                                                break;
                                                                                        }
                                                                                    }
                                                                                    break;
                                                                            }
                                                                        } catch (Throwable th16) {
                                                                            th = th16;
                                                                        }
                                                                    }
                                                                    break;
                                                            }
                                                        }
                                                        String strHeader32 = responseExecute.header("x-kami-nonce");
                                                        str3 = "ۖ۬۟ۢ۬ۖۘۚ۬ۤۗۘۧۘۤۢۛۛۤ۬ۤۛۗ۠ۢ۬ۡۗۥۡۛۡۘۖۛۡۢۨۘۙ۠ۖۘۖۧ۬ۡ۟۫ۡ۠۫ۨ۠ۧۡۨۦ";
                                                        while (true) {
                                                            switch (str3.hashCode() ^ 1743894429) {
                                                                case -1015826713:
                                                                    break;
                                                                case -44532638:
                                                                    break;
                                                                case 437159831:
                                                                    break;
                                                                case 1479192311:
                                                                    break;
                                                            }
                                                        }
                                                    } catch (Throwable th17) {
                                                        th = th17;
                                                        zIsSuccessful = z3;
                                                        z3 = zIsSuccessful;
                                                        str2 = "ۢۥۥ۠ۙۙۧۜۜ۠ۦۡۘۖۜۥۘۛ۫ۥۘ۠۟ۤۢۚۥۥۨ۠ۤ۫ۘۘۘ۬ۜ۫ۦۘۘۢۘ۠ۗ۟ۛۖۥۖۦۙۡۚۛ۫ۜۢۨۘ";
                                                        while (true) {
                                                            switch (str2.hashCode() ^ (-1706872566)) {
                                                                case -1917991824:
                                                                    break;
                                                                case -1165689843:
                                                                    break;
                                                                case -410278016:
                                                                    break;
                                                                case 1776577389:
                                                                    break;
                                                            }
                                                        }
                                                        throw th;
                                                    }
                                                    String strOptString2 = new JSONObject(strString).optString("message", strString);
                                                    String strHeader4 = responseExecute.header("x-kami-sign-time");
                                                    String strHeader22 = responseExecute.header("x-kami-sign-token");
                                                } catch (Throwable th18) {
                                                    th = th18;
                                                }
                                            } catch (Throwable th19) {
                                                th = th19;
                                                iCode = i4;
                                            }
                                        } catch (Utils.a e17) {
                                            e = e17;
                                            iCode = i4;
                                        } catch (Exception e18) {
                                            i3 = i4;
                                            e2 = e18;
                                        }
                                        break;
                                    case 720522504:
                                        iCode = i4;
                                        break;
                                    case 1490792851:
                                        String str68 = "ۜۜۡۘۛۚ۠ۡۢۦۘۙۜۡۘۙ۫ۧۧۘۥۢۘۢۦۜۡۘ۟ۙ۬۬۫ۦۘۧۖ۟ۧۙ۠";
                                        while (true) {
                                            switch (str68.hashCode() ^ (-1007306438)) {
                                                case -2079202187:
                                                    str30 = "ۥۨۘ۫۬ۙ۬۟۬ۢ۫۫ۙۜ۬۟۟ۛۨۤ۟۬ۦۘۚ۫ۚۛۥ";
                                                    break;
                                                case -1786680668:
                                                    str68 = i5 <= 3 ? "۫ۡۡۘۥ۠ۙۙۘۚۗ۬ۨۦۢۡۘۦۨ۬ۦۙۥۘۥۦۦۜۧۤ۟۬ۡۘۚ۠ۗۡۘۜۘۖ۟ۚۖۦۘۡۨۜۢۤ۟ۡ۠۫ۢ" : "۠ۚۜۘۥۛۦۘۤۧۚ۫ۛۧۜۙۘۘۖۜۦ۠ۗۡۢۨ۟ۤۨۢۗۚۜ";
                                                case 1511418487:
                                                    str68 = "۫ۦۖۢۘ۠۟ۨۦۘ۬ۖۥۘ۫ۤۧۖ۫ۙۖۘ۬ۤۡۥۦۘۧۗۥۡۘۤ۟ۗۥۘۛۢۚۦۜۘۙۚۙۥۗ۫ۧۛۥۙۛۗ";
                                                case 2088132126:
                                                    str30 = "ۢۘۘۘۦۥۦۘۖۤ۠ۙۢ۫ۖ۠ۧۖ۟ۡ۬۠ۧ۟ۖۥۘ۟ۚۖۘۤۨۢۖۢۨ۫ۥۦ";
                                                    break;
                                            }
                                        }
                                        break;
                                    case 1585575476:
                                        str30 = "ۜۙ۠ۡ۬ۦۨۜۘۘۢۙ۫ۥ۬ۘۘۜۦ۠ۥۤ۬۠ۥۜۥۙۥۛۗ۬۠۬ۨۨۗۥۖ۫۫ۜۛۡۘ";
                                }
                            }
                            i4 = i3;
                            i5++;
                        }
                    }
                    objArr[0] = new Object[]{Boolean.valueOf(z3), Integer.valueOf(iCode), map6, sb3.toString()};
                    synchronized (gTBLD.dev.XSSTG.free.Utils.e) {
                        gTBLD.dev.XSSTG.free.Utils.f = false;
                    }
                    return;
                }
        }
    }
}
