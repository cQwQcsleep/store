package com.android.tools.r8.internal;

import java.io.Serializable;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class J40 implements Serializable {
    public final Pattern b;

    public J40(String str) {
        Pattern patternCompile = Pattern.compile(str);
        KB.b(patternCompile, "compile(...)");
        this.b = patternCompile;
    }

    public final String toString() {
        String string = this.b.toString();
        KB.b(string, "toString(...)");
        return string;
    }
}
