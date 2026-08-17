package com.reandroid.archive.io;

import com.reandroid.archive.Archive;
import com.reandroid.archive.ArchiveEntry;
import com.reandroid.utils.io.FileUtil;
import java.io.File;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ArchiveFileEntrySource extends ArchiveEntrySource<ZipFileInput> {
    public ArchiveFileEntrySource(ZipFileInput zipFileInput, ArchiveEntry archiveEntry) {
        super(zipFileInput, archiveEntry);
        setSort(archiveEntry.getIndex());
    }

    @Override // com.reandroid.archive.InputSource
    public byte[] getBytes(int i) throws IOException {
        FileChannel fileChannel = getFileChannel();
        if (getMethod() != Archive.STORED || fileChannel == null) {
            return super.getBytes(i);
        }
        byte[] bArr = new byte[i];
        fileChannel.read(ByteBuffer.wrap(bArr));
        return bArr;
    }

    public FileChannel getFileChannel() throws IOException {
        FileChannel fileChannel = getZipSource().getFileChannel();
        fileChannel.position(getArchiveEntry().getFileOffset());
        return fileChannel;
    }

    @Override // com.reandroid.archive.InputSource
    public void write(File file) throws IOException {
        FileChannel fileChannel = getFileChannel();
        if (getMethod() != Archive.STORED || fileChannel == null) {
            super.write(file);
            return;
        }
        FileChannel fileChannelOpenWriteChannel = FileUtil.openWriteChannel(file);
        long length = getLength();
        long j = 0;
        while (length > 0) {
            long jTransferFrom = fileChannelOpenWriteChannel.transferFrom(fileChannel, j, length);
            j += jTransferFrom;
            length -= jTransferFrom;
        }
        fileChannelOpenWriteChannel.close();
    }
}
