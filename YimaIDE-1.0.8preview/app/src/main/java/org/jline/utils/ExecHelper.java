package org.jline.utils;

import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public final class ExecHelper {
    private static void close(Closeable... closeableArr) {
        for (Closeable closeable : closeableArr) {
            if (closeable != null) {
                try {
                    closeable.close();
                } catch (Exception unused) {
                }
            }
        }
    }

    public static String waitAndCapture(Process process) throws Throwable {
        OutputStream outputStream;
        InputStream errorStream;
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        OutputStream outputStream2 = null;
        try {
            InputStream inputStream = process.getInputStream();
            while (true) {
                try {
                    int i = inputStream.read();
                    if (i == -1) {
                        break;
                    }
                    byteArrayOutputStream.write(i);
                } catch (Throwable th) {
                    th = th;
                    outputStream = null;
                    errorStream = null;
                }
                outputStream2 = inputStream;
                close(outputStream2, outputStream, errorStream);
                throw th;
            }
            errorStream = process.getErrorStream();
            while (true) {
                try {
                    int i2 = errorStream.read();
                    if (i2 == -1) {
                        outputStream2 = process.getOutputStream();
                        process.waitFor();
                        close(inputStream, outputStream2, errorStream);
                        return byteArrayOutputStream.toString();
                    }
                    byteArrayOutputStream.write(i2);
                } catch (Throwable th2) {
                    th = th2;
                    outputStream = outputStream2;
                }
            }
        } catch (Throwable th3) {
            th = th3;
            outputStream = null;
            errorStream = null;
        }
    }
}
