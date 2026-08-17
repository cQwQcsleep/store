package net.schmizz.sshj.common;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class SSHRuntimeException extends RuntimeException {
    public SSHRuntimeException(String str, Throwable th) {
        super(str);
        if (th != null) {
            initCause(th);
        }
    }

    public SSHRuntimeException(String str) {
        this(str, null);
    }

    public SSHRuntimeException() {
        this(null, null);
    }

    public SSHRuntimeException(Throwable th) {
        this(th.getMessage(), th);
    }
}
