package com.reandroid.archive.writer;

import com.reandroid.archive.io.ZipFileOutput;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class BufferFileOutput extends ZipFileOutput {
    public BufferFileOutput(File file) throws IOException {
        super(file);
    }
}
