package com.intellij.util.io;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.SystemProperties;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
final class FileChannelWithSizeTracking {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger LOG = Logger.getInstance(FileChannelWithSizeTracking.class);
    private static final boolean doAssertions = SystemProperties.getBooleanProperty("idea.do.random.access.wrapper.assertions", false);
    private final ResilientFileChannel fileChannel;
    private final Path myPath;
    private volatile long mySize;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "path", "com/intellij/util/io/FileChannelWithSizeTracking", "<init>"));
    }

    public FileChannelWithSizeTracking(Path path) throws IOException {
        if (path == null) {
            $$$reportNull$$$0(0);
        }
        Path parent = path.getParent();
        if (!Files.exists(parent, new LinkOption[0])) {
            Files.createDirectories(parent, new FileAttribute[0]);
        }
        this.myPath = path;
        ResilientFileChannel resilientFileChannel = new ResilientFileChannel(path, StandardOpenOption.READ, StandardOpenOption.WRITE, StandardOpenOption.CREATE);
        this.fileChannel = resilientFileChannel;
        this.mySize = resilientFileChannel.size();
        Logger logger = LOG;
        if (logger.isTraceEnabled()) {
            logger.trace("Inst:" + this + "," + Thread.currentThread() + "," + FileChannelWithSizeTracking.class.getClassLoader());
        }
    }

    public void close() throws IOException {
        Logger logger = LOG;
        if (logger.isTraceEnabled()) {
            logger.trace("Closed:" + this + "," + Thread.currentThread());
        }
        this.fileChannel.close();
    }

    public void force() throws IOException {
        Logger logger = LOG;
        if (logger.isTraceEnabled()) {
            logger.trace("Forcing:" + this + "," + Thread.currentThread());
        }
        this.fileChannel.force(true);
    }

    public long length() throws IOException {
        return this.mySize;
    }

    public void read(long j, byte[] bArr, int i, int i2) throws IOException {
        Logger logger = LOG;
        if (logger.isTraceEnabled()) {
            logger.trace("read:" + this + "," + Thread.currentThread() + "," + i2 + "," + j);
        }
        this.fileChannel.read(ByteBuffer.wrap(bArr, i, i2), j);
    }

    public String toString() {
        return this.myPath + "@" + Integer.toHexString(hashCode());
    }

    public void write(long j, byte[] bArr, int i, int i2) throws IOException {
        Logger logger = LOG;
        if (logger.isTraceEnabled()) {
            logger.trace("write:" + this + "," + Thread.currentThread() + "," + i2 + "," + j);
        }
        this.mySize = Math.max(((long) this.fileChannel.write(ByteBuffer.wrap(bArr, i, i2), j)) + j, length());
    }
}
