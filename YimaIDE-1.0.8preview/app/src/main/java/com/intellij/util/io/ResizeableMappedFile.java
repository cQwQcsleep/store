package com.intellij.util.io;

import androidx.collection.ScatterMapKt;
import androidx.collection.SieveCacheKt;
import com.intellij.openapi.Forceable;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.ThrowableNotNullFunction;
import com.intellij.openapi.util.io.FileUtilRt;
import com.intellij.util.ExceptionUtil;
import com.intellij.util.SystemProperties;
import com.intellij.util.ThrowableRunnable;
import com.intellij.util.io.ResizeableMappedFile;
import defpackage.agc;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.NoSuchFileException;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.nio.file.attribute.FileAttribute;
import java.util.Objects;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class ResizeableMappedFile implements Forceable, Closeable {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final Logger LOG = Logger.getInstance(ResizeableMappedFile.class);
    private final int initialSize;
    private volatile long lastWrittenLogicalSize;
    private volatile long logicalSize;
    private int roundingFactor;
    private final PagedFileStorage storage;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 3 || i == 4 || i == 6 || i == 8) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 3 || i == 4 || i == 6 || i == 8) ? 2 : 3];
        switch (i) {
            case 2:
                objArr[0] = "buffer";
                break;
            case 3:
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 8:
                objArr[0] = "com/intellij/util/io/ResizeableMappedFile";
                break;
            case 5:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "consumer";
                break;
            default:
                objArr[0] = "file";
                break;
        }
        if (i == 3) {
            objArr[1] = "getPagedFileStorage";
        } else if (i == 4) {
            objArr[1] = "getStorageLockContext";
        } else if (i == 6) {
            objArr[1] = "readInputStream";
        } else if (i != 8) {
            objArr[1] = "com/intellij/util/io/ResizeableMappedFile";
        } else {
            objArr[1] = "readChannel";
        }
        switch (i) {
            case 2:
                objArr[2] = "put";
                break;
            case 3:
            case 4:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 8:
                break;
            case 5:
                objArr[2] = "readInputStream";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "readChannel";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 3 && i != 4 && i != 6 && i != 8) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public ResizeableMappedFile(Path path, int i, StorageLockContext storageLockContext, int i2, boolean z, boolean z2) throws Throwable {
        if (path == null) {
            $$$reportNull$$$0(1);
        }
        this.roundingFactor = 4096;
        this.initialSize = i;
        PagedFileStorage pagedFileStorage = new PagedFileStorage(path, storageLockContext, i2, z, z2);
        this.storage = pagedFileStorage;
        try {
            Path file = pagedFileStorage.getFile();
            Path pathDeriveLengthFile = deriveLengthFile();
            long length = pagedFileStorage.length();
            if (!Files.exists(pathDeriveLengthFile, new LinkOption[0]) && length == 0) {
                this.logicalSize = 0L;
                this.lastWrittenLogicalSize = 0L;
                writeLogicalSize(0L);
                return;
            }
            long logicalSize = readLogicalSize();
            this.logicalSize = logicalSize;
            this.lastWrittenLogicalSize = logicalSize;
            if (this.lastWrittenLogicalSize > length) {
                LOG.warn("[" + file.toAbsolutePath() + "] inconsistency: realFileSize(=" + length + "b) > logicalSize(=" + this.lastWrittenLogicalSize + "b) -- storage file was removed/truncated? => resetting logical size to real size");
                this.logicalSize = length;
                this.lastWrittenLogicalSize = length;
                writeLogicalSize(length);
            }
        } catch (Throwable th) {
            PagedFileStorage pagedFileStorage2 = this.storage;
            Objects.requireNonNull(pagedFileStorage2);
            Exception excRunAndCatch = ExceptionUtil.runAndCatch(new agc(pagedFileStorage2));
            if (excRunAndCatch == null) {
                throw th;
            }
            th.addSuppressed(excRunAndCatch);
            throw th;
        }
    }

    public static /* synthetic */ IOException a(ResizeableMappedFile resizeableMappedFile) {
        return new IOException("Failed to close ResizableMappedFile[" + resizeableMappedFile.getPagedFileStorage().getFile() + "]");
    }

    public static /* synthetic */ void c(ResizeableMappedFile resizeableMappedFile) throws IOException {
        resizeableMappedFile.ensureLogicalSizeWritten();
        resizeableMappedFile.storage.force();
        if (!SystemProperties.getBooleanProperty("idea.resizeable.file.truncate.on.close", false) || resizeableMappedFile.logicalSize >= resizeableMappedFile.storage.length()) {
            return;
        }
        resizeableMappedFile.storage.resize(resizeableMappedFile.logicalSize);
    }

    public static /* synthetic */ Boolean d(ResizeableMappedFile resizeableMappedFile, Path path, long j, boolean z) throws IOException {
        resizeableMappedFile.getClass();
        try {
            DataOutputStream dataOutputStream = new DataOutputStream(Files.newOutputStream(path, new OpenOption[0]));
            try {
                dataOutputStream.writeLong(j);
                Boolean bool = Boolean.TRUE;
                dataOutputStream.close();
                return bool;
            } catch (Throwable th) {
                try {
                    dataOutputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e) {
            if (e instanceof NoSuchFileException) {
                resizeableMappedFile.ensureParentDirectoryExists();
            }
            if (z) {
                throw e;
            }
            return null;
        }
    }

    private Path deriveLengthFile() {
        Path file = this.storage.getFile();
        return file.resolveSibling(file.getFileName() + ".len");
    }

    private long doRoundToFactor(long j) {
        long j2 = this.roundingFactor;
        return j % j2 != 0 ? ((j / j2) + 1) * j2 : j;
    }

    private void ensureLogicalSizeWritten() {
        if (this.lastWrittenLogicalSize != this.logicalSize) {
            writeLogicalSize(this.logicalSize);
            this.lastWrittenLogicalSize = this.logicalSize;
        }
    }

    private void ensureParentDirectoryExists() throws IOException {
        Files.createDirectories(this.storage.getFile().getParent(), new FileAttribute[0]);
    }

    private void expand(long j) {
        long jDoRoundToFactor;
        long jRealSize = realSize();
        if (j <= jRealSize) {
            return;
        }
        if (jRealSize == 0) {
            jDoRoundToFactor = doRoundToFactor(Math.max(this.initialSize, j));
        } else {
            long jMax = Math.max(jRealSize + 1, 2L);
            while (j > jMax) {
                long j2 = (13 * jMax) >> 3;
                jMax = j2 >= SieveCacheKt.NodeLinkMask ? jMax + (jMax / 5) : j2;
            }
            jDoRoundToFactor = doRoundToFactor(jMax);
        }
        try {
            this.storage.resize(jDoRoundToFactor);
        } catch (IOException e) {
            rc6.a(e);
        }
    }

    private long readLogicalSize() throws IOException {
        Path file = this.storage.getFile();
        try {
            DataInputStream dataInputStream = new DataInputStream(Files.newInputStream(deriveLengthFile(), StandardOpenOption.READ));
            try {
                long j = dataInputStream.readLong();
                dataInputStream.close();
                return j;
            } catch (Throwable th) {
                try {
                    dataInputStream.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
                throw th;
            }
        } catch (IOException e) {
            long jRealSize = realSize();
            writeLogicalSize(jRealSize);
            LOG.info("Can't find .len file for " + file + ", re-creating it from actual file. Storage size = " + jRealSize + ", file size = " + Files.size(file), e);
            return jRealSize;
        }
    }

    private long realSize() {
        return this.storage.length();
    }

    private void writeLogicalSize(final long j) {
        final Path pathDeriveLengthFile = deriveLengthFile();
        try {
            FileUtilRt.doIOOperation(new FileUtilRt.RepeatableIOOperation() { // from class: bgc
                public final Object execute(boolean z) {
                    return ResizeableMappedFile.d(this.a, pathDeriveLengthFile, j, z);
                }
            });
        } catch (IOException e) {
            LOG.error("Can't write logical size to [" + pathDeriveLengthFile.toAbsolutePath() + "]", e);
        }
    }

    public void clear() throws IOException {
        this.storage.resize(0L);
        this.logicalSize = 0L;
        this.lastWrittenLogicalSize = 0L;
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() throws Exception {
        Supplier supplier = new Supplier() { // from class: yfc
            @Override // java.util.function.Supplier
            public final Object get() {
                return ResizeableMappedFile.a(this.b);
            }
        };
        ThrowableRunnable throwableRunnable = new ThrowableRunnable() { // from class: zfc
            @Override // com.intellij.util.ThrowableRunnable
            public final void run() throws IOException {
                ResizeableMappedFile.c(this.a);
            }
        };
        PagedFileStorage pagedFileStorage = this.storage;
        Objects.requireNonNull(pagedFileStorage);
        ExceptionUtil.runAllAndRethrowAllExceptions(IOException.class, supplier, throwableRunnable, new agc(pagedFileStorage));
    }

    public void ensureSize(long j) {
        this.logicalSize = Math.max(j, this.logicalSize);
        expand(j);
    }

    @Override // com.intellij.openapi.Forceable
    public void force() throws IOException {
        ensureLogicalSizeWritten();
        this.storage.force();
    }

    public byte get(long j, boolean z) throws IOException {
        return this.storage.get(j, z);
    }

    public int getInt(long j) throws IOException {
        return this.storage.getInt(j);
    }

    public long getLong(long j) throws IOException {
        return this.storage.getLong(j);
    }

    public PagedFileStorage getPagedFileStorage() {
        PagedFileStorage pagedFileStorage = this.storage;
        if (pagedFileStorage == null) {
            $$$reportNull$$$0(3);
        }
        return pagedFileStorage;
    }

    public StorageLockContext getStorageLockContext() {
        StorageLockContext storageLockContext = this.storage.getStorageLockContext();
        if (storageLockContext == null) {
            $$$reportNull$$$0(4);
        }
        return storageLockContext;
    }

    @Override // com.intellij.openapi.Forceable
    public boolean isDirty() {
        return this.storage.isDirty();
    }

    public long length() {
        return this.logicalSize;
    }

    public void lockRead() {
        this.storage.lockRead();
    }

    public void lockWrite() {
        this.storage.lockWrite();
    }

    public void put(long j, byte[] bArr, int i, int i2) throws IOException {
        ensureSize(((long) i2) + j);
        this.storage.put(j, bArr, i, i2);
    }

    public void putInt(long j, int i) throws IOException {
        ensureSize(4 + j);
        this.storage.putInt(j, i);
    }

    public void putLong(long j, long j2) throws IOException {
        ensureSize(8 + j);
        this.storage.putLong(j, j2);
    }

    public <R> R readInputStream(ThrowableNotNullFunction<? super InputStream, R, ? extends IOException> throwableNotNullFunction) throws IOException {
        if (throwableNotNullFunction == null) {
            $$$reportNull$$$0(5);
        }
        R r = (R) this.storage.readInputStream(throwableNotNullFunction);
        if (r == null) {
            $$$reportNull$$$0(6);
        }
        return r;
    }

    public void setRoundFactor(int i) {
        this.roundingFactor = i;
    }

    public String toString() {
        return "ResizeableMappedFile[" + this.storage.toString() + "]";
    }

    public void unlockRead() {
        this.storage.unlockRead();
    }

    public void unlockWrite() {
        this.storage.unlockWrite();
    }

    public void get(long j, byte[] bArr, int i, int i2, boolean z) throws IOException {
        this.storage.get(j, bArr, i, i2, z);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public ResizeableMappedFile(Path path, int i, StorageLockContext storageLockContext, int i2, boolean z) throws IOException {
        this(path, i, storageLockContext, i2, z, false);
        if (path == null) {
            $$$reportNull$$$0(0);
        }
    }
}
