package net.schmizz.sshj.common;

import java.io.IOException;
import net.schmizz.concurrent.ExceptionChainer;
import okhttp3.HttpUrl;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class SSHException extends IOException {
    public static final ExceptionChainer<SSHException> chainer = new ExceptionChainer<SSHException>() { // from class: net.schmizz.sshj.common.SSHException.1
        @Override // net.schmizz.concurrent.ExceptionChainer
        public SSHException chain(Throwable th) {
            return th instanceof SSHException ? (SSHException) th : new SSHException(th);
        }
    };
    private final DisconnectReason reason;

    public SSHException(DisconnectReason disconnectReason, String str, Throwable th) {
        super(str);
        this.reason = disconnectReason;
        if (th != null) {
            initCause(th);
        }
    }

    public DisconnectReason getDisconnectReason() {
        return this.reason;
    }

    @Override // java.lang.Throwable
    public String getMessage() {
        if (super.getMessage() != null) {
            return super.getMessage();
        }
        if (getCause() == null || getCause().getMessage() == null) {
            return null;
        }
        return getCause().getMessage();
    }

    @Override // java.lang.Throwable
    public String toString() {
        String str;
        String name = getClass().getName();
        DisconnectReason disconnectReason = this.reason;
        DisconnectReason disconnectReason2 = DisconnectReason.UNKNOWN;
        String str2 = HttpUrl.FRAGMENT_ENCODE_SET;
        if (disconnectReason != disconnectReason2) {
            str = "[" + this.reason + "] ";
        } else {
            str = HttpUrl.FRAGMENT_ENCODE_SET;
        }
        String message = getMessage() != null ? getMessage() : HttpUrl.FRAGMENT_ENCODE_SET;
        StringBuilder sb = new StringBuilder();
        sb.append(name);
        if (!str.isEmpty() || !message.isEmpty()) {
            str2 = ": ";
        }
        sb.append(str2);
        sb.append(str);
        sb.append(message);
        return sb.toString();
    }

    public SSHException(DisconnectReason disconnectReason, String str) {
        this(disconnectReason, str, null);
    }

    public SSHException(DisconnectReason disconnectReason) {
        this(disconnectReason, null, null);
    }

    public SSHException(DisconnectReason disconnectReason, Throwable th) {
        this(disconnectReason, null, th);
    }

    public SSHException(String str) {
        this(DisconnectReason.UNKNOWN, str, null);
    }

    public SSHException(String str, Throwable th) {
        this(DisconnectReason.UNKNOWN, str, th);
    }

    public SSHException(Throwable th) {
        this(DisconnectReason.UNKNOWN, null, th);
    }
}
