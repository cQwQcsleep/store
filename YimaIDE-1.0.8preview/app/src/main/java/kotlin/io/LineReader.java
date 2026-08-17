package kotlin.io;

import java.io.InputStream;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharacterCodingException;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.kotlin.utils.PathUtil;
import org.joni.constants.internal.OPCode;

/* JADX INFO: loaded from: /workspace/dex_all/classes3.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\u0019\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0002\b\u0003\bÀ\u0002\u0018\u00002\u00020\u0001B\t\bB¢\u0006\u0004\b\u0002\u0010\u0003J\u001c\u0010\u0015\u001a\u0004\u0018\u00010\u00162\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0086\u0080\u0004J\u0012\u0010\u001b\u001a\u00020\u00052\u0006\u0010\u001c\u001a\u00020\tH\u0082\u0080\u0004J\n\u0010\u001d\u001a\u00020\u0005H\u0082\u0080\u0004J\u001a\u0010\u001e\u001a\u00020\u00052\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u0005H\u0082\u0080\u0004J\u0012\u0010!\u001a\u00020\"2\u0006\u0010\u0019\u001a\u00020\u001aH\u0082\u0080\u0004J\n\u0010#\u001a\u00020\"H\u0082\u0080\u0004J\n\u0010$\u001a\u00020\"H\u0082\u0080\u0004R\u000f\u0010\u0004\u001a\u00020\u0005X\u0082Ô\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0006\u001a\u00020\u0007X\u0082®\b¢\u0006\u0002\n\u0000R\u000f\u0010\b\u001a\u00020\tX\u0082\u008e\b¢\u0006\u0002\n\u0000R\u000f\u0010\n\u001a\u00020\u000bX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\f\u001a\u00020\rX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u000e\u001a\u00020\u000fX\u0082\u0084\b¢\u0006\u0002\n\u0000R\u000f\u0010\u0010\u001a\u00020\u0011X\u0082\u0084\b¢\u0006\u0002\n\u0000R\u0013\u0010\u0012\u001a\u00060\u0013j\u0002`\u0014X\u0082\u0084\b¢\u0006\u0002\n\u0000¨\u0006%"}, d2 = {"Lkotlin/io/LineReader;", "", "<init>", "()V", "BUFFER_SIZE", "", "decoder", "Ljava/nio/charset/CharsetDecoder;", "directEOL", "", "bytes", "", "chars", "", "byteBuf", "Ljava/nio/ByteBuffer;", "charBuf", "Ljava/nio/CharBuffer;", "sb", "Ljava/lang/StringBuilder;", "Lkotlin/text/StringBuilder;", "readLine", "", "inputStream", "Ljava/io/InputStream;", "charset", "Ljava/nio/charset/Charset;", "decode", "endOfInput", "compactBytes", "decodeEndOfInput", "nBytes", "nChars", "updateCharset", "", "resetAll", "trimStringBuilder", PathUtil.KOTLIN_JAVA_STDLIB_NAME}, k = 1, mv = {2, 4, 0}, xi = OPCode.BACKREFN)
public final class LineReader {
    private static final int BUFFER_SIZE = 32;
    public static final LineReader INSTANCE = new LineReader();
    private static final ByteBuffer byteBuf;
    private static final byte[] bytes;
    private static final CharBuffer charBuf;
    private static final char[] chars;
    private static CharsetDecoder decoder;
    private static boolean directEOL;
    private static final StringBuilder sb;

    static {
        byte[] bArr = new byte[32];
        bytes = bArr;
        char[] cArr = new char[32];
        chars = cArr;
        ByteBuffer byteBufferWrap = ByteBuffer.wrap(bArr);
        byteBufferWrap.getClass();
        byteBuf = byteBufferWrap;
        CharBuffer charBufferWrap = CharBuffer.wrap(cArr);
        charBufferWrap.getClass();
        charBuf = charBufferWrap;
        sb = new StringBuilder();
    }

    private LineReader() {
    }

    private final int compactBytes() {
        ByteBuffer byteBuffer = byteBuf;
        byteBuffer.compact();
        int iPosition = byteBuffer.position();
        byteBuffer.position(0);
        return iPosition;
    }

    private final int decode(boolean endOfInput) throws CharacterCodingException {
        while (true) {
            CharsetDecoder charsetDecoder = decoder;
            if (charsetDecoder == null) {
                Intrinsics.throwUninitializedPropertyAccessException("decoder");
                charsetDecoder = null;
            }
            ByteBuffer byteBuffer = byteBuf;
            CharBuffer charBuffer = charBuf;
            CoderResult coderResultDecode = charsetDecoder.decode(byteBuffer, charBuffer, endOfInput);
            coderResultDecode.getClass();
            if (coderResultDecode.isError()) {
                resetAll();
                coderResultDecode.throwException();
            }
            int iPosition = charBuffer.position();
            if (!coderResultDecode.isOverflow()) {
                return iPosition;
            }
            StringBuilder sb2 = sb;
            char[] cArr = chars;
            int i = iPosition - 1;
            sb2.append(cArr, 0, i);
            charBuffer.position(0);
            charBuffer.limit(32);
            charBuffer.put(cArr[i]);
        }
    }

    private final int decodeEndOfInput(int nBytes, int nChars) throws CharacterCodingException {
        ByteBuffer byteBuffer = byteBuf;
        byteBuffer.limit(nBytes);
        charBuf.position(nChars);
        int iDecode = decode(true);
        CharsetDecoder charsetDecoder = decoder;
        if (charsetDecoder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
            charsetDecoder = null;
        }
        charsetDecoder.reset();
        byteBuffer.position(0);
        return iDecode;
    }

    private final void resetAll() {
        CharsetDecoder charsetDecoder = decoder;
        if (charsetDecoder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
            charsetDecoder = null;
        }
        charsetDecoder.reset();
        byteBuf.position(0);
        sb.setLength(0);
    }

    private final void trimStringBuilder() {
        StringBuilder sb2 = sb;
        sb2.setLength(32);
        sb2.trimToSize();
    }

    private final void updateCharset(Charset charset) {
        CharsetDecoder charsetDecoderNewDecoder = charset.newDecoder();
        charsetDecoderNewDecoder.getClass();
        decoder = charsetDecoderNewDecoder;
        ByteBuffer byteBuffer = byteBuf;
        byteBuffer.clear();
        CharBuffer charBuffer = charBuf;
        charBuffer.clear();
        byteBuffer.put((byte) 10);
        byteBuffer.flip();
        CharsetDecoder charsetDecoder = decoder;
        if (charsetDecoder == null) {
            Intrinsics.throwUninitializedPropertyAccessException("decoder");
            charsetDecoder = null;
        }
        boolean z = false;
        charsetDecoder.decode(byteBuffer, charBuffer, false);
        if (charBuffer.position() == 1 && charBuffer.get(0) == '\n') {
            z = true;
        }
        directEOL = z;
        resetAll();
    }

    /* JADX WARN: Code duplicated, block: B:11:0x0022 A[Catch: all -> 0x0015, TryCatch #0 {all -> 0x0015, blocks: (B:3:0x0001, B:6:0x000e, B:9:0x0018, B:13:0x0028, B:15:0x0033, B:21:0x0041, B:35:0x0076, B:37:0x007e, B:39:0x0082, B:41:0x008a, B:43:0x008e, B:45:0x0096, B:48:0x009f, B:50:0x00ae, B:51:0x00b1, B:22:0x0046, B:25:0x0051, B:29:0x0058, B:31:0x0068, B:33:0x0070, B:54:0x00b6, B:11:0x0022), top: B:57:0x0001 }] */
    public final synchronized String readLine(InputStream inputStream, Charset charset) {
        int iDecodeEndOfInput;
        try {
            inputStream.getClass();
            charset.getClass();
            CharsetDecoder charsetDecoder = decoder;
            if (charsetDecoder == null) {
                updateCharset(charset);
            } else {
                if (charsetDecoder == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("decoder");
                    charsetDecoder = null;
                }
                if (!Intrinsics.areEqual(charsetDecoder.charset(), charset)) {
                    updateCharset(charset);
                }
            }
            int iCompactBytes = 0;
            int iDecode = 0;
            while (true) {
                int i = inputStream.read();
                if (i == -1) {
                    if (sb.length() != 0 || iCompactBytes != 0 || iDecode != 0) {
                        iDecodeEndOfInput = decodeEndOfInput(iCompactBytes, iDecode);
                        break;
                    }
                    return null;
                }
                int i2 = iCompactBytes + 1;
                bytes[iCompactBytes] = (byte) i;
                if (i == 10 || i2 == 32 || !directEOL) {
                    ByteBuffer byteBuffer = byteBuf;
                    byteBuffer.limit(i2);
                    charBuf.position(iDecode);
                    iDecode = decode(false);
                    if (iDecode > 0 && chars[iDecode - 1] == '\n') {
                        byteBuffer.position(0);
                        iDecodeEndOfInput = iDecode;
                        break;
                    }
                    iCompactBytes = compactBytes();
                } else {
                    iCompactBytes = i2;
                }
            }
            if (iDecodeEndOfInput > 0) {
                char[] cArr = chars;
                if (cArr[iDecodeEndOfInput - 1] == '\n') {
                    int i3 = iDecodeEndOfInput - 1;
                    iDecodeEndOfInput = (i3 <= 0 || cArr[iDecodeEndOfInput + (-2)] != '\r') ? i3 : iDecodeEndOfInput - 2;
                }
            }
            StringBuilder sb2 = sb;
            if (sb2.length() == 0) {
                return new String(chars, 0, iDecodeEndOfInput);
            }
            sb2.append(chars, 0, iDecodeEndOfInput);
            String string = sb2.toString();
            if (sb2.length() > 32) {
                trimStringBuilder();
            }
            sb2.setLength(0);
            return string;
        } catch (Throwable th) {
            throw th;
        }
    }
}
