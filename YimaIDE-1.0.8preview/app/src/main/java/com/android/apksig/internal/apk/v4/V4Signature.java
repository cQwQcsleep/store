package com.android.apksig.internal.apk.v4;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.util.ArrayList;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class V4Signature {
    public static final int CURRENT_VERSION = 2;
    public static final int HASHING_ALGORITHM_SHA256 = 1;
    public static final byte LOG2_BLOCK_SIZE_4096_BYTES = 12;
    public static final int MAX_SIGNING_INFOS_SIZE = 7168;
    public final byte[] hashingInfo;
    public final byte[] signingInfos;
    public final int version;

    public static class HashingInfo {
        public final int hashAlgorithm;
        public final byte log2BlockSize;
        public final byte[] rawRootHash;
        public final byte[] salt;

        public HashingInfo(int i, byte b, byte[] bArr, byte[] bArr2) {
            this.hashAlgorithm = i;
            this.log2BlockSize = b;
            this.salt = bArr;
            this.rawRootHash = bArr2;
        }

        public static HashingInfo fromByteArray(byte[] bArr) throws IOException {
            ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            return new HashingInfo(byteBufferOrder.getInt(), byteBufferOrder.get(), V4Signature.readBytes(byteBufferOrder), V4Signature.readBytes(byteBufferOrder));
        }

        public byte[] toByteArray() {
            ByteBuffer byteBufferOrder = ByteBuffer.allocate(V4Signature.bytesSize(this.salt) + 5 + V4Signature.bytesSize(this.rawRootHash)).order(ByteOrder.LITTLE_ENDIAN);
            byteBufferOrder.putInt(this.hashAlgorithm);
            byteBufferOrder.put(this.log2BlockSize);
            V4Signature.writeBytes(byteBufferOrder, this.salt);
            V4Signature.writeBytes(byteBufferOrder, this.rawRootHash);
            return byteBufferOrder.array();
        }
    }

    public static class SigningInfo {
        public final byte[] additionalData;
        public final byte[] apkDigest;
        public final byte[] certificate;
        public final byte[] publicKey;
        public final byte[] signature;
        public final int signatureAlgorithmId;

        public SigningInfo(byte[] bArr, byte[] bArr2, byte[] bArr3, byte[] bArr4, int i, byte[] bArr5) {
            this.apkDigest = bArr;
            this.certificate = bArr2;
            this.additionalData = bArr3;
            this.publicKey = bArr4;
            this.signatureAlgorithmId = i;
            this.signature = bArr5;
        }

        public static SigningInfo fromByteArray(byte[] bArr) throws IOException {
            return fromByteBuffer(ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN));
        }

        public static SigningInfo fromByteBuffer(ByteBuffer byteBuffer) throws IOException {
            return new SigningInfo(V4Signature.readBytes(byteBuffer), V4Signature.readBytes(byteBuffer), V4Signature.readBytes(byteBuffer), V4Signature.readBytes(byteBuffer), byteBuffer.getInt(), V4Signature.readBytes(byteBuffer));
        }

        public byte[] toByteArray() {
            ByteBuffer byteBufferOrder = ByteBuffer.allocate(V4Signature.bytesSize(this.apkDigest) + V4Signature.bytesSize(this.certificate) + V4Signature.bytesSize(this.additionalData) + V4Signature.bytesSize(this.publicKey) + 4 + V4Signature.bytesSize(this.signature)).order(ByteOrder.LITTLE_ENDIAN);
            V4Signature.writeBytes(byteBufferOrder, this.apkDigest);
            V4Signature.writeBytes(byteBufferOrder, this.certificate);
            V4Signature.writeBytes(byteBufferOrder, this.additionalData);
            V4Signature.writeBytes(byteBufferOrder, this.publicKey);
            byteBufferOrder.putInt(this.signatureAlgorithmId);
            V4Signature.writeBytes(byteBufferOrder, this.signature);
            return byteBufferOrder.array();
        }
    }

    public static class SigningInfoBlock {
        public final int blockId;
        public final byte[] signingInfo;

        public SigningInfoBlock(int i, byte[] bArr) {
            this.blockId = i;
            this.signingInfo = bArr;
        }

        public static SigningInfoBlock fromByteBuffer(ByteBuffer byteBuffer) throws IOException {
            return new SigningInfoBlock(byteBuffer.getInt(), V4Signature.readBytes(byteBuffer));
        }

        public byte[] toByteArray() {
            ByteBuffer byteBufferOrder = ByteBuffer.allocate(V4Signature.bytesSize(this.signingInfo) + 4).order(ByteOrder.LITTLE_ENDIAN);
            byteBufferOrder.putInt(this.blockId);
            V4Signature.writeBytes(byteBufferOrder, this.signingInfo);
            return byteBufferOrder.array();
        }
    }

    public V4Signature(int i, byte[] bArr, byte[] bArr2) {
        this.version = i;
        this.hashingInfo = bArr;
        this.signingInfos = bArr2;
    }

    public static int bytesSize(byte[] bArr) {
        return (bArr == null ? 0 : bArr.length) + 4;
    }

    public static byte[] getSignedData(long j, HashingInfo hashingInfo, SigningInfo signingInfo) {
        int iBytesSize = bytesSize(hashingInfo.salt) + 17 + bytesSize(hashingInfo.rawRootHash) + bytesSize(signingInfo.apkDigest) + bytesSize(signingInfo.certificate) + bytesSize(signingInfo.additionalData);
        ByteBuffer byteBufferOrder = ByteBuffer.allocate(iBytesSize).order(ByteOrder.LITTLE_ENDIAN);
        byteBufferOrder.putInt(iBytesSize);
        byteBufferOrder.putLong(j);
        byteBufferOrder.putInt(hashingInfo.hashAlgorithm);
        byteBufferOrder.put(hashingInfo.log2BlockSize);
        writeBytes(byteBufferOrder, hashingInfo.salt);
        writeBytes(byteBufferOrder, hashingInfo.rawRootHash);
        writeBytes(byteBufferOrder, signingInfo.apkDigest);
        writeBytes(byteBufferOrder, signingInfo.certificate);
        writeBytes(byteBufferOrder, signingInfo.additionalData);
        return byteBufferOrder.array();
    }

    public static byte[] readBytes(ByteBuffer byteBuffer) throws IOException {
        if (byteBuffer.remaining() < 4) {
            u01.a();
            return null;
        }
        int i = byteBuffer.getInt();
        if (byteBuffer.remaining() < i) {
            u01.a();
            return null;
        }
        byte[] bArr = new byte[i];
        byteBuffer.get(bArr);
        return bArr;
    }

    public static V4Signature readFrom(InputStream inputStream) throws IOException {
        int intLE = readIntLE(inputStream);
        if (intLE == 2) {
            return new V4Signature(intLE, readBytes(inputStream), readBytes(inputStream));
        }
        a16.a("Invalid signature version.");
        return null;
    }

    public static void readFully(InputStream inputStream, byte[] bArr) throws IOException {
        int length = bArr.length;
        int i = 0;
        while (i < length) {
            int i2 = inputStream.read(bArr, i, length - i);
            if (i2 < 0) {
                u01.a();
                return;
            }
            i += i2;
        }
    }

    public static int readIntLE(InputStream inputStream) throws IOException {
        byte[] bArr = new byte[4];
        readFully(inputStream, bArr);
        return ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN).getInt();
    }

    public static void writeBytes(OutputStream outputStream, byte[] bArr) throws IOException {
        if (bArr == null) {
            writeIntLE(outputStream, 0);
        } else {
            writeIntLE(outputStream, bArr.length);
            outputStream.write(bArr);
        }
    }

    public static void writeIntLE(OutputStream outputStream, int i) throws IOException {
        outputStream.write(ByteBuffer.wrap(new byte[4]).order(ByteOrder.LITTLE_ENDIAN).putInt(i).array());
    }

    public void writeTo(OutputStream outputStream) throws IOException {
        writeIntLE(outputStream, this.version);
        writeBytes(outputStream, this.hashingInfo);
        writeBytes(outputStream, this.signingInfos);
    }

    public static class SigningInfos {
        public final SigningInfo signingInfo;
        public final SigningInfoBlock[] signingInfoBlocks;

        public SigningInfos(SigningInfo signingInfo) {
            this.signingInfo = signingInfo;
            this.signingInfoBlocks = new SigningInfoBlock[0];
        }

        public static SigningInfos fromByteArray(byte[] bArr) throws IOException {
            ByteBuffer byteBufferOrder = ByteBuffer.wrap(bArr).order(ByteOrder.LITTLE_ENDIAN);
            SigningInfo signingInfoFromByteBuffer = SigningInfo.fromByteBuffer(byteBufferOrder);
            if (!byteBufferOrder.hasRemaining()) {
                return new SigningInfos(signingInfoFromByteBuffer);
            }
            ArrayList arrayList = new ArrayList(1);
            while (byteBufferOrder.hasRemaining()) {
                arrayList.add(SigningInfoBlock.fromByteBuffer(byteBufferOrder));
            }
            return new SigningInfos(signingInfoFromByteBuffer, (SigningInfoBlock[]) arrayList.toArray(new SigningInfoBlock[arrayList.size()]));
        }

        public byte[] toByteArray() {
            byte[][] bArr = new byte[this.signingInfoBlocks.length + 1][];
            byte[] byteArray = this.signingInfo.toByteArray();
            bArr[0] = byteArray;
            int length = byteArray.length;
            int length2 = this.signingInfoBlocks.length;
            int i = 0;
            while (i < length2) {
                int i2 = i + 1;
                byte[] byteArray2 = this.signingInfoBlocks[i].toByteArray();
                bArr[i2] = byteArray2;
                length += byteArray2.length;
                i = i2;
            }
            if (length > 7168) {
                qf1.a("Combined SigningInfos length exceeded limit of 7K: ", length);
                return null;
            }
            byte[] bArrCopyOf = Arrays.copyOf(bArr[0], length);
            int length3 = bArr[0].length;
            int length4 = this.signingInfoBlocks.length;
            int i3 = 0;
            while (i3 < length4) {
                i3++;
                byte[] bArr2 = bArr[i3];
                System.arraycopy(bArr2, 0, bArrCopyOf, length3, bArr2.length);
                length3 += bArr[i3].length;
            }
            return bArrCopyOf;
        }

        public SigningInfos(SigningInfo signingInfo, SigningInfoBlock... signingInfoBlockArr) {
            this.signingInfo = signingInfo;
            this.signingInfoBlocks = signingInfoBlockArr;
        }
    }

    public static void writeBytes(ByteBuffer byteBuffer, byte[] bArr) {
        if (bArr == null) {
            byteBuffer.putInt(0);
        } else {
            byteBuffer.putInt(bArr.length);
            byteBuffer.put(bArr);
        }
    }

    public static byte[] readBytes(InputStream inputStream) throws IOException {
        try {
            byte[] bArr = new byte[readIntLE(inputStream)];
            readFully(inputStream, bArr);
            return bArr;
        } catch (EOFException unused) {
            return null;
        }
    }
}
