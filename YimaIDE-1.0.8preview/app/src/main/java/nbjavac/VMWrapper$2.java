package nbjavac;

import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystem;
import java.nio.file.Path;
import java.nio.file.PathMatcher;
import java.nio.file.WatchService;
import java.nio.file.attribute.UserPrincipalLookupService;
import java.nio.file.spi.FileSystemProvider;
import java.util.Collections;
import java.util.Set;

/* JADX INFO: loaded from: /workspace/dex_all/classes8.dex */
public class VMWrapper$2 extends FileSystem {
    final /* synthetic */ Path val$p;

    public VMWrapper$2(Path path) {
        this.val$p = path;
    }

    @Override // java.nio.file.FileSystem, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws IOException {
    }

    @Override // java.nio.file.FileSystem
    public Iterable<FileStore> getFileStores() {
        return this.val$p.getFileSystem().getFileStores();
    }

    @Override // java.nio.file.FileSystem
    public Path getPath(String str, String... strArr) {
        Path pathResolve = this.val$p.resolve(str);
        for (String str2 : strArr) {
            pathResolve = pathResolve.resolve(str2);
        }
        return pathResolve;
    }

    @Override // java.nio.file.FileSystem
    public PathMatcher getPathMatcher(String str) {
        return this.val$p.getFileSystem().getPathMatcher(str);
    }

    @Override // java.nio.file.FileSystem
    public Iterable<Path> getRootDirectories() {
        return Collections.singleton(this.val$p);
    }

    @Override // java.nio.file.FileSystem
    public String getSeparator() {
        return this.val$p.getFileSystem().getSeparator();
    }

    @Override // java.nio.file.FileSystem
    public UserPrincipalLookupService getUserPrincipalLookupService() {
        return this.val$p.getFileSystem().getUserPrincipalLookupService();
    }

    @Override // java.nio.file.FileSystem
    public boolean isOpen() {
        return this.val$p.getFileSystem().isOpen();
    }

    @Override // java.nio.file.FileSystem
    public boolean isReadOnly() {
        return this.val$p.getFileSystem().isReadOnly();
    }

    @Override // java.nio.file.FileSystem
    public WatchService newWatchService() throws IOException {
        return this.val$p.getFileSystem().newWatchService();
    }

    @Override // java.nio.file.FileSystem
    public FileSystemProvider provider() {
        return this.val$p.getFileSystem().provider();
    }

    @Override // java.nio.file.FileSystem
    public Set<String> supportedFileAttributeViews() {
        return this.val$p.getFileSystem().supportedFileAttributeViews();
    }
}
