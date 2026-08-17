package org.bouncycastle.mime;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface MimeContext {
    InputStream applyContext(Headers headers, InputStream inputStream) throws IOException;
}
