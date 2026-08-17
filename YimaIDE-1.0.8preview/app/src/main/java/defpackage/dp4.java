package defpackage;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.collections.SetsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.SequencesKt;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public abstract class dp4 {
    public static final Set a = SetsKt.setOf((Object[]) new String[]{"read_file", "current_file"});
    public static final Set b = SetsKt.setOf((Object[]) new String[]{"write_file", "create_file"});

    public static final int a(JSONArray jSONArray) throws JSONException {
        JSONArray jSONArrayOptJSONArray;
        JSONObject jSONObjectOptJSONObject;
        JSONArray jSONArray2;
        Object objM38constructorimpl;
        HashMap map;
        String strB;
        JSONArray jSONArrayOptJSONArray2;
        JSONObject jSONObjectOptJSONObject2;
        Object objM38constructorimpl2;
        JSONArray jSONArray3 = jSONArray;
        jSONArray3.getClass();
        HashMap map2 = new HashMap();
        int length = jSONArray3.length();
        for (int i = 0; i < length; i++) {
            JSONObject jSONObjectOptJSONObject3 = jSONArray3.optJSONObject(i);
            if (jSONObjectOptJSONObject3 != null && Intrinsics.areEqual(jSONObjectOptJSONObject3.optString("role"), "assistant") && (jSONArrayOptJSONArray2 = jSONObjectOptJSONObject3.optJSONArray("tool_calls")) != null) {
                int length2 = jSONArrayOptJSONArray2.length();
                for (int i2 = 0; i2 < length2; i2++) {
                    JSONObject jSONObjectOptJSONObject4 = jSONArrayOptJSONArray2.optJSONObject(i2);
                    if (jSONObjectOptJSONObject4 != null && (jSONObjectOptJSONObject2 = jSONObjectOptJSONObject4.optJSONObject("function")) != null) {
                        String strOptString = jSONObjectOptJSONObject2.optString("name");
                        if (a.contains(strOptString)) {
                            String strOptString2 = jSONObjectOptJSONObject4.optString("id");
                            strOptString2.getClass();
                            if (!StringsKt.isBlank(strOptString2)) {
                                try {
                                    Result.Companion companion = Result.INSTANCE;
                                    objM38constructorimpl2 = Result.m38constructorimpl(new JSONObject(jSONObjectOptJSONObject2.optString("arguments")));
                                } catch (Throwable th) {
                                    Result.Companion companion2 = Result.INSTANCE;
                                    objM38constructorimpl2 = Result.m38constructorimpl(ResultKt.createFailure(th));
                                }
                                if (Result.m44isFailureimpl(objM38constructorimpl2)) {
                                    objM38constructorimpl2 = null;
                                }
                                JSONObject jSONObject = (JSONObject) objM38constructorimpl2;
                                strOptString.getClass();
                                String strOptString3 = jSONObject != null ? jSONObject.optString("path") : null;
                                if (strOptString3 == null) {
                                    strOptString3 = "";
                                }
                                map2.put(strOptString2, new wa1(strOptString, StringsKt.trim(strOptString3).toString()));
                            }
                        }
                    }
                }
            }
        }
        HashSet hashSet = new HashSet();
        int length3 = jSONArray3.length() - 1;
        int i3 = 0;
        while (length3 > 0) {
            JSONObject jSONObjectOptJSONObject5 = jSONArray3.optJSONObject(length3);
            if (jSONObjectOptJSONObject5 != null) {
                String strOptString4 = jSONObjectOptJSONObject5.optString("role");
                if (Intrinsics.areEqual(strOptString4, "tool")) {
                    wa1 wa1Var = (wa1) map2.get(jSONObjectOptJSONObject5.optString("tool_call_id"));
                    if (wa1Var != null && a.contains(wa1Var.a())) {
                        String strOptString5 = jSONObjectOptJSONObject5.optString("content");
                        if (Intrinsics.areEqual(wa1Var.a(), "current_file")) {
                            strOptString5.getClass();
                            strB = b(strOptString5);
                        } else {
                            strB = wa1Var.b();
                            if (StringsKt.isBlank(strB)) {
                                strB = null;
                            }
                        }
                        if (strB != null && !hashSet.add(strB) && strOptString5.length() > 1500) {
                            jSONObjectOptJSONObject5.put("content", "[此文件当时的内容快照已被后续读取/写入取代，为节省 Token 已省略；需要当前内容请重新 read_file]");
                            i3++;
                        }
                    }
                } else if (Intrinsics.areEqual(strOptString4, "assistant") && (jSONArrayOptJSONArray = jSONObjectOptJSONObject5.optJSONArray("tool_calls")) != null) {
                    int length4 = jSONArrayOptJSONArray.length() - 1;
                    while (-1 < length4) {
                        JSONObject jSONObjectOptJSONObject6 = jSONArrayOptJSONArray.optJSONObject(length4);
                        if (jSONObjectOptJSONObject6 == null || (jSONObjectOptJSONObject = jSONObjectOptJSONObject6.optJSONObject("function")) == null) {
                            jSONArray2 = jSONArrayOptJSONArray;
                        } else {
                            jSONArray2 = jSONArrayOptJSONArray;
                            if (b.contains(jSONObjectOptJSONObject.optString("name"))) {
                                String strOptString6 = jSONObjectOptJSONObject.optString("arguments");
                                try {
                                    Result.Companion companion3 = Result.INSTANCE;
                                    objM38constructorimpl = Result.m38constructorimpl(new JSONObject(strOptString6));
                                } catch (Throwable th2) {
                                    Result.Companion companion4 = Result.INSTANCE;
                                    objM38constructorimpl = Result.m38constructorimpl(ResultKt.createFailure(th2));
                                }
                                if (Result.m44isFailureimpl(objM38constructorimpl)) {
                                    objM38constructorimpl = null;
                                }
                                JSONObject jSONObject2 = (JSONObject) objM38constructorimpl;
                                if (jSONObject2 != null) {
                                    String strOptString7 = jSONObject2.optString("path");
                                    if (strOptString7 == null) {
                                        strOptString7 = "";
                                    }
                                    String string = StringsKt.trim(strOptString7).toString();
                                    if (string.length() != 0 && !hashSet.add(string)) {
                                        map = map2;
                                        if (strOptString6.length() > 1500) {
                                            jSONObjectOptJSONObject.put("arguments", new JSONObject().put("path", string).put("content", "[此版本文件内容已被后续读取/写入取代，为节省 Token 已省略；需要当前内容请重新 read_file]").toString());
                                            i3++;
                                        }
                                    }
                                }
                            }
                            length4--;
                            jSONArrayOptJSONArray = jSONArray2;
                            map2 = map;
                        }
                        map = map2;
                        length4--;
                        jSONArrayOptJSONArray = jSONArray2;
                        map2 = map;
                    }
                }
            }
            length3--;
            jSONArray3 = jSONArray;
            map2 = map2;
        }
        return i3;
    }

    public static final String b(String str) {
        String str2 = (String) SequencesKt.firstOrNull(StringsKt.lineSequence(str));
        if (str2 == null || !StringsKt.startsWith$default(str2, "当前文件：", false, 2, (Object) null)) {
            return null;
        }
        String string = StringsKt.trim(StringsKt.removePrefix(str2, "当前文件：")).toString();
        if (StringsKt.isBlank(string)) {
            return null;
        }
        return string;
    }
}
