package com.reandroid.archive.writer;

import com.reandroid.apk.APKLogger;
import com.reandroid.archive.Archive;
import com.reandroid.archive.InputSource;
import com.reandroid.archive.ZipSignature;
import com.reandroid.archive.block.CentralEntryHeader;
import com.reandroid.archive.block.DataDescriptor;
import com.reandroid.archive.block.LocalFileHeader;
import com.reandroid.archive.io.CountingOutputStream;
import com.reandroid.archive.io.ZipOutput;
import com.reandroid.utils.io.FileUtil;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;
import java.util.zip.DeflaterOutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
class OutputSource {
    private static final long LOG_LARGE_FILE_SIZE = 2048000;
    private APKLogger apkLogger;
    private HeaderInterceptor headerInterceptor;
    private final InputSource inputSource;
    private LocalFileHeader lfh;

    public OutputSource(InputSource inputSource) {
        this.inputSource = inputSource;
    }

    private void notifyCEHWrite(CentralEntryHeader centralEntryHeader) {
        HeaderInterceptor headerInterceptor = this.headerInterceptor;
        if (headerInterceptor != null) {
            headerInterceptor.onWriteCeh(centralEntryHeader);
        }
    }

    private void notifyDDWrite(DataDescriptor dataDescriptor) {
        HeaderInterceptor headerInterceptor = this.headerInterceptor;
        if (headerInterceptor != null) {
            headerInterceptor.onWriteDD(dataDescriptor);
        }
    }

    private void notifyLFHWrite(LocalFileHeader localFileHeader) {
        HeaderInterceptor headerInterceptor = this.headerInterceptor;
        if (headerInterceptor != null) {
            headerInterceptor.onWriteLfh(localFileHeader);
        }
    }

    public LocalFileHeader createLocalFileHeader() {
        InputSource inputSource = getInputSource();
        LocalFileHeader localFileHeader = new LocalFileHeader();
        localFileHeader.setSignature(ZipSignature.LOCAL_FILE);
        localFileHeader.getGeneralPurposeFlag().initDefault();
        localFileHeader.setFileName(inputSource.getAlias());
        localFileHeader.setMethod(inputSource.getMethod());
        return localFileHeader;
    }

    public InputSource getInputSource() {
        return this.inputSource;
    }

    public LocalFileHeader getLocalFileHeader() {
        if (this.lfh == null) {
            LocalFileHeader localFileHeaderCreateLocalFileHeader = createLocalFileHeader();
            localFileHeaderCreateLocalFileHeader.setFileName(getInputSource().getAlias());
            localFileHeaderCreateLocalFileHeader.setZipAlign(0);
            localFileHeaderCreateLocalFileHeader.updateDataDescriptor();
            this.lfh = localFileHeaderCreateLocalFileHeader;
        }
        return this.lfh;
    }

    public void logFileWrite() {
        if (this.apkLogger == null) {
            return;
        }
        logVerbose("Write [" + FileUtil.toReadableFileSize(getLocalFileHeader().getDataSize()) + "] " + getInputSource().getAlias());
    }

    public void logLargeFileWrite() {
        if (this.apkLogger != null && getLocalFileHeader().getDataSize() >= LOG_LARGE_FILE_SIZE) {
            logFileWrite();
        }
    }

    public void logVerbose(String str) {
        APKLogger aPKLogger = this.apkLogger;
        if (aPKLogger != null) {
            aPKLogger.logVerbose(str);
        }
    }

    public void setAPKLogger(APKLogger aPKLogger) {
        this.apkLogger = aPKLogger;
    }

    public void setHeaderInterceptor(HeaderInterceptor headerInterceptor) {
        this.headerInterceptor = headerInterceptor;
    }

    public void writeBuffer(ZipOutput zipOutput) throws IOException {
        LocalFileHeader localFileHeader = getLocalFileHeader();
        InputSource inputSource = getInputSource();
        CountingOutputStream countingOutputStream = new CountingOutputStream(zipOutput.getOutputStream());
        int method = inputSource.getMethod();
        int i = Archive.STORED;
        CountingOutputStream countingOutputStream2 = method != i ? new CountingOutputStream(new DeflaterOutputStream((OutputStream) countingOutputStream, new Deflater(-1, true), true), false) : null;
        if (countingOutputStream2 != null) {
            countingOutputStream.disableCrc(true);
            inputSource.write(countingOutputStream2);
            countingOutputStream2.close();
            countingOutputStream.close();
        } else {
            inputSource.write(countingOutputStream);
        }
        localFileHeader.setCompressedSize(countingOutputStream.getSize());
        if (countingOutputStream2 != null) {
            localFileHeader.setMethod(Archive.DEFLATED);
            localFileHeader.setCrc(countingOutputStream2.getCrc32());
            localFileHeader.setSize(countingOutputStream2.getSize());
        } else {
            localFileHeader.setSize(countingOutputStream.getSize());
            localFileHeader.setMethod(i);
            localFileHeader.setCrc(countingOutputStream.getCrc32());
        }
        inputSource.disposeInputSource();
    }

    public void writeCEH(ZipOutput zipOutput) throws IOException {
        CentralEntryHeader centralEntryHeaderFromLocalFileHeader = CentralEntryHeader.fromLocalFileHeader(getLocalFileHeader());
        notifyCEHWrite(centralEntryHeaderFromLocalFileHeader);
        centralEntryHeaderFromLocalFileHeader.writeBytes(zipOutput.getOutputStream());
    }

    public void writeDD(ZipOutput zipOutput) throws IOException {
        DataDescriptor dataDescriptor = getLocalFileHeader().getDataDescriptor();
        if (dataDescriptor == null) {
            return;
        }
        notifyDDWrite(dataDescriptor);
        dataDescriptor.writeBytes(zipOutput.getOutputStream());
    }

    public void writeLFH(ZipOutput zipOutput, ZipAligner zipAligner) throws IOException {
        LocalFileHeader localFileHeader = getLocalFileHeader();
        if (zipAligner != null) {
            zipAligner.align(zipOutput.position(), localFileHeader);
        }
        notifyLFHWrite(localFileHeader);
        localFileHeader.writeBytes(zipOutput.getOutputStream());
    }
}
