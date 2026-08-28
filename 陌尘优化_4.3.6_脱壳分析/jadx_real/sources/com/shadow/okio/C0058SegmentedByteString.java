package com.shadow.okio;

import com.shadow.kotlin.collections.ArraysKt;
import com.shadow.kotlin.io.CloseableKt;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

/* renamed from: com.shadow.okio.SegmentedByteString, reason: case insensitive filesystem */
/* loaded from: /workspace/unpacked/classes2.dex */
public final class C0058SegmentedByteString extends ByteString {
    private final transient int[] directory;
    private final transient byte[][] segments;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public C0058SegmentedByteString(byte[][] bArr, int[] iArr) {
        super(ByteString.EMPTY.getData$okio());
        CloseableKt.checkNotNullParameter(bArr, "segments");
        CloseableKt.checkNotNullParameter(iArr, "directory");
        this.segments = bArr;
        this.directory = iArr;
    }

    private final ByteString toByteString() {
        return new ByteString(toByteArray());
    }

    private final Object writeReplace() {
        ByteString byteString = toByteString();
        CloseableKt.checkNotNull(byteString, "null cannot be cast to non-null type java.lang.Object");
        return byteString;
    }

    @Override // com.shadow.okio.ByteString
    public ByteBuffer asByteBuffer() {
        ByteBuffer byteBufferAsReadOnlyBuffer = ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
        CloseableKt.checkNotNullExpressionValue(byteBufferAsReadOnlyBuffer, "asReadOnlyBuffer(...)");
        return byteBufferAsReadOnlyBuffer;
    }

    @Override // com.shadow.okio.ByteString
    public String base64() {
        return toByteString().base64();
    }

    @Override // com.shadow.okio.ByteString
    public String base64Url() {
        return toByteString().base64Url();
    }

    @Override // com.shadow.okio.ByteString
    public void copyInto(int i, byte[] bArr, int i2, int i3) {
        CloseableKt.checkNotNullParameter(bArr, "target");
        long j = i3;
        SegmentedByteString.checkOffsetAndCount(size(), i, j);
        SegmentedByteString.checkOffsetAndCount(bArr.length, i2, j);
        int i4 = i3 + i;
        int iSegment = com.shadow.okio.internal.SegmentedByteString.segment(this, i);
        while (i < i4) {
            int i5 = iSegment == 0 ? 0 : getDirectory$okio()[iSegment - 1];
            int i6 = getDirectory$okio()[iSegment] - i5;
            int i7 = getDirectory$okio()[getSegments$okio().length + iSegment];
            int iMin = Math.min(i4, i6 + i5) - i;
            int i8 = (i - i5) + i7;
            ArraysKt.b(getSegments$okio()[iSegment], i2, bArr, i8, i8 + iMin);
            i2 += iMin;
            i += iMin;
            iSegment++;
        }
    }

    @Override // com.shadow.okio.ByteString
    public ByteString digest$okio(String str) throws NoSuchAlgorithmException {
        CloseableKt.checkNotNullParameter(str, "algorithm");
        MessageDigest messageDigest = MessageDigest.getInstance(str);
        int length = getSegments$okio().length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = getDirectory$okio()[length + i];
            int i4 = getDirectory$okio()[i];
            messageDigest.update(getSegments$okio()[i], i3, i4 - i2);
            i++;
            i2 = i4;
        }
        byte[] bArrDigest = messageDigest.digest();
        CloseableKt.checkNotNull(bArrDigest);
        return new ByteString(bArrDigest);
    }

    @Override // com.shadow.okio.ByteString
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ByteString) {
            ByteString byteString = (ByteString) obj;
            if (byteString.size() == size() && rangeEquals(0, byteString, 0, size())) {
                return true;
            }
        }
        return false;
    }

    public final int[] getDirectory$okio() {
        return this.directory;
    }

    public final byte[][] getSegments$okio() {
        return this.segments;
    }

    @Override // com.shadow.okio.ByteString
    public int getSize$okio() {
        return getDirectory$okio()[getSegments$okio().length - 1];
    }

    @Override // com.shadow.okio.ByteString
    public int hashCode() {
        int hashCode$okio = getHashCode$okio();
        if (hashCode$okio != 0) {
            return hashCode$okio;
        }
        int length = getSegments$okio().length;
        int i = 0;
        int i2 = 1;
        int i3 = 0;
        while (i < length) {
            int i4 = getDirectory$okio()[length + i];
            int i5 = getDirectory$okio()[i];
            byte[] bArr = getSegments$okio()[i];
            int i6 = (i5 - i3) + i4;
            while (i4 < i6) {
                i2 = (i2 * 31) + bArr[i4];
                i4++;
            }
            i++;
            i3 = i5;
        }
        setHashCode$okio(i2);
        return i2;
    }

    @Override // com.shadow.okio.ByteString
    public String hex() {
        return toByteString().hex();
    }

    @Override // com.shadow.okio.ByteString
    public ByteString hmac$okio(String str, ByteString byteString) throws IllegalStateException, NoSuchAlgorithmException, InvalidKeyException {
        CloseableKt.checkNotNullParameter(str, "algorithm");
        CloseableKt.checkNotNullParameter(byteString, "key");
        try {
            Mac mac = Mac.getInstance(str);
            mac.init(new SecretKeySpec(byteString.toByteArray(), str));
            int length = getSegments$okio().length;
            int i = 0;
            int i2 = 0;
            while (i < length) {
                int i3 = getDirectory$okio()[length + i];
                int i4 = getDirectory$okio()[i];
                mac.update(getSegments$okio()[i], i3, i4 - i2);
                i++;
                i2 = i4;
            }
            byte[] bArrDoFinal = mac.doFinal();
            CloseableKt.checkNotNullExpressionValue(bArrDoFinal, "doFinal(...)");
            return new ByteString(bArrDoFinal);
        } catch (InvalidKeyException e) {
            throw new IllegalArgumentException(e);
        }
    }

    @Override // com.shadow.okio.ByteString
    public int indexOf(byte[] bArr, int i) {
        CloseableKt.checkNotNullParameter(bArr, "other");
        return toByteString().indexOf(bArr, i);
    }

    @Override // com.shadow.okio.ByteString
    public byte[] internalArray$okio() {
        return toByteArray();
    }

    @Override // com.shadow.okio.ByteString
    public byte internalGet$okio(int i) {
        SegmentedByteString.checkOffsetAndCount(getDirectory$okio()[getSegments$okio().length - 1], i, 1L);
        int iSegment = com.shadow.okio.internal.SegmentedByteString.segment(this, i);
        return getSegments$okio()[iSegment][(i - (iSegment == 0 ? 0 : getDirectory$okio()[iSegment - 1])) + getDirectory$okio()[getSegments$okio().length + iSegment]];
    }

    @Override // com.shadow.okio.ByteString
    public int lastIndexOf(byte[] bArr, int i) {
        CloseableKt.checkNotNullParameter(bArr, "other");
        return toByteString().lastIndexOf(bArr, i);
    }

    @Override // com.shadow.okio.ByteString
    public boolean rangeEquals(int i, ByteString byteString, int i2, int i3) {
        CloseableKt.checkNotNullParameter(byteString, "other");
        if (i < 0 || i > size() - i3) {
            return false;
        }
        int i4 = i3 + i;
        int iSegment = com.shadow.okio.internal.SegmentedByteString.segment(this, i);
        while (i < i4) {
            int i5 = iSegment == 0 ? 0 : getDirectory$okio()[iSegment - 1];
            int i6 = getDirectory$okio()[iSegment] - i5;
            int i7 = getDirectory$okio()[getSegments$okio().length + iSegment];
            int iMin = Math.min(i4, i6 + i5) - i;
            if (!byteString.rangeEquals(i2, getSegments$okio()[iSegment], (i - i5) + i7, iMin)) {
                return false;
            }
            i2 += iMin;
            i += iMin;
            iSegment++;
        }
        return true;
    }

    @Override // com.shadow.okio.ByteString
    public String string(Charset charset) {
        CloseableKt.checkNotNullParameter(charset, "charset");
        return toByteString().string(charset);
    }

    @Override // com.shadow.okio.ByteString
    public ByteString substring(int i, int i2) {
        int iResolveDefaultParameter = SegmentedByteString.resolveDefaultParameter(this, i2);
        if (i < 0) {
            throw new IllegalArgumentException(("beginIndex=" + i + " < 0").toString());
        }
        if (iResolveDefaultParameter > size()) {
            throw new IllegalArgumentException(("endIndex=" + iResolveDefaultParameter + " > length(" + size() + ')').toString());
        }
        int i3 = iResolveDefaultParameter - i;
        if (i3 < 0) {
            throw new IllegalArgumentException(("endIndex=" + iResolveDefaultParameter + " < beginIndex=" + i).toString());
        }
        if (i == 0 && iResolveDefaultParameter == size()) {
            return this;
        }
        if (i == iResolveDefaultParameter) {
            return ByteString.EMPTY;
        }
        int iSegment = com.shadow.okio.internal.SegmentedByteString.segment(this, i);
        int iSegment2 = com.shadow.okio.internal.SegmentedByteString.segment(this, iResolveDefaultParameter - 1);
        byte[][] bArr = (byte[][]) ArraysKt.e(getSegments$okio(), iSegment, iSegment2 + 1);
        int[] iArr = new int[bArr.length * 2];
        if (iSegment <= iSegment2) {
            int i4 = iSegment;
            int i5 = 0;
            while (true) {
                iArr[i5] = Math.min(getDirectory$okio()[i4] - i, i3);
                int i6 = i5 + 1;
                iArr[i5 + bArr.length] = getDirectory$okio()[getSegments$okio().length + i4];
                if (i4 == iSegment2) {
                    break;
                }
                i4++;
                i5 = i6;
            }
        }
        int i7 = iSegment != 0 ? getDirectory$okio()[iSegment - 1] : 0;
        int length = bArr.length;
        iArr[length] = (i - i7) + iArr[length];
        return new C0058SegmentedByteString(bArr, iArr);
    }

    @Override // com.shadow.okio.ByteString
    public ByteString toAsciiLowercase() {
        return toByteString().toAsciiLowercase();
    }

    @Override // com.shadow.okio.ByteString
    public ByteString toAsciiUppercase() {
        return toByteString().toAsciiUppercase();
    }

    @Override // com.shadow.okio.ByteString
    public byte[] toByteArray() {
        byte[] bArr = new byte[size()];
        int length = getSegments$okio().length;
        int i = 0;
        int i2 = 0;
        int i3 = 0;
        while (i < length) {
            int i4 = getDirectory$okio()[length + i];
            int i5 = getDirectory$okio()[i];
            int i6 = i5 - i2;
            ArraysKt.b(getSegments$okio()[i], i3, bArr, i4, i4 + i6);
            i3 += i6;
            i++;
            i2 = i5;
        }
        return bArr;
    }

    @Override // com.shadow.okio.ByteString
    public String toString() {
        return toByteString().toString();
    }

    @Override // com.shadow.okio.ByteString
    public void write(OutputStream outputStream) throws IOException {
        CloseableKt.checkNotNullParameter(outputStream, "out");
        int length = getSegments$okio().length;
        int i = 0;
        int i2 = 0;
        while (i < length) {
            int i3 = getDirectory$okio()[length + i];
            int i4 = getDirectory$okio()[i];
            outputStream.write(getSegments$okio()[i], i3, i4 - i2);
            i++;
            i2 = i4;
        }
    }

    @Override // com.shadow.okio.ByteString
    public void write$okio(Buffer buffer, int i, int i2) {
        CloseableKt.checkNotNullParameter(buffer, "buffer");
        int i3 = i + i2;
        int iSegment = com.shadow.okio.internal.SegmentedByteString.segment(this, i);
        while (i < i3) {
            int i4 = iSegment == 0 ? 0 : getDirectory$okio()[iSegment - 1];
            int i5 = getDirectory$okio()[iSegment] - i4;
            int i6 = getDirectory$okio()[getSegments$okio().length + iSegment];
            int iMin = Math.min(i3, i5 + i4) - i;
            int i7 = (i - i4) + i6;
            Segment segment = new Segment(getSegments$okio()[iSegment], i7, i7 + iMin, true, false);
            Segment segment2 = buffer.head;
            if (segment2 == null) {
                segment.prev = segment;
                segment.next = segment;
                buffer.head = segment;
            } else {
                CloseableKt.checkNotNull(segment2);
                Segment segment3 = segment2.prev;
                CloseableKt.checkNotNull(segment3);
                segment3.push(segment);
            }
            i += iMin;
            iSegment++;
        }
        buffer.setSize$okio(buffer.size() + i2);
    }

    @Override // com.shadow.okio.ByteString
    public boolean rangeEquals(int i, byte[] bArr, int i2, int i3) {
        CloseableKt.checkNotNullParameter(bArr, "other");
        if (i < 0 || i > size() - i3 || i2 < 0 || i2 > bArr.length - i3) {
            return false;
        }
        int i4 = i3 + i;
        int iSegment = com.shadow.okio.internal.SegmentedByteString.segment(this, i);
        while (i < i4) {
            int i5 = iSegment == 0 ? 0 : getDirectory$okio()[iSegment - 1];
            int i6 = getDirectory$okio()[iSegment] - i5;
            int i7 = getDirectory$okio()[getSegments$okio().length + iSegment];
            int iMin = Math.min(i4, i6 + i5) - i;
            if (!SegmentedByteString.arrayRangeEquals(getSegments$okio()[iSegment], (i - i5) + i7, bArr, i2, iMin)) {
                return false;
            }
            i2 += iMin;
            i += iMin;
            iSegment++;
        }
        return true;
    }
}
