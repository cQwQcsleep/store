package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.text.StringsKt;
import com.shadow.okio.internal.ResourceFileSystem;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.file.Files;
import java.nio.file.OpenOption;
import java.security.MessageDigest;
import java.util.Arrays;
import java.util.logging.Logger;
import javax.crypto.Cipher;
import javax.crypto.Mac;

/* loaded from: /workspace/unpacked/classes2.dex */
final /* synthetic */ class Okio__JvmOkioKt {
    private static final Logger logger = Logger.getLogger("com.shadow.okio.Okio");

    public static final Sink appendingSink(File file) throws FileNotFoundException {
        CloseableKt.checkNotNullParameter(file, "<this>");
        return Okio.sink(new FileOutputStream(file, true));
    }

    public static final FileSystem asResourceFileSystem(ClassLoader classLoader) {
        CloseableKt.checkNotNullParameter(classLoader, "<this>");
        return new ResourceFileSystem(classLoader, true, null, 4, null);
    }

    public static final CipherSink cipherSink(Sink sink, Cipher cipher) {
        CloseableKt.checkNotNullParameter(sink, "<this>");
        CloseableKt.checkNotNullParameter(cipher, "cipher");
        return new CipherSink(Okio.buffer(sink), cipher);
    }

    public static final CipherSource cipherSource(Source source, Cipher cipher) {
        CloseableKt.checkNotNullParameter(source, "<this>");
        CloseableKt.checkNotNullParameter(cipher, "cipher");
        return new CipherSource(Okio.buffer(source), cipher);
    }

    public static final HashingSink hashingSink(Sink sink, Mac mac) {
        CloseableKt.checkNotNullParameter(sink, "<this>");
        CloseableKt.checkNotNullParameter(mac, "mac");
        return new HashingSink(sink, mac);
    }

    public static final HashingSource hashingSource(Source source, Mac mac) {
        CloseableKt.checkNotNullParameter(source, "<this>");
        CloseableKt.checkNotNullParameter(mac, "mac");
        return new HashingSource(source, mac);
    }

    public static final boolean isAndroidGetsocknameError(AssertionError assertionError) {
        CloseableKt.checkNotNullParameter(assertionError, "<this>");
        if (assertionError.getCause() == null) {
            return false;
        }
        String message = assertionError.getMessage();
        return message != null ? StringsKt.e(message, "getsockname failed") : false;
    }

    public static final Sink sink(File file) throws FileNotFoundException {
        CloseableKt.checkNotNullParameter(file, "<this>");
        return sink$default(file, false, 1, null);
    }

    public static /* synthetic */ Sink sink$default(File file, boolean z, int i, Object obj) throws FileNotFoundException {
        if ((i & 1) != 0) {
            z = false;
        }
        return Okio.sink(file, z);
    }

    public static final Source source(InputStream inputStream) {
        CloseableKt.checkNotNullParameter(inputStream, "<this>");
        return new InputStreamSource(inputStream, new Timeout());
    }

    public static final HashingSink hashingSink(Sink sink, MessageDigest messageDigest) {
        CloseableKt.checkNotNullParameter(sink, "<this>");
        CloseableKt.checkNotNullParameter(messageDigest, "digest");
        return new HashingSink(sink, messageDigest);
    }

    public static final HashingSource hashingSource(Source source, MessageDigest messageDigest) {
        CloseableKt.checkNotNullParameter(source, "<this>");
        CloseableKt.checkNotNullParameter(messageDigest, "digest");
        return new HashingSource(source, messageDigest);
    }

    public static final Sink sink(OutputStream outputStream) {
        CloseableKt.checkNotNullParameter(outputStream, "<this>");
        return new OutputStreamSink(outputStream, new Timeout());
    }

    public static final Source source(Socket socket) throws IOException {
        CloseableKt.checkNotNullParameter(socket, "<this>");
        SocketAsyncTimeout socketAsyncTimeout = new SocketAsyncTimeout(socket);
        InputStream inputStream = socket.getInputStream();
        CloseableKt.checkNotNullExpressionValue(inputStream, "getInputStream(...)");
        return socketAsyncTimeout.source(new InputStreamSource(inputStream, socketAsyncTimeout));
    }

    public static final Sink sink(Socket socket) throws IOException {
        CloseableKt.checkNotNullParameter(socket, "<this>");
        SocketAsyncTimeout socketAsyncTimeout = new SocketAsyncTimeout(socket);
        OutputStream outputStream = socket.getOutputStream();
        CloseableKt.checkNotNullExpressionValue(outputStream, "getOutputStream(...)");
        return socketAsyncTimeout.sink(new OutputStreamSink(outputStream, socketAsyncTimeout));
    }

    public static final Source source(File file) throws FileNotFoundException {
        CloseableKt.checkNotNullParameter(file, "<this>");
        return new InputStreamSource(new FileInputStream(file), Timeout.NONE);
    }

    public static final Sink sink(File file, boolean z) throws FileNotFoundException {
        CloseableKt.checkNotNullParameter(file, "<this>");
        return Okio.sink(new FileOutputStream(file, z));
    }

    public static final Source source(java.nio.file.Path path, OpenOption... openOptionArr) throws IOException {
        CloseableKt.checkNotNullParameter(path, "<this>");
        CloseableKt.checkNotNullParameter(openOptionArr, "options");
        InputStream inputStreamNewInputStream = Files.newInputStream(path, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        CloseableKt.checkNotNullExpressionValue(inputStreamNewInputStream, "newInputStream(...)");
        return Okio.source(inputStreamNewInputStream);
    }

    public static final Sink sink(java.nio.file.Path path, OpenOption... openOptionArr) throws IOException {
        CloseableKt.checkNotNullParameter(path, "<this>");
        CloseableKt.checkNotNullParameter(openOptionArr, "options");
        OutputStream outputStreamNewOutputStream = Files.newOutputStream(path, (OpenOption[]) Arrays.copyOf(openOptionArr, openOptionArr.length));
        CloseableKt.checkNotNullExpressionValue(outputStreamNewOutputStream, "newOutputStream(...)");
        return Okio.sink(outputStreamNewOutputStream);
    }
}
