package net.schmizz.sshj.xfer.scp;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class SCPRemoteException extends SCPException {
    private final String remoteMessage;

    public SCPRemoteException(String str, String str2) {
        super(str);
        this.remoteMessage = str2;
    }

    public String getRemoteMessage() {
        return this.remoteMessage;
    }
}
