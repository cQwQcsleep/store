package net.schmizz.sshj.connection.channel.direct;

import com.hierynomus.sshj.common.RemoteAddressProvider;
import net.schmizz.sshj.common.SSHException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface SessionFactory extends RemoteAddressProvider {
    Session startSession() throws SSHException;
}
