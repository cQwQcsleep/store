package com.intellij.util.io.stats;

import com.intellij.util.io.PersistentBTreeEnumerator;
import com.intellij.util.io.PersistentMapImpl;
import java.nio.file.Path;
import java.util.concurrent.ConcurrentHashMap;
import kotlin.Metadata;

/* JADX INFO: loaded from: /workspace/dex_all/classes.dex */
@Metadata(d1 = {"\u00008\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0007\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÇ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b\u0002\u0010\u0003J\u001e\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\u000e\u0010\r\u001a\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u0007J\u000e\u0010\u000e\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006J\u001a\u0010\u000f\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u00062\n\u0010\u0010\u001a\u0006\u0012\u0002\b\u00030\tJ\u000e\u0010\u0011\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\u0006J\u0012\u0010\u0012\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00140\u0013J\u0012\u0010\u0015\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00160\u0013R\"\u0010\u0004\u001a\u0016\u0012\u0004\u0012\u00020\u0006\u0012\f\u0012\n\u0012\u0002\b\u0003\u0012\u0002\b\u00030\u00070\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\b\u001a\u0012\u0012\u0004\u0012\u00020\u0006\u0012\b\u0012\u0006\u0012\u0002\b\u00030\t0\u0005X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0017"}, d2 = {"Lcom/intellij/util/io/stats/StorageStatsRegistrar;", "", "<init>", "()V", "maps", "Ljava/util/concurrent/ConcurrentHashMap;", "Ljava/nio/file/Path;", "Lcom/intellij/util/io/PersistentMapImpl;", "enumerators", "Lcom/intellij/util/io/PersistentBTreeEnumerator;", "registerMap", "", "path", "map", "unregisterMap", "registerEnumerator", "enumerator", "unregisterEnumerator", "dumpStatsForOpenMaps", "", "Lcom/intellij/util/io/stats/PersistentHashMapStatistics;", "dumpStatsForOpenEnumerators", "Lcom/intellij/util/io/stats/PersistentEnumeratorStatistics;", "intellij.platform.util"}, k = 1, mv = {2, 0, 0}, xi = 48)
public final class StorageStatsRegistrar {
    public static final StorageStatsRegistrar INSTANCE = new StorageStatsRegistrar();
    private static final ConcurrentHashMap<Path, PersistentMapImpl<?, ?>> maps = new ConcurrentHashMap<>();
    private static final ConcurrentHashMap<Path, PersistentBTreeEnumerator> enumerators = new ConcurrentHashMap<>();

    private StorageStatsRegistrar() {
    }

    public final void registerEnumerator(Path path, PersistentBTreeEnumerator enumerator) {
        path.getClass();
        enumerator.getClass();
        ConcurrentHashMap<Path, PersistentBTreeEnumerator> concurrentHashMap = enumerators;
        Path absolutePath = path.toAbsolutePath();
        absolutePath.getClass();
        concurrentHashMap.put(absolutePath, enumerator);
    }

    public final void registerMap(Path path, PersistentMapImpl<?, ?> map) {
        path.getClass();
        map.getClass();
        ConcurrentHashMap<Path, PersistentMapImpl<?, ?>> concurrentHashMap = maps;
        Path absolutePath = path.toAbsolutePath();
        absolutePath.getClass();
        concurrentHashMap.put(absolutePath, map);
    }

    public final void unregisterEnumerator(Path path) {
        path.getClass();
        ConcurrentHashMap<Path, PersistentBTreeEnumerator> concurrentHashMap = enumerators;
        Path absolutePath = path.toAbsolutePath();
        absolutePath.getClass();
        concurrentHashMap.remove(absolutePath);
    }

    public final void unregisterMap(Path path) {
        path.getClass();
        ConcurrentHashMap<Path, PersistentMapImpl<?, ?>> concurrentHashMap = maps;
        Path absolutePath = path.toAbsolutePath();
        absolutePath.getClass();
        concurrentHashMap.remove(absolutePath);
    }
}
