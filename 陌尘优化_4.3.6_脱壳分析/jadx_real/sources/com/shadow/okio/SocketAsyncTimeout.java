package com.shadow.okio;

import com.shadow.kotlin.io.CloseableKt;
import java.io.IOException;
import java.net.Socket;
import java.net.SocketTimeoutException;
import java.util.logging.Level;

/* loaded from: /workspace/unpacked/classes2.dex */
final class SocketAsyncTimeout extends AsyncTimeout {
    private final Socket socket;

    public SocketAsyncTimeout(Socket socket) {
        CloseableKt.checkNotNullParameter(socket, "socket");
        this.socket = socket;
    }

    @Override // com.shadow.okio.AsyncTimeout
    public IOException newTimeoutException(IOException iOException) {
        SocketTimeoutException socketTimeoutException = new SocketTimeoutException("timeout");
        if (iOException != null) {
            socketTimeoutException.initCause(iOException);
        }
        return socketTimeoutException;
    }

    @Override // com.shadow.okio.AsyncTimeout
    public void timedOut() throws IOException {
        try {
            this.socket.close();
        } catch (AssertionError e) {
            if (!Okio.isAndroidGetsocknameError(e)) {
                throw e;
            }
            Okio__JvmOkioKt.logger.log(Level.WARNING, "Failed to close timed out socket " + this.socket, (Throwable) e);
        } catch (Exception e2) {
            Okio__JvmOkioKt.logger.log(Level.WARNING, "Failed to close timed out socket " + this.socket, (Throwable) e2);
        }
    }
}
