package com.intellij.util.io;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.util.io.DataInputOutputUtilRt;
import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class DataInputOutputUtil {
    /* JADX WARN: Code duplicated, block: B:25:0x0052  */
    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = i != 19 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
        Object[] objArr = new Object[i != 19 ? 3 : 2];
        if (i == 1 || i == 3) {
            objArr[0] = "byteBuffer";
        } else if (i != 10) {
            switch (i) {
                case 12:
                    objArr[0] = "buffer";
                    break;
                case 13:
                case 20:
                    objArr[0] = "out";
                    break;
                case 14:
                    objArr[0] = "writeValue";
                    break;
                case 15:
                case 17:
                    objArr[0] = "in";
                    break;
                case 16:
                    objArr[0] = "readValue";
                    break;
                case 18:
                    objArr[0] = "readElement";
                    break;
                case 19:
                    objArr[0] = "com/intellij/util/io/DataInputOutputUtil";
                    break;
                case 21:
                    objArr[0] = "collection";
                    break;
                case 22:
                    objArr[0] = "writeElement";
                    break;
                default:
                    objArr[0] = "record";
                    break;
            }
        } else {
            objArr[0] = "buffer";
        }
        if (i != 19) {
            objArr[1] = "com/intellij/util/io/DataInputOutputUtil";
        } else {
            objArr[1] = "readSeq";
        }
        switch (i) {
            case 2:
            case 3:
                objArr[2] = "writeINT";
                break;
            case 4:
            case 5:
                objArr[2] = "readLONG";
                break;
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
                objArr[2] = "writeLONG";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "readSINT";
                break;
            case 8:
                objArr[2] = "writeSINT";
                break;
            case 9:
            case 10:
                objArr[2] = "writeTIME";
                break;
            case 11:
            case 12:
                objArr[2] = "readTIME";
                break;
            case 13:
            case 14:
                objArr[2] = "writeNullable";
                break;
            case 15:
            case 16:
                objArr[2] = "readNullable";
                break;
            case 17:
            case 18:
                objArr[2] = "readSeq";
                break;
            case 19:
                break;
            case 20:
            case 21:
            case 22:
                objArr[2] = "writeSeq";
                break;
            default:
                objArr[2] = "readINT";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i == 19) {
            throw new IllegalStateException(str2);
        }
    }

    public static int readINT(DataInput dataInput) throws IOException {
        if (dataInput == null) {
            $$$reportNull$$$0(0);
        }
        return DataInputOutputUtilRt.readINT(dataInput);
    }

    public static long readLONG(DataInput dataInput) throws IOException {
        if (dataInput == null) {
            $$$reportNull$$$0(4);
        }
        int unsignedByte = dataInput.readUnsignedByte();
        if (unsignedByte < 192) {
            return unsignedByte;
        }
        long j = unsignedByte - 192;
        int i = 6;
        while (true) {
            int unsignedByte2 = dataInput.readUnsignedByte();
            j |= ((long) (unsignedByte2 & 127)) << i;
            if ((unsignedByte2 & 128) == 0) {
                return j;
            }
            i += 7;
        }
    }

    public static void writeINT(DataOutput dataOutput, int i) throws IOException {
        if (dataOutput == null) {
            $$$reportNull$$$0(2);
        }
        DataInputOutputUtilRt.writeINT(dataOutput, i);
    }

    public static void writeLONG(DataOutput dataOutput, long j) throws IOException {
        char c = 6;
        if (dataOutput == null) {
            $$$reportNull$$$0(6);
        }
        if (0 > j || j >= 192) {
            dataOutput.writeByte(((int) (63 & j)) + 192);
            while (true) {
                j >>>= c;
                if (j < 128) {
                    break;
                }
                dataOutput.writeByte(((int) (127 & j)) | 128);
                c = 7;
            }
        }
        dataOutput.writeByte((int) j);
    }
}
