package jdk.internal.jimage.decompression;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class StringSharingDecompressor implements ResourceDecompressor {
    private static final int CONSTANT_Class = 7;
    private static final int CONSTANT_Double = 6;
    private static final int CONSTANT_Fieldref = 9;
    private static final int CONSTANT_Float = 4;
    private static final int CONSTANT_Integer = 3;
    private static final int CONSTANT_InterfaceMethodref = 11;
    private static final int CONSTANT_InvokeDynamic = 18;
    private static final int CONSTANT_Long = 5;
    private static final int CONSTANT_MethodHandle = 15;
    private static final int CONSTANT_MethodType = 16;
    private static final int CONSTANT_Methodref = 10;
    private static final int CONSTANT_Module = 19;
    private static final int CONSTANT_NameAndType = 12;
    private static final int CONSTANT_Package = 20;
    private static final int CONSTANT_String = 8;
    private static final int CONSTANT_Utf8 = 1;
    public static final int EXTERNALIZED_STRING = 23;
    public static final int EXTERNALIZED_STRING_DESCRIPTOR = 25;
    private static final int[] SIZES = {0, 0, 0, 4, 4, 8, 8, 2, 2, 4, 4, 4, 4, 0, 0, 3, 2, 0, 4, 2, 2};

    public static byte[] getEncoded(String str) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        new DataOutputStream(byteArrayOutputStream).writeUTF(str);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        return byteArray.length <= 2 ? new byte[0] : Arrays.copyOfRange(byteArray, 2, byteArray.length);
    }

    public static int[] getSizes() {
        return (int[]) SIZES.clone();
    }

    public static byte[] normalize(ResourceDecompressor.StringsProvider stringsProvider, byte[] bArr, int i) throws IOException {
        DataInputStream dataInputStream = new DataInputStream(new ByteArrayInputStream(bArr, i, bArr.length - i));
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(bArr.length);
        DataOutputStream dataOutputStream = new DataOutputStream(byteArrayOutputStream);
        byte[] bArr2 = new byte[8];
        dataInputStream.readFully(bArr2);
        dataOutputStream.write(bArr2);
        int unsignedShort = dataInputStream.readUnsignedShort();
        dataOutputStream.writeShort(unsignedShort);
        int i2 = 1;
        while (i2 < unsignedShort) {
            int unsignedByte = dataInputStream.readUnsignedByte();
            if (unsignedByte == 1) {
                dataOutputStream.write(unsignedByte);
                dataOutputStream.writeUTF(dataInputStream.readUTF());
            } else if (unsignedByte == 23) {
                String string = stringsProvider.getString(CompressIndexes.readInt(dataInputStream));
                dataOutputStream.write(1);
                dataOutputStream.writeUTF(string);
            } else if (unsignedByte != 25) {
                if (unsignedByte == 5 || unsignedByte == 6) {
                    i2++;
                }
                dataOutputStream.write(unsignedByte);
                byte[] bArr3 = new byte[SIZES[unsignedByte]];
                dataInputStream.readFully(bArr3);
                dataOutputStream.write(bArr3);
            } else {
                String strReconstruct = reconstruct(stringsProvider, dataInputStream);
                dataOutputStream.write(1);
                dataOutputStream.writeUTF(strReconstruct);
            }
            i2++;
        }
        dataOutputStream.write(bArr, bArr.length - dataInputStream.available(), dataInputStream.available());
        dataOutputStream.flush();
        return byteArrayOutputStream.toByteArray();
    }

    private static String reconstruct(ResourceDecompressor.StringsProvider stringsProvider, DataInputStream dataInputStream) throws IOException {
        byte[] encoded = getEncoded(stringsProvider.getString(CompressIndexes.readInt(dataInputStream)));
        byte[] bArr = new byte[CompressIndexes.readInt(dataInputStream)];
        dataInputStream.readFully(bArr);
        List<Integer> listDecompressFlow = CompressIndexes.decompressFlow(bArr);
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(encoded.length * 2);
        byteBufferAllocate.order(ByteOrder.BIG_ENDIAN);
        int i = 0;
        for (byte b : encoded) {
            if (b == 76) {
                ByteBuffer byteBufferSafeAdd = safeAdd(byteBufferAllocate, b);
                int i2 = i + 1;
                String string = stringsProvider.getString(listDecompressFlow.get(i).intValue());
                if (!string.isEmpty()) {
                    byteBufferSafeAdd = safeAdd(byteBufferSafeAdd, getEncoded(string.concat("/")));
                }
                i += 2;
                byteBufferAllocate = safeAdd(byteBufferSafeAdd, getEncoded(stringsProvider.getString(listDecompressFlow.get(i2).intValue())));
            } else {
                byteBufferAllocate = safeAdd(byteBufferAllocate, b);
            }
        }
        byte[] bArrArray = byteBufferAllocate.array();
        ByteBuffer byteBufferAllocate2 = ByteBuffer.allocate(bArrArray.length + 2);
        byteBufferAllocate2.order(ByteOrder.BIG_ENDIAN);
        byteBufferAllocate2.putShort((short) byteBufferAllocate.position());
        byteBufferAllocate2.put(bArrArray, 0, byteBufferAllocate.position());
        return new DataInputStream(new ByteArrayInputStream(byteBufferAllocate2.array())).readUTF();
    }

    private static ByteBuffer safeAdd(ByteBuffer byteBuffer, byte[] bArr) {
        if (byteBuffer.remaining() < bArr.length) {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate((byteBuffer.capacity() + bArr.length) * 2);
            byteBufferAllocate.order(ByteOrder.BIG_ENDIAN);
            byteBufferAllocate.put(byteBuffer.array(), 0, byteBuffer.position());
            byteBuffer = byteBufferAllocate;
        }
        byteBuffer.put(bArr);
        return byteBuffer;
    }

    @Override // jdk.internal.jimage.decompression.ResourceDecompressor
    public byte[] decompress(ResourceDecompressor.StringsProvider stringsProvider, byte[] bArr, int i, long j) throws Exception {
        return normalize(stringsProvider, bArr, i);
    }

    @Override // jdk.internal.jimage.decompression.ResourceDecompressor
    public String getName() {
        return StringSharingDecompressorFactory.NAME;
    }

    private static ByteBuffer safeAdd(ByteBuffer byteBuffer, byte b) {
        return safeAdd(byteBuffer, new byte[]{b});
    }
}
