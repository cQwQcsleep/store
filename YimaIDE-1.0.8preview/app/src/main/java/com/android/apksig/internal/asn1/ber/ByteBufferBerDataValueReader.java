package com.android.apksig.internal.asn1.ber;

import defpackage.q31;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: /workspace/dex_all/classes4.dex */
public class ByteBufferBerDataValueReader implements BerDataValueReader {
    private final ByteBuffer mBuf;

    public ByteBufferBerDataValueReader(ByteBuffer byteBuffer) {
        if (byteBuffer != null) {
            this.mBuf = byteBuffer;
        } else {
            x0e.a("buf == null");
            throw null;
        }
    }

    private int readHighTagNumber() throws BerDataValueFormatException {
        int i = 0;
        while (this.mBuf.hasRemaining()) {
            byte b = this.mBuf.get();
            if (i > 16777215) {
                throw new BerDataValueFormatException("Tag number too large");
            }
            i = (i << 7) | (b & 127);
            if ((b & 128) == 0) {
                return i;
            }
        }
        throw new BerDataValueFormatException("Truncated tag number");
    }

    private int readLongFormLength(int i) throws BerDataValueFormatException {
        int i2 = i & 127;
        if (i2 > 4) {
            q31.a("Length too large: ", i2, " bytes");
            return 0;
        }
        int i3 = 0;
        for (int i4 = 0; i4 < i2; i4++) {
            if (!this.mBuf.hasRemaining()) {
                throw new BerDataValueFormatException("Truncated length");
            }
            byte b = this.mBuf.get();
            if (i3 > 8388607) {
                throw new BerDataValueFormatException("Length too large");
            }
            i3 = (i3 << 8) | (b & 255);
        }
        return i3;
    }

    private int readShortFormLength(int i) {
        return i & 127;
    }

    private int readTagNumber(byte b) throws BerDataValueFormatException {
        int tagNumber = BerEncoding.getTagNumber(b);
        return tagNumber == 31 ? readHighTagNumber() : tagNumber;
    }

    private int skipConstructedIndefiniteLengthContents() throws BerDataValueFormatException {
        int iPosition = this.mBuf.position();
        while (this.mBuf.hasRemaining()) {
            if (this.mBuf.remaining() > 1) {
                ByteBuffer byteBuffer = this.mBuf;
                if (byteBuffer.getShort(byteBuffer.position()) == 0) {
                    int iPosition2 = this.mBuf.position() - iPosition;
                    ByteBuffer byteBuffer2 = this.mBuf;
                    byteBuffer2.position(byteBuffer2.position() + 2);
                    return iPosition2;
                }
            }
            readDataValue();
        }
        throw new BerDataValueFormatException("Truncated indefinite-length contents: " + (this.mBuf.position() - iPosition) + " bytes read");
    }

    private void skipDefiniteLengthContents(int i) throws BerDataValueFormatException {
        if (this.mBuf.remaining() >= i) {
            ByteBuffer byteBuffer = this.mBuf;
            byteBuffer.position(byteBuffer.position() + i);
            return;
        }
        throw new BerDataValueFormatException("Truncated contents. Need: " + i + " bytes, available: " + this.mBuf.remaining());
    }

    private int skipPrimitiveIndefiniteLengthContents() throws BerDataValueFormatException {
        int i = 0;
        boolean z = false;
        while (this.mBuf.hasRemaining()) {
            byte b = this.mBuf.get();
            int i2 = i + 1;
            if (i2 < 0) {
                throw new BerDataValueFormatException("Indefinite-length contents too long");
            }
            if (b != 0) {
                z = false;
            } else {
                if (z) {
                    return i - 1;
                }
                z = true;
            }
            i = i2;
        }
        q31.a("Truncated indefinite-length contents: ", i, " bytes read");
        return 0;
    }

    @Override // com.android.apksig.internal.asn1.ber.BerDataValueReader
    public BerDataValue readDataValue() throws BerDataValueFormatException {
        int iPosition;
        int iSkipConstructedIndefiniteLengthContents;
        int iPosition2 = this.mBuf.position();
        if (!this.mBuf.hasRemaining()) {
            return null;
        }
        byte b = this.mBuf.get();
        int tagNumber = readTagNumber(b);
        boolean zIsConstructed = BerEncoding.isConstructed(b);
        if (!this.mBuf.hasRemaining()) {
            throw new BerDataValueFormatException("Missing length");
        }
        byte b2 = this.mBuf.get();
        int i = b2 & 255;
        if ((b2 & 128) == 0) {
            iSkipConstructedIndefiniteLengthContents = readShortFormLength(i);
            iPosition = this.mBuf.position() - iPosition2;
            skipDefiniteLengthContents(iSkipConstructedIndefiniteLengthContents);
        } else if (i != 128) {
            iSkipConstructedIndefiniteLengthContents = readLongFormLength(i);
            iPosition = this.mBuf.position() - iPosition2;
            skipDefiniteLengthContents(iSkipConstructedIndefiniteLengthContents);
        } else {
            iPosition = this.mBuf.position() - iPosition2;
            iSkipConstructedIndefiniteLengthContents = zIsConstructed ? skipConstructedIndefiniteLengthContents() : skipPrimitiveIndefiniteLengthContents();
        }
        int iPosition3 = this.mBuf.position();
        this.mBuf.position(iPosition2);
        int iLimit = this.mBuf.limit();
        this.mBuf.limit(iPosition3);
        ByteBuffer byteBufferSlice = this.mBuf.slice();
        ByteBuffer byteBuffer = this.mBuf;
        byteBuffer.position(byteBuffer.limit());
        this.mBuf.limit(iLimit);
        byteBufferSlice.position(iPosition);
        byteBufferSlice.limit(iPosition + iSkipConstructedIndefiniteLengthContents);
        ByteBuffer byteBufferSlice2 = byteBufferSlice.slice();
        byteBufferSlice.clear();
        return new BerDataValue(byteBufferSlice, byteBufferSlice2, BerEncoding.getTagClass(b), zIsConstructed, tagNumber);
    }
}
