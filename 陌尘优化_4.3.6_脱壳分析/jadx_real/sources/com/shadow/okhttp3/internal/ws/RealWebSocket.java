package com.shadow.okhttp3.internal.ws;

import com.shadow.kotlin.collections.CollectionsKt;
import com.shadow.kotlin.io.CloseableKt;
import com.shadow.kotlin.jvm.internal.DefaultConstructorMarker;
import com.shadow.kotlin.ranges.IntRange;
import com.shadow.okhttp3.Call;
import com.shadow.okhttp3.Callback;
import com.shadow.okhttp3.EventListener;
import com.shadow.okhttp3.OkHttpClient;
import com.shadow.okhttp3.Protocol;
import com.shadow.okhttp3.Request;
import com.shadow.okhttp3.Response;
import com.shadow.okhttp3.WebSocket;
import com.shadow.okhttp3.WebSocketListener;
import com.shadow.okhttp3.internal.Util;
import com.shadow.okhttp3.internal.concurrent.Task;
import com.shadow.okhttp3.internal.concurrent.TaskQueue;
import com.shadow.okhttp3.internal.concurrent.TaskRunner;
import com.shadow.okhttp3.internal.connection.Exchange;
import com.shadow.okhttp3.internal.connection.RealCall;
import com.shadow.okhttp3.internal.ws.WebSocketReader;
import com.shadow.okio.BufferedSink;
import com.shadow.okio.BufferedSource;
import com.shadow.okio.ByteString;
import java.io.Closeable;
import java.io.IOException;
import java.net.ProtocolException;
import java.net.SocketTimeoutException;
import java.util.ArrayDeque;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/* loaded from: /workspace/unpacked/classes2.dex */
public final class RealWebSocket implements WebSocket, WebSocketReader.FrameCallback {
    private static final long CANCEL_AFTER_CLOSE_MILLIS = 60000;
    public static final long DEFAULT_MINIMUM_DEFLATE_SIZE = 1024;
    private static final long MAX_QUEUE_SIZE = 16777216;
    private boolean awaitingPong;
    private Call call;
    private boolean enqueuedClose;
    private WebSocketExtensions extensions;
    private boolean failed;
    private final String key;
    private final WebSocketListener listener;
    private final ArrayDeque<Object> messageAndCloseQueue;
    private long minimumDeflateSize;
    private String name;
    private final Request originalRequest;
    private final long pingIntervalMillis;
    private final ArrayDeque<ByteString> pongQueue;
    private long queueSize;
    private final Random random;
    private WebSocketReader reader;
    private int receivedCloseCode;
    private String receivedCloseReason;
    private int receivedPingCount;
    private int receivedPongCount;
    private int sentPingCount;
    private Streams streams;
    private TaskQueue taskQueue;
    private WebSocketWriter writer;
    private Task writerTask;
    public static final Companion Companion = new Companion(null);
    private static final List<Protocol> ONLY_HTTP1 = CollectionsKt.d(Protocol.HTTP_1_1);

    public static final class Close {
        private final long cancelAfterCloseMillis;
        private final int code;
        private final ByteString reason;

        public Close(int i, ByteString byteString, long j) {
            this.code = i;
            this.reason = byteString;
            this.cancelAfterCloseMillis = j;
        }

        public final long getCancelAfterCloseMillis() {
            return this.cancelAfterCloseMillis;
        }

        public final int getCode() {
            return this.code;
        }

        public final ByteString getReason() {
            return this.reason;
        }
    }

    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }
    }

    public static final class Message {
        private final ByteString data;
        private final int formatOpcode;

        public Message(int i, ByteString byteString) {
            CloseableKt.checkNotNullParameter(byteString, "data");
            this.formatOpcode = i;
            this.data = byteString;
        }

        public final ByteString getData() {
            return this.data;
        }

        public final int getFormatOpcode() {
            return this.formatOpcode;
        }
    }

    public static abstract class Streams implements Closeable {
        private final boolean client;
        private final BufferedSink sink;
        private final BufferedSource source;

        public Streams(boolean z, BufferedSource bufferedSource, BufferedSink bufferedSink) {
            CloseableKt.checkNotNullParameter(bufferedSource, "source");
            CloseableKt.checkNotNullParameter(bufferedSink, "sink");
            this.client = z;
            this.source = bufferedSource;
            this.sink = bufferedSink;
        }

        public final boolean getClient() {
            return this.client;
        }

        public final BufferedSink getSink() {
            return this.sink;
        }

        public final BufferedSource getSource() {
            return this.source;
        }
    }

    public final class WriterTask extends Task {
        public WriterTask() {
            super(RealWebSocket.this.name + " writer", false, 2, null);
        }

        @Override // com.shadow.okhttp3.internal.concurrent.Task
        public long runOnce() throws IOException {
            try {
                return RealWebSocket.this.writeOneFrame$okhttp() ? 0L : -1L;
            } catch (IOException e) {
                RealWebSocket.this.failWebSocket(e, null);
                return -1L;
            }
        }
    }

    public RealWebSocket(TaskRunner taskRunner, Request request, WebSocketListener webSocketListener, Random random, long j, WebSocketExtensions webSocketExtensions, long j2) {
        CloseableKt.checkNotNullParameter(taskRunner, "taskRunner");
        CloseableKt.checkNotNullParameter(request, "originalRequest");
        CloseableKt.checkNotNullParameter(webSocketListener, "listener");
        CloseableKt.checkNotNullParameter(random, "random");
        this.originalRequest = request;
        this.listener = webSocketListener;
        this.random = random;
        this.pingIntervalMillis = j;
        this.extensions = webSocketExtensions;
        this.minimumDeflateSize = j2;
        this.taskQueue = taskRunner.newQueue();
        this.pongQueue = new ArrayDeque<>();
        this.messageAndCloseQueue = new ArrayDeque<>();
        this.receivedCloseCode = -1;
        if (!"GET".equals(request.method())) {
            throw new IllegalArgumentException(("Request must be GET: " + request.method()).toString());
        }
        ByteString.Companion companion = ByteString.Companion;
        byte[] bArr = new byte[16];
        random.nextBytes(bArr);
        this.key = ByteString.Companion.of$default(companion, bArr, 0, 0, 3, null).base64();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final boolean isValid(WebSocketExtensions webSocketExtensions) {
        if (webSocketExtensions.unknownValues || webSocketExtensions.clientMaxWindowBits != null) {
            return false;
        }
        if (webSocketExtensions.serverMaxWindowBits != null) {
            IntRange intRange = new IntRange(8, 15, 1);
            int iIntValue = webSocketExtensions.serverMaxWindowBits.intValue();
            if (intRange.getFirst() > iIntValue || iIntValue > intRange.getLast()) {
                return false;
            }
        }
        return true;
    }

    private final void runWriter() {
        if (!Util.assertionsEnabled || Thread.holdsLock(this)) {
            Task task = this.writerTask;
            if (task != null) {
                TaskQueue.schedule$default(this.taskQueue, task, 0L, 2, null);
                return;
            }
            return;
        }
        throw new AssertionError("Thread " + Thread.currentThread().getName() + " MUST hold lock on " + this);
    }

    public final void awaitTermination(long j, TimeUnit timeUnit) throws InterruptedException {
        CloseableKt.checkNotNullParameter(timeUnit, "timeUnit");
        this.taskQueue.idleLatch().await(j, timeUnit);
    }

    @Override // com.shadow.okhttp3.WebSocket
    public void cancel() {
        Call call = this.call;
        CloseableKt.checkNotNull(call);
        call.cancel();
    }

    public final void checkUpgradeSuccess$okhttp(Response response, Exchange exchange) throws IOException {
        CloseableKt.checkNotNullParameter(response, "response");
        if (response.code() != 101) {
            throw new ProtocolException("Expected HTTP 101 response but was '" + response.code() + ' ' + response.message() + '\'');
        }
        String strHeader$default = Response.header$default(response, "Connection", null, 2, null);
        if (!"Upgrade".equalsIgnoreCase(strHeader$default)) {
            throw new ProtocolException("Expected 'Connection' header value 'Upgrade' but was '" + strHeader$default + '\'');
        }
        String strHeader$default2 = Response.header$default(response, "Upgrade", null, 2, null);
        if (!"websocket".equalsIgnoreCase(strHeader$default2)) {
            throw new ProtocolException("Expected 'Upgrade' header value 'websocket' but was '" + strHeader$default2 + '\'');
        }
        String strHeader$default3 = Response.header$default(response, "Sec-WebSocket-Accept", null, 2, null);
        String strBase64 = ByteString.Companion.encodeUtf8(this.key + WebSocketProtocol.ACCEPT_MAGIC).sha1().base64();
        if (CloseableKt.areEqual(strBase64, strHeader$default3)) {
            if (exchange == null) {
                throw new ProtocolException("Web Socket exchange missing: bad interceptor?");
            }
            return;
        }
        throw new ProtocolException("Expected 'Sec-WebSocket-Accept' header value '" + strBase64 + "' but was '" + strHeader$default3 + '\'');
    }

    @Override // com.shadow.okhttp3.WebSocket
    public boolean close(int i, String str) {
        return close(i, str, CANCEL_AFTER_CLOSE_MILLIS);
    }

    public final void connect(OkHttpClient okHttpClient) {
        CloseableKt.checkNotNullParameter(okHttpClient, "client");
        if (this.originalRequest.header("Sec-WebSocket-Extensions") != null) {
            failWebSocket(new ProtocolException("Request header not permitted: 'Sec-WebSocket-Extensions'"), null);
            return;
        }
        OkHttpClient okHttpClientBuild = okHttpClient.newBuilder().eventListener(EventListener.NONE).protocols(ONLY_HTTP1).build();
        final Request requestBuild = this.originalRequest.newBuilder().header("Upgrade", "websocket").header("Connection", "Upgrade").header("Sec-WebSocket-Key", this.key).header("Sec-WebSocket-Version", "13").header("Sec-WebSocket-Extensions", "permessage-deflate").build();
        RealCall realCall = new RealCall(okHttpClientBuild, requestBuild, true);
        this.call = realCall;
        realCall.enqueue(new Callback() { // from class: com.shadow.okhttp3.internal.ws.RealWebSocket.connect.1
            @Override // com.shadow.okhttp3.Callback
            public void onFailure(Call call, IOException iOException) throws IOException {
                CloseableKt.checkNotNullParameter(call, "call");
                CloseableKt.checkNotNullParameter(iOException, "e");
                RealWebSocket.this.failWebSocket(iOException, null);
            }

            @Override // com.shadow.okhttp3.Callback
            public void onResponse(Call call, Response response) throws IOException {
                CloseableKt.checkNotNullParameter(call, "call");
                CloseableKt.checkNotNullParameter(response, "response");
                Exchange exchange = response.exchange();
                try {
                    RealWebSocket.this.checkUpgradeSuccess$okhttp(response, exchange);
                    CloseableKt.checkNotNull(exchange);
                    Streams streamsNewWebSocketStreams = exchange.newWebSocketStreams();
                    WebSocketExtensions webSocketExtensions = WebSocketExtensions.Companion.parse(response.headers());
                    RealWebSocket.this.extensions = webSocketExtensions;
                    if (!RealWebSocket.this.isValid(webSocketExtensions)) {
                        RealWebSocket realWebSocket = RealWebSocket.this;
                        synchronized (realWebSocket) {
                            realWebSocket.messageAndCloseQueue.clear();
                            realWebSocket.close(1010, "unexpected Sec-WebSocket-Extensions in response header");
                        }
                    }
                    try {
                        RealWebSocket.this.initReaderAndWriter(Util.okHttpName + " WebSocket " + requestBuild.url().redact(), streamsNewWebSocketStreams);
                        RealWebSocket.this.getListener$okhttp().onOpen(RealWebSocket.this, response);
                        RealWebSocket.this.loopReader();
                    } catch (Exception e) {
                        RealWebSocket.this.failWebSocket(e, null);
                    }
                } catch (IOException e2) {
                    RealWebSocket.this.failWebSocket(e2, response);
                    Util.closeQuietly(response);
                    if (exchange != null) {
                        exchange.webSocketUpgradeFailed();
                    }
                }
            }
        });
    }

    public final void failWebSocket(Exception exc, Response response) throws IOException {
        CloseableKt.checkNotNullParameter(exc, "e");
        synchronized (this) {
            if (this.failed) {
                return;
            }
            this.failed = true;
            Streams streams = this.streams;
            this.streams = null;
            WebSocketReader webSocketReader = this.reader;
            this.reader = null;
            WebSocketWriter webSocketWriter = this.writer;
            this.writer = null;
            this.taskQueue.shutdown();
            try {
                this.listener.onFailure(this, exc, response);
            } finally {
                if (streams != null) {
                    Util.closeQuietly(streams);
                }
                if (webSocketReader != null) {
                    Util.closeQuietly(webSocketReader);
                }
                if (webSocketWriter != null) {
                    Util.closeQuietly(webSocketWriter);
                }
            }
        }
    }

    public final WebSocketListener getListener$okhttp() {
        return this.listener;
    }

    public final void initReaderAndWriter(String str, Streams streams) throws IOException {
        CloseableKt.checkNotNullParameter(str, "name");
        CloseableKt.checkNotNullParameter(streams, "streams");
        WebSocketExtensions webSocketExtensions = this.extensions;
        CloseableKt.checkNotNull(webSocketExtensions);
        synchronized (this) {
            try {
                this.name = str;
                this.streams = streams;
                this.writer = new WebSocketWriter(streams.getClient(), streams.getSink(), this.random, webSocketExtensions.perMessageDeflate, webSocketExtensions.noContextTakeover(streams.getClient()), this.minimumDeflateSize);
                this.writerTask = new WriterTask();
                long j = this.pingIntervalMillis;
                if (j != 0) {
                    final long nanos = TimeUnit.MILLISECONDS.toNanos(j);
                    TaskQueue taskQueue = this.taskQueue;
                    final String strConcat = str.concat(" ping");
                    taskQueue.schedule(new Task(strConcat) { // from class: com.shadow.okhttp3.internal.ws.RealWebSocket$initReaderAndWriter$lambda$3$$inlined$schedule$1
                        @Override // com.shadow.okhttp3.internal.concurrent.Task
                        public long runOnce() throws IOException {
                            this.writePingFrame$okhttp();
                            return nanos;
                        }
                    }, nanos);
                }
                if (!this.messageAndCloseQueue.isEmpty()) {
                    runWriter();
                }
            } catch (Throwable th) {
                throw th;
            }
        }
        this.reader = new WebSocketReader(streams.getClient(), streams.getSource(), this, webSocketExtensions.perMessageDeflate, webSocketExtensions.noContextTakeover(!streams.getClient()));
    }

    public final void loopReader() throws IOException {
        while (this.receivedCloseCode == -1) {
            WebSocketReader webSocketReader = this.reader;
            CloseableKt.checkNotNull(webSocketReader);
            webSocketReader.processNextFrame();
        }
    }

    @Override // com.shadow.okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadClose(int i, String str) throws IOException {
        Streams streams;
        WebSocketReader webSocketReader;
        WebSocketWriter webSocketWriter;
        CloseableKt.checkNotNullParameter(str, "reason");
        if (i == -1) {
            throw new IllegalArgumentException("Failed requirement.");
        }
        synchronized (this) {
            if (this.receivedCloseCode != -1) {
                throw new IllegalStateException("already closed");
            }
            this.receivedCloseCode = i;
            this.receivedCloseReason = str;
            streams = null;
            if (this.enqueuedClose && this.messageAndCloseQueue.isEmpty()) {
                Streams streams2 = this.streams;
                this.streams = null;
                webSocketReader = this.reader;
                this.reader = null;
                webSocketWriter = this.writer;
                this.writer = null;
                this.taskQueue.shutdown();
                streams = streams2;
            } else {
                webSocketReader = null;
                webSocketWriter = null;
            }
        }
        try {
            this.listener.onClosing(this, i, str);
            if (streams != null) {
                this.listener.onClosed(this, i, str);
            }
        } finally {
            if (streams != null) {
                Util.closeQuietly(streams);
            }
            if (webSocketReader != null) {
                Util.closeQuietly(webSocketReader);
            }
            if (webSocketWriter != null) {
                Util.closeQuietly(webSocketWriter);
            }
        }
    }

    @Override // com.shadow.okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadMessage(String str) throws IOException {
        CloseableKt.checkNotNullParameter(str, "text");
        this.listener.onMessage(this, str);
    }

    @Override // com.shadow.okhttp3.internal.ws.WebSocketReader.FrameCallback
    public synchronized void onReadPing(ByteString byteString) {
        try {
            CloseableKt.checkNotNullParameter(byteString, "payload");
            if (!this.failed && (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty())) {
                this.pongQueue.add(byteString);
                runWriter();
                this.receivedPingCount++;
            }
        } finally {
        }
    }

    @Override // com.shadow.okhttp3.internal.ws.WebSocketReader.FrameCallback
    public synchronized void onReadPong(ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "payload");
        this.receivedPongCount++;
        this.awaitingPong = false;
    }

    public final synchronized boolean pong(ByteString byteString) {
        try {
            CloseableKt.checkNotNullParameter(byteString, "payload");
            if (!this.failed && (!this.enqueuedClose || !this.messageAndCloseQueue.isEmpty())) {
                this.pongQueue.add(byteString);
                runWriter();
                return true;
            }
            return false;
        } finally {
        }
    }

    public final boolean processNextFrame() throws IOException {
        try {
            WebSocketReader webSocketReader = this.reader;
            CloseableKt.checkNotNull(webSocketReader);
            webSocketReader.processNextFrame();
            return this.receivedCloseCode == -1;
        } catch (Exception e) {
            failWebSocket(e, null);
            return false;
        }
    }

    @Override // com.shadow.okhttp3.WebSocket
    public synchronized long queueSize() {
        return this.queueSize;
    }

    public final synchronized int receivedPingCount() {
        return this.receivedPingCount;
    }

    public final synchronized int receivedPongCount() {
        return this.receivedPongCount;
    }

    @Override // com.shadow.okhttp3.WebSocket
    public Request request() {
        return this.originalRequest;
    }

    @Override // com.shadow.okhttp3.WebSocket
    public boolean send(String str) {
        CloseableKt.checkNotNullParameter(str, "text");
        return send(ByteString.Companion.encodeUtf8(str), 1);
    }

    public final synchronized int sentPingCount() {
        return this.sentPingCount;
    }

    public final void tearDown() throws InterruptedException {
        this.taskQueue.shutdown();
        this.taskQueue.idleLatch().await(10L, TimeUnit.SECONDS);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x007c A[Catch: all -> 0x0085, TRY_ENTER, TryCatch #0 {all -> 0x0085, blocks: (B:28:0x007c, B:31:0x0087, B:33:0x008b, B:34:0x009b, B:36:0x00aa, B:39:0x00ad, B:40:0x00ae, B:41:0x00af, B:43:0x00b3, B:45:0x00c5, B:53:0x00dd, B:54:0x00e2, B:35:0x009c), top: B:64:0x007a, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:31:0x0087 A[Catch: all -> 0x0085, TryCatch #0 {all -> 0x0085, blocks: (B:28:0x007c, B:31:0x0087, B:33:0x008b, B:34:0x009b, B:36:0x00aa, B:39:0x00ad, B:40:0x00ae, B:41:0x00af, B:43:0x00b3, B:45:0x00c5, B:53:0x00dd, B:54:0x00e2, B:35:0x009c), top: B:64:0x007a, inners: #2 }] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00cf A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00d4 A[DONT_GENERATE] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00d9 A[DONT_GENERATE] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
    */
    public final boolean writeOneFrame$okhttp() throws IOException {
        Streams streams;
        String str;
        WebSocketReader webSocketReader;
        WebSocketWriter webSocketWriter;
        int i;
        synchronized (this) {
            try {
                if (this.failed) {
                    return false;
                }
                WebSocketWriter webSocketWriter2 = this.writer;
                ByteString byteStringPoll = this.pongQueue.poll();
                final boolean z = true;
                Object obj = null;
                try {
                    if (byteStringPoll == null) {
                        Object objPoll = this.messageAndCloseQueue.poll();
                        if (objPoll instanceof Close) {
                            i = this.receivedCloseCode;
                            str = this.receivedCloseReason;
                            if (i != -1) {
                                streams = this.streams;
                                this.streams = null;
                                webSocketReader = this.reader;
                                this.reader = null;
                                webSocketWriter = this.writer;
                                this.writer = null;
                                this.taskQueue.shutdown();
                            } else {
                                long cancelAfterCloseMillis = ((Close) objPoll).getCancelAfterCloseMillis();
                                TaskQueue taskQueue = this.taskQueue;
                                final String str2 = this.name + " cancel";
                                taskQueue.schedule(new Task(str2, z) { // from class: com.shadow.okhttp3.internal.ws.RealWebSocket$writeOneFrame$lambda$8$$inlined$execute$default$1
                                    @Override // com.shadow.okhttp3.internal.concurrent.Task
                                    public long runOnce() {
                                        this.cancel();
                                        return -1L;
                                    }
                                }, TimeUnit.MILLISECONDS.toNanos(cancelAfterCloseMillis));
                                streams = null;
                                webSocketReader = null;
                                webSocketWriter = null;
                            }
                            obj = objPoll;
                            if (byteStringPoll == null) {
                                CloseableKt.checkNotNull(webSocketWriter2);
                                webSocketWriter2.writePong(byteStringPoll);
                            } else if (obj instanceof Message) {
                                Message message = (Message) obj;
                                CloseableKt.checkNotNull(webSocketWriter2);
                                webSocketWriter2.writeMessageFrame(message.getFormatOpcode(), message.getData());
                                synchronized (this) {
                                    this.queueSize -= message.getData().size();
                                }
                            } else {
                                if (!(obj instanceof Close)) {
                                    throw new AssertionError();
                                }
                                Close close = (Close) obj;
                                CloseableKt.checkNotNull(webSocketWriter2);
                                webSocketWriter2.writeClose(close.getCode(), close.getReason());
                                if (streams != null) {
                                    WebSocketListener webSocketListener = this.listener;
                                    CloseableKt.checkNotNull(str);
                                    webSocketListener.onClosed(this, i, str);
                                }
                            }
                            return true;
                        }
                        if (objPoll == null) {
                            return false;
                        }
                        streams = null;
                        str = null;
                        webSocketReader = null;
                        webSocketWriter = null;
                        obj = objPoll;
                    } else {
                        streams = null;
                        str = null;
                        webSocketReader = null;
                        webSocketWriter = null;
                    }
                    if (byteStringPoll == null) {
                    }
                    return true;
                } finally {
                    if (streams != null) {
                        Util.closeQuietly(streams);
                    }
                    if (webSocketReader != null) {
                        Util.closeQuietly(webSocketReader);
                    }
                    if (webSocketWriter != null) {
                        Util.closeQuietly(webSocketWriter);
                    }
                }
                i = -1;
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final void writePingFrame$okhttp() throws IOException {
        synchronized (this) {
            try {
                if (this.failed) {
                    return;
                }
                WebSocketWriter webSocketWriter = this.writer;
                if (webSocketWriter == null) {
                    return;
                }
                int i = this.awaitingPong ? this.sentPingCount : -1;
                this.sentPingCount++;
                this.awaitingPong = true;
                if (i == -1) {
                    try {
                        webSocketWriter.writePing(ByteString.EMPTY);
                        return;
                    } catch (IOException e) {
                        failWebSocket(e, null);
                        return;
                    }
                }
                failWebSocket(new SocketTimeoutException("sent ping but didn't receive pong within " + this.pingIntervalMillis + "ms (after " + (i - 1) + " successful ping/pongs)"), null);
            } catch (Throwable th) {
                throw th;
            }
        }
    }

    public final synchronized boolean close(int i, String str, long j) {
        ByteString byteStringEncodeUtf8;
        try {
            WebSocketProtocol.INSTANCE.validateCloseCode(i);
            if (str != null) {
                byteStringEncodeUtf8 = ByteString.Companion.encodeUtf8(str);
                if (byteStringEncodeUtf8.size() > 123) {
                    throw new IllegalArgumentException("reason.size() > 123: ".concat(str).toString());
                }
            } else {
                byteStringEncodeUtf8 = null;
            }
            if (!this.failed && !this.enqueuedClose) {
                this.enqueuedClose = true;
                this.messageAndCloseQueue.add(new Close(i, byteStringEncodeUtf8, j));
                runWriter();
                return true;
            }
            return false;
        } finally {
        }
    }

    @Override // com.shadow.okhttp3.internal.ws.WebSocketReader.FrameCallback
    public void onReadMessage(ByteString byteString) throws IOException {
        CloseableKt.checkNotNullParameter(byteString, "bytes");
        this.listener.onMessage(this, byteString);
    }

    @Override // com.shadow.okhttp3.WebSocket
    public boolean send(ByteString byteString) {
        CloseableKt.checkNotNullParameter(byteString, "bytes");
        return send(byteString, 2);
    }

    private final synchronized boolean send(ByteString byteString, int i) {
        if (!this.failed && !this.enqueuedClose) {
            if (this.queueSize + byteString.size() > MAX_QUEUE_SIZE) {
                close(WebSocketProtocol.CLOSE_CLIENT_GOING_AWAY, null);
                return false;
            }
            this.queueSize += byteString.size();
            this.messageAndCloseQueue.add(new Message(i, byteString));
            runWriter();
            return true;
        }
        return false;
    }
}
