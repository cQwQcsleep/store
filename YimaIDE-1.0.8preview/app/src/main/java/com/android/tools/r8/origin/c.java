package com.android.tools.r8.origin;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class c extends Origin {
    public static final c f = new c(Origin.root());

    public c(Origin origin) {
        super(origin);
    }

    public static Origin a() {
        return f;
    }

    @Override // com.android.tools.r8.origin.Origin
    public final String part() {
        return "<synthetic>";
    }
}
