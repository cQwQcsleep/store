package org.bouncycastle.cms;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
interface MACProvider {
    byte[] getMAC();

    void init() throws IOException;
}
