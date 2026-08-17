package net.schmizz.sshj.xfer;

import java.io.IOException;
import java.io.OutputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface LocalDestFile {
    LocalDestFile getChild(String str);

    long getLength();

    OutputStream getOutputStream() throws IOException;

    OutputStream getOutputStream(boolean z) throws IOException;

    LocalDestFile getTargetDirectory(String str) throws IOException;

    LocalDestFile getTargetFile(String str) throws IOException;

    void setLastAccessedTime(long j) throws IOException;

    void setLastModifiedTime(long j) throws IOException;

    void setPermissions(int i) throws IOException;
}
