package com.reandroid.archive.writer;

import com.reandroid.archive.InputSource;
import com.reandroid.archive.io.ZipByteOutput;
import com.reandroid.archive.io.ZipStreamOutput;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
class StreamOutputSource extends OutputSource {
    public StreamOutputSource(InputSource inputSource) {
        super(inputSource);
    }

    public void writeApk(ZipStreamOutput zipStreamOutput, ZipAligner zipAligner) throws IOException {
        ZipByteOutput zipByteOutput = new ZipByteOutput();
        writeBuffer(zipByteOutput);
        zipByteOutput.close();
        writeLFH(zipStreamOutput, zipAligner);
        getLocalFileHeader().setFileOffset(zipStreamOutput.position());
        zipStreamOutput.write(zipByteOutput.toByteArray());
        writeDD(zipStreamOutput);
    }
}
