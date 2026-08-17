package com.android.tools.r8;

import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes5.dex */
public interface AndroidResourceInput extends Resource {

    public enum Kind {
        MANIFEST,
        RESOURCE_TABLE,
        XML_FILE,
        RES_FOLDER_FILE,
        UNKNOWN;

        Kind() {
        }
    }

    InputStream getByteStream() throws ResourceException;

    Kind getKind();

    ResourcePath getPath();
}
