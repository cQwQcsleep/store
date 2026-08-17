package com.intellij.util.io.zip;

import com.intellij.util.ArrayUtil;
import java.util.zip.ZipException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class Zip64ExtraField implements JBZipExtraField {
    static final ZipShort HEADER_ID = new ZipShort(1);
    private ZipUInt64 myCompressedSize;
    private ZipUInt64 myHeaderOffset;
    private ZipUInt64 mySize;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 4 || i == 5) ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[(i == 4 || i == 5) ? 3 : 2];
        if (i == 4 || i == 5) {
            objArr[0] = "buffer";
        } else {
            objArr[0] = "com/intellij/util/io/zip/Zip64ExtraField";
        }
        if (i == 1 || i == 2) {
            objArr[1] = "getLocalFileDataData";
        } else if (i == 3) {
            objArr[1] = "getCentralDirectoryData";
        } else if (i == 4 || i == 5) {
            objArr[1] = "com/intellij/util/io/zip/Zip64ExtraField";
        } else {
            objArr[1] = "getHeaderId";
        }
        if (i == 4) {
            objArr[2] = "parseFromLocalFileData";
        } else if (i == 5) {
            objArr[2] = "parseFromCentralDirectoryData";
        }
        String str2 = String.format(str, objArr);
        if (i != 4 && i != 5) {
            throw new IllegalStateException(str2);
        }
        throw new IllegalArgumentException(str2);
    }

    public Zip64ExtraField(ZipUInt64 zipUInt64, ZipUInt64 zipUInt65, ZipUInt64 zipUInt66) {
        this.mySize = zipUInt64;
        this.myCompressedSize = zipUInt65;
        this.myHeaderOffset = zipUInt66;
    }

    private int addSizes(byte[] bArr) {
        int i;
        ZipUInt64 zipUInt64 = this.mySize;
        if (zipUInt64 != null) {
            System.arraycopy(zipUInt64.getBytes(), 0, bArr, 0, 8);
            i = 8;
        } else {
            i = 0;
        }
        ZipUInt64 zipUInt65 = this.myCompressedSize;
        if (zipUInt65 == null) {
            return i;
        }
        System.arraycopy(zipUInt65.getBytes(), 0, bArr, i, 8);
        return i + 8;
    }

    @Override // com.intellij.util.io.zip.JBZipExtraField
    public byte[] getCentralDirectoryData() {
        byte[] bArrNewByteArray = ArrayUtil.newByteArray(getCentralDirectoryLength().getValue());
        int iAddSizes = addSizes(bArrNewByteArray);
        ZipUInt64 zipUInt64 = this.myHeaderOffset;
        if (zipUInt64 != null) {
            System.arraycopy(zipUInt64.getBytes(), 0, bArrNewByteArray, iAddSizes, 8);
        }
        if (bArrNewByteArray == null) {
            $$$reportNull$$$0(3);
        }
        return bArrNewByteArray;
    }

    @Override // com.intellij.util.io.zip.JBZipExtraField
    public ZipShort getCentralDirectoryLength() {
        return new ZipShort((this.mySize != null ? 8 : 0) + (this.myCompressedSize != null ? 8 : 0) + (this.myHeaderOffset != null ? 8 : 0));
    }

    public ZipUInt64 getCompressedSize() {
        return this.myCompressedSize;
    }

    @Override // com.intellij.util.io.zip.JBZipExtraField
    public ZipShort getHeaderId() {
        ZipShort zipShort = HEADER_ID;
        if (zipShort == null) {
            $$$reportNull$$$0(0);
        }
        return zipShort;
    }

    public ZipUInt64 getHeaderOffset() {
        return this.myHeaderOffset;
    }

    @Override // com.intellij.util.io.zip.JBZipExtraField
    public byte[] getLocalFileDataData() {
        ZipUInt64 zipUInt64 = this.mySize;
        if (zipUInt64 == null && this.myCompressedSize == null) {
            byte[] bArr = ArrayUtil.EMPTY_BYTE_ARRAY;
            if (bArr == null) {
                $$$reportNull$$$0(2);
            }
            return bArr;
        }
        if (zipUInt64 == null || this.myCompressedSize == null) {
            w01.a("Must contain both size values in the local file header");
            return null;
        }
        byte[] bArr2 = new byte[16];
        addSizes(bArr2);
        return bArr2;
    }

    @Override // com.intellij.util.io.zip.JBZipExtraField
    public ZipShort getLocalFileDataLength() {
        return new ZipShort(this.mySize != null ? 16 : 0);
    }

    public ZipUInt64 getSize() {
        return this.mySize;
    }

    @Override // com.intellij.util.io.zip.JBZipExtraField
    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) throws ZipException {
        if (bArr == null) {
            $$$reportNull$$$0(5);
        }
        this.mySize = new ZipUInt64(bArr, i);
        this.myCompressedSize = new ZipUInt64(bArr, i + 8);
        this.myHeaderOffset = new ZipUInt64(bArr, i + 16);
    }

    public Zip64ExtraField() {
    }
}
