package com.reandroid.archive.block;

import com.reandroid.archive.ZipSignature;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class Zip64Locator extends ZipHeader {
    public static final int MIN_LENGTH = 20;
    private static final int OFFSET_numberOfDisks = 16;
    private static final int OFFSET_offsetZip64Record = 8;

    public Zip64Locator() {
        super(20, ZipSignature.ZIP64_LOCATOR);
    }

    public static Zip64Locator newZip64Locator() {
        Zip64Locator zip64Locator = new Zip64Locator();
        zip64Locator.setSignature(ZipSignature.ZIP64_LOCATOR);
        zip64Locator.setNumberOfDisks(1);
        return zip64Locator;
    }

    public int getNumberOfDisks() {
        return getInteger(16);
    }

    public long getOffsetZip64Record() {
        return getLong(8);
    }

    public void setNumberOfDisks(int i) {
        putInteger(16, i);
    }

    public void setOffsetZip64Record(long j) {
        putLong(8, j);
    }

    public String toString() {
        if (countBytes() < getMinByteLength()) {
            return "Invalid";
        }
        return getSignature() + ", offsetZip64Record=" + getOffsetZip64Record() + ", numberOfDisks=" + getNumberOfDisks();
    }
}
