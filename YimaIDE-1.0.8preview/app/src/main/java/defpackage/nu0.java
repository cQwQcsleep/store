package defpackage;

import kotlin.Result;
import kotlin.ResultKt;
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

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class nu0 {
    public static final nu0 a = new nu0();
    public static final MediaType b = MediaType.Companion.get("application/json; charset=utf-8");
    public static final int c = 8;

    public static final class a {
        public final int a;
        public final String b;

        public a(int i, String str) {
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
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return this.a == aVar.a && Intrinsics.areEqual(this.b, aVar.b);
        }

        public int hashCode() {
            return (Integer.hashCode(this.a) * 31) + this.b.hashCode();
        }

        public String toString() {
            return "InviteBindResult(grantedPoints=" + this.a + ", message=" + this.b + ")";
        }
    }

    public static final class b {
        public final String a;
        public final int b;
        public final int c;
        public final int d;
        public final boolean e;

        public b(String str, int i, int i2, int i3, boolean z) {
            str.getClass();
            this.a = str;
            this.b = i;
            this.c = i2;
            this.d = i3;
            this.e = z;
        }

        public static /* synthetic */ b b(b bVar, String str, int i, int i2, int i3, boolean z, int i4, Object obj) {
            if ((i4 & 1) != 0) {
                str = bVar.a;
            }
            if ((i4 & 2) != 0) {
                i = bVar.b;
            }
            if ((i4 & 4) != 0) {
                i2 = bVar.c;
            }
            if ((i4 & 8) != 0) {
                i3 = bVar.d;
            }
            if ((i4 & 16) != 0) {
                z = bVar.e;
            }
            boolean z2 = z;
            int i5 = i2;
            return bVar.a(str, i, i5, i3, z2);
        }

        public final b a(String str, int i, int i2, int i3, boolean z) {
            str.getClass();
            return new b(str, i, i2, i3, z);
        }

        public final boolean c() {
            return this.e;
        }

        public final String d() {
            return this.a;
        }

        public final int e() {
            return this.c;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof b)) {
                return false;
            }
            b bVar = (b) obj;
            return Intrinsics.areEqual(this.a, bVar.a) && this.b == bVar.b && this.c == bVar.c && this.d == bVar.d && this.e == bVar.e;
        }

        public final int f() {
            return this.b;
        }

        public int hashCode() {
            return (((((((this.a.hashCode() * 31) + Integer.hashCode(this.b)) * 31) + Integer.hashCode(this.c)) * 31) + Integer.hashCode(this.d)) * 31) + Boolean.hashCode(this.e);
        }

        public String toString() {
            return "InviteInfo(inviteCode=" + this.a + ", invitedCount=" + this.b + ", inviteGrantPoints=" + this.c + ", registerGrantPoints=" + this.d + ", bound=" + this.e + ")";
        }
    }

    public static final class c {
        public final String a;
        public final int b;
        public final String c;
        public final String d;
        public final int e;

        public c(String str, int i, String str2, String str3, int i2) {
            str.getClass();
            this.a = str;
            this.b = i;
            this.c = str2;
            this.d = str3;
            this.e = i2;
        }

        public final String a() {
            return this.a;
        }

        public final int b() {
            return this.b;
        }

        public final String c() {
            return this.d;
        }

        public final String d() {
            return this.c;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof c)) {
                return false;
            }
            c cVar = (c) obj;
            return Intrinsics.areEqual(this.a, cVar.a) && this.b == cVar.b && Intrinsics.areEqual(this.c, cVar.c) && Intrinsics.areEqual(this.d, cVar.d) && this.e == cVar.e;
        }

        public int hashCode() {
            int iHashCode = ((this.a.hashCode() * 31) + Integer.hashCode(this.b)) * 31;
            String str = this.c;
            int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
            String str2 = this.d;
            return ((iHashCode2 + (str2 != null ? str2.hashCode() : 0)) * 31) + Integer.hashCode(this.e);
        }

        public String toString() {
            return "RedeemResult(cardType=" + this.a + ", grantedPoints=" + this.b + ", membershipName=" + this.c + ", membershipExpireAt=" + this.d + ", membershipDays=" + this.e + ")";
        }
    }

    /* JADX INFO: renamed from: nu0$d$a, reason: case insensitive filesystem */
    public static final class C0104a implements d {
        public final int a;
        public final String b;

        public C0104a(int i, String str) {
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
            if (!(obj instanceof C0104a)) {
                return false;
            }
            C0104a c0104a = (C0104a) obj;
            return this.a == c0104a.a && Intrinsics.areEqual(this.b, c0104a.b);
        }

        public int hashCode() {
            return (Integer.hashCode(this.a) * 31) + this.b.hashCode();
        }

        public String toString() {
            return "Err(code=" + this.a + ", message=" + this.b + ")";
        }
    }

    /* JADX INFO: renamed from: nu0$d$b, reason: case insensitive filesystem */
    public static final class C0105b implements d {
        public final Object a;

        public C0105b(Object obj) {
            this.a = obj;
        }

        public final Object a() {
            return this.a;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            return (obj instanceof C0105b) && Intrinsics.areEqual(this.a, ((C0105b) obj).a);
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

    public final d a(String str, String str2) throws JSONException {
        d c0104a;
        Object objM38constructorimpl;
        int iOptInt;
        JSONObject jSONObject;
        str.getClass();
        str2.getClass();
        JSONObject jSONObjectPut = new JSONObject().put("inviteCode", str2);
        Request.Builder builderHeader = new Request.Builder().url(xl0.a.b("/v1/billing/invite/bind")).header("Authorization", "Bearer " + str);
        RequestBody.Companion companion = RequestBody.Companion;
        String string = jSONObjectPut.toString();
        string.getClass();
        try {
            Response responseExecute = c().newCall(builderHeader.post(companion.create(string, b)).build()).execute();
            try {
                ResponseBody responseBodyBody = responseExecute.body();
                String strString = responseBodyBody != null ? responseBodyBody.string() : null;
                if (strString == null) {
                    strString = "";
                }
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM38constructorimpl = Result.m38constructorimpl(new JSONObject(strString));
                } catch (Throwable th) {
                    Result.Companion companion3 = Result.INSTANCE;
                    objM38constructorimpl = Result.m38constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m44isFailureimpl(objM38constructorimpl)) {
                    objM38constructorimpl = null;
                }
                JSONObject jSONObject2 = (JSONObject) objM38constructorimpl;
                if (jSONObject2 != null) {
                    iOptInt = jSONObject2.optInt("code", responseExecute.isSuccessful() ? 0 : -1);
                } else {
                    iOptInt = -1;
                }
                if (responseExecute.isSuccessful() && iOptInt == 0) {
                    if (jSONObject2 == null || (jSONObject = jSONObject2.optJSONObject("data")) == null) {
                        jSONObject = new JSONObject();
                    }
                    int iOptInt2 = jSONObject.optInt("grantedPoints", 0);
                    String strOptString = jSONObject.optString("message");
                    strOptString.getClass();
                    if (StringsKt.isBlank(strOptString)) {
                        strOptString = null;
                    }
                    if (strOptString == null) {
                        strOptString = "绑定成功";
                    }
                    c0104a = new C0105b(new a(iOptInt2, strOptString));
                } else {
                    String strOptString2 = jSONObject2 != null ? jSONObject2.optString("message") : null;
                    if (strOptString2 == null || StringsKt.isBlank(strOptString2)) {
                        strOptString2 = null;
                    }
                    if (strOptString2 == null) {
                        strOptString2 = "绑定失败（HTTP " + responseExecute.code() + "）";
                    }
                    c0104a = new C0104a(iOptInt, strOptString2);
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
            c0104a = new C0104a(-1, message);
        }
        return c0104a;
    }

    public final d b(String str) {
        str.getClass();
        return d(new Request.Builder().url(xl0.a.b("/v1/billing/invite/info")).header("Authorization", "Bearer " + str).get().build());
    }

    public final OkHttpClient c() {
        return xl0.a.a();
    }

    public final d d(Request request) {
        d c0104a;
        Object objM38constructorimpl;
        int iOptInt;
        JSONObject jSONObject;
        try {
            Response responseExecute = c().newCall(request).execute();
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
                JSONObject jSONObject2 = (JSONObject) objM38constructorimpl;
                if (jSONObject2 != null) {
                    iOptInt = jSONObject2.optInt("code", responseExecute.isSuccessful() ? 0 : -1);
                } else {
                    iOptInt = -1;
                }
                if (responseExecute.isSuccessful() && iOptInt == 0) {
                    if (jSONObject2 == null || (jSONObject = jSONObject2.optJSONObject("data")) == null) {
                        jSONObject = new JSONObject();
                    }
                    String strOptString = jSONObject.optString("inviteCode");
                    strOptString.getClass();
                    c0104a = new C0105b(new b(strOptString, jSONObject.optInt("invitedCount", 0), jSONObject.optInt("inviteGrantPoints", 5), jSONObject.optInt("registerGrantPoints", 3), jSONObject.optBoolean("bound", false)));
                } else {
                    String strOptString2 = jSONObject2 != null ? jSONObject2.optString("message") : null;
                    if (strOptString2 == null || StringsKt.isBlank(strOptString2)) {
                        strOptString2 = null;
                    }
                    if (strOptString2 == null) {
                        strOptString2 = "加载失败（HTTP " + responseExecute.code() + "）";
                    }
                    c0104a = new C0104a(iOptInt, strOptString2);
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
            c0104a = new C0104a(-1, message);
        }
        return c0104a;
    }

    public final d e(String str, String str2) throws JSONException {
        d c0104a;
        Object objM38constructorimpl;
        int iOptInt;
        JSONObject jSONObject;
        String strOptString;
        String strOptString2;
        str.getClass();
        str2.getClass();
        JSONObject jSONObjectPut = new JSONObject().put("cardKey", str2);
        Request.Builder builderHeader = new Request.Builder().url(xl0.a.b("/v1/billing/redeem")).header("Authorization", "Bearer " + str);
        RequestBody.Companion companion = RequestBody.Companion;
        String string = jSONObjectPut.toString();
        string.getClass();
        try {
            Response responseExecute = c().newCall(builderHeader.post(companion.create(string, b)).build()).execute();
            try {
                ResponseBody responseBodyBody = responseExecute.body();
                String strString = responseBodyBody != null ? responseBodyBody.string() : null;
                if (strString == null) {
                    strString = "";
                }
                try {
                    Result.Companion companion2 = Result.INSTANCE;
                    objM38constructorimpl = Result.m38constructorimpl(new JSONObject(strString));
                } catch (Throwable th) {
                    Result.Companion companion3 = Result.INSTANCE;
                    objM38constructorimpl = Result.m38constructorimpl(ResultKt.createFailure(th));
                }
                if (Result.m44isFailureimpl(objM38constructorimpl)) {
                    objM38constructorimpl = null;
                }
                JSONObject jSONObject2 = (JSONObject) objM38constructorimpl;
                if (jSONObject2 != null) {
                    iOptInt = jSONObject2.optInt("code", responseExecute.isSuccessful() ? 0 : -1);
                } else {
                    iOptInt = -1;
                }
                if (responseExecute.isSuccessful() && iOptInt == 0) {
                    if (jSONObject2 == null || (jSONObject = jSONObject2.optJSONObject("data")) == null) {
                        jSONObject = new JSONObject();
                    }
                    String strOptString3 = jSONObject.optString("cardType", "points");
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("grantedMembership");
                    strOptString3.getClass();
                    c0104a = new C0105b(new c(strOptString3, jSONObject.optInt("grantedPoints", 0), (jSONObjectOptJSONObject == null || (strOptString2 = jSONObjectOptJSONObject.optString("name")) == null || StringsKt.isBlank(strOptString2)) ? null : strOptString2, (jSONObjectOptJSONObject == null || (strOptString = jSONObjectOptJSONObject.optString("expireAt")) == null || StringsKt.isBlank(strOptString)) ? null : strOptString, jSONObjectOptJSONObject != null ? jSONObjectOptJSONObject.optInt("days", 0) : 0));
                } else {
                    String strOptString4 = jSONObject2 != null ? jSONObject2.optString("message") : null;
                    if (strOptString4 == null || StringsKt.isBlank(strOptString4)) {
                        strOptString4 = null;
                    }
                    if (strOptString4 == null) {
                        strOptString4 = "兑换失败（HTTP " + responseExecute.code() + "）";
                    }
                    c0104a = new C0104a(iOptInt, strOptString4);
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
            c0104a = new C0104a(-1, message);
        }
        return c0104a;
    }
}
