package defpackage;

import java.util.concurrent.TimeUnit;
import okhttp3.OkHttpClient;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class xl0 {
    public static final xl0 a = new xl0();
    public static final OkHttpClient b;
    public static final int c;

    static {
        OkHttpClient.Builder builder = new OkHttpClient.Builder();
        TimeUnit timeUnit = TimeUnit.SECONDS;
        b = builder.connectTimeout(10L, timeUnit).readTimeout(30L, timeUnit).writeTimeout(30L, timeUnit).addInterceptor(new od0()).build();
        c = 8;
    }

    public final OkHttpClient a() {
        return b;
    }

    public final String b(String str) {
        str.getClass();
        return "https://api.yimaai.xyz" + str;
    }
}
