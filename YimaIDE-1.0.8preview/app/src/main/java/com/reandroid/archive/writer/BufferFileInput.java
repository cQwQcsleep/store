package com.reandroid.archive.writer;

import com.reandroid.archive.io.ZipFileInput;
import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class BufferFileInput extends ZipFileInput {
    private boolean unlocked;

    public BufferFileInput(File file) {
        super(file);
    }

    @Override // com.reandroid.archive.io.ZipFileInput, com.reandroid.archive.io.RandomStream, java.nio.channels.Channel, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        super.close();
        if (this.unlocked) {
            File file = super.getFile();
            if (file.isFile()) {
                file.delete();
            }
            this.unlocked = false;
        }
    }

    @Override // com.reandroid.archive.io.ZipFileInput
    public FileChannel getFileChannel() throws IOException {
        if (this.unlocked) {
            return super.getFileChannel();
        }
        a16.a("File locked!");
        return null;
    }

    public void unlock() {
        this.unlocked = true;
    }
}
