package com.sun.nio.zipfs;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.ReadableByteChannel;
import java.nio.channels.WritableByteChannel;
import java.nio.file.Files;
import java.nio.file.Path;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class ZipFileSystem$4 extends FileChannel {
    final /* synthetic */ ZipFileSystem this$0;
    final /* synthetic */ FileChannel val$fch;
    final /* synthetic */ boolean val$forWrite;
    final /* synthetic */ boolean val$isFCH;
    final /* synthetic */ Path val$tmpfile;
    final /* synthetic */ ZipFileSystem.Entry val$u;

    public ZipFileSystem$4(ZipFileSystem zipFileSystem, FileChannel fileChannel, boolean z, ZipFileSystem.Entry entry, boolean z2, Path path) {
        this.this$0 = zipFileSystem;
        this.val$fch = fileChannel;
        this.val$forWrite = z;
        this.val$u = entry;
        this.val$isFCH = z2;
        this.val$tmpfile = path;
    }

    @Override // java.nio.channels.FileChannel
    public void force(boolean z) throws IOException {
        this.val$fch.force(z);
    }

    @Override // java.nio.channels.spi.AbstractInterruptibleChannel
    public void implCloseChannel() throws IOException {
        this.val$fch.close();
        if (!this.val$forWrite) {
            if (this.val$isFCH) {
                return;
            }
            ZipFileSystem.access$100(this.this$0, this.val$tmpfile);
        } else {
            this.val$u.mtime = System.currentTimeMillis();
            ZipFileSystem.Entry entry = this.val$u;
            entry.size = Files.size(entry.file);
            ZipFileSystem.access$000(this.this$0, this.val$u);
        }
    }

    @Override // java.nio.channels.FileChannel
    public FileLock lock(long j, long j2, boolean z) throws IOException {
        return this.val$fch.lock(j, j2, z);
    }

    @Override // java.nio.channels.FileChannel
    public MappedByteBuffer map(FileChannel.MapMode mapMode, long j, long j2) throws IOException {
        throw new UnsupportedOperationException();
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel
    public long position() throws IOException {
        return this.val$fch.position();
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel, java.nio.channels.ReadableByteChannel
    public int read(ByteBuffer byteBuffer) throws IOException {
        return this.val$fch.read(byteBuffer);
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel
    public long size() throws IOException {
        return this.val$fch.size();
    }

    @Override // java.nio.channels.FileChannel
    public long transferFrom(ReadableByteChannel readableByteChannel, long j, long j2) throws IOException {
        return this.val$fch.transferFrom(readableByteChannel, j, j2);
    }

    @Override // java.nio.channels.FileChannel
    public long transferTo(long j, long j2, WritableByteChannel writableByteChannel) throws IOException {
        return this.val$fch.transferTo(j, j2, writableByteChannel);
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel
    public FileChannel truncate(long j) throws IOException {
        this.val$fch.truncate(j);
        return this;
    }

    @Override // java.nio.channels.FileChannel
    public FileLock tryLock(long j, long j2, boolean z) throws IOException {
        return this.val$fch.tryLock(j, j2, z);
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel, java.nio.channels.WritableByteChannel
    public int write(ByteBuffer byteBuffer) throws IOException {
        return this.val$fch.write(byteBuffer);
    }

    @Override // java.nio.channels.FileChannel
    public int read(ByteBuffer byteBuffer, long j) throws IOException {
        return this.val$fch.read(byteBuffer, j);
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.GatheringByteChannel
    public long write(ByteBuffer[] byteBufferArr, int i, int i2) throws IOException {
        return this.val$fch.write(byteBufferArr, i, i2);
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.SeekableByteChannel
    public FileChannel position(long j) throws IOException {
        this.val$fch.position(j);
        return this;
    }

    @Override // java.nio.channels.FileChannel, java.nio.channels.ScatteringByteChannel
    public long read(ByteBuffer[] byteBufferArr, int i, int i2) throws IOException {
        return this.val$fch.read(byteBufferArr, i, i2);
    }

    @Override // java.nio.channels.FileChannel
    public int write(ByteBuffer byteBuffer, long j) throws IOException {
        return this.val$fch.write(byteBuffer, j);
    }
}
