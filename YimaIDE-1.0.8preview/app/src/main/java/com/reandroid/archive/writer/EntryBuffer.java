package com.reandroid.archive.writer;

import com.reandroid.archive.io.ZipFileInput;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class EntryBuffer {
    private final long length;
    private final long offset;
    private final ZipFileInput zipFileInput;

    public EntryBuffer(ZipFileInput zipFileInput, long j, long j2) {
        this.zipFileInput = zipFileInput;
        this.offset = j;
        this.length = j2;
    }

    public long getLength() {
        return this.length;
    }

    public long getOffset() {
        return this.offset;
    }

    public ZipFileInput getZipFileInput() {
        return this.zipFileInput;
    }
}
