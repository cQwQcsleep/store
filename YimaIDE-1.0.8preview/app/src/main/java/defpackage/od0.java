package defpackage;

import com.sun.org.apache.xalan.internal.templates.Constants;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.text.StringsKt;
import okhttp3.Interceptor;
import okhttp3.Response;
import org.json.JSONObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class od0 implements Interceptor {
    public Response intercept(Interceptor.Chain chain) {
        Object obj;
        Object obj2;
        chain.getClass();
        Response responseProceed = chain.proceed(chain.request().newBuilder().header("X-App-Version-Code", nd0.a.a()).build());
        if (responseProceed.code() == 403 || responseProceed.code() == 426) {
            try {
                Result.Companion companion = Result.Companion;
                obj = Result.constructor-impl(responseProceed.peekBody(65536L).string());
            } catch (Throwable th) {
                Result.Companion companion2 = Result.Companion;
                obj = Result.constructor-impl(ResultKt.createFailure(th));
            }
            JSONObject jSONObject = null;
            if (Result.isFailure-impl(obj)) {
                obj = null;
            }
            String str = (String) obj;
            if (str != null) {
                try {
                    obj2 = Result.constructor-impl(new JSONObject(str));
                } catch (Throwable th2) {
                    Result.Companion companion3 = Result.Companion;
                    obj2 = Result.constructor-impl(ResultKt.createFailure(th2));
                }
                jSONObject = (JSONObject) (Result.isFailure-impl(obj2) ? null : obj2);
            }
            if (jSONObject != null && jSONObject.optInt("code") == 2005) {
                JSONObject jSONObjectOptJSONObject = jSONObject.optJSONObject("data");
                if (jSONObjectOptJSONObject == null) {
                    jSONObjectOptJSONObject = new JSONObject();
                }
                String strOptString = jSONObjectOptJSONObject.optString("downloadUrl");
                strOptString.getClass();
                String string = StringsKt.trim(strOptString).toString();
                if (!StringsKt.isBlank(string)) {
                    md0 md0Var = md0.a;
                    int iOptInt = jSONObjectOptJSONObject.optInt("latestVersionCode", 0);
                    String strOptString2 = jSONObjectOptJSONObject.optString("latestVersionName", "");
                    strOptString2.getClass();
                    String strOptString3 = jSONObjectOptJSONObject.optString("title");
                    if (StringsKt.isBlank(strOptString3)) {
                        strOptString3 = "请更新至最新版本";
                    }
                    strOptString3.getClass();
                    String strOptString4 = jSONObjectOptJSONObject.optString(Constants.ELEMNAME_MESSAGE_STRING);
                    if (StringsKt.isBlank(strOptString4)) {
                        strOptString4 = jSONObject.optString(Constants.ELEMNAME_MESSAGE_STRING);
                    }
                    String str2 = StringsKt.isBlank(strOptString4) ? "请更新至最新版本" : strOptString4;
                    str2.getClass();
                    md0Var.b(new pbf.b(true, iOptInt, strOptString2, string, strOptString3, str2));
                }
            }
        }
        return responseProceed;
    }
}
