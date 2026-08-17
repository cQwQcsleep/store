package com.android.apksig;

import java.security.PrivateKey;
import java.util.function.Function;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class KeyConfig {

    public static class Jca extends KeyConfig {
        public final PrivateKey privateKey;

        public Jca(PrivateKey privateKey) {
            super();
            this.privateKey = privateKey;
        }

        @Override // com.android.apksig.KeyConfig
        public <T> T match(Function<Jca, T> function, Function<Kms, T> function2) {
            return function.apply(this);
        }
    }

    public static class Kms extends KeyConfig {
        public final String keyAlias;
        public final String kmsType;

        public Kms(String str, String str2) {
            super();
            this.kmsType = str;
            this.keyAlias = str2;
        }

        @Override // com.android.apksig.KeyConfig
        public <T> T match(Function<Jca, T> function, Function<Kms, T> function2) {
            return function2.apply(this);
        }
    }

    public abstract <T> T match(Function<Jca, T> function, Function<Kms, T> function2);

    private KeyConfig() {
    }
}
