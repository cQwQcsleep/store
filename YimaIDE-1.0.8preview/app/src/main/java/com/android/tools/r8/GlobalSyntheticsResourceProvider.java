package com.android.tools.r8;

import com.android.tools.r8.origin.Origin;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface GlobalSyntheticsResourceProvider {
    InputStream getByteStream() throws ResourceException;

    Origin getOrigin();
}
