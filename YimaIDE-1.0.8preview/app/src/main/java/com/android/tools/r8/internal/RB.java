package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class RB extends IOException {
    public static final /* synthetic */ int c = 0;
    public TN b;

    public RB(IOException iOException) {
        super(iOException.getMessage(), iOException);
        this.b = null;
    }

    public final IOException a() {
        return getCause() instanceof IOException ? (IOException) getCause() : this;
    }

    public RB(String str) {
        super(str);
        this.b = null;
    }
}
