package androidx.core.hardware.fingerprint;

import android.content.Context;
import android.os.CancellationSignal;
import android.os.Handler;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
@Deprecated
public class FingerprintManagerCompat {

    public static abstract class AuthenticationCallback {
        public void onAuthenticationError(int i, CharSequence charSequence) {
        }

        public void onAuthenticationFailed() {
        }

        public void onAuthenticationHelp(int i, CharSequence charSequence) {
        }

        public void onAuthenticationSucceeded(AuthenticationResult authenticationResult) {
        }
    }

    public static final class AuthenticationResult {
        private final CryptoObject mCryptoObject;

        public AuthenticationResult(CryptoObject cryptoObject) {
            this.mCryptoObject = cryptoObject;
        }

        public CryptoObject getCryptoObject() {
            return this.mCryptoObject;
        }
    }

    private FingerprintManagerCompat() {
    }

    public static FingerprintManagerCompat from(Context context) {
        return new FingerprintManagerCompat();
    }

    public void authenticate(CryptoObject cryptoObject, int i, CancellationSignal cancellationSignal, AuthenticationCallback authenticationCallback, Handler handler) {
    }

    @Deprecated
    public void authenticate(CryptoObject cryptoObject, int i, androidx.core.os.CancellationSignal cancellationSignal, AuthenticationCallback authenticationCallback, Handler handler) {
    }

    public boolean hasEnrolledFingerprints() {
        return false;
    }

    public boolean isHardwareDetected() {
        return false;
    }
}
