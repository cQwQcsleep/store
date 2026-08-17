package com.reandroid.archive.block;

import com.reandroid.archive.ArchiveException;
import com.reandroid.archive.ZipSignature;
import com.reandroid.archive.io.ZipInput;
import com.reandroid.utils.HexUtil;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class EndRecord extends ZipHeader {
    public static final int MAX_LENGTH = 65557;
    public static final int MIN_LENGTH = 22;
    private static final int OFFSET_centralDirectoryStartDisk = 6;
    private static final int OFFSET_lastShort = 20;
    private static final int OFFSET_lengthOfCentralDirectory = 12;
    private static final int OFFSET_numberOfDirectories = 8;
    private static final int OFFSET_numberOfDisk = 4;
    private static final int OFFSET_offsetOfCentralDirectory = 16;
    private static final int OFFSET_totalNumberOfDirectories = 10;
    private Zip64Locator zip64Locator;
    private Zip64Record zip64Record;

    public EndRecord() {
        super(22, ZipSignature.END_RECORD);
    }

    private boolean isZip64Value() {
        return getInteger(16) == -1;
    }

    public void findEndRecord(ZipInput zipInput) throws IOException {
        findEndRecord(zipInput.getFooter(65581));
        Zip64Locator zip64Locator = getZip64Locator();
        if (zip64Locator == null) {
            return;
        }
        Zip64Record zip64Record = new Zip64Record();
        zip64Record.readBytes(zipInput.getInputStream(zip64Locator.getOffsetZip64Record(), 56L));
        if (zip64Record.isValidSignature()) {
            setZip64Record(zip64Record);
            return;
        }
        StringBuilder sb = new StringBuilder("Invalid ");
        sb.append(ZipSignature.ZIP64_RECORD);
        String hex8 = HexUtil.toHex8(zip64Record.getSignatureValue());
        sb.append(": ");
        sb.append(hex8);
        throw new IOException(sb.toString());
    }

    public int getCentralDirectoryStartDisk() {
        return getShortUnsigned(6);
    }

    public int getLastShort() {
        return getShortUnsigned(20);
    }

    public long getLengthOfCentralDirectory() {
        return getIntegerUnsigned(12);
    }

    public int getNumberOfDirectories() {
        return getShortUnsigned(8);
    }

    public int getNumberOfDisk() {
        return getShortUnsigned(4);
    }

    public long getOffsetOfCentralDirectory() {
        Zip64Record zip64Record = getZip64Record();
        return zip64Record != null ? zip64Record.getOffsetOfCentralDirectory() : getIntegerUnsigned(16);
    }

    public int getTotalBytesCount() {
        int iCountBytes = countBytes();
        if (getZip64Locator() != null) {
            iCountBytes += this.zip64Locator.countBytes();
        }
        return getZip64Record() != null ? iCountBytes + this.zip64Locator.countBytes() : iCountBytes;
    }

    public int getTotalNumberOfDirectories() {
        return getShortUnsigned(10);
    }

    public Zip64Locator getZip64Locator() {
        return this.zip64Locator;
    }

    public Zip64Record getZip64Record() {
        return this.zip64Record;
    }

    public void setCentralDirectoryStartDisk(int i) {
        putShort(6, i);
    }

    public void setLengthOfCentralDirectory(long j) {
        putInteger(12, j);
        Zip64Record zip64Record = getZip64Record();
        if (zip64Record != null) {
            zip64Record.setSizeOfCD(j);
        }
    }

    public void setNumberOfDirectories(int i) {
        putShort(8, i);
        Zip64Record zip64Record = getZip64Record();
        if (zip64Record != null) {
            zip64Record.setNumberOfCDRecords(i);
        }
    }

    public void setNumberOfDisk(int i) {
        putShort(4, i);
    }

    public void setOffsetOfCentralDirectory(long j) {
        if (((-4294967296L) & j) == 0) {
            putInteger(16, j);
            this.zip64Locator = null;
            this.zip64Record = null;
            return;
        }
        Zip64Record zip64RecordNewZip64Record = this.zip64Record;
        if (zip64RecordNewZip64Record == null) {
            zip64RecordNewZip64Record = Zip64Record.newZip64Record();
            this.zip64Record = zip64RecordNewZip64Record;
        }
        putInteger(16, -1);
        zip64RecordNewZip64Record.setOffsetOfCentralDirectory(j);
        if (this.zip64Locator == null) {
            this.zip64Locator = Zip64Locator.newZip64Locator();
        }
    }

    public void setTotalNumberOfDirectories(int i) {
        putShort(10, i);
        Zip64Record zip64Record = getZip64Record();
        if (zip64Record != null) {
            zip64Record.setTotalCDRecords(i);
        }
    }

    public void setZip64Locator(Zip64Locator zip64Locator) {
        this.zip64Locator = zip64Locator;
    }

    public void setZip64Record(Zip64Record zip64Record) {
        this.zip64Record = zip64Record;
    }

    public String toString() {
        if (countBytes() < getMinByteLength()) {
            return "Invalid";
        }
        StringBuilder sb = new StringBuilder();
        sb.append(getSignature());
        if (isZip64Value()) {
            sb.append(", ZIP64");
        }
        sb.append(", disks=");
        sb.append(getNumberOfDisk());
        sb.append(", start disk=");
        sb.append(getCentralDirectoryStartDisk());
        sb.append(", dirs=");
        sb.append(getNumberOfDirectories());
        sb.append(", total dirs=");
        sb.append(getTotalNumberOfDirectories());
        sb.append(", length=");
        sb.append(getLengthOfCentralDirectory());
        sb.append(", offset=");
        sb.append(getOffsetOfCentralDirectory());
        sb.append(", last=");
        sb.append(HexUtil.toHex8(getLastShort()));
        return sb.toString();
    }

    public void getLastShort(int i) {
        putShort(20, i);
    }

    public void findEndRecord(byte[] bArr) throws IOException {
        int length = bArr.length - 22;
        while (length >= 0) {
            putBytes(bArr, length, 0, 22);
            if (isValidSignature()) {
                break;
            } else {
                length--;
            }
        }
        if (isValidSignature()) {
            if (isZip64Value()) {
                Zip64Locator zip64Locator = new Zip64Locator();
                for (int i = length - 20; i >= 0; i--) {
                    zip64Locator.putBytes(bArr, i, 0, 20);
                    if (zip64Locator.isValidSignature()) {
                        break;
                    }
                }
                if (zip64Locator.isValidSignature()) {
                    setZip64Locator(zip64Locator);
                    return;
                }
                throw new ArchiveException("Failed to find zip64 locator");
            }
            return;
        }
        throw new ArchiveException("Failed to find end record");
    }
}
