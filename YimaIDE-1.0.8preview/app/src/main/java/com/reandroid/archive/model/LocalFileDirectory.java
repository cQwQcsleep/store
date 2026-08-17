package com.reandroid.archive.model;

import com.reandroid.archive.ArchiveEntry;
import com.reandroid.archive.ArchiveException;
import com.reandroid.archive.block.ApkSignatureBlock;
import com.reandroid.archive.block.CentralEntryHeader;
import com.reandroid.archive.block.DataDescriptor;
import com.reandroid.archive.block.EndRecord;
import com.reandroid.archive.block.LocalFileHeader;
import com.reandroid.archive.block.SignatureFooter;
import com.reandroid.archive.io.ZipInput;
import com.reandroid.arsc.io.BlockReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class LocalFileDirectory {
    private ApkSignatureBlock apkSignatureBlock;
    private final CentralFileDirectory centralFileDirectory;
    private final List<LocalFileHeader> headerList;

    public LocalFileDirectory(CentralFileDirectory centralFileDirectory) {
        this.centralFileDirectory = centralFileDirectory;
        this.headerList = new ArrayList(centralFileDirectory.count() + 2);
    }

    private void visitApkSigBlock(ZipInput zipInput) throws IOException {
        CentralFileDirectory centralFileDirectory = getCentralFileDirectory();
        SignatureFooter signatureFooter = centralFileDirectory.getSignatureFooter();
        if (signatureFooter == null || !signatureFooter.isValid()) {
            return;
        }
        EndRecord endRecord = centralFileDirectory.getEndRecord();
        long signatureSize = signatureFooter.getSignatureSize() + 8;
        long offsetOfCentralDirectory = endRecord.getOffsetOfCentralDirectory() - signatureSize;
        ApkSignatureBlock apkSignatureBlock = new ApkSignatureBlock(signatureFooter);
        apkSignatureBlock.readBytes(new BlockReader(zipInput.getInputStream(offsetOfCentralDirectory, signatureSize)));
        this.apkSignatureBlock = apkSignatureBlock;
    }

    private void visitLocalFile(ZipInput zipInput) throws IOException {
        List<LocalFileHeader> headerList = getHeaderList();
        CentralFileDirectory centralFileDirectory = getCentralFileDirectory();
        InputStream inputStream = zipInput.getInputStream(0L, zipInput.getLength());
        int i = 0;
        for (CentralEntryHeader centralEntryHeader : centralFileDirectory.getHeaderList()) {
            long localRelativeOffset = centralEntryHeader.getLocalRelativeOffset();
            inputStream.reset();
            long jSkip = inputStream.skip(localRelativeOffset);
            LocalFileHeader localFileHeader = LocalFileHeader.read(inputStream);
            if (localFileHeader == null) {
                throw new ArchiveException("Error reading LFH at " + jSkip + ", for CEH = " + centralEntryHeader.getFileName());
            }
            centralEntryHeader.setFileOffset(jSkip + ((long) localFileHeader.countBytes()));
            localFileHeader.setCentralEntryHeader(centralEntryHeader);
            inputStream.skip(localFileHeader.getDataSize());
            localFileHeader.updateDataDescriptor();
            DataDescriptor dataDescriptor = localFileHeader.getDataDescriptor();
            if (dataDescriptor != null && dataDescriptor.readBytes(inputStream) != dataDescriptor.countBytes()) {
                localFileHeader.setHasDataDescriptor(false);
            }
            localFileHeader.setIndex(i);
            headerList.add(localFileHeader);
            i++;
        }
    }

    public ArchiveEntry[] buildArchiveEntryList() {
        List<LocalFileHeader> headerList = getHeaderList();
        int size = headerList.size();
        ArchiveEntry[] archiveEntryArr = new ArchiveEntry[size];
        for (int i = 0; i < size; i++) {
            LocalFileHeader localFileHeader = headerList.get(i);
            if (localFileHeader.getCentralEntryHeader() != null) {
                archiveEntryArr[i] = new ArchiveEntry(localFileHeader);
            }
        }
        return archiveEntryArr;
    }

    public ApkSignatureBlock getApkSigBlock() {
        return this.apkSignatureBlock;
    }

    public CentralFileDirectory getCentralFileDirectory() {
        return this.centralFileDirectory;
    }

    public List<LocalFileHeader> getHeaderList() {
        return this.headerList;
    }

    public void visit(ZipInput zipInput) throws IOException {
        visitLocalFile(zipInput);
        visitApkSigBlock(zipInput);
    }
}
