package com.reandroid.archive.writer;

import com.reandroid.archive.InputSource;
import com.reandroid.archive.io.ZipStreamOutput;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ApkStreamWriter extends ApkWriter<ZipStreamOutput, StreamOutputSource> {
    public ApkStreamWriter(OutputStream outputStream, InputSource[] inputSourceArr) {
        this(new ZipStreamOutput(outputStream), inputSourceArr);
    }

    @Override // com.reandroid.archive.writer.ApkWriter
    public StreamOutputSource toOutputSource(InputSource inputSource) {
        return new StreamOutputSource(inputSource);
    }

    @Override // com.reandroid.archive.writer.ApkWriter
    public void writeApk(StreamOutputSource streamOutputSource, ZipAligner zipAligner) throws IOException {
        streamOutputSource.writeApk(getZipOutput(), zipAligner);
    }

    @Override // com.reandroid.archive.writer.ApkWriter
    public StreamOutputSource[] createOutArray(int i) {
        return new StreamOutputSource[i];
    }

    @Override // com.reandroid.archive.writer.ApkWriter
    public void prepareOutputs(StreamOutputSource[] streamOutputSourceArr) throws IOException {
    }

    public ApkStreamWriter(ZipStreamOutput zipStreamOutput, InputSource[] inputSourceArr) {
        super(zipStreamOutput, inputSourceArr);
    }
}
