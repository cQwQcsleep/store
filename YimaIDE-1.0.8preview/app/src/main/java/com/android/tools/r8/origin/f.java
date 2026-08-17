package com.android.tools.r8.origin;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class f extends Origin {
    public final String f;

    public f(String str, Class<?> cls) {
        super(Origin.root());
        this.f = str;
    }

    @Override // com.android.tools.r8.origin.Origin
    public final String part() {
        return "synthesized for " + this.f;
    }
}
