package com.reandroid.dex.ins;

import com.reandroid.dex.id.StringId;
import com.reandroid.dex.key.StringKey;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public interface ConstString {
    int getRegister();

    String getString();

    void setRegister(int i);

    void setString(StringKey stringKey);

    default void setString(String str) {
        setString(StringKey.create(str));
    }

    /* JADX WARN: Multi-variable type inference failed */
    default void setString(StringId stringId) {
        ((SizeXIns) this).setSectionId(stringId);
    }
}
