package com.intellij.util.io.zip;

import java.util.zip.ZipException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public interface JBZipExtraField {
    byte[] getCentralDirectoryData();

    ZipShort getCentralDirectoryLength();

    ZipShort getHeaderId();

    byte[] getLocalFileDataData();

    ZipShort getLocalFileDataLength();

    void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException;
}
