package com.intellij.util.io;

import androidx.collection.ScatterMapKt;
import com.intellij.util.ConcurrencyUtil;
import com.intellij.util.SystemProperties;
import java.io.IOException;
import java.nio.file.Path;
import java.util.concurrent.ExecutorService;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
public final class PersistentMapBuilder<Key, Value> {
    private Boolean myCompactOnClose;
    private boolean myEnableWal;
    private final Path myFile;
    private Boolean myHasChunks;
    private Integer myInitialSize;
    private Boolean myInlineValues;
    private Boolean myIsReadOnly;
    private final KeyDescriptor<Key> myKeyDescriptor;
    private StorageLockContext myLockContext;
    private final DataExternalizer<Value> myValueExternalizer;
    private Integer myVersion;
    private ExecutorService myWalExecutor;

    private static /* synthetic */ void $$$reportNull$$$0(int i) {
        String str;
        int i2;
        switch (i) {
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 11:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
                str = "@NotNull method %s.%s must not return null";
                break;
            case 12:
            case 13:
            case 14:
            case 20:
            default:
                str = "Argument for @NotNull parameter '%s' of %s.%s must not be null";
                break;
        }
        switch (i) {
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 11:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
                i2 = 2;
                break;
            case 12:
            case 13:
            case 14:
            case 20:
            default:
                i2 = 3;
                break;
        }
        Object[] objArr = new Object[i2];
        switch (i) {
            case 1:
            case 5:
            case 13:
                objArr[0] = "keyDescriptor";
                break;
            case 2:
            case ScatterMapKt.DefaultScatterCapacity /* 6 */:
            case 14:
                objArr[0] = "valueExternalizer";
                break;
            case 3:
                objArr[0] = "walExecutor";
                break;
            case 4:
            case 12:
            default:
                objArr[0] = "file";
                break;
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 11:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
                objArr[0] = "com/intellij/util/io/PersistentMapBuilder";
                break;
            case 20:
                objArr[0] = "service";
                break;
        }
        switch (i) {
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
                objArr[1] = "buildImplementation";
                break;
            case 9:
                objArr[1] = "getFile";
                break;
            case 10:
                objArr[1] = "getKeyDescriptor";
                break;
            case 11:
                objArr[1] = "getValueExternalizer";
                break;
            case 12:
            case 13:
            case 14:
            case 20:
            default:
                objArr[1] = "com/intellij/util/io/PersistentMapBuilder";
                break;
            case 15:
                objArr[1] = "withInitialSize";
                break;
            case 16:
                objArr[1] = "withVersion";
                break;
            case 17:
                objArr[1] = "withReadonly";
                break;
            case 18:
                objArr[1] = "readonly";
                break;
            case 19:
                objArr[1] = "withWal";
                break;
            case 21:
                objArr[1] = "withWalExecutor";
                break;
            case 22:
            case 23:
                objArr[1] = "inlineValues";
                break;
            case 24:
                objArr[1] = "withStorageLockContext";
                break;
            case 25:
                objArr[1] = "hasChunks";
                break;
            case 26:
                objArr[1] = "hasNoChunks";
                break;
            case 27:
                objArr[1] = "withCompactOnClose";
                break;
            case 28:
                objArr[1] = "compactOnClose";
                break;
            case 29:
                objArr[1] = "getWalExecutor";
                break;
        }
        switch (i) {
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 11:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
                break;
            case 12:
            case 13:
            case 14:
                objArr[2] = "newBuilder";
                break;
            case 20:
                objArr[2] = "withWalExecutor";
                break;
            case 30:
                objArr[2] = "copyWithFile";
                break;
            default:
                objArr[2] = "<init>";
                break;
        }
        String str2 = String.format(str, objArr);
        switch (i) {
            case ScatterMapKt.ClonedMetadataCount /* 7 */:
            case 8:
            case 9:
            case 10:
            case 11:
            case 15:
            case 16:
            case 17:
            case 18:
            case 19:
            case 21:
            case 22:
            case 23:
            case 24:
            case 25:
            case 26:
            case 27:
            case 28:
            case 29:
                throw new IllegalStateException(str2);
            case 12:
            case 13:
            case 14:
            case 20:
            default:
                throw new IllegalArgumentException(str2);
        }
    }

    private PersistentMapBuilder(Path path, KeyDescriptor<Key> keyDescriptor, DataExternalizer<Value> dataExternalizer, Integer num, Integer num2, StorageLockContext storageLockContext, Boolean bool, Boolean bool2, Boolean bool3, Boolean bool4, ExecutorService executorService, boolean z) {
        if (path == null) {
            $$$reportNull$$$0(0);
        }
        if (keyDescriptor == null) {
            $$$reportNull$$$0(1);
        }
        if (dataExternalizer == null) {
            $$$reportNull$$$0(2);
        }
        if (executorService == null) {
            $$$reportNull$$$0(3);
        }
        this.myFile = path;
        this.myKeyDescriptor = keyDescriptor;
        this.myValueExternalizer = dataExternalizer;
        this.myInitialSize = num;
        this.myVersion = num2;
        this.myLockContext = storageLockContext;
        this.myInlineValues = bool;
        this.myIsReadOnly = bool2;
        this.myHasChunks = bool3;
        this.myCompactOnClose = bool4;
        this.myWalExecutor = executorService;
        this.myEnableWal = z;
    }

    public static <Key, Value> PersistentMapBuilder<Key, Value> newBuilder(Path path, KeyDescriptor<Key> keyDescriptor, DataExternalizer<Value> dataExternalizer) {
        if (path == null) {
            $$$reportNull$$$0(12);
        }
        if (keyDescriptor == null) {
            $$$reportNull$$$0(13);
        }
        if (dataExternalizer == null) {
            $$$reportNull$$$0(14);
        }
        return new PersistentMapBuilder<>(path, keyDescriptor, dataExternalizer);
    }

    public PersistentHashMap<Key, Value> build() throws IOException {
        return new PersistentHashMap<>(buildImplementation());
    }

    public PersistentMapBase<Key, Value> buildImplementation() throws IOException {
        Boolean bool;
        if (this.myHasChunks != null) {
            ThreadLocal<Boolean> threadLocal = PersistentHashMapValueStorage.CreationTimeOptions.HAS_NO_CHUNKS;
            bool = threadLocal.get();
            threadLocal.set(Boolean.valueOf(!this.myHasChunks.booleanValue()));
        } else {
            bool = null;
        }
        ThreadLocal<Boolean> threadLocal2 = PersistentHashMapValueStorage.CreationTimeOptions.READONLY;
        Boolean bool2 = threadLocal2.get();
        threadLocal2.set(this.myIsReadOnly);
        try {
            if (SystemProperties.getBooleanProperty("idea.use.in.memory.persistent.map", false)) {
                PersistentMapInMemory persistentMapInMemory = new PersistentMapInMemory(this);
                if (this.myHasChunks != null) {
                    PersistentHashMapValueStorage.CreationTimeOptions.HAS_NO_CHUNKS.set(bool);
                }
                return persistentMapInMemory;
            }
            PersistentMapImpl persistentMapImpl = new PersistentMapImpl(this);
            if (this.myHasChunks != null) {
                PersistentHashMapValueStorage.CreationTimeOptions.HAS_NO_CHUNKS.set(bool);
            }
            return persistentMapImpl;
        } finally {
            if (this.myHasChunks != null) {
                PersistentHashMapValueStorage.CreationTimeOptions.HAS_NO_CHUNKS.set(bool);
            }
            PersistentHashMapValueStorage.CreationTimeOptions.READONLY.set(bool2);
        }
    }

    public PersistentMapBuilder<Key, Value> copy() {
        return new PersistentMapBuilder<>(this.myFile, this.myKeyDescriptor, this.myValueExternalizer, this.myInitialSize, this.myVersion, this.myLockContext, this.myInlineValues, this.myIsReadOnly, this.myHasChunks, this.myCompactOnClose, this.myWalExecutor, this.myEnableWal);
    }

    public boolean getCompactOnClose(boolean z) {
        Boolean bool = this.myCompactOnClose;
        return bool != null ? bool.booleanValue() : z;
    }

    public Path getFile() {
        Path path = this.myFile;
        if (path == null) {
            $$$reportNull$$$0(9);
        }
        return path;
    }

    public int getInitialSize(int i) {
        Integer num = this.myInitialSize;
        return num != null ? num.intValue() : i;
    }

    public boolean getInlineValues(boolean z) {
        Boolean bool = this.myInlineValues;
        return bool != null ? bool.booleanValue() : z;
    }

    public KeyDescriptor<Key> getKeyDescriptor() {
        KeyDescriptor<Key> keyDescriptor = this.myKeyDescriptor;
        if (keyDescriptor == null) {
            $$$reportNull$$$0(10);
        }
        return keyDescriptor;
    }

    public StorageLockContext getLockContext() {
        return this.myLockContext;
    }

    public boolean getReadOnly(boolean z) {
        Boolean bool = this.myIsReadOnly;
        return bool != null ? bool.booleanValue() : z;
    }

    public DataExternalizer<Value> getValueExternalizer() {
        DataExternalizer<Value> dataExternalizer = this.myValueExternalizer;
        if (dataExternalizer == null) {
            $$$reportNull$$$0(11);
        }
        return dataExternalizer;
    }

    public int getVersion(int i) {
        Integer num = this.myVersion;
        return num != null ? num.intValue() : i;
    }

    public ExecutorService getWalExecutor() {
        ExecutorService executorService = this.myWalExecutor;
        if (executorService == null) {
            $$$reportNull$$$0(29);
        }
        return executorService;
    }

    public PersistentMapBuilder<Key, Value> inlineValues(boolean z) {
        if (!z || (this.myValueExternalizer instanceof IntInlineKeyDescriptor)) {
            this.myInlineValues = Boolean.valueOf(z);
            return this;
        }
        sle.a("can't inline values for externalizer ", this.myValueExternalizer.getClass());
        return null;
    }

    public boolean isEnableWal() {
        return this.myEnableWal;
    }

    public PersistentMapBuilder<Key, Value> withInitialSize(int i) {
        this.myInitialSize = Integer.valueOf(i);
        return this;
    }

    public PersistentMapBuilder<Key, Value> withReadonly(boolean z) {
        this.myIsReadOnly = Boolean.valueOf(z);
        return this;
    }

    public PersistentMapBuilder<Key, Value> withStorageLockContext(StorageLockContext storageLockContext) {
        this.myLockContext = storageLockContext;
        return this;
    }

    public PersistentMapBuilder<Key, Value> withVersion(int i) {
        this.myVersion = Integer.valueOf(i);
        return this;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    private PersistentMapBuilder(Path path, KeyDescriptor<Key> keyDescriptor, DataExternalizer<Value> dataExternalizer) {
        this(path, keyDescriptor, dataExternalizer, null, null, null, null, null, null, null, ConcurrencyUtil.newSameThreadExecutorService(), false);
        if (path == null) {
            $$$reportNull$$$0(4);
        }
        if (keyDescriptor == null) {
            $$$reportNull$$$0(5);
        }
        if (dataExternalizer == null) {
            $$$reportNull$$$0(6);
        }
    }
}
