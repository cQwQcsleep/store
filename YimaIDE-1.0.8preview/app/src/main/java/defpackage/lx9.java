package defpackage;

import com.sun.org.apache.xalan.internal.templates.Constants;
import java.util.ArrayList;
import java.util.List;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class lx9 {
    public static final lx9 a = new lx9();

    public final String a(JSONObject jSONObject) {
        jSONObject.getClass();
        boolean zOptBoolean = jSONObject.optBoolean("isError", false);
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("content");
        if (jSONArrayOptJSONArray == null) {
            jSONArrayOptJSONArray = new JSONArray();
        }
        StringBuilder sb = new StringBuilder();
        if (zOptBoolean) {
            sb.append("失败：");
        }
        int length = jSONArrayOptJSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                if (Intrinsics.areEqual(jSONObjectOptJSONObject.optString("type"), "text")) {
                    String strOptString = jSONObjectOptJSONObject.optString("text", "");
                    strOptString.getClass();
                    if (!StringsKt.isBlank(strOptString)) {
                        if (sb.length() > 0 && !StringsKt.endsWith$default(sb, '\n', false, 2, (Object) null)) {
                            sb.append('\n');
                        }
                        sb.append(strOptString);
                    }
                } else {
                    String strOptString2 = jSONObjectOptJSONObject.optString("text", jSONObjectOptJSONObject.toString());
                    strOptString2.getClass();
                    if (!StringsKt.isBlank(strOptString2)) {
                        if (sb.length() > 0 && !StringsKt.endsWith$default(sb, '\n', false, 2, (Object) null)) {
                            sb.append('\n');
                        }
                        sb.append(strOptString2);
                    }
                }
            }
        }
        if (sb.length() != 0) {
            return sb.toString();
        }
        String strOptString3 = jSONObject.optString(Constants.ELEMNAME_MESSAGE_STRING, "");
        strOptString3.getClass();
        if (StringsKt.isBlank(strOptString3)) {
            return zOptBoolean ? "失败：MCP 工具返回空结果" : "（空结果）";
        }
        if (!zOptBoolean) {
            return strOptString3;
        }
        return "失败：" + strOptString3;
    }

    public final String b(tv9 tv9Var, String str) {
        tv9Var.getClass();
        str.getClass();
        String str2 = "mcp_" + tv9Var.g() + "_" + str;
        if (str2.length() <= 64) {
            return str2;
        }
        String strG = tv9Var.g();
        String hexString = Integer.toHexString(str.hashCode());
        hexString.getClass();
        String str3 = "mcp_" + strG + "_" + StringsKt.takeLast(StringsKt.padStart(hexString, 8, '0'), 8) + "_";
        int iCoerceAtLeast = RangesKt.coerceAtLeast(64 - str3.length(), 0);
        return StringsKt.take(str3 + (iCoerceAtLeast > 0 ? StringsKt.takeLast(str, iCoerceAtLeast) : ""), 64);
    }

    public final JSONObject c(JSONObject jSONObject) throws JSONException {
        JSONObject jSONObject2 = new JSONObject(jSONObject.toString());
        if (!jSONObject2.has("type")) {
            jSONObject2.put("type", "object");
        }
        if (!jSONObject2.has("properties")) {
            jSONObject2.put("properties", new JSONObject());
        }
        jSONObject2.remove("$schema");
        jSONObject2.remove("additionalProperties");
        return jSONObject2;
    }

    public final Pair d(String str) {
        String strRemovePrefix;
        int iIndexOf$default;
        str.getClass();
        if (StringsKt.startsWith$default(str, "mcp_", false, 2, (Object) null) && (iIndexOf$default = StringsKt.indexOf$default((strRemovePrefix = StringsKt.removePrefix(str, "mcp_")), '_', 0, false, 6, (Object) null)) > 0 && iIndexOf$default < strRemovePrefix.length() - 1) {
            String strSubstring = strRemovePrefix.substring(0, iIndexOf$default);
            String strSubstring2 = strRemovePrefix.substring(iIndexOf$default + 1);
            if (!StringsKt.isBlank(strSubstring) && !StringsKt.isBlank(strSubstring2)) {
                return TuplesKt.to(strSubstring, strSubstring2);
            }
        }
        return null;
    }

    public final List e(JSONObject jSONObject) throws JSONException {
        jSONObject.getClass();
        JSONArray jSONArrayOptJSONArray = jSONObject.optJSONArray("tools");
        if (jSONArrayOptJSONArray == null) {
            return CollectionsKt.emptyList();
        }
        ArrayList arrayList = new ArrayList(jSONArrayOptJSONArray.length());
        int length = jSONArrayOptJSONArray.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject = jSONArrayOptJSONArray.optJSONObject(i);
            if (jSONObjectOptJSONObject != null) {
                String strOptString = jSONObjectOptJSONObject.optString("name", "");
                strOptString.getClass();
                String string = StringsKt.trim(strOptString).toString();
                if (!StringsKt.isBlank(string)) {
                    String strOptString2 = jSONObjectOptJSONObject.optString("description", "");
                    strOptString2.getClass();
                    String string2 = StringsKt.trim(strOptString2).toString();
                    JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("inputSchema");
                    if (jSONObjectOptJSONObject2 == null) {
                        jSONObjectOptJSONObject2 = new JSONObject().put("type", "object").put("properties", new JSONObject());
                    }
                    jSONObjectOptJSONObject2.getClass();
                    arrayList.add(new mx9(string, string2, jSONObjectOptJSONObject2));
                }
            }
        }
        return arrayList;
    }

    public final JSONObject f(tv9 tv9Var, mx9 mx9Var) throws JSONException {
        tv9Var.getClass();
        mx9Var.getClass();
        String strB = b(tv9Var, mx9Var.c());
        StringBuilder sb = new StringBuilder("[MCP:");
        sb.append(tv9Var.f());
        sb.append("] ");
        if (!Intrinsics.areEqual(strB, "mcp_" + tv9Var.g() + "_" + mx9Var.c())) {
            sb.append("(");
            sb.append(mx9Var.c());
            sb.append(") ");
        }
        String strA = mx9Var.a();
        if (StringsKt.isBlank(strA)) {
            strA = mx9Var.c();
        }
        sb.append(strA);
        String strTake = StringsKt.take(sb.toString(), 400);
        JSONObject jSONObjectC = c(mx9Var.b());
        JSONObject jSONObject = new JSONObject();
        jSONObject.put("type", Constants.EXSLT_ELEMNAME_FUNCTION_STRING);
        JSONObject jSONObject2 = new JSONObject();
        jSONObject2.put("name", strB);
        jSONObject2.put("description", strTake);
        jSONObject2.put("parameters", jSONObjectC);
        Unit unit = Unit.INSTANCE;
        jSONObject.put(Constants.EXSLT_ELEMNAME_FUNCTION_STRING, jSONObject2);
        return jSONObject;
    }
}
