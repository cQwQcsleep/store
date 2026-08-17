package com.android.tools.r8.internal;

import java.util.Collection;
import java.util.Collections;

/* JADX INFO: renamed from: com.android.tools.r8.internal.iO, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C1740iO extends C1824jO {
    public byte[] b;
    public C1824jO c;

    public C1740iO(byte[] bArr) {
        super(Collections.EMPTY_LIST);
        this.c = null;
        this.b = bArr;
    }

    @Override // com.android.tools.r8.internal.C1824jO
    public final Collection a() {
        if (this.c == null) {
            this.c = C1824jO.a(this.b);
            this.b = null;
        }
        return this.c.a();
    }
}
