package com.shadow.okhttp3;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;
import java.io.IOException;

/* loaded from: /workspace/unpacked/classes2.dex */
public enum Protocol {
    HTTP_1_0("http/1.0"),
    HTTP_1_1("http/1.1"),
    SPDY_3("spdy/3.1"),
    HTTP_2("h2"),
    H2_PRIOR_KNOWLEDGE("h2_prior_knowledge"),
    QUIC("quic");

    public static final Companion Companion = new Companion(null);
    private final String protocol;

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        public final Protocol get(String str) throws IOException {
            CloseableKt.checkNotNullParameter(str, "protocol");
            Protocol protocol = Protocol.HTTP_1_0;
            if (!str.equals(protocol.protocol)) {
                protocol = Protocol.HTTP_1_1;
                if (!str.equals(protocol.protocol)) {
                    protocol = Protocol.H2_PRIOR_KNOWLEDGE;
                    if (!str.equals(protocol.protocol)) {
                        protocol = Protocol.HTTP_2;
                        if (!str.equals(protocol.protocol)) {
                            protocol = Protocol.SPDY_3;
                            if (!str.equals(protocol.protocol)) {
                                protocol = Protocol.QUIC;
                                if (!str.equals(protocol.protocol)) {
                                    throw new IOException("Unexpected protocol: ".concat(str));
                                }
                            }
                        }
                    }
                }
            }
            return protocol;
        }

        private Companion() {
        }
    }

    Protocol(String str) {
        this.protocol = str;
    }

    public static final Protocol get(String str) throws IOException {
        return Companion.get(str);
    }

    @Override // java.lang.Enum
    public String toString() {
        return this.protocol;
    }
}
