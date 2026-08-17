package androidx.security.crypto;

import android.security.keystore.KeyGenParameterSpec;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;
import java.security.ProviderException;
import java.util.Arrays;
import javax.crypto.KeyGenerator;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Deprecated
public final class MasterKeys {
    private static final String ANDROID_KEYSTORE = "AndroidKeyStore";
    static final int KEY_SIZE = 256;
    static final String MASTER_KEY_ALIAS = "_androidx_security_master_key_";
    public static final KeyGenParameterSpec AES256_GCM_SPEC = createAES256GCMKeyGenParameterSpec("_androidx_security_master_key_");
    private static final Object sLock = new Object();

    private MasterKeys() {
    }

    private static KeyGenParameterSpec createAES256GCMKeyGenParameterSpec(String str) {
        return new KeyGenParameterSpec.Builder(str, 3).setBlockModes("GCM").setEncryptionPaddings("NoPadding").setKeySize(256).build();
    }

    private static void generateKey(KeyGenParameterSpec keyGenParameterSpec) throws GeneralSecurityException {
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("AES", ANDROID_KEYSTORE);
            keyGenerator.init(keyGenParameterSpec);
            keyGenerator.generateKey();
        } catch (ProviderException e) {
            throw new GeneralSecurityException(e.getMessage(), e);
        }
    }

    public static String getOrCreate(KeyGenParameterSpec keyGenParameterSpec) throws GeneralSecurityException, IOException {
        validate(keyGenParameterSpec);
        synchronized (sLock) {
            try {
                if (!keyExists(keyGenParameterSpec.getKeystoreAlias())) {
                    generateKey(keyGenParameterSpec);
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        return keyGenParameterSpec.getKeystoreAlias();
    }

    private static boolean keyExists(String str) throws GeneralSecurityException, IOException {
        KeyStore keyStore = KeyStore.getInstance(ANDROID_KEYSTORE);
        keyStore.load(null);
        return keyStore.containsAlias(str);
    }

    public static void validate(KeyGenParameterSpec keyGenParameterSpec) {
        if (keyGenParameterSpec.getKeySize() != 256) {
            prd.a("invalid key size, want 256 bits got ", keyGenParameterSpec.getKeySize(), " bits");
            return;
        }
        if (!Arrays.equals(keyGenParameterSpec.getBlockModes(), new String[]{"GCM"})) {
            z01.a("invalid block mode, want GCM got ", Arrays.toString(keyGenParameterSpec.getBlockModes()));
            return;
        }
        if (keyGenParameterSpec.getPurposes() != 3) {
            jt6.a("invalid purposes mode, want PURPOSE_ENCRYPT | PURPOSE_DECRYPT got ", keyGenParameterSpec.getPurposes());
            return;
        }
        if (!Arrays.equals(keyGenParameterSpec.getEncryptionPaddings(), new String[]{"NoPadding"})) {
            z01.a("invalid padding mode, want NoPadding got ", Arrays.toString(keyGenParameterSpec.getEncryptionPaddings()));
        } else {
            if (!keyGenParameterSpec.isUserAuthenticationRequired() || keyGenParameterSpec.getUserAuthenticationValidityDurationSeconds() >= 1) {
                return;
            }
            w01.a("per-operation authentication is not supported (UserAuthenticationValidityDurationSeconds must be >0)");
        }
    }
}
