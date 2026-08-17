package com.reandroid.archive.io;

import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class ZipInput implements ReadOnlyStream {
    public abstract byte[] getFooter(int i) throws IOException;
}
