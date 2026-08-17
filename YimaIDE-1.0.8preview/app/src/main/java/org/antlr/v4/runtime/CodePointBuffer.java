package org.antlr.v4.runtime;

import defpackage.c41;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.IntBuffer;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class CodePointBuffer {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private final ByteBuffer byteBuffer;
    private final CharBuffer charBuffer;
    private final IntBuffer intBuffer;
    private final Type type;

    /* JADX INFO: renamed from: org.antlr.v4.runtime.CodePointBuffer$1, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$org$antlr$v4$runtime$CodePointBuffer$Type;

        static {
            int[] iArr = new int[Type.values().length];
            $SwitchMap$org$antlr$v4$runtime$CodePointBuffer$Type = iArr;
            try {
                iArr[Type.BYTE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$org$antlr$v4$runtime$CodePointBuffer$Type[Type.CHAR.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$org$antlr$v4$runtime$CodePointBuffer$Type[Type.INT.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    public enum Type {
        BYTE,
        CHAR,
        INT
    }

    private CodePointBuffer(Type type, ByteBuffer byteBuffer, CharBuffer charBuffer, IntBuffer intBuffer) {
        this.type = type;
        this.byteBuffer = byteBuffer;
        this.charBuffer = charBuffer;
        this.intBuffer = intBuffer;
    }

    public static Builder builder(int i) {
        return new Builder(i, null);
    }

    public static CodePointBuffer withBytes(ByteBuffer byteBuffer) {
        return new CodePointBuffer(Type.BYTE, byteBuffer, null, null);
    }

    public static CodePointBuffer withChars(CharBuffer charBuffer) {
        return new CodePointBuffer(Type.CHAR, null, charBuffer, null);
    }

    public static CodePointBuffer withInts(IntBuffer intBuffer) {
        return new CodePointBuffer(Type.INT, null, null, intBuffer);
    }

    public int arrayOffset() {
        int i = AnonymousClass1.$SwitchMap$org$antlr$v4$runtime$CodePointBuffer$Type[this.type.ordinal()];
        if (i == 1) {
            return this.byteBuffer.arrayOffset();
        }
        if (i == 2) {
            return this.charBuffer.arrayOffset();
        }
        if (i == 3) {
            return this.intBuffer.arrayOffset();
        }
        c41.a("Not reached");
        return 0;
    }

    public byte[] byteArray() {
        return this.byteBuffer.array();
    }

    public char[] charArray() {
        return this.charBuffer.array();
    }

    public int get(int i) {
        int i2 = AnonymousClass1.$SwitchMap$org$antlr$v4$runtime$CodePointBuffer$Type[this.type.ordinal()];
        if (i2 == 1) {
            return this.byteBuffer.get(i);
        }
        if (i2 == 2) {
            return this.charBuffer.get(i);
        }
        if (i2 == 3) {
            return this.intBuffer.get(i);
        }
        c41.a("Not reached");
        return 0;
    }

    public Type getType() {
        return this.type;
    }

    public int[] intArray() {
        return this.intBuffer.array();
    }

    public int position() {
        int i = AnonymousClass1.$SwitchMap$org$antlr$v4$runtime$CodePointBuffer$Type[this.type.ordinal()];
        if (i == 1) {
            return this.byteBuffer.position();
        }
        if (i == 2) {
            return this.charBuffer.position();
        }
        if (i == 3) {
            return this.intBuffer.position();
        }
        c41.a("Not reached");
        return 0;
    }

    public int remaining() {
        int i = AnonymousClass1.$SwitchMap$org$antlr$v4$runtime$CodePointBuffer$Type[this.type.ordinal()];
        if (i == 1) {
            return this.byteBuffer.remaining();
        }
        if (i == 2) {
            return this.charBuffer.remaining();
        }
        if (i == 3) {
            return this.intBuffer.remaining();
        }
        c41.a("Not reached");
        return 0;
    }

    public /* synthetic */ CodePointBuffer(Type type, ByteBuffer byteBuffer, CharBuffer charBuffer, IntBuffer intBuffer, AnonymousClass1 anonymousClass1) {
        this(type, byteBuffer, charBuffer, intBuffer);
    }

    public static class Builder {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private ByteBuffer byteBuffer;
        private CharBuffer charBuffer;
        private IntBuffer intBuffer;
        private int prevHighSurrogate;
        private Type type;

        private Builder(int i) {
            this.type = Type.BYTE;
            this.byteBuffer = ByteBuffer.allocate(i);
            this.charBuffer = null;
            this.intBuffer = null;
            this.prevHighSurrogate = -1;
        }

        private void appendArray(CharBuffer charBuffer) {
            int i = AnonymousClass1.$SwitchMap$org$antlr$v4$runtime$CodePointBuffer$Type[this.type.ordinal()];
            if (i == 1) {
                appendArrayByte(charBuffer);
            } else if (i == 2) {
                appendArrayChar(charBuffer);
            } else {
                if (i != 3) {
                    return;
                }
                appendArrayInt(charBuffer);
            }
        }

        private void appendArrayByte(CharBuffer charBuffer) {
            char[] cArrArray = charBuffer.array();
            int iArrayOffset = charBuffer.arrayOffset() + charBuffer.position();
            int iArrayOffset2 = charBuffer.arrayOffset() + charBuffer.limit();
            byte[] bArrArray = this.byteBuffer.array();
            int iArrayOffset3 = this.byteBuffer.arrayOffset() + this.byteBuffer.position();
            while (iArrayOffset < iArrayOffset2) {
                char c = cArrArray[iArrayOffset];
                if (c > 255) {
                    charBuffer.position(iArrayOffset - charBuffer.arrayOffset());
                    ByteBuffer byteBuffer = this.byteBuffer;
                    byteBuffer.position(iArrayOffset3 - byteBuffer.arrayOffset());
                    if (Character.isHighSurrogate(c)) {
                        byteToIntBuffer(charBuffer.remaining());
                        appendArrayInt(charBuffer);
                        return;
                    } else {
                        byteToCharBuffer(charBuffer.remaining());
                        appendArrayChar(charBuffer);
                        return;
                    }
                }
                bArrArray[iArrayOffset3] = (byte) (c & 255);
                iArrayOffset++;
                iArrayOffset3++;
            }
            charBuffer.position(iArrayOffset - charBuffer.arrayOffset());
            ByteBuffer byteBuffer2 = this.byteBuffer;
            byteBuffer2.position(iArrayOffset3 - byteBuffer2.arrayOffset());
        }

        private void appendArrayChar(CharBuffer charBuffer) {
            char[] cArrArray = charBuffer.array();
            int iArrayOffset = charBuffer.arrayOffset() + charBuffer.position();
            int iArrayOffset2 = charBuffer.arrayOffset() + charBuffer.limit();
            char[] cArrArray2 = this.charBuffer.array();
            int iArrayOffset3 = this.charBuffer.arrayOffset() + this.charBuffer.position();
            while (iArrayOffset < iArrayOffset2) {
                char c = cArrArray[iArrayOffset];
                if (Character.isHighSurrogate(c)) {
                    charBuffer.position(iArrayOffset - charBuffer.arrayOffset());
                    CharBuffer charBuffer2 = this.charBuffer;
                    charBuffer2.position(iArrayOffset3 - charBuffer2.arrayOffset());
                    charToIntBuffer(charBuffer.remaining());
                    appendArrayInt(charBuffer);
                    return;
                }
                cArrArray2[iArrayOffset3] = c;
                iArrayOffset++;
                iArrayOffset3++;
            }
            charBuffer.position(iArrayOffset - charBuffer.arrayOffset());
            CharBuffer charBuffer3 = this.charBuffer;
            charBuffer3.position(iArrayOffset3 - charBuffer3.arrayOffset());
        }

        private void appendArrayInt(CharBuffer charBuffer) {
            char[] cArrArray = charBuffer.array();
            int iArrayOffset = charBuffer.arrayOffset() + charBuffer.position();
            int iArrayOffset2 = charBuffer.arrayOffset() + charBuffer.limit();
            int[] iArrArray = this.intBuffer.array();
            int iArrayOffset3 = this.intBuffer.arrayOffset() + this.intBuffer.position();
            while (iArrayOffset < iArrayOffset2) {
                char c = cArrArray[iArrayOffset];
                iArrayOffset++;
                if (this.prevHighSurrogate != -1) {
                    boolean zIsLowSurrogate = Character.isLowSurrogate(c);
                    int i = this.prevHighSurrogate;
                    if (zIsLowSurrogate) {
                        iArrArray[iArrayOffset3] = Character.toCodePoint((char) i, c);
                        iArrayOffset3++;
                        this.prevHighSurrogate = -1;
                    } else {
                        iArrArray[iArrayOffset3] = i;
                        int i2 = iArrayOffset3 + 1;
                        if (Character.isHighSurrogate(c)) {
                            this.prevHighSurrogate = c & 65535;
                            iArrayOffset3 = i2;
                        } else {
                            iArrArray[i2] = 65535 & c;
                            iArrayOffset3 += 2;
                            this.prevHighSurrogate = -1;
                        }
                    }
                } else if (Character.isHighSurrogate(c)) {
                    this.prevHighSurrogate = c & 65535;
                } else {
                    iArrArray[iArrayOffset3] = c & 65535;
                    iArrayOffset3++;
                }
            }
            int i3 = this.prevHighSurrogate;
            if (i3 != -1) {
                iArrArray[iArrayOffset3] = i3 & 65535;
                iArrayOffset3++;
            }
            charBuffer.position(iArrayOffset - charBuffer.arrayOffset());
            IntBuffer intBuffer = this.intBuffer;
            intBuffer.position(iArrayOffset3 - intBuffer.arrayOffset());
        }

        private void byteToCharBuffer(int i) {
            this.byteBuffer.flip();
            CharBuffer charBufferAllocate = CharBuffer.allocate(Math.max(this.byteBuffer.remaining() + i, this.byteBuffer.capacity() / 2));
            while (this.byteBuffer.hasRemaining()) {
                charBufferAllocate.put((char) (this.byteBuffer.get() & 255));
            }
            this.type = Type.CHAR;
            this.byteBuffer = null;
            this.charBuffer = charBufferAllocate;
        }

        private void byteToIntBuffer(int i) {
            this.byteBuffer.flip();
            IntBuffer intBufferAllocate = IntBuffer.allocate(Math.max(this.byteBuffer.remaining() + i, this.byteBuffer.capacity() / 4));
            while (this.byteBuffer.hasRemaining()) {
                intBufferAllocate.put(this.byteBuffer.get() & 255);
            }
            this.type = Type.INT;
            this.byteBuffer = null;
            this.intBuffer = intBufferAllocate;
        }

        private void charToIntBuffer(int i) {
            this.charBuffer.flip();
            IntBuffer intBufferAllocate = IntBuffer.allocate(Math.max(this.charBuffer.remaining() + i, this.charBuffer.capacity() / 2));
            while (this.charBuffer.hasRemaining()) {
                intBufferAllocate.put(this.charBuffer.get() & 65535);
            }
            this.type = Type.INT;
            this.charBuffer = null;
            this.intBuffer = intBufferAllocate;
        }

        private static int roundUpToNextPowerOfTwo(int i) {
            return (int) Math.pow(2.0d, 32 - Integer.numberOfLeadingZeros(i - 1));
        }

        public void append(CharBuffer charBuffer) {
            ensureRemaining(charBuffer.remaining());
            if (charBuffer.hasArray()) {
                appendArray(charBuffer);
            } else {
                c41.a("TODO");
            }
        }

        public CodePointBuffer build() {
            int i = AnonymousClass1.$SwitchMap$org$antlr$v4$runtime$CodePointBuffer$Type[this.type.ordinal()];
            if (i == 1) {
                this.byteBuffer.flip();
            } else if (i == 2) {
                this.charBuffer.flip();
            } else if (i == 3) {
                this.intBuffer.flip();
            }
            return new CodePointBuffer(this.type, this.byteBuffer, this.charBuffer, this.intBuffer, null);
        }

        public void ensureRemaining(int i) {
            int i2 = AnonymousClass1.$SwitchMap$org$antlr$v4$runtime$CodePointBuffer$Type[this.type.ordinal()];
            if (i2 == 1) {
                if (this.byteBuffer.remaining() < i) {
                    ByteBuffer byteBufferAllocate = ByteBuffer.allocate(roundUpToNextPowerOfTwo(this.byteBuffer.capacity() + i));
                    this.byteBuffer.flip();
                    byteBufferAllocate.put(this.byteBuffer);
                    this.byteBuffer = byteBufferAllocate;
                    return;
                }
                return;
            }
            if (i2 == 2) {
                if (this.charBuffer.remaining() < i) {
                    CharBuffer charBufferAllocate = CharBuffer.allocate(roundUpToNextPowerOfTwo(this.charBuffer.capacity() + i));
                    this.charBuffer.flip();
                    charBufferAllocate.put(this.charBuffer);
                    this.charBuffer = charBufferAllocate;
                    return;
                }
                return;
            }
            if (i2 == 3 && this.intBuffer.remaining() < i) {
                IntBuffer intBufferAllocate = IntBuffer.allocate(roundUpToNextPowerOfTwo(this.intBuffer.capacity() + i));
                this.intBuffer.flip();
                intBufferAllocate.put(this.intBuffer);
                this.intBuffer = intBufferAllocate;
            }
        }

        public ByteBuffer getByteBuffer() {
            return this.byteBuffer;
        }

        public CharBuffer getCharBuffer() {
            return this.charBuffer;
        }

        public IntBuffer getIntBuffer() {
            return this.intBuffer;
        }

        public Type getType() {
            return this.type;
        }

        public /* synthetic */ Builder(int i, AnonymousClass1 anonymousClass1) {
            this(i);
        }
    }

    public void position(int i) {
        int i2 = AnonymousClass1.$SwitchMap$org$antlr$v4$runtime$CodePointBuffer$Type[this.type.ordinal()];
        if (i2 == 1) {
            this.byteBuffer.position(i);
        } else if (i2 == 2) {
            this.charBuffer.position(i);
        } else {
            if (i2 != 3) {
                return;
            }
            this.intBuffer.position(i);
        }
    }
}
