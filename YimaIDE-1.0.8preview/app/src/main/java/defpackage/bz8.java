package defpackage;

import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class bz8 {
    public static final bz8 a = new bz8();
    public static final MediaType b = MediaType.Companion.get("application/json; charset=utf-8");
    public static final int c = 8;

    public interface a {

        /* JADX INFO: renamed from: bz8$a$a, reason: collision with other inner class name */
        public static final class C0000a implements a {
            public final int a;
            public final String b;

            public C0000a(int i, String str) {
                str.getClass();
                this.a = i;
                this.b = str;
            }

            public final int a() {
                return this.a;
            }

            public final String b() {
                return this.b;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof C0000a)) {
                    return false;
                }
                C0000a c0000a = (C0000a) obj;
                return this.a == c0000a.a && Intrinsics.areEqual(this.b, c0000a.b);
            }

            public int hashCode() {
                return (Integer.hashCode(this.a) * 31) + this.b.hashCode();
            }

            public String toString() {
                return "Err(code=" + this.a + ", message=" + this.b + ")";
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

    public static final class b {
        public final String a;
        public final String b;
        public final String c;

        public b(String str, String str2, String str3) {
            str.getClass();
            str2.getClass();
            str3.getClass();
            this.a = str;
            this.b = str2;
            this.c = str3;
        }

        public final String a() {
            return this.a;
        }

        public final String b() {
            return this.b;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return Intrinsics.areEqual(this.a, bVar.a) && Intrinsics.areEqual(this.b, bVar.b) && Intrinsics.areEqual(this.c, bVar.c);
        }

        public int hashCode() {
            return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode();
        }

        public String toString() {
            return "SessionTicket(ticketId=" + this.a + ", token=" + this.b + ", expiresAt=" + this.c + ")";
        }
    }

    public final a a(String str, b bVar, String str2) {
        str.getClass();
        bVar.getClass();
        str2.getClass();
        JSONObject jSONObjectG = g(bVar, str2);
        jSONObjectG.getClass();
        return f("/v1/licensing/agent-session/close", str, jSONObjectG);
    }

    public final OkHttpClient b() {
        return xl0.a.a();
    }

    public final a c(String str, b bVar, String str2) {
        str.getClass();
        bVar.getClass();
        str2.getClass();
        JSONObject jSONObjectG = g(bVar, str2);
        jSONObjectG.getClass();
        return f("/v1/licensing/agent-session/heartbeat", str, jSONObjectG);
    }

    public final a d(String str, String str2, String str3) throws JSONException {
        str.getClass();
        str2.getClass();
        str3.getClass();
        JSONObject jSONObjectPut = new JSONObject().put("deviceId", str2).put("projectId", str3);
        jSONObjectPut.getClass();
        return e("/v1/licensing/agent-session", str, jSONObjectPut);
    }

    public final a e(String str, String str2, JSONObject jSONObject) {
        a c0000a;
        Object obj;
        int iOptInt;
        JSONObject jSONObject2;
        Request.Builder builderHeader = new Request.Builder().url(xl0.a.b(str)).header("Authorization", "Bearer " + str2);
        RequestBody.Companion companion = RequestBody.Companion;
        String string = jSONObject.toString();
        string.getClass();
        try {
            Response responseExecute = b().newCall(builderHeader.post(companion.create(string, b)).build()).execute();
            try {
                ResponseBody responseBodyBody = responseExecute.body();
                String strString = responseBodyBody != null ? responseBodyBody.string() : null;
                if (strString == null) {
                    strString = "";
                }
                try {
                    Result.Companion companion2 = Result.Companion;
                    obj = Result.constructor-impl(new JSONObject(strString));
                } catch (Throwable th) {
                    Result.Companion companion3 = Result.Companion;
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
                if (Result.isFailure-impl(obj)) {
                    obj = null;
                }
                JSONObject jSONObject3 = (JSONObject) obj;
                if (jSONObject3 != null) {
                    iOptInt = jSONObject3.optInt("code", responseExecute.isSuccessful() ? 0 : -1);
                } else {
                    iOptInt = -1;
                }
                if (responseExecute.isSuccessful() && iOptInt == 0) {
                    if (jSONObject3 == null || (jSONObject2 = jSONObject3.optJSONObject("data")) == null) {
                        jSONObject2 = new JSONObject();
                    }
                    String strOptString = jSONObject2.optString("ticketId");
                    String strOptString2 = jSONObject2.optString(SchemaSymbols.ATTVAL_TOKEN);
                    strOptString.getClass();
                    if (StringsKt.isBlank(strOptString)) {
                        c0000a = new a.C0000a(-1, "服务器未返回有效 Agent 授权");
                    } else {
                        strOptString2.getClass();
                        if (StringsKt.isBlank(strOptString2)) {
                            c0000a = new a.C0000a(-1, "服务器未返回有效 Agent 授权");
                        } else {
                            String strOptString3 = jSONObject2.optString("expiresAt");
                            strOptString3.getClass();
                            c0000a = new a.b(new b(strOptString, strOptString2, strOptString3));
                        }
                    }
                } else {
                    String strOptString4 = jSONObject3 != null ? jSONObject3.optString(Constants.ELEMNAME_MESSAGE_STRING) : null;
                    if (strOptString4 == null || StringsKt.isBlank(strOptString4)) {
                        strOptString4 = null;
                    }
                    if (strOptString4 == null) {
                        strOptString4 = "请求失败（HTTP " + responseExecute.code() + "）";
                    }
                    c0000a = new a.C0000a(iOptInt, strOptString4);
                }
                CloseableKt.closeFinally(responseExecute, (Throwable) null);
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
            c0000a = new a.C0000a(-1, message);
        }
        return c0000a;
    }

    public final a f(String str, String str2, JSONObject jSONObject) {
        a c0000a;
        Object obj;
        int iOptInt;
        Request.Builder builderHeader = new Request.Builder().url(xl0.a.b(str)).header("Authorization", "Bearer " + str2);
        RequestBody.Companion companion = RequestBody.Companion;
        String string = jSONObject.toString();
        string.getClass();
        try {
            Response responseExecute = b().newCall(builderHeader.post(companion.create(string, b)).build()).execute();
            try {
                ResponseBody responseBodyBody = responseExecute.body();
                String strString = responseBodyBody != null ? responseBodyBody.string() : null;
                if (strString == null) {
                    strString = "";
                }
                try {
                    Result.Companion companion2 = Result.Companion;
                    obj = Result.constructor-impl(new JSONObject(strString));
                } catch (Throwable th) {
                    Result.Companion companion3 = Result.Companion;
                    obj = Result.constructor-impl(ResultKt.createFailure(th));
                }
                if (Result.isFailure-impl(obj)) {
                    obj = null;
                }
                JSONObject jSONObject2 = (JSONObject) obj;
                if (jSONObject2 != null) {
                    iOptInt = jSONObject2.optInt("code", responseExecute.isSuccessful() ? 0 : -1);
                } else {
                    iOptInt = -1;
                }
                if (responseExecute.isSuccessful() && iOptInt == 0) {
                    c0000a = new a.b(Unit.INSTANCE);
                } else {
                    String strOptString = jSONObject2 != null ? jSONObject2.optString(Constants.ELEMNAME_MESSAGE_STRING) : null;
                    if (strOptString == null || StringsKt.isBlank(strOptString)) {
                        strOptString = null;
                    }
                    if (strOptString == null) {
                        strOptString = "请求失败（HTTP " + responseExecute.code() + "）";
                    }
                    c0000a = new a.C0000a(iOptInt, strOptString);
                }
                CloseableKt.closeFinally(responseExecute, (Throwable) null);
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
            c0000a = new a.C0000a(-1, message);
        }
        return c0000a;
    }

    public final JSONObject g(b bVar, String str) {
        return new JSONObject().put("ticketId", bVar.a()).put(SchemaSymbols.ATTVAL_TOKEN, bVar.b()).put("deviceId", str);
    }

    public final a h(String str, b bVar, String str2) {
        str.getClass();
        bVar.getClass();
        str2.getClass();
        JSONObject jSONObjectG = g(bVar, str2);
        jSONObjectG.getClass();
        return f("/v1/licensing/agent-session/validate", str, jSONObjectG);
    }
}
