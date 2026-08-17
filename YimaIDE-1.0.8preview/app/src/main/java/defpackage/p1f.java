package defpackage;

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
public final class p1f {
    public static final p1f a = new p1f();

    public static final class a {
        public final String a;
        public final String b;
        public final long c;
        public final double d;
        public final double e;
        public final String f;
        public final String g;
        public final String h;
        public final long i;
        public final String j;

        public a(String str, String str2, long j, double d, double d2, String str3, String str4, String str5, long j2, String str6) {
            str.getClass();
            str2.getClass();
            str3.getClass();
            str4.getClass();
            str6.getClass();
            this.a = str;
            this.b = str2;
            this.c = j;
            this.d = d;
            this.e = d2;
            this.f = str3;
            this.g = str4;
            this.h = str5;
            this.i = j2;
            this.j = str6;
        }

        public final String a() {
            return this.h;
        }

        public final String b() {
            return this.f;
        }

        public final String c() {
            return this.g;
        }

        public final String d() {
            return this.b;
        }

        public final double e() {
            return this.e;
        }

        public boolean equals(Object obj) {
            if (this == obj) {
                return true;
            }
            if (!(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            return Intrinsics.areEqual(this.a, aVar.a) && Intrinsics.areEqual(this.b, aVar.b) && this.c == aVar.c && Double.compare(this.d, aVar.d) == 0 && Double.compare(this.e, aVar.e) == 0 && Intrinsics.areEqual(this.f, aVar.f) && Intrinsics.areEqual(this.g, aVar.g) && Intrinsics.areEqual(this.h, aVar.h) && this.i == aVar.i && Intrinsics.areEqual(this.j, aVar.j);
        }

        public final double f() {
            return this.d;
        }

        public int hashCode() {
            int iHashCode = ((((((((((((this.a.hashCode() * 31) + this.b.hashCode()) * 31) + Long.hashCode(this.c)) * 31) + Double.hashCode(this.d)) * 31) + Double.hashCode(this.e)) * 31) + this.f.hashCode()) * 31) + this.g.hashCode()) * 31;
            String str = this.h;
            return ((((iHashCode + (str == null ? 0 : str.hashCode())) * 31) + Long.hashCode(this.i)) * 31) + this.j.hashCode();
        }

        public String toString() {
            return "Profile(userId=" + this.a + ", phone=" + this.b + ", quotaBalance=" + this.c + ", quotaPoints=" + this.d + ", quotaPercent=" + this.e + ", membershipLevel=" + this.f + ", membershipName=" + this.g + ", membershipExpireAt=" + this.h + ", points=" + this.i + ", inviteCode=" + this.j + ")";
        }
    }

    public interface b {

        public static final class a implements b {
            public final int a;
            public final String b;

            public a(int i, String str) {
                str.getClass();
                this.a = i;
                this.b = str;
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
                return "Err(code=" + this.a + ", message=" + this.b + ")";
            }
        }

        /* JADX INFO: renamed from: p1f$b$b, reason: collision with other inner class name */
        public static final class C0087b implements b {
            public final Object a;

            public C0087b(Object obj) {
                this.a = obj;
            }

            public final Object a() {
                return this.a;
            }

            public boolean equals(Object obj) {
                if (this == obj) {
                    return true;
                }
                return (obj instanceof C0087b) && Intrinsics.areEqual(this.a, ((C0087b) obj).a);
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

    public final b a(String str) {
        b aVar;
        Object objM38constructorimpl;
        int iOptInt;
        JSONObject jSONObject;
        String string;
        str.getClass();
        try {
            Response responseExecute = b().newCall(new Request.Builder().url(xl0.a.b("/v1/user/profile")).header("Authorization", "Bearer " + str).get().build()).execute();
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
                    JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("membership");
                    if (jSONObjectOptJSONObject == null) {
                        jSONObjectOptJSONObject = new JSONObject();
                    }
                    Object objOpt = jSONObjectOptJSONObject.opt("expireAt");
                    String str2 = (objOpt == null || (string = objOpt.toString()) == null || Intrinsics.areEqual(string, "null")) ? null : string;
                    String strOptString = jSONObject.optString("userId");
                    strOptString.getClass();
                    String strOptString2 = jSONObject.optString("phone");
                    strOptString2.getClass();
                    long jOptLong = jSONObject.optLong("quotaBalance", 0L);
                    double dOptDouble = jSONObject.optDouble("quotaPoints", 0.0d);
                    double dOptDouble2 = jSONObject.optDouble("quotaPercent", 0.0d);
                    String strOptString3 = jSONObjectOptJSONObject.optString("level", "free");
                    strOptString3.getClass();
                    String strOptString4 = jSONObjectOptJSONObject.optString("name", "普通用户");
                    strOptString4.getClass();
                    long jOptLong2 = jSONObject.optLong("points", 0L);
                    String strOptString5 = jSONObject.optString("inviteCode");
                    strOptString5.getClass();
                    aVar = new b.C0087b(new a(strOptString, strOptString2, jOptLong, dOptDouble, dOptDouble2, strOptString3, strOptString4, str2, jOptLong2, strOptString5));
                } else {
                    String strOptString6 = jSONObject2 != null ? jSONObject2.optString("message") : null;
                    if (strOptString6 == null || StringsKt.isBlank(strOptString6)) {
                        strOptString6 = null;
                    }
                    if (strOptString6 == null) {
                        strOptString6 = "请求失败（HTTP " + responseExecute.code() + "）";
                    }
                    aVar = new b.a(iOptInt, strOptString6);
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
            aVar = new b.a(-1, message);
        }
        return aVar;
    }

    public final OkHttpClient b() {
        return xl0.a.a();
    }
}
