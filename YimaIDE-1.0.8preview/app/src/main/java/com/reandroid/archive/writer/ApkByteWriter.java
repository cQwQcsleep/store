package com.reandroid.archive.writer;

import com.reandroid.archive.InputSource;
import com.reandroid.archive.io.ZipByteOutput;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ApkByteWriter extends ApkWriter<ZipByteOutput, ByteOutputSource> {
    public ApkByteWriter(InputSource[] inputSourceArr) {
        this(new ZipByteOutput(), inputSourceArr);
    }

    public byte[] toByteArray() {
        return getZipOutput().toByteArray();
    }

    @Override // com.reandroid.archive.writer.ApkWriter
    public ByteOutputSource toOutputSource(InputSource inputSource) {
        return new ByteOutputSource(inputSource);
    }

    @Override // com.reandroid.archive.writer.ApkWriter
    public void writeApk(ByteOutputSource byteOutputSource, ZipAligner zipAligner) throws IOException {
        byteOutputSource.writeApk(getZipOutput(), zipAligner);
    }

    @Override // com.reandroid.archive.writer.ApkWriter
    public ByteOutputSource[] createOutArray(int i) {
        return new ByteOutputSource[i];
    }

    @Override // com.reandroid.archive.writer.ApkWriter
    public void prepareOutputs(ByteOutputSource[] byteOutputSourceArr) throws IOException {
    }

    public ApkByteWriter(ZipByteOutput zipByteOutput, InputSource[] inputSourceArr) {
        super(zipByteOutput, inputSourceArr);
    }
}
