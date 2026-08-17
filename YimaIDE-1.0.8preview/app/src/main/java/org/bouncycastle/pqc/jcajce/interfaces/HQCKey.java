package org.bouncycastle.pqc.jcajce.interfaces;

import java.security.Key;
import org.bouncycastle.pqc.jcajce.spec.HQCParameterSpec;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface HQCKey extends Key {
    HQCParameterSpec getParameterSpec();
}
