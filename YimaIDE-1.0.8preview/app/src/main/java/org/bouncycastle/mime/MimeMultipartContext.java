package org.bouncycastle.mime;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface MimeMultipartContext extends MimeContext {
    MimeContext createContext(int i) throws IOException;
}
