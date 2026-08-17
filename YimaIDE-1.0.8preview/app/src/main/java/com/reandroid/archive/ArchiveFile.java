package com.reandroid.archive;

import com.reandroid.archive.io.ArchiveFileEntrySource;
import com.reandroid.archive.io.ZipFileInput;
import com.reandroid.utils.io.FileUtil;
import java.io.File;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ArchiveFile extends Archive<ZipFileInput> {
    public ArchiveFile(File file) throws IOException {
        this(new ZipFileInput(file));
    }

    @Override // com.reandroid.archive.Archive
    public InputSource createInputSource(ArchiveEntry archiveEntry) {
        return new ArchiveFileEntrySource(getZipInput(), archiveEntry);
    }

    @Override // com.reandroid.archive.Archive
    public void extractStored(File file, ArchiveEntry archiveEntry) throws IOException {
        FileChannel fileChannelOpenWriteChannel = FileUtil.openWriteChannel(file);
        FileChannel fileChannel = getZipInput().getFileChannel();
        fileChannel.position(archiveEntry.getFileOffset());
        long dataSize = archiveEntry.getDataSize();
        long j = 0;
        while (dataSize > 0) {
            long jTransferFrom = fileChannelOpenWriteChannel.transferFrom(fileChannel, j, dataSize);
            j += jTransferFrom;
            dataSize -= jTransferFrom;
        }
        fileChannelOpenWriteChannel.close();
    }

    public ArchiveFile(ZipFileInput zipFileInput) throws IOException {
        super(zipFileInput);
    }
}
