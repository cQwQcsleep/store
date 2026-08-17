package defpackage;

import com.sun.org.apache.xalan.internal.templates.Constants;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.io.CloseableKt;
import kotlin.jvm.functions.Function1;
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
public final class mj0 {
    public static final mj0 a = new mj0();
    public static final MediaType b = MediaType.Companion.get("application/json; charset=utf-8");
    public static final int c = 8;

    public interface a {

        /* JADX INFO: renamed from: mj0$a$a, reason: collision with other inner class name */
        public static final class C0010a implements a {
            public final int a;
            public final String b;

            public C0010a(int i, String str) {
                str.getClass();
                this.a = i;
                this.b = str;
            }

            public final String a() {
                return this.b;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                if (!(obj instanceof C0010a)) {
                    return false;
                }
                C0010a c0010a = (C0010a) obj;
                return this.a == c0010a.a && Intrinsics.areEqual(this.b, c0010a.b);
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
        public final long c;

        public b(String str, String str2, long j) {
            str.getClass();
            str2.getClass();
            this.a = str;
            this.b = str2;
            this.c = j;
        }

        public final String a() {
            return this.a;
        }

        public final long b() {
            return this.c;
        }

        public final String c() {
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
            return Intrinsics.areEqual(this.a, bVar.a) && Intrinsics.areEqual(this.b, bVar.b) && this.c == bVar.c;
        }

        public int hashCode() {
            return (((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + Long.hashCode(this.c);
        }

        public String toString() {
            return "Tokens(accessToken=" + this.a + ", refreshToken=" + this.b + ", expiresIn=" + this.c + ")";
        }
    }

    public static b a(JSONObject jSONObject) {
        jSONObject.getClass();
        return a.k(jSONObject);
    }

    public static b b(JSONObject jSONObject) {
        jSONObject.getClass();
        return a.k(jSONObject);
    }

    public static Unit c(JSONObject jSONObject) {
        jSONObject.getClass();
        Unit unit = Unit.INSTANCE;
        new a.b(unit);
        return unit;
    }

    public static /* synthetic */ a f(mj0 mj0Var, String str, String str2, String str3, int i, Object obj) {
        if ((i & 4) != 0) {
            str3 = null;
        }
        return mj0Var.e(str, str2, str3);
    }

    public final OkHttpClient d() {
        return xl0.a.a();
    }

    public final a e(String str, String str2, String str3) throws JSONException {
        str.getClass();
        str2.getClass();
        JSONObject jSONObjectPut = new JSONObject().put("phone", str).put("smsCode", str2);
        if (str3 != null && !StringsKt.isBlank(str3)) {
            jSONObjectPut.put("inviteCode", str3);
        }
        jSONObjectPut.getClass();
        return h("/v1/auth/login", jSONObjectPut, new Function1() { // from class: kj0
            public final Object invoke(Object obj) {
                return mj0.a((JSONObject) obj);
            }
        });
    }

    public final a g(String str, String str2) throws JSONException {
        a c0010a;
        Object obj;
        int iOptInt;
        str.getClass();
        JSONObject jSONObject = new JSONObject();
        if (str2 != null && !StringsKt.isBlank(str2)) {
            jSONObject.put("refreshToken", str2);
        }
        Request.Builder builderHeader = new Request.Builder().url(xl0.a.b("/v1/auth/logout")).header("Authorization", "Bearer " + str);
        RequestBody.Companion companion = RequestBody.Companion;
        String string = jSONObject.toString();
        string.getClass();
        try {
            Response responseExecute = d().newCall(builderHeader.post(companion.create(string, b)).build()).execute();
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
                    c0010a = new a.b(Unit.INSTANCE);
                } else {
                    String strOptString = jSONObject2 != null ? jSONObject2.optString(Constants.ELEMNAME_MESSAGE_STRING) : null;
                    if (strOptString == null || StringsKt.isBlank(strOptString)) {
                        strOptString = null;
                    }
                    if (strOptString == null) {
                        strOptString = "登出失败（HTTP " + responseExecute.code() + "）";
                    }
                    c0010a = new a.C0010a(iOptInt, strOptString);
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
            c0010a = new a.C0010a(-1, message);
        }
        return c0010a;
    }

    public final a h(String str, JSONObject jSONObject, Function1 function1) {
        a c0010a;
        Object obj;
        int iOptInt;
        JSONObject jSONObject2;
        Request.Builder builderUrl = new Request.Builder().url(xl0.a.b(str));
        RequestBody.Companion companion = RequestBody.Companion;
        String string = jSONObject.toString();
        string.getClass();
        try {
            Response responseExecute = d().newCall(builderUrl.post(companion.create(string, b)).build()).execute();
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
                    c0010a = new a.b(function1.invoke(jSONObject2));
                } else {
                    String strOptString = jSONObject3 != null ? jSONObject3.optString(Constants.ELEMNAME_MESSAGE_STRING) : null;
                    if (strOptString == null || StringsKt.isBlank(strOptString)) {
                        strOptString = null;
                    }
                    if (strOptString == null) {
                        strOptString = "请求失败（HTTP " + responseExecute.code() + "）";
                    }
                    c0010a = new a.C0010a(iOptInt, strOptString);
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
            c0010a = new a.C0010a(-1, message);
        }
        return c0010a;
    }

    public final a i(String str) throws JSONException {
        str.getClass();
        JSONObject jSONObjectPut = new JSONObject().put("refreshToken", str);
        jSONObjectPut.getClass();
        return h("/v1/auth/refresh", jSONObjectPut, new Function1() { // from class: jj0
            public final Object invoke(Object obj) {
                return mj0.b((JSONObject) obj);
            }
        });
    }

    public final a j(String str, String str2) throws JSONException {
        str.getClass();
        str2.getClass();
        JSONObject jSONObjectPut = new JSONObject().put("phone", str).put("scene", str2);
        jSONObjectPut.getClass();
        return h("/v1/auth/sms", jSONObjectPut, new Function1() { // from class: lj0
            public final Object invoke(Object obj) {
                return mj0.c((JSONObject) obj);
            }
        });
    }

    public final b k(JSONObject jSONObject) {
        String strOptString = jSONObject.optString("accessToken");
        String strOptString2 = jSONObject.optString("refreshToken");
        strOptString.getClass();
        if (!StringsKt.isBlank(strOptString)) {
            strOptString2.getClass();
            if (!StringsKt.isBlank(strOptString2)) {
                return new b(strOptString, strOptString2, jSONObject.optLong("expiresIn", 2592000L));
            }
        }
        k2d.a("服务端未返回有效令牌，请稍后重试");
        return null;
    }
}
