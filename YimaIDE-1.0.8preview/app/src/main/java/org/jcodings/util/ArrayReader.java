package org.jcodings.util;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import org.jcodings.exception.InternalException;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class ArrayReader {
    public static void checkAvailable(DataInputStream dataInputStream, String str) throws IOException {
        if (dataInputStream.available() == 0) {
            return;
        }
        throw new InternalException("length mismatch for table: " + str + " (" + dataInputStream.available() + " left)");
    }

    public static void decorate(IOException iOException, String str) {
        throw new InternalException("problem reading table: " + str + ": " + iOException);
    }

    public static DataInputStream openStream(String str) {
        String str2 = "/tables/" + str + ".bin";
        InputStream resourceAsStream = ArrayReader.class.getResourceAsStream(str2);
        if (resourceAsStream != null) {
            return new DataInputStream(new BufferedInputStream(resourceAsStream));
        }
        throw new InternalException("entry: " + str2 + " not found");
    }

    public static byte[] readByteArray(String str) {
        DataInputStream dataInputStreamOpenStream = openStream(str);
        try {
            int i = dataInputStreamOpenStream.readInt();
            byte[] bArr = new byte[i];
            for (int i2 = 0; i2 < i; i2++) {
                bArr[i2] = dataInputStreamOpenStream.readByte();
            }
            checkAvailable(dataInputStreamOpenStream, str);
            return bArr;
        } catch (IOException e) {
            decorate(e, str);
            try {
                return null;
            } catch (IOException unused) {
                return null;
            }
        } finally {
            try {
                dataInputStreamOpenStream.close();
            } catch (IOException unused2) {
            }
        }
    }

    public static int[] readIntArray(String str) {
        DataInputStream dataInputStreamOpenStream = openStream(str);
        try {
            int i = dataInputStreamOpenStream.readInt();
            int[] iArr = new int[i];
            for (int i2 = 0; i2 < i; i2++) {
                iArr[i2] = dataInputStreamOpenStream.readInt();
            }
            checkAvailable(dataInputStreamOpenStream, str);
            return iArr;
        } catch (IOException e) {
            decorate(e, str);
            try {
                return null;
            } catch (IOException unused) {
                return null;
            }
        } finally {
            try {
                dataInputStreamOpenStream.close();
            } catch (IOException unused2) {
            }
        }
    }

    public static int[][] readNestedIntArray(String str) {
        DataInputStream dataInputStreamOpenStream = openStream(str);
        try {
            int i = dataInputStreamOpenStream.readInt();
            int[][] iArr = new int[i][];
            for (int i2 = 0; i2 < i; i2++) {
                int i3 = dataInputStreamOpenStream.readInt();
                int[] iArr2 = new int[i3];
                iArr[i2] = iArr2;
                for (int i4 = 0; i4 < i3; i4++) {
                    iArr2[i4] = dataInputStreamOpenStream.readInt();
                }
            }
            checkAvailable(dataInputStreamOpenStream, str);
            return iArr;
        } catch (IOException e) {
            decorate(e, str);
            try {
                return null;
            } catch (IOException unused) {
                return null;
            }
        } finally {
            try {
                dataInputStreamOpenStream.close();
            } catch (IOException unused2) {
            }
        }
    }
}
