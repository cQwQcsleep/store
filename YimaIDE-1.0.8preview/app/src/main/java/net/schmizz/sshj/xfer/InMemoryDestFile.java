package net.schmizz.sshj.xfer;

import java.io.IOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public abstract class InMemoryDestFile implements LocalDestFile {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Override // net.schmizz.sshj.xfer.LocalDestFile
    public LocalDestFile getChild(String str) {
        throw new AssertionError("Unimplemented");
    }

    @Override // net.schmizz.sshj.xfer.LocalDestFile
    public LocalDestFile getTargetDirectory(String str) throws IOException {
        throw new AssertionError("Unimplemented");
    }

    @Override // net.schmizz.sshj.xfer.LocalDestFile
    public void setLastAccessedTime(long j) throws IOException {
        this.log.info("atime = {}", Long.valueOf(j));
    }

    @Override // net.schmizz.sshj.xfer.LocalDestFile
    public void setLastModifiedTime(long j) throws IOException {
        this.log.info("mtime = {}", Long.valueOf(j));
    }

    @Override // net.schmizz.sshj.xfer.LocalDestFile
    public void setPermissions(int i) throws IOException {
        this.log.info("permissions = {}", Integer.toOctalString(i));
    }

    @Override // net.schmizz.sshj.xfer.LocalDestFile
    public InMemoryDestFile getTargetFile(String str) throws IOException {
        return this;
    }
}
