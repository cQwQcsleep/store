package androidx.security.crypto;

import android.content.Context;
import android.security.keystore.KeyGenParameterSpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public final class MasterKey {
    public static final int DEFAULT_AES_GCM_MASTER_KEY_SIZE = 256;
    private static final int DEFAULT_AUTHENTICATION_VALIDITY_DURATION_SECONDS = 300;
    public static final String DEFAULT_MASTER_KEY_ALIAS = "_androidx_security_master_key_";
    static final String KEYSTORE_PATH_URI = "android-keystore://";
    private final String mKeyAlias;
    private final KeyGenParameterSpec mKeyGenParameterSpec;

    /* JADX INFO: renamed from: androidx.security.crypto.MasterKey$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$androidx$security$crypto$MasterKey$KeyScheme;

        static {
            int[] iArr = new int[KeyScheme.values().length];
            $SwitchMap$androidx$security$crypto$MasterKey$KeyScheme = iArr;
            try {
                iArr[KeyScheme.AES256_GCM.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
        }
    }

    public static class Api23Impl {
        private Api23Impl() {
        }

        public static int getUserAuthenticationValidityDurationSeconds(KeyGenParameterSpec keyGenParameterSpec) {
            return keyGenParameterSpec.getUserAuthenticationValidityDurationSeconds();
        }

        public static boolean isUserAuthenticationRequired(KeyGenParameterSpec keyGenParameterSpec) {
            return keyGenParameterSpec.isUserAuthenticationRequired();
        }
    }

    public static class Api28Impl {
        private Api28Impl() {
        }

        public static boolean isStrongBoxBacked(KeyGenParameterSpec keyGenParameterSpec) {
            return keyGenParameterSpec.isStrongBoxBacked();
        }
    }

    public enum KeyScheme {
        AES256_GCM
    }

    public MasterKey(String str, Object obj) {
        this.mKeyAlias = str;
        this.mKeyGenParameterSpec = (KeyGenParameterSpec) obj;
    }

    public static int getDefaultAuthenticationValidityDurationSeconds() {
        return 300;
    }

    public String getKeyAlias() {
        return this.mKeyAlias;
    }

    public int getUserAuthenticationValidityDurationSeconds() {
        KeyGenParameterSpec keyGenParameterSpec = this.mKeyGenParameterSpec;
        if (keyGenParameterSpec == null) {
            return 0;
        }
        return Api23Impl.getUserAuthenticationValidityDurationSeconds(keyGenParameterSpec);
    }

    public boolean isKeyStoreBacked() {
        try {
            KeyStore keyStore = KeyStore.getInstance("AndroidKeyStore");
            keyStore.load(null);
            return keyStore.containsAlias(this.mKeyAlias);
        } catch (IOException | KeyStoreException | NoSuchAlgorithmException | CertificateException unused) {
            return false;
        }
    }

    public boolean isStrongBoxBacked() {
        KeyGenParameterSpec keyGenParameterSpec = this.mKeyGenParameterSpec;
        if (keyGenParameterSpec == null) {
            return false;
        }
        return Api28Impl.isStrongBoxBacked(keyGenParameterSpec);
    }

    public boolean isUserAuthenticationRequired() {
        KeyGenParameterSpec keyGenParameterSpec = this.mKeyGenParameterSpec;
        return keyGenParameterSpec != null && Api23Impl.isUserAuthenticationRequired(keyGenParameterSpec);
    }

    public String toString() {
        return "MasterKey{keyAlias=" + this.mKeyAlias + ", isKeyStoreBacked=" + isKeyStoreBacked() + "}";
    }

    public static final class Builder {
        boolean mAuthenticationRequired;
        final Context mContext;
        final String mKeyAlias;
        KeyGenParameterSpec mKeyGenParameterSpec;
        KeyScheme mKeyScheme;
        boolean mRequestStrongBoxBacked;
        int mUserAuthenticationValidityDurationSeconds;

        public static class Api23Impl {

            public static class Api28Impl {
                private Api28Impl() {
                }

                public static void setIsStrongBoxBacked(KeyGenParameterSpec.Builder builder) {
                    builder.setIsStrongBoxBacked(true);
                }
            }

            public static class Api30Impl {
                private Api30Impl() {
                }

                public static void setUserAuthenticationParameters(KeyGenParameterSpec.Builder builder, int i, int i2) {
                    builder.setUserAuthenticationParameters(i, i2);
                }
            }

            private Api23Impl() {
            }

            public static MasterKey build(Builder builder) throws GeneralSecurityException, IOException {
                KeyScheme keyScheme = builder.mKeyScheme;
                if (keyScheme == null && builder.mKeyGenParameterSpec == null) {
                    w01.a("build() called before setKeyGenParameterSpec or setKeyScheme.");
                    return null;
                }
                if (keyScheme == KeyScheme.AES256_GCM) {
                    KeyGenParameterSpec.Builder keySize = new KeyGenParameterSpec.Builder(builder.mKeyAlias, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setKeySize(256);
                    if (builder.mAuthenticationRequired) {
                        keySize.setUserAuthenticationRequired(true);
                        Api30Impl.setUserAuthenticationParameters(keySize, builder.mUserAuthenticationValidityDurationSeconds, 3);
                    }
                    if (builder.mRequestStrongBoxBacked && builder.mContext.getPackageManager().hasSystemFeature("android.hardware.strongbox_keystore")) {
                        Api28Impl.setIsStrongBoxBacked(keySize);
                    }
                    builder.mKeyGenParameterSpec = keySize.build();
                }
                KeyGenParameterSpec keyGenParameterSpec = builder.mKeyGenParameterSpec;
                if (keyGenParameterSpec != null) {
                    return new MasterKey(MasterKeys.getOrCreate(keyGenParameterSpec), builder.mKeyGenParameterSpec);
                }
                x0e.a("KeyGenParameterSpec was null after build() check");
                return null;
            }

            public static String getKeystoreAlias(KeyGenParameterSpec keyGenParameterSpec) {
                return keyGenParameterSpec.getKeystoreAlias();
            }
        }

        public Builder(Context context, String str) {
            this.mContext = context.getApplicationContext();
            this.mKeyAlias = str;
        }

        public MasterKey build() throws GeneralSecurityException, IOException {
            return Api23Impl.build(this);
        }

        public Builder setKeyGenParameterSpec(KeyGenParameterSpec keyGenParameterSpec) {
            if (this.mKeyScheme != null) {
                w01.a("KeyGenParamSpec set after setting a KeyScheme");
                return null;
            }
            if (this.mKeyAlias.equals(Api23Impl.getKeystoreAlias(keyGenParameterSpec))) {
                this.mKeyGenParameterSpec = keyGenParameterSpec;
                return this;
            }
            i0f.a("KeyGenParamSpec's key alias does not match provided alias (", this.mKeyAlias, " vs ", Api23Impl.getKeystoreAlias(keyGenParameterSpec));
            return null;
        }

        public Builder setKeyScheme(KeyScheme keyScheme) {
            if (AnonymousClass1.$SwitchMap$androidx$security$crypto$MasterKey$KeyScheme[keyScheme.ordinal()] != 1) {
                aca.a("Unsupported scheme: ", keyScheme);
                return null;
            }
            if (this.mKeyGenParameterSpec == null) {
                this.mKeyScheme = keyScheme;
                return this;
            }
            w01.a("KeyScheme set after setting a KeyGenParamSpec");
            return null;
        }

        public Builder setRequestStrongBoxBacked(boolean z) {
            this.mRequestStrongBoxBacked = z;
            return this;
        }

        public Builder setUserAuthenticationRequired(boolean z) {
            return setUserAuthenticationRequired(z, MasterKey.getDefaultAuthenticationValidityDurationSeconds());
        }

        public Builder setUserAuthenticationRequired(boolean z, int i) {
            this.mAuthenticationRequired = z;
            this.mUserAuthenticationValidityDurationSeconds = i;
            return this;
        }

        public Builder(Context context) {
            this(context, MasterKey.DEFAULT_MASTER_KEY_ALIAS);
        }
    }
}
