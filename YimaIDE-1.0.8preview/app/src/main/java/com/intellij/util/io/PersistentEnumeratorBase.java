package com.intellij.util.io;

import androidx.collection.ScatterMapKt;
import com.intellij.openapi.Forceable;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.util.ExceptionUtil;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.Processor;
import com.intellij.util.SystemProperties;
import com.intellij.util.ThrowableRunnable;
import com.intellij.util.indexing.impl.IndexDebugProperties;
import com.intellij.util.io.PersistentEnumeratorBase;
import com.intellij.util.io.keyStorage.AppendableObjectStorage;
import com.intellij.util.io.keyStorage.AppendableStorageBackedByResizableMappedFile;
import com.intellij.util.io.keyStorage.InlinedKeyStorage;
import com.intellij.util.io.keyStorage.NoDataException;
import defpackage.u0b;
import java.io.Closeable;
import java.io.Flushable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.attribute.FileAttribute;
import java.util.Objects;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public abstract class PersistentEnumeratorBase implements Forceable, ScannableDataEnumeratorEx, Closeable {
    protected static final Logger LOG = Logger.getInstance(PersistentEnumeratorBase.class);
    protected static final boolean USE_RW_LOCK = SystemProperties.getBooleanProperty("idea.persistent.data.use.read.write.lock", false);
    private boolean myClosed;
    protected final ResizeableMappedFile myCollisionResolutionStorage;
    private volatile boolean myCorrupted;
    final KeyDescriptor<Object> myDataDescriptor;
    private volatile boolean myDirty;
    private volatile boolean myDirtyStatusUpdateInProgress;
    private final boolean myDoCaching;
    protected final Path myFile;
    protected final AppendableObjectStorage<Object> myKeyStorage;
    private final ReentrantReadWriteLock myLock;
    private Flushable myMarkCleanCallback;
    private RecordBufferHandler myRecordHandler;
    private final Version myVersion;

    public interface DataFilter {
        boolean accept(int i) throws IOException;
    }

    public static abstract class RecordBufferHandler<T extends PersistentEnumeratorBase> {
        public abstract byte[] getRecordBuffer(T t);

        public abstract int recordWriteOffset(T t, byte[] bArr) throws IOException;

        public abstract void setupRecord(T t, int i, int i2, byte[] bArr);
    }

    public static abstract class RecordsProcessor {
        private int myKey;

        public int getCurrentKey() {
            return this.myKey;
        }

        public abstract boolean process(int i) throws IOException;

        public void setCurrentKey(int i) {
            this.myKey = i;
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 5 || i == 6 || i == 7 || i == 13) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 5 || i == 6 || i == 7 || i == 13) ? 2 : 3];
        switch (i) {
            case 1:
                objArr[0] = "valueStorage";
                break;
            case 2:
                objArr[0] = "dataDescriptor";
                break;
            case 3:
                objArr[0] = "version";
                break;
            case 4:
                objArr[0] = "recordBufferHandler";
                break;
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 13:
                objArr[0] = "com/intellij/util/io/PersistentEnumeratorBase";
                break;
            case 8:
                objArr[0] = "recordHandler";
                break;
            case 9:
                objArr[0] = "markCleanCallback";
                break;
            case 10:
            case 14:
            case 15:
                objArr[0] = "processor";
                break;
            case 11:
            case 12:
                objArr[0] = "reader";
                break;
            case 16:
                objArr[0] = "lockContext";
                break;
            case 17:
                objArr[0] = "task";
                break;
            default:
                objArr[0] = "file";
                break;
        }
        if (i == 5) {
            objArr[1] = "getWriteLock";
        } else if (i == 6) {
            objArr[1] = "getReadLock";
        } else if (i == 7) {
            objArr[1] = "getRecordHandler";
        } else if (i != 13) {
            objArr[1] = "com/intellij/util/io/PersistentEnumeratorBase";
        } else {
            objArr[1] = "getAllDataObjects";
        }
        switch (i) {
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 13:
                break;
            case 8:
                objArr[2] = "setRecordHandler";
                break;
            case 9:
                objArr[2] = "setMarkCleanCallback";
                break;
            case 10:
                objArr[2] = "processAllDataObject";
                break;
            case 11:
            case 12:
                objArr[2] = "forEach";
                break;
            case 14:
            case 15:
                objArr[2] = "iterateData";
                break;
            case 16:
            case 17:
                objArr[2] = "runWithStorageReadLocksTemporaryReleased";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 5 && i != 6 && i != 7 && i != 13) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public PersistentEnumeratorBase(Path path, ResizeableMappedFile resizeableMappedFile, KeyDescriptor keyDescriptor, int i, Version version, RecordBufferHandler recordBufferHandler, boolean z) throws IOException {
        boolean z2;
        int i2;
        if (path == null) {
            $$$reportNull$$$0(0);
        }
        if (resizeableMappedFile == null) {
            $$$reportNull$$$0(1);
        }
        if (keyDescriptor == null) {
            $$$reportNull$$$0(2);
        }
        if (version == null) {
            $$$reportNull$$$0(3);
        }
        if (recordBufferHandler == null) {
            $$$reportNull$$$0(4);
        }
        this.myLock = new ReentrantReadWriteLock();
        this.myDataDescriptor = keyDescriptor;
        this.myFile = path;
        this.myVersion = version;
        this.myRecordHandler = recordBufferHandler;
        this.myDoCaching = z;
        this.myCollisionResolutionStorage = resizeableMappedFile;
        lockStorageWrite();
        try {
            if (!Files.exists(path, new LinkOption[0])) {
                if (path.getFileSystem().isReadOnly()) {
                    throw new IOException(path + " in " + path.getFileSystem() + " is not exist");
                }
                Path parent = path.getParent();
                if (parent != null) {
                    Files.createDirectories(parent, new FileAttribute[0]);
                }
                Files.createFile(path, new FileAttribute[0]);
            }
            if (resizeableMappedFile.length() == 0) {
                try {
                    try {
                        markDirty(true);
                        putMetaData(0L);
                        putMetaData2(0L);
                        setupEmptyFile();
                        doFlush();
                        z2 = true;
                    } catch (RuntimeException e) {
                        LOG.info(e);
                        if (!(e.getCause() instanceof IOException)) {
                            throw e;
                        }
                        throw ((IOException) e.getCause());
                    }
                } catch (IOException e2) {
                    LOG.info(e2);
                    throw e2;
                } catch (Exception e3) {
                    LOG.info(e3);
                    throw new CorruptedException("PersistentEnumerator storage corrupted " + path, e3);
                }
            } else {
                try {
                    i2 = resizeableMappedFile.getInt(0L);
                } catch (Exception e4) {
                    LOG.info(e4);
                    i2 = this.myVersion.dirtyMagic;
                }
                if (i2 != this.myVersion.correctlyClosedMagic) {
                    if (i2 != this.myVersion.dirtyMagic) {
                        throw new VersionUpdatedException(path, Integer.toHexString(this.myVersion.correctlyClosedMagic), Integer.toHexString(i2));
                    }
                    throw new CorruptedException("PersistentEnumerator storage corrupted " + path);
                }
                z2 = false;
            }
            if (keyDescriptor instanceof InlineKeyDescriptor) {
                this.myKeyStorage = new InlinedKeyStorage((InlineKeyDescriptor) keyDescriptor);
            } else {
                try {
                    this.myKeyStorage = new AppendableStorageBackedByResizableMappedFile(keyStreamFile(), i, this.myCollisionResolutionStorage.getStorageLockContext(), IOUtil.MiB, false, keyDescriptor);
                } catch (Throwable th) {
                    LOG.info(th);
                    throw new CorruptedException(path);
                }
            }
            if (IndexDebugProperties.IS_UNIT_TEST_MODE) {
                Logger logger = LOG;
                if (logger.isTraceEnabled()) {
                    logger.debug("PersistentEnumeratorBase at " + this.myFile + " has been open (new = " + z2 + ")");
                }
            }
            unlockStorageWrite();
        } catch (Throwable th2) {
            try {
                Objects.requireNonNull(resizeableMappedFile);
                Exception excRunAndCatch = ExceptionUtil.runAndCatch(new u0b(resizeableMappedFile));
                if (excRunAndCatch == null) {
                    throw th2;
                }
                th2.addSuppressed(excRunAndCatch);
                throw th2;
            } catch (Throwable th3) {
                unlockStorageWrite();
                throw th3;
            }
        }
    }

    public static /* synthetic */ void a(PersistentEnumeratorBase persistentEnumeratorBase) throws IOException {
        persistentEnumeratorBase.markDirty(true);
        persistentEnumeratorBase.force();
    }

    private int doEnumerate(final Object obj, final boolean z, final boolean z2) throws IOException {
        int cachedId;
        if (this.myDoCaching && !z2 && (cachedId = PersistentEnumeratorCache.getCachedId(obj, this)) != 0) {
            return cachedId;
        }
        int iIntValue = ((Integer) catchCorruption(new ThrowableComputable() { // from class: v0b
            public final Object compute() {
                return Integer.valueOf(this.a.enumerateImpl(obj, z, z2));
            }
        })).intValue();
        if (this.myDoCaching && iIntValue != 0) {
            PersistentEnumeratorCache.cacheId(obj, iIntValue, this);
        }
        return iIntValue;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public Object findValueFor(int i) throws IOException {
        boolean zShouldLockOnValueOf = shouldLockOnValueOf();
        if (zShouldLockOnValueOf) {
            lockStorageRead();
        }
        try {
            return this.myKeyStorage.read(indexToAddr(i), zShouldLockOnValueOf);
        } finally {
            if (zShouldLockOnValueOf) {
                unlockStorageRead();
            }
        }
    }

    private Path keyStreamFile() {
        return this.myFile.resolveSibling(this.myFile.getFileName() + ".keystream");
    }

    private static void runWithStorageReadLocksTemporaryReleased(StorageLockContext storageLockContext, ThrowableRunnable<IOException> throwableRunnable) throws Throwable {
        int i;
        if (storageLockContext == null) {
            $$$reportNull$$$0(16);
        }
        if (throwableRunnable == null) {
            $$$reportNull$$$0(17);
        }
        Lock lock = storageLockContext.readLock();
        int i2 = 0;
        try {
            int lockHolds = storageLockContext.readLockHolds();
            i = 0;
            while (i < lockHolds) {
                try {
                    lock.unlock();
                    i++;
                } catch (Throwable th) {
                    th = th;
                    while (i2 < i) {
                        lock.lock();
                        i2++;
                    }
                    throw th;
                }
            }
            throwableRunnable.run();
            while (i2 < i) {
                lock.lock();
                i2++;
            }
        } catch (Throwable th2) {
            th = th2;
            i = 0;
        }
    }

    public boolean canReEnumerate() {
        return false;
    }

    public <V> V catchCorruption(ThrowableComputable<V, IOException> throwableComputable) throws IOException {
        if (isCorrupted()) {
            throw new CorruptedException("PersistentEnumerator storage corrupted " + this.myFile);
        }
        try {
            return (V) throwableComputable.compute();
        } catch (Throwable th) {
            try {
                if ((th instanceof NoDataException) || !trySelfHeal()) {
                    throw th;
                }
                return (V) throwableComputable.compute();
            } catch (ClosedStorageException e) {
                throw e;
            } catch (NoDataException unused) {
                return null;
            } catch (IOException e2) {
                LOG.error(e2);
                markCorrupted();
                throw e2;
            } catch (Throwable th2) {
                LOG.error(th2);
                markCorrupted();
                rc6.a(th2);
                return null;
            }
        }
    }

    public void close() throws IOException {
        getWriteLock().lock();
        try {
            lockStorageWrite();
            try {
                if (!this.myClosed) {
                    this.myClosed = true;
                    doClose();
                    if (IndexDebugProperties.IS_UNIT_TEST_MODE) {
                        Logger logger = LOG;
                        if (logger.isTraceEnabled()) {
                            logger.info("PersistentEnumeratorBase at " + this.myFile + " has been closed");
                        }
                    }
                }
                unlockStorageWrite();
                getWriteLock().unlock();
            } catch (Throwable th) {
                unlockStorageWrite();
                throw th;
            }
        } catch (Throwable th2) {
            getWriteLock().unlock();
            throw th2;
        }
    }

    public void doClose() throws IOException {
        IOCancellationCallbackHolder.INSTANCE.interactWithUI();
        getWriteLock().lock();
        try {
            try {
                force();
                this.myKeyStorage.close();
                this.myCollisionResolutionStorage.close();
                getWriteLock().unlock();
            } catch (Throwable th) {
                this.myCollisionResolutionStorage.close();
                throw th;
            }
        } catch (Throwable th2) {
            getWriteLock().unlock();
            throw th2;
        }
    }

    public void doFlush() throws IOException {
        markDirty(false);
        this.myCollisionResolutionStorage.force();
    }

    public int doWriteData(Object obj) throws IOException {
        return this.myKeyStorage.append(obj);
    }

    public void dumpKeysOnCorruption() {
    }

    @Override // com.intellij.util.io.DataEnumerator
    public int enumerate(Object obj) throws IOException {
        return doEnumerate(obj, false, false);
    }

    public abstract int enumerateImpl(Object obj, boolean z, boolean z2) throws IOException;

    @Override // com.intellij.openapi.Forceable
    public void force() {
        if (isDirty()) {
            getWriteLock().lock();
            try {
                lockStorageWrite();
                try {
                    try {
                        if (isDirty()) {
                            if (this.myKeyStorage.isDirty()) {
                                this.myKeyStorage.force();
                            }
                            if (this.myCollisionResolutionStorage.isDirty()) {
                                doFlush();
                            }
                        }
                        unlockStorageWrite();
                        getWriteLock().unlock();
                    } catch (IOException e) {
                        throw new RuntimeException(e);
                    }
                } catch (Throwable th) {
                    unlockStorageWrite();
                    throw th;
                }
            } catch (Throwable th2) {
                getWriteLock().unlock();
                throw th2;
            }
        }
    }

    public long getMetaData() throws IOException {
        lockStorageRead();
        try {
            return this.myCollisionResolutionStorage.getLong(4L);
        } finally {
            unlockStorageRead();
        }
    }

    public long getMetaData2() throws IOException {
        lockStorageRead();
        try {
            return this.myCollisionResolutionStorage.getLong(12L);
        } finally {
            unlockStorageRead();
        }
    }

    public Lock getReadLock() {
        boolean z = USE_RW_LOCK;
        ReentrantReadWriteLock reentrantReadWriteLock = this.myLock;
        Lock lock = z ? reentrantReadWriteLock.readLock() : reentrantReadWriteLock.writeLock();
        if (lock == null) {
            $$$reportNull$$$0(6);
        }
        return lock;
    }

    public final RecordBufferHandler getRecordHandler() {
        RecordBufferHandler recordBufferHandler = this.myRecordHandler;
        if (recordBufferHandler == null) {
            $$$reportNull$$$0(7);
        }
        return recordBufferHandler;
    }

    public Object getValue(int i, int i2) throws IOException {
        return valueOf(i);
    }

    public Lock getWriteLock() {
        ReentrantReadWriteLock.WriteLock writeLock = this.myLock.writeLock();
        if (writeLock == null) {
            $$$reportNull$$$0(5);
        }
        return writeLock;
    }

    public abstract int indexToAddr(int i) throws IOException;

    public boolean isClosed() {
        getReadLock().lock();
        try {
            return this.myClosed;
        } finally {
            getReadLock().unlock();
        }
    }

    public boolean isCorrupted() {
        return this.myCorrupted;
    }

    @Override // com.intellij.openapi.Forceable
    public boolean isDirty() {
        return this.myDirty;
    }

    public boolean isKeyAtIndex(Object obj, int i) throws IOException {
        if (this.myKeyStorage instanceof InlinedKeyStorage) {
            return false;
        }
        if (this.myKeyStorage.checkBytesAreTheSame(indexToAddr(i), obj)) {
            return true;
        }
        if (this.myDataDescriptor instanceof DifferentSerializableBytesImplyNonEqualityPolicy) {
            return false;
        }
        Object objValueOf = valueOf(i);
        if (objValueOf == null) {
            return obj == null;
        }
        return this.myDataDescriptor.isEqual(objValueOf, obj);
    }

    public boolean iterateData(final Processor<Object> processor) throws IOException {
        if (processor == null) {
            $$$reportNull$$$0(14);
        }
        return iterateData(new AppendableObjectStorage.StorageObjectProcessor() { // from class: t0b
            @Override // com.intellij.util.io.keyStorage.AppendableObjectStorage.StorageObjectProcessor
            public final boolean process(int i, Object obj) {
                return processor.process(obj);
            }
        });
    }

    public void lockStorageRead() {
        this.myCollisionResolutionStorage.lockRead();
    }

    public void lockStorageWrite() {
        this.myCollisionResolutionStorage.lockWrite();
    }

    public void markCorrupted() {
        if (IndexDebugProperties.IS_UNIT_TEST_MODE && LOG.isTraceEnabled()) {
            dumpKeysOnCorruption();
        }
        getWriteLock().lock();
        try {
            if (!this.myCorrupted) {
                this.myCorrupted = true;
                Logger logger = LOG;
                if (logger.isDebugEnabled()) {
                    logger.debug("Marking corrupted:" + this.myFile, new Throwable());
                }
                try {
                    runWithStorageReadLocksTemporaryReleased(this.myCollisionResolutionStorage.getStorageLockContext(), new ThrowableRunnable() { // from class: s0b
                        @Override // com.intellij.util.ThrowableRunnable
                        public final void run() throws IOException {
                            PersistentEnumeratorBase.a(this.a);
                        }
                    });
                } catch (IOException unused) {
                }
            }
        } finally {
            getWriteLock().unlock();
        }
    }

    public final void markDirty(boolean z) throws IOException {
        if (z && this.myDirty && !this.myDirtyStatusUpdateInProgress) {
            return;
        }
        lockStorageWrite();
        try {
            if (this.myDirty) {
                if (!z) {
                    this.myDirtyStatusUpdateInProgress = true;
                    Flushable flushable = this.myMarkCleanCallback;
                    if (flushable != null) {
                        flushable.flush();
                    }
                    if (!this.myCorrupted) {
                        this.myCollisionResolutionStorage.putInt(0L, this.myVersion.correctlyClosedMagic);
                        this.myDirty = false;
                    }
                    this.myDirtyStatusUpdateInProgress = false;
                }
            } else if (z) {
                this.myDirtyStatusUpdateInProgress = true;
                this.myCollisionResolutionStorage.putInt(0L, this.myVersion.dirtyMagic);
                this.myDirtyStatusUpdateInProgress = false;
                this.myDirty = true;
            }
        } finally {
            unlockStorageWrite();
        }
    }

    public boolean processAllDataObject(final Processor<Object> processor, final DataFilter dataFilter) throws IOException {
        if (processor == null) {
            $$$reportNull$$$0(10);
        }
        return traverseAllRecords(new RecordsProcessor() { // from class: com.intellij.util.io.PersistentEnumeratorBase.1
            @Override // com.intellij.util.io.PersistentEnumeratorBase.RecordsProcessor
            public boolean process(int i) throws IOException {
                DataFilter dataFilter2 = dataFilter;
                if (dataFilter2 == null || dataFilter2.accept(i)) {
                    return processor.process(PersistentEnumeratorBase.this.valueOf(i));
                }
                return true;
            }
        });
    }

    public void putMetaData(long j) throws IOException {
        lockStorageWrite();
        try {
            if (this.myCollisionResolutionStorage.length() < 12 || getMetaData() != j) {
                this.myCollisionResolutionStorage.putLong(4L, j);
            }
        } finally {
            unlockStorageWrite();
        }
    }

    public void putMetaData2(long j) throws IOException {
        lockStorageWrite();
        try {
            if (this.myCollisionResolutionStorage.length() < 20 || getMetaData2() != j) {
                this.myCollisionResolutionStorage.putLong(12L, j);
            }
        } finally {
            unlockStorageWrite();
        }
    }

    public int reEnumerate(Object obj) throws IOException {
        if (canReEnumerate()) {
            return doEnumerate(obj, false, true);
        }
        throw new IncorrectOperationException();
    }

    public void setMarkCleanCallback(Flushable flushable) {
        if (flushable == null) {
            $$$reportNull$$$0(9);
        }
        this.myMarkCleanCallback = flushable;
    }

    public void setRecordHandler(RecordBufferHandler recordBufferHandler) {
        if (recordBufferHandler == null) {
            $$$reportNull$$$0(8);
        }
        this.myRecordHandler = recordBufferHandler;
    }

    public abstract void setupEmptyFile() throws IOException;

    public int setupValueId(int i, int i2) throws IOException {
        byte[] recordBuffer = this.myRecordHandler.getRecordBuffer(this);
        this.myRecordHandler.setupRecord(this, i, i2, recordBuffer);
        int iRecordWriteOffset = this.myRecordHandler.recordWriteOffset(this, recordBuffer);
        this.myCollisionResolutionStorage.put(iRecordWriteOffset, recordBuffer, 0, recordBuffer.length);
        return iRecordWriteOffset;
    }

    public abstract boolean shouldLockOnValueOf();

    public abstract boolean traverseAllRecords(RecordsProcessor recordsProcessor) throws IOException;

    public int tryEnumerate(Object obj) throws IOException {
        return doEnumerate(obj, true, false);
    }

    public boolean trySelfHeal() {
        return false;
    }

    public void unlockStorageRead() {
        this.myCollisionResolutionStorage.unlockRead();
    }

    public void unlockStorageWrite() {
        this.myCollisionResolutionStorage.unlockWrite();
    }

    public Object valueOf(final int i) throws IOException {
        if (i <= 0) {
            return null;
        }
        return catchCorruption(new ThrowableComputable() { // from class: w0b
            public final Object compute() {
                return this.a.findValueFor(i);
            }
        });
    }

    public int writeData(Object obj, int i) {
        try {
            markDirty(true);
            return setupValueId(i, doWriteData(obj));
        } catch (IOException e) {
            rc6.a(e);
            return 0;
        }
    }

    public static final class Version {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final int correctlyClosedMagic;
        private final int dirtyMagic;

        public Version(int i) {
            this(i + 247118589, -1161946761);
        }

        private Version(int i, int i2) {
            this.correctlyClosedMagic = i;
            this.dirtyMagic = i2;
        }
    }

    public boolean iterateData(AppendableObjectStorage.StorageObjectProcessor<Object> storageObjectProcessor) throws IOException {
        if (storageObjectProcessor == null) {
            $$$reportNull$$$0(15);
        }
        return this.myKeyStorage.processAll(storageObjectProcessor);
    }
}
