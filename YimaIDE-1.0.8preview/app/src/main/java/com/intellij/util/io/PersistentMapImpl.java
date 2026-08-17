package com.intellij.util.io;

import androidx.collection.ScatterMapKt;
import androidx.collection.SieveCacheKt;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.openapi.util.LowMemoryWatcher;
import com.intellij.openapi.util.ThreadLocalCachedValue;
import com.intellij.openapi.util.io.BufferExposingByteArrayOutputStream;
import com.intellij.openapi.util.io.FileUtil;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.util.ArrayUtil;
import com.intellij.util.ArrayUtilRt;
import com.intellij.util.IncorrectOperationException;
import com.intellij.util.Processor;
import com.intellij.util.SystemProperties;
import com.intellij.util.containers.LimitedPool;
import com.intellij.util.containers.SLRUCache;
import com.intellij.util.io.PersistentMapImpl;
import com.intellij.util.io.stats.StorageStatsRegistrar;
import java.io.DataInputStream;
import java.io.DataOutput;
import java.io.File;
import java.io.Flushable;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.function.IntFunction;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Stream;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class PersistentMapImpl<Key, Value> implements PersistentMapBase<Key, Value> {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private int largeKeys;
    private final SLRUCache<Key, BufferExposingByteArrayOutputStream> myAppendCache;
    private final LowMemoryWatcher myAppendCacheFlusher;
    private final PersistentMapBuilder<Key, Value> myBuilder;
    private final boolean myCanReEnumerate;
    private final boolean myCompactOnClose;
    private final boolean myDirectlyStoreLongFileOffsetMode;
    private final PersistentEnumeratorBase myEnumerator;
    private boolean myIntAddressForNewRecord;
    private final boolean myIntMapping;
    private final boolean myIsReadOnly;
    private final KeyDescriptor<Key> myKeyDescriptor;
    private int myLargeIndexWatermarkId;
    private long myLiveAndGarbageKeysCounter;
    private final ReentrantReadWriteLock myLock;
    private final PersistentHashMapValueStorage.CreationTimeOptions myOptions;
    private final int myParentValueRefOffset;
    private int myReadCompactionGarbageSize;
    private final Path myStorageFile;
    private final LimitedPool<BufferExposingByteArrayOutputStream> myStreamPool;
    private final DataExternalizer<Value> myValueExternalizer;
    private PersistentHashMapValueStorage myValueStorage;
    private final PersistentMapWal<Key, Value> myWal;
    private int requests;
    private int smallKeys;
    private int transformedKeys;
    private static final Logger LOG = Logger.getInstance(PersistentMapImpl.class);
    private static final boolean myDoTrace = SystemProperties.getBooleanProperty("idea.trace.persistent.map", false);
    private static final int DEFAULT_INDEX_INITIAL_SIZE = SystemProperties.getIntProperty("idea.initialIndexSize", 4096);
    private static final ThreadLocalCachedValue<AppendStream> ourFlyweightAppenderStream = new ThreadLocalCachedValue<AppendStream>() { // from class: com.intellij.util.io.PersistentMapImpl.3
        /* JADX WARN: Can't rename method to resolve collision */
        @Override // com.intellij.openapi.util.ThreadLocalCachedValue
        public AppendStream create() {
            return new AppendStream();
        }
    };

    public static final class AppendStream extends DataOutputStream {
        private AppendStream() {
            super(null);
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void setOut(BufferExposingByteArrayOutputStream bufferExposingByteArrayOutputStream) {
            ((java.io.DataOutputStream) this).out = bufferExposingByteArrayOutputStream;
        }
    }

    public static final class CompactionRecordInfo {
        final int address;
        final int key;
        long newValueAddress;
        byte[] value;
        long valueAddress;

        public CompactionRecordInfo(int i, long j, int i2) {
            this.key = i;
            this.address = i2;
            this.valueAddress = j;
        }
    }

    public final class MyEnumeratorRecordHandler extends PersistentEnumeratorBase.RecordBufferHandler {
        private final ThreadLocal<byte[]> myRecordBuffer;
        private final PersistentEnumeratorBase.RecordBufferHandler myRecordHandler;
        private final ThreadLocal<byte[]> mySmallRecordBuffer;
        final /* synthetic */ PersistentMapImpl this$0;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 1 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 1 ? 3 : 2];
            if (i == 1) {
                objArr[0] = "com/intellij/util/io/PersistentMapImpl$MyEnumeratorRecordHandler";
            } else if (i != 2) {
                objArr[0] = "recordHandler";
            } else {
                objArr[0] = "buf";
            }
            if (i != 1) {
                objArr[1] = "com/intellij/util/io/PersistentMapImpl$MyEnumeratorRecordHandler";
            } else {
                objArr[1] = "getRecordBuffer";
            }
            if (i != 1) {
                if (i != 2) {
                    objArr[2] = "<init>";
                } else {
                    objArr[2] = "setupRecord";
                }
            }
            String str2 = String.format(str, objArr);
            if (i == 1) {
                throw new IllegalStateException(str2);
            }
        }

        public MyEnumeratorRecordHandler(PersistentMapImpl persistentMapImpl, PersistentEnumeratorBase.RecordBufferHandler recordBufferHandler) {
            if (recordBufferHandler == null) {
                $$$reportNull$$$0(0);
            }
            this.this$0 = persistentMapImpl;
            this.myRecordHandler = recordBufferHandler;
            this.myRecordBuffer = ThreadLocal.withInitial(new Supplier() { // from class: com.intellij.util.io.j
                @Override // java.util.function.Supplier
                public final Object get() {
                    return PersistentMapImpl.MyEnumeratorRecordHandler.a(this.b);
                }
            });
            this.mySmallRecordBuffer = ThreadLocal.withInitial(new Supplier() { // from class: com.intellij.util.io.k
                @Override // java.util.function.Supplier
                public final Object get() {
                    return PersistentMapImpl.MyEnumeratorRecordHandler.b(this.b);
                }
            });
        }

        public static /* synthetic */ byte[] a(MyEnumeratorRecordHandler myEnumeratorRecordHandler) {
            return myEnumeratorRecordHandler.this$0.myDirectlyStoreLongFileOffsetMode ? ArrayUtilRt.EMPTY_BYTE_ARRAY : new byte[myEnumeratorRecordHandler.this$0.myParentValueRefOffset + 8];
        }

        public static /* synthetic */ byte[] b(MyEnumeratorRecordHandler myEnumeratorRecordHandler) {
            return myEnumeratorRecordHandler.this$0.myDirectlyStoreLongFileOffsetMode ? ArrayUtilRt.EMPTY_BYTE_ARRAY : new byte[myEnumeratorRecordHandler.this$0.myParentValueRefOffset + 4];
        }

        @Override // com.intellij.util.io.PersistentEnumeratorBase.RecordBufferHandler
        public byte[] getRecordBuffer(PersistentEnumeratorBase persistentEnumeratorBase) {
            byte[] bArr = (this.this$0.myIntAddressForNewRecord ? this.mySmallRecordBuffer : this.myRecordBuffer).get();
            if (bArr == null) {
                $$$reportNull$$$0(1);
            }
            return bArr;
        }

        @Override // com.intellij.util.io.PersistentEnumeratorBase.RecordBufferHandler
        public int recordWriteOffset(PersistentEnumeratorBase persistentEnumeratorBase, byte[] bArr) throws IOException {
            return this.myRecordHandler.recordWriteOffset(persistentEnumeratorBase, bArr);
        }

        @Override // com.intellij.util.io.PersistentEnumeratorBase.RecordBufferHandler
        public void setupRecord(PersistentEnumeratorBase persistentEnumeratorBase, int i, int i2, byte[] bArr) {
            if (bArr == null) {
                $$$reportNull$$$0(2);
            }
            this.myRecordHandler.setupRecord(persistentEnumeratorBase, i, i2, bArr);
            for (int i3 = this.this$0.myParentValueRefOffset; i3 < bArr.length; i3++) {
                bArr[i3] = 0;
            }
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 4 || i == 5 || i == 6 || i == 9 || i == 10 || i == 12 || i == 14 || i == 22) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 4 || i == 5 || i == 6 || i == 9 || i == 10 || i == 12 || i == 14 || i == 22) ? 2 : 3];
        switch (i) {
            case 2:
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[0] = "options";
                break;
            case 3:
                objArr[0] = "path";
                break;
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 9:
            case 10:
            case 12:
            case 14:
            case 22:
                objArr[0] = "com/intellij/util/io/PersistentMapImpl";
                break;
            case 8:
                objArr[0] = "keyDescriptor";
                break;
            case 11:
            case 13:
                objArr[0] = "file";
                break;
            case 15:
            case 16:
                objArr[0] = "appender";
                break;
            case 17:
            case 18:
                objArr[0] = "processor";
                break;
            case 19:
                objArr[0] = "fileFromDirectory";
                break;
            case 20:
                objArr[0] = "newStorage";
                break;
            case 21:
                objArr[0] = "map";
                break;
            default:
                objArr[0] = "builder";
                break;
        }
        if (i == 4) {
            objArr[1] = "getValuesExternalizer";
        } else if (i == 5) {
            objArr[1] = "getKeyDescriptor";
        } else if (i == 6) {
            objArr[1] = "builder";
        } else if (i == 9) {
            objArr[1] = "getWriteLock";
        } else if (i == 10) {
            objArr[1] = "getReadLock";
        } else if (i == 12) {
            objArr[1] = "checkDataFiles";
        } else if (i == 14) {
            objArr[1] = "getDataFile";
        } else if (i != 22) {
            objArr[1] = "com/intellij/util/io/PersistentMapImpl";
        } else {
            objArr[1] = "unwrap";
        }
        switch (i) {
            case 3:
                objArr[2] = "deriveEmptyMap";
                break;
            case 4:
            case 5:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 9:
            case 10:
            case 12:
            case 14:
            case 22:
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
                objArr[2] = "modifyVersionDependingOnOptions";
                break;
            case 8:
                objArr[2] = "createAppendCache";
                break;
            case 11:
                objArr[2] = "checkDataFiles";
                break;
            case 13:
                objArr[2] = "getDataFile";
                break;
            case 15:
                objArr[2] = "appendData";
                break;
            case 16:
                objArr[2] = "doAppendData";
                break;
            case 17:
                objArr[2] = "processKeys";
                break;
            case 18:
                objArr[2] = "processExistingKeys";
                break;
            case 19:
                objArr[2] = "getFilesInDirectoryWithNameStartingWith";
                break;
            case 20:
                objArr[2] = "newCompact";
                break;
            case 21:
                objArr[2] = "unwrap";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 4 && i != 5 && i != 6 && i != 9 && i != 10 && i != 12 && i != 14 && i != 22) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    public PersistentMapImpl(PersistentMapBuilder<Key, Value> persistentMapBuilder, PersistentHashMapValueStorage.CreationTimeOptions creationTimeOptions) throws IOException {
        DataExternalizer<Value> dataExternalizer;
        if (persistentMapBuilder == null) {
            $$$reportNull$$$0(1);
        }
        if (creationTimeOptions == null) {
            $$$reportNull$$$0(2);
        }
        this.myLock = new ReentrantReadWriteLock();
        this.myStreamPool = new LimitedPool<>(10, new LimitedPool.ObjectFactory<BufferExposingByteArrayOutputStream>() { // from class: com.intellij.util.io.PersistentMapImpl.1
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                throw new IllegalArgumentException(String.format("Argument for @NotNull parameter '%s' of %s.%s must not be null", "appendStream", "com/intellij/util/io/PersistentMapImpl$1", "cleanup"));
            }

            @Override // com.intellij.util.containers.LimitedPool.ObjectFactory
            public void cleanup(BufferExposingByteArrayOutputStream bufferExposingByteArrayOutputStream) {
                if (bufferExposingByteArrayOutputStream == null) {
                    $$$reportNull$$$0(0);
                }
                bufferExposingByteArrayOutputStream.reset();
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.intellij.util.containers.LimitedPool.ObjectFactory
            public BufferExposingByteArrayOutputStream create() {
                return new BufferExposingByteArrayOutputStream();
            }
        });
        PersistentMapBuilder<Key, Value> persistentMapBuilderCopy = persistentMapBuilder.copy();
        this.myBuilder = persistentMapBuilderCopy;
        Path file = persistentMapBuilderCopy.getFile();
        KeyDescriptor<Key> keyDescriptor = persistentMapBuilderCopy.getKeyDescriptor();
        DataExternalizer<Value> valueExternalizer = persistentMapBuilderCopy.getValueExternalizer();
        int initialSize = persistentMapBuilderCopy.getInitialSize(DEFAULT_INDEX_INITIAL_SIZE);
        boolean z = false;
        int version = persistentMapBuilderCopy.getVersion(0);
        StorageLockContext lockContext = persistentMapBuilderCopy.getLockContext();
        this.myCompactOnClose = persistentMapBuilderCopy.getCompactOnClose(false);
        boolean readOnly = persistentMapBuilderCopy.getReadOnly(false);
        this.myIsReadOnly = readOnly;
        creationTimeOptions = readOnly ? creationTimeOptions.setReadOnly() : creationTimeOptions;
        this.myOptions = creationTimeOptions;
        PersistentEnumeratorBase persistentEnumeratorBaseCreateDefaultEnumerator = PersistentEnumerator.createDefaultEnumerator(checkDataFiles(file), keyDescriptor, initialSize, lockContext, modifyVersionDependingOnOptions(version, creationTimeOptions), false);
        this.myEnumerator = persistentEnumeratorBaseCreateDefaultEnumerator;
        this.myStorageFile = file;
        this.myKeyDescriptor = keyDescriptor;
        LowMemoryWatcher lowMemoryWatcherRegister = null;
        if (persistentMapBuilderCopy.isEnableWal()) {
            dataExternalizer = valueExternalizer;
            this.myWal = new PersistentMapWal<>(keyDescriptor, dataExternalizer, creationTimeOptions.useCompression(), file.resolveSibling(file.getFileName().toString() + ".wal"), persistentMapBuilderCopy.getWalExecutor(), true);
        } else {
            dataExternalizer = valueExternalizer;
            this.myWal = null;
        }
        PersistentEnumeratorBase.RecordBufferHandler recordHandler = persistentEnumeratorBaseCreateDefaultEnumerator.getRecordHandler();
        this.myParentValueRefOffset = recordHandler.getRecordBuffer(persistentEnumeratorBaseCreateDefaultEnumerator).length;
        boolean z2 = (dataExternalizer instanceof IntInlineKeyDescriptor) && persistentMapBuilderCopy.getInlineValues(false);
        this.myIntMapping = z2;
        if ((keyDescriptor instanceof InlineKeyDescriptor) && (persistentEnumeratorBaseCreateDefaultEnumerator instanceof PersistentBTreeEnumerator)) {
            z = true;
        }
        this.myDirectlyStoreLongFileOffsetMode = z;
        persistentEnumeratorBaseCreateDefaultEnumerator.setRecordHandler(new MyEnumeratorRecordHandler(this, recordHandler));
        persistentEnumeratorBaseCreateDefaultEnumerator.setMarkCleanCallback(new Flushable() { // from class: z0b
            @Override // java.io.Flushable
            public final void flush() throws IOException {
                PersistentMapImpl.f(this.b);
            }
        });
        if (myDoTrace) {
            LOG.info("Opened " + file);
        }
        StorageStatsRegistrar.INSTANCE.registerMap(file, this);
        try {
            this.myValueExternalizer = dataExternalizer;
            this.myValueStorage = z2 ? null : new PersistentHashMapValueStorage(getDataFile(file), creationTimeOptions);
            this.myAppendCache = z2 ? null : createAppendCache(keyDescriptor);
            if (!z2) {
                lowMemoryWatcherRegister = LowMemoryWatcher.register(new Runnable() { // from class: a1b
                    @Override // java.lang.Runnable
                    public final void run() {
                        PersistentMapImpl.c(this.b);
                    }
                });
            }
            this.myAppendCacheFlusher = lowMemoryWatcherRegister;
            this.myLiveAndGarbageKeysCounter = persistentEnumeratorBaseCreateDefaultEnumerator.getMetaData();
            long metaData2 = persistentEnumeratorBaseCreateDefaultEnumerator.getMetaData2();
            this.myLargeIndexWatermarkId = (int) (4294967295L & metaData2);
            this.myReadCompactionGarbageSize = (int) (metaData2 >>> 32);
            this.myCanReEnumerate = persistentEnumeratorBaseCreateDefaultEnumerator.canReEnumerate();
            if (creationTimeOptions.isReadOnly() || !makesSenseToCompact()) {
                return;
            }
            compact();
        } catch (IOException e) {
            try {
                close(true);
                throw e;
            } catch (Throwable unused) {
                throw e;
            }
        } catch (Throwable th) {
            LOG.error(th);
            try {
                close(true);
            } catch (Throwable unused2) {
            }
            throw new CorruptedException(this.myStorageFile);
        }
    }

    public static /* synthetic */ long access$714(PersistentMapImpl persistentMapImpl, long j) {
        long j2 = persistentMapImpl.myLiveAndGarbageKeysCounter + j;
        persistentMapImpl.myLiveAndGarbageKeysCounter = j2;
        return j2;
    }

    public static /* synthetic */ boolean b(PersistentMapImpl persistentMapImpl, int i) {
        return persistentMapImpl.readValueId(i) != 0;
    }

    public static /* synthetic */ void c(PersistentMapImpl persistentMapImpl) {
        persistentMapImpl.getClass();
        try {
            persistentMapImpl.force();
        } catch (IOException e) {
            LOG.error(e);
        }
    }

    private boolean canUseIntAddressForNewRecord(long j) {
        return this.myCanReEnumerate && j + 1 < SieveCacheKt.NodeLinkMask;
    }

    private static Path checkDataFiles(Path path) {
        if (path == null) {
            $$$reportNull$$$0(11);
        }
        if (!Files.exists(path, new LinkOption[0])) {
            IOUtil.deleteAllFilesStartingWith(getDataFile(path));
        }
        if (path == null) {
            $$$reportNull$$$0(12);
        }
        return path;
    }

    private void clearAppenderCaches() {
        if (this.myIntMapping) {
            return;
        }
        flushAppendCache();
        this.myValueStorage.force();
    }

    private void close(boolean z) throws IOException {
        String str;
        if (myDoTrace) {
            Logger logger = LOG;
            StringBuilder sb = new StringBuilder("Closed ");
            sb.append(this.myStorageFile);
            sb.append(".");
            if (this.myAppendCache == null) {
                str = "";
            } else {
                str = "Append cache stats: " + this.myAppendCache.dumpStats();
            }
            sb.append(str);
            logger.info(sb.toString());
        }
        StorageStatsRegistrar.INSTANCE.unregisterMap(this.myStorageFile);
        getWriteLock().lock();
        try {
            if (!isClosed()) {
                PersistentMapWal<Key, Value> persistentMapWal = this.myWal;
                if (persistentMapWal != null) {
                    persistentMapWal.close();
                }
                if (!z) {
                    try {
                        if (this.myCompactOnClose && isCompactionSupported()) {
                            compact();
                        }
                    } catch (Throwable th) {
                        doClose();
                        throw th;
                    }
                }
                doClose();
            }
            getWriteLock().unlock();
        } catch (Throwable th2) {
            getWriteLock().unlock();
            throw th2;
        }
    }

    private SLRUCache<Key, BufferExposingByteArrayOutputStream> createAppendCache(KeyDescriptor<Key> keyDescriptor) {
        if (keyDescriptor == null) {
            $$$reportNull$$$0(8);
        }
        return new SLRUCache<Key, BufferExposingByteArrayOutputStream>(16384, 4096, keyDescriptor) { // from class: com.intellij.util.io.PersistentMapImpl.2
            private static /* synthetic */ void $$$reportNull$$$0(int i) {
                String str = i != 1 ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                Object[] objArr = new Object[i != 1 ? 2 : 3];
                if (i != 1) {
                    objArr[0] = "com/intellij/util/io/PersistentMapImpl$2";
                } else {
                    objArr[0] = "bytes";
                }
                if (i != 1) {
                    objArr[1] = "createValue";
                } else {
                    objArr[1] = "com/intellij/util/io/PersistentMapImpl$2";
                }
                if (i == 1) {
                    objArr[2] = "onDropFromCache";
                }
                String str2 = String.format(str, objArr);
                if (i == 1) {
                    throw new IllegalArgumentException(str2);
                }
            }

            /* JADX WARN: Can't rename method to resolve collision */
            @Override // com.intellij.util.containers.SLRUCache
            public BufferExposingByteArrayOutputStream createValue(Key key) {
                BufferExposingByteArrayOutputStream bufferExposingByteArrayOutputStream = (BufferExposingByteArrayOutputStream) PersistentMapImpl.this.myStreamPool.alloc();
                if (bufferExposingByteArrayOutputStream == null) {
                    $$$reportNull$$$0(0);
                }
                return bufferExposingByteArrayOutputStream;
            }

            @Override // com.intellij.util.containers.SLRUMap
            public void onDropFromCache(Key key, BufferExposingByteArrayOutputStream bufferExposingByteArrayOutputStream) {
                int iEnumerate;
                long valueId;
                if (bufferExposingByteArrayOutputStream == null) {
                    $$$reportNull$$$0(1);
                }
                PersistentMapImpl.this.myEnumerator.lockStorageWrite();
                try {
                    try {
                        try {
                            boolean z = PersistentMapImpl.this.myDirectlyStoreLongFileOffsetMode;
                            PersistentMapImpl persistentMapImpl = PersistentMapImpl.this;
                            if (z) {
                                valueId = ((PersistentBTreeEnumerator) persistentMapImpl.myEnumerator).getNonNegativeValue(key);
                                iEnumerate = -1;
                            } else {
                                iEnumerate = persistentMapImpl.enumerate(key);
                                valueId = PersistentMapImpl.this.readValueId(iEnumerate);
                            }
                            long j = valueId;
                            int i = iEnumerate;
                            long jAppendBytes = PersistentMapImpl.this.myValueStorage.appendBytes(bufferExposingByteArrayOutputStream.toByteArraySequence(), j);
                            boolean z2 = PersistentMapImpl.this.myDirectlyStoreLongFileOffsetMode;
                            PersistentMapImpl persistentMapImpl2 = PersistentMapImpl.this;
                            if (z2) {
                                ((PersistentBTreeEnumerator) persistentMapImpl2.myEnumerator).putNonNegativeValue(key, jAppendBytes);
                            } else {
                                persistentMapImpl2.updateValueId(i, jAppendBytes, j, key, 0);
                            }
                            if (j == 0) {
                                PersistentMapImpl.access$714(PersistentMapImpl.this, 4294967296L);
                            }
                            if (bufferExposingByteArrayOutputStream.getInternalBuffer().length <= 4096) {
                                PersistentMapImpl.this.myStreamPool.recycle(bufferExposingByteArrayOutputStream);
                            }
                            PersistentMapImpl.this.myEnumerator.unlockStorageWrite();
                        } catch (ClosedStorageException e) {
                            throw new RuntimeException(e);
                        }
                    } catch (IOException e2) {
                        PersistentMapImpl.this.markCorrupted();
                        throw new RuntimeException(e2);
                    }
                } catch (Throwable th) {
                    PersistentMapImpl.this.myEnumerator.unlockStorageWrite();
                    throw th;
                }
            }
        };
    }

    public static /* synthetic */ File[] d(int i) {
        return new File[i];
    }

    private void doAppendData(Key key, AppendablePersistentMap.ValueDataAppender valueDataAppender) throws IOException {
        if (valueDataAppender == null) {
            $$$reportNull$$$0(16);
        }
        this.myEnumerator.markDirty(true);
        AppendStream value = ourFlyweightAppenderStream.getValue();
        BufferExposingByteArrayOutputStream bufferExposingByteArrayOutputStream = this.myAppendCache.get(key);
        value.setOut(bufferExposingByteArrayOutputStream);
        this.myValueStorage.checkAppendsAllowed(bufferExposingByteArrayOutputStream.size());
        valueDataAppender.append(value);
        value.setOut(null);
    }

    private void doClose() throws IOException {
        this.myEnumerator.lockStorageWrite();
        try {
            try {
                try {
                    LowMemoryWatcher lowMemoryWatcher = this.myAppendCacheFlusher;
                    if (lowMemoryWatcher != null) {
                        lowMemoryWatcher.stop();
                    }
                    flushAppendCache();
                    PersistentHashMapValueStorage persistentHashMapValueStorage = this.myValueStorage;
                    if (persistentHashMapValueStorage != null) {
                        try {
                            persistentHashMapValueStorage.dispose();
                        } catch (Throwable th) {
                            this.myEnumerator.close();
                            throw th;
                        }
                    }
                    this.myEnumerator.close();
                    this.myEnumerator.unlockStorageWrite();
                } catch (Throwable th2) {
                    PersistentHashMapValueStorage persistentHashMapValueStorage2 = this.myValueStorage;
                    if (persistentHashMapValueStorage2 != null) {
                        try {
                            persistentHashMapValueStorage2.dispose();
                        } finally {
                            this.myEnumerator.close();
                        }
                    }
                    throw th2;
                }
            } catch (Throwable th3) {
                this.myEnumerator.unlockStorageWrite();
                throw th3;
            }
        } catch (RuntimeException e) {
            Throwable cause = e.getCause();
            if (!(cause instanceof IOException)) {
                throw e;
            }
            throw ((IOException) cause);
        }
    }

    /* JADX WARN: Code duplicated, block: B:9:0x001c  */
    private boolean doContainsMapping(Key key) throws IOException {
        flushAppendCache(key);
        this.myEnumerator.lockStorageRead();
        try {
            boolean z = this.myDirectlyStoreLongFileOffsetMode;
            PersistentEnumeratorBase persistentEnumeratorBase = this.myEnumerator;
            boolean z2 = false;
            if (!z) {
                int iTryEnumerate = persistentEnumeratorBase.tryEnumerate(key);
                if (iTryEnumerate != 0) {
                    if (this.myIntMapping) {
                        return true;
                    }
                    if (readValueId(iTryEnumerate) != 0) {
                        z2 = true;
                    }
                }
            } else if (((PersistentBTreeEnumerator) persistentEnumeratorBase).getNonNegativeValue(key) != 0) {
                z2 = true;
            }
            return z2;
        } finally {
            this.myEnumerator.unlockStorageRead();
        }
    }

    private void doForce() {
        this.myEnumerator.lockStorageWrite();
        try {
            try {
                clearAppenderCaches();
                this.myEnumerator.force();
                this.myEnumerator.unlockStorageWrite();
            } catch (Throwable th) {
                this.myEnumerator.force();
                throw th;
            }
        } catch (Throwable th2) {
            this.myEnumerator.unlockStorageWrite();
            throw th2;
        }
    }

    /* JADX WARN: Code duplicated, block: B:27:0x005b  */
    /* JADX WARN: Code duplicated, block: B:29:0x005f  */
    /* JADX WARN: Code duplicated, block: B:33:0x0087  */
    /* JADX WARN: Code duplicated, block: B:40:0x00ae  */
    /* JADX WARN: Code duplicated, block: B:51:0x00d5 A[RETURN] */
    /* JADX WARN: Code duplicated, block: B:71:0x00a1 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r12v0, types: [com.intellij.util.io.PersistentMapImpl, com.intellij.util.io.PersistentMapImpl<Key, Value>] */
    /* JADX WARN: Type inference failed for: r12v11, types: [com.intellij.util.io.PersistentEnumeratorBase] */
    /* JADX WARN: Type inference failed for: r12v2 */
    /* JADX WARN: Type inference failed for: r4v0 */
    /* JADX WARN: Type inference failed for: r4v1, types: [com.intellij.util.io.PersistentMapImpl] */
    /* JADX WARN: Type inference failed for: r4v10, types: [com.intellij.util.io.PersistentMapImpl] */
    /* JADX WARN: Type inference failed for: r4v11 */
    /* JADX WARN: Type inference failed for: r4v12 */
    /* JADX WARN: Type inference failed for: r4v2 */
    /* JADX WARN: Type inference failed for: r4v3 */
    /* JADX WARN: Type inference failed for: r4v4, types: [com.intellij.util.io.PersistentMapImpl] */
    /* JADX WARN: Type inference failed for: r4v5 */
    /* JADX WARN: Type inference failed for: r4v6 */
    /* JADX WARN: Type inference failed for: r4v7 */
    /* JADX WARN: Type inference failed for: r4v8 */
    /* JADX WARN: Type inference failed for: r4v9, types: [com.intellij.util.io.PersistentMapImpl] */
    private Value doGet(Key key) throws Exception {
        ?? r4;
        Throwable th;
        int iTryEnumerate;
        long valueId;
        Value value;
        long j;
        int i;
        PersistentEnumeratorBase persistentEnumeratorBase;
        PersistentHashMapValueStorage.ReadResult bytes;
        DataInputStream dataInputStream;
        final Value value2;
        long jCompactChunks;
        ?? r5;
        Throwable th2;
        ?? r6;
        flushAppendCache(key);
        this.myEnumerator.lockStorageRead();
        try {
            boolean z = this.myDirectlyStoreLongFileOffsetMode;
            PersistentEnumeratorBase persistentEnumeratorBase2 = this.myEnumerator;
            try {
                if (!z) {
                    iTryEnumerate = persistentEnumeratorBase2.tryEnumerate(key);
                    if (iTryEnumerate == 0) {
                        this.myEnumerator.unlockStorageRead();
                        return null;
                    }
                    if (this.myIntMapping) {
                        value = (Value) Integer.valueOf(this.myEnumerator.myCollisionResolutionStorage.getInt(iTryEnumerate + this.myParentValueRefOffset));
                        this = (PersistentMapImpl<Key, Value>) this.myEnumerator;
                        this.unlockStorageRead();
                        return value;
                    }
                    valueId = readValueId(iTryEnumerate);
                    j = valueId;
                    i = iTryEnumerate;
                    persistentEnumeratorBase = this.myEnumerator;
                    if (j == 0) {
                        persistentEnumeratorBase.unlockStorageRead();
                        return null;
                    }
                    persistentEnumeratorBase.unlockStorageRead();
                    bytes = this.myValueStorage.readBytes(j);
                    dataInputStream = new DataInputStream(new UnsyncByteArrayInputStream(bytes.buffer));
                    value2 = this.myValueExternalizer.read(dataInputStream);
                    dataInputStream.close();
                    if (this.myValueStorage.performChunksCompaction(bytes.chunksCount)) {
                        return value2;
                    }
                    jCompactChunks = this.myValueStorage.compactChunks(new AppendablePersistentMap.ValueDataAppender() { // from class: b1b
                        @Override // com.intellij.util.io.AppendablePersistentMap.ValueDataAppender
                        public final void append(DataOutput dataOutput) throws IOException {
                            this.a.myValueExternalizer.save(dataOutput, value2);
                        }
                    }, bytes);
                    this.myEnumerator.lockStorageWrite();
                    this.myEnumerator.markDirty(true);
                    if (this.myDirectlyStoreLongFileOffsetMode) {
                        ((PersistentBTreeEnumerator) this.myEnumerator).putNonNegativeValue(key, jCompactChunks);
                        r5 = this;
                    } else {
                        r5 = this;
                        r5.updateValueId(i, jCompactChunks, j, key, 0);
                        r5 = r5;
                    }
                    r5.myLiveAndGarbageKeysCounter++;
                    r5.myReadCompactionGarbageSize += bytes.buffer.length;
                    r5.myEnumerator.unlockStorageWrite();
                    return value2;
                }
                valueId = ((PersistentBTreeEnumerator) persistentEnumeratorBase2).getNonNegativeValue(key);
                if (this.myIntMapping) {
                    value = (Value) Integer.valueOf((int) valueId);
                    this = (PersistentMapImpl<Key, Value>) this.myEnumerator;
                    this.unlockStorageRead();
                    return value;
                }
                iTryEnumerate = -1;
                j = valueId;
                i = iTryEnumerate;
                persistentEnumeratorBase = this.myEnumerator;
                if (j == 0) {
                    persistentEnumeratorBase.unlockStorageRead();
                    return null;
                }
                persistentEnumeratorBase.unlockStorageRead();
                bytes = this.myValueStorage.readBytes(j);
                dataInputStream = new DataInputStream(new UnsyncByteArrayInputStream(bytes.buffer));
                try {
                    value2 = this.myValueExternalizer.read(dataInputStream);
                    dataInputStream.close();
                    if (this.myValueStorage.performChunksCompaction(bytes.chunksCount)) {
                        return value2;
                    }
                    jCompactChunks = this.myValueStorage.compactChunks(new AppendablePersistentMap.ValueDataAppender() { // from class: b1b
                        @Override // com.intellij.util.io.AppendablePersistentMap.ValueDataAppender
                        public final void append(DataOutput dataOutput) throws IOException {
                            this.a.myValueExternalizer.save(dataOutput, value2);
                        }
                    }, bytes);
                    this.myEnumerator.lockStorageWrite();
                    try {
                        this.myEnumerator.markDirty(true);
                        if (this.myDirectlyStoreLongFileOffsetMode) {
                            try {
                                ((PersistentBTreeEnumerator) this.myEnumerator).putNonNegativeValue(key, jCompactChunks);
                                r5 = this;
                            } catch (Throwable th3) {
                                th2 = th3;
                                r6 = this;
                                r6.myEnumerator.unlockStorageWrite();
                                throw th2;
                            }
                        } else {
                            r5 = this;
                            try {
                                r5.updateValueId(i, jCompactChunks, j, key, 0);
                                r5 = r5;
                            } catch (Throwable th4) {
                                th = th4;
                                th2 = th;
                                r6 = r5;
                                r6.myEnumerator.unlockStorageWrite();
                                throw th2;
                            }
                        }
                        r5.myLiveAndGarbageKeysCounter++;
                        r5.myReadCompactionGarbageSize += bytes.buffer.length;
                        r5.myEnumerator.unlockStorageWrite();
                        return value2;
                    } catch (Throwable th5) {
                        th = th5;
                        r5 = this;
                    }
                } catch (Throwable th6) {
                    try {
                        dataInputStream.close();
                        throw th6;
                    } catch (Throwable th7) {
                        th6.addSuppressed(th7);
                        throw th6;
                    }
                }
            } catch (Throwable th8) {
                th = th8;
                r4 = this;
            }
        } catch (Throwable th9) {
            r4 = this;
            th = th9;
        }
        r4.myEnumerator.unlockStorageRead();
        throw th;
    }

    private static boolean doNewCompact() {
        return System.getProperty("idea.persistent.hash.map.oldcompact") == null;
    }

    /* JADX WARN: Code duplicated, block: B:31:0x0096  */
    /* JADX WARN: Code duplicated, block: B:35:0x009f A[Catch: all -> 0x009c, TRY_LEAVE, TryCatch #2 {all -> 0x009c, blocks: (B:32:0x0099, B:35:0x009f, B:27:0x008c), top: B:44:0x008c }] */
    /* JADX WARN: Multi-variable type inference failed */
    private void doPut(Key key, Value value) throws Throwable {
        long jAppendBytes;
        PersistentMapImpl<Key, Value> persistentMapImpl;
        Throwable th;
        long nonNegativeValue;
        long j;
        PersistentEnumeratorBase persistentEnumeratorBase;
        if (this.myIntMapping) {
            jAppendBytes = -1;
        } else {
            BufferExposingByteArrayOutputStream bufferExposingByteArrayOutputStream = new BufferExposingByteArrayOutputStream();
            AppendStream value2 = ourFlyweightAppenderStream.getValue();
            value2.setOut(bufferExposingByteArrayOutputStream);
            this.myValueExternalizer.save(value2, value);
            value2.setOut(null);
            jAppendBytes = this.myValueStorage.appendBytes(bufferExposingByteArrayOutputStream.toByteArraySequence(), 0L);
        }
        long j2 = jAppendBytes;
        this.myEnumerator.lockStorageWrite();
        try {
            this.myEnumerator.markDirty(true);
            flushAppendCache(key);
            try {
                if (!this.myDirectlyStoreLongFileOffsetMode) {
                    int iEnumerate = enumerate(key);
                    if (this.myIntMapping) {
                        this.myEnumerator.myCollisionResolutionStorage.putInt(iEnumerate + this.myParentValueRefOffset, ((Integer) value).intValue());
                        persistentEnumeratorBase = this.myEnumerator;
                    } else {
                        long valueId = readValueId(iEnumerate);
                        persistentMapImpl = this;
                        try {
                            persistentMapImpl.updateValueId(iEnumerate, j2, valueId, key, 0);
                            nonNegativeValue = valueId;
                            j = persistentMapImpl.myLiveAndGarbageKeysCounter;
                            if (nonNegativeValue != 0) {
                                persistentMapImpl.myLiveAndGarbageKeysCounter = j + 1;
                            } else {
                                persistentMapImpl.myLiveAndGarbageKeysCounter = j + 4294967296L;
                            }
                            persistentEnumeratorBase = persistentMapImpl.myEnumerator;
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    th = th;
                    persistentMapImpl.myEnumerator.unlockStorageWrite();
                    throw th;
                }
                boolean z = this.myIntMapping;
                PersistentEnumeratorBase persistentEnumeratorBase2 = this.myEnumerator;
                if (z) {
                    ((PersistentBTreeEnumerator) persistentEnumeratorBase2).putNonNegativeValue(key, ((Integer) value).intValue());
                    persistentEnumeratorBase = this.myEnumerator;
                } else {
                    nonNegativeValue = ((PersistentBTreeEnumerator) persistentEnumeratorBase2).getNonNegativeValue(key);
                    ((PersistentBTreeEnumerator) this.myEnumerator).putNonNegativeValue(key, j2);
                    persistentMapImpl = this;
                    j = persistentMapImpl.myLiveAndGarbageKeysCounter;
                    if (nonNegativeValue != 0) {
                        persistentMapImpl.myLiveAndGarbageKeysCounter = j + 1;
                    } else {
                        persistentMapImpl.myLiveAndGarbageKeysCounter = j + 4294967296L;
                    }
                    persistentEnumeratorBase = persistentMapImpl.myEnumerator;
                }
                persistentEnumeratorBase.unlockStorageWrite();
            } catch (Throwable th3) {
                th = th3;
                persistentMapImpl = this;
            }
        } catch (Throwable th4) {
            th = th4;
            persistentMapImpl = this;
        }
    }

    private void doRemove(Key key) throws Throwable {
        PersistentMapImpl<Key, Value> persistentMapImpl;
        Throwable th;
        long valueId;
        PersistentEnumeratorBase persistentEnumeratorBase;
        this.myEnumerator.lockStorageWrite();
        try {
            flushAppendCache(key);
            if (!this.myDirectlyStoreLongFileOffsetMode) {
                int iTryEnumerate = this.myEnumerator.tryEnumerate(key);
                if (iTryEnumerate == 0) {
                    persistentEnumeratorBase = this.myEnumerator;
                } else {
                    this.myEnumerator.markDirty(true);
                    valueId = readValueId(iTryEnumerate);
                    persistentMapImpl = this;
                    try {
                        persistentMapImpl.updateValueId(iTryEnumerate, 0L, valueId, key, 0);
                    } catch (Throwable th2) {
                        th = th2;
                        th = th;
                        persistentMapImpl.myEnumerator.unlockStorageWrite();
                        throw th;
                    }
                }
                persistentEnumeratorBase.unlockStorageWrite();
            }
            try {
                long nonNegativeValue = ((PersistentBTreeEnumerator) this.myEnumerator).getNonNegativeValue(key);
                if (nonNegativeValue != 0) {
                    ((PersistentBTreeEnumerator) this.myEnumerator).putNonNegativeValue(key, 0L);
                }
                valueId = nonNegativeValue;
                persistentMapImpl = this;
            } catch (Throwable th3) {
                th = th3;
                persistentMapImpl = this;
                persistentMapImpl.myEnumerator.unlockStorageWrite();
                throw th;
            }
            if (valueId != 0) {
                persistentMapImpl.myLiveAndGarbageKeysCounter -= 4294967295L;
            }
            persistentEnumeratorBase = persistentMapImpl.myEnumerator;
            persistentEnumeratorBase.unlockStorageWrite();
        } catch (Throwable th4) {
            th = th4;
            persistentMapImpl = this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
    public int enumerate(Key key) throws IncorrectOperationException, IOException {
        if (this.myIsReadOnly) {
            throw new IncorrectOperationException();
        }
        getWriteLock().lock();
        try {
            this.myIntAddressForNewRecord = canUseIntAddressForNewRecord(this.myValueStorage.getSize());
            return this.myEnumerator.enumerate(key);
        } finally {
            getWriteLock().unlock();
        }
    }

    public static /* synthetic */ void f(PersistentMapImpl persistentMapImpl) throws IOException {
        persistentMapImpl.myEnumerator.putMetaData(persistentMapImpl.myLiveAndGarbageKeysCounter);
        persistentMapImpl.myEnumerator.putMetaData2(((long) persistentMapImpl.myLargeIndexWatermarkId) | (((long) persistentMapImpl.myReadCompactionGarbageSize) << 32));
    }

    private void flushAppendCache(Key key) {
        SLRUCache<Key, BufferExposingByteArrayOutputStream> sLRUCache = this.myAppendCache;
        if (sLRUCache != null) {
            sLRUCache.remove(key);
        }
    }

    private boolean forceNewCompact() {
        return System.getProperty("idea.persistent.hash.map.newcompact") != null && ((int) (this.myLiveAndGarbageKeysCounter & 4294967295L)) > 0;
    }

    public static Path getDataFile(Path path) {
        if (path == null) {
            $$$reportNull$$$0(13);
        }
        Path pathResolveSibling = path.resolveSibling(path.getFileName() + ".values");
        if (pathResolveSibling == null) {
            $$$reportNull$$$0(14);
        }
        return pathResolveSibling;
    }

    private static File[] getFilesInDirectoryWithNameStartingWith(Path path) throws IOException {
        if (path == null) {
            $$$reportNull$$$0(19);
        }
        Path parent = path.getParent();
        if (parent == null) {
            return ArrayUtil.EMPTY_FILE_ARRAY;
        }
        final Path fileName = path.getFileName();
        Stream<Path> list = Files.list(parent);
        try {
            File[] fileArr = (File[]) list.filter(new Predicate() { // from class: c1b
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((Path) obj).getFileName().toString().startsWith(fileName.toString());
                }
            }).map(new gii()).toArray(new IntFunction() { // from class: d1b
                @Override // java.util.function.IntFunction
                public final Object apply(int i) {
                    return PersistentMapImpl.d(i);
                }
            });
            list.close();
            return fileArr;
        } catch (Throwable th) {
            if (list != null) {
                try {
                    list.close();
                } catch (Throwable th2) {
                    th.addSuppressed(th2);
                }
            }
            throw th;
        }
    }

    private Lock getReadLock() {
        boolean z = PersistentEnumeratorBase.USE_RW_LOCK;
        ReentrantReadWriteLock reentrantReadWriteLock = this.myLock;
        Lock lock = z ? reentrantReadWriteLock.readLock() : reentrantReadWriteLock.writeLock();
        if (lock == null) {
            $$$reportNull$$$0(10);
        }
        return lock;
    }

    private Lock getWriteLock() {
        ReentrantReadWriteLock.WriteLock writeLock = this.myLock.writeLock();
        if (writeLock == null) {
            $$$reportNull$$$0(9);
        }
        return writeLock;
    }

    private static int modifyVersionDependingOnOptions(int i, PersistentHashMapValueStorage.CreationTimeOptions creationTimeOptions) {
        if (creationTimeOptions == null) {
            $$$reportNull$$$0(7);
        }
        return i + creationTimeOptions.getVersion();
    }

    private void newCompact(PersistentHashMapValueStorage persistentHashMapValueStorage) throws Throwable {
        long jCompactValues;
        PersistentMapImpl<Key, Value> persistentMapImpl;
        if (persistentHashMapValueStorage == null) {
            $$$reportNull$$$0(20);
        }
        long jCurrentTimeMillis = System.currentTimeMillis();
        final ArrayList<CompactionRecordInfo> arrayList = new ArrayList(10000);
        this.myEnumerator.traverseAllRecords(new PersistentEnumeratorBase.RecordsProcessor() { // from class: com.intellij.util.io.PersistentMapImpl.5
            @Override // com.intellij.util.io.PersistentEnumeratorBase.RecordsProcessor
            public boolean process(int i) throws IOException {
                long valueId = PersistentMapImpl.this.readValueId(i);
                if (valueId == 0) {
                    return true;
                }
                arrayList.add(new CompactionRecordInfo(getCurrentKey(), valueId, i));
                return true;
            }
        });
        Logger logger = LOG;
        logger.info("Loaded mappings:" + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms, keys:" + arrayList.size());
        long jCurrentTimeMillis2 = System.currentTimeMillis();
        if (arrayList.isEmpty()) {
            jCompactValues = 0;
        } else {
            try {
                jCompactValues = this.myValueStorage.compactValues(arrayList, persistentHashMapValueStorage);
            } catch (IOException e) {
                throw e;
            } catch (Throwable th) {
                dk3.a("Compaction failed", th);
                return;
            }
        }
        logger.info("Compacted values for:" + (System.currentTimeMillis() - jCurrentTimeMillis2) + "ms fragments:" + ((int) jCompactValues) + ", new fragments:" + (jCompactValues >> 32));
        long jCurrentTimeMillis3 = System.currentTimeMillis();
        this.myEnumerator.lockStorageWrite();
        try {
            for (CompactionRecordInfo compactionRecordInfo : arrayList) {
                persistentMapImpl = this;
                try {
                    persistentMapImpl.updateValueId(compactionRecordInfo.address, compactionRecordInfo.newValueAddress, compactionRecordInfo.valueAddress, null, compactionRecordInfo.key);
                    persistentMapImpl.myLiveAndGarbageKeysCounter += 4294967296L;
                    this = persistentMapImpl;
                } catch (Throwable th2) {
                    th = th2;
                    Throwable th3 = th;
                    persistentMapImpl.myEnumerator.unlockStorageWrite();
                    throw th3;
                }
            }
            this.myEnumerator.unlockStorageWrite();
            LOG.info("Updated mappings:" + (System.currentTimeMillis() - jCurrentTimeMillis3) + " ms");
        } catch (Throwable th4) {
            th = th4;
            persistentMapImpl = this;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public long readValueId(int i) throws IOException {
        boolean z = this.myDirectlyStoreLongFileOffsetMode;
        PersistentEnumeratorBase persistentEnumeratorBase = this.myEnumerator;
        if (z) {
            return ((PersistentBTreeEnumerator) persistentEnumeratorBase).keyIdToNonNegativeOffset(i);
        }
        long j = persistentEnumeratorBase.myCollisionResolutionStorage.getInt(this.myParentValueRefOffset + i);
        if (j == 0 || j == -1) {
            return 0L;
        }
        if (j < 0) {
            return (-j) - 1;
        }
        return (-4611686018427387905L) & ((j << 32) + (((long) this.myEnumerator.myCollisionResolutionStorage.getInt(i + this.myParentValueRefOffset + 4)) & 4294967295L));
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:596)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void updateValueId(int i, long j, long j2, Key key, int i2) throws IOException {
        if (this.myDirectlyStoreLongFileOffsetMode) {
            ((PersistentBTreeEnumerator) this.myEnumerator).putNonNegativeValue(((InlineKeyDescriptor) this.myKeyDescriptor).fromInt(i2), j);
            return;
        }
        boolean z = false;
        boolean z2 = j2 == 0;
        if (z2) {
            this.requests++;
        }
        if (!this.myCanReEnumerate) {
            z = true;
        } else if (canUseIntAddressForNewRecord(j)) {
            this.myEnumerator.myCollisionResolutionStorage.putInt(this.myParentValueRefOffset + i, -((int) (1 + j)));
            if (z2) {
                this.smallKeys++;
            }
        } else {
            int i3 = this.myLargeIndexWatermarkId;
            if ((i < i3 || i3 == 0) && (z2 || canUseIntAddressForNewRecord(j2))) {
                this.myIntAddressForNewRecord = false;
                PersistentEnumeratorBase persistentEnumeratorBase = this.myEnumerator;
                if (key == null) {
                    key = (Key) persistentEnumeratorBase.getValue(i, i2);
                }
                i = persistentEnumeratorBase.reEnumerate(key);
                this.transformedKeys++;
                if (this.myLargeIndexWatermarkId == 0) {
                    this.myLargeIndexWatermarkId = i;
                }
            }
            z = true;
        }
        if (z) {
            long j3 = j | SieveCacheKt.NodeVisitedBit;
            this.myEnumerator.myCollisionResolutionStorage.putInt(this.myParentValueRefOffset + i, (int) (j3 >>> 32));
            this.myEnumerator.myCollisionResolutionStorage.putInt(i + this.myParentValueRefOffset + 4, (int) j3);
            if (z2) {
                this.largeKeys++;
            }
        }
        if (z2 && IOStatistics.DEBUG && (this.requests & 65535) == 0) {
            IOStatistics.dump("small:" + this.smallKeys + ", large:" + this.largeKeys + ", transformed:" + this.transformedKeys + ",@" + getBaseFile());
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
    @Override // com.intellij.util.io.PersistentMapBase
    public void appendData(Key key, AppendablePersistentMap.ValueDataAppender valueDataAppender) throws IncorrectOperationException, IOException {
        if (valueDataAppender == null) {
            $$$reportNull$$$0(15);
        }
        if (this.myIsReadOnly) {
            throw new IncorrectOperationException();
        }
        PersistentMapWal<Key, Value> persistentMapWal = this.myWal;
        if (persistentMapWal != null) {
            persistentMapWal.appendData(key, valueDataAppender);
        }
        getWriteLock().lock();
        try {
            try {
                try {
                    doAppendData(key, valueDataAppender);
                    getWriteLock().unlock();
                } catch (IOException e) {
                    markCorrupted();
                    throw e;
                }
            } catch (ClosedStorageException e2) {
                throw e2;
            }
        } catch (Throwable th) {
            getWriteLock().unlock();
            throw th;
        }
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public void closeAndDelete() {
        Path baseFile = getBaseFile();
        try {
            close(true);
        } catch (IOException unused) {
        }
        IOUtil.deleteAllFilesStartingWith(baseFile);
        try {
            PersistentMapWal<Key, Value> persistentMapWal = this.myWal;
            if (persistentMapWal != null) {
                persistentMapWal.closeAndDelete();
            }
        } catch (IOException unused2) {
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
    public void compact() throws IncorrectOperationException, IOException {
        if (!isCompactionSupported()) {
            throw new IncorrectOperationException();
        }
        getWriteLock().lock();
        try {
            force();
            Logger logger = LOG;
            logger.info("Compacting " + this.myEnumerator.myFile);
            logger.info("Live keys:" + ((int) (this.myLiveAndGarbageKeysCounter / 4294967296L)) + ", dead keys:" + ((int) (this.myLiveAndGarbageKeysCounter & 4294967295L)) + ", read compaction size:" + this.myReadCompactionGarbageSize);
            long jCurrentTimeMillis = System.currentTimeMillis();
            Path dataFile = getDataFile(this.myEnumerator.myFile);
            File[] filesInDirectoryWithNameStartingWith = getFilesInDirectoryWithNameStartingWith(dataFile);
            Path pathResolveSibling = dataFile.resolveSibling(dataFile.getFileName() + ".new");
            PersistentHashMapValueStorage.CreationTimeOptions options = this.myValueStorage.getOptions();
            final PersistentHashMapValueStorage persistentHashMapValueStorage = new PersistentHashMapValueStorage(pathResolveSibling, options);
            this.myValueStorage.switchToCompactionMode();
            this.myEnumerator.markDirty(true);
            long size = this.myValueStorage.getSize();
            this.myLiveAndGarbageKeysCounter = 0L;
            int i = 0;
            this.myReadCompactionGarbageSize = 0;
            try {
                if (doNewCompact()) {
                    newCompact(persistentHashMapValueStorage);
                } else {
                    this.myEnumerator.traverseAllRecords(new PersistentEnumeratorBase.RecordsProcessor() { // from class: com.intellij.util.io.PersistentMapImpl.4
                        @Override // com.intellij.util.io.PersistentEnumeratorBase.RecordsProcessor
                        public boolean process(int i2) throws Exception {
                            long valueId = PersistentMapImpl.this.readValueId(i2);
                            if (valueId == 0) {
                                return true;
                            }
                            PersistentHashMapValueStorage.ReadResult bytes = PersistentMapImpl.this.myValueStorage.readBytes(valueId);
                            PersistentHashMapValueStorage persistentHashMapValueStorage2 = persistentHashMapValueStorage;
                            byte[] bArr = bytes.buffer;
                            PersistentMapImpl.this.updateValueId(i2, persistentHashMapValueStorage2.appendBytes(bArr, 0, bArr.length, 0L), valueId, null, getCurrentKey());
                            PersistentMapImpl.access$714(PersistentMapImpl.this, 4294967296L);
                            return true;
                        }
                    });
                }
                persistentHashMapValueStorage.dispose();
                this.myValueStorage.dispose();
                for (File file : filesInDirectoryWithNameStartingWith) {
                }
                long size2 = persistentHashMapValueStorage.getSize();
                File[] filesInDirectoryWithNameStartingWith2 = getFilesInDirectoryWithNameStartingWith(pathResolveSibling);
                File file2 = pathResolveSibling.getParent().toFile();
                String string = pathResolveSibling.getFileName().toString();
                String string2 = dataFile.getFileName().toString();
                int length = filesInDirectoryWithNameStartingWith2.length;
                while (i < length) {
                    File file3 = filesInDirectoryWithNameStartingWith2[i];
                    FileUtil.rename(file3, new File(file2, StringUtil.replace(file3.getName(), string, string2)));
                    i++;
                    jCurrentTimeMillis = jCurrentTimeMillis;
                }
                this.myValueStorage = new PersistentHashMapValueStorage(dataFile, options);
                Logger logger2 = LOG;
                logger2.info("Compacted " + this.myEnumerator.myFile + ":" + size + " bytes into " + size2 + " bytes in " + (System.currentTimeMillis() - jCurrentTimeMillis) + "ms.");
                this.myEnumerator.putMetaData(this.myLiveAndGarbageKeysCounter);
                this.myEnumerator.putMetaData2((long) this.myLargeIndexWatermarkId);
                if (myDoTrace) {
                    logger2.assertTrue(this.myEnumerator.isDirty());
                }
                getWriteLock().unlock();
            } catch (Throwable th) {
                persistentHashMapValueStorage.dispose();
                throw th;
            }
        } catch (Throwable th2) {
            getWriteLock().unlock();
            throw th2;
        }
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public boolean containsKey(Key key) throws IOException {
        getReadLock().lock();
        try {
            return doContainsMapping(key);
        } finally {
            getReadLock().unlock();
        }
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public void force() throws IOException {
        if (this.myIsReadOnly) {
            return;
        }
        if (myDoTrace) {
            LOG.info("Forcing " + this.myStorageFile);
        }
        PersistentMapWal<Key, Value> persistentMapWal = this.myWal;
        if (persistentMapWal != null) {
            persistentMapWal.flush();
        }
        getWriteLock().lock();
        try {
            doForce();
        } finally {
            getWriteLock().unlock();
        }
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public Value get(Key key) throws IOException {
        getReadLock().lock();
        try {
            try {
                try {
                    Value valueDoGet = doGet(key);
                    getReadLock().unlock();
                    return valueDoGet;
                } catch (IOException e) {
                    markCorrupted();
                    throw e;
                }
            } catch (ClosedStorageException e2) {
                throw e2;
            }
        } catch (Throwable th) {
            getReadLock().unlock();
            throw th;
        }
    }

    public Path getBaseFile() {
        return this.myEnumerator.myFile;
    }

    public int getSize() {
        return (int) (this.myLiveAndGarbageKeysCounter / 4294967296L);
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public DataExternalizer<Value> getValuesExternalizer() {
        DataExternalizer<Value> dataExternalizer = this.myValueExternalizer;
        if (dataExternalizer == null) {
            $$$reportNull$$$0(4);
        }
        return dataExternalizer;
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public boolean isClosed() {
        return this.myEnumerator.isClosed();
    }

    public boolean isCompactionSupported() {
        return (this.myIsReadOnly || this.myIntMapping) ? false : true;
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public boolean isDirty() {
        return this.myEnumerator.isDirty();
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public int keysCount() {
        return getSize();
    }

    public boolean makesSenseToCompact() {
        if (!isCompactionSupported()) {
            return false;
        }
        long size = this.myValueStorage.getSize();
        if (size <= 5242880) {
            return false;
        }
        long j = this.myLiveAndGarbageKeysCounter;
        int i = (int) (j / 4294967296L);
        int i2 = (int) (j & 4294967295L);
        if (size > 52428800 && forceNewCompact()) {
            return true;
        }
        if (i2 < 50) {
            return false;
        }
        return i2 > i || (size / ((long) (i + i2))) * ((long) i2) > Math.max(104857600L, size / 4) || ((long) this.myReadCompactionGarbageSize) > size / 2;
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public void markCorrupted() {
        if (this.myStorageFile.getFileSystem().isReadOnly()) {
            return;
        }
        try {
            this.myEnumerator.markCorrupted();
        } catch (Exception e) {
            LOG.warn(e);
        }
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public void markDirty() throws IOException {
        this.myEnumerator.markDirty(true);
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public boolean processExistingKeys(Processor<? super Key> processor) throws IOException {
        if (processor == null) {
            $$$reportNull$$$0(18);
        }
        getReadLock().lock();
        try {
            try {
                flushAppendCache();
                boolean zProcessAllDataObject = this.myEnumerator.processAllDataObject(processor, new PersistentEnumeratorBase.DataFilter() { // from class: e1b
                    @Override // com.intellij.util.io.PersistentEnumeratorBase.DataFilter
                    public final boolean accept(int i) {
                        return PersistentMapImpl.b(this.a, i);
                    }
                });
                getReadLock().unlock();
                return zProcessAllDataObject;
            } catch (ClosedStorageException e) {
                throw e;
            } catch (IOException e2) {
                markCorrupted();
                throw e2;
            }
        } catch (Throwable th) {
            getReadLock().unlock();
            throw th;
        }
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public boolean processKeys(Processor<? super Key> processor) throws IOException {
        if (processor == null) {
            $$$reportNull$$$0(17);
        }
        try {
            Lock readLock = getReadLock();
            readLock.lock();
            try {
                flushAppendCache();
                return this.myEnumerator.iterateData(processor);
            } finally {
                readLock.unlock();
            }
        } catch (ClosedStorageException e) {
            throw e;
        } catch (IOException e2) {
            markCorrupted();
            throw e2;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
    @Override // com.intellij.util.io.PersistentMapBase
    public void put(Key key, Value value) throws IncorrectOperationException, IOException {
        if (this.myIsReadOnly) {
            throw new IncorrectOperationException();
        }
        PersistentMapWal<Key, Value> persistentMapWal = this.myWal;
        if (persistentMapWal != null) {
            persistentMapWal.put(key, value);
        }
        getWriteLock().lock();
        try {
            try {
                doPut(key, value);
                getWriteLock().unlock();
            } catch (ClosedStorageException e) {
                throw e;
            } catch (IOException e2) {
                markCorrupted();
                throw e2;
            }
        } catch (Throwable th) {
            getWriteLock().unlock();
            throw th;
        }
    }

    /* JADX INFO: Thrown type has an unknown type hierarchy: com.intellij.util.IncorrectOperationException */
    @Override // com.intellij.util.io.PersistentMapBase
    public void remove(Key key) throws IncorrectOperationException, IOException {
        if (this.myIsReadOnly) {
            throw new IncorrectOperationException();
        }
        PersistentMapWal<Key, Value> persistentMapWal = this.myWal;
        if (persistentMapWal != null) {
            persistentMapWal.remove(key);
        }
        getWriteLock().lock();
        try {
            doRemove(key);
        } finally {
            getWriteLock().unlock();
        }
    }

    public String toString() {
        return PersistentMapImpl.class.getName() + "@" + Integer.toHexString(hashCode()) + ": " + this.myStorageFile;
    }

    private void flushAppendCache() {
        SLRUCache<Key, BufferExposingByteArrayOutputStream> sLRUCache = this.myAppendCache;
        if (sLRUCache != null) {
            sLRUCache.clear();
        }
    }

    @Override // com.intellij.util.io.PersistentMapBase
    public void close() throws IOException {
        close(false);
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public PersistentMapImpl(PersistentMapBuilder<Key, Value> persistentMapBuilder) throws IOException {
        this(persistentMapBuilder, PersistentHashMapValueStorage.CreationTimeOptions.threadLocalOptions());
        if (persistentMapBuilder == null) {
            $$$reportNull$$$0(0);
        }
    }
}
