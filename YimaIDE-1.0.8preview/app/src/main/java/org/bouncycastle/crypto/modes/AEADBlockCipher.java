package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface AEADBlockCipher extends AEADCipher {
    BlockCipher getUnderlyingCipher();
}
