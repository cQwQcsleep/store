package com.android.tools.r8;

import com.android.tools.r8.origin.Origin;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class ResourceException extends Exception {
    private final Origin b;

    public ResourceException(Origin origin, String str) {
        super(str);
        this.b = origin;
    }

    public Origin getOrigin() {
        return this.b;
    }

    public ResourceException(Origin origin, Throwable th) {
        super(th);
        this.b = origin;
    }

    public ResourceException(Origin origin, String str, Throwable th) {
        super(str, th);
        this.b = origin;
    }
}
