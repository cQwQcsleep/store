package org.bouncycastle.pqc.jcajce.interfaces;

import java.security.Key;
import org.bouncycastle.pqc.jcajce.spec.NTRUParameterSpec;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface NTRUKey extends Key {
    NTRUParameterSpec getParameterSpec();
}
