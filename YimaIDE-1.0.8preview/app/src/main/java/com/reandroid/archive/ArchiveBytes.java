package com.reandroid.archive;

import com.reandroid.archive.io.ArchiveByteEntrySource;
import com.reandroid.archive.io.ZipByteInput;
import com.reandroid.utils.io.IOUtil;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ArchiveBytes extends Archive<ZipByteInput> {
    public ArchiveBytes(byte[] bArr) throws IOException {
        super(new ZipByteInput(bArr));
    }

    @Override // com.reandroid.archive.Archive
    public InputSource createInputSource(ArchiveEntry archiveEntry) {
        return new ArchiveByteEntrySource(getZipInput(), archiveEntry);
    }

    @Override // com.reandroid.archive.Archive
    public void extractStored(File file, ArchiveEntry archiveEntry) throws IOException {
        IOUtil.writeAll(getZipInput().getInputStream(archiveEntry.getFileOffset(), archiveEntry.getDataSize()), file);
    }

    public ArchiveBytes(ZipByteInput zipByteInput) throws IOException {
        super(zipByteInput);
    }

    public ArchiveBytes(InputStream inputStream) throws IOException {
        this(IOUtil.readFully(inputStream));
    }
}
