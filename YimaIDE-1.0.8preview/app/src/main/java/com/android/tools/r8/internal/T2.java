package com.android.tools.r8.internal;

import java.util.LinkedHashSet;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public abstract class T2 {
    public static final LinkedHashSet a;

    static {
        String[] strArr = {"android.support.v7.widget.SuggestionsAdapter", "android.support.v7.internal.widget.ResourcesWrapper", "android.support.v7.widget.ResourcesWrapper", "android.support.v7.widget.TintContextWrapper$TintResources"};
        LinkedHashSet linkedHashSet = new LinkedHashSet(AbstractC1823jN.a(4));
        for (int i = 0; i < 4; i++) {
            linkedHashSet.add(strArr[i]);
        }
        a = linkedHashSet;
    }
}
