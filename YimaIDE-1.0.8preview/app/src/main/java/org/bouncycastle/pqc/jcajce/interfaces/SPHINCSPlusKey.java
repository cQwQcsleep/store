package org.bouncycastle.pqc.jcajce.interfaces;

import java.security.Key;
import org.bouncycastle.pqc.jcajce.spec.SPHINCSPlusParameterSpec;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public interface SPHINCSPlusKey extends Key {
    SPHINCSPlusParameterSpec getParameterSpec();
}
