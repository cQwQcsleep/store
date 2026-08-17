package org.bouncycastle.crypto.engines;

import org.bouncycastle.util.Memoable;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public final class Zuc256Engine extends Zuc256CoreEngine {
    public Zuc256Engine() {
    }

    @Override // org.bouncycastle.crypto.engines.Zuc256CoreEngine, org.bouncycastle.crypto.engines.Zuc128CoreEngine
    public Memoable copy() {
        return new Zuc256Engine(this);
    }

    public Zuc256Engine(int i) {
        super(i);
    }

    private Zuc256Engine(Zuc256Engine zuc256Engine) {
        super(zuc256Engine);
    }
}
