package kotlin.io;

import defpackage.hb9;
import io.github.rosemoe.sora.widget.schemes.EditorColorScheme;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.nio.charset.Charset;
import kotlin.Deprecated;
import kotlin.DeprecatedSinceKotlin;
import kotlin.IgnorableReturnValue;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.collections.ByteIterator;
import kotlin.text.Charsets;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
@Metadata(d1 = {"\u0000\u0084\u0001\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a\u000e\u0010\u0000\u001a\u00020\u0001*\u00020\u0002H\u0086\u0082\u0004\u001a\u001c\u0010\u0003\u001a\u00020\u0004*\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0087\u0088\u0004b\u0002\b\b\u001a\u0012\u0010\t\u001a\u00020\u0004*\u00020\nH\u0087\u0088\u0004b\u0002\b\b\u001a\"\u0010\t\u001a\u00020\u0004*\u00020\n2\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\r\u001a\u00020\fH\u0087\u0088\u0004b\u0002\b\b\u001a\u001c\u0010\u000e\u001a\u00020\u0002*\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\fH\u0087\u0088\u0004b\u0002\b\b\u001a\u001c\u0010\u0011\u001a\u00020\u0012*\u00020\u000f2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0087\u0088\u0004b\u0002\b\b\u001a\u001c\u0010\u0013\u001a\u00020\u0014*\u00020\u000f2\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0087\u0088\u0004b\u0002\b\b\u001a\u001c\u0010\u000e\u001a\u00020\u0015*\u00020\u00162\b\b\u0002\u0010\u0010\u001a\u00020\fH\u0087\u0088\u0004b\u0002\b\b\u001a\u001c\u0010\u0017\u001a\u00020\u0018*\u00020\u00162\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0087\u0088\u0004b\u0002\b\b\u001a\u001c\u0010\u0019\u001a\u00020\u001a*\u00020\u00162\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u0087\u0088\u0004b\u0002\b\b\u001a$\u0010\u001b\u001a\u00020\u001c*\u00020\u000f2\u0006\u0010\u001d\u001a\u00020\u00162\b\b\u0002\u0010\u0010\u001a\u00020\fH\u0087\u0080\bb\u0002\b\u001e\u001af\u0010\u001f\u001a\u00020\n*\u00020\u000f2\b\b\u0002\u0010 \u001a\u00020\fH\u0087\u0080\u0004b*\b!\u0012\b\b\"\u0012\u0004\b\b(#\u0012\u001c\b$\u0012\u0018\b\u000bB\u0014\b%\u0012\b\b&\u0012\u0004\b\b('\u0012\u0006\b(\u0012\u0002\b\fb \b)\u0012\b\b*\u0012\u0004\b\b(+\u0012\b\b,\u0012\u0004\b\b(-\u0012\b\b.\u0012\u0004\b\b(/\u001a\u001c\u0010\u001f\u001a\u00020\n*\u00020\u000fH\u0087\u0080\u0004b\f\b0\u0012\b\b1\u0012\u0004\b\b(+¨\u00062"}, d2 = {"iterator", "Lkotlin/collections/ByteIterator;", "Ljava/io/BufferedInputStream;", "byteInputStream", "Ljava/io/ByteArrayInputStream;", "", "charset", "Ljava/nio/charset/Charset;", "Lkotlin/internal/InlineOnly;", "inputStream", "", "offset", "", "length", "buffered", "Ljava/io/InputStream;", "bufferSize", "reader", "Ljava/io/InputStreamReader;", "bufferedReader", "Ljava/io/BufferedReader;", "Ljava/io/BufferedOutputStream;", "Ljava/io/OutputStream;", "writer", "Ljava/io/OutputStreamWriter;", "bufferedWriter", "Ljava/io/BufferedWriter;", "copyTo", "", "out", "Lkotlin/IgnorableReturnValue;", "readBytes", "estimatedSize", "Lkotlin/Deprecated;", "message", "Use readBytes() overload without estimatedSize parameter", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "readBytes()", "imports", "Lkotlin/DeprecatedSinceKotlin;", "warningSince", "1.3", "errorSince", "1.5", "hiddenSince", "2.3", "Lkotlin/SinceKotlin;", "version", "kotlin-stdlib"}, k = 2, mv = {2, 4, 0}, xi = EditorColorScheme.SNIPPET_BACKGROUND_EDITING)
public final class ByteStreamsKt {
    private static final BufferedInputStream buffered(InputStream inputStream, int i) {
        inputStream.getClass();
        return inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream, i);
    }

    public static /* synthetic */ BufferedInputStream buffered$default(InputStream inputStream, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8192;
        }
        inputStream.getClass();
        return inputStream instanceof BufferedInputStream ? (BufferedInputStream) inputStream : new BufferedInputStream(inputStream, i);
    }

    private static final BufferedReader bufferedReader(InputStream inputStream, Charset charset) {
        inputStream.getClass();
        charset.getClass();
        return new BufferedReader(new InputStreamReader(inputStream, charset), 8192);
    }

    public static /* synthetic */ BufferedReader bufferedReader$default(InputStream inputStream, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        inputStream.getClass();
        charset.getClass();
        return new BufferedReader(new InputStreamReader(inputStream, charset), 8192);
    }

    private static final BufferedWriter bufferedWriter(OutputStream outputStream, Charset charset) {
        outputStream.getClass();
        charset.getClass();
        return new BufferedWriter(new OutputStreamWriter(outputStream, charset), 8192);
    }

    public static /* synthetic */ BufferedWriter bufferedWriter$default(OutputStream outputStream, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        outputStream.getClass();
        charset.getClass();
        return new BufferedWriter(new OutputStreamWriter(outputStream, charset), 8192);
    }

    private static final ByteArrayInputStream byteInputStream(String str, Charset charset) {
        str.getClass();
        charset.getClass();
        byte[] bytes = str.getBytes(charset);
        bytes.getClass();
        return new ByteArrayInputStream(bytes);
    }

    public static /* synthetic */ ByteArrayInputStream byteInputStream$default(String str, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        str.getClass();
        charset.getClass();
        byte[] bytes = str.getBytes(charset);
        bytes.getClass();
        return new ByteArrayInputStream(bytes);
    }

    @IgnorableReturnValue
    public static final long copyTo(InputStream inputStream, OutputStream outputStream, int i) throws IOException {
        inputStream.getClass();
        outputStream.getClass();
        byte[] bArr = new byte[i];
        int i2 = inputStream.read(bArr);
        long j = 0;
        while (i2 >= 0) {
            outputStream.write(bArr, 0, i2);
            j += (long) i2;
            i2 = inputStream.read(bArr);
        }
        return j;
    }

    public static /* synthetic */ long copyTo$default(InputStream inputStream, OutputStream outputStream, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 8192;
        }
        return copyTo(inputStream, outputStream, i);
    }

    private static final ByteArrayInputStream inputStream(byte[] bArr) {
        bArr.getClass();
        return new ByteArrayInputStream(bArr);
    }

    public static final ByteIterator iterator(final BufferedInputStream bufferedInputStream) {
        bufferedInputStream.getClass();
        return new ByteIterator() { // from class: kotlin.io.ByteStreamsKt.iterator.1
            private boolean finished;
            private int nextByte = -1;
            private boolean nextPrepared;

            private final void prepareNext() throws IOException {
                if (this.nextPrepared || this.finished) {
                    return;
                }
                int i = bufferedInputStream.read();
                this.nextByte = i;
                this.nextPrepared = true;
                this.finished = i == -1;
            }

            public final boolean getFinished() {
                return this.finished;
            }

            public final int getNextByte() {
                return this.nextByte;
            }

            public final boolean getNextPrepared() {
                return this.nextPrepared;
            }

            @Override // java.util.Iterator
            public boolean hasNext() throws IOException {
                prepareNext();
                return !this.finished;
            }

            @Override // kotlin.collections.ByteIterator
            public byte nextByte() throws IOException {
                prepareNext();
                if (this.finished) {
                    hb9.a("Input stream is over.");
                    return (byte) 0;
                }
                byte b = (byte) this.nextByte;
                this.nextPrepared = false;
                return b;
            }

            public final void setFinished(boolean z) {
                this.finished = z;
            }

            public final void setNextByte(int i) {
                this.nextByte = i;
            }

            public final void setNextPrepared(boolean z) {
                this.nextPrepared = z;
            }
        };
    }

    public static final byte[] readBytes(InputStream inputStream) {
        inputStream.getClass();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max(8192, inputStream.available()));
        copyTo$default(inputStream, byteArrayOutputStream, 0, 2, null);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArray.getClass();
        return byteArray;
    }

    public static /* synthetic */ byte[] readBytes$default(InputStream inputStream, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8192;
        }
        return readBytes(inputStream, i);
    }

    private static final InputStreamReader reader(InputStream inputStream, Charset charset) {
        inputStream.getClass();
        charset.getClass();
        return new InputStreamReader(inputStream, charset);
    }

    public static /* synthetic */ InputStreamReader reader$default(InputStream inputStream, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        inputStream.getClass();
        charset.getClass();
        return new InputStreamReader(inputStream, charset);
    }

    private static final OutputStreamWriter writer(OutputStream outputStream, Charset charset) {
        outputStream.getClass();
        charset.getClass();
        return new OutputStreamWriter(outputStream, charset);
    }

    public static /* synthetic */ OutputStreamWriter writer$default(OutputStream outputStream, Charset charset, int i, Object obj) {
        if ((i & 1) != 0) {
            charset = Charsets.UTF_8;
        }
        outputStream.getClass();
        charset.getClass();
        return new OutputStreamWriter(outputStream, charset);
    }

    private static final ByteArrayInputStream inputStream(byte[] bArr, int i, int i2) {
        bArr.getClass();
        return new ByteArrayInputStream(bArr, i, i2);
    }

    private static final BufferedOutputStream buffered(OutputStream outputStream, int i) {
        outputStream.getClass();
        return outputStream instanceof BufferedOutputStream ? (BufferedOutputStream) outputStream : new BufferedOutputStream(outputStream, i);
    }

    public static /* synthetic */ BufferedOutputStream buffered$default(OutputStream outputStream, int i, int i2, Object obj) {
        if ((i2 & 1) != 0) {
            i = 8192;
        }
        outputStream.getClass();
        return outputStream instanceof BufferedOutputStream ? (BufferedOutputStream) outputStream : new BufferedOutputStream(outputStream, i);
    }

    @Deprecated(message = "Use readBytes() overload without estimatedSize parameter", replaceWith = @ReplaceWith(expression = "readBytes()", imports = {}))
    @DeprecatedSinceKotlin(errorSince = "1.5", hiddenSince = "2.3", warningSince = "1.3")
    public static final /* synthetic */ byte[] readBytes(InputStream inputStream, int i) {
        inputStream.getClass();
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream(Math.max(i, inputStream.available()));
        copyTo$default(inputStream, byteArrayOutputStream, 0, 2, null);
        byte[] byteArray = byteArrayOutputStream.toByteArray();
        byteArray.getClass();
        return byteArray;
    }
}
