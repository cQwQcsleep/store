package com.shadow.okhttp3;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.okhttp3.internal.authenticator.JavaNetAuthenticator;
import java.io.IOException;

/* loaded from: /workspace/unpacked/classes2.dex */
public interface Authenticator {
    public static final Companion Companion = Companion.$$INSTANCE;
    public static final Authenticator NONE = new Companion.AuthenticatorNone();
    public static final Authenticator JAVA_NET_AUTHENTICATOR = new JavaNetAuthenticator(null, 1, null);

    public static final class Companion {
        static final /* synthetic */ Companion $$INSTANCE = new Companion();

        public static final class AuthenticatorNone implements Authenticator {
            @Override // com.shadow.okhttp3.Authenticator
            public Request authenticate(Route route, Response response) {
                CloseableKt.checkNotNullParameter(response, "response");
                return null;
            }
        }

        private Companion() {
        }
    }

    Request authenticate(Route route, Response response) throws IOException;
}
