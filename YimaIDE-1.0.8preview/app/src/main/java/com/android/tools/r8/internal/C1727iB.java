package com.android.tools.r8.internal;

import com.android.tools.r8.ResourceException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.iB, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1727iB extends IllegalStateException {
    public C1727iB(ResourceException resourceException) {
        super("Unexpected resource error", resourceException);
    }

    public C1727iB(String str) {
        super(str);
    }

    public C1727iB() {
    }

    public C1727iB(Exception exc) {
        super(exc);
    }
}
