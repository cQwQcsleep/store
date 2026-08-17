package net.schmizz.sshj.xfer;

import java.io.IOException;
import java.io.InputStream;

/* JADX INFO: loaded from: /workspace/dex_all/classes9.dex */
public interface LocalSourceFile {
    Iterable<? extends LocalSourceFile> getChildren(LocalFileFilter localFileFilter) throws IOException;

    InputStream getInputStream() throws IOException;

    long getLastAccessTime() throws IOException;

    long getLastModifiedTime() throws IOException;

    long getLength();

    String getName();

    int getPermissions() throws IOException;

    boolean isDirectory();

    boolean isFile();

    boolean providesAtimeMtime();
}
