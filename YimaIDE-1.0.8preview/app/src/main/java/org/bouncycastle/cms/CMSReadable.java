package org.bouncycastle.cms;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
interface CMSReadable {
    InputStream getInputStream() throws CMSException, IOException;
}
