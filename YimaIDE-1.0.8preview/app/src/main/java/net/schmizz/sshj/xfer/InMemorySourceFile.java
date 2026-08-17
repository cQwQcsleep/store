package net.schmizz.sshj.xfer;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public abstract class InMemorySourceFile implements LocalSourceFile {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Override // net.schmizz.sshj.xfer.LocalSourceFile
    public Iterable<? extends LocalSourceFile> getChildren(LocalFileFilter localFileFilter) throws IOException {
        throw new AssertionError("Unimplemented");
    }

    @Override // net.schmizz.sshj.xfer.LocalSourceFile
    public long getLastAccessTime() throws IOException {
        throw new AssertionError("Unimplemented");
    }

    @Override // net.schmizz.sshj.xfer.LocalSourceFile
    public long getLastModifiedTime() throws IOException {
        throw new AssertionError("Unimplemented");
    }

    @Override // net.schmizz.sshj.xfer.LocalSourceFile
    public int getPermissions() throws IOException {
        return 420;
    }

    @Override // net.schmizz.sshj.xfer.LocalSourceFile
    public boolean isDirectory() {
        return false;
    }

    @Override // net.schmizz.sshj.xfer.LocalSourceFile
    public boolean isFile() {
        return true;
    }

    @Override // net.schmizz.sshj.xfer.LocalSourceFile
    public boolean providesAtimeMtime() {
        return false;
    }
}
