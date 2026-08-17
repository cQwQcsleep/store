package com.reandroid.utils.io;

import com.reandroid.common.FileChannelInputStream;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class IOUtil {
    public static void close(Object obj) throws IOException {
        if (obj instanceof Closeable) {
            ((Closeable) obj).close();
        }
    }

    public static byte[] readFully(InputStream inputStream) throws IOException {
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        writeAll(inputStream, byteArrayOutputStream, true);
        return byteArrayOutputStream.toByteArray();
    }

    public static String readUtf8(File file) throws IOException {
        return new String(readFully(file), StandardCharsets.UTF_8);
    }

    @Deprecated
    public static String shortPath(File file, int i) {
        return FileUtil.shortPath(file, i);
    }

    public static void writeAll(InputStream inputStream, OutputStream outputStream, boolean z) throws IOException {
        byte[] bArr = new byte[1024000];
        while (true) {
            int i = inputStream.read(bArr, 0, bArr.length);
            if (i < 0) {
                break;
            }
            outputStream.write(bArr, 0, i);
            int length = bArr.length;
            if (i == length && length < 10240000) {
                bArr = new byte[length + 1024000];
            }
        }
        if (z) {
            inputStream.close();
            outputStream.close();
        }
    }

    public static void writeUtf8(String str, File file) throws IOException {
        File tmpName = file.isFile() ? FileUtil.toTmpName(file) : file;
        writeUtf8(str, FileUtil.outputStream(tmpName));
        if (tmpName.equals(file)) {
            return;
        }
        file.delete();
        tmpName.renameTo(file);
    }

    public static String readUtf8(InputStream inputStream) throws IOException {
        return new String(readFully(inputStream), StandardCharsets.UTF_8);
    }

    public static byte[] readFully(File file) throws IOException {
        return FileChannelInputStream.read(file, (int) file.length());
    }

    public static void writeUtf8(String str, OutputStream outputStream) throws IOException {
        byte[] bytes = str.getBytes(StandardCharsets.UTF_8);
        outputStream.write(bytes, 0, bytes.length);
        outputStream.close();
    }

    public static void writeAll(InputStream inputStream, OutputStream outputStream) throws IOException {
        writeAll(inputStream, outputStream, true);
    }

    public static void writeAll(InputStream inputStream, File file) throws IOException {
        FileUtil.ensureParentDirectory(file);
        File tmpName = file.isFile() ? FileUtil.toTmpName(file) : file;
        writeAll(inputStream, FileUtil.outputStream(tmpName), true);
        if (tmpName.equals(file)) {
            return;
        }
        file.delete();
        tmpName.renameTo(file);
    }
}
