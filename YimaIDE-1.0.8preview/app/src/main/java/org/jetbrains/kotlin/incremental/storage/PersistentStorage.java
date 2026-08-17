package org.jetbrains.kotlin.incremental.storage;

import java.io.Closeable;
import java.io.File;
import java.util.Set;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes2.dex */
@Metadata(d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\"\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\b\b\bf\u0018\u0000*\u0004\b\u0000\u0010\u0001*\u0004\b\u0001\u0010\u00022\u00020\u0003J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00028\u0000H¦\u0002¢\u0006\u0002\u0010\u000fJ\u0018\u0010\u0010\u001a\u0004\u0018\u00018\u00012\u0006\u0010\u000e\u001a\u00028\u0000H¦\u0002¢\u0006\u0002\u0010\u0011J\u001e\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00028\u00002\u0006\u0010\u0014\u001a\u00028\u0001H¦\u0002¢\u0006\u0002\u0010\u0015J\u0015\u0010\u0016\u001a\u00020\u00132\u0006\u0010\u000e\u001a\u00028\u0000H&¢\u0006\u0002\u0010\u0017J\b\u0010\u0018\u001a\u00020\u0013H&J\b\u0010\u0019\u001a\u00020\u0013H&J\b\u0010\u001a\u001a\u00020\u0013H&R\u0012\u0010\u0004\u001a\u00020\u0005X¦\u0004¢\u0006\u0006\u001a\u0004\b\u0006\u0010\u0007R\u0018\u0010\b\u001a\b\u0012\u0004\u0012\u00028\u00000\tX¦\u0004¢\u0006\u0006\u001a\u0004\b\n\u0010\u000bø\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001¨\u0006\u001bÀ\u0006\u0001"}, d2 = {"Lorg/jetbrains/kotlin/incremental/storage/PersistentStorage;", "KEY", "VALUE", "Ljava/io/Closeable;", "storageFile", "Ljava/io/File;", "getStorageFile", "()Ljava/io/File;", "keys", "", "getKeys", "()Ljava/util/Set;", "contains", "", "key", "(Ljava/lang/Object;)Z", "get", "(Ljava/lang/Object;)Ljava/lang/Object;", "set", "", "value", "(Ljava/lang/Object;Ljava/lang/Object;)V", "remove", "(Ljava/lang/Object;)V", "flush", "close", "clean", "org.jetbrains.kotlin:kotlin-build-common"}, k = 1, mv = {2, 2, 0}, xi = 48)
public interface PersistentStorage<KEY, VALUE> extends Closeable {
    void clean();

    void close();

    boolean contains(KEY key);

    void flush();

    VALUE get(KEY key);

    Set<KEY> getKeys();

    File getStorageFile();

    void remove(KEY key);

    void set(KEY key, VALUE value);
}
