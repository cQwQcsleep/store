package net.schmizz.sshj.connection.channel.direct;

import java.io.InputStream;
import java.util.Map;
import net.schmizz.sshj.connection.ConnectionException;
import net.schmizz.sshj.connection.channel.Channel;
import net.schmizz.sshj.transport.TransportException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface Session extends Channel {

    public interface Command extends Channel {
        InputStream getErrorStream();

        String getExitErrorMessage();

        Signal getExitSignal();

        Integer getExitStatus();

        Boolean getExitWasCoreDumped();

        void signal(Signal signal) throws TransportException;
    }

    public interface Shell extends Channel {
        Boolean canDoFlowControl();

        void changeWindowDimensions(int i, int i2, int i3, int i4) throws TransportException;

        InputStream getErrorStream();

        void signal(Signal signal) throws TransportException;
    }

    public interface Subsystem extends Channel {
        Integer getExitStatus();
    }

    void allocateDefaultPTY() throws ConnectionException, TransportException;

    void allocatePTY(String str, int i, int i2, int i3, int i4, Map<PTYMode, Integer> map) throws ConnectionException, TransportException;

    Command exec(String str) throws ConnectionException, TransportException;

    void reqX11Forwarding(String str, String str2, int i) throws ConnectionException, TransportException;

    void setEnvVar(String str, String str2) throws ConnectionException, TransportException;

    Shell startShell() throws ConnectionException, TransportException;

    Subsystem startSubsystem(String str) throws ConnectionException, TransportException;
}
