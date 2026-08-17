package com.reandroid.archive.writer;

import com.reandroid.archive.InputSource;
import com.reandroid.archive.io.ZipFileOutput;
import com.reandroid.archive.io.ZipOutput;
import java.io.IOException;
import java.nio.channels.FileChannel;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
class FileOutputSource extends OutputSource {
    private EntryBuffer entryBuffer;

    public FileOutputSource(InputSource inputSource) {
        super(inputSource);
    }

    private EntryBuffer writeBuffer(BufferFileInput bufferFileInput, ZipOutput zipOutput) throws IOException {
        long jPosition = zipOutput.position();
        writeBuffer(zipOutput);
        return new EntryBuffer(bufferFileInput, jPosition, zipOutput.position() - jPosition);
    }

    private void writeData(FileChannel fileChannel, long j, ZipFileOutput zipFileOutput) throws IOException {
        getLocalFileHeader().setFileOffset(zipFileOutput.position());
        zipFileOutput.write(fileChannel, j);
    }

    public void makeBuffer(BufferFileInput bufferFileInput, BufferFileOutput bufferFileOutput) throws IOException {
        if (this.entryBuffer != null) {
            return;
        }
        EntryBuffer entryBufferMakeFromEntry = makeFromEntry();
        if (entryBufferMakeFromEntry != null) {
            this.entryBuffer = entryBufferMakeFromEntry;
        } else {
            this.entryBuffer = writeBuffer(bufferFileInput, bufferFileOutput);
        }
    }

    public EntryBuffer makeFromEntry() {
        return null;
    }

    public void writeApk(ZipFileOutput zipFileOutput, ZipAligner zipAligner) throws IOException {
        logLargeFileWrite();
        EntryBuffer entryBuffer = this.entryBuffer;
        FileChannel fileChannel = entryBuffer.getZipFileInput().getFileChannel();
        fileChannel.position(entryBuffer.getOffset());
        writeLFH(zipFileOutput, zipAligner);
        writeData(fileChannel, entryBuffer.getLength(), zipFileOutput);
        writeDD(zipFileOutput);
    }
}
