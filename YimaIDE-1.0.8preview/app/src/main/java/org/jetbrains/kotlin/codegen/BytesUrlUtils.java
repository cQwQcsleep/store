package org.jetbrains.kotlin.codegen;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;
import java.util.Base64;

/* JADX INFO: loaded from: /workspace/dex_all/classes11.dex */
public class BytesUrlUtils {
    private static final URLStreamHandler BYTES_URL_HANDLER = new URLStreamHandler() { // from class: org.jetbrains.kotlin.codegen.BytesUrlUtils.1
        @Override // java.net.URLStreamHandler
        public URLConnection openConnection(URL url) {
            return new URLConnection(url) { // from class: org.jetbrains.kotlin.codegen.BytesUrlUtils.1.1
                @Override // java.net.URLConnection
                public void connect() {
                }

                @Override // java.net.URLConnection
                public InputStream getInputStream() {
                    return new ByteArrayInputStream(Base64.getDecoder().decode(((URLConnection) this).url.getPath()));
                }
            };
        }
    };

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "bytes", "org/jetbrains/kotlin/codegen/BytesUrlUtils", "createBytesUrl"));
    }

    public static URL createBytesUrl(byte[] bArr) throws MalformedURLException {
        if (bArr == null) {
            $$$reportNull$$$0(0);
        }
        return new URL((URL) null, "bytes:" + Base64.getEncoder().encodeToString(bArr), BYTES_URL_HANDLER);
    }
}
