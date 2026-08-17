package defpackage;

import android.util.Log;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class fa0 {
    public static final fa0 a = new fa0();

    public static final class a {
        public final long a;
        public final String b;
        public final String c;

        public a(long j, String str, String str2) {
            str.getClass();
            str2.getClass();
            this.a = j;
            this.b = str;
            this.c = str2;
        }

        public final String a() {
            return this.c;
        }

        public final String b() {
            return this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.a == aVar.a && Intrinsics.areEqual(this.b, aVar.b) && Intrinsics.areEqual(this.c, aVar.c);
        }

        public int hashCode() {
            return (((Long.hashCode(this.a) * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
        }

        public String toString() {
            return "Info(id=" + this.a + ", title=" + this.b + ", content=" + this.c + ")";
        }
    }

    public final a a() {
        Object objM38constructorimpl;
        JSONObject jSONObjectOptJSONObject;
        try {
            Response responseExecute = b().newCall(new Request.Builder().url(xl0.a.b("/v1/app/announcement")).get().build()).execute();
            try {
                ResponseBody responseBodyBody = responseExecute.body();
                String strString = responseBodyBody != null ? responseBodyBody.string() : null;
                if (strString == null) {
                    strString = "";
                }
                try {
                    Result.Companion companion = Result.INSTANCE;
                    objM38constructorimpl = Result.m38constructorimpl(new JSONObject(strString));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM38constructorimpl = Result.m38constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m44isFailureimpl(objM38constructorimpl)) {
                    objM38constructorimpl = null;
                }
                JSONObject jSONObject = (JSONObject) objM38constructorimpl;
                int iOptInt = -1;
                if (jSONObject != null) {
                    iOptInt = jSONObject.optInt("code", responseExecute.isSuccessful() ? 0 : -1);
                }
                if (responseExecute.isSuccessful() && iOptInt == 0) {
                    if (jSONObject != null && (jSONObjectOptJSONObject = jSONObject.optJSONObject("data")) != null) {
                        if (!jSONObjectOptJSONObject.optBoolean("hasAnnouncement", false)) {
                            CloseableKt.closeFinally(responseExecute, (Throwable) null);
                            return null;
                        }
                        String strOptString = jSONObjectOptJSONObject.optString("title");
                        strOptString.getClass();
                        String string = StringsKt.trim(strOptString).toString();
                        String strOptString2 = jSONObjectOptJSONObject.optString("content");
                        strOptString2.getClass();
                        String string2 = StringsKt.trim(strOptString2).toString();
                        if (StringsKt.isBlank(string) && StringsKt.isBlank(string2)) {
                            CloseableKt.closeFinally(responseExecute, (Throwable) null);
                            return null;
                        }
                        long jOptLong = jSONObjectOptJSONObject.optLong("id", 0L);
                        if (StringsKt.isBlank(string)) {
                            string = "通知公告";
                        }
                        a aVar = new a(jOptLong, string, string2);
                        CloseableKt.closeFinally(responseExecute, (Throwable) null);
                        return aVar;
                        Log.w("AnnouncementApi", "获取公告失败", th);
                        return null;
                    }
                    CloseableKt.closeFinally(responseExecute, (Throwable) null);
                    return null;
                }
                CloseableKt.closeFinally(responseExecute, (Throwable) null);
                return null;
            } catch (Throwable th2) {
                try {
                    throw th2;
                } catch (Throwable th3) {
                    CloseableKt.closeFinally(responseExecute, th2);
                    throw th3;
                }
            }
        } catch (Throwable th4) {
            Log.w("AnnouncementApi", "获取公告失败", th4);
            return null;
        }
    }

    public final OkHttpClient b() {
        return xl0.a.a();
    }
}
