package com.android.apksig.kms;

import com.android.apksig.KeyConfig;
import com.android.apksig.SignerEngine;
import java.security.spec.AlgorithmParameterSpec;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public interface KmsSignerEngineProvider {
    SignerEngine getInstance(KeyConfig.Kms kms, String str, AlgorithmParameterSpec algorithmParameterSpec);

    String getKmsType();
}
