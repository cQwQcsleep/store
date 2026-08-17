package net.schmizz.sshj.connection;

import net.schmizz.concurrent.ExceptionChainer;
import net.schmizz.sshj.common.DisconnectReason;
import net.schmizz.sshj.common.SSHException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class ConnectionException extends SSHException {
    public static final ExceptionChainer<ConnectionException> chainer = new ExceptionChainer<ConnectionException>() { // from class: net.schmizz.sshj.connection.ConnectionException.1
        @Override // net.schmizz.concurrent.ExceptionChainer
        public ConnectionException chain(Throwable th) {
            return th instanceof ConnectionException ? (ConnectionException) th : new ConnectionException(th);
        }
    };

    public ConnectionException(DisconnectReason disconnectReason) {
        super(disconnectReason);
    }

    public ConnectionException(DisconnectReason disconnectReason, String str) {
        super(disconnectReason, str);
    }

    public ConnectionException(DisconnectReason disconnectReason, String str, Throwable th) {
        super(disconnectReason, str, th);
    }

    public ConnectionException(DisconnectReason disconnectReason, Throwable th) {
        super(disconnectReason, th);
    }

    public ConnectionException(String str) {
        super(str);
    }

    public ConnectionException(String str, Throwable th) {
        super(str, th);
    }

    public ConnectionException(Throwable th) {
        super(th);
    }
}
