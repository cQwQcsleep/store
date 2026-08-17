package com.reandroid.archive.model;

import com.reandroid.archive.block.CentralEntryHeader;
import com.reandroid.archive.block.EndRecord;
import com.reandroid.archive.block.SignatureFooter;
import com.reandroid.archive.io.ZipInput;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class CentralFileDirectory {
    private EndRecord endRecord;
    private List<CentralEntryHeader> headerList = new ArrayList();
    private SignatureFooter signatureFooter;

    private List<CentralEntryHeader> loadCentralFileHeaders(InputStream inputStream, int i) throws IOException {
        ArrayList arrayList = new ArrayList(i);
        CentralEntryHeader centralEntryHeader = new CentralEntryHeader();
        centralEntryHeader.readBytes(inputStream);
        while (centralEntryHeader.isValidSignature()) {
            arrayList.add(centralEntryHeader);
            centralEntryHeader = new CentralEntryHeader();
            centralEntryHeader.readBytes(inputStream);
        }
        inputStream.close();
        return arrayList;
    }

    private SignatureFooter tryFindSignatureFooter(ZipInput zipInput, EndRecord endRecord) throws IOException {
        long length = ((zipInput.getLength() - ((long) endRecord.getTotalBytesCount())) - endRecord.getLengthOfCentralDirectory()) - 24;
        if (length < 0) {
            return null;
        }
        InputStream inputStream = zipInput.getInputStream(length, 24L);
        SignatureFooter signatureFooter = new SignatureFooter();
        signatureFooter.readBytes(inputStream);
        inputStream.close();
        if (signatureFooter.isValid()) {
            return signatureFooter;
        }
        return null;
    }

    public int count() {
        return this.headerList.size();
    }

    public EndRecord getEndRecord() {
        return this.endRecord;
    }

    public List<CentralEntryHeader> getHeaderList() {
        return this.headerList;
    }

    public SignatureFooter getSignatureFooter() {
        return this.signatureFooter;
    }

    public void visit(ZipInput zipInput) throws IOException {
        EndRecord endRecord = new EndRecord();
        endRecord.findEndRecord(zipInput);
        InputStream inputStream = zipInput.getInputStream(endRecord.getOffsetOfCentralDirectory(), endRecord.getLengthOfCentralDirectory());
        this.endRecord = endRecord;
        this.headerList = loadCentralFileHeaders(inputStream, endRecord.getTotalNumberOfDirectories());
        this.signatureFooter = tryFindSignatureFooter(zipInput, endRecord);
    }
}
