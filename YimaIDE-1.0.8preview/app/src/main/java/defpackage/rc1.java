package defpackage;

import android.util.Log;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.io.CloseableKt;
import kotlin.text.StringsKt;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import org.json.JSONObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public final class rc1 {
    public static final rc1 a = new rc1();

    public final String a() {
        Object objM38constructorimpl;
        String string;
        JSONObject jSONObjectOptJSONObject;
        String strOptString;
        try {
            Response responseExecute = b().newCall(new Request.Builder().url(xl0.a.b("/v1/app/card-purchase")).get().build()).execute();
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
                    if (jSONObject == null || (jSONObjectOptJSONObject = jSONObject.optJSONObject("data")) == null || (strOptString = jSONObjectOptJSONObject.optString("purchaseUrl")) == null || (string = StringsKt.trim(strOptString).toString()) == null || StringsKt.isBlank(string)) {
                        string = null;
                    }
                    CloseableKt.closeFinally(responseExecute, (Throwable) null);
                    return string;
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
            Log.w("CardPurchaseApi", "获取购买链接失败", th4);
            return null;
        }
    }

    public final OkHttpClient b() {
        return xl0.a.a();
    }
}
