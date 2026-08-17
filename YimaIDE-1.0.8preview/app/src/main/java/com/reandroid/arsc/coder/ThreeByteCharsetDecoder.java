package com.reandroid.arsc.coder;

import java.nio.Buffer;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.StandardCharsets;
import org.eclipse.jdt.internal.compiler.codegen.Opcodes;
import org.eclipse.jdt.internal.compiler.lookup.TypeIds;

/* JADX INFO: loaded from: /workspace/dex_all/classes10.dex */
public class ThreeByteCharsetDecoder extends CharsetDecoder {
    public static final ThreeByteCharsetDecoder INSTANCE = new ThreeByteCharsetDecoder();

    public ThreeByteCharsetDecoder() {
        super(StandardCharsets.UTF_8, 1.0f, 1.0f);
    }

    private CoderResult decodeArrayLoop(ByteBuffer byteBuffer, CharBuffer charBuffer) {
        ByteBuffer byteBuffer2;
        int i;
        CharBuffer charBuffer2;
        byte[] bArrArray = byteBuffer.array();
        int iArrayOffset = byteBuffer.arrayOffset() + byteBuffer.position();
        int iArrayOffset2 = byteBuffer.arrayOffset() + byteBuffer.limit();
        char[] cArrArray = charBuffer.array();
        int iArrayOffset3 = charBuffer.arrayOffset() + charBuffer.position();
        int iArrayOffset4 = charBuffer.arrayOffset() + charBuffer.limit();
        int i2 = iArrayOffset2 - iArrayOffset;
        int i3 = iArrayOffset4 - iArrayOffset3;
        if (i3 < i2) {
            i2 = i3;
        }
        int i4 = i2 + iArrayOffset3;
        while (iArrayOffset3 < i4) {
            byte b = bArrArray[iArrayOffset];
            if (b < 0) {
                break;
            }
            iArrayOffset++;
            cArrArray[iArrayOffset3] = (char) b;
            iArrayOffset3++;
        }
        int i5 = iArrayOffset;
        int i6 = iArrayOffset3;
        while (i5 < iArrayOffset2) {
            byte b2 = bArrArray[i5];
            if (b2 >= 0) {
                int i7 = iArrayOffset4;
                byteBuffer2 = byteBuffer;
                i = i7;
                charBuffer2 = charBuffer;
                if (i6 >= i) {
                    return xFlow(byteBuffer2, i5, iArrayOffset2, charBuffer2, i6, 1);
                }
                cArrArray[i6] = (char) b2;
                i5++;
                i6++;
            } else if ((b2 >> 5) != -2 || (b2 & Opcodes.OPC_lload_0) == 0) {
                int i8 = iArrayOffset4;
                byteBuffer2 = byteBuffer;
                i = i8;
                charBuffer2 = charBuffer;
                if ((b2 >> 4) != -2) {
                    return malformed(byteBuffer2, i5, charBuffer2, i6, 1);
                }
                int i9 = iArrayOffset2 - i5;
                if (i9 < 3 || i6 >= i) {
                    return (i9 <= 1 || !isMalformed3_2(b2, bArrArray[i5 + 1])) ? xFlow(byteBuffer2, i5, iArrayOffset2, charBuffer2, i6, 3) : malformedForLength(byteBuffer2, i5, charBuffer2, i6);
                }
                byte b3 = bArrArray[i5 + 1];
                byte b4 = bArrArray[i5 + 2];
                if (isMalformed3(b2, b3, b4)) {
                    return malformed(byteBuffer2, i5, charBuffer2, i6, 3);
                }
                cArrArray[i6] = (char) ((((b3 << 6) ^ (b2 << Opcodes.OPC_fconst_1)) ^ b4) ^ (-123008));
                i5 += 3;
                i6++;
            } else {
                if (iArrayOffset2 - i5 < 2 || i6 >= iArrayOffset4) {
                    return xFlow(byteBuffer, i5, iArrayOffset2, charBuffer, i6, 2);
                }
                byte b5 = bArrArray[i5 + 1];
                if (isNotContinuation(b5)) {
                    return malformedForLength(byteBuffer, i5, charBuffer, i6);
                }
                int i10 = i6 + 1;
                cArrArray[i6] = (char) (((b2 << 6) ^ b5) ^ 3968);
                i5 += 2;
                int i11 = iArrayOffset4;
                byteBuffer2 = byteBuffer;
                i = i11;
                i6 = i10;
                charBuffer2 = charBuffer;
            }
            ByteBuffer byteBuffer3 = byteBuffer2;
            iArrayOffset4 = i;
            byteBuffer = byteBuffer3;
            charBuffer = charBuffer2;
        }
        return xFlow(byteBuffer, i5, iArrayOffset2, charBuffer, i6, 0);
    }

    private CoderResult decodeBufferLoop(ByteBuffer byteBuffer, CharBuffer charBuffer) {
        int iPosition = byteBuffer.position();
        int iLimit = byteBuffer.limit();
        while (iPosition < iLimit) {
            byte b = byteBuffer.get();
            if (b < 0) {
                if ((b >> 5) != -2 || (b & Opcodes.OPC_lload_0) == 0) {
                    if ((b >> 4) != -2) {
                        return malformed(byteBuffer, iPosition, 1);
                    }
                    int i = iLimit - iPosition;
                    if (i < 3 || charBuffer.remaining() < 1) {
                        return (i <= 1 || !isMalformed3_2(b, byteBuffer.get())) ? xFlow(byteBuffer, iPosition, 3) : malformedForLength(byteBuffer, iPosition);
                    }
                    byte b2 = byteBuffer.get();
                    byte b3 = byteBuffer.get();
                    if (isMalformed3(b, b2, b3)) {
                        return malformed(byteBuffer, iPosition, 3);
                    }
                    charBuffer.put((char) ((((b << Opcodes.OPC_fconst_1) ^ (b2 << 6)) ^ b3) ^ (-123008)));
                    iPosition += 3;
                } else {
                    if (iLimit - iPosition < 2 || charBuffer.remaining() < 1) {
                        return xFlow(byteBuffer, iPosition, 2);
                    }
                    byte b4 = byteBuffer.get();
                    if (isNotContinuation(b4)) {
                        return malformedForLength(byteBuffer, iPosition);
                    }
                    charBuffer.put((char) (((b << 6) ^ b4) ^ 3968));
                    iPosition += 2;
                }
            } else {
                if (charBuffer.remaining() < 1) {
                    return xFlow(byteBuffer, iPosition, 1);
                }
                charBuffer.put((char) b);
                iPosition++;
            }
        }
        return xFlow(byteBuffer, iPosition, 0);
    }

    private static boolean isMalformed3(int i, int i2, int i3) {
        return ((i != -32 || (i2 & TypeIds.BitAnyNullAnnotation) != 128) && (i2 & 192) == 128 && (i3 & 192) == 128) ? false : true;
    }

    private static boolean isMalformed3_2(int i, int i2) {
        return (i == -32 && (i2 & TypeIds.BitAnyNullAnnotation) == 128) || (i2 & 192) != 128;
    }

    private static boolean isNotContinuation(int i) {
        return (i & 192) != 128;
    }

    private static CoderResult malformed(ByteBuffer byteBuffer, int i, CharBuffer charBuffer, int i2, int i3) {
        byteBuffer.position(i - byteBuffer.arrayOffset());
        CoderResult coderResultMalformedN = malformedN(byteBuffer, i3);
        updatePositions(byteBuffer, i, charBuffer, i2);
        return coderResultMalformedN;
    }

    private static CoderResult malformedForLength(ByteBuffer byteBuffer, int i, CharBuffer charBuffer, int i2) {
        updatePositions(byteBuffer, i, charBuffer, i2);
        return CoderResult.malformedForLength(1);
    }

    private static CoderResult malformedN(ByteBuffer byteBuffer, int i) {
        int i2 = 1;
        if (i == 1 || i == 2) {
            return CoderResult.malformedForLength(1);
        }
        if (i == 3) {
            byte b = byteBuffer.get();
            byte b2 = byteBuffer.get();
            if ((b != -32 || (b2 & 224) != 128) && !isNotContinuation(b2)) {
                i2 = 2;
            }
            return CoderResult.malformedForLength(i2);
        }
        if (i != 4) {
            return null;
        }
        int i3 = byteBuffer.get() & 255;
        byte b3 = byteBuffer.get();
        int i4 = b3 & 255;
        if (i3 > 244 || ((i3 == 240 && (i4 < 144 || i4 > 191)) || ((i3 == 244 && (b3 & 240) != 128) || isNotContinuation(i4)))) {
            return CoderResult.malformedForLength(1);
        }
        return isNotContinuation(byteBuffer.get()) ? CoderResult.malformedForLength(2) : CoderResult.malformedForLength(3);
    }

    private static void updatePositions(Buffer buffer, int i, Buffer buffer2, int i2) {
        buffer.position(i - buffer.arrayOffset());
        buffer2.position(i2 - buffer2.arrayOffset());
    }

    private static CoderResult xFlow(Buffer buffer, int i, int i2) {
        buffer.position(i);
        return (i2 == 0 || buffer.remaining() < i2) ? CoderResult.UNDERFLOW : CoderResult.OVERFLOW;
    }

    @Override // java.nio.charset.CharsetDecoder
    public CoderResult decodeLoop(ByteBuffer byteBuffer, CharBuffer charBuffer) {
        return (byteBuffer.hasArray() && charBuffer.hasArray()) ? decodeArrayLoop(byteBuffer, charBuffer) : decodeBufferLoop(byteBuffer, charBuffer);
    }

    private static CoderResult malformedForLength(ByteBuffer byteBuffer, int i) {
        byteBuffer.position(i);
        return CoderResult.malformedForLength(1);
    }

    private static CoderResult malformed(ByteBuffer byteBuffer, int i, int i2) {
        byteBuffer.position(i);
        CoderResult coderResultMalformedN = malformedN(byteBuffer, i2);
        byteBuffer.position(i);
        return coderResultMalformedN;
    }

    private static CoderResult xFlow(Buffer buffer, int i, int i2, Buffer buffer2, int i3, int i4) {
        updatePositions(buffer, i, buffer2, i3);
        return (i4 == 0 || i2 - i < i4) ? CoderResult.UNDERFLOW : CoderResult.OVERFLOW;
    }
}
