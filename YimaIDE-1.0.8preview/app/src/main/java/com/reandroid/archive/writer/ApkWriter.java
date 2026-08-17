package com.reandroid.archive.writer;

import com.reandroid.apk.APKLogger;
import com.reandroid.archive.ArchiveInfo;
import com.reandroid.archive.InputSource;
import com.reandroid.archive.WriteProgress;
import com.reandroid.archive.ZipSignature;
import com.reandroid.archive.block.ApkSignatureBlock;
import com.reandroid.archive.block.EndRecord;
import com.reandroid.archive.block.Zip64Locator;
import com.reandroid.archive.block.Zip64Record;
import com.reandroid.archive.block.ZipHeader;
import com.reandroid.archive.io.ZipOutput;
import com.reandroid.archive.writer.OutputSource;
import java.io.Closeable;
import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public abstract class ApkWriter<T extends ZipOutput, OUT extends OutputSource> implements Closeable {
    private APKLogger apkLogger;
    private ApkSignatureBlock apkSignatureBlock;
    private final InputSource[] inputSources;
    private WriteProgress writeProgress;
    private final T zipOutput;
    private final Object mLock = new Object();
    private ZipAligner zipAligner = ZipAligner.apkAligner();
    private final HeaderInterceptorChain interceptorChain = HeaderInterceptorChain.createDefault();

    public ApkWriter(T t, InputSource[] inputSourceArr) {
        this.zipOutput = t;
        this.inputSources = inputSourceArr;
    }

    private void writeApkList(OUT[] outArr) throws IOException {
        int length = outArr.length;
        logMessage("Writing files: " + length);
        APKLogger apkLogger = getApkLogger();
        ZipAligner zipAligner = getZipAligner();
        for (int i = 0; i < length; i++) {
            OUT out = outArr[i];
            out.setAPKLogger(apkLogger);
            writeApk(out, zipAligner);
            if (i % 100 == 0) {
                out.logFileWrite();
            }
        }
    }

    private void writeCEHList(OUT[] outArr) throws IOException {
        EndRecord endRecord = new EndRecord();
        endRecord.setSignature(ZipSignature.END_RECORD);
        long jPosition = position();
        endRecord.setOffsetOfCentralDirectory(jPosition);
        int length = outArr.length;
        endRecord.setNumberOfDirectories(length);
        endRecord.setTotalNumberOfDirectories(length);
        ZipOutput zipOutput = getZipOutput();
        for (OUT out : outArr) {
            out.writeCEH(zipOutput);
        }
        endRecord.setLengthOfCentralDirectory(position() - jPosition);
        OutputStream outputStream = getOutputStream();
        Zip64Record zip64Record = endRecord.getZip64Record();
        if (zip64Record != null) {
            long jPosition2 = position();
            logMessage("ZIP64: " + zip64Record);
            zip64Record.writeBytes(outputStream);
            Zip64Locator zip64Locator = endRecord.getZip64Locator();
            zip64Locator.setOffsetZip64Record(jPosition2);
            logMessage("ZIP64: " + zip64Locator);
            zip64Locator.writeBytes(outputStream);
        }
        endRecord.writeBytes(getOutputStream());
    }

    /* JADX WARN: Multi-variable type inference failed */
    public OUT[] buildOutputEntries() {
        InputSource[] inputSources = getInputSources();
        int length = inputSources.length;
        OUT[] outArr = (OUT[]) createOutArray(length);
        HeaderInterceptorChain interceptorChain = getInterceptorChain();
        if (interceptorChain.isDisabled()) {
            interceptorChain = null;
        }
        for (int i = 0; i < length; i++) {
            OutputSource outputSource = toOutputSource(inputSources[i]);
            outputSource.setHeaderInterceptor(interceptorChain);
            outArr[i] = outputSource;
        }
        return outArr;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        this.zipOutput.close();
    }

    public void closeBuffer() throws IOException {
    }

    public abstract OUT[] createOutArray(int i);

    public APKLogger getApkLogger() {
        return this.apkLogger;
    }

    public ApkSignatureBlock getApkSignatureBlock() {
        return this.apkSignatureBlock;
    }

    public InputSource[] getInputSources() {
        return this.inputSources;
    }

    public HeaderInterceptorChain getInterceptorChain() {
        return this.interceptorChain;
    }

    public OutputStream getOutputStream() throws IOException {
        return this.zipOutput.getOutputStream();
    }

    public ZipAligner getZipAligner() {
        return this.zipAligner;
    }

    public T getZipOutput() {
        return this.zipOutput;
    }

    public void logMessage(String str) {
        APKLogger aPKLogger = this.apkLogger;
        if (aPKLogger != null) {
            aPKLogger.logMessage(str);
        }
    }

    public void onCompressFileProgress(String str, int i, long j) {
        WriteProgress writeProgress = this.writeProgress;
        if (writeProgress != null) {
            writeProgress.onCompressFile(str, i, j);
        }
    }

    public long position() throws IOException {
        return this.zipOutput.position();
    }

    public abstract void prepareOutputs(OUT[] outArr) throws IOException;

    public void setAPKLogger(APKLogger aPKLogger) {
        this.apkLogger = aPKLogger;
    }

    public void setApkSignatureBlock(ApkSignatureBlock apkSignatureBlock) {
        this.apkSignatureBlock = apkSignatureBlock;
    }

    public void setArchiveInfo(ArchiveInfo archiveInfo) {
        getInterceptorChain().setArchiveInfo(archiveInfo);
    }

    public void setDataDescriptorFactory(DataDescriptorFactory dataDescriptorFactory) {
        getInterceptorChain().setDataDescriptorFactory(dataDescriptorFactory);
    }

    public void setHeaderInterceptor(HeaderInterceptor headerInterceptor) {
        getInterceptorChain().setHeaderInterceptor(headerInterceptor);
    }

    public void setWriteProgress(WriteProgress writeProgress) {
        this.writeProgress = writeProgress;
    }

    public void setZipAligner(ZipAligner zipAligner) {
        this.zipAligner = zipAligner;
    }

    public abstract OUT toOutputSource(InputSource inputSource);

    public void write() throws IOException {
        synchronized (this.mLock) {
            OutputSource[] outputSourceArrBuildOutputEntries = buildOutputEntries();
            prepareOutputs(outputSourceArrBuildOutputEntries);
            writeApkList(outputSourceArrBuildOutputEntries);
            closeBuffer();
            writeSignatureBlock();
            writeCEHList(outputSourceArrBuildOutputEntries);
            close();
        }
    }

    public abstract void writeApk(OUT out, ZipAligner zipAligner) throws IOException;

    public void writeSignatureBlock() throws IOException {
        ApkSignatureBlock apkSignatureBlock = getApkSignatureBlock();
        if (apkSignatureBlock == null) {
            return;
        }
        logMessage("Writing signature block ...");
        long jPosition = position();
        if (ZipHeader.isZip64Length(jPosition)) {
            logMessage("ZIP64 mode, skip writing signature block!");
            return;
        }
        int i = (int) ((4096 - (jPosition % 4096)) % 4096);
        OutputStream outputStream = getOutputStream();
        if (i > 0) {
            outputStream.write(new byte[i]);
        }
        apkSignatureBlock.updatePadding();
        apkSignatureBlock.writeBytes(outputStream);
    }
}
