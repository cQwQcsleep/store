package org.bouncycastle.crypto.modes;

import org.bouncycastle.crypto.BlockCipher;
import org.bouncycastle.crypto.MultiBlockCipher;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface CBCModeCipher extends MultiBlockCipher {
    BlockCipher getUnderlyingCipher();
}
