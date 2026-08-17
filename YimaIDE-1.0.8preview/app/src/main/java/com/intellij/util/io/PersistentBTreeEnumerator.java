package com.intellij.util.io;

import androidx.collection.SieveCacheKt;
import com.intellij.openapi.diagnostic.Logger;
import com.intellij.util.ArrayUtilRt;
import com.intellij.util.ConcurrencyUtil;
import com.intellij.util.Processor;
import com.intellij.util.SystemProperties;
import com.intellij.util.io.PersistentBTreeEnumerator;
import com.intellij.util.io.keyStorage.AppendableObjectStorage;
import com.intellij.util.io.stats.PersistentEnumeratorStatistics;
import com.intellij.util.io.stats.StorageStatsRegistrar;
import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.function.Supplier;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public class PersistentBTreeEnumerator extends PersistentEnumeratorBase implements DurableDataEnumerator {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private IntToIntBtree myBTree;
    private int myCollisions;
    private int myDataPageOffset;
    private int myDataPageStart;
    private int myDuplicatedValuesPageOffset;
    private int myDuplicatedValuesPageStart;
    private boolean myExternalKeysNoMapping;
    private int myFirstPageStart;
    private final boolean myInlineKeysNoMapping;
    private int myLogicalFileLength;
    private final boolean myRegisterForStats;
    private final int[] myResultBuf;
    private int myValuesCount;
    private final PersistentEnumeratorWal<Object> myWal;
    private static final boolean DO_EXPENSIVE_CHECKS = SystemProperties.getBooleanProperty("idea.persistent.enumerator.do.expensive.checks", false);
    private static final int BTREE_PAGE_SIZE = SystemProperties.getIntProperty("idea.btree.page.size", 32768);

    /* JADX INFO: renamed from: com.intellij.util.io.PersistentBTreeEnumerator$1DataWithOffset, reason: invalid class name */
    public class C1DataWithOffset {
        final Object data;
        final int offset;

        public C1DataWithOffset(Object obj, int i) {
            this.data = obj;
            this.offset = i;
        }
    }

    public static class RecordBufferHandler extends PersistentEnumeratorBase.RecordBufferHandler<PersistentBTreeEnumerator> {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private ThreadLocal<byte[]> myBuffer;

        private static /* synthetic */ void $$$reportNull$$$0(int i) {
            String str = i != 3 ? "Argument for @NotNull parameter '%s' of %s.%s must not be null" : "@NotNull method %s.%s must not return null";
            Object[] objArr = new Object[i != 3 ? 3 : 2];
            if (i == 1) {
                objArr[0] = "buf";
            } else if (i != 3) {
                objArr[0] = "enumerator";
            } else {
                objArr[0] = "com/intellij/util/io/PersistentBTreeEnumerator$RecordBufferHandler";
            }
            if (i != 3) {
                objArr[1] = "com/intellij/util/io/PersistentBTreeEnumerator$RecordBufferHandler";
            } else {
                objArr[1] = "getRecordBuffer";
            }
            if (i == 2) {
                objArr[2] = "getRecordBuffer";
            } else if (i != 3) {
                if (i != 4) {
                    objArr[2] = "recordWriteOffset";
                } else {
                    objArr[2] = "setupRecord";
                }
            }
            String str2 = String.format(str, objArr);
            if (i == 3) {
                throw new IllegalStateException(str2);
            }
        }

        private RecordBufferHandler() {
        }

        public static /* synthetic */ byte[] a(PersistentBTreeEnumerator persistentBTreeEnumerator) {
            return persistentBTreeEnumerator.myInlineKeysNoMapping ? ArrayUtilRt.EMPTY_BYTE_ARRAY : new byte[4];
        }

        @Override // com.intellij.util.io.PersistentEnumeratorBase.RecordBufferHandler
        public byte[] getRecordBuffer(final PersistentBTreeEnumerator persistentBTreeEnumerator) {
            if (persistentBTreeEnumerator == null) {
                $$$reportNull$$$0(2);
            }
            if (this.myBuffer == null) {
                this.myBuffer = ThreadLocal.withInitial(new Supplier() { // from class: com.intellij.util.io.g
                    @Override // java.util.function.Supplier
                    public final Object get() {
                        return PersistentBTreeEnumerator.RecordBufferHandler.a(persistentBTreeEnumerator);
                    }
                });
            }
            byte[] bArr = this.myBuffer.get();
            if (bArr == null) {
                $$$reportNull$$$0(3);
            }
            return bArr;
        }

        @Override // com.intellij.util.io.PersistentEnumeratorBase.RecordBufferHandler
        public int recordWriteOffset(PersistentBTreeEnumerator persistentBTreeEnumerator, byte[] bArr) throws IOException {
            if (persistentBTreeEnumerator == null) {
                $$$reportNull$$$0(0);
            }
            if (bArr == null) {
                $$$reportNull$$$0(1);
            }
            if (persistentBTreeEnumerator.myFirstPageStart == -1) {
                persistentBTreeEnumerator.myFirstPageStart = persistentBTreeEnumerator.myDataPageStart = persistentBTreeEnumerator.allocPage();
                int i = persistentBTreeEnumerator.myDataPageStart % 4096;
                persistentBTreeEnumerator.myDataPageOffset = i;
                PersistentBTreeEnumerator.access$420(persistentBTreeEnumerator, i);
            }
            if (persistentBTreeEnumerator.myDataPageOffset + bArr.length + 4 > 4096) {
                int i2 = persistentBTreeEnumerator.myDataPageStart + 4092;
                persistentBTreeEnumerator.myDataPageStart = persistentBTreeEnumerator.allocPage();
                persistentBTreeEnumerator.myCollisionResolutionStorage.putInt(i2, persistentBTreeEnumerator.myDataPageStart);
                persistentBTreeEnumerator.myDataPageOffset = 0;
            }
            int i3 = persistentBTreeEnumerator.myDataPageOffset;
            PersistentBTreeEnumerator.access$612(persistentBTreeEnumerator, bArr.length);
            return i3 + persistentBTreeEnumerator.myDataPageStart;
        }

        @Override // com.intellij.util.io.PersistentEnumeratorBase.RecordBufferHandler
        public void setupRecord(PersistentBTreeEnumerator persistentBTreeEnumerator, int i, int i2, byte[] bArr) {
            if (persistentBTreeEnumerator == null) {
                $$$reportNull$$$0(4);
            }
            if (persistentBTreeEnumerator.myInlineKeysNoMapping) {
                return;
            }
            Bits.putInt(bArr, 0, i2);
        }
    }

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str = (i == 9 || i == 14) ? "@NotNull method %s.%s must not return null" : "Argument for @NotNull parameter '%s' of %s.%s must not be null";
        Object[] objArr = new Object[(i == 9 || i == 14) ? 2 : 3];
        if (i != 1 && i != 3 && i != 5 && i != 7) {
            switch (i) {
                case 9:
                case 14:
                    objArr[0] = "com/intellij/util/io/PersistentBTreeEnumerator";
                    break;
                case 10:
                    objArr[0] = "processor";
                    break;
                case 11:
                    objArr[0] = "reader";
                    break;
                case 12:
                    objArr[0] = "p";
                    break;
                case 13:
                    objArr[0] = "recordHandler";
                    break;
                default:
                    objArr[0] = "file";
                    break;
            }
        } else {
            objArr[0] = "dataDescriptor";
        }
        if (i == 9) {
            objArr[1] = "indexFile";
        } else if (i != 14) {
            objArr[1] = "com/intellij/util/io/PersistentBTreeEnumerator";
        } else {
            objArr[1] = "getStatistics";
        }
        switch (i) {
            case 8:
                objArr[2] = "indexFile";
                break;
            case 9:
            case 14:
                break;
            case 10:
                objArr[2] = "processAllDataObject";
                break;
            case 11:
                objArr[2] = "forEach";
                break;
            case 12:
                objArr[2] = "traverseAllRecords";
                break;
            case 13:
                objArr[2] = "setRecordHandler";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        if (i != 9 && i != 14) {
            throw new IllegalArgumentException(str2);
        }
        throw new IllegalStateException(str2);
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    /* JADX WARN: Multi-variable type inference failed */
    public PersistentBTreeEnumerator(Path path, KeyDescriptor<Object> keyDescriptor, int i, StorageLockContext storageLockContext, int i2, boolean z, boolean z2) throws ExecutionException, InterruptedException, IOException {
        super(path, new ResizeableMappedFile(path, i, storageLockContext, IOUtil.MiB, true, IOUtil.useNativeByteOrderForByteBuffers()), keyDescriptor, i, new PersistentEnumeratorBase.Version(baseVersion() + i2), new RecordBufferHandler(), false);
        if (path == null) {
            $$$reportNull$$$0(6);
        }
        if (keyDescriptor == null) {
            $$$reportNull$$$0(7);
        }
        PersistentEnumeratorWal<Object> persistentEnumeratorWal = null;
        this.myResultBuf = new int[1];
        boolean z3 = keyDescriptor instanceof InlineKeyDescriptor;
        this.myInlineKeysNoMapping = z3;
        this.myExternalKeysNoMapping = true ^ z3;
        this.myRegisterForStats = z2;
        if (z2) {
            StorageStatsRegistrar.INSTANCE.registerEnumerator(path, this);
        }
        lockStorageWrite();
        try {
            try {
                try {
                    storeVars(false);
                    initBtree(false);
                    storeBTreeVars(false);
                    unlockStorageWrite();
                    try {
                        diagnose();
                        if (z) {
                            persistentEnumeratorWal = new PersistentEnumeratorWal<>(keyDescriptor, false, path.resolveSibling(path.getFileName() + ".wal"), ConcurrencyUtil.newSameThreadExecutorService(), true);
                        }
                        this.myWal = persistentEnumeratorWal;
                    } catch (Throwable th) {
                        close();
                        throw th;
                    }
                } catch (Throwable th2) {
                    PersistentEnumeratorBase.LOG.info(th2);
                    try {
                        close();
                    } catch (Throwable unused) {
                    }
                    throw new CorruptedException("PersistentEnumerator storage corrupted " + path, th2);
                }
            } catch (IOException e) {
                try {
                    close();
                    throw e;
                } catch (Throwable th3) {
                    e.addSuppressed(th3);
                    throw e;
                }
            }
        } catch (Throwable th4) {
            unlockStorageWrite();
            throw th4;
        }
    }

    public static /* synthetic */ int access$420(PersistentBTreeEnumerator persistentBTreeEnumerator, int i) {
        int i2 = persistentBTreeEnumerator.myDataPageStart - i;
        persistentBTreeEnumerator.myDataPageStart = i2;
        return i2;
    }

    public static /* synthetic */ int access$612(PersistentBTreeEnumerator persistentBTreeEnumerator, int i) {
        int i2 = persistentBTreeEnumerator.myDataPageOffset + i;
        persistentBTreeEnumerator.myDataPageOffset = i2;
        return i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int allocPage() {
        int i = this.myLogicalFileLength;
        this.myLogicalFileLength = (4096 - (i % 4096)) + i;
        return i;
    }

    public static int baseVersion() {
        return AbstractIntToIntBtree.version() + 8 + BTREE_PAGE_SIZE + 4224;
    }

    private void doExpensiveSanityCheck() {
        try {
            PersistentEnumeratorBase.LOG.info("Doing self diagnostic for " + this.myFile);
            final ArrayList arrayList = new ArrayList();
            iterateData(new Processor() { // from class: q0b
                @Override // com.intellij.util.Processor
                public final boolean process(Object obj) {
                    return PersistentBTreeEnumerator.h(arrayList, obj);
                }
            });
            for (int i = 0; i < arrayList.size(); i++) {
                try {
                    Object obj = arrayList.get(i);
                    int i2 = i + 1;
                    if (tryEnumerate(obj) != i2) {
                        throw new IOException(this.myFile + " is corrupted");
                    }
                    if (!this.myDataDescriptor.isEqual(valueOf(i2), obj)) {
                        throw new IOException(this.myFile + " is corrupted");
                    }
                } catch (Exception e) {
                    PersistentEnumeratorBase.LOG.error(e);
                }
            }
        } catch (Throwable th) {
            PersistentEnumeratorBase.LOG.error(th);
        }
    }

    public static /* synthetic */ boolean f(PersistentBTreeEnumerator persistentBTreeEnumerator, List list, int i, Object obj) {
        persistentBTreeEnumerator.getClass();
        list.add(persistentBTreeEnumerator.new C1DataWithOffset(obj, i));
        return true;
    }

    public static /* synthetic */ boolean g(int i, Object obj) {
        PersistentEnumeratorBase.LOG.info("Enumerator entry '" + obj.toString() + "'");
        return true;
    }

    public static /* synthetic */ boolean h(List list, Object obj) {
        list.add(obj);
        return true;
    }

    private static Path indexFile(Path path) {
        if (path == null) {
            $$$reportNull$$$0(8);
        }
        Path pathResolveSibling = path.resolveSibling(path.getFileName() + "_i");
        if (pathResolveSibling == null) {
            $$$reportNull$$$0(9);
        }
        return pathResolveSibling;
    }

    private void initBtree(boolean z) throws Exception {
        IntToIntBtree intToIntBtree = this.myBTree;
        if (intToIntBtree != null) {
            intToIntBtree.doClose();
            this.myBTree = null;
        }
        this.myBTree = new IntToIntBtree(BTREE_PAGE_SIZE, indexFile(this.myFile), this.myCollisionResolutionStorage.getStorageLockContext(), z);
    }

    private int nextDuplicatedValueRecord() {
        if (this.myDuplicatedValuesPageStart == -1 || this.myDuplicatedValuesPageOffset == 4096) {
            int iAllocPage = allocPage();
            int i = iAllocPage % 4096;
            this.myDuplicatedValuesPageOffset = i;
            this.myDuplicatedValuesPageStart = iAllocPage - i;
        }
        int i2 = this.myDuplicatedValuesPageOffset;
        this.myDuplicatedValuesPageOffset = i2 + 8;
        return this.myDuplicatedValuesPageStart + i2;
    }

    private int nextLongValueRecord() {
        if (this.myDuplicatedValuesPageStart == -1 || this.myDuplicatedValuesPageOffset == 4096) {
            int iAllocPage = allocPage();
            int i = iAllocPage % 4096;
            this.myDuplicatedValuesPageOffset = i;
            this.myDuplicatedValuesPageStart = iAllocPage - i;
        }
        int i2 = this.myDuplicatedValuesPageOffset;
        this.myDuplicatedValuesPageOffset = i2 + 8;
        return this.myDuplicatedValuesPageStart + i2;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int store(int i, int i2, boolean z) throws IOException {
        if (!z) {
            return this.myCollisionResolutionStorage.getInt(i);
        }
        if (this.myFirstPageStart != -1 && this.myCollisionResolutionStorage.getInt(i) == i2) {
            return i2;
        }
        this.myCollisionResolutionStorage.putInt(i, i2);
        return i2;
    }

    private void storeBTreeVars(boolean z) throws IOException {
        IntToIntBtree intToIntBtree = this.myBTree;
        if (intToIntBtree != null) {
            intToIntBtree.persistVars(new AbstractIntToIntBtree.BtreeDataStorage() { // from class: com.intellij.util.io.PersistentBTreeEnumerator.1
                @Override // com.intellij.util.io.AbstractIntToIntBtree.BtreeDataStorage
                public int persistInt(int i, int i2, boolean z2) throws IOException {
                    return PersistentBTreeEnumerator.this.store(i + 56, i2, z2);
                }
            }, z);
        }
    }

    private void storeVars(boolean z) throws IOException {
        this.myLogicalFileLength = store(20, this.myLogicalFileLength, z);
        this.myDataPageStart = store(24, this.myDataPageStart, z);
        this.myDataPageOffset = store(28, this.myDataPageOffset, z);
        this.myFirstPageStart = store(32, this.myFirstPageStart, z);
        this.myDuplicatedValuesPageStart = store(36, this.myDuplicatedValuesPageStart, z);
        this.myDuplicatedValuesPageOffset = store(40, this.myDuplicatedValuesPageOffset, z);
        this.myValuesCount = store(44, this.myValuesCount, z);
        this.myCollisions = store(48, this.myCollisions, z);
        storeBTreeVars(z);
    }

    public int addrToIndex(int i) {
        return i + 1;
    }

    @Override // com.intellij.util.io.PersistentEnumeratorBase
    public boolean canReEnumerate() {
        return true;
    }

    @Override // com.intellij.util.io.PersistentEnumeratorBase, java.io.Closeable, java.lang.AutoCloseable
    public void close() throws ExecutionException, InterruptedException, IOException {
        try {
            super.close();
            if (this.myWal != null) {
            }
        } finally {
            if (this.myWal != null) {
                this.myWal.close();
            }
            if (this.myRegisterForStats) {
                StorageStatsRegistrar.INSTANCE.unregisterEnumerator(this.myFile);
            }
        }
    }

    public void diagnose() {
        if (!DO_EXPENSIVE_CHECKS || this.myInlineKeysNoMapping) {
            return;
        }
        doExpensiveSanityCheck();
    }

    @Override // com.intellij.util.io.PersistentEnumeratorBase
    public void doClose() throws Exception {
        try {
            super.doClose();
        } finally {
            IntToIntBtree intToIntBtree = this.myBTree;
            if (intToIntBtree != null) {
                intToIntBtree.doClose();
            }
        }
    }

    @Override // com.intellij.util.io.PersistentEnumeratorBase
    public void doFlush() throws IOException {
        this.myBTree.doFlush();
        storeVars(true);
        super.doFlush();
    }

    @Override // com.intellij.util.io.PersistentEnumeratorBase
    public void dumpKeysOnCorruption() {
        try {
            force();
        } catch (Exception unused) {
        }
        lockStorageWrite();
        try {
            Logger logger = PersistentEnumeratorBase.LOG;
            logger.info("Listing corrupted enumerator:");
            iterateData(new AppendableObjectStorage.StorageObjectProcessor() { // from class: r0b
                @Override // com.intellij.util.io.keyStorage.AppendableObjectStorage.StorageObjectProcessor
                public final boolean process(int i, Object obj) {
                    return PersistentBTreeEnumerator.g(i, obj);
                }
            });
            logger.info("Listing ended.");
        } catch (Throwable th) {
            try {
                PersistentEnumeratorBase.LOG.info(th);
            } finally {
                unlockStorageWrite();
            }
        }
    }

    @Override // com.intellij.util.io.PersistentEnumeratorBase
    public int enumerateImpl(Object obj, boolean z, boolean z2) throws IOException {
        boolean z3;
        int iNextDuplicatedValueRecord;
        (z ? getReadLock() : getWriteLock()).lock();
        try {
            if (z) {
                lockStorageRead();
            } else {
                lockStorageWrite();
            }
            try {
                int hashCode = this.myDataDescriptor.getHashCode(obj);
                boolean z4 = this.myBTree.get(hashCode, this.myResultBuf);
                if (!z4 && z) {
                    if (z) {
                        unlockStorageRead();
                    } else {
                        unlockStorageWrite();
                    }
                    (z ? getReadLock() : getWriteLock()).unlock();
                    return 0;
                }
                int i = z4 ? this.myResultBuf[0] : 0;
                if (!this.myInlineKeysNoMapping) {
                    if (i > 0) {
                        if (!isKeyAtIndex(obj, i)) {
                            z3 = false;
                        } else {
                            if (!z2) {
                                if (z) {
                                    unlockStorageRead();
                                } else {
                                    unlockStorageWrite();
                                }
                                (z ? getReadLock() : getWriteLock()).unlock();
                                return i;
                            }
                            z3 = true;
                        }
                        iNextDuplicatedValueRecord = i;
                    } else if (i < 0) {
                        iNextDuplicatedValueRecord = -i;
                        while (true) {
                            int i2 = this.myCollisionResolutionStorage.getInt(iNextDuplicatedValueRecord);
                            if (isKeyAtIndex(obj, i2)) {
                                if (z2) {
                                    z3 = true;
                                    break;
                                }
                                if (z) {
                                    unlockStorageRead();
                                } else {
                                    unlockStorageWrite();
                                }
                                (z ? getReadLock() : getWriteLock()).unlock();
                                return i2;
                            }
                            int i3 = this.myCollisionResolutionStorage.getInt(iNextDuplicatedValueRecord + 4);
                            if (i3 == 0) {
                                z3 = false;
                                break;
                            }
                            iNextDuplicatedValueRecord = i3;
                        }
                    } else {
                        z3 = false;
                        iNextDuplicatedValueRecord = 0;
                    }
                    if (z) {
                        if (z) {
                            unlockStorageRead();
                        } else {
                            unlockStorageWrite();
                        }
                        (z ? getReadLock() : getWriteLock()).unlock();
                        return 0;
                    }
                } else if (!z4) {
                    z3 = false;
                    iNextDuplicatedValueRecord = 0;
                } else {
                    if (!z2) {
                        if (z) {
                            unlockStorageRead();
                        } else {
                            unlockStorageWrite();
                        }
                        (z ? getReadLock() : getWriteLock()).unlock();
                        return i;
                    }
                    iNextDuplicatedValueRecord = 0;
                    z3 = true;
                }
                int iWriteData = writeData(obj, hashCode);
                this.myValuesCount++;
                PersistentEnumeratorWal<Object> persistentEnumeratorWal = this.myWal;
                if (persistentEnumeratorWal != null) {
                    persistentEnumeratorWal.enumerate(obj, iWriteData);
                }
                if (IOStatistics.DEBUG && (this.myValuesCount & 65535) == 0) {
                    IOStatistics.dump("Enumerator " + this.myFile + ": " + getStatistics());
                }
                if (iNextDuplicatedValueRecord == 0) {
                    this.myBTree.put(hashCode, iWriteData);
                } else if (!z3) {
                    if (i > 0) {
                        iNextDuplicatedValueRecord = nextDuplicatedValueRecord();
                        this.myBTree.put(hashCode, -iNextDuplicatedValueRecord);
                        this.myCollisionResolutionStorage.putInt(iNextDuplicatedValueRecord, i);
                        this.myCollisions++;
                    }
                    this.myCollisions++;
                    int iNextDuplicatedValueRecord2 = nextDuplicatedValueRecord();
                    this.myCollisionResolutionStorage.putInt(iNextDuplicatedValueRecord + 4, iNextDuplicatedValueRecord2);
                    this.myCollisionResolutionStorage.putInt(iNextDuplicatedValueRecord2, iWriteData);
                    this.myCollisionResolutionStorage.putInt(iNextDuplicatedValueRecord2 + 4, 0);
                } else if (i > 0) {
                    this.myBTree.put(hashCode, iWriteData);
                } else {
                    this.myCollisionResolutionStorage.putInt(iNextDuplicatedValueRecord, iWriteData);
                }
                if (z) {
                    unlockStorageRead();
                } else {
                    unlockStorageWrite();
                }
                (z ? getReadLock() : getWriteLock()).unlock();
                return iWriteData;
            } catch (Throwable th) {
                if (z) {
                    unlockStorageRead();
                } else {
                    unlockStorageWrite();
                }
                throw th;
            }
        } catch (Throwable th2) {
            (z ? getReadLock() : getWriteLock()).unlock();
            throw th2;
        }
    }

    @Override // com.intellij.util.io.PersistentEnumeratorBase, com.intellij.openapi.Forceable
    public void force() throws IOException {
        try {
            super.force();
            if (this.myWal != null) {
            }
        } finally {
            if (this.myWal != null) {
                this.myWal.flush();
            }
        }
    }

    public long getNonNegativeValue(Object obj) throws IOException {
        getReadLock().lock();
        try {
            try {
                lockStorageRead();
                if (!this.myBTree.get(((InlineKeyDescriptor) this.myDataDescriptor).toInt(obj), this.myResultBuf)) {
                    unlockStorageRead();
                    getReadLock().unlock();
                    return 0L;
                }
                long jKeyIdToNonNegativeOffset = keyIdToNonNegativeOffset(this.myResultBuf[0]);
                unlockStorageRead();
                getReadLock().unlock();
                return jKeyIdToNonNegativeOffset;
            } catch (Throwable th) {
                unlockStorageRead();
                throw th;
            }
        } catch (Throwable th2) {
            getReadLock().unlock();
            throw th2;
        }
    }

    public PersistentEnumeratorStatistics getStatistics() throws IOException {
        lockStorageRead();
        try {
            return new PersistentEnumeratorStatistics(this.myBTree.getStatistics(), this.myCollisions, this.myValuesCount, this.myKeyStorage.getCurrentLength(), this.myCollisionResolutionStorage.length());
        } finally {
            unlockStorageRead();
        }
    }

    @Override // com.intellij.util.io.PersistentEnumeratorBase
    public Object getValue(int i, int i2) throws IOException {
        return this.myInlineKeysNoMapping ? ((InlineKeyDescriptor) this.myDataDescriptor).fromInt(i2) : super.getValue(i, i2);
    }

    @Override // com.intellij.util.io.PersistentEnumeratorBase
    public int indexToAddr(int i) throws IOException {
        return this.myExternalKeysNoMapping ? i - 1 : this.myCollisionResolutionStorage.getInt(i);
    }

    public long keyIdToNonNegativeOffset(int i) throws IOException {
        return i >= 0 ? i : this.myCollisionResolutionStorage.getLong(-i);
    }

    @Override // com.intellij.util.io.PersistentEnumeratorBase
    public boolean processAllDataObject(final Processor<Object> processor, final PersistentEnumeratorBase.DataFilter dataFilter) throws IOException {
        if (processor == null) {
            $$$reportNull$$$0(10);
        }
        return this.myInlineKeysNoMapping ? traverseAllRecords(new PersistentEnumeratorBase.RecordsProcessor() { // from class: com.intellij.util.io.PersistentBTreeEnumerator.2
            @Override // com.intellij.util.io.PersistentEnumeratorBase.RecordsProcessor
            public boolean process(int i) throws IOException {
                PersistentEnumeratorBase.DataFilter dataFilter2 = dataFilter;
                if (dataFilter2 != null && !dataFilter2.accept(i)) {
                    return true;
                }
                return processor.process(((InlineKeyDescriptor) PersistentBTreeEnumerator.this.myDataDescriptor).fromInt(getCurrentKey()));
            }
        }) : super.processAllDataObject(processor, dataFilter);
    }

    public void putNonNegativeValue(Object obj, long j) throws IOException {
        int i;
        getWriteLock().lock();
        try {
            lockStorageWrite();
            try {
                int i2 = ((InlineKeyDescriptor) this.myDataDescriptor).toInt(obj);
                markDirty(true);
                IntToIntBtree intToIntBtree = this.myBTree;
                if (j < SieveCacheKt.NodeLinkMask) {
                    intToIntBtree.put(i2, (int) j);
                } else if (!intToIntBtree.get(i2, this.myResultBuf) || (i = this.myResultBuf[0]) >= 0) {
                    int iNextLongValueRecord = nextLongValueRecord();
                    this.myCollisionResolutionStorage.putLong(iNextLongValueRecord, j);
                    this.myBTree.put(i2, -iNextLongValueRecord);
                } else {
                    this.myCollisionResolutionStorage.putLong(-i, j);
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

    @Override // com.intellij.util.io.PersistentEnumeratorBase
    public void setRecordHandler(PersistentEnumeratorBase.RecordBufferHandler<PersistentEnumeratorBase> recordBufferHandler) {
        if (recordBufferHandler == null) {
            $$$reportNull$$$0(13);
        }
        this.myExternalKeysNoMapping = false;
        super.setRecordHandler(recordBufferHandler);
    }

    @Override // com.intellij.util.io.PersistentEnumeratorBase
    public void setupEmptyFile() throws Exception {
        this.myLogicalFileLength = 128;
        this.myDataPageStart = -1;
        this.myFirstPageStart = -1;
        this.myDuplicatedValuesPageStart = -1;
        initBtree(true);
        storeVars(true);
    }

    @Override // com.intellij.util.io.PersistentEnumeratorBase
    public int setupValueId(int i, int i2) throws IOException {
        if (this.myExternalKeysNoMapping) {
            return addrToIndex(i2);
        }
        PersistentEnumeratorBase.RecordBufferHandler recordHandler = getRecordHandler();
        byte[] recordBuffer = recordHandler.getRecordBuffer(this);
        int iRecordWriteOffset = recordHandler.recordWriteOffset(this, recordBuffer);
        this.myCollisionResolutionStorage.ensureSize(recordBuffer.length + iRecordWriteOffset);
        if (!this.myInlineKeysNoMapping) {
            this.myCollisionResolutionStorage.putInt(iRecordWriteOffset, i2);
        }
        return iRecordWriteOffset;
    }

    @Override // com.intellij.util.io.PersistentEnumeratorBase
    public boolean shouldLockOnValueOf() {
        return true;
    }

    @Override // com.intellij.util.io.PersistentEnumeratorBase
    public boolean traverseAllRecords(final PersistentEnumeratorBase.RecordsProcessor recordsProcessor) throws IOException {
        if (recordsProcessor == null) {
            $$$reportNull$$$0(12);
        }
        getReadLock().lock();
        try {
            lockStorageRead();
            try {
                boolean zProcessMappings = this.myBTree.processMappings(new AbstractIntToIntBtree.KeyValueProcessor() { // from class: com.intellij.util.io.PersistentBTreeEnumerator.4
                    @Override // com.intellij.util.io.AbstractIntToIntBtree.KeyValueProcessor
                    public boolean process(int i, int i2) throws IOException {
                        recordsProcessor.setCurrentKey(i);
                        if (i2 > 0 || PersistentBTreeEnumerator.this.myInlineKeysNoMapping) {
                            return recordsProcessor.process(i2);
                        }
                        int i3 = -i2;
                        while (i3 != 0) {
                            if (!recordsProcessor.process(PersistentBTreeEnumerator.this.myCollisionResolutionStorage.getInt(i3))) {
                                return false;
                            }
                            i3 = PersistentBTreeEnumerator.this.myCollisionResolutionStorage.getInt(i3 + 4);
                        }
                        return true;
                    }
                });
                unlockStorageRead();
                getReadLock().unlock();
                return zProcessMappings;
            } catch (Throwable th) {
                unlockStorageRead();
                throw th;
            }
        } catch (Throwable th2) {
            getReadLock().unlock();
            throw th2;
        }
    }

    @Override // com.intellij.util.io.PersistentEnumeratorBase
    public boolean trySelfHeal() {
        if (!SystemProperties.getBooleanProperty("idea.persistent.enumerator.do.self.heal", false)) {
            return false;
        }
        PersistentEnumeratorBase.LOG.info("Trying to self-heal " + this.myFile);
        final ArrayList<C1DataWithOffset> arrayList = new ArrayList();
        try {
            iterateData(new AppendableObjectStorage.StorageObjectProcessor() { // from class: p0b
                @Override // com.intellij.util.io.keyStorage.AppendableObjectStorage.StorageObjectProcessor
                public final boolean process(int i, Object obj) {
                    return PersistentBTreeEnumerator.f(this.a, arrayList, i, obj);
                }
            });
            lockStorageWrite();
            try {
                this.myCollisionResolutionStorage.clear();
                this.myKeyStorage.clear();
                this.myCollisionResolutionStorage.ensureSize(4096L);
                markDirty(true);
                putMetaData(0L);
                putMetaData2(0L);
                IntToIntBtree intToIntBtree = this.myBTree;
                if (intToIntBtree != null) {
                    intToIntBtree.doClose();
                }
                setupEmptyFile();
                doFlush();
                unlockStorageWrite();
                for (C1DataWithOffset c1DataWithOffset : arrayList) {
                    int iEnumerate = enumerate(c1DataWithOffset.data);
                    int i = c1DataWithOffset.offset + 1;
                    if (i != iEnumerate) {
                        throw new IOException("Enumeration order has been changed while self-healing, were " + i + " now " + iEnumerate);
                    }
                }
                return true;
            } catch (Throwable th) {
                unlockStorageWrite();
                throw th;
            }
        } catch (Throwable th2) {
            PersistentEnumeratorBase.LOG.info(th2);
            return false;
        }
    }

    @Override // com.intellij.util.io.PersistentEnumeratorBase, com.intellij.util.io.DataEnumerator
    public Object valueOf(int i) throws IOException {
        return super.valueOf(i);
    }
}
