package com.intellij.util.containers;

import com.intellij.openapi.util.text.StringUtilRt;
import com.intellij.openapi.util.text.Strings;
import it.unimi.dsi.fastutil.Hash;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class FastUtilCaseInsensitiveStringHashingStrategy implements Hash.Strategy<String> {
    static final Hash.Strategy<String> INSTANCE = new FastUtilCaseInsensitiveStringHashingStrategy();

    public boolean equals(String str, String str2) {
        if (Strings.areSameInstance(str, str2)) {
            return true;
        }
        return str != null && str.equalsIgnoreCase(str2);
    }

    public int hashCode(String str) {
        if (str == null) {
            return 0;
        }
        return StringUtilRt.stringHashCodeInsensitive(str);
    }
}
