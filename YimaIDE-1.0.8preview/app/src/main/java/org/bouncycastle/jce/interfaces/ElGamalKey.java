package org.bouncycastle.jce.interfaces;

import javax.crypto.interfaces.DHKey;
import org.bouncycastle.jce.spec.ElGamalParameterSpec;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface ElGamalKey extends DHKey {
    ElGamalParameterSpec getParameters();
}
