package com.android.tools.r8.internal;

import com.android.tools.r8.naming.C3313b;
import com.android.tools.r8.retrace.ProguardMapProducer;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public final class ZY implements ProguardMapProducer {
    public final C3313b a;

    public ZY(C3313b c3313b) {
        this.a = c3313b;
    }

    @Override // com.android.tools.r8.retrace.ProguardMapProducer
    public final InputStream get() {
        throw new Kk0("Should never get on ProguardMapProducerInternal");
    }
}
