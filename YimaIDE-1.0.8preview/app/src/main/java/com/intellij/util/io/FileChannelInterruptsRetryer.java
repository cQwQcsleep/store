package com.intellij.util.io;

import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.ConcurrencyUtil;
import com.intellij.util.SystemProperties;
import com.intellij.util.ThrowableRunnable;
import com.intellij.util.io.FileChannelInterruptsRetryer;
import java.io.IOException;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.FileChannel;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class FileChannelInterruptsRetryer implements AutoCloseable {
    private volatile FileChannel channel;
    private final Lock openCloseLock;
    private final Set<? extends OpenOption> openOptions;
    private final Path path;
    private static final Logger LOG = Logger.getInstance(FileChannelInterruptsRetryer.class);
    public static final int MAX_RETRIES = SystemProperties.getIntProperty("idea.vfs.FileChannelInterruptsRetryer.MAX_RETRIES", 64);
    private static final int LOG_STACKTRACE_IF_RETRY_CHAIN_LONGER = SystemProperties.getIntProperty("idea.vfs.LOG_STACKTRACE_IF_RETRY_CHAIN_LONGER", 32);
    private static final AtomicLong totalRetriedAttempts = new AtomicLong();

    public interface FileChannelIdempotentOperation<T> {
        T execute(FileChannel fileChannel) throws IOException;
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        Object[] objArr = new Object[3];
        if (i != 1) {
            objArr[0] = "path";
        } else {
            objArr[0] = "operation";
        }
        objArr[1] = "com/intellij/util/io/FileChannelInterruptsRetryer";
        if (i != 1) {
            objArr[2] = "<init>";
        } else {
            objArr[2] = "retryIfInterrupted";
        }
        throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", objArr));
    }

    public FileChannelInterruptsRetryer(Path path, Set<? extends OpenOption> set) throws Throwable {
        if (path == null) {
            $$$reportNull$$$0(0);
        }
        this.openCloseLock = new ReentrantLock();
        this.path = path;
        this.openOptions = set;
        reopenChannel();
    }

    public static /* synthetic */ void a(FileChannelInterruptsRetryer fileChannelInterruptsRetryer) {
        fileChannelInterruptsRetryer.getClass();
        try {
            fileChannelInterruptsRetryer.tryClose();
        } catch (IOException e) {
            LOG.info("Can't close channel[" + fileChannelInterruptsRetryer.path + "]: " + e.getMessage());
        }
        fileChannelInterruptsRetryer.channel = FileChannel.open(fileChannelInterruptsRetryer.path, fileChannelInterruptsRetryer.openOptions, new FileAttribute[0]);
    }

    private void reopenChannel() throws Throwable {
        ConcurrencyUtil.withLock(this.openCloseLock, new ThrowableRunnable() { // from class: zo4
            @Override // com.intellij.util.ThrowableRunnable
            public final void run() {
                FileChannelInterruptsRetryer.a(this.a);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void tryClose() throws IOException {
        try {
            FileChannel fileChannel = this.channel;
            if (fileChannel != null && fileChannel.isOpen()) {
                fileChannel.close();
            }
        } finally {
            this.channel = null;
        }
    }

    @Override // java.lang.AutoCloseable
    public void close() throws Throwable {
        ConcurrencyUtil.withLock(this.openCloseLock, new ThrowableRunnable() { // from class: yo4
            @Override // com.intellij.util.ThrowableRunnable
            public final void run() throws IOException {
                this.a.tryClose();
            }
        });
    }

    public <T> T retryIfInterrupted(FileChannelIdempotentOperation<T> fileChannelIdempotentOperation) throws IOException {
        if (fileChannelIdempotentOperation == null) {
            $$$reportNull$$$0(1);
        }
        int i = 0;
        boolean z = false;
        while (true) {
            try {
                FileChannel fileChannel = this.channel;
                if (fileChannel == null && i == 0) {
                    throw new ClosedChannelException();
                }
                try {
                    if (fileChannel == null && i >= 0) {
                        throw new ClosedChannelException();
                    }
                    T tExecute = fileChannelIdempotentOperation.execute(fileChannel);
                    if (z) {
                        Thread.currentThread().interrupt();
                    }
                    return tExecute;
                } catch (ClosedChannelException e) {
                    totalRetriedAttempts.incrementAndGet();
                    int i2 = MAX_RETRIES;
                    if (i >= i2) {
                        IOException iOException = new IOException("Channel[" + this.path + "][@" + System.identityHashCode(fileChannel) + "] is interrupted/closed in the middle of operation " + i2 + " times in the row: surrender");
                        iOException.addSuppressed(e);
                        throw iOException;
                    }
                    int i3 = LOG_STACKTRACE_IF_RETRY_CHAIN_LONGER;
                    if (i3 <= 0 || i % i3 != i3 - 1) {
                        LOG.warn("Channel[" + this.path + "][@" + System.identityHashCode(fileChannel) + "] is closed during " + fileChannelIdempotentOperation + " => trying to reopen it again. Reason: " + e);
                    } else {
                        LOG.warn("Channel[" + this.path + "][@" + System.identityHashCode(fileChannel) + "] is closed during " + fileChannelIdempotentOperation + " " + i3 + " times in a row -- suspicious, log stacktrace", e);
                    }
                    if (Thread.currentThread().isInterrupted()) {
                        Thread.interrupted();
                        z = true;
                    }
                    reopenChannel();
                    i++;
                }
            } catch (Throwable th) {
                if (z) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
    }
}
