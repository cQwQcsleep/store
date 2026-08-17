package com.android.tools.r8.internal;

import java.io.IOException;

/* JADX INFO: renamed from: com.android.tools.r8.internal.Od, reason: case insensitive filesystem */
/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class C0715Od extends IOException {
    public C0715Od() {
        super("CodedOutputStream was writing to a flat byte array and ran out of space.");
    }
}
