package com.reandroid.apk;

import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class DecodeFilter {
    private final Set<String> mExcludePaths = new HashSet();

    public void addExclude(String str) {
        if (str != null) {
            this.mExcludePaths.add(str);
        }
    }

    public boolean isExcluded(String str) {
        return this.mExcludePaths.contains(str);
    }
}
