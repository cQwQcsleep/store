package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import java.io.File;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.OpenOption;
import java.util.Arrays;

/* renamed from: com.shadow.okio.-DeprecatedOkio, reason: invalid class name */
/* loaded from: /workspace/unpacked/classes2.dex */
public final class DeprecatedOkio {
    public static final DeprecatedOkio INSTANCE = new DeprecatedOkio();

    private DeprecatedOkio() {
    }

    public final Sink appendingSink(File file) {
        CloseableKt.checkNotNullParameter(file, "file");
        return Okio.appendingSink(file);
    }

    public final Sink blackhole() {
        return Okio.blackhole();
    }

    public final BufferedSink buffer(Sink sink) {
        CloseableKt.checkNotNullParameter(sink, "sink");
        return Okio.buffer(sink);
    }

    public final Sink sink(File file) {
        CloseableKt.checkNotNullParameter(file, "file");
        return Okio__JvmOkioKt.sink$default(file, false, 1, null);
    }

    public final Source source(File file) {
        CloseableKt.checkNotNullParameter(file, "file");
        return Okio.source(file);
    }

    public final BufferedSource buffer(Source source) {
        CloseableKt.checkNotNullParameter(source, "source");
        return Okio.buffer(source);
    }

    public final Sink sink(OutputStream outputStream) {
        CloseableKt.checkNotNullParameter(outputStream, "outputStream");
        return Okio.sink(outputStream);
    }

    public final Source source(InputStream inputStream) {
        CloseableKt.checkNotNullParameter(inputStream, "inputStream");
        return Okio.source(inputStream);
    }

    public final Sink sink(java.nio.file.Path path, OpenOption... openOptionArr) {
        CloseableKt.checkNotNullParameter(path, "path");
        CloseableKt.checkNotNullParameter(openOptionArr, "options");
        return Okio.sink(path, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
    }

    public final Source source(java.nio.file.Path path, OpenOption... openOptionArr) {
        CloseableKt.checkNotNullParameter(path, "path");
        CloseableKt.checkNotNullParameter(openOptionArr, "options");
        return Okio.source(path, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
    }

    public final Sink sink(Socket socket) {
        CloseableKt.checkNotNullParameter(socket, "socket");
        return Okio.sink(socket);
    }

    public final Source source(Socket socket) {
        CloseableKt.checkNotNullParameter(socket, "socket");
        return Okio.source(socket);
    }
}
