package com.sun.nio.zipfs;

import com.sun.org.apache.xpath.internal.compiler.PsuedoNames;
import java.io.IOException;
import java.nio.file.FileStore;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.FileAttributeView;
import java.nio.file.attribute.FileStoreAttributeView;

/* JADX INFO: loaded from: /workspace/dex_all/classes7.dex */
public class ZipFileStore extends FileStore {
    private final ZipFileSystem zfs;

    public static class ZipFileStoreAttributes {
        final FileStore fstore;
        final long size;

        public ZipFileStoreAttributes(ZipFileStore zipFileStore) throws IOException {
            Path path = FileSystems.getDefault().getPath(zipFileStore.name(), new String[0]);
            this.size = Files.size(path);
            this.fstore = Files.getFileStore(path);
        }

        public long totalSpace() {
            return this.size;
        }

        public long unallocatedSpace() throws IOException {
            if (this.fstore.isReadOnly()) {
                return 0L;
            }
            return this.fstore.getUnallocatedSpace();
        }

        public long usableSpace() throws IOException {
            if (this.fstore.isReadOnly()) {
                return 0L;
            }
            return this.fstore.getUsableSpace();
        }
    }

    public ZipFileStore(ZipPath zipPath) {
        this.zfs = zipPath.getFileSystem();
    }

    @Override // java.nio.file.FileStore
    public Object getAttribute(String str) throws IOException {
        if (str.equals("totalSpace")) {
            return Long.valueOf(getTotalSpace());
        }
        if (str.equals("usableSpace")) {
            return Long.valueOf(getUsableSpace());
        }
        if (str.equals("unallocatedSpace")) {
            return Long.valueOf(getUnallocatedSpace());
        }
        c41.a("does not support the given attribute");
        return null;
    }

    @Override // java.nio.file.FileStore
    public <V extends FileStoreAttributeView> V getFileStoreAttributeView(Class<V> cls) {
        cls.getClass();
        return null;
    }

    @Override // java.nio.file.FileStore
    public long getTotalSpace() throws IOException {
        return new ZipFileStoreAttributes(this).totalSpace();
    }

    @Override // java.nio.file.FileStore
    public long getUnallocatedSpace() throws IOException {
        return new ZipFileStoreAttributes(this).unallocatedSpace();
    }

    @Override // java.nio.file.FileStore
    public long getUsableSpace() throws IOException {
        return new ZipFileStoreAttributes(this).usableSpace();
    }

    @Override // java.nio.file.FileStore
    public boolean isReadOnly() {
        return this.zfs.isReadOnly();
    }

    @Override // java.nio.file.FileStore
    public String name() {
        return this.zfs.toString() + PsuedoNames.PSEUDONAME_ROOT;
    }

    @Override // java.nio.file.FileStore
    public boolean supportsFileAttributeView(String str) {
        return str.equals("basic") || str.equals("zip");
    }

    @Override // java.nio.file.FileStore
    public String type() {
        return "zipfs";
    }

    @Override // java.nio.file.FileStore
    public boolean supportsFileAttributeView(Class<? extends FileAttributeView> cls) {
        return cls == BasicFileAttributeView.class || cls == ZipFileAttributeView.class;
    }
}
