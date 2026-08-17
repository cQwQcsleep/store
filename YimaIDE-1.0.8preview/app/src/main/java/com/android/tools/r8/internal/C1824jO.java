package com.android.tools.r8.internal;

import java.nio.charset.StandardCharsets;
import java.util.Collection;

/* JADX INFO: renamed from: com.android.tools.r8.internal.jO, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public class C1824jO {
    public final Collection a;

    public C1824jO(Collection collection) {
        this.a = collection;
    }

    public static C1824jO a(byte[] bArr) {
        return new C1824jO(Wf0.a(new String(bArr, StandardCharsets.UTF_8), ';'));
    }

    public Collection a() {
        return this.a;
    }
}
