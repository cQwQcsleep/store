package com.intellij.util.io.zip;

import androidx.collection.ScatterMapKt;
import com.intellij.util.ArrayUtil;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
class UnrecognizedExtraField implements JBZipExtraField {
    private byte[] myCentralData;
    private final ZipShort myHeaderId;
    private byte[] myLocalData;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 1 || i == 2 || i == 3 || i == 4 || i == 5) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 1 || i == 2 || i == 3 || i == 4 || i == 5) ? 2 : 3];
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                objArr[0] = "com/intellij/util/io/zip/UnrecognizedExtraField";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "data";
                break;
            default:
                objArr[0] = "headerId";
                break;
        }
        if (i == 1) {
            objArr[1] = "getHeaderId";
        } else if (i == 2) {
            objArr[1] = "getLocalFileDataData";
        } else if (i == 3) {
            objArr[1] = "getCentralDirectoryLength";
        } else if (i == 4 || i == 5) {
            objArr[1] = "getCentralDirectoryData";
        } else {
            objArr[1] = "com/intellij/util/io/zip/UnrecognizedExtraField";
        }
        switch (i) {
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "parseFromLocalFileData";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "parseFromCentralDirectoryData";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 1 && i != 2 && i != 3 && i != 4 && i != 5) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public UnrecognizedExtraField(ZipShort zipShort) {
        if (zipShort == null) {
            $$$reportNull$$$0(0);
        }
        this.myHeaderId = zipShort;
    }

    @Override // com.intellij.util.io.zip.JBZipExtraField
    public byte[] getCentralDirectoryData() {
        byte[] bArr = this.myCentralData;
        if (bArr != null) {
            byte[] bArrCopyOf = ArrayUtil.copyOf(bArr);
            if (bArrCopyOf == null) {
                $$$reportNull$$$0(4);
            }
            return bArrCopyOf;
        }
        byte[] localFileDataData = getLocalFileDataData();
        if (localFileDataData == null) {
            $$$reportNull$$$0(5);
        }
        return localFileDataData;
    }

    @Override // com.intellij.util.io.zip.JBZipExtraField
    public ZipShort getCentralDirectoryLength() {
        byte[] bArr = this.myCentralData;
        if (bArr != null) {
            return new ZipShort(bArr.length);
        }
        ZipShort localFileDataLength = getLocalFileDataLength();
        if (localFileDataLength == null) {
            $$$reportNull$$$0(3);
        }
        return localFileDataLength;
    }

    @Override // com.intellij.util.io.zip.JBZipExtraField
    public ZipShort getHeaderId() {
        ZipShort zipShort = this.myHeaderId;
        if (zipShort == null) {
            $$$reportNull$$$0(1);
        }
        return zipShort;
    }

    @Override // com.intellij.util.io.zip.JBZipExtraField
    public byte[] getLocalFileDataData() {
        byte[] bArrCopyOf = ArrayUtil.copyOf(this.myLocalData);
        if (bArrCopyOf == null) {
            $$$reportNull$$$0(2);
        }
        return bArrCopyOf;
    }

    @Override // com.intellij.util.io.zip.JBZipExtraField
    public ZipShort getLocalFileDataLength() {
        byte[] bArr = this.myLocalData;
        return new ZipShort(bArr != null ? bArr.length : 0);
    }

    @Override // com.intellij.util.io.zip.JBZipExtraField
    public void parseFromCentralDirectoryData(byte[] bArr, int i, int i2) {
        if (bArr == null) {
            $$$reportNull$$$0(7);
        }
        byte[] bArrCopyOfRange = Arrays.copyOfRange(bArr, i, i2 + i);
        setCentralDirectoryData(bArrCopyOfRange);
        if (this.myLocalData == null) {
            setLocalFileDataData(bArrCopyOfRange);
        }
    }

    public void setCentralDirectoryData(byte[] bArr) {
        this.myCentralData = ArrayUtil.copyOf(bArr);
    }

    public void setLocalFileDataData(byte[] bArr) {
        this.myLocalData = ArrayUtil.copyOf(bArr);
    }
}
