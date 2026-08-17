package com.reandroid.archive;

import com.reandroid.common.FileChannelInputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class FileInputSource extends InputSource {
    private final File file;

    public FileInputSource(File file, String str) {
        super(str);
        this.file = file;
    }

    public void close(InputStream inputStream) throws IOException {
        inputStream.close();
    }

    public byte[] getBytes(int i) throws IOException {
        return FileChannelInputStream.read(getFile(), i);
    }

    public File getFile() {
        return this.file;
    }

    public long getLength() {
        return getFile().length();
    }

    /* JADX INFO: renamed from: openStream, reason: merged with bridge method [inline-methods] */
    public FileChannelInputStream m0openStream() throws IOException {
        return new FileChannelInputStream(this.file);
    }
}
