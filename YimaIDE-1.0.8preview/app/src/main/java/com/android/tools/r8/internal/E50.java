package com.android.tools.r8.internal;

import java.util.HashMap;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public enum E50 {
    c("anim"),
    d("animator"),
    e("color"),
    f("drawable"),
    g("font"),
    h("interpolator"),
    i("layout"),
    j("menu"),
    k("mipmap"),
    l("navigation"),
    m("raw"),
    n("transition"),
    o("values"),
    p("xml");

    public static final HashMap q;
    public static final /* synthetic */ boolean s = true;
    public final String b;

    static {
        E50[] e50ArrValues = values();
        q = new HashMap(e50ArrValues.length * 2);
        for (E50 e50 : e50ArrValues) {
            q.put(e50.b, e50);
        }
    }

    E50(String str) {
        this.b = str;
    }
}
