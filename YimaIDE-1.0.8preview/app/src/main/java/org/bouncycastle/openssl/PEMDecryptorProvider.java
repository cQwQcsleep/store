package org.bouncycastle.openssl;

import org.bouncycastle.operator.OperatorCreationException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface PEMDecryptorProvider {
    PEMDecryptor get(String str) throws OperatorCreationException;
}
