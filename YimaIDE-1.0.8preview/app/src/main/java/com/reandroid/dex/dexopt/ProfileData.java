package com.reandroid.dex.dexopt;

import com.reandroid.json.JSONConvert;
import com.reandroid.json.JSONObject;
import com.reandroid.utils.ObjectsUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ProfileData extends LinkableProfileItem, JSONConvert<JSONObject> {
    default boolean equalsName(String str) {
        return ObjectsUtil.equals(getName(), str);
    }

    String getName();

    boolean isInitialized();

    void setInitialized(boolean z);

    void setName(String str);
}
