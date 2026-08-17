package defpackage;

import com.sun.org.apache.xalan.internal.templates.Constants;
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

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class pbf {
    public static final pbf a = new pbf();

    public interface a {

        /* JADX INFO: renamed from: pbf$a$a, reason: collision with other inner class name */
        public static final class C0012a implements a {
            public final String a;

            public C0012a(String str) {
                str.getClass();
                this.a = str;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof C0012a) && Intrinsics.areEqual(this.a, ((C0012a) obj).a);
            }

            public int hashCode() {
                return this.a.hashCode();
            }

            public String toString() {
                return "Err(message=" + this.a + ")";
            }
        }

        public static final class b implements a {
            public final Object a;

            public b(Object obj) {
                this.a = obj;
            }

            public final Object a() {
                return this.a;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof b) && Intrinsics.areEqual(this.a, ((b) obj).a);
            }

            public int hashCode() {
                Object obj = this.a;
                if (obj == null) {
                    return 0;
                }
                return obj.hashCode();
            }

            public String toString() {
                return "Ok(data=" + this.a + ")";
            }
        }
    }

    public final a a(long j) {
        a c0012a;
        Object obj;
        String strOptString;
        JSONObject jSONObject;
        try {
            Response responseExecute = b().newCall(new Request.Builder().url(xl0.a.b("/v1/app/version") + "?versionCode=" + j).get().build()).execute();
            try {
                ResponseBody responseBodyBody = responseExecute.body();
                String strString = responseBodyBody != null ? responseBodyBody.string() : null;
                if (strString == null) {
                    strString = "";
                }
                try {
                    Result.Companion companion = Result.Companion;
                    obj = Result.constructor-impl(new JSONObject(strString));
                } catch (Throwable th) {
                    Result.Companion companion2 = Result.Companion;
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
                if (Result.isFailure-impl(obj)) {
                    obj = null;
                }
                JSONObject jSONObject2 = (JSONObject) obj;
                int iOptInt = -1;
                if (jSONObject2 != null) {
                    iOptInt = jSONObject2.optInt("code", responseExecute.isSuccessful() ? 0 : -1);
                }
                if (!responseExecute.isSuccessful() || iOptInt != 0) {
                    if (jSONObject2 == null || (strOptString = jSONObject2.optString(Constants.ELEMNAME_MESSAGE_STRING)) == null) {
                        strOptString = "检测失败";
                    }
                    a.C0012a c0012a2 = new a.C0012a(strOptString);
                    CloseableKt.closeFinally(responseExecute, (Throwable) null);
                    return c0012a2;
                }
                if (jSONObject2 == null || (jSONObject = jSONObject2.optJSONObject("data")) == null) {
                    jSONObject = new JSONObject();
                }
                if (!jSONObject.optBoolean("hasUpdate", false)) {
                    a.b bVar = new a.b(null);
                    CloseableKt.closeFinally(responseExecute, (Throwable) null);
                    return bVar;
                }
                String strOptString2 = jSONObject.optString("downloadUrl");
                strOptString2.getClass();
                String string = StringsKt.trim(strOptString2).toString();
                if (StringsKt.isBlank(string)) {
                    a.b bVar2 = new a.b(null);
                    CloseableKt.closeFinally(responseExecute, (Throwable) null);
                    return bVar2;
                }
                boolean zOptBoolean = jSONObject.optBoolean("forceUpdate", false);
                int iOptInt2 = jSONObject.optInt("latestVersionCode", 0);
                String strOptString3 = jSONObject.optString("latestVersionName", "");
                strOptString3.getClass();
                String strOptString4 = jSONObject.optString("title", "发现新版本");
                strOptString4.getClass();
                String strOptString5 = jSONObject.optString(Constants.ELEMNAME_MESSAGE_STRING, "");
                strOptString5.getClass();
                c0012a = new a.b(new b(zOptBoolean, iOptInt2, strOptString3, string, strOptString4, strOptString5));
                CloseableKt.closeFinally(responseExecute, (Throwable) null);
                return c0012a;
            } catch (Throwable th2) {
                try {
                    throw th2;
                } catch (Throwable th3) {
                    CloseableKt.closeFinally(responseExecute, th2);
                    throw th3;
                }
            }
        } catch (Throwable th4) {
            String message = th4.getMessage();
            if (message == null) {
                message = "网络错误";
            }
            c0012a = new a.C0012a(message);
        }
    }

    public final OkHttpClient b() {
        return xl0.a.a();
    }
}
