package org.bouncycastle.operator;

import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface AADProcessor {
    OutputStream getAADStream();

    byte[] getMAC();
}
