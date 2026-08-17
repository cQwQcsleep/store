package com.intellij.openapi.util.io;

import androidx.collection.ScatterMapKt;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class DataInputOutputUtilRt {
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 9 || i == 17) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 9 || i == 17) ? 2 : 3];
        switch (i) {
            case 1:
            case 3:
                objArr[0] = "byteBuffer";
                break;
            case 2:
            default:
                objArr[0] = "record";
                break;
            case 4:
            case 10:
                objArr[0] = "out";
                break;
            case 5:
                objArr[0] = "collection";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[0] = "writeElement";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 14:
                objArr[0] = "in";
                break;
            case 8:
                objArr[0] = "readElement";
                break;
            case 9:
            case 17:
                objArr[0] = "com/intellij/openapi/util/io/DataInputOutputUtilRt";
                break;
            case 11:
                objArr[0] = "map";
                break;
            case 12:
                objArr[0] = "writeKey";
                break;
            case 13:
                objArr[0] = "writeValue";
                break;
            case 15:
                objArr[0] = "readKey";
                break;
            case 16:
                objArr[0] = "readValue";
                break;
        }
        if (i == 9) {
            objArr[1] = "readSeq";
        } else if (i != 17) {
            objArr[1] = "com/intellij/openapi/util/io/DataInputOutputUtilRt";
        } else {
            objArr[1] = "readMap";
        }
        switch (i) {
            case 2:
            case 3:
                objArr[2] = "writeINT";
                break;
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "writeSeq";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                objArr[2] = "readSeq";
                break;
            case 9:
            case 17:
                break;
            case 10:
            case 11:
            case 12:
            case 13:
                objArr[2] = "writeMap";
                break;
            case 14:
            case 15:
            case 16:
                objArr[2] = "readMap";
                break;
            default:
                objArr[2] = "readINT";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 9 && i != 17) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public static int readINT(DataInput dataInput) throws IOException {
        if (dataInput == null) {
            $$$reportNull$$$0(0);
        }
        int unsignedByte = dataInput.readUnsignedByte();
        if (unsignedByte < 192) {
            return unsignedByte;
        }
        int i = unsignedByte - 192;
        int i2 = 6;
        while (true) {
            int unsignedByte2 = dataInput.readUnsignedByte();
            i |= (unsignedByte2 & 127) << i2;
            if ((unsignedByte2 & 128) == 0) {
                return i;
            }
            i2 += 7;
        }
    }

    public static void writeINT(DataOutput dataOutput, int i) throws IOException {
        if (dataOutput == null) {
            $$$reportNull$$$0(2);
        }
        if (i < 0 || i >= 192) {
            dataOutput.writeByte((i & 63) + 192);
            i >>>= 6;
            while (i >= 128) {
                dataOutput.writeByte(128 | (i & 127));
                i >>>= 7;
            }
        }
        dataOutput.writeByte(i);
    }
}
