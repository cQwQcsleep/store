package com.reandroid.archive.writer;

import com.reandroid.archive.InputSource;
import com.reandroid.archive.RenamedInputSource;
import com.reandroid.archive.io.ArchiveFileEntrySource;
import com.reandroid.archive.io.ZipFileOutput;
import com.reandroid.arsc.chunk.TableBlock;
import java.io.File;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ApkFileWriter extends ApkWriter<ZipFileOutput, FileOutputSource> {
    private BufferFileInput buffer;

    public ApkFileWriter(File file, InputSource[] inputSourceArr) throws IOException {
        super(new ZipFileOutput(file), inputSourceArr);
    }

    private File getBufferFile() {
        File file = getZipOutput().getFile();
        File parentFile = file.getParentFile();
        String str = "tmp" + file.getAbsolutePath().hashCode();
        File file2 = parentFile != null ? new File(parentFile, str) : new File(str);
        file2.deleteOnExit();
        return file2;
    }

    private BufferFileInput writeBuffer(FileOutputSource[] fileOutputSourceArr) throws IOException {
        File bufferFile = getBufferFile();
        BufferFileOutput bufferFileOutput = new BufferFileOutput(bufferFile);
        BufferFileInput bufferFileInput = new BufferFileInput(bufferFile);
        FileOutputSource fileOutputSource = null;
        for (FileOutputSource fileOutputSource2 : fileOutputSourceArr) {
            InputSource inputSource = fileOutputSource2.getInputSource();
            if (fileOutputSource == null && TableBlock.FILE_NAME.equals(inputSource.getAlias())) {
                fileOutputSource = fileOutputSource2;
            } else {
                onCompressFileProgress(inputSource.getAlias(), inputSource.getMethod(), bufferFileOutput.position());
                fileOutputSource2.makeBuffer(bufferFileInput, bufferFileOutput);
            }
        }
        if (fileOutputSource != null) {
            fileOutputSource.makeBuffer(bufferFileInput, bufferFileOutput);
        }
        bufferFileOutput.close();
        return bufferFileInput;
    }

    @Override // com.reandroid.archive.writer.ApkWriter
    public void closeBuffer() throws IOException {
        this.buffer.close();
    }

    @Override // com.reandroid.archive.writer.ApkWriter
    public void prepareOutputs(FileOutputSource[] fileOutputSourceArr) throws IOException {
        logMessage("Buffering compress changed files ...");
        BufferFileInput bufferFileInputWriteBuffer = writeBuffer(fileOutputSourceArr);
        bufferFileInputWriteBuffer.unlock();
        this.buffer = bufferFileInputWriteBuffer;
    }

    @Override // com.reandroid.archive.writer.ApkWriter
    public FileOutputSource toOutputSource(InputSource inputSource) {
        if (inputSource instanceof ArchiveFileEntrySource) {
            return new ArchiveOutputSource(inputSource);
        }
        if (inputSource instanceof RenamedInputSource) {
            RenamedInputSource renamedInputSource = (RenamedInputSource) inputSource;
            if (renamedInputSource.getParentInputSource(ArchiveFileEntrySource.class) != null) {
                return new RenamedArchiveSource(renamedInputSource);
            }
        }
        return new FileOutputSource(inputSource);
    }

    @Override // com.reandroid.archive.writer.ApkWriter
    public void writeApk(FileOutputSource fileOutputSource, ZipAligner zipAligner) throws IOException {
        fileOutputSource.writeApk(getZipOutput(), zipAligner);
    }

    @Override // com.reandroid.archive.writer.ApkWriter
    public FileOutputSource[] createOutArray(int i) {
        return new FileOutputSource[i];
    }
}
