package defpackage;

import android.content.Context;
import android.content.SharedPreferences;
import androidx.security.crypto.EncryptedSharedPreferences;
import androidx.security.crypto.MasterKey;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import kotlin.Result;
import kotlin.ResultKt;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.collections.IntIterator;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.text.StringsKt;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class uv9 {
    public static volatile SharedPreferences b;
    public static final uv9 a = new uv9();
    public static final Object c = new Object();
    public static final int d = 8;

    public final List a(Context context) {
        List listB;
        context.getClass();
        synchronized (c) {
            listB = a.b(context);
        }
        return listB;
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0041  */
    public final List b(Context context) {
        Object obj;
        tv9 tv9Var;
        String string = d(context).getString("servers_json", null);
        if (string == null) {
            return CollectionsKt.emptyList();
        }
        try {
            Result.Companion companion = Result.Companion;
            JSONArray jSONArray = new JSONArray(string);
            IntRange intRangeUntil = RangesKt.until(0, jSONArray.length());
            ArrayList arrayList = new ArrayList();
            IntIterator it = intRangeUntil.iterator();
            while (it.hasNext()) {
                JSONObject jSONObjectOptJSONObject = jSONArray.optJSONObject(it.nextInt());
                if (jSONObjectOptJSONObject == null) {
                    tv9Var = null;
                } else {
                    String strOptString = jSONObjectOptJSONObject.optString("id", "");
                    strOptString.getClass();
                    String string2 = StringsKt.trim(strOptString).toString();
                    String strOptString2 = jSONObjectOptJSONObject.optString("url", "");
                    strOptString2.getClass();
                    String string3 = StringsKt.trim(strOptString2).toString();
                    if (StringsKt.isBlank(string2) || StringsKt.isBlank(string3)) {
                        tv9Var = null;
                    } else {
                        JSONObject jSONObjectOptJSONObject2 = jSONObjectOptJSONObject.optJSONObject("headers");
                        LinkedHashMap linkedHashMap = new LinkedHashMap();
                        if (jSONObjectOptJSONObject2 != null) {
                            Iterator<String> itKeys = jSONObjectOptJSONObject2.keys();
                            while (itKeys.hasNext()) {
                                String next = itKeys.next();
                                String strOptString3 = jSONObjectOptJSONObject2.optString(next, "");
                                next.getClass();
                                if (!StringsKt.isBlank(next)) {
                                    strOptString3.getClass();
                                    if (!StringsKt.isBlank(strOptString3)) {
                                        linkedHashMap.put(next, strOptString3);
                                    }
                                }
                            }
                        }
                        String strOptString4 = jSONObjectOptJSONObject.optString("name", "");
                        if (StringsKt.isBlank(strOptString4)) {
                            strOptString4 = "MCP";
                        }
                        String str = strOptString4;
                        str.getClass();
                        tv9Var = new tv9(string2, str, string3, jSONObjectOptJSONObject.optBoolean("enabled", true), linkedHashMap);
                    }
                }
                if (tv9Var != null) {
                    arrayList.add(tv9Var);
                }
            }
            obj = Result.constructor-impl(arrayList);
        } catch (Throwable th) {
            Result.Companion companion2 = Result.Companion;
            obj = Result.constructor-impl(ResultKt.createFailure(th));
        }
        List listEmptyList = CollectionsKt.emptyList();
        if (Result.isFailure-impl(obj)) {
            obj = listEmptyList;
        }
        return (List) obj;
    }

    public final String c(List list) throws IOException {
        list.getClass();
        List list2 = list;
        ArrayList arrayList = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it = list2.iterator();
        while (it.hasNext()) {
            arrayList.add(((tv9) it.next()).e());
        }
        HashSet hashSet = CollectionsKt.toHashSet(arrayList);
        ArrayList arrayList2 = new ArrayList(CollectionsKt.collectionSizeOrDefault(list2, 10));
        Iterator it2 = list2.iterator();
        while (it2.hasNext()) {
            arrayList2.add(((tv9) it2.next()).g());
        }
        HashSet hashSet2 = CollectionsKt.toHashSet(arrayList2);
        for (int i = 0; i < 32; i++) {
            String string = UUID.randomUUID().toString();
            string.getClass();
            String strTake = StringsKt.take(StringsKt.replace$default(string, "-", "", false, 4, (Object) null), 10);
            StringBuilder sb = new StringBuilder();
            int length = strTake.length();
            for (int i2 = 0; i2 < length; i2++) {
                char cCharAt = strTake.charAt(i2);
                if (Character.isLetterOrDigit(cCharAt) || cCharAt == '_' || cCharAt == '-') {
                    sb.append(cCharAt);
                }
            }
            String strTake2 = StringsKt.take(sb.toString(), 12);
            if (StringsKt.isBlank(strTake2)) {
                strTake2 = "srv";
            }
            if (!hashSet.contains(strTake) && !hashSet2.contains(strTake2)) {
                return strTake;
            }
        }
        String string2 = UUID.randomUUID().toString();
        string2.getClass();
        return StringsKt.replace$default(string2, "-", "", false, 4, (Object) null);
    }

    public final SharedPreferences d(Context context) {
        SharedPreferences sharedPreferences = b;
        if (sharedPreferences != null) {
            return sharedPreferences;
        }
        synchronized (this) {
            SharedPreferences sharedPreferences2 = b;
            if (sharedPreferences2 != null) {
                return sharedPreferences2;
            }
            MasterKey masterKeyBuild = new MasterKey.Builder(context.getApplicationContext()).setKeyScheme(MasterKey.KeyScheme.AES256_GCM).build();
            masterKeyBuild.getClass();
            SharedPreferences sharedPreferencesCreate = EncryptedSharedPreferences.create(context.getApplicationContext(), "yima_mcp_servers", masterKeyBuild, EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV, EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM);
            sharedPreferencesCreate.getClass();
            b = sharedPreferencesCreate;
            return sharedPreferencesCreate;
        }
    }

    public final void e(Context context, String str) {
        context.getClass();
        str.getClass();
        synchronized (c) {
            try {
                uv9 uv9Var = a;
                List listB = uv9Var.b(context);
                ArrayList arrayList = new ArrayList();
                for (Object obj : listB) {
                    if (!Intrinsics.areEqual(((tv9) obj).e(), str)) {
                        arrayList.add(obj);
                    }
                }
                uv9Var.f(context, arrayList);
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void f(Context context, List list) throws JSONException {
        JSONArray jSONArray = new JSONArray();
        Iterator it = list.iterator();
        while (it.hasNext()) {
            tv9 tv9Var = (tv9) it.next();
            JSONObject jSONObject = new JSONObject();
            jSONObject.put("id", tv9Var.e());
            jSONObject.put("name", tv9Var.f());
            jSONObject.put("url", tv9Var.h());
            jSONObject.put("enabled", tv9Var.c());
            if (!tv9Var.d().isEmpty()) {
                JSONObject jSONObject2 = new JSONObject();
                for (Map.Entry entry : tv9Var.d().entrySet()) {
                    jSONObject2.put((String) entry.getKey(), (String) entry.getValue());
                }
                Unit unit = Unit.INSTANCE;
                jSONObject.put("headers", jSONObject2);
            }
            jSONArray.put(jSONObject);
        }
        d(context).edit().putString("servers_json", jSONArray.toString()).commit();
    }

    public final void g(Context context, tv9 tv9Var) {
        context.getClass();
        tv9Var.getClass();
        synchronized (c) {
            try {
                List mutableList = CollectionsKt.toMutableList(a.b(context));
                Iterator it = mutableList.iterator();
                int i = 0;
                while (true) {
                    if (!it.hasNext()) {
                        i = -1;
                        break;
                    } else if (Intrinsics.areEqual(((tv9) it.next()).e(), tv9Var.e())) {
                        break;
                    } else {
                        i++;
                    }
                }
                if (i >= 0) {
                    mutableList.set(i, tv9Var);
                } else {
                    mutableList.add(tv9Var);
                }
                a.f(context, mutableList);
                Unit unit = Unit.INSTANCE;
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
