package com.android.apksig;

import com.android.apksig.KeyConfig;
import com.android.apksig.SignerEngineFactory;
import com.android.apksig.kms.KmsException;
import com.android.apksig.kms.KmsSignerEngineProvider;
import java.security.spec.AlgorithmParameterSpec;
import java.util.Objects;
import java.util.ServiceLoader;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class SignerEngineFactory {
    private SignerEngineFactory() {
    }

    public static /* synthetic */ SignerEngine b(String str, AlgorithmParameterSpec algorithmParameterSpec, KeyConfig.Jca jca) {
        return new JcaSignerEngine(jca.privateKey, str, algorithmParameterSpec);
    }

    public static SignerEngine getImplementation(KeyConfig keyConfig, final String str, final AlgorithmParameterSpec algorithmParameterSpec) {
        return (SignerEngine) keyConfig.match(new Function() { // from class: obd
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SignerEngineFactory.b(str, algorithmParameterSpec, (KeyConfig.Jca) obj);
            }
        }, new Function() { // from class: pbd
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return SignerEngineFactory.getKmsImplementation((KeyConfig.Kms) obj, str, algorithmParameterSpec);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static SignerEngine getKmsImplementation(KeyConfig.Kms kms, String str, AlgorithmParameterSpec algorithmParameterSpec) {
        for (KmsSignerEngineProvider kmsSignerEngineProvider : ServiceLoader.load(KmsSignerEngineProvider.class)) {
            if (Objects.equals(kmsSignerEngineProvider.getKmsType(), kms.kmsType)) {
                return kmsSignerEngineProvider.getInstance(kms, str, algorithmParameterSpec);
            }
        }
        throw new KmsException(kms.kmsType, "No SignerEngine implementation found on the classpath");
    }
}
