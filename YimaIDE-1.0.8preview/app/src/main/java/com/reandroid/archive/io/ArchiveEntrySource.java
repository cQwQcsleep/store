package com.reandroid.archive.io;

import com.reandroid.archive.Archive;
import com.reandroid.archive.ArchiveEntry;
import com.reandroid.archive.InputSource;
import com.reandroid.archive.io.ZipInput;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.Inflater;
import java.util.zip.InflaterInputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ArchiveEntrySource<T extends ZipInput> extends InputSource {
    private final ArchiveEntry archiveEntry;
    private final T zipInput;

    public ArchiveEntrySource(T t, ArchiveEntry archiveEntry) {
        super(archiveEntry.getSanitizedName());
        this.zipInput = t;
        this.archiveEntry = archiveEntry;
        setMethod(archiveEntry.getMethod());
    }

    private boolean isCompressed() {
        ArchiveEntry archiveEntry = getArchiveEntry();
        int method = archiveEntry.getMethod();
        if (method == Archive.STORED) {
            return false;
        }
        int i = Archive.DEFLATED;
        if (method == i) {
            return true;
        }
        try {
            openInflaterInputStream().read(new byte[1024], 0, 1024);
            archiveEntry.setMethod(i);
            setMethod(i);
            return true;
        } catch (Throwable unused) {
            int i2 = Archive.STORED;
            archiveEntry.setMethod(i2);
            setMethod(i2);
            long size = archiveEntry.getSize();
            long compressedSize = archiveEntry.getCompressedSize();
            if (size > compressedSize) {
                archiveEntry.setCompressedSize(size);
            } else if (compressedSize > size) {
                archiveEntry.setSize(compressedSize);
            }
            return false;
        }
    }

    private InputStream openInflaterInputStream() throws IOException {
        ArchiveEntry archiveEntry = getArchiveEntry();
        return new InflaterInputStream(getZipSource().getInputStream(archiveEntry.getFileOffset(), archiveEntry.getDataSize()), new Inflater(true), 512);
    }

    public ArchiveEntry getArchiveEntry() {
        return this.archiveEntry;
    }

    @Override // com.reandroid.archive.InputSource
    public long getCrc() throws IOException {
        return getArchiveEntry().getCrc();
    }

    @Override // com.reandroid.archive.InputSource
    public long getLength() throws IOException {
        return getArchiveEntry().getDataSize();
    }

    public T getZipSource() {
        return this.zipInput;
    }

    @Override // com.reandroid.archive.InputSource
    public InputStream openStream() throws IOException {
        if (isCompressed()) {
            return openInflaterInputStream();
        }
        ArchiveEntry archiveEntry = getArchiveEntry();
        return getZipSource().getInputStream(archiveEntry.getFileOffset(), archiveEntry.getDataSize());
    }
}
