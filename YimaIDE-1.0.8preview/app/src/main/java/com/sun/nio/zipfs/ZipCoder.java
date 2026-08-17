package com.sun.nio.zipfs;

import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CharsetEncoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.util.Arrays;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
final class ZipCoder {
    private Charset cs;
    private final ThreadLocal<CharsetDecoder> decTL = new ThreadLocal<>();
    private final ThreadLocal<CharsetEncoder> encTL = new ThreadLocal<>();
    private boolean isutf8;
    private ZipCoder utf8;

    private ZipCoder(Charset charset) {
        this.cs = charset;
        this.isutf8 = charset.name().equals("UTF-8");
    }

    private CharsetDecoder decoder() {
        CharsetDecoder charsetDecoder = this.decTL.get();
        if (charsetDecoder != null) {
            return charsetDecoder;
        }
        CharsetDecoder charsetDecoderNewDecoder = this.cs.newDecoder();
        CodingErrorAction codingErrorAction = CodingErrorAction.REPORT;
        CharsetDecoder charsetDecoderOnUnmappableCharacter = charsetDecoderNewDecoder.onMalformedInput(codingErrorAction).onUnmappableCharacter(codingErrorAction);
        this.decTL.set(charsetDecoderOnUnmappableCharacter);
        return charsetDecoderOnUnmappableCharacter;
    }

    private CharsetEncoder encoder() {
        CharsetEncoder charsetEncoder = this.encTL.get();
        if (charsetEncoder != null) {
            return charsetEncoder;
        }
        CharsetEncoder charsetEncoderNewEncoder = this.cs.newEncoder();
        CodingErrorAction codingErrorAction = CodingErrorAction.REPORT;
        CharsetEncoder charsetEncoderOnUnmappableCharacter = charsetEncoderNewEncoder.onMalformedInput(codingErrorAction).onUnmappableCharacter(codingErrorAction);
        this.encTL.set(charsetEncoderOnUnmappableCharacter);
        return charsetEncoderOnUnmappableCharacter;
    }

    public static ZipCoder get(String str) {
        try {
            return new ZipCoder(Charset.forName(str));
        } catch (Throwable th) {
            th.printStackTrace();
            return new ZipCoder(Charset.defaultCharset());
        }
    }

    public byte[] getBytes(String str) {
        CharsetEncoder charsetEncoderReset = encoder().reset();
        char[] charArray = str.toCharArray();
        int length = (int) (charArray.length * charsetEncoderReset.maxBytesPerChar());
        byte[] bArr = new byte[length];
        if (length != 0) {
            ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
            CoderResult coderResultEncode = charsetEncoderReset.encode(CharBuffer.wrap(charArray), byteBufferWrap, true);
            if (!coderResultEncode.isUnderflow()) {
                throw new IllegalArgumentException(coderResultEncode.toString());
            }
            CoderResult coderResultFlush = charsetEncoderReset.flush(byteBufferWrap);
            if (!coderResultFlush.isUnderflow()) {
                throw new IllegalArgumentException(coderResultFlush.toString());
            }
            if (byteBufferWrap.position() != length) {
                return Arrays.copyOf(bArr, byteBufferWrap.position());
            }
        }
        return bArr;
    }

    public byte[] getBytesUTF8(String str) {
        if (this.isutf8) {
            return getBytes(str);
        }
        if (this.utf8 == null) {
            this.utf8 = new ZipCoder(Charset.forName("UTF-8"));
        }
        return this.utf8.getBytes(str);
    }

    public boolean isUTF8() {
        return this.isutf8;
    }

    public String toString(byte[] bArr, int i) {
        CharsetDecoder charsetDecoderReset = decoder().reset();
        int iMaxCharsPerByte = (int) (i * charsetDecoderReset.maxCharsPerByte());
        char[] cArr = new char[iMaxCharsPerByte];
        if (iMaxCharsPerByte == 0) {
            return new String(cArr);
        }
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr, 0, i);
        CharBuffer charBufferWrap = CharBuffer.wrap(cArr);
        CoderResult coderResultDecode = charsetDecoderReset.decode(byteBufferWrap, charBufferWrap, true);
        if (!coderResultDecode.isUnderflow()) {
            throw new IllegalArgumentException(coderResultDecode.toString());
        }
        CoderResult coderResultFlush = charsetDecoderReset.flush(charBufferWrap);
        if (coderResultFlush.isUnderflow()) {
            return new String(cArr, 0, charBufferWrap.position());
        }
        throw new IllegalArgumentException(coderResultFlush.toString());
    }

    public String toStringUTF8(byte[] bArr, int i) {
        if (this.isutf8) {
            return toString(bArr, i);
        }
        if (this.utf8 == null) {
            this.utf8 = new ZipCoder(Charset.forName("UTF-8"));
        }
        return this.utf8.toString(bArr, i);
    }

    public static ZipCoder get(Charset charset) {
        return new ZipCoder(charset);
    }

    public String toString(byte[] bArr) {
        return toString(bArr, bArr.length);
    }
}
