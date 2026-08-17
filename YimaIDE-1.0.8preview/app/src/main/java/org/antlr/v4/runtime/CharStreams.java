package org.antlr.v4.runtime;

import java.io.IOException;
import java.io.InputStream;
import java.io.Reader;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;
import java.nio.charset.CoderResult;
import java.nio.charset.CodingErrorAction;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class CharStreams {
    private static final int DEFAULT_BUFFER_SIZE = 4096;

    private CharStreams() {
    }

    public static CodePointCharStream fromChannel(ReadableByteChannel readableByteChannel, Charset charset, int i, CodingErrorAction codingErrorAction, String str, long j) throws IOException {
        try {
            ByteBuffer byteBufferAllocate = ByteBuffer.allocate(i);
            CharBuffer charBufferAllocate = CharBuffer.allocate(i);
            if (j == -1) {
                j = i;
            } else if (j > 2147483647L) {
                throw new IOException(String.format("inputSize %d larger than max %d", Long.valueOf(j), Integer.MAX_VALUE));
            }
            CodePointBuffer.Builder builder = CodePointBuffer.builder((int) j);
            CharsetDecoder charsetDecoderOnUnmappableCharacter = charset.newDecoder().onMalformedInput(codingErrorAction).onUnmappableCharacter(codingErrorAction);
            boolean z = false;
            while (!z) {
                z = readableByteChannel.read(byteBufferAllocate) == -1;
                byteBufferAllocate.flip();
                CoderResult coderResultDecode = charsetDecoderOnUnmappableCharacter.decode(byteBufferAllocate, charBufferAllocate, z);
                if (coderResultDecode.isError() && codingErrorAction.equals(CodingErrorAction.REPORT)) {
                    coderResultDecode.throwException();
                }
                charBufferAllocate.flip();
                builder.append(charBufferAllocate);
                byteBufferAllocate.compact();
                charBufferAllocate.compact();
            }
            CoderResult coderResultFlush = charsetDecoderOnUnmappableCharacter.flush(charBufferAllocate);
            if (coderResultFlush.isError() && codingErrorAction.equals(CodingErrorAction.REPORT)) {
                coderResultFlush.throwException();
            }
            charBufferAllocate.flip();
            builder.append(charBufferAllocate);
            CodePointCharStream codePointCharStreamFromBuffer = CodePointCharStream.fromBuffer(builder.build(), str);
            readableByteChannel.close();
            return codePointCharStreamFromBuffer;
        } catch (Throwable th) {
            readableByteChannel.close();
            throw th;
        }
    }

    public static CharStream fromFileName(String str) throws IOException {
        return fromPath(Paths.get(str, new String[0]), StandardCharsets.UTF_8);
    }

    public static CharStream fromPath(Path path, Charset charset) throws IOException {
        long size = Files.size(path);
        SeekableByteChannel seekableByteChannelNewByteChannel = Files.newByteChannel(path, new OpenOption[0]);
        try {
            CodePointCharStream codePointCharStreamFromChannel = fromChannel(seekableByteChannelNewByteChannel, charset, 4096, CodingErrorAction.REPLACE, path.toString(), size);
            if (seekableByteChannelNewByteChannel != null) {
                seekableByteChannelNewByteChannel.close();
            }
            return codePointCharStreamFromChannel;
        } catch (Throwable th) {
            if (seekableByteChannelNewByteChannel == null) {
                throw th;
            }
            try {
                seekableByteChannelNewByteChannel.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    public static CodePointCharStream fromReader(Reader reader, String str) throws IOException {
        try {
            CodePointBuffer.Builder builder = CodePointBuffer.builder(4096);
            CharBuffer charBufferAllocate = CharBuffer.allocate(4096);
            while (reader.read(charBufferAllocate) != -1) {
                charBufferAllocate.flip();
                builder.append(charBufferAllocate);
                charBufferAllocate.compact();
            }
            return CodePointCharStream.fromBuffer(builder.build(), str);
        } finally {
            reader.close();
        }
    }

    public static CharStream fromStream(InputStream inputStream, Charset charset, long j) throws IOException {
        ReadableByteChannel readableByteChannelNewChannel = Channels.newChannel(inputStream);
        try {
            CodePointCharStream codePointCharStreamFromChannel = fromChannel(readableByteChannelNewChannel, charset, 4096, CodingErrorAction.REPLACE, "<unknown>", j);
            if (readableByteChannelNewChannel != null) {
                readableByteChannelNewChannel.close();
            }
            return codePointCharStreamFromChannel;
        } catch (Throwable th) {
            if (readableByteChannelNewChannel == null) {
                throw th;
            }
            try {
                readableByteChannelNewChannel.close();
                throw th;
            } catch (Throwable th2) {
                th.addSuppressed(th2);
                throw th;
            }
        }
    }

    public static CodePointCharStream fromString(String str, String str2) {
        CodePointBuffer.Builder builder = CodePointBuffer.builder(str.length());
        CharBuffer charBufferAllocate = CharBuffer.allocate(str.length());
        charBufferAllocate.put(str);
        charBufferAllocate.flip();
        builder.append(charBufferAllocate);
        return CodePointCharStream.fromBuffer(builder.build(), str2);
    }

    public static CharStream fromFileName(String str, Charset charset) throws IOException {
        return fromPath(Paths.get(str, new String[0]), charset);
    }

    public static CodePointCharStream fromString(String str) {
        return fromString(str, "<unknown>");
    }

    public static CharStream fromStream(InputStream inputStream, Charset charset) throws IOException {
        return fromStream(inputStream, charset, -1L);
    }

    public static CharStream fromStream(InputStream inputStream) throws IOException {
        return fromStream(inputStream, StandardCharsets.UTF_8);
    }

    public static CharStream fromPath(Path path) throws IOException {
        return fromPath(path, StandardCharsets.UTF_8);
    }

    public static CodePointCharStream fromReader(Reader reader) throws IOException {
        return fromReader(reader, "<unknown>");
    }

    public static CharStream fromChannel(ReadableByteChannel readableByteChannel, Charset charset) throws IOException {
        return fromChannel(readableByteChannel, 4096, CodingErrorAction.REPLACE, "<unknown>");
    }

    public static CodePointCharStream fromChannel(ReadableByteChannel readableByteChannel, int i, CodingErrorAction codingErrorAction, String str) throws IOException {
        return fromChannel(readableByteChannel, StandardCharsets.UTF_8, i, codingErrorAction, str, -1L);
    }

    public static CharStream fromChannel(ReadableByteChannel readableByteChannel) throws IOException {
        return fromChannel(readableByteChannel, StandardCharsets.UTF_8);
    }
}
