package org.jetbrains.kotlin.incremental.storage;

import com.intellij.util.CommonProcessors;
import com.intellij.util.io.DataExternalizer;
import com.intellij.util.io.IOUtil;
import com.intellij.util.io.KeyDescriptor;
import com.intellij.util.io.PersistentHashMap;
import java.io.File;
import java.io.IOException;
import java.util.Set;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.SetsKt;
import kotlin.jvm.functions.Function0;
import org.jetbrains.kotlin.incremental.storage.LazyStorage;
import org.jetbrains.kotlin.metadata.jvm.deserialization.JvmProtoBufUtil;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0010\"\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\b\b\u0016\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u000e\u0012\u0004\u0012\u0002H\u0001\u0012\u0004\u0012\u0002H\u00020\u0003B+\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007\u0012\f\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\t¢\u0006\u0004\b\n\u0010\u000bJ\u0016\u0010\u0015\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000fH\u0002J\u0014\u0010\u0016\u001a\u000e\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u00010\u000fH\u0004J$\u0010\u0017\u001a\u001e\u0012\f\u0012\n \u0018*\u0004\u0018\u00018\u00008\u0000\u0012\f\u0012\n \u0018*\u0004\u0018\u00018\u00018\u00010\u000fH\u0002J\u0016\u0010\u001d\u001a\u00020\u00112\u0006\u0010\u001e\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010\u001fJ\u0018\u0010 \u001a\u0004\u0018\u00018\u00012\u0006\u0010\u001e\u001a\u00028\u0000H\u0096\u0002¢\u0006\u0002\u0010!J\u001e\u0010\"\u001a\u00020#2\u0006\u0010\u001e\u001a\u00028\u00002\u0006\u0010$\u001a\u00028\u0001H\u0096\u0002¢\u0006\u0002\u0010%J\u0015\u0010&\u001a\u00020#2\u0006\u0010\u001e\u001a\u00028\u0000H\u0016¢\u0006\u0002\u0010'J\b\u0010(\u001a\u00020#H\u0016J\b\u0010)\u001a\u00020#H\u0016J\b\u0010*\u001a\u00020#H\u0016R\u0014\u0010\u0004\u001a\u00020\u0005X\u0096\u0004¢\u0006\b\n\u0000\u001a\u0004\b\f\u0010\rR\u0014\u0010\u0006\u001a\b\u0012\u0004\u0012\u00028\u00000\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00010\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u001c\u0010\u000e\u001a\u0010\u0012\u0004\u0012\u00028\u0000\u0012\u0004\u0012\u00028\u0001\u0018\u00010\u000fX\u0082\u000e¢\u0006\u0002\n\u0000R\u001b\u0010\u0010\u001a\u00020\u00118BX\u0082\u0084\u0002¢\u0006\f\n\u0004\b\u0013\u0010\u0014\u001a\u0004\b\u0010\u0010\u0012R\u001a\u0010\u0019\u001a\b\u0012\u0004\u0012\u00028\u00000\u001a8VX\u0096\u0004¢\u0006\u0006\u001a\u0004\b\u001b\u0010\u001c¨\u0006+"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/LazyStorage;", "KEY", "VALUE", "Lorg/jetbrains/kotlin/incremental/storage/PersistentStorage;", "storageFile", "Ljava/io/File;", "keyDescriptor", "Lcom/intellij/util/io/KeyDescriptor;", "valueExternalizer", "Lcom/intellij/util/io/DataExternalizer;", "<init>", "(Ljava/io/File;Lcom/intellij/util/io/KeyDescriptor;Lcom/intellij/util/io/DataExternalizer;)V", "getStorageFile", "()Ljava/io/File;", "storage", "Lcom/intellij/util/io/PersistentHashMap;", "isStorageFileExist", "", "()Z", "isStorageFileExist$delegate", "Lkotlin/Lazy;", "getStorageIfExists", "getStorageOrCreateNew", "createMap", JvmProtoBufUtil.PLATFORM_TYPE_ID, "keys", "", "getKeys", "()Ljava/util/Set;", "contains", "key", "(Ljava/lang/Object;)Z", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "set", "", "value", "(Ljava/lang/Object;Ljava/lang/Object;)V", "remove", "(Ljava/lang/Object;)V", "flush", "close", "clean", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public class LazyStorage<KEY, VALUE> implements PersistentStorage<KEY, VALUE> {

    /* JADX INFO: renamed from: isStorageFileExist$delegate, reason: from kotlin metadata */
    private final Lazy isStorageFileExist;
    private final KeyDescriptor<KEY> keyDescriptor;
    private PersistentHashMap<KEY, VALUE> storage;
    private final File storageFile;
    private final DataExternalizer<VALUE> valueExternalizer;

    public LazyStorage(File file, KeyDescriptor<KEY> keyDescriptor, DataExternalizer<VALUE> dataExternalizer) {
        file.getClass();
        keyDescriptor.getClass();
        dataExternalizer.getClass();
        this.storageFile = file;
        this.keyDescriptor = keyDescriptor;
        this.valueExternalizer = dataExternalizer;
        this.isStorageFileExist = LazyKt.lazy(new Function0() { // from class: hx8
            public final Object invoke() {
                return Boolean.valueOf(LazyStorage.a(this.b));
            }
        });
    }

    public static boolean a(LazyStorage lazyStorage) {
        return lazyStorage.getStorageFile().exists();
    }

    private final PersistentHashMap<KEY, VALUE> createMap() {
        return new PersistentHashMap<>(getStorageFile().toPath(), this.keyDescriptor, this.valueExternalizer);
    }

    private final PersistentHashMap<KEY, VALUE> getStorageIfExists() {
        PersistentHashMap<KEY, VALUE> persistentHashMap = this.storage;
        if (persistentHashMap != null) {
            return persistentHashMap;
        }
        if (!isStorageFileExist()) {
            return null;
        }
        PersistentHashMap<KEY, VALUE> persistentHashMapCreateMap = createMap();
        this.storage = persistentHashMapCreateMap;
        return persistentHashMapCreateMap;
    }

    private final boolean isStorageFileExist() {
        return ((Boolean) this.isStorageFileExist.getValue()).booleanValue();
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized void clean() {
        try {
            try {
                PersistentHashMap<KEY, VALUE> persistentHashMap = this.storage;
                if (persistentHashMap != null) {
                    persistentHashMap.close();
                }
                this.storage = null;
                if (!IOUtil.deleteAllFilesStartingWith(getStorageFile())) {
                    throw new IOException("Could not delete internal storage: " + getStorageFile().getAbsolutePath());
                }
            } catch (Throwable th) {
                this.storage = null;
                if (IOUtil.deleteAllFilesStartingWith(getStorageFile())) {
                    throw th;
                }
                throw new IOException("Could not delete internal storage: " + getStorageFile().getAbsolutePath());
            }
        } catch (Throwable th2) {
            throw th2;
        }
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage, java.io.Closeable, java.lang.AutoCloseable
    public synchronized void close() {
        try {
            PersistentHashMap<KEY, VALUE> persistentHashMap = this.storage;
            if (persistentHashMap != null) {
                persistentHashMap.close();
            }
            this.storage = null;
        } catch (Throwable th) {
            this.storage = null;
            throw th;
        }
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized boolean contains(KEY key) {
        PersistentHashMap<KEY, VALUE> storageIfExists;
        storageIfExists = getStorageIfExists();
        return storageIfExists != null ? storageIfExists.containsMapping(key) : false;
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized void flush() {
        PersistentHashMap<KEY, VALUE> persistentHashMap = this.storage;
        if (persistentHashMap != null) {
            persistentHashMap.force();
        }
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized VALUE get(KEY key) {
        PersistentHashMap<KEY, VALUE> storageIfExists;
        storageIfExists = getStorageIfExists();
        return storageIfExists != null ? (VALUE) storageIfExists.get(key) : null;
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized Set<KEY> getKeys() {
        Set setCreateSetBuilder;
        try {
            setCreateSetBuilder = SetsKt.createSetBuilder();
            PersistentHashMap<KEY, VALUE> storageIfExists = getStorageIfExists();
            if (storageIfExists != null) {
                storageIfExists.processKeysWithExistingMapping(new CommonProcessors.CollectProcessor(setCreateSetBuilder));
            }
        } catch (Throwable th) {
            throw th;
        }
        return SetsKt.build(setCreateSetBuilder);
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public File getStorageFile() {
        return this.storageFile;
    }

    public final PersistentHashMap<KEY, VALUE> getStorageOrCreateNew() {
        PersistentHashMap<KEY, VALUE> persistentHashMap = this.storage;
        if (persistentHashMap != null) {
            return persistentHashMap;
        }
        PersistentHashMap<KEY, VALUE> persistentHashMapCreateMap = createMap();
        this.storage = persistentHashMapCreateMap;
        return persistentHashMapCreateMap;
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized void remove(KEY key) {
        PersistentHashMap<KEY, VALUE> storageIfExists = getStorageIfExists();
        if (storageIfExists != null) {
            storageIfExists.remove(key);
        }
    }

    @Override // org.jetbrains.kotlin.incremental.storage.PersistentStorage
    public synchronized void set(KEY key, VALUE value) {
        getStorageOrCreateNew().put(key, value);
    }
}
