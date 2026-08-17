package com.android.tools.r8.origin;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class a extends Origin {
    public static final a f = new a();

    public a() {
        super(Origin.root());
    }

    @Override // com.android.tools.r8.origin.Origin
    public final String part() {
        return "Command line";
    }
}
