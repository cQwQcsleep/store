package net.schmizz.sshj.transport.compression;

import net.schmizz.sshj.common.Buffer;
import net.schmizz.sshj.transport.TransportException;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface Compression {

    public enum Mode {
        INFLATE,
        DEFLATE
    }

    void compress(Buffer buffer);

    void init(Mode mode);

    boolean isDelayed();

    void uncompress(Buffer buffer, Buffer buffer2) throws TransportException;
}
