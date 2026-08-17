package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import com.intellij.psi.PsiKeyword;
import com.sun.org.apache.xalan.internal.templates.Constants;
import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;
import java.util.UUID;
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
public final class kwa {
    public static final kwa a = new kwa();
    public static final MediaType b = MediaType.Companion.get("application/json; charset=utf-8");
    public static final int c = 8;

    public interface a {

        /* JADX INFO: renamed from: kwa$a$a, reason: collision with other inner class name */
        public static final class C0009a implements a {
            public final int a;
            public final String b;

            public C0009a(int i, String str) {
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
                if (!(obj instanceof C0009a)) {
                    return false;
                }
                C0009a c0009a = (C0009a) obj;
                return this.a == c0009a.a && Intrinsics.areEqual(this.b, c0009a.b);
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
        public final int d;

        public b(String str, String str2, String str3, int i) {
            str.getClass();
            str2.getClass();
            str3.getClass();
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = i;
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
            return Intrinsics.areEqual(this.a, bVar.a) && Intrinsics.areEqual(this.b, bVar.b) && Intrinsics.areEqual(this.c, bVar.c) && this.d == bVar.d;
        }

        public int hashCode() {
            return (((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + Integer.hashCode(this.d);
        }

        public String toString() {
            return "Ticket(ticketId=" + this.a + ", token=" + this.b + ", expiresAt=" + this.c + ", remainingToday=" + this.d + ")";
        }
    }

    public static final class c {
        public final String a;
        public final String b;
        public final String c;
        public final String d;

        public c(String str, String str2, String str3, String str4) {
            str.getClass();
            str2.getClass();
            str3.getClass();
            str4.getClass();
            this.a = str;
            this.b = str2;
            this.c = str3;
            this.d = str4;
        }

        public final String a() {
            return this.b;
        }

        public final String b() {
            return this.c;
        }

        public final String c() {
            return this.a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return Intrinsics.areEqual(this.a, cVar.a) && Intrinsics.areEqual(this.b, cVar.b) && Intrinsics.areEqual(this.c, cVar.c) && Intrinsics.areEqual(this.d, cVar.d);
        }

        public int hashCode() {
            return (((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + this.c.hashCode()) * 31) + this.d.hashCode();
        }

        public String toString() {
            return "Unlock(unlockId=" + this.a + ", key=" + this.b + ", sealed=" + this.c + ", ticketId=" + this.d + ")";
        }
    }

    public final a a(String str, b bVar, String str2) throws JSONException {
        str.getClass();
        bVar.getClass();
        str2.getClass();
        JSONObject jSONObjectPut = new JSONObject().put("ticketId", bVar.a()).put(SchemaSymbols.ATTVAL_TOKEN, bVar.b()).put("deviceId", str2);
        Request.Builder builderHeader = new Request.Builder().url(xl0.a.b("/v1/packaging/release-ticket/consume")).header("Authorization", "Bearer " + str);
        RequestBody.Companion companion = RequestBody.Companion;
        String string = jSONObjectPut.toString();
        string.getClass();
        return h(builderHeader.post(companion.create(string, b)).build());
    }

    public final a b(String str, String str2, String str3) throws JSONException {
        str.getClass();
        str2.getClass();
        str3.getClass();
        JSONObject jSONObjectPut = new JSONObject().put("unlockId", str2).put("key", str3);
        Request.Builder builderHeader = new Request.Builder().url(xl0.a.b("/v1/packaging/release-ticket/unlock/consume")).header("Authorization", "Bearer " + str);
        RequestBody.Companion companion = RequestBody.Companion;
        String string = jSONObjectPut.toString();
        string.getClass();
        return h(builderHeader.post(companion.create(string, b)).build());
    }

    public final String c(Context context) {
        context.getClass();
        SharedPreferences sharedPreferences = context.getApplicationContext().getSharedPreferences("yima_device", 0);
        String string = sharedPreferences.getString("install_id", null);
        if (string != null && !StringsKt.isBlank(string)) {
            return string;
        }
        String string2 = UUID.randomUUID().toString();
        string2.getClass();
        String strReplace$default = StringsKt.replace$default(string2, "-", "", false, 4, (Object) null);
        sharedPreferences.edit().putString("install_id", strReplace$default).apply();
        return strReplace$default;
    }

    public final OkHttpClient d() {
        return xl0.a.a();
    }

    public final a e(String str, String str2, String str3) throws JSONException {
        str.getClass();
        str2.getClass();
        str3.getClass();
        JSONObject jSONObjectPut = new JSONObject().put("deviceId", str2).put("packageName", str3);
        Request.Builder builderHeader = new Request.Builder().url(xl0.a.b("/v1/packaging/release-ticket")).header("Authorization", "Bearer " + str);
        RequestBody.Companion companion = RequestBody.Companion;
        String string = jSONObjectPut.toString();
        string.getClass();
        return g(builderHeader.post(companion.create(string, b)).build());
    }

    public final a f(String str, b bVar, String str2) throws JSONException {
        str.getClass();
        bVar.getClass();
        str2.getClass();
        JSONObject jSONObjectPut = new JSONObject().put("ticketId", bVar.a()).put(SchemaSymbols.ATTVAL_TOKEN, bVar.b()).put("deviceId", str2);
        Request.Builder builderHeader = new Request.Builder().url(xl0.a.b("/v1/packaging/release-ticket/unlock")).header("Authorization", "Bearer " + str);
        RequestBody.Companion companion = RequestBody.Companion;
        String string = jSONObjectPut.toString();
        string.getClass();
        return i(builderHeader.post(companion.create(string, b)).build());
    }

    public final a g(Request request) {
        a c0009a;
        Object obj;
        int iOptInt;
        JSONObject jSONObject;
        try {
            Response responseExecute = d().newCall(request).execute();
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
                if (jSONObject2 != null) {
                    iOptInt = jSONObject2.optInt("code", responseExecute.isSuccessful() ? 0 : -1);
                } else {
                    iOptInt = -1;
                }
                if (responseExecute.isSuccessful() && iOptInt == 0) {
                    if (jSONObject2 == null || (jSONObject = jSONObject2.optJSONObject("data")) == null) {
                        jSONObject = new JSONObject();
                    }
                    String strOptString = jSONObject.optString("ticketId");
                    String strOptString2 = jSONObject.optString(SchemaSymbols.ATTVAL_TOKEN);
                    strOptString.getClass();
                    if (StringsKt.isBlank(strOptString)) {
                        c0009a = new a.C0009a(-1, "服务器未返回有效授权");
                    } else {
                        strOptString2.getClass();
                        if (StringsKt.isBlank(strOptString2)) {
                            c0009a = new a.C0009a(-1, "服务器未返回有效授权");
                        } else {
                            String strOptString3 = jSONObject.optString("expiresAt");
                            strOptString3.getClass();
                            c0009a = new a.b(new b(strOptString, strOptString2, strOptString3, jSONObject.optInt("remainingToday", 0)));
                        }
                    }
                } else {
                    String strOptString4 = jSONObject2 != null ? jSONObject2.optString(Constants.ELEMNAME_MESSAGE_STRING) : null;
                    if (strOptString4 == null || StringsKt.isBlank(strOptString4)) {
                        strOptString4 = null;
                    }
                    if (strOptString4 == null) {
                        strOptString4 = "请求失败（HTTP " + responseExecute.code() + "）";
                    }
                    c0009a = new a.C0009a(iOptInt, strOptString4);
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
            c0009a = new a.C0009a(-1, message);
        }
        return c0009a;
    }

    public final a h(Request request) {
        a c0009a;
        Object obj;
        int iOptInt;
        try {
            Response responseExecute = d().newCall(request).execute();
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
                JSONObject jSONObject = (JSONObject) obj;
                if (jSONObject != null) {
                    iOptInt = jSONObject.optInt("code", responseExecute.isSuccessful() ? 0 : -1);
                } else {
                    iOptInt = -1;
                }
                if (responseExecute.isSuccessful() && iOptInt == 0) {
                    c0009a = new a.b(Unit.INSTANCE);
                } else {
                    String strOptString = jSONObject != null ? jSONObject.optString(Constants.ELEMNAME_MESSAGE_STRING) : null;
                    if (strOptString == null || StringsKt.isBlank(strOptString)) {
                        strOptString = null;
                    }
                    if (strOptString == null) {
                        strOptString = "请求失败（HTTP " + responseExecute.code() + "）";
                    }
                    c0009a = new a.C0009a(iOptInt, strOptString);
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
            c0009a = new a.C0009a(-1, message);
        }
        return c0009a;
    }

    /* JADX WARN: Code duplicated, block: B:42:0x00b3 A[Catch: all -> 0x001c, TryCatch #3 {all -> 0x001c, blocks: (B:4:0x0010, B:6:0x0017, B:16:0x003b, B:19:0x0042, B:21:0x0046, B:25:0x0051, B:27:0x0057, B:31:0x0061, B:34:0x006e, B:36:0x0092, B:38:0x009b, B:41:0x00a5, B:42:0x00b3, B:33:0x0069, B:44:0x00be, B:47:0x00c8, B:51:0x00d1, B:52:0x00e7, B:15:0x0031, B:12:0x0024), top: B:71:0x0010, outer: #1, inners: #0 }] */
    public final a i(Request request) {
        a c0009a;
        Object obj;
        int iOptInt;
        JSONObject jSONObject;
        try {
            Response responseExecute = d().newCall(request).execute();
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
                if (jSONObject2 != null) {
                    iOptInt = jSONObject2.optInt("code", responseExecute.isSuccessful() ? 0 : -1);
                } else {
                    iOptInt = -1;
                }
                if (responseExecute.isSuccessful() && iOptInt == 0) {
                    if (jSONObject2 == null || (jSONObject = jSONObject2.optJSONObject("data")) == null) {
                        jSONObject = new JSONObject();
                    }
                    String strOptString = jSONObject.optString("unlockId");
                    String strOptString2 = jSONObject.optString("key");
                    String strOptString3 = jSONObject.optString(PsiKeyword.SEALED);
                    String strOptString4 = jSONObject.optString("ticketId");
                    strOptString.getClass();
                    if (StringsKt.isBlank(strOptString)) {
                        c0009a = new a.C0009a(-1, "服务器未返回有效授权");
                    } else {
                        strOptString2.getClass();
                        if (StringsKt.isBlank(strOptString2)) {
                            c0009a = new a.C0009a(-1, "服务器未返回有效授权");
                        } else {
                            strOptString3.getClass();
                            if (StringsKt.isBlank(strOptString3)) {
                                c0009a = new a.C0009a(-1, "服务器未返回有效授权");
                            } else {
                                strOptString4.getClass();
                                c0009a = new a.b(new c(strOptString, strOptString2, strOptString3, strOptString4));
                            }
                        }
                    }
                } else {
                    String strOptString5 = jSONObject2 != null ? jSONObject2.optString(Constants.ELEMNAME_MESSAGE_STRING) : null;
                    if (strOptString5 == null || StringsKt.isBlank(strOptString5)) {
                        strOptString5 = null;
                    }
                    if (strOptString5 == null) {
                        strOptString5 = "请求失败（HTTP " + responseExecute.code() + "）";
                    }
                    c0009a = new a.C0009a(iOptInt, strOptString5);
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
            c0009a = new a.C0009a(-1, message);
        }
        return c0009a;
    }

    public final a j(String str, b bVar, String str2) throws JSONException {
        str.getClass();
        bVar.getClass();
        str2.getClass();
        JSONObject jSONObjectPut = new JSONObject().put("ticketId", bVar.a()).put(SchemaSymbols.ATTVAL_TOKEN, bVar.b()).put("deviceId", str2);
        Request.Builder builderHeader = new Request.Builder().url(xl0.a.b("/v1/packaging/release-ticket/validate")).header("Authorization", "Bearer " + str);
        RequestBody.Companion companion = RequestBody.Companion;
        String string = jSONObjectPut.toString();
        string.getClass();
        return h(builderHeader.post(companion.create(string, b)).build());
    }
}
