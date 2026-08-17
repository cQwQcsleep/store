package org.jline.utils;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.MalformedInputException;
import java.nio.charset.UnmappableCharacterException;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class InputStreamReader extends Reader {
    ByteBuffer bytes;
    CharsetDecoder decoder;
    private boolean endOfInput;
    private InputStream in;
    char pending;

    public InputStreamReader(InputStream inputStream, Charset charset) {
        super(inputStream);
        this.endOfInput = false;
        this.bytes = ByteBuffer.allocate(4);
        this.pending = (char) 65535;
        this.in = inputStream;
        CharsetDecoder charsetDecoderNewDecoder = charset.newDecoder();
        CodingErrorAction codingErrorAction = CodingErrorAction.REPLACE;
        this.decoder = charsetDecoderNewDecoder.onMalformedInput(codingErrorAction).onUnmappableCharacter(codingErrorAction);
        this.bytes.limit(0);
    }

    private boolean isOpen() {
        return this.in != null;
    }

    @Override // java.io.Reader, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
        synchronized (((Reader) this).lock) {
            try {
                this.decoder = null;
                InputStream inputStream = this.in;
                if (inputStream != null) {
                    inputStream.close();
                    this.in = null;
                }
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    /* JADX WARN: Code duplicated, block: B:34:0x0078 A[Catch: all -> 0x0016, TryCatch #0 {all -> 0x0016, blocks: (B:4:0x0003, B:7:0x000b, B:12:0x0014, B:16:0x0019, B:17:0x0027, B:21:0x0030, B:23:0x0038, B:32:0x006a, B:34:0x0078, B:36:0x0086, B:26:0x003f, B:28:0x005a, B:31:0x0060, B:38:0x009b, B:40:0x009f, B:42:0x00a3, B:43:0x00b5, B:45:0x00bb, B:47:0x00c1, B:51:0x00cf, B:50:0x00c9, B:53:0x00d1, B:54:0x00da, B:55:0x00db, B:56:0x00e4, B:57:0x00e5, B:58:0x00ea, B:59:0x00eb, B:60:0x00f2), top: B:64:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:36:0x0086 A[Catch: all -> 0x0016, TryCatch #0 {all -> 0x0016, blocks: (B:4:0x0003, B:7:0x000b, B:12:0x0014, B:16:0x0019, B:17:0x0027, B:21:0x0030, B:23:0x0038, B:32:0x006a, B:34:0x0078, B:36:0x0086, B:26:0x003f, B:28:0x005a, B:31:0x0060, B:38:0x009b, B:40:0x009f, B:42:0x00a3, B:43:0x00b5, B:45:0x00bb, B:47:0x00c1, B:51:0x00cf, B:50:0x00c9, B:53:0x00d1, B:54:0x00da, B:55:0x00db, B:56:0x00e4, B:57:0x00e5, B:58:0x00ea, B:59:0x00eb, B:60:0x00f2), top: B:64:0x0003 }] */
    /* JADX WARN: Code duplicated, block: B:67:0x009b A[EDGE_INSN: B:67:0x009b->B:38:0x009b BREAK  A[LOOP:0: B:17:0x0027->B:37:0x0099], SYNTHETIC] */
    /* JADX WARN: Code duplicated, block: B:73:0x0099 A[SYNTHETIC] */
    @Override // java.io.Reader
    public int read(char[] cArr, int i, int i2) throws IOException {
        int iPosition;
        synchronized (((Reader) this).lock) {
            try {
                if (!isOpen()) {
                    throw new IOException("InputStreamReader is closed.");
                }
                if (i < 0 || i > cArr.length - i2 || i2 < 0) {
                    throw new IndexOutOfBoundsException();
                }
                if (i2 == 0) {
                    return 0;
                }
                CharBuffer charBufferWrap = CharBuffer.wrap(cArr, i, i2);
                CoderResult coderResultDecode = CoderResult.UNDERFLOW;
                boolean z = !this.bytes.hasRemaining();
                while (true) {
                    iPosition = -1;
                    if (charBufferWrap.position() == i) {
                        if (!z) {
                            coderResultDecode = this.decoder.decode(this.bytes, charBufferWrap, false);
                            if (coderResultDecode.isUnderflow()) {
                                break;
                                break;
                            }
                            if (this.bytes.limit() == this.bytes.capacity()) {
                                this.bytes.compact();
                                ByteBuffer byteBuffer = this.bytes;
                                byteBuffer.limit(byteBuffer.position());
                                this.bytes.position(0);
                            }
                            z = true;
                        } else {
                            try {
                                if (this.in.available() == 0 && charBufferWrap.position() > i) {
                                    break;
                                }
                                int i3 = this.in.read(this.bytes.array(), this.bytes.arrayOffset() + this.bytes.limit(), 1);
                                if (i3 != -1) {
                                    if (i3 != 0) {
                                        ByteBuffer byteBuffer2 = this.bytes;
                                        byteBuffer2.limit(byteBuffer2.limit() + i3);
                                        coderResultDecode = this.decoder.decode(this.bytes, charBufferWrap, false);
                                        if (coderResultDecode.isUnderflow()) {
                                            break;
                                        }
                                        if (this.bytes.limit() == this.bytes.capacity()) {
                                            this.bytes.compact();
                                            ByteBuffer byteBuffer3 = this.bytes;
                                            byteBuffer3.limit(byteBuffer3.position());
                                            this.bytes.position(0);
                                        }
                                        z = true;
                                    } else {
                                        break;
                                    }
                                } else {
                                    this.endOfInput = true;
                                    break;
                                }
                            } catch (IOException unused) {
                            }
                        }
                    } else {
                        break;
                    }
                }
                if (coderResultDecode == CoderResult.UNDERFLOW && this.endOfInput) {
                    coderResultDecode = this.decoder.decode(this.bytes, charBufferWrap, true);
                    this.decoder.flush(charBufferWrap);
                    this.decoder.reset();
                }
                if (coderResultDecode.isMalformed()) {
                    throw new MalformedInputException(coderResultDecode.length());
                }
                if (coderResultDecode.isUnmappable()) {
                    throw new UnmappableCharacterException(coderResultDecode.length());
                }
                if (charBufferWrap.position() - i != 0) {
                    iPosition = charBufferWrap.position() - i;
                }
                return iPosition;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    @Override // java.io.Reader
    public boolean ready() throws IOException {
        boolean z;
        synchronized (((Reader) this).lock) {
            if (this.in == null) {
                throw new IOException("InputStreamReader is closed.");
            }
            try {
                z = this.bytes.hasRemaining() || this.in.available() > 0;
            } catch (IOException unused) {
                return false;
            }
        }
        return z;
    }

    @Override // java.io.Reader
    public int read() throws IOException {
        synchronized (((Reader) this).lock) {
            try {
                if (isOpen()) {
                    char c = this.pending;
                    if (c != 65535) {
                        this.pending = (char) 65535;
                        return c;
                    }
                    char[] cArr = new char[2];
                    int i = read(cArr, 0, 2);
                    if (i == 2) {
                        this.pending = cArr[1];
                    }
                    if (i <= 0) {
                        return -1;
                    }
                    return cArr[0];
                }
                throw new ClosedException("InputStreamReader is closed.");
            } catch (Throwable th) {
                throw th;
            }
        }
    }
}
