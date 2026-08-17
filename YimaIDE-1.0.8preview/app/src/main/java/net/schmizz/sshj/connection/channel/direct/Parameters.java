package net.schmizz.sshj.connection.channel.direct;

import java.util.Objects;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public class Parameters {
    private final String localHost;
    private final int localPort;
    private final String remoteHost;
    private final int remotePort;

    public Parameters(String str, int i, String str2, int i2) {
        this.localHost = str;
        this.localPort = i;
        this.remoteHost = str2;
        this.remotePort = i2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Parameters)) {
            return false;
        }
        Parameters parameters = (Parameters) obj;
        return Objects.equals(this.localHost, parameters.localHost) && this.localPort == parameters.localPort && Objects.equals(this.remoteHost, parameters.remoteHost) && this.remotePort == parameters.remotePort;
    }

    public String getLocalHost() {
        return this.localHost;
    }

    public int getLocalPort() {
        return this.localPort;
    }

    public String getRemoteHost() {
        return this.remoteHost;
    }

    public int getRemotePort() {
        return this.remotePort;
    }

    public int hashCode() {
        return Objects.hash(this.localHost, Integer.valueOf(this.localPort), this.remoteHost, Integer.valueOf(this.remotePort));
    }

    public String toString() {
        return "Parameters [localHost=" + this.localHost + ", localPort=" + this.localPort + ", remoteHost=" + this.remoteHost + ", remotePort=" + this.remotePort + "]";
    }
}
